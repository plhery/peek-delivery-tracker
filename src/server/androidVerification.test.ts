import { createHash, randomBytes, sign } from 'node:crypto';
import cbor from 'cbor';
import { NextRequest } from 'next/server';
import { afterEach, beforeEach, describe, expect, it, vi } from 'vitest';
import { GET as configuration, POST as register } from '../../app/api/public/native/verification/route';
import { attestationChain, attestationRoot } from '../test/androidAttestation';
import * as roots from './androidAttestationRoots';
import { forgetRevocationList } from './androidKeyAttestation';
import { metricsText } from './metrics';
import { androidAttestSettings, checkAccountRequest, nativeChallenge, nativeRequestPayload, verifyNativeRequest } from './nativeVerification';
import { verifyLookupRequest } from './lookupVerification';
import { SupabaseServiceClient } from './supabase';

vi.mock('./androidAttestationRoots', () => ({ ANDROID_ATTESTATION_ROOTS: [] as string[], SELF_SIGNED_BOOT_KEYS: new Set<string>() }));

const root = attestationRoot();
const appleAppID = 'TESTTEAM01.com.example.Peek';
const androidAppID = 'android:com.plhery.peekdeliverytracker';
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

/** What the app posts to register: a key made with the SHA-256 of the challenge, and its chain as CBOR. */
async function registration(options: Partial<Parameters<typeof attestationChain>[0]> = {}) {
  const req = request('/api/public/native/verification', '{}');
  const { challenge } = nativeChallenge(req, keyID, 'attestation');
  const { x5c, privateKey } = attestationChain({ root, challenge: createHash('sha256').update(challenge).digest(), ...options });
  const attestation = (await cbor.encodeAsync({ fmt: 'android-key', attStmt: { x5c } })).toString('base64');
  return { body: { keyId: keyID, challenge, attestation }, privateKey };
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
  vi.stubGlobal('fetch', vi.fn().mockResolvedValue(Response.json({ entries: { deadbeef: { status: 'REVOKED' } } })));
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

describe('Android key attestation', () => {
  it('is offered with the app ID Android keys register under, and can be turned off', async () => {
    const read = async () => (await configuration(new NextRequest('https://peek.example/api/public/native/verification'), { params: Promise.resolve({}) })).json();
    expect(await read()).toEqual({ appAttestAppId: appleAppID, androidAttestAppId: androidAppID, turnstile: true });
    vi.stubEnv('ANDROID_KEY_ATTESTATION', 'off');
    expect(await read()).toEqual({ appAttestAppId: appleAppID, androidAttestAppId: null, turnstile: true });
    expect(androidAttestSettings({ TURNSTILE_SECRET_KEY: 'secret', ANDROID_KEY_ATTESTATION_SIGNERS: 'AB:'.repeat(31) + 'AB' })?.signers)
      .toContain('ab'.repeat(32));
    expect(() => androidAttestSettings({ TURNSTILE_SECRET_KEY: 'secret', ANDROID_KEY_ATTESTATION_SIGNERS: 'not-a-digest' })).toThrow('SHA-256');
    expect(androidAttestSettings({})).toBeNull();
  });

  it('registers a key, then counts the installation for each signed lookup, before the shared budgets', async () => {
    const { body, privateKey } = await registration();
    expect((await post(body)).status).toBe(204);
    expect(service.request).toHaveBeenCalledWith('/rest/v1/rpc/register_native_attest_key', expect.objectContaining({ body: expect.objectContaining({
      p_key_id: keyID, p_app_id: androidAppID, p_public_key: expect.stringContaining('BEGIN PUBLIC KEY'),
    }) }));
    const verifiedLookups = 'lookup_proof_total{kind="lookup",proof="key_attestation"}';
    const before = await series(verifiedLookups);
    await verifyLookupRequest(signed(request(), privateKey), service, signedBody);
    await verifyLookupRequest(signed(request(), privateKey), service, signedBody);
    expect(service.request).toHaveBeenLastCalledWith('/rest/v1/rpc/accept_native_assertion', expect.objectContaining({ body: expect.objectContaining({
      p_key_id: keyID, p_counter: 2, p_kind: 'lookup', p_limit: 15,
    }) }));
    expect(await series(verifiedLookups)).toBe(before + 2);
  });

  it('refuses an attestation the hardware does not vouch for, which sends the app to the browser check', async () => {
    const lines: string[] = [];
    vi.spyOn(console, 'log').mockImplementation((line: string) => { lines.push(line); });
    const unlocked = 'native_verification_total{kind="attestation",outcome="rejected",reason="unlocked",platform="android"}';
    const before = await series(unlocked);
    const { body } = await registration({ locked: false, bootState: 2 });
    const refused = await post(body);
    expect(refused.status).toBe(403);
    expect(refused.headers.get('X-Lookup-Verification')).toBe('required');
    expect(await series(unlocked)).toBe(before + 1);
    const line = lines.find((entry) => JSON.parse(entry).event === 'native_verification')!;
    expect(JSON.parse(line)).toMatchObject({ kind: 'attestation', platform: 'android', outcome: 'rejected', reason: 'unlocked' });
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

  it('refuses an assertion over other bytes, by another key, or replayed', async () => {
    const { body, privateKey } = await registration();
    await post(body);
    await expect(verifyNativeRequest(signed(request(), privateKey), service, Buffer.from('{}'))).rejects.toMatchObject({ status: 403, reason: 'signature' });
    const stranger = attestationChain({ root, challenge: Buffer.alloc(32) }).privateKey;
    await expect(verifyNativeRequest(signed(request(), stranger), service, signedBody)).rejects.toMatchObject({ status: 403, reason: 'signature' });
    const req = signed(request(), privateKey);
    const replay = new NextRequest(req.url, { method: 'POST', headers: req.headers, body: signedBody.toString() });
    await verifyNativeRequest(req, service, signedBody);
    // The database refuses the used challenge.
    await expect(verifyNativeRequest(replay, service, signedBody)).rejects.toMatchObject({ status: 403, reason: 'replayed' });
  });

  it('refuses Android keys once it is turned off, and leaves App Attest as it was', async () => {
    const { body, privateKey } = await registration();
    await post(body);
    vi.stubEnv('ANDROID_KEY_ATTESTATION', 'off');
    await expect(verifyNativeRequest(signed(request(), privateKey), service, signedBody)).rejects.toMatchObject({ status: 403, reason: 'unconfigured' });
    expect((await post((await registration()).body)).status).toBe(503);
  });

  it('works without App Attest, on a server that only verifies Android', async () => {
    vi.stubEnv('APP_ATTEST_APP_ID', '');
    const { body, privateKey } = await registration();
    expect((await post(body)).status).toBe(204);
    await expect(verifyNativeRequest(signed(request(), privateKey), service, signedBody)).resolves.toBe('android');
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
