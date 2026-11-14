-- Parcels whose kept plan has a step dated in 2025, for the hourly review's "Planned steps passed" section
-- (ops/review/signals.sql, read by scripts/test-migrations.sh): a passed step at a clock the carrier holds back as
-- written ahead is served apart (written), any other passed step only if the rule that hides it broke (passed).
\set ON_ERROR_STOP on
begin;

insert into auth.users (id, email) values ('19000000-0000-0000-0000-000000000002', 'review-planned-steps@example.test');

insert into public.packages (id, user_id, tracking_number, carrier, current_stage, carrier_data)
values
  -- Its passed delivery is at a clock held back, written with an offset: written.
  ('19000000-0000-0000-0000-000000000201', '19000000-0000-0000-0000-000000000002', 'EXAMPLEPLAN0201', 'gzyl', 'pending',
   '{"routing": {"planned": {"number": "EXAMPLEPLAN0201", "carriers": {"gzyl": [
      {"time": "2025-11-07T15:00:00+08:00", "description": "Delivered", "stage": "delivered"},
      {"time": "2099-01-01T10:00:00+08:00", "description": "Proof of delivery filed"}]}},
     "written_ahead": {"number": "EXAMPLEPLAN0201", "carriers": {"gzyl": ["2025-11-07T15:00:00+08:00", "2099-01-01T10:00:00+08:00"]}}}}'),
  -- Its passed step is dated by its day alone, and a clock held back falls on that day: written.
  ('19000000-0000-0000-0000-000000000202', '19000000-0000-0000-0000-000000000002', 'EXAMPLEPLAN0202', 'gzyl', 'in_transit',
   '{"routing": {"planned": {"number": "EXAMPLEPLAN0202", "carriers": {"gzyl": [
      {"local_time": "2025-11-06", "description": "Out for delivery", "stage": "out_for_delivery"}]}},
     "written_ahead": {"number": "EXAMPLEPLAN0202", "carriers": {"gzyl": ["2025-11-06T10:00:00"]}}}}'),
  -- Its passed step is at no clock held back: only a broken rule shows it.
  ('19000000-0000-0000-0000-000000000203', '19000000-0000-0000-0000-000000000002', 'EXAMPLEPLAN0203', 'gzyl', 'in_transit',
   '{"routing": {"planned": {"number": "EXAMPLEPLAN0203", "carriers": {"gzyl": [
      {"time": "2025-11-07T15:00:00+08:00", "description": "Delivered", "stage": "delivered"}]}},
     "written_ahead": {"number": "EXAMPLEPLAN0203", "carriers": {"gzyl": ["2025-11-07T16:00:00+08:00"]}}}}'),
  -- The clocks held back are another number's: passed.
  ('19000000-0000-0000-0000-000000000204', '19000000-0000-0000-0000-000000000002', 'EXAMPLEPLAN0204', 'gzyl', 'in_transit',
   '{"routing": {"planned": {"number": "EXAMPLEPLAN0204", "carriers": {"gzyl": [
      {"time": "2025-11-07T15:00:00+08:00", "description": "Delivered", "stage": "delivered"}]}},
     "written_ahead": {"number": "EXAMPLEPLAN0299", "carriers": {"gzyl": ["2025-11-07T15:00:00+08:00"]}}}}');

commit;
