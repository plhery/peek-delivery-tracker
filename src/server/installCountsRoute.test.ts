import { NextRequest } from 'next/server';
import { afterEach, beforeEach, describe, expect, it, vi } from 'vitest';
import { POST } from '../../app/api/public/seen/route';
import { installHash, readSighting } from './installCounts';
import { SupabaseServiceClient } from './supabase';

// Synthetic counting ids: no install made them.
const WEB_ID = 'q1Z9-x_Y8w7v6u5t4s3r2p';
const APP_ID = '6F1B6C7E-1D2A-4B3C-9D8E-0F1A2B3C4D5E';
let address = 0;
const nextIp = () => `198.51.100.${++address % 250}`;
const send = (body: unknown, headers: Record<string, string> = {}) => POST(new NextRequest('https://delivery.example/api/public/seen', {
  method: 'POST',
  headers: { 'content-type': 'application/json', 'x-real-ip': nextIp(), ...headers },
  body: JSON.stringify(body),
}), { params: Promise.resolve({}) });

beforeEach(() => {
  vi.stubEnv('SUPABASE_URL', 'https://database.example');
  vi.stubEnv('SUPABASE_SERVICE_ROLE_KEY', 'test-service-key');
  vi.stubEnv('TRUST_PROXY_HEADERS', 'true');
  vi.spyOn(console, 'log').mockImplementation(() => undefined);
  vi.spyOn(console, 'error').mockImplementation(() => undefined);
});
afterEach(() => { vi.restoreAllMocks(); vi.unstubAllEnvs(); });

describe('counting an install as seen', () => {
  it('records the keyed hash of the id, never the id, and answers without a body', async () => {
    const record = vi.spyOn(SupabaseServiceClient.prototype, 'recordInstallSeen').mockResolvedValue();
    const response = await send({ id: WEB_ID, platform: 'web', signedIn: false, parcels: 2 });
    expect(response.status).toBe(204);
    expect(await response.text()).toBe('');
    expect(record).toHaveBeenCalledExactlyOnceWith(expect.stringMatching(/^[0-9a-f]{64}$/), 'web', false, 2);
    expect(record.mock.calls[0]![0]).toBe(installHash(WEB_ID));
    expect(JSON.stringify(record.mock.calls)).not.toContain(WEB_ID);
  });

  it('logs the request without its id', async () => {
    vi.spyOn(SupabaseServiceClient.prototype, 'recordInstallSeen').mockResolvedValue();
    const log = vi.mocked(console.log);
    await send({ id: APP_ID, platform: 'iphone', signedIn: true, parcels: 0 });
    expect(log).toHaveBeenCalled();
    expect(JSON.stringify(log.mock.calls)).not.toContain(APP_ID);
  });

  it.each([
    ['no id', { platform: 'web', signedIn: false, parcels: 0 }],
    ['an id too short', { id: 'abc', platform: 'web', signedIn: false, parcels: 0 }],
    ['an id with a dot', { id: `${'a'.repeat(20)}.`, platform: 'web', signedIn: false, parcels: 0 }],
    ['an unknown platform', { id: WEB_ID, platform: 'watch', signedIn: false, parcels: 0 }],
    ['no sign-in state', { id: WEB_ID, platform: 'web', parcels: 0 }],
    ['parcels that are not a count', { id: WEB_ID, platform: 'web', signedIn: false, parcels: 1.5 }],
    ['negative parcels', { id: WEB_ID, platform: 'web', signedIn: false, parcels: -1 }],
  ])('refuses %s without writing', async (_name, body) => {
    const record = vi.spyOn(SupabaseServiceClient.prototype, 'recordInstallSeen');
    expect((await send(body)).status).toBe(400);
    expect(record).not.toHaveBeenCalled();
  });

  it('refuses a page on another site', async () => {
    const record = vi.spyOn(SupabaseServiceClient.prototype, 'recordInstallSeen');
    expect((await send({ id: WEB_ID, platform: 'web', signedIn: false, parcels: 0 }, { 'sec-fetch-site': 'cross-site' })).status).toBe(403);
    expect(record).not.toHaveBeenCalled();
  });

  it('limits a burst from one network', async () => {
    vi.spyOn(SupabaseServiceClient.prototype, 'recordInstallSeen').mockResolvedValue();
    const statuses = [];
    for (let attempt = 0; attempt < 11; attempt += 1) {
      statuses.push((await send({ id: WEB_ID, platform: 'web', signedIn: false, parcels: 0 }, { 'x-real-ip': '203.0.113.9' })).status);
    }
    expect(statuses).toEqual([...Array<number>(10).fill(204), 429]);
  });
});

describe('the counting hash', () => {
  it('is keyed: its own key when set, else one made from the service key', () => {
    const service = installHash(WEB_ID, { SUPABASE_SERVICE_ROLE_KEY: 'one' });
    expect(installHash(WEB_ID, { SUPABASE_SERVICE_ROLE_KEY: 'two' })).not.toBe(service);
    const own = installHash(WEB_ID, { COUNTING_KEY: 'counting', SUPABASE_SERVICE_ROLE_KEY: 'one' });
    expect(own).not.toBe(service);
    // The service key can change under its own key.
    expect(installHash(WEB_ID, { COUNTING_KEY: 'counting', SUPABASE_SERVICE_ROLE_KEY: 'two' })).toBe(own);
    expect(installHash(APP_ID, { COUNTING_KEY: 'counting' })).not.toBe(own);
  });

  it('reads a sighting', () => {
    expect(readSighting({ id: APP_ID, platform: 'android', signedIn: true, parcels: 3 }))
      .toEqual({ id: APP_ID, platform: 'android', signedIn: true, parcels: 3 });
  });
});
