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

/**
 * Set while a choice made in this browser, as someone without an account or while the server could not take it, has
 * not been saved to the account: it stands over the account's until it is.
 */
export const MARK_NEW_UPDATES_UNSAVED_KEY = 'deliveryTrackerMarkNewUpdatesUnsaved';
let unsavedMemory: boolean | null = null;
/** The choice whose save is on its way, so a read answered meanwhile does not send it again. */
let saving: boolean | null = null;

function unsaved(): boolean {
  if (unsavedMemory !== null) return unsavedMemory;
  try { return window.localStorage.getItem(MARK_NEW_UPDATES_UNSAVED_KEY) === '1'; } catch { return false; }
}

function setUnsaved(value: boolean): void {
  unsavedMemory = value;
  try {
    if (value) window.localStorage.setItem(MARK_NEW_UPDATES_UNSAVED_KEY, '1');
    else window.localStorage.removeItem(MARK_NEW_UPDATES_UNSAVED_KEY);
    unsavedMemory = null;
  } catch {
    // Kept in memory.
  }
}

/** Whether cards mark their parcel's new updates. */
export function useMarkNewUpdates(): boolean {
  return useSyncExternalStore(subscribe, read, () => true);
}

/**
 * The choice with its switch. An account's choice follows it to its other devices: what the server
 * holds replaces this browser's copy whenever it is read or saved. A choice made here that the
 * account has not taken, made without one or while the server could not take it, stands until it
 * is saved, and goes to the account when its preferences are next read.
 */
export function useMarkNewUpdatesChoice(auth: ApiAuth | undefined): [boolean, (enabled: boolean) => void] {
  const enabled = useMarkNewUpdates();
  // Each answer, read or saved, even one that names the same choice.
  const preferences = useNotificationPreferences(auth);
  const keep = useCallback((next: boolean) => {
    if (!auth || saving === next) return;
    saving = next;
    void saveMarkNewUpdates(next, auth).then((saved) => {
      // Switched again meanwhile: that choice stands, and its own save is on its way.
      if (saved.markNewUpdates === next && read() === next) setUnsaved(false);
    }).catch(() => undefined).finally(() => { if (saving === next) saving = null; });
  }, [auth]);
  useEffect(() => {
    const stored = preferences?.markNewUpdates;
    if (typeof stored !== 'boolean') return;
    if (!unsaved()) write(stored);
    else if (stored === read()) setUnsaved(false);
    else keep(read());
  }, [preferences, keep]);
  const choose = useCallback((next: boolean) => {
    write(next);
    setUnsaved(true);
    keep(next);
  }, [keep]);
  return [enabled, choose];
}

/**
 * Marks the open parcel as seen while the page is in front: as it opens with news, and again when
 * news arrives under the reader's eyes.
 */
export function useSeenWhileOpen(parcel: Parcel | null, markSeen: (id: string) => Promise<void>): void {
  const id = parcel?.id;
  const news = parcel && hasNewUpdate(parcel) ? parcel.newsAt : undefined;
  const unopened = !!parcel?.seenAt && Date.parse(parcel.seenAt) === 0;
  useEffect(() => {
    if (!id || (!news && !unopened)) return;
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
  }, [id, news, unopened, markSeen]);
}
