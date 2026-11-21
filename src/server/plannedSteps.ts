import { DateTime } from 'luxon';
import { CARRIER_DEFINITIONS, carrierTimezone, type CarrierId } from 'universal-parcel-scraper';
import { eventTimestamp } from 'universal-parcel-scraper/app';
import { EVENT_STAGE_ORDER } from '../lib/stages';
import { clockDay, scanDay } from './eventIdentity';
import { isRecord, type JsonObject } from './types';

/**
 * A step the carrier lists before it happens: a row written ahead of its own clock (a tenant's script of the
 * whole journey, written when the order is made) or a scan entered at its planned time and not due yet. The
 * scraper holds such rows out of a result's scans and names them beside them (`planned_events`, or an error's
 * `plannedEvents` when nothing else remains), as its scans are shaped. A row written ahead stays listed once its own
 * clock has passed, as long as the carrier keeps it, and so does its clock among those held back (`written_ahead`).
 */
export interface PlannedStep {
  time?: string;
  local_time?: string;
  description: string;
  location?: string;
  stage?: string;
}

/** Each carrier keeps this many upcoming steps and this many elapsed steps. */
export const PLANNED_STEPS_KEPT = 30;
const TEXT_LENGTH = 300;

/** The planned steps a carrier's answer, or its failure, names (`planned_events`, `plannedEvents`, through causes). */
export function plannedStepsOf(value: unknown, zone = 'UTC', now = Date.now()): PlannedStep[] {
  const seen = new Set<unknown>();
  let current = value;
  while (isRecord(current) && !seen.has(current)) {
    seen.add(current);
    const listed = current.planned_events ?? current.plannedEvents;
    if (Array.isArray(listed)) return plannedSteps(listed, zone, now);
    current = current.cause;
  }
  return [];
}

/** Keep the soonest upcoming steps and the most recent elapsed steps within separate limits. */
export function plannedSteps(listed: readonly unknown[], zone = 'UTC', now = Date.now()): PlannedStep[] {
  const text = (value: unknown) => typeof value === 'string' ? value.replace(/\s+/g, ' ').trim().slice(0, TEXT_LENGTH) : '';
  const seen = new Set<string>();
  const steps = listed.flatMap((entry): PlannedStep[] => {
    if (!isRecord(entry)) return [];
    const description = text(entry.description);
    const time = text(entry.time);
    const local = text(entry.local_time);
    if (!description || (!time && !local)) return [];
    const key = JSON.stringify([time, local, description]);
    if (seen.has(key)) return [];
    seen.add(key);
    const location = text(entry.location);
    const stage = typeof entry.stage === 'string' && EVENT_STAGE_ORDER.includes(entry.stage) ? entry.stage : '';
    return [{ ...(time ? { time } : {}), ...(local ? { local_time: local } : {}), description,
      ...(location ? { location } : {}), ...(stage ? { stage } : {}) }];
  });
  if (steps.length <= PLANNED_STEPS_KEPT) return steps;
  const dated = steps.map(step => ({ step, until: stepTime(step, zone)?.until ?? NaN }))
    .filter(entry => Number.isFinite(entry.until)).sort((a, b) => a.until - b.until);
  return [...dated.filter(entry => entry.until <= now).slice(-PLANNED_STEPS_KEPT),
    ...dated.filter(entry => entry.until > now).slice(0, PLANNED_STEPS_KEPT)].map(entry => entry.step);
}

/** Stages a parcel ends its journey in: no plan is left to show. */
const FINAL_STAGES = new Set(['delivered', 'returned']);
/** The stages that tell how far a parcel has come, in order. A notice, a problem or customs say where it is, not how far. */
const PROGRESS = ['registered', 'accepted', 'in_transit', 'out_for_delivery', 'delivered'];

const words = (value: unknown) => String(value ?? '').replace(/\s+/g, ' ').trim().toLocaleLowerCase('en-US');

/**
 * The carrier whose tracker writes the parcel's timeline in advance, as the API serves it (`written_ahead_by`): one whose
 * answers for the number held rows back as written ahead of their own clocks (the adapter's `written_ahead`, kept in
 * `routing.written_ahead`), the carrier tracking the parcel first. The apps say so in one line. None once the parcel is
 * delivered or returned.
 */
export function servedWrittenAhead(row: JsonObject): CarrierId | null {
  const data = isRecord(row.carrier_data) ? row.carrier_data : {};
  const routing = isRecord(data.routing) ? data.routing : {};
  const kept = isRecord(routing.written_ahead) ? routing.written_ahead : null;
  const listed = kept && kept.number === row.tracking_number && isRecord(kept.carriers) ? kept.carriers : null;
  if (!listed || FINAL_STAGES.has(String(row.current_stage ?? ''))) return null;
  const named = Object.entries(listed).filter(([carrier, clocks]) => Array.isArray(clocks) && clocks.length > 0 && carrier in CARRIER_DEFINITIONS)
    .map(([carrier]) => carrier);
  const tracking = [data.active_tracking_carrier, row.carrier].find((carrier): carrier is string => typeof carrier === 'string' && named.includes(carrier));
  return (tracking ?? named[0] ?? null) as CarrierId | null;
}

/** The steps each carrier listed for the parcel's own number (`routing.planned`), or null. */
function keptPlans(row: JsonObject): JsonObject | null {
  const data = isRecord(row.carrier_data) ? row.carrier_data : {};
  const routing = isRecord(data.routing) ? data.routing : {};
  const kept = isRecord(routing.planned) ? routing.planned : null;
  return kept && kept.number === row.tracking_number && isRecord(kept.carriers) ? kept.carriers : null;
}

/** The shown scans that are a carrier's: Peek's own rows ("Tracking added") aside. */
const carrierScans = (events: readonly JsonObject[]) =>
  events.filter((event) => !(typeof event.provider_event_id === 'string' && event.provider_event_id.startsWith('app:')));

/**
 * When a step is planned, read in the carrier's zone: its moment, the moment it stops being ahead (a step dated by its
 * day alone stands until the end of that day) and that day. A wall clock with its time of day, as `time` or as
 * `local_time`, is that minute on the carrier's clock. Null when its date does not read.
 */
function stepTime(step: PlannedStep, zone: string): { instant: number; until: number; day: string | null } | null {
  const wall = !step.time && WALL_MINUTE.test((step.local_time ?? '').trim());
  const day = wall ? null : scanDay(step as unknown as JsonObject) ?? clockDay(step as unknown as JsonObject);
  const at = day ? DateTime.fromISO(day, { zone }).toUTC().toISO() : eventTimestamp(step.time ?? step.local_time, zone);
  const instant = Date.parse(at ?? '');
  if (!Number.isFinite(instant)) return null;
  return { instant, until: day ? DateTime.fromISO(day, { zone }).plus({ days: 1 }).startOf('day').toMillis() : instant, day };
}

/** A step as the API serves it (`PlannedTrackingEvent`): one the carrier gave no stage is in transit. */
function servedStep(step: PlannedStep, time: { instant: number; day: string | null }): JsonObject {
  const stage = step.stage && EVENT_STAGE_ORDER.includes(step.stage) ? step.stage : 'in_transit';
  return { description: step.description, stage, location: step.location ?? null,
    occurred_at: new Date(time.instant).toISOString(), ...(time.day ? { day: time.day } : {}) };
}

const oldestFirst = (a: JsonObject, b: JsonObject) => String(a.occurred_at).localeCompare(String(b.occurred_at));

/**
 * The planned steps a parcel's timeline folds, greyed, behind its line on predictions, as the API serves them
 * (`planned_events`): those its routing kept for the carrier tracking it now (`routing.planned`) and still ahead of
 * what happened and of `now`. They are never scans: nothing else reads them, so they set no stage, status line,
 * estimate, alert, email, count or drawing. A step goes once its time has passed (a step dated by its day alone, once
 * that day has), once the parcel is delivered or returned, once a shown scan (`events`, as served) is as late or later,
 * tells it in the same words or has come further, and for a placeholder number: a carrier's script that has run past
 * its "Delivered" never shows that step as planned for a time gone by. One the carrier wrote ahead is then served
 * apart (`servedWrittenSteps`).
 */
export function servedPlannedSteps(row: JsonObject, events: readonly JsonObject[], now = Date.now()): JsonObject[] {
  const listed = keptPlans(row);
  if (!listed) return [];
  if (FINAL_STAGES.has(String(row.current_stage ?? ''))) return [];
  const data = isRecord(row.carrier_data) ? row.carrier_data : {};
  const carriers = [...new Set([data.active_tracking_carrier, row.carrier].filter((carrier): carrier is string => typeof carrier === 'string'))];
  const carrier = carriers.find((name) => Array.isArray(listed[name]));
  if (!carrier) return [];
  const scans = carrierScans(events);
  const latest = Math.max(...scans.map((event) => Date.parse(String(event.occurred_at))).filter(Number.isFinite));
  const reached = Math.max(-1, ...[row.current_stage, ...scans.map((event) => event.stage)].map((stage) => PROGRESS.indexOf(String(stage))));
  if (scans.some((event) => FINAL_STAGES.has(String(event.stage)))) return [];
  const zone = carrierTimezone(carrier as Parameters<typeof carrierTimezone>[0]) ?? 'UTC';
  return plannedSteps(listed[carrier] as unknown[], zone, now).flatMap((step) => {
    const time = stepTime(step, zone);
    if (!time || time.until <= Math.max(latest, now)) return [];
    if (scans.some(scan => {
      const at = Date.parse(String(scan.occurred_at));
      const sameTime = time.day ? at >= time.instant && at <= time.until : Math.abs(at - time.instant) < MINUTE_MS;
      return sameTime && words(scan.description) === words(step.description)
        && (!scan.location || !step.location || words(scan.location) === words(step.location));
    })) return [];
    const served = servedStep(step, time);
    if (time.day) served.expires_at = new Date(time.until).toISOString();
    const progress = PROGRESS.indexOf(String(served.stage));
    return progress >= 0 && progress < reached ? [] : [served];
  }).sort(oldestFirst);
}

const OFFSET = /(?:Z|[+-]\d{2}:?\d{2})$/i;
const WALL_MINUTE = /^(\d{4}-\d{2}-\d{2})[T ](\d{2}:\d{2})/;
const BARE_DAY = /^\d{4}-\d{2}-\d{2}$/;
const MINUTE_MS = 60_000;
const DAY_MS = 86_400_000;

/**
 * Whether a step stands at a clock its carrier holds back as written ahead (`routing.written_ahead`), matched as the
 * scraper matches a provider's copy to one: the same instant within a minute when both carry their offset, else the
 * same wall clock to the minute, and for a step dated by its day alone a clock on that day.
 */
export function atWrittenClock(step: Pick<PlannedStep, 'time' | 'local_time'>, clocks: readonly string[]): boolean {
  // Its time, and the wall clock it kept beside it (`local_time`), as the scraper reads both.
  const times = [step.time, step.local_time].filter((value): value is string => typeof value === 'string').map((value) => value.trim());
  const absolute = times.filter((value) => OFFSET.test(value));
  return clocks.some((clock) => (OFFSET.test(clock) && absolute.length ? absolute : times)
    .some((value) => atClock(value, clock)));
}

function atClock(value: string, clock: string): boolean {
  const instant = OFFSET.test(value) ? Date.parse(value) : NaN;
  const wall = WALL_MINUTE.exec(value);
  if (OFFSET.test(value) && OFFSET.test(clock)) return Math.abs(Date.parse(clock) - instant) < MINUTE_MS;
  const other = WALL_MINUTE.exec(clock);
  if (wall) return other !== null && wall[1] === other[1] && wall[2] === other[2];
  return BARE_DAY.test(value) && clock.startsWith(value);
}

/**
 * The steps the carrier writing the timeline in advance (`servedWrittenAhead`) wrote and whose time has passed, as the
 * API serves them (`written_ahead_events`), oldest first. The timeline folds them, greyed, in the carrier's own words
 * behind its line on predictions, as its own page lists them. A step is one when that carrier listed it
 * (`routing.planned`) at a clock it holds back as written ahead (`routing.written_ahead`, `atWrittenClock`): a scan
 * entered at its planned time that the carrier then shows as a scan frees its clock, and a step merely not due yet is
 * the carrier's scan once due. Until its time passes a step is planned (`servedPlannedSteps`). A step goes once a shown
 * scan (`events`, as served) stands at its minute or tells it in the same words within a day of it, and every step once
 * the parcel is delivered or returned, and for a placeholder number. They are never scans either: a written
 * "Delivered" sets no stage, status line, estimate, alert, email, count or drawing.
 */
export function servedWrittenSteps(row: JsonObject, events: readonly JsonObject[], now = Date.now()): JsonObject[] {
  const carrier = servedWrittenAhead(row);
  const listed = keptPlans(row)?.[carrier ?? ''];
  if (!carrier || !Array.isArray(listed)) return [];
  const routing = (row.carrier_data as JsonObject).routing as JsonObject;
  const held = ((routing.written_ahead as JsonObject).carriers as JsonObject)[carrier] as unknown[];
  const clocks = held.filter((clock): clock is string => typeof clock === 'string');
  const scans = carrierScans(events);
  if (scans.some((event) => FINAL_STAGES.has(String(event.stage)))) return [];
  const shown = scans.map((event) => ({ at: Date.parse(String(event.occurred_at)), words: words(event.description) }))
    .filter((scan) => Number.isFinite(scan.at));
  const zone = carrierTimezone(carrier) ?? 'UTC';
  return plannedSteps(listed, zone, now).flatMap((step) => {
    const time = stepTime(step, zone);
    if (!time || time.until > now || !atWrittenClock(step, clocks)) return [];
    const said = words(step.description);
    const told = shown.some((scan) => Math.abs(scan.at - time.instant) < MINUTE_MS
      || (scan.words === said && scan.at > time.instant - DAY_MS && scan.at < time.until + DAY_MS));
    return told ? [] : [servedStep(step, time)];
  }).sort(oldestFirst);
}
