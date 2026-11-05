import assert from 'node:assert/strict';
import { spawnSync } from 'node:child_process';
import { mkdtempSync, readFileSync, rmSync, writeFileSync } from 'node:fs';
import { tmpdir } from 'node:os';
import path from 'node:path';
import { fileURLToPath } from 'node:url';
import { after, describe, it } from 'node:test';
import {
  DEPLOY_JOB, DEPLOY_STEP, HANDOFF_JOB, MAX_WAIT_MS, decide, deployedBy, handoffState, newestTested, pushRuns, waitingSince,
} from './deploy-gate.mjs';

const sha = digit => digit.repeat(40);
const at = minute => new Date(Date.UTC(2026, 0, 1, 12, minute)).toISOString();
const run = (digit, minute, status = 'completed', handoff = 'done') => ({ id: Number(digit), head_sha: sha(digit), created_at: at(minute), status, handoff });
const handoff = async ({ handoff: state }) => state;
const minutes = count => Date.parse(at(0)) + count * 60_000;
// Unless a test says otherwise, git's history follows the digits: commit 2 comes after commit 1.
const DIGITS = ['1', '2', '3', '4', '5', '6', '7', '8', '9'];
const laterThan = commit => new Set(DIGITS.map(sha).filter(other => other > commit));

// 1 Nov 2026: GitHub logged the push of d7c619646 3.5 minutes after the push of its child
// c24c82029, so the parent's CI run started later. Ordered by run times, the gate stood the
// child down and put the parent live at 09:59Z.
const PARENT = 'd7c61964639d959356a95d4b3749228f52d63ae3';
const CHILD = 'c24c82029a6d022d020f5353db81829b40e3e916';
const PARENT_RUN = { id: 41290262820, head_sha: PARENT, created_at: '2026-11-01T09:46:34Z' };
const CHILD_RUN = { id: 41294323566, head_sha: CHILD, created_at: '2026-11-01T09:43:03Z' };
const MAIN_AFTER = { [PARENT]: new Set([CHILD]), [CHILD]: new Set() };

describe('a CI run', () => {
  it('handed its commit over once its hand-over job succeeded, or while it runs', () => {
    assert.equal(handoffState([{ name: HANDOFF_JOB, status: 'completed', conclusion: 'success' }]), 'done');
    assert.equal(handoffState([{ name: HANDOFF_JOB, status: 'in_progress', conclusion: null }]), 'running');
    assert.equal(handoffState([{ name: HANDOFF_JOB, status: 'completed', conclusion: 'skipped' }]), 'none');
    assert.equal(handoffState([{ name: 'Unit tests', status: 'completed', conclusion: 'success' }]), 'none');
  });

  it('deployed when the deploy step succeeded, even if a later check failed', () => {
    const job = (conclusion) => [{ name: DEPLOY_JOB, steps: [{ name: DEPLOY_STEP, conclusion }, { name: 'Check the public site', conclusion: 'failure' }] }];
    assert.equal(deployedBy(job('success')), true);
    assert.equal(deployedBy(job('skipped')), false);
    assert.equal(deployedBy([{ name: 'Wait for newer commits', steps: [] }]), false);
  });
});

describe("CI's runs on main", () => {
  const push = (digit, minute, extra = {}) => ({ ...run(digit, minute), event: 'push', head_branch: 'main', ...extra });
  it("take in the commit's own CI runs the workflow's list left out, once each, newest first", () => {
    const own = push('2', 5, { path: '.github/workflows/ci.yml' });
    const runs = pushRuns([push('1', 0)], [own, { ...own, id: 7, path: '.github/workflows/secrets.yml' }, push('1', 0, { path: '.github/workflows/ci.yml' })]);
    assert.deepEqual(runs.map(({ id }) => id), [2, 1]);
  });
  it('leave out runs other than pushes to main', () => {
    assert.deepEqual(pushRuns([push('1', 0), push('2', 1, { event: 'workflow_dispatch' }), push('3', 2, { head_branch: 'fix' })]).map(({ id }) => id), [1]);
  });
});

describe('deciding a deploy', () => {
  const base = { handoff, now: minutes(10), since: minutes(8), wait: true };
  const decideFor = options => decide({ ...base, after: laterThan(options.commit), ...options });

  it('deploys when CI tests nothing newer', async () => {
    const runs = [run('2', 5, 'completed', 'none'), run('1', 0, 'in_progress', 'running')];
    assert.equal((await decideFor({ commit: sha('1'), runs })).action, 'deploy');
  });

  it('waits while CI tests a newer commit', async () => {
    const runs = [run('3', 7, 'pending', 'none'), run('2', 5, 'in_progress', 'running'), run('1', 0)];
    const decision = await decideFor({ commit: sha('1'), runs });
    assert.equal(decision.action, 'wait');
    assert.match(decision.reason, new RegExp(sha('3')));
  });

  it('waits only for newer commits pushed before the oldest waiting one was handed over', async () => {
    // Commit 1 was handed over at minute 8: commit 3, pushed at minute 9, does not hold it back.
    const later = [run('3', 9, 'in_progress', 'none'), run('1', 0)];
    const decision = await decideFor({ commit: sha('1'), runs: later });
    assert.equal(decision.action, 'deploy');
    assert.match(decision.reason, /pushed after/);
    const burst = [run('3', 9, 'in_progress', 'none'), run('2', 7, 'in_progress', 'none'), run('1', 0)];
    assert.match((await decideFor({ commit: sha('1'), runs: burst })).reason, new RegExp(sha('2')));
  });

  it('leaves the deploy to a newer commit that passed', async () => {
    const runs = [run('3', 7, 'in_progress', 'none'), run('2', 5), run('1', 0)];
    assert.equal((await decideFor({ commit: sha('1'), runs })).action, 'stand-down');
  });

  it('deploys when the newer commits failed or change nothing the server runs', async () => {
    const runs = [run('3', 7, 'completed', 'none'), run('2', 5, 'completed', 'none'), run('1', 0)];
    assert.equal((await decideFor({ commit: sha('1'), runs })).action, 'deploy');
  });

  it('stops waiting once tested commits have waited the longest allowed', async () => {
    const runs = [run('2', 5, 'in_progress', 'none'), run('1', 0)];
    assert.equal((await decideFor({ commit: sha('1'), runs, now: minutes(8) + MAX_WAIT_MS - 1 })).action, 'wait');
    assert.equal((await decideFor({ commit: sha('1'), runs, now: minutes(8) + MAX_WAIT_MS })).action, 'deploy');
  });

  it('deploys at once when asked, but never over a newer tested commit', async () => {
    const testing = [run('2', 5, 'in_progress', 'none'), run('1', 0)];
    assert.equal((await decideFor({ commit: sha('1'), runs: testing, wait: false })).action, 'deploy');
    const passed = [run('2', 5), run('1', 0)];
    assert.equal((await decideFor({ commit: sha('1'), runs: passed, wait: false })).action, 'stand-down');
  });

  it('refuses a commit CI did not pass, and leaves one older than its recent runs', async () => {
    const runs = [run('2', 5), run('1', 0, 'completed', 'none')];
    assert.equal((await decideFor({ commit: sha('1'), runs })).action, 'refuse');
    assert.equal((await decideFor({ commit: sha('9'), runs })).action, 'stand-down');
  });

  it('picks the newest commit CI passed for a deploy by hand', async () => {
    const runs = [run('3', 7, 'in_progress', 'running'), run('2', 5, 'completed', 'none'), run('1', 0)];
    const after = async commit => laterThan(commit);
    assert.equal(await newestTested(runs, handoff, after), sha('1'));
    assert.equal(await newestTested([run('2', 5, 'completed', 'none')], handoff, after), null);
  });
});

describe('which commit is newer', () => {
  const runs = [{ ...PARENT_RUN, status: 'completed', handoff: 'done' }, { ...CHILD_RUN, status: 'completed', handoff: 'done' }];
  const base = { runs, handoff, now: Date.parse('2026-11-01T09:50:00Z'), since: Date.parse('2026-11-01T09:49:14Z'), wait: true };

  it('comes from git history, not CI run times: the child deploys though its run started first', async () => {
    const decision = await decide({ ...base, commit: CHILD, after: MAIN_AFTER[CHILD] });
    assert.equal(decision.action, 'deploy');
    assert.equal((await decide({ ...base, commit: CHILD, after: MAIN_AFTER[CHILD], wait: false })).action, 'deploy');
  });

  it("leaves the parent's deploy to the child, though the parent's run started later", async () => {
    const decision = await decide({ ...base, commit: PARENT, after: MAIN_AFTER[PARENT] });
    assert.equal(decision.action, 'stand-down');
    assert.match(decision.reason, new RegExp(`^${CHILD} is newer`));
    assert.equal((await decide({ ...base, commit: PARENT, after: MAIN_AFTER[PARENT], wait: false })).action, 'stand-down');
  });

  it('waits for a newer commit in git history whose run started before this one', async () => {
    const testing = [{ ...PARENT_RUN, status: 'completed', handoff: 'done' }, { ...CHILD_RUN, status: 'in_progress', handoff: 'none' }];
    const decision = await decide({ ...base, runs: testing, commit: PARENT, after: MAIN_AFTER[PARENT] });
    assert.equal(decision.action, 'wait');
    assert.match(decision.reason, new RegExp(CHILD));
  });

  it('picks the child for a deploy by hand, and follows git through several commits', async () => {
    assert.equal(await newestTested(runs, handoff, async commit => MAIN_AFTER[commit]), CHILD);
    // Runs listed 3, 1, 2 by time; git's order is 1, 2, 3, then 4, which failed CI.
    const shuffled = [run('4', 9, 'completed', 'none'), run('3', 2), run('1', 5), run('2', 7)];
    const order = { [sha('1')]: new Set([sha('2'), sha('3'), sha('4')]), [sha('2')]: new Set([sha('3'), sha('4')]), [sha('3')]: new Set([sha('4')]) };
    assert.equal(await newestTested(shuffled, handoff, async commit => order[commit]), sha('3'));
  });
});

describe('how long tested commits have waited', () => {
  const deployed = async ({ deployed: value }) => value;
  const deploy = (id, minute, value, status = 'completed') => ({ id, created_at: at(minute), status, deployed: value });

  it('counts from the oldest hand-over since the last deploy', async () => {
    const runs = [deploy(5, 9, false, 'pending'), deploy(4, 7, false, 'in_progress'), deploy(3, 5, false), deploy(2, 3, false), deploy(1, 0, true)];
    assert.equal(await waitingSince(runs, 4, deployed), minutes(3));
  });

  it('counts from its own hand-over right after a deploy, and is unknown without it', async () => {
    const runs = [deploy(2, 4, false, 'in_progress'), deploy(1, 0, true)];
    assert.equal(await waitingSince(runs, 2, deployed), minutes(4));
    assert.equal(await waitingSince(runs, 7, deployed), null);
  });
});

describe('the command', () => {
  const directory = mkdtempSync(path.join(tmpdir(), 'deploy-gate-'));
  after(() => rmSync(directory, { recursive: true, force: true }));
  // Answers `gh api <endpoint>` from the fixture in GH_FIXTURE.
  writeFileSync(path.join(directory, 'gh'), `#!/usr/bin/env node
const fixture = JSON.parse(process.env.GH_FIXTURE);
const endpoint = process.argv[3];
const key = Object.keys(fixture).find(prefix => endpoint.startsWith(prefix));
if (!key) { console.error('unexpected ' + endpoint); process.exit(1); }
process.stdout.write(JSON.stringify(fixture[key]));
`, { mode: 0o755 });
  const ciRun = (digit, minute, status = 'completed') => ({ id: Number(digit), run_attempt: 1, event: 'push', head_branch: 'main', head_sha: sha(digit), created_at: at(minute), status });
  const passed = { jobs: [{ name: HANDOFF_JOB, status: 'completed', conclusion: 'success' }] };
  const failed = { jobs: [{ name: HANDOFF_JOB, status: 'completed', conclusion: 'skipped' }] };
  // GitHub's compare, as the gate's jq filter leaves it: the commits on main after one.
  const compare = (after) => Object.fromEntries(Object.entries(after).map(([commit, shas]) =>
    [`repos/owner/app/compare/${commit}...main?per_page=100&page=1`, { total: shas.size, commits: [...shas] }]));
  const gate = (args, { runs, jobs, commitRuns = [], after = Object.fromEntries(DIGITS.map(sha).map(commit => [commit, laterThan(commit)])), extra = {} }) => {
    const output = path.join(directory, `output-${Math.random()}`);
    writeFileSync(output, '');
    const fixture = {
      ...compare(after),
      ...extra,
      'repos/owner/app/actions/workflows/ci.yml/runs': { workflow_runs: runs },
      'repos/owner/app/actions/runs?head_sha=': { workflow_runs: commitRuns },
      'repos/owner/app/actions/workflows/deploy.yml/runs': { workflow_runs: [{ id: 50, created_at: at(9), status: 'in_progress' }] },
      ...Object.fromEntries(Object.entries(jobs).map(([id, value]) => [`repos/owner/app/actions/runs/${id}/jobs`, value])),
    };
    const result = spawnSync(process.execPath, [fileURLToPath(new URL('./deploy-gate.mjs', import.meta.url)), ...args], {
      encoding: 'utf8',
      env: { PATH: `${directory}${path.delimiter}${process.env.PATH}`, GH_FIXTURE: JSON.stringify(fixture),
        GITHUB_REPOSITORY: 'owner/app', GITHUB_RUN_ID: '50', GITHUB_OUTPUT: output },
    });
    return { status: result.status, output: readFileSync(output, 'utf8'), log: result.stdout + result.stderr };
  };

  it('deploys the commit CI handed over when nothing newer is being tested', () => {
    const result = gate([`--commit=${sha('2')}`], { runs: [ciRun('3', 6), ciRun('2', 3), ciRun('1', 0)], jobs: { 3: failed, 2: passed, 1: passed } });
    assert.equal(result.status, 0, result.log);
    assert.equal(result.output, `deploy=true\ncommit=${sha('2')}\n`);
  });

  it('finds the run of a commit CI handed over that its workflow\'s list leaves out', () => {
    const own = { ...ciRun('3', 6), path: '.github/workflows/ci.yml' };
    const result = gate([`--commit=${sha('3')}`], { runs: [ciRun('2', 3), ciRun('1', 0)], jobs: { 3: passed, 2: passed, 1: passed },
      commitRuns: [own, { ...own, id: 4, path: '.github/workflows/secrets.yml' }] });
    assert.equal(result.status, 0, result.log);
    assert.equal(result.output, `deploy=true\ncommit=${sha('3')}\n`);
  });

  it('deploys the newest tested commit when run by hand', () => {
    const result = gate(['--commit='], { runs: [ciRun('3', 6, 'in_progress'), ciRun('2', 3), ciRun('1', 0)], jobs: { 3: failed, 2: passed, 1: passed } });
    assert.equal(result.status, 0, result.log);
    assert.equal(result.output, `deploy=true\ncommit=${sha('2')}\n`);
  });

  it('deploys a re-run commit when the commits after it handed nothing over, and stands it down when one passed', () => {
    // A web change whose first run failed passes on a re-run after a documentation push, whose run hands nothing over.
    const runs = [ciRun('2', 6), { ...ciRun('1', 0), run_attempt: 2 }];
    for (const args of [[`--commit=${sha('1')}`], [`--commit=${sha('1')}`, '--at-once']]) {
      const result = gate(args, { runs, jobs: { 2: failed, 1: passed } });
      assert.equal(result.status, 0, result.log);
      assert.equal(result.output, `deploy=true\ncommit=${sha('1')}\n`);
      assert.equal(gate(args, { runs, jobs: { 2: passed, 1: passed } }).output, `deploy=false\ncommit=${sha('1')}\n`);
    }
  });

  it('stops a re-run of a commit a newer one replaced, without waiting', () => {
    const result = gate([`--commit=${sha('1')}`, '--at-once'], { runs: [ciRun('3', 6, 'in_progress'), ciRun('2', 3), ciRun('1', 0)], jobs: { 3: failed, 2: passed, 1: passed } });
    assert.equal(result.status, 0, result.log);
    assert.equal(result.output, `deploy=false\ncommit=${sha('1')}\n`);
  });

  it('deploys the commit that is newer in git history, not the one whose run started later', () => {
    const ciRuns = [{ ...ciRun('8', 0), ...PARENT_RUN }, { ...ciRun('9', 0), ...CHILD_RUN }];
    const options = { runs: ciRuns, jobs: { [PARENT_RUN.id]: passed, [CHILD_RUN.id]: passed }, after: MAIN_AFTER };
    const child = gate([`--commit=${CHILD}`], options);
    assert.equal(child.status, 0, child.log);
    assert.equal(child.output, `deploy=true\ncommit=${CHILD}\n`);
    const parent = gate([`--commit=${PARENT}`], options);
    assert.equal(parent.status, 0, parent.log);
    assert.equal(parent.output, `deploy=false\ncommit=${PARENT}\n`);
    assert.match(parent.log, new RegExp(`${CHILD} is newer and passed CI`));
    assert.equal(gate([`--commit=${PARENT}`, '--at-once'], options).output, `deploy=false\ncommit=${PARENT}\n`);
    assert.equal(gate(['--commit='], options).output, `deploy=true\ncommit=${CHILD}\n`);
  });

  it('pages through a long comparison', () => {
    // Commit 1 has 150 commits after it on main; the passed commit 2 is on the second page.
    const filler = Array.from({ length: 149 }, (_, index) => index.toString(16).padStart(40, 'f'));
    const fixture = {
      [`repos/owner/app/compare/${sha('1')}...main?per_page=100&page=1`]: { total: 150, commits: filler.slice(0, 100) },
      [`repos/owner/app/compare/${sha('1')}...main?per_page=100&page=2`]: { total: 150, commits: [...filler.slice(100), sha('2')] },
    };
    const result = gate([`--commit=${sha('1')}`, '--at-once'], { runs: [ciRun('1', 6), ciRun('2', 3)], jobs: { 2: passed, 1: passed }, after: {}, extra: fixture });
    assert.equal(result.status, 0, result.log);
    assert.equal(result.output, `deploy=false\ncommit=${sha('1')}\n`);
  });

  it('fails for a commit CI did not pass, or that is not one', () => {
    const runs = { runs: [ciRun('1', 0)], jobs: { 1: failed } };
    assert.equal(gate([`--commit=${sha('1')}`], runs).status, 1);
    assert.equal(gate(['--commit=main'], runs).status, 1);
  });
});
