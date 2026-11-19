# Project instructions

- This is an open-source project. Repository files, code comments, commit messages, and pull request descriptions are publicly readable. What a public source shows may go in them with the source's URL; public tracking numbers belong in the scraper's corpus. Everything from production stays out of them: users' tracking numbers, postcodes, links, names and email addresses, database rows, logs and secrets. Sentry, Loki and the other debugging tools may receive production data. Use public or synthetic fixtures and supply private live-test inputs outside the repository.
- After completing a requested change in this repository, stage the files for that change, commit them, and push the commit directly to `main` without asking for separate permission. Once the checks pass, push; don't stop to show the result or wait for a review first, even for large content changes, and report what went out afterwards. Hold back only when the user asks to see the change before it goes live.
- Do not create a feature branch or pull request unless the user explicitly asks for one.
- When a change needs a database migration, apply it to production yourself without asking for separate permission: dry-run it in a transaction that is rolled back, apply it in one transaction, both starting with `set local lock_timeout = '4s';` after `begin` (on failure, run it again a little later; see [docs/DEPLOYMENT.md](docs/DEPLOYMENT.md)), and push the code that depends on it only once it is applied. One that must follow its server starts with `-- After its server.` instead: push, then apply it once the commit is live ([docs/DEPLOYMENT.md](docs/DEPLOYMENT.md)). A scraper release that the adoption's database gate stops needs one too: write it with the gate lines the run's summary gives (a migration written ahead of the release declares them too), apply it, and the next adoption takes the release, or start it again with `database_ready=true` ([docs/SCRAPER.md](docs/SCRAPER.md)). Never leave an adoption blocked.
- Before pushing, run validation appropriate to the changed areas and confirm that no secrets or unrelated generated artifacts are included.
- Carrier behaviour (catalog, detection, adapters, status wording, provider order, places) lives in [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper), an exact npm dependency. Change it there and release; the app adopts the newest release once in each 2-hour slot from 06:07 to 22:07 UTC, started by a timer on the netcup production server (GitHub's schedule and CI runs on main are only a backup), not each release on its own. Adopt an urgent one with `gh workflow run adopt-scraper.yml` (`-f version=<version>` for an exact one) ([docs/SCRAPER.md](docs/SCRAPER.md)). Import only its published entry points.

## Documentation

- Keep docs short, plain and current. Describe how things work now: no dates, verification logs, changelogs, one-off measurements ("22 scans in 14.6 s") or hedging. Git history holds the history.
- Don't copy what a machine-readable source already holds: `contracts/openapi.json` (API, carrier catalog), `shared/analytics.json` (events), `shared/locales` (copy), the scraper's catalog and coverage data. No status tables or field tables in READMEs.
- Carrier adapters and provider documentation belong in [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper). App documentation covers routing, persistence and client behaviour.
- Keep one topic in one place: routing in `docs/ROUTING.md`, logs/audit/Sentry/metrics in `docs/OBSERVABILITY.md`, provider results in `https://github.com/plhery/universal-parcel-scraper/blob/main/providers/COVERAGE.md`. Link to files rather than section anchors.
- Update the docs in the same commit as the behaviour they describe. When a migration needs a rollout order, put it in the commit message, not the docs.

## Guides

The guides and carrier pages (`content/guides/`, `content/carriers/`) are proofread when the user asks, every few weeks; don't raise these points between proofreads. Besides the text, check the facts that change with time against their sources, in every language:

- Christmas posting dates (`christmas-posting-dates`): replace last year's dates with this year's as each carrier publishes them, and say so in the opening paragraph.
- The EU handling fee of €2 per item on online orders from outside the EU, due from 1 November 2026 (`customs`): once that day has passed, check that it applies, put it in the present tense and say how each post or carrier passes it on to the recipient.
- Any other "not announced yet", "from <date>" or "until <date>" whose date has passed.

<!-- BEGIN:nextjs-agent-rules -->

## This is NOT the Next.js you know

This version has breaking changes — APIs, conventions, and file structure may all differ from your training data. Read the relevant guide in `node_modules/next/dist/docs/` (resolved from this file's directory; in monorepos the `next` package may not be visible from the repo root) before writing any code. Heed deprecation notices.

This block is written and re-added by `next dev` — verify at `node_modules/next/dist/server/lib/generate-agent-files.js`. Removing it from a diff only re-creates the uncommitted change; committing it with your work keeps the tree clean.

<!-- END:nextjs-agent-rules -->
