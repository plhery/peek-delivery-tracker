import 'server-only';

import { explicitOffsetTime, isoTime, settleRecognition } from 'universal-parcel-scraper';
import type { BrowserRecognition, TrackingContext } from 'universal-parcel-scraper/node';
import { createAdapterRegistry } from './adapterRegistry';
import { withLookupPurpose } from './metrics';
import { requireRoyalBrowser, usesRoyalBrowser } from './royalBrowserAdmission';

export const BROWSER_RECOGNITION_BUDGET_MS = 20_000;
/**
 * Carriers whose browser read needs longer: Onlog's guest verification, ZTO's, whose reads through the browser
 * service take 15–18 s, and Speedy's tracker page, which needs 10 s of the service's time to pass its Turnstile
 * check after the 15 s the adapter keeps for the transport.
 */
const LONGER_BROWSER_RECOGNITION_MS = new Map([['onlog-express', 40_000], ['zto', 40_000], ['speedy', 40_000]]);
/** Shared work and saved-parcel callers allow these carriers' verification time to finish. */
export function browserRecognitionWorkBudget(carrier: string): number {
  return LONGER_BROWSER_RECOGNITION_MS.get(carrier) ?? BROWSER_RECOGNITION_BUDGET_MS;
}
export const MAX_BROWSER_RECOGNITIONS = 2;

/** Every browser slot of the process is taken: refused before any browser opens, so the carrier was not asked. */
export class BrowserRecognitionBusy extends Error {
  constructor() { super('Browser recognition is busy'); this.name = 'BrowserRecognitionBusy'; }
}
const TTL_MS = 5 * 60_000;
const FAILURE_TTL_MS = 30_000;
const MAX_ENTRIES = 500;
interface BrowserRecognitionState {
  cache: Map<string, { at: number; answer?: BrowserRecognition }>;
  pending: Map<string, { controller: AbortController; users: number; promise: Promise<BrowserRecognition> }>;
}
// Next compiles the API and instrumentation worker into different module instances.
const stateKey = Symbol.for('peek.browserRecognition');
const processScope = globalThis as typeof globalThis & { [stateKey]?: BrowserRecognitionState };
const shared: BrowserRecognitionState = processScope[stateKey] ??= { cache: new Map(), pending: new Map() };
const { cache, pending } = shared;
let registry: ReturnType<typeof createAdapterRegistry> | undefined;

/**
 * Whether a read started ahead of need can take a browser without leaving the process none: it joins the same read
 * under way, or leaves a slot free for every other check.
 */
export function browserRecognitionSlotToSpare(carrier: string, number: string): boolean {
  return pending.has(`${carrier}:${number}`) || pending.size < MAX_BROWSER_RECOGNITIONS - 1;
}

function remember(key: string, answer?: BrowserRecognition) {
  cache.delete(key);
  cache.set(key, { at: Date.now(), answer });
  if (cache.size > MAX_ENTRIES) cache.delete(cache.keys().next().value!);
}

/** Shared by preflight and sync; identical requests share work until their last caller cancels. */
export async function recognizeBrowser(carrier: string, number: string, context: TrackingContext = {}, previousError?: unknown): Promise<BrowserRecognition> {
  context.signal?.throwIfAborted();
  const workBudget = browserRecognitionWorkBudget(carrier);
  const deadline = performance.now() + Math.min(context.budgetMs ?? BROWSER_RECOGNITION_BUDGET_MS, workBudget);
  const key = `${carrier}:${number}`;
  const cached = cache.get(key);
  if (cached && Date.now() - cached.at < (cached.answer ? TTL_MS : FAILURE_TTL_MS)) {
    if (!cached.answer) throw new Error('Browser recognition is cooling down');
    return structuredClone(cached.answer);
  }
  let job = pending.get(key);
  if (!job && usesRoyalBrowser(carrier)) {
    await requireRoyalBrowser(carrier, 'recognition', context);
    if (performance.now() >= deadline) throw new Error('Browser recognition budget exceeded');
    // Another caller may have started the shared job while incident state was being read.
    job = pending.get(key);
  }
  if (!job) {
    if (pending.size >= MAX_BROWSER_RECOGNITIONS) throw new BrowserRecognitionBusy();
    const controller = new AbortController();
    const adapter = (registry ??= createAdapterRegistry()).for(carrier);
    if (!adapter?.recognizeWithBrowser) throw new RangeError(`${carrier} cannot confirm through a browser`);
    const promise = Promise.resolve().then(() => withLookupPurpose('recognition', () => adapter.recognizeWithBrowser!(number, {
      // The first caller can be preflight; a joined saved-parcel caller must not inherit its shorter deadline.
      signal: controller.signal, budgetMs: workBudget,
    }, previousError))).then((answer) => {
      controller.signal.throwIfAborted();
      remember(key, structuredClone(answer));
      return answer;
    }, (error: unknown) => {
      if (!controller.signal.aborted) remember(key);
      throw error;
    }).finally(() => { if (pending.get(key)?.controller === controller) pending.delete(key); });
    job = { controller, users: 0, promise };
    pending.set(key, job);
  }
  const active = job;
  active.users++;
  return new Promise<BrowserRecognition>((resolve, reject) => {
    let finished = false;
    const finish = (answer?: BrowserRecognition, error?: unknown) => {
      if (finished) return;
      finished = true;
      clearTimeout(timer);
      context.signal?.removeEventListener('abort', abort);
      if (--active.users === 0 && pending.get(key) === active) active.controller.abort();
      if (error !== undefined) reject(error);
      else resolve(structuredClone(answer!));
    };
    const abort = () => finish(undefined, context.signal?.reason ?? new Error('Browser recognition cancelled'));
    context.signal?.addEventListener('abort', abort, { once: true });
    const timer = setTimeout(() => finish(undefined, new Error('Browser recognition budget exceeded')),
      Math.max(1, deadline - performance.now()));
    active.promise.then((answer) => finish(answer), (error: unknown) => finish(undefined, error));
    if (context.signal?.aborted) abort();
  });
}

/** Consume fresh anonymous history once; recipient credentials never share cached lookups. */
export function takeBrowserHistory(carrier: string, number: string) {
  const entry = cache.get(`${carrier}:${number}`);
  if (!entry?.answer?.known || !entry.answer.result || Date.now() - entry.at >= TTL_MS) return undefined;
  const answer = entry.answer;
  // Require a real clock before settlement: an undated shell can be known but has no reusable age.
  const instant = explicitOffsetTime(answer.lastActivityAt);
  if (!instant && !isoTime(answer.lastActivityLocal, 'UTC')) return undefined;
  if (settleRecognition([{ carrier, needsInput: null, preferred: false, status: 'known',
    lastActivityAt: instant ? answer.lastActivityAt ?? null : null, lastActivityLocal: answer.lastActivityLocal }]).carrier !== carrier) return undefined;
  const result = entry.answer.result;
  delete entry.answer.result;
  return structuredClone(result);
}
