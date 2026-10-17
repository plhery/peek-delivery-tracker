import { execFileSync } from 'node:child_process';
import { appendFileSync, writeFileSync } from 'node:fs';
import { z } from 'zod';
import { api, isAncestor, publishedManifests, releases, verifySourceCi } from './github.mjs';
import { parseJson, planRelease, shaSchema } from './model.mjs';

const env = process.env;
const repository = z.string().regex(/^[a-zA-Z0-9_.-]+\/[a-zA-Z0-9_.-]+$/).parse(env.GITHUB_REPOSITORY);
const publish = env.PEEK_PUBLISH === 'true';
const sourceSha = shaSchema.parse(env.PEEK_SOURCE_SHA || execFileSync('git', ['rev-parse', 'HEAD'], { encoding: 'utf8' }).trim());
const main = await api(`repos/${repository}/git/ref/heads/main`, z.object({ object: z.object({ sha: shaSchema }) }));
if (publish && (env.GITHUB_REF !== 'refs/heads/main' || env.PEEK_PUBLISH_ENABLED !== 'true')) {
  throw new Error('Publication requires trusted main and PEEK_RELEASE_PUBLISH_ENABLED=true');
}
if (publish && !isAncestor(sourceSha, main.object.sha)) throw new Error('Publication source is not on main');
const manifests = await publishedManifests(repository, await releases(repository));
if (publish && manifests.some((manifest) => manifest.plan.sourceSha !== sourceSha && isAncestor(sourceSha, manifest.plan.sourceSha))) {
  throw new Error('Publication would move backwards behind an already published source');
}
const plan = planRelease({
  channel: env.PEEK_CHANNEL,
  sourceSha,
  packageVersion: parseJson(z.object({ version: z.string() }), execFileSync('git', ['show', `${sourceSha}:package.json`], { encoding: 'utf8' })).version,
  requestedVersion: env.PEEK_STABLE_VERSION,
  runNumber: env.GITHUB_RUN_NUMBER,
  runId: env.GITHUB_RUN_ID,
  runAttempt: Number(env.GITHUB_RUN_ATTEMPT),
  repository,
  imageRepository: `ghcr.io/${(env.PEEK_IMAGE_REPOSITORY || `${repository}-release-poc`).toLowerCase()}`,
  publish,
  manifests,
});
if (publish && !plan.skip) await verifySourceCi(repository, sourceSha);
writeFileSync('release-plan.json', `${JSON.stringify(plan, null, 2)}\n`);
for (const [name, value] of Object.entries({ skip: plan.skip, source_sha: sourceSha, version: plan.version, build_number: plan.buildNumber, image: plan.imageRepository })) {
  if (env.GITHUB_OUTPUT) appendFileSync(env.GITHUB_OUTPUT, `${name}=${value}\n`);
}
if (env.GITHUB_STEP_SUMMARY) appendFileSync(env.GITHUB_STEP_SUMMARY, `### ${publish ? 'Publication' : 'NONPUBLISHING / UNSIGNED'} release candidate\n\n${plan.version} · ${sourceSha}\n\n${plan.reason}\n`);
console.log(`${plan.skip ? 'SKIP' : 'BUILD'} ${plan.version} (${sourceSha})`);
