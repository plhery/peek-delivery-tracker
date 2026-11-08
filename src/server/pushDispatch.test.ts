import { generateKeyPairSync } from 'node:crypto';
import { afterEach, describe, expect, it, vi } from 'vitest';
import * as observability from './observability';
import {
  CompositePushNotificationService,
  DeliveryLiveActivityNotificationService,
  FcmPushNotificationService,
  NativePushNotificationService,
  WebPushNotificationService,
} from './push';
import type { JsonObject } from './types';
import { WORKER_LEASE_CONFIRMED_MS, WORKER_LEASE_RENEW_MS, WORKER_LEASE_SECONDS, WorkerLease, WorkerLeaseLost } from './workerLease';

const now = Date.parse('2026-09-07T12:40:00Z');
const apnsKey = generateKeyPairSync('ec', { namedCurve: 'prime256v1' }).privateKey.export({ type: 'pkcs8', format: 'pem' }).toString();
const fcmKey = generateKeyPairSync('rsa', { modulusLength: 2048 }).privateKey.export({ type: 'pkcs8', format: 'pem' }).toString();

const scan = (eventId: string, packageId: string): JsonObject => ({
  subscription_id: 'subscription-1', device_id: 'device-1', package_id: packageId, event_id: eventId, stage: 'in_transit',
  occurred_at: '2026-09-07T12:30:00Z', event_created_at: '2026-09-07T12:35:00Z', event_has_time: true, locale: 'en',
});

/** One database for every server: its scans waiting for a browser, its leases and its clock. */
function database(rows: JsonObject[]) {
  const recorded = new Set<string>();
  const leases = new Map<string, { owner: string; until: number }>();
  let clock = now;
  const client = {
    listPendingPushNotifications: vi.fn(async () => rows.filter((row) => !recorded.has(String(row.event_id)))),
    recordPushDeliveries: vi.fn(async (_subscriptionId: string, eventIds: string[]) => {
      for (const eventId of eventIds) recorded.add(eventId);
    }),
    updatePushSubscription: vi.fn().mockResolvedValue(undefined),
    archivedPackageIds: vi.fn().mockResolvedValue(new Set()),
    claimWorkerLease: vi.fn(async (name: string, owner: string, seconds: number) => {
      const held = leases.get(name);
      if (held && held.owner !== owner && held.until > clock) return false;
      leases.set(name, { owner, until: clock + seconds * 1_000 });
      return true;
    }),
    renewWorkerLease: vi.fn(async (name: string, owner: string, seconds: number) => {
      const held = leases.get(name);
      if (!held || held.owner !== owner || held.until <= clock) return false;
      held.until = clock + seconds * 1_000;
      return true;
    }),
    releaseWorkerLease: vi.fn(async (name: string, owner: string) => {
      if (leases.get(name)?.owner !== owner) return false;
      return leases.delete(name);
    }),
  };
  return { client, recorded, leases, pass: (ms: number) => { clock += ms; } };
}

/** A server's push dispatch, with a browser channel whose sends wait for `sending` when given. */
function server(client: ReturnType<typeof database>['client'], sending?: Promise<void>, lease = true) {
  const web = new WebPushNotificationService(client as never, 'public-key', 'private-key', 'https://delivery.example', () => now);
  const send = vi.spyOn(web, 'send').mockImplementation(async () => { await sending; });
  const push = new CompositePushNotificationService(web, null, null, null, null, lease ? new WorkerLease(client, 'push_dispatch') : null);
  return { push, send };
}

afterEach(() => { vi.restoreAllMocks(); vi.useRealTimers(); });

describe('one dispatch at a time across servers', () => {
  it('sends each scan once when two servers dispatch together, and the one that waited sends nothing', async () => {
    vi.spyOn(console, 'log').mockImplementation(() => undefined);
    const logged = vi.spyOn(observability, 'logOperationalEvent');
    const { client, recorded, leases } = database([scan('event-1', 'package-1'), scan('event-2', 'package-2')]);
    let sent!: () => void;
    const sending = new Promise<void>((resolve) => { sent = resolve; });
    const old = server(client, sending);
    const fresh = server(client, sending);

    const dispatches = Promise.all([old.push.dispatch(), fresh.push.dispatch()]);
    await vi.waitFor(() => expect(old.send.mock.calls.length + fresh.send.mock.calls.length).toBe(1));
    sent();
    const [first, second] = await dispatches;

    expect(old.send.mock.calls.length + fresh.send.mock.calls.length).toBe(2);
    expect(Math.min(old.send.mock.calls.length, fresh.send.mock.calls.length)).toBe(0);
    expect([first.sent, second.sent].sort()).toEqual([0, 2]);
    expect(recorded).toEqual(new Set(['event-1', 'event-2']));
    expect(logged).toHaveBeenCalledExactlyOnceWith('push_dispatch_skipped', { reason: 'lease_held' });
    // Given up at the end: the next dispatch, from either server, needs no wait.
    expect(leases.size).toBe(0);
    await expect(fresh.push.dispatch()).resolves.toMatchObject({ attempted: 0, sent: 0 });
    expect(fresh.send.mock.calls.length + old.send.mock.calls.length).toBe(2);
  });

  it('would send each scan twice without the lease', async () => {
    const { client } = database([scan('event-1', 'package-1')]);
    let sent!: () => void;
    const sending = new Promise<void>((resolve) => { sent = resolve; });
    const old = server(client, sending, false);
    const fresh = server(client, sending, false);
    const dispatches = Promise.all([old.push.dispatch(), fresh.push.dispatch()]);
    await vi.waitFor(() => expect(old.send.mock.calls.length + fresh.send.mock.calls.length).toBe(2));
    sent();
    await dispatches;
  });

  it('takes over the lease of a server that crashed once it runs out, and not before', async () => {
    vi.spyOn(console, 'log').mockImplementation(() => undefined);
    const { client, leases, pass } = database([scan('event-1', 'package-1')]);
    await client.claimWorkerLease('push_dispatch', 'crashed-server', WORKER_LEASE_SECONDS);
    const fresh = server(client);

    await expect(fresh.push.dispatch()).resolves.toMatchObject({ sent: 0 });
    expect(fresh.send).not.toHaveBeenCalled();

    pass(WORKER_LEASE_SECONDS * 1_000);
    await expect(fresh.push.dispatch()).resolves.toMatchObject({ sent: 1 });
    expect(fresh.send).toHaveBeenCalledOnce();
    expect(leases.size).toBe(0);
  });

  it('renews the lease while it sends, and stops before another server could take it over', async () => {
    vi.useFakeTimers();
    const { client, leases } = database([]);
    const lease = new WorkerLease(client, 'push_dispatch');
    let signal!: AbortSignal;
    let finish!: () => void;
    const running = lease.run(async (held) => {
      signal = held;
      await new Promise<void>((resolve) => { finish = resolve; });
      held.throwIfAborted();
      return 'done';
    });
    await vi.advanceTimersByTimeAsync(WORKER_LEASE_RENEW_MS);
    expect(client.renewWorkerLease).toHaveBeenCalledOnce();
    expect(signal.aborted).toBe(false);

    // The database stops answering: the work is told to stop half way through the lease.
    client.renewWorkerLease.mockRejectedValue(new Error('fetch failed'));
    await vi.advanceTimersByTimeAsync(WORKER_LEASE_CONFIRMED_MS - 1);
    expect(signal.aborted).toBe(false);
    await vi.advanceTimersByTimeAsync(1);
    expect(signal.reason).toBeInstanceOf(WorkerLeaseLost);
    finish();
    await expect(running).rejects.toBeInstanceOf(WorkerLeaseLost);
    expect(leases.size).toBe(0);
  });

  it('stops at once when another server holds the lease it renews', async () => {
    vi.useFakeTimers();
    const { client } = database([]);
    const lease = new WorkerLease(client, 'push_dispatch');
    const running = lease.run((held) => new Promise((_, reject) => {
      held.addEventListener('abort', () => reject(held.reason as Error));
    }));
    const outcome = expect(running).rejects.toBeInstanceOf(WorkerLeaseLost);
    await vi.advanceTimersByTimeAsync(0);
    client.renewWorkerLease.mockResolvedValue(false);
    await vi.advanceTimersByTimeAsync(WORKER_LEASE_RENEW_MS);
    await outcome;
    // It was not this server's to give up.
    expect(client.releaseWorkerLease).toHaveBeenCalledOnce();
  });

  it('gives the lease up at shutdown only once the send in progress has ended', async () => {
    const { client, leases } = database([scan('event-1', 'package-1')]);
    let sent!: () => void;
    const { push } = server(client, new Promise<void>((resolve) => { sent = resolve; }));
    const dispatch = push.dispatch();
    await vi.waitFor(() => expect(leases.size).toBe(1));
    let released = false;
    const releasing = push.releaseLease().then(() => { released = true; });
    await Promise.resolve();
    expect(released).toBe(false);
    expect(client.releaseWorkerLease).not.toHaveBeenCalled();
    sent();
    await Promise.all([dispatch, releasing]);
    expect(leases.size).toBe(0);
    expect(client.recordPushDeliveries).toHaveBeenCalledOnce();
  });

  it('fails the dispatch while the lease cannot be read, and leaves the scans pending', async () => {
    const { client, recorded } = database([scan('event-1', 'package-1')]);
    client.claimWorkerLease.mockRejectedValueOnce(new Error('fetch failed'));
    const { push, send } = server(client);
    await expect(push.dispatch()).rejects.toThrow('fetch failed');
    expect(send).not.toHaveBeenCalled();
    expect(recorded.size).toBe(0);
    await expect(push.dispatch()).resolves.toMatchObject({ sent: 1 });
  });
});

describe('a send that ends as the server stops', () => {
  const draining = new Error('draining');
  const stopping = () => {
    const controller = new AbortController();
    return { signal: controller.signal, stop: async () => { controller.abort(draining); } };
  };

  it('is recorded for a browser, and the next scan waits', async () => {
    const { client, recorded } = database([scan('event-1', 'package-1'), scan('event-2', 'package-2')]);
    const web = new WebPushNotificationService(client as never, 'public-key', 'private-key', 'https://delivery.example', () => now);
    const { signal, stop } = stopping();
    const send = vi.spyOn(web, 'send').mockImplementation(stop);
    await expect(web.dispatch(signal)).rejects.toBe(draining);
    expect(send).toHaveBeenCalledOnce();
    expect(recorded).toEqual(new Set(['event-1']));
  });

  it.each([
    ['an iPhone', (client: never) => new NativePushNotificationService(client, 'TEAM', 'KEY', apnsKey, 'com.example.delivery', () => now / 1_000)],
    ['an Android phone', (client: never) => new FcmPushNotificationService(client, 'synthetic-project', 'sender@synthetic-project.example', fcmKey, () => now / 1_000)],
  ])('is recorded for %s, and the next scan waits', async (_name, channel) => {
    const client = {
      listPendingNativePushNotifications: vi.fn().mockResolvedValue([scan('event-1', 'package-1'), scan('event-2', 'package-2')]),
      recordNativePushDeliveries: vi.fn().mockResolvedValue(undefined),
      updateNativePushDevice: vi.fn().mockResolvedValue(undefined),
      archivedPackageIds: vi.fn().mockResolvedValue(new Set()),
    };
    const service = channel(client as never);
    const { signal, stop } = stopping();
    const send = vi.spyOn(service, 'send').mockImplementation(stop);
    await expect(service.dispatch(signal)).rejects.toBe(draining);
    expect(send).toHaveBeenCalledOnce();
    expect(client.recordNativePushDeliveries).toHaveBeenCalledExactlyOnceWith('device-1', ['event-1']);
  });

  it('is recorded for a Live Activity, and the next scan waits', async () => {
    const activity = (eventId: string, packageId: string) => ({ ...scan(eventId, packageId), stage: 'out_for_delivery', push_to_start_token: 'ab'.repeat(32) });
    const client = {
      listPendingLiveActivityEvents: vi.fn().mockResolvedValue([activity('event-1', 'package-1'), activity('event-2', 'package-2')]),
      recordLiveActivityDeliveries: vi.fn().mockResolvedValue(undefined),
      updateLiveActivityDevice: vi.fn().mockResolvedValue(undefined),
    };
    const native = new NativePushNotificationService(client as never, 'TEAM', 'KEY', apnsKey, 'com.example.delivery', () => now / 1_000);
    const live = new DeliveryLiveActivityNotificationService(client as never, native);
    const { signal, stop } = stopping();
    const send = vi.spyOn(live, 'send').mockImplementation(stop);
    await expect(live.dispatch(signal)).rejects.toBe(draining);
    expect(send).toHaveBeenCalledOnce();
    expect(client.recordLiveActivityDeliveries).toHaveBeenCalledExactlyOnceWith([
      expect.objectContaining({ eventId: 'event-1', deliveryKind: 'start' }),
    ]);
  });
});
