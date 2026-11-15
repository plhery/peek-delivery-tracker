import { createECDH, generateKeyPairSync, randomBytes } from 'node:crypto';
import { NextRequest } from 'next/server';
import { afterEach, beforeEach, describe, expect, it, vi } from 'vitest';
import webpush from 'web-push';
import { DELETE, POST } from '../../app/api/push/devices/route';
import { SupabaseAuthenticator } from './auth';
import { FcmClient } from './fcm';
import * as publicAddress from './publicAddress';
import { SupabaseServiceClient } from './supabase';
import { nativePushDevice, nativePushTransport, parcelAppAlert, parcelAppAlertToken } from './validation';

// Synthetic tokens and keys, made for the test: neither FCM nor APNs issued them.
const owner = '10000000-0000-0000-0000-000000000001';
const fcmToken = `synthetic-Token:${'Ab0_-'.repeat(30)}`;
const apnsToken = 'ab'.repeat(32);
const registration = (changes: Record<string, unknown> = {}) => ({
  token: fcmToken, transport: 'fcm', locale: 'fr', sendTest: false, deviceName: 'Pixel', ...changes,
});
const call = (handler: typeof POST, method: string, body: unknown) => handler(new NextRequest('https://delivery.example/api/push/devices', {
  method, headers: { 'content-type': 'application/json', Authorization: 'Bearer test-session' }, body: JSON.stringify(body),
}), { params: Promise.resolve({}) });

const fcmSettings = () => {
  vi.stubEnv('FCM_PROJECT_ID', 'synthetic-project');
  vi.stubEnv('FCM_CLIENT_EMAIL', 'sender@synthetic-project.example');
  vi.stubEnv('FCM_PRIVATE_KEY', generateKeyPairSync('rsa', { modulusLength: 2048 }).privateKey
    .export({ type: 'pkcs8', format: 'pem' }).toString());
};

beforeEach(() => {
  vi.stubEnv('SUPABASE_URL', 'https://database.example');
  vi.stubEnv('SUPABASE_PUBLISHABLE_KEY', 'public-key');
  vi.stubEnv('SUPABASE_SERVICE_ROLE_KEY', 'service-key');
  for (const name of ['VAPID_PUBLIC_KEY', 'VAPID_PRIVATE_KEY', 'APNS_TEAM_ID', 'APNS_KEY_ID', 'APNS_PRIVATE_KEY', 'APNS_BUNDLE_ID',
    'FCM_PROJECT_ID', 'FCM_CLIENT_EMAIL', 'FCM_PRIVATE_KEY']) vi.stubEnv(name, '');
  vi.spyOn(console, 'log').mockImplementation(() => undefined);
  vi.spyOn(console, 'error').mockImplementation(() => undefined);
  vi.spyOn(SupabaseAuthenticator.prototype, 'validate').mockResolvedValue({
    id: owner, email: null, authenticatedAt: null, sessionId: null,
  });
});
afterEach(() => { vi.restoreAllMocks(); vi.unstubAllEnvs(); });

describe('an Android phone\'s registration', () => {
  it('stores the FCM token as sent, as a production device, and sends the test notification through FCM', async () => {
    fcmSettings();
    const write = vi.spyOn(SupabaseServiceClient.prototype, 'request')
      .mockResolvedValue([{ id: 'device-1', token: fcmToken, locale: 'fr', transport: 'fcm' }]);
    const sent = vi.spyOn(FcmClient.prototype, 'send').mockResolvedValue();
    const response = await call(POST, 'POST', registration({ sendTest: true, environment: 'development' }));
    expect(response.status).toBe(201);
    expect(await response.json()).toEqual({ ok: true, testSent: true });
    const [path, options] = write.mock.calls[0]!;
    expect(path).toBe('/rest/v1/native_push_devices?on_conflict=environment%2Ctoken');
    expect(options?.body).toMatchObject({
      user_id: owner, token: fcmToken, environment: 'production', transport: 'fcm', locale: 'fr', device_name: 'Pixel',
    });
    expect(sent).toHaveBeenCalledExactlyOnceWith(fcmToken, { title: expect.any(String), body: expect.any(String) }, '');
  });

  it('still registers when FCM cannot take the test notification', async () => {
    fcmSettings();
    vi.spyOn(SupabaseServiceClient.prototype, 'request').mockResolvedValue([{ id: 'device-1', token: fcmToken, locale: 'fr' }]);
    vi.spyOn(FcmClient.prototype, 'send').mockRejectedValue(new Error('offline'));
    const response = await call(POST, 'POST', registration({ sendTest: true }));
    expect(response.status).toBe(201);
    expect(await response.json()).toEqual({ ok: true, testSent: false });
  });

  it('is turned away as an iPhone is without APNs when this server has no FCM settings', async () => {
    const write = vi.spyOn(SupabaseServiceClient.prototype, 'request');
    const response = await call(POST, 'POST', registration());
    expect(response.status).toBe(503);
    expect(await response.json()).toEqual({ error: 'Native push notifications are not configured' });
    // An iPhone's request, which names no transport, still needs APNs.
    fcmSettings();
    expect((await call(POST, 'POST', { token: apnsToken, environment: 'production', locale: 'fr', sendTest: false })).status).toBe(503);
    expect(write).not.toHaveBeenCalled();
  });

  it('is removed by its token and transport, letter case kept', async () => {
    const write = vi.spyOn(SupabaseServiceClient.prototype, 'request').mockResolvedValue(null);
    expect((await call(DELETE, 'DELETE', { token: fcmToken, transport: 'fcm' })).status).toBe(200);
    const url = new URL(write.mock.calls[0]![0], 'https://database.example');
    expect(url.pathname).toBe('/rest/v1/native_push_devices');
    expect(Object.fromEntries(url.searchParams)).toEqual({ user_id: `eq.${owner}`, token: `eq.${fcmToken}`, transport: 'eq.fcm' });
  });
});

describe('what a phone may register', () => {
  it('reads a request that names no transport as an iPhone\'s, as before', () => {
    expect(nativePushTransport(undefined)).toBe('apns');
    expect(nativePushTransport(null)).toBe('apns');
    expect(nativePushDevice({ token: apnsToken.toUpperCase(), environment: 'development', locale: 'en', sendTest: false }))
      .toMatchObject({ token: apnsToken, transport: 'apns', environment: 'development' });
  });

  it('keeps an FCM token\'s letter case and records it in production, whatever environment is sent', () => {
    for (const environment of [undefined, 'development', 'production', 'anything']) {
      expect(nativePushDevice(registration({ environment }))).toMatchObject({
        token: fcmToken, transport: 'fcm', environment: 'production', installationId: null, deviceName: 'Pixel',
      });
    }
    expect(nativePushDevice({ token: ` ${fcmToken} `, transport: 'fcm' }, false)).toMatchObject({ token: fcmToken, transport: 'fcm' });
    expect(parcelAppAlertToken({ token: fcmToken, transport: 'fcm' })).toBe(fcmToken);
  });

  it.each([
    ['a token too short', 'a'.repeat(31)],
    ['a token too long for the token index', 'a'.repeat(2_049)],
    ['a token with a slash', `${fcmToken}/x`],
    ['a token with a dot', `${fcmToken}.x`],
    ['a token that is not text', 42],
  ])('refuses %s', (_name, token) => {
    expect(() => nativePushDevice(registration({ token }))).toThrow(expect.objectContaining({
      status: 400, message: 'Send a valid FCM registration token',
    }));
  });

  it('takes the longest token the index holds', () => {
    expect(nativePushDevice(registration({ token: 'a'.repeat(2_048) })).token).toHaveLength(2_048);
  });

  it.each(['gcm', 'FCM', 'APNS', '', 1])('refuses the transport %j', (transport) => {
    expect(() => nativePushDevice(registration({ transport }))).toThrow(expect.objectContaining({
      status: 400, message: 'Choose apns, fcm, unifiedpush or inbox as the transport',
    }));
  });

  it('refuses an FCM token sent as an iPhone\'s', () => {
    expect(() => parcelAppAlertToken({ token: fcmToken })).toThrow('Send a valid APNs device token');
  });
});

describe('an Android phone without Google\'s services', () => {
  // A synthetic installation token, endpoint and keys: no UnifiedPush distributor issued them.
  const token = `${'Up_-'.repeat(10)}abc`;
  const endpoint = 'https://push.example.com/up/synthetic-endpoint';
  const ecdh = createECDH('prime256v1');
  ecdh.generateKeys();
  const keys = { p256dh: ecdh.getPublicKey().toString('base64url'), auth: randomBytes(16).toString('base64url') };
  const unifiedPush = (changes: Record<string, unknown> = {}) => registration({ token, transport: 'unifiedpush', endpoint, keys, ...changes });
  const inbox = (changes: Record<string, unknown> = {}) => registration({ token, transport: 'inbox', ...changes });
  const vapidSettings = () => {
    const vapid = webpush.generateVAPIDKeys();
    vi.stubEnv('VAPID_PUBLIC_KEY', vapid.publicKey);
    vi.stubEnv('VAPID_PRIVATE_KEY', vapid.privateKey);
  };

  it('stores the installation token with the endpoint and keys, as a production device, and sends the test through UnifiedPush', async () => {
    vapidSettings();
    const write = vi.spyOn(SupabaseServiceClient.prototype, 'request')
      .mockResolvedValue([{ id: 'device-1', token, locale: 'fr', transport: 'unifiedpush', endpoint, ...keys }]);
    const post = vi.spyOn(publicAddress, 'postToPublicEndpoint').mockResolvedValue(201);
    const response = await call(POST, 'POST', unifiedPush({ sendTest: true, environment: 'development' }));
    expect(response.status).toBe(201);
    expect(await response.json()).toEqual({ ok: true, testSent: true });
    const [path, options] = write.mock.calls[0]!;
    expect(path).toBe('/rest/v1/native_push_devices?on_conflict=environment%2Ctoken');
    expect(options?.body).toMatchObject({
      user_id: owner, token, environment: 'production', transport: 'unifiedpush', endpoint, p256dh: keys.p256dh, auth: keys.auth,
    });
    expect(post).toHaveBeenCalledExactlyOnceWith(endpoint, expect.objectContaining({ headers: expect.objectContaining({ Urgency: 'high' }) }));
  });

  it('is turned away without the server\'s VAPID key, which UnifiedPush signs with', async () => {
    const write = vi.spyOn(SupabaseServiceClient.prototype, 'request');
    const response = await call(POST, 'POST', unifiedPush());
    expect(response.status).toBe(503);
    expect(await response.json()).toEqual({ error: 'Native push notifications are not configured' });
    expect(write).not.toHaveBeenCalled();
  });

  it('registers for the inbox on a server with no push settings at all, and leaves the test notice there', async () => {
    const write = vi.spyOn(SupabaseServiceClient.prototype, 'request')
      .mockResolvedValue([{ id: 'device-1', token, locale: 'fr', transport: 'inbox' }]);
    const response = await call(POST, 'POST', inbox({ sendTest: true }));
    expect(response.status).toBe(201);
    expect(await response.json()).toEqual({ ok: true, testSent: true });
    const [[path, options], [testPath, test]] = write.mock.calls as [[string, { body?: Record<string, unknown> }], [string, { body?: Record<string, unknown> }]];
    expect(path).toBe('/rest/v1/native_push_devices?on_conflict=environment%2Ctoken');
    // A phone switching from UnifiedPush leaves its endpoint and keys behind.
    expect(options.body).toMatchObject({
      user_id: owner, token, environment: 'production', transport: 'inbox', endpoint: null, p256dh: null, auth: null,
    });
    expect(testPath).toBe('/rest/v1/rpc/put_push_inbox');
    expect(test.body).toMatchObject({ p_token: token, p_device_id: 'device-1', p_data: { title: expect.any(String), body: expect.any(String) } });
  });

  it.each([
    ['an endpoint over plain HTTP', { endpoint: 'http://push.example.com/up' }, 'Send a valid UnifiedPush endpoint'],
    ['an endpoint with credentials', { endpoint: 'https://user:secret@push.example.com/up' }, 'Send a valid UnifiedPush endpoint'],
    ['an endpoint with a fragment', { endpoint: 'https://push.example.com/up#x' }, 'Send a valid UnifiedPush endpoint'],
    ['an endpoint too long', { endpoint: `https://push.example.com/${'a'.repeat(4_096)}` }, 'Send a valid UnifiedPush endpoint'],
    ['no endpoint', { endpoint: undefined }, 'Send a valid UnifiedPush endpoint'],
    ['an endpoint on localhost', { endpoint: 'https://localhost/up' }, 'Send a UnifiedPush endpoint on a public host'],
    ['an endpoint in a private network', { endpoint: 'https://192.168.1.10/up' }, 'Send a UnifiedPush endpoint on a public host'],
    ['an endpoint at loopback written in hex', { endpoint: 'https://0x7f.1/up' }, 'Send a UnifiedPush endpoint on a public host'],
    ['an endpoint at a mapped IPv6 loopback', { endpoint: 'https://[::ffff:127.0.0.1]/up' }, 'Send a UnifiedPush endpoint on a public host'],
    ['an endpoint on a .internal name', { endpoint: 'https://metadata.internal/up' }, 'Send a UnifiedPush endpoint on a public host'],
    ['no keys', { keys: undefined }, 'Send valid push encryption keys'],
    ['a public key off the curve', { keys: { ...keys, p256dh: Buffer.concat([Buffer.from([4]), Buffer.alloc(64, 1)]).toString('base64url') } }, 'Send valid push encryption keys'],
    ['an auth secret of the wrong length', { keys: { ...keys, auth: randomBytes(8).toString('base64url') } }, 'Send valid push encryption keys'],
    ['an installation token too short', { token: token.slice(1) }, 'Send a valid installation token'],
    ['an installation token with a dot', { token: `${token.slice(1)}.` }, 'Send a valid installation token'],
  ])('refuses %s', (_name, changes, message) => {
    expect(() => nativePushDevice(unifiedPush(changes))).toThrow(expect.objectContaining({ status: 400, message }));
  });

  it.each([
    ['the inbox', { transport: 'inbox' }],
    ['FCM', { transport: 'fcm', token: fcmToken }],
    ['APNs', { transport: undefined, token: apnsToken, environment: 'production' }],
  ])('refuses an endpoint or keys sent for %s', (_name, changes) => {
    for (const extra of [{ endpoint }, { keys }, { endpoint, keys }]) {
      const payload = { ...registration({ token, ...changes }), ...extra };
      expect(() => nativePushDevice(payload)).toThrow(expect.objectContaining({
        status: 400, message: 'Send an endpoint and keys only with the unifiedpush transport',
      }));
      expect(() => parcelAppAlert({ ...payload, preset: 'important' })).toThrow(expect.objectContaining({ status: 400 }));
    }
  });

  it('reads an endpoint and keys sent as null as none', () => {
    expect(nativePushDevice({ ...registration(), endpoint: null, keys: null })).toMatchObject({ transport: 'fcm', webPush: null });
  });

  it('keeps the endpoint as the URL parser writes it', () => {
    expect(nativePushDevice(unifiedPush({ endpoint: 'https://Push.Example.com:443/up/x' })).webPush)
      .toEqual({ endpoint: 'https://push.example.com/up/x', ...keys });
    expect(nativePushDevice(inbox()).webPush).toBeNull();
    expect(parcelAppAlert({ token, transport: 'unifiedpush', endpoint, keys, preset: 'important', locale: 'fr' }))
      .toEqual({ token, transport: 'unifiedpush', environment: 'production', locale: 'fr', preset: 'important', webPush: { endpoint, ...keys } });
  });

  it.each(['unifiedpush', 'inbox'])('is removed by its token and %s, without endpoint or keys', async (transport) => {
    const write = vi.spyOn(SupabaseServiceClient.prototype, 'request').mockResolvedValue(null);
    expect((await call(DELETE, 'DELETE', { token, transport })).status).toBe(200);
    const url = new URL(write.mock.calls[0]![0], 'https://database.example');
    expect(Object.fromEntries(url.searchParams)).toEqual({ user_id: `eq.${owner}`, token: `eq.${token}`, transport: `eq.${transport}` });
    expect(parcelAppAlertToken({ token, transport })).toBe(token);
  });

  it('never logs the endpoint, the keys or the token', async () => {
    vapidSettings();
    vi.spyOn(SupabaseServiceClient.prototype, 'request').mockResolvedValue([{ id: 'device-1', token, locale: 'fr' }]);
    vi.spyOn(publicAddress, 'postToPublicEndpoint').mockResolvedValue(500);
    await call(POST, 'POST', unifiedPush({ sendTest: true }));
    await call(POST, 'POST', unifiedPush({ endpoint: 'https://10.0.0.1/up/synthetic-endpoint' }));
    const lines = [...vi.mocked(console.log).mock.calls, ...vi.mocked(console.error).mock.calls].map((line) => line.map(String).join(' '));
    expect(lines.length).toBeGreaterThan(0);
    for (const line of lines) {
      for (const secret of ['synthetic-endpoint', keys.p256dh, keys.auth, token]) expect(line).not.toContain(secret);
    }
  });
});
