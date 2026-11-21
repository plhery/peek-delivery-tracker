import { CLIENT_CAPABILITIES_META_KEY, LATEST_PROTOCOL_VERSION, PROTOCOL_VERSION_META_KEY } from '@modelcontextprotocol/server';
import { NextRequest } from 'next/server';
import { afterEach, beforeEach, describe, expect, it, onTestFinished, vi } from 'vitest';
import { GET as authorizationServer } from '../../app/.well-known/oauth-authorization-server/route';
import { GET as mcpResource } from '../../app/.well-known/oauth-protected-resource/mcp/route';
import { GET as protectedResource } from '../../app/.well-known/oauth-protected-resource/route';
import { DELETE as deleteAccount } from '../../app/api/account/route';
import { DELETE as disconnect } from '../../app/api/account/assistants/[id]/route';
import { POST as consent } from '../../app/api/account/assistants/consent/route';
import { GET as readAssistants, POST as createToken } from '../../app/api/account/assistants/route';
import * as tokenApi from '../../app/api/parcels/route';
import { GET as tokenParcels } from '../../app/api/parcels/route';
import { DELETE as mcpDelete, GET as mcpGet, POST as mcpPost } from '../../app/mcp/route';
import { GET as homeAssistant } from '../../app/oauth/clients/home-assistant.json/route';
import { POST as register } from '../../app/oauth/register/route';
import { POST as revoke } from '../../app/oauth/revoke/route';
import { POST as token } from '../../app/oauth/token/route';
import { guideHref } from '../guides/paths';
import { AMAZON_ACCOUNT_MESSAGE, AMAZON_GUIDE_URL } from '../lib/amazon';
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

function connect(scopes: Scope[], user = userId, kind: 'access' | 'personal' = 'access'): string {
  grants += 1;
  const secret = newSecret(kind);
  owners.set(secretDigest(secret), {
    userId: user,
    grantId: `6c000000-0000-4000-a000-${String(grants).padStart(12, '0')}`,
    scopes,
    kind,
    clientId: kind === 'access' ? newClientId() : null,
    expiresAt: kind === 'access' ? Math.floor(Date.now() / 1_000) + 3_600 : null,
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
  // The account's days are this machine's, as `dayFromToday` counts them.
  vi.spyOn(service, 'accountTimeZone').mockResolvedValue(Intl.DateTimeFormat().resolvedOptions().timeZone);
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
function stored(index: number, stage: string, options: {
  archived?: boolean; trackingNumber?: string; label?: string; sender?: string; expected?: string; latest?: string;
} = {}) {
  const id = `7d000000-0000-4000-a000-${String(index).padStart(12, '0')}`;
  return {
    id,
    tracking_number: options.trackingNumber ?? `SYNTHETIC${index}`,
    label: options.label ?? `Synthetic parcel ${index}`,
    carrier: 'ups',
    created_at: ADDED,
    sync_status: 'ok',
    archived_at: options.archived ? ago(2) : null,
    expected_delivery: options.expected ?? null,
    dpd_postcode: POSTCODE,
    carrier_data: { receiver_name: RECEIVER, sender_name: options.sender ?? 'Example Shop' },
    tracking_events: [
      { id: `${id}-0`, package_id: id, stage: 'accepted', description: 'Parcel received', location: 'Example Depot', occurred_at: ACCEPTED, provider_event_id: `carrier:${index}:0` },
      { id: `${id}-1`, package_id: id, stage, description: 'Latest scan', location: 'Example Hub', occurred_at: options.latest ?? LATEST, provider_event_id: `carrier:${index}:1` },
    ],
  };
}

/** A day from today on this machine's clock, as the `today` and `week` lists read it for the account. */
function dayFromToday(days: number): string {
  const date = new Date(Date.now() + days * 86_400_000);
  return `${date.getFullYear()}-${String(date.getMonth() + 1).padStart(2, '0')}-${String(date.getDate()).padStart(2, '0')}`;
}

describe('the MCP endpoint', () => {
  it('asks for a token, and says when the one given is unknown', async () => {
    const challenge = 'Bearer resource_metadata="https://peek.example/.well-known/oauth-protected-resource/mcp", scope="parcels:read parcels:write parcels:edit"';
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

  it('still serves a token issued or found valid lately while its shared network waits', async () => {
    vi.stubEnv('TRUST_PROXY_HEADERS', 'true');
    const shared = { 'cf-connecting-ip': '203.0.113.20' };
    const seen = connect(['parcels:read']);
    expect((await mcp(seen, 'tools/list', {}, 'legacy', shared)).status).toBe(200);
    vi.spyOn(service, 'redeemAssistantCode').mockResolvedValue({ userId, grantId: '6c000000-0000-4000-a000-0000000000f4', scopes: ['parcels:read'] });
    const issued = await (await token(new NextRequest('https://peek.example/oauth/token', {
      method: 'POST',
      headers: { 'content-type': 'application/x-www-form-urlencoded' },
      body: new URLSearchParams({ grant_type: 'authorization_code', code: newSecret('code'), redirect_uri: 'https://assistant.example.com/callback', code_verifier: 'v'.repeat(64), client_id: newClientId() }).toString(),
    }), none)).json() as { access_token: string };
    owners.set(secretDigest(issued.access_token), {
      userId, grantId: '6c000000-0000-4000-a000-0000000000f4', scopes: ['parcels:read'], kind: 'access', clientId: newClientId(), expiresAt: Math.floor(Date.now() / 1_000) + 3_600,
    });

    for (let index = 0; index <= 600; index += 1) await mcp(newSecret('access'), 'tools/list', {}, 'legacy', shared);
    expect((await mcp(connect(['parcels:read']), 'tools/list', {}, 'legacy', shared)).status).toBe(429);
    expect((await mcp(seen, 'tools/list', {}, 'legacy', shared)).status).toBe(200);
    expect((await mcp(issued.access_token, 'tools/list', {}, 'legacy', shared)).status).toBe(200);
    // Revoked since, it is looked up and refused.
    owners.delete(secretDigest(seen));
    expect((await mcp(seen, 'tools/list', {}, 'legacy', shared)).status).toBe(401);
  });

  it('counts an IPv6 /48\'s unknown tokens together, whichever of its /64s they come from', async () => {
    vi.stubEnv('TRUST_PROXY_HEADERS', 'true');
    const from = (ip: string) => ({ 'cf-connecting-ip': ip });
    for (let index = 0; index < 600; index += 1) {
      expect((await mcp(newSecret('access'), 'tools/list', {}, 'legacy', from(`2001:db8:5500:${index.toString(16)}::1`))).status).toBe(401);
    }
    expect((await mcp(newSecret('access'), 'tools/list', {}, 'legacy', from('2001:db8:5500:ffff::1'))).status).toBe(401);
    expect((await mcp(connect(['parcels:read']), 'tools/list', {}, 'legacy', from('2001:db8:5500:fffe::1'))).status).toBe(429);
    expect((await mcp(connect(['parcels:read']), 'tools/list', {}, 'legacy', from('2001:db8:5501::1'))).status).toBe(200);
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

  it('takes a personal token only while personal tokens are on', async () => {
    const personal = connect(['parcels:read'], userId, 'personal');
    const refused = await mcp(personal, 'tools/list');
    expect(refused.status).toBe(401);
    expect(refused.headers.get('www-authenticate')).toContain('error="invalid_token"');
    vi.stubEnv('PERSONAL_TOKENS', 'on');
    expect((await mcp(personal, 'tools/list')).status).toBe(200);
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

  it('lists every tool to every grant, each request on its own, each naming the scope it needs', async () => {
    const reader = connect(['parcels:read']);
    const writer = connect(['parcels:read', 'parcels:write']);
    const started = await mcp(reader, 'initialize', { protocolVersion: LATEST_PROTOCOL_VERSION, capabilities: {}, clientInfo: { name: 'example-client', version: '1.0.0' } });
    expect(started.status).toBe(200);
    const initialized = (await rpc(started)).result!;
    // The tool list never changes, so a client has no stream to open for it: Peek refuses `subscriptions/listen`.
    expect(initialized).toMatchObject({ protocolVersion: LATEST_PROTOCOL_VERSION, serverInfo: { name: 'peek' }, capabilities: { tools: { listChanged: false } } });
    const discovered = await mcp(reader, 'server/discover', {}, 'modern');
    expect(discovered.status).toBe(200);
    expect((await rpc(discovered)).result).toMatchObject({ capabilities: { tools: { listChanged: false } } });
    // The instructions send the model to Peek's own tools alone, never to another connector.
    expect(initialized.instructions).toContain('add_parcels');
    expect(initialized.instructions).not.toMatch(/gmail|connector|plugin/i);
    // An Amazon import takes a browser on the user's own computer, signed in to Amazon: nothing else reaches the page,
    // and an assistant without one says so instead of trying a browser of its own or the user's emails.
    expect(initialized.instructions).toContain('only inside the user\'s Amazon account, under Your Orders, then Track package');
    expect(initialized.instructions).toContain('don\'t search the user\'s emails for Amazon orders');
    expect(initialized.instructions).toContain('only in a browser on the user\'s own computer where they are signed in to Amazon: Claude in Chrome, the ChatGPT browser extension');
    expect(initialized.instructions).toContain('A browser that runs elsewhere (a cloud, agent or sandbox browser), web search and page fetching can\'t');
    expect(initialized.instructions).toContain('Without such a browser, as in a phone app, don\'t start. Tell the user, in their language, that you can\'t reach their Amazon account from here');
    expect(initialized.instructions).toContain('paste each tracking ID to you or into Peek');
    // An assistant that browses the user's Amazon site reads each shipment's carrier and tracking ID, and changes nothing.
    expect(initialized.instructions).toContain('the Amazon site the user uses (amazon.fr, .de, .it, .es, .co.uk, .nl, .com and so on: every Amazon site works the same way)');
    expect(initialized.instructions).toContain('at /gp/css/order-history (on amazon.de, for example,');
    expect(initialized.instructions).toContain('If Amazon asks to sign in, stop and ask the user to sign in there themselves');
    // Amazon names the carrier by a name or a code: the assistant gives its id when Peek can't tell the number's shape.
    expect(initialized.instructions).toContain('look the carrier up with find_carriers, by the name Amazon writes, which may be a code such as VIR_TR');
    expect(initialized.instructions).toContain('Each package on its way or just delivered has its own Track package button');
    expect(initialized.instructions).toContain('That page names the carrier after "Delivery By" and shows the "Tracking ID", with labels in the site\'s language');
    expect(initialized.instructions).toMatch(/order number .* is never one: never send it/);
    expect(initialized.instructions).toContain('tell the user Amazon tracks that one only in their Amazon account');
    expect(initialized.instructions).toContain('On Amazon, only read: never buy, cancel, return or change anything');
    expect(initialized.instructions).toContain('never sign in for the user or ask for their Amazon password');
    const listed = async (secret: string, era?: 'legacy' | 'modern') => {
      const response = await mcp(secret, 'tools/list', {}, era);
      expect(response.status).toBe(200);
      const tools = (await rpc(response)).result!.tools! as Array<{ name: string; _meta?: { securitySchemes?: unknown } }>;
      return Object.fromEntries(tools.map(({ name, _meta }) => [name, _meta?.securitySchemes]));
    };
    const write = [{ type: 'oauth2', scopes: ['parcels:write'] }];
    const edit = [{ type: 'oauth2', scopes: ['parcels:edit'] }];
    const read = [{ type: 'oauth2', scopes: ['parcels:read'] }];
    const schemes = {
      add_parcels: write,
      amazon_import_steps: write,
      rename_parcel: edit,
      change_carrier: edit,
      set_parcel_notifications: edit,
      archive_parcel: write,
      restore_parcel: edit,
      get_parcel: read,
      list_parcels: read,
      find_carriers: read,
    };
    expect(await listed(reader)).toEqual(schemes);
    expect(await listed(writer)).toEqual(schemes);
    expect(await listed(reader, 'modern')).toEqual(schemes);
  });

  it('can archive a parcel but never delete one: these tools, the ones that overwrite a value marked so, and a token API that only reads', async () => {
    const response = await mcp(connect(['parcels:read', 'parcels:write']), 'tools/list');
    const tools = (await rpc(response)).result!.tools! as Array<{
      name: string; annotations?: { readOnlyHint?: boolean; destructiveHint?: boolean; idempotentHint?: boolean };
    }>;
    expect(tools.map(({ name }) => name).sort()).toEqual([
      'add_parcels', 'amazon_import_steps', 'archive_parcel', 'change_carrier', 'find_carriers', 'get_parcel', 'list_parcels',
      'rename_parcel', 'restore_parcel', 'set_parcel_notifications',
    ]);
    // A new name or carrier replaces the old one; nothing else is lost, and every call may be repeated.
    expect(tools.filter(({ annotations }) => annotations?.destructiveHint !== false).map(({ name }) => name).sort())
      .toEqual(['change_carrier', 'rename_parcel']);
    expect(tools.filter(({ annotations }) => annotations?.readOnlyHint).map(({ name }) => name).sort())
      .toEqual(['amazon_import_steps', 'find_carriers', 'get_parcel', 'list_parcels']);
    for (const { annotations } of tools) expect(annotations?.idempotentHint).toBe(true);
    expect(Object.keys(tokenApi).filter((name) => /^[A-Z]+$/.test(name))).toEqual(['GET']);
  });

  it('takes one message a request: no batch of calls, and no stream to listen on', async () => {
    const list = vi.spyOn(service, 'listAccountPackages').mockResolvedValue([]);
    const secret = connect(['parcels:read']);
    const batch = (size: number) => mcpPost(new NextRequest('https://peek.example/mcp', {
      method: 'POST',
      headers: { accept: 'application/json, text/event-stream', 'content-type': 'application/json', 'mcp-protocol-version': LATEST_PROTOCOL_VERSION, authorization: `Bearer ${secret}` },
      body: JSON.stringify(Array.from({ length: size }, (_, index) => ({ jsonrpc: '2.0', id: index + 1, method: 'tools/call', params: { name: 'list_parcels', arguments: {} } }))),
    }), none);
    const refused = await batch(100);
    expect(refused.status).toBe(400);
    expect(await refused.json()).toMatchObject({ jsonrpc: '2.0', id: null, error: { code: -32600 } });
    expect(list).not.toHaveBeenCalled();

    const listen = await mcp(secret, 'subscriptions/listen', { notifications: { toolsListChanged: true } }, 'modern');
    expect(listen.status).toBe(400);
    expect(listen.headers.get('content-type')).toMatch(/^application\/json/);
    expect((await listen.json()).error).toMatchObject({ code: -32601 });

    // One message still goes through, whatever it holds.
    expect((await call(secret, 'list_parcels')).result!.structuredContent).toMatchObject({ total: 0 });
    expect(list).toHaveBeenCalledTimes(1);
    const broken = await mcpPost(new NextRequest('https://peek.example/mcp', {
      method: 'POST',
      headers: { accept: 'application/json, text/event-stream', 'content-type': 'application/json', 'mcp-protocol-version': LATEST_PROTOCOL_VERSION, authorization: `Bearer ${secret}` },
      body: '{"jsonrpc":',
    }), none);
    expect(broken.status).toBe(400);
    expect((await broken.json()).error).toMatchObject({ code: -32700 });
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
      status: 'active',
      sort: 'expected',
      parcels: [{
        id: '7d000000-0000-4000-a000-000000000001',
        name: 'Synthetic parcel 1',
        carrier: 'UPS',
        trackingNumber: 'SYNTHETIC1',
        status: 'in_transit',
        statusText: 'Latest scan',
        needsAttention: null,
        expectedDelivery: null,
        deliveredAt: null,
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

  it('says when the carrier picked by hand refuses the number and another carrier has its news', async () => {
    const picked = { ...stored(1, 'in_transit'), carrier: 'inpost', carrier_data: { sender_name: 'Example Shop',
      active_tracking_carrier: 'aliexpress', routing: { served_elsewhere: { picked: 'inpost', carrier: 'aliexpress' } } } };
    // Picked again since: the warning was of the carrier picked before.
    const moved = { ...stored(2, 'in_transit'), carrier: 'dpd', carrier_data: { routing: { served_elsewhere: { picked: 'inpost', carrier: 'aliexpress' } } } };
    vi.spyOn(service, 'listAccountPackages').mockResolvedValue([picked, moved]);
    const answer = await call(connect(['parcels:read']), 'list_parcels');
    const parcels = (answer.result!.structuredContent as { parcels: Record<string, unknown>[] }).parcels;
    expect(parcels.find((parcel) => parcel.id === picked.id)).toMatchObject({ carrier: 'InPost',
      carrierWarning: 'You picked InPost, but only AliExpress / Cainiao has news about this parcel.' });
    expect(parcels.find((parcel) => parcel.id === moved.id)).not.toHaveProperty('carrierWarning');
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

  it('answers "my latest delivered parcel", "what arrives this week" and "the one from a shop" in one call each', async () => {
    const parcels = [
      stored(11, 'delivered', { latest: ago(30) }),
      // Archived by hand after its delivery, which is the latest.
      stored(12, 'delivered', { latest: ago(5), archived: true, label: 'Synthetic lamp' }),
      stored(13, 'in_transit', { expected: dayFromToday(2), sender: 'Synthetic Furniture' }),
      stored(14, 'in_transit', { expected: dayFromToday(10), latest: ago(3) }),
      stored(15, 'out_for_delivery', { expected: dayFromToday(0), latest: ago(2) }),
      stored(16, 'in_transit', { expected: dayFromToday(-1) }),
    ];
    vi.spyOn(service, 'listAccountPackages').mockResolvedValue(parcels);
    const secret = connect(['parcels:read']);
    const listed = async (args: Record<string, unknown>) => {
      const content = (await call(secret, 'list_parcels', args)).result!.structuredContent as {
        total: number; status: string; sort: string; parcels: Array<{ id: string; deliveredAt: string | null; archived: boolean }>;
      };
      return { ...content, ids: content.parcels.map(({ id }) => Number(id.slice(-2))) };
    };

    const latest = await listed({ status: 'delivered', limit: 1 });
    expect(latest).toMatchObject({ total: 2, status: 'delivered', sort: 'delivered', ids: [12] });
    expect(latest.parcels[0]).toMatchObject({ deliveredAt: parcels[1]!.tracking_events[1]!.occurred_at, archived: true });
    expect(await listed({ status: 'week' })).toMatchObject({ total: 2, sort: 'expected', ids: [15, 13] });
    expect(await listed({ status: 'today' })).toMatchObject({ ids: [15] });
    expect(await listed({ search: 'furniture' })).toMatchObject({ status: 'all', sort: 'updated', ids: [13] });
    expect(await listed({ search: 'LAMP' })).toMatchObject({ ids: [12] });
    expect(await listed({ search: 'lamp', status: 'active' })).toMatchObject({ total: 0, ids: [] });
    expect((await listed({ status: 'all', sort: 'delivered' })).ids.slice(0, 2)).toEqual([12, 11]);
    expect((await listed({ sort: 'updated' })).ids).toEqual([13, 16, 15, 14]);
    expect((await call(secret, 'list_parcels', { sort: 'cheapest' })).result).toMatchObject({ isError: true });
  });

  it('reads today and the week in the user\'s timezone: the one given, else the account\'s', async () => {
    // 20:30 in Zurich on 18 November is already the 19th in Auckland, and the 25th is the last day of Auckland's week.
    vi.useFakeTimers({ toFake: ['Date'] });
    vi.setSystemTime(new Date('2026-11-18T19:30:00Z'));
    onTestFinished(() => { vi.useRealTimers(); });
    vi.spyOn(service, 'listAccountPackages').mockResolvedValue([
      stored(21, 'in_transit', { expected: '2026-11-18' }),
      stored(22, 'in_transit', { expected: '2026-11-19' }),
      stored(23, 'in_transit', { expected: '2026-11-25' }),
    ]);
    const zone = vi.spyOn(service, 'accountTimeZone').mockResolvedValue(null);
    const secret = connect(['parcels:read']);
    const ids = async (args: Record<string, unknown>) => ((await call(secret, 'list_parcels', args)).result!.structuredContent!.parcels as Array<{ id: string }>)
      .map(({ id }) => Number(id.slice(-2)));

    expect(await ids({ status: 'today' })).toEqual([21]);
    expect(await ids({ status: 'week' })).toEqual([21, 22]);
    expect(zone).toHaveBeenCalledWith(userId);
    zone.mockResolvedValue('Pacific/Auckland');
    expect(await ids({ status: 'today' })).toEqual([22]);
    expect(await ids({ status: 'week' })).toEqual([22, 23]);
    zone.mockClear();
    expect(await ids({ status: 'today', timeZone: 'Europe/Zurich' })).toEqual([21]);
    expect(zone).not.toHaveBeenCalled();
    expect((await call(secret, 'list_parcels', { status: 'today', timeZone: 'Mars/Olympus' })).result).toMatchObject({
      isError: true, content: [{ text: expect.stringContaining('timezone') }],
    });
  });

  it('reads the scans as a link\'s viewer does: no recipient, signatory, code or door in any text', async () => {
    // Synthetic wording: a delivery that names who took it, a scan and a place that name the receiver, a status line
    // that names the signatory, and a scan with a collection code.
    const base = stored(7, 'in_transit');
    const named = { ...base, last_status_text: 'Handed to Synthetic Signatory',
      carrier_data: { ...base.carrier_data, signed_by: 'Synthetic Signatory' },
      tracking_events: [
        ...base.tracking_events,
        { ...base.tracking_events[1]!, id: `${base.id}-2`, description: `Notice left for ${RECEIVER}`, occurred_at: ago(30), provider_event_id: 'carrier:7:2' },
        { ...base.tracking_events[1]!, id: `${base.id}-3`, description: 'Sorted', location: `${RECEIVER}'s street`, occurred_at: ago(20), provider_event_id: 'carrier:7:3' },
        { ...base.tracking_events[1]!, id: `${base.id}-4`, description: 'Ready, collection code 4815', occurred_at: ago(10), provider_event_id: 'carrier:7:4' },
      ] };
    const delivered = stored(8, 'delivered');
    delivered.tracking_events[1] = { ...delivered.tracking_events[1]!, description: 'Delivered, signed by Synthetic Signatory' };
    vi.spyOn(service, 'listAccountPackages').mockResolvedValue([named, delivered]);
    vi.spyOn(service, 'getOwnedPackage').mockImplementation(async (id) => (id === named.id ? named : null));
    const secret = connect(['parcels:read']);
    const list = await call(secret, 'list_parcels', { status: 'all' });
    const parcels = list.result!.structuredContent!.parcels as Array<{ id: string }>;
    expect(parcels.find((parcel) => parcel.id === delivered.id)).toMatchObject({ statusText: 'Delivered', lastUpdate: { description: 'Delivered' } });
    expect(parcels.find((parcel) => parcel.id === named.id)).toMatchObject({ statusText: 'Latest scan' });
    const read = await call(secret, 'get_parcel', { parcel: named.id });
    // A scan that names them reads its stage's name instead, without its place.
    expect(read.result!.structuredContent!.history).toEqual([
      { time: LATEST, status: 'in_transit', description: 'Latest scan', location: 'Example Hub' },
      { time: expect.any(String), status: 'in_transit', description: 'In transit', location: null },
      { time: expect.any(String), status: 'in_transit', description: 'Sorted', location: null },
      { time: expect.any(String), status: 'in_transit', description: 'In transit', location: null },
      { time: ACCEPTED, status: 'accepted', description: 'Parcel received', location: 'Example Depot' },
    ]);
    for (const text of [JSON.stringify(list), JSON.stringify(read)]) {
      expect(text).not.toContain(RECEIVER);
      expect(text).not.toContain('Signatory');
      expect(text).not.toContain('4815');
    }
  });

  it('keeps a scan that names a code in the status and what the parcel needs, in words of its stage', async () => {
    // Synthetic wording: the newest scan says the parcel waits at a pickup point, with the code that collects it.
    const base = stored(17, 'in_transit', { trackingNumber: UPS });
    const waiting = { ...base, current_stage: 'ready_for_pickup', last_status_text: 'Ready, collection code 4815',
      tracking_events: [
        ...base.tracking_events,
        { ...base.tracking_events[1]!, id: `${base.id}-2`, stage: 'ready_for_pickup', description: 'Ready, collection code 4815',
          location: 'Example Locker', occurred_at: ago(0.5), provider_event_id: 'carrier:17:2' },
      ] };
    vi.spyOn(service, 'listAccountPackages').mockResolvedValue([waiting]);
    ownParcel(waiting);
    const secret = connect(['parcels:read']);

    const attention = await call(secret, 'list_parcels', { status: 'attention' });
    expect(attention.result!.structuredContent).toMatchObject({ total: 1, parcels: [{ id: waiting.id, status: 'ready_for_pickup', needsAttention: expect.anything() }] });
    const read = await call(secret, 'get_parcel', { parcel: UPS });
    expect(read.result!.structuredContent).toMatchObject({
      status: 'ready_for_pickup', statusText: 'Ready for pickup',
      lastUpdate: { description: 'Ready for pickup', location: null },
    });
    expect((read.result!.structuredContent!.history as Array<{ status: string; description: string }>)[0])
      .toMatchObject({ status: 'ready_for_pickup', description: 'Ready for pickup', location: null });
    for (const text of [JSON.stringify(attention), JSON.stringify(read)]) expect(text).not.toContain('4815');
  });

  it('reads a scan the earlier carrier also told once, its other words as also', async () => {
    // A handed-over parcel: the earlier carrier tells the new carrier's scans in its own language, at the same second
    // and a few seconds off, and once a few seconds off in words that show no language, which may be a scan of its own.
    // Synthetic wording.
    const minute = Math.floor(Date.now() / 60_000) * 60_000;
    const at = (hours: number, second: number) => new Date(minute - hours * 3_600_000 + second * 1_000).toISOString();
    const base = stored(9, 'in_transit');
    const event = (id: number, source: string, description: string, occurred: string, location: string | null) => ({
      id: `${base.id}-${id}`, package_id: base.id, stage: 'in_transit', description, location, occurred_at: occurred, provider_event_id: `${source}:9:${id}`,
    });
    const handed = { ...base, carrier: 'yanwen',
      carrier_data: { ...base.carrier_data, original_carrier: 'yanwen', active_tracking_carrier: 'la-poste' },
      tracking_events: [
        { ...base.tracking_events[0]!, provider_event_id: 'yanwen:9:0' },
        event(2, 'la-poste', 'Votre colis est arrivé sur notre site', at(5, 5), 'Example Hub'),
        event(3, 'yanwen', 'Arrived at the delivery facility', at(5, 5), 'Example Hub'),
        event(4, 'la-poste', 'Votre colis est en cours d\'acheminement', at(2, 20), 'Example Hub'),
        event(5, 'yanwen', 'Parcel departed the facility', at(2, 41), null),
        event(6, 'la-poste', 'Votre colis est en cours de traitement', at(3, 10), 'Example Hub'),
        event(7, 'yanwen', 'In transit', at(3, 30), null),
      ] };
    vi.spyOn(service, 'getOwnedPackage').mockImplementation(async (id) => (id === handed.id ? handed : null));
    const read = await call(connect(['parcels:read']), 'get_parcel', { parcel: handed.id });
    expect(read.result!.structuredContent!.history).toEqual([
      { time: at(2, 20), status: 'in_transit', description: 'Votre colis est en cours d\'acheminement', location: 'Example Hub' },
      { time: at(3, 10), status: 'in_transit', description: 'Votre colis est en cours de traitement', location: 'Example Hub', also: ['In transit'] },
      { time: at(5, 5), status: 'in_transit', description: 'Votre colis est arrivé sur notre site', location: 'Example Hub' },
      { time: ACCEPTED, status: 'accepted', description: 'Parcel received', location: 'Example Depot' },
    ]);
  });

  it('adds parcels from a text for the caller\'s own account, with the write scope only', async () => {
    vi.mocked(addParcelsFromText).mockResolvedValue([
      { trackingNumber: UPS, carrier: 'ups', outcome: 'added', packageId: '7d000000-0000-4000-a000-0000000000aa', jobId: null },
      { trackingNumber: 'SYNTHETIC2', carrier: 'ups', outcome: 'refused', reason: 'not followed' },
    ]);
    const writer = connect(['parcels:write']);
    const answer = await call(writer, 'add_parcels', { text: `Shipped with UPS: ${UPS}`, name: '  Example Shop ' });
    // A text an assistant sends may be a number alone, as the Add sheet takes a typed one.
    expect(addParcelsFromText).toHaveBeenCalledExactlyOnceWith(expect.any(SupabaseServiceClient), userId, `Shipped with UPS: ${UPS}`, 'Example Shop', [], { trust: 'alone' });
    expect(answer.result!.structuredContent).toEqual({
      parcels: [
        { trackingNumber: UPS, carrier: 'UPS', outcome: 'added', id: '7d000000-0000-4000-a000-0000000000aa' },
        { trackingNumber: 'SYNTHETIC2', carrier: 'UPS', outcome: 'refused', reason: 'not followed' },
      ],
    });
    expect(answer.result!.content![0]!.text).toContain(`${UPS} (UPS): added`);

    expect(addParcelsFromText).toHaveBeenCalledTimes(1);
  });

  it('tells the assistant where an Amazon order\'s tracking number is, and never to send its order number', async () => {
    const writer = connect(['parcels:write']);
    const tools = (await rpc(await mcp(writer, 'tools/list'))).result!.tools! as Array<{ name: string; description: string }>;
    // For a client that keeps the tools but not the instructions: what the import takes, and the tool that says the rest.
    const description = tools.find(({ name }) => name === 'add_parcels')!.description;
    expect(description).toContain('Never send an Amazon order number, and don\'t search the user\'s emails for Amazon orders');
    expect(description).toContain('Your Orders, then Track package');
    expect(description).toContain('only in a browser on the user\'s own computer where they are signed in to Amazon');
    expect(description).toContain('Call amazon_import_steps before you try');
    vi.spyOn(service, 'getAuthAccount').mockResolvedValue({ email: null, emailConfirmed: false, locale: null });
    vi.mocked(addParcelsFromText).mockResolvedValue([]);
    const none = await call(writer, 'add_parcels', { text: 'Your Example Shop order has shipped.' });
    expect(none.result!.structuredContent).toEqual({ parcels: [] });
    const text = none.result!.content![0]!.text;
    expect(text).toContain('Amazon account: Your Orders, then Track package');
    expect(text).toContain('find its id with find_carriers and send the tracking number again, alone, with that id as the carrier');
    expect(text).toContain(`Peek's guide shows where: ${AMAZON_GUIDE_URL}`);
    expect(text).not.toContain('Claude in Chrome');
  });

  it('answers a text about an Amazon order that holds no number with what the import takes', async () => {
    vi.spyOn(service, 'getAuthAccount').mockResolvedValue({ email: null, emailConfirmed: false, locale: null });
    vi.mocked(addParcelsFromText).mockResolvedValue([]);
    const writer = connect(['parcels:write']);
    // An Amazon email, or its order number, in the shape Amazon's help gives: made-up digits.
    for (const text of ['Your Amazon.fr order has shipped and is running late.', 'Order 123-1234567-1234567']) {
      const answer = (await call(writer, 'add_parcels', { text })).result!;
      expect(answer.structuredContent).toEqual({ parcels: [] });
      const told = answer.content![0]!.text;
      expect(told, text).toContain('Amazon\'s emails hold no tracking number');
      expect(told, text).toContain('only in a browser on the user\'s own computer where they are signed in to Amazon');
      expect(told, text).toContain('Without such a browser, as in a phone app, don\'t start');
      expect(told, text).toContain(`Peek's guide shows where: ${AMAZON_GUIDE_URL}`);
    }
  });

  it('says what an Amazon import takes and its steps before the assistant tries, and changes nothing', async () => {
    vi.spyOn(service, 'getAuthAccount').mockResolvedValue({ email: null, emailConfirmed: false, locale: 'fr' });
    const french = `https://peektracker.com${guideHref('fr', 'amazon-orders')}`;
    const writer = connect(['parcels:write']);
    const tools = (await rpc(await mcp(writer, 'tools/list'))).result!.tools! as Array<{ name: string; description: string }>;
    expect(tools.find(({ name }) => name === 'amazon_import_steps')!.description)
      .toContain('Call it first whenever the user asks you to import, add or follow their Amazon orders, before you browse, search their emails or call add_parcels');
    const answer = (await call(writer, 'amazon_import_steps', {})).result!;
    const steps = answer.content![0]!.text;
    expect(answer.structuredContent).toEqual({ guide: french, steps });
    // What it takes comes first, then what to tell the user without it, then the steps.
    const order = [
      'only inside the user\'s Amazon account',
      'only in a browser on the user\'s own computer where they are signed in to Amazon',
      'Without such a browser, as in a phone app, don\'t start',
      `Peek's guide shows where, with pictures: ${french}`,
      'With one, open Your Orders',
      'On Amazon, only read',
    ].map((words) => steps.indexOf(words));
    expect(order.every((index) => index >= 0)).toBe(true);
    expect(order).toEqual([...order].sort((first, second) => first - second));
    expect(addParcelsFromText).not.toHaveBeenCalled();
    // Importing is adding: a grant that may only see parcels is asked to sign in again before it starts.
    expect((await call(connect(['parcels:read']), 'amazon_import_steps', {})).result).toMatchObject({ isError: true });
  });

  it('follows a number whose shape Peek can\'t tell with the carrier the assistant names by its id', async () => {
    vi.mocked(addParcelsFromText).mockResolvedValue([
      { trackingNumber: 'SYNTHETIC7', carrier: 'jp-home', outcome: 'added', packageId: '7d000000-0000-4000-a000-0000000000ab', jobId: null },
    ]);
    const writer = connect(['parcels:write']);
    const answer = await call(writer, 'add_parcels', { text: 'SYNTHETIC7', name: 'Sofa', carrier: 'jp-home' });
    expect(addParcelsFromText).toHaveBeenCalledExactlyOnceWith(expect.any(SupabaseServiceClient), userId, 'SYNTHETIC7', 'Sofa', ['jp-home'], { trust: 'named' });
    expect(answer.result!.structuredContent).toEqual({
      parcels: [{ trackingNumber: 'SYNTHETIC7', carrier: 'JP HOME', outcome: 'added', id: '7d000000-0000-4000-a000-0000000000ab' }],
    });
    // A name is no id: find_carriers gives it, with the other name it found the carrier by.
    expect((await call(writer, 'add_parcels', { text: 'SYNTHETIC7', carrier: 'VIR_TR' })).result)
      .toMatchObject({ isError: true, content: [{ text: expect.stringContaining('find_carriers') }] });
    expect(addParcelsFromText).toHaveBeenCalledTimes(1);
    const found = (await call(connect(['parcels:read']), 'find_carriers', { search: 'VIR_TR' })).result!.structuredContent;
    expect(found).toMatchObject({ total: 1, carriers: [{ id: 'jp-home', name: 'JP HOME', alsoCalled: 'VIR Transport' }] });
    // With a carrier named, the answer no longer asks for one.
    vi.mocked(addParcelsFromText).mockResolvedValue([]);
    vi.spyOn(service, 'getAuthAccount').mockResolvedValue({ email: null, emailConfirmed: false, locale: null });
    expect((await call(writer, 'add_parcels', { text: 'no number here', carrier: 'jp-home' })).result!.content![0]!.text).not.toContain('find its id');
  });

  it('links the Amazon guide in the language the account last used, read once a request', async () => {
    const auth = vi.spyOn(service, 'getAuthAccount').mockResolvedValue({ email: null, emailConfirmed: false, locale: 'de-CH' });
    const german = `https://peektracker.com${guideHref('de', 'amazon-orders')}`;
    expect(german).not.toBe(AMAZON_GUIDE_URL);
    const writer = connect(['parcels:write']);
    vi.mocked(addParcelsFromText).mockResolvedValue([]);
    expect((await call(writer, 'add_parcels', { text: 'Your Example Shop order has shipped.' })).result!.content![0]!.text).toContain(german);
    // Synthetic Amazon numbers, as the public routes' tests use.
    vi.mocked(addParcelsFromText).mockResolvedValue(['TBA999999999980', 'TBA999999999998'].map((trackingNumber) => (
      { trackingNumber, carrier: 'amazon-logistics', outcome: 'refused' as const, reason: AMAZON_ACCOUNT_MESSAGE })));
    const refused = await call(writer, 'add_parcels', { text: 'Tracking IDs: TBA999999999980, TBA999999999998' });
    const reasons = (refused.result!.structuredContent!.parcels as Array<{ reason: string }>).map(({ reason }) => reason);
    expect(reasons).toEqual(Array(2).fill(AMAZON_ACCOUNT_MESSAGE.replace(AMAZON_GUIDE_URL, german)));
    expect(auth).toHaveBeenCalledTimes(2);
  });

  it('answers a call outside the token\'s scopes with the challenge that asks for a new sign-in', async () => {
    const list = vi.spyOn(service, 'listAccountPackages').mockResolvedValue([]);
    const owned = vi.spyOn(service, 'getOwnedPackage').mockResolvedValue(null);
    const archive = vi.spyOn(service, 'setAccountPackageArchived').mockResolvedValue(true);
    const update = vi.spyOn(service, 'updateAccountPackage').mockResolvedValue(true);
    const change = vi.spyOn(service, 'changeAccountPackageCarrier').mockResolvedValue(true);
    const challenge = (description: string) => 'Bearer resource_metadata="https://peek.example/.well-known/oauth-protected-resource/mcp", '
      + `scope="parcels:read parcels:write parcels:edit", error="insufficient_scope", error_description="${description}"`;
    const reader = connect(['parcels:read']);
    const writes = [['add_parcels', { text: UPS }], ['archive_parcel', { parcel: UPS }]] as const;
    const edits = [
      ['restore_parcel', { parcel: UPS }], ['rename_parcel', { parcel: UPS, name: 'Sofa' }], ['change_carrier', { parcel: UPS, carrier: 'dhl' }],
      ['set_parcel_notifications', { parcel: UPS, notifications: false }],
    ] as const;
    for (const era of ['legacy', 'modern'] as const) {
      for (const [name, args] of writes) {
        expect((await call(reader, name, args, era)).result).toMatchObject({
          isError: true,
          content: [{ type: 'text', text: expect.stringContaining('allow it to add and archive parcels') }],
          _meta: { 'mcp/www_authenticate': [challenge('This connection may not add or archive parcels')] },
        });
      }
      for (const [name, args] of edits) {
        expect((await call(reader, name, args, era)).result).toMatchObject({
          isError: true,
          content: [{ type: 'text', text: expect.stringContaining('allow it to change their name, carrier or notifications') }],
          _meta: { 'mcp/www_authenticate': [challenge('This connection may not edit parcels')] },
        });
      }
    }
    // A grant made before editing had a scope of its own keeps what its owner allowed: adding and archiving, not editing.
    const earlier = connect(['parcels:read', 'parcels:write']);
    for (const [name, args] of edits) {
      expect((await call(earlier, name, args)).result).toMatchObject({
        isError: true, _meta: { 'mcp/www_authenticate': [challenge('This connection may not edit parcels')] },
      });
    }
    expect(owned).not.toHaveBeenCalled();
    expect(update).not.toHaveBeenCalled();
    expect(change).not.toHaveBeenCalled();
    const writer = connect(['parcels:write']);
    for (const [name, args] of [['list_parcels', {}], ['get_parcel', { parcel: UPS }], ['find_carriers', { search: 'UPS' }]] as const) {
      expect((await call(writer, name, args)).result).toMatchObject({
        isError: true,
        _meta: { 'mcp/www_authenticate': [challenge('This connection may not see parcels')] },
      });
    }
    expect(addParcelsFromText).not.toHaveBeenCalled();
    expect(list).not.toHaveBeenCalled();
    expect(archive).not.toHaveBeenCalled();
    // A tool Peek doesn't have is still a protocol error.
    expect((await call(reader, 'delete_parcel', { parcel: UPS })).error).toMatchObject({ code: -32602 });
  });

  it('masks the embedded postcode in a C Chez Vous parcel detail', async () => {
    const parcel = { ...stored(4, 'in_transit', { trackingNumber: 'ABCDEFGHIJK54321' }), carrier: 'c-chez-vous' };
    vi.spyOn(service, 'getOwnedPackage').mockResolvedValue(parcel);
    const read = await call(connect(['parcels:read']), 'get_parcel', { parcel: parcel.id });
    expect(read.result!.structuredContent).toMatchObject({ id: parcel.id, trackingNumber: 'ABCDEFGHIJK•••••' });
    expect(JSON.stringify(read)).not.toContain('54321');
    vi.spyOn(service, 'listAccountPackages').mockResolvedValue([parcel]);
    const list = await call(connect(['parcels:read']), 'list_parcels', { search: '54321' });
    expect(list.result!.structuredContent).toMatchObject({ total: 0 });
  });

  it('reads and archives only the caller\'s own parcels, by id or by tracking number', async () => {
    const parcel = stored(4, 'in_transit', { trackingNumber: UPS });
    const known = vi.spyOn(service, 'accountTrackingNumbers').mockResolvedValue(new Map([[UPS, parcel.id]]));
    const owned = vi.spyOn(service, 'getOwnedPackage').mockImplementation(async (id, owner) => (id === parcel.id && owner === userId ? parcel : null));
    const archive = vi.spyOn(service, 'setAccountPackageArchived').mockResolvedValue(true);
    const secret = connect(['parcels:read', 'parcels:write']);

    const read = await call(secret, 'get_parcel', { parcel: parcel.id.toUpperCase() });
    expect(read.result!.structuredContent).toMatchObject({ id: parcel.id, trackingNumber: UPS, status: 'in_transit' });
    expect((read.result!.structuredContent!.history as Array<{ status: string }>).map((event) => event.status)).toEqual(['in_transit', 'accepted']);
    expect(JSON.stringify(read)).not.toContain(RECEIVER);

    expect((await call(secret, 'archive_parcel', { parcel: ` ${UPS.toLowerCase()} ` })).result!.structuredContent).toEqual({ id: parcel.id, archived: true });
    expect(known).toHaveBeenCalledWith(userId, [UPS]);
    expect(owned).toHaveBeenCalledWith(parcel.id, userId);
    expect(archive).toHaveBeenCalledExactlyOnceWith(userId, parcel.id, true);

    const elsewhere = await call(secret, 'get_parcel', { parcel: '7d000000-0000-4000-a000-0000000000ff' });
    expect(elsewhere.result).toMatchObject({ isError: true, content: [{ text: 'No such parcel in this Peek account.' }] });
    vi.stubEnv('ASSISTANT_ACCOUNTS', `${userId},${otherUser}`);
    const other = await call(connect(['parcels:read', 'parcels:write'], otherUser), 'archive_parcel', { parcel: parcel.id });
    expect(other.result).toMatchObject({ isError: true });
    expect(archive).toHaveBeenCalledTimes(1);
  });

  /** One parcel of the caller's, found by its id or tracking number; any other account's call finds nothing. */
  function ownParcel(parcel: Omit<ReturnType<typeof stored>, 'dpd_postcode'> & { dpd_postcode: string | null }) {
    vi.spyOn(service, 'accountTrackingNumbers').mockImplementation(async (owner, numbers) => (
      new Map(owner === userId && numbers.includes(parcel.tracking_number) ? [[parcel.tracking_number, parcel.id]] : [])
    ));
    return vi.spyOn(service, 'getOwnedPackage').mockImplementation(async (id, owner) => (id === parcel.id && owner === userId ? parcel : null));
  }

  function otherAccount(): string {
    vi.stubEnv('ASSISTANT_ACCOUNTS', `${userId},${otherUser}`);
    return connect(['parcels:read', 'parcels:write', 'parcels:edit'], otherUser);
  }

  it('restores an archived parcel within the account\'s limit', async () => {
    const parcel = stored(5, 'delivered', { trackingNumber: UPS, archived: true });
    ownParcel(parcel);
    const restore = vi.spyOn(service, 'setAccountPackageArchived').mockResolvedValue(true);
    const secret = connect(['parcels:read', 'parcels:edit']);

    expect((await call(secret, 'restore_parcel', { parcel: UPS })).result!.structuredContent).toEqual({ id: parcel.id, archived: false });
    expect(restore).toHaveBeenCalledExactlyOnceWith(userId, parcel.id, false);

    restore.mockRejectedValueOnce(new SupabaseError('Parcel limit reached', 400, 'P0001'));
    expect((await call(secret, 'restore_parcel', { parcel: parcel.id })).result).toMatchObject({
      isError: true, content: [{ text: expect.stringContaining('as many parcels as it may') }],
    });
    expect((await call(otherAccount(), 'restore_parcel', { parcel: parcel.id })).result).toMatchObject({ isError: true });
    expect(restore).toHaveBeenCalledTimes(2);
  });

  it('leaves a parcel on the list as it is when asked to restore it', async () => {
    const parcel = stored(6, 'in_transit');
    ownParcel(parcel);
    const restore = vi.spyOn(service, 'setAccountPackageArchived').mockResolvedValue(true);
    const answered = await call(connect(['parcels:edit']), 'restore_parcel', { parcel: parcel.id });
    expect(answered.result!.structuredContent).toEqual({ id: parcel.id, archived: false });
    expect(restore).not.toHaveBeenCalled();
  });

  it('renames the caller\'s own parcel, or clears its name', async () => {
    const parcel = stored(7, 'in_transit', { trackingNumber: UPS });
    ownParcel(parcel);
    const update = vi.spyOn(service, 'updateAccountPackage').mockResolvedValue(true);
    const secret = connect(['parcels:edit']);

    expect((await call(secret, 'rename_parcel', { parcel: UPS, name: '  Sofa  ' })).result!.structuredContent).toEqual({ id: parcel.id, name: 'Sofa' });
    expect(update).toHaveBeenLastCalledWith(userId, parcel.id, { label: 'Sofa' });
    expect((await call(secret, 'rename_parcel', { parcel: parcel.id, name: '' })).result!.structuredContent).toEqual({ id: parcel.id, name: null });
    expect(update).toHaveBeenLastCalledWith(userId, parcel.id, { label: '' });
    // Characters as the app counts them: 80 emoji are a name, 81 are not.
    expect((await call(secret, 'rename_parcel', { parcel: parcel.id, name: '📦'.repeat(80) })).result!.structuredContent).toMatchObject({ id: parcel.id });
    expect((await call(secret, 'rename_parcel', { parcel: parcel.id, name: '📦'.repeat(81) })).result).toMatchObject({
      isError: true, content: [{ text: 'Parcel names can be at most 80 characters' }],
    });
    expect(update).toHaveBeenCalledTimes(3);

    expect((await call(otherAccount(), 'rename_parcel', { parcel: parcel.id, name: 'Mine' })).result).toMatchObject({
      isError: true, content: [{ text: 'No such parcel in this Peek account.' }],
    });
    expect(update).toHaveBeenCalledTimes(3);
  });

  it('changes the carrier of the caller\'s own parcel and checks it again, as the edit sheet does', async () => {
    const parcel = { ...stored(8, 'in_transit', { trackingNumber: UPS }), dpd_postcode: null };
    ownParcel(parcel);
    const change = vi.spyOn(service, 'changeAccountPackageCarrier').mockResolvedValue(true);
    const enqueue = vi.spyOn(service, 'enqueueSyncJob').mockResolvedValue({ row: { id: '8e000000-0000-4000-a000-000000000001' }, queued: true } as Awaited<ReturnType<typeof service.enqueueSyncJob>>);
    const secret = connect(['parcels:read', 'parcels:edit']);

    const changed = await call(secret, 'change_carrier', { parcel: UPS, carrier: 'dhl' });
    expect(changed.result!.structuredContent).toEqual({ id: parcel.id, carrier: 'DHL', carrierId: 'dhl', checking: true });
    expect(change).toHaveBeenCalledExactlyOnceWith(userId, parcel.id, 'dhl', null, null, null);
    expect(enqueue).toHaveBeenCalledExactlyOnceWith({ userId, packageId: parcel.id, reason: 'changed' });

    // The carrier it has already: nothing to change or check.
    expect((await call(secret, 'change_carrier', { parcel: UPS, carrier: 'ups' })).result!.structuredContent).toMatchObject({ carrierId: 'ups', checking: false });
    for (const carrier of ['no-such-carrier', 'unknown']) {
      expect((await call(secret, 'change_carrier', { parcel: UPS, carrier })).result).toMatchObject({
        isError: true, content: [{ text: expect.stringContaining('find_carriers') }],
      });
    }
    change.mockRejectedValueOnce(new SupabaseError('Invalid delivery postcode', 400, '22023'));
    expect((await call(secret, 'change_carrier', { parcel: UPS, carrier: 'dhl' })).result).toMatchObject({
      isError: true, content: [{ text: expect.stringContaining('Invalid delivery postcode') }],
    });
    expect((await call(otherAccount(), 'change_carrier', { parcel: parcel.id, carrier: 'dhl' })).result).toMatchObject({ isError: true });
    expect(change).toHaveBeenCalledTimes(2);
    expect(enqueue).toHaveBeenCalledTimes(1);
  });

  it('keeps the parcel\'s postcode for a carrier that takes one, and checks no archived parcel', async () => {
    const parcel = { ...stored(9, 'in_transit', { trackingNumber: 'SYNTHETIC0009', archived: true }), carrier: 'dpd-fr', dpd_postcode: '10115' };
    ownParcel(parcel);
    const change = vi.spyOn(service, 'changeAccountPackageCarrier').mockResolvedValue(true);
    const enqueue = vi.spyOn(service, 'enqueueSyncJob');
    const secret = connect(['parcels:edit']);

    expect((await call(secret, 'change_carrier', { parcel: parcel.id, carrier: 'dpd-de' })).result!.structuredContent)
      .toMatchObject({ carrierId: 'dpd-de', checking: false });
    expect(change).toHaveBeenLastCalledWith(userId, parcel.id, 'dpd-de', null, '10115', null);
    await call(secret, 'change_carrier', { parcel: parcel.id, carrier: 'dpd-de', postcode: '' });
    expect(change).toHaveBeenLastCalledWith(userId, parcel.id, 'dpd-de', null, null, null);
    // A carrier that takes no postcode is given none.
    await call(secret, 'change_carrier', { parcel: parcel.id, carrier: 'hermes-de' });
    expect(change).toHaveBeenLastCalledWith(userId, parcel.id, 'hermes-de', null, null, null);
    expect(enqueue).not.toHaveBeenCalled();
  });

  it('keeps the country a kept postcode was given with, or takes another', async () => {
    // Synthetic Belgian postcode for a Spring GDS parcel, as its carrier takes postcodes from abroad.
    const base = stored(18, 'in_transit', { trackingNumber: '3SABCD826405193' });
    const parcel = { ...base, carrier: 'ups', dpd_postcode: '1000',
      carrier_data: { ...base.carrier_data, postcode_country: { postcode: '1000', country: 'BE' } } };
    ownParcel(parcel);
    const change = vi.spyOn(service, 'changeAccountPackageCarrier').mockResolvedValue(true);
    vi.spyOn(service, 'enqueueSyncJob').mockResolvedValue({ row: { id: '8e000000-0000-4000-a000-000000000002' }, queued: true } as Awaited<ReturnType<typeof service.enqueueSyncJob>>);
    const write = vi.spyOn(service, 'updatePackageAtGeneration').mockResolvedValue(true);
    const readBack = vi.spyOn(service, 'getPackage');
    const secret = connect(['parcels:edit']);

    // The row the change stores holds the postcode with the country the change kept: nothing to write after it.
    const after = { ...parcel, carrier: 'spring-gds', tracking_generation: 'generation-1' };
    vi.mocked(service.getOwnedPackage).mockResolvedValueOnce(parcel);
    readBack.mockResolvedValueOnce(after);
    expect((await call(secret, 'change_carrier', { parcel: parcel.id, carrier: 'spring-gds' })).result!.structuredContent).toMatchObject({ carrierId: 'spring-gds' });
    expect(change).toHaveBeenLastCalledWith(userId, parcel.id, 'spring-gds', null, '1000', null);
    expect(write).not.toHaveBeenCalled();
    // The same postcode given again keeps its country too: the parcel holds all of it already.
    vi.mocked(service.getOwnedPackage).mockResolvedValueOnce(after);
    expect((await call(secret, 'change_carrier', { parcel: parcel.id, carrier: 'spring-gds', postcode: '1000' })).result!.structuredContent)
      .toMatchObject({ checking: false });
    // A new postcode takes the country given with it.
    const moved = { ...after, dpd_postcode: '1050', carrier_data: {}, tracking_generation: 'generation-2' };
    vi.mocked(service.getOwnedPackage).mockResolvedValueOnce(after);
    readBack.mockResolvedValueOnce(moved);
    await call(secret, 'change_carrier', { parcel: parcel.id, carrier: 'spring-gds', postcode: '1050', postcodeCountry: 'be' });
    expect(change).toHaveBeenLastCalledWith(userId, parcel.id, 'spring-gds', null, '1050', null);
    expect(write).toHaveBeenLastCalledWith(parcel.id, 'generation-2', expect.objectContaining({
      carrier_data: expect.objectContaining({ postcode_country: { postcode: '1050', country: 'BE' } }),
    }));
    // Another postcode saved since this change keeps its own country.
    write.mockClear();
    vi.mocked(service.getOwnedPackage).mockResolvedValueOnce(moved);
    readBack.mockResolvedValueOnce({ ...moved, dpd_postcode: '75001', tracking_generation: 'generation-3' });
    await call(secret, 'change_carrier', { parcel: parcel.id, carrier: 'spring-gds', postcode: '1060', postcodeCountry: 'be' });
    expect(write).not.toHaveBeenCalled();
  });

  it('takes the last digits of the recipient\'s phone for a carrier that reads them, and keeps them unless given others', async () => {
    const parcel = { ...stored(19, 'in_transit', { trackingNumber: 'JMX000000000001' }), carrier: 'ups', dpd_postcode: null, phone_digits: null as string | null };
    ownParcel(parcel);
    const change = vi.spyOn(service, 'changeAccountPackageCarrier').mockResolvedValue(true);
    vi.spyOn(service, 'enqueueSyncJob').mockResolvedValue({ row: { id: '8e000000-0000-4000-a000-000000000003' }, queued: true } as Awaited<ReturnType<typeof service.enqueueSyncJob>>);
    const write = vi.spyOn(service, 'updatePackage').mockResolvedValue(undefined);
    const secret = connect(['parcels:edit']);

    const found = (await call(connect(['parcels:read']), 'find_carriers', { search: 'J&T Express Mexico' })).result!.structuredContent as {
      carriers: Array<{ id: string; inputs?: Array<{ input: string }> }>;
    };
    expect(found.carriers.find(({ id }) => id === 'j-and-t-mx')?.inputs).toEqual([expect.objectContaining({ input: 'phoneDigits' })]);
    await call(secret, 'change_carrier', { parcel: parcel.id, carrier: 'j-and-t-mx', phoneDigits: ' 0123 ' });
    expect(change).toHaveBeenLastCalledWith(userId, parcel.id, 'j-and-t-mx', null, null, '0123');
    expect(write).not.toHaveBeenCalled();
    expect((await call(secret, 'change_carrier', { parcel: parcel.id, carrier: 'j-and-t-mx', phoneDigits: '12a4' })).result).toMatchObject({
      isError: true, content: [{ text: expect.stringContaining('last four digits') }],
    });

    // Stored digits stay for a carrier that reads them, and a call that changes nothing changes nothing.
    Object.assign(parcel, { carrier: 'j-and-t-mx', phone_digits: '0123' });
    expect((await call(secret, 'change_carrier', { parcel: parcel.id, carrier: 'j-and-t-mx' })).result!.structuredContent).toMatchObject({ checking: false });
    await call(secret, 'change_carrier', { parcel: parcel.id, carrier: 'j-and-t-cn' });
    expect(change).toHaveBeenLastCalledWith(userId, parcel.id, 'j-and-t-cn', null, null, null);
    // A carrier that reads none forgets them.
    await call(secret, 'change_carrier', { parcel: parcel.id, carrier: 'ups' });
    expect(change).toHaveBeenLastCalledWith(userId, parcel.id, 'ups', null, null, null);
    expect(change).toHaveBeenCalledTimes(3);
  });

  it('turns a parcel\'s notifications and emails off or on, for the caller\'s own parcel only', async () => {
    const parcel = stored(10, 'in_transit', { trackingNumber: UPS });
    ownParcel(parcel);
    const update = vi.spyOn(service, 'updateAccountPackage').mockResolvedValue(true);
    const secret = connect(['parcels:edit']);

    // A grant that may not read hears back what it set, not the parcel's other setting; one that may read hears both.
    expect((await call(secret, 'set_parcel_notifications', { parcel: UPS, notifications: false })).result!.structuredContent)
      .toEqual({ id: parcel.id, notifications: false });
    expect((await call(connect(['parcels:read', 'parcels:edit']), 'set_parcel_notifications', { parcel: UPS, notifications: false }))
      .result!.structuredContent).toEqual({ id: parcel.id, notifications: false, emails: true });
    expect(update).toHaveBeenLastCalledWith(userId, parcel.id, { notifications_muted: true });
    expect((await call(secret, 'set_parcel_notifications', { parcel: UPS, notifications: true, emails: false })).result!.structuredContent)
      .toEqual({ id: parcel.id, notifications: true, emails: false });
    expect(update).toHaveBeenLastCalledWith(userId, parcel.id, { notifications_muted: false, email_muted: true });
    expect((await call(secret, 'set_parcel_notifications', { parcel: UPS })).result).toMatchObject({ isError: true });
    expect((await call(otherAccount(), 'set_parcel_notifications', { parcel: parcel.id, emails: false })).result).toMatchObject({ isError: true });
    expect(update).toHaveBeenCalledTimes(3);
  });

  it('finds carriers by name, country or number from the catalog, asking none', async () => {
    const fetched = vi.spyOn(globalThis, 'fetch');
    const secret = connect(['parcels:read']);
    const found = async (args: Record<string, unknown>) => (await call(secret, 'find_carriers', args)).result!.structuredContent as {
      total: number; carriers: Array<{ id: string; name: string; inputs?: Array<{ input: string; optional: boolean }>; fitsNumber?: boolean }>;
    };

    expect((await found({ search: 'Colissimo' })).carriers[0]).toMatchObject({ id: 'la-poste' });
    expect((await found({ trackingNumber: UPS })).carriers).toEqual([expect.objectContaining({ id: 'ups', fitsNumber: true })]);
    const german = await found({ search: 'Germany' });
    expect(german.carriers.length).toBeLessThanOrEqual(10);
    expect(german.total).toBeGreaterThanOrEqual(german.carriers.length);
    expect(german.carriers.find(({ id }) => id === 'gls-de')?.inputs).toEqual([{ input: 'postcode', optional: false }]);
    expect((await call(secret, 'find_carriers', {})).result).toMatchObject({ isError: true });
    expect(fetched).not.toHaveBeenCalled();
  });

  it('opens no stream and ends no session, and is not there when no account may connect', async () => {
    for (const handler of [mcpGet, mcpDelete]) {
      const response = handler();
      expect(response.status).toBe(405);
      expect(response.headers.get('allow')).toBe('POST');
    }
    vi.stubEnv('ASSISTANT_ACCOUNTS', '');
    for (const handler of [mcpGet, mcpDelete]) expect(handler().status).toBe(404);
  });

  it('keeps a comma or a quote in a tracking number within that number', async () => {
    const paths: string[] = [];
    vi.spyOn(service as unknown as { request: (path: string) => Promise<unknown> }, 'request').mockImplementation(async (path: string) => {
      paths.push(new URL(path, 'https://database.example').searchParams.get('tracking_number') ?? '');
      return [];
    });
    const lookup = await call(connect(['parcels:read']), 'get_parcel', { parcel: 'SYNTHETICA","SYNTHETICB' });
    expect(lookup.result).toMatchObject({ isError: true });
    expect(paths).toContain('in.("SYNTHETICA\\",\\"SYNTHETICB")');
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

  it('publish the Home Assistant integration\'s client document at the address it names as its id', async () => {
    const response = homeAssistant();
    expect(response.status).toBe(200);
    expect(response.headers.get('content-type')).toMatch(/^application\/json/);
    expect(response.headers.get('cache-control')).toBe('public, max-age=3600');
    expect(await response.json()).toEqual({
      client_id: 'https://peek.example/oauth/clients/home-assistant.json',
      client_name: 'Home Assistant',
      client_uri: 'https://peek.example/assistants',
      redirect_uris: ['https://my.home-assistant.io/redirect/oauth'],
      grant_types: ['authorization_code', 'refresh_token'],
      response_types: ['code'],
      token_endpoint_auth_method: 'none',
      scope: 'parcels:read parcels:write parcels:edit',
    });
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

  it('give no token to an account the setting is no longer open to', async () => {
    vi.spyOn(service, 'redeemAssistantCode').mockResolvedValue({ userId: otherUser, grantId: '6c000000-0000-4000-a000-0000000000f2', scopes: ['parcels:read'] });
    const refresh = vi.spyOn(service, 'refreshAssistantTokens').mockResolvedValue({ userId: otherUser, grantId: '6c000000-0000-4000-a000-0000000000f2', scopes: ['parcels:read'], rotated: true });
    const answers = [
      await token(form('/oauth/token', {
        grant_type: 'authorization_code', code: newSecret('code'), redirect_uri: 'https://assistant.example.com/callback', code_verifier: 'v'.repeat(64), client_id: newClientId(),
      }), none),
      await token(form('/oauth/token', { grant_type: 'refresh_token', refresh_token: newSecret('refresh'), client_id: newClientId() }), none),
    ];
    for (const answer of answers) {
      expect(answer.status).toBe(400);
      const body = await answer.json();
      expect(body.error).toBe('invalid_grant');
      expect(body).not.toHaveProperty('access_token');
    }
    refresh.mockResolvedValue({ userId, grantId: '6c000000-0000-4000-a000-0000000000f3', scopes: ['parcels:read'], rotated: true });
    const renewed = await token(form('/oauth/token', { grant_type: 'refresh_token', refresh_token: newSecret('refresh'), client_id: newClientId() }), none);
    expect(renewed.status).toBe(200);
    expect(await renewed.json()).toMatchObject({ token_type: 'Bearer', scope: 'parcels:read' });
  });

  it('name each refusal\'s OAuth error in its request line', async () => {
    const output = vi.spyOn(console, 'log').mockImplementation(() => undefined);
    vi.spyOn(service, 'refreshAssistantTokens').mockResolvedValue(null);
    await token(form('/oauth/token', { grant_type: 'refresh_token', refresh_token: newSecret('refresh'), client_id: newClientId() }), none);
    await token(new NextRequest('https://peek.example/oauth/token', { method: 'POST', headers: { 'content-type': 'application/json' }, body: '{}' }), none);
    await token(form('/oauth/token', { grant_type: 'password', client_id: newClientId() }), none);
    const requests = output.mock.calls.map(([line]) => JSON.parse(String(line)))
      .filter((line) => line.event === 'http_request')
      .map(({ route, status, error_code }) => ({ route, status, error_code }));
    expect(requests).toEqual([
      { route: '/oauth/token', status: 400, error_code: 'invalid_grant' },
      { route: '/oauth/token', status: 400, error_code: 'invalid_request' },
      { route: '/oauth/token', status: 400, error_code: 'unsupported_grant_type' },
    ]);
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
    for (const handler of [protectedResource, mcpResource, authorizationServer, homeAssistant]) expect(handler().status).toBe(404);
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
  // Personal tokens are off: an assistant's access token reads the parcels without them.
  beforeEach(() => { vi.stubEnv('PERSONAL_TOKENS', ''); });

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
    expect(await (await read(secret, '?status=delivered&sort=updated&search=synthetic')).json())
      .toEqual((await call(secret, 'list_parcels', { status: 'delivered', sort: 'updated', search: 'synthetic' })).result!.structuredContent);
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
    for (const query of ['?status=lost', '?sort=cheapest', `?search=${'x'.repeat(101)}`, '?limit=0', '?limit=51', '?limit=2.5', '?timeZone=Mars/Olympus']) {
      expect((await read(reader, query)).status, query.slice(0, 20)).toBe(400);
    }
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

  it('takes a personal token only while personal tokens are on', async () => {
    vi.spyOn(service, 'listAccountPackages').mockResolvedValue([]);
    const personal = connect(['parcels:read'], userId, 'personal');
    const refused = await read(personal);
    expect(refused.status).toBe(401);
    expect(refused.headers.get('www-authenticate')).toContain('error="invalid_token"');
    expect(service.listAccountPackages).not.toHaveBeenCalled();
    vi.stubEnv('PERSONAL_TOKENS', 'on');
    expect((await read(personal)).status).toBe(200);
  });

  it('is not there when no account may connect', async () => {
    vi.spyOn(service, 'listAccountPackages').mockResolvedValue([]);
    const secret = connect(['parcels:read']);
    vi.stubEnv('ASSISTANT_ACCOUNTS', '');
    expect((await read(secret)).status).toBe(404);
    vi.stubEnv('PERSONAL_TOKENS', 'on');
    expect((await read(connect(['parcels:read'], userId, 'personal'))).status).toBe(404);
    expect(service.listAccountPackages).not.toHaveBeenCalled();
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
    vi.stubEnv('PERSONAL_TOKENS', 'on');
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

  it('makes no personal token while personal tokens are off', async () => {
    const create = vi.spyOn(service, 'createPersonalToken').mockResolvedValue(connection);
    expect((await createToken(signedIn('POST', undefined, { name: 'Home script', scopes: ['parcels:read'] }), none)).status).toBe(404);
    expect(create).not.toHaveBeenCalled();
  });

  it('makes a personal token, shown once and stored as its digest', async () => {
    vi.stubEnv('PERSONAL_TOKENS', 'on');
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
    vi.stubEnv('PERSONAL_TOKENS', 'on');
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
      vi.stubEnv('PERSONAL_TOKENS', 'on');
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
