import 'server-only';

import { promises as dns, type LookupAddress } from 'node:dns';
import { request as httpsRequest } from 'node:https';
import { BlockList, isIP, type LookupFunction } from 'node:net';

/**
 * Where the server may send what a phone asked for: a host on the public internet. A UnifiedPush
 * distributor names any host (ntfy.sh, a self-hosted server), so the browser services' allowlist cannot
 * apply. Instead the address is checked when the endpoint is registered and again, after resolving its
 * name, at each send, which connects to the very address it checked.
 */

/** IPv4 ranges no push server lives in: this network, private, shared (CGNAT), loopback, link-local, documentation, multicast, reserved. */
const NON_PUBLIC_IPV4 = new BlockList();
for (const [network, prefix] of [
  ['0.0.0.0', 8], ['10.0.0.0', 8], ['100.64.0.0', 10], ['127.0.0.0', 8], ['169.254.0.0', 16], ['172.16.0.0', 12],
  ['192.0.0.0', 24], ['192.0.2.0', 24], ['192.88.99.0', 24], ['192.168.0.0', 16], ['198.18.0.0', 15],
  ['198.51.100.0', 24], ['203.0.113.0', 24], ['224.0.0.0', 4], ['240.0.0.0', 4],
] as const) NON_PUBLIC_IPV4.addSubnet(network, prefix, 'ipv4');

/** Top-level names that never leave the network they are asked in, or name no host at all (`home.arpa` and the reverse zones end in `arpa`). */
const PRIVATE_SUFFIXES = ['localhost', 'local', 'internal', 'localdomain', 'lan', 'home', 'corp', 'intranet', 'arpa', 'onion', 'test', 'invalid', 'example'];

/** The eight 16-bit words of an IPv6 address `isIP` accepts, without a zone. */
function ipv6Words(address: string): number[] | null {
  let text = address.toLowerCase();
  const dotted = /(?:^|:)(\d+\.\d+\.\d+\.\d+)$/.exec(text)?.[1];
  if (dotted) {
    if (isIP(dotted) !== 4) return null;
    const [a, b, c, d] = dotted.split('.').map(Number) as [number, number, number, number];
    text = `${text.slice(0, -dotted.length)}${((a << 8) | b).toString(16)}:${((c << 8) | d).toString(16)}`;
  }
  const halves = text.split('::');
  if (halves.length > 2) return null;
  const head = halves[0] ? halves[0].split(':') : [];
  const tail = halves.length === 2 && halves[1] ? halves[1].split(':') : [];
  const missing = 8 - head.length - tail.length;
  if (halves.length === 1 ? missing !== 0 : missing < 1) return null;
  const words = [...head, ...Array<string>(halves.length === 2 ? missing : 0).fill('0'), ...tail];
  if (!words.every((word) => /^[0-9a-f]{1,4}$/.test(word))) return null;
  return words.map((word) => Number.parseInt(word, 16));
}

/**
 * Whether an IP address is one of the public internet's. IPv4-mapped (`::ffff:10.0.0.1`) and NAT64
 * (`64:ff9b::10.0.0.1`) addresses are the IPv4 address they carry. Of IPv6, only global unicast
 * (2000::/3) is public, less its protocol (2001::/23), documentation (2001:db8::/32, 3fff::/20) and
 * 6to4 (2002::/16) ranges. An address with a zone (`fe80::1%eth0`) never is.
 */
export function isPublicAddress(address: string): boolean {
  const family = isIP(address);
  if (family === 4) return !NON_PUBLIC_IPV4.check(address, 'ipv4');
  if (family !== 6 || address.includes('%')) return false;
  const words = ipv6Words(address);
  if (!words) return false;
  const [first, second] = words as [number, number];
  const mapped = words.slice(0, 5).every((word) => word === 0) && words[5] === 0xffff;
  const nat64 = first === 0x64 && second === 0xff9b && words.slice(2, 6).every((word) => word === 0);
  if (mapped || nat64) {
    const [high, low] = words.slice(6) as [number, number];
    return isPublicAddress(`${high >> 8}.${high & 255}.${low >> 8}.${low & 255}`);
  }
  if ((first & 0xe000) !== 0x2000) return false;
  if (first === 0x2001 && (second < 0x200 || second === 0xdb8)) return false;
  return first !== 0x2002 && !(first === 0x3fff && second < 0x1000);
}

/** A URL's host as an address or a name: without the brackets of IPv6, the final dot of a name, or capitals. */
export function urlHost(url: URL): string {
  return url.hostname.replace(/^\[(.*)\]$/, '$1').replace(/\.$/, '').toLowerCase();
}

/**
 * Whether a host, as `urlHost` gives it, may be pushed to: a public IP address, or a name of at least two
 * labels outside the ones private networks use (`localhost`, `.local`, `.internal`…). A name passes here
 * and is checked again once resolved (`publicAddresses`).
 */
export function isPublicHost(host: string): boolean {
  if (isIP(host)) return isPublicAddress(host);
  if (host.length > 253 || !host.includes('.')) return false;
  const labels = host.split('.');
  if (!labels.every((label) => /^[a-z0-9](?:[a-z0-9-]{0,61}[a-z0-9])?$/.test(label))) return false;
  // A name ending in digits alone is an address written in a form `isIP` leaves out.
  if (/^\d+$/.test(labels.at(-1)!)) return false;
  return !PRIVATE_SUFFIXES.includes(labels.at(-1)!);
}

export class NonPublicAddressError extends Error {
  constructor(message = 'The push endpoint does not resolve to public addresses only') {
    super(message);
    this.name = 'NonPublicAddressError';
  }
}

export type AddressLookup = (hostname: string, options: { all: true }) => Promise<LookupAddress[]>;

/**
 * Every address a host resolves to, refused when any of them is not public: a name that also points into
 * the server's own network could be answered with that address on the next resolution.
 */
export async function publicAddresses(host: string, lookup: AddressLookup = dns.lookup): Promise<LookupAddress[]> {
  const literal = isIP(host);
  if (!isPublicHost(host)) throw new NonPublicAddressError('The push endpoint is not a public host');
  if (literal) return [{ address: host, family: literal }];
  const addresses = await lookup(host, { all: true });
  if (addresses.length === 0 || !addresses.every(({ address }) => isPublicAddress(address))) {
    throw new NonPublicAddressError();
  }
  return addresses;
}

/** A lookup that answers with the addresses already checked, whatever it is asked, so the connection goes nowhere else. */
export function pinnedLookup(addresses: readonly LookupAddress[]): LookupFunction {
  return (_hostname, options, callback) => {
    const family = options.family === 4 || options.family === 'IPv4' ? 4 : options.family === 6 || options.family === 'IPv6' ? 6 : 0;
    const offered = addresses.filter((address) => !family || address.family === family);
    if (offered.length === 0) {
      const error: NodeJS.ErrnoException = new Error('No checked address of the requested family');
      error.code = 'ENOTFOUND';
      callback(error, '', 0);
      return;
    }
    if (options.all) callback(null, offered.map((address) => ({ ...address })));
    else callback(null, offered[0]!.address, offered[0]!.family);
  };
}

export interface PublicPostOptions {
  headers: Record<string, string | number>;
  body: Buffer | null;
  /** The whole request, from resolving the name to the end of the answer. */
  timeoutMs: number;
  /** What is read of the answer before the connection is closed. */
  maxResponseBytes?: number;
  lookup?: AddressLookup;
}

/**
 * POSTs to a public HTTPS endpoint and answers with the status code: the name is resolved and checked,
 * the connection made to a checked address only, a redirect is a status like any other, and the answer
 * is read up to a few kilobytes and within the time given.
 */
export async function postToPublicEndpoint(endpoint: string, options: PublicPostOptions): Promise<number> {
  const url = new URL(endpoint);
  if (url.protocol !== 'https:' || url.username || url.password) throw new NonPublicAddressError('The push endpoint is not an HTTPS URL');
  const signal = AbortSignal.timeout(options.timeoutMs);
  const addresses = await Promise.race([
    publicAddresses(urlHost(url), options.lookup),
    new Promise<never>((_, reject) => {
      signal.addEventListener('abort', () => reject(signal.reason as Error), { once: true });
    }),
  ]);
  const maxResponseBytes = options.maxResponseBytes ?? 8_192;
  return await new Promise<number>((resolve, reject) => {
    const request = httpsRequest({
      protocol: 'https:',
      hostname: url.hostname.replace(/^\[(.*)\]$/, '$1'),
      port: url.port || 443,
      path: `${url.pathname}${url.search}`,
      method: 'POST',
      headers: options.headers,
      lookup: pinnedLookup(addresses),
      agent: false,
      signal,
    }, (response) => {
      const status = response.statusCode ?? 0;
      let read = 0;
      response.on('data', (chunk: Buffer) => {
        read += chunk.byteLength;
        if (read > maxResponseBytes) {
          response.destroy();
          resolve(status);
        }
      });
      response.once('end', () => resolve(status));
      response.once('error', () => resolve(status));
      response.once('close', () => resolve(status));
    });
    request.once('error', reject);
    request.end(options.body ?? undefined);
  });
}
