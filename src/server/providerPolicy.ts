import 'server-only';

import { BROWSER_SOURCES, UNIVERSAL_SOURCES } from 'universal-parcel-scraper';
import { captureProviderSetting, logOperationalEvent } from './observability';

/** A provider's name as the setting may spell it: whatever its case, spaces or hyphens. */
const spelled = (name: string) => name.toLowerCase().replace(/[^a-z0-9]/g, '');

/**
 * Every universal provider the scraper has: the default ones and those asked through a browser, Postal Ninja and
 * Track123 among them. Read from the scraper, so a provider it adds is known here without an app change.
 */
const PROVIDERS = [...new Set<string>([...UNIVERSAL_SOURCES, ...BROWSER_SOURCES])];
const KNOWN = new Set(PROVIDERS.map(spelled));

/**
 * Whether checks and the Add preflight may ask `provider`, a universal provider, under
 * `setting`, the value of `TRACKING_UNIVERSAL_PROVIDERS`: every provider when it is unset
 * or empty, none for `none`, else the ones it names, separated by commas (`ParcelsApp,
 * Ship24`). A name of no provider allows nobody, so a typo asks fewer providers, never more.
 */
export function providerAllowed(provider: string, setting: string | undefined): boolean {
  const names = (setting ?? '').split(',').map(spelled).filter(Boolean);
  return !setting?.trim() || names.includes(spelled(provider));
}

/** The names in `setting` that spell no provider, `none` aside, as written: each a provider the setting meant and turned off. */
export function unknownProviders(setting: string | undefined): string[] {
  return (setting ?? '').split(',').map((name) => name.trim())
    .filter((name) => name !== '' && spelled(name) !== 'none' && !KNOWN.has(spelled(name)));
}

const reportedKey = Symbol.for('peek.providerSettingReported');
const processScope = globalThis as typeof globalThis & { [reportedKey]?: true };

/**
 * At start, the names `TRACKING_UNIVERSAL_PROVIDERS` holds that spell no provider: logged, and reported to Sentry once
 * per process, with how many providers the setting still allows. The setting still allows only what it names. Nothing
 * else of it is logged or reported.
 */
export function reportProviderSetting(setting = process.env.TRACKING_UNIVERSAL_PROVIDERS): void {
  try {
    const unknown = unknownProviders(setting);
    if (!unknown.length || processScope[reportedKey]) return;
    processScope[reportedKey] = true;
    const allowed = PROVIDERS.filter((provider) => providerAllowed(provider, setting)).length;
    logOperationalEvent('provider_setting_unknown', { unknown: unknown.join(','), providers_allowed: allowed }, 'error');
    captureProviderSetting(unknown, allowed);
  } catch {
    // A warning about the setting must never stop the server.
  }
}

/**
 * The most lookups each universal provider is sent in an hour, by every server together: the allowance of its shared
 * lease (`acquire_tracking_provider`). The lease alone admits a lookup every five seconds, and a carrier's outage
 * sends its parcels to the providers together. The allowance holds half an hour of the limit, so a quiet provider takes
 * a burst and a busy one settles at its limit; a lookup it refuses asks the next provider.
 *
 * ParcelsApp refuses an address that asks it too much, for every carrier, and several carriers have no other source:
 * its limit keeps well under what it has refused. 17TRACK's, Track123's and Postal Ninja's lookups are browser reads,
 * the last two the slowest. Ship24 could be lost with the least harm and takes the most. UPU, the posts' own feed,
 * has none.
 */
export const PROVIDER_HOURLY_LIMITS: Readonly<Record<string, number>> = {
  Ship24: 180, '17TRACK': 120, ParcelsApp: 90, 'Postal Ninja': 40, Track123: 30,
};

/**
 * What a lookup states to `provider`'s hourly allowance: the limit, and the lookups it leaves in the allowance when nobody
 * waits for its answer (`background`), a third of what the allowance holds, for the Adds and refreshes someone does
 * wait for. Undefined for a provider without a limit.
 */
export function providerLimit(provider: string, background: boolean): { limit: number; reserve: number } | undefined {
  const limit = Object.hasOwn(PROVIDER_HOURLY_LIMITS, provider) ? PROVIDER_HOURLY_LIMITS[provider]! : undefined;
  return limit === undefined ? undefined : { limit, reserve: background ? Math.floor(limit / 6) : 0 };
}
