import type { LookupAddress } from 'node:dns';
import type { RequestOptions } from 'node:https';
import { afterEach, describe, expect, it, vi } from 'vitest';
import type { SupabaseServiceClient } from '../supabase';
import {
  clientFromDocument,
  clientName,
  clientUri,
  findClient,
  forgetClientDocuments,
  isMetadataDocumentId,
  keepsRefreshToken,
  publicAddress,
  redirectHost,
  redirectMatches,
  redirectUriProblem,
  type AssistantClient,
} from './clients';
import { newClientId } from './settings';

/** What the mocked network answers: documents by address, and the addresses DNS gives each host. */
const network = vi.hoisted(() => ({
  documents: new Map<string, { status: number; type: string; body: string }>(),
  addresses: new Map<string, Array<{ address: string; family: number }>>(),
  requests: [] as Array<{ url: string; options: RequestOptions }>,
}));

vi.mock('node:https', async () => {
  const { EventEmitter } = await import('node:events');
  const { Readable } = await import('node:stream');
  return {
    request: (url: string, options: RequestOptions, answer: (response: unknown) => void) => {
      network.requests.push({ url, options });
      const outgoing = Object.assign(new EventEmitter(), {
        destroy: (error?: Error) => { if (error) outgoing.emit('error', error); },
        end: () => {
          const document = network.documents.get(url);
          if (!document) return queueMicrotask(() => outgoing.emit('error', new Error('unreachable')));
          if (!document.status) return; // A server that never answers.
          const response = Object.assign(Readable.from([Buffer.from(document.body)]), {
            statusCode: document.status,
            headers: { 'content-type': document.type },
          });
          queueMicrotask(() => answer(response));
        },
      });
      return outgoing;
    },
  };
});

vi.mock('node:dns', () => ({
  lookup: (hostname: string, _options: unknown, callback: (error: Error | null, addresses: LookupAddress[]) => void) => {
    const addresses = network.addresses.get(hostname);
    if (!addresses) return callback(Object.assign(new Error('not found'), { code: 'ENOTFOUND' }), []);
    callback(null, addresses);
  },
}));

afterEach(() => {
  forgetClientDocuments();
  network.documents.clear();
  network.addresses.clear();
  network.requests.length = 0;
  vi.useRealTimers();
});

describe('redirect addresses a client may register', () => {
  it('takes HTTPS, HTTP to this computer, and an app\'s own scheme', () => {
    for (const uri of [
      'https://assistant.example.com/oauth/callback',
      'https://assistant.example.com/callback?tenant=1',
      'http://localhost:3000/callback',
      'http://127.0.0.1/callback',
      'http://[::1]:8080/callback',
      'com.example.assistant:/oauth/callback',
      'example-assistant://oauth/callback',
    ]) expect(redirectUriProblem(uri), uri).toBeNull();
  });

  it('refuses HTTP to another host, dangerous schemes, fragments and credentials', () => {
    expect(redirectUriProblem('http://assistant.example.com/callback')).toBe('uses HTTP away from this computer');
    expect(redirectUriProblem('http://127.0.0.2/callback')).toBe('uses HTTP away from this computer');
    for (const uri of ['javascript:alert(1)', 'JavaScript:alert(1)', 'data:text/html,hello', 'file:///tmp/x', 'vbscript:x', 'blob:https://assistant.example.com/x']) {
      expect(redirectUriProblem(uri), uri).toBe('uses a scheme that is not allowed');
    }
    for (const uri of [
      'microsoft-edge:https://attacker.example.net', 'x-safari-https://attacker.example.net', 'googlechromes://attacker.example.net',
      'opera-https://attacker.example.net', 'firefox:https://attacker.example.net', 'intent://scan/', 'wss://attacker.example.net', 'mailto:a@example.com',
    ]) expect(redirectUriProblem(uri), uri).toBe('uses a scheme that is not allowed');
    expect(redirectUriProblem('https://assistant.example.com/callback#done')).toBe('has a fragment');
    expect(redirectUriProblem('https://assistant.example.com/callback#')).toBe('has a fragment');
    expect(redirectUriProblem('https://someone@assistant.example.com/callback')).toBe('has credentials');
    expect(redirectUriProblem('not an address')).toBe('is not an address');
    expect(redirectUriProblem(`https://assistant.example.com/${'x'.repeat(2_000)}`)).toBe('is too long');
  });

  it('matches a loopback address on any port, but nothing else that differs', () => {
    const registered = 'http://127.0.0.1/callback';
    expect(redirectMatches(registered, 'http://127.0.0.1:49152/callback')).toBe(true);
    expect(redirectMatches('http://localhost:3000/callback', 'http://localhost:51000/callback')).toBe(true);
    expect(redirectMatches(registered, 'http://127.0.0.1:49152/other')).toBe(false);
    expect(redirectMatches(registered, 'http://localhost:49152/callback')).toBe(false);
    expect(redirectMatches(registered, 'http://127.0.0.1:49152/callback?extra=1')).toBe(false);
    expect(redirectMatches(registered, 'http://127.0.0.1:49152/callback#x')).toBe(false);
    expect(redirectMatches(registered, 'http://someone@127.0.0.1:49152/callback')).toBe(false);
    expect(redirectMatches('https://assistant.example.com/callback', 'https://assistant.example.com:8443/callback')).toBe(false);
    expect(redirectMatches('https://assistant.example.com/callback', 'https://assistant.example.com/callback')).toBe(true);
    expect(redirectMatches(registered, 'not an address')).toBe(false);
  });

  it('names the site, this computer, or the app scheme on the consent page', () => {
    expect(redirectHost('https://assistant.example.com/callback')).toBe('assistant.example.com');
    expect(redirectHost('http://127.0.0.1:3000/callback')).toBe('localhost');
    expect(redirectHost('com.example.assistant:/oauth/callback')).toBe('com.example.assistant://');
  });

  it('keeps one refresh token only for Google\'s redirect hosts', () => {
    expect(keepsRefreshToken('https://oauth-redirect.googleusercontent.com/r/example-project')).toBe(true);
    expect(keepsRefreshToken('https://oauth-redirect-sandbox.googleusercontent.com/r/example-project')).toBe(true);
    expect(keepsRefreshToken('http://oauth-redirect.googleusercontent.com/r/example-project')).toBe(false);
    expect(keepsRefreshToken('https://oauth-redirect.googleusercontent.com.example.com/r/x')).toBe(false);
    expect(keepsRefreshToken('https://evil-oauth-redirect.googleusercontent.com/r/x')).toBe(false);
    expect(keepsRefreshToken('https://assistant.example.com/callback')).toBe(false);
    expect(keepsRefreshToken('not an address')).toBe(false);
  });
});

describe('client names and addresses', () => {
  it('shows a printable single-line name of at most 100 characters', () => {
    expect(clientName('  Example\nAssistant\u0000 ')).toBe('Example Assistant');
    expect(clientName('‮Assistant')).toBe('Assistant');
    expect(clientName('é'.repeat(150))).toHaveLength(100);
    expect([...clientName('😀'.repeat(150))!]).toHaveLength(100);
    expect(clientName(' ​ ')).toBeNull();
    expect(clientName(42)).toBeNull();
  });

  it('keeps a client address only when it is HTTPS', () => {
    expect(clientUri('https://assistant.example.com')).toBe('https://assistant.example.com');
    expect(clientUri('http://assistant.example.com')).toBeNull();
    expect(clientUri(`https://assistant.example.com/${'x'.repeat(500)}`)).toBeNull();
    expect(clientUri('nope')).toBeNull();
    expect(clientUri(null)).toBeNull();
  });
});

describe('client id metadata documents', () => {
  const id = 'https://assistant.example.com/oauth/client.json';
  const document = {
    client_id: id,
    client_name: 'Example Assistant',
    client_uri: 'https://assistant.example.com',
    redirect_uris: ['https://assistant.example.com/oauth/callback'],
    token_endpoint_auth_method: 'none',
  };

  it('takes as an id only an HTTPS address with a path, a name for its host, and no tricks', () => {
    expect(isMetadataDocumentId(id)).toBe(true);
    for (const value of [
      'http://assistant.example.com/oauth/client.json',
      'https://assistant.example.com/',
      'https://assistant.example.com',
      'https://assistant.example.com:8443/client.json',
      'https://203.0.113.10/client.json',
      'https://[2001:db8::1]/client.json',
      'https://assistant.example.com/a/../client.json',
      'https://assistant.example.com/a/%2e%2e/client.json',
      'https://assistant.example.com/client.json#x',
      'https://someone@assistant.example.com/client.json',
      'https://Assistant.example.com/client.json',
      `https://assistant.example.com/${'x'.repeat(500)}`,
      'peek_c_not-a-document',
    ]) expect(isMetadataDocumentId(value), value).toBe(false);
  });

  it('fetches nothing from a private, loopback or special address', () => {
    for (const address of [
      '127.0.0.1', '10.1.2.3', '172.16.5.4', '192.168.1.1', '169.254.169.254', '100.64.0.1', '0.0.0.0',
      '::1', '::', 'fc00::1', 'fd12:3456::1', 'fe80::1', '::ffff:127.0.0.1', '::ffff:10.0.0.1', '64:ff9b::7f00:1',
      'not an address',
    ]) expect(publicAddress(address), address).toBe(false);
    expect(publicAddress('1.1.1.1')).toBe(true);
    expect(publicAddress('2606:4700:4700::1111')).toBe(true);
  });

  it('describes a public client whose id is its own address, with 1 to 10 valid redirects', () => {
    expect(clientFromDocument(id, document)).toEqual({
      id, name: 'Example Assistant', uri: 'https://assistant.example.com', redirectUris: document.redirect_uris,
    });
    const bare = { client_id: id, client_uri: document.client_uri, redirect_uris: document.redirect_uris };
    expect(clientFromDocument(id, bare)).toEqual({ id, name: 'assistant.example.com', uri: 'https://assistant.example.com', redirectUris: document.redirect_uris });
    expect(clientFromDocument(id, { ...document, client_uri: 'http://assistant.example.com' })?.uri).toBeNull();
  });

  it('refuses a document for another id, a confidential client, or bad redirects', () => {
    expect(clientFromDocument(id, { ...document, client_id: 'https://assistant.example.com/other.json' })).toBeNull();
    expect(clientFromDocument(id, { ...document, client_id: undefined })).toBeNull();
    expect(clientFromDocument(id, { ...document, token_endpoint_auth_method: 'client_secret_basic' })).toBeNull();
    expect(clientFromDocument(id, { ...document, token_endpoint_auth_method: 'private_key_jwt' })).toBeNull();
    expect(clientFromDocument(id, { ...document, redirect_uris: [] })).toBeNull();
    expect(clientFromDocument(id, { ...document, redirect_uris: 'https://assistant.example.com/oauth/callback' })).toBeNull();
    expect(clientFromDocument(id, { ...document, redirect_uris: Array.from({ length: 11 }, (_, index) => `https://assistant.example.com/${index}`) })).toBeNull();
    expect(clientFromDocument(id, { ...document, redirect_uris: [...document.redirect_uris, 'javascript:alert(1)'] })).toBeNull();
    expect(clientFromDocument(id, { ...document, redirect_uris: [42] })).toBeNull();
    expect(clientFromDocument(id, [document])).toBeNull();
    expect(clientFromDocument(id, null)).toBeNull();
  });
});

describe('finding a client', () => {
  const registered: AssistantClient = { id: newClientId(), name: 'Example Assistant', uri: null, redirectUris: ['https://assistant.example.com/callback'] };
  const id = 'https://assistant.example.com/oauth/client.json';
  const document = { client_id: id, client_name: 'Example Assistant', redirect_uris: ['https://assistant.example.com/oauth/callback'] };

  function service() {
    const assistantClient = vi.fn<SupabaseServiceClient['assistantClient']>(async (clientId) => (clientId === registered.id ? registered : null));
    return { assistantClient, client: { assistantClient } as unknown as SupabaseServiceClient };
  }

  it('reads a client that registered with Peek from the database, and nothing else there', async () => {
    const { assistantClient, client } = service();
    await expect(findClient(client, registered.id)).resolves.toEqual(registered);
    expect(assistantClient).toHaveBeenCalledWith(registered.id);
    await expect(findClient(client, 'peek_c_short')).resolves.toBeNull();
    await expect(findClient(client, 'some-client')).resolves.toBeNull();
    expect(assistantClient).toHaveBeenCalledTimes(1);
    expect(network.requests).toEqual([]);
  });

  it('reads a metadata document once an hour, through DNS that refuses private addresses', async () => {
    const { assistantClient, client } = service();
    network.documents.set(id, { status: 200, type: 'application/json; charset=utf-8', body: JSON.stringify(document) });
    const found = await findClient(client, id);
    expect(found).toEqual({ id, name: 'Example Assistant', uri: null, redirectUris: document.redirect_uris });
    expect(await findClient(client, id)).toEqual(found);
    expect(network.requests).toHaveLength(1);
    expect(assistantClient).not.toHaveBeenCalled();

    const lookup = network.requests[0]!.options.lookup as unknown as (
      hostname: string, options: object, callback: (error: NodeJS.ErrnoException | null, address: unknown, family?: number) => void) => void;
    const resolve = (hostname: string, options: object) => new Promise<{ error: NodeJS.ErrnoException | null; address: unknown; family?: number }>(
      (done) => lookup(hostname, options, (error, address, family) => done({ error, address, family })));
    network.addresses.set('rebound.example.com', [{ address: '10.0.0.5', family: 4 }, { address: '169.254.169.254', family: 4 }]);
    expect((await resolve('rebound.example.com', {})).error?.code).toBe('ENOTFOUND');
    network.addresses.set('mixed.example.com', [{ address: '127.0.0.1', family: 4 }, { address: '1.1.1.1', family: 4 }]);
    expect(await resolve('mixed.example.com', {})).toEqual({ error: null, address: '1.1.1.1', family: 4 });
    expect((await resolve('mixed.example.com', { all: true })).address).toEqual([{ address: '1.1.1.1', family: 4 }]);
    expect((await resolve('missing.example.com', {})).error?.code).toBe('ENOTFOUND');

    forgetClientDocuments();
    await findClient(client, id);
    expect(network.requests).toHaveLength(2);
  });

  it('serves no client from a document that is missing, not JSON, too large or for another id, and tries again a minute later', async () => {
    const { client } = service();
    vi.useFakeTimers({ toFake: ['Date'] });
    network.documents.set(id, { status: 404, type: 'application/json', body: '{}' });
    await expect(findClient(client, id)).resolves.toBeNull();
    network.documents.set(id, { status: 200, type: 'application/json', body: JSON.stringify(document) });
    await expect(findClient(client, id)).resolves.toBeNull();
    expect(network.requests).toHaveLength(1);
    vi.setSystemTime(Date.now() + 60_001);
    await expect(findClient(client, id)).resolves.not.toBeNull();

    for (const answer of [
      { status: 200, type: 'text/html', body: JSON.stringify(document) },
      { status: 200, type: 'application/json', body: 'not json' },
      { status: 200, type: 'application/json', body: JSON.stringify({ ...document, padding: 'x'.repeat(10_000) }) },
      { status: 200, type: 'application/json', body: JSON.stringify({ ...document, client_id: 'https://assistant.example.com/other.json' }) },
    ]) {
      forgetClientDocuments();
      network.documents.set(id, answer);
      await expect(findClient(client, id)).resolves.toBeNull();
    }
    forgetClientDocuments();
    network.documents.delete(id);
    await expect(findClient(client, id)).resolves.toBeNull();
  });

  it('fetches a document once for requests that arrive together', async () => {
    const { client } = service();
    network.documents.set(id, { status: 200, type: 'application/json', body: JSON.stringify(document) });
    const found = await Promise.all([findClient(client, id), findClient(client, id), findClient(client, id)]);
    expect(found.every((one) => one?.id === id)).toBe(true);
    expect(network.requests).toHaveLength(1);
  });

  it('gives up on a document whose whole answer takes too long', async () => {
    const { client } = service();
    vi.useFakeTimers({ toFake: ['setTimeout', 'clearTimeout'] });
    network.documents.set(id, { status: 0, type: 'application/json', body: '' });
    const pending = findClient(client, id);
    await vi.advanceTimersByTimeAsync(5_000);
    await expect(pending).resolves.toBeNull();
  });
});
