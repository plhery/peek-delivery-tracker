-- A check that asks no carrier and no provider, every source still waiting out a failure or an
-- input only the owner can give, ends `skipped`: it says nothing about any carrier's health.
-- Neither the failure counts here and in metrics.health nor their totals count it; the 24-hour
-- health counts it apart.
alter table public.tracking_sync_attempts drop constraint tracking_sync_attempts_outcome_check;
alter table public.tracking_sync_attempts add constraint tracking_sync_attempts_outcome_check
  check (outcome in ('running', 'updated', 'unchanged', 'waiting', 'error', 'refused', 'unsupported', 'abandoned',
    'superseded', 'interrupted', 'skipped'));

-- Appends its column: a replaced view keeps the ones it had, in order.
create or replace view public.tracking_sync_health_24h
with (security_invoker = true)
as
select
  configured_carrier,
  count(*) filter (where outcome <> 'skipped') as attempts,
  count(*) filter (where outcome = 'updated') as updated,
  count(*) filter (where outcome = 'waiting') as waiting,
  count(*) filter (where outcome = 'error') as errors,
  count(*) filter (where outcome = 'unsupported') as unsupported,
  count(*) filter (where outcome = 'abandoned') as abandoned,
  count(*) filter (where cardinality(anomaly_codes) > 0) as anomalous,
  round(
    100.0 * count(*) filter (where outcome in ('error', 'abandoned'))
      / nullif(count(*) filter (where outcome <> 'skipped'), 0),
    2
  ) as error_percent,
  max(started_at) as last_attempt_at,
  count(*) filter (where outcome = 'unchanged') as unchanged,
  sum(events_new) as events_new,
  count(*) filter (where outcome = 'refused') as refused,
  count(*) filter (where outcome = 'skipped') as skipped
from public.tracking_sync_attempts
where started_at >= now() - interval '24 hours'
group by configured_carrier;

create or replace view metrics.health as
select
  (select count(*) from public.tracking_sync_attempts as attempt
    where attempt.started_at > now() - interval '3 hours'
      and attempt.outcome not in ('running', 'superseded', 'skipped')) as checks_3h,
  (select count(*) from public.tracking_sync_attempts as attempt
    where attempt.started_at > now() - interval '3 hours' and attempt.outcome = 'error') as failed_checks_3h,
  (select count(distinct attempt.configured_carrier) from public.tracking_sync_attempts as attempt
    where attempt.started_at > now() - interval '3 hours' and attempt.outcome = 'error') as failed_carriers_3h,
  (select coalesce(extract(epoch from now() - max(attempt.completed_at)), 86400)::integer
    from public.tracking_sync_attempts as attempt
    where attempt.started_at > now() - interval '1 day' and attempt.completed_at is not null)
    as seconds_since_check,
  (select coalesce(max(extract(epoch from job.completed_at - job.started_at)), 0)::integer
    from public.sync_jobs as job
    where job.kind = 'scheduled' and job.state in ('succeeded', 'failed')
      and job.completed_at > now() - interval '2 hours') as longest_scheduled_sync_2h,
  (select count(*) from public.tracking_health_incidents as incident where incident.active)
    as open_health_incidents,
  (select count(*) from public.tracking_health_incidents as incident
    where incident.active and exists (
      select 1 from public.tracking_health_samples as sample
      where sample.kind = incident.kind and sample.subject = incident.subject
        and not sample.healthy and sample.observed_at > now() - interval '3 hours'))
    as failing_health_incidents,
  (select count(distinct incident.subject) from public.tracking_health_incidents as incident
    where incident.active and exists (
      select 1 from public.tracking_health_samples as sample
      where sample.kind = incident.kind and sample.subject = incident.subject
        and not sample.healthy and sample.observed_at > now() - interval '3 hours'
        and not exists (
          select 1 from public.tracking_health_samples as refresh
          where refresh.attempt_id = sample.attempt_id and refresh.kind = 'refresh' and refresh.healthy)))
    as failing_incident_carriers,
  (select count(distinct attempt.package_id) from public.tracking_sync_attempts as attempt
    where attempt.started_at > now() - interval '1 hour'
      and 'future_event_timestamp' = any(attempt.anomaly_codes)
      and not exists (
        select 1 from public.tracking_sync_attempts as earlier
        where earlier.package_id = attempt.package_id
          and earlier.started_at <= now() - interval '1 hour'
          and 'future_event_timestamp' = any(earlier.anomaly_codes))) as future_scan_parcels_1h;

comment on view metrics.health is
  'How parcel checks are going: checks that asked a carrier or provider, failed checks and the carriers they failed for in the last 3 hours; seconds since a check last finished (86400 when none did in a day); the longest scheduled sync, in seconds, that finished in the last 2 hours; open health incidents, those of them that failed in the last 3 hours, and the carriers of those whose failures left a check without an answer, each carrier once; parcels whose first check to find a scan dated in the future started in the last hour.';

insert into public.applied_migrations (name) values ('20261029110000_skipped_tracking_checks') on conflict do nothing;
