-- The carriers whose own site is down now, which a parcel tracked by one of them tells its reader (carrierOutages.ts).
-- A carrier is down while its lookup or its parcels' scheduled checks have an open incident (record_tracking_health),
-- and every lookup since the carrier last answered that failed on its own site failed on the site itself: no
-- connection, a maintenance page or a server error. A bot check, a page Peek could not read or a number the carrier
-- does not know is not the carrier's outage, and a step through another service (`trawl`) says nothing of its site.
-- One parcel's failures are the carrier's only once three of them have lasted an hour: the site down for every
-- number, as colisprive.com was. Any answer since ends it at once, before the incident recovers. `since` is the first
-- such failure after the carrier's last answer.

create or replace function public.carrier_outages()
returns table(carrier text, since timestamptz)
language sql stable set search_path = pg_catalog as $$
  with open_incident as (
    select distinct i.subject from public.tracking_health_incidents i
    where i.active and i.kind in ('refresh', 'provider') and coalesce(i.pending->>'state', 'open') = 'open'
  ),
  lookup as (
    select s.subject, s.observed_at, s.attempt_id,
      coalesce(p.tracking_number, a.package_id::text, s.attempt_id::text) as parcel,
      -- The carrier's site replied, a not-found included. A refusal under a quota every caller shares and a lookup
      -- waiting on rate limits say nothing of it.
      s.healthy and coalesce(s.details->>'category', '') not in ('shared_quota', 'rate_limit_wait', 'unanswered') as answered,
      coalesce(not s.healthy and (s.details->>'step' in ('direct', 'retry', 'browser')
        or s.details->>'reason' in ('maintenance_page', 'carrier_unavailable')), false) as failed_on_site,
      -- Never null: bool_and below would pass over a failure it cannot tell.
      coalesce((s.details->>'category' = 'transport' and coalesce(s.details->>'reason', '') in ('', 'lookup_unavailable'))
        or s.details->>'category' = 'maintenance'
        or (s.details->>'category' = 'indeterminate' and s.details->>'reason' in ('maintenance_page', 'carrier_unavailable'))
        or (jsonb_typeof(s.details->'http_status') = 'number' and (s.details->>'http_status')::numeric >= 500), false) as site_down
    from public.tracking_health_samples s
    join open_incident o on o.subject = s.subject
    left join public.tracking_sync_attempts a on a.id = s.attempt_id
    left join public.packages p on p.id = a.package_id
    where s.kind = 'provider'
  ),
  last_answer as (
    select subject, max(observed_at) filter (where answered) as at from lookup group by subject
  )
  select l.subject, min(l.observed_at)
  from lookup l join last_answer a using (subject)
  where l.failed_on_site and l.observed_at > coalesce(a.at, '-infinity'::timestamptz)
  group by l.subject
  having bool_and(l.site_down)
    and (count(distinct l.parcel) >= 2
      or (count(distinct l.attempt_id) >= 3 and max(l.observed_at) - min(l.observed_at) >= interval '1 hour'));
$$;

revoke all on function public.carrier_outages() from public, anon, authenticated;
grant execute on function public.carrier_outages() to service_role;

notify pgrst, 'reload schema';

insert into public.applied_migrations (name) values ('20261119200000_carrier_outages') on conflict do nothing;
