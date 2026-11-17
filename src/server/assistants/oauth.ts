import 'server-only';

import { readBodyBytes } from '../api';
import type { SupabaseServiceClient } from '../supabase';
import type { JsonObject } from '../types';
import { assistantsOpenToAccount } from './access';
import { clientName, clientUri, MOST_REDIRECT_URIS, redirectHost, redirectUriProblem } from './clients';
import { noteValidToken } from './recentTokens';
import {
  ACCESS_TOKEN_SECONDS,
  newClientId,
  newSecret,
  pkceChallenge,
  PKCE_VERIFIER,
  REFRESH_TOKEN_DAYS,
  SCOPES,
  secretDigest,
  type AssistantSettings,
  type Scope,
} from './settings';

const NO_STORE = { 'Cache-Control': 'no-store', Pragma: 'no-cache' };
const MOST_FORM_BYTES = 8_192;

/** An OAuth error answer (RFC 6749 §5.2). */
export function oauthError(error: string, description: string, status = 400): Response {
  return Response.json({ error, error_description: description }, { status, headers: NO_STORE });
}

/** What `/.well-known/oauth-protected-resource/mcp` says (RFC 9728). */
export function protectedResourceMetadata(settings: AssistantSettings) {
  return {
    resource: settings.resource,
    authorization_servers: [settings.issuer],
    scopes_supported: [...SCOPES],
    bearer_methods_supported: ['header'],
    resource_name: 'Peek',
  };
}

/** What `/.well-known/oauth-authorization-server` says (RFC 8414). */
export function authorizationServerMetadata(settings: AssistantSettings) {
  const { issuer } = settings;
  return {
    issuer,
    authorization_endpoint: `${issuer}/oauth/authorize`,
    token_endpoint: `${issuer}/oauth/token`,
    registration_endpoint: `${issuer}/oauth/register`,
    revocation_endpoint: `${issuer}/oauth/revoke`,
    scopes_supported: [...SCOPES],
    response_types_supported: ['code'],
    response_modes_supported: ['query'],
    grant_types_supported: ['authorization_code', 'refresh_token'],
    token_endpoint_auth_methods_supported: ['none'],
    revocation_endpoint_auth_methods_supported: ['none'],
    code_challenge_methods_supported: ['S256'],
    client_id_metadata_document_supported: true,
    authorization_response_iss_parameter_supported: true,
  };
}

/** Where Peek serves the Client ID Metadata Document of its Home Assistant integration. */
export function homeAssistantClientId(settings: Pick<AssistantSettings, 'issuer'>): string {
  return `${settings.issuer}/oauth/clients/home-assistant.json`;
}

/**
 * The Home Assistant integration's metadata document. Every Home Assistant answers at the same redirect, its own
 * `my.home-assistant.io`, so Peek publishes the document the integration names as its client id, and reads it back from
 * its public address as any client's.
 */
export function homeAssistantClientDocument(settings: Pick<AssistantSettings, 'issuer'>) {
  return {
    client_id: homeAssistantClientId(settings),
    client_name: 'Home Assistant',
    client_uri: `${settings.issuer}/assistants`,
    redirect_uris: ['https://my.home-assistant.io/redirect/oauth'],
    grant_types: ['authorization_code', 'refresh_token'],
    response_types: ['code'],
    token_endpoint_auth_method: 'none',
    scope: SCOPES.join(' '),
  };
}

export class OAuthRequestError extends Error {
  constructor(readonly error: string, message: string) {
    super(message);
  }
}

/** Reads a form body (`application/x-www-form-urlencoded`), each parameter at most once. */
export async function readForm(request: Request): Promise<URLSearchParams> {
  if (!/^application\/x-www-form-urlencoded\s*(?:;|$)/i.test(request.headers.get('content-type') ?? '')) {
    throw new OAuthRequestError('invalid_request', 'Send the request as application/x-www-form-urlencoded');
  }
  const form = new URLSearchParams(Buffer.from(await readBodyBytes(request, MOST_FORM_BYTES)).toString('utf8'));
  const repeated = [...new Set(form.keys())].find((name) => form.getAll(name).length > 1);
  if (repeated) throw new OAuthRequestError('invalid_request', `${repeated} is given more than once`);
  return form;
}

function tokenAnswer(access: string, expires: Date, refresh: string | null, scopes: Scope[]): Response {
  noteValidToken(secretDigest(access), expires.getTime());
  return Response.json({
    access_token: access,
    token_type: 'Bearer',
    expires_in: ACCESS_TOKEN_SECONDS,
    ...(refresh ? { refresh_token: refresh } : {}),
    scope: scopes.join(' '),
  }, { headers: NO_STORE });
}

const CLOSED_ACCOUNT = 'Peek no longer connects assistants to this account';

/**
 * The token endpoint: a code (with its PKCE verifier) or a refresh token, for a public client. An account the setting
 * is no longer open to gets no token, so its grants lapse rather than renew.
 */
export async function exchangeToken(
  settings: AssistantSettings,
  service: SupabaseServiceClient,
  form: URLSearchParams,
  now = Date.now(),
): Promise<Response> {
  const clientId = form.get('client_id');
  if (!clientId) return oauthError('invalid_request', 'client_id is required');
  const resource = form.get('resource');
  if (resource !== null && resource !== settings.resource && resource !== `${settings.resource}/`) {
    return oauthError('invalid_target', `Tokens are only issued for ${settings.resource}`);
  }
  const access = newSecret('access');
  const refresh = newSecret('refresh');
  const accessExpires = new Date(now + ACCESS_TOKEN_SECONDS * 1_000);
  const refreshExpires = new Date(now + REFRESH_TOKEN_DAYS * 86_400_000);
  switch (form.get('grant_type')) {
    case 'authorization_code': {
      const code = form.get('code');
      const redirectUri = form.get('redirect_uri');
      const verifier = form.get('code_verifier') ?? '';
      if (!code || !redirectUri) return oauthError('invalid_request', 'code and redirect_uri are required');
      if (!PKCE_VERIFIER.test(verifier)) return oauthError('invalid_request', 'A PKCE code_verifier is required');
      const issued = await service.redeemAssistantCode({
        codeDigest: secretDigest(code), clientId, redirectUri, challenge: pkceChallenge(verifier),
        accessDigest: secretDigest(access), accessExpires, refreshDigest: secretDigest(refresh), refreshExpires,
      });
      if (!issued) return oauthError('invalid_grant', 'The code is unknown, expired, already used, or not this client\'s');
      if (!(await assistantsOpenToAccount(settings, service, issued.userId, now))) return oauthError('invalid_grant', CLOSED_ACCOUNT);
      return tokenAnswer(access, accessExpires, refresh, issued.scopes);
    }
    case 'refresh_token': {
      const presented = form.get('refresh_token');
      if (!presented) return oauthError('invalid_request', 'refresh_token is required');
      const issued = await service.refreshAssistantTokens({
        refreshDigest: secretDigest(presented), clientId, accessDigest: secretDigest(access), accessExpires,
        newRefreshDigest: secretDigest(refresh), refreshExpires,
      });
      if (!issued) return oauthError('invalid_grant', 'The refresh token is unknown, expired or revoked');
      if (!(await assistantsOpenToAccount(settings, service, issued.userId, now))) return oauthError('invalid_grant', CLOSED_ACCOUNT);
      return tokenAnswer(access, accessExpires, issued.rotated ? refresh : null, issued.scopes);
    }
    default:
      return oauthError('unsupported_grant_type', 'Use authorization_code or refresh_token');
  }
}

/** Dynamic client registration (RFC 7591), for public clients: every client is given `none` as its auth method. */
export async function registerClient(service: SupabaseServiceClient, body: JsonObject, now = Date.now()): Promise<Response> {
  const uris = body.redirect_uris;
  if (!Array.isArray(uris) || !uris.length || uris.length > MOST_REDIRECT_URIS) {
    return oauthError('invalid_redirect_uri', `Give between 1 and ${MOST_REDIRECT_URIS} redirect_uris`);
  }
  for (const uri of uris) {
    const problem = typeof uri === 'string' ? redirectUriProblem(uri) : 'is not text';
    if (problem) return oauthError('invalid_redirect_uri', `A redirect URI ${problem}`);
  }
  const redirectUris = uris as string[];
  const client = {
    id: newClientId(),
    name: clientName(body.client_name) ?? redirectHost(redirectUris[0]!),
    uri: clientUri(body.client_uri),
    redirectUris,
  };
  await service.registerAssistantClient(client);
  return Response.json({
    client_id: client.id,
    client_id_issued_at: Math.floor(now / 1_000),
    client_name: client.name,
    ...(client.uri ? { client_uri: client.uri } : {}),
    redirect_uris: client.redirectUris,
    grant_types: ['authorization_code', 'refresh_token'],
    response_types: ['code'],
    token_endpoint_auth_method: 'none',
  }, { status: 201, headers: NO_STORE });
}

/** Token revocation (RFC 7009): always 200, whether the token was known or not. */
export async function revokeToken(service: SupabaseServiceClient, form: URLSearchParams): Promise<Response> {
  const token = form.get('token');
  if (!token) return oauthError('invalid_request', 'token is required');
  await service.revokeAssistantToken(secretDigest(token), form.get('client_id'));
  return new Response(null, { status: 200, headers: NO_STORE });
}
