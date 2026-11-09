import 'server-only';

import { EVENT_STAGE_ORDER, tieOrder } from '../lib/stages';
import { captureOperationalError } from './observability';
import { CARRIER_DEFINITIONS, classifyWording, RECIPIENT_ACTIONS, recipientAction, type CarrierId } from 'universal-parcel-scraper';
import { placesForEvents, type EventPoint } from 'universal-parcel-scraper/places';
import { clockDay, placeWords, scanDay, UNWORDED_STEP } from './eventIdentity';
import { DIRECT_HISTORY, providerCopies, providerCopyScans } from './providerCopies';
import { copyTellsProblem, relayCopies } from './relayCopies';
import { repeatLeads } from './scanRepeats';
import { noticeScan, stageNotices } from './stageNotices';
import { servedPlannedSteps, servedWrittenAhead } from './plannedSteps';
import { PLACEHOLDER_NUMBER, placeholderNumber } from './trackingFailure';
import { isRecord, type JsonObject } from './types';

let reported = false;

/**
 * The stored point only feeds the place, and the source, provider and stage source only tell
 * Peek's own rows, relay copies, provider copies and notices apart: the API returns none
 * of them. A row of a relay pair names the other row instead (`relayLeads`),
 * a carrier's repeat of a scan the newest of its run (scanRepeats.ts), and a
 * carrier's notice in the stage of its scan before it that scan (stageNotices.ts). A
 * scan dated by its day alone names that day. A scan or stage change its source
 * gave no words for is served without Peek's stand-in, so the apps name its
 * stage. A scan in which the carrier asks
 * the recipient to act names what (`recipient_action`, see `scanAction`).
 */
function served(event: JsonObject, relayOf?: string): JsonObject {
  const action = scanAction(event);
  const rest: JsonObject = { ...dated(event), ...(relayOf ? { relay_of: relayOf } : {}),
    ...(event.description === UNWORDED_STEP ? { description: '' } : {}) };
  delete rest.point;
  delete rest.provider_event_id;
  delete rest.universal_provider;
  delete rest.stage_source;
  delete rest.recipient_action;
  if (tieOrder(rest.tie_order) < 0) delete rest.tie_order;
  return action ? { ...rest, recipient_action: action } : rest;
}

/**
 * What a scan asks its recipient to do: as the scraper flagged it when the scan was stored, which
 * a carrier's own code can set too (UPS), or, for a row stored before the flag, as its words say.
 */
function scanAction(event: JsonObject): string | undefined {
  return RECIPIENT_ACTIONS.find((action) => action === event.recipient_action)
    ?? recipientAction(typeof event.description === 'string' ? event.description : null);
}

/**
 * A scan with the times it was stored with read: the day it names, if any, and nothing else of
 * them. A wall clock in no zone dates its scan by its day too.
 */
function dated(event: JsonObject): JsonObject {
  const day = event.observed === true ? null : scanDay(event) ?? clockDay(event);
  const rest: JsonObject = { ...event, ...(day ? { day } : {}) };
  for (const key of ['time', 'local_time', 'observed']) delete rest[key];
  return rest;
}

/** A row Peek writes itself (`app:` source), such as "Tracking added". */
function isOwnRow(event: JsonObject): boolean {
  return typeof event.provider_event_id === 'string' && event.provider_event_id.startsWith('app:');
}

/**
 * The timeline is the carrier's story. Peek's own row, "Tracking added" or a
 * new carrier still to answer, only opens it: once a carrier scan is as old
 * as that row or older, the row is left out, so it never sits among the
 * scans or above a delivery. The stored row, the stage and alerts are not
 * affected.
 */
export function timeline(events: JsonObject[]): JsonObject[] {
  const time = (event: JsonObject) => Date.parse(String(event.occurred_at));
  const firstScan = Math.min(...events.filter((event) => !isOwnRow(event)).map(time).filter(Number.isFinite));
  return events.filter((event) => !isOwnRow(event) || !(firstScan <= time(event)));
}

/**
 * Whether the carrier the followed one names as delivering lists the parcel without a scan of its own: its last
 * probe found only the record the earlier carrier sent ahead (DHL's pre-advice), and the parcel was not handed over.
 */
function deliveryAnnounced(data: JsonObject): boolean {
  const probe = data.delivery_probe;
  return isRecord(probe) && probe.listed === true && typeof data.delivery_carrier === 'string'
    && probe.carrier === data.delivery_carrier && !data.original_carrier && data.active_tracking_carrier !== data.delivery_carrier;
}

/** The carrier data only the service reads, the delivering carrier's last check with its number (`delivery_probe`) among them. */
const SERVICE_DATA = ['add_recognition_pending', DIRECT_HISTORY, 'delivery_probe'];

/** The routing state served on its own: planned steps (`planned_events`) and the rows written ahead (`written_ahead_by`). */
const SERVED_APART = ['planned', 'written_ahead'];

/**
 * A package row without the carrier data that only the service reads (`SERVICE_DATA`), and with what the card says of
 * the delivering carrier's check (`delivery_announced`). Planned steps and the rows written ahead are served on their
 * own (`SERVED_APART`).
 */
function withoutServiceData(row: JsonObject): JsonObject {
  const stored = isRecord(row.carrier_data) ? row.carrier_data : null;
  const apart = stored && isRecord(stored.routing) && SERVED_APART.some((key) => key in (stored.routing as JsonObject));
  const announced = stored !== null && deliveryAnnounced(stored);
  if (!stored || !(SERVICE_DATA.some((key) => key in stored) || apart)) return row;
  const data: JsonObject = { ...stored, ...announced ? { delivery_announced: true } : {} };
  for (const key of SERVICE_DATA) delete data[key];
  if (apart) {
    const routing = { ...stored.routing as JsonObject };
    for (const key of SERVED_APART) delete routing[key];
    data.routing = routing;
  }
  return { ...row, carrier_data: data };
}

/** A stage change the sync stamped with the time it saw it, not a scan of the carrier's. */
function sighted(event: JsonObject): boolean {
  return event.observed === true || event.observed_without_provider_timestamp === true;
}

/** Who stored a row: the carrier its identity starts with (`unknown` for a universal provider, `app` for Peek's own rows). */
function sourceOf(event: JsonObject): string {
  return typeof event.provider_event_id === 'string' ? event.provider_event_id.split(':')[0]! : '';
}

/**
 * The sightings a parcel has moved on from that a dated scan of their source tells in another stage: the sync stored
 * the stage the carrier's summary gave when no scan showed it, in the words of a scan that kept a stage of its own
 * (Swiss Post's summary read a poste restante scan as a missed delivery while the scan's code had no stage, and the
 * words made it ready for pickup). The scan, no later than the sighting and in the same words, tells it, once a later
 * scan shows the stage passed. A sighting in the scan's stage is the sync's to date (`datedObservations`), and one in
 * Peek's stand-in words names no scan.
 */
function restatedSightings(events: readonly JsonObject[]): Set<string> {
  const words = (event: JsonObject) => String(event.description ?? '').replace(/\s+/g, ' ').trim().toLocaleLowerCase('en-US');
  const time = (event: JsonObject) => Date.parse(String(event.occurred_at));
  const scans = events.filter((event) => !sighted(event) && !['', 'app'].includes(sourceOf(event)) && Number.isFinite(time(event)));
  const worded = (event: JsonObject) => !['', UNWORDED_STEP.toLocaleLowerCase('en-US')].includes(words(event));
  return new Set(events.filter((sighting) => sighted(sighting) && worded(sighting) && !['', 'app'].includes(sourceOf(sighting))
    && scans.some((scan) => time(scan) > time(sighting))
    && scans.some((scan) => sourceOf(scan) === sourceOf(sighting) && scan.stage !== sighting.stage
      && words(scan) === words(sighting) && time(scan) <= time(sighting)))
    .map((sighting) => String(sighting.id)));
}

/**
 * The scans of a stored package row its timeline shows: without a provider's copies of its carrier's scans or a
 * sighting a scan already tells (`restatedSightings`), then as `timeline` keeps them. A placeholder number's scans are
 * other people's shipments merged under it: only Peek's own rows are shown.
 */
export function shownEvents(row: JsonObject): JsonObject[] {
  const copied = providerCopies(row);
  const placeholder = placeholderNumber(row);
  const events = Array.isArray(row.tracking_events) ? row.tracking_events.filter(isRecord) : [];
  const restated = restatedSightings(events);
  return timeline(events
    .filter((event) => !copied.has(String(event.id)) && !restated.has(String(event.id)) && (!placeholder || isOwnRow(event))));
}

/**
 * A parcel under a placeholder number is served as the owner's to correct, whatever was stored from it: what
 * providers answered for it, its status line, estimate and carrier data included, belongs to other people's
 * shipments.
 */
export function withoutPlaceholderAnswers(row: JsonObject): JsonObject {
  return placeholderNumber(row) ? {
    ...row, sync_status: 'error', sync_error: PLACEHOLDER_NUMBER, last_status_text: null, expected_delivery: null, carrier_data: {},
  } : row;
}

/** A stored package row with its events' sources left out, every row kept: the account's export. */
export function withoutEventSources(row: JsonObject): JsonObject {
  row = withoutServiceData(row);
  if (!Array.isArray(row.tracking_events)) return row;
  return {
    ...row,
    tracking_events: row.tracking_events.map((event) => {
      if (!isRecord(event)) return event;
      const rest = dated(event);
      delete rest.provider_event_id;
      delete rest.universal_provider;
      delete rest.stage_source;
      return rest;
    }),
  };
}

function eventPoint(value: unknown): EventPoint | null {
  if (!isRecord(value)) return null;
  const { latitude, longitude } = value;
  return typeof latitude === 'number' && typeof longitude === 'number' ? { latitude, longitude } : null;
}

/** Whether a stored scan says where it was: in words or as a point. */
function placed(event: JsonObject): boolean {
  return (typeof event.location === 'string' && event.location.trim() !== '') || eventPoint(event.point) !== null;
}

/**
 * A scan's words as a reader tells them apart: case, spacing and punctuation left out. Empty for a scan its source
 * gave no words for.
 */
function wordsRead(event: JsonObject): string {
  if (event.description === UNWORDED_STEP) return '';
  return String(event.description ?? '').normalize('NFC').toLocaleLowerCase('en-US').replace(/[^\p{L}\p{N}]+/gu, '');
}

/** Whether two rows of a relay pair tell the scan in the same words, spelt alike or not ("Designated Facility"). */
function sameWords(event: JsonObject, other: JsonObject): boolean {
  const words = wordsRead(event);
  return words !== '' && words === wordsRead(other);
}

/**
 * Whether a row names more of the place than another, every word of the other's included. A postcode or a number is
 * no more of the place for a reader: 4PX adds the ZIP code, its leading zero dropped, to SpeedX's town and state.
 */
function namesMoreOfThePlace(event: JsonObject, other: JsonObject): boolean {
  const words = (row: JsonObject) => placeWords(row).filter((word) => !/^\d+$/.test(word));
  const own = words(event);
  const others = words(other);
  return others.every((word) => own.includes(word)) && own.some((word) => !others.includes(word));
}

/**
 * Whether the earlier carrier's copy tells its scan in words that read as a stage neither row is in: TopYou tells
 * GOFO's arrival at a transfer centre as "Shipment is out for delivery", in a code it means as in transit.
 */
function copyMisleads(copy: JsonObject, scan: JsonObject): boolean {
  const { stage, source } = classifyWording(typeof copy.description === 'string' ? copy.description : '');
  return source !== 'none' && stage !== copy.stage && stage !== scan.stage;
}

/**
 * Each relay pair as it is served: the row the apps show under the other, and that row's id
 * (`relay_of`). The earlier carrier's copy goes under the scan it repeats, unless the copy alone
 * says where it was: YunExpress and Yanwen tell Ecoscooting's scans with the town Ecoscooting's
 * own leave out, Chronopost tells DPD's with the depot. Nor does a copy that alone tells a problem
 * beside the scan's routine step (relayCopies.ts), nor one in the scan's stage and words that names
 * more of the place. The apps show the lower row's words, not its place.
 */
function relayLeads(events: readonly JsonObject[], relays: Map<string, string>): Map<string, string> {
  const byId = new Map(events.map((event) => [String(event.id), event]));
  const leads = (copy: JsonObject, scan: JsonObject) => (placed(copy) && !placed(scan)) || copyTellsProblem(copy, scan)
    || (copy.stage === scan.stage && sameWords(copy, scan) && namesMoreOfThePlace(copy, scan));
  return new Map([...relays].map(([copy, scan]): [string, string] => (
    leads(byId.get(copy)!, byId.get(scan)!) ? [scan, copy] : [copy, scan])));
}

/**
 * Each notice in the stage of its scan before it, with the row it is shown under (stageNotices.ts): that scan, or the
 * row the scan itself is shown under as a carrier's repeat. Rows of a relay pair (`apart`) are neither.
 */
function noticeLeads(events: readonly JsonObject[], apart: ReadonlySet<string>, repeats: ReadonlyMap<string, string>): Map<string, string> {
  const notices = stageNotices(events.filter((event) => !apart.has(String(event.id))).map(noticeScan));
  return new Map([...notices].map(([notice, scan]) => [notice, repeats.get(scan) ?? scan]));
}

/**
 * The left-out provider copies that can place each carrier scan they repeat, best first: a copy that says where it
 * was and repeats that scan alone, at the scan's minute before one a zone or a few minutes away, then the nearest.
 * Correos Chile names its own office ("OFICINA INTERNACIONAL") where 17TRACK and ParcelsApp name the town.
 */
function placeLenders(row: JsonObject): Map<string, JsonObject[]> {
  const events = new Map((Array.isArray(row.tracking_events) ? row.tracking_events.filter(isRecord) : [])
    .map((event) => [String(event.id), event]));
  const time = (event: JsonObject) => Date.parse(String(event.occurred_at));
  const minute = (event: JsonObject) => Math.floor(time(event) / 60_000);
  const lenders = new Map<string, JsonObject[]>();
  for (const [copy, scans] of providerCopyScans(row)) {
    const lender = events.get(copy);
    const [scan] = scans;
    if (scans.size === 1 && lender && placed(lender)) lenders.set(scan, [...lenders.get(scan) ?? [], lender]);
  }
  for (const [scan, list] of lenders) {
    const own = events.get(scan)!;
    list.sort((a, b) => Number(minute(b) === minute(own)) - Number(minute(a) === minute(own))
      || Math.abs(time(a) - time(own)) - Math.abs(time(b) - time(own)));
  }
  return lenders;
}

/**
 * An API package row as it is served. Every scan gets a `place`: where its
 * free text locates it, or null. Places are worked out when the package is
 * served, so improving the gazetteer improves every parcel, old ones
 * included. A universal provider's copy of a confirmed carrier's scan is left
 * out (providerCopies.ts), so is Peek's own opening row once carrier scans
 * reach back to it (see `timeline`), and a row of a relay pair, a carrier's repeat of a scan or its notice in the stage
 * of the scan before it names the row it is shown under (`relay_of`, see `relayLeads`, relayCopies.ts, scanRepeats.ts and
 * stageNotices.ts). A carrier's scan that names no place the
 * gazetteer finds, such as an office, takes the place of a left-out copy of it (`placeLenders`). A placeholder
 * number's parcel is served without what was stored from it (`withoutPlaceholderAnswers`). The steps its carrier
 * listed before they happen and that are still ahead are served apart from the scans, each with its place
 * (`planned_events`, plannedSteps.ts and `placedSteps`), and so is the carrier that writes the timeline in advance
 * (`written_ahead_by`, `servedWrittenAhead`).
 */
export function withEventPlaces(row: JsonObject): JsonObject {
  const served = placedEvents(row);
  const answered = withoutPlaceholderAnswers(row);
  const planned = placedSteps(row, servedPlannedSteps(answered, shownEvents(row)));
  const ahead = servedWrittenAhead(answered);
  if (!planned.length && !ahead) return served;
  return { ...served, ...planned.length ? { planned_events: planned } : {}, ...ahead ? { written_ahead_by: ahead } : {} };
}

/**
 * Planned steps with the place each one's location names, found as a scan's from the parcel's destination and
 * carriers, so the apps name its town as they name a scan's (a place written in another script is shown by it). No
 * map shows them. Without the gazetteer they are served as listed.
 */
function placedSteps(row: JsonObject, steps: JsonObject[]): JsonObject[] {
  if (!steps.length) return steps;
  const data = isRecord(row.carrier_data) ? row.carrier_data : {};
  try {
    const places = placesForEvents(steps.map((step) => typeof step.location === 'string' ? step.location : null), {
      destinationCountry: typeof data.destination_country === 'string' ? data.destination_country : null,
      carriers: [data.active_tracking_carrier, row.carrier].filter((carrier): carrier is string => typeof carrier === 'string'),
    });
    return steps.map((step, index) => ({ ...step, place: places[index] ?? null }));
  } catch (error) {
    if (!reported) captureOperationalError(error, { component: 'api', operation: 'locate_event_places' });
    reported = true;
    return steps;
  }
}

function placedEvents(row: JsonObject): JsonObject {
  const stored = row;
  row = withoutPlaceholderAnswers(withoutServiceData(row));
  if (!Array.isArray(row.tracking_events) || !row.tracking_events.length) return row;
  const events = shownEvents(stored);
  const pairs = relayCopies({ ...row, tracking_events: events });
  const relays = relayLeads(events, pairs);
  const apart = new Set([...relays.keys(), ...relays.values()]);
  const repeats = repeatLeads(events, apart);
  const notices = noticeLeads(events, apart, repeats);
  const byId = new Map(events.map((event) => [String(event.id), event]));
  // The apps show a relay row's words under the other row unless they are that row's: in the same words spelt
  // otherwise, it is served in the other row's spelling, so they are shown once. So is a copy whose words read as
  // a stage neither row is in, in the words of the carrier whose scan it tells, whichever row is on top.
  const misled = new Map([...pairs].filter(([copy, scan]) => copyMisleads(byId.get(copy)!, byId.get(scan)!))
    .map(([copy, scan]) => [copy, byId.get(scan)!.description]));
  const spelt = (event: JsonObject, under: JsonObject | undefined) => misled.has(String(event.id))
    ? { ...event, description: misled.get(String(event.id)) }
    : under && sameWords(event, under) && event.description !== under.description ? { ...event, description: under.description } : event;
  const shown = (event: JsonObject) => {
    const relayOf = relays.get(String(event.id));
    return served(spelt(event, relayOf ? byId.get(relayOf) : undefined),
      relayOf ?? notices.get(String(event.id)) ?? repeats.get(String(event.id)));
  };
  const carrierData = isRecord(row.carrier_data) ? row.carrier_data : {};
  try {
    const ordered = [...events].sort((a, b) => String(a.occurred_at).localeCompare(String(b.occurred_at))
      || EVENT_STAGE_ORDER.indexOf(String(a.stage)) - EVENT_STAGE_ORDER.indexOf(String(b.stage))
      || tieOrder(a.tie_order) - tieOrder(b.tie_order));
    const destination = typeof carrierData.destination_country === 'string' ? carrierData.destination_country : null;
    const carriers = [carrierData.active_tracking_carrier, row.carrier].filter((carrier): carrier is string => typeof carrier === 'string');
    const locate = (where: readonly JsonObject[]) => placesForEvents(where.map((event) => typeof event.location === 'string' ? event.location : null), {
      destinationCountry: destination,
      // Their countries and their own hubs. The carrier delivering now says more than the one the parcel was added with.
      carriers,
      points: where.map((event) => eventPoint(event.point)),
      // Paperwork filed where the parcel is not, such as customs at the destination's office, gets no place; the scan keeps its text.
      stages: ordered.map((event) => typeof event.stage === 'string' ? event.stage : null),
      times: ordered.map((event) => typeof event.occurred_at === 'string' ? event.occurred_at : null),
    });
    const places = locate(ordered);
    // A scan with no place and no point of its own is placed where the best copy of it says, at its own time and stage,
    // in a country the parcel is going to, its carriers deliver in or its scans already show; every other scan keeps
    // the place it had.
    const lenders = placeLenders(stored);
    const lent = ordered.map((event, index) => places[index] === null && eventPoint(event.point) === null
      ? lenders.get(String(event.id))?.[0] : undefined);
    if (lent.some(Boolean)) {
      const borrowed = locate(ordered.map((event, index) => lent[index] ?? event));
      const countries = new Set([destination, ...carriers.flatMap((carrier) => CARRIER_DEFINITIONS[carrier as CarrierId]?.countries ?? []),
        ...places.map((place) => place?.country)]);
      lent.forEach((lender, index) => {
        if (lender && countries.has(borrowed[index]?.country)) places[index] = borrowed[index];
      });
    }
    const byEvent = new Map(ordered.map((event, index) => [event, places[index]]));
    return {
      ...row,
      tracking_events: events.map((event) => ({ ...shown(event), place: byEvent.get(event) ?? null })),
    };
  } catch (error) {
    // A map is a nice extra: the parcel still loads without one.
    if (!reported) captureOperationalError(error, { component: 'api', operation: 'locate_event_places' });
    reported = true;
    return { ...row, tracking_events: events.map(shown) };
  }
}
