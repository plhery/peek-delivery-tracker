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
  const stage = currentEvent(parcel.events)?.stage;
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
  if (currentEvent(parcel.events)?.stage === 'out_for_delivery') {
    const first = parcel.expectedDeliveryFrom ? estimateDay(parcel.expectedDeliveryFrom) : null;
    const hours = /^\S+[ T]\d{2}:\d{2}/.test(value.trim());
    if (day.getTime() !== today.getTime() || !hours || (first && first < today)) return null;
  }
  return value;
}

/** Whether a parcel out for delivery has an estimate: it comes today, whichever day the estimate named. */
export function parcelArrivesToday(parcel: ParcelWithEvents, now = Date.now()): boolean {
  return currentEvent(parcel.events)?.stage === 'out_for_delivery' && liveEstimate(parcel, now) !== null;
}

export function parcelHasCarrierUpdate(parcel: ParcelWithEvents): boolean {
  const current = currentEvent(parcel.events);
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

export function parcelIsUnannounced(parcel: ParcelWithEvents): boolean {
  return !parcelHasCarrierUpdate(parcel) && parcel.syncStatus === 'waiting';
}

/** Keep the app's sync lifecycle separate from the carrier's delivery stage. */
export function parcelDisplayStatus(parcel: ParcelWithEvents): ParcelDisplayStatus {
  const current = currentEvent(parcel.events);
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
  const current = currentEvent(parcel.events);
  const hasCarrierUpdate = parcelHasCarrierUpdate(parcel);
  // No carrier will answer for this number until its owner corrects it.
  if (!hasCarrierUpdate && parcel.syncStatus === 'error' && numberToCheck(parcel.syncError)) return 'status.checkNumber';
  if (!hasCarrierUpdate && !tracksAutomatically(activeTrackingCarrierId(parcel))) {
    return 'status.unsupported';
  }
  if (!hasCarrierUpdate && (parcel.syncStatus === 'pending' || parcel.syncStatus === 'syncing')) {
    return 'status.syncing';
  }
  if (!hasCarrierUpdate && parcel.syncStatus === 'error') return 'status.failed';
  if (!hasCarrierUpdate && parcel.syncStatus === 'unsupported') return 'status.unsupported';
  if (!hasCarrierUpdate && parcel.syncStatus === 'waiting') {
    return parcel.providerInputNeeded ? 'status.inputNeeded' : parcel.carrier !== 'unknown' ? 'status.unannounced'
      : likelyCarrier(parcel) ? 'status.likelyCarrier' : 'status.noHistory';
  }
  return current ? (`stage.${current.stage}` as MessageKey) : 'status.unannounced';
}

/** The status line, which names the carrier a parcel probably travels with while it waits for that carrier's first scan. */
export function parcelStatusLabel(parcel: ParcelWithEvents, t: Translate, locale?: string): string {
  const key = parcelDisplayStatusKey(parcel);
  const likely = key === 'status.likelyCarrier' ? likelyCarrier(parcel) : null;
  return likely ? t(key, { carrier: carrierInfo(likely.carrier, locale).name }) : t(key);
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
  const current = currentEvent(parcel.events);
  if (current?.stage === 'delivered' || current?.stage === 'returned') {
    const when = scanDate(current);
    if (when) return localizedDeliveryDate(when.date, t, languageTag, now);
  }
  return null;
}
