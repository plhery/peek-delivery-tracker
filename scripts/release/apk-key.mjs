import { createPrivateKey, createPublicKey, generateKeyPairSync, timingSafeEqual } from 'node:crypto';
import { mkdirSync, rmSync, writeFileSync } from 'node:fs';
import { join, resolve } from 'node:path';
import { pathToFileURL } from 'node:url';

export function apkKeys(publish, environment) {
  if (!publish) return generateKeyPairSync('rsa', {
    modulusLength: 3072,
    privateKeyEncoding: { type: 'pkcs1', format: 'pem' },
    publicKeyEncoding: { type: 'spki', format: 'pem' },
  });
  const privatePem = environment.APK_SIGNING_KEY_CONTENT;
  const publicPem = environment.APK_SIGNING_PUBLIC_KEY_CONTENT;
  if (!privatePem || !publicPem) throw new Error('Public APK publication requires owner private and public signing keys');
  if (!/^-----BEGIN (?:RSA )?PUBLIC KEY-----/.test(publicPem.trimStart()) || publicPem.includes('PRIVATE KEY')) {
    throw new Error('APK public signing key must contain only a public PEM key');
  }
  const privateKey = createPrivateKey(privatePem);
  const publicKey = createPublicKey(publicPem);
  if (privateKey.asymmetricKeyType !== 'rsa' || publicKey.asymmetricKeyType !== 'rsa') throw new Error('APK signing keys must be RSA');
  const actual = createPublicKey(privateKey).export({ type: 'spki', format: 'der' });
  const expected = publicKey.export({ type: 'spki', format: 'der' });
  if (actual.length !== expected.length || !timingSafeEqual(actual, expected)) throw new Error('APK private/public signing keys do not match');
  return { privateKey: privateKey.export({ type: 'pkcs1', format: 'pem' }), publicKey: publicKey.export({ type: 'spki', format: 'pem' }) };
}

if (process.argv[1] && import.meta.url === pathToFileURL(resolve(process.argv[1])).href) {
  const temporary = process.env.RUNNER_TEMP;
  if (!temporary) throw new Error('RUNNER_TEMP is required for isolated APK signing keys');
  const directory = join(temporary, 'peek-apk-keys');
  if (process.argv[2] === 'clean') {
    rmSync(directory, { recursive: true, force: true });
  } else {
    const keys = apkKeys(process.env.PEEK_PUBLISH === 'true', process.env);
    mkdirSync(directory, { recursive: true, mode: 0o700 });
    writeFileSync(join(directory, 'peek-server-apk.rsa'), keys.privateKey, { mode: 0o600 });
    writeFileSync(join(directory, 'peek-server-apk.rsa.pub'), keys.publicKey, { mode: 0o644 });
    console.log(process.env.PEEK_PUBLISH === 'true' ? 'Owner APK signing key verified' : 'Ephemeral APK key for VALIDATION ONLY; not a trusted public release key');
  }
}
