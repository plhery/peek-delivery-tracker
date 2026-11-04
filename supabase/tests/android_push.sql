\set ON_ERROR_STOP on
begin;
insert into auth.users (id, email, is_anonymous) values
 ('d5000000-0000-4000-8000-000000000001', 'android-owner@example.test', false),
 ('d5000000-0000-4000-8000-000000000002', 'android-friend@example.test', false);

-- The functions an Android phone uses stay the server's.
do $$
declare
  role_name text;
  service_function text;
begin
  foreach role_name in array array['anon', 'authenticated'] loop
    foreach service_function in array array[
      'public.add_parcel_link_app_alert(text,text,text,text,text,text,text)',
      'public.remove_parcel_link_app_alert(text,text)',
      'public.claim_friendship_push(boolean,boolean,integer,boolean)'
    ] loop
      if has_function_privilege(role_name, service_function, 'EXECUTE') then
        raise exception '% may execute %', role_name, service_function;
      end if;
    end loop;
  end loop;
end;
$$;

set local role service_role;

-- A device is an iPhone's unless it says otherwise; an Android phone's token is FCM's and has no
-- development service.
do $$
declare
  owner_id constant uuid := 'd5000000-0000-4000-8000-000000000001';
  fcm_token constant text := 'synthetic-fcm:' || repeat('Ab0_-', 20);
begin
  insert into public.native_push_devices (user_id, token, environment, subscribed_at)
  values (owner_id, repeat('d5', 32), 'production', now() - interval '1 hour');
  if (select transport from public.native_push_devices where token = repeat('d5', 32)) <> 'apns' then
    raise exception 'A device without a transport is not an APNs one';
  end if;
  insert into public.native_push_devices (user_id, token, environment, transport, subscribed_at)
  values (owner_id, fcm_token, 'production', 'fcm', now() - interval '1 hour');

  begin
    insert into public.native_push_devices (user_id, token, environment, transport)
    values (owner_id, 'synthetic-fcm:' || repeat('x', 40), 'development', 'fcm');
    raise exception 'An FCM device took the development environment' using errcode = 'P0001';
  exception when check_violation then null; end;
  begin
    insert into public.native_push_devices (user_id, token, environment, transport)
    values (owner_id, 'synthetic fcm token with spaces and more than enough length', 'production', 'fcm');
    raise exception 'An FCM device took a malformed token' using errcode = 'P0001';
  exception when check_violation then null; end;
  begin
    insert into public.native_push_devices (user_id, token, environment, transport)
    values (owner_id, 'synthetic-fcm:' || repeat('x', 2100), 'production', 'fcm');
    raise exception 'An FCM device took a token past the limit' using errcode = 'P0001';
  exception when check_violation then null; end;
  begin
    insert into public.native_push_devices (user_id, token, environment, transport)
    values (owner_id, 'synthetic-fcm:' || repeat('x', 40), 'production', 'apns');
    raise exception 'An APNs device took an FCM token' using errcode = 'P0001';
  exception when check_violation then null; end;
  begin
    insert into public.native_push_devices (user_id, token, environment, transport)
    values (owner_id, repeat('d6', 32), 'production', 'carrier-pigeon');
    raise exception 'A device took an unknown transport' using errcode = 'P0001';
  exception when check_violation then null; end;
end;
$$;

-- The account's queue says which service reaches each device.
do $$
declare
  owner_id constant uuid := 'd5000000-0000-4000-8000-000000000001';
  fcm_token constant text := 'synthetic-fcm:' || repeat('Ab0_-', 20);
  new_event uuid;
begin
  insert into public.packages (id, user_id, tracking_number, carrier, current_stage)
  values ('d6000000-0000-4000-8000-000000000001', owner_id, 'ANDROIDPUSH0001', 'unknown', 'in_transit');
  insert into public.tracking_events (package_id, provider_event_id, stage, description, occurred_at)
  values ('d6000000-0000-4000-8000-000000000001', 'test:android-news', 'in_transit', 'Synthetic scan', now())
  returning id into new_event;
  if (select array_agg(transport order by transport) from public.pending_native_push_notifications
      where event_id = new_event) is distinct from array['apns', 'fcm'] then
    raise exception 'The account queue lost a device or its transport: %', (
      select jsonb_agg(jsonb_build_object('token', token, 'transport', transport))
      from public.pending_native_push_notifications where event_id = new_event);
  end if;
  if (select environment from public.pending_native_push_notifications
      where event_id = new_event and token = fcm_token) <> 'production' then
    raise exception 'An FCM device is queued without its environment';
  end if;
end;
$$;

-- A link's alert on an Android phone: added, updated, queued with its token, turned off by it.
do $$
declare
  key_a constant text := repeat('e', 64);
  token_viewer constant text := 'synthetic-viewer:' || repeat('Zz9', 20);
  answer jsonb;
  target_link text;
  target_package uuid;
  pending record;
  new_event uuid;
  outcome text;
begin
  answer := public.create_one_off_parcel('ANDROIDPUSH0002', 'unknown', null, null, key_a);
  target_link := answer#>>'{link,id}';
  target_package := (answer#>>'{package,id}')::uuid;

  if public.add_parcel_link_app_alert(target_link, null, token_viewer, 'development', 'fr', 'important', 'fcm') <> 'added' then
    raise exception 'An Android phone could not turn an alert on';
  end if;
  if not exists (
    select 1 from public.parcel_link_alerts
    where fcm_token = token_viewer and apns_token is null and apns_environment is null
      and endpoint is null and locale = 'fr' and preset = 'important' and not owner
  ) then
    raise exception 'An Android phone''s alert was stored wrongly: %', (
      select jsonb_agg(to_jsonb(alert)) from public.parcel_link_alerts as alert where link_id = target_link);
  end if;

  update public.parcel_link_alerts set failures = 2 where fcm_token = token_viewer;
  outcome := public.add_parcel_link_app_alert(target_link, key_a, token_viewer, 'production', 'pl', 'delivery', 'fcm');
  if outcome <> 'updated'
      or (select count(*) from public.parcel_link_alerts where link_id = target_link) <> 1
      or not exists (
        select 1 from public.parcel_link_alerts
        where fcm_token = token_viewer and locale = 'pl' and preset = 'delivery' and failures = 0 and owner) then
    raise exception 'An Android phone''s second request did not update its alert: %', (
      select jsonb_agg(to_jsonb(alert)) from public.parcel_link_alerts as alert where link_id = target_link);
  end if;

  -- The same token sent as an APNs one is a different alert, and must be hex to be taken.
  begin
    perform public.add_parcel_link_app_alert(target_link, null, token_viewer, 'production', 'en', 'all', 'apns');
    raise exception 'An APNs alert took an FCM token' using errcode = 'P0001';
  exception when check_violation then null; end;
  begin
    perform public.add_parcel_link_app_alert(target_link, null, token_viewer, 'production', 'en', 'all', 'telegraph');
    raise exception 'An alert took an unknown transport' using errcode = 'P0001';
  exception when invalid_parameter_value then null; end;
  begin
    insert into public.parcel_link_alerts (link_id, apns_token, apns_environment, fcm_token, locale, preset)
    values (target_link, repeat('0c', 32), 'production', 'synthetic-both:' || repeat('q', 40), 'en', 'all');
    raise exception 'An alert held an iPhone and an Android phone' using errcode = 'P0001';
  exception when check_violation then null; end;
  begin
    insert into public.parcel_link_alerts (link_id, fcm_token, locale, preset)
    values (target_link, 'synthetic fcm token with spaces and more than enough length', 'en', 'all');
    raise exception 'An alert took a malformed FCM token' using errcode = 'P0001';
  exception when check_violation then null; end;

  update public.parcel_link_alerts set created_at = now() - interval '1 hour' where link_id = target_link;
  insert into public.tracking_events (package_id, provider_event_id, stage, description, occurred_at, raw_data)
  values (target_package, 'test:android-link', 'in_transit', 'Synthetic scan', now(),
    '{"time":"2026-11-04T10:15:00+01:00","tie_order":[1,2]}')
  returning id into new_event;
  select * into pending from public.pending_parcel_link_alerts where fcm_token = token_viewer;
  if pending.event_id is distinct from new_event or pending.apns_token is not null
      or pending.apns_environment is not null or pending.endpoint is not null or pending.account_endpoint
      or pending.event_time is distinct from '2026-11-04T10:15:00+01:00'
      or pending.tie_order is distinct from '[1,2]'::jsonb or pending.event_has_time is distinct from true then
    raise exception 'An Android phone''s alert is queued wrongly: %', to_jsonb(pending);
  end if;

  if public.remove_parcel_link_app_alert(target_link, 'synthetic-viewer:' || repeat('Zz8', 20))
      or not public.remove_parcel_link_app_alert(target_link, token_viewer) then
    raise exception 'An Android phone''s alert was turned off without its token, or not with it';
  end if;
  if exists (select 1 from public.parcel_link_alerts where fcm_token = token_viewer) then
    raise exception 'An Android phone''s alert was turned off without its token, or not with it';
  end if;
end;
$$;

-- An alert on an Android phone the parcel's account already notifies is marked, as an iPhone's is.
do $$
declare
  owner_id constant uuid := 'd5000000-0000-4000-8000-000000000001';
  fcm_token constant text := 'synthetic-fcm:' || repeat('Ab0_-', 20);
  target_link text;
  new_event uuid;
begin
  insert into public.parcel_links (package_id, created_by)
  values ('d6000000-0000-4000-8000-000000000001', owner_id) returning id into target_link;
  if public.add_parcel_link_app_alert(target_link, null, fcm_token, 'production', 'en', 'all', 'fcm') <> 'added' then
    raise exception 'An account''s shared link refused an Android phone''s alert';
  end if;
  update public.parcel_link_alerts set created_at = now() - interval '1 hour' where link_id = target_link;
  insert into public.tracking_events (package_id, provider_event_id, stage, description, occurred_at)
  values ('d6000000-0000-4000-8000-000000000001', 'test:android-account', 'in_transit', 'Synthetic scan', now())
  returning id into new_event;
  if (select account_endpoint from public.pending_parcel_link_alerts
      where event_id = new_event and link_id = target_link) is distinct from true then
    raise exception 'An alert on the owner''s own Android phone is not marked';
  end if;
  update public.native_push_devices set disabled_at = now() where token = fcm_token;
  if (select account_endpoint from public.pending_parcel_link_alerts
      where event_id = new_event and link_id = target_link) is distinct from false then
    raise exception 'A disabled Android phone of the account still marks the alert';
  end if;
  update public.native_push_devices set disabled_at = null where token = fcm_token;
end;
$$;

reset role;

-- A friendship's notice reaches an Android phone only through a server that claims FCM rows.
do $$
declare
  owner_id constant uuid := 'd5000000-0000-4000-8000-000000000001';
  friend_id constant uuid := 'd5000000-0000-4000-8000-000000000002';
  fcm_token constant text := 'synthetic-fcm:' || repeat('Ab0_-', 20);
  claimed jsonb;
begin
  delete from public.native_push_devices where user_id = owner_id and transport = 'apns';
  insert into public.friend_profiles (user_id, nickname) values (owner_id, 'Robin'), (friend_id, 'Sam');
  insert into public.friend_connections (user_low, user_high)
  values (least(owner_id, friend_id), greatest(owner_id, friend_id));
  insert into public.friendship_updates (recipient_id, friend_user_id, user_low, user_high)
  values (owner_id, friend_id, least(owner_id, friend_id), greatest(owner_id, friend_id));
  if (select count(*) from public.friendship_push_deliveries where device_id is not null) <> 1 then
    raise exception 'An Android phone was not queued for a friendship''s notice';
  end if;
  if exists (select 1 from public.claim_friendship_push(false, true)) then
    raise exception 'An APNs-only server claimed an Android phone''s notice';
  end if;
  select value into claimed from public.claim_friendship_push(false, false, 10, true) value;
  if claimed->>'token' is distinct from fcm_token or claimed->>'transport' is distinct from 'fcm'
      or claimed->>'environment' is distinct from 'production' or claimed->>'nickname' is distinct from 'Sam' then
    raise exception 'Wrong Android friendship notice: %', claimed;
  end if;
end;
$$;


-- The usage figures count phones of each kind.
do $$
begin
  if (select iphone_push_accounts from metrics.live) <> (
        select count(distinct user_id) from public.native_push_devices where transport = 'apns' and disabled_at is null)
      or (select android_push_accounts from metrics.live) <> (
        select count(distinct user_id) from public.native_push_devices where transport = 'fcm' and disabled_at is null)
      or (select android_push_accounts from metrics.live) < 1 then
    raise exception 'The usage figures miscount phones with notifications on';
  end if;
end;
$$;
rollback;
select 'Android push devices, link alerts, friendship notices and usage figures passed' as result;
