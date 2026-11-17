/**
 * The server down for a few minutes of maintenance: the machine it runs on restarting, or its database being upgraded.
 * Clients keep what they hold, say so calmly, and ask again quietly; nothing signs out or forgets a parcel for it.
 * The edge in front of the server answers `/maintenance.json` and its own 503s with whether the maintenance was
 * announced, and until when (docs/ARCHITECTURE.md, docs/DEPLOYMENT.md).
 */

/** What the edge says about a maintenance: announced or not, and the time it gave for the end, if any. */
export interface MaintenanceNotice {
  planned: boolean;
  backAround: string | null;
}

/** Statuses a proxy or Cloudflare answers with for a server that does not answer itself. */
const DOWN_STATUSES = new Set([502, 503, 504, 520, 521, 522, 523, 524, 525, 526, 527, 530]);
/**
 * The app's own 503s about one part it can't use now, which it words itself: something this server was never set
 * up for, Amazon's or a carrier's check, the browser check. Not the server down.
 */
const PART_UNAVAILABLE = /not configured|amazon|carrier|verification/i;

/** Whether a body is the app's own JSON answer: an object with its `error`. */
export function isServiceAnswer(body: unknown): body is { error: string } {
  return !!body && typeof body === 'object' && !Array.isArray(body) && typeof (body as { error?: unknown }).error === 'string';
}

/** The maintenance a 503's body announces: the edge's `maintenance` object, or null. */
export function announcedMaintenance(body: unknown): MaintenanceNotice | null {
  const maintenance = body && typeof body === 'object' ? (body as { maintenance?: unknown }).maintenance : null;
  return maintenance && typeof maintenance === 'object' ? notice(maintenance) : null;
}

function notice(value: object): MaintenanceNotice {
  const { planned, backAround } = value as { planned?: unknown; backAround?: unknown };
  const back = typeof backAround === 'string' && Number.isFinite(Date.parse(backAround)) ? backAround : null;
  return { planned: planned === true, backAround: planned === true ? back : null };
}

/**
 * Whether an answer means the server is down rather than refusing: a proxy's or Cloudflare's error status
 * (the database's 502 and the sign-in server's 503 too), or a 404 the app did not write, such as the proxy's
 * "404 page not found" while the app starts. The app's own 503 about one part it can't use is not maintenance.
 */
export function serverDown(status: number, body: unknown): boolean {
  if (status === 404) return !isServiceAnswer(body);
  if (!DOWN_STATUSES.has(status)) return false;
  return !(status === 503 && isServiceAnswer(body) && !announcedMaintenance(body) && PART_UNAVAILABLE.test(body.error));
}

/** Whether the device says it is online: a request that got no answer while it is means the server is down. */
export function deviceOnline(): boolean {
  return typeof navigator === 'undefined' || navigator.onLine !== false;
}

/** A request that got no answer at all: the network failed, or it timed out. Not one the caller cancelled. */
export function unanswered(error: unknown): boolean {
  if (error instanceof TypeError) return true;
  if (error instanceof DOMException) return error.name === 'TimeoutError' || error.name === 'NetworkError';
  // Supabase's own name for a sign-in request that got no answer, or a server's 5xx in its place: the session stays.
  return !!error && typeof error === 'object' && (error as { name?: unknown }).name === 'AuthRetryableFetchError';
}

/** The server is down: a failure the list, the parcel page and the apps say calmly, keeping what they show. */
export class ServerDownError extends Error {
  constructor(
    readonly retryAfterMs: number,
    readonly maintenance: MaintenanceNotice | null = null,
    options?: { cause?: unknown },
  ) {
    super('Peek is down for maintenance', options);
    this.name = 'ServerDownError';
  }
}

/** How long to wait before asking again: what the server asked for and the backoff, whichever is longer, at most a minute. */
export function quietRetryDelay(attempt: number, retryAfterMs = 0): number {
  const backoff = Math.min(60_000, 5_000 * 2 ** Math.max(0, attempt));
  return Math.min(60_000, Math.max(backoff, retryAfterMs));
}

/**
 * What `/maintenance.json` says, asked only once something failed: null when it is not there to say (the edge not
 * in front yet answers the app's own 404 page) or says nothing readable. Never cached.
 */
export async function readMaintenance(signal?: AbortSignal, request: typeof fetch = (input, init) => fetch(input, init)): Promise<MaintenanceNotice | null> {
  try {
    const response = await request('/maintenance.json', { cache: 'no-store', credentials: 'omit', signal: signal ?? AbortSignal.timeout(10_000) });
    if (!response.ok && response.status !== 503) return null;
    const body: unknown = await response.json().catch(() => null);
    if (!body || typeof body !== 'object') return null;
    return announcedMaintenance(body) ?? ('planned' in body ? notice(body) : null);
  } catch {
    return null;
  }
}
