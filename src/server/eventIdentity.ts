/**
 * Stored event identity when a scan reaches the parcel more than once.
 *
 * A stored event is keyed by `provider_event_id`: the source carrier and a hash
 * of the scan's time, place and wording strings (`providerEventId` in
 * trackingSync.ts). The sync upserts on it, so a scan whose strings change gets
 * a second row, and the push views announce that row again.
 *
 * A new scan may instead take over a stored row: it gets that row's identity,
 * and the upsert updates the row in place (same row id, `created_at` and push
 * receipts), so nothing is copied or announced twice. Such a row's identity no
 * longer hashes its own strings (and a universal row's prefix no longer names
 * the source that worded it), so a repair that finds rows by recomputing
 * identities from their strings misses it.
 *
 * - The scraper opts sources into same-instant matching and declares whether
 *   their provider codes or other scan evidence must agree. A unique matching
 *   scan takes over the stored row at its instant (`sameInstantIdentities`).
 *   A policy that names the zone its source once read every wall clock in
 *   lets a scan whose wall clock now carries its own offset take over the row
 *   stored under the old label (India Post's scans abroad). One whose feeds
 *   put different offsets on a scan's wall clock lets the scan take over the
 *   row showing that wall clock under another offset (DHL Express's mobile API
 *   and global portal). A scan its source restates with or without its
 *   milliseconds takes over the row of that second (Royal Mail's locker drop-off).
 * - Whatever the policy, a scan the carrier's own answer moved up to three
 *   hours later takes over the row of the earlier time that the answer no
 *   longer lists (`retimedScans`). The row keeps that earlier time as the one
 *   it was first stored with (`RETIMED_FROM`).
 * - A row taken over keeps its old identity while its strings follow the
 *   scan. When a later check meets the scan again, a policy that matched the
 *   old strings (AliExpress's town in brackets) no longer matches, so a scan
 *   whose source, time, place and wording equal a stored row's takes over
 *   that row whatever its identity (`restatedIdentities`).
 * - A universal provider copies a carrier's scans while the carrier's own
 *   lookup is down, sometimes only to the minute, sometimes in a zone it
 *   misread (Ship24 keeps GOFO's Pacific offset on Eastern clocks), sometimes
 *   in its own wording as well (ParcelsApp put Paack's scans two hours early). A universal copy of a stored scan is
 *   left out of the batch, and a carrier's scan takes over the universal copy
 *   stored while it was down (`sharedScans`). The other copies at its instant
 *   are absorbed: the sync deletes them, and leaves out a provider's later
 *   copy of a scan a carrier holds.
 * - A row only a universal provider has written names that provider
 *   (`UNIVERSAL_PROVIDER`), and follows the provider's clock when a scraper
 *   fix moves it, and the stage a fix reads in a scan it held unclassified. A carrier's scan writes the row without the name, so the
 *   row keeps the carrier's instant and words from then on.
 * - A universal copy stored a zone away from a carrier's row of the scan,
 *   before a fix read the provider's clock right, goes with any check
 *   (`sharedScans`). So does one that shows the carrier scan's wall clock
 *   under another offset (ParcelsApp's UTC for Maersk eCommerce's US
 *   clocks), which the carrier's scan takes over when it is new.
 * - A stage change the sync stored at the time it saw it goes once a scan
 *   dates it, a scan new to the parcel taking its row over, and a new one a
 *   stored row dates is not stored (`datedObservations`).
 * - A handoff's batch carries the earlier carrier's scans too, and that
 *   carrier may relay the delivery carrier's scans in its own words and stage.
 *   Both are stored (relayCopies.ts), but only the delivery carrier's scan
 *   takes over a universal copy (`relayedScans`).
 */
import { createHash } from 'node:crypto';
import { DateTime, FixedOffsetZone } from 'luxon';
import { eventTimestamp, sameInstantIdentityPolicy, trackingPlace, type SameInstantIdentityPolicy, type SameInstantScan } from 'universal-parcel-scraper/app';
import { locatePlace } from 'universal-parcel-scraper/places';
import { isRecord, type JsonObject } from './types';

/**
 * The `raw_data` key that names the universal provider (`tracking_provider`)
 * whose reading a row holds, set on its scans when they are built. A carrier's
 * scan replaces the row's raw data without it, a universal scan that rewrites
 * a row without it leaves it out (`withIdentities`), and rows stored before
 * the key existed have none.
 */
export const UNIVERSAL_PROVIDER = 'universal_provider';

/**
 * The `raw_data` key that keeps the time a row was first stored with, as its
 * source gave it, once its carrier moved the scan later (`retimedScans`). The
 * row keeps its identity and creation time, so without it a moved scan reads
 * like one stored ahead of its time. Later checks carry it over while the row
 * shows another time (`withIdentities`).
 */
export const RETIMED_FROM = 'retimed_from';

/** Peek's words for a step its source gave none for. */
export const UNWORDED_STEP = 'Tracking update';

/** A stored scan's identity: its source, its time as the source gave it, its place and its wording. */
export function providerEventId(
  carrierId: string,
  rawTime: unknown,
  location: string,
  description: string,
): string {
  const material = JSON.stringify([
    carrierId,
    String(rawTime ?? ''),
    location.trim(),
    description.trim(),
  ]);
  return `${carrierId}:${createHash('sha256').update(material).digest('hex')}`;
}

function providerCode(row: JsonObject): string {
  const code = row.provider_code ?? (isRecord(row.raw_data) ? row.raw_data.provider_code : undefined);
  return typeof code === 'string' ? code : '';
}

function scanEvidence(row: JsonObject): SameInstantScan {
  return {
    stage: typeof row.stage === 'string' ? row.stage : '',
    description: typeof row.description === 'string' ? row.description : '',
    location: typeof row.location === 'string' ? row.location : '',
    providerCode: providerCode(row),
  };
}

function identity(row: JsonObject): string {
  return typeof row.provider_event_id === 'string' ? row.provider_event_id : '';
}

function byInstant(rows: readonly JsonObject[]): Map<number, JsonObject[]> {
  const grouped = new Map<number, JsonObject[]>();
  for (const row of rows) {
    const instant = Date.parse(String(row.occurred_at ?? ''));
    if (!Number.isFinite(instant)) continue;
    grouped.set(instant, [...(grouped.get(instant) ?? []), row]);
  }
  return grouped;
}

/**
 * Maps the computed identity of each new scan that should update a stored row
 * in place to that row's identity. Empty unless `sourceCarrierId` opted in.
 *
 * Only a scan from `sourceCarrierId` whose own identity is not stored yet is
 * considered. It takes over a stored row at the exact same instant whose
 * identity has an allowed prefix and is not carried by any event of this
 * batch. The scan and stored row must match uniquely in both directions.
 * A required provider code can distinguish scans sharing an instant. Sources
 * without required codes or a per-scan evidence matcher still require exactly
 * one new scan and one candidate there. Ambiguous scans are inserted as before.
 *
 * A policy's `relabelledFrom` zone adds a pass for the scans and rows left,
 * matched by `relabelled`, its `matchWallClock` another, matched by
 * `sameWallClock`, and its `matchWithinMinute` another, for rows less than a
 * minute away that its `matches` accepts. A last pass, for every policy,
 * matches the scans and rows left by `restatedSecond`.
 */
export function sameInstantIdentities(
  events: readonly JsonObject[],
  stored: readonly JsonObject[],
  sourceCarrierId: string,
): Map<string, string> {
  const reused = new Map<string, string>();
  const policy = sameInstantIdentityPolicy(sourceCarrierId, { supportsScanMatching: true });
  if (!policy || stored.length === 0) return reused;
  const prefixes = policy.storedSources.map((source) => `${source}:`);
  const storedIds = new Set(stored.map(identity));
  const claimed = new Set(events.map(identity));
  const unmatched = byInstant(events.filter((event) => (
    identity(event).startsWith(`${sourceCarrierId}:`) && !storedIds.has(identity(event)) && !observedOnly(event)
  )));
  const candidates = byInstant(stored.filter((row) => (
    prefixes.some((prefix) => identity(row).startsWith(prefix)) && !claimed.has(identity(row)) && !observedOnly(row)
  )));
  const matchEach = policy.requireProviderCode || (policy.matchEachScan === true && policy.matches !== undefined);
  for (const [instant, scans] of unmatched) {
    const rows = candidates.get(instant) ?? [];
    if (!matchEach && (scans.length !== 1 || rows.length !== 1)) continue;
    const matches = (scan: JsonObject, row: JsonObject) => {
      const code = providerCode(scan);
      if (policy.requireProviderCode && (!code || code.toLowerCase() === 'unknown' || code !== providerCode(row))) return false;
      return !policy.matches || policy.matches(scanEvidence(scan), scanEvidence(row));
    };
    for (const scan of scans) {
      const matching = rows.filter((row) => matches(scan, row));
      if (matching.length !== 1) continue;
      const saved = matching[0]!;
      if (scans.filter((other) => matches(other, saved)).length !== 1) continue;
      reused.set(identity(scan), identity(saved));
    }
  }
  const zone = relabelledFrom(policy);
  if (zone) matchLeft(reused, unmatched, candidates, (scan, row) => relabelled(zone, scan, row));
  if (matchesWallClock(policy)) matchLeft(reused, unmatched, candidates, sameWallClock);
  const { matches } = policy;
  if (matchesWithinMinute(policy) && matches) {
    matchLeft(reused, unmatched, candidates, (scan, row) => withinMinute(scan, row) && matches(scanEvidence(scan), scanEvidence(row)));
  }
  matchLeft(reused, unmatched, candidates, (scan, row) => restatedSecond(policy, scan, row));
  return reused;
}

/**
 * One scan its source restated with or without its milliseconds: Royal Mail
 * first dated a locker drop-off 16:31:18.092, then 16:31:18. The same second,
 * one of the times that whole second, the same location and wording apart
 * from case and spacing, and the provider code the policy requires, or codes
 * that agree when both carry one.
 */
function restatedSecond(policy: SameInstantIdentityPolicy, scan: JsonObject, row: JsonObject): boolean {
  const [shown, saved] = [instantOf(scan), instantOf(row)];
  if (!Number.isFinite(shown) || !Number.isFinite(saved) || shown === saved
    || Math.floor(shown / 1_000) !== Math.floor(saved / 1_000) || (shown % 1_000 !== 0 && saved % 1_000 !== 0)) return false;
  const [code, savedCode] = [providerCode(scan), providerCode(row)];
  if (policy.requireProviderCode ? !code || code.toLowerCase() === 'unknown' || code !== savedCode : code && savedCode && code !== savedCode) {
    return false;
  }
  return wordingOf(scan) !== '' && wordingOf(scan) === wordingOf(row)
    && String(scan.location ?? '').trim() === String(row.location ?? '').trim();
}

/** Each scan left whose one matching row left matches no other scan left takes that row over. */
function matchLeft(
  reused: Map<string, string>,
  unmatched: ReadonlyMap<number, JsonObject[]>,
  candidates: ReadonlyMap<number, JsonObject[]>,
  matches: (scan: JsonObject, row: JsonObject) => boolean,
): void {
  const taken = new Set(reused.values());
  const scans = [...unmatched.values()].flat().filter((scan) => !reused.has(identity(scan)));
  const rows = [...candidates.values()].flat().filter((row) => !taken.has(identity(row)));
  for (const scan of scans) {
    const matching = rows.filter((row) => matches(scan, row));
    if (matching.length !== 1) continue;
    const saved = matching[0]!;
    if (scans.filter((other) => matches(other, saved)).length !== 1) continue;
    reused.set(identity(scan), identity(saved));
  }
}

/** The zone a policy's source once read every wall clock in. Policies from scraper releases before the field name none. */
function relabelledFrom(policy: SameInstantIdentityPolicy): string | null {
  const zone = (policy as { relabelledFrom?: unknown }).relabelledFrom;
  return typeof zone === 'string' && DateTime.now().setZone(zone).isValid ? zone : null;
}

const WALL_CLOCK = "yyyy-LL-dd'T'HH:mm:ss";
const MINUTE_CLOCK = "yyyy-LL-dd'T'HH:mm";
const NUMERIC_OFFSET = /[+-]\d{2}:?\d{2}$/;

function timeOf(row: JsonObject): string {
  const time = row.time ?? (isRecord(row.raw_data) ? row.raw_data.time : undefined);
  return typeof time === 'string' ? time : '';
}

/** The wall clock a row's time shows under an offset of its own: not UTC's label, nor the zone's. */
function ownWallClock(zone: string, row: JsonObject): string | null {
  const time = timeOf(row);
  if (!NUMERIC_OFFSET.test(time)) return null;
  const shown = DateTime.fromISO(time, { setZone: true });
  if (!shown.isValid || shown.offset === shown.setZone(zone).offset) return null;
  return shown.toFormat(WALL_CLOCK);
}

/** The wall clock the zone shows at a row's instant, for a row labelled in it. */
function zoneWallClock(zone: string, row: JsonObject): string | null {
  const instant = instantOf(row);
  if (!Number.isFinite(instant) || ownWallClock(zone, row) !== null) return null;
  return DateTime.fromMillis(instant, { zone }).toFormat(WALL_CLOCK);
}

/**
 * One scan read on two clocks: one row shows a wall clock under an offset of
 * its own, the other carries the same wall clock under the zone's label. The
 * provider code must agree, and the location too; wording may differ. A scan
 * and a row that carry no code (Posti's) need the same wording instead.
 */
function relabelled(zone: string, scan: JsonObject, row: JsonObject): boolean {
  const code = providerCode(scan);
  if (code || providerCode(row) ? !code || code.toLowerCase() === 'unknown' || code !== providerCode(row)
    : wordingOf(scan) === '' || wordingOf(scan) !== wordingOf(row)) return false;
  if (String(scan.location ?? '').trim() !== String(row.location ?? '').trim()) return false;
  const shown = ownWallClock(zone, scan);
  const saved = ownWallClock(zone, row);
  return (shown !== null && shown === zoneWallClock(zone, row)) || (saved !== null && saved === zoneWallClock(zone, scan));
}

/** Whether a policy's source can put different offsets on one scan's wall clock. Policies from scraper releases before the field say nothing. */
function matchesWallClock(policy: SameInstantIdentityPolicy): boolean {
  return (policy as { matchWallClock?: unknown }).matchWallClock === true;
}

/** Whether a policy's source dates one scan apart in two feeds, to the minute and to the second. Policies from scraper releases before the field say nothing. */
function matchesWithinMinute(policy: SameInstantIdentityPolicy): boolean {
  return (policy as { matchWithinMinute?: unknown }).matchWithinMinute === true;
}

/** Two instants less than a minute apart. */
function withinMinute(scan: JsonObject, row: JsonObject): boolean {
  const [shown, saved] = [instantOf(scan), instantOf(row)];
  return Number.isFinite(shown) && Number.isFinite(saved) && Math.abs(shown - saved) < MINUTE_MS;
}

const EXPLICIT_OFFSET = /(?:Z|[+-]\d{2}:?\d{2})$/i;

/** A row's time as its source labelled it, when the label names an offset (UTC's included). */
function labelledTime(row: JsonObject): DateTime | null {
  const time = timeOf(row);
  if (!EXPLICIT_OFFSET.test(time)) return null;
  const shown = DateTime.fromISO(time, { setZone: true });
  return shown.isValid ? shown : null;
}

/**
 * One scan whose source's feeds label its wall clock with different offsets
 * (DHL Express's mobile API reads it on the facility's zone, its global portal
 * gives the offset of the system that wrote the record): the same date and
 * time, to the second, under another offset, the same location, the same
 * wording apart from case and spacing, and the same provider code when both
 * carry one.
 */
function sameWallClock(scan: JsonObject, row: JsonObject): boolean {
  const shown = labelledTime(scan);
  const saved = labelledTime(row);
  if (!shown || !saved || shown.offset === saved.offset || shown.toFormat(WALL_CLOCK) !== saved.toFormat(WALL_CLOCK)) return false;
  const code = providerCode(scan);
  const savedCode = providerCode(row);
  if (code && savedCode && code !== savedCode) return false;
  return wordingOf(scan) !== '' && wordingOf(scan) === wordingOf(row)
    && String(scan.location ?? '').trim() === String(row.location ?? '').trim();
}

function stringsOf(row: JsonObject): string {
  return JSON.stringify([timeOf(row), String(row.location ?? '').trim(), String(row.description ?? '').trim()]);
}

/**
 * Maps the computed identity of each new scan to the stored row an earlier
 * take-over left under another identity: the same source, the same instant,
 * and the time, place and wording the scan's identity hashes. Only a scan
 * whose own identity is not stored is considered, against rows no event of
 * the batch carries, never an observation, and the match must be unique.
 */
export function restatedIdentities(
  events: readonly JsonObject[],
  stored: readonly JsonObject[],
): Map<string, string> {
  const restated = new Map<string, string>();
  const storedIds = new Set(stored.map(identity));
  const claimed = new Set(events.map(identity));
  const rows = stored.filter((row) => (
    sourceOf(identity(row)) !== '' && !claimed.has(identity(row)) && !observedOnly(row) && timeOf(row) !== ''
  ));
  for (const event of events) {
    const id = identity(event);
    const source = sourceOf(id);
    if (source === '' || source === 'app' || storedIds.has(id) || observedOnly(event) || timeOf(event) === '') continue;
    const matching = rows.filter((row) => (
      sourceOf(identity(row)) === source && instantOf(row) === instantOf(event) && stringsOf(row) === stringsOf(event)
    ));
    if (matching.length === 1) restated.set(id, identity(matching[0]!));
  }
  return restated;
}

/** A placeholder of a carrier's text template that an adapter left unfilled, such as `{{bboxRebrand}}`. */
const PLACEHOLDER = /\{\{[^{}]*\}\}/;

/** The wordings that fill each placeholder of a stored row's wording, or null when it has none. */
function filledTemplate(row: JsonObject): RegExp | null {
  const parts = String(row.description ?? '').replace(/\s+/g, ' ').trim().split(PLACEHOLDER);
  if (parts.length < 2) return null;
  return new RegExp(`^${parts.map((part) => part.replace(/[.*+?^${}()|[\]\\]/g, '\\$&')).join('\\S.*?')}$`, 'iu');
}

/**
 * Maps the computed identity of each new scan to the one stored row `restates` says it restates, matched one to one:
 * never an observation, a scan whose own identity is not stored and a row no event of the batch carries.
 */
function restatedRows(
  events: readonly JsonObject[],
  stored: readonly JsonObject[],
  restates: (scan: JsonObject, row: JsonObject) => boolean,
): Map<string, string> {
  const taken = new Map<string, string>();
  const storedIds = new Set(stored.map(identity));
  const claimed = new Set(events.map(identity));
  const rows = stored.filter((row) => sourceOf(identity(row)) !== '' && !claimed.has(identity(row)) && !observedOnly(row));
  if (rows.length === 0) return taken;
  const scans = events.filter((event) => {
    const source = sourceOf(identity(event));
    return source !== '' && source !== 'app' && !storedIds.has(identity(event)) && !observedOnly(event);
  });
  for (const scan of scans) {
    const matching = rows.filter((row) => restates(scan, row));
    if (matching.length !== 1) continue;
    if (scans.filter((other) => restates(other, matching[0]!)).length !== 1) continue;
    taken.set(identity(scan), identity(matching[0]!));
  }
  return taken;
}

/**
 * Maps the computed identity of each new scan to the stored row whose wording it fills in: a
 * row an adapter stored with a placeholder of the carrier's template left in ("Item available at
 * the {{bboxRebrand}}"), which the wording identifies, so the scraper's fix would otherwise store
 * the scan beside it. The same source, instant and place, never an observation, a scan whose own
 * identity is not stored and a row no event of the batch carries, matched one to one.
 */
export function filledPlaceholders(
  events: readonly JsonObject[],
  stored: readonly JsonObject[],
): Map<string, string> {
  const templates = new Map(stored.map((row) => [row, filledTemplate(row)]));
  return restatedRows(events, stored, (scan, row) => {
    const template = templates.get(row);
    return template !== null && template !== undefined
      && sourceOf(identity(row)) === sourceOf(identity(scan)) && instantOf(row) === instantOf(scan)
      && String(row.location ?? '').trim() === String(scan.location ?? '').trim()
      && !PLACEHOLDER.test(String(scan.description ?? ''))
      && template.test(String(scan.description ?? '').replace(/\s+/g, ' ').trim());
  });
}

/** A row and a scan of one source, and of one universal provider when that source is one. */
function sameSource(scan: JsonObject, row: JsonObject): boolean {
  return sourceOf(identity(row)) === sourceOf(identity(scan)) && providerOf(row) === providerOf(scan);
}

/** The parts of a scan's place between its commas, without case or extra spaces. */
function placeParts(row: JsonObject): string[] {
  return String(row.location ?? '').split(',')
    .map((part) => part.replace(/\s+/g, ' ').trim().toLocaleLowerCase('en-US')).filter(Boolean);
}

/** Two places of which one names the other with parts more: "São Paulo" and "São Paulo, SP", or none and "Poland". */
function requalified(left: JsonObject, right: JsonObject): boolean {
  const [one, other] = [placeParts(left), placeParts(right)];
  if (one.length === other.length) return false;
  const [fewer, more] = one.length < other.length ? [one, other] : [other, one];
  return fewer.every((part) => more.includes(part));
}

/**
 * Maps the computed identity of each new scan to the stored row whose place it names with a part
 * more or less: a release that adds a qualifier to the carrier's place ("São Paulo, SP" for
 * "São Paulo"), or a source that names the country in one answer and no place in the next, would
 * otherwise store the scan beside its row, the place being part of the identity. The same source
 * (and universal provider), instant, wording and stage, codes that agree when both carry one, never an observation, a scan
 * whose own identity is not stored and a row no event of the batch carries, matched one to one.
 */
export function requalifiedPlaces(
  events: readonly JsonObject[],
  stored: readonly JsonObject[],
): Map<string, string> {
  return restatedRows(events, stored, (scan, row) => sameSource(scan, row)
    && instantOf(row) === instantOf(scan) && stageOf(row) === stageOf(scan)
    && wordingOf(row) !== '' && wordingOf(row) === wordingOf(scan) && codesAgree(scan, row) && requalified(scan, row));
}

/** An HTML tag an adapter left in a scan's words, such as a link around them. */
const MARKUP = /<\/?[a-z][a-z0-9]*(?:\s[^<>]*)?\/?>/iu;
const MARKUP_TAGS = new RegExp(MARKUP.source, 'giu');

/**
 * Maps the computed identity of each new scan to the stored row whose words it gives without the
 * HTML an adapter left in them (YunExpress's link around "CARD LEFT"), which the scraper's fix
 * would otherwise store beside it, often in another stage. The same source (and universal
 * provider), instant and place, codes that agree when both carry one, in any stage, never an observation, a scan whose own
 * identity is not stored and a row no event of the batch carries, matched one to one.
 */
export function strippedMarkup(
  events: readonly JsonObject[],
  stored: readonly JsonObject[],
): Map<string, string> {
  return restatedRows(events, stored, (scan, row) => {
    const words = String(row.description ?? '');
    return sameSource(scan, row) && instantOf(row) === instantOf(scan)
      && placeOf(row) === placeOf(scan) && codesAgree(scan, row)
      && MARKUP.test(words) && !MARKUP.test(String(scan.description ?? '')) && wordingOf(scan) !== ''
      && words.replace(MARKUP_TAGS, ' ').replace(/\s+/g, ' ').trim().toLocaleLowerCase('en-US') === wordingOf(scan);
  });
}

const UNIVERSAL = 'unknown';
const MINUTE_MS = 60 * 1_000;
const QUARTER_HOUR_MS = 15 * MINUTE_MS;
const HOUR_MS = 60 * 60 * 1_000;
const MAX_ZONE_SHIFT_MS = 14 * HOUR_MS;

/**
 * Whole quarter hours apart, up to 14 h: one scan read in the wrong zone. A
 * copy may drop the milliseconds a carrier keeps (Vinted Go, InPost), so a
 * time in whole seconds is compared to the other's second.
 */
function zoneApart(left: number, right: number): boolean {
  const second = (at: number) => Math.floor(at / 1_000) * 1_000;
  const shift = left % 1_000 === 0 || right % 1_000 === 0 ? second(left) - second(right) : left - right;
  return shift !== 0 && shift % QUARTER_HOUR_MS === 0 && Math.abs(shift) <= MAX_ZONE_SHIFT_MS;
}

/** `zoneApart` for a copy of a carrier's scan in whole minutes, at the copy's minute: 17TRACK adds seconds to USPS's minute. */
function minuteApart(copy: number, scan: number): boolean {
  return scan % MINUTE_MS === 0 && zoneApart(Math.floor(copy / MINUTE_MS) * MINUTE_MS, scan);
}

function sourceOf(id: string): string {
  const separator = id.indexOf(':');
  return separator > 0 ? id.slice(0, separator) : '';
}

function instantOf(row: JsonObject): number {
  return Date.parse(String(row.occurred_at ?? ''));
}

function wordingOf(row: JsonObject): string {
  return String(row.description ?? '').replace(/\s+/g, ' ').trim().toLocaleLowerCase('en-US');
}

function stageOf(row: JsonObject): string {
  return typeof row.stage === 'string' ? row.stage : '';
}

function providerOf(row: JsonObject): string {
  const name = row[UNIVERSAL_PROVIDER] ?? (isRecord(row.raw_data) ? row.raw_data[UNIVERSAL_PROVIDER] : undefined);
  return typeof name === 'string' ? name : '';
}

/** A stored row that only the scan's universal provider has written, so it follows that provider's clock. */
function followsClock(row: JsonObject, scan: JsonObject): boolean {
  return providerOf(row) !== '' && providerOf(row) === providerOf(scan);
}

/** A stage change the sync stamped with the time it saw it, not a provider scan. */
function observedOnly(row: JsonObject): boolean {
  return row.observed_without_provider_timestamp === true
    || (isRecord(row.raw_data) && row.raw_data.observed_without_provider_timestamp === true);
}

/** A row stored with no stage of its own: no map or rule read its words, or it is a notice keeping the stage before it. */
function stageless(row: JsonObject): boolean {
  return (row.stage_source ?? (isRecord(row.raw_data) ? row.raw_data.stage_source : undefined)) === 'none';
}

/** A scan dated by its day alone: it stands at the start of that day, with no clock that could be off. */
function dayAlone(row: JsonObject): boolean {
  return scanDay(isRecord(row.raw_data) ? row.raw_data : row) !== null;
}

/**
 * The same wording, or one with a further sentence after it (GOFO's support
 * line) or before it (ParcelsApp's heading for an Ecoscooting scan), or a
 * detail after an arrow (17TRACK's "Arrived at USPS Facility -> Your item…").
 */
function sameWording(left: string, right: string): boolean {
  if (!left || !right) return false;
  if (left === right) return true;
  const [shorter, longer] = left.length < right.length ? [left, right] : [right, left];
  return (/[.!?]$/.test(shorter) && longer.startsWith(`${shorter} `))
    || longer.startsWith(`${shorter} -> `)
    || (longer.endsWith(` ${shorter}`) && /[.!?]$/.test(longer.slice(0, -shorter.length - 1)));
}

/**
 * Whether one wording opens with every word of the other, whatever the punctuation:
 * ParcelsApp's "Colis retardé durant l'acheminement. Livraison reportée…" and
 * Chronopost's "Colis retardé durant l'acheminement".
 */
function opensAlike(left: JsonObject, right: JsonObject): boolean {
  const words = (row: JsonObject) => wordingOf(row).split(/[^\p{L}\p{N}]+/u).filter(Boolean);
  const [first, second] = [words(left), words(right)];
  const [shorter, longer] = first.length < second.length ? [first, second] : [second, first];
  return shorter.length > 0 && shorter.every((word, index) => longer[index] === word);
}

/** Of several rows that could hold a scan, the one whose wording opens alike, when only one does. */
function openingAlike(scan: JsonObject, rows: readonly JsonObject[]): JsonObject | undefined {
  const alike = rows.filter((row) => opensAlike(scan, row));
  return alike.length === 1 ? alike[0] : undefined;
}

/**
 * Two times of one second or minute, one of them that whole second or minute:
 * a source that keeps only seconds (ParcelsApp's copy of Nova Poshta's
 * milliseconds) or only minutes.
 */
function sameTruncated(left: number, right: number): boolean {
  return left !== right && [1_000, MINUTE_MS].some((unit) => {
    const whole = (at: number) => Math.floor(at / unit) * unit;
    return whole(left) === whole(right) && (left === whole(left) || right === whole(right));
  });
}

/** Stages that can name one scan: the same, or one source left it unclassified. */
function stagesAgree(left: string, right: string): boolean {
  return left === right || [left, right].some((stage) => stage === '' || stage === 'pending');
}

/** The provider codes of one scan: the same when both carry one. */
function codesAgree(left: JsonObject, right: JsonObject): boolean {
  return !providerCode(left) || !providerCode(right) || providerCode(left) === providerCode(right);
}

function placeOf(row: JsonObject): string {
  return String(row.location ?? '').replace(/\s+/g, ' ').trim().toLocaleLowerCase('en-US');
}

/**
 * A copy a whole number of hours from a scan, up to 14 h: the scan read in
 * another zone. A copy in whole minutes is compared to the scan's minute, a
 * copy in whole seconds to its second.
 */
function hoursApart(copy: number, scan: number): boolean {
  const unit = copy % MINUTE_MS === 0 ? MINUTE_MS : 1_000;
  const shift = copy - Math.floor(scan / unit) * unit;
  return shift !== 0 && shift % HOUR_MS === 0 && Math.abs(shift) <= MAX_ZONE_SHIFT_MS;
}

/**
 * How far a copy is from a scan read in another zone: a nonzero number of
 * quarter hours up to 14 h, compared to the minute when either is in whole
 * minutes (17TRACK adds seconds of its own to a carrier's minute) and to the
 * second otherwise. Zero for any other gap.
 */
function zoneShift(copy: number, scan: number): number {
  const unit = copy % MINUTE_MS === 0 || scan % MINUTE_MS === 0 ? MINUTE_MS : 1_000;
  const shift = Math.floor(copy / unit) * unit - Math.floor(scan / unit) * unit;
  return shift % QUARTER_HOUR_MS === 0 && Math.abs(shift) <= MAX_ZONE_SHIFT_MS ? shift : 0;
}

/**
 * The words of a scan's place, without accents, case or countries ("Paris, FR"
 * and "PARIS CDG" share paris), umlauts spelt out (ZUERICH is Zürich). A word
 * with letters beyond a to z (Cyrillic, Chinese, ł) is left out.
 */
export function placeWords(row: JsonObject): string[] {
  return trackingPlace(String(row.location ?? '')).place.normalize('NFD').replace(/\p{Diacritic}/gu, '').toLowerCase()
    .split(/[^\p{L}\p{N}]+/u)
    .filter((word) => /^[a-z0-9]+$/.test(word) && trackingPlace(word.toUpperCase()).country === null)
    .map((word) => word.replace(/([aou])e/g, '$1'));
}

/** Two places named with no word in common. A place that names no more than a country conflicts with none. */
export function placesConflict(left: readonly string[], right: readonly string[]): boolean {
  return left.length > 0 && right.length > 0 && !left.some((word) => right.includes(word));
}

/** Each place's town, by the gazetteer's coordinates: one key for every spelling it knows ("Warsaw", "Warszawa"). */
const towns = new Map<string, string | null>();

function townOf(row: JsonObject): string | null {
  const location = String(row.location ?? '').trim();
  if (location === '') return null;
  let town = towns.get(location);
  if (town === undefined) {
    let place = null;
    try {
      place = locatePlace(location);
    } catch { /* A place the gazetteer cannot read is in no known town. */ }
    town = place?.precision === 'city' ? `${place.country} ${place.latitude} ${place.longitude}` : null;
    if (towns.size >= 10_000) towns.clear();
    towns.set(location, town);
  }
  return town;
}

/**
 * Two scans' places that conflict (`placesConflict`), unless the gazetteer finds both in one
 * town: ParcelsApp's English for Poczta Polska's "Sortownia Warszawa" names no other place.
 */
function placesApart(row: JsonObject, other: JsonObject): boolean {
  if (!placesConflict(placeWords(row), placeWords(other))) return false;
  const town = townOf(row);
  return town === null || town !== townOf(other);
}

/**
 * A provider's copy that shows a carrier's scan on the scan's wall clock under another offset, to
 * the minute, in any wording (ParcelsApp gave Maersk eCommerce's US scans their wall clock as UTC,
 * in words and places of its own), with stages and codes that agree and places that do not conflict.
 */
function readsWallClock(copy: JsonObject, scan: JsonObject): boolean {
  const [shown, own] = [labelledTime(copy), labelledTime(scan)];
  return shown !== null && own !== null && shown.offset !== own.offset && shown.toFormat(MINUTE_CLOCK) === own.toFormat(MINUTE_CLOCK)
    && stagesAgree(stageOf(copy), stageOf(scan)) && codesAgree(copy, scan) && !placesApart(copy, scan);
}

/**
 * The providers whose copies read two or more of a parcel's carrier scans that way, each copy the
 * one scan it reads (`readsWallClock`): the provider's clock, not two scans of one minute by chance.
 */
function wallClockReaders(copies: readonly JsonObject[], scans: readonly JsonObject[]): Set<string> {
  const read = new Map<string, Set<string>>();
  for (const copy of copies) {
    const twins = scans.filter((scan) => readsWallClock(copy, scan));
    if (twins.length === 1) read.set(providerOf(copy), (read.get(providerOf(copy)) ?? new Set<string>()).add(identity(twins[0]!)));
  }
  return new Set([...read].filter(([, scans]) => scans.size >= 2).map(([provider]) => provider));
}

/** A scan with a clock to compare: not dated by its day alone, nor at a whole hour, which unrelated scans share by chance. */
function minuteClock(row: JsonObject): boolean {
  return !dayAlone(row) && instantOf(row) % HOUR_MS !== 0;
}

/** Another provider's row that can name a scan: stages that agree, places that do not conflict. */
function alike(scan: JsonObject, row: JsonObject): boolean {
  return stagesAgree(stageOf(scan), stageOf(row)) && !placesApart(scan, row);
}

/**
 * The shift most of a provider's scans show from another provider's rows that are alike, in whole
 * quarter hours up to 14 h (`zoneShift`): two instants at least, more than any other shift and more
 * than meet a row at their own instant. One of the two reads the parcel's clock in another zone, in
 * wording and places of its own: 17TRACK read a Dutch post's summer time as London's, ParcelsApp
 * China Post's as UTC. Zero when no shift stands out.
 */
function providerShift(scans: readonly JsonObject[], rows: readonly JsonObject[]): number {
  const shifts = new Map<number, Set<number>>();
  for (const scan of scans) {
    for (const row of rows) {
      if (!alike(scan, row)) continue;
      const same = instantOf(scan) === instantOf(row) || sameTruncated(instantOf(scan), instantOf(row));
      const shift = same ? 0 : zoneShift(instantOf(scan), instantOf(row));
      if (same || shift !== 0) shifts.set(shift, (shifts.get(shift) ?? new Set<number>()).add(instantOf(scan)));
    }
  }
  const met = shifts.get(0)?.size ?? 0;
  const [first, second] = [...shifts].filter(([shift]) => shift !== 0).sort(([, left], [, right]) => right.size - left.size);
  return first && first[1].size >= 2 && first[1].size > met && (!second || second[1].size < first[1].size) ? first[0] : 0;
}

export interface SharedScans {
  /**
   * A scan's computed identity → the stored row it takes over in place: a
   * carrier's scan the universal copy, a universal scan a row only its
   * provider wrote, on another clock (its own row maps to itself).
   */
  reused: Map<string, string>;
  /** A universal scan left out of the batch → the stored identity it repeats. */
  skipped: Map<string, string>;
  /**
   * The scans of `reused` and `skipped` whose row stands at another instant, beyond the minute: one source's clock is off.
   * A scan or row dated by its day alone has no clock.
   */
  shifted: Set<string>;
  /** Stored universal copies of the carrier's scans at their instant that no scan takes over: deleted with the sync. */
  absorbed: Set<string>;
}

/** A row written by a carrier: its prefix, or a universal row that names no provider, which a carrier's scan took over or an earlier release wrote. */
function heldByCarrier(row: JsonObject): boolean {
  const source = sourceOf(identity(row));
  return source !== '' && source !== 'app' && (source !== UNIVERSAL || providerOf(row) === '');
}

/**
 * The other source's copy of a classified scan at its instant, from no conflicting place, in
 * whatever stage it reads the scan (17TRACK's exception for UPS's out for delivery).
 */
function copies(scan: JsonObject, row: JsonObject): boolean {
  return instantOf(scan) === instantOf(row) && stageOf(scan) !== '' && !placesApart(scan, row);
}

/** How much later a source may restate one of its scans: a correction of the scan's time, not the next round of scans. */
const RETIMED_WITHIN_MS = 3 * HOUR_MS;

/**
 * Maps the computed identity of each new scan of `source`, the carrier that answered, to the
 * stored row of that source the answer moved to it: USPS moved a departure from 01:55 to 03:00,
 * and then listed only the later one. The scan is no more than three hours after the row, with
 * the same wording, place and stage, and codes that agree when both carry one. The answer no
 * longer lists the row but still lists an older scan of the source, so it is no shorter window
 * of the history, and neither the answer nor the stored rows hold a scan of the source between
 * the two. Scans and rows another rule matched, observations, scans dated by their day alone and
 * universal or app rows are left out, and the match is one to one. A carrier that lists only the
 * latest of a run of the same scan (TNT's daily scan of a parcel held at its depot) moves it to
 * the next day: those are scans of their own, and stay.
 */
export function retimedScans(
  events: readonly JsonObject[],
  stored: readonly JsonObject[],
  source: string,
  matched: ReadonlyMap<string, string> = new Map(),
): Map<string, string> {
  const retimed = new Map<string, string>();
  if (source === '' || source === UNIVERSAL || source === 'app') return retimed;
  const own = (row: JsonObject) => sourceOf(identity(row)) === source && !observedOnly(row) && !dayAlone(row)
    && Number.isFinite(instantOf(row));
  const storedIds = new Set(stored.map(identity));
  const listed = new Set([...events.map(identity), ...matched.values()]);
  const answer = events.filter(own);
  const held = [...answer, ...stored.filter(own)];
  const scans = answer.filter((scan) => !storedIds.has(identity(scan)) && !matched.has(identity(scan)));
  const rows = stored.filter((row) => own(row) && !listed.has(identity(row)));
  const earliest = Math.min(...answer.map(instantOf));
  const alike = (scan: JsonObject, row: JsonObject) => {
    const [from, to] = [instantOf(row), instantOf(scan)];
    return to > from && to - from <= RETIMED_WITHIN_MS && earliest < from
      && wordingOf(scan) !== '' && wordingOf(scan) === wordingOf(row) && placeOf(scan) === placeOf(row)
      && stageOf(scan) !== '' && stageOf(scan) === stageOf(row) && codesAgree(scan, row);
  };
  for (const scan of scans) {
    const matching = rows.filter((row) => alike(scan, row));
    if (matching.length !== 1) continue;
    const row = matching[0]!;
    if (scans.filter((other) => alike(other, row)).length !== 1) continue;
    if (held.some((other) => instantOf(other) > instantOf(row) && instantOf(other) < instantOf(scan))) continue;
    retimed.set(identity(scan), identity(row));
  }
  return retimed;
}

/**
 * Maps the computed identity of each new scan to the stored row of its old wording: a source
 * rewords a scan it lists and no longer lists the old words (DPD UK rewrote a failed attempt
 * when it delivered the parcel). The row is of the same source, at the same instant and in the
 * same stage, or in any stage when it had none of its own (a release that gives a carrier's
 * words in English and maps them), with codes that agree when both carry one, and no scan of
 * the answer carries it.
 * Scans and rows another rule matched, observations, scans dated by their day alone and
 * universal or app rows are left out, and the match is one to one: two new scans, or two rows,
 * of one instant and stage are scans of their own.
 */
export function rewordedScans(
  events: readonly JsonObject[],
  stored: readonly JsonObject[],
  matched: ReadonlyMap<string, string> = new Map(),
): Map<string, string> {
  const reworded = new Map<string, string>();
  const own = (row: JsonObject) => {
    const source = sourceOf(identity(row));
    return source !== '' && source !== UNIVERSAL && source !== 'app' && stageOf(row) !== ''
      && !observedOnly(row) && !dayAlone(row) && Number.isFinite(instantOf(row));
  };
  const storedIds = new Set(stored.map(identity));
  const listed = new Set([...events.map(identity), ...matched.values()]);
  const scans = events.filter((scan) => own(scan) && !storedIds.has(identity(scan)) && !matched.has(identity(scan)));
  const rows = stored.filter((row) => own(row) && !listed.has(identity(row)));
  const alike = (scan: JsonObject, row: JsonObject) => sourceOf(identity(scan)) === sourceOf(identity(row))
    && instantOf(scan) === instantOf(row) && (stageOf(scan) === stageOf(row) || stageless(row)) && codesAgree(scan, row);
  for (const scan of scans) {
    const matching = rows.filter((row) => alike(scan, row));
    if (matching.length !== 1) continue;
    const row = matching[0]!;
    if (scans.filter((other) => alike(other, row)).length !== 1) continue;
    reworded.set(identity(scan), identity(row));
  }
  return reworded;
}

/**
 * Keeps one row per scan when a carrier and a universal provider report it.
 *
 * Only scans whose own identity is not stored are matched, against stored rows
 * that no event of this batch carries and `reused` has not taken, never the
 * app's own rows, and never a scan the sync observed without a provider time.
 * A row matches a scan at the exact same instant when it is the only row there
 * with the same wording, or the only row there at all, with a stage that
 * agrees (La Poste's French scan and a provider's unclassified English copy),
 * and the scan the only new scan there. With no row at that instant, a row at
 * an instant no scan of the batch shows matches when one of the two is dated
 * to the second or minute the other falls in (UPS to the second, a provider to
 * the minute): with the same wording, or else alone in other wording when a
 * provider's copy and a carrier's scan meet, one to the minute and the other
 * to the second, or both in one second, one of them to the whole second, in
 * any stage and from no conflicting place (FedEx's own wording of a scan a
 * provider copied, 17TRACK's seconds on Shiprocket's minutes, ParcelsApp's
 * whole second of CTT Express's milliseconds). The `relaying` carrier, the one
 * a parcel was handed over from, retells the next one's scans to the minute,
 * as India Post does La Poste's, or in the same second, so neither its scan to
 * the minute nor one in the copy's second meets a copy to the second. Of
 * several rows in other wording there, the one whose wording opens with every
 * word of the scan's, or the scan's with every word of its, is the match
 * (ParcelsApp dates two Chronopost scans to one minute, and adds the reason
 * after "Colis retardé durant l'acheminement"). Failing that, the same wording matches a
 * whole number of quarter hours up to 14 h away: the same scan read in the
 * wrong zone, a copy compared to the minute of a carrier's scan in whole
 * minutes (17TRACK's seconds on USPS's minute). Failing that, a carrier's row for a universal scan, or a
 * universal row for a carrier's scan, at least an hour away matches in any
 * wording when the stages agree and the time has seconds other than zero: a provider whose clock is
 * off by a zone's offset from UTC. Times to the minute fall whole quarter
 * hours apart too often by chance, and carriers stamp some scans 15 or 30
 * minutes after another to the second (Swiss Post sorts a parcel a quarter
 * hour after customs clear it). Across a zone's offset, two places that share
 * no word, countries aside, are two scans. Failing all that, a carrier's scan
 * matches a provider's copy that shows its wall clock, to the minute, under
 * another offset, in any wording, with stages and codes that agree, when the
 * copy's wording opens alike or its provider reads two of the parcel's
 * carrier scans that way (`readsWallClock`). A row that two scans match is
 * ambiguous and stays unmatched.
 *
 * A carrier's scan takes over the universal row it matches. A universal scan
 * that matches any row is left out, unless it states the offset of a carrier's
 * row whose zone the scraper deduced, at the row's wall clock: it takes the row
 * over, and the carrier's scan keeps that offset (`withStatedClocks`). A universal scan whose own identity
 * is stored at another instant is left out too: a carrier's scan has taken that row over and corrected
 * its time. So is one whose own row a carrier's scan took over at its instant in other words or at
 * another place, so the row keeps the carrier's words instead of switching with each source that
 * answers (ParcelsApp's copies of FedEx's scans while FedEx's own lookup is challenged).
 *
 * At each instant of the batch's classified carrier scans, every other
 * universal row from no conflicting place copies one of them, in whatever
 * stage it reads it: a provider that reads a scan twice (Speedaf's ParcelsApp
 * rows), two providers that each copied it (InPost), or one that reads it as
 * another stage (17TRACK's exception for UPS's out for delivery). A scan
 * that matched no row takes one over, in its own stage when one is, and the
 * others are absorbed. Which of several scans at the instant takes which copy
 * does not matter, as the scan rewrites the row; the carrier's own scans there
 * stay apart (FedEx's departure and "On the way"). A universal scan that copies
 * a row a carrier holds at its instant, by its prefix or because the row names
 * no provider, is left out, so an absorbed copy does not come back with the
 * provider's next answer. Of several such rows it repeats the one whose wording
 * opens alike, when one alone does, else the first. A universal scan to the
 * whole second repeats the one row a carrier holds in that second, unless the
 * row is an earlier carrier's relay (Ship24 copies a CTT Express scan twice,
 * in CTT Express's words and in CTT Portugal's, without the milliseconds).
 *
 * A provider's scan that matched nothing repeats another provider's row at the
 * shift most of its scans show from that provider's rows, in whole quarter
 * hours up to 14 h, in any wording, with stages that agree and places that do
 * not conflict: two instants at least, more than any other shift and more than
 * meet a row at their own instant (17TRACK's copies of a Dutch post's scans,
 * its summer time read as London's). It is left out and the shift flagged.
 * Scans dated by their day alone or at a whole hour do not count.
 *
 * A row that only the scan's universal provider has written follows that
 * provider's clock instead (a scraper fix that reads its scans in another
 * zone), and the stage a fix now reads in a scan the row holds unclassified,
 * at the row's instant from no conflicting place: the scan takes the row over. A scan whose own row it is moves the row to the scan's instant,
 * unless another row matches the scan by the rules above: that row holds the
 * scan already, so the scan is left out and its row on the old clock goes. So
 * does such a row the fix gave another identity, when the provider's answer no
 * longer shows its instant, shows an older scan, and shows the scan a zone
 * shift away where another row holds it. A scan that matches such a
 * row across an offset, not just within a minute, takes it over as a carrier's
 * scan would.
 *
 * A row only a universal provider wrote, a whole number of hours from a
 * carrier's own row, up to 14 h, in the same wording and place, with stages
 * and provider codes that agree, is a copy an earlier release read on the
 * wrong clock: it is absorbed, and a universal scan that matches a stored
 * carrier row that way is left out. A copy in whole minutes is compared to
 * the carrier's minute. So is one, in any wording or stage, at the carrier
 * row's place by the shift in whole quarter hours that most of the
 * provider's copies of the parcel show, two at least. Failing both, so is one
 * on a carrier row's wall clock under another offset by the rule above. A
 * row at the copy's own minute and place, the carrier's or one written before
 * rows named their provider, the provider's reading at the carrier row's
 * minute, a scan that takes the row over or repeats it, or a second copy of
 * the carrier row from the provider keeps it.
 */
export function sharedScans(
  events: readonly JsonObject[],
  stored: readonly JsonObject[],
  reused: ReadonlyMap<string, string> = new Map(),
  relaying: string | null = null,
): SharedScans {
  const shared: SharedScans = { reused: new Map(), skipped: new Map(), shifted: new Set(), absorbed: new Set() };
  if (stored.length === 0) return shared;
  const storedById = new Map(stored.map((row) => [identity(row), row] as const));
  const taken = new Set([...events.map(identity), ...reused.values()]);
  const rows = stored.filter((row) => {
    const source = sourceOf(identity(row));
    return source !== '' && source !== 'app' && !taken.has(identity(row)) && !observedOnly(row)
      && Number.isFinite(instantOf(row));
  });
  const pending: JsonObject[] = [];
  // Universal scans whose own row, which only their provider wrote, stands at another instant.
  const moving: JsonObject[] = [];
  for (const event of events) {
    const id = identity(event);
    const source = sourceOf(id);
    if (source === '' || source === 'app' || reused.has(id) || observedOnly(event) || !Number.isFinite(instantOf(event))) continue;
    const own = storedById.get(id);
    if (!own) pending.push(event);
    else if (source === UNIVERSAL && instantOf(own) !== instantOf(event)) {
      if (followsClock(own, event)) moving.push(event);
      else shared.skipped.set(id, id);
      if (!sameTruncated(instantOf(own), instantOf(event)) && !dayAlone(own) && !dayAlone(event)) shared.shifted.add(id);
    } else if (source === UNIVERSAL && heldByCarrier(own) && (wordingOf(own) !== wordingOf(event) || placeOf(own) !== placeOf(event))) {
      shared.skipped.set(id, id);
    }
  }
  const newAt = new Map<number, number>();
  for (const event of pending) newAt.set(instantOf(event), (newAt.get(instantOf(event)) ?? 0) + 1);
  const occupied = new Set(rows.map(instantOf));
  // A row the batch shows at its own instant is that scan's, not a shifted copy's.
  const shown = new Set(events.map(instantOf));
  // A provider's copy on a carrier scan's wall clock under another offset, from a provider that reads two of the
  // parcel's carrier scans that way, or in wording that opens alike.
  const carrierHeld = [...new Map([...stored, ...events].filter((row) => !['', 'app', UNIVERSAL].includes(sourceOf(identity(row)))
    && !observedOnly(row)).map((row) => [identity(row), row] as const)).values()];
  const readers = wallClockReaders([...rows, ...events].filter((row) => sourceOf(identity(row)) === UNIVERSAL && providerOf(row) !== ''
    && !observedOnly(row)), carrierHeld);
  const readsClock = (copy: JsonObject, scan: JsonObject) => providerOf(copy) !== '' && readsWallClock(copy, scan)
    && (readers.has(providerOf(copy)) || opensAlike(copy, scan));
  /** The row a scan repeats; `alone` when no other new scan stands at its instant. */
  const matchOf = (event: JsonObject, alone: boolean): JsonObject | undefined => {
    const universal = sourceOf(identity(event)) === UNIVERSAL;
    const at = instantOf(event);
    const wording = wordingOf(event);
    const eligible = universal ? rows : rows.filter((row) => sourceOf(identity(row)) === UNIVERSAL);
    const exact = eligible.filter((row) => instantOf(row) === at);
    const worded = exact.filter((row) => sameWording(wordingOf(row), wording));
    const truncated = occupied.has(at) ? [] : eligible.filter((row) => sameTruncated(at, instantOf(row)) && !shown.has(instantOf(row)));
    const minuted = truncated.filter((row) => sameWording(wordingOf(row), wording));
    // Other wording, and any stage, only for a provider's copy and a carrier's scan, one of them to the minute,
    // or both in one second (ParcelsApp's whole second of CTT Express's milliseconds).
    // An earlier carrier's relay of a delivery scan (India Post's to the minute, Nova Poshta's) is no such pair.
    const restaged = stageOf(event) === '' ? [] : truncated.filter((row) => {
      if ((sourceOf(identity(row)) === UNIVERSAL) === universal || placesApart(event, row)) return false;
      const [copy, own] = universal ? [event, row] : [row, event];
      const second = (at: number) => Math.floor(at / 1_000);
      return instantOf(copy) % MINUTE_MS === 0 || (sourceOf(identity(own)) !== relaying
        && (instantOf(own) % MINUTE_MS === 0 || second(instantOf(copy)) === second(instantOf(own))));
    });
    const apart = occupied.has(at) ? [] : eligible.filter((row) => !shown.has(instantOf(row)) && !placesApart(event, row));
    const zoned = apart.filter((row) => zoneApart(at, instantOf(row)));
    const paired = (row: JsonObject) => (sourceOf(identity(row)) === UNIVERSAL) !== universal;
    const shifted = apart.filter((row) => sameWording(wordingOf(row), wording) && (zoneApart(at, instantOf(row))
      || (paired(row) && (universal ? minuteApart(at, instantOf(row)) : minuteApart(instantOf(row), at)))));
    const reclocked = Math.floor(at / 1_000) % 60 === 0 ? [] : zoned.filter((row) => (
      (sourceOf(identity(row)) === UNIVERSAL) !== universal && Math.abs(at - instantOf(row)) >= HOUR_MS
      && stagesAgree(stageOf(row), stageOf(event))
    ));
    const clocked = universal ? [] : apart.filter((row) => readsClock(row, event));
    const staged = exact.length === 1 && alone && stageOf(event) !== '' && stagesAgree(stageOf(exact[0]!), stageOf(event));
    return worded.length === 1 ? worded[0]
      : worded.length === 0 && staged ? exact[0]
        : minuted.length > 0 ? (minuted.length === 1 ? minuted[0] : undefined)
          : restaged.length > 0 ? (restaged.length === 1 ? restaged[0] : openingAlike(event, restaged))
            : shifted.length === 1 ? shifted[0]
              : shifted.length === 0 && reclocked.length === 1 ? reclocked[0]
                : shifted.length + reclocked.length === 0 && clocked.length === 1 ? clocked[0] : undefined;
  };
  const picks = new Map<string, JsonObject[]>();
  for (const event of pending) {
    const match = matchOf(event, newAt.get(instantOf(event)) === 1);
    if (match) picks.set(identity(match), [...(picks.get(identity(match)) ?? []), event]);
  }
  for (const [rowId, scans] of picks) {
    if (scans.length !== 1) continue;
    const scan = scans[0]!;
    const id = identity(scan);
    const row = storedById.get(rowId)!;
    const offset = instantOf(scan) !== instantOf(row) && !sameTruncated(instantOf(scan), instantOf(row));
    const restated = offset && sourceOf(id) === UNIVERSAL && restates(scan, row);
    // The provider's own row of a scan it gave in words no release classified then, which a fix now reads as a stage
    // (ParcelsApp's Russian Post delivery): the row takes it rather than staying pending beside the parcel's stage.
    const classified = instantOf(scan) === instantOf(row) && followsClock(row, scan) && stageOf(row) === 'pending'
      && !['', 'pending'].includes(stageOf(scan)) && !placesApart(scan, row);
    if (sourceOf(id) !== UNIVERSAL || (offset && followsClock(row, scan)) || restated || classified) shared.reused.set(id, rowId);
    else shared.skipped.set(id, rowId);
    if (offset && !restated && !dayAlone(row) && !dayAlone(scan)) shared.shifted.add(id);
  }
  // Each copy at a carrier's instant goes: a scan that matched no row takes one over, and the others are absorbed.
  const kept = new Set([...shared.reused.values(), ...shared.skipped.values()]);
  const waiting = new Set(pending.map(identity));
  const carrierScans = byInstant(events.filter((event) => (
    !['', 'app', UNIVERSAL].includes(sourceOf(identity(event))) && !observedOnly(event)
  )));
  for (const [at, scans] of carrierScans) {
    const copied = rows.filter((row) => (
      instantOf(row) === at && sourceOf(identity(row)) === UNIVERSAL && !kept.has(identity(row))
      && scans.some((scan) => copies(scan, row))
    ));
    for (const scan of scans) {
      const id = identity(scan);
      if (!waiting.has(id) || shared.reused.has(id)) continue;
      const fit = copied.filter((row) => copies(scan, row));
      const row = fit.find((candidate) => stageOf(candidate) === stageOf(scan)) ?? fit[0];
      if (!row) continue;
      shared.reused.set(id, identity(row));
      copied.splice(copied.indexOf(row), 1);
    }
    for (const row of copied) shared.absorbed.add(identity(row));
  }
  // A copy of a scan a carrier holds stays out, so the copies it absorbed do not come back.
  const held = stored.filter((row) => heldByCarrier(row) && !observedOnly(row));
  // A copy to the whole second repeats the one row a carrier holds in that second (Ship24 tells a CTT Express scan in
  // its words and in CTT Portugal's, both without the milliseconds): two such copies meet the row, which the one-to-one
  // match above gives to neither. An earlier carrier's relay in the second is no such row.
  const heldInSecond = (event: JsonObject) => {
    const at = instantOf(event);
    const rows = at % 1_000 === 0 ? held.filter((row) => Math.floor(instantOf(row) / 1_000) * 1_000 === at) : [];
    const row = rows.length === 1 ? rows[0]! : undefined;
    return row && stageOf(event) !== '' && sourceOf(identity(row)) !== relaying && !placesApart(event, row) ? row : undefined;
  };
  const heldCopy = (event: JsonObject) => {
    const twins = held.filter((row) => copies(event, row));
    return twins.length > 1 ? openingAlike(event, twins) ?? twins[0] : twins[0] ?? heldInSecond(event);
  };
  for (const event of pending) {
    const id = identity(event);
    if (sourceOf(id) !== UNIVERSAL || shared.skipped.has(id) || shared.reused.has(id)) continue;
    const twin = heldCopy(event);
    if (twin) shared.skipped.set(id, identity(twin));
  }
  // A provider's scan that matched nothing, at the shift most of its scans show from another provider's
  // rows (`providerShift`), repeats the row there: the row holds the scan, and the shift is flagged.
  const unmatched = pending.filter((event) => sourceOf(identity(event)) === UNIVERSAL && providerOf(event) !== ''
    && !shared.skipped.has(identity(event)) && !shared.reused.has(identity(event)) && minuteClock(event));
  const providerRows = rows.filter((row) => sourceOf(identity(row)) === UNIVERSAL && providerOf(row) !== '' && minuteClock(row));
  for (const provider of new Set(unmatched.map(providerOf))) {
    const scans = events.filter((event) => sourceOf(identity(event)) === UNIVERSAL && providerOf(event) === provider
      && !observedOnly(event) && Number.isFinite(instantOf(event)) && minuteClock(event));
    for (const other of new Set(providerRows.map(providerOf))) {
      const theirs = providerRows.filter((row) => providerOf(row) === other);
      const shift = other === provider ? 0 : providerShift(scans, theirs);
      if (shift === 0) continue;
      for (const event of unmatched) {
        const id = identity(event);
        if (providerOf(event) !== provider || shared.skipped.has(id)) continue;
        const twins = theirs.filter((row) => alike(event, row) && zoneShift(instantOf(event), instantOf(row)) === shift);
        const twin = twins.length > 1 ? openingAlike(event, twins) ?? twins[0] : twins[0];
        if (!twin) continue;
        shared.skipped.set(id, identity(twin));
        shared.shifted.add(id);
      }
    }
  }
  // Moving its own row beside another row that holds the scan would store the scan twice:
  // the scan stays out, and its own row, on the clock the fix corrected, goes.
  for (const event of moving) {
    const id = identity(event);
    if (matchOf(event, !newAt.has(instantOf(event))) || heldCopy(event)) {
      shared.skipped.set(id, id);
      shared.absorbed.add(id);
    } else shared.reused.set(id, id);
  }
  // A fix can change the time a scan's identity hashes too. A row its provider alone wrote,
  // at an instant the provider's answer no longer shows though another of its scans is older,
  // is the stale copy of a scan the answer shows a zone shift away, once another row holds it.
  const holders = new Map<string, string>();
  for (const event of events) {
    const id = identity(event);
    const holder = shared.reused.get(id) ?? shared.skipped.get(id) ?? (storedById.has(id) ? id : undefined);
    if (holder) holders.set(id, holder);
  }
  const claimed = new Set([...holders.values(), ...shared.absorbed]);
  for (const row of rows) {
    const id = identity(row);
    if (sourceOf(id) !== UNIVERSAL || providerOf(row) === '' || claimed.has(id) || shown.has(instantOf(row))) continue;
    const answer = events.filter((event) => followsClock(row, event));
    const copy = answer.find((event) => holders.has(identity(event)) && zoneApart(instantOf(event), instantOf(row))
      && sameWording(wordingOf(event), wordingOf(row)) && !placesApart(event, row));
    if (copy && answer.some((event) => event !== copy && instantOf(event) < instantOf(row))) shared.absorbed.add(id);
  }
  // A provider's copy of a carrier's row stored before a fix read the provider's clock right goes,
  // and the provider's copies at that time stay out: one a whole number of hours away in the same
  // wording and place (ParcelsApp's copy of UPS's customs scan, 11 h late), or one at the shift in
  // whole quarter hours that most of the provider's copies of the parcel show, two at least, in
  // any wording or stage at the carrier row's place (17TRACK's copies of Shiprocket's scans, India's
  // clock read as US Eastern). Failing both, one on a carrier row's wall clock under another offset
  // (`readsClock`). A row at the copy's own minute and place, the provider's reading at
  // the carrier row's minute, or a second copy of the row from the provider makes it another scan.
  // A universal scan is matched with stored rows alone.
  const carrierRows = stored.filter((row) => !taken.has(identity(row)) && heldByCarrier(row) && !observedOnly(row)
    && Number.isFinite(instantOf(row)));
  const batchScans = events.filter((event) => heldByCarrier(event) && !observedOnly(event) && Number.isFinite(instantOf(event)));
  const minute = (at: number) => Math.floor(at / MINUTE_MS);
  const own = (row: JsonObject) => sourceOf(identity(row)) !== UNIVERSAL;
  const sameScan = (copy: JsonObject, row: JsonObject) => wordingOf(copy) !== '' && wordingOf(row) === wordingOf(copy)
    && placeOf(row) === placeOf(copy) && codesAgree(copy, row) && stagesAgree(stageOf(row), stageOf(copy));
  // Each copy, with the rows it is matched with.
  const candidates = new Map<string, readonly [JsonObject, readonly JsonObject[]]>();
  for (const event of events) {
    const id = identity(event);
    if (sourceOf(id) !== UNIVERSAL || providerOf(event) === '' || reused.has(id) || shared.reused.has(id)
      || shared.skipped.has(id) || observedOnly(event) || !Number.isFinite(instantOf(event))) continue;
    candidates.set(id, [event, carrierRows]);
  }
  // A row another scan takes over or repeats holds that scan.
  const holding = new Set([...shared.reused.values(), ...shared.skipped.values(), ...shared.absorbed]);
  for (const row of rows) {
    const id = identity(row);
    if (sourceOf(id) !== UNIVERSAL || providerOf(row) === '' || holding.has(id) || candidates.has(id)) continue;
    candidates.set(id, [row, [...carrierRows, ...batchScans]]);
  }
  // The shift most of a provider's copies show from carrier rows of the same scan, when two do and no other shift as many.
  const shiftsOf = new Map<string, Map<number, Set<string>>>();
  for (const [id, [copy, among]] of candidates) {
    const shifts = shiftsOf.get(providerOf(copy)) ?? new Map<number, Set<string>>();
    shiftsOf.set(providerOf(copy), shifts);
    for (const row of among) {
      const shift = own(row) && sameScan(copy, row) ? zoneShift(instantOf(copy), instantOf(row)) : 0;
      if (shift !== 0) shifts.set(shift, (shifts.get(shift) ?? new Set()).add(id));
    }
  }
  const clocks = new Map<string, number>();
  for (const [provider, shifts] of shiftsOf) {
    const [first, second] = [...shifts].sort(([, left], [, right]) => right.size - left.size);
    if (first && first[1].size >= 2 && (!second || second[1].size < first[1].size)) clocks.set(provider, first[0]);
  }
  const carrierRowOf = (copy: JsonObject, among: readonly JsonObject[]): JsonObject | undefined => {
    const clock = clocks.get(providerOf(copy));
    const misread = among.filter((row) => own(row) && (clock === undefined
      ? sameScan(copy, row) && hoursApart(instantOf(copy), instantOf(row))
      : placeOf(row) === placeOf(copy) && codesAgree(copy, row) && zoneShift(instantOf(copy), instantOf(row)) === clock));
    const apart = misread.length > 0 ? misread : among.filter((row) => own(row) && readsClock(copy, row));
    if (apart.length !== 1) return undefined;
    const carrier = apart[0]!;
    const at = (row: JsonObject, instant: number) => placeOf(row) === placeOf(copy) && minute(instantOf(row)) === minute(instant);
    return among.some((row) => at(row, instantOf(copy)))
      || events.some((event) => providerOf(event) === providerOf(copy) && at(event, instantOf(carrier))) ? undefined : carrier;
  };
  const stale = new Map<string, JsonObject>();
  for (const [id, [copy, among]] of candidates) {
    const carrier = carrierRowOf(copy, among);
    if (carrier) stale.set(id, carrier);
  }
  const copiesOf = new Map<string, number>();
  const key = (id: string, carrier: JsonObject) => `${identity(carrier)}\n${providerOf(candidates.get(id)![0])}`;
  for (const [id, carrier] of stale) copiesOf.set(key(id, carrier), (copiesOf.get(key(id, carrier)) ?? 0) + 1);
  for (const [id, carrier] of stale) {
    if (copiesOf.get(key(id, carrier)) !== 1) continue;
    if (storedById.has(id)) shared.absorbed.add(id);
    if (events.some((event) => identity(event) === id)) shared.skipped.set(id, storedById.has(identity(carrier)) ? identity(carrier) : id);
  }
  return shared;
}

const BARE_DATES = ['yyyy-MM-dd', 'dd.MM.yyyy', 'dd/MM/yyyy'];
/**
 * The day of a scan the carrier dated without a clock time: a bare date given as its time, or
 * its local time when that is a bare date and its time gives no instant (NACEX's calendar headings).
 */
export function scanDay(scan: JsonObject): string | null {
  const time = typeof scan.time === 'string' ? scan.time.trim() : '';
  const day = time ? BARE_DATES.map((format) => DateTime.fromFormat(time, format)).find((date) => date.isValid) : undefined;
  if (day) return day.toISODate();
  if (eventTimestamp(time)) return null;
  const local = typeof scan.local_time === 'string' ? DateTime.fromFormat(scan.local_time, 'yyyy-MM-dd') : undefined;
  return local?.isValid ? local.toISODate() : null;
}

const LOCAL_CLOCK = /^(\d{4}-\d{2}-\d{2})T\d{2}:\d{2}(?::\d{2})?$/;
/**
 * The day of a scan the carrier gave a wall clock in no zone (`local_time`) and no instant:
 * YunExpress dates some scans of a history so and the others in full.
 */
export function clockDay(scan: JsonObject): string | null {
  if (eventTimestamp(timeOf(scan).trim())) return null;
  const day = LOCAL_CLOCK.exec(localTimeOf(scan))?.[1];
  return day && DateTime.fromISO(day).isValid ? day : null;
}

/** The wall clock a row kept (`local_time`), with or without the time of day. */
function localTimeOf(row: JsonObject): string {
  const local = row.local_time ?? (isRecord(row.raw_data) ? row.raw_data.local_time : undefined);
  return typeof local === 'string' ? local : '';
}

/** How a carrier's scan on a wall clock in no zone got its offset: `stated` once another source gave it (`withStatedClocks`). */
const STATED = 'stated';

function rawOf(row: JsonObject, key: string): unknown {
  return row[key] ?? (isRecord(row.raw_data) ? row.raw_data[key] : undefined);
}

/**
 * The wall clock and offset the carrier stated for a scan: a provider's copy
 * that kept the carrier's own offset (17TRACK's `carrier_reported` time), or
 * a carrier's scan that took one.
 */
function statedClock(row: JsonObject): DateTime | null {
  const time = rawOf(row, 'time_provenance') === 'carrier_reported' ? rawOf(row, 'provider_time_iso')
    : rawOf(row, 'time_zone_basis') === STATED ? rawOf(row, 'time') : undefined;
  if (typeof time !== 'string' || !NUMERIC_OFFSET.test(time)) return null;
  const clock = DateTime.fromISO(time, { setZone: true });
  return clock.isValid ? clock : null;
}

/** The wall clock, to the minute, of a carrier's scan whose zone the scraper deduced (`time_zone_basis`). */
function deducedClock(row: JsonObject): string | null {
  const basis = rawOf(row, 'time_zone_basis');
  const local = localTimeOf(row);
  return typeof basis === 'string' && basis !== STATED && LOCAL_CLOCK.test(local) ? local.slice(0, 16) : null;
}

/** A copy that states the offset of a carrier's row whose zone was deduced, at the row's wall clock. */
function restates(copy: JsonObject, row: JsonObject): boolean {
  const clock = statedClock(copy);
  return clock !== null && clock.toFormat(MINUTE_CLOCK) === deducedClock(row);
}

/**
 * A carrier's scan whose zone the scraper deduced for its wall clock takes the
 * offset the carrier stated elsewhere for that wall clock, to the minute, in
 * the same wording and from no conflicting place: in a provider's copy, stored
 * or in the batch (17TRACK's USPS times, which USPS's own scans lack), or in
 * the scan's row that took it, so the offset outlasts the copy. The scan then
 * says so (`time_zone_basis` `stated`, its `time` at that offset). Stated
 * offsets that disagree leave it as it is.
 */
export function withStatedClocks(events: readonly JsonObject[], stored: readonly JsonObject[]): JsonObject[] {
  const stated = [...stored, ...events].flatMap((row) => {
    const clock = statedClock(row);
    return clock && !observedOnly(row) ? [{ row, clock }] : [];
  });
  if (stated.length === 0) return [...events];
  return events.map((event) => {
    const wall = deducedClock(event);
    if (!wall || ['', 'app', UNIVERSAL].includes(sourceOf(identity(event))) || observedOnly(event)) return event;
    const offsets = new Set(stated.filter(({ row, clock }) => clock.toFormat(MINUTE_CLOCK) === wall
      && sameWording(wordingOf(row), wordingOf(event)) && !placesApart(row, event))
      .map(({ clock }) => clock.offset));
    if (offsets.size !== 1) return event;
    const time = DateTime.fromISO(localTimeOf(event), { zone: FixedOffsetZone.instance([...offsets][0]!) });
    if (time.toMillis() === instantOf(event)) return event;
    const raw: JsonObject = { ...(isRecord(event.raw_data) ? event.raw_data : {}),
      time: time.toISO({ suppressMilliseconds: true }), time_zone_basis: STATED };
    delete raw.time_zone;
    return { ...event, occurred_at: time.toUTC().toISO({ suppressMilliseconds: true }), raw_data: raw };
  });
}

/** Whether a scan's instant can be the wall clock a sighting kept: in the scan's zone or another, up to 14 h away. */
export function showsClock(local: string, instant: number): boolean {
  if (local === '') return true;
  const day = /^\d{4}-\d{2}-\d{2}$/.test(local);
  const wall = Date.parse(day ? `${local}T00:00:00Z` : `${local.slice(0, 19)}Z`);
  if (!Number.isFinite(wall)) return true;
  if (day) return instant >= wall - MAX_ZONE_SHIFT_MS && instant < wall + 24 * HOUR_MS + MAX_ZONE_SHIFT_MS;
  const unit = wall % MINUTE_MS === 0 || instant % MINUTE_MS === 0 ? MINUTE_MS : 1_000;
  const shift = Math.floor(wall / unit) * unit - Math.floor(instant / unit) * unit;
  return shift % QUARTER_HOUR_MS === 0 && Math.abs(shift) <= MAX_ZONE_SHIFT_MS;
}

export interface DatedObservations {
  /** A scan's computed identity → the sighting's row it takes over in place. */
  reused: Map<string, string>;
  /** Stored sightings whose scan another row holds: deleted with the sync. */
  absorbed: Set<string>;
  /** Sightings of the batch whose scan a stored row holds: left out. */
  skipped: Set<string>;
}

/**
 * The stage changes the sync stamped with the time it saw them that one dated
 * scan holds (DPD UK's registration once its scans carry London's clock, a
 * provider's copy of a scan the carrier gave on local clocks): the same stage
 * and wording, the same provider code when both carry one, and the same place
 * unless one names none. The scan is no later than the sighting, and a
 * sighting that kept the scan's wall clock shows it, in the scan's zone or
 * whole quarter hours away up to 14 h. A stored sighting is matched with the
 * batch's scans, and with the stored rows too when it kept the time of day,
 * as is a sighting of the batch; without it, a stage seen twice in the same
 * words could be taken for one scan. A sighting two scans could date stays.
 *
 * A scan of the batch new to the parcel, with a time of day, from the
 * sighting's source or dating a universal provider's sighting, takes the
 * stored sighting's row over, so it is not announced again; the row then
 * holds the scan as any row of that source does. Otherwise the sighting is
 * absorbed, or left out of the batch. A stored sighting in words its source
 * no longer gives is absorbed by the scan of its stage before it.
 */
export function datedObservations(
  events: readonly JsonObject[],
  stored: readonly JsonObject[],
  matches: { reused: ReadonlyMap<string, string>; skipped: ReadonlyMap<string, string> },
): DatedObservations {
  const dated: DatedObservations = { reused: new Map(), absorbed: new Set(), skipped: new Set() };
  const storedIds = new Set(stored.map(identity));
  const batchIds = new Set(events.map(identity));
  // A stored row a scan of the batch takes over or repeats is that scan.
  const held = new Set([...batchIds, ...matches.reused.values(), ...matches.skipped.values()]);
  const dates = (row: JsonObject) => !['', 'app'].includes(sourceOf(identity(row))) && !observedOnly(row)
    && Number.isFinite(instantOf(row));
  const scans = events.filter(dates);
  const rows = stored.filter((row) => dates(row) && !held.has(identity(row)));
  const sightings = [...stored, ...events.filter((event) => !storedIds.has(identity(event)))]
    .filter((row) => observedOnly(row) && !['', 'app'].includes(sourceOf(identity(row))) && wordingOf(row) !== '');
  for (const sighting of sightings) {
    const id = identity(sighting);
    const clocked = /T\d{2}:\d{2}/.test(localTimeOf(sighting));
    const kept = storedIds.has(id);
    if (!kept && !clocked) continue;
    const matching = (clocked ? [...scans, ...rows] : scans).filter((scan) => stageOf(scan) === stageOf(sighting)
      && wordingOf(scan) === wordingOf(sighting) && codesAgree(scan, sighting)
      && (placeOf(scan) === placeOf(sighting) || !placeOf(scan) || !placeOf(sighting))
      && instantOf(scan) <= instantOf(sighting) && showsClock(localTimeOf(sighting), instantOf(scan)));
    if (new Set(matching.map(identity)).size !== 1) continue;
    const scan = identity(matching[0]!);
    // A sighting the batch writes again is left out, so its stored row can go.
    if (batchIds.has(id)) dated.skipped.add(id);
    if (!kept) continue;
    // A scan dated by its day alone keeps its own row: a later check finds a row by its time.
    if (!batchIds.has(id) && batchIds.has(scan) && !storedIds.has(scan) && !matches.reused.has(scan) && !matches.skipped.has(scan)
      && !dated.reused.has(scan) && timeOf(matching[0]!) !== '' && [sourceOf(scan), UNIVERSAL].includes(sourceOf(id))) {
      dated.reused.set(scan, id);
    }
    else dated.absorbed.add(id);
  }
  // A carrier's scan dated by its day, its wall clock in no zone (`clockDay`), is the scan a later
  // reply of that source gives with its time: the same stage, wording, provider code and place, and
  // an instant showing that wall clock. The scan takes the row over, one to one.
  const dayRows = stored.filter((row) => !held.has(identity(row)) && dates(row) && clockDay(row) !== null);
  const timedScans = scans.filter((scan) => !storedIds.has(identity(scan)) && !matches.reused.has(identity(scan))
    && !matches.skipped.has(identity(scan)) && clockDay(scan) === null && timeOf(scan) !== '');
  const pairs = dayRows.map((row) => [row, timedScans.filter((scan) => sourceOf(identity(scan)) === sourceOf(identity(row))
    && stageOf(scan) === stageOf(row) && wordingOf(scan) === wordingOf(row) && codesAgree(scan, row)
    && placeOf(scan) === placeOf(row) && showsClock(localTimeOf(row), instantOf(scan)))] as const);
  const claims = new Map<string, number>();
  for (const [, matching] of pairs) for (const scan of matching) claims.set(identity(scan), (claims.get(identity(scan)) ?? 0) + 1);
  for (const [row, matching] of pairs) {
    const scan = matching.length === 1 ? identity(matching[0]!) : '';
    if (scan && claims.get(scan) === 1 && !dated.reused.has(scan)) dated.reused.set(scan, identity(row));
  }
  // A stored sighting whose words its source's answer no longer lists, as when the scraper rewords a
  // status, goes once a scan of that answer holds the change it saw: the answer's newest scan dated
  // before it, from its universal provider if it has one, is of its stage and code.
  const taken = new Set(dated.reused.values());
  for (const sighting of stored) {
    const id = identity(sighting);
    if (!observedOnly(sighting) || ['', 'app'].includes(sourceOf(id)) || wordingOf(sighting) === '' || held.has(id)
      || dated.absorbed.has(id) || taken.has(id)) continue;
    const answer = events.filter((event) => sourceOf(identity(event)) === sourceOf(id) && providerOf(event) === providerOf(sighting));
    if (answer.some((event) => wordingOf(event) === wordingOf(sighting))) continue;
    const before = answer.filter((scan) => dates(scan) && instantOf(scan) <= instantOf(sighting));
    const newest = Math.max(...before.map(instantOf));
    const last = before.filter((scan) => instantOf(scan) === newest);
    if (last.length > 0 && last.every((scan) => stageOf(scan) === stageOf(sighting) && codesAgree(scan, sighting))) dated.absorbed.add(id);
  }
  return dated;
}

/**
 * Whether two scans of one minute are one, told by a carrier that keeps minutes only and one that keeps seconds: one
 * on the minute's first second and the other within it, from places whose names, without their codes, do not
 * conflict (a postcode or an office number names no other place). Two scans on the minute's first second, or two
 * with seconds, are no such pair.
 */
export function sameMinuteScan(at: number, words: readonly string[], other: number, otherWords: readonly string[]): boolean {
  const first = (time: number) => Math.floor(time / 1_000) % 60 === 0;
  const names = (place: readonly string[]) => place.filter((word) => !/\d/.test(word));
  return Math.floor(at / MINUTE_MS) === Math.floor(other / MINUTE_MS) && first(at) !== first(other)
    && !placesConflict(names(words), names(otherWords));
}

/**
 * The identities of the earlier carrier's scans in a handoff's batch that
 * relay a delivery scan: Nova Poshta tells each GOFO scan in its own words at
 * the same second and place. A relay falls in the same second as a delivery
 * scan, from places that share a word or name no more than a country; wording
 * may differ. The stages may differ too, as relayCopies.ts pairs the two (JS
 * Express calls La Poste's return notice an exception), except on a minute's
 * first second, which a carrier keeping minutes gives every scan: there the
 * stages agree or the places share a word. A relay also falls in the minute
 * of a delivery scan, in any stage, when one of the two keeps minutes only and
 * the other seconds (`sameMinuteScan`, Hrvatska pošta's minute of Royal Mail's
 * customs scan). Left out of `sharedScans`, a relay stays a row of the earlier
 * carrier, which relayCopies.ts pairs with the scan, and the delivery scan
 * takes over a universal copy alone: the copy on the minute's first second
 * that the relay would otherwise take at its own instant, or the copy of the
 * relay's second that a scan on the minute would then reach.
 */
export function relayedScans(earlier: readonly JsonObject[], delivery: readonly JsonObject[]): Set<string> {
  const scans = delivery.filter((row) => !observedOnly(row) && Number.isFinite(instantOf(row)));
  const second = (row: JsonObject) => Math.floor(instantOf(row) / 1_000);
  const relays = (row: JsonObject, scan: JsonObject) => {
    const [words, scanWords] = [placeWords(row), placeWords(scan)];
    if (sameMinuteScan(instantOf(row), words, instantOf(scan), scanWords)) return true;
    return second(scan) === second(row) && !placesConflict(scanWords, words) && (stagesAgree(stageOf(scan), stageOf(row))
      || second(row) % 60 !== 0 || words.some((word) => scanWords.includes(word)));
  };
  return new Set(earlier.filter((row) => !observedOnly(row) && Number.isFinite(instantOf(row))
    && scans.some((scan) => relays(row, scan))).map(identity));
}

/** Clocks a few minutes apart: a scan dated this little past the check that returned it is no misread zone. */
const SIGHTING_LEEWAY_MS = 5 * MINUTE_MS;

/**
 * Whether two places are named with no word in common, where a word may also
 * shorten another to its first letters (Swiss Post's "LON E ET BEA" office is
 * ParcelsApp's "LONDON BPI").
 */
function placesDiffer(left: readonly string[], right: readonly string[]): boolean {
  const shortens = (short: string, long: string) => short.length >= 3 && long.startsWith(short);
  return left.length > 0 && right.length > 0
    && !left.some((word) => right.some((other) => word === other || shortens(word, other) || shortens(other, word)));
}

/**
 * The universal rows that cannot make a carrier's own answer look older, or a
 * provider's newer, than it is, with the instant each counts at. A provider's
 * copy of a carrier's scan the sync could not tell from it (other wording, a
 * clock to the minute) counts at the scan's instant: one later in the scan's
 * minute (17TRACK adds seconds to USPS's minute), or a whole number of hours
 * up to 14 h later, the scan read in another zone (ParcelsApp reads a London
 * office's wall clock as UTC), with stages that agree and from no other place,
 * unless such a scan falls in the copy's own minute. The latest such scan
 * counts. A scan dated by its day alone has no clock: it neither dates a copy
 * nor counts as a misdated one. A provider's scan dated more than a few
 * minutes past the check that first returned it counts at none (NaN): no clock
 * but a misread zone does that. `scans` are a check's scans, `stored` the
 * parcel's rows, each first seen at its `created_at` (else at `checkedAt`, the
 * check's time). A row written by a carrier, or one naming no provider, is no
 * copy.
 */
export function misdatedCopies(scans: readonly JsonObject[], stored: readonly JsonObject[], checkedAt: number): Map<string, number> {
  const storedById = new Map(stored.map((row) => [identity(row), row] as const));
  const rows = [...new Map([...stored, ...scans].map((row) => [identity(row), row] as const)).values()]
    .filter((row) => !observedOnly(row) && Number.isFinite(instantOf(row)));
  const carriers = rows.filter((row) => heldByCarrier(row) && !dayAlone(row));
  const misdated = new Map<string, number>();
  for (const row of rows) {
    if (sourceOf(identity(row)) !== UNIVERSAL || providerOf(row) === '' || dayAlone(row)) continue;
    const at = instantOf(row);
    const sighted = Date.parse(String(storedById.get(identity(row))?.created_at ?? ''));
    if (at > (Number.isFinite(sighted) ? sighted : checkedAt) + SIGHTING_LEEWAY_MS) {
      misdated.set(identity(row), Number.NaN);
      continue;
    }
    const agrees = (scan: JsonObject) => stagesAgree(stageOf(row), stageOf(scan)) && !placesDiffer(placeWords(row), placeWords(scan));
    // A carrier's scan in the copy's own minute dates it: a scan whole hours away only shares its minute of the hour.
    const dated = carriers.some((scan) => Math.floor(instantOf(scan) / MINUTE_MS) === Math.floor(at / MINUTE_MS) && agrees(scan));
    const twins = carriers.filter((scan) => at > instantOf(scan) && agrees(scan)
      && (sameTruncated(at, instantOf(scan)) || (!dated && hoursApart(at, instantOf(scan)))));
    if (twins.length) misdated.set(identity(row), Math.max(...twins.map(instantOf)));
  }
  return misdated;
}

/**
 * A newest-event time (the routing watermark, a result's summary time) that
 * universal copies read in the wrong zone may have set hours late. A skipped
 * copy counts at its stored twin's instant, and a stored copy a scan takes over
 * (a carrier's, or its provider's on a corrected clock) at that scan's
 * instant. The time drops to the newest instant left
 * only when it is later than that and no later than the newest instant a copy
 * claimed, so the copies explain the excess (the router caps a future-dated
 * copy at the time of the check); a time they do not explain stands. The batch
 * always counts, `stored` rows only with `withStored`, and `also` adds instants
 * the batch rows do not carry. `explained` adds times copies are known to have
 * set: a universal provider's summary time is its newest scan's, which the
 * sync stored. `misdated` rows count at the instant it gives them
 * (`misdatedCopies`). The app's own rows ("Tracking added") are no scans: a parcel
 * added after its newest scan keeps that scan's time.
 */
export function withoutCopyDrift(
  time: number,
  events: readonly JsonObject[],
  stored: readonly JsonObject[],
  matches: { reused: ReadonlyMap<string, string>; skipped: ReadonlyMap<string, string> },
  options: { withStored?: boolean; also?: readonly number[]; explained?: readonly number[]; misdated?: ReadonlyMap<string, number> } = {},
): number {
  if (!Number.isFinite(time)) return time;
  const storedById = new Map(stored.map((row) => [identity(row), row] as const));
  const claimed: number[] = [...(options.explained ?? [])];
  const retimed = new Map<string, number>();
  const kept: number[] = [...(options.also ?? [])];
  const counted = (row: JsonObject, at: number) => {
    const misdated = options.misdated?.get(identity(row));
    if (misdated !== undefined) claimed.push(at);
    kept.push(misdated ?? at);
  };
  for (const event of events) {
    if (observedOnly(event)) continue;
    const takenOver = storedById.get(matches.reused.get(identity(event)) ?? '');
    if (takenOver) {
      claimed.push(instantOf(takenOver));
      retimed.set(identity(takenOver), instantOf(event));
    }
    const twin = storedById.get(matches.skipped.get(identity(event)) ?? '');
    if (twin) {
      claimed.push(instantOf(event));
      kept.push(instantOf(twin));
    } else counted(event, instantOf(event));
  }
  if (options.withStored) {
    // A row stored when the sync first saw it, a provider having given no instant, holds no scan time.
    for (const row of stored) {
      if (sourceOf(identity(row)) === 'app' || observedOnly(row)) continue;
      const moved = retimed.get(identity(row));
      if (moved !== undefined) kept.push(moved);
      else counted(row, instantOf(row));
    }
  }
  const newest = Math.max(...kept.filter(Number.isFinite));
  const claimedNewest = Math.max(...claimed.filter(Number.isFinite));
  return Number.isFinite(newest) && time > newest && time <= claimedNewest ? newest : time;
}

/**
 * The events as persisted: a reused identity replaces the computed one. A
 * universal scan that rewrites a stored row naming no provider (a carrier
 * wrote it, or it was stored before rows named one) leaves it unnamed. A scan
 * that takes over the row of an earlier time (`retimed`) keeps the time that
 * row was first stored with (`RETIMED_FROM`), and so does any scan that
 * rewrites such a row at another time.
 */
export function withIdentities(
  events: readonly JsonObject[],
  identities: ReadonlyMap<string, string>,
  stored: readonly JsonObject[] = [],
  retimed: ReadonlyMap<string, string> = new Map(),
): JsonObject[] {
  const unnamed = new Set(stored.filter((row) => providerOf(row) === '').map(identity));
  const rows = new Map(stored.map((row) => [identity(row), row]));
  return events.map((event) => {
    const reused = identities.get(identity(event));
    const persisted = reused ? { ...event, provider_event_id: reused } : event;
    const row = rows.get(identity(persisted));
    const kept = row ? rawOf(row, RETIMED_FROM) : undefined;
    const first = typeof kept === 'string' ? kept
      : row && reused && retimed.get(identity(event)) === reused ? timeOf(row) || String(row.occurred_at ?? '') : '';
    const moved = first !== '' && first !== timeOf(event);
    const unnaming = unnamed.has(identity(persisted)) && providerOf(event) !== '';
    if (!isRecord(persisted.raw_data) || (!moved && !unnaming)) return persisted;
    const raw = { ...persisted.raw_data };
    if (moved) raw[RETIMED_FROM] = first;
    if (unnaming) delete raw[UNIVERSAL_PROVIDER];
    return { ...persisted, raw_data: raw };
  });
}
