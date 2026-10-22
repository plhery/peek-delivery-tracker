import { CARRIER_DEFINITIONS } from 'universal-parcel-scraper';
import { DateTime } from 'luxon';
import type { CarrierResult } from 'universal-parcel-scraper';
import { showsClock } from './eventIdentity';
import { isRecord, type JsonObject } from './types';

const CARRIERS = new Set(Object.entries(CARRIER_DEFINITIONS).filter(([, carrier]) => carrier.tracking.localClocks).map(([id]) => id));
const eventKey = (event: JsonObject): string => JSON.stringify([
  event.local_time, event.provider_time_text, event.time, event.description, event.location, event.provider_code, event.provider_leg, event.summary_snapshot,
]);

function instant(value: unknown): boolean {
  return typeof value === 'string' && /(?:Z|[+-]\d{2}:?\d{2})$/i.test(value)
    && DateTime.fromISO(value, { setZone: true }).isValid;
}

const scanWords = (event: JsonObject): string => JSON.stringify([event.description, event.location, event.provider_code, event.provider_leg]);

/**
 * Leaves out a scan kept on its wall clock alone (`local_time`) once a later
 * answer dates it: the same wording, place, provider code and leg, at an
 * instant showing that wall clock. Keyed by their times, the two would stay
 * side by side. One to one: a copy two dated scans could be, or a dated scan
 * two copies could be, stays.
 */
function withoutDatedCopies(events: readonly JsonObject[]): JsonObject[] {
  const dated = events.filter((event) => instant(event.time));
  const candidates = new Map<JsonObject, JsonObject[]>();
  for (const event of events) {
    if (instant(event.time) || typeof event.local_time !== 'string' || event.local_time === '') continue;
    const local = event.local_time;
    candidates.set(event, dated.filter((scan) => scanWords(scan) === scanWords(event) && showsClock(local, Date.parse(String(scan.time)))));
  }
  const claims = new Map<JsonObject, number>();
  for (const scans of candidates.values()) for (const scan of scans) claims.set(scan, (claims.get(scan) ?? 0) + 1);
  return events.filter((event) => {
    const scans = candidates.get(event);
    return scans?.length !== 1 || claims.get(scans[0]!) !== 1;
  });
}

/** Whether the catalog reads this carrier's own answers on local clocks. */
export function localClockCarrier(carrier: string): boolean {
  return CARRIERS.has(carrier);
}

/** These direct sources preserve incomplete dates separately from instants. */
export function hasUnresolvedDirectHistory(carrier: string, result: CarrierResult): boolean {
  return CARRIERS.has(carrier) && Boolean(result.events?.some((event) => !instant(event.time)));
}

export function hasUnresolvedDirectCurrent(carrier: string, result: CarrierResult): boolean {
  return CARRIERS.has(carrier) && (result.summary_only === true || hasUnresolvedDirectHistory(carrier, result))
    && !instant(result.last_update);
}

/**
 * A bounded copy of the carrier's scans. A code comes with the carrier's group for it (SEUR's `provider_group`), so a
 * code seen only here can still be mapped. The group is no part of a scan's key: the copy kept before it was stored
 * gives way to the next answer's.
 */
export function captureDirectLocalHistory(carrier: string, number: string, result: CarrierResult): JsonObject {
  const events = (result.events ?? []).slice(0, 100).map((event) => Object.fromEntries(
    ['time', 'local_time', 'provider_time_text', 'description', 'location', 'stage', 'provider_code', 'provider_group', 'provider_leg', 'summary_snapshot'].flatMap((key): Array<[string, string | true]> => {
      const value = event[key];
      if (key === 'summary_snapshot' && value === true) return [[key, true]];
      return typeof value === 'string' ? [[key, value.slice(0, key === 'description' ? 500 : 200)]] : [];
    }),
  ));
  return { carrier, number, events,
    ...(typeof result.last_update_local === 'string' ? { last_update_local: result.last_update_local.slice(0, 64) } : {}) };
}

export function directHistoryNumber(parcel: JsonObject, result: CarrierResult = {}): unknown {
  const data = isRecord(parcel.carrier_data) ? parcel.carrier_data : {};
  if (result.original_carrier && result.active_tracking_carrier && typeof result.active_tracking_number === 'string') {
    return result.active_tracking_number;
  }
  return data.original_carrier && data.active_tracking_carrier && typeof data.active_tracking_number === 'string'
    ? data.active_tracking_number : parcel.tracking_number;
}

/** Recognize a shortened old snapshot without comparing clocks across countries. */
export function directLocalSnapshotIsOlder(previous: unknown, incoming: unknown): boolean {
  if (!isRecord(previous) || !Array.isArray(previous.events)
    || !isRecord(incoming) || !Array.isArray(incoming.events) || !isRecord(incoming.events[0])) return false;
  const key = eventKey(incoming.events[0]);
  return previous.events.findIndex((event) => isRecord(event) && eventKey(event) === key) > 0;
}

/**
 * Keep bounded local evidence across provider changes without creating scan
 * instants. A scan an earlier answer kept undated goes once another dates it.
 */
export function directLocalHistory(parcel: JsonObject, result: CarrierResult): JsonObject | undefined {
  const data = isRecord(parcel.carrier_data) ? parcel.carrier_data : {};
  const number = directHistoryNumber(parcel, result);
  const valid = (value: unknown): value is JsonObject => isRecord(value)
    && CARRIERS.has(String(value.carrier)) && value.number === number && Array.isArray(value.events);
  const previous = valid(data.direct_local_history) ? data.direct_local_history : undefined;
  const incoming = valid(result.direct_local_history) ? result.direct_local_history : undefined;
  const bounded = (archive: JsonObject, events: unknown[]) => ({
    ...archive, events: withoutDatedCopies(events.filter(isRecord)).slice(0, 100),
  });
  if (!incoming) return previous ? bounded(previous, previous.events as unknown[]) : undefined;
  if (!previous || previous.carrier !== incoming.carrier) return bounded(incoming, incoming.events as unknown[]);
  const unique = new Map<string, JsonObject>();
  const older = (incoming.events as unknown[]).length === 0 || directLocalSnapshotIsOlder(previous, incoming);
  const ordered = older ? [previous, incoming] : [incoming, previous];
  // The provider's order is meaningful across locations with different clocks.
  for (const event of ordered.flatMap((archive) => archive.events as unknown[])) {
    if (!isRecord(event)) continue;
    const key = eventKey(event);
    if (!unique.has(key)) unique.set(key, event);
  }
  return bounded(older ? previous : incoming, [...unique.values()]);
}
