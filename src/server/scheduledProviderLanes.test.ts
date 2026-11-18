import { afterEach, describe, expect, it, vi } from 'vitest';
import * as scraper from 'universal-parcel-scraper';
import type { CarrierResult } from 'universal-parcel-scraper';
import type { SupabaseServiceClient } from './supabase';
import { TrackingSyncService } from './trackingSync';
import type { JsonObject } from './types';

vi.mock(import('universal-parcel-scraper'), async (importOriginal) => ({ ...await importOriginal() }));
afterEach(() => vi.restoreAllMocks());

// Two checks of one scheduled run and the universal provider the parcels' plans ask first.
const at = () => new Date('2026-09-04T10:00:00Z');

function client(packages: JsonObject[]) {
  // acquire_tracking_provider: one call at a time per provider; a held lease answers no token
  // and retry_at = lease_until (90 s), as the SQL function does.
  const leases = new Set<string>();
  return {
    listActivePackages: vi.fn().mockResolvedValue(packages),
    getPackage: vi.fn(async (id: string) => packages.find((parcel) => parcel.id === id) ?? null),
    syncJobQueuedAhead: vi.fn().mockResolvedValue(null),
    listFollowedOneOffPackages: vi.fn().mockResolvedValue([]),
    listUnfiledOneOffPackages: vi.fn().mockResolvedValue([]),
    listSyncCandidates: vi.fn().mockResolvedValue([]),
    listUnwatchedPackageIds: vi.fn().mockResolvedValue([]),
    listWaitingPackages: vi.fn().mockResolvedValue(new Map()),
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
    acquireTrackingProvider: vi.fn(async (source: string) => {
      if (leases.has(source)) return { token: null, retry_at: new Date(at().getTime() + 90_000).toISOString() };
      leases.add(source);
      return { token: `lease-${source}`, retry_at: new Date(at().getTime() + 90_000).toISOString() };
    }),
    finishTrackingProvider: vi.fn(async (source: string) => { leases.delete(source); }),
  };
}

const parcel = (id: string, user: string, carrier: string, number: string): JsonObject => ({
  id, user_id: user, one_off: false, carrier, tracking_number: number, created_at: '2026-09-03T09:00:00Z',
  last_synced_at: '2026-09-04T09:00:00Z', sync_status: 'ok', archived_at: null, current_stage: 'in_transit' });

async function run(packages: JsonObject[], sources: string[] = ['ParcelsApp', '17TRACK']) {
  vi.spyOn(scraper, 'universalPlan').mockReturnValue({ sources: sources as never, carrier: null, tier: () => 'unknown', rank: () => 0 });
  const asked: string[] = [];
  const pending: Array<() => void> = [];
  const progress: CarrierResult = { status: 'in_transit', current_stage: 'in_transit', last_update: '2026-09-04T08:00:00Z',
    events: [{ time: '2026-09-04T08:00:00Z', description: 'In transit', stage: 'in_transit' }] };
  // Each provider call takes a while, as ParcelsApp's polling does.
  const adapter = { fetch: vi.fn(), fetchUniversal: vi.fn((source: string, number: string) => new Promise<CarrierResult>((resolve) => {
    asked.push(`${source} ${number}`);
    pending.push(() => resolve(structuredClone(progress)));
  })) };
  const fake = client(packages);
  const running = new TrackingSyncService(fake as unknown as SupabaseServiceClient, adapter, null, at).sync();
  // Answer every call once both checks had their chance to start.
  for (let i = 0; i < 20; i++) {
    await new Promise((resolve) => setTimeout(resolve, 5));
    while (pending.length) pending.shift()!();
  }
  const summary = await running;
  const saved = (id: string) => fake.applyTrackingSync.mock.calls.filter(([row]) => row.id === id).at(-1)?.[1] as JsonObject | undefined;
  return { asked, saved, summary };
}

describe('two checks of one run and a universal provider', () => {
  it('checks the parcels of one account one after the other, each answered by the first provider', async () => {
    const { asked, saved } = await run([parcel('a', 'same', '247express', 'TESTA0000001'), parcel('b', 'same', '139express', 'TESTB0000002')]);
    expect([...asked].sort()).toEqual(['ParcelsApp TESTA0000001', 'ParcelsApp TESTB0000002']);
    for (const id of ['a', 'b']) {
      expect(((saved(id)?.carrier_data as JsonObject)?.routing as JsonObject)?.failures ?? {}).not.toHaveProperty('ParcelsApp');
    }
  });

  it('never asks the provider two parcels with none named would ask first at once, so neither stores a rest it never gave', async () => {
    const { asked, saved } = await run([parcel('a', 'one', '247express', 'TESTA0000001'), parcel('b', 'two', '139express', 'TESTB0000002')]);
    const routing = (id: string) => (saved(id)?.carrier_data as JsonObject)?.routing as JsonObject;
    expect([...asked].sort()).toEqual(['ParcelsApp TESTA0000001', 'ParcelsApp TESTB0000002']);
    for (const id of ['a', 'b']) expect(routing(id)?.failures ?? {}).not.toHaveProperty('ParcelsApp');
  });

  it('answers both parcels after their preferred providers are disabled', async () => {
    vi.stubEnv('TRACKING_UNIVERSAL_PROVIDERS', 'Ship24');
    try {
      const { asked } = await run([
        { ...parcel('a', 'one', '247express', 'TESTA0000001'), carrier_data: { tracking_provider: '17TRACK' } },
        { ...parcel('b', 'two', '139express', 'TESTB0000002'), carrier_data: { tracking_provider: 'ParcelsApp' } },
      ], ['ParcelsApp', 'Ship24']);
      expect(asked.sort()).toEqual(['Ship24 TESTA0000001', 'Ship24 TESTB0000002']);
    } finally { vi.unstubAllEnvs(); }
  });

  it('answers both parcels when the plan holds one provider', async () => {
    const { asked, summary } = await run([parcel('a', 'one', '247express', 'TESTA0000001'), parcel('b', 'two', '139express', 'TESTB0000002')], ['ParcelsApp']);
    expect([...asked].sort()).toEqual(['ParcelsApp TESTA0000001', 'ParcelsApp TESTB0000002']);
    expect(summary).toMatchObject({ checked: 2, updated: 2 });
  });
});
