// Fills the edge guard's saved copies before a planned maintenance. The guard saves a copy of a carrier's
// page or a guide only when someone reads it while its routes are on, so once the maintenance switch has
// put it in front of the site, and before the server goes down, this asks for every such page of the
// sitemap through it, a few at a time (docs/DEPLOYMENT.md, "Edge guard"):
//
//   node scripts/warm-edge-guard.mjs https://peek.example.com
//   node scripts/warm-edge-guard.mjs --dry-run https://peek.example.com      (lists the pages, asks none)
//
// Each page is one request to the Worker. Copies are kept per Cloudflare data centre, so this fills the
// one its requests go through. It asks for no page unless /health comes back marked `X-Edge-Guard: on`,
// as it does only through the guard, and stops once the guard answers in the server's place
// (`X-Maintenance`) or after several failures in a row. --concurrency=<1-8> sets how many pages are
// asked at once (4). A page that failed, or a stop, exits with 1.
import path from 'node:path';
import { fileURLToPath } from 'node:url';
import site from '../ops/cloudflare/edge/site.js';
import { parseSitemap } from './indexnow.mjs';

export const DEFAULT_CONCURRENCY = 4;
export const MAX_CONCURRENCY = 8;
/** Failures in a row after which the server is taken to be down, or the guard gone, and nothing more is asked. */
export const MAX_FAILURES_IN_A_ROW = 10;
const TIMEOUT_MS = 30_000;
const USER_AGENT = 'peek-edge-guard-warm-up';
const USAGE = 'Usage: node scripts/warm-edge-guard.mjs [--dry-run] [--concurrency=<1-8>] https://peek.example.com';

const reason = error => (error instanceof Error ? [error.message, error.cause?.message].filter(Boolean).join(': ') : String(error));

/** The sitemap's pages the guard keeps a copy of: on the site's host, without a query, among its saved pages. */
export function pagesToWarm(pages, origin) {
  const { host } = new URL(origin);
  const urls = new Set();
  for (const { url } of pages) {
    let address;
    try {
      address = new URL(url);
    } catch {
      continue;
    }
    if (address.host === host && !address.search && site.savedPages.test(address.pathname)) urls.add(address.href);
  }
  return [...urls];
}

/** Whether the edge guard stands in front of the site: its health check comes back marked by it. */
export async function guardInFront(origin, fetchImpl = fetch) {
  const response = await fetchImpl(new URL('/health', origin).href, {
    headers: { 'User-Agent': USER_AGENT, 'Cache-Control': 'no-cache' },
    signal: AbortSignal.timeout(TIMEOUT_MS),
  });
  await response.body?.cancel().catch(() => {});
  return response.headers.get('x-edge-guard') === 'on';
}

/**
 * Asks for each page through the guard, `concurrency` at a time, as a reader without a session does, and reads
 * each answer whole. Returns how many came back as the server's page, which pages failed, and whether it stopped
 * early: once the guard answers in the server's place, or after `MAX_FAILURES_IN_A_ROW` failures in a row.
 */
export async function warm(urls, { concurrency = DEFAULT_CONCURRENCY, fetchImpl = fetch, log = console.log } = {}) {
  const queue = [...urls];
  const failed = [];
  let asked = 0;
  let inARow = 0;
  let stopped = null;
  const fail = (url, why) => {
    failed.push(url);
    log(`${url}: ${why}`);
    inARow += 1;
    if (inARow >= MAX_FAILURES_IN_A_ROW && !stopped) stopped = `${inARow} pages failed in a row`;
  };
  const next = async () => {
    while (queue.length && !stopped) {
      const url = queue.shift();
      let response;
      try {
        response = await fetchImpl(url, {
          headers: { Accept: 'text/html', 'User-Agent': USER_AGENT },
          redirect: 'manual',
          signal: AbortSignal.timeout(TIMEOUT_MS),
        });
        // The guard saves its copy from the server's answer as it goes out: read it whole, as a reader would.
        await response.arrayBuffer();
      } catch (error) {
        fail(url, reason(error));
        continue;
      }
      if (response.headers.has('x-maintenance')) {
        stopped ??= `the edge guard answered ${url} in the server's place: the server is down`;
        return;
      }
      if (response.status !== 200 || !(response.headers.get('content-type') ?? '').includes('text/html')) {
        fail(url, `HTTP ${response.status}`);
        continue;
      }
      asked += 1;
      inARow = 0;
    }
  };
  await Promise.all(Array.from({ length: Math.min(concurrency, Math.max(queue.length, 1)) }, next));
  return { asked, failed, stopped };
}

function httpOrigin(address) {
  try {
    const url = new URL(address);
    return url.protocol === 'https:' || url.protocol === 'http:' ? url.origin : null;
  } catch {
    return null;
  }
}

/** Runs the command line `args` and returns its exit code. */
export async function run(args, { fetchImpl = fetch, log = console.log } = {}) {
  let dryRun = false;
  let concurrency = DEFAULT_CONCURRENCY;
  const positional = [];
  for (const argument of args) {
    const option = argument.match(/^--(?:(dry-run)|concurrency=(\d+))$/);
    if (option?.[1]) dryRun = true;
    else if (option) concurrency = Number(option[2]);
    else positional.push(argument);
  }
  const origin = positional.length === 1 && !positional[0].startsWith('--') ? httpOrigin(positional[0]) : null;
  if (!origin || !Number.isInteger(concurrency) || concurrency < 1 || concurrency > MAX_CONCURRENCY) {
    log(USAGE);
    return 2;
  }

  let xml;
  try {
    const response = await fetchImpl(new URL('/sitemap.xml', origin).href, {
      headers: { 'User-Agent': USER_AGENT, 'Cache-Control': 'no-cache' },
      signal: AbortSignal.timeout(TIMEOUT_MS),
    });
    if (!response.ok) throw new Error(`HTTP ${response.status}`);
    xml = await response.text();
  } catch (error) {
    log(`${origin}/sitemap.xml could not be read: ${reason(error)}`);
    return 1;
  }
  const urls = pagesToWarm(parseSitemap(xml), origin);
  if (urls.length === 0) {
    log(`${origin}/sitemap.xml lists no carrier's page or guide: nothing to ask for.`);
    return 1;
  }
  if (dryRun) {
    for (const url of urls) log(url);
    log(`Dry run: ${urls.length} pages would be asked for through the edge guard, one Worker request each. None was asked.`);
    return 0;
  }

  let inFront;
  try {
    inFront = await guardInFront(origin, fetchImpl);
  } catch (error) {
    log(`${origin}/health could not be read: ${reason(error)}`);
    return 1;
  }
  if (!inFront) {
    log(`The edge guard is not in front of ${origin} (/health has no X-Edge-Guard: on), so it would save no copy.`
      + ' Turn its routes on first with the maintenance switch. No page was asked for.');
    return 1;
  }

  log(`Asking for ${urls.length} pages through the edge guard, ${concurrency} at a time.`);
  const { asked, failed, stopped } = await warm(urls, { concurrency, fetchImpl, log });
  if (stopped) log(`Stopped: ${stopped}.`);
  log(`The edge guard got ${asked} of ${urls.length} pages from the server to save${failed.length ? `; ${failed.length} failed` : ''}.`);
  return stopped || failed.length ? 1 : 0;
}

if (process.argv[1] && path.resolve(process.argv[1]) === fileURLToPath(import.meta.url)) {
  process.exitCode = await run(process.argv.slice(2));
}
