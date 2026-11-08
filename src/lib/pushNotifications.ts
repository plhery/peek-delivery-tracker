import { authenticatedFetch, type ApiAuth } from './apiClient';
import type { Locale } from '../i18n';
import { linkAlertsUse, moveLinkAlerts } from '../peek/alerts';
import { decodePublicKey } from './pushKey';
import type {
  ApiOkResponse,
  ApiNotificationPreferences,
  ApiNotificationStage,
  ApiPushConfigResponse,
  ApiPushSubscriptionResponse,
  ApiPushSubscriptionStatusResponse,
} from '../generated/apiContract';

export type NotificationPreferences = ApiNotificationPreferences;
export type NotificationStage = ApiNotificationStage;

export {
  ALL_NOTIFICATION_STAGES,
  DELIVERY_DAY_NOTIFICATION_STAGES,
  IMPORTANT_NOTIFICATION_STAGES,
} from './notificationPresets';

export type PushState =
  | { kind: 'unsupported' }
  | { kind: 'install' }
  | { kind: 'unavailable' }
  | { kind: 'prompt'; publicKey: string }
  | { kind: 'blocked' }
  | { kind: 'enabled'; publicKey: string };

type BadgeNavigator = { clearAppBadge?: () => Promise<void> };
type VisibilityDocument = Pick<
  Document,
  'visibilityState' | 'addEventListener' | 'removeEventListener'
>;

/** Clear stale OS app badges on launch and whenever the PWA returns to the foreground. */
export function enableAppBadgeClearing(
  appNavigator: BadgeNavigator = navigator as BadgeNavigator,
  page: VisibilityDocument = document,
): () => void {
  const clearWhenVisible = () => {
    if (page.visibilityState !== 'visible') return;
    void appNavigator.clearAppBadge?.().catch(() => undefined);
  };

  clearWhenVisible();
  page.addEventListener('visibilitychange', clearWhenVisible);
  return () => page.removeEventListener('visibilitychange', clearWhenVisible);
}

async function request<T>(path: string, auth?: ApiAuth, init?: RequestInit): Promise<T> {
  const headers = new Headers(init?.headers);
  headers.set('Content-Type', 'application/json');
  const response = await authenticatedFetch(path, auth, { ...init, headers });
  const body = (await response.json().catch(() => ({}))) as { error?: string };
  if (!response.ok) throw new Error(body.error || 'Notification settings are unavailable');
  return body as T;
}

function supported(): boolean {
  return (
    'serviceWorker' in navigator &&
    typeof window.PushManager !== 'undefined' &&
    typeof window.Notification !== 'undefined'
  );
}

/**
 * The language this browser's push subscription last gave an account, with the
 * subscription's address, so a start that changes nothing asks the server nothing.
 */
const sentLocaleKey = (userId: string) => `deliveryTrackerPushLocale:${userId}`;

function wasSent(userId: string, endpoint: string, locale: Locale): boolean {
  try {
    return localStorage.getItem(sentLocaleKey(userId)) === `${locale} ${endpoint}`;
  } catch {
    return false;
  }
}

function noteSent(userId: string, sent: { endpoint?: string; locale: Locale } | null): void {
  try {
    if (sent?.endpoint) localStorage.setItem(sentLocaleKey(userId), `${sent.locale} ${sent.endpoint}`);
    else localStorage.removeItem(sentLocaleKey(userId));
  } catch {
    // Without storage the language is sent again on the next start.
  }
}

export async function inspectPushState(auth?: ApiAuth): Promise<PushState> {
  const needsInstallation = (/iPad|iPhone|iPod/.test(navigator.userAgent)
    || (navigator.platform === 'MacIntel' && navigator.maxTouchPoints > 1))
    && !window.matchMedia?.('(display-mode: standalone)').matches
    && !(navigator as Navigator & { standalone?: boolean }).standalone;
  if (!supported() && !needsInstallation) return { kind: 'unsupported' };
  const config = await request<ApiPushConfigResponse>('/api/push/config', auth);
  if (!config.available || !config.publicKey) return { kind: 'unavailable' };
  if (needsInstallation) return { kind: 'install' };
  if (Notification.permission === 'denied') return { kind: 'blocked' };
  const registration = await navigator.serviceWorker.ready;
  const subscription = await registration.pushManager.getSubscription();
  // The browser keeps its subscription after the server removed or expired it,
  // so a signed-in device counts as enabled only while the server delivers to it.
  const active = subscription && (!auth || (await request<ApiPushSubscriptionStatusResponse>(
    '/api/push/subscriptions/status',
    auth,
    { method: 'POST', body: JSON.stringify({ endpoint: subscription.endpoint }) },
  )).active);
  return active
    ? { kind: 'enabled', publicKey: config.publicKey }
    : { kind: 'prompt', publicKey: config.publicKey };
}

export async function getNotificationPreferences(
  auth: ApiAuth,
): Promise<NotificationPreferences> {
  return request<ApiNotificationPreferences>('/api/push/preferences', auth);
}

export async function saveNotificationPreferences(
  preferences: NotificationPreferences,
  auth: ApiAuth,
): Promise<NotificationPreferences> {
  return request<ApiNotificationPreferences>('/api/push/preferences', auth, {
    method: 'PATCH',
    body: JSON.stringify(preferences),
  });
}

export async function enablePushNotifications(
  publicKey: string,
  auth?: ApiAuth,
  locale: Locale = 'en',
): Promise<boolean> {
  const permission = Notification.permission === 'granted'
    ? 'granted'
    : await Notification.requestPermission();
  if (permission !== 'granted') throw new Error('Notifications were not allowed');
  const registration = await navigator.serviceWorker.ready;
  const subscribe = () => registration.pushManager.subscribe({
    userVisibleOnly: true,
    applicationServerKey: decodePublicKey(publicKey),
  });
  const register = async (subscription: PushSubscription, created: boolean) => {
    try {
      const result = await request<ApiPushSubscriptionResponse>('/api/push/subscriptions', auth, {
        method: 'POST',
        body: JSON.stringify({ ...subscription.toJSON(), locale }),
      });
      if (auth) noteSent(auth.userId, { endpoint: subscription.endpoint, locale });
      return result.testSent;
    } catch (error) {
      if (created) await subscription.unsubscribe();
      throw error;
    }
  };
  const existing = await registration.pushManager.getSubscription();
  if (!existing) return register(await subscribe(), true);
  if (await register(existing, false)) return true;
  // A kept subscription can be one the push service already dropped. Replace it
  // once rather than registering an endpoint that cannot receive alerts. The
  // alerts this browser turned on for parcel links move to the new one, which
  // then stays for them even if the account's request fails.
  await request<ApiOkResponse>('/api/push/subscriptions', auth, {
    method: 'DELETE',
    body: JSON.stringify({ endpoint: existing.endpoint }),
  }).catch(() => undefined);
  await existing.unsubscribe().catch(() => false);
  const replacement = await subscribe();
  await moveLinkAlerts(existing.endpoint, replacement);
  return register(replacement, !linkAlertsUse(replacement.endpoint));
}

/**
 * Gives the account's push subscription on this browser the app's language,
 * which the server writes its alerts in, without resetting the delivery cursor
 * or sending a welcome alert. It asks the browser for nothing, and the server
 * only when this subscription last gave the account another language: without
 * a permission and a subscription there is nothing to update.
 */
export async function followPushNotificationLocale(locale: Locale, auth: ApiAuth): Promise<void> {
  if (!supported() || Notification.permission !== 'granted') return;
  const registration = await navigator.serviceWorker.getRegistration();
  const subscription = await registration?.pushManager.getSubscription();
  if (!subscription || wasSent(auth.userId, subscription.endpoint, locale)) return;
  await request<ApiOkResponse>('/api/push/subscriptions', auth, {
    method: 'PATCH', body: JSON.stringify({ endpoint: subscription.endpoint, locale }),
  });
  noteSent(auth.userId, { endpoint: subscription.endpoint, locale });
}

/**
 * Swaps this browser's push subscription for a new one when the server may
 * still send the account's alerts to it. Those stop: the push service refuses
 * the old one, and the server's next send to it drops the account's row. The
 * alerts this browser turned on for parcel links move to the new subscription;
 * without any, the browser keeps none.
 */
async function replaceSubscription(registration: ServiceWorkerRegistration, old: PushSubscription): Promise<void> {
  const key = old.options?.applicationServerKey;
  await old.unsubscribe();
  if (!key || !linkAlertsUse(old.endpoint)) return;
  const replacement = await registration.pushManager.subscribe({ userVisibleOnly: true, applicationServerKey: key });
  await moveLinkAlerts(old.endpoint, replacement);
  if (!linkAlertsUse(replacement.endpoint)) await replacement.unsubscribe();
}

/**
 * Stops the account's alerts on this browser: the toggle in its settings, and
 * signing out. The browser's push subscription also carries the alerts it
 * turned on for parcel links, so once the server says it dropped the account's
 * row, the subscription stays while they use it. When the server can't be
 * told, the subscription is replaced instead, and the error is the caller's.
 */
export async function disablePushNotifications(auth?: ApiAuth): Promise<void> {
  if (auth) noteSent(auth.userId, null);
  if (!('serviceWorker' in navigator)) return;
  const registration = await navigator.serviceWorker.getRegistration();
  const subscription = await registration?.pushManager.getSubscription();
  if (!registration || !subscription) return;
  try {
    await request<ApiOkResponse>('/api/push/subscriptions', auth, {
      method: 'DELETE',
      body: JSON.stringify({ endpoint: subscription.endpoint }),
    });
  } catch (error) {
    await replaceSubscription(registration, subscription).catch(() => undefined);
    throw error;
  }
  if (!linkAlertsUse(subscription.endpoint)) await subscription.unsubscribe();
}

/**
 * Lets go of this browser's push subscription once the server holds no row of
 * the account's for it, as after the account was deleted. It stays while
 * alerts this browser turned on for parcel links use it.
 */
export async function releasePushSubscription(): Promise<void> {
  if (!('serviceWorker' in navigator)) return;
  const registration = await navigator.serviceWorker.getRegistration();
  const subscription = await registration?.pushManager.getSubscription();
  if (subscription && !linkAlertsUse(subscription.endpoint)) await subscription.unsubscribe();
}
