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
  // Schemes the system itself handles: they open files, shares, installers and settings, which no assistant's app needs.
  'search', 'shell', 'hcp', 'its', 'mk', 'res', 'jar', 'smb', 'nfs', 'afp', 'webdav', 'davs', 'ssh', 'sftp', 'telnet',
  'rdp', 'vnc', 'ldap', 'ldaps', 'gopher', 'news', 'nntp', 'feed', 'webcal', 'callto', 'sip', 'sips', 'facetime',
  'facetime-audio', 'shortcuts', 'workflow', 'prefs', 'app-settings', 'help', 'x-apple-helpbook',
  'x-apple-systempreferences', 'applescript',
]);
// Windows' own schemes (search-ms, ms-msdt, ms-officecmd, ms-settings…) and Apple's installers (itms-services…).
const BLOCKED_SCHEME_PREFIXES = /^(?:ms-|search-ms$|itms(?:-|s?$)|vnd\.ms-)/;
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
  return /^[a-z][a-z0-9+.-]*$/.test(scheme) && !BLOCKED_SCHEMES.has(scheme) && !BLOCKED_SCHEME_PREFIXES.test(scheme)
    && !/-https?$/.test(scheme) ? null : 'uses a scheme that is not allowed';
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
// An IPv6 address must be global unicast (2000::/3): that leaves out loopback, the IPv4-mapped and -compatible forms,
// NAT64, unique-local, link- and site-local and multicast. Within it, these special ranges are left out too.
const globalUnicast = new BlockList();
globalUnicast.addSubnet('2000::', 3, 'ipv6');
for (const [network, prefix] of [
  ['2001::', 23], ['2001:db8::', 32], ['2002::', 16], ['3fff::', 20],
] as const) unreachable.addSubnet(network, prefix, 'ipv6');

export function publicAddress(address: string): boolean {
  const family = isIP(address);
  if (family === 4) return !unreachable.check(address, 'ipv4');
  return family === 6 && globalUnicast.check(address, 'ipv6') && !unreachable.check(address, 'ipv6');
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
/** How long a connected client's document stands in once it is due again, while a fresh one can't be read. */
const STAND_IN_MS = 86_400_000;
const FAILURE_LIFETIME_MS = 60_000;
const MOST_CACHED_DOCUMENTS = 200;
const MOST_DOCUMENTS_PER_SITE = 10;
const MOST_FETCHES_AT_ONCE = 16;
const MOST_FETCHES_PER_SITE = 2;
const MOST_CONNECTED_CLIENTS = 1_000;
// Documents read, and the addresses that gave none, apart: a flood of bad addresses can't push out good clients.
const documents = new Map<string, { client: AssistantClient; until: number }>();
const failures = new Map<string, number>();
const fetching = new Map<string, Promise<AssistantClient | null>>();
/** Clients whose tokens reached the MCP endpoint lately, in order of use: their documents are pushed out last. */
const connected = new Set<string>();

/**
 * The site a document's address belongs to, as far as limits go: its host's last two labels, so that one domain's many
 * names count as one. A country's second-level domain (example.co.uk) then shares its limits with others, which only
 * makes it stricter.
 */
function siteOf(id: string): string {
  return new URL(id).hostname.split('.').slice(-2).join('.');
}

/** Notes a client whose token reached the MCP endpoint: its document is kept before others'. */
export function noteConnectedClient(id: string): void {
  if (!isMetadataDocumentId(id)) return;
  connected.delete(id);
  if (connected.size >= MOST_CONNECTED_CLIENTS) connected.delete(connected.values().next().value!);
  connected.add(id);
}

/**
 * Keeps a value, used last, in a cache that holds at most `MOST_CACHED_DOCUMENTS`, and `MOST_DOCUMENTS_PER_SITE` of one
 * site: past either, the entry used least recently goes, a connected client's last. A site's many addresses then push
 * out only that site's own.
 */
function remember<Value>(cache: Map<string, Value>, key: string, value: Value): void {
  cache.delete(key);
  const site = siteOf(key);
  const pushOut = (candidates: string[]) => {
    const chosen = candidates.find((candidate) => !connected.has(candidate)) ?? candidates[0];
    if (chosen !== undefined) cache.delete(chosen);
  };
  const sameSite = [...cache.keys()].filter((other) => siteOf(other) === site);
  if (sameSite.length >= MOST_DOCUMENTS_PER_SITE) pushOut(sameSite);
  if (cache.size >= MOST_CACHED_DOCUMENTS) pushOut([...cache.keys()]);
  cache.set(key, value);
}

/** A document its site says is gone (404, 410): the client withdrew it, which no older copy outlives. */
class WithdrawnDocument extends Error {}

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
      if (response.statusCode === 404 || response.statusCode === 410) return fail(new WithdrawnDocument(`The metadata document answered ${response.statusCode}`));
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

/**
 * The client a metadata document describes, when it is one Peek can serve: a public client, with its own address as id.
 * A document that lists the methods it can sign in to the token endpoint with (ChatGPT's) must list `none`, whatever
 * method it prefers; one that names a single method must name `none`.
 */
export function clientFromDocument(id: string, document: unknown): AssistantClient | null {
  if (!isRecord(document) || document.client_id !== id) return null;
  const methods = document.token_endpoint_auth_methods_supported;
  const method = document.token_endpoint_auth_method;
  if (methods !== undefined ? !Array.isArray(methods) || !methods.includes('none') : method !== undefined && method !== 'none') return null;
  const uris = document.redirect_uris;
  if (!Array.isArray(uris) || !uris.length || uris.length > MOST_REDIRECT_URIS) return null;
  if (!uris.every((uri) => typeof uri === 'string' && redirectUriProblem(uri) === null)) return null;
  const name = clientName(document.client_name) ?? new URL(id).hostname;
  return { id, name, uri: clientUri(document.client_uri), redirectUris: uris as string[] };
}

/**
 * The client a metadata document describes, read at most once an hour, and once at a time. A document that can't be
 * read is tried again a minute later. While many are being read (until their connections have closed), 16 in all or 2
 * of one site, a new one counts as unknown. A connected client's document, once due again, stands in for a day while a
 * fresh one can't be read: while its site is down or slow, not once the site says it is gone (404, 410) or serves one
 * Peek can't serve, which ends the old one.
 */
async function documentClient(id: string, now = Date.now()): Promise<AssistantClient | null> {
  const cached = documents.get(id);
  if (cached && cached.until > now) {
    remember(documents, id, cached);
    return cached.client;
  }
  const standIn = () => {
    const kept = documents.get(id);
    return kept && connected.has(id) && kept.until + STAND_IN_MS > now ? kept.client : null;
  };
  if ((failures.get(id) ?? 0) > now) return standIn();
  const pending = fetching.get(id);
  if (pending) return (await pending) ?? standIn();
  const site = siteOf(id);
  if (fetching.size >= MOST_FETCHES_AT_ONCE || [...fetching.keys()].filter((other) => siteOf(other) === site).length >= MOST_FETCHES_PER_SITE) {
    return standIn();
  }
  const read = readDocument(id).then(
    (document) => ({ client: clientFromDocument(id, document), withdrawn: true }),
    (error: unknown) => ({ client: null, withdrawn: error instanceof WithdrawnDocument }),
  ).then(({ client, withdrawn }) => {
    if (client) remember(documents, id, { client, until: Date.now() + DOCUMENT_LIFETIME_MS });
    else {
      if (withdrawn) documents.delete(id);
      remember(failures, id, Date.now() + FAILURE_LIFETIME_MS);
    }
    return client;
  }).finally(() => fetching.delete(id));
  fetching.set(id, read);
  return (await read) ?? standIn();
}

/** The client an id names: one that registered with Peek, or one that publishes its metadata document. */
export async function findClient(service: SupabaseServiceClient, id: string): Promise<AssistantClient | null> {
  if (/^peek_c_[A-Za-z0-9_-]{22}$/.test(id)) return service.assistantClient(id);
  return isMetadataDocumentId(id) ? documentClient(id) : null;
}

/** Forgets the metadata documents read and the clients seen connected, for tests. */
export function forgetClientDocuments(): void {
  documents.clear();
  failures.clear();
  connected.clear();
}
