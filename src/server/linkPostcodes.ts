import 'server-only';

import { CarrierError, carrierErrorKind, retryAfterMsOf } from 'universal-parcel-scraper';
import { trackCarrier, UniversalTracker, type AdapterRegistry } from 'universal-parcel-scraper/node';
import { createAdapterRegistry } from './adapterRegistry';
import { carrierLookupInput } from './postcodeCountry';
import { hostStepRecorder } from './stepRecorder';

/**
 * The delivery postcode the owner of a lookup adds for the carrier that asks
 * for it (`routing.input_needed`): another carrier holding the parcel, or its
 * own. The carrier is asked before anything is filed, so a postcode it refuses
 * shows nothing, and each refusal counts against the link.
 */

/** Postcodes a carrier may refuse per link and day. */
export const LINK_POSTCODE_TRIES = 5;
/** How long the owner waits for the carrier's answer. */
const CHECK_BUDGET_MS = 25_000;

let registry: AdapterRegistry | undefined;
let universal: UniversalTracker | undefined;

export type PostcodeCheck =
  | { outcome: 'accepted' }
  /** `retryAfterMs` when the carrier takes no postcode for a while after several wrong ones. */
  | { outcome: 'refused'; retryAfterMs?: number }
  | { outcome: 'unavailable' };

/** The stable detail of the first carrier error in the chain, such as `portal_not_found`. */
function reasonOf(error: unknown): string | undefined {
  let current = error;
  for (let i = 0; i < 8 && current instanceof Error; i++, current = current.cause) {
    if (current instanceof CarrierError) return current.reason;
  }
  return undefined;
}

/**
 * Asks the carrier's own tracking, never a provider, for the parcel with the
 * postcode. The answer is not kept: the sync asks again for the filed parcel.
 * A carrier that asks for the input again, finds it invalid or no longer finds
 * the parcel refused the postcode, and so does a portal that finds nothing
 * with it (PostNL's domestic tracker answers a wrong postcode as an unknown
 * barcode); any other failure says nothing about it. A postcode from abroad
 * goes with its country.
 */
export async function checkLinkPostcode(
  carrier: string,
  trackingNumber: string,
  postcode: string,
  country?: string | null,
  signal?: AbortSignal,
): Promise<PostcodeCheck> {
  const adapters = registry ??= createAdapterRegistry();
  if (!adapters.for(carrier)) return { outcome: 'unavailable' };
  try {
    await trackCarrier(carrier, carrierLookupInput(trackingNumber, null, postcode, country), {
      registry: adapters,
      // Never asked: the carrier has an adapter of its own.
      universal: universal ??= new UniversalTracker(),
      recorder: hostStepRecorder(),
      budgetMs: CHECK_BUDGET_MS,
      signal,
    });
    return { outcome: 'accepted' };
  } catch (error) {
    signal?.throwIfAborted();
    const kind = carrierErrorKind(error);
    if (kind !== 'input_required' && kind !== 'invalid_input' && kind !== 'not_found'
      && !(kind === 'indeterminate' && reasonOf(error) === 'portal_not_found')) return { outcome: 'unavailable' };
    const retryAfterMs = retryAfterMsOf(error);
    return retryAfterMs ? { outcome: 'refused', retryAfterMs } : { outcome: 'refused' };
  }
}
