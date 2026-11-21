import { deliveryWallClock, estimateDay, type MessageKey, type Translate } from './messages';
import { localizedCalendarDate } from './format';
import { accountChoice } from './accountChoice';
import type { ApiAuth } from './apiClient';
import { saveShowPeekAi } from '../store/notificationPreferences';
import { currentEvent } from './stages';
import type { ParcelWithEvents, PeekAi } from '../types';

/**
 * Peek AI: Peek's own estimate of when a parcel will come, which the server gives only on a strong hint
 * (src/server/peekAi.ts). It is always named as Peek's, never the carrier's.
 */

/** Whether this browser shows Peek AI: "off", or nothing for on. */
export const SHOW_PEEK_AI_STORAGE_KEY = 'deliveryTrackerShowPeekAi';
/** Set while a choice made here has not been saved to the account. */
export const SHOW_PEEK_AI_UNSAVED_KEY = 'deliveryTrackerShowPeekAiUnsaved';

const choice = accountChoice({ storageKey: SHOW_PEEK_AI_STORAGE_KEY, unsavedKey: SHOW_PEEK_AI_UNSAVED_KEY, field: 'showPeekAi', save: saveShowPeekAi });

/** Whether cards and pages show Peek AI's estimates. */
export const useShowPeekAi: () => boolean = choice.useValue;

/** The choice with its switch; an account's follows it to its other devices. */
export const useShowPeekAiChoice: (auth: ApiAuth | undefined) => [boolean, (enabled: boolean) => void] = choice.useChoice;

const pad = (value: number) => String(value).padStart(2, '0');
/** A day on this device's calendar, as the server writes days: "2026-11-24". */
function isoDay(date: Date): string {
  return `${date.getFullYear()}-${pad(date.getMonth() + 1)}-${pad(date.getDate())}`;
}
/** The day a time with its offset names, on its own clock: "2026-11-24T08:12:00+01:00" is the 24th. */
const dayOf = (iso: string | undefined) => iso?.slice(0, 10);
/** A day the server wrote, as a date on this device's calendar. */
function dateOf(day: string): Date {
  const [year, month, date] = day.split('-').map(Number);
  return new Date(year!, month! - 1, date!);
}
function addDays(day: string, days: number): string {
  const date = dateOf(day);
  date.setDate(date.getDate() + days);
  return isoDay(date);
}

/**
 * Peek AI's estimate while it still holds: none from the moment the server gives (`ends_at`), whatever this device's
 * zone, else once its day or hour has passed on this device's clock. A page left open, or kept for a while without a
 * connection, would otherwise go on saying it.
 */
export function currentPeekAi(ai: PeekAi | undefined, now = Date.now()): PeekAi | null {
  if (!ai) return null;
  const ends = ai.ends_at ? Date.parse(ai.ends_at) : NaN;
  if (Number.isFinite(ends)) return now < ends ? ai : null;
  const today = isoDay(new Date(now));
  if (ai.says === 'not_today') return (ai.carrier_day ?? dayOf(ai.out_since)) === today ? ai : null;
  if (ai.says === 'by_time') {
    if (dayOf(ai.out_since) !== today || !ai.time) return null;
    const at = new Date(now);
    return `${pad(at.getHours())}:${pad(at.getMinutes())}` < ai.time ? ai : null;
  }
  // Earlier than the carrier's date holds until that date comes; the other days, until their own has passed.
  if (ai.says === 'sooner') return !ai.carrier_day || ai.carrier_day > today ? ai : null;
  return !ai.day || ai.day >= today ? ai : null;
}

/** Stages after which a parcel is no longer on its way to the door, as the server has them (peekAi.ts). */
const NOT_DUE: ReadonlySet<string> = new Set(['delivered', 'returned', 'ready_for_pickup', 'failed_attempt']);

/**
 * Peek AI's estimate for a parcel while it holds, and while the parcel is still on its way: a copy kept from before a
 * scan that delivered it, or an archived parcel, says none.
 */
export function parcelPeekAi(parcel: Pick<ParcelWithEvents, 'peekAi' | 'events' | 'returnHeld' | 'archivedAt'>, now = Date.now()): PeekAi | null {
  const stage = currentEvent(parcel.events, parcel.returnHeld)?.stage;
  if (parcel.archivedAt || (stage && NOT_DUE.has(stage))) return null;
  return currentPeekAi(parcel.peekAi, now);
}

/** How the reader writes dates and times. */
export interface PeekAiWording {
  t: Translate;
  languageTag: string;
  use24HourTime: boolean;
  now?: number;
}

/** A wall clock Peek AI names ("15:30"), or the hour on a time it gives with its offset, as the reader writes times. */
const clock = (value: string, { languageTag, use24HourTime }: PeekAiWording) =>
  deliveryWallClock(value.length > 5 ? value.slice(11, 16) : value, languageTag, use24HourTime);
const calendar = (day: string, { languageTag }: PeekAiWording) => localizedCalendarDate(dateOf(day), languageTag);

/**
 * The few words after the name on a card: "unlikely" beside the carrier's date when it says today (`estimate`, the
 * carrier's estimate the card shows), "not today" where Peek AI stands alone, else what it expects ("by 16:00", "by Thu
 * 26 Nov").
 */
export function peekAiCardWords(ai: PeekAi, estimate: string | null, wording: PeekAiWording): string {
  const { t, now = Date.now() } = wording;
  switch (ai.says) {
    case 'not_today': {
      const day = estimate ? estimateDay(estimate) : null;
      return t(day && isoDay(day) === isoDay(new Date(now)) ? 'peekAi.card.unlikely' : 'peekAi.card.notToday');
    }
    case 'by_time': return t('peekAi.card.byTime', { time: clock(ai.time ?? '', wording) });
    case 'by_day': return ai.day ? t('peekAi.card.byDay', { date: calendar(ai.day, wording) }) : t('peekAi.card.todayOrTomorrow');
    case 'today_or_tomorrow': return t('peekAi.card.todayOrTomorrow');
    case 'this_week': return t('peekAi.card.thisWeek');
    case 'by_end_of_next_week': return t('peekAi.card.byEndOfNextWeek');
    case 'sooner': return t('peekAi.card.sooner');
  }
}

/** What Peek AI says on the parcel's page, after its name: "today is unlikely", "maybe sooner, from tomorrow". */
export function peekAiClaim(ai: PeekAi, wording: PeekAiWording): string {
  const { t, now = Date.now() } = wording;
  switch (ai.says) {
    case 'not_today': return t('peekAi.claim.notToday');
    case 'by_time': return t('peekAi.claim.byTime', { time: clock(ai.time ?? '', wording) });
    case 'by_day': return ai.day ? t('peekAi.claim.byDay', { date: calendar(ai.day, wording) }) : t('peekAi.claim.todayOrTomorrow');
    case 'today_or_tomorrow': return t('peekAi.claim.todayOrTomorrow');
    case 'this_week': return t('peekAi.claim.thisWeek');
    case 'by_end_of_next_week': return t('peekAi.claim.byEndOfNextWeek');
    case 'sooner':
      if (!ai.day) return t('peekAi.claim.sooner');
      return ai.day === addDays(isoDay(new Date(now)), 1) ? t('peekAi.claim.soonerTomorrow') : t('peekAi.claim.soonerFrom', { date: calendar(ai.day, wording) });
  }
}

/** The claim as a heading: "Today is unlikely". */
export function peekAiHeading(ai: PeekAi, wording: PeekAiWording): string {
  const claim = peekAiClaim(ai, wording);
  return claim.charAt(0).toLocaleUpperCase(wording.languageTag) + claim.slice(1);
}

/**
 * What the carrier says, under Peek AI's heading in its sheet: "DPD says today", "UPS gives no date". A parcel still out
 * late is one the carrier set out to deliver today.
 */
export function peekAiCarrierWord(ai: PeekAi, carrier: string, estimate: string | null, wording: PeekAiWording): string {
  const { t } = wording;
  if (ai.why === 'missed_date') return t('peekAi.carrier.wasDue', { carrier });
  if (ai.why === 'out_for_delivery') return t('peekAi.carrier.noHour', { carrier });
  if (ai.why === 'no_out_for_delivery' || ai.why === 'late_out') return t('peekAi.carrier.saysToday', { carrier });
  if (ai.why === 'ahead' && ai.carrier_day) return t('peekAi.carrier.saysDate', { carrier, date: calendar(ai.carrier_day, wording) });
  return estimate ? t('peekAi.carrier.saysDate', { carrier, date: estimate }) : t('peekAi.carrier.noDate', { carrier });
}

/**
 * How long ago, in words that sit in a sentence: "5 hours ago", "yesterday", "2 days ago", by the calendar on this
 * device once a day has passed.
 */
function ago(iso: string, languageTag: string, now: number): string {
  const format = new Intl.RelativeTimeFormat(languageTag, { numeric: 'auto' });
  const then = new Date(iso);
  const hours = Math.floor((now - then.getTime()) / 3_600_000);
  if (hours < 1) return format.format(-Math.max(1, Math.floor((now - then.getTime()) / 60_000)), 'minute');
  if (hours < 24) return format.format(-hours, 'hour');
  const days = Math.round((dateOf(isoDay(new Date(now))).getTime() - dateOf(isoDay(then)).getTime()) / 86_400_000);
  return days < 14 ? format.format(-days, 'day') : format.format(-Math.floor(days / 7), 'week');
}

const LAST: Record<NonNullable<PeekAi['last']>['kind'], MessageKey> = {
  depot: 'peekAi.last.depot', customs: 'peekAi.last.customs', country: 'peekAi.last.country', scan: 'peekAi.last.scan',
};

/** Why Peek AI thinks so, about this parcel: one or two sentences. */
export function peekAiWhy(ai: PeekAi, carrier: string, wording: PeekAiWording): string {
  const { t, languageTag, now = Date.now() } = wording;
  switch (ai.why) {
    case 'no_out_for_delivery': return t('peekAi.why.noOutForDelivery', { carrier });
    case 'out_for_delivery': return t('peekAi.why.outForDelivery', { carrier, time: clock(ai.out_since ?? '', wording) });
    case 'late_out': return t('peekAi.why.lateOut', { carrier });
    case 'missed_date': return t('peekAi.why.missedDate', { carrier });
    case 'ahead': return t('peekAi.why.ahead', { carrier, date: ai.carrier_day ? calendar(ai.carrier_day, wording) : '' });
    case 'no_date': {
      const first = t('peekAi.why.noDate', { carrier });
      if (!ai.last) return first;
      const step = t(LAST[ai.last.kind], { ago: ago(ai.last.at, languageTag, now) });
      return new Intl.Locale(languageTag).language === 'zh' ? `${first}${step}` : `${first} ${step}`;
    }
  }
}

/** How often Peek AI was right on parcels like this one: "Right 8 times in 10 at this hour." */
export function peekAiRecord(ai: PeekAi, { t }: PeekAiWording): string {
  // Rounded down, so it never says more than it was.
  const n = Math.min(9, Math.max(1, Math.floor(ai.sure * 10 + 1e-9)));
  return t(ai.says === 'not_today' ? 'peekAi.sure.hour' : 'peekAi.sure.parcels', { n });
}

/** One cell of the sheet's strip: a day or two hours, how likely, and whether it is the carrier's date or has passed. */
export interface PeekAiCell {
  key: string;
  /** The small line: a weekday, or nothing for hours. */
  over: string;
  /** The bold line: the day of the month, or the hour. */
  main: string;
  likely: boolean;
  maybe: boolean;
  carrier: boolean;
  past: boolean;
}

/** The strip as a screen reader says it: "Most likely days: Wed 25, Thu 26. DPD’s date: Fri 27". */
export function peekAiStripSpoken(strip: { kind: 'days' | 'hours'; cells: PeekAiCell[] }, carrier: string, { t, languageTag }: PeekAiWording): string {
  const named = (cell: PeekAiCell) => [cell.over, cell.main].filter(Boolean).join(' ');
  const likely = strip.cells.filter((cell) => cell.likely && !cell.past).map(named);
  const ringed = strip.cells.find((cell) => cell.carrier);
  return [
    likely.length ? `${t(strip.kind === 'hours' ? 'peekAi.sheet.hours' : 'peekAi.sheet.days')}: ${new Intl.ListFormat(languageTag, { style: 'short', type: 'unit' }).format(likely)}` : '',
    ringed ? `${t('peekAi.sheet.carrierDate', { carrier })}: ${named(ringed)}` : '',
  ].filter(Boolean).join('. ');
}

/** Days with their chance, the carrier's date ringed; or, out for delivery, two hours to a cell. */
export function peekAiStrip(ai: PeekAi, wording: PeekAiWording): { kind: 'days' | 'hours'; cells: PeekAiCell[] } | null {
  const { languageTag, use24HourTime, now = Date.now() } = wording;
  if (ai.hours?.length) {
    const at = new Date(now);
    const hour = at.getHours() + at.getMinutes() / 60;
    const cells: PeekAiCell[] = [];
    for (let index = 0; index < ai.hours.length; index += 2) {
      const pair = ai.hours.slice(index, index + 2);
      const start = Number(pair[0]!.time.slice(0, 2));
      const share = pair.reduce((sum, entry) => sum + entry.share, 0);
      cells.push({
        key: pair[0]!.time, over: '', main: deliveryWallClock(pair[0]!.time, languageTag, use24HourTime),
        likely: share >= 0.15, maybe: share >= 0.06 && share < 0.15, carrier: false, past: start + pair.length <= hour,
      });
    }
    return { kind: 'hours', cells };
  }
  if (!ai.days?.length) return null;
  const weekday = new Intl.DateTimeFormat(languageTag, { weekday: 'short' });
  return {
    kind: 'days',
    cells: ai.days.map(({ day, p }) => ({
      key: day, over: weekday.format(dateOf(day)).replaceAll('.', ''), main: String(dateOf(day).getDate()),
      likely: p >= 0.25, maybe: p >= 0.08 && p < 0.25, carrier: day === ai.carrier_day, past: false,
    })),
  };
}
