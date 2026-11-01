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

  it('keeps country dispatchers separate, reuses one route and preserves legacy US routing', async () => {
    const fetcher = vi.fn<typeof fetch>().mockResolvedValue(new Response('{}'));
    const proxy = trackingProxyFetcher({ TRACKING_US_PROXY_URL: 'http://fixture:example@us.example:8080', TRACKING_US_PROXY_HOSTS: 'us-tracking.example',
      TRACKING_PROXY_ROUTES: JSON.stringify({ 'np-tracking.example': 'http://fixture:example@np.example:8080',
        'ru-tracking.example': 'http://fixture:example@ru.example:8080', 'ru-details.example': 'http://fixture:example@ru.example:8080' }) }, fetcher)!;
    const agents: ProxyAgent[] = [];
    for (const host of ['us-tracking.example', 'np-tracking.example', 'ru-tracking.example', 'ru-details.example']) {
      await proxy(new Request(`https://${host}/lookup`));
      agents.push((fetcher.mock.lastCall![1] as RequestInit & { dispatcher: ProxyAgent }).dispatcher);
    }
    expect(new Set(agents.slice(0, 3)).size).toBe(3);
    expect(agents[2]).toBe(agents[3]);
    for (const agent of new Set(agents)) await agent.close();
  });

  it('supports country routes without legacy settings and rejects ambiguous or secret-bearing malformed configuration', async () => {
    expect(trackingProxyFetcher({ TRACKING_PROXY_ROUTES: '{}' })).toBeUndefined();
    for (const value of ['not-json', 'null', '[]', '{"*.example":"http://fixture:example@proxy.example"}',
      '{"tracking.example":4}', '{"tracking.example":"http://fixture:example@proxy.example/private"}',
      '{"tracking.example":"http://%zz:example@proxy.example"}',
      '{"tracking.example":"http://proxy.example","TRACKING.EXAMPLE":"http://other.example"}']) {
      expect(() => trackingProxyFetcher({ TRACKING_PROXY_ROUTES: value })).toThrow(/^TRACKING_PROXY_ROUTES /);
      try { trackingProxyFetcher({ TRACKING_PROXY_ROUTES: value }); } catch (error) { expect(String(error)).not.toContain('fixture:example'); }
    }
    expect(() => trackingProxyFetcher({ TRACKING_US_PROXY_URL: 'http://proxy.example', TRACKING_US_PROXY_HOSTS: 'tracking.example',
      TRACKING_PROXY_ROUTES: '{"tracking.example":"http://other.example"}' })).toThrow('distinct exact tracking hostnames');
    const fetcher = vi.fn<typeof fetch>().mockResolvedValue(new Response('{}'));
    const proxy = trackingProxyFetcher({ TRACKING_PROXY_ROUTES: '{"tracking.example":"http://proxy.example"}' }, fetcher)!;
    await proxy('https://tracking.example/');
    const agent = (fetcher.mock.lastCall![1] as RequestInit & { dispatcher: ProxyAgent }).dispatcher;
    expect(agent).toBeInstanceOf(ProxyAgent);
    await proxy('https://tracking.example.evil.test/');
    expect(fetcher.mock.lastCall![1]).toBeUndefined();
    await expect(proxy('http://tracking.example/')).rejects.toThrow('require HTTPS');
    await agent.close();
  });
});
