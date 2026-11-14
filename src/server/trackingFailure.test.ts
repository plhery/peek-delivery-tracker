import { describe, expect, it } from 'vitest';
import { InputRequiredError, IndeterminateError, InvalidInputError, MaintenanceError, RateLimitedError, ChallengeError } from 'universal-parcel-scraper';
import { answeredWithoutNews, deferredTrackingFailure, placeholderNumber, refusedNumberCode, trackingFailureCode } from './trackingFailure';

describe('public tracking failure codes', () => {
  it('retains typed causes without leaking diagnostic text', () => {
    expect(trackingFailureCode(new Error('secret diagnostic', { cause: new InputRequiredError('Heppner', 'postcode') }))).toBe('carrier:input_required');
    expect(trackingFailureCode(new MaintenanceError('Carrier'))).toBe('carrier:maintenance');
    expect(trackingFailureCode(new RateLimitedError('Carrier'))).toBe('carrier:rate_limited');
    expect(trackingFailureCode(new ChallengeError('Carrier'))).toBe('carrier:challenge');
    expect(trackingFailureCode(new Error('unclassified'))).toBeNull();
    // A number that names several parcels has copy of its own; other inconclusive answers do not.
    expect(trackingFailureCode(new Error('wrapped', { cause: new IndeterminateError('Australia Post', undefined, { reason: 'several_parcels' }) })))
      .toBe('carrier:several_parcels');
    expect(trackingFailureCode(new IndeterminateError('Carrier', undefined, { reason: 'portal_not_found' }))).toBe('carrier:indeterminate');
  });

  it('keeps missing input, a refused number and one naming several parcels actionable after fallback attempts, without blaming mixed failures on one provider', () => {
    expect(deferredTrackingFailure({ heppner: { kind: 'schema', user_error: 'carrier:input_required' }, universal: { kind: 'transport' } }, 'heppner')).toBe('carrier:input_required');
    expect(deferredTrackingFailure({ other: { kind: 'schema', user_error: 'carrier:input_required' }, universal: { kind: 'transport' } }, 'heppner')).toBeNull();
    expect(deferredTrackingFailure({ nacex: { kind: 'invalid_input', user_error: 'carrier:invalid_input' }, universal: { kind: 'transport' } }, 'nacex')).toBe('carrier:invalid_input');
    expect(deferredTrackingFailure({ other: { kind: 'invalid_input', user_error: 'carrier:invalid_input' }, universal: { kind: 'transport' } }, 'nacex')).toBeNull();
    expect(deferredTrackingFailure({ 'australia-post': { kind: 'invalid_input', user_error: 'carrier:several_parcels' }, universal: { kind: 'no_history' } }, 'australia-post'))
      .toBe('carrier:several_parcels');
    expect(deferredTrackingFailure({ first: { kind: 'rate_limited' }, second: { kind: 'rate_limited' } }, 'heppner')).toBe('carrier:rate_limited');
    expect(deferredTrackingFailure({ first: { kind: 'rate_limited' }, second: { kind: 'transport' } }, 'heppner')).toBeNull();
  });

  it('tells sources that all answered without a scan from an outage', () => {
    expect(answeredWithoutNews({ jrt: { kind: 'no_history' }, Ship24: { kind: 'not_found' } })).toBe(true);
    expect(answeredWithoutNews({ jrt: { kind: 'no_history' }, ParcelsApp: { kind: 'transport' } })).toBe(false);
    expect(answeredWithoutNews({ jrt: { kind: 'rate_limited' } })).toBe(false);
    expect(answeredWithoutNews({})).toBe(false);
  });

  it('names a placeholder number, refused by every source alike, whatever the parcel\'s carrier', () => {
    const refusal = new InvalidInputError('Ship24', 'placeholder', { reason: 'placeholder_number' });
    expect(trackingFailureCode(new Error('wrapped', { cause: refusal }))).toBe('carrier:placeholder_number');
    expect(trackingFailureCode(new InvalidInputError('Carrier', 'bad check digit'))).toBe('carrier:invalid_input');
    // One source still cooling down from an older failure does not hide it.
    expect(deferredTrackingFailure({
      ship24: { kind: 'invalid_input', user_error: 'carrier:placeholder_number' }, '17track': { kind: 'transport' },
    }, 'unknown')).toBe('carrier:placeholder_number');
    expect(refusedNumberCode('carrier:placeholder_number')).toBe(true);
    expect(refusedNumberCode('carrier:invalid_input')).toBe(true);
    expect(refusedNumberCode('carrier:not_found')).toBe(false);
  });

  it('tells a placeholder number from a parcel\'s own', () => {
    for (const number of ['1234567890', '0000000000', 'JJD1234567890']) expect(placeholderNumber({ tracking_number: number })).toBe(true);
    for (const number of ['TEST1234', '1234067892', undefined]) expect(placeholderNumber({ tracking_number: number })).toBe(false);
  });
});
