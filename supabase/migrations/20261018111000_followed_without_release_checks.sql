-- A parcel without an account is followed when it had a check in the last 24 h. A scraper
-- release's check (trigger 'release') asks a parcel nobody may follow any more, so it no
-- longer counts: private.tracking_gaps and metrics.record_goals read only the parcel's own
-- follow-up, scheduled or asked for. Same columns.


create or replace view private.tracking_gaps as
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
    -- A release's check is no follow-up: nobody asked for it.
    package.user_id is not null or exists (
      select 1 from public.tracking_sync_attempts as attempt
      where attempt.package_id = package.id and attempt.started_at > now() - interval '24 hours'
        and attempt.trigger <> 'release'
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
         -- Its last answer was set aside (routing.set_aside): its newest scan older than the
         -- parcel's, without movement, or on local clocks only.
         when parcel.routing->'set_aside'->parcel.could_take is not null then
           case parcel.routing->'set_aside'->parcel.could_take->>'reason'
             when 'no_movement' then 'answer shows no movement'
             when 'local_clock' then 'answer on local clocks'
             else 'answer older than parcel' end
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
       when classified.why = 'answer older than parcel' then 'answer set aside as older'
       when classified.why = 'answer shows no movement' then 'answer without movement'
       when classified.why = 'answer on local clocks' then 'answer on local clocks'
       else 'adapter fails' end as reason,
  case when classified.why = 'no carrier known' then 'carriers'
       when classified.why = 'carrier answers, history kept' then 'none'
       when classified.why = 'not checked for 24 h' then 'dormant'
       when classified.why in ('carrier not asked', 'answer older than parcel') then 'delivery-tracker'
       when classified.why = 'adapter verification' then 'captcha'
       else 'adapters' end as lane,
  -- What the goals count against direct share and errors: a parcel whose latest answer
  -- came from a provider, or a followed one that has gone 24 h without one or shows an error.
  (classified.has_scan and classified.last_answer_by = 'provider') or (classified.followed and (classified.failing
    or (classified.has_scan and classified.last_answer_at < now() - interval '24 hours'))) as gap
from classified;

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
      count(*) filter (where cardinality(attempt.anomaly_codes) > 0) as anomalous
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

comment on column metrics.goals_daily.followed_parcels is
  'Parcels of accounts, and parcels without an account that had a check other than a release''s in the 24 h before as_of. Parcels nobody follows are not checked.';

insert into public.applied_migrations (name) values ('20261018111000_followed_without_release_checks') on conflict do nothing;

