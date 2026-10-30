import { describe, expect, it } from 'vitest';
import {
  localizedCalendarDate,
  localizedCalendarDateTime,
  formatDate,
  formatDateTime,
  formatExpectedDelivery,
  relativeTime,
  scanDate,
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
    expect(localizedCalendarDate(date, locale)).toBe(expected);
    expect(localizedCalendarDateTime(date, locale)).toBe(`${expected}, 14:05`);
    expect(localizedCalendarDate(date, locale, true)).toBe(`${expected} 2026`);
  });

  it('writes Chinese dates with their own characters, and runs the time on after a space', () => {
    const date = new Date(2026, 8, 12, 14, 5);
    expect(localizedCalendarDate(date, 'zh-CN')).toBe('9月12日周六');
    expect(localizedCalendarDate(date, 'zh-CN', true)).toBe('2026年9月12日周六');
    expect(localizedCalendarDateTime(date, 'zh-CN')).toBe('9月12日周六 14:05');
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
});
