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
select a.configured_carrier as carrier, coalesce(a.source_carrier, '-') as served_by, code as anomaly,
       -- A worker that died mid-check is the app's; every other anomaly is a carrier's or provider's answer.
       case when code = 'worker_abandoned_attempt' then 'delivery-tracker' else 'adapters' end as lane,
       count(*) as checks, count(distinct a.package_id) as parcels
from public.tracking_sync_attempts a cross join unnest(a.anomaly_codes) as code
where a.started_at >= :since
group by 1, 2, 3, 4
order by checks desc, carrier, anomaly;

\echo '== Unmapped wording: open and seen in the window (new: first seen in it; … masks a rare word; look the wording up by id)'
-- Wording can quote a number or name a place, a shop or a person: its digits are masked, and so
-- is each word fewer than five stored wordings share once their digits are masked, so that
-- wordings differing only by a date or a time count once.
-- A carrier's wording last seen on a server version that another replaced more than 2 days ago
-- is left out: a followed parcel is checked again within a day, so the versions since no longer
-- produce it, and the replay expires its row after 8 days unseen (src/server/reviewQueues.ts).
-- Wording a universal provider relays (carrier `unknown`) is the provider's own and stays.
with templates as (
  select id, carrier, provider_code, chosen_stage, count, first_seen, last_seen, last_seen_version, reviewed_at,
         regexp_replace(description_normalized, '[0-9]+', '#', 'g') as template
  from public.tracking_status_observations
),
common as (
  select word
  from (select distinct template from templates) as t cross join regexp_split_to_table(t.template, '[^[:alpha:]]+') as word
  where word <> ''
  group by word
  having count(*) >= 5
)
select t.id, t.carrier, coalesce(t.provider_code, '-') as code,
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
group by t.id, t.carrier, t.provider_code, t.chosen_stage, t.first_seen, t.count
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
\echo '   direct answer in 30 days; captcha: a verification or captcha page stops the adapter; dormant: not checked for 24 h;'
\echo '   none: the carrier answers and the provider history is kept;'
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

\echo '== Reader feedback: answers other than right, given or completed with a note in the window, not reviewed'
\echo '   carriers: no carrier found, or the wrong one; adapters: the carrier''s status, steps, times or places;'
\echo '   delivery-tracker: no reason given. Set reviewed_at and review_note by id (docs/OBSERVABILITY.md)'
select format('%s right, %s wrong, %s found elsewhere', count(*) filter (where answer = 'right'),
         count(*) filter (where answer = 'wrong'), count(*) filter (where answer = 'found_elsewhere')) as counts
from public.parcel_feedback
where created_at >= :since \gset feedback_
\echo '   answers given in the window:' :feedback_counts
-- A reader's words can quote a number or name a person or a shop: a note is only counted, a
-- carrier's name shows its first three words with digits masked, and a tracking page shows its
-- host alone.
select f.id, to_char(f.created_at, 'YYYY-MM-DD HH24:MI') as given, f.carrier, f.answer,
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
-- A note can follow its reason within the hour: updated_at shows it again once it has.
where f.answer <> 'right' and f.reviewed_at is null and f.updated_at >= :since
order by f.created_at desc;
