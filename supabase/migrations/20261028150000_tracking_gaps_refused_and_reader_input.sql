-- private.tracking_gaps tells two parcels it listed as 'carrier not asked' or 'no carrier known'. A
-- carrier whose latest answer the sync refused because it lacked the parcel's progress
-- (ProgressDisappeared) was asked: 'answer lacks the parcel's progress', lane delivery-tracker. And a
-- parcel filed under no carrier whose reader Peek asks for a carrier's input (routing.input_needed or
-- input_suggested) could be taken by that carrier: 'reader asked for input', lane none, as is any
-- parcel whose reader is asked for the input of the carrier that could take it. Same columns;
-- metrics.carrier_gaps reads it unchanged.

begin;

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
    -- One no adapter has answered for in 30 days, as one only universal providers track, leaves the
    -- parcel to the carrier a provider names as delivering it (delivery_carrier) when that one's
    -- adapter answers: the sync asks it (ROUTING.md).
    case when filed.carrier not in ('unknown', 'intl-post') and filed.carrier not in (select carrier from direct)
           and nullif(package.carrier_data->>'delivery_carrier', '') in (select carrier from direct)
         then package.carrier_data->>'delivery_carrier' else filed.carrier end as could_take,
    nullif(package.carrier_data->>'delivery_carrier', '') as last_mile,
    coalesce(nullif(package.carrier_data->'routing'->'input_needed'->>'carrier', ''),
      nullif(package.carrier_data->'routing'->'input_suggested'->>'carrier', '')) as input_of,
    package.carrier_data->'delivery_probe' as probe,
    nullif(package.carrier_data->>'discovered_carrier', '') as named,
    package.carrier_data->>'tracking_provider' as provider,
    package.carrier_data->>'carrier_answered' = 'true' as answered,
    package.carrier_data->'routing' as routing,
    answer.served_by as last_answer_by, answer.started_at as last_answer_at,
    -- The carrier whose answer the sync refused at the latest check one answered, for lacking
    -- the parcel's progress.
    case when latest.error_type = 'ProgressDisappeared' then latest.source_carrier end as refused,
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
  cross join lateral (
    -- For a parcel filed under no carrier, else the carrier whose input Peek asks its reader for.
    select coalesce(nullif(package.carrier_data->>'discovered_carrier', ''),
      nullif(package.carrier_data->'routing'->>'discovered_carrier', ''), detected.carrier,
      case when package.carrier in ('unknown', 'intl-post') then coalesce(
        nullif(package.carrier_data->'routing'->'input_needed'->>'carrier', ''),
        nullif(package.carrier_data->'routing'->'input_suggested'->>'carrier', '')) end,
      package.carrier) as carrier
  ) as filed
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
  left join lateral (
    select attempt.source_carrier, attempt.error_type
    from public.tracking_sync_attempts as attempt
    where attempt.package_id = package.id
      and (attempt.outcome in ('updated', 'unchanged') or attempt.error_type = 'ProgressDisappeared')
    order by attempt.started_at desc
    limit 1
  ) as latest on true
  where package.archived_at is null and coalesce(package.current_stage, '') not in ('delivered', 'returned')
    and (package.carrier_data->>'tracking_provider' is not null or package.sync_status = 'error'
      or answer.served_by = 'provider' or answer.started_at < now() - interval '24 hours')
), classified as (
  select parcel.*,
    case when parcel.could_take in ('unknown', 'intl-post') then 'no carrier known'
         -- Whatever its last check stored, failures included, is as old as that check.
         when parcel.last_synced_at < now() - interval '24 hours' then 'not checked for 24 h'
         -- A provider filled in the carrier's partial answer: the parcel is served as meant.
         when parcel.answered and parcel.routing->'partial_history'->>'provider' = parcel.provider
           then 'carrier answers, history kept'
         -- The carrier's rows show its label alone while the provider's progress serves the parcel:
         -- it scans only its own leg, the provider follows the legs before (ROUTING.md).
         when parcel.answered and exists (
           select 1 from public.tracking_events as event
           where event.package_id = parcel.package_id and split_part(event.provider_event_id, ':', 1) = parcel.could_take
         ) and not exists (
           select 1 from public.tracking_events as event
           where event.package_id = parcel.package_id and split_part(event.provider_event_id, ':', 1) = parcel.could_take
             and event.stage is distinct from 'registered'
         ) then 'carrier shows its label alone'
         when parcel.answered then 'carrier answered in part'
         -- Peek asks the reader for the input the carrier needs (a delivery postcode).
         when parcel.could_take = parcel.input_of then 'reader asked for input'
         when parcel.routing->'failures'->parcel.could_take->>'user_error' = 'carrier:invalid_input'
           then 'adapter refuses the number'
         -- Routing forgets, unasked, a carrier the provider names whose adapter refuses the number.
         when parcel.could_take = parcel.named and parcel.could_take is distinct from parcel.routing->>'discovered_carrier'
           then 'adapter refuses the number'
         -- Its last answer was set aside (routing.set_aside): its newest scan older than the
         -- parcel's, without movement, on local clocks only, without any date, or behind a
         -- provider's delivered or returned stage. An older one whose newest scan the parcel
         -- already shows adds nothing: the carrier only knows an earlier leg.
         when parcel.routing->'set_aside'->parcel.could_take is not null then
           case parcel.routing->'set_aside'->parcel.could_take->>'reason'
             when 'no_movement' then 'answer shows no movement'
             when 'local_clock' then 'answer on local clocks'
             when 'undated' then 'answer without dates'
             when 'behind_final' then 'answer behind delivered or returned'
             else case when exists (
               select 1 from public.tracking_events as event
               where event.package_id = parcel.package_id
                 and event.occurred_at >= date_trunc('minute', (parcel.routing->'set_aside'->parcel.could_take->>'newest')::timestamptz)
                 and event.occurred_at < date_trunc('minute', (parcel.routing->'set_aside'->parcel.could_take->>'newest')::timestamptz) + interval '1 minute'
             ) then 'answer shows an earlier leg' else 'answer older than parcel' end end
         when parcel.routing->'failures'->parcel.could_take is not null
           then 'adapter ' || coalesce(parcel.routing->'failures'->parcel.could_take->>'kind', 'failed')
         -- The delivery carrier a provider names was asked (delivery_probe) and has not scanned the
         -- parcel yet: the parcel follows it once it does.
         when parcel.could_take = parcel.last_mile and parcel.probe->>'carrier' = parcel.last_mile
           then 'delivery carrier yet to scan'
         -- Asked, it answered without the progress the parcel shows, and the sync refused the answer.
         when parcel.could_take = parcel.refused then 'answer lacks the parcel''s progress'
         when parcel.could_take not in (select carrier from direct) then 'no direct answer in 30 days'
         when parcel.routing->>'confirmed_carrier' = parcel.could_take then 'carrier answers, history kept'
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
       when classified.why = 'answer shows an earlier leg' then 'carrier knows an earlier leg'
       when classified.why = 'carrier shows its label alone' then 'carrier knows its label alone'
       when classified.why = 'delivery carrier yet to scan' then 'delivery carrier yet to scan'
       when classified.why = 'answer shows no movement' then 'answer without movement'
       when classified.why = 'answer on local clocks' then 'answer on local clocks'
       when classified.why = 'answer without dates' then 'answer without dates'
       when classified.why = 'answer behind delivered or returned' then 'answer behind a final stage'
       when classified.why = 'reader asked for input' then 'reader asked for input'
       when classified.why = 'answer lacks the parcel''s progress' then 'answer refused'
       else 'adapter fails' end as reason,
  case when classified.why = 'no carrier known' then 'carriers'
       when classified.why in ('carrier answers, history kept', 'answer shows an earlier leg', 'carrier shows its label alone',
         'delivery carrier yet to scan', 'reader asked for input') then 'none'
       when classified.why = 'not checked for 24 h' then 'dormant'
       when classified.why in ('carrier not asked', 'answer older than parcel', 'answer lacks the parcel''s progress')
         then 'delivery-tracker'
       when classified.why = 'adapter verification' then 'captcha'
       else 'adapters' end as lane,
  -- What the goals count against direct share and errors: a parcel whose latest answer
  -- came from a provider, or a followed one that has gone 24 h without one or shows an error.
  (classified.has_scan and classified.last_answer_by = 'provider') or (classified.followed and (classified.failing
    or (classified.has_scan and classified.last_answer_at < now() - interval '24 hours'))) as gap
from classified;

insert into public.applied_migrations (name) values ('20261028150000_tracking_gaps_refused_and_reader_input') on conflict do nothing;

commit;
