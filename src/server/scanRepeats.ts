/**
 * A carrier's repeats of its own scan.
 *
 * Now and then a carrier sends one step several times over, each time with its
 * own seconds: J&T Express Australia answers one failed delivery ten times in
 * three minutes. Every row is stored. A scan right after another in the
 * parcel's timeline, from the same source (a provider's row the carrier's
 * answer took over counts as the carrier's) and in the same stage, words and
 * place, within an hour of the first of them, tells the reader nothing new.
 * Rows of one instant come between none of them: the carrier's other code for
 * a scan at its second (FAN Courier tells a delivery round under two codes)
 * splits no run, nor does a scan of another stage at a notice's instant.
 * The apps show such a run as its newest scan, the others served with
 * `relay_of` its id (eventPlaces.ts), and only the run's first scan announces
 * (push.ts): a copy stored later announces nothing, even stamped before it. A scan La Poste lists twice at one
 * instant, in two wordings, is shown once (`wordingTwins`).
 */
import { isRecord, type JsonObject } from './types';

export type RepeatScan = { id: string; source: string; stage: string; description: string; location: string; at: number; held?: boolean };

/** A run ends an hour after its first scan: the same step told later is news that the parcel is still there. */
const RUN_MS = 60 * 60 * 1_000;

/** The source of a stored row: its carrier, or the universal provider that told it. Empty for none. */
export function scanSource(providerEventId: unknown, universalProvider: unknown): string {
  const carrier = typeof providerEventId === 'string' ? providerEventId.split(':')[0]! : '';
  return carrier && typeof universalProvider === 'string' && universalProvider ? `${carrier}/${universalProvider}` : carrier;
}

function repeatScan(event: JsonObject, held: ReadonlySet<string>): RepeatScan {
  return {
    id: String(event.id), source: scanSource(event.provider_event_id, event.universal_provider),
    stage: String(event.stage ?? ''), description: String(event.description ?? '').trim(),
    location: typeof event.location === 'string' ? event.location.trim() : '', at: Date.parse(String(event.occurred_at)),
    ...(typeof event.provider_event_id === 'string' && held.has(event.provider_event_id) ? { held: true } : {}),
  };
}

/** A carrier's own row: neither Peek's nor a universal provider's. */
const carrierRow = (scan: RepeatScan) => !['', 'app'].includes(scan.source) && !scan.source.startsWith('unknown');

/**
 * Whether two rows come from one source. A universal row a carrier's answer holds (`held`, providerCopies.ts) is that
 * carrier's scan: it is the source of any carrier's row.
 */
const sameSource = (a: RepeatScan, b: RepeatScan) => a.source === b.source
  || (Boolean(a.held) !== Boolean(b.held) && carrierRow(a.held ? b : a));

/**
 * Peek's own rows repeat nothing. At one instant, La Poste's two wordings of a scan are its words too, in whichever
 * stage each was read (`wordingTwins`).
 */
const repeats = (first: RepeatScan, scan: RepeatScan) => first.source !== '' && first.source !== 'app'
  && sameSource(first, scan)
  && ((scan.stage === first.stage && scan.description === first.description)
    || (scan.at === first.at && productWords(scan.description) === productWords(first.description)))
  && scan.location === first.location && scan.at - first.at <= RUN_MS;

/**
 * The runs of repeated scans, each in time order, two scans or more. Each instant's rows go on the runs they repeat
 * or start their own; a run that none of an instant's rows goes on ends there. Scans in `apart` join none.
 */
export function repeatRuns(scans: readonly RepeatScan[], apart: ReadonlySet<string> = new Set()): string[][] {
  const ordered = scans.filter((scan) => Number.isFinite(scan.at)).sort((a, b) => a.at - b.at || a.id.localeCompare(b.id));
  const runs: string[][] = [];
  const close = (run: RepeatScan[]) => { if (run.length > 1) runs.push(run.map(({ id }) => id)); };
  let open: RepeatScan[][] = [];
  for (let start = 0; start < ordered.length;) {
    const at = ordered[start]!.at;
    let end = start;
    while (end < ordered.length && ordered[end]!.at === at) end += 1;
    const joined = new Set<RepeatScan[]>();
    const started: RepeatScan[][] = [];
    for (const scan of ordered.slice(start, end)) {
      const run = apart.has(scan.id) ? undefined
        : [...open, ...started].find((candidate) => !apart.has(candidate[0]!.id) && repeats(candidate[0]!, scan));
      if (run) {
        run.push(scan);
        joined.add(run);
      } else started.push([scan]);
    }
    for (const run of open) if (!joined.has(run)) close(run);
    open = [...open.filter((run) => joined.has(run)), ...started];
    start = end;
  }
  open.forEach(close);
  return runs;
}

/**
 * Each scan of a run but the newest, with the newest's id: the row the apps show the run as. Rows of
 * a relay pair (`apart`) are already shown under another or have one under them, and join no run. Rows whose identity
 * is in `held` are their carrier's scans (`heldRows`).
 */
export function repeatLeads(
  events: readonly JsonObject[], apart: ReadonlySet<string> = new Set(), held: ReadonlySet<string> = new Set(),
): Map<string, string> {
  return new Map(repeatRuns(events.filter(isRecord).map((event) => repeatScan(event, held)), apart)
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
 * "votre colis" and once "votre Colissimo", and a carrier that copies its scans copies both, at times reading the two
 * in different stages. Of the rows of one source at one instant, in words that differ only so, with one place or
 * none, the timeline keeps the one with a place, then the first stored, so the parcel's news keeps its time, then the
 * one the source listed last (`tie_order`). A row in the kept one's very words and stage stays, a repeat
 * (`repeatLeads`).
 */
export function wordingTwins(events: readonly JsonObject[]): Set<string> {
  const groups = new Map<string, JsonObject[]>();
  for (const event of events.filter(isRecord)) {
    const source = scanSource(event.provider_event_id, event.universal_provider);
    const at = Date.parse(String(event.occurred_at));
    const words = productWords(event.description);
    if (!source || source === 'app' || !Number.isFinite(at) || !words) continue;
    const key = JSON.stringify([source, at, words]);
    groups.set(key, [...groups.get(key) ?? [], event]);
  }
  const place = (event: JsonObject) => typeof event.location === 'string' ? event.location.trim() : '';
  const tie = (event: JsonObject) => typeof event.tie_order === 'number' ? event.tie_order : -1;
  const left = new Set<string>();
  for (const group of groups.values()) {
    if (group.length < 2 || new Set(group.map(place).filter(Boolean)).size > 1) continue;
    const kept = [...group].sort((a, b) => Number(place(b) !== '') - Number(place(a) !== '')
      || String(a.stored_at ?? '').localeCompare(String(b.stored_at ?? '')) || tie(b) - tie(a) || String(b.id).localeCompare(String(a.id)))[0]!;
    for (const event of group) {
      if (readWords(event.description) !== readWords(kept.description) || event.stage !== kept.stage) left.add(String(event.id));
    }
  }
  return left;
}
