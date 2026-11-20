\set ON_ERROR_STOP on
begin;
set local role service_role;

-- A lookup's owner changes its carrier on the same link.
do $$
declare
  key_a constant text := repeat('a', 64);
  key_b constant text := repeat('b', 64);
  first jsonb;
  second jsonb;
  moved jsonb;
  shared jsonb;
  again jsonb;
  postcode jsonb;
  link_a text;
  link_b text;
  failed boolean := false;
begin
  first := public.create_one_off_parcel('3SABCD000000021', 'unknown', null, null, key_a);
  link_a := first#>>'{link,id}';
  update public.parcel_links set show_number = true where id = link_a;

  moved := public.change_parcel_link_carrier(link_a, key_a, 'dhl', null, null);
  if moved#>>'{link,id}' <> link_a or moved#>>'{package,carrier}' <> 'dhl' or moved->'created' <> 'true'
      or moved->'changed' <> 'true' or moved->'previous_forgotten' <> 'true' then
    raise exception 'A link did not move to a new parcel with the carrier: %', moved;
  end if;
  if exists (select 1 from public.packages where id = (first#>>'{package,id}')::uuid) then
    raise exception 'The parcel no link reaches was kept';
  end if;
  if not (select show_number from public.parcel_links where id = link_a) then
    raise exception 'The link lost what it shows';
  end if;

  -- Another lookup of the number with that carrier shares the parcel; moving on leaves it to that lookup.
  second := public.create_one_off_parcel('3SABCD000000021', 'dhl', null, null, key_b);
  link_b := second#>>'{link,id}';
  if second#>>'{package,id}' <> moved#>>'{package,id}' then
    raise exception 'The lookups did not share the parcel';
  end if;
  shared := public.change_parcel_link_carrier(link_a, key_a, 'ups', null, null);
  if shared->'changed' <> 'true' or shared->'previous_forgotten' <> 'false'
      or not exists (select 1 from public.packages where id = (moved#>>'{package,id}')::uuid) then
    raise exception 'Moving on took the parcel another lookup reaches: %', shared;
  end if;
  -- Back to that carrier, the link joins it.
  again := public.change_parcel_link_carrier(link_a, key_a, 'dhl', null, null);
  if again->'created' <> 'false' or again#>>'{package,id}' <> moved#>>'{package,id}' then
    raise exception 'The link did not join the parcel of its number with that carrier: %', again;
  end if;
  -- The same carrier and inputs change nothing.
  again := public.change_parcel_link_carrier(link_a, key_a, 'dhl', null, '  ');
  if again->'changed' <> 'false' or again#>>'{package,id}' <> moved#>>'{package,id}' then
    raise exception 'The same carrier moved the link: %', again;
  end if;
  -- Inputs make another parcel, with the postcode's country.
  postcode := public.change_parcel_link_carrier(link_a, key_a, 'spring-gds', null, '12345', null, 'de');
  if postcode->'created' <> 'true' or postcode#>>'{package,dpd_postcode}' is distinct from '12345'
      or (select carrier_data->'postcode_country' from public.packages where id = (postcode#>>'{package,id}')::uuid)
        is distinct from '{"postcode":"12345","country":"DE"}'::jsonb then
    raise exception 'The postcode was not filed with its country: %', postcode;
  end if;

  -- Only the owner key changes a link.
  if public.change_parcel_link_carrier(link_a, key_b, 'ups', null, null) is not null
      or public.change_parcel_link_carrier('k7Qm2xHd9RtW', key_a, 'ups', null, null) is not null then
    raise exception 'A link changed without its owner key';
  end if;
  begin
    perform public.change_parcel_link_carrier(link_a, key_a, 'spring-gds', null, '12345', null, 'DEU');
  exception when sqlstate '22023' then failed := true;
  end;
  if not failed then raise exception 'A malformed country was taken'; end if;
end;
$$;

-- A lookup moved onto a parcel another lookup follows takes none of that parcel's scans as news to its alert.
do $$
declare
  key_a constant text := repeat('c', 64);
  key_b constant text := repeat('d', 64);
  a jsonb;
  b jsonb;
  scan uuid;
begin
  a := public.create_one_off_parcel('3SALERT000042', 'ups', null, null, key_a);
  perform public.add_parcel_link_alert(a#>>'{link,id}', key_a, 'https://push.example/a', repeat('p', 87), repeat('q', 22), 'en', 'important');
  update public.parcel_link_alerts set created_at = now() - interval '3 hours' where link_id = a#>>'{link,id}';
  b := public.create_one_off_parcel('3SALERT000042', 'dhl', null, null, key_b);
  update public.packages set created_at = now() - interval '3 days' where id = (b#>>'{package,id}')::uuid;
  insert into public.tracking_events (package_id, stage, description, occurred_at, provider_event_id, created_at)
  values ((b#>>'{package,id}')::uuid, 'out_for_delivery', 'Out for delivery', now() - interval '2 hours', 'dhl:alert-1',
    now() - interval '1 hour')
  returning id into scan;
  a := public.change_parcel_link_carrier(a#>>'{link,id}', key_a, 'dhl', null, null);
  if a#>>'{package,id}' <> b#>>'{package,id}' then raise exception 'The link did not join the followed parcel: %', a; end if;
  if exists (select 1 from public.pending_parcel_link_alerts where link_id = a#>>'{link,id}' and event_id = scan) then
    raise exception 'A moved link''s alert takes a scan the parcel had before the move as news';
  end if;
end;
$$;
reset role;

do $$
begin
  if has_function_privilege('anon', 'public.change_parcel_link_carrier(text,text,text,text,text,text,text)', 'EXECUTE')
      or has_function_privilege('authenticated', 'public.change_parcel_link_carrier(text,text,text,text,text,text,text)', 'EXECUTE') then
    raise exception 'Clients may change a link''s carrier';
  end if;
end;
$$;
rollback;
