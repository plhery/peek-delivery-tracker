---
title: Open-source parcel tracking: Peek's code and engine
description: Peek is open source under the AGPL-3.0. Host your own, run it locally, or track parcels from your own code with Universal Parcel Scraper: CLI, Node or HTTP.
slug: open-source-parcel-tracking
picture: Pip, the parcel with a face, perched on an open laptop whose terminal prints a parcel's scans line by line.
published: 2026-11-19
updated: 2026-11-22
---

[Peek](/) is a free, open-source parcel tracker: its web app, API and iPhone and Android apps are on GitHub under the GNU AGPL 3.0, and its carriers are read by a separate engine, Universal Parcel Scraper, free for noncommercial use. Peek has no public API with keys, so to track parcels from your own code, use the scraper, as a command, a Node library or an HTTP server, with no account and no API key. You can also host your own Peek, or run it on your computer with made-up parcels.

To try the scraper from your terminal:

:::steps
- Install it | You need a recent Node.js (the README names the minimum). Run `npm install -g universal-parcel-scraper`, or type `npx universal-parcel-scraper` wherever `parcel-scraper` appears below.
- Name the carrier | `parcel-scraper detect 1Z999AA10123456784` says which carrier a number belongs to, offline.
- Fetch the history | `parcel-scraper track` followed by your number asks the carrier and prints the parcel's history as JSON. If the number fits several carriers, add `--carrier dhl`, for example. Some carriers also want `--postcode`, `--phone-digits` or `--tracking-url`.
:::

## Is Peek open source, and under what licence?

Yes. Peek's code is public at [github.com/plhery/peek-delivery-tracker](https://github.com/plhery/peek-delivery-tracker), under the GNU Affero General Public License 3.0 (AGPL-3.0): the web app and API (Next.js, React, TypeScript), the native SwiftUI app for iPhone, iPad and Mac, and the Android app. A copy or modified version keeps the credit "Based on Peek by Paul-Louis Hery", with its link. [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper), which Peek installs from npm, is under the PolyForm Noncommercial License 1.0.0 from version 0.1294.0, which allows noncommercial use only; earlier versions stay under Apache 2.0. Its optional TRAWL browser image is under AGPL-3.0.

Peek is a non-commercial project with no ads; [universal parcel tracking](guide:universal-tracker) compares it with other trackers.

Bugs and ideas go to Peek's [GitHub issues](https://github.com/plhery/peek-delivery-tracker/issues), which are public: leave out real tracking numbers, postcodes and sign-in codes. Security problems go through GitHub's [private vulnerability report](https://github.com/plhery/peek-delivery-tracker/security/advisories/new). [CONTRIBUTING.md](https://github.com/plhery/peek-delivery-tracker/blob/main/CONTRIBUTING.md) lists the checks to run before a pull request.

## Does Peek have an API I can use?

Not a public one: Peek gives out no API keys. Its API is the app's own, the one the website and the iPhone and Android apps call, signed in with the user's session. It's described in an [OpenAPI file](https://peektracker.com/api/openapi.json), kept in the repository as `contracts/openapi.json`; peektracker.com/api itself answers 404.

The carrier catalog, `GET /api/carriers`, needs no sign-in. Nor does following a parcel without an account, but a new lookup there must first pass a browser or app check. Personal tokens for scripts are off unless the server turns them on, and "Settings" has no screen that makes one.

From outside, the only way in is an assistant's sign-in. Claude, ChatGPT, Gemini or another assistant signs in to your account with standard OAuth and uses Peek's MCP server, `https://peektracker.com/mcp`, within what you allowed. The same token can read your parcels as JSON at `GET /api/parcels`, and Peek's Home Assistant integration signs in the same way. See [the guide to assistants](guide:ai-assistants), [Peek's page for assistants](https://peektracker.com/assistants) and [the Home Assistant guide](guide:home-assistant).

## How do I track parcels from my own code without an API key?

Use Universal Parcel Scraper, the engine that reads carriers for Peek. It finds the carrier, reads that carrier's own site, falls back on universal trackers only where it has to, and returns the same JSON for every carrier, with each scan filed under a common delivery stage.

Its README counts more than 4,500 carriers, through more than 800 dedicated adapters and universal fallbacks. In its benchmark of 100 carriers picked for shoppers in Europe, the adapters return a history for 92, or 95 with every fallback, against 73 for the best single aggregator.

Besides the command line, it runs in three ways:

| Where | How |
| --- | --- |
| Node | Import `createTracker` from `universal-parcel-scraper/node`; `tracker.track({ number })` returns the carrier, who answered, the scans, the current stage and what the carrier shows, such as the expected delivery |
| A browser | Carrier detection only, with `parseTrackingInput`: no Node imports, no requests |
| HTTP | `parcel-scraper serve --port 8080`, or the Docker image `ghcr.io/plhery/universal-parcel-scraper`, tagged by release version only; then POST to `/v1/track`. The server caches answers in memory and rate-limits callers; it has no database and never polls |

Before you build on it:

- **It answers one lookup at a time.** Storing parcels and checking them again are up to you: that's the part Peek adds.
- **Some carrier sites only answer a browser.** For those, it needs a Chromium it drives through optional packages, or its TRAWL browser service. The Docker image ships Chromium.
- **Fallbacks are opt-in.** Only UPU is on by default; ParcelsApp, Ship24, 17TRACK, Postal Ninja and Track718 wait until you enable them, and each one you enable receives the tracking number.
- **A lookup sends the number to the carrier**, with the postcode, link or phone digits when the carrier asks. There's no telemetry.

## Can I host my own Peek?

Yes. A self-hosted Peek is one long-running container, holding the web app, the API and the worker that keeps checking carriers, next to Supabase for sign-in and Postgres, behind HTTPS. You need:

- **a Supabase stack** (Auth, PostgREST and Postgres) with a tested backup and restore;
- **SMTP**, if email sign-in or the delivery email is on;
- **a host that can build the Dockerfile and keep the container running.** Serverless hosting won't work: the worker must stay alive.

Web push, iPhone notifications (an Apple team and an APNs key) and Android notifications (Firebase) are optional. So are assistants: without `ASSISTANT_ACCOUNTS`, `/mcp` and `/oauth` aren't there. It lists the accounts that may connect, or `*` for everyone, and needs `CANONICAL_ORIGIN`. With it, `PERSONAL_TOKENS=on` turns on personal tokens for your scripts, made through the API. [docs/DEPLOYMENT.md](https://github.com/plhery/peek-delivery-tracker/blob/main/docs/DEPLOYMENT.md) has the steps, from the database and sign-in to upgrades, backups and moving hosts.

### Run it on your computer first

No account, no database and no `.env` file needed:

:::steps
- Get the code | Clone `https://github.com/plhery/peek-delivery-tracker.git`. You need nvm and the Node.js version the README names.
- Install | In its folder, run `nvm use`, then `npm ci`.
- Start | Run `npm run dev` and open localhost:3000: Peek answers in the browser with made-up parcels. localhost:3000/demo lists sample deliveries that move along when you refresh.
:::

For real accounts and tracking, copy `.env.example` to `.env.local`, fill in a Supabase project and set `NEXT_PUBLIC_USE_API=true`; [docs/AUTHENTICATION.md](https://github.com/plhery/peek-delivery-tracker/blob/main/docs/AUTHENTICATION.md) has the sign-in steps. The iPhone app builds in Xcode for an iPhone simulator and starts with sample parcels, with no account, server or Apple team.

## A carrier is missing: how do I report or add it?

Report it to the scraper, where carriers live. On its [new issue page](https://github.com/plhery/universal-parcel-scraper/issues/new/choose), linked from Peek's README as "Tell the scraper", choose "Add a carrier" (its name, home country and public tracking website) or "A carrier stopped working". New carriers and fixes go to the scraper as pull requests: each carrier has its own folder with synthetic fixtures and offline tests, and `npm run carrier:new` scaffolds one.

Peek adopts new scraper releases by itself, so a carrier needs no pull request to Peek, and the iPhone app refreshes its carrier list from the server, so no app release either.

No GitHub account? Tell Peek from the parcel's page, on the web, iPhone or Android, with or without a Peek account:

:::steps
- Answer the question | When Peek finds no carrier for a parcel, its page asks "Does the carrier’s own site show it?". Choose "Yes".
- Name the carrier | In "Who’s carrying it?", name it under "Carrier or shop", or paste its tracking link under "Its tracking page, if you have the link". One of the two is enough.
- Send it | Select "Send". It goes with the parcel's number and nothing about you.
:::

A person reads these answers, but nobody can write back: for a reply, write to hello@peektracker.com, the address on [Peek's help page](https://peektracker.com/support.html). How Peek names a carrier from what you paste is in [Track a parcel without knowing the carrier](guide:track-without-carrier).

:::sources
- [Peek on GitHub: README](https://github.com/plhery/peek-delivery-tracker) – what the repository holds, carriers in the scraper, run it locally, host your own, the iPhone build
- [Peek on GitHub: LICENSE](https://github.com/plhery/peek-delivery-tracker/blob/main/LICENSE) – GNU Affero General Public License 3.0
- [Universal Parcel Scraper on GitHub](https://github.com/plhery/universal-parcel-scraper) – commands, options, Node, browser, HTTP and Docker, Chromium in the image, fallbacks, privacy, carrier count, benchmark, licences, adding a carrier
- [Universal Parcel Scraper: new issue](https://github.com/plhery/universal-parcel-scraper/issues/new/choose) – the "Add a carrier" and "A carrier stopped working" forms
- [Universal Parcel Scraper on npm](https://www.npmjs.com/package/universal-parcel-scraper) – the package and its Node.js requirement
- [Peek: OpenAPI file](https://peektracker.com/api/openapi.json) – the app's own API, the routes that need no sign-in and their checks, reading parcels with an assistant's token
- [Peek on GitHub: Architecture](https://github.com/plhery/peek-delivery-tracker/blob/main/docs/ARCHITECTURE.md) – the assistants' and Home Assistant's sign-in, GET /api/parcels, personal tokens off
- [Peek on GitHub: assistants' settings](https://github.com/plhery/peek-delivery-tracker/blob/main/src/server/assistants/settings.ts) – personal tokens off unless PERSONAL_TOKENS is on, ASSISTANT_ACCOUNTS
- [Peek: Connect Peek to Claude, ChatGPT or Gemini](https://peektracker.com/assistants) – the way in for assistants
- [Peek on GitHub: Deployment](https://github.com/plhery/peek-delivery-tracker/blob/main/docs/DEPLOYMENT.md) – what self-hosting needs, optional parts, assistants and personal tokens on your own instance
- [Peek on GitHub: Contributing](https://github.com/plhery/peek-delivery-tracker/blob/main/CONTRIBUTING.md) – checks before a pull request, carriers go to the scraper, releases adopted by themselves
- [Peek on GitHub: parcel feedback](https://github.com/plhery/peek-delivery-tracker/blob/main/src/peek/parcel/Feedback.tsx) – "Who’s carrying it?" when no carrier is found, either field enough to send
- [Peek: Help](https://peektracker.com/support.html) – reporting a carrier without GitHub, public issues, security reports, contact
- [Peek: Terms of use](https://peektracker.com/terms.html) – a free, non-commercial project with no advertising
:::
