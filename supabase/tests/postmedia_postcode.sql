\set ON_ERROR_STOP on
begin;
insert into auth.users (id, email) values ('19000000-0000-0000-0000-000000000048', 'postmedia-postcode-test@example.test');
select set_config('request.jwt.claim.sub', '19000000-0000-0000-0000-000000000048', true);
set local role authenticated;
do $$
declare
  parcel public.packages;
begin
  -- Postmedia Parcel Services shows a parcel only with its delivery postal code.
  parcel := test_support.add_parcel('BNI999999980', 'Postmedia fixture', 'postmedia-parcel-services', null, ' x0x  0x0 ');
  if parcel.carrier <> 'postmedia-parcel-services' or parcel.dpd_postcode <> 'X0X 0X0' then
    raise exception 'Postmedia lost its postal code on creation';
  end if;
  begin
    perform test_support.add_parcel('BNI000000002', 'Without a postal code', 'postmedia-parcel-services');
    raise exception 'Postmedia was accepted without a postal code';
  exception when sqlstate '22023' then null;
  end;
  begin
    perform test_support.add_parcel('BNI000000003', 'Malformed postal code', 'postmedia-parcel-services', null, 'X0X/0X0');
    raise exception 'A malformed Postmedia postal code was accepted';
  exception when sqlstate '22023' then null;
  end;
  begin
    perform public.change_owned_package_carrier(parcel.id, 'postmedia-parcel-services', null, null);
    raise exception 'Postmedia lost its postal code on a carrier change';
  exception when sqlstate '22023' then null;
  end;
  perform public.change_owned_package_carrier(parcel.id, 'purolator');
  if (select dpd_postcode from public.packages where id = parcel.id) is not null then
    raise exception 'The Postmedia postal code followed the parcel to Purolator';
  end if;
  perform public.change_owned_package_carrier(parcel.id, 'postmedia-parcel-services', null, 'x0x0x0');
  if (select dpd_postcode from public.packages where id = parcel.id) <> 'X0X0X0' then
    raise exception 'The Postmedia postal code was not saved on a carrier change';
  end if;
end;
$$;
reset role;
-- A Postmedia parcel stored before the postal code was asked for keeps none.
insert into public.packages (user_id, tracking_number, label, carrier)
values ('19000000-0000-0000-0000-000000000048', 'BNI000000004', 'Stored earlier', 'postmedia-parcel-services');
rollback;
