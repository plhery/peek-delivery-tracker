-- Direct-step samples for the review's "Direct steps failing behind a fallback" section (ops/review/signals.sql, read
-- by scripts/test-migrations.sh): a direct failure whose check's refresh answered is rescued, and a carrier is listed
-- once ten of its failures were.
\set ON_ERROR_STOP on
begin;

-- review-direct: 12 failures its refresh answered, 2 it did not, 1 direct step that answered.
insert into public.tracking_health_samples (attempt_id, kind, subject, healthy, details, observed_at)
select attempt, kind, 'review-direct', healthy, details, now() - interval '1 hour'
from (select gen_random_uuid() as attempt, n from generate_series(1, 15) as n) checks
cross join lateral (values
  ('direct', n = 15, case when n = 15 then '{}' else '{"category": "challenge"}' end::jsonb),
  ('refresh', n not in (13, 14), '{}'::jsonb)) as sample(kind, healthy, details);

-- review-direct-few: nine rescued failures, one short of the list.
insert into public.tracking_health_samples (attempt_id, kind, subject, healthy, details, observed_at)
select attempt, kind, 'review-direct-few', kind = 'refresh', '{"category": "transport"}', now() - interval '1 hour'
from (select gen_random_uuid() as attempt from generate_series(1, 9)) checks
cross join (values ('direct'), ('refresh')) as sample(kind);

commit;
