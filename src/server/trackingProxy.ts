import { ProxyAgent } from 'undici';

/** Tracking egress is opt-in per hostname; browser-service and other carrier requests stay direct. */
export function trackingProxyFetcher(env: Readonly<Record<string, string | undefined>>, fetcher: typeof fetch = fetch): typeof fetch | undefined {
  const raw = env.TRACKING_US_PROXY_URL?.trim();
  if (!raw) return undefined;
  const hosts = new Set((env.TRACKING_US_PROXY_HOSTS ?? '').split(',').map(host => host.trim().toLowerCase()).filter(Boolean));
  if (!hosts.size || [...hosts].some(host => !/^[a-z0-9]+(?:[.-][a-z0-9]+)*$/.test(host))) {
    throw new TypeError('TRACKING_US_PROXY_HOSTS must list the exact tracking hostnames');
  }
  let proxy: URL;
  try {
    proxy = new URL(raw);
    if (!['http:', 'https:'].includes(proxy.protocol) || proxy.pathname !== '/' || proxy.search || proxy.hash) throw new Error();
  } catch { throw new TypeError('TRACKING_US_PROXY_URL must be an HTTP proxy URL'); }
  const token = proxy.username || proxy.password
    ? `Basic ${Buffer.from(`${decodeURIComponent(proxy.username)}:${decodeURIComponent(proxy.password)}`).toString('base64')}` : undefined;
  const dispatcher = new ProxyAgent({ uri: proxy.origin, ...(token ? { token } : {}) });
  return async (input, init) => {
    const url = new URL(input instanceof Request ? input.url : String(input));
    if (!hosts.has(url.hostname.toLowerCase())) return fetcher(input, init);
    if (url.protocol !== 'https:') throw new TypeError('Proxied tracking requests require HTTPS');
    return fetcher(input, { ...init, dispatcher } as RequestInit);
  };
}
