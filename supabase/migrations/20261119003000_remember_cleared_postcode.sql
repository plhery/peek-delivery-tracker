-- A postcode the owner typed and then cleared was filled again: only a postcode the account gave
-- the parcel (accountPostcode.ts) marked it (`account_postcode`), so the next check gave the
-- emptied field another parcel's postcode. Clearing the parcel's postcode under the same carrier
-- and number now marks it too (`account_postcode` false): nothing fills it again, and the
-- carrier a handoff goes to is not given the account's postcode in its place.

create function public.remember_cleared_postcode()
returns trigger
language plpgsql
set search_path = pg_catalog
as $$
begin
  new.carrier_data := coalesce(new.carrier_data, '{}'::jsonb) || jsonb_build_object('account_postcode', false);
  return new;
end;
$$;

create trigger packages_remember_cleared_postcode
  before update of dpd_postcode
  on public.packages
  for each row
  when (old.dpd_postcode is not null and new.dpd_postcode is null
    and old.carrier = new.carrier and old.tracking_number = new.tracking_number)
  execute function public.remember_cleared_postcode();

revoke all on function public.remember_cleared_postcode() from public, anon, authenticated;

insert into public.applied_migrations (name) values ('20261119003000_remember_cleared_postcode') on conflict do nothing;

