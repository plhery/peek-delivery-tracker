import { useCallback, useEffect, useSyncExternalStore } from 'react';
import type { ApiAuth } from './apiClient';
import type { NotificationPreferences } from './pushNotifications';
import { useNotificationPreferences } from '../store/notificationPreferences';

/**
 * A switch this browser keeps, on until switched off, that follows the account: what the server holds replaces this
 * browser's copy whenever it is read or saved. A choice made here that the account has not taken, made without one or
 * while the server could not take it, stands until it is saved, and goes to the account when its preferences are next
 * read: one made without an account goes to the account signed in to, and one an account made waits for that account,
 * so another signed in meanwhile neither takes it nor loses it.
 */
export function accountChoice({ storageKey, unsavedKey, field, save }: {
  /** Where this browser keeps the choice: "off", or nothing for on. */
  storageKey: string;
  /** Set while a choice made here has not been saved to the account: whose it is ("device" without one) and what. */
  unsavedKey: string;
  /** The choice's name in the account's preferences. */
  field: 'markNewUpdates' | 'showPeekAi';
  save: (enabled: boolean, auth: ApiAuth) => Promise<NotificationPreferences>;
}) {
  const eventName = `peek-choice-change:${storageKey}`;
  // Storage that cannot be read or written leaves the choice working for as long as the page lives.
  let memory: boolean | null = null;
  let unsavedMemory: Unsaved | null | undefined;
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

  function unsaved(): Unsaved | null {
    if (unsavedMemory !== undefined) return unsavedMemory;
    let stored: string | null;
    try { stored = window.localStorage.getItem(unsavedKey); } catch { return null; }
    if (stored === null) return null;
    // Left by an earlier version, which didn't say whose: the next account's, as it was then.
    if (stored === '1') return { owner: null, enabled: read() };
    const [owner, choice] = stored.split(' ');
    if (!owner || (choice !== 'on' && choice !== 'off')) return null;
    return { owner: owner === DEVICE ? null : owner, enabled: choice === 'on' };
  }

  function setUnsaved(value: Unsaved | null): void {
    unsavedMemory = value;
    try {
      if (value) window.localStorage.setItem(unsavedKey, `${value.owner ?? DEVICE} ${value.enabled ? 'on' : 'off'}`);
      else window.localStorage.removeItem(unsavedKey);
      unsavedMemory = undefined;
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
        const left = unsaved();
        if (saved[field] === next && left?.enabled === next && mine(left, auth)) setUnsaved(null);
      }).catch(() => undefined).finally(() => { if (saving === next) saving = null; });
    }, [auth]);
    useEffect(() => {
      const stored = preferences?.[field];
      if (typeof stored !== 'boolean') return;
      const left = unsaved();
      if (!left || !mine(left, auth)) return write(stored);
      write(left.enabled);
      if (stored === left.enabled) setUnsaved(null);
      else keep(left.enabled);
    }, [preferences, keep, auth]);
    const choose = useCallback((next: boolean) => {
      write(next);
      setUnsaved({ owner: auth?.userId ?? null, enabled: next });
      keep(next);
    }, [keep, auth]);
    return [enabled, choose];
  }

  return { useValue, useChoice };
}

const DEVICE = 'device';

/** A choice made here (`enabled`) that the account `owner` has not taken, or made with no account (`owner` null). */
interface Unsaved { owner: string | null; enabled: boolean }

/** Whether an unsaved choice is the signed-in account's to take: its own, or one made without an account. */
function mine(unsaved: Unsaved, auth: ApiAuth | undefined): boolean {
  return !!auth && (unsaved.owner === null || unsaved.owner === auth.userId);
}
