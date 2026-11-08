import 'server-only';

import { createHash, randomUUID } from 'node:crypto';
import { isIP } from 'node:net';
import type { NextRequest } from 'next/server';
import { authenticator, SupabaseAuthError, SupabaseAuthUnavailableError, type SupabaseUser } from './auth';
import { captureOperationalError, logOperationalEvent } from './observability';
import { RateLimiter } from './rateLimit';
import { serviceClient } from './runtime';
import {
  SupabaseError,
  type SupabaseServiceClient,
  type SupabaseUserClient,
} from './supabase';
import { errorMessage, isRecord, type JsonObject } from './types';
import type { ApiErrorResponse } from '../generated/apiContract';

const MAX_JSON_BODY = 16_384;
const PREAUTH_REQUEST_LIMIT = 300;
const PREAUTH_REQUEST_WINDOW_SECONDS = 60;
const RECENT_SIGN_IN_MS = 10 * 60 * 1_000;
const UUID = /^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$/i;
// One limiter per kind of key: a flood of made-up tokens or addresses fills
// its own limiter and leaves the other counts alone.
const limiters = {
  address: new RateLimiter(),
  credential: new RateLimiter(),
  account: new RateLimiter(),
  public: new RateLimiter(),
};

export interface RouteParameters {
  [key: string]: string | string[];
}

export interface RouteContext<Parameters extends RouteParameters = RouteParameters> {
  params: Promise<Parameters>;
}

export interface ApiContext<Parameters extends RouteParameters = RouteParameters> {
  request: NextRequest;
  route: RouteContext<Parameters>;
  requestId: string;
  user: SupabaseUser | null;
  token: string | null;
  userClient: SupabaseUserClient | null;
  service: SupabaseServiceClient | null;
}

export class HttpError extends Error {
  /** Said in the answer beside the message, for a client to tell this refusal from others of its status. */
  readonly code?: ApiErrorResponse['code'];

  constructor(
    readonly status: number,
    message: string,
    readonly headers?: HeadersInit,
    options?: ErrorOptions & { code?: ApiErrorResponse['code'] },
  ) {
    super(message, options);
    this.name = 'HttpError';
    if (options?.code) this.code = options.code;
  }
}

interface ApiRouteOptions {
  authenticated?: boolean;
  serviceRequired?: boolean;
  loadService?: boolean;
  /**
   * Requests per client address for a route without sign-in. Each address of
   * the route has its own allowance unless `bucket` names one to share, as the
   * addresses of one route with an id in them must. `onLimited` counts refusals.
   */
  publicRateLimit?: { limit: number; window: number; bucket?: string; onLimited?: () => void };
  /**
   * The request's address, headers or body carry a capability (a parcel link
   * id, an owner key). Error reports for the route leave the request out.
   */
  capability?: boolean;
  /**
   * The route takes a body in whatever form its sender chose: a mail app posts
   * a form to an email's unsubscribe address, and Sentry's browser SDK an error
   * report as plain text. What guards such a route is the capability in its
   * address, or what its body must hold, not the body's type.
   */
  anyBody?: boolean;
  /**
   * Machines poll the route, a health check or a metrics scrape every few
   * seconds. Its requests are logged only when they fail.
   */
  quiet?: boolean;
}

type ApiHandler<Parameters extends RouteParameters> = (
  context: ApiContext<Parameters>,
) => Response | Promise<Response>;

function ratePolicy(method: string, pathname: string): {
  bucket: string;
  limit: number;
  window: number;
} {
  if (pathname === '/api/sync' || pathname.endsWith('/sync')) {
    return { bucket: 'sync', limit: 12, window: 300 };
  }
  if (method === 'GET' || method === 'HEAD') {
    return { bucket: 'read', limit: 240, window: 60 };
  }
  return { bucket: 'write', limit: 60, window: 60 };
}

export function clientIp(request: Pick<Request, 'headers'>): string {
  if (process.env.TRUST_PROXY_HEADERS !== 'true') return 'untrusted';
  const candidate = request.headers.get('cf-connecting-ip')?.trim()
    || request.headers.get('x-real-ip')?.trim()
    || request.headers.get('x-forwarded-for')?.split(',', 1)[0]?.trim()
    || '';
  return isIP(candidate) ? candidate : 'unknown';
}

/** The eight groups of an IPv6 address, lower case and without leading zeros. */
function ipv6Groups(ip: string): string[] {
  const [head = '', tail = ''] = ip.split('::');
  const leading = head ? head.split(':') : [];
  const trailing = tail ? tail.split(':') : [];
  // "::" stands for the groups left out of the eight.
  const groups = ip.includes('::')
    ? [...leading, ...Array<string>(Math.max(0, 8 - leading.length - trailing.length)).fill('0'), ...trailing]
    : leading;
  return groups.map((group) => group.replace(/^0+(?=.)/, '').toLowerCase());
}

const MAPPED_IPV4 = /^::ffff:(\d+\.\d+\.\d+\.\d+)$/i;

/**
 * The address a limit without sign-in counts. An IPv6 client is its /64: a
 * line is given a whole one, and privacy addresses rotate inside it.
 */
export function clientNetwork(ip: string): string {
  if (isIP(ip) !== 6) return ip;
  const mapped = MAPPED_IPV4.exec(ip);
  if (mapped) return mapped[1]!;
  return `${ipv6Groups(ip).slice(0, 4).join(':')}::/64`;
}

/**
 * The /48 an IPv6 client belongs to, or null for any other address. One
 * customer is often given a whole /48, which holds 65,536 of the /64s that
 * clientNetwork counts apart: a daily allowance counts the /48 as well.
 */
export function clientSite(ip: string): string | null {
  if (isIP(ip) !== 6 || MAPPED_IPV4.test(ip)) return null;
  return `${ipv6Groups(ip).slice(0, 3).join(':')}::/48`;
}

/**
 * Whether a browser says the request comes from a page of another site.
 * Without sign-in a request is counted against its sender's address, so
 * another site's page must not send it from its visitors' browsers. Apps and
 * scripts send neither header and speak for their own address only.
 */
function fromAnotherSite(request: Request): boolean {
  const site = request.headers.get('sec-fetch-site');
  if (site !== null) return site !== 'same-origin' && site !== 'none';
  // Browsers that do not send Sec-Fetch-Site still name the page's origin on a write.
  const origin = request.headers.get('origin');
  if (origin === null) return false;
  try {
    // A proxy that rewrites Host passes the one the browser asked for as X-Forwarded-Host; a page cannot set either.
    const hosts = [request.headers.get('host'), request.headers.get('x-forwarded-host')?.split(',')[0], new URL(request.url).host];
    const from = new URL(origin).host.toLowerCase();
    return !hosts.some((host) => host?.trim().toLowerCase() === from);
  } catch {
    return true;
  }
}

/**
 * Whether the request carries a body it does not declare as JSON. A page can
 * send another site a form or plain text unasked, never JSON.
 */
function bodyIsNotJson(request: Request): boolean {
  const type = request.headers.get('content-type');
  if (type !== null) return !/^application\/json\s*(?:;|$)/i.test(type);
  const length = request.headers.get('content-length');
  return (length !== null && length !== '0') || request.headers.has('transfer-encoding');
}

function bearerToken(request: Request): string | null {
  const authorization = request.headers.get('authorization') ?? '';
  const match = /^Bearer\s+(.+)$/i.exec(authorization);
  return match?.[1]?.trim() || null;
}

function apiResponse(
  status: number,
  payload: unknown,
  headers?: HeadersInit,
): Response {
  return Response.json(payload, {
    status,
    headers: {
      'Cache-Control': 'no-store',
      ...Object.fromEntries(new Headers(headers)),
    },
  });
}

export function json(payload: unknown, status = 200, headers?: HeadersInit): Response {
  return apiResponse(status, payload, headers);
}

export function noContent(status = 204, headers?: HeadersInit): Response {
  return new Response(null, {
    status,
    headers: {
      'Cache-Control': 'no-store',
      ...Object.fromEntries(new Headers(headers)),
    },
  });
}

const expectedFailures = new WeakSet<Response>();

/**
 * Marks a 5xx the route gives by design, such as /health's 503 while the
 * server shuts down: its request line is logged at info, not error.
 */
export function expectedFailure(response: Response): Response {
  expectedFailures.add(response);
  return response;
}

function failure(error: unknown): Response {
  if (error instanceof HttpError) {
    return apiResponse(error.status, { error: error.message, ...(error.code ? { code: error.code } : {}) }, error.headers);
  }
  if (error instanceof SupabaseError) {
    if (error.status === 401 || error.status === 403) {
      return apiResponse(401, { error: 'Authentication is required' });
    }
    // The database found no such row, or none of the caller's. A 404 without that code is
    // a missing function or route, which is an outage rather than an answer.
    if (error.status === 404 && error.code === 'P0002') {
      return apiResponse(404, { error: 'Not found' });
    }
    return apiResponse(502, { error: 'The delivery database is temporarily unavailable' });
  }
  return apiResponse(500, { error: 'The request could not be completed' });
}

/** The route as logs and error tags name it: without ids, and never with a parcel link id, which is a capability. */
function routeLabel(request: Request): string {
  try {
    return new URL(request.url).pathname
      .replace(/\/[0-9a-f]{8}-[0-9a-f-]{27,}/gi, '/:id')
      .replace(/^(\/api\/public\/parcels\/)[^/]+/, '$1:link');
  } catch {
    return 'unknown';
  }
}

function logRequest(
  request: Request,
  requestId: string,
  status: number,
  startedAt: number,
  error?: unknown,
  expected = false,
): void {
  const payload: JsonObject = {
    request_id: requestId,
    method: request.method,
    route: routeLabel(request),
    status,
    duration_ms: Math.round((performance.now() - startedAt) * 10) / 10,
  };
  if (error) payload.error_class = error instanceof Error ? error.name : typeof error;
  // A 502 names the upstream failure behind it: a timeout, an HTTP status, a login.
  if (error instanceof HttpError && error.cause instanceof Error) payload.error_cause = error.cause.name;
  logOperationalEvent('http_request', payload, status >= 500 && !expected ? 'error' : 'info');
}

export function apiRoute<Parameters extends RouteParameters = RouteParameters>(
  handler: ApiHandler<Parameters>,
  options: ApiRouteOptions = {},
): (
  request: NextRequest,
  route: RouteContext<Parameters>,
) => Promise<Response> {
  const authenticated = options.authenticated ?? true;
  return async (request, route) => {
    const requestId = randomUUID().replaceAll('-', '');
    const startedAt = performance.now();
    let response: Response;
    let caught: unknown;
    try {
      const token = bearerToken(request);
      let user: SupabaseUser | null = null;
      let userClient: SupabaseUserClient | null = null;

      if (!authenticated && !['GET', 'HEAD', 'OPTIONS'].includes(request.method)) {
        if (fromAnotherSite(request)) throw new HttpError(403, 'Requests from other sites are not accepted');
        if (!options.anyBody && bodyIsNotJson(request)) throw new HttpError(415, 'Send the request as application/json');
      }

      if (!authenticated && options.publicRateLimit) {
        const { limit, window, bucket, onLimited } = options.publicRateLimit;
        const retryAfter = limiters.public.retryAfter(
          `${bucket ?? new URL(request.url).pathname}:${clientNetwork(clientIp(request))}`,
          { limit, window },
        );
        if (retryAfter) {
          onLimited?.();
          throw new HttpError(429, 'Too many requests. Try again shortly.', { 'Retry-After': String(retryAfter) });
        }
      }

      if (authenticated) {
        const credential = token
          ? createHash('sha256').update(token).digest('hex').slice(0, 24)
          : null;
        const ip = clientIp(request);
        // Unknown callers must not share an admission bucket with every signed-in user. An IPv6
        // client is its /64, so rotating the address inside it does not open another bucket.
        let retryAfter = isIP(ip) ? limiters.address.retryAfter(clientNetwork(ip), {
          limit: PREAUTH_REQUEST_LIMIT * 3,
          window: PREAUTH_REQUEST_WINDOW_SECONDS,
        }) : 0;
        if (!retryAfter && credential) {
          retryAfter = limiters.credential.retryAfter(credential, {
            limit: PREAUTH_REQUEST_LIMIT,
            window: PREAUTH_REQUEST_WINDOW_SECONDS,
          });
        }
        if (retryAfter) {
          throw new HttpError(
            429,
            'Too many authentication attempts. Try again shortly.',
            { 'Retry-After': String(retryAfter) },
          );
        }

        let auth;
        try {
          auth = authenticator();
        } catch (error) {
          throw new HttpError(503, 'Supabase authentication is not configured', undefined, {
            cause: error,
          });
        }
        if (!auth) throw new HttpError(503, 'Supabase authentication is not configured');
        if (!token) throw new HttpError(401, 'Authentication is required');
        try {
          user = await auth.validate(token);
        } catch (error) {
          if (error instanceof SupabaseAuthError) {
            throw new HttpError(401, 'Authentication is required', undefined, { cause: error });
          }
          if (error instanceof SupabaseAuthUnavailableError) {
            throw new HttpError(503, error.message, { 'Retry-After': '5' }, { cause: error });
          }
          throw error;
        }
        const policy = ratePolicy(request.method, new URL(request.url).pathname);
        retryAfter = limiters.account.retryAfter(`${user.id}:${policy.bucket}`, {
          limit: policy.limit,
          window: policy.window,
        });
        if (retryAfter) {
          throw new HttpError(429, 'Too many requests. Try again shortly.', {
            'Retry-After': String(retryAfter),
          });
        }
        userClient = auth.userClient(token);
      }

      let service: SupabaseServiceClient | null = null;
      if (options.loadService ?? true) {
        try {
          service = serviceClient();
        } catch (error) {
          throw new HttpError(503, 'The delivery database is not configured', undefined, {
            cause: error,
          });
        }
      }
      if (options.serviceRequired && !service) {
        throw new HttpError(503, 'The delivery database is not configured');
      }
      response = await handler({
        request,
        route,
        requestId,
        user,
        token,
        userClient,
        service,
      });
    } catch (error) {
      caught = error;
      response = failure(error);
    }

    const headers = new Headers(response.headers);
    headers.set('X-Request-ID', requestId);
    if (!headers.has('Cache-Control')) headers.set('Cache-Control', 'no-store');
    const finalized = new Response(response.body, {
      status: response.status,
      statusText: response.statusText,
      headers,
    });
    if (!options.quiet || finalized.status >= 400) {
      logRequest(request, requestId, finalized.status, startedAt, caught, expectedFailures.has(response));
    }
    if (caught && finalized.status >= 500) {
      captureOperationalError(caught, {
        component: 'api',
        operation: 'request',
        requestId,
        route: routeLabel(request),
        withoutRequest: options.capability,
      });
    }
    return finalized;
  };
}

/**
 * Reads the body once, at most 16 KiB of it: the bytes as sent, which App
 * Attest signs, and the JSON object they hold. A refused body is cancelled
 * without waiting, so a branch of a teed body cannot hold the answer back.
 */
export async function readJsonBody(request: Request): Promise<{ raw: Uint8Array; value: JsonObject }> {
  const declaredLength = request.headers.get('content-length');
  if (declaredLength !== null) {
    const length = Number(declaredLength);
    if (!/^\d+$/.test(declaredLength) || length <= 0 || length > MAX_JSON_BODY) {
      void request.body?.cancel().catch(() => undefined);
      throw new HttpError(400, 'Invalid request size');
    }
  }
  const reader = request.body?.getReader();
  if (!reader) throw new HttpError(400, 'Invalid request size');
  const decoder = new TextDecoder('utf-8', { fatal: true });
  const chunks: Uint8Array[] = [];
  const parts: string[] = [];
  let receivedBytes = 0;
  try {
    while (true) {
      const { done, value } = await reader.read();
      if (done) break;
      receivedBytes += value.byteLength;
      if (receivedBytes > MAX_JSON_BODY) {
        void reader.cancel().catch(() => undefined);
        throw new HttpError(400, 'Invalid request size');
      }
      chunks.push(value);
      parts.push(decoder.decode(value, { stream: true }));
    }
    parts.push(decoder.decode());
  } catch (error) {
    if (error instanceof HttpError) throw error;
    void reader.cancel().catch(() => undefined);
    throw new HttpError(400, 'Send a valid JSON object', undefined, { cause: error });
  } finally {
    reader.releaseLock();
  }
  const text = parts.join('');
  if (!text) {
    throw new HttpError(400, 'Invalid request size');
  }
  let payload: unknown;
  try {
    payload = JSON.parse(text);
  } catch (error) {
    throw new HttpError(400, 'Send a valid JSON object', undefined, { cause: error });
  }
  if (!isRecord(payload)) throw new HttpError(400, 'Send a valid JSON object');
  return { raw: Buffer.concat(chunks, receivedBytes), value: payload };
}

export async function readJsonObject(request: Request): Promise<JsonObject> {
  return (await readJsonBody(request)).value;
}

/** Reads a body of any type, at most `limit` bytes of it; a longer one is refused with 413 and cancelled. */
export async function readBodyBytes(request: Request, limit: number): Promise<Uint8Array> {
  const declaredLength = request.headers.get('content-length');
  if (declaredLength !== null && Number(declaredLength) > limit) {
    void request.body?.cancel().catch(() => undefined);
    throw new HttpError(413, 'The request is too large');
  }
  const reader = request.body?.getReader();
  if (!reader) throw new HttpError(400, 'Invalid request size');
  const chunks: Uint8Array[] = [];
  let receivedBytes = 0;
  try {
    while (true) {
      const { done, value } = await reader.read();
      if (done) break;
      receivedBytes += value.byteLength;
      if (receivedBytes > limit) {
        void reader.cancel().catch(() => undefined);
        throw new HttpError(413, 'The request is too large');
      }
      chunks.push(value);
    }
  } finally {
    reader.releaseLock();
  }
  if (!receivedBytes) throw new HttpError(400, 'Invalid request size');
  return Buffer.concat(chunks, receivedBytes);
}

export function requireUser(context: ApiContext): SupabaseUser {
  if (!context.user) throw new HttpError(401, 'Authentication is required');
  return context.user;
}

export function requireUserClient(context: ApiContext): SupabaseUserClient {
  if (!context.userClient) throw new HttpError(401, 'Authentication is required');
  return context.userClient;
}

export function requireService(context: ApiContext): SupabaseServiceClient {
  if (!context.service) throw new HttpError(503, 'The delivery database is not configured');
  return context.service;
}

/**
 * The caller as Auth sees them now, asked afresh rather than from the cache: a session
 * ended since the token's last check is refused. `refusal` is the 401's message.
 */
export async function validateAfresh(context: ApiContext, refusal: string): Promise<SupabaseUser> {
  const user = requireUser(context);
  const auth = context.token ? authenticator() : null;
  if (!auth || !context.token) throw new HttpError(401, refusal);
  let fresh: SupabaseUser;
  try {
    fresh = await auth.validate(context.token, false);
  } catch (error) {
    if (error instanceof SupabaseAuthError) throw new HttpError(401, refusal, undefined, { cause: error });
    if (error instanceof SupabaseAuthUnavailableError) {
      throw new HttpError(503, error.message, { 'Retry-After': '5' }, { cause: error });
    }
    throw error;
  }
  if (fresh.id !== user.id) throw new HttpError(401, refusal);
  return fresh;
}

/**
 * The caller, asked afresh, when they signed in within the last ten minutes: what deleting
 * the account, a personal token and an assistant's grant need, since each outlasts the
 * session. A copied access token alone can't make them. A session Auth still confirms but
 * signed in earlier gets a 401 with `code: recent_sign_in`: the web keeps it and asks for a
 * sign-in in place, and the apps answer it as any other 401. A session Auth refuses gets a
 * plain 401, which signs out.
 */
export async function requireRecentSignIn(context: ApiContext, refusal: string): Promise<SupabaseUser> {
  const fresh = await validateAfresh(context, refusal);
  const age = fresh.sessionId && fresh.authenticatedAt ? Date.now() - fresh.authenticatedAt.getTime() : null;
  if (age === null || age < -60_000 || age > RECENT_SIGN_IN_MS) {
    throw new HttpError(401, refusal, undefined, { code: 'recent_sign_in' });
  }
  return fresh;
}

export function parseUuid(value: string, label: string): string {
  if (!UUID.test(value)) throw new HttpError(400, `Invalid ${label}`);
  return value.toLowerCase();
}

export function validationError(error: unknown): never {
  if (error instanceof HttpError) throw error;
  throw new HttpError(400, errorMessage(error), undefined, { cause: error });
}
