// Decides when a commit that CI tested on main goes to production (deploy.yml). Every deploy
// restarts the server, and pushes come in bursts: a tested commit waits while CI tests a newer
// one pushed before the oldest commit still waiting was handed over, and stands down when that
// one passes, since its deploy includes this commit. A newer commit that fails CI, or one pushed
// later, does not hold this one back. Newer means later in main's git history, from GitHub's
// compare of two commits: CI's run times are not in that order when GitHub logs a push late.
import { execFileSync } from 'node:child_process';
import { appendFileSync } from 'node:fs';
import path from 'node:path';
import { setTimeout as delay } from 'node:timers/promises';
import { fileURLToPath } from 'node:url';

/** CI's job that hands a tested commit to deploy.yml, and deploy.yml's job and step that deploy it. */
export const HANDOFF_JOB = 'Request the deploy';
export const DEPLOY_JOB = 'Deploy';
export const DEPLOY_STEP = 'Deploy this commit';
/** The longest a tested commit waits for newer ones, counted from the oldest still waiting. */
export const MAX_WAIT_MS = 30 * 60_000;
export const POLL_MS = 30_000;

const SHA = /^[0-9a-f]{40}$/;
const completed = run => run.status === 'completed';
const time = value => Date.parse(value);

/** Whether a CI run handed its commit over: `done`, `running` (CI is asking now) or `none`. */
export function handoffState(jobs) {
  const job = jobs.find(candidate => candidate.name === HANDOFF_JOB);
  if (job?.conclusion === 'success') return 'done';
  return job && job.status !== 'completed' ? 'running' : 'none';
}

/** Whether a deploy.yml run put its commit in production. */
export function deployedBy(jobs) {
  const job = jobs.find(candidate => candidate.name === DEPLOY_JOB);
  return job?.steps?.some(step => step.name === DEPLOY_STEP && step.conclusion === 'success') ?? false;
}

/**
 * CI's runs of pushes to main, newest first: its workflow's list, and the commit's own runs from
 * the repository's list, since GitHub's list of a workflow's runs sometimes leaves a run out.
 */
export function pushRuns(listed, commitRuns = []) {
  const runs = new Map([...listed, ...commitRuns.filter(run => run.path === '.github/workflows/ci.yml')].map(run => [run.id, run]));
  return [...runs.values()].filter(run => run.event === 'push' && run.head_branch === 'main' && SHA.test(run.head_sha))
    .sort((a, b) => time(b.created_at) - time(a.created_at));
}

/**
 * The newest commit CI handed over, from its runs on main, newest first, and `commitsAfter`,
 * which gives the commits on main after one. The newest run handed over is usually the newest
 * commit, but GitHub can log a push minutes late, so a later commit's run may come first.
 */
export async function newestTested(runs, handoff, commitsAfter) {
  const tested = async run => completed(run) && await handoff(run) === 'done';
  let newest = null;
  for (const run of runs) if (await tested(run)) { newest = run; break; }
  const seen = new Set();
  while (newest && !seen.has(newest.head_sha)) {
    seen.add(newest.head_sha);
    const after = await commitsAfter(newest.head_sha);
    let later = null;
    for (const run of runs) if (after.has(run.head_sha) && await tested(run)) { later = run; break; }
    if (!later) break;
    newest = later;
  }
  return newest?.head_sha ?? null;
}

/**
 * When the oldest commit still waiting for production was handed over: deploy.yml runs
 * (newest first) after the last one that deployed. A run replaced while it waited counts.
 */
export async function waitingSince(deployRuns, selfId, deployed) {
  let since = null;
  for (const run of deployRuns) {
    if (String(run.id) === String(selfId)) since = run.created_at;
    else if (since !== null) {
      if (completed(run) && await deployed(run)) break;
      since = run.created_at;
    }
  }
  return since === null ? null : time(since);
}

/**
 * What the deploy of `commit` does now: `deploy`, `wait`, `stand-down` (a newer tested commit
 * deploys it) or `refuse` (CI did not pass it). `runs` are CI's runs of pushes to main,
 * newest first; `handoff` reads one's hand-over; `after` holds the commits on main after
 * `commit`, from git's history, which decides which runs are newer: their times can be out of
 * order. It waits only for CI runs that started by `since`, when the oldest commit still
 * waiting was handed over: a stream of later pushes would otherwise hold every deploy back
 * until the longest wait. Without `wait` it does not wait for CI.
 */
export async function decide({ commit, runs, handoff, after, now, since, wait, maxWaitMs = MAX_WAIT_MS }) {
  const own = runs.find(run => run.head_sha === commit);
  if (!own) return { action: 'stand-down', reason: `${commit} is older than CI's recent runs on main.` };
  if (await handoff(own) === 'none') return { action: 'refuse', reason: `CI on main has not passed ${commit}.` };
  const newer = runs.filter(run => after.has(run.head_sha));
  for (const run of newer) {
    if (completed(run) && await handoff(run) === 'done') {
      return { action: 'stand-down', reason: `${run.head_sha} is newer and passed CI: its deploy includes ${commit}.` };
    }
  }
  if (!wait) return { action: 'deploy', reason: 'Deploying at once, as asked.' };
  if (since !== null && now - since >= maxWaitMs) {
    return { action: 'deploy', reason: `Tested commits have waited ${Math.round(maxWaitMs / 60_000)} minutes for newer ones.` };
  }
  const testing = newer.find(run => !completed(run) && (since === null || time(run.created_at) <= since));
  if (testing) return { action: 'wait', reason: `CI has yet to finish ${testing.head_sha}, whose deploy would replace this one.` };
  return { action: 'deploy', reason: newer.some(run => !completed(run))
    ? 'CI is testing only commits pushed after the oldest waiting one was handed over.' : 'CI is testing no newer commit.' };
}

function github(endpoint, jq) {
  return JSON.parse(execFileSync('gh', ['api', endpoint, ...jq ? ['--jq', jq] : []], {
    encoding: 'utf8', maxBuffer: 16 * 1024 * 1024, timeout: 30_000, stdio: ['ignore', 'pipe', 'pipe'],
  }));
}

async function main() {
  const repository = process.env.GITHUB_REPOSITORY;
  const requested = (process.argv.find(argument => argument.startsWith('--commit=')) ?? '--commit=').slice(9);
  if (requested && !SHA.test(requested)) throw new Error(`Not a commit: ${requested}`);
  // CI names its commit and lets newer ones replace it; a run by hand deploys the newest at once.
  const wait = Boolean(requested) && !process.argv.includes('--at-once');
  const ciRuns = commit => {
    const listed = github(`repos/${repository}/actions/workflows/ci.yml/runs?branch=main&event=push&per_page=50`).workflow_runs;
    return pushRuns(listed, commit && !listed.some(run => run.head_sha === commit)
      ? github(`repos/${repository}/actions/runs?head_sha=${commit}&per_page=100`).workflow_runs : []);
  };
  const finished = new Map();
  const jobs = run => {
    const key = `${run.id}:${run.run_attempt}`;
    if (finished.has(key)) return finished.get(key);
    const list = github(`repos/${repository}/actions/runs/${run.id}/jobs?per_page=100`).jobs;
    if (completed(run)) finished.set(key, list);
    return list;
  };
  const handoff = async run => handoffState(jobs(run));
  // The commits on main after one, as `git log <commit>..main` lists them, read again at each
  // look since main moves. GitHub's compare also sends every changed file, about 1 MB for 50
  // commits: jq keeps only the commits.
  const commitsAfter = commit => {
    const shas = new Set();
    for (let page = 1; ; page += 1) {
      const { total, commits } = github(`repos/${repository}/compare/${commit}...main?per_page=100&page=${page}`,
        '{total: .total_commits, commits: [.commits[].sha]}');
      for (const sha of commits) shas.add(sha);
      if (commits.length < 100 || shas.size >= total) return shas;
    }
  };
  const started = Date.now();
  let commit = requested || null;
  let since = null;
  let decision;
  for (;;) {
    try {
      const runs = ciRuns(commit);
      const after = new Map();
      const commitsAfterOnce = sha => after.get(sha) ?? after.set(sha, commitsAfter(sha)).get(sha);
      commit ??= await newestTested(runs, handoff, commitsAfterOnce);
      if (!commit) {
        decision = { action: 'refuse', reason: 'CI on main has passed no recent commit.' };
      } else {
        if (wait) since ??= await waitingSince(github(`repos/${repository}/actions/workflows/deploy.yml/runs?per_page=30`)
          .workflow_runs, process.env.GITHUB_RUN_ID, async run => deployedBy(jobs(run))) ?? started;
        decision = await decide({ commit, runs, handoff, after: commitsAfterOnce(commit), now: Date.now(), since, wait });
      }
    } catch (error) {
      // GitHub's API failing must not hold a tested commit back for good. Deploying at once
      // without it could put an older commit back, so that waits for the job to time out.
      decision = wait && Date.now() - started >= MAX_WAIT_MS
        ? { action: 'deploy', reason: 'GitHub could not say whether a newer commit is being tested.' }
        : { action: 'wait', reason: `GitHub's API failed: ${error instanceof Error ? error.message : error}` };
    }
    console.log(decision.reason);
    if (decision.action !== 'wait') break;
    await delay(POLL_MS);
  }
  if (decision.action === 'refuse') {
    console.error(`::error::${decision.reason}`);
    process.exit(1);
  }
  const deploy = decision.action === 'deploy';
  if (process.env.GITHUB_OUTPUT) appendFileSync(process.env.GITHUB_OUTPUT, `deploy=${deploy}\ncommit=${commit}\n`);
  if (process.env.GITHUB_STEP_SUMMARY) {
    appendFileSync(process.env.GITHUB_STEP_SUMMARY,
      `${deploy ? 'Deploying' : 'Not deploying'} \`${commit}\`: ${decision.reason}\n`);
  }
}

if (process.argv[1] && path.resolve(process.argv[1]) === fileURLToPath(import.meta.url)) await main();
