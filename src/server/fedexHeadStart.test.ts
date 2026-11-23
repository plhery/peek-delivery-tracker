import { afterEach, beforeEach, expect, it, vi } from 'vitest';
import { MAX_RECOGNITIONS, recognitionCandidates } from 'universal-parcel-scraper';
import type { BrowserRecognition, Recognition, TrackingContext } from 'universal-parcel-scraper/node';
import { lastResort } from './trackingRouting';

// FedEx's read starts with the first five carriers asked over HTTP when the scraper ranks FedEx above them all. These
// tests run it through the process's real browser pool; no test reaches a carrier.
const mocks = vi.hoisted(() => ({ http: vi.fn(), browser: vi.fn() }));
vi.mock('./adapterRegistry', () => ({ createAdapterRegistry: () => ({ for: (carrier: string) => ({
  recognize: (number: string, context: TrackingContext) => mocks.http(carrier, number, context),
  recognizeWithBrowser: (number: string, context: TrackingContext) => mocks.browser(carrier, number, context),
}) }) }));

// FedEx's public samples (carriers/fedex/numbers.json in the scraper): twelve digits that pass FedEx's check.
const NUMBER = '779017972697';
const OTHER = '477179081230';
// The last resorts come only once every other candidate has missed the number.
const firstFive = () => recognitionCandidates(NUMBER).filter((candidate) => !lastResort(candidate)).slice(0, MAX_RECOGNITIONS)
  .map(({ carrier }) => carrier);
const known = (): BrowserRecognition => ({ known: true, lastActivityAt: new Date().toISOString() });
/** A read that answers only when it is stopped, as a browser read does. */
const untilStopped = (context: TrackingContext) => new Promise<never>((_, reject) => {
  context.signal?.addEventListener('abort', () => reject(context.signal?.reason), { once: true });
});
function later<T>() {
  let resolve!: (value: T) => void;
  return { promise: new Promise<T>((settle) => { resolve = settle; }), resolve };
}
const logged = () => vi.mocked(console.log).mock.calls.map(([line]) => JSON.parse(String(line)) as Record<string, unknown>)
  .find((line) => line.event === 'carrier_recognition');
const browserReads = () => mocks.browser.mock.calls.map(([carrier, number]) => `${carrier}:${number}`);

let detectCarrier: typeof import('./carrierDetection').detectCarrier;
let browser: typeof import('./browserRecognition');
beforeEach(async () => {
  vi.resetModules();
  vi.unstubAllEnvs();
  Reflect.deleteProperty(globalThis, Symbol.for('peek.browserRecognition'));
  mocks.http.mockReset().mockResolvedValue({ known: false } satisfies Recognition);
  mocks.browser.mockReset().mockImplementation(async () => known());
  vi.spyOn(console, 'log').mockImplementation(() => undefined);
  ({ detectCarrier } = await import('./carrierDetection'));
  browser = await import('./browserRecognition');
});
afterEach(() => { vi.useRealTimers(); vi.restoreAllMocks(); });

it('reads FedEx with the first five when it ranks above them, and takes its earlier answer once they miss', async () => {
  expect(recognitionCandidates(NUMBER, { phase: 'browser' })[0]).toMatchObject({ carrier: 'fedex', preferred: true });
  expect(recognitionCandidates(NUMBER).some(({ preferred }) => preferred)).toBe(false);
  const http = later<Recognition>();
  mocks.http.mockReturnValue(http.promise);
  const answer = detectCarrier({ trackingNumber: NUMBER });
  // FedEx has answered while the first five are still being asked.
  await vi.waitFor(() => expect(mocks.browser).toHaveBeenCalledWith('fedex', NUMBER, expect.objectContaining({ budgetMs: 20_000 })));
  expect(mocks.http).toHaveBeenCalledTimes(firstFive().length);
  http.resolve({ known: false });
  expect(await answer).toEqual({ trackingNumber: NUMBER, carrier: 'fedex', asked: [...firstFive(), 'fedex'] });
  expect(mocks.browser).toHaveBeenCalledOnce();
  expect(logged()).toMatchObject({ fedex_head_start: true, fedex_early: true, settled: 'fedex' });
});

it("settles on the first five's answer over FedEx's, as when FedEx was asked after them", async () => {
  const http = later<Recognition>();
  mocks.http.mockImplementation((carrier: string) => carrier === 'brt' ? http.promise : Promise.resolve({ known: false }));
  const answer = detectCarrier({ trackingNumber: NUMBER });
  await vi.waitFor(() => expect(browserReads()).toEqual([`fedex:${NUMBER}`]));
  // FedEx knows the number too, and answered first.
  await vi.waitFor(() => expect(browser.browserRecognitionSlotToSpare('fedex', OTHER)).toBe(true));
  http.resolve({ known: true, lastActivityAt: new Date().toISOString() });
  expect(await answer).toEqual({ trackingNumber: NUMBER, carrier: 'brt', asked: firstFive() });
  expect(logged()).toMatchObject({ fedex_head_start: true, settled: 'brt' });
  expect(logged()).not.toHaveProperty('fedex_early');
});

it("stops FedEx's read once the first five settle the number, freeing its browser", async () => {
  mocks.http.mockImplementation(async (carrier: string) => ({ known: carrier === 'colis-prive', lastActivityAt: new Date().toISOString() }));
  mocks.browser.mockImplementation((_carrier: string, _number: string, context: TrackingContext) => untilStopped(context));
  expect(await detectCarrier({ trackingNumber: NUMBER })).toEqual({ trackingNumber: NUMBER, carrier: 'colis-prive', asked: firstFive() });
  const [[, , read]] = mocks.browser.mock.calls as [[string, string, TrackingContext]];
  expect(read.signal?.aborted).toBe(true);
  // Both browsers are free for the next check.
  await vi.waitFor(() => expect(browser.browserRecognitionSlotToSpare('fedex', OTHER)).toBe(true));
});

it("keeps the route's deadline when FedEx's read never ends", async () => {
  vi.useFakeTimers({ toFake: ['setTimeout', 'clearTimeout', 'performance'] });
  mocks.browser.mockImplementation((_carrier: string, _number: string, context: TrackingContext) => untilStopped(context));
  const started = performance.now();
  let ended: number | undefined;
  const answer = detectCarrier({ trackingNumber: NUMBER }).finally(() => { ended = performance.now(); });
  await vi.advanceTimersByTimeAsync(25_000);
  expect(await answer).toMatchObject({ carrier: 'unknown', asked: [...firstFive(), 'fedex'], unanswered: ['fedex'] });
  expect(ended! - started).toBeLessThanOrEqual(25_000);
  // Read from the start for 20 seconds, then again for what was left; both reads were stopped.
  expect(mocks.browser.mock.calls.map(([, , context]) => (context as TrackingContext).signal?.aborted)).toEqual([true, true]);
  expect(logged()).toMatchObject({ fedex_head_start: true, fedex_early: true, fedex_retried: true });
});

it('leaves the last free browser to other checks: FedEx then waits for the first five, as before', async () => {
  mocks.browser.mockImplementation((_carrier: string, number: string, context: TrackingContext) =>
    number === OTHER ? untilStopped(context) : Promise.resolve(known()));
  const elsewhere = new AbortController();
  const other = browser.recognizeBrowser('fedex', OTHER, { signal: elsewhere.signal }).catch(() => undefined);
  await vi.waitFor(() => expect(browserReads()).toEqual([`fedex:${OTHER}`]));
  const http = later<Recognition>();
  mocks.http.mockReturnValue(http.promise);
  const answer = detectCarrier({ trackingNumber: NUMBER });
  await vi.waitFor(() => expect(mocks.http).toHaveBeenCalledTimes(firstFive().length));
  await new Promise((resolve) => setTimeout(resolve, 0));
  expect(browserReads()).toEqual([`fedex:${OTHER}`]);
  http.resolve({ known: false });
  expect(await answer).toEqual({ trackingNumber: NUMBER, carrier: 'fedex', asked: [...firstFive(), 'fedex'] });
  expect(logged()).toMatchObject({ fedex_early: true });
  expect(logged()).not.toHaveProperty('fedex_head_start');
  elsewhere.abort();
  await other;
});

it("waits for the first five on the page of the carrier the number was typed on", async () => {
  const http = later<Recognition>();
  mocks.http.mockReturnValue(http.promise);
  const answer = detectCarrier({ trackingNumber: NUMBER, carrierHint: 'brt' });
  await vi.waitFor(() => expect(mocks.http).toHaveBeenCalledTimes(firstFive().length));
  await new Promise((resolve) => setTimeout(resolve, 0));
  expect(mocks.browser).not.toHaveBeenCalled();
  http.resolve({ known: false });
  expect(await answer).toMatchObject({ carrier: 'fedex', asked: ['brt', 'colis-prive', 'fedex'] });
  expect(logged()).not.toHaveProperty('fedex_head_start');
});
