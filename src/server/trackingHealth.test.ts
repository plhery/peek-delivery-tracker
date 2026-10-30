import { describe, expect, it, vi } from 'vitest';
import { ChallengeError, IndeterminateError, NoHistoryError, NotFoundError, RateLimitedError, SchemaError, UpstreamHttpError } from 'universal-parcel-scraper';
import { TrackingCaptureError, type LookupRecord } from 'universal-parcel-scraper/node';
import { confirmationLookup, healthMessage, healthStepRecorder, observeTrackingHealth, type HealthSample } from './trackingHealth';
import { TrackingSyncAudit } from './trackingAudit';
import type { SupabaseServiceClient } from './supabase';
import type { JsonObject } from './types';
import * as observability from './observability';

const step = { carrier: 'ups', step: 'direct', attempt: 1, outcome: 'transport' as const,
  errorType: 'UpstreamNetworkError', durationMs: 200, fallbackFrom: null,
  fallbackReason: null, fallbackErrorType: null };

describe('tracking health evidence', () => {
  it('isolates overlapping syncs and counts the lookup rather than each retry', async () => {
    const a = new Map<string, HealthSample>();
    const b = new Map<string, HealthSample>();
    await Promise.all([
      observeTrackingHealth(a, async () => {
        healthStepRecorder.step(step);
        await Promise.resolve();
        healthStepRecorder.step({ ...step, step: 'retry', attempt: 2, outcome: 'ok' });
        healthStepRecorder.lookup({ carrier: 'ups', finalStep: 'trawl', outcome: 'ok', errorType: null, durationMs: 250, attempts: 2 });
      }),
      observeTrackingHealth(b, async () => {
        await Promise.resolve();
        healthStepRecorder.step({ ...step, carrier: 'dpd', outcome: 'ok' });
      }),
    ]);
    expect([...a.values()]).toMatchObject([{ subject: 'ups', kind: 'direct', healthy: false }, { subject: 'ups', kind: 'provider', healthy: true }]);
    expect([...b.values()]).toMatchObject([{ subject: 'dpd', healthy: true }]);
  });

  it('records missing input and not-found answers as healthy transports, not outages', async () => {
    const samples = new Map<string, HealthSample>();
    await observeTrackingHealth(samples, async () => {
      healthStepRecorder.step({ ...step, outcome: 'input_required' });
      healthStepRecorder.lookup({ carrier: 'ups', finalStep: 'direct', outcome: 'not_found', errorType: 'NotFoundError', durationMs: 1, attempts: 1 });
    });
    expect([...samples.values()]).toEqual([
      expect.objectContaining({ kind: 'direct', healthy: true, details: expect.objectContaining({ category: 'input_required' }) }),
      expect.objectContaining({ kind: 'provider', healthy: true, details: expect.objectContaining({ category: 'not_found' }) }),
    ]);
  });

  it('files a number the carrier does not issue with the missing inputs', async () => {
    const samples = new Map<string, HealthSample>();
    await observeTrackingHealth(samples, async () => {
      healthStepRecorder.lookup({ carrier: 'evri', finalStep: 'direct', outcome: 'invalid_input', errorType: 'InvalidInputError', durationMs: 1, attempts: 1 });
    });
    expect([...samples.values()]).toEqual([
      expect.objectContaining({ kind: 'provider', healthy: true, details: expect.objectContaining({ category: 'input_required', error_type: 'InvalidInputError' }) }),
    ]);
  });

  it('files a number that names several parcels with the missing inputs', async () => {
    const samples = new Map<string, HealthSample>();
    const several = new IndeterminateError('Australia Post', 'Australia Post lists several articles under this consignment', { reason: 'several_parcels' });
    await observeTrackingHealth(samples, async () => {
      healthStepRecorder.step({ ...step, carrier: 'australia-post', outcome: 'indeterminate', errorType: 'IndeterminateError', error: several });
      healthStepRecorder.lookup({ carrier: 'australia-post', finalStep: 'direct', outcome: 'indeterminate', errorType: 'IndeterminateError',
        durationMs: 1, attempts: 1, error: new Error('lookup failed', { cause: several }) });
    });
    expect([...samples.values()].map(({ kind, healthy, details }) => [kind, healthy, details.category])).toEqual([
      ['direct', true, 'input_required'],
      ['provider', true, 'input_required'],
    ]);
  });

  it("leaves a hand-off to the group's unit abroad out of direct health, and counts the step's other failures", async () => {
    const samples = new Map<string, HealthSample>();
    const abroad = new IndeterminateError('DPD Germany', 'DPD returned activity in another country', { reason: 'other_country' });
    const direct = { ...step, carrier: 'dpd-de', outcome: 'indeterminate' as const, errorType: 'IndeterminateError' };
    await observeTrackingHealth(samples, async () => {
      healthStepRecorder.step({ ...direct, error: abroad });
      healthStepRecorder.step({ ...direct, step: 'abroad', attempt: 2, outcome: 'ok', errorType: null, fallbackFrom: 'direct' });
      healthStepRecorder.lookup({ carrier: 'dpd-de', finalStep: 'abroad', outcome: 'ok', errorType: null, durationMs: 900, attempts: 2 });
    });
    expect([...samples.values()].map(({ kind, healthy }) => [kind, healthy])).toEqual([['provider', true]]);

    const failed = new Map<string, HealthSample>();
    await observeTrackingHealth(failed, async () => {
      healthStepRecorder.step({ ...direct, error: new IndeterminateError('DPD Germany', 'DPD Germany returned no parcel scans') });
    });
    expect([...failed.values()]).toMatchObject([{ kind: 'direct', healthy: false, details: { category: 'indeterminate' } }]);
  });

  it('counts a provider with no history for a new parcel as an answer, and a capture failure as a failure', async () => {
    const samples = new Map<string, HealthSample>();
    const noHistory = new NoHistoryError('Postal Ninja', 'Postal Ninja has no available tracking history');
    await observeTrackingHealth(samples, async () => {
      healthStepRecorder.lookup({ carrier: 'Postal Ninja', finalStep: 'trawl', outcome: 'indeterminate',
        errorType: 'NoHistoryError', durationMs: 900, attempts: 1, error: new Error('lookup failed', { cause: noHistory }) });
      healthStepRecorder.lookup({ carrier: '17TRACK', finalStep: 'trawl', outcome: 'indeterminate',
        errorType: 'TrackingCaptureError', durationMs: 900, attempts: 1, error: new TrackingCaptureError('capture_missing', '17TRACK') });
    });
    expect([...samples.values()]).toEqual([
      expect.objectContaining({ subject: 'Postal Ninja', healthy: true,
        details: expect.objectContaining({ category: 'not_found', error_type: 'NoHistoryError' }) }),
      expect.objectContaining({ subject: '17TRACK', healthy: false, details: expect.objectContaining({ category: 'indeterminate' }) }),
    ]);
  });

  it("counts Ukrposhta's misses and a post's item without scans as answers, and other inconclusive lookups as failures", async () => {
    const samples = new Map<string, HealthSample>();
    const miss = (reason?: string) => new IndeterminateError('ukrposhta', undefined, reason ? { reason } : undefined);
    await observeTrackingHealth(samples, async () => {
      healthStepRecorder.step({ ...step, carrier: 'ukrposhta', outcome: 'indeterminate', errorType: 'IndeterminateError',
        error: miss('status_api_not_found') });
      healthStepRecorder.lookup({ carrier: 'ukrposhta', finalStep: 'browser', outcome: 'indeterminate', errorType: 'IndeterminateError',
        durationMs: 3000, attempts: 2, error: new Error('lookup failed', { cause: miss('portal_not_found') }) });
      healthStepRecorder.lookup({ carrier: 'slovenska-posta', finalStep: 'direct', outcome: 'indeterminate', errorType: 'IndeterminateError',
        durationMs: 1, attempts: 1, error: new IndeterminateError('Slovenská pošta', undefined, { reason: 'no_history' }) });
      healthStepRecorder.lookup({ carrier: 'dhl-ecommerce', finalStep: 'direct', outcome: 'indeterminate', errorType: 'IndeterminateError',
        durationMs: 1, attempts: 1, error: new IndeterminateError('DHL eCommerce', undefined, { reason: 'webtrack_not_found' }) });
      healthStepRecorder.lookup({ carrier: 'seur', finalStep: 'direct', outcome: 'indeterminate', errorType: 'IndeterminateError',
        durationMs: 1, attempts: 1, error: miss() });
    });
    expect([...samples.values()].map(({ kind, subject, healthy, details }) => [kind, subject, healthy, details.category])).toEqual([
      ['direct', 'ukrposhta', true, 'not_found'],
      ['provider', 'ukrposhta', true, 'not_found'],
      ['provider', 'slovenska-posta', true, 'not_found'],
      ['provider', 'dhl-ecommerce', false, 'indeterminate'],
      ['provider', 'seur', false, 'indeterminate'],
    ]);
  });

  it("leaves a confirmation's inconclusive answer out of provider health, and counts its real failures", async () => {
    const samples = new Map<string, HealthSample>();
    const lookup = (carrier: string, outcome: LookupRecord['outcome'], error: Error) => healthStepRecorder.lookup({
      carrier, finalStep: 'direct', outcome, errorType: error.name, durationMs: 1, attempts: 1, error });
    const empty = (provider: string) => new IndeterminateError(provider);
    await observeTrackingHealth(samples, async () => {
      // The parcel's own carrier answering so still fails.
      lookup('seur', 'indeterminate', empty('SEUR'));
      await confirmationLookup(async () => {
        // A partner listing an item it does not hold yet without scans, in its direct step and its lookup.
        healthStepRecorder.step({ ...step, carrier: 'spring-gds', outcome: 'indeterminate', errorType: 'IndeterminateError', error: empty('PostNL') });
        await Promise.resolve();
        lookup('spring-gds', 'indeterminate', new Error('lookup failed', { cause: empty('PostNL') }));
        lookup('posti', 'not_found', new NotFoundError('Posti'));
        lookup('bpost', 'indeterminate', new IndeterminateError('bpost', 'bpost is unavailable', { cause: new UpstreamHttpError('bpost', 503) }));
        lookup('evri', 'indeterminate', new TrackingCaptureError('capture_missing', 'Evri'));
        lookup('dpd', 'rate_limited', new RateLimitedError('DPD', 60_000));
        lookup('gls', 'schema', new SchemaError('GLS'));
      });
      // Outside the confirmation, an inconclusive answer counts again.
      lookup('australia-post', 'indeterminate', empty('Australia Post'));
    });
    expect([...samples.values()].map(({ kind, subject, healthy, details }) => [kind, subject, healthy, details.category])).toEqual([
      ['provider', 'seur', false, 'indeterminate'],
      ['provider', 'posti', true, 'not_found'],
      ['provider', 'bpost', false, 'indeterminate'],
      ['provider', 'evri', false, 'indeterminate'],
      ['provider', 'dpd', false, 'rate_limited'],
      ['provider', 'gls', false, 'schema'],
      ['provider', 'australia-post', false, 'indeterminate'],
    ]);
  });

  it('keeps one sample when direct is the only tier a carrier has', async () => {
    const samples = new Map<string, HealthSample>();
    await observeTrackingHealth(samples, async () => {
      healthStepRecorder.step({ ...step, carrier: 'swiss-post' });
      healthStepRecorder.lookup({ carrier: 'swiss-post', finalStep: 'direct', outcome: 'transport',
        errorType: 'UpstreamHttpError', durationMs: 200, attempts: 1, stepsAvailable: 1 });
    });
    expect([...samples.values()]).toEqual([expect.objectContaining({ kind: 'provider', subject: 'swiss-post', healthy: false })]);
  });

  it('keeps the direct sample when the one step a lookup had is not direct', async () => {
    const samples = new Map<string, HealthSample>();
    await observeTrackingHealth(samples, async () => {
      healthStepRecorder.step({ ...step, carrier: 'dpd-fr' });
      healthStepRecorder.lookup({ carrier: 'dpd-fr', finalStep: 'trawl', outcome: 'ok',
        errorType: null, durationMs: 200, attempts: 1, stepsAvailable: 1 });
    });
    expect([...samples.values()]).toEqual([
      expect.objectContaining({ kind: 'direct', subject: 'dpd-fr', healthy: false }),
      expect.objectContaining({ kind: 'provider', subject: 'dpd-fr', healthy: true }),
    ]);
  });

  it('reads an HTTP status only from a response a provider sent', async () => {
    const samples = new Map<string, HealthSample>();
    await observeTrackingHealth(samples, async () => {
      // Each kind of carrier error carries a status of its own: 404 for not found, 400 for a schema.
      healthStepRecorder.step({ ...step, carrier: 'paack', outcome: 'schema', errorType: 'SchemaError', error: new SchemaError('paack') });
      healthStepRecorder.lookup({ carrier: 'paack', finalStep: 'direct', outcome: 'not_found', errorType: 'NotFoundError',
        durationMs: 1, attempts: 1, error: new NotFoundError('paack') });
      healthStepRecorder.lookup({ carrier: 'Ship24', finalStep: 'direct', outcome: 'maintenance', errorType: 'UpstreamHttpError',
        durationMs: 1, attempts: 1, error: new Error('lookup failed', { cause: new UpstreamHttpError('Ship24', 503) }) });
    });
    expect([...samples.values()].map((sample) => sample.details.http_status)).toEqual([null, null, 503]);
  });

  it('keeps the reason a failure gave, so one carrier\'s challenges can be told apart', async () => {
    const samples = new Map<string, HealthSample>();
    await observeTrackingHealth(samples, async () => {
      healthStepRecorder.step({ ...step, carrier: 'zto', outcome: 'challenge', errorType: 'ChallengeError',
        error: new Error('wrapped', { cause: new ChallengeError('ZTO', 'Not verified', { reason: 'puzzle_unsolved' }) }) });
      healthStepRecorder.step({ ...step, carrier: 'paack', outcome: 'schema', errorType: 'SchemaError', error: new SchemaError('paack') });
    });
    expect([...samples.values()].map((sample) => sample.details.reason)).toEqual(['puzzle_unsolved', null]);
  });

  it('explains user impact, maintenance and rate-limit next steps', () => {
    expect(healthMessage({ subject: 'la-poste', kind: 'direct', state: 'open', attempts: 12, failures: 8, window_hours: 24,
      evidence: { http_status: 403 } })).toMatchObject({ title: expect.stringContaining('8/12'), nextSteps: expect.stringContaining('maintenance'), impact: expect.stringContaining('not the final shipment refresh') });
    expect(healthMessage({ subject: 'Ship24', evidence: { http_status: 429 } }).nextSteps).toContain('Retry-After');
    // A refusal the adapter read from the page, without a status to show for it.
    expect(healthMessage({ subject: 'Ship24', evidence: { category: 'rate_limited', http_status: null } }).nextSteps).toContain('Retry-After');
    expect(healthMessage({ subject: 'dpd', evidence: { category: 'maintenance', http_status: null } }).nextSteps).toContain('maintenance');
    expect(healthMessage({ subject: 'ups', state: 'recovered' }).nextSteps).toContain('No action needed');
    expect(healthMessage({ subject: 'ups', kind: 'refresh', state: 'closed' })).toMatchObject({
      title: 'Shipment refreshes closed without a recent check: ups', nextSteps: expect.stringContaining('no recovery was observed') });
    expect(healthMessage({ subject: 'dhl-express', kind: 'direct', state: 'open', attempts: 12, failures: 10, window_hours: 24,
      rescued: true }).title).toBe('Direct tracking repeatedly failing: dhl-express (10/12 in 24h; every refresh answered through another step)');
    expect(healthMessage({ subject: 'royal-mail', kind: 'refresh', state: 'open', attempts: 6, failures: 4, window_hours: 1,
      consecutive_failures: true }).title).toBe('Shipment refreshes repeatedly failing: royal-mail (4/6 in 1h; 3 checks in a row failed for two parcels, or for one while no other answered)');
    expect(healthMessage({ subject: 'dpd-fr', kind: 'direct', state: 'closed', reason: 'bypassed' })).toMatchObject({
      title: 'Direct tracking closed: dpd-fr answers through another step', nextSteps: expect.stringContaining('has not run for six hours') });
    expect(healthMessage({ subject: 'jd-logistics', kind: 'provider', state: 'closed', reason: 'verification_abstained' }))
      .toMatchObject({ title: expect.stringContaining('review signals'), impact: expect.stringContaining('No successful lookup or recovery'),
        nextSteps: expect.stringContaining('other lookup failures still open incidents') });
  });
});

it('keeps the failing tier as refresh evidence when the attempt only reports a deferral', async () => {
  const client = { completeSyncAttempt: vi.fn().mockResolvedValue(true),
    recordTrackingHealth: vi.fn().mockResolvedValue([]), ackTrackingHealth: vi.fn() };
  const audit = new TrackingSyncAudit(client as unknown as SupabaseServiceClient,
    'synthetic-package', 'TEST1234', 'ups', 'in_transit', { trigger: 'scheduled' });
  await audit.observeFetch(async () => {
    healthStepRecorder.step(step);
    healthStepRecorder.lookup({ carrier: 'ups', finalStep: 'direct', outcome: 'transport',
      errorType: 'UpstreamNetworkError', durationMs: 200, attempts: 1 });
  });
  const deferred = Object.assign(new Error('every provider failed'), { name: 'RoutingDeferredError' });
  await audit.finish({ outcome: 'error', error: deferred });
  expect(client.completeSyncAttempt).toHaveBeenCalledWith(expect.any(String),
    expect.objectContaining({ error_type: 'RoutingDeferredError' }), expect.any(Array));
  expect(client.recordTrackingHealth).toHaveBeenLastCalledWith(expect.any(String), 'synthetic-package',
    expect.arrayContaining([expect.objectContaining({ kind: 'refresh', healthy: false,
      details: expect.objectContaining({ error_type: 'UpstreamNetworkError' }) })]));
});

it('leaves a direct failure its lookup recovered from out of the refresh evidence', async () => {
  const client = { completeSyncAttempt: vi.fn().mockResolvedValue(true),
    recordTrackingHealth: vi.fn().mockResolvedValue([]), ackTrackingHealth: vi.fn() };
  const audit = new TrackingSyncAudit(client as unknown as SupabaseServiceClient,
    'synthetic-package', 'TEST1234', 'unknown', 'in_transit', { trigger: 'scheduled' });
  await audit.observeFetch(async () => {
    // The direct step's network error, then a retry the carrier answers without history.
    healthStepRecorder.step({ ...step, carrier: 'happy-post' });
    healthStepRecorder.step({ ...step, carrier: 'happy-post', step: 'retry', attempt: 2, outcome: 'not_found', errorType: 'NoHistoryError' });
    healthStepRecorder.lookup({ carrier: 'happy-post', finalStep: 'retry', outcome: 'not_found', errorType: 'NoHistoryError', durationMs: 300, attempts: 2 });
  });
  const deferred = Object.assign(new Error('every provider failed'), { name: 'RoutingDeferredError' });
  await audit.finish({ outcome: 'error', error: deferred, providerFailures: { 'happy-post': { kind: 'no_history' } } });
  const samples = client.recordTrackingHealth.mock.lastCall![2] as JsonObject[];
  // The direct step still counts as failed for its own incident.
  expect(samples).toContainEqual(expect.objectContaining({ kind: 'direct', subject: 'happy-post', healthy: false, recovered: true }));
  expect(samples.find((sample) => sample.kind === 'refresh')).toMatchObject({ healthy: false,
    details: { error_type: 'RoutingDeferredError', provider_failures: 'happy-post:no_history' } });
  expect((samples.find((sample) => sample.kind === 'refresh')!.details as JsonObject).step).toBeUndefined();
});

it('marks a failed lookup recovered once a retry of it answers', async () => {
  const samples = new Map<string, HealthSample>();
  await observeTrackingHealth(samples, async () => {
    healthStepRecorder.lookup({ carrier: 'ups', finalStep: 'direct', outcome: 'transport', errorType: 'UpstreamNetworkError', durationMs: 1, attempts: 1 });
    healthStepRecorder.lookup({ carrier: 'ups', finalStep: 'direct', outcome: 'ok', errorType: null, durationMs: 1, attempts: 1 });
  });
  expect(samples.get('provider:ups')).toMatchObject({ healthy: false, recovered: true });
});

it('gives a failed refresh the anomaly and each provider\'s failure kind as evidence', async () => {
  const client = { completeSyncAttempt: vi.fn().mockResolvedValue(true),
    recordTrackingHealth: vi.fn().mockResolvedValue([]), ackTrackingHealth: vi.fn() };
  const audit = () => new TrackingSyncAudit(client as unknown as SupabaseServiceClient,
    'synthetic-package', 'TEST1234', 'inpost', 'in_transit', { trigger: 'scheduled' });
  const failures = { ParcelsApp: { kind: 'no_history', count: 2, retry_at: '2026-01-01T00:00:00Z' },
    Ship24: { kind: 'transport', count: 1, retry_at: '2026-01-01T00:00:00Z' }, broken: null };
  const refreshSample = () => (client.recordTrackingHealth.mock.lastCall![2] as JsonObject[])
    .find((sample) => sample.kind === 'refresh')!;
  // The carrier answered without the progress already saved: no error object, every tier healthy.
  await audit().finish({ outcome: 'error', anomalyCodes: ['progress_disappeared', 'provider_clock_offset'],
    errorType: 'ProgressDisappeared', providerFailures: failures });
  expect(client.completeSyncAttempt).toHaveBeenLastCalledWith(expect.any(String), expect.objectContaining({ error_type: 'ProgressDisappeared' }), expect.any(Array));
  expect(refreshSample()).toMatchObject({ healthy: false, details: {
    error_type: 'ProgressDisappeared', anomaly_codes: 'progress_disappeared,provider_clock_offset', provider_failures: 'ParcelsApp:no_history,Ship24:transport',
  } });
  const deferred = Object.assign(new Error('every provider failed'), { name: 'RoutingDeferredError' });
  await audit().finish({ outcome: 'error', error: deferred, providerFailures: { 'la-poste': { kind: 'verification' } } });
  expect(refreshSample().details).toEqual({ error_type: 'RoutingDeferredError', provider_failures: 'la-poste:verification' });
  expect(JSON.stringify(refreshSample())).not.toContain('TEST1234');
  // A refresh that worked is no evidence, whatever a provider did.
  await audit().finish({ outcome: 'unchanged', anomalyCodes: ['provider_clock_offset'], providerFailures: failures });
  expect(refreshSample()).toEqual(expect.objectContaining({ healthy: true, details: {} }));
});

it("files a carrier's own lookup repeating its thin answer as answered from the second check in a row", async () => {
  const report = vi.spyOn(observability, 'captureOperationalError').mockReturnValue(null);
  const logged = vi.spyOn(observability, 'logOperationalEvent');
  const client = { completeSyncAttempt: vi.fn().mockResolvedValue(true), recordTrackingHealth: vi.fn().mockResolvedValue([]),
    ackTrackingHealth: vi.fn(), previousThinAnswer: vi.fn().mockResolvedValue(null) };
  const check = async (thinAnswer: string | undefined, lookup: Partial<LookupRecord> = {}) => {
    const audit = new TrackingSyncAudit(client as unknown as SupabaseServiceClient,
      'synthetic-package', 'TEST1234', 'jrt', 'in_transit', { trigger: 'scheduled' });
    await audit.observeFetch(async () => {
      healthStepRecorder.lookup({ carrier: 'jrt', finalStep: 'direct', outcome: 'ok', errorType: null, durationMs: 400, attempts: 1, ...lookup });
    });
    await audit.finish({ outcome: 'error', anomalyCodes: ['progress_disappeared'], errorType: 'ProgressDisappeared', thinAnswer });
    return (client.recordTrackingHealth.mock.lastCall![2] as JsonObject[]).find((sample) => sample.kind === 'refresh');
  };
  // The first is a failure, which keeps the answer for the next check.
  await expect(check('a1')).resolves.toMatchObject({ healthy: false,
    details: { error_type: 'ProgressDisappeared', anomaly_codes: 'progress_disappeared', thin_answer: 'a1' } });
  expect(client.previousThinAnswer).toHaveBeenCalledExactlyOnceWith('synthetic-package', 'jrt');
  client.previousThinAnswer.mockResolvedValue('a1');
  await expect(check('a1')).resolves.toEqual({ kind: 'refresh', subject: 'jrt', healthy: true,
    details: { category: 'repeated_answer', anomaly_codes: 'progress_disappeared', thin_answer: 'a1' } });
  // Another thin answer fails, as does the same one when the carrier's lookup did not answer normally.
  await expect(check('b2')).resolves.toMatchObject({ healthy: false, details: { thin_answer: 'b2' } });
  await expect(check('a1', { outcome: 'transport', errorType: 'UpstreamNetworkError' })).resolves.toMatchObject({ healthy: false });
  await expect(check('a1', { outcome: 'not_found', errorType: 'NotFoundError' })).resolves.toMatchObject({ healthy: false });
  // A check without a thin answer of the carrier's own has nothing to compare.
  client.previousThinAnswer.mockClear();
  await expect(check(undefined)).resolves.toMatchObject({ healthy: false });
  expect(client.previousThinAnswer).not.toHaveBeenCalled();
  // A previous answer that cannot be read leaves the check a failure.
  const failure = new Error('unreachable');
  client.previousThinAnswer.mockRejectedValue(failure);
  await expect(check('a1')).resolves.toMatchObject({ healthy: false, details: { thin_answer: 'a1' } });
  expect(report).toHaveBeenCalledExactlyOnceWith(failure, expect.objectContaining({ component: 'tracking-sync-audit', operation: 'read_thin_answer' }));
  expect(logged).toHaveBeenCalledWith('tracking_sync_audit_read_failed', expect.objectContaining({ operation: 'read_thin_answer' }), 'error');
  expect(logged).not.toHaveBeenCalledWith('tracking_sync_audit_write_failed', expect.anything(), expect.anything());
});

it('counts every finished attempt by who served it', async () => {
  const { metricsText } = await import('./metrics');
  const client = { completeSyncAttempt: vi.fn().mockResolvedValue(true), recordTrackingHealth: vi.fn().mockResolvedValue([]), ackTrackingHealth: vi.fn() };
  const finish = (completion: Parameters<TrackingSyncAudit['finish']>[0]) => new TrackingSyncAudit(
    client as unknown as SupabaseServiceClient, 'synthetic-package', 'TEST1234', 'chronopost', 'in_transit', { trigger: 'package' },
  ).finish(completion);
  await finish({ outcome: 'updated', sourceCarrier: 'chronopost' });
  await finish({ outcome: 'updated', sourceCarrier: 'unknown' });
  await finish({ outcome: 'error' });
  // New series read 0 on their first scrape; the second carries the counts.
  await metricsText();
  const text = await metricsText();
  expect(text).toContain('carrier_refresh_total{carrier="chronopost",served_by="adapter",outcome="updated"} 1');
  expect(text).toContain('carrier_refresh_total{carrier="chronopost",served_by="provider",outcome="updated"} 1');
  expect(text).toContain('carrier_refresh_total{carrier="chronopost",served_by="none",outcome="error"} 1');
});

it('evaluates only scheduled outcomes and acknowledges only flushed incident events', async () => {
  const capture = vi.spyOn(observability, 'captureTrackingHealth').mockReturnValue('event-id');
  const flush = vi.spyOn(observability, 'flushObservability').mockResolvedValue(false);
  const client = { completeSyncAttempt: vi.fn().mockResolvedValue(true),
    recordTrackingHealth: vi.fn().mockResolvedValue([{ id: 'notice-id' }]), ackTrackingHealth: vi.fn() };
  const audit = (trigger: 'package' | 'scheduled') => new TrackingSyncAudit(client as unknown as SupabaseServiceClient,
    'synthetic-package', 'TEST1234', 'ups', 'in_transit', { trigger });
  try {
    await audit('package').finish({ outcome: 'error' });
    expect(client.recordTrackingHealth).not.toHaveBeenCalled();
    const failed = audit('scheduled');
    failed.record('fetch', 'failed', 100);
    await failed.finish({ outcome: 'waiting' });
    expect(client.recordTrackingHealth).toHaveBeenLastCalledWith(expect.any(String), 'synthetic-package',
      [expect.objectContaining({ kind: 'refresh', healthy: false })]);
    expect(client.ackTrackingHealth).not.toHaveBeenCalled();
    flush.mockResolvedValue(true);
    await audit('scheduled').finish({ outcome: 'updated' });
    expect(client.ackTrackingHealth).toHaveBeenCalledWith(['notice-id']);
  } finally { capture.mockRestore(); flush.mockRestore(); }
});
