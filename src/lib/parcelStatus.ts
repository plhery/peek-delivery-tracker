import { scanDate } from './format';
import { estimateDay, localizedDeliveryDate, numberToCheck, type MessageKey, type Translate } from './messages';
import type { CarrierId, ParcelWithEvents } from '../types';
import { currentEvent, stageMeta } from './stages';
import { activeTrackingCarrierId, carrierInfo, tracksAutomatically } from './carriers';

export interface ParcelDisplayStatus {
  label: string;
  tone: 'ok' | 'warn' | 'done';
  syncing: boolean;
}

/** Show estimates only when they still help explain what happens next. */
/** The carrier's estimate while the parcel is on its way and the estimate's last day has not passed. */
function liveEstimate(parcel: ParcelWithEvents, now: number): { value: string; day: Date; today: Date } | null {
  const value = parcel.expectedDelivery;
  const stage = currentEvent(parcel.events, parcel.returnHeld)?.stage;
  if (!value || (stage && ['delivered', 'returned', 'ready_for_pickup', 'failed_attempt', 'exception'].includes(stage))) return null;
  const day = estimateDay(value);
  const today = new Date(now);
  today.setHours(0, 0, 0, 0);
  return day && day >= today ? { value, day, today } : null;
}

/**
 * The carrier's estimate while it still explains what happens next: the parcel is on its way and its last day has not
 * passed. Out for delivery already says the parcel comes today, so only the hours a carrier gives for today are kept,
 * not a day, nor the last day of a window.
 */
export function parcelDeliveryEstimate(parcel: ParcelWithEvents, now = Date.now()): string | null {
  const live = liveEstimate(parcel, now);
  if (!live) return null;
  const { value, day, today } = live;
  if (currentEvent(parcel.events, parcel.returnHeld)?.stage === 'out_for_delivery') {
    const first = parcel.expectedDeliveryFrom ? estimateDay(parcel.expectedDeliveryFrom) : null;
    const hours = /^\S+[ T]\d{2}:\d{2}/.test(value.trim());
    if (day.getTime() !== today.getTime() || !hours || (first && first < today)) return null;
  }
  return value;
}

/** Whether a parcel out for delivery has an estimate: it comes today, whichever day the estimate named. */
export function parcelArrivesToday(parcel: ParcelWithEvents, now = Date.now()): boolean {
  return currentEvent(parcel.events, parcel.returnHeld)?.stage === 'out_for_delivery' && liveEstimate(parcel, now) !== null;
}

export function parcelHasCarrierUpdate(parcel: ParcelWithEvents): boolean {
  const current = currentEvent(parcel.events, parcel.returnHeld);
  return Boolean(current && current.stage !== 'pending');
}

/** How long a carrier may go without listing a number of its own format before the reader is asked to check it. */
export const LIKELY_CARRIER_WAIT_MS = 7 * 24 * 60 * 60_000;

/**
 * The carrier a parcel filed under none probably travels with: the one its number points to, which does not list the
 * number yet. `waited` once it has not for a week.
 */
export function likelyCarrier(parcel: ParcelWithEvents, now = Date.now()): { carrier: CarrierId; waited: boolean } | null {
  const likely = parcel.likelyCarrier;
  if (!likely || parcel.carrier !== 'unknown' || parcelHasCarrierUpdate(parcel)) return null;
  return { carrier: likely.carrier, waited: now - Date.parse(likely.since) >= LIKELY_CARRIER_WAIT_MS };
}

/** Failures only the owner's details can fix. Any other failure of a parcel with no scans is its carrier's. */
const OWNER_FAILURES = new Set(['carrier:input_required', 'carrier:invalid_input', 'carrier:several_parcels']);

/**
 * The carrier a parcel with no scans waits for while nothing is asked of its owner: its own, which has not listed the
 * number yet or could not be reached. Null under no carrier, or a postal service not named yet.
 */
export function awaitedCarrier(parcel: ParcelWithEvents): CarrierId | null {
  if (parcelHasCarrierUpdate(parcel) || parcel.carrier === 'unknown' || parcel.carrier === 'intl-post'
    || !tracksAutomatically(activeTrackingCarrierId(parcel))) return null;
  const waits = parcel.syncStatus === 'waiting' ? !parcel.providerInputNeeded
    : parcel.syncStatus === 'error' && !numberToCheck(parcel.syncError) && !OWNER_FAILURES.has(parcel.syncError ?? '');
  return waits ? parcel.carrier : null;
}

/**
 * The line under the status for a carrier that writes the whole timeline in advance (`writtenAheadBy`): its page lists
 * steps Peek can't confirm, which the timeline shows greyed once the carrier names them in words (`writtenAheadEvents`,
 * or still ahead `plannedEvents`), and leaves out until then. None for another carrier.
 */
export function writtenAheadNote(parcel: ParcelWithEvents, t: Translate, locale: string): string | null {
  if (!parcel.writtenAheadBy) return null;
  const shown = !!(parcel.writtenAheadEvents?.length || parcel.plannedEvents?.length);
  return t(shown ? 'parcel.writtenAheadShown' : 'parcel.writtenAhead', { carrier: carrierInfo(parcel.writtenAheadBy, locale).name });
}

/**
 * A universal provider's postcode request as the parcel page and the carrier editor word it: the provider needs the
 * delivery postcode, or found no parcel with the parcel's own, which the owner is asked to check.
 */
export function providerRequestText(request: NonNullable<ParcelWithEvents['providerInputNeeded']>, t: Translate): string {
  return request.refusedPostcode ? t('detail.postcodeRefused', { carrier: request.provider, postcode: request.refusedPostcode })
    : t('detail.providerInputNeeded', { provider: request.provider });
}

export function parcelIsUnannounced(parcel: ParcelWithEvents): boolean {
  return !parcelHasCarrierUpdate(parcel) && parcel.syncStatus === 'waiting';
}

/** Keep the app's sync lifecycle separate from the carrier's delivery stage. */
export function parcelDisplayStatus(parcel: ParcelWithEvents): ParcelDisplayStatus {
  const current = currentEvent(parcel.events, parcel.returnHeld);
  const hasCarrierUpdate = parcelHasCarrierUpdate(parcel);

  if (!hasCarrierUpdate && parcel.syncStatus === 'error' && numberToCheck(parcel.syncError)) {
    return { label: 'Check the tracking number', tone: 'warn', syncing: false };
  }
  if (!hasCarrierUpdate && !tracksAutomatically(activeTrackingCarrierId(parcel))) {
    return { label: 'Check tracking website', tone: 'warn', syncing: false };
  }

  if (!hasCarrierUpdate && (parcel.syncStatus === 'pending' || parcel.syncStatus === 'syncing')) {
    return { label: 'Checking for updates', tone: 'ok', syncing: true };
  }
  if (awaitedCarrier(parcel)) return { label: 'Waiting for the carrier', tone: 'ok', syncing: false };
  if (!hasCarrierUpdate && parcel.syncStatus === 'error') {
    return { label: 'Update unavailable', tone: 'warn', syncing: false };
  }
  if (!hasCarrierUpdate && parcel.syncStatus === 'unsupported') {
    return {
      label: 'Check tracking website',
      tone: 'warn',
      syncing: false,
    };
  }
  if (!hasCarrierUpdate && parcel.syncStatus === 'waiting') {
    return { label: parcel.providerInputNeeded ? 'Delivery postcode needed' : parcel.carrier === 'unknown' ? 'No tracking history yet' : 'Waiting for the carrier', tone: parcel.providerInputNeeded ? 'warn' : 'ok', syncing: false };
  }

  const meta = current ? stageMeta(current.stage) : null;
  return {
    label: meta?.label ?? 'Waiting for the carrier',
    tone: meta?.tone ?? 'ok',
    syncing: false,
  };
}

export function parcelDisplayStatusKey(parcel: ParcelWithEvents): MessageKey {
  const current = currentEvent(parcel.events, parcel.returnHeld);
  const hasCarrierUpdate = parcelHasCarrierUpdate(parcel);
  // No carrier will answer for this number until its owner corrects it.
  if (!hasCarrierUpdate && parcel.syncStatus === 'error' && numberToCheck(parcel.syncError)) return 'status.checkNumber';
  if (!hasCarrierUpdate && !tracksAutomatically(activeTrackingCarrierId(parcel))) {
    return 'status.unsupported';
  }
  if (!hasCarrierUpdate && (parcel.syncStatus === 'pending' || parcel.syncStatus === 'syncing')) {
    return 'status.syncing';
  }
  // Its carrier has not listed the number yet, or could not be reached: Peek keeps asking.
  if (awaitedCarrier(parcel)) return 'status.waitingFor';
  if (!hasCarrierUpdate && parcel.syncStatus === 'error') return 'status.failed';
  if (!hasCarrierUpdate && parcel.syncStatus === 'unsupported') return 'status.unsupported';
  if (!hasCarrierUpdate && parcel.syncStatus === 'waiting') {
    return parcel.providerInputNeeded ? 'status.inputNeeded' : parcel.carrier !== 'unknown' ? 'status.unannounced'
      : likelyCarrier(parcel) ? 'status.likelyCarrier' : 'status.noHistory';
  }
  return current ? (`stage.${current.stage}` as MessageKey) : 'status.unannounced';
}

/**
 * The status line, which names the carrier a parcel waits for, or probably travels with while it waits for that carrier's
 * first scan.
 */
export function parcelStatusLabel(parcel: ParcelWithEvents, t: Translate, locale?: string): string {
  const key = parcelDisplayStatusKey(parcel);
  const named = key === 'status.waitingFor' ? awaitedCarrier(parcel) : key === 'status.likelyCarrier' ? likelyCarrier(parcel)?.carrier : null;
  return named ? t(key, { carrier: carrierInfo(named, locale).name }) : t(key);
}

/**
 * The carrier a card or a parcel's header shows: the parcel's own, or while none is known, the one it probably travels
 * with, named as probable, in the livery of no carrier.
 */
export function shownCarrier<T extends { id: CarrierId; name: string }>(parcel: ParcelWithEvents, carrier: T, t: Translate, locale?: string): T {
  const likely = carrier.id === 'unknown' ? likelyCarrier(parcel) : null;
  return likely ? { ...carrier, name: t('link.likely.carrier', { carrier: carrierInfo(likely.carrier, locale).name }) } : carrier;
}

/** The actual completion date shown beside a final-state status tag. */
export function localizedParcelCompletionDate(
  parcel: ParcelWithEvents,
  languageTag: string,
  t: Translate,
  now: number = Date.now(),
): string | null {
  const current = currentEvent(parcel.events, parcel.returnHeld);
  if (current?.stage === 'delivered' || current?.stage === 'returned') {
    const when = scanDate(current);
    if (when) return localizedDeliveryDate(when.date, t, languageTag, now);
  }
  return null;
}
