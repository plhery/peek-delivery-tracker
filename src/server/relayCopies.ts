/**
 * Relay copies in a handed-over parcel's history.
 *
 * A carrier that hands a parcel over often goes on telling the new carrier's
 * scans in its own words, stamped within a minute of them and often without
 * the seconds: India Post tells La Poste's, DHL and Spring GDS tell Swiss
 * Post's, Chronopost tells DPD's. Both rows are stored and kept, because the
 * same rule would pair two distinct scans now and then. The parcel's timeline
 * serves the copy with `relay_of`, the id of the scan it repeats, or the scan
 * with the copy's when the copy alone says where it was, and the apps show the
 * row with `relay_of` under the other (eventPlaces.ts). Of the two rows, the one
 * that reached the parcel second announces nothing (push.ts). A copy that alone
 * tells a problem beside the scan's routine step (Chronopost's parcel held as
 * damaged beside DPD's "on its way") heads its pair, and announces.
 *
 * Both carriers may also tell a hand-over, each in its own stage, at the same
 * second (4PX's "Parcel handed to dpd" and DPD's acceptance, Chronopost's
 * departure sort and DPD's): a second the two share, from places that do not
 * conflict, is one scan. On a minute's first second, which a carrier keeping
 * minutes gives every scan, the places must also share a word. A carrier that
 * keeps minutes tells the other's scan on that minute's first second: such a
 * row and the other carrier's row within that minute, with seconds, are one
 * scan in any stage too (Poczta Polska's posting and DHL's or bpost's first
 * row, PostNord's failed delivery and DHL's), when the places' names do not
 * conflict. That pairing comes after the others, so a minute's two scans each
 * carrier tells in its stage still pair stage by stage.
 *
 * The new carrier, in turn, may tell the steps the earlier one sent it over
 * the posts' data exchange: the posting and the departure from its country,
 * placed in that country alone and stamped when the message arrived, an hour
 * or two after the earlier carrier's own scan (Slovenská pošta tells Poczta
 * Polska's). Such a row goes under the earlier carrier's latest scan of its
 * stage, the one it follows. A gap of whole hours, to the second when both
 * keep seconds, is one record read on two clocks, which is the scraper's to
 * fix, and is no such pair.
 *
 * One exception: the earlier carrier may tell the new carrier's scan on another
 * clock, with no place, which the scraper cannot set right (CTT Express tells
 * CTT's scans an hour early). Its row that names no place, dated to the second
 * (off a minute's first second, or with milliseconds), one to three whole hours
 * to the second from the new carrier's row, in any stage, goes under that row.
 *
 * The earlier carrier also publishes some of its own scans late, after the
 * new carrier's later ones are stored (trackingSync.ts asks it again for a day
 * after the handoff). Those announce nothing either.
 */
import { CARRIER_DEFINITIONS, type CarrierId } from 'universal-parcel-scraper';
import { trackingPlace } from 'universal-parcel-scraper/app';
import { placesConflict, placeWords, sameMinuteScan } from './eventIdentity';
import { isRecord, type JsonObject } from './types';

/** A copy is stamped within this of the scan it repeats, either way. */
const RELAY_WINDOW_MS = 60_000;
/** A step the new carrier learnt from the earlier one's message is stamped up to this long after the earlier one's scan. */
const EXCHANGE_WINDOW_MS = 3 * 60 * 60_000;
/** The earlier carrier tells the new one's scan on another clock up to this many whole hours away. */
const TWIN_HOURS = 3;
const SECOND_MS = 1000;
const MINUTE_MS = 60_000;
const HOUR_MS = 60 * MINUTE_MS;

/** The carrier tracking a parcel now: the one it was handed over to, or its own. */
function currentCarrier(row: JsonObject): string {
  const data = isRecord(row.carrier_data) ? row.carrier_data : {};
  return typeof data.active_tracking_carrier === 'string' ? data.active_tracking_carrier : String(row.carrier ?? '');
}

/** The carrier a parcel was handed over from, while another one tracks it; null for a parcel of one carrier. */
export function earlierCarrier(row: JsonObject): string | null {
  const data = isRecord(row.carrier_data) ? row.carrier_data : {};
  const earlier = typeof data.original_carrier === 'string' ? data.original_carrier : '';
  return earlier && earlier !== currentCarrier(row) ? earlier : null;
}

/**
 * Whether two times lie whole hours apart: to the second when both keep seconds, otherwise to the minute. One record
 * read on two clocks shows such a gap.
 */
function wholeHours(at: number, other: number): boolean {
  const seconds = (time: number) => Math.floor(time / SECOND_MS) % 60 !== 0;
  const unit = seconds(at) && seconds(other) ? SECOND_MS : MINUTE_MS;
  return (Math.floor(at / unit) - Math.floor(other / unit)) * unit % HOUR_MS === 0;
}

/** Whether two times lie one to `TWIN_HOURS` whole hours apart, to the second. */
function twinHours(at: number, other: number): boolean {
  const gap = Math.abs(Math.floor(at / SECOND_MS) - Math.floor(other / SECOND_MS)) * SECOND_MS;
  return gap >= HOUR_MS && gap <= TWIN_HOURS * HOUR_MS && gap % HOUR_MS === 0;
}

/** The countries a carrier delivers in under its own name. */
function homes(carrier: string): readonly string[] {
  return CARRIER_DEFINITIONS[carrier as CarrierId]?.countries ?? [];
}

/** The country a scan's place names, when it names nothing else. */
function countryOnly(location: unknown): string | null {
  if (typeof location !== 'string' || !location.trim()) return null;
  const { country, place } = trackingPlace(location);
  return country && !place ? country : null;
}

/** Who stored a scan: the carrier its identity starts with (`unknown` for a universal provider, `app` for Peek's own rows). */
function source(event: JsonObject): string {
  return typeof event.provider_event_id === 'string' ? event.provider_event_id.split(':')[0]! : '';
}

/** Steps that tell a problem, and the routine steps beside which such a step is the news. */
const PROBLEM_STAGES = new Set(['exception', 'failed_attempt']);
const ROUTINE_STAGES = new Set(['pending', 'registered', 'accepted', 'in_transit']);

/** Whether the earlier carrier's copy tells a problem that its pair's scan, a routine step, does not. */
export function copyTellsProblem(copy: JsonObject, scan: JsonObject): boolean {
  return PROBLEM_STAGES.has(String(copy.stage)) && ROUTINE_STAGES.has(String(scan.stage));
}

/** Whether two scans at one second are one: places that do not conflict, sharing a word when the second is a minute's first. */
function sameSecond(copy: { at: number; words: string[] }, scan: { at: number; words: string[] }): boolean {
  const second = (at: number) => Math.floor(at / SECOND_MS);
  if (second(copy.at) !== second(scan.at) || placesConflict(copy.words, scan.words)) return false;
  return second(copy.at) % 60 !== 0 || copy.words.some((word) => scan.words.includes(word));
}

/**
 * The relay pairs of a handed-over parcel, each copy with the id of the scan it repeats. The earlier carrier's scans
 * repeat a scan of another source at the same stage within a minute, or in any stage at the same second, the closest
 * pairs first. Then, in any stage, a row on a minute's first second repeats one with seconds within that minute
 * (`sameMinuteScan`, which the sync's `relayedScans` shares). Then a row of the new carrier placed only in a country
 * the earlier carrier delivers in, and the new carrier does not, repeats the earlier carrier's latest scan of its
 * stage, when it follows every such scan by up to three hours but not by whole hours. Last, a row of the earlier
 * carrier that names no place and is dated to the second repeats the new carrier's row one to three whole hours away,
 * to the second, in any stage.
 * A scan is in one pair at most, so two scans of one minute that the earlier carrier tells once stay two rows.
 */
export function relayCopies(row: JsonObject): Map<string, string> {
  const earlier = earlierCarrier(row);
  const pairs = new Map<string, string>();
  if (!earlier || !Array.isArray(row.tracking_events)) return pairs;
  const scans = row.tracking_events.filter(isRecord)
    .map((event) => ({
      id: String(event.id), stage: event.stage, source: source(event), at: Date.parse(String(event.occurred_at)),
      country: countryOnly(event.location), words: placeWords(event),
      placed: typeof event.location === 'string' && event.location.trim() !== '',
    }))
    .filter((scan) => Number.isFinite(scan.at) && scan.source !== '' && scan.source !== 'app');
  const candidates = scans.filter((copy) => copy.source === earlier).flatMap((copy) => scans
    .filter((scan) => scan.source !== earlier
      && ((scan.stage === copy.stage && Math.abs(scan.at - copy.at) <= RELAY_WINDOW_MS) || sameSecond(copy, scan)))
    .map((scan) => ({ copy, scan, gap: Math.abs(scan.at - copy.at) })));
  const minutes = scans.filter((copy) => copy.source === earlier).flatMap((copy) => scans
    .filter((scan) => scan.source !== earlier && sameMinuteScan(copy.at, copy.words, scan.at, scan.words))
    .map((scan) => ({ copy, scan, gap: Math.abs(scan.at - copy.at) })));
  const current = currentCarrier(row);
  const exchanged = scans.filter((copy) => copy.source === current && copy.country !== null
    && homes(earlier).includes(copy.country) && !homes(current).includes(copy.country)).flatMap((copy) => {
    const before = scans.filter((scan) => scan.source === earlier && scan.stage === copy.stage);
    const scan = before.reduce<(typeof before)[number] | null>((latest, other) => !latest || other.at > latest.at ? other : latest, null);
    return scan && copy.at > scan.at && copy.at - scan.at <= EXCHANGE_WINDOW_MS && !wholeHours(copy.at, scan.at)
      ? [{ copy, scan, gap: copy.at - scan.at }] : [];
  });
  const twins = scans.filter((copy) => copy.source === earlier && !copy.placed && copy.at % MINUTE_MS !== 0)
    .flatMap((copy) => scans.filter((scan) => scan.source === current && twinHours(copy.at, scan.at))
      .map((scan) => ({ copy, scan, gap: Math.abs(scan.at - copy.at) })));
  const paired = new Set<string>();
  for (const list of [candidates, minutes, exchanged, twins]) {
    list.sort((a, b) => a.gap - b.gap || a.copy.at - b.copy.at
      || a.copy.id.localeCompare(b.copy.id) || a.scan.id.localeCompare(b.scan.id));
    for (const { copy, scan } of list) {
      if (paired.has(copy.id) || paired.has(scan.id)) continue;
      pairs.set(copy.id, scan.id);
      paired.add(copy.id);
      paired.add(scan.id);
    }
  }
  return pairs;
}

/**
 * The rows of relay pairs that reached the parcel after the other one: their
 * news was the parcel's already. Of a pair stored by the same check, the copy.
 * A copy that alone tells a problem is news whenever it came: its scan is the
 * repeat. The scans need their `stage` and `created_at`.
 */
export function relayRepeats(row: JsonObject): Set<string> {
  const events = new Map((Array.isArray(row.tracking_events) ? row.tracking_events.filter(isRecord) : [])
    .map((event) => [String(event.id), event]));
  const stored = (id: string) => Date.parse(String(events.get(id)!.created_at));
  const repeats = new Set<string>();
  for (const [copy, scan] of relayCopies(row)) {
    repeats.add(copyTellsProblem(events.get(copy)!, events.get(scan)!) || stored(copy) < stored(scan) ? scan : copy);
  }
  return repeats;
}

/**
 * The earlier carrier's scans that reached the parcel after a later scan was
 * stored: older than news the parcel already had. Scans stored by one check
 * never make each other late. The scans need their `created_at`.
 */
export function lateScans(row: JsonObject): Set<string> {
  const earlier = earlierCarrier(row);
  if (!earlier || !Array.isArray(row.tracking_events)) return new Set();
  const scans = row.tracking_events.filter(isRecord)
    .map((event) => ({
      id: String(event.id), source: source(event),
      at: Date.parse(String(event.occurred_at)), stored: Date.parse(String(event.created_at)),
    }))
    .filter((scan) => Number.isFinite(scan.at) && Number.isFinite(scan.stored) && scan.source !== 'app');
  return new Set(scans.filter((scan) => scan.source === earlier
    && scans.some((other) => other.stored < scan.stored && other.at > scan.at)).map((scan) => scan.id));
}
