import assert from 'node:assert/strict';
import { createPrivateKey, createPublicKey, generateKeyPairSync } from 'node:crypto';
import { test } from 'node:test';
import { apkKeys } from './apk-key.mjs';

const fixture = generateKeyPairSync('rsa', {
  modulusLength: 2048,
  privateKeyEncoding: { type: 'pkcs8', format: 'pem' },
  publicKeyEncoding: { type: 'spki', format: 'pem' },
});

test('publication requires an owner RSA key pair and normalizes private format for APK signing', () => {
  assert.throws(() => apkKeys(true, {}), /requires owner/);
  assert.throws(() => apkKeys(true, { APK_SIGNING_KEY_CONTENT: fixture.privateKey, APK_SIGNING_PUBLIC_KEY_CONTENT: fixture.privateKey }), /only a public/);
  const result = apkKeys(true, { APK_SIGNING_KEY_CONTENT: fixture.privateKey, APK_SIGNING_PUBLIC_KEY_CONTENT: fixture.publicKey });
  assert.match(result.privateKey, /^-----BEGIN RSA PRIVATE KEY-----/);
  assert.equal(result.publicKey, fixture.publicKey);
  assert.deepEqual(createPublicKey(createPrivateKey(result.privateKey)).export({ type: 'spki', format: 'der' }), createPublicKey(fixture.publicKey).export({ type: 'spki', format: 'der' }));
});

test('an unrelated public key cannot be paired with the owner private key', () => {
  const other = generateKeyPairSync('rsa', { modulusLength: 2048 });
  assert.throws(() => apkKeys(true, { APK_SIGNING_KEY_CONTENT: fixture.privateKey, APK_SIGNING_PUBLIC_KEY_CONTENT: other.publicKey.export({ type: 'spki', format: 'pem' }) }), /do not match/);
});

test('validation generates a separate ephemeral key rather than consuming owner credentials', () => {
  const keys = apkKeys(false, { APK_SIGNING_KEY_CONTENT: 'must-not-be-read', APK_SIGNING_PUBLIC_KEY_CONTENT: 'must-not-be-read' });
  assert.notEqual(keys.publicKey, fixture.publicKey);
  assert.deepEqual(createPublicKey(createPrivateKey(keys.privateKey)).export({ type: 'spki', format: 'der' }), createPublicKey(keys.publicKey).export({ type: 'spki', format: 'der' }));
});
