import { afterEach, describe, expect, it, vi } from 'vitest';

// One spy for every copy of the SDK the test loads.
const sentry = vi.hoisted(() => ({ init: vi.fn(), captureCheckIn: vi.fn(() => 'check-in') }));
vi.mock('@sentry/node', async (importOriginal) => ({
  ...await importOriginal<typeof import('@sentry/node')>(),
  init: sentry.init,
  captureCheckIn: sentry.captureCheckIn,
}));

afterEach(() => {
  vi.unstubAllEnvs();
  sentry.init.mockClear();
  sentry.captureCheckIn.mockClear();
  Reflect.deleteProperty(globalThis, '__deliveryObservabilityInitialized');
});

it('starts Sentry once for the process, however many copies of the module the build makes', async () => {
  vi.stubEnv('SENTRY_DSN', 'https://public@example.test/1');
  // The instrumentation bundle and the route bundles each load their own copy.
  const instrumentation = await import('./observability');
  vi.resetModules();
  const routes = await import('./observability');
  expect(routes.initObservability).not.toBe(instrumentation.initObservability);

  expect(instrumentation.initObservability()).toBe(true);
  expect(routes.initObservability()).toBe(true);
  expect(instrumentation.initObservability()).toBe(true);
  // A second init would wrap the HTTP server's emit again on every request.
  expect(sentry.init).toHaveBeenCalledTimes(1);
});

it('stays off without a DSN, and can still start once one is set', async () => {
  const { initObservability } = await import('./observability');
  expect(initObservability()).toBe(false);
  expect(sentry.init).not.toHaveBeenCalled();
  vi.stubEnv('SENTRY_DSN', 'https://public@example.test/1');
  expect(initObservability()).toBe(true);
  expect(sentry.init).toHaveBeenCalledTimes(1);
});

describe('the scheduled sync monitor', () => {
  const minute = 60_000;
  // Sentry's rules for the monitor: a check-in is missed when none has come by the margin after the schedule's
  // next tick (an even minute) following the last one, and a run still open past its maximum runtime timed out.
  const missedAt = (lastCheckIn: number, margin: number) => (Math.floor(lastCheckIn / (2 * minute)) + 1) * 2 * minute + margin * minute;
  const start = Date.parse('2026-01-05T08:39:09Z');

  it('sends its settings with each run\'s first check-in', async () => {
    vi.stubEnv('SENTRY_DSN', 'https://public@example.test/1');
    const { beginScheduledSyncCheckIn, SCHEDULED_SYNC_MONITOR } = await import('./observability');
    expect(beginScheduledSyncCheckIn(new Date(start))).toEqual({
      checkInId: 'check-in', monitorSlug: 'delivery-tracker-sync-daytime', startedAt: start,
    });
    expect(sentry.captureCheckIn).toHaveBeenCalledWith(
      { monitorSlug: 'delivery-tracker-sync-daytime', status: 'in_progress' }, SCHEDULED_SYNC_MONITOR);
  });

  it('lets a slow run and a run handed over at a deploy finish, and reports a sync that stopped', async () => {
    const { SCHEDULED_SYNC_MONITOR: { checkinMargin, maxRuntime } } = await import('./observability');
    // A run held up by a slow carrier: no check-in between its first and its last.
    for (const runMinutes of [7, 11]) {
      const end = start + runMinutes * minute;
      expect(end).toBeLessThan(missedAt(start, checkinMargin));
      expect(runMinutes).toBeLessThan(maxRuntime);
    }
    // A run handed over at a deploy keeps its check-in while it waits behind readers' checks.
    const handedOverEnd = start + 20 * minute;
    expect(handedOverEnd).toBeLessThan(missedAt(start, checkinMargin));
    expect(handedOverEnd - start).toBeLessThan(maxRuntime * minute);
    // A sync that stopped is reported within 25 minutes of its last check-in, and a stuck run within 30.
    expect(missedAt(start, checkinMargin) - start).toBeLessThanOrEqual(25 * minute);
    expect(maxRuntime).toBeLessThanOrEqual(30);
  });
});
