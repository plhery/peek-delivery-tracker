import { ProxyAgent } from 'undici';

const HOST = /^[a-z0-9]+(?:[.-][a-z0-9]+)*$/;

function proxySettings(raw: string, setting: string): { key: string; uri: string; token?: string } {
  try {
    const proxy = new URL(raw);
    if (!['http:', 'https:'].includes(proxy.protocol) || proxy.pathname !== '/' || proxy.search || proxy.hash) throw new Error();
    const token = proxy.username || proxy.password
      ? `Basic ${Buffer.from(`${decodeURIComponent(proxy.username)}:${decodeURIComponent(proxy.password)}`).toString('base64')}` : undefined;
    return { key: proxy.href, uri: proxy.origin, ...(token ? { token } : {}) };
  } catch { throw new TypeError(`${setting} must be an HTTP proxy URL`); }
}

/** Tracking egress is opt-in per exact hostname; each route can use its own country. */
export function trackingProxyFetcher(env: Readonly<Record<string, string | undefined>>, fetcher: typeof fetch = fetch): typeof fetch | undefined {
  const routes = new Map<string, ReturnType<typeof proxySettings>>();
  const legacy = env.TRACKING_US_PROXY_URL?.trim();
  if (legacy) {
    const hosts = new Set((env.TRACKING_US_PROXY_HOSTS ?? '').split(',').map(host => host.trim().toLowerCase()).filter(Boolean));
    if (!hosts.size || [...hosts].some(host => !HOST.test(host))) throw new TypeError('TRACKING_US_PROXY_HOSTS must list the exact tracking hostnames');
    const settings = proxySettings(legacy, 'TRACKING_US_PROXY_URL');
    for (const host of hosts) routes.set(host, settings);
  }
  const configured = env.TRACKING_PROXY_ROUTES?.trim();
  if (configured) {
    let parsed: unknown;
    try { parsed = JSON.parse(configured); } catch { throw new TypeError('TRACKING_PROXY_ROUTES must map exact tracking hostnames to proxy URLs'); }
    if (!parsed || typeof parsed !== 'object' || Array.isArray(parsed)) throw new TypeError('TRACKING_PROXY_ROUTES must map exact tracking hostnames to proxy URLs');
    for (const [rawHost, rawProxy] of Object.entries(parsed)) {
      const host = rawHost.trim().toLowerCase();
      if (!HOST.test(host) || routes.has(host)) throw new TypeError('TRACKING_PROXY_ROUTES must use distinct exact tracking hostnames');
      if (typeof rawProxy !== 'string' || !rawProxy.trim()) throw new TypeError('TRACKING_PROXY_ROUTES must map exact tracking hostnames to proxy URLs');
      routes.set(host, proxySettings(rawProxy.trim(), 'TRACKING_PROXY_ROUTES proxy'));
    }
  }
  if (!routes.size) return undefined;
  const dispatchers = new Map<string, ProxyAgent>();
  return async (input, init) => {
    const url = new URL(input instanceof Request ? input.url : String(input));
    const route = routes.get(url.hostname.toLowerCase());
    if (!route) return fetcher(input, init);
    if (url.protocol !== 'https:') throw new TypeError('Proxied tracking requests require HTTPS');
    let dispatcher = dispatchers.get(route.key);
    if (!dispatcher) { dispatcher = new ProxyAgent({ uri: route.uri, ...(route.token ? { token: route.token } : {}) }); dispatchers.set(route.key, dispatcher); }
    return fetcher(input, { ...init, dispatcher } as RequestInit);
  };
}
