import 'server-only';

import { lookup as dnsLookup, type LookupAddress, type LookupOptions } from 'node:dns';
import type { IncomingMessage } from 'node:http';
import { request } from 'node:https';
import { BlockList, isIP } from 'node:net';
import type { SupabaseServiceClient } from '../supabase';
import { isRecord } from '../types';

/** An OAuth client asking for an account's parcels. */
export interface AssistantClient {
  id: string;
  name: string;
  uri: string | null;
  redirectUris: string[];
}

const BLOCKED_SCHEMES = new Set([
  'javascript', 'data', 'file', 'blob', 'about', 'vbscript', 'filesystem', 'view-source', 'ftp', 'ws', 'wss', 'mailto',
  'tel', 'sms', 'intent',
  // Schemes that open a web address in a browser, which would hand the code to a website as if to an app.
  'microsoft-edge', 'microsoft-edge-holographic', 'googlechrome', 'googlechromes', 'firefox', 'firefox-private',
  'opera', 'brave', 'duckduckgo', 'x-web-search', 'x-safari-http', 'x-safari-https',
]);
const LOOPBACK_HOSTS = new Set(['localhost', '127.0.0.1', '[::1]']);
export const MOST_REDIRECT_URIS = 10;

/** Why a redirect address can't be registered, or null when it can: HTTPS, HTTP to this computer, or an app's own scheme. */
export function redirectUriProblem(uri: string): string | null {
  if (uri.length > 2_000) return 'is too long';
  let url: URL;
  try {
    url = new URL(uri);
  } catch {
    return 'is not an address';
  }
  if (url.hash || uri.includes('#')) return 'has a fragment';
  if (url.username || url.password) return 'has credentials';
  if (url.protocol === 'https:') return url.hostname ? null : 'has no host';
  if (url.protocol === 'http:') return LOOPBACK_HOSTS.has(url.hostname) ? null : 'uses HTTP away from this computer';
  const scheme = url.protocol.slice(0, -1);
  return /^[a-z][a-z0-9+.-]*$/.test(scheme) && !BLOCKED_SCHEMES.has(scheme) && !/-https?$/.test(scheme)
    ? null : 'uses a scheme that is not allowed';
}

function loopback(url: URL): boolean {
  return url.protocol === 'http:' && LOOPBACK_HOSTS.has(url.hostname);
}

/**
 * Whether the address a client sent is one it registered. They must be the same, except that an HTTP address on this
 * computer may name any port, since the app picks a free one each time (RFC 8252).
 */
export function redirectMatches(registered: string, presented: string): boolean {
  if (registered === presented) return true;
  try {
    const a = new URL(registered);
    const b = new URL(presented);
    return loopback(a) && loopback(b) && a.hostname === b.hostname && a.pathname === b.pathname && a.search === b.search
      && !b.hash && !b.username && !b.password;
  } catch {
    return false;
  }
}

/** The host a consent page names for a redirect address: the site, or the app's scheme. */
export function redirectHost(uri: string): string {
  const url = new URL(uri);
  if (loopback(url)) return 'localhost';
  return url.protocol === 'https:' ? url.hostname : `${url.protocol}//`;
}

/** Google's assistants keep one refresh token for good, and must not be given new ones. */
export function keepsRefreshToken(uri: string): boolean {
  try {
    const url = new URL(uri);
    return url.protocol === 'https:' && /^oauth-redirect(?:-sandbox|-test)?\.googleusercontent\.com$/.test(url.hostname);
  } catch {
    return false;
  }
}

/** A name fit to show: printable, single-line, at most 100 characters. */
export function clientName(value: unknown): string | null {
  if (typeof value !== 'string') return null;
  const name = value.replace(/[\p{Cc}\p{Cf}\p{Zl}\p{Zp}]/gu, ' ').replace(/\s+/g, ' ').trim();
  return name ? [...name].slice(0, 100).join('') : null;
}

export function clientUri(value: unknown): string | null {
  if (typeof value !== 'string' || value.length > 500) return null;
  try {
    return new URL(value).protocol === 'https:' ? value : null;
  } catch {
    return null;
  }
}

/** Whether a client id is the address of its own metadata document (an OAuth Client ID Metadata Document). */
export function isMetadataDocumentId(id: string): boolean {
  if (id.length > 500) return false;
  try {
    const url = new URL(id);
    return url.protocol === 'https:' && url.href === id && url.pathname !== '/' && !url.hash && !url.username
      && !url.password && !url.port && !isIP(url.hostname.replace(/^\[|\]$/g, ''))
      && !url.pathname.split('/').some((segment) => segment === '.' || segment === '..');
  } catch {
    return false;
  }
}

// Addresses a metadata document is never fetched from: this network, private ranges, and the special ones.
const unreachable = new BlockList();
for (const [network, prefix] of [
  ['0.0.0.0', 8], ['10.0.0.0', 8], ['100.64.0.0', 10], ['127.0.0.0', 8], ['169.254.0.0', 16], ['172.16.0.0', 12],
  ['192.0.0.0', 24], ['192.0.2.0', 24], ['192.168.0.0', 16], ['198.18.0.0', 15], ['198.51.100.0', 24],
  ['203.0.113.0', 24], ['224.0.0.0', 4], ['240.0.0.0', 4],
] as const) unreachable.addSubnet(network, prefix, 'ipv4');
// No ::ffff:0:0/96: BlockList checks IPv4-mapped addresses against the IPv4 ranges, and that range would block every IPv4 one.
for (const [network, prefix] of [
  ['::', 127], ['64:ff9b::', 96], ['100::', 64], ['2001::', 23], ['2001:db8::', 32],
  ['2002::', 16], ['fc00::', 7], ['fe80::', 10], ['ff00::', 8],
] as const) unreachable.addSubnet(network, prefix, 'ipv6');

export function publicAddress(address: string): boolean {
  const family = isIP(address);
  return family !== 0 && !unreachable.check(address, family === 6 ? 'ipv6' : 'ipv4');
}

type LookupCallback = (error: NodeJS.ErrnoException | null, address: string | LookupAddress[], family?: number) => void;

/** DNS as the request makes it, refusing every address that isn't public: no rebinding to a private one. */
function publicLookup(hostname: string, options: LookupOptions, callback: LookupCallback): void {
  dnsLookup(hostname, { ...options, all: true }, (error, addresses) => {
    if (error) return callback(error, []);
    const usable = addresses.filter(({ address }) => publicAddress(address));
    if (!usable.length) return callback(Object.assign(new Error('No public address'), { code: 'ENOTFOUND' }), []);
    if (options.all) return callback(null, usable);
    callback(null, usable[0]!.address, usable[0]!.family);
  });
}

const MOST_DOCUMENT_BYTES = 10_000;
const DOCUMENT_TIMEOUT_MS = 5_000;
const DOCUMENT_LIFETIME_MS = 3_600_000;
const FAILURE_LIFETIME_MS = 60_000;
const MOST_CACHED_DOCUMENTS = 200;
const MOST_FETCHES_AT_ONCE = 16;
// Documents read, and the addresses that gave none, apart: a flood of bad addresses can't push out good clients.
const documents = new Map<string, { client: AssistantClient; until: number }>();
const failures = new Map<string, number>();
const fetching = new Map<string, Promise<AssistantClient | null>>();

function remember<Value>(cache: Map<string, Value>, key: string, value: Value): void {
  cache.delete(key);
  if (cache.size >= MOST_CACHED_DOCUMENTS) cache.delete(cache.keys().next().value!);
  cache.set(key, value);
}

/**
 * The document at an address. It settles only once the connection has closed, so a fetch counts among those at once
 * until its socket is gone; any answer refused closes the connection there and then, without reading on.
 */
function readDocument(url: string): Promise<unknown> {
  return new Promise((resolve, reject) => {
    let outcome: { document: unknown } | { error: unknown } | undefined;
    let answer: IncomingMessage | undefined;
    const fail = (error: unknown) => {
      outcome ??= { error };
      answer?.destroy();
      outgoing.destroy();
    };
    const outgoing = request(url, {
      method: 'GET',
      agent: false,
      lookup: publicLookup as never,
      headers: { Accept: 'application/json', 'User-Agent': 'Peek OAuth' },
      timeout: DOCUMENT_TIMEOUT_MS,
    }, (response) => {
      answer = response;
      if (response.statusCode !== 200 || !/^application\/(?:[\w.+-]+\+)?json\b/i.test(response.headers['content-type'] ?? '')) {
        return fail(new Error(`The metadata document answered ${response.statusCode}`));
      }
      const chunks: Buffer[] = [];
      let size = 0;
      response.on('data', (chunk: Buffer) => {
        size += chunk.length;
        if (size > MOST_DOCUMENT_BYTES) return fail(new Error('The metadata document is too large'));
        chunks.push(chunk);
      });
      response.on('end', () => {
        try {
          outcome ??= { document: JSON.parse(Buffer.concat(chunks).toString('utf8')) };
        } catch (error) {
          fail(error);
        }
      });
      response.on('error', fail);
    });
    // The whole answer, not each pause in it, must come within the time, and the connection must end with it.
    const deadline = setTimeout(() => fail(new Error('The metadata document took too long')), DOCUMENT_TIMEOUT_MS);
    outgoing.on('timeout', () => fail(new Error('The metadata document took too long')));
    outgoing.on('error', fail);
    outgoing.on('close', () => {
      clearTimeout(deadline);
      if (outcome && 'document' in outcome) resolve(outcome.document);
      else reject(outcome?.error ?? new Error('The metadata document connection closed'));
    });
    outgoing.end();
  });
}

/** The client a metadata document describes, when it is one Peek can serve: a public client, with its own address as id. */
export function clientFromDocument(id: string, document: unknown): AssistantClient | null {
  if (!isRecord(document) || document.client_id !== id) return null;
  const method = document.token_endpoint_auth_method;
  if (method !== undefined && method !== 'none') return null;
  const uris = document.redirect_uris;
  if (!Array.isArray(uris) || !uris.length || uris.length > MOST_REDIRECT_URIS) return null;
  if (!uris.every((uri) => typeof uri === 'string' && redirectUriProblem(uri) === null)) return null;
  const name = clientName(document.client_name) ?? new URL(id).hostname;
  return { id, name, uri: clientUri(document.client_uri), redirectUris: uris as string[] };
}

/**
 * The client a metadata document describes, read at most once an hour, and once at a time. A document that can't be
 * read is tried again a minute later; while many are being read (until their connections have closed), a new one counts
 * as unknown.
 */
async function documentClient(id: string, now = Date.now()): Promise<AssistantClient | null> {
  const cached = documents.get(id);
  if (cached && cached.until > now) return cached.client;
  if ((failures.get(id) ?? 0) > now) return null;
  const pending = fetching.get(id);
  if (pending) return pending;
  if (fetching.size >= MOST_FETCHES_AT_ONCE) return null;
  const read = readDocument(id).then((document) => clientFromDocument(id, document), () => null).then((client) => {
    if (client) remember(documents, id, { client, until: Date.now() + DOCUMENT_LIFETIME_MS });
    else remember(failures, id, Date.now() + FAILURE_LIFETIME_MS);
    return client;
  }).finally(() => fetching.delete(id));
  fetching.set(id, read);
  return read;
}

/** The client an id names: one that registered with Peek, or one that publishes its metadata document. */
export async function findClient(service: SupabaseServiceClient, id: string): Promise<AssistantClient | null> {
  if (/^peek_c_[A-Za-z0-9_-]{22}$/.test(id)) return service.assistantClient(id);
  return isMetadataDocumentId(id) ? documentClient(id) : null;
}

/** Forgets the metadata documents read, for tests. */
export function forgetClientDocuments(): void {
  documents.clear();
  failures.clear();
}
