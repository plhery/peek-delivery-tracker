import assert from 'node:assert/strict';
import { execFile } from 'node:child_process';
import { once } from 'node:events';
import { createServer } from 'node:http';
import { fileURLToPath } from 'node:url';
import { promisify } from 'node:util';
import test from 'node:test';

const run = promisify(execFile);
const script = fileURLToPath(new URL('./wait-for-commit.sh', import.meta.url));
const COMMIT = 'a1'.repeat(20);
const OLD = 'b2'.repeat(20);

// Answers /health/live with each of `answers` in turn, then with the last one: a commit, null for
// a build that names none, or a status.
async function wait(t, answers, { commit = COMMIT, timeout = 5 } = {}) {
  const requests = [];
  const server = createServer((request, response) => {
    const url = new URL(request.url, 'http://localhost');
    requests.push(url.pathname);
    const answer = answers[Math.min(requests.length, answers.length) - 1];
    if (typeof answer === 'number') response.statusCode = answer;
    response.end(JSON.stringify(typeof answer === 'number' ? { ok: false } : { ok: true, ...(answer ? { commit: answer } : {}) }));
  });
  server.listen(0, '127.0.0.1');
  t.after(() => new Promise((resolve) => server.close(resolve)));
  await once(server, 'listening');
  const result = await run('bash', [script, `http://127.0.0.1:${server.address().port}/`, commit], {
    env: { ...process.env, WAIT_FOR_COMMIT_TIMEOUT: String(timeout), WAIT_FOR_COMMIT_INTERVAL: '0' },
    timeout: 20_000,
  });
  return { ...result, requests };
}

test('waits through the old build and a bad gateway until three answers in a row name the commit', async (t) => {
  const { stdout, requests } = await wait(t, [null, OLD, 502, COMMIT, OLD, COMMIT, COMMIT, COMMIT]);
  assert.equal(requests.length, 8);
  assert.ok(requests.every((path) => path === '/health/live'));
  assert.match(stdout, /serves a build that names no commit\n.*serves b2b2[^]*serves HTTP 502\n[^]*served a1a1\w+ three answers in a row\n$/);
});
test('gives up after the timeout, saying what the site still serves', async (t) => {
  await assert.rejects(wait(t, [OLD], { timeout: 1 }), (error) => {
    assert.equal(error.code, 1);
    assert.match(error.stderr, new RegExp(`After 1 s, http://127\\.0\\.0\\.1:\\d+ still serves ${OLD}, not ${COMMIT}\\. The new build is not serving, so IndexNow is not told`));
    return true;
  });
});
test('asks for a full commit', async (t) => {
  await assert.rejects(wait(t, [COMMIT], { commit: COMMIT.slice(0, 12) }), (error) => {
    assert.equal(error.code, 2);
    assert.match(error.stderr, /Usage: /);
    return true;
  });
});
