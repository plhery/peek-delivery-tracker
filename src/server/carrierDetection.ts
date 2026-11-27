import 'server-only';

import type { AdapterRegistry, TrackingContext } from 'universal-parcel-scraper/node';
import type { ApiCarrierDetectionResponse, ApiCarrierId } from '../generated/apiContract';
import { isAmazonTrackingNumber } from '../lib/amazon';
import { detectCarrierMatch, KEPT_NUMBER_SHAPE, normalizeTrackingNumber, validTrackingNumber } from '../lib/carriers';
import { createAdapterRegistry } from './adapterRegistry';
import { BROWSER_RECOGNITION_BUDGET_MS, browserRecognitionSlotToSpare, MAX_BROWSER_RECOGNITIONS, recognizeBrowser } from './browserRecognition';
import { checkAmazonShipping } from './amazonShippingEligibility';
import { HttpError } from './api';
import { isPhoneNumber, isPlaceholderNumber, MAX_RECOGNITIONS, recognitionCandidates, recognizeAll, settleRecognition, type RecognitionCandidate } from 'universal-parcel-scraper';
import { AUTOMATIC_CARRIER_IDS, carrierAdapter, requiredRequirements } from './carriers';
import { preflightTracking } from './trackingPreflight';
import { rememberPreflightRecognition } from './preflightRecognition';
import { recordDetection, withLookupPurpose } from './metrics';
import { logOperationalEvent } from './observability';
import type { SupabaseServiceClient } from './supabase';
import { retainDetectionSupport } from './trackingSupport';
import type { JsonObject } from './types';
import { PLACEHOLDER_MESSAGE } from './validation';
import { getRecognitionPriorities } from './recognitionRanking';
import { reviewDemoCarrier } from './reviewDemo';
import { lastResort, suggestedPostcodeCarrier } from './trackingRouting';

/** The Add sheet waits this long for each batch of carriers it asks; the first sync asks again after saving. */
const RECOGNITION_BUDGET_MS = 3_000;
/** A focus-out repeated on the same number reuses the answer instead of asking the carriers again. */
const ANSWER_TTL_MS = 10 * 60_000;
const MAX_ANSWERS = 500;

/**
 * Whether FedEx is asked about a number that passes FedEx's check (the scraper ranks FedEx first for it) with the
 * first batch of carriers, or right after that batch misses it, rather than after the second batch and the providers:
 * on unless `FEDEX_EARLY_RECOGNITION` is `off`, `false`, `no` or `0`. FedEx answers only in a browser, which turns
 * some lookups away with a bot check.
 */
export function fedexEarlyRecognition(env: Record<string, string | undefined> = process.env): boolean {
  return !['off', 'false', 'no', '0'].includes(env.FEDEX_EARLY_RECOGNITION?.trim().toLowerCase() ?? '');
}

// One registry per server process keeps carrier sessions (DPD's guest token) warm.
let registry: AdapterRegistry | undefined;
const answers = new Map<string, { at: number; answer: ApiCarrierDetectionResponse }>();

/**
 * The carrier whose own page the number was typed on, to ask first when none of its rules offers the number: a reader
 * on that page most likely holds its number. Only a carrier that answers by itself, with no postcode or other input.
 * One its rules offer is already a candidate, which the hint ranks first.
 */
function hintedCandidate(hint: string | undefined, trackingNumber: string, offered: readonly RecognitionCandidate[]): RecognitionCandidate[] {
  if (!hint || offered.some(({ carrier }) => carrier === hint) || !AUTOMATIC_CARRIER_IDS.has(hint)
    || carrierAdapter(hint) === 'universal' || requiredRequirements(hint, trackingNumber).length > 0
    || !(registry ??= createAdapterRegistry()).for(hint)?.recognize) return [];
  return [{ carrier: hint, needsInput: null, preferred: false }];
}

async function recognize(trackingNumber: string, typed: string, beforeAsking?: () => Promise<void>, signal?: AbortSignal, health?: SupabaseServiceClient, countryHint?: string | null, hint?: string): Promise<ApiCarrierDetectionResponse> {
  const priorities = getRecognitionPriorities(trackingNumber);
  const ordering = { countryHint, priorities, ...(hint ? { hint } : {}) };
  // The typed form picks the carriers asked, so it keys the answer; the carriers are asked about the number itself.
  const cacheKey = `${typed.trim().toUpperCase()}:${countryHint ?? ''}:${hint ?? ''}:${JSON.stringify(priorities ?? {})}`;
  const cached = answers.get(cacheKey);
  if (cached && Date.now() - cached.at < (cached.answer.carrier === 'unknown' ? 30_000 : ANSWER_TTL_MS)) return cached.answer;
  const offered = recognitionCandidates(typed, ordering);
  // Last resorts, such as carriers whose numbers are another carrier's outside their countries, come once every other
  // candidate has missed the number, never beside them: after the ten below, the providers and the browser
  // confirmations. The carrier whose page the number was typed on is asked first all the same.
  const hinted = hintedCandidate(hint, trackingNumber, offered.filter((candidate) => !lastResort(candidate)));
  const later = (candidate: RecognitionCandidate) => lastResort(candidate) && candidate.carrier !== hinted[0]?.carrier;
  const inside = [...hinted, ...offered.filter((candidate) => !lastResort(candidate))];
  const candidates = inside.slice(0, 2 * MAX_RECOGNITIONS);
  const offeredBrowser = recognitionCandidates(typed, { ...ordering, phase: 'browser' });
  const browserCandidates = offeredBrowser.filter((candidate) => !lastResort(candidate)).slice(0, MAX_BROWSER_RECOGNITIONS);
  const outside = inside.length <= candidates.length ? offered.filter(later).slice(0, MAX_RECOGNITIONS) : [];
  const outsideBrowser = inside.length <= candidates.length ? offeredBrowser.filter(later).slice(0, MAX_BROWSER_RECOGNITIONS) : [];
  const deadline = performance.now() + 25_000;
  await beforeAsking?.();
  const errors = new Map<string, unknown>();
  // The name of the error each carrier's latest check threw before its batch's time ran out.
  const thrown = new Map<string, string>();
  const noteThrown = (carrier: string, error: unknown, context: TrackingContext) => {
    if (!context.signal?.aborted) thrown.set(carrier, error instanceof Error ? error.name : 'Error');
  };
  const ask = async (carrier: string, context: TrackingContext) => {
    thrown.delete(carrier);
    const adapter = (registry ??= createAdapterRegistry()).for(carrier);
    if (!adapter?.recognize) {
      const error = new RangeError(`${carrier} cannot recognize a number`);
      noteThrown(carrier, error, context);
      throw error;
    }
    try {
      const answer = await withLookupPurpose('recognition', () => adapter.recognize!(trackingNumber, context));
      // The saved parcel's first check takes it instead of asking again; an answer past the batch's time took no part here.
      if (!context.signal?.aborted) rememberPreflightRecognition(carrier, trackingNumber, answer);
      return answer;
    } catch (error) { errors.set(carrier, error); noteThrown(carrier, error, context); throw error; }
  };
  const askBrowser = async (carrier: string, context: TrackingContext) => {
    thrown.delete(carrier);
    try { return await recognizeBrowser(carrier, trackingNumber, context, errors.get(carrier)); }
    catch (error) { noteThrown(carrier, error, context); throw error; }
  };
  const browserBudget = () => Math.max(1, Math.min(BROWSER_RECOGNITION_BUDGET_MS, Math.floor(deadline - performance.now())));
  // A number that passes FedEx's check goes to FedEx, which answers only in a browser, as soon as the first five miss
  // it, before the next five and the providers. When FedEx also ranks above every carrier asked over HTTP (none of them
  // backed by number evidence or the page the number was typed on), its read starts with the first five instead, as
  // long as a browser is left for other checks. Their answer still comes first: one that settles the number stops it.
  const fedex = fedexEarlyRecognition()
    ? browserCandidates.filter(({ carrier, preferred }) => carrier === 'fedex' && preferred) : [];
  const stopFedex = new AbortController();
  const headStart = fedex.length && browserCandidates[0]?.carrier === 'fedex'
    && !candidates.some(({ carrier, preferred }) => preferred || carrier === hint)
    && browserRecognitionSlotToSpare('fedex', trackingNumber)
    ? recognizeAll(fedex, askBrowser, browserBudget(), signal ? AbortSignal.any([signal, stopFedex.signal]) : stopFedex.signal)
    : undefined;
  // A stopped read rejects with nobody waiting for it.
  headStart?.catch(() => undefined);
  const outcomes = await recognizeAll(candidates.slice(0, MAX_RECOGNITIONS), ask, RECOGNITION_BUDGET_MS, signal)
    .catch((error: unknown) => { stopFedex.abort(); throw error; });
  const missed = () => { const settled = settleRecognition(outcomes); return !settled.carrier && !settled.choices.length; };
  const early = missed() ? fedex : [];
  if (early.length) outcomes.push(...await (headStart ?? recognizeAll(early, askBrowser, browserBudget(), signal)));
  else stopFedex.abort();
  // The next five are asked only when none of the first knows the number, for another three
  // seconds at most: past the deadline they go unanswered.
  const next = missed() ? candidates.slice(MAX_RECOGNITIONS) : undefined;
  if (next) {
    outcomes.push(...await recognizeAll(next, ask,
      Math.min(RECOGNITION_BUDGET_MS, Math.floor(deadline - performance.now())), signal));
  }
  const cheap = settleRecognition(outcomes);
  const preflight = !cheap.carrier && !cheap.choices.length && health
    ? await preflightTracking(trackingNumber, health, signal) : undefined;
  let retried = false;
  if (!cheap.carrier && !cheap.choices.length && !preflight?.trackingFound) {
    // FedEx asked early that did not answer, as when both browsers were busy or its read failed, is asked again while
    // time is left before the deadline.
    const timeLeft = performance.now() < deadline;
    const due = browserCandidates.filter(({ carrier }) => (timeLeft || !early.some((asked) => asked.carrier === carrier))
      && !outcomes.some((outcome) => outcome.carrier === carrier && outcome.status !== 'failed'));
    retried = due.some(({ carrier }) => early.some((asked) => asked.carrier === carrier));
    outcomes.push(...await recognizeAll(due, askBrowser, browserBudget(), signal));
  }
  // The last resorts, while the detection's 25 seconds last: over HTTP, then in a browser those HTTP could not answer.
  const outsideDue = missed() && !preflight?.trackingFound && performance.now() < deadline ? outside : [];
  if (outsideDue.length) {
    outcomes.push(...await recognizeAll(outsideDue, ask, Math.min(RECOGNITION_BUDGET_MS, Math.floor(deadline - performance.now())), signal));
    const due = missed() && performance.now() < deadline ? outsideBrowser.filter(({ carrier }) =>
      !outcomes.some((outcome) => outcome.carrier === carrier && outcome.status !== 'failed')) : [];
    outcomes.push(...await recognizeAll(due, askBrowser, browserBudget(), signal));
  }
  const { carrier, choices } = settleRecognition(outcomes);
  // Who was asked, and who could not answer, tells "nobody knows it yet" from "could not check".
  const asked = [...new Set(outcomes.map((candidate) => candidate.carrier as ApiCarrierId))];
  const unanswered = asked.filter((carrier) => outcomes.filter((outcome) => outcome.carrier === carrier).every((outcome) => outcome.status === 'failed'));
  // Why a carrier could not answer, from its latest check: its error's kind and reason, the name of an error that is
  // not a carrier's, or `timeout` when it had not answered by the end of its batch.
  const why = (carrier: string) => {
    const failure = outcomes.filter((outcome) => outcome.carrier === carrier).at(-1)?.failure;
    return failure ? [failure.kind, failure.reason].filter(Boolean).join(':') : thrown.get(carrier) ?? 'timeout';
  };
  const answer: ApiCarrierDetectionResponse = {
    trackingNumber,
    ...preflight,
    carrier: (carrier ?? preflight?.carrier ?? 'unknown') as ApiCarrierId,
    ...(!carrier && choices.length > 1 ? { recognized: choices as ApiCarrierId[] } : {}),
    ...(asked.length ? { asked } : {}),
    ...(unanswered.length ? { unanswered } : {}),
  };
  if (outcomes.length) {
    logOperationalEvent('carrier_recognition', {
      tracking_number: trackingNumber,
      asked: asked.length,
      asked_carriers: asked.join(','),
      second_batch: Boolean(next?.length),
      ...(headStart ? { fedex_head_start: true } : {}),
      ...(early.length ? { fedex_early: true } : {}),
      ...(retried ? { fedex_retried: true } : {}),
      ...(outsideDue.length ? { last_resort: true } : {}),
      known: outcomes.filter((outcome) => outcome.status === 'known').length,
      failed: unanswered.length,
      unanswered_carriers: unanswered.join(','),
      unanswered_failures: unanswered.map((carrier) => `${carrier}:${why(carrier)}`).join(','),
      settled: carrier ?? preflight?.carrier ?? (choices.length > 1 ? 'choice' : 'none'),
    });
  }
  // A carrier that could not answer may answer on the next focus-out.
  if (unanswered.length === 0 && !preflight?.providers.some(({ outcome }) => outcome === 'unavailable' || outcome === 'deferred')) {
    answers.delete(cacheKey);
    answers.set(cacheKey, { at: Date.now(), answer });
    if (answers.size > MAX_ANSWERS) answers.delete(answers.keys().next().value!);
  }
  return answer;
}

/**
 * The carrier of a number, for the signed-in Add sheet and the front door
 * alike. `beforeAsking` runs before a carrier is asked about the number, and
 * may refuse by throwing. It does not run for an answer read from the number's
 * shape or kept from a moment ago.
 */
export async function detectCarrier(
  body: JsonObject,
  beforeAsking?: () => Promise<void>,
  signal?: AbortSignal,
  supportClient?: SupabaseServiceClient,
  countryHint?: string | null,
  /** The signed-in account, whose saved numbers tell a number still being typed. */
  userId?: string,
): Promise<ApiCarrierDetectionResponse> {
  if (signal?.aborted) throw new HttpError(499, 'Carrier check cancelled');
  if (typeof body.trackingNumber !== 'string' || body.trackingNumber.length > 80) {
    throw new HttpError(400, 'Invalid tracking number');
  }
  const trackingNumber = normalizeTrackingNumber(body.trackingNumber);
  if (!KEPT_NUMBER_SHAPE.test(trackingNumber) || !validTrackingNumber(body.trackingNumber)) {
    throw new HttpError(400, 'Invalid tracking number');
  }
  // A mobile number as dialled in the reader's country is refused as a placeholder is when added: no carrier, and no
  // universal provider either, is asked about it.
  if (isPhoneNumber(trackingNumber, countryHint)) throw new HttpError(400, PLACEHOLDER_MESSAGE);
  // A review demo number is its scenario's carrier's, and nobody is asked about it (reviewDemo.ts).
  const demo = reviewDemoCarrier(userId, trackingNumber);
  if (demo) return { trackingNumber, carrier: demo };
  if (isAmazonTrackingNumber(trackingNumber)) {
    // Every carrier's lookup refuses a placeholder, but Amazon's eligibility check asks its tracker directly.
    if (isPlaceholderNumber(trackingNumber)) throw new HttpError(400, PLACEHOLDER_MESSAGE);
    await beforeAsking?.();
    const amazonShippingStatus = await checkAmazonShipping(trackingNumber);
    recordDetection('high');
    return { trackingNumber, carrier: ['available', 'expired'].includes(amazonShippingStatus) ? 'amazon-shipping' : 'amazon-logistics', amazonShippingStatus };
  }
  // The number as typed keeps the dashes and spaces some carriers' numbers are told apart by (a detection rule's
  // rawPattern). Detection reads it; the carriers, the parcel and the answer keep the number itself.
  const typed = typeof body.typedNumber === 'string' && body.typedNumber.length <= 80
    && normalizeTrackingNumber(body.typedNumber) === trackingNumber ? body.typedNumber : trackingNumber;
  const detected = detectCarrierMatch(typed);
  // The carrier whose page the number was typed on (`carrierHint`); the shape's own carrier, when it names one, prevails.
  const hint = typeof body.carrierHint === 'string' && AUTOMATIC_CARRIER_IDS.has(body.carrierHint) ? body.carrierHint : undefined;
  // Shared shapes need shipment evidence; the shape alone remains a suggestion.
  const answer = detected.carrier === 'unknown' || recognitionCandidates(typed).length > 0 || recognitionCandidates(typed, { phase: 'browser' }).length > 0
    ? await recognize(trackingNumber, typed, beforeAsking, signal, supportClient, countryHint, hint).catch((error: unknown) => {
      if (signal?.aborted) throw new HttpError(499, 'Carrier check cancelled');
      throw error;
    })
    : { trackingNumber, carrier: detected.carrier } satisfies ApiCarrierDetectionResponse;
  recordDetection(answer.carrier !== 'unknown' ? 'high' : detected.confidence);
  // The start of a number the device follows is most likely that number typed again: no case for it.
  if (supportClient && !signal?.aborted && body.prefixOfSaved !== true) await retainDetectionSupport(supportClient, answer, userId);
  // Nobody knows the number: a carrier that answers only with the delivery postcode may have it, as a parcel's page offers.
  const suggested = answer.carrier === 'unknown' && !answer.recognized && !answer.trackingFound
    ? suggestedPostcodeCarrier(trackingNumber, countryHint ?? null) : undefined;
  return suggested ? { ...answer, inputSuggested: { carrier: suggested as ApiCarrierId, field: 'dpdPostcode' } } : answer;
}
