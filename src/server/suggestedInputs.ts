import 'server-only';
import type { ApiCarrierDetectionResponse, ApiCarrierId } from '../generated/apiContract';
import { detectCarrierMatch, phoneDigitsCandidate } from '../lib/carriers';
import { AUTOMATIC_CARRIER_IDS } from './carriers';
import { isRecord, type JsonObject } from './types';

/** The inputs beyond the delivery postcode that the app sending a request can ask a reader for. */
export type ClientInputs = { phoneDigits: boolean };

/**
 * The inputs the app names in its `X-Peek-Inputs` header (comma-separated). An input that may find a parcel is
 * suggested only to an app that names it: apps from before it reject a parcel or an answer whose input they don't know.
 */
export function clientInputs(request: Request): ClientInputs {
  const named = (request.headers.get('x-peek-inputs') ?? '').split(',').map((name) => name.trim());
  return { phoneDigits: named.includes('phoneDigits') };
}

/** The one carrier that shows a number of this shape only with the last 4 digits of a phone (J&T China's JT numbers). */
function phoneDigitsCarrier(number: string): ApiCarrierId | undefined {
  const carrier = phoneDigitsCandidate(detectCarrierMatch(number).candidates, number);
  return carrier && AUTOMATIC_CARRIER_IDS.has(carrier) ? carrier as ApiCarrierId : undefined;
}

/**
 * For a parcel filed under no carrier that a check found no carrier or provider to answer for, the carrier its number
 * points to that shows it only with the last 4 digits of the recipient's or sender's phone, which its owner may give.
 * Nothing says that carrier has the parcel. A postcode already asked or suggested (`input_needed`, `input_suggested`)
 * comes first.
 */
export function suggestedPhoneDigits(row: JsonObject): { carrier: ApiCarrierId; field: 'phoneDigits' } | undefined {
  const metadata = isRecord(row.carrier_data) ? row.carrier_data : {};
  const routing = isRecord(metadata.routing) ? metadata.routing : {};
  if (row.carrier !== 'unknown' || row.last_synced_at == null || ['delivered', 'returned'].includes(String(row.current_stage))
    || (Array.isArray(row.tracking_events) && row.tracking_events.length > 0) || metadata.original_carrier
    || routing.last_event_at || routing.confirmed_carrier || routing.discovered_carrier || routing.input_needed
    || routing.input_suggested) return undefined;
  const carrier = phoneDigitsCarrier(String(row.tracking_number ?? ''));
  return carrier ? { carrier, field: 'phoneDigits' } : undefined;
}

/** An owner's parcel as served to the app: with the phone digits it may give (`routing.input_suggested`), when it asks for them. */
export function withSuggestedInputs(row: JsonObject, inputs: ClientInputs): JsonObject {
  const suggested = inputs.phoneDigits ? suggestedPhoneDigits(row) : undefined;
  if (!suggested) return row;
  const metadata = isRecord(row.carrier_data) ? row.carrier_data : {};
  const routing = isRecord(metadata.routing) ? metadata.routing : {};
  return { ...row, carrier_data: { ...metadata, routing: { ...routing, input_suggested: suggested } } };
}

/**
 * A carrier check's answer for a number nobody knew, with the carrier that shows it only with the phone digits when the
 * app asks for them; a postcode suggestion comes first.
 */
export function detectionWithInputs(answer: ApiCarrierDetectionResponse, inputs: ClientInputs): ApiCarrierDetectionResponse {
  if (!inputs.phoneDigits || answer.carrier !== 'unknown' || answer.recognized || answer.trackingFound || answer.inputSuggested) return answer;
  const carrier = phoneDigitsCarrier(answer.trackingNumber);
  return carrier ? { ...answer, inputSuggested: { carrier, field: 'phoneDigits' } } : answer;
}
