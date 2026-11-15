import { describe, expect, it, vi } from 'vitest';
import type { CarrierResult } from 'universal-parcel-scraper';
import type { SupabaseServiceClient } from './supabase';
import { TrackingSyncService } from './trackingSync';
import type { JsonObject } from './types';

// A parcel merges with its other leg before its news goes out, so the merged parcel announces its newest scan once.
// With two checks at once, the dispatch after one check's merge must not send the news the other check has just
// stored, before that check's own merge.
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

const parcel = (id: string, user: string, carrier: string, number: string): JsonObject => ({
  id, user_id: user, one_off: false, carrier, tracking_number: number, created_at: '2026-09-03T09:00:00Z',
  last_synced_at: '2026-09-04T09:00:00Z', sync_status: 'ok', archived_at: null });

async function scenario(secondUser: string) {
  const answers = new Map<string, (result: CarrierResult) => void>();
  const adapter = { fetch: vi.fn((carrier: string) => new Promise<CarrierResult>((resolve) => { answers.set(carrier, resolve); })) };
  const fake = client([parcel('a', 'one', 'ctt', 'TEST0001'), parcel('b', secondUser, 'la-poste', 'TEST0002')]);
  // The merge after a's check takes a moment, as the database's advisory-locked merge does.
  let mergeDone!: () => void;
  const held = new Promise<void>((resolve) => { mergeDone = resolve; });
  fake.autoLinkPackages.mockImplementationOnce(async () => { await held; return 0; });
  const notifier = { dispatch: vi.fn().mockResolvedValue({ sent: 0, failed: 0, expired: 0 }) };
  const news = (time: string): CarrierResult => ({ status: 'in_transit', current_stage: 'in_transit', last_update: time,
    events: [{ time, description: 'In transit', stage: 'in_transit' }] });
  // The write that stores a parcel's news, after its opening "syncing" mark.
  const newsOf = (id: string) => fake.applyTrackingSync.mock.calls.findIndex(([row, values]) => row.id === id && values.sync_status !== 'syncing');
  const running = new TrackingSyncService(fake as unknown as SupabaseServiceClient, adapter, notifier as never, at).sync();
  await vi.waitFor(() => expect(answers.has('ctt')).toBe(true));
  answers.get('ctt')!(news('2026-09-04T09:30:00Z'));
  await vi.waitFor(() => expect(fake.autoLinkPackages).toHaveBeenCalledWith('one'));
  // While a's merge runs, b's check (under way, or started next) stores its news.
  await vi.waitFor(() => expect(answers.has('la-poste')).toBe(true), { timeout: 200 }).catch(() => undefined);
  if (answers.has('la-poste')) {
    answers.get('la-poste')!(news('2026-09-04T09:40:00Z'));
    await vi.waitFor(() => expect(newsOf('b')).toBeGreaterThanOrEqual(0));
  }
  const answeredEarly = newsOf('b') >= 0;
  mergeDone();
  if (!answeredEarly) {
    await vi.waitFor(() => expect(answers.has('la-poste')).toBe(true));
    answers.get('la-poste')!(news('2026-09-04T09:40:00Z'));
  }
  await running;
  const stored = fake.applyTrackingSync.mock.invocationCallOrder[newsOf('b')]!;
  const merged = fake.autoLinkPackages.mock.invocationCallOrder.find((order, index) => order > stored
    && fake.autoLinkPackages.mock.calls[index]![0] === secondUser)!;
  const firstDispatchAfterStore = notifier.dispatch.mock.invocationCallOrder.find((order) => order > stored)!;
  return { stored, merged, firstDispatchAfterStore };
}

describe('a parcel merges before its news goes out', () => {
  it('merges one account\'s second parcel, checked after the first, before any dispatch sends its news', async () => {
    const { merged, firstDispatchAfterStore } = await scenario('one');
    expect(merged).toBeLessThan(firstDispatchAfterStore);
  });

  it('merges another account\'s parcel checked at once before any dispatch sends its news', async () => {
    const { merged, firstDispatchAfterStore } = await scenario('two');
    expect(merged).toBeLessThan(firstDispatchAfterStore);
  });
});
