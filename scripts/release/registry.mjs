import { execFileSync } from 'node:child_process';
import { join } from 'node:path';
import { z } from 'zod';
import { digestSchema, parseJson } from './model.mjs';

const descriptorSchema = z.object({ digest: digestSchema, manifests: z.array(z.object({
  digest: digestSchema, platform: z.object({ architecture: z.enum(['amd64', 'arm64']), os: z.literal('linux') }),
})).optional() });
const imageManifestSchema = z.object({ config: z.object({ digest: digestSchema }) });
const command = (args) => execFileSync('docker', args, { encoding: 'utf8', maxBuffer: 16 * 1024 * 1024 });

function existingDescriptor(reference, run) {
  try {
    return parseJson(descriptorSchema, run(['buildx', 'imagetools', 'inspect', reference, '--format', '{{json .Manifest}}']));
  } catch (error) {
    const stderr = error.stderr?.toString() || '';
    if (/(?:manifest unknown|not found|name unknown)/i.test(stderr)) return null;
    throw error;
  }
}

export function verifyIndex(descriptor, platforms) {
  if (!descriptor.manifests || descriptor.manifests.length !== 2) throw new Error('Expected exactly two OCI platform manifests');
  for (const [architecture, digest] of Object.entries(platforms)) {
    const entries = descriptor.manifests.filter((item) => item.platform.architecture === architecture);
    if (entries.length !== 1 || entries[0].digest !== digest) throw new Error(`OCI index mismatch for ${architecture}`);
  }
}

export function publishImages(plan, directory, run = command) {
  const platforms = {};
  for (const architecture of ['amd64', 'arm64']) {
    const local = `peek-release:${architecture}`;
    const remote = `${plan.imageRepository}:${plan.version}-${architecture}`;
    run(['load', '--input', join(directory, `image-${architecture}.tar`)]);
    const localConfig = digestSchema.parse(run(['image', 'inspect', local, '--format', '{{.Id}}']).trim());
    const existing = existingDescriptor(remote, run);
    if (existing) {
      const manifest = parseJson(imageManifestSchema, run(['buildx', 'imagetools', 'inspect', remote, '--raw']));
      if (manifest.config.digest !== localConfig) throw new Error(`Refusing to overwrite existing image ${remote}`);
    } else {
      run(['tag', local, remote]);
      run(['push', remote]);
    }
    platforms[architecture] = existingDescriptor(remote, run)?.digest;
    digestSchema.parse(platforms[architecture]);
  }
  const reference = `${plan.imageRepository}:${plan.version}`;
  let index = existingDescriptor(reference, run);
  if (!index) {
    run(['buildx', 'imagetools', 'create', '--tag', reference, ...Object.values(platforms).map((digest) => `${plan.imageRepository}@${digest}`)]);
    index = existingDescriptor(reference, run);
  }
  if (!index) throw new Error('Published image index cannot be read');
  verifyIndex(index, platforms);
  return index.digest;
}

export function promoteAliases(plan, digest, run = command) {
  const aliases = plan.channel === 'nightly' ? ['nightly'] : ['stable', 'latest'];
  for (const alias of aliases) {
    run(['buildx', 'imagetools', 'create', '--tag', `${plan.imageRepository}:${alias}`, `${plan.imageRepository}@${digest}`]);
    const descriptor = existingDescriptor(`${plan.imageRepository}:${alias}`, run);
    if (descriptor?.digest !== digest) throw new Error(`OCI alias verification failed: ${alias}`);
  }
}
