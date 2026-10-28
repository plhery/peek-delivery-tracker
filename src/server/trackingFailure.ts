import { CarrierError, carrierErrorKind, isPlaceholderNumber } from 'universal-parcel-scraper';
import { PLACEHOLDER_NUMBER } from '../lib/messages';

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

/**
 * A carrier's tracking that places the parcel with its group's unit in another
 * country, which the lookup's next step asks: DPD Germany's guest API or app
 * answering for a parcel DPD France now holds. The source answered; the
 * parcel is no longer its own.
 */
export const HANDED_OFF_REASONS: ReadonlySet<string> = new Set(['other_country']);

/**
 * A carrier's verification its adapter declines on purpose rather than guess at: JD's browser
 * service leaves a puzzle it cannot read, a stale one or a guest session that changed under it
 * unanswered. The adapter works; the parcel waits for a check that meets a readable one.
 */
export const ABSTAINED_REASONS: ReadonlySet<string> = new Set(['verification_unreadable']);

export { PLACEHOLDER_NUMBER } from '../lib/messages';

/** Why a carrier answered as it did (`parcel_found`, `postcode_mismatch`): the first reason in the error's causes. */
export function carrierErrorReason(error: unknown): string | undefined {
  const seen = new Set<Error>();
  let current = error;
  for (let depth = 0; current instanceof Error && !seen.has(current) && depth < 8; depth += 1) {
    seen.add(current);
    if (current instanceof CarrierError && typeof current.reason === 'string') {
      return /^[a-z][a-z0-9_]{0,63}$/.test(current.reason) ? current.reason : undefined;
    }
    current = current.cause;
  }
  return undefined;
}

/** A source's refusal of the number: one it does not issue, or a placeholder. A later release may take either. */
export function refusedNumberCode(code: unknown): boolean {
  return code === 'carrier:invalid_input' || code === PLACEHOLDER_NUMBER;
}

/**
 * Whether a stored parcel's number is a placeholder the scraper refuses to look up, such as 123456789: providers
 * merge other people's shipments under one. Such a parcel is served without what was stored from it (eventPlaces.ts).
 */
export function placeholderNumber(row: { tracking_number?: unknown }): boolean {
  return typeof row.tracking_number === 'string' && isPlaceholderNumber(row.tracking_number);
}

/** Stable public copy codes. Full diagnostics remain in the tracking audit. */
export function trackingFailureCode(error: unknown): string | null {
  // A number that names several parcels, such as an Australia Post consignment of
  // several articles, answers inconclusively: the owner has to add each parcel's own number.
  let current = error;
  for (let depth = 0; current instanceof Error && !(current instanceof CarrierError) && depth < 8; depth++) current = current.cause;
  if (current instanceof CarrierError && current.reason === 'several_parcels') return 'carrier:several_parcels';
  if (current instanceof CarrierError && current.reason === 'placeholder_number') return PLACEHOLDER_NUMBER;
  const kind = carrierErrorKind(error);
  return kind ? `carrier:${kind}` : null;
}

/** Do not blame one provider when several failed for different reasons. */
export function deferredTrackingFailure(
  failures: Record<string, { user_error?: string; kind: string }>,
  configuredCarrier: string,
): string | null {
  // The scraper refuses a placeholder number from every source alike: one refusal names it.
  if (Object.values(failures).some(failure => failure?.user_error === PLACEHOLDER_NUMBER)) return PLACEHOLDER_NUMBER;
  // The parcel's own carrier needs an input, refuses the number or finds several parcels under it:
  // only the parcel's details can change that.
  const own = failures[configuredCarrier]?.user_error;
  if (own === 'carrier:input_required' || own === 'carrier:invalid_input' || own === 'carrier:several_parcels') return own;
  const codes = Object.values(failures).map(failure => failure.user_error
    ?? ({ rate_limited: 'carrier:rate_limited', verification: 'carrier:challenge', not_found: 'carrier:not_found' } as Record<string, string>)[failure.kind]);
  return codes.length && codes[0] && codes.every(code => code === codes[0]) ? codes[0] : null;
}
