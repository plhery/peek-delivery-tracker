import 'server-only';
import { activeRequirements, normalizeCarrierInputs as normalizeScraperInputs } from 'universal-parcel-scraper';

export { AUTOMATIC_CARRIER_IDS, CARRIER_NAMES, activeRequirements, carrierAdapter, carrierDefinition, carrierTimezone, requiredRequirements, isValidS10TrackingNumber, supportsSwissPostHandoff } from 'universal-parcel-scraper';

/** The scraper's checked inputs, under the names the app stores them by. */
export function normalizeCarrierInputs(
  carrierId: string,
  trackingNumber: string,
  trackingUrl: string,
  dpdPostcode: string,
  phoneDigits = '',
): { trackingUrl: string | null; dpdPostcode: string | null; phoneDigits: string | null } {
  const inputs = normalizeScraperInputs(carrierId, trackingNumber, trackingUrl, dpdPostcode, phoneDigits);
  return { trackingUrl: inputs.trackingUrl, dpdPostcode: inputs.postcode, phoneDigits: inputs.phoneDigits };
}

/**
 * The phone digits a check sends `carrierId` for the parcel: the ones its owner gave, to the
 * carrier the parcel is filed under when it reads them, and to no other carrier or provider.
 */
export function parcelPhoneDigits(parcel: { carrier?: unknown; tracking_number?: unknown; phone_digits?: unknown }, carrierId: string): string | null {
  const digits = typeof parcel.phone_digits === 'string' ? parcel.phone_digits : '';
  return digits && carrierId === parcel.carrier
    && activeRequirements(carrierId, String(parcel.tracking_number ?? '')).some(({ field }) => field === 'phoneDigits') ? digits : null;
}
