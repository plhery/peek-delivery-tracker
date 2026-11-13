import trackingMessages from '../../shared/tracking-messages.json';
import type en from '../../shared/locales/en.json';
import type { Stage, TrackingEvent } from '../types';
import { dayAndTime, localizedCalendarDate, localizedCalendarDateTime, localizedClockTime, scanDate } from './format';
import type { Locale } from './locale';

/**
 * The words of the app without its React side: message types, translation and
 * the phrases built from them. The server uses these for link previews; the
 * client reaches them through `src/i18n.tsx`.
 */
export type MessageKey = keyof typeof en;
export type Messages = Record<MessageKey, string>;

export type Translate = (
  key: MessageKey,
  variables?: Record<string, string | number>,
) => string;

export const languageTags: Record<Locale, string> = {
  en: 'en-CH', de: 'de-CH', fr: 'fr-CH', it: 'it-CH', es: 'es-ES', pt: 'pt-PT', 'pt-br': 'pt-BR', pl: 'pl-PL', ru: 'ru-RU', zh: 'zh-CN',
};
/**
 * The languages whose counts take more forms than one and the rest, with their CLDR rules: `.one`, `.few` and
 * `.many`. In Russian `.one` also counts 21, 31 and 101, so its copy writes the number.
 */
const countRules: Partial<Record<Locale, Intl.PluralRules>> = { pl: new Intl.PluralRules('pl-PL'), ru: new Intl.PluralRules('ru-RU') };

/** One message of a language, with its count form and its variables filled in. */
export function translateMessage(
  locale: Locale,
  key: MessageKey,
  variables: Record<string, string | number> | undefined,
  messages: Messages,
): string {
  const count = variables?.count;
  const rules = countRules[locale];
  const category = typeof count === 'number' && rules ? rules.select(count) : count === 1 ? 'one' : 'other';
  const baseKey = key.replace(/\.(one|few|many)$/, '');
  const pluralKey = `${baseKey}.${category}` as MessageKey;
  let message: string = category !== 'other' && pluralKey in messages ? messages[pluralKey] : messages[key];
  for (const [name, value] of Object.entries(variables ?? {})) {
    message = message.replaceAll(`{{${name}}}`, () => String(value));
  }
  return message;
}

export function stageLabel(t: Translate, stage: Stage): string {
  return t(`stage.${stage}` as MessageKey);
}

export function localizedRelativeTime(
  iso: string,
  t: Translate,
  languageTag: string,
  now: number = Date.now(),
): string {
  const then = new Date(iso).getTime();
  if (Number.isNaN(then)) return '';
  const minutes = Math.floor((now - then) / 60_000);
  if (minutes < 1) return t('time.justNow');
  if (minutes < 60) return t('time.minutesAgo', { count: minutes });
  const hours = Math.floor(minutes / 60);
  if (hours < 24) return t('time.hoursAgo', { count: hours });
  const days = Math.floor(hours / 24);
  if (days < 7) return t('time.daysAgo', { count: days });
  return localizedCalendarDate(new Date(iso), languageTag);
}

/** How long ago a scan was ("3 h ago"); a scan dated by its day alone gives that day ("yesterday"). */
export function localizedScanAge(
  event: Pick<TrackingEvent, 'occurredAt' | 'day'>,
  t: Translate,
  languageTag: string,
  now: number = Date.now(),
): string {
  const when = scanDate(event);
  if (!when) return '';
  return when.timed ? localizedRelativeTime(event.occurredAt, t, languageTag, now) : localizedDeliveryDate(when.date, t, languageTag, now);
}

/** Relative labels for nearby local calendar days, otherwise a compact date. */
export function localizedDeliveryDate(
  date: Date,
  t: Translate,
  languageTag: string,
  now: number = Date.now(),
): string {
  const today = new Date(now);
  for (const [offset, key] of [[-1, 'time.yesterday'], [0, 'time.today'], [1, 'time.tomorrow']] as const) {
    const day = new Date(today.getFullYear(), today.getMonth(), today.getDate() + offset);
    if (date.getFullYear() === day.getFullYear() &&
        date.getMonth() === day.getMonth() && date.getDate() === day.getDate()) return t(key);
  }
  return localizedCalendarDate(date, languageTag);
}

export function deliveryWallClock(value: string, languageTag: string, use24HourTime: boolean): string {
  if (use24HourTime) return value;
  // A carrier's wall clock has no zone. Format it in UTC so its hours survive local clock changes.
  const date = new Date(`2000-01-01T${value}:00Z`);
  return Number.isNaN(date.getTime()) ? value : localizedClockTime(date, languageTag, false, 'UTC');
}

export function localizedExpectedDelivery(
  value: string,
  t: Translate,
  languageTag: string,
  now: number = Date.now(),
  use24HourTime = true,
): string {
  const windowMatch = /^(\d{4}-\d{2}-\d{2})[ T]+(\d{2}:\d{2})(?:[–-](\d{2}:\d{2}))?$/.exec(value.trim());
  if (windowMatch) {
    const date = localizedExpectedDelivery(windowMatch[1], t, languageTag, now, use24HourTime);
    const start = deliveryWallClock(windowMatch[2], languageTag, use24HourTime);
    const end = windowMatch[3] ? `–${deliveryWallClock(windowMatch[3], languageTag, use24HourTime)}` : '';
    return dayAndTime(date, start + end, languageTag);
  }
  const expected = /^\d{4}-\d{2}-\d{2}$/.test(value)
    ? new Date(`${value}T00:00:00`)
    : new Date(value);
  if (Number.isNaN(expected.getTime())) return value;
  const day = localizedDeliveryDate(expected, t, languageTag, now);
  if (/T\d{2}:\d{2}/.test(value)) {
    const time = localizedClockTime(expected, languageTag, use24HourTime);
    return dayAndTime(day, time, languageTag);
  }
  return day;
}

/** Calendar dates need a preposition; relative dates such as "today" do not. */
export function localizedDatePhrase(date: string, t: Translate): string {
  const relative = (['time.yesterday', 'time.today', 'time.tomorrow'] as const)
    .some((key) => date === t(key));
  return relative ? date : t('parcel.onDate', { date });
}

/** Translate messages created by this app; preserve the carrier’s original scan notes. */
export function localizedEventDescription(description: string, t: Translate): string {
  const messages: Record<string, { key: string; variables: Record<string, string> }> = trackingMessages.events;
  const message = Object.hasOwn(messages, description) ? messages[description] : undefined;
  return message ? t(message.key as MessageKey, message.variables) : description;
}

/** A placeholder number, such as 123456789: no carrier will ever know it, and only the owner can correct it. */
export const PLACEHOLDER_NUMBER = 'carrier:placeholder_number';
/** A number the parcel's carrier does not issue, such as one whose carrier was chosen by hand. */
export const REFUSED_NUMBER = 'carrier:refused_number';

/** Whether only the owner can make the number trackable: a placeholder, or one its carrier does not issue. */
export function numberToCheck(error: string | null | undefined): boolean {
  return error === PLACEHOLDER_NUMBER || error === REFUSED_NUMBER;
}

/** Only stable service codes select specific copy; diagnostics never reach the UI. */
export function trackingFailureMessage(error: string | undefined, t: Translate): string {
  const kind = error?.startsWith('carrier:') ? error.slice('carrier:'.length) : '';
  const messages: Record<string, string> = trackingMessages.failures;
  return t((Object.hasOwn(messages, kind) ? messages[kind] : 'detail.trackingUnavailable') as MessageKey);
}

/** The local day an estimate falls on, at midnight: a date's own, or a time's on this device's clock. */
export function estimateDay(value: string): Date | null {
  const match = /^(\d{4}-\d{2}-\d{2})/.exec(value.trim());
  if (!match) return null;
  const day = /T\d{2}:\d{2}/.test(value) && Number.isFinite(Date.parse(value)) ? new Date(value) : new Date(`${match[1]}T00:00:00`);
  if (!Number.isFinite(day.getTime())) return null;
  day.setHours(0, 0, 0, 0);
  return day;
}

function startOfToday(now: number): Date {
  const today = new Date(now);
  today.setHours(0, 0, 0, 0);
  return today;
}

/** The first and last day of a window that spans more than one. */
function windowDays(from: string | undefined, to: string): { first: Date; last: Date } | null {
  const [first, last] = [from ? estimateDay(from) : null, estimateDay(to)];
  return first && last && first < last ? { first, last } : null;
}

/** Whether the window of several days an estimate gives has begun: the parcel may come any day until its last. */
export function deliveryWindowOpen(from: string | undefined, to: string, now = Date.now()): boolean {
  const days = windowDays(from, to);
  return !!days && days.first <= startOfToday(now);
}

/**
 * A window between two days, or within one day, which it names once: "Tue 5 Mar, 10:00–14:00". Once its first day has
 * passed, both ends are dated, as the carrier gave them: "Wed 4 Nov – Fri 6 Nov".
 */
export function localizedDeliveryWindow(from: string | undefined, to: string, t: Translate, languageTag: string, now = Date.now(), use24HourTime = true): string {
  const end = localizedExpectedDelivery(to, t, languageTag, now, use24HourTime);
  if (!from || !Number.isFinite(Date.parse(from)) || !Number.isFinite(Date.parse(to)) || Date.parse(from) >= Date.parse(to)) return end;
  const timed = (value: string) => /T\d{2}:\d{2}/.test(value);
  const [first, last] = [new Date(from), new Date(to)];
  if (timed(from) && timed(to) && first.toDateString() === last.toDateString()) {
    const times = [first, last].map((date) => localizedClockTime(date, languageTag, use24HourTime));
    return dayAndTime(localizedDeliveryDate(first, t, languageTag, now), times.join('–'), languageTag);
  }
  const days = windowDays(from, to);
  if (days && days.first < startOfToday(now)) {
    const ending = timed(to) ? localizedCalendarDateTime(last, languageTag, use24HourTime) : localizedCalendarDate(days.last, languageTag);
    return `${localizedCalendarDate(days.first, languageTag)} – ${ending}`;
  }
  return `${localizedExpectedDelivery(from, t, languageTag, now, use24HourTime)} – ${end}`;
}

/**
 * An estimate as short as a card writes it. A window of several days reads whole until it begins, then by the day it
 * ends, since the parcel may come any day until then: "by Fri 6 Nov". The parcel's page gives the whole window.
 */
export function localizedDeliveryEstimate(from: string | undefined, to: string, t: Translate, languageTag: string, now = Date.now(), use24HourTime = true): string {
  const days = windowDays(from, to);
  const today = startOfToday(now);
  if (!days || days.first > today || days.last < today) return localizedDeliveryWindow(from, to, t, languageTag, now, use24HourTime);
  if (days.last.getTime() === today.getTime()) return t('time.today');
  const tomorrow = new Date(today.getFullYear(), today.getMonth(), today.getDate() + 1);
  return days.last.getTime() === tomorrow.getTime() ? t('parcel.byTomorrow')
    : t('parcel.byDate', { date: localizedCalendarDate(days.last, languageTag) });
}
