import { afterEach, describe, expect, it, vi } from 'vitest';
import { ChallengeError, IndeterminateError, InputRequiredError, InvalidInputError, NotFoundError, TransportError } from 'universal-parcel-scraper';
import type { StepOutcome } from 'universal-parcel-scraper/node';
import { TrackingSyncAudit } from './trackingAudit';
import { healthStepRecorder } from './trackingHealth';
import { SupabaseServiceClient } from './supabase';
import { trackingSupportEvidence } from './trackingSupport';
import type { JsonObject } from './types';
import {
  captureOperationalError, captureTrackingHealth, logOperationalEvent, reportRoutingEvent, reportTrackingLookup, reportTrackingRecognition, reportTrackingStep,
} from './observability';
import { recordHealthIncident, recordRefresh, recordRoutingSignal, recordSyncAnomaly } from './metrics';

// Routing reports and the attempt they belong to stay real: signals travel through them.
vi.mock('./observability', async (importOriginal) => ({
  ...await importOriginal<typeof import('./observability')>(),
  captureOperationalError: vi.fn(), captureTrackingHealth: vi.fn(),
  flushObservability: vi.fn().mockResolvedValue(true), logOperationalEvent: vi.fn(),
}));
vi.mock('./metrics', () => ({
  recordRefresh: vi.fn(), recordRoutingSignal: vi.fn(), recordSyncAnomaly: vi.fn(), recordHealthIncident: vi.fn(),
}));

afterEach(() => {
  vi.restoreAllMocks();
  vi.clearAllMocks();
});

describe('tracking support audit persistence', () => {
  it('submits the original support context with a leased start and direct evidence with completion', async () => {
    const client = new SupabaseServiceClient('https://database.example', 'service-key');
    const request = vi.spyOn(client, 'request').mockResolvedValue(true);
    const lease = { jobId: 'synthetic-job', workerId: 'synthetic-worker' };
    const audit = new TrackingSyncAudit(client, 'synthetic-package', 'hl-123456789 rv', 'dhl', 'pending',
      { trigger: 'scheduled', jobId: lease.jobId, lease }, new Date('2026-09-10T12:00:00Z'));
    await audit.start();
    const evidence = trackingSupportEvidence({ tracking_number: 'HL123456789RV' }, {
      status: 'in_transit', current_stage: 'in_transit', last_update: '2026-09-10T11:00:00Z',
    }, 'chronopost', 'updated', false);
    await audit.finish({ outcome: 'updated', sourceCarrier: 'chronopost', supportEvidence: evidence, evaluateHealth: false });

    expect(request).toHaveBeenNthCalledWith(1, '/rest/v1/rpc/start_leased_sync_attempt', {
      method: 'POST', body: {
        p_attempt_id: audit.attemptId, p_job_id: lease.jobId, p_worker_id: lease.workerId,
        p_values: expect.objectContaining({
          package_id: 'synthetic-package', configured_carrier: 'dhl', started_at: '2026-09-10T12:00:00.000Z',
          support_context: expect.objectContaining({
            tracking_number: 'HL123456789RV', configured_carrier: 'dhl',
            reasons: ['carrier_mismatch'],
          }),
        }),
      },
    });
    expect(request).toHaveBeenNthCalledWith(2, '/rest/v1/rpc/complete_tracking_sync_attempt', {
      method: 'POST', body: {
        p_attempt_id: audit.attemptId,
        p_values: expect.objectContaining({ outcome: 'updated', source_carrier: 'chronopost' }),
        p_steps: [expect.objectContaining({
          step: 'complete', status: 'succeeded', details: {
            outcome: 'updated', support_lookup_number: 'HL123456789RV',
            support_provider: null, support_direct_progress: true, support_settled: false, support_gap_reason: 'ambiguous_shape',
          },
        })],
      },
    });
  });

  it('does not call a number another carrier tracked directly a mismatch', async () => {
    const client = new SupabaseServiceClient('https://database.example', 'service-key');
    const request = vi.spyOn(client, 'request').mockResolvedValue(true);
    const audit = new TrackingSyncAudit(client, 'synthetic-package', 'HL123456789RV', 'dhl', 'pending',
      { trigger: 'package' }, new Date('2026-09-10T12:00:00Z'), 'chronopost');
    await audit.start();
    expect(request).toHaveBeenNthCalledWith(1, '/rest/v1/tracking_sync_attempts', expect.objectContaining({
      body: expect.objectContaining({ support_context: expect.objectContaining({ reasons: [] }) }),
    }));
  });

  it.each([
    ['opens no case once the carrier the check filed the parcel under confirmed the number', 'janio', 'janio', []],
    ['still opens one while no carrier confirmed it', 'janio', null, ['ambiguous_shape']],
    ['still opens one while the parcel stays under no carrier', 'unknown', 'janio', ['ambiguous_shape']],
  ])('for a shape several carriers issue, %s', async (_case, carrier, answeredBy, gap) => {
    const client = new SupabaseServiceClient('https://database.example', 'service-key');
    const request = vi.spyOn(client, 'request').mockResolvedValue(true);
    // A parcel saved under no carrier, its number a shape Janio and AliExpress both issue.
    const audit = new TrackingSyncAudit(client, 'synthetic-package', 'JNCN000000000001', 'unknown', 'pending', { trigger: 'added' });
    await audit.start();
    audit.filed(carrier, answeredBy);
    await audit.finish({ outcome: 'updated', sourceCarrier: 'janio', evaluateHealth: false });
    expect(request).toHaveBeenNthCalledWith(1, '/rest/v1/tracking_sync_attempts', expect.objectContaining({
      body: expect.objectContaining({ support_context: expect.objectContaining({ configured_carrier: 'unknown', reasons: [] }) }),
    }));
    const [step] = (request.mock.calls[1][1] as { body: { p_steps: JsonObject[] } }).body.p_steps;
    expect([step.step, (step.details as JsonObject).support_gap_reason]).toEqual(['complete', ...gap.length ? gap : [undefined]]);
  });

  it('opens no case for a shape several carriers issue once the filed carrier says it holds the parcel', async () => {
    const gap = async (error?: Error) => {
      const client = new SupabaseServiceClient('https://database.example', 'service-key');
      const request = vi.spyOn(client, 'request').mockResolvedValue(true);
      // A synthetic 11-digit number, a shape several GLS countries issue, filed under GLS Germany.
      const audit = new TrackingSyncAudit(client, 'synthetic-package', '12345678901', 'gls-de', 'pending', { trigger: 'scheduled' });
      await audit.start();
      await audit.observeFetch(async () => {
        if (error) healthStepRecorder.lookup({ carrier: 'gls-de', finalStep: 'direct', outcome: 'input_required',
          errorType: error.name, durationMs: 1, attempts: 1, error });
        reportTrackingLookup('Ship24', 'success');
      });
      audit.filed('gls-de', null);
      await audit.finish({ outcome: 'unchanged', sourceCarrier: 'unknown', evaluateHealth: false });
      const [step] = (request.mock.calls[1][1] as { body: { p_steps: JsonObject[] } }).body.p_steps;
      return (step.details as JsonObject).support_gap_reason;
    };
    // GLS holds it for another postcode, or shows it only with one: the postcode is the owner's to correct.
    expect(await gap(new InputRequiredError('GLS', 'postcode', 'Synthetic', { reason: 'postcode_mismatch' }))).toBeUndefined();
    expect(await gap(new InputRequiredError('GLS', 'postcode', 'Synthetic', { reason: 'parcel_found' }))).toBeUndefined();
    // A postcode asked for without saying it holds the parcel confirms nothing, nor does no answer.
    expect(await gap(new InputRequiredError('GLS', 'postcode', 'Synthetic'))).toBe('ambiguous_shape');
    expect(await gap()).toBe('ambiguous_shape');
  });

  it('leaves the gap of a shape several carriers issue undecided while the filed carrier\'s lookup failed', async () => {
    const client = new SupabaseServiceClient('https://database.example', 'service-key');
    const request = vi.spyOn(client, 'request').mockResolvedValue(true);
    const audit = new TrackingSyncAudit(client, 'synthetic-package', 'JNCN000000000001', 'janio', 'pending', { trigger: 'added' });
    await audit.start();
    await audit.observeFetch(async () => {
      reportTrackingLookup('janio', 'error', 'rate_limited');
      reportTrackingLookup('ParcelsApp', 'success');
    });
    audit.filed('janio', null);
    await audit.finish({ outcome: 'updated', sourceCarrier: 'unknown', evaluateHealth: false });
    const [step] = (request.mock.calls[1][1] as { body: { p_steps: JsonObject[] } }).body.p_steps;
    expect(step.details).toMatchObject({ support_gap_reason: 'ambiguous_shape', support_gap_undecided: true });
  });

  it.each([
    ['opens no case once the filed carrier confirmed it', 'success', 'russian-post', {}],
    ['leaves the gap undecided while the filed carrier\'s lookup failed', 'error', null,
      { support_gap_reason: 'generic_postal', support_gap_undecided: true }],
    ['still opens one while the filed carrier answered without confirming it', 'not_found', null, { support_gap_reason: 'generic_postal' }],
  ] as const)('for an S10 number detection leaves to international post, %s', async (_case, lookup, answeredBy, gap) => {
    const client = new SupabaseServiceClient('https://database.example', 'service-key');
    const request = vi.spyOn(client, 'request').mockResolvedValue(true);
    // A synthetic S10 number filed under Russian Post on its first check: nothing confirmed it yet.
    const audit = new TrackingSyncAudit(client, 'synthetic-package', 'RR123456785RU', 'russian-post', 'pending', { trigger: 'added' });
    await audit.start();
    await audit.observeFetch(async () => {
      reportTrackingLookup('russian-post', lookup, lookup === 'error' ? 'transport' : undefined);
      reportTrackingLookup('ParcelsApp', 'success');
    });
    audit.filed('russian-post', answeredBy);
    await audit.finish({ outcome: 'updated', sourceCarrier: answeredBy ?? 'unknown', evaluateHealth: false });
    expect(request).toHaveBeenNthCalledWith(1, '/rest/v1/tracking_sync_attempts', expect.objectContaining({
      body: expect.objectContaining({ support_context: expect.objectContaining({ detection_carrier: 'intl-post', reasons: [] }) }),
    }));
    const [step] = (request.mock.calls[1][1] as { body: { p_steps: JsonObject[] } }).body.p_steps;
    const details = step.details as JsonObject;
    expect({ support_gap_reason: details.support_gap_reason, support_gap_undecided: details.support_gap_undecided })
      .toEqual({ support_gap_reason: undefined, support_gap_undecided: undefined, ...gap });
  });

  it('records a fallback success without losing the unresolved detection context', async () => {
    const client = new SupabaseServiceClient('https://database.example', 'service-key');
    const request = vi.spyOn(client, 'request').mockResolvedValue(true);
    const number = 'ZZTEST1234';
    const audit = new TrackingSyncAudit(client, 'synthetic-package', number, 'unknown', 'pending', { trigger: 'package' });
    await audit.start();
    await audit.finish({
      outcome: 'updated', sourceCarrier: 'unknown',
      supportEvidence: trackingSupportEvidence({ tracking_number: number }, {
        status: 'in_transit', current_stage: 'in_transit', last_update: '2026-09-10T11:00:00Z', tracking_provider: 'ParcelsApp',
      }, 'unknown', 'updated', false),
    });
    expect(request).toHaveBeenNthCalledWith(1, '/rest/v1/tracking_sync_attempts', {
      method: 'POST', prefer: 'return=minimal', body: expect.objectContaining({
        id: audit.attemptId,
        support_context: expect.objectContaining({ tracking_number: number, reasons: ['unknown_shape'] }),
      }),
    });
    expect(request).toHaveBeenNthCalledWith(2, '/rest/v1/rpc/complete_tracking_sync_attempt', {
      method: 'POST', body: expect.objectContaining({
        p_attempt_id: audit.attemptId, p_values: expect.objectContaining({ outcome: 'updated', source_carrier: 'unknown' }),
        p_steps: [expect.objectContaining({ details: {
          outcome: 'updated', support_lookup_number: number, support_provider: 'ParcelsApp', support_direct_progress: false,
          support_settled: false,
        } })],
      }),
    });
  });
});

describe('a check that found nothing new', () => {
  it('is recorded unchanged with its count of new scans, and still speaks for its carrier', async () => {
    const client = { completeSyncAttempt: vi.fn().mockResolvedValue(true),
      recordTrackingHealth: vi.fn().mockResolvedValue([]), ackTrackingHealth: vi.fn() };
    const audit = new TrackingSyncAudit(client as unknown as SupabaseServiceClient,
      'synthetic-package', 'TEST1234', 'dpd', 'in_transit', { trigger: 'scheduled' });
    await audit.finish({ outcome: 'unchanged', sourceCarrier: 'dpd', eventsNormalized: 3, eventsNew: 0 });
    expect(client.completeSyncAttempt).toHaveBeenCalledWith(audit.attemptId,
      expect.objectContaining({ outcome: 'unchanged', events_normalized: 3, events_new: 0 }), expect.any(Array));
    expect(client.recordTrackingHealth).toHaveBeenCalledWith(audit.attemptId, 'synthetic-package',
      [expect.objectContaining({ kind: 'refresh', subject: 'dpd', healthy: true })]);
    expect(logOperationalEvent).toHaveBeenCalledWith('tracking_sync_completed',
      expect.objectContaining({ outcome: 'unchanged', events_normalized: 3, events_new: 0 }), 'info');
    expect(recordRefresh).toHaveBeenCalledWith('dpd', 'dpd', 'unchanged');
  });
});

describe('a direct step\'s incident', () => {
  it('is a review signal: logged, counted and acknowledged without Sentry', async () => {
    const direct = { id: 'direct-notice', kind: 'direct', subject: 'dhl-express', state: 'open', rescued: false,
      attempts: 12, failures: 10, window_hours: 24 };
    const failing = { id: 'refresh-notice', kind: 'refresh', subject: 'dhl-express', state: 'open',
      attempts: 6, failures: 5, window_hours: 1 };
    vi.mocked(captureTrackingHealth).mockReturnValueOnce('synthetic-event');
    const client = { completeSyncAttempt: vi.fn().mockResolvedValue(true),
      recordTrackingHealth: vi.fn().mockResolvedValue([direct, failing]), ackTrackingHealth: vi.fn() };
    const audit = new TrackingSyncAudit(client as unknown as SupabaseServiceClient,
      'synthetic-package', 'TEST1234', 'dhl-express', 'in_transit', { trigger: 'scheduled' });
    await audit.finish({ outcome: 'unchanged', sourceCarrier: 'dhl-express' });
    // Only the failure that left a parcel without a fresh answer reaches Sentry.
    expect(captureTrackingHealth).toHaveBeenCalledTimes(1);
    expect(captureTrackingHealth).toHaveBeenCalledWith(failing);
    expect(client.ackTrackingHealth).toHaveBeenCalledWith(['direct-notice', 'refresh-notice']);
    expect(recordHealthIncident).toHaveBeenCalledWith('direct', 'dhl-express', 'open');
    expect(logOperationalEvent).toHaveBeenCalledWith('tracking_health_incident',
      expect.objectContaining({ kind: 'direct', state: 'open' }), 'info');
  });
});

describe('a check of a number its carrier does not issue', () => {
  it('is an answer the owner must act on, not a failed refresh of the carrier', async () => {
    const client = { completeSyncAttempt: vi.fn().mockResolvedValue(true),
      recordTrackingHealth: vi.fn().mockResolvedValue([]), ackTrackingHealth: vi.fn() };
    const audit = new TrackingSyncAudit(client as unknown as SupabaseServiceClient,
      'synthetic-package', 'TEST1234', 'nacex', 'pending', { trigger: 'scheduled' });
    const failures = { nacex: { kind: 'schema', count: 4, user_error: 'carrier:invalid_input' },
      Ship24: { kind: 'not_found', count: 1, user_error: 'carrier:not_found' }, '17TRACK': { kind: 'rate_limited', count: 4 } };
    await audit.finish({ outcome: 'error', sourceCarrier: 'nacex', providerFailures: failures });
    expect(client.recordTrackingHealth).toHaveBeenCalledWith(audit.attemptId, 'synthetic-package', [{
      kind: 'refresh', subject: 'nacex', healthy: true,
      details: { category: 'input_required', provider_failures: 'nacex:schema,Ship24:not_found,17TRACK:rate_limited' },
    }]);
    // Another carrier's refusal leaves this one's failed refresh a failure.
    const other = new TrackingSyncAudit(client as unknown as SupabaseServiceClient,
      'synthetic-package', 'TEST1234', 'seur', 'pending', { trigger: 'scheduled' });
    await other.finish({ outcome: 'error', sourceCarrier: 'seur', providerFailures: failures });
    expect(client.recordTrackingHealth).toHaveBeenLastCalledWith(other.attemptId, 'synthetic-package',
      [expect.objectContaining({ kind: 'refresh', subject: 'seur', healthy: false })]);
  });

  it('ends refused, which no error count of the carrier includes', async () => {
    const client = { completeSyncAttempt: vi.fn().mockResolvedValue(true),
      recordTrackingHealth: vi.fn().mockResolvedValue([]), ackTrackingHealth: vi.fn() };
    const failures = { nacex: { kind: 'schema', count: 1, user_error: 'carrier:invalid_input' } };
    const audit = new TrackingSyncAudit(client as unknown as SupabaseServiceClient,
      'synthetic-package', 'TEST1234', 'nacex', 'pending', { trigger: 'added' });
    await audit.finish({ outcome: 'error', sourceCarrier: 'nacex', providerFailures: failures });
    expect(client.completeSyncAttempt).toHaveBeenLastCalledWith(audit.attemptId,
      expect.objectContaining({ outcome: 'refused' }), [expect.objectContaining({ step: 'complete', details: expect.objectContaining({ outcome: 'refused' }) })]);
    expect(recordRefresh).toHaveBeenLastCalledWith('nacex', 'nacex', 'refused');
    expect(logOperationalEvent).toHaveBeenLastCalledWith('tracking_sync_completed', expect.objectContaining({ outcome: 'refused' }), 'info');
    // Another carrier's refusal leaves this one's failed check an error.
    const other = new TrackingSyncAudit(client as unknown as SupabaseServiceClient,
      'synthetic-package', 'TEST1234', 'seur', 'pending', { trigger: 'added' });
    await other.finish({ outcome: 'error', sourceCarrier: 'seur', providerFailures: failures });
    expect(client.completeSyncAttempt).toHaveBeenLastCalledWith(other.attemptId, expect.objectContaining({ outcome: 'error' }), expect.any(Array));
    expect(recordRefresh).toHaveBeenLastCalledWith('seur', 'seur', 'error');
    // A parcel that still waits keeps waiting.
    const waiting = new TrackingSyncAudit(client as unknown as SupabaseServiceClient,
      'synthetic-package', 'TEST1234', 'nacex', 'in_transit', { trigger: 'added' });
    await waiting.finish({ outcome: 'waiting', sourceCarrier: 'nacex', providerFailures: failures });
    expect(recordRefresh).toHaveBeenLastCalledWith('nacex', 'nacex', 'waiting');
  });

  it('is an answer too while the parcel still waits, or when the carrier refused it outside routing', async () => {
    const client = { completeSyncAttempt: vi.fn().mockResolvedValue(true),
      recordTrackingHealth: vi.fn().mockResolvedValue([]), ackTrackingHealth: vi.fn() };
    const refusal = new InvalidInputError('NACEX', 'The number is not a NACEX number');
    const waiting = new TrackingSyncAudit(client as unknown as SupabaseServiceClient,
      'synthetic-package', 'TEST1234', 'nacex', 'pending', { trigger: 'scheduled' });
    waiting.record('fetch', 'failed', 100, {}, new Error('Every tier deferred'));
    await waiting.finish({ outcome: 'waiting', sourceCarrier: 'nacex',
      providerFailures: { nacex: { kind: 'schema', count: 1, user_error: 'carrier:invalid_input' } } });
    expect(client.recordTrackingHealth).toHaveBeenLastCalledWith(waiting.attemptId, 'synthetic-package',
      [expect.objectContaining({ kind: 'refresh', subject: 'nacex', healthy: true, details: expect.objectContaining({ category: 'input_required' }) })]);
    const direct = new TrackingSyncAudit(client as unknown as SupabaseServiceClient,
      'synthetic-package', 'TEST1234', 'nacex', 'pending', { trigger: 'scheduled' });
    direct.record('fetch', 'failed', 100, {}, refusal);
    await direct.finish({ outcome: 'error', sourceCarrier: 'nacex', error: refusal });
    expect(client.recordTrackingHealth).toHaveBeenLastCalledWith(direct.attemptId, 'synthetic-package',
      [expect.objectContaining({ kind: 'refresh', subject: 'nacex', healthy: true })]);
  });
});

describe('a carrier\'s lookup in a check', () => {
  it('reads as its failure\'s category, ok once a retry answered past it, and not_asked when none ran', async () => {
    const audit = new TrackingSyncAudit({} as SupabaseServiceClient, 'synthetic-package', 'TEST1234', 'nacex', 'pending', { trigger: 'package' });
    expect(audit.lookupOutcome('nacex')).toBe('not_asked');
    const lookup = { carrier: 'nacex', finalStep: 'direct', durationMs: 1, attempts: 1 };
    await audit.observeFetch(async () => healthStepRecorder.lookup({ ...lookup, outcome: 'transport', errorType: 'TransportError' }));
    expect(audit.lookupOutcome('nacex')).toBe('transport');
    await audit.observeFetch(async () => healthStepRecorder.lookup({ ...lookup, outcome: 'ok', errorType: null }));
    expect(audit.lookupOutcome('nacex')).toBe('ok');
  });

  it('says why an inconclusive lookup failed, until a later one answered', async () => {
    const audit = new TrackingSyncAudit({} as SupabaseServiceClient, 'synthetic-package', 'TEST1234', 'ctt-express', 'pending', { trigger: 'package' });
    const lookup = { carrier: 'ctt-express', finalStep: 'retry', durationMs: 1, attempts: 2 };
    expect(audit.lookupReason('ctt-express')).toBeNull();
    const failure = new IndeterminateError('CTT Express', 'CTT Express returned no tracking scans');
    await audit.observeFetch(async () => {
      healthStepRecorder.lookup({ ...lookup, outcome: 'indeterminate', errorType: failure.name, error: failure });
      reportTrackingLookup('ctt-express', 'error', 'CTT Express returned no tracking scans');
    });
    expect(audit.lookupOutcome('ctt-express')).toBe('indeterminate');
    expect(audit.lookupReason('ctt-express')).toBe('CTT Express returned no tracking scans');
    await audit.observeFetch(async () => {
      healthStepRecorder.lookup({ ...lookup, outcome: 'ok', errorType: null });
      reportTrackingLookup('ctt-express', 'success');
    });
    expect(audit.lookupReason('ctt-express')).toBeNull();
  });
});

describe('the lookups of a check', () => {
  it('name on its row whom they asked and how each ended, so a refused check still shows them', async () => {
    const client = { completeSyncAttempt: vi.fn().mockResolvedValue(true),
      recordTrackingHealth: vi.fn().mockResolvedValue([]), ackTrackingHealth: vi.fn() };
    const audit = new TrackingSyncAudit(client as unknown as SupabaseServiceClient,
      'synthetic-package', 'TEST1234', 'nacex', 'pending', { trigger: 'added' });
    await audit.observeFetch(async () => {
      reportTrackingLookup('nacex', 'invalid_input');
      reportTrackingLookup('parcelsapp', 'success');
      // A success stays: a later lookup of the same provider in the check failing does not hide it.
      reportTrackingLookup('parcelsapp', 'error');
    });
    reportTrackingLookup('dhl', 'success');
    await audit.finish({ outcome: 'error', sourceCarrier: 'nacex',
      providerFailures: { nacex: { kind: 'schema', count: 1, user_error: 'carrier:invalid_input' } } });
    expect(client.completeSyncAttempt).toHaveBeenLastCalledWith(audit.attemptId,
      expect.objectContaining({ outcome: 'refused', lookups: { nacex: 'invalid_input', parcelsapp: 'success' } }), expect.any(Array));
    // A check that asked nobody says so; shutdown keeps what the interrupted one asked.
    const unasked = new TrackingSyncAudit(client as unknown as SupabaseServiceClient,
      'synthetic-package', 'TEST1234', 'nacex', 'pending', { trigger: 'added' });
    await unasked.finish({ outcome: 'waiting', sourceCarrier: 'nacex' });
    expect(client.completeSyncAttempt).toHaveBeenLastCalledWith(unasked.attemptId, expect.objectContaining({ lookups: {} }), expect.any(Array));
    const interrupted = new TrackingSyncAudit(client as unknown as SupabaseServiceClient,
      'synthetic-package', 'TEST1234', 'nacex', 'pending', { trigger: 'added' });
    await interrupted.observeFetch(async () => reportTrackingLookup('nacex', 'transport'));
    await interrupted.interrupt();
    expect(client.completeSyncAttempt).toHaveBeenLastCalledWith(interrupted.attemptId,
      expect.objectContaining({ outcome: 'interrupted', lookups: { nacex: 'transport' } }), expect.any(Array), { timeoutMs: 3_000 });
  });

  it('say on the fetch step which path they took, step by step', async () => {
    const client = { completeSyncAttempt: vi.fn().mockResolvedValue(true) };
    const audit = new TrackingSyncAudit(client as unknown as SupabaseServiceClient,
      'synthetic-package', 'TEST1234', 'poste-italiane', 'in_transit', { trigger: 'package' });
    await audit.observeFetch(async () => {
      reportTrackingStep('direct', 'poste-italiane', 'challenge');
      reportTrackingStep('browser', 'poste-italiane', 'transport');
      reportTrackingStep('api', '17track', 'ok');
    });
    audit.record('fetch', 'succeeded', 1, { source_carrier: 'unknown' });
    audit.record('normalize', 'succeeded', 1);
    await audit.finish({ outcome: 'unchanged', sourceCarrier: 'unknown', evaluateHealth: false });
    const steps = client.completeSyncAttempt.mock.lastCall![2] as { step: string; details: JsonObject }[];
    expect(steps.find((step) => step.step === 'fetch')!.details).toEqual({
      source_carrier: 'unknown', lookup_trail: 'poste-italiane direct:challenge>browser:transport; 17track api:ok',
    });
    expect(steps.find((step) => step.step === 'normalize')!.details).toEqual({});
    // A check that ran no lookup step, as one reusing an answer of the run, has no trail; a long one is bounded.
    const reused = new TrackingSyncAudit(client as unknown as SupabaseServiceClient,
      'synthetic-package', 'TEST1234', 'poste-italiane', 'in_transit', { trigger: 'package' });
    reused.record('fetch', 'succeeded', 1, { source_carrier: 'poste-italiane' });
    expect(reused.lookupTrail).toBeNull();
    const long = new TrackingSyncAudit(client as unknown as SupabaseServiceClient,
      'synthetic-package', 'TEST1234', 'poste-italiane', 'in_transit', { trigger: 'package' });
    await long.observeFetch(async () => {
      for (let index = 0; index < 40; index += 1) reportTrackingStep('direct', 'poste-italiane', 'transport');
    });
    expect(long.lookupTrail).toBe(`poste-italiane ${Array(24).fill('direct:transport').join('>')}`);
  });

  it("show a recognition answer only for a carrier whose lookup left no step of its own", async () => {
    const client = { completeSyncAttempt: vi.fn().mockResolvedValue(true) };
    const audit = new TrackingSyncAudit(client as unknown as SupabaseServiceClient,
      'synthetic-package', '1234567890', 'unknown', 'pending', { trigger: 'package' });
    await audit.observeFetch(async () => {
      reportTrackingStep('api', 'acs-courier', 'not_found');
      reportTrackingRecognition('acs-courier', 'unknown');
      reportTrackingRecognition('dhl-express', 'unknown');
      reportTrackingStep('api', 'ParcelsApp', 'ok');
    });
    expect(audit.lookupTrail).toBe('acs-courier api:not_found; dhl-express recognize:unknown; ParcelsApp api:ok');
  });

  it('keep at most 32 carriers and providers, each name bounded', async () => {
    const client = { completeSyncAttempt: vi.fn().mockResolvedValue(true) };
    const audit = new TrackingSyncAudit(client as unknown as SupabaseServiceClient,
      'synthetic-package', 'TEST1234', 'nacex', 'pending', { trigger: 'package' });
    await audit.observeFetch(async () => {
      for (let index = 0; index < 40; index += 1) reportTrackingLookup(`carrier-${index}`.padEnd(120, 'x'), 'not_found');
    });
    await audit.finish({ outcome: 'waiting', sourceCarrier: 'nacex', evaluateHealth: false });
    const lookups = client.completeSyncAttempt.mock.lastCall![1].lookups as Record<string, string>;
    expect(Object.keys(lookups)).toHaveLength(32);
    expect(Object.keys(lookups).every((carrier) => carrier.length === 100)).toBe(true);
  });
});

describe('a check of a number that names several parcels', () => {
  it('is an answer the owner must act on, and still a failure when the carrier failed otherwise', async () => {
    const client = { completeSyncAttempt: vi.fn().mockResolvedValue(true),
      recordTrackingHealth: vi.fn().mockResolvedValue([]), ackTrackingHealth: vi.fn() };
    const check = async (error: Error) => {
      const audit = new TrackingSyncAudit(client as unknown as SupabaseServiceClient,
        'synthetic-package', 'TEST1234', 'australia-post', 'pending', { trigger: 'scheduled' });
      await audit.observeFetch(async () => healthStepRecorder.lookup({ carrier: 'australia-post', finalStep: 'direct',
        outcome: 'indeterminate', errorType: 'IndeterminateError', durationMs: 1, attempts: 1, error }));
      audit.record('fetch', 'failed', 100, {}, new Error('Every tier deferred'));
      await audit.finish({ outcome: 'error', sourceCarrier: 'australia-post',
        providerFailures: { 'australia-post': { kind: 'transport', count: 1, user_error: 'carrier:indeterminate' } } });
      return client.recordTrackingHealth.mock.lastCall![2] as { kind: string; healthy: boolean; details: { category?: string } }[];
    };
    const several = await check(new IndeterminateError('Australia Post', undefined, { reason: 'several_parcels' }));
    expect(several.find((sample) => sample.kind === 'refresh')).toMatchObject({ healthy: true, details: { category: 'input_required' } });
    const failed = await check(new IndeterminateError('Australia Post'));
    expect(failed.find((sample) => sample.kind === 'refresh')).toMatchObject({ healthy: false });
  });
});

describe('a check whose carrier declined its verification on purpose', () => {
  const client = () => ({ completeSyncAttempt: vi.fn().mockResolvedValue(true),
    recordTrackingHealth: vi.fn().mockResolvedValue([]), ackTrackingHealth: vi.fn() });
  const lookup = (carrier: string, outcome: StepOutcome, error: Error) => healthStepRecorder.lookup({ carrier, finalStep: 'trawl', outcome,
    errorType: error.name, durationMs: 1, attempts: 1, error });
  const declined = () => new ChallengeError('JD Logistics', 'The verification stayed unread', { reason: 'verification_unreadable' });
  const unknown = () => new NotFoundError('ParcelsApp');
  const check = async (database: ReturnType<typeof client>, lookups: () => void, failures: JsonObject) => {
    const audit = new TrackingSyncAudit(database as unknown as SupabaseServiceClient,
      'synthetic-package', 'TEST1234', 'jd-logistics', 'in_transit', { trigger: 'scheduled' });
    await audit.observeFetch(async () => lookups());
    audit.record('fetch', 'failed', 100, {}, new Error('No source answered'));
    await audit.finish({ outcome: 'error', sourceCarrier: 'jd-logistics', providerFailures: failures });
    return (database.recordTrackingHealth.mock.lastCall?.[2] ?? []) as { kind: string; subject: string; healthy: boolean }[];
  };

  it('records its lookups but no refresh, in that check and in those its cooldown defers', async () => {
    const database = client();
    const failures = { 'jd-logistics': { kind: 'verification', count: 1, user_error: 'carrier:challenge', abstained: true },
      ParcelsApp: { kind: 'not_found', count: 1 } };
    const samples = await check(database, () => { lookup('jd-logistics', 'challenge', declined()); lookup('ParcelsApp', 'not_found', unknown()); }, failures);
    expect(samples.map(({ kind, subject, healthy }) => [kind, subject, healthy]))
      .toEqual([['provider', 'jd-logistics', false], ['provider', 'ParcelsApp', true]]);
    const deferred = await check(database, () => lookup('ParcelsApp', 'not_found', unknown()), failures);
    expect(deferred.map(({ kind }) => kind)).toEqual(['provider']);
    // A cooldown that asked nobody records nothing, as before.
    database.recordTrackingHealth.mockClear();
    await check(database, () => undefined, failures);
    expect(database.recordTrackingHealth).not.toHaveBeenCalled();
  });

  it('still fails a refresh when the carrier was refused or blocked, or another source failed too', async () => {
    const database = client();
    const blocked = { 'jd-logistics': { kind: 'verification', count: 1, user_error: 'carrier:challenge' } };
    const refresh = (samples: { kind: string; healthy: boolean }[]) => samples.find((sample) => sample.kind === 'refresh');
    expect(refresh(await check(database, () => lookup('jd-logistics', 'challenge', new ChallengeError('JD Logistics')), blocked)))
      .toMatchObject({ healthy: false });
    expect(refresh(await check(database, () => lookup('ParcelsApp', 'not_found', unknown()), blocked))).toMatchObject({ healthy: false });
    expect(refresh(await check(database, () => {
      lookup('jd-logistics', 'challenge', declined());
      lookup('ParcelsApp', 'transport', new TransportError('ParcelsApp'));
    }, blocked))).toMatchObject({ healthy: false });
  });
});

describe('a check while the carrier waits for the owner', () => {
  it('is an answer when the carrier, not asked again yet, last asked for an input or found several parcels', async () => {
    const client = { completeSyncAttempt: vi.fn().mockResolvedValue(true),
      recordTrackingHealth: vi.fn().mockResolvedValue([]), ackTrackingHealth: vi.fn() };
    for (const userError of ['carrier:input_required', 'carrier:several_parcels']) {
      const audit = new TrackingSyncAudit(client as unknown as SupabaseServiceClient,
        'synthetic-package', 'TEST1234', 'heppner', 'pending', { trigger: 'scheduled' });
      audit.record('fetch', 'failed', 100, {}, new Error('Every tier deferred'));
      await audit.finish({ outcome: 'error', sourceCarrier: 'heppner', providerFailures: {
        heppner: { kind: 'input_required', count: 1, user_error: userError }, Ship24: { kind: 'not_found', count: 1 } } });
      expect(client.recordTrackingHealth).toHaveBeenLastCalledWith(audit.attemptId, 'synthetic-package',
        [expect.objectContaining({ kind: 'refresh', subject: 'heppner', healthy: true, details: expect.objectContaining({ category: 'input_required' }) })]);
    }
  });
});

describe('a check of a parcel handed over to its delivery carrier', () => {
  const handedOver = () => {
    const client = { completeSyncAttempt: vi.fn().mockResolvedValue(true), recordTrackingHealth: vi.fn().mockResolvedValue([]),
      ackTrackingHealth: vi.fn(), previousThinAnswer: vi.fn().mockResolvedValue('thin-1') };
    const audit = new TrackingSyncAudit(client as unknown as SupabaseServiceClient, 'synthetic-package', 'TEST1234', 'gls-de',
      'in_transit', { trigger: 'scheduled' }, new Date(), null, null, 'posti');
    const refresh = () => (client.recordTrackingHealth.mock.lastCall![2] as JsonObject[]).find((sample) => sample.kind === 'refresh');
    return { audit, client, refresh };
  };

  it('fails the delivery carrier\'s refresh while it is down, though the filed carrier asks for an input', async () => {
    const { audit, client, refresh } = handedOver();
    await audit.observeFetch(async () => {
      healthStepRecorder.lookup({ carrier: 'posti', finalStep: 'direct', outcome: 'transport', errorType: 'TransportError', durationMs: 1, attempts: 1 });
      healthStepRecorder.lookup({ carrier: 'gls-de', finalStep: 'direct', outcome: 'input_required', errorType: 'InputRequiredError', durationMs: 1, attempts: 1 });
    });
    audit.record('fetch', 'failed', 1, {});
    await audit.finish({ outcome: 'error', sourceCarrier: null, providerFailures: {
      posti: { kind: 'transport', count: 1 }, 'gls-de': { kind: 'input_required', count: 1, user_error: 'carrier:input_required' } } });
    expect(refresh()).toMatchObject({ subject: 'posti', healthy: false });
    expect(client.completeSyncAttempt.mock.lastCall![1]).toMatchObject({ outcome: 'error' });
  });

  it('reads the delivery carrier\'s previous thin answer when it repeats one', async () => {
    const { audit, client, refresh } = handedOver();
    await audit.observeFetch(async () => {
      healthStepRecorder.lookup({ carrier: 'posti', finalStep: 'direct', outcome: 'ok', errorType: null, durationMs: 1, attempts: 1 });
    });
    await audit.finish({ outcome: 'error', sourceCarrier: 'posti', errorType: 'ProgressDisappeared', thinAnswer: 'thin-1' });
    expect(client.previousThinAnswer).toHaveBeenCalledWith('synthetic-package', 'posti');
    expect(refresh()).toMatchObject({ subject: 'posti', healthy: true, details: expect.objectContaining({ category: 'repeated_answer' }) });
  });
});

describe('review signals', () => {
  it('saves the routing signals a check reported, once each, after the check itself', async () => {
    const client = { completeSyncAttempt: vi.fn().mockResolvedValue(true), recordTrackingRoutingSignals: vi.fn() };
    const audit = new TrackingSyncAudit(client as unknown as SupabaseServiceClient,
      'synthetic-package', 'TEST1234', 'unknown', 'pending', { trigger: 'package' });
    await audit.observeFetch(async () => {
      reportRoutingEvent('carrier_coverage_discovered', { carrier: 'unknown', provider: 'Example Parcel Co' });
      reportRoutingEvent('carrier_coverage_discovered', { carrier: 'unknown', provider: 'Example Parcel Co' });
      reportRoutingEvent('provider_failed', { carrier: 'unknown', provider: '17TRACK', category: 'rate_limited' });
    });
    // A correction detection expected is no signal.
    audit.reportRouting('carrier_auto_swapped', { carrier: 'unknown', provider: 'gls', category: 'detected' });
    audit.reportRouting('carrier_auto_swapped', { carrier: 'dhl', provider: 'ups', trackingNumber: 'TEST1234' });
    // Outside a check, a signal has nowhere to go.
    reportRoutingEvent('direct_support_opportunity', { carrier: 'fedex', provider: 'fedex' });
    expect(client.recordTrackingRoutingSignals).not.toHaveBeenCalled();
    await audit.finish({ outcome: 'updated', sourceCarrier: 'unknown' });
    expect(client.recordTrackingRoutingSignals).toHaveBeenCalledExactlyOnceWith(audit.attemptId, 'synthetic-package', 'TEST1234', [
      { signal: 'carrier_coverage_discovered', carrier: 'unknown', subject: 'Example Parcel Co' },
      { signal: 'carrier_auto_swapped', carrier: 'dhl', subject: 'ups' },
    ]);
    expect(client.completeSyncAttempt.mock.invocationCallOrder[0])
      .toBeLessThan(client.recordTrackingRoutingSignals.mock.invocationCallOrder[0]);
    expect(vi.mocked(recordRoutingSignal).mock.calls).toEqual([
      ['carrier_coverage_discovered', 'unknown', 'Example Parcel Co'], ['carrier_auto_swapped', 'dhl', 'ups'],
    ]);
  });

  it('logs and counts each anomaly once, without an error report', () => {
    const audit = new TrackingSyncAudit({} as SupabaseServiceClient,
      'synthetic-package', 'TEST1234', 'dpd', 'in_transit', { trigger: 'scheduled' });
    audit.reportAnomalies(['progress_disappeared', 'progress_disappeared'], { providerStatus: 'pending', eventsReceived: 0 });
    expect(vi.mocked(logOperationalEvent).mock.calls.filter(([event]) => event === 'tracking_sync_anomaly')).toEqual([
      ['tracking_sync_anomaly', expect.objectContaining({ anomaly_code: 'progress_disappeared', carrier: 'dpd',
        previous_stage: 'in_transit', provider_status: 'pending', events_received: 0 }), 'warning'],
    ]);
    expect(recordSyncAnomaly).toHaveBeenCalledExactlyOnceWith('dpd', 'progress_disappeared');
    expect(captureOperationalError).not.toHaveBeenCalled();
  });

  it('names the stored row an anomaly is about on its line only', () => {
    const audit = new TrackingSyncAudit({} as SupabaseServiceClient,
      'synthetic-package', 'TEST1234', 'usps', 'in_transit', { trigger: 'scheduled' });
    audit.reportAnomalies(['provider_clock_offset', 'early_stage_regression'], { providerStatus: 'in_transit' },
      { provider_clock_offset: 'usps:synthetic-scan' });
    const lines = vi.mocked(logOperationalEvent).mock.calls.filter(([event]) => event === 'tracking_sync_anomaly')
      .map(([, fields]) => fields as Record<string, unknown>);
    expect(lines.map((line) => [line.anomaly_code, line.scan_id])).toEqual([
      ['provider_clock_offset', 'usps:synthetic-scan'], ['early_stage_regression', undefined]]);
    expect(lines[1]).not.toHaveProperty('scan_id');
  });

  it('acknowledges a recovery or a closing without Sentry, and an outage once Sentry has it', async () => {
    const recovered = { id: 'recovered-incident', kind: 'provider', subject: '17TRACK', state: 'recovered',
      attempts: 5, failures: 0, window_hours: 24 };
    const open = { id: 'open-incident', kind: 'provider', subject: 'dpd', state: 'open', attempts: 12, failures: 8, window_hours: 24 };
    const closed = { id: 'closed-incident', kind: 'direct', subject: 'gls', state: 'closed', attempts: 0, failures: 0, window_hours: 24 };
    const client = { completeSyncAttempt: vi.fn().mockResolvedValue(true),
      recordTrackingHealth: vi.fn().mockResolvedValue([recovered, open, closed]), ackTrackingHealth: vi.fn() };
    vi.mocked(captureTrackingHealth).mockReturnValue('synthetic-event');
    const audit = new TrackingSyncAudit(client as unknown as SupabaseServiceClient,
      'synthetic-package', 'TEST1234', 'dpd', 'in_transit', { trigger: 'scheduled' });
    await audit.finish({ outcome: 'unchanged', sourceCarrier: 'dpd' });
    expect(captureTrackingHealth).toHaveBeenCalledExactlyOnceWith(open);
    expect(client.ackTrackingHealth).toHaveBeenCalledExactlyOnceWith(['recovered-incident', 'closed-incident', 'open-incident']);
    expect(vi.mocked(recordHealthIncident).mock.calls)
      .toEqual([['provider', '17TRACK', 'recovered'], ['provider', 'dpd', 'open'], ['direct', 'gls', 'closed']]);
    expect(logOperationalEvent).toHaveBeenCalledWith('tracking_health_incident', expect.objectContaining({
      kind: 'provider', subject: '17TRACK', state: 'recovered', title: 'Provider lookups recovered: 17TRACK' }), 'info');
    expect(logOperationalEvent).toHaveBeenCalledWith('tracking_health_incident', expect.objectContaining({
      kind: 'direct', subject: 'gls', state: 'closed', title: 'Direct tracking closed without a recent check: gls' }), 'info');

    // Without Sentry the outage waits for the next check; the recovery does not.
    vi.mocked(captureTrackingHealth).mockReturnValue(null);
    client.ackTrackingHealth.mockClear();
    const next = new TrackingSyncAudit(client as unknown as SupabaseServiceClient,
      'synthetic-package', 'TEST1234', 'dpd', 'in_transit', { trigger: 'scheduled' });
    await next.finish({ outcome: 'unchanged', sourceCarrier: 'dpd' });
    expect(client.ackTrackingHealth).toHaveBeenCalledExactlyOnceWith(['recovered-incident', 'closed-incident']);
  });
});
