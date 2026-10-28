import { createECDH, createHash, generateKeyPairSync, randomBytes } from 'node:crypto';
import { NextRequest } from 'next/server';
import { afterEach, beforeEach, describe, expect, it, vi } from 'vitest';
import webpush from 'web-push';
import { POST as lookUp } from '../../app/api/public/parcels/route';
import { DELETE as forget, GET as read, PATCH as change } from '../../app/api/public/parcels/[linkId]/route';
import { DELETE as alertOff, PUT as alertOn } from '../../app/api/public/parcels/[linkId]/alerts/route';
import { DELETE as appAlertOff, PUT as appAlertOn } from '../../app/api/public/parcels/[linkId]/app-alerts/route';
import { POST as postcodeAdded } from '../../app/api/public/parcels/[linkId]/postcode/route';
import { AMAZON_ACCOUNT_MESSAGE } from '../lib/amazon';
import * as amazon from './amazonShippingEligibility';
import { HttpError } from './api';
import { SupabaseAuthenticator } from './auth';
import * as background from './background';
import * as linkPostcodes from './linkPostcodes';
import * as metrics from './metrics';
import * as observability from './observability';
import { SupabaseError, SupabaseServiceClient } from './supabase';
import type { JsonObject } from './types';

// Synthetic identifiers only: no carrier issued any of these.
const linkId = 'k7Qm2xHd9RtW';
const ownerKey = 'A'.repeat(43);
const keyHash = createHash('sha256').update(ownerKey).digest('hex');
const packageId = '51000000-0000-4000-a000-000000000001';
const trackingNumber = 'TESTPARCEL983456';
const postcode = '9999';

/** A package row as the database returns it to the server, with everything a link must not show. */
function storedPackage(overrides: JsonObject = {}): JsonObject {
  return {
    id: packageId,
    user_id: null,
    one_off: true,
    tracking_number: trackingNumber,
    label: 'A name that stays on the device',
    carrier: 'planzer',
    created_at: '2026-09-20T08:00:00+00:00',
    current_stage: 'in_transit',
    expected_delivery: '2026-10-03',
    expected_delivery_changed_at: null,
    last_status_text: 'In transit',
    last_synced_at: '2026-10-02T09:00:00+00:00',
    sync_status: 'ok',
    sync_error: null,
    tracking_url: 'https://trackandtrace.planzergroup.com/shared/sendungen/SECRET-CAPABILITY',
    dpd_postcode: postcode,
    archived_at: null,
    notifications_muted: true,
    tracking_generation: '51000000-0000-4000-a000-000000000009',
    carrier_data: {
      sender_name: 'Example Shop',
      weight_kg: 1.2,
      dimensions_text: '30 × 20 × 10 cm',
      pickup_point: 'Example Kiosk\nExample Street 1',
      destination_country: 'CH',
      expected_delivery_from: '2026-10-03T08:00:00+02:00',
      tracking_provider: 'ParcelsApp',
      carrier_answered: true,
      swiss_post_ready: true,
      active_tracking_carrier: 'swiss-post',
      active_tracking_number: 'TESTDELIVERYLEG01',
      original_carrier: 'planzer',
      original_tracking_number: 'TESTORIGINLEG0001',
      auto_changed_from: 'unknown',
      auto_changed_to: 'planzer',
      auto_changed_at: '2026-09-28T09:00:00.000Z',
      original_tracking_url: 'https://trackandtrace.planzergroup.com/shared/sendungen/SECRET-ORIGINAL',
      original_package_id: '51000000-0000-4000-a000-000000000007',
      receiver_name: 'Private Recipient',
      dpd_postcode_verified: false,
      pickup_code: 'PIN-4711',
      routing: {
        confirmed_postcode: postcode,
        confirmed_tracking_url: 'https://trackandtrace.planzergroup.com/shared/sendungen/SECRET-ROUTE',
        input_needed: { carrier: 'gls-ch', field: 'dpdPostcode' },
        candidate_probes: { 'gls-ch': { at: '2026-10-01T08:00:00Z' } },
      },
      delivery_probe: { carrier: 'swiss-post', number: 'TESTPROBE00000001' },
      upu_history: [{ description: 'Private archive' }],
    },
    tracking_events: [{
      id: '51000000-0000-4000-a000-000000000002',
      package_id: packageId,
      stage: 'in_transit',
      description: 'Sorted at the parcel centre',
      location: 'Zürich',
      occurred_at: '2026-10-01T10:00:00+00:00',
      point: { latitude: 47.38, longitude: 8.5 },
      provider_event_id: 'planzer:private-identity',
    }],
    ...overrides,
  };
}

function storedLink(overrides: JsonObject = {}): JsonObject {
  return {
    id: linkId,
    owner: false,
    shared: false,
    show_number: false,
    gift: false,
    stopped: false,
    created_at: '2026-09-28T08:00:00+00:00',
    forget_at: '2026-12-30T10:00:00+00:00',
    ...overrides,
  };
}

let address = 0;
const nextIp = () => `192.0.2.${++address}`;
const route = (id: string) => ({ params: Promise.resolve({ linkId: id }) });

function lookup(body: unknown, ip = nextIp(), headers: Record<string, string> = {}) {
  return lookUp(new NextRequest('https://delivery.example/api/public/parcels', {
    method: 'POST', headers: { 'content-type': 'application/json', 'x-real-ip': ip, ...headers }, body: JSON.stringify(body),
  }), { params: Promise.resolve({}) });
}
function open(id = linkId, key?: string, ip = nextIp()) {
  return read(new NextRequest(`https://delivery.example/api/public/parcels/${id}`, {
    headers: { 'x-real-ip': ip, ...(key === undefined ? {} : { 'x-parcel-key': key }) },
  }), route(id));
}
/** `key` null sends no owner key. */
function update(body: unknown, id = linkId, key: string | null = ownerKey, ip = nextIp()) {
  return change(new NextRequest(`https://delivery.example/api/public/parcels/${id}`, {
    method: 'PATCH',
    headers: { 'content-type': 'application/json', 'x-real-ip': ip, ...(key === null ? {} : { 'x-parcel-key': key }) },
    body: JSON.stringify(body),
  }), route(id));
}
function alertRequest(handler: typeof alertOn, method: string, body: unknown, id = linkId, key?: string, ip = nextIp()) {
  return handler(new NextRequest(`https://delivery.example/api/public/parcels/${id}/alerts`, {
    method,
    headers: { 'content-type': 'application/json', 'x-real-ip': ip, ...(key === undefined ? {} : { 'x-parcel-key': key }) },
    body: JSON.stringify(body),
  }), route(id));
}
function remove(id = linkId, key?: string, ip = nextIp()) {
  return forget(new NextRequest(`https://delivery.example/api/public/parcels/${id}`, {
    method: 'DELETE', headers: { 'x-real-ip': ip, ...(key === undefined ? {} : { 'x-parcel-key': key }) },
  }), route(id));
}

/** Nothing private, in any spelling, anywhere in an answer. */
function expectNothingPrivate(text: string) {
  for (const secret of ['SECRET', postcode, 'Private', 'PIN-4711', 'TESTPROBE', 'routing', 'candidate_probes',
    'receiver_name', 'original_package_id', 'tracking_generation', 'provider_event_id', 'private-identity',
    'A name that stays', 'user_id', 'one_off', '"point"', keyHash, ownerKey]) {
    expect(text).not.toContain(secret);
  }
}

let wake: ReturnType<typeof vi.spyOn>;
let enqueue: ReturnType<typeof vi.spyOn>;
beforeEach(() => {
  vi.stubEnv('SUPABASE_URL', 'https://database.example');
  vi.stubEnv('SUPABASE_SERVICE_ROLE_KEY', 'test-service-key');
  vi.stubEnv('TRUST_PROXY_HEADERS', 'true');
  vi.stubEnv('SMTP_HOST', '');
  vi.spyOn(console, 'log').mockImplementation(() => undefined);
  vi.spyOn(console, 'error').mockImplementation(() => undefined);
  wake = vi.spyOn(background, 'wakeSyncWorker').mockImplementation(() => undefined);
  vi.spyOn(SupabaseServiceClient.prototype, 'updatePackage').mockResolvedValue(undefined);
  enqueue = vi.spyOn(SupabaseServiceClient.prototype, 'enqueueSyncJob').mockResolvedValue({ row: { id: 'job' }, queued: true });
  vi.spyOn(SupabaseServiceClient.prototype, 'ignoreTypedTrackingSupportCases').mockResolvedValue(0);
});
afterEach(() => { vi.useRealTimers(); vi.restoreAllMocks(); vi.unstubAllEnvs(); });

describe('looking up a parcel without an account', () => {
  const allow = (overallUsed = 1) => vi.spyOn(SupabaseServiceClient.prototype, 'claimPublicAllowance')
    .mockResolvedValue({ allowed: true, scope: null, overallUsed });
  const store = (created = true) => vi.spyOn(SupabaseServiceClient.prototype, 'createOneOffParcel').mockResolvedValue({
    link: storedLink({ owner: true }), package: storedPackage({ last_synced_at: null, sync_status: 'pending' }), created,
  });

  it('stores the number, returns the owner key once and queues the first check without an owner', async () => {
    const authenticate = vi.spyOn(SupabaseAuthenticator.prototype, 'validate');
    const claim = allow();
    const create = store();
    const counted = vi.spyOn(metrics, 'recordPublicLookup');
    const response = await lookup({ trackingNumber: 'test parcel-983456', carrier: 'dpd', dpdPostcode: postcode, label: 'Surprise for Ada' }, '198.51.100.7');
    expect(response.status).toBe(201);
    expect(response.headers.get('cache-control')).toBe('no-store');
    const text = await response.text();
    const answer = JSON.parse(text);
    expect(answer.key).toMatch(/^[A-Za-z0-9_-]{43}$/);
    expect(answer.link).toEqual({
      id: linkId, role: 'owner', kind: 'lookup', createdAt: '2026-09-28T08:00:00.000Z',
      forgetAt: '2026-12-30T10:00:00.000Z', numberShown: true, showNumber: false, canKeep: true,
      gift: false, shared: true, alerts: { available: false, vapidPublicKey: null, email: false },
      // The stored parcel's routing asks for GLS Switzerland's postcode: only its owner is told.
      inputNeeded: { carrier: 'gls-ch', field: 'dpdPostcode' },
    });
    expect(answer.package).toMatchObject({ id: packageId, tracking_number: trackingNumber, number_hint: null, label: '' });

    // The database gets the key's hash, never the key, and never the name.
    const hash = createHash('sha256').update(answer.key).digest('hex');
    expect(create).toHaveBeenCalledExactlyOnceWith(
      { trackingNumber, label: '', carrier: 'dpd', trackingUrl: null, dpdPostcode: postcode, dpdPostcodeCountry: null }, hash,
    );
    expect(text).not.toContain(hash);
    expect(text).not.toContain('Surprise');
    // The daily counter is keyed by a hash of the address, with the default allowances.
    expect(claim).toHaveBeenCalledExactlyOnceWith({
      bucket: expect.stringMatching(/^[0-9a-f]{64}$/), limit: 15, overall: { bucket: 'global', limit: 300 }, network: null,
    });
    expect(enqueue).toHaveBeenCalledExactlyOnceWith({ packageId, reason: 'added' });
    expect(wake).toHaveBeenCalledOnce();
    expect(counted).toHaveBeenCalledExactlyOnceWith('created');
    expect(authenticate).not.toHaveBeenCalled();
    // Cases detection opened on the start of the number, as it was typed, close.
    expect(SupabaseServiceClient.prototype.ignoreTypedTrackingSupportCases)
      .toHaveBeenCalledExactlyOnceWith(trackingNumber, expect.any(Date), expect.any(String), expect.any(String));
  });

  it('saves a trusted visitor country only for a newly created lookup', async () => {
    allow();
    store();
    const updateCountry = vi.spyOn(SupabaseServiceClient.prototype, 'updatePackage').mockResolvedValue(undefined);
    const response = await lookup({ trackingNumber }, nextIp(), { 'cf-connecting-ip': nextIp(), 'cf-ipcountry': 'FR' });
    expect(response.status).toBe(201);
    expect(updateCountry).toHaveBeenCalledWith(packageId, { carrier_data: expect.objectContaining({ lookup_country_hint: 'FR' }) });
    expect(await response.text()).not.toContain('lookup_country_hint');
    expect(updateCountry.mock.invocationCallOrder[0]).toBeLessThan(enqueue.mock.invocationCallOrder[0]!);
    vi.mocked(SupabaseServiceClient.prototype.createOneOffParcel).mockResolvedValue({ link: storedLink({ owner: true }), package: storedPackage(), created: false });
    expect((await lookup({ trackingNumber }, nextIp(), { 'cf-connecting-ip': nextIp(), 'cf-ipcountry': 'CH' })).status).toBe(201);
    expect(updateCountry).toHaveBeenCalledOnce();
  });

  it('saves the device region before the visitor country and never trusts client addition markers', async () => {
    allow(); store();
    const updateCountry = vi.mocked(SupabaseServiceClient.prototype.updatePackage);
    const response = await lookup({ trackingNumber, lookupCountryHint: 'CH', add_recognition_pending: false }, nextIp(),
      { 'cf-connecting-ip': nextIp(), 'cf-ipcountry': 'FR' });
    expect(response.status).toBe(201);
    expect(updateCountry).toHaveBeenCalledWith(packageId, { carrier_data: expect.objectContaining({ lookup_country_hint: 'CH', add_recognition_pending: true }) });
    expect(await response.text()).not.toContain('add_recognition_pending');
  });

  it('keeps the app language a new lookup was made in, when Peek speaks it', async () => {
    allow(); store();
    const update = vi.mocked(SupabaseServiceClient.prototype.updatePackage);
    for (const language of ['xx', 'PL', 7]) {
      expect((await lookup({ trackingNumber, language }, nextIp())).status).toBe(201);
      expect(update.mock.lastCall![1].carrier_data).not.toHaveProperty('lookup_language');
    }
    expect((await lookup({ trackingNumber, language: 'pl' }, nextIp())).status).toBe(201);
    expect(update).toHaveBeenLastCalledWith(packageId, { carrier_data: expect.objectContaining({ lookup_language: 'pl' }) });
  });

  it('keeps the country of a postcode from abroad with the postcode of a new lookup', async () => {
    allow();
    const create = vi.spyOn(SupabaseServiceClient.prototype, 'createOneOffParcel').mockResolvedValue({
      link: storedLink({ owner: true }), created: true,
      package: storedPackage({ carrier: 'spring-gds', dpd_postcode: '1000', last_synced_at: null, sync_status: 'pending' }),
    });
    const updateCountry = vi.mocked(SupabaseServiceClient.prototype.updatePackage);
    const response = await lookup({ trackingNumber: '3SABCD826405193', carrier: 'spring-gds', dpdPostcode: '1000', dpdPostcodeCountry: 'BE' });
    expect(response.status).toBe(201);
    expect(create).toHaveBeenCalledWith(expect.objectContaining({ dpdPostcode: '1000', dpdPostcodeCountry: 'BE' }), expect.any(String));
    expect(updateCountry).toHaveBeenCalledWith(packageId, {
      carrier_data: expect.objectContaining({ postcode_country: { postcode: '1000', country: 'BE' } }),
    });
    expect(await response.text()).not.toContain('postcode_country');
    // A country the carrier takes no postcode from is refused before anything is stored.
    create.mockClear();
    expect((await lookup({ trackingNumber: '3SABCD826405193', carrier: 'spring-gds', dpdPostcode: '1000', dpdPostcodeCountry: 'XX' }, nextIp())).status).toBe(400);
    expect(create).not.toHaveBeenCalled();
  });

  it('rejects invalid device regions before creating a lookup or spending its allowance', async () => {
    const claim = allow(); const create = store();
    expect((await lookup({ trackingNumber, lookupCountryHint: 'ZZ' })).status).toBe(400);
    expect(claim).not.toHaveBeenCalled();
    expect(create).not.toHaveBeenCalled();
  });

  it('refuses an oversized or malformed body at once, before the allowance', async () => {
    const claim = allow();
    const encoder = new TextEncoder();
    // Next passes the body on as a stream of the chunks as they arrive.
    const send = (chunks: Uint8Array[], headers: Record<string, string> = {}) => lookUp(new NextRequest('https://delivery.example/api/public/parcels', {
      method: 'POST', headers: { 'content-type': 'application/json', 'x-real-ip': nextIp(), ...headers }, duplex: 'half',
      body: new ReadableStream<Uint8Array>({ pull(controller) { const chunk = chunks.shift(); if (chunk) controller.enqueue(chunk); else controller.close(); } }),
    }), { params: Promise.resolve({}) });
    expect((await send([encoder.encode('x'.repeat(16_385))], { 'content-length': '16385' })).status).toBe(400);
    expect((await send([encoder.encode('x'.repeat(16_384)), encoder.encode('x')])).status).toBe(400);
    expect((await send([Uint8Array.from([0x7b, 0xff]), encoder.encode('}')])).status).toBe(400);
    expect(claim).not.toHaveBeenCalled();
  });

  it('answers a stored parcel the same way and counts it as reused', async () => {
    allow();
    store(false);
    const counted = vi.spyOn(metrics, 'recordPublicLookup');
    expect((await lookup({ trackingNumber })).status).toBe(201);
    expect(counted).toHaveBeenCalledExactlyOnceWith('reused');
    expect(enqueue).toHaveBeenCalledOnce();
  });

  it('still answers when the first check cannot be queued', async () => {
    allow();
    store();
    enqueue.mockRejectedValue(new SupabaseError('queue unavailable', 503));
    expect((await lookup({ trackingNumber })).status).toBe(201);
    expect(wake).not.toHaveBeenCalled();
  });

  it.each([
    [{ trackingNumber: '!!' }, 'Enter a tracking number between 4 and 40 characters'],
    [{ trackingNumber: 'ABCDE' }, 'Tracking numbers use letters and numbers and include a digit, unless a carrier issues them as letters alone'],
    [{ trackingNumber, carrier: 'not-a-carrier' }, 'Choose a supported carrier'],
    [{ trackingNumber, carrier: 'gls-ch' }, expect.stringContaining('postcode')],
    [{ trackingNumber: 'TBA000000000001', carrier: 'amazon-logistics' }, AMAZON_ACCOUNT_MESSAGE],
  ])('rejects %j like the signed-in route, before counting a lookup', async (body, error) => {
    const claim = allow();
    const create = store();
    const response = await lookup(body);
    expect(response.status).toBe(400);
    expect(await response.json()).toEqual({ error });
    expect(claim).not.toHaveBeenCalled();
    expect(create).not.toHaveBeenCalled();
  });

  it.each([['bucket', 'limited_daily'], ['network', 'limited_network'], ['global', 'limited_global']] as const)(
    'refuses once the daily %s allowance is used up, until the next UTC midnight', async (scope, outcome) => {
      vi.useFakeTimers({ toFake: ['Date'], now: new Date('2026-10-02T21:30:00Z') });
      vi.spyOn(SupabaseServiceClient.prototype, 'claimPublicAllowance').mockResolvedValue({ allowed: false, scope, overallUsed: 0 });
      const create = store();
      const counted = vi.spyOn(metrics, 'recordPublicLookup');
      const response = await lookup({ trackingNumber });
      expect(response.status).toBe(429);
      expect(response.headers.get('retry-after')).toBe(String(2.5 * 3_600));
      expect(response.headers.get('cache-control')).toBe('no-store');
      expect(await response.json()).toEqual({ error: expect.any(String), scope: 'daily' });
      expect(create).not.toHaveBeenCalled();
      expect(enqueue).not.toHaveBeenCalled();
      expect(counted).toHaveBeenCalledExactlyOnceWith(outcome);
    },
  );

  it('takes the daily allowances from the environment', async () => {
    vi.stubEnv('PUBLIC_LOOKUPS_PER_DAY', '3');
    vi.stubEnv('PUBLIC_LOOKUPS_GLOBAL_PER_DAY', '0');
    const claim = allow();
    store();
    await lookup({ trackingNumber }, '2001:db8:0:9::1');
    // An IPv6 /48 may make ten clients' lookups.
    expect(claim).toHaveBeenCalledExactlyOnceWith({
      bucket: expect.any(String), limit: 3, overall: { bucket: 'global', limit: 0 },
      network: { bucket: expect.stringMatching(/^network:[0-9a-f]{64}$/), limit: 30 },
    });
  });

  it('counts the lines of one IPv6 /48 against a shared allowance as well', async () => {
    const claim = allow();
    store();
    await lookup({ trackingNumber }, '2001:db8:7:1::1');
    await lookup({ trackingNumber }, '2001:db8:7:2::1');
    await lookup({ trackingNumber }, '2001:db8:8:1::1');
    const claims = claim.mock.calls.map(([claimed]) => claimed);
    expect(claims[0]!.bucket).not.toBe(claims[1]!.bucket);
    expect(claims[0]!.network).toEqual({ bucket: claims[1]!.network!.bucket, limit: 150 });
    expect(claims[2]!.network!.bucket).not.toBe(claims[0]!.network!.bucket);
    // The network's counter is not a client's.
    expect(claims.map((claimed) => claimed.bucket)).not.toContain(claims[0]!.network!.bucket.slice('network:'.length));
  });

  it('asks Amazon about an Amazon Shipping number only once the lookup is counted', async () => {
    const check = vi.spyOn(amazon, 'verifyAmazonShippingAddition').mockResolvedValue(undefined);
    vi.spyOn(SupabaseServiceClient.prototype, 'claimPublicAllowance').mockResolvedValue({ allowed: false, scope: 'bucket', overallUsed: 0 });
    expect((await lookup({ trackingNumber })).status).toBe(429);
    expect(check).not.toHaveBeenCalled();
    const claim = allow();
    store();
    expect((await lookup({ trackingNumber })).status).toBe(201);
    expect(check).toHaveBeenCalledExactlyOnceWith('unknown', trackingNumber);
    expect(claim.mock.invocationCallOrder[0]).toBeLessThan(check.mock.invocationCallOrder[0]!);
  });

  it('says once a day that the overall allowance is running out, and that it is used up', async () => {
    const report = vi.spyOn(observability, 'captureOverallAllowance').mockReturnValue(null);
    store();
    vi.useFakeTimers({ toFake: ['Date'], now: new Date('2026-11-05T10:00:00Z') });
    const claim = allow(239);
    await lookup({ trackingNumber });
    expect(report).not.toHaveBeenCalled();
    claim.mockResolvedValue({ allowed: true, scope: null, overallUsed: 240 });
    await lookup({ trackingNumber });
    await lookup({ trackingNumber });
    expect(report.mock.calls).toEqual([['public', 'lookup', 'running_out', { used: 240, limit: 300 }]]);
    claim.mockResolvedValue({ allowed: false, scope: 'global', overallUsed: 300 });
    await lookup({ trackingNumber });
    await lookup({ trackingNumber });
    expect(report.mock.calls.at(-1)).toEqual(['public', 'lookup', 'used_up', { used: 300, limit: 300 }]);
    expect(report).toHaveBeenCalledTimes(2);
    // The next day it is said again.
    vi.setSystemTime(new Date('2026-11-06T00:00:01Z'));
    await lookup({ trackingNumber });
    expect(report).toHaveBeenCalledTimes(3);
    // An allowance of 0 was turned off on purpose.
    vi.setSystemTime(new Date('2026-11-07T00:00:01Z'));
    vi.stubEnv('PUBLIC_LOOKUPS_GLOBAL_PER_DAY', '0');
    claim.mockResolvedValue({ allowed: false, scope: 'global', overallUsed: 0 });
    await lookup({ trackingNumber });
    expect(report).toHaveBeenCalledTimes(3);
  });

  it.each([
    [{ 'sec-fetch-site': 'cross-site', origin: 'https://elsewhere.example' }, 403],
    [{ 'sec-fetch-site': 'same-site', origin: 'https://sibling.delivery.example' }, 403],
    [{ origin: 'https://elsewhere.example' }, 403],
    [{ 'content-type': 'text/plain' }, 415],
  ])('refuses a lookup that a page of another site could send (%j), before counting it', async (headers, status) => {
    const claim = allow();
    const create = store();
    const response = await lookup({ trackingNumber }, nextIp(), headers);
    expect(response.status).toBe(status);
    expect(claim).not.toHaveBeenCalled();
    expect(create).not.toHaveBeenCalled();
    // The page's own requests go through.
    expect((await lookup({ trackingNumber }, nextIp(), { 'sec-fetch-site': 'same-origin', origin: 'https://delivery.example' })).status).toBe(201);
  });

  it('allows a burst of six lookups a minute per client, and counts an IPv6 line as one client', async () => {
    const claim = allow();
    store();
    const counted = vi.spyOn(metrics, 'recordPublicLookup');
    for (let index = 0; index < 6; index += 1) {
      expect((await lookup({ trackingNumber }, `2001:db8:0:1::${index + 1}`)).status).toBe(201);
    }
    const refused = await lookup({ trackingNumber }, '2001:db8:0:1:ffff::9');
    expect(refused.status).toBe(429);
    expect(Number(refused.headers.get('retry-after'))).toBeGreaterThan(0);
    // A burst refusal has no scope: the client shows a countdown.
    expect(await refused.json()).toEqual({ error: 'Too many requests. Try again shortly.' });
    expect(claim).toHaveBeenCalledTimes(6);
    expect(counted).toHaveBeenLastCalledWith('limited_burst');
    // Every address of the line fed the same daily counter; another line has its own.
    expect(new Set(claim.mock.calls.map(([claimed]) => claimed.bucket)).size).toBe(1);
    expect((await lookup({ trackingNumber }, '2001:db8:0:2::1')).status).toBe(201);
    expect(claim.mock.calls.at(-1)![0].bucket).not.toBe(claim.mock.calls[0]![0].bucket);
  });

  it('logs how each lookup ended and where a failed one stopped, never with its number', async () => {
    const logged = () => [...vi.mocked(console.log).mock.calls, ...vi.mocked(console.error).mock.calls].map(([line]) => String(line));
    const ended = () => logged().map((line) => JSON.parse(line)).filter((line) => line.event === 'public_lookup')
      .map(({ outcome, reason, status, error_class }) => ({ outcome, reason, status, error_class }));
    const claim = allow();
    const create = store();
    expect((await lookup({ trackingNumber })).status).toBe(201);
    expect((await lookup({ trackingNumber: '!!' })).status).toBe(400);
    vi.spyOn(amazon, 'verifyAmazonShippingAddition').mockRejectedValueOnce(new HttpError(503, 'Amazon is unavailable'));
    expect((await lookup({ trackingNumber })).status).toBe(503);
    create.mockRejectedValueOnce(new SupabaseError('database down', 503));
    expect((await lookup({ trackingNumber })).status).toBe(502);
    claim.mockResolvedValueOnce({ allowed: false, scope: 'bucket', overallUsed: 0 });
    expect((await lookup({ trackingNumber })).status).toBe(429);
    expect(ended()).toEqual([
      { outcome: 'created' },
      { outcome: 'failed', reason: 'input', status: 400, error_class: 'HttpError' },
      { outcome: 'failed', reason: 'amazon', status: 503, error_class: 'HttpError' },
      { outcome: 'failed', reason: 'saving', error_class: 'SupabaseError' },
      { outcome: 'limited_daily' },
    ]);
    for (const line of logged()) expect(line).not.toContain(trackingNumber);
  });

  it('counts every client together when the proxy headers are not trusted', async () => {
    vi.stubEnv('TRUST_PROXY_HEADERS', 'false');
    const claim = allow();
    store();
    await lookup({ trackingNumber }, '203.0.113.1');
    await lookup({ trackingNumber }, '203.0.113.2');
    expect(claim.mock.calls[0]![0].bucket).toBe(claim.mock.calls[1]![0].bucket);
  });
});

describe('reading a parcel link', () => {
  const find = (link: JsonObject = {}, parcel: JsonObject = {}) => vi.spyOn(SupabaseServiceClient.prototype, 'publicParcel')
    .mockResolvedValue({ link: storedLink(link), package: storedPackage(parcel) });

  it('shows the owner the whole number and only the carrier details a link may show', async () => {
    const found = find({ owner: true });
    const response = await open(linkId, ownerKey);
    expect(response.status).toBe(200);
    expect(response.headers.get('cache-control')).toBe('no-store');
    expect(found).toHaveBeenCalledExactlyOnceWith(linkId, keyHash, true);
    const text = await response.text();
    expectNothingPrivate(text);
    const answer = JSON.parse(text);
    expect(answer).not.toHaveProperty('key');
    expect(answer.link).toMatchObject({ role: 'owner', kind: 'lookup', numberShown: true, canKeep: true });
    expect(answer.package).toEqual({
      id: packageId,
      tracking_number: trackingNumber,
      number_hint: null,
      label: '',
      carrier: 'planzer',
      // The link is younger than the stored parcel: its own start is shown.
      created_at: '2026-09-28T08:00:00+00:00',
      expected_delivery: '2026-10-03',
      last_status_text: 'In transit',
      last_synced_at: '2026-10-02T09:00:00+00:00',
      sync_status: 'ok',
      sync_error: null,
      tracking_url: null,
      dpd_postcode: null,
      carrier_data: {
        sender_name: 'Example Shop',
        weight_kg: 1.2,
        dimensions_text: '30 × 20 × 10 cm',
        pickup_point: 'Example Kiosk\nExample Street 1',
        destination_country: 'CH',
        expected_delivery_from: '2026-10-03T08:00:00+02:00',
        tracking_provider: 'ParcelsApp',
        carrier_answered: true,
        swiss_post_ready: true,
        active_tracking_carrier: 'swiss-post',
        active_tracking_number: 'TESTDELIVERYLEG01',
        original_carrier: 'planzer',
        original_tracking_number: 'TESTORIGINLEG0001',
        auto_changed_from: 'unknown',
        auto_changed_to: 'planzer',
        auto_changed_at: '2026-09-28T09:00:00.000Z',
      },
      archived_at: null,
      notifications_muted: false,
      tracking_events: [{
        id: '51000000-0000-4000-a000-000000000002',
        package_id: packageId,
        stage: 'in_transit',
        description: 'Sorted at the parcel centre',
        location: 'Zürich',
        occurred_at: '2026-10-01T10:00:00+00:00',
        place: expect.objectContaining({ country: 'CH', name: 'Zürich' }),
      }],
    });
  });

  it.each([[undefined], ['not-a-key'], ['B'.repeat(43)]])('masks the number for a viewer (key %s)', async (key) => {
    const found = find();
    const response = await open(linkId, key);
    expect(found).toHaveBeenCalledExactlyOnceWith(linkId, key === 'B'.repeat(43) ? expect.stringMatching(/^[0-9a-f]{64}$/) : null, true);
    const text = await response.text();
    expectNothingPrivate(text);
    // Neither the parcel's number nor the numbers of its other leg.
    for (const number of [trackingNumber, 'TESTDELIVERYLEG01', 'TESTORIGINLEG0001']) expect(text).not.toContain(number);
    const answer = JSON.parse(text);
    expect(answer.link).toMatchObject({ role: 'viewer', numberShown: false, canKeep: false });
    expect(answer.package).toMatchObject({ tracking_number: null, number_hint: { head: '', tail: '3456' }, tracking_url: null });
    expect(answer.package.carrier_data).not.toHaveProperty('active_tracking_number');
    expect(answer.package.carrier_data).not.toHaveProperty('original_tracking_number');
    expect(answer.package.carrier_data).toMatchObject({ sender_name: 'Example Shop', active_tracking_carrier: 'swiss-post' });
  });

  it('shows a viewer the number of a link that shares it, and a link from an account has no forget date', async () => {
    find({ show_number: true, shared: true, forget_at: null }, { one_off: false, user_id: '51000000-0000-4000-a000-000000000005' });
    const answer = await (await open()).json();
    expect(answer.link).toEqual({
      id: linkId, role: 'viewer', kind: 'shared', createdAt: '2026-09-28T08:00:00.000Z',
      forgetAt: null, numberShown: true, canKeep: true,
      gift: false, shared: true, alerts: { available: false, vapidPublicKey: null, email: false },
    });
    expect(answer.package).toMatchObject({ tracking_number: trackingNumber, number_hint: null, dpd_postcode: null });
    expect(enqueue).not.toHaveBeenCalled();
  });

  it('never passes diagnostic text on as a failure code', async () => {
    find({}, { sync_status: 'error', sync_error: `GET https://carrier.example/track/${trackingNumber} failed` });
    expect((await (await open()).json()).package.sync_error).toBeNull();
    vi.restoreAllMocks();
    find({}, { sync_status: 'error', sync_error: 'carrier:not_found' });
    expect((await (await open()).json()).package.sync_error).toBe('carrier:not_found');
  });

  it('answers an unknown, forgotten, expired and malformed link alike', async () => {
    const found = vi.spyOn(SupabaseServiceClient.prototype, 'publicParcel').mockResolvedValue(null);
    const counted = vi.spyOn(metrics, 'recordPublicParcelRead');
    const unknown = await open('unknownLink2', ownerKey);
    const malformed = await open('not-a-link-id!', ownerKey);
    const lookalike = await open('k7Qm2xHd9Rt0');
    for (const response of [unknown, malformed, lookalike]) {
      expect(response.status).toBe(404);
      expect(response.headers.get('cache-control')).toBe('no-store');
      expect(await response.json()).toEqual({ error: 'Parcel unavailable' });
    }
    // A malformed id never reaches the database.
    expect(found).toHaveBeenCalledExactlyOnceWith('unknownLink2', keyHash, true);
    expect(counted.mock.calls).toEqual([['not_found'], ['not_found'], ['not_found']]);
    expect(enqueue).not.toHaveBeenCalled();
  });

  it('tells a viewer that the sharing was stopped, and nothing else', async () => {
    const found = vi.spyOn(SupabaseServiceClient.prototype, 'publicParcel').mockResolvedValue('stopped');
    const counted = vi.spyOn(metrics, 'recordPublicParcelRead');
    const response = await open(linkId, 'B'.repeat(43));
    expect(response.status).toBe(410);
    expect(response.headers.get('cache-control')).toBe('no-store');
    expect(await response.json()).toEqual({ error: 'Parcel not shared' });
    expect(found).toHaveBeenCalledOnce();
    expect(counted).toHaveBeenCalledExactlyOnceWith('stopped');
    expect(enqueue).not.toHaveBeenCalled();
  });

  it('still shows a stopped lookup to its owner, marked as not shared', async () => {
    find({ owner: true, stopped: true });
    const answer = await (await open(linkId, ownerKey)).json();
    expect(answer.link).toMatchObject({ role: 'owner', shared: false, numberShown: true });
    expect(answer.package.tracking_number).toBe(trackingNumber);
  });

  it('shows a gift on its way to a viewer without its sender, number or origin, whatever the link shows', async () => {
    const gift = {
      current_stage: 'in_transit',
      carrier_data: { sender_name: 'Example Shop', weight_kg: 1.2, destination_country: 'CH', active_tracking_number: 'TESTDELIVERYLEG01' },
      tracking_events: [
        { id: 'scan-3', package_id: packageId, stage: 'in_transit', description: 'Arrived in Switzerland', location: 'Basel, CH', occurred_at: '2026-10-01T12:00:00+00:00' },
        { id: 'scan-2', package_id: packageId, stage: 'accepted', description: 'Picked up at Example Shop', location: 'Hamburg, DE', occurred_at: '2026-10-01T08:00:00+00:00' },
        { id: 'scan-1', package_id: packageId, stage: 'registered', description: 'Announced by Example Shop', location: null, occurred_at: '2026-10-01T06:00:00+00:00' },
      ],
    };
    find({ gift: true, show_number: true, shared: true, forget_at: null }, gift);
    const text = await (await open()).text();
    expectNothingPrivate(text);
    for (const hidden of [trackingNumber, 'Example Shop', 'Hamburg', 'TESTDELIVERYLEG01', 'weight_kg', 'In transit']) expect(text).not.toContain(hidden);
    const answer = JSON.parse(text);
    expect(answer.link).toMatchObject({ role: 'viewer', gift: true, numberShown: false, canKeep: false });
    expect(answer.package).toMatchObject({ tracking_number: null, number_hint: { head: '', tail: '3456' }, last_status_text: null });
    expect(answer.package.tracking_events).toEqual([
      expect.objectContaining({ id: 'scan-3', description: 'Arrived in Switzerland', location: 'Basel, CH' }),
      { id: 'scan-2', package_id: packageId, stage: 'accepted', description: 'Left the sender', location: 'DE',
        occurred_at: '2026-10-01T08:00:00+00:00', place: expect.objectContaining({ precision: 'country', country: 'DE' }) },
    ]);

    // The same link with its owner key shows everything.
    vi.restoreAllMocks();
    vi.spyOn(console, 'log').mockImplementation(() => undefined);
    find({ gift: true, owner: true }, gift);
    const owned = await (await open(linkId, ownerKey)).json();
    expect(owned.link).toMatchObject({ role: 'owner', gift: true, numberShown: true });
    expect(owned.package.tracking_number).toBe(trackingNumber);
    expect(owned.package.carrier_data.sender_name).toBe('Example Shop');
    expect(owned.package.tracking_events).toHaveLength(3);
  });

  it('says a link can take alerts, and with which key, when Web Push is configured', async () => {
    const keys = webpush.generateVAPIDKeys();
    vi.stubEnv('VAPID_PUBLIC_KEY', keys.publicKey);
    vi.stubEnv('VAPID_PRIVATE_KEY', keys.privateKey);
    find();
    const text = await (await open()).text();
    expect(JSON.parse(text).link.alerts).toEqual({ available: true, vapidPublicKey: keys.publicKey, email: false });
    expect(text).not.toContain(keys.privateKey);
  });

  it('says that accounts are emailed on delivery when the server has mail settings', async () => {
    vi.stubEnv('SMTP_HOST', 'smtp.example.com');
    vi.stubEnv('SMTP_USER', 'mailer');
    vi.stubEnv('SMTP_PASSWORD', 'test-smtp-pass');
    vi.stubEnv('EMAIL_FROM', 'Peek <hello@example.com>');
    vi.stubEnv('CANONICAL_ORIGIN', 'https://delivery.example');
    find();
    const text = await (await open()).text();
    expect(JSON.parse(text).link.alerts).toEqual({ available: false, vapidPublicKey: null, email: true });
    // The answer says that mail is on, never how it is sent.
    expect(text).not.toMatch(/smtp|mailer|hello@example\.com/);
  });

  it('queues a check of a one-off parcel that is due one, and only then', async () => {
    vi.useFakeTimers({ toFake: ['Date'], now: new Date('2026-10-02T10:35:00Z') });
    find({}, { last_synced_at: '2026-10-02T09:50:00+00:00' });
    expect((await open()).status).toBe(200);
    expect(enqueue).toHaveBeenCalledExactlyOnceWith({ packageId, reason: 'opened' });
    expect(wake).toHaveBeenCalledOnce();

    // Checked a minute ago, delivered, or owned by an account: reading queues nothing.
    for (const parcel of [
      { last_synced_at: '2026-10-02T10:34:00+00:00' },
      { last_synced_at: '2026-10-02T09:50:00+00:00', current_stage: 'delivered' },
      { last_synced_at: '2026-10-02T09:50:00+00:00', one_off: false, user_id: '51000000-0000-4000-a000-000000000005' },
    ]) {
      vi.restoreAllMocks();
      vi.spyOn(console, 'log').mockImplementation(() => undefined);
      const queued = vi.spyOn(SupabaseServiceClient.prototype, 'enqueueSyncJob');
      find({}, parcel);
      expect((await open()).status).toBe(200);
      expect(queued).not.toHaveBeenCalled();
    }
  });

  it('still shows the parcel when the check cannot be queued', async () => {
    vi.useFakeTimers({ toFake: ['Date'], now: new Date('2026-10-02T10:35:00Z') });
    find({}, { last_synced_at: null });
    enqueue.mockRejectedValue(new SupabaseError('queue unavailable', 503));
    expect((await open()).status).toBe(200);
  });

  it('keeps the link out of request logs and error reports', async () => {
    const logged = vi.mocked(console.log);
    vi.spyOn(SupabaseServiceClient.prototype, 'publicParcel').mockRejectedValue(new SupabaseError('database down', 503));
    const report = vi.spyOn(observability, 'captureOperationalError').mockReturnValue(null);
    const failed = await open(linkId, ownerKey);
    expect(failed.status).toBe(502);
    expect(report).toHaveBeenCalledExactlyOnceWith(expect.any(SupabaseError), expect.objectContaining({
      route: '/api/public/parcels/:link', withoutRequest: true,
    }));
    const lines = [...logged.mock.calls, ...vi.mocked(console.error).mock.calls].map(([line]) => String(line));
    expect(lines.some((line) => line.includes('"route":"/api/public/parcels/:link"'))).toBe(true);
    for (const line of lines) {
      expect(line).not.toContain(linkId);
      expect(line).not.toContain(ownerKey);
      expect(line).not.toContain(keyHash);
    }
  });

  it('limits reads per client across every link', async () => {
    const found = vi.spyOn(SupabaseServiceClient.prototype, 'publicParcel').mockResolvedValue(null);
    for (let index = 0; index < 120; index += 1) {
      expect((await open(index % 2 ? linkId : 'unknownLink2', undefined, '198.51.100.20')).status).toBe(404);
    }
    const refused = await open('anotherLink3', undefined, '198.51.100.20');
    expect(refused.status).toBe(429);
    expect(refused.headers.get('retry-after')).toBeTruthy();
    expect(found).toHaveBeenCalledTimes(120);
    expect((await open(linkId, undefined, '198.51.100.21')).status).toBe(404);
  });
});

describe('the postcode another carrier asks for', () => {
  // Synthetic: a twelve-digit number of the shape DHL asks a postcode for.
  const heldNumber = '990000000001';
  const heldPostcode = '99999';
  const filedId = '51000000-0000-4000-a000-000000000011';
  const asking = { input_needed: { carrier: 'dhl', field: 'dpdPostcode' } };
  const find = (link: JsonObject, parcel: JsonObject = {}) => vi.spyOn(SupabaseServiceClient.prototype, 'publicParcel')
    .mockResolvedValue({ link: storedLink(link), package: storedPackage({ carrier: 'unknown', carrier_data: { routing: asking }, ...parcel }) });
  const claim = (answer: Awaited<ReturnType<SupabaseServiceClient['claimParcelLinkPostcode']>> = { attempt: 'try-1', trackingNumber: heldNumber }) =>
    vi.spyOn(SupabaseServiceClient.prototype, 'claimParcelLinkPostcode').mockResolvedValue(answer);
  const check = (answer: linkPostcodes.PostcodeCheck) => vi.spyOn(linkPostcodes, 'checkLinkPostcode').mockResolvedValue(answer);
  const file = () => vi.spyOn(SupabaseServiceClient.prototype, 'fileParcelLinkPostcode').mockResolvedValue({
    link: storedLink({ owner: true }),
    package: storedPackage({
      id: filedId, tracking_number: heldNumber, carrier: 'dhl', dpd_postcode: heldPostcode, sync_status: 'pending',
      last_synced_at: null, carrier_data: {}, tracking_events: [],
    }),
    created: true,
  });
  let release: ReturnType<typeof vi.spyOn>;
  beforeEach(() => {
    release = vi.spyOn(SupabaseServiceClient.prototype, 'releaseParcelLinkPostcode').mockResolvedValue(undefined);
  });
  /** `key` null sends no owner key. */
  const add = (body: unknown, id = linkId, key: string | null = ownerKey, ip = nextIp()) => postcodeAdded(
    new NextRequest(`https://delivery.example/api/public/parcels/${id}/postcode`, {
      method: 'POST',
      headers: { 'content-type': 'application/json', 'x-real-ip': ip, ...(key === null ? {} : { 'x-parcel-key': key }) },
      body: JSON.stringify(body),
    }), route(id));
  const outcomes = () => vi.mocked(console.log).mock.calls.map(([line]) => JSON.parse(String(line)))
    .filter((line) => line.event === 'parcel_link_postcode').map((line) => line.outcome);

  it('tells the owner of a lookup, and no one else, which carrier asks for the postcode', async () => {
    find({ owner: true });
    expect((await (await open(linkId, ownerKey)).json()).link.inputNeeded).toEqual({ carrier: 'dhl', field: 'dpdPostcode' });
    find({});
    const viewer = await (await open()).text();
    expect(viewer).not.toContain('inputNeeded');
    expect(viewer).not.toContain('dhl');
    // Its own carrier asks too while the lookup has no postcode.
    find({ owner: true }, { carrier: 'dhl', dpd_postcode: null });
    expect((await (await open(linkId, ownerKey)).json()).link.inputNeeded).toEqual({ carrier: 'dhl', field: 'dpdPostcode' });
    // Not once the parcel is filed under that carrier with a postcode, nor once it is over, nor for another input.
    for (const parcel of [{ carrier: 'dhl' }, { current_stage: 'delivered' }, { current_stage: 'returned' },
      { carrier_data: { routing: { input_needed: { carrier: 'dhl', field: 'trackingUrl' } } } }]) {
      find({ owner: true }, parcel);
      expect((await (await open(linkId, ownerKey)).json()).link).not.toHaveProperty('inputNeeded');
    }
  });

  it('asks the carrier first, then files the lookup under it and checks it at once', async () => {
    const claimed = claim();
    const checked = check({ outcome: 'accepted' });
    const filed = file();
    const response = await add({ carrier: 'dhl', postcode: ' 99999 ' });
    expect(response.status).toBe(200);
    expect(claimed).toHaveBeenCalledExactlyOnceWith(linkId, keyHash, 'dhl', linkPostcodes.LINK_POSTCODE_TRIES);
    expect(checked).toHaveBeenCalledExactlyOnceWith('dhl', heldNumber, heldPostcode, null, expect.any(AbortSignal));
    expect(filed).toHaveBeenCalledExactlyOnceWith(linkId, keyHash, 'dhl', heldPostcode);
    expect(checked.mock.invocationCallOrder[0]).toBeLessThan(filed.mock.invocationCallOrder[0]!);
    const answer = await response.json();
    expect(answer.link).toMatchObject({ id: linkId, role: 'owner' });
    expect(answer.link).not.toHaveProperty('inputNeeded');
    expect(answer.package).toMatchObject({ id: filedId, carrier: 'dhl', dpd_postcode: null, tracking_events: [] });
    expect(enqueue).toHaveBeenCalledExactlyOnceWith({ packageId: filedId, reason: 'added' });
    expect(wake).toHaveBeenCalledOnce();
    // Filing clears the link's tries: none is given back on top.
    expect(release).not.toHaveBeenCalled();
    expect(outcomes()).toEqual(['accepted']);
  });

  it('asks the carrier with the country of a postcode from abroad, and keeps it with the parcel it files', async () => {
    const postnl = '3SABCD826405193';
    claim({ attempt: 'try-1', trackingNumber: postnl });
    const checked = check({ outcome: 'accepted' });
    const filed = vi.spyOn(SupabaseServiceClient.prototype, 'fileParcelLinkPostcode').mockResolvedValue({
      link: storedLink({ owner: true }),
      package: storedPackage({
        id: filedId, tracking_number: postnl, carrier: 'spring-gds', dpd_postcode: '1000', sync_status: 'pending',
        last_synced_at: null, carrier_data: {}, tracking_events: [],
      }),
      created: true,
    });
    const update = vi.mocked(SupabaseServiceClient.prototype.updatePackage);
    const response = await add({ carrier: 'spring-gds', postcode: '1000', postcodeCountry: 'BE' });
    expect(response.status).toBe(200);
    expect(checked).toHaveBeenCalledExactlyOnceWith('spring-gds', postnl, '1000', 'BE', expect.any(AbortSignal));
    expect(filed).toHaveBeenCalledExactlyOnceWith(linkId, keyHash, 'spring-gds', '1000');
    expect(update).toHaveBeenCalledExactlyOnceWith(filedId, {
      carrier_data: { postcode_country: { postcode: '1000', country: 'BE' } }, dpd_postcode: '1000',
    });
    expect(update.mock.invocationCallOrder[0]).toBeLessThan(enqueue.mock.invocationCallOrder[0]!);
    expect(await response.text()).not.toContain('postcode_country');
  });

  it('refuses a postcode country that is not one, or not text, before asking the carrier', async () => {
    claim();
    const checked = check({ outcome: 'accepted' });
    expect((await add({ carrier: 'dhl', postcode: heldPostcode, postcodeCountry: 'ZZ' })).status).toBe(400);
    expect((await add({ carrier: 'dhl', postcode: heldPostcode, postcodeCountry: 49 })).status).toBe(400);
    expect(checked).not.toHaveBeenCalled();
  });

  it('shows nothing for a postcode the carrier refuses, and keeps the try counted', async () => {
    claim();
    check({ outcome: 'refused' });
    const filed = file();
    const refused = await add({ carrier: 'dhl', postcode: heldPostcode });
    expect(refused.status).toBe(422);
    expect(Object.keys(await refused.json())).toEqual(['error']);
    expect(filed).not.toHaveBeenCalled();
    expect(enqueue).not.toHaveBeenCalled();
    expect(release).not.toHaveBeenCalled();

    // After several wrong ones the carrier takes none for a while: the owner is told when to try again.
    check({ outcome: 'refused', retryAfterMs: 90_000 });
    const blocked = await add({ carrier: 'dhl', postcode: heldPostcode });
    expect(blocked.status).toBe(429);
    expect(blocked.headers.get('retry-after')).toBe('90');
    expect(release).not.toHaveBeenCalled();
    expect(outcomes()).toEqual(['refused', 'refused']);
  });

  it('refuses a link out of tries without asking the carrier', async () => {
    vi.useFakeTimers({ now: Date.parse('2026-10-24T08:00:00Z'), toFake: ['Date'] });
    claim({ retryAt: '2026-10-24T09:30:00.000Z' });
    const checked = check({ outcome: 'accepted' });
    const response = await add({ carrier: 'dhl', postcode: heldPostcode });
    expect(response.status).toBe(429);
    expect(response.headers.get('retry-after')).toBe('5400');
    expect(checked).not.toHaveBeenCalled();
    expect(release).not.toHaveBeenCalled();
    expect(outcomes()).toEqual(['limited']);
  });

  it('gives the try back when the carrier cannot be asked or the postcode cannot be one', async () => {
    claim();
    const checked = check({ outcome: 'unavailable' });
    const filed = file();
    expect((await add({ carrier: 'dhl', postcode: heldPostcode })).status).toBe(503);
    expect(release).toHaveBeenCalledExactlyOnceWith('try-1');
    expect(filed).not.toHaveBeenCalled();

    expect((await add({ carrier: 'dhl', postcode: '12' })).status).toBe(400);
    expect(release).toHaveBeenCalledTimes(2);
    expect(checked).toHaveBeenCalledOnce();

    checked.mockRejectedValue(new Error('carrier crashed'));
    vi.spyOn(observability, 'captureOperationalError').mockReturnValue(null);
    expect((await add({ carrier: 'dhl', postcode: heldPostcode })).status).toBe(500);
    expect(release).toHaveBeenCalledTimes(3);
  });

  it('answers an unknown link, a wrong key and a missing key alike, and a lookup that asks for nothing apart', async () => {
    const claimed = claim(null);
    const checked = check({ outcome: 'accepted' });
    for (const [id, key] of [[linkId, ownerKey], ['unknownLink2', ownerKey], [linkId, 'not-a-key'], [linkId, null], ['bad', ownerKey]] as const) {
      const response = await add({ carrier: 'dhl', postcode: heldPostcode }, id, key);
      expect(response.status).toBe(404);
      expect(await response.json()).toEqual({ error: 'Parcel unavailable' });
    }
    expect(claimed).toHaveBeenCalledTimes(2);
    claim('not_asked');
    expect((await add({ carrier: 'not a carrier we asked', postcode: heldPostcode })).status).toBe(409);
    expect(checked).not.toHaveBeenCalled();
    expect((await add({ carrier: 'dhl' })).status).toBe(400);
    // A carrier the link did not ask for is the sender's own text: it stays out of the log.
    const lines = vi.mocked(console.log).mock.calls.map(([line]) => JSON.parse(String(line)))
      .filter((line) => line.event === 'parcel_link_postcode');
    expect(lines.map((line) => [line.outcome, line.carrier])).toEqual([...Array(5).fill(['unknown', undefined]), ['not_asked', undefined]]);
  });

  it('never logs the link, the key, the number or the postcode', async () => {
    claim();
    check({ outcome: 'refused' });
    await add({ carrier: 'dhl', postcode: heldPostcode });
    const lines = [...vi.mocked(console.log).mock.calls, ...vi.mocked(console.error).mock.calls].map(([line]) => String(line));
    expect(lines.some((line) => line.includes('parcel_link_postcode'))).toBe(true);
    for (const line of lines) {
      for (const secret of [linkId, ownerKey, keyHash, heldNumber, heldPostcode]) expect(line).not.toContain(secret);
    }
  });

  it('limits postcodes per client across every link', async () => {
    const claimed = claim(null);
    for (let index = 0; index < 10; index += 1) {
      expect((await add({ carrier: 'dhl', postcode: heldPostcode }, index % 2 ? linkId : 'unknownLink2', ownerKey, '198.51.100.40')).status).toBe(404);
    }
    const refused = await add({ carrier: 'dhl', postcode: heldPostcode }, 'anotherLink3', ownerKey, '198.51.100.40');
    expect(refused.status).toBe(429);
    expect(refused.headers.get('retry-after')).toBeTruthy();
    expect(claimed).toHaveBeenCalledTimes(10);
  });
});

describe('forgetting a lookup', () => {
  it('forgets the link, and its parcel with it, for the right key', async () => {
    const forgotten = vi.spyOn(SupabaseServiceClient.prototype, 'forgetParcelLink').mockResolvedValue({ links: 1, packages: 1 });
    const counted = vi.spyOn(metrics, 'recordParcelsForgotten');
    const response = await remove(linkId, ownerKey);
    expect(response.status).toBe(204);
    expect(await response.text()).toBe('');
    expect(response.headers.get('cache-control')).toBe('no-store');
    expect(forgotten).toHaveBeenCalledExactlyOnceWith(linkId, keyHash);
    expect(counted).toHaveBeenCalledExactlyOnceWith('asked', { links: 1, packages: 1 });
  });

  it('answers an unknown link, a wrong key and a missing key alike', async () => {
    const forgotten = vi.spyOn(SupabaseServiceClient.prototype, 'forgetParcelLink').mockResolvedValue({ links: 0, packages: 0 });
    const counted = vi.spyOn(metrics, 'recordParcelsForgotten');
    const answers = [
      await remove('unknownLink2', ownerKey),
      await remove(linkId, 'B'.repeat(43)),
      await remove(linkId),
      await remove(linkId, 'not-a-key'),
      await remove('not-a-link-id!', ownerKey),
    ];
    for (const response of answers) {
      expect(response.status).toBe(404);
      expect(await response.json()).toEqual({ error: 'Parcel unavailable' });
    }
    // Only a well-formed link and key are checked against the database.
    expect(forgotten).toHaveBeenCalledTimes(2);
    expect(counted).not.toHaveBeenCalled();
  });
});

describe('changing what a lookup shows', () => {
  const store = (transition: 'stopped' | 'started' | null = null, link: JsonObject = {}) => vi
    .spyOn(SupabaseServiceClient.prototype, 'updateParcelLink')
    .mockResolvedValue({ link: storedLink({ owner: true, ...link }), package: storedPackage(), transition });

  it('changes the link for its owner key and answers as the owner sees it', async () => {
    const stored = store(null, { gift: true, show_number: true });
    const counted = vi.spyOn(metrics, 'recordParcelShare');
    const response = await update({ showNumber: true, gift: true, label: 'never stored' });
    expect(response.status).toBe(200);
    expect(response.headers.get('cache-control')).toBe('no-store');
    expect(stored).toHaveBeenCalledExactlyOnceWith(linkId, keyHash, { showNumber: true, gift: true });
    const text = await response.text();
    expectNothingPrivate(text);
    const answer = JSON.parse(text);
    expect(answer).not.toHaveProperty('transition');
    expect(answer.link).toMatchObject({ role: 'owner', gift: true, shared: true, numberShown: true, canKeep: true });
    // The owner of a gift still sees all of it.
    expect(answer.package).toMatchObject({ tracking_number: trackingNumber, carrier_data: { sender_name: 'Example Shop' } });
    expect(counted).toHaveBeenCalledExactlyOnceWith('lookup', 'changed');
  });

  it('saves a gift message using only the owner key and returns it to the owner', async () => {
    const giftWords = { name: 'Trail shoes', note: 'Happy birthday!', from: 'Sam' };
    const stored = store(null, { gift: true, gift_words: giftWords });
    const response = await update({ giftWords: { ...giftWords, note: ' Happy\n birthday! ' } });
    expect(response.status).toBe(200);
    expect(stored).toHaveBeenCalledExactlyOnceWith(linkId, keyHash, { giftWords });
    expect((await response.json()).link.giftWords).toEqual(giftWords);
    expect(response.headers.get('cache-control')).toBe('no-store');
  });

  it.each([['stopped', false], ['started', true]] as const)('counts sharing %s', async (transition, shared) => {
    const stored = store(transition, { stopped: !shared });
    const counted = vi.spyOn(metrics, 'recordParcelShare');
    const answer = await (await update({ shared })).json();
    expect(stored).toHaveBeenCalledExactlyOnceWith(linkId, keyHash, { shared });
    expect(answer.link.shared).toBe(shared);
    expect(counted).toHaveBeenCalledExactlyOnceWith('lookup', transition);
  });

  it('answers an unknown link, a wrong key and a missing key alike', async () => {
    const stored = vi.spyOn(SupabaseServiceClient.prototype, 'updateParcelLink').mockResolvedValue(null);
    const counted = vi.spyOn(metrics, 'recordParcelShare');
    const answers = [
      await update({ gift: true }, 'unknownLink2'),
      await update({ gift: true }, linkId, 'B'.repeat(43)),
      await update({ gift: true }, linkId, null),
      await update({ gift: true }, linkId, 'not-a-key'),
      await update({ gift: true }, 'not-a-link-id!'),
    ];
    for (const response of answers) {
      expect(response.status).toBe(404);
      expect(response.headers.get('cache-control')).toBe('no-store');
      expect(await response.json()).toEqual({ error: 'Parcel unavailable' });
    }
    // Only a well-formed link and key are checked against the database.
    expect(stored).toHaveBeenCalledTimes(2);
    expect(counted).not.toHaveBeenCalled();
  });

  it.each([
    [{}], [{ label: 'Sneakers' }], [{ gift: 'yes' }], [{ showNumber: 1 }], [{ shared: null }], [{ gift: true, shared: 'no' }],
  ])('rejects %j before looking at the key or the link', async (body) => {
    const stored = store();
    for (const response of [await update(body), await update(body, 'unknownLink2', null)]) {
      expect(response.status).toBe(400);
      expect(await response.json()).toEqual({ error: expect.stringMatching(/showNumber|gift|shared/) });
    }
    expect(stored).not.toHaveBeenCalled();
  });

  it('keeps the link and its key out of request logs and error reports', async () => {
    const logged = vi.mocked(console.log);
    vi.spyOn(SupabaseServiceClient.prototype, 'updateParcelLink').mockRejectedValue(new SupabaseError('database down', 503));
    const report = vi.spyOn(observability, 'captureOperationalError').mockReturnValue(null);
    expect((await update({ shared: false })).status).toBe(502);
    expect(report).toHaveBeenCalledExactlyOnceWith(expect.any(SupabaseError), expect.objectContaining({
      route: '/api/public/parcels/:link', withoutRequest: true,
    }));
    for (const [line] of [...logged.mock.calls, ...vi.mocked(console.error).mock.calls]) {
      for (const secret of [linkId, ownerKey, keyHash]) expect(String(line)).not.toContain(secret);
    }
  });

  it('limits changes per client across every link', async () => {
    const stored = vi.spyOn(SupabaseServiceClient.prototype, 'updateParcelLink').mockResolvedValue(null);
    for (let index = 0; index < 30; index += 1) {
      expect((await update({ gift: true }, index % 2 ? linkId : 'unknownLink2', ownerKey, '198.51.100.30')).status).toBe(404);
    }
    const refused = await update({ gift: true }, 'anotherLink3', ownerKey, '198.51.100.30');
    expect(refused.status).toBe(429);
    expect(refused.headers.get('retry-after')).toBeTruthy();
    expect(stored).toHaveBeenCalledTimes(30);
  });
});

describe('alerts for a parcel link', () => {
  // A synthetic subscription: a fresh P-256 point and secret, and an endpoint no push service issued.
  const endpoint = 'https://fcm.googleapis.com/fcm/send/synthetic-link-alert';
  const exchange = createECDH('prime256v1');
  exchange.generateKeys();
  const keys = { p256dh: exchange.getPublicKey().toString('base64url'), auth: randomBytes(16).toString('base64url') };
  const body = (changes: JsonObject = {}) => ({ subscription: { endpoint, keys }, preset: 'important', locale: 'de', ...changes });
  const secrets = [endpoint, 'synthetic-link-alert', keys.p256dh, keys.auth, ownerKey, keyHash];
  const turnOn = (payload: unknown = body(), id = linkId, key?: string, ip?: string) => alertRequest(alertOn, 'PUT', payload, id, key, ip);
  const turnOff = (payload: unknown = { endpoint }, id = linkId, ip?: string) => alertRequest(alertOff, 'DELETE', payload, id, undefined, ip);
  const store = (outcome: 'added' | 'updated' | 'full' | 'finished' | 'stopped' | null = 'added') => vi
    .spyOn(SupabaseServiceClient.prototype, 'addParcelLinkAlert').mockResolvedValue(outcome);

  beforeEach(() => {
    const vapid = webpush.generateVAPIDKeys();
    vi.stubEnv('VAPID_PUBLIC_KEY', vapid.publicKey);
    vi.stubEnv('VAPID_PRIVATE_KEY', vapid.privateKey);
  });

  it('turns an alert on for anyone holding the link, without sign-in', async () => {
    const authenticate = vi.spyOn(SupabaseAuthenticator.prototype, 'validate');
    const stored = store();
    const counted = vi.spyOn(metrics, 'recordParcelAlertSet');
    const response = await turnOn();
    expect(response.status).toBe(204);
    expect(await response.text()).toBe('');
    expect(response.headers.get('cache-control')).toBe('no-store');
    expect(stored).toHaveBeenCalledExactlyOnceWith(linkId, null, {
      endpoint, p256dh: keys.p256dh, auth: keys.auth, locale: 'de', preset: 'important',
    });
    expect(counted).toHaveBeenCalledExactlyOnceWith('added', 'web');
    expect(authenticate).not.toHaveBeenCalled();
  });

  it('marks the alert of the lookup\'s owner by its key', async () => {
    const stored = store('updated');
    expect((await turnOn(body({ preset: 'delivery' }), linkId, ownerKey)).status).toBe(204);
    expect(stored).toHaveBeenCalledExactlyOnceWith(linkId, keyHash, expect.objectContaining({ preset: 'delivery' }));
    stored.mockClear();
    // A malformed key is no key: the alert is a viewer's.
    await turnOn(body(), linkId, 'not-a-key');
    expect(stored).toHaveBeenCalledExactlyOnceWith(linkId, null, expect.anything());
  });

  it('answers a journey that is over like an alert that has ended', async () => {
    store('finished');
    const counted = vi.spyOn(metrics, 'recordParcelAlertSet');
    expect((await turnOn()).status).toBe(204);
    expect(counted).toHaveBeenCalledExactlyOnceWith('finished', 'web');
  });

  it('answers an unknown and a malformed link alike, a stopped one as not shared, a full one as full', async () => {
    const stored = store(null);
    const counted = vi.spyOn(metrics, 'recordParcelAlertSet');
    for (const response of [await turnOn(body(), 'unknownLink2', ownerKey), await turnOn(body(), 'not-a-link-id!')]) {
      expect(response.status).toBe(404);
      expect(await response.json()).toEqual({ error: 'Parcel unavailable' });
    }
    // A malformed id never reaches the database.
    expect(stored).toHaveBeenCalledOnce();
    stored.mockResolvedValue('stopped');
    const stopped = await turnOn();
    expect(stopped.status).toBe(410);
    expect(await stopped.json()).toEqual({ error: 'Parcel not shared' });
    stored.mockResolvedValue('full');
    const full = await turnOn();
    expect(full.status).toBe(409);
    expect(await full.json()).toEqual({ error: expect.any(String) });
    expect(counted.mock.calls).toEqual([['unavailable', 'web'], ['unavailable', 'web'], ['stopped', 'web'], ['full', 'web']]);
  });

  it.each([
    ['no subscription', { subscription: undefined }],
    ['a subscription that is not an object', { subscription: endpoint }],
    ['an endpoint outside the known push services', { subscription: { endpoint: 'https://push.example.test/send/1', keys } }],
    ['an endpoint over plain HTTP', { subscription: { endpoint: endpoint.replace('https:', 'http:'), keys } }],
    ['an endpoint with credentials', { subscription: { endpoint: endpoint.replace('https://', 'https://user:pass@'), keys } }],
    ['an endpoint on another port', { subscription: { endpoint: endpoint.replace('.com/', '.com:8443/'), keys } }],
    ['no keys', { subscription: { endpoint } }],
    ['a key of the wrong length', { subscription: { endpoint, keys: { ...keys, p256dh: keys.auth } } }],
    ['a key that is not a point of the curve', { subscription: { endpoint, keys: { ...keys, p256dh: Buffer.concat([Buffer.from([4]), Buffer.alloc(64, 1)]).toString('base64url') } } }],
    ['a secret of the wrong length', { subscription: { endpoint, keys: { ...keys, auth: 'c2hvcnQ' } } }],
    ['an unknown preset', { preset: 'everything' }],
    ['an account preset\'s name', { preset: 'delivery-day' }],
    ['no preset', { preset: undefined }],
    ['an unsupported language', { locale: 'nl' }],
    ['no language', { locale: undefined }],
  ])('rejects %s without storing anything', async (_name, changes) => {
    const stored = store();
    const response = await turnOn(body(changes));
    expect(response.status).toBe(400);
    expect(await response.json()).toEqual({ error: expect.any(String) });
    expect(stored).not.toHaveBeenCalled();
  });

  it('says so when this server cannot send Web Push', async () => {
    vi.stubEnv('VAPID_PUBLIC_KEY', '');
    vi.stubEnv('VAPID_PRIVATE_KEY', '');
    const stored = store();
    const response = await turnOn();
    expect(response.status).toBe(503);
    expect(await response.json()).toEqual({ error: 'Push notifications are not configured' });
    expect(stored).not.toHaveBeenCalled();
    // An invalid request is still told so first.
    expect((await turnOn(body({ preset: 'everything' }))).status).toBe(400);
  });

  it('turns an alert off for whoever knows its endpoint, and answers the same when there was none', async () => {
    const removed = vi.spyOn(SupabaseServiceClient.prototype, 'removeParcelLinkAlert').mockResolvedValueOnce(true).mockResolvedValue(false);
    const counted = vi.spyOn(metrics, 'recordParcelAlertRemoved');
    for (const response of [await turnOff(), await turnOff(), await turnOff({ endpoint }, 'unknownLink2'), await turnOff({ endpoint }, 'not-a-link-id!')]) {
      expect(response.status).toBe(204);
      expect(await response.text()).toBe('');
      expect(response.headers.get('cache-control')).toBe('no-store');
    }
    expect(removed.mock.calls).toEqual([[linkId, endpoint], [linkId, endpoint], ['unknownLink2', endpoint]]);
    expect(counted).toHaveBeenCalledExactlyOnceWith('asked');
    for (const invalid of [{}, { endpoint: 'https://push.example.test/send/1' }, { endpoint: 42 }]) {
      expect((await turnOff(invalid)).status).toBe(400);
    }
    expect(removed).toHaveBeenCalledTimes(3);
  });

  it('never returns or logs the endpoint, its keys, the link or the owner key', async () => {
    const logged = vi.mocked(console.log);
    const report = vi.spyOn(observability, 'captureOperationalError').mockReturnValue(null);
    store();
    vi.spyOn(SupabaseServiceClient.prototype, 'removeParcelLinkAlert').mockResolvedValue(true);
    const answers = [await turnOn(body(), linkId, ownerKey), await turnOff()];
    vi.spyOn(SupabaseServiceClient.prototype, 'addParcelLinkAlert').mockRejectedValue(new SupabaseError('database down', 503));
    vi.spyOn(SupabaseServiceClient.prototype, 'removeParcelLinkAlert').mockRejectedValue(new SupabaseError('database down', 503));
    answers.push(await turnOn(body(), linkId, ownerKey), await turnOff(), await turnOn(body({ preset: 'everything' })));
    expect(answers.map((response) => response.status)).toEqual([204, 204, 502, 502, 400]);
    const lines = [...logged.mock.calls, ...vi.mocked(console.error).mock.calls].map(([line]) => String(line));
    expect(lines.filter((line) => line.includes('"route":"/api/public/parcels/:link/alerts"'))).toHaveLength(5);
    for (const text of [...lines, ...await Promise.all(answers.map((response) => response.text()))]) {
      for (const secret of [...secrets, linkId]) expect(text).not.toContain(secret);
    }
    expect(report).toHaveBeenCalledTimes(2);
    for (const [, context] of report.mock.calls) {
      expect(context).toMatchObject({ route: '/api/public/parcels/:link/alerts', withoutRequest: true });
    }
  });

  it('limits alert requests per client across every link', async () => {
    const stored = store(null);
    for (let index = 0; index < 20; index += 1) {
      expect((await turnOn(body(), index % 2 ? linkId : 'unknownLink2', undefined, '198.51.100.40')).status).toBe(404);
    }
    const refused = await turnOff({ endpoint }, linkId, '198.51.100.40');
    expect(refused.status).toBe(429);
    expect(refused.headers.get('retry-after')).toBeTruthy();
    expect(stored).toHaveBeenCalledTimes(20);
    expect((await turnOn(body(), linkId, undefined, '198.51.100.41')).status).toBe(404);
  });
});

describe('alerts for a parcel link in the iPhone app', () => {
  // A synthetic device token: APNs never issued it.
  const token = 'ab'.repeat(32);
  const body = (changes: JsonObject = {}) => ({ token, environment: 'production', preset: 'important', locale: 'fr', ...changes });
  const request = (handler: typeof appAlertOn, method: string, payload: unknown, id: string, key?: string, ip = nextIp()) =>
    handler(new NextRequest(`https://delivery.example/api/public/parcels/${id}/app-alerts`, {
      method,
      headers: { 'content-type': 'application/json', 'x-real-ip': ip, ...(key === undefined ? {} : { 'x-parcel-key': key }) },
      body: JSON.stringify(payload),
    }), route(id));
  const turnOn = (payload: unknown = body(), id = linkId, key?: string) => request(appAlertOn, 'PUT', payload, id, key);
  const turnOff = (payload: unknown = { token }, id = linkId) => request(appAlertOff, 'DELETE', payload, id);
  const store = (outcome: 'added' | 'updated' | 'full' | 'finished' | 'stopped' | null = 'added') => vi
    .spyOn(SupabaseServiceClient.prototype, 'addParcelLinkAppAlert').mockResolvedValue(outcome);

  beforeEach(() => {
    vi.stubEnv('APNS_TEAM_ID', 'TEAM000000');
    vi.stubEnv('APNS_KEY_ID', 'KEY0000000');
    vi.stubEnv('APNS_PRIVATE_KEY', generateKeyPairSync('ec', { namedCurve: 'prime256v1' }).privateKey
      .export({ type: 'pkcs8', format: 'pem' }).toString());
    vi.stubEnv('APNS_BUNDLE_ID', 'com.example.peek');
  });

  it('turns an alert on for anyone holding the link, without sign-in, and marks the owner\'s by its key', async () => {
    const authenticate = vi.spyOn(SupabaseAuthenticator.prototype, 'validate');
    const stored = store();
    const counted = vi.spyOn(metrics, 'recordParcelAlertSet');
    const response = await turnOn(body({ token: token.toUpperCase() }));
    expect(response.status).toBe(204);
    expect(response.headers.get('cache-control')).toBe('no-store');
    expect(stored).toHaveBeenCalledExactlyOnceWith(linkId, null, { token, environment: 'production', locale: 'fr', preset: 'important' });
    expect(counted).toHaveBeenCalledExactlyOnceWith('added', 'ios');
    expect(authenticate).not.toHaveBeenCalled();
    stored.mockClear();
    expect((await turnOn(body({ environment: 'development' }), linkId, ownerKey)).status).toBe(204);
    expect(stored).toHaveBeenCalledExactlyOnceWith(linkId, keyHash, expect.objectContaining({ environment: 'development' }));
  });

  it('answers as a browser\'s alert does: unknown, stopped, full, and a journey that is over', async () => {
    const stored = store(null);
    const counted = vi.spyOn(metrics, 'recordParcelAlertSet');
    expect((await turnOn(body(), 'unknownLink2')).status).toBe(404);
    expect((await turnOn(body(), 'not-a-link-id!')).status).toBe(404);
    expect(stored).toHaveBeenCalledOnce();
    stored.mockResolvedValue('stopped');
    expect((await turnOn()).status).toBe(410);
    stored.mockResolvedValue('full');
    expect((await turnOn()).status).toBe(409);
    stored.mockResolvedValue('finished');
    expect((await turnOn()).status).toBe(204);
    expect(counted.mock.calls).toEqual([['unavailable', 'ios'], ['unavailable', 'ios'], ['stopped', 'ios'], ['full', 'ios'], ['finished', 'ios']]);
  });

  it.each([
    ['no token', { token: undefined }],
    ['a token that is not hexadecimal', { token: 'zz'.repeat(32) }],
    ['a token too short', { token: 'ab'.repeat(8) }],
    ['an unknown environment', { environment: 'staging' }],
    ['an unknown preset', { preset: 'everything' }],
    ['an unsupported language', { locale: 'nl' }],
  ])('rejects %s without storing anything', async (_name, changes) => {
    const stored = store();
    expect((await turnOn(body(changes))).status).toBe(400);
    expect(stored).not.toHaveBeenCalled();
  });

  it('says so when this server cannot send to iPhones', async () => {
    vi.stubEnv('APNS_TEAM_ID', '');
    vi.stubEnv('APNS_KEY_ID', '');
    vi.stubEnv('APNS_PRIVATE_KEY', '');
    vi.stubEnv('APNS_BUNDLE_ID', '');
    const stored = store();
    const response = await turnOn();
    expect(response.status).toBe(503);
    expect(await response.json()).toEqual({ error: 'APNs is not configured' });
    expect(stored).not.toHaveBeenCalled();
  });

  it('turns an alert off for whoever knows its token, and never returns or logs the token or the key', async () => {
    const removed = vi.spyOn(SupabaseServiceClient.prototype, 'removeParcelLinkAppAlert').mockResolvedValueOnce(true).mockResolvedValue(false);
    const counted = vi.spyOn(metrics, 'recordParcelAlertRemoved');
    store();
    const answers = [await turnOff(), await turnOff(), await turnOff({ token }, 'not-a-link-id!'), await turnOn(body(), linkId, ownerKey)];
    expect(answers.map((response) => response.status)).toEqual([204, 204, 204, 204]);
    expect(removed.mock.calls).toEqual([[linkId, token], [linkId, token]]);
    expect(counted).toHaveBeenCalledExactlyOnceWith('asked');
    expect((await turnOff({ token: 'not-a-token' })).status).toBe(400);
    const lines = [...vi.mocked(console.log).mock.calls, ...vi.mocked(console.error).mock.calls].map(([line]) => String(line));
    expect(lines.filter((line) => line.includes('"route":"/api/public/parcels/:link/app-alerts"')).length).toBeGreaterThan(0);
    for (const text of [...lines, ...await Promise.all(answers.map((response) => response.text()))]) {
      for (const secret of [token, ownerKey, keyHash, linkId]) expect(text).not.toContain(secret);
    }
  });
});
