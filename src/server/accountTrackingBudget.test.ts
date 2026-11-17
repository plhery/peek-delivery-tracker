import { afterEach, beforeEach, expect, it, vi } from 'vitest';
import { NextRequest } from 'next/server';
import { POST as add } from '../../app/api/packages/route';
import { POST as detect } from '../../app/api/carriers/detect/route';
import { claimAccountTracking } from './accountTrackingBudget';
import { SupabaseAuthenticator } from './auth';
import * as observability from './observability';
import { SupabaseServiceClient } from './supabase';
import * as amazon from './amazonShippingEligibility';

const userId = 'e8000000-0000-4000-8000-000000000001';
beforeEach(() => {
  vi.stubEnv('SUPABASE_URL', 'https://database.example');
  vi.stubEnv('SUPABASE_PUBLISHABLE_KEY', 'public-key');
  vi.stubEnv('SUPABASE_SERVICE_ROLE_KEY', 'test-service-key');
  vi.spyOn(SupabaseAuthenticator.prototype, 'validate').mockResolvedValue({ id: userId, email: null, authenticatedAt: null, sessionId: null });
  vi.spyOn(console, 'log').mockImplementation(() => undefined);
  vi.spyOn(SupabaseServiceClient.prototype, 'claimAllowanceReport').mockResolvedValue(true);
});
afterEach(() => { vi.useRealTimers(); vi.restoreAllMocks(); vi.unstubAllEnvs(); });

it('claims configurable daily and hourly budgets and returns Retry-After on exhaustion', async () => {
  vi.useFakeTimers({ toFake: ['Date'], now: new Date('2026-11-04T10:45:00Z') });
  const claim = vi.spyOn(SupabaseServiceClient.prototype, 'claimAccountTracking').mockResolvedValue({ allowed: true, scope: null, overallUsed: 1 });
  const client = new SupabaseServiceClient('https://database.example', 'test-key');
  await claimAccountTracking(client, userId, 'lookup');
  expect(claim).toHaveBeenCalledWith(userId, 'lookup', { perAccount: 15, overall: 50 });
  vi.stubEnv('ACCOUNT_DETECTIONS_PER_DAY', '12');
  vi.stubEnv('ACCOUNT_DETECTIONS_PER_HOUR', '0');
  claim.mockResolvedValue({ allowed: false, scope: 'account', overallUsed: 0 });
  await expect(claimAccountTracking(client, userId, 'detection')).rejects.toMatchObject({ status: 429, headers: { 'Retry-After': String(13 * 3_600 + 15 * 60) } });
  expect(claim).toHaveBeenLastCalledWith(userId, 'detection', { perAccount: 12, overall: 0 });
  // Every account's allowance for the hour is used up: until the next hour.
  claim.mockResolvedValue({ allowed: false, scope: 'overall', overallUsed: 50 });
  await expect(claimAccountTracking(client, userId, 'lookup')).rejects.toMatchObject({ status: 429, headers: { 'Retry-After': '900' } });
  await expect(claimAccountTracking(client, userId, 'lookup')).rejects.toMatchObject({ scope: undefined });
});

it('says once an hour that every account’s allowance is running out, and that it is used up', async () => {
  vi.useFakeTimers({ toFake: ['Date'], now: new Date('2026-11-05T10:00:00Z') });
  const report = vi.spyOn(observability, 'captureOverallAllowance').mockReturnValue(null);
  const claim = vi.spyOn(SupabaseServiceClient.prototype, 'claimAccountTracking').mockResolvedValue({ allowed: true, scope: null, overallUsed: 24 });
  const client = new SupabaseServiceClient('https://database.example', 'test-key');
  await claimAccountTracking(client, userId, 'lookup');
  expect(report).not.toHaveBeenCalled();
  claim.mockResolvedValue({ allowed: true, scope: null, overallUsed: 25 });
  await claimAccountTracking(client, userId, 'lookup');
  await claimAccountTracking(client, userId, 'lookup');
  expect(report.mock.calls).toEqual([['account', 'lookup', 'running_out', { used: 25, limit: 50 }, new Date('2026-11-05T10:00:00Z')]]);
  // An account at its own daily limit is not every account's allowance running out.
  claim.mockResolvedValue({ allowed: false, scope: 'account', overallUsed: 30 });
  await expect(claimAccountTracking(client, userId, 'lookup')).rejects.toMatchObject({ status: 429 });
  expect(report).toHaveBeenCalledTimes(1);
  claim.mockResolvedValue({ allowed: false, scope: 'overall', overallUsed: 50 });
  await expect(claimAccountTracking(client, userId, 'lookup')).rejects.toMatchObject({ status: 429 });
  await expect(claimAccountTracking(client, userId, 'lookup')).rejects.toMatchObject({ status: 429 });
  expect(report.mock.calls.at(-1)).toEqual(['account', 'lookup', 'used_up', { used: 50, limit: 50 }, new Date('2026-11-05T10:00:00Z')]);
  expect(report).toHaveBeenCalledTimes(2);
  // The next hour it is said again.
  vi.setSystemTime(new Date('2026-11-05T11:00:01Z'));
  await expect(claimAccountTracking(client, userId, 'lookup')).rejects.toMatchObject({ status: 429 });
  expect(report).toHaveBeenCalledTimes(3);
});

it('only logs an hour whose allowance lasts at its pace, and says it is running out once the pace would use it up', async () => {
  vi.useFakeTimers({ toFake: ['Date'], now: new Date('2026-11-05T15:42:00Z') });
  const report = vi.spyOn(observability, 'captureOverallAllowance').mockReturnValue(null);
  const log = vi.spyOn(observability, 'logOperationalEvent').mockReturnValue(undefined);
  const claim = vi.spyOn(SupabaseServiceClient.prototype, 'claimAccountTracking').mockResolvedValue({ allowed: true, scope: null, overallUsed: 25 });
  const client = new SupabaseServiceClient('https://database.example', 'test-key');
  // Half of it by 15:42 is about 36 of 50 by the hour's end.
  await claimAccountTracking(client, userId, 'lookup');
  claim.mockResolvedValue({ allowed: true, scope: null, overallUsed: 26 });
  await claimAccountTracking(client, userId, 'lookup');
  expect(report).not.toHaveBeenCalled();
  expect(SupabaseServiceClient.prototype.claimAllowanceReport).not.toHaveBeenCalled();
  expect(log.mock.calls).toEqual([['account_allowance', { kind: 'lookup', state: 'running_out', used: 25, limit: 50, forecast: 36 }, 'info']]);
  // At 45 by 15:50 it is used up before the hour is over.
  vi.setSystemTime(new Date('2026-11-05T15:50:00Z'));
  claim.mockResolvedValue({ allowed: true, scope: null, overallUsed: 45 });
  await claimAccountTracking(client, userId, 'lookup');
  await claimAccountTracking(client, userId, 'lookup');
  expect(report.mock.calls).toEqual([['account', 'lookup', 'running_out', { used: 45, limit: 50 }, new Date('2026-11-05T15:50:00Z')]]);
  expect(SupabaseServiceClient.prototype.claimAllowanceReport).toHaveBeenCalledExactlyOnceWith('account:lookup:running_out', '2026-11-05T15');
  expect(log.mock.calls.at(-1)).toEqual(['account_allowance', { kind: 'lookup', state: 'running_out', used: 45, limit: 50, forecast: 54 }, 'warning']);
});

it('refuses additions and provider detection before spending provider resources', async () => {
  vi.spyOn(SupabaseServiceClient.prototype, 'claimAccountTracking').mockResolvedValue({ allowed: false, scope: 'account', overallUsed: 0 });
  const verify = vi.spyOn(amazon, 'verifyAmazonShippingAddition');
  const check = vi.spyOn(amazon, 'checkAmazonShipping');
  const create = vi.spyOn(SupabaseServiceClient.prototype, 'createAccountPackage');
  for (const [handler, path] of [[add, 'packages'], [detect, 'carriers/detect']] as const) {
    const result = await handler(new NextRequest(`https://peek.example/api/${path}`, {
      method: 'POST', headers: { 'Content-Type': 'application/json', Authorization: 'Bearer synthetic-token' },
      body: JSON.stringify({ trackingNumber: 'FR0000000099', carrier: 'amazon-shipping' }),
    }), { params: Promise.resolve({}) });
    expect(result.status).toBe(429);
    // The day's allowance says so, for the app to say until when.
    expect(await result.json()).toEqual({ error: 'Today’s tracking allowance is used up. Try again tomorrow.', scope: 'daily' });
  }
  expect(verify).not.toHaveBeenCalled();
  expect(check).not.toHaveBeenCalled();
  expect(create).not.toHaveBeenCalled();
});
