import { describe, expect, it } from 'vitest';
import { romanisesPlace, scanCountry, scanPlace } from './scanPlace';

describe('scan places', () => {
  it.each([
    ['Basildon, GB', undefined, 'GB', 'Basildon'],
    ['Haiger, DE', 'DE', 'DE', 'Haiger'],
    ['Haiger (DE)', 'DE', 'DE', 'Haiger'],
    ['Paris, FR', 'FR', 'FR', 'Paris'],
    ['Toronto, CA', 'CA', 'CA', 'Toronto'],
    // A state, province or canton keeps its code when the scan was placed elsewhere, or nowhere.
    ['Wilmington, DE', 'US', null, 'Wilmington, DE'],
    ['Austin, TX', 'US', null, 'Austin, TX'],
    ['Los Angeles, CA', 'US', null, 'Los Angeles, CA'],
    ['Fribourg, FR', 'CH', null, 'Fribourg, FR'],
    ['Hannover, DE', undefined, null, 'Hannover, DE'],
  ])('reads %s placed in %s as %s, %s', (location, placed, country, place) => {
    expect(scanPlace(location, placed ? { country: placed, name: 'Town', precision: 'city' } : undefined)).toEqual({ country, place });
  });

  it('names a place written in Chinese, Japanese or Korean script by the town it was placed in', () => {
    const shenzhen = { country: 'CN', name: 'Shenzhen', precision: 'city' as const };
    expect(scanPlace('深圳市', shenzhen, 'de')).toEqual({ country: 'CN', place: 'Shenzhen' });
    expect(scanPlace('【深圳市】', shenzhen)).toEqual({ country: 'CN', place: 'Shenzhen' });
    expect(scanPlace('上海市, CN', { country: 'CN', name: 'Shanghai', precision: 'city' }, 'en')).toEqual({ country: 'CN', place: 'Shanghai' });
    expect(scanPlace('東京', { country: 'JP', name: 'Tokyo', precision: 'city' }, 'fr')).toEqual({ country: 'JP', place: 'Tokyo' });
    expect(scanPlace('中国 广东省', { country: 'CN', name: 'China', precision: 'country' }, 'en')).toEqual({ country: 'CN', place: '' });
    // Placed nowhere, it stays as written.
    expect(scanPlace('台北', null, 'en')).toEqual({ country: null, place: '台北' });
    // A Chinese reader keeps Chinese characters, not kana or hangul.
    expect(scanPlace('深圳市', shenzhen, 'zh')).toEqual({ country: null, place: '深圳市' });
    expect(scanPlace('서울', { country: 'KR', name: 'Seoul', precision: 'city' }, 'zh')).toEqual({ country: 'KR', place: 'Seoul' });
    // Latin script keeps its own spelling.
    expect(scanPlace('Shenzhen', shenzhen, 'en')).toEqual({ country: null, place: 'Shenzhen' });
    expect(romanisesPlace('ZUERICH, CH')).toBe(false);
    expect(romanisesPlace('とうきょう', 'zh')).toBe(true);
  });

  it('counts a final field only, settled by where the scan was placed', () => {
    expect(scanCountry('Zürich, CH')).toBe('CH');
    expect(scanCountry('Haiger, DE', 'DE')).toBe('DE');
    expect(scanCountry('Wilmington, DE', 'US')).toBeNull();
    expect(scanCountry('Austin, TX', 'US')).toBeNull();
    // Never a country guessed from a town, wherever it was placed.
    expect(scanCountry('Basel', 'CH')).toBeNull();
    expect(scanCountry(undefined, 'DE')).toBeNull();
  });
});
