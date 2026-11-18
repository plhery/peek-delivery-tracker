-- "Added from your email" (#405): a parcel an email adds starts unseen and is notified once to each browser and
-- phone of its account, unless the account switched it off; one opened, archived or too old is dropped.
\set ON_ERROR_STOP on
begin;

insert into auth.users (id, email, is_anonymous) values
  ('40500000-0000-4000-8000-000000000001', 'email-added-alex@example.com', false),
  ('40500000-0000-4000-8000-000000000002', 'email-added-sam@example.com', false);
insert into public.packages (id, user_id, tracking_number, carrier, label) values
  ('40500000-0000-4000-8000-000000000011', '40500000-0000-4000-8000-000000000001', 'ADDEDTEST0001', 'ups', 'Example Shop'),
  ('40500000-0000-4000-8000-000000000012', '40500000-0000-4000-8000-000000000001', 'ADDEDTEST0002', 'dhl', ''),
  ('40500000-0000-4000-8000-000000000013', '40500000-0000-4000-8000-000000000002', 'ADDEDTEST0003', 'ups', '');
insert into public.push_subscriptions (id, user_id, endpoint, p256dh, auth, locale) values
  ('40500000-0000-4000-8000-000000000021', '40500000-0000-4000-8000-000000000001', 'https://fcm.googleapis.com/fcm/send/added-alex', 'public-key', 'auth-secret', 'fr'),
  ('40500000-0000-4000-8000-000000000022', '40500000-0000-4000-8000-000000000002', 'https://fcm.googleapis.com/fcm/send/added-sam', 'public-key', 'auth-secret', 'en');
insert into public.push_subscriptions (user_id, endpoint, p256dh, auth, disabled_at) values
  ('40500000-0000-4000-8000-000000000001', 'https://fcm.googleapis.com/fcm/send/added-alex-old', 'public-key', 'auth-secret', now());
insert into public.native_push_devices (id, user_id, token, environment, transport, locale) values
  ('40500000-0000-4000-8000-000000000031', '40500000-0000-4000-8000-000000000001', repeat('a5', 32), 'production', 'apns', 'de');

do $$
declare
  alex constant uuid := '40500000-0000-4000-8000-000000000001';
  sam constant uuid := '40500000-0000-4000-8000-000000000002';
  named constant uuid := '40500000-0000-4000-8000-000000000011';
  unnamed constant uuid := '40500000-0000-4000-8000-000000000012';
  sams constant uuid := '40500000-0000-4000-8000-000000000013';
  epoch constant timestamptz := 'epoch'::timestamptz;
  mailbox jsonb;
  claimed jsonb[];
  delivery jsonb;
  generation uuid;
  queued integer;
  fn text;
begin
  foreach fn in array array[
    'public.set_inbound_mailbox_notify(uuid,boolean)', 'public.parcels_added_by_email(uuid,uuid[])',
    'public.claim_email_added_push(boolean,boolean,integer,boolean,boolean,boolean)',
    'public.finish_email_added_push(uuid,uuid,boolean)'
  ] loop
    if has_function_privilege('anon', fn, 'EXECUTE') or has_function_privilege('authenticated', fn, 'EXECUTE')
        or not has_function_privilege('service_role', fn, 'EXECUTE') then
      raise exception '% has the wrong grants', fn;
    end if;
  end loop;
  if has_table_privilege('authenticated', 'public.email_added_push_deliveries', 'SELECT')
      or has_table_privilege('anon', 'public.email_added_push_deliveries', 'SELECT') then
    raise exception 'The deliveries are readable outside the service';
  end if;

  -- Without an address there is nothing to switch, and nothing is notified; the parcels still start unseen.
  if public.set_inbound_mailbox_notify(alex, false) is not null then
    raise exception 'A choice was saved without an address';
  end if;
  queued := public.parcels_added_by_email(alex, array[named]);
  if queued <> 0 or (select seen_at from public.packages where id = named) <> epoch then
    raise exception 'An account without an address was notified, or its parcel did not start unseen';
  end if;
  update public.packages set seen_at = now() where id = named;

  -- An address starts with the notification on, and its renewal keeps the choice.
  mailbox := public.open_inbound_mailbox(alex, 'ffffffffffffffff');
  if (mailbox->>'notify_added')::boolean is not true then raise exception 'A new address starts silent: %', mailbox; end if;
  mailbox := public.set_inbound_mailbox_notify(alex, false);
  if (mailbox->>'notify_added')::boolean or mailbox->>'token' <> 'ffffffffffffffff'
      or (public.inbound_mailbox(alex)->>'notify_added')::boolean then
    raise exception 'The notification was not switched off: %', mailbox;
  end if;
  mailbox := public.open_inbound_mailbox(alex, 'gggggggggggggggg');
  if (mailbox->>'notify_added')::boolean then
    raise exception 'A renewed address switched the notification back on: %', mailbox;
  end if;
  begin
    perform public.set_inbound_mailbox_notify(alex, null);
    raise exception 'A null choice was taken' using errcode = 'P0001';
  exception when invalid_parameter_value then null; end;

  -- Switched off: the parcel starts unseen, and nothing is queued.
  queued := public.parcels_added_by_email(alex, array[named]);
  if queued <> 0 or exists (select 1 from public.email_added_push_deliveries)
      or (select seen_at from public.packages where id = named) <> epoch then
    raise exception 'A notification switched off was queued';
  end if;
  update public.packages set seen_at = now() where id = named;
  perform public.set_inbound_mailbox_notify(alex, true);

  -- On: one notification per active browser and phone of the account, for its own parcels alone.
  queued := public.parcels_added_by_email(alex, array[named, unnamed, sams]);
  if queued <> 4 or (select count(*) from public.email_added_push_deliveries) <> 4
      or exists (select 1 from public.email_added_push_deliveries where package_id = sams or user_id <> alex) then
    raise exception 'The wrong notifications were queued: %', queued;
  end if;
  if (select seen_at from public.packages where id = named) <> epoch
      or (select seen_at from public.packages where id = sams) = epoch then
    raise exception 'The wrong parcels start unseen';
  end if;
  -- Asked again for the same parcels, nothing is queued twice.
  queued := public.parcels_added_by_email(alex, array[named]);
  if queued <> 0 or (select count(*) from public.email_added_push_deliveries) <> 4 then raise exception 'A parcel was queued twice'; end if;
  begin
    perform public.parcels_added_by_email(alex, array(select gen_random_uuid() from generate_series(1, 101)));
    raise exception 'Too many parcels were taken' using errcode = 'P0001';
  exception when invalid_parameter_value then null; end;

  -- Its first answer keeps it unseen: the scan it brings is new to the owner.
  select tracking_generation into generation from public.packages where id = named;
  if not public.apply_tracking_sync(named, generation,
    jsonb_build_object('sync_status', 'ok', 'last_synced_at', now()),
    '[{"provider_event_id":"ups:added","stage":"in_transit","description":"Departed","occurred_at":"2026-11-10T08:00:00Z"}]') then
    raise exception 'The first answer was not stored';
  end if;
  if (select seen_at from public.packages where id = named) <> epoch then
    raise exception 'The first answer marked a parcel an email added as seen';
  end if;

  -- A server claims only the transports it sends, with the parcel's name and carrier and the device's language.
  claimed := array(select public.claim_email_added_push(true, false));
  if cardinality(claimed) <> 2 or exists (select 1 from unnest(claimed) c where c->>'subscription_id' is null) then
    raise exception 'A browser server claimed the wrong notifications: %', claimed;
  end if;
  delivery := (select c from unnest(claimed) c where c->>'package_id' = named::text);
  if delivery->>'label' <> 'Example Shop' or delivery->>'carrier' <> 'ups' or delivery->>'locale' <> 'fr'
      or delivery->>'endpoint' <> 'https://fcm.googleapis.com/fcm/send/added-alex' or delivery->>'lease_token' is null then
    raise exception 'A claim lacks what the notification says: %', delivery;
  end if;
  if cardinality(array(select public.claim_email_added_push(true, false))) <> 0 then
    raise exception 'A leased notification was claimed twice';
  end if;
  perform public.finish_email_added_push((delivery->>'id')::uuid, (delivery->>'lease_token')::uuid, true);
  if (select sent_at from public.email_added_push_deliveries where id = (delivery->>'id')::uuid) is null then
    raise exception 'A sent notification was not finished';
  end if;
  -- A failed one waits for its retry.
  delivery := (select c from unnest(claimed) c where c->>'package_id' = unnamed::text);
  perform public.finish_email_added_push((delivery->>'id')::uuid, (delivery->>'lease_token')::uuid, false);
  if (select next_attempt_at <= now() or sent_at is not null from public.email_added_push_deliveries where id = (delivery->>'id')::uuid) then
    raise exception 'A failed notification was not put off';
  end if;
  update public.email_added_push_deliveries set next_attempt_at = now() where id = (delivery->>'id')::uuid;

  -- During quiet hours nothing goes; after them it does.
  insert into public.notification_preferences (user_id, quiet_hours_start, quiet_hours_end, timezone)
  values (alex, ((now() at time zone 'UTC') - interval '1 hour')::time, ((now() at time zone 'UTC') + interval '1 hour')::time, 'UTC');
  if cardinality(array(select public.claim_email_added_push(true, true))) <> 0 then
    raise exception 'A notification went out during quiet hours';
  end if;
  delete from public.notification_preferences where user_id = alex;

  perform public.set_inbound_mailbox_notify(alex, false);
  if exists (select public.claim_email_added_push(true, true)) then
    raise exception 'A delayed push outlived its notification switch';
  end if;
  perform public.set_inbound_mailbox_notify(alex, true);

  -- Opened or archived, a parcel is not announced any more.
  set local role authenticated;
  perform set_config('request.jwt.claim.sub', alex::text, true);
  perform public.mark_owned_package_seen(named);
  reset role;
  if (select seen_at from public.packages where id = named) = epoch then raise exception 'Opening a parcel left it unseen'; end if;
  update public.packages set archived_at = now() where id = unnamed;
  claimed := array(select public.claim_email_added_push(true, true));
  if cardinality(claimed) <> 0 then raise exception 'An opened or archived parcel was announced: %', claimed; end if;
  update public.packages set archived_at = null where id = unnamed;
  claimed := array(select public.claim_email_added_push(true, true));
  if cardinality(claimed) <> 2 or exists (select 1 from unnest(claimed) c where c->>'package_id' <> unnamed::text) then
    raise exception 'An unopened parcel was not announced: %', claimed;
  end if;
  delivery := (select c from unnest(claimed) c where c->>'device_id' is not null);
  if delivery->>'token' <> repeat('a5', 32) or delivery->>'transport' <> 'apns' or delivery->>'locale' <> 'de' then
    raise exception 'A phone''s claim lacks its token: %', delivery;
  end if;

  -- A parcel that joins another account starts as seen there.
  update public.packages set user_id = sam where id = unnamed;
  if (select seen_at from public.packages where id = unnamed) <> now() then
    raise exception 'A parcel kept its unseen mark in another account';
  end if;

  -- One that waited more than twelve hours is dropped.
  update public.email_added_push_deliveries set created_at = now() - interval '13 hours';
  perform public.claim_email_added_push(true, true);
  if exists (select 1 from public.email_added_push_deliveries) then raise exception 'Old notifications were kept'; end if;
end $$;

rollback;
