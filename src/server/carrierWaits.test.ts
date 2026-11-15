import { afterEach, describe, expect, it, vi } from 'vitest';
import { RateLimitedError } from 'universal-parcel-scraper';
import { UpstreamHttpError } from 'universal-parcel-scraper/node';
import { RoutingDeferred, TrackingRouter } from './trackingRouting';
import type { JsonObject } from './types';
import * as monitoring from './observability';

// How long a parcel waits for its own carrier after the carrier failed in passing or our pacing refused it.
const HOUR = 3_600_000;
const DAY = 24 * HOUR;
const success = '2026-09-10T10:00:00.000Z';
const parcel = (routing: JsonObject = {}, data: JsonObject = {}): JsonObject => ({
  id: 'hourly', carrier: 'la-poste', tracking_number: 'TEST1234', current_stage: 'in_transit',
  sync_status: 'ok', last_synced_at: success, user_id: 'owner', one_off: false,
  carrier_data: { ...data, routing: { version: 1, configured_carrier: 'la-poste', last_success_at: success,
    confirmed_carrier: 'la-poste', confirmed_number: 'TEST1234', ...routing } },
});
function setup(time = '2026-09-10T11:00:00.000Z') {
  vi.spyOn(monitoring, 'reportRoutingEvent').mockImplementation(() => undefined);
  let now = new Date(time);
  const direct = vi.fn().mockRejectedValue(new Error('carrier down'));
  const universal = vi.fn().mockRejectedValue(new Error('provider down'));
  const health = {
    acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: time }),
    finishTrackingProvider: vi.fn().mockResolvedValue(undefined),
  };
  const router = () => new TrackingRouter({ direct, universal, health, now: () => now });
  return { direct, universal, router, now: () => now, advance: (ms: number) => { now = new Date(now.getTime() + ms); } };
}
async function missed(router: TrackingRouter, snapshot: JsonObject, scheduled = true): Promise<RoutingDeferred> {
  try { await router.fetch(snapshot, scheduled); }
  catch (error) { if (error instanceof RoutingDeferred) return error; throw error; }
  throw new Error('Expected a deferred check');
}
afterEach(() => vi.restoreAllMocks());

describe('a scheduled check waiting for the carrier\'s retry', () => {
  // An hourly (idle) parcel: its last answer is an hour old, and the check before this one answered.
  it('comes back within the hour after a lost connection without Retry-After', async () => {
    const run = setup();
    run.direct.mockRejectedValue(new UpstreamHttpError('La Poste', 503));
    const deferred = await missed(run.router(), parcel());
    expect(run.universal).not.toHaveBeenCalled();
    expect(Date.parse(deferred.routing.next_check_at!) - run.now().getTime()).toBeLessThanOrEqual(HOUR);
  });

  it('waits an hour at most for a maintenance page\'s 2-day Retry-After, then asks the providers', async () => {
    const run = setup();
    run.direct.mockRejectedValue(new UpstreamHttpError('La Poste', 503, 2 * DAY));
    const deferred = await missed(run.router(), parcel());
    expect(deferred).toMatchObject({ stale: false, awaitsCarrier: true });
    expect(run.universal).not.toHaveBeenCalled();
    const wait = Date.parse(deferred.routing.next_check_at!) - run.now().getTime();
    expect(wait).toBeLessThanOrEqual(HOUR);
    // The next check: the carrier still waits out its Retry-After, so the providers stand in.
    run.advance(wait);
    run.direct.mockClear();
    await run.router().fetch(parcel(deferred.routing as unknown as JsonObject), true).catch(() => undefined);
    expect(run.direct).not.toHaveBeenCalled();
    expect(run.universal).toHaveBeenCalled();
  });
});

describe('a provider standing in while our own pacing refuses the parcel\'s carrier', () => {
  const answer = { status: 'in_transit', current_stage: 'in_transit', last_update: '2026-09-10T10:50:00Z',
    events: [{ time: '2026-09-10T10:50:00Z', description: 'In transit', stage: 'in_transit' }] };
  const directAnswer = { result: answer, sourceCarrierId: 'la-poste', swissPostReady: null, handoffFallbackErrorType: null };

  async function carrierAskedAfter(refusal: Error): Promise<{ asked: boolean; directRetryAt: unknown }> {
    const run = setup();
    run.direct.mockRejectedValue(refusal);
    run.universal.mockResolvedValue(answer);
    const served = await run.router().fetch(parcel(), true);
    expect(run.universal).toHaveBeenCalled();
    const routing = served.result.routing as JsonObject;
    // The carrier's search freed up 5 minutes after the refusal; the next scheduled check comes 20 minutes later.
    run.advance(20 * 60_000);
    run.direct.mockClear();
    run.direct.mockResolvedValue(directAnswer);
    run.universal.mockClear();
    await run.router().fetch(parcel(routing, { tracking_provider: 'ParcelsApp' }), true).catch(() => undefined);
    return { asked: run.direct.mock.calls.length > 0, directRetryAt: routing.direct_retry_at };
  }

  it('asks the carrier again once its own 429 wait is over', async () => {
    const { asked } = await carrierAskedAfter(new RateLimitedError('La Poste', 5 * 60_000, 'Too many requests'));
    expect(asked).toBe(true);
  });

  it('asks the carrier again once its search frees up after a self-paced refusal of 5 minutes', async () => {
    const { asked, directRetryAt } = await carrierAskedAfter(new RateLimitedError('La Poste', 5 * 60_000,
      'Outage page on both routes; no read until the pause ends', { reason: 'self_paced' }));
    expect(asked, `direct_retry_at left at ${String(directRetryAt)}`).toBe(true);
  });

  it('Poste Italiane: an hourly account parcel whose search the hour refused is asked at its next hourly check', async () => {
    const run = setup();
    const poste = (routing: JsonObject = {}, data: JsonObject = {}): JsonObject => {
      const base = parcel({ configured_carrier: 'poste-italiane', confirmed_carrier: 'poste-italiane', ...routing }, data);
      return { ...base, carrier: 'poste-italiane' };
    };
    run.direct.mockRejectedValue(new RateLimitedError('Poste Italiane', 40 * 60_000, 'Searched as often as an hour allows',
      { reason: 'self_paced' }));
    run.universal.mockResolvedValue(answer);
    // Checked hourly (its minimum interval), so its last answer is always an hour old: the providers stand in at once.
    const served = await run.router().fetch(poste(), true);
    expect(monitoring.reportRoutingEvent).toHaveBeenCalledWith('paced_fallback', expect.objectContaining({ provider: 'poste-italiane' }));
    const routing = served.result.routing as JsonObject;
    run.advance(HOUR);
    run.direct.mockClear();
    run.direct.mockResolvedValue({ ...directAnswer, sourceCarrierId: 'poste-italiane' });
    await run.router().fetch(poste(routing, { tracking_provider: 'ParcelsApp' }), true).catch(() => undefined);
    expect(run.direct.mock.calls.length, `direct_retry_at left at ${String(routing.direct_retry_at)}`).toBe(1);
  });
});
