import 'server-only';

import type { SupabaseServiceClient } from '../supabase';
import { findClient, redirectMatches, type AssistantClient } from './clients';
import { knownScopes, PKCE_CHALLENGE, SCOPES, type AssistantSettings, type Scope } from './settings';

/** An authorization request Peek can ask the owner about. */
export interface AuthorizationRequest {
  client: AssistantClient;
  redirectUri: string;
  state: string | null;
  challenge: string;
  /** What the client asked for; all of Peek's scopes when it named none Peek knows. */
  scopes: Scope[];
}

export type AuthorizationProblem =
  /** The client or its redirect address is unknown: the owner is told, and nothing goes back to the client. */
  | { kind: 'shown'; reason: 'client' | 'redirect' }
  /** Anything else goes back to the client's redirect address. */
  | { kind: 'returned'; redirect: string };

const MOST_STATE_LENGTH = 2_000;

/** The client's redirect address with the answer's parameters, and the issuer, which clients check (RFC 9207). */
export function redirectWith(settings: AssistantSettings, redirectUri: string, values: Record<string, string | null>): string {
  const url = new URL(redirectUri);
  for (const [name, value] of Object.entries(values)) if (value !== null) url.searchParams.set(name, value);
  url.searchParams.set('iss', settings.issuer);
  return url.href;
}

function sameResource(settings: AssistantSettings, value: string): boolean {
  return value === settings.resource || value === `${settings.resource}/`;
}

/**
 * Reads an authorization request (OAuth 2.1 with PKCE S256), as the consent page and its answer both do. Lenient
 * where clients differ: scopes Peek doesn't know are left out, and a missing `resource` means `/mcp`.
 */
export async function readAuthorizationRequest(
  settings: AssistantSettings,
  service: SupabaseServiceClient,
  parameters: URLSearchParams,
): Promise<{ request: AuthorizationRequest } | { problem: AuthorizationProblem }> {
  const clientId = parameters.get('client_id') ?? '';
  const client = clientId ? await findClient(service, clientId) : null;
  if (!client) return { problem: { kind: 'shown', reason: 'client' } };
  const presented = parameters.get('redirect_uri');
  const redirectUri = presented === null
    ? (client.redirectUris.length === 1 ? client.redirectUris[0]! : null)
    : (client.redirectUris.some((registered) => redirectMatches(registered, presented)) ? presented : null);
  if (!redirectUri) return { problem: { kind: 'shown', reason: 'redirect' } };

  const state = parameters.get('state');
  const back = (error: string, description: string) => ({
    problem: {
      kind: 'returned' as const,
      redirect: redirectWith(settings, redirectUri, {
        error,
        error_description: description,
        state: state && state.length <= MOST_STATE_LENGTH ? state : null,
      }),
    },
  });
  if (state !== null && state.length > MOST_STATE_LENGTH) return back('invalid_request', 'state is too long');
  if (parameters.get('response_type') !== 'code') return back('unsupported_response_type', 'Only the code flow is supported');
  const challenge = parameters.get('code_challenge') ?? '';
  if (parameters.get('code_challenge_method') !== 'S256' || !PKCE_CHALLENGE.test(challenge)) {
    return back('invalid_request', 'PKCE with S256 is required');
  }
  const resource = parameters.get('resource');
  if (resource !== null && !sameResource(settings, resource)) return back('invalid_target', `Tokens are only issued for ${settings.resource}`);
  const named = knownScopes(parameters.get('scope'));
  return { request: { client, redirectUri, state, challenge, scopes: named.length ? named : [...SCOPES] } };
}
