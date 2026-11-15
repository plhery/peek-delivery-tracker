import { useCallback, useEffect, useSyncExternalStore } from 'react';
import type { ApiAuth } from './apiClient';
import { saveMarkNewUpdates, useNotificationPreferences } from '../store/notificationPreferences';
import type { Parcel } from '../types';

/**
 * "New since you last looked": a parcel whose latest scan arrived after its owner last opened it
 * carries a small mark on its card. Opening the parcel clears it, on every device of the account.
 * Settings can switch the mark off; what was seen is kept either way.
 */
export function hasNewUpdate(parcel: Pick<Parcel, 'newsAt' | 'seenAt'>): boolean {
  if (!parcel.newsAt || !parcel.seenAt) return false;
  return Date.parse(parcel.newsAt) > Date.parse(parcel.seenAt);
}

/** The choice as this browser keeps it: the account's, once read, else the one made here. On until switched off. */
export const MARK_NEW_UPDATES_STORAGE_KEY = 'deliveryTrackerMarkNewUpdates';
const eventName = 'peek-mark-new-updates-change';
// Storage that cannot be read or written leaves the choice working for as long as the page lives.
let memory: boolean | null = null;

function read(): boolean {
  if (memory !== null) return memory;
  try { return window.localStorage.getItem(MARK_NEW_UPDATES_STORAGE_KEY) !== 'off'; } catch { return true; }
}

function write(enabled: boolean): void {
  if (read() === enabled) return;
  memory = enabled;
  try {
    if (enabled) window.localStorage.removeItem(MARK_NEW_UPDATES_STORAGE_KEY);
    else window.localStorage.setItem(MARK_NEW_UPDATES_STORAGE_KEY, 'off');
    memory = null;
  } catch {
    // Kept in memory.
  }
  window.dispatchEvent(new Event(eventName));
}

function subscribe(notify: () => void) {
  const onStorage = (event: StorageEvent) => {
    if (event.key === null || event.key === MARK_NEW_UPDATES_STORAGE_KEY) notify();
  };
  window.addEventListener(eventName, notify);
  window.addEventListener('storage', onStorage);
  return () => {
    window.removeEventListener(eventName, notify);
    window.removeEventListener('storage', onStorage);
  };
}

/** Whether cards mark their parcel's new updates. */
export function useMarkNewUpdates(): boolean {
  return useSyncExternalStore(subscribe, read, () => true);
}

/**
 * The choice with its switch. An account's choice follows it to its other devices: what the server
 * holds replaces this browser's copy whenever it is read or saved, and a choice the server could not
 * take stays on this browser until then.
 */
export function useMarkNewUpdatesChoice(auth: ApiAuth | undefined): [boolean, (enabled: boolean) => void] {
  const enabled = useMarkNewUpdates();
  const stored = useNotificationPreferences(auth)?.markNewUpdates;
  useEffect(() => { if (typeof stored === 'boolean') write(stored); }, [stored]);
  const choose = useCallback((next: boolean) => {
    write(next);
    if (auth) void saveMarkNewUpdates(next, auth).catch(() => undefined);
  }, [auth]);
  return [enabled, choose];
}

/**
 * Marks the open parcel as seen while the page is in front: as it opens with news, and again when
 * news arrives under the reader's eyes.
 */
export function useSeenWhileOpen(parcel: Parcel | null, markSeen: (id: string) => Promise<void>): void {
  const id = parcel?.id;
  const news = parcel && hasNewUpdate(parcel) ? parcel.newsAt : undefined;
  useEffect(() => {
    if (!id || !news) return;
    if (document.visibilityState !== 'hidden') {
      void markSeen(id);
      return;
    }
    const onVisible = () => {
      if (document.visibilityState === 'hidden') return;
      document.removeEventListener('visibilitychange', onVisible);
      void markSeen(id);
    };
    document.addEventListener('visibilitychange', onVisible);
    return () => document.removeEventListener('visibilitychange', onVisible);
  }, [id, news, markSeen]);
}
