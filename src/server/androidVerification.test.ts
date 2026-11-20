import { createHash, generateKeyPairSync, randomBytes, sign, X509Certificate } from 'node:crypto';
import cbor from 'cbor';
import { NextRequest } from 'next/server';
import { afterEach, beforeEach, describe, expect, it, vi } from 'vitest';
import { GET as configuration, POST as register } from '../../app/api/public/native/verification/route';
import { attestationChain, attestationRoot } from '../test/androidAttestation';
import * as roots from './androidAttestationRoots';
import { ANDROID_PACKAGE, ANDROID_SIGNERS, forgetRevocationList } from './androidKeyAttestation';
import { metricsText } from './metrics';
import { androidAttestSettings, checkAccountRequest, nativeChallenge, nativeRequestPayload, verifyNativeRequest } from './nativeVerification';
import { verifyLookupBrowser, verifyLookupRequest } from './lookupVerification';
import { forgetPlayIntegrityAccount, playRequestHash } from './playIntegrity';
import { SupabaseServiceClient } from './supabase';

vi.mock('./androidAttestationRoots', () => ({ ANDROID_ATTESTATION_ROOTS: [] as string[], SELF_SIGNED_BOOT_KEYS: new Set<string>() }));

const root = attestationRoot();
const appleAppID = 'TESTTEAM01.com.example.Peek';
const androidAppID = 'android:com.plhery.peekdeliverytracker';
const serviceKey = generateKeyPairSync('rsa', { modulusLength: 2048 }).privateKey.export({ type: 'pkcs8', format: 'pem' }).toString();
/** The app's own random key ID, as KeystoreAttestation makes one. */
const keyID = randomBytes(32).toString('base64');
const signedBody = Buffer.from('{"trackingNumber":"TESTPARCEL983456"}');
const androidAgent = 'PeekDeliveryTracker/14 okhttp/5.3.0 Android/16';
const service = new SupabaseServiceClient('https://database.example', 'test-key');
/** Each test's own address: registrations are limited per address, and challenges bound to its network. */
let ip = '';
let tests = 0;

function request(path = '/api/public/parcels', body = signedBody.toString()) {
  return new NextRequest(`https://peek.example${path}`, { method: 'POST', body,
    headers: { 'Content-Type': 'application/json', host: 'peek.example', 'x-real-ip': ip, 'user-agent': androidAgent } });
}

/**
 * What the app posts to register: a key made with the SHA-256 of the challenge, its chain as CBOR, and the Play
 * build's Play Integrity token where `playIntegrity` is given. `requestHash` is what the token should name.
 */
async function registration(options: Partial<Parameters<typeof attestationChain>[0]> = {}, playIntegrity?: string) {
  const req = request('/api/public/native/verification', '{}');
  const { challenge } = nativeChallenge(req, keyID, 'attestation');
  const clientDataHash = createHash('sha256').update(challenge).digest();
  const { x5c, privateKey } = attestationChain({ root, challenge: clientDataHash, ...options });
  const attestation = (await cbor.encodeAsync({ fmt: 'android-key', attStmt: { x5c } })).toString('base64');
  return {
    body: { keyId: keyID, challenge, attestation, ...playIntegrity ? { playIntegrity } : {} },
    privateKey,
    requestHash: playRequestHash(clientDataHash, new X509Certificate(x5c[0]!).publicKey),
  };
}

/** Google's decoding of the Play build's token: the app Play distributes, on a device meeting `device`. */
function decodedToken(requestHash: string, device = ['MEETS_DEVICE_INTEGRITY']) {
  return Response.json({ tokenPayloadExternal: {
    requestDetails: { requestPackageName: ANDROID_PACKAGE, requestHash, timestampMillis: String(Date.now()) },
    appIntegrity: { appRecognitionVerdict: 'PLAY_RECOGNIZED', packageName: ANDROID_PACKAGE, versionCode: '42',
      certificateSha256Digest: [Buffer.from(ANDROID_SIGNERS[0]!, 'hex').toString('base64url')] },
    deviceIntegrity: { deviceRecognitionVerdict: device },
    accountDetails: { appLicensingVerdict: 'LICENSED' },
  } });
}

/** A browser proof, as the Turnstile sheet gets one. */
async function browserProof(): Promise<string> {
  return (await verifyLookupBrowser(request('/api/public/verification', '{}'), 'one-use-token')).proof;
}

const post = (body: object) => register(new NextRequest('https://peek.example/api/public/native/verification', {
  method: 'POST', body: JSON.stringify(body),
  headers: { 'Content-Type': 'application/json', host: 'peek.example', 'x-real-ip': ip, 'user-agent': androidAgent },
}), { params: Promise.resolve({}) });

/** Signs `req` as the app does: the key's ECDSA signature of the SHA-256 of the payload. */
function signed<R extends Request>(req: R, privateKey: Parameters<typeof sign>[2], body: Uint8Array = signedBody): R {
  const { challenge } = nativeChallenge(req, keyID, 'assertion');
  const hash = createHash('sha256').update(nativeRequestPayload(req, challenge, body)).digest();
  req.headers.set('X-App-Attest-Key-Id', keyID);
  req.headers.set('X-App-Attest-Challenge', challenge);
  req.headers.set('X-App-Attest-Assertion', sign('sha256', hash, privateKey).toString('base64'));
  return req;
}

/** A counter's value: the first scrape shows a new series at 0 and applies its updates. */
async function series(name: string): Promise<number> {
  await metricsText();
  const line = (await metricsText()).split('\n').find((entry) => entry.startsWith(`${name} `));
  return line ? Number(line.slice(name.length + 1)) : 0;
}

let stored: { public_key: string; app_id: string; sign_count: number } | null;
/** The challenges the database took, which it never takes twice. */
let used: Set<unknown>;
/** Google's answer to decodeIntegrityToken. */
let decode: () => Response;

beforeEach(() => {
  ip = `192.0.2.${++tests}`;
  vi.stubEnv('APP_ATTEST_APP_ID', appleAppID);
  vi.stubEnv('TURNSTILE_SECRET_KEY', 'synthetic-secret');
  vi.stubEnv('TURNSTILE_SITE_KEY', 'synthetic-site-key');
  vi.stubEnv('TURNSTILE_HOSTNAMES', 'peek.example');
  vi.stubEnv('TRUST_PROXY_HEADERS', 'true');
  vi.stubEnv('SUPABASE_URL', 'https://database.example');
  vi.stubEnv('SUPABASE_SERVICE_ROLE_KEY', 'test-key');
  (roots.ANDROID_ATTESTATION_ROOTS as string[]).splice(0, Infinity, root.pem);
  forgetRevocationList();
  forgetPlayIntegrityAccount();
  decode = () => new Response('{}', { status: 503 });
  vi.stubGlobal('fetch', vi.fn(async (input: string | URL | Request) => {
    const url = String(input);
    if (url === 'https://android.googleapis.com/attestation/status') return Response.json({ entries: { deadbeef: { status: 'REVOKED' } } });
    if (url === 'https://oauth2.googleapis.com/token') return Response.json({ access_token: 'synthetic-access-token', expires_in: 3_600 });
    if (url === `https://playintegrity.googleapis.com/v1/${ANDROID_PACKAGE}:decodeIntegrityToken`) return decode();
    if (url === 'https://challenges.cloudflare.com/turnstile/v0/siteverify') return Response.json({ success: true, hostname: 'peek.example', action: 'parcel_lookup' });
    throw new Error(`unexpected fetch ${url}`);
  }));
  stored = null;
  used = new Set();
  vi.spyOn(SupabaseServiceClient.prototype, 'request').mockImplementation(async (path, options) => {
    const body = options?.body as Record<string, unknown>;
    if (path.endsWith('register_native_attest_key')) {
      if (used.has(body.p_challenge)) return false;
      used.add(body.p_challenge);
      stored = { public_key: body.p_public_key as string, app_id: body.p_app_id as string, sign_count: 0 };
      return true;
    }
    if (path.endsWith('get_native_attest_key')) return stored;
    if (path.endsWith('accept_native_assertion')) {
      if (!stored || (body.p_counter as number) <= stored.sign_count || used.has(body.p_challenge)) return 'rejected';
      used.add(body.p_challenge);
      stored.sign_count = body.p_counter as number;
      return body.p_limit === 0 ? 'limited' : 'accepted';
    }
    return true;
  });
});
afterEach(() => { vi.restoreAllMocks(); vi.unstubAllEnvs(); vi.unstubAllGlobals(); });

describe('Android attestation', () => {
  const read = async () => (await configuration(new NextRequest('https://peek.example/api/public/native/verification'), { params: Promise.resolve({}) })).json();

  it('is offered in log mode by default, with the app ID keys register under, and signs lookups only when enforced', async () => {
    expect(await read()).toEqual({
      appAttestAppId: appleAppID, androidAttestAppId: null, turnstile: true,
      androidAttestation: { mode: 'log', appId: androidAppID, playIntegrityCloudProjectNumber: null },
    });
    vi.stubEnv('ANDROID_ATTESTATION_MODE', 'enforce');
    vi.stubEnv('PLAY_INTEGRITY_CLOUD_PROJECT_NUMBER', '123456789012');
    vi.stubEnv('PLAY_INTEGRITY_CLIENT_EMAIL', 'integrity@example.iam.gserviceaccount.com');
    vi.stubEnv('PLAY_INTEGRITY_PRIVATE_KEY', serviceKey);
    expect(await read()).toEqual({
      appAttestAppId: appleAppID, androidAttestAppId: androidAppID, turnstile: true,
      androidAttestation: { mode: 'enforce', appId: androidAppID, playIntegrityCloudProjectNumber: '123456789012' },
    });
    vi.stubEnv('ANDROID_ATTESTATION_MODE', 'off');
    expect(await read()).toEqual({ appAttestAppId: appleAppID, androidAttestAppId: null, androidAttestation: null, turnstile: true });
  });

  it('reads its settings: added signers, allowances per verdict, and nothing without Turnstile', () => {
    expect(androidAttestSettings({ TURNSTILE_SECRET_KEY: 'secret', ANDROID_ATTESTATION_SIGNERS: 'AB:'.repeat(31) + 'AB' })?.signers)
      .toContain('ab'.repeat(32));
    expect(() => androidAttestSettings({ TURNSTILE_SECRET_KEY: 'secret', ANDROID_ATTESTATION_SIGNERS: 'not-a-digest' })).toThrow('SHA-256');
    expect(androidAttestSettings({ TURNSTILE_SECRET_KEY: 'secret' })).toMatchObject({
      mode: 'log', playIntegrity: null, allowances: { strong: { lookup: 15, detection: 60 }, basic: { lookup: 5, detection: 20 } },
    });
    expect(androidAttestSettings({ TURNSTILE_SECRET_KEY: 'secret', ANDROID_ATTESTATION_MODE: ' Enforce ', NATIVE_LOOKUPS_PER_DAY: '30',
      ANDROID_BASIC_LOOKUPS_PER_DAY: '0', ANDROID_BASIC_DETECTIONS_PER_DAY: 'many' })).toMatchObject({
      mode: 'enforce', allowances: { strong: { lookup: 30, detection: 60 }, basic: { lookup: 0, detection: 20 } },
    });
    expect(androidAttestSettings({ TURNSTILE_SECRET_KEY: 'secret', ANDROID_ATTESTATION_MODE: 'other' })?.mode).toBe('log');
    expect(androidAttestSettings({})).toBeNull();
  });

  it('registers a key in log mode and counts its verdict, while its lookups keep the browser check and spend nothing', async () => {
    const registered = 'android_attestation_total{key="strong",play="absent",mode="log"}';
    const before = await series(registered);
    const { body, privateKey } = await registration();
    expect((await post(body)).status).toBe(204);
    expect(service.request).toHaveBeenCalledWith('/rest/v1/rpc/register_native_attest_key', expect.objectContaining({ body: expect.objectContaining({
      p_key_id: keyID, p_app_id: `${androidAppID}:key-strong`, p_public_key: expect.stringContaining('BEGIN PUBLIC KEY'),
    }) }));
    expect(await series(registered)).toBe(before + 1);

    const refused = await verifyLookupRequest(signed(request(), privateKey), service, signedBody).catch((error: unknown) => error);
    expect(refused).toMatchObject({ status: 403 });
    const headers = new Headers((refused as { headers: HeadersInit }).headers);
    expect(headers.get('X-Lookup-Verification')).toBe('required');
    expect(headers.get('X-Native-Assertion')).toBe('mode');
    const proven = signed(request(), privateKey);
    proven.headers.set('X-Lookup-Proof', await browserProof());
    const turnstile = 'lookup_proof_total{kind="lookup",proof="turnstile"}';
    const passed = await series(turnstile);
    await verifyLookupRequest(proven, service, signedBody);
    expect(await series(turnstile)).toBe(passed + 1);
    expect(service.request).not.toHaveBeenCalledWith('/rest/v1/rpc/accept_native_assertion', expect.anything());
    expect(await series('native_verification_total{kind="lookup",outcome="ignored",reason="strong",platform="android"}')).toBeGreaterThanOrEqual(2);
  });

  it('when enforced, counts the installation for each signed lookup within its verdict\'s allowance, before the shared budgets', async () => {
    vi.stubEnv('ANDROID_ATTESTATION_MODE', 'enforce');
    const { body, privateKey } = await registration();
    expect((await post(body)).status).toBe(204);
    const verifiedLookups = 'lookup_proof_total{kind="lookup",proof="key_attestation"}';
    const before = await series(verifiedLookups);
    await verifyLookupRequest(signed(request(), privateKey), service, signedBody);
    await verifyLookupRequest(signed(request(), privateKey), service, signedBody);
    expect(service.request).toHaveBeenLastCalledWith('/rest/v1/rpc/accept_native_assertion', expect.objectContaining({ body: expect.objectContaining({
      p_key_id: keyID, p_counter: 2, p_kind: 'lookup', p_limit: 15,
    }) }));
    expect(await series(verifiedLookups)).toBe(before + 2);
    // A lookup's new carrier asks carriers as a lookup does, and spends the same allowance.
    await verifyLookupRequest(signed(request('/api/public/parcels/00000000-0000-4000-8000-000000000001/carrier'), privateKey), service, signedBody);
    expect(service.request).toHaveBeenLastCalledWith('/rest/v1/rpc/accept_native_assertion', expect.objectContaining({ body: expect.objectContaining({
      p_key_id: keyID, p_counter: 3, p_kind: 'lookup', p_limit: 15,
    }) }));
  });

  it('registers a key in the secure hardware of an unlocked phone as basic, with the smaller allowance', async () => {
    vi.stubEnv('ANDROID_ATTESTATION_MODE', 'enforce');
    const { body, privateKey } = await registration({ locked: false, bootState: 2 });
    expect((await post(body)).status).toBe(204);
    expect(stored?.app_id).toBe(`${androidAppID}:key-basic`);
    await verifyLookupRequest(signed(request('/api/public/detect'), privateKey), service, signedBody);
    expect(service.request).toHaveBeenLastCalledWith('/rest/v1/rpc/accept_native_assertion', expect.objectContaining({ body: expect.objectContaining({
      p_kind: 'detection', p_limit: 20,
    }) }));
  });

  it('leaves a key whose allowance is used to the browser check, which still lets it through, and says until when', async () => {
    vi.stubEnv('ANDROID_ATTESTATION_MODE', 'enforce');
    vi.stubEnv('ANDROID_BASIC_LOOKUPS_PER_DAY', '0');
    const { body, privateKey } = await registration({ locked: false });
    await post(body);
    const refused = await verifyLookupRequest(signed(request(), privateKey), service, signedBody).catch((error: unknown) => error);
    const headers = new Headers((refused as { headers: HeadersInit }).headers);
    expect(refused).toMatchObject({ status: 403 });
    expect(headers.get('X-Native-Assertion')).toBe('limited');
    expect(Number(headers.get('Retry-After'))).toBeGreaterThan(0);
    const proven = signed(request(), privateKey);
    proven.headers.set('X-Lookup-Proof', await browserProof());
    await expect(verifyLookupRequest(proven, service, signedBody)).resolves.toEqual({ proof: 'turnstile', app: 'android' });
    expect(await series('native_verification_total{kind="lookup",outcome="limited",reason="basic",platform="android"}')).toBeGreaterThanOrEqual(2);
  });

  it('refuses an attestation the hardware does not vouch for, which sends the app to the browser check', async () => {
    const lines: string[] = [];
    vi.spyOn(console, 'log').mockImplementation((line: string) => { lines.push(line); });
    const failedBoot = 'native_verification_total{kind="attestation",outcome="rejected",reason="boot_state",platform="android"}';
    const before = await series(failedBoot);
    const { body } = await registration({ bootState: 3 });
    const refused = await post(body);
    expect(refused.status).toBe(403);
    expect(refused.headers.get('X-Lookup-Verification')).toBe('required');
    expect(await series(failedBoot)).toBe(before + 1);
    const line = lines.find((entry) => JSON.parse(entry).event === 'native_verification')!;
    expect(JSON.parse(line)).toMatchObject({ kind: 'attestation', platform: 'android', outcome: 'rejected', reason: 'boot_state', key: 'boot_state', play: 'absent', mode: 'log' });
    expect(line).not.toContain(keyID);
    expect(line).not.toContain(ip);
    expect(service.request).not.toHaveBeenCalledWith('/rest/v1/rpc/register_native_attest_key', expect.anything());
  });

  it('refuses a registration for another key or challenge, and a revoked chain', async () => {
    const { body } = await registration();
    expect((await post({ ...body, keyId: randomBytes(32).toString('base64') })).status).toBe(403);
    const other = await registration();
    expect((await post({ ...body, challenge: other.body.challenge })).status).toBe(403);
    const revoked = await registration({ intermediateSerial: 0xdeadbeefn });
    expect((await post(revoked.body)).status).toBe(403);
    expect(service.request).not.toHaveBeenCalledWith('/rest/v1/rpc/register_native_attest_key', expect.anything());
  });

  it('leaves an assertion over other bytes, by another key, or replayed to the browser check', async () => {
    vi.stubEnv('ANDROID_ATTESTATION_MODE', 'enforce');
    const { body, privateKey } = await registration();
    await post(body);
    const badSignature = 'native_verification_total{kind="lookup",outcome="rejected",reason="signature",platform="android"}';
    const before = await series(badSignature);
    await expect(verifyNativeRequest(signed(request(), privateKey), service, Buffer.from('{}'))).resolves.toEqual({ proof: null, fallback: 'refused' });
    const stranger = attestationChain({ root, challenge: Buffer.alloc(32) }).privateKey;
    await expect(verifyNativeRequest(signed(request(), stranger), service, signedBody)).resolves.toEqual({ proof: null, fallback: 'refused' });
    expect(await series(badSignature)).toBe(before + 2);
    const req = signed(request(), privateKey);
    const replay = new NextRequest(req.url, { method: 'POST', headers: req.headers, body: signedBody.toString() });
    await expect(verifyNativeRequest(req, service, signedBody)).resolves.toEqual({ proof: 'key_attestation' });
    // The database refuses the used challenge.
    await expect(verifyNativeRequest(replay, service, signedBody)).resolves.toEqual({ proof: null, fallback: 'refused' });
    const refused = await verifyLookupRequest(signed(request(), stranger), service, signedBody).catch((error: unknown) => error);
    expect(new Headers((refused as { headers: HeadersInit }).headers).get('X-Native-Assertion')).toBe('refused');
  });

  it('leaves Android keys to the browser check once it is turned off, and App Attest as it was', async () => {
    const { body, privateKey } = await registration();
    await post(body);
    vi.stubEnv('ANDROID_ATTESTATION_MODE', 'off');
    await expect(verifyNativeRequest(signed(request(), privateKey), service, signedBody)).resolves.toEqual({ proof: null, fallback: 'refused' });
    expect((await post((await registration()).body)).status).toBe(503);
  });

  it('works without App Attest, on a server that only verifies Android', async () => {
    vi.stubEnv('APP_ATTEST_APP_ID', '');
    vi.stubEnv('ANDROID_ATTESTATION_MODE', 'enforce');
    const { body, privateKey } = await registration();
    expect((await post(body)).status).toBe(204);
    await expect(verifyNativeRequest(signed(request(), privateKey), service, signedBody)).resolves.toEqual({ proof: 'key_attestation' });
  });

  it('checks a signed-in Android request in log mode without spending an allowance or refusing it', async () => {
    const { body, privateKey } = await registration();
    await post(body);
    const add = request('/api/packages');
    await checkAccountRequest(signed(add, privateKey), service, signedBody, 'account_lookup');
    expect(service.request).toHaveBeenLastCalledWith('/rest/v1/rpc/accept_native_assertion', expect.objectContaining({ body: expect.objectContaining({ p_limit: 0 }) }));
    vi.stubEnv('NATIVE_ATTEST_ACCOUNT_MODE', 'enforce');
    // Enforcement is the iPhone's: an Android request with a bad signature still goes through.
    await expect(checkAccountRequest(signed(request('/api/packages'), privateKey), service, Buffer.from('{}'), 'account_lookup')).resolves.toBeUndefined();
    await expect(checkAccountRequest(request('/api/packages'), service, signedBody, 'account_lookup')).resolves.toBeUndefined();
  });
});

describe('Play Integrity in the Play build\'s registration', () => {
  beforeEach(() => {
    vi.stubEnv('PLAY_INTEGRITY_CLOUD_PROJECT_NUMBER', '123456789012');
    vi.stubEnv('PLAY_INTEGRITY_CLIENT_EMAIL', 'integrity@example.iam.gserviceaccount.com');
    vi.stubEnv('PLAY_INTEGRITY_PRIVATE_KEY', serviceKey);
  });

  it('registers a key the hardware can\'t vouch for on Play Integrity\'s word, and counts both checks', async () => {
    vi.stubEnv('ANDROID_ATTESTATION_MODE', 'enforce');
    const both = 'android_attestation_total{key="security_level",play="strong",mode="enforce"}';
    const before = await series(both);
    const { body, privateKey, requestHash } = await registration({ securityLevel: 0, keyMintSecurityLevel: 0 }, 'synthetic.integrity-token');
    decode = () => decodedToken(requestHash);
    expect((await post(body)).status).toBe(204);
    expect(stored?.app_id).toBe(`${androidAppID}:play-strong`);
    expect(await series(both)).toBe(before + 1);
    const lookups = 'lookup_proof_total{kind="lookup",proof="play_integrity"}';
    const passed = await series(lookups);
    await verifyLookupRequest(signed(request(), privateKey), service, signedBody);
    expect(await series(lookups)).toBe(passed + 1);
    expect(vi.mocked(fetch)).toHaveBeenCalledWith(`https://playintegrity.googleapis.com/v1/${ANDROID_PACKAGE}:decodeIntegrityToken`,
      expect.objectContaining({ body: JSON.stringify({ integrityToken: 'synthetic.integrity-token' }) }));
  });

  it('keeps the better verdict, the hardware\'s on a tie', async () => {
    const unlocked = await registration({ locked: false }, 'token');
    decode = () => decodedToken(unlocked.requestHash);
    await post(unlocked.body);
    expect(stored?.app_id).toBe(`${androidAppID}:play-strong`);
    const locked = await registration({}, 'token');
    decode = () => decodedToken(locked.requestHash, ['MEETS_BASIC_INTEGRITY']);
    await post(locked.body);
    expect(stored?.app_id).toBe(`${androidAppID}:key-strong`);
    const tie = await registration({}, 'token');
    decode = () => decodedToken(tie.requestHash);
    await post(tie.body);
    expect(stored?.app_id).toBe(`${androidAppID}:key-strong`);
  });

  it('refuses a token for another key, and registers on the hardware\'s word when Google does not answer', async () => {
    const other = await registration({ securityLevel: 0 }, 'token');
    decode = () => decodedToken('ab'.repeat(32));
    const refused = await post(other.body);
    expect(refused.status).toBe(403);
    expect(await series('android_attestation_total{key="security_level",play="request_hash",mode="log"}')).toBeGreaterThanOrEqual(1);
    const unavailable = 'android_attestation_total{key="strong",play="unavailable",mode="log"}';
    const before = await series(unavailable);
    decode = () => new Response('{}', { status: 503 });
    expect((await post((await registration({}, 'token')).body)).status).toBe(204);
    expect(stored?.app_id).toBe(`${androidAppID}:key-strong`);
    expect(await series(unavailable)).toBe(before + 1);
  });

  it('takes a chain with a token as long as the server reads, past the usual body limit', async () => {
    const token = `${'a'.repeat(8_000)}.${'b'.repeat(8_000)}`;
    const { body, requestHash } = await registration({ locked: false }, token);
    expect(JSON.stringify(body).length).toBeGreaterThan(16_384);
    decode = () => decodedToken(requestHash);
    expect((await post(body)).status).toBe(204);
    expect(stored?.app_id).toBe(`${androidAppID}:play-strong`);
  });

  it('ignores a token where the server reads none', async () => {
    vi.stubEnv('PLAY_INTEGRITY_CLOUD_PROJECT_NUMBER', '');
    const unconfigured = 'android_attestation_total{key="strong",play="unconfigured",mode="log"}';
    const before = await series(unconfigured);
    expect((await post((await registration({}, 'token')).body)).status).toBe(204);
    expect(await series(unconfigured)).toBe(before + 1);
    expect(vi.mocked(fetch)).not.toHaveBeenCalledWith(expect.stringContaining('playintegrity'), expect.anything());
  });
});
