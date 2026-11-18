import 'server-only';

import type { ErrorReportsConfig } from '../lib/errorReportsConfig';
import { isIP } from 'node:net';
import { clientIp, HttpError, noContent } from './api';
import { errorType, logOperationalEvent, resolveSentryRelease } from './observability';
import { RateLimiter } from './rateLimit';

/** What one report may weigh. An error with its stack and causes is a few kilobytes. */
const MAX_ENVELOPE_BYTES = 65_536;
/** Every browser together: a release that breaks every page still sends Sentry a bounded stream. */
const OVERALL_LIMIT = { limit: 60, window: 600 };
const overall = new RateLimiter(1);

interface SentryProject {
  /** The DSN without the secret part an old one may carry: what a browser may be told. */
  dsn: string;
  /** Where the project takes envelopes. */
  envelope: string;
}

/** The project a DSN names, or null for anything that is not one. */
export function sentryProject(value: unknown): SentryProject | null {
  if (typeof value !== 'string') return null;
  try {
    const url = new URL(value.trim());
    const match = /^(.*)\/(\d+)$/.exec(url.pathname);
    if (!['https:', 'http:'].includes(url.protocol) || !url.username || !match || url.search || url.hash) return null;
    const base = `${url.protocol}//${url.host}${match[1]}`;
    return {
      dsn: `${url.protocol}//${url.username}@${url.host}${match[1]}/${match[2]}`,
      envelope: `${base}/api/${match[2]}/envelope/`,
    };
  } catch {
    return null;
  }
}

function project(env: NodeJS.ProcessEnv): SentryProject | null {
  return env.NODE_ENV === 'production' ? sentryProject(env.SENTRY_DSN) : null;
}

/**
 * What a page tells its scripts so they can report their errors: the server's
 * project, release and environment. Null outside production and without a
 * DSN, where browsers report nothing.
 */
export function browserErrorReporting(env: NodeJS.ProcessEnv = process.env): ErrorReportsConfig | null {
  const target = project(env);
  if (!target) return null;
  const release = resolveSentryRelease(env);
  return { dsn: target.dsn, ...(release ? { release } : {}), environment: env.SENTRY_ENVIRONMENT?.trim() || 'production' };
}

async function readEnvelope(request: Request): Promise<Buffer> {
  const declared = request.headers.get('content-length');
  if (declared !== null && Number(declared) > MAX_ENVELOPE_BYTES) {
    await request.body?.cancel().catch(() => undefined);
    throw new HttpError(413, 'The error report is too large');
  }
  const reader = request.body?.getReader();
  const chunks: Uint8Array[] = [];
  let size = 0;
  while (reader) {
    const { done, value } = await reader.read();
    if (done) break;
    size += value.byteLength;
    if (size > MAX_ENVELOPE_BYTES) {
      await reader.cancel().catch(() => undefined);
      throw new HttpError(413, 'The error report is too large');
    }
    chunks.push(value);
  }
  if (!size) throw new HttpError(400, 'Send an error report');
  return Buffer.concat(chunks);
}

/** The DSN an envelope names in its first line, its header. */
function envelopeDsn(envelope: Buffer): unknown {
  const end = envelope.indexOf(10);
  try {
    const header: unknown = JSON.parse(envelope.subarray(0, end === -1 ? envelope.length : end).toString('utf8'));
    return header && typeof header === 'object' ? (header as { dsn?: unknown }).dsn : undefined;
  } catch {
    return undefined;
  }
}

/**
 * The envelope with the browser's address as the user's of each event that names none: Sentry sees
 * the site's address only. An envelope with an item that does not parse goes on as it came, as does
 * one whose item claims a length that is no whole number of bytes it holds: each item read moves
 * past its header, so a length that points back cannot hold the server in this loop.
 */
export function withClientAddress(envelope: Buffer, ip: string): Buffer {
  if (!isIP(ip)) return envelope;
  const parts: Buffer[] = [];
  let offset = envelope.indexOf(10);
  if (offset < 0) return envelope;
  parts.push(envelope.subarray(0, offset + 1));
  offset += 1;
  while (offset < envelope.length) {
    const headerEnd = envelope.indexOf(10, offset);
    if (headerEnd < 0) { parts.push(envelope.subarray(offset)); break; }
    let header: { type?: unknown; length?: unknown };
    try { header = JSON.parse(envelope.subarray(offset, headerEnd).toString('utf8')) as typeof header; } catch { return envelope; }
    const start = headerEnd + 1;
    if (header.length !== undefined && !(Number.isSafeInteger(header.length) && (header.length as number) >= 0
      && start + (header.length as number) <= envelope.length)) return envelope;
    const end = typeof header.length === 'number' ? start + header.length : (() => {
      const newline = envelope.indexOf(10, start);
      return newline < 0 ? envelope.length : newline;
    })();
    let payload = envelope.subarray(start, end);
    if (header.type === 'event') {
      try {
        const event = JSON.parse(payload.toString('utf8')) as { user?: Record<string, unknown> };
        if (!event.user?.ip_address || event.user.ip_address === '{{auto}}') {
          event.user = { ...event.user, ip_address: ip };
          payload = Buffer.from(JSON.stringify(event), 'utf8');
          if (typeof header.length === 'number') header = { ...header, length: payload.length };
        }
      } catch { /* sent as it came */ }
    }
    parts.push(Buffer.from(`${JSON.stringify(header)}\n`, 'utf8'), payload, Buffer.from('\n', 'utf8'));
    offset = end + 1;
  }
  return Buffer.concat(parts);
}

/**
 * Passes a browser's error report on to the server's own Sentry project, and
 * nowhere else, with the browser's address as its user's. Its cookies and headers
 * stay here. Sentry's answer to an envelope it limits goes back, so the browser's
 * SDK waits.
 */
export async function forwardBrowserErrors(
  request: Request,
  { env = process.env, send = fetch, limiter = overall }: {
    env?: NodeJS.ProcessEnv; send?: typeof fetch; limiter?: RateLimiter;
  } = {},
): Promise<Response> {
  const target = project(env);
  if (!target) throw new HttpError(404, 'Error reports are off');
  const envelope = await readEnvelope(request);
  if (sentryProject(envelopeDsn(envelope))?.dsn !== target.dsn) {
    throw new HttpError(400, 'Not an error report for this site');
  }
  const retryAfter = limiter.retryAfter('all', OVERALL_LIMIT);
  if (retryAfter) {
    throw new HttpError(429, 'Too many error reports. Try again later.', { 'Retry-After': String(retryAfter) });
  }
  let response: Response;
  try {
    response = await send(target.envelope, {
      method: 'POST',
      headers: { 'Content-Type': 'application/x-sentry-envelope' },
      body: new Uint8Array(withClientAddress(envelope, clientIp(request))),
      signal: AbortSignal.timeout(5_000),
    });
  } catch (error) {
    logOperationalEvent('browser_errors_forward_failed', { error_type: errorType(error) }, 'warning');
    return noContent(502);
  }
  await response.body?.cancel().catch(() => undefined);
  if (response.ok) return noContent();
  logOperationalEvent('browser_errors_refused', { upstream_status: response.status }, 'warning');
  const limits = Object.fromEntries(['Retry-After', 'X-Sentry-Rate-Limits']
    .flatMap((name) => response.headers.get(name) ? [[name, response.headers.get(name)!]] : []));
  return noContent(response.status === 429 ? 429 : response.status < 500 ? 400 : 502, limits);
}
