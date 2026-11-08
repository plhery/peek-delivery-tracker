import { NextRequest } from 'next/server';
import { afterEach, beforeEach, expect, it, vi } from 'vitest';
import { POST as watchLiveActivity } from '../../app/api/live-activities/activities/route';
import { DELETE as archive, PATCH as rename } from '../../app/api/packages/[id]/route';
import { PATCH as changeCarrier } from '../../app/api/packages/[id]/carrier/route';
import { PATCH as changeAlerts } from '../../app/api/packages/[id]/notifications/route';
import { DELETE as deleteForever } from '../../app/api/packages/[id]/permanent/route';
import { POST as restore } from '../../app/api/packages/[id]/restore/route';
import { DELETE as stopSharing, GET as readShare, PUT as share } from '../../app/api/packages/[id]/share/route';
import { POST as refresh } from '../../app/api/packages/[id]/sync/route';
import { SupabaseAuthenticator } from './auth';
import * as metrics from './metrics';
import { SupabaseServiceClient, SupabaseUserClient } from './supabase';

vi.mock('./background', () => ({ wakeSyncWorker: vi.fn() }));
vi.mock('./push', async (original) => ({
  ...await original<typeof import('./push')>(),
  pushServices: () => ({ liveActivities: {} }),
}));

// Synthetic identifiers only. The stale id is the parcel's own before it was merged into the kept one.
const userId = '5e000000-0000-4000-a000-000000000001';
const staleId = '5e000000-0000-4000-a000-000000000002';
const keptId = '5e000000-0000-4000-a000-000000000003';
const kept = {
  id: keptId,
  tracking_number: '1234560891',
  carrier: 'dhl-express',
  tracking_url: null,
  dpd_postcode: null,
  archived_at: null,
  carrier_data: { original_package_id: staleId },
};

type Handler = (request: NextRequest, context: { params: Promise<{ id: string }> }) => Promise<Response>;

function call(handler: Handler, method: string, body?: object, id = staleId, path = '') {
  return handler(new NextRequest(`https://delivery.example/api/packages/${id}${path}`, {
    method,
    headers: { Authorization: 'Bearer synthetic-token', ...(body ? { 'Content-Type': 'application/json' } : {}) },
    ...(body ? { body: JSON.stringify(body) } : {}),
  }), { params: Promise.resolve({ id }) });
}

beforeEach(() => {
  vi.stubEnv('SUPABASE_URL', 'https://database.example');
  vi.stubEnv('SUPABASE_PUBLISHABLE_KEY', 'public-key');
  vi.stubEnv('SUPABASE_SERVICE_ROLE_KEY', 'service-key');
  vi.spyOn(console, 'log').mockImplementation(() => undefined);
  vi.spyOn(console, 'error').mockImplementation(() => undefined);
  vi.spyOn(SupabaseAuthenticator.prototype, 'validate').mockResolvedValue({ id: userId, email: null, authenticatedAt: null, sessionId: null });
  // As the database answers: the stale id finds the parcel it was merged into.
  vi.spyOn(SupabaseUserClient.prototype, 'getPackage')
    .mockImplementation(async (id) => (id === staleId || id === keptId ? kept : null));
  vi.spyOn(SupabaseServiceClient.prototype, 'enqueueSyncJob').mockResolvedValue({ row: { id: '5e000000-0000-4000-a000-000000000004' }, queued: true });
  vi.spyOn(SupabaseServiceClient.prototype, 'pendingSyncJobCount').mockResolvedValue(1);
});
afterEach(() => { vi.restoreAllMocks(); vi.unstubAllEnvs(); vi.unstubAllGlobals(); });

it('renames, archives, restores and deletes the parcel a stale id was merged into', async () => {
  const update = vi.spyOn(SupabaseUserClient.prototype, 'updatePackage').mockResolvedValue(undefined);
  const archived = vi.spyOn(SupabaseUserClient.prototype, 'archivePackage').mockResolvedValue(undefined);
  const restored = vi.spyOn(SupabaseUserClient.prototype, 'restorePackage').mockResolvedValue(undefined);
  const deleted = vi.spyOn(SupabaseUserClient.prototype, 'deletePackage').mockResolvedValue(true);

  const renamed = await call(rename, 'PATCH', { label: 'Boots' });
  expect(renamed.status).toBe(200);
  expect((await renamed.json()).id).toBe(keptId);
  expect(update).toHaveBeenCalledExactlyOnceWith(keptId, { label: 'Boots' });
  expect((await call(archive, 'DELETE')).status).toBe(200);
  expect(archived).toHaveBeenCalledExactlyOnceWith(keptId);
  expect((await call(restore, 'POST', undefined, staleId, '/restore')).status).toBe(200);
  expect(restored).toHaveBeenCalledExactlyOnceWith(keptId);
  expect((await call(deleteForever, 'DELETE', undefined, staleId, '/permanent')).status).toBe(200);
  expect(deleted).toHaveBeenCalledExactlyOnceWith(keptId);
});

it('changes the alerts of, and refreshes, the parcel a stale id was merged into', async () => {
  const update = vi.spyOn(SupabaseUserClient.prototype, 'updatePackage').mockResolvedValue(undefined);
  expect((await call(changeAlerts, 'PATCH', { muted: true, emailMuted: false }, staleId, '/notifications')).status).toBe(200);
  expect(update.mock.calls).toEqual([[keptId, { notifications_muted: true }], [keptId, { email_muted: false }]]);
  expect((await call(refresh, 'POST', undefined, staleId, '/sync')).status).toBe(202);
  expect(SupabaseServiceClient.prototype.enqueueSyncJob).toHaveBeenCalledExactlyOnceWith({ userId, packageId: keptId, reason: 'refresh' });
});

it('changes the carrier of the parcel a stale id was merged into, and checks that one', async () => {
  const changed = vi.spyOn(SupabaseUserClient.prototype, 'changePackageCarrier').mockResolvedValue(true);
  const response = await call(changeCarrier, 'PATCH', { carrier: 'unknown' }, staleId, '/carrier');
  expect(response.status).toBe(200);
  expect(changed).toHaveBeenCalledExactlyOnceWith(keptId, 'unknown', null, null);
  expect(SupabaseServiceClient.prototype.enqueueSyncJob).toHaveBeenCalledExactlyOnceWith({ userId, packageId: keptId, reason: 'changed' });
  const logged = vi.mocked(console.log).mock.calls.map(([line]) => String(line)).find((line) => line.includes('package_carrier_changed'));
  expect(logged).toContain(keptId);
  expect(logged).not.toContain(staleId);

  // A provider's input goes to the kept parcel too.
  vi.mocked(SupabaseUserClient.prototype.getPackage).mockResolvedValue({ ...kept, carrier: 'unknown',
    carrier_data: { original_package_id: staleId, routing: { version: 1, provider_input_needed: { provider: 'ParcelsApp', field: 'dpdPostcode' } } } });
  const supplied = vi.spyOn(SupabaseUserClient.prototype, 'setProviderPostcode').mockResolvedValue(true);
  vi.spyOn(metrics, 'recordProviderInput').mockImplementation(() => undefined);
  expect((await call(changeCarrier, 'PATCH', { carrier: 'unknown', providerPostcode: '8000' }, staleId, '/carrier')).status).toBe(200);
  expect(supplied).toHaveBeenCalledExactlyOnceWith(keptId, '8000');
});

it('shares, reads and stops sharing the parcel a stale id was merged into', async () => {
  const link = { id: 'g8Rn3yJe2SuX', showNumber: false, gift: false, createdAt: '2026-11-01T08:00:00+00:00' };
  vi.spyOn(metrics, 'recordParcelShare').mockImplementation(() => undefined);
  const shared = vi.spyOn(SupabaseUserClient.prototype, 'sharePackage').mockResolvedValue({ ...link, created: true });
  const read = vi.spyOn(SupabaseUserClient.prototype, 'packageShare').mockResolvedValue(link);
  const stopped = vi.spyOn(SupabaseUserClient.prototype, 'stopPackageShare').mockResolvedValue(true);
  expect((await call(share, 'PUT', { gift: false }, staleId, '/share')).status).toBe(200);
  expect(shared).toHaveBeenCalledExactlyOnceWith(keptId, { gift: false });
  expect((await call(readShare, 'GET', undefined, staleId, '/share')).status).toBe(200);
  expect(read).toHaveBeenCalledExactlyOnceWith(keptId);
  expect((await call(stopSharing, 'DELETE', undefined, staleId, '/share')).status).toBe(204);
  expect(stopped).toHaveBeenCalledExactlyOnceWith(keptId);
  // A parcel the account cannot read is never asked about.
  shared.mockClear();
  expect((await call(share, 'PUT', { gift: false }, '5e000000-0000-4000-a000-000000000009', '/share')).status).toBe(404);
  expect(shared).not.toHaveBeenCalled();
});

it('follows a Live Activity started under a stale id on the parcel it was merged into', async () => {
  vi.spyOn(SupabaseServiceClient.prototype, 'getLiveActivityDevice').mockResolvedValue({ id: '5e000000-0000-4000-a000-000000000005', environment: 'production' });
  const stored = vi.spyOn(SupabaseServiceClient.prototype, 'upsertLiveActivityUpdateToken').mockResolvedValue({});
  const response = await watchLiveActivity(new NextRequest('https://delivery.example/api/live-activities/activities', {
    method: 'POST',
    headers: { Authorization: 'Bearer synthetic-token', 'Content-Type': 'application/json' },
    body: JSON.stringify({
      installationId: '5e000000-0000-4000-a000-000000000006',
      token: 'ab'.repeat(32),
      environment: 'production',
      locale: 'en',
      activityId: 'activity-1',
      parcelId: staleId,
    }),
  }), { params: Promise.resolve({}) });
  expect(response.status).toBe(201);
  expect(stored).toHaveBeenCalledExactlyOnceWith(expect.objectContaining({ packageId: keptId, activityId: 'activity-1' }));
});

it('answers 404 when the database finds no such parcel, and 502 when a function or route is missing', async () => {
  const answers: Record<string, () => Response> = {};
  vi.stubGlobal('fetch', vi.fn(async (input: string | URL) => {
    const rpc = String(input).replace(/^.*\/rpc\//, '');
    return answers[rpc]?.() ?? new Response('null', { status: 500 });
  }));
  const json = (body: unknown, status = 200) => () => new Response(JSON.stringify(body), { status, headers: { 'Content-Type': 'application/json' } });

  // Merged or deleted between the lookup and the change: the RPC changes nothing.
  answers.set_owned_package_archived = json(false);
  const gone = await call(archive, 'DELETE');
  expect(gone.status).toBe(404);
  expect(await gone.json()).toEqual({ error: 'Not found' });
  // The database's own "no such row".
  answers.rename_owned_package = json({ code: 'P0002', message: 'Package not found' }, 404);
  expect((await call(rename, 'PATCH', { label: 'Boots' })).status).toBe(404);
  // A function PostgREST does not know is an outage, not an answer.
  answers.rename_owned_package = json({ code: 'PGRST202', message: 'Could not find the function' }, 404);
  const missing = await call(rename, 'PATCH', { label: 'Boots' });
  expect(missing.status).toBe(502);
  expect(await missing.json()).toEqual({ error: 'The delivery database is temporarily unavailable' });
});
