// node --test scripts/warm-edge-guard.test.mjs
import assert from 'node:assert/strict';
import { readFileSync } from 'node:fs';
import { registerHooks } from 'node:module';
import { beforeEach, describe, it } from 'node:test';
import { MAX_FAILURES_IN_A_ROW, pagesToWarm, run } from './warm-edge-guard.mjs';

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
const saved = () => [...(stores.get('edge-guard-saved-pages') ?? new Map()).keys()];

const { default: worker } = await import('../ops/cloudflare/edge/worker.js');

const origin = 'https://peektracker.com';
const carrierPages = ['/carriers', '/carriers/dhl-paket', '/de/carriers/dhl-paket', '/fi/carriers/posti'];
const guidePages = ['/guides', '/guides/amazon-orders', '/pl/guides/customs'];
const otherPages = ['/', '/de', '/assistants', '/privacy.html'];
const sitemap = paths => '<?xml version="1.0" encoding="UTF-8"?>\n'
  + '<urlset xmlns="http://www.sitemaps.org/schemas/sitemap/0.9" xmlns:xhtml="http://www.w3.org/1999/xhtml">\n'
  + paths.map(address => `<url>\n<loc>${address.startsWith('http') ? address : `${origin}${address}`}</loc>\n`
    + `<xhtml:link rel="alternate" hreflang="de" href="${origin}/de${address}" />\n</url>\n`).join('')
  + '</urlset>\n';
const html = body => new Response(`<!doctype html><html><head></head><body>${body}</body></html>`, { status: 200, headers: { 'content-type': 'text/html; charset=utf-8' } });

/**
 * The server behind the guard: its sitemap, its health check and its pages, `page` answering each page. It counts
 * the pages asked for and how many at once.
 */
function server(paths, page = address => html(address)) {
  const asked = [];
  let inFlight = 0;
  let most = 0;
  const handle = async (input, init) => {
    const request = new Request(input, init);
    const { pathname } = new URL(request.url);
    if (pathname === '/sitemap.xml') return new Response(sitemap(paths), { headers: { 'content-type': 'application/xml' } });
    if (pathname === '/health') return Response.json({ ok: true });
    asked.push({ path: pathname, headers: Object.fromEntries(request.headers) });
    inFlight += 1;
    most = Math.max(most, inFlight);
    await new Promise(resolve => setTimeout(resolve, 2));
    inFlight -= 1;
    return page(pathname);
  };
  return { handle, asked, most: () => most };
}

/** Requests that go through the guard to `origin`, as they do while its routes are on. */
function throughGuard(behind) {
  globalThis.fetch = behind.handle;
  return async (url, init) => {
    const waits = [];
    const response = await worker.fetch(new Request(url, init), {}, { waitUntil: promise => waits.push(promise) });
    await Promise.all(waits);
    return response;
  };
}

const quiet = () => {
  const lines = [];
  return { lines, log: line => lines.push(line) };
};

describe('the pages to warm', () => {
  it('are the carriers’ pages and the guides of the site the guard keeps copies of, nothing else', () => {
    const pages = [...carrierPages, ...guidePages, ...otherPages, '/carriers/dhl-paket', 'https://elsewhere.example/carriers/dhl-paket', '/guides?ref=x']
      .map(address => ({ url: address.startsWith('http') ? address : `${origin}${address}`, lastmod: null }));
    assert.deepEqual(pagesToWarm(pages, origin), [...carrierPages, ...guidePages].map(address => `${origin}${address}`));
  });
});

describe('warming the edge guard', () => {
  beforeEach(() => stores.clear());

  it('asks for each carrier’s page and guide through the guard, a few at a time, so it has a copy of each when the server is down', async () => {
    const behind = server([...carrierPages, ...guidePages, ...otherPages]);
    const fetchImpl = throughGuard(behind);
    const { lines, log } = quiet();

    assert.equal(await run(['--concurrency=2', origin], { fetchImpl, log }), 0);
    assert.deepEqual(behind.asked.map(({ path }) => path).sort(), [...carrierPages, ...guidePages].sort());
    assert.equal(behind.most(), 2, 'pages asked at once');
    // As a reader without a session: no cookie, no query, the page itself.
    for (const { headers } of behind.asked) {
      assert.equal(headers.cookie, undefined);
      assert.match(headers.accept, /text\/html/);
    }
    assert.deepEqual(saved().sort(), [...carrierPages, ...guidePages].map(address => `https://saved.edge-guard.invalid/peektracker.com${address}`).sort());
    assert.match(lines.at(-1), /got 7 of 7 pages/);

    // The server goes down: the guard answers with the copy the warm-up left.
    globalThis.fetch = async () => new Response('error code: 1033', { status: 530, headers: { 'content-type': 'text/html' } });
    const outage = await worker.fetch(new Request(`${origin}/de/carriers/dhl-paket`, { headers: { accept: 'text/html' } }), {}, { waitUntil() {} });
    assert.equal(outage.status, 200);
    assert.equal(outage.headers.get('x-maintenance'), 'unplanned');
    assert.match(await outage.text(), /\/de\/carriers\/dhl-paket/);
  });

  it('asks for no page while the guard is not in front, where nothing would be saved', async () => {
    const behind = server([...carrierPages, ...guidePages]);
    const { lines, log } = quiet();

    assert.equal(await run([origin], { fetchImpl: behind.handle, log }), 1);
    assert.deepEqual(behind.asked, []);
    assert.match(lines.join('\n'), /not in front/);
  });

  it('stops once the guard answers in the server’s place: the server is already down', async () => {
    let answered = 0;
    const behind = server([...carrierPages, ...guidePages], address => (answered++ < 2 ? html(address)
      : new Response('error code: 1033', { status: 530, headers: { 'content-type': 'text/html' } })));
    const { lines, log } = quiet();

    assert.equal(await run(['--concurrency=1', origin], { fetchImpl: throughGuard(behind), log }), 1);
    assert.equal(behind.asked.length, 3);
    assert.match(lines.join('\n'), /Stopped: the edge guard answered .* in the server's place/);
  });

  it('stops after failures in a row, and says which pages failed', async () => {
    const many = Array.from({ length: 30 }, (_, index) => `/carriers/carrier-${index}`);
    const behind = server(many, () => new Response('<!doctype html><title>Not found</title>', { status: 404, headers: { 'content-type': 'text/html' } }));
    const { lines, log } = quiet();

    assert.equal(await run(['--concurrency=1', origin], { fetchImpl: throughGuard(behind), log }), 1);
    assert.equal(behind.asked.length, MAX_FAILURES_IN_A_ROW);
    assert.match(lines.join('\n'), /carrier-0: HTTP 404/);
    assert.match(lines.join('\n'), /Stopped: 10 pages failed in a row/);
    assert.deepEqual(saved(), []);
  });

  it('lists the pages on a dry run and asks for none', async () => {
    const behind = server([...carrierPages, ...otherPages]);
    const { lines, log } = quiet();

    assert.equal(await run(['--dry-run', origin], { fetchImpl: behind.handle, log }), 0);
    assert.deepEqual(behind.asked, []);
    assert.deepEqual(lines.slice(0, -1), carrierPages.map(address => `${origin}${address}`));
    assert.match(lines.at(-1), /4 pages would be asked for/);
  });

  it('refuses a missing site or more than a few pages at once', async () => {
    const { log } = quiet();
    for (const args of [[], ['peektracker.com'], ['--concurrency=0', origin], ['--concurrency=9', origin], [origin, origin]]) {
      assert.equal(await run(args, { fetchImpl: () => assert.fail('nothing is asked'), log }), 2, args.join(' '));
    }
  });
});
