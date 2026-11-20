-- Two things the router needs to spread lookups over the universal providers.
--
-- An hourly allowance per provider, shared by every server. Spacing alone admits a lookup every 5 seconds, 720 an
-- hour: a carrier's outage sends its parcels to the providers together, and one asked that much refuses the server
-- for every carrier it serves. The allowance is a token bucket that fills at the hourly limit and holds half an hour
-- of it, so a quiet provider takes a burst and a busy one settles at its limit. A lookup refused for it goes to
-- the next provider. A check nobody waits for leaves p_reserve lookups in the bucket, for those someone does.
-- The app states the limit with each request (providerPolicy.ts), so a limit changes with the code that knows the
-- provider. The one-argument function stays for the server running during the rollout, and asks without a limit.
--
-- A count of what each provider had for each carrier's parcels (provider_coverage), which the scraper's plan
-- grades into the order providers are asked in: a parcel a provider returned history for, and one it had none for
-- in a check where another provider did. A check no provider answered says nothing about any of them and counts
-- for none. A parcel followed under a delivery leg's number is left out: its lookups ask about the leg's carrier.

alter table public.tracking_provider_health
  add column allowance double precision,
  add column allowance_at timestamptz;
comment on column public.tracking_provider_health.allowance is
  'Lookups left in the provider''s hourly allowance when allowance_at was written; null until a request states a limit.';

create function public.acquire_tracking_provider(p_provider text, p_hourly_limit integer, p_reserve integer default 0)
returns jsonb language plpgsql set search_path = pg_catalog as $$
declare row public.tracking_provider_health; token uuid := gen_random_uuid(); remaining double precision;
begin
  if p_hourly_limit < 1 or p_reserve < 0 or p_reserve + 1 > p_hourly_limit / 2.0 then
    raise exception 'Invalid provider limit';
  end if;
  insert into public.tracking_provider_health(provider) values(p_provider) on conflict do nothing;
  select * into row from public.tracking_provider_health where provider = p_provider for update;
  if row.next_allowed_at > now() or row.lease_until > now() then
    return jsonb_build_object('token', null, 'retry_at', greatest(row.next_allowed_at, coalesce(row.lease_until, now())),
      'held', coalesce(row.lease_until > now(), false)
        or (row.last_failure_kind is null or row.last_failure_kind = 'not_found'));
  end if;
  if p_hourly_limit is not null then
    remaining := least(p_hourly_limit / 2.0, coalesce(row.allowance, p_hourly_limit / 2.0)
      + extract(epoch from now() - coalesce(row.allowance_at, now())) * p_hourly_limit / 3600.0);
    if remaining < 1 + p_reserve then
      return jsonb_build_object('token', null, 'limited', true,
        'retry_at', now() + (1 + p_reserve - remaining) * 3600.0 / p_hourly_limit * interval '1 second');
    end if;
  end if;
  update public.tracking_provider_health set lease_token = token, lease_until = now() + interval '90 seconds',
    last_attempt_at = now(), attempts = attempts + 1,
    allowance = case when p_hourly_limit is null then allowance else remaining - 1 end,
    allowance_at = case when p_hourly_limit is null then allowance_at else now() end
  where provider = p_provider;
  return jsonb_build_object('token', token, 'retry_at', now() + interval '90 seconds');
end;
$$;

create or replace function public.acquire_tracking_provider(p_provider text)
returns jsonb language sql set search_path = pg_catalog as $$
  select public.acquire_tracking_provider(p_provider, null::integer);
$$;

create function public.provider_coverage(p_days integer)
returns jsonb language sql stable set search_path = pg_catalog as $$
  with asked as (
    -- A lookup's source is a carrier or a provider; every provider asked has its row in tracking_provider_health.
    select attempt.id, attempt.configured_carrier as carrier, attempt.package_id, lookup.key as provider,
      lookup.value = 'success' as history,
      lookup.value in ('no_history', 'not_found', 'invalid_input') as empty
    from public.tracking_sync_attempts as attempt
    join public.packages as parcel on parcel.id = attempt.package_id
    cross join lateral jsonb_each_text(attempt.lookups) as lookup
    join public.tracking_provider_health as health on health.provider = lookup.key
    where attempt.started_at >= now() - make_interval(days => p_days)
      and attempt.configured_carrier <> 'unknown'
      and coalesce(parcel.carrier_data ->> 'active_tracking_number', parcel.tracking_number) = parcel.tracking_number
  ), judged as (
    select asked.carrier, asked.package_id, asked.provider, asked.history,
      -- UPU's sparse postal feed is asked last and holds parcels no aggregator can: its history makes no miss.
      asked.empty and bool_or(asked.history and asked.provider <> 'UPU') over (partition by asked.id) as missed
    from asked
  ), parcels as (
    select judged.carrier, judged.provider, judged.package_id,
      bool_or(judged.history) as history, bool_or(judged.missed) as missed
    from judged
    group by judged.carrier, judged.provider, judged.package_id
  ), counted as (
    select parcels.carrier, parcels.provider, count(*) filter (where parcels.history) as history,
      count(*) filter (where parcels.missed and not parcels.history) as missed
    from parcels
    where parcels.history or parcels.missed
    group by parcels.carrier, parcels.provider
  ), carriers as (
    select counted.carrier,
      jsonb_object_agg(counted.provider, jsonb_build_object('history', counted.history, 'missed', counted.missed)) as providers
    from counted
    group by counted.carrier
  )
  -- One value, `{carrier: {provider: {history, missed}}}`: the API's row limit cannot cut it short.
  select coalesce(jsonb_object_agg(carriers.carrier, carriers.providers), '{}'::jsonb) from carriers;
$$;

revoke all on function public.acquire_tracking_provider(text, integer, integer) from public, anon, authenticated;
revoke all on function public.provider_coverage(integer) from public, anon, authenticated;
grant execute on function public.acquire_tracking_provider(text, integer, integer) to service_role;
grant execute on function public.provider_coverage(integer) to service_role;

notify pgrst, 'reload schema';
insert into public.applied_migrations (name) values ('20261120203000_provider_hourly_limit') on conflict do nothing;
