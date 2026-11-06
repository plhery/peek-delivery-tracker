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

it('constructs an independent host-owned residential route', () => {
  const env = hostAdapterEnvironment({ FLARESOLVERR_URL: 'http://primary.test', FEDEX_RESIDENTIAL_TRAWL_URL: 'http://residential.test:8192' });
  expect(env.trawlRoutes?.['fedex-residential']).toBeInstanceOf(HostTrawlClient);
  expect(env.trawlRoutes?.['fedex-residential']?.scrapeUrl().hostname).toBe('residential.test');
  expect(env.trawlRoutes?.['fedex-residential']).not.toBe(env.trawl);
});

it('adds the datacenter route beside the residential one, and keeps a trial only with both routes and a name', () => {
  const env = hostAdapterEnvironment({ FLARESOLVERR_URL: 'http://primary.test', FEDEX_DATACENTER_TRAWL_URL: 'http://datacenter.test:8192' });
  expect(env.trawlRoutes?.['fedex-datacenter']?.scrapeUrl().hostname).toBe('datacenter.test');
  expect(env.trawlRoutes?.['fedex-residential']).toBeUndefined();
  expect(env.retryTrialStores).toBeUndefined();
  const both = hostAdapterEnvironment({ FEDEX_DATACENTER_TRAWL_URL: 'http://datacenter.test:8192', FEDEX_RESIDENTIAL_TRAWL_URL: 'http://residential.test:8192' });
  expect(Object.keys(both.trawlRoutes ?? {}).sort()).toEqual(['fedex-datacenter', 'fedex-residential']);
  // Without a database the trial has nowhere to keep its limits, so the scraper sends every retry to the datacenter route.
  expect(hostAdapterEnvironment({ FEDEX_DATACENTER_TRAWL_URL: 'http://datacenter.test', FEDEX_RESIDENTIAL_TRAWL_URL: 'http://residential.test',
    FEDEX_RETRY_TRIAL: 'example' }).retryTrialStores).toBeUndefined();
});
