import { execFileSync } from 'node:child_process';
import { createHash } from 'node:crypto';
import { z } from 'zod';
import { digestSchema, manifestSchema, parseJson, shaSchema } from './model.mjs';

export const releaseSchema = z.object({
  id: z.number().int().positive(),
  tag_name: z.string(),
  draft: z.boolean(),
  prerelease: z.boolean(),
  target_commitish: z.string(),
  assets: z.array(z.object({
    id: z.number().int().positive(), name: z.string(), size: z.number().int().nonnegative(), state: z.enum(['uploaded', 'starter']),
    browser_download_url: z.string().url(), digest: z.string().nullable().optional(),
  })),
});

export async function api(path, schema, { method = 'GET', body, token = process.env.GH_TOKEN, missingOkay = false } = {}) {
  const response = await fetch(`https://api.github.com/${path}`, {
    method,
    headers: {
      Accept: 'application/vnd.github+json',
      'X-GitHub-Api-Version': '2022-11-28',
      ...(token ? { Authorization: `Bearer ${token}` } : {}),
      ...(body ? { 'Content-Type': 'application/json' } : {}),
    },
    ...(body ? { body: JSON.stringify(body) } : {}),
    signal: AbortSignal.timeout(30_000),
  });
  if (missingOkay && response.status === 404) return null;
  if (!response.ok) throw new Error(`GitHub API ${method} ${path}: HTTP ${response.status}`);
  if (response.status === 204) return schema.parse(null);
  return parseJson(schema, await response.text());
}

export async function releases(repository) {
  const all = [];
  for (let page = 1; ; page += 1) {
    const batch = await api(`repos/${repository}/releases?per_page=100&page=${page}`, z.array(releaseSchema));
    all.push(...batch);
    if (batch.length < 100) return all;
  }
}

export async function publishedManifests(repository, entries) {
  const manifests = [];
  for (const release of entries.filter((entry) => !entry.draft)) {
    const asset = release.assets.find((item) => item.name === 'release-manifest.json');
    if (!asset) continue;
    const url = new URL(asset.browser_download_url);
    if (url.origin !== 'https://github.com' || !url.pathname.startsWith(`/${repository}/releases/download/`)) {
      throw new Error('Release manifest URL is outside this repository');
    }
    const response = await fetch(url, { signal: AbortSignal.timeout(30_000) });
    if (!response.ok) throw new Error(`Cannot read published manifest: HTTP ${response.status}`);
    const manifest = parseJson(manifestSchema, await response.text());
    if (manifest.plan.tag !== release.tag_name || manifest.plan.repository !== repository || !manifest.signed || !manifest.plan.publish) {
      throw new Error('Published release manifest identity/signature policy mismatch');
    }
    if (release.prerelease !== (manifest.plan.channel === 'nightly')) throw new Error('Release channel mismatch');
    if (release.target_commitish !== manifest.plan.sourceSha || await tagSource(repository, release.tag_name) !== manifest.plan.sourceSha) {
      throw new Error('Published release tag does not resolve to its declared source');
    }
    for (const file of manifest.files) {
      const published = release.assets.find((item) => item.name === file.name);
      if (!published || published.state !== 'uploaded' || published.size !== file.size || published.digest !== `sha256:${file.sha256}`) {
        throw new Error(`Published asset is missing or mismatched: ${file.name}`);
      }
    }
    const checksums = release.assets.find((item) => item.name === 'SHA256SUMS');
    if (!checksums) throw new Error('Published checksums are missing');
    const checksumUrl = new URL(checksums.browser_download_url);
    if (checksumUrl.origin !== 'https://github.com' || !checksumUrl.pathname.startsWith(`/${repository}/releases/download/`)) throw new Error('Checksums URL is outside this repository');
    const checksumResponse = await fetch(checksumUrl, { signal: AbortSignal.timeout(30_000) });
    if (!checksumResponse.ok || await checksumResponse.text() !== manifest.files.map((file) => `${file.sha256}  ${file.name}\n`).join('')) throw new Error('Published checksums mismatch');
    await verifyPublishedImage(manifest.image, manifest.plan);
    manifests.push(manifest);
  }
  return manifests;
}

export async function tagSource(repository, tag, missingOkay = false) {
  const objectSchema = z.object({ object: z.object({ type: z.enum(['commit', 'tag']), sha: shaSchema }) });
  let reference = await api(`repos/${repository}/git/ref/tags/${encodeURIComponent(tag)}`, objectSchema, { missingOkay });
  if (!reference) return null;
  for (let depth = 0; depth < 5; depth += 1) {
    if (reference.object.type === 'commit') return reference.object.sha;
    reference = await api(`repos/${repository}/git/tags/${reference.object.sha}`, objectSchema);
  }
  throw new Error('Release tag has excessive annotated-tag nesting');
}

export async function verifyPublishedImage(image, plan) {
  const name = image.repository.slice('ghcr.io/'.length);
  const tokenResponse = await fetch(`https://ghcr.io/token?service=ghcr.io&scope=${encodeURIComponent(`repository:${name}:pull`)}`, { signal: AbortSignal.timeout(30_000) });
  if (!tokenResponse.ok) throw new Error('Release image is not anonymously accessible; owner must configure GHCR visibility');
  const { token } = parseJson(z.object({ token: z.string().min(1) }), await tokenResponse.text());
  const readContent = async (digest, kind = 'manifests') => {
    const result = await fetch(`https://ghcr.io/v2/${name}/${kind}/${digest}`, {
      headers: { Authorization: `Bearer ${token}`, Accept: 'application/vnd.oci.image.index.v1+json, application/vnd.docker.distribution.manifest.list.v2+json, application/vnd.oci.image.manifest.v1+json, application/vnd.docker.distribution.manifest.v2+json' },
      signal: AbortSignal.timeout(30_000),
    });
    if (!result.ok) throw new Error(`Published OCI manifest is unavailable: HTTP ${result.status}`);
    const text = await result.text();
    if (`sha256:${createHash('sha256').update(text).digest('hex')}` !== digest) throw new Error('Published OCI manifest content digest mismatch');
    return text;
  };
  const index = parseJson(z.object({ manifests: z.array(z.object({ digest: digestSchema, platform: z.object({ os: z.literal('linux'), architecture: z.enum(['amd64', 'arm64']) }) })).length(2) }), await readContent(image.digest));
  for (const architecture of ['amd64', 'arm64']) {
    const descriptors = index.manifests.filter((item) => item.platform.architecture === architecture);
    if (descriptors.length !== 1) throw new Error(`Published OCI platform missing or duplicated: ${architecture}`);
    const platformManifest = parseJson(z.object({ config: z.object({ digest: digestSchema }), layers: z.array(z.object({ digest: digestSchema })).min(1) }), await readContent(descriptors[0].digest));
    const config = parseJson(z.object({
      architecture: z.enum(['amd64', 'arm64']), os: z.literal('linux'),
      config: z.object({ Labels: z.object({
        'org.opencontainers.image.revision': shaSchema,
        'org.opencontainers.image.version': z.string(),
        'org.opencontainers.image.source': z.string().url(),
      }) }),
    }), await readContent(platformManifest.config.digest, 'blobs'));
    if (config.architecture !== architecture) throw new Error(`OCI config architecture mismatch: ${architecture}`);
    const labels = config.config.Labels;
    if (labels['org.opencontainers.image.revision'] !== plan.sourceSha || labels['org.opencontainers.image.version'] !== plan.version || labels['org.opencontainers.image.source'] !== `https://github.com/${plan.repository}`) {
      throw new Error('OCI config source/version identity mismatch');
    }
  }
}

export function isDraftPlaceholder(release, asset) {
  return release.draft && asset.state === 'starter' && asset.size === 0;
}

export function verifyStagedAssets(release, expected) {
  if (release.assets.length !== expected.length) throw new Error('Staged release has missing or unexpected assets');
  for (const file of expected) {
    const asset = release.assets.find((item) => item.name === file.name);
    if (!asset || asset.state !== 'uploaded' || asset.size !== file.size || asset.digest !== `sha256:${file.sha256}`) {
      throw new Error(`Staged asset is incomplete or mismatched: ${file.name}`);
    }
  }
}

export function isAncestor(ancestor, descendant) {
  try {
    execFileSync('git', ['merge-base', '--is-ancestor', ancestor, descendant], { stdio: 'pipe' });
    return true;
  } catch (error) {
    if (error.status === 1) return false;
    throw error;
  }
}

const runsSchema = z.object({ workflow_runs: z.array(z.object({
  id: z.number().int().positive(), head_sha: z.string(), head_branch: z.string().nullable(),
  event: z.string(), status: z.string(), conclusion: z.string().nullable(),
})) });

export async function verifySourceCi(repository, sha) {
  for (const workflow of ['ci.yml', 'secrets.yml']) {
    const result = await api(`repos/${repository}/actions/workflows/${workflow}/runs?head_sha=${sha}&event=push&per_page=100`, runsSchema);
    const run = result.workflow_runs.find((item) => item.head_sha === sha && item.head_branch === 'main' && item.event === 'push');
    if (!run || run.status !== 'completed' || run.conclusion !== 'success') {
      throw new Error(`${workflow} has no completed successful main push run for exact SHA ${sha}`);
    }
  }
}
