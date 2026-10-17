import { test } from 'node:test';
import assert from 'node:assert/strict';
import { releaseSchema, nativeVersions, channelIdentity } from './release.ts';

const sha = 'abcdef123456abcdef123456abcdef123456abcd';
test('native metadata preserves full display version and numeric build limits', () => {
  const release = releaseSchema.parse({ version: '1.2.3-nightly.456.gabcdef123456', channel: 'nightly', sha, buildNumber: 65535 });
  assert.deepEqual(nativeVersions(release), { macShortVersion: '1.2.3', macBuildVersion: '7.55.35', windowsVersion: '1.2.3.65535', debianVersion: '1.2.3~nightly.456.gabcdef123456' });
  assert.equal(nativeVersions({ version: '1.2.3', channel: 'stable', sha, buildNumber: 1 }).debianVersion, '1.2.3');
  for (const buildNumber of [0, 65536, 1.1]) assert.throws(() => releaseSchema.parse({ ...release, buildNumber }));
  assert.throws(() => releaseSchema.parse({ ...release, channel: 'stable' }));
  assert.throws(() => releaseSchema.parse({ ...release, sha: '0'.repeat(40) }));
  assert.throws(() => releaseSchema.parse({ ...release, version: '65536.0.0-nightly.456.gabcdef123456' }));
});
test('nightly and stable identities and profile directories are distinct', () => {
  const stable = channelIdentity('stable');
  const nightly = channelIdentity('nightly');
  assert.notEqual(stable.appId, nightly.appId);
  assert.notEqual(stable.packageName, nightly.packageName);
  assert.notEqual(stable.profile, nightly.profile);
});
