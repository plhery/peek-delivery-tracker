\set ON_ERROR_STOP on
begin;
insert into auth.users (id, email, is_anonymous) values
 ('d7000000-0000-4000-8000-000000000001', 'unifiedpush-owner@example.test', false),
 ('d7000000-0000-4000-8000-000000000002', 'unifiedpush-friend@example.test', false);

-- The inbox and the functions of the Android app without Google's services stay the server's.
do $$
declare
  role_name text;
  service_function text;
begin
  if to_regprocedure('public.add_parcel_link_app_alert(text,text,text,text,text,text,text)') is not null
      or to_regprocedure('public.claim_friendship_push(boolean,boolean,integer,boolean)') is not null then
    raise exception 'An earlier signature was left beside the new one';
  end if;
  if not (select relrowsecurity from pg_class where oid = 'public.push_inbox'::regclass) then
    raise exception 'The inbox has no row level security';
  end if;
  foreach role_name in array array['anon', 'authenticated'] loop
    foreach service_function in array array[
      'public.add_parcel_link_app_alert(text,text,text,text,text,text,text,text,text,text)',
      'public.remove_parcel_link_app_alert(text,text)',
      'public.claim_friendship_push(boolean,boolean,integer,boolean,boolean,boolean)',
      'public.put_push_inbox(text,text,jsonb,uuid)',
      'public.take_push_inbox(text)',
      'public.forget_expired_push_inbox()'
    ] loop
      if has_function_privilege(role_name, service_function, 'EXECUTE') then
        raise exception '% may execute %', role_name, service_function;
      end if;
    end loop;
    if has_table_privilege(role_name, 'public.push_inbox', 'SELECT')
        or has_table_privilege(role_name, 'public.push_inbox', 'INSERT')
        or has_table_privilege(role_name, 'public.push_inbox', 'DELETE')
        or has_sequence_privilege(role_name, 'public.push_inbox_id_seq', 'USAGE') then
      raise exception '% may reach the inbox', role_name;
    end if;
  end loop;
end;
$$;

set local role service_role;

-- A device of the Android app without Google's services is named by its installation token; a
-- UnifiedPush one carries its endpoint and keys, and nothing else does.
do $$
declare
  owner_id constant uuid := 'd7000000-0000-4000-8000-000000000001';
  install constant text := repeat('Up0_-', 8) || 'abc';
  up_endpoint constant text := 'https://push.example.test/up/synthetic?token=1';
  up_p256dh constant text := repeat('B', 87);
  up_auth constant text := repeat('A', 22);
  device_id uuid;
begin
  insert into public.native_push_devices (user_id, token, environment, transport, endpoint, p256dh, auth, subscribed_at)
  values (owner_id, install, 'production', 'unifiedpush', up_endpoint, up_p256dh, up_auth, now() - interval '1 hour')
  returning id into device_id;

  -- Switching to the inbox keeps the row, as the server's upsert does.
  insert into public.native_push_devices (user_id, token, environment, transport, endpoint, p256dh, auth, locale)
  values (owner_id, install, 'production', 'inbox', null, null, null, 'fr')
  on conflict (environment, token) do update
  set transport = excluded.transport, endpoint = excluded.endpoint, p256dh = excluded.p256dh,
    auth = excluded.auth, locale = excluded.locale;
  if (select count(*) from public.native_push_devices where token = install) <> 1
      or (select id from public.native_push_devices where token = install) <> device_id
      or (select transport from public.native_push_devices where token = install) <> 'inbox' then
    raise exception 'Switching to the inbox did not keep the device''s row';
  end if;
  insert into public.native_push_devices (user_id, token, environment, transport, endpoint, p256dh, auth)
  values (owner_id, install, 'production', 'unifiedpush', up_endpoint, up_p256dh, up_auth)
  on conflict (environment, token) do update
  set transport = excluded.transport, endpoint = excluded.endpoint, p256dh = excluded.p256dh, auth = excluded.auth;

  begin
    insert into public.native_push_devices (user_id, token, environment, transport)
    values (owner_id, repeat('Zz9', 14) || 'x', 'production', 'unifiedpush');
    raise exception 'A UnifiedPush device took no endpoint' using errcode = 'P0001';
  exception when check_violation then null; end;
  begin
    insert into public.native_push_devices (user_id, token, environment, transport, endpoint, p256dh, auth)
    values (owner_id, repeat('Zz9', 14) || 'x', 'production', 'inbox', up_endpoint, up_p256dh, up_auth);
    raise exception 'An inbox device took an endpoint' using errcode = 'P0001';
  exception when check_violation then null; end;
  begin
    insert into public.native_push_devices (user_id, token, environment, transport, endpoint, p256dh, auth)
    values (owner_id, 'synthetic-fcm:' || repeat('x', 40), 'production', 'fcm', up_endpoint, up_p256dh, up_auth);
    raise exception 'An FCM device took an endpoint' using errcode = 'P0001';
  exception when check_violation then null; end;
  begin
    insert into public.native_push_devices (user_id, token, environment, transport)
    values (owner_id, repeat('Zz9', 14) || 'x', 'development', 'inbox');
    raise exception 'An inbox device took the development environment' using errcode = 'P0001';
  exception when check_violation then null; end;
  begin
    insert into public.native_push_devices (user_id, token, environment, transport)
    values (owner_id, repeat('Zz9', 15), 'production', 'inbox');
    raise exception 'An inbox device took a token that is no installation token' using errcode = 'P0001';
  exception when check_violation then null; end;
  begin
    insert into public.native_push_devices (user_id, token, environment, transport, endpoint, p256dh, auth)
    values (owner_id, repeat('Zz9', 14) || 'x', 'production', 'unifiedpush', 'https://user:secret@push.example.test/up', up_p256dh, up_auth);
    raise exception 'A UnifiedPush device took an endpoint with credentials' using errcode = 'P0001';
  exception when check_violation then null; end;
  begin
    insert into public.native_push_devices (user_id, token, environment, transport, endpoint, p256dh, auth)
    values (owner_id, repeat('Zz9', 14) || 'x', 'production', 'unifiedpush', 'http://push.example.test/up', up_p256dh, up_auth);
    raise exception 'A UnifiedPush device took a plain HTTP endpoint' using errcode = 'P0001';
  exception when check_violation then null; end;
  begin
    insert into public.native_push_devices (user_id, token, environment, transport, endpoint, p256dh, auth)
    values (owner_id, repeat('Zz9', 14) || 'x', 'production', 'unifiedpush', up_endpoint, repeat('B', 86), up_auth);
    raise exception 'A UnifiedPush device took a malformed key' using errcode = 'P0001';
  exception when check_violation then null; end;
end;
$$;

-- The account's queue carries a UnifiedPush device's endpoint and keys, and none for the others.
do $$
declare
  owner_id constant uuid := 'd7000000-0000-4000-8000-000000000001';
  install constant text := repeat('Up0_-', 8) || 'abc';
  queued record;
  new_event uuid;
begin
  insert into public.native_push_devices (user_id, token, environment, subscribed_at)
  values (owner_id, repeat('d7', 32), 'production', now() - interval '1 hour');
  insert into public.packages (id, user_id, tracking_number, carrier, current_stage)
  values ('d8000000-0000-4000-8000-000000000001', owner_id, 'UNIFIEDPUSH0001', 'unknown', 'in_transit');
  insert into public.tracking_events (package_id, provider_event_id, stage, description, occurred_at)
  values ('d8000000-0000-4000-8000-000000000001', 'test:unifiedpush-news', 'in_transit', 'Synthetic scan', now())
  returning id into new_event;
  select * into queued from public.pending_native_push_notifications where event_id = new_event and token = install;
  if queued.transport is distinct from 'unifiedpush' or queued.environment is distinct from 'production'
      or queued.endpoint is distinct from 'https://push.example.test/up/synthetic?token=1'
      or queued.p256dh is distinct from repeat('B', 87) or queued.auth is distinct from repeat('A', 22) then
    raise exception 'A UnifiedPush device is queued wrongly: %', to_jsonb(queued);
  end if;
  if exists (select 1 from public.pending_native_push_notifications
      where event_id = new_event and transport = 'apns' and (endpoint is not null or p256dh is not null)) then
    raise exception 'An iPhone is queued with an endpoint';
  end if;
end;
$$;

-- A link's alert through UnifiedPush or the inbox: added, switched in place, refused when its
-- endpoint and transport disagree, queued with its token, marked on the owner's own phone, turned off.
do $$
declare
  owner_id constant uuid := 'd7000000-0000-4000-8000-000000000001';
  install constant text := repeat('Up0_-', 8) || 'abc';
  viewer constant text := repeat('Vw1_-', 8) || 'xyz';
  viewer_endpoint constant text := 'https://push.example.test/up/viewer';
  key_a constant text := repeat('f', 64);
  answer jsonb;
  target_link text;
  owner_link text;
  target_package uuid;
  pending record;
  new_event uuid;
  outcome text;
  removed boolean;
begin
  answer := public.create_one_off_parcel('UNIFIEDPUSH0002', 'unknown', null, null, key_a);
  target_link := answer#>>'{link,id}';
  target_package := (answer#>>'{package,id}')::uuid;

  if public.add_parcel_link_app_alert(target_link, null, viewer, 'production', 'de', 'important', 'unifiedpush',
      viewer_endpoint, repeat('C', 87), repeat('D', 22)) <> 'added' then
    raise exception 'A UnifiedPush phone could not turn an alert on';
  end if;
  if not exists (
    select 1 from public.parcel_link_alerts
    where app_token = viewer and app_transport = 'unifiedpush' and app_endpoint = viewer_endpoint
      and app_p256dh = repeat('C', 87) and app_auth = repeat('D', 22)
      and endpoint is null and apns_token is null and fcm_token is null and locale = 'de' and not owner
  ) then
    raise exception 'A UnifiedPush alert was stored wrongly: %', (
      select jsonb_agg(to_jsonb(alert)) from public.parcel_link_alerts as alert where link_id = target_link);
  end if;

  update public.parcel_link_alerts set failures = 2 where app_token = viewer;
  outcome := public.add_parcel_link_app_alert(target_link, key_a, viewer, 'production', 'pl', 'all', 'inbox');
  if outcome <> 'updated'
      or (select count(*) from public.parcel_link_alerts where link_id = target_link) <> 1
      or not exists (
        select 1 from public.parcel_link_alerts
        where app_token = viewer and app_transport = 'inbox' and app_endpoint is null and app_p256dh is null
          and app_auth is null and locale = 'pl' and failures = 0 and owner) then
    raise exception 'Switching to the inbox did not update the alert in place: %', (
      select jsonb_agg(to_jsonb(alert)) from public.parcel_link_alerts as alert where link_id = target_link);
  end if;

  begin
    perform public.add_parcel_link_app_alert(target_link, null, viewer, 'production', 'en', 'all', 'unifiedpush');
    raise exception 'A UnifiedPush alert took no endpoint' using errcode = 'P0001';
  exception when invalid_parameter_value then null; end;
  begin
    perform public.add_parcel_link_app_alert(target_link, null, viewer, 'production', 'en', 'all', 'inbox',
      viewer_endpoint, repeat('C', 87), repeat('D', 22));
    raise exception 'An inbox alert took an endpoint' using errcode = 'P0001';
  exception when invalid_parameter_value then null; end;
  begin
    perform public.add_parcel_link_app_alert(target_link, null, repeat('0d', 32), 'production', 'en', 'all', 'apns',
      viewer_endpoint, repeat('C', 87), repeat('D', 22));
    raise exception 'An APNs alert took an endpoint' using errcode = 'P0001';
  exception when invalid_parameter_value then null; end;
  begin
    perform public.add_parcel_link_app_alert(target_link, null, 'short', 'production', 'en', 'all', 'inbox');
    raise exception 'An inbox alert took a token that is no installation token' using errcode = 'P0001';
  exception when check_violation then null; end;
  begin
    insert into public.parcel_link_alerts (link_id, fcm_token, app_transport, app_token, locale, preset)
    values (target_link, 'synthetic-both:' || repeat('q', 40), 'inbox', repeat('Qq2', 14) || 'q', 'en', 'all');
    raise exception 'An alert held an FCM token and an installation token' using errcode = 'P0001';
  exception when check_violation then null; end;

  update public.parcel_link_alerts set created_at = now() - interval '1 hour' where link_id = target_link;
  insert into public.tracking_events (package_id, provider_event_id, stage, description, occurred_at)
  values (target_package, 'test:unifiedpush-link', 'in_transit', 'Synthetic scan', now())
  returning id into new_event;
  select * into pending from public.pending_parcel_link_alerts where app_token = viewer;
  if pending.event_id is distinct from new_event or pending.app_transport is distinct from 'inbox'
      or pending.endpoint is not null or pending.apns_token is not null or pending.fcm_token is not null
      or pending.account_endpoint or pending.return_held is distinct from false then
    raise exception 'An inbox alert is queued wrongly: %', to_jsonb(pending);
  end if;

  perform public.put_push_inbox(viewer, 'link-' || target_link, jsonb_build_object('title', 'Moving', 'link_id', target_link));
  perform public.put_push_inbox(viewer, 'other', jsonb_build_object('title', 'Another link', 'link_id', 'another'));
  if public.remove_parcel_link_app_alert(target_link, repeat('Nn3_-', 8) || 'nop') then
    raise exception 'An installation token''s alert was turned off without its token';
  end if;
  removed := public.remove_parcel_link_app_alert(target_link, viewer);
  if not removed or exists (select 1 from public.parcel_link_alerts where app_token = viewer) then
    raise exception 'An installation token''s alert was turned off without its token, or not with it';
  end if;
  if (select array_agg(data ->> 'link_id') from public.push_inbox where token = viewer) is distinct from array['another'] then
    raise exception 'Turning an alert off did not take its waiting notices along, or took another link''s';
  end if;

  -- On the owner's own phone, which gets the account's notifications, the alert is marked.
  insert into public.parcel_links (package_id, created_by)
  values ('d8000000-0000-4000-8000-000000000001', owner_id) returning id into owner_link;
  if public.add_parcel_link_app_alert(owner_link, null, install, 'production', 'en', 'all', 'inbox') <> 'added' then
    raise exception 'An account''s shared link refused an inbox alert';
  end if;
  update public.parcel_link_alerts set created_at = now() - interval '1 hour' where link_id = owner_link;
  insert into public.tracking_events (package_id, provider_event_id, stage, description, occurred_at)
  values ('d8000000-0000-4000-8000-000000000001', 'test:unifiedpush-account', 'in_transit', 'Synthetic scan', now())
  returning id into new_event;
  if (select account_endpoint from public.pending_parcel_link_alerts
      where event_id = new_event and link_id = owner_link) is distinct from true then
    raise exception 'An alert on the owner''s own UnifiedPush phone is not marked';
  end if;
  update public.native_push_devices set disabled_at = now() where token = install;
  if (select account_endpoint from public.pending_parcel_link_alerts
      where event_id = new_event and link_id = owner_link) is distinct from false then
    raise exception 'A disabled phone of the account still marks the alert';
  end if;
  update public.native_push_devices set disabled_at = null where token = install;
end;
$$;

-- The inbox: a newer notice replaces the one for the same thing, a fetch hands them over oldest
-- first and once, expired ones go unread, a phone that never polls keeps fifty, and a device's
-- sign-out takes its notices along.
do $$
declare
  owner_id constant uuid := 'd7000000-0000-4000-8000-000000000001';
  phone constant text := repeat('Ib4_-', 8) || 'inb';
  device uuid;
  taken jsonb;
begin
  insert into public.native_push_devices (user_id, token, environment, transport)
  values (owner_id, phone, 'production', 'inbox') returning id into device;
  perform public.put_push_inbox(phone, 'parcel-a', '{"title":"First","parcel_id":"a"}', device);
  perform public.put_push_inbox(phone, '', '{"title":"Test"}', device);
  perform public.put_push_inbox(phone, 'parcel-b', '{"title":"Other","parcel_id":"b"}', device);
  perform public.put_push_inbox(phone, 'parcel-a', '{"title":"Newer","parcel_id":"a"}', device);
  update public.push_inbox set created_at = now() - interval '3 minutes' where token = phone and collapse_key is null;
  update public.push_inbox set created_at = now() - interval '2 minutes' where token = phone and collapse_key = 'parcel-b';
  update public.push_inbox set created_at = now() - interval '1 minute' where token = phone and collapse_key = 'parcel-a';
  perform public.put_push_inbox(phone, 'parcel-c', '{"title":"Gone","parcel_id":"c"}', device);
  update public.push_inbox set expires_at = now() - interval '1 second' where token = phone and collapse_key = 'parcel-c';

  taken := public.take_push_inbox(phone);
  if taken is distinct from '[{"title":"Test"},{"title":"Other","parcel_id":"b"},{"title":"Newer","parcel_id":"a"}]'::jsonb then
    raise exception 'The inbox handed over %', taken;
  end if;
  if public.take_push_inbox(phone) <> '[]'::jsonb or exists (select 1 from public.push_inbox where token = phone) then
    raise exception 'The inbox handed its notices over twice';
  end if;

  begin
    perform public.put_push_inbox(phone, 'parcel-a', '{"title":"Bad","badge":1}', device);
    raise exception 'The inbox took a value that is not a string' using errcode = 'P0001';
  exception when check_violation then null; end;
  begin
    perform public.put_push_inbox(phone, 'parcel-a', jsonb_build_object('body', repeat('x', 9000)), device);
    raise exception 'The inbox took a notice past its size' using errcode = 'P0001';
  exception when check_violation then null; end;
  begin
    perform public.put_push_inbox('not-a-token', 'parcel-a', '{"title":"Bad"}');
    raise exception 'The inbox took a token that is no installation token' using errcode = 'P0001';
  exception when check_violation then null; end;

  for number in 1..55 loop
    perform public.put_push_inbox(phone, 'parcel-' || number, jsonb_build_object('n', number::text), device);
  end loop;
  if (select count(*) from public.push_inbox where token = phone) <> 50
      or exists (select 1 from public.push_inbox where token = phone and collapse_key = 'parcel-1') then
    raise exception 'A phone that never polls kept % notices', (select count(*) from public.push_inbox where token = phone);
  end if;

  update public.push_inbox set expires_at = now() - interval '1 second' where token = phone and collapse_key = 'parcel-10';
  if public.forget_expired_push_inbox() <> 1 then
    raise exception 'The sweep did not forget the expired notice alone';
  end if;
  delete from public.native_push_devices where id = device;
  if exists (select 1 from public.push_inbox where token = phone) then
    raise exception 'A device''s sign-out left its notices in the inbox';
  end if;
end;
$$;

reset role;

-- A friendship's notice reaches a UnifiedPush or inbox phone only through a server that claims them,
-- with the UnifiedPush endpoint and keys.
do $$
declare
  owner_id constant uuid := 'd7000000-0000-4000-8000-000000000001';
  friend_id constant uuid := 'd7000000-0000-4000-8000-000000000002';
  install constant text := repeat('Up0_-', 8) || 'abc';
  claimed jsonb;
begin
  delete from public.native_push_devices where user_id = owner_id and transport <> 'unifiedpush';
  insert into public.friend_profiles (user_id, nickname) values (owner_id, 'Robin'), (friend_id, 'Sam');
  insert into public.friend_connections (user_low, user_high)
  values (least(owner_id, friend_id), greatest(owner_id, friend_id));
  insert into public.friendship_updates (recipient_id, friend_user_id, user_low, user_high)
  values (owner_id, friend_id, least(owner_id, friend_id), greatest(owner_id, friend_id));
  if exists (select 1 from public.claim_friendship_push(true, true, 10, true))
      or exists (select 1 from public.claim_friendship_push(false, false, 10, false, false, true)) then
    raise exception 'A server claimed a UnifiedPush phone''s notice without asking for UnifiedPush';
  end if;
  select value into claimed from public.claim_friendship_push(false, false, 10, false, true) value;
  if claimed->>'token' is distinct from install or claimed->>'transport' is distinct from 'unifiedpush'
      or claimed->>'endpoint' is distinct from 'https://push.example.test/up/synthetic?token=1'
      or claimed->>'p256dh' is distinct from repeat('B', 87) or claimed->>'auth' is distinct from repeat('A', 22)
      or claimed->>'nickname' is distinct from 'Sam' then
    raise exception 'Wrong UnifiedPush friendship notice: %', claimed;
  end if;
end;
$$;

-- The usage figures count the Android app's phones whichever way their notifications come.
do $$
begin
  if (select android_push_accounts from metrics.live) <> (
        select count(distinct user_id) from public.native_push_devices
        where transport in ('fcm', 'unifiedpush', 'inbox') and disabled_at is null)
      or (select android_push_accounts from metrics.live) < 1 then
    raise exception 'The usage figures miscount Android phones with notifications on';
  end if;
end;
$$;
rollback;
select 'UnifiedPush and inbox devices, link alerts, inbox, friendship notices and usage figures passed' as result;
