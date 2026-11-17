import { createHash, createPublicKey, generateKeyPairSync, verify } from 'node:crypto';
import { afterEach, beforeEach, describe, expect, it, vi } from 'vitest';
import { ANDROID_PACKAGE, ANDROID_SIGNERS } from './androidKeyAttestation';
import {
  decodePlayIntegrityToken, forgetPlayIntegrityAccount, GoogleServiceAccount, playIntegritySettings, playIntegrityVerdict, playRequestHash,
} from './playIntegrity';

const { privateKey: rsaKey, publicKey: rsaPublicKey } = generateKeyPairSync('rsa', { modulusLength: 2048 });
const serviceKey = rsaKey.export({ type: 'pkcs8', format: 'pem' }).toString();
const settings = { projectNumber: '123456789012', clientEmail: 'integrity@example.iam.gserviceaccount.com', privateKey: serviceKey };
const PLAY_SIGNER = ANDROID_SIGNERS[0]!;
const issuedAt = Date.parse('2026-11-17T12:00:00Z');
const expected = { packageName: ANDROID_PACKAGE, signers: new Set(ANDROID_SIGNERS), requestHash: 'ab'.repeat(32), issuedAt, now: issuedAt + 5_000 };

/** A decoded token as Google's documentation shows one, for Peek. */
function payload(changes: { request?: object; app?: object; device?: string[]; account?: object; testing?: boolean } = {}) {
  return {
    requestDetails: { requestPackageName: ANDROID_PACKAGE, requestHash: 'ab'.repeat(32), timestampMillis: String(issuedAt + 2_000), ...changes.request },
    appIntegrity: {
      appRecognitionVerdict: 'PLAY_RECOGNIZED', packageName: ANDROID_PACKAGE, versionCode: '42',
      certificateSha256Digest: [Buffer.from(PLAY_SIGNER, 'hex').toString('base64url')], ...changes.app,
    },
    deviceIntegrity: { deviceRecognitionVerdict: changes.device ?? ['MEETS_DEVICE_INTEGRITY'] },
    accountDetails: { appLicensingVerdict: 'LICENSED', ...changes.account },
    ...changes.testing ? { testingDetails: { isTestingResponse: true } } : {},
  };
}

beforeEach(() => forgetPlayIntegrityAccount());
afterEach(() => { vi.unstubAllGlobals(); vi.restoreAllMocks(); });

describe('Play Integrity settings', () => {
  it('turn on with the Cloud project number, with its own service account or FCM\'s', () => {
    expect(playIntegritySettings({})).toBeNull();
    expect(playIntegritySettings({ PLAY_INTEGRITY_CLOUD_PROJECT_NUMBER: '123456789012', PLAY_INTEGRITY_CLIENT_EMAIL: 'own@example', PLAY_INTEGRITY_PRIVATE_KEY: 'key',
      FCM_CLIENT_EMAIL: 'fcm@example', FCM_PRIVATE_KEY: 'fcm-key' })).toEqual({ projectNumber: '123456789012', clientEmail: 'own@example', privateKey: 'key' });
    expect(playIntegritySettings({ PLAY_INTEGRITY_CLOUD_PROJECT_NUMBER: '123456789012', FCM_CLIENT_EMAIL: 'fcm@example', FCM_PRIVATE_KEY: 'fcm-key' }))
      .toEqual({ projectNumber: '123456789012', clientEmail: 'fcm@example', privateKey: 'fcm-key' });
  });

  it('refuse a project ID for its number, and half an account', () => {
    expect(() => playIntegritySettings({ PLAY_INTEGRITY_CLOUD_PROJECT_NUMBER: 'peek-project', FCM_CLIENT_EMAIL: 'a', FCM_PRIVATE_KEY: 'b' })).toThrow('project number');
    expect(() => playIntegritySettings({ PLAY_INTEGRITY_CLOUD_PROJECT_NUMBER: '1', PLAY_INTEGRITY_CLIENT_EMAIL: 'own@example', FCM_CLIENT_EMAIL: 'a', FCM_PRIVATE_KEY: 'b' }))
      .toThrow('service account');
    expect(() => playIntegritySettings({ PLAY_INTEGRITY_CLOUD_PROJECT_NUMBER: '1' })).toThrow('service account');
  });
});

it('hashes the challenge\'s SHA-256 and the key\'s SubjectPublicKeyInfo, as the app does', () => {
  // The client specification's example.
  const key = createPublicKey('-----BEGIN PUBLIC KEY-----\nMFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAEOq/HCxVr3bwzRO3OycTUOuUFQ/r2\nFWAPCQcBdDBRqymSZFeW2MENv50nL2Z819BN45Vsd4OYLkEIm9RAJRLaSg==\n-----END PUBLIC KEY-----');
  const challenge = '1767225600.AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA.BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB';
  expect(playRequestHash(createHash('sha256').update(challenge).digest(), key)).toBe('9fbaba741e5504030f4b802299131c261574dc3a5b9a2b751db4595a2f4fee72');
});

describe('decoding a token', () => {
  function google(decoded: Response | (() => Response) = Response.json({ tokenPayloadExternal: payload() })) {
    const fetch = vi.fn(async (url: string | URL | Request) => String(url) === 'https://oauth2.googleapis.com/token'
      ? Response.json({ access_token: 'synthetic-access-token', expires_in: 3_600 })
      : typeof decoded === 'function' ? decoded() : decoded.clone());
    vi.stubGlobal('fetch', fetch);
    return fetch;
  }

  it('asks Google as the service account, with the playintegrity scope, and keeps the access token', async () => {
    const fetch = google();
    await expect(decodePlayIntegrityToken('synthetic.token-_1', ANDROID_PACKAGE, settings)).resolves.toEqual(payload());
    await decodePlayIntegrityToken('synthetic.token-_2', ANDROID_PACKAGE, settings);
    expect(fetch).toHaveBeenCalledTimes(3);
    const [, grant] = fetch.mock.calls[0]! as unknown as [string, RequestInit];
    const assertion = new URLSearchParams(String(grant.body)).get('assertion')!;
    const [header, claims, signature] = assertion.split('.');
    expect(JSON.parse(Buffer.from(claims!, 'base64url').toString())).toMatchObject({
      iss: settings.clientEmail, scope: 'https://www.googleapis.com/auth/playintegrity', aud: 'https://oauth2.googleapis.com/token',
    });
    expect(verify('RSA-SHA256', Buffer.from(`${header}.${claims}`), rsaPublicKey, Buffer.from(signature!, 'base64url'))).toBe(true);
    const [url, decode] = fetch.mock.calls[2]! as unknown as [string, RequestInit];
    expect(url).toBe(`https://playintegrity.googleapis.com/v1/${ANDROID_PACKAGE}:decodeIntegrityToken`);
    expect(decode).toMatchObject({ method: 'POST', headers: { authorization: 'Bearer synthetic-access-token' } });
    expect(JSON.parse(String(decode.body))).toEqual({ integrityToken: 'synthetic.token-_2' });
  });

  it('refuses what is not a token without asking Google', async () => {
    const fetch = google();
    for (const token of [undefined, 42, '', 'not a token', 'x'.repeat(16_385)]) {
      await expect(decodePlayIntegrityToken(token, ANDROID_PACKAGE, settings)).rejects.toMatchObject({ reason: 'malformed' });
    }
    expect(fetch).not.toHaveBeenCalled();
  });

  it('names a token Google can\'t read malformed, and Google or its OAuth failing unavailable', async () => {
    google(new Response('{"error":{"status":"INVALID_ARGUMENT"}}', { status: 400 }));
    await expect(decodePlayIntegrityToken('token', ANDROID_PACKAGE, settings)).rejects.toMatchObject({ reason: 'malformed' });
    google(new Response('{}', { status: 503 }));
    await expect(decodePlayIntegrityToken('token', ANDROID_PACKAGE, settings)).rejects.toMatchObject({ reason: 'unavailable' });
    google(Response.json({ other: true }));
    await expect(decodePlayIntegrityToken('token', ANDROID_PACKAGE, settings)).rejects.toMatchObject({ reason: 'malformed' });
    forgetPlayIntegrityAccount();
    vi.stubGlobal('fetch', vi.fn(async () => new Response('{"error":"invalid_grant"}', { status: 400 })));
    await expect(decodePlayIntegrityToken('token', ANDROID_PACKAGE, settings)).rejects.toMatchObject({ reason: 'unavailable' });
    vi.stubGlobal('fetch', vi.fn(async () => { throw new TypeError('fetch failed'); }));
    await expect(decodePlayIntegrityToken('token', ANDROID_PACKAGE, settings)).rejects.toMatchObject({ reason: 'unavailable' });
  });

  it('asks for a new access token once Google refuses the one it has', async () => {
    let status = 401;
    const fetch = google(() => (status === 401 ? new Response('{}', { status: (status = 200, 401) }) : Response.json({ tokenPayloadExternal: payload() })));
    await expect(decodePlayIntegrityToken('token', ANDROID_PACKAGE, settings)).rejects.toMatchObject({ reason: 'unavailable' });
    await decodePlayIntegrityToken('token', ANDROID_PACKAGE, settings);
    expect(fetch.mock.calls.filter(([url]) => String(url) === 'https://oauth2.googleapis.com/token')).toHaveLength(2);
  });

  it('needs an RSA service account key', () => {
    const ec = generateKeyPairSync('ec', { namedCurve: 'prime256v1' }).privateKey.export({ type: 'pkcs8', format: 'pem' }).toString();
    expect(() => new GoogleServiceAccount('a@example', ec, 'scope')).toThrow('RSA');
    expect(() => new GoogleServiceAccount('a@example', 'not a key', 'scope')).toThrow('private key');
  });
});

describe('the verdicts', () => {
  it('count the app Google Play distributes as strong on a device with device or strong integrity, and basic with basic integrity alone', () => {
    expect(playIntegrityVerdict(payload(), expected)).toEqual({ verdict: 'strong', licensing: 'LICENSED', testing: false });
    expect(playIntegrityVerdict(payload({ device: ['MEETS_BASIC_INTEGRITY', 'MEETS_DEVICE_INTEGRITY', 'MEETS_STRONG_INTEGRITY'] }), expected).verdict).toBe('strong');
    expect(playIntegrityVerdict(payload({ device: ['MEETS_BASIC_INTEGRITY'] }), expected).verdict).toBe('basic');
    // Licensing is logged, not required: a Play build installed another way.
    expect(playIntegrityVerdict(payload({ account: { appLicensingVerdict: 'UNLICENSED' }, testing: true }), expected))
      .toEqual({ verdict: 'strong', licensing: 'UNLICENSED', testing: true });
  });

  it('read the signer in base64 web-safe, as Google says, or hex, as its examples show', () => {
    expect(playIntegrityVerdict(payload({ app: { certificateSha256Digest: [PLAY_SIGNER] } }), expected).verdict).toBe('strong');
    expect(playIntegrityVerdict(payload({ app: { certificateSha256Digest: [`${Buffer.from(PLAY_SIGNER, 'hex').toString('base64url')}=`] } }), expected).verdict).toBe('strong');
  });

  it.each<[string, Parameters<typeof payload>[0], string]>([
    ['another app\'s request', { request: { requestPackageName: 'com.example.other' } }, 'request_package'],
    ['another key or challenge', { request: { requestHash: 'cd'.repeat(32) } }, 'request_hash'],
    ['a token asked before the challenge', { request: { timestampMillis: String(issuedAt - 61_000) } }, 'token_time'],
    ['a token from the future', { request: { timestampMillis: String(issuedAt + 66_000) } }, 'token_time'],
    ['a version Play does not distribute', { app: { appRecognitionVerdict: 'UNRECOGNIZED_VERSION' } }, 'app_recognition'],
    // What a token decoded a second time says.
    ['a replayed token', { app: { appRecognitionVerdict: 'UNEVALUATED' }, device: [] }, 'app_recognition'],
    ['another package', { app: { packageName: 'com.example.other' } }, 'app_id'],
    ['another signer', { app: { certificateSha256Digest: [Buffer.alloc(32, 1).toString('base64url')] } }, 'signer'],
    ['no device integrity', { device: [] }, 'device_integrity'],
    ['Google Play Games for PC', { device: ['MEETS_VIRTUAL_INTEGRITY'] }, 'device_integrity'],
  ])('refuse %s as %s', (_name, changes, reason) => {
    expect(() => playIntegrityVerdict(payload(changes), expected)).toThrow(expect.objectContaining({ reason }));
  });

  it('refuse a payload without its request details', () => {
    expect(() => playIntegrityVerdict({}, expected)).toThrow(expect.objectContaining({ reason: 'malformed' }));
  });
});
