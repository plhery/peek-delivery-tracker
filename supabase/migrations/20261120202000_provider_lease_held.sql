-- A check that found a universal provider taken could not tell another check's call from the provider's own rest: both
-- came back as a token-less lease with a time to retry at. A check of a carrier with an adapter of its own that fell back
-- to a provider then stored a rate limit the provider never gave and moved on. The answer now says which (`held`):
-- another check's lease, or the 5 s spacing after another check's call that answered (or found no history), is held;
-- a cooldown after a failure is the provider's rest. The server waits for a held provider and records no rest.

create or replace function public.acquire_tracking_provider(p_provider text)
returns jsonb language plpgsql set search_path = pg_catalog as $$
declare row public.tracking_provider_health; token uuid := gen_random_uuid();
begin
  insert into public.tracking_provider_health(provider) values(p_provider) on conflict do nothing;
  select * into row from public.tracking_provider_health where provider = p_provider for update;
  if row.next_allowed_at > now() or row.lease_until > now() then
    return jsonb_build_object('token', null, 'retry_at', greatest(row.next_allowed_at, coalesce(row.lease_until, now())),
      'held', coalesce(row.lease_until > now(), false)
        or (row.last_failure_kind is null or row.last_failure_kind = 'not_found'));
  end if;
  update public.tracking_provider_health set lease_token = token, lease_until = now() + interval '90 seconds',
    last_attempt_at = now(), attempts = attempts + 1 where provider = p_provider;
  return jsonb_build_object('token', token, 'retry_at', now() + interval '90 seconds');
end;
$$;

revoke all on function public.acquire_tracking_provider(text) from public, anon, authenticated;
grant execute on function public.acquire_tracking_provider(text) to service_role;

insert into public.applied_migrations (name) values ('20261120202000_provider_lease_held') on conflict do nothing;
