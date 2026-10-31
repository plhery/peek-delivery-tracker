import { createServer, type IncomingHttpHeaders } from 'node:http';
import type { AddressInfo } from 'node:net';
import { afterEach, expect, it, vi } from 'vitest';
import * as Sentry from '@sentry/node';
import type { Event } from '@sentry/node';
import { captureOperationalError, captureOverallAllowance, captureTrackingHealth, flushObservability, initObservability, reportRoutingEvent,
  withTrackingAttempt, type RoutingSignal,
} from './observability';
import { UniversalTrackingError } from 'universal-parcel-scraper/node';
import { UpstreamHttpError } from 'universal-parcel-scraper/node';
import { createAdapterRegistry } from './adapterRegistry';
import { hostStepRecorder } from './stepRecorder';
import { SupabaseError } from './supabase';

const captured = vi.hoisted(() => ({ events: [] as Event[] }));

// Exercise the real SDK event pipeline, replacing only transport so diagnostic
// fixtures never leave the test process.
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

/** A report as text, without the source lines around its frames: this test's own lines name its numbers. */
function withoutSource(event: Event): string {
  return JSON.stringify(event, (key, value: unknown) => (['pre_context', 'context_line', 'post_context'].includes(key) ? undefined : value));
}

afterEach(async () => {
  await Sentry.close();
  // The next test starts the SDK again.
  Reflect.deleteProperty(globalThis, '__deliveryObservabilityInitialized');
  vi.unstubAllEnvs();
  vi.restoreAllMocks();
});

it('retains original exceptions, provider causes, and SDK diagnostic context', async () => {
  vi.stubEnv('SENTRY_DSN', 'https://public@example.test/1');
  vi.stubEnv('SENTRY_ENVIRONMENT', 'test');
  expect(initObservability()).toBe(true);
  const error = new UniversalTrackingError([
    { source: '17TRACK', reason: 'history unavailable', error: new Error('17TRACK returned HTTP 429') },
    { source: 'ParcelsApp', reason: 'history unavailable', error: new Error('ParcelsApp identity missing') },
  ]);

  let operationalEventId: string | null = null;
  Sentry.withScope((scope) => {
    scope.setUser({ id: 'diagnostic-user' });
    scope.addBreadcrumb({ message: 'Looking up parcel TEST1234' });
    scope.setContext('carrier_response', { number: 'TEST1234', status: 'waiting' });
    scope.setExtra('carrier_payload', { number: 'TEST1234' });
    operationalEventId = captureOperationalError(error, {
      component: 'tracking-sync', operation: 'fetch', carrier: 'unknown',
      trackingNumber: 'TEST1234',
      route: '/api/packages/11111111-1111-1111-1111-111111111111?detail=full',
    });
    Sentry.captureEvent({
      message: 'Direct SDK diagnostic message',
      request: { url: 'https://delivery.example.test/api/packages/TEST1234', method: 'GET' },
      transaction: 'parcel-detail',
      exception: { values: [{ type: 'CarrierError', value: 'Parcel TEST1234 unavailable',
        stacktrace: { frames: [{ filename: '/app/carrier.ts',
          vars: { trackingNumber: 'TEST1234' }, context_line: 'throw carrierError;',
        }] },
      }] },
    });
  });

  expect(await flushObservability()).toBe(true);
  expect(captured.events).toHaveLength(2);
  const operational = captured.events.find((event) => event.event_id === operationalEventId)!;
  expect(operational.exception?.values?.map((value) => value.value)).toEqual(expect.arrayContaining([
    error.message, '17TRACK returned HTTP 429', 'ParcelsApp identity missing',
  ]));
  expect(operational.contexts?.UniversalTrackingError).toHaveProperty('failures');
  expect(operational.tags).toMatchObject({
    number_pattern: '4 letters + 4 digits',
    route: '/api/packages/11111111-1111-1111-1111-111111111111?detail=full',
  });
  expect(operational.tags).not.toHaveProperty('tracking_number');
  expect(operational.contexts?.operation).toMatchObject({ numberPattern: '4 letters + 4 digits' });
  expect(operational.contexts?.operation).not.toHaveProperty('trackingNumber');
  // Whatever else the report holds keeps its shape, with the parcel's number in its place.
  expect(operational.extra?.carrier_payload).toEqual({ number: '4 letters + 4 digits' });
  expect(operational.contexts?.carrier_response).toEqual({ number: '4 letters + 4 digits', status: 'waiting' });
  expect(operational.user?.id).toBe('diagnostic-user');
  expect(operational.breadcrumbs).toEqual(expect.arrayContaining([
    expect.objectContaining({ message: 'Looking up parcel 4 letters + 4 digits' }),
  ]));
  expect(withoutSource(operational)).not.toContain('TEST1234');

  // A report about no parcel keeps its text; addresses are still cut down.
  const direct = captured.events.find((event) => event.message === 'Direct SDK diagnostic message')!;
  expect(direct.request?.url).toBe('https://delivery.example.test/api/packages/:id');
  expect(direct.transaction).toBe('parcel-detail');
  expect(direct.exception?.values?.[0]).toMatchObject({ value: 'Parcel TEST1234 unavailable',
    stacktrace: { frames: [expect.objectContaining({
      vars: { trackingNumber: 'TEST1234' }, context_line: 'throw carrierError;',
    })] },
  });
  const client = Sentry.getClient()!;
  const options = client.getOptions();
  expect(options.dataCollection).toMatchObject({ userInfo: false, httpBodies: [], urlQueryParams: false });
  expect(['Console', 'Http', 'NodeFetch', 'RequestData', 'ExtraErrorData']
    .filter((name) => !client.getIntegrationByName(name))).toEqual([]);
  expect(client.getIntegrationByName('Dedupe')).toBeUndefined();

  const ids = ['first', 'second'].map((attemptId) => captureOperationalError(
    new UpstreamHttpError('GLS Germany tracking', 404), {
      component: 'tracking-sync', operation: 'fetch', carrier: 'gls-de',
      attemptId, jobId: `job-${attemptId}`,
      trackingNumber: `TEST-${attemptId}`,
    },
  ));
  await flushObservability();
  const repeats = captured.events.filter((event) => ids.includes(event.event_id!));
  expect(repeats).toHaveLength(2);
  expect(repeats[0].fingerprint).toEqual([
    'delivery-tracker', 'tracking-sync', 'fetch', 'gls-de', 'UpstreamHttpError',
  ]);
  expect(repeats[1].fingerprint).toEqual(repeats[0].fingerprint);
  expect(repeats.map((event) => event.tags?.attempt_id).sort()).toEqual(['first', 'second']);
  expect(repeats.map((event) => event.tags?.number_pattern).sort()).toEqual(['10 letters', '9 letters']);
  const warn = vi.spyOn(console, 'warn').mockImplementation(() => undefined);
  reportRoutingEvent('provider_failed', { carrier: 'dhl', provider: '17TRACK',
    category: 'rate_limited', trackingNumber: 'TEST-first', errorClass: 'UpstreamHttpError', error: new UpstreamHttpError('17TRACK', 429) });
  // The log line is the only per-attempt record now that failures no longer reach Sentry.
  expect(JSON.parse(String(warn.mock.calls.at(-1)?.[0]))).toMatchObject({ event: 'tracking_routing', decision: 'provider_failed',
    provider: '17TRACK', error_type: 'UpstreamHttpError', error_message: expect.stringContaining('429'), upstream_status: 429 });
  warn.mockRestore();
  const log = vi.spyOn(console, 'log').mockImplementation(() => undefined);
  reportRoutingEvent('fresher_provider_found', { carrier: 'dhl', provider: 'ParcelsApp', trackingNumber: 'TEST-first' });
  // The router has already adopted the fresher provider: the log line is the record of it.
  expect(JSON.parse(String(log.mock.calls.at(-1)?.[0]))).toMatchObject({ event: 'tracking_routing', decision: 'fresher_provider_found',
    carrier: 'dhl', provider: 'ParcelsApp' });
  log.mockRestore();
  // Routing signals go to the check that reported them, for the review queues, and stay out of Sentry.
  const signals: RoutingSignal[] = [];
  withTrackingAttempt('attempt-signals', () => {
    reportRoutingEvent('carrier_auto_swapped', { carrier: 'dhl', provider: 'ups', trackingNumber: 'TEST-first' });
    const swapLog = vi.spyOn(console, 'log').mockImplementation(() => undefined);
    reportRoutingEvent('carrier_auto_swapped', { carrier: 'unknown', provider: 'gls', category: 'detected', trackingNumber: 'TEST-detected' });
    // Detection already names the new carrier: the correction is a log line and a breadcrumb, and no signal.
    expect(JSON.parse(String(swapLog.mock.calls.at(-1)?.[0]))).toMatchObject({ event: 'tracking_routing', decision: 'carrier_auto_swapped',
      carrier: 'unknown', provider: 'gls', category: 'detected' });
    swapLog.mockRestore();
    reportRoutingEvent('provider_recovered', { carrier: 'dhl', provider: '17TRACK' });
    reportRoutingEvent('carrier_coverage_discovered', { carrier: 'dhl', provider: 'Example Parcel Co' });
    reportRoutingEvent('carrier_mismatch_confirmed', { carrier: 'unknown', provider: 'gls' });
    reportRoutingEvent('direct_support_opportunity', { carrier: 'fedex', provider: 'fedex' });
  }, (signal) => signals.push(signal));
  expect(signals).toEqual([
    { signal: 'carrier_auto_swapped', carrier: 'dhl', subject: 'ups' },
    { signal: 'carrier_coverage_discovered', carrier: 'dhl', subject: 'Example Parcel Co' },
    { signal: 'carrier_mismatch_confirmed', carrier: 'unknown', subject: 'gls' },
    { signal: 'direct_support_opportunity', carrier: 'fedex', subject: 'fedex' },
  ]);
  await flushObservability();
  expect(captured.events.filter((event) => event.message?.startsWith('Tracking routing:'))).toEqual([]);
  // A failed coordination store is the one routing decision that opens an issue.
  reportRoutingEvent('health_store_unavailable', { carrier: 'dhl', provider: 'tracking-health', trackingNumber: 'TEST-first' });
  await flushObservability();
  const storeDown = captured.events.find((event) => event.message === 'Tracking routing: health_store_unavailable')!;
  expect(storeDown.level).toBe('warning');
  expect(storeDown.tags).toMatchObject({ carrier: 'dhl', provider: 'tracking-health', number_pattern: '9 letters' });
  // With an exception attached, Sentry titles the issue after its top frame, not the message.
  expect(storeDown.exception).toBeUndefined();
  expect(storeDown.breadcrumbs).toEqual(expect.arrayContaining([
    expect.objectContaining({ category: 'tracking-routing', message: 'fresher_provider_found', data: { provider: 'ParcelsApp' } }),
    expect.objectContaining({ category: 'tracking-routing', message: 'carrier_auto_swapped', data: { provider: 'gls' } }),
    expect.objectContaining({ category: 'tracking-routing', message: 'carrier_coverage_discovered', data: { provider: 'Example Parcel Co' } }),
  ]));

  const refusedBody = '<h1>Access Denied</h1><p>Reference #18.test.123; parcel 8U00000000000</p>';
  const fetcher = vi.spyOn(globalThis, 'fetch').mockImplementation(async () => new Response(refusedBody, {
    status: 403, headers: { 'content-type': 'text/html', 'x-request-id': 'example-request-id',
      'set-cookie': 'session=DO_NOT_CAPTURE', authorization: 'Bearer DO_NOT_CAPTURE', 'retry-after': '120' },
  }));
  // The tracker reports through the package's StepRecorder now, so the host's
  // sinks have to be wired in for its retries to reach Sentry.
  const refused = await createAdapterRegistry({ fetcher, trawl: null, browserExecutablePath: null, env: {}, recorder: hostStepRecorder() })
    .for('la-poste')!.track({ number: '8U00000000000' }).catch((error: unknown) => error);
  // One direct request and the three immediate 403 retries.
  expect(fetcher).toHaveBeenCalledTimes(4);
  fetcher.mockRestore();
  expect(refused).toBeInstanceOf(UpstreamHttpError);
  reportRoutingEvent('provider_failed', { carrier: 'la-poste', provider: 'la-poste',
    category: 'verification', error: refused, errorClass: 'UpstreamHttpError' });
  const wrappedId = captureOperationalError(new Error('Recovery failed', { cause: refused }), {
    component: 'tracking-sync', operation: 'fetch', carrier: 'la-poste',
  });
  reportRoutingEvent('provider_recovered', { carrier: 'la-poste', provider: 'la-poste' });
  reportRoutingEvent('health_store_unavailable', { carrier: 'la-poste', provider: 'tracking-health' });
  await flushObservability();
  const retries = captured.events.filter((event) => event.message === 'Tracking routing: transport_fallback' && event.tags?.provider === 'la-poste');
  expect(retries).toHaveLength(0);
  expect(captured.events.some(event => event.message === 'Tracking routing: provider_failed')).toBe(false);
  const wrapped = captured.events.find(event => event.event_id === wrappedId)!;
  expect(wrapped.contexts?.upstream_http).toMatchObject({ body_excerpt: refusedBody, body_read: 'complete' });
  const incidentId = captureTrackingHealth({ id: 'test-incident', kind: 'direct', subject: 'la-poste', state: 'open',
    attempts: 12, failures: 8, window_hours: 24, evidence: { http_status: 403, category: 'challenge' } });
  await flushObservability();
  const incident = captured.events.find(event => event.event_id === incidentId)!;
  expect(incident.message).toBe('Direct tracking repeatedly failing: la-poste (8/12 in 24h)');
  expect(incident.exception).toBeUndefined();
  expect(incident.tags).toMatchObject({ component: 'tracking-health', incident_state: 'open' });
  expect(incident.contexts?.tracking_health?.next_steps).toContain('maintenance');
  expect(incident.fingerprint).toEqual(['delivery-tracker', 'tracking-health', 'direct', 'la-poste']);
  // A recovery, a closing, a rescued direct step or an own lookup its direct incident covers is a review signal, not an issue.
  const before = captured.events.length;
  expect(captureTrackingHealth({ id: 'test-recovery', kind: 'direct', subject: 'la-poste', state: 'recovered',
    attempts: 3, failures: 0, window_hours: 24 })).toBeNull();
  expect(captureTrackingHealth({ id: 'test-closing', kind: 'direct', subject: 'la-poste', state: 'closed',
    attempts: 0, failures: 0, window_hours: 24 })).toBeNull();
  expect(captureTrackingHealth({ id: 'test-rescued', kind: 'direct', subject: 'la-poste', state: 'open', rescued: true,
    attempts: 12, failures: 8, window_hours: 24 })).toBeNull();
  expect(captureTrackingHealth({ id: 'test-covered', kind: 'provider', subject: 'la-poste', state: 'open', covered: true,
    attempts: 12, failures: 8, window_hours: 24 })).toBeNull();
  await flushObservability();
  expect(captured.events).toHaveLength(before);
  // A later error-free routing event must not inherit the refusal's HTTP context,
  // and the recovery itself is only a breadcrumb on it rather than its own issue.
  const storeAfter = captured.events.filter((event) => event.message === 'Tracking routing: health_store_unavailable').at(-1)!;
  expect(storeAfter.tags?.carrier).toBe('la-poste');
  expect(storeAfter.contexts?.upstream_http).toBeUndefined();
  expect(storeAfter.tags?.upstream_body_read).toBeUndefined();
  expect(storeAfter.breadcrumbs?.some((crumb) => crumb.category === 'tracking-routing' && crumb.message === 'provider_recovered')).toBe(true);

  // An overall allowance without an account is one issue per allowance and state.
  const low = captureOverallAllowance('public', 'lookup', 'running_out', { used: 2_400, limit: 3_000 });
  const gone = captureOverallAllowance('public', 'detection', 'used_up', { used: 10_000, limit: 10_000 });
  await flushObservability();
  const runningOut = captured.events.find((event) => event.event_id === low)!;
  expect(runningOut.message).toBe("Lookups without an account: today's allowance is running out");
  expect(runningOut.level).toBe('warning');
  expect(runningOut.exception).toBeUndefined();
  expect(runningOut.fingerprint).toEqual(['delivery-tracker', 'public-allowance', 'lookup', 'running_out']);
  expect(runningOut.contexts?.public_allowance).toMatchObject({ used: 2_400, limit: 3_000 });
  expect(runningOut.contexts?.public_allowance?.next_steps).toContain('PUBLIC_LOOKUPS_GLOBAL_PER_DAY');
  expect(runningOut.contexts?.upstream_http).toBeUndefined();
  const usedUp = captured.events.find((event) => event.event_id === gone)!;
  expect(usedUp.message).toBe("Carrier detection without an account: today's allowance is used up");
  expect(usedUp.level).toBe('error');
  expect(usedUp.tags).toMatchObject({ component: 'public-allowance', allowance: 'detection', allowance_state: 'used_up' });
  expect(usedUp.contexts?.public_allowance?.next_steps).toContain('PUBLIC_DETECTIONS_GLOBAL_PER_DAY');

  // Every account shares an hourly allowance, reported apart from the one without an account.
  const accounts = captureOverallAllowance('account', 'lookup', 'used_up', { used: 50, limit: 50 });
  await flushObservability();
  const accountsUsedUp = captured.events.find((event) => event.event_id === accounts)!;
  expect(accountsUsedUp.message).toBe("Parcels added by accounts: this hour's allowance is used up");
  expect(accountsUsedUp.fingerprint).toEqual(['delivery-tracker', 'account-allowance', 'lookup', 'used_up']);
  expect(accountsUsedUp.contexts?.account_allowance?.next_steps).toContain('ACCOUNT_LOOKUPS_PER_HOUR');
});

it('names a parcel by the shape of its number and cuts addresses down in reports and breadcrumbs', async () => {
  vi.stubEnv('SENTRY_DSN', 'https://public@example.test/1');
  expect(initObservability()).toBe(true);

  // Breadcrumbs gather across parcels: another parcel's log line, and a request about it.
  const line = JSON.stringify({ event: 'tracking_sync_started', number_pattern: '4 letters + 4 digits' });
  Sentry.addBreadcrumb({ category: 'console', level: 'log', message: line, data: { arguments: [line], logger: 'console' } });
  Sentry.addBreadcrumb({ category: 'http', type: 'http', data: { url: 'https://carrier.example/v1/track/TEST9012/events',
    'http.method': 'GET', 'http.query': 'number=TEST9012', status_code: 404 } });

  const request = { url: 'https://carrier.example/v1/track?number=TEST5678', method: 'POST', headers: {},
    body: '{"number":"TEST5678"}', timeout_ms: 10_000 };
  const refused = new UpstreamHttpError('Example Carrier', 404, undefined, {
    content_type: 'text/html', headers: { location: 'https://carrier.example/v1/parcels/TEST5678' },
    response_url: request.url, status_text: 'Not Found', request_ids: {}, body_read: 'complete',
    bytes_inspected: 40, body_signals: [], body_excerpt: '<p>No parcel test5678</p>',
  }, request);
  const id = captureOperationalError(new Error('Lookup of TEST5678 failed', { cause: refused }), {
    component: 'tracking-sync', operation: 'fetch', carrier: 'example', trackingNumber: 'TEST5678',
  });
  await flushObservability();
  const report = captured.events.find((event) => event.event_id === id)!;
  expect(report.tags).toMatchObject({ number_pattern: '4 letters + 4 digits' });
  expect(report.tags).not.toHaveProperty('tracking_number');
  expect(report.contexts?.operation).toEqual(expect.objectContaining({ component: 'tracking-sync', carrier: 'example',
    numberPattern: '4 letters + 4 digits' }));
  expect(report.contexts?.operation).not.toHaveProperty('trackingNumber');
  expect(report.exception?.values?.map((value) => value.value)).toContain('Lookup of 4 letters + 4 digits failed');
  // Addresses lose their query and number-like path segments; other text loses the number, in any case.
  expect(report.contexts?.upstream_http).toMatchObject({ response_url: 'https://carrier.example/v1/track',
    body_excerpt: '<p>No parcel 4 letters + 4 digits</p>', headers: { location: 'https://carrier.example/v1/parcels/:id' } });

  // A log line, which holds no number, keeps its text without the console's copy of it.
  const logLine = report.breadcrumbs?.find((crumb) => crumb.category === 'console' && crumb.message?.includes('tracking_sync_started'));
  expect(logLine!.message).toBe(line);
  expect(withoutSource(report)).not.toMatch(/test5678|test9012/i);
  expect(logLine!.data).toEqual({ logger: 'console' });
  const call = report.breadcrumbs?.find((crumb) => crumb.category === 'http' && crumb.data?.status_code === 404);
  expect(call!.data).toEqual({ url: 'https://carrier.example/v1/track/:id/events', 'http.method': 'GET', status_code: 404 });
});

it('opens one issue for a database that does not answer, whichever write found it, and one per write for a refusal', async () => {
  vi.stubEnv('SENTRY_DSN', 'https://public@example.test/1');
  expect(initObservability()).toBe(true);
  const unreachable = () => new SupabaseError('The delivery database is unreachable', undefined, 'unreachable',
    { cause: new TypeError('fetch failed') });
  const ids = [
    captureOperationalError(unreachable(), { component: 'sync-worker', operation: 'finish_job', jobId: 'job-1' }),
    captureOperationalError(unreachable(), { component: 'tracking-sync', operation: 'persist_package', carrier: 'example', jobId: 'job-1' }),
    captureOperationalError(new SupabaseError('Supabase POST request failed (404)', 404, 'PGRST202'),
      { component: 'tracking-sync', operation: 'persist_package', carrier: 'example', jobId: 'job-1' }),
  ];
  await flushObservability();
  const [finish, persist, refused] = ids.map((id) => captured.events.find((event) => event.event_id === id)!);
  expect(finish.fingerprint).toEqual(['delivery-tracker', 'database-unreachable']);
  expect(persist.fingerprint).toEqual(finish.fingerprint);
  // Each event still names the write that failed.
  expect(finish.tags).toMatchObject({ component: 'sync-worker', operation: 'finish_job', job_id: 'job-1', database_code: 'unreachable' });
  expect(persist.tags).toMatchObject({ component: 'tracking-sync', operation: 'persist_package', carrier: 'example',
    job_id: 'job-1', database_code: 'unreachable' });
  expect(refused.fingerprint).toEqual(['delivery-tracker', 'tracking-sync', 'persist_package', 'example', 'SupabaseError']);
  expect(refused.tags).toMatchObject({ database_code: 'PGRST202', database_status: '404' });
  // A database that does not answer gives no status, and still says what to check.
  expect(finish.contexts?.next_steps?.action).toContain('did not answer');
  expect(refused.contexts?.next_steps?.action).toContain('PostgREST schema cache');
});

it('sends carriers no trace headers', async () => {
  vi.stubEnv('SENTRY_DSN', 'https://public@example.test/1');
  expect(initObservability()).toBe(true);
  const received: IncomingHttpHeaders[] = [];
  const server = createServer((request, response) => { received.push(request.headers); response.end('{}'); });
  await new Promise<void>((resolve) => server.listen(0, '127.0.0.1', resolve));
  try {
    const { port } = server.address() as AddressInfo;
    // A carrier is asked from inside a refresh, which runs in a span of its own.
    await Sentry.startSpan({ name: 'carrier lookup' }, () => fetch(`http://127.0.0.1:${port}/track`).then((response) => response.text()));
  } finally {
    await new Promise((resolve) => server.close(resolve));
  }
  expect(received).toHaveLength(1);
  expect(received[0]).not.toHaveProperty('baggage');
  expect(received[0]).not.toHaveProperty('sentry-trace');
});
