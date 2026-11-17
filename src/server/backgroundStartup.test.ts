import { afterEach, beforeEach, expect, it, vi } from 'vitest';
import { startBackgroundServices } from './background';
import { DeliveryEmailService } from './email/deliveryEmails';
import * as observability from './observability';

type Runtime = NonNullable<ReturnType<typeof startBackgroundServices>>;
const started: Runtime[] = [];
const globalRuntime = globalThis as { __deliveryBackgroundRuntime?: unknown };

function start(): Runtime {
  const runtime = startBackgroundServices();
  if (!runtime) throw new Error('The background services did not start');
  started.push(runtime);
  return runtime;
}

beforeEach(() => {
  // The workers only set timers here: none fires.
  vi.useFakeTimers();
  vi.stubEnv('SUPABASE_URL', 'https://database.example');
  vi.stubEnv('SUPABASE_SERVICE_ROLE_KEY', 'test-service-key');
  for (const unset of ['VAPID_PUBLIC_KEY', 'VAPID_PRIVATE_KEY', 'APNS_TEAM_ID', 'APNS_KEY_ID', 'APNS_PRIVATE_KEY',
    'APNS_BUNDLE_ID', 'SMTP_HOST', 'SMTP_USER', 'SMTP_PASSWORD']) {
    vi.stubEnv(unset, '');
  }
});
afterEach(() => {
  for (const runtime of started.splice(0)) {
    runtime.worker.stop();
    for (const reader of runtime.readerWorkers) reader.stop();
    runtime.scheduler.stop();
    runtime.friendshipWorker.stop();
    runtime.reviews.stop();
  }
  delete globalRuntime.__deliveryBackgroundRuntime;
  vi.useRealTimers();
  vi.restoreAllMocks();
  vi.unstubAllEnvs();
});

it('sends delivery emails on a deployment without push, and says so at startup', () => {
  const logged = vi.spyOn(observability, 'logOperationalEvent').mockImplementation(() => undefined);
  vi.stubEnv('SMTP_HOST', 'smtp.example.com');
  vi.stubEnv('EMAIL_FROM', 'Peek <hello@example.com>');
  vi.stubEnv('CANONICAL_ORIGIN', 'https://peek.example.com');
  const { service } = start().worker;
  // Only the Android app's inbox, which needs nothing but the database.
  expect(service.notifier).toMatchObject({ web: null, native: null, fcm: null, unifiedpush: null, liveActivities: null });
  expect(service.notifier!.inbox).not.toBeNull();
  expect(service.emails).toBeInstanceOf(DeliveryEmailService);
  expect(service.emails!.settings).toMatchObject({ host: 'smtp.example.com', origin: 'https://peek.example.com' });
  expect(logged).toHaveBeenCalledExactlyOnceWith('background_services_started', {
    sync_enabled: true, reader_lane: true, web_push_enabled: false, native_push_enabled: false, fcm_push_enabled: false,
    unifiedpush_push_enabled: false, live_activity_push_enabled: false, delivery_email_enabled: true,
  });
});

it('runs the readers\' checks in a lane of their own, unless switched off', () => {
  const lane = start();
  expect(lane.worker.service.readerLane).toBe(true);
  // Two readers' checks at once, each worker with its own state.
  expect(lane.readerWorkers).toHaveLength(2);
  for (const reader of lane.readerWorkers) {
    expect(reader.lane).toBe('readers');
    expect(reader.service).toBe(lane.worker.service);
    expect(reader.state).not.toBe(lane.state);
  }
  expect(lane.readerWorkers[0]!.state).not.toBe(lane.readerWorkers[1]!.state);
  expect(new Set(lane.readerWorkers.map((reader) => reader.workerId)).size).toBe(2);
  lane.worker.stop();
  for (const reader of lane.readerWorkers) reader.stop();
  delete globalRuntime.__deliveryBackgroundRuntime;
  vi.stubEnv('SYNC_READER_LANE', 'off');
  const off = start();
  expect(off.worker.service.readerLane).toBe(false);
  expect(off.readerWorkers).toEqual([]);
});

it('sends no email without mail settings', () => {
  const logged = vi.spyOn(observability, 'logOperationalEvent').mockImplementation(() => undefined);
  expect(start().worker.service.emails).toBeNull();
  expect(logged).toHaveBeenCalledExactlyOnceWith('background_services_started', expect.objectContaining({ delivery_email_enabled: false }));
});

it('does not start on incomplete mail settings', () => {
  vi.stubEnv('SMTP_HOST', 'smtp.example.com');
  vi.stubEnv('CANONICAL_ORIGIN', 'https://peek.example.com');
  expect(() => startBackgroundServices()).toThrow('SMTP_HOST needs EMAIL_FROM');
  expect(globalRuntime.__deliveryBackgroundRuntime).toBeUndefined();
});
