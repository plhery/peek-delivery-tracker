import { describe, expect, it } from 'vitest';
import { hostAdapterEnvironment } from './adapterRegistry';

describe('browser route configuration', () => {
  it('keeps the optional IPv6 worker independent of the primary browser client', () => {
    const env = hostAdapterEnvironment({ FLARESOLVERR_URL: 'http://primary.test:8191', FEDEX_IPV6_TRAWL_URL: 'http://ipv6.test:8192' });
    expect(env.trawl?.scrapeUrl().hostname).toBe('primary.test');
    expect(env.trawlRoutes?.['fedex-ipv6']?.scrapeUrl().hostname).toBe('ipv6.test');
    expect(env.trawlRoutes?.['fedex-ipv6']).not.toBe(env.trawl);
    expect(hostAdapterEnvironment({}).trawlRoutes).toBeUndefined();
  });
});
