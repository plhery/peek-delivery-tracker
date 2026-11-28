-- A connected assistant gives the delivery postcode a tracking service asked for, for the server, which acts for the
-- token's account. It runs the owner's own function for the account named, with every check it makes: a permanent
-- account, a postcode's shape, and the parcel being the account's. The postcode is saved where the app's "Add
-- postcode" saves it.

create or replace function public.set_account_package_provider_postcode(
  p_user_id uuid,
  p_package_id uuid,
  p_postcode text
)
returns boolean
language plpgsql
security definer
set search_path = pg_catalog
as $$
declare
  changed boolean;
begin
  if p_user_id is null then
    raise exception 'An account is required' using errcode = '42501';
  end if;
  -- auth.uid() reads this first: set_owned_package_provider_postcode acts for the account named here.
  perform set_config('request.jwt.claim.sub', p_user_id::text, true);
  changed := public.set_owned_package_provider_postcode(p_package_id, p_postcode);
  perform set_config('request.jwt.claim.sub', '', true);
  return changed;
end;
$$;
revoke all on function public.set_account_package_provider_postcode(uuid, uuid, text) from public, anon, authenticated;
grant execute on function public.set_account_package_provider_postcode(uuid, uuid, text) to service_role;

notify pgrst, 'reload schema';

insert into public.applied_migrations (name) values ('20261128161000_account_provider_postcode') on conflict do nothing;
