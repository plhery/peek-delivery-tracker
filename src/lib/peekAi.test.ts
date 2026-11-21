import { describe, expect, it } from 'vitest';
import fr from '../../shared/locales/fr.json';
import zh from '../../shared/locales/zh.json';
import { translate } from '../i18n';
import type { Locale } from './locale';
import type { Messages, Translate } from './messages';
import type { PeekAi } from '../types';
import {
  currentPeekAi, peekAiCardWords, peekAiCarrierWord, peekAiClaim, peekAiHeading, peekAiRecord, peekAiStrip, peekAiWhy, type PeekAiWording,
} from './peekAi';

// Tuesday 24 November 2026, 15:30 on this device's clock.
const NOW = new Date(2026, 10, 24, 15, 30).getTime();
const english: Translate = (key, variables) => translate('en', key, variables);
const words = (overrides: Partial<PeekAiWording> = {}): PeekAiWording => ({ t: english, languageTag: 'en-CH', use24HourTime: true, now: NOW, ...overrides });
const inLanguage = (locale: Locale, messages: unknown, languageTag: string): PeekAiWording =>
  words({ t: (key, variables) => translate(locale, key, variables, messages as Messages), languageTag });

const NOT_TODAY: PeekAi = { says: 'not_today', why: 'no_out_for_delivery', carrier_day: '2026-11-24', sure: 0.82 };
const BY_TIME: PeekAi = {
  says: 'by_time', why: 'out_for_delivery', time: '16:00', sure: 0.86, out_since: '2026-11-24T08:12:00+01:00',
  hours: ['08:00', '09:00', '10:00', '11:00', '12:00', '13:00', '14:00', '15:00', '16:00', '17:00'].map((time, index) => ({ time, share: [0.02, 0.05, 0.12, 0.2, 0.22, 0.16, 0.1, 0.06, 0.04, 0.03][index]! })),
};
const NO_DATE: PeekAi = {
  says: 'by_day', why: 'no_date', day: '2026-11-26', sure: 0.81, last: { kind: 'depot', at: new Date(NOW - 2 * 86_400_000 - 3_600_000).toISOString() },
  days: [{ day: '2026-11-24', p: 0.05 }, { day: '2026-11-25', p: 0.34 }, { day: '2026-11-26', p: 0.41 }, { day: '2026-11-27', p: 0.12 }],
};
const SOONER: PeekAi = { says: 'sooner', why: 'ahead', day: '2026-11-25', carrier_day: '2026-11-27', sure: 0.71 };

describe('Peek AI on this device’s clock', () => {
  it('says not today only on the carrier’s day, or the day the parcel went out', () => {
    expect(currentPeekAi(NOT_TODAY, NOW)).toBe(NOT_TODAY);
    expect(currentPeekAi(NOT_TODAY, new Date(2026, 10, 25, 9).getTime())).toBeNull();
    const late: PeekAi = { says: 'not_today', why: 'late_out', sure: 0.9, out_since: '2026-11-24T08:12:00+01:00' };
    expect(currentPeekAi(late, NOW)).toBe(late);
    expect(currentPeekAi(late, new Date(2026, 10, 25, 0, 5).getTime())).toBeNull();
  });

  it('names an hour until it comes, on the day the parcel went out', () => {
    expect(currentPeekAi(BY_TIME, NOW)).toBe(BY_TIME);
    expect(currentPeekAi(BY_TIME, new Date(2026, 10, 24, 16, 0).getTime())).toBeNull();
    expect(currentPeekAi(BY_TIME, new Date(2026, 10, 25, 9).getTime())).toBeNull();
  });

  it('holds a day until it has passed, and sooner until the carrier’s own day', () => {
    expect(currentPeekAi(NO_DATE, new Date(2026, 10, 26, 23, 59).getTime())).toBe(NO_DATE);
    expect(currentPeekAi(NO_DATE, new Date(2026, 10, 27, 0, 1).getTime())).toBeNull();
    expect(currentPeekAi(SOONER, new Date(2026, 10, 27, 12).getTime())).toBe(SOONER);
    expect(currentPeekAi(SOONER, new Date(2026, 10, 28, 8).getTime())).toBeNull();
    expect(currentPeekAi(undefined, NOW)).toBeNull();
  });
});

describe('Peek AI’s words', () => {
  it('comments on the carrier’s today on a card, and says not today where it stands alone', () => {
    expect(peekAiCardWords(NOT_TODAY, '2026-11-24', words())).toBe('unlikely');
    expect(peekAiCardWords(NOT_TODAY, null, words())).toBe('not today');
    expect(peekAiCardWords(NOT_TODAY, '2026-11-25', words())).toBe('not today');
    expect(peekAiCardWords(BY_TIME, null, words())).toBe('by 16:00');
    expect(peekAiCardWords(BY_TIME, null, words({ use24HourTime: false, languageTag: 'en-US' }))).toMatch(/^by 4:00\sPM$/);
    expect(peekAiCardWords(NO_DATE, null, words())).toBe('by Thu 26 Nov');
    expect(peekAiCardWords({ ...NO_DATE, says: 'this_week' }, null, words())).toBe('this week');
    expect(peekAiCardWords(SOONER, '2026-11-27', words())).toBe('maybe sooner');
  });

  it('says on the page what it expects, from tomorrow or a named day', () => {
    expect(peekAiClaim(NOT_TODAY, words())).toBe('today is unlikely');
    expect(peekAiHeading(NOT_TODAY, words())).toBe('Today is unlikely');
    expect(peekAiClaim(BY_TIME, words())).toBe('likely by 16:00');
    expect(peekAiClaim(SOONER, words())).toBe('maybe sooner, from tomorrow');
    expect(peekAiClaim({ ...SOONER, day: '2026-11-26' }, words())).toBe('maybe sooner, from Thu 26 Nov');
    expect(peekAiClaim({ ...NO_DATE, says: 'by_end_of_next_week' }, words())).toBe('likely by the end of next week');
  });

  it('names the carrier’s word under its heading, and says why', () => {
    expect(peekAiCarrierWord(NOT_TODAY, 'DPD', 'Today', words())).toBe('DPD says today');
    expect(peekAiCarrierWord(SOONER, 'DPD', 'Fri 27 Nov', words())).toBe('DPD says Fri 27 Nov');
    expect(peekAiCarrierWord(NO_DATE, 'UPS', null, words())).toBe('UPS gives no date');
    expect(peekAiWhy(NOT_TODAY, 'DPD', words())).toBe('DPD says today, but it hasn’t gone out for delivery.');
    expect(peekAiWhy(BY_TIME, 'FedEx', words())).toBe('Out for delivery since 08:12. FedEx names no hour.');
    expect(peekAiWhy(NO_DATE, 'UPS', words())).toBe('UPS gives no date. It reached the delivery depot 2 days ago.');
    const at = (hoursAgo: number) => ({ ...NO_DATE, last: { kind: 'scan' as const, at: new Date(NOW - hoursAgo * 3_600_000).toISOString() } });
    expect(peekAiWhy(at(5), 'UPS', words())).toBe('UPS gives no date. Its last scan was 5 hours ago.');
    // A day or more: by the calendar, then in weeks.
    expect(peekAiWhy(at(24), 'UPS', words())).toBe('UPS gives no date. Its last scan was yesterday.');
    expect(peekAiWhy(at(24 * 20), 'UPS', words())).toBe('UPS gives no date. Its last scan was 2 weeks ago.');
  });

  it('counts how often it was right, rounded down', () => {
    expect(peekAiRecord(NOT_TODAY, words())).toBe('Right 8 times in 10 at this hour.');
    expect(peekAiRecord(NO_DATE, words())).toBe('Right 8 times in 10 on parcels like this one.');
    expect(peekAiRecord({ ...NO_DATE, sure: 0.95 }, words())).toBe('Right 9 times in 10 on parcels like this one.');
  });

  it('writes the reader’s language, with Chinese sentences joined without a space', () => {
    expect(peekAiClaim(NOT_TODAY, inLanguage('fr', fr, 'fr-CH'))).toBe(fr['peekAi.claim.notToday']);
    const why = peekAiWhy(NO_DATE, 'UPS', inLanguage('zh', zh, 'zh-CN'));
    expect(why.startsWith(zh['peekAi.why.noDate'].replace('{{carrier}}', 'UPS'))).toBe(true);
    expect(why).not.toMatch(/。 /);
  });
});

describe('the strip in Peek AI’s sheet', () => {
  it('shows the days with their chance, the carrier’s day ringed', () => {
    const strip = peekAiStrip({ ...NO_DATE, carrier_day: '2026-11-27' }, words())!;
    expect(strip.kind).toBe('days');
    expect(strip.cells.map(({ over, main }) => `${over} ${main}`)).toEqual(['Tue 24', 'Wed 25', 'Thu 26', 'Fri 27']);
    expect(strip.cells.map(({ likely, maybe, carrier }) => [likely, maybe, carrier])).toEqual([
      [false, false, false], [true, false, false], [true, false, false], [false, true, true],
    ]);
  });

  it('shows the hours out for delivery two to a cell, the past ones faded', () => {
    const strip = peekAiStrip(BY_TIME, words())!;
    expect(strip.kind).toBe('hours');
    expect(strip.cells.map(({ main }) => main)).toEqual(['08:00', '10:00', '12:00', '14:00', '16:00']);
    expect(strip.cells.map(({ likely, maybe, past }) => [likely, maybe, past])).toEqual([
      [false, true, true], [true, false, true], [true, false, true], [true, false, false], [false, true, false],
    ]);
    expect(peekAiStrip(NOT_TODAY, words())).toBeNull();
  });
});
