import { NextRequest } from 'next/server';
import { afterEach, beforeEach, describe, expect, it, vi } from 'vitest';
import { POST as markSeen } from '../../app/api/packages/[id]/seen/route';
import contract from '../../contracts/openapi.json';
import { SupabaseAuthenticator } from './auth';
import { SupabaseUserClient } from './supabase';

// Synthetic identifiers only.
const userId = '72000000-0000-4000-a000-000000000001';
const packageId = '72000000-0000-4000-a000-000000000002';
const mergedInto = '72000000-0000-4000-a000-000000000003';
const seenAt = '2026-11-15T08:00:00.123456+00:00';

let address = 0;
const open = (id = packageId) => markSeen(new NextRequest(`https://delivery.example/api/packages/${id}/seen`, {
  method: 'POST', headers: { Authorization: 'Bearer seen-test', 'x-forwarded-for': `198.51.100.${++address % 250 + 1}` },
}), { params: Promise.resolve({ id }) });

beforeEach(() => {
  vi.stubEnv('SUPABASE_URL', 'https://database.example');
  vi.stubEnv('SUPABASE_PUBLISHABLE_KEY', 'test-public');
  vi.stubEnv('SUPABASE_SERVICE_ROLE_KEY', 'test-service-key');
  vi.stubEnv('TRUST_PROXY_HEADERS', 'true');
  vi.spyOn(console, 'log').mockImplementation(() => undefined);
  vi.spyOn(console, 'error').mockImplementation(() => undefined);
  vi.spyOn(SupabaseAuthenticator.prototype, 'validate').mockResolvedValue({
    id: userId, email: 'alex@example.com', emailConfirmed: true, authenticatedAt: null, sessionId: null,
  });
});
afterEach(() => { vi.restoreAllMocks(); vi.unstubAllEnvs(); });

describe('opening a parcel marks it as seen (#72)', () => {
  it('answers the moment stored, in the shape the contract lists', async () => {
    const mark = vi.spyOn(SupabaseUserClient.prototype, 'markPackageSeen').mockResolvedValue(seenAt);
    const response = await open();
    expect(response.status).toBe(200);
    const answer = await response.json();
    expect(answer).toEqual({ seen_at: seenAt });
    expect(mark).toHaveBeenCalledExactlyOnceWith(packageId);
    const schema = contract.components.schemas.PackageSeenResponse;
    expect(Object.keys(answer)).toEqual(schema.required);
    expect(contract.paths['/api/packages/{packageId}/seen'].post.responses['200'].content['application/json'].schema.$ref)
      .toBe('#/components/schemas/PackageSeenResponse');
  });

  it('finds no parcel that is not the account\'s, and takes only a parcel id', async () => {
    const mark = vi.spyOn(SupabaseUserClient.prototype, 'markPackageSeen').mockResolvedValue(null);
    expect((await open()).status).toBe(404);
    mark.mockClear();
    expect((await open('not-a-parcel')).status).toBe(400);
    expect(mark).not.toHaveBeenCalled();
  });

  it('asks the owner-scoped RPC, and marks the parcel an old id was merged into', async () => {
    const client = new SupabaseUserClient('https://database.example', 'public-key', 'token');
    const request = vi.spyOn(client, 'request').mockResolvedValueOnce(seenAt);
    await expect(client.markPackageSeen(packageId)).resolves.toBe(seenAt);
    expect(request).toHaveBeenCalledExactlyOnceWith('/rest/v1/rpc/mark_owned_package_seen', { method: 'POST', body: { p_package_id: packageId } });

    request.mockReset().mockResolvedValueOnce(null).mockResolvedValueOnce([{ id: mergedInto }]).mockResolvedValueOnce(seenAt);
    await expect(client.markPackageSeen(packageId)).resolves.toBe(seenAt);
    const lookup = new URL(`https://database.example${request.mock.calls[1]![0]}`).searchParams;
    expect(lookup.get('carrier_data->>original_package_id')).toBe(`eq.${packageId}`);
    expect(request.mock.calls[2]![1]).toEqual({ method: 'POST', body: { p_package_id: mergedInto } });

    request.mockReset().mockResolvedValueOnce(null).mockResolvedValueOnce([]);
    await expect(client.markPackageSeen(packageId)).resolves.toBeNull();
  });

  it('saves the mark\'s switch only when a request makes the choice', async () => {
    const client = new SupabaseUserClient('https://database.example', 'public-key', 'token');
    const request = vi.spyOn(client, 'request').mockResolvedValue({ enabled_stages: ['delivered'], mark_new_updates: false });
    const base = { p_enabled_stages: ['delivered'], p_quiet_hours_start: null, p_quiet_hours_end: null, p_timezone: 'Europe/Zurich' };
    await client.setNotificationPreferences(['delivered'], null, null, 'Europe/Zurich');
    await client.setNotificationPreferences(['delivered'], null, null, 'Europe/Zurich', null, false);
    await client.setNotificationPreferences(['delivered'], null, null, 'Europe/Zurich', true, true);
    expect(request.mock.calls.map(([, options]) => options!.body)).toEqual([
      base, { ...base, p_mark_new_updates: false }, { ...base, p_email_on_delivery: true, p_mark_new_updates: true },
    ]);
    request.mockResolvedValue([]);
    await expect(client.getNotificationPreferences()).resolves.toMatchObject({ mark_new_updates: true });
  });
});
