import 'server-only';

import { createHash } from 'node:crypto';
import { DateTime, FixedOffsetZone, IANAZone, type Zone } from 'luxon';
import { placesForEvents, placeTimeZone, type EventPoint } from 'universal-parcel-scraper/places';
import type { ApiPackageRow, ApiPeekAi, ApiPeekAiSays } from '../generated/apiContract';
import { currentEvent } from '../lib/stages';
import { toParcel } from '../store/apiRepo';
import { scanDay } from './eventIdentity';
import { captureOperationalError } from './observability';
import {
  ARRIVAL_STAGES, codePoints, orderedScans, PHYSICAL_STAGES, predictArrival, scanMilestones, snapshotFeatures,
  statusKey, withTargetEncoding, type AiParcel, type AiScan, type ModelContext, type PeekAiModelDoc,
} from './peekAiModel';
import { isRecord, type JsonObject } from './types';

/**
 * Peek AI: when Peek has a strong hint of when a parcel will come, it says so (`peek_ai`), as Peek's own estimate
 * beside the carrier's. Five hints held up on the parcels Peek has followed, each right at least 8 times in 10:
 *
 * - the carrier says today, nothing has gone out for delivery by the afternoon and a check has just looked: not today;
 * - out for delivery: by the hour most of that carrier's deliveries come by, or, late in the day, not today;
 * - the carrier's day passed yesterday and a scan came since: today or tomorrow;
 * - a carrier's day two days ahead or more that parcels like it usually beat: maybe sooner;
 * - no carrier date: a day, today or tomorrow, this week or the end of next week, where the model is sure enough.
 *
 * The model and its tables are trained in private on production data and stored in the database (`peek_ai_models`);
 * the server loads the newest at start and every hour (`keepPeekAiModel`). With none loaded, Peek AI says nothing.
 */

/** What Peek AI says of a parcel, as the API serves it (`peek_ai`, contracts/openapi.json). */
export type PeekAi = ApiPeekAi;

interface HourSure { from: number | null; sure?: Record<string, number> }
interface ByTime { by: string; sure: number; days: number; shares: number[] }
interface LateOut { from: number; sure: number }

/** The document the database stores (`peek_ai_models.model`), written by the private training. */
export interface PeekAiBlob extends ModelContext {
  version: string;
  trainedThrough: string;
  parcels: number;
  /** A parcel's clock when no scan gives its zone: the destination's offset in hours, else `defaultOffset`. */
  offsets: Record<string, number>;
  defaultOffset: number;
  notToday: Record<string, HourSure>;
  byTime: Record<string, ByTime>;
  lateOut: Record<string, LateOut>;
  ofdUnreliable: string[];
  claims: { tau: number; byDayWidth: number; weekTau: number; soonerLead: number; soonerTau: number; recentCheckHours: number };
  sure: Record<string, number>;
  model: PeekAiModelDoc;
}

/** A call right at most this often, for Peek's word: a measured "10 in 10" comes from too few parcels. */
const MAX_SURE = 0.95;
/** A call right less often than this is not said: a model whose measure slipped below 8 in 10 stays quiet. */
const MIN_SURE = 0.8;
/** Hours since a check after which the model's calls wait for the next one: the scans may be behind the carrier's. */
const FRESH_HOURS = 36;
const DEFAULT_DELIVERY_DAYS: readonly number[] = [0, 1, 2, 3, 4, 5];
const NOT_DUE: ReadonlySet<string> = new Set(['delivered', 'returned', 'ready_for_pickup', 'failed_attempt']);
const SNAPSHOT_HOURS = [14, 8] as const;
const CACHE_SIZE = 5000;

// One model and cache per process: Next compiles the routes and the instrumentation that loads the model into different
// module instances, so a module variable set at start would stay empty where the routes read it.
const stateKey = Symbol.for('peek.peekAiModel');
const processState = globalThis as typeof globalThis & { [stateKey]?: { blob: PeekAiBlob | null; placed: Map<string, Placed> } };
const state = processState[stateKey] ??= { blob: null, placed: new Map() };

/** The model Peek AI reads, or null while none is loaded. */
export function peekAiBlob(): PeekAiBlob | null {
  return state.blob;
}

/** Puts a model in place, or takes it away. Tests use it; the server keeps it with `keepPeekAiModel`. */
export function setPeekAiBlob(blob: PeekAiBlob | null): void {
  state.blob = blob;
  placedCache.clear();
}

export interface PeekAiModelSource {
  latestPeekAiModelVersion(): Promise<string | null>;
  peekAiModel(version: string): Promise<unknown>;
}

/** Loads the newest stored model now and every hour after; a model that fails to load leaves the one in place. */
export function keepPeekAiModel(source: PeekAiModelSource, every = 3_600_000): () => void {
  const load = async () => {
    try {
      const version = await source.latestPeekAiModelVersion();
      if (version === null) return setPeekAiBlob(null);
      if (state.blob?.version === version) return;
      const blob = peekAiBlobFrom(await source.peekAiModel(version));
      if (blob) setPeekAiBlob(blob);
      else captureOperationalError(new Error('Stored Peek AI model is malformed'), { component: 'api', operation: 'load_peek_ai_model' });
    } catch (error) {
      captureOperationalError(error, { component: 'api', operation: 'load_peek_ai_model' });
    }
  };
  void load();
  const timer = setInterval(() => void load(), every);
  timer.unref?.();
  return () => clearInterval(timer);
}

const numbers = (value: unknown) => isRecord(value) && Object.values(value).every((item) => typeof item === 'number');
const CLAIMS = ['tau', 'byDayWidth', 'weekTau', 'soonerLead', 'soonerTau', 'recentCheckHours'] as const;
const SURE = ['missed_date', 'sooner', 'today_or_tomorrow', 'by_day', 'this_week', 'by_end_of_next_week'] as const;
const has = (value: Record<string, unknown>, keys: readonly string[]) => keys.every((key) => typeof value[key] === 'number');

/** The stored document as a model, or null when it lacks what Peek AI reads. */
export function peekAiBlobFrom(value: unknown): PeekAiBlob | null {
  if (!isRecord(value) || typeof value.version !== 'string' || !isRecord(value.model)) return null;
  const model = value.model;
  const encoding = isRecord(model.targetEncoding) ? model.targetEncoding : null;
  const claims = isRecord(value.claims) ? value.claims : null;
  const ok = typeof model.horizon === 'number' && Array.isArray(model.features) && Array.isArray(model.trees)
    && isRecord(model.categories) && encoding !== null && Array.isArray(encoding.prior) && isRecord(encoding.keys)
    && typeof encoding.k === 'number' && isRecord(value.deliveryDays) && isRecord(value.homeCountries)
    && numbers(value.offsets) && typeof value.defaultOffset === 'number' && isRecord(value.notToday)
    && isRecord(value.notToday.default) && isRecord(value.byTime) && isRecord(value.lateOut) && isRecord(value.lateOut.default)
    && has(value.lateOut.default, ['from', 'sure']) && Array.isArray(value.ofdUnreliable) && claims !== null && numbers(claims)
    && has(claims, CLAIMS) && numbers(value.sure) && has(value.sure as Record<string, unknown>, SURE);
  return ok ? value as unknown as PeekAiBlob : null;
}

const mod = (a: number, b: number) => ((a % b) + b) % b;
const round2 = (x: number) => Math.round(x * 100) / 100;
const capped = (sure: number) => Math.min(MAX_SURE, sure);
/** Days since 1970-01-01 of a moment's date on its clock. */
const ordinal = (time: DateTime) => Math.round(Date.UTC(time.year, time.month - 1, time.day) / 86_400_000);
const isoDay = (day: number) => new Date(day * 86_400_000).toISOString().slice(0, 10);
const dayOf = (text: string) => Math.round(Date.parse(`${text}T00:00:00Z`) / 86_400_000);

/** Whether the carrier delivers on a day (days since 1970-01-01). */
type Delivers = (day: number) => boolean;

/** The day `b` delivery days after day `snap`. */
function endDay(delivers: Delivers, snap: number, b: number): number {
  let day = snap;
  for (let n = 0; n < b; day++) if (delivers(day + 1)) n++;
  return day;
}

/** Delivery days after day `a` up to and including day `b`. */
function daysBetween(delivers: Delivers, a: number, b: number): number {
  let n = 0;
  for (let day = a + 1; day <= b; day++) if (delivers(day)) n++;
  return n;
}

const dateDay = (year: number, month: number, date: number) => Math.round(Date.UTC(year, month - 1, date) / 86_400_000);
/** The n-th weekday (0 for Monday) of a month, or its last for n = -1, in days since 1970-01-01. */
function nthWeekday(year: number, month: number, weekday: number, n: number): number {
  if (n < 0) {
    const last = dateDay(year, month + 1, 0);
    return last - mod(mod(last + 3, 7) - weekday, 7);
  }
  const first = dateDay(year, month, 1);
  return first + mod(weekday - mod(first + 3, 7), 7) + (n - 1) * 7;
}

/**
 * The days no carrier delivers on in the country: in the US, the six holidays USPS, UPS and FedEx all close on (New
 * Year's Day, Memorial Day, Independence Day, Labor Day, Thanksgiving and Christmas), with the Friday before one that
 * falls on a Saturday and the Monday after one on a Sunday, as they observe it. None for other countries yet.
 */
export function closedDays(country: string | null, years: readonly number[]): Set<number> {
  const closed = new Set<number>();
  if (country !== 'US') return closed;
  for (const year of years) {
    for (const day of [dateDay(year, 1, 1), nthWeekday(year, 5, 0, -1), dateDay(year, 7, 4), nthWeekday(year, 9, 0, 1),
      nthWeekday(year, 11, 3, 4), dateDay(year, 12, 25)]) {
      closed.add(day);
      if (mod(day + 3, 7) === 5) closed.add(day - 1);
      if (mod(day + 3, 7) === 6) closed.add(day + 1);
    }
  }
  return closed;
}

/** The shortest run of delivery days holding at least `tau` of the chance, the likeliest of them: [a, b]. */
export function shortestRange(F: readonly number[], tau: number): { a: number; b: number } | null {
  const H = F.length - 1;
  for (let w = 0; w <= H; w++) {
    let best = -1;
    let mass = -Infinity;
    for (let a = 0; a + w <= H; a++) {
      const m = F[a + w]! - (a > 0 ? F[a - 1]! : 0);
      if (m > mass) [mass, best] = [m, a];
    }
    if (mass >= tau) return { a: best, b: best + w };
  }
  return null;
}

interface PlacedScan extends AiScan {
  zone: string | null;
  id: string;
  /** Dated by its day alone: it stands at the start of that day, or just after the scan before it, at no hour it was made. */
  dayAlone: boolean;
}

interface Placed {
  scans: PlacedScan[];
  /** The model's chances by model version, snapshot moment and what the carrier says of the parcel itself. */
  F: Map<string, number[] | null>;
}

const placedCache = state.placed;

const text = (value: unknown) => typeof value === 'string' ? value : null;

function point(value: unknown): EventPoint | null {
  if (!isRecord(value)) return null;
  const { latitude, longitude } = value;
  return typeof latitude === 'number' && typeof longitude === 'number' ? { latitude, longitude } : null;
}

/** The carrier the model reads the parcel as: the one now tracking it, else the one routing confirmed, else its own. */
function modelCarrier(row: JsonObject): string {
  const data = isRecord(row.carrier_data) ? row.carrier_data : {};
  const routing = isRecord(data.routing) ? data.routing : {};
  return text(data.active_tracking_carrier) ?? text(routing.confirmed_carrier) ?? String(row.carrier ?? '');
}

/** The country a stored postcode was given for (`postcode_country`), as a code. */
function postcodeCountry(data: JsonObject): string | null {
  const value = data.postcode_country;
  if (typeof value === 'string') return /^[A-Z]{2}$/.test(value) ? value : null;
  return isRecord(value) && typeof value.country === 'string' ? value.country : null;
}

/**
 * The scans as the model was trained on them, placed: the carrier's (not Peek's own rows), dated by the carrier and no
 * more than a day after they were stored, not stamped with the time the carrier's system wrote them, ordered by time
 * and then stage, and placed by their words and points on the parcel's way.
 */
function placedScans(row: JsonObject, c: string): Placed {
  const raw = (Array.isArray(row.tracking_events) ? row.tracking_events.filter(isRecord) : []).filter((event) => {
    const id = text(event.provider_event_id);
    const occurred = Date.parse(String(event.occurred_at));
    const stored = Date.parse(String(event.stored_at));
    return id !== null && id.split(':')[0] !== 'app' && (event.observed === null || event.observed === undefined)
      && (event.written_at === null || event.written_at === undefined) && Number.isFinite(occurred)
      && (!Number.isFinite(stored) || occurred <= stored + 86_400_000);
  });
  const data = isRecord(row.carrier_data) ? row.carrier_data : {};
  // As in training, which read a postcode's country stored with its postcode as text no place matches: only a
  // country stored alone reaches the places.
  const destination = text(data.destination_country) ?? (typeof data.postcode_country === 'string' ? text(data.postcode_country) : null);
  // Keyed by everything placing reads: a scan reworded or restaged in place is placed again.
  const key = createHash('sha1').update(JSON.stringify([row.id, c, destination, raw.map((event) => [
    event.id, event.stage, event.occurred_at, event.description, event.location, event.point, event.provider_code, event.provider_event_id,
    event.time, event.local_time,
  ])])).digest('base64');
  const hit = placedCache.get(key);
  if (hit) {
    placedCache.delete(key);
    placedCache.set(key, hit);
    return hit;
  }
  const scans: PlacedScan[] = raw.map((event) => {
    const stage = String(event.stage);
    const id = String(event.provider_event_id);
    return {
      id: String(event.id), stage, ts: Date.parse(String(event.occurred_at)) / 1000,
      description: stage === 'delivered' ? null : text(event.description), providerCode: text(event.provider_code),
      source: id.split(':')[0]!.slice(0, 40), country: null, lat: null, lon: null, precision: null, zone: null,
      dayAlone: scanDay(event) !== null,
    };
  });
  const ordered = orderedScans(scans) as PlacedScan[];
  const byId = new Map(raw.map((event) => [String(event.id), event]));
  try {
    // Each place's words cut as the training export cut them (Postgres's `left`, by character).
    const locations = ordered.map((scan) => {
      const location = text(byId.get(scan.id)?.location);
      return location === null ? null : codePoints(location, 120);
    });
    const places = placesForEvents(locations, {
      destinationCountry: destination,
      carriers: c ? [c] : [],
      points: ordered.map((scan) => point(byId.get(scan.id)?.point)),
      stages: ordered.map((scan) => scan.stage),
      times: ordered.map((scan) => new Date(scan.ts * 1000).toISOString().replace(/\.\d{3}Z$/, 'Z')),
    });
    ordered.forEach((scan, index) => {
      const place = places[index];
      if (!place) return;
      Object.assign(scan, { country: place.country, lat: place.latitude, lon: place.longitude, precision: place.precision });
      try {
        scan.zone = placeTimeZone(place);
      } catch {
        scan.zone = null;
      }
    });
  } catch (error) {
    captureOperationalError(error, { component: 'api', operation: 'locate_peek_ai_scans' });
  }
  const placed = { scans: ordered, F: new Map<string, number[] | null>() };
  placedCache.set(key, placed);
  if (placedCache.size > CACHE_SIZE) placedCache.delete(placedCache.keys().next().value!);
  return placed;
}

/**
 * The parcel's clock, as in training: the zone the carrier declares, else the place of its newest out-for-delivery
 * scan, else of its newest placed scan when that is in the destination country, else the destination's offset.
 */
function parcelClock(row: JsonObject, scans: readonly PlacedScan[], blob: PeekAiBlob): Zone {
  const data = isRecord(row.carrier_data) ? row.carrier_data : {};
  const fixed = () => {
    const dest = text(data.destination_country) ?? postcodeCountry(data);
    const hint = text(data.lookup_country_hint);
    const hours = dest ? blob.offsets[dest] ?? blob.defaultOffset : hint ? blob.offsets[hint] ?? blob.defaultOffset : blob.defaultOffset;
    return FixedOffsetZone.instance(hours * 60);
  };
  const zone = (name: string | null) => name && IANAZone.isValidZone(name) ? IANAZone.create(name) : null;
  if (typeof data.timezone === 'string' && data.timezone.trim()) return zone(data.timezone) ?? fixed();
  const end = [...scans].reverse().find((scan) => scan.zone && (scan.stage === 'out_for_delivery' || ARRIVAL_STAGES.has(scan.stage)));
  if (end) return zone(end.zone) ?? fixed();
  const endCountry = [...scans].reverse().find((scan) => scan.country && (scan.stage === 'out_for_delivery' || ARRIVAL_STAGES.has(scan.stage)))?.country;
  const dest = text(data.destination_country) ?? postcodeCountry(data) ?? endCountry ?? null;
  const lastCountry = [...scans].reverse().find((scan) => scan.country)?.country;
  const lastZone = [...scans].reverse().find((scan) => scan.zone)?.zone ?? null;
  if (dest && lastCountry === dest && lastZone) return zone(lastZone) ?? fixed();
  return fixed();
}

/** The latest 08:00 or 14:00 on the parcel's clock at or before `local`: the moments the model was trained at. */
function snapshot(local: DateTime): DateTime {
  const hour = SNAPSHOT_HOURS.find((h) => local.hour >= h);
  const at = (day: DateTime, h: number) => day.set({ hour: h, minute: 0, second: 0, millisecond: 0 });
  return hour !== undefined ? at(local, hour) : at(local.minus({ days: 1 }), 14);
}

function arrivalChances(row: JsonObject, placed: Placed, snap: DateTime, blob: PeekAiBlob, c: string): number[] | null {
  const t = snap.toSeconds();
  const data = isRecord(row.carrier_data) ? row.carrier_data : {};
  const parcel: AiParcel = {
    c, destinationCountry: text(data.destination_country), serviceName: text(data.service_name),
    // A link's row says whether it is a lookup (`lookup`) without naming the account: parcel_link_view.
    weightKg: data.weight_kg ?? null, lookup: typeof row.lookup === 'boolean' ? row.lookup : 'user_id' in row ? row.user_id === null : false,
    offset: snap.offset * 60,
  };
  // The scans are placed once; what the carrier adds of the parcel itself, with no new scan, is read again.
  const key = JSON.stringify([blob.version, t, parcel.serviceName, parcel.weightKg, parcel.lookup]);
  if (placed.F.has(key)) return placed.F.get(key)!;
  const features = snapshotFeatures(parcel, placed.scans, t, ordinal(snap), snap.hour, blob);
  let F: number[] | null = null;
  if (features) {
    const newest = placed.scans.filter((scan) => scan.ts <= t).at(-1)!;
    F = predictArrival(blob.model, withTargetEncoding(features, statusKey(newest.providerCode, newest.description), blob.model));
  }
  placed.F.set(key, F);
  return F;
}

/** The chance of each day from today, as the model spreads it over the carrier's delivery days. */
function dayStrip(F: readonly number[], delivers: Delivers, snap: number, today: number): PeekAi['days'] {
  const chance = new Map<number, number>();
  F.forEach((p, h) => {
    const day = endDay(delivers, snap, h);
    if (day >= today) chance.set(day, (chance.get(day) ?? 0) + p - (h > 0 ? F[h - 1]! : 0));
  });
  return [...chance].slice(0, 7).map(([day, p]) => ({ day: isoDay(day), p: round2(p) }));
}

/** The share of the carrier's deliveries in each hour from 08:00 to 20:00, from their running share by each hour. */
function hourStrip(shares: readonly number[]): PeekAi['hours'] {
  return shares.slice(0, 13).map((share, index) => ({
    time: `${String(8 + index).padStart(2, '0')}:00`, share: round2((shares[index + 1] ?? share) - (index > 0 ? share : 0)),
  }));
}

/** The step the second sentence of a no-date call names: the depot, customs, the destination country, else the last scan. */
function lastStep(scans: readonly PlacedScan[], dest: string | null, zone: Zone): PeekAi['last'] {
  const at = (scan: PlacedScan) => DateTime.fromSeconds(scan.ts, { zone }).toISO({ suppressMilliseconds: true })!;
  const origin = scans.find((scan) => scan.country)?.country ?? null;
  const abroad = dest !== null && origin !== null && origin !== dest;
  const depot = scans.find((scan) => scanMilestones(scan.stage, scan.description).has('depot'));
  if (depot) return { kind: 'depot', at: at(depot) };
  if (abroad) {
    const customs = scans.find((scan) => scan.stage === 'customs' || scanMilestones(scan.stage, scan.description).has('import'));
    if (customs) return { kind: 'customs', at: at(customs) };
    const reached = scans.find((scan) => scan.country === dest);
    if (reached) return { kind: 'country', at: at(reached), country: dest };
  }
  return { kind: 'scan', at: at(scans.at(-1)!) };
}

/** Whether the carrier's estimate names an hour or a slot, not only a day. */
function namesHour(value: unknown): boolean {
  const time = /\d{2}:\d{2}/.exec(typeof value === 'string' ? value.slice(10) : '');
  return time !== null && !/T00:00(?::00)?(?:\.0+)?(?:Z|[+-]00:?00)?$/.test(String(value));
}

/**
 * What Peek AI says of a stored parcel at `now` (see the module's comment), or null: for an archived parcel, one
 * delivered, returned, waiting at a pickup point or after a delivery attempt by the scans the apps show (`served`), and
 * whenever no hint is strong enough.
 */
export function peekAiFor(row: JsonObject, served: JsonObject, now = new Date(), blob = state.blob): PeekAi | null {
  if (!blob || row.archived_at || NOT_DUE.has(String(row.current_stage))) return null;
  const shown = toParcel(served as unknown as ApiPackageRow);
  const stage = currentEvent(shown.events, shown.returnHeld)?.stage;
  if (stage && NOT_DUE.has(stage)) return null;
  const c = modelCarrier(row);
  const placed = placedScans(row, c);
  const zone = parcelClock(row, placed.scans, blob);
  const local = DateTime.fromJSDate(now, { zone });
  const ai = call(row, c, placed, local, blob);
  if (!ai || !(ai.sure >= MIN_SURE)) return null;
  const ends = endsAt(ai, local);
  return ends ? { ...ai, ends_at: ends } : ai;
}

/** When a call stops holding, with the parcel's offset: so the apps drop it then, whatever their own clock. */
function endsAt(ai: PeekAi, local: DateTime): string | null {
  const day = (iso: string | undefined) => iso ? DateTime.fromISO(iso, { zone: local.zone }) : null;
  const [hour, minute] = (ai.time ?? '').split(':').map(Number);
  // A call on a missed date says the carrier's date was yesterday: it holds that day only, as `call` makes it.
  const end = ai.says === 'not_today' || ai.why === 'missed_date' ? local.startOf('day').plus({ days: 1 })
    : ai.says === 'by_time' ? (ai.time ? local.set({ hour, minute, second: 0, millisecond: 0 }) : null)
    : ai.says === 'sooner' ? day(ai.carrier_day)
    : day(ai.day)?.plus({ days: 1 }) ?? null;
  return end?.isValid ? end.toISO({ suppressMilliseconds: true }) : null;
}

/** When the hour or slot the carrier names ends, or null when it names only a day. */
function slotEnd(value: unknown, zone: Zone): DateTime | null {
  if (typeof value !== 'string' || !namesHour(value)) return null;
  const wall = /^(\d{4}-\d{2}-\d{2})[ T]+(\d{2}:\d{2})(?:[–-](\d{2}:\d{2}))?$/.exec(value.trim());
  const end = wall ? DateTime.fromISO(`${wall[1]}T${wall[3] ?? wall[2]}`, { zone }) : DateTime.fromISO(value.trim(), { setZone: true });
  return end.isValid ? end : null;
}

/** What the hints say at `local`, the moment on the parcel's clock, before how often they were right is checked. */
function call(row: JsonObject, c: string, placed: Placed, local: DateTime, blob: PeekAiBlob): PeekAi | null {
  const nowS = local.toSeconds();
  const past = placed.scans.filter((scan) => scan.ts <= nowS);
  const newest = past.at(-1);
  if (!newest || !past.some((scan) => PHYSICAL_STAGES.has(scan.stage)) || ARRIVAL_STAGES.has(newest.stage)
    || past.some((scan) => scan.stage === 'returned')) return null;
  const zone = local.zone;
  const today = ordinal(local);
  const days = blob.deliveryDays[c]?.length ? blob.deliveryDays[c]! : DEFAULT_DELIVERY_DAYS;
  // The holidays of the country the parcel goes to, else of the one its newest placed scan is in.
  const country = text(isRecord(row.carrier_data) ? row.carrier_data.destination_country : null)
    ?? [...past].reverse().find((scan) => scan.country)?.country ?? null;
  const closed = closedDays(country, [local.year, local.year + 1]);
  const delivers: Delivers = (day) => days.includes(mod(day + 3, 7)) && !closed.has(day);
  const estimate = /^\d{4}-\d{2}-\d{2}/.exec(typeof row.expected_delivery === 'string' ? row.expected_delivery.trim() : '')?.[0];
  const carrierDay = estimate ? dayOf(estimate) : null;
  const checked = Date.parse(String(row.last_synced_at));
  const checkedHours = Number.isFinite(checked) ? (local.toMillis() - checked) / 3_600_000 : Infinity;
  const localIso = (scan: PlacedScan) => DateTime.fromSeconds(scan.ts, { zone }).toISO({ suppressMilliseconds: true })!;
  const hourNow = local.hour + local.minute / 60;
  // Not today never contradicts an hour or slot the carrier names for today before it has passed.
  const slot = slotEnd(row.expected_delivery, zone);
  const slotOpen = slot !== null && slot > local && ordinal(slot.setZone(zone)) === today;

  // Out for delivery today: by the carrier's usual hour, or not today once it is late.
  const out = [...past].reverse().find((scan) => scan.stage === 'out_for_delivery');
  if (out && ordinal(DateTime.fromSeconds(out.ts, { zone })) === today) {
    if (blob.ofdUnreliable.includes(c) || slotOpen) return null;
    const late = blob.lateOut[c] ?? blob.lateOut.default!;
    if (hourNow >= late.from && (newest.stage === 'out_for_delivery' || newest.stage === 'exception')) {
      return { says: 'not_today', why: 'late_out', sure: capped(late.sure), out_since: localIso(out) };
    }
    const rule = blob.byTime[c];
    // Sent out on a day with no hour, it may have gone out after the carrier's usual hour, and has no time to show.
    if (!rule || out.dayAlone || newest.stage !== 'out_for_delivery' || namesHour(row.expected_delivery)) return null;
    const [h, m] = rule.by.split(':').map(Number) as [number, number];
    const bound = h + m / 60;
    const left = DateTime.fromSeconds(out.ts, { zone });
    if (left.hour + left.minute / 60 >= bound || hourNow >= bound) return null;
    return { says: 'by_time', why: 'out_for_delivery', time: rule.by, sure: capped(rule.sure), out_since: localIso(out),
      hours: hourStrip(rule.shares) };
  }

  // The carrier says today and nothing has gone out for delivery by the afternoon.
  if (carrierDay === today) {
    const rule = blob.notToday[c] ?? blob.notToday.default!;
    if (rule.from === null || local.hour < rule.from || checkedHours > blob.claims.recentCheckHours || slotOpen) return null;
    const hours = Object.keys(rule.sure ?? {}).map(Number).filter((hour) => hour <= local.hour);
    const sure = hours.length ? rule.sure![String(Math.max(...hours))]! : null;
    return sure === null ? null : { says: 'not_today', why: 'no_out_for_delivery', carrier_day: isoDay(today), sure: capped(sure) };
  }

  if (checkedHours > FRESH_HOURS) return null;
  // The carrier's day was yesterday, and a scan came in the day since.
  if (carrierDay === today - 1) {
    if (!past.some((scan) => scan.ts > nowS - 86_400)) return null;
    const by = endDay(delivers, today, 1);
    return { says: by <= today + 1 ? 'today_or_tomorrow' : 'by_day', why: 'missed_date', day: isoDay(by),
      carrier_day: isoDay(carrierDay), sure: capped(blob.sure.missed_date!) };
  }
  if (carrierDay !== null && carrierDay < today) return null;

  // The model, at the latest morning or afternoon it was trained at, before the parcel is out for delivery.
  const snap = snapshot(local);
  const F = arrivalChances(row, placed, snap, blob, c);
  if (!F || placed.scans.filter((scan) => scan.ts <= snap.toSeconds()).at(-1)?.stage === 'out_for_delivery') return null;
  const snapDay = ordinal(snap);
  const H = F.length - 1;
  const { tau, byDayWidth, weekTau, soonerLead, soonerTau } = blob.claims;
  const strip = dayStrip(F, delivers, snapDay, today);
  const range = shortestRange(F, tau);

  if (carrierDay !== null) {
    // Sooner than a window of days the carrier gives would only repeat its first day.
    const from = /^\d{4}-\d{2}-\d{2}/.exec(text(isRecord(row.carrier_data) ? row.carrier_data.expected_delivery_from : null) ?? '')?.[0];
    if (from && dayOf(from) < carrierDay) return null;
    const before = daysBetween(delivers, snapDay, carrierDay);
    if (carrierDay - snapDay < soonerLead || before < 1 || F[Math.min(before - 1, H)]! < soonerTau) return null;
    const first = range && range.a >= 1 ? endDay(delivers, snapDay, range.a) : null;
    return { says: 'sooner', why: 'ahead', carrier_day: isoDay(carrierDay), sure: capped(blob.sure.sooner!), days: strip,
      ...first !== null && first > today && first < carrierDay ? { day: isoDay(first) } : {} };
  }

  const dest = text(isRecord(row.carrier_data) ? row.carrier_data.destination_country : null);
  const said = (says: ApiPeekAiSays, day: number) => ({ says, why: 'no_date' as const, day: isoDay(day), sure: capped(blob.sure[says]!),
    last: lastStep(past, dest, zone), days: strip });
  if (range && range.b - range.a <= byDayWidth) {
    const by = endDay(delivers, snapDay, range.b);
    if (by < today) return null;
    // "Today or tomorrow" only when the carrier delivers tomorrow; a call made last night still holds this morning.
    return said(by <= today + 1 && delivers(today + 1) ? 'today_or_tomorrow' : 'by_day', by);
  }
  const sunday = today + 6 - mod(today + 3, 7);
  const snapSunday = snapDay + 6 - mod(snapDay + 3, 7);
  const thisWeek = F[Math.min(daysBetween(delivers, snapDay, snapSunday), H)]!;
  if (mod(snapDay + 3, 7) <= 3 && thisWeek >= weekTau) return said('this_week', snapSunday);
  const nextWeek = F[Math.min(daysBetween(delivers, snapDay, snapSunday + 7), H)]!;
  if (thisWeek < 0.5 && nextWeek >= weekTau) return said(snapSunday + 7 === sunday ? 'this_week' : 'by_end_of_next_week', snapSunday + 7);
  return null;
}
