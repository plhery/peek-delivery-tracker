// A dry run of the installed scraper release over production's stored scans, asking no provider
// (docs/SCRAPER.md). `--query` prints the read-only export to run on the database; its output, one
// parcel a line, goes to stdin. `--from <version>` replays the rows with that earlier release too
// and keeps the rows it reads as they are stored: the ones this release changes. It takes
// 0.621.0-main.2946 or later, the first release to export `carrierHomeZone`, which the replay
// imports; an older one fails on that import. The report names rows by their ids' first eight
// characters and shows no number, place or wording. `--sql <file>` writes the guarded transaction
// that would apply the changes and step back the open parcels no row then shows past the label,
// `--source <a,b>` keeps the rows of those sources only.
import { execFileSync } from 'node:child_process';
import { existsSync, mkdirSync, readFileSync, writeFileSync } from 'node:fs';
import { resolve } from 'node:path';
import { pathToFileURL } from 'node:url';
import { build } from 'esbuild';

const root = resolve(import.meta.dirname, '..');
const args = process.argv.slice(2);
const option = (name) => {
  const index = args.indexOf(name);
  return index === -1 ? null : args[index + 1] ?? null;
};

// The module lives in the app's TypeScript; bundled into node_modules, it resolves the installed
// packages, or an earlier scraper release unpacked beside it.
const cache = resolve(root, 'node_modules/.cache/replay-stored-scans');
async function replayModule(directory) {
  const output = resolve(directory, 'storedReplay.mjs');
  mkdirSync(directory, { recursive: true });
  await build({
    entryPoints: [resolve(root, 'src/server/storedReplay.ts')], outfile: output,
    bundle: true, platform: 'node', format: 'esm', packages: 'external', logLevel: 'error',
  });
  return import(pathToFileURL(output).href);
}
const { STORED_SCANS_QUERY, replayParcel, replaySql, steppedBack } = await replayModule(cache);

if (args.includes('--query')) {
  console.log(STORED_SCANS_QUERY);
  process.exit(0);
}

async function earlierRelease(version) {
  const directory = resolve(cache, version);
  const unpacked = resolve(directory, 'node_modules/universal-parcel-scraper');
  if (!existsSync(resolve(unpacked, 'package.json'))) {
    mkdirSync(unpacked, { recursive: true });
    const tarball = execFileSync('npm', ['pack', `universal-parcel-scraper@${version}`, '--silent', '--pack-destination', directory], { encoding: 'utf8' }).trim().split('\n').at(-1);
    execFileSync('tar', ['-xzf', resolve(directory, tarball), '-C', unpacked, '--strip-components', '1']);
  }
  return replayModule(directory);
}

const sources = option('--source')?.split(',') ?? null;
const from = option('--from');
const lines = readFileSync(0, 'utf8').split('\n').map((line) => line.trim()).filter((line) => line.startsWith('{'));
const parcels = lines.map((line) => JSON.parse(line)).map((parcel) => ({ ...parcel, events: parcel.events ?? [] }));
const key = (change) => `${change.kind} ${change.row}`;
const earlier = from ? new Set(parcels.flatMap((await earlierRelease(from)).replayParcel).map(key)) : new Set();
const replayed = parcels.flatMap(replayParcel).filter((change) => !sources || sources.includes(change.source));
const changes = replayed.filter((change) => !earlier.has(key(change)));
const stepBacks = steppedBack(parcels, changes);
const { version } = JSON.parse(readFileSync(resolve(root, 'node_modules/universal-parcel-scraper/package.json'), 'utf8'));
const short = (id) => id.slice(0, 8);
const minute = (iso) => iso.slice(0, 16).replace('T', ' ') + 'Z';

console.log(`Replayed ${parcels.reduce((sum, parcel) => sum + parcel.events.length, 0)} stored rows of ${parcels.length} parcels with universal-parcel-scraper ${version}.`);
if (from) console.log(`Left out ${replayed.length - changes.length} rows ${from} already reads otherwise than they are stored.`);
for (const kind of ['clock', 'stage']) {
  const found = changes.filter((change) => change.kind === kind);
  console.log(`\n${kind === 'clock' ? 'Clocks' : 'Stages'}: ${found.length} rows on ${new Set(found.map((change) => change.parcel)).size} parcels, ${found.filter((change) => change.open).length} of them on open parcels.`);
  const groups = new Map();
  for (const change of found) {
    const key = kind === 'clock'
      ? `${change.source} ${change.basis ?? '?'} ${Math.round((Date.parse(change.to) - Date.parse(change.from)) / 60_000)} min`
      : `${change.source} ${change.code ?? '-'} ${change.from} -> ${change.to} (${change.reason})`;
    groups.set(key, [...groups.get(key) ?? [], change]);
  }
  for (const [key, group] of [...groups].sort((a, b) => b[1].length - a[1].length)) {
    console.log(`  ${String(group.length).padStart(4)}  ${key}`);
    for (const change of group.slice(0, args.includes('--all') ? group.length : 5)) {
      console.log(`        ${short(change.parcel)} ${short(change.row)} ${change.open ? 'open  ' : 'closed'} ${kind === 'clock'
        ? `${minute(change.from)} -> ${minute(change.to)} ${change.timeZone ?? ''}${change.clash ? ' CLASH' : ''}`
        : `${change.from} -> ${change.to}`}`);
    }
    if (group.length > 5 && !args.includes('--all')) console.log(`        … ${group.length - 5} more (--all)`);
  }
}

console.log(`\nParcels stepped back: ${stepBacks.length}.`);
for (const step of stepBacks) console.log(`        ${short(step.parcel)} ${step.from} -> ${step.to}`);

const sqlFile = option('--sql');
if (sqlFile) {
  writeFileSync(sqlFile, replaySql(changes, stepBacks), { mode: 0o600 });
  console.log(`\nWrote the guarded transaction for ${changes.length} rows and ${stepBacks.length} parcels to ${sqlFile}; it ends open, to roll back or commit.`);
}
