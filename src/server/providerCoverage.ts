import 'server-only';

import type { ObservedCoverage, UniversalSource } from 'universal-parcel-scraper';
import { errorType, logOperationalEvent } from './observability';
import type { SupabaseServiceClient } from './supabase';

/** The counts cover two weeks of checks, so an hour changes little: they are read again that often. */
const FRESH_MS = 60 * 60_000;
/** Counts that could not be read again are kept this long, then the scraper's own references order the providers. */
const KEPT_MS = 24 * 60 * 60_000;
/** A read that failed is tried again this long after: every check asks for the counts. */
const RETRY_MS = 5 * 60_000;

let known: { at: number; counts: ReadonlyMap<string, Record<string, ObservedCoverage>> } | undefined;
let reading: Promise<void> | undefined;
let tried = -Infinity;

/**
 * Reads what each universal provider had for each carrier's parcels (`provider_coverage`), at most once an hour per
 * server, so the checks routed next order the providers by it (`observedCoverage`). No check waits for it: the
 * counts read last are used meanwhile, and before a server's first read parcels are routed by the scraper's
 * references. The promise ends with the read and never rejects.
 */
export function loadProviderCoverage(client: Pick<SupabaseServiceClient, 'providerCoverage'>, now = Date.now()): Promise<void> {
  if (reading) return reading;
  if ((known && now - known.at < FRESH_MS) || now - tried < RETRY_MS) return Promise.resolve();
  tried = now;
  reading = Promise.resolve().then(() => client.providerCoverage()).then(
    (counts) => { if (counts instanceof Map) known = { at: now, counts }; },
    (error: unknown) => {
      logOperationalEvent('provider_coverage_unread', { error_type: errorType(error) }, 'warning');
      if (known && now - known.at >= KEPT_MS) known = undefined;
    },
  ).finally(() => { reading = undefined; });
  return reading;
}

/** Forgets the counts read, for tests. */
export function forgetProviderCoverage(): void {
  known = undefined;
  reading = undefined;
  tried = -Infinity;
}

/**
 * What the checks counted of `carrier`'s parcels at each provider, for the scraper's plan (`observed`): it grades a
 * provider from ten parcels on, so the providers that had most of a carrier's parcels share them, and one that had
 * none is no longer asked about that carrier. Undefined before the first read and for a carrier without counts.
 */
export function observedCoverage(carrier: string): Partial<Record<UniversalSource, ObservedCoverage>> | undefined {
  return known?.counts.get(carrier);
}
