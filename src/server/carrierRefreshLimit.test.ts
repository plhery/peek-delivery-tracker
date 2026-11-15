import { describe, expect, it, vi } from 'vitest';
import type { CarrierResult } from 'universal-parcel-scraper';
import type { SupabaseServiceClient } from './supabase';
import { TrackingSyncService } from './trackingSync';
import type { JsonObject } from './types';

// A carrier's catalog limit (tracking.refresh.minMinutes) counts from the parcel's last check, whether or not
// it also sets a wait after a failure: a check late in one clock hour is not followed by another as the next starts.

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

// Checked late in the 10:00 hour (a backlog), then a run at 11:00:30, 10.5 minutes later.
async function checks(carrier: string, lastSynced = '2026-09-09T10:50:00Z', now = '2026-09-09T11:00:30Z') {
  const parcel: JsonObject = { id: 'hourly', user_id: 'owner', carrier, tracking_number: 'TEST00000001', current_stage: 'in_transit',
    created_at: '2026-09-08T08:00:00Z', last_synced_at: lastSynced, sync_status: 'ok', archived_at: null,
    carrier_data: { last_update: '2026-09-09T09:00:00Z', routing: { version: 1, configured_carrier: carrier, last_event_at: '2026-09-09T09:00:00Z' } } };
  const adapter = { fetch: vi.fn(async (): Promise<CarrierResult> => ({ status: 'in_transit', current_stage: 'in_transit', last_update: '2026-09-09T09:00:00Z' })) };
  const summary = await new TrackingSyncService(client([parcel]) as unknown as SupabaseServiceClient, adapter, null, () => new Date(now)).sync();
  return summary.checked;
}

describe('a carrier asking for at most one check an hour', () => {
  it('Poste Italiane (a limit with a wait after failures) waits a full hour after a late check', async () => {
    expect(await checks('poste-italiane')).toBe(0);
    expect(await checks('poste-italiane', '2026-09-09T10:50:00Z', '2026-09-09T11:50:00Z')).toBe(1);
  });

  it('China Post is not checked twice inside one clock hour', async () => {
    expect(await checks('china-post', '2026-09-09T10:05:00Z', '2026-09-09T10:55:00Z')).toBe(0);
  });

  it('China Post (an hourly limit alone) waits a full hour after a late check', async () => {
    expect(await checks('china-post')).toBe(0);
    expect(await checks('china-post', '2026-09-09T10:50:00Z', '2026-09-09T11:50:00Z')).toBe(1);
  });

  it('Royal Mail (an hourly limit alone) likewise', async () => {
    expect(await checks('royal-mail')).toBe(0);
  });
});
