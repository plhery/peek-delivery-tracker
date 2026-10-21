import assert from 'node:assert/strict';
import { test } from 'node:test';
import { createManifest, verifyMatrix } from './artifacts.mjs';
import { compareVersions, manifestSchema, publicAssetNames, parseJson, planRelease, serverPackageNames } from './model.mjs';

const source = 'a'.repeat(40);
const otherSource = 'b'.repeat(40);
const input = {
  channel: 'nightly', sourceSha: source, packageVersion: '1.0.0', requestedVersion: '',
  runNumber: 42, runId: '123456789', runAttempt: 1, repository: 'example/peek',
  imageRepository: 'ghcr.io/example/peek-release-poc', publish: false, manifests: [],
};
const names = ['peek-1.0.0-arm64.dmg', 'peek-1.0.0-x64.dmg', 'peek-1.0.0-x64.exe', 'peek-1.0.0-x64.deb', 'peek-1.0.0-x64.AppImage', 'peek-server-1.0.0-linux-amd64.deb', 'peek-server-1.0.0-linux-x86_64.apk', 'peek-server-apk.rsa.pub', 'image-amd64.tar', 'image-arm64.tar'];
const files = names.map((name) => ({ name, sha256: 'a'.repeat(64), size: 123 }));
function published(overrides = {}) {
  const plan = planRelease({ ...input, channel: 'stable', requestedVersion: '1.0.0', publish: true, ...overrides });
  const installers = publicAssetNames(plan).map((name) => ({ name, sha256: 'a'.repeat(64), size: 123 }));
  return createManifest(plan, installers, `sha256:${'b'.repeat(64)}`);
}

test('bootstrap nightly is stable base plus entry run and source, explicitly unpublished base', () => {
  const plan = planRelease(input);
  assert.equal(plan.version, '1.0.0-nightly.42.gaaaaaaaaaaaa');
  assert.equal(plan.stableBasePublished, false);
  assert.equal(plan.buildNumber, 42);
  assert.equal(plan.skip, false);
  assert.equal(plan.publish, false);
});

test('nightly follows greatest published stable numerically, not package or API order', () => {
  const plan = planRelease({ ...input, packageVersion: '3.0.0', sourceSha: otherSource, manifests: [published({ packageVersion: '1.2.0', requestedVersion: '1.2.0' }), published({ packageVersion: '1.10.0', requestedVersion: '1.10.0' })] });
  assert.equal(plan.version, '1.10.0-nightly.42.gbbbbbbbbbbbb');
  assert.equal(plan.stableBasePublished, true);
});

test('unchanged SHA skips after either stable or nightly successful publication', () => {
  const stable = published();
  const nightly = published({ channel: 'nightly' });
  assert.equal(planRelease({ ...input, runNumber: 43, manifests: [stable] }).skip, true);
  assert.equal(planRelease({ ...input, runNumber: 43, manifests: [nightly] }).skip, true);
  assert.equal(planRelease({ ...input, sourceSha: otherSource, manifests: [stable] }).skip, false);
});

test('new source remains eligible after no complete successful publication', () => {
  assert.equal(planRelease({ ...input, runAttempt: 2 }).skip, false);
  assert.throws(() => planRelease({ ...input, manifests: [{ ...published(), status: 'failed' }] }));
});

test('stable rejects downgrade and package mismatch, allows exact idempotent resume', () => {
  const newer = published({ packageVersion: '1.1.0', requestedVersion: '1.1.0' });
  assert.throws(() => planRelease({ ...input, channel: 'stable', requestedVersion: '1.0.0', manifests: [newer] }), /greater/);
  assert.throws(() => planRelease({ ...input, channel: 'stable', requestedVersion: '1.2.0' }), /committed/);
  assert.equal(planRelease({ ...input, channel: 'stable', requestedVersion: '1.0.0', manifests: [published()] }).skip, true);
  assert.throws(() => planRelease({ ...input, channel: 'stable', sourceSha: otherSource, requestedVersion: '1.0.0', manifests: [published()] }), /different source/);
});

test('stable can follow a nightly with the same source without being skipped', () => {
  const nightly = published({ channel: 'nightly' });
  const stable = planRelease({ ...input, channel: 'stable', requestedVersion: '1.0.0', manifests: [nightly] });
  assert.equal(stable.skip, false);
});

test('run retries retain release identity and record attempts separately', () => {
  const retry = planRelease({ ...input, runAttempt: 2 });
  assert.equal(retry.version, planRelease(input).version);
  assert.equal(retry.buildNumber, planRelease(input).buildNumber);
  assert.equal(retry.runAttempt, 2);
});

test('numeric native limits and shell/JSON input boundaries fail before a build', () => {
  for (const runNumber of [0, -1, 65536, '1; false', 1.5]) assert.throws(() => planRelease({ ...input, runNumber }));
  assert.equal(planRelease({ ...input, runNumber: 65535 }).buildNumber, 65535);
  assert.throws(() => planRelease({ ...input, channel: 'stable', packageVersion: '65536.0.0', requestedVersion: '65536.0.0' }), /16-bit/);
  assert.throws(() => planRelease({ ...input, sourceSha: 'main;false' }));
  assert.throws(() => planRelease({ ...input, imageRepository: 'ghcr.io/Example/peek' }));
  assert.throws(() => parseJson(manifestSchema, '{"status":"complete"}'));
});

test('SemVer stable comparison does not sort numeric parts lexically', () => {
  assert.equal(compareVersions('1.10.0', '1.2.0'), 1);
  assert.equal(compareVersions('1.0.0', '1.0.0'), 0);
  assert.throws(() => compareVersions('01.0.0', '1.0.0'));
});

test('complete matrix requires both macOS architectures, Windows, Linux and both image archives', () => {
  assert.doesNotThrow(() => verifyMatrix(files));
  assert.throws(() => verifyMatrix(files.filter((file) => !file.name.endsWith('.exe'))));
  assert.throws(() => verifyMatrix([...files, { name: 'other-x64.exe' }]));
});


test('manifest rejects cross-image and cross-source evidence even with a complete filename matrix', () => {
  const manifest = published();
  assert.throws(() => manifestSchema.parse({ ...manifest, image: { ...manifest.image, repository: 'ghcr.io/example/other' } }), /identity mismatch/);
  assert.throws(() => manifestSchema.parse({ ...manifest, validation: { ...manifest.validation, sourceSha: otherSource } }), /identity mismatch/);
});


test('artifact binding rejects substring versions, wrong channels and signed/unsigned name confusion', () => {
  const manifest = published();
  for (const transform of [
    (name) => name.replace('-1.0.0-', '-11.0.0-'),
    (name) => name.replace('-1.0.0-', '-1.0.0-nightly.1.gaaaaaaaaaaaa-'),
    (name) => name.replace('peek-desktop-', 'peek-desktop-nightly-'),
    (name) => name.replace('-mac-', '-UNSIGNED-mac-'),
  ]) {
    assert.throws(() => manifestSchema.parse({ ...manifest, files: manifest.files.map((file) => ({ ...file, name: transform(file.name) })) }), /artifact matrix/);
  }
  const candidates = [...manifest.files, ...files.filter((file) => file.name.endsWith('.tar'))];
  assert.doesNotThrow(() => verifyMatrix(candidates, manifest.plan));
  assert.throws(() => verifyMatrix(candidates.map((file) => ({ ...file, name: file.name.replace('-1.0.0-', '-11.0.0-') })), manifest.plan), /exactly identify/);
});

test('server packages are distinct mandatory assets for both channels and bind the APK key', () => {
  for (const channel of ['stable', 'nightly']) {
    const manifest = published({ channel });
    const names = serverPackageNames(manifest.plan);
    const prefix = channel === 'nightly' ? 'peek-server-nightly-' : 'peek-server-';
    assert.ok(names.every((name) => name.startsWith(prefix)));
    assert.ok(names[0].endsWith('-linux-amd64.deb'));
    assert.ok(names[1].endsWith('-linux-x86_64.apk'));
    assert.throws(() => manifestSchema.parse({ ...manifest, files: manifest.files.filter((file) => file.name !== names[0]) }));
    assert.throws(() => manifestSchema.parse({ ...manifest, files: manifest.files.filter((file) => file.name !== names[1]) }));
    assert.throws(() => manifestSchema.parse({ ...manifest, server: { ...manifest.server, apkSigningKey: { ...manifest.server.apkSigningKey, sha256: 'd'.repeat(64) } } }), /key identity/);
  }
});
