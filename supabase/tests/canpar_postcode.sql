\set ON_ERROR_STOP on
begin;
insert into auth.users (id, email) values ('19000000-0000-0000-0000-000000000053', 'canpar-postcode-test@example.test');
select set_config('request.jwt.claim.sub', '19000000-0000-0000-0000-000000000053', true);
set local role authenticated;
do $$
declare
  parcel public.packages;
  barcode public.packages;
  other text;
begin
  -- Canpar finds some short barcodes only with their destination postal code.
  parcel := test_support.add_parcel('D0000000000001', 'Canpar fixture', 'canpar', null, ' a9a  9a9 ');
  if parcel.carrier <> 'canpar' or parcel.dpd_postcode <> 'A9A 9A9' then
    raise exception 'Canpar lost its postal code on creation';
  end if;
  if (test_support.add_parcel('D00000002', 'Eight digits', 'canpar')).dpd_postcode is not null then
    raise exception 'Canpar invented a postal code';
  end if;
  begin
    perform test_support.add_parcel('D0000000000003', 'Malformed postal code', 'canpar', null, 'A9A/9A9');
    raise exception 'A malformed Canpar postal code was accepted';
  exception when sqlstate '22023' then null;
  end;
  foreach other in array array['D000000000000000000004', 'P000000005', 'W00000006'] loop
    begin
      perform test_support.add_parcel(other, 'Other shape', 'canpar', null, 'A9A 9A9');
      raise exception 'Canpar took a postal code for % of another shape', other;
    exception when sqlstate '22023' then null;
    end;
  end loop;
  barcode := test_support.add_parcel('D000000000000000000007', 'Full barcode', 'canpar');
  begin
    perform public.change_owned_package_carrier(barcode.id, 'canpar', null, 'A9A 9A9');
    raise exception 'Canpar took a postal code for a full barcode on a carrier change';
  exception when sqlstate '22023' then null;
  end;
  perform public.change_owned_package_carrier(parcel.id, 'canpar', null, null);
  if (select dpd_postcode from public.packages where id = parcel.id) is not null then
    raise exception 'The optional Canpar postal code could not be cleared';
  end if;
  perform public.change_owned_package_carrier(parcel.id, 'canpar', null, 'A9A 9A9');
  if (select dpd_postcode from public.packages where id = parcel.id) <> 'A9A 9A9' then
    raise exception 'The Canpar postal code was not saved on a carrier change';
  end if;
end;
$$;
reset role;
do $$
begin
  insert into public.packages (user_id, tracking_number, label, carrier, dpd_postcode)
  values ('19000000-0000-0000-0000-000000000053', 'D000000000000000000008', 'Plate', 'canpar', 'A9A 9A9');
  raise exception 'The table took a Canpar postal code for a number of another shape';
exception when check_violation then null;
end;
$$;
rollback;
