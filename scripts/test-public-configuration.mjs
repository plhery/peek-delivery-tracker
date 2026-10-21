import assert from 'node:assert/strict';
import { spawn } from 'node:child_process';
import { createHash } from 'node:crypto';
import { once } from 'node:events';
import { createServer } from 'node:http';
import { readFile, readdir } from 'node:fs/promises';
import { resolve } from 'node:path';
import { setTimeout as delay } from 'node:timers/promises';
import { fileURLToPath } from 'node:url';
import { z } from 'zod';

// Exercise one already-built standalone artifact twice. Only the child process
// gets synthetic settings; no source, bundle, .env file or parent env is changed.
const root = fileURLToPath(new URL('../', import.meta.url));
const standalone = resolve(root, process.argv[2] || '.next/standalone');
const serverPath = resolve(standalone, 'server.js');
assert.equal((await readdir(standalone)).some((name) => /^\.env(?:\.|$)/.test(name)), false, 'The standalone artifact must not contain instance environment files');
const fingerprint = async () => createHash('sha256').update(await readFile(serverPath)).update(await readFile(resolve(standalone, '.next/BUILD_ID'))).digest('hex');
const originalFingerprint = await fingerprint();
const secretCanary = 'synthetic-private-service-role-canary';

const backend = createServer((_request, response) => {
  response.writeHead(200, { 'Content-Type': 'application/json' });
  response.end('[]');
});
backend.listen(0, '127.0.0.1');
await once(backend, 'listening');
const backendAddress = backend.address();
assert.ok(backendAddress && typeof backendAddress !== 'string');

async function unusedPort() {
  const reservation = createServer();
  reservation.listen(0, '127.0.0.1');
  await once(reservation, 'listening');
  const address = reservation.address();
  assert.ok(address && typeof address !== 'string');
  await new Promise((resolve, reject) => reservation.close((error) => error ? reject(error) : resolve()));
  return address.port;
}

function flightData(html) {
  const chunks = [];
  for (const match of html.matchAll(/self\.__next_f\.push\((\[1,[\s\S]*?\])\)\s*<\/script>/g)) {
    chunks.push(z.tuple([z.literal(1), z.string()]).parse(JSON.parse(match[1]))[1]);
  }
  assert.ok(chunks.length, 'The page must carry its server-rendered client props');
  return chunks.join('');
}

try {
  for (const [name, googleEnabled, emailOtpEnabled, oidc] of [
    ['first', true, false, null],
    ['second', false, true, { provider: 'custom:second', name: 'Synthetic SSO' }],
  ]) {
    const port = await unusedPort();
    const publicUrl = `https://${name}.auth.example.invalid`;
    const publicKey = `sb_publishable_synthetic_${name}`;
    const iosAppUrl = `https://${name}.apps.example.invalid/peek`;
    const child = spawn(process.execPath, [serverPath], {
      cwd: standalone,
      env: {
        PATH: process.env.PATH,
        TMPDIR: process.env.TMPDIR,
        NODE_ENV: 'production', HOSTNAME: '127.0.0.1', PORT: String(port), NEXT_TELEMETRY_DISABLED: '1',
        SUPABASE_URL: `http://127.0.0.1:${backendAddress.port}`,
        SUPABASE_PUBLIC_URL: publicUrl, SUPABASE_PUBLISHABLE_KEY: publicKey, SUPABASE_SERVICE_ROLE_KEY: secretCanary,
        NEXT_PUBLIC_AUTH_GOOGLE_ENABLED: String(googleEnabled), NEXT_PUBLIC_AUTH_APPLE_ENABLED: 'false', NEXT_PUBLIC_AUTH_EMAIL_OTP_ENABLED: String(emailOtpEnabled),
        NEXT_PUBLIC_AUTH_OIDC_PROVIDER: oidc?.provider || '', NEXT_PUBLIC_AUTH_OIDC_NAME: oidc?.name || '',
        NEXT_PUBLIC_IOS_APP_URL: iosAppUrl,
      },
      stdio: ['ignore', 'pipe', 'pipe'],
    });
    let output = '';
    const remember = (chunk) => { output = (output + chunk).slice(-16_000); };
    child.stdout.on('data', remember);
    child.stderr.on('data', remember);
    const exited = once(child, 'exit');
    try {
      const deadline = Date.now() + 30_000;
      let html;
      while (Date.now() < deadline) {
        assert.equal(child.exitCode, null, `Standalone server exited early:\n${output}`);
        try {
          const response = await fetch(`http://127.0.0.1:${port}/`, { signal: AbortSignal.timeout(5_000) });
          assert.equal(response.status, 200, `Standalone page returned ${response.status}:\n${output}`);
          html = await response.text();
          break;
        } catch (error) {
          if (error instanceof assert.AssertionError) throw error;
          await delay(100);
        }
      }
      assert.ok(html, `Standalone server did not serve a page:\n${output}`);
      const expected = { auth: { url: publicUrl, publishableKey: publicKey, googleEnabled, appleEnabled: false, emailOtpEnabled, oidc }, iosAppUrl };
      assert.ok(flightData(html).includes(JSON.stringify(expected)), 'The page must initialize auth and links from this process’s runtime configuration');
      assert.ok(html.includes(publicUrl), 'Preconnect and client configuration must use the public auth origin');
      assert.ok(!html.includes(secretCanary), 'Private service credentials must not reach HTML or client props');
      if (name === 'second') assert.ok(!html.includes('first.auth.example.invalid'), 'A second process must not retain the first instance’s public endpoint');
      assert.equal(await fingerprint(), originalFingerprint, 'Runtime configuration must not change the built artifact');
    } finally {
      child.kill('SIGTERM');
      const forceStop = setTimeout(() => child.kill('SIGKILL'), 5_000);
      forceStop.unref();
      await exited;
      clearTimeout(forceStop);
    }
  }
  console.log('The same standalone artifact serves two runtime auth/link configurations without exposing server credentials.');
} finally {
  await new Promise((resolve, reject) => backend.close((error) => error ? reject(error) : resolve()));
}
