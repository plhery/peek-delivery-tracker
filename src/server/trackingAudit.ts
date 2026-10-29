import 'server-only';

import { randomUUID } from 'node:crypto';
import {
  captureOperationalError,
  captureTrackingHealth,
  flushObservability,
  errorType,
  logOperationalEvent,
  reportRoutingEvent,
  withTrackingAttempt,
  type RoutingSignal,
} from './observability';
import { recordHealthIncident, recordRefresh, recordRoutingSignal, recordSyncAnomaly } from './metrics';
import { healthMessage, healthReviewSignal, observeTrackingHealth, type HealthSample } from './trackingHealth';
import { SupabaseError, type SupabaseServiceClient } from './supabase';
import { isRecord, type JsonObject } from './types';
import { ABSTAINED_REASONS, PLACEHOLDER_NUMBER, trackingFailureCode } from './trackingFailure';
import { trackingSupportContext } from './trackingSupport';

/**
 * What began a check: the scheduled run, a scraper release's re-check, or a reader adding the
 * parcel (a lookup too), refreshing it, opening its link, or changing its carrier or inputs.
 * `package` is a reader's check queued before checks named their reason.
 */
export type SyncTrigger = 'scheduled' | 'release' | 'added' | 'refresh' | 'opened' | 'changed' | 'package';
/** Why a parcel's own check was queued (`sync_jobs.reason`). */
export type SyncReason = Exclude<SyncTrigger, 'scheduled' | 'package'>;

/** Whether someone asked for the check, or a lookup's own check: neither the schedule nor a release. */
export function askedForCheck(trigger: SyncTrigger): boolean {
  return trigger !== 'scheduled' && trigger !== 'release';
}
export type SyncStep =
  | 'selected'
  | 'fetch'
  | 'normalize'
  | 'persist_events'
  | 'persist_package'
  | 'complete';
/**
 * `updated`: the check stored a new scan or moved the parcel's stage.
 * `unchanged`: the carrier answered with the progress already saved.
 * `skipped`: the check asked no carrier and no provider, each still waiting out a failure or an input.
 */
export type SyncAuditOutcome = 'updated' | 'unchanged' | 'waiting' | 'error' | 'unsupported' | 'superseded' | 'skipped';
/** `refused`: a failed check whose carrier answered with what only the owner can correct. */
type RecordedOutcome = SyncAuditOutcome | 'refused';
export type SyncAnomalyCode =
  | 'delivered_status_conflict'
  | 'early_stage_regression'
  | 'fallback_without_progress'
  | 'future_event_timestamp'
  | 'invalid_event_timestamp'
  | 'observed_without_timestamp'
  | 'progress_disappeared'
  | 'provider_clock_offset'
  | 'scans_withdrawn'
  | 'terminal_stage_regression';

export interface SyncRunContext {
  jobId?: string | null;
  lease?: { jobId: string; workerId: string };
  /** Aborts the work: a lost lease, or shutdown with {@link WorkerShutdown}. */
  signal?: AbortSignal;
  /** Shutdown asks a scheduled run to stop before its next check and hand its job back. */
  stopping?: AbortSignal;
  trigger: SyncTrigger;
}

/**
 * Why shutdown stops the sync worker's work. A check it aborts ends as `interrupted`,
 * and the job goes back to the queue without using up a retry.
 */
export class WorkerShutdown extends Error {
  constructor() {
    super('The sync worker is shutting down');
    this.name = 'WorkerShutdown';
  }
}

export interface SyncAuditCompletion {
  outcome: SyncAuditOutcome;
  sourceCarrier?: string | null;
  providerStatus?: string | null;
  reportedStage?: string | null;
  selectedStage?: string | null;
  statusText?: string | null;
  eventsReceived?: number;
  eventsNormalized?: number;
  /** Scans stored for the first time; rewritten rows do not count. */
  eventsNew?: number;
  anomalyCodes?: SyncAnomalyCode[];
  error?: unknown;
  /** An error no exception raised, such as an answer without the progress already saved. */
  errorType?: string;
  /** Routing's `failures`, each with its `kind`, keyed by provider or carrier id: a failed refresh's evidence. */
  providerFailures?: unknown;
  /** False to leave the check out of health evaluation, as a check that asked nobody already is (`skipped`). */
  evaluateHealth?: boolean;
  /**
   * The answer without the saved progress that the parcel's own carrier gave, as an opaque id of
   * its stage and rows: the refresh sample keeps it for the next check to compare.
   */
  thinAnswer?: string;
  supportEvidence?: JsonObject;
}

type StepStatus = 'succeeded' | 'failed' | 'skipped';

interface StoredStep extends JsonObject {
  sequence: number;
  step: SyncStep;
  status: StepStatus;
  occurred_at: string;
  duration_ms: number | null;
  details: JsonObject;
  error_type: string | null;
}

function elapsedMilliseconds(startedAt: number): number {
  return Math.max(0, Math.round((performance.now() - startedAt) * 10) / 10);
}

function boundedCount(value: number | undefined): number {
  return Number.isInteger(value) && value! >= 0 ? Math.min(value!, 10_000) : 0;
}

/** Each source's failure kind as `source:kind` pairs: provider and carrier ids only, bounded. */
export function providerFailureSummary(failures: unknown): string | null {
  if (!isRecord(failures)) return null;
  const pairs = Object.entries(failures).flatMap(([source, failure]) => (
    isRecord(failure) && typeof failure.kind === 'string' ? [`${source.slice(0, 40)}:${failure.kind.slice(0, 40)}`] : []
  ));
  return pairs.length ? pairs.join(',').slice(0, 300) : null;
}

/**
 * The carrier's answers only the owner can act on: a missing input, a number it does not issue, one naming several
 * parcels, a placeholder number.
 */
const OWNER_TO_CORRECT: ReadonlySet<string> = new Set([
  'carrier:input_required', 'carrier:invalid_input', 'carrier:several_parcels', PLACEHOLDER_NUMBER,
]);

/**
 * Whether the check failed on the carrier's answer for the owner to act on, through routing or not,
 * including one routing still waits out before asking the carrier again.
 */
function refusedNumber(completion: SyncAuditCompletion, carrier: string): boolean {
  const failures = isRecord(completion.providerFailures) ? completion.providerFailures : {};
  const failure = failures[carrier];
  return (isRecord(failure) && OWNER_TO_CORRECT.has(String(failure.user_error)))
    || OWNER_TO_CORRECT.has(String(trackingFailureCode(completion.error)))
    // A placeholder number, refused by every source whatever the parcel's carrier.
    || Object.values(failures).some((other) => isRecord(other) && other.user_error === PLACEHOLDER_NUMBER);
}

/**
 * What a failed refresh adds to its health sample beyond its error class: the
 * check's anomaly codes and routing's failure per source. A check that failed
 * because its answer lost progress has no error, and one that every tier
 * deferred names no failing tier of its own.
 */
function refreshEvidence(completion: SyncAuditCompletion): JsonObject {
  const anomalies = [...new Set(completion.anomalyCodes ?? [])].join(',').slice(0, 200);
  const failures = providerFailureSummary(completion.providerFailures);
  return { ...(anomalies ? { anomaly_codes: anomalies } : {}), ...(failures ? { provider_failures: failures } : {}) };
}

function safeDetails(details: JsonObject): JsonObject {
  return Object.fromEntries(
    Object.entries(details).filter(([, value]) => (
      value === null
      || typeof value === 'string'
      || typeof value === 'number'
      || typeof value === 'boolean'
    )),
  );
}

/** A health incident's notification, as a log line and a count, whether or not Sentry has it. */
function noteHealthIncident(incident: JsonObject): void {
  const kind = String(incident.kind), subject = String(incident.subject), state = String(incident.state);
  try { recordHealthIncident(kind, subject, state); } catch { /* metrics only */ }
  logOperationalEvent('tracking_health_incident', {
    kind, subject, state, title: healthMessage(incident).title,
    failures: Number(incident.failures ?? 0), attempts: Number(incident.attempts ?? 0),
  }, healthReviewSignal(incident) ? 'info' : 'warning');
}

/** Lookup tiers that open a browser: a local Chromium, the browser service, or a page it solves. */
const BROWSER_STEPS = new Set(['browser', 'trawl', 'page']);

export class TrackingSyncAudit {
  readonly attemptId = randomUUID();
  readonly #startedAt = performance.now();
  readonly #startedAtIso: string;
  readonly #steps: StoredStep[] = [];
  #reportedWriteFailure = false;
  #ended = false;
  readonly #healthSamples = new Map<string, HealthSample>();
  /** Routing signals reported during the check, once each, saved when it completes. */
  readonly #signals = new Map<string, RoutingSignal>();
  readonly #signal = (signal: RoutingSignal): void => {
    const key = `${signal.signal}\n${signal.carrier}\n${signal.subject}`;
    if (this.#signals.has(key) || this.#signals.size >= 32) return;
    this.#signals.set(key, { signal: signal.signal, carrier: signal.carrier.slice(0, 100), subject: signal.subject.slice(0, 100) });
    try { recordRoutingSignal(signal.signal, signal.carrier, signal.subject); } catch { /* metrics only */ }
  };
  /** Whether the check's lookups went through a browser; null while no lookup tier ran. */
  #browser: boolean | null = null;
  readonly #step = (step: string): void => { this.#browser = this.#browser === true || BROWSER_STEPS.has(step); };
  /** The carriers and providers the check's lookups asked, with how each ended; a success stays. */
  readonly #lookups = new Map<string, string>();
  readonly #lookup = (carrier: string, outcome: string): void => {
    const key = carrier.slice(0, 100);
    if ((!this.#lookups.has(key) && this.#lookups.size >= 32) || this.#lookups.get(key) === 'success') return;
    this.#lookups.set(key, outcome.slice(0, 40));
  };

  constructor(
    readonly client: SupabaseServiceClient,
    readonly packageId: string,
    readonly trackingNumber: string,
    readonly configuredCarrier: string,
    readonly previousStage: string,
    readonly context: SyncRunContext,
    now = new Date(),
    /** The carrier that confirmed this number directly, if any (trackingSupport.ts). */
    readonly answeredBy: string | null = null,
  ) {
    this.#startedAtIso = now.toISOString();
  }

  observeFetch<T>(operation: () => Promise<T>): Promise<T> {
    return withTrackingAttempt(this.attemptId, () => observeTrackingHealth(this.#healthSamples, operation), this.#signal, this.#step,
      this.#lookup);
  }

  /**
   * How a carrier's lookup went in this check: `ok`, the category of its failure (such as
   * `transport` or `not_found`), or `not_asked` when the check ran none. Kinds only, never a number.
   */
  lookupOutcome(carrier: string): string {
    const sample = this.#healthSamples.get(`provider:${carrier}`);
    return !sample ? 'not_asked' : sample.recovered ? 'ok' : String(sample.details.category ?? 'unknown').slice(0, 40);
  }

  /**
   * Whether a lookup of the check opened a browser. Null when none ran, as when the check
   * took an answer another copy of the number got in the same run.
   */
  get browserLookup(): boolean | null {
    return this.#browser;
  }

  /** A routing decision taken after the fetch, such as a carrier correction, for this check. */
  reportRouting(code: string, context: Omit<Parameters<typeof reportRoutingEvent>[1], 'attemptId'>): void {
    withTrackingAttempt(this.attemptId, () => reportRoutingEvent(code, { ...context, attemptId: this.attemptId }), this.#signal);
  }

  async start(): Promise<void> {
    await this.writeAudit('start_attempt', async () => {
      await this.client.startSyncAttempt(this.attemptId, {
        job_id: this.context.jobId ?? null,
        package_id: this.packageId,
        trigger: this.context.trigger,
        configured_carrier: this.configuredCarrier,
        previous_stage: this.previousStage,
        started_at: this.#startedAtIso,
        support_context: trackingSupportContext(this.trackingNumber, this.configuredCarrier, this.answeredBy),
      }, this.context.lease);
    });
    logOperationalEvent('tracking_sync_started', this.logContext());
  }

  record(
    step: SyncStep,
    status: StepStatus,
    durationMs: number | null,
    details: JsonObject = {},
    error?: unknown,
  ): void {
    const stored: StoredStep = {
      sequence: this.#steps.length + 1,
      step,
      status,
      occurred_at: new Date().toISOString(),
      duration_ms: durationMs == null ? null : Math.max(0, Math.round(durationMs)),
      details: safeDetails(details),
      error_type: error === undefined ? null : errorType(error),
    };
    this.#steps.push(stored);
    logOperationalEvent('tracking_sync_step', {
      ...this.logContext(),
      step,
      step_status: status,
      duration_ms: stored.duration_ms,
      error_type: stored.error_type,
      ...stored.details,
    }, status === 'failed' ? 'error' : 'info');
  }

  async step<T>(
    step: SyncStep,
    operation: () => Promise<T> | T,
    details: (value: T) => JsonObject = () => ({}),
  ): Promise<T> {
    const startedAt = performance.now();
    try {
      const value = await operation();
      this.record(step, 'succeeded', elapsedMilliseconds(startedAt), details(value));
      return value;
    } catch (error) {
      this.record(step, 'failed', elapsedMilliseconds(startedAt), {}, error);
      throw error;
    }
  }

  skip(step: SyncStep, reason: string): void {
    this.record(step, 'skipped', null, { reason: reason.slice(0, 100) });
  }

  reportError(error: unknown, operation: SyncStep | string): void {
    // Fetch/normalization failures are counted at completion, after all fallbacks.
    if (operation === 'fetch' || operation === 'normalize') return;
    captureOperationalError(error, {
      component: 'tracking-sync',
      operation,
      carrier: this.configuredCarrier,
      trackingNumber: this.trackingNumber,
      attemptId: this.attemptId,
      jobId: this.context.jobId,
      trigger: this.context.trigger,
      previousStage: this.previousStage,
    });
  }

  /**
   * Anomalies are review signals, not errors: the attempt keeps their codes, and each
   * is a log line and a count. None reaches Sentry. `scans` names the stored row an
   * anomaly is about, by its `provider_event_id`, where one is.
   */
  reportAnomalies(
    anomalies: SyncAnomalyCode[],
    completion: Omit<SyncAuditCompletion, 'outcome' | 'anomalyCodes' | 'error'>,
    scans: Partial<Record<SyncAnomalyCode, string>> = {},
  ): void {
    for (const anomalyCode of new Set(anomalies)) {
      try { recordSyncAnomaly(this.configuredCarrier, anomalyCode); } catch { /* metrics only */ }
      logOperationalEvent('tracking_sync_anomaly', {
        ...this.logContext(),
        anomaly_code: anomalyCode,
        ...(scans[anomalyCode] ? { scan_id: scans[anomalyCode] } : {}),
        previous_stage: this.previousStage,
        provider_status: completion.providerStatus ?? null,
        reported_stage: completion.reportedStage ?? null,
        selected_stage: completion.selectedStage ?? null,
        events_received: completion.eventsReceived ?? null,
        events_normalized: completion.eventsNormalized ?? null,
      }, 'warning');
    }
  }

  async finish(completion: SyncAuditCompletion): Promise<void> {
    // A check that shutdown interrupted has ended, whatever its carrier answers afterwards.
    if (this.#ended) return;
    this.#ended = true;
    const failedFetch = this.#steps.some(step => step.step === 'fetch' && step.status === 'failed');
    // A number the carrier does not issue is the owner's to correct. Like a missing input, the
    // refresh was answered, and it says nothing about the carrier's health, whether the check
    // ended as an error or still waits for the carrier's first scan.
    // So is a number the carrier's own lookup filed with the missing inputs, such as an
    // Australia Post consignment of several articles: the owner has to give one parcel's number.
    const ownAnswer = this.#healthSamples.get(`provider:${this.configuredCarrier}`);
    const refused = (completion.outcome === 'error' || failedFetch) && (refusedNumber(completion, this.configuredCarrier)
      || (ownAnswer?.healthy === true && ownAnswer.details.category === 'input_required'));
    // Such a check failed for its parcel, not for its carrier: error rates leave it out.
    const outcome: RecordedOutcome = refused && completion.outcome === 'error' ? 'refused' : completion.outcome;
    if (!this.#steps.some((step) => step.step === 'complete')) {
      this.record('complete', 'succeeded', elapsedMilliseconds(this.#startedAt), {
        outcome,
        ...completion.supportEvidence,
      });
    }
    const completedAt = new Date();
    const values: JsonObject = {
      outcome,
      source_carrier: completion.sourceCarrier ?? null,
      provider_status: completion.providerStatus ?? null,
      reported_stage: completion.reportedStage ?? null,
      selected_stage: completion.selectedStage ?? null,
      status_text: completion.statusText?.slice(0, 500) || null,
      events_received: boundedCount(completion.eventsReceived),
      events_normalized: boundedCount(completion.eventsNormalized),
      events_new: boundedCount(completion.eventsNew),
      anomaly_codes: [...new Set(completion.anomalyCodes ?? [])].slice(0, 16),
      error_type: completion.error === undefined ? completion.errorType ?? null : errorType(completion.error),
      completed_at: completedAt.toISOString(),
      duration_ms: Math.round(elapsedMilliseconds(this.#startedAt)),
      lookups: Object.fromEntries(this.#lookups),
    };
    // Telemetry must never change a tracking result. A skipped check refreshed nothing: it counts in no refresh rate.
    if (outcome !== 'skipped') {
      try { recordRefresh(this.configuredCarrier, completion.sourceCarrier, outcome); } catch { /* metrics only */ }
    }
    await this.writeAudit('complete_attempt', async () => {
      const completed = await this.client.completeSyncAttempt(
        this.attemptId,
        values,
        this.#steps,
      );
      if (!completed) throw new Error('The tracking sync attempt was not running');
    });
    if (this.#signals.size) {
      await this.writeAudit('record_routing_signals', async () => {
        await this.client.recordTrackingRoutingSignals(this.attemptId, this.packageId, this.trackingNumber, [...this.#signals.values()]);
      });
    }
    if (this.context.trigger === 'scheduled' && !this.context.signal?.aborted && completion.evaluateHealth !== false
      && ['updated', 'unchanged', 'waiting', 'error'].includes(completion.outcome)) {
      // A carrier whose own lookup answered normally, repeating the thin answer of the parcel's previous
      // scheduled check, works: it withdrew scans it had shown, and only it can add them back. Like a
      // missing input, the refresh answered. The first such answer still fails, so a scraper that drops
      // scans across many parcels still opens an incident, and every check keeps the anomaly.
      const repeated = !refused && completion.thinAnswer !== undefined && ownAnswer?.healthy === true
        && ownAnswer.details.category === 'ok' && await this.previousThinAnswer() === completion.thinAnswer;
      const healthy = refused || repeated || (completion.outcome !== 'error' && !failedFetch);
      // A carrier whose adapter declined its verification on purpose, in this check or in the one whose
      // cooldown deferred it, failed no refresh while every other source answered: the check keeps its
      // lookups' samples, and the lookup incident still counts the carrier's failed ones.
      const ownFailure = isRecord(completion.providerFailures) ? completion.providerFailures[this.configuredCarrier] : undefined;
      const abstained = !healthy && (ownAnswer
        ? !ownAnswer.healthy && !ownAnswer.recovered && ABSTAINED_REASONS.has(String(ownAnswer.details.reason))
        : isRecord(ownFailure) && ownFailure.abstained === true)
        && [...this.#healthSamples.values()].every((sample) => sample === ownAnswer || sample.healthy || sample.recovered);
      const thin = completion.thinAnswer === undefined ? {} : { thin_answer: completion.thinAnswer };
      const samples: JsonObject[] = [...this.#healthSamples.values(), ...(abstained ? [] : [{
        kind: 'refresh', subject: this.configuredCarrier, healthy,
        // The failing tier's own error class is better evidence than the attempt's summary, unless
        // its lookup recovered from it.
        details: refused ? { category: 'input_required', ...refreshEvidence(completion) }
          : repeated ? { category: 'repeated_answer', ...refreshEvidence(completion), ...thin }
          : { ...(values.error_type ? { error_type: values.error_type } : {}),
            ...[...this.#healthSamples.values()].reverse().find(sample => !sample.healthy && !sample.recovered)?.details,
            ...(healthy ? {} : refreshEvidence(completion)), ...thin },
      }])];
      if (samples.length) await this.writeAudit('evaluate_health', async () => {
        const incidents = await this.client.recordTrackingHealth(this.attemptId, this.packageId, samples);
        for (const incident of incidents) noteHealthIncident(incident);
        // A recovery, a closing or a rescued direct step is a review signal: Postgres keeps it once
        // acknowledged, Sentry never sees it.
        const signals = incidents.filter(healthReviewSignal);
        const reported = incidents.filter(incident => !healthReviewSignal(incident) && captureTrackingHealth(incident));
        const ids = [...signals, ...(reported.length && await flushObservability() ? reported : [])]
          .map(incident => String(incident.id));
        if (ids.length) await this.client.ackTrackingHealth(ids);
      });
    }
    logOperationalEvent('tracking_sync_completed', {
      ...this.logContext(),
      outcome,
      duration_ms: values.duration_ms,
      source_carrier: completion.sourceCarrier ?? null,
      provider_status: completion.providerStatus ?? null,
      reported_stage: completion.reportedStage ?? null,
      selected_stage: completion.selectedStage ?? null,
      events_received: values.events_received,
      events_normalized: values.events_normalized,
      events_new: values.events_new,
      anomaly_count: (completion.anomalyCodes ?? []).length,
      error_type: values.error_type,
    }, outcome === 'error' ? 'error' : 'info');
  }

  /**
   * Ends a check that shutdown aborted: logged at once, then saved with its steps as
   * `interrupted`. Should this write come too late, the job's handoff closes the row.
   */
  async interrupt(): Promise<void> {
    if (this.#ended) return;
    this.#ended = true;
    const durationMs = Math.round(elapsedMilliseconds(this.#startedAt));
    this.record('complete', 'succeeded', durationMs, { outcome: 'interrupted' });
    logOperationalEvent('tracking_sync_completed', {
      ...this.logContext(),
      outcome: 'interrupted',
      duration_ms: durationMs,
      error_type: 'WorkerShutdown',
    });
    await this.writeAudit('complete_attempt', async () => {
      await this.client.completeSyncAttempt(this.attemptId, {
        outcome: 'interrupted',
        error_type: 'WorkerShutdown',
        completed_at: new Date().toISOString(),
        duration_ms: durationMs,
        lookups: Object.fromEntries(this.#lookups),
      }, this.#steps, { timeoutMs: 3_000 });
    });
  }

  /** The thin answer the parcel's previous scheduled check recorded; null when it recorded none or cannot be read. */
  private async previousThinAnswer(): Promise<string | null> {
    let previous: string | null = null;
    await this.writeAudit('read_thin_answer', async () => {
      previous = await this.client.previousThinAnswer(this.packageId, this.configuredCarrier);
    }, 'tracking_sync_audit_read_failed');
    return previous;
  }

  /** The parcel by its id: the line keeps only its number's shape (logOperationalEvent). */
  private logContext(): JsonObject {
    return {
      attempt_id: this.attemptId,
      job_id: this.context.jobId ?? null,
      trigger: this.context.trigger,
      carrier: this.configuredCarrier,
      package_id: this.packageId,
      tracking_number: this.trackingNumber,
    };
  }

  private async writeAudit(operation: string, write: () => Promise<void>, failure = 'tracking_sync_audit_write_failed'): Promise<void> {
    try {
      await write();
    } catch (error) {
      // Shutdown may already have closed the audit and fenced this worker out.
      if (this.context.signal?.aborted) return;
      // The audit went with its parcel: the row this write refers to is deleted.
      if (error instanceof SupabaseError && error.code === '23503') {
        logOperationalEvent('tracking_sync_audit_skipped', { ...this.logContext(), operation });
        return;
      }
      logOperationalEvent(failure, {
        ...this.logContext(),
        operation,
        error_type: errorType(error),
      }, 'error');
      if (!this.#reportedWriteFailure) {
        this.#reportedWriteFailure = true;
        captureOperationalError(error, {
          component: 'tracking-sync-audit',
          operation,
          carrier: this.configuredCarrier,
          trackingNumber: this.trackingNumber,
          attemptId: this.attemptId,
          jobId: this.context.jobId,
          trigger: this.context.trigger,
        });
      }
    }
  }
}
