-- A lookup without an account shares a one-off parcel with lookups that gave the same inputs, but the
-- postcode's country was not one of them: a guest who corrected the country of a postcode from abroad
-- (PostNL takes any country's) joined the parcel of the earlier lookup and was looked up with its country,
-- and the server kept the new country only for a parcel the lookup created, in a write of its own. Both
-- filings now take the country (`p_postcode_country`, none for the carrier's home, as the server sends it),
-- share a parcel only with one whose postcode holds the same country, and store it with the parcel they
-- create (`carrier_data.postcode_country`). create_one_off_parcel builds on
-- 20261116044500_one_underscore_in_tracking_numbers and file_parcel_link_postcode on
-- 20261024070000_parcel_link_postcodes, the last that defined them; only the country changes.
begin;

-- The country a parcel's postcode was given with, as src/lib/postcodeCountry.ts reads it, or null for the carrier's home.
create function private.postcode_country(p_carrier_data jsonb, p_postcode text)
returns text
language sql
immutable
set search_path = ''
as $$
  select case when p_postcode is not null and p_carrier_data->'postcode_country'->>'postcode' = p_postcode
    and p_carrier_data->'postcode_country'->>'country' ~ '^[A-Z]{2}$'
    then p_carrier_data->'postcode_country'->>'country' end;
$$;

-- What a new parcel's carrier_data holds of its postcode's country.
create function private.postcode_country_data(p_postcode text, p_country text)
returns jsonb
language sql
immutable
set search_path = ''
as $$
  select case when p_postcode is not null and p_country is not null
    then jsonb_build_object('postcode_country', jsonb_build_object('postcode', p_postcode, 'country', p_country))
    else '{}'::jsonb end;
$$;

-- The country as given: two capitals, or none.
create function private.given_postcode_country(p_country text)
returns text
language plpgsql
immutable
set search_path = ''
as $$
declare
  normalized text := nullif(upper(btrim(coalesce(p_country, ''))), '');
begin
  if normalized is not null and normalized !~ '^[A-Z]{2}$' then
    raise exception 'Invalid postcode country' using errcode = '22023';
  end if;
  return normalized;
end;
$$;

revoke all on function private.postcode_country(jsonb, text) from public, anon, authenticated;
revoke all on function private.postcode_country_data(text, text) from public, anon, authenticated;
revoke all on function private.given_postcode_country(text) from public, anon, authenticated;

drop function public.create_one_off_parcel(text, text, text, text, text, text);
create function public.create_one_off_parcel(
  p_tracking_number text,
  p_carrier text,
  p_tracking_url text,
  p_dpd_postcode text,
  p_owner_key_hash text,
  p_phone_digits text default null,
  p_postcode_country text default null
)
returns jsonb
language plpgsql
security definer
set search_path = ''
as $$
declare
  normalized_tracking text := upper(regexp_replace(coalesce(p_tracking_number, ''), '[[:space:].-]', '', 'g'));
  normalized_url text := nullif(btrim(coalesce(p_tracking_url, '')), '');
  normalized_postcode text := nullif(btrim(coalesce(p_dpd_postcode, '')), '');
  normalized_digits text := nullif(btrim(coalesce(p_phone_digits, '')), '');
  normalized_country text;
  target_id uuid;
  link_id text;
  created boolean := false;
begin
  if char_length(normalized_tracking) not between 4 and 40
      or (normalized_tracking !~ '^[A-Z0-9]+(_[A-Z0-9]+)?$' and normalized_tracking !~ '^\d{4}/\d{8}$')
      or (normalized_tracking !~ '[0-9]' and normalized_tracking !~ '^[A-Z]{6,}$')
      or p_carrier is null
      or p_owner_key_hash is null then
    raise exception 'Invalid one-off parcel' using errcode = '22023';
  end if;
  normalized_country := case when normalized_postcode is not null then private.given_postcode_country(p_postcode_country) end;

  perform private.lock_one_off_number(normalized_tracking);
  select package.id into target_id
  from public.packages as package
  where package.one_off
    and package.user_id is null
    and package.tracking_number = normalized_tracking
    and (package.carrier = p_carrier or package.carrier_data->>'auto_changed_from' = p_carrier)
    and package.tracking_url is not distinct from normalized_url
    and package.dpd_postcode is not distinct from normalized_postcode
    and private.postcode_country(package.carrier_data, package.dpd_postcode) is not distinct from normalized_country
    and package.phone_digits is not distinct from normalized_digits
  order by package.created_at desc
  limit 1;

  if target_id is null then
    insert into public.packages (user_id, one_off, tracking_number, label, carrier, tracking_url, dpd_postcode, phone_digits, carrier_data)
    values (null, true, normalized_tracking, '', p_carrier, normalized_url, normalized_postcode, normalized_digits,
      private.postcode_country_data(normalized_postcode, normalized_country))
    returning id into target_id;
    created := true;
  end if;

  insert into public.parcel_links (package_id, owner_key_hash)
  values (target_id, p_owner_key_hash)
  returning id into link_id;

  return public.public_parcel(link_id, p_owner_key_hash) || jsonb_build_object('created', created);
end;
$$;
revoke all on function public.create_one_off_parcel(text, text, text, text, text, text, text) from public, anon, authenticated;
grant execute on function public.create_one_off_parcel(text, text, text, text, text, text, text) to service_role;

drop function public.file_parcel_link_postcode(text, text, text, text);
create function public.file_parcel_link_postcode(
  p_link_id text,
  p_owner_key_hash text,
  p_carrier text,
  p_dpd_postcode text,
  p_postcode_country text default null
)
returns jsonb
language plpgsql
security definer
set search_path = ''
as $$
declare
  normalized_postcode text := nullif(btrim(coalesce(p_dpd_postcode, '')), '');
  normalized_country text := private.given_postcode_country(p_postcode_country);
  link public.parcel_links;
  previous public.packages;
  target_id uuid;
  created boolean := false;
  orphaned boolean;
begin
  if p_owner_key_hash is null or p_carrier is null or normalized_postcode is null then
    raise exception 'Invalid postcode filing' using errcode = '22023';
  end if;
  select parcel.tracking_number into previous.tracking_number
  from public.parcel_links as stored
  join public.packages as parcel on parcel.id = stored.package_id
  where stored.id = p_link_id and stored.owner_key_hash = p_owner_key_hash;
  if not found then return null; end if;

  -- Under the number's lock, as a lookup and a forgetting take it.
  perform private.lock_one_off_number(previous.tracking_number);
  select * into link from public.parcel_links
  where id = p_link_id and owner_key_hash = p_owner_key_hash
  for update;
  if not found or public.parcel_link_forget_at(link) <= now() then return null; end if;
  select * into previous from public.packages where id = link.package_id;
  if not private.waits_for_link_postcode(previous, p_carrier) then
    return jsonb_build_object('asked', false);
  end if;

  select package.id into target_id
  from public.packages as package
  where package.one_off
    and package.user_id is null
    and package.tracking_number = previous.tracking_number
    and (package.carrier = p_carrier or package.carrier_data->>'auto_changed_from' = p_carrier)
    and package.tracking_url is null
    and package.dpd_postcode = normalized_postcode
    and private.postcode_country(package.carrier_data, package.dpd_postcode) is not distinct from normalized_country
  order by package.created_at desc
  limit 1;

  if target_id is null then
    insert into public.packages (user_id, one_off, tracking_number, label, carrier, tracking_url, dpd_postcode, carrier_data)
    values (null, true, previous.tracking_number, '', p_carrier, null, normalized_postcode,
      private.postcode_country_data(normalized_postcode, normalized_country))
    returning id into target_id;
    created := true;
  end if;

  update public.parcel_links set package_id = target_id where id = link.id;
  delete from public.parcel_link_postcode_attempts where link_id = link.id;
  orphaned := private.forget_orphaned_one_off(previous.id);

  return public.public_parcel(link.id, p_owner_key_hash)
    || jsonb_build_object('created', created, 'previous_forgotten', orphaned);
end;
$$;
revoke all on function public.file_parcel_link_postcode(text, text, text, text, text) from public, anon, authenticated;
grant execute on function public.file_parcel_link_postcode(text, text, text, text, text) to service_role;

notify pgrst, 'reload schema';

insert into public.applied_migrations (name) values ('20261119010000_guest_postcode_country') on conflict do nothing;

commit;
