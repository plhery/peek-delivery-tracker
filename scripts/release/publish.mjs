import { readFileSync, writeFileSync } from 'node:fs';
import { createHash } from 'node:crypto';
import { join } from 'node:path';
import { z } from 'zod';
import { artifactFiles, createManifest, readPlan, verifyMatrix, writeChecksums } from './artifacts.mjs';
import { api, isAncestor, isDraftPlaceholder, publishedManifests, releases, releaseSchema, tagSource, verifyPublishedImage, verifySourceCi, verifyStagedAssets } from './github.mjs';
import { compareVersions, parseJson, planSchema, shaSchema } from './model.mjs';
import { promoteAliases, publishImages } from './registry.mjs';

const directory = process.argv[2] || 'release-assets';
const plan = readPlan();
if (!plan.publish || process.env.PEEK_PUBLISH_ENABLED !== 'true' || process.env.GITHUB_REF !== 'refs/heads/main') {
  throw new Error('Publication is disabled; requires an opted-in trusted main workflow');
}
if (plan.repository !== process.env.GITHUB_REPOSITORY || plan.runId !== process.env.GITHUB_RUN_ID) {
  throw new Error('Release plan belongs to a different repository or workflow run');
}
const main = await api(`repos/${plan.repository}/git/ref/heads/main`, z.object({ object: z.object({ sha: shaSchema }) }));
if (!isAncestor(plan.sourceSha, main.object.sha)) throw new Error('Release source is not on main');
await verifySourceCi(plan.repository, plan.sourceSha);
const entries = await releases(plan.repository);
let release = entries.find((entry) => entry.tag_name === plan.tag);
const tagSha = await tagSource(plan.repository, plan.tag, true);
if (release && release.target_commitish !== plan.sourceSha) throw new Error('Release target is bound to a different source');
if (tagSha && (!release || tagSha !== plan.sourceSha)) throw new Error('Existing release tag cannot be adopted or overwritten');
if (release && !release.draft && !tagSha) throw new Error('Published release tag is missing');
const manifests = await publishedManifests(plan.repository, entries);
const latestStable = manifests.filter((item) => item.plan.channel === 'stable').sort((a, b) => compareVersions(b.plan.version, a.plan.version))[0];
if (plan.channel === 'nightly' && latestStable && latestStable.plan.version !== plan.baseVersion) {
  throw new Error('Nightly stable base changed; start a new nightly run');
}
if (plan.channel === 'stable' && latestStable && compareVersions(plan.version, latestStable.plan.version) < 0) {
  throw new Error('A newer stable release has already been published');
}
if (manifests.some((item) => item.plan.sourceSha !== plan.sourceSha && isAncestor(plan.sourceSha, item.plan.sourceSha))) {
  throw new Error('A newer source has already been published');
}
const candidates = artifactFiles(directory);
verifyMatrix(candidates, plan);
// OCI archives are run-scoped staging; users get the image from GHCR by digest.
const files = candidates.filter((file) => !file.name.endsWith('.tar'));
const imageDigest = publishImages(plan, directory);
const manifest = createManifest(plan, files, imageDigest);
await verifyPublishedImage(manifest.image, plan);
writeChecksums(directory, files);
writeFileSync(join(directory, 'release-manifest.json'), `${JSON.stringify(manifest, null, 2)}\n`);
const summary = [
  `Source: ${plan.sourceSha}`,
  `Channel: ${plan.channel}. Desktop is a client of a separately running Peek server.`,
  `Runtime-configured server image: ${plan.imageRepository}@${imageDigest}`,
  'Native server DEB/APK packages run without Docker. Configure the Supabase backend and manage migrations separately.',
  'The APK public key is included for verification; establish its expected fingerprint through the owner before trusting it.',
  'See release-manifest.json and SHA256SUMS for artifact identity. No production deployment is performed.',
].join('\n\n');
if (!release) {
  release = await api(`repos/${plan.repository}/releases`, releaseSchema, {
    method: 'POST', body: { tag_name: plan.tag, target_commitish: plan.sourceSha, name: plan.tag, body: summary, draft: true, prerelease: plan.channel === 'nightly', make_latest: 'false' },
  });
}
const paths = [...files.map((file) => file.name), 'SHA256SUMS', 'release-manifest.json'];
if (release.assets.some((asset) => !paths.includes(asset.name))) throw new Error('Draft release contains unexpected assets');
for (const name of paths) {
  const bytes = readFileSync(join(directory, name));
  let existing = release.assets.find((asset) => asset.name === name);
  if (existing && isDraftPlaceholder(release, existing)) {
    // GitHub may leave an empty starter asset after upload HTTP 502. It is not
    // uploaded content; only this draft placeholder may be removed for retry.
    await api(`repos/${plan.repository}/releases/assets/${existing.id}`, z.null(), { method: 'DELETE' });
    existing = undefined;
  }
  if (existing) {
    // Reuse staged bytes only after an exact byte comparison; never clobber them.
    const response = await fetch(`https://api.github.com/repos/${plan.repository}/releases/assets/${existing.id}`, {
      headers: { Authorization: `Bearer ${process.env.GH_TOKEN}`, Accept: 'application/octet-stream' }, signal: AbortSignal.timeout(30_000),
    });
    if (!response.ok || !Buffer.from(await response.arrayBuffer()).equals(bytes)) throw new Error(`Existing release asset differs: ${name}; rerun the original staged publication`);
    continue;
  }
  if (!release.draft) throw new Error(`Cannot add missing asset to a published release: ${name}`);
  const response = await fetch(`https://uploads.github.com/repos/${plan.repository}/releases/${release.id}/assets?name=${encodeURIComponent(name)}`, {
    method: 'POST', headers: { Authorization: `Bearer ${process.env.GH_TOKEN}`, 'Content-Type': 'application/octet-stream', 'Content-Length': String(bytes.length) }, body: bytes,
    signal: AbortSignal.timeout(300_000),
  });
  if (!response.ok) throw new Error(`Asset upload failed for ${name}: HTTP ${response.status}`);
}
const finalTagSha = await tagSource(plan.repository, plan.tag, true);
if (finalTagSha && finalTagSha !== plan.sourceSha) throw new Error('Release tag moved during staging');
const staged = await api(`repos/${plan.repository}/releases/${release.id}`, releaseSchema);
verifyStagedAssets(staged, paths.map((name) => {
  const bytes = readFileSync(join(directory, name));
  return { name, size: bytes.length, sha256: createHash('sha256').update(bytes).digest('hex') };
}));
if (release.draft) await api(`repos/${plan.repository}/releases/${release.id}`, releaseSchema, {
  method: 'PATCH', body: { draft: false, make_latest: plan.channel === 'stable' ? 'true' : 'false' },
});
// Published manifest is the cross-service commit point; aliases are repairable.
const completed = await publishedManifests(plan.repository, await releases(plan.repository));
const published = completed.find((item) => item.plan.tag === plan.tag);
if (!published || published.image.digest !== imageDigest) throw new Error('Published release verification failed');
const currentStable = completed.filter((item) => item.plan.channel === 'stable').sort((a, b) => compareVersions(b.plan.version, a.plan.version))[0];
if (plan.channel === 'stable' && currentStable?.plan.version !== plan.version) throw new Error('Stable alias would regress');
if (plan.channel === 'nightly' && completed.some((item) => item.plan.channel === 'nightly' && item.plan.sourceSha !== plan.sourceSha && isAncestor(plan.sourceSha, item.plan.sourceSha))) throw new Error('Nightly alias would regress');
promoteAliases(planSchema.parse(plan), imageDigest);
// Parse our own serialized manifest with the same public schema before reporting success.
parseJson(z.object({ status: z.literal('complete') }), readFileSync(join(directory, 'release-manifest.json'), 'utf8'));
console.log(`Published ${plan.tag} and verified ${imageDigest}`);
