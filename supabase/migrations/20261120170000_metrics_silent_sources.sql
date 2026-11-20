-- The carriers and providers metrics.health's silent_sources_3h counts, by name: Grafana's rule fires once for each,
-- labelled with it, rather than once for all of them with nothing to say which.
create view metrics.silent_sources as
select sample.subject as source, count(distinct attempt.package_id) as parcels_3h
from public.tracking_health_samples as sample
join public.tracking_sync_attempts as attempt on attempt.id = sample.attempt_id
where sample.kind = 'provider' and sample.observed_at > now() - interval '3 hours'
group by sample.subject
having count(distinct attempt.package_id) >= 20 and count(*) filter (where sample.details ->> 'category' = 'ok') = 0;

comment on view metrics.silent_sources is
  'The carriers and providers asked about 20 parcels or more in the last 3 hours that found none of them (metrics.health''s silent_sources_3h), each with those parcels.';
grant select on metrics.silent_sources to metrics_reader;

notify pgrst, 'reload schema';
insert into public.applied_migrations (name) values ('20261120170000_metrics_silent_sources') on conflict do nothing;
