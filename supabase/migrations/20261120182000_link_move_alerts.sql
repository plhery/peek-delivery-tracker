-- A lookup whose owner changes its carrier or files its postcode can join a parcel another lookup already follows.
-- The scans that parcel already has are on the page as it opens: they are not news to the link's alerts, as a kept
-- link's copied scans are not (claim_parcel_link). Built on 20261119040000_change_parcel_link_carrier and
-- 20261119010000_guest_postcode_country, the last that defined them.

create or replace function public.change_parcel_link_carrier(
  p_link_id text,
  p_owner_key_hash text,
  p_carrier text,
  p_tracking_url text,
  p_dpd_postcode text,
  p_phone_digits text default null,
  p_postcode_country text default null
)
returns jsonb
language plpgsql
security definer
set search_path = ''
as $$
declare
  normalized_url text := nullif(btrim(coalesce(p_tracking_url, '')), '');
  normalized_postcode text := nullif(btrim(coalesce(p_dpd_postcode, '')), '');
  normalized_digits text := nullif(btrim(coalesce(p_phone_digits, '')), '');
  normalized_country text;
  link public.parcel_links;
  previous public.packages;
  target_id uuid;
  created boolean := false;
  orphaned boolean := false;
begin
  if p_owner_key_hash is null or p_carrier is null then
    raise exception 'Invalid carrier change' using errcode = '22023';
  end if;
  normalized_country := case when normalized_postcode is not null then private.given_postcode_country(p_postcode_country) end;
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
  if not previous.one_off or previous.user_id is not null then return null; end if;

  select package.id into target_id
  from public.packages as package
  where package.one_off
    and package.user_id is null
    and package.tracking_number = previous.tracking_number
    and (package.carrier = p_carrier or package.carrier_data->>'auto_changed_from' = p_carrier)
    and package.tracking_url is not distinct from normalized_url
    and package.dpd_postcode is not distinct from normalized_postcode
    and private.postcode_country(package.carrier_data, package.dpd_postcode) is not distinct from normalized_country
    and package.phone_digits is not distinct from normalized_digits
  -- The parcel the link already has, when it is one of them.
  order by package.id = previous.id desc, package.created_at desc
  limit 1;

  if target_id is null then
    insert into public.packages (user_id, one_off, tracking_number, label, carrier, tracking_url, dpd_postcode, phone_digits, carrier_data)
    values (null, true, previous.tracking_number, '', p_carrier, normalized_url, normalized_postcode, normalized_digits,
      private.postcode_country_data(normalized_postcode, normalized_country))
    returning id into target_id;
    created := true;
  end if;

  if target_id <> previous.id then
    update public.parcel_links set package_id = target_id where id = link.id;
    -- The scans the parcel already has are not news to the link's alerts.
    update public.parcel_link_alerts set created_at = now() where link_id = link.id;
    delete from public.parcel_link_postcode_attempts where link_id = link.id;
    orphaned := private.forget_orphaned_one_off(previous.id);
  end if;

  return public.public_parcel(link.id, p_owner_key_hash)
    || jsonb_build_object('created', created, 'changed', target_id <> previous.id, 'previous_forgotten', orphaned);
end;
$$;

create or replace function public.file_parcel_link_postcode(
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
  -- The scans the parcel already has are not news to the link's alerts.
  update public.parcel_link_alerts set created_at = now() where link_id = link.id;
  delete from public.parcel_link_postcode_attempts where link_id = link.id;
  orphaned := private.forget_orphaned_one_off(previous.id);

  return public.public_parcel(link.id, p_owner_key_hash)
    || jsonb_build_object('created', created, 'previous_forgotten', orphaned);
end;
$$;

notify pgrst, 'reload schema';

insert into public.applied_migrations (name) values ('20261120182000_link_move_alerts') on conflict do nothing;
