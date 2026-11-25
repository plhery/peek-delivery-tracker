// USPS refuses every route in spells (TRAWL's sessions and the browser alike): scheduled checks rest it for a moment
// after five lookups in a row end without its answer, and ask the universal providers meanwhile.
import { afterEach, beforeEach, describe, expect, it, vi } from 'vitest';
import { ChallengeError, IndeterminateError, NoHistoryError, NotFoundError, RateLimitedError, TransportError, type CarrierResult } from 'universal-parcel-scraper';
import { CarrierRests, carrierRests, REST_AFTER, REST_MS } from './carrierRests';
import * as monitoring from './observability';
import { TrackingRouter } from './trackingRouting';
import { TrackingSyncService } from './trackingSync';
import type { SupabaseServiceClient } from './supabase';
import type { JsonObject } from './types';

vi.mock(import('universal-parcel-scraper'), async importOriginal => ({ ...await importOriginal() }));
beforeEach(() => {
  vi.spyOn(monitoring, 'reportRoutingEvent').mockImplementation(() => undefined);
  vi.spyOn(monitoring, 'logOperationalEvent').mockImplementation(() => undefined);
});
afterEach(() => vi.restoreAllMocks());

const start = Date.parse('2026-09-10T12:00:00Z');
const challenge = () => new ChallengeError('USPS', 'Tracking challenged');
const selfPaced = () => new RateLimitedError('USPS', 60_000, 'TRAWL rests its sessions', { reason: 'self_paced' });
const refuse = (rests: CarrierRests, times: number, at = start, error: () => Error = challenge) => {
  for (let index = 0; index < times; index++) rests.noteLookup('usps', error(), at);
};

describe('a carrier\'s rest', () => {
  it('starts after five lookups in a row without an answer and ends three minutes later', () => {
    const rests = new CarrierRests();
    refuse(rests, REST_AFTER - 1);
    expect(rests.rests('usps', start)).toBe(false);
    rests.noteLookup('usps', selfPaced(), start);
    expect(rests.rests('usps', start)).toBe(true);
    expect(rests.rests('usps', start + REST_MS - 1)).toBe(true);
    expect(rests.rests('usps', start + REST_MS)).toBe(false);
    expect(REST_AFTER).toBe(5);
    expect(REST_MS).toBe(3 * 60_000);
  });

  it('rests again when the first lookup after the rest is refused, and an answer ends the streak', () => {
    const rests = new CarrierRests();
    refuse(rests, REST_AFTER);
    const later = start + REST_MS;
    rests.noteLookup('usps', new IndeterminateError('USPS', 'Maintenance page', { reason: 'maintenance_page' }), later);
    expect(rests.rests('usps', later + REST_MS - 1)).toBe(true);
    // A reader's check answered during the rest: it ends at once, and four refusals start none.
    rests.noteLookup('usps', undefined, later + 60_000);
    expect(rests.rests('usps', later + 60_000)).toBe(false);
    refuse(rests, REST_AFTER - 1, later + 2 * 60_000);
    expect(rests.rests('usps', later + 2 * 60_000)).toBe(false);
  });

  it('takes "not found" and "no history" for answers', () => {
    for (const answer of [new NotFoundError('USPS'), new NoHistoryError('USPS', 'No history')]) {
      const rests = new CarrierRests();
      refuse(rests, REST_AFTER - 1);
      rests.noteLookup('usps', answer, start);
      refuse(rests, REST_AFTER - 1);
      expect(rests.rests('usps', start)).toBe(false);
    }
  });

  it('neither counts nor ends the streak on a transport error', () => {
    const rests = new CarrierRests();
    refuse(rests, REST_AFTER - 1);
    rests.noteLookup('usps', new TransportError('USPS', 'socket hang up'), start);
    expect(rests.rests('usps', start)).toBe(false);
    refuse(rests, 1);
    expect(rests.rests('usps', start)).toBe(true);
  });

  it('never rests another carrier', () => {
    const rests = new CarrierRests();
    for (let index = 0; index < 20; index++) rests.noteLookup('ups', challenge(), start);
    expect(rests.rests('ups', start)).toBe(false);
  });
});

const usps = (routing: JsonObject = {}): JsonObject => ({
  id: 'usps-parcel', carrier: 'usps', tracking_number: '9400100000000000000000', current_stage: 'in_transit',
  sync_status: 'ok', last_synced_at: '2026-09-10T10:00:00.000Z', user_id: 'owner', one_off: false,
  carrier_data: { routing: { version: 1, configured_carrier: 'usps', confirmed_carrier: 'usps', confirmed_number: '9400100000000000000000',
    last_success_at: '2026-09-10T10:00:00.000Z', last_event_at: '2026-09-10T09:00:00.000Z', failures: {}, probe_cursor: 0, discovery_cursor: 0, ...routing } },
});
const providerAnswer: CarrierResult = { status: 'in_transit', current_stage: 'in_transit', last_update: '2026-09-10T11:30:00Z',
  events: [{ time: '2026-09-10T11:30:00Z', description: 'Arrived at USPS Regional Facility', stage: 'in_transit' }] };

describe('the router during a rest', () => {
  function router(restsUntil?: (carrier: string) => number, at = start) {
    const direct = vi.fn();
    const universal = vi.fn().mockResolvedValue(providerAnswer);
    const health = {
      acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: '2026-09-10T12:01:30Z' }),
      finishTrackingProvider: vi.fn().mockResolvedValue(undefined),
    };
    return { direct, universal,
      router: new TrackingRouter({ direct, universal, health, now: () => new Date(at), wait: vi.fn().mockResolvedValue(undefined),
        ...(restsUntil ? { restsUntil } : {}) }) };
  }

  it('leaves the resting carrier to the providers, counting no failure of its own', async () => {
    const run = router((carrier) => carrier === 'usps' ? start + REST_MS : 0);
    const served = await run.router.fetch(usps(), true);
    expect(run.direct).not.toHaveBeenCalled();
    expect(run.universal).toHaveBeenCalled();
    expect(served.result.tracking_provider).toBe(run.universal.mock.calls[0]![0]);
    const routing = served.result.routing as { failures: JsonObject; direct_retry_at?: string };
    expect(routing.failures.usps).toBeUndefined();
    // The next scheduled check after the rest asks USPS, not one 6 h after the provider's answer.
    expect(routing.direct_retry_at).toBe(new Date(start + REST_MS).toISOString());
    expect(monitoring.reportRoutingEvent).toHaveBeenCalledWith('carrier_rested', expect.objectContaining({ provider: 'usps' }));
  });

  it('asks the carrier at the first scheduled check after the rest, though a provider served the parcel meanwhile', async () => {
    const rested = await router((carrier) => carrier === 'usps' ? start + REST_MS : 0).router.fetch(usps(), true);
    const routing = rested.result.routing as JsonObject;
    expect(routing.preferred_provider).toBeTruthy();
    const run = router(() => 0, start + REST_MS);
    run.direct.mockResolvedValue({ result: providerAnswer, sourceCarrierId: 'usps', swissPostReady: null, handoffFallbackErrorType: null });
    await run.router.fetch(usps(routing), true);
    expect(run.direct).toHaveBeenCalled();
  });

  it('asks the carrier when the check is given no rest', async () => {
    const run = router();
    run.direct.mockResolvedValue({ result: providerAnswer, sourceCarrierId: 'usps', swissPostReady: null, handoffFallbackErrorType: null });
    await run.router.fetch(usps(), true);
    expect(run.direct).toHaveBeenCalled();
  });
});

describe('checks during a spell of USPS refusals', () => {
  function fakeClient() {
    const client = {
      listActivePackages: vi.fn().mockResolvedValue([]),
      getPackage: vi.fn(async (): Promise<JsonObject | null> => null),
      syncJobQueuedAhead: vi.fn().mockResolvedValue(null),
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

  it('rests USPS for scheduled checks only, and reads it again once the rest is over', async () => {
    let now = start;
    // Each test starts from an answer: the streak is the server's, kept across checks.
    carrierRests.noteLookup('usps', undefined, now);
    const adapter = { fetch: vi.fn(async () => { throw challenge(); }), fetchUniversal: vi.fn(async () => providerAnswer) };
    const service = new TrackingSyncService(fakeClient() as unknown as SupabaseServiceClient, adapter, null, () => new Date(now));
    // Readers' checks meet five refusals in a row.
    for (let index = 0; index < REST_AFTER; index++) await service.syncPackage(usps(), { trigger: 'refresh' });
    expect(adapter.fetch).toHaveBeenCalledTimes(REST_AFTER);
    adapter.fetch.mockClear();
    adapter.fetchUniversal.mockClear();

    await service.syncPackage(usps(), { trigger: 'scheduled' });
    expect(adapter.fetch).not.toHaveBeenCalled();
    expect(adapter.fetchUniversal).toHaveBeenCalled();
    // A release's re-check and a reader's still read USPS.
    await service.syncPackage(usps(), { trigger: 'release' });
    expect(adapter.fetch, 'release').toHaveBeenCalledTimes(1);
    await service.syncPackage(usps(), { trigger: 'refresh' });
    expect(adapter.fetch, 'refresh').toHaveBeenCalledTimes(2);

    adapter.fetch.mockClear();
    now += REST_MS;
    await service.syncPackage(usps(), { trigger: 'scheduled' });
    expect(adapter.fetch).toHaveBeenCalledTimes(1);
    carrierRests.noteLookup('usps', undefined, now);
  });

  it('counts a rested check the providers leave unanswered apart, as a wait for rate limits', async () => {
    const now = start;
    carrierRests.noteLookup('usps', undefined, now);
    const client = fakeClient();
    const adapter = { fetch: vi.fn(async () => { throw challenge(); }),
      fetchUniversal: vi.fn(async () => { throw new TransportError('17TRACK', 'socket hang up'); }) };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => new Date(now));
    for (let index = 0; index < REST_AFTER; index++) await service.syncPackage(usps(), { trigger: 'refresh' });
    adapter.fetch.mockClear();
    client.recordTrackingHealth.mockClear();

    await service.syncPackage(usps(), { trigger: 'scheduled' });
    expect(adapter.fetch).not.toHaveBeenCalled();
    const samples = client.recordTrackingHealth.mock.calls.flatMap((call: unknown[]) => call[2] as JsonObject[]);
    expect(samples.find((sample) => sample.kind === 'refresh')).toMatchObject({
      subject: 'usps', healthy: true, details: { category: 'rate_limit_wait' } });
    carrierRests.noteLookup('usps', undefined, now);
  });
});
