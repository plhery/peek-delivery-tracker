import assert from 'node:assert/strict';
import { test } from 'node:test';
import { promoteAliases, publishImages, verifyIndex } from './registry.mjs';

const amd64 = `sha256:${'a'.repeat(64)}`;
const arm64 = `sha256:${'b'.repeat(64)}`;
const index = `sha256:${'c'.repeat(64)}`;
const plan = { imageRepository: 'ghcr.io/example/peek-release-poc', version: '1.0.0', channel: 'stable' };
const descriptor = { digest: index, manifests: [
  { digest: amd64, platform: { architecture: 'amd64', os: 'linux' } },
  { digest: arm64, platform: { architecture: 'arm64', os: 'linux' } },
] };

test('OCI index rejects missing, duplicate or mismatched architecture digests', () => {
  assert.doesNotThrow(() => verifyIndex(descriptor, { amd64, arm64 }));
  assert.throws(() => verifyIndex({ ...descriptor, manifests: [descriptor.manifests[0]] }, { amd64, arm64 }));
  assert.throws(() => verifyIndex({ ...descriptor, manifests: [descriptor.manifests[0], descriptor.manifests[0]] }, { amd64, arm64 }));
  assert.throws(() => verifyIndex(descriptor, { amd64: arm64, arm64 }));
});

test('retry reuses identical staged image config and never pushes or rewrites version tags', () => {
  const calls = [];
  const run = (args) => {
    calls.push(args);
    if (args[0] === 'load') return '';
    if (args[0] === 'image') return `${amd64}\n`;
    if (args.includes('--raw')) return JSON.stringify({ config: { digest: amd64 } });
    if (args.includes('--format')) {
      const reference = args[3];
      if (reference.endsWith('-amd64')) return JSON.stringify({ digest: amd64 });
      if (reference.endsWith('-arm64')) return JSON.stringify({ digest: arm64 });
      return JSON.stringify(descriptor);
    }
    throw new Error(`Unexpected mutation: ${args.join(' ')}`);
  };
  assert.equal(publishImages(plan, 'artifacts', run), index);
  assert.equal(calls.some((args) => ['push', 'tag'].includes(args[0]) || args.includes('create')), false);
});

test('a changed staged image config blocks overwrite of an existing version', () => {
  const calls = [];
  assert.throws(() => publishImages(plan, 'artifacts', (args) => {
    calls.push(args);
    if (args[0] === 'load') return '';
    if (args[0] === 'image') return amd64;
    if (args.includes('--raw')) return JSON.stringify({ config: { digest: arm64 } });
    return JSON.stringify({ digest: amd64 });
  }), /overwrite/);
  assert.equal(calls.some((args) => args[0] === 'push'), false);
});

test('registry authentication/network errors are never interpreted as a missing tag', () => {
  const network = new Error('registry unavailable');
  network.stderr = Buffer.from('HTTP 503 unavailable');
  assert.throws(() => publishImages(plan, 'artifacts', (args) => {
    if (args[0] === 'load') return '';
    if (args[0] === 'image') return amd64;
    throw network;
  }), /unavailable/);
});

test('stable alias recovery updates stable/latest by digest, never nightly', () => {
  const calls = [];
  promoteAliases(plan, index, (args) => {
    calls.push(args);
    return args.includes('inspect') ? JSON.stringify(descriptor) : '';
  });
  const creates = calls.filter((args) => args.includes('create'));
  assert.equal(creates.length, 2);
  assert.equal(creates[0].includes(`${plan.imageRepository}:stable`), true);
  assert.equal(creates[1].includes(`${plan.imageRepository}:latest`), true);
  assert.equal(creates.every((args) => args.at(-1) === `${plan.imageRepository}@${index}`), true);
});
