import 'server-only';
import { CARRIERS, carrierRequirements } from '../lib/carriers';
import { homePostcodeCountry, storedPostcodeCountry } from '../lib/postcodeCountry';
import type { CarrierId } from '../types';
import { withPostcodeCountry } from './postcodeCountry';
import { isRecord, type JsonObject } from './types';
import { packageCarrierValues } from './validation';

/**
 * An account's open parcel filed under a carrier that shows more with the delivery postcode
 * (the catalog's `showsMore`), saved without one, is given the postcode its owner gave their
 * latest parcel: the one given the same carrier first, otherwise the latest this carrier
 * takes. A postcode a carrier refused is left out. It is given once (`account_postcode`): a
 * postcode the owner clears or changes afterwards stays as they left it. The next check asks
 * the carrier with it, as with a postcode the owner typed, and a refused one shows on the
 * parcel page.
 */

/** carrier_data's mark of a parcel given its owner's postcode. */
export const ACCOUNT_POSTCODE = 'account_postcode';

function showsMorePostcode(carrier: string, number: string): boolean {
  return Object.hasOwn(CARRIERS, carrier) && carrierRequirements(carrier as CarrierId, number)
    .some(({ field, optional, showsMore }) => field === 'dpdPostcode' && optional === true && showsMore === true);
}

/** Whether the parcel, filed under `carrier` once checked, is to be given its owner's postcode. */
export function wantsAccountPostcode(parcel: JsonObject, carrier: string): boolean {
  const data = isRecord(parcel.carrier_data) ? parcel.carrier_data : {};
  return typeof parcel.user_id === 'string' && parcel.one_off !== true && parcel.archived_at == null
    && !['delivered', 'returned'].includes(String(parcel.current_stage ?? '')) && !String(parcel.dpd_postcode ?? '').trim()
    && data[ACCOUNT_POSTCODE] == null && showsMorePostcode(carrier, String(parcel.tracking_number ?? ''));
}

/**
 * The write that gives the parcel its owner's postcode, from `given`, their parcels' postcodes
 * newest parcel first (SupabaseServiceClient.accountPostcodes), or null when none of them fits
 * its carrier. The postcode keeps the country it was given with.
 */
export function accountPostcodeWrite(parcel: JsonObject, given: readonly JsonObject[]): JsonObject | null {
  const carrier = String(parcel.carrier);
  const number = String(parcel.tracking_number ?? '');
  if (!wantsAccountPostcode(parcel, carrier)) return null;
  const fitting = given.flatMap((row) => {
    const from = String(row.carrier);
    const postcode = typeof row.dpd_postcode === 'string' ? row.dpd_postcode.trim() : '';
    const refused = row.dpd_postcode_verified === false || (isRecord(row.postcode_refused) && row.postcode_refused.carrier === from);
    const country = Object.hasOwn(CARRIERS, from)
      ? storedPostcodeCountry({ postcode_country: row.postcode_country }, postcode) ?? homePostcodeCountry(from as CarrierId) : null;
    if (!postcode || refused || !country) return [];
    try {
      const values = packageCarrierValues({ carrier, dpdPostcode: postcode, dpdPostcodeCountry: country }, number);
      return values.dpdPostcode ? [{ from, postcode: values.dpdPostcode, country: values.dpdPostcodeCountry }] : [];
    } catch {
      // A postcode this carrier does not take, such as a Swiss one where five digits are asked.
      return [];
    }
  });
  const chosen = fitting.find(({ from }) => from === carrier) ?? fitting[0];
  if (!chosen) return null;
  const data = withPostcodeCountry(parcel.carrier_data, chosen.postcode, chosen.country);
  data[ACCOUNT_POSTCODE] = true;
  // As for a postcode the owner types: their input for the confirmed carrier replaces the saved one.
  if (isRecord(data.routing) && data.routing.confirmed_carrier === carrier) {
    data.routing = { ...data.routing, confirmed_postcode: chosen.postcode };
  }
  return { dpd_postcode: chosen.postcode, carrier_data: data, last_synced_at: null, sync_status: 'pending', sync_error: null };
}
