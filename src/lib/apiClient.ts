import type { ApiErrorResponse } from '../generated/apiContract';
import { apiAnalyticsEvent, trackAction } from './analytics';

export class ApiAuthenticationError extends Error {
  constructor() {
    super('Your sign-in expired. Please sign in again.');
    this.name = 'ApiAuthenticationError';
  }
}

/**
 * The action needs a sign-in from the last few minutes, and the session is older: it still stands, and signing in
 * again here lets the action through.
 */
export class ApiRecentSignInError extends Error {
  constructor() {
    super('Sign in again to continue.');
    this.name = 'ApiRecentSignInError';
  }
}

const RECENT_SIGN_IN: NonNullable<ApiErrorResponse['code']> = 'recent_sign_in';

/** The server's 401 for an action that needs a recent sign-in, which keeps the session. The body stays to be read. */
async function asksForRecentSignIn(response: Response): Promise<boolean> {
  if (response.status !== 401) return false;
  try {
    const body = await response.clone().json() as Partial<ApiErrorResponse> | null;
    return body?.code === RECENT_SIGN_IN;
  } catch {
    return false;
  }
}

export interface ApiAuth {
  userId: string;
  getAccessToken: (refresh?: boolean) => Promise<string | null>;
  /** Aborted synchronously when this sign-in ends, including signing back into the same account. */
  signal?: AbortSignal;
  onAuthenticationFailure?: () => Promise<void>;
}

/** Bound operations that do not themselves support cancellation, such as token refresh. */
export function abortable<T>(operation: Promise<T>, signal: AbortSignal): Promise<T> {
  return new Promise((resolve, reject) => {
    const abort = () => reject(signal.reason);
    signal.addEventListener('abort', abort, { once: true });
    if (signal.aborted) abort();
    operation.then(resolve, reject).finally(() => signal.removeEventListener('abort', abort));
  });
}

/** One deadline covers token lookup, the request and its single authentication retry. */
async function performAuthenticatedFetch(
  path: string,
  auth: ApiAuth | undefined,
  init?: RequestInit,
): Promise<Response> {
  const signal = AbortSignal.any([
    AbortSignal.timeout(30_000),
    ...(init?.signal ? [init.signal] : []),
    ...(auth?.signal ? [auth.signal] : []),
  ]);
  async function token(refresh = false) {
    signal.throwIfAborted();
    const value = await abortable(auth?.getAccessToken(refresh) ?? Promise.resolve(null), signal);
    signal.throwIfAborted();
    return value;
  }
  async function perform(accessToken: string | null) {
    signal.throwIfAborted();
    const headers = new Headers(init?.headers);
    headers.set('X-Requested-With', 'XMLHttpRequest');
    if (init?.body) headers.set('Content-Type', 'application/json');
    if (accessToken) headers.set('Authorization', `Bearer ${accessToken}`);
    const response = await abortable(fetch(path, {
      ...init, cache: 'no-store', redirect: 'manual', headers, signal,
    }), signal);
    signal.throwIfAborted();
    return response;
  }

  let response = await perform(await token());
  // A refreshed token keeps its session's sign-in time, so it can't pass where a recent sign-in is needed.
  let recentSignIn = await abortable(asksForRecentSignIn(response), signal);
  if (auth && !recentSignIn && (response.status === 401 || response.status === 403)) {
    // A transient refresh failure must not discard a recoverable session.
    const accessToken = await token(true);
    if (accessToken) {
      response = await perform(accessToken);
      recentSignIn = await abortable(asksForRecentSignIn(response), signal);
    }
  }
  signal.throwIfAborted();
  // Not a lost session: the caller asks for a sign-in in place.
  if (recentSignIn) throw new ApiRecentSignInError();
  if (response.type === 'opaqueredirect' || response.redirected
    || response.status === 401 || response.status === 403) {
    // Local sign-out starts synchronously; notification cleanup cannot hold up this request.
    void auth?.onAuthenticationFailure?.().catch(() => undefined);
    throw new ApiAuthenticationError();
  }
  return response;
}

/** Record one result after any auth retry; background reads are excluded. */
export async function authenticatedFetch(path: string, auth: ApiAuth | undefined, init?: RequestInit): Promise<Response> {
  const event = apiAnalyticsEvent(path, init?.method ?? 'GET', init?.body);
  try {
    const response = await performAuthenticatedFetch(path, auth, init);
    if (event) trackAction(event, response.ok ? (response.status === 202 ? 'accepted' : 'success') : 'error');
    return response;
  } catch (error) {
    if (event && !(error instanceof DOMException && error.name === 'AbortError')) trackAction(event, 'error');
    throw error;
  }
}
