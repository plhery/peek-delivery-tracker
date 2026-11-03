import 'server-only';

import { HttpError } from '../api';
import { SupabaseError, type SupabaseServiceClient } from '../supabase';
import { readAuthorizationRequest, redirectWith, type AuthorizationRequest } from './authorize';
import { isMetadataDocumentId, keepsRefreshToken, redirectHost } from './clients';
import { newSecret, secretDigest, type AssistantSettings, type Scope } from './settings';

/** What the consent page shows about a request: who asks, where its answer goes, and for what. */
export interface ConsentDetails {
  clientName: string;
  /** Where the name comes from, for a client that publishes its details; null for one that registered them. */
  clientHost: string | null;
  clientUri: string | null;
  redirectHost: string;
  /** The answer goes back to an app on this device, not to a website. */
  local: boolean;
  scopes: Scope[];
}

export function consentDetails(request: AuthorizationRequest): ConsentDetails {
  const host = redirectHost(request.redirectUri);
  return {
    clientName: request.client.name,
    clientHost: isMetadataDocumentId(request.client.id) ? new URL(request.client.id).hostname : null,
    clientUri: request.client.uri,
    redirectHost: host,
    local: new URL(request.redirectUri).protocol !== 'https:',
    scopes: request.scopes,
  };
}

/**
 * The owner's answer to an authorization request, given as the page's query string: where the browser goes next. An
 * approval opens a grant with the scopes the owner kept of those asked, and a code for the client.
 */
export async function answerConsent(
  settings: AssistantSettings,
  service: SupabaseServiceClient,
  userId: string,
  query: string,
  approved: boolean,
  kept: readonly Scope[] | null,
): Promise<string> {
  const read = await readAuthorizationRequest(settings, service, new URLSearchParams(query));
  if ('problem' in read) {
    if (read.problem.kind === 'returned') return read.problem.redirect;
    throw new HttpError(400, read.problem.reason === 'client' ? 'Unknown client' : 'Unknown redirect address');
  }
  const { request } = read;
  if (!approved) return redirectWith(settings, request.redirectUri, { error: 'access_denied', state: request.state });
  const scopes = kept ? request.scopes.filter((scope) => kept.includes(scope)) : request.scopes;
  if (!scopes.length) throw new HttpError(400, 'Allow at least one thing');
  const code = newSecret('code');
  try {
    await service.openAssistantCode({
      userId,
      clientId: request.client.id,
      name: request.client.name,
      redirectHost: redirectHost(request.redirectUri),
      rotate: !keepsRefreshToken(request.redirectUri),
      scopes,
      codeDigest: secretDigest(code),
      redirectUri: request.redirectUri,
      challenge: request.challenge,
    });
  } catch (error) {
    if (error instanceof SupabaseError && error.code === 'P0001') {
      throw new HttpError(409, 'This account has too many connected assistants. Disconnect one in Settings first.', undefined, { cause: error });
    }
    throw error;
  }
  return redirectWith(settings, request.redirectUri, { code, state: request.state });
}
