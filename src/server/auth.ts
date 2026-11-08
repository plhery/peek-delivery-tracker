import 'server-only';

import { createHash } from 'node:crypto';
import { SupabaseUserClient } from './supabase';
import { authConfiguration } from './runtime';
import { isRecord } from './types';

const INTERACTIVE_AUTH_METHODS = new Set([
  'magiclink',
  'oauth',
  'otp',
  'password',
  'sso/saml',
  'totp',
]);
const UUID = /^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$/i;
const JWT_SEGMENT = /^[A-Za-z0-9_-]+={0,2}$/;

export interface SupabaseUser {
  id: string;
  email: string | null;
  /** Whether the Auth server has confirmed that the address is the account's. Left out, it has not. */
  emailConfirmed?: boolean;
  authenticatedAt: Date | null;
  sessionId: string | null;
}

export class SupabaseAuthError extends Error {
  constructor(message = 'Supabase access token is invalid', options?: ErrorOptions) {
    super(message, options);
    this.name = 'SupabaseAuthError';
  }
}

export class SupabaseAuthUnavailableError extends Error {
  constructor(options?: ErrorOptions) {
    super('Supabase authentication is temporarily unavailable', options);
    this.name = 'SupabaseAuthUnavailableError';
  }
}

export class SupabaseAuthenticator {
  readonly #cache = new Map<string, { expiresAt: number; user: SupabaseUser }>();

  constructor(
    readonly url: string,
    readonly publishableKey: string,
    readonly options: {
      timeoutMs?: number;
      cacheMs?: number;
      clock?: () => number;
    } = {},
  ) {
    if (!url.trim() || !publishableKey.trim()) {
      throw new TypeError('Supabase Auth requires a URL and publishable key');
    }
  }

  async validate(token: string | null | undefined, useCache = true): Promise<SupabaseUser> {
    if (!token || token.length > 16_384) {
      throw new SupabaseAuthError('Supabase access token is missing or invalid');
    }
    if (!mayBeAccessToken(token)) throw new SupabaseAuthError('Supabase access token is malformed');
    const fingerprint = createHash('sha256').update(token).digest('hex');
    const now = (this.options.clock ?? Date.now)();
    if (useCache) {
      const cached = this.#cache.get(fingerprint);
      if (cached && cached.expiresAt > now) return cached.user;
    }

    let response: Response;
    try {
      response = await fetch(`${this.url}/auth/v1/user`, {
        headers: {
          Accept: 'application/json',
          apikey: this.publishableKey,
          Authorization: `Bearer ${token}`,
        },
        cache: 'no-store',
        redirect: 'error',
        signal: AbortSignal.timeout(this.options.timeoutMs ?? 5_000),
      });
    } catch (error) {
      throw new SupabaseAuthUnavailableError({ cause: error });
    }
    if (response.status === 401 || response.status === 403) throw new SupabaseAuthError();
    if (!response.ok) throw new SupabaseAuthUnavailableError();

    let payload: unknown;
    try {
      payload = await response.json();
    } catch (error) {
      throw new SupabaseAuthUnavailableError({ cause: error });
    }
    if (!isRecord(payload) || typeof payload.id !== 'string' || !UUID.test(payload.id)) {
      throw new SupabaseAuthUnavailableError();
    }
    if (payload.is_anonymous === true) {
      throw new SupabaseAuthError('Anonymous Supabase users are not accepted');
    }

    const claims = tokenClaims(token, payload.id);
    const user: SupabaseUser = {
      id: payload.id.toLowerCase(),
      email: typeof payload.email === 'string' ? payload.email : null,
      emailConfirmed: typeof payload.email_confirmed_at === 'string' && payload.email_confirmed_at !== '',
      authenticatedAt: claims.authenticatedAt,
      sessionId: claims.sessionId,
    };

    // The answer holds until the token expires, and a minute at most: a token without a
    // usable expiry is asked about every time.
    const expiresAt = claims.expiresAt === null
      ? now
      : Math.min(now + Math.max(0, this.options.cacheMs ?? 60_000), claims.expiresAt);
    if (expiresAt <= now) return user;
    if (this.#cache.size >= 256) {
      for (const [key, value] of this.#cache) {
        if (value.expiresAt <= now) this.#cache.delete(key);
      }
      if (this.#cache.size >= 256) {
        this.#cache.delete(this.#cache.keys().next().value as string);
      }
    }
    this.#cache.set(fingerprint, { expiresAt, user });
    return user;
  }

  userClient(token: string): SupabaseUserClient {
    return new SupabaseUserClient(this.url, this.publishableKey, token);
  }
}

/** A segment of a JWT as the JSON object it holds, or null. */
function jwtObject(segment: string): Record<string, unknown> | null {
  try {
    const value: unknown = JSON.parse(Buffer.from(segment, 'base64url').toString('utf8'));
    return isRecord(value) ? value : null;
  } catch {
    return null;
  }
}

/**
 * Whether the token has the shape of one Auth issues: a JWT whose header names its
 * algorithm and whose claims name the account by its UUID, as every Supabase access token
 * does. Anything else, such as a personal token sent to the wrong address, Auth would
 * refuse, so it is refused here without asking.
 */
function mayBeAccessToken(token: string): boolean {
  const parts = token.split('.');
  if (parts.length !== 3 || !parts.every((part) => JWT_SEGMENT.test(part))) return false;
  const header = jwtObject(parts[0]!);
  const claims = jwtObject(parts[1]!);
  return typeof header?.alg === 'string' && typeof claims?.sub === 'string' && UUID.test(claims.sub);
}

/**
 * What the token says of its session, once Auth has accepted it for `userId`: when its
 * holder last signed in, the session, and when the token expires (milliseconds).
 */
function tokenClaims(
  token: string,
  userId: string,
): { authenticatedAt: Date | null; sessionId: string | null; expiresAt: number | null } {
  const none = { authenticatedAt: null, sessionId: null, expiresAt: null };
  const parts = token.split('.');
  const claims = parts.length === 3 ? jwtObject(parts[1]!) : null;
  if (!claims || claims.sub !== userId) return none;
  const expiresAt = typeof claims.exp === 'number' && Number.isFinite(claims.exp) ? claims.exp * 1_000 : null;
  const sessionId = typeof claims.session_id === 'string' && UUID.test(claims.session_id)
    ? claims.session_id.toLowerCase()
    : null;
  let authenticatedAt: Date | null = null;
  if (Array.isArray(claims.amr)) {
    for (const entry of claims.amr) {
      if (
        !isRecord(entry)
        || typeof entry.method !== 'string'
        || !INTERACTIVE_AUTH_METHODS.has(entry.method)
        || typeof entry.timestamp !== 'number'
        || !Number.isFinite(entry.timestamp)
      ) continue;
      const candidate = new Date(entry.timestamp * 1_000);
      if (
        Number.isFinite(candidate.getTime())
        && (authenticatedAt === null || candidate > authenticatedAt)
      ) authenticatedAt = candidate;
    }
  }
  return { authenticatedAt, sessionId, expiresAt };
}

interface AuthRuntime {
  signature: string;
  authenticator: SupabaseAuthenticator;
}

const globalAuth = globalThis as typeof globalThis & {
  __deliveryAuthRuntime?: AuthRuntime;
};

export function authenticator(): SupabaseAuthenticator | null {
  const configuration = authConfiguration();
  if (!configuration) return null;
  const signature = `${configuration.url}\u0000${configuration.publishableKey}`;
  if (globalAuth.__deliveryAuthRuntime?.signature !== signature) {
    globalAuth.__deliveryAuthRuntime = {
      signature,
      authenticator: new SupabaseAuthenticator(
        configuration.url,
        configuration.publishableKey,
      ),
    };
  }
  return globalAuth.__deliveryAuthRuntime.authenticator;
}
