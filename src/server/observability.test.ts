import { afterEach, describe, expect, it, vi } from 'vitest';
import {
  databaseUnavailable,
  errorDetails,
  errorType,
  logOperationalEvent,
  numberPattern,
  operationalErrorMetadata,
  parseSampleRate,
  reportRoutingEvent,
  resolveSentryRelease,
  shouldReportOutage,
  shouldReportRepeatedFailure,
  trackingAttemptId,
  withTrackingAttempt,
} from './observability';
import { UpstreamHttpError } from 'universal-parcel-scraper/node';
import { IndeterminateError, InputRequiredError } from 'universal-parcel-scraper';
import { SupabaseError } from './supabase';
import { TrackingCaptureError, SeventeenTrackLookupError, SeventeenTrackNoHistoryError } from 'universal-parcel-scraper/node';

afterEach(() => vi.restoreAllMocks());

describe('structured operational logs', () => {
  it('retains supplied diagnostic fields in structured logs', () => {
    const output = vi.spyOn(console, 'log').mockImplementation(() => undefined);

    logOperationalEvent('tracking_sync_step', {
      attempt_id: 'opaque-attempt',
      carrier: 'dpd-fr',
      package_id: 'private-package',
      status_text: 'private status',
      tracking_url: 'https://carrier.example/secret-link',
      authorization: 'Bearer secret',
    });

    const payload = JSON.parse(String(output.mock.calls[0]?.[0]));
    expect(payload).toMatchObject({
      event: 'tracking_sync_step',
      attempt_id: 'opaque-attempt',
      carrier: 'dpd-fr',
    });
    expect(payload).toHaveProperty('package_id');
    expect(payload).toHaveProperty('status_text');
    expect(payload).toHaveProperty('tracking_url');
    expect(payload).toHaveProperty('authorization');
  });

  it('names a parcel by the shape of its number, wherever the line would hold a number', () => {
    const output = vi.spyOn(console, 'log').mockImplementation(() => undefined);

    logOperationalEvent('tracking_sync_step', {
      package_id: 'private-package',
      tracking_number: 'TEST9012',
      // A handoff's number, looked up instead of the parcel's.
      support_lookup_number: 'HAND7305',
      error_message: 'No parcel test9012, see hand7305',
    });
    logOperationalEvent('tracking_routing', { tracking_number: null, decision: 'fresher_provider_found' });

    const [step, routing] = output.mock.calls.map(([line]) => String(line));
    expect(JSON.parse(step!)).toEqual(expect.objectContaining({ package_id: 'private-package', number_pattern: '4 letters + 4 digits',
      error_message: 'No parcel 4 letters + 4 digits, see 4 letters + 4 digits' }));
    expect(step).not.toMatch(/tracking_number|support_lookup_number|test9012|hand7305/i);
    expect(JSON.parse(routing!)).not.toHaveProperty('number_pattern');
  });
});

describe('the check a line belongs to', () => {
  it('names the attempt on the routing lines of its lookups, and on no other', async () => {
    const output = vi.spyOn(console, 'log').mockImplementation(() => undefined);
    vi.spyOn(console, 'warn').mockImplementation(() => undefined);
    await Promise.all(['first', 'second'].map((attempt) => withTrackingAttempt(attempt, async () => {
      await Promise.resolve();
      reportRoutingEvent('fresher_provider_found', { carrier: 'dhl', provider: 'ParcelsApp' });
    })));
    reportRoutingEvent('fresher_provider_found', { carrier: 'dhl', provider: 'ParcelsApp' });
    // The sync names its attempt where it reports outside the lookup.
    reportRoutingEvent('carrier_auto_swapped', { carrier: 'dhl', provider: 'ups', attemptId: 'third' });
    const lines = output.mock.calls.map(([line]) => JSON.parse(String(line)));
    expect(lines.map((line) => line.attempt_id)).toEqual(['first', 'second', undefined, 'third']);
    expect(trackingAttemptId()).toBeUndefined();
  });
});

describe('a number in a report', () => {
  it('keeps its shape: short letter runs as they are, longer ones and digits counted', () => {
    expect(numberPattern('my1234567890us')).toBe('MY + 10 digits + US');
    expect(numberPattern('JJD0003-9000 7')).toBe('JJD + 9 digits');
    expect(numberPattern('ABCDEF7')).toBe('6 letters + 1 digit');
    expect(numberPattern('')).toBe('');
  });
});

describe('why a lookup failed', () => {
  it('gives the reason, the wording and each cause, with no address query and no word that could be a number', () => {
    const cause = new TypeError('fetch failed for https://carrier.example/track/AB123456789CD?session=secret', {
      cause: new Error('socket hang up on 1234567'),
    });
    const error = new IndeterminateError('city-express-rs', 'City Express did not open an anonymous session', { reason: 'session_rejected', cause });
    expect(errorDetails(error)).toEqual({
      error_reason: 'session_rejected',
      error_message: 'City Express did not open an anonymous session',
      error_cause: 'TypeError: fetch failed for https://carrier.example/track/:id <- Error: socket hang up on [7 digits]',
    });
    expect(errorDetails(new Error('Shipment AB123456789CD was not listed, HTTP 502'))).toEqual({
      error_reason: undefined, error_message: 'Shipment [AB + 9 digits + CD] was not listed, HTTP 502', error_cause: undefined,
    });
    expect(errorDetails('not an error')).toEqual({});
  });
});

describe('routing log levels', () => {
  it('logs a provider without history for the number at info, and other provider failures as warnings', () => {
    const info = vi.spyOn(console, 'log').mockImplementation(() => undefined);
    const warn = vi.spyOn(console, 'warn').mockImplementation(() => undefined);
    reportRoutingEvent('provider_failed', { carrier: 'unknown', provider: 'ParcelsApp', category: 'no_history' });
    reportRoutingEvent('provider_failed', { carrier: 'unknown', provider: 'Ship24', category: 'transport' });
    reportRoutingEvent('transport_fallback', { carrier: 'ups', provider: 'ups', category: 'trawl' });
    expect(info.mock.calls.map(([line]) => JSON.parse(String(line)).provider)).toEqual(['ParcelsApp']);
    expect(warn.mock.calls.map(([line]) => JSON.parse(String(line)).provider)).toEqual(['Ship24', 'ups']);
  });

  it('names the reason a carrier gave, found through the causes, and only a reason that reads as a code', () => {
    const info = vi.spyOn(console, 'log').mockImplementation(() => undefined);
    vi.spyOn(console, 'warn').mockImplementation(() => undefined);
    const held = new InputRequiredError('DHL', 'the delivery postcode', 'DHL has the parcel', { reason: 'parcel_found' });
    reportRoutingEvent('carrier_input_needed', { carrier: 'dhl', provider: 'dhl', error: new Error('probe failed', { cause: held }) });
    reportRoutingEvent('carrier_input_needed', { carrier: 'dhl', provider: 'dhl', error: new InputRequiredError('DHL', 'x', 'y') });
    reportRoutingEvent('carrier_input_needed', {
      carrier: 'dhl', provider: 'dhl', error: new InputRequiredError('DHL', 'x', 'y', { reason: 'Free text with 1234' }),
    });
    expect(info.mock.calls.map(([line]) => JSON.parse(String(line)).error_reason)).toEqual(['parcel_found', undefined, undefined]);
  });
});

describe('observability configuration', () => {
  it('accepts only bounded trace sample rates', () => {
    expect(parseSampleRate(undefined)).toBe(0);
    expect(parseSampleRate('0.25')).toBe(0.25);
    expect(parseSampleRate('-1', 0.1)).toBe(0.1);
    expect(parseSampleRate('2', 0.1)).toBe(0.1);
    expect(parseSampleRate('invalid', 0.1)).toBe(0.1);
  });

  it('prefers immutable image commits and rejects placeholder releases', () => {
    expect(resolveSentryRelease({
      IMAGE_COMMIT: 'a'.repeat(40),
      SENTRY_RELEASE: 'delivery@fallback',
    })).toBe('a'.repeat(40));
    expect(resolveSentryRelease({ SENTRY_RELEASE: 'delivery@2026.08.31' }))
      .toBe('delivery@2026.08.31');
    expect(resolveSentryRelease({ SENTRY_RELEASE: 'HEAD' })).toBeUndefined();
  });

  it('reports early repeats and then powers of two to prevent alert floods', () => {
    expect([1, 2, 3, 4, 5, 8, 16].filter(shouldReportRepeatedFailure))
      .toEqual([1, 2, 3, 4, 8, 16]);
  });

  it('reports an outage once it has lasted two minutes, then each time it has doubled', () => {
    expect([0, 10_000, 119_999].some((failingFor) => shouldReportOutage(failingFor, null))).toBe(false);
    expect(shouldReportOutage(120_000, null)).toBe(true);
    expect(shouldReportOutage(180_000, 123_000)).toBe(false);
    expect(shouldReportOutage(245_999, 123_000)).toBe(false);
    expect(shouldReportOutage(246_000, 123_000)).toBe(true);
    expect(shouldReportOutage(Number.NaN, null)).toBe(false);
    expect(shouldReportOutage(Number.POSITIVE_INFINITY, null)).toBe(false);
  });

  it('tells a database that does not answer from one that refuses', () => {
    expect(databaseUnavailable(new SupabaseError('The delivery database is unreachable', undefined, 'unreachable'))).toBe(true);
    for (const status of [502, 503, 504]) {
      expect(databaseUnavailable(new SupabaseError(`Supabase POST request failed (${status})`, status))).toBe(true);
    }
    // A copy of the class from another bundle is recognised by its name.
    const copy = Object.assign(new Error('Supabase POST request failed (503)'), { name: 'SupabaseError', status: 503 });
    expect(databaseUnavailable(copy)).toBe(true);

    for (const refusal of [
      new SupabaseError('Supabase POST request failed (500)', 500, '55000'),
      new SupabaseError('Supabase POST request failed (500)', 500),
      new SupabaseError('Supabase POST request failed (400)', 400, '42804'),
      new SupabaseError('Supabase POST request failed (401)', 401),
      new SupabaseError('Supabase POST request failed (404)', 404, 'PGRST202'),
      new SupabaseError('The durable sync job could not be queued'),
      Object.assign(new Error('gateway'), { status: 503, code: 'unreachable' }),
      new Error('offline'),
      'unreachable',
      null,
    ]) {
      expect(databaseUnavailable(refusal)).toBe(false);
    }
  });

  it('does not treat an arbitrary exception name as telemetry metadata', () => {
    const error = new Error('private');
    error.name = 'TRACKING123';
    expect(errorType(error)).toBe('Error');
    error.name = 'CarrierTimeoutError';
    expect(errorType(error)).toBe('CarrierTimeoutError');
  });

  it('keeps actionable scraper reasons and status codes without provider response text', () => {
    expect(operationalErrorMetadata(new TrackingCaptureError('capture_unreadable')))
      .toEqual({ providerFailureReason: 'capture_unreadable' });
    expect(operationalErrorMetadata(new SeventeenTrackLookupError('lookup_unavailable', 400)))
      .toEqual({ providerFailureReason: 'lookup_unavailable', providerCode: 400 });
    expect(operationalErrorMetadata(new SeventeenTrackNoHistoryError()))
      .toEqual({ providerFailureReason: 'no_history', providerCode: 400 });
    expect(operationalErrorMetadata(new Error('recovery timed out', { cause: new UpstreamHttpError('DHL eCommerce tracking', 428) })))
      .toEqual({ upstreamStatus: 428 });
    const forged = Object.assign(new Error('PRIVATE'), { reason: 'PRIVATE', providerCode: 1234567890 });
    forged.name = 'TrackingCaptureError';
    expect(operationalErrorMetadata(forged)).toEqual({});
  });

  it('extracts only bounded status and database code metadata from known errors', () => {
    expect(operationalErrorMetadata(new UpstreamHttpError('Carrier', 503))).toEqual({
      upstreamStatus: 503,
    });

    const database = new SupabaseError('private response', 502, 'PGRST000');
    expect(operationalErrorMetadata(new Error('wrapper', { cause: database }))).toEqual({
      databaseStatus: 502,
      databaseCode: 'PGRST000',
    });

    expect(operationalErrorMetadata(new SupabaseError(
      'private response',
      999,
      'private response text',
    ))).toEqual({});
  });
});
