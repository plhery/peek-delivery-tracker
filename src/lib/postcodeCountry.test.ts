import { describe, expect, it } from 'vitest';
import { carrierRequirements } from './carriers';
import {
  COUNTRIES,
  initialPostcodeCountry,
  postcodeCountries,
  postcodeFieldFor,
  readsPostcodeCountry,
  sentPostcodeCountry,
  storedPostcodeCountry,
  takesAnyPostcode,
} from './postcodeCountry';
import { postcodeExample } from './postcodeExample';

const POSTNL = '3SABCD826405193';
const DPD = '06080000000001';

describe('postcode countries', () => {
  it('names the country only where the carrier’s lookup reads it, from any country when its check reads any shape', () => {
    expect(takesAnyPostcode('spring-gds', POSTNL)).toBe(true);
    expect(readsPostcodeCountry('spring-gds', POSTNL)).toBe(true);
    expect(postcodeCountries('spring-gds', POSTNL)).toBe(COUNTRIES);
    // DPD takes any country’s postcode and GLS Switzerland its two countries’, but both read the postcode alone.
    expect(takesAnyPostcode('dpd', DPD)).toBe(true);
    expect(readsPostcodeCountry('dpd', DPD)).toBe(false);
    expect(postcodeCountries('dpd', DPD)).toEqual(['CH']);
    expect(takesAnyPostcode('gls-ch', '12345678901')).toBe(false);
    expect(postcodeCountries('gls-ch', '12345678901')).toEqual(['CH']);
  });

  it('starts on the first known country the carrier takes, then its home', () => {
    expect(initialPostcodeCountry('spring-gds', POSTNL, [undefined, 'BE', 'DE'])).toBe('BE');
    expect(initialPostcodeCountry('spring-gds', POSTNL, [null])).toBe('NL');
    expect(initialPostcodeCountry('dpd', DPD, ['FR'])).toBe('CH');
    expect(initialPostcodeCountry('gls-ch', '12345678901', ['LI'])).toBe('CH');
  });

  it('sends a country only when it is not the carrier’s home', () => {
    expect(sentPostcodeCountry('spring-gds', 'BE')).toBe('BE');
    expect(sentPostcodeCountry('spring-gds', 'NL')).toBeUndefined();
    expect(sentPostcodeCountry('spring-gds', null)).toBeUndefined();
  });

  it('shapes the field for a postcode from abroad with that country’s example', () => {
    const [own] = carrierRequirements('spring-gds', POSTNL);
    expect(postcodeFieldFor(own, 'spring-gds', POSTNL, 'NL')).toBe(own);
    const belgian = postcodeFieldFor(own, 'spring-gds', POSTNL, 'BE');
    expect(belgian).toMatchObject({ field: 'dpdPostcode', maxLength: 12, inputMode: 'text', placeholder: '1000' });
    // The same field each time, so it compares as a requirement does.
    expect(postcodeFieldFor(own, 'spring-gds', POSTNL, 'BE')).toBe(belgian);
    const shape = new RegExp(belgian.pattern!);
    for (const postcode of ['1000', '1012 AB', 'SW1A 1AA', '00-001']) expect(shape.test(postcode)).toBe(true);
    for (const postcode of ['12', 'abcde', '1000/2']) expect(shape.test(postcode)).toBe(false);
    expect(postcodeExample('spring-gds', belgian, 'FR')).toBe('1000');
    // A country without an example shows none, rather than the carrier's own.
    expect(postcodeExample('spring-gds', postcodeFieldFor(own, 'spring-gds', POSTNL, 'AQ'), 'FR')).toBeUndefined();
    // A carrier that checks its own countries’ shapes, or reads the postcode alone, keeps its field.
    const [swiss] = carrierRequirements('gls-ch', '12345678901');
    expect(postcodeFieldFor(swiss, 'gls-ch', '12345678901', 'LI')).toBe(swiss);
    const [dpd] = carrierRequirements('dpd', DPD);
    expect(postcodeFieldFor(dpd, 'dpd', DPD, 'FR')).toBe(dpd);
  });

  it('reads the stored country only while the parcel holds the postcode it came with', () => {
    const data = { postcode_country: { postcode: '1000', country: 'BE' } };
    expect(storedPostcodeCountry(data, '1000')).toBe('BE');
    expect(storedPostcodeCountry(data, '1012 AB')).toBeUndefined();
    expect(storedPostcodeCountry(data, null)).toBeUndefined();
    expect(storedPostcodeCountry({ postcode_country: { postcode: '1000', country: 'be' } }, '1000')).toBeUndefined();
    expect(storedPostcodeCountry(null, '1000')).toBeUndefined();
  });
});
