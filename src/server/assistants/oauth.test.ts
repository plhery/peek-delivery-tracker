import { describe, expect, it, vi } from 'vitest';
import { HttpError } from '../api';
import { SupabaseError, type SupabaseServiceClient } from '../supabase';
import {
  authorizationServerMetadata,
  exchangeToken,
  OAuthRequestError,
  protectedResourceMetadata,
  readForm,
  registerClient,
  revokeToken,
} from './oauth';
import { assistantSettings, newClientId, newSecret, pkceChallenge, secretDigest } from './settings';

// Synthetic ids only.
const userId = '5b000000-0000-4000-a000-0000000000a1';
const grantId = '6c000000-0000-4000-a000-0000000000a1';
const settings = assistantSettings({ ASSISTANT_ACCOUNTS: '*', CANONICAL_ORIGIN: 'https://peek.example' } as unknown as NodeJS.ProcessEnv)!;
const clientId = newClientId();
const callback = 'https://assistant.example.com/oauth/callback';
const verifier = 'v'.repeat(64);
const now = Date.UTC(2026, 10, 3, 12);

function service() {
  const calls = {
    redeemAssistantCode: vi.fn<SupabaseServiceClient['redeemAssistantCode']>(async () => ({ userId, grantId, scopes: ['parcels:read'] })),
    refreshAssistantTokens: vi.fn<SupabaseServiceClient['refreshAssistantTokens']>(async () => ({ userId, grantId, scopes: ['parcels:read', 'parcels:write'], rotated: true })),
    registerAssistantClient: vi.fn<SupabaseServiceClient['registerAssistantClient']>(async () => undefined),
    revokeAssistantToken: vi.fn<SupabaseServiceClient['revokeAssistantToken']>(async () => undefined),
  };
  return { ...calls, client: calls as unknown as SupabaseServiceClient };
}

function form(values: Record<string, string>): URLSearchParams {
  return new URLSearchParams(values);
}

function posted(body: string, type = 'application/x-www-form-urlencoded'): Request {
  return new Request('https://peek.example/oauth/token', { method: 'POST', headers: { 'content-type': type }, body });
}

describe('the metadata', () => {
  it('names the MCP resource and its authorization server', () => {
    expect(protectedResourceMetadata(settings)).toEqual({
      resource: 'https://peek.example/mcp',
      authorization_servers: ['https://peek.example'],
      scopes_supported: ['parcels:read', 'parcels:write'],
      bearer_methods_supported: ['header'],
      resource_name: 'Peek',
    });
  });

  it('describes a public-client server with PKCE S256, metadata documents and the issuer in redirects', () => {
    expect(authorizationServerMetadata(settings)).toEqual({
      issuer: 'https://peek.example',
      authorization_endpoint: 'https://peek.example/oauth/authorize',
      token_endpoint: 'https://peek.example/oauth/token',
      registration_endpoint: 'https://peek.example/oauth/register',
      revocation_endpoint: 'https://peek.example/oauth/revoke',
      scopes_supported: ['parcels:read', 'parcels:write'],
      response_types_supported: ['code'],
      response_modes_supported: ['query'],
      grant_types_supported: ['authorization_code', 'refresh_token'],
      token_endpoint_auth_methods_supported: ['none'],
      revocation_endpoint_auth_methods_supported: ['none'],
      code_challenge_methods_supported: ['S256'],
      client_id_metadata_document_supported: true,
      authorization_response_iss_parameter_supported: true,
    });
  });
});

describe('reading a form', () => {
  it('reads a form body', async () => {
    expect((await readForm(posted('grant_type=refresh_token&client_id=a%20b'))).get('client_id')).toBe('a b');
    expect((await readForm(posted('token=x', 'Application/X-WWW-Form-Urlencoded; charset=UTF-8'))).get('token')).toBe('x');
  });

  it('refuses another content type, a repeated parameter, and a large body', async () => {
    await expect(readForm(posted('{"token":"x"}', 'application/json'))).rejects.toMatchObject({ error: 'invalid_request' });
    await expect(readForm(posted('token=x', 'application/x-www-form-urlencodedx'))).rejects.toBeInstanceOf(OAuthRequestError);
    await expect(readForm(new Request('https://peek.example/oauth/token', { method: 'POST', body: 'token=x' }))).rejects.toBeInstanceOf(OAuthRequestError);
    await expect(readForm(posted('code=a&grant_type=authorization_code&code=b'))).rejects.toMatchObject({
      error: 'invalid_request', message: 'code is given more than once',
    });
    await expect(readForm(posted(`token=${'x'.repeat(8_200)}`))).rejects.toMatchObject({ status: 413 });
    await expect(readForm(posted(`token=${'x'.repeat(8_200)}`))).rejects.toBeInstanceOf(HttpError);
  });
});

describe('the token endpoint', () => {
  it('trades a code and its verifier for tokens, passing the database only digests and the challenge', async () => {
    const db = service();
    const code = newSecret('code');
    const response = await exchangeToken(settings, db.client, form({
      grant_type: 'authorization_code', code, redirect_uri: callback, code_verifier: verifier, client_id: clientId, resource: settings.resource,
    }), now);
    expect(response.status).toBe(200);
    expect(response.headers.get('cache-control')).toBe('no-store');
    expect(response.headers.get('pragma')).toBe('no-cache');
    const body = await response.json();
    expect(body).toEqual({
      access_token: expect.stringMatching(/^peek_at_[A-Za-z0-9_-]{43}$/),
      token_type: 'Bearer',
      expires_in: 3_600,
      refresh_token: expect.stringMatching(/^peek_rt_[A-Za-z0-9_-]{43}$/),
      scope: 'parcels:read',
    });
    expect(db.redeemAssistantCode).toHaveBeenCalledExactlyOnceWith({
      codeDigest: secretDigest(code),
      clientId,
      redirectUri: callback,
      challenge: pkceChallenge(verifier),
      accessDigest: secretDigest(body.access_token),
      accessExpires: new Date(now + 3_600_000),
      refreshDigest: secretDigest(body.refresh_token),
      refreshExpires: new Date(now + 60 * 86_400_000),
    });
    const sent = JSON.stringify(db.redeemAssistantCode.mock.calls);
    for (const secret of [code, verifier, body.access_token, body.refresh_token]) expect(sent).not.toContain(secret);
  });

  it('answers invalid_grant for a code the database does not redeem', async () => {
    const db = service();
    db.redeemAssistantCode.mockResolvedValue(null);
    const response = await exchangeToken(settings, db.client, form({
      grant_type: 'authorization_code', code: newSecret('code'), redirect_uri: callback, code_verifier: verifier, client_id: clientId,
    }), now);
    expect(response.status).toBe(400);
    expect(response.headers.get('cache-control')).toBe('no-store');
    expect(await response.json()).toMatchObject({ error: 'invalid_grant' });
  });

  it('refuses a code request without its parts or a usable verifier, before asking the database', async () => {
    const db = service();
    const complete = { grant_type: 'authorization_code', code: newSecret('code'), redirect_uri: callback, code_verifier: verifier, client_id: clientId };
    for (const values of [
      { ...complete, client_id: '' },
      { ...complete, code: '' },
      { ...complete, redirect_uri: '' },
      { ...complete, code_verifier: '' },
      { ...complete, code_verifier: 'v'.repeat(42) },
      { ...complete, code_verifier: `${'v'.repeat(42)}!` },
    ]) {
      const response = await exchangeToken(settings, db.client, form(values), now);
      expect(response.status).toBe(400);
      expect((await response.json()).error).toBe('invalid_request');
    }
    expect(db.redeemAssistantCode).not.toHaveBeenCalled();
  });

  it('gives a new refresh token only when the grant rotates them', async () => {
    const db = service();
    const presented = newSecret('refresh');
    const values = { grant_type: 'refresh_token', refresh_token: presented, client_id: clientId };
    const rotated = await (await exchangeToken(settings, db.client, form(values), now)).json();
    expect(rotated).toMatchObject({ token_type: 'Bearer', scope: 'parcels:read parcels:write', refresh_token: expect.stringMatching(/^peek_rt_/) });
    expect(rotated.refresh_token).not.toBe(presented);
    expect(db.refreshAssistantTokens).toHaveBeenCalledWith({
      refreshDigest: secretDigest(presented),
      clientId,
      accessDigest: secretDigest(rotated.access_token),
      accessExpires: new Date(now + 3_600_000),
      newRefreshDigest: secretDigest(rotated.refresh_token),
      refreshExpires: new Date(now + 60 * 86_400_000),
    });

    db.refreshAssistantTokens.mockResolvedValue({ userId, grantId, scopes: ['parcels:read'], rotated: false });
    const kept = await (await exchangeToken(settings, db.client, form(values), now)).json();
    expect(kept).not.toHaveProperty('refresh_token');
    expect(kept).toMatchObject({ access_token: expect.stringMatching(/^peek_at_/), scope: 'parcels:read' });

    db.refreshAssistantTokens.mockResolvedValue(null);
    const refused = await exchangeToken(settings, db.client, form(values), now);
    expect(refused.status).toBe(400);
    expect((await refused.json()).error).toBe('invalid_grant');

    const missing = await exchangeToken(settings, db.client, form({ grant_type: 'refresh_token', client_id: clientId }), now);
    expect((await missing.json()).error).toBe('invalid_request');
  });

  it('issues tokens for /mcp alone, and only for the two grants', async () => {
    const db = service();
    const values = { grant_type: 'refresh_token', refresh_token: newSecret('refresh'), client_id: clientId };
    const elsewhere = await exchangeToken(settings, db.client, form({ ...values, resource: 'https://other.example/mcp' }), now);
    expect(elsewhere.status).toBe(400);
    expect((await elsewhere.json()).error).toBe('invalid_target');
    expect(db.refreshAssistantTokens).not.toHaveBeenCalled();
    expect((await exchangeToken(settings, db.client, form({ ...values, resource: 'https://peek.example/mcp/' }), now)).status).toBe(200);
    const password = await exchangeToken(settings, db.client, form({ grant_type: 'password', client_id: clientId }), now);
    expect((await password.json()).error).toBe('unsupported_grant_type');
  });
});

describe('client registration', () => {
  it('registers a public client, whatever auth method it asks for', async () => {
    const db = service();
    const response = await registerClient(db.client, {
      client_name: 'Example Assistant',
      client_uri: 'https://assistant.example.com',
      redirect_uris: [callback, 'http://127.0.0.1/callback'],
      token_endpoint_auth_method: 'client_secret_basic',
    }, now);
    expect(response.status).toBe(201);
    expect(response.headers.get('cache-control')).toBe('no-store');
    const body = await response.json();
    expect(body).toEqual({
      client_id: expect.stringMatching(/^peek_c_[A-Za-z0-9_-]{22}$/),
      client_id_issued_at: Math.floor(now / 1_000),
      client_name: 'Example Assistant',
      client_uri: 'https://assistant.example.com',
      redirect_uris: [callback, 'http://127.0.0.1/callback'],
      grant_types: ['authorization_code', 'refresh_token'],
      response_types: ['code'],
      token_endpoint_auth_method: 'none',
    });
    expect(body).not.toHaveProperty('client_secret');
    expect(db.registerAssistantClient).toHaveBeenCalledWith({
      id: body.client_id, name: 'Example Assistant', uri: 'https://assistant.example.com', redirectUris: [callback, 'http://127.0.0.1/callback'],
    });
  });

  it('names an unnamed client after its first redirect, and drops an address that is not HTTPS', async () => {
    const db = service();
    const body = await (await registerClient(db.client, { redirect_uris: ['http://localhost:3000/callback'], client_uri: 'http://assistant.example.com' }, now)).json();
    expect(body.client_name).toBe('localhost');
    expect(body).not.toHaveProperty('client_uri');
    expect(db.registerAssistantClient).toHaveBeenCalledWith(expect.objectContaining({ name: 'localhost', uri: null }));
  });

  it('refuses missing, too many or unsafe redirects', async () => {
    const db = service();
    for (const redirects of [
      undefined, [], 'https://assistant.example.com/callback', Array.from({ length: 11 }, (_, index) => `https://assistant.example.com/${index}`),
      ['javascript:alert(1)'], [callback, 'http://assistant.example.com/callback'], [42],
    ]) {
      const response = await registerClient(db.client, redirects === undefined ? {} : { redirect_uris: redirects }, now);
      expect(response.status, JSON.stringify(redirects)).toBe(400);
      expect((await response.json()).error).toBe('invalid_redirect_uri');
    }
    expect(db.registerAssistantClient).not.toHaveBeenCalled();
  });

  it('answers 429 when the database says too many clients registered lately', async () => {
    const db = service();
    db.registerAssistantClient.mockRejectedValue(new SupabaseError('too many', 400, 'P0001'));
    const response = await registerClient(db.client, { redirect_uris: [callback] }, now);
    expect(response.status).toBe(429);
    expect((await response.json()).error).toBe('temporarily_unavailable');
    db.registerAssistantClient.mockRejectedValue(new SupabaseError('down', 503));
    await expect(registerClient(db.client, { redirect_uris: [callback] }, now)).rejects.toBeInstanceOf(SupabaseError);
  });
});

describe('token revocation', () => {
  it('answers 200 whether or not the token was known, passing only its digest', async () => {
    const db = service();
    const token = newSecret('refresh');
    const response = await revokeToken(db.client, form({ token, client_id: clientId, token_type_hint: 'refresh_token' }));
    expect(response.status).toBe(200);
    expect(response.headers.get('cache-control')).toBe('no-store');
    expect(db.revokeAssistantToken).toHaveBeenCalledWith(secretDigest(token), clientId);
    expect((await revokeToken(db.client, form({ token: 'unknown-token' }))).status).toBe(200);
    expect(db.revokeAssistantToken).toHaveBeenLastCalledWith(secretDigest('unknown-token'), null);
    const missing = await revokeToken(db.client, form({}));
    expect(missing.status).toBe(400);
    expect((await missing.json()).error).toBe('invalid_request');
  });
});
