import { useEffect } from 'react';
import { accountChoice } from './accountChoice';
import type { ApiAuth } from './apiClient';
import { saveMarkNewUpdates } from '../store/notificationPreferences';
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
/**
 * Set while a choice made in this browser, as someone without an account or while the server could not take it, has
 * not been saved to the account: it stands over the account's until it is.
 */
export const MARK_NEW_UPDATES_UNSAVED_KEY = 'deliveryTrackerMarkNewUpdatesUnsaved';

const choice = accountChoice({
  storageKey: MARK_NEW_UPDATES_STORAGE_KEY, unsavedKey: MARK_NEW_UPDATES_UNSAVED_KEY, field: 'markNewUpdates', save: saveMarkNewUpdates,
});

/** Whether cards mark their parcel's new updates. */
export const useMarkNewUpdates: () => boolean = choice.useValue;

/** The choice with its switch: an account's choice follows it to its other devices (see `accountChoice`). */
export const useMarkNewUpdatesChoice: (auth: ApiAuth | undefined) => [boolean, (enabled: boolean) => void] = choice.useChoice;

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
