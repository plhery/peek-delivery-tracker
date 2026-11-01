-- The open, unarchived one-off parcels without an account filed under one of `p_carriers`
-- (no carrier, or one with no adapter of its own) and added since `p_added_since`: what a
-- scheduled run re-detects. The carriers come in the request body, not the URL, which the
-- catalog had outgrown.

create or replace function public.unfiled_one_off_packages(p_added_since timestamptz, p_carriers text[])
returns setof public.packages
language sql
stable
set search_path = ''
as $$
  select package.*
  from public.packages as package
  where package.one_off
    and package.user_id is null
    and package.archived_at is null
    and package.carrier = any(p_carriers)
    and package.current_stage not in ('delivered', 'returned')
    and package.created_at >= p_added_since
  order by package.last_synced_at asc nulls first, package.created_at asc;
$$;

revoke all on function public.unfiled_one_off_packages(timestamptz, text[]) from public, anon, authenticated;
grant execute on function public.unfiled_one_off_packages(timestamptz, text[]) to service_role;

notify pgrst, 'reload schema';

insert into public.applied_migrations (name) values ('20261101204000_unfiled_one_off_packages') on conflict do nothing;
