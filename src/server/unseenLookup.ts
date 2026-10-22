import 'server-only';
import { routingState } from './trackingRouting';
import { isRecord, type JsonObject } from './types';

/**
 * A number nobody has seen yet is checked hourly for this long after it was
 * added, then every six hours. A one-off lookup then leaves the schedule.
 */
export const UNSEEN_HOURLY_MS = 6 * 60 * 60 * 1_000;

const DAY_MS = 24 * 60 * 60 * 1_000;

/** After this long unseen, a number is checked daily. */
const UNSEEN_DAILY_AFTER_MS = 48 * 60 * 60 * 1_000;

/**
 * After this long unseen, the checks nobody asked for, the schedule's and a scraper
 * release's, come weekly: hardly any number gets its first answer this late.
 */
const UNSEEN_WEEKLY_AFTER_MS = 7 * 24 * 60 * 60 * 1_000;

/**
 * How many hours apart a number nobody has seen for `unseen` milliseconds is checked:
 * hourly, then every 6 h, daily after 48 h and weekly after 7 days. `opened`: one of
 * its links was just opened, and someone looking gets a daily check rather than a weekly one.
 */
export function unseenIntervalHours(unseen: number, opened = false): number {
  if (unseen < UNSEEN_HOURLY_MS) return 1;
  if (unseen < UNSEEN_DAILY_AFTER_MS) return 6;
  return opened || unseen < UNSEEN_WEEKLY_AFTER_MS ? 24 : 7 * 24;
}

/**
 * How long ago a parcel was added when no carrier or provider has answered
 * for its number yet: no carrier identified or recognised, no history and
 * nothing asked of the user. Null once one has.
 */
export function unseenFor(parcel: JsonObject, now: Date): number | null {
  if (!['unknown', 'intl-post'].includes(String(parcel.carrier)) || String(parcel.current_stage ?? 'pending') !== 'pending') return null;
  const data = isRecord(parcel.carrier_data) ? parcel.carrier_data : {};
  const routing = routingState(parcel);
  if (data.original_carrier || data.universal_input || routing.last_success_at || routing.last_event_at
    || routing.confirmed_carrier || routing.discovered_carrier || routing.input_needed || routing.provider_input_needed) return null;
  const added = Date.parse(String(parcel.created_at ?? ''));
  return Number.isFinite(added) ? Math.max(0, now.getTime() - added) : 0;
}

/**
 * With `TRACKING_ENABLE_UNSEEN_RECHECKS=true`, an account's parcel whose number nobody
 * has answered for a day is checked again once each this long after it was added, by
 * day, rather than every 6 hours, daily and then weekly with every provider.
 */
const UNSEEN_RECHECKS_MS = [1, 3, 7].map((days) => days * DAY_MS);

/** From this long after it was added, such a parcel leaves the schedule. */
const UNSEEN_RECHECKS_END_MS = 14 * DAY_MS;

/**
 * When a check nobody asked for is due for an account's parcel whose number nobody
 * has answered for a day, as epoch milliseconds: 1, 3 or 7 days after it was added,
 * the latest of these that has passed. Null from 14 days, when no such check is due
 * any more. Undefined when the rechecks don't apply: they are off, the parcel is a
 * lookup without an account, someone has answered for its number, or it is newer.
 */
export function unseenRecheckDue(parcel: JsonObject, now: Date): number | null | undefined {
  if (process.env.TRACKING_ENABLE_UNSEEN_RECHECKS !== 'true') return undefined;
  // Lookups without an account have no owner (one_off), and every account's parcel has one.
  const owned = parcel.one_off === false || (parcel.one_off == null && typeof parcel.user_id === 'string');
  const unseen = owned ? unseenFor(parcel, now) : null;
  if (unseen === null || unseen < UNSEEN_RECHECKS_MS[0]) return undefined;
  if (unseen >= UNSEEN_RECHECKS_END_MS) return null;
  const since = UNSEEN_RECHECKS_MS.filter((after) => unseen >= after).at(-1)!;
  return now.getTime() - unseen + since;
}

/**
 * A one-off lookup that left the schedule before any carrier or provider answered for
 * its number. Only opening its link, or a scraper release that teaches a carrier to recognize
 * its number, checks it again.
 */
export function leftScheduleUnseen(parcel: JsonObject, now: Date): boolean {
  const unseen = parcel.one_off === true ? unseenFor(parcel, now) : null;
  return unseen !== null && unseen >= UNSEEN_HOURLY_MS;
}

/**
 * With `TRACKING_ENABLE_SPARSE_LOOKUP_RECHECKS=true`, a check nobody asked for of such a
 * lookup, a scraper release's re-check, contacts one universal provider at most, as an
 * account's parcel's recheck does.
 */
export function sparseLookupRecheck(parcel: JsonObject, now: Date): boolean {
  return process.env.TRACKING_ENABLE_SPARSE_LOOKUP_RECHECKS === 'true' && leftScheduleUnseen(parcel, now);
}
