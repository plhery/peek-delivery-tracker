-- A reader may say a parcel hasn't come (`not_received`): its own answer, kept with what the
-- page said of the parcel then (`page_said`): delivered by a carrier's scan, delivered or on
-- its way by steps its carrier writes in advance (`written_ahead`), or not delivered yet
-- (`in_transit`). A note may follow it, as it follows a reason. The goals count these answers
-- on their own (`feedback_not_received_7d`), outside `feedback_7d`, whose share of right answers
-- says whether Peek shows parcels right.
--
-- Apply this before deploying the server that accepts the answer. The server before it keeps
-- working on this schema: it never sends `not_received` or `page_said`.

alter table public.parcel_feedback
  add column page_said text check (page_said in ('delivered', 'written_ahead', 'in_transit')),
  drop constraint parcel_feedback_answer_check,
  add constraint parcel_feedback_answer_check check (answer in ('right', 'wrong', 'found_elsewhere', 'not_received')),
  drop constraint parcel_feedback_words_check,
  add constraint parcel_feedback_words_check check (
    ((answer = 'not_received') = (page_said is not null))
    and (
      (answer = 'right' and reasons = '{}' and note is null and carrier_name is null and tracking_page is null)
      or (answer = 'wrong' and carrier_name is null and tracking_page is null
        and (cardinality(reasons) > 0 or note is not null))
      or (answer = 'found_elsewhere' and reasons = '{}' and note is null
        and (carrier_name is not null or tracking_page is not null))
      or (answer = 'not_received' and reasons = '{}' and carrier_name is null and tracking_page is null)
    )
  );

comment on column public.parcel_feedback.page_said is
  'For an answer that the parcel has not come: what the page said of it then. delivered: a carrier''s scan; written_ahead: steps its carrier writes in advance; in_transit: not delivered yet.';

-- As before, and what the page said of a parcel that has not come.
create or replace function public.record_parcel_feedback(p_feedback jsonb)
returns text
language plpgsql
security definer
set search_path = ''
as $$
declare
  feedback_id constant uuid := (p_feedback->>'id')::uuid;
  number constant text := p_feedback->>'tracking_number';
  given_reasons constant text[] := array(
    select pg_catalog.jsonb_array_elements_text(coalesce(p_feedback->'reasons', '[]'))
  );
  existing public.parcel_feedback;
begin
  if feedback_id is null or number is null then
    raise exception 'Invalid parcel feedback' using errcode = '22023';
  end if;
  select * into existing from public.parcel_feedback where id = feedback_id for update;
  if found then
    if existing.tracking_number <> number or existing.answer <> p_feedback->>'answer'
        or existing.created_at < pg_catalog.now() - interval '1 hour' then
      return 'closed';
    end if;
    update public.parcel_feedback set
      reasons = given_reasons,
      note = p_feedback->>'note',
      carrier_name = p_feedback->>'carrier_name',
      tracking_page = p_feedback->>'tracking_page',
      updated_at = pg_catalog.now()
    where id = feedback_id;
    return 'replaced';
  end if;
  if (
    select pg_catalog.count(*) from public.parcel_feedback
    where tracking_number = number and created_at > pg_catalog.now() - interval '1 day'
  ) >= 20 then
    return 'full';
  end if;
  insert into public.parcel_feedback (
    id, tracking_number, carrier, answer, reasons, note, carrier_name, tracking_page, page_said,
    asked, via, app, locale, server_version, shown
  ) values (
    feedback_id, number, p_feedback->>'carrier', p_feedback->>'answer', given_reasons, p_feedback->>'note',
    p_feedback->>'carrier_name', p_feedback->>'tracking_page', p_feedback->>'page_said', p_feedback->>'asked',
    p_feedback->>'via', p_feedback->>'app', p_feedback->>'locale', p_feedback->>'server_version',
    coalesce(p_feedback->'shown', '{}')
  );
  return 'stored';
end;
$$;

alter table metrics.goals_daily
  add column feedback_not_received_7d integer not null default 0 check (feedback_not_received_7d >= 0);

comment on column metrics.goals_daily.feedback_7d is
  'Readers'' answers in the 7 days before as_of, leaving out those that the parcel has not come.';
comment on column metrics.goals_daily.feedback_not_received_7d is
  'Readers'' answers in the 7 days before as_of that the parcel has not come.';

-- As before, with the answers that a parcel has not come counted on their own.
create or replace function metrics.record_goals(p_day date)
returns void
language sql
security definer
set search_path = ''
as $$
  with bounds as (
    select p_day::timestamp at time zone 'UTC' as starts,
      least((p_day + 1)::timestamp at time zone 'UTC', now()) as ends
  ), checks as (
    select
      count(*) as checks,
      count(*) filter (where attempt.outcome in ('error', 'abandoned')) as failed,
      count(*) filter (where attempt.outcome in ('updated', 'unchanged')) as answered,
      count(*) filter (where attempt.outcome in ('updated', 'unchanged') and attempt.source_carrier is not null
        and attempt.source_carrier <> 'unknown') as direct,
      count(*) filter (where cardinality(attempt.anomaly_codes) > 0) as anomalous,
      count(*) filter (where attempt.outcome = 'updated' and attempt.selected_stage <> 'pending'
        and (attempt.previous_stage is null or attempt.previous_stage = 'pending')) as first_scans,
      count(*) filter (where attempt.outcome = 'updated' and attempt.selected_stage <> 'pending'
        and (attempt.previous_stage is null or attempt.previous_stage = 'pending')
        and attempt.source_carrier = 'unknown') as first_scans_by_provider
    from public.tracking_sync_attempts as attempt, bounds
    where attempt.started_at >= bounds.starts and attempt.started_at < bounds.ends
      and attempt.outcome in ('updated', 'unchanged', 'waiting', 'error', 'abandoned')
  ), parcel as (
    select package.id,
      (package.user_id is not null and coalesce(package.owned_since, package.created_at) < bounds.ends)
        or exists (select 1 from public.tracking_sync_attempts as attempt
          where attempt.package_id = package.id and attempt.trigger <> 'release'
            and attempt.started_at >= bounds.ends - interval '24 hours' and attempt.started_at < bounds.ends)
        as followed,
      answer.served_by, answer.provider, answer.started_at as answered_at, bounds.ends
    from public.packages as package
    cross join bounds
    left join lateral (
      select case when attempt.source_carrier = 'unknown' then 'provider'
                  when attempt.source_carrier = attempt.configured_carrier then 'adapter'
                  else 'other_adapter' end as served_by,
        (select step.details->>'support_provider' from public.tracking_sync_steps as step
          where step.attempt_id = attempt.id and step.step = 'complete' limit 1) as provider,
        attempt.started_at
      from public.tracking_sync_attempts as attempt
      where attempt.package_id = package.id and attempt.outcome in ('updated', 'unchanged')
        and attempt.started_at < bounds.ends
      order by attempt.started_at desc
      limit 1
    ) as answer on true
    where package.created_at < bounds.ends
      and (package.archived_at is null or package.archived_at >= bounds.ends)
      -- Delivered or returned by then: when a check, or else a stored scan, first said so.
      and (package.current_stage not in ('delivered', 'returned') or coalesce(
        (select min(attempt.started_at) from public.tracking_sync_attempts as attempt
          where attempt.package_id = package.id and attempt.selected_stage = package.current_stage
            and attempt.outcome in ('updated', 'unchanged')),
        (select min(event.created_at) from public.tracking_events as event
          where event.package_id = package.id and event.stage = package.current_stage),
        package.last_synced_at, package.created_at) >= bounds.ends)
      -- A parcel without a scan yet waits for its carrier: neither direct nor stale.
      and exists (select 1 from public.tracking_sync_attempts as attempt
        where attempt.package_id = package.id and attempt.outcome in ('updated', 'unchanged')
          and attempt.selected_stage <> 'pending' and attempt.started_at < bounds.ends)
  ), parcels as (
    select
      count(*) as parcels,
      count(*) filter (where served_by = 'adapter') as own_adapter,
      count(*) filter (where served_by = 'other_adapter') as other_adapter,
      count(*) filter (where served_by = 'provider' and provider = 'ParcelsApp') as parcelsapp,
      count(*) filter (where served_by = 'provider' and provider = '17TRACK') as seventeentrack,
      count(*) filter (where served_by = 'provider' and provider = 'Ship24') as ship24,
      count(*) filter (where served_by = 'provider' and provider = 'Postal Ninja') as postal_ninja,
      count(*) filter (where served_by = 'provider' and provider = 'UPU') as upu,
      count(*) filter (where served_by = 'provider' and provider = 'Track123') as track123,
      count(*) filter (where served_by = 'provider' and provider = 'Track718') as track718,
      -- Every provider named above has its column; any other counts here.
      count(*) filter (where served_by = 'provider' and (provider is null or provider not in (
        'ParcelsApp', '17TRACK', 'Ship24', 'Postal Ninja', 'UPU', 'Track123', 'Track718'))) as other_provider,
      count(*) filter (where followed) as followed,
      count(*) filter (where followed and answered_at < ends - interval '24 hours') as stale
    from parcel
  ), copies as (
    -- Only now: a copy the sync later absorbs leaves no trace of having been shown.
    select count(distinct copy.package_id) as parcels
    from public.tracking_events as copy
    join public.tracking_events as scan on scan.package_id = copy.package_id and scan.id <> copy.id
      and not (scan.raw_data ? 'universal_provider') and scan.stage = copy.stage
    where copy.raw_data ? 'universal_provider' and copy.package_id in (select id from parcel)
      and (date_trunc('second', copy.occurred_at) = date_trunc('second', scan.occurred_at)
        or (lower(btrim(copy.description)) = lower(btrim(scan.description))
          and abs(extract(epoch from copy.occurred_at - scan.occurred_at)) <= 14 * 3600
          and mod(extract(epoch from copy.occurred_at - scan.occurred_at)::numeric, 900) = 0))
  ), feedback as (
    select count(*) filter (where answer.answer <> 'not_received') as answers,
      count(*) filter (where answer.answer = 'right') as right_answers,
      count(*) filter (where answer.answer = 'not_received') as not_received
    from public.parcel_feedback as answer, bounds
    where answer.created_at >= bounds.ends - interval '7 days' and answer.created_at < bounds.ends
  ), scans as (
    select count(*) as scans,
      count(*) filter (where event.raw_data->>'stage_source' = 'none') as unmapped,
      count(*) filter (where event.raw_data->>'stage_source' like 'wording:%') as by_wording
    from public.tracking_events as event, bounds
    where event.created_at >= bounds.starts and event.created_at < bounds.ends and event.raw_data ? 'stage_source'
  ), wordings as (
    select count(*) as wordings
    from public.tracking_status_observations as observation, bounds
    where observation.first_seen >= bounds.starts and observation.first_seen < bounds.ends
  )
  insert into metrics.goals_daily as goals (
    day, as_of, checks, failed_checks, answered_checks, direct_checks, anomalous_checks,
    parcels, parcels_own_adapter, parcels_other_adapter, parcels_parcelsapp, parcels_17track, parcels_ship24,
    parcels_postal_ninja, parcels_upu, parcels_track123, parcels_track718, parcels_other_provider,
    followed_parcels, stale_parcels, copied_parcels,
    feedback_7d, feedback_right_7d, feedback_not_received_7d, scans, scans_unmapped, scans_by_wording, new_unmapped_wordings,
    first_scans, first_scans_by_provider
  )
  select p_day, bounds.ends, checks.checks, checks.failed, checks.answered, checks.direct, checks.anomalous,
    parcels.parcels, parcels.own_adapter, parcels.other_adapter, parcels.parcelsapp, parcels.seventeentrack,
    parcels.ship24, parcels.postal_ninja, parcels.upu, parcels.track123, parcels.track718, parcels.other_provider,
    parcels.followed, parcels.stale,
    case when bounds.ends > now() - interval '1 hour' then copies.parcels end,
    feedback.answers, feedback.right_answers, feedback.not_received, scans.scans, scans.unmapped, scans.by_wording, wordings.wordings,
    checks.first_scans, checks.first_scans_by_provider
  from bounds, checks, parcels, copies, feedback, scans, wordings
  on conflict (day) do update set
    as_of = excluded.as_of, checks = excluded.checks, failed_checks = excluded.failed_checks,
    answered_checks = excluded.answered_checks, direct_checks = excluded.direct_checks,
    anomalous_checks = excluded.anomalous_checks, parcels = excluded.parcels,
    parcels_own_adapter = excluded.parcels_own_adapter, parcels_other_adapter = excluded.parcels_other_adapter,
    parcels_parcelsapp = excluded.parcels_parcelsapp, parcels_17track = excluded.parcels_17track,
    parcels_ship24 = excluded.parcels_ship24, parcels_postal_ninja = excluded.parcels_postal_ninja,
    parcels_upu = excluded.parcels_upu, parcels_track123 = excluded.parcels_track123,
    parcels_track718 = excluded.parcels_track718, parcels_other_provider = excluded.parcels_other_provider,
    followed_parcels = excluded.followed_parcels, stale_parcels = excluded.stale_parcels,
    copied_parcels = coalesce(excluded.copied_parcels, goals.copied_parcels),
    feedback_7d = excluded.feedback_7d, feedback_right_7d = excluded.feedback_right_7d,
    feedback_not_received_7d = excluded.feedback_not_received_7d,
    scans = excluded.scans, scans_unmapped = excluded.scans_unmapped, scans_by_wording = excluded.scans_by_wording,
    new_unmapped_wordings = excluded.new_unmapped_wordings,
    first_scans = excluded.first_scans, first_scans_by_provider = excluded.first_scans_by_provider;
$$;

notify pgrst, 'reload schema';

insert into public.applied_migrations (name) values ('20261122170000_parcel_not_received') on conflict do nothing;
