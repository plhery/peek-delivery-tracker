import type { ApiInstallSighting } from '../generated/apiContract';

/**
 * The counting id ([ANALYTICS.md](../../docs/ANALYTICS.md)): a random id this browser keeps to tell visits that come
 * back from one-time ones. It is sent alone to /api/public/seen, at most once a UTC day and again once the reader
 * signs in, with the platform, whether the reader is signed in and how many parcels the browser follows. It never
 * travels with another request, and a new one replaces it after 13 months.
 */
export const COUNTING_STORAGE_KEY = 'sdt.counting.v1'; // gitleaks:allow -- public localStorage name
/** The parcels this browser follows, as recents.ts keeps them. */
const RECENTS_STORAGE_KEY = 'sdt.peek.parcels.v1'; // gitleaks:allow -- public localStorage name
const LIFETIME_MONTHS = 13;

interface Stored {
  id: string;
  /** The UTC day the id was made. */
  made: string;
  /** The UTC day it was last counted, and whether the reader was signed in then. */
  sent?: string;
  signedIn?: boolean;
}

let sending = false;

const day = (now: Date) => now.toISOString().slice(0, 10);

function newId(): string {
  const bytes = crypto.getRandomValues(new Uint8Array(16));
  return btoa(String.fromCharCode(...bytes)).replace(/\+/g, '-').replace(/\//g, '_').replace(/=+$/, '');
}

function read(): Stored | null {
  try {
    const value = JSON.parse(localStorage.getItem(COUNTING_STORAGE_KEY) ?? 'null') as Partial<Stored> | null;
    if (typeof value?.id !== 'string' || !/^[A-Za-z0-9_-]{22}$/.test(value.id)
      || typeof value.made !== 'string' || !/^\d{4}-\d{2}-\d{2}$/.test(value.made)) return null;
    return { id: value.id, made: value.made, sent: typeof value.sent === 'string' ? value.sent : undefined, signedIn: value.signedIn === true };
  } catch { return null; }
}

function expired(stored: Stored, now: Date): boolean {
  const made = new Date(`${stored.made}T00:00:00Z`);
  made.setUTCMonth(made.getUTCMonth() + LIFETIME_MONTHS);
  return now >= made;
}

function followed(): number {
  try {
    const value = JSON.parse(localStorage.getItem(RECENTS_STORAGE_KEY) ?? '[]') as unknown;
    return Array.isArray(value) ? value.length : 0;
  } catch { return 0; }
}

/** Deletes the counting id from this browser, which counts nothing ([analytics.ts](./analytics.ts)). */
export function forgetCountingId(): void {
  try { localStorage.removeItem(COUNTING_STORAGE_KEY); } catch { /* Nothing kept. */ }
}

/**
 * Counts this browser as seen today, unless it already was with the same sign-in state. The caller has checked that
 * this browser is counted. A failed request leaves the day uncounted, for the next page load.
 */
export async function countBrowser(signedIn: boolean, now = new Date()): Promise<void> {
  if (sending) return;
  const today = day(now);
  let stored = read();
  if (stored && stored.sent === today && (stored.signedIn || !signedIn)) return;
  if (!stored || expired(stored, now)) stored = { id: newId(), made: today };
  const sighting: ApiInstallSighting = {
    id: stored.id,
    platform: window.matchMedia?.('(display-mode: standalone)').matches ? 'pwa' : 'web',
    signedIn,
    parcels: followed(),
  };
  sending = true;
  try {
    // Keep the id even if the request fails, so the next attempt counts the same browser.
    localStorage.setItem(COUNTING_STORAGE_KEY, JSON.stringify(stored));
    const response = await fetch('/api/public/seen', {
      method: 'POST', credentials: 'omit', cache: 'no-store', referrerPolicy: 'no-referrer', keepalive: true,
      signal: AbortSignal.timeout(5_000),
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(sighting),
    });
    if (response.status === 204) {
      localStorage.setItem(COUNTING_STORAGE_KEY, JSON.stringify({ ...stored, sent: today, signedIn }));
    }
  } catch { /* Counting must never affect the app. */ }
  finally { sending = false; }
}
