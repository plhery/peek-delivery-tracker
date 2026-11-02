import 'server-only';

import { AdapterRegistry, TrawlClient, REGISTRY, type AdapterEnvironment } from 'universal-parcel-scraper/node';
import { reportTrackingBrowser } from './observability';
import { hostStepRecorder } from './stepRecorder';
import { trackingProxyFetcher } from './trackingProxy';

/**
 * The browser service's client, telling the running check when a call can open a browser: every call but a plain
 * HTTP request (`maxTier: 1`), which some adapters send through the service only for its network path. Out-for-delivery
 * checks run less often while the parcel's lookups open a browser (trackingSync.ts).
 */
export class HostTrawlClient extends TrawlClient {
  override scrape(...[request, options]: Parameters<TrawlClient['scrape']>): ReturnType<TrawlClient['scrape']> {
    if (request.maxTier !== 1) reportTrackingBrowser();
    return super.scrape(request, options);
  }

  override solve(...[url, options]: Parameters<TrawlClient['solve']>): ReturnType<TrawlClient['solve']> {
    reportTrackingBrowser();
    return super.solve(url, options);
  }
}

/**
 * The environment this server hands to every carrier adapter: the private
 * browser service and local Chromium when configured, the telemetry sinks,
 * and the process environment for provider-specific flags. Adapters never
 * read secrets; the environment carries only what the package documents.
 */
export function hostAdapterEnvironment(env: Record<string, string | undefined> = process.env): AdapterEnvironment {
  const ipv6Url = env.FEDEX_IPV6_TRAWL_URL?.trim(), trawlUrl = env.FLARESOLVERR_URL?.trim();
  return {
    fetcher: trackingProxyFetcher(env),
    trawlRoutes: ipv6Url ? { 'fedex-ipv6': new HostTrawlClient(ipv6Url) } : undefined,
    trawl: trawlUrl ? new HostTrawlClient(trawlUrl) : null,
    browserExecutablePath: env.TRACKING_CHROMIUM_PATH?.trim() || null,
    recorder: hostStepRecorder(),
    env,
  };
}

export function createAdapterRegistry(environment: AdapterEnvironment = hostAdapterEnvironment()): AdapterRegistry {
  return new AdapterRegistry(REGISTRY, environment);
}
