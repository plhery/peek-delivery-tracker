import type { ApiAssistantScope } from '../generated/apiContract';

/**
 * An action that asked for a recent sign-in, noted for this tab before a sign-in leaves the page or completes. A
 * provider returns to `/` alone and a code for another account starts its deliveries afresh: the note says where the
 * person was, what they typed, and for which account, so that it goes on there for that account alone.
 */
export type SignInAgainNote =
  | { action: 'personal-token'; userId: string; name: string; writes: boolean }
  | { action: 'delete-account'; userId: string }
  | { action: 'assistant-consent'; userId: string; query: string; scopes: ApiAssistantScope[] };

export type SignInAgainAction = SignInAgainNote['action'];

export const SIGN_IN_AGAIN_STORAGE_KEY = 'sdt.web.signInAgain.v1'; // gitleaks:allow -- sessionStorage name, not a credential
// Past this, the sign-in is no longer recent enough for the action anyway.
const MAX_AGE_MS = 15 * 60_000;
const MOST_TEXT_LENGTH = 8_000;
const SCOPES: readonly ApiAssistantScope[] = ['parcels:read', 'parcels:write'];

export function noteSignInAgain(note: SignInAgainNote, now = Date.now()): void {
  try {
    window.sessionStorage.setItem(SIGN_IN_AGAIN_STORAGE_KEY, JSON.stringify({ ...note, at: now }));
  } catch { /* Without session storage the person starts again where the provider returns them. */ }
}

function shaped(value: unknown): SignInAgainNote | null {
  if (!value || typeof value !== 'object') return null;
  const note = value as Record<string, unknown>;
  if (typeof note.userId !== 'string' || !note.userId) return null;
  const text = (field: unknown) => typeof field === 'string' && field.length <= MOST_TEXT_LENGTH;
  if (note.action === 'personal-token' && text(note.name) && typeof note.writes === 'boolean') {
    return { action: 'personal-token', userId: note.userId, name: note.name as string, writes: note.writes };
  }
  if (note.action === 'delete-account') return { action: 'delete-account', userId: note.userId };
  if (note.action === 'assistant-consent' && text(note.query) && Array.isArray(note.scopes)) {
    return { action: 'assistant-consent', userId: note.userId, query: note.query as string, scopes: SCOPES.filter((scope) => (note.scopes as unknown[]).includes(scope)) };
  }
  return null;
}

/**
 * The note, once, when it is for one of `actions` and recent: it is then dropped, as is one too old or unreadable.
 * A note for another action stays for the screen that takes it.
 */
export function takeSignInAgain<Action extends SignInAgainAction>(
  actions: readonly Action[],
  now = Date.now(),
): Extract<SignInAgainNote, { action: Action }> | null {
  try {
    const written = window.sessionStorage.getItem(SIGN_IN_AGAIN_STORAGE_KEY);
    if (written === null) return null;
    const value = JSON.parse(written) as { at?: unknown } | null;
    const age = now - Number(value?.at);
    const note = age >= 0 && age < MAX_AGE_MS ? shaped(value) : null;
    if (note && !(actions as readonly SignInAgainAction[]).includes(note.action)) return null;
    window.sessionStorage.removeItem(SIGN_IN_AGAIN_STORAGE_KEY);
    return note as Extract<SignInAgainNote, { action: Action }> | null;
  } catch {
    return null;
  }
}

/** Takes back a note for one of `actions`: the sign-in failed, or the person left the action. */
export function dropSignInAgain(actions: readonly SignInAgainAction[]): void {
  takeSignInAgain(actions);
}
