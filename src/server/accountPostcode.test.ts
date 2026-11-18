import { describe, expect, it } from 'vitest';
import { IndeterminateError, TransportError } from 'universal-parcel-scraper';
import type { StepRecord } from 'universal-parcel-scraper/node';
import {
  accountPostcodeFor, accountPostcodeWrite, handoffPostcodeFound, handoffPostcodeMark, typedHandoffPostcode, wantsAccountPostcode,
  wantsHandoffPostcode,
} from './accountPostcode';
import type { JsonObject } from './types';

const parcel = (values: JsonObject = {}): JsonObject => ({
  id: 'parcel', user_id: 'owner', carrier: 'dpd', tracking_number: '06080000000001', current_stage: 'in_transit',
  dpd_postcode: null, archived_at: null, carrier_data: {}, tracking_generation: 'generation', ...values,
});

describe('account postcode', () => {
  it('is given to an open account parcel whose carrier shows more with it', () => {
    expect(wantsAccountPostcode(parcel(), 'dpd')).toBe(true);
    expect(wantsAccountPostcode(parcel({ carrier: 'unknown' }), 'dpd')).toBe(true);
    // A carrier that needs it for some parcels only asks for those.
    expect(wantsAccountPostcode(parcel({ carrier: 'dhl', tracking_number: '000000000012' }), 'dhl')).toBe(false);
    expect(wantsAccountPostcode(parcel(), 'unknown')).toBe(false);
    expect(wantsAccountPostcode(parcel({ user_id: null }), 'dpd')).toBe(false);
    expect(wantsAccountPostcode(parcel({ one_off: true }), 'dpd')).toBe(false);
    expect(wantsAccountPostcode(parcel({ archived_at: '2026-10-01T00:00:00Z' }), 'dpd')).toBe(false);
    expect(wantsAccountPostcode(parcel({ current_stage: 'delivered' }), 'dpd')).toBe(false);
    expect(wantsAccountPostcode(parcel({ dpd_postcode: '8000' }), 'dpd')).toBe(false);
    // Once given, a postcode the owner cleared stays cleared.
    expect(wantsAccountPostcode(parcel({ carrier_data: { account_postcode: true } }), 'dpd')).toBe(false);
  });

  it('gives the latest postcode given the same carrier, else the latest the carrier takes', () => {
    const given = [
      { carrier: 'colis-prive', dpd_postcode: '75001' },
      { carrier: 'dpd', dpd_postcode: '8000' },
    ];
    expect(accountPostcodeWrite(parcel({ carrier_data: { routing: { confirmed_carrier: 'dpd', last_success_at: 'x' } } }), given))
      .toEqual({
        dpd_postcode: '8000', last_synced_at: null, sync_status: 'pending', sync_error: null,
        carrier_data: { account_postcode: true, routing: { confirmed_carrier: 'dpd', last_success_at: 'x', confirmed_postcode: '8000' } },
      });
    // Another carrier's postcode, from France: DPD Switzerland takes any country's, and reads the postcode alone.
    expect(accountPostcodeWrite(parcel(), given.slice(0, 1))).toEqual({
      dpd_postcode: '75001', last_synced_at: null, sync_status: 'pending', sync_error: null, carrier_data: { account_postcode: true },
    });
    // DPD Germany takes German postcodes only.
    expect(accountPostcodeWrite(parcel({ carrier: 'dpd-de' }), given)).toBeNull();
    expect(accountPostcodeWrite(parcel({ carrier: 'dpd-de' }), [...given, {
      carrier: 'dpd', dpd_postcode: '10115', postcode_country: { postcode: '10115', country: 'DE' },
    }])).toMatchObject({ dpd_postcode: '10115', carrier_data: { account_postcode: true } });
  });

  it('leaves out a postcode a carrier refused', () => {
    expect(accountPostcodeWrite(parcel(), [
      { carrier: 'dpd', dpd_postcode: '8000', dpd_postcode_verified: false },
      { carrier: 'spring-gds', dpd_postcode: '1011 AB', postcode_refused: { carrier: 'spring-gds' } },
    ])).toBeNull();
    expect(accountPostcodeWrite(parcel({ current_stage: 'delivered' }), [{ carrier: 'dpd', dpd_postcode: '8000' }])).toBeNull();
  });

  it('offers PostNL the postcode given it last, else the latest Dutch one', () => {
    // A Dutch postcode is from PostNL's home: it keeps no country.
    const number = '3SABCD000000001';
    expect(accountPostcodeFor('spring-gds', number, [
      { carrier: 'dpd', dpd_postcode: '8000' },
      { carrier: 'gls-nl', dpd_postcode: '1011 AB', postcode_country: { postcode: '1011 AB', country: 'NL' } },
      { carrier: 'spring-gds', dpd_postcode: '2511 CV' },
    ])).toEqual({ postcode: '2511 CV', country: null });
    expect(accountPostcodeFor('spring-gds', number, [{ carrier: 'gls-nl', dpd_postcode: '1011 AB' }]))
      .toEqual({ postcode: '1011 AB', country: null });
    expect(accountPostcodeFor('spring-gds', number, [{ carrier: 'spring-gds', dpd_postcode: '1011 AB', postcode_refused: { carrier: 'spring-gds' } }]))
      .toBeNull();
    expect(accountPostcodeFor('spring-gds', number, [{ carrier: 'dpd', dpd_postcode: '10115', postcode_country: { postcode: '10115', country: 'DE' } }]))
      .toEqual({ postcode: '10115', country: 'DE' });
  });
});

describe('handoff postcode', () => {
  const number = '3SABCD000000001';
  const step = (values: Partial<StepRecord>): StepRecord => ({ carrier: 'spring-gds', step: 'domestic', attempt: 1, outcome: 'ok',
    errorType: null, durationMs: 1, fallbackFrom: null, fallbackReason: null, fallbackErrorType: null, ...values });

  it('is wanted for an account\'s parcel at PostNL under a number its domestic tracker holds', () => {
    expect(wantsHandoffPostcode(parcel({ carrier: 'aliexpress' }), 'spring-gds', number)).toBe(true);
    // A guest's parcel, a one-off lookup, a number PostNL takes no postcode for, a carrier that says nothing of one.
    expect(wantsHandoffPostcode(parcel({ carrier: 'aliexpress', user_id: null }), 'spring-gds', number)).toBe(false);
    expect(wantsHandoffPostcode(parcel({ carrier: 'aliexpress', one_off: true }), 'spring-gds', number)).toBe(false);
    expect(wantsHandoffPostcode(parcel({ carrier: 'aliexpress' }), 'spring-gds', '3SABCD0000000001')).toBe(false);
    expect(wantsHandoffPostcode(parcel({ carrier: 'dpd-be' }), 'dpd', '05200000000001')).toBe(false);
  });

  it('takes the postcode typed for that leg, else the parcel\'s own, when PostNL takes it', () => {
    const leg = { universal_input: { number: number.toLowerCase(), postcode: '2511 cv' } };
    expect(typedHandoffPostcode(parcel({ dpd_postcode: '1011 AB', carrier_data: leg }), 'spring-gds', number))
      .toEqual({ postcode: '2511 CV', country: null });
    expect(typedHandoffPostcode(parcel({ dpd_postcode: '1011 AB', carrier_data: { universal_input: { number: 'other', postcode: '2511 CV' } } }),
      'spring-gds', number)).toEqual({ postcode: '1011 AB', country: null });
    expect(typedHandoffPostcode(parcel({ dpd_postcode: '12/34' }), 'spring-gds', number)).toBeNull();
    expect(typedHandoffPostcode(parcel(), 'spring-gds', number)).toBeNull();
  });

  it('reads the mark for its carrier and number alone', () => {
    const mark = { carrier: 'spring-gds', number, postcode: '1011 AB', country: 'NL', found: false };
    expect(handoffPostcodeMark(parcel({ carrier_data: { handoff_postcode: mark } }), 'spring-gds', number.toLowerCase())).toEqual(mark);
    expect(handoffPostcodeMark(parcel({ carrier_data: { handoff_postcode: mark } }), 'spring-gds', '3SABCD000000002')).toBeNull();
    expect(handoffPostcodeMark(parcel({ carrier_data: { handoff_postcode: { ...mark, found: 'no' } } }), 'spring-gds', number)).toBeNull();
  });

  it('says the postcode found the parcel, missed it while PostNL still answered, or neither', () => {
    const missed = new IndeterminateError('PostNL', 'PostNL found no parcel for that barcode and postcode', { reason: 'portal_not_found' });
    expect(handoffPostcodeFound('spring-gds', [step({})], true)).toBe(true);
    expect(handoffPostcodeFound('spring-gds', [step({ outcome: 'indeterminate', error: missed }), step({ step: 'direct' })], true)).toBe(false);
    // Nobody knew the number: the international tracker failed too, so the postcode is not to blame.
    expect(handoffPostcodeFound('spring-gds', [step({ outcome: 'indeterminate', error: missed })], false)).toBeNull();
    // The domestic tracker failed to answer, or was not asked.
    expect(handoffPostcodeFound('spring-gds', [step({ outcome: 'transport', error: new TransportError('PostNL', 'Down') })], true)).toBeNull();
    expect(handoffPostcodeFound('spring-gds', [step({ step: 'direct' })], true)).toBeNull();
    expect(handoffPostcodeFound('dpd', [step({ carrier: 'dpd' })], true)).toBeNull();
  });
});
