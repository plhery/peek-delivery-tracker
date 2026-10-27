import { createHash, generateKeyPairSync, sign } from 'node:crypto';
import cbor from 'cbor';
import { NextRequest } from 'next/server';
import { afterEach, beforeEach, describe, expect, it, vi } from 'vitest';
import { GET as configuration, POST as register } from '../../app/api/public/native/verification/route';
import { POST as challengeRoute } from '../../app/api/public/native/challenge/route';
import { POST as lookUp } from '../../app/api/public/parcels/route';
import { GET as page } from '../../app/api/public/native/turnstile/route';
import { POST as addParcel } from '../../app/api/packages/route';
import { POST as refreshParcel } from '../../app/api/packages/[id]/sync/route';
import { POST as refreshAll } from '../../app/api/sync/route';
import { POST as detectForAccount } from '../../app/api/carriers/detect/route';
import { SupabaseAuthenticator } from './auth';
import { accountAttestMode, nativeAttestSettings, nativeChallenge, nativeRequestPayload, registerNativeKey, verifyNativeRequest } from './nativeVerification';
import { verifyLookupRequest, verifyLookupBrowser } from './lookupVerification';
import { metricsText } from './metrics';
import { SupabaseError, SupabaseServiceClient, SupabaseUserClient } from './supabase';

const appID = 'TESTTEAM01.com.example.Peek';
const keyID = Buffer.alloc(32, 7).toString('base64');
const { privateKey, publicKey } = generateKeyPairSync('ec', { namedCurve: 'prime256v1' });
const storedKey = publicKey.export({ type: 'spki', format: 'pem' }).toString();
/** The body every assertion here signs, as the route reads it. */
const signedBody = Buffer.from('{"trackingNumber":"TESTPARCEL983456"}');
function request(path = '/api/public/parcels', body = signedBody.toString(), ip = '192.0.2.10') {
  return new NextRequest(`https://peek.example${path}`, { method: 'POST', body,
    headers: { 'Content-Type': 'application/json', host: 'peek.example', 'x-real-ip': ip } });
}
/** Adds an assertion over `body` to the request, as the app signs it. */
async function signed<R extends Request>(req: R, body: Uint8Array, options: { counter?: number; appID?: string; extension?: object } = {}): Promise<R> {
  const { challenge } = nativeChallenge(req, keyID, 'assertion');
  const auth = Buffer.alloc(37);
  createHash('sha256').update(options.appID ?? appID).digest().copy(auth);
  auth.writeUInt32BE(options.counter ?? 1, 33);
  if (options.extension) auth[32] = 0x80;
  const authenticatorData = options.extension ? Buffer.concat([auth, await cbor.encodeAsync(options.extension)]) : auth;
  const payload = nativeRequestPayload(req, challenge, body);
  const nonce = createHash('sha256').update(Buffer.concat([authenticatorData, createHash('sha256').update(payload).digest()])).digest();
  const signature = sign('sha256', nonce, privateKey);
  req.headers.set('X-App-Attest-Key-Id', keyID);
  req.headers.set('X-App-Attest-Challenge', challenge);
  req.headers.set('X-App-Attest-Assertion', (await cbor.encodeAsync({ signature, authenticatorData })).toString('base64'));
  return req;
}
const signedRequest = (options: Parameters<typeof signed>[2] = {}) => signed(request(), signedBody, options);
const service = new SupabaseServiceClient('https://database.example', 'test-key');
/** A counter's value: the first scrape shows a new series at 0 and applies its updates. */
async function series(name: string): Promise<number> {
  await metricsText();
  const line = (await metricsText()).split('\n').find((entry) => entry.startsWith(`${name} `));
  return line ? Number(line.slice(name.length + 1)) : 0;
}
beforeEach(() => {
  vi.stubEnv('APP_ATTEST_APP_ID', appID);
  vi.stubEnv('APP_ATTEST_ALLOW_DEVELOPMENT', 'false');
  vi.stubEnv('TURNSTILE_SECRET_KEY', 'synthetic-secret');
  vi.stubEnv('TURNSTILE_SITE_KEY', 'synthetic-site-key');
  vi.stubEnv('TURNSTILE_HOSTNAMES', 'peek.example');
  vi.stubEnv('TRUST_PROXY_HEADERS', 'true');
  vi.stubEnv('SUPABASE_URL', 'https://database.example');
  vi.stubEnv('SUPABASE_SERVICE_ROLE_KEY', 'test-key');
  vi.spyOn(SupabaseServiceClient.prototype, 'request').mockImplementation(async (path, options) => {
    if (path.endsWith('get_native_attest_key')) return { public_key: storedKey, app_id: appID, sign_count: 0 };
    // As the database answers: with no allowance to spend, a valid assertion is `limited`.
    if (path.endsWith('accept_native_assertion')) return (options?.body as { p_limit?: number }).p_limit === 0 ? 'limited' : 'accepted';
    return true;
  });
});
afterEach(() => { vi.restoreAllMocks(); vi.unstubAllEnvs(); vi.unstubAllGlobals(); vi.useRealTimers(); });

describe('native verification', () => {
  it('requires a valid app ID and secret, and refuses development keys in production', () => {
    expect(nativeAttestSettings({})).toBeNull();
    expect(() => nativeAttestSettings({ APP_ATTEST_APP_ID: 'invalid' })).toThrow('valid app ID');
    expect(() => nativeAttestSettings({ APP_ATTEST_APP_ID: appID, TURNSTILE_SECRET_KEY: 'secret', NODE_ENV: 'production', APP_ATTEST_ALLOW_DEVELOPMENT: 'true' })).toThrow('production');
    expect(nativeAttestSettings({ APP_ATTEST_APP_ID: appID, TURNSTILE_SECRET_KEY: 'secret' })?.bundleIdentifier).toBe('com.example.Peek');
  });
  it('verifies a real synthetic P256 signature and atomically spends the installation allowance', async () => {
    await verifyNativeRequest(await signedRequest(), service, signedBody);
    expect(service.request).toHaveBeenLastCalledWith('/rest/v1/rpc/accept_native_assertion', expect.objectContaining({ body: expect.objectContaining({
      p_key_id: keyID, p_counter: 1, p_kind: 'lookup', p_limit: 15, p_challenge: expect.stringMatching(/^[a-f0-9]{64}$/),
    }) }));
  });
  it('checks the assertion over the bytes it is given, the request\'s own body being already read', async () => {
    const req = await signedRequest();
    await req.text();
    await verifyNativeRequest(req, service, signedBody);
    expect(service.request).toHaveBeenLastCalledWith('/rest/v1/rpc/accept_native_assertion', expect.anything());
  });
  it('verifies a lookup over the body the route read from its stream', async () => {
    vi.spyOn(console, 'log').mockImplementation(() => undefined);
    const claim = vi.spyOn(SupabaseServiceClient.prototype, 'claimPublicAllowance').mockResolvedValue({ allowed: false, scope: 'bucket', overallUsed: 0 });
    const send = async (body: Buffer) => {
      const signed = await signedRequest();
      const chunks = [body.subarray(0, 10), body.subarray(10)];
      return lookUp(new NextRequest(signed.url, { method: 'POST', headers: signed.headers, duplex: 'half',
        body: new ReadableStream<Uint8Array>({ pull(controller) { const chunk = chunks.shift(); if (chunk) controller.enqueue(chunk); else controller.close(); } }),
      }), { params: Promise.resolve({}) });
    };
    // Once verified, the lookup reaches its daily allowance, used up here.
    expect((await send(signedBody)).status).toBe(429);
    expect(service.request).toHaveBeenCalledWith('/rest/v1/rpc/accept_native_assertion', expect.anything());
    expect((await send(Buffer.from('{"trackingNumber":"TESTPARCEL674321"}'))).status).toBe(403);
    expect(claim).toHaveBeenCalledOnce();
  });
  it.each([
    ['body', 'signature'], ['path', 'signature'], ['method', 'signature'], ['network', 'challenge_mismatch'],
    ['hostname', 'challenge_mismatch'], ['key', 'challenge_mismatch'], ['challenge', 'malformed'], ['signature', 'signature'],
  ])('rejects a signed request after changing its %s, as a %s failure', async (kind, reason) => {
    const original = await signedRequest();
    let req: Request = original;
    const body = kind === 'body' ? Buffer.from('{}') : signedBody;
    if (kind === 'path') req = new Request('https://peek.example/api/public/detect', original);
    if (kind === 'method') req = new Request(original, { method: 'PUT' });
    if (kind === 'network') req.headers.set('x-real-ip', '198.51.100.4');
    if (kind === 'hostname') req.headers.set('host', 'attacker.example');
    if (kind === 'key') req.headers.set('x-app-attest-key-id', Buffer.alloc(32, 8).toString('base64'));
    if (kind === 'challenge') req.headers.set('x-app-attest-challenge', original.headers.get('x-app-attest-challenge')!.replace(/.$/, '!'));
    if (kind === 'signature') req.headers.set('x-app-attest-assertion', (await cbor.encodeAsync({ signature: Buffer.alloc(71), authenticatorData: Buffer.alloc(37) })).toString('base64'));
    await expect(verifyNativeRequest(req, service, body)).rejects.toMatchObject({ status: 403, reason });
    expect(service.request).not.toHaveBeenCalledWith('/rest/v1/rpc/accept_native_assertion', expect.anything());
  });
  it('rejects expired, wrongly scoped, malformed and unconfigured challenges', async () => {
    vi.useFakeTimers();
    const req = await signedRequest();
    vi.advanceTimersByTime(121_000);
    await expect(verifyNativeRequest(req, service, signedBody)).rejects.toMatchObject({ status: 403, reason: 'challenge_expired' });
    expect(() => nativeChallenge(request(), 'invalid', 'assertion')).toThrow('Invalid');
    expect(() => nativeChallenge(request(), keyID, 'other')).toThrow('Invalid');
    const other = await signedRequest();
    other.headers.set('x-app-attest-challenge', nativeChallenge(other, keyID, 'attestation').challenge);
    await expect(verifyNativeRequest(other, service, signedBody)).rejects.toMatchObject({ status: 403, reason: 'challenge_mismatch' });
    const unconfigured = await signedRequest();
    vi.stubEnv('APP_ATTEST_APP_ID', '');
    await expect(verifyNativeRequest(unconfigured, service, signedBody)).rejects.toMatchObject({ status: 403, reason: 'unconfigured' });
  });
  it('rejects replayed counters, unknown or incorrectly scoped keys and malformed assertions', async () => {
    await expect(verifyNativeRequest(await signedRequest({ counter: 0 }), service, signedBody)).rejects.toMatchObject({ status: 403, reason: 'counter' });
    await expect(verifyNativeRequest(await signedRequest({ appID: 'TESTTEAM02.com.example.Other' }), service, signedBody)).rejects.toMatchObject({ status: 403, reason: 'app_id' });
    vi.mocked(service.request).mockResolvedValueOnce(null);
    await expect(verifyNativeRequest(await signedRequest(), service, signedBody)).rejects.toMatchObject({ status: 403, reason: 'unknown_key' });
    vi.mocked(service.request).mockResolvedValueOnce({ public_key: storedKey, app_id: 'other', sign_count: 0 });
    await expect(verifyNativeRequest(await signedRequest(), service, signedBody)).rejects.toMatchObject({ status: 403, reason: 'app_id' });
    for (const value of ['not base64!', 'AA==', (await cbor.encodeAsync({ signature: 'bad', authenticatorData: Buffer.alloc(37) })).toString('base64'), 'A'.repeat(4100)]) {
      const req = await signedRequest(); req.headers.set('x-app-attest-assertion', value);
      await expect(verifyNativeRequest(req, service, signedBody)).rejects.toMatchObject({ status: 403, reason: 'malformed' });
    }
  });
  it('checks signed launch categories and bundle versions on systems that supply them', async () => {
    await verifyNativeRequest(await signedRequest({ extension: { validationCategory: 4, bundleVersion: '2.1' } }), service, signedBody);
    for (const [extension, reason] of [[{ validationCategory: 0 }, 'category'], [{ validationCategory: 3 }, 'category'], [{ bundleVersion: '<bad>' }, 'bundle_version']] as const) {
      await expect(verifyNativeRequest(await signedRequest({ extension }), service, signedBody)).rejects.toMatchObject({ status: 403, reason });
    }
    vi.stubEnv('APP_ATTEST_ALLOW_DEVELOPMENT', 'true');
    await verifyNativeRequest(await signedRequest({ extension: { validationCategory: 3 } }), service, signedBody);
  });
  it('reads the launch category as the four little-endian bytes iOS writes', async () => {
    const category = (value: number) => { const bytes = Buffer.alloc(4); bytes.writeUInt32LE(value); return bytes; };
    await verifyNativeRequest(await signedRequest({ extension: { apple_bundle_version_01: '1', apple_validation_category_01: category(4) } }), service, signedBody);
    await verifyNativeRequest(await signedRequest({ extension: { apple_validation_category_01: category(2) } }), service, signedBody);
    for (const value of [category(3), category(1), Buffer.from([4, 0])]) {
      await expect(verifyNativeRequest(await signedRequest({ extension: { apple_validation_category_01: value } }), service, signedBody))
        .rejects.toMatchObject({ status: 403, reason: 'category' });
    }
  });
  it('returns daily limits and rejects a replay refused by the atomic database check', async () => {
    vi.mocked(service.request).mockResolvedValueOnce({ public_key: storedKey, app_id: appID, sign_count: 0 }).mockResolvedValueOnce('limited');
    await expect(verifyNativeRequest(await signedRequest(), service, signedBody)).rejects.toMatchObject({ status: 429, headers: { 'Retry-After': expect.any(String) } });
    vi.mocked(service.request).mockResolvedValueOnce({ public_key: storedKey, app_id: appID, sign_count: 0 }).mockResolvedValueOnce('rejected');
    await expect(verifyNativeRequest(await signedRequest(), service, signedBody)).rejects.toMatchObject({ status: 403, reason: 'replayed' });
  });
  it('does not let incomplete attestation headers fall back to a browser proof or a user-agent', async () => {
    vi.stubGlobal('fetch', vi.fn().mockResolvedValue(Response.json({ success: true, hostname: 'peek.example', action: 'parcel_lookup' })));
    const req = request();
    const { proof } = await verifyLookupBrowser(req, 'synthetic-token');
    req.headers.set('x-lookup-proof', proof);
    req.headers.set('user-agent', 'PeekDeliveryTracker/1 CFNetwork/1 Darwin/1');
    req.headers.set('x-app-attest-key-id', keyID);
    await expect(verifyLookupRequest(req, service, signedBody)).rejects.toMatchObject({ status: 403 });
    // Nor does the app's user-agent alone, from a build that says it verifies or one that never did.
    req.headers.delete('x-app-attest-key-id'); req.headers.delete('x-lookup-proof');
    await expect(verifyLookupRequest(req, service, signedBody)).rejects.toMatchObject({ status: 403 });
    req.headers.set('x-native-verification', '1');
    await expect(verifyLookupRequest(req, service, signedBody)).rejects.toMatchObject({ status: 403 });
  });
  it('rejects invalid attestations before storing a public key', async () => {
    const req = request();
    const challenge = nativeChallenge(req, keyID, 'attestation').challenge;
    for (const [attestation, reason] of [['bad!', 'malformed'], [(await cbor.encodeAsync({ attStmt: { x5c: [Buffer.alloc(10), Buffer.alloc(10)] }, authData: Buffer.alloc(87) })).toString('base64'), 'chain']]) {
      await expect(registerNativeKey(req, service, { keyId: keyID, challenge, attestation })).rejects.toMatchObject({ status: 403, reason });
    }
    await expect(registerNativeKey(req, service, { keyId: 'short', challenge, attestation: 'AA==' })).rejects.toMatchObject({ status: 403, reason: 'malformed' });
    await expect(registerNativeKey(request('/api/public/native/verification', '{}', '198.51.100.4'), service, { keyId: keyID, challenge, attestation: 'AA==' }))
      .rejects.toMatchObject({ status: 403, reason: 'challenge_mismatch' });
    expect(service.request).not.toHaveBeenCalled();
  });
  it('names each failure in its log line and counts every check, without a key, challenge or address', async () => {
    const lines: string[] = [];
    vi.spyOn(console, 'log').mockImplementation((line: string) => { lines.push(line); });
    const events = () => lines.map((line) => JSON.parse(line) as Record<string, unknown>).filter((line) => line.event === 'native_verification');
    const rejectedSignature = 'native_verification_total{kind="detection",outcome="rejected",reason="signature"}';
    const verifiedLookup = 'native_verification_total{kind="lookup",outcome="verified",reason="none"}';
    const attested = 'lookup_proof_total{kind="lookup",proof="app_attest"}';
    const before = [await series(rejectedSignature), await series(verifiedLookup), await series(attested)];
    const req = await signedRequest();
    await expect(verifyNativeRequest(new Request('https://peek.example/api/public/detect', req), service, signedBody)).rejects.toMatchObject({ status: 403 });
    await verifyLookupRequest(await signedRequest(), service, signedBody);
    const req2 = request('/api/public/native/verification');
    await expect(registerNativeKey(req2, service, { keyId: keyID, challenge: nativeChallenge(req2, keyID, 'attestation').challenge, attestation: 'bad!' }))
      .rejects.toMatchObject({ status: 403 });
    expect(events().map(({ kind, outcome, reason }) => ({ kind, outcome, reason }))).toEqual([
      { kind: 'detection', outcome: 'rejected', reason: 'signature' },
      { kind: 'lookup', outcome: 'verified', reason: undefined },
      { kind: 'attestation', outcome: 'rejected', reason: 'malformed' },
    ]);
    expect(lines.join('\n')).not.toContain(keyID);
    expect(lines.join('\n')).not.toContain('192.0.2.10');
    expect([await series(rejectedSignature), await series(verifiedLookup), await series(attested)]).toEqual(before.map((value) => value + 1));
  });
  it('exposes the free fallback without enabling App Attest and returns no-store challenges', async () => {
    vi.stubEnv('APP_ATTEST_APP_ID', '');
    const config = await configuration(new NextRequest('https://peek.example/api/public/native/verification'), { params: Promise.resolve({}) });
    expect(await config.json()).toEqual({ appAttestAppId: null, turnstile: true });
    const unavailable = await register(request('/api/public/native/verification', '{}'), { params: Promise.resolve({}) });
    expect(unavailable.status).toBe(503);
    vi.stubEnv('APP_ATTEST_APP_ID', appID);
    const result = await challengeRoute(request('/api/public/native/challenge', JSON.stringify({ keyId: keyID, purpose: 'assertion' })), { params: Promise.resolve({}) });
    expect(result.status).toBe(200);
    expect(result.headers.get('Cache-Control')).toBe('no-store');
    expect(await result.json()).toMatchObject({ challenge: expect.any(String), expiresAt: expect.any(Number) });
  });
  it('hosts a nonce-protected, non-cacheable WKWebView bridge on the configured origin', async () => {
    const result = await page(new NextRequest('https://peek.example/api/public/native/turnstile?language=fr'), { params: Promise.resolve({}) });
    const html = await result.text();
    const nonce = /nonce="([^"]+)"/.exec(html)![1];
    expect(result.headers.get('Content-Security-Policy')).toContain(`script-src 'nonce-${nonce}' 'strict-dynamic'`);
    expect(result.headers.get('Cache-Control')).toBe('no-store');
    expect(html).toContain('peekVerification'); expect(html).toContain('parcel_lookup'); expect(html).toContain('"language":"fr"');
    expect(html).not.toContain('synthetic-secret');
    vi.stubEnv('TURNSTILE_SITE_KEY', ''); vi.stubEnv('TURNSTILE_SECRET_KEY', ''); vi.stubEnv('TURNSTILE_HOSTNAMES', '');
    expect((await page(new NextRequest('https://peek.example/api/public/native/turnstile'), { params: Promise.resolve({}) })).status).toBe(503);
  });
});

describe('signed-in requests', () => {
  const userId = '55000000-0000-4000-a000-000000000001';
  const parcelId = '55000000-0000-4000-a000-000000000002';
  const addition = Buffer.from('{"trackingNumber":"FR0000000099","carrier":"amazon-shipping"}');
  const appAgent = 'PeekDeliveryTracker/42 CFNetwork/3860.500.111 Darwin/25.5.0';
  const none = new Uint8Array();
  const routeContext = { params: Promise.resolve({}) };
  let lines: string[];
  const account = (path: string, body: Buffer | null, headers: Record<string, string> = {}) => new NextRequest(`https://peek.example${path}`, {
    method: 'POST', body: body?.toString(),
    headers: { 'Content-Type': 'application/json', host: 'peek.example', 'x-real-ip': '192.0.2.10', Authorization: 'Bearer synthetic-token', ...headers },
  });
  const checks = () => lines.map((line) => JSON.parse(line) as Record<string, unknown>)
    .filter((line) => line.event === 'native_verification').map(({ kind, outcome, reason, mode }) => ({ kind, outcome, reason, mode }));
  let claim: ReturnType<typeof vi.spyOn>;
  beforeEach(() => {
    vi.stubEnv('SUPABASE_PUBLISHABLE_KEY', 'test-public');
    vi.spyOn(SupabaseAuthenticator.prototype, 'validate').mockResolvedValue({ id: userId, email: null, authenticatedAt: null, sessionId: null });
    // The account's own allowance answers each request that gets past verification.
    claim = vi.spyOn(SupabaseServiceClient.prototype, 'claimAccountTracking').mockResolvedValue({ allowed: false, scope: 'account', overallUsed: 0 });
    lines = [];
    vi.spyOn(console, 'log').mockImplementation((line: string) => { lines.push(line); });
  });

  it('reads an unknown mode as log', () => {
    expect(accountAttestMode({})).toBe('log');
    expect(accountAttestMode({ NATIVE_ATTEST_ACCOUNT_MODE: ' Enforce ' })).toBe('enforce');
    expect(accountAttestMode({ NATIVE_ATTEST_ACCOUNT_MODE: 'off' })).toBe('off');
    expect(accountAttestMode({ NATIVE_ATTEST_ACCOUNT_MODE: 'strict' })).toBe('log');
  });

  it('checks assertions in log mode without refusing anyone or spending a device allowance', async () => {
    const valid = await signed(account('/api/packages', addition, { 'User-Agent': appAgent }), addition);
    expect((await addParcel(valid, routeContext)).status).toBe(429);
    expect(service.request).toHaveBeenCalledWith('/rest/v1/rpc/accept_native_assertion', expect.objectContaining({ body: expect.objectContaining({
      p_key_id: keyID, p_counter: 1, p_kind: 'lookup', p_limit: 0,
    }) }));
    const tampered = await signed(account('/api/packages', addition, { 'User-Agent': appAgent }), Buffer.from('{}'));
    expect((await addParcel(tampered, routeContext)).status).toBe(429);
    expect((await addParcel(account('/api/packages', addition, { 'User-Agent': appAgent }), routeContext)).status).toBe(429);
    expect((await addParcel(account('/api/packages', addition, { 'User-Agent': appAgent, 'X-Native-Verification': '1' }), routeContext)).status).toBe(429);
    expect((await addParcel(account('/api/packages', addition), routeContext)).status).toBe(429);
    vi.mocked(service.request).mockRejectedValueOnce(new SupabaseError('database unavailable'));
    expect((await addParcel(await signed(account('/api/packages', addition, { 'User-Agent': appAgent }), addition), routeContext)).status).toBe(429);
    expect(claim).toHaveBeenCalledTimes(6);
    expect(checks()).toEqual([
      { kind: 'account_lookup', outcome: 'verified', reason: undefined, mode: 'log' },
      { kind: 'account_lookup', outcome: 'invalid', reason: 'signature', mode: 'log' },
      { kind: 'account_lookup', outcome: 'missing', reason: 'legacy', mode: 'log' },
      { kind: 'account_lookup', outcome: 'missing', reason: 'unsigned', mode: 'log' },
      { kind: 'account_lookup', outcome: 'invalid', reason: 'unavailable', mode: 'log' },
    ]);
    expect(lines.join('\n')).not.toContain(keyID);
  });

  it('refuses the app without a valid assertion in enforce mode, before its allowance, and never a browser', async () => {
    vi.stubEnv('NATIVE_ATTEST_ACCOUNT_MODE', 'enforce');
    for (const refused of [
      account('/api/packages', addition, { 'User-Agent': appAgent, 'X-Native-Verification': '1' }),
      await signed(account('/api/packages', addition, { 'User-Agent': appAgent }), Buffer.from('{}')),
    ]) {
      const response = await addParcel(refused, routeContext);
      expect(response.status).toBe(403);
      expect(response.headers.get('X-Lookup-Verification')).toBe('required');
    }
    vi.mocked(service.request).mockResolvedValueOnce({ public_key: storedKey, app_id: appID, sign_count: 0 }).mockResolvedValueOnce('rejected');
    expect((await addParcel(await signed(account('/api/packages', addition, { 'User-Agent': appAgent }), addition), routeContext)).status).toBe(403);
    const refusedRefresh = await refreshAll(account('/api/sync', null, { 'User-Agent': appAgent }), routeContext);
    expect(refusedRefresh.status).toBe(403);
    expect(claim).not.toHaveBeenCalled();
    expect((await addParcel(await signed(account('/api/packages', addition, { 'User-Agent': appAgent }), addition), routeContext)).status).toBe(429);
    // Browsers never sign, and a broken assertion from one is counted, not refused.
    expect((await addParcel(account('/api/packages', addition), routeContext)).status).toBe(429);
    expect((await addParcel(await signed(account('/api/packages', addition), Buffer.from('{}')), routeContext)).status).toBe(429);
    // Without App Attest configured, the app cannot sign, so nothing is enforced.
    vi.stubEnv('APP_ATTEST_APP_ID', '');
    expect((await addParcel(account('/api/packages', addition, { 'User-Agent': appAgent }), routeContext)).status).toBe(429);
    expect(claim).toHaveBeenCalledTimes(4);
    expect(checks().map(({ outcome, reason }) => [outcome, reason])).toEqual([
      ['missing', 'unsigned'], ['invalid', 'signature'], ['invalid', 'replayed'], ['missing', 'legacy'], ['verified', undefined],
      ['invalid', 'signature'], ['missing', 'legacy'],
    ]);
  });

  it('checks nothing when off', async () => {
    vi.stubEnv('NATIVE_ATTEST_ACCOUNT_MODE', 'off');
    expect((await addParcel(await signed(account('/api/packages', addition, { 'User-Agent': appAgent }), Buffer.from('{}')), routeContext)).status).toBe(429);
    expect((await addParcel(account('/api/packages', addition, { 'User-Agent': appAgent }), routeContext)).status).toBe(429);
    expect(service.request).not.toHaveBeenCalled();
    expect(checks()).toEqual([]);
  });

  it('checks refreshes over an empty body, and detections when carriers are to be asked', async () => {
    vi.spyOn(SupabaseUserClient.prototype, 'listActivePackages').mockResolvedValue([]);
    vi.spyOn(SupabaseUserClient.prototype, 'getPackage').mockResolvedValue(null);
    vi.spyOn(SupabaseServiceClient.prototype, 'pendingSyncJobCount').mockResolvedValue(0);
    expect((await refreshAll(await signed(account('/api/sync', null, { 'User-Agent': appAgent }), none), routeContext)).status).toBe(202);
    const one = await signed(account(`/api/packages/${parcelId}/sync`, null, { 'User-Agent': appAgent }), none);
    expect((await refreshParcel(one, { params: Promise.resolve({ id: parcelId }) })).status).toBe(404);
    const detection = await signed(account('/api/carriers/detect', addition, { 'User-Agent': appAgent }), addition);
    expect((await detectForAccount(detection, routeContext)).status).toBe(429);
    expect(checks()).toEqual([
      { kind: 'account_refresh', outcome: 'verified', reason: undefined, mode: 'log' },
      { kind: 'account_refresh', outcome: 'verified', reason: undefined, mode: 'log' },
      { kind: 'account_detection', outcome: 'verified', reason: undefined, mode: 'log' },
    ]);
  });
});
