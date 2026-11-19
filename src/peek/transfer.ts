import { useEffect } from 'react';
import { trackAction } from '../lib/analytics';
import { isParcelLinkId } from './linkModel';
import { readParcelLink, type ParcelLinkRead, type ParcelLinkReadOptions } from './links';
import { announceNotice } from './parcel/Toast';
import { archiveRecent, cleanParcelName, recentFor, rememberParcel, type RecentParcel } from './recents';

/**
 * The address another phone or computer opens to take this device's parcels. What it carries is in its `#`, which
 * the browser never sends: the server sees `/move` and nothing else.
 */
export const MOVE_PATH = '/move';
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

/** The parcels a move's `#` carries; a part that is not one is passed over. */
export function parcelsInMove(hash: string): MovedParcel[] {
  const moved: MovedParcel[] = [];
  for (const value of new URLSearchParams(hash.replace(/^#/, '')).getAll('p')) {
    const [id, key, fold, ...name] = value.split('.');
    if (!isParcelLinkId(id) || moved.some((parcel) => parcel.id === id)) continue;
    moved.push({ id, key: OWNER_KEY.test(key ?? '') ? key : null, name: cleanParcelName(name.join('.')), archived: fold === 'a' });
  }
  return moved;
}

/**
 * The parcels of a move this page was opened with. The address loses its `#` at once, so the keys stay out of the
 * history and out of anything the page shares or reports.
 */
export function takeMove(): MovedParcel[] {
  if (window.location.pathname.replace(/\/$/, '') !== MOVE_PATH) return [];
  const moved = parcelsInMove(window.location.hash);
  window.history.replaceState(window.history.state, '', `/${window.location.search}`);
  return moved;
}

/**
 * Puts a move's parcels on this device, each read through its link first. A key the link does not take as its
 * owner's is dropped, and a parcel the service no longer keeps (`missed`) or a read could not reach (`failed`) is
 * passed over. A parcel already here keeps what it has, and gains the key it lacked.
 */
export async function receiveMove(
  moved: readonly MovedParcel[],
  read: (id: string, options: ParcelLinkReadOptions) => Promise<ParcelLinkRead> = readParcelLink,
): Promise<{ added: number; missed: number; failed: number }> {
  let added = 0;
  let missed = 0;
  let failed = 0;
  const queue = moved.filter((parcel) => {
    const here = recentFor(parcel.id);
    return !here || (!here.key && !!parcel.key && !here.frozen);
  });
  async function next(): Promise<void> {
    const parcel = queue.shift();
    if (!parcel) return;
    const view = await read(parcel.id, { key: parcel.key, hidden: true }).catch(() => null);
    if (!view) {
      failed += 1;
    } else if (view !== 'unavailable') {
      const here = recentFor(parcel.id);
      rememberParcel({ id: parcel.id, view, key: view.link.role === 'owner' ? parcel.key : null, ...(here?.name || !parcel.name ? {} : { name: parcel.name }) });
      if (parcel.archived && !here) archiveRecent(parcel.id, true);
      added += 1;
    } else {
      missed += 1;
    }
    return next();
  }
  await Promise.all(Array.from({ length: Math.min(READS_AT_ONCE, queue.length) }, next));
  return { added, missed, failed };
}

/**
 * Takes a move this page was opened with, whoever opens it: a visitor finds the parcels on the front door, someone
 * signed in is offered to bring them into the account, as after signing in.
 */
export function useMoveArrival(): void {
  useEffect(() => {
    const moved = takeMove();
    if (!moved.length) return;
    void receiveMove(moved).then(({ added, missed, failed }) => {
      trackAction('parcels-move-receive', added ? 'success' : 'error');
      if (added) announceNotice('move.received.many', { count: added }, 'success');
      // The link still holds them: opened again once the connection is back, it brings them.
      else if (failed) announceNotice('move.failed');
      else if (missed) announceNotice('move.missed');
    });
  }, []);
}
