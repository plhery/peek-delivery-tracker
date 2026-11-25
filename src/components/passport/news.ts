import { useCallback, useEffect, useMemo, useSyncExternalStore } from 'react';
import { countrySeenKey, passportBook, stampSeenKey, type CountryStamp, type PassportBook, type StampProgress } from '../../lib/passport';
import type { ParcelWithEvents } from '../../types';

/**
 * What the passport has that this browser has not shown yet: the stamps and countries earned since the reader last
 * opened it. The seen ones are kept on this device, one list per account and one for a guest; the first time a
 * passport is read here, everything it already holds counts as seen, so only what arrives after that is new.
 */
const PREFIX = 'peek-passport-seen:';
const CHANGE = 'peek-passport-seen-change';
// Storage that cannot be read or written keeps the list for as long as the page lives.
const memory = new Map<string, string>();

function readSeen(scope: string): string | null {
  if (memory.has(scope)) return memory.get(scope)!;
  try { return window.localStorage.getItem(PREFIX + scope); } catch { return null; }
}

function writeSeen(scope: string, keys: Iterable<string>) {
  const value = JSON.stringify([...new Set(keys)].sort());
  if (readSeen(scope) === value) return;
  memory.set(scope, value);
  try {
    window.localStorage.setItem(PREFIX + scope, value);
    memory.delete(scope);
  } catch {
    // Kept in memory.
  }
  window.dispatchEvent(new Event(CHANGE));
}

function subscribe(notify: () => void) {
  const onStorage = (event: StorageEvent) => { if (event.key === null || event.key.startsWith(PREFIX)) notify(); };
  window.addEventListener(CHANGE, notify);
  window.addEventListener('storage', onStorage);
  return () => {
    window.removeEventListener(CHANGE, notify);
    window.removeEventListener('storage', onStorage);
  };
}

export interface PassportNewsItem { seen: string; parcelId: string | null; stamp?: StampProgress; country?: CountryStamp }

export interface PassportNews {
  book: PassportBook;
  /** Earned and not shown yet, oldest first. */
  unseen: PassportNewsItem[];
  /** The unseen ones each delivered parcel brought. */
  byParcel: Map<string, PassportNewsItem[]>;
  /** Everything earned now counts as seen. */
  markSeen: () => void;
}

export function usePassportNews(parcels: readonly ParcelWithEvents[], scope: string, loading: boolean, { countries = false } = {}): PassportNews {
  const book = useMemo(() => passportBook(parcels), [parcels]);
  const stored = useSyncExternalStore(subscribe, () => readSeen(scope), () => null);
  const seen = useMemo(() => {
    if (stored === null) return null;
    try { return new Set(JSON.parse(stored) as string[]); } catch { return null; }
  }, [stored]);
  const earned = useMemo(() => [
    ...(countries ? book.countries.map((country): PassportNewsItem => ({ seen: countrySeenKey(country), parcelId: country.parcelId, country })) : []),
    ...book.stamps.filter((stamp) => stamp.earned).map((stamp): PassportNewsItem => ({ seen: stampSeenKey(stamp), parcelId: stamp.parcelId, stamp })),
  ], [book, countries]);
  const markSeen = useCallback(() => { if (!loading) writeSeen(scope, earned.map((item) => item.seen)); }, [earned, loading, scope]);
  // A passport read here for the first time starts with everything it holds already seen.
  useEffect(() => { if (!loading && seen === null) markSeen(); }, [loading, markSeen, seen]);
  return useMemo(() => {
    const unseen = seen ? earned.filter((item) => !seen.has(item.seen)) : [];
    const byParcel = new Map<string, PassportNewsItem[]>();
    for (const item of unseen) if (item.parcelId) byParcel.set(item.parcelId, [...(byParcel.get(item.parcelId) ?? []), item]);
    return { book, unseen, byParcel, markSeen };
  }, [book, earned, markSeen, seen]);
}
