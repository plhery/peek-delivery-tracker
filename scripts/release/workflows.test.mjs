import assert from 'node:assert/strict';
import { readFileSync } from 'node:fs';
import { test } from 'node:test';
import { parse } from 'yaml';
import { z } from 'zod';

const strings = z.record(z.string(), z.string());
const permissions = z.record(z.string(), z.enum(['read', 'write', 'none']));
const step = z.object({ uses: z.string().optional(), run: z.string().optional(), if: z.string().optional(), env: strings.optional(), with: z.record(z.string(), z.union([z.string(), z.number(), z.boolean()])).optional() });
const workflowSchema = z.object({
  on: z.object({
    pull_request: z.object({ paths: z.array(z.string()).optional() }).nullable().optional(),
    workflow_dispatch: z.object({ inputs: z.record(z.string(), z.object({ type: z.string(), default: z.union([z.string(), z.boolean()]).optional() })).optional() }).nullable().optional(),
    workflow_call: z.object({}).nullable().optional(),
    schedule: z.array(z.object({ cron: z.string() })).optional(),
  }),
  permissions,
  concurrency: z.object({ group: z.string(), 'cancel-in-progress': z.boolean() }).optional(),
  jobs: z.record(z.string(), z.object({
    uses: z.string().optional(), if: z.string().optional(), permissions: permissions.optional(),
    secrets: z.string().optional(), with: z.record(z.string(), z.union([z.string(), z.boolean()])).optional(),
    needs: z.union([z.string(), z.array(z.string())]).optional(), steps: z.array(step).optional(),
    strategy: z.object({ matrix: z.object({ include: z.array(z.object({ runner: z.string(), arch: z.string() })).optional(), format: z.array(z.enum(['deb', 'apk'])).optional() }) }).optional(),
  })),
});
const names = ['nightly', 'release', 'release-poc', 'release-build', 'release-publish'];
const workflows = Object.fromEntries(names.map((name) => [name, workflowSchema.parse(parse(readFileSync(`.github/workflows/${name}.yml`, 'utf8')))]));

test('only daily/manual entry points publish; publication is opt-in and shares one noncancelling lock', () => {
  assert.equal(workflows.nightly.on.schedule[0].cron, '17 2 * * *');
  assert.match(workflows.nightly.jobs.build.if, /github.event_name != 'schedule' \|\| vars.PEEK_RELEASE_PUBLISH_ENABLED == 'true'/);
  assert.equal(workflows.nightly.on.workflow_dispatch.inputs.publish.default, false);
  assert.equal(workflows.release.on.workflow_dispatch.inputs.publish.default, false);
  assert.deepEqual(workflows.nightly.concurrency, workflows.release.concurrency);
  assert.equal(workflows.nightly.concurrency['cancel-in-progress'], false);
  assert.match(workflows.nightly.jobs.build.with.publish, /PEEK_RELEASE_PUBLISH_ENABLED == 'true'/);
  assert.match(workflows['release-publish'].jobs.publish.if, /github.ref == 'refs\/heads\/main'/);
  assert.match(workflows['release-publish'].jobs.publish.if, /PEEK_RELEASE_PUBLISH_ENABLED == 'true'/);
});

test('fork PRs can call build workflow without secrets or write-permission ceilings', () => {
  const pr = workflows['release-poc'];
  assert.ok(pr.on.pull_request);
  assert.equal(pr.jobs.validate.with.publish, false);
  assert.equal(pr.jobs.validate.secrets, undefined);
  for (const workflow of [pr, workflows['release-build']]) {
    assert.equal(Object.values(workflow.permissions).includes('write'), false);
    for (const job of Object.values(workflow.jobs)) assert.equal(Object.values(job.permissions ?? {}).includes('write'), false);
  }
  const source = readFileSync('.github/workflows/release-poc.yml', 'utf8');
  assert.doesNotMatch(source, /pull_request_target|release-publish|secrets:/);
  for (const job of Object.values(workflows['release-build'].jobs)) {
    for (const entry of job.steps ?? []) {
      for (const value of Object.values(entry.env ?? {})) {
        if (value.includes('secrets.')) assert.ok(value.includes('inputs.publish &&') || entry.if?.includes('inputs.publish'));
      }
    }
  }
});

test('candidate jobs pin the planner source and packaging waits for every full validation gate', () => {
  const build = workflows['release-build'];
  for (const name of ['validation', 'migrations', 'browser', 'scan', 'desktop', 'image', 'server']) {
    const checkout = build.jobs[name].steps.find((entry) => entry.uses?.startsWith('actions/checkout@'));
    assert.equal(checkout.with.ref, '${{ needs.plan.outputs.source_sha }}');
  }
  for (const name of ['desktop', 'image', 'server']) assert.deepEqual(build.jobs[name].needs, ['plan', 'validation', 'migrations', 'browser', 'scan']);
  assert.equal(build.jobs.image.steps.find((entry) => entry.uses?.startsWith('docker/build-push-action@')).with.push, false);
});

test('target matrix includes native architectures and only publisher receives registry/write token', () => {
  const matrix = workflows['release-build'].jobs.desktop.strategy.matrix.include;
  assert.deepEqual(matrix.map((item) => [item.runner, item.arch]), [['macos-15', 'arm64'], ['macos-15-intel', 'x64'], ['windows-2025', 'x64'], ['ubuntu-24.04', 'x64']]);
  assert.deepEqual(workflows['release-build'].jobs.image.strategy.matrix.include.map((item) => item.arch), ['amd64', 'arm64']);
  assert.equal(workflows['release-publish'].permissions.packages, 'write');
});

test('all external actions are immutable SHA pins and all checkouts discard credentials', () => {
  for (const workflow of Object.values(workflows)) {
    for (const job of Object.values(workflow.jobs)) {
      for (const entry of job.steps ?? []) {
        if (entry.uses) assert.match(entry.uses, /^[a-zA-Z0-9_.-]+\/[a-zA-Z0-9_./-]+@[a-f0-9]{40}$/);
        if (entry.uses?.startsWith('actions/checkout@')) assert.equal(entry.with['persist-credentials'], false);
      }
    }
  }
});


test('native server DEB/APK are mandatory and APK keys arrive after the isolated build', () => {
  const build = workflows['release-build'];
  assert.deepEqual(build.jobs.server.strategy.matrix.format, ['deb', 'apk']);
  assert.ok(build.jobs.collect.needs.includes('server'));
  const steps = build.jobs.server.steps;
  const compile = steps.findIndex((entry) => entry.run?.includes('native-server.sh build'));
  const signing = steps.findIndex((entry) => entry.run?.includes('apk-key.mjs') && !entry.run.includes('clean'));
  const cleanup = steps.find((entry) => entry.run?.includes('apk-key.mjs clean'));
  assert.ok(compile >= 0 && signing > compile);
  assert.match(cleanup.if, /always\(\)/);
  assert.ok(steps.some((entry) => entry.run?.includes('native-server.sh smoke')));
  assert.ok(build.jobs.validation.steps.some((entry) => entry.run === 'node scripts/test-public-configuration.mjs'));
  const imageBuild = build.jobs.image.steps.find((entry) => entry.uses?.startsWith('docker/build-push-action@'));
  assert.doesNotMatch(imageBuild.with['build-args'], /vars\.NEXT_PUBLIC/);
});
