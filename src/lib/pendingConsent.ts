/**
 * An assistant's request to connect (`/oauth/authorize?…`), answered by someone who signs in with Google, Apple or
 * another provider first. The provider returns to `/` alone, so the request is noted for this tab before leaving, and
 * the deliveries go back to it once signed in.
 */
export const CONSENT_PATH = '/oauth/authorize';
export const PENDING_CONSENT_STORAGE_KEY = 'sdt.web.pendingConsent.v1'; // gitleaks:allow -- sessionStorage name, not a credential
const MAX_AGE_MS = 15 * 60_000;
const MOST_QUERY_LENGTH = 8_000;

/** Notes the request on the consent page's address; anywhere else, nothing. */
export function rememberPendingConsent(now = Date.now()): void {
  try {
    const { pathname, search } = window.location;
    if (pathname !== CONSENT_PATH || !search || search.length > MOST_QUERY_LENGTH) return;
    window.sessionStorage.setItem(PENDING_CONSENT_STORAGE_KEY, JSON.stringify({ query: search, at: now }));
  } catch { /* Without session storage the deliveries open, and the assistant asks again. */ }
}

/** The noted request's address, once: the note is dropped whether it is used or not. */
export function takePendingConsent(now = Date.now()): string | null {
  try {
    const written = window.sessionStorage.getItem(PENDING_CONSENT_STORAGE_KEY);
    if (written === null) return null;
    window.sessionStorage.removeItem(PENDING_CONSENT_STORAGE_KEY);
    const note = JSON.parse(written) as { query?: unknown; at?: unknown } | null;
    const age = now - Number(note?.at);
    if (typeof note?.query !== 'string' || !note.query.startsWith('?') || note.query.length > MOST_QUERY_LENGTH
      || !(age >= 0 && age < MAX_AGE_MS)) return null;
    return `${CONSENT_PATH}${note.query}`;
  } catch {
    return null;
  }
}
