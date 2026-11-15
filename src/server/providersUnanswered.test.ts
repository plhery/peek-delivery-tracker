// A universal provider that was busy, refused the guest request, spent its quota or could not reach the carrier
// did not answer: a parcel tracked only through providers says it could not be checked, not that the carrier has
// no new scans.
import { afterEach, describe, expect, it, vi } from 'vitest';
import { IndeterminateError, NoHistoryError, TransportError } from 'universal-parcel-scraper';
import { TrackingSyncService } from './trackingSync';
import type { SupabaseServiceClient } from './supabase';
import type { JsonObject } from './types';

vi.mock(import('universal-parcel-scraper'), async importOriginal => ({ ...await importOriginal() }));
afterEach(() => vi.restoreAllMocks());

function fakeClient() {
  const client = {
    listActivePackages: vi.fn().mockResolvedValue([]),
    getPackage: vi.fn(async (): Promise<JsonObject | null> => null),
    syncJobQueuedAhead: vi.fn().mockResolvedValue(null),
    listFollowedOneOffPackages: vi.fn().mockResolvedValue([]),
    listUnfiledOneOffPackages: vi.fn().mockResolvedValue([]),
    listSyncCandidates: vi.fn().mockResolvedValue([]),
    listUnwatchedPackageIds: vi.fn().mockResolvedValue([]),
    sharedAlertPackageIds: vi.fn().mockResolvedValue(new Set()),
    autoLinkPackages: vi.fn().mockResolvedValue(0),
    reopenScanAnnouncements: vi.fn(async (_id: string, ids: string[]) => ids.length),
    updatePackage: vi.fn().mockResolvedValue(undefined),
    insertEvents: vi.fn().mockResolvedValue(undefined),
    deleteEventsByDescriptions: vi.fn().mockResolvedValue(undefined),
    startSyncAttempt: vi.fn().mockResolvedValue(undefined),
    completeSyncAttempt: vi.fn().mockResolvedValue(true),
    recordTrackingHealth: vi.fn().mockResolvedValue([]),
    trackingProviderIncidentOpen: vi.fn().mockResolvedValue(false),
    ackTrackingHealth: vi.fn().mockResolvedValue(undefined),
    previousThinAnswer: vi.fn().mockResolvedValue(null),
    recordTrackingStatusObservations: vi.fn().mockResolvedValue(undefined),
    closeTrackingStatusObservations: vi.fn().mockResolvedValue(1),
    acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: '2026-09-10T12:01:30Z' }),
    finishTrackingProvider: vi.fn().mockResolvedValue(undefined),
  };
  return { ...client, applyTrackingSync: vi.fn(async (parcel: JsonObject, values: JsonObject) => {
    await client.updatePackage(parcel.id, values); return true;
  }) };
}

// A parcel tracked only through providers, with scans, whose last two checks missed.
const parcel = (data: JsonObject = {}): JsonObject => ({ id: 'providers-only', carrier: 'unknown', tracking_number: 'TEST1234',
  current_stage: 'in_transit', last_synced_at: '2026-09-10T10:00:00Z',
  carrier_data: { ...data, routing: { version: 1, configured_carrier: 'unknown', consecutive_failures: 2,
    last_success_at: '2026-09-08T12:00:00Z', last_event_at: '2026-09-08T08:00:00Z', failures: {}, probe_cursor: 0, discovery_cursor: 0 } } });

async function check(failure: (source: string) => Error, data?: JsonObject) {
  const client = fakeClient();
  const adapter = { fetch: vi.fn(), fetchUniversal: vi.fn(async (source: string) => { throw failure(source); }) };
  const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => new Date('2026-09-10T12:00:00Z'));
  await service.syncPackage(parcel(data), { trigger: 'scheduled' });
  expect(adapter.fetchUniversal).toHaveBeenCalled();
  const saved = client.updatePackage.mock.lastCall![1] as JsonObject;
  return { sync_status: saved.sync_status, sync_error: saved.sync_error,
    kinds: Object.fromEntries(Object.entries((saved.carrier_data as { routing: { failures: Record<string, { kind: string }> } }).routing.failures)
      .map(([source, value]) => [source, value.kind])) };
}

// The providers' own words when they do not answer (universal-parcel-scraper providers/*/adapter.ts).
const outages: Record<string, (source: string) => Error> = {
  'Track718 busy': (source) => new IndeterminateError(source, `${source} is temporarily busy`),
  'Track123 guest refused': (source) => new IndeterminateError(source, 'The guest tracking request was refused', { reason: 'guest_refused' }),
  'ParcelsApp carrier lookup failed': (source) => new IndeterminateError(source, "ParcelsApp's carrier lookup failed: timeout", { reason: 'carrier_unavailable' }),
  '17TRACK USPS quota': (source) => new IndeterminateError(source, '17TRACK has spent its USPS lookups for this address', { reason: 'usps_quota' }),
};

describe('"no new scans" only for sources that answered', () => {
  it('says no new scans when every source answered without history', async () => {
    const answered = await check((source) => new NoHistoryError(source, `${source} has no usable shipment history`));
    expect(answered.sync_error).toBe('carrier:no_new_scans');
  });
  it('keeps the outage copy after a transport failure', async () => {
    const down = await check((source) => new TransportError(source, 'socket hang up'));
    expect(down.sync_error).not.toBe('carrier:no_new_scans');
    expect(down.sync_status).toBe('error');
  });
  it.each(Object.keys(outages))('does not say no new scans when every provider fails with "%s"', async (name) => {
    const result = await check(outages[name]!);
    expect(Object.values(result.kinds)).not.toContain('no_history');
    expect(result.sync_status).toBe('error');
    expect(result.sync_error).not.toBe('carrier:no_new_scans');
  });
  it('does not say no new scans when each provider fails in its own words', async () => {
    const own: Record<string, (source: string) => Error> = {
      ParcelsApp: outages['ParcelsApp carrier lookup failed']!,
      Ship24: (source) => new IndeterminateError(source, 'Ship24 has not queried the carriers for the shipment yet'),
      '17TRACK': outages['17TRACK USPS quota']!,
    };
    const result = await check((source) => (own[source] ?? outages['Track718 busy']!)(source));
    expect(result.sync_error).not.toBe('carrier:no_new_scans');
  });
  it('shows the outage beside a listed delivery probe', async () => {
    const probe = { at: '2026-09-10T11:50:00Z', origin_update: null, carrier: 'ups', number: '1ZTEST0000000000', listed: true };
    const result = await check(outages['Track123 guest refused']!, { delivery_probe: probe });
    expect(result.sync_status).toBe('error');
  });
});
