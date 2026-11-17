import { NextRequest } from 'next/server';
import { afterEach, beforeEach, expect, it, vi } from 'vitest';
import { GET, POST } from '../../app/api/packages/route';
import { SupabaseAuthenticator } from './auth';
import * as observability from './observability';
import { SupabaseClient, SupabaseError, SupabaseServiceClient, SupabaseUserClient } from './supabase';

// Synthetic identifiers only.
const userId = '55000000-0000-4000-a000-000000000001';
const parcel = { id: '55000000-0000-4000-a000-000000000002', tracking_number: 'TEST1234', events: [] };

function list(query = '') {
  return GET(new NextRequest(`https://delivery.example/api/packages${query}`, {
    headers: { Authorization: 'Bearer list-test' },
  }), { params: Promise.resolve({}) });
}

function add() {
  return POST(new NextRequest('https://delivery.example/api/packages', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json', Authorization: 'Bearer add-test' },
    body: JSON.stringify({ trackingNumber: parcel.tracking_number, carrier: 'swiss-post' }),
  }), { params: Promise.resolve({}) });
}

beforeEach(() => {
  vi.stubEnv('SUPABASE_URL', 'https://database.example');
  vi.stubEnv('SUPABASE_PUBLISHABLE_KEY', 'test-public');
  vi.stubEnv('SUPABASE_SERVICE_ROLE_KEY', 'test-service');
  vi.spyOn(console, 'log').mockImplementation(() => undefined);
  vi.spyOn(console, 'error').mockImplementation(() => undefined);
  vi.spyOn(SupabaseAuthenticator.prototype, 'validate').mockResolvedValue({ id: userId, email: null, authenticatedAt: null, sessionId: null });
});
afterEach(() => { vi.restoreAllMocks(); vi.unstubAllEnvs(); });

it('records that the account read its parcels, which keeps them on the full schedule', async () => {
  const read = vi.spyOn(SupabaseUserClient.prototype, 'listPackages').mockResolvedValue([parcel]);
  const recorded = vi.spyOn(SupabaseUserClient.prototype, 'recordOpened').mockResolvedValue(undefined);
  const response = await list('?includeArchived=true');
  expect(response.status).toBe(200);
  expect((await response.json()).packages).toMatchObject([{ id: parcel.id }]);
  expect(read).toHaveBeenCalledExactlyOnceWith(true);
  expect(recorded).toHaveBeenCalledOnce();
});

it('does not record a read from an app or page out of sight', async () => {
  vi.spyOn(SupabaseUserClient.prototype, 'listPackages').mockResolvedValue([parcel]);
  const recorded = vi.spyOn(SupabaseUserClient.prototype, 'recordOpened').mockResolvedValue(undefined);
  const response = await list('?includeArchived=true&hidden=true');
  expect(response.status).toBe(200);
  expect((await response.json()).packages).toMatchObject([{ id: parcel.id }]);
  expect(recorded).not.toHaveBeenCalled();
});

it('still lists the parcels when the read cannot be recorded', async () => {
  vi.spyOn(SupabaseUserClient.prototype, 'listPackages').mockResolvedValue([parcel]);
  const failure = new SupabaseError('function unavailable');
  vi.spyOn(SupabaseUserClient.prototype, 'recordOpened').mockRejectedValue(failure);
  const report = vi.spyOn(observability, 'captureOperationalError').mockReturnValue(null);
  const response = await list();
  expect(response.status).toBe(200);
  expect((await response.json()).packages).toHaveLength(1);
  expect(report).toHaveBeenCalledExactlyOnceWith(failure, { component: 'packages', operation: 'record_account_opened' });
});

it('serves the carrier\'s scans without "Tracking added" once they reach back to it, nor any source', async () => {
  const event = { package_id: parcel.id, location: null, point: null };
  vi.spyOn(SupabaseUserClient.prototype, 'listPackages').mockResolvedValue([{ ...parcel, carrier: 'swiss-post', tracking_events: [
    { ...event, id: 'added', stage: 'pending', description: 'Tracking added', occurred_at: '2026-10-02T08:00:00+00:00', provider_event_id: 'app:pending' },
    { ...event, id: 'delivered', stage: 'delivered', description: 'Delivered', occurred_at: '2026-10-01T15:00:00+00:00', provider_event_id: 'swiss-post:a' },
  ] }]);
  vi.spyOn(SupabaseUserClient.prototype, 'recordOpened').mockResolvedValue(undefined);
  const [served] = (await (await list()).json()).packages;
  expect(served.tracking_events).toEqual([expect.objectContaining({ id: 'delivered', place: null })]);
  expect(JSON.stringify(served)).not.toContain('provider_event_id');
});

it('answers 409 with the parcel when a second add of it races the first', async () => {
  // The routes' database client may come from another bundle, with its own copy of the error class.
  vi.resetModules();
  const { SupabaseError: OtherCopy } = await import('./supabase');
  expect(OtherCopy).not.toBe(SupabaseError);
  vi.spyOn(SupabaseServiceClient.prototype, 'claimAccountTracking').mockResolvedValue({ allowed: true, scope: null, overallUsed: 1 });
  vi.spyOn(SupabaseServiceClient.prototype, 'createAccountPackage')
    .mockResolvedValueOnce(parcel.id)
    .mockRejectedValueOnce(new OtherCopy('Supabase POST request failed (409)', 409, '23505'));
  vi.spyOn(SupabaseServiceClient.prototype, 'updatePackage').mockResolvedValue(undefined);
  vi.spyOn(SupabaseServiceClient.prototype, 'enqueueSyncJob').mockResolvedValue({ row: { id: 'job' }, queued: true });
  vi.spyOn(SupabaseUserClient.prototype, 'getPackage').mockResolvedValue(parcel);
  vi.spyOn(SupabaseUserClient.prototype, 'getPackageByTrackingNumber').mockResolvedValue(parcel);
  const [first, second] = await Promise.all([add(), add()]);
  expect(first.status).toBe(201);
  expect(second.status).toBe(409);
  expect(await second.json()).toEqual({ error: 'This tracking number is already in your delivery box', packageId: parcel.id });
});

it('adds and lists a review demo number in a listed account, with no allowance, check or carrier asked', async () => {
  vi.stubEnv('REVIEW_DEMO_ACCOUNTS', userId);
  const demo = { ...parcel, tracking_number: 'TESTOUT01', carrier: 'swiss-post', label: 'IKEA', carrier_data: {}, tracking_events: [] };
  const allowance = vi.spyOn(SupabaseServiceClient.prototype, 'claimAccountTracking');
  const create = vi.spyOn(SupabaseServiceClient.prototype, 'createAccountPackage').mockResolvedValue(parcel.id);
  const queue = vi.spyOn(SupabaseServiceClient.prototype, 'enqueueSyncJob');
  vi.spyOn(SupabaseClient.prototype, 'getPackage').mockResolvedValue(demo);
  vi.spyOn(SupabaseClient.prototype, 'listPackages').mockResolvedValue([demo]);
  vi.spyOn(SupabaseUserClient.prototype, 'recordOpened').mockResolvedValue(undefined);
  const added = await POST(new NextRequest('https://delivery.example/api/packages', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json', Authorization: 'Bearer add-test' },
    body: JSON.stringify({ trackingNumber: 'TEST-OUT-01', label: 'IKEA', carrier: 'unknown' }),
  }), { params: Promise.resolve({}) });
  expect(added.status).toBe(201);
  const body = await added.json();
  expect(body.jobIds).toEqual([]);
  expect(body.package.tracking_events.at(-1)).toMatchObject({ stage: 'out_for_delivery', description: 'Out for delivery' });
  expect(create).toHaveBeenCalledExactlyOnceWith(userId, 'TESTOUT01', 'IKEA', 'swiss-post', null, null);
  expect(allowance).not.toHaveBeenCalled();
  expect(queue).not.toHaveBeenCalled();
  const [listed] = (await (await list()).json()).packages;
  expect(listed).toMatchObject({ label: 'IKEA', sync_status: 'ok', last_status_text: 'Out for delivery' });
  expect(listed.tracking_events).toHaveLength(5);
});
