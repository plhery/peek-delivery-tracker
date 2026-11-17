// A kept expected day is judged on the clock of the carrier delivering the parcel. US carriers name no zone in the catalog
// (several zones), and after an earlier leg (Cainiao before GOFO) answered, the newest scan states that leg's +08:00.
import { afterEach, describe, expect, it, vi } from 'vitest';
import type { CarrierResult } from 'universal-parcel-scraper';
import * as scraper from 'universal-parcel-scraper';
import { STORED_EVENT_IDENTITIES, type SupabaseServiceClient } from './supabase';
import { TrackingSyncService } from './trackingSync';
import type { JsonObject } from './types';

vi.mock(import('universal-parcel-scraper'), async importOriginal => ({ ...await importOriginal() }));
afterEach(() => vi.restoreAllMocks());

function client() {
  const updatePackage = vi.fn().mockResolvedValue(undefined);
  return {
    listActivePackages: vi.fn().mockResolvedValue([]), getPackage: vi.fn().mockResolvedValue(null),
    syncJobQueuedAhead: vi.fn().mockResolvedValue(null), listFollowedOneOffPackages: vi.fn().mockResolvedValue([]),
    listUnfiledOneOffPackages: vi.fn().mockResolvedValue([]), listSyncCandidates: vi.fn().mockResolvedValue([]),
    listUnwatchedPackageIds: vi.fn().mockResolvedValue([]), listWaitingPackages: vi.fn().mockResolvedValue(new Map()), sharedAlertPackageIds: vi.fn().mockResolvedValue(new Set()),
    autoLinkPackages: vi.fn().mockResolvedValue(0), reopenScanAnnouncements: vi.fn(async (_id: string, ids: string[]) => ids.length),
    updatePackage, insertEvents: vi.fn().mockResolvedValue(undefined), deleteEventsByDescriptions: vi.fn().mockResolvedValue(undefined),
    startSyncAttempt: vi.fn().mockResolvedValue(undefined), completeSyncAttempt: vi.fn().mockResolvedValue(true),
    recordTrackingHealth: vi.fn().mockResolvedValue([]), trackingProviderIncidentOpen: vi.fn().mockResolvedValue(false),
    ackTrackingHealth: vi.fn().mockResolvedValue(undefined), previousThinAnswer: vi.fn().mockResolvedValue(null),
    recordTrackingStatusObservations: vi.fn().mockResolvedValue(undefined), closeTrackingStatusObservations: vi.fn().mockResolvedValue(1),
    acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: '' }),
    finishTrackingProvider: vi.fn().mockResolvedValue(undefined),
    applyTrackingSync: vi.fn(async (_parcel: JsonObject, values: JsonObject) => { await updatePackage(_parcel.id, values); return true; }),
  };
}

const CARRIER = 'gofo';
// The stored state: GOFO gave 13 Nov; the last direct answer was Cainiao's earlier leg (newest scan stated at +08:00).
const scan = { time: '2026-11-12T22:00:00+08:00', description: 'Departed facility', stage: 'in_transit' };
const stored = (data: JsonObject): JsonObject => ({
  id: 'earlier-leg', carrier: CARRIER, tracking_number: 'GFUS01000000000000001', current_stage: 'in_transit',
  created_at: '2026-11-05T09:00:00Z', expected_delivery: '2026-11-13',
  carrier_data: { last_update: scan.time, ...data },
});
// GOFO is down; a provider relays the same scan, without a day (the day is judged on the stored carrier_data).
const relayed: CarrierResult = { status: 'in_transit', current_stage: 'in_transit', last_status_text: scan.description,
  last_update: scan.time, events: [scan] };

async function check(parcel: JsonObject, at: string, rows: JsonObject[] = []): Promise<JsonObject> {
  vi.spyOn(scraper, 'universalPlan').mockReturnValue({ sources: ['ParcelsApp'], carrier: null, tier: () => 'unknown', rank: () => 0 });
  const db = client();
  const adapter = { fetch: vi.fn(async () => { throw new Error('down'); }), fetchUniversal: vi.fn().mockResolvedValue(relayed) };
  await new TrackingSyncService(db as unknown as SupabaseServiceClient, adapter, null, () => new Date(at))
    .syncPackage({ ...parcel, [STORED_EVENT_IDENTITIES]: rows });
  return { ...parcel, ...db.applyTrackingSync.mock.calls.at(-1)![1] } as JsonObject;
}

// 17:00 UTC on the 13th: 09:00 in Los Angeles, 12:00 in New York (the day is not over), already the 14th at +08:00.
const AFTERNOON = '2026-11-13T17:00:00Z';

describe('a kept expected day after an earlier leg answered directly', () => {
  it('drops the day once it has passed on the US carrier\'s own clock', async () => {
    expect((await check(stored({ last_update: '2026-11-12T22:00:00-08:00' }), '2026-11-14T09:00:00Z')).expected_delivery).toBeNull();
  });

  it('keeps the day all afternoon with GOFO\'s own newest scan (-08:00)', async () => {
    expect((await check(stored({ last_update: '2026-11-12T22:00:00-08:00' }), AFTERNOON)).expected_delivery).toBe('2026-11-13');
  });

  it('keeps the day in the morning after Cainiao answered', async () => {
    expect((await check(stored({ active_tracking_carrier: 'cainiao' }), '2026-11-13T10:00:00Z')).expected_delivery).toBe('2026-11-13');
  });

  it('keeps the day through the afternoon after Cainiao answered, not judged on its +08:00', async () => {
    expect((await check(stored({ active_tracking_carrier: 'cainiao' }), AFTERNOON)).expected_delivery).toBe('2026-11-13');
  });

  it('judges the day on the offset of GOFO\'s own newest stored scan after Cainiao answered', async () => {
    // 06:00 UTC on the 14th: 22:00 on the 13th in Los Angeles.
    const own = { provider_event_id: 'gofo:sorted', occurred_at: '2026-11-12T18:00:00+00:00', stage: 'in_transit',
      description: 'Sorted', location: null, time: '2026-11-12T10:00:00-08:00' };
    expect((await check(stored({ active_tracking_carrier: 'cainiao' }), '2026-11-14T06:00:00Z', [own])).expected_delivery).toBe('2026-11-13');
    expect((await check(stored({ active_tracking_carrier: 'cainiao' }), '2026-11-14T06:00:00Z')).expected_delivery).toBeNull();
  });
});
