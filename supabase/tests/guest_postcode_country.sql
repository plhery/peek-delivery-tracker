\set ON_ERROR_STOP on
begin;
set local role service_role;

-- A lookup without an account shares a parcel only with lookups whose postcode is from the same country.
do $$
declare
  key_a constant text := repeat('a', 64);
  key_b constant text := repeat('b', 64);
  key_c constant text := repeat('c', 64);
  german jsonb;
  french jsonb;
  again jsonb;
  home jsonb;
  failed boolean := false;
begin
  german := public.create_one_off_parcel('3SABCD000000011', 'spring-gds', null, '12345', key_a, null, 'DE');
  if german->'created' <> 'true' or (select carrier_data->'postcode_country' from public.packages
      where id = (german#>>'{package,id}')::uuid) is distinct from '{"postcode":"12345","country":"DE"}'::jsonb then
    raise exception 'A lookup did not keep its postcode''s country with the parcel: %', german;
  end if;
  -- The same postcode from another country is another address.
  french := public.create_one_off_parcel('3SABCD000000011', 'spring-gds', null, '12345', key_b, null, 'FR');
  if french->'created' <> 'true' or french#>>'{package,id}' = german#>>'{package,id}' then
    raise exception 'A postcode from another country joined the first lookup''s parcel';
  end if;
  again := public.create_one_off_parcel('3SABCD000000011', 'spring-gds', null, '12345', key_c, null, ' de ');
  if again->'created' <> 'false' or again#>>'{package,id}' <> german#>>'{package,id}' then
    raise exception 'The same postcode from the same country did not share the parcel';
  end if;
  -- A postcode from the carrier's home goes without a country, and is another parcel again.
  home := public.create_one_off_parcel('3SABCD000000011', 'spring-gds', null, '12345', key_c);
  if home->'created' <> 'true' or (select carrier_data ? 'postcode_country' from public.packages where id = (home#>>'{package,id}')::uuid) then
    raise exception 'A home postcode was given a country';
  end if;
  begin
    perform public.create_one_off_parcel('3SABCD000000011', 'spring-gds', null, '12345', key_c, null, 'DEU');
  exception when sqlstate '22023' then failed := true;
  end;
  if not failed then raise exception 'A malformed country was taken'; end if;
end;
$$;

-- Filing a postcode through a link does the same.
do $$
declare
  key_a constant text := repeat('d', 64);
  key_b constant text := repeat('e', 64);
  key_c constant text := repeat('f', 64);
  links text[] := '{}';
  answer jsonb;
  key text;
  german jsonb;
  french jsonb;
  again jsonb;
begin
  foreach key in array array[key_a, key_b, key_c] loop
    answer := public.create_one_off_parcel('3SABCD000000012', 'unknown', null, null, key);
    links := links || (answer#>>'{link,id}');
  end loop;
  update public.packages set carrier_data = '{"routing":{"input_needed":{"carrier":"spring-gds","field":"dpdPostcode"}}}'
  where id = (answer#>>'{package,id}')::uuid;
  german := public.file_parcel_link_postcode(links[1], key_a, 'spring-gds', '12345', 'DE');
  if german->'created' <> 'true' or (select carrier_data->'postcode_country' from public.packages
      where id = (german#>>'{package,id}')::uuid) is distinct from '{"postcode":"12345","country":"DE"}'::jsonb then
    raise exception 'A filed postcode did not keep its country: %', german;
  end if;
  french := public.file_parcel_link_postcode(links[2], key_b, 'spring-gds', '12345', 'FR');
  if french->'created' <> 'true' or french#>>'{package,id}' = german#>>'{package,id}' then
    raise exception 'A filed postcode from another country joined the first filing''s parcel';
  end if;
  again := public.file_parcel_link_postcode(links[3], key_c, 'spring-gds', '12345', 'DE');
  if again->'created' <> 'false' or again#>>'{package,id}' <> german#>>'{package,id}' then
    raise exception 'A filed postcode from the same country did not share the parcel';
  end if;
end;
$$;
reset role;

do $$
declare
  service_function text;
begin
  foreach service_function in array array[
    'public.create_one_off_parcel(text,text,text,text,text,text,text)',
    'public.file_parcel_link_postcode(text,text,text,text,text)',
    'private.postcode_country(jsonb,text)', 'private.postcode_country_data(text,text)', 'private.given_postcode_country(text)'
  ] loop
    if has_function_privilege('anon', service_function, 'EXECUTE') or has_function_privilege('authenticated', service_function, 'EXECUTE') then
      raise exception 'Clients may execute %', service_function;
    end if;
  end loop;
end;
$$;
rollback;
