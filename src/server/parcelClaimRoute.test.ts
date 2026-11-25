import { createHash } from 'node:crypto';
import { NextRequest } from 'next/server';
import { afterEach, beforeEach, expect, it, vi } from 'vitest';
import { POST } from '../../app/api/packages/claim/route';
import { SupabaseAuthenticator } from './auth';
import * as background from './background';
import * as metrics from './metrics';
import * as observability from './observability';
import { SupabaseError, SupabaseServiceClient, SupabaseUserClient } from './supabase';

const userId = '52000000-0000-4000-a000-000000000001';
const ownerKey = 'C'.repeat(43);
const keyHash = createHash('sha256').update(ownerKey).digest('hex');
const kept = '52000000-0000-4000-a000-000000000002';
const owned = '52000000-0000-4000-a000-000000000003';

const claim = (body: unknown, authenticated = true) => POST(new NextRequest('https://delivery.example/api/packages/claim', {
  method: 'POST',
  headers: { 'content-type': 'application/json', ...(authenticated ? { Authorization: 'Bearer claim-test' } : {}) },
  body: JSON.stringify(body),
}), { params: Promise.resolve({}) });

let enqueue: ReturnType<typeof vi.spyOn>;
let wake: ReturnType<typeof vi.spyOn>;
beforeEach(() => {
  vi.stubEnv('SUPABASE_URL', 'https://database.example');
  vi.stubEnv('SUPABASE_PUBLISHABLE_KEY', 'test-public');
  vi.stubEnv('SUPABASE_SERVICE_ROLE_KEY', 'test-service');
  vi.spyOn(console, 'log').mockImplementation(() => undefined);
  vi.spyOn(console, 'error').mockImplementation(() => undefined);
  vi.spyOn(SupabaseAuthenticator.prototype, 'validate').mockResolvedValue({ id: userId, email: null, authenticatedAt: null, sessionId: null });
  enqueue = vi.spyOn(SupabaseServiceClient.prototype, 'enqueueSyncJob').mockResolvedValue({ row: { id: 'job' }, queued: true });
  wake = vi.spyOn(background, 'wakeSyncWorker').mockImplementation(() => undefined);
});
afterEach(() => { vi.restoreAllMocks(); vi.unstubAllEnvs(); });

it('keeps each link under the caller\'s own token and queues a check for every kept parcel', async () => {
  const decide = vi.spyOn(SupabaseUserClient.prototype, 'claimParcelLink')
    .mockResolvedValueOnce({ outcome: 'kept', packageId: kept })
    .mockResolvedValueOnce({ outcome: 'already', packageId: owned })
    .mockResolvedValueOnce({ outcome: 'quota', packageId: null })
    .mockResolvedValueOnce(null);
  const counted = vi.spyOn(metrics, 'recordParcelClaim');
  const response = await claim({ links: [
    { id: 'keptLink2345', key: ownerKey, label: '  Sneakers ' },
    { id: 'ownedLink234', key: ownerKey },
    { id: 'quotaLink234' },
    { id: 'unknownLink2', key: 'not-a-key' },
    { id: 'not a link id', key: ownerKey },
  ] });
  expect(response.status).toBe(200);
  expect(response.headers.get('cache-control')).toBe('no-store');
  expect(await response.json()).toEqual({ results: [
    { id: 'keptLink2345', outcome: 'kept', packageId: kept },
    { id: 'ownedLink234', outcome: 'already', packageId: owned },
    { id: 'quotaLink234', outcome: 'quota' },
    { id: 'unknownLink2', outcome: 'unavailable' },
    { id: 'not a link id', outcome: 'unavailable' },
  ] });
  // The database sees the key's hash and the trimmed name; a malformed key is no key, a malformed id no link.
  expect(decide.mock.calls).toEqual([
    ['keptLink2345', keyHash, 'Sneakers', null],
    ['ownedLink234', keyHash, '', null],
    ['quotaLink234', null, '', null],
    ['unknownLink2', null, '', null],
  ]);
  expect(enqueue).toHaveBeenCalledExactlyOnceWith({ userId, packageId: kept, reason: 'added' });
  expect(wake).toHaveBeenCalledOnce();
  expect(counted.mock.calls.map(([outcome]) => outcome)).toEqual(['kept', 'already', 'quota', 'unavailable', 'unavailable']);
});

it('keeps the note each parcel had on the device with it, its lines kept and the rest trimmed', async () => {
  const decide = vi.spyOn(SupabaseUserClient.prototype, 'claimParcelLink').mockResolvedValue({ outcome: 'kept', packageId: kept });
  const note = 'é'.repeat(1_000);
  // Twenty links with a name and a note of 1,000 characters each fit in one request.
  const links = Array.from({ length: 20 }, (_, index) => ({
    id: `keptLinkab${'abcdefghijkmnopqrstu'[index]}${'abcdefghijkmnopqrstu'[19 - index]}`, key: ownerKey, label: 'ü'.repeat(80), note,
  }));
  links[0] = { ...links[0]!, note: '  Size 42\r\nReturn by Friday\u0007  ' };
  links[1] = { ...links[1]!, note: '   ' };
  const response = await claim({ links });
  expect(response.status).toBe(200);
  expect(decide).toHaveBeenCalledTimes(20);
  expect(decide.mock.calls[0]).toEqual(['keptLinkabau', keyHash, 'ü'.repeat(80), 'Size 42\nReturn by Friday']);
  expect(decide.mock.calls[1]![3]).toBeNull();
  expect(decide.mock.calls[2]![3]).toBe(note);
});

it.each([
  [{ links: [{ id: 'keptLink2345', note: 'n'.repeat(1_001) }] }],
  [{ links: [{ id: 'keptLink2345', note: 42 }] }],
])('refuses a note that is not text of at most 1,000 characters before asking the database', async (body) => {
  const decide = vi.spyOn(SupabaseUserClient.prototype, 'claimParcelLink');
  expect((await claim(body)).status).toBe(400);
  expect(decide).not.toHaveBeenCalled();
});

it('keeps the parcel when its check cannot be queued', async () => {
  vi.spyOn(SupabaseUserClient.prototype, 'claimParcelLink').mockResolvedValue({ outcome: 'kept', packageId: kept });
  enqueue.mockRejectedValue(new SupabaseError('queue unavailable', 503));
  const response = await claim({ links: [{ id: 'keptLink2345', key: ownerKey }] });
  expect(response.status).toBe(200);
  expect((await response.json()).results[0]).toEqual({ id: 'keptLink2345', outcome: 'kept', packageId: kept });
  expect(wake).not.toHaveBeenCalled();
});

it.each([
  [{}],
  [{ links: [] }],
  [{ links: Array.from({ length: 21 }, () => ({ id: 'keptLink2345' })) }],
  [{ links: ['keptLink2345'] }],
  [{ links: [{ id: 42 }] }],
  [{ links: [{ id: 'keptLink2345', key: 42 }] }],
  [{ links: [{ id: 'keptLink2345', label: 'n'.repeat(81) }] }],
  [{ links: [{ id: 'keptLink2345', label: 42 }] }],
])('rejects %j before asking the database', async (body) => {
  const decide = vi.spyOn(SupabaseUserClient.prototype, 'claimParcelLink');
  expect((await claim(body)).status).toBe(400);
  expect(decide).not.toHaveBeenCalled();
});

it('requires a signed-in account', async () => {
  const decide = vi.spyOn(SupabaseUserClient.prototype, 'claimParcelLink');
  expect((await claim({ links: [{ id: 'keptLink2345', key: ownerKey }] }, false)).status).toBe(401);
  expect(decide).not.toHaveBeenCalled();
});

it('leaves the request, which carries owner keys, out of an error report', async () => {
  vi.spyOn(SupabaseUserClient.prototype, 'claimParcelLink').mockRejectedValue(new SupabaseError('database down', 503));
  const report = vi.spyOn(observability, 'captureOperationalError').mockReturnValue(null);
  expect((await claim({ links: [{ id: 'keptLink2345', key: ownerKey }] })).status).toBe(502);
  expect(report).toHaveBeenCalledExactlyOnceWith(expect.any(SupabaseError), expect.objectContaining({
    route: '/api/packages/claim', withoutRequest: true,
  }));
});
