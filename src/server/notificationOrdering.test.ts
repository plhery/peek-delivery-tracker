import { generateKeyPairSync } from 'node:crypto';
import { afterEach, beforeEach, expect, it, vi } from 'vitest';
import * as metrics from './metrics';
import * as observability from './observability';
import { APNsError, compareNotificationEvents, DeliveryLiveActivityNotificationService, NativePushNotificationService, WebPushNotificationService } from './push';
import { SupabaseServiceClient } from './supabase';
import type { JsonObject } from './types';

const privateKey = generateKeyPairSync('ec', { namedCurve: 'prime256v1' }).privateKey.export({ type: 'pkcs8', format: 'pem' }).toString();
beforeEach(() => {
  vi.spyOn(SupabaseServiceClient.prototype, 'handoffScans').mockResolvedValue([]);
  vi.spyOn(SupabaseServiceClient.prototype, 'providerCopyScans').mockResolvedValue([]);
  vi.spyOn(SupabaseServiceClient.prototype, 'recentScans').mockResolvedValue(new Map());
  vi.spyOn(SupabaseServiceClient.prototype, 'archivedPackageIds').mockResolvedValue(new Set());
});
afterEach(() => vi.restoreAllMocks());

/** Web Push counts milliseconds and APNs seconds. */
const clocks = (at: string) => ({ web: () => Date.parse(at), apns: () => Date.parse(at) / 1_000 });

it.each(['web', 'native', 'live'] as const)('sends the latest milestone and acknowledges the batch for %s notifications', async (channel) => {
  const client = new SupabaseServiceClient('https://example.test', 'test');
  const base = { subscription_id: 'sub', device_id: 'device', package_id: 'pkg', update_token: 'token', update_token_id: 'update', event_created_at: '2026-09-08T12:00:00Z' };
  const events = [
    { ...base, event_id: 'old', stage: 'accepted', occurred_at: '2026-09-07T09:00:00Z' },
    { ...base, event_id: 'latest', stage: 'delivered', occurred_at: '2026-09-08T11:00:00Z' },
  ];
  vi.spyOn(client, 'latestScanTimes').mockResolvedValue(new Map([['pkg', '2026-09-08T11:00:00Z']]));
  vi.spyOn(client, 'packageJoinTimes').mockResolvedValue(new Map([['pkg', '2026-09-01T08:00:00Z']]));
  const clock = clocks('2026-09-08T12:00:30Z');
  const apns = new NativePushNotificationService(client, 'team', 'key', privateKey, 'app', clock.apns);
  if (channel === 'web') {
    vi.spyOn(client, 'listPendingPushNotifications').mockResolvedValue(events);
    const ack = vi.spyOn(client, 'recordPushDeliveries').mockResolvedValue();
    vi.spyOn(client, 'updatePushSubscription').mockResolvedValue();
    const service = new WebPushNotificationService(client, '', '', '', clock.web);
    const send = vi.spyOn(service, 'send').mockResolvedValue();
    await service.dispatch();
    expect(send).toHaveBeenCalledWith(events[1]);
    expect(ack).toHaveBeenCalledWith('sub', ['old', 'latest']);
  } else if (channel === 'native') {
    vi.spyOn(client, 'listPendingNativePushNotifications').mockResolvedValue(events);
    const ack = vi.spyOn(client, 'recordNativePushDeliveries').mockResolvedValue();
    vi.spyOn(client, 'updateNativePushDevice').mockResolvedValue();
    const send = vi.spyOn(apns, 'send').mockResolvedValue();
    await apns.dispatch();
    expect(send).toHaveBeenCalledWith(events[1]);
    expect(ack).toHaveBeenCalledWith('device', ['old', 'latest']);
  } else {
    vi.spyOn(client, 'listPendingLiveActivityEvents').mockResolvedValue(events);
    const ack = vi.spyOn(client, 'recordLiveActivityDeliveries').mockResolvedValue();
    vi.spyOn(client, 'deleteLiveActivityTokenById').mockResolvedValue();
    const service = new DeliveryLiveActivityNotificationService(client, apns);
    const send = vi.spyOn(service, 'send').mockResolvedValue();
    await service.dispatch();
    expect(send).toHaveBeenCalledWith(events[1], 'end');
    expect(ack.mock.calls[0][0].map(event => event.eventId)).toEqual(['old', 'latest']);
  }
});

it('uses delivery progress for timestamp ties and numeric time for timezone offsets', () => {
  const created = '2026-09-08T12:00:00Z';
  const delivered = { stage: 'delivered', occurred_at: created, event_created_at: created, event_id: 'a' };
  const accepted = { ...delivered, stage: 'accepted', event_id: 'z' };
  expect([accepted, delivered].sort(compareNotificationEvents)[0]).toBe(delivered);
  expect([delivered, accepted].sort(compareNotificationEvents)[0]).toBe(delivered);
  expect(compareNotificationEvents({ ...accepted, occurred_at: '2026-09-08T13:00:00+02:00' }, delivered)).toBeGreaterThan(0);
  expect(compareNotificationEvents({ ...accepted, occurred_at: null }, { ...delivered, occurred_at: 'bad' })).toBeGreaterThan(0);
});

it("announces the newest of one instant and stage in its source's order, not by row id", () => {
  const created = '2026-09-08T12:00:00Z';
  const arrived = { stage: 'in_transit', occurred_at: created, event_created_at: created, event_id: 'z', tie_order: 0 };
  const departed = { ...arrived, event_id: 'a', tie_order: 1 };
  expect([arrived, departed].sort(compareNotificationEvents)[0]).toBe(departed);
  expect([departed, arrived].sort(compareNotificationEvents)[0]).toBe(departed);
  // Delivery progress still comes first, and a scan stored before the order loses to one with it.
  expect([{ ...arrived, stage: 'customs' }, departed].sort(compareNotificationEvents)[0]).toMatchObject({ stage: 'customs' });
  const stored = { ...departed, tie_order: 0 };
  expect([{ ...arrived, tie_order: null }, stored].sort(compareNotificationEvents)[0]).toBe(stored);
});

it.each(['web', 'native'] as const)('records a backfilled older scan as handled without announcing it for %s', async (channel) => {
  const client = new SupabaseServiceClient('https://example.test', 'test');
  const base = { subscription_id: 'sub', device_id: 'device', package_id: 'pkg', event_created_at: '2026-09-24T09:55:00Z' };
  // A carrier change stored the customs scan after the parcel was already delivered.
  const backfill = [{ ...base, event_id: 'customs', stage: 'customs', occurred_at: '2026-09-23T15:23:40Z' }];
  const latest = vi.spyOn(client, 'latestScanTimes').mockResolvedValue(new Map([['pkg', '2026-09-24T08:37:04Z']]));
  vi.spyOn(client, 'packageJoinTimes').mockResolvedValue(new Map([['pkg', '2026-09-01T08:00:00Z']]));
  const clock = clocks('2026-09-24T10:00:00Z');
  const apns = new NativePushNotificationService(client, 'team', 'key', privateKey, 'app', clock.apns);
  const service = channel === 'web' ? new WebPushNotificationService(client, '', '', '', clock.web) : apns;
  const send = vi.spyOn(service, 'send').mockResolvedValue();
  const ack = channel === 'web'
    ? vi.spyOn(client, 'recordPushDeliveries').mockResolvedValue()
    : vi.spyOn(client, 'recordNativePushDeliveries').mockResolvedValue();
  if (channel === 'web') {
    vi.spyOn(client, 'listPendingPushNotifications').mockResolvedValue(backfill);
    vi.spyOn(client, 'updatePushSubscription').mockResolvedValue();
  } else {
    vi.spyOn(client, 'listPendingNativePushNotifications').mockResolvedValue(backfill);
    vi.spyOn(client, 'updateNativePushDevice').mockResolvedValue();
  }
  expect(await service.dispatch()).toMatchObject({ attempted: 0, sent: 0 });
  expect(latest).toHaveBeenCalledWith(['pkg']);
  expect(send).not.toHaveBeenCalled();
  expect(ack).toHaveBeenCalledWith(channel === 'web' ? 'sub' : 'device', ['customs']);

  // The parcel's newest scan is news, and so is one within an hour of it
  // (clock skew); a failed lookup announces as before.
  latest.mockResolvedValueOnce(new Map([['pkg', '2026-09-23T15:23:40Z']]))
    .mockResolvedValueOnce(new Map([['pkg', '2026-09-23T16:15:00Z']]))
    .mockRejectedValueOnce(new Error('down'));
  await service.dispatch();
  await service.dispatch();
  await service.dispatch();
  expect(send).toHaveBeenCalledTimes(3);
});

it.each(['web', 'native'] as const)('records what a parcel already showed when it was added as handled, and announces what follows, for %s', async (channel) => {
  const client = new SupabaseServiceClient('https://example.test', 'test');
  const joined = '2026-10-05T10:00:00Z';
  const scan = (occurred_at: string, event_created_at: string, event_has_time = true) => ({
    subscription_id: 'sub', device_id: 'device', package_id: 'pkg', event_id: 'scan', stage: 'in_transit', occurred_at, event_created_at, event_has_time,
  });
  const clock = clocks('2026-10-05T12:30:00Z');
  const apns = new NativePushNotificationService(client, 'team', 'key', privateKey, 'app', clock.apns);
  const service = channel === 'web' ? new WebPushNotificationService(client, '', '', '', clock.web) : apns;
  const send = vi.spyOn(service, 'send').mockResolvedValue();
  const ack = channel === 'web' ? vi.spyOn(client, 'recordPushDeliveries').mockResolvedValue() : vi.spyOn(client, 'recordNativePushDeliveries').mockResolvedValue();
  const pending = channel === 'web' ? vi.spyOn(client, 'listPendingPushNotifications') : vi.spyOn(client, 'listPendingNativePushNotifications');
  vi.spyOn(client, 'updatePushSubscription').mockResolvedValue();
  vi.spyOn(client, 'updateNativePushDevice').mockResolvedValue();
  const joins = vi.spyOn(client, 'packageJoinTimes').mockResolvedValue(new Map([['pkg', joined]]));
  // Each batch is the parcel's newest scan, as a first history always is.
  const announced = async (event: ReturnType<typeof scan>) => {
    pending.mockResolvedValueOnce([event]);
    vi.spyOn(client, 'latestScanTimes').mockResolvedValueOnce(new Map([['pkg', event.occurred_at]]));
    send.mockClear(); ack.mockClear();
    await service.dispatch();
    expect(ack).toHaveBeenCalledExactlyOnceWith(channel === 'web' ? 'sub' : 'device', ['scan']);
    return send.mock.calls.length === 1;
  };
  // The check that runs as the parcel is added stores what the carrier already had.
  expect(await announced(scan('2026-10-04T18:00:00Z', '2026-10-05T10:00:01Z'))).toBe(false);
  expect(await announced(scan('2026-10-05T00:00:00Z', '2026-10-05T10:00:02Z', false))).toBe(false);
  // A scan from well before the add is old news whenever it arrives.
  expect(await announced(scan('2026-09-20T10:00:00Z', '2026-10-05T11:00:00Z'))).toBe(false);
  // A first history that arrives later, from the hours before the add, is news.
  expect(await announced(scan('2026-10-05T09:30:00Z', '2026-10-05T10:50:00Z'))).toBe(true);
  // A day without a clock may have ended less than a day before the add.
  expect(await announced(scan('2026-10-04T00:00:00Z', '2026-10-05T12:00:00Z', false))).toBe(true);
  // So is a scan made after the add, even one the first check stores.
  expect(await announced(scan('2026-10-05T10:02:00Z', '2026-10-05T10:03:00Z'))).toBe(true);
  // Without the join time every batch is announced, as before.
  joins.mockRejectedValueOnce(new Error('down'));
  expect(await announced(scan('2026-10-04T18:00:00Z', '2026-10-05T10:00:01Z'))).toBe(true);
  expect(joins).toHaveBeenCalledWith(['pkg']);
});

it.each(['web', 'native'] as const)('records a batch whose newest scan is more than a day old as handled without announcing it, for %s', async (channel) => {
  const client = new SupabaseServiceClient('https://example.test', 'test');
  const clock = clocks('2026-10-08T07:10:00Z');
  const apns = new NativePushNotificationService(client, 'team', 'key', privateKey, 'app', clock.apns);
  const service = channel === 'web' ? new WebPushNotificationService(client, '', '', '', clock.web) : apns;
  const send = vi.spyOn(service, 'send').mockResolvedValue();
  const ack = channel === 'web' ? vi.spyOn(client, 'recordPushDeliveries').mockResolvedValue() : vi.spyOn(client, 'recordNativePushDeliveries').mockResolvedValue();
  const pending = channel === 'web' ? vi.spyOn(client, 'listPendingPushNotifications') : vi.spyOn(client, 'listPendingNativePushNotifications');
  vi.spyOn(client, 'updatePushSubscription').mockResolvedValue();
  vi.spyOn(client, 'updateNativePushDevice').mockResolvedValue();
  vi.spyOn(client, 'packageJoinTimes').mockResolvedValue(new Map([['pkg', '2026-09-01T08:00:00Z']]));
  const announced = async (occurred_at: string, event_has_time = true) => {
    const event = {
      subscription_id: 'sub', device_id: 'device', package_id: 'pkg', event_id: 'scan', stage: 'in_transit',
      occurred_at, event_created_at: '2026-10-08T07:10:00Z', event_has_time,
    };
    pending.mockResolvedValueOnce([event]);
    vi.spyOn(client, 'latestScanTimes').mockResolvedValueOnce(new Map([['pkg', occurred_at]]));
    send.mockClear(); ack.mockClear();
    await service.dispatch();
    expect(ack).toHaveBeenCalledExactlyOnceWith(channel === 'web' ? 'sub' : 'device', ['scan']);
    return send.mock.calls.length === 1;
  };
  // A leg a provider reports days late is the parcel's newest scan, but no longer news.
  expect(await announced('2026-10-06T14:01:20Z')).toBe(false);
  expect(await announced('2026-10-07T07:20:00Z')).toBe(true);
  // A day without a clock has no age to judge.
  expect(await announced('2026-10-05T00:00:00Z', false)).toBe(true);
});

it.each(['web', 'native'] as const)('records the scans of an archived parcel as handled without announcing them, for %s', async (channel) => {
  const client = new SupabaseServiceClient('https://example.test', 'test');
  const clock = clocks('2026-10-08T07:10:00Z');
  const apns = new NativePushNotificationService(client, 'team', 'key', privateKey, 'app', clock.apns);
  const service = channel === 'web' ? new WebPushNotificationService(client, '', '', '', clock.web) : apns;
  const send = vi.spyOn(service, 'send').mockResolvedValue();
  const ack = channel === 'web' ? vi.spyOn(client, 'recordPushDeliveries').mockResolvedValue() : vi.spyOn(client, 'recordNativePushDeliveries').mockResolvedValue();
  const pending = channel === 'web' ? vi.spyOn(client, 'listPendingPushNotifications') : vi.spyOn(client, 'listPendingNativePushNotifications');
  vi.spyOn(client, 'updatePushSubscription').mockResolvedValue();
  vi.spyOn(client, 'updateNativePushDevice').mockResolvedValue();
  vi.spyOn(client, 'packageJoinTimes').mockResolvedValue(new Map([['pkg', '2026-09-01T08:00:00Z']]));
  vi.spyOn(client, 'latestScanTimes').mockResolvedValue(new Map([['pkg', '2026-10-08T07:00:00Z']]));
  const archived = vi.mocked(client.archivedPackageIds).mockResolvedValue(new Set(['pkg']));
  // Put away while it waited at a pickup point, the parcel is collected: its daily check says nothing.
  pending.mockResolvedValue([{
    subscription_id: 'sub', device_id: 'device', package_id: 'pkg', event_id: 'collected', stage: 'delivered',
    occurred_at: '2026-10-08T07:00:00Z', event_created_at: '2026-10-08T07:10:00Z', event_has_time: true,
  }]);
  expect(await service.dispatch()).toMatchObject({ attempted: 0, sent: 0 });
  expect(archived).toHaveBeenCalledWith(['pkg']);
  expect(send).not.toHaveBeenCalled();
  expect(ack).toHaveBeenCalledExactlyOnceWith(channel === 'web' ? 'sub' : 'device', ['collected']);
  // On the list it is news, and so it is when the lookup fails.
  archived.mockResolvedValueOnce(new Set()).mockRejectedValueOnce(new Error('down'));
  await service.dispatch();
  await service.dispatch();
  expect(send).toHaveBeenCalledTimes(2);
});

it.each(['web', 'native'] as const)('records the row of a relay pair that reached the parcel second as handled without announcing it, for %s', async (channel) => {
  const client = new SupabaseServiceClient('https://example.test', 'test');
  const clock = clocks('2026-09-09T15:45:00Z');
  const apns = new NativePushNotificationService(client, 'team', 'key', privateKey, 'app', clock.apns);
  const service = channel === 'web' ? new WebPushNotificationService(client, '', '', '', clock.web) : apns;
  const send = vi.spyOn(service, 'send').mockResolvedValue();
  const ack = channel === 'web' ? vi.spyOn(client, 'recordPushDeliveries').mockResolvedValue() : vi.spyOn(client, 'recordNativePushDeliveries').mockResolvedValue();
  const pending = channel === 'web' ? vi.spyOn(client, 'listPendingPushNotifications') : vi.spyOn(client, 'listPendingNativePushNotifications');
  vi.spyOn(client, 'updatePushSubscription').mockResolvedValue();
  vi.spyOn(client, 'updateNativePushDevice').mockResolvedValue();
  vi.spyOn(client, 'packageJoinTimes').mockResolvedValue(new Map([['pkg', '2026-09-01T08:00:00Z']]));
  vi.spyOn(client, 'latestScanTimes').mockResolvedValue(new Map([['pkg', '2026-09-09T15:40:38Z']]));
  // DHL handed the parcel over to Swiss Post and tells its scans again.
  const stored = (id: string, source: string, stage: string, occurred_at: string, created_at: string) => ({
    id, stage, occurred_at, created_at, provider_event_id: `${source}:${id}`,
  });
  const scans = [
    // Swiss Post's scan was announced; DHL's copy comes with a later check.
    stored('arrived', 'swiss-post', 'in_transit', '2026-09-09T15:10:51Z', '2026-09-09T15:12:00Z'),
    stored('arrived-copy', 'dhl', 'in_transit', '2026-09-09T15:10:00Z', '2026-09-09T15:30:00Z'),
    // DHL's copy came first, then Swiss Post's own, with a scan DHL never told.
    stored('sorted-copy', 'dhl', 'in_transit', '2026-09-09T15:40:00Z', '2026-09-09T15:41:00Z'),
    stored('sorted', 'swiss-post', 'in_transit', '2026-09-09T15:40:38Z', '2026-09-09T15:44:00Z'),
    stored('customs', 'swiss-post', 'customs', '2026-09-09T15:20:00Z', '2026-09-09T15:44:00Z'),
  ];
  const handoffs = vi.mocked(client.handoffScans).mockResolvedValue([{
    id: 'pkg', carrier: 'dhl', carrier_data: { original_carrier: 'dhl', active_tracking_carrier: 'swiss-post' }, tracking_events: scans,
  }]);
  const dispatched = async (...ids: string[]) => {
    pending.mockResolvedValueOnce(ids.map((id) => {
      const { stage, occurred_at, created_at } = scans.find((scan) => scan.id === id)!;
      return { subscription_id: 'sub', device_id: 'device', package_id: 'pkg', event_id: id, stage, occurred_at, event_created_at: created_at };
    }));
    send.mockClear(); ack.mockClear();
    await service.dispatch();
    expect(ack).toHaveBeenCalledExactlyOnceWith(channel === 'web' ? 'sub' : 'device', ids);
    return send.mock.calls.map(([event]) => event.event_id);
  };
  // Within an hour of the parcel's newest scan, yet no news.
  expect(await dispatched('arrived-copy')).toEqual([]);
  expect(handoffs).toHaveBeenCalledWith(['pkg']);
  expect(await dispatched('sorted')).toEqual([]);
  // The rest of the batch is announced as ever, and so is the row that came first.
  expect(await dispatched('sorted', 'customs')).toEqual(['customs']);
  expect(await dispatched('sorted-copy')).toEqual(['sorted-copy']);
  // A failed lookup announces as before.
  handoffs.mockRejectedValueOnce(new Error('down'));
  expect(await dispatched('sorted')).toEqual(['sorted']);
});

it.each(['web', 'native'] as const)('records a provider\'s copy the timeline leaves out as handled without announcing it, for %s', async (channel) => {
  const client = new SupabaseServiceClient('https://example.test', 'test');
  const clock = clocks('2026-10-08T15:26:00Z');
  const apns = new NativePushNotificationService(client, 'team', 'key', privateKey, 'app', clock.apns);
  const service = channel === 'web' ? new WebPushNotificationService(client, '', '', '', clock.web) : apns;
  const send = vi.spyOn(service, 'send').mockResolvedValue();
  const ack = channel === 'web' ? vi.spyOn(client, 'recordPushDeliveries').mockResolvedValue() : vi.spyOn(client, 'recordNativePushDeliveries').mockResolvedValue();
  const pending = channel === 'web' ? vi.spyOn(client, 'listPendingPushNotifications') : vi.spyOn(client, 'listPendingNativePushNotifications');
  vi.spyOn(client, 'updatePushSubscription').mockResolvedValue();
  vi.spyOn(client, 'updateNativePushDevice').mockResolvedValue();
  vi.spyOn(client, 'packageJoinTimes').mockResolvedValue(new Map([['pkg', '2026-10-01T08:00:00Z']]));
  vi.spyOn(client, 'latestScanTimes').mockResolvedValue(new Map([['pkg', '2026-10-08T15:20:00Z']]));
  // Posti's scan was announced; while its lookup was down, a provider stored it again an hour off, in its own words.
  const stored = (id: string, source: string, stage: string, occurred_at: string, created_at: string, extra = {}) => ({
    id, stage, occurred_at, created_at, provider_event_id: `${source}:${id}`, ...extra,
  });
  const scans = [
    stored('sorted', 'posti', 'in_transit', '2026-10-08T13:20:00Z', '2026-10-08T13:30:00Z'),
    stored('copy', 'unknown', 'in_transit', '2026-10-08T14:20:00Z', '2026-10-08T15:25:00Z', { universal_provider: 'ParcelsApp' }),
    stored('out', 'unknown', 'out_for_delivery', '2026-10-08T15:20:00Z', '2026-10-08T15:25:00Z', { universal_provider: 'ParcelsApp' }),
  ];
  const copies = vi.mocked(client.providerCopyScans).mockResolvedValue([{
    id: 'pkg', carrier_data: { routing: { confirmed_carrier: 'posti' } }, tracking_events: scans,
  }]);
  const dispatched = async (...ids: string[]) => {
    pending.mockResolvedValueOnce(ids.map((id) => {
      const { stage, occurred_at, created_at } = scans.find((scan) => scan.id === id)!;
      return { subscription_id: 'sub', device_id: 'device', package_id: 'pkg', event_id: id, stage, occurred_at, event_created_at: created_at };
    }));
    send.mockClear(); ack.mockClear();
    await service.dispatch();
    expect(ack).toHaveBeenCalledExactlyOnceWith(channel === 'web' ? 'sub' : 'device', ids);
    return send.mock.calls.map(([event]) => event.event_id);
  };
  expect(await dispatched('copy')).toEqual([]);
  expect(copies).toHaveBeenLastCalledWith(['copy']);
  // The provider's own news in the same batch is announced as ever.
  expect(await dispatched('copy', 'out')).toEqual(['out']);
  // A failed lookup announces as before.
  copies.mockRejectedValueOnce(new Error('down'));
  expect(await dispatched('copy')).toEqual(['copy']);
});

it('moves no Live Activity for a provider\'s copy the timeline leaves out', async () => {
  const client = new SupabaseServiceClient('https://example.test', 'test');
  const apns = new NativePushNotificationService(client, 'team', 'key', privateKey, 'app', clocks('2026-10-08T09:06:00Z').apns);
  const service = new DeliveryLiveActivityNotificationService(client, apns);
  const send = vi.spyOn(service, 'send').mockResolvedValue();
  const ack = vi.spyOn(client, 'recordLiveActivityDeliveries').mockResolvedValue();
  vi.spyOn(client, 'updateLiveActivityToken').mockResolvedValue();
  vi.spyOn(client, 'deleteLiveActivityTokenById').mockResolvedValue();
  const pending = vi.spyOn(client, 'listPendingLiveActivityEvents');
  // The activity runs on the carrier's out for delivery; a provider stores the scan again, read as in transit.
  const stored = (id: string, source: string, stage: string, occurred_at: string, extra = {}) => ({
    id, stage, occurred_at, created_at: '2026-10-08T09:05:00Z', provider_event_id: `${source}:${id}`, ...extra,
  });
  const scans = [
    stored('out', 'posti', 'out_for_delivery', '2026-10-08T08:40:00Z'),
    stored('copy', 'unknown', 'in_transit', '2026-10-08T08:40:21Z', { universal_provider: '17TRACK' }),
    stored('delivered', 'posti', 'delivered', '2026-10-08T09:02:00Z'),
  ];
  const copies = vi.mocked(client.providerCopyScans).mockResolvedValue([{
    id: 'pkg', carrier_data: { routing: { confirmed_carrier: 'posti' } }, tracking_events: scans,
  }]);
  const dispatched = async (...ids: string[]) => {
    pending.mockResolvedValueOnce(ids.map((id) => {
      const { stage, occurred_at, created_at } = scans.find((scan) => scan.id === id)!;
      return { device_id: 'device', package_id: 'pkg', update_token: 'token', update_token_id: 'update',
        event_id: id, stage, occurred_at, event_created_at: created_at };
    }));
    send.mockClear(); ack.mockClear();
    await service.dispatch();
    expect(ack.mock.calls.flatMap(([rows]) => rows.map((row) => row.eventId))).toEqual(ids);
    return [send.mock.calls.map(([event, kind]) => [event.event_id, kind]), ack.mock.calls[0]![0][0]!.deliveryKind];
  };
  expect(await dispatched('copy')).toEqual([[], 'update']);
  // The carrier's own news in the same batch moves it as ever.
  expect(await dispatched('copy', 'delivered')).toEqual([[['delivered', 'end']], 'end']);
  // A failed lookup moves it as before.
  copies.mockRejectedValueOnce(new Error('down'));
  expect(await dispatched('copy')).toEqual([[['copy', 'end']], 'end']);
});

it.each(['web', 'native'] as const)('records an earlier carrier\'s scan told after a later one as handled without announcing it, for %s', async (channel) => {
  const client = new SupabaseServiceClient('https://example.test', 'test');
  const clock = clocks('2026-10-08T15:26:00Z');
  const apns = new NativePushNotificationService(client, 'team', 'key', privateKey, 'app', clock.apns);
  const service = channel === 'web' ? new WebPushNotificationService(client, '', '', '', clock.web) : apns;
  const send = vi.spyOn(service, 'send').mockResolvedValue();
  const ack = channel === 'web' ? vi.spyOn(client, 'recordPushDeliveries').mockResolvedValue() : vi.spyOn(client, 'recordNativePushDeliveries').mockResolvedValue();
  const pending = channel === 'web' ? vi.spyOn(client, 'listPendingPushNotifications') : vi.spyOn(client, 'listPendingNativePushNotifications');
  vi.spyOn(client, 'updatePushSubscription').mockResolvedValue();
  vi.spyOn(client, 'updateNativePushDevice').mockResolvedValue();
  vi.spyOn(client, 'packageJoinTimes').mockResolvedValue(new Map([['pkg', '2026-10-01T08:00:00Z']]));
  vi.spyOn(client, 'latestScanTimes').mockResolvedValue(new Map([['pkg', '2026-10-08T14:40:00Z']]));
  // Chronopost handed the parcel over to DPD Germany, and is asked again for its late scans.
  const stored = (id: string, source: string, stage: string, occurred_at: string, created_at: string) => ({
    id, stage, occurred_at, created_at, provider_event_id: `${source}:${id}`,
  });
  const scans = [
    // DPD's scan was announced; Chronopost's scan from before it comes with a later check.
    stored('received', 'dpd-de', 'in_transit', '2026-10-08T14:24:00Z', '2026-10-08T14:30:00Z'),
    stored('late', 'chronopost', 'in_transit', '2026-10-08T14:12:00Z', '2026-10-08T15:25:00Z'),
    stored('newer', 'chronopost', 'out_for_delivery', '2026-10-08T14:40:00Z', '2026-10-08T15:25:00Z'),
  ];
  const handoffs = vi.mocked(client.handoffScans).mockResolvedValue([{
    id: 'pkg', carrier: 'chronopost', carrier_data: { original_carrier: 'chronopost', active_tracking_carrier: 'dpd-de' }, tracking_events: scans,
  }]);
  const dispatched = async (...ids: string[]) => {
    pending.mockResolvedValueOnce(ids.map((id) => {
      const { stage, occurred_at, created_at } = scans.find((scan) => scan.id === id)!;
      return { subscription_id: 'sub', device_id: 'device', package_id: 'pkg', event_id: id, stage, occurred_at, event_created_at: created_at };
    }));
    send.mockClear(); ack.mockClear();
    await service.dispatch();
    expect(ack).toHaveBeenCalledExactlyOnceWith(channel === 'web' ? 'sub' : 'device', ids);
    return send.mock.calls.map(([event]) => event.event_id);
  };
  // Within an hour of the parcel's newest scan, yet older than one it had announced.
  expect(await dispatched('late')).toEqual([]);
  // A scan newer than everything stored before it is announced as ever.
  expect(await dispatched('late', 'newer')).toEqual(['newer']);
  expect(await dispatched('newer')).toEqual(['newer']);
  // A failed lookup announces as before.
  handoffs.mockRejectedValueOnce(new Error('down'));
  expect(await dispatched('late')).toEqual(['late']);
});

it.each(['web', 'native'] as const)('counts each batch by what became of it, for %s notifications', async (channel) => {
  const client = new SupabaseServiceClient('https://example.test', 'test');
  const clock = clocks('2026-10-08T12:00:30Z');
  const apns = new NativePushNotificationService(client, 'team', 'key', privateKey, 'app', clock.apns);
  const service = channel === 'web' ? new WebPushNotificationService(client, '', '', '', clock.web) : apns;
  const batch = (to: string, package_id: string, occurred_at: string) => ({
    subscription_id: to, device_id: to, package_id, event_id: `${to}-${package_id}`, stage: 'in_transit', occurred_at, event_created_at: '2026-10-08T12:00:00Z',
  });
  const pending = [
    batch('sent', 'pkg', '2026-10-08T11:00:00Z'),
    // A backfilled scan, hours older than the parcel's newest.
    batch('sent', 'backfilled', '2026-10-08T03:00:00Z'),
    batch('gone', 'pkg', '2026-10-08T11:00:00Z'),
    batch('down', 'pkg', '2026-10-08T11:00:00Z'),
  ];
  vi.spyOn(client, 'latestScanTimes').mockResolvedValue(new Map([['pkg', '2026-10-08T11:00:00Z'], ['backfilled', '2026-10-08T11:00:00Z']]));
  vi.spyOn(client, 'packageJoinTimes').mockResolvedValue(new Map([['pkg', '2026-10-01T08:00:00Z'], ['backfilled', '2026-10-01T08:00:00Z']]));
  vi.spyOn(client, channel === 'web' ? 'listPendingPushNotifications' : 'listPendingNativePushNotifications').mockResolvedValue(pending);
  vi.spyOn(client, channel === 'web' ? 'recordPushDeliveries' : 'recordNativePushDeliveries').mockResolvedValue();
  vi.spyOn(client, channel === 'web' ? 'updatePushSubscription' : 'updateNativePushDevice').mockResolvedValue();
  const gone = channel === 'web' ? Object.assign(new Error('Gone'), { statusCode: 410 }) : new APNsError(410, 'Unregistered');
  const down = channel === 'web' ? Object.assign(new Error('Unavailable'), { statusCode: 503 }) : new APNsError(503, 'ServiceUnavailable');
  vi.spyOn(service, 'send').mockImplementation(async (row: JsonObject) => {
    if (row.subscription_id === 'gone') throw gone;
    if (row.subscription_id === 'down') throw down;
  });
  vi.spyOn(observability, 'logOperationalEvent').mockReturnValue(undefined);
  const counted = vi.spyOn(metrics, 'recordAccountPushSent');
  expect(await service.dispatch()).toEqual({ attempted: 3, sent: 1, failed: 1, expired: 1 });
  const app = channel === 'web' ? 'web' : 'ios';
  expect([...counted.mock.calls].sort()).toEqual([['expired', app], ['failed', app], ['sent', app], ['skipped', app]]);
});
