\set ON_ERROR_STOP on
begin;
insert into auth.users (id, email) values
  ('19000000-0000-0000-0000-000000000041', 'phone-digits-owner@example.test'),
  ('19000000-0000-0000-0000-000000000042', 'phone-digits-viewer@example.test');
select set_config('request.jwt.claim.sub', '19000000-0000-0000-0000-000000000041', true);
set local role authenticated;
select set_config('digits_test.parcel', (test_support.add_parcel('JTMXTEST0001', 'Digits fixture', 'j-and-t-mx')).id::text, true);

set local role service_role;
do $$
declare
  parcel uuid := current_setting('digits_test.parcel')::uuid;
  generation uuid;
begin
  -- The server writes the digits beside the parcel; a new set of them is a new configuration.
  generation := (select tracking_generation from public.packages where id = parcel);
  update public.packages set phone_digits = '0472' where id = parcel;
  if (select tracking_generation from public.packages where id = parcel) = generation then
    raise exception 'New phone digits did not renew the tracking generation';
  end if;
  begin
    update public.packages set phone_digits = '47a2' where id = parcel;
    raise exception 'Malformed phone digits were stored' using errcode = 'P0001';
  exception when check_violation then null; end;
  begin
    update public.packages set carrier = 'dpd', phone_digits = '0473' where id = parcel;
    raise exception 'Phone digits were kept for a carrier that never reads them' using errcode = 'P0001';
  exception when check_violation then null; end;
  -- Another J&T carrier or number forgets them, the system's carrier corrections included.
  update public.packages set carrier = 'j-and-t-cn' where id = parcel;
  if (select phone_digits from public.packages where id = parcel) is not null then
    raise exception 'Phone digits followed the parcel to another carrier';
  end if;
  update public.packages set phone_digits = '0472' where id = parcel;
  update public.packages set carrier = 'dpd' where id = parcel;
  if (select phone_digits from public.packages where id = parcel) is not null then
    raise exception 'A carrier correction kept the phone digits';
  end if;
  update public.packages set carrier = 'j-and-t-mx', phone_digits = '0472' where id = parcel;
  update public.packages set tracking_number = 'JTMXTEST0002' where id = parcel;
  if (select phone_digits from public.packages where id = parcel) is not null then
    raise exception 'Phone digits followed the parcel to another number';
  end if;
  -- Digits written with the change itself are kept.
  update public.packages set carrier = 'j-and-t-cn', phone_digits = '0473' where id = parcel;
  if (select phone_digits from public.packages where id = parcel) is distinct from '0473' then
    raise exception 'The phone digits sent with a carrier change were dropped';
  end if;
end;
$$;

-- Lookups share a parcel only when they gave the same digits; a viewer's copy never has them.
do $$
declare
  key_a constant text := repeat('c', 64);
  key_b constant text := repeat('d', 64);
  first jsonb;
  answer jsonb;
begin
  first := public.create_one_off_parcel('JTMXTEST0101', 'j-and-t-mx', null, null, key_a, '0472');
  if first->'created' <> 'true' or first#>>'{package,phone_digits}' <> '0472' then
    raise exception 'A lookup did not keep its phone digits: %', first->'created';
  end if;
  answer := public.create_one_off_parcel('JTMXTEST0101', 'j-and-t-mx', null, null, key_b, ' 0472 ');
  if answer->'created' <> 'false' or answer#>>'{package,id}' <> first#>>'{package,id}' then
    raise exception 'The same digits did not reuse the stored parcel';
  end if;
  perform set_config('digits_test.key_holder_link', first#>>'{link,id}', true);
  perform set_config('digits_test.viewer_link', answer#>>'{link,id}', true);
  perform public.update_parcel_link(answer#>>'{link,id}', key_b, p_show_number => true, p_shared => true);
  answer := public.create_one_off_parcel('JTMXTEST0101', 'j-and-t-mx', null, null, key_a, '0473');
  if answer->'created' <> 'true' or answer#>>'{package,id}' = first#>>'{package,id}' then
    raise exception 'Other digits reused a parcel';
  end if;
  answer := public.create_one_off_parcel('JTMXTEST0101', 'j-and-t-mx', null, null, key_a);
  if answer->'created' <> 'true' or answer#>>'{package,id}' = first#>>'{package,id}'
      or answer#>>'{package,phone_digits}' is not null then
    raise exception 'A lookup without digits reused a parcel that has them';
  end if;
  begin
    perform public.create_one_off_parcel('JTMXTEST0102', 'dhl', null, null, key_a, '0472');
    raise exception 'A lookup kept phone digits for a carrier that never reads them' using errcode = 'P0001';
  exception when check_violation then null; end;
  -- What the digits brought: a delivery naming its recipient, and the carrier's status line.
  insert into public.tracking_events (package_id, provider_event_id, stage, description, location, occurred_at, raw_data)
  values ((first#>>'{package,id}')::uuid, 'test:private', 'delivered', 'Synthetic delivery signed by a synthetic name',
    'Synthetic Street 1', now(), '{"signed_by":"Synthetic Name"}');
  update public.packages
  set current_stage = 'delivered', expected_delivery = '2026-11-16', last_status_text = 'Synthetic status line'
  where id = (first#>>'{package,id}')::uuid;
end;
$$;

set local role authenticated;
do $$
declare
  answer jsonb;
begin
  -- Another lookup follows the parcel, so the key holder gets a copy with the lookup's inputs.
  answer := public.claim_parcel_link(current_setting('digits_test.key_holder_link'), repeat('c', 64), 'Mine');
  if answer->>'outcome' <> 'kept'
      or (select phone_digits from public.packages where id = (answer->>'package_id')::uuid) is distinct from '0472' then
    raise exception 'The key holder''s copy lost the phone digits: %', answer->>'outcome';
  end if;
  perform set_config('digits_test.key_holder_copy', answer->>'package_id', true);
end;
$$;

select set_config('request.jwt.claim.sub', '19000000-0000-0000-0000-000000000042', true);
do $$
declare
  answer jsonb;
begin
  answer := public.claim_parcel_link(current_setting('digits_test.viewer_link'), null, 'Seen');
  if answer->>'outcome' <> 'kept'
      or (select phone_digits from public.packages where id = (answer->>'package_id')::uuid) is not null then
    raise exception 'A viewer''s copy was given someone else''s phone digits: %', answer->>'outcome';
  end if;
  perform set_config('digits_test.viewer_copy', answer->>'package_id', true);
end;
$$;

-- The key holder's copy keeps the parcel's history; a viewer's starts as a new lookup of the number.
set local role service_role;
do $$
declare
  mine uuid := current_setting('digits_test.key_holder_copy')::uuid;
  seen uuid := current_setting('digits_test.viewer_copy')::uuid;
begin
  if not exists (select 1 from public.tracking_events where package_id = mine and provider_event_id = 'test:private')
      or (select last_status_text from public.packages where id = mine) is distinct from 'Synthetic status line' then
    raise exception 'The key holder''s copy lost the parcel''s history';
  end if;
  if exists (select 1 from public.tracking_events where package_id = seen and provider_event_id like 'test:%')
      or exists (select 1 from public.packages where id = seen and (current_stage <> 'pending'
        or expected_delivery is not null or last_status_text is not null)) then
    raise exception 'A viewer''s copy was given the parcel''s scans or status';
  end if;
end;
$$;
rollback;
