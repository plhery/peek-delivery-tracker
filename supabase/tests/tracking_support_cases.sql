\set ON_ERROR_STOP on
begin;

insert into auth.users(id, email) values ('a7100000-0000-4000-8000-000000000001', 'support-test@example.invalid');
insert into public.packages(id, user_id, tracking_number, carrier) values
  ('a7100000-0000-4000-8000-000000000002', 'a7100000-0000-4000-8000-000000000001', 'CASETEST1234', 'unknown'),
  ('a7100000-0000-4000-8000-000000000003', 'a7100000-0000-4000-8000-000000000001', 'GAPTEST5678', 'unknown');

do $$
declare
  context jsonb := '{"tracking_number":"case-test.1234", "configured_carrier":"unknown", "detection_carrier":"unknown", "detection_confidence":"none", "detection_candidates":[], "reasons":["unknown_shape"], "app_version":"test"}';
  case_id uuid;
  attempt_id uuid := 'a7100000-0000-4000-8000-000000000004';
begin
  assert not has_table_privilege('anon', 'public.tracking_support_cases', 'select');
  assert not has_table_privilege('authenticated', 'public.tracking_support_cases', 'select');
  assert not has_table_privilege('authenticated', 'public.tracking_support_observations', 'select');
  assert not has_function_privilege('anon', 'public.record_tracking_support_observation(text,jsonb,jsonb,timestamptz,text)', 'execute');
  assert not has_function_privilege('authenticated', 'public.record_tracking_support_observation(text,jsonb,jsonb,timestamptz,text)', 'execute');
  assert has_function_privilege('service_role', 'public.record_tracking_support_observation(text,jsonb,jsonb,timestamptz,text)', 'execute');
  assert (select relrowsecurity from pg_class where oid = 'public.tracking_support_cases'::regclass);
  assert (select relrowsecurity from pg_class where oid = 'public.tracking_support_observations'::regclass);

  insert into public.tracking_sync_attempts(id, package_id, trigger, configured_carrier, started_at, support_context)
  values(attempt_id, 'a7100000-0000-4000-8000-000000000002', 'package', 'unknown', '2026-10-04T18:00:00Z', context);
  select id into strict case_id from public.tracking_support_cases where tracking_number = 'CASETEST1234';
  assert (select seen_count = 1 and fallback_count = 0 and fix_status = 'open'
    and configured_carrier = 'unknown' and reasons = array['unknown_shape']
    from public.tracking_support_cases where id = case_id);

  assert public.complete_tracking_sync_attempt(attempt_id,
    '{"outcome":"updated", "source_carrier":"unknown", "completed_at":"2026-10-04T18:01:00Z", "events_received":3, "events_normalized":3}',
    '[{"sequence":1,"step":"complete","status":"succeeded","details":{"support_lookup_number":"CASETEST1234","support_provider":"test-provider","support_direct_progress":false}}]');
  assert (select seen_count = 1 and fallback_count = 1 and fix_status = 'open'
    and last_outcome = 'updated' and last_provider = 'test-provider' and direct_verified_at is null
    from public.tracking_support_cases where id = case_id), 'Fallback progress must leave the case open';
  assert not public.complete_tracking_sync_attempt(attempt_id,
    '{"outcome":"updated", "source_carrier":"unknown", "completed_at":"2026-10-04T18:01:00Z"}',
    '[{"sequence":1,"step":"complete","status":"succeeded","details":{"support_provider":"test-provider"}}]');
  assert (select seen_count = 1 and fallback_count = 1 from public.tracking_support_cases where id = case_id), 'Audit replay counted twice';

  update public.tracking_support_cases set fix_status = 'fixed', fix_reference = 'synthetic-fix', fixed_at = '2026-10-04T19:00:00Z' where id = case_id;
  perform public.record_tracking_support_observation('CASETEST1234', context,
    '{"outcome":"updated", "source_carrier":"chronopost", "support_lookup_number":"CASETEST1234", "support_direct_progress":true}',
    '2026-10-04T18:30:00Z', 'test:pre-fix');
  assert (select fix_status = 'fixed' and direct_verified_carrier = 'chronopost' from public.tracking_support_cases where id = case_id), 'Older direct history must not verify a new fix';
  perform public.record_tracking_support_observation('CASETEST1234', context,
    '{"outcome":"updated", "source_carrier":"chronopost", "support_lookup_number":"CASETEST1234", "support_direct_progress":false}',
    '2026-10-04T19:01:00Z', 'test:empty-direct');
  perform public.record_tracking_support_observation('CASETEST1234', context,
    '{"outcome":"updated", "source_carrier":"chronopost", "support_lookup_number":"HANDOFF5678", "support_direct_progress":true}',
    '2026-10-04T19:02:00Z', 'test:handoff');
  perform public.record_tracking_support_observation('CASETEST1234', context,
    '{"outcome":"updated", "source_carrier":"chronopost", "support_lookup_number":"CASETEST1234", "support_provider":"test-provider", "support_direct_progress":true}',
    '2026-10-04T19:03:00Z', 'test:provider');
  perform public.record_tracking_support_observation('CASETEST1234', context,
    '{"outcome":"waiting", "source_carrier":"chronopost", "support_lookup_number":"CASETEST1234", "support_direct_progress":true}',
    '2026-10-04T19:04:00Z', 'test:waiting');
  assert (select fix_status = 'fixed' and direct_verified_at = '2026-10-04T18:30:00Z'::timestamptz from public.tracking_support_cases where id = case_id), 'Only usable direct progress for the same number can verify';
  perform public.record_tracking_support_observation('CASETEST1234', context,
    '{"outcome":"updated", "source_carrier":"chronopost", "support_lookup_number":"case-test.1234", "support_direct_progress":true}',
    '2026-10-04T19:05:00Z', 'test:direct');
  assert (select fix_status = 'verified' and direct_verified_at = '2026-10-04T19:05:00Z'::timestamptz
    and direct_verified_carrier = 'chronopost' from public.tracking_support_cases where id = case_id);
  perform public.record_tracking_support_observation('CASETEST1234', context,
    '{"outcome":"updated", "source_carrier":"chronopost", "support_lookup_number":"CASETEST1234", "support_direct_progress":true}',
    '2026-10-04T19:05:00Z', 'test:direct');
  assert (select seen_count = 7 and fallback_count = 2 from public.tracking_support_cases where id = case_id), 'Backfill replay counted twice';
  perform public.record_tracking_support_observation('CASETEST1234', context,
    '{"outcome":"abandoned", "source_carrier":"chronopost", "support_lookup_number":"CASETEST1234", "support_direct_progress":true}',
    '2026-10-04T19:06:00Z', 'test:abandoned');
  assert (select seen_count = 7 and last_outcome = 'updated' from public.tracking_support_cases where id = case_id), 'Abandoned attempts supplied evidence';

  delete from public.tracking_sync_attempts where id = attempt_id;
  assert exists(select 1 from public.tracking_support_cases where id = case_id), 'Audit pruning deleted the case';
  perform public.record_tracking_support_observation('CASETEST1234', context,
    '{"outcome":"updated", "source_carrier":"unknown", "support_provider":"test-provider"}',
    '2026-10-04T18:01:00Z', 'attempt:' || attempt_id);
  assert (select seen_count = 7 and fallback_count = 2 from public.tracking_support_cases where id = case_id), 'Pruned audit replay counted twice';
  perform public.record_tracking_support_observation('CASETEST1234', '{"configured_carrier":"chronopost"}',
    '{"outcome":"error", "error_type":"TransportError"}', '2026-10-04T19:07:00Z', 'test:partial-context');
  assert (select configured_carrier = 'chronopost' and detection_carrier = 'unknown'
    and detection_confidence = 'none' and app_version = 'test'
    from public.tracking_support_cases where id = case_id), 'Partial context erased earlier detection metadata';
  begin
    update public.tracking_support_cases set fixed_at = '2026-10-04T20:00:00Z' where id = case_id;
    raise exception 'Verified fix accepted older direct evidence';
  exception when check_violation then null;
  end;

  assert public.record_tracking_support_observation('KNOWNTEST1234', '{"reasons":[]}',
    '{"outcome":"error", "source_carrier":"ups", "error_type":"TransportError"}',
    '2026-10-04T19:05:00Z', 'test:known-transport') is null;
  assert public.record_tracking_support_observation('KNOWNTEST1234', '{"reasons":[]}',
    '{"outcome":"updated", "source_carrier":"unknown", "support_provider":"test-provider"}',
    '2026-10-04T19:05:00Z', 'test:known-provider') is null, 'Known-carrier outages must not become format cases';

  insert into public.tracking_sync_attempts(id, package_id, trigger, configured_carrier)
  values('a7100000-0000-4000-8000-000000000005', 'a7100000-0000-4000-8000-000000000003', 'package', 'unknown');
  assert not exists(select 1 from public.tracking_support_cases where tracking_number = 'GAPTEST5678');
  assert public.complete_tracking_sync_attempt('a7100000-0000-4000-8000-000000000005',
    '{"outcome":"updated", "source_carrier":"unknown"}',
    '[{"sequence":1,"step":"complete","status":"succeeded","details":{"support_lookup_number":"GAPTEST5678","support_gap_reason":"no_direct_adapter","support_provider":"test-provider"}}]');
  assert (select seen_count = 1 and fallback_count = 1 and reasons = array['no_direct_adapter']
    from public.tracking_support_cases where tracking_number = 'GAPTEST5678'), 'Completion must preserve newly discovered gaps';

  begin
    perform public.record_tracking_support_observation('CASETEST1234', '{"reasons":["bad tag"]}', '{}', now(), 'test:bad');
    raise exception 'Invalid reason accepted';
  exception when invalid_parameter_value then null;
  end;
  begin
    perform public.record_tracking_support_observation('DIFFERENT1234', '{"reasons":["unknown_shape"]}', '{}', now(), 'test:direct');
    raise exception 'Observation key reused for another number';
  exception when invalid_parameter_value then null;
  end;
  assert public.record_tracking_support_observation('1234/12345678', '{"reasons":["ambiguous_shape"]}', '{}', now(), 'test:slash') is not null;
end;
$$;

-- A check's sighting replaces the shape and carrier reasons; other sightings add to them.
do $$
declare
  case_id uuid;
  reasons_now text[];
begin
  case_id := public.record_tracking_support_observation('REASONTEST1234',
    '{"configured_carrier":"unknown", "reasons":["ambiguous_shape", "recognition_unknown"]}', '{}',
    '2026-10-04T10:00:00Z', 'detection:a7100000-0000-4000-8000-000000000010');
  perform public.record_tracking_support_observation('REASONTEST1234',
    '{"configured_carrier":"synthetic-carrier", "reasons":["ambiguous_shape", "carrier_mismatch", "no_direct_adapter"]}', '{}',
    '2026-10-04T11:00:00Z', 'attempt:a7100000-0000-4000-8000-000000000011');
  select reasons into reasons_now from public.tracking_support_cases where id = case_id;
  assert reasons_now = array['ambiguous_shape', 'carrier_mismatch', 'no_direct_adapter', 'recognition_unknown'], reasons_now::text;
  perform public.record_tracking_support_observation('REASONTEST1234',
    '{"configured_carrier":"synthetic-carrier", "reasons":["ambiguous_shape"]}', '{}',
    '2026-10-04T12:00:00Z', 'attempt:a7100000-0000-4000-8000-000000000012');
  select reasons into reasons_now from public.tracking_support_cases where id = case_id;
  assert reasons_now = array['ambiguous_shape', 'recognition_unknown'], 'A newer check must replace stale reasons: ' || reasons_now::text;
  perform public.record_tracking_support_observation('REASONTEST1234',
    '{"configured_carrier":"synthetic-carrier", "reasons":[]}', '{}',
    '2026-10-04T11:30:00Z', 'attempt:a7100000-0000-4000-8000-000000000013');
  perform public.record_tracking_support_observation('REASONTEST1234',
    '{"configured_carrier":"unknown", "reasons":["ambiguous_shape", "recognition_choice"]}', '{}',
    '2026-10-04T13:00:00Z', 'detection:a7100000-0000-4000-8000-000000000014');
  perform public.record_tracking_support_observation('REASONTEST1234', '{"configured_carrier":"synthetic-carrier"}',
    '{"outcome":"error", "error_type":"TransportError"}', '2026-10-04T13:30:00Z', 'attempt:a7100000-0000-4000-8000-000000000015');
  select reasons into reasons_now from public.tracking_support_cases where id = case_id;
  assert reasons_now = array['ambiguous_shape', 'recognition_choice', 'recognition_unknown'],
    'Older checks, detection and checks without reasons must only add: ' || reasons_now::text;
  perform public.record_tracking_support_observation('REASONTEST1234',
    '{"configured_carrier":"synthetic-carrier", "reasons":[]}',
    '{"outcome":"updated", "source_carrier":"unknown", "support_gap_reason":"no_direct_adapter"}',
    '2026-10-04T14:00:00Z', 'attempt:a7100000-0000-4000-8000-000000000016');
  select reasons into reasons_now from public.tracking_support_cases where id = case_id;
  assert reasons_now = array['no_direct_adapter', 'recognition_choice', 'recognition_unknown'],
    'A check keeps recognition reasons and its own gap evidence: ' || reasons_now::text;
end;
$$;

insert into public.sync_jobs(id, user_id, package_id, kind, state, locked_by, lease_until)
values('a7100000-0000-4000-8000-000000000006', 'a7100000-0000-4000-8000-000000000001',
  'a7100000-0000-4000-8000-000000000002', 'package', 'running', 'support-worker', now() + interval '1 minute');
do $$
declare
  values jsonb := jsonb_build_object('package_id', 'a7100000-0000-4000-8000-000000000002',
    'trigger', 'package', 'configured_carrier', 'unknown', 'started_at', now(),
    'support_context', '{"tracking_number":"LEASECASE1234","detection_carrier":"unknown","detection_confidence":"none","reasons":["unknown_shape"]}'::jsonb);
begin
  assert not public.start_leased_sync_attempt('a7100000-0000-4000-8000-000000000007',
    'a7100000-0000-4000-8000-000000000006', 'wrong-worker', values);
  assert not exists(select 1 from public.tracking_support_cases where tracking_number = 'LEASECASE1234');
  assert public.start_leased_sync_attempt('a7100000-0000-4000-8000-000000000007',
    'a7100000-0000-4000-8000-000000000006', 'support-worker', values);
  assert (select support_context = values->'support_context' from public.tracking_sync_attempts where id = 'a7100000-0000-4000-8000-000000000007');
  assert (select seen_count = 1 from public.tracking_support_cases where tracking_number = 'LEASECASE1234');
  update public.tracking_sync_attempts set outcome = 'superseded', completed_at = now()
  where id = 'a7100000-0000-4000-8000-000000000007';
  assert (select last_outcome is null and direct_verified_at is null from public.tracking_support_cases where tracking_number = 'LEASECASE1234');
  insert into public.tracking_sync_attempts(package_id, trigger, configured_carrier)
  values('a7100000-0000-4000-8000-000000000002', 'package', 'chronopost');
  assert (select configured_carrier = 'chronopost' and detection_confidence = 'none' and app_version = 'test'
    from public.tracking_support_cases where tracking_number = 'CASETEST1234'), 'Legacy audit erased detection metadata';
end;
$$;

set local role authenticated;
do $$
begin
  begin
    perform 1 from public.tracking_support_cases;
    raise exception 'Client could read support cases';
  exception when insufficient_privilege then null;
  end;
  begin
    perform public.record_tracking_support_observation('CLIENTTEST1234', '{"reasons":["unknown_shape"]}', '{}', now(), 'test:client');
    raise exception 'Client could create support cases';
  exception when insufficient_privilege then null;
  end;
end;
$$;
reset role;

-- A deleted parcel leaves its case and signals for the review. They go 10 days after no parcel
-- follows their number and after their last check, whether the parcel or the account was
-- deleted or the number was checked without being saved.
do $$
begin
  assert not has_function_privilege('authenticated', 'public.forget_unfollowed_tracking_review()', 'execute');
  assert not has_function_privilege('anon', 'public.forget_unfollowed_tracking_review()', 'execute');
  assert has_function_privilege('service_role', 'public.forget_unfollowed_tracking_review()', 'execute');
  -- Written for a number no parcel follows, a case starts its clock.
  assert (select unfollowed_at is not null from public.tracking_support_cases where tracking_number = 'LEASECASE1234');
  assert (select unfollowed_at is null from public.tracking_support_cases where tracking_number = 'CASETEST1234');
end;
$$;
insert into auth.users(id, email) values ('a7100000-0000-4000-8000-000000000008', 'support-other@example.invalid');
insert into public.packages(id, user_id, tracking_number, carrier) values
  ('a7100000-0000-4000-8000-000000000009', 'a7100000-0000-4000-8000-000000000008', 'GAPTEST5678', 'unknown'),
  ('a7100000-0000-4000-8000-000000000010', 'a7100000-0000-4000-8000-000000000001', 'LEASECASE1234', 'unknown');
insert into public.tracking_routing_signals(signal, carrier, subject, tracking_number, package_id) values
  ('direct_support_opportunity', 'unknown', 'dpd', 'CASETEST1234', 'a7100000-0000-4000-8000-000000000002'),
  ('direct_support_opportunity', 'unknown', 'dpd', 'GAPTEST5678', 'a7100000-0000-4000-8000-000000000003'),
  ('direct_support_opportunity', 'unknown', 'dpd', 'LEASECASE1234', 'a7100000-0000-4000-8000-000000000010'),
  ('direct_support_opportunity', 'unknown', 'dpd', 'SIGNALONLY1234', null);
do $$
begin
  -- A parcel following the number stops the clock.
  assert (select unfollowed_at is null from public.tracking_support_cases where tracking_number = 'LEASECASE1234');
  assert (select array_agg(tracking_number) from public.tracking_routing_signals where unfollowed_at is not null) = array['SIGNALONLY1234'];
end;
$$;
delete from public.packages where id = 'a7100000-0000-4000-8000-000000000002';
delete from auth.users where id = 'a7100000-0000-4000-8000-000000000001';
do $$
begin
  assert not exists(select 1 from public.tracking_sync_attempts where id in ('a7100000-0000-4000-8000-000000000005', 'a7100000-0000-4000-8000-000000000007'));
  -- Nothing goes at once: the deleted parcel's number and the deleted account's start their
  -- clock, and a number another account follows does not.
  assert exists(select 1 from public.tracking_support_cases where tracking_number = 'CASETEST1234' and fix_status = 'verified');
  assert exists(select 1 from public.tracking_support_observations where observation_key = 'test:direct');
  assert exists(select 1 from public.tracking_support_cases where tracking_number = 'GAPTEST5678' and fallback_count = 1);
  assert exists(select 1 from public.tracking_support_observations where observation_key = 'attempt:a7100000-0000-4000-8000-000000000005');
  assert exists(select 1 from public.tracking_support_observations where observation_key = 'attempt:a7100000-0000-4000-8000-000000000007');
  assert (select array_agg(tracking_number order by tracking_number) from public.tracking_support_cases
    where tracking_number in ('CASETEST1234', 'GAPTEST5678', 'LEASECASE1234') and unfollowed_at is not null)
    = array['CASETEST1234', 'LEASECASE1234'];
  assert (select array_agg(tracking_number order by tracking_number) from public.tracking_routing_signals
    where unfollowed_at is not null) = array['CASETEST1234', 'LEASECASE1234', 'SIGNALONLY1234'];
  assert public.forget_unfollowed_tracking_review() = '{"cases": 0, "signals": 0}'::jsonb;
end;
$$;
-- Ten days on, the entries go, except one checked since.
update public.tracking_support_cases set first_seen = first_seen - interval '11 days',
  last_seen = last_seen - interval '11 days', unfollowed_at = unfollowed_at - interval '11 days'
where tracking_number in ('CASETEST1234', 'LEASECASE1234');
update public.tracking_support_cases set last_seen = now() - interval '1 day' where tracking_number = 'LEASECASE1234';
update public.tracking_routing_signals set first_seen = first_seen - interval '11 days',
  last_seen = last_seen - interval '11 days', unfollowed_at = unfollowed_at - interval '11 days';
do $$
begin
  assert public.forget_unfollowed_tracking_review() = '{"cases": 1, "signals": 3}'::jsonb;
  assert not exists(select 1 from public.tracking_support_cases where tracking_number = 'CASETEST1234');
  assert not exists(select 1 from public.tracking_support_observations where observation_key = 'test:direct');
  assert exists(select 1 from public.tracking_support_observations where observation_key = 'attempt:a7100000-0000-4000-8000-000000000005');
  assert (select array_agg(tracking_number order by tracking_number) from public.tracking_support_cases
    where tracking_number in ('CASETEST1234', 'GAPTEST5678', 'LEASECASE1234')) = array['GAPTEST5678', 'LEASECASE1234'];
  assert (select array_agg(tracking_number) from public.tracking_routing_signals) = array['GAPTEST5678'];
end;
$$;
-- A parcel moved to another number starts the clock of the one it left.
update public.packages set tracking_number = 'LEASECASE1234' where id = 'a7100000-0000-4000-8000-000000000009';
do $$
begin
  assert (select unfollowed_at is not null from public.tracking_support_cases where tracking_number = 'GAPTEST5678');
  assert (select unfollowed_at is null from public.tracking_support_cases where tracking_number = 'LEASECASE1234');
  assert (select unfollowed_at is not null from public.tracking_routing_signals where tracking_number = 'GAPTEST5678');
end;
$$;
rollback;

-- An Add-sheet case left for a carrier recognition asks only through a browser waits an hour
-- for that carrier's checks; one it tracks directly within the hour, leaving no gap, is ignored.
begin;
do $$
declare
  pending jsonb := '{"configured_carrier":"unknown", "detection_carrier":"unknown", "detection_candidates":["fedex","dhl"], "reasons":["ambiguous_shape","recognition_unknown"], "browser_pending":true}';
  settled jsonb := '{"outcome":"updated", "source_carrier":"fedex", "support_lookup_number":"HELDTEST1234", "support_direct_progress":true, "support_settled":true}';
begin
  perform public.record_tracking_support_observation('HELDTEST1234', pending, '{}', '2026-10-19T10:00:00Z', 'detection:held');
  assert (select held_until = '2026-10-19T11:00:00Z'::timestamptz and fix_status = 'open'
    from public.tracking_support_cases where tracking_number = 'HELDTEST1234'), 'A pending browser recognition holds the case';
  assert (select detection_candidates = array['fedex', 'dhl'] from public.tracking_support_cases
    where tracking_number = 'HELDTEST1234'), 'A case keeps the candidates in their rank';
  -- Progress that settles nothing, or comes from a provider, leaves it open.
  perform public.record_tracking_support_observation('HELDTEST1234', '{"configured_carrier":"fedex"}',
    settled - 'support_settled', '2026-10-19T10:10:00Z', 'test:held-unsettled');
  perform public.record_tracking_support_observation('HELDTEST1234', '{"configured_carrier":"fedex"}',
    settled || '{"support_provider":"test-provider"}', '2026-10-19T10:12:00Z', 'test:held-provider');
  assert (select fix_status = 'open' from public.tracking_support_cases where tracking_number = 'HELDTEST1234');
  perform public.record_tracking_support_observation('HELDTEST1234', '{"configured_carrier":"fedex"}',
    settled, '2026-10-19T10:16:00Z', 'test:held-settled');
  assert (select fix_status = 'ignored' and notes like 'fedex, %' and direct_verified_carrier = 'fedex'
    from public.tracking_support_cases where tracking_number = 'HELDTEST1234'), 'The carrier confirming it within the hour closes it';

  -- After the hour, the case stays open for review.
  perform public.record_tracking_support_observation('LATETEST1234', pending, '{}', '2026-10-19T10:00:00Z', 'detection:late');
  perform public.record_tracking_support_observation('LATETEST1234', '{"configured_carrier":"fedex"}',
    settled || '{"support_lookup_number":"LATETEST1234"}', '2026-10-19T11:01:00Z', 'test:late-settled');
  assert (select fix_status = 'open' and direct_verified_carrier = 'fedex'
    from public.tracking_support_cases where tracking_number = 'LATETEST1234'), 'A hold ends after an hour';

  -- Without a pending browser recognition, a case is not held, and the same progress leaves it open.
  perform public.record_tracking_support_observation('OPENTEST1234', pending - 'browser_pending', '{}', '2026-10-19T10:00:00Z', 'detection:open');
  perform public.record_tracking_support_observation('OPENTEST1234', '{"configured_carrier":"fedex"}',
    settled || '{"support_lookup_number":"OPENTEST1234"}', '2026-10-19T10:16:00Z', 'test:open-settled');
  assert (select held_until is null and fix_status = 'open'
    from public.tracking_support_cases where tracking_number = 'OPENTEST1234'), 'Only a pending browser recognition holds a case';
end;
$$;
rollback;
