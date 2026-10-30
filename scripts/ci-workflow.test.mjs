import assert from 'node:assert/strict';
import { mkdtempSync, readFileSync, rmSync } from 'node:fs';
import { tmpdir } from 'node:os';
import { join } from 'node:path';
import { it } from 'node:test';
import { execFileSync } from 'node:child_process';
import { DEPLOY_JOB, DEPLOY_STEP, HANDOFF_JOB, MAX_WAIT_MS } from './deploy-gate.mjs';

const read = file => readFileSync(new URL(`../${file}`, import.meta.url), 'utf8');

// The browser journeys run in Playwright's image, which holds only its own version's browsers.
it('runs the browser journeys in the image of the installed Playwright', () => {
  const lock = JSON.parse(read('package-lock.json')).packages;
  const image = read('.github/workflows/ci.yml').match(/mcr\.microsoft\.com\/playwright:v([0-9.]+)-noble@sha256:[0-9a-f]{64}/);
  assert.ok(image, 'ci.yml must name the Playwright image by version and digest');
  for (const name of ['playwright-core', '@playwright/test']) {
    assert.equal(lock[`node_modules/${name}`].version, image[1],
      `${name} moved without the browser image in ci.yml: use mcr.microsoft.com/playwright:v${lock[`node_modules/${name}`].version}-noble with its digest`);
  }
});

// A push to main that only changes content would otherwise cancel the native tests in progress.
it('lets a run on main finish in CI and the native apps, and replaces only a pull request\'s', () => {
  for (const workflow of ['ci.yml', 'ios.yml', 'android.yml']) {
    assert.match(read(`.github/workflows/${workflow}`), /\nconcurrency:\n {2}group: [a-z]+-\$\{\{ github\.event\.pull_request\.number \|\| github\.ref \}\}\n {2}cancel-in-progress: \$\{\{ github\.event_name == 'pull_request' \}\}\n/,
      `${workflow} must not cancel a run in progress on main`);
  }
});

const job = (workflow, id) => {
  const start = workflow.indexOf(`\n  ${id}:\n`);
  assert.ok(start >= 0, `no ${id} job`);
  const end = workflow.slice(start + 1).search(/\n {2}(?:# .*\n {2})*[a-z][a-z-]*:\n/);
  return end < 0 ? workflow.slice(start) : workflow.slice(start, start + 1 + end);
};
const step = (body, name) => {
  const start = body.indexOf(`- name: ${name}`);
  assert.ok(start >= 0, `no step ${name}`);
  return body.slice(start).split(/\n {6}- /)[0];
};

for (const repository of [
  'Dunky13/peek-delivery-tracker',
  'CustomOwner/ConfiguredImage',
  'OWNER/IMAGE',
  'plhery/peek-delivery-tracker',
  'custom-owner/configured.image',
]) {
  it(`uses a lowercase image repository for ${repository} throughout the image job`, () => {
    const image = job(read('.github/workflows/ci.yml'), 'image');
    const setup = step(image, 'Set image reference');
    assert.match(setup, /IMAGE_REPOSITORY: \$\{\{ vars\.CONTAINER_IMAGE_REPOSITORY \|\| github\.repository \}\}/);
    assert.doesNotMatch(image, /IMAGE: ghcr\.io/);
    assert.ok(image.indexOf('- name: Set image reference') < image.indexOf('- name: Build the image'));
    assert.match(step(image, 'Build the image'), /tags: \$\{\{ env\.IMAGE \}\}/);
    assert.match(step(image, 'Start the container and check it'), /"\$IMAGE"/);
    assert.match(step(image, 'Publish the image'), /docker push "\$IMAGE"/);
    const script = setup.split('run: |\n')[1];
    assert.ok(script, 'the selected repository is normalized before Docker uses it');
    const commit = 'a'.repeat(40);
    const directory = mkdtempSync(join(tmpdir(), 'peek-image-env-'));
    const envFile = join(directory, 'github.env');
    try {
      execFileSync('sh', ['-c', script], {
        encoding: 'utf8',
        env: { ...process.env, IMAGE_REPOSITORY: repository, GITHUB_SHA: commit, GITHUB_ENV: envFile },
      });
      assert.equal(readFileSync(envFile, 'utf8'), `IMAGE=ghcr.io/${repository.toLowerCase()}:${commit}\n`);
    } finally {
      rmSync(directory, { recursive: true, force: true });
    }
  });
}

// The screenshots are recorded on a Mac with Apple silicon, and Linux x86-64 draws text edges and
// blurs a little differently. GitHub's macos-<version> runners are Apple silicon.
it('checks the Android screenshots on a Mac with Apple silicon, and the core in two time zones', () => {
  const workflow = read('.github/workflows/android.yml');
  assert.match(job(workflow, 'changes'), /\n {8}run: node scripts\/ci-changes\.mjs android\.yml\n/);
  const screenshots = job(workflow, 'screenshots');
  assert.match(screenshots, /\n {4}runs-on: macos-\d+\n/);
  assert.match(screenshots, /\n {8}run: \.\/gradlew :app:testDebugUnitTest :app:verifyRoborazziDebug\n/);
  assert.equal(workflow.match(/run: .*Roborazzi/g).length, 1, 'only the Mac checks the screenshots');
  const build = job(workflow, 'build');
  for (const zone of ['UTC', 'Europe/Zurich']) {
    assert.match(build, new RegExp(`\n {10}TEST_TIME_ZONE: ${zone}\n {8}run: \\./gradlew :core:test\n`), zone);
  }
  assert.match(build, /\n {6}- run: npm run test:contract\n/);
  for (const id of ['build', 'screenshots']) {
    assert.match(job(workflow, id), /\n {4}if: needs\.changes\.outputs\.android == 'true'\n/, id);
  }
});

// An outage of the audit endpoint skips the audit; a vulnerability still fails the job.
it('audits the production dependencies through the script that tells the two apart', () => {
  const ci = read('.github/workflows/ci.yml');
  assert.match(job(ci, 'checks'), /\n {6}- run: node scripts\/npm-audit\.mjs --omit=dev\n/);
  assert.doesNotMatch(ci, /run: npm audit/);
});

// CI hands a tested commit over; deploy.yml waits for newer ones and deploys (scripts/deploy-gate.mjs).
it('hands a tested commit on main to the Deploy workflow, which alone deploys', () => {
  const ci = read('.github/workflows/ci.yml');
  const handoff = job(ci, 'deploy');
  assert.match(handoff, new RegExp(`\n {4}name: ${HANDOFF_JOB}\n`));
  for (const need of ['changes.result', 'checks.result', 'unit.result', 'browser.result', 'image.result', 'migrations.result']) {
    assert.ok(handoff.includes(`needs.${need}`), need);
  }
  assert.match(handoff, /github\.ref == 'refs\/heads\/main'/);
  assert.match(handoff, /\n {4}permissions:\n {6}contents: read\n {6}actions: write\n {4}steps:/);
  assert.match(handoff, /gh workflow run deploy\.yml --repo "\$GITHUB_REPOSITORY" --ref main -f commit="\$GITHUB_SHA"/);
  // A re-run of an older commit would replace a newer one waiting to deploy.
  assert.match(handoff, /if \[ "\$GITHUB_RUN_ATTEMPT" != 1 \] && \[ "\$current_sha" != "\$GITHUB_SHA" \]; then[^]*?exit 0\n[^]*?gh workflow run/);
  assert.doesNotMatch(ci, /secrets\.DEPLOY_SSH|group: production-deploy/);
});

it('waits in a job a newer commit queues behind, and deploys in one that is never cut off', () => {
  const workflow = read('.github/workflows/deploy.yml');
  assert.match(workflow, /\non:\n {2}workflow_dispatch:\n/);
  assert.doesNotMatch(workflow, /\n {2}(push|pull_request|workflow_run):/);
  const settle = job(workflow, 'settle');
  // The waiting run stands down by itself once the newer commit passed, and runs queued behind it
  // each get their turn, rather than being cancelled.
  assert.match(settle, /concurrency:\n {6}group: production-deploy-wait\n {6}cancel-in-progress: false\n {6}queue: max\n/);
  assert.match(settle, /run: node scripts\/deploy-gate\.mjs "--commit=\$COMMIT"/);
  const minutes = Number(settle.match(/timeout-minutes: (\d+)/)[1]);
  assert.ok(minutes * 60_000 > MAX_WAIT_MS + 5 * 60_000, 'the wait ends before the job times out');
  const deploy = job(workflow, 'deploy');
  assert.match(deploy, new RegExp(`\n {4}name: ${DEPLOY_JOB}\n {4}needs: settle\n {4}if: needs\\.settle\\.outputs\\.deploy == 'true'\n`));
  assert.match(deploy, /concurrency:\n {6}group: production-deploy\n {6}cancel-in-progress: false\n {6}queue: max\n/);
  assert.match(deploy, /COMMIT: \$\{\{ needs\.settle\.outputs\.commit \}\}/);
  assert.match(deploy, /ref: \$\{\{ needs\.settle\.outputs\.commit \}\}/);
  assert.match(step(deploy, DEPLOY_STEP), /"\$DEPLOY_SSH_TARGET" deploy "\$COMMIT"/);
  assert.ok(deploy.indexOf("- name: Check that production has this commit's migrations") < deploy.indexOf(`- name: ${DEPLOY_STEP}`));
  assert.doesNotMatch(deploy, /GITHUB_SHA/);
  // Neither a deploy queued behind another nor a re-run of the job may put back a commit that a
  // newer one replaced.
  const guard = step(deploy, 'Skip a commit that a newer one replaced');
  assert.doesNotMatch(guard, /\n {8}if:/);
  assert.match(guard, /run: node scripts\/deploy-gate\.mjs "--commit=\$COMMIT" --at-once/);
  for (const name of ["Check that production has this commit's migrations", DEPLOY_STEP, 'Wait until the public site serves this commit', 'Check the public site', 'Record the release in Sentry']) {
    assert.match(step(deploy, name), /\n {8}if: steps\.current\.outputs\.deploy != 'false'\n/, name);
  }
});

// IndexNow compares the live sitemap with the one it last took (refs/indexnow/submitted), so it
// runs once the public site served the deploy's commit, even if the smoke check then failed: the
// pages of a deploy it missed go with the next. It runs in a job of its own, which alone may push,
// and never fails.
it('tells IndexNow once the site serves the deployed commit, from a job of its own, without ever failing it', () => {
  const workflow = read('.github/workflows/deploy.yml');
  const deploy = job(workflow, 'deploy');
  assert.doesNotMatch(deploy, /indexnow|sitemap|contents: write/i);
  const live = step(deploy, 'Wait until the public site serves this commit');
  assert.match(live, /\n {8}id: live\n/);
  assert.match(live, /run: scripts\/wait-for-commit\.sh "\$DEPLOY_URL" "\$COMMIT"$/);
  assert.ok(deploy.indexOf(`- name: ${DEPLOY_STEP}`) < deploy.indexOf('- name: Wait until the public site serves this commit'));
  assert.ok(deploy.indexOf('- name: Wait until the public site serves this commit') < deploy.indexOf('- name: Check the public site'));
  assert.match(deploy, /\n {4}outputs:\n(?: {6}#.*\n)* {6}live: \$\{\{ steps\.live\.outcome == 'success' \}\}\n/);
  assert.match(workflow, /\npermissions:\n {2}contents: read\n/);
  const indexNow = job(workflow, 'indexnow');
  assert.match(indexNow, /\n {4}needs: \[settle, deploy\]\n {4}if: \$\{\{ !cancelled\(\) && needs\.deploy\.outputs\.live == 'true' \}\}\n/);
  assert.match(indexNow, /\n {4}permissions:\n {6}contents: write\n {4}steps:/);
  assert.match(indexNow, /concurrency:\n {6}group: indexnow\n {6}cancel-in-progress: false\n {6}queue: max\n/);
  assert.match(indexNow, /ref: \$\{\{ needs\.settle\.outputs\.commit \}\}/);
  const tell = step(indexNow, 'Tell IndexNow about new and redated pages');
  assert.match(tell, /\n {8}continue-on-error: true\n/);
  assert.match(tell, /run: node scripts\/indexnow\.mjs --since=refs\/indexnow\/submitted "\$DEPLOY_URL"\n/);
});

// The app names its Sentry release after the image's commit; telling Sentry about a deploy is
// optional, and must never fail or precede it.
it('records the deployed commit in Sentry after the deploy, without ever failing it', () => {
  assert.match(read('.github/workflows/ci.yml'), /IMAGE_COMMIT=\$\{\{ github\.sha \}\}/);
  const deploy = job(read('.github/workflows/deploy.yml'), 'deploy');
  const start = deploy.indexOf('- name: Record the release in Sentry');
  assert.ok(start > deploy.indexOf('- name: Check the public site'), 'the release is recorded once the site answers');
  const release = step(deploy, 'Record the release in Sentry');
  assert.match(release, /\n {8}continue-on-error: true\n/);
  assert.match(release, /SENTRY_AUTH_TOKEN: \$\{\{ secrets\.SENTRY_AUTH_TOKEN \}\}/);
  assert.match(release, /if \[ -z "\$SENTRY_AUTH_TOKEN" \] \|\| \[ -z "\$SENTRY_PROJECT" \]; then[^]*?exit 0/);
  for (const command of ['releases new "$COMMIT"', 'releases set-commits "$COMMIT" --auto',
    'deploys new --release "$COMMIT" --env production']) {
    assert.ok(release.includes(command), command);
  }
});
