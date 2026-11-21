import 'server-only';

import { createMcpHandler, type McpHttpHandler } from '@modelcontextprotocol/server';
import { clientIp, clientNetwork, clientSite, readBodyBytes } from '../api';
import { captureOperationalError } from '../observability';
import { RateLimiter } from '../rateLimit';
import { siteName } from '../siteSettings';
import type { AssistantTokenOwner, SupabaseServiceClient } from '../supabase';
import { isRecord } from '../types';
import { assistantsOpenToAccount } from './access';
import { noteConnectedClient } from './clients';
import { noteValidToken, validLately } from './recentTokens';
import { personalTokensOpen, resourceMetadataUrl, SCOPES, secretDigest, type AssistantSettings } from './settings';
import { assistantServer, type AssistantCaller } from './tools';

/** Requests one grant may make a minute. */
const PER_GRANT_PER_MINUTE = 120;
const MOST_MCP_BODY_BYTES = 65_536;

/**
 * Unknown tokens one network (an IPv4 address or IPv6 /64), and one IPv6 /48, may present a minute; past that it waits
 * before any token is looked up, except one issued or found valid lately. Assistants call from a few shared addresses,
 * and each connected account's expired access token counts once an hour, so this stays high: it only bounds the
 * database lookups a stranger can cause.
 */
const UNKNOWN_TOKENS_PER_MINUTE = 600;

const grants = new RateLimiter();
const unknownTokens = new RateLimiter();
const refusedNetworks = new Map<string, number>();
let handler: McpHttpHandler | null = null;

interface McpRequestData {
  service: SupabaseServiceClient;
  caller: AssistantCaller;
  resourceMetadata: string;
}

/**
 * One handler serves every request; each request brings its caller and builds its own server. Nothing is kept between
 * requests: a 2026-07-28 client is answered in JSON, an older one with a single event stream.
 */
function mcpHandler(): McpHttpHandler {
  handler ??= createMcpHandler(({ authInfo }) => {
    const data = authInfo?.extra?.peek as McpRequestData | undefined;
    if (!data) throw new Error('An MCP request reached the tools without its caller');
    return assistantServer(data.service, data.caller, data.resourceMetadata);
  }, {
    responseMode: 'json',
    legacy: 'stateless',
    maxRequestBodySize: MOST_MCP_BODY_BYTES,
    onerror: (error) => captureOperationalError(error, { component: 'assistants', operation: 'mcp' }),
  });
  return handler;
}

function tooMany(retryAfter: number): Response {
  return Response.json({ error: 'Too many requests. Try again shortly.' }, { status: 429, headers: { 'Retry-After': String(retryAfter) } });
}

function unauthorized(settings: AssistantSettings, error?: 'invalid_token'): Response {
  const challenge = `Bearer resource_metadata="${resourceMetadataUrl(settings)}", scope="${SCOPES.join(' ')}"${error ? `, error="${error}"` : ''}`;
  return Response.json(
    { error: error ? 'The token is unknown, expired or revoked' : 'Authentication is required' },
    { status: 401, headers: { 'WWW-Authenticate': challenge } },
  );
}

/**
 * The grant behind a bearer token, or the answer that refuses it: none (401), unknown, expired or revoked (401), too
 * many requests from its grant or its network (429), or an account the setting is not open to (403). `/mcp` and
 * `GET /api/parcels` share it, and so share each grant's allowance.
 */
export async function tokenOwner(
  settings: AssistantSettings,
  service: SupabaseServiceClient,
  request: Request,
  token: string | null,
  now = Date.now(),
): Promise<AssistantTokenOwner | Response> {
  if (!token) return unauthorized(settings);
  const ip = clientIp(request);
  const site = clientSite(ip);
  const networks = site ? [clientNetwork(ip), site] : [clientNetwork(ip)];
  const digest = secretDigest(token);
  const refusedUntil = Math.max(...networks.map((network) => refusedNetworks.get(network) ?? 0));
  if (refusedUntil > now && !validLately(digest, now)) return tooMany(Math.ceil((refusedUntil - now) / 1_000));
  const found = await service.assistantToken(digest);
  // While personal tokens are off, one counts as unknown: it is kept, and works again once they are on.
  const owner = found && (found.kind !== 'personal' || personalTokensOpen()) ? found : null;
  if (!owner) {
    for (const network of networks) {
      const wait = unknownTokens.retryAfter(network, { limit: UNKNOWN_TOKENS_PER_MINUTE, window: 60 });
      if (!wait) continue;
      if (refusedNetworks.size >= 4_096) refusedNetworks.delete(refusedNetworks.keys().next().value!);
      refusedNetworks.set(network, now + wait * 1_000);
    }
    return unauthorized(settings, 'invalid_token');
  }
  noteValidToken(digest, owner.expiresAt ? owner.expiresAt * 1_000 : now + 3_600_000);
  if (owner.clientId) noteConnectedClient(owner.clientId);
  const retryAfter = grants.retryAfter(owner.grantId, { limit: PER_GRANT_PER_MINUTE, window: 60 });
  if (retryAfter) return tooMany(retryAfter);
  if (!(await assistantsOpenToAccount(settings, service, owner.userId, now))) {
    return Response.json({ error: `${siteName()} does not connect assistants to this account` }, { status: 403 });
  }
  return owner;
}

function rpcError(id: unknown, code: number, message: string): Response {
  const echoed = typeof id === 'string' || typeof id === 'number' ? id : null;
  return Response.json({ jsonrpc: '2.0', id: echoed, error: { code, message } }, { status: 400 });
}

/**
 * The request again with its one JSON-RPC message, or the answer that refuses it. A batch would run many tools for one
 * request of the grant's allowance, and `subscriptions/listen` would hold a stream open with nothing to tell: Peek's
 * tools never change while it runs. A body that is not JSON goes on, for the handler to answer as the protocol says.
 */
async function oneMessage(request: Request): Promise<Request | Response> {
  const body = Buffer.from(await readBodyBytes(request, MOST_MCP_BODY_BYTES));
  let message: unknown = null;
  try {
    message = JSON.parse(body.toString('utf8'));
  } catch { /* Not JSON. */ }
  if (Array.isArray(message) && message.length > 1) return rpcError(null, -32600, 'Send one message a request');
  const only: unknown = Array.isArray(message) ? message[0] : message;
  if (isRecord(only) && only.method === 'subscriptions/listen') return rpcError(only.id, -32601, `${siteName()} sends no notifications`);
  return new Request(request.url, { method: request.method, headers: request.headers, body, signal: request.signal });
}

/**
 * Serves `POST /mcp`: the bearer token must be an access token of a live grant, or a personal token while those are on,
 * whose account the setting is open to. Each request carries one message; the tools then work on that account alone.
 */
export async function serveMcp(
  settings: AssistantSettings,
  service: SupabaseServiceClient,
  request: Request,
  token: string | null,
  now = Date.now(),
): Promise<Response> {
  if (!token) return unauthorized(settings);
  const owner = await tokenOwner(settings, service, request, token, now);
  if (owner instanceof Response) return owner;
  const single = await oneMessage(request);
  if (single instanceof Response) return single;
  const peek: McpRequestData = { service, caller: { userId: owner.userId, grantId: owner.grantId, scopes: owner.scopes }, resourceMetadata: resourceMetadataUrl(settings) };
  return mcpHandler().fetch(single, {
    authInfo: {
      token,
      clientId: owner.clientId ?? 'personal',
      scopes: owner.scopes,
      ...(owner.expiresAt ? { expiresAt: owner.expiresAt } : {}),
      resource: new URL(settings.resource),
      extra: { peek },
    },
  });
}
