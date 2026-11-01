import { describe, expect, it, vi } from 'vitest';
import { ProxyAgent } from 'undici';
import { trackingProxyFetcher } from './trackingProxy';

describe('tracking proxy scope', () => {
  it('uses a CONNECT proxy only for exact configured HTTPS hosts and preserves request options', async () => {
    const fetcher = vi.fn<typeof fetch>().mockResolvedValue(new Response('{}'));
    const proxy = trackingProxyFetcher({ TRACKING_US_PROXY_URL: 'http://example:fixture@proxy.example:8080',
      TRACKING_US_PROXY_HOSTS: 'tracking.example' }, fetcher)!;
    const signal = new AbortController().signal;
    await proxy('https://tracking.example/lookup', { signal, headers: { Accept: 'application/json' } });
    const options = fetcher.mock.calls[0]![1] as RequestInit & { dispatcher: ProxyAgent };
    expect(options).toMatchObject({ signal, headers: { Accept: 'application/json' } });
    expect(options.dispatcher).toBeInstanceOf(ProxyAgent);
    for (const url of ['https://tracking.example.evil.test/', 'http://browser-service:8191/', 'https://another.example/']) {
      await proxy(url, { signal });
      expect(fetcher.mock.lastCall?.[1]).toEqual({ signal });
    }
    await expect(proxy('http://tracking.example/lookup')).rejects.toThrow('require HTTPS');
    expect(fetcher).toHaveBeenCalledTimes(4);
    await options.dispatcher.close();
  });

  it('stays unconfigured without a proxy and refuses broad or malformed settings without echoing credentials', () => {
    expect(trackingProxyFetcher({})).toBeUndefined();
    expect(() => trackingProxyFetcher({ TRACKING_US_PROXY_URL: 'http://example:fixture@proxy.example', TRACKING_US_PROXY_HOSTS: '*' }))
      .toThrow('exact tracking hostnames');
    expect(() => trackingProxyFetcher({ TRACKING_US_PROXY_URL: 'http://example:fixture@proxy.example/private', TRACKING_US_PROXY_HOSTS: 'tracking.example' }))
      .toThrow(/^TRACKING_US_PROXY_URL must be an HTTP proxy URL$/);
  });
});
