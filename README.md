<div align="center">

<img src="public/icons/icon-192.png" width="88" alt="Peek's mark: two eyes on a yellow tile">

# Peek

**Universal parcel tracker for iPhone and the web. Open source.**

Paste a tracking number, a carrier link or a whole shipping email.<br>
Peek finds the carrier, draws the journey, notifies you when the parcel moves.

<sub>The carriers are read by our engine, <a href="https://github.com/plhery/universal-parcel-scraper">Universal Parcel Scraper</a>.</sub>

[![CI](https://github.com/plhery/peek-delivery-tracker/actions/workflows/ci.yml/badge.svg)](https://github.com/plhery/peek-delivery-tracker/actions/workflows/ci.yml)
[![License: AGPL-3.0](https://img.shields.io/badge/license-AGPL--3.0-blue.svg)](LICENSE)

[Try it: peektracker.com](https://peektracker.com) · [Run it locally](#run-it-locally) · [iPhone app](#iphone-app) · [Run your own](#run-your-own)

<picture>
  <source media="(prefers-color-scheme: dark)" srcset="docs/assets/hero-dark.webp">
  <img src="docs/assets/hero-light.webp" width="860" alt="Peek in a browser and on an iPhone. The browser lists deliveries as coloured cards, the next one drawn on a map of its route. The iPhone asks “Where's my parcel?” over a field to paste a tracking number, under a globe that follows a sample parcel from Shenzhen to Zürich.">
</picture>

</div>

## What you get

**Where's my parcel?**<br>
Paste what you have. Peek works out which of 4,500+ carriers has the parcel and gives it a
page of its own: the journey on a map, every scan, the day it should arrive. No account
needed.

**Will I know when it moves?**<br>
Yes. Choose every scan, the important steps or delivery only, on your iPhone or in
your browser. Quiet hours keep the night quiet, and an email can tell you it arrived.

**Following more than one?**<br>
Sign in and they share one list, on the web and on the iPhone. Every delivery counts in your
passport and some earn a stamp; compare passports with friends while your parcels stay private.

**Someone else waiting for it?**<br>
Share the parcel's page with them. Wrap it as a gift, and what's inside stays a surprise
until it arrives.

<p align="center">
  <picture>
    <source media="(prefers-color-scheme: dark)" srcset="docs/assets/screens-dark.webp">
    <img src="docs/assets/screens-light.webp" width="860" alt="Four screens: a parcel's page with its route and tracking history, a globe showing a journey from Kyoto to Zürich, a share sheet wrapping a parcel as a gift, and a passport of stamps earned by deliveries.">
  </picture>
</p>

<p align="center"><sub>A parcel's page · its journey · shared as a gift · the passport. Every parcel in these pictures is made up.</sub></p>

Also in the box: light and dark themes, ten languages (English, German, French, Italian,
Spanish, Portuguese, Brazilian Portuguese, Polish, Russian and Simplified Chinese), a web app you can install, and on iPhone a barcode
scanner, a Share extension and widgets. Times, distances, weights and parcel sizes follow your device's own settings,
a 12- or 24-hour clock, kilometres or miles, and kilograms and centimetres or (in US units) pounds and inches;
**Time format**, **Distance** and **Weight** in **Settings → Preferences** choose them yourself, sizes
going in inches with pounds.

Peek shows no ads and sells no data. A parcel followed without an account is forgotten 30
days after it arrives. The [privacy notice](PRIVACY.md) has the rest.

Stuck? [Help](SUPPORT.md) says what to check and where to write.

## Carriers live next door

Reading carriers is a job of its own, so it has a repository of its own:
**[Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper)**. It
works out which carrier a number belongs to, reads that carrier's own site, falls back on
universal trackers such as ParcelsApp, Ship24 and 17TRACK only where it has to, and turns
everyone's wording into the same delivery stages.

Peek installs it from npm and builds the rest around it: accounts, checks in the
background, notifications and the two apps.

- A carrier is missing, or tracks wrongly?
  [Tell the scraper](https://github.com/plhery/universal-parcel-scraper/issues/new/choose).
- Want tracking without Peek? The scraper runs on its own, as a command-line tool, a Node
  library or a small HTTP server.
- Curious how the two fit together? See [the scraper package](docs/SCRAPER.md).

## Run it locally

You need Node.js 26. No account, no database, no `.env` file.

```bash
git clone https://github.com/plhery/peek-delivery-tracker.git
cd peek-delivery-tracker
nvm use
npm ci
npm run dev
```

Open [localhost:3000](http://localhost:3000) and paste anything: with no server behind it,
Peek answers in the browser with made-up parcels.

- Tap Pip, the parcel with a face, to open a sample parcel.
- [localhost:3000/demo](http://localhost:3000/demo) is a whole list of sample deliveries.
  Refresh moves them along, and **Reset demo data** in settings starts over.
- [localhost:3000/design/map](http://localhost:3000/design/map) is a study of three ways
  to draw a journey, from the globe to a close-up.

For real accounts and real tracking, copy [`.env.example`](.env.example) to `.env.local`,
fill in your Supabase project and set `NEXT_PUBLIC_USE_API=true`.
[Authentication](docs/AUTHENTICATION.md) has the steps.

## iPhone app

A native SwiftUI app for iOS 18 and later, [on the App Store](https://apps.apple.com/app/apple-store/id6823615370?pt=129628155&ct=github-readme&mt=8).
To build it yourself, open [`ios/PeekDeliveryTracker.xcodeproj`](ios/PeekDeliveryTracker.xcodeproj)
in Xcode 27 or newer, pick the `PeekDeliveryTracker` scheme and an iPhone simulator, and
press Run. It starts with sample parcels, so it needs no account, no server and no Apple
team.

The [iPhone guide](ios/README.md) covers connecting it to a server, signing it for a real
phone, notifications and widgets.

The Android beta, built without Google's libraries and signed with Peek's own key, is in [Peek's F-Droid repository](https://raw.githubusercontent.com/plhery/peek-delivery-tracker/fdroid/repo?fingerprint=2BA268AF2C76EFB2C262DA7CA8499284B7CC7D0F76DCBE47A8ABA5703067F1A7) and on [GitHub Releases](https://github.com/plhery/peek-delivery-tracker/releases).
To add the repository, open the F-Droid app, go to Settings → Repositories → +, and paste
the whole address, `?fingerprint=…` included:

```
https://raw.githubusercontent.com/plhery/peek-delivery-tracker/fdroid/repo?fingerprint=2BA268AF2C76EFB2C262DA7CA8499284B7CC7D0F76DCBE47A8ABA5703067F1A7
```

Opened in a browser, the address shows a 404 page; that's expected.

## Run your own

Peek is one long-running container: the web app, the API and the worker that keeps
checking carriers. Next to it, it needs Supabase for sign-in and Postgres. This is the
short path; [Deployment](docs/DEPLOYMENT.md) has every step in full.

**You need**

- a host that keeps a Docker container running (serverless won't do: the worker must stay up);
- a Supabase stack, hosted or your own: Auth, PostgREST and Postgres 16 or newer;
- HTTPS in front of the container's port 3000;
- SMTP with an authenticated domain, for sign-in by email.

**The scraper.** Peek reads carriers through
[Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper), which `npm ci`
installs. Its licence,
[PolyForm Noncommercial 1.0.0](https://github.com/plhery/universal-parcel-scraper/blob/main/LICENSE),
allows noncommercial use only, so a Peek of your own may only run for noncommercial purposes,
as that licence defines them. Its carrier catalog and tracking-number patterns, which the apps
bundle (`CarrierCatalog.json` and `Reading.json`), are licensed under
[AGPL-3.0-only](https://github.com/plhery/universal-parcel-scraper/blob/main/NOTICE), like Peek.

**The database.** Apply every file in [`supabase/migrations`](supabase/migrations) in
filename order, each in its own transaction, stopping at the first error. To try them
first, point `TEST_DATABASE_URL` at a throwaway Postgres 16 and run
`scripts/test-migrations.sh`: it applies them all and runs the access tests.

**Sign-in.** In Supabase Auth, set the Site URL to your origin, turn on at least one
sign-in method and point the magic-link and confirmation emails at
`/auth-emails/magic-link.html`. [Authentication](docs/AUTHENTICATION.md) has Google, Apple,
email and OpenID Connect.

**The env.** Copy [`.env.example`](.env.example) to `.env` and delete the services you
don't use. What every server needs:

- `NEXT_PUBLIC_SUPABASE_URL` and `NEXT_PUBLIC_SUPABASE_PUBLISHABLE_KEY`, at build time and
  at runtime, with the same values;
- `SUPABASE_URL`, `SUPABASE_PUBLISHABLE_KEY` and `SUPABASE_SERVICE_ROLE_KEY`, at runtime
  only;
- `CANONICAL_ORIGIN`, the address people reach the site at;
- `TRUST_PROXY_HEADERS=true`, but only behind a proxy that overwrites the client's address
  headers.

**First run.**

```bash
docker build \
  --build-arg NEXT_PUBLIC_SUPABASE_URL=https://supabase.example.com \
  --build-arg NEXT_PUBLIC_SUPABASE_PUBLISHABLE_KEY=sb_publishable_example \
  -t peek .
docker run -d --name peek --restart unless-stopped --env-file .env -p 3000:3000 peek
```

The image turns on the API and email sign-in by default; `NEXT_PUBLIC_AUTH_*` build
arguments change the sign-in methods. `GET /health` answers 200 once the database answers
and the worker runs, and `scripts/smoke-url.sh https://your-host` checks the site from
outside.

**Optional.** Notifications (Web Push, APNs, Firebase), the delivery email, adding
parcels by email, assistants (MCP), usage analytics (Umami), Sentry, Turnstile, and
FlareSolverr and tracking proxies for the carriers whose sites turn servers away.
Each one is off until its settings are there; `.env.example` lists them.

**Off by default.** A copy starts with no App Store or Android app, no author links and
none of Peek's own pages, and where the code names the site it says "Parcel Tracker".
Name yours with `SITE_NAME`, add your apps with `APP_STORE_ID` and `ANDROID_APP_PACKAGE`,
and link your own privacy notice with `PRIVACY_URL`. Peek's privacy notice, terms, help
and accessibility pages (`LEGAL_PAGES`) name Peek's operator, and its guides and carrier
pages (`GUIDE_PAGES`) are not licensed with the code, so a copy keeps both off and nothing
links them ([Trademarks and content](#trademarks-and-content)). [Deployment](docs/DEPLOYMENT.md)
describes each setting.

**Rename before you publish.** The app's words (`shared/locales`), the sign-in email
(`public/auth-emails/`) and the logo and icons (`public/icons/`) still say Peek. A fork
replaces them before it is published or run as a public service.

## Under the hood

Next.js, React and TypeScript for the web and the API, SwiftUI for the iPhone. Supabase
Auth and Postgres row-level security keep accounts apart, and a queue in the database runs
the carrier checks and the notifications.

- [Architecture](docs/ARCHITECTURE.md): how the pieces fit, and who can see what
- [Scraper package](docs/SCRAPER.md): what Peek takes from the scraper, and how it updates
- [Routing](docs/ROUTING.md): where a refresh looks for a parcel's history
- [Deployment](docs/DEPLOYMENT.md): hosting, upgrades, operations
- [Authentication](docs/AUTHENTICATION.md): Google, Apple, email and custom OIDC sign-in
- [Observability](docs/OBSERVABILITY.md): logs, audit tables, Sentry, metrics
- [Friends](docs/FRIENDS.md): how passports are shared, and what never is
- [Analytics](docs/ANALYTICS.md): optional usage analytics with Umami
- [Localization](docs/LOCALIZATION.md): which language carrier text shows up in
- [iPhone](ios/README.md): the native app

## Contributing

Fixes and ideas are welcome. [Contributing](CONTRIBUTING.md) has the setup and the checks
to run before a pull request. Carrier fixes go to the
[scraper](https://github.com/plhery/universal-parcel-scraper/blob/main/CONTRIBUTING.md).
Found a security problem? [Report it privately](SECURITY.md).

## Trademarks and content

The code is licensed under the [GNU Affero General Public License, version 3 only](LICENSE),
with one additional term under its section 7(b) ([NOTICE](NOTICE)): copies and modified
versions keep the credit "Based on Peek by Paul-Louis Hery", with a link to
<https://github.com/plhery/peek-delivery-tracker>, among the legal notices they show, such as
the website's footer or an app's About screen. Once, where a user can find it, is enough.

The name "Peek", its logo and app icon, the App Store and Google Play assets, and the guides
and carrier pages (the texts under `content/`) are not licensed: Paul-Louis Hery keeps all rights to them. A copy
or fork may not publish them or use the name "Peek", and a fork must rename itself before
it is published or run as a public service. [Trademarks and content](TRADEMARKS.md) has
the details.

Made by [@plhery](https://x.com/plhery).
