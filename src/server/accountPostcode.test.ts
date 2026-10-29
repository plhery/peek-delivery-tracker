import { describe, expect, it } from 'vitest';
import { accountPostcodeWrite, wantsAccountPostcode } from './accountPostcode';
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
    // Another carrier's postcode keeps its country, which DPD Switzerland is asked with.
    expect(accountPostcodeWrite(parcel(), given.slice(0, 1))).toMatchObject({
      dpd_postcode: '75001', carrier_data: { account_postcode: true, postcode_country: { postcode: '75001', country: 'FR' } },
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
});
