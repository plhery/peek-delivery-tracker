\set ON_ERROR_STOP on
begin;
insert into auth.users (id, email) values ('19000000-0000-0000-0000-000000000051', 'postnl-postcode-test@example.test');
select set_config('request.jwt.claim.sub', '19000000-0000-0000-0000-000000000051', true);
set local role authenticated;
do $$
declare
  parcel public.packages;
  letter public.packages;
begin
  -- PostNL shows a 3S parcel sent within the Netherlands only with its delivery postcode.
  parcel := test_support.add_parcel('3SABCD000000001', 'PostNL fixture', 'spring-gds', null, ' 1234  ab ');
  if parcel.carrier <> 'spring-gds' or parcel.dpd_postcode <> '1234 AB' then
    raise exception 'PostNL lost its postal code on creation';
  end if;
  if (test_support.add_parcel('3SABCD0000002', 'Thirteen characters', 'spring-gds', null, '1234AB')).dpd_postcode <> '1234AB' then
    raise exception 'PostNL lost the postal code of a thirteen-character barcode';
  end if;
  if (test_support.add_parcel('3SABCD000000003', 'Without a postal code', 'spring-gds')).dpd_postcode is not null then
    raise exception 'PostNL invented a postal code';
  end if;
  begin
    perform test_support.add_parcel('3SABCD000000004', 'Malformed postal code', 'spring-gds', null, '12/34');
    raise exception 'A malformed PostNL postal code was accepted';
  exception when sqlstate '22023' then null;
  end;
  -- A postal item from abroad (two letters, nine digits, two letters) shows on the same tracker.
  if (test_support.add_parcel('LA000000005NL', 'Postal item', 'spring-gds', null, '1234 AB')).dpd_postcode <> '1234 AB' then
    raise exception 'PostNL lost the postal code of a postal item from abroad';
  end if;
  letter := test_support.add_parcel('LA000000006NL', 'Letter', 'spring-gds');
  perform public.change_owned_package_carrier(letter.id, 'spring-gds', null, '1234 AB');
  if (select dpd_postcode from public.packages where id = letter.id) <> '1234 AB' then
    raise exception 'The postal code of a postal item from abroad was not saved on a carrier change';
  end if;
  begin
    perform test_support.add_parcel('3SABCD0000000008', 'Sixteen characters', 'spring-gds', null, '1234 AB');
    raise exception 'PostNL took a postal code for a number of another shape';
  exception when sqlstate '22023' then null;
  end;
  letter := test_support.add_parcel('3SABCD0000000009', 'Sixteen characters', 'spring-gds');
  begin
    perform public.change_owned_package_carrier(letter.id, 'spring-gds', null, '1234 AB');
    raise exception 'PostNL took a postal code for a number of another shape on a carrier change';
  exception when sqlstate '22023' then null;
  end;
  perform public.change_owned_package_carrier(parcel.id, 'spring-gds', null, null);
  if (select dpd_postcode from public.packages where id = parcel.id) is not null then
    raise exception 'The optional PostNL postal code could not be cleared';
  end if;
  perform public.change_owned_package_carrier(parcel.id, 'spring-gds', null, '1234 AB');
  if (select dpd_postcode from public.packages where id = parcel.id) <> '1234 AB' then
    raise exception 'The PostNL postal code was not saved on a carrier change';
  end if;
end;
$$;
reset role;
do $$
begin
  insert into public.packages (user_id, tracking_number, label, carrier, dpd_postcode)
  values ('19000000-0000-0000-0000-000000000051', '3SABCD0000000007', 'Plate', 'spring-gds', '1234 AB');
  raise exception 'The table took a PostNL postal code for a number of another shape';
exception when check_violation then null;
end;
$$;
rollback;
