import { authenticatedFetch, type ApiAuth } from '../../lib/apiClient';
import { ParcelAlreadyExistsError, type ParcelRepo } from '../../types';
import { linkNote } from '../deviceNotes';
import { claimParcelLinks, forgetParcelLink, readParcelLink } from '../links';
import { forgetRecent, recentFor, type RecentParcel } from '../recents';

/**
 * "Bring these parcels too?": the other parcels this device followed before
 * someone signed in, looked up here or shared with it. Each is offered once;
 * what was declined stays on the device, and is not asked about again.
 */
export const BRING_ALONG_STORAGE_KEY = 'sdt.peek.bringAlong.v1'; // gitleaks:allow -- public localStorage name
// Without usable storage the answer lasts as long as the page.
let memory: string[] | null = null;

/** The link ids this device was already asked about. */
export function offeredParcels(): ReadonlySet<string> {
  if (memory) return new Set(memory);
  try {
    const value: unknown = JSON.parse(window.localStorage.getItem(BRING_ALONG_STORAGE_KEY) ?? '[]');
    if (Array.isArray(value)) return new Set(value.filter((id): id is string => typeof id === 'string'));
  } catch { /* A damaged or unreadable list is an empty one. */ }
  return new Set();
}

export function markOffered(ids: readonly string[]): void {
  // Only a parcel the device could still offer can be asked about again, which bounds the list.
  const offered = [...new Set([...offeredParcels(), ...ids])].filter((id) => {
    const recent = recentFor(id);
    return recent !== null && keepable(recent);
  });
  try {
    window.localStorage.setItem(BRING_ALONG_STORAGE_KEY, JSON.stringify(offered));
    memory = null;
  } catch {
    memory = offered;
  }
}

/**
 * Whether an account can take the parcel: one looked up here, whose key the device holds, or one shared with
 * it whose link, as last read, lets its viewers keep it (the number shown, a gift once it has arrived). One the
 * service forgot is gone from its link.
 */
function keepable(recent: RecentParcel): boolean {
  return !!recent.key || (!recent.frozen && recent.snapshot.link.canKeep);
}

/** The device's parcels someone signed in can take along. */
export function bringable(recents: readonly RecentParcel[], pendingId: string | null): RecentParcel[] {
  const offered = offeredParcels();
  return recents.filter((recent) => keepable(recent) && recent.id !== pendingId && !offered.has(recent.id));
}

/** How many parcels one request keeps at most. */
const CLAIM_BATCH = 20;

/**
 * How bringing parcels along ended: how many joined the account or were there
 * already, and the ones it refused. `quota`: the account is full; such a parcel
 * stays on the device, to be offered again. `unavailable`: its link is gone.
 */
export interface BroughtAlong {
  brought: number;
  refused: { id: string; reason: 'quota' | 'unavailable' }[];
}

/** Archives a parcel the account has just taken: one the device had archived stays archived. A failure leaves it listed. */
async function archiveKept(packageId: string, auth: ApiAuth): Promise<void> {
  await authenticatedFetch(`/api/packages/${encodeURIComponent(packageId)}`, auth, { method: 'DELETE', signal: auth.signal })
    .catch(() => undefined);
}

/** Mutes a parcel the account has just taken whose alerts were turned off here. A failure leaves its alerts on. */
async function muteKept(packageId: string, auth: ApiAuth): Promise<void> {
  await authenticatedFetch(`/api/packages/${encodeURIComponent(packageId)}/notifications`, auth, {
    method: 'PATCH',
    body: JSON.stringify({ muted: true }),
    signal: auth.signal,
  }).catch(() => undefined);
}

/**
 * Keeps several of the device's parcels in the account, twenty to a request. A
 * parcel the account now has leaves the device as soon as its request answers,
 * archived in the account when it was archived here, and muted there when its
 * alerts were turned off here, unless the account had it already.
 */
export async function bringAlong(parcels: readonly RecentParcel[], auth: ApiAuth): Promise<BroughtAlong> {
  const outcome: BroughtAlong = { brought: 0, refused: [] };
  for (let start = 0; start < parcels.length; start += CLAIM_BATCH) {
    const batch = parcels.slice(start, start + CLAIM_BATCH);
    const results = await claimParcelLinks(batch.map(({ id, key, name }) => ({ id, key, label: name })), auth, auth.signal);
    for (const result of results) {
      if (result.outcome === 'quota' || result.outcome === 'unavailable') {
        outcome.refused.push({ id: result.id, reason: result.outcome });
        continue;
      }
      if (result.outcome === 'kept' && result.packageId) {
        if (batch.find((recent) => recent.id === result.id)?.archivedAt) await archiveKept(result.packageId, auth);
        if (linkNote(result.id).muted) await muteKept(result.packageId, auth);
      }
      forgetRecent(result.id);
      outcome.brought += 1;
    }
  }
  return outcome;
}

/** The demo's counterpart: each parcel moves into the demo deliveries with its history, and its link is forgotten. */
export async function bringAlongInDemo(parcels: readonly RecentParcel[], repo: ParcelRepo): Promise<BroughtAlong> {
  const outcome: BroughtAlong = { brought: 0, refused: [] };
  for (const recent of parcels) {
    const read = await readParcelLink(recent.id, { key: recent.key }).catch(() => recent.snapshot);
    if (read === 'unavailable' || !read.link.canKeep || !read.parcel.trackingNumber) {
      outcome.refused.push({ id: recent.id, reason: 'unavailable' });
      continue;
    }
    try {
      const added = repo.adopt ? await repo.adopt(read.parcel, recent.name ?? '')
        : await repo.add({ trackingNumber: read.parcel.trackingNumber, label: recent.name ?? '', carrier: read.parcel.carrier });
      if (linkNote(recent.id).muted) await repo.setNotificationsMuted?.(added.id, true);
      if (recent.archivedAt) await repo.remove(added.id);
    } catch (error) {
      if (!(error instanceof ParcelAlreadyExistsError)) throw error;
    }
    if (recent.key) await forgetParcelLink(recent.id, recent.key).catch(() => undefined);
    forgetRecent(recent.id);
    outcome.brought += 1;
  }
  return outcome;
}
