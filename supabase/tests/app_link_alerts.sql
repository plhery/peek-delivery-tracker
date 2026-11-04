\set ON_ERROR_STOP on
begin;
insert into auth.users (id, email, is_anonymous) values
 ('d3000000-0000-4000-8000-000000000001', 'app-sharer@example.test', false),
 ('d3000000-0000-4000-8000-000000000002', 'app-other@example.test', false);

-- The app's alerts belong to the server, as a browser's do.
do $$
declare
  role_name text;
  service_function text;
begin
  foreach role_name in array array['anon', 'authenticated'] loop
    foreach service_function in array array[
      'public.add_parcel_link_app_alert(text,text,text,text,text,text,text)',
      'public.remove_parcel_link_app_alert(text,text)'
    ] loop
      if has_function_privilege(role_name, service_function, 'EXECUTE') then
        raise exception '% may execute %', role_name, service_function;
      end if;
    end loop;
  end loop;
end;
$$;

set local role service_role;

-- A lookup's link: an iPhone turns its alert on and off, a viewer's and the owner's alike.
do $$
declare
  key_a constant text := repeat('a', 64);
  token_viewer constant text := repeat('0a', 32);
  token_owner constant text := repeat('0b', 32);
  answer jsonb;
  target_link text;
  target_package uuid;
  outcome text;
  pending record;
  new_event uuid;
begin
  answer := public.create_one_off_parcel('APPALERT0001', 'unknown', null, null, key_a);
  target_link := answer#>>'{link,id}';
  target_package := (answer#>>'{package,id}')::uuid;

  if public.add_parcel_link_app_alert(target_link, null, token_viewer, 'production', 'fr', 'important') <> 'added'
      or public.add_parcel_link_app_alert(target_link, key_a, token_owner, 'development', 'en', 'all') <> 'added'
      or public.add_parcel_link_app_alert('unknownLink2', null, token_viewer, 'production', 'en', 'all') is not null then
    raise exception 'An iPhone could not turn an alert on, or an unknown link took one';
  end if;
  if not exists (
        select 1 from public.parcel_link_alerts
        where apns_token = token_viewer and apns_environment = 'production' and locale = 'fr' and not owner
          and endpoint is null and p256dh is null and auth is null)
      or not exists (select 1 from public.parcel_link_alerts where apns_token = token_owner and owner) then
    raise exception 'An iPhone''s alert was stored wrongly';
  end if;

  -- Sending it again changes the alert the iPhone has: one alert per iPhone and link.
  update public.parcel_link_alerts set failures = 2 where apns_token = token_viewer;
  outcome := public.add_parcel_link_app_alert(target_link, null, token_viewer, 'production', 'pl', 'delivery');
  if outcome <> 'updated'
      or (select count(*) from public.parcel_link_alerts where link_id = target_link) <> 2
      or not exists (
        select 1 from public.parcel_link_alerts
        where apns_token = token_viewer and locale = 'pl' and preset = 'delivery' and failures = 0) then
    raise exception 'An iPhone''s second request did not update its alert';
  end if;

  -- An alert is a browser's or an iPhone's, never both or neither.
  begin
    insert into public.parcel_link_alerts (link_id, endpoint, p256dh, auth, apns_token, apns_environment, locale, preset)
    values (target_link, 'https://fcm.googleapis.com/fcm/send/both', 'k', 'a', repeat('0c', 32), 'production', 'en', 'all');
    raise exception 'An alert held a browser and an iPhone' using errcode = 'P0001';
  exception when check_violation then null; end;
  begin
    insert into public.parcel_link_alerts (link_id, locale, preset) values (target_link, 'en', 'all');
    raise exception 'An alert held no way to be sent' using errcode = 'P0001';
  exception when check_violation then null; end;
  begin
    insert into public.parcel_link_alerts (link_id, apns_token, apns_environment, locale, preset)
    values (target_link, 'NOT-HEX', 'production', 'en', 'all');
    raise exception 'An alert took a malformed token' using errcode = 'P0001';
  exception when check_violation then null; end;

  -- The queue carries the iPhone's token and leaves the browser's fields empty.
  update public.parcel_link_alerts set created_at = now() - interval '1 hour' where link_id = target_link;
  insert into public.tracking_events (package_id, provider_event_id, stage, description, occurred_at)
  values (target_package, 'test:app-news', 'in_transit', 'Synthetic scan', now()) returning id into new_event;
  select * into pending from public.pending_parcel_link_alerts where apns_token = token_viewer;
  if pending.event_id is distinct from new_event or pending.apns_environment <> 'production'
      or pending.endpoint is not null or pending.account_endpoint then
    raise exception 'An iPhone''s alert is queued wrongly: %', to_jsonb(pending);
  end if;

  -- A stopped link takes the owner's iPhone, not a viewer's.
  update public.parcel_links set shared = false, stopped_at = now() where id = target_link;
  if public.add_parcel_link_app_alert(target_link, null, repeat('0d', 32), 'production', 'en', 'all') <> 'stopped'
      or public.add_parcel_link_app_alert(target_link, key_a, token_owner, 'production', 'en', 'all') <> 'updated' then
    raise exception 'A stopped link took a viewer''s iPhone, or refused its owner''s';
  end if;
  update public.parcel_links set shared = true, stopped_at = null where id = target_link;

  -- Ten from viewers, browsers and iPhones together.
  for n in 1..9 loop
    outcome := public.add_parcel_link_alert(target_link, null, 'https://fcm.googleapis.com/fcm/send/app-cap-' || n, 'k', 'a', 'en', 'all');
  end loop;
  if public.add_parcel_link_app_alert(target_link, null, repeat('0e', 32), 'production', 'en', 'all') <> 'full' then
    raise exception 'An iPhone''s alert went past the ten a link takes from viewers';
  end if;

  -- Only the token turns its alert off.
  if public.remove_parcel_link_app_alert(target_link, repeat('0f', 32))
      or public.remove_parcel_link_app_alert('unknownLink2', token_viewer)
      or not public.remove_parcel_link_app_alert(target_link, token_viewer) then
    raise exception 'An iPhone''s alert was turned off without its token, or not with it';
  end if;
  if exists (select 1 from public.parcel_link_alerts where apns_token = token_viewer) then
    raise exception 'An iPhone''s alert was turned off without its token, or not with it';
  end if;

  -- A journey that is over takes no alert.
  update public.packages set current_stage = 'delivered' where id = target_package;
  if public.add_parcel_link_app_alert(target_link, null, repeat('10', 32), 'production', 'en', 'all') <> 'finished' then
    raise exception 'A delivered parcel took an iPhone''s alert';
  end if;
end;
$$;

-- An alert on an iPhone the parcel's own account gets the app's notifications on is marked, so it is not told twice.
do $$
declare
  sharer constant uuid := 'd3000000-0000-4000-8000-000000000001';
  other constant uuid := 'd3000000-0000-4000-8000-000000000002';
  device_token constant text := repeat('1a', 32);
  target_link text;
  new_event uuid;
begin
  insert into public.packages (id, user_id, tracking_number, carrier, current_stage)
  values ('d4000000-0000-4000-8000-000000000001', sharer, 'APPALERT0101', 'unknown', 'in_transit');
  insert into public.parcel_links (package_id, created_by)
  values ('d4000000-0000-4000-8000-000000000001', sharer) returning id into target_link;
  if public.add_parcel_link_app_alert(target_link, null, device_token, 'production', 'en', 'all') <> 'added' then
    raise exception 'An account''s shared link refused an iPhone''s alert';
  end if;
  update public.parcel_link_alerts set created_at = now() - interval '1 hour' where link_id = target_link;
  insert into public.native_push_devices (user_id, token, environment, locale) values (sharer, device_token, 'production', 'en');
  insert into public.tracking_events (package_id, provider_event_id, stage, description, occurred_at)
  values ('d4000000-0000-4000-8000-000000000001', 'test:app-account', 'in_transit', 'Synthetic scan', now())
  returning id into new_event;
  if (select account_endpoint from public.pending_parcel_link_alerts where event_id = new_event) is distinct from true then
    raise exception 'An alert on the owner''s own iPhone is not marked';
  end if;
  update public.native_push_devices set disabled_at = now() where token = device_token;
  if (select account_endpoint from public.pending_parcel_link_alerts where event_id = new_event) is distinct from false then
    raise exception 'A disabled iPhone of the account still marks the alert';
  end if;
  update public.native_push_devices set disabled_at = null, user_id = other where token = device_token;
  if (select account_endpoint from public.pending_parcel_link_alerts where event_id = new_event) is distinct from false then
    raise exception 'Another account''s iPhone marks the alert';
  end if;
end;
$$;
rollback;
