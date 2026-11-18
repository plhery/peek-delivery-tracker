import { afterEach, describe, expect, it, vi } from 'vitest';
import type { CarrierResult } from 'universal-parcel-scraper';
import { readerLaneEnabled, SyncJobWorker, type BackgroundState } from './background';
import { CheckHolds } from './checkHolds';
import { SCHEDULED_SYNC_PRIORITY, type SupabaseServiceClient } from './supabase';
import { TrackingSyncService } from './trackingSync';
import type { JsonObject } from './types';

// Readers' checks run beside the scheduled run, in a lane of their own, and share with it what keeps one number asked
// once at a time, each carrier's pace and the browser slots.
const at = () => new Date('2026-09-04T10:00:00Z');

afterEach(() => { vi.restoreAllMocks(); vi.useRealTimers(); });

function client(packages: JsonObject[]) {
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
  };
}

const parcel = (id: string, user: string, carrier: string, number: string, data?: JsonObject): JsonObject => ({
  id, user_id: user, one_off: false, carrier, tracking_number: number, created_at: '2026-09-03T09:00:00Z',
  last_synced_at: '2026-09-04T09:00:00Z', sync_status: 'ok', archived_at: null, ...(data ? { carrier_data: data } : {}) });
const inBrowser = { routing: { version: 1, browser_check: true } };

/** A carrier adapter whose lookups each wait to be let through, with which lookups run at once. */
function gatedAdapter() {
  const gates = new Map<string, () => void>();
  const running = new Set<string>();
  let peak = 0;
  const adapter = {
    fetch: vi.fn(async (carrier: string, number: string) => {
      const lookup = `${carrier} ${number}`;
      running.add(lookup);
      peak = Math.max(peak, running.size);
      await new Promise<void>((resolve) => gates.set(lookup, resolve));
      running.delete(lookup);
      return { status: 'in_transit', current_stage: 'in_transit', last_update: '2026-09-04T08:00:00Z' } as CarrierResult;
    }),
  };
  const asked = () => adapter.fetch.mock.calls.map(([carrier, number]) => `${carrier} ${number}`);
  const pass = async (lookup: string) => {
    await vi.waitFor(() => expect(gates.has(lookup)).toBe(true));
    gates.get(lookup)!();
    gates.delete(lookup);
  };
  return { adapter, asked, pass, running, peak: () => peak };
}

const service = (packages: JsonObject[], adapter: ReturnType<typeof gatedAdapter>['adapter']) => {
  const db = client(packages);
  return { db, sync: new TrackingSyncService(db as unknown as SupabaseServiceClient, adapter, null, at, null, { readerLane: true }) };
};

describe('a reader\'s check while the scheduled run checks the same number', () => {
  it('waits for the run\'s answer and takes it, rather than asking again', async () => {
    const gated = gatedAdapter();
    // Two accounts follow the number: the run checks one copy, a refresh comes for the other.
    const run = parcel('a', 'one', 'la-poste', 'TEST0001');
    const refreshed = parcel('b', 'two', 'la-poste', 'TEST0001');
    const { sync } = service([run], gated.adapter);
    const scheduled = sync.sync();
    await vi.waitFor(() => expect(gated.asked()).toEqual(['la-poste TEST0001']));
    const reader = sync.syncPackage(refreshed, { trigger: 'refresh' });
    await new Promise((resolve) => setTimeout(resolve, 20));
    await gated.pass('la-poste TEST0001');
    const [, summary] = await Promise.all([scheduled, reader]);
    expect(gated.asked()).toEqual(['la-poste TEST0001']);
    expect(summary).toMatchObject({ checked: 1, updated: 1 });
  });

  it('checks the new carrier after the old generation finishes', async () => {
    const gated = gatedAdapter();
    const old = parcel('a', 'one', 'la-poste', 'TEST0001');
    const { sync } = service([old], gated.adapter);
    const scheduled = sync.sync();
    await vi.waitFor(() => expect(gated.asked()).toHaveLength(1));
    const reader = sync.syncPackage({ ...old, carrier: 'ctt', tracking_generation: 'new' }, { trigger: 'refresh' });
    await gated.pass('la-poste TEST0001');
    await gated.pass('ctt TEST0001');
    expect((await reader).checked).toBe(1);
    await scheduled;
  });

  it('of the same parcel, waits for the run\'s check to end and takes it as its own', async () => {
    const gated = gatedAdapter();
    const run = parcel('a', 'one', 'la-poste', 'TEST0001');
    const { sync } = service([run], gated.adapter);
    const scheduled = sync.sync();
    await vi.waitFor(() => expect(gated.asked()).toHaveLength(1));
    let ended = false;
    const reader = sync.syncPackage(run, { trigger: 'refresh' }).finally(() => { ended = true; });
    await new Promise((resolve) => setTimeout(resolve, 20));
    expect(ended).toBe(false);
    await gated.pass('la-poste TEST0001');
    const [, summary] = await Promise.all([scheduled, reader]);
    expect(gated.asked()).toEqual(['la-poste TEST0001']);
    expect(summary.checked).toBe(0);
  });
});

describe('the limits the lanes share', () => {
  it('asks one carrier for one parcel at a time across lanes, the reader\'s going before the run\'s next', async () => {
    const gated = gatedAdapter();
    const { sync } = service([parcel('a', 'one', 'la-poste', 'TEST0001'), parcel('c', 'three', 'la-poste', 'TEST0003')], gated.adapter);
    const scheduled = sync.sync();
    await vi.waitFor(() => expect(gated.asked()).toEqual(['la-poste TEST0001']));
    const reader = sync.syncPackage(parcel('b', 'two', 'la-poste', 'TEST0002'), { trigger: 'refresh' });
    await new Promise((resolve) => setTimeout(resolve, 20));
    expect(gated.asked()).toEqual(['la-poste TEST0001']);
    await gated.pass('la-poste TEST0001');
    await vi.waitFor(() => expect(gated.asked()).toHaveLength(2));
    expect(gated.asked()[1]).toBe('la-poste TEST0002');
    await gated.pass('la-poste TEST0002');
    await gated.pass('la-poste TEST0003');
    await Promise.all([scheduled, reader]);
    expect(gated.asked()).toEqual(['la-poste TEST0001', 'la-poste TEST0002', 'la-poste TEST0003']);
    expect(gated.peak()).toBe(1);
  });

  it('runs no more browser checks at once than the run\'s lanes, and a reader\'s other checks at once', async () => {
    const gated = gatedAdapter();
    const { sync } = service([parcel('a', 'one', 'la-poste', 'TEST0001', inBrowser), parcel('c', 'three', 'ups', '1Z0000000000000003', inBrowser)],
      gated.adapter);
    const scheduled = sync.sync();
    await vi.waitFor(() => expect(gated.asked()).toHaveLength(2));
    const browser = sync.syncPackage(parcel('b', 'two', 'dhl', 'TEST0002', inBrowser), { trigger: 'refresh' });
    const plain = sync.syncPackage(parcel('d', 'four', 'ctt', 'TEST0004'), { trigger: 'refresh' });
    // The plain check runs beside the run's two; the browser one waits for a slot.
    await vi.waitFor(() => expect(gated.asked()).toContain('ctt TEST0004'));
    await new Promise((resolve) => setTimeout(resolve, 20));
    expect(gated.asked()).not.toContain('dhl TEST0002');
    await gated.pass('ctt TEST0004');
    await gated.pass('ups 1Z0000000000000003');
    await vi.waitFor(() => expect(gated.asked()).toContain('dhl TEST0002'));
    await gated.pass('la-poste TEST0001');
    await gated.pass('dhl TEST0002');
    await Promise.all([scheduled, browser, plain]);
    expect(gated.peak()).toBe(3);
  });

  it('runs two readers\' checks at once, and two holding the same carrier one after the other', async () => {
    const gated = gatedAdapter();
    const { sync } = service([], gated.adapter);
    const first = sync.syncPackage(parcel('a', 'one', 'la-poste', 'TEST0001'), { trigger: 'refresh' });
    const other = sync.syncPackage(parcel('b', 'two', 'dhl', 'TEST0002'), { trigger: 'refresh' });
    const same = sync.syncPackage(parcel('c', 'three', 'la-poste', 'TEST0003'), { trigger: 'refresh' });
    await vi.waitFor(() => expect(gated.asked()).toEqual(['la-poste TEST0001', 'dhl TEST0002']));
    await new Promise((resolve) => setTimeout(resolve, 20));
    expect(gated.asked()).not.toContain('la-poste TEST0003');
    await gated.pass('la-poste TEST0001');
    await gated.pass('la-poste TEST0003');
    await gated.pass('dhl TEST0002');
    await Promise.all([first, other, same]);
    expect(gated.peak()).toBe(2);
  });

  it('gives way only to the checks the readers\' lane does not take', async () => {
    const gated = gatedAdapter();
    const { db, sync } = service([parcel('a', 'one', 'la-poste', 'TEST0001'), parcel('c', 'three', 'ups', '1Z0000000000000003')], gated.adapter);
    const scheduled = sync.sync();
    await gated.pass('la-poste TEST0001');
    await gated.pass('ups 1Z0000000000000003');
    await scheduled;
    expect(db.syncJobQueuedAhead).toHaveBeenCalledWith(SCHEDULED_SYNC_PRIORITY, false);
  });
});

describe('the shared holds', () => {
  it('keep a waiting check ahead of the run\'s next, for its keys and a browser slot', async () => {
    const holds = new CheckHolds(2);
    const first = holds.take({ parcelId: 'a', keys: ['carrier:la-poste'], browser: true });
    holds.take({ parcelId: 'c', keys: ['carrier:ups'], browser: true });
    const waiting = holds.acquire({ parcelId: 'b', keys: ['carrier:dhl'], browser: true });
    expect(holds.free(['carrier:gls'], true, true)).toBe(false);
    expect(holds.free(['carrier:gls'], false, true)).toBe(true);
    first();
    // The slot goes to the waiting check, not to the run's next.
    expect(holds.free(['carrier:gls'], true, true)).toBe(false);
    const release = await waiting;
    expect(holds.free(['carrier:dhl'], false)).toBe(false);
    release();
    expect(holds.free(['carrier:dhl'], false, true)).toBe(true);
  });

  it('stop waiting when the check is aborted', async () => {
    const holds = new CheckHolds(2);
    holds.take({ parcelId: 'a', keys: ['number:TEST0001'], browser: false });
    const controller = new AbortController();
    const waiting = holds.acquire({ parcelId: 'b', keys: ['number:TEST0001'], browser: false }, controller.signal);
    controller.abort(new Error('stopped'));
    await expect(waiting).rejects.toThrow('stopped');
    expect(holds.free(['number:TEST0001'], false, true)).toBe(false);
    expect(holds.free(['number:TEST0002'], false, true)).toBe(true);
  });
});

describe('the readers\' lane', () => {
  it('is on unless switched off', () => {
    expect(readerLaneEnabled({})).toBe(true);
    for (const value of ['off', 'false', '0', ' OFF ']) expect(readerLaneEnabled({ SYNC_READER_LANE: value })).toBe(false);
    expect(readerLaneEnabled({ SYNC_READER_LANE: 'on' })).toBe(true);
  });

  it('claims only readers\' checks', async () => {
    vi.useFakeTimers();
    const claimReaderSyncJob = vi.fn().mockResolvedValue(null);
    const claimSyncJob = vi.fn().mockResolvedValue(null);
    const sync = new TrackingSyncService({ claimReaderSyncJob, claimSyncJob } as unknown as SupabaseServiceClient);
    const state: BackgroundState = { workerHeartbeat: null, lastScheduledSync: null, nextScheduledSync: null,
      lastSummary: null, lastError: null, lastAutoArchived: 0 };
    const worker = new SyncJobWorker(sync, state, 1_000, 'readers');
    worker.start();
    await vi.advanceTimersByTimeAsync(1);
    worker.stop();
    expect(claimReaderSyncJob).toHaveBeenCalledExactlyOnceWith(worker.workerId);
    expect(claimSyncJob).not.toHaveBeenCalled();
    expect(worker.workerId).toMatch(/:readers$/);
  });
});
