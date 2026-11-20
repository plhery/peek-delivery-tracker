-- A lookup's own carrier can ask for the delivery postcode too (PostNL's for a
-- 3S barcode sent within the Netherlands), when the lookup has none. Its owner
-- adds it from the link as for another carrier: the link moves to the lookup
-- with that carrier and postcode.
--
-- Apply this before deploying the server that shows the request on links.


create or replace function private.waits_for_link_postcode(p_package public.packages, p_carrier text)
returns boolean
language sql
stable
security definer
set search_path = ''
as $$
  select coalesce(
    p_package.one_off
      and p_package.user_id is null
      and (p_package.carrier <> p_carrier or nullif(btrim(coalesce(p_package.dpd_postcode, '')), '') is null)
      and p_package.current_stage not in ('delivered', 'returned')
      and p_package.carrier_data #>> '{routing,input_needed,carrier}' = p_carrier
      and p_package.carrier_data #>> '{routing,input_needed,field}' = 'dpdPostcode',
    false
  );
$$;

revoke all on function private.waits_for_link_postcode(public.packages, text) from public, anon, authenticated;

insert into public.applied_migrations (name) values ('20261024230000_own_carrier_link_postcodes') on conflict do nothing;

