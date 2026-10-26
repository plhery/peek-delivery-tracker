import 'server-only';
import type { TrackingInput } from 'universal-parcel-scraper/node';
import { storedPostcodeCountry } from '../lib/postcodeCountry';
import { isRecord, type JsonObject } from './types';

/**
 * The country of a delivery postcode from outside the carrier's home country
 * (src/lib/postcodeCountry.ts) is kept in `carrier_data.postcode_country`
 * with the postcode it was given with, and goes to the carrier with that
 * postcode as the lookup input's `postcodeCountry`. A carrier adapter that
 * does not read it yet asks as before.
 */

/** A carrier lookup's input, with the country of a postcode from abroad. */
export function carrierLookupInput(
  number: string,
  trackingUrl: string | null,
  postcode: string | null,
  postcodeCountry?: string | null,
  language?: string | null,
): TrackingInput & { postcodeCountry?: string } {
  return { number, trackingUrl, postcode, ...(postcode && postcodeCountry ? { postcodeCountry } : {}), ...(language ? { language } : {}) };
}

/** carrier_data with the parcel's postcode bound to its country, or without a country. */
export function withPostcodeCountry(data: unknown, postcode: unknown, country: string | null | undefined): JsonObject {
  const next: JsonObject = { ...(isRecord(data) ? data : {}) };
  delete next.postcode_country;
  if (typeof postcode === 'string' && postcode && country) next.postcode_country = { postcode, country };
  return next;
}

/** Whether the parcel's postcode is from `country`, or from the carrier's home without one. */
export function holdsPostcodeCountry(parcel: JsonObject, country: string | null | undefined): boolean {
  const postcode = typeof parcel.dpd_postcode === 'string' ? parcel.dpd_postcode : null;
  return (storedPostcodeCountry(parcel.carrier_data, postcode) ?? null) === (country ?? null);
}

/**
 * The write that gives the parcel's postcode `country`, or null when it has it
 * already. A new country is a new input: as for a new postcode
 * (`clear_carrier_wait`), the carrier's wait ends. The postcode is written
 * again, which renews the tracking generation, so a check that read the
 * parcel before this write is set aside.
 */
export function postcodeCountryWrite(parcel: JsonObject, country: string | null | undefined): JsonObject | null {
  if (holdsPostcodeCountry(parcel, country)) return null;
  const data = withPostcodeCountry(parcel.carrier_data, parcel.dpd_postcode, country);
  if (isRecord(data.routing)) {
    const routing: JsonObject = { ...data.routing };
    delete routing.direct_retry_at;
    delete routing.next_check_at;
    if (isRecord(routing.failures)) {
      const failures = { ...routing.failures };
      delete failures[String(parcel.carrier)];
      routing.failures = failures;
    }
    data.routing = routing;
  }
  return { carrier_data: data, dpd_postcode: parcel.dpd_postcode ?? null };
}
