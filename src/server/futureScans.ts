import { languageStageStatus, type CarrierEvent, type CarrierResult, type Stage } from 'universal-parcel-scraper';
import { eventTimestamp, resultTimezone } from 'universal-parcel-scraper/app';

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
 * A result whose summary a scan dated over an hour after the check gave (a
 * sender's notice on its own clock under the carrier's offset): its status
 * line, time and stage come from its newest scan before then. The scan itself
 * is still stored and flagged, but it can't become the parcel's latest status.
 */
export function withoutFutureSummary(result: CarrierResult, carrierId: string, now: Date): CarrierResult {
  const timezone = resultTimezone(carrierId, result);
  const events = result.events ?? [];
  const ahead = events.filter((event) => datedAfterCheck(event.time, timezone, now));
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

/** Whether the result's stage is the one a future scan gave it. */
function aheadStage(result: CarrierResult, ahead: readonly CarrierEvent[]): boolean {
  return Boolean(result.current_stage) && ahead.some((event) => event.stage === result.current_stage);
}
