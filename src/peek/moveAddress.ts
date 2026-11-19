/**
 * The address another phone or computer opens to take this device's parcels. What it carries is in its `#`, which
 * the browser never sends: the server sees `/move` and nothing else.
 */
export const MOVE_PATH = '/move';

let held: string | null = null;

/**
 * Takes a move's `#` out of the address before the app starts, so its owner keys never reach the router, the
 * history or anything else that reads the address. The page keeps its path and query. Runs from
 * `instrumentation-client.ts`, so it imports nothing.
 */
export function holdMove(location: Location = window.location, history: History = window.history): void {
  if (location.pathname.replace(/\/$/, '') !== MOVE_PATH || location.hash.length < 2) return;
  held = location.hash;
  history.replaceState(history.state, '', `${location.pathname}${location.search}`);
}

/** The `#` the page was opened with, if it was a move's: given once. */
export function takeHeldMove(): string | null {
  const hash = held;
  held = null;
  return hash;
}
