import { act, renderHook } from '@testing-library/react';
import { afterEach, describe, expect, it, vi } from 'vitest';
import { carrierRequirements } from './carriers';
import {
  forgetPostcode,
  MAX_POSTCODES,
  POSTCODES_STORAGE_KEY,
  rememberedPostcode,
  rememberedPostcodeField,
  rememberPostcode,
  usePostcodeMemory,
  type GivenPostcode,
} from './postcodeMemory';
import { forgetAllRecents, forgetRecent, rememberParcel } from '../peek/recents';
import { LINK_ID, OWNER_KEY, testView } from '../test/parcelLinks';

// Every postcode here is a city centre's, not anyone's address.
const DPD = '06080000000002';
const dpd = carrierRequirements('dpd', DPD)[0]!;
const GLS = '12345678';
const glsSwitzerland = carrierRequirements('gls-ch', GLS)[0]!;
const POSTNL = '3SABCD826405193';
const postnl = carrierRequirements('spring-gds', POSTNL)[0]!;
const MONDIAL = '40283715';
const mondialRelay = carrierRequirements('mondial-relay', MONDIAL)[0]!;
const gls = (given: readonly GivenPostcode[]) => rememberedPostcode('gls-ch', GLS, glsSwitzerland, given)?.postcode;

describe('remembered postcodes', () => {
  it("takes the same carrier's newest postcode first", () => {
    expect(rememberedPostcode('gls-ch', GLS, glsSwitzerland, [
      { carrier: 'dpd', postcode: '8004' },
      { carrier: 'gls-ch', postcode: '1200' },
      { carrier: 'gls-ch', postcode: '3011' },
    ])).toEqual({ postcode: '1200', country: 'CH' });
  });

  it("falls back to another carrier's, when this field takes it", () => {
    expect(gls([{ carrier: 'dpd', postcode: '8004' }])).toBe('8004');
    expect(gls([
      { carrier: 'dpd', postcode: '75001' },
      { carrier: 'dpd', postcode: '1012 AB' },
      { carrier: 'swiss-post', postcode: '3011' },
    ])).toBe('3011');
    expect(gls([{ carrier: 'dpd', postcode: '75001' }])).toBeUndefined();
  });

  it('gives a postcode as carriers print it, and skips empty ones', () => {
    expect(rememberedPostcode('dpd', DPD, dpd, [
      { carrier: 'dpd', postcode: null },
      { carrier: 'dpd', postcode: '  ' },
      { carrier: 'gls-de', postcode: ' sw1a  1aa ' },
    ])?.postcode).toBe('SW1A 1AA');
    expect(rememberedPostcode('dpd', DPD, dpd, [])).toBeUndefined();
  });

  it('brings the country it was given from to a field that reads it', () => {
    const belgian = { carrier: 'spring-gds', postcode: '1000', country: 'BE' } as const;
    expect(rememberedPostcode('spring-gds', POSTNL, postnl, [belgian])).toEqual({ postcode: '1000', country: 'BE' });
    expect(rememberedPostcode('mondial-relay', MONDIAL, mondialRelay, [belgian])).toEqual({ postcode: '1000', country: 'BE' });
    // One given to a carrier that reads the postcode alone is from that carrier's home.
    expect(rememberedPostcode('mondial-relay', MONDIAL, mondialRelay, [{ carrier: 'gls-de', postcode: '10115' }]))
      .toEqual({ postcode: '10115', country: 'DE' });
    // Once the reader names a country, only that country's.
    expect(rememberedPostcode('mondial-relay', MONDIAL, mondialRelay, [belgian, { carrier: 'gls-de', postcode: '10115' }], 'DE'))
      .toEqual({ postcode: '10115', country: 'DE' });
    expect(rememberedPostcode('spring-gds', POSTNL, postnl, [belgian], 'NL')).toBeUndefined();
  });

  it('never gives a field that reads the country a postcode whose country is not known', () => {
    // Kept before this device kept countries, for a carrier that reads them: it may be from anywhere.
    expect(rememberedPostcode('spring-gds', POSTNL, postnl, [{ carrier: 'spring-gds', postcode: '1012 AB' }])).toBeUndefined();
    expect(rememberedPostcode('mondial-relay', MONDIAL, mondialRelay, [{ carrier: 'mondial-relay', postcode: '1000' }])).toBeUndefined();
    // A field that reads the postcode alone takes it by its shape, from its own country.
    expect(rememberedPostcode('gls-ch', GLS, glsSwitzerland, [{ carrier: 'mondial-relay', postcode: '1000', country: 'BE' }]))
      .toEqual({ postcode: '1000', country: 'CH' });
  });

  it('fills a required field and offers it to an optional one, until the reader types', () => {
    const given = [{ carrier: 'dpd' as const, postcode: '8004' }];
    const offered = { postcode: '8004', country: 'CH' };
    expect(rememberedPostcodeField('gls-ch', GLS, glsSwitzerland, undefined, given)).toEqual({ value: '8004', offered: undefined, country: 'CH' });
    expect(rememberedPostcodeField('dpd', DPD, dpd, undefined, given)).toEqual({ value: '', offered, country: undefined });
    expect(rememberedPostcodeField('gls-ch', GLS, glsSwitzerland, '', given)).toEqual({ value: '', offered: undefined, country: undefined });
    expect(rememberedPostcodeField('dpd', DPD, dpd, '', given)).toEqual({ value: '', offered, country: undefined });
    expect(rememberedPostcodeField('dpd', DPD, dpd, '3011', given)).toEqual({ value: '3011', offered: undefined, country: undefined });
    expect(rememberedPostcodeField('dpd', DPD, undefined, undefined, given)).toEqual({ value: '', offered: undefined, country: undefined });
    expect(rememberedPostcodeField('mondial-relay', MONDIAL, mondialRelay, undefined, [{ carrier: 'spring-gds', postcode: '1000', country: 'BE' }]))
      .toEqual({ value: '1000', offered: undefined, country: 'BE' });
  });
});

describe('the postcodes this device gave', () => {
  afterEach(() => {
    vi.restoreAllMocks();
    for (const { postcode } of renderHook(() => usePostcodeMemory()).result.current) forgetPostcode(postcode!);
    localStorage.removeItem(POSTCODES_STORAGE_KEY);
  });
  const kept = () => JSON.parse(localStorage.getItem(POSTCODES_STORAGE_KEY) ?? '[]') as unknown;

  it('keeps the newest first, once per carrier, and only a few', () => {
    const { result } = renderHook(() => usePostcodeMemory());
    act(() => {
      rememberPostcode('dpd', ' sw1a  1aa ');
      rememberPostcode('gls-ch', '8004');
      rememberPostcode('dpd', 'SW1A 1AA');
    });
    expect(result.current).toEqual([{ carrier: 'dpd', postcode: 'SW1A 1AA', country: 'CH' }, { carrier: 'gls-ch', postcode: '8004', country: 'CH' }]);
    act(() => { for (const postcode of ['1200', '3011', '6900', '7000', '9000']) rememberPostcode('swiss-post', postcode); });
    expect(result.current).toHaveLength(MAX_POSTCODES);
    expect(result.current[0]).toEqual({ carrier: 'swiss-post', postcode: '9000', country: 'CH' });
    expect(result.current).not.toContainEqual(expect.objectContaining({ carrier: 'gls-ch', postcode: '8004' }));
  });

  it('keeps the country each was given from: the one sent with it, or the carrier\'s home', () => {
    rememberPostcode('spring-gds', '1000', 'BE');
    rememberPostcode('spring-gds', '1012 ab');
    rememberPostcode('unknown', '8004');
    expect(kept()).toEqual([{ carrier: 'unknown', postcode: '8004' }, { carrier: 'spring-gds', postcode: '1012 AB', country: 'NL' },
      { carrier: 'spring-gds', postcode: '1000', country: 'BE' }]);
    // Given again from another country, it goes to the front with that country.
    rememberPostcode('spring-gds', '1000', 'LU');
    expect(kept()).toEqual([{ carrier: 'spring-gds', postcode: '1000', country: 'LU' }, { carrier: 'unknown', postcode: '8004' },
      { carrier: 'spring-gds', postcode: '1012 AB', country: 'NL' }]);
    // One kept before countries were still reads, without one.
    localStorage.setItem(POSTCODES_STORAGE_KEY, JSON.stringify([{ carrier: 'spring-gds', postcode: '1000' }, { carrier: 'dpd', postcode: '8004', country: 'Switzerland' }]));
    expect(renderHook(() => usePostcodeMemory()).result.current).toEqual([{ carrier: 'spring-gds', postcode: '1000' }]);
  });

  it('keeps nothing that is not a postcode', () => {
    rememberPostcode('dpd', '');
    rememberPostcode('dpd', 'https://example.test/?postcode=8004');
    expect(kept()).toEqual([]);
    localStorage.setItem(POSTCODES_STORAGE_KEY, JSON.stringify([{ carrier: 'nobody', postcode: '8004' }, { carrier: 'dpd', postcode: '<b>' }, { carrier: 'dpd', postcode: '8004' }]));
    expect(renderHook(() => usePostcodeMemory()).result.current).toEqual([{ carrier: 'dpd', postcode: '8004' }]);
    localStorage.setItem(POSTCODES_STORAGE_KEY, '{damaged');
    expect(renderHook(() => usePostcodeMemory()).result.current).toEqual([]);
  });

  it('forgets a postcode for every carrier it was given to', () => {
    rememberPostcode('dpd', '8004');
    rememberPostcode('gls-ch', '8004');
    rememberPostcode('dpd', '1200');
    forgetPostcode(' 8004 ');
    expect(kept()).toEqual([{ carrier: 'dpd', postcode: '1200', country: 'CH' }]);
    forgetPostcode('1200');
    expect(localStorage.getItem(POSTCODES_STORAGE_KEY)).toBeNull();
  });

  it('outlives the parcels it was given for', () => {
    rememberParcel({ id: LINK_ID, key: OWNER_KEY, view: testView({ parcel: { carrier: 'gls-ch' } }) });
    rememberPostcode('gls-ch', '8004');
    forgetRecent(LINK_ID);
    forgetAllRecents();
    expect(kept()).toEqual([{ carrier: 'gls-ch', postcode: '8004', country: 'CH' }]);
  });

  it('works for the life of the page when storage refuses it', () => {
    vi.spyOn(Storage.prototype, 'setItem').mockImplementation(() => { throw new Error('QuotaExceededError'); });
    vi.spyOn(Storage.prototype, 'getItem').mockImplementation(() => { throw new Error('SecurityError'); });
    const { result } = renderHook(() => usePostcodeMemory());
    act(() => rememberPostcode('dpd', '8004'));
    expect(result.current).toEqual([{ carrier: 'dpd', postcode: '8004', country: 'CH' }]);
  });
});
