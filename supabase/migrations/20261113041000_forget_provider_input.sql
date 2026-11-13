-- The postcode an owner gave a provider in a field of its own (universal_input) went to every
-- provider before the parcel's own: one the owner types in the carrier's postcode field later,
-- as with a new carrier, was never sent. A new postcode of the parcel's own replaces it. A
-- write that sets the provider postcode itself keeps it, and so does the postcode an account
-- gives its parcel (accountPostcode.ts), which is a guess beside the owner's answer.
create function public.forget_provider_input()
returns trigger
language plpgsql
set search_path = pg_catalog
as $$
begin
  new.carrier_data := new.carrier_data - 'universal_input';
  return new;
end;
$$;

create trigger packages_forget_provider_input
  before update of dpd_postcode
  on public.packages
  for each row
  when (new.dpd_postcode is not null and old.dpd_postcode is distinct from new.dpd_postcode
    and new.carrier_data ? 'universal_input'
    and new.carrier_data->'universal_input' is not distinct from old.carrier_data->'universal_input'
    and new.carrier_data->'account_postcode' is not distinct from old.carrier_data->'account_postcode')
  execute function public.forget_provider_input();

revoke all on function public.forget_provider_input() from public, anon, authenticated;

insert into public.applied_migrations (name) values ('20261113041000_forget_provider_input') on conflict do nothing;
