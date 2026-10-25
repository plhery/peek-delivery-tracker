-- A check that finds a scan dated over an hour after it flags future_event_timestamp, usually a
-- carrier clock read in the wrong zone. Nothing alerted on it: the review signals listed the
-- parcels a day later. future_scan_parcels_1h counts the parcels whose first flagged check
-- started in the last hour, so a parcel alerts once, not at each check that repeats the scan.
create or replace view metrics.health as
select
  (select count(*) from public.tracking_sync_attempts as attempt
    where attempt.started_at > now() - interval '3 hours'
      and attempt.outcome not in ('running', 'superseded')) as checks_3h,
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
  'How parcel checks are going: checks, failed checks and the carriers they failed for in the last 3 hours; seconds since a check last finished (86400 when none did in a day); the longest scheduled sync, in seconds, that finished in the last 2 hours; open health incidents, those of them that failed in the last 3 hours, and the carriers of those whose failures left a check without an answer, each carrier once; parcels whose first check to find a scan dated in the future started in the last hour.';

insert into public.applied_migrations (name) values ('20261025190000_metrics_health_future_scans') on conflict do nothing;
