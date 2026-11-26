import { describe, expect, it } from 'vitest';
import fr from '../../shared/locales/fr.json';
import ru from '../../shared/locales/ru.json';
import zh from '../../shared/locales/zh.json';
import { translate } from '../i18n';
import type { Locale } from './locale';
import type { Messages, Translate } from './messages';
import type { PeekAi } from '../types';
import {
  currentPeekAi, peekAiCardWords, peekAiCarrierWord, peekAiClaim, peekAiHeading, peekAiReason, peekAiRecord, peekAiShades, peekAiStrip, peekAiStripSpoken,
  peekAiWhy,
  type PeekAiWording,
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
const SOONER: PeekAi = { says: 'sooner', why: 'ahead', day: '2026-11-25', carrier_day: '2026-11-27', sure: 0.88 };

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

  it('holds a day until it has passed, and sooner until the carrier’s own day comes', () => {
    expect(currentPeekAi(NO_DATE, new Date(2026, 10, 26, 23, 59).getTime())).toBe(NO_DATE);
    expect(currentPeekAi(NO_DATE, new Date(2026, 10, 27, 0, 1).getTime())).toBeNull();
    expect(currentPeekAi(SOONER, new Date(2026, 10, 26, 23, 59).getTime())).toBe(SOONER);
    expect(currentPeekAi(SOONER, new Date(2026, 10, 27, 0, 1).getTime())).toBeNull();
    expect(currentPeekAi(undefined, NOW)).toBeNull();
  });

  it('ends at the moment the server gives, whatever this device’s zone', () => {
    const zurich: PeekAi = { ...BY_TIME, ends_at: '2026-11-24T16:00:00+01:00' };
    expect(currentPeekAi(zurich, Date.parse('2026-11-24T14:59:00Z'))).toBe(zurich);
    expect(currentPeekAi(zurich, Date.parse('2026-11-24T15:00:00Z'))).toBeNull();
    // A copy kept from yesterday: the moment holds, not this device's day.
    const tonight: PeekAi = { ...NOT_TODAY, ends_at: '2026-11-25T00:00:00+01:00' };
    expect(currentPeekAi(tonight, Date.parse('2026-11-24T22:59:00Z'))).toBe(tonight);
    expect(currentPeekAi(tonight, Date.parse('2026-11-24T23:00:00Z'))).toBeNull();
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
    // Kept past its first day, it names none.
    expect(peekAiClaim({ ...SOONER, day: '2026-11-23' }, words())).toBe('maybe sooner');
    expect(peekAiClaim({ ...NO_DATE, says: 'by_end_of_next_week' }, words())).toBe('likely by the end of next week');
  });

  it('names the carrier’s word under its heading, and says why', () => {
    expect(peekAiCarrierWord(NOT_TODAY, 'DPD', 'Today', words())).toBe('DPD says today');
    expect(peekAiCarrierWord(SOONER, 'DPD', 'Fri 27 Nov', words())).toBe('DPD says Fri 27 Nov');
    expect(peekAiCarrierWord(NO_DATE, 'UPS', null, words())).toBe('UPS gives no date');
    // Still out late: the carrier set out to deliver it today, though its date is hidden while out for delivery.
    expect(peekAiCarrierWord({ says: 'not_today', why: 'late_out', sure: 0.88 }, 'DPD', null, words())).toBe('DPD says today');
    expect(peekAiWhy(NOT_TODAY, 'DPD', words())).toBe('DPD says today, but it hasn’t gone out for delivery.');
    expect(peekAiWhy(BY_TIME, 'FedEx', words())).toBe('Out for delivery since 08:12. FedEx names no hour.');
    expect(peekAiWhy(NO_DATE, 'UPS', words())).toBe('UPS gives no date. It reached the delivery depot 2 days ago.');
    const at = (hoursAgo: number) => ({ ...NO_DATE, last: { kind: 'scan' as const, at: new Date(NOW - hoursAgo * 3_600_000).toISOString() } });
    expect(peekAiWhy(at(5), 'UPS', words())).toBe('UPS gives no date. Its last scan was 5 hours ago.');
    // A day or more: by the calendar, then in weeks.
    expect(peekAiWhy(at(24), 'UPS', words())).toBe('UPS gives no date. Its last scan was yesterday.');
    expect(peekAiWhy(at(24 * 20), 'UPS', words())).toBe('UPS gives no date. Its last scan was 2 weeks ago.');
  });

  it('says in its sheet what it goes by: the parcels Peek has followed, the carrier’s usual hour before this one’s', () => {
    expect(peekAiReason(NOT_TODAY, 'DPD', words())).toBe(
      'It hasn’t gone out for delivery yet. Most parcels Peek has followed that weren’t out by this hour didn’t arrive that day.');
    expect(peekAiReason(BY_TIME, 'FedEx', words())).toBe(
      'Most FedEx parcels Peek has followed arrived by 16:00 on the day they went out for delivery. This one went out at 08:12.');
    expect(peekAiReason({ says: 'not_today', why: 'late_out', sure: 0.88 }, 'DPD', words())).toBe(
      'It’s still out for delivery this late. Most parcels Peek has followed that were still out at this hour didn’t arrive that day.');
    expect(peekAiReason({ ...NO_DATE, why: 'missed_date', says: 'today_or_tomorrow' }, 'DPD', words())).toBe(
      'It was scanned in the last 24 hours. Most parcels Peek has followed that kept moving after missing their date arrived within a day or two.');
    expect(peekAiReason(SOONER, 'DPD', words())).toBe('Most parcels Peek has followed with scans like this one’s came before the date their carrier gave.');
    expect(peekAiReason(NO_DATE, 'UPS', words())).toBe(
      'Peek AI goes by how long parcels Peek has followed with scans like this one’s took. It reached the delivery depot 2 days ago.');
  });

  it('names how many parcels it stands on when the server says, in the reader’s numbers and count forms', () => {
    expect(peekAiReason({ ...NOT_TODAY, parcels: 89 }, 'DPD', words())).toBe(
      'It hasn’t gone out for delivery yet. Of 89 parcels Peek has followed that weren’t out for delivery by this hour on the day their carrier gave, most didn’t arrive that day.');
    expect(peekAiReason({ ...BY_TIME, parcels: 85, start_before: '10:00' }, 'FedEx', words({ use24HourTime: false }))).toBe(
      'Of 85 FedEx parcels Peek has followed that went out for delivery before 10:00 AM, most arrived by 4:00 PM that day. This one went out at 8:12 AM.');
    // Without the round's start, the hour is said as before.
    expect(peekAiReason({ ...BY_TIME, parcels: 85 }, 'FedEx', words())).toBe(
      'Most FedEx parcels Peek has followed arrived by 16:00 on the day they went out for delivery. This one went out at 08:12.');
    expect(peekAiReason({ says: 'not_today', why: 'late_out', sure: 0.87, parcels: 508 }, 'DPD', words())).toBe(
      'It’s still out for delivery this late. Of 508 parcels Peek has followed that were still out at this hour, most didn’t arrive that day.');
    expect(peekAiReason({ ...SOONER, parcels: 4988 }, 'DPD', words({ languageTag: 'en-US' }))).toBe(
      'Peek AI learned from 4,988 parcels Peek has followed. Most with scans like this one’s came before the date their carrier gave.');
    expect(peekAiReason({ ...NO_DATE, parcels: 4988 }, 'UPS', words({ languageTag: 'en-US' }))).toBe(
      'Peek AI learned from 4,988 parcels Peek has followed, and goes by how long those with scans like this one’s took. It reached the delivery depot 2 days ago.');
    // A missed date names no count.
    expect(peekAiReason({ ...NO_DATE, why: 'missed_date', says: 'today_or_tomorrow', parcels: 14 }, 'DPD', words())).toMatch(/^It was scanned in the last 24 hours\./);
    // Russian reads 21 and 4,981 with its one form, 22 with few, 25 with many.
    const russian = inLanguage('ru', ru, 'ru-RU');
    expect(peekAiReason({ ...SOONER, parcels: 21 }, 'DPD', russian)).toMatch(/^Peek AI учится на 21 посылке,/);
    expect(peekAiReason({ ...SOONER, parcels: 4981 }, 'DPD', russian)).toMatch(/^Peek AI учится на 4\s981 посылке,/);
    expect(peekAiReason({ ...NOT_TODAY, parcels: 22 }, 'DPD', russian)).toContain('Из 22 посылок,');
    expect(peekAiReason({ ...NOT_TODAY, parcels: 25 }, 'DPD', russian)).toContain('Из 25 посылок,');
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
  it('shades each cell in four steps against the likeliest still to come, none under 3 in 100 or gone by', () => {
    const cells = [0.02, 0.06, 0.15, 0.25, 0.36, 0.4].map((chance) => ({ chance, past: false }));
    expect(peekAiShades(cells)).toEqual([0, 1, 2, 3, 4, 4]);
    // The likeliest hours gone by leave the scale to the ones still to come.
    expect(peekAiShades([{ chance: 0.5, past: true }, { chance: 0.2, past: false }, { chance: 0.1, past: false }])).toEqual([0, 4, 2]);
    expect(peekAiShades([{ chance: 0.5, past: true }])).toEqual([0]);
  });

  it('shows the days shaded by their chance, the carrier’s day ringed, and says them likeliest first', () => {
    const strip = peekAiStrip({ ...NO_DATE, carrier_day: '2026-11-27' }, words())!;
    expect(strip.kind).toBe('days');
    expect(strip.cells.map(({ over, main }) => `${over} ${main}`)).toEqual(['Tue 24', 'Wed 25', 'Thu 26', 'Fri 27']);
    expect(strip.cells.map(({ level, carrier }) => [level, carrier])).toEqual([[1, false], [3, false], [4, false], [2, true]]);
    // What its shades and ring show, in words.
    expect(peekAiStripSpoken(strip, 'DPD', words())).toBe('Days, likeliest first: Thu 26, Wed 25, Fri 27, Tue 24. DPD’s date: Fri 27');
  });

  it('shows the hours out for delivery two to a cell, the past ones faded and unshaded', () => {
    // 15:30 on the parcel's clock, the one its hours are on (+01:00).
    const strip = peekAiStrip(BY_TIME, words({ now: Date.parse('2026-11-24T14:30:00Z') }))!;
    expect(strip.kind).toBe('hours');
    expect(strip.cells.map(({ main }) => main)).toEqual(['08:00', '10:00', '12:00', '14:00', '16:00']);
    expect(strip.cells.map(({ level, past }) => [level, past])).toEqual([[0, true], [0, true], [0, true], [4, false], [2, false]]);
    expect(peekAiStripSpoken(strip, 'DPD', words())).toBe('Hours, likeliest first: 14:00, 16:00');
    // In the morning the midday hours are the likeliest.
    const morning = peekAiStrip(BY_TIME, words({ now: Date.parse('2026-11-24T08:30:00Z') }))!;
    expect(morning.cells.map(({ level }) => level)).toEqual([1, 3, 4, 2, 1]);
    expect(peekAiStripSpoken(morning, 'DPD', words())).toBe('Hours, likeliest first: 12:00, 10:00, 14:00, 08:00, 16:00');
    expect(peekAiStrip(NOT_TODAY, words())).toBeNull();
  });

  it('tells the hours gone by on the parcel’s clock, not on this device’s', () => {
    // Out in Tokyo; 14:30 there is 05:30 on this device's clock (UTC), when no hour of the round has gone by.
    const tokyo = peekAiStrip({ ...BY_TIME, out_since: '2026-11-24T08:12:00+09:00' }, words({ now: Date.parse('2026-11-24T05:30:00Z') }))!;
    expect(tokyo.cells.map(({ level, past }) => [level, past])).toEqual([[0, true], [0, true], [0, true], [4, false], [2, false]]);
    expect(peekAiStripSpoken(tokyo, 'DPD', words())).toBe('Hours, likeliest first: 14:00, 16:00');
  });

  it('fades the days gone by of a copy kept past midnight, and names none of them', () => {
    const kept: PeekAi = { ...SOONER, days: [{ day: '2026-11-24', p: 0.4 }, { day: '2026-11-25', p: 0.3 }, { day: '2026-11-26', p: 0.2 }, { day: '2026-11-27', p: 0.1 }] };
    const strip = peekAiStrip(kept, words({ now: new Date(2026, 10, 25, 9).getTime() }))!;
    expect(strip.cells.map(({ level, past }) => [level, past])).toEqual([[0, true], [4, false], [3, false], [2, false]]);
    expect(peekAiStripSpoken(strip, 'DPD', words())).toBe('Days, likeliest first: Wed 25, Thu 26, Fri 27. DPD’s date: Fri 27');
  });
});
