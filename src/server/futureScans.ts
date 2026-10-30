import { languageStageStatus, type CarrierEvent, type CarrierResult, type Stage } from 'universal-parcel-scraper';
import { eventTimestamp, resultTimezone } from 'universal-parcel-scraper/app';
import { isRecord, type JsonObject } from './types';

/**
 * How far after a check a scan may be dated before its clock counts as read in
 * the wrong zone (`future_event_timestamp`). A lead under an hour is a clock
 * running a little fast.
 */
export const FUTURE_SCAN_MS = 60 * 60 * 1_000;

/** Whether a scan's time, read in `timezone`, is over an hour after `now`. */
export function datedAfterCheck(time: unknown, timezone: string, now: Date): boolean {
  const at = Date.parse(eventTimestamp(time, timezone) ?? '');
  return Number.isFinite(at) && at > now.getTime() + FUTURE_SCAN_MS;
}

/**
 * A result whose summary a scan dated over an hour after its check gave (a
 * sender's notice on its own clock under the carrier's offset): its status
 * line, time and stage come from its newest scan before then. The scan itself
 * is still stored and flagged, but it can't become the parcel's latest status,
 * then or once its time has passed: a stored row (`stored`, with `time`,
 * `description`, `occurred_at` and `created_at`) dated over an hour after it
 * was first stored keeps it out.
 */
export function withoutFutureSummary(result: CarrierResult, carrierId: string, now: Date,
  stored: readonly JsonObject[] = []): CarrierResult {
  const timezone = resultTimezone(carrierId, result);
  const events = result.events ?? [];
  const storedAhead = storedAheadOfCheck(stored);
  const ahead = events.filter((event) => datedAfterCheck(event.time, timezone, now)
    || storedAhead.has(scanKey(event.time, event.description)));
  if (ahead.length === 0) return result;
  const summaryAhead = datedAfterCheck(result.last_update, timezone, now)
    || ahead.some((event) => event.description === result.last_status_text);
  if (!summaryAhead) return result;
  const newest = events.filter((event) => !ahead.includes(event) && Number.isFinite(Date.parse(eventTimestamp(event.time, timezone) ?? '')))
    .sort((left, right) => Date.parse(eventTimestamp(right.time, timezone)!) - Date.parse(eventTimestamp(left.time, timezone)!))[0];
  if (!newest) return result;
  const stage = aheadStage(result, ahead) && newest.stage ? newest.stage as Stage : null;
  return {
    ...result,
    last_status_text: newest.description ?? result.last_status_text,
    last_update: newest.time ?? result.last_update,
    ...(stage ? { current_stage: stage, status: languageStageStatus(stage) } : {}),
  };
}

/**
 * A routing watermark (the newest scan's time) that a scan dated over an hour after its check set: the
 * router caps such a scan at the check, and a row stored so stands at its own time once that has passed.
 * Answers summarised by the newest scan before it (`withoutFutureSummary`) would read as older, and the
 * parcel would keep the status line it gave. The watermark comes back to the newest other scan, of the
 * answer (`events`, as normalized) or stored, when it is past that and no later than the check or the
 * newest scan dated ahead. Peek's own rows and sightings without a scan time are no scans.
 */
export function withoutAheadWatermark(time: number, events: readonly JsonObject[], stored: readonly JsonObject[], now: Date): number {
  if (!Number.isFinite(time)) return time;
  const storedAhead = storedAheadOfCheck(stored);
  const raw = (row: JsonObject) => isRecord(row.raw_data) ? row.raw_data : row;
  const scans = [...events, ...stored].filter((row) => !String(row.provider_event_id ?? '').startsWith('app:')
    && raw(row).observed_without_provider_timestamp !== true);
  const ahead = (row: JsonObject) => datedAfterCheck(row.occurred_at, 'UTC', now) || storedAhead.has(scanKey(raw(row).time, row.description));
  const instants = (rows: readonly JsonObject[]) => rows.map((row) => Date.parse(String(row.occurred_at ?? ''))).filter(Number.isFinite);
  const aheadOf = instants(scans.filter(ahead));
  const others = instants(scans.filter((row) => !ahead(row)));
  if (aheadOf.length === 0 || others.length === 0) return time;
  const newest = Math.max(...others);
  return time > newest && time <= Math.max(now.getTime(), ...aheadOf) ? newest : time;
}

/** The stored rows dated over an hour after they were first stored, by `scanKey`. */
export function storedAheadOfCheck(stored: readonly JsonObject[]): Set<string> {
  return new Set(stored.filter((row) => datedAfterCheck(row.occurred_at, 'UTC', new Date(String(row.created_at))))
    .map((row) => scanKey(row.time, row.description)));
}

/** A scan as its carrier gave it: its raw time and wording. */
export function scanKey(time: unknown, description: unknown): string {
  return JSON.stringify([time ?? null, description ?? null]);
}

/** Whether the result's stage is the one a future scan gave it. */
function aheadStage(result: CarrierResult, ahead: readonly CarrierEvent[]): boolean {
  return Boolean(result.current_stage) && ahead.some((event) => event.stage === result.current_stage);
}
