// The edge guard: a Cloudflare Worker in front of a site that runs on one server behind a Cloudflare
// Tunnel. It passes every request through unchanged. When the server can't answer (the tunnel is
// gone during a reboot, or the proxy has no app behind it), it answers in the site's place:
//
// - a page: the copy of it saved the last time someone without a session read it, under a short
//   note, or else the site's maintenance page (503, Retry-After);
// - anything else (the API, the auth host, a script): a 503 with Retry-After, JSON for the API,
//   never a 4xx, so no client signs out or forgets what it keeps. The proxy's own 404, while the
//   server is up but the app isn't yet, counts as the server not answering.
//
// `/maintenance.json` is always answered here, without the server: it says whether maintenance
// was announced and until when, for the apps and the web app to word their notice.
//
// The announcement is a JSON value in the MAINTENANCE KV namespace, shared by every site: the key
// `all` (the server, e.g. a reboot) and the site's own key (its database, e.g. a Supabase upgrade).
// RUNBOOK.md describes the switch. Without the binding, nothing is announced and the rest works.
//
// This file is the same in every site that uses it; `site.js` holds what differs.

import site from './site.js';
import page from './maintenance.html';

/** Statuses that mean the server did not answer: the proxy's and Cloudflare's own. */
const UNREACHABLE = new Set([502, 503, 504, 520, 521, 522, 523, 524, 525, 526, 527, 530]);
/** How long an isolate keeps the announcement it read. KV itself caches it for up to a minute. */
const ANNOUNCEMENT_TTL_MS = 30_000;
/** An announcement nobody switched off ends this long after the time it gave, so a forgotten one can't linger. */
const ANNOUNCEMENT_GRACE_MS = 3 * 60 * 60_000;
/** How long a saved copy of a page is kept. */
const SAVED_COPY_SECONDS = 7 * 24 * 60 * 60;
const SAVED_COPY_CACHE = 'edge-guard-saved-pages';

let announcement = { value: null, readAt: 0 };

/** The announcement in force, or null. Never throws: a KV failure means nothing is announced. */
export async function readAnnouncement(env, now = Date.now()) {
  if (!env.MAINTENANCE) return null;
  if (now - announcement.readAt < ANNOUNCEMENT_TTL_MS) return current(announcement.value, now);
  let value = null;
  try {
    const [all, own] = await Promise.all([env.MAINTENANCE.get('all', 'json'), env.MAINTENANCE.get(site.key, 'json')]);
    value = pickAnnouncement([all, own], now);
  } catch {
    value = null;
  }
  announcement = { value, readAt: now };
  return current(value, now);
}

/** Of the announcements given, the one in force that ends last. */
export function pickAnnouncement(values, now = Date.now()) {
  return values.map((value) => current(normalize(value), now)).filter(Boolean)
    .sort((a, b) => (Date.parse(b.backAround ?? '') || 0) - (Date.parse(a.backAround ?? '') || 0))[0] ?? null;
}

function normalize(value) {
  if (!value || typeof value !== 'object' || value.on === false) return null;
  const time = (text) => typeof text === 'string' && !Number.isNaN(Date.parse(text)) ? new Date(text).toISOString() : null;
  return {
    since: time(value.since),
    backAround: time(value.backAround),
    // `hard` turns the server away before it is down, e.g. while its database is upgraded.
    mode: value.mode === 'hard' ? 'hard' : 'soft',
    ends: time(value.ends),
  };
}

function current(value, now) {
  if (!value) return null;
  const end = value.ends ? Date.parse(value.ends) : value.backAround ? Date.parse(value.backAround) + ANNOUNCEMENT_GRACE_MS : Infinity;
  const start = value.since ? Date.parse(value.since) : -Infinity;
  // A hard switch applies from its start; a soft one only words the notice, so it applies at once.
  if (value.mode === 'hard' && now < start) return { ...value, mode: 'soft' };
  return now < end ? value : null;
}

/** What `/maintenance.json` and the 503s say: whether maintenance was announced, and until when. */
export function status(found) {
  return {
    planned: Boolean(found),
    mode: found?.mode ?? null,
    since: found?.since ?? null,
    backAround: found?.backAround ?? null,
  };
}

const isPageRequest = (request) => (request.method === 'GET' || request.method === 'HEAD')
  && (request.headers.get('accept') ?? '').includes('text/html');

const isJsonRequest = (request, url) => site.jsonHosts.includes(url.hostname) || site.apiPath.test(url.pathname)
  || (request.headers.get('accept') ?? '').includes('application/json');

/** The server did not answer: its proxy's or Cloudflare's error, not one the app wrote itself as JSON. */
export function unreachable(response) {
  return UNREACHABLE.has(response.status) && !(response.headers.get('content-type') ?? '').includes('application/json');
}

/**
 * The proxy's own "404 page not found" (Traefik's, as text): the server is up but the site's app isn't yet,
 * as just after a reboot. The app's own 404s are pages or JSON, so they pass through.
 */
export function appMissing(request, url, response) {
  return response.status === 404 && (response.headers.get('content-type') ?? '').startsWith('text/plain')
    && (isPageRequest(request) || isJsonRequest(request, url));
}

/** A page whose copy may be saved and shown to anyone: public, the same for every reader, asked for without a session. */
export function savable(request, url) {
  if (request.method !== 'GET' || url.search || !site.savedPages.test(url.pathname)) return false;
  if (site.jsonHosts.includes(url.hostname)) return false;
  const cookie = request.headers.get('cookie') ?? '';
  if (site.sessionCookie.test(cookie) || request.headers.has('authorization')) return false;
  // A site whose pages differ for some readers says which requests get the page everyone gets.
  return site.savableRequest?.(request, url) ?? true;
}

/** Where a page's copy is kept: one per address, or one per variant (a country's prices, say) when the site has them. */
function savedKey(request, url) {
  const variant = site.savedVariant?.(request);
  return `https://saved.edge-guard.invalid/${url.hostname}${url.pathname}${variant ? `?variant=${encodeURIComponent(variant)}` : ''}`;
}

async function savePage(request, url, response) {
  const type = response.headers.get('content-type') ?? '';
  if (response.status !== 200 || !type.includes('text/html')) return;
  // A page that sets a cookie is someone's, unless it is only the site's language cookie.
  const cookies = response.headers.getSetCookie?.() ?? [response.headers.get('set-cookie')].filter(Boolean);
  if (cookies.some((cookie) => !site.harmlessCookies?.test(cookie.split('=')[0].trim()))) return;
  const headers = new Headers({ 'Content-Type': type, 'Cache-Control': `public, max-age=${SAVED_COPY_SECONDS}`, 'X-Saved-At': new Date().toISOString() });
  // The policy goes with the page: its scripts carry the nonce it names.
  const policy = response.headers.get('content-security-policy');
  if (policy) headers.set('Content-Security-Policy', policy);
  const cache = await caches.open(SAVED_COPY_CACHE);
  await cache.put(savedKey(request, url), new Response(response.body, { headers }));
}

/** The saved copy of a page, with a note on its body, or null. */
export async function savedPage(request, url, found) {
  if (!site.savedPages.test(url.pathname)) return null;
  const { lang, timeZone } = reader(request, url);
  let saved;
  try {
    saved = await (await caches.open(SAVED_COPY_CACHE)).match(savedKey(request, url));
  } catch {
    return null;
  }
  if (!saved) return null;
  const html = await saved.text();
  const note = site.savedNote(lang, { planned: Boolean(found), backAround: found?.backAround ?? null, timeZone });
  // Drawn by a style rule, not an element: the page's own scripts redraw its body as they start, and would drop
  // one. The rule first undoes whatever the site's own style gives body::before.
  const nonce = /'nonce-([^']+)'/.exec(saved.headers.get('Content-Security-Policy') ?? '')?.[1];
  const content = JSON.stringify(note).replace(/</g, '\\3c ');
  const style = `<style${nonce ? ` nonce="${nonce}"` : ''}>body::before{all:initial;${site.savedNoteStyle};content:${content}}</style>`;
  const body = /<\/head>/i.test(html) ? html.replace(/<\/head>/i, `${style}</head>`) : style + html;
  const headers = new Headers(saved.headers);
  headers.set('Cache-Control', 'no-store');
  headers.set('X-Maintenance', found ? 'planned' : 'unplanned');
  headers.delete('X-Saved-At');
  return new Response(body, { status: 200, headers });
}

/** The reader's language and time zone, for what is written here. */
const reader = (request, url) => ({ lang: language(request, url), timeZone: request.cf?.timezone ?? 'UTC' });

/** The reader's language: the address's, then the site's own cookie, then the browser's, then English. */
export function language(request, url) {
  const first = url.pathname.split('/')[1]?.toLowerCase();
  if (site.languages.includes(first)) return first;
  const cookie = new RegExp(`(?:^|;\\s*)${site.localeCookie.replace(/\./g, '\\.')}=([^;]+)`).exec(request.headers.get('cookie') ?? '')?.[1]?.toLowerCase();
  if (cookie && site.languages.includes(cookie)) return cookie;
  for (const part of (request.headers.get('accept-language') ?? '').split(',')) {
    const tag = part.split(';')[0].trim().toLowerCase();
    if (site.languages.includes(tag)) return tag;
    const base = tag.split('-')[0];
    if (site.languages.includes(base)) return base;
  }
  return 'en';
}

const retryAfter = (found) => {
  const back = found?.backAround ? Math.round((Date.parse(found.backAround) - Date.now()) / 1000) : 0;
  return String(Math.min(Math.max(back, 30), 900));
};

/** The maintenance page in the reader's language, with what was announced. */
export function maintenancePage(request, url, found) {
  const lang = language(request, url);
  const state = JSON.stringify({ ...status(found), lang }).replace(/</g, '\\u003c');
  const html = page
    .replace(/<html lang="[^"]*"/, `<html lang="${lang}" dir="${site.rtl?.includes(lang) ? 'rtl' : 'ltr'}"`)
    .replace('/*__STATE__*/null', state);
  return new Response(request.method === 'HEAD' ? null : html, {
    status: 503,
    headers: {
      'Content-Type': 'text/html; charset=utf-8',
      'Cache-Control': 'no-store',
      'Retry-After': retryAfter(found),
      'X-Maintenance': found ? 'planned' : 'unplanned',
      'X-Robots-Tag': 'noindex',
      // The page is one file: its own style and script, nothing else.
      'Content-Security-Policy': "default-src 'none'; style-src 'unsafe-inline'; script-src 'unsafe-inline'; img-src data:; font-src data:; connect-src 'self'; base-uri 'none'; form-action 'none'; frame-ancestors 'none'",
    },
  });
}

function maintenanceJson(request, url, found) {
  const lang = language(request, url);
  return Response.json({ error: site.apiMessage(lang, Boolean(found)), maintenance: status(found) }, {
    status: 503,
    headers: { 'Cache-Control': 'no-store', 'Retry-After': retryAfter(found), 'X-Maintenance': found ? 'planned' : 'unplanned', ...cors(request) },
  });
}

function cors(request) {
  const origin = request.headers.get('origin');
  return origin ? { 'Access-Control-Allow-Origin': origin, 'Access-Control-Allow-Credentials': 'true', Vary: 'Origin' } : {};
}

/** The site's answer while its server can't give one. */
export async function fallback(request, url, found) {
  if (isPageRequest(request)) return (await savedPage(request, url, found)) ?? maintenancePage(request, url, found);
  if (isJsonRequest(request, url) || request.method !== 'GET') return maintenanceJson(request, url, found);
  return new Response(null, { status: 503, headers: { 'Cache-Control': 'no-store', 'Retry-After': retryAfter(found) } });
}

/** In local tests only: the server to pass requests to, in place of the address's own host. */
function toOrigin(request, env) {
  if (!env.TEST_ORIGIN) return request;
  const url = new URL(request.url);
  const origin = new URL(env.TEST_ORIGIN);
  url.protocol = origin.protocol;
  url.host = origin.host;
  return new Request(url, request);
}

const worker = {
  async fetch(request, env, ctx) {
    const url = new URL(request.url);
    if (url.pathname === '/maintenance.json') {
      return Response.json(status(await readAnnouncement(env)), { headers: { 'Cache-Control': 'no-store', 'Access-Control-Allow-Origin': '*' } });
    }
    const found = await readAnnouncement(env);
    // Switched to hard: the server is left alone, apart from what checks it is up.
    if (found?.mode === 'hard' && !site.healthPath.test(url.pathname)) return fallback(request, url, found);

    let response;
    try {
      response = await fetch(toOrigin(request, env));
    } catch {
      return fallback(request, url, found);
    }
    if (unreachable(response) || appMissing(request, url, response) || (response.status === 500 && isPageRequest(request) && site.savedPages.test(url.pathname))) {
      if (site.healthPath.test(url.pathname)) return response;
      const saved = isPageRequest(request) ? await savedPage(request, url, found) : null;
      if (saved) return saved;
      return response.status === 500 ? response : fallback(request, url, found);
    }
    if (savable(request, url) && response.status === 200) ctx.waitUntil(savePage(request, url, response.clone()).catch(() => {}));
    return response;
  },
};

export default worker;
