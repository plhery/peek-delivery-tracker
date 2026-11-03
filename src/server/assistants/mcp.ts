import 'server-only';

import { createMcpHandler, type McpHttpHandler } from '@modelcontextprotocol/server';
import { clientIp, clientNetwork } from '../api';
import { captureOperationalError } from '../observability';
import { RateLimiter } from '../rateLimit';
import type { SupabaseServiceClient } from '../supabase';
import { assistantsOpenToAccount } from './access';
import { SCOPES, secretDigest, type AssistantSettings } from './settings';
import { assistantServer, type AssistantCaller } from './tools';

/** Requests one grant may make a minute. */
const PER_GRANT_PER_MINUTE = 120;
const MOST_MCP_BODY_BYTES = 65_536;

/**
 * Unknown tokens one network may present a minute; past that it waits before any token is looked up. Assistants call
 * from a few shared addresses, and each connected account's expired access token counts once an hour, so this stays
 * high: it only bounds the database lookups a stranger can cause.
 */
const UNKNOWN_TOKENS_PER_MINUTE = 600;

const grants = new RateLimiter();
const unknownTokens = new RateLimiter();
const refusedNetworks = new Map<string, number>();
let handler: McpHttpHandler | null = null;

interface McpRequestData {
  service: SupabaseServiceClient;
  caller: AssistantCaller;
}

/**
 * One handler serves every request; each request brings its caller and builds its own server. Nothing is kept between
 * requests: a 2026-07-28 client is answered in JSON, an older one with a single event stream.
 */
function mcpHandler(): McpHttpHandler {
  handler ??= createMcpHandler(({ authInfo }) => {
    const data = authInfo?.extra?.peek as McpRequestData | undefined;
    if (!data) throw new Error('An MCP request reached the tools without its caller');
    return assistantServer(data.service, data.caller);
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
  const metadata = `${settings.issuer}/.well-known/oauth-protected-resource/mcp`;
  const challenge = `Bearer resource_metadata="${metadata}", scope="${SCOPES.join(' ')}"${error ? `, error="${error}"` : ''}`;
  return Response.json(
    { error: error ? 'The token is unknown, expired or revoked' : 'Authentication is required' },
    { status: 401, headers: { 'WWW-Authenticate': challenge } },
  );
}

/**
 * Serves `POST /mcp`: the bearer token must be an access or personal token of a live grant, whose account the
 * setting is open to. The tools then work on that account alone.
 */
export async function serveMcp(
  settings: AssistantSettings,
  service: SupabaseServiceClient,
  request: Request,
  token: string | null,
  now = Date.now(),
): Promise<Response> {
  if (!token) return unauthorized(settings);
  const network = clientNetwork(clientIp(request));
  const refusedUntil = refusedNetworks.get(network) ?? 0;
  if (refusedUntil > now) return tooMany(Math.ceil((refusedUntil - now) / 1_000));
  const owner = await service.assistantToken(secretDigest(token));
  if (!owner) {
    const wait = unknownTokens.retryAfter(network, { limit: UNKNOWN_TOKENS_PER_MINUTE, window: 60 });
    if (wait) {
      if (refusedNetworks.size >= 4_096) refusedNetworks.delete(refusedNetworks.keys().next().value!);
      refusedNetworks.set(network, now + wait * 1_000);
    }
    return unauthorized(settings, 'invalid_token');
  }
  const retryAfter = grants.retryAfter(owner.grantId, { limit: PER_GRANT_PER_MINUTE, window: 60 });
  if (retryAfter) return tooMany(retryAfter);
  if (!(await assistantsOpenToAccount(settings, service, owner.userId, now))) {
    return Response.json({ error: 'Peek does not connect assistants to this account' }, { status: 403 });
  }
  const peek: McpRequestData = { service, caller: { userId: owner.userId, scopes: owner.scopes } };
  return mcpHandler().fetch(request, {
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
