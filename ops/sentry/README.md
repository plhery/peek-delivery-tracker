# Tracking incidents

Carrier and provider outages open a single Sentry issue; their recovery is a review signal,
logged and counted but not sent to Sentry. Postgres (`record_tracking_health`) decides when, from scheduled refreshes only. Manual
refreshes, unsupported carriers and superseded work don't count. Samples last 24 hours and
hold only provider ids, outcome categories, HTTP statuses and opaque ids. A sample's
`http_status` is one a provider answered with; a carrier error the adapter raised on its
own, such as not-found, has none. A failed refresh's sample also names the check's anomaly
codes (`progress_disappeared` when an answer lost the saved progress without an error) and
routing's failure kind for each provider and carrier (`provider_failures`), which become a
refresh incident's evidence, with the error of the last tier that failed. A tier its lookup
answered past, a direct step whose retry or browser step answered, is still counted against
its own transport but is never a refresh's evidence.

Individual attempts, retries and fallbacks go to logs, metrics and Prometheus instead. A
failure that recovers never opens an issue. See [OBSERVABILITY.md](../../docs/OBSERVABILITY.md).

| Incident | Opens when | Recovers when |
| --- | --- | --- |
| Shipment refresh | The 3 latest scheduled checks of two parcels failed, the last within the hour, or of one parcel while no other parcel's latest check that hour answered; or ≥ 5 failures and > 20 % of checks in one hour | The 3 latest checks succeed and no threshold is still breached |
| Direct transport | > 50 % of ≥ 10 direct probes failed in 24 h | The 3 latest direct probes succeed and no more than half of the 24 h's failed |
| Provider lookup | > 50 % of ≥ 10 lookups failed in 24 h | The 3 latest lookups succeed and no more than half of the 24 h's failed |

While a threshold is still breached and the 3 latest samples are healthy, an incident
waits: no recovery and no reminder. It recovers once enough later checks succeed or its
failures age out of the window; a new failure meanwhile reminds as usual.

## Counting rules

- One sample per provider per scheduled refresh, so retries don't inflate the rate.
- A direct failure rescued by the browser counts as a failed *direct* sample and a
  successful *provider* sample. A carrier with only a direct step records the provider
  sample alone, so one outage opens one incident.
- Missing input, genuine not-found and a provider's answer that it has no history for the
  number yet are healthy. They count toward recovery, never toward an outage, so a parcel
  added before its first scan can't open a provider incident. So is a carrier's tracking
  that does not know the item yet (Ukrposhta, myDSV, Ecoscooting), inconclusive with the
  reason `status_api_not_found` or `portal_not_found`: routing files it as `no_history`,
  and the hand-off probes of mail bound for Ukraine can't open an incident either.
- A number the parcel's own carrier refuses as one it does not issue is the owner's to
  correct. The check stays an error or `waiting` for the parcel, and its refresh sample is
  healthy, filed as missing input, whether or not routing made the check. So is a number
  that names several parcels (inconclusive with the reason `several_parcels`, such as an
  Australia Post consignment of several articles): the owner has to give one parcel's number.
- One parcel's failed streak while the carrier answers its other parcels is that parcel's
  own, such as a number that trips the carrier's bot defences: it neither opens a refresh
  incident nor holds one open. Missing input and not-found don't count as answering. A
  carrier whose other parcels fail too, or that has no other parcel, opens at the first streak.
- A step skipped during a cooldown is not a success. A check that contacted nobody because
  everything was cooling down records nothing.
- A parcel whose own carrier says not-found stays `waiting` even if every fallback fails.
  The fallbacks can't know a parcel the carrier hasn't announced.
- If a tier stops being probed (no parcels left), its incident closes once its last sample
  ages out, with a `closed` notice instead of a recovery: none was observed. A new outage
  of that tier notifies at once.
- A direct incident whose carrier stopped running its direct step (an adapter that goes
  straight to its browser step) closes once the carrier's three latest lookups, all after
  its last direct attempt, answered and that attempt is six hours old: a `closed` notice
  with the reason `bypassed`. A direct step paused for less, as DHL Express's is for half
  an hour after a CAPTCHA, keeps its incident until it recovers.
- A direct step whose failures in the window all came from checks whose refresh still
  answered, through the carrier's browser step or a provider, left no parcel without a fresh
  answer. Its opening and reminders say `rescued` and are review signals; the incident, its
  samples and its reminders are otherwise unchanged. One failure that left its parcel without
  an answer sends the next one to Sentry.

## Delivery

Postgres serializes transitions. A repeated incident notifies at most once per 6 hours;
recovery at once. An opening or reminder is acknowledged once the SDK has flushed its event,
so it is delivered at least once (2-minute lease), and duplicates group under the same
fingerprint. A recovery, a closing or a rescued notice is acknowledged at once.

Events carry `component:tracking-health`, `incident_kind`, `incident_state` and `provider`
tags, plus a `tracking_health` context with counts, impact, evidence and next steps.
Opening and reminders share one issue per incident kind and provider. Every notification,
recoveries, closings and rescued notices included, is also a `tracking_health_incident` log line and counts in
`tracking_health_incident_total`, and the hourly review
([ops/review/signals.sql](../review/signals.sql)) lists it. Resolve the outage's issue once
its recovery shows there; the next outage of the same kind and provider then shows as a
regression.

**Alert rule:** match `component:tracking-health` at all levels. Don't add a frequency
threshold, because Postgres already applied one. Keep infrastructure alerts in separate
rules.

If the health functions are missing, the audit reports an evaluation failure; saving
shipments is never affected.

[`scraper-health-dashboard.json`](scraper-health-dashboard.json) is an importable Sentry
dashboard for step latency, errors and recovery.
