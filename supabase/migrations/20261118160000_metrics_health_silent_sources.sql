-- A provider can stop answering with scans while every lookup still counts as healthy: ParcelsApp
-- answered "no history" for every number for a day, each one a not_found, and nothing alerted.
-- silent_sources_3h counts the carriers and providers asked about 20 parcels or more in the last
-- 3 hours that found none of them; ordinary days never reach it.
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
    where incident.active and incident.kind <> 'direct' and exists (
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
          and 'future_event_timestamp' = any(earlier.anomaly_codes))) as future_scan_parcels_1h,
  (select count(*) from (
    select sample.subject from public.tracking_health_samples as sample
    where sample.kind = 'provider' and sample.observed_at > now() - interval '3 hours'
    group by sample.subject
    having count(*) >= 20 and count(*) filter (where sample.details ->> 'category' = 'ok') = 0) as silent)
    as silent_sources_3h;

comment on view metrics.health is
  'How parcel checks are going: checks that asked a carrier or provider, failed checks and the carriers they failed for in the last 3 hours; seconds since a check last finished (86400 when none did in a day); the longest scheduled sync, in seconds, that finished in the last 2 hours; open health incidents, those of them that failed in the last 3 hours, and the carriers of those whose failures left a check without an answer, each carrier once and a direct step''s incident left out; parcels whose first check to find a scan dated in the future started in the last hour; carriers and providers asked about 20 parcels or more in the last 3 hours that found none of them.';

notify pgrst, 'reload schema';
insert into public.applied_migrations (name) values ('20261118160000_metrics_health_silent_sources') on conflict do nothing;
