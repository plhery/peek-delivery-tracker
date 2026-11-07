import 'server-only';
import { AsyncLocalStorage } from 'node:async_hooks';
import { carrierAdapter } from './carriers';
import { logOperationalEvent, trackingAttemptId } from './observability';
import { serviceClient } from './runtime';
import type { SupabaseServiceClient } from './supabase';

type IncidentReader = Pick<SupabaseServiceClient, 'trackingProviderIncidentOpen'>;
interface FiledParcel {
  carrier: string;
  client: IncidentReader;
  signal?: AbortSignal;
}
// The API and instrumentation worker can evaluate separate copies of this module.
const key = Symbol.for('peek.royalBrowserAdmission');
const processScope = globalThis as typeof globalThis & { [key]?: AsyncLocalStorage<FiledParcel> };
const filed = processScope[key] ??= new AsyncLocalStorage<FiledParcel>();

/** Keep the filed carrier even when routing asks a different carrier or saved delivery leg. */
export function withRoyalBrowserAdmission<T>(carrier: string, client: IncidentReader, operation: () => T, signal?: AbortSignal): T {
  return filed.run({ carrier, client, signal }, operation);
}

export class RoyalBrowserPaused extends Error {
  constructor() { super('Royal Mail browser is reserved for Royal Mail parcels during its provider incident'); this.name = 'RoyalBrowserPaused'; }
  static [Symbol.hasInstance](value: unknown): value is RoyalBrowserPaused {
    return value instanceof Error && value.name === 'RoyalBrowserPaused';
  }
}

/** Parcelforce uses the same Royal Mail browser and home connection. */
export function usesRoyalBrowser(carrier: string): boolean {
  return carrierAdapter(carrier) === 'royal-mail';
}

export async function royalBrowserAllowed(carrier: string, phase: 'tracking' | 'recognition' = 'tracking',
  context: { signal?: AbortSignal; budgetMs?: number } = {}): Promise<boolean> {
  if (!usesRoyalBrowser(carrier)) return true;
  const parcel = filed.getStore();
  const signal = context.signal ?? parcel?.signal;
  signal?.throwIfAborted();
  if (phase === 'tracking' && parcel?.carrier === 'royal-mail') return true;
  let reason = 'incident_open';
  try {
    const client = parcel?.client ?? serviceClient();
    if (!client) throw new Error('Incident state is unavailable');
    if (!await client.trackingProviderIncidentOpen('royal-mail', {
      signal, timeoutMs: Math.max(1, Math.min(1_000, context.budgetMs ?? 1_000)),
    })) {
      signal?.throwIfAborted();
      return true;
    }
  } catch {
    signal?.throwIfAborted();
    // Keep the optional traffic paused if state cannot be read; filed Royal checks can still recover it.
    reason = 'incident_unavailable';
  }
  signal?.throwIfAborted();
  logOperationalEvent('tracking_browser_paused', {
    carrier, filed_carrier: parcel?.carrier ?? null, phase, reason, attempt_id: trackingAttemptId() ?? null,
  });
  return false;
}

export async function requireRoyalBrowser(carrier: string, phase: 'tracking' | 'recognition',
  context: { signal?: AbortSignal; budgetMs?: number } = {}): Promise<void> {
  if (!await royalBrowserAllowed(carrier, phase, context)) throw new RoyalBrowserPaused();
}
