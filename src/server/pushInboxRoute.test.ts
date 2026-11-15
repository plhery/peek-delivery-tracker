import { NextRequest } from 'next/server';
import { afterEach, beforeEach, describe, expect, it, vi } from 'vitest';
import { POST } from '../../app/api/public/push/inbox/route';
import { SupabaseServiceClient } from './supabase';

// Synthetic installation tokens: no phone made them.
let made = 0;
const newToken = () => `${String(++made).padStart(3, '0')}${'In_-'.repeat(10)}`;
let address = 0;
const nextIp = () => `198.51.100.${++address % 250}`;
const fetchInbox = (body: unknown, headers: Record<string, string> = {}) => POST(new NextRequest('https://delivery.example/api/public/push/inbox', {
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

describe('the Android app\'s inbox', () => {
  it('hands over the notices waiting for the token, oldest first, without sign-in', async () => {
    const token = newToken();
    const messages: Record<string, string>[] = [
      { kind: 'parcel_update', title: 'Paket', body: 'Unterwegs', parcel_id: 'package-1' }, { title: 'Test', body: 'OK' },
    ];
    const take = vi.spyOn(SupabaseServiceClient.prototype, 'takePushInbox').mockResolvedValue(messages);
    const response = await fetchInbox({ token });
    expect(response.status).toBe(200);
    expect(response.headers.get('cache-control')).toBe('no-store');
    expect(await response.json()).toEqual({ messages });
    expect(take).toHaveBeenCalledExactlyOnceWith(token);
  });

  it('answers a token with nothing waiting, or never registered, with an empty list', async () => {
    vi.spyOn(SupabaseServiceClient.prototype, 'takePushInbox').mockResolvedValue([]);
    expect(await (await fetchInbox({ token: newToken() })).json()).toEqual({ messages: [] });
  });

  it.each([
    ['no token', {}],
    ['a token too short', { token: 'abc' }],
    ['a token with a dot', { token: `${'a'.repeat(42)}.` }],
    ['a token that is not text', { token: 42 }],
  ])('refuses %s without reading the database', async (_name, body) => {
    const take = vi.spyOn(SupabaseServiceClient.prototype, 'takePushInbox');
    const response = await fetchInbox(body);
    expect(response.status).toBe(400);
    expect(await response.json()).toEqual({ error: 'Send a valid installation token' });
    expect(take).not.toHaveBeenCalled();
  });

  it('limits each token, from whatever address it asks', async () => {
    const take = vi.spyOn(SupabaseServiceClient.prototype, 'takePushInbox').mockResolvedValue([]);
    const token = newToken();
    const statuses = [];
    for (let attempt = 0; attempt < 13; attempt += 1) statuses.push((await fetchInbox({ token })).status);
    expect(statuses).toEqual([...Array<number>(12).fill(200), 429]);
    const limited = await fetchInbox({ token });
    expect(Number(limited.headers.get('retry-after'))).toBeGreaterThan(0);
    expect(take).toHaveBeenCalledTimes(12);
    expect((await fetchInbox({ token: newToken() })).status).toBe(200);
  });

  it('limits each address across tokens', async () => {
    vi.spyOn(SupabaseServiceClient.prototype, 'takePushInbox').mockResolvedValue([]);
    const statuses = [];
    for (let attempt = 0; attempt < 121; attempt += 1) {
      statuses.push((await fetchInbox({ token: newToken() }, { 'x-real-ip': '203.0.113.77' })).status);
    }
    expect(statuses.slice(0, 120).every((status) => status === 200)).toBe(true);
    expect(statuses[120]).toBe(429);
  });

  it('refuses another site\'s page and a body that is not JSON', async () => {
    const take = vi.spyOn(SupabaseServiceClient.prototype, 'takePushInbox');
    expect((await fetchInbox({ token: newToken() }, { origin: 'https://elsewhere.example', 'sec-fetch-site': 'cross-site' })).status).toBe(403);
    expect((await fetchInbox({ token: newToken() }, { 'content-type': 'text/plain' })).status).toBe(415);
    expect(take).not.toHaveBeenCalled();
  });

  it('never logs the token', async () => {
    vi.spyOn(SupabaseServiceClient.prototype, 'takePushInbox').mockResolvedValue([]);
    const token = newToken();
    await fetchInbox({ token });
    const lines = [...vi.mocked(console.log).mock.calls, ...vi.mocked(console.error).mock.calls].map((line) => line.map(String).join(' '));
    expect(lines.some((line) => line.includes('/api/public/push/inbox'))).toBe(true);
    for (const line of lines) expect(line).not.toContain(token);
  });
});
