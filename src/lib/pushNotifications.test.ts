import { afterEach, beforeEach, describe, expect, it, vi } from 'vitest';
import { forgetAllLinkNotes, linkNote, noteLink } from '../peek/deviceNotes';
import { forgetAllRecents, rememberParcel } from '../peek/recents';
import { LINK_ID, OTHER_LINK_ID, OWNER_KEY, testView } from '../test/parcelLinks';
import { decodePublicKey } from './pushKey';
import {
  disablePushNotifications,
  enableAppBadgeClearing,
  enablePushNotifications,
  followPushNotificationLocale,
  getNotificationPreferences,
  inspectPushState,
  releasePushSubscription,
  saveNotificationPreferences,
} from './pushNotifications';

const links = vi.hoisted(() => ({ setParcelAlert: vi.fn() }));
vi.mock('../peek/links', async (original) => ({
  ...await original<typeof import('../peek/links')>(),
  setParcelAlert: links.setParcelAlert,
}));

const getSubscription = vi.fn();
const subscribe = vi.fn();
const getRegistration = vi.fn();
const registration = { pushManager: { getSubscription, subscribe } };

function response(body: unknown, ok = true): Response {
  return { ok, json: vi.fn().mockResolvedValue(body) } as unknown as Response;
}

beforeEach(() => {
  Object.defineProperty(navigator, 'maxTouchPoints', { configurable: true, get: () => 0 });
  vi.stubGlobal('PushManager', function PushManager() {});
  vi.stubGlobal('Notification', {
    permission: 'default',
    requestPermission: vi.fn().mockResolvedValue('granted'),
  });
  Object.defineProperty(navigator, 'serviceWorker', {
    configurable: true,
    value: { ready: Promise.resolve(registration), getRegistration },
  });
  getRegistration.mockReset().mockResolvedValue(registration);
  getSubscription.mockReset().mockResolvedValue(null);
  subscribe.mockReset();
  vi.stubGlobal('fetch', vi.fn().mockResolvedValue(response({ available: true, publicKey: 'AQID' })));
  links.setParcelAlert.mockReset().mockResolvedValue(undefined);
});

afterEach(() => {
  vi.unstubAllGlobals();
  vi.restoreAllMocks();
  forgetAllLinkNotes();
  forgetAllRecents();
});

describe('inspectPushState', () => {
  it('offers installation only on iOS browsers with an available push service', async () => {
    vi.spyOn(navigator, 'userAgent', 'get').mockReturnValue('iPhone');
    vi.stubGlobal('PushManager', undefined);
    await expect(inspectPushState()).resolves.toEqual({ kind: 'install' });
    vi.mocked(fetch).mockResolvedValueOnce(response({ available: false }));
    await expect(inspectPushState()).resolves.toEqual({ kind: 'unavailable' });
    vi.stubGlobal('matchMedia', vi.fn().mockReturnValue({ matches: true }));
    await expect(inspectPushState()).resolves.toEqual({ kind: 'unsupported' });
  });

  it('recognizes iPad desktop user agents and installed standalone apps', async () => {
    vi.spyOn(navigator, 'platform', 'get').mockReturnValue('MacIntel');
    vi.spyOn(navigator, 'maxTouchPoints', 'get').mockReturnValue(5);
    await expect(inspectPushState()).resolves.toEqual({ kind: 'install' });
    vi.stubGlobal('matchMedia', vi.fn().mockReturnValue({ matches: true }));
    await expect(inspectPushState()).resolves.toEqual({ kind: 'prompt', publicKey: 'AQID' });
  });
  it('explains unsupported and server-unavailable installations', async () => {
    vi.stubGlobal('PushManager', undefined);
    await expect(inspectPushState()).resolves.toEqual({ kind: 'unsupported' });
    expect(fetch).not.toHaveBeenCalled();

    vi.stubGlobal('PushManager', function PushManager() {});
    vi.mocked(fetch).mockResolvedValueOnce(response({ available: false, publicKey: null }));
    await expect(inspectPushState()).resolves.toEqual({ kind: 'unavailable' });
  });

  it('distinguishes blocked, ready, and subscribed devices', async () => {
    vi.stubGlobal('Notification', { permission: 'denied', requestPermission: vi.fn() });
    await expect(inspectPushState()).resolves.toEqual({ kind: 'blocked' });

    vi.stubGlobal('Notification', { permission: 'default', requestPermission: vi.fn() });
    await expect(inspectPushState()).resolves.toEqual({ kind: 'prompt', publicKey: 'AQID' });

    getSubscription.mockResolvedValueOnce({ endpoint: 'https://push.example/token' });
    await expect(inspectPushState()).resolves.toEqual({ kind: 'enabled', publicKey: 'AQID' });
  });

  it('shows a kept browser subscription as off once the server stopped delivering to it', async () => {
    const auth = { userId: 'user-1', getAccessToken: vi.fn().mockResolvedValue('token') };
    getSubscription.mockResolvedValue({ endpoint: 'https://push.example/token' });
    vi.mocked(fetch)
      .mockResolvedValueOnce(response({ available: true, publicKey: 'AQID' }))
      .mockResolvedValueOnce(response({ active: false }))
      .mockResolvedValueOnce(response({ available: true, publicKey: 'AQID' }))
      .mockResolvedValueOnce(response({ active: true }));

    await expect(inspectPushState(auth)).resolves.toEqual({ kind: 'prompt', publicKey: 'AQID' });
    await expect(inspectPushState(auth)).resolves.toEqual({ kind: 'enabled', publicKey: 'AQID' });
    expect(fetch).toHaveBeenNthCalledWith(2, '/api/push/subscriptions/status', expect.objectContaining({
      method: 'POST', body: JSON.stringify({ endpoint: 'https://push.example/token' }),
    }));
  });
});

describe('notification preferences', () => {
  it('loads and saves account-wide event and quiet-hour settings', async () => {
    const preferences = {
      enabledStages: ['out_for_delivery', 'delivered'] as const,
      quietHoursStart: '22:00',
      quietHoursEnd: '08:00',
      timezone: 'Europe/Zurich',
    };
    const auth = {
      userId: 'user-1',
      getAccessToken: vi.fn().mockResolvedValue('token'),
    };
    vi.mocked(fetch)
      .mockResolvedValueOnce(response(preferences))
      .mockResolvedValueOnce(response(preferences));

    await expect(getNotificationPreferences(auth)).resolves.toEqual(preferences);
    await expect(saveNotificationPreferences({
      ...preferences,
      enabledStages: [...preferences.enabledStages],
    }, auth)).resolves.toEqual(preferences);

    expect(fetch).toHaveBeenNthCalledWith(
      1,
      '/api/push/preferences',
      expect.objectContaining({ headers: expect.any(Headers) }),
    );
    expect(fetch).toHaveBeenNthCalledWith(
      2,
      '/api/push/preferences',
      expect.objectContaining({ method: 'PATCH' }),
    );
  });
});

describe('app badge lifecycle', () => {
  it('clears the badge on launch and each return to the foreground', () => {
    let visibilityState: DocumentVisibilityState = 'visible';
    let onVisibilityChange: EventListener | undefined;
    const page = {
      get visibilityState() {
        return visibilityState;
      },
      addEventListener: vi.fn((_type: string, listener: EventListenerOrEventListenerObject) => {
        onVisibilityChange = listener as EventListener;
      }),
      removeEventListener: vi.fn(),
    } as unknown as Document;
    const clearAppBadge = vi.fn().mockResolvedValue(undefined);

    const cleanupBadgeClearing = enableAppBadgeClearing({ clearAppBadge }, page);
    expect(clearAppBadge).toHaveBeenCalledOnce();

    visibilityState = 'hidden';
    onVisibilityChange?.(new Event('visibilitychange'));
    expect(clearAppBadge).toHaveBeenCalledOnce();

    visibilityState = 'visible';
    onVisibilityChange?.(new Event('visibilitychange'));
    expect(clearAppBadge).toHaveBeenCalledTimes(2);

    cleanupBadgeClearing();
    expect(page.removeEventListener).toHaveBeenCalledWith(
      'visibilitychange',
      onVisibilityChange,
    );
  });

  it('is inert when the Badging API is unsupported', () => {
    const addEventListener = vi.fn();
    const page = {
      visibilityState: 'visible',
      addEventListener,
      removeEventListener: vi.fn(),
    } as unknown as Document;

    expect(() => enableAppBadgeClearing({}, page)).not.toThrow();
    expect(addEventListener).toHaveBeenCalledWith('visibilitychange', expect.any(Function));
  });
});

describe('push subscription lifecycle', () => {
  it('reuses granted permission when retrying or re-enabling alerts', async () => {
    vi.stubGlobal('Notification', { permission: 'granted', requestPermission: vi.fn() });
    getSubscription.mockResolvedValue({ toJSON: () => ({ endpoint: 'https://push.example/token' }) });
    vi.mocked(fetch).mockResolvedValueOnce(response({ ok: true, testSent: true }));
    await expect(enablePushNotifications('AQID')).resolves.toBe(true);
    expect(Notification.requestPermission).not.toHaveBeenCalled();
    expect(subscribe).not.toHaveBeenCalled();
  });

  it('replaces a kept subscription the push service no longer accepts', async () => {
    vi.stubGlobal('Notification', { permission: 'granted', requestPermission: vi.fn() });
    const stale = {
      endpoint: 'https://push.example/stale',
      toJSON: () => ({ endpoint: 'https://push.example/stale' }),
      unsubscribe: vi.fn().mockResolvedValue(true),
    };
    const fresh = {
      endpoint: 'https://push.example/fresh',
      toJSON: () => ({ endpoint: 'https://push.example/fresh' }),
      unsubscribe: vi.fn(),
    };
    getSubscription.mockResolvedValue(stale);
    subscribe.mockResolvedValue(fresh);
    vi.mocked(fetch)
      .mockResolvedValueOnce(response({ ok: true, testSent: false }))
      .mockResolvedValueOnce(response({ ok: true }))
      .mockResolvedValueOnce(response({ ok: true, testSent: true }));

    await expect(enablePushNotifications('AQID')).resolves.toBe(true);
    const calls = vi.mocked(fetch).mock.calls.map(([, init]) => [init?.method, JSON.parse(String(init?.body)).endpoint]);
    expect(calls).toEqual([
      ['POST', 'https://push.example/stale'],
      ['DELETE', 'https://push.example/stale'],
      ['POST', 'https://push.example/fresh'],
    ]);
    expect(stale.unsubscribe).toHaveBeenCalledOnce();
    expect(fresh.unsubscribe).not.toHaveBeenCalled();
  });
  it('decodes VAPID keys and registers a new subscription', async () => {
    expect([...new Uint8Array(decodePublicKey('AQID'))]).toEqual([1, 2, 3]);
    const subscription = {
      endpoint: 'https://push.example/token',
      toJSON: () => ({ endpoint: 'https://push.example/token', keys: { p256dh: 'p', auth: 'a' } }),
      unsubscribe: vi.fn(),
    };
    subscribe.mockResolvedValue(subscription);
    vi.mocked(fetch).mockResolvedValueOnce(response({ testSent: true }));

    await expect(enablePushNotifications('AQID')).resolves.toBe(true);
    expect(subscribe).toHaveBeenCalledWith({
      userVisibleOnly: true,
      applicationServerKey: expect.any(ArrayBuffer),
    });
    expect(fetch).toHaveBeenCalledWith(
      '/api/push/subscriptions',
      expect.objectContaining({ method: 'POST' }),
    );
  });

  it('rolls back a new browser subscription when the server rejects it', async () => {
    const subscription = {
      endpoint: 'https://push.example/token',
      toJSON: () => ({ endpoint: 'https://push.example/token' }),
      unsubscribe: vi.fn().mockResolvedValue(true),
    };
    subscribe.mockResolvedValue(subscription);
    vi.mocked(fetch).mockResolvedValueOnce(response({ error: 'database offline' }, false));
    await expect(enablePushNotifications('AQID')).rejects.toThrow('database offline');
    expect(subscription.unsubscribe).toHaveBeenCalled();
  });

  it('removes an existing subscription from server and browser', async () => {
    const subscription = {
      endpoint: 'https://push.example/token',
      unsubscribe: vi.fn().mockResolvedValue(true),
    };
    getSubscription.mockResolvedValue(subscription);
    vi.mocked(fetch).mockResolvedValueOnce(response({ ok: true }));
    await disablePushNotifications();
    expect(fetch).toHaveBeenCalledWith(
      '/api/push/subscriptions',
      expect.objectContaining({ method: 'DELETE' }),
    );
    expect(subscription.unsubscribe).toHaveBeenCalled();
  });

  it('unsubscribes locally when server cleanup is unavailable', async () => {
    const subscription = {
      endpoint: 'https://push.example/token',
      unsubscribe: vi.fn().mockResolvedValue(true),
    };
    getSubscription.mockResolvedValue(subscription);
    vi.mocked(fetch).mockRejectedValueOnce(new Error('offline'));

    await expect(disablePushNotifications()).rejects.toThrow('offline');
    expect(subscription.unsubscribe).toHaveBeenCalledOnce();
  });

  it('can remove only the local subscription after account deletion', async () => {
    const subscription = { endpoint: 'https://push.example/token', unsubscribe: vi.fn().mockResolvedValue(true) };
    getSubscription.mockResolvedValue(subscription);

    await releasePushSubscription();

    expect(subscription.unsubscribe).toHaveBeenCalledOnce();
    expect(fetch).not.toHaveBeenCalled();
  });

  it('does not wait indefinitely when no worker registration exists', async () => {
    getRegistration.mockResolvedValueOnce(undefined);
    await expect(disablePushNotifications()).resolves.toBeUndefined();

    getRegistration.mockResolvedValueOnce(undefined);
    await expect(releasePushSubscription()).resolves.toBeUndefined();
    expect(getSubscription).not.toHaveBeenCalled();
  });
});

describe('the browser subscription that parcel links’ alerts share with the account', () => {
  const auth = { userId: 'user-1', getAccessToken: vi.fn().mockResolvedValue('token') };
  const KEY = new Uint8Array([1, 2, 3]).buffer;
  const pushSubscription = (endpoint: string) => ({
    endpoint,
    options: { applicationServerKey: KEY, userVisibleOnly: true },
    toJSON: () => ({ endpoint, keys: { p256dh: `${endpoint}#p256dh`, auth: `${endpoint}#auth` } }),
    unsubscribe: vi.fn().mockResolvedValue(true),
  });
  const SHARED = 'https://push.example/shared';
  const FRESH = 'https://push.example/fresh';
  const alertOn = (endpoint: string, linkId = LINK_ID) => noteLink(linkId, { alert: { preset: 'important', endpoint, locale: 'de' } });

  it('stays for the parcels’ alerts once the server dropped the account’s, and goes with the last of them', async () => {
    const shared = pushSubscription(SHARED);
    getSubscription.mockResolvedValue(shared);
    alertOn(SHARED);
    vi.mocked(fetch).mockResolvedValue(response({ ok: true }));

    await disablePushNotifications(auth);
    expect(fetch).toHaveBeenCalledWith('/api/push/subscriptions', expect.objectContaining({
      method: 'DELETE', body: JSON.stringify({ endpoint: SHARED }),
    }));
    expect(shared.unsubscribe).not.toHaveBeenCalled();
    expect(subscribe).not.toHaveBeenCalled();
    expect(linkNote(LINK_ID).alert?.endpoint).toBe(SHARED);

    // An alert on a subscription this browser no longer has keeps nothing.
    noteLink(LINK_ID, { alert: null });
    alertOn('https://push.example/older', OTHER_LINK_ID);
    await disablePushNotifications(auth);
    expect(shared.unsubscribe).toHaveBeenCalledOnce();
  });

  it('is replaced when the server can’t be told, so the account’s alerts stop and the parcels’ move to the new one', async () => {
    const shared = pushSubscription(SHARED);
    const fresh = pushSubscription(FRESH);
    getSubscription.mockResolvedValue(shared);
    subscribe.mockResolvedValue(fresh);
    rememberParcel({ id: LINK_ID, key: OWNER_KEY, view: testView() });
    alertOn(SHARED);
    alertOn(SHARED, OTHER_LINK_ID);
    // The server takes one alert and refuses the other.
    links.setParcelAlert.mockImplementation(async (id: string) => { if (id === OTHER_LINK_ID) throw new Error('full'); });
    vi.mocked(fetch).mockRejectedValueOnce(new Error('offline'));

    await expect(disablePushNotifications(auth)).rejects.toThrow('offline');
    expect(shared.unsubscribe).toHaveBeenCalledOnce();
    expect(subscribe).toHaveBeenCalledExactlyOnceWith({ userVisibleOnly: true, applicationServerKey: KEY });
    expect(links.setParcelAlert).toHaveBeenCalledWith(LINK_ID, {
      subscription: { endpoint: FRESH, keys: { p256dh: `${FRESH}#p256dh`, auth: `${FRESH}#auth` } }, preset: 'important', locale: 'de',
    }, OWNER_KEY);
    expect(links.setParcelAlert).toHaveBeenCalledWith(OTHER_LINK_ID, expect.anything(), null);
    expect(linkNote(LINK_ID).alert).toEqual({ preset: 'important', endpoint: FRESH, locale: 'de' });
    expect(linkNote(OTHER_LINK_ID).alert).toBeUndefined();
    expect(fresh.unsubscribe).not.toHaveBeenCalled();
  });

  it('is not replaced for parcels’ alerts the server takes none of, nor without any', async () => {
    const shared = pushSubscription(SHARED);
    const fresh = pushSubscription(FRESH);
    getSubscription.mockResolvedValue(shared);
    subscribe.mockResolvedValue(fresh);
    alertOn(SHARED);
    links.setParcelAlert.mockRejectedValue(new Error('offline'));
    vi.mocked(fetch).mockRejectedValue(new Error('offline'));

    await expect(disablePushNotifications(auth)).rejects.toThrow('offline');
    expect(shared.unsubscribe).toHaveBeenCalledOnce();
    expect(fresh.unsubscribe).toHaveBeenCalledOnce();
    expect(linkNote(LINK_ID).alert).toBeUndefined();

    subscribe.mockClear();
    await expect(disablePushNotifications(auth)).rejects.toThrow('offline');
    expect(subscribe).not.toHaveBeenCalled();
  });

  it('stays after the account is deleted while the parcels’ alerts use it', async () => {
    const shared = pushSubscription(SHARED);
    getSubscription.mockResolvedValue(shared);
    alertOn(SHARED);
    await releasePushSubscription();
    expect(shared.unsubscribe).not.toHaveBeenCalled();
    expect(fetch).not.toHaveBeenCalled();
  });

  it('takes the parcels’ alerts along when the account replaces a subscription the push service dropped, and keeps the new one for them', async () => {
    vi.stubGlobal('Notification', { permission: 'granted', requestPermission: vi.fn() });
    const stale = pushSubscription(SHARED);
    const fresh = pushSubscription(FRESH);
    getSubscription.mockResolvedValue(stale);
    subscribe.mockResolvedValue(fresh);
    alertOn(SHARED);
    vi.mocked(fetch)
      .mockResolvedValueOnce(response({ ok: true, testSent: false }))
      .mockResolvedValueOnce(response({ ok: true }))
      .mockResolvedValueOnce(response({ error: 'database offline' }, false));

    await expect(enablePushNotifications('AQID', auth)).rejects.toThrow('database offline');
    expect(stale.unsubscribe).toHaveBeenCalledOnce();
    expect(links.setParcelAlert).toHaveBeenCalledWith(LINK_ID, expect.objectContaining({ subscription: expect.objectContaining({ endpoint: FRESH }) }), null);
    expect(linkNote(LINK_ID).alert?.endpoint).toBe(FRESH);
    // The account's request failed, but the parcels' alerts now use the new subscription.
    expect(fresh.unsubscribe).not.toHaveBeenCalled();
  });
});

describe('browser notification language', () => {
  it('registers the selected language with a new subscription', async () => {
    subscribe.mockResolvedValue({ toJSON: () => ({ endpoint: 'https://push.example/token', keys: { p256dh: 'p', auth: 'a' } }) });
    await enablePushNotifications('AQID', undefined, 'fr');
    const options = vi.mocked(fetch).mock.calls[0][1]!;
    expect(JSON.parse(String(options.body))).toMatchObject({ locale: 'fr' });
  });

  it('updates an existing device language without subscribing again or asking for permission', async () => {
    vi.stubGlobal('Notification', { permission: 'granted', requestPermission: vi.fn() });
    getSubscription.mockResolvedValue({ endpoint: 'https://push.example/token' });
    const auth = { userId: 'user-1', getAccessToken: vi.fn().mockResolvedValue('token') };
    await followPushNotificationLocale('it', auth);
    expect(fetch).toHaveBeenCalledWith('/api/push/subscriptions', expect.objectContaining({
      method: 'PATCH', body: JSON.stringify({ endpoint: 'https://push.example/token', locale: 'it' }),
    }));
    expect(subscribe).not.toHaveBeenCalled();
    expect(Notification.requestPermission).not.toHaveBeenCalled();
  });

  it('asks the server only when the language or the subscription changed since it was last sent', async () => {
    vi.stubGlobal('Notification', { permission: 'granted', requestPermission: vi.fn() });
    getSubscription.mockResolvedValue({ endpoint: 'https://push.example/token' });
    vi.mocked(fetch).mockResolvedValue(response({ ok: true }));
    const auth = { userId: 'user-1', getAccessToken: vi.fn().mockResolvedValue('token') };
    const patches = () => vi.mocked(fetch).mock.calls.filter(([, init]) => init?.method === 'PATCH').map(([, init]) => JSON.parse(String(init?.body)));

    await followPushNotificationLocale('fr', auth);
    // Every later start in the same language asks nothing.
    await followPushNotificationLocale('fr', auth);
    expect(patches()).toEqual([{ endpoint: 'https://push.example/token', locale: 'fr' }]);

    await followPushNotificationLocale('de', auth);
    getSubscription.mockResolvedValue({ endpoint: 'https://push.example/renewed' });
    await followPushNotificationLocale('de', auth);
    // Another account on this browser has its own subscription row.
    await followPushNotificationLocale('de', { ...auth, userId: 'user-2' });
    expect(patches()).toEqual([
      { endpoint: 'https://push.example/token', locale: 'fr' },
      { endpoint: 'https://push.example/token', locale: 'de' },
      { endpoint: 'https://push.example/renewed', locale: 'de' },
      { endpoint: 'https://push.example/renewed', locale: 'de' },
    ]);
  });

  it('tries again after a failed update, and needs none after turning alerts on in the language', async () => {
    vi.stubGlobal('Notification', { permission: 'granted', requestPermission: vi.fn() });
    const subscription = { endpoint: 'https://push.example/token', toJSON: () => ({ endpoint: 'https://push.example/token' }) };
    getSubscription.mockResolvedValue(subscription);
    const auth = { userId: 'user-1', getAccessToken: vi.fn().mockResolvedValue('token') };
    vi.mocked(fetch).mockResolvedValueOnce(response({ error: 'offline' }, false));
    await expect(followPushNotificationLocale('pl', auth)).rejects.toThrow('offline');
    vi.mocked(fetch).mockResolvedValue(response({ ok: true }));
    await followPushNotificationLocale('pl', auth);
    expect(vi.mocked(fetch)).toHaveBeenCalledTimes(2);

    // Turning alerts on sends the language with the subscription.
    vi.mocked(fetch).mockClear().mockResolvedValue(response({ ok: true, testSent: true }));
    await enablePushNotifications('AQID', auth, 'es');
    await followPushNotificationLocale('es', auth);
    expect(vi.mocked(fetch).mock.calls.map(([, init]) => init?.method)).toEqual(['POST']);

    // Turning them off forgets it: alerts turned on again later send it again.
    getSubscription.mockResolvedValue({ ...subscription, unsubscribe: vi.fn().mockResolvedValue(true) });
    await disablePushNotifications(auth);
    getSubscription.mockResolvedValue(subscription);
    await followPushNotificationLocale('es', auth);
    expect(vi.mocked(fetch).mock.calls.map(([, init]) => init?.method)).toEqual(['POST', 'DELETE', 'PATCH']);
  });

  it('does nothing when alerts are off', async () => {
    const auth = { userId: 'user-1', getAccessToken: vi.fn() };
    await followPushNotificationLocale('de', auth);
    // Allowed, but this browser has no subscription.
    vi.stubGlobal('Notification', { permission: 'granted', requestPermission: vi.fn() });
    await followPushNotificationLocale('de', auth);
    expect(fetch).not.toHaveBeenCalled();
    expect(subscribe).not.toHaveBeenCalled();
    expect(Notification.requestPermission).not.toHaveBeenCalled();
  });
});
