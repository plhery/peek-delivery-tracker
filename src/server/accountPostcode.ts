import 'server-only';
import type { StepRecord } from 'universal-parcel-scraper/node';
import { CARRIERS, carrierRequirements, normalizeTrackingNumber } from '../lib/carriers';
import { givenPostcodeCountry, readsPostcodeCountry, storedPostcodeCountry } from '../lib/postcodeCountry';
import type { CarrierId } from '../types';
import { stepOutcome } from './metrics';
import { withPostcodeCountry } from './postcodeCountry';
import { isRecord, type JsonObject } from './types';
import { packageCarrierValues } from './validation';

/**
 * An account's open parcel filed under a carrier that shows more with the delivery postcode
 * (the catalog's `showsMore`), saved without one, is given the postcode its owner gave their
 * latest parcel: the one given the same carrier first, otherwise the latest this carrier
 * takes. A postcode a carrier refused is left out. It is given once (`account_postcode`): a
 * postcode the owner clears or changes afterwards stays as they left it. A postcode the owner
 * typed and cleared marks the parcel too (`account_postcode` false, set by the database when the
 * postcode is emptied under the same carrier and number), so none is given in its place. The
 * next check asks the carrier with it, as with a postcode the owner typed, and a refused one
 * shows on the parcel page.
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

/** Whether the owner cleared the parcel's postcode: none of the account's is given in its place. */
export function postcodeCleared(parcel: JsonObject): boolean {
  return isRecord(parcel.carrier_data) && parcel.carrier_data[ACCOUNT_POSTCODE] === false;
}

/** A postcode as a carrier takes it for a number, and the country it is from. */
export interface FittingPostcode { postcode: string; country: string | null }

/** The postcode as `carrier` takes it for `number`, checked as from `country`, or null when it does not take it. */
function fitting(carrier: string, number: string, postcode: string, country: string | null): FittingPostcode | null {
  try {
    const values = packageCarrierValues({ carrier, dpdPostcode: postcode, dpdPostcodeCountry: country ?? '' }, number);
    return values.dpdPostcode ? { postcode: values.dpdPostcode, country: values.dpdPostcodeCountry } : null;
  } catch {
    // A postcode this carrier does not take, such as a Swiss one where five digits are asked.
    return null;
  }
}

/**
 * The postcode its owner gave their parcels that `carrier` takes for `number`, from `given`, newest parcel first
 * (SupabaseServiceClient.accountPostcodes): the one given the same carrier first, otherwise the latest this carrier
 * takes, as the Add sheet offers it (src/lib/postcodeMemory.ts). A postcode a carrier refused is left out. Each is
 * checked as from the country it was given from (`givenPostcodeCountry`); a carrier whose lookup reads the country
 * takes none whose country is not known.
 */
export function accountPostcodeFor(carrier: string, number: string, given: readonly JsonObject[]): FittingPostcode | null {
  const seen = new Set<string>();
  const readsCountry = Object.hasOwn(CARRIERS, carrier) && readsPostcodeCountry(carrier as CarrierId, number);
  const fits = given.flatMap((row) => {
    const from = String(row.carrier);
    const postcode = typeof row.dpd_postcode === 'string' ? row.dpd_postcode.trim() : '';
    const refused = row.dpd_postcode_verified === false || (isRecord(row.postcode_refused) && row.postcode_refused.carrier === from);
    if (!postcode || refused || !Object.hasOwn(CARRIERS, from)) return [];
    const country = givenPostcodeCountry(from as CarrierId, storedPostcodeCountry({ postcode_country: row.postcode_country }, postcode));
    if (!country && readsCountry) return [];
    // An account's parcels often repeat one address: each is checked once, at its newest.
    const key = JSON.stringify([from, postcode, country]);
    if (seen.has(key)) return [];
    seen.add(key);
    const fit = fitting(carrier, number, postcode, country);
    return fit ? [{ from, ...fit }] : [];
  });
  const chosen = fits.find(({ from }) => from === carrier) ?? fits[0];
  return chosen ? { postcode: chosen.postcode, country: chosen.country } : null;
}

/**
 * The write that gives the parcel its owner's postcode, from `given`, their parcels' postcodes
 * newest parcel first (SupabaseServiceClient.accountPostcodes), or null when none of them fits
 * its carrier. The postcode is checked as from the country it was given with, and keeps it
 * where this carrier's lookup reads it.
 */
export function accountPostcodeWrite(parcel: JsonObject, given: readonly JsonObject[]): JsonObject | null {
  const carrier = String(parcel.carrier);
  if (!wantsAccountPostcode(parcel, carrier)) return null;
  const chosen = accountPostcodeFor(carrier, String(parcel.tracking_number ?? ''), given);
  if (!chosen) return null;
  const data = withPostcodeCountry(parcel.carrier_data, chosen.postcode, chosen.country);
  data[ACCOUNT_POSTCODE] = true;
  // As for a postcode the owner types: their input for the confirmed carrier replaces the saved one.
  if (isRecord(data.routing) && data.routing.confirmed_carrier === carrier) {
    data.routing = { ...data.routing, confirmed_postcode: chosen.postcode };
  }
  return { dpd_postcode: chosen.postcode, carrier_data: data, last_synced_at: null, sync_status: 'pending', sync_error: null };
}

/*
 * A parcel another carrier hands to PostNL under a number its domestic tracker holds (a 3S barcode, or a
 * postal item from abroad) shows there, with its delivery slot, only with the delivery postcode; without it,
 * PostNL's international tracker answers, later and without the slot. An account's parcel is looked up
 * there with the postcode its owner typed for it, for that leg (`universal_input`) or for the parcel, and
 * otherwise with the account's, as the Add sheet offers it, unless the owner cleared the parcel's. The domestic tracker answers a wrong postcode
 * as an unknown barcode, and the international one then answers: that miss is kept (`handoff_postcode`), so
 * the postcode is not sent again for that number, and nobody is told, as nobody gave it for this leg. A
 * guest's parcel is asked as before.
 */

/** carrier_data's record of the postcode a handoff's carrier was last asked with, and whether it found the parcel. */
export const HANDOFF_POSTCODE = 'handoff_postcode';

/** The carriers whose lookup says in a step of its own whether a postcode found the parcel: PostNL's domestic tracker. */
const POSTCODE_STEPS: Readonly<Record<string, string>> = { 'spring-gds': 'domestic' };

export interface HandoffPostcodeMark { carrier: string; number: string; postcode: string; country: string | null; found: boolean }

/** Whether an account's parcel is asked at `carrier`, which it is handed to under `number`, with a postcode. */
export function wantsHandoffPostcode(parcel: JsonObject, carrier: string, number: string): boolean {
  return typeof parcel.user_id === 'string' && parcel.one_off !== true && Object.hasOwn(POSTCODE_STEPS, carrier)
    && Object.hasOwn(CARRIERS, carrier)
    && carrierRequirements(carrier as CarrierId, number).some(({ field }) => field === 'dpdPostcode');
}

/** The parcel's record of the postcode `carrier` was asked with for `number`, if it has one. */
export function handoffPostcodeMark(parcel: JsonObject, carrier: string, number: string): HandoffPostcodeMark | null {
  const mark = isRecord(parcel.carrier_data) ? parcel.carrier_data[HANDOFF_POSTCODE] : null;
  if (!isRecord(mark) || mark.carrier !== carrier || typeof mark.number !== 'string' || typeof mark.postcode !== 'string'
    || typeof mark.found !== 'boolean' || normalizeTrackingNumber(mark.number) !== normalizeTrackingNumber(number)) return null;
  return { carrier, number: mark.number, postcode: mark.postcode, country: typeof mark.country === 'string' ? mark.country : null,
    found: mark.found };
}

/**
 * The postcode its owner typed for the parcel that `carrier` takes for `number`: the one given to the
 * universal providers for that number, else the parcel's own, each from the country it was given with.
 */
export function typedHandoffPostcode(parcel: JsonObject, carrier: string, number: string): FittingPostcode | null {
  const data = isRecord(parcel.carrier_data) ? parcel.carrier_data : {};
  const input = isRecord(data.universal_input) ? data.universal_input : null;
  const leg = input && typeof input.number === 'string' && typeof input.postcode === 'string' && input.postcode.trim()
    && normalizeTrackingNumber(input.number) === normalizeTrackingNumber(number) ? input.postcode.trim() : '';
  const own = typeof parcel.dpd_postcode === 'string' ? parcel.dpd_postcode.trim() : '';
  for (const postcode of [leg, own]) {
    const fit = postcode ? fitting(carrier, number, postcode, storedPostcodeCountry(data, postcode) ?? null) : null;
    if (fit) return fit;
  }
  return null;
}

/**
 * What the steps of a lookup with a postcode say of it: found the parcel (true), missed it while the carrier still
 * answered for the number (false), or neither, as when the step failed or nobody knew the number (null).
 */
export function handoffPostcodeFound(carrier: string, steps: readonly StepRecord[], answered: boolean): boolean | null {
  const step = POSTCODE_STEPS[carrier];
  const record = [...steps].reverse().find((candidate) => candidate.carrier === carrier && candidate.step === step);
  if (!record) return null;
  if (record.outcome === 'ok') return true;
  return answered && stepOutcome(record.outcome, record.error) === 'no_history' ? false : null;
}
