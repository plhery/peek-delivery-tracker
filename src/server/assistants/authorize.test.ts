import { describe, expect, it, vi } from 'vitest';
import type { SupabaseServiceClient } from '../supabase';
import { readAuthorizationRequest, redirectWith } from './authorize';
import type { AssistantClient } from './clients';
import { assistantSettings, newClientId, pkceChallenge } from './settings';

const settings = assistantSettings({ ASSISTANT_ACCOUNTS: '*', CANONICAL_ORIGIN: 'https://peek.example' } as unknown as NodeJS.ProcessEnv)!;
const challenge = pkceChallenge('v'.repeat(64));
const callback = 'https://assistant.example.com/oauth/callback';
const single: AssistantClient = { id: newClientId(), name: 'Example Assistant', uri: null, redirectUris: [callback] };
const several: AssistantClient = { id: newClientId(), name: 'Example App', uri: null, redirectUris: ['http://127.0.0.1/callback', 'com.example.assistant:/oauth'] };
/** Registered before its scheme was refused. */
const older: AssistantClient = { id: newClientId(), name: 'Example Old App', uri: null, redirectUris: ['ms-officecmd:callback'] };
const service = {
  assistantClient: vi.fn(async (id: string) => [single, several, older].find((client) => client.id === id) ?? null),
} as unknown as SupabaseServiceClient;

function query(values: Record<string, string | null> = {}): URLSearchParams {
  const all: Record<string, string | null> = {
    response_type: 'code', client_id: single.id, redirect_uri: callback, code_challenge: challenge,
    code_challenge_method: 'S256', state: 'state-123', scope: 'parcels:read', resource: settings.resource, ...values,
  };
  return new URLSearchParams(Object.entries(all).filter((entry): entry is [string, string] => entry[1] !== null));
}

async function returned(values: Record<string, string | null>) {
  const read = await readAuthorizationRequest(settings, service, query(values));
  if (!('problem' in read) || read.problem.kind !== 'returned') throw new Error(`Expected an error for the client, got ${JSON.stringify(read)}`);
  const url = new URL(read.problem.redirect);
  return { url, error: url.searchParams.get('error'), state: url.searchParams.get('state'), iss: url.searchParams.get('iss') };
}

describe('an authorization request', () => {
  it('reads a valid request', async () => {
    await expect(readAuthorizationRequest(settings, service, query())).resolves.toEqual({
      request: { client: single, redirectUri: callback, state: 'state-123', challenge, scopes: ['parcels:read'] },
    });
  });

  it('shows the owner an unknown client or redirect, and sends nothing back', async () => {
    await expect(readAuthorizationRequest(settings, service, query({ client_id: newClientId() }))).resolves.toEqual({ problem: { kind: 'shown', reason: 'client' } });
    await expect(readAuthorizationRequest(settings, service, query({ client_id: null }))).resolves.toEqual({ problem: { kind: 'shown', reason: 'client' } });
    await expect(readAuthorizationRequest(settings, service, query({ redirect_uri: 'https://attacker.example.net/callback' })))
      .resolves.toEqual({ problem: { kind: 'shown', reason: 'redirect' } });
    // With several registered, the client must say which one.
    await expect(readAuthorizationRequest(settings, service, query({ client_id: several.id, redirect_uri: null })))
      .resolves.toEqual({ problem: { kind: 'shown', reason: 'redirect' } });
    // An address registered before its scheme was refused is never sent anything, not even a refusal.
    for (const redirect of ['ms-officecmd:callback', null]) {
      await expect(readAuthorizationRequest(settings, service, query({ client_id: older.id, redirect_uri: redirect })))
        .resolves.toEqual({ problem: { kind: 'shown', reason: 'redirect' } });
    }
  });

  it("reads Peek's own Home Assistant client without fetching its document, on a development server too", async () => {
    const fetching = vi.spyOn(globalThis, 'fetch');
    // A development server's page is on HTTP, which the consent page never links to.
    for (const [origin, uri] of [['https://peek.example', 'https://peek.example/assistants'], ['http://127.0.0.1:3418', null]] as const) {
      const local = assistantSettings({ ASSISTANT_ACCOUNTS: '*', CANONICAL_ORIGIN: origin } as unknown as NodeJS.ProcessEnv)!;
      const id = `${origin}/oauth/clients/home-assistant.json`;
      const redirect = 'https://my.home-assistant.io/redirect/oauth';
      const read = await readAuthorizationRequest(local, service, query({ client_id: id, redirect_uri: redirect, resource: null }));
      expect(read).toEqual({ request: {
        client: { id, name: 'Home Assistant', uri, redirectUris: [redirect] },
        redirectUri: redirect, state: 'state-123', challenge, scopes: ['parcels:read'],
      } });
      await expect(readAuthorizationRequest(local, service, query({ client_id: id, redirect_uri: 'https://attacker.example.net/redirect/oauth' })))
        .resolves.toEqual({ problem: { kind: 'shown', reason: 'redirect' } });
    }
    // Another server's Home Assistant document is not Peek's own: on HTTP it is not read at all.
    await expect(readAuthorizationRequest(settings, service, query({ client_id: 'http://127.0.0.1:3418/oauth/clients/home-assistant.json' })))
      .resolves.toEqual({ problem: { kind: 'shown', reason: 'client' } });
    expect(fetching).not.toHaveBeenCalled();
    fetching.mockRestore();
  });

  it('uses the one registered redirect when none is given, and a loopback one on any port', async () => {
    const implied = await readAuthorizationRequest(settings, service, query({ redirect_uri: null }));
    expect('request' in implied && implied.request.redirectUri).toBe(callback);
    const loopback = await readAuthorizationRequest(settings, service, query({ client_id: several.id, redirect_uri: 'http://127.0.0.1:49152/callback' }));
    expect('request' in loopback && loopback.request.redirectUri).toBe('http://127.0.0.1:49152/callback');
  });

  it('sends every other problem back to the redirect, with the state and the issuer', async () => {
    for (const [values, error] of [
      [{ response_type: 'token' }, 'unsupported_response_type'],
      [{ response_type: null }, 'unsupported_response_type'],
      [{ code_challenge: null }, 'invalid_request'],
      [{ code_challenge_method: null }, 'invalid_request'],
      [{ code_challenge_method: 'plain', code_challenge: 'v'.repeat(64) }, 'invalid_request'],
      [{ code_challenge: `${challenge}x` }, 'invalid_request'],
      [{ resource: 'https://other.example/mcp' }, 'invalid_target'],
      [{ resource: 'https://peek.example' }, 'invalid_target'],
    ] as const) {
      const answer = await returned(values);
      expect(answer.error, JSON.stringify(values)).toBe(error);
      expect(answer.state).toBe('state-123');
      expect(answer.iss).toBe('https://peek.example');
      expect(`${answer.url.origin}${answer.url.pathname}`).toBe(callback);
      expect(answer.url.searchParams.get('code')).toBeNull();
    }
  });

  it('refuses a state that is too long, without echoing it', async () => {
    const answer = await returned({ state: 's'.repeat(2_001) });
    expect(answer.error).toBe('invalid_request');
    expect(answer.state).toBeNull();
    expect(answer.iss).toBe('https://peek.example');
  });

  it('takes the resource with or without a trailing slash, or none', async () => {
    for (const resource of ['https://peek.example/mcp/', null]) {
      expect('request' in await readAuthorizationRequest(settings, service, query({ resource }))).toBe(true);
    }
  });

  it('drops the scopes it does not know, and asks for every one only when scope is omitted', async () => {
    const scopes = async (scope: string | null) => {
      const read = await readAuthorizationRequest(settings, service, query({ scope }));
      return 'request' in read ? read.request.scopes : null;
    };
    expect(await scopes('openid parcels:write offline_access')).toEqual(['parcels:write']);
    expect(await scopes('parcels:write parcels:read')).toEqual(['parcels:read', 'parcels:write']);
    expect(await scopes('parcels:edit parcels:read')).toEqual(['parcels:read', 'parcels:edit']);
    expect((await returned({ scope: 'openid profile' })).error).toBe('invalid_scope');
    expect((await returned({ scope: '' })).error).toBe('invalid_scope');
    expect(await scopes(null)).toEqual(['parcels:read', 'parcels:write', 'parcels:edit']);
  });
});

describe('the redirect back to a client', () => {
  it('keeps the redirect\'s own query, leaves out what is null, and names the issuer', () => {
    const url = new URL(redirectWith(settings, 'https://assistant.example.com/callback?tenant=7', { code: 'synthetic-code', state: null }));
    expect([...url.searchParams]).toEqual([['tenant', '7'], ['code', 'synthetic-code'], ['iss', 'https://peek.example']]);
    expect(new URL(redirectWith(settings, 'com.example.assistant:/oauth', { error: 'access_denied' })).searchParams.get('iss')).toBe('https://peek.example');
  });
});
