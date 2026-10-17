import { access } from 'node:fs/promises';
import { constants } from 'node:fs';
import { pathToFileURL } from 'node:url';

const name = '@PACKAGE_NAME@';
const base = `/usr/lib/${name}`;
const configPath = `/etc/${name}/server.env`;
async function start() {
  if (process.argv.includes('--version')) {
    console.log(`${name} @RELEASE_VERSION@ (@SOURCE_SHA@)`);
    return;
  }
try {
  process.loadEnvFile(configPath);
  const url = (value, label) => {
    if (!value) throw new Error(`${label} is required.`);
    const parsed = new URL(value);
    if (!['https:', 'http:'].includes(parsed.protocol) || parsed.username || parsed.password || parsed.pathname !== '/' || parsed.search || parsed.hash) throw new Error(`${label} must be an HTTP(S) origin.`);
  };
  url(process.env.SUPABASE_URL, 'SUPABASE_URL');
  url(process.env.SUPABASE_PUBLIC_URL || process.env.NEXT_PUBLIC_SUPABASE_URL || process.env.SUPABASE_URL, 'SUPABASE_PUBLIC_URL');
  if (!(process.env.SUPABASE_PUBLISHABLE_KEY || process.env.SUPABASE_ANON_KEY)) throw new Error('SUPABASE_PUBLISHABLE_KEY is required.');
  if (!process.env.SUPABASE_SERVICE_ROLE_KEY) throw new Error('SUPABASE_SERVICE_ROLE_KEY is required.');
  if (!/^\d+$/.test(process.env.PORT ?? '') || Number(process.env.PORT) < 1 || Number(process.env.PORT) > 65535) throw new Error('PORT must be between 1 and 65535.');
  process.env.HOSTNAME ||= '127.0.0.1';
  process.env.NODE_ENV = 'production';
  process.env.NEXT_TELEMETRY_DISABLED = '1';
  process.env.HOME = `/var/lib/${name}`;
  process.env.XDG_CACHE_HOME = `/var/cache/${name}/browser`;
  process.env.TRACKING_CHROMIUM_PATH ||= '/usr/bin/chromium';
  await access(process.env.TRACKING_CHROMIUM_PATH, constants.X_OK);
  const [major, minor, patch] = process.versions.node.split('.').map(Number);
  if (major < 26 || (major === 26 && (minor < 5 || (minor === 5 && patch < 1)))) throw new Error('Node 26.5.1 or newer is required.');
  process.env.IMAGE_COMMIT = '@SOURCE_SHA@';
  await access(`${base}/app/server.js`, constants.R_OK);
  if (process.argv.includes('--check-environment')) {
    console.log(`${name} environment preflight passed.`);

  } else {
    await import(pathToFileURL(`${base}/app/server.js`).href);
  }
} catch (error) {
  console.error(`${name}: ${error instanceof Error ? error.message : 'Server startup failed.'}`);
  process.exitCode = 1;
}
}

await start();
