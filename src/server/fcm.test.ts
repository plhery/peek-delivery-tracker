import { generateKeyPairSync, verify } from 'node:crypto';
import { afterEach, beforeEach, describe, expect, it, vi } from 'vitest';
import { FCMAuthError, FcmClient, FCMError, fcmError, fcmTokenGone } from './fcm';
import { friendshipNotification } from './friendshipPush';
import * as metrics from './metrics';
import * as observability from './observability';
import {
  APNsError,
  CompositePushNotificationService,
  FcmPushNotificationService,
  fcmData,
  NativePushNotificationService,
  ParcelLinkAlertService,
  pushServices,
} from './push';
import { SupabaseServiceClient } from './supabase';
import type { JsonObject } from './types';

// Synthetic credentials, made for each run: Google never issued them, nor this token.
const { privateKey: rsaKey, publicKey: rsaPublicKey } = generateKeyPairSync('rsa', { modulusLength: 2048 });
const privateKey = rsaKey.export({ type: 'pkcs8', format: 'pem' }).toString();
const projectId = 'synthetic-project';
const clientEmail = 'sender@synthetic-project.example';
const deviceToken = `synthetic-token:${'Ab0_-'.repeat(30)}`;
const TOKEN_URL = 'https://oauth2.googleapis.com/token';
const SEND_URL = `https://fcm.googleapis.com/v1/projects/${projectId}/messages:send`;
const nowSeconds = Date.parse('2026-10-02T12:40:00Z') / 1_000;

const answer = (status: number, body: unknown) => new Response(JSON.stringify(body), {
  status, headers: { 'content-type': 'application/json' },
});

/** Google's two endpoints: tokens and sends answer from their queues, then succeed. */
function google({ tokens = [], sends = [] }: { tokens?: Response[]; sends?: Response[] } = {}) {
  let granted = 0;
  const requests: { url: string; init: RequestInit }[] = [];
  const fetcher = vi.fn(async (input: string | URL | Request, init: RequestInit = {}) => {
    const url = String(input);
    requests.push({ url, init });
    if (url === TOKEN_URL) return tokens.shift() ?? answer(200, { access_token: `access-${++granted}`, expires_in: 3_599, token_type: 'Bearer' });
    return sends.shift() ?? answer(200, { name: `projects/${projectId}/messages/1` });
  });
  return {
    fetcher: fetcher as unknown as typeof fetch,
    tokenRequests: () => requests.filter((request) => request.url === TOKEN_URL),
    sends: () => requests.filter((request) => request.url !== TOKEN_URL),
  };
}

const sentBody = (request: { init: RequestInit }) => JSON.parse(String(request.init.body)) as {
  message: { token: string; data: Record<string, string>; android: JsonObject; notification?: unknown };
};

function claims(assertion: string): { header: JsonObject; payload: JsonObject; verified: boolean } {
  const [header, payload, signature] = assertion.split('.');
  return {
    header: JSON.parse(Buffer.from(header!, 'base64url').toString()) as JsonObject,
    payload: JSON.parse(Buffer.from(payload!, 'base64url').toString()) as JsonObject,
    verified: verify('RSA-SHA256', Buffer.from(`${header}.${payload}`), rsaPublicKey, Buffer.from(signature!, 'base64url')),
  };
}

afterEach(() => { vi.restoreAllMocks(); vi.unstubAllEnvs(); });

describe('the service account\'s access token', () => {
  it('is asked for with an RS256 assertion for the messaging scope, which the account\'s public key verifies', async () => {
    const { fetcher, tokenRequests } = google();
    const fcm = new FcmClient(projectId, clientEmail, privateKey, () => nowSeconds, fetcher);
    expect(await fcm.accessToken()).toBe('access-1');
    const [request] = tokenRequests();
    expect(request!.init).toMatchObject({ method: 'POST', headers: { 'content-type': 'application/x-www-form-urlencoded' } });
    const form = new URLSearchParams(String(request!.init.body));
    expect(form.get('grant_type')).toBe('urn:ietf:params:oauth:grant-type:jwt-bearer');
    expect(claims(form.get('assertion')!)).toEqual({
      header: { alg: 'RS256', typ: 'JWT' },
      payload: {
        iss: clientEmail,
        scope: 'https://www.googleapis.com/auth/firebase.messaging',
        aud: TOKEN_URL,
        iat: nowSeconds,
        exp: nowSeconds + 3_600,
      },
      verified: true,
    });
  });

  it('is kept until five minutes before it expires, then asked for again', async () => {
    let now = nowSeconds;
    const { fetcher, tokenRequests } = google();
    const fcm = new FcmClient(projectId, clientEmail, privateKey, () => now, fetcher);
    expect(await fcm.accessToken()).toBe('access-1');
    now += 3_599 - 301;
    expect(await fcm.accessToken()).toBe('access-1');
    now += 1;
    expect(await fcm.accessToken()).toBe('access-2');
    expect(tokenRequests()).toHaveLength(2);
  });

  it('is asked for once by sends that need it together', async () => {
    const { fetcher, tokenRequests, sends } = google();
    const fcm = new FcmClient(projectId, clientEmail, privateKey, () => nowSeconds, fetcher);
    await Promise.all([1, 2, 3].map(() => fcm.send(deviceToken, { title: 'T' }, 'package-1')));
    expect(tokenRequests()).toHaveLength(1);
    expect(sends().map((request) => (request.init.headers as Record<string, string>).authorization))
      .toEqual(['Bearer access-1', 'Bearer access-1', 'Bearer access-1']);
  });

  it('names what Google said when it refuses, keeps nothing, and asks again next time', async () => {
    const { fetcher, tokenRequests } = google({ tokens: [answer(400, { error: 'invalid_grant', error_description: 'Invalid JWT Signature.' })] });
    const fcm = new FcmClient(projectId, clientEmail, privateKey, () => nowSeconds, fetcher);
    const refused = await fcm.accessToken().catch((error: unknown) => error);
    expect(refused).toBeInstanceOf(FCMAuthError);
    expect(refused).toMatchObject({ statusCode: 400, reason: 'invalid_grant' });
    expect(await fcm.accessToken()).toBe('access-1');
    expect(tokenRequests()).toHaveLength(2);
  });

  it('is renewed after FCM refuses it, for the next send', async () => {
    const { fetcher, tokenRequests, sends } = google({
      sends: [answer(401, { error: { code: 401, status: 'UNAUTHENTICATED', message: 'Request had invalid authentication credentials.' } })],
    });
    const fcm = new FcmClient(projectId, clientEmail, privateKey, () => nowSeconds, fetcher);
    await expect(fcm.send(deviceToken, {}, '')).rejects.toMatchObject({ statusCode: 401, reason: 'UNAUTHENTICATED' });
    await fcm.send(deviceToken, {}, '');
    expect(tokenRequests()).toHaveLength(2);
    expect((sends()[1]!.init.headers as Record<string, string>).authorization).toBe('Bearer access-2');
  });

  it('takes a key written on one line with \\n, and refuses one that is not RSA', () => {
    const oneLine = new FcmClient(projectId, clientEmail, privateKey.trim().replaceAll('\n', '\\n'));
    expect(claims(oneLine.assertion(nowSeconds)).verified).toBe(true);
    const ec = generateKeyPairSync('ec', { namedCurve: 'prime256v1' }).privateKey.export({ type: 'pkcs8', format: 'pem' }).toString();
    for (const key of [ec, 'not a key']) {
      expect(() => new FcmClient(projectId, clientEmail, key)).toThrow('FCM_PRIVATE_KEY must be an RSA private key');
    }
  });
});

describe('a message', () => {
  it('is data alone, at high priority, kept a day, and replaced by the next one with its collapse key', async () => {
    const { fetcher, sends } = google();
    const fcm = new FcmClient(projectId, clientEmail, privateKey, () => nowSeconds, fetcher);
    await fcm.send(deviceToken, { title: 'Delivered', body: 'Example Town', parcel_id: 'package-1' }, 'package-1');
    const [request] = sends();
    expect(request!.url).toBe(SEND_URL);
    expect(request!.init).toMatchObject({
      method: 'POST', headers: { authorization: 'Bearer access-1', 'content-type': 'application/json' },
    });
    expect(sentBody(request!)).toEqual({
      message: {
        token: deviceToken,
        data: { title: 'Delivered', body: 'Example Town', parcel_id: 'package-1' },
        android: { priority: 'HIGH', ttl: '86400s', collapse_key: 'package-1' },
      },
    });
  });

  it('has no collapse key when nothing names what it replaces', () => {
    const fcm = new FcmClient(projectId, clientEmail, privateKey);
    expect(fcm.message(deviceToken, {}, '').message.android).toEqual({ priority: 'HIGH', ttl: '86400s' });
  });
});

describe('what FCM\'s refusals mean', () => {
  const fcmCode = (errorCode: string) => ({ '@type': 'type.googleapis.com/google.firebase.fcm.v1.FcmError', errorCode });
  const refusal = (code: number, status: string, message: string, details: JsonObject[] = []) => ({ error: { code, status, message, details } });

  it.each([
    ['an unregistered token', 404, refusal(404, 'NOT_FOUND', 'Requested entity was not found.', [fcmCode('UNREGISTERED')]), 'UNREGISTERED', true],
    ['a token FCM never issued', 400, refusal(400, 'INVALID_ARGUMENT', 'The registration token is not a valid FCM registration token', [
      fcmCode('INVALID_ARGUMENT'),
      { '@type': 'type.googleapis.com/google.rpc.BadRequest', fieldViolations: [{ field: 'message.token', description: 'Invalid registration token' }] },
    ]), 'INVALID_ARGUMENT', true],
    ['a token named only in the message', 400, refusal(400, 'INVALID_ARGUMENT', 'The registration token is not a valid FCM registration token'), 'INVALID_ARGUMENT', true],
    ['a malformed payload', 400, refusal(400, 'INVALID_ARGUMENT', 'Invalid value at \'message.data[0].value\'', [
      fcmCode('INVALID_ARGUMENT'),
      { '@type': 'type.googleapis.com/google.rpc.BadRequest', fieldViolations: [{ field: 'message.data[0].value', description: 'Invalid value' }] },
    ]), 'INVALID_ARGUMENT', false],
    ['another project\'s token', 403, refusal(403, 'PERMISSION_DENIED', 'SenderId mismatch', [fcmCode('SENDER_ID_MISMATCH')]), 'SENDER_ID_MISMATCH', false],
    ['too many messages', 429, refusal(429, 'RESOURCE_EXHAUSTED', 'Quota exceeded.', [fcmCode('QUOTA_EXCEEDED')]), 'QUOTA_EXCEEDED', false],
    ['an outage', 503, refusal(503, 'UNAVAILABLE', 'The service is currently unavailable.', [fcmCode('UNAVAILABLE')]), 'UNAVAILABLE', false],
    ['an answer that is not FCM\'s', 502, null, '', false],
  ])('reads %s', (_name, status, body, reason, gone) => {
    const error = fcmError(status, body);
    expect(error).toMatchObject({ statusCode: status, reason });
    expect(fcmTokenGone(error)).toBe(gone);
  });

  it('retires a device for no other error', () => {
    expect(fcmTokenGone(new FCMError(404))).toBe(true);
    expect(fcmTokenGone(new FCMAuthError(401, 'invalid_grant'))).toBe(false);
    expect(fcmTokenGone(new APNsError(410, 'Unregistered'))).toBe(false);
    expect(fcmTokenGone(new Error('UNREGISTERED'))).toBe(false);
  });
});

describe('an Android phone\'s notifications', () => {
  const apnsKey = generateKeyPairSync('ec', { namedCurve: 'prime256v1' }).privateKey.export({ type: 'pkcs8', format: 'pem' }).toString();
  const scan = (changes: JsonObject = {}): JsonObject => ({
    device_id: 'device-1', subscription_id: 'subscription-1', token: deviceToken, environment: 'production', transport: 'fcm',
    locale: 'en', package_id: 'package-1', event_id: 'event-1', stage: 'delivered', location: 'Example Town',
    occurred_at: '2026-10-02T12:30:00Z', event_created_at: '2026-10-02T12:39:00Z', event_has_time: true, ...changes,
  });
  const devices = (rows: JsonObject[]) => ({
    listPendingNativePushNotifications: vi.fn().mockResolvedValue(rows),
    recordNativePushDeliveries: vi.fn().mockResolvedValue(undefined),
    updateNativePushDevice: vi.fn().mockResolvedValue(undefined),
    // An owner is never told about a parcel they put away, so the archive must answer.
    archivedPackageIds: vi.fn().mockResolvedValue(new Set()),
  });
  const android = (client: ReturnType<typeof devices>, fetcher: typeof fetch) =>
    new FcmPushNotificationService(client as never, projectId, clientEmail, privateKey, () => nowSeconds, fetcher);

  beforeEach(() => {
    vi.spyOn(console, 'log').mockImplementation(() => undefined);
  });

  it('get a parcel\'s newest scan as FCM data, in the words and fields an iPhone gets', async () => {
    const client = devices([scan(), scan({ event_id: 'event-0', stage: 'in_transit', occurred_at: '2026-10-02T09:00:00Z' })]);
    const { fetcher, sends } = google();
    const counted = vi.spyOn(metrics, 'recordAccountPushSent');
    await expect(android(client, fetcher).dispatch()).resolves.toEqual({ attempted: 1, sent: 1, failed: 0, expired: 0 });
    expect(client.listPendingNativePushNotifications).toHaveBeenCalledExactlyOnceWith('fcm');

    const iphone = new NativePushNotificationService(client as never, 'TEAM000000', 'KEY0000000', apnsKey, 'com.example.peek', () => nowSeconds);
    const { aps, ...fields } = iphone.eventPayload(scan()) as { aps: { alert: { title: string; body: string } } };
    const [request] = sends();
    const { message } = sentBody(request!);
    expect(message.data).toEqual({ title: aps.alert.title, body: aps.alert.body, ...fields });
    // A delivery carries its time for the phone's clock.
    expect(Object.keys(message.data).sort()).toEqual(['body', 'body_12_hour', 'body_other_day', 'body_same_day', 'date_locale', 'parcel_id', 'scan_at', 'title']);
    expect(Object.values(message.data).every((value) => typeof value === 'string')).toBe(true);
    expect(message.notification).toBeUndefined();
    expect(message).toMatchObject({ token: deviceToken, android: { collapse_key: 'package-1' } });

    expect(client.recordNativePushDeliveries).toHaveBeenCalledExactlyOnceWith('device-1', ['event-1', 'event-0']);
    expect(client.updateNativePushDevice).toHaveBeenCalledExactlyOnceWith('device-1', { last_success_at: expect.any(String), last_error: null });
    expect(counted).toHaveBeenCalledExactlyOnceWith('sent', 'android');
  });

  it('retire a device whose token FCM no longer takes', async () => {
    const client = devices([scan()]);
    const { fetcher } = google({ sends: [answer(404, { error: { code: 404, status: 'NOT_FOUND', details: [{ errorCode: 'UNREGISTERED' }] } })] });
    const counted = vi.spyOn(metrics, 'recordAccountPushSent');
    await expect(android(client, fetcher).dispatch()).resolves.toEqual({ attempted: 1, sent: 0, failed: 0, expired: 1 });
    expect(client.updateNativePushDevice).toHaveBeenCalledExactlyOnceWith('device-1', {
      disabled_at: expect.any(String), last_error: 'FCM device token expired',
    });
    expect(client.recordNativePushDeliveries).not.toHaveBeenCalled();
    expect(counted).toHaveBeenCalledExactlyOnceWith('expired', 'android');
  });

  it.each([
    ['FCM fails', { sends: [answer(503, { error: { code: 503, status: 'UNAVAILABLE' } })] }, { status: 503, reason: 'UNAVAILABLE', error_type: 'FCMError' }],
    ['Google refuses the service account', { tokens: [answer(400, { error: 'invalid_grant' })] }, { status: 400, reason: 'invalid_grant', error_type: 'FCMAuthError' }],
  ])('keep a device and its scan for the next dispatch when %s, and log what Google said', async (_name, answers, logged) => {
    const log = vi.spyOn(observability, 'logOperationalEvent').mockReturnValue(undefined);
    const client = devices([scan()]);
    const counted = vi.spyOn(metrics, 'recordAccountPushSent');
    await expect(android(client, google(answers).fetcher).dispatch()).resolves.toEqual({ attempted: 1, sent: 0, failed: 1, expired: 0 });
    expect(client.updateNativePushDevice).toHaveBeenCalledExactlyOnceWith('device-1', { last_error: 'FCM delivery failed' });
    expect(client.recordNativePushDeliveries).not.toHaveBeenCalled();
    expect(log).toHaveBeenCalledExactlyOnceWith('push_delivery_failed', { channel: 'native', transport: 'fcm', ...logged }, 'warning');
    expect(counted).toHaveBeenCalledExactlyOnceWith('failed', 'android');
  });

  it('get the test notification in the device\'s language, collapsed with nothing', async () => {
    const { fetcher, sends } = google();
    const client = devices([]);
    await android(client, fetcher).sendTest({ token: deviceToken, locale: 'de' });
    const { message } = sentBody(sends()[0]!);
    expect(message.data).toEqual({ title: 'Ab jetzt hörst du von Peek', body: expect.stringContaining('Dieses Gerät') });
    expect(message.android).toEqual({ priority: 'HIGH', ttl: '86400s' });
  });

  it('get a friend\'s news and a parcel link\'s alert with the keys the iPhone collapses them by', async () => {
    const { fetcher, sends } = google();
    const service = android(devices([]), fetcher);
    const friend = { device_id: 'device-1', token: deviceToken, transport: 'fcm', friend_id: 'friend-7', nickname: 'Sam', locale: 'en' };
    await service.send(friend, friendshipNotification(friend).native);
    const link = new ParcelLinkAlertService(null, null, service);
    const alert = { link_id: 'k7Qm2xHd9RtW', locale: 'en', stage: 'out_for_delivery', occurred_at: '2026-10-02T12:30:00Z', event_has_time: true };
    await service.send({ token: deviceToken }, link.appPayload(alert), 'link-k7Qm2xHd9RtW');
    const [friendMessage, linkMessage] = sends().map((request) => sentBody(request).message);
    expect(friendMessage!.data).toEqual({ title: expect.any(String), body: expect.any(String), kind: 'friend_accepted', friend_id: 'friend-7' });
    expect(friendMessage!.android.collapse_key).toBe('friend-friend-7');
    expect(linkMessage!.data).toEqual({ title: expect.any(String), body: expect.any(String), link_id: 'k7Qm2xHd9RtW' });
    expect(linkMessage!.android.collapse_key).toBe('link-k7Qm2xHd9RtW');
  });
});

describe('the iPhone\'s payload as FCM data', () => {
  it('keeps the alert\'s words and the app\'s fields, as text, and leaves out what only APNs reads', () => {
    expect(fcmData({
      aps: { alert: { title: 'Out for delivery', body: 'Example Town' }, sound: 'default', badge: 1, 'thread-id': 'package-1', 'mutable-content': 1 },
      parcel_id: 'package-1', kind: 'friend_accepted', friend_id: 7, link_id: null, scan_at: '2026-10-02T12:30:00.000Z',
    })).toEqual({
      title: 'Out for delivery', body: 'Example Town', parcel_id: 'package-1', kind: 'friend_accepted', friend_id: '7',
      scan_at: '2026-10-02T12:30:00.000Z',
    });
    expect(fcmData({})).toEqual({});
  });
});

describe('FCM on this server', () => {
  const unset = ['VAPID_PUBLIC_KEY', 'VAPID_PRIVATE_KEY', 'APNS_TEAM_ID', 'APNS_KEY_ID', 'APNS_PRIVATE_KEY', 'APNS_BUNDLE_ID',
    'FCM_PROJECT_ID', 'FCM_CLIENT_EMAIL', 'FCM_PRIVATE_KEY'];
  beforeEach(() => { for (const name of unset) vi.stubEnv(name, ''); });

  it('sends Android\'s notifications and link alerts once all three settings are there, and nothing without them', () => {
    const client = new SupabaseServiceClient('https://database.example', 'test');
    expect(pushServices(client)).toMatchObject({ fcm: null, native: null, web: null, linkAlerts: null });
    vi.stubEnv('FCM_PROJECT_ID', projectId);
    vi.stubEnv('FCM_CLIENT_EMAIL', clientEmail);
    vi.stubEnv('FCM_PRIVATE_KEY', privateKey.trim().replaceAll('\n', '\\n'));
    const push = pushServices(client);
    expect(push.fcm).toBeInstanceOf(FcmPushNotificationService);
    expect(push.fcm!.fcm.projectId).toBe(projectId);
    expect(push.native).toBeNull();
    expect(push.liveActivities).toBeNull();
    expect(push.linkAlerts).toMatchObject({ web: null, native: null, fcm: push.fcm });
  });

  it.each(['FCM_CLIENT_EMAIL', 'FCM_PRIVATE_KEY'])('refuses to start without %s', (missing) => {
    vi.stubEnv('FCM_PROJECT_ID', projectId);
    vi.stubEnv('FCM_CLIENT_EMAIL', clientEmail);
    vi.stubEnv('FCM_PRIVATE_KEY', privateKey);
    vi.stubEnv(missing, '');
    expect(() => pushServices(new SupabaseServiceClient('https://database.example', 'test')))
      .toThrow('FCM_PROJECT_ID, FCM_CLIENT_EMAIL and FCM_PRIVATE_KEY are all required');
  });

  it('counts Android\'s sends as native, beside the iPhone\'s', async () => {
    const channel = (sent: number) => ({ dispatch: async () => ({ attempted: sent, sent, failed: 0, expired: 0 }) }) as never;
    const composite = new CompositePushNotificationService(null, channel(1), null, null, channel(2));
    expect(await composite.dispatch()).toEqual({ attempted: 3, sent: 3, failed: 0, expired: 0, sentByChannel: { native: 3 } });
  });
});
