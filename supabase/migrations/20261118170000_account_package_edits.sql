-- A connected assistant changes a parcel's carrier, archives it or restores it for the server, which acts
-- for the token's account. These run the owner's own functions for the account named, with every check
-- they make: the carrier list and its inputs, and the active-parcel limit a restore counts against.

create or replace function public.change_account_package_carrier(
  p_user_id uuid,
  p_package_id uuid,
  p_carrier text,
  p_tracking_url text default null,
  p_dpd_postcode text default null
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
  -- auth.uid() reads this first: change_owned_package_carrier acts for the account named here.
  perform set_config('request.jwt.claim.sub', p_user_id::text, true);
  changed := public.change_owned_package_carrier(p_package_id, p_carrier, p_tracking_url, p_dpd_postcode);
  perform set_config('request.jwt.claim.sub', '', true);
  return changed;
end;
$$;
revoke all on function public.change_account_package_carrier(uuid, uuid, text, text, text) from public, anon, authenticated;
grant execute on function public.change_account_package_carrier(uuid, uuid, text, text, text) to service_role;

create or replace function public.set_account_package_archived(
  p_user_id uuid,
  p_package_id uuid,
  p_archived boolean
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
  perform set_config('request.jwt.claim.sub', p_user_id::text, true);
  changed := public.set_owned_package_archived(p_package_id, p_archived);
  perform set_config('request.jwt.claim.sub', '', true);
  return changed;
end;
$$;
revoke all on function public.set_account_package_archived(uuid, uuid, boolean) from public, anon, authenticated;
grant execute on function public.set_account_package_archived(uuid, uuid, boolean) to service_role;

notify pgrst, 'reload schema';

insert into public.applied_migrations (name) values ('20261118170000_account_package_edits') on conflict do nothing;
