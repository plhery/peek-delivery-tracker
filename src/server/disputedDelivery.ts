import { classifyStage, inferStage, resultStage, type CarrierResult } from 'universal-parcel-scraper';
import { eventTimestamp, resultTimezone, statusMapAnswer } from 'universal-parcel-scraper/app';
import { STAGES } from '../generated/apiContract';
import { isRecord, type JsonObject } from './types';

/**
 * How long a delivery only a provider reported, which the parcel's own carrier did not
 * show, is checked again (`unconfirmed_delivery_until` in `carrier_data`).
 */
export const UNCONFIRMED_DELIVERY_MS = 3 * 24 * 60 * 60_000;

const FINAL_STAGES = new Set(['delivered', 'returned']);
const VALID_STAGES = new Set<string>(STAGES);

/** A scan's stage: the one its carrier declared, else the one its wording reads as. */
export function eventStage(event: JsonObject): string {
  const declared = String(event.stage ?? '');
  return VALID_STAGES.has(declared) ? declared : inferStage(String(event.description ?? ''));
}

/** The stages a scan read as in transit would step back from. */
const PAST_TRANSIT = new Set(['out_for_delivery', 'delivered', 'ready_for_pickup', 'failed_attempt', 'returned', 'exception']);

/**
 * The stage each scan of one answer gives itself, given the instant each is dated at (NaN for a
 * scan with no date): the one its carrier declared, else the one a wording rule reads it as; null
 * for a scan in none. A declared stage the carrier says no rule read (`stage_source` `none`) is
 * none when it is in transit, the reading of a scan no rule reads, or when it is a stage of the
 * newest dated scans in a stage of their own at or before it: a notice to the recipient that
 * keeps the stage of the scan before it. Any other is the carrier's own judgement, such as an
 * incident it flags as a problem, and stands.
 */
function ownStages(events: readonly JsonObject[], times: readonly number[]): (string | null)[] {
  // undefined: a declared stage in none, decided from the scans before it.
  const own = events.map((event): string | null | undefined => {
    const declared = String(event.stage ?? '');
    if (VALID_STAGES.has(declared)) return event.stage_source === 'none' ? undefined : declared;
    const wording = classifyStage(String(event.description ?? ''));
    return wording.source === 'none' ? null : wording.stage;
  });
  const at = (index: number) => Number.isFinite(times[index]) ? times[index] : Number.POSITIVE_INFINITY;
  const open = own.flatMap((stage, index) => stage === undefined ? [index] : []).sort((a, b) => at(a) - at(b) || a - b);
  for (const index of open) {
    const declared = String(events[index].stage);
    const before = new Set<string>();
    let newest = Number.NEGATIVE_INFINITY;
    for (const [other, stage] of Number.isFinite(times[index]) ? own.entries() : []) {
      const time = times[other];
      if (other === index || stage == null || stage === 'pending' || !Number.isFinite(time) || time > times[index] || time < newest) continue;
      if (time > newest) before.clear();
      before.add(stage);
      newest = time;
    }
    own[index] = declared === 'in_transit' || before.has(declared) ? null : declared;
  }
  return own.map((stage) => stage ?? null);
}

/**
 * The stage each scan of one answer is stored in, given the instant each is dated at (null for
 * a scan with no date): `eventStage`, except for a dated scan in no stage of its own (`ownStages`).
 * That one reads as the stage its carrier left it in, else as in transit, news that the parcel
 * moves, unless that is a step back from the newest dated scan in a stage of its own at or
 * before it (out for delivery, a delivery, a pickup point, a missed attempt, a return or a
 * problem). It is then pending: it stays in the timeline as the carrier's news but moves the
 * parcel neither back nor on (a carrier's notice after the delivery), and its wording never
 * stands for the stage it followed. A scan left pending is in no stage. A notice its carrier
 * left in the stage of that scan keeps it, but after a delivery or a return: the apps show it
 * under the scan and no alert announces it (stageNotices.ts).
 */
export function scanStages(events: readonly JsonObject[], instants: readonly (string | null)[]): string[] {
  const times = events.map((_, index) => Date.parse(instants[index] ?? ''));
  const read = ownStages(events, times);
  return read.map((stage, index) => {
    if (stage !== null) return stage;
    const declared = String(events[index].stage ?? '');
    const kept = VALID_STAGES.has(declared) ? declared : 'in_transit';
    const at = times[index];
    if (!Number.isFinite(at)) return kept;
    let newest = Number.NEGATIVE_INFINITY;
    let past = false;
    let same = false;
    for (const [other, otherStage] of read.entries()) {
      const time = times[other];
      if (otherStage === null || otherStage === 'pending' || !Number.isFinite(time) || time > at || time < newest) continue;
      past = (time === newest && past) || PAST_TRANSIT.has(otherStage);
      same = (time === newest && same) || otherStage === kept;
      newest = time;
    }
    return past && !(same && !FINAL_STAGES.has(kept)) ? 'pending' : kept;
  });
}

/** A gap note of a carrier's status map that says its scan keeps the stage of the scan before it: a notice. */
export const KEEPS_STAGE = /keeps? the stage/;

/**
 * The scans of one carrier's answer, a notice its adapter gave no stage of its own given the stage of the scan
 * dated before it, with no source of its own, as an adapter that keeps it gives it (DPD France's acceptance
 * notice): the carrier's map leaves its code or wording without a stage, noting that it keeps the stage of the scan
 * before it (Royal Mail's retention seconds after a customs hold). `scanStages` then reads it as a notice, not as
 * news that the parcel moves. A notice dated first takes registered. Oldest first; of two scans at one instant, the
 * one listed after is older, as carriers list theirs newest first. The stored replay restages stored rows alike.
 */
export function keptNotices(carrier: string, scans: readonly JsonObject[], instants: readonly (string | null)[]): JsonObject[] {
  const notices = scans.map((scan) => {
    if (VALID_STAGES.has(String(scan.stage ?? '')) && scan.stage_source === 'none') return false;
    const answer = statusMapAnswer({ carrier, providerCode: typeof scan.provider_code === 'string' ? scan.provider_code : null,
      description: String(scan.description ?? '') });
    return answer.kind === 'intentional_gap' && KEEPS_STAGE.test(answer.note);
  });
  if (!notices.includes(true)) return [...scans];
  const read = scanStages(scans, instants);
  const times = instants.map((instant) => Date.parse(instant ?? ''));
  const order = scans.map((_, index) => index).filter((index) => Number.isFinite(times[index]))
    .sort((a, b) => times[a] - times[b] || b - a);
  const kept = [...scans];
  let previous: string | null = null;
  for (const index of order) {
    const declared = String(scans[index].stage ?? '');
    if (notices[index]) kept[index] = { ...scans[index], stage: previous ?? 'registered', stage_source: 'none' };
    previous = notices[index] ? String(kept[index].stage) : VALID_STAGES.has(declared) ? declared : read[index];
  }
  return kept;
}

function scanTime(event: JsonObject, zone: string): number {
  return Date.parse(eventTimestamp(event.time || event.local_time, zone) ?? '');
}

/** Whether a carrier's own answer shows the parcel delivered, in its summary or a scan. */
export function showsDelivery(answer: CarrierResult | null): boolean {
  return answer !== null && [answer.status, answer.current_stage, ...(answer.events ?? []).filter(isRecord).map(eventStage)].includes('delivered');
}

/**
 * When a carrier's own answer last moved the parcel, as epoch milliseconds: its newest
 * scan in a stage of its own (`ownStages`), read on the carrier's clock. A scan in none, such as
 * a notice no rule reads, is one `scanStages` stores pending after a delivery, so it moves
 * nothing. NaN when the answer shows a delivery or a return, or no dated scan in a stage: it
 * then disputes no delivery.
 */
export function carrierMovedAt(carrier: string, answer: CarrierResult): number {
  const events = (answer.events ?? []).filter(isRecord);
  if ([answer.status, answer.current_stage, ...events.map(eventStage)].some((stage) => FINAL_STAGES.has(String(stage)))) {
    return Number.NaN;
  }
  const zone = resultTimezone(carrier, answer);
  const times = events.map((event) => scanTime(event, zone));
  const moved = ownStages(events, times).flatMap((stage, index) => stage !== null && stage !== 'pending' && Number.isFinite(times[index]) ? [times[index]] : []);
  return moved.length ? Math.max(...moved) : Number.NaN;
}

/**
 * A provider's answer without the delivery the parcel's own carrier disputes in the
 * same check: the carrier shows none and moved the parcel at the time of the provider's
 * newest delivery or later. The provider's delivery scans go, and a summary of delivery
 * gives way to the carrier's own. Null when nothing is disputed, as when the carrier's
 * newest scan is older than the delivery or a delivery scan has no date.
 */
export function withoutDisputedDelivery(
  provider: CarrierResult,
  providerSource: string,
  carrier: string,
  answer: CarrierResult,
): CarrierResult | null {
  const moved = carrierMovedAt(carrier, answer);
  if (!Number.isFinite(moved)) return null;
  const zone = resultTimezone(providerSource, provider);
  const events = provider.events ?? [];
  const deliveries = events.filter((event) => eventStage(event) === 'delivered').map((event) => scanTime(event, zone));
  const summary = provider.status === 'delivered' || resultStage(provider) === 'delivered';
  const delivered = deliveries.length ? Math.max(...deliveries) : summary ? scanTime({ time: provider.delivered_at }, zone) : Number.NaN;
  if (deliveries.some((time) => !Number.isFinite(time)) || !Number.isFinite(delivered) || delivered > moved) return null;
  const kept: CarrierResult = { ...provider };
  delete kept.delivered_at;
  return {
    ...kept,
    events: events.filter((event) => eventStage(event) !== 'delivered'),
    ...(summary ? {
      status: answer.status, current_stage: answer.current_stage,
      current_stage_source: answer.current_stage_source, last_status_text: answer.last_status_text,
    } : {}),
  };
}
