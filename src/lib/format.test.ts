import { afterEach, describe, expect, it, vi } from 'vitest';
import {
  dateShowsYear,
  localizedCalendarDate,
  localizedCalendarDateTime,
  localizedFullDate,
  formatDate,
  formatDateTime,
  formatExpectedDelivery,
  relativeTime,
  scanDate,
  timeZoneName,
} from './format';

const NOW = new Date('2026-07-01T12:00:00.000Z').getTime();

describe('relativeTime', () => {
  it('says "just now" under a minute', () => {
    expect(relativeTime('2026-07-01T11:59:30.000Z', NOW)).toBe('just now');
  });

  it('reports minutes, hours and days', () => {
    expect(relativeTime('2026-07-01T11:45:00.000Z', NOW)).toBe('15 min ago');
    expect(relativeTime('2026-07-01T09:00:00.000Z', NOW)).toBe('3 h ago');
    expect(relativeTime('2026-06-29T12:00:00.000Z', NOW)).toBe('2 d ago');
  });

  it('falls back to a date after a week', () => {
    expect(relativeTime('2026-06-10T12:00:00.000Z', NOW)).toBe(
      formatDate('2026-06-10T12:00:00.000Z'),
    );
  });

  it('returns empty string for invalid input', () => {
    expect(relativeTime('not-a-date', NOW)).toBe('');
  });
});

describe('formatDate / formatDateTime', () => {
  it('uses Swiss dd.mm.yyyy style', () => {
    const iso = new Date(2026, 11, 24, 14, 5).toISOString();
    expect(formatDate(iso)).toBe('24.12.2026');
    expect(formatDateTime(iso)).toBe('24.12.2026, 14:05');
  });

  it('returns empty string for invalid input', () => {
    expect(formatDate('nope')).toBe('');
    expect(formatDateTime('nope')).toBe('');
  });
});

describe('formatExpectedDelivery', () => {
  const today = new Date(2026, 6, 15, 12).getTime();

  it('uses friendly labels for yesterday, today and tomorrow', () => {
    expect(formatExpectedDelivery('2026-07-14', today)).toBe('yesterday');
    expect(formatExpectedDelivery('2026-07-15', today)).toBe('today');
    expect(formatExpectedDelivery('2026-07-16', today)).toBe('tomorrow');
  });

  it('formats other dates and preserves unrecognised carrier values', () => {
    expect(formatExpectedDelivery('2026-07-20', today)).toBe('20.07.2026');
    expect(formatExpectedDelivery('Awaiting carrier estimate', today)).toBe(
      'Awaiting carrier estimate',
    );
  });

  it('preserves DPD delivery windows while formatting their day', () => {
    expect(formatExpectedDelivery('2026-07-15 13:30–14:30', today)).toBe(
      'today, 13:30–14:30',
    );
    expect(formatExpectedDelivery('2026-07-16 09:00', today)).toBe(
      'tomorrow, 09:00',
    );
    expect(formatExpectedDelivery('2026-07-20 08:00-10:00', today)).toBe(
      '20.07.2026, 08:00–10:00',
    );
  });

  it('handles tomorrow across month boundaries', () => {
    const endOfMonth = new Date(2026, 6, 31, 12).getTime();
    expect(formatExpectedDelivery('2026-08-01', endOfMonth)).toBe('tomorrow');
  });
});


describe('readable localized calendar dates', () => {
  it.each([
    ['en-CH', 'Sat 12 Sep'], ['de-CH', 'Sa 12. Sept'],
    ['fr-CH', 'sam 12 sept'], ['it-CH', 'sab 12 set'],
    ['es-ES', 'sáb 12 sept'], ['pt-PT', 'sáb 12 set'], ['pt-BR', 'sáb 12 set'], ['pl-PL', 'sob 12 wrz'],
    ['ru-RU', 'сб 12 сент'],
  ])('formats dates and times in %s', (locale, expected) => {
    const date = new Date(2026, 8, 12, 14, 5);
    expect(localizedCalendarDate(date, locale, date.getTime())).toBe(expected);
    expect(localizedCalendarDateTime(date, locale, true, date.getTime())).toBe(`${expected}, 14:05`);
    // Read a year later, the date says its year.
    expect(localizedCalendarDate(date, locale, new Date(2027, 8, 12).getTime())).toBe(`${expected} 2026`);
  });

  it('writes Chinese dates with their own characters, and runs the time on after a space', () => {
    const date = new Date(2026, 8, 12, 14, 5);
    expect(localizedCalendarDate(date, 'zh-CN', date.getTime())).toBe('9月12日周六');
    expect(localizedCalendarDate(date, 'zh-CN', new Date(2027, 8, 12).getTime())).toBe('2026年9月12日周六');
    expect(localizedCalendarDateTime(date, 'zh-CN', true, date.getTime())).toBe('9月12日周六 14:05');
  });
});

describe('the year on a short date', () => {
  const at = (year: number, month: number, day: number, hour = 12) => new Date(year, month - 1, day, hour);

  it('leaves the year off this year, however far the date is', () => {
    expect(dateShowsYear(at(2026, 1, 1), at(2026, 12, 31).getTime())).toBe(false);
    expect(dateShowsYear(at(2026, 12, 31), at(2026, 1, 1).getTime())).toBe(false);
  });

  it('leaves it off across New Year, both ways, within a month', () => {
    // "Sun 28 Dec" read on 3 January, and an estimate for 2 January read on 28 December.
    expect(localizedCalendarDate(at(2025, 12, 28), 'en-CH', at(2026, 1, 3).getTime())).toBe('Sun 28 Dec');
    expect(localizedCalendarDate(at(2027, 1, 2), 'en-CH', at(2026, 12, 28).getTime())).toBe('Sat 2 Jan');
  });

  it('gives it from the 32nd day in another year, past or future', () => {
    expect(dateShowsYear(at(2025, 12, 1), at(2026, 1, 1).getTime())).toBe(false);
    expect(dateShowsYear(at(2025, 11, 30), at(2026, 1, 1).getTime())).toBe(true);
    expect(dateShowsYear(at(2027, 1, 31), at(2026, 12, 31).getTime())).toBe(false);
    expect(dateShowsYear(at(2027, 2, 1), at(2026, 12, 31).getTime())).toBe(true);
    // Counted in calendar days, whatever the hour on either side.
    expect(dateShowsYear(at(2025, 12, 1, 0), at(2026, 1, 1, 23).getTime())).toBe(false);
    expect(localizedCalendarDate(at(2025, 11, 28), 'en-CH', at(2026, 1, 3).getTime())).toBe('Fri 28 Nov 2025');
  });

  it('keeps each language\'s own order and marks', () => {
    const now = at(2026, 1, 3).getTime();
    expect(localizedCalendarDate(at(2025, 11, 28), 'de-CH', now)).toBe('Fr 28. Nov 2025');
    expect(localizedCalendarDate(at(2025, 11, 28), 'zh-CN', now)).toBe('2025年11月28日周五');
    expect(localizedCalendarDate(at(2025, 12, 28), 'de-CH', now)).toBe('So 28. Dez');
    expect(localizedCalendarDate(at(2025, 12, 28), 'zh-CN', now)).toBe('12月28日周日');
    expect(localizedCalendarDateTime(at(2025, 11, 28, 14), 'zh-CN', true, now)).toBe('2025年11月28日周五 14:00');
  });
});

describe('a date in full', () => {
  const date = new Date(2024, 10, 28, 14, 32);

  it('names the weekday, day, month and year, and the time when there is one', () => {
    expect(localizedFullDate(date, 'en-CH')).toBe('Thursday, 28 November 2024');
    expect(localizedFullDate(date, 'en-CH', { use24HourTime: true })).toBe('Thursday, 28 November 2024, 14:32');
    expect(localizedFullDate(date, 'en-US', { use24HourTime: false })).toBe('Thursday, November 28, 2024, 2:32 PM');
  });

  it('writes it as each language does', () => {
    expect(localizedFullDate(date, 'de-CH', { use24HourTime: true })).toBe('Donnerstag, 28. November 2024, 14:32');
    expect(localizedFullDate(date, 'fr-CH', { use24HourTime: true })).toBe('jeudi, 28 novembre 2024, 14:32');
    expect(localizedFullDate(date, 'zh-CN', { use24HourTime: true })).toBe('2024年11月28日星期四 14:32');
  });
});

describe('scanDate', () => {
  it('reads a scan dated by its day alone as that day on the reader\'s calendar, untimed', () => {
    // The start of the day in the carrier's zone can be the evening before for the reader.
    expect(scanDate({ occurredAt: new Date(2026, 9, 1, 23).toISOString(), day: '2026-10-02' })).toEqual({ date: new Date(2026, 9, 2), timed: false });
    expect(scanDate({ occurredAt: new Date(2026, 9, 2, 9, 12).toISOString() })).toEqual({ date: new Date(2026, 9, 2, 9, 12), timed: true });
    expect(scanDate({ occurredAt: new Date(2026, 9, 2, 9, 12).toISOString(), day: '2026-02-30' })).toEqual({ date: new Date(2026, 9, 2, 9, 12), timed: true });
    expect(scanDate({ occurredAt: 'not a date' })).toBeNull();
  });

  it('reads a status seen at a check as the check\'s day, untimed: the check\'s time is not the carrier\'s', () => {
    expect(scanDate({ occurredAt: new Date(2026, 9, 2, 14, 5).toISOString(), observed: true })).toEqual({ date: new Date(2026, 9, 2), timed: false });
    expect(scanDate({ occurredAt: 'not a date', observed: true })).toBeNull();
  });
});

describe('timeZoneName', () => {
  afterEach(() => { vi.useRealTimers(); vi.restoreAllMocks(); });

  it('names a zone in the reader’s language by its name for the whole year, in summer as in winter', () => {
    vi.useFakeTimers({ toFake: ['Date'] });
    vi.setSystemTime(new Date('2026-07-15T12:00:00Z'));
    expect(timeZoneName('en-GB', 'Europe/Paris')).toBe('Central European Time');
    expect(timeZoneName('de', 'Europe/Berlin')).toBe('Mitteleuropäische Zeit');
    expect(timeZoneName('en-US', 'America/New_York')).toBe('Eastern Time');
    vi.setSystemTime(new Date('2026-01-15T12:00:00Z'));
    expect(timeZoneName('fr', 'Europe/Paris')).toBe('heure d’Europe centrale');
    expect(timeZoneName('zh-CN', 'Europe/Helsinki')).toBe('东欧时间');
  });

  it('names the reader’s own zone when given none', () => {
    const own = new Intl.DateTimeFormat().resolvedOptions().timeZone;
    expect(timeZoneName('it', undefined)).toBe(timeZoneName('it', own));
  });

  it('gives the zone’s id where the engine names no zone for the whole year', () => {
    const Native = Intl.DateTimeFormat;
    vi.spyOn(Intl, 'DateTimeFormat').mockImplementation(((locales?: string | string[], options?: Intl.DateTimeFormatOptions) => {
      if (options?.timeZoneName === 'longGeneric') throw new RangeError('timeZoneName');
      return new Native(locales, options);
    }) as unknown as typeof Intl.DateTimeFormat);
    expect(timeZoneName('es', 'America/Argentina/Buenos_Aires')).toBe('America/Argentina/Buenos Aires');
  });
});
