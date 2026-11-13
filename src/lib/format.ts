import type { TrackingEvent } from '../types';

const MINUTE = 60_000;
const HOUR = 60 * MINUTE;
const DAY = 24 * HOUR;

/** A YYYY-MM-DD day as the start of that day on the reader's calendar, or null when it is no such day. */
function calendarDay(value: string): Date | null {
  const dateOnly = /^(\d{4})-(\d{2})-(\d{2})$/.exec(value);
  if (!dateOnly) return null;
  const year = Number(dateOnly[1]);
  const month = Number(dateOnly[2]) - 1;
  const day = Number(dateOnly[3]);
  const parsed = new Date(year, month, day);
  return parsed.getFullYear() === year &&
    parsed.getMonth() === month &&
    parsed.getDate() === day
    ? parsed
    : null;
}

function parseDeliveryDate(value: string): Date | null {
  const trimmed = value.trim();
  if (/^\d{4}-\d{2}-\d{2}$/.test(trimmed)) return calendarDay(trimmed);
  const parsed = new Date(trimmed);
  return Number.isNaN(parsed.getTime()) ? null : parsed;
}

/**
 * When a scan happened, on the reader's calendar. A scan the carrier dated by
 * its day alone is that day, with no time (`timed` false): its `occurredAt`
 * only puts it in order.
 */
export function scanDate(event: Pick<TrackingEvent, 'occurredAt' | 'day'>): { date: Date; timed: boolean } | null {
  const day = event.day ? calendarDay(event.day) : null;
  if (day) return { date: day, timed: false };
  const date = new Date(event.occurredAt);
  return Number.isNaN(date.getTime()) ? null : { date, timed: true };
}

const zoneNames = new Map<string, string>();

/**
 * The zone scan times are shown in, the reader's own unless `timeZone` names another, as the language names it all
 * year round: "Central European Time", not its summer or winter name nor an offset that changes between them. An
 * engine without such names gives the zone's id ("America/New York").
 */
export function timeZoneName(languageTag: string, timeZone?: string): string {
  const zone = timeZone ?? new Intl.DateTimeFormat().resolvedOptions().timeZone;
  const key = `${languageTag}|${zone}`;
  let name = zoneNames.get(key);
  if (name === undefined) {
    try {
      name = new Intl.DateTimeFormat(languageTag, { timeZone: zone, timeZoneName: 'longGeneric' })
        .formatToParts(new Date()).find((part) => part.type === 'timeZoneName')?.value;
    } catch {
      // An engine without generic zone names refuses the option.
    }
    name ||= zone.replaceAll('_', ' ');
    zoneNames.set(key, name);
  }
  return name;
}

function isSameCalendarDay(a: Date, b: Date): boolean {
  return a.getFullYear() === b.getFullYear() &&
    a.getMonth() === b.getMonth() &&
    a.getDate() === b.getDate();
}

/** Friendly relative time: "just now", "12 min ago", "3 h ago", "2 d ago". */
export function relativeTime(iso: string, now: number = Date.now()): string {
  const then = new Date(iso).getTime();
  if (Number.isNaN(then)) return '';
  const diff = now - then;
  if (diff < MINUTE) return 'just now';
  if (diff < HOUR) return `${Math.floor(diff / MINUTE)} min ago`;
  if (diff < DAY) return `${Math.floor(diff / HOUR)} h ago`;
  if (diff < 7 * DAY) return `${Math.floor(diff / DAY)} d ago`;
  return formatDate(iso);
}

/** Swiss-style date: 24.12.2025 */
export function formatDate(iso: string): string {
  const d = new Date(iso);
  if (Number.isNaN(d.getTime())) return '';
  const pad = (x: number) => String(x).padStart(2, '0');
  return `${pad(d.getDate())}.${pad(d.getMonth() + 1)}.${d.getFullYear()}`;
}

/** Swiss-style date and time: 24.12.2025, 14:05 */
export function formatDateTime(iso: string): string {
  const d = new Date(iso);
  if (Number.isNaN(d.getTime())) return '';
  const pad = (x: number) => String(x).padStart(2, '0');
  return `${formatDate(iso)}, ${pad(d.getHours())}:${pad(d.getMinutes())}`;
}

/** Friendly expected-delivery date: "yesterday", "today", "tomorrow", or 24.12.2025. */
export function formatExpectedDelivery(
  value: string,
  now: number = Date.now(),
): string {
  const deliveryWindow = /^(\d{4}-\d{2}-\d{2})[ T]+(\d{2}:\d{2})(?:[–-](\d{2}:\d{2}))?$/.exec(
    value.trim(),
  );
  if (deliveryWindow) {
    const day = formatExpectedDelivery(deliveryWindow[1], now);
    const window = deliveryWindow[3]
      ? `${deliveryWindow[2]}–${deliveryWindow[3]}`
      : deliveryWindow[2];
    return `${day}, ${window}`;
  }

  const expected = parseDeliveryDate(value);
  if (!expected) return value;

  const today = new Date(now);
  if (isSameCalendarDay(expected, today)) return 'today';

  const yesterday = new Date(today.getFullYear(), today.getMonth(), today.getDate() - 1);
  if (isSameCalendarDay(expected, yesterday)) return 'yesterday';

  const tomorrow = new Date(
    today.getFullYear(),
    today.getMonth(),
    today.getDate() + 1,
  );
  if (isSameCalendarDay(expected, tomorrow)) return 'tomorrow';

  const pad = (x: number) => String(x).padStart(2, '0');
  return `${pad(expected.getDate())}.${pad(expected.getMonth() + 1)}.${expected.getFullYear()}`;
}

/**
 * Compact calendar dates with explicit weekdays and months: "Sat 12 Sep", "Sa 12. Sept", "sam 12 sept".
 * Names keep the language's own case, since the date often sits mid-sentence ("Livré le sam 12 sept"),
 * and the day keeps the period German writes after it. Chinese writes its own: "9月12日周六".
 */
export function localizedCalendarDate(date: Date, languageTag: string, withYear = false): string {
  const language = new Intl.Locale(languageTag).language;
  const options: Intl.DateTimeFormatOptions = { weekday: 'short', day: 'numeric', month: 'short' };
  if (language === 'zh') return new Intl.DateTimeFormat(language, { ...options, ...(withYear && { year: 'numeric' }) }).format(date);
  const parts = new Intl.DateTimeFormat(language, options).formatToParts(date);
  const index = (type: Intl.DateTimeFormatPartTypes) => parts.findIndex((entry) => entry.type === type);
  const part = (type: Intl.DateTimeFormatPartTypes) => parts[index(type)]!.value.replaceAll('.', '');
  const dayPeriod = parts[index('day') + 1]?.value.startsWith('.') ? '.' : '';
  return `${part('weekday')} ${part('day')}${dayPeriod} ${part('month')}${withYear ? ` ${date.getFullYear()}` : ''}`;
}

/** A day and a time on it: "tomorrow, 09:00–12:00". Chinese runs them together with a space: "明天 09:00–12:00". */
export function dayAndTime(day: string, time: string, languageTag: string): string {
  return new Intl.Locale(languageTag).language === 'zh' ? `${day} ${time}` : `${day}, ${time}`;
}

export function localizedClockTime(date: Date, languageTag: string, use24HourTime = true, timeZone?: string): string {
  return new Intl.DateTimeFormat(languageTag, {
    hour: use24HourTime ? '2-digit' : 'numeric',
    minute: '2-digit',
    hourCycle: use24HourTime ? 'h23' : 'h12',
    timeZone,
  }).format(date);
}

export function localizedCalendarDateTime(date: Date, languageTag: string, use24HourTime = true): string {
  return dayAndTime(localizedCalendarDate(date, languageTag), localizedClockTime(date, languageTag, use24HourTime), languageTag);
}
