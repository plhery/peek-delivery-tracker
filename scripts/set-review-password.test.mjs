import assert from 'node:assert/strict';
import { EventEmitter } from 'node:events';
import test from 'node:test';
import { hiddenLine, passwordProblem, reviewUser, setReviewPassword } from './set-review-password.mjs';

const LONG = 'correct-horse-battery-staple';

test('takes only a long password typed the same twice', () => {
  assert.equal(passwordProblem(LONG, LONG), null);
  assert.match(passwordProblem(LONG, `${LONG}x`), /differ/);
  assert.equal(passwordProblem('eight-ch', 'eight-ch'), null);
  assert.match(passwordProblem('seven-c', 'seven-c'), /at least 8/);
  assert.match(passwordProblem(` ${LONG}`, ` ${LONG}`), /space/);
});

test('picks the account with exactly the address, once it is confirmed', () => {
  const users = [
    { id: 'u-near', email: 'review+old@example.test', email_confirmed_at: '2026-01-01T00:00:00Z' },
    { id: 'u-review', email: 'Review@example.test', email_confirmed_at: '2026-01-01T00:00:00Z' },
  ];
  assert.equal(reviewUser(users, 'review@example.test').id, 'u-review');
  assert.throws(() => reviewUser(users, 'nobody@example.test'), /No account has the address/);
  assert.throws(() => reviewUser([{ id: 'u', email: 'new@example.test', email_confirmed_at: null }], 'new@example.test'),
    /isn't confirmed/);
});

test('finds the account, then sets its password through the admin API with the service key', async () => {
  const calls = [];
  const fetcher = async (url, init) => {
    calls.push({ url: String(url), method: init.method ?? 'GET', headers: init.headers, body: init.body });
    return String(url).includes('?filter=')
      ? Response.json({ users: [{ id: 'u-review', email: 'review@example.test', email_confirmed_at: '2026-01-01T00:00:00Z' }] })
      : Response.json({ id: 'u-review' });
  };
  const id = await setReviewPassword({ url: 'https://auth.example.test', key: 'service-key', email: ' Review@example.test ', password: LONG, fetcher });
  assert.equal(id, 'u-review');
  assert.deepEqual(calls.map(({ url, method }) => [method, url]), [
    ['GET', 'https://auth.example.test/auth/v1/admin/users?filter=review%40example.test&per_page=50'],
    ['PUT', 'https://auth.example.test/auth/v1/admin/users/u-review'],
  ]);
  assert.equal(calls[1].headers.Authorization, 'Bearer service-key');
  assert.equal(calls[1].headers.apikey, 'service-key');
  assert.deepEqual(JSON.parse(calls[1].body), { password: LONG });
});

test('says what Auth refused, and changes nothing for an unknown account', async () => {
  const weak = async (url) => String(url).includes('?filter=')
    ? Response.json({ users: [{ id: 'u-review', email: 'review@example.test', email_confirmed_at: '2026-01-01T00:00:00Z' }] })
    : Response.json({ msg: 'Password is known to be weak' }, { status: 422 });
  await assert.rejects(setReviewPassword({ url: 'https://auth.example.test', key: 'k', email: 'review@example.test', password: LONG, fetcher: weak }),
    /Auth answered 422: Password is known to be weak/);
  let puts = 0;
  const empty = async (url, init) => { if (init.method === 'PUT') puts += 1; return Response.json({ users: [] }); };
  await assert.rejects(setReviewPassword({ url: 'https://auth.example.test', key: 'k', email: 'review@example.test', password: LONG, fetcher: empty }),
    /No account has the address/);
  assert.equal(puts, 0);
});

function terminal(isTTY = true) {
  const input = Object.assign(new EventEmitter(), {
    isTTY, raw: [], setRawMode(on) { this.raw.push(on); }, setEncoding() {}, resume() {}, pause() {},
  });
  const output = { text: '', write(chunk) { this.text += chunk; } };
  return { input, output };
}

test('reads a typed line without echoing it, and leaves the terminal as it was', async () => {
  const { input, output } = terminal();
  const line = hiddenLine('Password: ', { input, output });
  input.emit('data', 'secrex');
  input.emit('data', '\u007ft\r');
  assert.equal(await line, 'secret');
  assert.equal(output.text, 'Password: \n');
  assert.deepEqual(input.raw, [true, false]);
  assert.equal(input.listenerCount('data'), 0);
});

test('stops on Ctrl-C, and refuses a piped secret', async () => {
  const { input, output } = terminal();
  const line = hiddenLine('Password: ', { input, output });
  input.emit('data', 'abc\u0003');
  await assert.rejects(line, /Stopped/);
  await assert.rejects(hiddenLine('Password: ', terminal(false)), /Run this in a terminal/);
});
