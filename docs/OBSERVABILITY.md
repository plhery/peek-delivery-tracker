# Observability

`lookup_verification` records the outcome and server verification duration,
without tokens, IP addresses or parcel inputs. Compare rejections and service
failures with completed public lookups and the API's `403`/`429` rates when
tuning protection. Client lookup failures remain in the existing parcel-lookup
analytics event; `public_lookup` logs how each lookup ended on the server.
Configuration belongs in [DEPLOYMENT.md](DEPLOYMENT.md).
`lookup_proof` logs each refused proof with its `reason`: `missing` (none sent,
as when a page asks before its verification has finished), `malformed`,
`bad_signature` (another hostname, a changed `TURNSTILE_SECRET_KEY`, or
tampering), `expired` or `too_many_networks`. It logs `moved` when a proof first
works on another network. Each line has the request `kind`, the address family
the proof was issued on and used from (`issued_family`, `used_family`: `v4`,
`v6` or `none`) and its `age_s`; a move or `too_many_networks` adds the
`networks` the proof works on. It never has an address or the proof.
`native_verification` logs each App Attest check with its `kind` (`attestation`,
or the `lookup` or `detection` an assertion signed), its `outcome` (`verified`,
`rejected`, or `limited` by the device's daily allowance) and, for a rejection, a
`reason`: `malformed`, `unconfigured`, `challenge_expired`, `challenge_mismatch`
(issued for another network, hostname, key or purpose), `replayed` (a used
challenge, or a counter another request moved past), `unknown_key`, a failed step
of Apple's validation (`chain`, `certificate_time`, `nonce`, `key_id`, `app_id`,
`counter`, `aaguid`, `environment` for a development key, `signature`), the signed
launch `category` or `bundle_version`, or `unknown`. Signed-in parcel additions,
detections that contact a carrier and refreshes log `account_lookup`,
`account_detection` or `account_refresh` with the `mode` that answered (`log` or
`enforce`): `verified`, `invalid` with the same reasons (`unavailable` when the
database could not check), or `missing` for a request from the app's user-agent
that was not signed, `unsigned` when the app says it verifies but could not sign
and `legacy` from builds that never sign. Browsers sign nothing and are not
counted. `native_verification_total` counts the same. Neither has a key identifier, challenge, assertion, address or
parcel input. `lookup_proof_total` counts what let each anonymous lookup or
detection through. Verification, anonymous lookup and detection endpoints omit the incoming request
from error reports because their headers can carry verification credentials.

Each carrier refresh leaves a trace in three places:

1. **Logs**: one-line JSON describing the control flow, including the tracking number.
2. **Postgres audit**: private rows with every step and the evidence behind each status
   decision.
3. **Sentry**: groups the failures someone has to fix: crashes, outages and failing
   adapters. An opaque `attempt_id` links each event back to Postgres.

What a check learnt that the catalog, detection or the adapters could act on is no error:
routing signals, sync anomalies, unmapped wording, support cases and health recoveries stay
in Postgres and Prometheus, for the hourly review in [Review signals](#review-signals).

Metrics (Sentry and Prometheus) cover latency and fallback use. Sentry alerts on each new
error; [Grafana alerts](#grafana-alerts) on what no single error shows.

**Privacy.** Logs keep tracking numbers, original errors and upstream request/response
details. Sentry reports keep the errors and details but name a parcel by the shape of its
number (`MY + 10 digits + US`), shorten addresses and leave out the failing request's
body, query and client address (see "What Sentry reports keep" below). Both survive parcel
deletion until retention expires. Restrict access and retention, and sanitize anything
you share publicly.

Parcel link ids and owner keys are the exception, because holding one is enough to read
or forget a parcel. Request logs name those routes without the id
(`/api/public/parcels/:link`, `/api/public/parcels/:link/alerts`), and error reports for
them and for `/api/packages/claim` leave the incoming request out. A reverse proxy in front
of the app still sees the address. The push endpoint and keys of a link's alert are never
logged, and a failed send to one is logged and counted but not reported as an error:
anyone holding a link can add an endpoint.

The delivery email is another: its lines name a parcel by its id and say how the email
ended, never the address, the subject or the parcel's name. A mail server's refusal quotes
the recipient, so only its kind and SMTP status are logged and reported. The token of an
unsubscribe link is the right to switch an account's email: `/api/email/unsubscribe` is
logged without its query, and its error reports leave the request out.

## Postgres audit

All tables and views here are service-role only.

- `tracking_sync_attempts`: one row per check. It holds its `trigger`: `added` for a parcel
  added or a lookup, `refresh`, `opened` for a link read, `changed` for a carrier or input
  changed, `scheduled`, `release` for a scraper release's re-check, and `package` for a check
  someone asked for before checks named their reason. It also holds
  the configured and actual carrier, job and package, previous and selected stage, provider status, event counts, outcome,
  error class, anomaly codes and a bounded private `status_text`. A check that stored a
  scan for the first time, moved the parcel's stage or restaged a stored scan into news
  (see `scans_reannounced`) ends `updated`; one whose carrier answered with the progress
  already saved ends `unchanged`. `events_new` counts the scans
  a check stored for the first time: a stored scan rewritten in place, as when its location
  fills in, is not new. It is null for checks recorded before the column existed.
- `tracking_sync_steps`: `selected`, `fetch`, `normalize`, `persist_events`,
  `persist_package`, `complete`, each with status and duration. `persist_events` has
  `events_persisted`, the rows written, `events_new`, and `copies_absorbed`, the universal
  copies of the carrier's scans it deleted. When the check restaged stored
  scans into a delivery or pickup readiness to announce, it has `scans_reannounced`, the
  scans whose receipts it dropped, and `reannouncement_failed` when that failed (also
  reported to Sentry as `reopen_announcements`). A not-yet-announced number
  is a successful `fetch` with disposition `unannounced`, and the attempt ends as
  `waiting`. Real failures end as `error`. A successful `fetch` names the source that served
  the parcel (`source_carrier`, `unknown` for a universal provider) and has `carrier_answered`
  when the carrier answered too, on local clocks or in part. A `fetch` that reused answers another copy of
  its number got earlier in the same scheduled run counts them in `shared_answers`, as it
  does the failure of a carrier whose own browser already failed in the run.

Completed rows are kept for 90 days. A check cut off at shutdown ends `interrupted`, with
its `tracking_sync_completed` line; one whose worker died is marked `abandoned` when its
job is claimed again. Attempts still running after 30 min are marked `abandoned` and
reported. Deleting a package or account deletes its audit.

A check whose parcel is deleted or whose carrier changes while it runs ends as
`superseded`: nothing is saved and no error is reported, whether its next write or the
worker's lease renewal notices first. A deleted parcel takes the check's job and audit rows
with it, and a carrier change ends the job, so the worker drops the job instead of
finishing it.

`delivery_emails` records what was emailed: one row per parcel and stage claimed for a
delivery email, with the account, the scan it told and its `stage` (`delivered`,
`ready_for_pickup`), `status` (`claimed`, `sent`, `failed`, `skipped`), a `reason` code,
the attempts and the times. It holds no address and none of the email. A row outlives its
parcel and goes with its account. The delivery email also reads the attempts above: a
scan without a clock time is news only when an
earlier attempt, since the parcel joined the account, ended `updated` or `unchanged` on
another stage.

**Anomaly codes:**

| Code | Meaning |
| --- | --- |
| `invalid_event_timestamp` | A provider timestamp couldn't be parsed |
| `future_event_timestamp` | An event is over 1 h in the future, usually a local clock read in the wrong zone |
| `observed_without_timestamp` | A synthetic observation was needed to change stage |
| `terminal_stage_regression` | A delivered/returned parcel moved stage (`exception` excepted) |
| `delivered_status_conflict` | The provider says delivered but the chosen stage doesn't |
| `early_stage_regression` | A scan only announcing or accepting the parcel came after a later stage, which was kept |
| `provider_clock_offset` | One scan reached the parcel on two clocks a zone apart: a provider read it in the wrong zone, or a scraper fix moved the provider's clock and its rows followed. Also a provider's copy stored whole hours after the carrier's scan, or dated past the check that returned it ([ROUTING.md](ROUTING.md)) |
| `progress_disappeared` | The carrier's own adapter or the provider its saved summary came from answers without progress; for a carrier without an adapter, exhausted providers return only thin answers and the routing failure threshold is reached. The saved state is kept and the check ends as an error, with `error_type` `ProgressDisappeared`. |
| `fallback_without_progress` | A different fallback provider answers without progress for a carrier with its own adapter, or a thin answer for a carrier without one stays below the routing failure threshold. The saved state is kept and the check ends as waiting. |
| `worker_abandoned_attempt` | The check was still running after 30 min, usually because its worker died, and was marked `abandoned` by the audit maintenance |

A universal answer with progress resets the consecutive-check count, even when it has
no new scan. Thin answers keep the previous provider preference and event watermark.

Anomalies are review signals: each is kept on its attempt, logged as
`tracking_sync_anomaly` and counted in `tracking_sync_anomaly_total`, and none opens a
Sentry issue. A check that ends as an error still counts towards its carrier's refresh
incident.

## Tracking support backlog

`tracking_support_cases` keeps one private case per normalized tracking number when
recognition is unknown, ambiguous or generic postal, the carrier has no direct adapter,
or its number points elsewhere. The audit captures recognition before fetching, so a
successful fallback still leaves the gap visible. Known-carrier outages stay in health
monitoring. A number is not marked as pointing elsewhere when detection offers its
carrier under another id (Amazon Shipping as Amazon Logistics), or when the parcel's
carrier, or the carrier it handed the parcel to, tracked that number directly. Nor is a
shape several carriers issue ambiguous once the parcel's carrier, one of them, confirmed
the number directly.

Each check computes the shape and carrier reasons afresh and replaces them on its case.
Recognition reasons from the Add sheet, gap evidence, and sightings older than the case's
latest still add to them, so a reason that no longer applies leaves with the next check.

Cases and their bounded `tracking_support_observations` are service-role only. They
survive parcel deletion and audit pruning. A case goes 10 days after no parcel, an
account's or a lookup's, follows its number any more (its parcel or account was deleted, or
the number was checked without being saved) and after its last check; a parcel following the
number again stops the clock (`unfollowed_at`). Repeating an audit write does not count the
same check twice. Cases retain sightings, fallback counts, detection candidates, the last
outcome and direct verification evidence.

Six digits filed under no carrier are a pickup code, the one that opens a parcel locker or
collects a parcel at a pickup point, not a tracking number: neither their checks nor their
detection opens a case, and the apps ask for the parcel's tracking number instead. Under a
carrier picked by hand, six digits are that carrier's number and count like any other.

Unresolved detection requests also retain their normalized number in this backlog,
including cached answers and choices between carriers, before any parcel is saved.
Invalid, refused and cancelled checks do not create observations, and neither does the
start of a longer number the same person saved, which is most likely that number typed
again: the server looks for one among the account's parcels, and the front door and the
iPhone without an account flag one their device follows (`prefixOfSaved`). When the
account check fails, the case opens. The other way round, saving a number closes the open
cases on its start that only detection saw within the hour before: a case has no owner,
so the hour stands in for the same person. A failed backlog write does not change the
detection answer. `carrier_detection_support` logs the number and
the carriers asked, unanswered or offered as choices.

Recognition asks some carriers, FedEx among them, only through a browser, and skips them
once a universal provider already tracks the number. A case an unrecognized number opens
while such a carrier went unasked or could not answer is held for an hour (`held_until`),
so the parcel's first checks can ask it. If a carrier recognition does not ask over plain
HTTP tracks the number directly within that hour, leaving no shape or carrier gap, the case
is marked `ignored` with a note. Otherwise it is reviewed once the hour is over.

Review open cases:

```sql
select id, tracking_number, reasons, detection_candidates, seen_count, fallback_count,
       last_outcome, last_provider, first_seen, last_seen, fix_status, fix_reference
from public.tracking_support_cases
where fix_status = 'open' and (held_until is null or held_until <= now())
order by last_seen desc;
```

Each server version replays the backlog once, a minute after it starts. It reruns
detection and the adapter check for the 500 most recently seen open cases, without asking
any carrier but counting the one that last tracked the number directly, and marks `fixed`
each case it finds no gap for, or that its parcel's latest check left without a reason:
`fix_reference` names the
version (the scraper release the app pins, plus the app commit in an image) and `notes`
says why. A case whose configured carrier changed meanwhile stays open. A case that only
waits for its parcel's carrier to confirm the number, because the shape fits several
carriers and that carrier is one of them, is marked `ignored` once every saved parcel with
that number is delivered or returned: finished parcels are not checked again, so nothing
is left to confirm it. So is a case whose saved parcels are all one-off lookups that left
the schedule before any carrier or provider answered for the number
([`unseenLookup.ts`](../src/server/unseenLookup.ts)), or whose parcels are all gone and
whose last check got no answer (`waiting`): no check is left to update it. So is a case on
a pickup code filed under no carrier, which was never a tracking number. A case only the
Add sheet's recognition saw stays open, since detection could still learn its shape.
`tracking_review_runs` keeps one row per version, so restarts and other servers do not
replay it again; another server takes over a replay left unfinished for ten minutes.

A fix the replay cannot see is marked by hand: set `fix_status = 'fixed'`, `fixed_at`
to its deployment time and `fix_reference` to the commit or release. Either way, a later
successful direct check with accepted, timestamped progress for that same number promotes
it to `verified`. Provider answers, thin results, preserved older summaries and a
different handoff number cannot verify a fix. Use `ignored` with a note for a case that
needs no change; future sightings still accumulate.

## Reader feedback

A parcel's page asks its reader whether it is right, or who carries a parcel no carrier
was found for. `parcel_feedback` keeps one row per answer, service-role only: the
tracking number, the carrier, the answer (`right`, `wrong` with its reasons and note, or
`found_elsewhere` with a carrier name or tracking page), where it was asked, and `shown`,
what the service held about the parcel at that moment (status, last check, newest scans
as its timeline shows them).
A row names no account, link or device. A note may follow its reason within the hour and
replaces the row; a tracking number takes twenty answers a day. Rows are deleted 90 days
after they were given, by the scheduled sync's maintenance pass.

Review what readers reported:

```sql
select id, created_at, tracking_number, carrier, answer, reasons, note, carrier_name,
       tracking_page, asked, via, app, shown
from public.parcel_feedback
where answer <> 'right' and reviewed_at is null
order by created_at desc;
```

The hourly review ([ops/review/signals.sql](../ops/review/signals.sql)) lists the answers
other than right given in its window, or completed there with a note, with the number's
shape and a lane, and counts the window's answers by kind. Once an answer is handled or
routed, set `reviewed_at`, and `review_note` with the outcome. A note can hold personal
details, so a review note says what was off in other words:

```sql
update public.parcel_feedback
set reviewed_at = now(), review_note = 'WHY'
where id = 'ID';
```

## DPD app sessions

DPD Germany and DPD Switzerland read through a session of DPD's app service, which takes
tens of seconds to open. `dpd_app_sessions` keeps them across deploys, service-role only:
the `token`, `opened_at`, `checked_at` (the last time an hourly check found DPD accepting
it) and `refused_at` (when DPD began refusing it, once the next check repeated the
refusal). The server keeps a session until DPD refuses it, then opens the next. It checks
the current session, and each one it no longer uses for a week after it opened.

How long DPD accepted each session, and how long the ones it still accepts have lasted:

```sql
select opened_at, refused_at - opened_at as accepted_for,
       checked_at - opened_at as last_accepted_after
from public.dpd_app_sessions
order by opened_at desc
limit 30;
```

The newest row is the current session: its `last_accepted_after` is how long DPD has
accepted it so far. `refused` openings in `dpd_app_session` tell when DPD stopped
accepting one.
Each process also logs the session it takes up at start and how each opening ended
(`dpd_app_session` in [Logs](#logs)); `dpd_app_session_total` counts them.

## Unmapped wording

`tracking_status_observations` collects carrier wording whose stage didn't come from a
carrier status map, so it can be mapped instead of guessed forever. Scans a universal
provider relays are filed under the carrier `unknown`, which has no carrier map: the
scraper's universal map, which reads them as the providers do, stands in for one. Such a
scan is collected only when that map leaves its wording unread, or reads it as another
stage than the scan's. There is one row per carrier, provider code and normalized
description. Wording that names a shop or a postcode is kept as one template instead
(`PLACE_WORDINGS` in `src/server/trackingSync.ts`, such as `received at <store> local express
<postcode>`): every place shares its row, which the review closes once, and no place is stored.
The maps still read the wording itself. `count` and `last_seen` grow on repeat sightings. It holds no tracking number
or account reference. Each refresh records at most 32 observations, and a failed write
never fails the refresh. `last_seen_version` names the server version of the latest
sighting.

Each refresh also sends the keys of the wording its maps did resolve, the universal map
included, with its version. An open row with one of those keys closes as `mapped`, with
`reviewed_version` and `review_note`. If a server that names its version sees that wording
unmapped again, the row reopens: the map covers it only sometimes.

The scraper's carrier maps also declare the wording they leave unmapped on purpose, each
with a note, and `statusMapAnswer` from `universal-parcel-scraper/app` says, for one
carrier, code and wording, whether the map stages it, leaves it unmapped on purpose, or
does not know it. A refresh closes the rows it records that a map leaves unmapped on
purpose as `ignored`, with the map's note and its version.

Each server version also asks the scraper it runs about the 1,000 most recently seen open
rows, once, a minute after it starts, with the support case replay above. A row the map
stages closes as `mapped`, its note naming the stage, and the stage it was last seen with
when that differs: the stored scans of finished parcels may then need a repair. A row the
map leaves unmapped on purpose closes as `ignored` with the map's note. Both record the
version. A row that version itself saw unmapped stays open, since its map covers it only
sometimes. Wording no map knows stays open until reviewed by hand, or until it expires.

Every check of a parcel records its wording again, and a followed parcel is checked at least
weekly. A carrier's wording that no check has shown for 8 days is on no followed parcel,
typically because a scraper release composes that carrier's wording otherwise. The replay
closes its row as `expired`, and a later sighting reopens it. Wording a universal provider
relays (carrier `unknown`) is the provider's own and stays open.

Wording a carrier map leaves unmapped on purpose without the scraper declaring it belongs
in `tracking_status_intentionally_unmapped`. Adding or editing a row there closes the open
observations it covers as `ignored`, with its note, and later sightings close as they are
recorded. A row without a provider code covers only wording that came without one; a row
without a description covers every wording of its code:

```sql
insert into public.tracking_status_intentionally_unmapped (carrier, provider_code, note)
values ('CARRIER_ID', 'CODE', 'Why the carrier map leaves it unmapped.');
```

Classifier corrections also need guarded migrations for stored events. Completed parcels
no longer refresh, so parser fixes alone leave their histories unchanged. Repairs preserve
raw evidence, event identities, timestamps and notification receipts, and recompute the
current stage only when the newest milestone changes.

Each event records its stage source in `tracking_events.raw_data.stage_source`:
`carrier_map` (explicit carrier vocabulary or provider-code mapping), `wording:<rule>`
(classifier rule that matched), or `none` (fallback). The sync preserves the scraper's
source, including on unresolved `pending` scans and observed milestones. Local-clock
wording is recorded without a sample timeline event when its instant is unresolved. It
never creates a timestamped scan just to populate the review queue.

Review, most frequent first:

```sql
select carrier, count(*) as wordings, sum(count) as sightings, max(last_seen) as last_seen
from public.tracking_status_observations
where reviewed_at is null
group by carrier
order by sightings desc, wordings desc;

select provider_code, description_normalized, chosen_stage, stage_source,
       count, first_seen, last_seen, last_seen_version, sample_event_id
from public.tracking_status_observations
where reviewed_at is null and carrier = 'CARRIER_ID'
order by count desc, last_seen desc;
```

For each row, in the scraper: map the code or wording in the carrier's `status.ts`, or
declare it in that carrier's `statusMap` when it is left unmapped on purpose, add a fixture
and run the carrier's tests. Once the app runs that release, its replay closes the row. A
row no carrier map answers for, such as one a generic classifier rule now covers, is
marked by hand:

```sql
update public.tracking_status_observations
set reviewed_at = now(), resolution = 'mapped',  -- or 'wording_rule', 'ignored'
    review_note = 'WHY'
where observation_key = 'OBSERVATION_KEY';
```

A reviewed wording that keeps appearing keeps counting up, which shows whether the mapping
took effect.

## Review signals

The routing signals marked in [ROUTING.md](ROUTING.md) are saved in
`tracking_routing_signals` once their check completes: one row per signal, parcel carrier,
subject and number, whose `count` grows with each check that reports it again. A row
outlives its parcel and goes after 90 days unseen, or 10 days after no parcel follows its
number, as support cases do. `tracking_routing_signal_total` counts
them, and the "Review signals" row of the carrier dashboard shows them beside sync
anomalies and health incidents.

[ops/review/signals.sql](../ops/review/signals.sql) is the hourly review of every queue. For
a window of hours (1 by default, 24 for the daily review) it lists routing signals, sync
anomalies, new unmapped wording, new open support cases, health incidents notified in the
window or open and failing in it (`rescued` when every check that failed still answered
through another step; an open one nothing checked, or whose checks answered, waits unlisted
until it fails, recovers or closes), the unfinished parcels a provider tracks, grouped by type, carrier and
provider, with `why` they stay with it, and readers' answers that something is off. It
only reads, and shows a number as its shape (`MY + 10 digits + US`, from
`private.tracking_number_pattern`). Free text can name a place, a shop or a person, so
wording shows `…` for each word fewer than five stored wordings share and `#` for digits,
with the row's id to look it up by, and a carrier's name a reader gave shows its first
three words:

```sh
psql "$DATABASE_URL" -v hours=1 -f ops/review/signals.sql
```

Each row names the lane that acts on it:

- **carriers**: an unknown or ambiguous number format, a detection rule that missed, a
  carrier missing from the catalog, a reader's answer about a parcel with no carrier or the
  wrong one;
- **adapters**: a carrier without an adapter, a failing adapter or provider, an adapter that
  refuses a number its carrier issues or answers it only in part, without movement, on
  local clocks only, without dates or behind a provider's delivered or returned stage, wording no map reads, a reader's answer that a carrier's status, steps,
  times or places are off;
- **captcha**: an adapter stopped by a verification or captcha page;
- **delivery-tracker**: the app's routing, such as parcels left with a provider although
  their carrier's adapter answers other parcels and was not asked for them or had its answer
  set aside as older than the parcel's newest scan, a check whose worker died, or a reader's
  answer that gives no reason.

A parcel a provider tracks can also be **dormant**, not checked for 24 h because nobody
follows it any more, or need **none**: its carrier answers and the provider's fuller history
is kept.

Whatever its lane, a signal that comes from a wrong routing decision is a delivery-tracker
fix. Mark a signal reviewed so that the next run leaves it out:

```sql
update public.tracking_routing_signals
set reviewed_at = now(), review_note = 'WHY'
where signal = 'SIGNAL' and carrier = 'CARRIER_ID' and subject = 'SUBJECT' and reviewed_at is null;
```

## Carrier goals

The carrier dashboard starts with three goals: few errors a reader sees, good tracking, and
parcels answered by their carrier's own tracking rather than a universal provider
(ParcelsApp, 17TRACK, Ship24, Postal Ninja, UPU). `metrics.goals_daily` counts them per UTC
day; `record_usage_metrics` rewrites today's row at most every ten minutes and yesterday's
once more as of its end.

- **Answered directly**: of the unfinished parcels with a scan (a check answered with a stage
  other than pending), those whose latest answered check came from a carrier's adapter, theirs
  or the one it was handed to; and the same share of the day's answered checks.
- **Failed checks**: checks that ended `error` or `abandoned`.
- **Stale parcels**: followed parcels without an answered check for 24 h. A parcel is followed
  when it belongs to an account or had a check in the last 24 h other than a scraper
  release's, which nobody asked for; the others are not checked.
  A parcel without a scan waits for its carrier and is never stale.
- **Readers who say it's right**: right answers among the last 7 days' answers, with their count.
- **Quality**: scans no map read or a wording rule read, new unmapped wording, checks with an
  anomaly, and parcels showing a provider's copy of a carrier's scan (counted live only).

The gap is the unfinished parcels whose latest answer came from a provider, and the followed
ones that went 24 h without an answer or show an error. `metrics.carrier_gaps` ranks it by the
carrier that could take each parcel, with the reason and the lane of the hourly review:
`private.tracking_gaps` classifies the parcels for both. Annotations mark each scraper
release, and each release of Peek alone (off by default), from `metrics.releases`.

The week's goals and the ten carriers that hold most of the gap:

```sql
select day, round(100.0 * (parcels_own_adapter + parcels_other_adapter) / nullif(parcels, 0), 1) as direct,
       round(100.0 * direct_checks / nullif(answered_checks, 0), 1) as direct_checks,
       round(100.0 * failed_checks / nullif(checks, 0), 2) as failed,
       round(100.0 * stale_parcels / nullif(followed_parcels, 0), 1) as stale,
       round(100.0 * feedback_right_7d / nullif(feedback_7d, 0), 1) as right, feedback_7d as answers
from metrics.goals_daily where day > current_date - 7 order by day;

select rank, carrier, reason, lane, parcels, cumulative_percent
from metrics.carrier_gaps where rank <= 10 order by rank, parcels desc;
```

The hourly review lists the gap's parcels with their number shapes.

## First-response queries

```sql
-- Carrier health, last 24 h: `updated` checks stored news, `unchanged` ones found none
select * from public.tracking_sync_health_24h
order by error_percent desc nulls last, attempts desc;

-- One Sentry event
select * from public.tracking_sync_attempts where id = 'SENTRY_ATTEMPT_ID';
select sequence, step, status, duration_ms, details, error_type, occurred_at
from public.tracking_sync_steps where attempt_id = 'SENTRY_ATTEMPT_ID' order by sequence;

-- Recent suspicious decisions
select * from public.tracking_sync_recent_anomalies order by started_at desc limit 100;

-- Repeated failures by carrier, last 7 days
select configured_carrier, error_type, count(*) as failures,
       min(started_at) as first_seen, max(started_at) as last_seen
from public.tracking_sync_attempts
where outcome in ('error', 'abandoned') and started_at >= now() - interval '7 days'
group by configured_carrier, error_type
order by failures desc, last_seen desc;

-- Delivery emails of the last day, by how they ended
select status, reason, count(*) as emails, max(claimed_at) as last_claimed
from public.delivery_emails
where claimed_at >= now() - interval '24 hours'
group by status, reason order by emails desc;

-- Stuck attempts (maintenance clears these after 30 min)
select id, job_id, package_id, configured_carrier, current_step, started_at, now() - started_at as age
from public.tracking_sync_attempts where outcome = 'running' order by started_at;
```

## Logs

Every deploy replaces the app container, and Docker deletes a container's logs with it.
Production therefore keeps them in Loki for 30 days: the Coolify service
`delivery-tracker-logs` runs Loki beside Grafana and Alloy, which follows the app's
containers. Alloy finds them by the `coolify.name` label of the Coolify application that
runs the app, so moving the app to another application means changing that label in the
service's `alloy/config.alloy`; until then Loki receives nothing. Query the logs in
Grafana through the "Loki (delivery tracker logs)" data source.
Lines carry `app`, `container`, `event` and `level` labels, for example
`{app="delivery-tracker", event="tracking_scrape"} | json | outcome = "error"`. The TRAWL
browser service's lines come as the app `delivery-tracker-trawl`, with the same labels, in
logfmt: `{app="delivery-tracker-trawl", event="tier.failure"} | logfmt`.

Key JSON events:

- `tracking_sync_started`, `tracking_sync_step`, `tracking_sync_completed` (by `attempt_id`).
  The completed line has the `outcome` and `events_new`; a check cut off at shutdown
  ends `interrupted`, with `error_type` `WorkerShutdown`;
- `tracking_scrape` and `tracking_routing`, which include `transport_fallback`, carry the
  `attempt_id` of the check whose lookup they belong to. Lookups outside a check, such as
  carrier recognition, have none. A `tracking_scrape` `outcome` is `success`, `error`, or
  an answer about the number: `not_found`, `no_history` (a provider has no scans for it
  yet, or a carrier's tracking does not know the item or lists it without a scan: an
  inconclusive answer with a reason in `UNKNOWN_ITEM_REASONS`, `src/server/trackingFailure.ts`),
  `input_required` or `invalid_input`.
  A `tracking_routing` line is a warning for `transport_fallback` and `provider_failed`,
  except a provider's `no_history` answer, which is info;
- `tracking_sync_audit_write_failed`, `tracking_sync_audit_maintenance_failed`,
  `tracking_status_observation_write_failed`;
- `tracking_sync_anomaly` (warning, by `attempt_id`): one per anomaly code of a check,
  with the previous, reported and selected stage, the provider status and the event counts;
- `tracking_health_incident`: a health incident opened, still fails six hours on (warning,
  info for a direct step whose every failure a fallback step covered), recovered, or closed once no sample of it was left or the carrier answers without its
  direct step (info), with its `kind`, `subject`,
  `title`, `failures` and `attempts`;
- `dpd_app_session`: the scraper took up a saved session at start (`outcome` `taken_up`,
  with `age_ms`, how long ago it opened), or an opening ended, `opened` or `failed`
  (warning, with `error_kind`). `trigger` says what began it: `start`, `retry` a quarter of
  an hour after a failure, `refused` after a lookup or the hourly check found DPD refusing
  the current session, or `lookup` when a lookup found none open. `duration_ms` is how long
  the opening took, or the database read for a session taken up;
- `dpd_app_session_refused`: DPD refused a session the server no longer used, with
  `accepted_ms`, how long after it opened DPD began refusing it, and `last_accepted_ms`,
  when a check last found it accepted. `dpd_app_session_store_failed` (warning, with the
  `operation`, `load` or `save`, and `error_type`) when `dpd_app_sessions` could not be
  read or written; the scraper then carries on without it;
- `review_queues_replayed`: the review queue replay of a `version`, with
  `cases_replayed`, `cases_fixed` and `cases_ignored` for the support backlog, and
  `observations_replayed`, `observations_mapped`, `observations_ignored` and
  `observations_expired` for the unmapped wording. `review_queue_replay_failed` (error, with
  `error_type`) when it could not run; it is retried every five minutes;
- `sync_claim_failed`: the sync worker could not claim a job, with its `failure_count` and
  `failing_for_ms`, how long claims have been failing. A database restart leaves a few of
  them. Alert when they repeat for two minutes (`failing_for_ms` of 120000 or more).
  `sync_claim_recovered` follows with the first claim that works again and says how long
  the streak lasted;
- `sync_job_failed`, `sync_job_finish_failed` (by `job_id`). Alert on these. A scheduled
  run whose worker died three times is logged as failed by the next run, with
  `error_type` `WorkerLeaseExpired`;
- `sync_job_handoff` (by `job_id`): at shutdown, whether the job went back to the queue;
- `sync_job_dropped` (by `job_id`): a package job had nothing left to finish, with the
  `reason`: `parcel_deleted` before its check, or `job_withdrawn` when a deletion or a
  carrier change took the job during it. `tracking_sync_audit_skipped` (by `attempt_id`):
  an audit write found its rows deleted with the parcel. Neither is a failure;
- `http_request` (by `request_id`, matching Sentry for server errors). A failed request
  carries `error_class`; a 502 that wraps an upstream failure also names that failure's
  class in `error_cause`. Health checks (`/health`, `/health/live`) and metric scrapes
  (`/api/metrics`) are logged only when they fail. A 5xx is logged at `error`, except
  `/health`'s 503 while the server shuts down, which is `info`;
- `public_lookup` (by `request_id`): how a lookup without an account ended, as
  `public_lookup_total` counts it, or `failed` with the `reason`, the stage it stopped at:
  `input`, `verification` (the browser proof), `allowance`, `amazon` or `saving`. A
  refusal adds its `status`; `error_class` names any failure. It never has the number;
- `carrier_recognition`: how many carriers the Add sheet's recognition asked, whether it
  asked a `second_batch` because none of the first five knew the number, how many knew the
  number or failed, and what it settled on (a carrier, `choice` or `none`).
- `typo_candidate`: the first check of a parcel just added or looked up failed or found
  nothing, and leaving out one digit of two identical neighbours gives a number that a
  high-confidence detection rule's check digit accepts while the number as typed passes
  none. It has that rule's `checksum` (`s10` before any other), the `carrier` it points to
  and the `origin`, `account` or `lookup`; never either number. Nothing is suggested to the
  reader: it counts how often a "did you mean" would apply;
- `tracking_redetections_queued`: how many parcels added in the last 30 days were queued for
  a check because a scraper release's catalog now points them to a carrier they never asked
  (`queued`, at most 50 a batch) and how many wait for a later batch or their cooldown to end
  (`waiting`) ([ROUTING.md](ROUTING.md));
- `parcel_links_forgotten`: how many expired lookups, and parcels with them, a maintenance
  pass forgot, how many stopped links from accounts it purged and how many alerts of
  finished journeys it ended. `parcel_link_maintenance_failed` when it could not run.
- `parcel_link_alerts_failed`: how many alerts of parcel links a dispatch could not send.
- `push_delivery_failed`: a notification the push service did not take, by `channel` (`web`,
  `native` or `live_activity`), with its HTTP `status`, the APNs `reason` and the `error_type`;
  never the endpoint or token. The scan stays pending and the next dispatch sends it again.
- `parcel_feedback`: an answer about a parcel, with the `answer`, its `reasons`, the
  `carrier`, where it was `asked` (`page` or `back`, on the way back from the carrier's
  site), `via` an `account` or a `link`, the `app` and the `outcome` as
  `parcel_feedback_total` counts it. It never has the number or the reader's words.
  `parcel_feedback_forgotten` says how many old `answers` a maintenance pass deleted, and
  `parcel_feedback_maintenance_failed` that it could not run.
- `tracking_review_forgotten`: how many support `cases` and routing `signals` of numbers no
  parcel follows a maintenance pass deleted. `tracking_review_maintenance_failed` when it
  could not run.
- `public_allowance`: an overall daily allowance without an account (`kind`: `lookup` or
  `detection`) is `running_out` at 80% or `used_up`, with `used` and `limit`.
  `account_allowance` says the same of the hourly allowance every account shares, at 50%.
- `delivery_email` (by `package_id`): how one delivery email ended, with its `stage`, `outcome` and
  `reason`; a refusal by the mail server adds `error_code` and `smtp_status`.
  `delivery_emails_capped` when emails were skipped for a daily allowance, and
  `delivery_email_finish_failed` when an ending could not be recorded: that email stays
  claimed and is not sent again.
  `delivery_email_card_failed` (warning, with `error_type`) when the map card could not be
  drawn: the email goes out without its picture.

The logger allows `tracking_number` explicitly. It drops other fields whose names look
like parcel, user, label, location, status text, URL, token, cookie or secret data. Keep
new fields scalar and bounded.

## Sentry

The SDK is enabled only when `SENTRY_DSN` is set. Default integrations are on and tracing is
off by default. Outgoing requests carry no `sentry-trace` or `baggage` header
(`tracePropagationTargets: []`): carriers' bot protection refuses a request with `baggage`. Reports keep original errors, their causes and custom fields; `beforeSend`
and `beforeBreadcrumb` only shorten addresses and take tracking numbers out (below).
Sentry's own limits and project-side scrubbing still apply. Automatic lookups wrap each
provider failure in an `AggregateError` so every cause is visible.

- **Titles**: messages are sent without a stack trace (`attachStacktrace: false`), so an
  issue is titled by its message rather than by a minified function name.
- **Grouping**: by component, operation, carrier and error type. A database that does
  not answer (`database_code:unreachable`) fails every write at once and is one issue;
  its events' tags name each write. `attempt_id`,
  `job_id`, `request_id`, `number_pattern`, `upstream_status` and `database_code` are
  searchable tags.
- **Source maps**: the server's stay in its image. The browser's are public, beside the
  scripts; see "Browser error reports" below.
- **Crons**: a scheduled run sends an in-progress check-in to the daytime or overnight
  monitor, then `ok` or `error` with its duration in seconds. A run handed over at a deploy
  keeps its check-in. The first check-in also sends the monitor's settings (schedule, 5 or
  15 minutes of margin, 10 minutes of maximum runtime), which overwrite edits made in Sentry:
  change them in `beginScheduledSyncCheckIn`. Runs never overlap, so a stuck run holds the
  queue: Sentry reports it as timed out after 10 minutes, and as missed once the next run is
  late. A run whose worker died three times is failed by the next claim; the next run closes
  its check-in as `error` before opening its own, so the monitor alerts. Each reclaim
  before that was reported.
- **Background work**: each run of the sync worker, the scheduler, the friendship
  notifications and the review queue replay has Sentry scopes and a trace of its own
  (`inBackgroundScope`). A request that wakes a worker leaves its transaction, headers, tags,
  user and trace out of the worker's reports. Their transaction and `background_job` tag name
  the work: `sync-worker`, `sync-scheduler`, `friendship-notifications` or `review-queues`.
- **Alerts**: notify on new issues and regressions in `production`, and keep the Cron
  alerts. Avoid "more than 0 times in 5 minutes" rules: they fire on every failed check.
- **Incidents**: carrier and provider outages open once, from thresholds computed in
  Postgres, and remind every six hours while they last. A recovery, or a direct step whose
  every failure a fallback step covered, is only logged and counted. See [ops/sentry](../ops/sentry/README.md).
- **Review signals**: routing signals, sync anomalies, health recoveries and covered direct
  failures open no issue;
  see [Review signals](#review-signals).
- **Push deliveries**: a send the push service refuses, other than for a subscription that is
  gone, is retried by the next dispatch, so a dispatch with failed deliveries is only logged.
  While every dispatch keeps failing some, it is reported 1, 2, 3, 4, 8, 16… dispatches after
  the first. A channel that cannot dispatch at all is reported at once.
- **Input requirements**: requests for a postcode or other tracking input stay in logs
  and breadcrumbs without opening Sentry issues. Each lookup made with a postcode the
  owner supplied for a provider logs `provider_input_lookup` with its result.
- **Database outages**: when the database does not answer (`database_code:unreachable`), or
  its gateway answers 502, 503 or 504, the sync worker's claims, the scheduler and the
  friendship notifications keep it in the logs. The sync worker reports it once its claims
  have failed for two minutes, and again each time the outage has doubled. A claim the
  database refuses is also reported at once. The scheduler and the friendship
  notifications report refusals only. Work that is running when the database goes away
  still reports its own failed writes.
- **Allowances without an account**: `component:public-allowance` warns once a day when
  an overall allowance is 80% used, and reports an error when it is used up: every visitor
  without an account is then refused until midnight UTC. One issue per allowance and
  state.
- **Allowances for accounts**: `component:account-allowance` warns once an hour when the
  allowance every account shares is half used, and reports an error when it is used up:
  every account is then refused until the next hour (UTC). Many new accounts at their
  daily limit in `private.account_tracking_usage` point to accounts made for scraping.
- **Routing decisions**: see [ROUTING.md](ROUTING.md).

### Upstream HTTP diagnostics

Rejected responses from `fetchBounded` carry `UpstreamHttpError.diagnostics`, attached to
Sentry's `upstream_http` context before fallback. It contains:

- content type, server header, request/correlation IDs and `Retry-After`;
- recognized body signatures and error codes, plus a text excerpt of the body;
- all response headers, and the request URL, method, headers, body and timeout.

Like the rest of a report, their addresses are shortened as described below.

Body inspection stops after 8 KiB or 200 ms. `body_read` says whether the body was
complete, empty, truncated, timed out, unreadable or skipped as binary. `body_signals` are
hints, not proof: a bare 403 or `access_denied` doesn't identify an anti-bot vendor.
Request IDs and excerpts don't affect grouping.

### What Sentry reports keep

A report names its parcel by the shape of its number, in the `number_pattern` tag and the
operation context. The number itself is replaced by that shape wherever it appears in the
report, in any case, and so is the number of any log line among its breadcrumbs. Find a
parcel's reports through its `attempt_id` or `job_id`.

- **Addresses** anywhere in a report or breadcrumb, the request's and the transaction's
  included, lose their query and fragment, which can hold keys. A path segment of six
  characters or more with a digit, other than a uuid, reads `:id`, so a transaction is
  named after its route.
- **Breadcrumbs** drop the console's copy of a log line's arguments; the line itself stays.
- **Incoming requests** keep their headers, less those Sentry filters, but no body, query
  or client address (`dataCollection.userInfo` is off).

From a report back to its parcel, follow `attempt_id` or `job_id` to Postgres.

### Browser error reports

Pages report their own errors to the same project, with the server's release and
environment, which the server writes into each page's `error-reports` meta tag. Only a
production server with `SENTRY_DSN` writes it: development, CI's servers and forks
without a DSN report nothing.

- **Loading**: `instrumentation-client.ts` listens for uncaught errors and unhandled
  rejections before the app renders, and the error screen hands over what it catches.
  Sentry's SDK is a chunk of its own that loads only once a page has an error to report;
  the first ten wait for it. Most visits never download it.
- **Quiet**: an error is reported only when its stack runs through the site's `/_next/`
  scripts and through no extension's code. Failed and aborted requests, cancelled
  navigations, scripts that did not load and `ResizeObserver` loops are dropped
  (`IGNORED_ERRORS` in `src/lib/errorReporter.ts`), so is a repeat of the last report,
  and a page sends five reports at most. An error rendered on the server reaches the page with only
  a `digest`: the server reported it. No tracing, replay, breadcrumbs or user.
- **Translated pages**: a browser translating the page, or an extension, swaps the text
  React wrote for nodes of its own. From before the app renders, React's later changes
  around those nodes land as near as the page allows (`src/lib/pageTranslation.ts`)
  instead of throwing a `NotFoundError` that would bring up the error screen.
- **Through the site**: the SDK posts to `/api/errors` (Sentry's `tunnel`), so the CSP
  names no other host and blockers leave reports alone. The route forwards a report only
  to the project of `SENTRY_DSN`, without the browser's address, cookies or headers. It
  takes 64 KiB at most, 10 reports per client network and 60 from all browsers every 10
  minutes, and passes Sentry's rate limits back. `browser_errors_refused` (with Sentry's
  `upstream_status`) and `browser_errors_forward_failed` log what Sentry did not take.
- **What they keep**: the error, its causes and stack, the page's address and the
  browser's user agent. Addresses lose their query and fragment; parcel link ids, and
  path segments of six characters or more with a digit, read `:id`. Messages lose email
  addresses and anything shaped like a tracking number or a key. No IP address: the SDK
  tells Sentry not to infer one.
- **Source maps** are served beside the scripts (`productionBrowserSourceMaps`): the
  source is public. Sentry fetches them by the frames' addresses; the service worker
  leaves them out. A tab left open across a deploy reports scripts the new image no
  longer serves, so its frames stay minified.

To check after a deploy: a page's source has the `error-reports` meta tag, and an empty
POST to `/api/errors` answers 400 (404 where reports are off). Then, in a production
page's console:

```js
const at = `${location.origin}/_next/static/chunks/check.js`;
const error = Object.assign(new Error('Browser report check'), { stack: `Error: Browser report check\n    at ${at}:1:1` });
dispatchEvent(new ErrorEvent('error', { error, filename: at }));
```

The network panel shows `/api/errors` answering 204, and the issue appears in Sentry
with `sdk.name:sentry.javascript.browser`. An error thrown from the console itself is
not reported: its stack is not the site's. A real issue's frames should show the
original source; a source map processing error on the event means Sentry could not
fetch the scripts or their maps.

## Metrics

The host [step recorder](../src/server/stepRecorder.ts) turns each adapter step into
metrics. Steps are declared per carrier in the scraper's `carrier.json` (`direct`, `retry`,
`trawl`, `browser`…). `phase:total` is one runner invocation, not the whole refresh. Filter
by one phase when counting attempts; summing phases double-counts.

**Sentry metrics**, which work with tracing off:
- `tracking.scrape.duration` (ms) and `tracking.scrape.attempts`, tagged `carrier`,
  `phase`, `outcome`, `error_type`. `outcome` reads as on the `tracking_scrape` line, so
  `outcome:error` counts failures and leaves out answers about the number;
- `tracking.scrape.fallbacks`, with `from_phase` and `to_phase`;
- `tracking.provider_input`, tagged `provider` and `step`, as `provider_input_total` below;
- `tracking.checksum_rejection`, tagged `carrier` and `rule`, as
  `carrier_checksum_rejection_total` below, each in a trace of its own;
- `tracking.typo_candidate`, tagged `checksum`, `carrier` and `origin`, as
  `typo_candidate_total` below, each in a trace of its own.

Import [the Scraper Health dashboard](../ops/sentry/scraper-health-dashboard.json) and set
your project ID.

A step failure is recorded immediately, but the `transport_fallback` warning is sent when
the recovery step completes. An interrupted recovery may never send it.

**Prometheus** metrics are served at `GET /api/metrics` when `METRICS_TOKEN` (16+ chars) is
set, sent as a bearer token. Labels never contain tracking data, link ids or client
addresses.
Every deploy restarts the counters, so a new series is served at 0 on its first scrape and
counts from the next one. `increase()` then still sees an event that happens once per
container.
A step or lookup `outcome`, and a fallback's `reason`, is `ok` or the scraper's error kind
(`not_found`, `rate_limited`, `transport`…). A provider with no history for the number yet,
or a carrier's tracking that does not know the item, is `no_history` rather than
`indeterminate`, as routing and health monitoring count it.

| Series | Answers |
| --- | --- |
| `carrier_step_duration_seconds` (carrier, step, outcome) | How long each step takes |
| `carrier_step_total` (+ error_type) | Which step fails, and how |
| `carrier_lookup_total` (carrier, final_step, outcome, attempts) | Which step served the result, after how many attempts |
| `carrier_fallback_total` (from_step, to_step, reason) | How often recovery is needed |
| `carrier_status_mapping_total` (carrier, stage_source) | Share of events mapped explicitly, by wording, or not at all |
| `carrier_detection_total` (result) | Detection confidence served to clients |
| `carrier_refresh_total` (carrier, served_by, outcome) | Who served each refresh: `adapter`, `other_adapter`, `provider` or `none`, and how it ended: `updated` (a new scan or stage), `unchanged`, `waiting`, `error` or `superseded` |
| `tracking_routing_signal_total` (signal, carrier, subject) | Routing signals for the review: `subject` is the carrier the signal is about, `uncatalogued` for a name a provider gave |
| `tracking_sync_anomaly_total` (carrier, anomaly) | Suspicious decisions of checks, by anomaly code |
| `tracking_health_incident_total` (kind, subject, state) | Health incidents that opened or still fail six hours on (`open`), `recovered`, or `closed` with no sample left or a direct step the carrier has not run for six hours |
| `carrier_checksum_rejection_total` (carrier, rule) | Parcels a carrier's own lookup first confirmed although detection had left that carrier out on a failed check digit. `rule` is the failing rule's id in the scraper's `carrier.json` |
| `typo_candidate_total` (checksum, carrier, origin) | First checks that failed or found nothing for a number that reads as a digit typed twice, as the `typo_candidate` line says |
| `public_lookup_total` (outcome) | Lookups without an account: `created`, `reused` (the number was already stored), `limited_burst`, `limited_daily` (the client's day is used up), `limited_network` (its IPv6 /48's day), `limited_global` |
| `public_detection_total` (outcome) | Detections without an account that needed a carrier's answer: `asked`, or refused first as `limited_burst`, `limited_daily` or `limited_global` |
| `native_verification_total` (kind, outcome, reason) | App Attest checks, as the `native_verification` log line names them; `reason` is `none` when there is nothing to explain |
| `lookup_proof_total` (kind, proof) | What let a `lookup` or `detection` without an account through: `app_attest`, `turnstile` (a browser proof), `ua_bypass` (the app's user-agent while `TURNSTILE_ALLOW_NATIVE_USER_AGENT` is on) or `none` (no verification configured), or that it was `refused` |
| `public_parcel_read_total` (outcome) | Reads of a parcel link: `ok`, `not_found` or `stopped` (its sharing was stopped) |
| `parcel_claim_total` (outcome) | Links kept after sign-in: `kept`, `already`, `quota`, `unavailable` |
| `parcel_forgotten_total` (kind, reason) | Forgotten links and parcels (`kind`): `asked` by their owner, `expired`, or `stopped` 30 days ago |
| `parcel_share_total` (kind, change) | Sharing `started`, `changed` (what the link shows) or `stopped`, through a `lookup`'s link or an `account`'s |
| `parcel_alert_set_total` (outcome) | Requests to turn on an alert for a link: `added`, `updated`, `full` (ten already), `finished` (journey over), `stopped`, `unavailable` |
| `parcel_alert_sent_total` (outcome) | Batches of new scans per alert: `sent`, `skipped` (not in its preset, backfilled, known when the parcel was added, more than a day old, or the owner's own browser), `failed`, `expired` (the push service says the subscription is gone) |
| `parcel_alert_removed_total` (reason) | Alerts ended: `asked`, `delivered` (journey over), `expired`, `failed` (three failed sends in a row) |
| `parcel_feedback_total` (answer, via, app, outcome) | What readers say of a parcel: `right`, `wrong` or `found_elsewhere`, through an `account` or a `link`. `stored`, `replaced` (a note followed its reason), `full` (twenty answers for the number that day) or `closed` (too late to add to) |
| `provider_input_total` (provider, step) | Whether relaying a provider's postcode request pays off: a parcel starts asking its owner (`asked`), the owner gives one (`supplied`), then each lookup made with it: `history`, `still_required`, `no_history` or `failed`. Every provider receives the postcode; supported sources can submit it |
| `dpd_app_session_total` (outcome, trigger) | DPD app sessions `taken_up` at start, and openings that ended `opened` or `failed`, by what began them: `start`, `retry`, `refused` or `lookup`. `refused` counts the sessions DPD stopped accepting, and openings by `lookup` mean lookups waited for one |
| `delivery_email_total` (outcome, reason) | Delivery emails: `sent`; `failed` and tried again later (`smtp`, `content`, `account`, `parcel`, `interrupted`); `skipped` for good (`no_address`, `relay_address`, `parcel_gone`, `account_cap`, `service_cap`) |
| `public_lookup_clients` | Clients that made a lookup yesterday (UTC) |
| `public_lookups_per_client` (stat) | Yesterday's lookups per client: `p50`, `p90`, `max` |
| `public_detection_clients` | Clients that had carriers asked about a number yesterday (UTC) |
| `public_detections_per_client` (stat) | Yesterday's such numbers per client: `p50`, `p90`, `max` |

Useful questions:

- **Is the browser fallback earning its cost?**
  `carrier_lookup_total{final_step="trawl"}` over all successful lookups.
- **Is a carrier's adapter actually serving it?**
  `carrier_refresh_total{served_by="provider",outcome=~"updated|unchanged"}` over all
  `updated` and `unchanged`. It should stay near zero for carriers with their own adapter.
  One direct failure benches the adapter for its cooldown, so small failure rates show up
  amplified.
- **Does an in-adapter retry ever help?**
  `carrier_lookup_total{final_step="retry",outcome="ok",attempts=~"[2-9]"}`.
- **New unmapped wording?** A rising `stage_source="none"` share. See
  [Unmapped wording](#unmapped-wording).
- **Is the daily lookup allowance right?** `public_lookups_per_client{stat="p90"}` is
  what nine clients in ten stayed at or under yesterday. Well below
  `PUBLIC_LOOKUPS_PER_DAY`, the limit only stops outliers. At the limit, ordinary use is
  being refused: refused lookups are not counted, so the value cannot go higher, and
  `public_lookup_total{outcome="limited_daily"}` rises with it. The gauges are set by the
  maintenance pass after each scheduled sync. `public_detections_per_client` and
  `PUBLIC_DETECTIONS_PER_DAY` read the same way.

- **Can the user-agent exemption go?** `lookup_proof_total{proof="ua_bypass"}` counts
  lookups from app builds that never verify, and from callers that copy their
  user-agent. Once it is small next to `app_attest` and `turnstile`, turn
  `TURNSTILE_ALLOW_NATIVE_USER_AGENT` off ([DEPLOYMENT.md](DEPLOYMENT.md)).
  `native_verification_total{outcome="rejected"}` by `reason` says why installations
  that do verify fall back to Turnstile.

- **Can signed-in requests require App Attest?** `native_verification_total` for the
  `account_*` kinds. Builds that never sign count `outcome="missing",reason="legacy"`
  and would be signed out by `NATIVE_ATTEST_ACCOUNT_MODE=enforce`; once that is about
  zero, and `unsigned` and `invalid` are rare next to `verified`, enforce
  ([DEPLOYMENT.md](DEPLOYMENT.md)). `invalid` by `reason` says what to fix first.

- **Is a check digit wrong for real numbers?** A `carrier_checksum_rejection_total` rule that
  keeps counting. Review that checksum in the scraper; the series holds no number to test it with.

- **Are delivery emails going out?** `delivery_email_total{outcome="failed",reason="smtp"}`
  rising means the mail server refuses them: the log lines say how. A reason ending in
  `_cap` means a daily allowance is too small for the traffic, and those emails are lost.

[ops/grafana/carrier-scrapers.json](../ops/grafana/carrier-scrapers.json) is an importable
Grafana dashboard with these panels, below the carrier goals; choose its Prometheus data
source and the usage dashboard's PostgreSQL one at the top. Its "Silent carriers" table lists carriers with lookups last week but none in two
days: silence isn't success. Its "Review signals" row counts what the hourly review lists.
The "Deploys" toggle marks app restarts, where the counters start again.

## Usage dashboard

[ops/grafana/users-and-parcels.json](../ops/grafana/users-and-parcels.json) shows the
accounts, how many open their deliveries, and the parcels added and being tracked, by UTC
day. It reads the database's `metrics` schema through a PostgreSQL data source that
connects as `metrics_reader`. That role can read nothing else, and the schema holds counts
only: no account, parcel or number. To set it up, give the role `LOGIN` and a password,
add the data source and import the dashboard. In production, the Grafana container joins
the Supabase stack's network to reach the database.

- `metrics.daily`: accounts, accounts made, parcels added and parcels being tracked, with
  and without an account. The maintenance pass after each scheduled sync rewrites today's
  row and yesterday's additions, and logs `usage_metrics_failed` when it cannot.
- `metrics.active_accounts`: accounts that opened their deliveries in each day, week and
  month. `record_account_opened` counts an account the first time it opens them in a period.
- `metrics.live`, `metrics.open_parcels` and `metrics.carriers`: the latest figures, counted
  when read.

Days from before the schema existed were rebuilt from the rows stored then, so they miss
the accounts and parcels deleted earlier, and their activity counts sign-ins and session
renewals instead of openings.

## Grafana alerts

[ops/grafana/alerts.json](../ops/grafana/alerts.json) holds the alert rules Grafana
evaluates every minute, in its "Peek alerts" folder. They notify through Grafana's default
contact point. Sentry already reports each error, each carrier or provider outage,
and a scheduled sync that is missed, fails or passes its 10-minute limit (its cron
monitors). The rules cover what none of those show:

- **Peek unreachable**: Prometheus has not scraped `/api/metrics` for 5 minutes. A deploy
  takes less.
- **Peek checks failing across carriers**: in the last 3 hours, 10 checks or more failed,
  5% of them or more, across 3 carriers or more; or 5 health incidents or more are open and
  failed in the last 3 hours. One carrier failing is its own Sentry incident. Several at
  once usually mean Peek's side: the network, Chromium, the database or a provider many
  carriers share.
- **Peek scheduled sync slow**: a scheduled sync that finished in the last 2 hours took
  over 5 minutes, half its limit.
- **Peek stopped checking parcels**: no check has finished for 30 minutes between 09:00 and
  22:00 Zurich time, or for 2 hours otherwise, while Prometheus still reaches Peek. The
  scheduled sync can run without checking anything.
- **Peek server errors**: 5 API requests or more answered a 5xx in 15 minutes, health checks
  and metric scrapes aside. Sentry alerts only on a new or regressed issue, not on a known
  one that starts repeating.
- **Peek Apple web sign-in secret**: the client secret of Sign in with Apple on the web
  expires within 14 days, or Apple refuses it. The host's daily job that renews it 30 days
  ahead publishes `peek_apple_web_client_secret_expiry_timestamp_seconds` and
  `peek_apple_web_client_secret_accepted`, so this means renewals keep failing
  ([AUTHENTICATION.md](AUTHENTICATION.md)).

Each threshold sits above what ordinary days reach. The database rules read
`metrics.health` through the usage dashboard's data source: the last 3 hours' checks,
failures and the carriers they failed for, the time since a check last finished, the
longest scheduled sync of the last 2 hours, and the open health incidents and those of them
that failed in the last 3 hours, counted when read. A query that fails, as while
Prometheus restarts, leaves each rule in its last state instead of putting it in Error.

Grafana does not take rules from CI. After changing the file, run
`npm run grafana:alerts` to see what would change, then `npm run grafana:alerts -- --apply`.
It reaches Grafana over ssh and runs curl inside its container, so the admin password stays
there; it saves every alert rule and the notification policies on the host before replacing
the group. `-- --status` shows how each rule last evaluated. Set `GRAFANA_SSH_HOST`,
`GRAFANA_CONTAINER`, and the data source uids `GRAFANA_PROMETHEUS_UID`, `GRAFANA_LOKI_UID`
and `GRAFANA_METRICS_UID`.

## Incident checklist

1. Read the Sentry component, operation, carrier and error type, or the review row.
2. Put the `attempt_id` into the attempt and step queries above.
3. Find where it failed: fetch, normalize, event persistence or package persistence.
4. For classification anomalies, compare provider status, reported and selected stage,
   `status_text` and the normalized events.
5. Check nearby attempts for the same carrier: one odd shipment, or a provider change?
6. After the fix, run the carrier's tests and one controlled refresh, then check the new
   audit row and, for an outage, its `tracking_health_incident` recovery line.
