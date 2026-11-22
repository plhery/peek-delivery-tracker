import { authenticatedFetch, type ApiAuth } from './apiClient';
import type { ApiCarrierDetectionRequest, ApiCarrierDetectionResponse } from '../generated/apiContract';
import { CARRIERS, normalizeTrackingNumber } from './carriers';
import { timeZoneRegion } from './postcodeExample';

/**
 * The number as typed, when it differs from the number itself: its dashes and spaces tell some carriers' numbers
 * apart, and detection reads them.
 */
export function typedNumber(typed: string, trackingNumber: string): { typedNumber?: string } {
  const trimmed = typed.trim();
  return trimmed !== trackingNumber && trimmed.length <= 80 ? { typedNumber: trimmed } : {};
}

/**
 * Which carrier knows the number, given as typed, for the reader's country: the one picked for it, else the browser
 * clock's (`timeZoneRegion`), else the server takes the IP country. The answer says which (`lookupCountry`).
 */
export async function lookupCarrier(typed: string, auth: ApiAuth, signal?: AbortSignal, country: string | null = timeZoneRegion()) {
  const trackingNumber = normalizeTrackingNumber(typed);
  const body: ApiCarrierDetectionRequest = { trackingNumber, ...typedNumber(typed, trackingNumber), ...(country ? { lookupCountryHint: country } : {}) };
  const response = await authenticatedFetch('/api/carriers/detect', auth, {
    method: 'POST', body: JSON.stringify(body), signal,
  });
  if (!response.ok) throw new Error('Carrier lookup unavailable');
  const result = await response.json() as ApiCarrierDetectionResponse;
  if (result.trackingNumber !== trackingNumber || !Object.hasOwn(CARRIERS, result.carrier)) {
    throw new Error('Invalid carrier lookup response');
  }
  if (result.carrier === 'amazon-shipping' && !['available', 'expired'].includes(result.amazonShippingStatus ?? '')) throw new Error('Unverified Amazon Shipping response');
  return result;
}
