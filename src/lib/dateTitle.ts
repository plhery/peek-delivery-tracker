import { useCallback, useSyncExternalStore } from 'react';
import { useI18n } from '../i18n';
import { localizedFullDate, localizedFullDays } from './format';

/** A pointer that hovers: a desktop's mouse or trackpad, never a finger. */
const HOVERS = '(hover: hover) and (pointer: fine)';

function onHoverChange(changed: () => void): () => void {
  // Safari before 14 listens only by `addListener`: there the title follows the pointer the page opened with.
  const query = window.matchMedia?.(HOVERS);
  query?.addEventListener?.('change', changed);
  return () => query?.removeEventListener?.('change', changed);
}

const hovers = () => window.matchMedia?.(HOVERS).matches ?? false;

/** A date a short one stands for: a day, at a time when `timed`, or the days from it `until` another. */
export interface TitledDate {
  date: Date;
  timed?: boolean;
  until?: Date;
}

/**
 * The full date a desktop shows on hovering a short one, as its `title`: "Thursday, 28 November 2024, 14:32", in the
 * reader's language and clock. None on touch, where a tooltip can't be read, nor on the server, which can't tell.
 */
export function useDateTitle(): (when: TitledDate | null | undefined) => string | undefined {
  const { languageTag, use24HourTime } = useI18n();
  const hover = useSyncExternalStore(onHoverChange, hovers, () => false);
  return useCallback((when: TitledDate | null | undefined) => {
    if (!hover || !when || Number.isNaN(when.date.getTime())) return undefined;
    if (when.until) return localizedFullDays(when.date, when.until, languageTag);
    return localizedFullDate(when.date, languageTag, when.timed ? { use24HourTime } : undefined);
  }, [hover, languageTag, use24HourTime]);
}
