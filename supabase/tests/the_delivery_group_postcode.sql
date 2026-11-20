\set ON_ERROR_STOP on
begin;
insert into auth.users (id, email) values ('19000000-0000-0000-0000-000000000245', 'tdg-postcode-test@example.test');
select set_config('request.jwt.claim.sub', '19000000-0000-0000-0000-000000000245', true);
set local role authenticated;
do $$
declare
  parcel public.packages;
begin
  -- TDG keeps a supplied delivery postcode with the parcel.
  parcel := test_support.add_parcel('TDG00000001', 'TDG fixture', 'the-delivery-group', null, ' 1234 ');
  if parcel.carrier <> 'the-delivery-group' or parcel.dpd_postcode <> '1234' then
    raise exception 'TDG lost its postcode on creation';
  end if;
  -- The postcode is optional: fallback can serve a parcel saved without it.
  parcel := test_support.add_parcel('TDG00000002', 'Without a postcode', 'the-delivery-group');
  if parcel.dpd_postcode is not null then raise exception 'TDG invented a postcode'; end if;
  perform public.change_owned_package_carrier(parcel.id, 'the-delivery-group', null, 'sw1a 1aa');
  if (select dpd_postcode from public.packages where id = parcel.id) <> 'SW1A 1AA' then
    raise exception 'TDG postcode was not saved on a carrier change';
  end if;
  begin
    perform test_support.add_parcel('TDG00000003', 'Malformed postcode', 'the-delivery-group', null, '12/34');
    raise exception 'A malformed TDG postcode was accepted';
  exception when sqlstate '22023' then null;
  end;
  perform public.change_owned_package_carrier(parcel.id, 'dsv');
  if (select dpd_postcode from public.packages where id = parcel.id) is not null then
    raise exception 'The TDG postcode followed the parcel to DSV';
  end if;
  begin
    perform test_support.add_parcel('TDG00000004', 'No digits in postcode', 'the-delivery-group', null, 'ABCDE');
    raise exception 'A postcode with no digit was accepted';
  exception when sqlstate '22023' then null;
  end;
end;
$$;
reset role;
do $$
begin
  begin
    update public.packages set carrier = 'the-delivery-group', dpd_postcode = '12/34'
    where tracking_number = 'TDG00000001';
    raise exception 'The table constraint accepted malformed input';
  exception when check_violation then null;
  end;
end;
$$;
rollback;
