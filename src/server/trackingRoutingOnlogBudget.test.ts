import { afterEach, beforeEach, expect, it, vi } from 'vitest';
import { TrackingRouter } from './trackingRouting';
import type { TrackingContext } from 'universal-parcel-scraper/node';
import * as scraper from 'universal-parcel-scraper';
import * as monitoring from './observability';
vi.mock(import('universal-parcel-scraper'), async importOriginal => ({ ...await importOriginal() }));
const now = new Date('2026-11-17T12:00:00Z');
const candidate = (carrier: string) => ({ carrier, needsInput: null, preferred: false });
const history = { status: 'in_transit' as const, events: [{ time: now.toISOString(), description: 'Synthetic scan' }] };
beforeEach(() => {
  vi.useFakeTimers({ toFake: ['setTimeout', 'clearTimeout', 'performance'] });
  vi.spyOn(monitoring, 'reportRoutingEvent').mockImplementation(() => undefined);
});
afterEach(() => { vi.useRealTimers(); vi.restoreAllMocks(); });
function routerFor(due: string[]) {
  vi.spyOn(scraper, 'recognitionCandidates').mockImplementation((_number, options) => options?.phase === 'browser' ? due.map(candidate) : []);
  const recognizeBrowser = vi.fn(async (carrier: string, number: string, context?: TrackingContext) => {
    expect(number).toBe('SYNTHETIC0001');
    expect(context?.signal).toBeDefined();
    await new Promise(resolve => setTimeout(resolve, carrier === 'onlog-express' ? 30_000 : 10));
    return { known: false };
  });
  const universal = vi.fn(async () => history);
  const router = new TrackingRouter({ direct: vi.fn(), universal, recognize: vi.fn().mockResolvedValue({ known: false }), recognizeBrowser,
    now: () => now, health: { acquireTrackingProvider: async () => ({ token: 'synthetic', retry_at: now.toISOString() }), finishTrackingProvider: async () => {} } });
  return { router, recognizeBrowser, universal };
}
it.each([false, true])('lets saved-parcel Onlog recognition outlive20s: scheduled=%s', async scheduled => {
  const state = routerFor(['onlog-express', 'fedex']);
  const task = state.router.fetch({ carrier: 'unknown', tracking_number: 'SYNTHETIC0001' }, scheduled);
  await vi.advanceTimersByTimeAsync(20_001);
  expect(state.recognizeBrowser).toHaveBeenCalledTimes(2);
  expect(state.recognizeBrowser.mock.calls.find(c => c[0] === 'onlog-express')?.[2]?.budgetMs).toBe(40_000);
  expect(state.recognizeBrowser.mock.calls.find(c => c[0] === 'fedex')?.[2]?.budgetMs).toBe(20_000);
  expect(state.universal).not.toHaveBeenCalled();
  await vi.advanceTimersByTimeAsync(10_000);
  await expect(task).resolves.toMatchObject({ result: { events: history.events } });
});
it('keeps an ordinary saved-parcel browser batch at20s', async () => {
  const state = routerFor(['fedex']);
  state.recognizeBrowser.mockImplementation(async () => new Promise(() => {}));
  const task = state.router.fetch({ carrier: 'unknown', tracking_number: 'SYNTHETIC0001' }, false);
  await vi.advanceTimersByTimeAsync(20_001);
  await expect(task).resolves.toMatchObject({ result: { events: history.events } });
  expect(state.recognizeBrowser.mock.calls[0]?.[2]?.budgetMs).toBe(20_000);
});
