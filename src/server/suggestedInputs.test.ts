import { describe, expect, it } from 'vitest';
import { clientInputs, detectionWithInputs, suggestedPhoneDigits, withSuggestedInputs } from './suggestedInputs';

// J&T China shows its JT numbers only with the last 4 digits of the recipient's or sender's phone.
const JT = 'JT0805136742918';
const checked = { id: 'p1', tracking_number: JT, carrier: 'unknown', current_stage: 'pending', last_synced_at: '2026-11-23T08:00:00Z',
  tracking_events: [], carrier_data: { lookup_country_hint: 'FR' } };

describe('clientInputs', () => {
  it('reads the inputs an app names in X-Peek-Inputs', () => {
    const asking = (value?: string) => clientInputs(new Request('https://delivery.example/api/packages',
      { headers: value === undefined ? {} : { 'X-Peek-Inputs': value } })).phoneDigits;
    expect(asking('phoneDigits')).toBe(true);
    expect(asking('dpdPostcode, phoneDigits')).toBe(true);
    expect(asking()).toBe(false);
    expect(asking('phonedigits')).toBe(false);
    expect(asking('phoneDigitsX')).toBe(false);
  });
});

describe('suggestedPhoneDigits', () => {
  it('names the carrier for a checked parcel filed under no carrier that nobody answered for', () => {
    expect(suggestedPhoneDigits(checked)).toEqual({ carrier: 'j-and-t-cn', field: 'phoneDigits' });
  });

  it('names it past Peek\'s own "Tracking added" row, which every parcel holds', () => {
    const added = { id: 'e0', stage: 'pending', description: 'Tracking added', provider_event_id: 'app:pending' };
    expect(suggestedPhoneDigits({ ...checked, tracking_events: [added] })).toEqual({ carrier: 'j-and-t-cn', field: 'phoneDigits' });
    expect(suggestedPhoneDigits({ ...checked, tracking_events: [added, { id: 'e1', stage: 'pending', provider_event_id: 'jt:1' }] }))
      .toBeUndefined();
  });

  it('suggests nothing before the first check, once anyone answered, once it is over, or past another input', () => {
    for (const row of [
      { ...checked, last_synced_at: null },
      { ...checked, carrier: 'j-and-t' },
      { ...checked, current_stage: 'delivered' },
      { ...checked, current_stage: 'returned' },
      { ...checked, tracking_events: [{ id: 'e1', stage: 'in_transit' }] },
      { ...checked, carrier_data: { original_carrier: 'j-and-t-cn' } },
      { ...checked, carrier_data: { routing: { last_event_at: '2026-11-22T08:00:00Z' } } },
      { ...checked, carrier_data: { routing: { confirmed_carrier: 'j-and-t' } } },
      { ...checked, carrier_data: { routing: { discovered_carrier: 'j-and-t' } } },
      { ...checked, carrier_data: { routing: { input_needed: { carrier: 'dhl', field: 'dpdPostcode' } } } },
      { ...checked, carrier_data: { routing: { input_suggested: { carrier: 'mondial-relay', field: 'dpdPostcode' } } } },
      // Synthetic: a number of no carrier that takes the phone digits.
      { ...checked, tracking_number: 'TEST12345678' },
    ]) expect(suggestedPhoneDigits(row)).toBeUndefined();
  });

  it('serves it in the parcel\'s routing only to an app that asks for it, keeping the rest', () => {
    const served = withSuggestedInputs(checked, { phoneDigits: true });
    expect(served.carrier_data).toEqual({ lookup_country_hint: 'FR', routing: { input_suggested: { carrier: 'j-and-t-cn', field: 'phoneDigits' } } });
    expect(checked.carrier_data).toEqual({ lookup_country_hint: 'FR' });
    expect(withSuggestedInputs(checked, { phoneDigits: false })).toBe(checked);
  });
});

describe('detectionWithInputs', () => {
  it('adds the carrier to a check that found nobody, for an app that asks for it', () => {
    const answer = { trackingNumber: JT, carrier: 'unknown' as const };
    expect(detectionWithInputs(answer, { phoneDigits: true })).toEqual({ ...answer, inputSuggested: { carrier: 'j-and-t-cn', field: 'phoneDigits' } });
    expect(detectionWithInputs(answer, { phoneDigits: false })).toBe(answer);
    for (const other of [{ ...answer, trackingFound: true }, { ...answer, recognized: ['j-and-t' as const] },
      { ...answer, inputSuggested: { carrier: 'mondial-relay' as const, field: 'dpdPostcode' as const } }]) {
      expect(detectionWithInputs(other, { phoneDigits: true })).toBe(other);
    }
  });
});
