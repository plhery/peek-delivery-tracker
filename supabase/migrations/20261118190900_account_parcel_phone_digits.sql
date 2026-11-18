-- Keep validation in the catalog-managed functions. Their row locks last until the digits are stored.

create function public.create_account_package_with_digits(
  p_user_id uuid, p_tracking_number text, p_label text, p_carrier text, p_tracking_url text, p_dpd_postcode text, p_phone_digits text
) returns public.packages
language plpgsql security definer set search_path = pg_catalog as $$
declare
  parcel public.packages;
begin
  parcel := public.create_account_package(p_user_id, p_tracking_number, p_label, p_carrier, p_tracking_url, p_dpd_postcode);
  if p_phone_digits is not null then
    update public.packages set phone_digits = p_phone_digits where id = parcel.id returning * into parcel;
  end if;
  return parcel;
end;
$$;
revoke all on function public.create_account_package_with_digits(uuid, text, text, text, text, text, text) from public, anon, authenticated;
grant execute on function public.create_account_package_with_digits(uuid, text, text, text, text, text, text) to service_role;

create function public.change_account_package_carrier_with_digits(
  p_user_id uuid, p_package_id uuid, p_carrier text, p_tracking_url text, p_dpd_postcode text, p_phone_digits text
) returns boolean
language plpgsql security definer set search_path = pg_catalog as $$
begin
  if not public.change_account_package_carrier(p_user_id, p_package_id, p_carrier, p_tracking_url, p_dpd_postcode) then return false; end if;
  update public.packages set phone_digits = p_phone_digits where id = p_package_id and phone_digits is distinct from p_phone_digits;
  return true;
end;
$$;
revoke all on function public.change_account_package_carrier_with_digits(uuid, uuid, text, text, text, text) from public, anon, authenticated;
grant execute on function public.change_account_package_carrier_with_digits(uuid, uuid, text, text, text, text) to service_role;

create function public.change_owned_package_carrier_with_digits(
  p_package_id uuid, p_carrier text, p_tracking_url text, p_dpd_postcode text, p_phone_digits text
) returns boolean
language plpgsql security definer set search_path = pg_catalog as $$
begin
  if not public.change_owned_package_carrier(p_package_id, p_carrier, p_tracking_url, p_dpd_postcode) then return false; end if;
  update public.packages set phone_digits = p_phone_digits where id = p_package_id and phone_digits is distinct from p_phone_digits;
  return true;
end;
$$;
revoke all on function public.change_owned_package_carrier_with_digits(uuid, text, text, text, text) from public, anon, authenticated;
grant execute on function public.change_owned_package_carrier_with_digits(uuid, text, text, text, text) to authenticated;

notify pgrst, 'reload schema';
insert into public.applied_migrations (name) values ('20261118190900_account_parcel_phone_digits') on conflict do nothing;
