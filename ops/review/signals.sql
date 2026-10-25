-- What the review queues learnt and readers reported in the last hours, grouped by type,
-- carrier and provider, each with the lane that acts on it: carriers (number formats,
-- detection, the catalog), adapters (no adapter, a failing one, unmapped wording), captcha
-- (a verification page stops an adapter) or delivery-tracker (the app's routing). Numbers
-- appear only as their shape, and free text with its rare words, which can name a place, a
-- shop or a person, masked. Read-only; see docs/OBSERVABILITY.md.
--
--   psql "$DATABASE_URL" -v hours=1 -f ops/review/signals.sql
\set ON_ERROR_STOP on
\set QUIET on
\if :{?hours}
\else
  \set hours 1
\endif
set session characteristics as transaction read only;
\pset null '-'
\pset footer off
\set since '(now() - make_interval(hours => :hours))'

\echo
\echo '== Routing signals: numbers seen in the window (new: first seen in it)'
select signal, carrier, subject,
       case when signal = 'direct_support_opportunity' then 'adapters' else 'carriers' end as lane,
       count(*) filter (where first_seen >= :since) as new, count(*) as numbers,
       array_to_string((array_agg(distinct private.tracking_number_pattern(tracking_number)))[1:3], '; ') as patterns
from public.tracking_routing_signals
where last_seen >= :since and reviewed_at is null
group by signal, carrier, subject
order by new desc, numbers desc, signal, carrier, subject;

\echo '== Sync anomalies: checks in the window'
-- `looping_track` is no check's own: a parcel checked in the window whose moves in transit, each
-- to a new place, come at one interval, to within 2 s, three times or more, the latest in the last
-- 3 days. No network moves a parcel on at the same interval to the second: the track is generated.
-- Intervals in whole minutes are left out, as a carrier that rounds its clock gives them.
with checks as (
  select package_id, configured_carrier as carrier, coalesce(source_carrier, '-') as served_by, anomaly_codes
  from public.tracking_sync_attempts
  where started_at >= :since
),
scans as (
  select package_id, occurred_at, bool_and(stage = 'in_transit') as transit,
         min(lower(trim(location))) as place, count(distinct lower(trim(location))) as places
  from public.tracking_events
  where package_id in (select package_id from checks)
  group by package_id, occurred_at
),
moves as (
  select package_id, occurred_at, extract(epoch from occurred_at - lag(occurred_at) over w)::bigint as gap,
         transit and lag(transit) over w and places = 1 and lag(places) over w = 1 and place <> lag(place) over w as moved
  from scans
  window w as (partition by package_id order by occurred_at)
),
looping as (
  select package_id
  from (
    select package_id, occurred_at,
           count(*) over (partition by package_id order by gap range between 2 preceding and 2 following) as alike
    from moves
    where moved and gap between 3600 and 172800 and gap % 60 <> 0
  ) as repeated
  where alike >= 3
  group by package_id
  having max(occurred_at) >= now() - interval '3 days'
)
select carrier, served_by, anomaly,
       -- A worker that died mid-check and a generated track are the app's; every other anomaly is a carrier's or provider's answer.
       case when anomaly in ('worker_abandoned_attempt', 'looping_track') then 'delivery-tracker' else 'adapters' end as lane,
       count(*) as checks, count(distinct package_id) as parcels
from (
  select c.carrier, c.served_by, code as anomaly, c.package_id from checks as c cross join unnest(c.anomaly_codes) as code
  union all
  select c.carrier, c.served_by, 'looping_track', c.package_id from checks as c join looping using (package_id)
) as anomalies
group by 1, 2, 3, 4
order by checks desc, carrier, anomaly;

\echo '== Future scans: parcels first flagged in the window with a scan dated over 1 h after its check'
\echo '   usually a clock read in the wrong zone; provider: the universal provider of the future scan, else the check''s source'
-- future_event_timestamp, counted above per carrier: a parcel shows once, at its first check that flags it,
-- not at the checks after that repeat the scan.
select left(a.package_id::text, 8) as parcel, a.configured_carrier as carrier,
       coalesce((select string_agg(distinct e.raw_data->>'universal_provider', ',')
                 from public.tracking_events as e
                 where e.package_id = a.package_id and e.occurred_at > a.started_at + interval '1 hour'),
                a.source_carrier, '-') as provider,
       private.tracking_number_pattern(p.tracking_number) as pattern, 'adapters' as lane,
       to_char(a.started_at, 'YYYY-MM-DD HH24:MI') as flagged
from (
  select distinct on (package_id) package_id, configured_carrier, source_carrier, started_at
  from public.tracking_sync_attempts
  where started_at >= :since and 'future_event_timestamp' = any(anomaly_codes)
  order by package_id, started_at
) as a
left join public.packages as p on p.id = a.package_id
where not exists (
  select from public.tracking_sync_attempts as earlier
  where earlier.package_id = a.package_id and earlier.started_at < :since
    and 'future_event_timestamp' = any(earlier.anomaly_codes))
order by a.started_at desc;

\echo '== Unmapped wording: open and seen in the window (new: first seen in it; … masks a rare word; look the wording up by id)'
-- Wording can quote a number or name a place, a shop or a person: its digits are masked, and so
-- is each word fewer than five stored wordings share once their digits are masked, so that
-- wordings differing only by a date or a time count once.
-- A carrier's wording last seen on a server version that another replaced more than 2 days ago
-- is left out: a followed parcel is checked again within a day, so the versions since no longer
-- produce it, and the replay expires its row after 8 days unseen (src/server/reviewQueues.ts).
-- Wording a universal provider relays (carrier `unknown`) is the provider's own and stays. It
-- shows as relayed by the provider its sample scan names, beside the carrier of that scan's parcel.
with templates as (
  select o.id, o.carrier, o.provider_code, o.chosen_stage, o.count, o.first_seen, o.last_seen, o.last_seen_version, o.reviewed_at,
         regexp_replace(o.description_normalized, '[0-9]+', '#', 'g') as template,
         case when o.carrier <> 'unknown' then o.carrier
           else 'relayed by ' || coalesce(e.raw_data->>'universal_provider', 'a provider') || coalesce(' (' || p.carrier || ' parcel)', '') end as shown
  from public.tracking_status_observations as o
  left join public.tracking_events as e on o.carrier = 'unknown' and e.id = o.sample_event_id
  left join public.packages as p on p.id = e.package_id
),
common as (
  select word
  from (select distinct template from templates) as t cross join regexp_split_to_table(t.template, '[^[:alpha:]]+') as word
  where word <> ''
  group by word
  having count(*) >= 5
)
select t.id, t.shown as carrier, coalesce(t.provider_code, '-') as code,
       left(regexp_replace(string_agg(case when common.word is null and token.part[1] ~ '[[:alpha:]]' then '…' else token.part[1] end,
         '' order by token.n), '…([^[:alpha:]]*…)+', '…', 'g'), 80) as wording,
       t.chosen_stage as read_as, 'adapters' as lane, t.first_seen >= :since as new, t.count as sightings
from templates as t
cross join regexp_matches(t.template, '[[:alpha:]]+|[^[:alpha:]]+', 'g') with ordinality as token(part, n)
left join common on common.word = token.part[1]
where t.reviewed_at is null and t.last_seen >= :since
  and not (t.carrier <> 'unknown' and exists (
    select from public.tracking_review_runs as run
    where run.started_at > t.last_seen and run.started_at < now() - interval '2 days'
      and run.version is distinct from t.last_seen_version))
group by t.id, t.shown, t.provider_code, t.chosen_stage, t.first_seen, t.count
order by new desc, sightings desc, carrier, wording
limit 50;

\echo '== Support cases: opened in the window, or held until then, and still open with a reason'
select configured_carrier as carrier, detection_carrier as detected, array_to_string(reasons, ',') as reasons,
       array_to_string(array_remove(array[
         case when reasons && array['unknown_shape', 'ambiguous_shape', 'generic_postal', 'carrier_mismatch',
           'recognition_unknown', 'recognition_choice'] then 'carriers' end,
         case when 'no_direct_adapter' = any(reasons) then 'adapters' end], null), '+') as lane,
       count(*) as cases,
       array_to_string((array_agg(distinct private.tracking_number_pattern(tracking_number)))[1:3], '; ') as patterns
from public.tracking_support_cases
-- A case held for a carrier recognition asks only through a browser waits until its hold ends.
-- One whose parcel's latest check left no reason, its carrier having confirmed the number, needs
-- no work: the next release's replay marks it fixed (reviewQueues.ts).
where coalesce(held_until, first_seen) >= :since and coalesce(held_until, first_seen) <= now() and fix_status = 'open'
  and cardinality(reasons) > 0
group by 1, 2, 3, 4
order by cases desc, carrier;

\echo '== Health incidents: notified in the window, or open and failing in it'
\echo '   rescued: every check that failed still answered through another step; open: no failure in the window,'
\echo '   the incident waits for three healthy checks, or closes once nothing was checked for 24 h'
select i.kind, i.subject,
       case when not i.active then 'over'
            when count(s.healthy) filter (where not s.healthy) = 0 then 'open'
            when count(s.healthy) filter (where not s.healthy and not s.rescued) = 0 then 'rescued'
            else 'failing' end as state,
       'adapters' as lane, to_char(i.last_notified_at, 'YYYY-MM-DD HH24:MI') as notified,
       count(s.healthy) filter (where not s.healthy) as failures, count(s.healthy) as checks
from public.tracking_health_incidents i
left join lateral (
  -- A direct step's failure is rescued when its check's refresh answered, as record_tracking_health reads it.
  select sample.healthy, sample.kind = 'direct' and exists (
           select 1 from public.tracking_health_samples refresh
           where refresh.attempt_id = sample.attempt_id and refresh.kind = 'refresh' and refresh.healthy) as rescued
  from public.tracking_health_samples sample
  where sample.kind = i.kind and sample.subject = i.subject and sample.observed_at >= :since
) s on true
where i.last_notified_at >= :since or i.active or i.pending is not null
group by i.kind, i.subject, i.active, i.last_notified_at, i.pending is not null
-- An open incident whose checks answered in the window, or that nothing checked, is not listed again
-- until it fails, its six-hourly reminder goes out, or it recovers or closes.
having i.last_notified_at >= :since or i.pending is not null or count(s.healthy) filter (where not s.healthy) > 0
order by i.active desc, i.last_notified_at desc;

\echo '== Unfinished parcels a provider tracks, by the carrier that could take them (new: added in the window)'
\echo '   carriers: no carrier known; adapters: the adapter refuses, fails or half-answers the number, answered it without'
\echo '   movement, on local clocks only, without dates or behind a provider''s delivered or returned stage, or gave no'
\echo '   direct answer in 30 days; captcha: a verification or captcha page stops the adapter; dormant: not checked for 24 h'
\echo '   because nobody follows it (a lookup whose link nobody opened for 24 h and that has no alert: opening its link checks it);'
\echo '   none: the carrier answers and the provider history is kept or fills in its partial answer, or the parcel'
\echo '   already shows the newest scan of its answer set aside as older (answer shows an earlier leg);'
\echo '   delivery-tracker: its adapter answers other parcels and was not asked for these (carrier not asked),'
\echo '   or its answer was set aside as older than the parcel''s newest scan (answer older than parcel)'
\echo '   could_take: the carrier that answered or a provider names, else the one detection names for an unfiled parcel'
-- private.tracking_gaps classifies them, as the carrier dashboard's goals count them.
select carrier, could_take, provider, lane, why,
       count(*) filter (where created_at >= :since) as new, count(*) as parcels,
       array_to_string((array_agg(distinct private.tracking_number_pattern(tracking_number)))[1:3], '; ') as patterns
from private.tracking_gaps
where provider is not null
group by 1, 2, 3, 4, 5
order by parcels desc, could_take, provider;

\echo '== Followed parcels not checked for 24 h: a scheduler fault, none expected (new: added in the window)'
\echo '   an account''s parcel with a scan, or a lookup with an alert or a link opened in 24 h; never-checked Amazon parcels'
\echo '   and routing deferrals left out. Lookups nobody follows leave the schedule (the dormant lane above).'
select case when package.user_id is not null then 'account' else 'lookup' end as kind, package.carrier,
       package.carrier_data->>'tracking_provider' as provider, package.sync_status,
       count(*) filter (where package.created_at >= :since) as new, count(*) as parcels,
       min(package.last_synced_at)::timestamp(0) as oldest_check
from public.packages as package
where package.archived_at is null and package.current_stage not in ('pending', 'delivered', 'returned')
  and package.last_synced_at < now() - interval '24 hours' and package.sync_status is distinct from 'unsupported'
  and not coalesce(case when pg_input_is_valid(package.carrier_data->'routing'->>'next_check_at', 'timestamptz')
    then (package.carrier_data->'routing'->>'next_check_at')::timestamptz > now() end, false)
  and (package.user_id is not null or (package.one_off and exists (
    select 1 from public.parcel_links as link
    where link.package_id = package.id and (link.last_opened_at > now() - interval '24 hours' or exists (
      select 1 from public.parcel_link_alerts as alert where alert.link_id = link.id and (link.shared or alert.owner))))))
group by 1, 2, 3, 4
order by parcels desc, kind, carrier;

\echo '== Reader feedback: answers other than right not reviewed yet (new: given or completed with a note in the window)'
\echo '   carriers: no carrier found, or the wrong one; adapters: the carrier''s status, steps, times or places;'
\echo '   delivery-tracker: no reason given. Close each one once it is routed or handled:'
\echo '   ops/review/close-feedback.sql with its id and a review note (docs/OBSERVABILITY.md)'
select format('%s right, %s wrong, %s found elsewhere', count(*) filter (where answer = 'right'),
         count(*) filter (where answer = 'wrong'), count(*) filter (where answer = 'found_elsewhere')) as counts
from public.parcel_feedback
where created_at >= :since \gset feedback_
\echo '   answers given in the window:' :feedback_counts
-- A reader's words can quote a number or name a person or a shop: a note is only counted, a
-- carrier's name shows its first three words with digits masked, and a tracking page shows its
-- host alone.
select f.id, f.updated_at >= :since as new, to_char(f.created_at, 'YYYY-MM-DD HH24:MI') as given,
       f.carrier, f.answer,
       coalesce(nullif(array_to_string(f.reasons, ','), ''), '-') as reasons,
       case when f.note is null then 'no' else 'yes' end as note,
       coalesce(left(substring(regexp_replace(f.carrier_name, '[0-9]+', '#', 'g') from '^\s*([^[:space:]]+(?:\s+[^[:space:]]+){0,2})'), 40),
         lower(substring(f.tracking_page
           from '^(?:[[:alpha:]][[:alnum:]+.-]*://)?(?:[^/?#@[:space:]]*@)?([[:alnum:]-]+(?:\.[[:alnum:]-]+)+)(?:[:/?#]|$)')),
         case when f.tracking_page is not null then '<page>' end, '-') as named,
       private.tracking_number_pattern(f.tracking_number) as pattern,
       case when f.answer = 'found_elsewhere' or 'carrier' = any(f.reasons) or f.carrier in ('unknown', 'intl-post')
              then 'carriers'
            when f.reasons && array['arrived', 'status', 'steps', 'time_place'] then 'adapters'
            else 'delivery-tracker' end as lane
from public.parcel_feedback f
-- An answer stays listed until it is closed, so one that was never routed comes back every
-- hour. A note can follow its reason within the hour: updated_at marks it new again once it has.
where f.answer <> 'right' and f.reviewed_at is null
order by new desc, f.created_at desc;
