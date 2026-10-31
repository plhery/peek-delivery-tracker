import 'server-only';

import { AsyncLocalStorage } from 'node:async_hooks';
import * as Sentry from '@sentry/node';
import type { Breadcrumb, ErrorEvent } from '@sentry/node';
import { carrierErrorReason } from './trackingFailure';
import { healthMessage, healthReviewSignal } from './trackingHealth';
import type { JsonObject } from './types';
import { UpstreamHttpError, type UpstreamHttpDiagnostics } from 'universal-parcel-scraper/node';

/**
 * What a check learnt that the catalog, detection or the adapters could act on.
 * Product signals for the review queues, not errors: they are kept with the
 * check's audit and counted, never reported to Sentry.
 */
export const ROUTING_SIGNALS = [
  'carrier_coverage_discovered', 'carrier_auto_swapped', 'carrier_mismatch_confirmed', 'direct_support_opportunity',
] as const;
export type RoutingSignalCode = typeof ROUTING_SIGNALS[number];

/** `subject` is what the signal is about: a carrier a provider named, or a carrier id. */
export interface RoutingSignal {
  signal: RoutingSignalCode;
  carrier: string;
  subject: string;
}

interface TrackingAttempt {
  id: string;
  signal?: (signal: RoutingSignal) => void;
  step?: (step: string) => void;
  lookup?: (carrier: string, outcome: string) => void;
}

/** The tracking check whose lookups are running, so their scrape and routing lines name its attempt. */
const trackingAttempts = new AsyncLocalStorage<TrackingAttempt>();

/**
 * `signal` receives the routing signals reported while the operation runs, `step`
 * each lookup tier that runs (`direct`, `trawl` and the like), and `lookup` each
 * carrier or provider asked, with how its lookup ended.
 */
export function withTrackingAttempt<T>(
  attemptId: string, operation: () => T, signal?: (signal: RoutingSignal) => void, step?: (step: string) => void,
  lookup?: (carrier: string, outcome: string) => void,
): T {
  return trackingAttempts.run({ id: attemptId, signal, step, lookup }, operation);
}

export function trackingAttemptId(): string | undefined {
  return trackingAttempts.getStore()?.id;
}

/** Tells the running check which lookup tier ran. */
export function reportTrackingStep(step: string): void {
  trackingAttempts.getStore()?.step?.(step);
}

/** Tells the running check which carrier or provider a lookup asked, and how it ended. */
export function reportTrackingLookup(carrier: string, outcome: string): void {
  trackingAttempts.getStore()?.lookup?.(carrier, outcome);
}

/**
 * A number's shape without the number, as reports show it: "MY + 10 digits + US".
 * Runs of up to three letters stay, longer ones and every digit run are counted.
 * `tracking_number_pattern` in Postgres gives the same answer.
 */
export function numberPattern(number: string): string {
  const runs = number.toUpperCase().replace(/[^A-Z0-9]/g, '').match(/[0-9]+|[A-Z]+/g) ?? [];
  return runs.map((run) => (/^[0-9]/.test(run) ? `${run.length} ${run.length === 1 ? 'digit' : 'digits'}`
    : run.length <= 3 ? run : `${run.length} letters`)).join(' + ');
}

/**
 * The build puts this module in more than one server bundle (instrumentation
 * and the routes), each with its own module state, while Sentry keeps one
 * client for the whole process. The flag therefore lives on the process too:
 * a second Sentry.init would instrument the HTTP server again, and the two
 * copies would then wrap its `emit` anew on every request until the stack
 * overflows a few hours later.
 */
const runtime = globalThis as typeof globalThis & { __deliveryObservabilityInitialized?: boolean };

export interface OperationalContext {
  component: string;
  operation: string;
  carrier?: string | null;
  trackingNumber?: string | null;
  anomalyCode?: string | null;
  attemptId?: string | null;
  jobId?: string | null;
  trigger?: string | null;
  route?: string | null;
  routeType?: string | null;
  requestId?: string | null;
  failureCount?: number | null;
  durationMs?: number | null;
  eventsReceived?: number | null;
  eventsNormalized?: number | null;
  previousStage?: string | null;
  providerStatus?: string | null;
  reportedStage?: string | null;
  selectedStage?: string | null;
  /** Leave the incoming request out of the report: its address or body carries a capability. */
  withoutRequest?: boolean;
}

export interface ScheduledCheckIn {
  checkInId: string;
  monitorSlug: string;
  startedAt: number;
}

export interface OperationalErrorMetadata {
  upstreamStatus?: number;
  databaseStatus?: number;
  databaseCode?: string;
  providerFailureReason?: string;
  providerCode?: number;
  upstreamHttp?: UpstreamHttpDiagnostics;
  retryAfterMs?: number;
}

export function errorType(error: unknown): string {
  if (
    error instanceof Error
    && /^(?:Error|[A-Z][A-Za-z0-9_.-]{0,80}(?:Error|Exception))$/.test(error.name.trim())
  ) {
    return error.name.trim();
  }
  if (error instanceof Error) return 'Error';
  return typeof error;
}

export function parseSampleRate(value: string | undefined, fallback = 0): number {
  if (value == null || value.trim() === '') return fallback;
  const parsed = Number(value);
  return Number.isFinite(parsed) && parsed >= 0 && parsed <= 1 ? parsed : fallback;
}

/** The full commit the image was built from, in lower case, or undefined outside an image. */
export function imageCommit(environment: Record<string, string | undefined> = process.env): string | undefined {
  const commit = environment.IMAGE_COMMIT?.trim();
  return commit && /^[0-9a-f]{40}$/i.test(commit) ? commit.toLowerCase() : undefined;
}

export function resolveSentryRelease(
  environment: Record<string, string | undefined> = process.env,
): string | undefined {
  const commit = imageCommit(environment);
  if (commit) return commit;
  const configured = environment.SENTRY_RELEASE?.trim();
  if (!configured || /^(?:head|latest|unknown)$/i.test(configured)) return undefined;
  return configured.slice(0, 200);
}

function boundedText(value: unknown, maximum = 200): string | undefined {
  if (value == null) return undefined;
  const text = String(value).trim().slice(0, maximum);
  return text || undefined;
}

function safeNumber(value: number | null | undefined): number | undefined {
  return typeof value === 'number' && Number.isFinite(value) ? value : undefined;
}

function safeHttpStatus(value: unknown): number | undefined {
  return typeof value === 'number'
    && Number.isInteger(value)
    && value >= 100
    && value <= 599
    ? value
    : undefined;
}

function safeDatabaseCode(value: unknown): string | undefined {
  return typeof value === 'string' && /^[A-Za-z0-9_.-]{1,40}$/.test(value)
    ? value
    : undefined;
}

export function operationalErrorMetadata(error: unknown): OperationalErrorMetadata {
  const metadata: OperationalErrorMetadata = {};
  const seen = new Set<Error>();
  let current = error;
  for (let depth = 0; current instanceof Error && depth < 8; depth += 1) {
    if (seen.has(current)) break;
    seen.add(current);
    if (current instanceof UpstreamHttpError) {
      metadata.upstreamHttp ??= current.diagnostics;
      metadata.retryAfterMs ??= current.retryAfterMs;
    }
    const details = current as Error & { status?: unknown; code?: unknown; reason?: unknown; providerCode?: unknown };
    if (['UpstreamHttpError', 'DHLEcommerceSessionError'].includes(current.name) && metadata.upstreamStatus === undefined) {
      metadata.upstreamStatus = safeHttpStatus(details.status);
    }
    if (['TrackingCaptureError', 'SeventeenTrackLookupError', 'SeventeenTrackNoHistoryError', 'SeventeenTrackVerificationError'].includes(current.name)) {
      if (typeof details.reason === 'string' && ['capture_missing', 'capture_unreadable', 'history_missing',
        'verification_required', 'lookup_unavailable', 'lookup_pending', 'no_history'].includes(details.reason)) {
        metadata.providerFailureReason ??= details.reason;
      }
      if (typeof details.providerCode === 'number' && Number.isInteger(details.providerCode)
        && details.providerCode >= -100 && details.providerCode <= 999) metadata.providerCode ??= details.providerCode;
    }
    if (current.name === 'SupabaseError') {
      if (metadata.databaseStatus === undefined) {
        metadata.databaseStatus = safeHttpStatus(details.status);
      }
      if (metadata.databaseCode === undefined) {
        metadata.databaseCode = safeDatabaseCode(details.code);
      }
    }
    current = current.cause;
  }
  return metadata;
}

const UUID = /^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$/i;
const ADDRESS = /\bhttps?:\/\/[^\s"'<>\\`]+/gi;

/** A path without query or fragment, where a segment that could be a tracking number reads `:id`. */
function scrubPath(path: string): string {
  return path.split(/[?#]/, 1)[0].split('/')
    .map((segment) => (segment.length >= 6 && /\d/.test(segment) && !UUID.test(segment) ? ':id' : segment))
    .join('/');
}

function scrubAddress(address: string): string {
  const origin = /^https?:\/\/[^/?#]*/i.exec(address)?.[0] ?? '';
  return origin + scrubPath(address.slice(origin.length));
}

/** Every address loses its query and its number-like path segments. */
function scrubText(text: string): string {
  return text.replace(ADDRESS, scrubAddress);
}

/** A copy with every string scrubbed. Events arrive normalized; breadcrumb data may not be. */
function scrubValue<T>(value: T, scrub: (text: string) => string, ancestors = new Set<object>()): T {
  if (typeof value === 'string') return scrub(value) as T;
  if (!value || typeof value !== 'object') return value;
  if (ancestors.has(value) || ancestors.size > 20) return '[Truncated]' as T;
  ancestors.add(value);
  const copy = Array.isArray(value)
    ? value.map((item: unknown) => scrubValue(item, scrub, ancestors))
    : Object.fromEntries(Object.entries(value).map(([key, item]) => [scrub(key), scrubValue(item, scrub, ancestors)]));
  ancestors.delete(value);
  return copy as T;
}

/**
 * The last step before an event leaves: addresses are cut down wherever they appear,
 * and the incoming request keeps neither its body nor its query.
 */
export function scrubSentryEvent(event: ErrorEvent): ErrorEvent {
  const { sdkProcessingMetadata, ...rest } = event;
  // The parcel the report is about: its number also gives way to its shape.
  const number = typeof sdkProcessingMetadata?.trackingNumber === 'string' ? sdkProcessingMetadata.trackingNumber : '';
  const scrubbed = scrubValue(rest, number.length >= 4 ? (text) => withoutNumber(scrubText(text), number) : scrubText);
  if (scrubbed.request) scrubbed.request = { ...scrubbed.request, data: undefined, query_string: undefined };
  if (scrubbed.transaction) {
    scrubbed.transaction = scrubbed.transaction.replace(/(^|\s)(\/\S*)/g, (_, space: string, path: string) => space + scrubPath(path));
  }
  return { ...scrubbed, sdkProcessingMetadata };
}

/**
 * Each breadcrumb is scrubbed as it is recorded: addresses are cut down, and the
 * console's copy of a log line's arguments is dropped. A log line holds no number.
 */
export function scrubSentryBreadcrumb(breadcrumb: Breadcrumb): Breadcrumb {
  const data = breadcrumb.data && Object.fromEntries(Object.entries(breadcrumb.data)
    .filter(([key]) => !['arguments', 'http.query', 'http.fragment'].includes(key))
    .map(([key, value]) => [key, scrubValue(value, scrubText)]));
  return { ...breadcrumb, ...(breadcrumb.message ? { message: scrubText(breadcrumb.message) } : {}), ...(data ? { data } : {}) };
}

/** The text with every occurrence of the number, in any case, replaced by its pattern. */
function withoutNumber(text: string, number: string): string {
  const pattern = numberPattern(number);
  return text.replace(new RegExp(number.replace(/[.*+?^${}()|[\]\\]/g, '\\$&'), 'gi'), pattern);
}

/** A word of six letters and digits or more, with a digit: it could be a tracking number. */
const NUMBER_LIKE = /(?<![A-Za-z0-9])(?=[A-Za-z0-9]*\d)[A-Za-z0-9]{6,}(?![A-Za-z0-9])/g;

/** Wording for a line that is given no number: addresses cut down, and each word that could be a number given as its shape. */
function withoutNumbers(text: string): string {
  return scrubText(text).replace(NUMBER_LIKE, (word) => `[${numberPattern(word)}]`);
}

/**
 * Why a lookup failed, for a line that has no number to mask: the reason the carrier
 * gave, read through the causes, its wording, and the wording of each cause.
 */
export function errorDetails(error: unknown): JsonObject {
  if (!(error instanceof Error)) return {};
  const causes: string[] = [];
  const seen = new Set<Error>([error]);
  for (let cause = error.cause; cause instanceof Error && !seen.has(cause) && causes.length < 3; cause = cause.cause) {
    seen.add(cause);
    causes.push(withoutNumbers(`${cause.name}: ${cause.message}`).slice(0, 150));
  }
  return {
    error_reason: carrierErrorReason(error),
    error_message: withoutNumbers(error.message).slice(0, 200),
    error_cause: causes.length > 0 ? causes.join(' <- ') : undefined,
  };
}

export function initObservability(): boolean {
  if (runtime.__deliveryObservabilityInitialized) return true;
  const dsn = process.env.SENTRY_DSN?.trim();
  if (!dsn) return false;
  Sentry.init({
    dsn,
    environment: process.env.SENTRY_ENVIRONMENT?.trim() || process.env.NODE_ENV || 'development',
    release: resolveSentryRelease(),
    // Request bodies and queries can carry keys and personal data. Without user
    // information the SDK also leaves out the client's address and the headers that
    // forward it: the visitor's is personal data, and the proxy's says nothing.
    dataCollection: { userInfo: false, httpBodies: [], urlQueryParams: false },
    beforeSend: scrubSentryEvent,
    beforeBreadcrumb: scrubSentryBreadcrumb,
    // Sentry 11 turns on stack traces for messages. A message then arrives as an
    // untyped exception, and its issue is titled after a minified frame ("Object.l").
    attachStacktrace: false,
    tracesSampleRate: parseSampleRate(process.env.SENTRY_TRACES_SAMPLE_RATE),
    // On a server the SDK adds `sentry-trace` and `baggage` to every outgoing request.
    // Nothing this server calls reads them, and bot protection refuses a request that
    // carries `baggage`: Australia Post's gateway answered every lookup with a captcha.
    tracePropagationTargets: [],
    // Sentry 11.2 drops an error captured right after an identical one. Two parcels
    // failing alike are two events with their own tags; the fingerprint groups them.
    integrations: (defaults) => [
      ...defaults.filter((integration) => integration.name !== 'Dedupe'),
      Sentry.extraErrorDataIntegration({ depth: 8 }),
    ],
  });
  runtime.__deliveryObservabilityInitialized = true;
  return true;
}

/**
 * Runs scheduled or background work in Sentry scopes and a trace of its own. A timer set
 * while a request is handled, such as the one that wakes a worker, runs in that request's
 * async context, and so does every run the worker schedules from there: without this, its
 * reports would carry the request's transaction, headers, tags, user and trace. `job` names
 * the work: it is the reports' transaction and their `background_job` tag.
 */
export function inBackgroundScope<T>(job: string, work: () => T): T {
  if (!runtime.__deliveryObservabilityInitialized) return work();
  const isolationScope = new Sentry.Scope();
  isolationScope.setTransactionName(job);
  isolationScope.setTag('background_job', job);
  const scope = new Sentry.Scope();
  scope.setClient(Sentry.getClient());
  return Sentry.withIsolationScope(isolationScope, () => Sentry.withScope(scope, () => Sentry.startNewTrace(work)));
}

function applyContext(
  scope: Sentry.Scope,
  context: OperationalContext,
  capturedErrorType?: string,
  errorMetadata: OperationalErrorMetadata = {},
): void {
  applyUpstreamHttpContext(scope, errorMetadata);
  // Reports name the parcel by the shape of its number and the attempt or job that
  // checked it, never by the number: Sentry is a third party.
  const { trackingNumber, ...operation } = context;
  const number = trackingNumber?.trim() ?? '';
  const pattern = number ? numberPattern(number) : '';
  scope.setContext('operation', { ...operation, ...(pattern ? { numberPattern: pattern } : {}) });
  // The number can still reach a report in an error message, a provider's answer or a
  // breadcrumb: the last step replaces it there. SDK metadata is never sent.
  if (number) scope.setSDKProcessingMetadata({ trackingNumber: number });
  const tags: Record<string, string | undefined> = {
    anomaly_code: boundedText(context.anomalyCode, 100),
    attempt_id: boundedText(context.attemptId, 100),
    carrier: boundedText(context.carrier, 100),
    component: boundedText(context.component, 100),
    database_code: boundedText(errorMetadata.databaseCode, 40),
    database_status: boundedText(errorMetadata.databaseStatus, 3),
    error_type: boundedText(capturedErrorType, 100),
    job_id: boundedText(context.jobId, 100),
    operation: boundedText(context.operation, 100),
    request_id: boundedText(context.requestId, 100),
    route: boundedText(context.route),
    route_type: boundedText(context.routeType, 100),
    trigger: boundedText(context.trigger, 100),
    number_pattern: boundedText(pattern, 100),
    upstream_status: boundedText(errorMetadata.upstreamStatus, 3),
  };
  for (const [key, value] of Object.entries(tags)) {
    if (value) scope.setTag(key, value);
  }
  const extras: Record<string, string | number | undefined> = {
    attempt_id: boundedText(context.attemptId, 100),
    duration_ms: safeNumber(context.durationMs),
    events_normalized: safeNumber(context.eventsNormalized),
    events_received: safeNumber(context.eventsReceived),
    failure_count: safeNumber(context.failureCount),
    job_id: boundedText(context.jobId, 100),
    previous_stage: boundedText(context.previousStage, 100),
    provider_status: boundedText(context.providerStatus, 100),
    reported_stage: boundedText(context.reportedStage, 100),
    request_id: boundedText(context.requestId, 100),
    selected_stage: boundedText(context.selectedStage, 100),
  };
  for (const [key, value] of Object.entries(extras)) {
    if (value !== undefined) scope.setExtra(key, value);
  }
}

export function captureOperationalError(
  error: unknown,
  context: OperationalContext,
): string | null {
  if (!initObservability()) return null;
  const capturedErrorType = errorType(error);
  const errorMetadata = operationalErrorMetadata(error);
  let eventId: string | null = null;
  Sentry.withScope((scope) => {
    applyContext(scope, context, capturedErrorType, errorMetadata);
    // A database that does not answer gives no status, so its code alone asks for the help text.
    if (errorMetadata.databaseStatus || errorMetadata.databaseCode === 'unreachable') scope.setContext('next_steps', {
      action: errorMetadata.databaseCode === 'unreachable'
        ? 'The database did not answer in time, or cut its answer off. Check that the database and its gateway are running and reachable from the app; the operation tag of each event names a write that failed.'
        : errorMetadata.databaseCode === 'PGRST202'
          ? 'Verify the deployed database migrations and RPC signature, then reload the PostgREST schema cache. Check whether observation logging or shipment persistence failed.'
          : 'Check database availability, permissions and migrations; inspect the operation tag to distinguish shipment persistence from diagnostics.',
    });
    // A database that does not answer fails every write at once: one outage is one issue, and the tags name each write.
    scope.setFingerprint(errorMetadata.databaseCode === 'unreachable' ? ['delivery-tracker', 'database-unreachable'] : [
      'delivery-tracker',
      context.component,
      context.operation,
      context.carrier ?? 'none',
      capturedErrorType,
    ]);
    // Scope processors run after the SDK has attached the request and named
    // the transaction after its address.
    if (context.withoutRequest) {
      scope.addEventProcessor((event) => ({ ...event, request: undefined, transaction: undefined }));
    }
    eventId = Sentry.captureException(error);
  });
  return eventId;
}

function applyUpstreamHttpContext(scope: Sentry.Scope, metadata: OperationalErrorMetadata): void {
  scope.setContext('upstream_http', null);
  scope.setTag('upstream_content_type', undefined);
  scope.setTag('upstream_body_read', undefined);
  scope.setTag('upstream_error_code', undefined);
  if (!metadata.upstreamHttp) return;
  scope.setContext('upstream_http', { ...metadata.upstreamHttp, retry_after_ms: metadata.retryAfterMs });
  scope.setTag('upstream_content_type', metadata.upstreamHttp.content_type);
  scope.setTag('upstream_body_read', metadata.upstreamHttp.body_read);
  if (metadata.upstreamHttp.error_code) scope.setTag('upstream_error_code', metadata.upstreamHttp.error_code);
}

/**
 * A provider failure or a transport recovery is a warning. A provider's answer
 * that it has no history for the number yet is an answer about the parcel.
 */
function warnsOf(code: string, category: string | undefined): boolean {
  return code === 'transport_fallback' || (code === 'provider_failed' && category !== 'no_history');
}

/**
 * A correction to the carrier detection already names or asks first for the
 * number is expected: it is no signal.
 */
function routingSignal(code: string, category: string | undefined): code is RoutingSignalCode {
  return (ROUTING_SIGNALS as readonly string[]).includes(code) && !(code === 'carrier_auto_swapped' && category === 'detected');
}

/**
 * Every routing decision is a log line and a breadcrumb. A routing signal also goes
 * to the check that reported it; only a failed coordination store opens an issue.
 */
export function reportRoutingEvent(code: string, context: {
  carrier: string; provider: string; category?: string; trackingNumber?: string; errorClass?: string; error?: unknown;
  attemptId?: string;
}): void {
  try {
    if (routingSignal(code, context.category)) {
      trackingAttempts.getStore()?.signal?.({ signal: code, carrier: context.carrier, subject: context.provider });
    }
    const metadata = operationalErrorMetadata(context.error);
    logOperationalEvent('tracking_routing', {
      attempt_id: context.attemptId ?? trackingAttemptId(),
      decision: code, carrier: context.carrier, provider: context.provider,
      category: context.category ?? null, tracking_number: context.trackingNumber ?? null,
      error_type: context.errorClass ?? null,
      error_reason: carrierErrorReason(context.error),
      // Per-attempt failures no longer reach Sentry, so the log line carries the provider's wording.
      error_message: context.error instanceof Error ? context.error.message.slice(0, 200) : undefined,
      upstream_status: metadata.upstreamStatus,
      upstream_content_type: metadata.upstreamHttp?.content_type,
      upstream_body_read: metadata.upstreamHttp?.body_read,
      upstream_body_signals: metadata.upstreamHttp?.body_signals.join(','),
    }, warnsOf(code, context.category) ? 'warning' : 'info');
    if (!initObservability()) return;
    Sentry.withScope((scope) => {
      applyContext(scope, { component: 'tracking-routing', operation: code,
        carrier: context.carrier, trackingNumber: context.trackingNumber });
      scope.setTag('provider', context.provider.slice(0, 80));
      scope.setTag('failure_category', context.category ?? 'none');
      if (context.errorClass) scope.setTag('error_type', context.errorClass);
      applyUpstreamHttpContext(scope, metadata);
      if (metadata.upstreamStatus !== undefined) scope.setTag('upstream_status', metadata.upstreamStatus);
      if (metadata.providerFailureReason) scope.setTag('provider_failure_reason', metadata.providerFailureReason);
      if (metadata.providerCode !== undefined) scope.setTag('provider_code', metadata.providerCode);
      scope.setFingerprint(['delivery-tracker', 'tracking-routing', code,
        context.provider, context.category ?? 'none']);
      scope.setLevel('warning');
      // Failures for one parcel, recoveries and routing signals stay in logs and
      // breadcrumbs; signals also go to the review queues.
      if (code === 'health_store_unavailable') {
        const message = `Tracking routing: ${code}`;
        if (context.error !== undefined) {
          // Keep stack, causes and custom error fields even if recovery succeeds.
          scope.addEventProcessor(event => ({ ...event, message }));
          Sentry.captureException(context.error);
        } else Sentry.captureMessage(message);
      }
      else Sentry.addBreadcrumb({ category: 'tracking-routing', message: code, data: { provider: context.provider } });
    });
  } catch {
    // Monitoring must never prevent fallback or turn valid data into an error.
  }
}

/**
 * An incident that opened, or still fails six hours on, reaches Sentry; the event
 * title states the impact. A recovery, a closing, a rescued direct step or a covered own
 * lookup is a review signal and stays out (null).
 */
export function captureTrackingHealth(incident: JsonObject): string | null {
  if (healthReviewSignal(incident) || !initObservability()) return null;
  const message = healthMessage(incident);
  let id: string | null = null;
  Sentry.withScope(scope => {
    scope.setTag('component', 'tracking-health');
    scope.setTag('incident_kind', String(incident.kind));
    scope.setTag('incident_state', String(incident.state));
    scope.setTag('provider', String(incident.subject));
    scope.setContext('tracking_health', { ...incident, impact: message.impact, next_steps: message.nextSteps,
      suppression: 'Repeated incident notifications are limited to one per six hours.',
      runbook: 'https://github.com/plhery/peek-delivery-tracker/blob/main/ops/sentry/README.md' });
    // Opening and reminders are one issue.
    scope.setFingerprint(['delivery-tracker', 'tracking-health', String(incident.kind), String(incident.subject)]);
    scope.setLevel(incident.kind === 'refresh' ? 'error' : 'warning');
    id = Sentry.captureMessage(message.title);
  });
  return id;
}

/** Who shares an overall allowance: everyone without an account, per UTC day, or every account, per UTC hour. */
export type AllowanceHolder = 'public' | 'account';

const OVERALL_ALLOWANCES = {
  public: {
    component: 'public-allowance',
    context: 'public_allowance',
    subject: { lookup: 'Lookups without an account', detection: 'Carrier detection without an account' },
    setting: { lookup: 'PUBLIC_LOOKUPS_GLOBAL_PER_DAY', detection: 'PUBLIC_DETECTIONS_GLOBAL_PER_DAY' },
    period: "today's",
    usedUp: 'Every visitor without an account is refused until midnight UTC. Signed-in accounts are not affected.',
    runningOut: 'At this rate visitors without an account will be refused before midnight UTC.',
    check: "Compare yesterday's per-client gauges with the allowance (docs/OBSERVABILITY.md): a few clients at their limit point to abuse",
  },
  account: {
    component: 'account-allowance',
    context: 'account_allowance',
    subject: { lookup: 'Parcels added by accounts', detection: 'Carrier detection for accounts' },
    setting: { lookup: 'ACCOUNT_LOOKUPS_PER_HOUR', detection: 'ACCOUNT_DETECTIONS_PER_HOUR' },
    period: "this hour's",
    usedUp: 'Every account is refused until the next hour (UTC). Visitors without an account are not affected.',
    runningOut: 'At this rate every account will be refused before the hour (UTC) is over.',
    check: "List today's busiest accounts in private.account_tracking_usage and when they were made (auth.users): many new accounts at their daily limit point to abuse",
  },
} as const;

/**
 * An overall allowance is running out or used up. Used up, everyone who
 * shares it is refused until its day or hour is over. One issue per holder,
 * allowance and state; the event title states the impact.
 */
export function captureOverallAllowance(
  holder: AllowanceHolder,
  kind: 'lookup' | 'detection',
  state: 'running_out' | 'used_up',
  usage: { used: number; limit: number },
): string | null {
  if (!initObservability()) return null;
  const allowance = OVERALL_ALLOWANCES[holder];
  let id: string | null = null;
  Sentry.withScope(scope => {
    scope.setTag('component', allowance.component);
    scope.setTag('allowance', kind);
    scope.setTag('allowance_state', state);
    scope.setContext(allowance.context, { ...usage,
      impact: state === 'used_up' ? allowance.usedUp : allowance.runningOut,
      next_steps: `${allowance.check}; if the use is ordinary, raise ${allowance.setting[kind]}.` });
    scope.setFingerprint(['delivery-tracker', allowance.component, kind, state]);
    scope.setLevel(state === 'used_up' ? 'error' : 'warning');
    id = Sentry.captureMessage(`${allowance.subject[kind]}: ${allowance.period} allowance is ${state === 'used_up' ? 'used up' : 'running out'}`);
  });
  return id;
}

function boundedLogValue(value: unknown): string | number | boolean | null | undefined {
  if (value === null || typeof value === 'boolean') return value;
  if (typeof value === 'number') return Number.isFinite(value) ? value : undefined;
  if (typeof value === 'string') return value.slice(0, 500);
  return undefined;
}

/** The fields a line is given a whole number in: the parcel's, and the one its check looked up (a handoff's). */
const LOG_NUMBER_FIELDS = ['tracking_number', 'support_lookup_number'];

/**
 * Writes one JSON line. Loki keeps lines past their parcel's deletion, so no line holds a
 * tracking number: the parcel's gives way to its shape (`number_pattern`), and each number
 * given is replaced by its shape wherever else the line repeats it, such as a carrier's error.
 */
export function logOperationalEvent(
  event: string,
  fields: JsonObject = {},
  level: 'info' | 'warning' | 'error' = 'info',
): void {
  const numbers = LOG_NUMBER_FIELDS.map((field) => fields[field])
    .filter((value): value is string => typeof value === 'string' && value.length >= 4);
  const { tracking_number: trackingNumber } = fields;
  const safeFields = Object.fromEntries(
    Object.entries({ ...fields, number_pattern: typeof trackingNumber === 'string' ? numberPattern(trackingNumber) : undefined })
      .filter(([key]) => !LOG_NUMBER_FIELDS.includes(key))
      .map(([key, value]) => [key, boundedLogValue(typeof value === 'string' ? numbers.reduce(withoutNumber, value) : value)] as const)
      .filter((entry): entry is [string, string | number | boolean | null] => entry[1] !== undefined),
  );
  const payload = JSON.stringify({
    timestamp: new Date().toISOString(),
    level,
    event: event.slice(0, 100),
    ...safeFields,
  });
  if (level === 'error') console.error(payload);
  else if (level === 'warning') console.warn(payload);
  else console.log(payload);
}

export function shouldReportRepeatedFailure(failureCount: number): boolean {
  return Number.isInteger(failureCount)
    && failureCount > 0
    && (failureCount <= 3 || (failureCount & (failureCount - 1)) === 0);
}

const OUTAGE_REPORT_AFTER_MS = 120_000;

/**
 * Whether a failure streak has become an outage to report: once it has lasted
 * two minutes, then each time it has doubled since the last report.
 * `lastReportedAfterMs` is how long the streak had lasted at that report.
 */
export function shouldReportOutage(failingForMs: number, lastReportedAfterMs: number | null): boolean {
  if (!Number.isFinite(failingForMs) || failingForMs < OUTAGE_REPORT_AFTER_MS) return false;
  return lastReportedAfterMs === null || failingForMs >= lastReportedAfterMs * 2;
}

/**
 * The database did not answer, or its gateway answered for it (502, 503, 504):
 * what a restart looks like, and what waiting heals. A 500 is the database's
 * own answer to a failed statement and counts as a refusal.
 */
export function databaseUnavailable(error: unknown): boolean {
  // By name: the build's bundles each hold their own copy of the error class.
  if (!(error instanceof Error) || error.name !== 'SupabaseError') return false;
  const details = error as Error & { status?: unknown; code?: unknown };
  return details.code === 'unreachable' || details.status === 502 || details.status === 503 || details.status === 504;
}

function zurichHour(now: Date): number {
  return Number(new Intl.DateTimeFormat('en-GB', {
    hour: '2-digit',
    hourCycle: 'h23',
    timeZone: 'Europe/Zurich',
  }).format(now));
}

export function beginScheduledSyncCheckIn(now = new Date()): ScheduledCheckIn | null {
  if (!initObservability()) return null;
  const daytime = zurichHour(now) >= 8 && zurichHour(now) < 22;
  const monitorSlug = daytime
    ? 'delivery-tracker-sync-daytime'
    : 'delivery-tracker-sync-overnight';
  const checkInId = Sentry.captureCheckIn({ monitorSlug, status: 'in_progress' }, {
    schedule: {
      type: 'crontab',
      value: daytime ? '*/2 8-21 * * *' : '*/20 0-7,22-23 * * *',
    },
    checkinMargin: daytime ? 5 : 15,
    // Runs never overlap: a stuck one holds the queue, no later check-in arrives, and
    // Sentry reports it as timed out after ten minutes, far beyond a normal run.
    // Every run's first check-in sends this configuration, replacing edits made in Sentry.
    maxRuntime: 10,
    timezone: 'Europe/Zurich',
    failureIssueThreshold: 1,
    recoveryThreshold: 1,
  });
  return { checkInId, monitorSlug, startedAt: now.getTime() };
}

export function finishScheduledSyncCheckIn(
  checkIn: ScheduledCheckIn | null,
  status: 'ok' | 'error',
  now = new Date(),
): void {
  if (!checkIn || !runtime.__deliveryObservabilityInitialized) return;
  Sentry.captureCheckIn({
    monitorSlug: checkIn.monitorSlug,
    checkInId: checkIn.checkInId,
    status,
    duration: Math.max(0, now.getTime() - checkIn.startedAt) / 1000,
  });
}

export async function flushObservability(timeoutMs = 2_000): Promise<boolean> {
  return runtime.__deliveryObservabilityInitialized ? await Sentry.flush(timeoutMs) : true;
}
