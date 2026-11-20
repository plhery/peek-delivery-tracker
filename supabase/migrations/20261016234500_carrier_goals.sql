-- The carrier dashboard's goals: few errors a reader sees, good tracking, and parcels
-- answered by their carrier's own tracking rather than a universal provider (17TRACK,
-- ParcelsApp, Ship24, Postal Ninja, UPU). See docs/OBSERVABILITY.md.
--
-- private.tracking_gaps classifies each unfinished parcel that a provider tracks or
-- whose checks fail: the carrier that could take it, why it does not, and the lane that
-- acts on it. The hourly review (ops/review/signals.sql) lists it with number shapes;
-- metrics.carrier_gaps counts it by carrier for Grafana.
--
-- metrics.goals_daily keeps one row per UTC day, rewritten by record_usage_metrics at most
-- every ten minutes, and once more after midnight as of the day's end. The past days the
-- checks' audit still holds are filled from it below.
--
-- metrics.releases lists each server version's start, for the dashboard's annotations.
--
-- Nothing here names a parcel, an account or a number outside the private schema. The
-- server needs no change: it already calls record_usage_metrics.


create view private.tracking_gaps as
with direct as (
  -- Carriers whose own adapter answered a check in the last 30 days.
  select distinct attempt.source_carrier as carrier
  from public.tracking_sync_attempts as attempt
  where attempt.started_at >= now() - interval '30 days' and attempt.outcome in ('updated', 'unchanged')
    and attempt.source_carrier is not null and attempt.source_carrier not in ('unknown', 'intl-post')
), parcel as (
  -- The summary names the carrier that answered or, from a provider's answer, the one the
  -- provider names; routing keeps a provider's naming unless that carrier's adapter refuses
  -- the number. An unfiled parcel's support case keeps what today's detection names.
  select package.id as package_id, package.tracking_number, package.carrier, package.created_at,
    package.last_synced_at, package.current_stage is distinct from 'pending' as has_scan,
    package.sync_status = 'error' as failing,
    coalesce(nullif(package.carrier_data->>'discovered_carrier', ''),
      nullif(package.carrier_data->'routing'->>'discovered_carrier', ''), detected.carrier, package.carrier) as could_take,
    nullif(package.carrier_data->>'discovered_carrier', '') as named,
    package.carrier_data->>'tracking_provider' as provider,
    package.carrier_data->>'carrier_answered' = 'true' as answered,
    package.carrier_data->'routing' as routing,
    answer.served_by as last_answer_by, answer.started_at as last_answer_at,
    package.user_id is not null or exists (
      select 1 from public.tracking_sync_attempts as attempt
      where attempt.package_id = package.id and attempt.started_at > now() - interval '24 hours'
    ) as followed
  from public.packages as package
  left join lateral (
    select support.detection_carrier as carrier
    from public.tracking_support_cases as support
    where support.tracking_number = package.tracking_number and package.carrier in ('unknown', 'intl-post')
      and support.detection_confidence = 'high' and support.detection_carrier not in ('unknown', 'intl-post')
    order by support.last_seen desc
    limit 1
  ) as detected on true
  left join lateral (
    select case when attempt.source_carrier = 'unknown' then 'provider'
                when attempt.source_carrier = attempt.configured_carrier then 'adapter'
                else 'other_adapter' end as served_by,
      attempt.started_at
    from public.tracking_sync_attempts as attempt
    where attempt.package_id = package.id and attempt.outcome in ('updated', 'unchanged')
    order by attempt.started_at desc
    limit 1
  ) as answer on true
  where package.archived_at is null and coalesce(package.current_stage, '') not in ('delivered', 'returned')
    and (package.carrier_data->>'tracking_provider' is not null or package.sync_status = 'error'
      or answer.served_by = 'provider' or answer.started_at < now() - interval '24 hours')
), classified as (
  select parcel.*,
    case when parcel.could_take in ('unknown', 'intl-post') then 'no carrier known'
         when parcel.answered then 'carrier answered in part'
         when parcel.routing->'failures'->parcel.could_take->>'user_error' = 'carrier:invalid_input'
           then 'adapter refuses the number'
         -- Routing forgets, unasked, a carrier the provider names whose adapter refuses the number.
         when parcel.could_take = parcel.named and parcel.could_take is distinct from parcel.routing->>'discovered_carrier'
           then 'adapter refuses the number'
         when parcel.routing->'failures'->parcel.could_take is not null
           then 'adapter ' || coalesce(parcel.routing->'failures'->parcel.could_take->>'kind', 'failed')
         when parcel.could_take not in (select carrier from direct) then 'no direct answer in 30 days'
         when parcel.routing->>'confirmed_carrier' = parcel.could_take then 'carrier answers, history kept'
         when parcel.last_synced_at < now() - interval '24 hours' then 'not checked for 24 h'
         when parcel.provider is null and parcel.last_answer_by is distinct from 'provider' then 'checks failing'
         else 'carrier not asked' end as why
  from parcel
)
select classified.package_id, classified.tracking_number, classified.carrier, classified.could_take,
  classified.provider, classified.created_at, classified.has_scan, classified.failing, classified.followed,
  classified.last_answer_by, classified.last_answer_at, classified.why,
  case when classified.why = 'no carrier known' then 'no carrier known'
       when classified.why in ('adapter refuses the number', 'adapter not_found', 'adapter no_history',
         'adapter input_required', 'adapter invalid_input') then 'adapter refuses'
       when classified.why = 'carrier answered in part' then 'half-answers'
       when classified.why = 'adapter verification' then 'verification or captcha'
       when classified.why in ('adapter transport', 'adapter rate_limited') then 'transport'
       when classified.why = 'no direct answer in 30 days' then 'no direct answer'
       when classified.why = 'carrier answers, history kept' then 'provider history fuller'
       when classified.why = 'not checked for 24 h' then 'not checked'
       when classified.why = 'carrier not asked' then 'carrier not asked'
       else 'adapter fails' end as reason,
  case when classified.why = 'no carrier known' then 'carriers'
       when classified.why = 'carrier answers, history kept' then 'none'
       when classified.why = 'not checked for 24 h' then 'dormant'
       when classified.why = 'carrier not asked' then 'delivery-tracker'
       when classified.why = 'adapter verification' then 'captcha'
       else 'adapters' end as lane,
  -- What the goals count against direct share and errors: a parcel whose latest answer
  -- came from a provider, or a followed one that has gone 24 h without one or shows an error.
  (classified.has_scan and classified.last_answer_by = 'provider') or (classified.followed and (classified.failing
    or (classified.has_scan and classified.last_answer_at < now() - interval '24 hours'))) as gap
from classified;

comment on view private.tracking_gaps is
  'Unfinished parcels a universal provider tracks or answered last, or whose checks fail: the carrier that could take each, why it does not, the lane that acts on it, and whether the goals count it (gap). Service role and reviewers only.';

revoke all on private.tracking_gaps from public, anon, authenticated;

-- The goals by carrier, most parcels first: what share of the gap the first carriers cover.
create view metrics.carrier_gaps as
with gap as (
  select could_take as carrier, reason, lane, count(*)::integer as parcels
  from private.tracking_gaps
  where gap
  group by 1, 2, 3
), carrier as (
  select gap.carrier, sum(gap.parcels)::integer as carrier_parcels,
    row_number() over (order by sum(gap.parcels) desc, gap.carrier)::integer as rank
  from gap
  group by gap.carrier
), running as (
  select carrier.*, sum(carrier.carrier_parcels) over (order by carrier.rank) as up_to_rank,
    sum(carrier.carrier_parcels) over () as total
  from carrier
)
select running.rank, gap.carrier, gap.reason, gap.lane, gap.parcels, running.carrier_parcels,
  round(100.0 * running.up_to_rank / running.total, 1) as cumulative_percent
from gap
join running using (carrier);

comment on view metrics.carrier_gaps is
  'Unfinished parcels whose latest answer came from a universal provider, and followed ones that went 24 h without an answer or show an error, by the carrier that could take them, reason and lane. rank orders the carriers by parcels; cumulative_percent is the share of all such parcels the carriers up to this rank hold.';

create table metrics.goals_daily (
  day date primary key,
  as_of timestamptz not null,
  checks integer not null check (checks >= 0),
  failed_checks integer not null check (failed_checks >= 0),
  answered_checks integer not null check (answered_checks >= 0),
  direct_checks integer not null check (direct_checks >= 0),
  anomalous_checks integer not null check (anomalous_checks >= 0),
  parcels integer not null check (parcels >= 0),
  parcels_own_adapter integer not null check (parcels_own_adapter >= 0),
  parcels_other_adapter integer not null check (parcels_other_adapter >= 0),
  parcels_parcelsapp integer not null check (parcels_parcelsapp >= 0),
  parcels_17track integer not null check (parcels_17track >= 0),
  parcels_ship24 integer not null check (parcels_ship24 >= 0),
  parcels_postal_ninja integer not null check (parcels_postal_ninja >= 0),
  parcels_upu integer not null check (parcels_upu >= 0),
  parcels_other_provider integer not null check (parcels_other_provider >= 0),
  followed_parcels integer not null check (followed_parcels >= 0),
  stale_parcels integer not null check (stale_parcels >= 0),
  copied_parcels integer check (copied_parcels >= 0),
  feedback_7d integer not null check (feedback_7d >= 0),
  feedback_right_7d integer not null check (feedback_right_7d >= 0),
  scans integer not null check (scans >= 0),
  scans_unmapped integer not null check (scans_unmapped >= 0),
  scans_by_wording integer not null check (scans_by_wording >= 0),
  new_unmapped_wordings integer not null check (new_unmapped_wordings >= 0)
);

comment on table metrics.goals_daily is
  'The carrier goals per UTC day, counted as of as_of: the day''s end, or the latest recording for today.';
comment on column metrics.goals_daily.checks is
  'Checks that started that day and ended updated, unchanged, waiting, error or abandoned.';
comment on column metrics.goals_daily.failed_checks is 'Checks that ended error or abandoned.';
comment on column metrics.goals_daily.answered_checks is 'Checks that ended updated or unchanged.';
comment on column metrics.goals_daily.direct_checks is
  'Answered checks a carrier''s own adapter served: the parcel''s carrier, or the carrier it was handed to.';
comment on column metrics.goals_daily.anomalous_checks is 'Checks with at least one anomaly code.';
comment on column metrics.goals_daily.parcels is
  'Parcels neither delivered, returned nor archived at as_of, with a scan by then: a check had answered with a stage other than pending.';
comment on column metrics.goals_daily.parcels_own_adapter is
  'Of those, parcels whose latest answered check their own carrier''s adapter served.';
comment on column metrics.goals_daily.parcels_other_adapter is
  'Parcels whose latest answered check another carrier''s adapter served: a handoff, or a carrier found for the number.';
comment on column metrics.goals_daily.parcels_other_provider is
  'Parcels whose latest answered check a universal provider served without naming itself, as checks before 4 October 2026 did.';
comment on column metrics.goals_daily.followed_parcels is
  'Parcels of accounts, and parcels without an account that had a check in the 24 h before as_of. Parcels nobody follows are not checked.';
comment on column metrics.goals_daily.stale_parcels is 'Followed parcels with no answered check in the 24 h before as_of.';
comment on column metrics.goals_daily.copied_parcels is
  'Parcels that show a universal provider''s copy of a carrier''s scan: the same stage at the same second, or the same wording a whole number of quarter hours up to 14 h away. Counted only when recorded live.';
comment on column metrics.goals_daily.feedback_7d is 'Readers'' answers in the 7 days before as_of.';
comment on column metrics.goals_daily.feedback_right_7d is 'Of those, answers that the parcel is right.';
comment on column metrics.goals_daily.scans is 'Scans stored that day that record where their stage came from.';
comment on column metrics.goals_daily.scans_unmapped is 'Of those, scans no carrier map or wording rule read.';
comment on column metrics.goals_daily.scans_by_wording is 'Of those, scans a wording rule read instead of a carrier map.';
comment on column metrics.goals_daily.new_unmapped_wordings is 'Wordings first collected as unmapped that day.';

-- Counts one UTC day as of its end, or as of now for today.
create function metrics.record_goals(p_day date)
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
      count(*) filter (where cardinality(attempt.anomaly_codes) > 0) as anomalous
    from public.tracking_sync_attempts as attempt, bounds
    where attempt.started_at >= bounds.starts and attempt.started_at < bounds.ends
      and attempt.outcome in ('updated', 'unchanged', 'waiting', 'error', 'abandoned')
  ), parcel as (
    select package.id,
      (package.user_id is not null and coalesce(package.owned_since, package.created_at) < bounds.ends)
        or exists (select 1 from public.tracking_sync_attempts as attempt
          where attempt.package_id = package.id
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
      count(*) filter (where served_by = 'provider' and (provider is null
        or provider not in ('ParcelsApp', '17TRACK', 'Ship24', 'Postal Ninja', 'UPU'))) as other_provider,
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
    select count(*) as answers, count(*) filter (where answer.answer = 'right') as right_answers
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
    parcels_postal_ninja, parcels_upu, parcels_other_provider, followed_parcels, stale_parcels, copied_parcels,
    feedback_7d, feedback_right_7d, scans, scans_unmapped, scans_by_wording, new_unmapped_wordings
  )
  select p_day, bounds.ends, checks.checks, checks.failed, checks.answered, checks.direct, checks.anomalous,
    parcels.parcels, parcels.own_adapter, parcels.other_adapter, parcels.parcelsapp, parcels.seventeentrack,
    parcels.ship24, parcels.postal_ninja, parcels.upu, parcels.other_provider, parcels.followed, parcels.stale,
    case when bounds.ends > now() - interval '1 hour' then copies.parcels end,
    feedback.answers, feedback.right_answers, scans.scans, scans.unmapped, scans.by_wording, wordings.wordings
  from bounds, checks, parcels, copies, feedback, scans, wordings
  on conflict (day) do update set
    as_of = excluded.as_of, checks = excluded.checks, failed_checks = excluded.failed_checks,
    answered_checks = excluded.answered_checks, direct_checks = excluded.direct_checks,
    anomalous_checks = excluded.anomalous_checks, parcels = excluded.parcels,
    parcels_own_adapter = excluded.parcels_own_adapter, parcels_other_adapter = excluded.parcels_other_adapter,
    parcels_parcelsapp = excluded.parcels_parcelsapp, parcels_17track = excluded.parcels_17track,
    parcels_ship24 = excluded.parcels_ship24, parcels_postal_ninja = excluded.parcels_postal_ninja,
    parcels_upu = excluded.parcels_upu, parcels_other_provider = excluded.parcels_other_provider,
    followed_parcels = excluded.followed_parcels, stale_parcels = excluded.stale_parcels,
    copied_parcels = coalesce(excluded.copied_parcels, goals.copied_parcels),
    feedback_7d = excluded.feedback_7d, feedback_right_7d = excluded.feedback_right_7d,
    scans = excluded.scans, scans_unmapped = excluded.scans_unmapped, scans_by_wording = excluded.scans_by_wording,
    new_unmapped_wordings = excluded.new_unmapped_wordings;
$$;

revoke all on function metrics.record_goals(date) from public;

-- Rewrites today's counts, and the additions of the day before; today's goals at most every
-- ten minutes, and yesterday's once more as of its end.
create or replace function public.record_usage_metrics()
returns void
language plpgsql
security definer
set search_path = ''
as $$
declare
  today constant date := (now() at time zone 'UTC')::date;
begin
  perform metrics.record_additions(today - 1, today);
  update metrics.daily set
    accounts = (select count(*) from auth.users where not is_anonymous),
    parcels_open_account = (select count(*) from public.packages
      where user_id is not null and archived_at is null and current_stage not in ('delivered', 'returned')),
    parcels_open_lookup = (select count(*) from public.packages
      where one_off and archived_at is null and current_stage not in ('delivered', 'returned'))
  where day = today;

  if not exists (select 1 from metrics.goals_daily
      where day = today - 1 and as_of >= today::timestamp at time zone 'UTC') then
    perform metrics.record_goals(today - 1);
  end if;
  if not exists (select 1 from metrics.goals_daily where day = today and as_of > now() - interval '10 minutes') then
    perform metrics.record_goals(today);
  end if;
end;
$$;

-- Each server version's start: the scraper release it pins and the app commit.
create view metrics.releases as
select run.started_at as time, release.scraper, release.app,
  release.scraper is distinct from lag(release.scraper) over (order by run.started_at) as scraper_changed
from public.tracking_review_runs as run
cross join lateral (
  select substring(run.version from 'universal-parcel-scraper@([^ ]+)') as scraper,
    substring(run.version from 'app@([0-9a-f]+)') as app
) as release;

comment on view metrics.releases is
  'When each server version started, a minute before its review replay: the scraper release it pins, the app commit, and whether the scraper release changed.';

grant select on metrics.goals_daily, metrics.carrier_gaps, metrics.releases to metrics_reader;

-- The days the checks' audit still holds.
select metrics.record_goals(day::date)
from generate_series(
  (select (min(started_at) at time zone 'UTC')::date from public.tracking_sync_attempts),
  (now() at time zone 'UTC')::date,
  interval '1 day'
) as day
where exists (select 1 from public.tracking_sync_attempts);

insert into public.applied_migrations (name) values ('20261016234500_carrier_goals') on conflict do nothing;

