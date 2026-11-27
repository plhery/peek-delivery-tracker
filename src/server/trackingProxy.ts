import { subscribe } from 'node:diagnostics_channel';
import type { Socket } from 'node:net';
import { Dispatcher, getGlobalDispatcher, ProxyAgent } from 'undici';

const HOST = /^[a-z0-9]+(?:[.-][a-z0-9]+)*$/;
/** undici's error when the proxy answers the tunnel with another status than 200: it sent nothing to the host. */
const TUNNEL_REFUSED = /^Proxy response \((\d{3})\) !== 200 when HTTP Tunneling$/;

/**
 * The status the tracking proxy answered a request's tunnel with, when it refused it: a gateway error (502–504) when
 * its exit could not reach the host, 402 when the proxy account has run out of credit or traffic. Null otherwise.
 */
export function proxyTunnelStatus(error: unknown): number | null {
  for (let cause = error, depth = 0; cause && depth < 8; cause = (cause as { cause?: unknown }).cause, depth += 1) {
    const message = (cause as { message?: unknown }).message;
    const refused = typeof message === 'string' ? TUNNEL_REFUSED.exec(message) : null;
    if (refused) return Number(refused[1]);
  }
  return null;
}

function tunnelRefused(error: unknown): boolean {
  const status = proxyTunnelStatus(error);
  return status !== null && status >= 502 && status <= 504;
}

function proxySettings(raw: string, setting: string): { key: string; uri: string; token?: string } {
  try {
    const proxy = new URL(raw);
    if (!['http:', 'https:'].includes(proxy.protocol) || proxy.pathname !== '/' || proxy.search || proxy.hash) throw new Error();
    const token = proxy.username || proxy.password
      ? `Basic ${Buffer.from(`${decodeURIComponent(proxy.username)}:${decodeURIComponent(proxy.password)}`).toString('base64')}` : undefined;
    return { key: proxy.href, uri: proxy.origin, ...(token ? { token } : {}) };
  } catch { throw new TypeError(`${setting} must be an HTTP proxy URL`); }
}

/** Receives what a tunnel to a routed tracking host carried, both ways, once it closes. */
export type ProxyBytesListener = (host: string, bytes: number) => void;

/**
 * Counts each tunnel to a routed host on its socket to the proxy: the CONNECT exchange and the encrypted traffic
 * inside it, which is what a proxy billed by the byte counts. undici announces every tunnel a proxy accepts; a refused
 * one carried nothing to the host. A tunnel still open when the process ends goes uncounted.
 */
function meterTunnels(proxies: ReadonlySet<string>, hosts: ReadonlySet<string>, onBytes: ProxyBytesListener): void {
  subscribe('undici:proxy:connected', (message) => {
    const { socket, connectParams } = (message ?? {}) as { socket?: Socket; connectParams?: { origin?: unknown; path?: unknown } };
    const host = typeof connectParams?.path === 'string' ? connectParams.path.replace(/:\d+$/, '').toLowerCase() : '';
    if (!socket || !hosts.has(host) || !proxies.has(String(connectParams?.origin))) return;
    socket.once('close', () => {
      try { onBytes(host, socket.bytesRead + socket.bytesWritten); } catch { /* Telemetry never fails a lookup. */ }
    });
  });
}

/**
 * Tracking egress is opt-in per exact hostname; each route can use its own country. `onBytes` hears what each
 * tunnel carried.
 */
export function trackingProxyFetcher(env: Readonly<Record<string, string | undefined>>, fetcher: typeof fetch = fetch,
  onBytes?: ProxyBytesListener): typeof fetch | undefined {
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
  if (onBytes) meterTunnels(new Set([...routes.values()].map(route => route.uri)), new Set(routes.keys()), onBytes);
  const dispatchers = new Map<string, ProxyAgent>();
  function destination(origin: URL, direct: Dispatcher) {
    const route = routes.get(origin.hostname);
    if (!route) return direct;
    if (origin.protocol !== 'https:') throw new TypeError('Proxied tracking requests require HTTPS');
    let agent = dispatchers.get(route.key);
    if (!agent) {
      agent = new ProxyAgent({ uri: route.uri, token: route.token });
      dispatchers.set(route.key, agent);
    }
    return agent;
  }
  return async (input, init) => {
    const direct = (init as (RequestInit & { dispatcher?: Dispatcher }) | undefined)?.dispatcher ?? getGlobalDispatcher();
    let hops = 0;
    const dispatcher = {
      dispatch(options: Dispatcher.DispatchOptions, handler: Dispatcher.DispatchHandler) {
        hops++;
        return destination(new URL(String(options.origin)), direct).dispatch(options, handler);
      },
    };
    const proxied = () => fetcher(input, { ...init, dispatcher } as RequestInit);
    try {
      return await proxied();
    } catch (error) {
      // A proxy that answers the tunnel with a gateway error sent nothing to the host: it is asked
      // once more. A body that streams would not be there to send again.
      const replayable = !(input instanceof Request && input.body) && !(init?.body instanceof ReadableStream);
      if (hops > 1 || !replayable || init?.signal?.aborted || !tunnelRefused(error)) throw error;
      return proxied();
    }
  };
}
