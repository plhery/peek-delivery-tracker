-- Stage step-backs for the hourly review's "Stage step-backs" section (ops/review/signals.sql, read by
-- scripts/test-migrations.sh), in the 24 hours before 09:00 UTC on 1 January 2026.
\set ON_ERROR_STOP on
begin;

insert into auth.users (id, email) values ('19000000-0000-0000-0000-000000000002', 'review-step-backs@example.test');

insert into public.packages (id, user_id, tracking_number, carrier, current_stage)
values
  -- Delivered and checked again; a check the fence refused had loaded the stage from before the delivery.
  ('29000000-0000-0000-0000-000000000201', '19000000-0000-0000-0000-000000000002', 'EXAMPLESTEP0201', 'la-poste', 'delivered'),
  -- Delivered, then a check saved in transit: a step back.
  ('19000000-0000-0000-0000-000000000202', '19000000-0000-0000-0000-000000000002', 'EXAMPLESTEP0202', 'la-poste', 'in_transit');

insert into public.tracking_sync_attempts (package_id, trigger, configured_carrier, started_at, previous_stage, outcome, completed_at)
select package_id::uuid, 'scheduled', 'la-poste', started_at::timestamptz, previous_stage, outcome, started_at::timestamptz + interval '1 minute'
from (values
  ('29000000-0000-0000-0000-000000000201', '2025-12-31T10:00:00Z', 'in_transit', 'updated'),
  ('29000000-0000-0000-0000-000000000201', '2025-12-31T11:00:00Z', 'delivered', 'updated'),
  ('29000000-0000-0000-0000-000000000201', '2025-12-31T12:00:00Z', 'in_transit', 'superseded'),
  ('29000000-0000-0000-0000-000000000201', '2025-12-31T13:00:00Z', 'delivered', 'updated'),
  ('19000000-0000-0000-0000-000000000202', '2025-12-31T10:00:00Z', 'delivered', 'updated'),
  ('19000000-0000-0000-0000-000000000202', '2025-12-31T11:00:00Z', 'in_transit', 'updated')
) as checks (package_id, started_at, previous_stage, outcome);

-- A later leg added as a parcel of its own, then linked: the parcel merged into it brings its checks, filed under its
-- original carrier, which ran beside the later leg's until the link. Neither parcel stepped back.
insert into public.packages (id, user_id, tracking_number, carrier, current_stage, carrier_data)
values ('39000000-0000-0000-0000-000000000203', '19000000-0000-0000-0000-000000000002', 'EXAMPLESTEP0203', 'la-poste',
  'in_transit', '{"original_carrier": "colissimo", "original_package_id": "39000000-0000-0000-0000-000000000299"}');

insert into public.tracking_sync_attempts (package_id, trigger, configured_carrier, started_at, previous_stage, outcome, completed_at)
select '39000000-0000-0000-0000-000000000203', trigger, carrier, started_at::timestamptz, previous_stage, 'updated',
  started_at::timestamptz + interval '1 minute'
from (values
  ('scheduled', 'colissimo', '2025-12-31T10:00:00Z', 'in_transit'),
  ('added', 'la-poste', '2025-12-31T10:30:00Z', 'pending'),
  ('scheduled', 'colissimo', '2025-12-31T10:40:00Z', 'in_transit'),
  ('scheduled', 'la-poste', '2025-12-31T10:50:00Z', 'registered'),
  ('scheduled', 'colissimo', '2025-12-31T11:00:00Z', 'in_transit'),
  ('scheduled', 'la-poste', '2025-12-31T11:10:00Z', 'in_transit')
) as checks (trigger, carrier, started_at, previous_stage);

commit;
