import { readFileSync } from 'node:fs';
import { z } from 'zod';
const metadata = z.object({
  release: z.object({ version: z.string(), channel: z.enum(['stable', 'nightly']), sha: z.string(), buildNumber: z.number() }),
  native: z.object({ macShortVersion: z.string(), macBuildVersion: z.string(), windowsVersion: z.string(), debianVersion: z.string() }),
  identity: z.object({ appId: z.string(), packageName: z.string(), productName: z.string(), profile: z.string() }),
}).parse(JSON.parse(readFileSync('build/release.json', 'utf8')));
const { release, native, identity } = metadata;
const signed = process.env.PEEK_PUBLISH === 'true';
if (signed && process.platform === 'darwin' && !(process.env.CSC_LINK && process.env.CSC_KEY_PASSWORD && process.env.APPLE_API_KEY && process.env.APPLE_API_KEY_ID && process.env.APPLE_API_ISSUER)) throw new Error('Public macOS packages require signing and notarization credentials.');
if (signed && process.platform === 'win32' && !(process.env.WIN_CSC_LINK && process.env.WIN_CSC_KEY_PASSWORD)) throw new Error('Public Windows packages require signing credentials.');
const config = {
  appId: identity.appId,
  productName: identity.productName,
  extraMetadata: { name: identity.packageName, version: release.version },
  buildVersion: native.windowsVersion,
  artifactName: `${identity.packageName}-${release.version}-${signed ? '' : 'UNSIGNED-'}${'${os}'}-${'${arch}'}.${'${ext}'}`,
  directories: { output: 'dist', buildResources: 'ui' },
  files: ['build/**', 'package.json', '!build/release.json'],
  asar: true,
  npmRebuild: false,
  forceCodeSigning: signed,
  publish: null,
  mac: {
    target: ['dmg'],
    category: 'public.app-category.utilities',
    bundleShortVersion: native.macShortVersion,
    bundleVersion: native.macBuildVersion,
    hardenedRuntime: signed,
    notarize: signed,
  },
  win: { target: ['nsis'], signtoolOptions: { signingHashAlgorithms: ['sha256'] } },
  nsis: { oneClick: false, allowToChangeInstallationDirectory: true, deleteAppDataOnUninstall: false },
  linux: { target: ['deb', 'AppImage'], category: 'Utility', icon: 'ui/icon.png', executableName: identity.packageName },
  deb: { packageName: identity.packageName, fpm: ['--version', native.debianVersion] },
};

export default config;
