import { afterEach, beforeEach, describe, expect, it, vi } from 'vitest';
import { UpstreamHttpError } from 'universal-parcel-scraper/node';
import { freshnessWindow, recheckSources, recognizers, RoutingDeferred, routingState, suggestedInput, TrackingRouter, type RoutedResult } from './trackingRouting';
import { refusesNumber } from './numberRefusal';
import type { JsonObject } from './types';
import type { CarrierResult, UniversalSource } from 'universal-parcel-scraper';
import * as monitoring from './observability';
import * as metrics from './metrics';
import { universalCarrierHints } from 'universal-parcel-scraper/app';
import { BudgetExceededError, ChallengeError, IndeterminateError, InputRequiredError, InvalidInputError, NoHistoryError, NotFoundError, RateLimitedError, TransportError } from 'universal-parcel-scraper';
import * as scraper from 'universal-parcel-scraper';
import { recognitionAskedCarriers } from '../lib/carriers';

// Keep the published plan configurable without changing the scraper's other exports.
vi.mock(import('universal-parcel-scraper'), async importOriginal => ({ ...await importOriginal() }));

const time = new Date('2026-09-10T12:00:00Z');
const history = (stamp = '2026-09-10T11:00:00Z'): CarrierResult => ({ status: 'in_transit', current_stage: 'in_transit',
  last_update: stamp, events: [{ time: stamp, description: 'In transit', stage: 'in_transit' }] });
const thinHistory = (): CarrierResult => ({ status: 'pending', current_stage: 'pending', last_update: '2026-09-10T11:30:00Z',
  events: [{ time: '2026-09-10T11:30:00Z', description: 'Information received', stage: 'pending' }] });
const directValue = (carrier = 'ups'): RoutedResult => ({ result: history(), sourceCarrierId: carrier, swissPostReady: null, handoffFallbackErrorType: null });
const yearlessYamato = (): RoutedResult => ({ sourceCarrierId: 'yamato', swissPostReady: null, handoffFallbackErrorType: null,
  result: { status: 'in_transit', current_stage: 'accepted', last_update: null,
    events: [{ description: '荷物受付', stage: 'accepted', provider_time_text: '09月01日 10:00' }] } });
// Providers in a fixed order, whatever the scraper's coverage evidence for the carrier says.
const fixedPlan = (...sources: UniversalSource[]) => vi.spyOn(scraper, 'universalPlan').mockReturnValue({
  sources, carrier: null, tier: () => 'unknown', rank: () => 0,
});
const parcel = (overrides: JsonObject = {}): JsonObject => ({ id: 'parcel', carrier: 'unknown', tracking_number: 'TEST1234', ...overrides });
const state = (extra: JsonObject = {}) => ({ version: 1, configured_carrier: 'unknown', failures: {}, probe_cursor: 0, discovery_cursor: 0, ...extra });
function setup(now = time, options: Partial<ConstructorParameters<typeof TrackingRouter>[0]> = {}) {
  const direct = vi.fn().mockResolvedValue(directValue());
  const universal = vi.fn().mockResolvedValue(history());
  const health = {
    acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: '2026-09-10T12:01:30Z' }),
    finishTrackingProvider: vi.fn().mockResolvedValue(undefined),
  };
  // No carrier knows the number unless a test says so.
  const recognize = vi.fn().mockResolvedValue({ known: false });
  const recognizeBrowser = vi.fn().mockResolvedValue({ known: false });
  // A provider's spacing is waited out instantly.
  const wait = vi.fn().mockResolvedValue(undefined);
  return { direct, universal, health, recognize, recognizeBrowser, wait,
    router: new TrackingRouter({ direct, universal, health, recognize, recognizeBrowser, wait, now: () => now, ...options }) };
}
beforeEach(() => vi.spyOn(monitoring, 'reportRoutingEvent').mockImplementation(() => undefined));
afterEach(() => vi.restoreAllMocks());

describe('persistent tracking routing', () => {
  it('discovers a browser carrier before using a universal provider', async () => {
    const { router, direct, universal, recognizeBrowser } = setup();
    recognizeBrowser.mockResolvedValue({ known: true, lastActivityAt: time.toISOString() });
    direct.mockImplementation(async (_parcel, carrier) => directValue(carrier));
    const result = await router.fetch(parcel({ tracking_number: '000000000011' }), false);
    expect(result.sourceCarrierId).toBe('fedex');
    expect(recognizeBrowser).toHaveBeenCalledWith('fedex', '000000000011', expect.objectContaining({ budgetMs: 20_000 }), undefined);
    expect(universal).not.toHaveBeenCalled();
  });

  it('cools down a browser miss and does not adopt an old reused number', async () => {
    const first = setup();
    // Twelve digits whose FedEx check digit adds up: the scraper names a carrier asked through a browser.
    const input = parcel({ tracking_number: '000000000022' });
    const result = await first.router.fetch(input, false);
    const browserAsked = first.recognizeBrowser.mock.calls.map(([carrier]) => carrier as string);
    expect(browserAsked.length, 'a carrier asked through a browser').toBeGreaterThan(0);
    const routing = result.result.routing;
    expect(routing).toMatchObject({ candidate_probes: Object.fromEntries(browserAsked.map((carrier) =>
      [carrier, { count: 1, retry_at: '2026-09-10T13:00:00.000Z' }])) });
    const next = setup(new Date('2026-09-10T12:10:00Z'));
    await next.router.fetch({ ...input, carrier_data: { routing } }, false);
    expect(next.recognizeBrowser).not.toHaveBeenCalled();
    const old = setup();
    old.recognizeBrowser.mockResolvedValue({ known: true, lastActivityAt: '2020-01-01T00:00:00Z' });
    await old.router.fetch(parcel({ tracking_number: '000000000033' }), false);
    expect(old.recognizeBrowser).toHaveBeenCalled();
    expect(old.direct).not.toHaveBeenCalled();
    expect(old.universal).toHaveBeenCalled();
  });

  it('avoids browser discovery after a matching carrier outage or an HTTP confirmation', async () => {
    const outage = setup();
    outage.direct.mockRejectedValue(new Error('Connection failed'));
    await outage.router.fetch(parcel({ carrier: 'fedex', tracking_number: '000000000014' }), false);
    expect(outage.recognizeBrowser).not.toHaveBeenCalled();
    const http = setup();
    http.recognize.mockResolvedValue({ known: true, lastActivityAt: time.toISOString() });
    await http.router.fetch(parcel({ tracking_number: '000000000015' }), false);
    expect(http.recognizeBrowser).not.toHaveBeenCalled();
  });
  it('sends universal providers neither the country hint nor a destination', async () => {
    const { router, universal } = setup();
    await router.fetch(parcel({ carrier_data: { lookup_country_hint: 'FR', destination_country: 'CH' } }), false);
    expect(universal).toHaveBeenCalledExactlyOnceWith('ParcelsApp', 'TEST1234', expect.any(Number), null, null, null);
  });

  it('retains actionable input failures through a deferred lookup', async () => {
    const { router, direct, universal } = setup();
    direct.mockRejectedValue(new InputRequiredError('Heppner', 'postcode'));
    universal.mockRejectedValue(new Error('unavailable'));
    await expect(router.fetch(parcel({ carrier: 'heppner' }), false)).rejects.toMatchObject({
      stale: false,
      routing: { failures: { heppner: { kind: 'input_required', user_error: 'carrier:input_required' } } },
    });
  });
  it.each(['ups', 'fedex', 'usps', 'canada-post', 'aramex'])('uses a direct carrier without contacting a universal provider', async (carrier) => {
    const { router, direct, universal } = setup();
    const result = await router.fetch(parcel({ carrier }), false);
    expect(result.result.routing).toMatchObject({ confirmed_carrier: carrier, last_success_at: time.toISOString() });
    expect(direct).toHaveBeenCalledWith(expect.objectContaining({ tracking_number: 'TEST1234' }), carrier);
    expect(universal).not.toHaveBeenCalled();
  });
  it.each([false, true])('uses Royal Mail history before providers, including saved provider affinity: %s', async (saved) => {
    const { router, direct, universal } = setup();
    direct.mockResolvedValue(directValue('royal-mail'));
    const result = await router.fetch(parcel({
      carrier: 'royal-mail',
      carrier_data: saved ? { routing: state({
        configured_carrier: 'royal-mail', confirmed_carrier: 'royal-mail', confirmed_number: 'TEST1234',
        preferred_provider: 'ParcelsApp', preferred_number: 'TEST1234',
      }) } : {},
    }), false);
    expect(direct).toHaveBeenCalledExactlyOnceWith(expect.objectContaining({ tracking_number: 'TEST1234' }), 'royal-mail');
    expect(universal).not.toHaveBeenCalled();
    expect(result.sourceCarrierId).toBe('royal-mail');
    expect(result.result.routing).toMatchObject({ configured_carrier: 'royal-mail', confirmed_carrier: 'royal-mail' });
  });
  it('falls back to provider history when Royal Mail browser tracking fails', async () => {
    const { router, direct, universal } = setup();
    vi.spyOn(scraper, 'universalPlan').mockReturnValue({
      sources: ['ParcelsApp'], carrier: 'royal-mail', tier: () => 'full', rank: () => 0,
    });
    direct.mockRejectedValue(new Error('Browser tracking unavailable'));
    universal.mockResolvedValue({ ...history(), discovered_carrier: 'royal-mail' });
    const result = await router.fetch(parcel({ carrier: 'royal-mail' }), false);
    expect(direct).toHaveBeenCalledExactlyOnceWith(expect.any(Object), 'royal-mail');
    expect(universal).toHaveBeenCalledExactlyOnceWith('ParcelsApp', 'TEST1234', expect.any(Number), null, null, 'royal-mail');
    expect(result.result.routing).toMatchObject({ preferred_provider: 'ParcelsApp', failures: { 'royal-mail': { kind: 'transport' } } });
    expect(result.result.tracking_provider).toBe('ParcelsApp');
  });
  it('discovers in default order and remembers the working provider across instances', async () => {
    const first = setup();
    first.universal.mockRejectedValueOnce(new Error('down'));
    const result = await first.router.fetch(parcel(), false);
    expect(first.universal.mock.calls.map(([source]) => source)).toEqual(['ParcelsApp', 'Ship24']);
    const second = setup(new Date('2026-09-10T12:20:00Z'));
    await second.router.fetch(parcel({ carrier_data: result.result }), false);
    expect(second.universal.mock.calls.map(([source]) => source)).toEqual(['Ship24']);
  });
  it('keeps an already successful Ship24 affinity after the default order changes', async () => {
    const { router, universal } = setup();
    await router.fetch(parcel({ carrier_data: { routing: state({ preferred_provider: 'Ship24' }) } }), false);
    expect(universal.mock.calls.map(([source]) => source)).toEqual(['Ship24']);
  });
  it.each([false, true])('searches past thin preferred and fallback answers for progress (scheduled=%s)', async (scheduled) => {
    const { router, universal, health } = setup();
    universal.mockImplementation(async (source: string) => source === '17TRACK' ? history() : thinHistory());
    const result = await router.fetch(parcel({ current_stage: 'in_transit', carrier_data: { routing: state({
      preferred_provider: 'Ship24', preferred_number: 'TEST1234', last_probe_at: time.toISOString(),
      consecutive_failures: 3,
    }) } }), scheduled);
    expect(universal.mock.calls.map(([source]) => source)).toEqual(['Ship24', 'ParcelsApp', '17TRACK']);
    expect(result.result).toMatchObject({ tracking_provider: '17TRACK', routing: { preferred_provider: '17TRACK', consecutive_failures: 0 } });
    // A thin answer is a healthy transport, not a provider outage.
    expect(health.finishTrackingProvider).toHaveBeenCalledTimes(3);
    for (const call of health.finishTrackingProvider.mock.calls) expect(call[2]).toBeNull();
  });
  it('keeps the progress provider through thin fallbacks and recovers on an hourly check', async () => {
    let saved: JsonObject = { tracking_provider: 'Ship24', routing: state({ preferred_provider: 'Ship24',
      preferred_number: 'TEST1234', last_event_at: '2026-09-10T11:00:00Z', last_success_at: '2026-09-10T11:00:00Z',
      last_probe_at: time.toISOString() }) };
    for (let hour = 0; hour < 7; hour++) {
      const { router, universal } = setup(new Date(time.getTime() + hour * 3_600_000));
      universal.mockImplementation(async (source: string) => {
        if (source === 'Ship24') throw new Error('upstream timeout');
        return thinHistory();
      });
      const result = await router.fetch(parcel({ current_stage: 'in_transit', carrier_data: saved }), false);
      const routing = result.result.routing as JsonObject;
      expect(routing).toMatchObject({ preferred_provider: 'Ship24', preferred_number: 'TEST1234',
        last_event_at: '2026-09-10T11:00:00Z', last_success_at: '2026-09-10T11:00:00Z', consecutive_failures: hour + 1 });
      expect(universal.mock.calls.map(([source]) => source)).toContain('ParcelsApp');
      saved = { ...saved, routing };
    }
    // The transport backoff still applies: resume after its six-hour cap.
    const recovered = setup(new Date(time.getTime() + 13 * 3_600_000));
    const result = await recovered.router.fetch(parcel({ current_stage: 'in_transit', carrier_data: saved }), false);
    expect(recovered.universal.mock.calls.map(([source]) => source)).toEqual(['Ship24']);
    expect(result.result.routing).toMatchObject({ preferred_provider: 'Ship24', failures: {}, consecutive_failures: 0 });
  });
  it('repairs an already pinned thin provider in the same check', async () => {
    const { router, universal } = setup();
    universal.mockImplementation(async (source: string) => source === 'ParcelsApp' ? thinHistory() : history());
    const result = await router.fetch(parcel({ current_stage: 'in_transit', carrier_data: { routing: state({
      preferred_provider: 'ParcelsApp', preferred_number: 'TEST1234',
    }) } }), false);
    expect(universal.mock.calls.map(([source]) => source)).toEqual(['ParcelsApp', 'Ship24']);
    expect(result.result.routing).toMatchObject({ preferred_provider: 'Ship24' });
  });
  it('does not report a direct-support opportunity from a thin answer', async () => {
    const { router, universal } = setup();
    universal.mockResolvedValue({ ...thinHistory(), discovered_carrier: 'royal-mail' });
    await router.fetch(parcel({ carrier: 'royal-mail', current_stage: 'in_transit' }), false);
    expect(monitoring.reportRoutingEvent).not.toHaveBeenCalledWith('direct_support_opportunity', expect.anything());
  });
  it('reports a direct-support opportunity once per number, not on every check', async () => {
    const reported = () => vi.mocked(monitoring.reportRoutingEvent).mock.calls
      .filter(([code]) => code === 'direct_support_opportunity').map(([, context]) => context.provider);
    const first = setup();
    first.universal.mockResolvedValue({ ...history(), discovered_carrier: 'aaa-cooper' });
    const result = await first.router.fetch(parcel(), false);
    expect(reported()).toEqual(['aaa-cooper']);
    expect(result.result.routing).toMatchObject({ support_opportunity: 'aaa-cooper' });
    const next = setup(new Date('2026-09-10T13:00:00Z'));
    next.universal.mockResolvedValue({ ...history('2026-09-10T12:30:00Z'), discovered_carrier: 'aaa-cooper' });
    await next.router.fetch(parcel({ carrier_data: result.result }), true);
    expect(next.universal).toHaveBeenCalled();
    expect(reported()).toEqual(['aaa-cooper']);
  });
  it.each([
    ['registered', { ...thinHistory(), current_stage: 'registered',
      events: [{ time: '2026-09-10T11:30:00Z', description: 'Information received', stage: 'registered' }] }],
    ['unmapped', { status: 'pending', events: [{ time: '2026-09-10T11:30:00Z', description: 'Noted at the fixture desk' }] }],
  ] as const)('searches past a %s label answer without changing carrier classification', async (_label, thin) => {
    const { router, universal } = setup();
    universal.mockResolvedValueOnce(thin).mockResolvedValueOnce(history());
    const result = await router.fetch(parcel({ current_stage: 'in_transit' }), false);
    expect(universal.mock.calls.map(([source]) => source)).toEqual(['ParcelsApp', 'Ship24']);
    expect(result.result.routing).toMatchObject({ preferred_provider: 'Ship24' });
  });
  it('accepts history that has movement before its latest label scan', async () => {
    const { router, universal } = setup();
    universal.mockResolvedValue({ ...thinHistory(), events: [...thinHistory().events!, ...history().events!] });
    const result = await router.fetch(parcel({ current_stage: 'in_transit' }), false);
    expect(universal.mock.calls.map(([source]) => source)).toEqual(['ParcelsApp']);
    expect(result.result.routing).toMatchObject({ preferred_provider: 'ParcelsApp' });
  });
  it('accepts movement classified from wording when a provider names no stage', async () => {
    const { router, universal } = setup();
    universal.mockResolvedValue({ status: 'pending', events: [{ time: '2026-09-10T11:00:00Z', description: 'In transit' }] });
    const result = await router.fetch(parcel({ current_stage: 'in_transit' }), false);
    expect(universal.mock.calls.map(([source]) => source)).toEqual(['ParcelsApp']);
    expect(result.result.routing).toMatchObject({ preferred_provider: 'ParcelsApp' });
  });
  it.each(['pending', 'registered'])('keeps the first label answer when the parcel is %s', async (stage) => {
    const { router, universal } = setup();
    universal.mockResolvedValue(thinHistory());
    const result = await router.fetch(parcel({ current_stage: stage }), false);
    expect(universal.mock.calls.map(([source]) => source)).toEqual(['ParcelsApp']);
    expect(result.result.routing).toMatchObject({ preferred_provider: 'ParcelsApp' });
  });
  it('retains the first thin answer when later providers fail or defer for a rate limit', async () => {
    const { router, universal } = setup();
    universal.mockResolvedValueOnce(thinHistory())
      .mockRejectedValueOnce(new UpstreamHttpError('Ship24', 429)).mockRejectedValueOnce(new Error('timeout'));
    const result = await router.fetch(parcel({ current_stage: 'in_transit', carrier_data: { routing: state({
      preferred_provider: 'ParcelsApp', last_success_at: '2026-09-10T11:50:00Z', last_event_at: '2026-09-10T11:00:00Z',
    }) } }), false);
    expect(universal.mock.calls.map(([source]) => source)).toEqual(['ParcelsApp', 'Ship24', '17TRACK']);
    expect(result.result).toMatchObject({ tracking_provider: 'ParcelsApp', routing: {
      preferred_provider: 'ParcelsApp', last_event_at: '2026-09-10T11:00:00Z', consecutive_failures: 1,
      last_success_at: '2026-09-10T11:50:00Z',
      failures: { Ship24: { kind: 'rate_limited' }, '17TRACK': { kind: 'transport' } },
    } });
  });
  it.each(['pending', 'in_transit'])('keeps movement ahead of a newer pending-only shadow answer for a %s parcel', async (stage) => {
    const { router, universal } = setup();
    universal.mockResolvedValueOnce(history()).mockResolvedValueOnce(thinHistory());
    const result = await router.fetch(parcel({ current_stage: stage, carrier_data: { routing: state({
      preferred_provider: 'Ship24', last_probe_at: '2026-09-08T12:00:00Z',
    }) } }), true);
    expect(universal.mock.calls.map(([source]) => source)).toEqual(['Ship24', 'ParcelsApp']);
    expect(result.result).toMatchObject({ tracking_provider: 'Ship24', routing: { preferred_provider: 'Ship24' } });
  });
  it.each(['ParcelsApp', 'Ship24'] as const)('records original direct failure before a successful %s fallback', async (provider) => {
    vi.spyOn(scraper, 'universalPlan').mockReturnValue({
      sources: [provider], carrier: null, tier: () => 'unknown', rank: () => 0,
    });
    const { router, direct, universal } = setup();
    direct.mockRejectedValue(new Error('upstream token SECRET'));
    universal.mockImplementation(async () => {
      expect(monitoring.reportRoutingEvent).toHaveBeenCalledWith('provider_failed', expect.objectContaining({ provider: 'dhl' }));
      return history();
    });
    const result = await router.fetch(parcel({ carrier: 'dhl' }), false);
    expect(result.result.routing).toMatchObject({ preferred_provider: provider });
    expect(JSON.stringify(vi.mocked(monitoring.reportRoutingEvent).mock.calls)).not.toContain('SECRET');
  });
  it.each(['unknown'])('uses universals for %s and still keeps the user selection', async (carrier) => {
    const { router, direct } = setup();
    const result = await router.fetch(parcel({ carrier }), false);
    expect(direct).not.toHaveBeenCalled();
    expect(result.result.routing).toMatchObject({ configured_carrier: carrier, preferred_provider: 'ParcelsApp' });
  });
  it('confirms a strong supported-carrier correction before adopting it', async () => {
    const { router, direct, universal } = setup();
    direct.mockRejectedValueOnce(Object.assign(new Error('missing'), { status: 404 }));
    const result = await router.fetch(parcel({ carrier: 'dhl', tracking_number: '1Z999AA10123456784' }), false);
    expect(direct.mock.calls.map(([, carrier]) => carrier)).toEqual(['dhl', 'ups']);
    expect(result.result.routing).toMatchObject({ confirmed_carrier: 'ups', configured_carrier: 'ups' });
    expect(universal).not.toHaveBeenCalled();
  });
  it('saves a correction and clears inputs belonging to the wrong carrier', async () => {
    const { router, direct } = setup();
    direct.mockRejectedValueOnce(new Error('wrong carrier'));
    const result = await router.fetch(parcel({ carrier: 'dhl', tracking_number: '1Z999AA10123456784',
      tracking_url: 'https://private.invalid/capability', dpd_postcode: '8000' }), false);
    expect(result.correction).toEqual({ carrier: 'ups', trackingUrl: null, postcode: null });
    expect(result.result).toMatchObject({ auto_changed_from: 'dhl', auto_changed_to: 'ups', auto_changed_at: time.toISOString() });
    expect(direct.mock.calls[1][0]).toMatchObject({ tracking_url: null, dpd_postcode: null });
  });
  it.each(['unknown', 'dhl'])('logs the carrier detection names for a parcel filed as %s without a detection warning', async (carrier) => {
    const { router, direct } = setup();
    direct.mockImplementation(async (_parcel, asked) => {
      if (asked !== 'ups') throw new NotFoundError(asked);
      return directValue('ups');
    });
    const result = await router.fetch(parcel({ carrier, tracking_number: '1Z999AA10123456784' }), false);
    expect(result.correction?.carrier).toBe('ups');
    expect(monitoring.reportRoutingEvent).toHaveBeenCalledWith('detected_carrier_confirmed', expect.objectContaining({ carrier, provider: 'ups' }));
    expect(monitoring.reportRoutingEvent).not.toHaveBeenCalledWith('carrier_mismatch_confirmed', expect.anything());
  });
  it('logs the carrier detection asks first for a parcel filed as unknown without a detection warning', async () => {
    const { router, universal } = setup();
    // A 595000 export waybill: detection asks Nova Poshta first among the 14-digit carriers.
    universal.mockResolvedValue({ ...history(), discovered_carrier: 'nova-poshta' });
    await router.fetch(parcel({ tracking_number: '59500000000012' }), false);
    expect(monitoring.reportRoutingEvent).toHaveBeenCalledWith('detected_carrier_confirmed', expect.objectContaining({ carrier: 'unknown', provider: 'nova-poshta' }));
    expect(monitoring.reportRoutingEvent).not.toHaveBeenCalledWith('carrier_mismatch_confirmed', expect.anything());
  });
  it('files a parcel filed as unknown under the carrier that answers and hands it on in the same lookup', async () => {
    const { router, direct, universal } = setup();
    universal.mockResolvedValue({ ...history(), discovered_carrier: 'nova-poshta' });
    const handedOn = (earlierFollowUp?: true): RoutedResult => ({ sourceCarrierId: 'gofo', swissPostReady: null, handoffFallbackErrorType: null,
      earlierCarrierId: 'nova-poshta', earlierResult: history('2026-09-10T10:00:00Z'), ...(earlierFollowUp ? { earlierFollowUp } : {}),
      result: { ...history(), original_carrier: 'nova-poshta', active_tracking_carrier: 'gofo', active_tracking_number: 'GFUS01000000000001' } });
    direct.mockResolvedValue(handedOn());
    const result = await router.fetch(parcel({ tracking_number: '59500000000012' }), false);
    expect(direct.mock.calls.map(([, asked]) => asked)).toEqual(['nova-poshta']);
    expect(result.correction?.carrier).toBe('nova-poshta');
    expect(result.result.routing).toMatchObject({ configured_carrier: 'nova-poshta', confirmed_carrier: 'nova-poshta' });
    expect(result.result).toMatchObject({ auto_changed_from: 'unknown', auto_changed_to: 'nova-poshta', active_tracking_carrier: 'gofo' });
    // The delivery carrier answering alone, with the earlier one only asked again, files nothing.
    const followUp = setup();
    followUp.universal.mockResolvedValue({ ...history(), discovered_carrier: 'nova-poshta' });
    followUp.direct.mockResolvedValue(handedOn(true));
    expect((await followUp.router.fetch(parcel({ tracking_number: '59500000000012' }), false)).correction).toBeUndefined();
  });
  it('warns of a detection gap when a provider names a carrier detection does not', async () => {
    // Detection cannot place the number.
    const hinted = setup();
    hinted.universal.mockResolvedValue({ ...history(), discovered_carrier: 'ups' });
    await hinted.router.fetch(parcel(), false);
    expect(monitoring.reportRoutingEvent).toHaveBeenCalledWith('carrier_mismatch_confirmed', expect.objectContaining({ carrier: 'unknown', provider: 'ups' }));
    vi.mocked(monitoring.reportRoutingEvent).mockClear();
    // Detection names UPS, which does not know the parcel; the saved hint's carrier does.
    const contradicted = setup();
    contradicted.direct.mockImplementation(async (_parcel, asked) => {
      if (asked === 'ups') throw new NotFoundError('UPS');
      return directValue(asked);
    });
    const result = await contradicted.router.fetch(parcel({ tracking_number: '1Z999AA10123456784',
      carrier_data: { routing: state({ discovered_carrier: 'fedex' }) } }), false);
    expect(contradicted.direct.mock.calls.map(([, asked]) => asked)).toEqual(['ups', 'fedex']);
    expect(result.correction?.carrier).toBe('fedex');
    expect(monitoring.reportRoutingEvent).toHaveBeenCalledWith('carrier_mismatch_confirmed', expect.objectContaining({ carrier: 'unknown', provider: 'fedex' }));
    expect(monitoring.reportRoutingEvent).not.toHaveBeenCalledWith('detected_carrier_confirmed', expect.anything());
  });
  it.each([
    // FedEx's 12-digit check fails, and Yamato's mod 7 passes, for the first number; the reverse for the second.
    ['fedex', '123456789013', [['fedex', 'fedex-1']]],
    ['yamato', '123456789012', [['yamato', 'yamato-1']]],
    ['fedex', '123456789012', []],
    ['yamato', '123456789013', []],
  ])('counts the rules whose check digit hid %s when its own lookup first confirms %s', async (carrier, number, expected) => {
    const recorded = vi.spyOn(metrics, 'recordChecksumRejection').mockImplementation(() => undefined);
    const { router, direct } = setup();
    direct.mockImplementation(async (_parcel, asked) => directValue(asked));
    const result = await router.fetch(parcel({ carrier, tracking_number: number }), false);
    expect(recorded.mock.calls).toEqual(expected);
    expect(JSON.stringify(recorded.mock.calls)).not.toContain(number);
    // Later checks of the confirmed carrier count nothing more.
    recorded.mockClear();
    await router.fetch(parcel({ carrier, tracking_number: number, carrier_data: { routing: result.result.routing } }), false);
    expect(recorded).not.toHaveBeenCalled();
  });
  it('counts a hidden carrier that a provider names and its own lookup confirms, but not another carrier\'s answer', async () => {
    const recorded = vi.spyOn(metrics, 'recordChecksumRejection').mockImplementation(() => undefined);
    const named = setup();
    named.universal.mockResolvedValue({ ...history(), discovered_carrier: 'fedex' });
    named.direct.mockImplementation(async (_parcel, asked) => directValue(asked));
    const result = await named.router.fetch(parcel({ tracking_number: '123456789013' }), false);
    expect(result.correction?.carrier).toBe('fedex');
    expect(recorded.mock.calls).toEqual([['fedex', 'fedex-1']]);
    recorded.mockClear();
    // The answer of the carrier a handoff passed the parcel to confirms nothing about this number.
    const handoff = setup();
    handoff.direct.mockResolvedValue(directValue('ups'));
    await handoff.router.fetch(parcel({ carrier: 'fedex', tracking_number: '123456789013' }), false);
    expect(recorded).not.toHaveBeenCalled();
  });
  it.each([
    ['the same carrier and number', 'fedex', '123456789013', false],
    ['another carrier', 'yamato', '123456789013', true],
    ['the carrier for another number', 'fedex', '123456789000', true],
  ])('reports and counts a confirmation the sync cleared before only when it is new: %s', async (_label, carrier, number, news) => {
    const recorded = vi.spyOn(metrics, 'recordChecksumRejection').mockImplementation(() => undefined);
    const { router, direct, universal } = setup();
    universal.mockResolvedValue({ ...history(), discovered_carrier: 'fedex' });
    direct.mockImplementation(async (_parcel, asked) => directValue(asked));
    const result = await router.fetch(parcel({ tracking_number: '123456789013',
      carrier_data: { routing: state({ cleared_confirmation: { carrier, number } }) } }), false);
    // Filed as a parcel never confirmed would be.
    expect(result.correction?.carrier).toBe('fedex');
    expect(result.result.routing).toMatchObject({ confirmed_carrier: 'fedex', confirmed_number: '123456789013' });
    expect(result.result.routing).not.toHaveProperty('cleared_confirmation');
    expect(recorded.mock.calls).toEqual(news ? [['fedex', 'fedex-1']] : []);
    expect(vi.mocked(monitoring.reportRoutingEvent).mock.calls.filter(([code]) => code === 'carrier_mismatch_confirmed'))
      .toHaveLength(news ? 1 : 0);
  });
  it('confirms a newly returned universal hint in the same sync and then uses only direct', async () => {
    const { router, direct, universal } = setup();
    universal.mockResolvedValue({ ...history(), discovered_carrier: 'ups' });
    const result = await router.fetch(parcel(), false);
    expect(universal).toHaveBeenCalledOnce();
    expect(direct).toHaveBeenCalledWith(expect.objectContaining({ tracking_number: 'TEST1234' }), 'ups');
    expect(result.correction?.carrier).toBe('ups');
    const next = setup();
    const refreshed = await next.router.fetch(parcel({ carrier: 'ups', carrier_data: result.result }), false);
    expect(refreshed.correction).toBeUndefined();
    expect(next.universal).not.toHaveBeenCalled();
  });
  it.each(['failed', 'older', 'untimed', 'placeholder', 'terminal-conflict'])('keeps universal coverage when direct confirmation is %s', async (reason) => {
    const { router, direct, universal } = setup();
    universal.mockResolvedValue({ ...history(), discovered_carrier: 'ups',
      ...(reason === 'terminal-conflict' ? { current_stage: 'delivered', status: 'delivered' } : {}) });
    if (reason === 'failed') direct.mockRejectedValue(new Error('not found'));
    if (reason === 'older') direct.mockResolvedValue({ ...directValue(), result: history('2026-09-09T11:00:00Z') });
    if (reason === 'untimed') direct.mockResolvedValue({ ...directValue(), result: { status: 'in_transit', events: [] } });
    if (reason === 'placeholder') direct.mockResolvedValue({ ...directValue(), result: { status: 'pending', events: [{ description: 'Waiting', stage: 'pending' }] } });
    const result = await router.fetch(parcel(), false);
    expect(result.correction).toBeUndefined();
    expect(result.result.tracking_provider).toBe('ParcelsApp');
    expect(result.result.routing).not.toHaveProperty('confirmed_carrier');
  });
  it('records a named carrier\'s answer behind the provider\'s delivered stage it sets aside', async () => {
    const { router, direct, universal } = setup();
    universal.mockResolvedValue({ ...history(), discovered_carrier: 'ups', current_stage: 'delivered', status: 'delivered' });
    const first = await router.fetch(parcel(), false);
    expect(first.result.current_stage).toBe('delivered');
    expect(first.result.routing).toMatchObject({ set_aside: { ups: { reason: 'behind_final', checked_at: time.toISOString(), count: 1 } } });
    const setAside = (first.result.routing as JsonObject).set_aside as JsonObject;
    expect(setAside.ups).not.toHaveProperty('newest');
    const again = await router.fetch(parcel({ carrier_data: { routing: state({ set_aside: setAside }) } }), false);
    expect(again.result.routing).toMatchObject({ set_aside: { ups: { reason: 'behind_final', count: 2 } } });
    expect(direct).toHaveBeenCalledTimes(2);
  });
  describe('a named carrier older than the parcel\'s newest scan', () => {
    // The provider's copies of the carrier's scans, read hours late, set the watermark.
    const named = (routing: JsonObject = {}) => parcel({ current_stage: 'in_transit', carrier_data: { routing: state({
      discovered_carrier: 'ups', preferred_provider: 'ParcelsApp', last_event_at: '2026-09-10T11:00:00.000Z', ...routing }) } });
    const older = () => ({ ...directValue(), result: history('2026-09-10T03:00:00Z') });

    it('takes the carrier when the copies explain the watermark', async () => {
      const withoutCopies = vi.fn().mockReturnValue(Date.parse('2026-09-10T03:00:00Z'));
      const { router, direct, universal } = setup(time, { withoutCopies });
      direct.mockResolvedValue(older());
      const result = await router.fetch(named(), false);
      expect(withoutCopies).toHaveBeenCalledExactlyOnceWith(Date.parse('2026-09-10T11:00:00Z'), expect.objectContaining({ sourceCarrierId: 'ups' }), undefined);
      expect(result).toMatchObject({ sourceCarrierId: 'ups', correction: { carrier: 'ups' },
        result: { routing: { confirmed_carrier: 'ups', last_event_at: '2026-09-10T03:00:00.000Z' } } });
      expect(universal).not.toHaveBeenCalled();
    });

    it('keeps the provider when the copies leave a newer scan', async () => {
      const withoutCopies = vi.fn().mockReturnValue(Date.parse('2026-09-10T05:00:00Z'));
      const { router, direct } = setup(time, { withoutCopies });
      direct.mockResolvedValue(older());
      const result = await router.fetch(named(), false);
      expect(result.correction).toBeUndefined();
      expect(result.result.tracking_provider).toBe('ParcelsApp');
      expect(result.result.routing).not.toHaveProperty('confirmed_carrier');
    });

    it('reads a scan dated to the minute as new as anything within that minute', async () => {
      const { router, direct } = setup();
      const answer = async (stamp: string) => {
        direct.mockResolvedValue({ ...directValue(), result: history(stamp) });
        return router.fetch(named({ last_event_at: '2026-09-10T11:00:41.250Z' }), false);
      };
      expect(await answer('2026-09-10T11:00:00Z')).toMatchObject({ sourceCarrierId: 'ups', correction: { carrier: 'ups' } });
      // The minute before is older, and so is a scan read to the second.
      for (const stamp of ['2026-09-10T10:59:00Z', '2026-09-10T11:00:12Z']) {
        expect((await answer(stamp)).result.routing).toMatchObject({ set_aside: { ups: { reason: 'older', newest: new Date(stamp).toISOString() } } });
      }
    });

    it('records the answer it sets aside, and the checks in a row that set the same one aside, until one is taken', async () => {
      const withoutCopies = vi.fn().mockReturnValue(Date.parse('2026-09-10T05:00:00Z'));
      const { router, direct } = setup(time, { withoutCopies });
      direct.mockResolvedValue(older());
      const aside = { checked_at: time.toISOString(), newest: '2026-09-10T03:00:00.000Z', watermark: '2026-09-10T11:00:00.000Z' };
      const first = await router.fetch(named(), false);
      expect(first.result.routing).toMatchObject({ set_aside: { ups: { ...aside, count: 1 } } });
      const setAside = (first.result.routing as JsonObject).set_aside as JsonObject;
      const again = await router.fetch(named({ set_aside: setAside }), false);
      expect(again.result.routing).toMatchObject({ set_aside: { ups: { ...aside, count: 2 } } });
      // Another answer, still older, starts over.
      direct.mockResolvedValue({ ...directValue(), result: history('2026-09-10T04:00:00Z') });
      const other = await router.fetch(named({ set_aside: setAside }), false);
      expect(other.result.routing).toMatchObject({ set_aside: { ups: { ...aside, newest: '2026-09-10T04:00:00.000Z', count: 1 } } });
      // A newer one is taken, and nothing is left set aside.
      direct.mockResolvedValue({ ...directValue(), result: history('2026-09-10T11:30:00Z') });
      const taken = await router.fetch(named({ set_aside: setAside }), false);
      expect(taken).toMatchObject({ sourceCarrierId: 'ups', correction: { carrier: 'ups' } });
      expect(taken.result.routing).not.toHaveProperty('set_aside');
    });

    it('records an answer without movement it sets aside, without a scan to hold against the parcel\'s', async () => {
      const { router, direct } = setup();
      direct.mockResolvedValue({ ...directValue(), result: thinHistory() });
      const first = await router.fetch(named(), false);
      expect(first.correction).toBeUndefined();
      expect(first.result.routing).toMatchObject({ set_aside: { ups: { reason: 'no_movement', checked_at: time.toISOString(), count: 1 } } });
      const setAside = (first.result.routing as JsonObject).set_aside as JsonObject;
      expect(setAside.ups).not.toHaveProperty('newest');
      const again = await router.fetch(named({ set_aside: setAside }), false);
      expect(again.result.routing).toMatchObject({ set_aside: { ups: { reason: 'no_movement', count: 2 } } });
      // An answer that moved but is older starts over, for that reason.
      direct.mockResolvedValue(older());
      const other = await router.fetch(named({ set_aside: setAside }), false);
      expect(other.result.routing).toMatchObject({ set_aside: { ups: { reason: 'older', newest: '2026-09-10T03:00:00.000Z', count: 1 } } });
    });

    it('sets aside an answer without a date as undated, neither older nor newer than the parcel', async () => {
      const withoutCopies = vi.fn().mockReturnValue(Date.parse('2026-09-10T03:00:00Z'));
      const { router, direct } = setup(time, { withoutCopies });
      direct.mockResolvedValue({ ...directValue(), result: { status: 'in_transit', current_stage: 'in_transit', last_update: null,
        events: [{ description: 'In transit', stage: 'in_transit' }] } });
      const first = await router.fetch(named(), false);
      expect(first.correction).toBeUndefined();
      expect(first.result.tracking_provider).toBe('ParcelsApp');
      expect(first.result.routing).not.toHaveProperty('confirmed_carrier');
      expect(first.result.routing).toMatchObject({ last_event_at: '2026-09-10T11:00:00.000Z',
        set_aside: { ups: { reason: 'undated', checked_at: time.toISOString(), count: 1 } } });
      const setAside = (first.result.routing as JsonObject).set_aside as JsonObject;
      expect(setAside.ups).not.toHaveProperty('newest');
      expect(setAside.ups).not.toHaveProperty('watermark');
      expect(withoutCopies).not.toHaveBeenCalled();
      const again = await router.fetch(named({ set_aside: setAside }), false);
      expect(again.result.routing).toMatchObject({ set_aside: { ups: { reason: 'undated', count: 2 } } });
    });

    it('holds the carrier against the copies in the answer of the provider that names it', async () => {
      const withoutCopies = vi.fn((watermark: number) => watermark > Date.parse('2026-09-10T11:00:00Z') ? Date.parse('2026-09-10T03:00:00Z') : watermark);
      const { router, direct, universal } = setup(time, { withoutCopies });
      const copies = { ...history('2026-09-10T11:30:00Z'), discovered_carrier: 'ups' };
      universal.mockResolvedValue(copies);
      direct.mockResolvedValue(older());
      const result = await router.fetch(parcel({ current_stage: 'in_transit',
        carrier_data: { routing: state({ last_event_at: '2026-09-10T11:00:00.000Z' }) } }), false);
      expect(withoutCopies).toHaveBeenCalledExactlyOnceWith(Date.parse('2026-09-10T11:30:00Z'), expect.objectContaining({ sourceCarrierId: 'ups' }), expect.objectContaining({ tracking_provider: 'ParcelsApp', events: copies.events }));
      expect(result).toMatchObject({ sourceCarrierId: 'ups', result: { routing: { confirmed_carrier: 'ups' } } });
    });
  });
  it('does not repeat the failing selected scraper when the universal names it', async () => {
    const { router, direct, universal } = setup();
    direct.mockRejectedValue(new Error('down'));
    universal.mockResolvedValue({ ...history(), discovered_carrier: 'ups' });
    const result = await router.fetch(parcel({ carrier: 'ups' }), false);
    expect(direct).toHaveBeenCalledOnce();
    expect(result.correction).toBeUndefined();
    expect(result.result.tracking_provider).toBe('ParcelsApp');
  });
  it.each(['saved', 'discovered'])('shows a %s earlier leg handing the parcel to its carrier without swapping', async (hint) => {
    const number = 'CNPRT90000000000000000017';
    const { router, direct, universal } = setup();
    // A customs agent that names no partner hands the parcel on under the same number.
    direct.mockImplementation(async (_parcel, carrier) => {
      if (carrier === 'ecoscooting') throw new NotFoundError('Ecoscooting');
      return { ...directValue(carrier), result: { ...history(), delivery_tracking_number: number } };
    });
    universal.mockResolvedValue({ ...history('2026-09-10T10:00:00Z'), discovered_carrier: 'ups' });
    const result = await router.fetch(parcel({ carrier: 'ecoscooting', tracking_number: number, carrier_data: {
      routing: state({ configured_carrier: 'ecoscooting', confirmed_carrier: 'ecoscooting', confirmed_number: number,
        ...(hint === 'saved' ? { discovered_carrier: 'ups' } : {}) }),
    } }), false);
    expect(result.sourceCarrierId).toBe('ups');
    expect(result.correction).toBeUndefined();
    expect(result.result).not.toHaveProperty('auto_changed_to');
    expect(result.result.routing).toMatchObject({ configured_carrier: 'ecoscooting', confirmed_carrier: 'ecoscooting' });
    expect(vi.mocked(monitoring.reportRoutingEvent).mock.calls.map(([code]) => code)).not.toContain('carrier_mismatch_confirmed');
    // Once its not-found cooldown is over, the filed carrier is asked first again.
    const next = setup(new Date('2026-09-10T14:00:00Z'));
    await next.router.fetch(parcel({ carrier: 'ecoscooting', tracking_number: number, carrier_data: result.result }), false);
    expect(next.direct.mock.calls[0]?.[1]).toBe('ecoscooting');
  });
  it('does not relabel an established cross-border journey', async () => {
    const { router, direct, universal } = setup();
    direct.mockRejectedValue(new Error('down'));
    universal.mockResolvedValue({ ...history(), discovered_carrier: 'ups' });
    const result = await router.fetch(parcel({ carrier: 'dhl', carrier_data: {
      original_carrier: 'dhl', active_tracking_carrier: 'swiss-post', active_tracking_number: 'LOCAL1234',
    } }), false);
    expect(direct).toHaveBeenCalledOnce();
    expect(result.correction).toBeUndefined();
  });
  it('waits an hour, as for a refused number, on a number its carrier finds several parcels under', async () => {
    const { router, direct, universal } = setup();
    direct.mockRejectedValue(new IndeterminateError('Australia Post', 'Several articles', { reason: 'several_parcels' }));
    universal.mockRejectedValue(new NotFoundError('Provider'));
    const deferred = await router.fetch(parcel({ carrier: 'australia-post' }), false).catch((error: unknown) => error);
    expect(deferred).toBeInstanceOf(RoutingDeferred);
    const routing = (deferred as RoutingDeferred).routing;
    expect(routing.failures['australia-post']).toEqual({ kind: 'invalid_input', count: 1,
      user_error: 'carrier:several_parcels', retry_at: '2026-09-10T13:00:00.000Z' });
    // Not asked again before then.
    const next = setup(new Date('2026-09-10T12:30:00Z'));
    next.universal.mockRejectedValue(new NotFoundError('Provider'));
    await next.router.fetch(parcel({ carrier: 'australia-post', carrier_data: { routing } }), false).catch(() => null);
    expect(next.direct).not.toHaveBeenCalled();
  });
  it('uses a universal carrier hint only after a matching direct lookup succeeds', async () => {
    const { router, direct } = setup();
    const result = await router.fetch(parcel({ carrier_data: { routing: state({ discovered_carrier: 'ups' }) } }), false);
    expect(direct).toHaveBeenCalledWith(expect.objectContaining({ carrier: 'ups' }), 'ups');
    expect(result.result.routing).toMatchObject({ confirmed_carrier: 'ups' });
  });
  it('forgets a saved carrier hint once its adapter refuses the number', async () => {
    const { router, direct } = setup();
    // A name saved before providers stopped proposing a carrier whose check digit the number fails.
    direct.mockRejectedValue(new InvalidInputError('DHL Express', 'The waybill check digit does not match'));
    const result = await router.fetch(parcel({ carrier_data: { routing: state({ discovered_carrier: 'dhl-express' }) } }), false);
    expect(direct).toHaveBeenCalledExactlyOnceWith(expect.objectContaining({ carrier: 'dhl-express' }), 'dhl-express');
    expect(result.result.routing).not.toHaveProperty('discovered_carrier');
    expect(result.result.routing).toMatchObject({ failures: { 'dhl-express': { kind: 'invalid_input', user_error: 'carrier:invalid_input' } } });
    // Once its cooldown is over, nothing asks it again.
    direct.mockClear();
    await router.fetch(parcel({ carrier_data: { routing: { ...result.result.routing as JsonObject, failures: {} } } }), false);
    expect(direct).not.toHaveBeenCalled();
  });
  it('asks a carrier that does not know the number yet again within hours, a provider after a day', async () => {
    const { router, direct, universal } = setup();
    direct.mockRejectedValue(new NotFoundError('Swiss Post'));
    universal.mockRejectedValue(new NotFoundError('Ship24'));
    await expect(router.fetch(parcel({ carrier: 'swiss-post' }), false)).rejects.toMatchObject({ routing: { failures: {
      'swiss-post': { count: 1, kind: 'not_found', retry_at: '2026-09-10T13:00:00.000Z' },
      Ship24: { count: 1, kind: 'not_found', retry_at: '2026-09-11T12:00:00.000Z' },
    } } });
    // Each further miss doubles the carrier's wait, up to six hours.
    const later = setup(new Date('2026-09-10T19:00:00Z'));
    later.direct.mockRejectedValue(new NotFoundError('Swiss Post'));
    const result = await later.router.fetch(parcel({ carrier: 'swiss-post', carrier_data: { routing: state({ configured_carrier: 'swiss-post',
      failures: { 'swiss-post': { count: 3, kind: 'not_found', retry_at: '2026-09-10T19:00:00.000Z' } } }) } }), false);
    expect(result.result.routing).toMatchObject({ failures: { 'swiss-post': { count: 4, retry_at: '2026-09-11T01:00:00.000Z' } } });
  });
  it('asks a carrier that said not found again at once when a provider shows new progress for it', async () => {
    const filed = (extra: JsonObject = {}) => parcel({ carrier: 'swiss-post', carrier_data: { routing: state({ configured_carrier: 'swiss-post',
      failures: { 'swiss-post': { count: 1, kind: 'not_found', retry_at: '2026-09-11T00:00:00.000Z' } }, ...extra }) } });
    const { router, direct, universal } = setup();
    universal.mockResolvedValue({ ...history(), discovered_carrier: 'swiss-post' });
    direct.mockResolvedValue(directValue('swiss-post'));
    const result = await router.fetch(filed(), false);
    expect(direct).toHaveBeenCalledOnce();
    expect(result.result).not.toHaveProperty('tracking_provider');
    expect(result.result.routing).toMatchObject({ confirmed_carrier: 'swiss-post', failures: {} });
    // Progress the parcel already had is no news: the carrier keeps its wait.
    const again = setup();
    again.universal.mockResolvedValue({ ...history(), discovered_carrier: 'swiss-post' });
    const kept = await again.router.fetch(filed({ last_event_at: '2026-09-10T11:00:00.000Z' }), false);
    expect(again.direct).not.toHaveBeenCalled();
    expect(kept.result.tracking_provider).toBe('ParcelsApp');
  });
  describe('recognition of the carriers a number could belong to', () => {
    // A Swiss DPD depot prefix; the number is only a suggestion by shape.
    const swissDpd = '06080000000002';
    // The scraper decides which carriers share a shape, and in what order.
    const fourteen = recognitionAskedCarriers(swissDpd);
    const probes = (count: number, retryAt: string, carriers = fourteen) => Object.fromEntries(carriers.map(carrier => [carrier, { count, retry_at: retryAt }]));
    const notFound = (carrier: string) => new NotFoundError(carrier);
    const knows = (...carriers: string[]) => async (carrier: string) => ({ known: carriers.includes(carrier) });
    const asked = (recognize: ReturnType<typeof vi.fn>) => recognize.mock.calls.map(([carrier]) => carrier);
    const twoBatches = ['dpd', 'seur', 'brt', 'relais-colis', 'ciblex', 'hermes-de'].map((carrier) => ({ carrier, needsInput: null, preferred: false }));

    it('asks a second unqueried HTTP batch only for a new addition with no first-round match', async () => {
      vi.spyOn(scraper, 'recognitionCandidates').mockImplementation((_number, options) =>
        options?.phase === 'browser' ? [] : twoBatches.filter(({ carrier }) => !options?.skip?.(carrier)));
      const added = setup();
      added.recognize.mockImplementation(knows('hermes-de'));
      added.direct.mockResolvedValue(directValue('hermes-de'));
      const result = await added.router.fetch(parcel({ tracking_number: '00000000000001' }), false, undefined, true);
      expect(asked(added.recognize)).toEqual(twoBatches.map(({ carrier }) => carrier));
      expect(result.correction?.carrier).toBe('hermes-de');
      expect(added.universal).not.toHaveBeenCalled();
      const refresh = setup();
      await refresh.router.fetch(parcel({ tracking_number: '00000000000001' }), false);
      expect(asked(refresh.recognize)).toEqual(twoBatches.slice(0, 5).map(({ carrier }) => carrier));
      expect(refresh.universal).toHaveBeenCalled();
    });

    it.each([['dpd'], ['dpd', 'seur'], ['dpd', 'relais-colis']])('does not expand a first-round match or choice: %j', async (...known) => {
      vi.spyOn(scraper, 'recognitionCandidates').mockImplementation((_number, options) => options?.phase === 'browser' ? [] : twoBatches);
      const { router, recognize } = setup();
      recognize.mockImplementation(knows(...known));
      await router.fetch(parcel({ tracking_number: '00000000000002' }), false, undefined, true);
      expect(asked(recognize)).toEqual(twoBatches.slice(0, 5).map(({ carrier }) => carrier));
    });

    it('reserves second-round time inside the existing total HTTP budget', async () => {
      vi.useFakeTimers({ toFake: ['setTimeout', 'clearTimeout', 'performance'] });
      try {
        vi.spyOn(scraper, 'recognitionCandidates').mockImplementation((_number, options) => options?.phase === 'browser' ? [] : twoBatches);
        const { router, recognize, direct } = setup();
        recognize.mockImplementation(async (carrier: string) => {
          if (carrier === 'hermes-de') return { known: true };
          return await new Promise(() => undefined);
        });
        direct.mockResolvedValue(directValue('hermes-de'));
        const request = router.fetch(parcel({ tracking_number: '00000000000003' }), false, undefined, true);
        await vi.advanceTimersByTimeAsync(7_500);
        await expect(request).resolves.toMatchObject({ correction: { carrier: 'hermes-de' } });
        expect(recognize.mock.calls[0][2]).toMatchObject({ budgetMs: 7_500 });
        expect(recognize.mock.calls[5][2]).toMatchObject({ budgetMs: 7_500 });
        expect(performance.now()).toBeLessThanOrEqual(15_000);
      } finally { vi.useRealTimers(); }
    });

    it('passes the saved country to query ordering without using it to settle ownership', async () => {
      const candidates = vi.spyOn(scraper, 'recognitionCandidates').mockReturnValue(twoBatches.slice(0, 2));
      const { router, recognize, direct } = setup();
      recognize.mockResolvedValue({ known: false });
      await router.fetch(parcel({ tracking_number: '00000000000004', carrier_data: { lookup_country_hint: 'FR' } }), false);
      expect(candidates).toHaveBeenCalledWith('00000000000004', expect.objectContaining({ countryHint: 'FR' }));
      expect(direct).not.toHaveBeenCalled();
    });
    it('recovers an already saved compact PostLogistics number filed as unknown', async () => {
      const { router, direct, universal, recognize } = setup();
      recognize.mockImplementation(knows('postlogistics'));
      direct.mockResolvedValue(directValue('postlogistics'));

      const result = await router.fetch(parcel({ tracking_number: '12345678002' }), false);

      expect(asked(recognize)).toContain('postlogistics');
      expect(direct).toHaveBeenCalledWith(expect.objectContaining({ tracking_number: '12345678002' }), 'postlogistics');
      expect(result.correction?.carrier).toBe('postlogistics');
      expect(universal).not.toHaveBeenCalled();
    });
    it('asks before the universals and adopts a carrier that tracks the parcel', async () => {
      const { router, direct, universal, recognize } = setup();
      recognize.mockImplementation(knows('dpd'));
      direct.mockImplementation(async (_lookup: JsonObject, carrier: string) => {
        if (carrier === 'asendia') throw notFound('Asendia');
        return directValue('dpd');
      });
      const result = await router.fetch(parcel({ carrier: 'asendia', tracking_number: swissDpd, dpd_postcode: null }), false);
      // Asked at once, number evidence first, then by popularity.
      expect(asked(recognize)).toEqual(fourteen);
      expect(fourteen.length, 'several carriers share the shape').toBeGreaterThan(1);
      expect(fourteen[0]).toBe('dpd');
      // Only the carrier that knows the number gets a full lookup, without borrowed inputs.
      expect(direct.mock.calls.map(([, carrier]) => carrier)).toEqual(['asendia', 'dpd']);
      expect(direct.mock.calls[1][0]).toMatchObject({ carrier: 'dpd', dpd_postcode: null, tracking_url: null });
      expect(universal).not.toHaveBeenCalled();
      expect(result.correction).toEqual({ carrier: 'dpd', trackingUrl: null, postcode: null });
      expect(result.result).toMatchObject({ auto_changed_from: 'asendia', auto_changed_to: 'dpd' });
    });
    it('orders the carriers by a universal hint, number evidence, then popularity', async () => {
      // A 14-digit number whose last digit passes the Hermes check.
      const hermesShape = '12345678901231';
      const first = setup();
      await first.router.fetch(parcel({ tracking_number: hermesShape }), false);
      expect(asked(first.recognize)).toEqual(recognitionAskedCarriers(hermesShape));
      expect(asked(first.recognize).length, 'several carriers share the shape').toBeGreaterThan(1);
      // A universal that named a carrier needing a postcode puts it first; one
      // needing nothing was already looked up by the correction step.
      const hinted = setup();
      await hinted.router.fetch(parcel({ tracking_number: '12345678901',
        carrier_data: { routing: state({ discovered_carrier: 'gls-de' }) } }), false);
      expect(asked(hinted.recognize)).toEqual(recognitionAskedCarriers('12345678901', { hint: 'gls-de' }));
      expect(asked(hinted.recognize)[0]).toBe('gls-de');
      // Newly supported number shapes follow the same popularity order.
      const tnt = setup();
      await tnt.router.fetch(parcel({ tracking_number: '1000000000000001' }), false);
      expect(asked(tnt.recognize)).toEqual(recognitionAskedCarriers('1000000000000001'));
      expect(asked(tnt.recognize)).toContain('tnt');
      expect(tnt.direct).not.toHaveBeenCalled();
      const austria = setup();
      await austria.router.fetch(parcel({ tracking_number: '1000000000000000000001' }), false);
      expect(asked(austria.recognize)).toEqual(['austrian-post']);
      expect(austria.direct).not.toHaveBeenCalled();
      // A number no recognizable carrier fits asks nobody.
      const other = setup();
      await other.router.fetch(parcel({ tracking_number: 'ZZUNMATCHED0001' }), false);
      expect(other.recognize).not.toHaveBeenCalled();
    });

    it('confirms Chronopost after a wrong carrier fails with a transport error', async () => {
      const { router, direct, universal, recognize } = setup();
      recognize.mockImplementation(knows('chronopost'));
      direct.mockImplementation(async (_lookup: JsonObject, carrier: string) => {
        if (carrier === 'dhl') throw new Error('upstream unavailable');
        return directValue('chronopost');
      });
      const result = await router.fetch(parcel({ carrier: 'dhl', tracking_number: 'HL123456789FR',
        tracking_url: 'https://private.invalid/capability', dpd_postcode: '8000' }), false);
      expect(asked(recognize)).toEqual(['chronopost']);
      expect(direct.mock.calls.map(([, carrier]) => carrier)).toEqual(['dhl', 'chronopost']);
      expect(direct.mock.calls[1][0]).toMatchObject({ tracking_url: null, dpd_postcode: null });
      expect(result.correction).toEqual({ carrier: 'chronopost', trackingUrl: null, postcode: null });
      expect(universal).not.toHaveBeenCalled();
    });

    it.each([
      ['RR123456785FI', 'posti', ['posti', 'chronopost']],
      ['RA123456785IR', 'chronopost', ['chronopost']],
      ['33870000000000001', 'dhl-ecommerce', ['dhl-ecommerce']],
      // Other carriers whose numbers pass the same check are asked beside TNT.
      ['505600001', 'tnt', recognitionAskedCarriers('505600001')],
    ])('tracks %s through its confirmed carrier before a provider', async (number, carrier, candidates) => {
      const { router, direct, universal, recognize } = setup();
      recognize.mockImplementation(knows(carrier as string));
      direct.mockResolvedValue(directValue(carrier));
      const result = await router.fetch(parcel({ carrier: 'intl-post', tracking_number: number }), false);
      expect(asked(recognize)).toEqual(candidates);
      expect(direct).toHaveBeenCalledExactlyOnceWith(expect.objectContaining({ tracking_number: number }), carrier);
      expect(result.correction?.carrier).toBe(carrier);
      expect(universal).not.toHaveBeenCalled();
    });
    it('asks again within hours, outside the carrier failures', async () => {
      const { router, universal, recognize } = setup();
      const result = await router.fetch(parcel({ tracking_number: swissDpd }), false);
      expect(asked(recognize)).toEqual(fourteen);
      expect(universal).toHaveBeenCalled();
      const routing = result.result.routing as JsonObject & { failures: JsonObject; candidate_probes: JsonObject };
      expect(routing.candidate_probes).toEqual(probes(1, '2026-09-10T13:00:00.000Z'));
      // The answers decide neither the parcel's status nor a carrier cooldown.
      expect(routing.failures).toEqual({});
      // Inside the cooldown the next check goes straight to the universals.
      const next = setup(new Date('2026-09-10T12:30:00Z'));
      const again = await next.router.fetch(parcel({ tracking_number: swissDpd, carrier_data: result.result }), false);
      expect(asked(next.recognize).filter(carrier => fourteen.includes(carrier))).toEqual([]);
      // After it, a second miss doubles the wait.
      const later = setup(new Date('2026-09-10T13:05:00Z'));
      const third = await later.router.fetch(parcel({ tracking_number: swissDpd, carrier_data: again.result }), false);
      expect((third.result.routing as JsonObject & { candidate_probes: JsonObject }).candidate_probes)
        .toMatchObject({ dpd: { count: 2, retry_at: '2026-09-10T15:05:00.000Z' } });
    });
    it.each([
      ['a pre-advice only', { status: 'registered', current_stage: 'registered', last_update: '2026-09-10T11:00:00Z',
        events: [{ time: '2026-09-10T11:00:00Z', description: 'Order created', stage: 'registered' }] }],
      ['history older than what the parcel has', history('2026-09-09T11:00:00Z')],
    ])('does not adopt a carrier that knows the number with %s', async (_label, answer) => {
      const { router, direct, recognize } = setup();
      recognize.mockImplementation(knows('dpd'));
      direct.mockResolvedValue({ ...directValue('dpd'), result: answer as CarrierResult });
      const saved = { routing: state({ last_event_at: '2026-09-10T10:00:00Z' }) };
      const result = await router.fetch(parcel({ tracking_number: swissDpd, carrier_data: saved }), false);
      expect(result.correction).toBeUndefined();
      expect(result.result.tracking_provider).toBe('ParcelsApp');
      expect(result.result.routing).toMatchObject({ candidate_probes: { dpd: { count: 1 } }, failures: {} });
    });
    it('offers a carrier that needs the user\'s postcode instead of looking it up', async () => {
      const { router, direct, recognize } = setup();
      // Both GLS networks answer from the same overview: the more common one is offered.
      recognize.mockImplementation(knows('gls-ch', 'gls-de'));
      const result = await router.fetch(parcel({ tracking_number: '12345678901' }), false);
      expect(asked(recognize)).toEqual(recognitionAskedCarriers('12345678901'));
      expect(direct).not.toHaveBeenCalled();
      expect(result.result.routing).toMatchObject({ input_needed: { carrier: 'gls-de', field: 'dpdPostcode' } });
      expect(monitoring.reportRoutingEvent).toHaveBeenCalledWith('carrier_input_needed', expect.objectContaining({ provider: 'gls-de' }));
      // Once the user files it under GLS with the postcode and it tracks, nothing is left to ask.
      // Once the providers' day of rest after their "not found" is over.
      const next = setup(new Date('2026-09-11T12:30:00Z'));
      next.direct.mockResolvedValue(directValue('gls-de'));
      const tracked = await next.router.fetch(parcel({ carrier: 'gls-de', dpd_postcode: '10115', tracking_number: '12345678901',
        carrier_data: result.result }), false);
      expect(tracked.result.routing).not.toHaveProperty('input_needed');
      // Nor once the carrier, asked again, no longer knows the number.
      const later = setup(new Date('2026-09-11T13:30:00Z'));
      const forgotten = await later.router.fetch(parcel({ tracking_number: '12345678901', carrier_data: result.result }), false);
      expect(asked(later.recognize)).toContain('gls-de');
      expect(forgotten.result.routing).not.toHaveProperty('input_needed');
    });
    it('suggests a carrier that cannot be asked about the number without the postcode', async () => {
      // An 8-digit shipment fits Mondial Relay, which needs the recipient's postcode for it, so
      // recognition cannot ask it; a parcel looked up from France is offered it.
      const lookup = parcel({ tracking_number: '12345678', current_stage: 'pending', carrier_data: { lookup_country_hint: 'FR' } });
      const { router, universal } = setup();
      universal.mockRejectedValue(new NotFoundError('Provider'));
      const deferred = await router.fetch(lookup, false).catch((error: unknown) => error);
      expect(deferred).toBeInstanceOf(RoutingDeferred);
      expect((deferred as RoutingDeferred).routing.input_suggested).toEqual({ carrier: 'mondial-relay', field: 'dpdPostcode' });
      // Nothing to suggest once a carrier or provider answered for the number, or once it is filed.
      const next = setup(new Date('2026-09-11T12:00:00Z'));
      const answered = await next.router.fetch({ ...lookup, carrier_data: { ...lookup.carrier_data as JsonObject,
        routing: { ...(deferred as RoutingDeferred).routing, last_event_at: '2026-09-10T11:00:00Z' } } }, false);
      expect(answered.result.routing).not.toHaveProperty('input_suggested');
      const routing = { ...(deferred as RoutingDeferred).routing };
      expect(suggestedInput({ ...lookup, carrier: 'mondial-relay' }, routing)).toBeUndefined();
      expect(suggestedInput(lookup, { ...routing, input_needed: { carrier: 'gls-de', field: 'dpdPostcode' } })).toBeUndefined();
      // Nor from a country the carrier does not serve, without a country, or for a number of another shape.
      expect(suggestedInput({ ...lookup, carrier_data: { lookup_country_hint: 'DE' } }, routing)).toBeUndefined();
      expect(suggestedInput({ ...lookup, carrier_data: {} }, routing)).toBeUndefined();
      expect(suggestedInput({ ...lookup, tracking_number: '12345678901' }, routing)).toBeUndefined();
      expect(suggestedInput({ ...lookup, current_stage: 'delivered' }, routing)).toBeUndefined();
    });
    it.each([
      ['a delivered parcel', 'asendia', swissDpd, notFound('Asendia'), { current_stage: 'delivered' }],
      ['a parcel older than a month', 'asendia', swissDpd, notFound('Asendia'), { created_at: '2026-08-01T00:00:00Z' }],
      ['a linked journey', 'asendia', swissDpd, notFound('Asendia'),
        { carrier_data: { original_carrier: 'asendia', active_tracking_carrier: 'dpd', active_tracking_number: 'LOCAL1234' } }],
      ['a transient failure of the filed carrier', 'hermes-de', '12345678901231', new Error('timeout'), {}],
      ['a number the filed carrier already confirmed', 'hermes-de', '12345678901231', notFound('Hermes'),
        { carrier_data: { routing: state({ configured_carrier: 'hermes-de', confirmed_carrier: 'hermes-de', confirmed_number: '12345678901231' }) } }],
      ['another network of the filed brand', 'gls-de', '12345678901', notFound('GLS'), {}],
    ])('does not ask for %s', async (_label, carrier, trackingNumber, error, overrides) => {
      const { router, direct, recognize } = setup();
      direct.mockRejectedValue(error);
      await router.fetch(parcel({ carrier, tracking_number: trackingNumber, ...overrides }), false);
      expect(asked(recognize)).not.toContain(carrier === 'gls-de' ? 'gls-ch' : 'dpd');
    });

    it('retains a confirmed carrier even when the number shape points elsewhere', async () => {
      const { router, direct, recognize } = setup();
      direct.mockRejectedValue(new Error('upstream unavailable'));
      await router.fetch(parcel({ carrier: 'dhl', tracking_number: 'HL123456789FR', carrier_data: {
        routing: state({ configured_carrier: 'dhl', confirmed_carrier: 'dhl', confirmed_number: 'HL123456789FR' }),
      } }), false);
      expect(recognize).not.toHaveBeenCalled();
    });
    it('keeps recognition out of the health evidence of a check that reached nobody else', async () => {
      const { router, direct, health, recognize } = setup();
      recognize.mockImplementation(knows('dpd'));
      direct.mockRejectedValue(new Error('DPD down'));
      health.acquireTrackingProvider.mockResolvedValue({ token: null, retry_at: '2026-09-10T12:30:00Z' });
      await expect(router.fetch(parcel({ tracking_number: swissDpd }), false))
        .rejects.toMatchObject({ name: 'RoutingDeferredError', attempted: 0 });
      expect(recognize).toHaveBeenCalled();
      expect(direct).toHaveBeenCalledOnce();
    });
    it('confirms DPD named by an aggregator in the same check when recognition was not due', async () => {
      const { router, direct, universal, recognize } = setup();
      universal.mockResolvedValue({ ...history(), ...universalCarrierHints(['DPD Group'], swissDpd) });
      direct.mockResolvedValue(directValue('dpd'));
      const saved = { routing: state({ candidate_probes: probes(7, '2026-09-11T11:00:00Z', scraper.recognitionCandidates(swissDpd).map(({ carrier }) => carrier)) }) };
      const result = await router.fetch(parcel({ tracking_number: swissDpd, carrier_data: saved }), false);
      expect(recognize).not.toHaveBeenCalled();
      expect(direct.mock.calls.map(([, carrier]) => carrier)).toEqual(['dpd']);
      expect(result.result).toMatchObject({ auto_changed_to: 'dpd' });
    });
    it('confirms DPD named by an aggregator for a parcel older than a month', async () => {
      const { router, direct, universal } = setup();
      universal.mockResolvedValue({ ...history(), ...universalCarrierHints(['DPD Group'], swissDpd) });
      direct.mockImplementation(async (_lookup: JsonObject, carrier: string) => {
        if (carrier === 'asendia') throw notFound('Asendia');
        return directValue('dpd');
      });
      const result = await router.fetch(parcel({ carrier: 'asendia', tracking_number: swissDpd, created_at: '2026-07-01T00:00:00Z' }), false);
      expect(universal).toHaveBeenCalledOnce();
      expect(direct.mock.calls.map(([, carrier]) => carrier)).toEqual(['asendia', 'dpd']);
      expect(result.result).toMatchObject({ auto_changed_from: 'asendia', auto_changed_to: 'dpd' });
    });
  });
  it('does not borrow verification inputs from another carrier', async () => {
    const { router, direct, universal } = setup();
    // An 8-digit Mondial Relay shipment needs its own postcode; the DPD one stays DPD's.
    await router.fetch(parcel({ tracking_number: '76434219', dpd_postcode: '8000', carrier_data: { routing: state({ discovered_carrier: 'mondial-relay' }) } }), false);
    expect(direct).not.toHaveBeenCalled();
    expect(universal).toHaveBeenCalledOnce();
    expect(monitoring.reportRoutingEvent).toHaveBeenCalledWith('carrier_input_required', expect.anything());
  });
  it.each([
    ['2026-09-10T12:00:00Z', '2026-09-10T11:01:00Z', true],
    ['2026-09-10T12:00:00Z', '2026-09-10T11:00:00Z', false],
    ['2026-09-10T23:00:00Z', '2026-09-10T20:01:00Z', true],
    ['2026-09-10T23:00:00Z', '2026-09-10T20:00:00Z', false],
  ])('defers a 429 at %s only for a successful retrieval at %s', async (now, success, fresh) => {
    const { router, direct, universal } = setup(new Date(now));
    direct.mockRejectedValue(new UpstreamHttpError('UPS', 429, 2 * 3_600_000));
    const task = router.fetch(parcel({ carrier: 'ups', carrier_data: { routing: state({ configured_carrier: 'ups', last_success_at: success }) } }), false);
    if (fresh) {
      await expect(task).rejects.toMatchObject({ name: 'RoutingDeferredError', stale: false });
      expect(universal).not.toHaveBeenCalled();
    } else await expect(task).resolves.toMatchObject({ result: { tracking_provider: 'ParcelsApp' } });
  });
  it('does not confuse a recent failed attempt with a successful retrieval', async () => {
    const { router, direct, universal } = setup();
    direct.mockRejectedValue(new UpstreamHttpError('UPS', 429));
    await router.fetch(parcel({ carrier: 'ups', last_synced_at: '2026-09-10T11:59:00Z', sync_status: 'error' }), false);
    expect(universal).toHaveBeenCalledOnce();
  });
  it('skips a shared provider cooldown and tries a healthy provider', async () => {
    const { router, health, universal, wait } = setup();
    health.acquireTrackingProvider.mockResolvedValueOnce({ token: null, retry_at: '2026-09-10T13:00:00Z' });
    await router.fetch(parcel(), false);
    expect(universal.mock.calls.map(([source]) => source)).toEqual(['Ship24']);
    expect(wait).not.toHaveBeenCalled();
  });
  it('waits out a provider spaced after another check instead of skipping it', async () => {
    const { router, health, universal, wait } = setup();
    health.acquireTrackingProvider.mockResolvedValueOnce({ token: null, retry_at: '2026-09-10T12:00:04Z' });
    const result = await router.fetch(parcel(), false);
    const first = health.acquireTrackingProvider.mock.calls[0][0];
    expect(wait).toHaveBeenCalledExactlyOnceWith(4_000, undefined);
    expect(health.acquireTrackingProvider.mock.calls.map(([source]) => source)).toEqual([first, first]);
    expect(universal.mock.calls.map(([source]) => source)).toEqual([first]);
    expect(result.result).toMatchObject({ tracking_provider: first, routing: { failures: {} } });
    expect(monitoring.reportRoutingEvent).toHaveBeenCalledWith('provider_paced', expect.objectContaining({ provider: first }));
  });
  it('records a provider still spaced after two waits as busy and moves on', async () => {
    const { router, health, universal, wait } = setup();
    health.acquireTrackingProvider.mockResolvedValueOnce({ token: null, retry_at: '2026-09-10T12:00:02Z' })
      .mockResolvedValueOnce({ token: null, retry_at: '2026-09-10T12:00:03Z' })
      .mockResolvedValueOnce({ token: null, retry_at: '2026-09-10T12:00:05Z' });
    const result = await router.fetch(parcel(), false);
    const first = health.acquireTrackingProvider.mock.calls[0][0];
    expect(wait.mock.calls.map(([ms]) => ms)).toEqual([2_000, 3_000]);
    expect(universal).toHaveBeenCalledOnce();
    expect(universal.mock.calls[0][0]).not.toBe(first);
    expect(result.result.routing).toMatchObject({ failures: { [first]: { kind: 'rate_limited', retry_at: '2026-09-10T12:00:05Z' } } });
  });
  it('does not wait for a spaced provider when the wait would cut into its lookup budget', async () => {
    const { router, health, universal, wait } = setup();
    let clock = 0;
    vi.spyOn(performance, 'now').mockImplementation(() => clock);
    const total = scraper.universalPlan({ carriers: ['unknown'], trackingNumber: 'TEST1234' }).sources
      .reduce((sum, source) => sum + scraper.universalSourceBudget(source) + 5_000, 0);
    // The check reaches the first provider with 20 s of its universal budget left.
    health.acquireTrackingProvider.mockImplementationOnce(async () => {
      clock += total - 20_000;
      return { token: null, retry_at: '2026-09-10T12:00:02Z' };
    });
    await router.fetch(parcel(), false);
    expect(wait).not.toHaveBeenCalled();
    expect(universal).toHaveBeenCalledOnce();
    expect(universal.mock.calls[0][2]).toBe(15_000);
  });
  it('takes an answer another check of the run already has without contacting the provider', async () => {
    const { health, universal, direct, recognize, recognizeBrowser } = setup();
    const reusedUniversal = vi.fn((source: string) => source === 'ParcelsApp' ? Promise.resolve(history()) : undefined);
    const router = new TrackingRouter({ direct, universal, health, recognize, recognizeBrowser, reusedUniversal, now: () => time });
    const result = await router.fetch(parcel(), false);
    expect(result.result).toMatchObject({ tracking_provider: 'ParcelsApp', routing: { preferred_provider: 'ParcelsApp' } });
    expect(reusedUniversal).toHaveBeenCalledExactlyOnceWith('ParcelsApp', 'TEST1234', null, null, null);
    expect(universal).not.toHaveBeenCalled();
    expect(health.acquireTrackingProvider).not.toHaveBeenCalled();
    expect(health.finishTrackingProvider).not.toHaveBeenCalled();
    // A failure it reuses counts for this parcel as it did for the check that asked.
    reusedUniversal.mockImplementation((source: string) => source === 'ParcelsApp' ? Promise.reject(new NotFoundError(source)) : undefined);
    const next = await router.fetch(parcel(), false);
    expect(next.result).toMatchObject({ tracking_provider: 'Ship24', routing: { failures: { ParcelsApp: { kind: 'not_found' } } } });
    expect(health.acquireTrackingProvider).toHaveBeenCalledExactlyOnceWith('Ship24');
  });
  it('retains provider-specific Retry-After and stops cascading on a fresh universal 429', async () => {
    const { router, universal, health } = setup();
    universal.mockRejectedValue(new UpstreamHttpError('Ship24', 429, 2 * 3_600_000));
    await expect(router.fetch(parcel({ carrier_data: { routing: state({ preferred_provider: 'Ship24', last_success_at: '2026-09-10T11:30:00Z' }) } }), false))
      .rejects.toMatchObject({ stale: false, routing: { failures: { 'Ship24': { retry_at: '2026-09-10T14:00:00.000Z' } } } });
    expect(universal).toHaveBeenCalledOnce();
    expect(health.finishTrackingProvider).toHaveBeenCalledWith('Ship24', 'lease', 'rate_limited', 7_200_000, expect.any(Number));
  });
  it('never moves the freshness watermark past the time of the check', async () => {
    const { router, direct } = setup();
    // A scan read two hours late would otherwise make the next real update look older.
    direct.mockResolvedValue({ ...directValue(), result: history('2026-09-10T13:30:00Z') });
    const result = await router.fetch(parcel({ carrier: 'ups' }), false);
    expect(result.result.routing).toMatchObject({ last_event_at: time.toISOString() });
  });
  it('keeps provider circuits closed when providers answer without history for an unknown number', async () => {
    const { router, universal, health } = setup();
    universal.mockImplementation(async (source: string) => {
      throw source === 'Ship24' ? new NotFoundError(source)
        : source === '17TRACK' ? new UpstreamHttpError(source, 502)
          : new IndeterminateError(source, `${source} has no usable shipment history`);
    });
    await expect(router.fetch(parcel(), false)).rejects.toMatchObject({ routing: { failures: {
      Ship24: { kind: 'not_found' },
      ParcelsApp: { kind: 'no_history', retry_at: '2026-09-10T12:15:00.000Z' },
      '17TRACK': { kind: 'transport' },
    } } });
    // Only the HTTP 5xx says anything about a provider's own health.
    expect(health.finishTrackingProvider.mock.calls.map(([source, , kind]) => [source, kind])).toEqual([
      ['ParcelsApp', 'not_found'], ['Ship24', 'not_found'], ['17TRACK', 'transport'],
    ]);
  });
  it('keeps provider circuits closed when providers refuse the number', async () => {
    const { router, universal, health } = setup();
    universal.mockImplementation(async (source: string) => { throw new InvalidInputError(source, `Invalid ${source} tracking number`); });
    await expect(router.fetch(parcel(), false)).rejects.toMatchObject({ routing: { failures: {
      Ship24: { kind: 'invalid_input', user_error: 'carrier:invalid_input' } } } });
    expect(health.finishTrackingProvider).toHaveBeenCalled();
    expect(health.finishTrackingProvider.mock.calls.every((call) => call[2] === 'not_found')).toBe(true);
  });
  it('reaches 17TRACK in the same check after the first two providers fail, then remembers it', async () => {
    const first = setup();
    first.universal.mockRejectedValueOnce(new Error('ParcelsApp down')).mockRejectedValueOnce(new Error('Ship24 down'));
    const result = await first.router.fetch(parcel(), false);
    expect(first.universal.mock.calls.map(([source]) => source)).toEqual(['ParcelsApp', 'Ship24', '17TRACK']);
    expect(result.result.routing).toMatchObject({ preferred_provider: '17TRACK' });
    const second = setup();
    await second.router.fetch(parcel({ carrier_data: result.result }), false);
    expect(second.universal.mock.calls.map(([source]) => source)).toEqual(['17TRACK']);
  });
  it('does not ask a provider the carrier\'s plan left out, even for a number it would lead', async () => {
    // A ten-digit number puts Ship24 first, unless the carrier's evidence leaves it out.
    fixedPlan('ParcelsApp', '17TRACK');
    const { router, universal } = setup();
    universal.mockRejectedValue(new NotFoundError('provider'));
    await expect(router.fetch(parcel({ tracking_number: '1234567891' }), false)).rejects.toBeInstanceOf(RoutingDeferred);
    expect(universal.mock.calls.map(([source]) => source)).toEqual(['ParcelsApp', '17TRACK']);
  });
  it('tries every enabled provider only once and includes Postal Ninja only when enabled', async () => {
    const { direct, universal, health } = setup();
    universal.mockRejectedValue(new Error('down'));
    for (const enablePostalNinja of [false, true]) {
      universal.mockClear();
      const router = new TrackingRouter({ direct, universal, health, now: () => time, enablePostalNinja });
      await expect(router.fetch(parcel(), false)).rejects.toBeInstanceOf(RoutingDeferred);
      // The scraper's coverage orders them; each is asked once.
      expect(universal.mock.calls.map(([source]) => source).sort()).toEqual(
        ['17TRACK', 'ParcelsApp', ...(enablePostalNinja ? ['Postal Ninja'] : []), 'Ship24']);
    }
  });
  it('never starts a check with Postal Ninja, even from a cursor saved over four providers', async () => {
    let elapsed = 0;
    vi.spyOn(performance, 'now').mockImplementation(() => elapsed);
    const { direct, universal, health } = setup();
    // Each lookup overruns the whole budget, so a check asks one provider and the cursor moves on.
    universal.mockImplementation(async () => { elapsed += 200_000; throw new Error('overrun'); });
    const router = new TrackingRouter({ direct, universal, health, now: () => time, enablePostalNinja: true });
    let routing: JsonObject = state({ discovery_cursor: 3 });
    const starts: string[] = [];
    for (let check = 0; check < 4; check++) {
      universal.mockClear();
      const deferred = await router.fetch(parcel({ carrier_data: { routing } }), false).catch((error: unknown) => error);
      expect(deferred).toBeInstanceOf(RoutingDeferred);
      starts.push(universal.mock.calls[0][0]);
      // Cooldowns aside, the next check starts where the cursor points.
      routing = { ...(deferred as RoutingDeferred).routing, failures: {} };
    }
    expect(starts).toEqual(['ParcelsApp', 'Ship24', '17TRACK', 'ParcelsApp']);
  });
  it('counts contacted providers so a check that reached nobody is not health evidence', async () => {
    const { router, direct, universal } = setup();
    direct.mockRejectedValue(new Error('carrier down'));
    universal.mockRejectedValue(new Error('down'));
    await expect(router.fetch(parcel({ carrier: 'la-poste' }), false)).rejects.toMatchObject({ attempted: 4 });
    const cooling = Object.fromEntries(['la-poste', 'Ship24', 'ParcelsApp', '17TRACK']
      .map((provider) => [provider, { count: 1, kind: 'transport', retry_at: '2026-09-10T13:00:00.000Z' }]));
    direct.mockClear(); universal.mockClear();
    await expect(router.fetch(parcel({ carrier: 'la-poste', carrier_data: { routing: state({ configured_carrier: 'la-poste', failures: cooling }) } }), false))
      .rejects.toMatchObject({ attempted: 0, stale: false });
    expect(direct).not.toHaveBeenCalled();
    expect(universal).not.toHaveBeenCalled();
  });
  it('gives 17TRACK a usable budget even after slow direct and universal failures', async () => {
    let elapsed = 0;
    vi.spyOn(performance, 'now').mockImplementation(() => elapsed);
    const { router, direct, universal } = setup();
    direct.mockImplementation(async () => { elapsed += 90_000.125; throw new Error('slow carrier'); });
    universal.mockImplementation(async (source, _number, timeoutMs) => {
      expect(timeoutMs).toBeGreaterThanOrEqual(29_998);
      expect(Number.isInteger(timeoutMs)).toBe(true);
      if (source === '17TRACK') return history();
      elapsed += timeoutMs + 5_000.125;
      throw new Error('slow universal');
    });
    await expect(router.fetch(parcel({ carrier: 'dhl-ecommerce' }), false))
      .resolves.toMatchObject({ result: { tracking_provider: '17TRACK' } });
    expect(universal).toHaveBeenCalledTimes(3);
  });
  it("skips an unavailable provider without spending another provider's attempt", async () => {
    const { router, health, universal } = setup();
    health.acquireTrackingProvider.mockResolvedValueOnce({ token: null, retry_at: '2026-09-10T13:00:00Z' });
    universal.mockRejectedValueOnce(new Error('Ship24 down'));
    await expect(router.fetch(parcel(), false)).resolves.toMatchObject({ result: { tracking_provider: '17TRACK' } });
    expect(universal.mock.calls.map(([source]) => source)).toEqual(['Ship24', '17TRACK']);
  });
  it('stops when a provider overruns the total fallback budget', async () => {
    let elapsed = 0;
    vi.spyOn(performance, 'now').mockImplementation(() => elapsed);
    const { router, universal } = setup();
    universal.mockImplementation(async () => { elapsed = 121_000; throw new Error('overrun'); });
    await expect(router.fetch(parcel(), false)).rejects.toBeInstanceOf(RoutingDeferred);
    expect(universal).toHaveBeenCalledOnce();
  });
  it('does one daily scheduled comparison and switches only for newer data', async () => {
    const { router, universal } = setup();
    universal.mockResolvedValueOnce(history('2026-09-10T10:00:00Z')).mockResolvedValueOnce(history('2026-09-10T11:00:00Z'));
    const result = await router.fetch(parcel({ carrier_data: { routing: state({ preferred_provider: 'Ship24', last_probe_at: '2026-09-08T12:00:00Z' }) } }), true);
    expect(universal.mock.calls.map(([source]) => source)).toEqual(['Ship24', 'ParcelsApp']);
    expect(result.result.routing).toMatchObject({ preferred_provider: 'ParcelsApp', probe_cursor: 1 });
  });
  it.each([false, true])('keeps affinity on older alternative data (scheduled=%s)', async (scheduled) => {
    const { router, universal } = setup();
    universal.mockResolvedValueOnce(history()).mockResolvedValueOnce(history('2026-09-09T11:00:00Z'));
    const result = await router.fetch(parcel({ carrier_data: { routing: state({ preferred_provider: '17TRACK', last_probe_at: '2026-09-08T12:00:00Z' }) } }), scheduled);
    expect(result.result.routing).toMatchObject({ preferred_provider: '17TRACK' });
    expect(universal).toHaveBeenCalledTimes(scheduled ? 2 : 1);
  });
  it('does not throw away successful history when a shadow check fails', async () => {
    const { router, universal } = setup();
    universal.mockResolvedValueOnce(history()).mockRejectedValueOnce(new UpstreamHttpError('ParcelsApp', 429));
    await expect(router.fetch(parcel({ carrier_data: { routing: state({ preferred_provider: 'Ship24', last_probe_at: '2026-09-08T12:00:00Z', last_success_at: '2026-09-10T11:50:00Z' }) } }), true))
      .resolves.toMatchObject({ result: { tracking_provider: 'Ship24' } });
  });
  it('tries a new manual carrier, then recovers the previous confirmed route and its own inputs', async () => {
    const { router, direct } = setup(); direct.mockRejectedValueOnce(new Error('down')).mockResolvedValueOnce(directValue('dpd'));
    const result = await router.fetch(parcel({ carrier: 'dhl', carrier_data: { routing: state({ configured_carrier: 'dpd',
      confirmed_carrier: 'dpd', confirmed_number: 'TEST1234', confirmed_postcode: '8000', last_success_at: '2026-09-10T11:00:00Z' }) } }), false);
    expect(direct.mock.calls.map(([, carrier]) => carrier)).toEqual(['dhl', 'dpd']);
    expect(direct.mock.calls[1][0].dpd_postcode).toBe('8000');
    expect(result.result.routing).toMatchObject({ configured_carrier: 'dpd', confirmed_carrier: 'dpd' });
  });
  it('keeps a prior confirmed route when the new choice has no direct support', async () => {
    const { router, direct, universal } = setup();
    const result = await router.fetch(parcel({ carrier: 'intl-post', carrier_data: { routing: state({ configured_carrier: 'ups', confirmed_carrier: 'ups', confirmed_number: 'TEST1234' }) } }), false);
    expect(direct.mock.calls.map(([, carrier]) => carrier)).toEqual(['ups']);
    expect(universal).not.toHaveBeenCalled();
    expect(result.correction?.carrier).toBe('ups');
    expect(result.result.routing).toMatchObject({ configured_carrier: 'ups', confirmed_carrier: 'ups' });
  });
  it('does not adopt an old route for a different tracking number', async () => {
    const { router, direct } = setup();
    await router.fetch(parcel({ carrier_data: { routing: state({ confirmed_carrier: 'ups', confirmed_number: 'OTHER123' }) } }), false);
    expect(direct).not.toHaveBeenCalled();
  });
  it('uses the confirmed delivery-leg number for universal recovery', async () => {
    const { router, direct, universal } = setup(); direct.mockRejectedValue(new Error('delivery unavailable'));
    await router.fetch(parcel({ carrier: 'dhl', carrier_data: { original_carrier: 'dhl', active_tracking_carrier: 'swiss-post',
      active_tracking_number: 'LOCAL1234' } }), false);
    expect(universal).toHaveBeenCalledWith('ParcelsApp', 'LOCAL1234', expect.any(Number), null, 'Europe/Zurich', 'swiss-post');
  });
  it('forwards the stored delivery postcode to universal providers', async () => {
    const { router, universal } = setup();
    await router.fetch(parcel({ dpd_postcode: '8000' }), false);
    expect(universal).toHaveBeenCalledWith('ParcelsApp', 'TEST1234', expect.any(Number), '8000', null, null);
  });
  it('gives universal providers the parcel carrier\'s zone for scans without a trustworthy one', async () => {
    const { router, direct, universal } = setup(); direct.mockRejectedValue(new Error('carrier down'));
    await router.fetch(parcel({ carrier: 'dpd' }), false);
    expect(universal).toHaveBeenCalledWith('ParcelsApp', 'TEST1234', expect.any(Number), null, 'Europe/Zurich', 'dpd');
  });
  it('asks universal providers about no carrier for an unknown parcel, not even one a provider named', async () => {
    const { router, direct, universal } = setup(); direct.mockRejectedValue(new Error('carrier down'));
    await router.fetch(parcel({ carrier_data: { routing: state({ discovered_carrier: 'dpd' }) } }), false);
    expect(universal).toHaveBeenCalledOnce();
    expect(universal.mock.calls[0][5]).toBeNull();
    // A carrier that confirmed the number itself is asked about.
    await router.fetch(parcel({ carrier_data: { routing: state({ confirmed_carrier: 'dpd', confirmed_number: 'TEST1234' }) } }), false);
    expect(universal.mock.lastCall?.[5]).toBe('dpd');
  });
  it.each(['ParcelsApp', 'Ship24'] as const)('gives %s the confirmed carrier\'s zone when the parcel carrier keeps UTC', async (provider) => {
    vi.spyOn(scraper, 'universalPlan').mockReturnValue({
      sources: [provider], carrier: null, tier: () => 'unknown', rank: () => 0,
    });
    const { router, direct, universal } = setup(); direct.mockRejectedValue(new Error('carrier down'));
    const confirmed = (number: string) => ({ routing: state({ configured_carrier: 'asendia', confirmed_carrier: 'dpd', confirmed_number: number }) });
    // The provider is asked about the confirmed carrier too.
    await router.fetch(parcel({ carrier: 'asendia', carrier_data: confirmed('TEST1234') }), false);
    expect(universal).toHaveBeenLastCalledWith(provider, 'TEST1234', expect.any(Number), null, 'Europe/Zurich', 'dpd');
    // A route confirmed for another number says nothing about this one.
    await router.fetch(parcel({ carrier: 'asendia', carrier_data: confirmed('OTHER123') }), false);
    expect(universal).toHaveBeenLastCalledWith(provider, 'TEST1234', expect.any(Number), null, null, 'asendia');
    // The parcel carrier's own zone still comes first; the carrier asked about is still the confirmed one.
    await router.fetch(parcel({ carrier: 'dhl', carrier_data: { routing: state({ configured_carrier: 'dhl', confirmed_carrier: 'dpd-fr', confirmed_number: 'TEST1234' }) } }), false);
    expect(universal).toHaveBeenLastCalledWith(provider, 'TEST1234', expect.any(Number), null, 'Europe/Berlin', 'dpd-fr');
  });
  it.each([
    // Quickpac reports Swiss wall time without an offset; the stored events read it in Zurich.
    ['quickpac', '2026-07-01T08:00:00.000Z'],
    // A zone no test machine is likely to run in, so the process zone cannot pass for it.
    ['india-post', '2026-07-01T04:30:00.000Z'],
  ])('reads a naive local last update in its carrier\'s zone for the freshness watermark (%s)', async (carrier, watermark) => {
    const { router, direct } = setup(new Date('2026-07-01T12:00:00Z'));
    const local = '2026-07-01T10:00:00.000';
    direct.mockResolvedValue({ ...directValue(carrier), result: { status: 'in_transit', current_stage: 'in_transit', last_update: local,
      events: [{ time: local, description: 'In transit', stage: 'in_transit' }] } });
    const result = await router.fetch(parcel({ carrier }), false);
    expect(result.result.routing).toMatchObject({ last_event_at: watermark });
  });
  it('sends no postcode to universal providers when the parcel stores none', async () => {
    const { router, universal } = setup();
    await router.fetch(parcel(), false);
    expect(universal).toHaveBeenCalledWith('ParcelsApp', 'TEST1234', expect.any(Number), null, null, null);
  });
  it('fails closed when shared coordination is unavailable, with a persisted retry', async () => {
    const { router, health, universal } = setup(); health.acquireTrackingProvider.mockRejectedValue(new Error('db down'));
    await expect(router.fetch(parcel(), false)).rejects.toMatchObject({ routing: { next_check_at: '2026-09-10T12:15:00.000Z' } });
    expect(universal).not.toHaveBeenCalled();
  });
  it('keeps valid data if the health completion write fails', async () => {
    const { router, health } = setup(); health.finishTrackingProvider.mockRejectedValue(new Error('db down'));
    await expect(router.fetch(parcel(), false)).resolves.toMatchObject({ result: { tracking_provider: 'ParcelsApp' } });
  });
  it('checks cancellation before contacting a provider', async () => {
    const { router, universal } = setup();
    await expect(router.fetch(parcel(), false, AbortSignal.abort())).rejects.toThrow();
    expect(universal).not.toHaveBeenCalled();
  });
  it('computes Zurich day/night windows across DST and resets config-specific cooldowns', () => {
    expect(freshnessWindow(new Date('2026-12-10T06:59:00Z'))).toBe(10_800_000);
    expect(freshnessWindow(new Date('2026-12-10T07:00:00Z'))).toBe(3_600_000);
    expect(routingState(parcel({ carrier: 'ups', carrier_data: { routing: state({ next_check_at: '2027-01-01T00:00:00Z' }) } })).next_check_at).toBeUndefined();
  });
  it('does not guess a regional carrier from a brand or cross-border provider list', () => {
    expect(universalCarrierHints(['DHL']).discovered_carrier).toBeUndefined();
    // A bare brand resolves only when the number leaves one of its networks.
    expect(universalCarrierHints(['DPD Group'], '06080000000002')).toMatchObject({ discovered_carrier: 'dpd' });
    expect(universalCarrierHints(['DPD Group'], '06460000000002').discovered_carrier).toBeUndefined();
    expect(universalCarrierHints(['UPS', 'Swiss Post']).discovered_carrier).toBeUndefined();
    expect(universalCarrierHints(['UPS'])).toMatchObject({ discovered_carrier: 'ups' });
    expect(universalCarrierHints(['Posti'])).toMatchObject({ discovered_carrier: 'posti' });
    expect(universalCarrierHints(['La Poste', 'Posti']).discovered_carrier).toBeUndefined();
    expect(universalCarrierHints(['https://private/token'])).toEqual({ reported_carriers: [] });
    expect(universalCarrierHints(['Chronopost France'])).toMatchObject({ discovered_carrier: 'chronopost' });
    expect(universalCarrierHints(['Correos Chile']).discovered_carrier).toBeUndefined();
  });
  it('reports only carrier names the catalog does not know, once per parcel', async () => {
    const { router, universal } = setup();
    const reported = ['La Poste', 'Posti', 'Finland Post', 'UPU', 'Universal Postal Union', 'Chronopost Portugal', 'DHL Express', 'Example Parcel Co'];
    universal.mockResolvedValue({ ...history(), reported_carriers: reported });
    const coverage = () => vi.mocked(monitoring.reportRoutingEvent).mock.calls
      .filter(([code]) => code === 'carrier_coverage_discovered').map(([, context]) => context.provider);
    const first = await router.fetch(parcel(), false);
    expect(coverage()).toEqual(['Example Parcel Co']);
    expect(first.result.routing).toMatchObject({ reported_carriers_seen: reported });
    vi.mocked(monitoring.reportRoutingEvent).mockClear();
    await router.fetch(parcel({ carrier_data: { routing: first.result.routing } }), false);
    expect(coverage()).toEqual([]);
  });

  it('marks a universal result the carrier answered too, without a time zone for its newest scan', async () => {
    fixedPlan('Ship24', '17TRACK');
    const { router, direct } = setup();
    direct.mockResolvedValue(yearlessYamato());
    const answered = await router.fetch(parcel({ carrier: 'yamato', tracking_number: '123456789012' }), false);
    expect(answered.result).toMatchObject({ tracking_provider: 'Ship24', carrier_answered: true });
    // A carrier that failed did not answer.
    const failed = setup();
    failed.direct.mockRejectedValue(new Error('down'));
    const fallback = await failed.router.fetch(parcel({ carrier: 'yamato', tracking_number: '123456789012' }), false);
    expect(fallback.result.tracking_provider).toBe('Ship24');
    expect(fallback.result).not.toHaveProperty('carrier_answered');
  });
  it('leaves out a provider\'s delivery the filed carrier\'s own answer disputes, and reports it', async () => {
    fixedPlan('ParcelsApp');
    // A BRT import a provider marked delivered the day BRT took it over; the number, wording and clocks are invented.
    const { router, direct, universal } = setup(new Date('2026-10-19T20:00:00Z'));
    direct.mockResolvedValue({ sourceCarrierId: 'brt', swissPostReady: null, handoffFallbackErrorType: null,
      result: { status: 'in_transit', current_stage: 'registered', last_status_text: 'Shipping info sent', last_update: null,
        events: [{ local_time: '2026-10-12T10:10:00', description: 'Shipping info sent', stage: 'registered' }] } });
    universal.mockResolvedValue({ status: 'delivered', current_stage: 'delivered', last_status_text: 'Delivered',
      delivered_at: '2026-10-12T01:00:01Z', last_update: '2026-10-17T01:00:02Z', events: [
        { time: '2026-10-17T01:00:02Z', description: 'In viaggio', stage: 'in_transit' },
        { time: '2026-10-12T01:00:01Z', description: 'Delivered', stage: 'delivered' }] });
    const routed = await router.fetch(parcel({ carrier: 'brt', tracking_number: '12345678901234' }), false);
    expect(routed.result).toMatchObject({ tracking_provider: 'ParcelsApp', carrier_answered: true,
      status: 'in_transit', current_stage: 'registered', last_status_text: 'Shipping info sent' });
    expect(routed.result).not.toHaveProperty('delivered_at');
    expect(routed.result.events).toEqual([expect.objectContaining({ description: 'In viaggio' })]);
    expect(monitoring.reportRoutingEvent).toHaveBeenCalledWith('delivery_disputed', expect.objectContaining({ carrier: 'brt', provider: 'ParcelsApp' }));
  });
  describe('a filed carrier\'s partial answer', () => {
    // UPS's public example number; the clocks, wording and estimate are invented.
    const ups = (overrides: JsonObject = {}) => parcel({ carrier: 'ups', tracking_number: '1Z999AA10123456784', ...overrides });
    const summary = (): RoutedResult => ({ sourceCarrierId: 'ups', swissPostReady: null, handoffFallbackErrorType: null,
      result: { status: 'in_transit', current_stage: 'in_transit', last_status_text: 'Synthetic status', last_update: '2026-09-10T11:00:00Z',
        expected_delivery: '2026-09-12', summary_only: true, events: [] } });
    const fuller = (): CarrierResult => ({ status: 'in_transit', current_stage: 'in_transit', last_update: '2026-09-10T11:00:00Z', events: [
      { time: '2026-09-10T11:00:00Z', description: 'Departed facility', stage: 'in_transit' },
      { time: '2026-09-09T18:00:00Z', description: 'Picked up', stage: 'accepted' },
    ] });

    it('takes a provider\'s fuller history with the carrier\'s facts, and asks the carrier first again', async () => {
      fixedPlan('Ship24', '17TRACK');
      const { router, direct, universal } = setup();
      direct.mockResolvedValue(summary());
      universal.mockResolvedValue(fuller());
      const result = await router.fetch(ups(), false);
      expect(result.sourceCarrierId).toBe('unknown');
      expect(result.result).toMatchObject({ tracking_provider: 'Ship24', carrier_answered: true, expected_delivery: '2026-09-12' });
      expect(result.result.events).toHaveLength(2);
      expect(result.result.routing).toMatchObject({ preferred_provider: 'Ship24', confirmed_carrier: 'ups' });
      expect((result.result.routing as JsonObject).direct_retry_at).toBeUndefined();
      expect(universal).toHaveBeenCalledOnce();
      const next = setup(new Date('2026-09-10T12:15:00Z'));
      next.direct.mockResolvedValue(summary());
      await next.router.fetch(ups({ carrier_data: { routing: result.result.routing } }), false);
      expect(next.direct).toHaveBeenCalledOnce();
    });

    it('stands against a history that is behind it, and asks the providers again only at their cadence', async () => {
      fixedPlan('Ship24', '17TRACK');
      const { router, direct, universal } = setup();
      direct.mockResolvedValue(summary());
      universal.mockResolvedValue(history('2026-09-10T09:00:00Z'));
      const result = await router.fetch(ups(), false);
      expect(result).toMatchObject({ sourceCarrierId: 'ups', result: { summary_only: true, routing: { partial_checked_at: time.toISOString() } } });
      expect(result.result).not.toHaveProperty('tracking_provider');
      expect(universal).toHaveBeenCalledTimes(2);
      const soon = setup(new Date('2026-09-10T12:10:00Z'));
      soon.direct.mockResolvedValue(summary());
      expect(await soon.router.fetch(ups({ carrier_data: { routing: result.result.routing } }), false)).toMatchObject({ sourceCarrierId: 'ups' });
      expect(soon.universal).not.toHaveBeenCalled();
      const later = setup(new Date('2026-09-10T12:15:00Z'));
      later.direct.mockResolvedValue(summary());
      later.universal.mockResolvedValue(fuller());
      expect((await later.router.fetch(ups({ carrier_data: { routing: result.result.routing } }), false)).result)
        .toMatchObject({ tracking_provider: 'Ship24' });
    });

    it('stands when a provider is rate limited after a recent check', async () => {
      fixedPlan('Ship24');
      const { router, direct, universal } = setup();
      direct.mockResolvedValue(summary());
      universal.mockRejectedValue(new UpstreamHttpError('Ship24', 429));
      const result = await router.fetch(ups({ carrier_data: { routing: state({ configured_carrier: 'ups', last_success_at: '2026-09-10T11:50:00Z' }) } }), false);
      expect(result).toMatchObject({ sourceCarrierId: 'ups', result: { summary_only: true } });
    });

    it('is a carrier\'s complete answer when it is not partial', async () => {
      const { router, direct, universal } = setup();
      direct.mockResolvedValue({ ...summary(), result: { ...fuller() } });
      expect(await router.fetch(ups(), false)).toMatchObject({ sourceCarrierId: 'ups' });
      expect(universal).not.toHaveBeenCalled();
    });
  });
  it('ignores an older parcel of another carrier that a universal returns for a reused number', async () => {
    fixedPlan('Ship24', '17TRACK');
    const { router, direct, universal } = setup();
    direct.mockResolvedValue(yearlessYamato());
    universal.mockResolvedValueOnce({ ...history('2026-01-09T13:19:00Z'), reported_carriers: ['FedEx', 'GLS'] });
    const result = await router.fetch(parcel({ carrier: 'yamato', tracking_number: '123456789012', created_at: '2026-09-01T00:00:00Z' }), false);
    expect(direct).toHaveBeenCalledExactlyOnceWith(expect.objectContaining({ tracking_number: '123456789012' }), 'yamato');
    // The next provider is asked after the first one's foreign parcel.
    expect(universal.mock.calls.map(([source]) => source)).toEqual(['Ship24', '17TRACK']);
    expect(result.result).toMatchObject({ tracking_provider: '17TRACK', last_update: '2026-09-10T11:00:00Z' });
    expect(result.result.direct_local_history).toMatchObject({ carrier: 'yamato', number: '123456789012',
      events: [{ provider_time_text: '09月01日 10:00', stage: 'accepted' }] });
    expect(result.result.routing).toMatchObject({ failures: { Ship24: { kind: 'no_history' } } });
    expect(vi.mocked(monitoring.reportRoutingEvent)).toHaveBeenCalledWith('foreign_history_rejected', expect.objectContaining({ provider: 'Ship24' }));
  });
  it.each([
    ['unknown', 'Example Post'], ['intl-post', 'Example Post'],
    // Filed under the carrier that reused the number, whose own tracker does not know the new parcel yet.
    ['happy-post', 'Happy-Post'],
  ])('ignores an old label a universal returns for a reused number filed under %s', async (carrier, name) => {
    fixedPlan('ParcelsApp', '17TRACK');
    const { router, direct, universal } = setup();
    direct.mockRejectedValue(new NotFoundError('Happy-Post'));
    // The number's earlier parcel: a label from last winter that never moved, naming the carrier that reused it.
    universal.mockResolvedValueOnce({ status: 'pending', current_stage: 'registered', last_update: '2026-01-08T08:36:05Z',
      reported_carriers: [name], events: [{ time: '2026-01-08T06:58:19Z', description: 'Label generated', stage: 'registered' },
        { time: '2026-01-08T08:36:05Z', description: 'shipped', stage: 'pending' }] });
    universal.mockResolvedValueOnce(history());
    const result = await router.fetch(parcel({ carrier, created_at: '2026-09-01T00:00:00Z' }), false);
    expect(universal.mock.calls.map(([source]) => source)).toEqual(['ParcelsApp', '17TRACK']);
    expect(result.result).toMatchObject({ tracking_provider: '17TRACK', last_update: '2026-09-10T11:00:00Z' });
    expect(result.result.routing).toMatchObject({ failures: { ParcelsApp: { kind: 'no_history' } } });
    expect(vi.mocked(monitoring.reportRoutingEvent)).toHaveBeenCalledWith('foreign_history_rejected', expect.objectContaining({ provider: 'ParcelsApp' }));
  });
  describe('the filed carrier\'s own answer for a reused number', () => {
    // Happy-Post's tracker dates its steps on wall clocks in no zone; the number and steps are invented.
    const filed = parcel({ carrier: 'happy-post', tracking_number: '12345678', created_at: '2026-09-01T00:00:00Z' });
    const happyPost = (local: string | undefined, stage = 'registered'): RoutedResult => ({ sourceCarrierId: 'happy-post',
      swissPostReady: null, handoffFallbackErrorType: null, result: { status: stage === 'delivered' ? 'delivered' : 'pending',
        current_stage: stage, last_update: null, ...(local ? { last_update_local: local } : {}),
        events: [{ ...(local ? { local_time: local } : { provider_time_text: '08/01 09:36' }),
          description: stage === 'delivered' ? 'Delivered' : 'Label created', stage }] } });
    // The number's earlier parcel: a label from last winter that never moved.
    const lastWinter = () => happyPost('2026-01-08T09:36:05');

    it('is no history for the parcel, and a provider with the new parcel\'s history serves it', async () => {
      fixedPlan('ParcelsApp', '17TRACK');
      const { router, direct, universal } = setup();
      direct.mockResolvedValue(lastWinter());
      const result = await router.fetch(filed, false);
      expect(direct).toHaveBeenCalledExactlyOnceWith(expect.objectContaining({ tracking_number: '12345678' }), 'happy-post');
      expect(universal).toHaveBeenCalledOnce();
      expect(result).toMatchObject({ sourceCarrierId: 'unknown', result: { tracking_provider: 'ParcelsApp', last_update: '2026-09-10T11:00:00Z',
        // The carrier answered without history for this parcel: asked again within the quarter hour, as when it lists none.
        routing: { failures: { 'happy-post': { kind: 'no_history', count: 1, retry_at: '2026-09-10T12:15:00.000Z' } },
          direct_retry_at: '2026-09-10T12:15:00.000Z' } } });
      for (const key of ['carrier_answered', 'direct_local_fallback', 'direct_local_history']) expect(result.result).not.toHaveProperty(key);
      expect(result.result.routing).not.toHaveProperty('confirmed_carrier');
      expect(monitoring.reportRoutingEvent).toHaveBeenCalledWith('foreign_history_rejected', expect.objectContaining({ carrier: 'happy-post', provider: 'happy-post' }));
      expect(monitoring.reportRoutingEvent).toHaveBeenCalledWith('provider_failed', expect.objectContaining({ provider: 'happy-post', category: 'no_history' }));
    });

    it('is not served while no provider has the parcel either', async () => {
      fixedPlan('ParcelsApp');
      const { router, direct, universal } = setup();
      direct.mockResolvedValue(lastWinter());
      universal.mockRejectedValue(new NotFoundError('ParcelsApp'));
      const deferred = await router.fetch(filed, false).catch((error: unknown) => error);
      expect(deferred).toBeInstanceOf(RoutingDeferred);
      expect((deferred as RoutingDeferred).routing).toMatchObject({ failures: { 'happy-post': { kind: 'no_history' } } });
      expect((deferred as RoutingDeferred).routing).not.toHaveProperty('confirmed_carrier');
    });

    it.each([
      ['a label two months old', '2026-07-03T09:36:05', 'registered'],
      ['an old delivery, which may be a parcel looked up again', '2026-01-08T09:36:05', 'delivered'],
      ['no readable date', undefined, 'registered'],
    ])('serves %s while no provider has the parcel', async (_label, local, stage) => {
      fixedPlan('ParcelsApp');
      const { router, direct, universal } = setup();
      direct.mockResolvedValue(happyPost(local, stage));
      universal.mockRejectedValue(new NotFoundError('ParcelsApp'));
      const result = await router.fetch(filed, false);
      expect(result).toMatchObject({ sourceCarrierId: 'happy-post', result: { direct_local_fallback: true, current_stage: stage,
        routing: { confirmed_carrier: 'happy-post', confirmed_number: '12345678' } } });
      expect(result.result.routing).not.toHaveProperty(['failures', 'happy-post']);
      expect(monitoring.reportRoutingEvent).not.toHaveBeenCalledWith('foreign_history_rejected', expect.anything());
    });
  });
  describe('a confirmed carrier asked while no provider has the parcel', () => {
    // A shape detection is unsure of, which recognition asks other carriers about. An earlier
    // check confirmed Happy-Post for it, and a day has passed since anything answered.
    const confirmed = (carrier: string, routing: JsonObject = {}) => parcel({ carrier, current_stage: 'in_transit',
      carrier_data: { routing: state({ configured_carrier: carrier, confirmed_carrier: 'happy-post', confirmed_number: 'TEST1234',
        confirmed_tracking_url: null, confirmed_postcode: null, last_success_at: '2026-09-09T12:00:00Z', ...routing }) } });
    const deferred = async (snapshot: JsonObject, answer: Error) => {
      fixedPlan('ParcelsApp', '17TRACK');
      const run = setup();
      run.direct.mockRejectedValue(answer);
      run.universal.mockRejectedValue(new NotFoundError('ParcelsApp'));
      const outcome = await run.router.fetch(snapshot, false).catch((error: unknown) => error);
      expect(outcome).toBeInstanceOf(RoutingDeferred);
      // While the carrier is confirmed, recognition asks no other carrier.
      expect(run.recognize).not.toHaveBeenCalled();
      return { ...run, routing: (outcome as RoutingDeferred).routing };
    };
    const kept = (routing: JsonObject) => {
      expect(routing).toMatchObject({ confirmed_carrier: 'happy-post', confirmed_number: 'TEST1234' });
      expect(routing).not.toHaveProperty('cleared_confirmation');
    };

    it.each([
      ['no history', new NoHistoryError('Happy-Post'), 'no_history'],
      ['not found', new NotFoundError('Happy-Post'), 'not_found'],
      ['an item its tracking does not know', new IndeterminateError('Happy-Post', 'No such item', { reason: 'portal_not_found' }), 'no_history'],
    ])('no longer holds an unknown parcel once it answers %s, so the next check routes the parcel afresh', async (_label, answer, kind) => {
      const { direct, routing } = await deferred(confirmed('unknown'), answer);
      expect(direct).toHaveBeenCalledExactlyOnceWith(expect.anything(), 'happy-post');
      expect(routing).toMatchObject({ configured_carrier: 'unknown', cleared_confirmation: { carrier: 'happy-post', number: 'TEST1234' },
        failures: { 'happy-post': { kind } } });
      for (const key of ['confirmed_carrier', 'confirmed_number', 'confirmed_tracking_url', 'confirmed_postcode']) {
        expect(routing).not.toHaveProperty(key);
      }
      // Once the providers' day of rest after their "not found" is over.
      const next = setup(new Date('2026-09-11T12:30:00Z'));
      const result = await next.router.fetch(parcel({ current_stage: 'in_transit', carrier_data: { routing } }), false);
      expect(next.direct).not.toHaveBeenCalledWith(expect.anything(), 'happy-post');
      expect(next.recognize).toHaveBeenCalled();
      expect(result.result).toMatchObject({ tracking_provider: expect.any(String),
        routing: { cleared_confirmation: { carrier: 'happy-post', number: 'TEST1234' } } });
    });

    it.each([
      ['a transport error', new TransportError('Happy-Post')],
      ['a challenge', new ChallengeError('Happy-Post')],
      ['a rate limit', new RateLimitedError('Happy-Post')],
      ['a timeout', new BudgetExceededError('Happy-Post', 45_000)],
      ['an inconclusive answer', new IndeterminateError('Happy-Post')],
    ])('keeps an unknown parcel\'s confirmation after %s, which says nothing about the number', async (_label, failure) => {
      kept((await deferred(confirmed('unknown'), failure)).routing);
    });

    it('keeps an unknown parcel\'s confirmation while the carrier waits out an earlier answer and is not asked', async () => {
      const { direct, routing } = await deferred(confirmed('unknown', { failures: { 'happy-post': {
        count: 1, kind: 'no_history', retry_at: '2026-09-10T12:15:00Z' } } }), new NoHistoryError('Happy-Post'));
      expect(direct).not.toHaveBeenCalled();
      kept(routing);
    });

    it.each([
      ['no history', new NoHistoryError('Happy-Post')],
      ['not found', new NotFoundError('Happy-Post')],
    ])('keeps the confirmation of a parcel filed under the carrier when it answers %s', async (_label, answer) => {
      const { direct, routing } = await deferred(confirmed('happy-post'), answer);
      expect(direct).toHaveBeenCalledExactlyOnceWith(expect.anything(), 'happy-post');
      kept(routing);
    });
  });
  it.each([
    // Older than a month, but not three, or delivered.
    ['a name for the filed carrier', { carrier: 'yamato' }, ['Yamato Transport'], '2026-07-01T10:00:00Z'],
    ['a name the catalog does not know', { carrier: 'yamato' }, ['Example Parcel Co'], '2026-07-01T10:00:00Z'],
    ["only the postal union's feed", { carrier: 'yamato' }, ['UPU'], '2026-07-01T10:00:00Z'],
    ['a name for the filed carrier and a delivery', { carrier: 'yamato' }, ['Yamato Transport'], '2026-01-09T13:19:00Z', 'delivered'],
    ['a recent history', { carrier: 'yamato' }, ['FedEx'], '2026-08-20T10:00:00Z'],
    ['no filed carrier and a delivery', { carrier: 'unknown' }, ['FedEx'], '2026-01-09T13:19:00Z', 'delivered'],
    ['no filed carrier and a label two months old', { carrier: 'unknown' }, ['FedEx'], '2026-07-01T10:00:00Z', 'registered'],
  ])('keeps a universal history with %s', async (_label, filed, reported, stamp, stage = 'in_transit') => {
    const { router, direct, universal } = setup();
    direct.mockResolvedValue(yearlessYamato());
    universal.mockResolvedValueOnce({ ...history(stamp), current_stage: stage, events: [{ time: stamp, description: 'Update', stage }],
      reported_carriers: reported });
    const result = await router.fetch(parcel({ ...filed, tracking_number: '123456789012', created_at: '2026-09-01T00:00:00Z' }), false);
    if (filed.carrier === 'yamato') {
      expect(direct).toHaveBeenCalledExactlyOnceWith(expect.objectContaining({ tracking_number: '123456789012' }), 'yamato');
      expect(result.result.direct_local_history).toMatchObject({ carrier: 'yamato' });
    } else expect(direct).not.toHaveBeenCalled();
    expect(universal).toHaveBeenCalledTimes(1);
    expect(result.result).toMatchObject({ tracking_provider: universal.mock.calls[0][0], last_update: stamp });
  });

  it('asks a carrier whose answer has only local clocks again in 6 h, not on every check', async () => {
    fixedPlan('Ship24', '17TRACK');
    const { router, direct } = setup();
    direct.mockResolvedValue(yearlessYamato());
    // A rate limit's cooldown that ended an hour ago must not keep the carrier due.
    const saved = state({ configured_carrier: 'yamato', preferred_provider: 'Ship24', direct_retry_at: '2026-09-10T11:00:00.000Z' });
    const filed = (routing: JsonObject) => parcel({ carrier: 'yamato', tracking_number: '123456789012', carrier_data: { routing } });
    const first = await router.fetch(filed(saved), true);
    expect(direct).toHaveBeenCalledOnce();
    expect(first.result).toMatchObject({ tracking_provider: 'Ship24', routing: { direct_retry_at: '2026-09-10T18:00:00.000Z' } });
    const later = setup(new Date('2026-09-10T12:30:00Z'));
    await later.router.fetch(filed(first.result.routing as JsonObject), true);
    expect(later.direct).not.toHaveBeenCalled();
    expect(later.universal).toHaveBeenCalledOnce();
  });

  it('asks the filed carrier again on the first check under another scraper release', async () => {
    fixedPlan('Ship24', '17TRACK');
    const filed = (routing: JsonObject) => parcel({ carrier: 'yamato', tracking_number: '123456789012', carrier_data: { routing } });
    // Its answer on local clocks set a wait; a refusal set another.
    const answered = (rules: string) => state({ configured_carrier: 'yamato', preferred_provider: 'Ship24', rules,
      confirmed_carrier: 'yamato', confirmed_number: '123456789012', direct_retry_at: '2026-09-10T18:00:00.000Z' });
    const refused = (rules: string) => state({ configured_carrier: 'yamato', preferred_provider: 'Ship24', rules,
      failures: { yamato: { kind: 'invalid_input', count: 1, retry_at: '2026-09-10T13:00:00.000Z' } } });
    for (const saved of [answered, refused]) {
      const same = setup();
      same.direct.mockResolvedValue(yearlessYamato());
      const first = await same.router.fetch(filed(saved('older')), true);
      expect(same.direct).toHaveBeenCalledOnce();
      expect(first.result.routing).toMatchObject({ direct_retry_at: '2026-09-10T18:00:00.000Z' });
      const next = setup(new Date('2026-09-10T12:30:00Z'));
      await next.router.fetch(filed(first.result.routing as JsonObject), true);
      expect(next.direct).not.toHaveBeenCalled();
    }
    // A passing failure's wait holds.
    const failed = setup();
    await failed.router.fetch(filed(state({ configured_carrier: 'yamato', preferred_provider: 'Ship24', rules: 'older',
      failures: { yamato: { kind: 'transport', count: 1, retry_at: '2026-09-10T13:00:00.000Z' } } })), true);
    expect(failed.direct).not.toHaveBeenCalled();
  });

  it('notes the carriers that recognize the number of a parcel filed under no carrier, for a release that adds one', async () => {
    // A ten-digit DHL Express waybill, which other carriers' recognition is asked about too.
    expect(recognizers('1234567891')).toContain('dhl-express');
    const unfiled = await setup().router.fetch(parcel({ tracking_number: '1234567891' }), true);
    expect(unfiled.result.routing).toMatchObject({ recognizers: recognizers('1234567891') });
    // A parcel filed under a carrier keeps none.
    const filed = await setup().router.fetch(parcel({ carrier: 'yamato', tracking_number: '123456789012',
      carrier_data: { routing: state({ configured_carrier: 'yamato', recognizers: ['dhl-express'] }) } }), true);
    expect(filed.result.routing).not.toHaveProperty('recognizers');
  });

  it('confirms the filed carrier from its local-clock answer, so a provider naming it does not ask it again', async () => {
    fixedPlan('Ship24', '17TRACK');
    const { router, direct, universal } = setup();
    direct.mockResolvedValue(yearlessYamato());
    universal.mockResolvedValue({ ...history(), discovered_carrier: 'yamato' });
    const filed = (routing?: JsonObject) => parcel({ carrier: 'yamato', tracking_number: '123456789012',
      ...(routing ? { carrier_data: { routing } } : {}) });
    const first = await router.fetch(filed(), true);
    expect(direct).toHaveBeenCalledOnce();
    expect(first.result).toMatchObject({ tracking_provider: 'Ship24', carrier_answered: true,
      routing: { confirmed_carrier: 'yamato', confirmed_number: '123456789012', direct_retry_at: '2026-09-10T18:00:00.000Z' } });
    expect(vi.mocked(monitoring.reportRoutingEvent)).not.toHaveBeenCalledWith('direct_carrier_discovered', expect.anything());
    const later = setup(new Date('2026-09-10T12:30:00Z'));
    later.universal.mockResolvedValue({ ...history(), discovered_carrier: 'yamato' });
    const next = await later.router.fetch(filed(first.result.routing as JsonObject), true);
    expect(later.direct).not.toHaveBeenCalled();
    expect(later.universal).toHaveBeenCalledOnce();
    expect(next.result.routing).toMatchObject({ direct_retry_at: '2026-09-10T18:00:00.000Z' });
  });

  describe('a provider that only dates the filed carrier\'s local-clock answer', () => {
    const local = (scans = 1, stage = 'in_transit'): RoutedResult => ({ sourceCarrierId: 'yamato', swissPostReady: null, handoffFallbackErrorType: null,
      result: { status: 'in_transit', current_stage: stage, last_update: null,
        events: Array.from({ length: scans }, (_, index) => ({ local_time: `2026-09-10T0${index + 1}:00:00`, description: `Scan ${index + 1}`, stage })) } });
    const dated = (scans = 1, stage = 'in_transit'): CarrierResult => ({ status: 'in_transit', current_stage: stage, last_update: '2026-09-10T11:00:00Z',
      events: Array.from({ length: scans }, (_, index) => ({ time: `2026-09-10T0${index + 1}:00:00Z`, description: `Scan ${index + 1}`, stage })) });
    // The parcel shows the provider's dated copy of the carrier's scans.
    const filed = (routing: JsonObject) => parcel({ carrier: 'yamato', tracking_number: '123456789012', current_stage: 'in_transit',
      carrier_data: { tracking_provider: 'Ship24', routing } });
    async function copied() {
      fixedPlan('Ship24', '17TRACK');
      const { router, direct, universal } = setup();
      direct.mockResolvedValue(local());
      universal.mockResolvedValue(dated());
      const result = await router.fetch(filed(state({ configured_carrier: 'yamato' })), true);
      return result.result.routing as JsonObject;
    }

    it('keeps the carrier first and remembers what the provider held', async () => {
      const routing = await copied();
      expect(routing).toMatchObject({ confirmed_carrier: 'yamato', preferred_provider: 'Ship24', provider_copy: {
        carrier: 'yamato', number: '123456789012', scans: 1, stage: 'in_transit', provider: 'Ship24', checked_at: time.toISOString() } });
      expect(routing.direct_retry_at).toBeUndefined();
    });

    // The sync may hold a stage of its own, such as a later one the provider no longer shows.
    it.each(['in_transit', 'out_for_delivery'])('is not asked again on a scheduled check while the carrier\'s answer stays the same (%s shown)', async (shown) => {
      const routing = await copied();
      const later = setup(new Date('2026-09-10T12:30:00Z'));
      later.direct.mockResolvedValue(local());
      const result = await later.router.fetch({ ...filed(routing), current_stage: shown }, true);
      expect(later.direct).toHaveBeenCalledOnce();
      expect(later.universal).not.toHaveBeenCalled();
      expect(result).toMatchObject({ sourceCarrierId: 'yamato', result: { direct_local_fallback: true,
        routing: { provider_copy: { checked_at: time.toISOString() }, next_check_at: '2026-09-10T12:45:00.000Z' } } });
    });

    it.each([
      ['a new scan', local(2), true, '2026-09-10T12:30:00Z'],
      ['a new stage', local(1, 'customs'), true, '2026-09-10T12:30:00Z'],
      ['a refresh by hand', local(), false, '2026-09-10T12:30:00Z'],
      ['6 h', local(), true, '2026-09-10T18:00:00Z'],
    ])('is asked again after %s', async (_label, answer, scheduled, at) => {
      const routing = await copied();
      const later = setup(new Date(at));
      later.direct.mockResolvedValue(answer);
      later.universal.mockResolvedValue(dated(answer.result.events!.length, String(answer.result.current_stage)));
      const result = await later.router.fetch(filed(routing), scheduled);
      expect(later.universal).toHaveBeenCalledOnce();
      expect(result.result).toMatchObject({ tracking_provider: 'Ship24', routing: { provider_copy: {
        scans: answer.result.events!.length, stage: answer.result.current_stage, checked_at: new Date(at).toISOString() } } });
    });

    it('stays on the provider when it holds scans the carrier does not list', async () => {
      const routing = await copied();
      const later = setup(new Date('2026-09-10T12:30:00Z'));
      later.direct.mockResolvedValue(local(2));
      later.universal.mockResolvedValue(dated(3));
      const result = await later.router.fetch(filed(routing), true);
      expect(result.result.tracking_provider).toBe('Ship24');
      expect(result.result.routing).not.toHaveProperty('provider_copy');
      expect(result.result.routing).toMatchObject({ direct_retry_at: '2026-09-10T18:30:00.000Z' });
      // Its next check asks the provider alone, as before.
      const next = setup(new Date('2026-09-10T13:00:00Z'));
      await next.router.fetch(filed(result.result.routing as JsonObject), true);
      expect(next.direct).not.toHaveBeenCalled();
      expect(next.universal).toHaveBeenCalledOnce();
    });

    it.each([
      ['number', { number: '123456789013' }],
      ['carrier', { carrier: 'sagawa' }],
    ])('is not matched against another %s', async (_label, other) => {
      const routing = await copied();
      const later = setup(new Date('2026-09-10T12:30:00Z'));
      later.direct.mockResolvedValue(local());
      await later.router.fetch(filed({ ...routing, provider_copy: { ...routing.provider_copy as JsonObject, ...other } }), true);
      expect(later.universal).toHaveBeenCalledOnce();
    });

    it.each([
      ["another provider's history", 'in_transit', '17TRACK'],
      ['a pending parcel', 'pending', 'Ship24'],
    ])('asks the carrier again in 6 h when the parcel shows %s', async (_label, stage, provider) => {
      const routing = await copied();
      const later = setup(new Date('2026-09-10T12:30:00Z'));
      later.direct.mockResolvedValue(local());
      later.universal.mockResolvedValue(dated());
      const result = await later.router.fetch({ ...filed(routing), current_stage: stage,
        carrier_data: { tracking_provider: provider, routing } }, true);
      expect(later.universal).toHaveBeenCalledOnce();
      // The copy keeps its time, so it lapses rather than being renewed on every check.
      expect(result.result.routing).toMatchObject({ provider_copy: { checked_at: time.toISOString() },
        direct_retry_at: '2026-09-10T18:30:00.000Z' });
    });

    it('keeps no copy of a pending answer', async () => {
      fixedPlan('Ship24', '17TRACK');
      const { router, direct, universal } = setup();
      direct.mockResolvedValue(local(1, 'pending'));
      universal.mockResolvedValue(dated(1, 'pending'));
      const result = await router.fetch(filed(state({ configured_carrier: 'yamato' })), true);
      expect(result.result.routing).not.toHaveProperty('provider_copy');
      expect(result.result.routing).toMatchObject({ direct_retry_at: '2026-09-10T18:00:00.000Z' });
    });

    it('forgets the copy when the carrier fails and a provider holds more', async () => {
      const routing = await copied();
      const later = setup(new Date('2026-09-10T12:30:00Z'));
      later.direct.mockRejectedValue(new Error('down'));
      later.universal.mockResolvedValue(dated(2));
      const result = await later.router.fetch(filed(routing), true);
      expect(result.result.routing).not.toHaveProperty('provider_copy');
    });

    it('asks a carrier chosen by hand first, after the one it replaced answered with local clocks only', async () => {
      const routing = await copied();
      const later = setup(new Date('2026-09-10T12:30:00Z'));
      later.direct.mockImplementation(async (_parcel, carrier) => {
        if (carrier === 'yamato') return local();
        throw new Error('down');
      });
      later.universal.mockResolvedValue(dated());
      const moved = (saved: JsonObject) => ({ ...filed(saved), carrier: 'sagawa' });
      const result = await later.router.fetch(moved(routing), false);
      expect(later.direct.mock.calls.map(([, carrier]) => carrier)).toEqual(['sagawa', 'yamato']);
      expect(result.result.routing).not.toHaveProperty('confirmed_carrier');
      const next = setup(new Date('2026-09-10T13:00:00Z'));
      next.universal.mockResolvedValue(dated());
      await next.router.fetch(moved(result.result.routing as JsonObject), true);
      expect(next.direct.mock.calls[0][1]).toBe('sagawa');
    });
  });

  describe('an unknown parcel whose detected or named carrier answers with local clocks only', () => {
    // A synthetic number in 17FEIA's own shape: detection names the carrier with high confidence.
    const number = 'FEIXY0000000001YQ';
    const local = (carrier = '17feia'): RoutedResult => ({ sourceCarrierId: carrier, swissPostReady: null, handoffFallbackErrorType: null,
      result: { status: 'in_transit', current_stage: 'in_transit', last_update: null, last_update_local: '2026-09-10T09:00:00',
        events: [{ local_time: '2026-09-10T09:00:00', description: 'Departed the sorting centre', stage: 'in_transit' }] } });
    // Filed before detection named the carrier, with a provider's timeline saved since.
    const filed = (routing: JsonObject = {}, overrides: JsonObject = {}) => parcel({ tracking_number: number, current_stage: 'in_transit',
      carrier_data: { routing: state({ last_event_at: '2026-09-10T10:00:00.000Z', ...routing }) }, ...overrides });
    // First and last mile: no single carrier for the provider to name.
    const legs = (result: CarrierResult) => ({ ...result, reported_carriers: ['17FEIA', 'DPD'] });

    it('takes the carrier once a provider dates the progress, as a parcel added with it would', async () => {
      fixedPlan('Ship24', '17TRACK');
      const { router, direct, universal } = setup();
      direct.mockResolvedValue(local());
      universal.mockResolvedValue(legs(history()));
      const result = await router.fetch(filed(), false);
      expect(direct).toHaveBeenCalledExactlyOnceWith(expect.objectContaining({ carrier: '17feia' }), '17feia');
      expect(result).toMatchObject({ sourceCarrierId: 'unknown', correction: { carrier: '17feia', trackingUrl: null, postcode: null },
        result: { tracking_provider: 'Ship24', carrier_answered: true, discovered_carrier: '17feia',
          routing: { configured_carrier: '17feia', discovered_carrier: '17feia' },
          direct_local_history: { carrier: '17feia', number, events: [expect.objectContaining({ local_time: '2026-09-10T09:00:00' })] } } });
      expect(result.result.routing).not.toHaveProperty('confirmed_carrier');
      // Taken, its answer is not left set aside.
      expect(result.result.routing).not.toHaveProperty('set_aside');
      expect(result.result).not.toHaveProperty('auto_changed_from');
      // Filed under the carrier, its next answer is the filed carrier's local fallback.
      const next = setup(new Date('2026-09-10T19:00:00Z'));
      next.direct.mockResolvedValue(local());
      next.universal.mockResolvedValue(legs(history('2026-09-10T18:00:00Z')));
      const again = await next.router.fetch(filed({}, { carrier: '17feia', carrier_data: { routing: result.result.routing } }), false);
      expect(next.direct).toHaveBeenCalledExactlyOnceWith(expect.objectContaining({ carrier: '17feia', carrier_data: expect.objectContaining({ routing: expect.anything() }) }), '17feia');
      expect(again.correction).toBeUndefined();
      expect(again.result).toMatchObject({ tracking_provider: 'Ship24', carrier_answered: true, direct_local_history: { carrier: '17feia' } });
    });

    it('files the parcel under it and serves its answer while no provider has progress', async () => {
      fixedPlan('Ship24', '17TRACK');
      const taken = (result: RoutedResult) => {
        expect(result).toMatchObject({ sourceCarrierId: '17feia', correction: { carrier: '17feia', trackingUrl: null, postcode: null },
          result: { direct_local_fallback: true, auto_changed_from: 'unknown', auto_changed_to: '17feia',
            routing: { configured_carrier: '17feia', confirmed_carrier: '17feia', confirmed_number: number },
            direct_local_history: { carrier: '17feia', number } } });
        expect(result.result).not.toHaveProperty('tracking_provider');
        expect(result.result.routing).not.toHaveProperty('set_aside');
      };
      const failing = setup();
      failing.direct.mockResolvedValue(local());
      failing.universal.mockRejectedValue(new NotFoundError('Ship24'));
      taken(await failing.router.fetch(filed(), false));
      // A registration alone, for a parcel still waiting for movement and for one that has moved.
      for (const stage of ['pending', 'in_transit']) {
        const thin = setup();
        thin.direct.mockResolvedValue(local());
        thin.universal.mockResolvedValue(legs(thinHistory()));
        const result = await thin.router.fetch(filed({}, { current_stage: stage }), false);
        taken(result);
        // Filed under it, the carrier is asked first, and its answer still serves.
        const next = setup(new Date('2026-09-10T13:00:00Z'));
        next.direct.mockResolvedValue(local());
        next.universal.mockResolvedValue(legs(thinHistory()));
        const again = await next.router.fetch(filed({}, { carrier: '17feia', current_stage: 'in_transit',
          carrier_data: { routing: result.result.routing } }), false);
        expect(next.direct.mock.calls[0][1]).toBe('17feia');
        expect(again.correction).toBeUndefined();
        expect(again).toMatchObject({ sourceCarrierId: '17feia', result: { direct_local_fallback: true } });
      }
    });

    it('files the parcel under the carrier a provider named, while that provider has no progress', async () => {
      fixedPlan('Ship24', '17TRACK');
      const { router, direct, universal } = setup();
      direct.mockImplementation(async (_parcel, asked: string) => local(asked));
      universal.mockResolvedValue({ ...thinHistory(), discovered_carrier: '17feia', reported_carriers: ['17FEIA'] });
      const result = await router.fetch(filed({ discovered_carrier: '17feia' }, { tracking_number: 'TEST1234', current_stage: 'pending' }), false);
      expect(result).toMatchObject({ sourceCarrierId: '17feia', correction: { carrier: '17feia' },
        result: { direct_local_fallback: true, routing: { configured_carrier: '17feia', confirmed_carrier: '17feia' } } });
    });

    it('does not file the parcel from an answer showing only a registration', async () => {
      fixedPlan('Ship24', '17TRACK');
      const registered = (): RoutedResult => ({ ...local(), result: { status: 'pending', current_stage: 'registered', last_update: null,
        events: [{ local_time: '2026-09-10T09:00:00', description: 'Shipment information received', stage: 'registered' }] } });
      // No provider answers, or one shows a registration too.
      for (const answer of [async () => { throw new NotFoundError('Ship24'); }, async () => thinHistory()]) {
        const { router, direct, universal } = setup();
        direct.mockResolvedValue(registered());
        universal.mockImplementation(answer);
        const outcome = await router.fetch(filed({}, { current_stage: 'registered' }), false)
          .catch((error: unknown) => error as RoutingDeferred);
        const routing = outcome instanceof RoutingDeferred ? outcome.routing : outcome.result.routing;
        expect(outcome).not.toHaveProperty('correction');
        expect(routing).toMatchObject({ configured_carrier: 'unknown', set_aside: { '17feia': { reason: 'local_clock' } } });
        expect(routing).not.toHaveProperty('confirmed_carrier');
      }
    });

    it('leaves the parcel unknown when only recognition found the carrier and no provider has progress', async () => {
      fixedPlan('Ship24', '17TRACK');
      const { router, direct, universal, recognize } = setup();
      recognize.mockImplementation(async (asked: string) => ({ known: asked === 'sto' }));
      direct.mockImplementation(async (_parcel, asked: string) => local(asked));
      universal.mockResolvedValue(thinHistory());
      const result = await router.fetch(filed({}, { tracking_number: '123456789012', current_stage: 'pending' }), false);
      expect(direct).toHaveBeenCalledExactlyOnceWith(expect.objectContaining({ carrier: 'sto' }), 'sto');
      expect(result.correction).toBeUndefined();
      expect(result.result).toMatchObject({ tracking_provider: 'Ship24', routing: { configured_carrier: 'unknown' } });
    });

    it.each([
      ['a provider named', 'TEST1234', '17feia', { discovered_carrier: '17feia' }],
      // STO's 09:00 in Shanghai is 01:00 UTC, 10 hours before the provider's scan.
      ['recognition found for a shape detection is unsure of, whose scans the provider does not date', '123456789012', 'sto', {}],
    ])('does not take a carrier %s', async (_label, trackingNumber, carrier, routing) => {
      fixedPlan('Ship24', '17TRACK');
      const { router, direct, universal, recognize } = setup();
      recognize.mockImplementation(async (asked: string) => ({ known: asked === carrier }));
      direct.mockImplementation(async (_parcel, asked: string) => local(asked));
      universal.mockResolvedValue(legs(history()));
      const result = await router.fetch(filed(routing, { tracking_number: trackingNumber }), false);
      expect(direct).toHaveBeenCalledExactlyOnceWith(expect.objectContaining({ carrier }), carrier);
      expect(result.correction).toBeUndefined();
      expect(result.result).toMatchObject({ tracking_provider: 'Ship24', routing: { configured_carrier: 'unknown',
        set_aside: { [carrier]: { reason: 'local_clock', checked_at: time.toISOString(), count: 1 } } } });
      expect((result.result.routing as JsonObject).set_aside).not.toHaveProperty([carrier, 'newest']);
    });

    it.each([
      ['detection names', number, '17feia'],
      ['recognition found', '123456789012', 'sto'],
    ])('does not take a reused number\'s old label from a carrier %s', async (_label, trackingNumber, carrier) => {
      fixedPlan('Ship24', '17TRACK');
      const { router, direct, universal, recognize } = setup();
      recognize.mockImplementation(async (asked: string) => ({ known: asked === carrier }));
      // The number's earlier parcel, last winter, months before this one was added.
      direct.mockImplementation(async (_parcel, asked: string) => ({ ...local(asked), result: { ...local(asked).result,
        last_update_local: '2026-01-08T09:00:00', events: [{ local_time: '2026-01-08T09:00:00', description: 'Departed the sorting centre', stage: 'in_transit' }] } }));
      universal.mockResolvedValue(legs(history()));
      const result = await router.fetch(filed({}, { tracking_number: trackingNumber, created_at: '2026-09-01T00:00:00Z' }), false);
      expect(direct).toHaveBeenCalledExactlyOnceWith(expect.objectContaining({ carrier }), carrier);
      expect(result.correction).toBeUndefined();
      expect(result.result).toMatchObject({ tracking_provider: 'Ship24', routing: { configured_carrier: 'unknown' } });
      for (const key of ['carrier_answered', 'direct_local_history']) expect(result.result).not.toHaveProperty(key);
      expect(result.result.routing).not.toHaveProperty('confirmed_carrier');
      expect(result.result.routing).not.toHaveProperty('set_aside');
      expect(monitoring.reportRoutingEvent).toHaveBeenCalledWith('foreign_history_rejected', expect.objectContaining({ provider: carrier }));
    });

    it.each([
      ['a provider named before', 'TEST1234', '17feia', '17FEIA', { discovered_carrier: '17feia' }],
      // An older release's adapter refused the number; today's answers it.
      ['a provider named before, which refused the number then', 'TEST1234', '17feia', '17FEIA', { discovered_carrier: '17feia',
        failures: { '17feia': { kind: 'schema', count: 5, retry_at: '2026-09-09T06:00:00Z', user_error: 'carrier:invalid_input' } } }],
      ['recognition found', '123456789012', 'sto', 'STO Express', {}],
    ])('takes a carrier %s once the provider dating the progress names it alone', async (_label, trackingNumber, carrier, name, routing) => {
      fixedPlan('Ship24', '17TRACK');
      const { router, direct, universal, recognize } = setup();
      recognize.mockImplementation(async (asked: string) => ({ known: asked === carrier }));
      direct.mockImplementation(async (_parcel, asked: string) => local(asked));
      universal.mockResolvedValue({ ...history(), discovered_carrier: carrier, reported_carriers: [name] });
      const result = await router.fetch(filed(routing, { tracking_number: trackingNumber }), false);
      expect(direct).toHaveBeenCalledExactlyOnceWith(expect.objectContaining({ carrier }), carrier);
      expect(result).toMatchObject({ correction: { carrier, trackingUrl: null, postcode: null },
        result: { tracking_provider: 'Ship24', carrier_answered: true, discovered_carrier: carrier,
          routing: { configured_carrier: carrier, discovered_carrier: carrier }, direct_local_history: { carrier } } });
      expect(result.result.routing).not.toHaveProperty('confirmed_carrier');
      expect(result.result.routing).not.toHaveProperty(['failures', carrier]);
      expect(result.result.routing).not.toHaveProperty('set_aside');
    });

    describe('a carrier only recognition found, whose scans a provider naming no carrier dates', () => {
      // A shape detection is unsure of: recognition asks STO, whose catalog zone is Asia/Shanghai,
      // among others. Its answer's 09:00 there is 01:00 UTC.
      const trackingNumber = '123456789012';
      const dating = (time = '2026-09-10T01:00:00Z', stage = 'in_transit', description = 'In transit'): CarrierResult => ({
        status: 'in_transit', current_stage: stage, last_update: time, events: [{ time, description, stage }] });
      const check = async (known: string[], provider: CarrierResult, routing: JsonObject = {}) => {
        fixedPlan('Postal Ninja');
        const { router, direct, universal, recognize } = setup();
        recognize.mockImplementation(async (asked: string) => ({ known: known.includes(asked) }));
        direct.mockImplementation(async (_parcel, asked: string) => local(asked));
        universal.mockResolvedValue(provider);
        const result = await router.fetch(filed({ last_event_at: '2026-09-10T01:00:00.000Z', ...routing },
          { tracking_number: trackingNumber }), false);
        expect(direct.mock.calls.map(([, carrier]) => carrier).sort()).toEqual([...known].sort());
        return result;
      };

      it.each([
        ['to the minute', '2026-09-10T01:00:42Z'],
        ['an hour off, as a scan on the Canary Islands is on Madrid\'s clock', '2026-09-10T02:00:00Z'],
      ])('files the parcel under it, confirmed as a probe\'s dated answer would be, when the scans match %s', async (_label, time) => {
        const result = await check(['sto'], dating(time));
        expect(result).toMatchObject({ sourceCarrierId: 'unknown', correction: { carrier: 'sto', trackingUrl: null, postcode: null },
          result: { tracking_provider: 'Postal Ninja', carrier_answered: true, auto_changed_from: 'unknown', auto_changed_to: 'sto',
            routing: { configured_carrier: 'sto', confirmed_carrier: 'sto', confirmed_number: trackingNumber,
              confirmed_tracking_url: null, confirmed_postcode: null },
            direct_local_history: { carrier: 'sto', number: trackingNumber, events: [expect.objectContaining({ local_time: '2026-09-10T09:00:00' })] } } });
        expect(result.result.routing).not.toHaveProperty('set_aside');
        expect(result.result.routing).not.toHaveProperty('discovered_carrier');
        expect(monitoring.reportRoutingEvent).toHaveBeenCalledWith('candidate_probe_confirmed', expect.objectContaining({ carrier: 'unknown', provider: 'sto' }));
      });

      it('files the parcel under it again, without reporting it again, once the sync cleared that confirmation', async () => {
        const result = await check(['sto'], dating(), { cleared_confirmation: { carrier: 'sto', number: trackingNumber } });
        expect(result).toMatchObject({ correction: { carrier: 'sto' },
          result: { routing: { configured_carrier: 'sto', confirmed_carrier: 'sto', confirmed_number: trackingNumber } } });
        expect(result.result.routing).not.toHaveProperty('cleared_confirmation');
        expect(monitoring.reportRoutingEvent).not.toHaveBeenCalledWith('candidate_probe_confirmed', expect.anything());
      });

      it.each([
        ['scans that match by an offset that is no whole hour', ['sto'], dating('2026-09-10T01:30:00Z')],
        ['a scan of another stage', ['sto'], dating('2026-09-10T01:00:00Z', 'out_for_delivery', 'Out for delivery')],
        ['recognition that knows two carriers', ['sto', 'j-and-t'], dating()],
        // BRT's catalog lists it in UTC: read there, its 09:00 would be the provider's.
        ['a carrier the catalog lists in UTC', ['brt'], dating('2026-09-10T09:00:00Z')],
        // Read as UTC, it would be STO's scan.
        ['provider times without an offset', ['sto'], dating('2026-09-10T01:00:00')],
      ] as const)('leaves the parcel unknown on %s', async (_label, known, provider) => {
        const result = await check([...known], provider);
        expect(result.correction).toBeUndefined();
        expect(result.result).toMatchObject({ tracking_provider: 'Postal Ninja', routing: { configured_carrier: 'unknown',
          set_aside: Object.fromEntries(known.map((carrier) => [carrier, expect.objectContaining({ reason: 'local_clock' })])) } });
        expect(result.result.routing).not.toHaveProperty('confirmed_carrier');
        expect(result.result).not.toHaveProperty('auto_changed_from');
        expect(monitoring.reportRoutingEvent).not.toHaveBeenCalledWith('candidate_probe_confirmed', expect.anything());
      });
    });

    it('keeps the local history of the carrier it takes, not of one asked before it', async () => {
      fixedPlan('Ship24', '17TRACK');
      const { router, direct, universal, recognize } = setup();
      // The hint saved by an earlier check is asked first; recognition then finds STO, the provider's carrier.
      recognize.mockImplementation(async (asked: string) => ({ known: asked === 'sto' }));
      direct.mockImplementation(async (_parcel, asked: string) => local(asked));
      universal.mockResolvedValue({ ...history(), discovered_carrier: 'sto', reported_carriers: ['STO Express'] });
      const result = await router.fetch(filed({ discovered_carrier: '17feia' }, { tracking_number: '123456789012' }), false);
      expect(direct.mock.calls.map(([, carrier]) => carrier)).toEqual(['17feia', 'sto']);
      expect(result).toMatchObject({ correction: { carrier: 'sto' },
        result: { carrier_answered: true, direct_local_history: { carrier: 'sto' } } });
    });

    it('does not replace a carrier already confirmed for the number', async () => {
      fixedPlan('Ship24', '17TRACK');
      const { router, direct, universal } = setup();
      direct.mockImplementation(async (_parcel, carrier: string) => {
        if (carrier !== '17feia') throw new NotFoundError(carrier);
        return local();
      });
      universal.mockResolvedValue(legs(history()));
      const result = await router.fetch(filed({ confirmed_carrier: 'ups', confirmed_number: number }), false);
      expect(direct.mock.calls.map(([, carrier]) => carrier)).toEqual(['ups', '17feia']);
      expect(result.correction).toBeUndefined();
      expect(result.result.routing).toMatchObject({ configured_carrier: 'unknown', confirmed_carrier: 'ups' });
    });
  });

  it('keeps the origin watermark when a verified delivery partner confirms the same completion', async () => {
    const { router, direct, universal } = setup();
    direct.mockResolvedValue({ sourceCarrierId: 'posti', swissPostReady: null, handoffFallbackErrorType: null,
      earlierCarrierId: 'la-poste', earlierResult: { status: 'delivered', last_update: '2026-09-10T11:00:00Z' },
      result: { status: 'delivered', last_update: '2026-09-10T10:00:00Z',
        original_carrier: 'la-poste', active_tracking_carrier: 'posti', active_tracking_number: 'CW123456785FR' } });
    const result = await router.fetch(parcel({ carrier: 'la-poste', tracking_number: 'CW123456785FR' }), false);
    expect(result.result.routing).toMatchObject({ last_event_at: '2026-09-10T11:00:00.000Z' });
    expect(result.result.active_tracking_carrier).toBe('posti');
    expect(result.correction).toBeUndefined();
    expect(universal).not.toHaveBeenCalled();
  });

  it('keeps an earlier carrier asked again after the handoff out of the watermark', async () => {
    const { router, direct } = setup();
    direct.mockResolvedValue({ sourceCarrierId: 'posti', swissPostReady: null, handoffFallbackErrorType: null,
      earlierCarrierId: 'la-poste', earlierFollowUp: true, earlierResult: { status: 'in_transit', last_update: '2026-09-10T11:00:00Z' },
      result: { status: 'in_transit', last_update: '2026-09-10T10:00:00Z' } });
    const result = await router.fetch(parcel({ carrier: 'la-poste', tracking_number: 'CW123456785FR', carrier_data: {
      original_carrier: 'la-poste', active_tracking_carrier: 'posti', active_tracking_number: 'CW123456785FR' } }), true);
    expect(result.result.routing).toMatchObject({ last_event_at: '2026-09-10T10:00:00.000Z' });
    expect(result.earlierResult).toMatchObject({ last_update: '2026-09-10T11:00:00Z' });
  });
});

describe('coverage-based provider order', () => {
  const sources = (universal: ReturnType<typeof vi.fn>) => universal.mock.calls.map(([source]) => source);
  it('asks the providers with a carrier\'s fullest history first', async () => {
    const { router, direct, universal } = setup();
    direct.mockRejectedValue(new Error('carrier down'));
    universal.mockRejectedValueOnce(new IndeterminateError('17TRACK', 'no history'));
    await router.fetch(parcel({ carrier: 'usps' }), false);
    // 17TRACK had USPS's full history and ParcelsApp part of it; Ship24, which had none, comes after them.
    expect(sources(universal)).toEqual(['17TRACK', 'ParcelsApp']);
  });
  it('orders by the carrier a provider discovered when the parcel has none', async () => {
    const { router, direct, universal } = setup();
    direct.mockRejectedValue(new Error('carrier down'));
    await router.fetch(parcel({ carrier_data: { routing: state({ discovered_carrier: 'usps' }) } }), false);
    expect(sources(universal)).toEqual(['17TRACK']);
  });
  it('asks a fuller provider before the one a parcel stays with, within its own backoff', async () => {
    const { router, direct, universal } = setup();
    direct.mockRejectedValue(new Error('carrier down'));
    // Ship24 was label-only for a La Poste reference.
    const pinned = (extra: JsonObject = {}) => parcel({ carrier: 'la-poste', carrier_data: { routing: state({
      configured_carrier: 'la-poste', preferred_provider: 'Ship24', preferred_number: 'TEST1234', ...extra }) } });
    const moved = await router.fetch(pinned(), false);
    expect(sources(universal)).toEqual(['ParcelsApp']);
    expect(moved.result.routing).toMatchObject({ preferred_provider: 'ParcelsApp' });
    // Fuller providers without this parcel wait out their backoff while the parcel stays where it was.
    universal.mockClear().mockRejectedValueOnce(new IndeterminateError('ParcelsApp', 'no history'))
      .mockRejectedValueOnce(new IndeterminateError('17TRACK', 'no history'));
    const kept = await router.fetch(pinned(), false);
    expect(sources(universal)).toEqual(['ParcelsApp', '17TRACK', 'Ship24']);
    expect(kept.result.routing).toMatchObject({ preferred_provider: 'Ship24', failures: {
      ParcelsApp: { kind: 'no_history', retry_at: '2026-09-10T12:15:00.000Z' }, '17TRACK': { kind: 'no_history' } } });
    universal.mockClear();
    await router.fetch(parcel({ carrier: 'la-poste', carrier_data: kept.result }), false);
    expect(sources(universal)).toEqual(['Ship24']);
  });
  it('compares only with providers whose history for the carrier is as full', async () => {
    const { router, direct, universal } = setup();
    direct.mockRejectedValue(new Error('carrier down'));
    const routing = state({ configured_carrier: 'la-poste', preferred_provider: 'ParcelsApp', preferred_number: 'TEST1234',
      last_probe_at: '2026-09-08T12:00:00Z' });
    for (const cursor of [0, 1]) {
      universal.mockClear();
      await router.fetch(parcel({ carrier: 'la-poste', carrier_data: { routing: { ...routing, probe_cursor: cursor } } }), true);
      // Never the label-only Ship24.
      expect(sources(universal)).toEqual(['ParcelsApp', '17TRACK']);
    }
  });
  it('logs results that contradict the coverage evidence', async () => {
    // Evidence that only 17TRACK has the carrier's history, whatever the scraper's says for USPS.
    const plan = vi.spyOn(scraper, 'universalPlan').mockReturnValue({ sources: ['17TRACK', 'ParcelsApp', 'Ship24'], carrier: 'usps',
      tier: (source) => source === '17TRACK' ? 'full' : 'empty', rank: (source) => source === '17TRACK' ? 0 : 1 });
    const { router, direct, universal } = setup();
    direct.mockRejectedValue(new Error('carrier down'));
    universal.mockRejectedValueOnce(new IndeterminateError('17TRACK', 'no history'));
    await router.fetch(parcel({ carrier: 'usps' }), false);
    expect(monitoring.reportRoutingEvent).toHaveBeenCalledWith('coverage_contradicted', expect.objectContaining({ provider: 'ParcelsApp', category: 'history' }));
    expect(monitoring.reportRoutingEvent).toHaveBeenCalledWith('coverage_contradicted', expect.objectContaining({ provider: '17TRACK', category: 'no_history' }));
    vi.mocked(monitoring.reportRoutingEvent).mockClear();
    plan.mockRestore();
    // Without evidence for the carrier, nothing contradicts it.
    const other = setup();
    other.universal.mockRejectedValueOnce(new IndeterminateError('ParcelsApp', 'no history'));
    await other.router.fetch(parcel(), false);
    expect(monitoring.reportRoutingEvent).not.toHaveBeenCalledWith('coverage_contradicted', expect.anything());
  });
});

describe('rechecks of a number nobody has answered', () => {
  const sources = (universal: ReturnType<typeof vi.fn>) => universal.mock.calls.map(([source]) => source);
  const recheck = (check: ReturnType<typeof setup>, input: JsonObject) => check.router.fetch(input, true, undefined, false, true);
  it('contacts one universal provider at most, where a full check asks each in turn', async () => {
    const full = setup();
    full.universal.mockRejectedValue(new NotFoundError('Provider'));
    await full.router.fetch(parcel(), true).catch(() => null);
    expect(sources(full.universal)).toEqual(['ParcelsApp', 'Ship24', '17TRACK']);
    const first = setup();
    first.universal.mockRejectedValue(new NotFoundError('Provider'));
    const deferred = await recheck(first, parcel()).catch((error: unknown) => error);
    expect(deferred).toBeInstanceOf(RoutingDeferred);
    expect(sources(first.universal)).toEqual(['ParcelsApp']);
    // The discovery cursor moves on: the next recheck of a number pointing to no provider starts with Ship24.
    const { routing } = deferred as RoutingDeferred;
    const next = setup(new Date('2026-09-12T12:00:00Z'));
    next.universal.mockRejectedValue(new NotFoundError('Provider'));
    await recheck(next, parcel({ carrier_data: { routing } })).catch(() => null);
    expect(sources(next.universal)).toEqual(['Ship24']);
  });
  it('skips a provider in backoff or cooling down for the next one it may ask', async () => {
    const cooling = setup();
    cooling.health.acquireTrackingProvider.mockResolvedValueOnce({ token: null, retry_at: '2026-09-10T13:00:00Z' });
    const answered = await recheck(cooling, parcel());
    expect(sources(cooling.universal)).toEqual(['Ship24']);
    // A first answer joins the regular flow.
    expect(answered.result).toMatchObject({ tracking_provider: 'Ship24', routing: { last_success_at: time.toISOString() } });
    const backoff = setup();
    backoff.universal.mockRejectedValue(new NotFoundError('Provider'));
    await recheck(backoff, parcel({ carrier_data: { routing: state({
      failures: { ParcelsApp: { count: 3, kind: 'not_found', retry_at: '2026-09-11T00:00:00Z' } } }) } })).catch(() => null);
    expect(sources(backoff.universal)).toEqual(['Ship24']);
    // 17TRACK, which nothing points to, is not asked even when both are out.
    const out = setup();
    await recheck(out, parcel({ carrier_data: { routing: state({ failures: {
      ParcelsApp: { count: 3, kind: 'not_found', retry_at: '2026-09-11T00:00:00Z' },
      Ship24: { count: 3, kind: 'not_found', retry_at: '2026-09-11T00:00:00Z' } } }) } })).catch(() => null);
    expect(out.universal).not.toHaveBeenCalled();
  });
  it('still asks the carriers that recognize the number', async () => {
    const { router, direct, universal, recognizeBrowser } = setup();
    recognizeBrowser.mockResolvedValue({ known: true, lastActivityAt: time.toISOString() });
    direct.mockImplementation(async (_parcel, carrier) => directValue(carrier));
    const result = await router.fetch(parcel({ tracking_number: '000000000011' }), true, undefined, false, true);
    expect(result.sourceCarrierId).toBe('fedex');
    expect(universal).not.toHaveBeenCalled();
  });
  it('asks the providers with history for the carrier the number points to', async () => {
    // A USPS number: 17TRACK had USPS's full history and ParcelsApp part of it, Ship24 none.
    const usps = setup();
    usps.direct.mockRejectedValue(new NotFoundError('USPS'));
    usps.universal.mockRejectedValue(new NotFoundError('Provider'));
    await recheck(usps, parcel({ tracking_number: '9400111206206406260787' })).catch(() => null);
    expect(sources(usps.universal)).toEqual(['17TRACK']);
    expect(recheckSources('9400111206206406260787', ['ParcelsApp', 'Ship24', '17TRACK'])).toEqual(['17TRACK', 'ParcelsApp']);
    // Ten digits, likeliest DHL Express: Ship24 and 17TRACK had its full history.
    expect(recheckSources('1234567891', ['ParcelsApp', 'Ship24', '17TRACK'])).toEqual(['Ship24', '17TRACK', 'ParcelsApp']);
    // A number pointing to no carrier with evidence gets the HTTP providers, in the check's order.
    expect(recheckSources('NOSHAPE42', ['17TRACK', 'Ship24', 'ParcelsApp'])).toEqual(['Ship24', 'ParcelsApp']);
    // Evidence that no provider had the carrier's history leaves them too, and only providers the check may ask count.
    vi.spyOn(scraper, 'universalPlan').mockReturnValue({ sources: ['17TRACK', 'ParcelsApp', 'Ship24', 'Postal Ninja'], carrier: 'usps',
      tier: (source) => source === 'Postal Ninja' ? 'full' : 'empty', rank: () => 0 });
    expect(recheckSources('NOSHAPE42', ['17TRACK', 'Ship24', 'ParcelsApp'])).toEqual(['Ship24', 'ParcelsApp']);
    expect(recheckSources('NOSHAPE42', ['17TRACK', 'Ship24', 'ParcelsApp', 'Postal Ninja'])).toEqual(['Postal Ninja']);
  });
});

describe('routingFailure with carrier package errors', () => {
  it('classifies by error kind before falling back to status sniffing', async () => {
    const { routingFailure } = await import('./trackingRouting');
    const errors = await import('universal-parcel-scraper');
    expect(routingFailure(new errors.NotFoundError('CTT'))).toEqual({ kind: 'not_found', retryAfterMs: 0 });
    expect(routingFailure(new errors.RateLimitedError('Ship24', 30_000))).toEqual({ kind: 'rate_limited', retryAfterMs: 30_000 });
    expect(routingFailure(new errors.ChallengeError('UPS'))).toEqual({ kind: 'verification', retryAfterMs: 0 });
    expect(routingFailure(new errors.SchemaError('DHL'))).toEqual({ kind: 'schema', retryAfterMs: 0 });
    expect(routingFailure(new errors.InputRequiredError('Heppner', 'the delivery postcode'))).toEqual({ kind: 'input_required', retryAfterMs: 0 });
    expect(routingFailure(new errors.InvalidInputError('Evri', 'Evri requires a 16-character tracking number'))).toEqual({ kind: 'invalid_input', retryAfterMs: 0 });
    expect(routingFailure(new errors.IndeterminateError('Colisweb'))).toEqual({ kind: 'transport', retryAfterMs: 0 });
    expect(routingFailure(new errors.IndeterminateError('Australia Post', undefined, { reason: 'several_parcels' }))).toEqual({ kind: 'invalid_input', retryAfterMs: 0 });
    expect(routingFailure(new Error('wrapped', { cause: new errors.NotFoundError('CTT') }))).toEqual({ kind: 'not_found', retryAfterMs: 0 });
    // Errors outside the taxonomy keep the historical status-based classification.
    expect(routingFailure(Object.assign(new Error('legacy'), { status: 429, retryAfterMs: 1_000 }))).toEqual({ kind: 'rate_limited', retryAfterMs: 1_000 });
    expect(routingFailure(new TypeError('bad payload'))).toEqual({ kind: 'schema', retryAfterMs: 0 });
  });
});

describe('universal preflight and recipient input', () => {
  it('reuses a selected carrier preflight whatever the country hint', async () => {
    const { router, direct, universal, health } = setup();
    direct.mockRejectedValue(new Error('carrier unavailable'));
    const cached = vi.fn((source) => source === 'ParcelsApp' ? history() : undefined);
    router.options.takePrefetchedUniversal = cached;
    const result = await router.fetch(parcel({ carrier: 'dhl', carrier_data: { lookup_country_hint: 'FR' } }), false);
    expect(cached).toHaveBeenCalledWith('ParcelsApp', 'TEST1234', null);
    expect(result.result.tracking_provider).toBe('ParcelsApp');
    expect(universal).not.toHaveBeenCalled();
    expect(health.acquireTrackingProvider).not.toHaveBeenCalled();
  });
  it('labels prefetched provider history without waiting for blocked direct confirmation', async () => {
    const value = setup();
    value.router.options.takePrefetchedUniversal = vi.fn((source) => source === 'Ship24' ? { ...history(),
      discovered_carrier: 'dhl-express', reported_carriers: ['DHL Express'] } : undefined);
    const result = await value.router.fetch(parcel({ tracking_number: '1234567891' }), false);
    expect(result.correction).toEqual({ carrier: 'dhl-express', trackingUrl: null, postcode: null });
    expect(result.sourceCarrierId).toBe('unknown');
    expect(result.result.tracking_provider).toBe('Ship24');
    expect(result.result.routing).toMatchObject({ configured_carrier: 'dhl-express' });
    expect(result.result.routing).not.toHaveProperty('confirmed_carrier');
    expect(result.result).not.toHaveProperty('auto_changed_from');
    expect(value.direct).not.toHaveBeenCalled();
    // The carrier is asked at the next run, not after the provider's spacing.
    expect(result.result.routing).not.toHaveProperty('next_check_at');
    expect(result.result.routing).toMatchObject({ carrier_named_at: expect.any(String) });
    value.direct.mockImplementation(async (_parcel, carrier) => directValue(carrier));
    const next = await value.router.fetch(parcel({ carrier: 'dhl-express', tracking_number: '1234567891',
      carrier_data: { routing: result.result.routing as JsonObject } }), true);
    expect(value.direct).toHaveBeenCalledExactlyOnceWith(expect.objectContaining({ carrier: 'dhl-express' }), 'dhl-express');
    expect(next.sourceCarrierId).toBe('dhl-express');
    expect(next.result.routing).not.toHaveProperty('carrier_named_at');
  });
  it('keeps the provider spacing when the check asked the carrier the provider named', async () => {
    const value = setup();
    value.direct.mockRejectedValue(new Error('carrier blocked'));
    value.universal.mockResolvedValue({ ...history(), discovered_carrier: 'dhl-express', reported_carriers: ['DHL Express'] });
    const result = await value.router.fetch(parcel({ tracking_number: '1234567891' }), false);
    expect(result.correction?.carrier).toBe('dhl-express');
    expect(value.direct).toHaveBeenCalledWith(expect.objectContaining({ carrier: 'dhl-express' }), 'dhl-express');
    expect(result.result.routing).toMatchObject({ next_check_at: expect.any(String) });
    expect(result.result.routing).not.toHaveProperty('carrier_named_at');
  });
  it('does not file prefetched history under a named carrier whose adapter refuses the number', async () => {
    const value = setup();
    value.router.options.takePrefetchedUniversal = vi.fn(() => ({ ...history(),
      discovered_carrier: 'china-post', reported_carriers: ['China Post'] }));
    value.router.options.refuses = vi.fn(refusesNumber);
    const result = await value.router.fetch(parcel({ tracking_number: 'ZZ12345678901' }), false);
    expect(value.router.options.refuses).toHaveBeenCalledWith('china-post', 'ZZ12345678901');
    expect(value.universal).not.toHaveBeenCalled();
    expect(value.direct).not.toHaveBeenCalled();
    expect(result.correction).toBeUndefined();
    expect(result.result.routing).not.toHaveProperty('configured_carrier', 'china-post');
    expect(result.result.routing).not.toHaveProperty('discovered_carrier');
  });
  it('asks no carrier a provider names when its adapter refuses the number', async () => {
    const value = setup();
    value.router.options.refuses = vi.fn(async (carrier: string) => carrier === 'poste-italiane');
    value.universal.mockResolvedValue({ ...history(), discovered_carrier: 'poste-italiane', reported_carriers: ['Italy Post'] });
    const result = await value.router.fetch(parcel({ carrier: 'intl-post', tracking_number: 'RR123456785MG' }), false);
    expect(value.direct).not.toHaveBeenCalledWith(expect.anything(), 'poste-italiane');
    expect(result.result.tracking_provider).toBeDefined();
    expect(result.result.routing).not.toHaveProperty('discovered_carrier');
    expect(result.result.routing).not.toHaveProperty('failures.poste-italiane');
  });
  it('files an unknown parcel under the journey owner a provider names among several carriers', async () => {
    const value = setup();
    value.universal.mockResolvedValue({ ...history(), discovered_carrier: 'j-and-t-mx',
      reported_carriers: ['Estafeta', 'J&T Express Mexico', 'DHL Express'] });
    const result = await value.router.fetch(parcel({ tracking_number: 'JMX000000000001' }), false);
    expect(result.correction).toEqual({ carrier: 'j-and-t-mx', trackingUrl: null, postcode: null });
    expect(result.result.tracking_provider).toBeDefined();
  });
  it.each(['unknown', 'la-poste'])('keeps provider history and changes only an unknown carrier: %s', async (carrier) => {
    const value = setup();
    value.direct.mockRejectedValue(new Error('carrier blocked'));
    value.universal.mockResolvedValue({ ...history(), discovered_carrier: 'dhl-express', reported_carriers: ['DHL Express'] });
    const result = await value.router.fetch(parcel({ carrier, tracking_number: '1234567891' }), false);
    expect(result.correction?.carrier).toBe(carrier === 'unknown' ? 'dhl-express' : undefined);
    expect(result.result.tracking_provider).toBe('Ship24');
    expect(result.result.routing).not.toHaveProperty('confirmed_carrier');
  });
  it('keeps provider history without filing an unknown parcel under a carrier that refuses the number', async () => {
    const value = setup();
    value.direct.mockRejectedValue(new InvalidInputError('DHL Express', 'The waybill check digit does not match'));
    value.universal.mockResolvedValue({ ...history(), discovered_carrier: 'dhl-express', reported_carriers: ['DHL Express'] });
    const result = await value.router.fetch(parcel({ tracking_number: '1234567891' }), false);
    expect(value.direct).toHaveBeenCalledExactlyOnceWith(expect.objectContaining({ carrier: 'dhl-express' }), 'dhl-express');
    expect(result.correction).toBeUndefined();
    expect(result.result.tracking_provider).toBe('Ship24');
    expect(result.result.routing).not.toHaveProperty('discovered_carrier');
    expect(result.result.routing).toMatchObject({ failures: { 'dhl-express': { user_error: 'carrier:invalid_input' } } });
    // Within the carrier's cooldown the provider names it again, unasked.
    value.direct.mockClear();
    const again = await value.router.fetch(parcel({ tracking_number: '1234567891', carrier_data: { routing: result.result.routing as JsonObject } }), false);
    expect(value.direct).not.toHaveBeenCalled();
    expect(again.correction).toBeUndefined();
    expect(again.result.tracking_provider).toBe('Ship24');
  });

  it('reuses a matching history before acquiring an upstream lease', async () => {
    const value = setup();
    value.router.options.takePrefetchedUniversal = vi.fn((source) => source === 'Ship24' ? { ...history(), discovered_carrier: 'dhl-express' } : undefined);
    const result = await value.router.fetch(parcel({ tracking_number: '1234567891' }), false);
    expect(result.result.tracking_provider).toBe('Ship24');
    expect(value.health.acquireTrackingProvider).not.toHaveBeenCalled();
    expect(value.universal).not.toHaveBeenCalled();
    expect(value.recognize).not.toHaveBeenCalled();
    expect(value.recognizeBrowser).not.toHaveBeenCalled();
    expect(value.direct).not.toHaveBeenCalled();
  });
  it.each(['ParcelsApp', '17TRACK'])('retains %s postcode requirements and keeps them out of global failure cooldowns', async provider => {
    const value = setup();
    value.universal.mockImplementation(async (source) => { if (source === provider) throw new InputRequiredError(source, 'postcode'); throw new NotFoundError(source); });
    const error = await value.router.fetch(parcel({ tracking_number: '1234567891' }), false).catch((error) => error);
    expect(error).toBeInstanceOf(RoutingDeferred);
    expect(error.routing).toMatchObject({ provider_input_needed: { provider, field: 'dpdPostcode' }, failures: { [provider]: { kind: 'input_required' } } });
    expect(value.health.finishTrackingProvider.mock.calls.every((call) => call[2] === 'not_found')).toBe(true);
  });
  it('submits provider credentials only for their bound number and clears the prompt after recovery', async () => {
    const matching = setup();
    const result = await matching.router.fetch(parcel({ tracking_number: '1234567891', dpd_postcode: '9999', carrier_data: {
      universal_input: { number: '1234567891', postcode: '8000' },
      routing: { version: 1, provider_input_needed: { provider: 'ParcelsApp', field: 'dpdPostcode' } },
    } }), false);
    expect(matching.universal).toHaveBeenCalledWith('Ship24', '1234567891', expect.any(Number), '8000', null, null);
    expect(result.result.routing).not.toHaveProperty('provider_input_needed');
    const foreign = setup();
    await foreign.router.fetch(parcel({ tracking_number: '1234567891', carrier_data: { universal_input: { number: '1234567880', postcode: '8000' } } }), false);
    expect(foreign.universal).toHaveBeenCalledWith('Ship24', '1234567891', expect.any(Number), null, null, null);
  });
  it('counts a postcode request once per prompt and what each lookup with the owner\'s postcode found', async () => {
    const recorded = vi.spyOn(metrics, 'recordProviderInput').mockImplementation(() => undefined);
    const gated = setup();
    gated.universal.mockImplementation(async (source) => { if (source === 'ParcelsApp') throw new InputRequiredError(source, 'postcode'); throw new NotFoundError(source); });
    await gated.router.fetch(parcel({ tracking_number: '1234567891' }), false).catch(() => undefined);
    await gated.router.fetch(parcel({ tracking_number: '1234567891', carrier_data: {
      routing: state({ provider_input_needed: { provider: 'ParcelsApp', field: 'dpdPostcode' } }) } }), false).catch(() => undefined);
    expect(recorded.mock.calls).toEqual([['ParcelsApp', 'asked']]);
    recorded.mockClear();
    const supplied = parcel({ tracking_number: '1234567891', carrier_data: { universal_input: { number: '1234567891', postcode: '8000' } } });
    await gated.router.fetch(supplied, false).catch(() => undefined);
    expect(recorded).toHaveBeenCalledWith('ParcelsApp', 'still_required');
    expect(recorded).toHaveBeenCalledWith('Ship24', 'no_history');
    expect(recorded).not.toHaveBeenCalledWith(expect.anything(), 'asked');
    recorded.mockClear();
    const unlocked = setup();
    unlocked.universal.mockImplementation(async (source) => { if (source === 'ParcelsApp') return history(); throw new NotFoundError(source); });
    await unlocked.router.fetch(supplied, false);
    expect(recorded).toHaveBeenCalledWith('ParcelsApp', 'history');
    expect(monitoring.reportRoutingEvent).toHaveBeenCalledWith('provider_input_lookup', expect.objectContaining({ provider: 'ParcelsApp', category: 'history' }));
  });
  it('leaves a provider waiting for a missing postcode to refreshes by hand until one is given', async () => {
    const asked = (value: ReturnType<typeof setup>) => value.universal.mock.calls.map(([source]) => source);
    const first = setup();
    first.universal.mockImplementation(async (source) => { if (source === 'ParcelsApp') throw new InputRequiredError(source, 'postcode'); throw new NotFoundError(source); });
    const { routing } = await first.router.fetch(parcel({ tracking_number: '1234567891' }), true).catch((error) => error);
    expect(routing.failures.ParcelsApp).toMatchObject({ kind: 'input_required', retry_at: '2026-09-10T12:15:00.000Z' });
    // Its wait would wake the parcel for nothing: the next check is the hourly one.
    expect(routing.next_check_at).toBe('2026-09-10T13:00:00.000Z');
    const later = new Date('2026-09-10T13:00:00Z');
    const scheduled = setup(later);
    await scheduled.router.fetch(parcel({ tracking_number: '1234567891', carrier_data: { routing } }), true).catch(() => undefined);
    expect(asked(scheduled)).not.toContain('ParcelsApp');
    const byHand = setup(later);
    await byHand.router.fetch(parcel({ tracking_number: '1234567891', carrier_data: { routing } }), false);
    expect(asked(byHand)).toContain('ParcelsApp');
    const given = setup(later);
    await given.router.fetch(parcel({ tracking_number: '1234567891', dpd_postcode: '8000', carrier_data: { routing } }), true);
    expect(given.universal).toHaveBeenCalledWith('ParcelsApp', '1234567891', expect.any(Number), '8000', null, null);
  });
  it('takes a postcode request for a number no carrier issues in that shape as no history', async () => {
    const value = setup();
    value.universal.mockImplementation(async (source) => { throw new InputRequiredError(source, 'postcode'); });
    const { routing } = await value.router.fetch(parcel({ tracking_number: 'NOSHAPE42', carrier_data: { routing: state({
      provider_input_needed: { provider: 'ParcelsApp', field: 'dpdPostcode' } }) } }), true).catch((error) => error);
    expect(routing.failures.ParcelsApp).toMatchObject({ kind: 'no_history', user_error: 'carrier:indeterminate' });
    expect(routing).not.toHaveProperty('provider_input_needed');
    // Filed with a carrier that takes a postcode, the same number may well need one.
    const filed = setup();
    filed.direct.mockRejectedValue(new NotFoundError('dpd'));
    filed.universal.mockImplementation(async (source) => { throw new InputRequiredError(source, 'postcode'); });
    const asked = await filed.router.fetch(parcel({ carrier: 'dpd', tracking_number: 'NOSHAPE42' }), true).catch((error) => error);
    expect(asked.routing.provider_input_needed).toMatchObject({ field: 'dpdPostcode' });
  });
});
