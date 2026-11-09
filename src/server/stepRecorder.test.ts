import { beforeEach, describe, expect, it, vi } from 'vitest';
import { IndeterminateError, NoHistoryError } from 'universal-parcel-scraper';

const metrics = { distribution: vi.fn(), count: vi.fn() };
const observability = {
  initObservability: vi.fn(() => true), logOperationalEvent: vi.fn(), reportRoutingEvent: vi.fn(),
  trackingAttemptId: vi.fn((): string | undefined => undefined), reportTrackingStep: vi.fn(), reportTrackingLookup: vi.fn(),
  errorDetails: vi.fn((error: unknown): Record<string, unknown> => (error instanceof Error ? { error_message: error.message } : {})),
};

vi.mock('@sentry/node', () => ({ metrics }));
vi.mock('./observability', () => observability);

const { sentryStepRecorder, addStepRecorder, hostStepRecorder, lookupVerdict, markLookup, unmarkLookups, withHeldLookups, withLookupVerdicts } = await import('./stepRecorder');

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
    expect(observability.logOperationalEvent).toHaveBeenCalledWith('tracking_scrape', { ...attributes, duration_ms: 120, attempt: 1 });
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
    expect(observability.logOperationalEvent).toHaveBeenCalledWith('tracking_scrape', expect.objectContaining({
      attempt: 2, fallback_from: 'direct', fallback_reason: 'challenge', outcome: 'success',
    }));
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

  it('tells the running check which tier its lookup went through, for whom, and how it ended', () => {
    hostStepRecorder().step({ carrier: 'ups', step: 'trawl', attempt: 1, outcome: 'ok', errorType: null, durationMs: 1,
      fallbackFrom: null, fallbackReason: null, fallbackErrorType: null });
    expect(observability.reportTrackingStep).toHaveBeenCalledExactlyOnceWith('trawl', 'ups', 'ok');
    hostStepRecorder().step({ carrier: 'poste-italiane', step: 'direct', attempt: 1, outcome: 'challenge', errorType: 'ChallengeError',
      durationMs: 1, fallbackFrom: null, fallbackReason: null, fallbackErrorType: null });
    expect(observability.reportTrackingStep).toHaveBeenLastCalledWith('direct', 'poste-italiane', 'challenge');
  });

  it('tells the running check whom its lookup asked and how it ended', () => {
    hostStepRecorder().lookup({ carrier: 'parcelsapp', finalStep: 'direct', outcome: 'not_found', errorType: 'NotFoundError', durationMs: 1, attempts: 1 });
    expect(observability.reportTrackingLookup).toHaveBeenLastCalledWith('parcelsapp', 'not_found', undefined);
    hostStepRecorder().lookup({ carrier: 'dhl', finalStep: 'direct', outcome: 'ok', errorType: null, durationMs: 1, attempts: 1 });
    expect(observability.reportTrackingLookup).toHaveBeenLastCalledWith('dhl', 'success', undefined);
  });

  it('tells the running check why an inconclusive lookup failed, without numbers', () => {
    const lookup = { carrier: 'ctt-express', finalStep: 'retry', outcome: 'indeterminate', errorType: 'IndeterminateError', durationMs: 1, attempts: 2 } as const;
    hostStepRecorder().lookup({ ...lookup, error: new IndeterminateError('CTT Express', 'CTT Express returned no tracking scans') });
    expect(observability.reportTrackingLookup).toHaveBeenLastCalledWith('ctt-express', 'error', 'CTT Express returned no tracking scans');
    // errorDetails gives a long number as its shape; a run of a postcode's length is given as its length.
    hostStepRecorder().lookup({ ...lookup, error: new IndeterminateError('CTT Express', 'No reply for office 28001 (HTTP 503)') });
    expect(observability.reportTrackingLookup).toHaveBeenLastCalledWith('ctt-express', 'error', 'No reply for office [5 digits] (HTTP 503)');
    // A reason code says it all.
    const details = observability.errorDetails.getMockImplementation()!;
    observability.errorDetails.mockImplementation(() => ({ error_reason: 'portal_not_found', error_message: 'Not on the portal' }));
    hostStepRecorder().lookup({ ...lookup, error: new IndeterminateError('CTT Express', 'Not on the portal') });
    observability.errorDetails.mockImplementation(details);
    expect(observability.reportTrackingLookup).toHaveBeenLastCalledWith('ctt-express', 'error', 'portal_not_found');
  });

  it('fans out to registered sinks', () => {
    const extra = { step: vi.fn(), lookup: vi.fn() };
    addStepRecorder(extra);
    hostStepRecorder().lookup({ carrier: 'x', finalStep: 'direct', outcome: 'ok', errorType: null, durationMs: 1, attempts: 1 });
    expect(extra.lookup).toHaveBeenCalledTimes(1);
  });

  it('records a lookup that answered with an empty result as one without history, everywhere', async () => {
    const extra = { step: vi.fn(), lookup: vi.fn() };
    addStepRecorder(extra);
    const recorder = hostStepRecorder();
    const ok = { finalStep: 'trawl', outcome: 'ok', errorType: null, durationMs: 1, attempts: 1 } as const;
    const empty = (value: { events: unknown[] }) => value.events.length === 0;
    // FedEx's "could not locate the shipment": held until the result, then an answer without history.
    const notLocated = { status: 'unknown', events: [] };
    await expect(withHeldLookups('fedex', async () => {
      recorder.lookup({ ...ok, carrier: 'fedex' });
      // Another carrier's lookup during the call goes out at once.
      recorder.lookup({ ...ok, carrier: 'ups' });
      expect(extra.lookup.mock.calls.map(([record]) => record.carrier)).toEqual(['ups']);
      return notLocated;
    }, empty)).resolves.toBe(notLocated);
    expect(extra.lookup.mock.lastCall![0]).toMatchObject({ carrier: 'fedex', outcome: 'indeterminate', errorType: 'NoHistoryError' });
    expect(observability.logOperationalEvent).toHaveBeenLastCalledWith('tracking_scrape',
      expect.objectContaining({ carrier: 'fedex', phase: 'total', outcome: 'no_history' }));
    expect(observability.reportTrackingLookup).toHaveBeenLastCalledWith('fedex', 'no_history', expect.any(String));
    // A result with history, and a call that failed, leave the lookup as it was.
    await withHeldLookups('fedex', async () => { recorder.lookup({ ...ok, carrier: 'fedex' }); return { events: [{}] }; }, empty);
    expect(observability.reportTrackingLookup).toHaveBeenLastCalledWith('fedex', 'success', undefined);
    await expect(withHeldLookups('usps', async () => {
      recorder.lookup({ ...ok, carrier: 'usps' });
      throw new Error('synthetic failure after the lookup');
    }, empty)).rejects.toThrow('synthetic failure');
    expect(observability.reportTrackingLookup).toHaveBeenLastCalledWith('usps', 'success', undefined);
  });

  it('records an answer the check set aside, or an older shipment\'s, as one without history, everywhere', async () => {
    const extra = { step: vi.fn(), lookup: vi.fn() };
    addStepRecorder(extra);
    const recorder = hostStepRecorder();
    const ok = { finalStep: 'direct', outcome: 'ok', errorType: null, durationMs: 1, attempts: 1 } as const;
    const sent = () => extra.lookup.mock.calls.map(([record]) => `${record.carrier}:${record.outcome}:${record.error?.reason ?? ''}`);
    await withLookupVerdicts(async () => {
      // Held until the check ends: the router may yet take an answer it set aside.
      recorder.lookup({ ...ok, carrier: 'fan-courier' });
      markLookup('fan-courier', 'set_aside_local_clock');
      recorder.lookup({ ...ok, carrier: 'dpd' });
      markLookup('dpd', 'older_answer');
      recorder.lookup({ ...ok, carrier: 'gls' });
      markLookup('gls', 'set_aside_no_movement');
      unmarkLookups('gls');
      recorder.lookup({ ...ok, carrier: 'ups' });
      expect(sent()).toEqual([]);
      // Another lookup of a carrier, or a step, lets its held ones go first, in order.
      recorder.lookup({ ...ok, carrier: 'dpd', outcome: 'transport', errorType: 'TransportError' });
      expect(sent()).toEqual(['dpd:indeterminate:older_answer', 'dpd:transport:']);
      recorder.step({ carrier: 'ups', step: 'direct', attempt: 1, outcome: 'ok', errorType: null, durationMs: 1,
        fallbackFrom: null, fallbackReason: null, fallbackErrorType: null });
      expect(sent().at(-1)).toBe('ups:ok:');
      expect(extra.step).toHaveBeenCalledTimes(1);
    });
    expect(sent()).toEqual(['dpd:indeterminate:older_answer', 'dpd:transport:', 'ups:ok:', 'fan-courier:indeterminate:set_aside_local_clock',
      'gls:ok:']);
    expect(observability.logOperationalEvent).toHaveBeenCalledWith('tracking_scrape',
      expect.objectContaining({ carrier: 'fan-courier', phase: 'total', outcome: 'no_history' }));
    expect(observability.reportTrackingLookup).toHaveBeenCalledWith('fan-courier', 'no_history', expect.any(String));
    // Outside a check, a mark changes nothing and a lookup goes out at once.
    markLookup('ups', 'older_parcel');
    recorder.lookup({ ...ok, carrier: 'ups' });
    expect(sent().at(-1)).toBe('ups:ok:');
    expect(['empty_answer', 'older_parcel', 'older_answer', 'set_aside_undated'].every(lookupVerdict)).toBe(true);
    expect([undefined, 'not_found', 'several_parcels'].some(lookupVerdict)).toBe(false);
  });
});
