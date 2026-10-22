# Tracking routing

How a parcel refresh picks where to get its history. The code is
[`trackingRouting.ts`](../src/server/trackingRouting.ts) and
[`trackingSync.ts`](../src/server/trackingSync.ts).

The **carrier** (what the user sees) and the **source** (who answered) are separate. A
FedEx parcel can be served by the FedEx adapter or, as a fallback, by a universal
provider.

## Add preflight

Both detection endpoints ask the shape-matching carriers first. If none confirms the
number, Ship24 and ParcelsApp share a short lookup budget before browser confirmation.
They run only after the request allowance is granted. Once one has dated history, the
other gets one more second and is then cancelled; a cancelled call is not a provider
failure and is left out of the answer, health, metrics and Sentry. Without dated history
the check waits for both. A successful universal answer
returns immediately; direct confirmation continues after saving. Their outcomes distinguish missing
history, recipient input, cooldowns and service failures. A provider refusing the number reports missing
history: asking again cannot change its answer, and so does one answering with a reused number's
earlier parcel (under "A source returns another parcel"). Universal history proves that
tracking is available. A single catalog carrier named with dated movement by every provider
asked identifies an unknown parcel; conflicting names, a cancelled provider, bare brands and
registration-only history stay unresolved, and so does a carrier whose adapter refuses the number.
Several names from one provider are legs of one journey (Mayi Yinfu and the DHL network that
delivers it): the carrier the provider settled on counts only when the number's own rules
point to it alone.
That check runs the adapter's own number validation with nothing sent. The lookups name no carrier to the providers: none is
filed yet, and a guess sent with them could come back as their answer. Direct confirmation remains necessary to replace a selected carrier. The Add button stays
available while the check runs, and the saved parcel continues the full lookup chain.

Concurrent anonymous checks share work until their last caller cancels. A process-wide
cache lets the first sync consume a fresh, number-bound history before acquiring another
provider lease. This first result can be saved without waiting for speculative carrier
confirmation; later checks retain the usual confirmation policy. Credentialed lookups bypass it. Browser confirmation keeps the same reuse
policy. These caches are transient; persisted routing remains the source of retry times.

An explicit universal postcode requirement is retained separately from carrier inputs.
The owner supplies it through the carrier editor. It is bound to the number providers are
asked about (the delivery leg's after a handoff), kept across checks, kept private, and
ignored for a different tracking number. Updating it invalidates an older
sync and clears universal providers' missing-input backoffs. Other failure backoffs stay in place. Missing recipient input does not
open the shared provider circuit. Another source with progress clears the prompt.
While the parcel has no postcode to send, scheduled checks skip a provider that asked for
one, since it would only ask again; a refresh by hand still asks once its backoff is over.
A postcode request for a number whose shape fits no carrier, on a parcel that names no
carrier taking a postcode, is the provider misreading the number: it counts as
`no_history` and asks the owner nothing.
Clients show the earliest next-check eligibility; polling windows can delay the actual run.

## Source order

1. **The carrier's own adapter**, when it has one. Cainiao (`aliexpress`) is an aggregator
   too, but it is only queried for AliExpress-style numbers and the Swiss Post handoff.
2. **Carriers that recognize the number**, when the filed carrier cannot track it (see
   Carrier recognition below).
3. **Universal providers**, by default ParcelsApp → Ship24 → 17TRACK → UPU.
   - A carrier with results in [coverage.json](https://github.com/plhery/universal-parcel-scraper/blob/main/providers/coverage.json)
     gets its own order, listed in [COVERAGE.md](https://github.com/plhery/universal-parcel-scraper/blob/main/providers/COVERAGE.md):
     providers by the tier its results give them (full history, partial history, nothing
     conclusive, answered without history), HTTP providers (ParcelsApp, Ship24) before the
     browser-service 17TRACK within a tier, then the default order. A
     full history outranks a cheaper partial one because a parcel keeps the provider that
     answers first. A provider that never had history and answered with another carrier's
     parcel or refused the number format is not asked, unless no other would remain. Nor is
     one that keeps the carrier for its subscribers, whatever it once returned: Ship24 answers
     no anonymous lookup for those couriers.
   - The order is the one of the carrier the lookup is for (the delivery leg's carrier for
     its number), else of the carrier confirmed or discovered for the number, else the
     default.
   - Each provider is also told the carrier a direct lookup confirmed for the number, else
     the one the lookup is for, none for `unknown`: a hint for one that can be asked about a
     given carrier when its own detection misses (17TRACK), never proof of the carrier. A
     carrier a provider named is never sent back.
   - UPU is only for checksum-valid postal S10 numbers, always last, never remembered as
     the preferred source and never used for shadow checks.
   - Postal Ninja is off by default. `TRACKING_ENABLE_POSTAL_NINJA=true` adds it after the
     other aggregators, whatever its tier, before UPU.
   - Exception: checksum-valid China Post `C…CN` and `L…CN` numbers try 17TRACK first,
     because it returns much richer history for them (see
     [COMPARISON.md](https://github.com/plhery/universal-parcel-scraper/blob/main/providers/COMPARISON.md)), and ten-digit
     numbers try Ship24 first, for its fuller Express histories. Dedicated adapters such as EMS
     still go first, and a provider the carrier's order leaves out stays out.

For new lookups, the iPhone's device region takes precedence over a trusted Cloudflare
`CF-IPCountry` header as a country hint.
A destination reported by a carrier takes precedence. The hint puts that country's carriers
first when recognition asks carriers whether they know the number. It does not establish
the carrier, set the delivery destination or change scan clocks, though it can name the post a
postal item is handed to, as under Handoffs below. Universal providers receive neither the hint nor the visitor's IP address. Reopening a shared parcel does not change its hint.

**Affinity.** A provider that returns history is saved with its lookup number in
`carrier_data.routing`. The next check starts there, whatever its place in the order, as
long as it isn't cooling down. Providers in a better tier for the carrier are asked before
it, each within its own backoff, so a parcel moves up to a fuller history and never back
down.

For a parcel that already has movement, a provider answering with only pending or
registered history does not gain affinity. The router asks the remaining eligible
providers in the same check. If none has progress, the first usable answer follows the
normal preservation path while the previous affinity and event watermark stay. A parcel
still waiting for its first progress keeps the first usable answer as before.

**Royal Mail** starts with its own adapter using the configured private browser service. A failed
direct lookup follows the normal provider fallback route (see its
[README](https://github.com/plhery/universal-parcel-scraper/blob/main/carriers/royal-mail/README.md)).

## When a source fails

In one check, every eligible universal provider is tried once, stopping at the first
usable answer with movement if the parcel already has some. Budgets per provider: 45 s
for ParcelsApp (slow first lookups, one network retry), 30 s for the others, plus 5 s
transport allowance each. That's 120 s in total by default, 155 s with Postal Ninja,
less when a carrier's order leaves a provider out. Postal S10 lookups get 13 s more for UPU. A slow direct
attempt doesn't eat into this budget. If the budget runs out first, the discovery cursor
moves on so the next check starts with another of ParcelsApp, Ship24 and 17TRACK. Postal
Ninja and UPU stay after them.

A failed source waits before it is retried for that parcel:

| Failure | First wait | Backs off to |
| --- | --- | --- |
| Transport, a missing input, and `no_history` (a provider answered but has nothing, or the carrier lists the parcel without scans or does not know it yet; either answered with a reused number's earlier parcel) | 15 min | 6 h |
| Verification, schema, or a number or input the source refuses (`invalid_input`), as one it does not issue or one it finds several parcels under; not found by a carrier's own adapter | 1 h | 6 h |
| Not found by a universal provider | 24 h | 24 h |

A longer `Retry-After` is honoured, up to 7 days. The owner changing the postcode or link
of the parcel's own carrier ends that carrier's wait, whatever the failure, since it answered
for the old one, so the check that follows asks it again. A carrier often doesn't know a label
before its first scan, hence its shorter not-found wait. When a universal provider shows
progress the parcel hasn't had yet and names a carrier that said not found, that carrier is
asked again in the same check. A pending parcel whose failures are all not-found or
`no_history` shows as waiting, not as an outage, unless its own carrier asks for an input:
the missed checks then show that request. A provider resting on a rate limit was
not asked, and a universal provider that failed (transport, verification, an unreadable
answer) did not answer, so neither changes that; a carrier's own adapter failing does. When a failed direct adapter recovers, it
takes over from the universal provider again.

When every source fails, the parcel keeps its last progress. The error chip appears only
after two consecutive checks fail and the last successful check
is at least 1 h old by day or 3 h overnight. An answer with progress, or one for a parcel
awaiting its first movement, resets the streak. Thin answers for a universal-only parcel
count as missed checks and keep the time of the last successful check. Checks that only
wait for cooldowns leave it unchanged. Provider failures remain in the audit and health
evidence while the chip is hidden. A parcel without scans whose own carrier refuses the
number, such as one chosen by hand that issues no number of that shape, shows the refusal
at the first check. The refusal is stored as `carrier:refused_number` when the carrier's
adapter refuses the number alone ([`numberRefusal.ts`](../src/server/numberRefusal.ts)), so
the reader is told to check the number or the carrier; a refused input such as a postcode
keeps `carrier:invalid_input`, which asks for the parcel's details.

A scan an adapter dates by its day alone, with no clock (NACEX's history), is stored as a
row of that day anyway, in the carrier's order: a second after the scan before it when that
one is of the same day, else at the start of the day on the carrier's home clock, the one
the scraper's `carrierHomeZone` gives. A catalog zone of UTC would put a USPS day on the
evening before in the United States. It replaces the stage change an earlier check stored
without a time. So is a scan on a wall clock in no zone (YunExpress's) listed after a dated
scan, in an answer of the carrier's own lookup that is no fallback. Its identity keeps the clock, so two scans of a day stay two, and once a release
dates the scan, the scan takes the row over: the same stage, wording, provider code and place,
at an instant showing that wall clock. The day dates no answer for routing.

The scraper dates a scan on a wall clock in no zone on a zone it deduces: the scan's place,
a neighbouring scan's, the destination the carrier delivers in, or the carrier's home
(`time_zone_basis`), keeping the wall clock in `local_time`. Such an answer counts as dated.
What follows serves the scans it cannot date: a day alone, an impossible clock, a date
without its year.

An adapter whose answer has only local clocks or an undated status summary keeps its
evidence while a universal provider dates the timeline (`direct_local_history`, up to 100
scans). A scan kept there on its wall clock alone goes once an answer dates it: the same
wording, place, provider code and leg, at an instant showing that wall clock, one to one
([`directLocalHistory.ts`](../src/server/directLocalHistory.ts)). The adapter's answer
confirms the filed carrier, so a provider naming that carrier doesn't ask it before its
own retry time. A
provider whose history holds just the carrier's number of scans and stage only dates them:
the carrier stays first on every check. While the carrier's answer keeps that number and
stage and the parcel shows that provider's history, scheduled checks keep the history
without asking the providers, for up to 6 h after the provider last matched it. A pending
parcel is left out, since a local-clock answer would replace its summary. A provider that
adds scans or a different stage stays the source, as does one whose copy the parcel didn't
keep, and the carrier is asked again 6 h later, not on every check. While no provider has
progress for the parcel, the carrier's answer serves it if it moved, and the carrier is asked
first on the next check. When detection names that carrier for an unknown parcel's number and
its answer shows movement, a provider's progress in the same check files the parcel under
it, as one added with that carrier would be, even when the provider also names the platform
the parcel went through (MailAmericas with Cainiao) or a carrier the catalog cannot place.
A carrier detection does not name does the same when that provider names it as the parcel's
carrier, as under "Unknown carrier" below.
That carrier is then the one routing settled on (`discovered_carrier`), as the database
requires of a parcel filed from a provider's history. While no provider has progress, the
carrier's moving answer files the parcel under it and serves it, confirmed as a probe's dated
answer would be, when detection names that carrier or a provider named it; a registration
alone files nothing. A carrier only recognition found files the parcel, confirmed the same way
while the provider stays its source, when recognition settles on it alone and a provider's
progress in the same check dates every scan of its moving answer: each scan's local time, read
in the carrier's catalog zone, falls in the minute of a provider scan of the same stage, or an
hour off it (SEUR's Canary Islands run an hour behind Madrid). Only provider times with an
offset count, and a carrier the catalog lists in UTC has no zone to read its scans in.
Otherwise the parcel stays unknown: a local-clock answer can't be ordered against its newest
scan, and number shapes overlap between carriers. A parcel the sync leaves unknown keeps no
carrier confirmed for it, so a filing the sync turned down, such as a local-clock answer over a
saved summary, doesn't keep a later check from filing it. A deferred check drops the confirmation
too when the confirmed carrier answered not found or without history and no other source
answered, so the next check routes the parcel afresh. A passing failure of that carrier (a
transport error, a challenge, a rate limit, a timeout) keeps it, and so does a parcel filed under
the carrier, so that a brief not-found doesn't send it to other carriers. The carrier and number
it had confirmed stay apart (`routing.cleared_confirmation`) and file nothing: a later check
confirming them again reports no `…_confirmed` decision and adds nothing to
`carrier_checksum_rejection_total` ([OBSERVABILITY.md](OBSERVABILITY.md)).

The filed carrier's partial answer, its status without scans or only its newest scans
(`summary_only`, `history_truncated`), stands unless a provider holds a fuller history that is
not behind it, as the scraper's `fillPartialAnswer` decides. That history then takes the
carrier's facts it lacks, such as the estimate. The carrier is still asked first on every
check. While no provider holds more, the providers are asked again every 15 min by day and
hourly overnight.

**Rate limits (429).** If the last successful retrieval is recent (under 1 h by day, 3 h
overnight), the parcel keeps its progress and skips fallback until that window or the
retry time runs out. Otherwise the router tries another provider. It never retries the
throttled one early.

## Refresh cadence

Daytime is 08:00–22:00 Europe/Zurich. Overnight, everything is checked hourly, except the
parcels followed without an account that nobody watches (below).

| Parcels | Daytime |
| --- | --- |
| Out for delivery (direct adapter) | every 2 min; every 5 min while its lookups open a browser |
| Out for delivery, until 30 min before its delivery window opens | every 10 min |
| Other stages (direct adapter) | every 10 min |
| Served by a universal provider, out for delivery too | every 15 min |
| PostNL | every 30 min, also after a failure and out for delivery |
| GLS (DE, CH, FR) | at most hourly, 4 h after a failure, manual refresh included |
| Registered or accepted, no new event for 12 h (from when it was added) | hourly around the clock; manual refresh still allowed |
| No new event for 48 h (from when it was added) | hourly around the clock; manual refresh still allowed |
| Nobody waiting for it | hourly around the clock; manual refresh still allowed |
| Followed without an account, no link opened for an hour and no alert on | every 3 h, not overnight; opening a link queues a check at once |
| Without an account, filed under no carrier, added in the last 30 days, no link opened for a day and no alert on, once a carrier or provider answered for its number | every 6 h, not overnight |
| Number no carrier or provider has seen (no carrier identified, no history, no input asked) | hourly for 6 h after it was added, then every 6 h, daily after 48 h, weekly after 7 days, around the clock; manual refresh still allowed. An account's parcel, with its rechecks on: 1, 3 and 7 days after it was added, then no more after 14 days (below) |
| Archived, with a new event in the last 30 days (from when it was added), and no alert on a link shared with someone | daily around the clock; manual refresh still allowed |
| Delivered on a provider's word alone, which the parcel's carrier did not show (below) | daily around the clock for 3 days |

A parcel checked hourly or less often has an hour of its own, opening at a minute its
number gives, so by day those checks spread over the hour rather than all falling in its
first runs; copies of one number open together and share their lookups. Overnight runs
start on the hour and check them all. A carrier tracked only through its own browser, such
as Royal Mail, fails every parcel alike while that browser is down or times out: once it
fails that way in a run, the run's other checks take the same failure without asking it.

Cooldowns use the persisted `last_synced_at` and `sync_status`, so restarts and repeated
Refresh taps don't bypass them. New or reconfigured parcels are checked at once. So, at the
next run, is a parcel a provider just filed under a carrier with an adapter of its own that
the check did not ask (an Add preflight's history skips confirming it): that carrier is asked
without waiting the provider's 15 minutes (`carrier_named_at` in its routing, which that check
clears and which lapses after 15 minutes). A scheduled run handles at most five due parcels
per account, round-robin.

**Someone is waiting** for a parcel while a notification can reach them: its account has
browser or phone notifications or the delivery email on and the parcel is not muted for
them, the account has a Live Activity device, or one of the parcel's links has an alert on.
So is someone who looked lately: for an hour after the account's apps read its parcels
(recorded at most every five minutes) or one of the parcel's links was opened. Any other
open parcel is checked hourly
([`unwatched_package_ids`](../supabase/migrations/20261003180000_unwatched_parcels.sql)).

**Out for delivery**, the news is the delivery. A carrier's own adapter is cheap and as
fresh as the carrier, so a parcel it answers for is checked every 2 minutes whether or not
anyone is looking, unless nobody is waiting for it or the carrier sets a longer interval of
its own. While its lookups open a browser, as FedEx's and UPS's do, each takes seconds of
the browser service, so it is checked every 5 minutes: the last check that ran a lookup
says which (`browser_check` in its routing). A universal provider has quotas, costs more and lags behind the carrier: its
parcels keep the regular cadence, and the 15 minutes routing waits after each of its
answers, even while someone is looking. A parcel counts as served by a universal provider
while the summary it shows came from one (`tracking_provider` in `carrier_data`).

**A delivery window** the carrier announces, such as `2026-10-08 13:00–15:00`, holds the
2-minute checks back: until 30 minutes before it opens, the regular cadence still catches a
changed slot. Inside the window and after it ends, they resume. The times are read in the
zone the carrier's answer declares (`timezone` in `carrier_data`, the recipient's for Paack),
else in the carrier's catalog zone. A single time, or a
window whose zone is unknown (UTC in the catalog), is not waited for.

**Archived parcels** stay on the schedule until they have gone 30 days without a new
carrier event, so one put away while it waits at a pickup point still shows its collection.
They go through the same scheduled run, routing and cooldowns as the others, and their
checks tell their owner nothing ([ARCHITECTURE.md](ARCHITECTURE.md)). One shared with
someone who turned its link's alerts on keeps its regular cadence, as does one brought back
to the list. Archived or not, a delivered or returned parcel is not checked, but for a
delivery only a provider reported that the parcel's carrier did not show, checked daily for
3 days in case the carrier disputes it (Scenarios, below).

**Parcels followed without an account** are checked only while one of their links was
opened in the last 24 hours or has an alert on. Otherwise a parcel waits until a link is
opened again; that read queues a check at once when the schedule would run one, and never
more often than the schedule, however often the link is polled. A scheduled check made while
that one waits stands in for it. One filed under no carrier stays on the schedule for 30
days after it was added, every 6 hours by day, after the followed ones, once a carrier or
provider has answered for its number: a check can find its carrier. One whose number nobody
has seen 6 h after it was added leaves the schedule, followed or not; opening a link still queues a
check at its cadence, daily rather than weekly after 7 days, since someone is looking. With
`TRACKING_ENABLE_SPARSE_LOOKUP_RECHECKS=true` (off by default), a release's re-check of one
that left the schedule unseen contacts one universal provider at most, picked as for an
account's recheck (below). One whose links nobody opened in the last hour and that has no alert
on is checked every 3 hours by day and waits for the morning overnight: nobody reads it,
and a number no carrier is known for asks every provider in turn. A scheduled run checks at most ten of them, after every
account's share, the least recently checked first. Their own checks queue behind every account's
refresh and the scheduled run. A delivered or returned one is not checked, but for a
delivery only a provider reported (above).

**An account's parcel whose number nobody has seen for a day**, with
`TRACKING_ENABLE_UNSEEN_RECHECKS=true` (off by default), leaves that cadence for three
rechecks: once 1, 3 and 7 days after it was added, by day, archived or not, and none from
14 days. Any check since the latest of these stands in for it. A scheduled run rechecks
after every other parcel and lookup, within the account's five, three at most, and skips a
parcel checked since the run listed it ([`unseenLookup.ts`](../src/server/unseenLookup.ts)).
A recheck, like a release's re-check of such a parcel, asks the carriers as any check does
and contacts one universal provider at most: the first free one with history for the
likeliest carrier the number's detection points to, fuller first, else ParcelsApp or Ship24
in the discovery cursor's order. Opening one of its links still checks it daily, a refresh
asks every provider, and a first answer puts it on the regular cadence.

HTTP 429 without `Retry-After` is not retried immediately. Adapters that allow one
transient retry honour a `Retry-After` of up to one minute; longer windows fail the attempt.

**Shadow checks.** Once a day, a scheduled successful universal check may compare one other
provider, rotating through those in the same or a better tier for the carrier. Its result is adopted only if it has strictly newer
timestamped history and doesn't overturn a terminal state. Direct successes and the China
Post 17TRACK route skip shadow checks.

## Scenarios

| Situation | Behaviour |
| --- | --- |
| Wrong carrier selected, right one supported | On failure, try the detected carrier, then a saved confirmed route, then the carriers that recognize the number, then universals. When a universal names a supported carrier, the router asks that carrier's adapter directly, unless it is the carrier already confirmed for the number and its retry time is still ahead. If the adapter refuses the number as one it does not issue (a failed check digit, or its own number validation, which sends nothing), the router forgets the name and does not ask it. A bare brand ("DPD Group") counts only when the number leaves one of the brand's catalog networks, by shape or a preferred rule such as a DPD depot range. It swaps only after that adapter returns real progress on the same number, at least as recent as what we have, and the same stage when the provider's is delivered or returned. The UI shows "Swapped automatically from X" for 12 h. |
| Carrier needs a postcode or capability URL | Report `carrier_input_required` and continue with universals. Inputs are never borrowed from another carrier. An optional input (DPD's postcode) does not block a lookup; the lookup runs without it. |
| Only one or two universals know the carrier | The carrier's order asks them first, and the one that answers is pinned. No fan-out on normal successful checks. |
| Unknown carrier | Try one strong direct candidate if there is one, then the carriers that recognize the number, otherwise discover a universal. A number's shape stays a suggestion. One carrier explicitly named by universal dated movement, or among several the one the provider settled on when the number's own rules point to it alone, can identify an unknown parcel while the universal remains its source, unless its adapter refuses the number. So can the one carrier recognition settles on, from its own answer on local clocks, when the universal dates every scan of it (under "When a source fails"). A refusal an older release recorded lapses once the carrier answers for the number, on local clocks too. History prefetched on Add skips direct confirmation; the next run asks the carrier it names. A provider copy older than the parcel's saved summary still files it, and the newer summary stays. Replacing a selected carrier still needs direct confirmation. Once found, the carrier replaces it without the "Swapped automatically" notice, as it does an unknown postal carrier: no carrier was chosen. |
| A source returns another parcel | Numbers are reused and carriers' number spaces overlap. A history whose newest scan is more than 90 days older than the parcel, with no delivery or return, counts as no history, whichever carrier it names and whether a universal or the carrier's own adapter gives it: a seller's label can wait a month for the parcel, a label that old is the number's earlier parcel, and an old delivery may be a parcel looked up again. A scan dated on a local clock alone is read as UTC for this; an answer without a date is kept. For a parcel filed under a specific carrier, a universal history also counts as no history when every carrier it names is a different catalog carrier and its newest scan is more than 30 days older than the parcel. The next provider is asked. The carrier's own answer neither confirms the carrier nor serves or files the parcel, on local clocks either. |
| Everything fails | Keep progress, store the next check time, keep affinity until a replacement works. |
| User switches to a worse carrier | Check the new choice first. If the previously confirmed route still works, restore it with the same notice, using the postcode or link saved with it. Choosing the confirmed carrier again replaces those with what the user entered, so a cleared postcode is not reused. If the earlier route doesn't track the number on that check, a new choice with its own adapter is asked first from then on. Generation fencing cancels in-flight work. |
| Older or regressing result | Keep the newer or terminal state. A successful response never downgrades status, but for a delivery the parcel's own carrier disputes (next row). For a carrier with its own adapter, a fallback provider that answers without progress for a parcel that has some is kept out the same way: the saved summary, timeline and freshness watermark stay, and the parcel shows no error. Without an adapter of its own to ask again, the same answer is an error (`progress_disappeared`). A fallback provider answering over a summary that carrier gave keeps the saved stage and summary unless it shows a scan newer than the carrier's latest: providers often read the carrier's last scan as another stage (17TRACK's exception for UPS's out for delivery), and the parcel would flip at every outage of the adapter. Its scans are still stored. A newer scan that only announces or accepts the parcel, such as a delivery notice, never replaces a later stage (in transit, customs, out for delivery, missed attempt, ready for pickup) unless the router swapped carriers: the summary and timeline still update (`early_stage_regression`). The later stage is held only while a saved or returned scan still shows it; once a scraper fix re-reads that scan as an earlier stage, the parcel takes the furthest stage its scans show. The apps show the scan before such a notice too. A new label after a problem, a return or a delivery starts over. |
| A provider's delivery the parcel's own carrier disputes | A provider can read a delivery the carrier never made: the day an import reached the delivering carrier, or the carrier's own "delivered by airline". When the filed carrier answered in the same check on local clocks, shows no delivery or return, and moved the parcel at the time of the provider's newest delivery or later (its newest scan in a stage, the one it declared or its wording reads as, read in its zone), the provider's delivery scans are left out and a summary of delivery gives way to the carrier's own (`delivery_disputed`). A delivery only a provider reported that an earlier check saved gives way the same way, its rows deleted, even when the provider dated a later scan than the carrier's newest: the carrier's answer is then not an older one ([`disputedDelivery.ts`](../src/server/disputedDelivery.ts)). A delivery only a provider reported on a parcel whose carrier has an adapter of its own, which that carrier did not show, is checked again daily for 3 days (`unconfirmed_delivery_until` in `carrier_data`); the carrier showing it ends that. |
| Same number followed more than once (several accounts, or also without one) | A scheduled run asks each carrier, provider or recognition once for what it sends (carrier or provider, number, link, postcode, timezone, the carrier a provider is told) and every copy saves the answer for itself. Copies that send the same, with the same country hint, also follow the routing of the copy checked last before the run: which provider answers, cooldowns, the carrier recognised or confirmed for the number and delivery-partner checks. Each keeps its own carrier choice, check streak, freshness watermark and next check. Nothing else of one copy (name, notes, other inputs) reaches another, and a copy whose saved route used inputs it no longer has shares only answers ([`sharedLookups.ts`](../src/server/sharedLookups.ts)). |
| Same scan from several sources | Stored once. A universal copy of a stored scan is not stored: the same instant with the same wording or stages that agree (the same, or one left unclassified), a source that keeps only seconds or minutes, or a zone the provider misread. Against a carrier's own scan at the same time, the copy's stage does not matter: a provider often reads a carrier's scan as another stage (17TRACK's exception for UPS's out for delivery, an acceptance for Austrian Post's handover), and shown beside the carrier's row it reads as a second, alarming scan. The same wording may carry a further sentence before or after it, or a detail after an arrow (17TRACK's "Arrived at USPS Facility -> Your item arrived…"). With no row at the scan's own instant, a row at an instant no other scan of the reply shows matches when one of the two is dated to the second or minute the other falls in, with the same wording, or with other wording, in any stage, when a provider's copy and a carrier's scan meet, one to the minute and the other to the second (17TRACK adds seconds of its own to Shiprocket's minutes), the places do not conflict and the match is one to one. The carrier a parcel was handed over from retells the next one's scans to the minute, as India Post does La Poste's, so its scan to the minute meets no copy to the second. Failing that, it matches with the same wording a whole number of quarter hours up to 14 h away, compared to the second when only one of the times keeps milliseconds; against a carrier's scan it may be worded otherwise when it is at least an hour away, its time has seconds other than zero and the stages agree or one is pending. Times to the minute fall whole quarter hours apart too often by chance, and carriers stamp some scans 15 or 30 minutes after another to the second. Across an offset, two places that share no word, countries aside, are two scans. A carrier's scan takes over, on its own clock, the universal copy stored while its lookup was down. Every other universal copy at the instant of a carrier's classified scans, in any stage and from no other place, goes with that check (one provider reading a scan twice, or two providers copying it): a scan that matched no row takes one over, in its own stage when one is, and the rest are deleted. The carrier's own scans at one instant stay apart. A provider's later copy of a scan a carrier holds is not stored, so the deleted copies do not come back. A row only a universal provider has written names that provider (`universal_provider` in its raw data) and follows the provider's clock: when a scraper fix moves the provider's scans, its next answer moves each such row in place, unless another row already holds the scan by the rules above: then the row on the old clock is deleted. So is such a row the fix gave another identity, when the provider's answer no longer shows its instant, shows an older scan, and shows the scan a zone shift away where another row holds it. A carrier's scan writes the row without the name, so a row a carrier took over keeps the carrier's clock, and so does a row stored before rows named their provider. A match or a move across an offset, not just within a minute, records the `provider_clock_offset` anomaly with the check. The scraper's identity policy can also allow a scan to update its one stored row at the same instant, checking the provider code, wording, stage or location as required by that source. Whatever the source's policy, a scan takes over the one stored row of its source, instant and place whose wording it fills in: a row an adapter stored with a placeholder of the carrier's template left unfilled (`{{…}}`), which a scraper fix would otherwise leave beside the scan, matched one to one. Required provider codes distinguish scans sharing an instant when each incoming and stored scan has exactly one matching counterpart. Swiss Post and universal scans can also gain a location there with unchanged wording and stage and unique matches. A policy's `relabelledFrom` names the zone a source once put on every wall clock (India Post's scans abroad, read on India's clock until October 2026): a scan whose wall clock carries another offset takes over the one stored row with its provider code and location whose wall clock in that zone is the same, and back again, whatever the wording. A policy's `matchWallClock` serves a source whose feeds put different offsets on one scan's wall clock (DHL Express's mobile API reads it on the facility's zone, its global portal gives the offset of the system that wrote the record): a scan takes over the one stored row showing its date and time, to the second, under another offset, with the same location and wording and, when both carry one, the same provider code. In a handoff's sync the earlier carrier's scans follow that carrier's policy, and its relay of a delivery scan in the same second and place takes over no universal copy: the delivery scan does, and the relay stays the earlier carrier's row. The row keeps its identity, creation time and notification receipts, so richer details do not announce an old scan again, unless they make it a delivery or pickup readiness the parcel had not shown ([ARCHITECTURE.md](ARCHITECTURE.md#where-things-live)). A universal copy that names its provider goes with the parcel's next check when it sits on a clock an earlier release misread: a whole number of hours from a carrier's own row of the scan, up to 14 h, with the same wording and place, stages that agree and, when both carry one, the same provider code, compared to the minute when the copy keeps only the minute; or, in any wording or stage, from a carrier's row at the same place by the shift in whole quarter hours that most of the provider's copies of the parcel show from such rows, two at least, compared to the minute when either keeps only the minute. A row at the copy's minute and place, the provider's reading at the carrier's minute, a second copy of the row from that provider, or another scan of the reply holding the copy keeps it. A stage change a check stored at the time it saw it, with no scan time, goes once one scan of a reply dates it: the same stage and wording, the same provider code when both carry one, the same place unless one names none, no later than the sighting and, when the sighting kept the scan's wall clock, showing it in the scan's zone or whole quarter hours from it. A sighting that kept the time of day is also matched with the stored rows, and a new one a stored row holds is not stored. A scan new to the parcel with a time of day takes over the sighting's row when it comes from the sighting's source or the sighting from a provider; otherwise the sighting is deleted. Other undated observations and ambiguous matches are stored separately ([`eventIdentity.ts`](../src/server/eventIdentity.ts)). Once a direct carrier is confirmed, the timeline leaves out the copies these rules keep: a row only a universal provider wrote, at the minute of one of the carrier's scans, or in that scan's stage within 15 minutes or a whole number of hours up to 14 h away, with that provider's other rows of the minute of a copy only whole hours from the carrier's scans, which it read a zone away; of a scan dated by its day on a wall clock in no zone, a copy in its stage and words whose time shows that wall clock, whole quarter hours away up to 14 h. A provider's step the carrier never shows, such as another leg's scans, stays, and neither push nor a Live Activity announces any of these copies. A universal row without the provider's name counts as the provider's only inside the time the carrier's answers have covered, when none of them held it (`direct_history`). The rows stay stored and in the account's export ([`providerCopies.ts`](../src/server/providerCopies.ts)). |

## Carrier recognition

A number whose shape fits several carriers or only the generic postal carrier is checked
with eligible direct carriers ([`carrierDetection.ts`](../src/server/carrierDetection.ts)).
HTTP recognition uses the catalog's `tracking.recognition` and the adapter's `recognize()`.
If no recent carrier is confirmed, the server checks candidates declaring
`tracking.browserRecognition` through `recognizeWithBrowser()`. Definite HTTP misses do
not get a browser retry. Browser confirmation requires dated shipment activity; shells,
undated defaults and old reused numbers cannot identify a carrier. Candidate selection
comes from the scraper's catalog, and the Add sheets show the possible candidates.
While checking, they show a general progress indicator; only the server's answer names
the carriers checked. A failed request does not manufacture a queried-carrier list.

- **Which and in what order.** Matching low-confidence rules that qualify, including
  those hidden by the generic postal detection: the
  carrier a universal named first, then those a `preferred` rule backs (a DPD depot
  range), then networks based in or serving the device or visitor country, then
  optional aggregate priorities for the number's shape and the catalog's
  `recognition.rank`. Country and aggregate hints change query order only; the
  answer's `asked` list identifies the carriers actually checked. At most five are asked
  at once. Aggregate priorities contain no tracking numbers and fall back to catalog order
  when the production evidence is too sparse.
  `CARRIER_RECOGNITION_PRIORITIES_PATH` optionally points to a server-owned aggregate file
  from the scraper's analyzer; it reloads periodically and contains no raw inputs. See the
  scraper's [architecture](https://github.com/plhery/universal-parcel-scraper/blob/main/ARCHITECTURE.md).
- **Settling.** Only an answer for a recent parcel counts; an old parcel can share a reused
  number. Only valid scan instants with explicit offsets establish activity age;
  unresolved local clocks cannot rank a match. One answer wins. With several, AliExpress
  wins when it is among them: it follows some parcels under their delivery carrier's
  number, so the other answers are the delivery leg of the same parcel (see
  [Handoffs](#handoffs-two-carriers)). Else the one number evidence backs, else the most
  common network of a single brand (GLS Switzerland and GLS Germany answer from one
  overview). Other unrelated carriers that all know the number are a choice for the user.
- **In the Add sheet.** The detect route asks once the number is settled (the field loses
  focus, which includes opening the carrier picker, a paste, a shared number), within
  three seconds for the first five HTTP candidates and, when none of them knows the number,
  three more for the next five, then up to twenty seconds for browser confirmation. At most two
  browser recognitions run per process, and identical requests share work. Complete answers
  are cached per number and country hint for ten minutes; browser answers and their history for five minutes,
  failures for thirty seconds. Changing the number or leaving the form cancels its request;
  the final cancelled caller also stops shared browser work. The answer lists
  the carriers asked (`asked`) and those that failed or ran out of time (`unanswered`), so
  the sheet can tell "not found yet" from "could not check". With automatic detection a
  single answer selects the carrier, and its required inputs (the GLS postcode) appear
  before saving; for a carrier picked by hand it only points out the one that has the
  parcel. Several answers ask the user to choose. Automatic detection stays a valid
  choice throughout, and the check never holds the Add button.
  When dedicated carriers cannot confirm the number, preflight also asks the fast universal
  providers. Once they answer, their outcomes tell "not found yet" from "could not check",
  and the sheet names tracking services instead of the carriers asked. Saving continues the full provider chain. The detect route and a newly created
  parcel's first saved check ask a second batch of unqueried HTTP candidates when the first
  found none. Detection gives it three seconds of its own; the first check's batches share
  its HTTP budget, and later refreshes ask one batch. Reused universal history still takes precedence over speculative confirmation.
  A pasted number continues into that lookup when recognition
  finds no carrier or cannot answer, so the universals can retrieve its history. A typed
  number waits for Track. Multiple carrier matches and missing inputs still require a choice.
- **A carrier picked by hand.** When the reader picks a carrier whose adapter refuses the
  number as one it does not issue, the Add sheet (web and iPhone) and the front door say so
  on the carrier line before tracking starts (`/api/public/refusal`), and the change-carrier
  sheet under the carrier it shows. The adapter's own check runs on the server and sends
  nothing, so no verification or allowance applies. The reader can still track the number
  with that carrier. The change-carrier sheet (web and iPhone) also goes back to automatic
  detection, which files the parcel as adding it would: under the carrier its number's shape
  names, else under no carrier, so routing looks for it. A parcel filed under no carrier opens
  on it; an Amazon number is not offered it.
- **In routing.** When the filed carrier cannot track the number (no adapter of its own,
  its adapter answered not-found, or an unconfirmed carrier failed and is absent from
  the low-confidence format's candidate list), the router asks
  before the universals, never another network of the filed carrier's brand, for open
  parcels in their first 30 days outside linked journeys. A carrier that knows the number
  and needs no input gets a full correction lookup, adopted only on real progress on the
  same number, at least as recent as what we have (a pre-advice is not enough). Browser
  confirmation follows unresolved HTTP checks and uses the same candidate cooldowns. Fresh
  browser history is consumed once by the correction lookup or the first saved sync, with the
  "Swapped automatically" notice. A carrier whose lookup hands the parcel to its delivery
  carrier in the same check is adopted the same way, with the delivery carrier as the parcel's
  current leg. A carrier that needs the user's input is saved as
  `routing.input_needed`, and the parcel asks the user for it.
  A parcel filed under no carrier that no carrier or provider has answered for yet is offered
  the first carrier detection lists for its number that serves the country it was looked up
  from and needs only the delivery postcode for numbers of its shape, which recognition
  cannot ask without it (Mondial Relay's 8-digit shipments): `routing.input_suggested`, set
  at each check. The prompt says the carrier may have the parcel; the owner adding the postcode
  files it there. Lookups without an account show no prompt.
  A transient failure of a matching carrier and failures of an already confirmed carrier
  do not trigger recognition.
- **Retries.** Answers are kept in `routing.candidate_probes`, not with the carrier
  failures, so they decide neither the parcel's status, nor the filed carrier's retry, nor
  the health evidence of the check. The next check waits 1, 2, 4, then 6 hours, since a
  parcel appears once it is handed over, and a day after six misses.
- **After a scraper release.** A parcel keeps a digest of the catalog that last routed it
  (`routing.rules`: the scraper release, each carrier's detection rules, names, whether it
  has an adapter of its own and whether that adapter's clocks are local), so every release
  changes it: one can change what an adapter answers with its catalog entry unchanged. The
  first check under another release asks the parcel's own carrier again, whatever wait its
  answer on local clocks set, and a carrier with an adapter of its own that refused the
  number or gave an unreadable answer (`invalid_input`, `schema`) has no wait left, unless
  its lookup can open a browser. After each deploy, once the first scheduled run is over, the open parcels added
  in the last 30 days that were routed with another catalog are read again, with nothing
  sent ([`redetection.ts`](../src/server/redetection.ts)). A check is queued for each one the new catalog points to a carrier it has not asked yet: the carrier its
  number is now detected as, the one a provider's saved carrier names now read as, or the
  filed carrier's new adapter. A filed carrier only recognition asked, before the parcel was
  filed under it, counts as not asked: a probe drops an answer on local clocks. So does a
  filed carrier that answered on local clocks a provider then dated, once the catalog dates
  its scans: its own answer can now serve the parcel. A carrier the catalog still gives as
  answering on local clocks is asked again once the parcel has gone 12 hours without a
  check: the release may date its scans. A parcel
  filed under no carrier is also queued for a carrier that recognizes its number under the
  new catalog and did not under the one that last routed it: each of its checks notes the
  carriers recognition can ask about the number (`routing.recognizers`). Asking again a
  carrier it already asked is left to its schedule, and for a lookup that left the schedule,
  to opening its link: after a release it rarely finds more. A carrier whose adapter
  refuses the number by its own check, which sends nothing, is passed over until a release
  whose adapter takes it. One that refused the number under an older release counts as not
  asked, the filed carrier and a provider's name the parcel already holds included. One whose
  answer was set aside counts as asked. A carrier the parcel already asked whose lookup can open
  a browser (the catalog's `tracking.browser`) is asked again only once the parcel has gone 12
  hours without a check: its answer would most likely be the same. Another is asked again at
  once. A set-aside answer that the parcel's newest scan has since
  come back to, or one on local clocks the catalog now dates, doesn't wait. The checks most
  likely to change the answer go first (a carrier's answer the catalog now dates, a number it
  refused under an older release, or a set-aside answer that would now be taken, then a
  carrier never asked or newly recognizing the number, then one asked again), 50 at a time: the next batch goes once the last
  one's checks are done. One still in its cooldown is queued by the first scheduled run after
  the cooldown ends. These checks wait behind every other job, a lookup's own check too, and
  keep the scheduled cadence; they are recorded with the `release` trigger. Asking for the
  parcel's check moves a queued one up. Only parcels filed under no carrier, or showing a
  provider's history, are looked at. A lookup nobody follows any more gets this one check,
  unless the schedule keeps it as one filed under no carrier.

## Handoffs (two carriers)

A parcel often changes carrier at the border. The origin history is always kept.

- Adapters can report `delivery_carrier`, `delivery_tracking_number` or an official
  partner link. Names and links are resolved through the carrier catalog.
- With no named partner, a checksum-valid S10 number and a reported destination country can
  propose **one** national-post lookup ([catalog hints](https://github.com/plhery/universal-parcel-scraper/blob/main/core/catalog/hints.ts)).
  When the origin reports no destination, as An Post's doesn't, or cannot answer, the country
  hint stands in unless it is the country that issued the number. It does so too when the
  origin is asked for a parcel filed under another carrier or none. The issuer suffix and
  transit scans never pick the operator.
- AliExpress follows some parcels under the delivery carrier's own number, such as an SDA
  waybill in Italy, and names no partner for it. The proposal is then the one carrier at
  home in the destination country whose number rule fits beside AliExpress's, asked with
  the same number.
- The partner is only adopted once its own adapter (no required inputs) returns dated,
  fresh progress: a scan no older than the origin's newest. A partner's time on a whole
  minute is compared to the minute, since a partner relaying the origin's scans may keep
  only their minute (CTT's copies of Royal Mail's). A partner whose only input is optional
  (DPD's postcode) is asked without it, and only with a reference of its own number shape,
  never the origin's postal number.
  Failed confirmations wait 55 min, doubling with each miss up to 6 h, unless the origin
  history or the partner changes; the wait stays 55 min while the origin is down, from an
  ask the failed check saves too, and a check a provider answers in the meantime keeps the
  misses and the last ask. It is
  5 min on a refresh by hand, and once the partner has announced the parcel: a dated
  record no older than the origin's newest scan, without a scan of its own. An origin
  asked without the parcel's data, as for a parcel filed under no carrier, keeps the same
  wait.
  A partner that does not know the item yet, or answers inconclusively, is not
  reported as `provider_failed`; its network failures and server errors still are.
- Until then the parcel is followed on the origin. The cards, parcel links and the delivered email
  already name the partner the origin names (`delivery_carrier`) as who delivers, and the cards and
  links show its number (`delivery_tracking_number`) after the followed one.
- The reverse holds too. A carrier that confirms the number and would hand the parcel to the
  carrier it is filed under, with the same number, is an earlier leg, such as a customs agent
  before the local courier. Its history is shown while the filed carrier has none, but it
  neither replaces the filed carrier nor raises `carrier_mismatch_confirmed`. The filed
  carrier is asked first on every check.
- A carrier asked about a parcel filed as unknown or under another carrier, that confirms the
  number and hands the parcel on in the same lookup, files it as it would without the
  handoff, and the parcel keeps the handoff. The database takes such a correction only when
  the handoff starts from the new carrier under the parcel's own number.
- Once adopted (`handed_over_at`), the partner answers for the parcel. The origin publishes
  some scans late, so for a day it is still asked alongside the partner, at most every 55 min
  (`earlier_checked_at`). Its scans join the history; its failures and its clock do not touch
  the parcel's summary or freshness watermark, and a stage it reports without a time adds no
  row stamped at observation time. A scan of the origin's that reaches the parcel
  after a later one announces no push or link alert
  ([`relayCopies.ts`](../src/server/relayCopies.ts)); one newer than everything stored does.
- After the handoff the origin often goes on telling the partner's scans in its own
  words, within a minute of them. Both rows are stored, since the same rule now and then
  pairs two scans of their own. A row of the earlier carrier at the same stage as another
  source's, within 60 s either way, is served with `relay_of`, that row's id (closest pairs
  first, one pair per row). The apps show its words as a line under that row instead of a
  step of its own. Of a pair, the row stored second announces no push or link alert, and
  the delivered email quotes the other one ([`relayCopies.ts`](../src/server/relayCopies.ts)).
- Compatibility: AliExpress `L…CH` numbers keep a Swiss Post confirmation probe, and
  selected Swiss postal routes keep their Cainiao fallback. On such a route Swiss Post is
  asked first; once it has missed the item, Cainiao answers first. Cainiao's news asks Swiss
  Post again at once; without news the gap doubles with each miss, from 55 min up to 6 h
  (5 min on a refresh by hand). When Cainiao fails after Swiss Post was asked, the check
  saves that ask without a miss.

## UPU history

UPU times have no reliable zone. Its scans are archived separately (up to 1,000 per
number). Only newly observed milestones enter the visible timeline, stamped at observation
time and flagged as lacking a provider time. UPU can update its own summary but never
proves freshness against another source, and forecasts are ignored. See the
[UPU README](https://github.com/plhery/universal-parcel-scraper/blob/main/providers/upu/README.md).

Universal providers also get the catalog timezone of the parcel's carrier, used only for
scans with no trustworthy zone of their own (ParcelsApp's, and Ship24's offset-less legs).
When that carrier's zone is UTC (Asendia, `unknown`), they get the zone of the carrier a
direct lookup confirmed for the same number instead, if any. The freshness watermark (`last_event_at`) reads
offset-less times in the result's zone, exactly as the stored events are read, and so does
the sync when it checks whether a returned summary is older than the watermark. A universal
copy of a stored scan counts at the stored scan's instant, and a row a scan takes over or
moves at that scan's instant. So does a provider's copy the sync could not tell from a
carrier's scan: one later in the scan's minute, or a whole number of hours up to 14 h later,
in any wording, with stages that agree and from no other place (a word may shorten another,
as Swiss Post's LON does ParcelsApp's LONDON). A provider's scan dated more than 5 minutes
past the check that first returned it counts at no instant: only a misread zone dates a scan
in the future. A provider that misread the zone therefore cannot set the
watermark hours ahead and make the carrier's own reply look older, and the watermark comes
back once the provider's corrected answer moves its rows. A watermark a provider's summary
set is that provider's newest scan, which the sync stored, so one past every stored scan
comes back to the newest, its copies having moved since. The router holds a carrier
detection or a provider names to the same rule before taking it: its newest scan counts
against the watermark less the copies of its own scans, stored or in the answer of the
provider that names it. A newest scan dated to the minute (Sameday's) is as new as a watermark
within that minute. An answer without any date is neither older nor newer, so it does
not displace the parcel's dated progress either. An answer it sets aside stays in
`routing.set_aside` until the carrier is asked again, so the review tells it from a carrier
not asked: why (older, no movement, local clocks only unless the check then takes that
answer, undated, or behind a provider's delivered or returned stage), when, how many checks
in a row,
and for an older one its newest scan and the watermark it lost to. The app's "Tracking added"
row is no scan, so a parcel added after its newest scan keeps that scan's time. Nor is a row stamped when it was
observed, such as a UPU milestone: its observation time never holds the watermark ahead.
Over a parcel still pending, an answer that moved is not older for rows its provider wrote
and no longer shows, such as a notice it dated at the lookup, when every row past its newest
scan is one of those. A row from another source past it, a reused number's new label for
instance, keeps a reused number's older history out.

When a scraper fix changes a stored scan's instant, a row only a universal provider wrote
moves on that provider's next answer. Other rows need a data migration that repairs the
row, summary and watermark together, preserving its creation time and notification
receipts. A source the fix still reads can instead name the old zone in its identity
policy, and its next sync moves each row in place. Any repair that moves, rewrites or
deletes rows ends, in the same transaction, with
`select * from private.settle_repaired_parcels(array[...]::uuid[])`
([migration](../supabase/migrations/20261018112000_settle_repaired_parcels.sql)): each
parcel's watermark becomes its newest scan left, and a summary whose stage no row left shows
takes the newest row's stage and words. It returns each parcel's stage and watermark before
and after, so a dry run rolled back shows what the repair changes.

## Direct histories without complete timestamps

Some direct feeds omit scan clocks, offsets or the event year. An adapter may guess a clock
it knows the feed uses, such as PostNL's own records on Amsterdam time. The guess stands
only if it fits between the feed's dated scans and isn't after the lookup
([`settleGuessedClocks`](https://github.com/plhery/universal-parcel-scraper/blob/main/core/time/index.ts)). The router keeps
histories that are still incomplete separately, including return-leg and summary markers.
When the current status has no complete timestamp, it tries providers for dated progress. If
providers cannot help, the direct current status remains available without advancing the
freshness watermark. An unresolved direct lookup cannot displace a carrier already confirmed
by dated progress. A status summary without scans also preserves previously saved direct
history. The sync preserves richer saved progress and records status changes as
observations, keeping observation time distinct from a carrier scan time. Where the parcel
is going outlives the summary that named it: a reply that names no destination keeps the
saved one, and an older reply names it for a newer summary that lacks it, unless the router
swapped carriers. The map draws the rest of the way to it.

## Tracking links

The link shown in the app follows `tracking_provider` of the result on screen: a 17TRACK,
ParcelsApp or Ship24 result links to that provider with the lookup number. Direct recovery
restores the carrier link. When the carrier's own lookup answered the same check and only its
missing clock or partial answer sent the result elsewhere, the result is marked
`carrier_answered` and the link stays with the carrier. Postal Ninja and UPU link to their public forms. No deep links are
guessed.

## Shared provider protection

`tracking_provider_health` (service role only) coordinates workers:

- one 90 s lease per universal provider, token-fenced, expiring if a worker crashes;
- 5 s spacing after a healthy call or one without history. A check that finds a provider
  free again within 6 s waits for it (twice at most) when the call still keeps its full
  lookup budget, rather than recording a cooldown and moving on;
- cooldowns: 429 → at least 15 min, verification → 1 h, other failures → 1 min to 1 h
  (exponential);
- not-found, `no_history` and a refused number (`invalid_input`) are per-parcel and never
  open a global cooldown.

If coordination fails, universal calls fail closed. A failed completion write never throws
away data already retrieved.

## Routing decisions

Each routing decision is a `tracking_routing` log line with its `decision`, `carrier`,
`provider`, `category` and the check's `attempt_id`, and a breadcrumb on any later Sentry
report. Only `health_store_unavailable` opens a Sentry issue (`component:tracking-routing`,
grouped by decision, provider and category). The four signals are what the catalog,
detection or the adapters could act on: each check saves them for the hourly review in
[OBSERVABILITY.md](OBSERVABILITY.md).

| `decision` | Meaning |
| --- | --- |
| `transport_fallback` | A direct path needed browser/TRAWL recovery, even if it then succeeded |
| `provider_failed` | A provider failed for a parcel: `category` `rate_limited` needs less traffic, `schema` parser work, `invalid_input` a number the source refuses |
| `all_providers_unavailable` | Uncovered parcel or broad outage |
| `health_store_unavailable` | Migration or database coordination problem (Sentry issue) |
| `carrier_auto_swapped` | A carrier correction was committed. A signal, unless detection already names the new carrier for the number or lists it first among the carriers to ask: then the line has `category` `detected` |
| `carrier_mismatch_confirmed` | Signal: a carrier that detection neither names nor lists first for the number tracks the parcel, so detection rules could be improved |
| `detected_carrier_confirmed` | The carrier detection names or lists first for the number tracks a parcel filed as unknown or under another carrier, such as one added before a detection rule existed |
| `candidate_probe_confirmed` | Carrier recognition found the carrier and it tracks the parcel |
| `foreign_history_rejected` | A source returned an older parcel for the number (`provider` is the universal, or the carrier whose own adapter answered): one never delivered or returned, or, from a universal, another carrier's |
| `carrier_input_needed` | A recognized carrier needs the user's input (the GLS postcode); the parcel asks for it |
| `direct_support_opportunity` | Signal: a carrier without an adapter of its own is tracked through a provider, a candidate for an adapter. Reported once per number |
| `carrier_coverage_discovered` | Signal: a provider named a carrier the catalog doesn't know |
| `fresher_provider_found` | A shadow check found newer history at another provider and the parcel moved to it. Evidence to revisit the default order |
| `delivery_disputed` | The parcel's own carrier disputed a provider's delivery in the same check, which was left out. Evidence for the provider's status map |
| `coverage_contradicted` | A provider disagreed with the carrier's coverage results: `category` `history` when one that answered without history has the parcel, `no_history` when one with history does not while another has it. Evidence to rerun the coverage probe |
| `provider_recovered` | A provider answers again after failing |

`carrier_coverage_discovered` fires once per parcel, only for names that are neither a
catalog name/alias nor a known network of DHL, DPD, GLS or Hermes. The postal union's feed,
which an aggregator lists as "UPU" beside a parcel's carriers, is not reported either.
Timings and metrics are in [OBSERVABILITY.md](OBSERVABILITY.md).
