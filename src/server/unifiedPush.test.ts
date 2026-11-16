import { createDecipheriv, createECDH, hkdfSync, randomBytes, type ECDH } from 'node:crypto';
import { afterEach, beforeEach, describe, expect, it, vi, type MockInstance } from 'vitest';
import webpush from 'web-push';
import * as metrics from './metrics';
import * as publicAddress from './publicAddress';
import {
  boundedAppData,
  fcmData,
  InboxPushNotificationService,
  ParcelLinkAlertService,
  UnifiedPushError,
  UnifiedPushNotificationService,
  webPushTopic,
} from './push';
import { SupabaseServiceClient } from './supabase';
import type { JsonObject } from './types';

// Synthetic identifiers only: no distributor issued this endpoint or installation token.
const endpoint = 'https://push.example.com/up/synthetic-endpoint?id=1';
const token = `${'Up_-'.repeat(10)}abc`;
const vapid = webpush.generateVAPIDKeys();
const now = Date.parse('2026-10-02T12:40:00Z');

/** The receiving phone: its key pair and auth secret, as a UnifiedPush distributor hands them to the app. */
function receiver(): { ecdh: ECDH; auth: Buffer; keys: { p256dh: string; auth: string } } {
  const ecdh = createECDH('prime256v1');
  ecdh.generateKeys();
  const auth = randomBytes(16);
  return { ecdh, auth, keys: { p256dh: ecdh.getPublicKey().toString('base64url'), auth: auth.toString('base64url') } };
}

/** Opens an aes128gcm Web Push message (RFC 8291) of one record, as the phone does. */
function open(body: Buffer, ecdh: ECDH, auth: Buffer): string {
  const salt = body.subarray(0, 16);
  const keyLength = body[20]!;
  const sender = body.subarray(21, 21 + keyLength);
  const record = body.subarray(21 + keyLength);
  const info = Buffer.concat([Buffer.from('WebPush: info\0'), ecdh.getPublicKey(), sender]);
  const ikm = Buffer.from(hkdfSync('sha256', ecdh.computeSecret(sender), auth, info, 32));
  const key = Buffer.from(hkdfSync('sha256', ikm, salt, Buffer.from('Content-Encoding: aes128gcm\0'), 16));
  const nonce = Buffer.from(hkdfSync('sha256', ikm, salt, Buffer.from('Content-Encoding: nonce\0'), 12));
  const decipher = createDecipheriv('aes-128-gcm', key, nonce);
  decipher.setAuthTag(record.subarray(-16));
  const plain = Buffer.concat([decipher.update(record.subarray(0, -16)), decipher.final()]);
  return plain.subarray(0, plain.lastIndexOf(2)).toString('utf8');
}

let client: SupabaseServiceClient;
beforeEach(() => {
  client = new SupabaseServiceClient('https://database.example', 'test');
  vi.spyOn(console, 'log').mockImplementation(() => undefined);
  vi.spyOn(console, 'error').mockImplementation(() => undefined);
});
afterEach(() => { vi.restoreAllMocks(); });

describe('a UnifiedPush message', () => {
  const phone = receiver();
  const device = { token, endpoint, ...phone.keys, locale: 'fr', package_id: 'package-1' };
  const service = () => new UnifiedPushNotificationService(client, vapid.publicKey, vapid.privateKey, 'https://delivery.example', () => now / 1_000);
  let post: MockInstance<typeof publicAddress.postToPublicEndpoint>;
  beforeEach(() => { post = vi.spyOn(publicAddress, 'postToPublicEndpoint').mockResolvedValue(201); });

  it('carries FCM\'s data as JSON, encrypted for the phone, signed by the server, kept a day at high urgency', async () => {
    const payload = { aps: { alert: { title: 'Colis', body: 'En route' } }, kind: 'parcel_update', parcel_id: 'package-1' };
    await service().send(device, payload, 'link-k7Qm2xHd9RtW');
    expect(post).toHaveBeenCalledOnce();
    const [url, options] = post.mock.calls[0]!;
    expect(url).toBe(endpoint);
    expect(options.headers).toMatchObject({
      TTL: 86_400, Urgency: 'high', 'Content-Encoding': 'aes128gcm', Topic: webPushTopic('link-k7Qm2xHd9RtW'),
    });
    expect(String(options.headers.Authorization)).toMatch(/^vapid t=[\w-]+\.[\w-]+\.[\w-]+, k=[\w-]+$/);
    expect(options.timeoutMs).toBeLessThanOrEqual(15_000);
    expect(JSON.parse(open(options.body!, phone.ecdh, phone.auth))).toEqual(fcmData(payload));
  });

  it('is topped by a later one about the same parcel, whose Topic stays within 32 URL-safe characters', () => {
    const topic = webPushTopic('package-1')!;
    expect(topic).toMatch(/^[A-Za-z0-9_-]{32}$/);
    expect(webPushTopic('package-1')).toBe(topic);
    expect(webPushTopic('x'.repeat(64))).toMatch(/^[A-Za-z0-9_-]{32}$/);
    expect(webPushTopic('package-2')).not.toBe(topic);
    expect(webPushTopic('')).toBeUndefined();
  });

  it('fits the 4096 bytes a push service takes, shortening the words of a notice too long', async () => {
    const payload = { aps: { alert: { title: 'T'.repeat(5_000), body: 'é'.repeat(5_000) } }, kind: 'parcel_update', scan_at: '2026-10-02T12:30:00Z' };
    await service().send(device, payload);
    const body = post.mock.calls[0]![1].body!;
    expect(body.byteLength).toBeLessThanOrEqual(4_096);
    const data = JSON.parse(open(body, phone.ecdh, phone.auth)) as Record<string, string>;
    expect(data.kind).toBe('parcel_update');
    // The device's own clock fields go first.
    expect(data).not.toHaveProperty('scan_at');
    expect(data.body!.endsWith('…')).toBe(true);
  });

  it('leaves data that fits as it is, and refuses what cannot fit', () => {
    expect(boundedAppData({ title: 'a', body: 'b', scan_at: 'c' })).toEqual({ title: 'a', body: 'b', scan_at: 'c' });
    expect(() => boundedAppData({ title: 'a', body: 'b', link: 'x'.repeat(5_000) })).toThrow(RangeError);
  });

  it.each([404, 410])('says the endpoint is gone at HTTP %i', async (status) => {
    post.mockResolvedValue(status);
    const sender = service();
    const error = await sender.send(device).catch((caught: unknown) => caught);
    expect(error).toBeInstanceOf(UnifiedPushError);
    expect(sender.isExpired(error)).toBe(true);
  });

  it.each([[413, 'payload_too_large'], [429, ''], [500, ''], [503, ''], [400, ''], [301, '']])(
    'keeps the endpoint at HTTP %i', async (status, reason) => {
      post.mockResolvedValue(status);
      const sender = service();
      const error = await sender.send(device).catch((caught: unknown) => caught);
      expect(error).toMatchObject({ name: 'UnifiedPushError', statusCode: status, reason });
      expect(sender.isExpired(error)).toBe(false);
      // The error says nothing of where it was sent.
      expect(String(error)).not.toContain('push.example.com');
    },
  );

  it('connects nowhere when the endpoint\'s name resolves into a private network', async () => {
    post.mockRestore();
    const lookup = vi.fn().mockResolvedValue([{ address: '10.1.2.3', family: 4 }]);
    const sender = new UnifiedPushNotificationService(client, vapid.publicKey, vapid.privateKey, 'https://delivery.example', () => now / 1_000, lookup);
    const error = await sender.send(device).catch((caught: unknown) => caught);
    expect(error).toBeInstanceOf(publicAddress.NonPublicAddressError);
    expect(sender.isExpired(error)).toBe(false);
    expect(lookup).toHaveBeenCalledExactlyOnceWith('push.example.com', { all: true });
  });
});

describe('the inbox', () => {
  const inbox = () => new InboxPushNotificationService(client, () => now / 1_000);

  it('stores FCM\'s data for the phone to fetch, under the collapse key, with the device it is for', async () => {
    const put = vi.spyOn(client, 'putPushInbox').mockResolvedValue();
    const payload = { aps: { alert: { title: 'Paket', body: 'Unterwegs' } }, kind: 'parcel_update', parcel_id: 'package-1' };
    await inbox().send({ token, device_id: 'device-1', package_id: 'package-1' }, payload);
    expect(put).toHaveBeenCalledExactlyOnceWith(token, 'package-1', fcmData(payload), 'device-1');
    put.mockClear();
    await inbox().send({ token, package_id: 'package-1' }, payload, 'link-k7Qm2xHd9RtW');
    expect(put).toHaveBeenCalledExactlyOnceWith(token, 'link-k7Qm2xHd9RtW', fcmData(payload), null);
  });

  it('sends a just registered device\'s test notification to its own inbox', async () => {
    const put = vi.spyOn(client, 'putPushInbox').mockResolvedValue();
    await inbox().sendTest({ id: 'device-1', token, locale: 'it' });
    expect(put).toHaveBeenCalledExactlyOnceWith(token, '', expect.objectContaining({ title: expect.any(String), body: expect.any(String) }), 'device-1');
  });

  it('never retires a device: nothing tells the server a phone is gone', () => {
    expect(inbox().isExpired()).toBe(false);
  });

  it('writes and takes the notices through the database\'s own functions', async () => {
    const request = vi.spyOn(client, 'request')
      .mockResolvedValueOnce(null)
      .mockResolvedValueOnce([{ title: 'a', body: 'b' }, { title: 'c', count: 3 }, 'not a message'])
      .mockResolvedValueOnce(7);
    await client.putPushInbox(token, 'package-1', { title: 'a' }, null);
    expect(await client.takePushInbox(token)).toEqual([{ title: 'a', body: 'b' }, { title: 'c' }]);
    expect(await client.forgetExpiredPushInbox()).toBe(7);
    expect(request.mock.calls.map(([path, options]) => [path, options?.body])).toEqual([
      ['/rest/v1/rpc/put_push_inbox', { p_token: token, p_collapse_key: 'package-1', p_data: { title: 'a' }, p_device_id: null }],
      ['/rest/v1/rpc/take_push_inbox', { p_token: token }],
      ['/rest/v1/rpc/forget_expired_push_inbox', {}],
    ]);
  });
});

describe('a parcel link\'s alert in the Android app without Google\'s services', () => {
  const linkId = 'k7Qm2xHd9RtW';
  const phone = receiver();
  const appRow = (changes: JsonObject = {}): JsonObject => ({
    alert_id: 'alert-1', link_id: linkId, endpoint: null, p256dh: null, auth: null, apns_token: null, fcm_token: null,
    app_transport: 'unifiedpush', app_token: token, app_endpoint: endpoint, app_p256dh: phone.keys.p256dh, app_auth: phone.keys.auth,
    locale: 'en', preset: 'all', owner: false, failures: 0, gift: false, event_id: 'event-1', package_id: 'package-1',
    stage: 'in_transit', location: 'Example Town', occurred_at: '2026-10-02T12:30:00Z', event_created_at: '2026-10-02T12:39:00Z',
    package_stage: 'in_transit', expected_delivery: null, expected_delivery_changed: false, event_has_time: true,
    account_endpoint: false, ...changes,
  });
  let unifiedpush: UnifiedPushNotificationService;
  let inbox: InboxPushNotificationService;
  let alerts: ParcelLinkAlertService;
  let removed: MockInstance<SupabaseServiceClient['deleteParcelLinkAlert']>;
  let failures: MockInstance<SupabaseServiceClient['setParcelLinkAlertFailures']>;

  beforeEach(() => {
    unifiedpush = new UnifiedPushNotificationService(client, vapid.publicKey, vapid.privateKey, 'https://delivery.example', () => now / 1_000);
    inbox = new InboxPushNotificationService(client, () => now / 1_000);
    alerts = new ParcelLinkAlertService(null, null, null, unifiedpush, inbox);
    vi.spyOn(client, 'recordParcelLinkAlertDeliveries').mockResolvedValue();
    removed = vi.spyOn(client, 'deleteParcelLinkAlert').mockResolvedValue();
    failures = vi.spyOn(client, 'setParcelLinkAlertFailures').mockResolvedValue();
    vi.spyOn(client, 'numberHidingParcels').mockResolvedValue(new Map());
    vi.spyOn(client, 'linkRecipientDetails').mockResolvedValue(new Map());
    vi.spyOn(client, 'latestScanTimes').mockResolvedValue(new Map());
    vi.spyOn(client, 'packageJoinTimes').mockResolvedValue(new Map());
    vi.spyOn(client, 'handoffScans').mockResolvedValue([]);
    vi.spyOn(client, 'providerCopyScans').mockResolvedValue([]);
    vi.spyOn(client, 'recentScans').mockResolvedValue(new Map());
    vi.spyOn(client, 'archivedPackageIds').mockResolvedValue(new Set());
  });

  it('goes through UnifiedPush or to the inbox by the alert\'s transport, in the words FCM carries', async () => {
    const queue = vi.spyOn(client, 'listPendingParcelLinkAlerts').mockResolvedValue([
      appRow(), appRow({ alert_id: 'alert-2', app_transport: 'inbox', app_endpoint: null, app_p256dh: null, app_auth: null }),
    ]);
    const pushed = vi.spyOn(unifiedpush, 'send').mockResolvedValue();
    const stored = vi.spyOn(client, 'putPushInbox').mockResolvedValue();
    const counted = vi.spyOn(metrics, 'recordParcelAlertSent');
    expect(alerts.now()).toBe(now);
    expect(await alerts.dispatch()).toEqual({ attempted: 2, sent: 2, failed: 0, expired: 0 });
    expect(queue).toHaveBeenCalledExactlyOnceWith({ web: false, apns: false, fcm: false, unifiedpush: true, inbox: true });
    expect(pushed).toHaveBeenCalledExactlyOnceWith(
      { token, environment: 'production', endpoint, p256dh: phone.keys.p256dh, auth: phone.keys.auth },
      alerts.appPayload(appRow()), `link-${linkId}`,
    );
    expect(stored).toHaveBeenCalledExactlyOnceWith(token, `link-${linkId}`, fcmData(alerts.appPayload(appRow())), null);
    expect(counted.mock.calls).toEqual([['sent', 'android'], ['sent', 'android']]);
  });

  it('is removed when the distributor says the endpoint is gone', async () => {
    vi.spyOn(client, 'listPendingParcelLinkAlerts').mockResolvedValue([appRow()]);
    vi.spyOn(unifiedpush, 'send').mockRejectedValue(new UnifiedPushError(410));
    expect(await alerts.dispatch()).toEqual({ attempted: 1, sent: 0, failed: 0, expired: 1 });
    expect(removed).toHaveBeenCalledExactlyOnceWith('alert-1');
  });

  it.each([
    ['the distributor limits the server', new UnifiedPushError(429)],
    ['the distributor is down', new UnifiedPushError(503)],
    ['the message is too large', new UnifiedPushError(413)],
  ])('keeps the alert and its count when %s', async (_, error) => {
    vi.spyOn(client, 'listPendingParcelLinkAlerts').mockResolvedValue([appRow({ failures: 2 })]);
    vi.spyOn(unifiedpush, 'send').mockRejectedValue(error);
    await alerts.dispatch();
    expect(removed).not.toHaveBeenCalled();
    expect(failures).not.toHaveBeenCalled();
  });

  it.each([
    ['the distributor refuses it', new UnifiedPushError(403)],
    ['the endpoint now resolves into a private network', new publicAddress.NonPublicAddressError()],
    ['the distributor cannot be reached', Object.assign(new Error('connect ECONNREFUSED'), { code: 'ECONNREFUSED' })],
  ])('counts a failed send when %s, as a browser\'s', async (_, error) => {
    vi.spyOn(client, 'listPendingParcelLinkAlerts').mockResolvedValue([appRow()]);
    vi.spyOn(unifiedpush, 'send').mockRejectedValue(error);
    await alerts.dispatch();
    expect(failures).toHaveBeenCalledExactlyOnceWith('alert-1', 1);
  });

  it('keeps an inbox alert and its count when the database cannot store the notice', async () => {
    vi.spyOn(client, 'listPendingParcelLinkAlerts').mockResolvedValue([
      appRow({ app_transport: 'inbox', app_endpoint: null, app_p256dh: null, app_auth: null, failures: 2 }),
    ]);
    vi.spyOn(client, 'putPushInbox').mockRejectedValue(new Error('database down'));
    await alerts.dispatch();
    expect(removed).not.toHaveBeenCalled();
    expect(failures).not.toHaveBeenCalled();
  });

  it('reads only the alerts of the transports this server sends on', async () => {
    const queue = vi.spyOn(client, 'listPendingParcelLinkAlerts').mockResolvedValue([]);
    await new ParcelLinkAlertService(null, null, null, null, inbox).dispatch();
    expect(queue).toHaveBeenCalledExactlyOnceWith({ web: false, apns: false, fcm: false, unifiedpush: false, inbox: true });
  });
});
