// The deploy's migration gate, run on synthetic migrations against a stand-in for production's
// missing_migrations answer.
import assert from 'node:assert/strict';
import { execFile } from 'node:child_process';
import { copyFileSync, mkdirSync, mkdtempSync, rmSync, writeFileSync } from 'node:fs';
import { createServer } from 'node:http';
import { tmpdir } from 'node:os';
import path from 'node:path';
import { after, before, describe, it } from 'node:test';

const script = new URL('./check-production-migrations.sh', import.meta.url);

let server;
let answer = { body: '[]' };
before(async () => {
  server = createServer((request, response) => {
    request.resume();
    request.on('end', () => {
      response.writeHead(200, { 'content-type': answer.type ?? 'application/json' });
      response.end(answer.body);
    });
  });
  await new Promise((resolve) => server.listen(0, '127.0.0.1', resolve));
});
after(() => server.close());

/** Runs the gate in a repository holding `migrations` ({ name: sql }), production answering `body` with HTTP 200. */
async function gate(migrations, body, type) {
  answer = { body, type };
  const directory = mkdtempSync(path.join(tmpdir(), 'migrations-gate-'));
  try {
    const copy = path.join(directory, 'scripts', 'check-production-migrations.sh');
    mkdirSync(path.dirname(copy));
    mkdirSync(path.join(directory, 'supabase', 'migrations'), { recursive: true });
    copyFileSync(script, copy);
    for (const [name, sql] of Object.entries(migrations)) {
      writeFileSync(path.join(directory, 'supabase', 'migrations', `${name}.sql`), sql);
    }
    const env = { ...process.env, SUPABASE_URL: `http://127.0.0.1:${server.address().port}`, SUPABASE_PUBLISHABLE_KEY: 'test' };
    return await new Promise((resolve) => {
      execFile('bash', [copy], { env }, (error, stdout, stderr) => resolve({ code: error ? error.code : 0, stdout, stderr }));
    });
  } finally {
    rmSync(directory, { recursive: true, force: true });
  }
}

const OLD = '20261101000000_old';
const NEW = '20261116110000_new';
const migrations = { [OLD]: 'select 1;\n', [NEW]: 'select 1;\n' };

describe("production's answer", () => {
  it('lets the deploy through when production has every migration', async () => {
    const { code, stdout, stderr } = await gate(migrations, '[]');
    assert.equal(code, 0, stderr);
    assert.match(stdout, /Production has all 2 migrations\./);
  });

  it('stops the deploy at a missing migration, and names it', async () => {
    const { code, stderr } = await gate(migrations, JSON.stringify([NEW]));
    assert.equal(code, 1);
    assert.match(stderr, new RegExp(`Production lacks these migrations[^]*${NEW}`));
  });

  it('stops the deploy when a 200 answer is not a list of names, such as an error page', async () => {
    for (const [body, type] of [['<html>maintenance</html>', 'text/html'], ['{"message":"JWT expired"}'], ['null'], [''], ['[1]']]) {
      const { code, stdout, stderr } = await gate(migrations, body, type);
      assert.equal(code, 1, `${JSON.stringify(body)} let the deploy through: ${stdout}`);
      assert.match(stderr, /not a list of migration names/);
    }
  });
});
