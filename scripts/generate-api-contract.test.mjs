import assert from 'node:assert/strict';
import { spawnSync } from 'node:child_process';
import { cpSync, mkdirSync, mkdtempSync, readFileSync, rmSync, writeFileSync } from 'node:fs';
import { tmpdir } from 'node:os';
import path from 'node:path';
import { fileURLToPath } from 'node:url';
import test from 'node:test';

const script = fileURLToPath(new URL('./generate-api-contract.mjs', import.meta.url));

/** A checkout with the scraper `installed` and `locked`, holding only what the generator reads first. */
function checkout(installed, locked) {
  const root = mkdtempSync(path.join(tmpdir(), 'contract-'));
  const scraper = path.join(root, 'node_modules', 'universal-parcel-scraper');
  mkdirSync(path.join(root, 'scripts'));
  mkdirSync(scraper, { recursive: true });
  cpSync(script, path.join(root, 'scripts', 'generate-api-contract.mjs'));
  writeFileSync(path.join(scraper, 'package.json'), JSON.stringify({ name: 'universal-parcel-scraper', version: installed, type: 'module', exports: './index.js' }));
  writeFileSync(path.join(scraper, 'index.js'), 'export const CARRIER_CATALOG = {}, RECIPIENT_ACTIONS = [], STAGES = [];\n');
  writeFileSync(path.join(root, 'package-lock.json'), JSON.stringify({ packages: { 'node_modules/universal-parcel-scraper': { version: locked } } }));
  return root;
}

test('writes nothing from a scraper other than the locked one', (t) => {
  const root = checkout('0.2.0', '0.1.0');
  t.after(() => rmSync(root, { recursive: true, force: true }));
  const run = spawnSync(process.execPath, [path.join(root, 'scripts', 'generate-api-contract.mjs')], { encoding: 'utf8' });
  assert.notEqual(run.status, 0);
  assert.match(run.stderr, /universal-parcel-scraper 0\.2\.0 is installed, but package-lock\.json locks 0\.1\.0/);
  assert.doesNotMatch(run.stdout, /Wrote/);
});

test('reads on past a scraper that is the locked one', (t) => {
  const root = checkout('0.1.0', '0.1.0');
  t.after(() => rmSync(root, { recursive: true, force: true }));
  const run = spawnSync(process.execPath, [path.join(root, 'scripts', 'generate-api-contract.mjs')], { encoding: 'utf8' });
  // The stand-in checkout has no contract, so the generator stops at reading it.
  assert.notEqual(run.status, 0);
  assert.doesNotMatch(run.stderr, /package-lock\.json locks/);
  assert.match(run.stderr, /openapi\.json/);
});

/** Runs the generator on the real scraper catalog and contract, with carriers `ids` added to the catalog. */
function generateWith(t, ids) {
  const root = checkout('0.1.0', '0.1.0');
  t.after(() => rmSync(root, { recursive: true, force: true }));
  const scraper = import.meta.resolve('universal-parcel-scraper');
  writeFileSync(path.join(root, 'node_modules', 'universal-parcel-scraper', 'index.js'), [
    `import { CARRIER_CATALOG as catalog } from ${JSON.stringify(scraper)};`,
    `export { RECIPIENT_ACTIONS, STAGES } from ${JSON.stringify(scraper)};`,
    'const [carrier] = Object.values(catalog);',
    `export const CARRIER_CATALOG = { ...catalog, ${ids.map((id) => `${JSON.stringify(id)}: carrier`).join(', ')} };`,
  ].join('\n'));
  mkdirSync(path.join(root, 'contracts'));
  cpSync(fileURLToPath(new URL('../contracts/openapi.json', import.meta.url)), path.join(root, 'contracts', 'openapi.json'));
  const run = spawnSync(process.execPath, [path.join(root, 'scripts', 'generate-api-contract.mjs')], { encoding: 'utf8' });
  return { root, run };
}

test('names a carrier that starts with a digit for Swift and Kotlin without an entry for it', (t) => {
  const { root, run } = generateWith(t, ['1688-express', '007-cargo']);
  assert.equal(run.status, 0, run.stderr);
  const swift = readFileSync(path.join(root, 'ios', 'PeekDeliveryTracker', 'GeneratedAPIContract.swift'), 'utf8');
  const kotlin = readFileSync(path.join(root, 'android', 'core', 'src', 'main', 'kotlin', 'com', 'plhery', 'peekdeliverytracker',
    'core', 'api', 'GeneratedApiContract.kt'), 'utf8');
  assert.match(swift, /static let oneThousandSixHundredEightyEightExpress = CarrierID\(rawValue: "1688-express"\)/);
  assert.match(swift, /static let zeroZeroSevenCargo = CarrierID\(rawValue: "007-cargo"\)/);
  assert.match(kotlin, /val OneThousandSixHundredEightyEightExpress = CarrierId\("1688-express"\)/);
  assert.match(kotlin, /val ZeroZeroSevenCargo = CarrierId\("007-cargo"\)/);
});

test('names a value holding a character other than - and _ as separate words', (t) => {
  const { root, run } = generateWith(t, []);
  assert.equal(run.status, 0, run.stderr);
  const swift = readFileSync(path.join(root, 'ios', 'PeekDeliveryTracker', 'GeneratedAPIContract.swift'), 'utf8');
  const kotlin = readFileSync(path.join(root, 'android', 'core', 'src', 'main', 'kotlin', 'com', 'plhery', 'peekdeliverytracker',
    'core', 'api', 'GeneratedApiContract.kt'), 'utf8');
  assert.match(swift, /case parcelsRead = "parcels:read"/);
  assert.match(kotlin, /@SerialName\("parcels:read"\) ParcelsRead\("parcels:read"\)/);
});

test('stops on two carriers given one Swift and Kotlin name, naming both, and writes nothing', (t) => {
  const { run } = generateWith(t, ['2go', 'two-go']);
  assert.notEqual(run.status, 0);
  assert.match(run.stderr, /CarrierId values 2go and two-go are both named twoGo in Swift; name one of them in swiftEnumCaseNames/);
  assert.doesNotMatch(run.stdout, /Wrote/);
});

test('says how to name a carrier whose first word starts with a digit after a separator', (t) => {
  const { run } = generateWith(t, ['-9lives']);
  assert.notEqual(run.status, 0);
  assert.match(run.stderr, /Cannot make a Swift or Kotlin identifier of -9lives: its first word must start with a letter\. Name an enum value in swiftEnumCaseNames/);
  assert.doesNotMatch(run.stdout, /Wrote/);
});
