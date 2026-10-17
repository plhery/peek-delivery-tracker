import { test } from 'node:test';
import assert from 'node:assert/strict';
import { generateKeyPairSync } from 'node:crypto';
import { mkdir, mkdtemp, rm, symlink, writeFile } from 'node:fs/promises';
import { tmpdir } from 'node:os';
import { join } from 'node:path';
import { packageIdentity, verifyApkKeys, buildPackage, validatePayload } from './server-package.mjs';

const sha = 'a'.repeat(40);
test('native identities, file names and distro versions preserve source and channel', () => {
  const nightly = packageIdentity({ version: '1.2.3-nightly.41.gaaaaaaaaaaaa', channel: 'nightly', sha });
  assert.equal(nightly.name, 'peek-server-nightly');
  assert.equal(nightly.apkVersion, '1.2.3_pre41');
  assert.equal(nightly.debVersion, '1.2.3~nightly.41.gaaaaaaaaaaaa');
  assert.equal(nightly.apkFilename, 'peek-server-nightly-1.2.3-nightly.41.gaaaaaaaaaaaa-linux-x86_64.apk');
  assert.equal(nightly.debFilename, 'peek-server-nightly-1.2.3-nightly.41.gaaaaaaaaaaaa-linux-amd64.deb');
  assert.equal(packageIdentity({ version: '1.2.3', channel: 'stable', sha }).name, 'peek-server');
  assert.throws(() => packageIdentity({ version: nightly.version, channel: 'stable', sha }));
  assert.throws(() => packageIdentity({ version: nightly.version, channel: 'nightly', sha: 'b'.repeat(40) }));
});
test('APK signing requires matching RSA private/public keys', async () => {
  const directory = await mkdtemp(join(tmpdir(), 'peek-apk-keys-'));
  try {
    const pair = generateKeyPairSync('rsa', { modulusLength: 2048 });
    const other = generateKeyPairSync('rsa', { modulusLength: 2048 });
    const privatePath = join(directory, 'private');
    const publicPath = join(directory, 'public');
    await writeFile(privatePath, pair.privateKey.export({ type: 'pkcs8', format: 'pem' }), { mode: 0o600 });
    await writeFile(publicPath, pair.publicKey.export({ type: 'spki', format: 'pem' }));
    assert.match(await verifyApkKeys(privatePath, publicPath), /^-----BEGIN PUBLIC KEY-----/);
    await assert.rejects(verifyApkKeys(privatePath, privatePath), /only a public key/);
    await writeFile(publicPath, other.publicKey.export({ type: 'spki', format: 'pem' }));
    await assert.rejects(verifyApkKeys(privatePath, publicPath), /do not match/);
  } finally { await rm(directory, { recursive: true, force: true }); }
});
test('unsupported native architectures fail before building', async () => {
  await assert.rejects(buildPackage({ format: 'deb', arch: 'arm64' }), /Only amd64/);
});

test('standalone validation rejects configuration leaks, foreign binaries and escaping symlinks', async () => {
  const directory = await mkdtemp(join(tmpdir(), 'peek-native-payload-'));
  const outside = await mkdtemp(join(tmpdir(), 'peek-native-outside-'));
  try {
    for (const path of ['.next/static', 'public', 'node_modules/sharp', 'node_modules/playwright-core', 'node_modules/onnxruntime-web/dist']) await mkdir(join(directory, path), { recursive: true });
    for (const path of ['server.js', '.next/BUILD_ID', 'node_modules/playwright-core/browsers.json', 'node_modules/onnxruntime-web/dist/ort-wasm-simd-threaded.wasm']) await writeFile(join(directory, path), 'synthetic fixture');
    await validatePayload(directory);
    await writeFile(join(directory, '.env'), 'SYNTHETIC_SECRET=fixture');
    await assert.rejects(validatePayload(directory), /local configuration/);
    await rm(join(directory, '.env'));
    await writeFile(join(directory, 'node_modules/sharp/foreign.node'), 'foreign native binary');
    await assert.rejects(validatePayload(directory), /wrong platform/);
    await rm(join(directory, 'node_modules/sharp/foreign.node'));
    await symlink(outside, join(directory, 'escaping'));
    await assert.rejects(validatePayload(directory), /escapes its payload/);
  } finally {
    await rm(directory, { recursive: true, force: true });
    await rm(outside, { recursive: true, force: true });
  }
});
