-- A provider counts as silent once it was asked about 20 parcels, not 20 times: a provider sample keeps
-- no parcel of its own (record_tracking_health stores one on refresh samples only), its check attempt does.
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
    join public.tracking_sync_attempts as attempt on attempt.id = sample.attempt_id
    where sample.kind = 'provider' and sample.observed_at > now() - interval '3 hours'
    group by sample.subject
    having count(distinct attempt.package_id) >= 20 and count(*) filter (where sample.details ->> 'category' = 'ok') = 0) as silent)
    as silent_sources_3h;

insert into public.applied_migrations (name) values ('20261118190400_distinct_silent_parcels') on conflict do nothing;
