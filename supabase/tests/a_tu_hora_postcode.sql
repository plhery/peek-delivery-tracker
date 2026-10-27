\set ON_ERROR_STOP on
begin;
insert into auth.users (id, email) values ('19000000-0000-0000-0000-000000000052', 'a-tu-hora-postcode-test@example.test');
select set_config('request.jwt.claim.sub', '19000000-0000-0000-0000-000000000052', true);
set local role authenticated;
do $$
declare
  parcel public.packages;
  shipping_label public.packages;
begin
  -- A Tu Hora Express shows a 10- or 16-digit code only with its delivery postcode.
  parcel := test_support.add_parcel('0000009990000017', 'A Tu Hora fixture', 'a-tu-hora-express', null, ' 99999 ');
  if parcel.carrier <> 'a-tu-hora-express' or parcel.dpd_postcode <> '99999' then
    raise exception 'A Tu Hora Express lost its postal code on creation';
  end if;
  if (test_support.add_parcel('9990000018', 'Ten digits', 'a-tu-hora-express', null, '99999')).dpd_postcode <> '99999' then
    raise exception 'A Tu Hora Express lost the postal code of a ten-digit code';
  end if;
  if (test_support.add_parcel('0000009990000019', 'Without a postal code', 'a-tu-hora-express')).dpd_postcode is not null then
    raise exception 'A Tu Hora Express invented a postal code';
  end if;
  begin
    perform test_support.add_parcel('0000009990000020', 'Malformed postal code', 'a-tu-hora-express', null, '99/99');
    raise exception 'A malformed A Tu Hora Express postal code was accepted';
  exception when sqlstate '22023' then null;
  end;
  begin
    perform test_support.add_parcel('000000999000002100199999', 'Label', 'a-tu-hora-express', null, '99999');
    raise exception 'A Tu Hora Express took a postal code for a number of another shape';
  exception when sqlstate '22023' then null;
  end;
  shipping_label := test_support.add_parcel('000000999000002200199999', 'Label', 'a-tu-hora-express');
  begin
    perform public.change_owned_package_carrier(shipping_label.id, 'a-tu-hora-express', null, '99999');
    raise exception 'A Tu Hora Express took a postal code for a number of another shape on a carrier change';
  exception when sqlstate '22023' then null;
  end;
  perform public.change_owned_package_carrier(parcel.id, 'a-tu-hora-express', null, null);
  if (select dpd_postcode from public.packages where id = parcel.id) is not null then
    raise exception 'The optional A Tu Hora Express postal code could not be cleared';
  end if;
  perform public.change_owned_package_carrier(parcel.id, 'a-tu-hora-express', null, '99999');
  if (select dpd_postcode from public.packages where id = parcel.id) <> '99999' then
    raise exception 'The A Tu Hora Express postal code was not saved on a carrier change';
  end if;
end;
$$;
reset role;
do $$
begin
  insert into public.packages (user_id, tracking_number, label, carrier, dpd_postcode)
  values ('19000000-0000-0000-0000-000000000052', '000000999000002300199999', 'Plate', 'a-tu-hora-express', '99999');
  raise exception 'The table took an A Tu Hora Express postal code for a number of another shape';
exception when check_violation then null;
end;
$$;
rollback;
