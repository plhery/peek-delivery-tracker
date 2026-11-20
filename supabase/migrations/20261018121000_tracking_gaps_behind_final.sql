-- private.tracking_gaps also tells a carrier whose last answer for the parcel was set aside
-- as behind a provider's delivered or returned stage (routing.set_aside reason
-- 'behind_final', written by the router) from one not asked: why 'answer behind delivered or
-- returned', reason 'answer behind a final stage', in the adapters lane. Same columns;
-- metrics.carrier_gaps reads it unchanged.


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
         -- parcel's, without movement, on local clocks only, or behind a provider's delivered or
         -- returned stage.
         when parcel.routing->'set_aside'->parcel.could_take is not null then
           case parcel.routing->'set_aside'->parcel.could_take->>'reason'
             when 'no_movement' then 'answer shows no movement'
             when 'local_clock' then 'answer on local clocks'
             when 'behind_final' then 'answer behind delivered or returned'
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
       when classified.why = 'answer behind delivered or returned' then 'answer behind a final stage'
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

insert into public.applied_migrations (name) values ('20261018121000_tracking_gaps_behind_final') on conflict do nothing;

