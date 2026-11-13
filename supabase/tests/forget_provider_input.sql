\set ON_ERROR_STOP on
begin;
insert into auth.users (id, email) values
  ('19000000-0000-0000-0000-000000000051', 'provider-input-owner@example.test');
select set_config('request.jwt.claim.sub', '19000000-0000-0000-0000-000000000051', true);
set local role authenticated;
select set_config('input_test.parcel', (test_support.add_parcel('3SABCD000000001', 'Input fixture', 'unknown')).id::text, true);
do $$
declare
  parcel uuid := current_setting('input_test.parcel')::uuid;
begin
  -- The owner answers a provider's request in the field of its own.
  if not public.set_owned_package_provider_postcode(parcel, '1234 AB') then raise exception 'Provider postcode was not saved'; end if;
  -- A carrier change without a postcode keeps the answer.
  perform public.change_owned_package_carrier(parcel, 'spring-gds');
  if (select carrier_data#>>'{universal_input,postcode}' from public.packages where id = parcel) is distinct from '1234 AB' then
    raise exception 'A carrier change without a postcode dropped the provider postcode';
  end if;
  -- A postcode in the carrier's own field replaces it: every provider receives that one.
  perform public.change_owned_package_carrier(parcel, 'spring-gds', null, '5678 CD');
  if exists (select 1 from public.packages where id = parcel and (dpd_postcode is distinct from '5678 CD' or carrier_data ? 'universal_input')) then
    raise exception 'The provider postcode outlived the postcode typed in the carrier''s field';
  end if;
  -- A provider postcode given afterwards is kept beside it, and the parcel's same postcode saved again keeps it.
  if not public.set_owned_package_provider_postcode(parcel, '1234 AB') then raise exception 'Provider postcode was not saved again'; end if;
  perform public.change_owned_package_carrier(parcel, 'spring-gds', null, '5678 CD');
  if (select carrier_data#>>'{universal_input,postcode}' from public.packages where id = parcel) is distinct from '1234 AB' then
    raise exception 'The same postcode saved again dropped the provider postcode';
  end if;
end;
$$;
reset role;
do $$
declare
  parcel uuid := current_setting('input_test.parcel')::uuid;
begin
  -- A write that sets both keeps the provider postcode it sets.
  update public.packages set dpd_postcode = '9999 ZZ',
    carrier_data = carrier_data || '{"universal_input":{"number":"3SABCD000000001","postcode":"4321 BA"}}'::jsonb where id = parcel;
  if (select carrier_data#>>'{universal_input,postcode}' from public.packages where id = parcel) is distinct from '4321 BA' then
    raise exception 'A write that set the provider postcode lost it';
  end if;
  -- The postcode an account gives its parcel keeps the owner's answer.
  update public.packages set dpd_postcode = null, carrier_data = carrier_data - 'account_postcode' where id = parcel;
  update public.packages set dpd_postcode = '1111 AA', carrier_data = carrier_data || '{"account_postcode":true}'::jsonb where id = parcel;
  if (select carrier_data#>>'{universal_input,postcode}' from public.packages where id = parcel) is distinct from '4321 BA' then
    raise exception 'The account''s postcode replaced the owner''s answer';
  end if;
  if has_function_privilege('authenticated', 'public.forget_provider_input()', 'execute') then
    raise exception 'Signed-in users can run the trigger function';
  end if;
end;
$$;
rollback;
