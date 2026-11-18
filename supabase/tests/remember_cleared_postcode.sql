\set ON_ERROR_STOP on
begin;
insert into auth.users (id, email) values
  ('19000000-0000-0000-0000-000000000061', 'cleared-postcode-owner@example.test');
select set_config('request.jwt.claim.sub', '19000000-0000-0000-0000-000000000061', true);
set local role authenticated;
select set_config('cleared_test.parcel', (test_support.add_parcel('06080000000001', 'Cleared fixture', 'dpd')).id::text, true);
select set_config('cleared_test.other', (test_support.add_parcel('06080000000002', 'Changed fixture', 'dpd')).id::text, true);
do $$
declare
  parcel uuid := current_setting('cleared_test.parcel')::uuid;
  other uuid := current_setting('cleared_test.other')::uuid;
begin
  -- The owner types a postcode, which marks nothing.
  perform public.change_owned_package_carrier(parcel, 'dpd', null, '8000');
  if (select carrier_data ? 'account_postcode' from public.packages where id = parcel) then
    raise exception 'A typed postcode was marked as the account''s';
  end if;
  -- Then clears it: the parcel keeps that it was cleared.
  perform public.change_owned_package_carrier(parcel, 'dpd', null, null);
  if (select dpd_postcode is not null or carrier_data->'account_postcode' is distinct from 'false'::jsonb
      from public.packages where id = parcel) then
    raise exception 'A cleared postcode was not remembered';
  end if;
  -- A postcode typed again is kept as typed, beside the mark.
  perform public.change_owned_package_carrier(parcel, 'dpd', null, '3000');
  if (select dpd_postcode is distinct from '3000' from public.packages where id = parcel) then
    raise exception 'A postcode typed after clearing was not saved';
  end if;
  -- A carrier change that drops the postcode is not a clear.
  perform public.change_owned_package_carrier(other, 'dpd', null, '8000');
  perform public.change_owned_package_carrier(other, 'unknown', null, null);
  if (select carrier_data ? 'account_postcode' from public.packages where id = other) then
    raise exception 'A carrier change was taken for a cleared postcode';
  end if;
end;
$$;
reset role;
do $$
begin
  if has_function_privilege('authenticated', 'public.remember_cleared_postcode()', 'execute') then
    raise exception 'Signed-in users can run the trigger function';
  end if;
end;
$$;
rollback;
