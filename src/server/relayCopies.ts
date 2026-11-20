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
 * minutes gives every scan, the places must also share a word, or the two rows
 * tell the same words (La Poste's posting and Chronopost's, which alone names
 * the place), or one of them names no place and neither is a universal
 * provider's (AliExpress's hand-over to Spring GDS beside Spring GDS's
 * acceptance, DPD's notice of the sender's hand-over beside YunExpress's
 * scan). A carrier that
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
 * So does such a row, placed or not, up to 14 whole hours away, when it tells
 * the new carrier's words in its stage from a place that does not conflict
 * (WanB Express tells J&T Australia's scans two hours early, placed in the
 * state; DHLink reads GOFO's US scans on Beijing's clock).
 *
 * The earlier carrier also publishes some of its own scans late, after the
 * new carrier's later ones are stored (trackingSync.ts asks it again for a day
 * after the handoff). Those announce nothing either. *
 * A parcel that records no handoff may hold another carrier's rows too: the
 * carrier it was filed under before a correction, or one asked beside it, told
 * the same scans (BRT Chronopost's, SwiftX DHLink's). Such a row at the same
 * second as a row of the carrier tracking the parcel now, in the same stage or,
 * off a minute's first second, in any (JS Express tells Royal Mail's acceptance
 * as in transit), from places that do not conflict once their words are read
 * ("UTRECHT NL" and "Utrecht, UT, NL"), goes under that row. So does a third carrier's row in a
 * handed-over parcel that no relay pair holds (Australia Post's rows of a
 * Parcelforce parcel EMS carries on), even under a scan that has the earlier
 * carrier's copy under it already.
 */
import { CARRIER_DEFINITIONS, type CarrierId } from 'universal-parcel-scraper';
import { trackingPlace } from 'universal-parcel-scraper/app';
import { placesApart, placesConflict, placeWithin, placeWords, sameMinuteScan } from './eventIdentity';
import { isRecord, type JsonObject } from './types';

/** A copy is stamped within this of the scan it repeats, either way. */
const RELAY_WINDOW_MS = 60_000;
/** A step the new carrier learnt from the earlier one's message is stamped up to this long after the earlier one's scan. */
const EXCHANGE_WINDOW_MS = 3 * 60 * 60_000;
/** The earlier carrier tells the new one's scan on another clock up to this many whole hours away. */
const TWIN_HOURS = 3;
/** In the new carrier's words and stage, as far as any zone can put a clock read a zone away (UTC−12 to UTC+14). */
const WORDED_TWIN_HOURS = 14;
const SECOND_MS = 1000;
const MINUTE_MS = 60_000;
const HOUR_MS = 60 * MINUTE_MS;

/** The carrier tracking a parcel now: the one it was handed over to, or its own. */
export function currentCarrier(row: JsonObject): string {
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

/**
 * How far a copy lies from a scan. A time on a minute's first second beside one with seconds is the minute a carrier
 * keeping minutes cut it to, and stands for that whole minute: La Poste's 12:05 tells DHL's 12:05:38, not its 12:04:38.
 */
function relayGap(at: number, other: number): number {
  const onMinute = (time: number) => time % MINUTE_MS === 0;
  if (onMinute(at) === onMinute(other)) return Math.abs(at - other);
  const [minute, exact] = onMinute(at) ? [at, other] : [other, at];
  return exact < minute ? minute - exact : Math.max(0, exact - (minute + MINUTE_MS - 1));
}

/** Whether two times lie one to `hours` whole hours apart, to the second. */
function twinHours(at: number, other: number, hours = TWIN_HOURS): boolean {
  const gap = Math.abs(Math.floor(at / SECOND_MS) - Math.floor(other / SECOND_MS)) * SECOND_MS;
  return gap >= HOUR_MS && gap <= hours * HOUR_MS && gap % HOUR_MS === 0;
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

/** A universal provider's rows: their copies of a carrier's scans are providerCopies.ts's. */
const UNIVERSAL_SOURCE = 'unknown';

/**
 * Steps that tell a problem, and the steps beside which such a step is the news: the routine ones, customs and the
 * delivery round (An Post's failed delivery beside Royal Mail's customs scan).
 */
const PROBLEM_STAGES = new Set(['exception', 'failed_attempt']);
const ROUTINE_STAGES = new Set(['pending', 'registered', 'accepted', 'in_transit', 'customs', 'out_for_delivery']);

/** Whether the earlier carrier's copy tells a problem that its pair's scan, a routine step, customs or the round, does not. */
export function copyTellsProblem(copy: JsonObject, scan: JsonObject): boolean {
  return PROBLEM_STAGES.has(String(copy.stage)) && ROUTINE_STAGES.has(String(scan.stage));
}

/** A scan's words, case, spacing and punctuation aside. */
function told(text: string): string {
  return text.toLowerCase().replace(/[^\p{L}\p{N}]+/gu, ' ').trim();
}

/** What the pairing reads of a row. */
type Scan = {
  id: string; stage: unknown; source: string; at: number; text: string; country: string | null; words: string[]; placed: boolean;
  place: string; land: string | null;
};

/**
 * A handed-over parcel's universal rows that tell a carrier's row over again, each with that row's id: the same
 * instant, stage, words and place (China Post's acceptance, and the universal row China Post's earlier answer took
 * over). The row they go under is the earlier carrier's, else the first carrier's by id, and it pairs for them all:
 * one scan stored twice must not use up the pair of the new carrier's row of it.
 */
function sameRows(scans: readonly Scan[], earlier: string): Map<string, string> {
  const groups = new Map<string, Scan[]>();
  for (const scan of scans) {
    if (told(scan.text) === '') continue;
    const key = JSON.stringify([scan.at, scan.stage, told(scan.text), scan.place]);
    groups.set(key, [...groups.get(key) ?? [], scan]);
  }
  const rank = (scan: Scan) => scan.source === earlier ? 0 : scan.source !== UNIVERSAL_SOURCE ? 1 : 2;
  const same = new Map<string, string>();
  for (const rows of groups.values()) {
    const [lead, ...others] = [...rows].sort((a, b) => rank(a) - rank(b) || a.id.localeCompare(b.id));
    if (rank(lead!) === 2) continue;
    for (const other of others) if (other.source === UNIVERSAL_SOURCE) same.set(other.id, lead!.id);
  }
  return same;
}

/**
 * Whether two scans at one second are one: places that do not conflict and, when the second is a minute's first,
 * sharing a word, telling the same words, or one naming no place when neither is a universal provider's.
 */
function sameSecond(copy: Scan, scan: Scan): boolean {
  const second = (at: number) => Math.floor(at / SECOND_MS);
  if (second(copy.at) !== second(scan.at) || placesConflict(copy.words, scan.words)) return false;
  return second(copy.at) % 60 !== 0 || copy.words.some((word) => scan.words.includes(word))
    || (told(copy.text) !== '' && told(copy.text) === told(scan.text))
    || ((!copy.placed || !scan.placed) && ![copy.source, scan.source].includes(UNIVERSAL_SOURCE));
}

/**
 * The relay pairs of a handed-over parcel, each copy with the id of the scan it repeats. The earlier carrier's scans
 * repeat a scan of another source at the same stage within a minute, or in any stage at the same second, the closest
 * pairs first (`relayGap`: a row on a minute's first second is as close as can be to a scan within that minute) and, of
 * pairs as close, the nearer to the second, then the new carrier's scan before a third carrier's (that row then goes under
 * the new carrier's scan of its second), then those telling the same words, then the same stage. Then, in any stage, a row on
 * a minute's first second repeats one with seconds within that minute (`sameMinuteScan`, which the sync's
 * `relayedScans` shares). Then a row of the new carrier placed only in a country
 * the earlier carrier delivers in, and the new carrier does not, repeats the earlier carrier's latest scan of its
 * stage, when it follows every such scan by up to three hours but not by whole hours. Last, a row of the earlier
 * carrier dated to the second repeats the new carrier's row one to three whole hours away, to the second: in any stage
 * when it names no place, else in the row's stage, with its words, from a place that does not conflict.
 * A scan is in one pair at most, so two scans of one minute that the earlier carrier tells once stay two rows. A
 * universal row that tells a carrier's row again (`sameRows`) goes under it first, and that row pairs for both. The
 * earlier carrier may tell the new carrier's one scan of a minute as several rows at one instant (DHL tells La Poste's
 * platform scan as its arrival in the country, at the parcel centre and its departure from the import parcel centre):
 * when its copy is on a minute's first second or the scan's own second, the new carrier has no other row in that minute,
 * no two of the rows name places that conflict or two countries, the instant's other rows in transit or in the scan's
 * stage repeat that scan too. After these, a third carrier's row no pair holds repeats the new carrier's at its second
 * and stage (`otherCarrierCopies`).
 * A scan may so have several rows under it, and a row under it rows of its own: eventPlaces.ts shows them as one step.
 */
export function relayCopies(row: JsonObject): Map<string, string> {
  const earlier = earlierCarrier(row);
  const pairs = new Map<string, string>();
  if (!Array.isArray(row.tracking_events)) return pairs;
  const rows = row.tracking_events.filter(isRecord)
    .map((event): Scan => ({
      id: String(event.id), stage: event.stage, source: source(event), at: Date.parse(String(event.occurred_at)),
      text: String(event.description ?? '').trim(), country: countryOnly(event.location), words: placeWords(event),
      placed: typeof event.location === 'string' && event.location.trim() !== '',
      place: typeof event.location === 'string' ? event.location.trim().toLowerCase() : '',
      land: typeof event.location === 'string' && event.location.trim() ? trackingPlace(event.location).country : null,
    }))
    .filter((scan) => Number.isFinite(scan.at) && scan.source !== '' && scan.source !== 'app');
  if (!earlier) return otherCarrierCopies(rows, currentCarrier(row));
  const same = sameRows(rows, earlier);
  const scans = rows.filter((scan) => !same.has(scan.id));
  const candidates = scans.filter((copy) => copy.source === earlier).flatMap((copy) => scans
    .filter((scan) => scan.source !== earlier
      && ((scan.stage === copy.stage && Math.abs(scan.at - copy.at) <= RELAY_WINDOW_MS) || sameSecond(copy, scan)))
    .map((scan) => ({ copy, scan, gap: relayGap(copy.at, scan.at) })));
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
  const twins = scans.filter((copy) => copy.source === earlier && copy.at % MINUTE_MS !== 0)
    .flatMap((copy) => scans.filter((scan) => scan.source === current
      && ((!copy.placed && twinHours(copy.at, scan.at)) || (twinHours(copy.at, scan.at, WORDED_TWIN_HOURS)
        && copy.stage === scan.stage && told(copy.text) !== '' && told(copy.text) === told(scan.text)
        && !placesConflict(copy.words, scan.words))))
      .map((scan) => ({ copy, scan, gap: Math.abs(scan.at - copy.at) })));
  const paired = new Set<string>();
  // Of pairs as close, the new carrier's scan, then those telling the same words, then the same stage, so that each copy
  // goes under its own scan: a third carrier's row of that scan pairs with it after (otherCarrierCopies).
  const unlike = ({ copy, scan }: (typeof candidates)[number]) => (copy.text === scan.text ? 0 : 2) + (copy.stage === scan.stage ? 0 : 1);
  const other = ({ scan }: (typeof candidates)[number]) => scan.source === current ? 0 : 1;
  for (const list of [candidates, minutes, exchanged, twins]) {
    const apart = ({ copy, scan }: (typeof candidates)[number]) => Math.abs(scan.at - copy.at);
    list.sort((a, b) => a.gap - b.gap || apart(a) - apart(b) || other(a) - other(b) || unlike(a) - unlike(b) || a.copy.at - b.copy.at
      || a.copy.id.localeCompare(b.copy.id) || a.scan.id.localeCompare(b.scan.id));
    for (const { copy, scan } of list) {
      if (paired.has(copy.id) || paired.has(scan.id)) continue;
      pairs.set(copy.id, scan.id);
      paired.add(copy.id);
      paired.add(scan.id);
    }
  }
  // The earlier carrier's other rows at the instant of its copy of the new carrier's one scan of that minute.
  const byId = new Map(scans.map((scan) => [scan.id, scan]));
  const minute = (at: number) => Math.floor(at / MINUTE_MS);
  for (const [copyId, scanId] of [...pairs]) {
    const [copy, scan] = [byId.get(copyId)!, byId.get(scanId)!];
    if (copy.source !== earlier || scan.source !== current
      || (copy.at % MINUTE_MS !== 0 && Math.floor(copy.at / SECOND_MS) !== Math.floor(scan.at / SECOND_MS))
      || scans.some((other) => other.source === current && other !== scan && minute(other.at) === minute(scan.at))) continue;
    const others = scans.filter((other) => other.source === earlier && other !== copy && other.at === copy.at && !paired.has(other.id)
      && (other.stage === 'in_transit' || other.stage === scan.stage));
    const group = [scan, copy, ...others];
    if (group.some((one) => group.some((other) => placesConflict(one.words, other.words)))
      || new Set(group.map((one) => one.land).filter(Boolean)).size > 1) continue;
    for (const other of others) {
      pairs.set(other.id, scan.id);
      paired.add(other.id);
    }
  }
  // A third carrier's rows that no pair holds tell the current carrier's scans as a carrier asked beside it does.
  const third = scans.filter((scan) => scan.source === current || (scan.source !== earlier && !paired.has(scan.id)));
  for (const [copy, scan] of otherCarrierCopies(third, current)) pairs.set(copy, scan);
  for (const [row, lead] of same) pairs.set(row, lead);
  return pairs;
}

/**
 * On a parcel that records no handoff, the rows of another carrier that tell a scan of the carrier tracking the parcel
 * now, each with that scan's id: the same second, places that do not conflict, and the same stage, or, off a minute's
 * first second, any stage once the same stage's pairs are made. A scan is in one pair at most; a universal provider's
 * row is in none.
 */
function otherCarrierCopies(
  scans: readonly Pick<Scan, 'id' | 'stage' | 'source' | 'at' | 'words'>[], current: string,
): Map<string, string> {
  const second = (at: number) => Math.floor(at / SECOND_MS);
  const own = scans.filter((scan) => scan.source === current);
  const pairs = new Map<string, string>();
  const paired = new Set<string>();
  const copies = scans.filter((scan) => scan.source !== current && scan.source !== UNIVERSAL_SOURCE)
    .sort((a, b) => a.at - b.at || a.id.localeCompare(b.id));
  for (const sameStage of [true, false]) {
    for (const copy of copies) {
      if (pairs.has(copy.id)) continue;
      const scan = own.filter((other) => !paired.has(other.id) && second(other.at) === second(copy.at)
        && (sameStage ? other.stage === copy.stage : second(copy.at) % 60 !== 0) && !placesConflict(copy.words, other.words))
        .sort((a, b) => a.id.localeCompare(b.id))[0];
      if (!scan) continue;
      pairs.set(copy.id, scan.id);
      paired.add(scan.id);
    }
  }
  return pairs;
}

/**
 * Two or more universal providers' rows of one scan, which no rule leaves out without a carrier's scan beside them:
 * the same instant and stage, or the same minute and stage when only one instant of it has seconds and no provider has
 * two rows in it (ParcelsApp gives to the minute the China Post scan 17TRACK gives to the second), each its provider's
 * only row there, from places that are not apart or of which one names only the country the other's town is in
 * (ParcelsApp names the country of a posting Ship24 places in its town). Each row with the id of the row it is shown
 * under: of those, the one whose place says most, a town before a country before none, then the first stored. Rows in
 * `paired` are in no such group.
 */
export function providerTwins(events: readonly JsonObject[], paired: ReadonlySet<string> = new Set()): Map<string, string> {
  const provider = (event: JsonObject) => source(event) === UNIVERSAL_SOURCE && typeof event.universal_provider === 'string'
    ? event.universal_provider : '';
  const minutes = new Map<string, JsonObject[]>();
  for (const event of events) {
    const at = Date.parse(String(event.occurred_at));
    if (!provider(event) || paired.has(String(event.id)) || !Number.isFinite(at)) continue;
    const key = `${Math.floor(at / MINUTE_MS)} ${String(event.stage)}`;
    minutes.set(key, [...minutes.get(key) ?? [], event]);
  }
  // A minute with two instants to the second, or a provider's two rows, holds two scans: each instant is a group of its own.
  const instant = (event: JsonObject) => Date.parse(String(event.occurred_at));
  const groups = [...minutes.values()].flatMap((rows) => new Set(rows.map(instant).filter((at) => at % MINUTE_MS !== 0)).size < 2
    && new Set(rows.map(provider)).size === rows.length ? [rows]
    : [...new Set(rows.map(instant))].map((at) => rows.filter((event) => instant(event) === at)));
  // A place the gazetteer finds only as a country lies within itself, and says less than any other place.
  const says = (event: JsonObject) => typeof event.location !== 'string' || !event.location.trim() ? 0
    : placeWithin(event, event) ? 1 : 2;
  const twins = new Map<string, string>();
  for (const rows of groups) {
    const ones = rows.filter((event) => rows.filter((other) => provider(other) === provider(event)).length === 1);
    if (ones.length < 2) continue;
    const [lead, ...others] = ones.sort((a, b) => says(b) - says(a)
      || String(a.created_at ?? a.stored_at).localeCompare(String(b.created_at ?? b.stored_at)) || String(a.id).localeCompare(String(b.id)));
    if (others.some((other) => placesApart(other, lead!) && !placeWithin(other, lead!) && !placeWithin(lead!, other))) continue;
    for (const other of others) twins.set(String(other.id), String(lead!.id));
  }
  return twins;
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
