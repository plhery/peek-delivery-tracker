import { CLIENT_CAPABILITIES_META_KEY, LATEST_PROTOCOL_VERSION, PROTOCOL_VERSION_META_KEY } from '@modelcontextprotocol/server';
import { NextRequest } from 'next/server';
import { afterEach, beforeEach, describe, expect, it, vi } from 'vitest';
import { GET as authorizationServer } from '../../app/.well-known/oauth-authorization-server/route';
import { GET as mcpResource } from '../../app/.well-known/oauth-protected-resource/mcp/route';
import { GET as protectedResource } from '../../app/.well-known/oauth-protected-resource/route';
import { DELETE as deleteAccount } from '../../app/api/account/route';
import { DELETE as disconnect } from '../../app/api/account/assistants/[id]/route';
import { POST as consent } from '../../app/api/account/assistants/consent/route';
import { GET as readAssistants, POST as createToken } from '../../app/api/account/assistants/route';
import { GET as tokenParcels } from '../../app/api/parcels/route';
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
  vi.spyOn(SupabaseAuthenticator.prototype, 'validate').mockResolvedValue({
    id: userId, email: 'alex@example.com', authenticatedAt: new Date(), sessionId: '7d000000-0000-4000-a000-000000000001',
  });
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

  it('gives the newest scan as the status line, as the apps head the parcel, and the saved one for a parcel without scans', async () => {
    // Handed over: the saved summary is the delivery carrier's, older than the earlier carrier's latest scan.
    const handedOver = { ...stored(5, 'in_transit'), last_status_text: 'Announced to the delivery carrier' };
    const unscanned = { ...stored(6, 'pending'), last_status_text: 'Label created', tracking_events: [] };
    vi.spyOn(service, 'listAccountPackages').mockResolvedValue([handedOver, unscanned]);
    const answer = await call(connect(['parcels:read']), 'list_parcels', { status: 'all' });
    const parcels = answer.result!.structuredContent!.parcels as Array<{ id: string }>;
    expect(parcels.find((parcel) => parcel.id === handedOver.id)).toMatchObject({ statusText: 'Latest scan', lastUpdate: { description: 'Latest scan' } });
    expect(parcels.find((parcel) => parcel.id === unscanned.id)).toMatchObject({ statusText: 'Label created', lastUpdate: null });
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

  it('register 20 clients an hour from one IPv6 /48, whichever of its /64s they come from', async () => {
    vi.stubEnv('TRUST_PROXY_HEADERS', 'true');
    vi.spyOn(service, 'registerAssistantClient').mockResolvedValue(undefined);
    const from = (ip: string) => register(new NextRequest('https://peek.example/oauth/register', {
      method: 'POST', headers: { 'content-type': 'application/json', 'cf-connecting-ip': ip },
      body: JSON.stringify({ client_name: 'Example Assistant', redirect_uris: ['https://assistant.example.com/callback'] }),
    }), none);
    for (let index = 0; index < 20; index += 1) expect((await from(`2001:db8:4400:${index.toString(16)}::1`)).status).toBe(201);
    expect((await from('2001:db8:4400:ff::1')).status).toBe(429);
    expect((await from('2001:db8:4401::1')).status).toBe(201);
    expect((await from('192.0.2.10')).status).toBe(201);
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

describe('the parcels a token reads as JSON', () => {
  function read(secret: string | null, query = '') {
    const headers: Record<string, string> = secret ? { authorization: `Bearer ${secret}` } : {};
    return tokenParcels(new NextRequest(`https://peek.example/api/parcels${query}`, { headers }), none);
  }

  it('gives a read token what list_parcels gives, without the receiver\'s name or postcode', async () => {
    vi.spyOn(service, 'listAccountPackages').mockResolvedValue([
      stored(1, 'in_transit'), stored(2, 'delivered'), stored(3, 'in_transit', { archived: true }),
    ]);
    const secret = connect(['parcels:read']);
    const response = await read(secret);
    expect(response.status).toBe(200);
    expect(response.headers.get('cache-control')).toBe('no-store');
    const body = await response.json();
    expect(body).toEqual((await call(secret, 'list_parcels')).result!.structuredContent);
    expect(body).toMatchObject({ total: 1, parcels: [{ name: 'Synthetic parcel 1', carrier: 'UPS', status: 'in_transit' }] });
    const all = await (await read(secret, '?status=all&limit=2')).json();
    expect(all.total).toBe(3);
    expect(all.parcels).toHaveLength(2);
    for (const text of [JSON.stringify(body), JSON.stringify(all)]) {
      expect(text).not.toContain(RECEIVER);
      expect(text).not.toContain(POSTCODE);
    }
  });

  it('refuses a bad query, a missing or unknown token, and a token that may only write', async () => {
    vi.spyOn(service, 'listAccountPackages').mockResolvedValue([]);
    const reader = connect(['parcels:read']);
    for (const query of ['?status=lost', '?limit=0', '?limit=51', '?limit=2.5']) expect((await read(reader, query)).status, query).toBe(400);
    const anonymous = await read(null);
    expect(anonymous.status).toBe(401);
    expect(anonymous.headers.get('www-authenticate')).toContain('Bearer resource_metadata=');
    expect((await read(newSecret('access'))).status).toBe(401);
    const writer = await read(connect(['parcels:write']));
    expect(writer.status).toBe(403);
    expect(writer.headers.get('www-authenticate')).toBe('Bearer error="insufficient_scope", scope="parcels:read"');
    expect(service.listAccountPackages).not.toHaveBeenCalled();
  });

  it('shares each grant\'s 120 requests a minute with the MCP endpoint', async () => {
    // An account the setting is closed to answers fastest, and still counts.
    const secret = connect(['parcels:read'], otherUser);
    for (let index = 0; index < 120; index += 1) expect((await read(secret)).status).toBe(403);
    expect((await mcp(secret, 'tools/list')).status).toBe(429);
  });

  it('is not there when no account may connect', async () => {
    vi.stubEnv('ASSISTANT_ACCOUNTS', '');
    expect((await read(connect(['parcels:read']))).status).toBe(404);
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

  describe('with Auth asked about each token', () => {
    const START = Date.parse('2026-11-08T10:00:00Z');
    let issued = 0;

    /**
     * An access token as Auth issues them, unsigned: the fake Auth below answers for it. Each one is new, so no answer
     * is cached. Without a sign-in time its `amr` names none.
     */
    function accessToken(signedInAt: number | null, expiresAt: number): string {
      issued += 1;
      const part = (value: object) => Buffer.from(JSON.stringify(value)).toString('base64url');
      return [part({ alg: 'HS256', typ: 'JWT' }), part({
        sub: userId, exp: Math.floor(expiresAt / 1_000), jti: `synthetic-${issued}`,
        session_id: '7d000000-0000-4000-a000-000000000001',
        amr: signedInAt === null ? ['otp'] : [{ method: 'otp', timestamp: Math.floor(signedInAt / 1_000) }],
      }), 'signature'].join('.');
    }

    /** A refusal that asks for a sign-in now, which keeps the session, rather than one that ends it. */
    const signInAgain = (error: string) => ({ error, code: 'recent_sign_in' });

    /** Auth, accepting the tokens of sessions still open. */
    function authServer(open: Set<string>) {
      return vi.spyOn(globalThis, 'fetch').mockImplementation(async (input, init) => {
        expect(String(input)).toBe('https://database.example/auth/v1/user');
        const bearer = new Headers(init?.headers).get('authorization')?.replace(/^Bearer /, '') ?? '';
        return open.has(bearer)
          ? Response.json({ id: userId, email: 'alex@example.com', email_confirmed_at: '2026-11-01T00:00:00Z' })
          : Response.json({ msg: 'invalid JWT' }, { status: 401 });
      });
    }

    function withToken(token: string, method: string, path = '/api/account/assistants', body?: unknown) {
      return new NextRequest(`https://peek.example${path}`, {
        method,
        headers: { Authorization: `Bearer ${token}`, ...(body === undefined ? {} : { 'content-type': 'application/json' }) },
        ...(body === undefined ? {} : { body: JSON.stringify(body) }),
      });
    }

    beforeEach(() => {
      vi.mocked(SupabaseAuthenticator.prototype.validate).mockRestore();
      vi.useFakeTimers({ toFake: ['Date'] });
      vi.setSystemTime(START);
    });
    afterEach(() => { vi.useRealTimers(); });

    it('serve a read from a checked token only until the token expires', async () => {
      vi.spyOn(service, 'assistantGrants').mockResolvedValue([connection]);
      const token = accessToken(START - 3_600_000, START + 30_000);
      const open = new Set([token]);
      const auth = authServer(open);
      expect((await readAssistants(withToken(token, 'GET'), none)).status).toBe(200);
      // Signed out elsewhere: the answer Auth gave holds while the token lasts…
      open.delete(token);
      vi.setSystemTime(START + 29_000);
      expect((await readAssistants(withToken(token, 'GET'), none)).status).toBe(200);
      expect(auth).toHaveBeenCalledOnce();
      // …and not past its expiry, inside the minute or not.
      vi.setSystemTime(START + 31_000);
      expect((await readAssistants(withToken(token, 'GET'), none)).status).toBe(401);
      expect(auth).toHaveBeenCalledTimes(2);
    });

    it('make a personal token only for a session Auth confirms now, signed in within ten minutes', async () => {
      const create = vi.spyOn(service, 'createPersonalToken').mockResolvedValue(connection);
      const make = (token: string) => createToken(withToken(token, 'POST', undefined, { name: 'Home script', scopes: ['parcels:read'] }), none);
      const recent = accessToken(START - 60_000, START + 3_600_000);
      const old = accessToken(START - 11 * 60_000, START + 3_600_000);
      const brief = accessToken(START - 60_000, START + 30_000);
      const open = new Set([recent, old, brief]);
      const auth = authServer(open);

      expect((await make(recent)).status).toBe(201);
      // Once for the request, from then on from what Auth said, and once afresh for the token.
      expect(auth).toHaveBeenCalledTimes(2);
      open.delete(recent);
      vi.setSystemTime(START + 5_000);
      const signedOut = await make(recent);
      expect(signedOut.status).toBe(401);
      expect(await signedOut.json()).toEqual({ error: 'Sign in again before making a personal token' });
      const stale = await make(old);
      expect(stale.status).toBe(401);
      expect(await stale.json()).toEqual(signInAgain('Sign in again before making a personal token'));
      // A token that says no sign-in time can't show a recent one either.
      const undated = accessToken(null, START + 3_600_000);
      open.add(undated);
      expect(await (await make(undated)).json()).toEqual(signInAgain('Sign in again before making a personal token'));

      expect((await make(brief)).status).toBe(201);
      open.delete(brief);
      vi.setSystemTime(START + 31_000);
      expect((await make(brief)).status).toBe(401);
      expect(create).toHaveBeenCalledTimes(2);
    });

    it('grant an assistant only for a session Auth confirms now, signed in within ten minutes to allow', async () => {
      const client = { id: newClientId(), name: 'Example Assistant', uri: null, redirectUris: ['https://assistant.example.com/callback'] };
      vi.spyOn(service, 'assistantClient').mockResolvedValue(client);
      const grant = vi.spyOn(service, 'openAssistantCode').mockResolvedValue(undefined);
      const query = new URLSearchParams({
        response_type: 'code', client_id: client.id, code_challenge: pkceChallenge('v'.repeat(64)), code_challenge_method: 'S256', state: 'state-123',
      }).toString();
      const answer = (token: string, approve: boolean) => consent(withToken(token, 'POST', '/api/account/assistants/consent', { query, approve, scopes: ['parcels:read'] }), none);
      const recent = accessToken(START - 60_000, START + 3_600_000);
      const old = accessToken(START - 11 * 60_000, START + 3_600_000);
      const brief = accessToken(START - 60_000, START + 30_000);
      const open = new Set([recent, old, brief]);
      authServer(open);

      expect((await answer(recent, true)).status).toBe(200);
      open.delete(recent);
      vi.setSystemTime(START + 5_000);
      const signedOut = await answer(recent, true);
      expect(signedOut.status).toBe(401);
      expect(await signedOut.json()).toEqual({ error: 'Sign in again before connecting an assistant' });
      // Refusing grants nothing: it needs a session Auth confirms, however old its sign-in.
      const ended = await answer(recent, false);
      expect(ended.status).toBe(401);
      expect(await ended.json()).toEqual({ error: 'Authentication is required' });
      const refused = await answer(old, false);
      expect(refused.status).toBe(200);
      expect(new URL((await refused.json()).redirect).searchParams.get('error')).toBe('access_denied');
      const stale = await answer(old, true);
      expect(stale.status).toBe(401);
      expect(await stale.json()).toEqual(signInAgain('Sign in again before connecting an assistant'));

      expect((await answer(brief, true)).status).toBe(200);
      open.delete(brief);
      vi.setSystemTime(START + 31_000);
      expect((await answer(brief, true)).status).toBe(401);
      expect(grant).toHaveBeenCalledTimes(2);
    });

    it('delete the account only for a session Auth confirms now, signed in within ten minutes', async () => {
      const deleted = vi.spyOn(service, 'deleteAuthUser').mockResolvedValue(undefined);
      vi.spyOn(service, 'appleSubjects').mockResolvedValue([]);
      const remove = (token: string) => deleteAccount(withToken(token, 'DELETE', '/api/account', { confirmation: 'Alex@Example.com' }), none);
      const recent = accessToken(START - 60_000, START + 3_600_000);
      const old = accessToken(START - 11 * 60_000, START + 3_600_000);
      const ahead = accessToken(START + 2 * 60_000, START + 3_600_000);
      const ending = accessToken(START - 60_000, START + 3_600_000);
      const open = new Set([recent, old, ahead, ending]);
      authServer(open);

      // An older sign-in, or one ahead of this server's clock, keeps its session and is asked for a new one; the
      // iPhone and Android apps read any 401 alike.
      for (const token of [old, ahead]) {
        const stale = await remove(token);
        expect(stale.status).toBe(401);
        expect(await stale.json()).toEqual(signInAgain('Sign in again before permanently deleting your account'));
      }
      // A session ended since its token was checked is refused plainly, which signs out.
      const mistyped = withToken(ending, 'DELETE', '/api/account', { confirmation: 'someone@else.example' });
      expect((await deleteAccount(mistyped, none)).status).toBe(400);
      open.delete(ending);
      vi.setSystemTime(START + 5_000);
      const signedOut = await remove(ending);
      expect(signedOut.status).toBe(401);
      expect(await signedOut.json()).toEqual({ error: 'Sign in again before permanently deleting your account' });
      expect(deleted).not.toHaveBeenCalled();

      expect((await remove(recent)).status).toBe(200);
      expect(deleted).toHaveBeenCalledExactlyOnceWith(userId);
    });
  });
});
