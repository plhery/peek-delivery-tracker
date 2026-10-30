import assert from 'node:assert/strict';
import { spawnSync } from 'node:child_process';
import { cpSync, mkdirSync, mkdtempSync, rmSync, writeFileSync } from 'node:fs';
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
