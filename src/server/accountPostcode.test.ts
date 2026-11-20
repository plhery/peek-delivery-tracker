import { describe, expect, it } from 'vitest';
import { IndeterminateError, TransportError } from 'universal-parcel-scraper';
import type { StepRecord } from 'universal-parcel-scraper/node';
import {
  accountPostcodeFor, accountPostcodeWrite, handoffPostcodeFound, handoffPostcodeMark, handoffPostcodeMissed, nextHandoffPostcodeMark,
  postcodeCleared, typedHandoffPostcode, wantsAccountPostcode,
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
    // So does one the owner typed and cleared, which the database marks.
    expect(wantsAccountPostcode(parcel({ carrier_data: { account_postcode: false } }), 'dpd')).toBe(false);
    expect(postcodeCleared(parcel({ carrier_data: { account_postcode: false } }))).toBe(true);
    expect(postcodeCleared(parcel({ carrier_data: { account_postcode: true } }))).toBe(false);
    expect(postcodeCleared(parcel())).toBe(false);
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

  it('finds the same carrier\'s postcode behind many other parcels', () => {
    // Thirty newer parcels at another carrier, all at one French address, then the one given DPD.
    const given = [...Array.from({ length: 30 }, () => ({ carrier: 'colis-prive', dpd_postcode: '75001' })),
      { carrier: 'dpd', dpd_postcode: '8000' }];
    expect(accountPostcodeFor('dpd', '06080000000001', given)).toEqual({ postcode: '8000', country: null });
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
    expect(accountPostcodeFor('spring-gds', number, [{ carrier: 'spring-gds', dpd_postcode: '1011 AB' }]))
      .toEqual({ postcode: '1011 AB', country: null });
    expect(accountPostcodeFor('spring-gds', number, [{ carrier: 'spring-gds', dpd_postcode: '1011 AB', postcode_refused: { carrier: 'spring-gds' } }]))
      .toBeNull();
    expect(accountPostcodeFor('spring-gds', number, [{ carrier: 'dpd', dpd_postcode: '10115', postcode_country: { postcode: '10115', country: 'DE' } }]))
      .toEqual({ postcode: '10115', country: 'DE' });
  });

  it('gives a carrier that reads the country only a postcode whose country is known', () => {
    const number = '3SABCD000000001';
    // DPD Switzerland, DHL Germany and GLS Netherlands take any country's postcode: a French one given them is not
    // Swiss, German or Dutch.
    for (const carrier of ['dpd', 'dhl', 'gls-nl']) {
      expect(accountPostcodeFor('spring-gds', number, [{ carrier, dpd_postcode: '75001' }])).toBeNull();
      expect(accountPostcodeFor('mondial-relay', '40283715', [{ carrier, dpd_postcode: '75001' }])).toBeNull();
    }
    // DPD Germany takes German postcodes only.
    expect(accountPostcodeFor('spring-gds', number, [{ carrier: 'dpd-de', dpd_postcode: '10115' }])).toEqual({ postcode: '10115', country: 'DE' });
    // A carrier that reads the postcode alone still takes it by its shape.
    expect(accountPostcodeFor('gls-ch', '999999999916', [{ carrier: 'dpd', dpd_postcode: '8000' }])).toEqual({ postcode: '8000', country: null });
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

  it('counts a postcode\'s misses in a row, and keeps it back until its one more try 6 h later', () => {
    const asked = { carrier: 'spring-gds', number, postcode: '1011 AB', country: null };
    const at = new Date('2026-11-18T09:00:00Z');
    const first = nextHandoffPostcodeMark(null, { ...asked, found: false }, at);
    expect(first).toEqual({ ...asked, found: false, at: '2026-11-18T09:00:00.000Z', misses: 1 });
    expect(handoffPostcodeMark(parcel({ carrier_data: { handoff_postcode: first } }), 'spring-gds', number)).toEqual(first);
    expect(handoffPostcodeMissed(first, '1011 AB', new Date('2026-11-18T14:59:59Z'))).toBe(true);
    expect(handoffPostcodeMissed(first, '1011 AB', new Date('2026-11-18T15:00:00Z'))).toBe(false);
    expect(handoffPostcodeMissed(first, '2511 CV', at)).toBe(false);
    const second = nextHandoffPostcodeMark(first, { ...asked, found: false }, new Date('2026-11-18T15:00:00Z'));
    expect(second.misses).toBe(2);
    expect(handoffPostcodeMissed(second, '1011 AB', new Date('2026-11-25T09:00:00Z'))).toBe(true);
    // Another postcode's miss counts on its own; a find ends the count.
    expect(nextHandoffPostcodeMark(second, { ...asked, postcode: '2511 CV', found: false }, at).misses).toBe(1);
    const found = nextHandoffPostcodeMark(second, { ...asked, found: true }, at);
    expect(found).toEqual({ ...asked, found: true, at: '2026-11-18T09:00:00.000Z' });
    expect(handoffPostcodeMissed(found, '1011 AB', at)).toBe(false);
    // A miss kept before misses were counted is tried once more at once.
    expect(handoffPostcodeMissed({ ...asked, found: false }, '1011 AB', at)).toBe(false);
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
