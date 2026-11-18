import { describe, expect, it, vi } from 'vitest';
import { getGlobalDispatcher, MockAgent, ProxyAgent, type Dispatcher } from 'undici';
import { proxyTunnelStatus, trackingProxyFetcher } from './trackingProxy';

describe('tracking proxy scope', () => {
  it('uses a CONNECT proxy only for exact configured HTTPS hosts and preserves request options', async () => {
    const fetcher = vi.fn<typeof fetch>().mockResolvedValue(new Response('{}'));
    const proxy = trackingProxyFetcher({ TRACKING_US_PROXY_URL: 'http://example:fixture@proxy.example:8080',
      TRACKING_US_PROXY_HOSTS: 'tracking.example' }, fetcher)!;
    const signal = new AbortController().signal;
    await proxy('https://tracking.example/lookup', { signal, headers: { Accept: 'application/json' } });
    const options = fetcher.mock.calls[0]![1] as RequestInit & { dispatcher: ProxyAgent };
    expect(options).toMatchObject({ signal, headers: { Accept: 'application/json' } });
    const direct = vi.spyOn(getGlobalDispatcher(), 'dispatch').mockReturnValue(true);
    const via = vi.spyOn(ProxyAgent.prototype, 'dispatch').mockReturnValue(true);
    try {
      const dispatch = (origin: string) => options.dispatcher.dispatch({ origin, path: '/', method: 'GET' }, {});
      dispatch('https://another.example');
      dispatch('https://tracking.example');
      expect(direct).toHaveBeenCalledTimes(1);
      expect(via).toHaveBeenCalledTimes(1);
      expect(() => dispatch('http://tracking.example')).toThrow('require HTTPS');
    } finally { direct.mockRestore(); via.mockRestore(); }
  });

  it('follows a redirect from direct to proxied egress without forwarding authorization', async () => {
    const direct = new MockAgent();
    const proxied = new MockAgent();
    direct.disableNetConnect();
    proxied.disableNetConnect();
    direct.get('https://direct.example').intercept({ path: '/' })
      .reply(302, '', { headers: { location: 'https://carrier.example/track' } });
    let arrived: unknown;
    proxied.get('https://carrier.example').intercept({ path: '/track' }).reply(options => {
      arrived = new Headers(options.headers as HeadersInit).get('authorization');
      return { statusCode: 200, data: 'arrived' };
    });
    const dispatch = vi.spyOn(ProxyAgent.prototype, 'dispatch').mockImplementation(proxied.dispatch.bind(proxied));
    try {
      const tracked = trackingProxyFetcher({ TRACKING_PROXY_ROUTES: '{"carrier.example":"http://proxy.example"}' })!;
      const response = await tracked('https://direct.example', { headers: { authorization: 'Fixture' }, dispatcher: direct } as RequestInit);
      expect(await response.text()).toBe('arrived');
      expect(arrived).toBeNull();
      expect(dispatch).toHaveBeenCalledTimes(1);
    } finally {
      dispatch.mockRestore();
      await Promise.all([direct.close(), proxied.close()]);
    }
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
    const sent = vi.spyOn(ProxyAgent.prototype, 'dispatch').mockReturnValue(true);
    try {
      await proxy('https://direct.example');
      const options = fetcher.mock.lastCall![1] as RequestInit & { dispatcher: Dispatcher };
      for (const host of ['us-tracking.example', 'np-tracking.example', 'ru-tracking.example', 'ru-details.example']) {
        options.dispatcher.dispatch({ origin: `https://${host}`, method: 'GET', path: '/' }, {});
      }
      const agents = sent.mock.contexts;
      expect(new Set(agents.slice(0, 3)).size).toBe(3);
      expect(agents[2]).toBe(agents[3]);
    } finally { sent.mockRestore(); }
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
    expect(fetcher.mock.lastCall![1]).toHaveProperty('dispatcher');
  });

  it('asks the proxy once more when it refuses the tunnel with a gateway error, and only then', async () => {
    // undici's error chain for a proxy whose exit could not reach the host.
    const refused = (status: number) => Object.assign(new TypeError('fetch failed'), { cause: Object.assign(new Error('Request was cancelled.'),
      { cause: Object.assign(new Error(`Proxy response (${status}) !== 200 when HTTP Tunneling`), { name: 'AbortError', code: 'UND_ERR_ABORTED' }) }) });
    const route = { TRACKING_PROXY_ROUTES: '{"tracking.example":"http://proxy.example"}' };
    const run = async (failures: unknown[], url = 'https://tracking.example/lookup', init?: RequestInit) => {
      const fetcher = vi.fn<typeof fetch>(async () => {
        const failure = failures.shift();
        if (failure) throw failure;
        return new Response('{}');
      });
      const outcome = await trackingProxyFetcher(route, fetcher)!(url, init).then((response) => response.status, (error: unknown) => error);
      return { outcome, calls: fetcher.mock.calls.length };
    };
    for (const status of [502, 503, 504]) expect(await run([refused(status)])).toEqual({ outcome: 200, calls: 2 });
    expect([refused(402), refused(502), new TypeError('fetch failed')].map(proxyTunnelStatus)).toEqual([402, 502, null]);
    // Out of credit: the proxy would refuse again.
    expect((await run([refused(402)])).calls).toBe(1);
    // Once: a second refusal is the lookup's failure.
    const twice = await run([refused(502), refused(502)]);
    expect(twice.calls).toBe(2);
    expect(twice.outcome).toBeInstanceOf(TypeError);
    // Not a refusal of the proxy's own (its credentials), another failure, a lookup cut short, or a streamed body.
    expect((await run([refused(407)])).calls).toBe(1);
    expect((await run([new TypeError('fetch failed')])).calls).toBe(1);
    const aborted = new AbortController();
    aborted.abort();
    expect((await run([refused(502)], undefined, { signal: aborted.signal })).calls).toBe(1);
    expect((await run([refused(502)], undefined, { method: 'POST', body: new ReadableStream(), duplex: 'half' } as RequestInit)).calls).toBe(1);
    expect((await run([refused(502)], undefined, { method: 'POST', body: '{}' })).calls).toBe(2);
  });
});
