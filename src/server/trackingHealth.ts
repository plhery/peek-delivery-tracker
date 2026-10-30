import 'server-only';
import { AsyncLocalStorage } from 'node:async_hooks';
import { CarrierError, NoHistoryError, UpstreamHttpError } from 'universal-parcel-scraper';
import type { LookupRecord, StepRecord } from 'universal-parcel-scraper/node';
import { HANDED_OFF_REASONS, UNKNOWN_ITEM_REASONS, carrierErrorReason } from './trackingFailure';
import type { JsonObject } from './types';

export interface HealthSample extends JsonObject {
  kind: 'refresh' | 'provider' | 'direct';
  subject: string;
  healthy: boolean;
  details: JsonObject;
  /** A failure its lookup answered past, through a retry or another step: still counted, never a refresh's evidence. */
  recovered?: boolean;
}
const observations = new AsyncLocalStorage<Map<string, HealthSample>>();
const confirmations = new AsyncLocalStorage<true>();
/** A browser capture that failed: the lookup's own fault, not the carrier's answer. */
const CAPTURE_FAILURES = new Set(['capture_missing', 'capture_unreadable']);
/**
 * A clean not-found or a missing input is a working transport that answered,
 * so it is recorded healthy and lets an incident recover. Postgres leaves
 * these categories out of the outage rate so they cannot mask a real outage.
 */
const answered = new Set(['ok', 'not_found', 'input_required']);

/**
 * A provider with no history for a number yet has answered, like not-found
 * (routing treats it the same way). Counted as a failure, every parcel added
 * before its first scan looked like a provider outage, and the hourly hand-off
 * probes of one item bound for Ukraine could open a Ukrposhta incident.
 */
function category(record: StepRecord | LookupRecord): string {
  // A number the carrier does not issue is an answer too. It is filed with the
  // missing inputs, the category Postgres already leaves out of the outage rate.
  const outcome = record.outcome === 'invalid_input' ? 'input_required' : record.outcome;
  let current = record.error;
  for (let depth = 0; current instanceof Error && depth < 8; depth++, current = current.cause) {
    if (current instanceof CarrierError) {
      if (current instanceof NoHistoryError || UNKNOWN_ITEM_REASONS.has(current.reason ?? '')) return 'not_found';
      if (HANDED_OFF_REASONS.has(current.reason ?? '')) return 'handed_off';
      // So is a number that names several parcels, such as an Australia Post consignment of
      // two articles: the owner has to give one parcel's number. Counted as a failure, one such
      // parcel checked hourly kept the carrier's direct incident open.
      return current.reason === 'several_parcels' ? 'input_required' : outcome;
    }
  }
  return outcome;
}
/**
 * The status of the HTTP response a provider rejected. Other carrier errors
 * carry an HTTP-like `status` of their own (404 for not found, 400 for a
 * number a parser refuses), which no server sent.
 */
function details(record: StepRecord | LookupRecord, outcome: string): JsonObject {
  let status: number | null = null;
  let current = record.error;
  for (let depth = 0; current instanceof Error && depth < 8; depth++, current = current.cause) {
    if (current instanceof UpstreamHttpError && current.status >= 100 && current.status <= 599) status = current.status;
  }
  return { error_type: record.errorType, category: outcome, reason: carrierErrorReason(record.error) ?? null, http_status: status,
    step: 'step' in record ? record.step : record.finalStep };
}
/**
 * A confirmation's inconclusive answer, such as an item listed without scans: the carrier may
 * simply not hold the parcel yet. An HTTP error or a failed browser capture is still a failure.
 */
function inconclusive(record: StepRecord | LookupRecord, outcome: string): boolean {
  if (!confirmations.getStore() || outcome !== 'indeterminate') return false;
  let current = record.error;
  for (let depth = 0; current instanceof Error && depth < 8; depth++, current = current.cause) {
    if (current instanceof UpstreamHttpError && (current.status >= 500 || current.status === 429)) return false;
    if (current instanceof CarrierError && CAPTURE_FAILURES.has(current.reason ?? '')) return false;
  }
  return true;
}
function save(kind: 'provider' | 'direct', record: StepRecord | LookupRecord): void {
  const store = observations.getStore();
  if (!store) return;
  const key = `${kind}:${record.carrier}`;
  const previous = store.get(key);
  const outcome = category(record);
  // Neither healthy nor failing: no sample. Nor is a hand-off to the group's unit abroad, which
  // the next step asks: counted as failures, three parcels bound for France made DPD Germany's
  // direct step look broken on 95% of its checks.
  if (outcome === 'handed_off' || inconclusive(record, outcome)) return;
  // A retry cannot count as another lookup or erase the original failed direct attempt: one
  // that answered only marks it recovered.
  if (previous && !previous.healthy) {
    if (answered.has(outcome)) previous.recovered = true;
    return;
  }
  store.set(key, { kind, subject: record.carrier.slice(0, 100), healthy: answered.has(outcome), details: details(record, outcome) });
}
export const healthStepRecorder = {
  step(record: StepRecord) { if (record.step === 'direct') save('direct', record); },
  lookup(record: LookupRecord) {
    // With direct as the only tier, the direct sample would duplicate the provider sample. A
    // carrier whose one step is another (dpd-fr's browser) keeps a direct sample it recorded.
    if (record.stepsAvailable === 1 && record.finalStep === 'direct') observations.getStore()?.delete(`direct:${record.carrier}`);
    save('provider', record);
    // The lookup answered after its direct step failed: a later step, such as a retry, recovered it.
    const direct = observations.getStore()?.get(`direct:${record.carrier}`);
    if (direct && !direct.healthy && observations.getStore()?.get(`provider:${record.carrier}`)?.healthy) direct.recovered = true;
  },
};
export function observeTrackingHealth<T>(samples: Map<string, HealthSample>, operation: () => Promise<T>): Promise<T> {
  return observations.run(samples, operation);
}
/**
 * A lookup asking a carrier whether it holds a parcel that is not its own yet: a delivery partner
 * before the handover, or a carrier that detection or a provider names. Its inconclusive answer
 * is no evidence of the carrier's health. Counted as failures, PostNL's empty answers for
 * Australian and Polish items it did not hold yet made most of Spring GDS's provider incident.
 */
export function confirmationLookup<T>(operation: () => Promise<T>): Promise<T> {
  return confirmations.run(true, operation);
}

/**
 * A recovery, a closing, or a direct step failing only in checks whose refresh a fallback step
 * answered (`rescued`): logged and counted, never sent to Sentry. Sentry gets the failures that
 * left a parcel without a fresh answer.
 */
export function healthReviewSignal(incident: JsonObject): boolean {
  return incident.state === 'recovered' || incident.state === 'closed' || incident.rescued === true;
}

export function healthMessage(incident: JsonObject): { title: string; nextSteps: string; impact: string } {
  const subject = String(incident.subject);
  const refresh = incident.kind === 'refresh';
  const recovered = incident.state === 'recovered';
  const evidence = incident.evidence as JsonObject | undefined;
  const scope = refresh ? 'Shipment refreshes' : incident.kind === 'direct' ? 'Direct tracking' : 'Provider lookups';
  if (incident.state === 'closed' && incident.reason === 'verification_abstained') {
    return { title: `${scope} closed: ${subject} verification abstentions are review signals`,
      nextSteps: 'Keep provider fallback and scheduled backoff. Count unreadable verification in the database and carrier dashboard; other lookup failures still open incidents.',
      impact: 'No successful lookup or recovery is claimed. Saved history remains available.' };
  }
  if (incident.state === 'closed' && incident.reason === 'bypassed') {
    return { title: `${scope} closed: ${subject} answers through another step`,
      nextSteps: 'The carrier\'s three latest lookups answered without its direct step, which has not run for six hours (an adapter that goes straight to its browser step). Fresh direct failures open a new incident.',
      impact: 'This describes one direct transport the carrier no longer uses.' };
  }
  if (incident.state === 'closed') {
    return { title: `${scope} closed without a recent check: ${subject}`,
      nextSteps: 'Nothing was checked for 24 hours, so no recovery was observed. If it should still be checked, find out why it is not; fresh failures open a new incident.',
      impact: refresh ? 'No scheduled refresh of this carrier was recorded for 24 hours.'
        : 'This describes one provider or direct transport that was not used for 24 hours.' };
  }
  // A streak counts when two parcels are on one, or one while no other parcel answered
  // (record_tracking_health); the incident does not say which.
  const title = recovered ? `${scope} recovered: ${subject}`
    : `${scope} repeatedly failing: ${subject} (${incident.failures}/${incident.attempts} in ${incident.window_hours}h${incident.consecutive_failures ? '; 3 checks in a row failed for two parcels, or for one while no other answered' : ''}${incident.rescued === true ? '; every refresh answered through another step' : ''})`;
  let nextSteps = 'Inspect the carrier scraper dashboard and the latest error type. Check the provider response and adapter parsing; retain the working fallback and scheduled backoff.';
  if (evidence?.http_status === 429 || evidence?.category === 'rate_limited') nextSteps = 'Honor Retry-After and inspect provider cooldowns. Reduce request frequency; do not increase retries or browser parallelism.';
  else if (['input_required','not_found'].includes(String(evidence?.category))) nextSteps = 'Check whether the shipment has been announced and whether its required postcode or tracking credentials were supplied. Do not retry invalid inputs immediately.';
  else if (evidence?.http_status === 403 || evidence?.category === 'challenge') nextSteps = 'Check for carrier maintenance or a verification challenge. Use the browser or alternate provider, keep the cooldown, and inspect the direct adapter if the refusal persists.';
  else if (subject === 'ups' && incident.kind === 'direct') nextSteps = 'Plain HTTP runs only without a browser service and Akamai holds its status call open until the timeout. Configure FLARESOLVERR_URL so the status reply is read from the browser page.';
  else if (['trawl','browser','page'].includes(String(evidence?.step))) nextSteps = 'Check TRAWL health, container memory/OOM events and browser availability. A closed browser needs replacement before retrying; keep concurrency bounded.';
  else if (Number(evidence?.http_status) >= 500 || evidence?.category === 'maintenance') nextSteps = 'Check the upstream service for maintenance. Inspect the bounded retry and fallback results; allow scheduled backoff rather than adding more retries.';
  return { title, nextSteps: recovered ? 'Three recent checks succeeded and failures are back under the threshold. No action needed; threshold monitoring remains active.' : nextSteps,
    impact: refresh ? recovered ? 'Scheduled shipment refreshes are succeeding again.' : 'Scheduled shipment refreshes failed after routing and fallback. Previous saved progress is retained.'
      : 'This describes one provider or direct transport, not the final shipment refresh. Check refresh incidents to determine user impact.' };
}
