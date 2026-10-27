# Scraper package

The app uses an exact release of [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper)
as an in-process npm dependency. The scraper owns carrier data, detection, adapters,
provider ordering, input validation, status classification, handoff suggestions and places.
The app owns accounts, polling, routing state, leases, persistence, notifications and clients.
See [architecture](ARCHITECTURE.md), [routing](ROUTING.md) and [observability](OBSERVABILITY.md).

## Public imports

- `universal-parcel-scraper`: browser-safe catalog, detection and result helpers.
- `universal-parcel-scraper/app`: browser-safe helpers shaped for this app, such as its
  parcel view, the clocks its sync uses, carrier scan-identity policies and what a carrier's
  status map says about observed wording. The scraper changes them with the app, outside
  its semver contract.
- `universal-parcel-scraper/node`: registry, tracking, transport and telemetry interfaces.
- `universal-parcel-scraper/places`: server-side place resolution.
- `universal-parcel-scraper/data/*`: published data used by generators.

ESLint refuses any other path into the package.

The app supplies its browser service and telemetry recorder through `AdapterEnvironment`
([`adapterRegistry.ts`](../src/server/adapterRegistry.ts)). `CarrierTrackingAdapter`
delegates to `trackCarrier`; the app's router chooses providers and saves their outcomes.
Commercial universal providers are explicitly enabled by the app.

DPD Germany and DPD Switzerland read through a session that takes tens of seconds to open.
A production server with `CANONICAL_ORIGIN` calls `warmDpdSession` with the same
environment at start ([`instrumentation-node.ts`](../instrumentation-node.ts)), so the
scraper keeps a session open in the background instead of the first DPD lookup after a
deploy waiting for it. The production servers CI starts have no canonical origin, so they
send DPD nothing.

The server also gives it a store in `dpd_app_sessions`
([`dpdSessions.ts`](../src/server/dpdSessions.ts)), service-role only. A deploy takes up
the newest saved session, whatever its age: the scraper keeps a session until DPD
refuses it. It checks the current session once an hour, so that a refusal opens the next
one before a lookup meets it, and each session it stopped using until DPD refuses it twice
in a row, which tells how long DPD really accepts a session
([how to read it](OBSERVABILITY.md#dpd-app-sessions)). A token is an anonymous session of
DPD's public app and names no account, parcel or person. Without the table, or when the
database fails, the scraper opens a session as it did before.

The scraper calls a carrier's postcode input `postcode`. The app's API, database and
clients call it `dpdPostcode`, so the app renames it wherever the scraper hands it over:
the requirements and checked inputs ([`lib/carriers.ts`](../src/lib/carriers.ts),
[`server/carriers.ts`](../src/server/carriers.ts)), the input a recognised carrier still
needs ([`trackingRouting.ts`](../src/server/trackingRouting.ts)) and the contract generator.

A national carrier's postcode requirement states its own example. One that takes any
country's postcode leaves it out, and the forms show the reader's country
([`postcodeExample.ts`](../src/lib/postcodeExample.ts), `CarrierCatalog.postcodeExample`
on iPhone): the device's time zone or language region on the web, its region setting on
iPhone, the carrier's own country otherwise. The examples live in
[`shared/postcode-examples.json`](../shared/postcode-examples.json).

A delivery postcode can be from another country than the carrier's own, such as a PostNL
parcel delivered in Belgium. A postcode field that can take more than one country names
the country beside it: any country when the requirement's validator is
`internationalPostcode`, the carrier's catalog countries otherwise. It starts on the
country stored with the parcel's postcode, then the parcel's destination, the reader's
country and the carrier's own. For another country than the carrier's first, the field
takes any country's shape and shows that country's example
([`postcodeCountry.ts`](../src/lib/postcodeCountry.ts), `CarrierCatalog.postcodeRequirement`
on iPhone). Only such a country is sent: `dpdPostcodeCountry` in the API
(`postcodeCountry` for a link's postcode), kept with the postcode it came with in
`carrier_data.postcode_country`, and given to the scraper as the lookup input's
`postcodeCountry` wherever that postcode goes
([`server/postcodeCountry.ts`](../src/server/postcodeCountry.ts)). A changed postcode
goes without it. An adapter that does not read it asks as before.

The scraper validates its own catalog. The app contract generator projects the catalog
onto the client API, excluding what only retrieval uses: refresh pacing, clocks, whether a
lookup can open a browser and the page the scraper's canary probes. The scraper's stage vocabulary must agree with the
contract. Brand palettes, decals and truck geometry live in `src/brand`;
`scripts/generate-brand.mjs` feeds both web and native resources.

## Tests

Parser, detection and live-provider tests belong to the scraper. App tests replay synthetic
`CarrierResult` fixtures to exercise routing, event identities and persistence, and use
the public registry to check dispatch and the host recorder.
Fallback input and persistence scenarios supply provider plans explicitly so they can
exercise each provider independently of coverage ordering.

To try a scraper change on the app before its release, install the scraper's packed tarball
with `npm install --no-save <tarball>` and run the app's tests. Outside CI,
[the pin check](../src/server/scraperDependency.test.ts) then skips and names both
versions; CI installs the pin with `npm ci` and keeps it strict.

## Updating the dependency

`.github/workflows/adopt-scraper.yml` moves the app to a scraper release. Once in each
2-hour slot, starting 7 past every even hour from 06:07 to 22:07 UTC, it takes the newer of
npm's `latest` and `next`, so one deploy carries every release published since the slot
before. The last slot lasts until 06:07. A systemd timer on the netcup production server
starts the workflow at each of the 9 slots (`workflow_dispatch`). GitHub starts scheduled
runs hours late or drops them, so its schedule, every half hour, and a CI run that ends on
main only back the timer up. The first run in a slot that pushes a release is its slot's
adoption; later runs look at the workflow's runs, find that one and stop at once. A run that
finds nothing newer leaves the slot to the next run, so a release published in the meantime
does not wait for the next slot. Each run records the release it tries as an artifact
(`release-<version>`): when that release fails, such as the app's tests with it, the slot's
later runs that would take it stop at once too, and a newer release still adopts. In the
night's slot, which lasts eight hours, the first CI run on main from two hours after the
failure tries it again, so a fix that lands at night does not wait for 06:07. The timer
sets the workflow's `slot` input. A run without it, by hand, always adopts but never counts
as its slot's adoption, so a release published after it still adopts in the same slot. An
urgent release is adopted by hand:

```bash
gh workflow run adopt-scraper.yml                    # the newer of npm's latest and next
gh workflow run adopt-scraper.yml -f version=1.2.3   # one exact release
```

It never downgrades. npm lists a release before it serves its tarball, so the run waits up
to half an hour until the tarball answers, and tries the install again for a few minutes
while it still answers 404. It then pins the release, checks the database gate and optional
peers, regenerates the contract and iPhone resources, and runs the app's typecheck and unit
tests with them. A second job, which runs nothing of the release, pushes the result to
`main` as one commit. CI validates that final commit before deployment
([deployment](DEPLOYMENT.md)); adoption leaves the browser journeys, the production build
and the iPhone app to it. A commit that reached `main` in the meantime is kept when it
leaves the adoption's files alone; otherwise the run fails and its summary gives the
command that starts it again. When `main` already pins the release or a newer one, such as
after a second run for the same release, the run ends without pushing or failing.

When the app fails its typecheck or unit tests with a release, nothing is pushed and
`main` keeps deploying the release it has; every later release fails the same way until
the app is fixed. When the release changed an answer on purpose, such as the carrier a
number is detected as, either make the app's tests accept both answers on `main` and start
the adoption again, or adopt the release by hand: one commit with
`npm install --save-exact universal-parcel-scraper@<version>`, `npm run contract:generate`,
`npm run ios:resources` and the fixes.

`playwright-core`, `sharp` and `onnxruntime-web` are the scraper's optional peers. npm does
not check them here, so [a test](../src/server/scraperDependency.test.ts) does. When a
release asks for other versions than the app pins, the run fails and names them: move
those pins and the scraper in one commit by hand, with `npm run contract:generate`,
`npm run ios:resources` and the app's validation. A new `playwright-core` also moves
`@playwright/test` and the browser journeys' Playwright image in `.github/workflows/ci.yml`.

`SCRAPER_ADOPTION_TOKEN` is a fine-grained token with read and write access to this
repository's contents. It is required to push the commit and start CI. Without it, adoption
stops without pushing: the workflow's own token would suppress the validation workflows.

The scraper's release workflow also publishes versioned HTTP and TRAWL images. TRAWL is
optional and has its own AGPL licence and matching source archives. Browser-service
configuration is documented in the scraper repository.

### Database gate

The database keeps its own copy of parts of the catalog, and a migration is applied
before the server that needs it. So the workflow stops, before pushing anything, when a
release changes one of these:

- the carrier ids;
- the inputs a carrier asks for (postcode, tracking URL): for which carrier, required or
  optional, and their shape;
- a tracking number that is more than letters and digits;
- the number shape that marks a Quickpac parcel;
- the stages;
- the names of the universal providers.

A migration that makes one of these changes declares it, one line per change, in the form
the run's summary gives:

```sql
-- Database gate: carriers added example-post
-- Database gate: inputs added example-post: postcode, validator internationalPostcode, optional
```

A change passes once a migration that declares it is in production's ledger of applied
migrations, which the gate reads with the publishable key, as the deployment does. For one
change, the latest migration that names it decides. So a migration applied before the
release lets the scheduled run adopt it. A line can be added to a migration after it is
applied: it is a comment.

The run's summary lists each change with the constraints and functions that enforce it and
the migration that made it. Only the first run a release stops fails, which notifies; later
runs stop at the same release without failing until the migration is in, and their summary
links to that first run. Write the migration with the lines the summary gives and apply it
to production: the next run adopts the release. To adopt it at once, continue with the
release the run named:

```bash
gh workflow run adopt-scraper.yml -f version=1.2.3 -f database_ready=true
```

`database_ready` vouches for one release, so it is refused without a version.

The gate compares catalog data. Rules written as scraper code, such as URL shapes and the
Mondial Relay barcode checksum, are outside it. So is the rule for a number with no digit:
the scraper's `validTrackingNumber` takes six to ten letters that a detection rule claims
and the database six or more, so a wider rule there needs a migration first. A changed
stage also needs the `Stage` enum in `contracts/openapi.json` edited by hand.

A checksum a detection rule names must also exist in `ios/PeekDeliveryTracker/CarrierCatalog.swift`.
The iPhone app keeps a rule whose checksum it does not know only as a low-confidence candidate
that is never preferred, so its replay of the scraper's detection answers fails after the
adoption. Add the checksum there before the release that names it. The same file holds the
iPhone's copy of how a number is normalized, of the shape a number may have, of the order
recognition asks carriers in and of the labels that introduce one in a message; change them with
the scraper's. The iPhone tests also replay the scraper's checksum vectors, so a Swift checksum
that answers differently from the scraper's fails.

### Stored scans after a release

A check rewrites the scans its source returns, but not a closed parcel's, nor scans a source no
longer returns, and a wall clock dated again gets a new identity, so it is stored beside the old
row rather than over it. After an adoption, a dry run replays production's stored scans through
the release, asking no provider ([storedReplay.ts](../src/server/storedReplay.ts)), each answer's
rows together, a carrier's own or one universal provider's, as the sync read them. A carrier's scan
that took over a universal provider's copy keeps the copy's identity, and replays with its carrier's
rows. A deduced zone's wall clocks are dated again, except on the carrier's home clock, which an
adapter can state itself, and a scan's stage is read again from its source's status map and the
wording rules, a notice stored as the sync stores it.

```bash
node scripts/replay-stored-scans.mjs --query > export.sql   # run read-only on the database, one parcel a line
node scripts/replay-stored-scans.mjs --from <previous version> --sql replay.sql < export.txt
```

`--from` replays the rows with the release the app had before too and keeps the rows it reads as
they are stored: the ones this release changes. The report names rows by their ids' first eight
characters and shows no number, place or wording. `replay.sql` updates those rows, each guarded
on its stored values, in one transaction left open to roll back or commit. A row a fixed bug wrote
is changed as any named-row fix is: a backup first, then a rolled-back run before the commit.
