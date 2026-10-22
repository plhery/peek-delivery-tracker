import { recognitionCandidates } from 'universal-parcel-scraper';
import { describe, expect, it } from 'vitest';
import { rerouteAskable, rerouteCarrier } from './redetection';
import { catalogRules, opensBrowser, recognizers } from './trackingRouting';
import type { JsonObject } from './types';

const routing = { version: 1, configured_carrier: 'unknown', failures: {}, probe_cursor: 0, discovery_cursor: 0, rules: 'older' };
const parcel = (values: JsonObject, data: JsonObject = {}): JsonObject => ({
  carrier: 'unknown', tracking_number: '1Z999AA10123456784', current_stage: 'in_transit', ...values,
  carrier_data: { tracking_provider: 'ParcelsApp', ...data, routing: { ...routing, ...data.routing as JsonObject } },
});

it('points an unknown parcel to the carrier its number is now detected as', () => {
  expect(rerouteCarrier(parcel({}))).toBe('ups');
  // Without a provider's history too: the carrier's own adapter is asked.
  expect(rerouteCarrier(parcel({}, { tracking_provider: undefined }))).toBe('ups');
});

// A synthetic number no rule claims and no carrier's recognition is asked about.
const UNCLAIMED = 'ABCD0000000001CN';
// The carrier recognition asks about a ten-digit DHL Express waybill after DHL Express, as the scraper orders them.
const NEXT = recognitionCandidates('1234567891')[1]!.carrier;

it('points an unknown parcel to a carrier a provider named, read with the current aliases', () => {
  expect(rerouteCarrier(parcel({ tracking_number: UNCLAIMED }, { reported_carriers: ['UPS'] }))).toBe('ups');
  // A hint the parcel already holds is asked on its next check anyway.
  expect(rerouteCarrier(parcel({ tracking_number: UNCLAIMED },
    { reported_carriers: ['UPS'], routing: { discovered_carrier: 'ups' } }))).toBeNull();
});

it('points a parcel filed under no carrier to a carrier without an adapter of its own', () => {
  expect(rerouteCarrier(parcel({ tracking_number: 'JMX000000000001' }, { reported_carriers: ['J&T Express Mexico', 'Estafeta'] })))
    .toBe('j-and-t-mx');
  // No provider has answered for it yet: its check asks them about that carrier.
  expect(rerouteCarrier(parcel({ tracking_number: 'JMX000000000001' }, { tracking_provider: undefined }))).toBe('j-and-t-mx');
  // A parcel filed under a carrier keeps it.
  expect(rerouteCarrier(parcel({ carrier: 'dhl', tracking_number: 'JMX000000000001' }, { routing: { failures: { dhl: { kind: 'not_found' } } } })))
    .toBeNull();
});

it('points an unknown parcel to the first carrier its check would ask again, asked before or not', () => {
  const now = Date.parse('2026-09-10T12:00:00Z');
  const probe = (retry: string) => ({ count: 3, retry_at: retry });
  const unseen = (data: JsonObject) => parcel({ tracking_number: '1234567891', current_stage: 'pending' }, { tracking_provider: undefined, ...data });
  expect(rerouteCarrier(unseen({}), 'newer', now)).toBe('dhl-express');
  // A release may have taught its adapter the number since it was asked.
  expect(rerouteCarrier(unseen({ routing: { candidate_probes: { 'dhl-express': probe('2026-09-10T08:00:00Z') } } }), 'newer', now))
    .toBe('dhl-express');
  // One still waiting is asked when its wait is over, and the next one now.
  expect(rerouteCarrier(unseen({ routing: { candidate_probes: { 'dhl-express': probe('2026-09-10T18:00:00Z') } } }), 'newer', now))
    .toBe(NEXT);
  // Not for a parcel filed under a carrier.
  expect(rerouteCarrier(parcel({ carrier: 'dhl', tracking_number: '1234567891' }, { routing: { failures: { dhl: { kind: 'not_found' } } } }),
    'newer', now)).toBeNull();
});

it('asks an unknown parcel\'s detected carrier again once its wait is over, asked before or not', () => {
  const now = Date.parse('2026-09-10T12:00:00Z');
  const failed = (retry: string) => parcel({}, { routing: { failures: { ups: { kind: 'rate_limited', count: 1, retry_at: retry } } } });
  expect(rerouteCarrier(failed('2026-09-10T11:00:00Z'), 'newer', now)).toBe('ups');
  expect(rerouteCarrier(failed('2026-09-10T13:00:00Z'), 'newer', now)).toBeNull();
});

it('points a parcel a provider tracks back to its own carrier when that carrier was never asked', () => {
  const filed = { carrier: 'royal-mail', tracking_number: 'RR123456785GB' };
  expect(rerouteCarrier(parcel(filed))).toBe('royal-mail');
  expect(rerouteCarrier(parcel(filed, { routing: { failures: { 'royal-mail': { kind: 'transport' } } } }))).toBeNull();
  // The carrier answers it directly.
  expect(rerouteCarrier(parcel(filed, { tracking_provider: undefined }))).toBeNull();
});

it('points a parcel a provider tracks back to its own carrier when only recognition asked it before it was filed', () => {
  const filed = { carrier: 'royal-mail', tracking_number: 'RR123456785GB' };
  const probes = { candidate_probes: { 'royal-mail': { count: 1, retry_at: '2999-01-01T00:00:00Z' } } };
  expect(rerouteCarrier(parcel(filed, { routing: probes }))).toBe('royal-mail');
  expect(rerouteCarrier(parcel(filed, { routing: { ...probes, confirmed_carrier: 'royal-mail' } }))).toBeNull();
});

it('points a parcel a provider dates back to its own carrier once the catalog dates that carrier\'s scans', () => {
  // Tianhui Hengyuan's scans are dated now; BRT's clocks are still local.
  const answered = (carrier: string, tracking_number: string) => parcel({ carrier, tracking_number }, {
    tracking_provider: '17TRACK', carrier_answered: true,
    direct_local_history: { carrier, number: tracking_number, events: [] },
    routing: { configured_carrier: carrier, confirmed_carrier: carrier, confirmed_number: tracking_number, preferred_provider: '17TRACK' },
  });
  expect(rerouteCarrier(answered('thhy', 'AT0000000001TH'))).toBe('thhy');
  // Checked within half a day.
  expect(rerouteCarrier({ ...answered('brt', '000000000001'), last_synced_at: new Date().toISOString() })).toBeNull();
  // The carrier already answers it directly.
  expect(rerouteCarrier(parcel({ carrier: 'thhy', tracking_number: 'AT0000000001TH' },
    { tracking_provider: undefined, routing: { confirmed_carrier: 'thhy' } }))).toBeNull();
});

it('asks a parcel\'s own carrier again after a release once its answer on local clocks is half a day old', async () => {
  // The catalog still gives BRT's clocks as local; the release's adapter may date its scans all the same.
  const now = Date.parse('2026-09-10T12:00:00Z');
  const answered = (last_synced_at: string, failures: JsonObject = {}) =>
    parcel({ carrier: 'brt', tracking_number: '000000000001', last_synced_at }, {
      tracking_provider: '17TRACK', carrier_answered: true,
      direct_local_history: { carrier: 'brt', number: '000000000001', events: [] },
      routing: { configured_carrier: 'brt', confirmed_carrier: 'brt', confirmed_number: '000000000001', preferred_provider: '17TRACK', failures },
    });
  const lead = (item: JsonObject) => rerouteAskable(item, 'newer', now, async () => false);
  expect(await lead(answered('2026-09-10T08:00:00Z'))).toBeNull();
  expect(await lead(answered('2026-09-09T20:00:00Z'))).toEqual({ carrier: 'brt', rank: 2 });
  // A passing failure's wait holds; a refusal under the older release does not.
  const failure = (kind: string) => ({ brt: { kind, count: 1, retry_at: '2026-09-10T13:00:00Z' } });
  expect(await lead(answered('2026-09-09T20:00:00Z', failure('transport')))).toBeNull();
  expect(await lead(answered('2026-09-09T20:00:00Z', failure('invalid_input')))).toEqual({ carrier: 'brt', rank: 2 });
  // Not once the release's rules have routed it.
  expect(await rerouteAskable(answered('2026-09-09T20:00:00Z'), 'older', now, async () => false)).toBeNull();
});

it.each<[string, JsonObject, JsonObject]>([
  ['routed with the current rules', {}, { routing: { rules: catalogRules() } }],
  ['delivered', { current_stage: 'delivered' }, {}],
  ['returned', { current_stage: 'returned' }, {}],
  ['corrected from another carrier', {}, { original_carrier: 'dhl' }],
  ['whose carrier failed and is waiting', {}, { routing: { failures: { ups: { kind: 'not_found', count: 1, retry_at: '2999-01-01T00:00:00Z' } } } }],
  ['whose carrier was probed and is waiting', {}, { routing: { candidate_probes: { ups: { count: 1, retry_at: '2999-01-01T00:00:00Z' } } } }],
  ['whose carrier is confirmed', {}, { routing: { confirmed_carrier: 'ups' } }],
])('leaves alone a parcel %s', (_name, values, data) => {
  expect(rerouteCarrier(parcel(values, data))).toBeNull();
});

it('asks an unknown parcel\'s carrier again once a deferred check cleared its confirmation and its wait is over', () => {
  const now = Date.parse('2026-09-10T12:00:00Z');
  const cleared = (retry: string) => parcel({}, { routing: { cleared_confirmation: { carrier: 'ups', number: '1Z999AA10123456784' },
    failures: { ups: { kind: 'no_history', count: 1, retry_at: retry } } } });
  expect(rerouteCarrier(cleared('2026-09-10T11:00:00Z'), 'newer', now)).toBe('ups');
  expect(rerouteCarrier(cleared('2026-09-10T13:00:00Z'), 'newer', now)).toBeNull();
});

it('points a parcel to a carrier that refused its number under an older release', async () => {
  const now = Date.parse('2026-09-10T12:00:00Z');
  const refusal = { kind: 'schema', count: 5, retry_at: '2026-09-09T06:00:00Z', user_error: 'carrier:invalid_input' };
  // The provider's name the parcel already holds, which its next check would ask, but there is none.
  const named = parcel({ tracking_number: UNCLAIMED }, { reported_carriers: ['UPS'],
    routing: { discovered_carrier: 'ups', failures: { ups: refusal } } });
  expect(rerouteCarrier(named, 'newer', now)).toBe('ups');
  // The filed carrier, and the one detection names for a parcel filed under another.
  expect(rerouteCarrier(parcel({ carrier: 'royal-mail', tracking_number: 'RR123456785GB' },
    { routing: { failures: { 'royal-mail': refusal } } }), 'newer', now)).toBe('royal-mail');
  expect(rerouteCarrier(parcel({ carrier: 'dhl' }, { routing: { failures: { ups: refusal } } }), 'newer', now)).toBe('ups');
  // Today's adapter refuses it too.
  expect(await rerouteAskable(named, 'newer', now, async () => true)).toBeNull();
});

it('passes over a carrier whose adapter refuses the number, until a release whose adapter may take it', async () => {
  const refuses = (refusing: string[]) => async (carrier: string) => refusing.includes(carrier);
  // A provider's name the check could not ask, and nothing else to ask.
  const named = parcel({ tracking_number: UNCLAIMED }, { reported_carriers: ['UPS'] });
  expect(await rerouteAskable(named, 'newer', Date.now(), refuses(['ups']))).toBeNull();
  expect(await rerouteAskable(named, 'newer', Date.now(), refuses([]))).toEqual({ carrier: 'ups', rank: 1 });
  // The next carrier the check would ask instead.
  const now = Date.parse('2026-09-10T12:00:00Z');
  const unseen = parcel({ tracking_number: '1234567891', current_stage: 'pending' }, { tracking_provider: undefined });
  expect(await rerouteAskable(unseen, 'newer', now, refuses(['dhl-express']))).toEqual({ carrier: NEXT, rank: 2 });
  // The adapters' own checks, which send nothing: DPD France's takes no 18 digits.
  const dpd = parcel({ tracking_number: '123456789012345678' }, { reported_carriers: ['DPD France'] });
  expect(rerouteCarrier(dpd, 'newer', now)).toBe('dpd-fr');
  expect((await rerouteAskable(dpd, 'newer', now))?.carrier).not.toBe('dpd-fr');
});

it('ranks the checks most likely to change the answer first', async () => {
  const now = Date.parse('2026-09-10T12:00:00Z');
  const lead = (values: JsonObject, data: JsonObject = {}) => rerouteAskable(parcel(values, data), 'newer', now, async () => false);
  const refusal = { kind: 'schema', count: 5, retry_at: '2026-09-09T06:00:00Z', user_error: 'carrier:invalid_input' };
  // The carrier's own answer is now dated, or the number it refused may be taken now.
  expect(await lead({ carrier: 'thhy', tracking_number: 'AT0000000001TH' }, { tracking_provider: '17TRACK',
    direct_local_history: { carrier: 'thhy', number: 'AT0000000001TH', events: [] }, routing: { confirmed_carrier: 'thhy' } }))
    .toEqual({ carrier: 'thhy', rank: 0 });
  expect(await lead({}, { routing: { failures: { ups: refusal } } })).toEqual({ carrier: 'ups', rank: 0 });
  // A carrier never asked.
  expect(await lead({})).toEqual({ carrier: 'ups', rank: 1 });
  // A carrier asked again once a passing failure is over, and recognition's first guess.
  expect(await lead({}, { routing: { failures: { ups: { kind: 'rate_limited', count: 1, retry_at: '2026-09-10T11:00:00Z' } } } }))
    .toEqual({ carrier: 'ups', rank: 2 });
  expect(await lead({ tracking_number: '1234567891', current_stage: 'pending' }, { tracking_provider: undefined }))
    .toEqual({ carrier: 'dhl-express', rank: 2 });
});

it('points a parcel filed under no carrier to a carrier the release taught to recognize its number', async () => {
  const now = Date.parse('2026-09-10T12:00:00Z');
  const asked = { 'dhl-express': { count: 3, retry_at: '2026-09-10T11:00:00Z' } };
  const unfiled = (routing: JsonObject) => parcel({ tracking_number: '1234567891', current_stage: 'pending' },
    { tracking_provider: undefined, routing: { candidate_probes: asked, ...routing } });
  const lead = (item: JsonObject, refusing: string[] = []) => rerouteAskable(item, 'newer', now, async (carrier) => refusing.includes(carrier));
  // The older release's recognition did not list the next carrier.
  const before = recognizers('1234567891').filter((carrier) => carrier !== NEXT);
  expect(await lead(unfiled({ recognizers: before }))).toEqual({ carrier: NEXT, rank: 1 });
  // Unless its adapter refuses the number.
  expect(await lead(unfiled({ recognizers: before }), [NEXT])).toEqual({ carrier: 'dhl-express', rank: 2 });
  // The same carriers, or a check that noted none: recognition's first guess, asked again.
  expect(await lead(unfiled({ recognizers: recognizers('1234567891') }))).toEqual({ carrier: 'dhl-express', rank: 2 });
  expect(await lead(unfiled({}))).toEqual({ carrier: 'dhl-express', rank: 2 });
});

it('asks a carrier the parcel already asked that can open a browser again only once the parcel has gone half a day without a check', () => {
  const now = Date.parse('2026-09-10T12:00:00Z');
  const probe = { count: 7, retry_at: '2026-09-10T11:00:00Z' };
  const unseen = (checked: string, probes: JsonObject) => parcel({ tracking_number: '1234567891', current_stage: 'pending',
    last_synced_at: checked }, { tracking_provider: undefined, routing: { candidate_probes: probes } });
  // DHL Express, whose lookup can open a browser, asked an hour ago: the next carrier its
  // recognition would ask, never asked yet.
  expect(rerouteCarrier(unseen('2026-09-10T11:00:00Z', { 'dhl-express': probe }), 'newer', now)).toBe(NEXT);
  // That one opens none: asked an hour ago, it is asked again at once.
  expect(opensBrowser(NEXT)).toBe(false);
  expect(rerouteCarrier(unseen('2026-09-10T11:00:00Z', { 'dhl-express': probe, [NEXT]: probe }), 'newer', now)).toBe(NEXT);
  // Half a day later, DHL Express is asked again.
  expect(rerouteCarrier(unseen('2026-09-09T23:00:00Z', { 'dhl-express': probe }), 'newer', now)).toBe('dhl-express');
  // So is a detected carrier whose failure's wait is over.
  const failed = (checked: string) => parcel({ last_synced_at: checked },
    { routing: { failures: { ups: { kind: 'not_found', count: 3, retry_at: '2026-09-10T11:30:00Z' } } } });
  expect(rerouteCarrier(failed('2026-09-10T11:00:00Z'), 'newer', now)).not.toBe('ups');
  expect(rerouteCarrier(failed('2026-09-09T23:00:00Z'), 'newer', now)).toBe('ups');
});

describe('a carrier whose answer was set aside as older than the parcel', () => {
  const now = Date.parse('2026-10-08T12:00:00Z');
  // An S10 item the catalog now points to Australia Post, a provider's misread clock ahead of its scans.
  const aside = { 'australia-post': { checked_at: '2026-10-08T11:00:00.000Z', newest: '2026-10-07T23:01:00.000Z',
    watermark: '2026-10-08T05:46:00.000Z', count: 3 } };
  const item = (watermark: string, values: JsonObject = {}) => parcel({ carrier: 'intl-post', tracking_number: 'EY123456785AU',
    last_synced_at: '2026-10-08T11:00:00Z', ...values }, { routing: { configured_carrier: 'intl-post', last_event_at: watermark, set_aside: aside } });

  it('is asked again once the same answer would no longer be set aside', async () => {
    const lead = (watermark: string, values?: JsonObject) => rerouteAskable(item(watermark, values), 'newer', now, async () => false);
    // The same answer would be set aside again within the hour.
    expect(await lead('2026-10-08T05:46:00.000Z')).toBeNull();
    expect(await lead('2026-10-08T05:46:00.000Z', { last_synced_at: '2026-10-07T23:00:00Z' })).toEqual({ carrier: 'australia-post', rank: 2 });
    // The provider's corrected clock brought the parcel's newest scan back to the carrier's.
    expect(await lead('2026-10-07T23:01:00.000Z')).toEqual({ carrier: 'australia-post', rank: 0 });
    // Or to the same minute, read to the second.
    expect(await lead('2026-10-07T23:01:42.318Z')).toEqual({ carrier: 'australia-post', rank: 0 });
  });

  it('counts as asked for a parcel filed under a carrier', () => {
    const filed = (routing: JsonObject) => rerouteCarrier(parcel({ carrier: 'dhl' }, { routing: { failures: { dhl: { kind: 'not_found' } }, ...routing } }), 'newer', now);
    expect(filed({})).toBe('ups');
    expect(filed({ last_event_at: '2026-09-10T11:00:00.000Z',
      set_aside: { ups: { checked_at: '2026-10-08T11:00:00.000Z', newest: '2026-09-10T03:00:00.000Z', watermark: '2026-09-10T11:00:00.000Z', count: 1 } } }))
      .toBeNull();
    // So does one set aside without movement.
    expect(filed({ set_aside: { ups: { reason: 'no_movement', checked_at: '2026-10-08T11:00:00.000Z', count: 1 } } })).toBeNull();
  });
});

describe('a carrier whose answer was set aside without movement or on local clocks', () => {
  const now = Date.parse('2026-10-08T12:00:00Z');
  const item = (carrier: string, reason: string, values: JsonObject = {}) => parcel({ carrier: 'intl-post', tracking_number: 'EY123456785AU',
    last_synced_at: '2026-10-08T11:00:00Z', ...values }, { routing: { configured_carrier: 'intl-post', last_event_at: '2026-10-08T05:46:00.000Z',
    set_aside: { [carrier]: { reason, checked_at: '2026-10-08T11:00:00.000Z', count: 2 } } } });
  const lead = (parcel: JsonObject) => rerouteAskable(parcel, 'newer', now, async () => false);

  it('waits half a day like any carrier asked that can open a browser, whatever the watermark', async () => {
    expect(await lead(item('australia-post', 'no_movement'))).toBeNull();
    expect(await lead(item('australia-post', 'no_movement', { last_synced_at: '2026-10-07T23:00:00Z' }))).toEqual({ carrier: 'australia-post', rank: 2 });
  });

  it('goes first, without waiting, once the catalog dates the carrier\'s scans', async () => {
    // Australia Post's clocks aren't local: an answer set aside on them would now be dated.
    expect(await lead(item('australia-post', 'local_clock'))).toEqual({ carrier: 'australia-post', rank: 0 });
    // YunExpress's still are.
    expect(await lead(item('yunexpress', 'local_clock'))).not.toEqual({ carrier: 'yunexpress', rank: 0 });
  });
});
