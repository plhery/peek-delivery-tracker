-- Followed parcels for the hourly review's "Followed parcels not checked for 24 h" section (ops/review/signals.sql,
-- read by scripts/test-migrations.sh), against the current time as that section reads it.
\set ON_ERROR_STOP on
begin;

insert into auth.users (id, email) values ('19000000-0000-0000-0000-000000000062', 'review-reopened@example.test');

insert into public.packages (id, user_id, tracking_number, carrier, current_stage, sync_status, last_synced_at)
values
  -- Delivered a week ago, then reopened by the stored-scan replay ten minutes ago: its check is coming.
  ('19000000-0000-0000-0000-000000006201', '19000000-0000-0000-0000-000000000062', 'EXAMPLEREOPEN01', 'speedx',
    'ready_for_pickup', 'ok', now() - interval '7 days'),
  -- Reopened two hours ago and still not checked: listed.
  ('19000000-0000-0000-0000-000000006202', '19000000-0000-0000-0000-000000000062', 'EXAMPLEREOPEN02', 'speedx',
    'ready_for_pickup', 'ok', now() - interval '7 days'),
  -- On its way and not checked for two days, with no hand fix: listed.
  ('19000000-0000-0000-0000-000000006203', '19000000-0000-0000-0000-000000000062', 'EXAMPLEREOPEN03', 'speedx',
    'in_transit', 'ok', now() - interval '2 days');

insert into private.hand_stage_changes (package_id, changed_at, from_stage, to_stage, fix)
values
  ('19000000-0000-0000-0000-000000006201', now() - interval '10 minutes', 'delivered', 'ready_for_pickup', 'stored-scan replay'),
  ('19000000-0000-0000-0000-000000006202', now() - interval '2 hours', 'delivered', 'ready_for_pickup', 'stored-scan replay');

commit;
