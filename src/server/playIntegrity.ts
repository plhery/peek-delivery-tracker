import 'server-only';

import { createHash, createPrivateKey, sign, type KeyObject } from 'node:crypto';
import { isRecord } from './types';

/**
 * Google Play Integrity (https://developer.android.com/google/play/integrity), for the Play build: the app asks
 * Google Play for a standard integrity token whose request hash names its key and the server's challenge
 * (playRequestHash), and the server has Google decode it (decodeIntegrityToken) and reads the verdicts. Google
 * sees each registration; nothing else goes to it.
 */

const TOKEN_URL = 'https://oauth2.googleapis.com/token';
const SCOPE = 'https://www.googleapis.com/auth/playintegrity';
const RENEW_BEFORE_SECONDS = 5 * 60;
const TIMEOUT_MS = 8_000;
/** How far a token's time may be before the challenge was issued, or after now: phones' clocks drift. */
const CLOCK_SKEW_MS = 60_000;

/**
 * Why a token gives no verdict, as logs and metrics name it: `malformed` (not a token, or one Google says is not),
 * `unavailable` (Google did not answer, or refused the service account), `request_package` and `request_hash`
 * (asked for another app, key or challenge), `token_time` (asked outside the challenge's lifetime), `app_recognition`
 * (not the app Google Play distributes, or a token decoded before), `app_id` (another package), `signer` (another
 * signing certificate) and `device_integrity` (a device that meets no integrity level counted here).
 */
export type PlayIntegrityFailure =
  | 'malformed' | 'unavailable' | 'request_package' | 'request_hash' | 'token_time' | 'app_recognition' | 'app_id'
  | 'signer' | 'device_integrity';

export class PlayIntegrityError extends Error {
  constructor(readonly reason: PlayIntegrityFailure) {
    super(`Play Integrity failed: ${reason}`);
  }
}
const fail = (reason: PlayIntegrityFailure): never => {
  throw new PlayIntegrityError(reason);
};

/**
 * `PLAY_INTEGRITY_CLOUD_PROJECT_NUMBER` turns it on: the number of the Google Cloud project linked to the app in
 * Play Console, which the app needs to ask for tokens. The server decodes them as a service account of that project
 * with the Play Integrity API enabled: `PLAY_INTEGRITY_CLIENT_EMAIL` and `PLAY_INTEGRITY_PRIVATE_KEY`, or FCM's when
 * neither is set.
 */
export function playIntegritySettings(env: Record<string, string | undefined> = process.env) {
  const projectNumber = env.PLAY_INTEGRITY_CLOUD_PROJECT_NUMBER?.trim();
  if (!projectNumber) return null;
  if (!/^[1-9]\d{0,17}$/.test(projectNumber)) throw new Error('PLAY_INTEGRITY_CLOUD_PROJECT_NUMBER takes the Cloud project number');
  const own = Boolean(env.PLAY_INTEGRITY_CLIENT_EMAIL?.trim() || env.PLAY_INTEGRITY_PRIVATE_KEY?.trim());
  const clientEmail = (own ? env.PLAY_INTEGRITY_CLIENT_EMAIL : env.FCM_CLIENT_EMAIL)?.trim();
  const privateKey = (own ? env.PLAY_INTEGRITY_PRIVATE_KEY : env.FCM_PRIVATE_KEY)?.trim();
  if (!clientEmail || !privateKey) {
    throw new Error('Play Integrity needs a service account: PLAY_INTEGRITY_CLIENT_EMAIL and PLAY_INTEGRITY_PRIVATE_KEY, or FCM\'s');
  }
  return { projectNumber, clientEmail, privateKey };
}
export type PlayIntegritySettings = NonNullable<ReturnType<typeof playIntegritySettings>>;

/**
 * What the app puts in the token's request hash: the SHA-256, in lowercase hex, of the challenge's SHA-256 (the
 * attestation challenge its key was made with) followed by its key's SubjectPublicKeyInfo (DER).
 */
export function playRequestHash(clientDataHash: Buffer, publicKey: KeyObject): string {
  return createHash('sha256').update(clientDataHash).update(publicKey.export({ type: 'spki', format: 'der' })).digest('hex');
}

/** A Google service account's access tokens for one scope, each kept until shortly before it expires. */
export class GoogleServiceAccount {
  #privateKey: KeyObject;
  #access: { token: string; renewAt: number } | null = null;
  #pending: Promise<string> | null = null;

  constructor(
    readonly clientEmail: string,
    privateKey: string,
    readonly scope: string,
    readonly fetcher: typeof fetch = (...args) => fetch(...args),
    readonly now: () => number = () => Date.now() / 1_000,
  ) {
    try {
      this.#privateKey = createPrivateKey(privateKey.replaceAll('\\n', '\n').trim());
    } catch (error) {
      throw new TypeError('The service account key must be a private key', { cause: error });
    }
    if (this.#privateKey.asymmetricKeyType !== 'rsa') throw new TypeError('The service account key must be an RSA private key');
  }

  assertion(issuedAt: number): string {
    const part = (value: object) => Buffer.from(JSON.stringify(value)).toString('base64url');
    const unsigned = `${part({ alg: 'RS256', typ: 'JWT' })}.${part({
      iss: this.clientEmail, scope: this.scope, aud: TOKEN_URL, iat: issuedAt, exp: issuedAt + 3_600,
    })}`;
    return `${unsigned}.${sign('RSA-SHA256', Buffer.from(unsigned), this.#privateKey).toString('base64url')}`;
  }

  async accessToken(): Promise<string> {
    if (this.#access && this.now() < this.#access.renewAt) return this.#access.token;
    this.#pending ??= this.requestAccessToken().finally(() => {
      this.#pending = null;
    });
    return await this.#pending;
  }

  /** A token Google stopped taking early is renewed for the next call. */
  forget(): void {
    this.#access = null;
  }

  private async requestAccessToken(): Promise<string> {
    const issuedAt = Math.floor(this.now());
    const response = await this.fetcher(TOKEN_URL, {
      method: 'POST',
      headers: { 'content-type': 'application/x-www-form-urlencoded' },
      body: new URLSearchParams({ grant_type: 'urn:ietf:params:oauth:grant-type:jwt-bearer', assertion: this.assertion(issuedAt) }).toString(),
      signal: AbortSignal.timeout(TIMEOUT_MS),
    });
    const body: unknown = await response.json().catch(() => null);
    if (!response.ok || !isRecord(body) || typeof body.access_token !== 'string' || !body.access_token) {
      throw new Error(`Google OAuth returned HTTP ${response.status}`);
    }
    const lifetime = typeof body.expires_in === 'number' && body.expires_in > 0 ? body.expires_in : 3_600;
    this.#access = { token: body.access_token, renewAt: issuedAt + Math.max(0, lifetime - RENEW_BEFORE_SECONDS) };
    return body.access_token;
  }
}

// One account per process, so its access token is shared by every registration.
const globalAccounts = globalThis as typeof globalThis & { __playIntegrityAccount?: GoogleServiceAccount };

function account(settings: PlayIntegritySettings): GoogleServiceAccount {
  const known = globalAccounts.__playIntegrityAccount;
  if (known?.clientEmail === settings.clientEmail) return known;
  return (globalAccounts.__playIntegrityAccount = new GoogleServiceAccount(settings.clientEmail, settings.privateKey, SCOPE));
}

/** Forgets the process's service account, as tests change it. */
export function forgetPlayIntegrityAccount(): void {
  delete globalAccounts.__playIntegrityAccount;
}

/** Google's decoding of a token: its `tokenPayloadExternal`. Throws a PlayIntegrityError. */
export async function decodePlayIntegrityToken(token: unknown, packageName: string, settings: PlayIntegritySettings): Promise<Record<string, unknown>> {
  if (typeof token !== 'string' || !token || token.length > 16_384 || !/^[A-Za-z0-9._-]+$/.test(token)) return fail('malformed');
  const google = account(settings);
  let response: Response;
  try {
    response = await google.fetcher(`https://playintegrity.googleapis.com/v1/${encodeURIComponent(packageName)}:decodeIntegrityToken`, {
      method: 'POST',
      headers: { authorization: `Bearer ${await google.accessToken()}`, 'content-type': 'application/json' },
      body: JSON.stringify({ integrityToken: token }),
      signal: AbortSignal.timeout(TIMEOUT_MS),
    });
  } catch {
    return fail('unavailable');
  }
  const body: unknown = await response.json().catch(() => null);
  if (response.status === 401) google.forget();
  // Google answers a token it can't read with 400 INVALID_ARGUMENT.
  if (response.status === 400) return fail('malformed');
  if (!response.ok) return fail('unavailable');
  if (!isRecord(body) || !isRecord(body.tokenPayloadExternal)) return fail('malformed');
  return body.tokenPayloadExternal;
}

/** A certificate digest as Google writes it (base64 web-safe; hex in its examples), in lowercase hex. */
function digestHex(value: unknown): string | null {
  if (typeof value !== 'string') return null;
  if (/^[0-9a-fA-F]{64}$/.test(value)) return value.toLowerCase();
  if (!/^[A-Za-z0-9_-]{43}=?$/.test(value)) return null;
  return Buffer.from(value, 'base64url').toString('hex');
}

export interface PlayIntegrityExpectation {
  packageName: string;
  /** Lowercase hex SHA-256 digests of the signing certificates. */
  signers: ReadonlySet<string>;
  requestHash: string;
  /** When the challenge was issued, in milliseconds. */
  issuedAt: number;
  now?: number;
}

export interface PlayIntegrityVerdict {
  /** `strong`: the app Google Play distributes, on a device that meets device (or strong) integrity; `basic`: basic integrity alone. */
  verdict: 'strong' | 'basic';
  /** `appLicensingVerdict`: LICENSED, UNLICENSED or UNEVALUATED; logged, not required. */
  licensing: string;
  /** A response Play Console's testing settings wrote, for a tester's account. */
  testing: boolean;
}

/** Reads a decoded token's verdicts against what the registration should say. Throws a PlayIntegrityError. */
export function playIntegrityVerdict(payload: Record<string, unknown>, expected: PlayIntegrityExpectation): PlayIntegrityVerdict {
  const request = isRecord(payload.requestDetails) ? payload.requestDetails : fail('malformed');
  if (request.requestPackageName !== expected.packageName) fail('request_package');
  if (request.requestHash !== expected.requestHash) fail('request_hash');
  const time = typeof request.timestampMillis === 'string' || typeof request.timestampMillis === 'number' ? Number(request.timestampMillis) : NaN;
  if (!Number.isFinite(time) || time < expected.issuedAt - CLOCK_SKEW_MS || time > (expected.now ?? Date.now()) + CLOCK_SKEW_MS) fail('token_time');

  const app = isRecord(payload.appIntegrity) ? payload.appIntegrity : fail('malformed');
  if (app.appRecognitionVerdict !== 'PLAY_RECOGNIZED') fail('app_recognition');
  if (app.packageName !== expected.packageName) fail('app_id');
  const digests = Array.isArray(app.certificateSha256Digest) ? app.certificateSha256Digest.map(digestHex) : [];
  if (!digests.some((digest) => digest !== null && expected.signers.has(digest))) fail('signer');

  const device = isRecord(payload.deviceIntegrity) && Array.isArray(payload.deviceIntegrity.deviceRecognitionVerdict)
    ? payload.deviceIntegrity.deviceRecognitionVerdict : [];
  const verdict = device.includes('MEETS_DEVICE_INTEGRITY') || device.includes('MEETS_STRONG_INTEGRITY') ? 'strong'
    : device.includes('MEETS_BASIC_INTEGRITY') ? 'basic' : fail('device_integrity');
  const account = isRecord(payload.accountDetails) ? payload.accountDetails : {};
  const testing = isRecord(payload.testingDetails) && payload.testingDetails.isTestingResponse === true;
  return { verdict, licensing: typeof account.appLicensingVerdict === 'string' ? account.appLicensingVerdict : 'UNEVALUATED', testing };
}
