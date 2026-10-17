import assert from 'node:assert/strict';
import { createHash } from 'node:crypto';
import { test } from 'node:test';
import { createManifest } from './artifacts.mjs';
import { isDraftPlaceholder, publishedManifests, tagSource, verifyPublishedImage, verifyStagedAssets } from './github.mjs';
import { publicAssetNames, planRelease } from './model.mjs';

const sha = 'a'.repeat(40);
const digestOf = (text) => `sha256:${createHash('sha256').update(text).digest('hex')}`;
function fixture() {
  const content = new Map();
  const descriptors = ['amd64', 'arm64'].map((architecture) => {
    const config = JSON.stringify({ architecture, os: 'linux', config: { Labels: { 'org.opencontainers.image.revision': sha, 'org.opencontainers.image.version': '1.0.0', 'org.opencontainers.image.source': 'https://github.com/example/peek' } } });
    const configDigest = digestOf(config);
    content.set(`/blobs/${configDigest}`, config);
    const platform = JSON.stringify({ config: { digest: configDigest }, layers: [{ digest: configDigest }] });
    const digest = digestOf(platform);
    content.set(`/manifests/${digest}`, platform);
    return { digest, platform: { os: 'linux', architecture } };
  });
  const index = JSON.stringify({ manifests: descriptors });
  const digest = digestOf(index);
  content.set(`/manifests/${digest}`, index);
  const plan = planRelease({ channel: 'stable', sourceSha: sha, packageVersion: '1.0.0', requestedVersion: '1.0.0', runNumber: 1, runId: '123', runAttempt: 1, repository: 'example/peek', imageRepository: 'ghcr.io/example/peek-release-poc', publish: true, manifests: [] });
  const files = publicAssetNames(plan).map((name) => ({ name, sha256: 'b'.repeat(64), size: 123 }));
  const manifest = createManifest(plan, files, digest);
  const release = { id: 1, tag_name: 'v1.0.0', target_commitish: sha, draft: false, prerelease: false, assets: [
    { id: 1, name: 'release-manifest.json', state: 'uploaded', size: 100, browser_download_url: 'https://github.com/example/peek/releases/download/v1.0.0/release-manifest.json' },
    { id: 2, name: 'SHA256SUMS', state: 'uploaded', size: 100, browser_download_url: 'https://github.com/example/peek/releases/download/v1.0.0/SHA256SUMS' },
    ...files.map((file, index) => ({ id: index + 3, name: file.name, size: file.size, state: 'uploaded', digest: `sha256:${file.sha256}`, browser_download_url: `https://github.com/example/peek/releases/download/v1.0.0/${file.name}` })),
  ] };
  return { content, descriptors, manifest, release };
}
function handler(data, overrides = {}) {
  return async (input) => {
    const url = new URL(input);
    if (overrides[url.pathname]) return overrides[url.pathname](url);
    if (url.pathname.endsWith('/release-manifest.json')) return new Response(JSON.stringify(data.manifest));
    if (url.pathname.endsWith('/SHA256SUMS')) return new Response(data.manifest.files.map((file) => `${file.sha256}  ${file.name}\n`).join(''));
    if (url.hostname === 'api.github.com' && url.pathname.includes('/git/ref/tags/')) return Response.json({ object: { type: 'commit', sha } });
    if (url.hostname === 'ghcr.io' && url.pathname === '/token') return Response.json({ token: 'synthetic-public-read-token' });
    if (url.hostname === 'ghcr.io') {
      const key = url.pathname.replace('/v2/example/peek-release-poc', '');
      return data.content.has(key) ? new Response(data.content.get(key)) : new Response('not found', { status: 404 });
    }
    throw new Error(`Unexpected network access: ${url}`);
  };
}

test('complete publication baseline requires installers, immutable tag identity and actual public OCI content', async (t) => {
  const data = fixture();
  t.mock.method(globalThis, 'fetch', handler(data));
  const result = await publishedManifests('example/peek', [data.release]);
  assert.equal(result.length, 1);
  assert.equal(result[0].plan.sourceSha, sha);
});

test('drafts and non-managed releases never advance the success baseline', async (t) => {
  t.mock.method(globalThis, 'fetch', () => { throw new Error('No network should be needed'); });
  const data = fixture();
  assert.deepEqual(await publishedManifests('example/peek', [{ ...data.release, draft: true }, { ...data.release, assets: [] }]), []);
});

test('unrelated files cannot masquerade as a complete release', async (t) => {
  const data = fixture();
  data.manifest.files = data.manifest.files.map((file, index) => ({ ...file, name: `unrelated-${index}.bin` }));
  t.mock.method(globalThis, 'fetch', handler(data));
  await assert.rejects(publishedManifests('example/peek', [data.release]), /artifact matrix/);
});

test('moved or wrongly declared tags cannot advance the baseline', async (t) => {
  const data = fixture();
  t.mock.method(globalThis, 'fetch', handler(data, { '/repos/example/peek/git/ref/tags/v1.0.0': () => Response.json({ object: { type: 'commit', sha: 'c'.repeat(40) } }) }));
  await assert.rejects(publishedManifests('example/peek', [data.release]), /tag does not resolve/);
  await assert.rejects(publishedManifests('example/peek', [{ ...data.release, target_commitish: 'main' }]), /tag does not resolve/);
});

test('annotated tags are peeled to their commit before identity comparison', async (t) => {
  const data = fixture();
  t.mock.method(globalThis, 'fetch', handler(data, {
    '/repos/example/peek/git/ref/tags/v1.0.0': () => Response.json({ object: { type: 'tag', sha: 'c'.repeat(40) } }),
    [`/repos/example/peek/git/tags/${'c'.repeat(40)}`]: () => Response.json({ object: { type: 'commit', sha } }),
  }));
  assert.equal(await tagSource('example/peek', 'v1.0.0'), sha);
});

test('missing registry content and falsely described platforms invalidate a success baseline', async (t) => {
  const data = fixture();
  data.content.delete(`/manifests/${data.descriptors[1].digest}`);
  t.mock.method(globalThis, 'fetch', handler(data));
  await assert.rejects(publishedManifests('example/peek', [data.release]), /OCI manifest is unavailable/);
});

test('registry manifest bytes must hash to the declared digest', async (t) => {
  const data = fixture();
  data.content.set(`/manifests/${data.manifest.image.digest}`, JSON.stringify({ manifests: [] }));
  t.mock.method(globalThis, 'fetch', handler(data));
  await assert.rejects(verifyPublishedImage(data.manifest.image, data.manifest.plan), /content digest mismatch/);
});

test('only zero-byte starter assets in drafts can be removed for retry', () => {
  assert.equal(isDraftPlaceholder({ draft: true }, { state: 'starter', size: 0 }), true);
  assert.equal(isDraftPlaceholder({ draft: false }, { state: 'starter', size: 0 }), false);
  assert.equal(isDraftPlaceholder({ draft: true }, { state: 'uploaded', size: 0 }), false);
  assert.equal(isDraftPlaceholder({ draft: true }, { state: 'starter', size: 1 }), false);
});


test('OCI platform descriptors must match the actual platform config blobs', async (t) => {
  const data = fixture();
  data.descriptors[0].platform.architecture = 'arm64';
  data.descriptors[1].platform.architecture = 'amd64';
  const text = JSON.stringify({ manifests: data.descriptors });
  data.manifest.image.digest = digestOf(text);
  data.content.set(`/manifests/${digestOf(text)}`, text);
  t.mock.method(globalThis, 'fetch', handler(data));
  await assert.rejects(verifyPublishedImage(data.manifest.image, data.manifest.plan), /config architecture mismatch/);
});

test('draft publication waits for uploaded digest-verified assets and rejects placeholders or extras', () => {
  const data = fixture();
  const files = data.manifest.files;
  const release = { ...data.release, assets: data.release.assets.filter((asset) => files.some((file) => file.name === asset.name)) };
  assert.doesNotThrow(() => verifyStagedAssets(release, files));
  assert.throws(() => verifyStagedAssets({ ...release, assets: release.assets.slice(1) }, files), /missing or unexpected/);
  assert.throws(() => verifyStagedAssets({ ...release, assets: release.assets.map((asset) => ({ ...asset, state: 'starter', size: 0 })) }, files), /incomplete or mismatched/);
  assert.throws(() => verifyStagedAssets({ ...release, assets: [...release.assets, { name: 'unexpected-secret' }] }, files), /missing or unexpected/);
});


test('valid public image content from a different source/version cannot validate this release', async (t) => {
  const data = fixture();
  t.mock.method(globalThis, 'fetch', handler(data));
  await assert.rejects(verifyPublishedImage(data.manifest.image, { ...data.manifest.plan, sourceSha: 'd'.repeat(40) }), /source\/version identity/);
  await assert.rejects(verifyPublishedImage(data.manifest.image, { ...data.manifest.plan, version: '1.0.1' }), /source\/version identity/);
});
