import { generateKeyPairSync } from 'node:crypto';
import { NextRequest } from 'next/server';
import { afterEach, beforeEach, describe, expect, it, vi } from 'vitest';
import { DELETE, POST } from '../../app/api/push/devices/route';
import { SupabaseAuthenticator } from './auth';
import { FcmClient } from './fcm';
import { SupabaseServiceClient } from './supabase';
import { nativePushDevice, nativePushTransport, parcelAppAlertToken } from './validation';

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
      status: 400, message: 'Choose apns or fcm as the transport',
    }));
  });

  it('refuses an FCM token sent as an iPhone\'s', () => {
    expect(() => parcelAppAlertToken({ token: fcmToken })).toThrow('Send a valid APNs device token');
  });
});
