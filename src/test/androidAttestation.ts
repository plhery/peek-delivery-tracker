import { generateKeyPairSync, sign, type KeyObject } from 'node:crypto';

/**
 * Synthetic Android key attestations: a certificate chain built here, root to leaf, whose leaf carries the key
 * description extension Android's secure hardware writes (https://source.android.com/docs/security/features/keystore/attestation).
 * Its root is a test root the tests trust in place of Google's; every value is made up.
 */

const length = (size: number) => {
  if (size < 0x80) return Buffer.from([size]);
  const bytes = Buffer.from(size.toString(16).padStart(size > 0xffff ? 6 : size > 0xff ? 4 : 2, '0'), 'hex');
  return Buffer.concat([Buffer.from([0x80 | bytes.length]), bytes]);
};
/** An element whose identifier is `identifier` (one or more bytes). */
export const der = (identifier: number | number[], ...parts: Buffer[]) => {
  const body = Buffer.concat(parts);
  return Buffer.concat([Buffer.from(Array.isArray(identifier) ? identifier : [identifier]), length(body.length), body]);
};
const unsigned = (value: number | bigint) => {
  let hex = value.toString(16);
  if (hex.length % 2) hex = `0${hex}`;
  const bytes = Buffer.from(hex, 'hex');
  return bytes[0]! & 0x80 ? Buffer.concat([Buffer.from([0]), bytes]) : bytes;
};
export const integer = (value: number | bigint) => der(0x02, unsigned(value));
export const enumerated = (value: number) => der(0x0a, unsigned(value));
export const octets = (value: Buffer | string) => der(0x04, Buffer.isBuffer(value) ? value : Buffer.from(value));
export const boolean = (value: boolean) => der(0x01, Buffer.from([value ? 0xff : 0]));
export const sequence = (...parts: Buffer[]) => der(0x30, ...parts);
export const set = (...parts: Buffer[]) => der(0x31, ...parts);
const oid = (hex: string) => der(0x06, Buffer.from(hex, 'hex'));
const nul = () => Buffer.from([0x05, 0x00]);
/** An explicitly tagged field, `[tag]`, as an AuthorizationList's. */
export const tagged = (tag: number, value: Buffer) => {
  if (tag < 31) return der(0xa0 | tag, value);
  const arcs = [tag & 0x7f];
  for (let rest = tag >> 7; rest; rest >>= 7) arcs.unshift(0x80 | (rest & 0x7f));
  return der([0xbf, ...arcs], value);
};

const time = (at: Date) => {
  const text = at.toISOString().replace(/[-:T]/g, '').replace(/\.\d+Z$/, 'Z');
  return at.getUTCFullYear() < 2050 ? der(0x17, Buffer.from(text.slice(2))) : der(0x18, Buffer.from(text));
};
const name = (common: string) => sequence(set(sequence(oid('550403'), der(0x0c, Buffer.from(common)))));
/** A factory-keyed batch's name, as its certificate under Google's root carries it. */
const batch = (serial: string) => sequence(set(sequence(oid('550405'), der(0x13, Buffer.from(serial)))));
const ECDSA_SHA256 = sequence(oid('2a8648ce3d040302'));
const KEY_DESCRIPTION = '2b06010401d679020111';

interface CertificateOptions {
  subject: string | Buffer;
  issuer: string;
  key: KeyObject;
  signer: KeyObject;
  serial?: bigint;
  notBefore?: Date;
  notAfter?: Date;
  ca?: boolean;
  description?: Buffer;
}

export function certificate(options: CertificateOptions): Buffer {
  const extensions: Buffer[] = [];
  if (options.ca) extensions.push(sequence(oid('551d13'), boolean(true), octets(sequence(boolean(true)))));
  if (options.description) extensions.push(sequence(oid(KEY_DESCRIPTION), octets(options.description)));
  const tbs = sequence(
    der(0xa0, integer(2)),
    integer(options.serial ?? 1n),
    ECDSA_SHA256,
    name(options.issuer),
    sequence(time(options.notBefore ?? new Date('2020-01-01T00:00:00Z')), time(options.notAfter ?? new Date('2048-01-01T00:00:00Z'))),
    typeof options.subject === 'string' ? name(options.subject) : options.subject,
    options.key.export({ type: 'spki', format: 'der' }),
    ...(extensions.length ? [der(0xa3, sequence(...extensions))] : []),
  );
  return sequence(tbs, ECDSA_SHA256, der(0x03, Buffer.from([0]), sign('sha256', tbs, options.signer)));
}

export const PEEK_PACKAGE = 'com.plhery.peekdeliverytracker';
/** Google Play's v3.0 signing certificate digest for Peek, one of the server's own. */
export const PLAY_SIGNER = 'f155dbe776f45feaf2514f733b984da3fc5f562ada66f2bd651fc589d2bdde60';

export interface Description {
  challenge: Buffer;
  version?: number;
  securityLevel?: number;
  keyMintSecurityLevel?: number;
  locked?: boolean;
  bootState?: number;
  bootKey?: Buffer;
  origin?: number | null;
  algorithm?: number;
  curve?: number;
  packages?: string[];
  signers?: string[];
  /** Where attestationApplicationId goes: the operating system's list, as Android writes it, or the hardware's. */
  applicationIdIn?: 'software' | 'hardware' | 'none';
  rootOfTrust?: boolean;
}

/** A KeyDescription (version 300, KeyMint 3) for a P-256 signing key, made in the TEE of a locked, verified phone. */
export function keyDescription(options: Description): Buffer {
  const applicationId = tagged(709, octets(sequence(
    set(...(options.packages ?? [PEEK_PACKAGE]).map((pkg) => sequence(octets(pkg), integer(14)))),
    set(...(options.signers ?? [PLAY_SIGNER]).map((digest) => octets(Buffer.from(digest, 'hex')))),
  )));
  const where = options.applicationIdIn ?? 'software';
  const software = sequence(tagged(701, integer(1_763_400_000_000)), ...(where === 'software' ? [applicationId] : []));
  const hardware = sequence(
    tagged(1, set(integer(2))),
    tagged(2, integer(options.algorithm ?? 3)),
    tagged(3, integer(256)),
    tagged(5, set(integer(4))),
    tagged(10, integer(options.curve ?? 1)),
    tagged(503, nul()),
    ...(options.origin === null ? [] : [tagged(702, integer(options.origin ?? 0))]),
    ...(options.rootOfTrust === false ? [] : [tagged(704, sequence(
      octets(options.bootKey ?? Buffer.alloc(32, 0x11)),
      boolean(options.locked ?? true),
      enumerated(options.bootState ?? 0),
      octets(Buffer.alloc(32, 0x22)),
    ))]),
    tagged(705, integer(160_000)),
    tagged(706, integer(202_611)),
    ...(where === 'hardware' ? [applicationId] : []),
  );
  return sequence(
    integer(options.version ?? 300),
    enumerated(options.securityLevel ?? 1),
    integer(300),
    enumerated(options.keyMintSecurityLevel ?? 1),
    octets(options.challenge),
    octets(Buffer.alloc(0)),
    software,
    hardware,
  );
}

const ec = () => generateKeyPairSync('ec', { namedCurve: 'prime256v1' });

/** A test root, the one the tests trust. */
export function attestationRoot() {
  const { privateKey, publicKey } = ec();
  const der = certificate({ subject: 'Test attestation root', issuer: 'Test attestation root', key: publicKey, signer: privateKey, ca: true });
  return { privateKey, publicKey, der, pem: `-----BEGIN CERTIFICATE-----\n${der.toString('base64').match(/.{1,64}/g)!.join('\n')}\n-----END CERTIFICATE-----\n` };
}

export interface ChainOptions extends Description {
  root: ReturnType<typeof attestationRoot>;
  /** A leaf key other than a fresh P-256 one. */
  leafKey?: { privateKey: KeyObject; publicKey: KeyObject };
  intermediateSerial?: bigint;
  intermediateNotAfter?: Date;
  /** Puts a key description on the intermediate too. */
  describedIntermediate?: boolean;
  /** Names the intermediate as a factory-keyed batch. */
  factory?: boolean;
  /** Leaves the key description off the leaf. */
  plainLeaf?: boolean;
  leafNotAfter?: Date;
}

/** A chain, leaf first, as the app sends it, and the leaf's private key, which signs assertions. */
export function attestationChain(options: ChainOptions) {
  const intermediate = ec();
  const leaf = options.leafKey ?? ec();
  const description = keyDescription(options);
  const middle = certificate({
    subject: options.factory ? batch('0123456789abcdef') : 'Test attestation intermediate', issuer: 'Test attestation root', key: intermediate.publicKey, signer: options.root.privateKey,
    serial: options.intermediateSerial ?? 0x0f1e2d3cn, ca: true, notAfter: options.intermediateNotAfter,
    description: options.describedIntermediate ? description : undefined,
  });
  const end = certificate({
    subject: 'Android Keystore Key', issuer: 'Test attestation intermediate', key: leaf.publicKey, signer: intermediate.privateKey,
    notBefore: new Date(0), notAfter: options.leafNotAfter, description: options.plainLeaf ? undefined : description,
  });
  return { x5c: [end, middle, options.root.der], privateKey: leaf.privateKey };
}
