import { useCallback, useEffect, useSyncExternalStore } from 'react';
import type { ApiAuth } from './apiClient';
import type { NotificationPreferences } from './pushNotifications';
import { useNotificationPreferences } from '../store/notificationPreferences';

/**
 * A switch this browser keeps, on until switched off, that follows the account: what the server holds replaces this
 * browser's copy whenever it is read or saved. A choice made here that the account has not taken, made without one or
 * while the server could not take it, stands until it is saved, and goes to the account when its preferences are next
 * read.
 */
export function accountChoice({ storageKey, unsavedKey, field, save }: {
  /** Where this browser keeps the choice: "off", or nothing for on. */
  storageKey: string;
  /** Set while a choice made here has not been saved to the account. */
  unsavedKey: string;
  /** The choice's name in the account's preferences. */
  field: 'markNewUpdates' | 'showPeekAi';
  save: (enabled: boolean, auth: ApiAuth) => Promise<NotificationPreferences>;
}) {
  const eventName = `peek-choice-change:${storageKey}`;
  // Storage that cannot be read or written leaves the choice working for as long as the page lives.
  let memory: boolean | null = null;
  let unsavedMemory: boolean | null = null;
  /** The choice whose save is on its way, so a read answered meanwhile does not send it again. */
  let saving: boolean | null = null;

  function read(): boolean {
    if (memory !== null) return memory;
    try { return window.localStorage.getItem(storageKey) !== 'off'; } catch { return true; }
  }

  function write(enabled: boolean): void {
    if (read() === enabled) return;
    memory = enabled;
    try {
      if (enabled) window.localStorage.removeItem(storageKey);
      else window.localStorage.setItem(storageKey, 'off');
      memory = null;
    } catch {
      // Kept in memory.
    }
    window.dispatchEvent(new Event(eventName));
  }

  function subscribe(notify: () => void) {
    const onStorage = (event: StorageEvent) => {
      if (event.key === null || event.key === storageKey) notify();
    };
    window.addEventListener(eventName, notify);
    window.addEventListener('storage', onStorage);
    return () => {
      window.removeEventListener(eventName, notify);
      window.removeEventListener('storage', onStorage);
    };
  }

  function unsaved(): boolean {
    if (unsavedMemory !== null) return unsavedMemory;
    try { return window.localStorage.getItem(unsavedKey) === '1'; } catch { return false; }
  }

  function setUnsaved(value: boolean): void {
    unsavedMemory = value;
    try {
      if (value) window.localStorage.setItem(unsavedKey, '1');
      else window.localStorage.removeItem(unsavedKey);
      unsavedMemory = null;
    } catch {
      // Kept in memory.
    }
  }

  /** The choice as this browser has it. */
  function useValue(): boolean {
    return useSyncExternalStore(subscribe, read, () => true);
  }

  /** The choice with its switch; with `auth`, it follows the account to its other devices. */
  function useChoice(auth: ApiAuth | undefined): [boolean, (enabled: boolean) => void] {
    const enabled = useValue();
    // Each answer, read or saved, even one that names the same choice.
    const preferences = useNotificationPreferences(auth);
    const keep = useCallback((next: boolean) => {
      if (!auth || saving === next) return;
      saving = next;
      void save(next, auth).then((saved) => {
        // Switched again meanwhile: that choice stands, and its own save is on its way.
        if (saved[field] === next && read() === next) setUnsaved(false);
      }).catch(() => undefined).finally(() => { if (saving === next) saving = null; });
    }, [auth]);
    useEffect(() => {
      const stored = preferences?.[field];
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

  return { useValue, useChoice };
}
