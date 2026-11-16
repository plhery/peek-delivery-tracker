import { beforeEach, expect, it, vi } from 'vitest';
import { detectCarrierMatch, IndeterminateError, MAX_RECOGNITIONS, recognitionCandidates } from 'universal-parcel-scraper';
import { detectCarrier } from './carrierDetection';
import { SupabaseServiceClient } from './supabase';

const mocks = vi.hoisted(() => ({ http: vi.fn(), browser: vi.fn() }));
vi.mock('./adapterRegistry', () => ({ createAdapterRegistry: () => ({ for: (carrier: string) => ({
  recognize: (number: string, context: unknown) => mocks.http(carrier, number, context),
}) }) }));
vi.mock('./browserRecognition', () => ({ BROWSER_RECOGNITION_BUDGET_MS: 20_000, MAX_BROWSER_RECOGNITIONS: 2,
  recognizeBrowser: (...args: unknown[]) => mocks.browser(...args) }));
beforeEach(() => {
  vi.unstubAllEnvs();
  mocks.http.mockReset().mockResolvedValue({ known: false });
  mocks.browser.mockReset().mockResolvedValue({ known: true, lastActivityAt: new Date().toISOString() });
  vi.spyOn(console, 'log').mockImplementation(() => undefined);
});

// Twelve digits whose GLS check holds, so GLS's networks are asked too, and FedEx's: the scraper ranks FedEx first.
const FEDEX_NUMBER = '100000000150';
const httpBatches = () => recognitionCandidates(FEDEX_NUMBER).slice(0, 2 * MAX_RECOGNITIONS).map(({ carrier }) => carrier);
/** Detection without the answers an earlier test left in its cache. */
async function freshDetection() {
  vi.resetModules();
  return (await import('./carrierDetection')).detectCarrier;
}

it('asks FedEx as soon as the first five miss a number that passes its check, and charges the allowance once', async () => {
  const allowance = vi.fn();
  expect(detectCarrierMatch(FEDEX_NUMBER).preferred).toContain('fedex');
  const http = httpBatches();
  expect(http.length, 'the number has a second batch').toBeGreaterThan(MAX_RECOGNITIONS);
  expect(await detectCarrier({ trackingNumber: FEDEX_NUMBER }, allowance)).toEqual({
    trackingNumber: FEDEX_NUMBER, carrier: 'fedex', asked: [...http.slice(0, MAX_RECOGNITIONS), 'fedex'],
  });
  expect(allowance).toHaveBeenCalledOnce();
  expect(mocks.http).toHaveBeenCalledWith('colis-prive', FEDEX_NUMBER, expect.objectContaining({ budgetMs: 3_000, signal: expect.any(AbortSignal) }));
  expect(mocks.browser).toHaveBeenCalledWith('fedex', FEDEX_NUMBER, expect.objectContaining({ budgetMs: 20_000 }), undefined);
});

it('asks the next five when FedEx does not know the number either, and FedEx only once', async () => {
  mocks.browser.mockResolvedValue({ known: false });
  const http = httpBatches();
  expect(await (await freshDetection())({ trackingNumber: FEDEX_NUMBER })).toEqual({
    trackingNumber: FEDEX_NUMBER, carrier: 'unknown', asked: [...http.slice(0, MAX_RECOGNITIONS), 'fedex', ...http.slice(MAX_RECOGNITIONS)],
  });
  expect(mocks.browser).toHaveBeenCalledOnce();
});

it('with FEDEX_EARLY_RECOGNITION off, asks FedEx only after both HTTP batches miss', async () => {
  vi.stubEnv('FEDEX_EARLY_RECOGNITION', 'off');
  const http = httpBatches();
  expect(await (await freshDetection())({ trackingNumber: FEDEX_NUMBER })).toEqual({
    trackingNumber: FEDEX_NUMBER, carrier: 'fedex', asked: [...http, 'fedex'],
  });
});

it('reads a NACEX number with its slash, as the add takes it, without asking anyone', async () => {
  expect(await detectCarrier({ trackingNumber: '1234/12345678' })).toEqual({ trackingNumber: '1234/12345678', carrier: 'nacex' });
  expect(mocks.http).not.toHaveBeenCalled();
});

it('can ask a browser-only candidate without an HTTP recognizer', async () => {
  const answer = await detectCarrier({ trackingNumber: '000000000000017' });
  expect(answer.carrier).toBe('fedex');
  expect(mocks.browser).toHaveBeenCalledOnce();
});

it('keeps a successful HTTP confirmation fast', async () => {
  mocks.http.mockImplementation(async (carrier: string) => ({ known: carrier === 'colis-prive' }));
  expect(await detectCarrier({ trackingNumber: '000000000012' })).toMatchObject({ carrier: 'colis-prive' });
  expect(mocks.browser).not.toHaveBeenCalled();
});

it('passes an inconclusive regional error into browser recovery and reports the recovered answer', async () => {
  const error = new IndeterminateError('DHL eCommerce', 'Regional miss', { reason: 'webtrack_not_found' });
  mocks.http.mockRejectedValue(error);
  expect(await detectCarrier({ trackingNumber: '33870000000000011' })).toEqual({
    trackingNumber: '33870000000000011', carrier: 'dhl-ecommerce', asked: ['dhl-ecommerce'],
  });
  expect(mocks.browser).toHaveBeenCalledWith('dhl-ecommerce', '33870000000000011', expect.any(Object), error);
});

it('does not browser-retry a definite miss, and does not settle on an old reused number', async () => {
  expect(await detectCarrier({ trackingNumber: '33870000000000012' })).toMatchObject({ carrier: 'unknown' });
  expect(mocks.browser).not.toHaveBeenCalled();
  mocks.browser.mockResolvedValue({ known: true, lastActivityAt: '2020-01-01T00:00:00Z' });
  expect(await detectCarrier({ trackingNumber: '000000000013' })).toMatchObject({ carrier: 'unknown' });
});

it('stops before browser work when the caller cancels the HTTP check', async () => {
  const controller = new AbortController();
  const client = new SupabaseServiceClient('https://database.example', 'service-key');
  const retain = vi.spyOn(client, 'recordTrackingSupportObservation').mockResolvedValue(undefined);
  mocks.http.mockImplementation(() => { controller.abort(); return Promise.resolve({ known: false }); });
  await expect(detectCarrier({ trackingNumber: '000000000014' }, undefined, controller.signal, client)).rejects.toThrow();
  expect(mocks.browser).not.toHaveBeenCalled();
  expect(retain).not.toHaveBeenCalled();
});
