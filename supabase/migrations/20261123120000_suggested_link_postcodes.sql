-- A lookup filed under no carrier can be offered a carrier its number points to
-- that can't be asked about it without the delivery postcode
-- (routing.input_suggested: Mondial Relay's 8-digit shipments). Its owner adds
-- the postcode from the link as for one a carrier asks for: the carrier is asked
-- first, a postcode it refuses counts against the link, and one it accepts moves
-- the link to the lookup with that carrier and postcode.
--
-- Apply this before deploying the server that shows the suggestion on links.


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
      and p_package.current_stage not in ('delivered', 'returned')
      and (
        (p_package.carrier <> p_carrier or nullif(btrim(coalesce(p_package.dpd_postcode, '')), '') is null)
          and p_package.carrier_data #>> '{routing,input_needed,carrier}' = p_carrier
          and p_package.carrier_data #>> '{routing,input_needed,field}' = 'dpdPostcode'
        or p_package.carrier = 'unknown'
          and p_package.carrier_data #> '{routing,input_needed}' is null
          and p_package.carrier_data #>> '{routing,input_suggested,carrier}' = p_carrier
          and p_package.carrier_data #>> '{routing,input_suggested,field}' = 'dpdPostcode'
      ),
    false
  );
$$;

revoke all on function private.waits_for_link_postcode(public.packages, text) from public, anon, authenticated;

insert into public.applied_migrations (name) values ('20261123120000_suggested_link_postcodes') on conflict do nothing;
