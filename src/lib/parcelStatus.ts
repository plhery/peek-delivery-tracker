import { scanDate } from './format';
import { localizedDeliveryDate, numberToCheck, type MessageKey, type Translate } from './messages';
import type { CarrierId, ParcelWithEvents } from '../types';
import { currentEvent, stageMeta } from './stages';
import { activeTrackingCarrierId, tracksAutomatically } from './carriers';

export interface ParcelDisplayStatus {
  label: string;
  tone: 'ok' | 'warn' | 'done';
  syncing: boolean;
}

/** Show estimates only when they still help explain what happens next. */
export function parcelDeliveryEstimate(parcel: ParcelWithEvents, now = Date.now()): string | null {
  const value = parcel.expectedDelivery;
  const stage = currentEvent(parcel.events)?.stage;
  if (!value || (stage && ['delivered', 'returned', 'ready_for_pickup', 'failed_attempt', 'exception'].includes(stage))) return null;
  const match = /^(\d{4}-\d{2}-\d{2})/.exec(value);
  if (!match) return null;
  const day = /T\d{2}:\d{2}/.test(value) && !Number.isNaN(Date.parse(value))
    ? new Date(value) : new Date(`${match[1]}T00:00:00`);
  day.setHours(0, 0, 0, 0);
  const today = new Date(now);
  today.setHours(0, 0, 0, 0);
  if (!Number.isFinite(day.getTime()) || day < today) return null;
  return value;
}

export function parcelHasCarrierUpdate(parcel: ParcelWithEvents): boolean {
  const current = currentEvent(parcel.events);
  return Boolean(current && current.stage !== 'pending');
}

/** How long a carrier may go without listing a number of its own format before the reader is asked to check it. */
export const LIKELY_CARRIER_WAIT_MS = 7 * 24 * 60 * 60_000;

/**
 * The carrier a parcel filed under none probably travels with: the only one whose own format its number has, which
 * does not list the number yet. `waited` once it has not for a week.
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
  if (!hasCarrierUpdate && parcel.syncStatus === 'waiting') return parcel.providerInputNeeded ? 'status.inputNeeded' : parcel.carrier === 'unknown' ? 'status.noHistory' : 'status.unannounced';
  return current ? (`stage.${current.stage}` as MessageKey) : 'status.unannounced';
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
