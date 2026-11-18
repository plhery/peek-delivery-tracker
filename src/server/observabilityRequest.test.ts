import { afterEach, expect, it, vi } from 'vitest';
import * as Sentry from '@sentry/node';
import type { Event } from '@sentry/node';
import { SyncJobWorker } from './background';
import * as observability from './observability';
import { captureOperationalError, flushObservability, initObservability } from './observability';
import { SupabaseError, SupabaseServiceClient } from './supabase';
import { TrackingSyncService } from './trackingSync';

const captured = vi.hoisted(() => ({ events: [] as Event[] }));

// The real SDK event pipeline with a transport that keeps events in the test process.
vi.mock('@sentry/node', async (importOriginal) => {
  const sdk = await importOriginal<typeof import('@sentry/node')>();
  return {
    ...sdk,
    init: vi.fn((options: Parameters<typeof sdk.init>[0]) => sdk.init({
      ...options,
      transport: () => ({
        send: async (envelope) => {
          for (const [header, payload] of envelope[1]) {
            if (header.type === 'event') captured.events.push(payload as Event);
          }
          return { statusCode: 200 };
        },
        flush: async () => true,
      }),
    })),
  };
});

afterEach(async () => {
  await Sentry.close();
  Reflect.deleteProperty(globalThis, '__deliveryObservabilityInitialized');
  vi.restoreAllMocks();
  vi.unstubAllEnvs();
});

it('leaves the request out of a report for a route whose address or body carries a capability', async () => {
  vi.stubEnv('SENTRY_DSN', 'https://public@example.test/1');
  expect(initObservability()).toBe(true);
  const capture = (withoutRequest: boolean) => {
    let id: string | null = null;
    Sentry.withIsolationScope((isolation) => {
      // What the SDK's HTTP integration records for an incoming request.
      isolation.setSDKProcessingMetadata({ normalizedRequest: {
        url: 'https://delivery.example.test/api/public/parcels/k7Qm2xHd9RtW?lang=fr', method: 'GET',
        headers: { 'x-parcel-key': 'OWNER-KEY', 'user-agent': 'test', 'x-forwarded-for': '203.0.113.7' },
        data: '{"number":"TEST1234","key":"OWNER-KEY"}',
      } });
      isolation.setTransactionName('GET /api/public/parcels/k7Qm2xHd9RtW');
      id = observability.withRequestPrivacy(withoutRequest, () => captureOperationalError(new Error('database down'), {
        component: 'api', operation: 'request', route: '/api/public/parcels/:link',
      }));
    });
    return id;
  };
  const kept = capture(false);
  const stripped = capture(true);
  expect(await flushObservability()).toBe(true);

  // Other routes keep the request: its address and query, its headers, its body and the client's
  // address. Access values are filtered by their names, and a link id reads `:id`, here and in
  // the transaction.
  const usual = captured.events.find((event) => event.event_id === kept)!;
  expect(usual.request?.url).toBe('https://delivery.example.test/api/public/parcels/:id?lang=fr');
  expect(usual.transaction).toBe('GET /api/public/parcels/:id');
  expect(usual.request?.headers).toEqual({ 'x-parcel-key': '[Filtered]', 'user-agent': 'test', 'x-forwarded-for': '203.0.113.7' });
  expect(usual.request?.data).toBe('{"number":"TEST1234","key":"[Filtered]"}');
  expect(usual.user?.ip_address).toBe('203.0.113.7');
  // The stack frames quote this file.
  expect(JSON.stringify({ ...usual, exception: undefined })).not.toMatch(/OWNER-KEY|k7Qm2xHd9RtW/);

  const report = captured.events.find((event) => event.event_id === stripped)!;
  expect(report.request).toBeUndefined();
  expect(report.transaction).toBeUndefined();
  expect(report.tags?.route).toBe('/api/public/parcels/:link');
  // The stack frames quote this file; nothing else of the report names the link or the key.
  const rest = JSON.stringify({ ...report, exception: undefined });
  expect(rest).not.toContain('k7Qm2xHd9RtW');
  expect(rest).not.toContain('OWNER-KEY');
});

it('reports a background job woken by a request with its own scope and trace, not the request\'s', async () => {
  vi.stubEnv('SENTRY_DSN', 'https://public@example.test/1');
  expect(initObservability()).toBe(true);
  vi.spyOn(observability, 'logOperationalEvent').mockImplementation(() => undefined);
  const client = new SupabaseServiceClient('https://database.test', 'test');
  // A refused claim is reported at once.
  vi.spyOn(client, 'claimSyncJob').mockRejectedValue(new SupabaseError('Supabase POST request failed (404)', 404, 'PGRST202'));
  const worker = new SyncJobWorker(new TrackingSyncService(client), { workerHeartbeat: null, lastScheduledSync: null,
    nextScheduledSync: null, lastSummary: null, lastError: null, lastAutoArchived: 0 });
  const requestTrace = '0af7651916cd43dd8448eb211c80319c';
  let inRequest: string | null = null;
  // What the SDK records for a request from the iPhone app that continues the app's trace; the route wakes the worker.
  Sentry.withIsolationScope((isolation) => Sentry.continueTrace({
    sentryTrace: `${requestTrace}-b7ad6b7169203331`, baggage: 'sentry-environment=app,sentry-release=app%401.0',
  }, () => {
    isolation.setSDKProcessingMetadata({ normalizedRequest: {
      url: 'https://delivery.example.test/api/public/parcels', method: 'POST',
      headers: { 'user-agent': 'PeekApp/1.0 CFNetwork/3826 Darwin/25.0.0' },
    } });
    isolation.setTransactionName('POST /api/public/parcels');
    isolation.setTag('app_platform', 'ios');
    isolation.setUser({ id: 'request-user' });
    inRequest = captureOperationalError(new Error('request failed'), { component: 'api', operation: 'request' });
    worker.wake();
  }));
  try {
    await vi.waitFor(() => expect(captured.events.some((event) => event.tags?.component === 'sync-worker')).toBe(true));
  } finally {
    worker.stop();
  }
  expect(await flushObservability()).toBe(true);

  // The request's own report carries the request, as a control.
  const request = captured.events.find((event) => event.event_id === inRequest)!;
  expect(request.transaction).toBe('POST /api/public/parcels');
  expect(request.tags?.app_platform).toBe('ios');
  expect(request.contexts?.trace?.trace_id).toBe(requestTrace);

  const job = captured.events.find((event) => event.tags?.component === 'sync-worker')!;
  expect(job.tags).toMatchObject({ background_job: 'sync-worker', operation: 'claim_job' });
  expect(job.tags?.app_platform).toBeUndefined();
  expect(job.transaction).toBe('sync-worker');
  expect(job.request).toBeUndefined();
  expect(job.user).toBeUndefined();
  expect(job.contexts?.trace?.trace_id).toMatch(/^[0-9a-f]{32}$/);
  expect(job.contexts?.trace?.trace_id).not.toBe(requestTrace);
  // The stack frames quote this file; nothing else of the report names the request.
  expect(JSON.stringify({ ...job, exception: undefined })).not.toMatch(/CFNetwork|request-user|\/api\/public\/parcels|app@1\.0/);
});
