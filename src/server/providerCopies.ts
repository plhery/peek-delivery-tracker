/**
 * Universal copies a parcel's timeline leaves out once its own carrier answers.
 *
 * A universal provider copies a carrier's scans, sometimes reworded, a zone
 * away or a few minutes off. The sync stores one row of a scan when it can
 * tell the copy from the carrier's scan (eventIdentity.ts); the copies it
 * cannot tell apart stay stored. Once a direct carrier is confirmed for the
 * parcel, its timeline leaves out such a copy: a row only a universal provider
 * wrote, at the minute of one of the carrier's scans, in that scan's stage
 * within 15 minutes or a whole number of hours up to 14 h away, or within an
 * hour in the scan's words, alone or followed by the copy's place (ParcelsApp
 * dates a USPS departure at the time USPS first gave it, before USPS moved the
 * departure on), or in any stage
 * and words whole hours away to the second when that second is not a minute's
 * first (ParcelsApp reads ECMS's Japanese scans an hour off). A copy only
 * whole hours from the carrier's scans shows the provider read them a zone
 * away, so its provider's other rows of that minute are copies too, in any
 * stage (Swiss Post dates a London office's scans on Swiss time, and
 * ParcelsApp tells the departure twice). So is the provider's twin of a copy,
 * in its words, place and stage whole hours from it (ParcelsApp tells a
 * Chilean posting on the right clock and again on Chile's wall clock read as
 * UTC), and its row whole hours from a carrier's scan, in any stage and at
 * any place, by the shift another of its copies shows (17TRACK's export-unit
 * scan, read as information received, beside its departure three hours early;
 * ParcelsApp's airport scans by the shift of its copy at a delivery depot);
 * the provider's other rows of their minute are copies too. A carrier's
 * scan dated by its day, on a wall clock in no zone (YunExpress's), has no
 * instant to compare: a copy whose time shows that wall clock whole quarter
 * hours away up to 14 h, in its words and stage (or none), or at its second
 * when that is not a minute's first, is left out, and so is a copy at the wall
 * clock read as UTC, to the second, in its stage (ParcelsApp's reworded
 * MailAmericas scans). A provider's step the carrier never shows, such as
 * another leg's scans, stays.
 * Push leaves the copies unannounced too (push.ts), and the Live Activity
 * queue tells them with the same rules in SQL (`public.hidden_provider_copy`):
 * change both together.
 *
 * A row a carrier's scan took over keeps its universal identity and loses the
 * provider's name, and rows stored before rows named their provider have none
 * either: the two only differ by whether a carrier's answer holds them. Each
 * check that stores a carrier's dated scans keeps the universal rows they hold
 * and the time they cover (`direct_history`), and an unnamed universal row in
 * that time that no answer held counts as the provider's.
 */
import { DateTime } from 'luxon';
import { clockDay } from './eventIdentity';
import { isRecord, type JsonObject } from './types';

/** The `carrier_data` key of the universal rows carrier answers held and the time those answers covered. */
export const DIRECT_HISTORY = 'direct_history';

const UNIVERSAL = 'unknown';
const SECOND_MS = 1_000;
const MINUTE_MS = 60_000;
const NEAR_MS = 15 * MINUTE_MS;
const QUARTER_HOUR_MS = 15 * MINUTE_MS;
const HOUR_MS = 60 * MINUTE_MS;
const MAX_SHIFT_MS = 14 * HOUR_MS;
const MAX_HELD = 500;

function identity(event: JsonObject): string {
  return typeof event.provider_event_id === 'string' ? event.provider_event_id : '';
}

/** Who stored a scan: the carrier its identity starts with (`unknown` for a universal provider, `app` for Peek's own rows). */
function source(event: JsonObject): string {
  const id = identity(event);
  const separator = id.indexOf(':');
  return separator > 0 ? id.slice(0, separator) : '';
}

function instant(event: JsonObject): number {
  return Date.parse(String(event.occurred_at ?? ''));
}

function observed(event: JsonObject): boolean {
  return event.observed === true || event.observed_without_provider_timestamp === true
    || (isRecord(event.raw_data) && event.raw_data.observed_without_provider_timestamp === true);
}

function providerName(event: JsonObject): string {
  const name = event.universal_provider ?? (isRecord(event.raw_data) ? event.raw_data.universal_provider : undefined);
  return typeof name === 'string' ? name : '';
}

function named(event: JsonObject): boolean {
  return providerName(event) !== '';
}

function storedHistory(data: unknown): { from: number; to: number; held: Set<string> } | null {
  const history = isRecord(data) ? data : null;
  const from = Date.parse(String(history?.from ?? ''));
  const to = Date.parse(String(history?.to ?? ''));
  if (!history || !Number.isFinite(from) || !Number.isFinite(to)) return null;
  return { from, to, held: new Set(Array.isArray(history.held) ? history.held.filter((id): id is string => typeof id === 'string') : []) };
}

/**
 * The `direct_history` a check leaves: the time its carriers' dated scans
 * cover and the universal rows they hold, with those of the parcel's earlier
 * answers whose rows are still stored. `scans` are the check's scans as they
 * were built and `persisted` the same scans with the identities they are
 * stored under. Null when the check stores no carrier's dated scan.
 */
export function directHistory(
  scans: readonly JsonObject[], persisted: readonly JsonObject[], previous: unknown, stored: readonly JsonObject[],
): JsonObject | null {
  const own = scans.flatMap((scan, index) => {
    const at = instant(scan);
    return ['', 'app', UNIVERSAL].includes(source(scan)) || observed(scan) || !Number.isFinite(at) ? [] : [{ at, id: identity(persisted[index] ?? scan) }];
  });
  if (!own.length) return null;
  const earlier = storedHistory(previous);
  const kept = new Set(stored.map(identity));
  const times = [...own.map((scan) => scan.at), ...(earlier ? [earlier.from, earlier.to] : [])];
  const held = new Set([...[...earlier?.held ?? []].filter((id) => kept.has(id)),
    ...own.map((scan) => scan.id).filter((id) => id.startsWith(`${UNIVERSAL}:`))]);
  return {
    from: new Date(Math.min(...times)).toISOString(),
    to: new Date(Math.max(...times)).toISOString(),
    held: [...held].slice(-MAX_HELD),
  };
}

function wording(event: JsonObject): string {
  return String(event.description ?? '').replace(/\s+/g, ' ').trim().toLocaleLowerCase('en-US');
}

/** The wall clock of a scan dated by its day (`clockDay`), read as if in UTC; NaN for any other scan. */
function wallClock(event: JsonObject): number {
  return clockDay(event) ? DateTime.fromISO(String(event.local_time), { zone: 'utc' }).toMillis() : Number.NaN;
}

/** A time to its second. */
function second(at: number): number {
  return Math.floor(at / SECOND_MS) * SECOND_MS;
}

/** Stages that can name one scan: the same, or the copy's provider left it unclassified. */
function stagesAgree(copy: JsonObject, scan: JsonObject): boolean {
  return copy.stage === scan.stage || ['', 'pending'].includes(String(copy.stage ?? ''));
}

/**
 * Whether `copy` tells `scan` in its words, alone or followed by the copy's place, or in any words when the scan
 * announces the parcel: a provider tells that notice in its own language (17TRACK's English for Swiss Post's French).
 */
function inScanWords(copy: JsonObject, scan: JsonObject): boolean {
  const words = wording(scan);
  return scan.stage === 'registered' || (words !== '' && [words, `${words}, ${place(copy)}`].includes(wording(copy)));
}

/**
 * Whether `copy` repeats the carrier's `scan`: at its minute, or in its stage close to it, within an
 * hour in its words (any words for the parcel's announcement) or whole hours from it, or whole hours from it to the second when that second is not a minute's first (a
 * provider reads the carrier's seconds on another clock, in its own words and stage). A scan dated
 * by its day is repeated at its wall clock in some zone: in its stage and words, or at that second
 * when it is not a minute's first, or at the wall clock itself, read as UTC, to the second, in its stage.
 */
function copies(copy: JsonObject, scan: JsonObject): boolean {
  const clock = wallClock(scan);
  if (Number.isFinite(clock)) {
    const shift = Math.floor(instant(copy) / MINUTE_MS) * MINUTE_MS - Math.floor(clock / MINUTE_MS) * MINUTE_MS;
    const seconds = second(instant(copy)) - clock;
    return (stagesAgree(copy, scan) && wording(copy) === wording(scan)
      && shift % QUARTER_HOUR_MS === 0 && Math.abs(shift) <= MAX_SHIFT_MS)
      || (clock % MINUTE_MS !== 0 && seconds % QUARTER_HOUR_MS === 0 && Math.abs(seconds) <= MAX_SHIFT_MS)
      || (seconds === 0 && copy.stage === scan.stage);
  }
  const minutes = Math.abs(Math.floor(instant(copy) / MINUTE_MS) - Math.floor(instant(scan) / MINUTE_MS)) * MINUTE_MS;
  if (minutes === 0) return true;
  if (second(instant(scan)) % MINUTE_MS !== 0 && wholeHours(second(instant(copy)) - second(instant(scan)))) return true;
  const apart = Math.abs(instant(copy) - instant(scan));
  return copy.stage === scan.stage
    && (apart <= NEAR_MS || (apart <= HOUR_MS && inScanWords(copy, scan)) || (minutes % HOUR_MS === 0 && minutes <= MAX_SHIFT_MS));
}

/** The minutes from `scan` to `row`, each read to the minute, in milliseconds. */
function minutesApart(row: JsonObject, scan: JsonObject): number {
  return (Math.floor(instant(row) / MINUTE_MS) - Math.floor(instant(scan) / MINUTE_MS)) * MINUTE_MS;
}

/** Whether a shift is a whole number of hours, up to 14 h, and not none. */
function wholeHours(shift: number): boolean {
  return shift !== 0 && shift % HOUR_MS === 0 && Math.abs(shift) <= MAX_SHIFT_MS;
}

/** Whether `copy` repeats the carrier's `scan` whole hours from it, and so on a clock a zone away. */
function hoursAway(copy: JsonObject, scan: JsonObject): boolean {
  return !Number.isFinite(wallClock(scan)) && copies(copy, scan) && wholeHours(minutesApart(copy, scan));
}

function place(event: JsonObject): string {
  return String(event.location ?? '').replace(/\s+/g, ' ').trim().toLocaleLowerCase('en-US');
}

/**
 * The stored rows of `row` its timeline leaves out as a universal provider's copies of its carrier's scans, each with
 * the ids of the carrier's scans it repeats.
 */
export function providerCopyScans(row: JsonObject): Map<string, Set<string>> {
  const data = isRecord(row.carrier_data) ? row.carrier_data : {};
  const routing = isRecord(data.routing) ? data.routing : {};
  const copied = new Map<string, Set<string>>();
  if (typeof routing.confirmed_carrier !== 'string' || routing.confirmed_carrier === UNIVERSAL
    || !Array.isArray(row.tracking_events)) return copied;
  const history = storedHistory(data[DIRECT_HISTORY]);
  const scans = row.tracking_events.filter(isRecord).filter((event) => !observed(event) && Number.isFinite(instant(event)));
  const universal = (event: JsonObject) => source(event) === UNIVERSAL && !history?.held.has(identity(event));
  const own = scans.filter((event) => !['', 'app'].includes(source(event)) && !universal(event));
  const providerOnly = (event: JsonObject) => universal(event)
    && (named(event) || (history !== null && history.from <= instant(event) && instant(event) <= history.to));
  const minute = (event: JsonObject) => `${providerName(event)}@${Math.floor(instant(event) / MINUTE_MS)}`;
  const ids = (list: readonly JsonObject[]) => new Set(list.map((scan) => String(scan.id)));
  const repeats = scans.filter((event) => providerOnly(event) && own.some((scan) => copies(event, scan)));
  // A copy that only repeats scans whole hours away shows its provider read them a zone away.
  const zoned = repeats.filter((event) => own.every((scan) => !copies(event, scan) || hoursAway(event, scan)));
  // That provider reads the carrier's other scans on the same clock: its row whole hours from a carrier's scan, by the
  // shift of such a copy, lands on that scan's minute once read on the carrier's clock. A provider can read each place
  // on its own clock, but a row of another step falls at the minute of that shift from one of the carrier's scans
  // too seldom to tell it by its place.
  const clock = (event: JsonObject, shift: number) => `${providerName(event)}@${shift}`;
  const shifts = new Set(zoned
    .flatMap((event) => own.filter((scan) => hoursAway(event, scan)).map((scan) => clock(event, minutesApart(event, scan)))));
  const twin = (event: JsonObject, copy: JsonObject) => providerName(event) === providerName(copy) && wording(event) === wording(copy)
    && place(event) === place(copy) && event.stage === copy.stage && wholeHours(minutesApart(event, copy));
  for (const event of repeats) copied.set(String(event.id), ids(own.filter((scan) => copies(event, scan))));
  for (const event of scans) {
    if (!providerOnly(event) || copied.has(String(event.id))) continue;
    // The provider's twin of a copy, in its words, place and stage whole hours away, is the same scan on another clock.
    const twins = repeats.filter((copy) => twin(event, copy));
    const shifted = own.filter((scan) => !Number.isFinite(wallClock(scan)) && shifts.has(clock(event, minutesApart(event, scan))));
    if (twins.length || shifted.length) {
      copied.set(String(event.id), new Set([...twins.flatMap((copy) => [...copied.get(String(copy.id))!]), ...ids(shifted)]));
      zoned.push(event);
    }
  }
  // A provider that reads the carrier's scans a zone away tells every scan of that minute on the same clock: its
  // rows at the minute of a copy that only repeats scans whole hours away repeat that scan too, whatever their stage.
  const shifted = new Map<string, Set<string>>();
  for (const event of zoned) {
    shifted.set(minute(event), new Set([...shifted.get(minute(event)) ?? [], ...copied.get(String(event.id))!]));
  }
  for (const event of scans) {
    const repeated = shifted.get(minute(event));
    if (providerOnly(event) && repeated && !copied.has(String(event.id))) copied.set(String(event.id), repeated);
  }
  return copied;
}

/** The ids of the stored rows of `row` its timeline leaves out as a universal provider's copies of its carrier's scans. */
export function providerCopies(row: JsonObject): Set<string> {
  return new Set(providerCopyScans(row).keys());
}
