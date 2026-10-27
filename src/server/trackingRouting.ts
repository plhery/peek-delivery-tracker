import 'server-only';
import { createHash } from 'node:crypto';
import { setTimeout as sleep } from 'node:timers/promises';
import { refusedNumberCode, trackingFailureCode, UNKNOWN_ITEM_REASONS } from './trackingFailure';

import { DateTime } from 'luxon';
import { appInputField, detectCarrierMatch } from '../lib/carriers';
import { activeRequirements, AUTOMATIC_CARRIER_IDS, carrierAdapter, carrierTimezone, requiredRequirements } from './carriers';
import { checksumRejections, fillPartialAnswer, inferStage, isPartialAnswer, normalizeCarrierResult, resultStage, STAGES, type CarrierResult } from 'universal-parcel-scraper';
import { isRecord, type JsonObject } from './types';
import { STORED_EVENT_IDENTITIES } from './supabase';
import { priorityUniversalSource, universalPlan, universalSourceBudget } from 'universal-parcel-scraper';
import type { UniversalSource } from 'universal-parcel-scraper';
import { isCarrierFeedName, isKnownCarrierName, statusMapAnswer } from 'universal-parcel-scraper/app';
import { brandCarrierIds, carrierBrand, carrierIdFromName } from 'universal-parcel-scraper/app';
import { errorType, reportRoutingEvent } from './observability';
import { recordChecksumRejection, recordProviderInput } from './metrics';
import { CarrierError, carrierErrorKind, CARRIER_DEFINITIONS, deliveryHandoff, IndeterminateError, NoHistoryError, retryAfterMsOf, withoutWrittenAhead, writtenAheadClocks } from 'universal-parcel-scraper';
import { captureDirectLocalHistory, directHistoryNumber, directLocalHistory, hasUnresolvedDirectCurrent, hasUnresolvedDirectHistory } from './directLocalHistory';
import { withoutDisputedDelivery } from './disputedDelivery';
import { eventTimestamp, latestResultTime, resultTimezone } from 'universal-parcel-scraper/app';
import type { Recognition, TrackingContext } from 'universal-parcel-scraper/node';
import { MAX_RECOGNITIONS, recognitionCandidates, recognizeAll, settleRecognition } from 'universal-parcel-scraper';
import { BROWSER_RECOGNITION_BUDGET_MS, MAX_BROWSER_RECOGNITIONS } from './browserRecognition';
import { providerCarrier } from './providerCarrier';
import { normalizedLookupCountry } from './lookupCountry';
import manifest from '../../package.json';
import { getRecognitionPriorities } from './recognitionRanking';
import { confirmationLookup } from './trackingHealth';

const HOUR = 3_600_000;
const DAY = 24 * HOUR;
// no_history: a universal provider, or the carrier listing the parcel without scans, answered without any history.
// invalid_input: the source refused the number or an input as one it does not issue, or found several parcels under it.
export type RoutingFailureKind = 'rate_limited' | 'not_found' | 'no_history' | 'input_required' | 'invalid_input' | 'verification' | 'schema' | 'transport';
export interface ProviderHealth {
  acquireTrackingProvider(provider: string): Promise<{ token: string | null; retry_at: string }>;
  finishTrackingProvider(provider: string, token: string, kind: Exclude<RoutingFailureKind, 'no_history' | 'input_required' | 'invalid_input'> | null, retryAfterMs: number, durationMs: number): Promise<void>;
}
export interface RoutedResult {
  correction?: { carrier: string; trackingUrl: string | null; postcode: string | null };
  /** A fresh, unchanged partial answer checked the parcel; its saved fuller history stays. */
  preserveHistory?: true;
  result: CarrierResult;
  sourceCarrierId: string;
  swissPostReady: boolean | null;
  handoffFallbackErrorType: string | null;
  earlierResult?: CarrierResult;
  earlierCarrierId?: string;
  /** The earlier carrier asked again after a handoff: its late scans join the history, not the watermark. */
  earlierFollowUp?: boolean;
}
interface Failure { count: number; retry_at: string; kind: RoutingFailureKind; user_error?: string }
/**
 * Older than the parcel's newest scan; no movement; dated only by a local clock; not dated at
 * all; or behind a provider's delivered or returned stage. Absent: older.
 */
export type SetAsideReason = 'older' | 'no_movement' | 'local_clock' | 'undated' | 'behind_final';
export interface SetAside { reason?: SetAsideReason; checked_at: string; newest?: string; watermark?: string; count: number; new_scans?: number }
export interface RoutingState extends JsonObject {
  version: 1;
  configured_carrier: string;
  preferred_provider?: UniversalSource;
  preferred_number?: string;
  confirmed_carrier?: string;
  confirmed_number?: string;
  confirmed_tracking_url?: string | null;
  confirmed_postcode?: string | null;
  /**
   * The carrier and number last confirmed, kept when the confirmation of a parcel left under
   * unknown is cleared (clearConfirmation). That confirmation was reported and counted:
   * confirming them again is no news. It files nothing, and the next confirmation takes its place.
   */
  cleared_confirmation?: { carrier: string; number: string };
  discovered_carrier?: string;
  last_success_at?: string;
  consecutive_failures: number;
  last_event_at?: string;
  next_check_at?: string;
  direct_retry_at?: string;
  last_probe_at?: string;
  /** Per-candidate schedule of the recognitions that ask carriers the number points to. */
  candidate_probes?: Record<string, { count: number; retry_at: string }>;
  /** A carrier that knows the number but cannot track it without the user's input. */
  input_needed?: { carrier: string; field: string };
  /** The filed carrier refused the delivery postcode stored for it, until it answers. */
  postcode_refused?: { carrier: string };
  /** A carrier the number of a parcel filed under no carrier points to, which needs the user's input before it can be asked (suggestedInput). */
  input_suggested?: { carrier: string; field: 'dpdPostcode' };
  provider_input_needed?: { provider: string; field: 'dpdPostcode' };
  /**
   * When providers last held no fuller history than the filed carrier's partial answer, or no
   * progress beside its label alone (LABEL_WAIT).
   */
  partial_checked_at?: string;
  /** The China Post preview whose fuller provider history was last fetched. The digest binds the lookup inputs and scraper rules too. */
  partial_history?: { fingerprint: string; provider: string; checked_at: string };
  /**
   * The filed carrier's local-clock answer that a provider's history last matched, adding no scan or stage,
   * or only guessing the clock of a scan the carrier dates by its day alone: then the carrier's answer was kept.
   */
  provider_copy?: { carrier: string; number: string; scans: number; stage: string | null; provider: string; checked_at: string; kept?: boolean };
  /**
   * Carriers whose answer a check set aside: why, when, how many checks in a row set that same
   * answer aside, and for one older than the parcel's newest scan, the answer's newest scan and
   * the watermark it lost to. `new_scans` counts the answer's scans the parcel's rows lack, so
   * the review tells an answer that only knows an earlier leg from one that loses scans. Asking
   * the carrier again replaces it, and an answer on local clocks the check then takes leaves nothing.
   */
  set_aside?: Record<string, SetAside>;
  probe_cursor: number;
  discovery_cursor: number;
  failures: Record<string, Failure>;
  /** The scraper release and catalog rules that last routed the parcel (`catalogRules`). */
  rules?: string;
  /**
   * For a parcel filed under no carrier, the carriers recognition could ask about its number
   * under those rules (`recognizers`): a release that adds one checks it again (redetection.ts).
   */
  recognizers?: string[];
  /** The last check that ran a lookup opened a browser: its own or the browser service. */
  browser_check?: true;
  /**
   * When a provider filed the parcel under a carrier with an adapter of its own that the
   * check did not ask: the next run asks it (see the schedule). The next check clears it.
   */
  carrier_named_at?: string;
  /** The carrier without an adapter of its own last reported as a candidate for one: a number counts once. */
  support_opportunity?: string;
}

let rules: string | undefined;
/**
 * The scraper release and what its catalog says about numbers and names, as a
 * short digest: the release's version, each carrier's detection rules, names,
 * whether it has an adapter of its own and whether that adapter's clocks are
 * local. Every release changes it: one can change what an adapter answers with
 * its catalog entry unchanged (Mayi Yinfu's came to date the scans it gave on
 * local clocks).
 */
export function catalogRules(): string {
  rules ??= createHash('sha256').update(JSON.stringify([manifest.dependencies['universal-parcel-scraper'],
    Object.entries(CARRIER_DEFINITIONS).map(([id, definition]) => [id,
      definition.displayName, definition.aliases, definition.detectionRules, definition.tracking.mode, definition.tracking.adapter,
      definition.tracking.localClocks === true])]))
    .digest('base64url').slice(0, 16);
  return rules;
}

/** Carriers whose lookup can open a browser (the catalog's `tracking.browser`). */
const BROWSER_CARRIERS = new Set(Object.entries(CARRIER_DEFINITIONS).filter(([, carrier]) => carrier.tracking.browser).map(([id]) => id));
export function opensBrowser(carrier: string): boolean {
  return BROWSER_CARRIERS.has(carrier);
}

/** The carriers recognition can ask about a number, over HTTP or in a browser. */
export function recognizers(number: string): string[] {
  return [...new Set([...recognitionCandidates(number), ...recognitionCandidates(number, { phase: 'browser' })]
    .map(({ carrier }) => carrier))];
}

/**
 * Whether a scraper release ends a carrier's wait after this failure: its
 * adapter refused the number or could not read the answer, a verdict the
 * release's adapter may not repeat. A carrier whose lookup can open a browser
 * keeps its wait.
 */
export function releaseLapses(carrier: string, failure: { kind?: unknown } | undefined): boolean {
  return directCarrier(carrier) && !opensBrowser(carrier) && (failure?.kind === 'invalid_input' || failure?.kind === 'schema');
}

export function routingState(parcel: JsonObject): RoutingState {
  const data = isRecord(parcel.carrier_data) ? parcel.carrier_data : {};
  const old = isRecord(data.routing) && data.routing.version === 1 ? data.routing : {};
  const state = structuredClone(old) as Partial<RoutingState>;
  const changed = state.configured_carrier !== parcel.carrier;
  return {
    ...state, version: 1, configured_carrier: String(parcel.carrier),
    failures: isRecord(state.failures) ? state.failures : {},
    probe_cursor: Number.isSafeInteger(state.probe_cursor) ? state.probe_cursor! : 0,
    discovery_cursor: Number.isSafeInteger(state.discovery_cursor) ? state.discovery_cursor! : 0,
    consecutive_failures: !changed && Number.isSafeInteger(state.consecutive_failures) && state.consecutive_failures! > 0
      ? Math.min(20, state.consecutive_failures!) : 0,
    ...(changed ? { direct_retry_at: undefined, next_check_at: undefined, browser_check: undefined } : {}),
    // Migration compatibility: an ok fetch is a success; failed attempts are not.
    last_success_at: typeof state.last_success_at === 'string' ? state.last_success_at
      : parcel.sync_status === 'ok' && typeof parcel.last_synced_at === 'string' ? parcel.last_synced_at : undefined,
  };
}

/**
 * Drops the carrier confirmed for a parcel left under unknown, whichever check leaves it there:
 * one the sync completes, or one deferred after that carrier answered not found or without
 * history. A confirmed carrier would stop routing from filing the parcel any other way. The
 * carrier and number it had confirmed stay apart (`cleared_confirmation`).
 */
export function clearConfirmation(routing: JsonObject): void {
  if (typeof routing.confirmed_carrier === 'string' && typeof routing.confirmed_number === 'string') {
    routing.cleared_confirmation = { carrier: routing.confirmed_carrier, number: routing.confirmed_number };
  }
  delete routing.confirmed_carrier; delete routing.confirmed_number;
  delete routing.confirmed_tracking_url; delete routing.confirmed_postcode;
}

export function freshnessWindow(now: Date): number {
  const hour = DateTime.fromJSDate(now, { zone: 'Europe/Zurich' }).hour;
  return hour >= 8 && hour < 22 ? HOUR : 3 * HOUR;
}
/** How often a parcel asks the universal providers: every 15 min by day, hourly overnight. */
function universalSpacing(now: Date): number {
  return freshnessWindow(now) === HOUR ? 15 * 60_000 : HOUR;
}
const millis = (value: unknown): number => typeof value === 'string' && Number.isFinite(Date.parse(value)) ? Date.parse(value) : 0;
const iso = (value: number): string => new Date(value).toISOString();
/**
 * A filed carrier still answering with the parcel's label alone this long after it first listed
 * it may only scan the parcel once it reaches its own leg, while providers follow the legs before
 * it: they are asked too, as for a partial answer, every LABEL_SPACING while none shows progress.
 * A carrier that delivers at home scans most labels within a day; one that takes a parcel over
 * after a border, within days.
 */
const LABEL_WAIT = 2 * DAY;
const LABEL_SPACING = 6 * HOUR;
/** When an answer first listed the parcel: its earliest dated scan or update, else when the parcel was added. 0 when nothing dates it. */
function listedSince(value: RoutedResult, addedAt: unknown): number {
  const zone = resultTimezone(value.sourceCarrierId, value.result);
  const times = [value.result.last_update, ...(value.result.events ?? []).map((event) => event.time)]
    .map((time) => Date.parse(eventTimestamp(time, zone) ?? '')).filter((time) => time > 0);
  return times.length ? Math.min(...times) : millis(addedAt);
}
/**
 * Whether a scan is as new as the parcel's newest. A scan dated to the minute (Sameday's) is as
 * new as anything within that minute: the parcel's newest may be the same scan read to the
 * second, or the moment the parcel was added.
 */
export function asNewAs(scan: number, watermark: number): boolean {
  return scan % 60_000 === 0 ? scan + 60_000 > watermark : scan >= watermark;
}
export function routingFailure(error: unknown): { kind: RoutingFailureKind; retryAfterMs: number } {
  // Errors from the carrier package carry their kind; classify those first so
  // a wrong shipment or malformed payload keeps its hourly retry, and only fall
  // back to status sniffing for errors raised outside the taxonomy.
  const kind = carrierErrorKind(error);
  if (kind !== null) {
    const retryAfterMs = Math.max(0, Math.min(7 * DAY, retryAfterMsOf(error) ?? 0));
    if (kind === 'rate_limited') return { kind: 'rate_limited', retryAfterMs };
    if (kind === 'not_found') return { kind: 'not_found', retryAfterMs: 0 };
    if (kind === 'challenge') return { kind: 'verification', retryAfterMs: 0 };
    if (kind === 'input_required') return { kind: 'input_required', retryAfterMs: 0 };
    // A number the carrier does not issue waits as a parser failure does: no sooner retry can succeed.
    if (kind === 'schema' || kind === 'invalid_input') return { kind, retryAfterMs: 0 };
    // So does one it finds several parcels under, until the owner adds each parcel's own number.
    if (trackingFailureCode(error) === 'carrier:several_parcels') return { kind: 'invalid_input', retryAfterMs: 0 };
    return { kind: 'transport', retryAfterMs };
  }
  let current = error;
  for (let i = 0; i < 8 && current instanceof Error; i++, current = current.cause) {
    const details = current as Error & { status?: number; retryAfterMs?: number };
    if (details.status === 429) return { kind: 'rate_limited', retryAfterMs: Number.isFinite(details.retryAfterMs) ? Math.max(0, Math.min(7 * DAY, details.retryAfterMs!)) : 0 };
    if (details.status === 404) return { kind: 'not_found', retryAfterMs: 0 };
    if (details.status === 401 || details.status === 403 || /Challenge|Verification/.test(current.name)) return { kind: 'verification', retryAfterMs: 0 };
  }
  return { kind: error instanceof TypeError || error instanceof RangeError || error instanceof SyntaxError ? 'schema' : 'transport', retryAfterMs: 0 };
}
/**
 * An adapter's own inconclusive verdict (17TRACK code 400, ParcelsApp NO_DATA,
 * Postal Ninja's empty lookup, a national post's miss) describes the number; an
 * HTTP 5xx describes the provider.
 */
export function answeredWithoutHistory(error: unknown, carrier = false): boolean {
  let current = error;
  for (let i = 0; i < 8 && current instanceof Error; i++, current = current.cause) {
    // A carrier's other inconclusive answers (an outage page, contradictory
    // scans) describe the carrier; only a parcel it lists without scans, or
    // one its tracking does not know yet, is an answer.
    if (current instanceof CarrierError) return current instanceof (carrier ? NoHistoryError : IndeterminateError)
      || (current instanceof IndeterminateError && UNKNOWN_ITEM_REASONS.has(current.reason ?? ''));
  }
  return false;
}
/**
 * Whether a universal provider's postcode request can be about this number:
 * its shape fits a carrier, or a carrier named for the parcel takes a postcode.
 * A number that no carrier issues in that shape was misread, and no postcode
 * answers that.
 */
export function plausibleInputRequest(number: string, carriers: readonly (string | undefined)[] = []): boolean {
  const detected = detectCarrierMatch(number);
  return detected.carrier !== 'unknown' || detected.candidates.length > 0 || carriers.some((carrier) => carrier !== undefined
    && carrier in CARRIER_DEFINITIONS && activeRequirements(carrier, number).some((rule) => rule.field === 'postcode'));
}
/**
 * The carrier to offer for a number no carrier or provider has answered for yet: the first
 * carrier detection lists for it that serves the country it was looked up from, takes the
 * delivery postcode and nothing else for numbers of this shape, and that recognition cannot ask
 * about it without it (Mondial Relay's 8-digit shipments, and Paack's SHEIN numbers, whose
 * postcode is optional because other sources are asked without it). Nothing says that carrier
 * has the parcel.
 */
export function suggestedPostcodeCarrier(number: string, country: string | null): string | undefined {
  if (!country) return undefined;
  const detected = detectCarrierMatch(number);
  if (detected.confidence !== 'low') return undefined;
  const askable = new Set([...recognitionCandidates(number, { countryHint: country }),
    ...recognitionCandidates(number, { countryHint: country, phase: 'browser' })].map(({ carrier }) => carrier));
  return detected.candidates.find((candidate) => AUTOMATIC_CARRIER_IDS.has(candidate) && !askable.has(candidate)
    && (CARRIER_DEFINITIONS[candidate]?.countries ?? []).includes(country)
    && activeRequirements(candidate, number).map(({ field }) => field).join() === 'postcode');
}
/** The carrier to offer the owner of a parcel filed under no carrier that no carrier or provider has answered for yet (suggestedPostcodeCarrier). */
export function suggestedInput(parcel: JsonObject, state: RoutingState): RoutingState['input_suggested'] {
  const metadata = isRecord(parcel.carrier_data) ? parcel.carrier_data : {};
  const country = normalizedLookupCountry(metadata.lookup_country_hint);
  if (String(parcel.carrier) !== 'unknown' || !country || ['delivered', 'returned'].includes(String(parcel.current_stage))
    || metadata.original_carrier || state.last_event_at || state.confirmed_carrier || state.discovered_carrier
    || state.input_needed) return undefined;
  const carrier = suggestedPostcodeCarrier(String(parcel.tracking_number ?? ''), country);
  return carrier ? { carrier, field: 'dpdPostcode' } : undefined;
}
/**
 * The universal providers a recheck of a number nobody has answered may ask, of
 * `ordered`, the check's own order; it contacts one of them at most. The first carrier its
 * detection names or lists with coverage evidence gives those that had history for it, fuller
 * first. A number pointing to no such carrier, or to one no provider had history for, gets
 * the HTTP providers the Add preflight asks, ParcelsApp and Ship24, in the check's order:
 * the browser-service ones cost more, and nothing points to them.
 */
export function recheckSources(number: string, ordered: readonly UniversalSource[], enablePostalNinja?: boolean): UniversalSource[] {
  const detected = detectCarrierMatch(number);
  const plan = universalPlan({ carriers: detected.confidence === 'high' ? [detected.carrier] : detected.candidates,
    trackingNumber: number, enablePostalNinja });
  const covered = plan.carrier ? plan.sources.filter((source) => ordered.includes(source) && ['full', 'partial'].includes(plan.tier(source))) : [];
  return covered.length ? covered : ordered.filter((source) => source === 'ParcelsApp' || source === 'Ship24');
}
/** A parcel carrier's own zone, when its catalog names one. */
function carrierZone(carrier: string): string | null {
  try {
    const zone = carrierTimezone(carrier);
    return zone === 'UTC' ? null : zone;
  } catch { return null; }
}
function usable(value: CarrierResult): boolean {
  return Boolean(value.events?.length || value.status && !['unknown', 'pending'].includes(value.status)
    || value.current_stage && value.current_stage !== 'pending');
}
const PROGRESS_STAGES = new Set<string>(STAGES.filter((stage) => !['pending', 'registered'].includes(stage)));
/** Movement worth retaining a provider for; registration alone only establishes the label. */
export function hasRoutingProgress(result: CarrierResult): boolean {
  return PROGRESS_STAGES.has(resultStage(result) ?? '') || (result.events ?? []).some((event) =>
    PROGRESS_STAGES.has(event.stage ?? '') || PROGRESS_STAGES.has(inferStage(event.description ?? '', 'pending')));
}
/**
 * The saved stage as today's scraper reads the scans the parcel keeps, for the progress an answer
 * must show. A row only a universal provider wrote is staged by its wording, which a scraper fix
 * can re-read as an earlier stage (ParcelsApp's relay of ePost Global's "in transit to the
 * processing center" is its label). Once no row reads past registration, neither does the parcel:
 * an answer without progress takes nothing back. A parcel loaded without its rows, whose saved
 * progress no row showed, or that is delivered, returned or held by an exception keeps its stage.
 */
export function rereadStage(parcel: JsonObject): string {
  const saved = String(parcel.current_stage ?? 'pending');
  const stored = parcel[STORED_EVENT_IDENTITIES];
  if (!PROGRESS_STAGES.has(saved) || ['delivered', 'returned', 'exception'].includes(saved) || !Array.isArray(stored)) return saved;
  const rows = stored.filter(isRecord);
  const read = rows.map((row) => typeof row.universal_provider === 'string'
    ? inferStage(String(row.description ?? ''), String(row.stage ?? 'pending')) : String(row.stage ?? 'pending'));
  if (!rows.some((row) => PROGRESS_STAGES.has(String(row.stage ?? ''))) || read.some((stage) => PROGRESS_STAGES.has(stage))) return saved;
  return read.includes('registered') ? 'registered' : 'pending';
}
/** What a carrier's local-clock answer shows: its scans and its stage. */
function localAnswer(result: CarrierResult): { scans: number; stage: string | null } {
  return { scans: result.events?.length ?? 0, stage: resultStage(result) };
}
/**
 * Whether a provider dates every scan of a carrier's local-clock answer: a scan of the same
 * stage at the instant the scan's local time gives in the carrier's catalog zone, to the
 * minute, or an hour off (SEUR's Canary Islands run an hour behind Madrid). Only provider
 * times with an offset count; a carrier the catalog places in no zone has none to read.
 */
function datesEveryScan(carrier: string, answer: CarrierResult, provider: CarrierResult): boolean {
  const zone = carrierZone(carrier);
  const scans = answer.events ?? [];
  const minute = (time: unknown, timezone?: string) => Math.floor(Date.parse(eventTimestamp(time, timezone) ?? '') / 60_000);
  const stage = (event: { stage?: string; description?: string }) => event.stage ?? inferStage(event.description ?? '', 'pending');
  const dated = (provider.events ?? []).filter((event) => typeof event.time === 'string' && /(?:Z|[+-]\d{2}:?\d{2})$/i.test(event.time));
  return zone !== null && scans.length > 0 && scans.every((scan) => {
    const local = minute(scan.local_time ?? scan.time, zone);
    return dated.some((event) => stage(event) === stage(scan) && [0, 60].includes(Math.abs(minute(event.time) - local)));
  });
}
const DAY_ALONE = /^\d{4}-\d{2}-\d{2}$/;
const offsetTime = (time: unknown): time is string => typeof time === 'string' && /(?:Z|[+-]\d{2}:?\d{2})$/i.test(time);
/**
 * Whether a provider's history is a carrier's answer whose scans are dated, or dated by their day
 * alone, with nothing more than a guess at the clock of those days: each of the provider's scans
 * is at an instant the carrier gives, to the minute, at midnight of one of those days (on the
 * carrier's catalog clock, or UTC), or itself dated by one of those days alone.
 */
function guessesDayClocks(carrier: string, answer: CarrierResult, provider: CarrierResult): boolean {
  const zone = carrierZone(carrier) ?? 'UTC';
  const scans = answer.events ?? [];
  const days = new Set(scans.flatMap((scan) => !offsetTime(scan.time) && DAY_ALONE.test(scan.local_time ?? '') ? [scan.local_time!] : []));
  const instants = new Set(scans.flatMap((scan) => offsetTime(scan.time) ? [Math.floor(Date.parse(scan.time) / 60_000)] : []));
  const events = provider.events ?? [];
  if (!days.size || events.length !== scans.length
    || scans.some((scan) => !offsetTime(scan.time) && !DAY_ALONE.test(scan.local_time ?? ''))) return false;
  return events.every((event) => {
    if (!offsetTime(event.time)) return DAY_ALONE.test(event.local_time ?? '') && days.has(event.local_time!);
    if (instants.has(Math.floor(Date.parse(event.time) / 60_000))) return true;
    return [zone, 'UTC'].some((clock) => {
      const at = DateTime.fromISO(event.time as string, { zone: clock });
      return at.isValid && at.toFormat('HH:mm:ss') === '00:00:00' && days.has(at.toISODate()!);
    });
  });
}
/** The newest instant of a routed result, read in its source's zone exactly as its events are persisted. */
function latest(value: RoutedResult): number {
  return latestResultTime(value.result, value.sourceCarrierId);
}
// Numbers are reused, and carriers' number spaces overlap (a 12-digit Yamato
// number can be an old FedEx parcel). A history that ended three months before
// the parcel was added, without a delivery or return, is the number's earlier
// parcel, whichever source gives it and whichever carrier it names: a seller's
// label can wait a month for the parcel, and an old delivery may be a parcel
// looked up again. For a parcel filed under a specific carrier, so is a
// universal history that ended a month before and names only other catalog carriers.
const OLD_LABEL_GAP = 90 * DAY;
const OTHER_CARRIER_GAP = 30 * DAY;
/**
 * Shipping platforms a shop books other carriers through. A provider can name one beside the
 * parcel's carrier, or in its place, but no catalog entry or adapter of its own could track it.
 */
const SHIPPING_PLATFORMS: ReadonlySet<string> = new Set(['easyship', 'packlink', 'sendcloud', 'shiprocket']);
function isShippingPlatformName(name: string): boolean {
  return SHIPPING_PLATFORMS.has(name.toLowerCase().replace(/[^a-z0-9]/g, ''));
}
/** Whether a reported name can mean this carrier: its id, a brand network of it, or a name the catalog doesn't know. */
function mayNameCarrier(name: string, carrier: string): boolean {
  const id = carrierIdFromName(name);
  if (id) return id === carrier;
  const networks = brandCarrierIds(name);
  if (networks.length) return networks.includes(carrier);
  const brand = carrierBrand(carrier);
  if (brand && name.toLowerCase().replace(/[^a-z0-9]/g, '').startsWith(brand)) return true;
  return !isKnownCarrierName(name);
}
/**
 * The newest scan of the answers, read closely enough to tell an age in months: a wall clock
 * without an offset reads as UTC, and so does a scan an adapter dates on the carrier's local
 * clock alone (`local_time`), which `latestResultTime` leaves out. 0 when nothing is dated.
 */
function newestScan(results: readonly (CarrierResult | undefined)[]): number {
  return Math.max(0, ...results.flatMap((result) => result ? [latestResultTime(result, 'unknown'),
    ...[result.last_update_local, ...(result.events ?? []).map((event) => event.local_time)]
      .map((time) => Date.parse(eventTimestamp(time) ?? '') || 0)] : []));
}
/** Whether an answer reached a delivery or a return. */
function finished(result: CarrierResult): boolean {
  return [resultStage(result), ...(result.events ?? []).map((event) => event.stage)]
    .some((stage) => stage === 'delivered' || stage === 'returned');
}
/**
 * Whether an answer is a reused number's earlier parcel: its newest scan, a handoff's earlier
 * leg included, ended three months before the parcel was added, and it never reached a
 * delivery or return. An answer without a date is no older parcel.
 */
function oldLabel(addedAt: unknown, ...results: (CarrierResult | undefined)[]): boolean {
  const newest = newestScan(results);
  return newest > 0 && newest < millis(addedAt) - OLD_LABEL_GAP
    && !results.some((result) => result && finished(result));
}
/** Whether a universal history is the number's earlier parcel, not the one filed under this carrier at this time. */
export function foreignHistory(result: CarrierResult, carrier: string, addedAt: unknown): boolean {
  if (oldLabel(addedAt, result)) return true;
  if (carrier === 'unknown' || carrier === 'intl-post') return false;
  const names = Array.isArray(result.reported_carriers)
    ? result.reported_carriers.filter((name): name is string => typeof name === 'string') : [];
  if (!names.length || names.some((name) => mayNameCarrier(name, carrier))) return false;
  const newest = newestScan([result]);
  return newest > 0 && newest < millis(addedAt) - OTHER_CARRIER_GAP;
}
/** A row Peek writes itself (`app:` source), such as "Tracking added". */
const ownRow = (row: JsonObject) => String(row.provider_event_id ?? '').startsWith('app:');
/**
 * The stored rows of a parcel that are all a reused number's earlier parcel, as `oldLabel`
 * reads an answer today: the parcel's stage and its rows, other than Peek's own, whose newest
 * scan ended three months before the parcel was added, none of them a delivery or a return. A
 * release or an Add preflight before that rule stored them. The provider ids of those rows;
 * null when the rows were not loaded, when they are not all that parcel's, or for a handoff.
 * A scan a carrier stops listing is not one: the parcel's own scans are not that much older
 * than the parcel, and they stay (`scans_withdrawn`).
 */
export function earlierParcelRows(parcel: JsonObject): string[] | null {
  const stored = parcel[STORED_EVENT_IDENTITIES];
  const data = isRecord(parcel.carrier_data) ? parcel.carrier_data : {};
  if (!Array.isArray(stored) || data.original_carrier) return null;
  const rows = stored.filter(isRecord).filter((row) => !ownRow(row));
  if (!rows.length || !rows.every((row) => typeof row.provider_event_id === 'string')) return null;
  const answer: CarrierResult = { status: 'pending', current_stage: String(parcel.current_stage ?? 'pending') as CarrierResult['current_stage'],
    last_update: null, events: rows.map((row) => ({ description: String(row.description ?? ''), stage: String(row.stage ?? 'pending'),
      // A stage change a check stored when it saw it is dated by that check, not by the carrier.
      ...(row.observed_without_provider_timestamp === true ? {} : { time: String(row.occurred_at ?? '') }),
      ...(typeof row.local_time === 'string' ? { local_time: row.local_time } : {}) })) };
  return oldLabel(parcel.created_at, answer) ? rows.map((row) => String(row.provider_event_id)) : null;
}
/** What a parcel keeps of its carrier data once it forgets a reused number's earlier parcel: what the owner gave and the number's cooldowns. */
const KEPT_WITHOUT_EARLIER_PARCEL = ['lookup_country_hint', 'postcode_country', 'universal_input', 'add_recognition_pending', 'delivery_probe', 'earlier_checked_at'];
/** Routing a reused number's earlier parcel taught: where its answers came from, its watermark and what filed or confirmed it. */
const EARLIER_PARCEL_ROUTING = ['last_event_at', 'last_success_at', 'discovered_carrier', 'preferred_provider', 'preferred_number',
  'reported_carriers_seen', 'provider_copy', 'set_aside', 'partial_history', 'partial_checked_at', 'carrier_named_at', 'input_suggested'];
/**
 * The parcel as if it had never stored a reused number's earlier parcel (`earlierParcelRows`):
 * no stage, summary, estimate or rows of that parcel, nor the routing it taught, so the check
 * routes it as a number nobody has seen (the postcode it may suggest, recognition, discovery).
 * The cooldowns the number met stay.
 */
export function withoutEarlierParcel(parcel: JsonObject): JsonObject {
  const data = isRecord(parcel.carrier_data) ? parcel.carrier_data : {};
  const routing = routingState(parcel);
  for (const key of EARLIER_PARCEL_ROUTING) delete routing[key];
  clearConfirmation(routing);
  const stored = parcel[STORED_EVENT_IDENTITIES];
  return { ...parcel, current_stage: 'pending', last_status_text: null, expected_delivery: null,
    carrier_data: { ...Object.fromEntries(KEPT_WITHOUT_EARLIER_PARCEL.filter((key) => data[key] !== undefined).map((key) => [key, data[key]])), routing },
    ...(Array.isArray(stored) ? { [STORED_EVENT_IDENTITIES]: stored.filter((row) => isRecord(row) && ownRow(row)) } : {}) };
}
/** Whether a carrier has an adapter of its own for the router to ask. */
export function directCarrier(carrier: string): boolean {
  return AUTOMATIC_CARRIER_IDS.has(carrier) && carrierAdapter(carrier) !== 'universal';
}
/**
 * Whether detection alone names this carrier for the number. Correcting a
 * parcel to it, such as one filed before the rule existed, is the designed
 * outcome and shows no gap in the detection rules.
 */
export function detectionNames(number: string, carrier: string): boolean {
  const detected = detectCarrierMatch(number);
  return detected.confidence === 'high' && detected.carrier === carrier;
}
/**
 * Whether every coded scan of an answer, which has one at least, carries a code the
 * filed carrier's own status map stages from the code alone: the scans are that
 * carrier's, served by a tracker its group shares, as Correos's localizador lists
 * Correos Express's. A carrier sharing the filed one's adapter reads the same map,
 * which proves nothing.
 */
export function filedCarrierCodes(declared: string, carrier: string, result: CarrierResult): boolean {
  if (!directCarrier(declared) || carrierAdapter(declared) === carrierAdapter(carrier)) return false;
  const codes = (result.events ?? []).flatMap((event) => event.provider_code ? [event.provider_code] : []);
  // A wording no map reads leaves the code alone to stage the scan.
  return codes.length > 0 && codes.every((code) => statusMapAnswer({ carrier: declared, providerCode: code, description: '-' }).kind === 'mapped');
}
/**
 * Whether detection names this carrier for the number, prefers it, or lists it
 * first among the carriers recognition asks for the lookup country. In each
 * case the parcel turning out to be that carrier's shows no gap in the
 * detection rules, so no alert is raised.
 */
export function detectionExpects(number: string, carrier: string, countryHint?: string | null): boolean {
  return detectionNames(number, carrier) || detectCarrierMatch(number).preferred[0] === carrier
    || recognitionCandidates(number, { countryHint })[0]?.carrier === carrier;
}

const CANDIDATE_PROBE_WINDOW = 30 * DAY;
/** Every recognition answer a sync waits for; later answers are ignored. */
const RECOGNITION_BUDGET_MS = 15_000;
/**
 * A provider busy for no longer than this is only spaced after another check
 * (5 s after a healthy call): it is waited for, at most twice per provider.
 */
const PACING_WAIT_MS = 6_000;
const PACING_ROUNDS = 2;

export class RoutingDeferred extends Error {
  /** `attempted` counts providers actually contacted; zero means every tier was still cooling down. */
  constructor(readonly routing: RoutingState, readonly stale: boolean, readonly attempted = 0) {
    super(stale ? 'Tracking providers are temporarily unavailable. Previous progress has been kept; another check is scheduled.'
      : 'Previous tracking has been kept; another check is scheduled.');
    // Ends in "Error" so the audit trail records it instead of a bare "Error".
    this.name = 'RoutingDeferredError';
  }
}

/** Per-parcel affinity lives in carrier_data; provider protection is shared in Postgres. */
export class TrackingRouter {
  constructor(readonly options: {
    direct: (parcel: JsonObject, carrier: string) => Promise<RoutedResult>;
    /**
     * The delivery carrier a provider's answer names for a parcel filed under `carrier`, asked as
     * the named partner of a carrier's own answer is (fetchResult): its result once it takes the
     * parcel over, else the provider's answer with the probe's marker.
     */
    handoff?: (parcel: JsonObject, carrier: string, origin: CarrierResult) => Promise<RoutedResult>;
    // postcode is the parcel's stored delivery postcode, if the user supplied
    // one; ParcelsApp submits it with its direct lookup.
    // timezone is the parcel carrier's catalog zone; when that is UTC, the zone
    // of the carrier confirmed for the same number, else null.
    // carrier is the catalog carrier a direct lookup confirmed for the number,
    // else the one it is filed under (a delivery leg's own for its number),
    // null when unknown: 17TRACK asks about it when its own detection misses.
    // A hint, never proof of the carrier.
    universal: (source: UniversalSource, number: string, timeoutMs: number, postcode: string | null, timezone: string | null, carrier: string | null) => Promise<CarrierResult>;
    health: ProviderHealth;
    preflightInputNeeded?: (number: string) => { provider: string; field: 'dpdPostcode' } | undefined;
    takePrefetchedUniversal?: (source: UniversalSource, number: string, postcode: string | null) => CarrierResult | undefined;
    /**
     * The answer another check of the same run already got, or awaits, for
     * exactly this lookup. Reusing it contacts nobody, so it takes no lease.
     */
    reusedUniversal?: (source: UniversalSource, number: string, postcode: string | null, timezone: string | null, carrier: string | null) => Promise<CarrierResult> | undefined;
    /** A carrier's cheap check of whether it knows a number; without it, no recognition runs. */
    recognize?: (carrier: string, number: string, context?: TrackingContext) => Promise<Recognition>;
    recognizeBrowser?: (carrier: string, number: string, context?: TrackingContext, previousError?: unknown) => Promise<Recognition>;
    /** Whether a carrier's adapter refuses the number as one it does not issue, without a request. */
    refuses?: (carrier: string, number: string) => Promise<boolean>;
    /**
     * The watermark without what a universal provider's copies of this
     * answer's own scans, read in the wrong zone, add to it: the parcel's
     * stored rows, and `provider`'s answer from this check.
     */
    withoutCopies?: (watermark: number, value: RoutedResult, provider?: CarrierResult) => number;
    /** How many of an answer's scans the parcel's stored rows lack; undefined when they were not loaded. */
    newScans?: (value: RoutedResult) => number | undefined;
    /**
     * For a number nobody has seen, when its hourly checks end (unseenLookup.ts), as epoch
     * milliseconds: the waits it asks again after end by the last of those checks.
     */
    hourlyUntil?: number;
    now?: () => Date;
    /** Waits out a provider's spacing; tests replace the timer. */
    wait?: (ms: number, signal?: AbortSignal) => Promise<unknown>;
    enablePostalNinja?: boolean;
  }) {}

  /**
   * `sparse`: a recheck nobody asked for of a number nobody has answered, which asks the
   * carriers as usual and contacts one universal provider at most (recheckSources).
   */
  async fetch(parcel: JsonObject, scheduled: boolean, signal?: AbortSignal, addition = false, sparse = false): Promise<RoutedResult> {
    const now = () => this.options.now?.() ?? new Date();
    const state = routingState(parcel);
    // Last routed under another scraper release, whose adapters may have answered otherwise.
    const released = state.rules !== catalogRules();
    state.rules = catalogRules();
    if (released) {
      for (const [carrier, failure] of Object.entries(state.failures)) {
        if (releaseLapses(carrier, failure)) failure.retry_at = now().toISOString();
      }
    }
    delete state.carrier_named_at;
    const previousFailures = state.consecutive_failures;
    const declared = String(parcel.carrier);
    const number = String(parcel.tracking_number ?? '');
    const postcode = String(parcel.dpd_postcode ?? '').trim();
    if (state.postcode_refused?.carrier !== declared || !postcode) delete state.postcode_refused;
    if (declared === 'unknown' || declared === 'intl-post') state.recognizers = recognizers(number);
    else delete state.recognizers;
    const metadata = isRecord(parcel.carrier_data) ? parcel.carrier_data : {};
    const countryHint = normalizedLookupCountry(metadata.lookup_country_hint);
    const suggested = suggestedInput(parcel, state);
    if (suggested) state.input_suggested = suggested;
    else delete state.input_suggested;
    let localDirectFallback: { value: RoutedResult; carrier: string } | undefined;
    let localHistory: JsonObject | undefined;
    // The filed carrier's status alone, part of its history, or its label alone after LABEL_WAIT, while
    // providers are asked for the rest.
    let partialDirect: { value: RoutedResult; carrier: string; label?: true } | undefined;
    const partialHistoryKey = (value: RoutedResult): string | undefined => declared === 'china-post'
      && value.sourceCarrierId === declared && !metadata.original_carrier && isPartialAnswer(value.result)
      && hasRoutingProgress(value.result)
      ? createHash('sha256').update(JSON.stringify([state.rules, declared, number, parcel.tracking_url,
        parcel.dpd_postcode, metadata.universal_input, value.result])).digest('base64url') : undefined;
    // Carriers asked in this check whose own answer for the number moved but had only
    // local clocks: the answer, the local history it brought, and how to confirm the carrier.
    const localAnswers = new Map<string, { value: RoutedResult; history: JsonObject | undefined; confirm: () => void }>();
    // The carrier this check's recognition settled on alone.
    let recognized: string | undefined;
    // Asked again, or its answer on local clocks taken, a carrier has nothing set aside.
    const clearSetAside = (carrier: string) => {
      if (!state.set_aside?.[carrier]) return;
      delete state.set_aside[carrier];
      if (!Object.keys(state.set_aside).length) delete state.set_aside;
    };
    const universalNumber = metadata.original_carrier && metadata.active_tracking_carrier
      && typeof metadata.active_tracking_number === 'string' ? metadata.active_tracking_number : number;
    // The owner's answer to a provider's postcode request, for this number only.
    const providerInput = isRecord(metadata.universal_input) && metadata.universal_input.number === universalNumber
      && typeof metadata.universal_input.postcode === 'string' ? metadata.universal_input.postcode : null;
    // The postcode every universal provider receives.
    const providerPostcode = providerInput ?? (typeof parcel.dpd_postcode === 'string' ? parcel.dpd_postcode : null);
    if (state.preferred_number && state.preferred_number !== universalNumber) state.preferred_provider = undefined;
    // The delivery leg's own carrier when its number is the one looked up.
    const universalCarrier = universalNumber !== number && typeof metadata.active_tracking_carrier === 'string'
      ? metadata.active_tracking_carrier : declared;
    const inputPlausible = plausibleInputRequest(universalNumber, [universalCarrier, state.discovered_carrier,
      state.confirmed_number === universalNumber ? state.confirmed_carrier : undefined]);
    // A request saved before it was found implausible stops asking the owner.
    if (!inputPlausible) delete state.provider_input_needed;
    else if (!metadata.universal_input && !state.provider_input_needed) {
      state.provider_input_needed = this.options.preflightInputNeeded?.(universalNumber);
      if (state.provider_input_needed) recordProviderInput(state.provider_input_needed.provider, 'asked');
    }
    // Ordered by that carrier's coverage evidence, else by the carrier confirmed
    // or discovered for the number, else the default order.
    const plan = universalPlan({
      carriers: [universalCarrier, state.confirmed_number === universalNumber ? state.confirmed_carrier : undefined, state.discovered_carrier],
      trackingNumber: universalNumber, enablePostalNinja: this.options.enablePostalNinja,
    });
    const sources = plan.sources;
    // A provider that asked for a postcode the parcel still lacks would only ask
    // again: scheduled checks leave it until a postcode is given.
    const awaitsInput = (provider: string) => sources.includes(provider as UniversalSource)
      && state.failures[provider]?.kind === 'input_required' && !providerPostcode;
    const prefetchedUniversal = new Map<UniversalSource, CarrierResult>();
    // Show an Add preflight's history immediately; speculative confirmation can wait for the next check.
    if (declared === 'unknown' && !state.confirmed_carrier && !metadata.original_carrier
      && !parcel.dpd_postcode && !metadata.universal_input && String(parcel.current_stage ?? 'pending') === 'pending') {
      for (const source of sources) {
        const result = this.options.takePrefetchedUniversal?.(source, universalNumber, null);
        if (result && usable(result)) { prefetchedUniversal.set(source, result); break; }
      }
    }
    // Sparse postal fallback must never become sticky, including saved state.
    if (state.preferred_provider === 'UPU') state.preferred_provider = undefined;
    const recent = () => millis(state.last_success_at) > 0 && now().getTime() - millis(state.last_success_at) < freshnessWindow(now());
    const report = (code: string, provider: string, kind?: string, error?: unknown) => reportRoutingEvent(code, {
      carrier: declared, provider, category: kind, trackingNumber: number,
      ...(error ? { errorClass: errorType(error), error } : {}),
    });
    // A carrier tracked through a provider for want of an adapter is reported once per number,
    // not on every check that tracks it.
    const opportunity = (carrier: string) => {
      if (state.support_opportunity === carrier) return;
      state.support_opportunity = carrier;
      report('direct_support_opportunity', carrier);
    };
    // The carrier confirmed for the number before the sync cleared it: confirming it again is no news.
    const confirmedBefore = (carrier: string) => state.cleared_confirmation?.carrier === carrier
      && state.cleared_confirmation.number === number;
    // While a number nobody has seen is checked hourly, a wait after a carrier's "not found" or
    // an answer without history that would outlast those checks ends an hour before they do,
    // half an hour after the ask at the soonest: the last hourly check asks again before the
    // checks slow down, or a lookup leaves the schedule.
    const beforeHourlyEnd = (retryAt: number) => {
      const end = this.options.hourlyUntil;
      const at = now().getTime();
      return end === undefined || at >= end - HOUR ? retryAt : Math.min(retryAt, Math.max(end - HOUR, at + HOUR / 2));
    };
    const fail = (provider: string, error: unknown, universalSource = false): Failure => {
      const classified = routingFailure(error);
      const kind = classified.kind === 'transport' && answeredWithoutHistory(error, !universalSource)
        ? 'no_history' : classified.kind;
      const { retryAfterMs } = classified;
      const count = Math.min(20, (state.failures[provider]?.count ?? 0) + 1);
      // A provider's "not found" holds for a day. A carrier often doesn't know a
      // label before its first scan, so its own "not found" is asked again within hours.
      const daily = kind === 'not_found' && universalSource;
      // A carrier's bot check comes and goes, as a lost connection does: its own adapter is asked again
      // as soon, and the wait still doubles while every read meets one. A provider's refusal holds an hour.
      const hourly = ['verification', 'schema', 'invalid_input', 'not_found'].includes(kind)
        && !(kind === 'verification' && !universalSource && carrierErrorKind(error) === 'challenge');
      const base = daily ? DAY : hourly ? HOUR : 15 * 60_000;
      const userError = trackingFailureCode(error);
      const retryAt = now().getTime() + Math.max(retryAfterMs, Math.min(daily ? DAY : 6 * HOUR, base * 2 ** (count - 1)));
      const unseen = kind === 'no_history' || (kind === 'not_found' && !daily);
      const failure = { count, kind, ...(userError ? { user_error: userError } : {}),
        retry_at: iso(unseen ? Math.max(now().getTime() + retryAfterMs, beforeHourlyEnd(retryAt)) : retryAt) };
      state.failures[provider] = failure;
      if (universalSource && kind === 'input_required') {
        // Counted when a parcel starts asking; a supplied postcode that is refused counts as still_required.
        if (!state.provider_input_needed && !providerInput) recordProviderInput(provider, 'asked');
        state.provider_input_needed = { provider, field: 'dpdPostcode' };
      }
      // The filed carrier asks for the delivery postcode it lists for the number (PostNL's for a 3S
      // parcel sent within the Netherlands): its owner is offered it while other sources track the parcel.
      if (!universalSource && kind === 'input_required' && provider === declared && !parcel.dpd_postcode
        && activeRequirements(provider, number).some(({ field }) => field === 'postcode')) {
        if (state.input_needed?.carrier !== provider) report('carrier_input_needed', provider);
        state.input_needed = { carrier: provider, field: 'dpdPostcode' };
      }
      // It refuses the postcode stored for it: it asks for it again, finds it invalid, or finds no parcel
      // with it while the parcel is moving (PostNL's domestic tracker answers a wrong postcode as an unknown
      // barcode). The owner is told, as DPD's own flag tells them.
      if (!universalSource && provider === declared && postcode
        && activeRequirements(provider, number).some(({ field }) => field === 'postcode')
        && (kind === 'input_required' || kind === 'invalid_input'
          || ((kind === 'not_found' || kind === 'no_history') && !['pending', 'registered'].includes(String(parcel.current_stage ?? 'pending'))))) {
        state.postcode_refused = { carrier: provider };
      }
      // Called before another provider is attempted, including recovered failures.
      report(kind === 'input_required' ? 'carrier_input_required' : 'provider_failed', provider, kind, error);
      return failure;
    };
    // A carrier that hands the parcel to the filed one under this same number,
    // such as a customs agent before the local courier, is an earlier leg of
    // the parcel, not a correction. A handoff the lookup already made counts too.
    const earlierLeg = (carrier: string, value: RoutedResult) => {
      if (carrier === declared) return false;
      const partner = deliveryHandoff(carrier, number, value.result);
      return value.sourceCarrierId === declared || (partner?.carrier === declared && partner.number === number);
    };
    const persistResult = (value: RoutedResult, provider: string, withoutProgress = false): RoutedResult => {
      if (state.failures[provider]) report('provider_recovered', provider);
      delete state.failures[provider];
      // A carrier tracks the parcel directly: nothing is left to ask the user.
      if (directCarrier(provider)) delete state.input_needed;
      if (provider === declared) delete state.postcode_refused;
      if (!withoutProgress) delete state.provider_input_needed;
      if (!withoutProgress) state.last_success_at = now().toISOString();
      // A thin answer cannot reset a universal-only parcel's missed-check
      // streak. Count this check once even if a later rate limit deferred it.
      state.consecutive_failures = withoutProgress && !directCarrier(declared)
        ? Math.min(20, previousFailures + 1) : 0;
      // A future-dated scan must not make every later real update look older.
      const eventTime = Math.min(now().getTime(), Math.max(latest(value), value.earlierResult && !value.earlierFollowUp
        ? latestResultTime(value.earlierResult, value.earlierCarrierId ?? value.sourceCarrierId) : 0));
      state.last_event_at = eventTime && !withoutProgress && !value.preserveHistory && value.result.direct_local_fallback !== true ? iso(eventTime) : state.last_event_at;
      // Universal checks are deliberately less frequent than direct in-transit polls.
      state.next_check_at = sources.includes(provider as UniversalSource)
        ? iso(now().getTime() + universalSpacing(now())) : undefined;
      // A correction changes the selected carrier, not merely its display source.
      // Delivery-leg handoffs remain separate from correcting an incorrect label.
      // A carrier that answered for this number and handed the parcel on in the
      // same lookup is the parcel's carrier, the delivery carrier its last leg.
      const answeredFirst = value.earlierCarrierId === provider && !value.earlierFollowUp;
      // A carrier only a provider named, not the number's shape, is no correction when
      // the scans it answered with carry the filed carrier's codes.
      const filedScans = provider === state.discovered_carrier && !detectionNames(number, provider)
        && filedCarrierCodes(declared, provider, value.result);
      const swap = provider !== declared && directCarrier(provider) && (value.sourceCarrierId === provider || answeredFirst)
        && state.confirmed_number === number && !metadata.original_carrier && !earlierLeg(provider, value) && !filedScans;
      if (swap) {
        state.configured_carrier = provider;
        value.correction = { carrier: provider, trackingUrl: state.confirmed_tracking_url ?? null,
          postcode: state.confirmed_postcode ?? null };
      }
      const unnamed = declared === 'unknown' && !withoutProgress && !state.confirmed_carrier
        && !metadata.original_carrier && universalNumber === number && sources.includes(provider as UniversalSource);
      // A provider can name an unknown parcel without pretending the carrier answered,
      // but not a carrier whose adapter refuses the number.
      const identified = unnamed ? providerCarrier(value.result, number) : undefined;
      const providerNamed = identified && state.discovered_carrier === identified
        && !refusedNumberCode(state.failures[identified]?.user_error) ? identified : undefined;
      // The carrier detection names, or else the one this provider names, answered
      // too, with local clocks only: a provider dating the progress files the
      // parcel under it, where a parcel added today would start, and links stay
      // with that carrier.
      const answered = unnamed && hasRoutingProgress(value.result)
        ? [...localAnswers.keys()].find((carrier) => detectionNames(number, carrier))
          ?? (providerNamed && localAnswers.has(providerNamed) ? providerNamed : undefined)
        : undefined;
      // Recognition alone found the carrier: its moving answer files the parcel once this
      // provider dates every scan of it, confirmed as a probe's dated answer would be. The
      // provider need name no carrier (Postal Ninja names none).
      const recognizedAnswer = unnamed && !answered && !providerNamed && recognized && hasRoutingProgress(value.result)
        ? localAnswers.get(recognized) : undefined;
      const dated = recognizedAnswer && hasRoutingProgress(recognizedAnswer.value.result)
        && datesEveryScan(recognized!, recognizedAnswer.value.result, value.result) ? recognized : undefined;
      const named = answered ?? providerNamed ?? dated;
      // Filed under the carrier that answered, the parcel keeps that carrier's history,
      // not one another candidate brought first.
      const taken = answered ?? dated;
      const history = taken ? localAnswers.get(taken)?.history : localHistory;
      if (named) {
        state.configured_carrier = named;
        // A provider naming several legs may settle on none (17FEIA, and a DPD the catalog
        // cannot place): the carrier that answered is the one routing settled on, which the
        // database's guard requires of a parcel a provider's history files.
        if (answered) state.discovered_carrier = answered;
        if (dated) {
          if (!confirmedBefore(dated)) report('candidate_probe_confirmed', dated);
          recognizedAnswer!.confirm();
        }
        if (taken) clearSetAside(taken);
        value.correction = { carrier: named, trackingUrl: null, postcode: null };
      }
      // A carrier with an adapter of its own that this check did not ask (an Add preflight's
      // history skips confirming it) is asked at the next run, not after the provider's spacing.
      if (named && directCarrier(named) && !attemptedDirect.has(named) && millis(state.failures[named]?.retry_at) <= now().getTime()) {
        delete state.next_check_at;
        state.carrier_named_at = now().toISOString();
      }
      return { ...value, result: { ...value.result, routing: state,
        ...(history ? { direct_local_history: history } : {}),
        ...(answered ? { carrier_answered: true, discovered_carrier: answered } : {}),
        ...(directCarrier(provider) ? { active_tracking_carrier: value.sourceCarrierId } : {}),
        ...(swap ? { auto_changed_from: declared, auto_changed_to: provider, auto_changed_at: now().toISOString() } : {}),
        ...(dated ? { carrier_answered: true, auto_changed_from: declared, auto_changed_to: dated, auto_changed_at: now().toISOString() } : {}),
      } };
    };
    const attemptedDirect = new Set<string>();
    // Carriers whose own lookup answered not found or without history for the number in this check.
    const emptyDirect = new Set<string>();
    // Candidates whose probe found the parcel held behind an input the catalog lists as optional,
    // and those whose probe failed on the way (a rate limit, a challenge): no answer about the number.
    const heldDirect = new Set<string>();
    const unsettledDirect = new Set<string>();
    // The clocks of rows a carrier holds back as written ahead of them: a provider relaying them shows them as scans.
    const writtenAhead: string[] = [];
    let attempts = 0;
    // Probes are this parcel's own guesses: they are no evidence of provider health.
    let probeContacts = 0;
    const attempted = () => attemptedDirect.size - probeContacts + attempts;
    const defer = (stale = !recent()): never => {
      const contacts = attempted();
      // A cooldown-only check supplies no new evidence of failure.
      if (contacts > 0) state.consecutive_failures = Math.min(20, state.consecutive_failures + 1);
      throw new RoutingDeferred(state, stale && state.consecutive_failures >= 2, contacts);
    };
    // A candidate whose newest scan is older than the parcel's newest is no
    // fresher source, unless the provider's copies of the candidate's own
    // scans set that watermark on a misread clock (ParcelsApp reads 4PX's and
    // Evri's local times as UTC). `provider` is the answer of this check. An
    // answer without a date is neither older nor newer.
    const olderThanParcel = (value: RoutedResult, provider?: CarrierResult) => {
      const watermark = millis(state.last_event_at);
      const newest = latest(value);
      return newest > 0 && !asNewAs(newest, watermark) && !asNewAs(newest, this.options.withoutCopies?.(watermark, value, provider) ?? watermark);
    };
    // A parcel filed under a carrier with no adapter of its own, which the delivery carrier a provider
    // named took over: the filed carrier's lookup asks that carrier, as after any handoff.
    const handedOver = metadata.original_carrier === declared && typeof metadata.active_tracking_carrier === 'string'
      && directCarrier(metadata.active_tracking_carrier);
    const tryDirect = async (carrier: string, candidate = false, terminalStage?: string, probe = false, provider?: CarrierResult): Promise<RoutedResult | null> => {
      signal?.throwIfAborted();
      if (!(directCarrier(carrier) || (handedOver && carrier === declared)) || attemptedDirect.has(carrier)) return null;
      // Never borrow a postcode/capability from another carrier or guess missing
      // input. An optional input (DPD's postcode) only adds detail, so a
      // candidate that needs nothing else is looked up without it.
      const ownInputs = state.confirmed_carrier === carrier && state.confirmed_number === number;
      if (candidate && !ownInputs && requiredRequirements(carrier, number).length) {
        report('carrier_input_required', carrier); return null;
      }
      if (millis(state.failures[carrier]?.retry_at) > now().getTime()) return null;
      attemptedDirect.add(carrier);
      const setAside = state.set_aside?.[carrier];
      clearSetAside(carrier);
      // Kept, so the review tells it from a carrier never asked, and a release's
      // check knows the same answer would be set aside again (redetection.ts).
      const setAsideAs = (reason: SetAsideReason, value: RoutedResult, newest?: string) => {
        const again = setAside && (setAside.reason ?? 'older') === reason && setAside.newest === newest;
        const added = this.options.newScans?.(value);
        state.set_aside = { ...state.set_aside, [carrier]: { reason, checked_at: now().toISOString(),
          ...(newest ? { newest, watermark: iso(millis(state.last_event_at)) } : {}),
          count: again ? Math.min(20, setAside.count + 1) : 1, ...(added !== undefined ? { new_scans: added } : {}) } };
      };
      try {
        const lookupParcel = candidate ? { ...parcel, carrier,
          tracking_url: ownInputs ? state.confirmed_tracking_url : null,
          dpd_postcode: ownInputs ? state.confirmed_postcode : null,
          // The country of the owner's postcode goes with it.
          carrier_data: ownInputs && isRecord(parcel.carrier_data) && parcel.carrier_data.postcode_country
            ? { postcode_country: parcel.carrier_data.postcode_country } : {} } : parcel;
        // A carrier not confirmed for the number is asked whether it holds the parcel.
        const lookup = () => this.options.direct(lookupParcel, carrier);
        const value = await (candidate && !ownInputs ? confirmationLookup(lookup) : lookup());
        value.result = normalizeCarrierResult(value.result);
        if (!usable(value.result)) throw Object.assign(new Error('No confirmed shipment progress'), { status: 404 });
        // It answers for the number, on local clocks too: a refusal an older release recorded no longer holds.
        if (refusedNumberCode(state.failures[carrier]?.user_error)) delete state.failures[carrier];
        // The carrier's own copy of a reused number's earlier parcel answers without history for
        // this one: it neither confirms the carrier nor serves the parcel, on local clocks either.
        if (oldLabel(parcel.created_at, value.result, value.earlierResult)) {
          report('foreign_history_rejected', carrier);
          throw new NoHistoryError(carrier, `${carrier} returned an older parcel for this number`);
        }
        // An empty summary archive binds the fallback to this parcel without
        // creating a scan; earlier real scans survive when it is merged.
        const capturedHistory = hasUnresolvedDirectHistory(value.sourceCarrierId, value.result)
          || (value.result.summary_only === true && hasUnresolvedDirectCurrent(value.sourceCarrierId, value.result))
          ? captureDirectLocalHistory(value.sourceCarrierId, String(directHistoryNumber(lookupParcel, value.result)), value.result)
          : undefined;
        // A probe needs movement: a pre-advice ("Order created") proves the
        // label, not that this carrier has the parcel yet.
        const notYet = probe ? ['pending', 'unknown', 'registered'] : ['pending', 'unknown'];
        const moved = [value.result.status, value.result.current_stage, ...(value.result.events ?? []).map((event) => event.stage)]
          .some((stage) => stage && !notYet.includes(stage));
        const confirm = () => {
          // The carrier's own answer for this very number, the first time: count
          // the rules whose failed check digit kept it out of detection's
          // suggestions. The carrier and rule ids are recorded, never the number.
          if (value.sourceCarrierId === carrier && !metadata.original_carrier
            && (state.confirmed_carrier !== carrier || state.confirmed_number !== number) && !confirmedBefore(carrier)) {
            for (const rejection of checksumRejections(number)) {
              if (rejection.carrier === carrier) recordChecksumRejection(carrier, rejection.rule);
            }
          }
          state.confirmed_carrier = carrier;
          state.confirmed_number = number;
          delete state.cleared_confirmation;
          if (candidate && !ownInputs) {
            state.confirmed_tracking_url = null;
            state.confirmed_postcode = null;
          } else if (!candidate) {
            state.confirmed_tracking_url = typeof parcel.tracking_url === 'string' ? parcel.tracking_url : null;
            state.confirmed_postcode = typeof parcel.dpd_postcode === 'string' ? parcel.dpd_postcode : null;
          }
        };
        if (hasUnresolvedDirectCurrent(value.sourceCarrierId, value.result)) {
          // Preserve the direct evidence, but let timestamped or richer
          // providers supply the normal timeline and freshness watermark.
          // A discovered candidate with no instants cannot displace a source
          // whose dated progress already established the carrier.
          if (!candidate) {
            localDirectFallback = { value, carrier };
            localHistory = capturedHistory;
            // The filed carrier answered: a provider naming it later is no discovery
            // to confirm again, and it keeps its own retry time.
            confirm();
          } else if (!localHistory && !directLocalHistory(parcel, {})) {
            // An unconfirmed candidate may add evidence for an unknown parcel,
            // but cannot replace history from the parcel's own carrier.
            localHistory = capturedHistory;
          }
          // The carrier detection or a provider names, answering for this very
          // number, can still file an unknown parcel once a provider dates the
          // parcel's progress. Its newest scan has no instant to hold against the watermark.
          if (candidate && moved && value.sourceCarrierId === carrier && !earlierLeg(carrier, value)) {
            localAnswers.set(carrier, { value, history: capturedHistory, confirm });
          }
          // Set aside unless the check takes it in the end.
          if (candidate) setAsideAs(moved ? 'local_clock' : 'no_movement', value);
          return null;
        }
        if (candidate && !moved) {
          setAsideAs('no_movement', value);
          return null;
        }
        // An answer without a date is neither older nor newer than the parcel's dated progress,
        // so it cannot displace it either.
        if (candidate && !latest(value) && millis(state.last_event_at)) {
          setAsideAs('undated', value);
          return null;
        }
        if (candidate && olderThanParcel(value, provider)) {
          setAsideAs('older', value, iso(latest(value)));
          return null;
        }
        // The provider's delivered or returned answer stands.
        if (terminalStage && ['delivered', 'returned'].includes(terminalStage)
          && resultStage(value.result) !== terminalStage) {
          setAsideAs('behind_final', value);
          return null;
        }
        if (capturedHistory) localHistory = capturedHistory;
        // It shows the parcel until the filed carrier has it, and is neither
        // confirmed in its place nor reported as a detection gap.
        if (earlierLeg(carrier, value)) return value;
        // A probe finding the carrier its number points to is the expected
        // outcome, not a detection gap: log it without raising an alert. The
        // same holds when detection already names the confirming carrier.
        if (carrier !== declared && state.confirmed_carrier !== carrier && !confirmedBefore(carrier)) {
          report(probe ? 'candidate_probe_confirmed'
            : detectionExpects(number, carrier, countryHint) ? 'detected_carrier_confirmed' : 'carrier_mismatch_confirmed', carrier);
        }
        confirm();
        state.direct_retry_at = undefined;
        return value;
      } catch (error) {
        signal?.throwIfAborted();
        const failure = fail(carrier, error);
        if (failure.kind === 'not_found' || failure.kind === 'no_history') emptyDirect.add(carrier);
        writtenAhead.push(...writtenAheadClocks(error));
        if (probe && error instanceof CarrierError && error.reason === 'parcel_found') heldDirect.add(carrier);
        if (probe && ['rate_limited', 'verification', 'schema', 'transport'].includes(failure.kind)) unsettledDirect.add(carrier);
        // The carrier does not issue this number (a failed check digit): a
        // provider's name for it, saved by an older check, stops being asked.
        if (carrierErrorKind(error) === 'invalid_input' && state.discovered_carrier === carrier) delete state.discovered_carrier;
        state.direct_retry_at = failure.retry_at;
        if (failure.kind === 'rate_limited' && recent()) {
          state.next_check_at = iso(Math.min(millis(failure.retry_at), millis(state.last_success_at) + freshnessWindow(now())));
          report('fallback_deferred_fresh', carrier, failure.kind);
          defer(false);
        }
        return null;
      }
    };

    // Try a new manual selection first; revalidate earlier confirmed corrections.
    const old = isRecord(parcel.carrier_data) && isRecord(parcel.carrier_data.routing) ? parcel.carrier_data.routing : {};
    const changed = old.configured_carrier !== undefined && old.configured_carrier !== declared;
    if (changed) { delete state.failures[declared]; report('manual_carrier_change', declared); }
    const primary = !changed && state.confirmed_number === number && state.confirmed_carrier
      ? state.confirmed_carrier : declared;
    // The wait an older release's answer set while a provider serves the parcel ends: this
    // release's adapter may date the scans it gave on local clocks. A carrier whose lookup can
    // open a browser keeps it, and a failure's own retry time still holds.
    if (released && !opensBrowser(primary)) delete state.direct_retry_at;
    if (!state.preferred_provider || changed || millis(state.direct_retry_at) <= now().getTime()) {
      const value = await tryDirect(primary, primary !== declared);
      const filed = value && primary === declared && universalNumber === number ? value : null;
      const sinceChecked = now().getTime() - millis(state.partial_checked_at);
      // The carrier's own label alone, no handoff's, LABEL_WAIT after it first listed the parcel.
      const since = filed ? listedSince(filed, parcel.created_at) : 0;
      const label = filed && filed.sourceCarrierId === declared && !filed.earlierResult && !hasRoutingProgress(filed.result)
        && since > 0 && now().getTime() - since >= LABEL_WAIT;
      // Providers are asked for the rest at their own cadence, and on the next check after one held it.
      if (filed && isPartialAnswer(filed.result)) {
        if (sinceChecked >= universalSpacing(now())) partialDirect = { value: filed, carrier: primary };
      } else if (filed && label && sinceChecked >= LABEL_SPACING) partialDirect = { value: filed, carrier: primary, label: true };
      if (value && !partialDirect) return persistResult(value, primary);
    }
    // The filed carrier's answer, kept although its clocks are local: it
    // creates no scan instants, and a provider's history already shown stays.
    const keepLocal = ({ value, carrier }: { value: RoutedResult; carrier: string }) => {
      value.result = { ...value.result, direct_local_fallback: true };
      return persistResult(value, carrier);
    };
    // While no provider has progress for the parcel, a carrier's own answer that moved serves
    // it, on local clocks too: the filed carrier's, or for an unknown parcel the one detection
    // names or a provider named, which it is then filed under and confirmed for, as a probe's
    // answer with dated scans would be. A registration alone is no movement. That carrier is
    // asked first on the next check.
    const ownLocalAnswer = (): RoutedResult | undefined => {
      if (localDirectFallback) {
        if (!hasRoutingProgress(localDirectFallback.value.result)) return undefined;
        state.direct_retry_at = undefined;
        return keepLocal(localDirectFallback);
      }
      if (declared !== 'unknown' || state.confirmed_carrier || metadata.original_carrier || universalNumber !== number) return undefined;
      const carrier = [...localAnswers.keys()].find((answered) => detectionNames(number, answered))
        ?? (state.discovered_carrier && localAnswers.has(state.discovered_carrier) ? state.discovered_carrier : undefined);
      const answer = carrier ? localAnswers.get(carrier) : undefined;
      if (!carrier || !answer || !hasRoutingProgress(answer.value.result)) return undefined;
      answer.confirm();
      clearSetAside(carrier);
      localHistory = answer.history;
      state.direct_retry_at = undefined;
      return keepLocal({ value: answer.value, carrier });
    };
    // A freshly checked preview can keep the already saved fuller history for an hour.
    // Compare the whole answer: a new scan can replace one in a fixed-size window without
    // changing its count or stage. A manual refresh still asks for the rest immediately.
    const partial = partialDirect ?? localDirectFallback;
    const held = state.partial_history;
    const historyAge = now().getTime() - millis(held?.checked_at);
    if (scheduled && !changed && !released && universalNumber === number && partial && held
      && held.fingerprint === partialHistoryKey(partial.value) && historyAge >= 0 && historyAge < HOUR
      && metadata.tracking_provider === held.provider && sources.includes(held.provider as UniversalSource)
      && resultStage(partial.value.result) === parcel.current_stage) {
      state.direct_retry_at = undefined;
      const kept = persistResult({ ...partial.value, preserveHistory: true }, partial.carrier);
      state.next_check_at = iso(now().getTime() + universalSpacing(now()));
      return kept;
    }
    // A provider held exactly the scans and stage of that answer in the last 6 h.
    const copy = state.provider_copy;
    const local = localDirectFallback && localAnswer(localDirectFallback.value.result);
    const unchanged = Boolean(localDirectFallback && local && copy?.carrier === localDirectFallback.carrier && copy.number === number
      && copy.scans === local.scans && copy.stage === local.stage && now().getTime() - millis(copy.checked_at) < 6 * HOUR);
    // While the answer stays the same and the parcel shows that provider's
    // history, a scheduled check keeps it instead of asking the providers again,
    // at their cadence. The sync keeps a provider's summary over a local-clock
    // answer, unless the parcel is still pending. A copy that only guessed the
    // clock of the carrier's days left the carrier's answer in its place: that
    // answer serves the check, at the carrier's own cadence.
    if (scheduled && unchanged && localDirectFallback && universalNumber === number && copy?.kept
      && typeof metadata.tracking_provider !== 'string' && parcel.current_stage !== 'pending') {
      state.direct_retry_at = undefined;
      return persistResult(localDirectFallback.value, localDirectFallback.carrier);
    }
    if (scheduled && unchanged && localDirectFallback && universalNumber === number && metadata.tracking_provider === copy?.provider
      && parcel.current_stage !== 'pending') {
      const kept = keepLocal(localDirectFallback);
      state.next_check_at = iso(now().getTime() + universalSpacing(now()));
      return kept;
    }

    // One evidence-based correction, not a fan-out to every matching number shape.
    const detected = detectCarrierMatch(number);
    const candidates = [...new Set([
      detected.confidence === 'high' ? detected.carrier : undefined,
      changed && state.confirmed_number === number ? state.confirmed_carrier : state.discovered_carrier,
    ])].filter((candidate): candidate is string => Boolean(candidate) && candidate !== primary);
    for (const candidate of partialDirect ? [] : candidates) {
      const value = await tryDirect(candidate, true);
      if (value) return persistResult(value, candidate);
    }
    // Beside the filed carrier's partial answer or label alone, Cainiao, an aggregator, is asked for
    // the rest before the universals when detection lists it for the number (GoFo's GFUS numbers).
    // Its movement, not a label of its own, files the parcel under it.
    if (partialDirect && primary !== 'aliexpress' && detected.candidates.includes('aliexpress')) {
      const value = await tryDirect('aliexpress', true, undefined, true);
      if (value) return persistResult(value, 'aliexpress');
    }
    // A carrier chosen by hand that has its own adapter comes first from now on:
    // the one confirmed before it is set aside unless it just tracked the number.
    if (changed && directCarrier(declared) && state.confirmed_number === number && state.confirmed_carrier !== declared) {
      delete state.confirmed_carrier; delete state.confirmed_number;
      delete state.confirmed_tracking_url; delete state.confirmed_postcode;
    }

    // The filed carrier cannot track this number: it has no adapter of its own
    // (unknown, a universal-only carrier) or its adapter does not know the
    // number (a forwarder such as Asendia, a wrong label). A low-confidence
    // shape that excludes the filed carrier also permits verified recognition,
    // even when that carrier returned a transport error. A transient failure
    // for a matching shape is not that, nor is a not-found for a number the
    // carrier already confirmed. Before the universals, ask the carriers the number could
    // belong to whether they know it, never another network of the filed
    // carrier's own brand, for open parcels in their first month.
    const confirmed = state.confirmed_carrier === primary && state.confirmed_number === number;
    const differentShape = detected.confidence === 'low' && !detected.candidates.includes(primary as never);
    const filedCannotTrack = !confirmed && (!directCarrier(primary)
      || state.failures[primary]?.kind === 'not_found' || differentShape);
    const open = !['delivered', 'returned'].includes(String(parcel.current_stage));
    const young = typeof parcel.created_at !== 'string' || now().getTime() - millis(parcel.created_at) < CANDIDATE_PROBE_WINDOW;
    const recognize = this.options.recognize;
    if (!prefetchedUniversal.size && (recognize || this.options.recognizeBrowser) && filedCannotTrack && open && young && universalNumber === number && !metadata.original_carrier) {
      const brand = carrierBrand(primary) ?? carrierBrand(declared);
      const skip = (candidate: string) => candidate === primary || candidate === declared
          || Boolean(brand && carrierBrand(candidate) === brand) || attemptedDirect.has(candidate)
          || millis(state.candidate_probes?.[candidate]?.retry_at) > now().getTime()
          || millis(state.failures[candidate]?.retry_at) > now().getTime();
      const ordering = { hint: state.discovered_carrier, countryHint, priorities: getRecognitionPriorities(number), skip };
      const eligible = recognize ? recognitionCandidates(number, ordering) : [];
      const due = eligible.slice(0, MAX_RECOGNITIONS);
      const next = addition ? eligible.slice(MAX_RECOGNITIONS, 2 * MAX_RECOGNITIONS) : [];
      const recognitionDeadline = performance.now() + RECOGNITION_BUDGET_MS;
      const errors = new Map<string, unknown>();
      const ask = async (candidate: string, context: TrackingContext) => {
        try { return await recognize!(candidate, number, context); }
        catch (error) { errors.set(candidate, error); throw error; }
      };
      const outcomes = due.length ? await recognizeAll(due, ask,
        next.length ? Math.floor(RECOGNITION_BUDGET_MS / 2) : RECOGNITION_BUDGET_MS, signal) : [];
      const first = settleRecognition(outcomes, now());
      const remaining = Math.floor(recognitionDeadline - performance.now());
      if (next.length && !first.carrier && !first.choices.length && remaining > 0) {
        outcomes.push(...await recognizeAll(next, ask, remaining, signal));
      }
      const cheap = settleRecognition(outcomes, now());
      if (this.options.recognizeBrowser && !cheap.carrier && !cheap.choices.length) {
        const browserDue = recognitionCandidates(number, { ...ordering, phase: 'browser',
          skip: (candidate) => skip(candidate) || outcomes.some((outcome) => outcome.carrier === candidate && outcome.status !== 'failed'),
        }).slice(0, MAX_BROWSER_RECOGNITIONS);
        outcomes.push(...await recognizeAll(browserDue, (candidate, context) => this.options.recognizeBrowser!(candidate, number, context, errors.get(candidate)),
          BROWSER_RECOGNITION_BUDGET_MS, signal));
      }
      signal?.throwIfAborted();
      recognized = settleRecognition(outcomes, now()).carrier;
      // A carrier that knows the number and needs no input gets a full
      // lookup, adopted only on real progress (a pre-advice is not enough).
      for (const outcome of outcomes) {
        if (outcome.needsInput || settleRecognition([outcome], now()).carrier !== outcome.carrier) continue;
        const failure = state.failures[outcome.carrier];
        const retry = state.direct_retry_at;
        const value = await tryDirect(outcome.carrier, true, undefined, true).catch((error: unknown) => {
          if (!(error instanceof RoutingDeferred)) throw error;
          return null;
        });
        if (attemptedDirect.has(outcome.carrier)) probeContacts++;
        if (value) {
          if (state.candidate_probes) delete state.candidate_probes[outcome.carrier];
          return persistResult(value, outcome.carrier);
        }
        // Not adopted: the parcel's own schedule, not the carrier's failure.
        if (failure) state.failures[outcome.carrier] = failure;
        else delete state.failures[outcome.carrier];
        state.direct_retry_at = retry;
      }
      // A carrier that knows the number but needs the user's input (GLS's
      // postcode) is offered to the user instead, as is one whose lookup found
      // the parcel behind an input it lists as optional (DHL's postcode, when
      // several shipments share the number).
      const asking = outcomes.map((outcome) => ({ ...outcome, needsInput: outcome.needsInput
        ?? (heldDirect.has(outcome.carrier) ? activeRequirements(outcome.carrier, number)[0]?.field ?? null : null) }))
        .filter((outcome) => outcome.needsInput);
      const needing = settleRecognition(asking, now()).carrier;
      const field = asking.find((outcome) => outcome.carrier === needing)?.needsInput;
      if (needing && field) {
        if (state.input_needed?.carrier !== needing) report('carrier_input_needed', needing);
        state.input_needed = { carrier: needing, field: appInputField(field) };
      } else if (outcomes.some((outcome) => outcome.carrier === state.input_needed?.carrier && outcome.status !== 'failed'
        && !(unsettledDirect.has(outcome.carrier) && settleRecognition([outcome], now()).carrier === outcome.carrier))) {
        // Asked again, it no longer knows the number (or only an old parcel with it), or
        // tracks it without the input. A lookup that failed on the way leaves the request.
        delete state.input_needed;
      }
      // Nothing adopted: ask again after 1, 2, 4, then 6 hours, since a parcel
      // shows up once it is handed over, and daily after six misses, but by the
      // last hourly check of a number nobody has seen. These answers decide
      // neither the parcel's status nor the filed carrier's retry.
      for (const carrier of new Set(outcomes.map((outcome) => outcome.carrier))) {
        const count = Math.min(20, (state.candidate_probes?.[carrier]?.count ?? 0) + 1);
        state.candidate_probes = { ...state.candidate_probes, [carrier]: {
          count, retry_at: iso(beforeHourlyEnd(now().getTime() + Math.min(count > 6 ? DAY : 6 * HOUR, HOUR * 2 ** (count - 1)))),
        } };
      }
    }

    const richerSources = sources.filter((source) => source !== 'UPU');
    const preferred = richerSources.includes(state.preferred_provider!) ? state.preferred_provider : undefined;
    // A cheaper fallback must not stay pinned after the richer route recovers. A
    // provider the carrier's plan left out stays out, even for a number it would lead.
    const priority = sources.find((source) => source === priorityUniversalSource(universalNumber));
    // Providers with fuller history for this carrier come before the one the parcel stays with.
    const fuller = preferred ? richerSources.filter((source) => plan.rank(source) < plan.rank(preferred)) : [];
    // The discovery cursor rotates which aggregator a check starts with.
    // Postal Ninja and UPU keep their place after the others.
    const rotation: UniversalSource[] = richerSources.filter((source) => source !== 'Postal Ninja');
    const offset = state.discovery_cursor % Math.max(1, rotation.length);
    const everyProvider = [...new Set<UniversalSource>([...(priority ? [priority] : []), ...fuller, ...(preferred ? [preferred] : []), ...rotation.slice(offset), ...rotation.slice(0, offset),
      ...sources])];
    const ordered = sparse ? recheckSources(universalNumber, everyProvider, this.options.enablePostalNinja) : everyProvider;
    // Reserve each source’s lookup budget plus transport allowance (UPU needs only 8s).
    // Start after direct attempts so a slow carrier cannot starve discovery.
    const universalDeadline = performance.now() + sources.reduce((sum, source) => sum + universalSourceBudget(source) + 5_000, 0);
    // A label without a local clock (asendia, unknown) defers to the carrier a
    // direct lookup confirmed for this same number.
    const zone = carrierZone(universalCarrier)
      ?? (state.confirmed_carrier && state.confirmed_number === universalNumber ? carrierZone(state.confirmed_carrier) : null);
    // The carrier that confirmed this same number (DPD for a parcel filed under
    // Asendia, which hands it on), else the filed one. Never one a provider
    // named: a hint must not echo a provider's answer.
    const askedCarrier = (state.confirmed_carrier && state.confirmed_number === universalNumber ? state.confirmed_carrier : null)
      ?? (universalCarrier === 'unknown' ? null : universalCarrier);
    const attemptedUniversal = new Set<UniversalSource>();
    // Providers that answered without history for this number in this check.
    const answeredEmpty: UniversalSource[] = [];
    const universal = async (source: UniversalSource): Promise<RoutedResult | null> => {
      signal?.throwIfAborted();
      // Node's AbortSignal.timeout requires integer milliseconds.
      const remaining = Math.floor(universalDeadline - performance.now());
      if (attemptedUniversal.has(source) || remaining <= 5_000 || millis(state.failures[source]?.retry_at) > now().getTime()
        || (scheduled && awaitsInput(source)) || (sparse && attempts > 0)) return null;
      attemptedUniversal.add(source);
      const prefetched = prefetchedUniversal.get(source) ?? this.options.takePrefetchedUniversal?.(source, universalNumber, providerPostcode);
      if (prefetched) {
        const result = withoutWrittenAhead(normalizeCarrierResult(prefetched), writtenAhead);
        if (result && usable(result) && !foreignHistory(result, universalCarrier, parcel.created_at)) {
          delete state.failures[source];
          return { result: { ...result, tracking_provider: source }, sourceCarrierId: 'unknown', swissPostReady: null, handoffFallbackErrorType: null };
        }
      }
      const reused = this.options.reusedUniversal?.(source, universalNumber, providerPostcode, zone, askedCarrier);
      const acquire = async () => {
        try { return await this.options.health.acquireTrackingProvider(source); }
        catch {
          report('health_store_unavailable', source, 'transport');
          state.failures[source] = { count: 0, kind: 'transport', retry_at: iso(now().getTime() + 15 * 60_000) };
          return null;
        }
      };
      let token: string | null = null;
      if (!reused) {
        let lease = await acquire();
        for (let round = 0; lease && !lease.token && round < PACING_ROUNDS; round++) {
          // Spacing after another check frees the provider within seconds: wait
          // when the call keeps its full lookup budget. A cooldown is not waited for.
          const wait = millis(lease.retry_at) - now().getTime();
          if (!millis(lease.retry_at) || wait > PACING_WAIT_MS
            || universalDeadline - performance.now() - Math.max(0, wait) - 5_000 < universalSourceBudget(source)) break;
          report('provider_paced', source);
          await (this.options.wait ?? ((ms, abort) => sleep(ms, undefined, { signal: abort })))(Math.max(250, wait), signal);
          lease = await acquire();
        }
        if (!lease) return null; // Fail closed: do not flood upstreams when coordination is down.
        if (!lease.token) {
          state.failures[source] = { count: state.failures[source]?.count ?? 0, kind: 'rate_limited', retry_at: lease.retry_at };
          report('provider_cooldown', source); return null;
        }
        token = lease.token;
      }
      attempts++;
      const before = performance.now();
      let kind: RoutingFailureKind | null = null;
      let retryAfterMs = 0;
      try {
        const result = withoutWrittenAhead(normalizeCarrierResult(await (reused ?? this.options.universal(source, universalNumber,
          Math.max(1, Math.min(universalSourceBudget(source), Math.floor(universalDeadline - performance.now()) - 5_000)), providerPostcode, zone, askedCarrier))), writtenAhead);
        if (!result) throw new NoHistoryError(source, `${source} relays only rows the carrier wrote ahead`);
        if (!usable(result)) throw new TypeError('No usable universal progress');
        if (foreignHistory(result, universalCarrier, parcel.created_at)) {
          report('foreign_history_rejected', source);
          throw new IndeterminateError(source, `${source} returned an older parcel for this number`);
        }
        const previousFailure = state.failures[source];
        if (previousFailure) report('provider_recovered', source);
        delete state.failures[source];
        return { result: { ...result, tracking_provider: source }, sourceCarrierId: 'unknown', swissPostReady: null, handoffFallbackErrorType: null };
      } catch (caught) {
        const error = !inputPlausible && carrierErrorKind(caught) === 'input_required'
          ? new NoHistoryError(source, `${source} asked for a postcode for a number that no carrier issues`, { cause: caught }) : caught;
        const failure = fail(source, error, true);
        kind = failure.kind;
        if (kind === 'not_found' || kind === 'no_history') answeredEmpty.push(source);
        retryAfterMs = millis(failure.retry_at) - now().getTime();
        if (kind === 'rate_limited' && recent() && !localDirectFallback && !partialDirect) {
          state.next_check_at = iso(Math.min(millis(failure.retry_at), millis(state.last_success_at) + freshnessWindow(now())));
          defer(false);
        }
        return null;
      } finally {
        // A reused answer was counted, and its lease released, by the check that asked.
        if (token) {
          if (providerInput) {
            // Every provider receives the postcode; supported sources can submit it.
            const step = kind === null ? 'history' : kind === 'input_required' ? 'still_required'
              : kind === 'not_found' || kind === 'no_history' ? 'no_history' : 'failed';
            recordProviderInput(source, step);
            report('provider_input_lookup', source, step);
          }
          // An answer about this number, a refusal included, keeps the provider's circuit closed, like not-found.
          try { await this.options.health.finishTrackingProvider(source, token, kind === 'no_history' || kind === 'input_required' || kind === 'invalid_input' ? 'not_found' : kind, retryAfterMs, performance.now() - before); }
          catch { report('health_store_unavailable', source, 'transport'); }
        }
      }
    };

    // A label-only answer must not stop discovery or displace a source that
    // supplied progress. Keep it only if no eligible provider has progress.
    const needsProgress = PROGRESS_STAGES.has(rereadStage(parcel));
    let thinAnswer: { value: RoutedResult; source: UniversalSource } | undefined;
    let selected: { value: RoutedResult; source: UniversalSource } | undefined;
    for (const source of ordered) {
      const value = await universal(source).catch((error: unknown) => {
        // A later cooldown cannot erase an answer already available to preserve.
        if (thinAnswer && error instanceof RoutingDeferred) return null;
        throw error;
      });
      if (!value) continue;
      // The carrier's partial answer stands unless this history is fuller and not behind it.
      if (partialDirect) {
        const filled = fillPartialAnswer(declared, number, partialDirect.value.result, value.result);
        if (!filled) continue;
        value.result = filled;
      }
      // Beside the carrier's label, only progress serves the parcel.
      if ((needsProgress || partialDirect?.label) && !hasRoutingProgress(value.result)) {
        if (!partialDirect) thinAnswer ??= { value, source };
        continue;
      }
      selected = { value, source };
      break;
    }
    const withoutProgress = !selected && Boolean(thinAnswer);
    selected ??= thinAnswer;
    if (selected) {
      let { value } = selected;
      const { source } = selected;
      let chosen = source;
      // Scheduled-only shadow check. Keep affinity unless the alternative has
      // strictly newer progress; never merge contradictory provider summaries.
      if (!withoutProgress && scheduled && source !== priority && preferred === source && now().getTime() - millis(state.last_probe_at) >= DAY && attempts < 2) {
        // Only providers with at least as full a history for the carrier are worth the comparison.
        const alternatives = richerSources.filter((item) => item !== source && plan.rank(item) <= plan.rank(source));
        const alternative = alternatives[state.probe_cursor % alternatives.length];
        state.probe_cursor++;
        state.last_probe_at = now().toISOString();
        let probe = alternative ? await universal(alternative).catch((error: unknown) => {
          if (!(error instanceof RoutingDeferred)) throw error;
          return null;
        }) : null;
        if (probe && partialDirect) {
          const filled = fillPartialAnswer(declared, number, partialDirect.value.result, probe.result);
          probe = filled ? { ...probe, result: filled } : null;
        }
        if (probe && (!hasRoutingProgress(value.result) || hasRoutingProgress(probe.result)) && latest(probe) > latest(value) && latest(probe) >= millis(state.last_event_at)
          && !['delivered', 'returned'].includes(String(resultStage(value.result)))) {
          value = probe; chosen = alternative;
          report('fresher_provider_found', chosen);
        }
      }
      if (!withoutProgress && preferred !== chosen) report('provider_selected', chosen);
      // Evidence to refresh the coverage comparison: a provider it found empty for
      // the carrier has this parcel, or one it found with history does not.
      // UPU is ordered by its role, not by evidence.
      if (plan.carrier) {
        if (chosen !== 'UPU' && plan.tier(chosen) === 'empty') report('coverage_contradicted', chosen, 'history');
        for (const source of answeredEmpty) {
          if (source !== 'UPU' && ['full', 'partial'].includes(plan.tier(source))) report('coverage_contradicted', source, 'no_history');
        }
      }
      if (!withoutProgress && !directCarrier(declared) && declared !== 'unknown' && declared !== 'intl-post' && !preferred) opportunity(declared);
      state.preferred_provider = withoutProgress || chosen === 'UPU' ? preferred : chosen;
      state.preferred_number = state.preferred_provider ? universalNumber : undefined;
      // A carrier whose adapter refuses the number as one it does not issue is no
      // hint: asked, it would only refuse again. The check sends nothing.
      const refused = typeof value.result.discovered_carrier === 'string'
        && await this.options.refuses?.(value.result.discovered_carrier, universalNumber);
      if (refused && state.discovered_carrier === value.result.discovered_carrier) delete state.discovered_carrier;
      if (typeof value.result.discovered_carrier === 'string' && !refused) {
        state.discovered_carrier = value.result.discovered_carrier;
        if (directCarrier(state.discovered_carrier) && state.discovered_carrier !== state.confirmed_carrier) {
          state.direct_retry_at = now().toISOString();
          report('direct_carrier_discovered', state.discovered_carrier);
        } else if (!withoutProgress && !directCarrier(state.discovered_carrier)) opportunity(state.discovered_carrier);
      }
      // Confirm a newly reported carrier immediately. Preserve universal data if
      // confirmation fails, needs credentials, or only returned older history.
      if (!prefetchedUniversal.has(chosen) && state.discovered_carrier && universalNumber === number && !metadata.original_carrier) {
        const watermark = state.last_event_at;
        // Progress the parcel hasn't had yet: a carrier that said "not found"
        // may know the parcel now, so it is asked without waiting its turn.
        const missed = state.failures[state.discovered_carrier];
        const overtaken = missed?.kind === 'not_found' && !attemptedDirect.has(state.discovered_carrier) && latest(value) > millis(watermark);
        if (overtaken) missed.retry_at = now().toISOString();
        // Otherwise the carrier already confirmed for the number keeps its own
        // retry time, as when the check starts.
        const later = !overtaken && state.discovered_carrier === state.confirmed_carrier && state.confirmed_number === number
          && millis(state.direct_retry_at) > now().getTime();
        state.last_event_at = withoutProgress ? watermark : iso(Math.max(millis(watermark), latest(value)));
        const direct = later ? null : await tryDirect(state.discovered_carrier, state.discovered_carrier !== declared,
          resultStage(value.result) ?? undefined, false, value.result).catch((error: unknown) => {
          if (!(error instanceof RoutingDeferred)) throw error;
          return null;
        });
        state.last_event_at = watermark;
        if (direct) return persistResult(direct, state.discovered_carrier);
        // The provider's copies of rows the carrier holds back as written ahead are no scans.
        const scans = withoutWrittenAhead(value.result, writtenAhead);
        if (!scans) return defer();
        value = { ...value, result: scans };
      }
      if (Array.isArray(value.result.reported_carriers)) {
        const seen = Array.isArray(state.reported_carriers_seen) ? state.reported_carriers_seen : [];
        for (const name of value.result.reported_carriers) {
          // A catalog carrier is no discovery, even as one leg of a handoff or with a country.
          // Nor is the postal union's feed, which names no carrier, or a shipping platform.
          if (typeof name === 'string' && !value.result.discovered_carrier && !seen.includes(name)
            && !isKnownCarrierName(name) && !isCarrierFeedName(name) && !isShippingPlatformName(name)) {
            report('carrier_coverage_discovered', name);
          }
        }
        state.reported_carriers_seen = [...new Set([...seen, ...value.result.reported_carriers])].slice(-20);
      }
      const own = hasRoutingProgress(value.result) ? undefined : ownLocalAnswer();
      if (own) return own;
      // A provider holding just the carrier's scans, which only guesses the clock of those the
      // carrier dates by their day alone, adds nothing: a midnight it gives one can even put it
      // before the scan the carrier lists first. The carrier's answer serves the parcel, its scans
      // in the carrier's order, and the carrier stays first on every check.
      if (localDirectFallback && !withoutProgress && universalNumber === number
        && guessesDayClocks(localDirectFallback.carrier, localDirectFallback.value.result, value.result)) {
        state.provider_copy = { carrier: localDirectFallback.carrier, number, ...localAnswer(localDirectFallback.value.result),
          provider: chosen, checked_at: now().toISOString(), kept: true };
        state.direct_retry_at = undefined;
        delete state.partial_checked_at;
        state.last_probe_at ??= now().toISOString();
        state.discovery_cursor = 0;
        return persistResult(localDirectFallback.value, localDirectFallback.carrier);
      }
      // The carrier's own answer had no current instant, so this provider dates the
      // timeline. A provider holding just the carrier's scans and stage adds
      // nothing else: the carrier stays first on every check and shows when there
      // is something new to date. Otherwise ask the carrier again in 6 h. A retry
      // time left over from an earlier failure has expired and would otherwise
      // keep it due on every check.
      // A carrier that answered only in part stays first on every check.
      const answer = localDirectFallback && !withoutProgress && universalNumber === number
        ? localAnswer(localDirectFallback.value.result) : undefined;
      // A pending answer is left out: it would replace the provider's pending summary.
      const copied = Boolean(answer && answer.stage !== 'pending' && answer.scans === (value.result.events?.length ?? 0)
        && answer.stage === resultStage(value.result));
      // The same copy on a scheduled check: the parcel did not keep that
      // provider's history, so the carrier waits its 6 h as for any other answer.
      const again = copied && scheduled && unchanged;
      if (localDirectFallback && answer && copied && !again) {
        state.provider_copy = { carrier: localDirectFallback.carrier, number, ...answer, provider: chosen, checked_at: now().toISOString() };
      } else if (!copied) delete state.provider_copy;
      // Neither the carrier's answer nor this provider's shows the parcel moving: the carrier
      // stays first on every check, so its first scan serves the parcel without waiting 6 h
      // behind the provider's pending summary.
      const unmoved = localDirectFallback && !hasRoutingProgress(localDirectFallback.value.result) && !hasRoutingProgress(value.result);
      if (partialDirect || (copied && !again) || unmoved) state.direct_retry_at = undefined;
      else if (localDirectFallback || !state.direct_retry_at) state.direct_retry_at = iso(now().getTime() + 6 * HOUR);
      delete state.partial_checked_at;
      state.last_probe_at ??= now().toISOString();
      state.discovery_cursor = 0;
      // The carrier answered too: only its clock, a partial answer or its label alone sent the result elsewhere, so links stay with it.
      if (localDirectFallback || partialDirect) value.result = { ...value.result, carrier_answered: true };
      // A delivery only the provider shows, which the carrier's own answer disputes, goes (disputedDelivery.ts).
      const undisputed = localDirectFallback && universalNumber === number ? withoutDisputedDelivery(
        value.result, value.sourceCarrierId, localDirectFallback.carrier, localDirectFallback.value.result) : null;
      if (undisputed) {
        value = { ...value, result: undisputed };
        report('delivery_disputed', chosen);
      }
      delete state.partial_history;
      const partialAnswer = partialDirect ?? localDirectFallback;
      const fingerprint = partialAnswer && universalNumber === number ? partialHistoryKey(partialAnswer.value) : undefined;
      if (fingerprint && partialAnswer && !withoutProgress && !undisputed
        && resultStage(partialAnswer.value.result) === resultStage(value.result)
        && fillPartialAnswer(declared, number, partialAnswer.value.result, value.result)) {
        state.partial_history = { fingerprint, provider: chosen, checked_at: now().toISOString() };
        state.direct_retry_at = undefined;
      }
      // The provider names the carrier that delivers a parcel filed under one with no adapter of its
      // own (DPD Poland's number for a Z-Leading parcel): that carrier is asked, at the gaps of a
      // carrier's named partner, and answers for the parcel once its own scans confirm it.
      const partner = this.options.handoff && !directCarrier(declared) && !['unknown', 'intl-post'].includes(declared)
        && !metadata.original_carrier && universalNumber === number && !withoutProgress
        ? deliveryHandoff(declared, number, value.result) : null;
      if (partner?.basis === 'partner') {
        const handed = await this.options.handoff!(parcel, declared, { ...value.result }).catch((error: unknown) => {
          signal?.throwIfAborted();
          report('provider_failed', partner.carrier, routingFailure(error).kind, error);
          return null;
        });
        if (handed && handed.sourceCarrierId !== declared) {
          state.direct_retry_at = undefined;
          return persistResult(handed, declared);
        }
      }
      return persistResult(value, chosen, withoutProgress);
    }
    if (partialDirect) {
      state.partial_checked_at = now().toISOString();
      return persistResult(partialDirect.value, partialDirect.carrier);
    }
    if (localDirectFallback) return keepLocal(localDirectFallback);
    const own = ownLocalAnswer();
    if (own) return own;
    // The carrier confirmed for an unknown parcel's number answered not found or without history,
    // and no other source answered: the confirmation goes, as when the sync leaves the parcel
    // unknown, so the next check routes it afresh (recognition, a provider naming its carrier). A
    // passing failure of that carrier says nothing about the number and keeps it. So does a parcel
    // filed under the carrier, which is asked first anyway: there the confirmation keeps a brief
    // "not found" from sending the parcel to other carriers.
    if (declared === 'unknown' && state.confirmed_carrier && state.confirmed_number === number
      && emptyDirect.has(state.confirmed_carrier)) clearConfirmation(state);
    state.discovery_cursor = (offset + Math.max(1, [...attemptedUniversal].filter((source) => rotation.includes(source)).length)) % Math.max(1, rotation.length);
    const deadlines = Object.entries(state.failures).filter(([provider]) => !awaitsInput(provider))
      .map(([, failure]) => millis(failure.retry_at)).filter((time) => time > now().getTime());
    state.next_check_at = iso(Math.max(now().getTime() + 15 * 60_000, Math.min(...deadlines, now().getTime() + HOUR)));
    report('all_providers_unavailable', preferred ?? 'none');
    return defer();
  }
}
