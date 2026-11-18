/**
 * "Add it after signing in": what a visitor typed at the door when it asked
 * them to sign in (several numbers in one text, or no lookups left for the
 * day), held for this tab so it survives the round trip to a sign-in provider.
 * The deliveries' add sheet opens with it once they are signed in, once.
 */
export const PENDING_ADD_STORAGE_KEY = 'sdt.peek.pendingAdd.v1'; // gitleaks:allow -- sessionStorage name, not a credential
const MAX_AGE_MS = 60 * 60_000;
const MAX_LENGTH = 10_000;

// Without usable storage the note lasts as long as the page, which covers signing in with an emailed code.
let memory: string | null = null;

/** Notes the text to add once someone is signed in. */
export function rememberPendingAdd(text: string, now = Date.now()): void {
  const typed = text.trim().slice(0, MAX_LENGTH);
  if (!typed) return;
  memory = JSON.stringify({ text: typed, at: now });
  try { window.sessionStorage.setItem(PENDING_ADD_STORAGE_KEY, memory); } catch { /* The page keeps it. */ }
}

/** Drops the note: the visitor closed the sheet, and a later sign-in adds nothing by surprise. */
export function clearPendingAdd(): void {
  memory = null;
  try { window.sessionStorage.removeItem(PENDING_ADD_STORAGE_KEY); } catch { /* Nothing was stored. */ }
}

/** The text to add, dropped as it is read; nothing once an hour has passed. */
export function takePendingAdd(now = Date.now()): string | null {
  let note = memory;
  try { note = window.sessionStorage.getItem(PENDING_ADD_STORAGE_KEY) ?? memory; } catch { /* The page's copy. */ }
  clearPendingAdd();
  try {
    const parsed = JSON.parse(note ?? 'null') as { text?: unknown; at?: unknown } | null;
    const age = now - Number(parsed?.at);
    return parsed && typeof parsed.text === 'string' && parsed.text && age >= 0 && age < MAX_AGE_MS ? parsed.text : null;
  } catch {
    return null;
  }
}
