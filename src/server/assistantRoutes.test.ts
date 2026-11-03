import { CLIENT_CAPABILITIES_META_KEY, LATEST_PROTOCOL_VERSION, PROTOCOL_VERSION_META_KEY } from '@modelcontextprotocol/server';
import { NextRequest } from 'next/server';
import { afterEach, beforeEach, describe, expect, it, vi } from 'vitest';
import { GET as authorizationServer } from '../../app/.well-known/oauth-authorization-server/route';
import { GET as mcpResource } from '../../app/.well-known/oauth-protected-resource/mcp/route';
import { GET as protectedResource } from '../../app/.well-known/oauth-protected-resource/route';
import { DELETE as disconnect } from '../../app/api/account/assistants/[id]/route';
import { POST as consent } from '../../app/api/account/assistants/consent/route';
import { GET as readAssistants, POST as createToken } from '../../app/api/account/assistants/route';
import { DELETE as mcpDelete, GET as mcpGet, POST as mcpPost } from '../../app/mcp/route';
import { POST as register } from '../../app/oauth/register/route';
import { POST as revoke } from '../../app/oauth/revoke/route';
import { POST as token } from '../../app/oauth/token/route';
import { forgetAssistantEmails } from './assistants/access';
import { newClientId, newSecret, pkceChallenge, secretDigest, type Scope } from './assistants/settings';
import { SupabaseAuthenticator } from './auth';
import { addParcelsFromText } from './parcelsFromText';
import { SupabaseError, SupabaseServiceClient, type AssistantGrant, type AssistantTokenOwner } from './supabase';

vi.mock('./parcelsFromText', async (importOriginal) => ({
  ...await importOriginal<typeof import('./parcelsFromText')>(),
  addParcelsFromText: vi.fn(),
}));

// Synthetic identifiers, addresses and numbers only: UPS's published sample number and made-up ids.
const userId = '5b000000-0000-4000-a000-0000000000a1';
const otherUser = '5b000000-0000-4000-a000-0000000000b2';
const UPS = '1Z999AA10123456784';
const RECEIVER = 'Synthetic Receiver Name';
const POSTCODE = 'PC-SYNTHETIC-9';
const MODERN = '2026-07-28';
/** Times relative to now, so no parcel looks stalled however late the tests run. */
const ago = (hours: number) => new Date(Date.now() - hours * 3_600_000).toISOString();
const ADDED = ago(72);
const ACCEPTED = ago(48);
const LATEST = ago(1);
const none = { params: Promise.resolve({}) };
const service = SupabaseServiceClient.prototype;

/** The access tokens the mocked database knows, by digest. Each has its own grant, so the per-grant limit starts afresh. */
const owners = new Map<string, AssistantTokenOwner>();
let grants = 0;

function connect(scopes: Scope[], user = userId): string {
  grants += 1;
  const secret = newSecret('access');
  owners.set(secretDigest(secret), {
    userId: user,
    grantId: `6c000000-0000-4000-a000-${String(grants).padStart(12, '0')}`,
    scopes,
    kind: 'access',
    clientId: newClientId(),
    expiresAt: Math.floor(Date.now() / 1_000) + 3_600,
  });
  return secret;
}

beforeEach(() => {
  vi.stubEnv('SUPABASE_URL', 'https://database.example');
  vi.stubEnv('SUPABASE_PUBLISHABLE_KEY', 'test-public');
  vi.stubEnv('SUPABASE_SERVICE_ROLE_KEY', 'test-service-key');
  vi.stubEnv('CANONICAL_ORIGIN', 'https://peek.example');
  vi.stubEnv('ASSISTANT_ACCOUNTS', userId);
  vi.spyOn(console, 'log').mockImplementation(() => undefined);
  vi.spyOn(console, 'warn').mockImplementation(() => undefined);
  vi.spyOn(console, 'error').mockImplementation(() => undefined);
  vi.spyOn(SupabaseAuthenticator.prototype, 'validate').mockResolvedValue({ id: userId, email: 'alex@example.com', authenticatedAt: null, sessionId: null });
  vi.spyOn(service, 'assistantToken').mockImplementation(async (digest) => owners.get(digest) ?? null);
  vi.mocked(addParcelsFromText).mockReset();
  forgetAssistantEmails();
  owners.clear();
});
afterEach(() => { vi.restoreAllMocks(); vi.unstubAllEnvs(); });

// The MCP endpoint, as a 2025-era client (stateless, no envelope) or a 2026-07-28 one (per-request envelope) calls it.

let ids = 0;

function mcpRequest(secret: string | null, method: string, params: Record<string, unknown> = {}, era: 'legacy' | 'modern' = 'legacy', extra: Record<string, string> = {}) {
  const headers: Record<string, string> = { accept: 'application/json, text/event-stream', 'content-type': 'application/json', ...extra };
  if (secret) headers.authorization = `Bearer ${secret}`;
  if (era === 'modern') {
    headers['mcp-protocol-version'] = MODERN;
    headers['mcp-method'] = method;
    if (typeof params.name === 'string') headers['mcp-name'] = params.name;
    params = { ...params, _meta: { [PROTOCOL_VERSION_META_KEY]: MODERN, [CLIENT_CAPABILITIES_META_KEY]: {} } };
  } else if (method !== 'initialize') {
    headers['mcp-protocol-version'] = LATEST_PROTOCOL_VERSION;
  }
  ids += 1;
  return new NextRequest('https://peek.example/mcp', { method: 'POST', headers, body: JSON.stringify({ jsonrpc: '2.0', id: ids, method, params }) });
}

function mcp(...values: Parameters<typeof mcpRequest>): Promise<Response> {
  return mcpPost(mcpRequest(...values), none);
}

interface RpcAnswer {
  result?: {
    tools?: Array<{ name: string }>;
    structuredContent?: Record<string, unknown>;
    content?: Array<{ type: string; text: string }>;
    isError?: boolean;
    [key: string]: unknown;
  };
  error?: { code: number; message: string };
}

/** The JSON-RPC answer, whether it came as JSON or as one event of a stream. */
async function rpc(response: Response): Promise<RpcAnswer> {
  const text = await response.text();
  if (!(response.headers.get('content-type') ?? '').startsWith('text/event-stream')) return JSON.parse(text);
  const messages = text.split('\n').filter((line) => line.startsWith('data:')).map((line) => line.slice(5).trim()).filter(Boolean).map((data) => JSON.parse(data));
  return messages.find((message) => 'result' in message || 'error' in message);
}

async function call(secret: string, name: string, args: Record<string, unknown> = {}, era: 'legacy' | 'modern' = 'legacy') {
  const response = await mcp(secret, 'tools/call', { name, arguments: args }, era);
  expect(response.status).toBe(200);
  return rpc(response);
}

/** A stored parcel row, as the database gives it to the server, with a receiver and a postcode that must not reach an assistant. */
function stored(index: number, stage: string, options: { archived?: boolean; trackingNumber?: string } = {}) {
  const id = `7d000000-0000-4000-a000-${String(index).padStart(12, '0')}`;
  return {
    id,
    tracking_number: options.trackingNumber ?? `SYNTHETIC${index}`,
    label: `Synthetic parcel ${index}`,
    carrier: 'ups',
    created_at: ADDED,
    sync_status: 'ok',
    archived_at: options.archived ? ago(2) : null,
    dpd_postcode: POSTCODE,
    carrier_data: { receiver_name: RECEIVER, sender_name: 'Example Shop' },
    tracking_events: [
      { id: `${id}-0`, package_id: id, stage: 'accepted', description: 'Parcel received', location: 'Example Depot', occurred_at: ACCEPTED, provider_event_id: `carrier:${index}:0` },
      { id: `${id}-1`, package_id: id, stage, description: 'Latest scan', location: 'Example Hub', occurred_at: LATEST, provider_event_id: `carrier:${index}:1` },
    ],
  };
}

describe('the MCP endpoint', () => {
  it('asks for a token, and says when the one given is unknown', async () => {
    const challenge = 'Bearer resource_metadata="https://peek.example/.well-known/oauth-protected-resource/mcp", scope="parcels:read parcels:write"';
    const anonymous = await mcp(null, 'tools/list');
    expect(anonymous.status).toBe(401);
    expect(anonymous.headers.get('www-authenticate')).toBe(challenge);
    expect(service.assistantToken).not.toHaveBeenCalled();

    const unknown = newSecret('access');
    const refused = await mcp(unknown, 'tools/list');
    expect(refused.status).toBe(401);
    expect(refused.headers.get('www-authenticate')).toBe(`${challenge}, error="invalid_token"`);
    expect(service.assistantToken).toHaveBeenCalledWith(secretDigest(unknown));
  });

  it('makes a network that presents 600 unknown tokens in a minute wait before any other is looked up', async () => {
    vi.stubEnv('TRUST_PROXY_HEADERS', 'true');
    const from = (ip: string) => ({ 'cf-connecting-ip': ip });
    for (let index = 0; index < 600; index += 1) {
      expect((await mcp(newSecret('access'), 'tools/list', {}, 'legacy', from('203.0.113.7'))).status).toBe(401);
    }
    const lookups = vi.mocked(service.assistantToken).mock.calls.length;
    expect((await mcp(newSecret('access'), 'tools/list', {}, 'legacy', from('203.0.113.7'))).status).toBe(401);
    const waiting = await mcp(connect(['parcels:read']), 'tools/list', {}, 'legacy', from('203.0.113.7'));
    expect(waiting.status).toBe(429);
    expect(Number(waiting.headers.get('retry-after'))).toBeGreaterThan(0);
    expect(service.assistantToken).toHaveBeenCalledTimes(lookups + 1);
    expect((await mcp(connect(['parcels:read']), 'tools/list', {}, 'legacy', from('198.51.100.9'))).status).toBe(200);
  });

  it('refuses an account the setting is not open to, by id or by confirmed email', async () => {
    const secret = connect(['parcels:read'], otherUser);
    const auth = vi.spyOn(service, 'getAuthAccount').mockResolvedValue({ email: 'sam@example.com', emailConfirmed: false, locale: null });
    expect((await mcp(secret, 'tools/list')).status).toBe(403);
    // A list of ids alone needs no email.
    expect(auth).not.toHaveBeenCalled();

    vi.stubEnv('ASSISTANT_ACCOUNTS', 'sam@example.com');
    expect((await mcp(secret, 'tools/list')).status).toBe(403);
    expect(auth).toHaveBeenCalledWith(otherUser);
    forgetAssistantEmails();
    auth.mockResolvedValue({ email: 'Sam@Example.com', emailConfirmed: true, locale: null });
    expect((await mcp(secret, 'tools/list')).status).toBe(200);
    expect((await mcp(secret, 'tools/list')).status).toBe(200);
    expect(auth).toHaveBeenCalledTimes(2);
  });

  it('limits each grant to 120 requests a minute', async () => {
    // An account the setting is closed to answers fastest, and still counts.
    const secret = connect(['parcels:read'], otherUser);
    for (let index = 0; index < 120; index += 1) expect((await mcp(secret, 'tools/list')).status).toBe(403);
    const limited = await mcp(secret, 'tools/list');
    expect(limited.status).toBe(429);
    expect(Number(limited.headers.get('retry-after'))).toBeGreaterThan(0);
    expect((await mcp(connect(['parcels:read'], otherUser), 'tools/list')).status).toBe(403);
  });

  it('lists the tools a token\'s scopes allow, each request on its own', async () => {
    const reader = connect(['parcels:read']);
    const writer = connect(['parcels:read', 'parcels:write']);
    const started = await mcp(reader, 'initialize', { protocolVersion: LATEST_PROTOCOL_VERSION, capabilities: {}, clientInfo: { name: 'example-client', version: '1.0.0' } });
    expect(started.status).toBe(200);
    expect((await rpc(started)).result).toMatchObject({ protocolVersion: LATEST_PROTOCOL_VERSION, serverInfo: { name: 'peek' }, capabilities: { tools: {} } });
    const names = async (secret: string, era?: 'legacy' | 'modern') => {
      const response = await mcp(secret, 'tools/list', {}, era);
      expect(response.status).toBe(200);
      return (await rpc(response)).result!.tools!.map(({ name }) => name).sort();
    };
    expect(await names(reader)).toEqual(['get_parcel', 'list_parcels']);
    expect(await names(writer)).toEqual(['add_parcels', 'archive_parcel', 'get_parcel', 'list_parcels']);
    expect(await names(reader, 'modern')).toEqual(['get_parcel', 'list_parcels']);
  });

  it('answers a 2026-07-28 client in JSON', async () => {
    const response = await mcp(connect(['parcels:read']), 'tools/list', {}, 'modern');
    expect(response.status).toBe(200);
    expect(response.headers.get('content-type')).toMatch(/^application\/json/);
  });

  it('lists the account\'s parcels without the receiver\'s name or postcode', async () => {
    const list = vi.spyOn(service, 'listAccountPackages').mockResolvedValue([
      stored(1, 'in_transit'), stored(2, 'delivered'), stored(3, 'in_transit', { archived: true }),
    ]);
    const secret = connect(['parcels:read']);
    const answer = await call(secret, 'list_parcels');
    expect(list).toHaveBeenCalledWith(userId);
    expect(answer.result!.structuredContent).toEqual({
      total: 1,
      parcels: [{
        id: '7d000000-0000-4000-a000-000000000001',
        name: 'Synthetic parcel 1',
        carrier: 'UPS',
        trackingNumber: 'SYNTHETIC1',
        status: 'in_transit',
        statusText: 'Latest scan',
        needsAttention: null,
        expectedDelivery: null,
        lastUpdate: { time: LATEST, description: 'Latest scan', location: 'Example Hub' },
        pickupPoint: null,
        sender: 'Example Shop',
        archived: false,
        addedAt: ADDED,
      }],
    });
    const all = await call(secret, 'list_parcels', { status: 'all', limit: 2 }, 'modern');
    expect(all.result!.structuredContent).toMatchObject({ total: 3 });
    expect(all.result!.structuredContent!.parcels).toHaveLength(2);
    for (const text of [JSON.stringify(answer), JSON.stringify(all)]) {
      expect(text).not.toContain(RECEIVER);
      expect(text).not.toContain(POSTCODE);
    }
  });

  it('adds parcels from a text for the caller\'s own account, with the write scope only', async () => {
    vi.mocked(addParcelsFromText).mockResolvedValue([
      { trackingNumber: UPS, carrier: 'ups', outcome: 'added', packageId: '7d000000-0000-4000-a000-0000000000aa', jobId: null },
      { trackingNumber: 'SYNTHETIC2', carrier: 'ups', outcome: 'refused', reason: 'not followed' },
    ]);
    const writer = connect(['parcels:write']);
    const answer = await call(writer, 'add_parcels', { text: `Shipped with UPS: ${UPS}`, name: '  Example Shop ' });
    expect(addParcelsFromText).toHaveBeenCalledExactlyOnceWith(expect.any(SupabaseServiceClient), userId, `Shipped with UPS: ${UPS}`, 'Example Shop');
    expect(answer.result!.structuredContent).toEqual({
      parcels: [
        { trackingNumber: UPS, carrier: 'UPS', outcome: 'added', id: '7d000000-0000-4000-a000-0000000000aa' },
        { trackingNumber: 'SYNTHETIC2', carrier: 'UPS', outcome: 'refused', reason: 'not followed' },
      ],
    });
    expect(answer.result!.content![0]!.text).toContain(`${UPS} (UPS): added`);

    const reader = connect(['parcels:read']);
    expect((await call(reader, 'add_parcels', { text: UPS })).error).toMatchObject({ code: -32602 });
    expect((await call(writer, 'list_parcels')).error).toMatchObject({ code: -32602 });
    expect(addParcelsFromText).toHaveBeenCalledTimes(1);
  });

  it('reads and archives only the caller\'s own parcels, by id or by tracking number', async () => {
    const parcel = stored(4, 'in_transit', { trackingNumber: UPS });
    const known = vi.spyOn(service, 'accountTrackingNumbers').mockResolvedValue(new Map([[UPS, parcel.id]]));
    const owned = vi.spyOn(service, 'getOwnedPackage').mockImplementation(async (id, owner) => (id === parcel.id && owner === userId ? parcel : null));
    const archive = vi.spyOn(service, 'archivePackage').mockResolvedValue(undefined);
    const secret = connect(['parcels:read', 'parcels:write']);

    const read = await call(secret, 'get_parcel', { parcel: parcel.id.toUpperCase() });
    expect(read.result!.structuredContent).toMatchObject({ id: parcel.id, trackingNumber: UPS, status: 'in_transit' });
    expect((read.result!.structuredContent!.history as Array<{ status: string }>).map((event) => event.status)).toEqual(['in_transit', 'accepted']);
    expect(JSON.stringify(read)).not.toContain(RECEIVER);

    expect((await call(secret, 'archive_parcel', { parcel: ` ${UPS.toLowerCase()} ` })).result!.structuredContent).toEqual({ id: parcel.id, archived: true });
    expect(known).toHaveBeenCalledWith(userId, [UPS]);
    expect(owned).toHaveBeenCalledWith(parcel.id, userId);
    expect(archive).toHaveBeenCalledExactlyOnceWith(parcel.id);

    const elsewhere = await call(secret, 'get_parcel', { parcel: '7d000000-0000-4000-a000-0000000000ff' });
    expect(elsewhere.result).toMatchObject({ isError: true, content: [{ text: 'No such parcel in this Peek account.' }] });
    vi.stubEnv('ASSISTANT_ACCOUNTS', `${userId},${otherUser}`);
    const other = await call(connect(['parcels:read', 'parcels:write'], otherUser), 'archive_parcel', { parcel: parcel.id });
    expect(other.result).toMatchObject({ isError: true });
    expect(archive).toHaveBeenCalledTimes(1);
  });

  it('opens no stream and ends no session', async () => {
    for (const handler of [mcpGet, mcpDelete]) {
      const response = handler();
      expect(response.status).toBe(405);
      expect(response.headers.get('allow')).toBe('POST');
    }
  });

  it('refuses another site\'s page', async () => {
    const secret = connect(['parcels:read']);
    expect((await mcpPost(mcpRequest(secret, 'tools/list', {}, 'legacy', { origin: 'https://attacker.example.net' }), none)).status).toBe(403);
    expect((await mcpPost(mcpRequest(secret, 'tools/list', {}, 'legacy', { 'sec-fetch-site': 'cross-site' }), none)).status).toBe(403);
  });
});

describe('the OAuth endpoints', () => {
  function form(path: string, values: Record<string, string>, type = 'application/x-www-form-urlencoded') {
    return new NextRequest(`https://peek.example${path}`, { method: 'POST', headers: { 'content-type': type }, body: new URLSearchParams(values).toString() });
  }

  it('describe the server and the resource', async () => {
    for (const handler of [protectedResource, mcpResource]) {
      const response = handler();
      expect(response.status).toBe(200);
      expect(response.headers.get('cache-control')).toBe('public, max-age=3600');
      expect(await response.json()).toMatchObject({ resource: 'https://peek.example/mcp', authorization_servers: ['https://peek.example'] });
    }
    expect(await authorizationServer().json()).toMatchObject({ issuer: 'https://peek.example', token_endpoint: 'https://peek.example/oauth/token' });
  });

  it('trade a code for tokens from a form, and refuse JSON with an OAuth error', async () => {
    const redeem = vi.spyOn(service, 'redeemAssistantCode').mockResolvedValue({ userId, grantId: '6c000000-0000-4000-a000-0000000000f1', scopes: ['parcels:read'] });
    const verifier = 'v'.repeat(64);
    const response = await token(form('/oauth/token', {
      grant_type: 'authorization_code', code: newSecret('code'), redirect_uri: 'https://assistant.example.com/callback', code_verifier: verifier, client_id: newClientId(),
    }), none);
    expect(response.status).toBe(200);
    expect(response.headers.get('cache-control')).toBe('no-store');
    expect(await response.json()).toMatchObject({ token_type: 'Bearer', scope: 'parcels:read' });
    expect(redeem).toHaveBeenCalledWith(expect.objectContaining({ challenge: pkceChallenge(verifier) }));

    const json = await token(new NextRequest('https://peek.example/oauth/token', { method: 'POST', headers: { 'content-type': 'application/json' }, body: '{}' }), none);
    expect(json.status).toBe(400);
    expect((await json.json()).error).toBe('invalid_request');
  });

  it('register a client from JSON only', async () => {
    const stored = vi.spyOn(service, 'registerAssistantClient').mockResolvedValue(undefined);
    const response = await register(new NextRequest('https://peek.example/oauth/register', {
      method: 'POST', headers: { 'content-type': 'application/json' }, body: JSON.stringify({ client_name: 'Example Assistant', redirect_uris: ['https://assistant.example.com/callback'] }),
    }), none);
    expect(response.status).toBe(201);
    expect(await response.json()).toMatchObject({ client_name: 'Example Assistant', token_endpoint_auth_method: 'none' });
    expect((await register(form('/oauth/register', { redirect_uris: 'https://assistant.example.com/callback' }), none)).status).toBe(415);
    expect(stored).toHaveBeenCalledTimes(1);
  });

  it('take a token back', async () => {
    const revoked = vi.spyOn(service, 'revokeAssistantToken').mockResolvedValue(undefined);
    const secret = newSecret('refresh');
    expect((await revoke(form('/oauth/revoke', { token: secret }), none)).status).toBe(200);
    expect(revoked).toHaveBeenCalledWith(secretDigest(secret), null);
  });

  it('and the MCP endpoint are not there when no account may connect', async () => {
    vi.stubEnv('ASSISTANT_ACCOUNTS', '');
    expect((await mcp(connect(['parcels:read']), 'tools/list')).status).toBe(404);
    for (const handler of [protectedResource, mcpResource, authorizationServer]) expect(handler().status).toBe(404);
    expect((await token(form('/oauth/token', { grant_type: 'refresh_token' }), none)).status).toBe(404);
    expect((await revoke(form('/oauth/revoke', { token: 'x' }), none)).status).toBe(404);
    expect((await register(new NextRequest('https://peek.example/oauth/register', {
      method: 'POST', headers: { 'content-type': 'application/json' }, body: JSON.stringify({ redirect_uris: ['https://assistant.example.com/callback'] }),
    }), none)).status).toBe(404);
    // A list without the canonical origin connects nothing either.
    vi.stubEnv('ASSISTANT_ACCOUNTS', '*');
    vi.stubEnv('CANONICAL_ORIGIN', '');
    expect(protectedResource().status).toBe(404);
  });
});

describe('the account\'s assistants', () => {
  const connection: AssistantGrant = {
    id: '6c000000-0000-4000-a000-0000000000c1', kind: 'personal', name: 'Home script', redirectHost: null,
    scopes: ['parcels:read'], createdAt: '2026-11-03T10:00:00.000Z', lastUsedAt: null,
  };

  function signedIn(method: string, path = '/api/account/assistants', body?: unknown) {
    return new NextRequest(`https://peek.example${path}`, {
      method,
      headers: { Authorization: 'Bearer assistants-test', ...(body === undefined ? {} : { 'content-type': 'application/json' }) },
      ...(body === undefined ? {} : { body: JSON.stringify(body) }),
    });
  }

  it('are not there when no account may connect, or for an account the setting is not open to', async () => {
    const read = vi.spyOn(service, 'assistantGrants').mockResolvedValue([connection]);
    vi.stubEnv('ASSISTANT_ACCOUNTS', '');
    expect((await readAssistants(signedIn('GET'), none)).status).toBe(404);
    vi.stubEnv('ASSISTANT_ACCOUNTS', otherUser);
    expect((await readAssistants(signedIn('GET'), none)).status).toBe(404);
    expect((await createToken(signedIn('POST', undefined, { name: 'Home script', scopes: ['parcels:read'] }), none)).status).toBe(404);
    expect(read).not.toHaveBeenCalled();
  });

  it('name an account by its confirmed email only, as the MCP endpoint does', async () => {
    vi.spyOn(service, 'assistantGrants').mockResolvedValue([connection]);
    const auth = vi.spyOn(service, 'getAuthAccount').mockResolvedValue({ email: 'sam@example.com', emailConfirmed: false, locale: null });
    vi.stubEnv('ASSISTANT_ACCOUNTS', 'sam@example.com');
    expect((await readAssistants(signedIn('GET'), none)).status).toBe(404);
    forgetAssistantEmails();
    auth.mockResolvedValue({ email: 'sam@example.com', emailConfirmed: true, locale: null });
    expect((await readAssistants(signedIn('GET'), none)).status).toBe(200);
  });

  it('lists the connections and the address to connect to', async () => {
    const read = vi.spyOn(service, 'assistantGrants').mockResolvedValue([connection]);
    const response = await readAssistants(signedIn('GET'), none);
    expect(response.status).toBe(200);
    expect(await response.json()).toEqual({ connections: [connection], mcpUrl: 'https://peek.example/mcp' });
    expect(read).toHaveBeenCalledWith(userId);
  });

  it('makes a personal token, shown once and stored as its digest', async () => {
    const create = vi.spyOn(service, 'createPersonalToken').mockResolvedValue(connection);
    const response = await createToken(signedIn('POST', undefined, { name: '  Home\nscript ', scopes: ['parcels:write', 'parcels:read'] }), none);
    expect(response.status).toBe(201);
    const body = await response.json();
    expect(body.connection).toEqual(connection);
    expect(body.token).toMatch(/^peek_pat_[A-Za-z0-9_-]{43}$/);
    expect(create).toHaveBeenCalledExactlyOnceWith(userId, 'Home script', ['parcels:read', 'parcels:write'], secretDigest(body.token));
    expect(JSON.stringify(create.mock.calls)).not.toContain(body.token);
  });

  it('refuses a token without a name or a known scope, and an eleventh one', async () => {
    const create = vi.spyOn(service, 'createPersonalToken').mockResolvedValue(connection);
    for (const payload of [
      { scopes: ['parcels:read'] }, { name: ' \u0000 ', scopes: ['parcels:read'] }, { name: 'Home script' }, { name: 'Home script', scopes: [] },
      { name: 'Home script', scopes: 'parcels:read' }, { name: 'Home script', scopes: ['parcels:read', 'parcels:delete'] },
    ]) expect((await createToken(signedIn('POST', undefined, payload), none)).status, JSON.stringify(payload)).toBe(400);
    expect(create).not.toHaveBeenCalled();
    create.mockRejectedValue(new SupabaseError('ten tokens', 400, 'P0001'));
    expect((await createToken(signedIn('POST', undefined, { name: 'Home script', scopes: ['parcels:read'] }), none)).status).toBe(409);
  });

  it('disconnects one, even for an account the setting is no longer open to', async () => {
    const revokeGrant = vi.spyOn(service, 'revokeAssistantGrant').mockResolvedValue(true);
    const route = (id: string) => ({ params: Promise.resolve({ id }) });
    expect((await disconnect(signedIn('DELETE', `/api/account/assistants/${connection.id}`), route(connection.id.toUpperCase()))).status).toBe(204);
    expect(revokeGrant).toHaveBeenCalledWith(userId, connection.id);
    vi.stubEnv('ASSISTANT_ACCOUNTS', '');
    expect((await disconnect(signedIn('DELETE', `/api/account/assistants/${connection.id}`), route(connection.id))).status).toBe(204);
    revokeGrant.mockResolvedValue(false);
    expect((await disconnect(signedIn('DELETE', `/api/account/assistants/${connection.id}`), route(connection.id))).status).toBe(404);
    expect((await disconnect(signedIn('DELETE', '/api/account/assistants/nope'), route('nope'))).status).toBe(400);
    expect(revokeGrant).toHaveBeenCalledTimes(3);
  });

  it('take the owner\'s consent only from an account the setting is open to', async () => {
    const client = { id: newClientId(), name: 'Example Assistant', uri: null, redirectUris: ['https://assistant.example.com/callback'] };
    vi.spyOn(service, 'assistantClient').mockResolvedValue(client);
    const open = vi.spyOn(service, 'openAssistantCode').mockResolvedValue(undefined);
    const query = new URLSearchParams({
      response_type: 'code', client_id: client.id, code_challenge: pkceChallenge('v'.repeat(64)), code_challenge_method: 'S256', state: 'state-123',
    }).toString();
    const path = '/api/account/assistants/consent';

    const response = await consent(signedIn('POST', path, { query, approve: true, scopes: ['parcels:read'] }), none);
    expect(response.status).toBe(200);
    const redirect = new URL((await response.json()).redirect);
    expect(redirect.searchParams.get('code')).toMatch(/^peek_ac_/);
    expect(redirect.searchParams.get('iss')).toBe('https://peek.example');
    expect(open).toHaveBeenCalledWith(expect.objectContaining({ userId, scopes: ['parcels:read'] }));

    for (const payload of [{ query, approve: 'yes' }, { query: 42, approve: true }, { query, approve: true, scopes: 'parcels:read' }, { query: 'x'.repeat(8_001), approve: true }]) {
      expect((await consent(signedIn('POST', path, payload), none)).status, JSON.stringify(payload).slice(0, 80)).toBe(400);
    }
    vi.stubEnv('ASSISTANT_ACCOUNTS', otherUser);
    expect((await consent(signedIn('POST', path, { query, approve: true }), none)).status).toBe(403);
    vi.stubEnv('ASSISTANT_ACCOUNTS', '');
    expect((await consent(signedIn('POST', path, { query, approve: true }), none)).status).toBe(404);
    expect(open).toHaveBeenCalledTimes(1);
  });
});
