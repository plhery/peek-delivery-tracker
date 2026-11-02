import { describe, expect, it, vi } from 'vitest';
import { REGISTRY, type AdapterEnvironment } from 'universal-parcel-scraper/node';
import { HostTrawlClient, createAdapterRegistry, hostAdapterEnvironment } from './adapterRegistry';
import { withTrackingAttempt } from './observability';

describe('proxy routes and adapter transports', () => {
  it('keeps Mondial Relay on its own transport under the proxy routes, and hands the others the routed fetcher', () => {
    const environment = hostAdapterEnvironment({ TRACKING_PROXY_ROUTES: JSON.stringify({ 'tracking.example': 'http://fixture:example@proxy.example:8080' }) });
    expect(environment.fetcher).toBeTypeOf('function');
    const received = new Map<string, AdapterEnvironment>();
    const factories = Object.fromEntries(Object.entries(REGISTRY.factories).map(([id, factory]) =>
      [id, (env: AdapterEnvironment) => { received.set(id, env); return factory(env); }]));
    const definition = { ...REGISTRY, factories };
    const registry = createAdapterRegistry(environment, definition);
    const other = Object.entries(REGISTRY.carriers).find(([, id]) => id && id !== 'universal' && id !== 'mondial-relay')!;
    registry.for('mondial-relay');
    registry.for(other[0]);
    expect(received.get('mondial-relay')?.fetcher).toBeUndefined();
    expect(received.get('mondial-relay')?.trawl).toBe(environment.trawl);
    expect(received.get(other[1]!)?.fetcher).toBe(environment.fetcher);

    received.clear();
    createAdapterRegistry(hostAdapterEnvironment({}), definition).for('mondial-relay');
    expect(received.get('mondial-relay')?.fetcher).toBeUndefined();
  });
});

describe('browser route configuration', () => {
  it('keeps the optional IPv6 worker independent of the primary browser client', () => {
    const env = hostAdapterEnvironment({ FLARESOLVERR_URL: 'http://primary.test:8191', FEDEX_IPV6_TRAWL_URL: 'http://ipv6.test:8192' });
    expect(env.trawl?.scrapeUrl().hostname).toBe('primary.test');
    expect(env.trawlRoutes?.['fedex-ipv6']?.scrapeUrl().hostname).toBe('ipv6.test');
    expect(env.trawlRoutes?.['fedex-ipv6']).not.toBe(env.trawl);
    expect(hostAdapterEnvironment({}).trawlRoutes).toBeUndefined();
    expect(hostAdapterEnvironment({}).trawl).toBeNull();
    expect(env.trawl).toBeInstanceOf(HostTrawlClient);
    expect(env.trawlRoutes?.['fedex-ipv6']).toBeInstanceOf(HostTrawlClient);
  });

  it('tells the check when a browser service call can open a browser, and not for a plain HTTP request', async () => {
    const client = new HostTrawlClient('http://primary.test:8191');
    const fetcher = vi.fn(async () => { throw new TypeError('offline'); });
    const options = { provider: 'Test', timeoutMs: 1_000, fetcher };
    const browser = vi.fn();
    const call = (operation: () => Promise<unknown>) => withTrackingAttempt('attempt', () => operation().catch(() => undefined),
      undefined, undefined, undefined, browser);
    await call(() => client.scrape({ url: 'https://carrier.test/api', maxTier: 1 }, options));
    expect(browser).not.toHaveBeenCalled();
    await call(() => client.scrape({ url: 'https://carrier.test/track' }, options));
    expect(browser).toHaveBeenCalledOnce();
    await call(() => client.scrape({ url: 'https://carrier.test/track', maxTier: 2 }, options));
    await call(() => client.solve('https://carrier.test/track', options));
    expect(browser).toHaveBeenCalledTimes(3);
    expect(fetcher).toHaveBeenCalled();
  });
});
