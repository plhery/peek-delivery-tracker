/**
 * A carrier's repeats of its own scan.
 *
 * Now and then a carrier sends one step several times over, each time with its
 * own seconds: J&T Express Australia answers one failed delivery ten times in
 * three minutes. Every row is stored. A scan right after another in the
 * parcel's timeline, from the same source and in the same stage, words and
 * place, within an hour of the first of them, tells the reader nothing new.
 * The apps show such a run as its newest scan, the others served with
 * `relay_of` its id (eventPlaces.ts), and only the run's first scan announces
 * (push.ts): a copy stored later announces nothing, even stamped before it. A scan La Poste lists twice at one
 * instant, in two wordings, is shown once (`wordingTwins`).
 */
import { isRecord, type JsonObject } from './types';

export type RepeatScan = { id: string; source: string; stage: string; description: string; location: string; at: number };

/** A run ends an hour after its first scan: the same step told later is news that the parcel is still there. */
const RUN_MS = 60 * 60 * 1_000;

/** The source of a stored row: its carrier, or the universal provider that told it. Empty for none. */
export function scanSource(providerEventId: unknown, universalProvider: unknown): string {
  const carrier = typeof providerEventId === 'string' ? providerEventId.split(':')[0]! : '';
  return carrier && typeof universalProvider === 'string' && universalProvider ? `${carrier}/${universalProvider}` : carrier;
}

function repeatScan(event: JsonObject): RepeatScan {
  return {
    id: String(event.id), source: scanSource(event.provider_event_id, event.universal_provider),
    stage: String(event.stage ?? ''), description: String(event.description ?? '').trim(),
    location: typeof event.location === 'string' ? event.location.trim() : '', at: Date.parse(String(event.occurred_at)),
  };
}

/**
 * Peek's own rows repeat nothing. At one instant, La Poste's two wordings of a scan are its words too (`wordingTwins`).
 */
const repeats = (first: RepeatScan, scan: RepeatScan) => first.source !== '' && first.source !== 'app'
  && scan.source === first.source && scan.stage === first.stage
  && (scan.description === first.description || (scan.at === first.at && productWords(scan.description) === productWords(first.description)))
  && scan.location === first.location && scan.at - first.at <= RUN_MS;

/** The runs of repeated scans, each in time order, two scans or more. Scans in `apart` join none. */
export function repeatRuns(scans: readonly RepeatScan[], apart: ReadonlySet<string> = new Set()): string[][] {
  const ordered = scans.filter((scan) => Number.isFinite(scan.at)).sort((a, b) => a.at - b.at || a.id.localeCompare(b.id));
  const runs: string[][] = [];
  let run: RepeatScan[] = [];
  for (const scan of ordered) {
    if (run.length && !apart.has(scan.id) && !apart.has(run[0]!.id) && repeats(run[0]!, scan)) {
      run.push(scan);
      continue;
    }
    if (run.length > 1) runs.push(run.map(({ id }) => id));
    run = [scan];
  }
  if (run.length > 1) runs.push(run.map(({ id }) => id));
  return runs;
}

/**
 * Each scan of a run but the newest, with the newest's id: the row the apps show the run as. Rows of
 * a relay pair (`apart`) are already shown under another or have one under them, and join no run.
 */
export function repeatLeads(events: readonly JsonObject[], apart: ReadonlySet<string> = new Set()): Map<string, string> {
  return new Map(repeatRuns(events.filter(isRecord).map(repeatScan), apart)
    .flatMap((run) => run.slice(0, -1).map((id): [string, string] => [id, run.at(-1)!])));
}

/**
 * The scans that repeat one the parcel had before them: each scan of a run but its first, and each of `fresh` (the
 * scans just stored) in a run that holds one stored before, whatever its time. A copy the carrier sends later
 * stamped earlier repeats the scan already told.
 */
export function repeatedScans(scans: readonly RepeatScan[], fresh?: ReadonlySet<string>): string[] {
  return repeatRuns(scans).flatMap((run) => fresh && run.some((id) => !fresh.has(id)) ? run.filter((id) => fresh.has(id)) : run.slice(1));
}

/** A row's words as a reader tells them apart. */
function readWords(description: unknown): string {
  return String(description ?? '').normalize('NFC').toLocaleLowerCase('fr').replace(/\s+/g, ' ').trim();
}

/** A row's words with La Poste's product name read as the parcel it names ("votre Colissimo" as "votre colis"). */
function productWords(description: unknown): string {
  return readWords(description).replace(/(?<![\p{L}\p{N}])colissimo(?![\p{L}\p{N}])/gu, 'colis');
}

/**
 * The rows the timeline leaves out as twins of another: La Poste can list one scan twice at one instant, once
 * "votre colis" and once "votre Colissimo", and a carrier that copies its scans copies both. Of the rows of one
 * source at one instant, in one stage and in words that differ only so, with one place or none, the timeline keeps
 * the one with a place, then the first stored, so the parcel's news keeps its time, then the one the source listed
 * last (`tie_order`). A row in the kept one's very words stays, a repeat (`repeatLeads`).
 */
export function wordingTwins(events: readonly JsonObject[]): Set<string> {
  const groups = new Map<string, JsonObject[]>();
  for (const event of events.filter(isRecord)) {
    const source = scanSource(event.provider_event_id, event.universal_provider);
    const at = Date.parse(String(event.occurred_at));
    const words = productWords(event.description);
    if (!source || source === 'app' || !Number.isFinite(at) || !words) continue;
    const key = JSON.stringify([source, at, String(event.stage ?? ''), words]);
    groups.set(key, [...groups.get(key) ?? [], event]);
  }
  const place = (event: JsonObject) => typeof event.location === 'string' ? event.location.trim() : '';
  const tie = (event: JsonObject) => typeof event.tie_order === 'number' ? event.tie_order : -1;
  const left = new Set<string>();
  for (const group of groups.values()) {
    if (group.length < 2 || new Set(group.map(place).filter(Boolean)).size > 1) continue;
    const kept = [...group].sort((a, b) => Number(place(b) !== '') - Number(place(a) !== '')
      || String(a.stored_at ?? '').localeCompare(String(b.stored_at ?? '')) || tie(b) - tie(a) || String(b.id).localeCompare(String(a.id)))[0]!;
    for (const event of group) if (readWords(event.description) !== readWords(kept.description)) left.add(String(event.id));
  }
  return left;
}
