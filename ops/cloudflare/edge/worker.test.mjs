// node --test ops/cloudflare/edge/worker.test.mjs
import assert from 'node:assert/strict';
import { readFileSync } from 'node:fs';
import { registerHooks } from 'node:module';
import { beforeEach, describe, it } from 'node:test';

// The Worker imports its page as text, as wrangler does.
registerHooks({
  load(url, context, next) {
    if (!url.endsWith('.html')) return next(url, context);
    return { format: 'module', source: `export default ${JSON.stringify(readFileSync(new URL(url), 'utf8'))};`, shortCircuit: true };
  },
});

// The Cache API of one data centre.
const stores = new Map();
globalThis.caches = {
  async open(name) {
    if (!stores.has(name)) stores.set(name, new Map());
    const store = stores.get(name);
    return {
      async put(key, response) { store.set(String(key), { body: await response.text(), headers: [...response.headers] }); },
      async match(key) { const hit = store.get(String(key)); return hit && new Response(hit.body, { headers: hit.headers }); },
    };
  },
};

const { default: worker, pickAnnouncement, savable } = await import('./worker.js');

const page = (path, headers = {}) => new Request(`https://peektracker.com${path}`, { headers: { accept: 'text/html,application/xhtml+xml', ...headers } });
const api = (path, init = {}) => new Request(`https://peektracker.com${path}`, { headers: { accept: 'application/json' }, ...init });
const kv = (values = {}) => ({ async get(key) { return values[key] ?? null; } });

async function run(request, origin, env = {}) {
  globalThis.fetch = async () => typeof origin === 'function' ? origin() : origin.clone();
  const waits = [];
  const response = await worker.fetch(request, env, { waitUntil: (promise) => waits.push(promise) });
  await Promise.all(waits);
  return response;
}

const tunnelDown = new Response('<html>error code: 1033</html>', { status: 530, headers: { 'content-type': 'text/html' } });
const refused = () => { throw new TypeError('fetch failed'); };
const carrierPage = new Response('<!doctype html><html><head></head><body class="x"><h1>DHL Paket</h1></body></html>', {
  status: 200, headers: { 'content-type': 'text/html; charset=utf-8', 'content-security-policy': "script-src 'nonce-abc'" },
});

describe('edge guard', () => {
  beforeEach(() => stores.clear());

  it('passes the server’s answers through', async () => {
    const response = await run(api('/api/packages'), Response.json({ ok: true }));
    assert.equal(response.status, 200);
    assert.deepEqual(await response.json(), { ok: true });
  });

  it('passes the app’s own JSON errors through', async () => {
    const response = await run(api('/api/packages'), Response.json({ error: 'The delivery database is temporarily unavailable' }, { status: 502 }));
    assert.equal(response.status, 502);
    assert.match((await response.json()).error, /database/);
  });

  it('answers a page with the maintenance page when the tunnel is down', async () => {
    const response = await run(page('/de', { 'accept-language': 'fr' }), tunnelDown);
    assert.equal(response.status, 503);
    assert.equal(response.headers.get('cache-control'), 'no-store');
    assert.ok(Number(response.headers.get('retry-after')) >= 30);
    const html = await response.text();
    assert.match(html, /<html lang="de"/);
    assert.match(html, /"planned":false/);
    assert.doesNotMatch(html, /__STATE__/);
  });

  it('picks the reader’s language from the browser when the address has none', async () => {
    const html = await (await run(page('/', { 'accept-language': 'pt-BR,pt;q=0.9' }), refused)).text();
    assert.match(html, /<html lang="pt-br"/);
  });

  it('treats the proxy’s own 404 as the app not being there yet', async () => {
    const proxy404 = new Response('404 page not found\n', { status: 404, headers: { 'content-type': 'text/plain; charset=utf-8' } });
    const answer = await run(api('/api/packages/abc'), proxy404);
    assert.equal(answer.status, 503);
    assert.equal(typeof (await answer.json()).maintenance.planned, 'boolean');
    assert.equal((await run(page('/'), proxy404)).status, 503);
    // The app's own 404s go out as they are.
    const gone = await run(api('/api/packages/abc'), Response.json({ error: 'Not found' }, { status: 404 }));
    assert.equal(gone.status, 404);
    const missing = await run(page('/nope'), new Response('<html>Not found</html>', { status: 404, headers: { 'content-type': 'text/html' } }));
    assert.equal(missing.status, 404);
  });

  it('never answers the API with a 4xx, nor with HTML', async () => {
    for (const origin of [tunnelDown, refused, new Response('Bad Gateway', { status: 502, headers: { 'content-type': 'text/plain' } })]) {
      const response = await run(api('/api/public/parcels/abc', { method: 'GET' }), origin);
      assert.equal(response.status, 503);
      assert.equal(response.headers.get('content-type'), 'application/json');
      const body = await response.json();
      assert.equal(typeof body.error, 'string');
      assert.equal(body.maintenance.planned, false);
    }
  });

  it('answers the auth host with JSON, whatever it was asked for', async () => {
    const request = new Request('https://auth.peektracker.com/auth/v1/token?grant_type=refresh_token', { method: 'POST', body: '{}' });
    const response = await run(request, tunnelDown);
    assert.equal(response.status, 503);
    assert.ok((await response.json()).maintenance);
  });

  it('keeps the health check’s own failure, so watchers see the server down', async () => {
    const response = await run(page('/health'), tunnelDown);
    assert.equal(response.status, 530);
  });

  it('serves the saved copy of a carrier page, with a note, while the server is down', async () => {
    await run(page('/carriers/dhl-paket-tracking'), carrierPage);
    const response = await run(page('/carriers/dhl-paket-tracking'), tunnelDown);
    assert.equal(response.status, 200);
    assert.equal(response.headers.get('cache-control'), 'no-store');
    assert.equal(response.headers.get('content-security-policy'), "script-src 'nonce-abc'");
    const html = await response.text();
    assert.match(html, /<style nonce="abc">body::before\{content:"You’re reading a saved copy[^"]*";display:block/);
    assert.match(html, /<\/style><\/head><body class="x"><h1>/);
    assert.match(html, /<h1>DHL Paket<\/h1>/);
  });

  it('never saves a page asked for with a session, a query or a cookie set in return', async () => {
    assert.equal(savable(page('/carriers/dhl', { cookie: 'sb-auth-auth-token=x' }), new URL('https://peektracker.com/carriers/dhl')), false);
    assert.equal(savable(page('/carriers/dhl?x=1'), new URL('https://peektracker.com/carriers/dhl?x=1')), false);
    assert.equal(savable(page('/p/abc'), new URL('https://peektracker.com/p/abc')), false);
    const withCookie = new Response('<body>a</body>', { headers: { 'content-type': 'text/html', 'set-cookie': 'a=b' } });
    await run(page('/guides/customs'), withCookie);
    assert.equal((await run(page('/guides/customs'), tunnelDown)).status, 503);
  });

  it('does not save the parcel pages or the app', async () => {
    await run(page('/p/some-link'), carrierPage);
    assert.equal((await run(page('/p/some-link'), tunnelDown)).status, 503);
  });

  it('answers /maintenance.json itself, with what was announced', async () => {
    globalThis.fetch = () => assert.fail('the server is not asked');
    const backAround = new Date(Date.now() + 15 * 60_000).toISOString();
    const response = await worker.fetch(new Request('https://peektracker.com/maintenance.json'), { MAINTENANCE: kv({ all: { backAround } }) }, { waitUntil() {} });
    const body = await response.json();
    assert.equal(body.planned, true);
    assert.equal(body.backAround, backAround);
  });

  it('turns everything but the health check away in hard mode, without asking the server', async () => {
    const env = { MAINTENANCE: kv({ peek: { mode: 'hard', backAround: new Date(Date.now() + 600_000).toISOString() } }) };
    // The isolate keeps what it read for 30 s; a fresh module would read it again.
    const { default: fresh } = await import(`./worker.js?hard=${Date.now()}`);
    let asked = 0;
    globalThis.fetch = async () => { asked += 1; return Response.json({ ok: true }); };
    const response = await fresh.fetch(api('/api/packages'), env, { waitUntil() {} });
    assert.equal(response.status, 503);
    assert.equal((await response.json()).maintenance.mode, 'hard');
    assert.equal(asked, 0);
    assert.equal((await fresh.fetch(page('/health'), env, { waitUntil() {} })).status, 200);
  });
});

describe('announcements', () => {
  const now = Date.parse('2026-11-18T02:20:00Z');

  it('take the one that ends last', () => {
    const found = pickAnnouncement([{ backAround: '2026-11-18T02:45:00Z' }, { backAround: '2026-11-18T03:30:00Z' }], now);
    assert.equal(found.backAround, '2026-11-18T03:30:00.000Z');
  });

  it('end by themselves three hours after the time they gave', () => {
    assert.equal(pickAnnouncement([{ backAround: '2026-11-17T23:00:00Z' }], now), null);
    assert.ok(pickAnnouncement([{ backAround: '2026-11-18T00:00:00Z' }], now));
  });

  it('ignore what was switched off or can’t be read', () => {
    assert.equal(pickAnnouncement([{ on: false, backAround: '2026-11-18T03:00:00Z' }, 'nonsense', null], now), null);
  });

  it('start a hard switch only at its own time', () => {
    const found = pickAnnouncement([{ mode: 'hard', since: '2026-11-18T02:27:00Z', backAround: '2026-11-18T02:45:00Z' }], now);
    assert.equal(found.mode, 'soft');
  });
});
