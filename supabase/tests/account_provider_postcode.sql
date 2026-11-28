\set ON_ERROR_STOP on
begin;
insert into auth.users (id, email) values ('19000000-0000-0000-0000-000000000056', 'provider-postcode-owner@example.test');
insert into auth.users (id, email) values ('19000000-0000-0000-0000-000000000057', 'provider-postcode-other@example.test');
set local role service_role;
do $$
declare
  parcel public.packages;
  changed public.packages;
  done boolean;
begin
  parcel := public.create_account_package('19000000-0000-0000-0000-000000000056', 'RR471035825CH', 'Postcode fixture', 'swiss-post', null, null);
  update public.packages
  set carrier_data = '{"routing":{"provider_input_needed":{"provider":"ParcelsApp","field":"dpdPostcode"},"failures":{"ParcelsApp":{"kind":"input_required"}}}}'::jsonb,
      sync_status = 'waiting'
  where id = parcel.id;

  -- Another account's call finds no parcel, and the ask stays.
  done := public.set_account_package_provider_postcode('19000000-0000-0000-0000-000000000057', parcel.id, '8001');
  if done or (select carrier_data #> '{routing,provider_input_needed}' from public.packages where id = parcel.id) is null then
    raise exception 'Another account answered the parcel''s postcode ask';
  end if;

  -- The owner's postcode is saved where the app's "Add postcode" saves it, and the ask is answered.
  done := public.set_account_package_provider_postcode('19000000-0000-0000-0000-000000000056', parcel.id, ' 8001 ');
  select * into changed from public.packages where id = parcel.id;
  if not done or changed.carrier_data #>> '{universal_input,postcode}' is distinct from '8001'
      or changed.carrier_data #>> '{universal_input,number}' is distinct from 'RR471035825CH'
      or changed.carrier_data #> '{routing,provider_input_needed}' is not null
      or changed.carrier_data #> '{routing,failures,ParcelsApp}' is not null
      or changed.sync_status <> 'pending' or changed.dpd_postcode is not null then
    raise exception 'The owner''s postcode was not saved as the app saves it';
  end if;
  if auth.uid() is not null then raise exception 'The named account outlived the call'; end if;

  begin
    perform public.set_account_package_provider_postcode('19000000-0000-0000-0000-000000000056', parcel.id, 'no digits');
    raise exception 'A postcode without a digit was taken';
  exception when sqlstate '22023' then null;
  end;
  begin
    perform public.set_account_package_provider_postcode(null, parcel.id, '8001');
    raise exception 'A call without an account was taken';
  exception when sqlstate '42501' then null;
  end;
end;
$$;
reset role;

do $$
begin
  if has_function_privilege('authenticated', 'public.set_account_package_provider_postcode(uuid,uuid,text)', 'EXECUTE')
      or has_function_privilege('anon', 'public.set_account_package_provider_postcode(uuid,uuid,text)', 'EXECUTE') then
    raise exception 'A user can give a postcode for another account';
  end if;
end;
$$;
rollback;
