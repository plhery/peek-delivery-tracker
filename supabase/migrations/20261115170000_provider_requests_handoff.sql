-- A parcel handed over stays filed under its earlier carrier while its checks ask the delivery carrier. The
-- provider-requests dashboard now reads that carrier's lookup first, so a provider asked because the delivery carrier
-- failed counts under adapter_failed, and one asked beside its answer under label_only or beside_carrier. A check
-- that did not ask the delivery carrier is read by the filed carrier's lookup, as before. Days already recorded keep
-- their counts: recording them again would drop the lookups of parcels deleted since.

begin;

create or replace function metrics.record_provider_requests(p_day date)
returns void
language sql
security definer
set search_path = ''
as $$
  delete from metrics.provider_requests_daily where day = p_day;

  with bounds as (
    select p_day::timestamp at time zone 'UTC' as starts,
      least((p_day + 1)::timestamp at time zone 'UTC', now()) as ends
  ), asked as (
    select attempt.configured_carrier as carrier, attempt.previous_stage,
      coalesce(attempt.lookups ->> handed.carrier, attempt.lookups ->> attempt.configured_carrier) as own,
      attempt.outcome = 'updated' and attempt.source_carrier = 'unknown' as news,
      lookup.key as provider, lookup.value as result
    from public.tracking_sync_attempts as attempt
    cross join bounds
    -- A parcel handed over stays filed under its earlier carrier while its checks ask the delivery carrier.
    left join lateral (select package.carrier_data ->> 'active_tracking_carrier' as carrier
      from public.packages as package
      where package.id = attempt.package_id
        and package.carrier_data ->> 'original_carrier' = attempt.configured_carrier) as handed on true
    cross join lateral jsonb_each_text(attempt.lookups) as lookup
    where attempt.started_at >= bounds.starts and attempt.started_at < bounds.ends
      and lookup.key <> lower(lookup.key)
  ), direct as (
    -- Of the carriers those checks were filed under, the ones whose own adapter answered a
    -- check in the 30 days before.
    select filed.carrier
    from (select distinct carrier from asked where carrier not in ('unknown', 'intl-post')) as filed
    cross join bounds
    where exists (select 1 from public.tracking_sync_attempts as attempt
      where attempt.configured_carrier = filed.carrier and attempt.source_carrier = filed.carrier
        and attempt.outcome in ('updated', 'unchanged')
        and attempt.started_at >= bounds.ends - interval '30 days' and attempt.started_at < bounds.ends)
  )
  insert into metrics.provider_requests_daily (
    day, provider, purpose, requests, with_history, without_history, failed, with_news)
  select p_day, request.provider, request.purpose, count(*),
    count(*) filter (where request.result = 'success'),
    count(*) filter (where request.result not in ('success', 'error')),
    count(*) filter (where request.result = 'error'),
    count(*) filter (where request.result = 'success' and request.news)
  from (
    select asked.provider, asked.result, asked.news,
      case
        when asked.previous_stage is null or asked.previous_stage = 'pending' then 'unseen'
        when asked.own = 'success' and asked.previous_stage = 'registered' then 'label_only'
        when asked.own = 'success' then 'beside_carrier'
        when asked.own = 'error' then 'adapter_failed'
        when asked.own is not null or asked.carrier in (select carrier from direct) then 'adapter_no_answer'
        else 'no_adapter'
      end as purpose
    from asked
  ) as request
  group by request.provider, request.purpose;
$$;

comment on column metrics.provider_requests_daily.purpose is
  'Why the check asked a provider. unseen: the parcel showed no scan yet. no_adapter: filed under no carrier, or under one whose own adapter answered no check in the 30 days before. adapter_failed: the carrier''s adapter, or after a handoff the delivery carrier''s, failed in the check. adapter_no_answer: that adapter was not asked, or held no history. label_only: the carrier answered and the parcel showed its label only. beside_carrier: the carrier answered a parcel already moving.';

insert into public.applied_migrations (name) values ('20261115170000_provider_requests_handoff') on conflict do nothing;

commit;
