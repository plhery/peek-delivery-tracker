import { beforeEach, describe, expect, it, vi } from 'vitest';
import { IndeterminateError, NoHistoryError } from 'universal-parcel-scraper';

const metrics = { distribution: vi.fn(), count: vi.fn() };
const observability = {
  initObservability: vi.fn(() => true), logOperationalEvent: vi.fn(), reportRoutingEvent: vi.fn(),
  trackingAttemptId: vi.fn((): string | undefined => undefined), reportTrackingStep: vi.fn(), reportTrackingLookup: vi.fn(),
  errorDetails: vi.fn((error: unknown) => (error instanceof Error ? { error_message: error.message } : {})),
};

vi.mock('@sentry/node', () => ({ metrics }));
vi.mock('./observability', () => observability);

const { sentryStepRecorder, addStepRecorder, hostStepRecorder } = await import('./stepRecorder');

describe('sentryStepRecorder', () => {
  beforeEach(() => {
    metrics.distribution.mockClear();
    metrics.count.mockClear();
    observability.logOperationalEvent.mockClear();
    observability.reportRoutingEvent.mockClear();
    observability.trackingAttemptId.mockReset();
  });

  it('keeps the historical metric names and phase labels for a plain step', () => {
    sentryStepRecorder.step({
      carrier: 'dhl', step: 'direct', attempt: 1, outcome: 'ok', errorType: null, durationMs: 120.4,
      fallbackFrom: null, fallbackReason: null, fallbackErrorType: null,
    });
    const attributes = { carrier: 'dhl', phase: 'direct', outcome: 'success', error_type: 'none' };
    expect(metrics.distribution).toHaveBeenCalledWith('tracking.scrape.duration', 120.4, { unit: 'millisecond', attributes });
    expect(metrics.count).toHaveBeenCalledWith('tracking.scrape.attempts', 1, { attributes });
    expect(observability.logOperationalEvent).toHaveBeenCalledWith('tracking_scrape', { ...attributes, duration_ms: 120 });
    expect(observability.reportRoutingEvent).not.toHaveBeenCalled();
  });

  it('counts a fallback and reports the transport_fallback warning for recovery steps', () => {
    const previous = new Error('challenged');
    sentryStepRecorder.step({
      carrier: 'ups', step: 'trawl', attempt: 2, outcome: 'ok', errorType: null, durationMs: 3000,
      fallbackFrom: 'direct', fallbackReason: 'challenge', fallbackErrorType: 'ChallengeError', fallbackError: previous,
    });
    expect(observability.reportRoutingEvent).toHaveBeenCalledWith('transport_fallback', {
      carrier: 'ups', provider: 'ups', category: 'trawl', errorClass: 'ChallengeError', error: previous,
    });
    expect(metrics.count).toHaveBeenCalledWith('tracking.scrape.fallbacks', 1, {
      attributes: { carrier: 'ups', from_phase: 'direct', to_phase: 'trawl', error_type: 'ChallengeError' },
    });
  });

  it('counts a fallback but warns of none after a step that answered', () => {
    const recovery = { carrier: 'china-post', step: 'browser', attempt: 2, outcome: 'ok', errorType: null, durationMs: 4000, fallbackFrom: 'direct' } as const;
    sentryStepRecorder.step({ ...recovery, fallbackReason: 'input_required', fallbackErrorType: 'InputRequiredError' });
    sentryStepRecorder.step({
      ...recovery, carrier: 'dpd-de', step: 'abroad', fallbackFrom: 'app', fallbackReason: 'indeterminate', fallbackErrorType: 'IndeterminateError',
      fallbackError: new IndeterminateError('DPD Germany', 'Activity abroad', { reason: 'other_country' }),
    });
    expect(observability.reportRoutingEvent).not.toHaveBeenCalled();
    expect(metrics.count.mock.calls.filter(([name]) => name === 'tracking.scrape.fallbacks')).toHaveLength(2);
    sentryStepRecorder.step({
      ...recovery, fallbackReason: 'indeterminate', fallbackErrorType: 'IndeterminateError', fallbackError: new IndeterminateError('ups', 'Odd page'),
    });
    expect(observability.reportRoutingEvent).toHaveBeenCalledTimes(1);
  });

  it('records lookups as the total phase with the serving step', () => {
    sentryStepRecorder.lookup({ carrier: 'ups', finalStep: 'trawl', outcome: 'ok', errorType: null, durationMs: 3200, attempts: 2 });
    expect(observability.logOperationalEvent).toHaveBeenCalledWith('tracking_scrape', {
      carrier: 'ups', phase: 'total', outcome: 'success', error_type: 'none', duration_ms: 3200, final_step: 'trawl', attempts: 2,
    });
  });

  it('names the check a scrape ran for', () => {
    observability.trackingAttemptId.mockReturnValue('attempt-1');
    sentryStepRecorder.step({
      carrier: 'dhl', step: 'direct', attempt: 1, outcome: 'ok', errorType: null, durationMs: 10,
      fallbackFrom: null, fallbackReason: null, fallbackErrorType: null,
    });
    sentryStepRecorder.lookup({ carrier: 'dhl', finalStep: 'direct', outcome: 'ok', errorType: null, durationMs: 10, attempts: 1 });
    expect(observability.logOperationalEvent.mock.calls.map(([, fields]) => fields.attempt_id)).toEqual(['attempt-1', 'attempt-1']);
  });

  it('tells answers about the number apart from failures', () => {
    const record = { carrier: 'ups', finalStep: 'direct', durationMs: 50, attempts: 1 } as const;
    sentryStepRecorder.lookup({ ...record, outcome: 'not_found', errorType: 'NotFoundError' });
    sentryStepRecorder.lookup({ ...record, outcome: 'indeterminate', errorType: 'NoHistoryError', error: new NoHistoryError('ups', 'No scans yet') });
    sentryStepRecorder.lookup({ ...record, outcome: 'input_required', errorType: 'InputRequiredError' });
    sentryStepRecorder.lookup({ ...record, outcome: 'indeterminate', errorType: 'IndeterminateError' });
    sentryStepRecorder.lookup({ ...record, outcome: 'transport', errorType: 'TimeoutError' });
    sentryStepRecorder.lookup({
      ...record, outcome: 'indeterminate', errorType: 'IndeterminateError',
      error: new IndeterminateError('seur', 'Unknown shipment', { reason: 'portal_not_found' }),
    });
    sentryStepRecorder.lookup({
      ...record, outcome: 'indeterminate', errorType: 'IndeterminateError',
      error: new IndeterminateError('DPD Germany', 'Activity abroad', { reason: 'other_country' }),
    });
    expect(observability.logOperationalEvent.mock.calls.map(([, fields]) => fields.outcome))
      .toEqual(['not_found', 'no_history', 'input_required', 'error', 'error', 'no_history', 'handed_off']);
    expect(metrics.count).toHaveBeenCalledWith('tracking.scrape.attempts', 1, {
      attributes: { carrier: 'ups', phase: 'total', outcome: 'not_found', error_type: 'NotFoundError' },
    });
  });

  it('says why a step or lookup failed, and not why one answered', () => {
    const error = new IndeterminateError('city-express-rs', 'City Express did not open an anonymous session', { reason: 'session_rejected' });
    sentryStepRecorder.step({
      carrier: 'city-express-rs', step: 'direct', attempt: 1, outcome: 'indeterminate', errorType: 'IndeterminateError', durationMs: 900,
      fallbackFrom: null, fallbackReason: null, fallbackErrorType: null, error,
    });
    const record = { carrier: 'city-express-rs', finalStep: 'direct', durationMs: 900, attempts: 1 } as const;
    sentryStepRecorder.lookup({ ...record, outcome: 'indeterminate', errorType: 'IndeterminateError', error });
    sentryStepRecorder.lookup({ ...record, outcome: 'not_found', errorType: 'NotFoundError', error: new Error('No such shipment') });
    expect(observability.logOperationalEvent.mock.calls.map(([, fields]) => fields.error_message))
      .toEqual(['City Express did not open an anonymous session', 'City Express did not open an anonymous session', undefined]);
  });

  it('never throws when a sink fails', () => {
    metrics.count.mockImplementation(() => { throw new Error('sentry down'); });
    observability.logOperationalEvent.mockImplementation(() => { throw new Error('stdout closed'); });
    expect(() => sentryStepRecorder.lookup({ carrier: 'x', finalStep: null, outcome: 'error', errorType: 'Error', durationMs: 1, attempts: 0 })).not.toThrow();
  });

  it('feeds the Prometheus sink without a registration step', async () => {
    const { metricsText } = await import('./metrics');
    hostStepRecorder().lookup({ carrier: 'gls-de', finalStep: 'direct', outcome: 'ok', errorType: null, durationMs: 1, attempts: 1 });
    // A new series reads 0 on its first scrape; the second carries the count.
    await metricsText();
    expect(await metricsText()).toContain('carrier_lookup_total{carrier="gls-de",final_step="direct",outcome="ok",attempts="1",reason="none"} 1');
  });

  it('tells the running check which tier its lookup went through', () => {
    hostStepRecorder().step({ carrier: 'ups', step: 'trawl', attempt: 1, outcome: 'ok', errorType: null, durationMs: 1,
      fallbackFrom: null, fallbackReason: null, fallbackErrorType: null });
    expect(observability.reportTrackingStep).toHaveBeenCalledExactlyOnceWith('trawl');
  });

  it('tells the running check whom its lookup asked and how it ended', () => {
    hostStepRecorder().lookup({ carrier: 'parcelsapp', finalStep: 'direct', outcome: 'not_found', errorType: 'NotFoundError', durationMs: 1, attempts: 1 });
    expect(observability.reportTrackingLookup).toHaveBeenLastCalledWith('parcelsapp', 'not_found');
    hostStepRecorder().lookup({ carrier: 'dhl', finalStep: 'direct', outcome: 'ok', errorType: null, durationMs: 1, attempts: 1 });
    expect(observability.reportTrackingLookup).toHaveBeenLastCalledWith('dhl', 'success');
  });

  it('fans out to registered sinks', () => {
    const extra = { step: vi.fn(), lookup: vi.fn() };
    addStepRecorder(extra);
    hostStepRecorder().lookup({ carrier: 'x', finalStep: 'direct', outcome: 'ok', errorType: null, durationMs: 1, attempts: 1 });
    expect(extra.lookup).toHaveBeenCalledTimes(1);
  });
});
