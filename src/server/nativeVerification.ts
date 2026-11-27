import 'server-only';

import { createHash, createHmac, randomBytes, timingSafeEqual, verify, X509Certificate } from 'node:crypto';
import cbor from 'cbor';
import { verifyAssertion, verifyAttestation } from 'node-app-attest';
import {
  ANDROID_PACKAGE, ANDROID_SIGNERS, AndroidAttestationError, leafKey, revokedSerials, verifyAndroidKeyAttestation,
  type AndroidFailure, type AndroidVerdict,
} from './androidKeyAttestation';
import { clientIp, clientNetwork, HttpError } from './api';
import { recordAndroidAttestation, recordNativeVerification, type NativeVerificationKind } from './metrics';
import {
  decodePlayIntegrityToken, PlayIntegrityError, playIntegritySettings, playIntegrityVerdict, playRequestHash,
  type PlayIntegrityFailure, type PlayIntegrityVerdict,
} from './playIntegrity';
import { logOperationalEvent } from './observability';
import { requestHostname } from './siteHosts';
import { SupabaseError, type SupabaseServiceClient } from './supabase';

const CHALLENGE_SECONDS = 120;
const KEY_ID = /^[A-Za-z0-9+/]{43}=$/;
/** The iPhone app's URLSession user-agent. Anyone can send it. */
export const NATIVE_USER_AGENT = /^PeekDeliveryTracker\/\S+ CFNetwork\/\S+ Darwin\/\S+$/;
/** The Android app's (PeekHttp.userAgent), which metrics read for a check that names no key. */
const ANDROID_USER_AGENT = /^PeekDeliveryTracker\/\S+ okhttp\/\S+ Android\/\S*$/;

/** Which app a check is for: Apple's App Attest, Android's key attestation, or not known. */
export type NativePlatform = 'apple' | 'android';
type SeenPlatform = NativePlatform | 'unknown';

/** The platform a request's user-agent names, until its key names one. Anyone can send either. */
function userAgentPlatform(request: Request): SeenPlatform {
  const agent = request.headers.get('user-agent') ?? '';
  return NATIVE_USER_AGENT.test(agent) ? 'apple' : ANDROID_USER_AGENT.test(agent) ? 'android' : 'unknown';
}

/** The app a request's user-agent names, or null: what it says, not what a key proves. */
export function appPlatform(request: Request): NativePlatform | null {
  const platform = userAgentPlatform(request);
  return platform === 'unknown' ? null : platform;
}

/** The build an app's user-agent names after `PeekDeliveryTracker/`: Apple's build number, Android's version code. */
const APP_BUILD = /^PeekDeliveryTracker\/([A-Za-z0-9._-]{1,40}) /;

/** The build of the app `app` that a request's user-agent names, or null: from the web, or from another agent. */
export function appBuild(request: Request, app: 'web' | 'ios' | 'android'): string | null {
  if (appPlatform(request) !== (app === 'ios' ? 'apple' : app === 'android' ? 'android' : null)) return null;
  return APP_BUILD.exec(request.headers.get('user-agent') ?? '')?.[1] ?? null;
}
const ASSERTION_HEADERS = ['x-app-attest-key-id', 'x-app-attest-challenge', 'x-app-attest-assertion'];

/** Whether the request carries any of an assertion's headers. */
export const hasNativeAssertion = (request: Request) => ASSERTION_HEADERS.some((header) => request.headers.has(header));

/**
 * Why an attestation or assertion failed, as logs and metrics name it:
 * `malformed` (bad encoding or shape), `unconfigured` (no App ID on the
 * server), `challenge_expired`, `challenge_mismatch` (issued for another
 * network, hostname, key or purpose, or not by this server), `replayed` (the
 * database refused a challenge already used, or a counter another request
 * moved past), `unknown_key` (never registered, or unused for 90 days), Apple's
 * steps (`chain`, `certificate_time`, `nonce`, `key_id`, `app_id`, `counter`,
 * `aaguid`, `environment` for a development key, `signature`), the signed
 * launch `category` and `bundle_version`, Android's steps (AndroidFailure,
 * PlayIntegrityFailure), `missing` (none sent where one is required),
 * `unavailable` (the database did not answer) and `unknown`.
 */
export type NativeFailure =
  | 'malformed' | 'unconfigured' | 'challenge_expired' | 'challenge_mismatch' | 'replayed' | 'unknown_key'
  | 'chain' | 'certificate_time' | 'nonce' | 'key_id' | 'app_id' | 'counter' | 'aaguid' | 'environment'
  | 'signature' | 'category' | 'bundle_version' | 'missing' | 'unavailable' | 'unknown' | AndroidFailure
  | PlayIntegrityFailure;

/** The refusal the app answers by verifying again. Its reason stays on the server. */
class NativeRefusal extends HttpError {
  constructor(readonly reason: NativeFailure) {
    super(403, 'Please verify this device and try again.', { 'X-Lookup-Verification': 'required' });
  }
}
const rejected = (reason: NativeFailure) => new NativeRefusal(reason);

/** node-app-attest's errors, by the step of Apple's validation that failed. */
const LIBRARY_FAILURES = new Map<string, NativeFailure>([
  ['invalid attestation', 'malformed'],
  ['number of decoded attestations is not 1', 'malformed'],
  ['invalid assertion', 'malformed'],
  ['invalid certificate', 'chain'],
  ['no sub CA certificate found', 'chain'],
  ['sub CA certificate is not signed by Apple App Attestation Root CA', 'chain'],
  ['no client CA certificate found', 'chain'],
  ['client CA certificate is not signed by Apple App Attestation CA 1', 'chain'],
  ['nonce does not match', 'nonce'],
  ['keyId does not match', 'key_id'],
  ['credentialId does not match', 'key_id'],
  ['appId does not match', 'app_id'],
  ['signCount is not 0', 'counter'],
  ['invalid signCount', 'counter'],
  ['aaguid is not valid', 'aaguid'],
  ['development environment is not allowed', 'environment'],
  ['invalid signature', 'signature'],
]);

function failure(error: unknown): NativeFailure {
  if (error instanceof NativeRefusal) return error.reason;
  if (error instanceof AndroidAttestationError || error instanceof PlayIntegrityError) return error.reason;
  if (error instanceof SupabaseError) return 'unavailable';
  return (error instanceof Error && LIBRARY_FAILURES.get(error.message)) || 'unknown';
}

/**
 * Logs and counts one check, with `details` in the line alone (an Android
 * registration's checks). The line never has a key, challenge, assertion or
 * address.
 */
function record(
  kind: NativeVerificationKind,
  outcome: Parameters<typeof recordNativeVerification>[1],
  platform: SeenPlatform,
  reason = 'none',
  mode?: AccountAttestMode | AndroidAttestMode,
  details: Record<string, string | boolean | undefined> = {},
) {
  recordNativeVerification(kind, outcome, reason, platform);
  logOperationalEvent('native_verification', { kind, platform, outcome, reason: reason === 'none' ? undefined : reason, mode, ...details });
}

/** Records a failed check and gives the refusal to throw. */
function refusal(
  kind: NativeVerificationKind,
  error: unknown,
  platform: SeenPlatform,
  mode?: AndroidAttestMode,
  details?: Record<string, string | boolean | undefined>,
): NativeRefusal {
  const reason = failure(error);
  record(kind, 'rejected', platform, reason, mode, details);
  return error instanceof NativeRefusal ? error : rejected(reason);
}

export function nativeAttestSettings(env: Record<string, string | undefined> = process.env) {
  const appId = env.APP_ATTEST_APP_ID?.trim();
  if (!appId) return null;
  const match = /^([A-Z0-9]{10})\.([A-Za-z0-9-]+(?:\.[A-Za-z0-9-]+)+)$/.exec(appId);
  const secret = env.TURNSTILE_SECRET_KEY?.trim();
  if (!match || !secret) throw new Error('APP_ATTEST_APP_ID requires a valid app ID and TURNSTILE_SECRET_KEY');
  const development = env.APP_ATTEST_ALLOW_DEVELOPMENT === 'true';
  if (development && env.NODE_ENV === 'production') throw new Error('Development App Attest keys must not be accepted in production');
  return { appId, teamIdentifier: match[1]!, bundleIdentifier: match[2]!, secret, development };
}
type NativeAttestSettings = NonNullable<ReturnType<typeof nativeAttestSettings>>;

/** A daily allowance from the environment, up to six digits; `fallback` otherwise. */
function dailyLimit(env: Record<string, string | undefined>, name: string, fallback: number): number {
  const value = env[name]?.trim();
  return value && /^\d{1,6}$/.test(value) ? Number(value) : fallback;
}

export type AndroidAttestMode = 'off' | 'log' | 'enforce';

/** `ANDROID_ATTESTATION_MODE`; anything but `off` or `enforce` reads as `log`. */
export function androidAttestMode(env: Record<string, string | undefined> = process.env): AndroidAttestMode {
  const mode = env.ANDROID_ATTESTATION_MODE?.trim().toLowerCase();
  return mode === 'off' || mode === 'enforce' ? mode : 'log';
}

/**
 * Android attestation, wherever `TURNSTILE_SECRET_KEY` signs challenges, in
 * the mode `ANDROID_ATTESTATION_MODE` sets: `log`, the default, registers keys
 * and counts their verdicts while every lookup keeps the browser check;
 * `enforce` lets a registered key's signed lookups skip it, within its
 * verdict's daily allowance; `off` registers none. A key is attested by the
 * phone's hardware (androidKeyAttestation.ts) and, in the Play build where
 * `PLAY_INTEGRITY_CLOUD_PROJECT_NUMBER` is set, by a Play Integrity token
 * (playIntegrity.ts); the better verdict counts. A `strong` key has the
 * iPhone's allowances (`NATIVE_LOOKUPS_PER_DAY`, `NATIVE_DETECTIONS_PER_DAY`),
 * a `basic` one `ANDROID_BASIC_LOOKUPS_PER_DAY` (5) and
 * `ANDROID_BASIC_DETECTIONS_PER_DAY` (20); 0 leaves it the browser check.
 * `ANDROID_ATTESTATION_SIGNERS` adds signing certificates' SHA-256 digests
 * (separated by commas) to the app's own, such as a debug key on a development
 * server.
 */
export function androidAttestSettings(env: Record<string, string | undefined> = process.env) {
  const secret = env.TURNSTILE_SECRET_KEY?.trim();
  const mode = androidAttestMode(env);
  if (mode === 'off' || !secret) return null;
  const added = (env.ANDROID_ATTESTATION_SIGNERS ?? '').split(',').map((digest) => digest.trim().toLowerCase().replaceAll(':', '')).filter(Boolean);
  if (added.some((digest) => !/^[0-9a-f]{64}$/.test(digest))) throw new Error('ANDROID_ATTESTATION_SIGNERS takes SHA-256 certificate digests');
  return {
    mode,
    appId: `android:${ANDROID_PACKAGE}`,
    packageName: ANDROID_PACKAGE,
    signers: new Set([...ANDROID_SIGNERS, ...added]),
    secret,
    playIntegrity: playIntegritySettings(env),
    allowances: {
      strong: { lookup: dailyLimit(env, 'NATIVE_LOOKUPS_PER_DAY', 15), detection: dailyLimit(env, 'NATIVE_DETECTIONS_PER_DAY', 60) },
      basic: { lookup: dailyLimit(env, 'ANDROID_BASIC_LOOKUPS_PER_DAY', 5), detection: dailyLimit(env, 'ANDROID_BASIC_DETECTIONS_PER_DAY', 20) },
    },
  };
}
type AndroidAttestSettings = NonNullable<ReturnType<typeof androidAttestSettings>>;

/** Which check gave an Android key its verdict: the hardware's attestation or Play Integrity. */
type AndroidEvidence = 'key' | 'play';
interface AndroidGrade { evidence: AndroidEvidence; verdict: AndroidVerdict }

/** An Android key registers under the app's ID, its evidence and its verdict: `android:<package>:key-strong`. */
const androidKeyAppId = (settings: AndroidAttestSettings, grade: AndroidGrade) => `${settings.appId}:${grade.evidence}-${grade.verdict}`;

function androidGrade(appId: string, settings: AndroidAttestSettings): AndroidGrade | null {
  const match = appId.startsWith(`${settings.appId}:`) ? /^(key|play)-(strong|basic)$/.exec(appId.slice(settings.appId.length + 1)) : null;
  return match ? { evidence: match[1] as AndroidEvidence, verdict: match[2] as AndroidVerdict } : null;
}

/**
 * A challenge is for either platform's key. It names Apple's app ID where App
 * Attest is set up, else Android's.
 */
function challengeSignature(request: Request, payload: string, keyId: string, purpose: string): Buffer {
  const settings = nativeAttestSettings() ?? androidAttestSettings();
  if (!settings) throw new HttpError(503, 'App verification is not configured');
  const hostname = requestHostname(request.headers) ?? new URL(request.url).hostname;
  return createHmac('sha256', settings.secret).update(
    `native-challenge-v1\n${settings.appId}\n${hostname}\n${clientNetwork(clientIp(request))}\n${keyId}\n${purpose}\n${payload}`,
  ).digest();
}

export function nativeChallenge(request: Request, keyId: unknown, purpose: unknown) {
  if (typeof keyId !== 'string' || !KEY_ID.test(keyId) || (purpose !== 'attestation' && purpose !== 'assertion')) {
    throw new HttpError(400, 'Invalid verification challenge');
  }
  const expires = Math.floor(Date.now() / 1_000) + CHALLENGE_SECONDS;
  const payload = `${expires}.${randomBytes(32).toString('base64url')}`;
  return { challenge: `${payload}.${challengeSignature(request, payload, keyId, purpose).toString('base64url')}`, expiresAt: expires * 1_000 };
}

interface CheckedChallenge { hash: string; expires: string }

function checkChallenge(request: Request, challenge: unknown, keyId: string, purpose: string): CheckedChallenge {
  const match = typeof challenge === 'string' ? /^(\d{10})\.([A-Za-z0-9_-]{43})\.([A-Za-z0-9_-]{43})$/.exec(challenge) : null;
  if (!match) throw rejected('malformed');
  const now = Math.floor(Date.now() / 1_000);
  if (Number(match[1]) <= now) throw rejected('challenge_expired');
  // No challenge is issued further ahead than its lifetime.
  if (Number(match[1]) > now + CHALLENGE_SECONDS) throw rejected('challenge_mismatch');
  const expected = challengeSignature(request, `${match[1]}.${match[2]}`, keyId, purpose);
  const received = Buffer.from(match[3]!, 'base64url');
  if (received.length !== expected.length || !timingSafeEqual(received, expected)) throw rejected('challenge_mismatch');
  return { hash: createHash('sha256').update(match[0]).digest('hex'), expires: new Date(Number(match[1]) * 1_000).toISOString() };
}

function binary(value: unknown, max: number): Buffer {
  if (typeof value !== 'string' || !value || value.length > max || !/^(?:[A-Za-z0-9+/]{4})*(?:[A-Za-z0-9+/]{2}==|[A-Za-z0-9+/]{3}=)?$/.test(value)) throw rejected('malformed');
  return Buffer.from(value, 'base64');
}

function decoded(bytes: Buffer): ReturnType<typeof cbor.decodeAllSync> {
  try {
    return cbor.decodeAllSync(bytes, { max_depth: 16 });
  } catch {
    throw rejected('malformed');
  }
}

/** Bound decoding and certificate lifetimes supplement the protocol verifier. */
function checkedAttestation(value: unknown, development: boolean): Buffer {
  const bytes = binary(value, 14_000);
  const [attestation, ...rest] = decoded(bytes);
  if (rest.length || !Array.isArray(attestation?.attStmt?.x5c) || attestation.attStmt.x5c.length !== 2) throw rejected('malformed');
  for (const encoded of attestation.attStmt.x5c) {
    if (!Buffer.isBuffer(encoded)) throw rejected('malformed');
    let cert: X509Certificate;
    try {
      cert = new X509Certificate(encoded);
    } catch {
      throw rejected('chain');
    }
    if (Date.parse(cert.validFrom) > Date.now() || Date.parse(cert.validTo) <= Date.now()) throw rejected('certificate_time');
  }
  checkExtensions(attestation.authData, true, development);
  return bytes;
}

/**
 * iOS writes the launch category as four little-endian bytes, where Apple's
 * documentation says a UInt32: both are read.
 */
function launchCategory(value: unknown): unknown {
  return Buffer.isBuffer(value) && value.length === 4 ? value.readUInt32LE(0) : value;
}

/** Newer systems add signed launch-category and bundle-version extensions. */
function checkExtensions(data: unknown, attestation: boolean, development: boolean) {
  if (!Buffer.isBuffer(data) || data.length < (attestation ? 87 : 37)) throw rejected('malformed');
  if (!(data[32]! & 0x80)) return;
  const offset = attestation ? 55 + data.readUInt16BE(53) : 37;
  const items = decoded(data.subarray(offset));
  const extensions = items[attestation ? 1 : 0];
  if (items.length !== (attestation ? 2 : 1) || !extensions || typeof extensions !== 'object') throw rejected('malformed');
  const category = launchCategory(extensions.apple_validation_category_01 ?? extensions.validationCategory);
  const version = extensions.apple_bundle_version_01 ?? extensions.bundleVersion;
  if (category !== undefined && !(typeof category === 'number' && [2, 4, 5, ...(development ? [3] : [])].includes(category))) throw rejected('category');
  if (version !== undefined && (typeof version !== 'string' || !/^\d{1,10}(?:\.\d{1,10}){0,2}$/.test(version))) throw rejected('bundle_version');
}

/**
 * Android's attestation: a CBOR map whose `fmt` is `android-key` and whose
 * `attStmt.x5c` is the key's certificate chain, leaf first, as WebAuthn's
 * android-key statement carries it; the Play build's Play Integrity token
 * comes beside it, in the body's `playIntegrity`. Null for anything else,
 * which is Apple's.
 */
interface AndroidStatement { x5c: unknown[]; playIntegrity?: unknown }

function androidAttestation(body: Record<string, unknown>): AndroidStatement | null {
  let object: unknown;
  try {
    [object] = decoded(binary(body.attestation, 14_000));
  } catch {
    return null;
  }
  const statement = object as { fmt?: unknown; attStmt?: { x5c?: unknown } } | null;
  if (statement?.fmt !== 'android-key') return null;
  return { x5c: Array.isArray(statement.attStmt?.x5c) ? statement.attStmt.x5c : [], playIntegrity: body.playIntegrity };
}

export async function registerNativeKey(request: Request, service: SupabaseServiceClient, body: Record<string, unknown>) {
  const statement = androidAttestation(body);
  if (statement) return registerAndroidKey(request, service, body, statement);
  const settings = nativeAttestSettings();
  if (!settings) throw new HttpError(503, 'App verification is not configured');
  const { keyId, challenge } = body;
  let checked: CheckedChallenge;
  let publicKey: string;
  try {
    if (typeof keyId !== 'string' || !KEY_ID.test(keyId)) throw rejected('malformed');
    checked = checkChallenge(request, challenge, keyId, 'attestation');
    publicKey = verifyAttestation({ attestation: checkedAttestation(body.attestation, settings.development), challenge: challenge as string,
      keyId, teamIdentifier: settings.teamIdentifier, bundleIdentifier: settings.bundleIdentifier,
      allowDevelopmentEnvironment: settings.development }).publicKey;
    if (typeof publicKey !== 'string' || publicKey.length > 1_024) throw rejected('malformed');
  } catch (error) {
    throw refusal('attestation', error, 'apple');
  }
  const accepted = await service.request('/rest/v1/rpc/register_native_attest_key', { method: 'POST', body: {
    p_key_id: keyId, p_public_key: publicKey, p_app_id: settings.appId, p_challenge: checked.hash, p_expires: checked.expires,
  } });
  if (accepted !== true) throw refusal('attestation', rejected('replayed'), 'apple');
  record('attestation', 'verified', 'apple');
}

/**
 * A Play Integrity token's verdict for the chain's key, or null where the app
 * sent none or the server reads none. Throws a PlayIntegrityError, or the
 * AndroidAttestationError of a leaf without a P-256 key.
 */
async function playIntegrity(statement: AndroidStatement, clientDataHash: Buffer, settings: AndroidAttestSettings, issuedAt: number): Promise<PlayIntegrityVerdict | null> {
  if (statement.playIntegrity === undefined || !settings.playIntegrity) return null;
  const requestHash = playRequestHash(clientDataHash, leafKey(statement.x5c));
  const payload = await decodePlayIntegrityToken(statement.playIntegrity, settings.packageName, settings.playIntegrity);
  return playIntegrityVerdict(payload, { packageName: settings.packageName, signers: settings.signers, requestHash, issuedAt });
}

/**
 * Registers an Android key in `log` and `enforce` modes. The app makes the key
 * with the SHA-256 of the challenge as its attestation challenge; the key ID is
 * the app's own random one, which the challenge is issued for. The hardware's
 * attestation (androidKeyAttestation.ts) and a Play Integrity token naming the
 * key (playIntegrity.ts) are checked together, and the better verdict
 * registers it, the hardware's on a tie; where both fail it is refused for the
 * hardware's reason. `android_attestation_total` counts what each gave.
 */
async function registerAndroidKey(request: Request, service: SupabaseServiceClient, body: Record<string, unknown>, statement: AndroidStatement) {
  const settings = androidAttestSettings();
  if (!settings) throw new HttpError(503, 'App verification is not configured');
  const { keyId, challenge } = body;
  let checked: CheckedChallenge;
  let grade: AndroidGrade;
  let publicKey: string;
  const details: Record<string, string | boolean | undefined> = {};
  try {
    if (typeof keyId !== 'string' || !KEY_ID.test(keyId)) throw rejected('malformed');
    checked = checkChallenge(request, challenge, keyId, 'attestation');
    const clientDataHash = createHash('sha256').update(challenge as string).digest();
    const issuedAt = Date.parse(checked.expires) - CHALLENGE_SECONDS * 1_000;
    const [key, play] = await Promise.allSettled([
      verifyAndroidKeyAttestation(statement.x5c, clientDataHash, settings, revokedSerials),
      playIntegrity(statement, clientDataHash, settings, issuedAt),
    ]);
    details.key = key.status === 'fulfilled' ? key.value.verdict : failure(key.reason);
    details.basic = key.status === 'fulfilled' ? key.value.basic : undefined;
    details.play = play.status === 'rejected' ? failure(play.reason)
      : play.value?.verdict ?? (statement.playIntegrity === undefined ? 'absent' : 'unconfigured');
    if (play.status === 'fulfilled' && play.value) Object.assign(details, { licensing: play.value.licensing, testing: play.value.testing || undefined });
    recordAndroidAttestation(details.key, details.play, settings.mode);
    const playVerdict = play.status === 'fulfilled' ? play.value?.verdict : undefined;
    if (key.status === 'fulfilled' && (key.value.verdict === 'strong' || playVerdict !== 'strong')) {
      grade = { evidence: 'key', verdict: key.value.verdict };
      publicKey = key.value.publicKey;
    } else if (playVerdict) {
      grade = { evidence: 'play', verdict: playVerdict };
      publicKey = leafKey(statement.x5c).export({ type: 'spki', format: 'pem' }).toString();
    } else {
      throw (key as PromiseRejectedResult).reason;
    }
    if (publicKey.length > 1_024) throw rejected('malformed');
  } catch (error) {
    throw refusal('attestation', error, 'android', settings.mode, details);
  }
  const accepted = await service.request('/rest/v1/rpc/register_native_attest_key', { method: 'POST', body: {
    p_key_id: keyId, p_public_key: publicKey, p_app_id: androidKeyAppId(settings, grade), p_challenge: checked.hash, p_expires: checked.expires,
  } });
  if (accepted !== true) throw refusal('attestation', rejected('replayed'), 'android', settings.mode, details);
  record('attestation', 'verified', 'android', grade.verdict, settings.mode, { ...details, evidence: grade.evidence });
}

export function nativeRequestPayload(request: Request, challenge: string, rawBody: Uint8Array): string {
  return `native-request-v1\n${challenge}\n${request.method}\n${new URL(request.url).pathname}\n${createHash('sha256').update(rawBody).digest('hex')}`;
}

interface CheckedAssertion { keyId: string; counter: number; challenge: CheckedChallenge; grade?: AndroidGrade }

/** Each platform's settings, null where it is off. */
interface NativeSettings { apple: NativeAttestSettings | null; android: AndroidAttestSettings | null }

/**
 * Checks the request's assertion over `body` against the registered key, and
 * reads nothing else: the caller has the database accept its challenge and
 * counter. Throws a refusal naming why it fails, or the database's error.
 * `seen.platform` becomes the key's once it is read.
 */
async function checkAssertion(
  request: Request,
  service: SupabaseServiceClient,
  body: Uint8Array,
  settings: NativeSettings,
  seen: { platform: SeenPlatform },
): Promise<CheckedAssertion> {
  const keyId = request.headers.get('x-app-attest-key-id') ?? '';
  const challenge = request.headers.get('x-app-attest-challenge') ?? '';
  if (!KEY_ID.test(keyId)) throw rejected('malformed');
  const checked = checkChallenge(request, challenge, keyId, 'assertion');
  const stored = await service.request<{ public_key: string; app_id: string; sign_count: number }>('/rest/v1/rpc/get_native_attest_key', {
    method: 'POST', body: { p_key_id: keyId },
  });
  if (!stored) throw rejected('unknown_key');
  if (stored.app_id.startsWith('android:')) {
    seen.platform = 'android';
    return { keyId, ...checkAndroidAssertion(request, challenge, body, stored, settings.android), challenge: checked };
  }
  seen.platform = 'apple';
  if (!settings.apple) throw rejected('unconfigured');
  if (stored.app_id !== settings.apple.appId) throw rejected('app_id');
  const assertion = binary(request.headers.get('x-app-attest-assertion'), 4_096);
  const [object, ...rest] = decoded(assertion);
  if (rest.length || !Buffer.isBuffer(object?.signature) || !Buffer.isBuffer(object?.authenticatorData)
    || object.authenticatorData.length < 37) throw rejected('malformed');
  checkExtensions(object.authenticatorData, false, settings.apple.development);
  const counter = verifyAssertion({ assertion, payload: nativeRequestPayload(request, challenge, body), publicKey: stored.public_key,
    teamIdentifier: settings.apple.teamIdentifier, bundleIdentifier: settings.apple.bundleIdentifier, signCount: stored.sign_count }).signCount;
  if (!Number.isSafeInteger(counter) || counter <= 0) throw rejected('counter');
  return { keyId, counter, challenge: checked };
}

/**
 * An Android assertion is the key's ECDSA signature (DER, SHA-256) of the
 * SHA-256 of the payload. The hardware keeps no counter, so the server counts
 * one up from the stored one: the single-use challenge already stops a replay.
 * Gives the key's grade, which its app ID names.
 */
function checkAndroidAssertion(
  request: Request,
  challenge: string,
  body: Uint8Array,
  stored: { public_key: string; app_id: string; sign_count: number },
  settings: AndroidAttestSettings | null,
): { counter: number; grade: AndroidGrade } {
  if (!settings) throw rejected('unconfigured');
  const grade = androidGrade(stored.app_id, settings);
  if (!grade) throw rejected('app_id');
  const signature = binary(request.headers.get('x-app-attest-assertion'), 4_096);
  const signed = createHash('sha256').update(nativeRequestPayload(request, challenge, body)).digest();
  let valid = false;
  try {
    valid = verify('sha256', signed, stored.public_key, signature);
  } catch {
    valid = false;
  }
  if (!valid) throw rejected('signature');
  const counter = stored.sign_count + 1;
  if (!Number.isSafeInteger(counter) || counter <= 0) throw rejected('counter');
  return { counter, grade };
}

/**
 * What a signed request's assertion did: the proof that let it through, or,
 * for an Android key, why the browser proof decides instead, as for a request
 * without one: `mode` (Android attestation only logs), `limited` (its
 * verdict's allowance is used until `retryAfter` seconds from now) or
 * `refused` (the assertion failed, which the app answers as any refusal).
 */
export type NativeOutcome =
  | { proof: 'app_attest' | 'key_attestation' | 'play_integrity' }
  | { proof: null; fallback: 'mode' | 'limited' | 'refused'; retryAfter?: number };

const secondsToMidnight = (now = new Date()) =>
  Math.ceil((Date.UTC(now.getUTCFullYear(), now.getUTCMonth(), now.getUTCDate() + 1) - now.getTime()) / 1_000);

/** A lookup, or a lookup's new carrier, which asks carriers as a lookup does; anything else is a detection. */
export function proofKind(request: Request): 'lookup' | 'detection' {
  const { pathname } = new URL(request.url);
  return pathname === '/api/public/parcels' || /^\/api\/public\/parcels\/[^/]+\/carrier$/.test(pathname) ? 'lookup' : 'detection';
}

/**
 * The assertion signs `body`, the bytes the route read from the request. An
 * iPhone's refused assertion or used allowance refuses the request; an Android
 * key's never does more than send it to the browser proof (NativeOutcome).
 */
export async function verifyNativeRequest(request: Request, service: SupabaseServiceClient, body: Uint8Array): Promise<NativeOutcome> {
  const kind = proofKind(request);
  const settings = { apple: nativeAttestSettings(), android: androidAttestSettings() };
  const seen = { platform: userAgentPlatform(request) };
  const mode = settings.android?.mode;
  if (!settings.apple && !settings.android) {
    if (seen.platform === 'android') {
      record(kind, 'rejected', 'android', 'unconfigured');
      return { proof: null, fallback: 'refused' };
    }
    throw refusal(kind, rejected('unconfigured'), seen.platform);
  }
  let checked: CheckedAssertion;
  try {
    checked = await checkAssertion(request, service, body, settings, seen);
  } catch (error) {
    if (error instanceof SupabaseError) throw error;
    const refused = refusal(kind, error, seen.platform, seen.platform === 'android' ? mode : undefined);
    if (seen.platform === 'android') return { proof: null, fallback: 'refused' };
    throw refused;
  }
  const platform = seen.platform as NativePlatform;
  const { grade } = checked;
  // Nothing is spent: the key is checked, and counted with its verdict.
  if (grade && mode !== 'enforce') {
    record(kind, 'ignored', 'android', grade.verdict, mode);
    return { proof: null, fallback: 'mode' };
  }
  const limit = grade ? settings.android!.allowances[grade.verdict][kind]
    : kind === 'lookup' ? dailyLimit(process.env, 'NATIVE_LOOKUPS_PER_DAY', 15) : dailyLimit(process.env, 'NATIVE_DETECTIONS_PER_DAY', 60);
  const outcome = await service.request('/rest/v1/rpc/accept_native_assertion', { method: 'POST', body: {
    p_key_id: checked.keyId, p_counter: checked.counter, p_challenge: checked.challenge.hash, p_expires: checked.challenge.expires,
    p_kind: kind, p_limit: limit,
  } });
  if (outcome === 'limited') {
    record(kind, 'limited', platform, grade?.verdict, grade ? mode : undefined);
    if (grade) return { proof: null, fallback: 'limited', retryAfter: secondsToMidnight() };
    throw new HttpError(429, 'Today’s device allowance is used up. Try again tomorrow.', { 'Retry-After': String(secondsToMidnight()) });
  }
  if (outcome !== 'accepted') {
    const refused = refusal(kind, rejected('replayed'), platform, grade ? mode : undefined);
    if (grade) return { proof: null, fallback: 'refused' };
    throw refused;
  }
  record(kind, 'verified', platform, grade?.verdict, grade ? mode : undefined);
  return { proof: !grade ? 'app_attest' : grade.evidence === 'play' ? 'play_integrity' : 'key_attestation' };
}

export type AccountAttestMode = 'off' | 'log' | 'enforce';

/** A platform's settings, or null where they are missing or broken. */
function configured<T>(settings: () => T | null): T | null {
  try {
    return settings();
  } catch {
    return null;
  }
}

/** `NATIVE_ATTEST_ACCOUNT_MODE`; anything but `off` or `enforce` reads as `log`, which never refuses. */
export function accountAttestMode(env: Record<string, string | undefined> = process.env): AccountAttestMode {
  const mode = env.NATIVE_ATTEST_ACCOUNT_MODE?.trim().toLowerCase();
  return mode === 'off' || mode === 'enforce' ? mode : 'log';
}

/**
 * Signed-in requests that have carriers asked or checks queued may carry the
 * assertion anonymous lookups do, over the bytes the route read (none for a
 * route without a body). In `log` mode, the default, a present assertion is
 * checked and the database takes its challenge and counter, with no device
 * allowance to spend: the check counts `verified` or `invalid` with its
 * reason, and a request from the app's user-agent without one counts
 * `missing`, `unsigned` when the app says it verifies (`X-Native-Verification`)
 * and `legacy` from builds that never do. Nothing is refused. In `enforce`
 * mode, with App Attest configured, a request from the app's user-agent
 * without a valid assertion gets the refusal an anonymous lookup gets.
 * Browsers never sign and are never refused here; account budgets apply to all.
 */
export async function checkAccountRequest(
  request: Request,
  service: SupabaseServiceClient,
  body: Uint8Array,
  kind: 'account_lookup' | 'account_detection' | 'account_refresh',
): Promise<void> {
  const mode = accountAttestMode();
  if (mode === 'off') return;
  // The anonymous routes report a broken configuration; it never stops an account.
  const settings: NativeSettings = { apple: configured(nativeAttestSettings), android: configured(androidAttestSettings) };
  const app = NATIVE_USER_AGENT.test(request.headers.get('user-agent') ?? '');
  const enforced = mode === 'enforce' && app && settings.apple !== null;
  if (!hasNativeAssertion(request)) {
    if (!app) return;
    record(kind, 'missing', 'apple', request.headers.has('x-native-verification') ? 'unsigned' : 'legacy', mode);
    if (enforced) throw rejected('missing');
    return;
  }
  const seen = { platform: userAgentPlatform(request) };
  try {
    if (!settings.apple && !settings.android) throw rejected('unconfigured');
    const checked = await checkAssertion(request, service, body, settings, seen);
    // With nothing to spend, the database takes the challenge and the counter and counts no use.
    const outcome = await service.request('/rest/v1/rpc/accept_native_assertion', { method: 'POST', body: {
      p_key_id: checked.keyId, p_counter: checked.counter, p_challenge: checked.challenge.hash, p_expires: checked.challenge.expires,
      p_kind: 'lookup', p_limit: 0,
    } });
    if (outcome !== 'limited') throw rejected('replayed');
    record(kind, 'verified', seen.platform, 'none', mode);
  } catch (error) {
    const reason = failure(error);
    record(kind, 'invalid', seen.platform, reason, mode);
    if (!enforced) return;
    if (error instanceof SupabaseError) throw error;
    throw error instanceof NativeRefusal ? error : rejected(reason);
  }
}
