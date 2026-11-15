import { afterEach, beforeEach, expect, it, vi } from 'vitest';
import type { BrowserRecognition } from 'universal-parcel-scraper/node';

// The process-wide browser recognition cap (2) is shared by both scheduled lanes and the Add preflight. A check that
// finds both slots taken sends nothing, so it neither starts the carrier's interval nor counts as a probe.
const lookup = vi.hoisted(() => vi.fn());
vi.mock('./adapterRegistry', () => ({ createAdapterRegistry: () => ({ for: () => ({ recognizeWithBrowser: lookup }) }) }));
vi.mock('./runtime', () => ({ serviceClient: () => ({ trackingProviderIncidentOpen: async () => false }) }));

let routing: typeof import('./trackingRouting');
let browser: typeof import('./browserRecognition');
const time = new Date('2026-09-10T12:00:00Z');
// Synthetic 20 digits: the cheap phase names nz-post, the browser phase usps (minimum interval 30 min).
const NUMBER = '92001901234567890123';

beforeEach(async () => {
  vi.resetModules();
  Reflect.deleteProperty(globalThis, Symbol.for('peek.browserRecognition'));
  lookup.mockReset();
  routing = await import('./trackingRouting');
  browser = await import('./browserRecognition');
  const monitoring = await import('./observability');
  vi.spyOn(monitoring, 'reportRoutingEvent').mockImplementation(() => undefined);
});
afterEach(() => vi.restoreAllMocks());

function router() {
  return new routing.TrackingRouter({
    direct: vi.fn().mockRejectedValue(new Error('no direct lookup here')),
    universal: vi.fn().mockResolvedValue({ status: 'pending', current_stage: 'pending', last_update: null, events: [] }),
    health: { acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: '2026-09-10T12:01:30Z' }),
      finishTrackingProvider: vi.fn().mockResolvedValue(undefined) },
    recognize: vi.fn().mockResolvedValue({ known: false }),
    recognizeBrowser: browser.recognizeBrowser,
    wait: vi.fn().mockResolvedValue(undefined),
    now: () => time,
  });
}
const parcel = { id: 'parcel', carrier: 'unknown', tracking_number: NUMBER };
// Nothing knows the number, so the check is deferred; the state it leaves is what the parcel keeps.
const leftState = (check: Promise<{ result: object }>) => check.then(value => (value.result as { routing?: unknown }).routing,
  (error: unknown) => { if (error instanceof routing.RoutingDeferred) return error.routing; throw error; });
type Routing = { candidate_probes?: Record<string, { count: number }>; paced_asks?: Record<string, string> };

it('asks usps with a free browser slot and counts its probe', async () => {
  lookup.mockResolvedValue({ known: false } satisfies BrowserRecognition);
  const state = await leftState(router().fetch(parcel, false)) as Routing;
  expect(lookup).toHaveBeenCalledWith(NUMBER, expect.anything(), undefined);
  expect(state.candidate_probes?.usps?.count).toBe(1);
});

it('counts no usps probe when both browser slots are taken by the other lane', async () => {
  let release!: () => void;
  const gate = new Promise<BrowserRecognition>(resolve => { release = () => resolve({ known: false }); });
  lookup.mockImplementation((number: string) => number === NUMBER ? Promise.resolve({ known: false }) : gate);
  // The other lane's check (or an Add preflight) holds both process-wide slots.
  const held = [browser.recognizeBrowser('usps', '92001901234567890001'), browser.recognizeBrowser('usps', '92001901234567890002')];
  const state = await leftState(router().fetch(parcel, false)) as Routing;
  release();
  await Promise.allSettled(held);
  // Nothing was sent for this number: the cap refused before any browser opened.
  expect(lookup.mock.calls.some(([number]) => number === NUMBER)).toBe(false);
  // So usps's interval doesn't start and the next try isn't pushed out an hour, then 2 h, 4 h...
  expect(state.paced_asks?.usps).toBeUndefined();
  expect(state.candidate_probes?.usps).toBeUndefined();
});
