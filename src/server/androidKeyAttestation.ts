import 'server-only';

import { timingSafeEqual, X509Certificate } from 'node:crypto';
import { ANDROID_ATTESTATION_ROOTS, SELF_SIGNED_BOOT_KEYS } from './androidAttestationRoots';

/**
 * Android hardware key attestation (https://developer.android.com/privacy-and-security/security-key-attestation):
 * the app makes a key in the phone's secure hardware with the server's challenge, and the hardware signs a
 * certificate for it that chains to one of Google's roots and says what it knows: the challenge, how the phone
 * booted and which app asked. No Google service is involved, so the F-Droid build attests as the Play build does.
 */

/** Peek's Android app. */
export const ANDROID_PACKAGE = 'com.plhery.peekdeliverytracker';

/**
 * The SHA-256 digests of the certificates Peek's Android builds are signed with, read from the APKs. Each is public:
 * anyone with an APK reads it with `apksigner verify --print-certs`.
 */
export const ANDROID_SIGNERS: readonly string[] = [
  // Google Play's app signing certificate (APK Signature Scheme v3.0), also in assetlinks.json (androidAppLinks.ts).
  'f155dbe776f45feaf2514f733b984da3fc5f562ada66f2bd651fc589d2bdde60',
  // Google Play's for Android 17 and later (APK Signature Scheme v3.2: classical and post-quantum).
  '4dac3d982b1a79ef0088e5fcfe76e9922368750beffef54b102b64698e4af057',
  '31d6bd912a4e2d760c9a06906c71d5e0c20d2df775ac16114527f0fb39b261ae',
  // The upload key, which signs the builds that are not installed from Google Play.
  'f433cbf03c68766d1e6b321d1020e8fee51f75780b56c0393ff7420f93103f73',
];

/**
 * Why an attestation failed, as logs and metrics name it: `malformed`, `chain` (a certificate that does not parse or
 * is not signed by the next), `root` (not one of Google's hardware roots: an emulator's or software attestation),
 * `certificate_time` (an intermediate certificate out of its validity period), `revoked` (on Google's revocation list),
 * `revocation_unavailable` (the list could not be read),
 * `security_level` (software, not the phone's secure hardware), `nonce` (another challenge), `unlocked` (an unlocked
 * bootloader), `boot_state` (an unverified operating system, or a self-signed one not known here), `origin` (an
 * imported key, whose holder may have a copy), `key` (not a P-256 key), `app_id` (another app) and `signer` (another
 * signing certificate).
 */
export type AndroidFailure =
  | 'malformed' | 'chain' | 'root' | 'certificate_time' | 'revoked' | 'revocation_unavailable' | 'security_level'
  | 'nonce' | 'unlocked' | 'boot_state' | 'origin' | 'key' | 'app_id' | 'signer';

export class AndroidAttestationError extends Error {
  constructor(readonly reason: AndroidFailure) {
    super(`Android key attestation failed: ${reason}`);
  }
}
function fail(reason: AndroidFailure): never {
  throw new AndroidAttestationError(reason);
}

// A strict DER reader: definite lengths only, every byte accounted for.

interface Der {
  /** 0 universal, 1 application, 2 context-specific, 3 private. */
  cls: number;
  constructed: boolean;
  tag: number;
  body: Buffer;
  /** Where the element ends in the buffer it was read from. */
  end: number;
}

function readDer(bytes: Buffer, offset = 0): Der {
  let at = offset;
  const next = () => (at < bytes.length ? bytes[at++]! : fail('malformed'));
  const first = next();
  let tag = first & 0x1f;
  if (tag === 0x1f) {
    tag = 0;
    for (let count = 0, byte = 0x80; byte & 0x80; count++) {
      if (count === 4) fail('malformed');
      byte = next();
      tag = tag * 128 + (byte & 0x7f);
    }
  }
  let length = next();
  if (length & 0x80) {
    const count = length & 0x7f;
    if (count === 0 || count > 4) fail('malformed');
    length = 0;
    for (let index = 0; index < count; index++) length = length * 256 + next();
  }
  if (at + length > bytes.length) fail('malformed');
  return { cls: first >> 6, constructed: (first & 0x20) !== 0, tag, body: bytes.subarray(at, at + length), end: at + length };
}

/** The one element `bytes` holds, nothing after it. */
function readOnly(bytes: Buffer): Der {
  const element = readDer(bytes);
  if (element.end !== bytes.length) fail('malformed');
  return element;
}

function children(element: Der): Der[] {
  if (!element.constructed) fail('malformed');
  const items: Der[] = [];
  for (let at = 0; at < element.body.length;) {
    const item = readDer(element.body, at);
    items.push(item);
    at = item.end;
  }
  return items;
}

const UNIVERSAL = { boolean: 1, integer: 2, octets: 4, oid: 6, enumerated: 10, sequence: 16, set: 17 } as const;

function universal(element: Der | undefined, tag: number): Der {
  if (!element || element.cls !== 0 || element.tag !== tag) fail('malformed');
  return element!;
}

/** A non-negative INTEGER or ENUMERATED small enough for a number: versions, levels, dates as numbers. */
function small(element: Der | undefined, tag: number = UNIVERSAL.integer): number {
  const { body } = universal(element, tag);
  if (body.length === 0 || body.length > 6 || body[0]! & 0x80) fail('malformed');
  return body.reduce((value, byte) => value * 256 + byte, 0);
}

const octets = (element: Der | undefined) => universal(element, UNIVERSAL.octets).body;
const sequence = (element: Der | undefined) => children(universal(element, UNIVERSAL.sequence));
const set = (element: Der | undefined) => children(universal(element, UNIVERSAL.set));

function boolean(element: Der | undefined): boolean {
  const { body } = universal(element, UNIVERSAL.boolean);
  if (body.length !== 1) fail('malformed');
  return body[0] !== 0;
}

/** 1.3.6.1.4.1.11129.2.1.17, Android's key description extension, as DER writes the OID's arcs. */
const KEY_DESCRIPTION_OID = Buffer.from('2b06010401d679020111', 'hex');

/** The key description extension's value in a certificate, or null when it has none. */
function keyDescriptionExtension(certificate: Buffer): Buffer | null {
  const [tbs] = sequence(readOnly(certificate));
  const fields = sequence(tbs);
  // Extensions come last, explicitly tagged [3].
  const extensions = fields.find((field) => field.cls === 2 && field.tag === 3);
  if (!extensions) return null;
  let found: Buffer | null = null;
  for (const extension of sequence(children(extensions)[0])) {
    const parts = sequence(extension);
    if (!universal(parts[0], UNIVERSAL.oid).body.equals(KEY_DESCRIPTION_OID)) continue;
    if (found) fail('malformed');
    found = octets(parts[parts.length - 1]);
  }
  return found;
}

/** An AuthorizationList's explicitly tagged fields by tag number. */
function authorizations(element: Der | undefined): Map<number, Der> {
  const fields = new Map<number, Der>();
  for (const field of sequence(element)) {
    if (field.cls !== 2 || fields.has(field.tag)) fail('malformed');
    const inner = children(field);
    if (inner.length !== 1) fail('malformed');
    fields.set(field.tag, inner[0]!);
  }
  return fields;
}

const TAG = { algorithm: 2, ecCurve: 10, origin: 702, rootOfTrust: 704, attestationApplicationId: 709 } as const;
const SECURITY_LEVEL = { software: 0, trustedEnvironment: 1, strongBox: 2 } as const;
const VERIFIED_BOOT = { verified: 0, selfSigned: 1 } as const;
const ALGORITHM_EC = 3;
const CURVE_P256 = 1;
const ORIGIN_GENERATED = 0;

/** What a key description says, as far as Peek reads it. */
export interface KeyDescription {
  attestationVersion: number;
  attestationSecurityLevel: number;
  keyMintSecurityLevel: number;
  challenge: Buffer;
  /** The hardware's list: what the secure hardware itself enforces. */
  hardware: Map<number, Der>;
  /** The operating system's list. */
  software: Map<number, Der>;
}

export function parseKeyDescription(value: Buffer): KeyDescription {
  const fields = sequence(readOnly(value));
  // Later versions may add fields; the first eight are fixed.
  if (fields.length < 8) fail('malformed');
  return {
    attestationVersion: small(fields[0]),
    attestationSecurityLevel: small(fields[1], UNIVERSAL.enumerated),
    keyMintSecurityLevel: small(fields[3], UNIVERSAL.enumerated),
    challenge: octets(fields[4]),
    software: authorizations(fields[6]),
    hardware: authorizations(fields[7]),
  };
}

export interface RootOfTrust {
  verifiedBootKey: Buffer;
  deviceLocked: boolean;
  verifiedBootState: number;
}

export function rootOfTrust(element: Der): RootOfTrust {
  const fields = sequence(element);
  // verifiedBootHash follows from version 3 on.
  if (fields.length < 3) fail('malformed');
  return { verifiedBootKey: octets(fields[0]), deviceLocked: boolean(fields[1]), verifiedBootState: small(fields[2], UNIVERSAL.enumerated) };
}

export interface ApplicationId {
  packages: string[];
  /** SHA-256 digests of the signing certificates, in lowercase hex. */
  signers: string[];
}

export function applicationId(element: Der): ApplicationId {
  const [packages, signers] = sequence(readOnly(octets(element)));
  return {
    packages: set(packages).map((info) => octets(sequence(info)[0]).toString('utf8')),
    signers: set(signers).map((digest) => octets(digest).toString('hex')),
  };
}

/** A certificate serial number as Google's revocation list writes it: lowercase hex, no leading zeros. */
export const serialKey = (serial: string) => serial.toLowerCase().replace(/^0+(?=.)/, '');

const spki = (certificate: X509Certificate) => certificate.publicKey.export({ type: 'spki', format: 'der' });
const trustedRoots = () => ANDROID_ATTESTATION_ROOTS.map((pem) => spki(new X509Certificate(pem)));
/**
 * Whether a chain was keyed in the factory rather than by Remote Key Provisioning: the certificate under the root
 * names a device batch's serial number. Google keeps trusting those chains after they expire, unless revoked.
 */
const factoryKeyed = (certificates: X509Certificate[]) => /(^|\n)serialNumber=/.test(certificates[certificates.length - 2]!.subject);

export interface AndroidPolicy {
  packageName: string;
  /** Lowercase hex SHA-256 digests of the signing certificates. */
  signers: ReadonlySet<string>;
}

export interface AndroidAttestation {
  /** The attested key, SPKI PEM. */
  publicKey: string;
}

/**
 * Checks an attestation's certificate chain (leaf first) for a key made with `challengeHash` as its attestation
 * challenge, following Google's steps: every certificate signed by the next, the last one's key one of Google's
 * roots, the intermediates in their validity period (the phone sets the leaf's dates, and a chain keyed in the
 * factory stays trusted after it expires) and none on the revocation list; then, in the leaf's key description, secure
 * hardware, the challenge, a locked bootloader with a verified operating system (or a self-signed one known here,
 * such as GrapheneOS), a generated P-256 key, and Peek's package signed with one of its certificates. Throws an
 * AndroidAttestationError naming the first failure.
 */
export async function verifyAndroidKeyAttestation(
  x5c: unknown,
  challengeHash: Buffer,
  policy: AndroidPolicy,
  revoked: () => Promise<ReadonlySet<string>>,
  now = Date.now(),
): Promise<AndroidAttestation> {
  if (!Array.isArray(x5c) || x5c.length < 3 || x5c.length > 8 || !x5c.every((item) => Buffer.isBuffer(item) && item.length <= 8_192)) {
    fail('malformed');
  }
  const encoded = x5c as Buffer[];
  const certificates = encoded.map((der) => {
    try {
      return new X509Certificate(der);
    } catch {
      return fail('chain');
    }
  });
  for (let index = 0; index < certificates.length - 1; index++) {
    let signed = false;
    try {
      signed = certificates[index]!.verify(certificates[index + 1]!.publicKey);
    } catch {
      signed = false;
    }
    if (!signed) fail('chain');
  }
  const root = spki(certificates[certificates.length - 1]!);
  if (!trustedRoots().some((trusted) => trusted.equals(root))) fail('root');
  if (!factoryKeyed(certificates)) {
    for (const certificate of certificates.slice(1, -1)) {
      if (Date.parse(certificate.validFrom) > now || Date.parse(certificate.validTo) <= now) fail('certificate_time');
    }
  }
  let list: ReadonlySet<string>;
  try {
    list = await revoked();
  } catch {
    return fail('revocation_unavailable');
  }
  if (certificates.some((certificate) => list.has(serialKey(certificate.serialNumber)))) fail('revoked');

  // The key description is the leaf's alone: a certificate above it with one would be a key attesting another.
  if (encoded.slice(1).some((der) => keyDescriptionExtension(der) !== null)) fail('chain');
  const extension = keyDescriptionExtension(encoded[0]!);
  if (!extension) fail('malformed');
  const description = parseKeyDescription(extension!);
  const hardware = [SECURITY_LEVEL.trustedEnvironment, SECURITY_LEVEL.strongBox] as number[];
  if (!hardware.includes(description.attestationSecurityLevel) || !hardware.includes(description.keyMintSecurityLevel)) fail('security_level');
  if (description.challenge.length !== challengeHash.length || !timingSafeEqual(description.challenge, challengeHash)) fail('nonce');

  const trust = description.hardware.get(TAG.rootOfTrust);
  if (!trust) fail('boot_state');
  const boot = rootOfTrust(trust!);
  if (!boot.deviceLocked) fail('unlocked');
  const knownSelfSigned = boot.verifiedBootState === VERIFIED_BOOT.selfSigned
    && SELF_SIGNED_BOOT_KEYS.has(boot.verifiedBootKey.toString('hex'));
  if (boot.verifiedBootState !== VERIFIED_BOOT.verified && !knownSelfSigned) fail('boot_state');

  const origin = description.hardware.get(TAG.origin);
  if (!origin || small(origin) !== ORIGIN_GENERATED) fail('origin');
  const algorithm = description.hardware.get(TAG.algorithm);
  const curve = description.hardware.get(TAG.ecCurve);
  const key = certificates[0]!.publicKey;
  if (!algorithm || small(algorithm) !== ALGORITHM_EC || !curve || small(curve) !== CURVE_P256
    || key.asymmetricKeyType !== 'ec' || key.asymmetricKeyDetails?.namedCurve !== 'prime256v1') fail('key');

  // The operating system fills it in, which a verified boot vouches for.
  const app = description.software.get(TAG.attestationApplicationId) ?? description.hardware.get(TAG.attestationApplicationId);
  if (!app) fail('app_id');
  const { packages, signers } = applicationId(app!);
  if (packages.length !== 1 || packages[0] !== policy.packageName) fail('app_id');
  if (!signers.some((digest) => policy.signers.has(digest))) fail('signer');

  return { publicKey: key.export({ type: 'spki', format: 'pem' }).toString() };
}

// Google's revocation list (https://developer.android.com/privacy-and-security/security-key-attestation): the
// serial numbers of attestation certificates whose keys leaked or were withdrawn. One copy per process, read again
// after an hour; past a day without a fresh copy, attestation waits for one, and fails if it can't be read.

const STATUS_URL = 'https://android.googleapis.com/attestation/status';
const FRESH_MS = 60 * 60_000;
const STALE_MS = 24 * 60 * 60_000;

interface StatusCopy { serials: ReadonlySet<string>; read: number }

const globalStatus = globalThis as typeof globalThis & {
  __androidAttestationStatus?: StatusCopy;
  __androidAttestationStatusReading?: Promise<StatusCopy>;
};

async function readStatus(): Promise<StatusCopy> {
  const response = await fetch(STATUS_URL, {
    headers: { Accept: 'application/json', 'User-Agent': 'Peek (https://peek.plhery.com)' },
    cache: 'no-store',
    signal: AbortSignal.timeout(10_000),
  });
  if (!response.ok) throw new Error(`Revocation list answered ${response.status}`);
  const body = await response.json() as { entries?: unknown };
  if (!body || typeof body.entries !== 'object' || body.entries === null || Array.isArray(body.entries)) {
    throw new Error('Revocation list is malformed');
  }
  // Every listed status (REVOKED, SUSPENDED) refuses the certificate.
  const copy = { serials: new Set(Object.keys(body.entries).map(serialKey)), read: Date.now() };
  globalStatus.__androidAttestationStatus = copy;
  return copy;
}

function refreshStatus(): Promise<StatusCopy> {
  globalStatus.__androidAttestationStatusReading ??= readStatus().finally(() => {
    globalStatus.__androidAttestationStatusReading = undefined;
  });
  return globalStatus.__androidAttestationStatusReading;
}

/** The revoked serial numbers: the copy in memory, read again in the background after an hour, waited for after a day. */
export async function revokedSerials(now = Date.now()): Promise<ReadonlySet<string>> {
  const copy = globalStatus.__androidAttestationStatus;
  if (copy && now - copy.read < FRESH_MS) return copy.serials;
  if (copy && now - copy.read < STALE_MS) {
    refreshStatus().catch(() => undefined);
    return copy.serials;
  }
  return (await refreshStatus()).serials;
}

/** Forgets the copy in memory (tests). */
export function forgetRevocationList(): void {
  globalStatus.__androidAttestationStatus = undefined;
  globalStatus.__androidAttestationStatusReading = undefined;
}
