import { CarrierError, carrierErrorKind } from 'universal-parcel-scraper';

/**
 * A carrier's own tracking that does not know the item yet: Ukrposhta's status API
 * or portal, Royal Mail's, myDSV's search, Ecoscooting's order query; or one that lists
 * it without a scan (Pošta Srbije, Slovenská pošta, DHL eCommerce's Webtrack). The
 * source answered, but may not prove absence, so the adapter raises these as
 * inconclusive with these reasons. A regional tracker that does not list the item
 * (DHL eCommerce's Webtrack, `webtrack_not_found`) is no such answer: the carrier's
 * global one may know it.
 */
export const UNKNOWN_ITEM_REASONS: ReadonlySet<string> = new Set([
  'status_api_not_found', 'portal_not_found', 'no_history', 'webtrack_no_history',
]);

/** Stable public copy codes. Full diagnostics remain in the tracking audit. */
export function trackingFailureCode(error: unknown): string | null {
  // A number that names several parcels, such as an Australia Post consignment of
  // several articles, answers inconclusively: the owner has to add each parcel's own number.
  let current = error;
  for (let depth = 0; current instanceof Error && !(current instanceof CarrierError) && depth < 8; depth++) current = current.cause;
  if (current instanceof CarrierError && current.reason === 'several_parcels') return 'carrier:several_parcels';
  const kind = carrierErrorKind(error);
  return kind ? `carrier:${kind}` : null;
}

/** Do not blame one provider when several failed for different reasons. */
export function deferredTrackingFailure(
  failures: Record<string, { user_error?: string; kind: string }>,
  configuredCarrier: string,
): string | null {
  // The parcel's own carrier needs an input, refuses the number or finds several parcels under it:
  // only the parcel's details can change that.
  const own = failures[configuredCarrier]?.user_error;
  if (own === 'carrier:input_required' || own === 'carrier:invalid_input' || own === 'carrier:several_parcels') return own;
  const codes = Object.values(failures).map(failure => failure.user_error
    ?? ({ rate_limited: 'carrier:rate_limited', verification: 'carrier:challenge', not_found: 'carrier:not_found' } as Record<string, string>)[failure.kind]);
  return codes.length && codes[0] && codes.every(code => code === codes[0]) ? codes[0] : null;
}
