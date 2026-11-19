import { useEffect } from 'react';
import { trackAction } from '../lib/analytics';
import { isParcelLinkId } from './linkModel';
import { readParcelLink, type ParcelLinkRead, type ParcelLinkReadOptions } from './links';
import { MOVE_PATH, takeHeldMove } from './moveAddress';
import { announceNotice } from './parcel/Toast';
import { archiveRecent, cleanParcelName, MAX_RECENTS, recentFor, recentsRoom, rememberParcel, type RecentParcel } from './recents';

export { MOVE_PATH } from './moveAddress';

const OWNER_KEY = /^[A-Za-z0-9_-]{43}$/;
/** How many parcels the other device reads at once. */
const READS_AT_ONCE = 4;

/** A parcel as a move carries it: its link, its owner key when this device made the lookup, its name and its fold. */
export interface MovedParcel {
  id: string;
  key: string | null;
  name: string | null;
  archived: boolean;
}

/**
 * What became of a move's parcels: put on the device, no longer kept by the service (`missed`), not reached by their
 * read (`failed`), or left out as the device holds as many parcels as it can (`full`).
 */
export interface MoveOutcome {
  added: number;
  missed: number;
  failed: number;
  full: number;
}

/**
 * The address that takes the parcels on this device to another one: `/move#p=<id>.<key>.<a>.<name>&p=…`. A
 * frozen copy stays: its link is gone, and only this device holds what it last showed.
 */
export function moveURL(recents: readonly RecentParcel[], origin = window.location.origin): string {
  const parts = new URLSearchParams();
  for (const recent of recents) {
    if (recent.frozen) continue;
    parts.append('p', [recent.id, recent.key ?? '', recent.archivedAt ? 'a' : '', recent.name ?? ''].join('.'));
  }
  return `${new URL(MOVE_PATH, origin).href}#${parts}`;
}

/** The parcels a move's `#` carries, as many as a device holds; a part that is not one is passed over. */
export function parcelsInMove(hash: string): MovedParcel[] {
  const moved: MovedParcel[] = [];
  const seen = new Set<string>();
  for (const value of new URLSearchParams(hash.replace(/^#/, '')).getAll('p')) {
    if (moved.length === MAX_RECENTS) break;
    const [id, key, fold, ...name] = value.split('.');
    if (!isParcelLinkId(id) || seen.has(id)) continue;
    seen.add(id);
    moved.push({ id, key: OWNER_KEY.test(key ?? '') ? key : null, name: cleanParcelName(name.join('.')), archived: fold === 'a' });
  }
  return moved;
}

/**
 * The parcels of a move this page was opened with. `holdMove` took them out of the address before the app started,
 * so the keys stay out of the router, the history and anything the page shares or reports.
 */
export function takeMove(): MovedParcel[] {
  const hash = takeHeldMove();
  return hash ? parcelsInMove(hash) : [];
}

/**
 * Puts a move's parcels on this device, each read through its link first. A key the link does not take as its
 * owner's is dropped, and a parcel the service no longer keeps or a read could not reach is passed over. A parcel
 * already here keeps what it has, and gains the key it lacked. The device takes no more than it has room for, so a
 * move never pushes out a parcel it already holds.
 */
export async function receiveMove(
  moved: readonly MovedParcel[],
  read: (id: string, options: ParcelLinkReadOptions) => Promise<ParcelLinkRead> = readParcelLink,
): Promise<MoveOutcome> {
  const outcome: MoveOutcome = { added: 0, missed: 0, failed: 0, full: 0 };
  let room = recentsRoom();
  const queue = moved.filter((parcel) => {
    const here = recentFor(parcel.id);
    if (here) return !here.key && !!parcel.key && !here.frozen;
    if (room > 0) {
      room -= 1;
      return true;
    }
    outcome.full += 1;
    return false;
  });
  async function next(): Promise<void> {
    const parcel = queue.shift();
    if (!parcel) return;
    const view = await read(parcel.id, { key: parcel.key, hidden: true }).catch(() => null);
    if (!view) {
      outcome.failed += 1;
    } else if (view !== 'unavailable') {
      const here = recentFor(parcel.id);
      rememberParcel({ id: parcel.id, view, key: view.link.role === 'owner' ? parcel.key : null, ...(here?.name || !parcel.name ? {} : { name: parcel.name }) });
      if (parcel.archived && !here) archiveRecent(parcel.id, true);
      outcome.added += 1;
    } else {
      outcome.missed += 1;
    }
    return next();
  }
  await Promise.all(Array.from({ length: Math.min(READS_AT_ONCE, queue.length) }, next));
  return outcome;
}

/**
 * Takes a move this page was opened with, whoever opens it: a visitor finds the parcels on the front door, someone
 * signed in is offered to bring them into the account, as after signing in.
 */
export function useMoveArrival(): void {
  useEffect(() => {
    const moved = takeMove();
    if (!moved.length) return;
    void receiveMove(moved).then(({ added, missed, failed, full }) => {
      trackAction('parcels-move-receive', added ? 'success' : 'error');
      // What a move leaves out stays in its link: opened again, it brings the rest and passes over what is here.
      if (failed) announceNotice('move.failed');
      else if (full) announceNotice('move.full', { limit: MAX_RECENTS });
      else if (added) announceNotice('move.received.many', { count: added }, 'success');
      else if (missed) announceNotice('move.missed');
    });
  }, []);
}
