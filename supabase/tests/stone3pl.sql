\set ON_ERROR_STOP on
begin;

insert into auth.users (id, email) values
  ('19000000-0000-0000-0000-000000000031', 'stone3pl-owner@example.test'),
  ('19000000-0000-0000-0000-000000000032', 'stone3pl-other@example.test');

set local role service_role;
do $$
declare parcel public.packages;
begin
  parcel := public.create_account_package('19000000-0000-0000-0000-000000000031',
    'S900000000000000001', 'Synthetic Stone3PL parcel', 'stone3pl');
  if parcel.carrier <> 'stone3pl' then raise exception 'Stone3PL was lost on account creation'; end if;
  perform set_config('test.stone3pl.parcel_id', parcel.id::text, true);
end;
$$;
reset role;

select set_config('request.jwt.claim.sub', '19000000-0000-0000-0000-000000000031', true);
set local role authenticated;
do $$
declare parcel_id uuid := current_setting('test.stone3pl.parcel_id')::uuid;
begin
  if not public.change_owned_package_carrier(parcel_id, 'unknown')
    or not public.change_owned_package_carrier(parcel_id, 'stone3pl') then
    raise exception 'Owner could not change the carrier to Stone3PL';
  end if;
  if (select carrier from public.packages where id = parcel_id) <> 'stone3pl' then
    raise exception 'Stone3PL was lost after carrier change';
  end if;
  begin
    perform public.change_owned_package_carrier(parcel_id, 'stone3pl', null, '2000');
    raise exception 'Stone3PL incorrectly accepted an unused postcode';
  exception when sqlstate '22023' then null;
  end;
  if has_function_privilege('authenticated', 'public.create_owned_package(text,text,text,text,text)', 'execute')
    or has_function_privilege('authenticated', 'public.create_account_package(uuid,text,text,text,text,text)', 'execute') then
    raise exception 'The migration expanded client package-creation access';
  end if;
end;
$$;
reset role;

select set_config('request.jwt.claim.sub', '19000000-0000-0000-0000-000000000032', true);
set local role authenticated;
do $$
begin
  if public.change_owned_package_carrier(current_setting('test.stone3pl.parcel_id')::uuid, 'unknown') then
    raise exception 'Another account changed the owner''s parcel';
  end if;
end;
$$;
rollback;
