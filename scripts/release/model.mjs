import { z } from 'zod';

export const shaSchema = z.string().regex(/^[a-f0-9]{40}$/);
export const versionSchema = z.string().regex(/^(0|[1-9]\d*)\.(0|[1-9]\d*)\.(0|[1-9]\d*)$/);
export const channelSchema = z.enum(['nightly', 'stable']);
export const digestSchema = z.string().regex(/^sha256:[a-f0-9]{64}$/);
const repositorySchema = z.string().regex(/^[a-zA-Z0-9_.-]+\/[a-zA-Z0-9_.-]+$/);
const imageRepositorySchema = z.string().regex(/^ghcr\.io\/[a-z0-9_.-]+\/[a-z0-9_./-]+$/);
export const planSchema = z.object({
  schemaVersion: z.literal(1),
  channel: channelSchema,
  sourceSha: shaSchema,
  version: z.string().regex(/^\d+\.\d+\.\d+(?:-nightly\.\d+\.g[a-f0-9]{12})?$/),
  baseVersion: versionSchema,
  stableBasePublished: z.boolean(),
  tag: z.string().regex(/^v\d+\.\d+\.\d+(?:-nightly\.\d+\.g[a-f0-9]{12})?$/),
  buildNumber: z.number().int().min(1).max(65535),
  runId: z.string().regex(/^\d+$/),
  runAttempt: z.number().int().positive(),
  repository: repositorySchema,
  imageRepository: imageRepositorySchema,
  publish: z.boolean(),
  skip: z.boolean(),
  reason: z.string(),
});
export const fileSchema = z.object({
  name: z.string().regex(/^[a-zA-Z0-9_.-]+$/),
  sha256: z.string().regex(/^[a-f0-9]{64}$/),
  size: z.number().int().positive(),
});

export function installerNames(plan) {
  const identity = plan.channel === 'nightly' ? 'peek-desktop-nightly' : 'peek-desktop';
  const prefix = `${identity}-${plan.version}-${plan.publish ? '' : 'UNSIGNED-'}`;
  return ['mac-arm64.dmg', 'mac-x64.dmg', 'win-x64.exe', 'linux-x64.deb', 'linux-x64.AppImage'].map((suffix) => `${prefix}${suffix}`);
}

export const apkPublicKeyName = 'peek-server-apk.rsa.pub';

export function serverPackageNames(plan) {
  const identity = plan.channel === 'nightly' ? 'peek-server-nightly' : 'peek-server';
  return [`${identity}-${plan.version}-linux-amd64.deb`, `${identity}-${plan.version}-linux-x86_64.apk`];
}

export function publicAssetNames(plan) {
  return [...installerNames(plan), ...serverPackageNames(plan), apkPublicKeyName];
}

export const manifestSchema = z.object({
  schemaVersion: z.literal(1),
  status: z.literal('complete'),
  plan: planSchema,
  signed: z.boolean(),
  files: z.array(fileSchema).length(8),
  image: z.object({
    repository: imageRepositorySchema,
    digest: digestSchema,
    platforms: z.tuple([z.literal('linux/amd64'), z.literal('linux/arm64')]),
    configuration: z.literal('runtime-configured'),
  }),
  server: z.object({
    runtime: z.literal('native'),
    configuration: z.literal('runtime-configured'),
    platforms: z.tuple([z.literal('debian13/amd64'), z.literal('alpine3.24/x86_64')]),
    apkSigningKey: z.object({ name: z.literal(apkPublicKeyName), sha256: z.string().regex(/^[a-f0-9]{64}$/) }),
  }),
  validation: z.object({ runId: z.string().regex(/^\d+$/), sourceSha: shaSchema }),
}).superRefine((manifest, context) => {
  const { plan } = manifest;
  if (plan.tag !== `v${plan.version}` || manifest.validation.sourceSha !== plan.sourceSha || manifest.validation.runId !== plan.runId || manifest.image.repository !== plan.imageRepository) {
    context.addIssue({ code: 'custom', message: 'Manifest source, run, image or tag identity mismatch' });
  }
  for (const name of publicAssetNames(plan)) {
    if (manifest.files.filter((file) => file.name === name).length !== 1) {
      context.addIssue({ code: 'custom', message: `Incomplete release artifact matrix: ${name}` });
    }
  }
  if (manifest.files.find((file) => file.name === apkPublicKeyName)?.sha256 !== manifest.server.apkSigningKey.sha256) {
    context.addIssue({ code: 'custom', message: 'APK signing public key identity mismatch' });
  }
});

export function parseJson(schema, text) {
  return schema.parse(JSON.parse(text));
}

export function compareVersions(left, right) {
  const a = versionSchema.parse(left).split('.').map(Number);
  const b = versionSchema.parse(right).split('.').map(Number);
  for (let index = 0; index < 3; index += 1) {
    if (a[index] !== b[index]) return a[index] > b[index] ? 1 : -1;
  }
  return 0;
}

export function planRelease(input) {
  const channel = channelSchema.parse(input.channel);
  const sourceSha = shaSchema.parse(input.sourceSha);
  const packageVersion = versionSchema.parse(input.packageVersion);
  const manifests = z.array(manifestSchema).parse(input.manifests);
  const stable = manifests.filter((manifest) => manifest.plan.channel === 'stable')
    .sort((a, b) => compareVersions(b.plan.version, a.plan.version));
  const baseVersion = stable[0]?.plan.version ?? packageVersion;
  const buildNumber = z.coerce.number().int().min(1).max(65535).parse(input.runNumber);
  const version = channel === 'nightly'
    ? `${baseVersion}-nightly.${buildNumber}.g${sourceSha.slice(0, 12)}`
    : versionSchema.parse(input.requestedVersion);
  if (version.split(/[.-]/).slice(0, 3).some((part) => Number(part) > 65535)) {
    throw new Error('Version components exceed the Windows 16-bit version limit');
  }
  if (channel === 'stable' && version !== packageVersion) {
    throw new Error('Stable version must match the version committed in package.json');
  }
  const sameTag = manifests.find((manifest) => manifest.plan.tag === `v${version}`);
  if (sameTag && sameTag.plan.sourceSha !== sourceSha) {
    throw new Error('Published version is already bound to a different source SHA');
  }
  if (channel === 'stable' && stable[0] && compareVersions(version, stable[0].plan.version) <= 0 && !sameTag) {
    throw new Error('Stable version must be greater than every published stable version');
  }
  const sameSource = manifests.some((manifest) => manifest.plan.sourceSha === sourceSha);
  const skip = Boolean(sameTag || (channel === 'nightly' && sameSource));
  return planSchema.parse({
    schemaVersion: 1, channel, sourceSha, version, baseVersion,
    stableBasePublished: stable.length > 0,
    tag: `v${version}`, buildNumber,
    runId: input.runId, runAttempt: input.runAttempt,
    repository: input.repository, imageRepository: input.imageRepository,
    publish: input.publish, skip,
    reason: skip ? 'Source/version already has a complete successful publication' : 'New release candidate',
  });
}
