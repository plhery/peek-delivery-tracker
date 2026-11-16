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

// Every parcel of such a carrier came due at once as each of its intervals opened, so a run asked it for all of them
// back to back and it answered the later ones with 429s.
describe('a carrier that spaces its lookups out', () => {
  it('has each number\'s interval open at a minute of its own, so its parcels come apart', async () => {
    // Two numbers whose half hours open at :01/:31 and :28/:58, both last checked as the 09:30 half hour opened.
    const parcels: JsonObject[] = ['TEST0002', 'TEST0003'].map((number, i) => ({ id: `p${i}`, user_id: `owner${i}`, carrier: 'aliexpress',
      tracking_number: number, current_stage: 'in_transit', created_at: '2026-09-08T08:00:00Z', last_synced_at: '2026-09-09T09:30:20Z',
      sync_status: 'ok', archived_at: null, carrier_data: { last_update: '2026-09-09T09:00:00Z',
        routing: { version: 1, configured_carrier: 'aliexpress', last_event_at: '2026-09-09T09:00:00Z' } } }));
    const asked: Record<string, string[]> = {};
    // A run every 2 minutes, from 10:00:30 to 11:00:30.
    for (let minute = 0; minute <= 60; minute += 2) {
      const now = new Date(Date.parse('2026-09-09T10:00:30Z') + minute * 60_000);
      const adapter = { fetch: vi.fn(async (): Promise<CarrierResult> => ({ status: 'in_transit', current_stage: 'in_transit', last_update: '2026-09-09T09:00:00Z' })) };
      await new TrackingSyncService(client(parcels) as unknown as SupabaseServiceClient, adapter, null, () => now).sync();
      for (const [, number] of adapter.fetch.mock.calls as unknown as Array<[string, string]>) {
        (asked[number] ??= []).push(now.toISOString().slice(11, 19));
        parcels.find((parcel) => parcel.tracking_number === number)!.last_synced_at = now.toISOString();
      }
    }
    // Both catch up in the first run. TEST0003 then keeps to its own minute. TEST0002's half hour opens just after
    // that check, so it waits out the carrier's interval (less a run's spread) until its own minute comes round.
    // Before, both were asked at 10:00:30, 10:30:30 and 11:00:30.
    expect(asked).toEqual({ TEST0002: ['10:00:30', '10:26:30', '10:52:30'], TEST0003: ['10:00:30', '10:28:30', '10:58:30'] });
  });
});
