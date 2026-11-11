import 'server-only';

import { randomUUID } from 'node:crypto';
import { CARRIER_DEFINITIONS, recognitionCandidates, type CarrierId, type CarrierResult } from 'universal-parcel-scraper';
import { latestResultTime } from 'universal-parcel-scraper/app';
import type { ApiCarrierDetectionResponse, ApiCarrierId } from '../generated/apiContract';
import { detectCarrierMatch, isPickupCode } from '../lib/carriers';
import { directHistoryNumber } from './directLocalHistory';
import { captureOperationalError, errorType, logOperationalEvent } from './observability';
import type { SupabaseServiceClient } from './supabase';
import { directCarrier, hasRoutingProgress } from './trackingRouting';
import type { JsonObject } from './types';

/** Carriers detection offers under another id: the Add check promotes an Amazon Logistics number Amazon Shipping tracks (carrierDetection.ts). */
const DETECTED_AS: Readonly<Record<string, string>> = { 'amazon-shipping': 'amazon-logistics' };

/**
 * Capture a detection gap before a successful provider can hide it.
 * `answeredBy` is the carrier that tracked this number directly: the parcel's
 * own carrier, or the local carrier its origin handed the parcel to. Either
 * way the number is where it belongs, whatever its shape suggests.
 */
export function trackingSupportContext(trackingNumber: string, configuredCarrier: string, answeredBy: string | null = null): JsonObject {
  const number = trackingNumber.replace(/[\s.-]/g, '').toUpperCase();
  const detection = detectCarrierMatch(number);
  const reasons: string[] = [];
  const offered = (carrier: string) => detection.candidates.some((candidate) => candidate === (DETECTED_AS[carrier] ?? carrier));
  if (detection.carrier === 'unknown') {
    // A shape several carriers issue leaves no gap once the filed carrier tracked the number, among them or not:
    // the Add sheet detects the number as typed, and the stored one has lost its hyphens (an Ozon posting number).
    // Six digits filed under no carrier are a pickup code, not a shape detection misses: the apps ask for the
    // parcel's tracking number instead.
    if (!detection.candidates.length) {
      if (configuredCarrier !== 'unknown' || !isPickupCode(number)) reasons.push('unknown_shape');
    } else if (answeredBy !== configuredCarrier) reasons.push('ambiguous_shape');
  } else if (detection.carrier === 'intl-post' && answeredBy !== configuredCarrier) {
    // An S10 number detection leaves to international post is where it belongs once the carrier it is filed under
    // tracked it, whether the post its suffix names or a courier that carries such numbers (Matkahuolto).
    reasons.push('generic_postal');
  }
  if (!['unknown', 'intl-post'].includes(configuredCarrier) && !directCarrier(configuredCarrier)) {
    reasons.push('no_direct_adapter');
  }
  if (!['unknown', 'intl-post'].includes(configuredCarrier)
    && detection.carrier !== 'intl-post' && detection.candidates.length
    && !offered(configuredCarrier) && answeredBy !== configuredCarrier && !(answeredBy && offered(answeredBy))) {
    reasons.push('carrier_mismatch');
  }
  return {
    tracking_number: number,
    configured_carrier: configuredCarrier,
    detection_carrier: detection.carrier,
    detection_confidence: detection.confidence,
    detection_candidates: detection.candidates.slice(0, 32),
    reasons,
    ...(process.env.IMAGE_COMMIT ? { app_version: process.env.IMAGE_COMMIT.slice(0, 100) } : {}),
  };
}

/** Whether detection offers `carrier` among the carriers the context's shape fits. */
export function detectionOffers(context: JsonObject, carrier: string): boolean {
  return Array.isArray(context.detection_candidates) && context.detection_candidates.includes(DETECTED_AS[carrier] ?? carrier);
}

/**
 * The context a check records when it begins. Whether a shape several carriers issue leaves a gap
 * depends on the check's answer, so that reason waits for its end (`trackingSupportGap`): a check whose
 * carrier confirms the number opens no case. An open case keeps the reason until a check ends: one cut short never does.
 */
export function trackingSupportStart(trackingNumber: string, configuredCarrier: string, answeredBy: string | null = null): JsonObject {
  const context = trackingSupportContext(trackingNumber, configuredCarrier, answeredBy);
  context.reasons = (context.reasons as string[]).filter((reason) => reason !== 'ambiguous_shape');
  return context;
}

/**
 * The ambiguous shape a check leaves, from the carrier it filed the parcel under and the one that confirmed the number.
 * When a carrier that could have settled it failed its lookup in the check (`failed`: rate limited or down), the
 * filed one if the shape fits it, or else any the shape fits, the gap is undecided: such a check opens no case for
 * it, the outage being health monitoring's, and a case already open keeps it.
 */
export function trackingSupportGap(
  trackingNumber: string, filedCarrier: string, answeredBy: string | null, failed: (carrier: string) => boolean = () => false,
): JsonObject {
  const context = trackingSupportContext(trackingNumber, filedCarrier, answeredBy);
  if (!(context.reasons as string[]).includes('ambiguous_shape')) return {};
  const candidates = context.detection_candidates as string[];
  const settling = candidates.includes(DETECTED_AS[filedCarrier] ?? filedCarrier) ? [filedCarrier] : candidates;
  return { support_gap_reason: 'ambiguous_shape', ...(settling.some(failed) ? { support_gap_undecided: true } : {}) };
}

/** Whether the Add sheet's recognition asks the carrier over plain HTTP; any other it asks through a browser, if at all. */
function recognizedOverHttp(carrier: string): boolean {
  return CARRIER_DEFINITIONS[carrier as CarrierId]?.tracking.recognitionRank != null;
}

/** Keep unresolved submissions even when the visitor never saves a parcel. */
export async function retainDetectionSupport(
  client: SupabaseServiceClient,
  answer: ApiCarrierDetectionResponse,
  userId?: string,
): Promise<void> {
  if (answer.carrier !== 'unknown' || isPickupCode(answer.trackingNumber)) return;
  // The start of a number the account saved is that number still being typed, not a case.
  // When the check fails, the case opens as before.
  if (userId && await client.savedLongerTrackingNumber(userId, answer.trackingNumber).catch(() => false)) return;
  const choice = (answer.recognized?.length ?? 0) > 1;
  const context = trackingSupportContext(answer.trackingNumber, answer.carrier);
  context.reasons = [...context.reasons as string[], choice ? 'recognition_choice' : 'recognition_unknown'];
  // A carrier recognition asks only through a browser, which it skips once a universal provider
  // tracks the number, went unasked or could not answer: the parcel's own checks of it may still
  // confirm the number, so the case is held for them.
  if (!choice && recognitionCandidates(answer.trackingNumber, { phase: 'browser' }).some(({ carrier }) =>
    !answer.asked?.includes(carrier as ApiCarrierId) || answer.unanswered?.includes(carrier as ApiCarrierId))) {
    context.browser_pending = true;
  }
  const outcome = choice ? 'detection_choice' : 'detection_unknown';
  logOperationalEvent('carrier_detection_support', {
    tracking_number: answer.trackingNumber, outcome,
    asked_carriers: answer.asked?.join(',') ?? '',
    unanswered_carriers: answer.unanswered?.join(',') ?? '',
    recognized_carriers: answer.recognized?.join(',') ?? '',
  });
  try {
    await client.recordTrackingSupportObservation(answer.trackingNumber, context, { outcome }, new Date(), `detection:${randomUUID()}`);
  } catch (error) {
    logOperationalEvent('tracking_support_write_failed', {
      operation: 'detection', tracking_number: answer.trackingNumber, error_type: errorType(error),
    }, 'error');
    captureOperationalError(error, {
      component: 'tracking-support', operation: 'detection', trackingNumber: answer.trackingNumber, withoutRequest: true,
    });
  }
}

/** Only accepted progress from this exact number can verify a direct fix. */
export function trackingSupportEvidence(
  parcel: JsonObject,
  result: CarrierResult,
  sourceCarrier: string,
  outcome: string,
  preserveSummary: boolean,
): JsonObject {
  const number = directHistoryNumber(parcel, result);
  const provider = typeof result.tracking_provider === 'string' ? result.tracking_provider.slice(0, 80) : null;
  const directProgress = ['updated', 'unchanged'].includes(outcome) && !preserveSummary && !provider
    && directCarrier(sourceCarrier) && hasRoutingProgress(result)
    && latestResultTime(result, sourceCarrier) > 0;
  return {
    support_lookup_number: typeof number === 'string' ? number : null,
    support_provider: provider,
    support_direct_progress: directProgress,
    // A carrier the Add sheet's recognition could not ask over HTTP tracked the number, which, filed
    // under it, leaves no gap: what a held case waits for.
    support_settled: directProgress && typeof number === 'string' && !recognizedOverHttp(sourceCarrier)
      && (trackingSupportContext(number, sourceCarrier, sourceCarrier).reasons as string[]).length === 0,
  };
}
