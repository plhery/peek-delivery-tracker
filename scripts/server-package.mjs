import { chmod, cp, mkdir, mkdtemp, readFile, readdir, realpath, rm, stat, symlink, writeFile } from 'node:fs/promises';
import { createHash, createPrivateKey, createPublicKey, timingSafeEqual } from 'node:crypto';
import { execFileSync } from 'node:child_process';
import { basename, dirname, join, relative, resolve, sep } from 'node:path';
import { pathToFileURL } from 'node:url';
import { tmpdir } from 'node:os';
import { parseArgs } from 'node:util';
import { z } from 'zod';

const root = resolve(import.meta.dirname, '..');
const templates = join(root, 'packaging/server');
const metadataSchema = z.object({
  version: z.string().regex(/^\d+\.\d+\.\d+(?:-nightly\.[1-9]\d*\.g[a-f0-9]{12})?$/),
  channel: z.enum(['stable', 'nightly']),
  sha: z.string().regex(/^[a-f0-9]{40}$/),
}).strict().superRefine((value, ctx) => {
  if (value.version.includes('-nightly.') !== (value.channel === 'nightly')) ctx.addIssue({ code: 'custom', message: 'Version/channel mismatch.' });
  if (value.channel === 'nightly' && !value.version.endsWith(`.g${value.sha.slice(0, 12)}`)) ctx.addIssue({ code: 'custom', message: 'Nightly source mismatch.' });
});

export function packageIdentity(input) {
  const metadata = metadataSchema.parse(input);
  const name = `peek-server${metadata.channel === 'nightly' ? '-nightly' : ''}`;
  const [base, nightly] = metadata.version.split('-nightly.');
  return {
    ...metadata, name,
    debVersion: metadata.version.replace('-nightly.', '~nightly.'),
    apkVersion: nightly ? `${base}_pre${nightly.split('.')[0]}` : base,
    debFilename: `${name}-${metadata.version}-linux-amd64.deb`,
    apkFilename: `${name}-${metadata.version}-linux-x86_64.apk`,
  };
}

export async function verifyApkKeys(privatePath, publicPath) {
  const privateKey = createPrivateKey(await readFile(privatePath));
  const publicContents = await readFile(publicPath, 'utf8');
  if (!/^-----BEGIN (?:RSA )?PUBLIC KEY-----/.test(publicContents.trim())) throw new Error('APK public key must contain only a public key.');
  const publicKey = createPublicKey(publicContents);
  if (privateKey.asymmetricKeyType !== 'rsa' || publicKey.asymmetricKeyType !== 'rsa') throw new Error('APK signing requires RSA keys.');
  const derived = createPublicKey(privateKey).export({ type: 'spki', format: 'der' });
  const expected = publicKey.export({ type: 'spki', format: 'der' });
  if (derived.length !== expected.length || !timingSafeEqual(derived, expected)) throw new Error('APK signing keys do not match.');
  return publicKey.export({ type: 'spki', format: 'pem' });
}

export async function validatePayload(directory) {
  const base = await realpath(directory);
  const required = ['server.js', '.next/BUILD_ID', '.next/static', 'public', 'node_modules/sharp', 'node_modules/playwright-core/browsers.json', 'node_modules/onnxruntime-web/dist/ort-wasm-simd-threaded.wasm'];
  for (const path of required) await stat(join(base, path));
  async function inspect(path) {
    const actual = await realpath(path);
    if (actual !== base && !actual.startsWith(`${base}${sep}`)) throw new Error('Standalone symlink escapes its payload.');
    const info = await stat(path);
    if (info.isDirectory()) {
      for (const entry of await readdir(path)) {
        if (entry === '.git' || /^\.env(?:\.|$)/.test(entry)) throw new Error('Standalone payload contains local configuration.');
        await inspect(join(path, entry));
      }
    } else if (path.endsWith('.node') || /\.so(?:\.|$)/.test(path)) {
      const bytes = await readFile(path);
      if (bytes.length < 20 || bytes.subarray(0, 4).toString('hex') !== '7f454c46' || bytes.readUInt16LE(18) !== 62) throw new Error('Standalone contains a native library for the wrong platform.');
    }
  }
  await inspect(base);
}

async function render(template, identity, destination, replacements = {}) {
  let content = await readFile(join(templates, template), 'utf8');
  for (const [key, value] of Object.entries({ PACKAGE_NAME: identity.name, RELEASE_VERSION: identity.version, SOURCE_SHA: identity.sha, ...replacements })) content = content.replaceAll(`@${key}@`, value);
  await writeFile(destination, content, { mode: template === 'launcher.mjs' || template === 'server.env' || template === 'systemd.service' ? 0o644 : 0o755 });
}

export async function buildPackage(options, environment = process.env) {
  const format = z.enum(['deb', 'apk']).parse(options.format);
  if (options.arch !== 'amd64') throw new Error('Only amd64/x86_64 native server packages are supported.');
  if (process.platform !== 'linux' || process.arch !== 'x64') throw new Error('Build native server packages on Linux x86_64 using the target distribution.');
  const osRelease = await readFile('/etc/os-release', 'utf8');
  if (format === 'deb' ? !/^ID=debian$/m.test(osRelease) || !/^VERSION_ID="?13"?$/m.test(osRelease) : !/^ID=alpine$/m.test(osRelease) || !/^VERSION_ID="?3\.24(?:\.\d+)?"?$/m.test(osRelease)) throw new Error('Build on Debian 13 for DEB or Alpine 3.24 for APK.');
  const identity = packageIdentity({ version: environment.PEEK_RELEASE_VERSION, channel: environment.PEEK_RELEASE_CHANNEL, sha: environment.PEEK_RELEASE_SHA });
  const standalone = resolve(z.string().min(1).parse(options.standalone));
  const output = resolve(z.string().min(1).parse(options.output));
  await validatePayload(standalone);
  const workspace = await mkdtemp(join(tmpdir(), 'peek-native-package-'));
  try {
    const app = join(workspace, 'app');
    await cp(standalone, app, { recursive: true, dereference: true, filter: (source) => relative(standalone, source) !== '.next/cache' });
    const launcher = join(workspace, 'launcher.mjs');
    const config = join(workspace, 'server.env');
    const migrations = join(workspace, 'migrations');
    await mkdir(migrations);
    const migrationSource = join(root, 'supabase/migrations');
    const migrationFiles = (await readdir(migrationSource)).filter((name) => name.endsWith('.sql')).sort();
    if (!migrationFiles.length) throw new Error('The release must include its SQL migrations.');
    for (const name of migrationFiles) {
      await cp(join(migrationSource, name), join(migrations, name));
      await chmod(join(migrations, name), 0o644);
    }
    await render('launcher.mjs', identity, launcher);
    await render('server.env', identity, config);
    const contents = [
      { src: `${app}/`, dst: `/usr/lib/${identity.name}/app/`, type: 'tree', file_info: { owner: 'root', group: 'root' } },
      { src: launcher, dst: `/usr/lib/${identity.name}/launcher.mjs`, file_info: { mode: 0o644 } },
      { src: config, dst: `/etc/${identity.name}/server.env`, type: 'config|noreplace', file_info: { mode: 0o640 } },
      { src: `/var/cache/${identity.name}/next`, dst: `/usr/lib/${identity.name}/app/.next/cache`, type: 'symlink' },
      { src: join(root, 'LICENSE'), dst: `/usr/share/doc/${identity.name}/copyright` },
      { src: `${migrations}/`, dst: `/usr/share/${identity.name}/migrations/`, type: 'tree', file_info: { owner: 'root', group: 'root' } },
    ];
    const scripts = {};
    const native = { name: identity.name, arch: 'amd64', platform: 'linux', version_schema: 'none', version: format === 'deb' ? identity.debVersion : identity.apkVersion,
      maintainer: 'Paul-Louis HERY <paullouis.hery@gmail.com>', description: 'Peek native parcel-tracking server', homepage: 'https://github.com/plhery/peek-delivery-tracker', license: 'Apache-2.0', disable_globbing: true, contents, scripts };
    let runtimeVersion;
    let runtimeSha256;
    if (format === 'deb') {
      const runtime = resolve(z.string().min(1).parse(options['node-runtime']));
      const nodePath = join(runtime, 'bin/node');
      const elf = await readFile(nodePath);
      if (elf.subarray(0, 4).toString('hex') !== '7f454c46' || elf.readUInt16LE(18) !== 62) throw new Error('Bundled Node must be Linux x86_64.');
      runtimeSha256 = createHash('sha256').update(elf).digest('hex');
      runtimeVersion = execFileSync(nodePath, ['--version'], { encoding: 'utf8' }).trim();
      z.string().regex(/^v26\.\d+\.\d+$/).parse(runtimeVersion);
      const [, minor, patch] = runtimeVersion.slice(1).split('.').map(Number);
      if (minor < 5 || (minor === 5 && patch < 1)) throw new Error('Bundled Node must satisfy the supported Node 26 engine.');
      contents.push({ src: nodePath, dst: `/usr/lib/${identity.name}/runtime/bin/node`, file_info: { mode: 0o755 } });
      contents.push({ src: join(runtime, 'LICENSE'), dst: `/usr/share/doc/${identity.name}/node-LICENSE` });
      const service = join(workspace, 'service');
      await render('systemd.service', identity, service);
      contents.push({ src: service, dst: `/usr/lib/systemd/system/${identity.name}.service`, file_info: { mode: 0o644 } });
      native.depends = ['libc6 (>= 2.36)', 'libstdc++6', 'libatomic1', 'chromium', 'ca-certificates', 'tini', 'adduser'];
      for (const phase of ['postinstall', 'preremove', 'postremove']) {
        const path = join(workspace, `deb-${phase}`);
        await render(`deb-${phase}`, identity, path);
        scripts[phase] = path;
      }
    } else {
      const privateKey = z.string().min(1).parse(environment.PEEK_APK_PRIVATE_KEY);
      const publicKey = z.string().min(1).parse(environment.PEEK_APK_PUBLIC_KEY);
      const exportedPublicKey = await verifyApkKeys(privateKey, publicKey);
      runtimeVersion = execFileSync('/usr/bin/node', ['--version'], { encoding: 'utf8' }).trim();
      native.depends = ['nodejs-current>=26.5.1', 'chromium', 'ca-certificates', 'tini', 'openrc'];
      native.release = '0';
      native.apk = { scripts: {} };
      const service = join(workspace, 'service');
      await render('openrc', identity, service);
      contents.push({ src: service, dst: `/etc/init.d/${identity.name}`, file_info: { mode: 0o755 } });
      for (const phase of ['preinstall', 'postinstall', 'preremove', 'preupgrade', 'postupgrade']) {
        const path = join(workspace, `apk-${phase}`);
        const postinstall = (await readFile(join(templates, 'apk-postinstall'), 'utf8')).split('\n').slice(3).join('\n');
        await render(`apk-${phase}`, identity, path, { POSTINSTALL: postinstall.replaceAll('@PACKAGE_NAME@', identity.name) });
        if (phase.endsWith('upgrade')) native.apk.scripts[phase] = path;
        else scripts[phase] = path;
      }
      await mkdir(output, { recursive: true });
      await writeFile(join(output, 'peek-server-apk.rsa.pub'), exportedPublicKey, { mode: 0o644 });
    }
    const info = join(workspace, 'build-info.json');
    await writeFile(info, JSON.stringify({ ...identity, runtimeVersion, ...(runtimeSha256 ? { runtimeSha256 } : {}), format, architecture: 'amd64', configuration: 'runtime' }, null, 2));
    contents.push({ src: info, dst: `/usr/share/doc/${identity.name}/build-info.json` });
    const nfpmConfig = join(workspace, 'nfpm.json');
    await writeFile(nfpmConfig, JSON.stringify(native, null, 2));
    await mkdir(output, { recursive: true });
    const target = join(output, format === 'deb' ? identity.debFilename : identity.apkFilename);
    if (format === 'deb') {
      execFileSync(environment.PEEK_NFPM || 'nfpm', ['package', '--config', nfpmConfig, '--packager', format, '--target', target], { stdio: 'inherit' });
    } else {
      if (process.getuid() !== 0) throw new Error('APK package staging must run as root in an isolated build environment.');
      const packageRoot = join(workspace, 'rootfs');
      for (const content of contents) {
        const destination = join(packageRoot, content.dst);
        await mkdir(dirname(destination), { recursive: true });
        if (content.type === 'symlink') await symlink(content.src, destination);
        else {
          await cp(content.src, destination, { recursive: true });
          if (content.file_info?.mode) await chmod(destination, content.file_info.mode);
        }
      }
      const args = ['mkpkg', '--files', packageRoot, '--output', target, '--sign-key', environment.PEEK_APK_PRIVATE_KEY];
      for (const [key, value] of Object.entries({ name: identity.name, version: `${identity.apkVersion}-r0`, arch: 'x86_64', description: native.description, license: native.license, url: native.homepage, maintainer: native.maintainer, depends: native.depends.join(' ') })) args.push('--info', `${key}:${value}`);
      const scriptNames = { preinstall: 'pre-install', postinstall: 'post-install', preremove: 'pre-deinstall', preupgrade: 'pre-upgrade', postupgrade: 'post-upgrade' };
      for (const [phase, path] of Object.entries({ ...scripts, ...native.apk.scripts })) args.push('--script', `${scriptNames[phase]}:${path}`);
      execFileSync('apk', args, { stdio: 'inherit' });
    }
    console.log(`Created ${basename(target)}.`);
    return target;
  } finally { await rm(workspace, { recursive: true, force: true }); }
}

if (process.argv[1] && import.meta.url === pathToFileURL(resolve(process.argv[1])).href) {
  const { values } = parseArgs({ options: { format: { type: 'string' }, arch: { type: 'string', default: 'amd64' }, standalone: { type: 'string' }, 'node-runtime': { type: 'string' }, output: { type: 'string', default: 'dist/server' } } });
  await buildPackage(values);
}
