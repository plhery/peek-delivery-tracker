import 'server-only';

import { isAmazonTrackingNumber } from '../lib/amazon';
import {
  carrierNumberInText, fromCarrier, labelledNumbersInText, nonTrackingShape, numberAlone, numbersInText, type NumberInText, type TextTrust,
} from '../peek/lookup/reading';
import type { ApiShopWait } from '../generated/apiContract';
import type { CarrierId } from '../types';
import { claimAccountTracking } from './accountTrackingBudget';
import { verifyAmazonShippingAddition } from './amazonShippingEligibility';
import { HttpError } from './api';
import { wakeSyncWorker } from './background';
import { accountLookupCountry, rememberLookupCountry } from './lookupCountry';
import { captureOperationalError } from './observability';
import { reviewDemoCarrier, reviewDemoNumbersInText } from './reviewDemo';
import { settleTypedSupportCases } from './reviewQueues';
import { shopLinkNumbers, withShopLinkNumbers } from './shopLinks';
import { keepShopWaits, waitableLinks } from './shopWaits';
import { SupabaseError, type SupabaseServiceClient } from './supabase';
import { newPackageValues } from './validation';

/** The most numbers one text adds: an email or a message names a few parcels, never a list of them. */
export const MOST_NUMBERS_PER_TEXT = 10;
/**
 * The most of a text that is read, in UTF-16 units: far more than a shipping email's words, and a bound on the
 * reading's time, which grows faster than the text.
 */
export const MOST_TEXT_LENGTH = 16_000;

/** What became of one number a text named. */
export type ParcelAddition =
  | { trackingNumber: string; carrier: string; outcome: 'added'; packageId: string; jobId: string | null }
  | { trackingNumber: string; carrier: string; outcome: 'known'; packageId: string | null }
  /** Not a number the account can follow, such as an Amazon number only Amazon's own account tracks. */
  | { trackingNumber: string; carrier: string; outcome: 'refused'; reason: string }
  /** The account's daily allowance, or every account's hourly one, is used up. */
  | { trackingNumber: string; carrier: string; outcome: 'limited'; retryAfter: number | null }
  /** The account follows as many parcels as it may. */
  | { trackingNumber: string; carrier: string; outcome: 'full' }
  | { trackingNumber: string; carrier: string; outcome: 'unavailable' };

/** A label that ends its line, as an email's plain text prints a table: "Sendungsnummer:" and the number below. */
const LABEL_ENDING_ITS_LINE = /:[ \t]*(?:\r?\n[ \t]*){1,2}(?=\S)/g;
/**
 * A label alone on its line without a colon, as a shop's table prints one: "Your parcel number", a blank line, then the
 * number alone on its line. It is joined as a colon would join it; the label reader still decides whether the words
 * label a tracking number, so an order's, a phone's or a sentence's line stays unread.
 */
const LABEL_LINE_ABOVE_NUMBER = /^([ \t]*\p{L}[^\n\d:]{2,58}?)(?<![.!?])[ \t]*(?:\r?\n[ \t]*){1,2}(?=(?=[A-Za-z0-9 -]*\d)[A-Za-z0-9][A-Za-z0-9 -]{5,40}[ \t]*(?:\r?\n|$))/gmu;

/**
 * The tracking numbers a text names: those a carrier claims with certainty, read as the Add sheet reads a pasted
 * message (`numbersInText`) with its guard that a shop's order number is never one, and after them those the
 * text's own label calls a tracking number though their shape proves no carrier (`labelledNumbersInText`). Each
 * once, at most MOST_NUMBERS_PER_TEXT, from the first MOST_TEXT_LENGTH of the text. A label alone on its line
 * names the number on the next.
 *
 * `carriers` are those the text's writer is, when it is a carrier's own email: a number whose shape proved no
 * carrier is then theirs (`fromCarrier`), and when no number was read, the one that stands without a label in
 * their shape is taken (`carrierNumberInText`).
 *
 * `trust` is for a text someone chose to send, as an assistant does (`TextTrust`).
 */
export function trackingNumbersInText(text: string, carriers: readonly CarrierId[] = [], trust?: TextTrust): NumberInText[] {
  const read = text.slice(0, MOST_TEXT_LENGTH);
  const certain = numbersInText(read).filter(({ normalized }) => nonTrackingShape(normalized)?.kind !== 'order');
  const named = new Set(certain.map(({ normalized }) => normalized));
  const labelled = labelledNumbersInText(read.replace(LABEL_ENDING_ITS_LINE, ': ').replace(LABEL_LINE_ABOVE_NUMBER, '$1: '), trust);
  const numbers = [...certain, ...labelled.filter(({ normalized }) => !named.has(normalized))];
  if (!numbers.length) {
    const bare = carrierNumberInText(read, carriers) ?? (trust ? numberAlone(read, trust === 'named') : null);
    if (bare) numbers.push(bare);
  }
  return numbers.slice(0, MOST_NUMBERS_PER_TEXT).map((number) => fromCarrier(number, carriers));
}

/**
 * The carriers that are a marketplace's own logistics: an email names their number beside the last-mile carrier's for
 * one package, and their tracker follows the whole journey and names the next leg itself (AliExpress's, Cainiao's).
 */
const MARKETPLACE_LOGISTICS: ReadonlySet<string> = new Set(['aliexpress']);

/** An email's two numbers for one package: the marketplace's logistics number and the last-mile carrier's. */
export interface TwinNumbers {
  earlier: NumberInText;
  later: NumberInText;
}

/**
 * The twins among a text's numbers: exactly two numbers, one a marketplace's logistics number and the other a last-mile
 * carrier's, or none. A text that names more numbers, or two of either kind, may name several packages.
 */
export function twinNumbers(numbers: readonly NumberInText[]): TwinNumbers | null {
  if (numbers.length !== 2) return null;
  const [first, second] = numbers as [NumberInText, NumberInText];
  const marketplace = (number: NumberInText) => MARKETPLACE_LOGISTICS.has(number.match.carrier);
  if (marketplace(first) === marketplace(second)) return null;
  return marketplace(first) ? { earlier: first, later: second } : { earlier: second, later: first };
}

/** Keeps a pair for the parcel that follows its package. A failure is reported, and the email's parcels stay. */
async function keepTwins(service: SupabaseServiceClient, userId: string, packageId: string, { earlier, later }: TwinNumbers): Promise<void> {
  try {
    await service.recordTwinNumbers(userId, packageId, earlier.normalized, later.normalized);
  } catch (error) {
    if (!(error instanceof SupabaseError)) throw error;
    captureOperationalError(error, { component: 'packages', operation: 'record_twin_numbers' });
  }
}

/**
 * Queues the first check of a parcel just added to an account. A queue that fails marks the parcel, whose next
 * refresh tries again.
 */
export async function queueFirstCheck(service: SupabaseServiceClient, userId: string, packageId: string): Promise<string | null> {
  try {
    const job = await service.enqueueSyncJob({ userId, packageId, reason: 'added' });
    wakeSyncWorker();
    return typeof job.row.id === 'string' ? job.row.id : null;
  } catch (error) {
    if (!(error instanceof SupabaseError)) throw error;
    await service.updatePackage(packageId, {
      sync_status: 'error',
      sync_error: 'The first tracking check could not be queued. Try again shortly.',
    });
    return null;
  }
}

function retryAfter(error: HttpError): number | null {
  const value = Number(new Headers(error.headers).get('Retry-After'));
  return Number.isFinite(value) && value > 0 ? value : null;
}

/** How a text's numbers are added. */
export interface AddingOptions {
  /**
   * False to add no number the account doesn't follow yet, as for an email that says its parcel was delivered: only
   * those it follows are then reported.
   */
  addNew?: boolean;
  /** Mail delivery can retry a temporary eligibility lookup without claiming another allowance. */
  retryEligibility?: boolean;
  /** How far the text is read beyond an email's (trackingNumbersInText). */
  trust?: TextTrust;
  /**
   * True for an email, whose two numbers for one package (`twinNumbers`) are followed as one parcel: the account's
   * under either number, else the marketplace's number alone, added. The pair is kept for that parcel, so either
   * number finds it later, and two parcels the account follows under them merge (`package_twin_numbers`).
   */
  twins?: boolean;
  /**
   * Called with the waits kept for the text's shop links that name no parcel yet (shopWaits.ts), which the account
   * then waits for. A text's links wait only when `addNew` holds.
   */
  onWaiting?: (waits: ApiShopWait[]) => void;
}

/**
 * Adds the numbers to an account, each as the Add sheet would, with the same daily allowance, parcel limit and
 * first check. A number the account already follows is left as it is and costs nothing. Once the allowance or the
 * parcel limit stops one, the numbers after it are not tried. No request of the reader's comes with a text, so the
 * numbers are added as from the country the account's latest parcel was looked up from (`accountLookupCountry`).
 */
export async function addNumbersToAccount(
  service: SupabaseServiceClient,
  userId: string,
  numbers: readonly NumberInText[],
  label: string,
  { addNew = true, retryEligibility = false, twins = false }: AddingOptions = {},
): Promise<ParcelAddition[]> {
  const known = await service.accountTrackingNumbers(userId, numbers.map(({ normalized }) => normalized));
  const pair = twins && !numbers.some(({ normalized }) => reviewDemoCarrier(userId, normalized)) ? twinNumbers(numbers) : null;
  // The parcel that follows the pair's package: the account's under either number, else the one the marketplace's
  // number adds, tried first.
  let twin = pair ? known.get(pair.earlier.normalized) ?? known.get(pair.later.normalized) ?? null : null;
  const additions: ParcelAddition[] = [];
  let stopped: ParcelAddition | null = null;
  // Read once, for the first number to add.
  let country: Promise<string | null> | undefined;
  for (const number of pair ? [pair.earlier, pair.later] : numbers) {
    const { normalized, match } = number;
    // A review demo number is filed under its scenario's carrier, costs no allowance and is never checked (reviewDemo.ts).
    const demo = reviewDemoCarrier(userId, normalized);
    // An Amazon-shaped number is Amazon Shipping's when Amazon Shipping's tracker follows it, as the Add sheet's
    // detection decides; otherwise only the buyer's Amazon account tracks it, and it is refused.
    const amazon = !demo && isAmazonTrackingNumber(normalized);
    const carrier = demo ?? (amazon ? 'amazon-shipping' : match.carrier);
    if (known.has(normalized)) {
      additions.push({ trackingNumber: normalized, carrier, outcome: 'known', packageId: known.get(normalized) ?? null });
      continue;
    }
    if (twin) {
      additions.push({ trackingNumber: normalized, carrier, outcome: 'known', packageId: twin });
      continue;
    }
    if (!addNew) continue;
    if (stopped) {
      additions.push({ ...stopped, trackingNumber: normalized, carrier } as ParcelAddition);
      continue;
    }
    let values;
    country ??= accountLookupCountry(service, userId);
    try {
      values = newPackageValues({ trackingNumber: match.trackingNumber, label, carrier, trackingUrl: demo ? '' : match.trackingUrl ?? '' },
        await country);
    } catch (error) {
      if (!(error instanceof HttpError)) throw error;
      additions.push({ trackingNumber: normalized, carrier, outcome: 'refused', reason: error.message });
      continue;
    }
    const trackingNumber = values.trackingNumber;
    // Amazon Shipping's check comes before the allowance, so a number it refuses, or an email sent again while it
    // can't answer, costs none.
    try {
      if (!demo) {
        const verify = () => verifyAmazonShippingAddition(values.carrier, trackingNumber);
        await verify().catch((error: unknown) => {
          if (retryEligibility && error instanceof HttpError && error.status === 503) return verify();
          throw error;
        });
      }
    } catch (error) {
      if (!(error instanceof HttpError)) throw error;
      additions.push(error.status === 400
        ? { trackingNumber, carrier: amazon ? 'amazon-logistics' : values.carrier, outcome: 'refused', reason: error.message }
        : { trackingNumber, carrier: values.carrier, outcome: 'unavailable' });
      continue;
    }
    try {
      if (!demo) await claimAccountTracking(service, userId, 'lookup');
    } catch (error) {
      if (!(error instanceof HttpError) || error.status !== 429) throw error;
      stopped = { trackingNumber, carrier: values.carrier, outcome: 'limited', retryAfter: retryAfter(error) };
      additions.push(stopped);
      continue;
    }
    let packageId: string;
    try {
      packageId = await service.createAccountPackage(userId, trackingNumber, values.label, values.carrier, values.trackingUrl, values.dpdPostcode);
    } catch (error) {
      if (error instanceof SupabaseError && error.code === 'P0001') {
        stopped = { trackingNumber, carrier: values.carrier, outcome: 'full' };
        additions.push(stopped);
        continue;
      }
      if (error instanceof SupabaseError && error.status === 409) {
        additions.push({ trackingNumber, carrier: values.carrier, outcome: 'known', packageId: null });
        continue;
      }
      throw error;
    }
    known.set(normalized, packageId);
    if (number === pair?.earlier) twin = packageId;
    if (demo) {
      additions.push({ trackingNumber, carrier: values.carrier, outcome: 'added', packageId, jobId: null });
      continue;
    }
    // Marked new as the Add sheet marks it, so its first check asks a second batch of carriers when the first knows
    // none of them. A reload that fails leaves it unmarked, not unchecked.
    const parcel = await service.getPackage(packageId).catch((error: unknown) => {
      if (!(error instanceof SupabaseError)) throw error;
      captureOperationalError(error, { component: 'packages', operation: 'remember_lookup_country' });
      return null;
    });
    if (parcel) await rememberLookupCountry(service, parcel, await country);
    await settleTypedSupportCases(service, trackingNumber);
    const jobId = await queueFirstCheck(service, userId, packageId);
    additions.push({ trackingNumber, carrier: values.carrier, outcome: 'added', packageId, jobId });
  }
  if (pair && twin) await keepTwins(service, userId, twin, pair);
  return additions;
}

/**
 * Reads a text's tracking numbers and adds them to the account (addNumbersToAccount), named `label`. `carriers`
 * are those the text's writer is, for a carrier's own email (trackingNumbersInText). In a review demo account the
 * text's demo numbers come first (reviewDemo.ts). A shop's tracking-page link adds the parcels its platform names
 * (shopLinkNumbers), after the text's own numbers; one that names none yet, as before the shop ships, is kept as a wait
 * for its parcels when the text adds no parcel of its own (keepShopWaits).
 */
export async function addParcelsFromText(
  service: SupabaseServiceClient,
  userId: string,
  text: string,
  label: string,
  carriers: readonly CarrierId[] = [],
  options: AddingOptions = {},
): Promise<ParcelAddition[]> {
  const demos = reviewDemoNumbersInText(userId, text.slice(0, MOST_TEXT_LENGTH));
  const named = new Set(demos.map(({ normalized }) => normalized));
  const read = trackingNumbersInText(text, carriers, options.trust).filter(({ normalized }) => !named.has(normalized));
  const links = await shopLinkNumbers(text.slice(0, MOST_TEXT_LENGTH));
  const linked = links.numbers.filter(({ normalized }) => !named.has(normalized));
  const numbers = [...demos, ...withShopLinkNumbers(read, linked)].slice(0, MOST_NUMBERS_PER_TEXT);
  // A link that names no parcel yet waits, unless the text names the parcel itself.
  const waiting = options.addNew !== false && !numbers.length ? waitableLinks(links.unresolved) : [];
  if (waiting.length) {
    try {
      options.onWaiting?.(await keepShopWaits(service, userId, waiting, label));
    } catch (error) {
      captureOperationalError(error, { component: 'shop_links', operation: 'keep_wait' });
    }
  }
  return numbers.length ? addNumbersToAccount(service, userId, numbers, label, options) : [];
}
