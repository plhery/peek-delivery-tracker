import { describe, expect, it, vi } from 'vitest';
import { HttpError } from '../api';
import { SupabaseError, type SupabaseServiceClient } from '../supabase';
import { readAuthorizationRequest } from './authorize';
import type { AssistantClient } from './clients';
import { answerConsent, consentDetails } from './consent';
import { assistantSettings, newClientId, pkceChallenge, secretDigest } from './settings';

// Synthetic ids only.
const userId = '5b000000-0000-4000-a000-0000000000a1';
const settings = assistantSettings({ ASSISTANT_ACCOUNTS: '*', CANONICAL_ORIGIN: 'https://peek.example' } as unknown as NodeJS.ProcessEnv)!;
const challenge = pkceChallenge('v'.repeat(64));
const google = 'https://oauth-redirect.googleusercontent.com/r/example-project';
const client: AssistantClient = {
  id: newClientId(),
  name: 'Example Assistant',
  uri: 'https://assistant.example.com',
  redirectUris: ['https://assistant.example.com/oauth/callback', google, 'http://127.0.0.1/callback', 'com.example.assistant:/oauth'],
};

function service() {
  const calls = {
    assistantClient: vi.fn(async (id: string) => (id === client.id ? client : null)),
    openAssistantCode: vi.fn<SupabaseServiceClient['openAssistantCode']>(async () => undefined),
  };
  return { ...calls, client: calls as unknown as SupabaseServiceClient };
}

function query(values: Record<string, string> = {}): string {
  return new URLSearchParams({
    response_type: 'code', client_id: client.id, redirect_uri: client.redirectUris[0]!, code_challenge: challenge,
    code_challenge_method: 'S256', state: 'state-123', scope: 'parcels:read parcels:write', ...values,
  }).toString();
}

describe('the owner\'s answer', () => {
  it('sends a denial back as access_denied, with the state and the issuer, and opens nothing', async () => {
    const db = service();
    const url = new URL(await answerConsent(settings, db.client, userId, query(), false, null));
    expect(`${url.origin}${url.pathname}`).toBe('https://assistant.example.com/oauth/callback');
    expect(Object.fromEntries(url.searchParams)).toEqual({ error: 'access_denied', state: 'state-123', iss: 'https://peek.example' });
    expect(db.openAssistantCode).not.toHaveBeenCalled();
  });

  it('opens a grant with the scopes the owner kept, and sends a code back with the state and the issuer', async () => {
    const db = service();
    const url = new URL(await answerConsent(settings, db.client, userId, query(), true, ['parcels:read']));
    const code = url.searchParams.get('code')!;
    expect(code).toMatch(/^peek_ac_[A-Za-z0-9_-]{43}$/);
    expect(url.searchParams.get('state')).toBe('state-123');
    expect(url.searchParams.get('iss')).toBe('https://peek.example');
    expect(db.openAssistantCode).toHaveBeenCalledExactlyOnceWith({
      userId,
      clientId: client.id,
      name: 'Example Assistant',
      redirectHost: 'assistant.example.com',
      rotate: true,
      scopes: ['parcels:read'],
      codeDigest: secretDigest(code),
      redirectUri: 'https://assistant.example.com/oauth/callback',
      challenge,
    });
    expect(JSON.stringify(db.openAssistantCode.mock.calls)).not.toContain(code);
  });

  it('never grants more than the client asked for', async () => {
    const db = service();
    await answerConsent(settings, db.client, userId, query({ scope: 'parcels:read' }), true, ['parcels:read', 'parcels:write']);
    expect(db.openAssistantCode.mock.calls[0]![0].scopes).toEqual(['parcels:read']);
    await answerConsent(settings, db.client, userId, query(), true, null);
    expect(db.openAssistantCode.mock.calls[1]![0].scopes).toEqual(['parcels:read', 'parcels:write']);
  });

  it('keeps the refresh token for Google\'s redirect, and rotates it for every other', async () => {
    const db = service();
    await answerConsent(settings, db.client, userId, query({ redirect_uri: google }), true, null);
    expect(db.openAssistantCode).toHaveBeenLastCalledWith(expect.objectContaining({ rotate: false, redirectHost: 'oauth-redirect.googleusercontent.com' }));
    await answerConsent(settings, db.client, userId, query({ redirect_uri: 'http://127.0.0.1:50123/callback' }), true, null);
    expect(db.openAssistantCode).toHaveBeenLastCalledWith(expect.objectContaining({
      rotate: true, redirectHost: 'localhost', redirectUri: 'http://127.0.0.1:50123/callback',
    }));
  });

  it('refuses an approval that keeps nothing, and a full account with 409', async () => {
    const db = service();
    await expect(answerConsent(settings, db.client, userId, query({ scope: 'parcels:read' }), true, ['parcels:write']))
      .rejects.toMatchObject({ status: 400 });
    await expect(answerConsent(settings, db.client, userId, query(), true, [])).rejects.toBeInstanceOf(HttpError);
    expect(db.openAssistantCode).not.toHaveBeenCalled();
    db.openAssistantCode.mockRejectedValue(new SupabaseError('too many grants', 400, 'P0001'));
    await expect(answerConsent(settings, db.client, userId, query(), true, null)).rejects.toMatchObject({ status: 409 });
    db.openAssistantCode.mockRejectedValue(new SupabaseError('down', 503));
    await expect(answerConsent(settings, db.client, userId, query(), true, null)).rejects.toBeInstanceOf(SupabaseError);
  });

  it('refuses an unknown client or redirect with 400, and sends other problems back to the client', async () => {
    const db = service();
    await expect(answerConsent(settings, db.client, userId, query({ client_id: newClientId() }), true, null)).rejects.toMatchObject({ status: 400, message: 'Unknown client' });
    await expect(answerConsent(settings, db.client, userId, query({ redirect_uri: 'https://attacker.example.net/callback' }), true, null))
      .rejects.toMatchObject({ status: 400, message: 'Unknown redirect address' });
    const url = new URL(await answerConsent(settings, db.client, userId, query({ code_challenge_method: 'plain' }), true, null));
    expect(url.searchParams.get('error')).toBe('invalid_request');
    expect(url.searchParams.get('code')).toBeNull();
    expect(db.openAssistantCode).not.toHaveBeenCalled();
  });
});

describe('what the consent page shows', () => {
  it('names the client and where the answer goes', async () => {
    const db = service();
    const details = async (redirect: string) => {
      const read = await readAuthorizationRequest(settings, db.client, new URLSearchParams(query({ redirect_uri: redirect, scope: 'parcels:read' })));
      if (!('request' in read)) throw new Error('Expected a request');
      return consentDetails(read.request);
    };
    expect(await details('https://assistant.example.com/oauth/callback')).toEqual({
      clientName: 'Example Assistant', clientHost: null, clientUri: 'https://assistant.example.com', redirectHost: 'assistant.example.com',
      local: false, scopes: ['parcels:read'],
    });
    expect(await details('http://127.0.0.1:50123/callback')).toMatchObject({ redirectHost: 'localhost', local: true });
    expect(await details('com.example.assistant:/oauth')).toMatchObject({ redirectHost: 'com.example.assistant://', local: true });
  });

  it('says where the name comes from for a client that publishes its details', () => {
    const published = { ...client, id: 'https://assistant.example.com/oauth/client.json' };
    const details = consentDetails({ client: published, redirectUri: client.redirectUris[0]!, state: null, challenge, scopes: ['parcels:read'] });
    expect(details).toMatchObject({ clientName: 'Example Assistant', clientHost: 'assistant.example.com', local: false });
  });
});
