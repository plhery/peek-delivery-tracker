import { createHash } from 'node:crypto';
import { readdirSync, readFileSync, statSync, writeFileSync } from 'node:fs';
import { basename, join } from 'node:path';
import { apkPublicKeyName, fileSchema, manifestSchema, parseJson, planSchema, publicAssetNames } from './model.mjs';

export function artifactFiles(directory) {
  return readdirSync(directory).filter((name) => /\.(?:dmg|exe|deb|apk|pub|AppImage|tar)$/.test(name))
    .sort().map((name) => {
      const path = join(directory, name);
      if (!statSync(path).isFile()) throw new Error(`Artifact is not a regular file: ${name}`);
      return fileSchema.parse({ name: basename(name), size: statSync(path).size, sha256: createHash('sha256').update(readFileSync(path)).digest('hex') });
    });
}

export function verifyMatrix(files, plan) {
  const names = files.map((file) => file.name);
  for (const pattern of [/-arm64\.dmg$/, /-x64\.dmg$/, /-x64\.exe$/, /-x64\.deb$/, /-x64\.AppImage$/, /-linux-amd64\.deb$/, /-linux-x86_64\.apk$/, /^peek-server-apk\.rsa\.pub$/, /^image-amd64\.tar$/, /^image-arm64\.tar$/]) {
    if (names.filter((name) => pattern.test(name)).length !== 1) throw new Error(`Expected exactly one artifact matching ${pattern}`);
  }
  if (names.length !== 10) throw new Error('Unexpected artifacts in the release matrix');
  if (plan && publicAssetNames(plan).some((name) => names.filter((candidate) => candidate === name).length !== 1)) {
    throw new Error('Release filenames do not exactly identify the planned version and channel');
  }
}

export function writeChecksums(directory, files) {
  writeFileSync(join(directory, 'SHA256SUMS'), files.map((file) => `${file.sha256}  ${file.name}\n`).join(''));
}

export function createManifest(plan, files, imageDigest) {
  const publicKey = files.find((file) => file.name === apkPublicKeyName);
  if (!publicKey) throw new Error('APK signing public key asset is required');
  return manifestSchema.parse({
    schemaVersion: 1, status: 'complete', plan, signed: true, files,
    image: { repository: plan.imageRepository, digest: imageDigest, platforms: ['linux/amd64', 'linux/arm64'], configuration: 'runtime-configured' },
    server: {
      runtime: 'native', configuration: 'runtime-configured', platforms: ['debian13/amd64', 'alpine3.24/x86_64'],
      apkSigningKey: { name: publicKey.name, sha256: publicKey.sha256 },
    },
    validation: { runId: plan.runId, sourceSha: plan.sourceSha },
  });
}

export function readPlan(path = 'release-plan.json') {
  return parseJson(planSchema, readFileSync(path, 'utf8'));
}
