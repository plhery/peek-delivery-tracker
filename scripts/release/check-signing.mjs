import { execFileSync } from 'node:child_process';
import { mkdtempSync, readdirSync, rmSync } from 'node:fs';
import { tmpdir } from 'node:os';
import { join, resolve } from 'node:path';

const directory = process.env.PEEK_ARTIFACT_DIR || 'desktop/dist';

if (process.env.PEEK_PUBLISH !== 'true') {
  console.log('UNSIGNED validation artifacts: no public publication is permitted');
} else if (process.argv[2] === 'credentials') {
  const required = process.platform === 'darwin'
    ? ['CSC_LINK', 'CSC_KEY_PASSWORD', 'APPLE_API_KEY', 'APPLE_API_KEY_ID', 'APPLE_API_ISSUER']
    : process.platform === 'win32' ? ['WIN_CSC_LINK', 'WIN_CSC_KEY_PASSWORD'] : [];
  for (const key of required) if (!process.env[key]?.trim()) throw new Error(`Public release requires ${key}`);
} else if (process.platform === 'darwin') {
  const installers = readdirSync(directory).filter((name) => name.endsWith('.dmg'));
  if (installers.length !== 1) throw new Error('Expected exactly one macOS disk image');
  const mount = mkdtempSync(join(tmpdir(), 'peek-signing-'));
  let mounted = false;
  try {
    execFileSync('hdiutil', ['attach', '-readonly', '-nobrowse', '-mountpoint', mount, resolve(directory, installers[0])], { stdio: 'inherit' });
    mounted = true;
    const app = readdirSync(mount).find((name) => name.endsWith('.app'));
    if (!app) throw new Error('Disk image contains no application');
    const path = join(mount, app);
    execFileSync('codesign', ['--verify', '--deep', '--strict', '--verbose=2', path], { stdio: 'inherit' });
    execFileSync('spctl', ['--assess', '--type', 'execute', '--verbose=2', path], { stdio: 'inherit' });
    execFileSync('xcrun', ['stapler', 'validate', path], { stdio: 'inherit' });
  } finally {
    if (mounted) execFileSync('hdiutil', ['detach', mount], { stdio: 'inherit' });
    rmSync(mount, { recursive: true, force: true });
  }
} else if (process.platform === 'win32') {
  const installers = readdirSync(directory).filter((name) => name.endsWith('.exe'));
  if (installers.length !== 1) throw new Error('Expected exactly one Windows installer');
  // Pass the path as an argument; never interpolate it into PowerShell source.
  execFileSync('powershell', ['-NoProfile', '-NonInteractive', '-Command', '& { param($p) if ((Get-AuthenticodeSignature -LiteralPath $p).Status -ne "Valid") { throw "Invalid Authenticode signature" } }', resolve(directory, installers[0])], { stdio: 'inherit' });
}
