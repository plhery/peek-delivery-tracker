-- The app language of a parcel's owner, read by the sync loaders as a computed field
-- (`owner_locale` in their select): carriers that word their scans in several languages
-- answer in the owner's. None for a lookup without an account. The parameter is unnamed,
-- so PostgREST does not expose it under /rpc; only the service role may call it.
create or replace function public.owner_locale(public.packages)
returns text language sql stable security definer set search_path = '' as $$
  select nullif(u.raw_user_meta_data->>'locale', '') from auth.users u where u.id = $1.user_id;
$$;
revoke all on function public.owner_locale(public.packages) from public, anon, authenticated;
grant execute on function public.owner_locale(public.packages) to service_role;

notify pgrst, 'reload schema';

insert into public.applied_migrations (name) values ('20261025180000_owner_locale') on conflict do nothing;
