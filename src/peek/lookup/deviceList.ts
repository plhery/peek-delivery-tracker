import { readMaintenance, type MaintenanceNotice } from '../../lib/maintenance';
import { isActiveParcel, nextPriorityParcel } from '../../lib/parcelPriority';
import { isFinal } from '../../lib/stages';
import { turnOffAlert } from '../alerts';
import { forgetParcelLink, ParcelLinkError, readParcelLink } from '../links';
import { journeyEndedBefore } from '../parcel/summary';
import { forgetRecent, freezeRecent, rememberParcel, strandedParcels, type RecentParcel } from '../recents';

/**
 * How long an answer is fresh enough for the list: a parcel on its way moves, one that arrived rests. A held
 * return is on its way: the server still follows it.
 */
const FRESH_MS = 5 * 60_000;
const SETTLED_FRESH_MS = 12 * 60 * 60_000;
/** A lookup the carrier has not answered yet is asked about again soon. */
const WAITING_FRESH_MS = 1_500;
/** A list asks again about a parcel whose carrier has not answered yet after 2 s, then ever more slowly up to 10 s. */
const FIRST_CHECK_MS = 2_000;
const FIRST_CHECK_MAX_MS = 10_000;
/** Otherwise a list in sight looks for stale parcels every minute. */
const PASS_MS = 60_000;
/** When the list last asked about each parcel, so coming back to the door does not ask again. */
const checkedAt = new Map<string, number>();

/**
 * The server down for maintenance, as the last round found it, until a round reads a parcel again; and when a round
 * last read one. The list then says so above what the device has, and the next round, within a minute, asks again.
 */
let down: MaintenanceNotice | null = null;
let answeredAt = 0;
let askedEdge = false;
const downListeners = new Set<() => void>();

function setDown(notice: MaintenanceNotice | null) {
  if (notice === down) return;
  down = notice;
  if (!notice) askedEdge = false;
  for (const listener of downListeners) listener();
}

/** Keeps an announcement already known; asks the edge once whether this one was announced. */
function noteDown(notice: MaintenanceNotice) {
  setDown(notice.planned || !down ? notice : down);
  if (notice.planned || askedEdge) return;
  askedEdge = true;
  void readMaintenance().then((announced) => { if (announced && down) setDown(announced); });
}

/** The server down for maintenance, as the list last found it, or null. */
export const deviceListDown = (): MaintenanceNotice | null => down;
export function onDeviceListDown(listener: () => void): () => void {
  downListeners.add(listener);
  return () => { downListeners.delete(listener); };
}

/** When the list last heard from the server: its last read, else the newest answer the device kept. */
export function deviceListReadAt(recents: readonly RecentParcel[]): string | null {
  const latest = Math.max(answeredAt, ...recents.map((recent) => Date.parse(recent.lastSeenAt) || 0));
  return latest > 0 ? new Date(latest).toISOString() : null;
}

/** Whether the parcel is still waiting for its first check with the carrier. */
const waiting = (recent: Pick<RecentParcel, 'syncStatus'>) => recent.syncStatus === 'pending' || recent.syncStatus === 'syncing';

/**
 * The parcel the list leads with, on a card that draws its route: the one the
 * deliveries would call Next up, else the newest one still on its way, else
 * one that arrived within the last day. Only a parcel with a located scan can
 * lead; a day after its journey ended it rests among the others, as does one
 * no longer updated.
 */
export function leadParcel(recents: readonly RecentParcel[], now = Date.now()): RecentParcel | null {
  const candidates = recents.filter(({ snapshot: { parcel }, frozen }) => !frozen && parcel.events.some((event) => event.place) && !journeyEndedBefore(parcel, now));
  const next = nextPriorityParcel(candidates.map((recent) => recent.snapshot.parcel), now);
  return candidates.find((recent) => recent.snapshot.parcel === next)
    ?? candidates.find((recent) => isActiveParcel(recent.snapshot.parcel)) ?? candidates[0] ?? null;
}

/**
 * Brings the device's parcels up to date, quietly: each stale one is read
 * through its link, one after the other; an archived one waits until it is
 * opened. A lookup of this device's own that
 * the server no longer has stays, no longer updated and never read again;
 * any other parcel it no longer has leaves the list. Any trouble ends the
 * round, and the list shows what the device has, under a note when the
 * server is down for maintenance. An answer keeps its parcel's place in the
 * list.
 *
 * Returns whether a parcel is still waiting for its first check, which is
 * worth another round soon.
 */
export async function refreshDeviceParcels(recents: readonly RecentParcel[], signal: AbortSignal, now: () => number = Date.now): Promise<boolean> {
  let unanswered = false;
  for (const recent of recents) {
    if (signal.aborted || (typeof navigator !== 'undefined' && navigator.onLine === false)) return false;
    if (recent.frozen || recent.archivedAt) continue;
    const seen = Date.parse(recent.lastSeenAt) || 0;
    const settled = recent.stage && isFinal(recent.stage) && !recent.snapshot.parcel.returnHeld;
    const fresh = waiting(recent) ? WAITING_FRESH_MS : settled ? SETTLED_FRESH_MS : FRESH_MS;
    if (now() - Math.max(seen, checkedAt.get(recent.id) ?? 0) < fresh) {
      unanswered ||= waiting(recent);
      continue;
    }
    try {
      const read = await readParcelLink(recent.id, { key: recent.key, signal });
      if (signal.aborted) return false;
      checkedAt.set(recent.id, now());
      answeredAt = now();
      setDown(null);
      if (read === 'unavailable') freezeRecent(recent.id);
      else {
        rememberParcel({ id: recent.id, view: read, now: seen || now() });
        unanswered ||= waiting(read.parcel);
      }
    } catch (error) {
      if (!signal.aborted && error instanceof ParcelLinkError && error.down) noteDown(error.down);
      return false;
    }
  }
  return unanswered;
}

/**
 * Keeps a list of the device's parcels up to date while it is shown: one round
 * now, then one every minute while the list is in sight, and sooner while a
 * parcel looked up a moment ago still waits for its carrier. Showing the list
 * again, coming back to its window or back online runs a round at once. A
 * round only reads the parcels that are stale. `parcels` is read at each round.
 * Returns the way to stop.
 */
export function watchDeviceParcels(parcels: () => readonly RecentParcel[]): () => void {
  const controller = new AbortController();
  let timer: ReturnType<typeof setTimeout> | undefined;
  let running = false;
  let waits = 0;
  const schedule = (delay: number) => {
    clearTimeout(timer);
    // Out of sight the list waits; showing it again runs a round.
    timer = setTimeout(() => document.hidden ? schedule(PASS_MS) : void round(), delay);
  };
  const round = async () => {
    if (running || controller.signal.aborted) return;
    clearTimeout(timer);
    running = true;
    let unanswered = false;
    try {
      unanswered = await refreshDeviceParcels(parcels(), controller.signal);
    } finally {
      // A round that ran into trouble leaves the next one to try again.
      running = false;
      if (!unanswered) waits = 0;
      if (!controller.signal.aborted) schedule(unanswered ? Math.min(FIRST_CHECK_MAX_MS, FIRST_CHECK_MS * 1.5 ** waits++) : PASS_MS);
    }
  };
  const catchUp = () => { if (!document.hidden) void round(); };
  document.addEventListener('visibilitychange', catchUp);
  window.addEventListener('focus', catchUp);
  window.addEventListener('online', catchUp);
  void round();
  return () => {
    clearTimeout(timer);
    controller.abort();
    document.removeEventListener('visibilitychange', catchUp);
    window.removeEventListener('focus', catchUp);
    window.removeEventListener('online', catchUp);
  };
}

/** Most times one parcel waits for the server to take forgets again; a server still refusing after that keeps it. */
const MOST_FORGET_WAITS = 3;

/** Waits out a server that asked for a pause. */
export type ForgetPause = (milliseconds: number) => Promise<void>;

const pause: ForgetPause = (milliseconds) => new Promise((resolve) => setTimeout(resolve, milliseconds));

/**
 * Forgets the device's parcels: on the server where this device made the
 * lookup and holds its key, then on the device. A parcel someone shared keeps
 * its link, and loses this browser's alerts first; a copy no longer updated
 * has nothing left on the server. The parcels the device holds
 * the key of but cannot list go too. The server takes a few forgets a minute
 * from one network: past those, each waits until it takes them again.
 * `progress` hears which parcel is being forgotten, from 1, of how many.
 * Returns how many could not be forgotten; those stay.
 */
export async function forgetDeviceParcels(
  recents: readonly RecentParcel[],
  progress: (done: number, count: number) => void = () => {},
  wait: ForgetPause = pause,
): Promise<number> {
  let kept = 0;
  const listed = new Set(recents.map((recent) => recent.id));
  const parcels = [...recents, ...strandedParcels().filter((stranded) => !listed.has(stranded.id))];
  for (const [index, recent] of parcels.entries()) {
    progress(index + 1, parcels.length);
    for (let waits = 0; ; waits += 1) {
      try {
        // A lookup is forgotten with its alerts; a parcel someone shared keeps its link, so this browser's alerts go.
        if (recent.key) await forgetParcelLink(recent.id, recent.key);
        else if (!('frozen' in recent && recent.frozen)) await turnOffAlert(recent.id);
        forgetRecent(recent.id);
      } catch (error) {
        if (error instanceof ParcelLinkError && error.kind === 'burst' && waits < MOST_FORGET_WAITS) {
          await wait((error.retryAfterSeconds ?? 60) * 1_000);
          continue;
        }
        // A link the server no longer has is forgotten already.
        if (error instanceof ParcelLinkError && error.kind === 'unavailable') forgetRecent(recent.id);
        else kept += 1;
      }
      break;
    }
  }
  if (!kept) forgetDeviceChecks();
  return kept;
}

/** Drops what the list remembers about its own checks: a device with nothing on it starts over. */
export function forgetDeviceChecks(): void {
  checkedAt.clear();
  answeredAt = 0;
  setDown(null);
}
