import 'server-only';

import { AsyncLocalStorage } from 'node:async_hooks';
import * as Sentry from '@sentry/node';
import { NoHistoryError } from 'universal-parcel-scraper';
import type { LookupRecord, StepRecord, StepRecorder } from 'universal-parcel-scraper/node';
import { combineRecorders, errorTypeOf, outcomeOf } from 'universal-parcel-scraper/node';
import { healthStepRecorder } from './trackingHealth';
import { prometheusStepRecorder, stepOutcome } from './metrics';
import {
  errorDetails, initObservability, logOperationalEvent, reportRoutingEvent, reportTrackingLookup, reportTrackingStep, trackingAttemptId,
} from './observability';

/**
 * The host's telemetry sinks for the scraper's `StepRecorder`.
 *
 * Metric and log names are the ones the Sentry "Scraper Health" dashboard
 * reads: a step is a `phase`, a lookup is `phase:total`, and a recovery step
 * also counts a fallback and records a `transport_fallback` breadcrumb before
 * the result is known, unless the step it falls back from answered.
 */

function metric(operation: () => void): void {
  try {
    if (!initObservability()) return;
    operation();
  } catch { /* Telemetry must never change a tracking result. */ }
}

/** Answers about the number rather than failures: the carrier or provider was reached and replied. */
const ANSWERS = new Set(['not_found', 'no_history', 'input_required', 'invalid_input', 'handed_off']);

/**
 * Whether the step a recovery falls back from answered: China Post's prompt for phone digits, or DPD Germany placing
 * the parcel abroad. The next step follows the answer, so there is no failure to warn of.
 */
function fellBackFromAnswer(record: StepRecord): boolean {
  return record.fallbackReason === 'ok'
    || (record.fallbackReason !== null && ANSWERS.has(stepOutcome(record.fallbackReason, record.fallbackError)));
}

function outcomeLabel(record: StepRecord | LookupRecord): string {
  if (record.outcome === 'ok') return 'success';
  const outcome = stepOutcome(record.outcome, record.error);
  return ANSWERS.has(outcome) ? outcome : 'error';
}

/**
 * Why a lookup failed, as the check's audit keeps it: the carrier's reason code, or else its error's wording without
 * numbers, which tells apart the ways one kind fails (CTT Express's empty history from its unavailable endpoint).
 * A run of four or five digits, such as a postcode, is given as its length too; an HTTP status stays.
 */
function failureReason(record: LookupRecord): string | undefined {
  if (record.outcome === 'ok') return undefined;
  const { error_reason: code, error_message: message } = errorDetails(record.error);
  const reason = typeof code === 'string' && code ? code : message;
  return typeof reason === 'string' && reason
    ? reason.replace(/(?<!\d)\d{4,}(?!\d)/g, (digits) => `[${digits.length} digits]`).slice(0, 100) : undefined;
}

/** A failure's line says why: the carrier's reason code and the error's wording, with each cause's. */
function failure(outcome: string, error: unknown) {
  return outcome === 'error' ? errorDetails(error) : {};
}

export const sentryStepRecorder: StepRecorder = {
  step(record: StepRecord & { networkBytes?: number }) {
    const attributes = {
      carrier: record.carrier, phase: record.step, outcome: outcomeLabel(record), error_type: record.errorType ?? 'none',
    };
    if (record.fallbackFrom) {
      // Report before-fallback evidence even when the recovery ultimately succeeds.
      if (!fellBackFromAnswer(record)) {
        try {
          reportRoutingEvent('transport_fallback', {
            carrier: record.carrier, provider: record.carrier, category: record.step,
            errorClass: record.fallbackErrorType ?? 'none', error: record.fallbackError,
          });
        } catch { /* Recovery reporting is best effort. */ }
      }
      metric(() => Sentry.metrics.count('tracking.scrape.fallbacks', 1, {
        attributes: { carrier: record.carrier, from_phase: record.fallbackFrom!, to_phase: record.step, error_type: record.fallbackErrorType ?? 'none' },
      }));
    }
    try {
      logOperationalEvent('tracking_scrape', {
        ...attributes, duration_ms: Math.round(record.durationMs), attempt_id: trackingAttemptId(),
        attempt: record.attempt, fallback_from: record.fallbackFrom ?? undefined,
        fallback_reason: record.fallbackReason ? stepOutcome(record.fallbackReason, record.fallbackError) : undefined,
        ...(record.networkRoute ? { network_route: record.networkRoute,
          network_attempt: record.networkAttempt, network_bytes: record.networkBytes, network_family: record.networkFamily ?? 'unknown', history_available: record.historyAvailable === true, history_partial: record.historyPartial === true } : {}),
        ...failure(attributes.outcome, record.error),
      });
    } catch { /* Preserve the result even if logging fails. */ }
    metric(() => {
      Sentry.metrics.distribution('tracking.scrape.duration', record.durationMs, { unit: 'millisecond', attributes });
      Sentry.metrics.count('tracking.scrape.attempts', 1, { attributes });
    });
  },
  lookup(record: LookupRecord) {
    const attributes = {
      carrier: record.carrier, phase: 'total', outcome: outcomeLabel(record), error_type: record.errorType ?? 'none',
    };
    try {
      logOperationalEvent('tracking_scrape', {
        ...attributes, duration_ms: Math.round(record.durationMs), final_step: record.finalStep ?? 'none', attempts: record.attempts,
        attempt_id: trackingAttemptId(), ...failure(attributes.outcome, record.error),
      });
    } catch { /* Preserve the result even if logging fails. */ }
    metric(() => {
      Sentry.metrics.distribution('tracking.scrape.duration', record.durationMs, { unit: 'millisecond', attributes });
      Sentry.metrics.count('tracking.scrape.attempts', 1, { attributes });
    });
  },
};

/**
 * Tells the running check which tiers its lookups went through, such as a browser's, how each ended (a challenge,
 * a transport failure, an answer) and whom they asked.
 */
const attemptStepRecorder: StepRecorder = {
  step(record: StepRecord & { networkBytes?: number }) {
    reportTrackingStep(record.step, record.carrier, stepOutcome(record.outcome, record.error));
  },
  lookup(record: LookupRecord) { reportTrackingLookup(record.carrier, outcomeLabel(record), failureReason(record)); },
};

const extraRecorders: StepRecorder[] = [];

/** Register another sink once at startup. */
export function addStepRecorder(recorder: StepRecorder): void {
  extraRecorders.push(recorder);
}

/** The successful lookups of the carrier a tracking call asks, held back until its result is known. */
const heldLookups = new AsyncLocalStorage<{ carrier: string; records: { record: LookupRecord; sinks: StepRecorder }[] }>();

/**
 * Why Peek recorded a successful lookup as one without history (`no_history`): the carrier's answer
 * held nothing (`empty_answer`), was set aside (`set_aside_` and the router's reason, such as
 * `set_aside_local_clock`), was an earlier parcel's under a reused number (`older_parcel`), or showed
 * no movement within recognition's 60 days (`older_answer`).
 */
export type LookupVerdict = 'empty_answer' | `set_aside_${string}` | 'older_parcel' | 'older_answer';

/** Whether a lookup's reason is one of Peek's verdicts on an answer, not the carrier's own. */
export function lookupVerdict(reason: string | undefined): boolean {
  return reason === 'empty_answer' || reason === 'older_parcel' || reason === 'older_answer' || Boolean(reason?.startsWith('set_aside_'));
}

const VERDICT_MESSAGES: Record<string, string> = {
  empty_answer: 'The carrier answered with an empty result',
  older_parcel: 'The carrier answered with an older parcel for this number',
  older_answer: 'The carrier answered with no movement in recognition\'s 60 days',
};

/** A check's successful lookups, held back until it ends, with the verdict Peek gave each answer. */
type CheckLookups = { records: { record: LookupRecord; sinks: StepRecorder; verdict?: LookupVerdict }[] };
const checkLookups = new AsyncLocalStorage<CheckLookups>();

/**
 * The recorder handed to every adapter: health, Sentry, Prometheus and the running
 * check plus any registered sinks, each failure-isolated. Prometheus is wired here rather than
 * registered from a side-effect import, because every bundle that records a
 * lookup evaluates its own copy of this module and must reach the sink.
 */
export function hostStepRecorder(): StepRecorder {
  const sinks = combineRecorders(healthStepRecorder, sentryStepRecorder, prometheusStepRecorder, attemptStepRecorder, ...extraRecorders);
  return {
    step: (record) => {
      const check = checkLookups.getStore();
      if (check) release(check, record.carrier);
      sinks.step(record);
    },
    lookup: (record) => {
      const held = heldLookups.getStore();
      if (held && record.outcome === 'ok' && record.carrier === held.carrier) held.records.push({ record, sinks });
      else deliver(record, sinks);
    },
  };
}

/** A successful lookup recorded as an answer without history, as every sink records an adapter's NoHistoryError. */
function withoutHistory(record: LookupRecord, verdict: LookupVerdict): LookupRecord {
  const error = new NoHistoryError(record.carrier, VERDICT_MESSAGES[verdict] ?? 'Peek set the carrier\'s answer aside', { reason: verdict });
  return { ...record, outcome: outcomeOf(error), errorType: errorTypeOf(error), error };
}

/**
 * Hands a lookup to the sinks, or holds a successful one back for the running check. Another step or lookup
 * of the same carrier lets the carrier's held ones go first, so each carrier's records reach health in order.
 */
function deliver(record: LookupRecord, sinks: StepRecorder): void {
  const check = checkLookups.getStore();
  if (check && record.outcome === 'ok') {
    check.records.push({ record, sinks });
    return;
  }
  if (check) release(check, record.carrier);
  sinks.lookup(record);
}

function release(check: CheckLookups, carrier?: string): void {
  const going = check.records.filter((held) => carrier === undefined || held.record.carrier === carrier);
  check.records = check.records.filter((held) => !going.includes(held));
  for (const { record, sinks, verdict } of going) sinks.lookup(verdict ? withoutHistory(record, verdict) : record);
}

/**
 * Runs a check's lookups with their successful ones held back until it ends, so that the router can say which
 * answers it did not take: `markLookup` records such an answer as one without history, everywhere a lookup is
 * recorded (`no_history` in the check's lookups and trail, `tracking_scrape` and `carrier_lookup_total`, health's
 * `not_found`), and `unmarkLookups` takes the mark back when the check takes the answer after all.
 */
export async function withLookupVerdicts<T>(operation: () => Promise<T>): Promise<T> {
  const check: CheckLookups = { records: [] };
  try {
    return await checkLookups.run(check, operation);
  } finally {
    try { release(check); } catch { /* Telemetry must never change a tracking result. */ }
  }
}

/** Marks the carrier's latest successful lookup the running check holds, when unmarked, with Peek's verdict on its answer. */
export function markLookup(carrier: string, verdict: LookupVerdict): void {
  const held = [...checkLookups.getStore()?.records ?? []].reverse().find((candidate) => candidate.record.carrier === carrier);
  if (held && !held.verdict) held.verdict = verdict;
}

/** Takes back the marks on the carrier's held lookups: the check took its answer after all. */
export function unmarkLookups(carrier: string): void {
  for (const held of checkLookups.getStore()?.records ?? []) if (held.record.carrier === carrier) delete held.verdict;
}

/**
 * Runs a carrier's tracking call with its successful lookups held back from every sink until the result is known.
 * An adapter answers a number it does not know with an empty result, such as FedEx's "could not locate the
 * shipment", which the scraper counts a success: when `empty` says the result holds nothing, the lookup that gave
 * it is recorded as an answer without history (`no_history` in the check's lookups and trail and `tracking_scrape`,
 * health's `not_found`), as an adapter's NoHistoryError is. Its steps stay as they ran: the transport answered.
 */
export async function withHeldLookups<T>(carrier: string, operation: () => Promise<T>, empty: (value: T) => boolean): Promise<T> {
  const held = { carrier, records: [] as { record: LookupRecord; sinks: StepRecorder }[] };
  let answer: { value: T } | undefined;
  try {
    answer = { value: await heldLookups.run(held, operation) };
    return answer.value;
  } finally {
    let emptied = false;
    try { emptied = answer !== undefined && empty(answer.value); } catch { /* Telemetry must never change a tracking result. */ }
    const last = held.records.length - 1;
    held.records.forEach(({ record, sinks }, index) => deliver(emptied && index === last ? withoutHistory(record, 'empty_answer') : record, sinks));
  }
}
