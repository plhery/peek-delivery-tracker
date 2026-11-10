import 'server-only';

import { labelledNumbersInText, nonTrackingShape, numbersInText, type NumberInText } from '../peek/lookup/reading';
import { claimAccountTracking } from './accountTrackingBudget';
import { verifyAmazonShippingAddition } from './amazonShippingEligibility';
import { HttpError } from './api';
import { wakeSyncWorker } from './background';
import { settleTypedSupportCases } from './reviewQueues';
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

/**
 * The tracking numbers a text names: those a carrier claims with certainty, read as the Add sheet reads a pasted
 * message (`numbersInText`) with its guard that a shop's order number is never one, and after them those the
 * text's own label calls a tracking number though their shape proves no carrier (`labelledNumbersInText`). Each
 * once, at most MOST_NUMBERS_PER_TEXT, from the first MOST_TEXT_LENGTH of the text.
 */
export function trackingNumbersInText(text: string): NumberInText[] {
  const read = text.slice(0, MOST_TEXT_LENGTH);
  const certain = numbersInText(read).filter(({ normalized }) => nonTrackingShape(normalized)?.kind !== 'order');
  const named = new Set(certain.map(({ normalized }) => normalized));
  return [...certain, ...labelledNumbersInText(read).filter(({ normalized }) => !named.has(normalized))].slice(0, MOST_NUMBERS_PER_TEXT);
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

/**
 * Adds the numbers to an account, each as the Add sheet would, with the same daily allowance, parcel limit and
 * first check. A number the account already follows is left as it is and costs nothing. Once the allowance or the
 * parcel limit stops one, the numbers after it are not tried.
 */
export async function addNumbersToAccount(
  service: SupabaseServiceClient,
  userId: string,
  numbers: readonly NumberInText[],
  label: string,
): Promise<ParcelAddition[]> {
  const known = await service.accountTrackingNumbers(userId, numbers.map(({ normalized }) => normalized));
  const additions: ParcelAddition[] = [];
  let stopped: ParcelAddition | null = null;
  for (const { normalized, match } of numbers) {
    const carrier = match.carrier;
    if (known.has(normalized)) {
      additions.push({ trackingNumber: normalized, carrier, outcome: 'known', packageId: known.get(normalized) ?? null });
      continue;
    }
    if (stopped) {
      additions.push({ ...stopped, trackingNumber: normalized, carrier } as ParcelAddition);
      continue;
    }
    let values;
    try {
      values = newPackageValues({ trackingNumber: match.trackingNumber, label, carrier, trackingUrl: match.trackingUrl ?? '' });
    } catch (error) {
      if (!(error instanceof HttpError)) throw error;
      additions.push({ trackingNumber: normalized, carrier, outcome: 'refused', reason: error.message });
      continue;
    }
    const trackingNumber = values.trackingNumber;
    try {
      await claimAccountTracking(service, userId, 'lookup');
    } catch (error) {
      if (!(error instanceof HttpError) || error.status !== 429) throw error;
      stopped = { trackingNumber, carrier: values.carrier, outcome: 'limited', retryAfter: retryAfter(error) };
      additions.push(stopped);
      continue;
    }
    try {
      await verifyAmazonShippingAddition(values.carrier, trackingNumber);
    } catch (error) {
      if (!(error instanceof HttpError)) throw error;
      additions.push(error.status === 400
        ? { trackingNumber, carrier: values.carrier, outcome: 'refused', reason: error.message }
        : { trackingNumber, carrier: values.carrier, outcome: 'unavailable' });
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
    await settleTypedSupportCases(service, trackingNumber);
    const jobId = await queueFirstCheck(service, userId, packageId);
    additions.push({ trackingNumber, carrier: values.carrier, outcome: 'added', packageId, jobId });
  }
  return additions;
}

/** Reads a text's tracking numbers and adds them to the account (addNumbersToAccount), named `label`. */
export async function addParcelsFromText(
  service: SupabaseServiceClient,
  userId: string,
  text: string,
  label: string,
): Promise<ParcelAddition[]> {
  const numbers = trackingNumbersInText(text);
  return numbers.length ? addNumbersToAccount(service, userId, numbers, label) : [];
}
