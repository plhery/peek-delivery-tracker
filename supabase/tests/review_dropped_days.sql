-- Parcels whose expected day a check dropped just after midnight UTC on 1 January 2026, for the hourly review's
-- "Expected days dropped" section (ops/review/signals.sql, read by scripts/test-migrations.sh): the day each check found
-- was 31 December, and only the parcel's own clock says whether it had passed.
\set ON_ERROR_STOP on
begin;

insert into auth.users (id, email) values ('19000000-0000-0000-0000-000000000001', 'review-dropped-days@example.test');

insert into public.packages (id, user_id, tracking_number, carrier, current_stage, expected_delivery_changed_at, carrier_data)
values
  -- Its newest scan states Paris's offset: 01:30 on 1 January there, the day had passed.
  ('19000000-0000-0000-0000-000000000101', '19000000-0000-0000-0000-000000000001', 'EXAMPLEDROP0101', 'la-poste', 'in_transit',
   '2026-01-01T00:30:00Z', '{"last_update": "2025-12-31T18:00:00+01:00"}'),
  -- Its answer declares Los Angeles's zone: 22:00 on 31 December there, the day was still on.
  ('19000000-0000-0000-0000-000000000102', '19000000-0000-0000-0000-000000000001', 'EXAMPLEDROP0102', 'la-poste', 'in_transit',
   '2026-01-01T06:00:00Z', '{"timezone": "America/Los_Angeles"}'),
  -- A "Z" states no clock: the declared zone, Paris, reads 07:00 on 1 January.
  ('19000000-0000-0000-0000-000000000103', '19000000-0000-0000-0000-000000000001', 'EXAMPLEDROP0103', 'la-poste', 'in_transit',
   '2026-01-01T06:00:00Z', '{"last_update": "2025-12-31T17:00:00Z", "timezone": "Europe/Paris"}'),
  -- No clock of its own: passed on some clocks only.
  ('19000000-0000-0000-0000-000000000104', '19000000-0000-0000-0000-000000000001', 'EXAMPLEDROP0104', 'la-poste', 'in_transit',
   '2026-01-01T06:00:00Z', '{}');

insert into public.tracking_sync_attempts (package_id, trigger, configured_carrier, started_at, previous_expected_delivery)
select id, 'scheduled', 'la-poste', expected_delivery_changed_at - interval '1 minute', '2025-12-31'
from public.packages where user_id = '19000000-0000-0000-0000-000000000001';

commit;
