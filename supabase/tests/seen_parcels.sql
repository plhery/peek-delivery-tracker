-- A parcel's scans new since its owner last opened it (#72): every parcel starts as seen, only its
-- owner marks it as seen, and the mark's switch is kept by a save that does not name it.
\set ON_ERROR_STOP on
begin;

insert into auth.users (id, email, is_anonymous) values
  ('72000000-0000-4000-8000-000000000001', 'seen-alex@example.com', false),
  ('72000000-0000-4000-8000-000000000002', 'seen-sam@example.com', false);
insert into public.packages (id, user_id, tracking_number, carrier) values
  ('72000000-0000-4000-8000-000000000011', '72000000-0000-4000-8000-000000000001', 'SEENTEST0001', 'ups');

do $$
declare
  alex constant uuid := '72000000-0000-4000-8000-000000000001';
  sam constant uuid := '72000000-0000-4000-8000-000000000002';
  parcel constant uuid := '72000000-0000-4000-8000-000000000011';
  long_ago constant timestamptz := now() - interval '3 days';
  shown constant timestamptz := now() - interval '1 day' + interval '123456 microseconds';
  generation uuid;
  seen timestamptz;
begin
  if has_function_privilege('anon', 'public.mark_owned_package_seen(uuid, timestamptz)', 'EXECUTE')
      or not has_function_privilege('authenticated', 'public.mark_owned_package_seen(uuid, timestamptz)', 'EXECUTE')
      or has_function_privilege('authenticated', 'public.start_package_seen()', 'EXECUTE')
      or not has_column_privilege('authenticated', 'public.packages', 'seen_at', 'SELECT')
      or has_column_privilege('authenticated', 'public.packages', 'seen_at', 'UPDATE')
      or not has_column_privilege('authenticated', 'public.notification_preferences', 'mark_new_updates', 'SELECT') then
    raise exception 'Seen state has the wrong grants';
  end if;
  if to_regprocedure('public.set_owned_notification_preferences(text[], time, time, text, boolean)') is not null then
    raise exception 'The five-argument preferences function is still there';
  end if;

  -- A new parcel starts as seen, and so does what its first answer holds.
  select seen_at, tracking_generation into seen, generation from public.packages where id = parcel;
  if seen <> now() then raise exception 'A new parcel did not start as seen: %', seen; end if;
  update public.packages set seen_at = long_ago where id = parcel;
  if not public.apply_tracking_sync(parcel, generation,
    jsonb_build_object('sync_status', 'ok', 'last_synced_at', now()),
    '[{"provider_event_id":"ups:1","stage":"in_transit","description":"Departed","occurred_at":"2026-11-10T08:00:00Z"}]') then
    raise exception 'The first answer was not stored';
  end if;
  select seen_at into seen from public.packages where id = parcel;
  if seen <> now() or exists (
    select 1 from public.tracking_events where package_id = parcel and created_at > seen
  ) then
    raise exception 'The first answer is news: %', seen;
  end if;

  -- A later answer leaves it: its scan is new since the owner last opened the parcel.
  update public.packages set seen_at = long_ago where id = parcel;
  perform public.apply_tracking_sync(parcel, generation,
    jsonb_build_object('sync_status', 'ok', 'last_synced_at', now()),
    '[{"provider_event_id":"ups:2","stage":"out_for_delivery","description":"Out for delivery","occurred_at":"2026-11-11T08:00:00Z"}]');
  select seen_at into seen from public.packages where id = parcel;
  if seen <> long_ago or not exists (
    select 1 from public.tracking_events where package_id = parcel and created_at > seen
  ) then
    raise exception 'A later answer moved when the parcel was seen: %', seen;
  end if;

  -- Only its owner marks it as seen.
  set local role authenticated;
  perform set_config('request.jwt.claim.sub', sam::text, true);
  if public.mark_owned_package_seen(parcel) is not null then
    raise exception 'Another account marked the parcel as seen';
  end if;
  perform set_config('request.jwt.claim.sub', alex::text, true);
  if (select seen_at from public.packages where id = parcel) <> long_ago then
    raise exception 'Another account moved when the parcel was seen';
  end if;
  seen := public.mark_owned_package_seen(parcel);
  if seen is distinct from now() or (select seen_at from public.packages where id = parcel) <> now() then
    raise exception 'The owner did not mark the parcel as seen: %', seen;
  end if;
  perform set_config('request.jwt.claim.sub', '', true);
  begin
    perform public.mark_owned_package_seen(parcel);
    raise exception 'Nobody marked a parcel as seen' using errcode = 'P0001';
  exception when insufficient_privilege then null; end;
  reset role;

  -- Marked with the news its page showed, kept to the millisecond, the parcel is seen as of that news: a scan stored
  -- since is still new. Never earlier than it was seen already, nor later than now.
  update public.packages set seen_at = long_ago where id = parcel;
  set local role authenticated;
  perform set_config('request.jwt.claim.sub', alex::text, true);
  seen := public.mark_owned_package_seen(parcel, date_trunc('milliseconds', shown));
  if seen < shown or seen >= date_trunc('milliseconds', shown) + interval '1 millisecond' then
    raise exception 'The parcel was not seen as of the news its page showed: %', seen;
  end if;
  if public.mark_owned_package_seen(parcel, long_ago) <> seen then
    raise exception 'Older news moved the parcel back to unseen';
  end if;
  if public.mark_owned_package_seen(parcel, now() + interval '1 day') <> now() then
    raise exception 'News from the future was stored as seen';
  end if;
  reset role;

  -- A parcel starts as seen in the account it joins, and again with its new carrier's first answer.
  update public.packages set seen_at = long_ago where id = parcel;
  update public.packages set user_id = sam where id = parcel;
  if (select seen_at from public.packages where id = parcel) <> now() then
    raise exception 'A parcel that joined an account did not start as seen';
  end if;
  update public.packages set seen_at = long_ago, last_synced_at = null where id = parcel;
  update public.packages set last_synced_at = now() where id = parcel;
  if (select seen_at from public.packages where id = parcel) <> now() then
    raise exception 'A first answer after the inputs changed did not start the parcel as seen';
  end if;
end;
$$;

-- The mark's switch: on until switched off, and kept by a save that does not name it.
set local role authenticated;
do $$
declare
  saved public.notification_preferences;
begin
  perform set_config('request.jwt.claim.sub', '72000000-0000-4000-8000-000000000001', true);
  saved := public.set_owned_notification_preferences(array['delivered'], null, null, 'Europe/Zurich');
  if saved.mark_new_updates is not true then raise exception 'A first save switched the mark off: %', saved; end if;
  saved := public.set_owned_notification_preferences(array['delivered'], null, null, 'Europe/Zurich', null, false);
  if saved.mark_new_updates is not false then raise exception 'The mark was not switched off: %', saved; end if;
  saved := public.set_owned_notification_preferences(array['delivered'], null, null, 'Europe/Zurich', true);
  if saved.mark_new_updates is not false or saved.email_on_delivery is not true then
    raise exception 'A save that did not name the mark changed it: %', saved;
  end if;
  saved := public.set_owned_notification_preferences(array['delivered'], null, null, 'Europe/Zurich', p_mark_new_updates => true);
  if saved.mark_new_updates is not true or saved.email_on_delivery is not true then
    raise exception 'The mark was not switched back on: %', saved;
  end if;
  if (select mark_new_updates from public.notification_preferences) is not true then
    raise exception 'The account cannot read its own choice';
  end if;
end;
$$;

rollback;
