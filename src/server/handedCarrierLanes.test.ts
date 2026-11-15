import { describe, expect, it, vi } from 'vitest';
import type { CarrierResult } from 'universal-parcel-scraper';
import type { SupabaseServiceClient } from './supabase';
import { TrackingSyncService } from './trackingSync';
import type { JsonObject } from './types';

// Two checks of one run never ask one carrier at once (carriers on one adapter count as one), including the
// carrier a handoff moved a parcel to.
const at = () => new Date('2026-09-04T10:00:00Z');

function client(packages: JsonObject[]) {
  return {
    listActivePackages: vi.fn().mockResolvedValue(packages),
    getPackage: vi.fn(async (id: string) => packages.find((parcel) => parcel.id === id) ?? null),
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
    applyTrackingSync: vi.fn().mockResolvedValue(true),
  };
}

const parcel = (id: string, user: string, carrier: string, number: string, data?: JsonObject): JsonObject => ({
  id, user_id: user, one_off: false, carrier, tracking_number: number, created_at: '2026-09-03T09:00:00Z',
  last_synced_at: '2026-09-04T09:00:00Z', sync_status: 'ok', archived_at: null, ...(data ? { carrier_data: data } : {}) });

async function inFlightPeak(packages: JsonObject[]) {
  const inFlight = new Map<string, number>();
  const peak = new Map<string, number>();
  const adapter = { fetch: vi.fn(async (...[carrier]: [carrier: string, number: string]) => {
    inFlight.set(carrier, (inFlight.get(carrier) ?? 0) + 1);
    peak.set(carrier, Math.max(peak.get(carrier) ?? 0, inFlight.get(carrier)!));
    await new Promise((resolve) => setTimeout(resolve, 30));
    inFlight.set(carrier, inFlight.get(carrier)! - 1);
    return { status: 'in_transit', current_stage: 'in_transit', last_update: '2026-09-04T08:00:00Z' } as CarrierResult;
  }) };
  await new TrackingSyncService(client(packages) as unknown as SupabaseServiceClient, adapter, null, at).sync();
  return { peak, asked: adapter.fetch.mock.calls.map(([carrier, number]) => `${carrier} ${number}`) };
}

describe('two checks of one run and one carrier', () => {
  it('never asks La Poste at once for two parcels filed under it', async () => {
    const { peak } = await inFlightPeak([parcel('a', 'one', 'la-poste', 'TEST0001'), parcel('b', 'two', 'la-poste', 'TEST0002')]);
    expect(peak.get('la-poste')).toBe(1);
  });

  it('never asks La Poste at once for a parcel handed over to it and one filed under it', async () => {
    const handed = parcel('a', 'one', 'ctt', 'TEST0001', { original_carrier: 'ctt', original_tracking_number: 'TEST0001',
      active_tracking_carrier: 'la-poste', active_tracking_number: 'TEST0009', handed_over_at: '2026-08-01T09:00:00Z' });
    const { peak, asked } = await inFlightPeak([handed, parcel('b', 'two', 'la-poste', 'TEST0002')]);
    expect(asked).toContain('la-poste TEST0009');
    expect(asked).toContain('la-poste TEST0002');
    expect(peak.get('la-poste')).toBe(1);
  });
});
