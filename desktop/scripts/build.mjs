import { build } from 'esbuild';
import { cp, mkdir, rm, writeFile } from 'node:fs/promises';
import { execFileSync } from 'node:child_process';
import { releaseSchema, nativeVersions, channelIdentity } from '../src/release.ts';

const supplied = ['PEEK_RELEASE_VERSION', 'PEEK_RELEASE_CHANNEL', 'PEEK_RELEASE_SHA', 'PEEK_BUILD_NUMBER'].some((key) => process.env[key]);
const sha = supplied ? process.env.PEEK_RELEASE_SHA : execFileSync('git', ['rev-parse', 'HEAD'], { encoding: 'utf8' }).trim();
const release = releaseSchema.parse(supplied ? {
  version: process.env.PEEK_RELEASE_VERSION,
  channel: process.env.PEEK_RELEASE_CHANNEL,
  sha,
  buildNumber: Number(process.env.PEEK_BUILD_NUMBER),
} : { version: `1.0.0-nightly.1.g${sha.slice(0, 12)}`, channel: 'nightly', sha, buildNumber: 1 });
await rm('build', { recursive: true, force: true });
await mkdir('build', { recursive: true });
await writeFile('build/release.json', JSON.stringify({ release, native: nativeVersions(release), identity: channelIdentity(release.channel) }, null, 2));
await build({ entryPoints: ['src/main.ts', 'src/preload.ts'], outdir: 'build', outExtension: { '.js': '.cjs' }, bundle: true, platform: 'node', format: 'cjs', external: ['electron'], define: { __PEEK_RELEASE__: JSON.stringify(release) } });
await build({ entryPoints: ['src/renderer.ts'], outfile: 'build/renderer.js', bundle: true, platform: 'browser', target: 'chrome130' });
await cp('ui', 'build/ui', { recursive: true });
