import { useCallback, useSyncExternalStore } from 'react';
import type { ApiAuth } from '../lib/apiClient';
import {
  getNotificationPreferences,
  saveNotificationPreferences,
  type NotificationPreferences,
  type NotificationStage,
} from '../lib/pushNotifications';

/**
 * The account's notification preferences, shared by everything that shows
 * them: Settings, a parcel's alerts and the email offer. The deliveries read
 * them once per sign-in, and every save replaces the shared copy with the
 * server's answer.
 */
interface Session {
  preferences: NotificationPreferences | null;
  /** The read in flight, so the deliveries ask once however often they render. */
  reading: Promise<NotificationPreferences> | null;
  /** Counts the answers taken: a read that began before a save must not undo it. */
  revision: number;
  /** The clock and units sent and not yet kept, so a server that does not keep them is not asked again and again. */
  unitsSent: string | null;
  listeners: Set<() => void>;
}

// One entry per sign-in. Its signal outlives the token refreshes that replace the `ApiAuth` object.
const sessions = new WeakMap<object, Session>();

function sessionOf(auth: ApiAuth): Session {
  const key = auth.signal ?? auth;
  let session = sessions.get(key);
  if (!session) {
    session = { preferences: null, reading: null, revision: 0, unitsSent: null, listeners: new Set() };
    sessions.set(key, session);
  }
  return session;
}

function take(session: Session, preferences: NotificationPreferences): NotificationPreferences {
  session.preferences = preferences;
  session.revision += 1;
  for (const listener of [...session.listeners]) listener();
  return preferences;
}

/**
 * Reads the preferences from the server. Settings asks whenever it opens; the
 * deliveries ask with `once`, and get what this sign-in has already read.
 */
export function loadNotificationPreferences(auth: ApiAuth, { once = false } = {}): Promise<NotificationPreferences> {
  const session = sessionOf(auth);
  if (once && session.preferences) return Promise.resolve(session.preferences);
  if (once && session.reading) return session.reading;
  const { revision } = session;
  const reading = getNotificationPreferences(auth).then((preferences) => {
    // Something answered meanwhile, a save perhaps: that answer is the newer one.
    if (session.revision !== revision && session.preferences) return session.preferences;
    return take(session, preferences);
  }).finally(() => {
    if (session.reading === reading) session.reading = null;
  });
  session.reading = reading;
  return reading;
}

/**
 * Sends what changed, and the browser's time zone, which the delivery email writes its times in:
 * the server keeps every other setting as it has it, whatever another device saved since.
 */
async function save(preferences: Partial<NotificationPreferences>, auth: ApiAuth): Promise<NotificationPreferences> {
  const timezone = Intl.DateTimeFormat().resolvedOptions().timeZone;
  return take(sessionOf(auth), await saveNotificationPreferences({ ...preferences, ...(timezone ? { timezone } : {}) }, auth));
}

/** Saves which events are announced, and clears the retired quiet hours, which no app shows any more. */
export function saveNotificationStages(stages: NotificationStage[], auth: ApiAuth): Promise<NotificationPreferences> {
  return save({ enabledStages: stages, quietHoursStart: null, quietHoursEnd: null }, auth);
}

/**
 * Switches the delivery email on or off, and nothing else: the events go as
 * they were last saved, not as a form may have them drafted.
 */
export function saveEmailOnDelivery(enabled: boolean, auth: ApiAuth): Promise<NotificationPreferences> {
  const saved = sessionOf(auth).preferences;
  if (!saved) return Promise.reject(new Error('Notification preferences are not loaded'));
  return save({ emailOnDelivery: enabled }, auth);
}

/**
 * Switches the list's mark on parcels with new updates on or off for the account, and nothing else: the
 * events and the email choice go as they were last saved.
 */
export async function saveMarkNewUpdates(enabled: boolean, auth: ApiAuth): Promise<NotificationPreferences> {
  await loadNotificationPreferences(auth, { once: true });
  return save({ markNewUpdates: enabled }, auth);
}

/**
 * Switches Peek AI's own estimates on or off for the account, and nothing else: the events and the other choices go as
 * they were last saved.
 */
export async function saveShowPeekAi(enabled: boolean, auth: ApiAuth): Promise<NotificationPreferences> {
  await loadNotificationPreferences(auth, { once: true });
  return save({ showPeekAi: enabled }, auth);
}

/** How the app writes a time and a distance here. */
export interface ShownUnits {
  clock: '24' | '12';
  distance: 'km' | 'mi';
}

/**
 * Saves how the app here writes a time and a distance where the account has them otherwise, so
 * the delivery email writes them as the app last opened does. Nothing else changes: the events
 * and the other choices go as they were last saved. Says whether it asked the server, which it
 * does not again for the same clock and units until it has kept them.
 */
export async function saveShownUnits(shown: ShownUnits, auth: ApiAuth): Promise<boolean> {
  const session = sessionOf(auth);
  const saved = session.preferences ?? await loadNotificationPreferences(auth, { once: true });
  const sent = `${shown.clock} ${shown.distance}`;
  if ((saved.clock === shown.clock && saved.distance === shown.distance) || session.unitsSent === sent) return false;
  session.unitsSent = sent;
  let answer: NotificationPreferences;
  try {
    answer = await save({
      ...(saved.clock !== shown.clock ? { clock: shown.clock } : {}),
      ...(saved.distance !== shown.distance ? { distance: shown.distance } : {}),
    }, auth);
  } catch (error) {
    if (session.unitsSent === sent) session.unitsSent = null;
    throw error;
  }
  // Kept: should another device change them, the next difference read here is sent again.
  if (session.unitsSent === sent && answer.clock === shown.clock && answer.distance === shown.distance) session.unitsSent = null;
  return true;
}

/**
 * Switches the delivery email on for someone who signed in to get it, and
 * says whether it is on. Asking again overrides an earlier "off"; an account
 * the server cannot write to stays without.
 */
export async function turnOnDeliveryEmail(auth: ApiAuth): Promise<boolean> {
  const preferences = await loadNotificationPreferences(auth, { once: true });
  if (preferences.emailAvailable !== true) return false;
  if (preferences.emailOnDelivery === true) return true;
  return (await saveEmailOnDelivery(true, auth)).emailOnDelivery === true;
}

/** The shared copy: null until this sign-in has read or saved them, and without an account. */
export function useNotificationPreferences(auth: ApiAuth | undefined): NotificationPreferences | null {
  const subscribe = useCallback((notify: () => void) => {
    if (!auth) return () => undefined;
    const { listeners } = sessionOf(auth);
    listeners.add(notify);
    return () => { listeners.delete(notify); };
  }, [auth]);
  return useSyncExternalStore(subscribe, () => auth ? sessionOf(auth).preferences : null, () => null);
}

/** The delivery email, as far as this account can have one. */
export interface DeliveryEmail {
  /** Where it goes: the address the account signs in with. */
  address: string;
  /** True on, false off or declined, null never chosen. */
  choice: boolean | null;
}

/**
 * The delivery email of the signed-in account, or null where nothing about
 * email may show: the server sends none or cannot write to this account, the
 * preferences are not read yet, or the session names no address.
 */
export function useDeliveryEmail(auth: ApiAuth | undefined, address: string | undefined): DeliveryEmail | null {
  const preferences = useNotificationPreferences(auth);
  if (preferences?.emailAvailable !== true || !address?.includes('@')) return null;
  // Only the server's explicit null means "never chosen", which is what the one-time offer waits for.
  return { address, choice: preferences.emailOnDelivery === undefined ? false : preferences.emailOnDelivery };
}
