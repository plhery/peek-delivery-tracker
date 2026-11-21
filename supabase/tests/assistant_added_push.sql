-- "Added by <assistant>": a parcel an assistant adds starts unseen and is notified once to each browser and phone of
-- its account with the assistant's name, unless the account switched it off; one opened, archived, too old or whose
-- assistant was disconnected is dropped.
\set ON_ERROR_STOP on
begin;

insert into auth.users (id, email, is_anonymous) values
  ('42100000-0000-4000-8000-000000000001', 'assistant-added-alex@example.com', false),
  ('42100000-0000-4000-8000-000000000002', 'assistant-added-sam@example.com', false);
insert into private.assistant_grants (id, user_id, kind, client_id, name, scopes) values
  ('42100000-0000-4000-8000-000000000041', '42100000-0000-4000-8000-000000000001', 'oauth', 'peek_c_alex', 'Example Assistant', array['parcels:write']),
  ('42100000-0000-4000-8000-000000000042', '42100000-0000-4000-8000-000000000002', 'oauth', 'peek_c_sam', 'Other Assistant', array['parcels:write']);
insert into public.packages (id, user_id, tracking_number, carrier, label) values
  ('42100000-0000-4000-8000-000000000011', '42100000-0000-4000-8000-000000000001', 'ASSISTANTTEST1', 'ups', 'Example Shop'),
  ('42100000-0000-4000-8000-000000000012', '42100000-0000-4000-8000-000000000001', 'ASSISTANTTEST2', 'dhl', ''),
  ('42100000-0000-4000-8000-000000000013', '42100000-0000-4000-8000-000000000002', 'ASSISTANTTEST3', 'ups', '');
insert into public.push_subscriptions (id, user_id, endpoint, p256dh, auth, locale) values
  ('42100000-0000-4000-8000-000000000021', '42100000-0000-4000-8000-000000000001', 'https://fcm.googleapis.com/fcm/send/assistant-alex', 'public-key', 'auth-secret', 'fr'),
  ('42100000-0000-4000-8000-000000000022', '42100000-0000-4000-8000-000000000002', 'https://fcm.googleapis.com/fcm/send/assistant-sam', 'public-key', 'auth-secret', 'en');
insert into public.push_subscriptions (user_id, endpoint, p256dh, auth, disabled_at) values
  ('42100000-0000-4000-8000-000000000001', 'https://fcm.googleapis.com/fcm/send/assistant-alex-old', 'public-key', 'auth-secret', now());
insert into public.native_push_devices (id, user_id, token, environment, transport, locale) values
  ('42100000-0000-4000-8000-000000000031', '42100000-0000-4000-8000-000000000001', repeat('b6', 32), 'production', 'apns', 'de');

do $$
declare
  alex constant uuid := '42100000-0000-4000-8000-000000000001';
  sam constant uuid := '42100000-0000-4000-8000-000000000002';
  alexs_assistant constant uuid := '42100000-0000-4000-8000-000000000041';
  sams_assistant constant uuid := '42100000-0000-4000-8000-000000000042';
  named constant uuid := '42100000-0000-4000-8000-000000000011';
  unnamed constant uuid := '42100000-0000-4000-8000-000000000012';
  sams constant uuid := '42100000-0000-4000-8000-000000000013';
  epoch constant timestamptz := 'epoch'::timestamptz;
  claimed jsonb[];
  delivery jsonb;
  queued integer;
  fn text;
begin
  foreach fn in array array[
    'public.assistant_added_notify(uuid)', 'public.set_assistant_added_notify(uuid,boolean)',
    'public.parcels_added_by_assistant(uuid,uuid,uuid[])',
    'public.claim_assistant_added_push(boolean,boolean,integer,boolean,boolean,boolean)',
    'public.finish_assistant_added_push(uuid,uuid,boolean)'
  ] loop
    if has_function_privilege('anon', fn, 'EXECUTE') or has_function_privilege('authenticated', fn, 'EXECUTE')
        or not has_function_privilege('service_role', fn, 'EXECUTE') then
      raise exception '% has the wrong grants', fn;
    end if;
  end loop;
  if has_table_privilege('authenticated', 'public.assistant_added_push_deliveries', 'SELECT')
      or has_table_privilege('anon', 'public.assistant_added_push_deliveries', 'SELECT') then
    raise exception 'The deliveries are readable outside the service';
  end if;

  -- An account starts with the notification on; its choice is kept.
  if not public.assistant_added_notify(alex) then raise exception 'An account starts silent'; end if;
  if public.set_assistant_added_notify(alex, false) then raise exception 'The switch answered the wrong choice'; end if;
  if public.assistant_added_notify(alex) then raise exception 'The notification was not switched off'; end if;
  begin
    perform public.set_assistant_added_notify(alex, null);
    raise exception 'A null choice was taken' using errcode = 'P0001';
  exception when invalid_parameter_value then null; end;

  -- Switched off: the parcel starts unseen, and nothing is queued.
  queued := public.parcels_added_by_assistant(alex, alexs_assistant, array[named]);
  if queued <> 0 or exists (select 1 from public.assistant_added_push_deliveries)
      or (select seen_at from public.packages where id = named) <> epoch then
    raise exception 'A notification switched off was queued';
  end if;
  update public.packages set seen_at = now() where id = named;
  if not public.set_assistant_added_notify(alex, true) then raise exception 'The notification was not switched on'; end if;

  -- Another account's assistant adds nothing to this one.
  queued := public.parcels_added_by_assistant(alex, sams_assistant, array[named]);
  if queued <> 0 or (select seen_at from public.packages where id = named) = epoch then
    raise exception 'Another account''s assistant announced a parcel';
  end if;

  -- On: one notification per active browser and phone of the account, for its own parcels alone.
  queued := public.parcels_added_by_assistant(alex, alexs_assistant, array[named, unnamed, sams]);
  if queued <> 4 or (select count(*) from public.assistant_added_push_deliveries) <> 4
      or exists (select 1 from public.assistant_added_push_deliveries where package_id = sams or user_id <> alex) then
    raise exception 'The wrong notifications were queued: %', queued;
  end if;
  if (select seen_at from public.packages where id = named) <> epoch
      or (select seen_at from public.packages where id = sams) = epoch then
    raise exception 'The wrong parcels start unseen';
  end if;
  queued := public.parcels_added_by_assistant(alex, alexs_assistant, array[named]);
  if queued <> 0 or (select count(*) from public.assistant_added_push_deliveries) <> 4 then raise exception 'A parcel was queued twice'; end if;
  begin
    perform public.parcels_added_by_assistant(alex, alexs_assistant, array(select gen_random_uuid() from generate_series(1, 101)));
    raise exception 'Too many parcels were taken' using errcode = 'P0001';
  exception when invalid_parameter_value then null; end;

  -- A server claims only the transports it sends, with the assistant's and parcel's names, the carrier and the
  -- device's language.
  claimed := array(select public.claim_assistant_added_push(true, false));
  if cardinality(claimed) <> 2 or exists (select 1 from unnest(claimed) c where c->>'subscription_id' is null) then
    raise exception 'A browser server claimed the wrong notifications: %', claimed;
  end if;
  delivery := (select c from unnest(claimed) c where c->>'package_id' = named::text);
  if delivery->>'assistant' <> 'Example Assistant' or delivery->>'label' <> 'Example Shop' or delivery->>'carrier' <> 'ups'
      or delivery->>'locale' <> 'fr' or delivery->>'endpoint' <> 'https://fcm.googleapis.com/fcm/send/assistant-alex'
      or delivery->>'lease_token' is null then
    raise exception 'A claim lacks what the notification says: %', delivery;
  end if;
  if cardinality(array(select public.claim_assistant_added_push(true, false))) <> 0 then
    raise exception 'A leased notification was claimed twice';
  end if;
  perform public.finish_assistant_added_push((delivery->>'id')::uuid, (delivery->>'lease_token')::uuid, true);
  if (select sent_at from public.assistant_added_push_deliveries where id = (delivery->>'id')::uuid) is null then
    raise exception 'A sent notification was not finished';
  end if;
  -- A failed one waits for its retry.
  delivery := (select c from unnest(claimed) c where c->>'package_id' = unnamed::text);
  perform public.finish_assistant_added_push((delivery->>'id')::uuid, (delivery->>'lease_token')::uuid, false);
  if (select next_attempt_at <= now() or sent_at is not null from public.assistant_added_push_deliveries where id = (delivery->>'id')::uuid) then
    raise exception 'A failed notification was not put off';
  end if;
  update public.assistant_added_push_deliveries set next_attempt_at = now() where id = (delivery->>'id')::uuid;

  -- During quiet hours nothing goes; after them it does.
  insert into public.notification_preferences (user_id, quiet_hours_start, quiet_hours_end, timezone)
  values (alex, ((now() at time zone 'UTC') - interval '1 hour')::time, ((now() at time zone 'UTC') + interval '1 hour')::time, 'UTC');
  if cardinality(array(select public.claim_assistant_added_push(true, true))) <> 0 then
    raise exception 'A notification went out during quiet hours';
  end if;
  delete from public.notification_preferences where user_id = alex;

  perform public.set_assistant_added_notify(alex, false);
  if exists (select public.claim_assistant_added_push(true, true)) then
    raise exception 'A delayed push outlived its notification switch';
  end if;
  perform public.set_assistant_added_notify(alex, true);

  -- Opened or archived, a parcel is not announced any more.
  set local role authenticated;
  perform set_config('request.jwt.claim.sub', alex::text, true);
  perform public.mark_owned_package_seen(named);
  reset role;
  update public.packages set archived_at = now() where id = unnamed;
  claimed := array(select public.claim_assistant_added_push(true, true));
  if cardinality(claimed) <> 0 then raise exception 'An opened or archived parcel was announced: %', claimed; end if;
  update public.packages set archived_at = null where id = unnamed;
  claimed := array(select public.claim_assistant_added_push(true, true));
  if cardinality(claimed) <> 2 or exists (select 1 from unnest(claimed) c where c->>'package_id' <> unnamed::text) then
    raise exception 'An unopened parcel was not announced: %', claimed;
  end if;
  delivery := (select c from unnest(claimed) c where c->>'device_id' is not null);
  if delivery->>'token' <> repeat('b6', 32) or delivery->>'transport' <> 'apns' or delivery->>'locale' <> 'de' then
    raise exception 'A phone''s claim lacks its token: %', delivery;
  end if;

  -- One that waited more than twelve hours is dropped, and so is every one of a disconnected assistant.
  update public.assistant_added_push_deliveries set created_at = now() - interval '13 hours' where package_id = named;
  perform public.claim_assistant_added_push(true, true);
  if exists (select 1 from public.assistant_added_push_deliveries where package_id = named) then
    raise exception 'Old notifications were kept';
  end if;
  perform public.revoke_assistant_grant(alex, alexs_assistant);
  if exists (select 1 from public.assistant_added_push_deliveries) then
    raise exception 'A disconnected assistant''s notifications were kept';
  end if;
end $$;

rollback;
