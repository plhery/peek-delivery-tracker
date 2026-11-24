import { afterEach, beforeEach, expect, it, vi } from 'vitest';
import { WebPushNotificationService } from './push';
import { SupabaseServiceClient } from './supabase';

// A copy the carrier sends later stamped earlier repeats the scan already told (scanRepeats.ts). What counts as told
// is read for each browser: a scan another browser of the account still waits for, its push having failed, was told
// to this one.

beforeEach(() => {
  vi.spyOn(SupabaseServiceClient.prototype, 'handoffScans').mockResolvedValue([]);
  vi.spyOn(SupabaseServiceClient.prototype, 'providerCopyScans').mockResolvedValue([]);
  vi.spyOn(SupabaseServiceClient.prototype, 'recentScans').mockResolvedValue(new Map());
  vi.spyOn(SupabaseServiceClient.prototype, 'archivedPackageIds').mockResolvedValue(new Set());
});
afterEach(() => vi.restoreAllMocks());

async function scenario(secondBrowserFails: boolean): Promise<string[]> {
  const client = new SupabaseServiceClient('https://example.test', 'test');
  const service = new WebPushNotificationService(client, '', '', '', () => Date.parse('2026-11-05T12:30:00Z'));
  let failing = false;
  const sent: { sub: string; event: string }[] = [];
  vi.spyOn(service, 'send').mockImplementation(async (row) => {
    if (failing && row.subscription_id === 'sub2') throw Object.assign(new Error('push service down'), { statusCode: 500 });
    sent.push({ sub: String(row.subscription_id), event: String(row.event_id) });
  });
  vi.spyOn(client, 'recordPushDeliveries').mockResolvedValue();
  vi.spyOn(client, 'updatePushSubscription').mockResolvedValue();
  vi.spyOn(client, 'packageJoinTimes').mockResolvedValue(new Map([['pkg', '2026-11-01T08:00:00Z']]));
  const pending = vi.spyOn(client, 'listPendingPushNotifications');
  const scans = [
    { id: 'told', occurredAt: '2026-11-05T12:02:00Z', created: '2026-11-05T12:05:00Z' },
    { id: 'earlier', occurredAt: '2026-11-05T12:00:00Z', created: '2026-11-05T12:20:00Z' },
    { id: 'again', occurredAt: '2026-11-05T12:01:00Z', created: '2026-11-05T12:20:00Z' },
  ] as const;
  let stored: readonly string[] = [];
  const kept = () => scans.filter((scan) => stored.includes(scan.id)).sort((a, b) => b.occurredAt.localeCompare(a.occurredAt));
  vi.mocked(client.recentScans).mockImplementation(async () => new Map([['pkg', kept().map(({ id, occurredAt }) => ({
    id, stage: 'failed_attempt' as const, occurredAt, stageSource: 'carrier_map', description: 'Delivery attempted', location: 'Depot', source: 'jt-au',
  }))]]));
  vi.spyOn(client, 'latestScanTimes').mockImplementation(async () => new Map([['pkg', kept()[0]!.occurredAt]]));
  const row = (sub: string, id: string) => {
    const { occurredAt, created } = scans.find((scan) => scan.id === id)!;
    return { subscription_id: sub, package_id: 'pkg', event_id: id, stage: 'failed_attempt', occurred_at: occurredAt, event_created_at: created };
  };

  // First check stores "told": the owner's two browsers are told; the second's push service fails (or not).
  stored = ['told'];
  failing = secondBrowserFails;
  pending.mockResolvedValueOnce([row('sub1', 'told'), row('sub2', 'told')]);
  await service.dispatch();
  failing = false;

  // A later check stores two copies stamped a little before "told".
  stored = ['told', 'earlier', 'again'];
  sent.length = 0;
  pending.mockResolvedValueOnce([
    row('sub1', 'earlier'), row('sub1', 'again'),
    ...(secondBrowserFails ? [row('sub2', 'told')] : []), row('sub2', 'earlier'), row('sub2', 'again'),
  ]);
  await service.dispatch();
  return sent.map(({ sub, event }) => `${sub}:${event}`);
}

it('a browser told the scan hears nothing of its earlier-stamped copies', async () => {
  expect(await scenario(false)).toEqual([]);
});

it('nor when another browser of the account failed to hear it, which hears it once', async () => {
  expect(await scenario(true)).toEqual(['sub2:earlier']);
});

// A carrier's scan that took a universal provider's row of it over is stored under the provider's identity (`held`):
// it is the carrier's scan, so the carrier's own row of the same step minutes later repeats it.
async function afterHeldRow(held: boolean): Promise<string[]> {
  const client = new SupabaseServiceClient('https://example.test', 'test');
  const service = new WebPushNotificationService(client, '', '', '', () => Date.parse('2026-11-20T14:35:00Z'));
  const sent: string[] = [];
  vi.spyOn(service, 'send').mockImplementation(async (row) => { sent.push(String(row.event_id)); });
  vi.spyOn(client, 'recordPushDeliveries').mockResolvedValue();
  vi.spyOn(client, 'updatePushSubscription').mockResolvedValue();
  vi.spyOn(client, 'packageJoinTimes').mockResolvedValue(new Map([['pkg', '2026-11-01T08:00:00Z']]));
  const arrival = { stage: 'in_transit' as const, description: 'Arrived at Regional Facility', location: 'SAMPLE TOWN, ST', stageSource: 'carrier_map' };
  vi.mocked(client.recentScans).mockResolvedValue(new Map([['pkg', [
    { id: 'own', occurredAt: '2026-11-20T13:23:00Z', source: 'usps', ...arrival },
    { id: 'taken', occurredAt: '2026-11-20T13:21:00Z', source: 'unknown/17TRACK', ...arrival, ...held ? { held: true } : {} },
  ]]]));
  vi.spyOn(client, 'latestScanTimes').mockResolvedValue(new Map([['pkg', '2026-11-20T13:23:00Z']]));
  vi.spyOn(client, 'listPendingPushNotifications').mockResolvedValueOnce([{
    subscription_id: 'sub1', package_id: 'pkg', event_id: 'own', stage: 'in_transit',
    occurred_at: '2026-11-20T13:23:00Z', event_created_at: '2026-11-20T14:30:00Z',
  }]);
  await service.dispatch();
  return sent;
}

it('the carrier\'s own row of a scan its answer took over from a provider tells nothing new', async () => {
  expect(await afterHeldRow(true)).toEqual([]);
  // A provider's row the carrier's answer holds nothing of is another source's.
  expect(await afterHeldRow(false)).toEqual(['own']);
});
