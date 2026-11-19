import { useEffect, useSyncExternalStore } from 'react';
import { useI18n } from '../i18n';
import type { Locale } from '../lib/locale';
import { isFinal } from '../lib/stages';
import { alertSupport, turnOnAlert } from './alerts';
import { linkNote } from './deviceNotes';
import { PARCEL_ALERT_PRESETS, type ParcelAlertPreset } from './linkModel';
import { parcelStage } from './parcel/summary';
import { useRecents, type RecentParcel } from './recents';

/**
 * "Every parcel on this device": a visitor's alerts for each parcel this
 * browser holds, with the alerts they chose, rather than one parcel at a
 * time. A parcel whose alerts were turned off on its own page stays quiet.
 * The browser is asked nothing for them: they follow once it has said yes.
 */
export const ALERTS_FOR_ALL_STORAGE_KEY = 'sdt.peek.alertsForAll.v1'; // gitleaks:allow -- public localStorage name
const eventName = 'peek-alerts-for-all-change';
// Without usable storage the choice lasts as long as the page.
let memory: ParcelAlertPreset | null = null;

function preset(value: string | null): ParcelAlertPreset | null {
  return PARCEL_ALERT_PRESETS.includes(value as ParcelAlertPreset) ? value as ParcelAlertPreset : null;
}

/** What every parcel's alerts announce, or null while each parcel has its own. */
export function alertsForAll(): ParcelAlertPreset | null {
  try { return preset(window.localStorage.getItem(ALERTS_FOR_ALL_STORAGE_KEY)) ?? memory; } catch { return memory; }
}

/** Turns alerts for every parcel on with what they announce, or off with null. The alerts already on stay. */
export function setAlertsForAll(next: ParcelAlertPreset | null): void {
  memory = next;
  try {
    if (next) window.localStorage.setItem(ALERTS_FOR_ALL_STORAGE_KEY, next);
    else window.localStorage.removeItem(ALERTS_FOR_ALL_STORAGE_KEY);
  } catch { /* The page keeps it. */ }
  window.dispatchEvent(new Event(eventName));
}

function subscribe(notify: () => void) {
  const onStorage = (event: StorageEvent) => { if (event.key === null || event.key === ALERTS_FOR_ALL_STORAGE_KEY) notify(); };
  window.addEventListener(eventName, notify);
  window.addEventListener('storage', onStorage);
  return () => {
    window.removeEventListener(eventName, notify);
    window.removeEventListener('storage', onStorage);
  };
}

/** The alerts every parcel gets, followed as they are turned on and off. */
export function useAlertsForAll(): ParcelAlertPreset | null {
  return useSyncExternalStore(subscribe, alertsForAll, () => null);
}

// Each parcel is tried once a page: one the server refused waits for the next visit.
const tried = new Set<string>();
let running: Promise<void> = Promise.resolve();

/** Whether a parcel would get alerts it doesn't have yet, without asking the browser anything. */
function wanting(recent: RecentParcel): boolean {
  const note = linkNote(recent.id);
  const stage = parcelStage(recent.snapshot.parcel);
  // A journey that has ended, or a copy no longer updated, has nothing more to announce.
  return !tried.has(recent.id) && !recent.frozen && !note.alert && !note.muted && !(stage && isFinal(stage))
    && alertSupport(recent.snapshot.link.alerts) === 'ready' && Notification.permission === 'granted';
}

/** Turns alerts on, one parcel at a time, for the device's parcels that have none and weren't turned off. */
export function alertParcels(recents: readonly RecentParcel[], locale: Locale): Promise<void> {
  running = running.then(async () => {
    for (const recent of recents) {
      const chosen = alertsForAll();
      if (!chosen) return;
      if (!wanting(recent)) continue;
      tried.add(recent.id);
      try {
        await turnOnAlert({ linkId: recent.id, key: recent.key, alerts: recent.snapshot.link.alerts, preset: chosen, locale });
      } catch { /* Its own page can still turn it on. */ }
    }
  });
  return running;
}

/** Keeps every parcel this browser holds alerted while the visitor asked for that. */
export function AlertEveryParcel() {
  const { locale } = useI18n();
  const recents = useRecents();
  const chosen = useAlertsForAll();
  useEffect(() => {
    if (chosen) void alertParcels(recents, locale);
  }, [recents, chosen, locale]);
  return null;
}

/** For tests: each parcel may be tried again. */
export function forgetAlertTries(): void {
  tried.clear();
}
