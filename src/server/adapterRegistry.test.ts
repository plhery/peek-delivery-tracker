import { describe, expect, it, vi } from 'vitest';
import { HostTrawlClient, hostAdapterEnvironment } from './adapterRegistry';
import { withTrackingAttempt } from './observability';

describe('browser route configuration', () => {
  it('builds the primary browser client, and no retry route without a retry worker', () => {
    const env = hostAdapterEnvironment({ FLARESOLVERR_URL: 'http://primary.test:8191' });
    expect(env.trawl?.scrapeUrl().hostname).toBe('primary.test');
    expect(env.trawl).toBeInstanceOf(HostTrawlClient);
    expect(env.trawlRoutes).toBeUndefined();
    expect(hostAdapterEnvironment({}).trawlRoutes).toBeUndefined();
    expect(hostAdapterEnvironment({}).trawl).toBeNull();
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

it('gives every environment of the process one fetcher per proxy setting, so lookups share the warmed DPD session', () => {
  const routes = { TRACKING_PROXY_ROUTES: JSON.stringify({ 'api.paketnavigator.de': 'http://proxy.test:8080' }) };
  const fetcher = hostAdapterEnvironment(routes).fetcher;
  expect(fetcher).toBeTypeOf('function');
  expect(hostAdapterEnvironment({ ...routes, FLARESOLVERR_URL: 'http://primary.test' }).fetcher).toBe(fetcher);
  expect(hostAdapterEnvironment({ TRACKING_PROXY_ROUTES: JSON.stringify({ 'api.paketnavigator.de': 'http://other.test:8080' }) }).fetcher)
    .not.toBe(fetcher);
  expect(hostAdapterEnvironment({}).fetcher).toBeUndefined();
  expect(() => hostAdapterEnvironment({ TRACKING_PROXY_ROUTES: '[]' })).toThrow(TypeError);
});

it('constructs an independent host-owned datacenter route for FedEx\'s retry, and no other', () => {
  const env = hostAdapterEnvironment({ FLARESOLVERR_URL: 'http://primary.test', FEDEX_DATACENTER_TRAWL_URL: 'http://datacenter.test:8192',
    FEDEX_RESIDENTIAL_TRAWL_URL: 'http://residential.test:8192' });
  expect(env.trawlRoutes?.['fedex-datacenter']).toBeInstanceOf(HostTrawlClient);
  expect(env.trawlRoutes?.['fedex-datacenter']?.scrapeUrl().hostname).toBe('datacenter.test');
  expect(env.trawlRoutes?.['fedex-datacenter']).not.toBe(env.trawl);
  expect(Object.keys(env.trawlRoutes ?? {})).toEqual(['fedex-datacenter']);
  expect(hostAdapterEnvironment({ FLARESOLVERR_URL: 'http://primary.test' }).trawlRoutes).toBeUndefined();
});
