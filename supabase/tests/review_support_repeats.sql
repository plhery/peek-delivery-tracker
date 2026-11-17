-- Support cases for the hourly review's "Support cases" and "Shapes detection could learn" sections
-- (ops/review/signals.sql, read by scripts/test-migrations.sh), in the 24 hours before 09:00 UTC on 1 January 2026.
\set ON_ERROR_STOP on
begin;
do $$
declare
  unknown jsonb := '{"configured_carrier":"unknown","reasons":["unknown_shape"]}';
begin
  -- The start of a number, then the whole number: the second case repeats the first, which saving the whole
  -- number closes. The repeat is then a case of its own.
  perform public.record_tracking_support_observation('REVIEWREP12345', unknown, '{}', '2025-12-31T10:00:00Z', 'detection:review-1');
  perform public.record_tracking_support_observation('REVIEWREP123456', unknown, '{}', '2025-12-31T10:02:00Z', 'detection:review-2');
  update public.tracking_support_cases set fix_status = 'ignored' where tracking_number = 'REVIEWREP12345';
  -- Both still open: the repeat counts under the first case.
  perform public.record_tracking_support_observation('REVIEWOPEN12345', unknown, '{}', '2025-12-31T11:00:00Z', 'detection:review-3');
  perform public.record_tracking_support_observation('REVIEWOPEN123456', unknown, '{}', '2025-12-31T11:03:00Z', 'detection:review-4');
  assert (select count(*) from public.tracking_support_cases where tracking_number like 'REVIEW%' and duplicate_of is not null) = 2,
    'setup: two repeats';
  -- Recognition could not tell the carrier; the reader chose Paack, whose check tracked the number with dated scans:
  -- the case moves to the shapes detection could learn.
  perform public.record_tracking_support_observation('REVIEWPAACK1234',
    '{"configured_carrier":"unknown","reasons":["recognition_unknown"]}', '{}', '2025-12-31T12:00:00Z', 'detection:review-5');
  perform public.record_tracking_support_observation('REVIEWPAACK1234', '{"configured_carrier":"paack","reasons":[]}',
    '{"outcome":"unchanged","support_direct_progress":true,"source_carrier":"paack","support_lookup_number":"REVIEWPAACK1234"}',
    '2025-12-31T12:01:00Z', 'attempt:review-6');
  assert (select direct_verified_carrier = 'paack' and reasons = array['recognition_unknown']
    from public.tracking_support_cases where tracking_number = 'REVIEWPAACK1234'), 'setup: Paack tracked it';
end;
$$;
commit;
