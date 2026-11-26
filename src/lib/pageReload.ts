/**
 * Whether this tab may reload for `key` now: not when it already did within `everyMs`, nor without session storage to
 * remember it, so a page whose reload brings back the same cause cannot reload over and over. Claiming records the time.
 */
export function claimReload(key: string, everyMs: number, now = Date.now()): boolean {
  try {
    if (now - (Number(sessionStorage.getItem(key)) || 0) < everyMs) return false;
    sessionStorage.setItem(key, String(now));
    return true;
  } catch {
    return false;
  }
}
