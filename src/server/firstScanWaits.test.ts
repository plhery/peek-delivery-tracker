import { afterEach, describe, expect, it, vi } from 'vitest';
import { NoHistoryError, NotFoundError, type CarrierResult } from 'universal-parcel-scraper';
import { directCarrier, RoutingDeferred, TrackingRouter } from './trackingRouting';
import type { JsonObject } from './types';
import * as monitoring from './observability';

// How soon a first scan shows when providers have answered without history before it.
const MINUTE = 60_000;
const HOUR = 60 * MINUTE;
const DAY = 24 * HOUR;
const millis = (value: unknown) => typeof value === 'string' ? Date.parse(value) || 0 : 0;
afterEach(() => vi.restoreAllMocks());

/**
 * A parcel added before its first scan, which appears `scanAfter` later at every source that tracks it. Scheduled
 * checks run every 10 minutes once the router's own wait (next_check_at, quiet_until) allows. Returns how long after
 * the scan appeared the first check that got it ran.
 */
async function firstScanDelay(carrier: string, scanAfter: number): Promise<number> {
  vi.spyOn(monitoring, 'reportRoutingEvent').mockImplementation(() => undefined);
  const created = Date.parse('2026-09-10T08:00:00Z');
  const scanAt = created + scanAfter;
  const scan = new Date(scanAt).toISOString();
  const history: CarrierResult = { status: 'in_transit', current_stage: 'in_transit', last_update: scan,
    events: [{ time: scan, description: 'Received at the sorting centre', stage: 'in_transit' }] };
  let at = created;
  const direct = vi.fn(async () => {
    if (at < scanAt) throw new NotFoundError(carrier);
    return { result: history, sourceCarrierId: carrier, swissPostReady: null, handoffFallbackErrorType: null };
  });
  const universal = vi.fn(async (source: string) => {
    if (at < scanAt) throw new NoHistoryError(source);
    return history;
  });
  const health = {
    acquireTrackingProvider: vi.fn(async () => ({ token: 'lease', retry_at: new Date(at).toISOString() })),
    finishTrackingProvider: vi.fn().mockResolvedValue(undefined),
  };
  let routing: JsonObject | undefined;
  while (at < created + 4 * DAY) {
    const now = new Date(at);
    const router = new TrackingRouter({ direct, universal, health, now: () => now });
    const parcel: JsonObject = { id: 'fresh', user_id: 'owner', one_off: false, carrier, tracking_number: 'TEST1234',
      current_stage: 'pending', sync_status: 'waiting', created_at: new Date(created).toISOString(),
      carrier_data: routing ? { routing } : {} };
    try {
      await router.fetch(parcel, true);
      return at - scanAt;
    } catch (error) {
      if (!(error instanceof RoutingDeferred)) throw error;
      routing = error.routing;
    }
    at = Math.max(at + 10 * MINUTE, millis(routing.next_check_at), millis(routing.quiet_until));
  }
  throw new Error('never answered');
}

describe('a provider without history for a number nothing has answered for', () => {
  it('shows within the hour a first scan made 6 h after the parcel was added, when its carrier has an adapter of its own', async () => {
    expect(directCarrier('ups')).toBe(true);
    expect(await firstScanDelay('ups', 6 * HOUR)).toBeLessThanOrEqual(HOUR);
  });

  it('shows within 6 h a first scan made 6 h after the parcel was added, when the providers alone track its carrier', async () => {
    expect(directCarrier('247express')).toBe(false);
    const delay = await firstScanDelay('247express', 6 * HOUR);
    expect(delay / HOUR, 'hours between the first scan and the check that showed it').toBeLessThanOrEqual(6);
  });

  it.each([
    ['no earlier failure', 0],
    ['three earlier lost connections of the provider (its outage)', 3],
  ])('its first answer without history waits 45 minutes, after %s', async (_name, count) => {
    vi.spyOn(monitoring, 'reportRoutingEvent').mockImplementation(() => undefined);
    const now = new Date('2026-09-10T12:00:00Z');
    const universal = vi.fn(async (source: string) => { throw new NoHistoryError(source); });
    const health = { acquireTrackingProvider: vi.fn(async () => ({ token: 'lease', retry_at: now.toISOString() })),
      finishTrackingProvider: vi.fn().mockResolvedValue(undefined) };
    const router = new TrackingRouter({ direct: vi.fn(), universal, health, now: () => now });
    const failures = count ? { ParcelsApp: { count, kind: 'transport', retry_at: '2026-09-10T11:00:00Z' } } : {};
    const outcome = await router.fetch({ id: 'fresh', carrier: '247express', tracking_number: 'TEST1234', current_stage: 'pending',
      carrier_data: { routing: { version: 1, configured_carrier: '247express', failures, probe_cursor: 0, discovery_cursor: 0 } } }, true)
      .catch((error: unknown) => error);
    expect(outcome).toBeInstanceOf(RoutingDeferred);
    const parcelsApp = (outcome as RoutingDeferred).routing.failures.ParcelsApp!;
    expect(parcelsApp.kind).toBe('no_history');
    expect((Date.parse(parcelsApp.retry_at) - now.getTime()) / HOUR, 'hours ParcelsApp waits').toBeLessThanOrEqual(0.75);
  });

  it('shows within 6 h a first scan made 18 h after the parcel was added, when the providers alone track its carrier', async () => {
    const delay = await firstScanDelay('247express', 18 * HOUR);
    expect(delay / HOUR, 'hours between the first scan and the check that showed it').toBeLessThanOrEqual(6);
  });
});
