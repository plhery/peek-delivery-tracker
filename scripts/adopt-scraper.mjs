// Adopts a Universal Parcel Scraper release, for .github/workflows/adopt-scraper.yml:
// which version to move to, and what that version changes among the things the
// database holds its own copy of. Every command also runs locally; the gate asks
// production's ledger of migrations through SUPABASE_URL and SUPABASE_PUBLISHABLE_KEY.
//
//   node scripts/adopt-scraper.mjs due                    whether this run is its slot's adoption (reads package.json and npm)
//   node scripts/adopt-scraper.mjs resolve [--version=1.2.3] [--database-ready]
//   node scripts/adopt-scraper.mjs snapshot <file>        before installing the release
//   node scripts/adopt-scraper.mjs gate <file> [--database-ready]   exits 3 when the release waits for a migration
//   node scripts/adopt-scraper.mjs peers
//   node scripts/adopt-scraper.mjs holds <package.json> --version=1.2.3   prints whether it pins that release or a newer one
import { appendFile, readFile, readdir, writeFile } from 'node:fs/promises';
import path from 'node:path';
import { setTimeout as delay } from 'node:timers/promises';
import { fileURLToPath } from 'node:url';
import { parseArgs } from 'node:util';

export const PACKAGE_NAME = 'universal-parcel-scraper';

const root = path.resolve(path.dirname(fileURLToPath(import.meta.url)), '..');
const registryUrl = `https://registry.npmjs.org/${PACKAGE_NAME}`;

// ---------------------------------------------------------------------------
// Versions
// ---------------------------------------------------------------------------

// A stable release is X.Y.Z. A prerelease of the scraper's main branch is X.Y.Z-main.N.
const versionPattern = /^(0|[1-9]\d*)\.(0|[1-9]\d*)\.(0|[1-9]\d*)(?:-main\.(0|[1-9]\d*))?$/;

export function parseVersion(value) {
  const match = typeof value === 'string' ? versionPattern.exec(value) : null;
  const numbers = match?.slice(1).map((part) => (part === undefined ? null : Number(part)));
  if (!numbers || numbers.some((number) => number !== null && !Number.isSafeInteger(number))) {
    throw new TypeError(`Not a scraper release version: ${JSON.stringify(value)?.slice(0, 80)}`);
  }
  const [major, minor, patch, prerelease] = numbers;
  return { major, minor, patch, prerelease };
}

/** Semver precedence: a prerelease comes before the release it leads to. */
export function compareVersions(first, second) {
  const a = parseVersion(first);
  const b = parseVersion(second);
  for (const part of ['major', 'minor', 'patch']) {
    if (a[part] !== b[part]) return a[part] < b[part] ? -1 : 1;
  }
  if (a.prerelease === b.prerelease) return 0;
  if (a.prerelease === null) return 1;
  if (b.prerelease === null) return -1;
  return a.prerelease < b.prerelease ? -1 : 1;
}

/** The exact release package.json pins. */
export function pinnedVersion(manifest) {
  const pinned = manifest.dependencies?.[PACKAGE_NAME];
  try {
    parseVersion(pinned);
  } catch {
    throw new Error(`package.json must pin ${PACKAGE_NAME} to an exact release, not ${JSON.stringify(pinned)}`);
  }
  return pinned;
}

/**
 * The release to move to: the requested one, otherwise the newer of npm's
 * `latest` and `next`. `adopt` is false when the app is already on it or newer.
 * `databaseReady` vouches for the migration of one release, so it needs the request.
 */
export function chooseTarget({ current, requested = '', distTags = {}, databaseReady = false }) {
  parseVersion(current);
  if (databaseReady && !requested) {
    throw new Error('database_ready confirms the migration of one release: pass -f version=<version> with it.');
  }
  let version = requested;
  if (requested) {
    parseVersion(requested);
  } else {
    const published = ['latest', 'next'].filter((tag) => distTags[tag] !== undefined).map((tag) => {
      try {
        parseVersion(distTags[tag]);
      } catch {
        throw new Error(`npm tags ${JSON.stringify(distTags[tag])} as ${tag}, which is not a scraper release version`);
      }
      return distTags[tag];
    });
    if (published.length === 0) throw new Error(`npm has no latest or next ${PACKAGE_NAME} release`);
    version = published.reduce((newest, candidate) => (compareVersions(candidate, newest) > 0 ? candidate : newest));
  }
  return { version, adopt: compareVersions(version, current) > 0 };
}

/** Whether a package.json already pins `version` or a newer release, as main does after another run adopted it. */
export function holdsRelease(manifest, version) {
  return compareVersions(pinnedVersion(manifest), version) >= 0;
}

// ---------------------------------------------------------------------------
// Slots
// ---------------------------------------------------------------------------

const MINUTE = 60_000;
// A slot starts 7 past every even hour from 06:07 to 22:07 UTC, and the last
// one lasts until the first of the next day.
const FIRST_SLOT = 6 * 60 + 7;
const LAST_SLOT = 22 * 60 + 7;
const SLOT_LENGTH = 120;

/** When the slot `now` falls in started. */
export function adoptionSlot(now) {
  const midnight = Date.UTC(now.getUTCFullYear(), now.getUTCMonth(), now.getUTCDate());
  const minute = Math.floor((now.getTime() - midnight) / MINUTE);
  if (minute < FIRST_SLOT) return new Date(midnight - (24 * 60 - LAST_SLOT) * MINUTE);
  const slot = FIRST_SLOT + Math.floor((minute - FIRST_SLOT) / SLOT_LENGTH) * SLOT_LENGTH;
  return new Date(midnight + Math.min(slot, LAST_SLOT) * MINUTE);
}

/**
 * The title the workflow's `run-name` gives a run dispatched by hand, without the
 * `slot` input the server's timer sets. Every other run has the workflow's name.
 */
export const BY_HAND_TITLE = 'Adopt scraper release by hand';

/**
 * The run that adopted in this slot, if any: its Push job, which runs only for a
 * newer release that passed the app's tests and the database gate, started once
 * the slot had begun and pushed or is pushing. A run that found nothing newer,
 * or whose release failed, leaves the slot to a later run, so a release
 * published since does not wait for the next slot. So does a run by hand: an
 * urgent release does not hold back the ones after it. `runs` are the
 * workflow's runs, each with its `display_title` and `jobs`.
 */
export function slotAdoption(slot, runs) {
  return runs.find((run) => run.display_title !== BY_HAND_TITLE && run.jobs.some((job) => job.name === 'Push'
    && (job.status === 'in_progress' || (job.status === 'completed' && job.conclusion === 'success'))
    && Date.parse(job.started_at) >= slot.getTime()));
}

/** The artifact by which a run's Adopt job names the release it tries. */
export function releaseArtifact(version) {
  return `release-${version}`;
}

/** The Adopt job's steps that run the release: one failing is the release's fault, not the runner's. */
export const RELEASE_STEPS = new Set(['Install the release', "Check the scraper's optional peers",
  'Regenerate the contract and the iPhone resources', 'Write the adoption as a patch', 'Test the app with the release']);

/**
 * Since when a release that failed waits: the slot's start, or a slot's length ago in the night's longer slot. A CI run
 * on main tries a release that failed at 22:07 again from 00:07, once the app may have been fixed, not only at 06:07.
 */
export function failureHeldSince(slot, now) {
  return new Date(Math.max(slot.getTime(), now.getTime() - SLOT_LENGTH * MINUTE));
}

/** Whether a run's Adopt job, started since `since`, failed at a step that runs its release. */
export function releaseFailed(since, run) {
  return run.jobs.some((job) => job.name === 'Adopt' && job.status === 'completed' && job.conclusion === 'failure'
    && Date.parse(job.started_at) >= since.getTime()
    && (job.steps ?? []).some((step) => step.conclusion === 'failure' && RELEASE_STEPS.has(step.name)));
}

/**
 * The run since `since` (failureHeldSince) whose release `version` failed, such
 * as the app's tests with it: that release waits for the next slot, or two
 * hours in the night's, instead of failing every later run of this one, while a
 * newer release still adopts at once. A run by hand counts too: the release
 * fails the same way whoever started the run. `runs` carry their `jobs` and
 * `artifacts`.
 */
export function slotFailure(since, version, runs) {
  return runs.find((run) => releaseFailed(since, run)
    && (run.artifacts ?? []).some((artifact) => artifact.name === releaseArtifact(version)));
}

// ---------------------------------------------------------------------------
// What the database enforces
// ---------------------------------------------------------------------------

/** The constraints and functions in supabase/migrations that copy each facet. */
export const ENFORCED_BY = {
  carriers: ['packages_carrier_check', 'create_owned_package', 'change_owned_package_carrier'],
  inputs: [
    'packages_dpd_postcode_check', 'packages_tracking_url_check',
    'create_owned_package', 'change_owned_package_carrier',
  ],
  numberShapes: ['packages_tracking_number_format_check', 'create_owned_package', 'create_one_off_parcel'],
  forcedCarriers: ['change_owned_package_carrier'],
  stages: [
    'tracking_events_stage_check', 'packages_current_stage_check', 'notification_preferences_stages_check',
    'set_owned_notification_preferences',
  ],
  providers: ['tracking_provider_health_provider_check'],
};

const facetTitles = {
  carriers: 'Carrier ids',
  inputs: 'Inputs a carrier asks for',
  numberShapes: 'Tracking numbers that are not plain letters and digits',
  forcedCarriers: 'Tracking numbers that always belong to one carrier',
  stages: 'Stages',
  providers: 'Universal providers',
};

const facetNotes = {
  forcedCarriers: 'The app copies this shape too, in `src/server/validation.ts`.',
  stages: 'A stage the app notifies about also joins the default of `notification_preferences.enabled_stages` '
    + 'and raises the limit on how many stages an account enables. '
    + 'A stage is also part of the API: edit the `Stage` enum in `contracts/openapi.json` by hand.',
};

// Carriers the database recognises by number: it moves a matching parcel to them.
const carriersForcedByNumber = ['quickpac'];

/**
 * Whether a detection pattern can only match the uppercase letters and digits
 * the database stores. A pattern this does not understand counts as wider.
 */
export function matchesOnlyLettersAndDigits(pattern) {
  const groups = []; // for each open group, whether it is a lookahead
  let inClass = false;
  for (let index = 0; index < pattern.length; index += 1) {
    const character = pattern[index];
    const consumes = !groups.includes(true); // a lookahead consumes nothing
    if (character === '\\') {
      index += 1;
      if (consumes && pattern[index] !== 'd') return false;
    } else if (inClass) {
      if (character === ']') inClass = false;
      else if (consumes && !/[A-Z0-9-]/.test(character)) return false;
    } else if (character === '[') {
      inClass = true;
      if (consumes && pattern[index + 1] === '^') return false;
    } else if (character === '(') {
      const opener = pattern.slice(index, index + 3);
      if (opener.startsWith('(?') && !['(?:', '(?=', '(?!'].includes(opener)) return false;
      groups.push(opener === '(?=' || opener === '(?!');
      if (opener.startsWith('(?')) index += 2;
    } else if (character === ')') {
      groups.pop();
    } else if (character === '{') {
      const end = pattern.indexOf('}', index);
      if (!/^\{\d+(?:,\d*)?\}$/.test(pattern.slice(index, end + 1))) return false;
      index = end;
    } else if (consumes && !/[A-Z0-9|?*+^$]/.test(character)) {
      return false;
    }
  }
  return true;
}

function inputFacet(carrier, requirement) {
  return [
    `${carrier}: ${requirement.field}`,
    `validator ${requirement.validator}`,
    requirement.optional ? 'optional' : 'required',
    ...(requirement.whenTrackingNumber ? [`for numbers matching ${requirement.whenTrackingNumber}`] : []),
    ...(requirement.pattern ? [`pattern ${requirement.pattern}`] : []),
  ].join(', ');
}

/**
 * The part of a release the database copies, as sorted lists of plain strings.
 * Names, colors, links, refresh timing and ordinary detection rules are left
 * out: the database does not know them.
 */
export function databaseFacets({ catalog, stages, providers }) {
  const carriers = Object.keys(catalog).sort();
  const inputs = [];
  const numberShapes = [];
  const forcedCarriers = [];
  for (const carrier of carriers) {
    for (const requirement of catalog[carrier].tracking?.requirements ?? []) {
      inputs.push(inputFacet(carrier, requirement));
    }
    for (const rule of catalog[carrier].detectionRules ?? []) {
      if (!matchesOnlyLettersAndDigits(rule.pattern)) numberShapes.push(`${carrier}: ${rule.pattern}`);
      if (carriersForcedByNumber.includes(carrier) && rule.confidence === 'high') {
        forcedCarriers.push(`${carrier}: ${rule.pattern}`);
      }
    }
  }
  return {
    carriers,
    inputs: inputs.sort(),
    numberShapes: numberShapes.sort(),
    forcedCarriers: forcedCarriers.sort(),
    stages: [...stages].sort(),
    providers: [...providers].sort(),
  };
}

/** The facets that differ, with what each gained and lost. */
export function diffDatabaseFacets(before, after) {
  return Object.keys(ENFORCED_BY).flatMap((facet) => {
    const was = new Set(before[facet]);
    const is = new Set(after[facet]);
    const added = [...is].filter((entry) => !was.has(entry));
    const removed = [...was].filter((entry) => !is.has(entry));
    return added.length > 0 || removed.length > 0 ? [{ facet, added, removed }] : [];
  });
}

export function continueCommand(version) {
  return `gh workflow run adopt-scraper.yml -f version=${version} -f database_ready=true`;
}

// ---------------------------------------------------------------------------
// Migrations that already made a change
// ---------------------------------------------------------------------------

const declarationPrefix = '-- Database gate: ';
const declarationPattern = /^-- Database gate: (\S+) (added|removed) (\S.*)$/;

/** The line a migration carries for each change it makes the database accept. */
export function declarationLine({ facet, change, entry }) {
  return `${declarationPrefix}${facet} ${change} ${entry}`;
}

/** The migrations in supabase/migrations, in the order they are applied. */
export async function readMigrations(directory = path.join(root, 'supabase', 'migrations')) {
  const names = (await readdir(directory)).filter((file) => file.endsWith('.sql')).sort();
  return Promise.all(names.map(async (file) => ({
    name: file.slice(0, -'.sql'.length),
    sql: await readFile(path.join(directory, file), 'utf8'),
  })));
}

/** The changes each migration declares, in the order of `migrations`. */
export function declaredChanges(migrations) {
  return migrations.flatMap(({ name, sql }) => sql.split('\n').filter((line) => line.startsWith(declarationPrefix))
    .map((line) => {
      const match = declarationPattern.exec(line.trimEnd());
      if (!match || !Object.hasOwn(ENFORCED_BY, match[1])) {
        throw new Error(`${name} has a database gate line the gate does not understand: ${line.slice(0, 200)}`);
      }
      return { migration: name, facet: match[1], change: match[2], entry: match[3] };
    }));
}

/** The added and removed entries no migration has made yet. */
export function pendingChanges(changes) {
  return changes.flatMap(({ facet, added, removed, madeBy = {} }) => [
    ...added.map((entry) => ({ facet, change: 'added', entry })),
    ...removed.map((entry) => ({ facet, change: 'removed', entry })),
  ].filter(({ entry }) => !madeBy[entry]));
}

/**
 * Adds to each change `madeBy`: the migration that made each entry, when it declares
 * the entry and production has applied it. For one entry, the latest migration that
 * names it decides, so a migration that undoes a change takes it back.
 * `missingMigrations(names)` answers which of them production lacks.
 */
export async function findMigrationsThatMadeChanges(changes, declarations, missingMigrations) {
  const latest = new Map(declarations.map((declaration) => [`${declaration.facet}\n${declaration.entry}`, declaration]));
  const declared = changes.map(({ facet, added, removed }) => Object.fromEntries([
    ...added.map((entry) => [entry, 'added']),
    ...removed.map((entry) => [entry, 'removed']),
  ].flatMap(([entry, change]) => {
    const declaration = latest.get(`${facet}\n${entry}`);
    return declaration?.change === change ? [[entry, declaration.migration]] : [];
  })));
  const names = [...new Set(declared.flatMap(Object.values))].sort();
  const missing = new Set(names.length > 0 ? await missingMigrations(names) : []);
  return changes.map((change, index) => ({
    ...change,
    madeBy: Object.fromEntries(Object.entries(declared[index]).filter(([, migration]) => !missing.has(migration))),
  }));
}

/**
 * The gate's report in Markdown. It blocks unless `databaseReady` or every change was
 * made by a migration production has applied (`madeBy`).
 */
export function gateReport({ from, to, changes, databaseReady }) {
  if (changes.length === 0) {
    return `Universal Parcel Scraper ${to} changes nothing the database enforces, compared with ${from}.`;
  }
  const code = (text) => `\`${text}\``;
  const pending = databaseReady ? [] : pendingChanges(changes);
  let title = `### Universal Parcel Scraper ${to} changes what the database enforces, as applied migrations declare`;
  if (databaseReady) title = `### Universal Parcel Scraper ${to} changes what the database enforces, confirmed as applied`;
  if (pending.length > 0) title = `### Blocked: Universal Parcel Scraper ${to} changes what the database enforces`;
  const lines = [title, '', `Compared with ${from}:`];
  for (const { facet, added, removed, madeBy = {} } of changes) {
    const entry = (change, text) => `- ${change}: ${code(text)}${madeBy[text] ? `, applied by ${code(madeBy[text])}` : ''}`;
    lines.push('', `**${facetTitles[facet]}** (${ENFORCED_BY[facet].map(code).join(', ')})`, '');
    lines.push(...added.map((text) => entry('added', text)));
    lines.push(...removed.map((text) => entry('removed', text)));
    if (facetNotes[facet]) lines.push('', facetNotes[facet]);
  }
  if (pending.length > 0) {
    lines.push(
      '',
      'Nothing was pushed. Write the migration for the changes not applied yet, with these lines in it, and apply '
        + 'it to the production database. The next run adopts the release:',
      '',
      '```sql',
      ...pending.map(declarationLine),
      '```',
      '',
      'Or adopt it at once, once the migration is applied:',
      '',
      '```bash',
      continueCommand(to),
      '```',
    );
  }
  return lines.join('\n');
}

/**
 * What the gate decides for a release: its report, and whether it waits for a
 * migration. `databaseReady` vouches for every change without asking production.
 */
export async function decideGate({ before, after, migrations, missingMigrations, databaseReady = false }) {
  let changes = diffDatabaseFacets(before.facets, after.facets);
  if (changes.length > 0 && !databaseReady) {
    changes = await findMigrationsThatMadeChanges(changes, declaredChanges(migrations), missingMigrations);
  }
  return {
    blocked: !databaseReady && pendingChanges(changes).length > 0,
    report: gateReport({ from: before.version, to: after.version, changes, databaseReady }),
  };
}

// ---------------------------------------------------------------------------
// Peer pins
// ---------------------------------------------------------------------------

/**
 * Packages the release expects at one exact version while the app has another.
 * npm stays silent about them: .npmrc sets legacy-peer-deps.
 */
export function peerMismatches(peerDependencies = {}, installedVersions = {}) {
  return Object.entries(peerDependencies)
    .filter(([name, expected]) => /^\d+\.\d+\.\d+$/.test(expected)
      && installedVersions[name] !== undefined && installedVersions[name] !== expected)
    .map(([name, expected]) => ({ name, expected, installed: installedVersions[name] }));
}

// ---------------------------------------------------------------------------
// npm registry
// ---------------------------------------------------------------------------

/** Runs `task` until it succeeds; the last failure is thrown after `attempts`. */
export async function retry(task, { attempts, pauseMs, pause = delay }) {
  for (let attempt = 1; ; attempt += 1) {
    try {
      return await task();
    } catch (error) {
      if (attempt >= attempts) throw error;
      await pause(pauseMs);
    }
  }
}

async function registryDocument() {
  // The document `npm install` reads, so that both see the same releases.
  const response = await fetch(registryUrl, {
    headers: { accept: 'application/vnd.npm.install-v1+json; q=1.0, application/json; q=0.8, */*' },
    signal: AbortSignal.timeout(20_000),
  });
  if (!response.ok) throw new Error(`The npm registry answered ${response.status} for ${PACKAGE_NAME}`);
  const document = await response.json();
  const tarballs = Object.fromEntries(Object.entries(document.versions ?? {})
    .map(([version, manifest]) => [version, manifest?.dist?.tarball]));
  return { distTags: document['dist-tags'] ?? {}, tarballs };
}

/** Whether npm serves a tarball: it lists a release before its tarball stops answering 404. */
async function served(url) {
  const response = await fetch(url, { method: 'HEAD', signal: AbortSignal.timeout(20_000) });
  return response.ok;
}

/**
 * Waits until npm lists a release and serves its tarball. npm scans a release for minutes
 * before listing it, then answers 404 for its tarball a while longer: a run started by
 * hand can name one in either state. This waits half an hour.
 */
export async function waitUntilServed(version, { tarballs = async () => (await registryDocument()).tarballs,
  isServed = served, attempts = 121, pauseMs = 15_000, pause = delay } = {}) {
  await retry(async () => {
    const tarball = (await tarballs())[version];
    if (!tarball || !(await isServed(tarball))) {
      console.error(`Waiting for npm to serve ${PACKAGE_NAME}@${version}${tarball ? ' (its tarball answers 404)' : ''}.`);
      throw new Error(`npm does not serve ${PACKAGE_NAME}@${version}`);
    }
  }, { attempts, pauseMs, pause });
}

// ---------------------------------------------------------------------------
// Commands
// ---------------------------------------------------------------------------

async function readJson(file) {
  return JSON.parse(await readFile(file, 'utf8'));
}

async function installedManifest(name) {
  return readJson(path.join(root, 'node_modules', name, 'package.json'));
}

/** The installed release and its database facets. */
export async function installedRelease() {
  const scraper = await import(PACKAGE_NAME);
  if (typeof scraper.universalSources !== 'function') {
    throw new Error(`The installed ${PACKAGE_NAME} does not export universalSources`);
  }
  return {
    version: (await installedManifest(PACKAGE_NAME)).version,
    facets: databaseFacets({
      catalog: scraper.CARRIER_CATALOG,
      stages: scraper.STAGES,
      // Every provider the router can ask, the opt-in one included.
      providers: scraper.universalSources(true),
    }),
  };
}

async function github(pathname) {
  const token = process.env.GITHUB_TOKEN;
  if (!token) throw new Error('GITHUB_TOKEN is required to read the adoption runs');
  const response = await fetch(`${process.env.GITHUB_API_URL ?? 'https://api.github.com'}${pathname}`, {
    headers: { accept: 'application/vnd.github+json', authorization: `Bearer ${token}`, 'x-github-api-version': '2022-11-28' },
    signal: AbortSignal.timeout(20_000),
  });
  if (!response.ok) throw new Error(`GitHub answered ${response.status} for ${pathname}`);
  return response.json();
}

/**
 * Whether this run is its slot's adoption: no other run has adopted since the
 * slot began, and the release it would take has not failed in this slot. A run
 * queued behind another one starts its adoption after it was created, so the
 * runs of the slot before are looked at too, unless they ended before this
 * slot began.
 */
async function due() {
  const repository = process.env.GITHUB_REPOSITORY;
  const ownRun = Number(process.env.GITHUB_RUN_ID);
  if (!repository || !ownRun) throw new Error('GITHUB_REPOSITORY and GITHUB_RUN_ID are required to look for the slot\'s adoption');
  const now = new Date();
  const slot = adoptionSlot(now);
  const held = failureHeldSince(slot, now);
  const ask = (pathname) => retry(() => github(pathname), { attempts: 3, pauseMs: 5_000 });
  const query = new URLSearchParams({ created: `>=${new Date(slot.getTime() - SLOT_LENGTH * MINUTE).toISOString()}`, per_page: '100' });
  const { workflow_runs: listed } = await ask(`/repos/${repository}/actions/workflows/adopt-scraper.yml/runs?${query}`);
  const runs = [];
  for (const run of listed) {
    if (run.id === ownRun || Date.parse(run.updated_at) < slot.getTime()) continue;
    const { jobs } = await ask(`/repos/${repository}/actions/runs/${run.id}/jobs?per_page=100`);
    runs.push({ ...run, jobs });
  }
  const adoption = slotAdoption(slot, runs);
  // The release this run would take, asked only when one of the slot's runs failed with its release.
  let failure;
  let target;
  if (!adoption && runs.some((run) => releaseFailed(held, run))) {
    const current = pinnedVersion(await readJson(path.join(root, 'package.json')));
    target = chooseTarget({ current, distTags: (await retry(registryDocument, { attempts: 3, pauseMs: 5_000 })).distTags });
    if (target.adopt) {
      for (const run of runs.filter((candidate) => releaseFailed(held, candidate))) {
        run.artifacts = (await ask(`/repos/${repository}/actions/runs/${run.id}/artifacts?per_page=100`)).artifacts;
      }
      failure = slotFailure(held, target.version, runs);
    }
  }
  const name = `${slot.toISOString().slice(11, 16)} UTC slot`;
  console.log(adoption ? `The ${name} already had its adoption: ${adoption.html_url}`
    : failure ? `${PACKAGE_NAME} ${target.version} already failed in the ${name}: ${failure.html_url}. A newer release adopts at once.`
      : `Nothing adopted in the ${name} yet: this run adopts.`);
  const outputs = `due=${!adoption && !failure}\n`;
  process.stdout.write(outputs);
  if (process.env.GITHUB_OUTPUT) await appendFile(process.env.GITHUB_OUTPUT, outputs);
}

async function summarize(markdown) {
  console.log(markdown);
  if (process.env.GITHUB_STEP_SUMMARY) await appendFile(process.env.GITHUB_STEP_SUMMARY, `${markdown}\n`);
}

async function resolveTarget(requested, databaseReady) {
  const current = pinnedVersion(await readJson(path.join(root, 'package.json')));
  const distTags = requested ? {} : (await retry(registryDocument, { attempts: 3, pauseMs: 5_000 })).distTags;
  const target = chooseTarget({ current, requested, distTags, databaseReady });
  if (target.adopt) await waitUntilServed(target.version);
  const outputs = `version=${target.version}\ncurrent=${current}\nadopt=${target.adopt}\n`;
  process.stdout.write(outputs);
  if (process.env.GITHUB_OUTPUT) await appendFile(process.env.GITHUB_OUTPUT, outputs);
  if (!target.adopt) {
    await summarize(`### Nothing to adopt\n\nThe app is on Universal Parcel Scraper ${current}${
      target.version === current ? '.' : `, which is newer than ${target.version}.`}`);
  }
}

/**
 * Which of the named migrations production lacks, from its ledger of applied
 * migrations. The names are public, so the publishable key may ask, as the deployment does.
 */
async function productionLacks(names) {
  const url = process.env.SUPABASE_URL;
  const key = process.env.SUPABASE_PUBLISHABLE_KEY;
  if (!url || !key) {
    throw new Error('SUPABASE_URL and SUPABASE_PUBLISHABLE_KEY are required to ask production which migrations it has');
  }
  return retry(async () => {
    const response = await fetch(`${url.replace(/\/+$/, '')}/rest/v1/rpc/missing_migrations`, {
      method: 'POST',
      headers: { apikey: key, 'content-type': 'application/json' },
      body: JSON.stringify({ p_names: names }),
      signal: AbortSignal.timeout(20_000),
    });
    if (!response.ok) throw new Error(`The database answered ${response.status} when asked which migrations it has`);
    const missing = await response.json();
    if (!Array.isArray(missing) || missing.some((name) => typeof name !== 'string')) {
      throw new Error('The database answered something else than a list of migrations');
    }
    return missing;
  }, { attempts: 3, pauseMs: 5_000 });
}

async function gate(snapshotFile, databaseReady) {
  const { blocked, report } = await decideGate({
    before: await readJson(snapshotFile),
    after: await installedRelease(),
    migrations: await readMigrations(),
    missingMigrations: productionLacks,
    databaseReady,
  });
  await summarize(report);
  if (blocked) process.exitCode = 3;
}

async function checkPeers() {
  const release = await installedManifest(PACKAGE_NAME);
  const installed = {};
  for (const name of Object.keys(release.peerDependencies ?? {})) {
    installed[name] = (await installedManifest(name).catch(() => ({}))).version;
  }
  const mismatches = peerMismatches(release.peerDependencies, installed);
  if (mismatches.length === 0) {
    console.log(`The app's pins match what ${PACKAGE_NAME} ${release.version} expects.`);
    return;
  }
  await summarize([
    `### Universal Parcel Scraper ${release.version} expects other versions than the app pins`,
    '',
    ...mismatches.map(({ name, expected, installed: has }) => `- \`${name}\` ${expected}, the app has ${has}`),
    '',
    'Move these pins and the scraper in one commit.',
  ].join('\n'));
  process.exitCode = 1;
}

async function main() {
  const { positionals: [command, file], values } = parseArgs({
    allowPositionals: true,
    options: {
      version: { type: 'string', default: '' },
      'database-ready': { type: 'boolean', default: false },
    },
  });
  if (command === 'due') return due();
  if (command === 'resolve') return resolveTarget(values.version.trim(), values['database-ready']);
  if (command === 'snapshot' && file) return writeFile(file, `${JSON.stringify(await installedRelease(), null, 2)}\n`);
  if (command === 'gate' && file) return gate(file, values['database-ready']);
  if (command === 'peers') return checkPeers();
  if (command === 'holds' && file && values.version) {
    return void process.stdout.write(`${holdsRelease(await readJson(file), values.version.trim())}\n`);
  }
  throw new Error('Usage: adopt-scraper.mjs due | resolve [--version=X.Y.Z] [--database-ready] | snapshot <file> | gate <file> [--database-ready] | peers | holds <package.json> --version=X.Y.Z');
}

if (process.argv[1] && path.resolve(process.argv[1]) === fileURLToPath(import.meta.url)) {
  try {
    await main();
  } catch (error) {
    const message = String(error?.message ?? error).replace(/\s+/g, ' ');
    console.error(process.env.GITHUB_ACTIONS ? `::error::${message}` : message);
    if (process.env.GITHUB_STEP_SUMMARY) {
      await appendFile(process.env.GITHUB_STEP_SUMMARY, `### Scraper adoption stopped\n\n${message}\n`);
    }
    process.exitCode = 1;
  }
}
