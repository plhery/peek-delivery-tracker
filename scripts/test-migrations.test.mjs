// The SQL suite's check that each migration has a number of its own, run on synthetic migrations
// before the suite asks for its database.
import assert from 'node:assert/strict';
import { execFile } from 'node:child_process';
import { copyFileSync, mkdirSync, mkdtempSync, rmSync, writeFileSync } from 'node:fs';
import { tmpdir } from 'node:os';
import path from 'node:path';
import { describe, it } from 'node:test';

const script = new URL('./test-migrations.sh', import.meta.url);

/** Runs the suite without a database in a repository holding migrations of these names. */
async function suite(names) {
  const directory = mkdtempSync(path.join(tmpdir(), 'migrations-suite-'));
  try {
    const copy = path.join(directory, 'scripts', 'test-migrations.sh');
    mkdirSync(path.dirname(copy));
    mkdirSync(path.join(directory, 'supabase', 'migrations'), { recursive: true });
    copyFileSync(script, copy);
    for (const name of names) writeFileSync(path.join(directory, 'supabase', 'migrations', `${name}.sql`), 'select 1;\n');
    const env = { ...process.env, TEST_DATABASE_URL: '' };
    return await new Promise((resolve) => {
      execFile('bash', [copy], { env }, (error, stdout, stderr) => resolve({ code: error ? error.code : 0, stdout, stderr }));
    });
  } finally {
    rmSync(directory, { recursive: true, force: true });
  }
}

describe('migration numbers', () => {
  it('refuses two migrations with one number, and names them', async () => {
    const { code, stderr } = await suite(['20261116000000_first', '20261116000000_second', '20261116010000_third']);
    assert.equal(code, 1);
    assert.match(stderr, /share the number 20261116000000/);
    assert.match(stderr, /20261116000000_first\.sql\n20261116000000_second\.sql/);
    assert.doesNotMatch(stderr, /20261116010000/);
  });

  it('lets through the pairs applied before the check, then asks for the database', async () => {
    const { code, stderr } = await suite(['20261106110000_dongdu_carrier', '20261106110000_hand_stage_changes', '20261116000000_new']);
    assert.equal(code, 2);
    assert.match(stderr, /TEST_DATABASE_URL is required/);
  });

  it('refuses a third migration on the number of an applied pair', async () => {
    const { code, stderr } = await suite(['20261106110000_dongdu_carrier', '20261106110000_hand_stage_changes', '20261106110000_late']);
    assert.equal(code, 1);
    assert.match(stderr, /share the number 20261106110000/);
  });
});
