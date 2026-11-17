import { createHash, generateKeyPairSync, X509Certificate } from 'node:crypto';
import { readFileSync } from 'node:fs';
import { afterEach, beforeEach, describe, expect, it, vi } from 'vitest';
import { attestationChain, attestationRoot, keyDescription, PLAY_SIGNER, type ChainOptions } from '../test/androidAttestation';
import * as roots from './androidAttestationRoots';
import {
  ANDROID_PACKAGE, ANDROID_SIGNERS, forgetRevocationList, parseKeyDescription, revokedSerials, serialKey, verifyAndroidKeyAttestation,
} from './androidKeyAttestation';

vi.mock('./androidAttestationRoots', () => ({ ANDROID_ATTESTATION_ROOTS: [] as string[], SELF_SIGNED_BOOT_KEYS: new Set<string>() }));

const root = attestationRoot();
const challenge = createHash('sha256').update('synthetic challenge').digest();
const policy = { packageName: ANDROID_PACKAGE, signers: new Set(ANDROID_SIGNERS) };
const noneRevoked = async () => new Set<string>();
const chain = (options: Partial<ChainOptions> = {}) => attestationChain({ root, challenge, ...options }).x5c;
const verify = (x5c: unknown, revoked = noneRevoked) => verifyAndroidKeyAttestation(x5c, challenge, policy, revoked);

beforeEach(() => {
  (roots.ANDROID_ATTESTATION_ROOTS as string[]).splice(0, Infinity, root.pem);
  (roots.SELF_SIGNED_BOOT_KEYS as Set<string>).clear();
  forgetRevocationList();
});
afterEach(() => { vi.unstubAllGlobals(); vi.useRealTimers(); });

describe('Android key attestation', () => {
  it('accepts a key made in the TEE or StrongBox of a locked, verified phone, for Peek, and gives its public key', async () => {
    const { x5c, privateKey } = attestationChain({ root, challenge });
    const { publicKey } = await verify(x5c);
    expect(publicKey).toBe(new X509Certificate(x5c[0]!).publicKey.export({ type: 'spki', format: 'pem' }));
    expect(publicKey).not.toBe(privateKey.export({ type: 'pkcs8', format: 'pem' }));
    await expect(verify(chain({ securityLevel: 2, keyMintSecurityLevel: 2 }))).resolves.toBeDefined();
    // Older versions without the boot hash, and the application ID in the hardware's list.
    await expect(verify(chain({ version: 3 }))).resolves.toBeDefined();
    await expect(verify(chain({ applicationIdIn: 'hardware' }))).resolves.toBeDefined();
  });

  it.each(ANDROID_SIGNERS)('accepts the build signed with %s', async (signer) => {
    await expect(verify(chain({ signers: ['00'.repeat(32), signer] }))).resolves.toBeDefined();
  });

  it('accepts a self-signed system only with a verified boot key it knows, as GrapheneOS reports', async () => {
    const bootKey = Buffer.alloc(32, 0x5a);
    await expect(verify(chain({ bootState: 1, bootKey }))).rejects.toMatchObject({ reason: 'boot_state' });
    (roots.SELF_SIGNED_BOOT_KEYS as Set<string>).add(bootKey.toString('hex'));
    await expect(verify(chain({ bootState: 1, bootKey }))).resolves.toBeDefined();
    // Still locked only.
    await expect(verify(chain({ bootState: 1, bootKey, locked: false }))).rejects.toMatchObject({ reason: 'unlocked' });
  });

  it.each<[string, Partial<ChainOptions>, string]>([
    ['software attestation', { securityLevel: 0 }, 'security_level'],
    ['a software key', { keyMintSecurityLevel: 0 }, 'security_level'],
    ['another challenge', { challenge: createHash('sha256').update('another').digest() }, 'nonce'],
    ['an unlocked bootloader', { locked: false, bootState: 2 }, 'unlocked'],
    ['an unverified system', { bootState: 2 }, 'boot_state'],
    ['a failed boot', { bootState: 3 }, 'boot_state'],
    ['no root of trust', { rootOfTrust: false }, 'boot_state'],
    ['an imported key', { origin: 2 }, 'origin'],
    ['no origin', { origin: null }, 'origin'],
    ['an RSA key', { algorithm: 1 }, 'key'],
    ['another curve', { curve: 2 }, 'key'],
    ['another app', { packages: ['com.example.other'] }, 'app_id'],
    ['a shared user ID', { packages: [ANDROID_PACKAGE, 'com.example.other'] }, 'app_id'],
    ['no application ID', { applicationIdIn: 'none' }, 'app_id'],
    ['another signer', { signers: ['ab'.repeat(32)] }, 'signer'],
    ['a key description above the leaf', { describedIntermediate: true }, 'chain'],
    ['an expired intermediate', { intermediateNotAfter: new Date('2021-01-01T00:00:00Z') }, 'certificate_time'],
  ])('refuses %s as %s', async (_name, options, reason) => {
    await expect(verify(chain(options))).rejects.toMatchObject({ reason });
  });

  it('refuses a P-384 leaf that says it is P-256', async () => {
    const leafKey = generateKeyPairSync('ec', { namedCurve: 'secp384r1' });
    await expect(verify(chain({ leafKey }))).rejects.toMatchObject({ reason: 'key' });
  });

  it('refuses chains that are not Google\'s, not whole or not signed in order', async () => {
    const other = attestationRoot();
    await expect(verify(attestationChain({ root: other, challenge }).x5c)).rejects.toMatchObject({ reason: 'root' });
    const [leaf, intermediate] = chain();
    const [, foreign] = chain();
    await expect(verify([leaf, foreign, root.der])).rejects.toMatchObject({ reason: 'chain' });
    await expect(verify([leaf, Buffer.from('not a certificate'), root.der])).rejects.toMatchObject({ reason: 'chain' });
    await expect(verify([leaf, intermediate, intermediate])).rejects.toMatchObject({ reason: 'chain' });
    for (const malformed of [undefined, 'x5c', [leaf], [leaf, intermediate], Array(9).fill(leaf), [leaf, 'text', root.der], [leaf, Buffer.alloc(9_000), root.der]]) {
      await expect(verify(malformed)).rejects.toMatchObject({ reason: 'malformed' });
    }
  });

  it('refuses a leaf without a key description', async () => {
    await expect(verify(chain({ plainLeaf: true }))).rejects.toMatchObject({ reason: 'malformed' });
  });

  it('leaves the leaf\'s dates, which the phone sets, and keeps trusting an expired chain keyed in the factory', async () => {
    const expired = new Date('2021-01-01T00:00:00Z');
    await expect(verify(chain({ leafNotAfter: expired }))).resolves.toBeDefined();
    await expect(verify(chain({ factory: true, intermediateNotAfter: expired }))).resolves.toBeDefined();
  });

  it('refuses a certificate on the revocation list, whose serial numbers it reads as Google writes them', async () => {
    const x5c = chain({ intermediateSerial: 0x0f1e2d3cn });
    await expect(verify(x5c, async () => new Set(['f1e2d3c']))).rejects.toMatchObject({ reason: 'revoked' });
    await expect(verify(x5c, async () => { throw new Error('offline'); })).rejects.toMatchObject({ reason: 'revocation_unavailable' });
    expect(serialKey('000F1E2D3C')).toBe('f1e2d3c');
    expect(serialKey('00')).toBe('0');
  });

  it('reads DER strictly', () => {
    const description = keyDescription({ challenge });
    expect(parseKeyDescription(description).challenge).toEqual(challenge);
    expect(() => parseKeyDescription(Buffer.concat([description, Buffer.from([0])]))).toThrow('malformed');
    expect(() => parseKeyDescription(description.subarray(0, -1))).toThrow('malformed');
    // An indefinite length, which DER never has.
    expect(() => parseKeyDescription(Buffer.from([0x30, 0x80, 0x00, 0x00]))).toThrow('malformed');
    expect(() => parseKeyDescription(Buffer.from([0x30, 0x00]))).toThrow('malformed');
  });
});

describe('Google\'s roots', () => {
  it('are self-signed certificates whose keys Google publishes, none of them software attestation\'s', async () => {
    const actual = await vi.importActual<typeof roots>('./androidAttestationRoots');
    // The SHA-256 fingerprints Google's page gives for its RSA and ECDSA roots.
    expect(actual.ANDROID_ATTESTATION_ROOTS.map((pem) => new X509Certificate(pem).fingerprint256)).toEqual([
      'CE:DB:1C:B6:DC:89:6A:E5:EC:79:73:48:BC:E9:28:67:53:C2:B3:8E:E7:1C:E0:FB:E3:4A:9A:12:48:80:0D:FC',
      '6D:9D:B4:CE:6C:5C:0B:29:31:66:D0:89:86:E0:57:74:A8:77:6C:EB:52:5D:9E:43:29:52:0D:E1:2B:A4:BC:C0',
    ]);
    for (const pem of actual.ANDROID_ATTESTATION_ROOTS) {
      const certificate = new X509Certificate(pem);
      expect(certificate.verify(certificate.publicKey)).toBe(true);
      expect(certificate.ca).toBe(true);
      expect(certificate.subject).not.toContain('Software Attestation');
    }
    for (const key of actual.SELF_SIGNED_BOOT_KEYS) expect(key).toMatch(/^[0-9a-f]{64}$/);
  });
});

describe('real attestations, from Google\'s verifier\'s test data', () => {
  const fixture = (name: string) => readFileSync(new URL(`./fixtures/android-attestation/${name}.pem`, import.meta.url), 'utf8')
    .match(/-----BEGIN CERTIFICATE-----[^-]+-----END CERTIFICATE-----/g)!
    .map((pem) => new X509Certificate(pem).raw);
  // Their test app, its signing certificate, and the challenges they were made with.
  const tester = { packageName: 'com.google.android.attestation', signers: new Set(['103938ee4537e59e8ee792f654504fb8346fc6b346d0bbc4415fc339fcfc8ec1']) };
  const check = (name: string, challenge: string, at: string, policy = tester) =>
    verifyAndroidKeyAttestation(fixture(name), Buffer.from(challenge), policy, noneRevoked, Date.parse(at));

  beforeEach(async () => {
    const actual = await vi.importActual<typeof roots>('./androidAttestationRoots');
    (roots.ANDROID_ATTESTATION_ROOTS as string[]).splice(0, Infinity, ...actual.ANDROID_ATTESTATION_ROOTS);
  });

  it('accepts a Pixel 9 Pro\'s TEE and StrongBox keys, provisioned remotely under the RSA root', async () => {
    const { publicKey } = await check('pixel-9-pro-tee-rkp', 'd688d763-6118-4ca6-94b2-e6cd9ed7e4e4', '2025-09-30T00:00:00Z');
    expect(publicKey).toContain('BEGIN PUBLIC KEY');
    await expect(check('pixel-9-pro-strongbox-rkp', '7ccac1ea-4845-482e-858d-f6fa9aa8c295', '2025-09-30T00:00:00Z')).resolves.toBeDefined();
  });

  it('accepts a key under the ECDSA root', async () => {
    await expect(check('frankel-tee-ecdsa-root', '146aaf16-7814-4e44-acdb-e5d652a7b602', '2026-11-17T00:00:00Z')).resolves.toBeDefined();
  });

  it('refuses a remotely provisioned chain once its short-lived certificates expire, another app and another challenge', async () => {
    await expect(check('pixel-9-pro-tee-rkp', 'd688d763-6118-4ca6-94b2-e6cd9ed7e4e4', '2026-11-17T00:00:00Z')).rejects.toMatchObject({ reason: 'certificate_time' });
    await expect(check('frankel-tee-ecdsa-root', '146aaf16-7814-4e44-acdb-e5d652a7b602', '2026-11-17T00:00:00Z', policy))
      .rejects.toMatchObject({ reason: 'app_id' });
    await expect(check('frankel-tee-ecdsa-root', 'another', '2026-11-17T00:00:00Z')).rejects.toMatchObject({ reason: 'nonce' });
  });

  it('refuses software attestation, an emulator\'s', async () => {
    await expect(check('software-attestation', 'challenge', '2026-11-17T00:00:00Z')).rejects.toMatchObject({ reason: 'root' });
  });

  it('reads a factory chain whose root certificate has expired, and refuses its unlocked bootloader', async () => {
    await expect(check('pixel-3-unlocked-factory', 'challenge', '2026-11-17T00:00:00Z')).rejects.toMatchObject({ reason: 'unlocked' });
  });
});

describe('the revocation list', () => {
  const listed = (entries: Record<string, unknown>) => vi.fn().mockResolvedValue(Response.json({ entries }));

  it('is read once, kept an hour, read again in the background for a day, then waited for', async () => {
    vi.useFakeTimers({ now: new Date('2026-11-17T12:00:00Z') });
    const fetch = listed({ '0F1E2D3C': { status: 'REVOKED', reason: 'KEY_COMPROMISE' }, abc: { status: 'SUSPENDED' } });
    vi.stubGlobal('fetch', fetch);
    expect([...await revokedSerials()]).toEqual(['f1e2d3c', 'abc']);
    expect(fetch).toHaveBeenCalledWith('https://android.googleapis.com/attestation/status', expect.objectContaining({ cache: 'no-store' }));
    vi.advanceTimersByTime(59 * 60_000);
    await revokedSerials();
    expect(fetch).toHaveBeenCalledTimes(1);
    fetch.mockResolvedValue(Response.json({ entries: { def: { status: 'REVOKED' } } }));
    vi.advanceTimersByTime(2 * 60_000);
    // Past the hour, the old copy answers while a new one is read.
    expect([...await revokedSerials()]).toEqual(['f1e2d3c', 'abc']);
    await vi.waitFor(() => expect(fetch).toHaveBeenCalledTimes(2));
    await vi.waitFor(async () => expect([...await revokedSerials()]).toEqual(['def']));
  });

  it('fails without a usable copy, and past a day without a fresh one', async () => {
    vi.useFakeTimers({ now: new Date('2026-11-17T12:00:00Z') });
    vi.stubGlobal('fetch', vi.fn().mockResolvedValue(new Response('unavailable', { status: 503 })));
    await expect(revokedSerials()).rejects.toThrow('503');
    vi.stubGlobal('fetch', vi.fn().mockResolvedValue(Response.json({ entries: [] })));
    await expect(revokedSerials()).rejects.toThrow('malformed');
    vi.stubGlobal('fetch', listed({}));
    await revokedSerials();
    vi.advanceTimersByTime(25 * 60 * 60_000);
    vi.stubGlobal('fetch', vi.fn().mockRejectedValue(new Error('offline')));
    await expect(revokedSerials()).rejects.toThrow('offline');
  });
});

it('keeps the synthetic Play signer among the app\'s own', () => {
  expect(ANDROID_SIGNERS).toContain(PLAY_SIGNER);
});
