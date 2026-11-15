\set ON_ERROR_STOP on
begin;

-- Grafana reads the providers' request counts and cannot write or record them.
do $$
begin
  if not has_table_privilege('metrics_reader', 'metrics.provider_requests_daily', 'SELECT') then
    raise exception 'Grafana cannot read the provider requests';
  end if;
  if has_table_privilege('metrics_reader', 'metrics.provider_requests_daily', 'INSERT')
      or has_function_privilege('metrics_reader', 'metrics.record_provider_requests(date)', 'EXECUTE')
      or has_table_privilege('anon', 'metrics.provider_requests_daily', 'SELECT')
      or has_table_privilege('authenticated', 'metrics.provider_requests_daily', 'SELECT') then
    raise exception 'The provider requests are reachable beyond Grafana''s counts';
  end if;
end;
$$;

create temp table requests_before on commit drop as
select * from metrics.provider_requests_daily where false;
create temp table goals_before on commit drop as
select * from metrics.goals_daily where false;
do $$
begin
  perform metrics.record_provider_requests((now() at time zone 'UTC')::date);
  perform metrics.record_goals((now() at time zone 'UTC')::date);
  insert into requests_before select * from metrics.provider_requests_daily where day = (now() at time zone 'UTC')::date;
  insert into goals_before select * from metrics.goals_daily where day = (now() at time zone 'UTC')::date;
end;
$$;

-- unseen: a number no source knows. found: one ParcelsApp finds at its first check. label: La
-- Poste shows its label alone and 17TRACK is asked beside it. beside: La Poste answers a moving
-- parcel and 17TRACK is asked beside it. failed: La Poste's adapter fails. resting: La Poste is
-- not asked, then asks for a postcode. foreign: a Z-Leading parcel, a carrier without an
-- adapter. fedex: FedEx fails and Track123 answers. new: a provider no column names.
-- direct: La Poste finds a number at its first check, no provider asked.
insert into public.packages (id, user_id, one_off, tracking_number, carrier, current_stage, sync_status, created_at, last_synced_at, carrier_data)
values
  ('f5000000-0000-4000-8000-000000000001', null, true, 'ASKED0001', 'unknown', 'pending', 'waiting',
    now() - interval '3 days', now() - interval '20 minutes', '{}'),
  ('f5000000-0000-4000-8000-000000000002', null, true, 'ASKED0002', 'la-poste', 'in_transit', 'ok',
    now() - interval '3 days', now() - interval '19 minutes', '{"tracking_provider": "ParcelsApp"}'),
  ('f5000000-0000-4000-8000-000000000003', null, true, 'ASKED0003', 'la-poste', 'registered', 'ok',
    now() - interval '3 days', now() - interval '18 minutes', '{}'),
  ('f5000000-0000-4000-8000-000000000004', null, true, 'ASKED0004', 'la-poste', 'in_transit', 'ok',
    now() - interval '3 days', now() - interval '17 minutes', '{}'),
  ('f5000000-0000-4000-8000-000000000005', null, true, 'ASKED0005', 'la-poste', 'in_transit', 'ok',
    now() - interval '3 days', now() - interval '16 minutes', '{"tracking_provider": "ParcelsApp"}'),
  ('f5000000-0000-4000-8000-000000000006', null, true, 'ASKED0006', 'la-poste', 'in_transit', 'ok',
    now() - interval '3 days', now() - interval '14 minutes', '{"tracking_provider": "Ship24"}'),
  ('f5000000-0000-4000-8000-000000000007', null, true, 'ASKED0007', 'z-leading', 'in_transit', 'ok',
    now() - interval '3 days', now() - interval '13 minutes', '{"tracking_provider": "ParcelsApp"}'),
  ('f5000000-0000-4000-8000-000000000008', null, true, 'ASKED0008', 'fedex', 'in_transit', 'ok',
    now() - interval '3 days', now() - interval '12 minutes', '{"tracking_provider": "Track123"}'),
  ('f5000000-0000-4000-8000-000000000009', null, true, 'ASKED0009', 'unknown', 'in_transit', 'ok',
    now() - interval '3 days', now() - interval '11 minutes', '{"tracking_provider": "NewTrack"}'),
  ('f5000000-0000-4000-8000-00000000000a', null, true, 'ASKED0010', 'la-poste', 'in_transit', 'ok',
    now() - interval '3 days', now() - interval '10 minutes', '{}');

insert into public.tracking_sync_attempts (id, package_id, trigger, configured_carrier, source_carrier, outcome,
  previous_stage, selected_stage, anomaly_codes, lookups, started_at, completed_at)
values
  ('f5000000-0000-4000-8000-0000000000a1', 'f5000000-0000-4000-8000-000000000001', 'scheduled', 'unknown', null, 'waiting',
    'pending', 'pending', '{}', '{"ParcelsApp": "no_history", "Ship24": "not_found", "17TRACK": "error"}',
    now() - interval '20 minutes', now() - interval '20 minutes'),
  ('f5000000-0000-4000-8000-0000000000a2', 'f5000000-0000-4000-8000-000000000002', 'added', 'la-poste', 'unknown', 'updated',
    null, 'in_transit', '{}', '{"la-poste": "no_history", "ParcelsApp": "success"}',
    now() - interval '19 minutes', now() - interval '19 minutes'),
  ('f5000000-0000-4000-8000-0000000000a3', 'f5000000-0000-4000-8000-000000000003', 'scheduled', 'la-poste', 'la-poste', 'unchanged',
    'registered', 'registered', '{}', '{"la-poste": "success", "17TRACK": "success"}',
    now() - interval '18 minutes', now() - interval '18 minutes'),
  ('f5000000-0000-4000-8000-0000000000a4', 'f5000000-0000-4000-8000-000000000004', 'scheduled', 'la-poste', 'la-poste', 'unchanged',
    'in_transit', 'in_transit', '{}', '{"la-poste": "success", "17TRACK": "no_history"}',
    now() - interval '17 minutes', now() - interval '17 minutes'),
  ('f5000000-0000-4000-8000-0000000000a5', 'f5000000-0000-4000-8000-000000000005', 'scheduled', 'la-poste', 'unknown', 'updated',
    'in_transit', 'out_for_delivery', '{}', '{"la-poste": "error", "ParcelsApp": "success"}',
    now() - interval '16 minutes', now() - interval '16 minutes'),
  ('f5000000-0000-4000-8000-0000000000a6', 'f5000000-0000-4000-8000-000000000006', 'scheduled', 'la-poste', 'unknown', 'unchanged',
    'in_transit', 'in_transit', '{}', '{"Ship24": "success"}',
    now() - interval '15 minutes', now() - interval '15 minutes'),
  ('f5000000-0000-4000-8000-0000000000b6', 'f5000000-0000-4000-8000-000000000006', 'scheduled', 'la-poste', null, 'waiting',
    'in_transit', 'in_transit', '{}', '{"la-poste": "input_required", "Ship24": "input_required"}',
    now() - interval '14 minutes', now() - interval '14 minutes'),
  ('f5000000-0000-4000-8000-0000000000a7', 'f5000000-0000-4000-8000-000000000007', 'scheduled', 'z-leading', 'unknown', 'unchanged',
    'in_transit', 'in_transit', '{}', '{"ParcelsApp": "success"}',
    now() - interval '13 minutes', now() - interval '13 minutes'),
  ('f5000000-0000-4000-8000-0000000000a8', 'f5000000-0000-4000-8000-000000000008', 'scheduled', 'fedex', 'unknown', 'unchanged',
    'in_transit', 'in_transit', '{}', '{"fedex": "error", "Track123": "success"}',
    now() - interval '12 minutes', now() - interval '12 minutes'),
  ('f5000000-0000-4000-8000-0000000000a9', 'f5000000-0000-4000-8000-000000000009', 'scheduled', 'unknown', 'unknown', 'updated',
    'in_transit', 'out_for_delivery', '{}', '{"NewTrack": "success"}',
    now() - interval '11 minutes', now() - interval '11 minutes'),
  ('f5000000-0000-4000-8000-0000000000aa', 'f5000000-0000-4000-8000-00000000000a', 'added', 'la-poste', 'la-poste', 'updated',
    'pending', 'in_transit', '{}', '{"la-poste": "success"}',
    now() - interval '10 minutes', now() - interval '10 minutes');
insert into public.tracking_sync_steps (attempt_id, sequence, step, status, details)
values
  ('f5000000-0000-4000-8000-0000000000a2', 6, 'complete', 'succeeded', '{"support_provider": "ParcelsApp"}'),
  ('f5000000-0000-4000-8000-0000000000a5', 6, 'complete', 'succeeded', '{"support_provider": "ParcelsApp"}'),
  ('f5000000-0000-4000-8000-0000000000a6', 6, 'complete', 'succeeded', '{"support_provider": "Ship24"}'),
  ('f5000000-0000-4000-8000-0000000000a7', 6, 'complete', 'succeeded', '{"support_provider": "ParcelsApp"}'),
  ('f5000000-0000-4000-8000-0000000000a8', 6, 'complete', 'succeeded', '{"support_provider": "Track123"}'),
  ('f5000000-0000-4000-8000-0000000000a9', 6, 'complete', 'succeeded', '{"support_provider": "NewTrack"}');

-- Each lookup sent to a provider counts once, under the provider's name, by why the check
-- asked and how the lookup ended; a carrier's own lookup counts nowhere.
do $$
declare
  counted text[];
begin
  perform metrics.record_provider_requests((now() at time zone 'UTC')::date);
  select array_agg(row(after.provider, after.purpose, after.requests - coalesce(before.requests, 0),
      after.with_history - coalesce(before.with_history, 0), after.without_history - coalesce(before.without_history, 0),
      after.failed - coalesce(before.failed, 0), after.with_news - coalesce(before.with_news, 0))::text
      order by after.provider, after.purpose) into counted
  from metrics.provider_requests_daily as after
  left join requests_before as before using (day, provider, purpose)
  where after.day = (now() at time zone 'UTC')::date and after.requests > coalesce(before.requests, 0);

  -- Late enough in the day that the checks of the last minutes are today's.
  if (now() at time zone 'UTC')::time >= time '00:30' and counted is distinct from array[
      row('17TRACK', 'beside_carrier', 1, 0, 1, 0, 0)::text,
      row('17TRACK', 'label_only', 1, 1, 0, 0, 0)::text,
      row('17TRACK', 'unseen', 1, 0, 0, 1, 0)::text,
      row('NewTrack', 'no_adapter', 1, 1, 0, 0, 1)::text,
      row('ParcelsApp', 'adapter_failed', 1, 1, 0, 0, 1)::text,
      row('ParcelsApp', 'no_adapter', 1, 1, 0, 0, 0)::text,
      row('ParcelsApp', 'unseen', 2, 1, 1, 0, 1)::text,
      row('Ship24', 'adapter_no_answer', 2, 1, 1, 0, 0)::text,
      row('Ship24', 'unseen', 1, 0, 1, 0, 0)::text,
      row('Track123', 'adapter_failed', 1, 1, 0, 0, 0)::text] then
    raise exception 'Provider requests were miscounted: %', counted;
  end if;
end;
$$;

-- Track123's parcel has its own count, a provider no column names counts among the others,
-- and a first scan counts once, with the one a provider found.
do $$
declare
  before goals_before;
  after metrics.goals_daily;
begin
  select * into strict before from goals_before;
  perform metrics.record_goals((now() at time zone 'UTC')::date);
  select * into strict after from metrics.goals_daily where day = (now() at time zone 'UTC')::date;

  if after.parcels - before.parcels <> 9
      or after.parcels_own_adapter - before.parcels_own_adapter <> 3
      or after.parcels_parcelsapp - before.parcels_parcelsapp <> 3
      or after.parcels_ship24 - before.parcels_ship24 <> 1
      or after.parcels_track123 - before.parcels_track123 <> 1
      or after.parcels_track718 <> before.parcels_track718
      or after.parcels_other_provider - before.parcels_other_provider <> 1 then
    raise exception 'Parcels were not counted under the provider that answered them: % then %', before, after;
  end if;
  if after.parcels <> after.parcels_own_adapter + after.parcels_other_adapter + after.parcels_parcelsapp
      + after.parcels_17track + after.parcels_ship24 + after.parcels_postal_ninja + after.parcels_upu
      + after.parcels_track123 + after.parcels_track718 + after.parcels_other_provider then
    raise exception 'A parcel counts under no source: %', after;
  end if;
  if (now() at time zone 'UTC')::time >= time '00:30' and (
      after.first_scans - before.first_scans <> 2
      or after.first_scans_by_provider - before.first_scans_by_provider <> 1) then
    raise exception 'First scans were miscounted: % then %', before, after;
  end if;
end;
$$;

-- A pass records the day's provider requests with its goals, and a day's rows are replaced.
do $$
begin
  insert into metrics.provider_requests_daily (day, provider, purpose, requests, with_history, without_history, failed, with_news)
  values ((now() at time zone 'UTC')::date, 'Gone', 'unseen', 5, 0, 5, 0, 0);
  update metrics.goals_daily set as_of = now() - interval '11 minutes' where day = (now() at time zone 'UTC')::date;
  perform public.record_usage_metrics();
  if exists (select 1 from metrics.provider_requests_daily
      where day = (now() at time zone 'UTC')::date and provider = 'Gone') then
    raise exception 'A recorded day kept a row its checks no longer hold';
  end if;
  if (now() at time zone 'UTC')::time >= time '00:30' and not exists (select 1 from metrics.provider_requests_daily
      where day = (now() at time zone 'UTC')::date and provider = 'Track123') then
    raise exception 'A pass did not record the day''s provider requests';
  end if;
end;
$$;

-- A parcel handed over from AliExpress to La Poste stays filed under AliExpress. handoff: La Poste fails and a
-- provider answers. follow_up: the same, with AliExpress asked alongside and answering. Both count as La Poste's
-- failure.
insert into public.packages (id, user_id, one_off, tracking_number, carrier, current_stage, sync_status, created_at, last_synced_at, carrier_data)
values
  ('f5000000-0000-4000-8000-00000000000b', null, true, 'ASKED0011', 'aliexpress', 'in_transit', 'ok',
    now() - interval '3 days', now() - interval '9 minutes',
    '{"original_carrier": "aliexpress", "active_tracking_carrier": "la-poste", "active_tracking_number": "ASKED0011"}'),
  ('f5000000-0000-4000-8000-00000000000c', null, true, 'ASKED0012', 'aliexpress', 'in_transit', 'ok',
    now() - interval '3 days', now() - interval '8 minutes',
    '{"original_carrier": "aliexpress", "active_tracking_carrier": "la-poste", "active_tracking_number": "ASKED0012"}');
insert into public.tracking_sync_attempts (id, package_id, trigger, configured_carrier, source_carrier, outcome,
  previous_stage, selected_stage, anomaly_codes, lookups, started_at, completed_at)
values
  ('f5000000-0000-4000-8000-0000000000ab', 'f5000000-0000-4000-8000-00000000000b', 'scheduled', 'aliexpress', 'unknown', 'unchanged',
    'in_transit', 'in_transit', '{}', '{"la-poste": "error", "HandoffTrack": "success"}',
    now() - interval '9 minutes', now() - interval '9 minutes'),
  ('f5000000-0000-4000-8000-0000000000ac', 'f5000000-0000-4000-8000-00000000000c', 'scheduled', 'aliexpress', 'unknown', 'unchanged',
    'in_transit', 'in_transit', '{}', '{"aliexpress": "success", "la-poste": "error", "FollowUpTrack": "success"}',
    now() - interval '8 minutes', now() - interval '8 minutes');

do $$
declare
  purposes text[];
begin
  perform metrics.record_provider_requests((now() at time zone 'UTC')::date);
  select array_agg(provider || ':' || purpose order by provider) into purposes from metrics.provider_requests_daily
  where day = (now() at time zone 'UTC')::date and provider in ('HandoffTrack', 'FollowUpTrack');
  if (now() at time zone 'UTC')::time >= time '00:30'
      and purposes is distinct from array['FollowUpTrack:adapter_failed', 'HandoffTrack:adapter_failed'] then
    raise exception 'A provider asked because the delivery carrier failed was filed as %', purposes;
  end if;
end;
$$;

rollback;
