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
 * (push.ts).
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

/** Peek's own rows repeat nothing. */
const repeats = (first: RepeatScan, scan: RepeatScan) => first.source !== '' && first.source !== 'app'
  && scan.source === first.source && scan.stage === first.stage && scan.description === first.description
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

/** The scans that repeat one the parcel had before them: each scan of a run but its first. */
export function repeatedScans(scans: readonly RepeatScan[]): string[] {
  return repeatRuns(scans).flatMap((run) => run.slice(1));
}
