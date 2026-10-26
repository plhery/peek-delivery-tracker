import type { TrackingEvent } from '../types';

type Scan = Pick<TrackingEvent, 'stage' | 'location' | 'occurredAt'>;

const SHORTEST_S = 3_600;
const LONGEST_S = 172_800;
/** Gaps this close count as one interval. */
const TOLERANCE_S = 2;
const REPEATS = 3;

/**
 * The scans of a generated track. The parcel's newest scans each moved it on in transit to a new place, each instant
 * at one place, one hour to two days after the scan before and not in whole minutes, and three of those gaps are one
 * interval to within 2 s. The track runs from the oldest move at a repeated interval to the newest scan. No network
 * moves a parcel on at one interval to the second; a carrier that rounds its clock gives whole minutes. None unless
 * the newest scan is such a move. The hourly review counts the same parcels (`looping_track` in ops/review/signals.sql).
 */
export function generatedScans<T extends Scan>(events: readonly T[]): T[] {
  // Peek's own rows and notices are not the carrier moving the parcel.
  const instants = new Map<number, { scans: T[]; places: Set<string>; transit: boolean }>();
  for (const event of events) {
    const at = Date.parse(event.occurredAt);
    if (event.stage === 'pending' || !Number.isFinite(at)) continue;
    const instant = instants.get(at) ?? { scans: [], places: new Set<string>(), transit: true };
    instant.scans.push(event);
    const place = event.location?.trim().toLowerCase();
    if (place) instant.places.add(place);
    instant.transit &&= event.stage === 'in_transit';
    instants.set(at, instant);
  }
  const newestFirst = [...instants].sort(([a], [b]) => b - a);
  const moves: { gap: number; scans: T[] }[] = [];
  for (let index = 1; index < newestFirst.length; index += 1) {
    const [at, scan] = newestFirst[index - 1]!;
    const [before, previous] = newestFirst[index]!;
    const gap = Math.round((at - before) / 1_000);
    const [place] = scan.places;
    const moved = scan.transit && previous.transit && scan.places.size === 1 && previous.places.size === 1 && !previous.places.has(place!);
    if (!moved || gap < SHORTEST_S || gap > LONGEST_S || gap % 60 === 0) break;
    moves.push({ gap, scans: scan.scans });
  }
  const repeated = (gap: number) => moves.filter((other) => Math.abs(other.gap - gap) <= TOLERANCE_S).length >= REPEATS;
  // Moves before the first repeated one may be the carrier's own.
  let first = moves.length - 1;
  while (first >= 0 && !repeated(moves[first]!.gap)) first -= 1;
  return moves.slice(0, first + 1).flatMap(({ scans }) => scans);
}

/** The scans with a generated track's left out: where the parcel really was last. */
export function withoutGeneratedScans<T extends Scan>(events: readonly T[]): readonly T[] {
  const generated = new Set(generatedScans(events));
  return generated.size ? events.filter((event) => !generated.has(event)) : events;
}
