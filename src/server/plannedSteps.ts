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
 * `plannedEvents` when nothing else remains), as its scans are shaped.
 */
export interface PlannedStep {
  time?: string;
  local_time?: string;
  description: string;
  location?: string;
  stage?: string;
}

/** The most planned steps kept for each carrier of a parcel. */
export const PLANNED_STEPS_KEPT = 30;
const TEXT_LENGTH = 300;

/** The planned steps a carrier's answer, or its failure, names (`planned_events`, `plannedEvents`, through causes). */
export function plannedStepsOf(value: unknown): PlannedStep[] {
  const seen = new Set<unknown>();
  let current = value;
  while (isRecord(current) && !seen.has(current)) {
    seen.add(current);
    const listed = current.planned_events ?? current.plannedEvents;
    if (Array.isArray(listed)) return plannedSteps(listed);
    current = current.cause;
  }
  return [];
}

/** A list of planned steps as stored: worded, dated, each once, at most `PLANNED_STEPS_KEPT`, the earliest kept. */
export function plannedSteps(listed: readonly unknown[]): PlannedStep[] {
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
  return steps.length > PLANNED_STEPS_KEPT ? [...steps].sort((a, b) => sortKey(a) - sortKey(b)).slice(0, PLANNED_STEPS_KEPT) : steps;
}

const sortKey = (step: PlannedStep) => { const at = Date.parse(step.time ?? step.local_time ?? ''); return Number.isFinite(at) ? at : Infinity; };

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

/**
 * The planned steps a parcel's timeline shows, greyed under "Planned by the carrier", as the API serves them
 * (`planned_events`): those its routing kept for the carrier tracking it now (`routing.planned`) and still ahead of
 * what happened and of `now`. They are never scans: nothing else reads them, so they set no stage, status line,
 * estimate, alert, email, count or drawing. A step goes once its time has passed (a step dated by its day alone, once
 * that day has), once the parcel is delivered or returned, once a shown scan (`events`, as served) is as late or later,
 * tells it in the same words or has come further, and for a placeholder number: a carrier's script that has run past
 * its "Delivered" never shows that step as planned for a time gone by.
 */
export function servedPlannedSteps(row: JsonObject, events: readonly JsonObject[], now = Date.now()): JsonObject[] {
  const data = isRecord(row.carrier_data) ? row.carrier_data : {};
  const routing = isRecord(data.routing) ? data.routing : {};
  const kept = isRecord(routing.planned) ? routing.planned : null;
  const listed = kept && kept.number === row.tracking_number && isRecord(kept.carriers) ? kept.carriers : null;
  if (!listed) return [];
  if (FINAL_STAGES.has(String(row.current_stage ?? ''))) return [];
  const carriers = [...new Set([data.active_tracking_carrier, row.carrier].filter((carrier): carrier is string => typeof carrier === 'string'))];
  const carrier = carriers.find((name) => Array.isArray(listed[name]));
  if (!carrier) return [];
  const own = (event: JsonObject) => typeof event.provider_event_id === 'string' && event.provider_event_id.startsWith('app:');
  const scans = events.filter((event) => !own(event));
  const latest = Math.max(...scans.map((event) => Date.parse(String(event.occurred_at))).filter(Number.isFinite));
  const told = new Set(scans.map((event) => words(event.description)));
  const reached = Math.max(-1, ...[row.current_stage, ...scans.map((event) => event.stage)].map((stage) => PROGRESS.indexOf(String(stage))));
  if (scans.some((event) => FINAL_STAGES.has(String(event.stage)))) return [];
  const zone = carrierTimezone(carrier as Parameters<typeof carrierTimezone>[0]) ?? 'UTC';
  return plannedSteps(listed[carrier] as unknown[]).flatMap((step) => {
    const day = scanDay(step as unknown as JsonObject) ?? clockDay(step as unknown as JsonObject);
    const at = day ? DateTime.fromISO(day, { zone }).toUTC().toISO() : eventTimestamp(step.time ?? step.local_time, zone);
    const instant = Date.parse(at ?? '');
    if (!Number.isFinite(instant)) return [];
    // A day-only step stands until the end of its day.
    const until = day ? DateTime.fromISO(day, { zone }).endOf('day').toMillis() : instant;
    if (until <= Math.max(latest, now) || told.has(words(step.description))) return [];
    const stage = step.stage && EVENT_STAGE_ORDER.includes(step.stage) ? step.stage : 'in_transit';
    if (PROGRESS.indexOf(stage) >= 0 && PROGRESS.indexOf(stage) < reached) return [];
    return [{ description: step.description, stage, location: step.location ?? null,
      occurred_at: new Date(instant).toISOString(), ...(day ? { day } : {}) }];
  }).sort((a, b) => String(a.occurred_at).localeCompare(String(b.occurred_at)));
}
