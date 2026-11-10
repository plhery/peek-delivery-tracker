import { afterEach, describe, expect, it, vi } from 'vitest';
import { RateLimitedError } from 'universal-parcel-scraper';
import { UpstreamHttpError } from 'universal-parcel-scraper/node';
import { catalogRules, quietUntil, RoutingDeferred, routingState, TrackingRouter } from './trackingRouting';
import type { JsonObject } from './types';
import * as monitoring from './observability';

const HOUR = 3_600_000;
const success = '2026-09-10T10:00:00.000Z';
const parcel = (routing: JsonObject = {}): JsonObject => ({
  id: 'hourly', carrier: 'la-poste', tracking_number: 'TEST1234', current_stage: 'in_transit',
  sync_status: 'ok', last_synced_at: success,
  carrier_data: { routing: { version: 1, configured_carrier: 'la-poste', last_success_at: success, ...routing } },
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
  return { direct, universal, health, router, advance: (ms: number) => { now = new Date(now.getTime() + ms); } };
}
async function missed(router: TrackingRouter, snapshot: JsonObject, scheduled = false): Promise<RoutingDeferred> {
  try { await router.fetch(snapshot, scheduled); }
  catch (error) { if (error instanceof RoutingDeferred) return error; throw error; }
  throw new Error('Expected every source to fail');
}
afterEach(() => vi.restoreAllMocks());

it('keeps an hourly parcel clear after one missed check, shows the chip after two, and clears on recovery', async () => {
  const run = setup();
  const first = await missed(run.router(), parcel());
  expect(first).toMatchObject({ stale: false, attempted: 4, routing: { consecutive_failures: 1, last_success_at: success } });
  run.advance(HOUR);
  const second = await missed(run.router(), parcel(first.routing));
  expect(second).toMatchObject({ stale: true, routing: { consecutive_failures: 2, last_success_at: success } });
  run.advance(HOUR);
  run.direct.mockResolvedValue({ result: { status: 'in_transit', current_stage: 'in_transit' },
    sourceCarrierId: 'la-poste', swissPostReady: null, handoffFallbackErrorType: null });
  const recovered = await run.router().fetch(parcel(second.routing), false);
  expect(recovered.result.routing).toMatchObject({ consecutive_failures: 0, last_success_at: '2026-09-10T13:00:00.000Z' });
  run.advance(HOUR);
  run.direct.mockRejectedValue(new Error('carrier down'));
  expect(await missed(run.router(), parcel(recovered.result.routing as JsonObject)))
    .toMatchObject({ stale: false, routing: { consecutive_failures: 1 } });
});

it.each([
  ['2026-09-10T10:15:00Z', '2026-09-10T10:00:00Z', HOUR],
  ['2026-09-10T23:15:00Z', '2026-09-10T23:00:00Z', HOUR],
])('keeps the freshness window for frequently checked parcels at %s', async (start, lastSuccess, window) => {
  const run = setup(start);
  let snapshot = parcel({ last_success_at: lastSuccess });
  for (let elapsed = 15 * 60_000; elapsed < window; elapsed += 15 * 60_000) {
    // Clear provider cooldowns so this models actual checks at the chosen cadence.
    const error = await missed(run.router(), snapshot);
    expect(error.stale).toBe(false);
    snapshot = parcel({ ...error.routing, failures: {} });
    run.advance(15 * 60_000);
  }
  expect(await missed(run.router(), snapshot)).toMatchObject({ stale: true });
});

it.each([0, 1, 2])('leaves a streak of %s unchanged when no source is contacted', async (streak) => {
  const run = setup();
  const failures = Object.fromEntries(['la-poste', 'ParcelsApp', 'Ship24', '17TRACK'].map(source => [source,
    { count: 1, kind: 'transport', retry_at: '2026-09-10T13:00:00Z' }]));
  expect(await missed(run.router(), parcel({ consecutive_failures: streak, failures })))
    .toMatchObject({ stale: streak >= 2, attempted: 0, routing: { consecutive_failures: streak } });
  expect(run.direct).not.toHaveBeenCalled();
  expect(run.universal).not.toHaveBeenCalled();
});

it('counts an early rate-limit deferral and resets the streak when a fallback succeeds', async () => {
  const run = setup('2026-09-10T10:15:00Z');
  run.direct.mockRejectedValue(new UpstreamHttpError('La Poste', 429));
  const first = await missed(run.router(), parcel());
  expect(first).toMatchObject({ stale: false, attempted: 1, routing: { consecutive_failures: 1 } });
  expect(run.universal).not.toHaveBeenCalled();
  run.advance(HOUR);
  run.universal.mockResolvedValue({ status: 'in_transit', current_stage: 'in_transit' });
  expect((await run.router().fetch(parcel(first.routing), false)).result.routing)
    .toMatchObject({ consecutive_failures: 0 });
});

it('gives a parcel with no previous success one missed check of grace', async () => {
  const run = setup();
  expect(await missed(run.router(), { id: 'new', carrier: 'la-poste', tracking_number: 'TEST1234', current_stage: 'pending' }))
    .toMatchObject({ stale: false, routing: { consecutive_failures: 1 } });
});

it.each([undefined, -1, 1.5, '2', Number.MAX_SAFE_INTEGER + 1])('starts malformed or absent streaks from zero: %s', (streak) => {
  expect(routingState(parcel({ consecutive_failures: streak })).consecutive_failures).toBe(0);
});

it('resets a streak when the configured carrier changes', () => {
  expect(routingState(parcel({ configured_carrier: 'dhl', consecutive_failures: 2 })).consecutive_failures).toBe(0);
});

it('defers a check our own pacing refused to when the next search frees up, asking no provider and counting no failure', async () => {
  const run = setup();
  run.direct.mockRejectedValue(new RateLimitedError('Poste Italiane', 20 * 60_000, 'Searched as often as an hour allows',
    { reason: 'self_paced' }));
  const paced = await missed(run.router(), parcel({ consecutive_failures: 1 }));
  expect(paced).toMatchObject({ stale: false, attempted: 0,
    routing: { consecutive_failures: 1, next_check_at: '2026-09-10T11:20:00.000Z' } });
  expect(paced.routing.failures).toEqual({});
  expect(run.direct).toHaveBeenCalledTimes(1);
  expect(run.universal).not.toHaveBeenCalled();
  expect(monitoring.reportRoutingEvent).toHaveBeenCalledWith('paced_deferred', expect.objectContaining({ provider: 'la-poste' }));
  // The carrier's own rate limit, which carries no reason, is a failed check that providers answer for.
  run.direct.mockRejectedValue(new RateLimitedError('Poste Italiane', 20 * 60_000, 'Too many requests'));
  const limited = await missed(run.router(), parcel({ consecutive_failures: 1 }));
  expect(limited).toMatchObject({ attempted: 4, routing: { consecutive_failures: 2, failures: { 'la-poste': { kind: 'rate_limited' } } } });
  expect(run.universal).toHaveBeenCalled();
});

describe('a check every source sits out', () => {
  const waiting = (turns: Record<string, string>, kind = 'no_history') => Object.fromEntries(Object.entries(turns)
    .map(([source, retry]) => [source, { count: 3, kind, retry_at: retry }]));
  const turns = { 'la-poste': '2026-09-10T15:00:00Z', ParcelsApp: '2026-09-10T17:00:00Z', Ship24: '2026-09-11T11:00:00Z', '17TRACK': '2026-09-10T14:30:00Z' };

  it('holds the scheduled checks until the earliest turn, a day at most', async () => {
    const run = setup();
    const held = await missed(run.router(), parcel({ failures: waiting(turns) }), true);
    expect(held).toMatchObject({ attempted: 0, routing: { next_check_at: '2026-09-10T12:00:00.000Z', quiet_until: '2026-09-10T14:30:00.000Z' } });
    expect(quietUntil(parcel(held.routing))).toBe(Date.parse('2026-09-10T14:30:00Z'));
    const later = Object.fromEntries(Object.keys(turns).map((source) => [source, '2026-09-13T00:00:00Z']));
    expect((await missed(run.router(), parcel({ failures: waiting(later) }), true)).routing.quiet_until).toBe('2026-09-11T11:00:00.000Z');
  });

  it.each([
    ['whose turn comes by the next check', { '17TRACK': '2026-09-10T11:40:00Z' }],
    ['the check asks', {}],
  ])('leaves the hourly check, and drops an earlier hold, beside a source %s', async (_, own) => {
    const run = setup();
    const others = Object.fromEntries(Object.entries(turns).filter(([source]) => source !== '17TRACK'));
    const free = await missed(run.router(), parcel({ rules: catalogRules(), quiet_until: '2026-09-10T14:30:00.000Z',
      failures: waiting({ ...others, ...own }) }), true);
    expect(free.routing.quiet_until).toBeUndefined();
    expect(quietUntil(parcel(free.routing))).toBe(0);
  });

  it.each([
    ['its failure', { failures: { 'la-poste': { count: 1, kind: 'transport', retry_at: '2026-09-10T13:00:00Z' } } }],
    ['its own wait while a provider serves the parcel', { preferred_provider: 'Ship24', preferred_number: 'TEST1234', direct_retry_at: '2026-09-10T13:00:00Z' }],
  ])('counts the filed carrier\'s turn after %s, and no provider waiting for a postcode the parcel lacks', async (_, carrier: JsonObject) => {
    const run = setup();
    const failures = { ...waiting({ Ship24: '2026-09-10T16:00:00Z', '17TRACK': '2026-09-10T16:00:00Z' }),
      ...waiting({ ParcelsApp: '2026-09-10T10:00:00Z' }, 'input_required'), ...carrier.failures as JsonObject };
    const held = await missed(run.router(), parcel({ rules: catalogRules(), ...carrier, failures }), true);
    expect(held).toMatchObject({ attempted: 0, routing: { quiet_until: '2026-09-10T13:00:00.000Z' } });
    expect(run.direct).not.toHaveBeenCalled();
    expect(run.universal).not.toHaveBeenCalled();
  });

  it('ends when the owner\'s input or a new carrier drops the next check, or a release ends a carrier\'s wait', () => {
    const held = { rules: catalogRules(), next_check_at: '2026-09-10T12:00:00.000Z', quiet_until: '2026-09-10T14:30:00.000Z',
      failures: waiting(turns) };
    const until = Date.parse('2026-09-10T14:30:00Z');
    expect(quietUntil(parcel(held))).toBe(until);
    expect(quietUntil(parcel({ ...held, next_check_at: undefined }))).toBe(0);
    expect(quietUntil({ ...parcel(held), carrier: 'dhl' })).toBe(0);
    // A release keeps it, unless it ends a carrier's wait.
    expect(quietUntil(parcel({ ...held, rules: 'older' }))).toBe(until);
    expect(quietUntil(parcel({ ...held, rules: 'older', failures: { ...held.failures, ...waiting({ 'la-poste': '2026-09-10T15:00:00Z' }, 'schema') } }))).toBe(0);
    expect(quietUntil(parcel({ ...held, rules: 'older', direct_retry_at: '2026-09-10T15:00:00Z' }))).toBe(0);
  });
});

it.each([
  ['transport', false],
  ['rate_limited', false],
  ['not_found', true],
])('ends an earlier %s failure of the carrier a handoff asked when it answers in the same lookup: kept %s', async (kind, kept) => {
  const run = setup();
  const scan = { stage: 'in_transit', status: 'in_transit', timestamp: '2026-09-10T10:30:00Z' };
  run.direct.mockResolvedValue({ result: { status: 'in_transit', current_stage: 'in_transit', events: [scan] },
    sourceCarrierId: 'dhl', swissPostReady: null, handoffFallbackErrorType: null,
    earlierResult: { status: 'in_transit', current_stage: 'in_transit', events: [] }, earlierCarrierId: 'jrt' });
  const dhl = { count: 3, kind, retry_at: '2026-09-10T10:30:00.000Z' };
  const filed = { ...parcel({ configured_carrier: 'jrt', failures: { jrt: { count: 1, kind: 'transport', retry_at: success }, dhl } }),
    carrier: 'jrt' };
  const { result } = await run.router().fetch(filed, true);
  const failures = (result.routing as JsonObject).failures as JsonObject;
  expect(failures.jrt).toBeUndefined();
  expect(failures.dhl).toEqual(kept ? dhl : undefined);
});
