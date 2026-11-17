import { defaultCache } from '@serwist/next/worker';
import type { PrecacheEntry, SerwistGlobalConfig, SerwistPlugin } from 'serwist';
import { ExpirationPlugin, NetworkFirst, NetworkOnly, Serwist } from 'serwist';
import { isShareDraftId, SHARE_DRAFTS, shareDraftAddress, shareTargetAddress, type ShareFailure } from '../src/lib/shareTarget';

declare global {
  interface WorkerGlobalScope extends SerwistGlobalConfig {
    __SW_MANIFEST: (PrecacheEntry | string)[] | undefined;
  }
}

declare const self: ServiceWorkerGlobalScope;

importScripts('/push-sw.js');

const SHARE_TARGET_CACHE = 'sdt-private-share-target-v1';
const SHARE_TARGET_MAX_AGE_MS = 10 * 60 * 1_000;
let shareTargetTail: Promise<void> = Promise.resolve();

async function serializeShareTarget<T>(operation: () => Promise<T>): Promise<T> {
  const prior = shareTargetTail;
  let release!: () => void;
  shareTargetTail = new Promise<void>((resolve) => { release = resolve; });
  await prior;
  try {
    return await operation();
  } finally {
    release();
  }
}

/** No draft, or an abandoned one: the app tells the reader that nothing came through. */
function missingShareTarget(): Response {
  return new Response(null, {
    status: 404,
    headers: { 'Cache-Control': 'no-store' },
  });
}

/**
 * Opens the app after a share: to read its draft, or to say why there is none.
 * The worker cannot know the reader's language, so the app says it.
 */
function openApp(marker: { draft: string } | { failure: ShareFailure }): Response {
  return Response.redirect(new URL(shareTargetAddress(marker), self.location.origin), 303);
}

/** A draft the app may still read: one written in the last ten minutes, by a clock that has not gone back. */
function fresh(draft: Response): boolean {
  const createdAtHeader = draft.headers.get('X-SDT-Created-At');
  const createdAt = Number(createdAtHeader);
  const age = Date.now() - createdAt;
  return createdAtHeader !== null && Number.isFinite(createdAt) && age >= -60_000 && age <= SHARE_TARGET_MAX_AGE_MS;
}

async function captureShareTarget(request: Request): Promise<Response> {
  const declaredHeader = request.headers.get('content-length');
  if (declaredHeader !== null) {
    const declaredLength = Number(declaredHeader);
    if (!Number.isInteger(declaredLength) || declaredLength < 0) return openApp({ failure: 'failed' });
    if (declaredLength > 65_536) return openApp({ failure: 'too-large' });
  }

  let form: FormData;
  try {
    form = await request.formData();
  } catch {
    return openApp({ failure: 'failed' });
  }
  const asText = (value: FormDataEntryValue | null) => (
    typeof value === 'string' ? value.trim() : ''
  );
  const title = asText(form.get('title')).slice(0, 80);
  const parts = [form.get('url'), form.get('text')]
    .map(asText)
    .filter((value, index, values) => value && values.indexOf(value) === index);
  const trackingInput = parts.join('\n').slice(0, 10_000);
  if (!trackingInput) return openApp({ failure: 'failed' });
  // Each share has its own draft, so a second share before the app read the first takes nothing from it.
  // Only the random id is in the app's address, never what was shared.
  const draft = crypto.randomUUID();
  await serializeShareTarget(async () => {
    const cache = await caches.open(SHARE_TARGET_CACHE);
    // Drafts the app never came back for go as a new one arrives.
    for (const key of await cache.keys()) {
      const kept = await cache.match(key);
      if (!kept || !fresh(kept)) await cache.delete(key);
    }
    await cache.put(shareDraftAddress(draft), new Response(
      JSON.stringify({ label: title, trackingInput }),
      {
        headers: {
          'Content-Type': 'application/json',
          'Cache-Control': 'no-store',
          'X-SDT-Created-At': String(Date.now()),
        },
      },
    ));
  });
  return openApp({ draft });
}

/** Hands a draft to the app once, if it is still fresh. */
async function consumeShareTarget(url: URL): Promise<Response> {
  const id = url.pathname.slice(SHARE_DRAFTS.length);
  if (!isShareDraftId(id)) return missingShareTarget();
  return await serializeShareTarget(async () => {
    const cache = await caches.open(SHARE_TARGET_CACHE);
    const address = shareDraftAddress(id);
    const draft = await cache.match(address);
    await cache.delete(address);
    return draft && fresh(draft) ? draft : missingShareTarget();
  });
}

/** Statuses a proxy or Cloudflare answers with for a server that does not answer itself. */
const SERVER_DOWN = new Set([502, 503, 504, 520, 521, 522, 523, 524, 525, 526, 527, 530]);

/**
 * A page the server could not give: a proxy's or Cloudflare's error, the proxy's own "404 page not found" while the
 * app starts, or what the edge in front answers in the server's place (`X-Maintenance`: its saved copy of a page,
 * or its maintenance page).
 */
function serverDown(response: Response): boolean {
  return SERVER_DOWN.has(response.status) || response.headers.has('X-Maintenance')
    || (response.status === 404 && (response.headers.get('Content-Type') ?? '').startsWith('text/plain'));
}

/**
 * While the server is down for maintenance, a page opens from the copy this browser kept of it. Without one, the
 * edge's own maintenance page says so; anything else, such as Cloudflare's error page, gives way to the offline page.
 */
const whileServerDown: SerwistPlugin = {
  // Throwing here makes the strategy look in its cache, as it does without a network.
  fetchDidSucceed: async ({ response, state }) => {
    if (!serverDown(response)) return response;
    if (state) state.down = response;
    throw new Error(`The server is down (${response.status})`);
  },
  // Next's router needs the fallback's real URL when it hydrates.
  // The target itself is precached and works without a network.
  handlerDidError: async ({ state }) => {
    const down = state?.down;
    if (down instanceof Response && down.headers.has('X-Maintenance')) return down;
    return Response.redirect(new URL('/~offline', self.location.origin).href, 302);
  },
};

const pageNavigation = new NetworkFirst({
  cacheName: 'pages',
  plugins: [
    new ExpirationPlugin({ maxEntries: 32, maxAgeSeconds: 24 * 60 * 60 }),
    {
      // Only a page itself is kept for offline use. A redirect is the server's
      // answer of the moment, such as a host whose pages moved: the browser
      // follows it, and it is asked again next time. Nor is what the edge
      // answered in the server's place.
      cacheWillUpdate: async ({ response }) => (response.status === 200 && !response.redirected && !serverDown(response) ? response : null),
    },
    whileServerDown,
  ],
});

const serwist: Serwist = new Serwist({
  precacheEntries: self.__SW_MANIFEST,
  skipWaiting: true,
  clientsClaim: true,
  // The app shell comes from the precache, so a preload request would go unused.
  navigationPreload: false,
  runtimeCaching: [
    {
      matcher: ({ sameOrigin, url }) => sameOrigin && url.pathname === '/share-target',
      method: 'POST',
      handler: async ({ request }) => captureShareTarget(request),
    },
    {
      matcher: ({ sameOrigin, url }) => sameOrigin && url.pathname.startsWith(SHARE_DRAFTS),
      handler: async ({ url }) => consumeShareTarget(url),
    },
    {
      // Account data and authentication traffic must never enter CacheStorage,
      // nor may a page whose address is a capability: an invitation or a
      // parcel link, or an assistant's request to connect. Keep this route ahead
      // of Serwist's defaults, which cache GET APIs and cross-origin responses
      // for applications with public data.
      // `/maintenance.json` says what the edge knows now, never what it said before.
      matcher: ({ sameOrigin, url }) => (
        !sameOrigin || url.pathname.startsWith('/api/') || url.pathname === '/health' || url.pathname === '/invite'
          || url.pathname === '/maintenance.json'
          || url.pathname.startsWith('/i/') || url.pathname.startsWith('/p/') || url.pathname.startsWith('/oauth/')
      ),
      handler: new NetworkOnly(),
    },
    {
      // Opening the app never waits for the network: every address of the
      // single-page app starts from the shell this worker precached together
      // with its scripts, so the two always come from the same build. The
      // client then reads its own query and saved data.
      matcher: ({ sameOrigin, request, url }) => sameOrigin && request.mode === 'navigate' && url.pathname === '/',
      handler: async ({ request, event }): Promise<Response> => (await serwist.matchPrecache('/')) ?? pageNavigation.handle({ request, event }),
    },
    {
      matcher: ({ sameOrigin, request }) => sameOrigin && request.mode === 'navigate',
      handler: pageNavigation,
    },
    ...defaultCache,
  ],
  fallbacks: {
    entries: [
      {
        url: '/~offline',
        matcher({ request }) {
          return request.destination === 'document';
        },
      },
    ],
  },
});

serwist.addEventListeners();
