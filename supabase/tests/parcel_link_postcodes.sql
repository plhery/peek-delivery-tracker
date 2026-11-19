\set ON_ERROR_STOP on
begin;

-- The tries and the functions behind them belong to the server.
do $$
declare
  role_name text;
  privilege text;
  service_function text;
begin
  foreach role_name in array array['anon', 'authenticated'] loop
    foreach privilege in array array['SELECT', 'INSERT', 'UPDATE', 'DELETE'] loop
      if has_table_privilege(role_name, 'public.parcel_link_postcode_attempts', privilege) then
        raise exception '% may % postcode tries', role_name, privilege;
      end if;
    end loop;
    foreach service_function in array array[
      'public.claim_parcel_link_postcode(text,text,text,integer)',
      'public.release_parcel_link_postcode(uuid)',
      'public.file_parcel_link_postcode(text,text,text,text,text)',
      'private.waits_for_link_postcode(public.packages,text)'
    ] loop
      if has_function_privilege(role_name, service_function, 'EXECUTE') then
        raise exception '% may execute %', role_name, service_function;
      end if;
    end loop;
  end loop;
end;
$$;

set local role service_role;

do $$
declare
  key_a constant text := repeat('a', 64);
  key_b constant text := repeat('b', 64);
  first jsonb;
  second jsonb;
  link_a text;
  link_b text;
  thin uuid;
  claim jsonb;
  attempt uuid;
  filed jsonb;
  again jsonb;
  dhl_package uuid;
begin
  -- Two lookups share the parcel the providers serve; DHL holds it and asks for the postcode.
  first := public.create_one_off_parcel('990000000001', 'unknown', null, null, key_a);
  second := public.create_one_off_parcel('990000000001', 'unknown', null, null, key_b);
  link_a := first#>>'{link,id}';
  link_b := second#>>'{link,id}';
  thin := (first#>>'{package,id}')::uuid;
  if (second#>>'{package,id}')::uuid <> thin then raise exception 'The two lookups do not share a parcel'; end if;

  if public.claim_parcel_link_postcode(link_a, key_a, 'dhl', 5) <> '{"asked": false}' then
    raise exception 'A lookup that waits for no postcode took a try';
  end if;
  update public.packages
  set carrier_data = '{"routing":{"input_needed":{"carrier":"dhl","field":"dpdPostcode"}}}'
  where id = thin;

  if public.claim_parcel_link_postcode(link_a, key_b, 'dhl', 5) is not null
      or public.claim_parcel_link_postcode('unknownLink2', key_a, 'dhl', 5) is not null then
    raise exception 'A wrong key or an unknown link took a try';
  end if;
  if public.claim_parcel_link_postcode(link_a, key_a, 'gls-de', 5) <> '{"asked": false}' then
    raise exception 'A try was taken for a carrier that asks for nothing';
  end if;

  -- Tries the carrier refused stay counted; one given back does not.
  for attempt_number in 1..3 loop
    claim := public.claim_parcel_link_postcode(link_a, key_a, 'dhl', 3);
    if claim->>'attempt' is null or claim->>'tracking_number' <> '990000000001' then
      raise exception 'A try was refused too early: %', claim;
    end if;
    if attempt_number = 3 and not public.release_parcel_link_postcode((claim->>'attempt')::uuid) then
      raise exception 'A try was not given back';
    end if;
  end loop;
  claim := public.claim_parcel_link_postcode(link_a, key_a, 'dhl', 3);
  if claim->>'attempt' is null then raise exception 'A given-back try still counted'; end if;
  claim := public.claim_parcel_link_postcode(link_a, key_a, 'dhl', 3);
  if claim ? 'attempt' or (claim->>'retry_at')::timestamptz <> now() + interval '1 day' then
    raise exception 'A link out of tries was not told when to try again: %', claim;
  end if;
  if public.claim_parcel_link_postcode(link_b, key_b, 'dhl', 3)->>'attempt' is null then
    raise exception 'One link''s tries counted against another''s';
  end if;
  update public.parcel_link_postcode_attempts set attempted_at = now() - interval '25 hours' where link_id = link_a;
  if public.claim_parcel_link_postcode(link_a, key_a, 'dhl', 3)->>'attempt' is null then
    raise exception 'A day-old try still counted';
  end if;
  if (select count(*) from public.parcel_link_postcode_attempts where link_id = link_a) <> 1 then
    raise exception 'Day-old tries were kept';
  end if;

  -- Filing: the link moves to a new DHL parcel, the shared one stays for the other link.
  if public.file_parcel_link_postcode(link_a, key_b, 'dhl', '99999') is not null then
    raise exception 'A wrong key filed a lookup';
  end if;
  if public.file_parcel_link_postcode(link_a, key_a, 'gls-de', '99999') <> '{"asked": false}' then
    raise exception 'A lookup was filed under a carrier that asked for nothing';
  end if;
  filed := public.file_parcel_link_postcode(link_a, key_a, 'dhl', ' 99999 ');
  dhl_package := (filed#>>'{package,id}')::uuid;
  if filed->'created' <> 'true' or filed->'previous_forgotten' <> 'false' or filed#>>'{link,id}' <> link_a
      or filed#>'{link,owner}' <> 'true' or dhl_package = thin
      or filed#>>'{package,carrier}' <> 'dhl' or filed#>>'{package,dpd_postcode}' <> '99999'
      or filed#>'{package,one_off}' <> 'true' or filed#>>'{package,tracking_number}' <> '990000000001' then
    raise exception 'A lookup was not filed under the carrier: %', filed;
  end if;
  if exists (select 1 from public.parcel_link_postcode_attempts where link_id = link_a) then
    raise exception 'A filed link kept its tries';
  end if;
  if not exists (select 1 from public.parcel_links where id = link_b and package_id = thin)
      or (select carrier from public.packages where id = thin) <> 'unknown' then
    raise exception 'Filing one lookup changed the parcel another shares';
  end if;
  if public.file_parcel_link_postcode(link_a, key_a, 'dhl', '99999') <> '{"asked": false}' then
    raise exception 'A filed lookup was filed again';
  end if;

  -- The other lookup with the same postcode joins that parcel, and the shared one goes with its last link.
  again := public.file_parcel_link_postcode(link_b, key_b, 'dhl', '99999');
  if again->'created' <> 'false' or (again#>>'{package,id}')::uuid <> dhl_package or again->'previous_forgotten' <> 'true'
      or exists (select 1 from public.packages where id = thin) then
    raise exception 'The same postcode did not reuse the filed parcel: %', again;
  end if;

  -- A parcel that is over asks for nothing.
  first := public.create_one_off_parcel('990000000002', 'unknown', null, null, key_a);
  update public.packages
  set carrier_data = '{"routing":{"input_needed":{"carrier":"dhl","field":"dpdPostcode"}}}', current_stage = 'delivered'
  where id = (first#>>'{package,id}')::uuid;
  if public.claim_parcel_link_postcode(first#>>'{link,id}', key_a, 'dhl', 5) <> '{"asked": false}' then
    raise exception 'A delivered lookup took a try';
  end if;
end;
$$;
rollback;
