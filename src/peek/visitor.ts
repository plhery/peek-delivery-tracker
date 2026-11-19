import { useMemo } from 'react';
import { useEntryExperience, type EntryScreen } from '../lib/experience';
import { clearPendingKeep, rememberPendingKeep } from './pending';
import { leaveParcelLink, parcelLinkIdFromPath } from './route';
import type { PeekSession } from './session';

/** Marks the step in the history that a parcel page left for signing in, so backing out of it returns there. */
const SIGN_IN_FROM = 'peekSignInFrom';

/** The parcel page the sign-in step was opened from, while the history is still at that step. */
function signInFrom(): string | null {
  const state: unknown = window.history.state;
  const from = state && typeof state === 'object' ? (state as Record<string, unknown>)[SIGN_IN_FROM] : null;
  return typeof from === 'string' && parcelLinkIdFromPath(from) ? from : null;
}

/**
 * The session of someone who is not signed in, for the app's entry points.
 * Signing in happens at `/`, where a sign-in provider returns to: a parcel
 * page is left for it, and the parcel to keep is noted for afterwards. Going
 * back from the sign-in step returns to that parcel page, not the front door.
 */
export function useVisitorSession(account: 'checking' | 'visitor'): PeekSession & {
  /** The sign-in step's own navigation: going back abandons the parcel that was to be kept. */
  leaveSignIn(next: EntryScreen): void;
} {
  const { navigate } = useEntryExperience();
  return useMemo(() => ({
    account,
    signIn(keepLinkId?: string, email = false) {
      if (keepLinkId) rememberPendingKeep(keepLinkId, { email });
      const from = window.location.pathname;
      if (parcelLinkIdFromPath(from)) leaveParcelLink('/', { [SIGN_IN_FROM]: from });
      navigate('sign-in');
    },
    leaveSignIn(next: EntryScreen) {
      if (next === 'welcome') clearPendingKeep();
      if (next === 'welcome' && signInFrom()) {
        // The parcel's own step is the one before: the sign-in step ends once the page is back.
        window.addEventListener('popstate', () => navigate(next), { once: true });
        window.history.back();
        return;
      }
      navigate(next);
    },
  }), [account, navigate]);
}
