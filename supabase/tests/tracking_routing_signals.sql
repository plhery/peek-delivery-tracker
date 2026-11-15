\set ON_ERROR_STOP on
begin;

insert into auth.users(id, email) values ('a7200000-0000-4000-8000-000000000001', 'signals-test@example.invalid');
insert into public.packages(id, user_id, tracking_number, carrier) values
  ('a7200000-0000-4000-8000-000000000002', 'a7200000-0000-4000-8000-000000000001', 'SIGNALTEST1234', 'unknown');

do $$
declare
  attempt uuid := 'a7200000-0000-4000-8000-000000000003';
  parcel uuid := 'a7200000-0000-4000-8000-000000000002';
  signals jsonb := '[{"signal":"carrier_coverage_discovered","carrier":"unknown","subject":"Example Parcel Co"},
    {"signal":"direct_support_opportunity","carrier":"unknown","subject":"example-post"}]';
begin
  assert not has_table_privilege('anon', 'public.tracking_routing_signals', 'select');
  assert not has_table_privilege('authenticated', 'public.tracking_routing_signals', 'select');
  assert not has_function_privilege('anon', 'public.record_tracking_routing_signals(uuid,uuid,text,jsonb)', 'execute');
  assert not has_function_privilege('authenticated', 'public.record_tracking_routing_signals(uuid,uuid,text,jsonb)', 'execute');
  assert has_function_privilege('service_role', 'public.record_tracking_routing_signals(uuid,uuid,text,jsonb)', 'execute');
  assert (select relrowsecurity from pg_class where oid = 'public.tracking_routing_signals'::regclass);

  assert public.record_tracking_routing_signals(attempt, parcel, 'signal-test 1234', signals) = 2;
  assert (select count(*) = 2 and bool_and(tracking_number = 'SIGNALTEST1234' and package_id = parcel and count = 1)
    from public.tracking_routing_signals);
  -- A repeat counts on the same row.
  assert public.record_tracking_routing_signals(attempt, parcel, 'SIGNALTEST1234', signals - 1) = 1;
  assert (select count = 2 from public.tracking_routing_signals where signal = 'carrier_coverage_discovered');
  -- A parcel deleted during its check leaves its signal without a package.
  assert public.record_tracking_routing_signals(attempt, 'a7200000-0000-4000-8000-0000000000ff', 'OTHER5678',
    '[{"signal":"carrier_auto_swapped","carrier":"dhl","subject":"ups"}]') = 1;
  assert (select package_id is null from public.tracking_routing_signals where tracking_number = 'OTHER5678');
  begin
    perform public.record_tracking_routing_signals(attempt, parcel, 'SIGNALTEST1234',
      '[{"signal":"provider_failed","carrier":"dhl","subject":"17TRACK"}]');
    raise exception 'Accepted a signal that is not one' using errcode = 'P0002';
  exception when invalid_parameter_value then null; end;
  -- Rows unseen for 90 days go with the next write.
  update public.tracking_routing_signals set first_seen = now() - interval '100 days', last_seen = now() - interval '91 days'
    where tracking_number = 'OTHER5678';
  perform public.record_tracking_routing_signals(attempt, parcel, 'SIGNALTEST1234', signals);
  assert not exists(select 1 from public.tracking_routing_signals where tracking_number = 'OTHER5678');

  assert not has_schema_privilege('anon', 'private', 'usage') and not has_schema_privilege('authenticated', 'private', 'usage');
  assert private.tracking_number_pattern('my1234567890us') = 'MY + 10 digits + US';
  assert private.tracking_number_pattern('JJD0003-9000 7') = 'JJD + 9 digits';
  assert private.tracking_number_pattern('ABCDEF7') = '6 letters + 1 digit';
  assert private.tracking_number_pattern('') = '';
end;
$$;

-- Three numbers of one shape no carrier claims, each on a parcel with a support case first seen in the last
-- 14 days, raise `repeated_unknown_shape` for each, under the shape the review shows.
insert into public.packages(id, user_id, tracking_number, carrier) values
  ('a7200000-0000-4000-8000-000000000011', 'a7200000-0000-4000-8000-000000000001', 'SHAPEAB1234567', 'unknown'),
  ('a7200000-0000-4000-8000-000000000012', 'a7200000-0000-4000-8000-000000000001', 'SHAPEAB7654321', 'unknown'),
  ('a7200000-0000-4000-8000-000000000013', 'a7200000-0000-4000-8000-000000000001', 'SHAPEXY2222222', 'unknown'),
  ('a7200000-0000-4000-8000-000000000014', 'a7200000-0000-4000-8000-000000000001', 'SHAPEAB5555555', 'unknown'),
  ('a7200000-0000-4000-8000-000000000015', 'a7200000-0000-4000-8000-000000000001', 'SHAPEAB6666666', 'unknown'),
  ('a7200000-0000-4000-8000-000000000016', 'a7200000-0000-4000-8000-000000000001', 'SHAPEAB8888888', 'unknown'),
  ('a7200000-0000-4000-8000-000000000017', 'a7200000-0000-4000-8000-000000000001', 'SHAPEAB9999999', 'unknown');

do $$
declare
  shown text := private.tracking_number_pattern('SHAPEAB1234567');
  shape_case text := 'insert into public.tracking_support_cases (tracking_number, first_seen, last_seen, configured_carrier, reasons, fix_status, fixed_at) values ($1, $2, $2, ''unknown'', $3, $4, $5)';
  signals integer;
begin
  assert private.tracking_number_shape('shapeab-123 4567') = 'SHAPEAB + 7';
  assert private.tracking_number_shape('MY1234567890US') = 'MY + 10 + US';
  assert private.tracking_number_shape('') = '';
  assert not has_function_privilege('authenticated', 'private.raise_repeated_unknown_shape(text)', 'execute');

  execute shape_case using 'SHAPEAB1234567', now() - interval '13 days', array['unknown_shape'], 'ignored', null::timestamptz;
  execute shape_case using 'SHAPEAB7654321', now() - interval '2 days', array['unknown_shape'], 'open', null::timestamptz;
  -- Not counted: a number no parcel follows, another shape the review shows alike, a case first seen 15 days ago,
  -- a fixed case, and one without the reason.
  execute shape_case using 'SHAPEAB3333333', now(), array['unknown_shape'], 'open', null::timestamptz;
  execute shape_case using 'SHAPEXY2222222', now(), array['unknown_shape'], 'open', null::timestamptz;
  execute shape_case using 'SHAPEAB5555555', now() - interval '15 days', array['unknown_shape'], 'ignored', null::timestamptz;
  execute shape_case using 'SHAPEAB6666666', now(), array['unknown_shape'], 'fixed', now();
  execute shape_case using 'SHAPEAB8888888', now(), array['no_direct_adapter'], 'open', null::timestamptz;
  assert not exists (select from public.tracking_routing_signals where signal = 'repeated_unknown_shape'), 'raised under three';
  assert private.tracking_number_pattern('SHAPEXY2222222') = shown;

  -- The third raises the shape for each of its numbers, with their parcels.
  execute shape_case using 'SHAPEAB9999999', now(), array['unknown_shape'], 'open', null::timestamptz;
  assert (select count(*) = 3 and bool_and(carrier = 'unknown' and subject = shown and count = 1 and package_id is not null)
    and array_agg(tracking_number order by tracking_number) = array['SHAPEAB1234567', 'SHAPEAB7654321', 'SHAPEAB9999999']
    from public.tracking_routing_signals where signal = 'repeated_unknown_shape'), 'three numbers raised';
  -- A later write of one of their cases adds nothing.
  update public.tracking_support_cases set reasons = array['unknown_shape'], seen_count = seen_count + 1 where tracking_number = 'SHAPEAB9999999';
  assert (select count(*) from public.tracking_routing_signals where signal = 'repeated_unknown_shape') = 3;

  -- Once reviewed, a fourth number adds its row and marks the unreviewed ones seen again; the reviewed stay reviewed.
  update public.tracking_routing_signals set reviewed_at = now(), review_note = 'checked',
    first_seen = now() - interval '2 hours', last_seen = now() - interval '1 hour'
    where signal = 'repeated_unknown_shape';
  update public.tracking_support_cases set reasons = array['unknown_shape', 'no_direct_adapter'] where tracking_number = 'SHAPEAB8888888';
  assert (select count(*) filter (where reviewed_at is null) = 1 and count(*) = 4
    and bool_and(reviewed_at is not null or (tracking_number = 'SHAPEAB8888888' and last_seen > now() - interval '1 minute'))
    from public.tracking_routing_signals where signal = 'repeated_unknown_shape'), 'fourth number';
  assert private.raise_repeated_unknown_shape('SHAPEAB1234567') = 0;
end;
$$;

rollback;
