import { afterEach, beforeEach, describe, expect, it, vi } from 'vitest';
import contractFixture from '../../contracts/fixtures/delivery-api.json';
import { ApiAuthenticationError } from '../lib/apiClient';
import { RateLimiter } from '../server/rateLimit';
import { ParcelAlreadyExistsError, RefreshTimeoutError } from '../types';
import { API_CACHE_KEY, browserStorage, clearApiCache, createApiRepo, SERVER_UPDATE_MESSAGE, toEvent } from './apiRepo';

const packageRow = contractFixture.packageList.packages[0];

function response(body: unknown, ok = true, status = 200, headers = new Headers()) {
  return { ok, status, headers, json: vi.fn().mockResolvedValue(body) };
}

describe('createApiRepo', () => {
  beforeEach(() => {
    vi.useFakeTimers();
  });

  afterEach(() => {
    vi.useRealTimers();
    vi.unstubAllGlobals();
    vi.restoreAllMocks();
  });

  it('treats a denied browser storage getter as unavailable', () => {
    const descriptor = Object.getOwnPropertyDescriptor(window, 'localStorage');
    Object.defineProperty(window, 'localStorage', {
      configurable: true,
      get() {
        throw new DOMException('Denied', 'SecurityError');
      },
    });
    try {
      expect(browserStorage()).toBeNull();
    } finally {
      if (descriptor) Object.defineProperty(window, 'localStorage', descriptor);
    }
  });

  it('maps when a parcel’s news arrived and when it was last opened, and leaves both out where the server names none', async () => {
    const fetch = vi.fn().mockResolvedValue(response({ packages: [
      { ...packageRow, news_at: '2026-11-15T10:00:00+00:00', seen_at: '2026-11-15T09:00:00+00:00' },
      { ...packageRow, id: '7d1f0f0e-2a55-4d3c-9d55-0c1f3c1b2a01', news_at: undefined, seen_at: undefined },
    ] }));
    vi.stubGlobal('fetch', fetch);

    const [marked, plain] = await createApiRepo().list();

    expect(marked).toMatchObject({ newsAt: '2026-11-15T10:00:00+00:00', seenAt: '2026-11-15T09:00:00+00:00' });
    expect(plain.newsAt).toBeUndefined();
    expect(plain.seenAt).toBeUndefined();
  });

  it('marks a parcel as seen on the server, and in the list it saved', async () => {
    const fetch = vi.fn()
      .mockResolvedValueOnce(response({ packages: [{ ...packageRow, news_at: '2026-11-15T10:00:00+00:00', seen_at: '2026-11-15T09:00:00+00:00' }] }))
      .mockResolvedValueOnce(response({ seen_at: '2026-11-15T10:30:00+00:00' }));
    vi.stubGlobal('fetch', fetch);
    const repo = createApiRepo();
    try {
      await repo.list();

      await expect(repo.markSeen!(packageRow.id)).resolves.toBe('2026-11-15T10:30:00+00:00');

      expect(fetch).toHaveBeenLastCalledWith(`/api/packages/${packageRow.id}/seen`, expect.objectContaining({ method: 'POST' }));
      expect(fetch.mock.calls[1][1]?.body).toBeUndefined();
      // As of the news the page showed, when it showed one.
      fetch.mockResolvedValueOnce(response({ seen_at: '2026-11-15T10:00:00.999+00:00' }));
      await repo.markSeen!(packageRow.id, '2026-11-15T10:00:00+00:00');
      expect(JSON.parse(String(fetch.mock.calls[2][1]?.body))).toEqual({ news_at: '2026-11-15T10:00:00+00:00' });
      fetch.mockResolvedValueOnce(response({ seen_at: '2026-11-15T10:30:00+00:00' }));
      await repo.markSeen!(packageRow.id);
      // The page opened again starts without the mark.
      expect(repo.cachedList!()?.[0]).toMatchObject({ newsAt: '2026-11-15T10:00:00+00:00', seenAt: '2026-11-15T10:30:00+00:00' });
    } finally {
      window.localStorage.removeItem(API_CACHE_KEY);
    }
  });

  it('loads and maps the account package collection', async () => {
    const fetch = vi.fn().mockResolvedValue(response({ packages: [{ ...packageRow, carrier_data: { ...packageRow.carrier_data, sender_name: 'Example sender', pickup_point: ' Shop ', receiver_name: ' Alex ', weight_kg: 1.25, dimensions_text: '20 × 30 × 10 cm', expected_delivery_from: '2026-07-15', tracking_provider: 'Ship24', original_carrier: 'gls-de', original_tracking_number: '12345678901' } }] }));
    vi.stubGlobal('fetch', fetch);

    const parcels = await createApiRepo().list();

    expect(fetch).toHaveBeenCalledWith('/api/packages?includeArchived=true', expect.objectContaining({
      cache: 'no-store',
      redirect: 'manual',
    }));
    const requestHeaders = new Headers(fetch.mock.calls[0][1]?.headers);
    expect(requestHeaders.get('X-Requested-With')).toBe('XMLHttpRequest');
    expect(parcels[0]).toMatchObject({
      trackingNumber: '993412345699999980',
      label: 'Coffee beans',
      senderName: 'Example sender',
      pickupPoint: 'Shop', receiverName: 'Alex', weightKg: 1.25, dimensionsText: '20 × 30 × 10 cm', expectedDeliveryFrom: '2026-07-15',
      trackingProvider: 'Ship24',
      originalCarrier: 'gls-de',
      originalTrackingNumber: '12345678901',
      carrier: 'swiss-post',
      expectedDelivery: '2026-07-16',
      lastStatusText: 'Sorted',
      syncStatus: 'ok',
      notificationsMuted: false,
      trackingSource: 'swiss-post',
      swissPostReady: true,
      destinationCountry: 'CH',
    });
    expect(parcels[0].events[0]).toMatchObject({
      parcelId: packageRow.id,
      stage: 'in_transit',
      location: 'Härkingen',
      place: { latitude: 47.305, longitude: 7.821, precision: 'city', country: 'CH', name: 'Härkingen' },
    });
    expect(JSON.parse(window.localStorage.getItem(API_CACHE_KEY) ?? 'null')).toHaveLength(1);
    expect(createApiRepo().cachedList?.()?.[0].id).toBe(packageRow.id);
    expect(createApiRepo().cachedList?.()?.[0].senderName).toBe('Example sender');
  });

  it('keeps the steps a carrier wrote in advance apart from its scans and its planned steps', async () => {
    const written = { description: 'Delivered', stage: 'delivered', location: null, occurred_at: '2026-07-14T07:00:00.000Z', place: null };
    vi.stubGlobal('fetch', vi.fn().mockResolvedValue(response({ packages: [{ ...packageRow, written_ahead_by: 'gzyl', written_ahead_events: [written] }] })));
    const [parcel] = await createApiRepo().list();
    expect(parcel).toMatchObject({ writtenAheadBy: 'gzyl',
      writtenAheadEvents: [{ description: 'Delivered', stage: 'delivered', occurredAt: '2026-07-14T07:00:00.000Z' }] });
    expect(parcel).not.toHaveProperty('plannedEvents');
    expect(parcel.events.map((event) => event.description)).not.toContain('Delivered');
  });

  it('shows a carrier picked by hand that refuses the number as the parcel’s, and names the one with its news', async () => {
    const served = (carrier: string) => ({ ...packageRow, carrier, carrier_data: { ...packageRow.carrier_data, active_tracking_carrier: 'aliexpress',
      routing: { served_elsewhere: { picked: 'inpost', carrier: 'aliexpress' } } } });
    vi.stubGlobal('fetch', vi.fn().mockResolvedValue(response({ packages: [served('inpost'), served('dpd')] })));
    const [picked, moved] = await createApiRepo().list();
    expect(picked).toMatchObject({ carrier: 'inpost', servedElsewhere: 'aliexpress' });
    expect(picked.trackingSource).toBeUndefined();
    // Picked again since: the carrier the next check asks tracks it until then.
    expect(moved).toMatchObject({ carrier: 'dpd', trackingSource: 'aliexpress' });
    expect(moved.servedElsewhere).toBeUndefined();
  });

  it('names the postcode a provider asked for one with while it is still the parcel’s', async () => {
    const asked = (postcode: string | null, refused?: string) => ({ ...packageRow, dpd_postcode: postcode, carrier_data: { ...packageRow.carrier_data,
      routing: { provider_input_needed: { provider: 'ParcelsApp', field: 'dpdPostcode', ...(refused ? { refused_postcode: refused } : {}) } } } });
    vi.stubGlobal('fetch', vi.fn().mockResolvedValue(response({ packages: [asked('1234 AB', '1234 AB'), asked('1234 AC', '1234 AB'), asked(null)] })));
    const parcels = await createApiRepo().list();
    expect(parcels.map((parcel) => parcel.providerInputNeeded)).toEqual([
      { provider: 'ParcelsApp', field: 'dpdPostcode', refusedPostcode: '1234 AB' },
      // Changed since: the next check answers.
      { provider: 'ParcelsApp', field: 'dpdPostcode' },
      { provider: 'ParcelsApp', field: 'dpdPostcode' },
    ]);
  });

  it('returns the merged parcel after refreshing its former id', async () => {
    const originalId = '10000000-0000-0000-0000-000000000099';
    vi.stubGlobal('fetch', vi.fn()
      .mockResolvedValueOnce(response({ queued: true, pending: 1, jobIds: [] }))
      .mockResolvedValueOnce(response({ packages: [{ ...packageRow, label: 'GLS / Post', carrier_data: {
        ...packageRow.carrier_data, original_package_id: originalId,
      } }] })));
    await expect(createApiRepo().refreshParcel!(originalId)).resolves.toMatchObject({
      id: packageRow.id, originalParcelId: originalId, label: 'GLS / Post',
    });
  });

  it('returns the same collection while polls find nothing new', async () => {
    const fetch = vi.fn().mockImplementation(async (_path: string, init?: RequestInit) => (
      init?.method === 'PATCH' ? response(packageRow) : response({ packages: [packageRow] })));
    vi.stubGlobal('fetch', fetch);
    const repo = createApiRepo();

    const saved = await repo.list();
    const setItem = vi.spyOn(Storage.prototype, 'setItem');
    await expect(repo.list()).resolves.toBe(saved);
    expect(setItem).not.toHaveBeenCalled();

    await repo.rename(packageRow.id, 'Coffee beans');
    await expect(repo.list()).resolves.not.toBe(saved);

    const reopened = createApiRepo();
    const cached = reopened.cachedList?.();
    await expect(reopened.list()).resolves.toBe(cached);
  });

  it('refreshes at once when a push reports new server state', async () => {
    const worker = new EventTarget();
    Object.defineProperty(navigator, 'serviceWorker', { configurable: true, value: worker });
    try {
      let finish!: () => void;
      const onChange = vi.fn(() => new Promise<void>((resolve) => { finish = resolve; }));
      const stop = createApiRepo().subscribe!(onChange);

      worker.dispatchEvent(new MessageEvent('message', { data: { type: 'other' } }));
      expect(onChange).not.toHaveBeenCalled();
      worker.dispatchEvent(new MessageEvent('message', { data: { type: SERVER_UPDATE_MESSAGE } }));
      expect(onChange).toHaveBeenCalledTimes(1);

      // A push during a running request, which may predate it, asks once more.
      worker.dispatchEvent(new MessageEvent('message', { data: { type: SERVER_UPDATE_MESSAGE } }));
      worker.dispatchEvent(new MessageEvent('message', { data: { type: SERVER_UPDATE_MESSAGE } }));
      finish();
      await vi.waitFor(() => expect(onChange).toHaveBeenCalledTimes(2));
      finish();
      await Promise.resolve();
      expect(onChange).toHaveBeenCalledTimes(2);

      stop();
      worker.dispatchEvent(new MessageEvent('message', { data: { type: SERVER_UPDATE_MESSAGE } }));
      expect(onChange).toHaveBeenCalledTimes(2);
    } finally {
      delete (navigator as { serviceWorker?: unknown }).serviceWorker;
    }
  });

  it('reads the server down for maintenance, keeps the saved list and when it was read, and leaves an offline device offline', async () => {
    window.localStorage.clear();
    vi.setSystemTime(new Date('2026-11-17T09:00:00Z'));
    const fetch = vi.fn().mockResolvedValue(response({ packages: [packageRow] }));
    vi.stubGlobal('fetch', fetch);
    const repo = createApiRepo();
    const saved = await repo.list();
    expect(repo.cachedAt?.()).toBe('2026-11-17T09:00:00.000Z');
    // A poll that finds nothing new still says the list is up to date.
    vi.setSystemTime(new Date('2026-11-17T09:05:00Z'));
    await repo.list();
    expect(repo.cachedAt?.()).toBe('2026-11-17T09:05:00.000Z');

    // Cloudflare's page while the host reboots, the proxy's 404 while the app starts, the edge's own answer, then none.
    fetch.mockResolvedValueOnce({ ok: false, status: 530, headers: new Headers({ 'Content-Type': 'text/html' }), json: vi.fn().mockRejectedValue(new SyntaxError('Not JSON')) });
    await expect(repo.list()).rejects.toMatchObject({ name: 'ServerDownError', maintenance: null });
    fetch.mockResolvedValueOnce({ ok: false, status: 404, headers: new Headers({ 'Content-Type': 'text/plain' }), json: vi.fn().mockRejectedValue(new SyntaxError('Not JSON')) });
    await expect(repo.list()).rejects.toMatchObject({ name: 'ServerDownError' });
    fetch.mockResolvedValueOnce(response({ error: 'The delivery database is temporarily unavailable' }, false, 502));
    await expect(repo.list()).rejects.toMatchObject({ name: 'ServerDownError', maintenance: null });
    fetch.mockResolvedValueOnce(response({
      error: 'Peek is down for maintenance', maintenance: { planned: true, mode: 'soft', since: null, backAround: '2026-11-17T09:30:00Z' },
    }, false, 503, new Headers({ 'Retry-After': '120' })));
    await expect(repo.list()).rejects.toMatchObject({ name: 'ServerDownError', retryAfterMs: 120_000, maintenance: { planned: true, backAround: '2026-11-17T09:30:00Z' } });
    fetch.mockRejectedValueOnce(new TypeError('Failed to fetch'));
    await expect(repo.list()).rejects.toMatchObject({ name: 'ServerDownError' });

    const online = vi.spyOn(navigator, 'onLine', 'get').mockReturnValue(false);
    fetch.mockRejectedValueOnce(new TypeError('Failed to fetch'));
    await expect(repo.list()).rejects.toBeInstanceOf(TypeError);
    online.mockRestore();

    const reopened = createApiRepo();
    expect(reopened.cachedList?.()).toEqual(saved);
    expect(reopened.cachedAt?.()).toBe('2026-11-17T09:05:00.000Z');
    clearApiCache(window.localStorage, 'user-1');
    expect(createApiRepo().cachedAt?.()).toBeNull();
  });

  it('keeps the sign-in when the sign-in server is down too: no new token is the server down, not a lapsed session', async () => {
    const fetch = vi.fn().mockResolvedValue(response({ error: 'Authentication is required' }, false, 401));
    vi.stubGlobal('fetch', fetch);
    const onAuthenticationFailure = vi.fn().mockResolvedValue(undefined);
    const getAccessToken = vi.fn(async (refresh?: boolean) => {
      if (refresh) throw Object.assign(new Error('Bad gateway'), { name: 'AuthRetryableFetchError', status: 530 });
      return 'synthetic-token';
    });
    const repo = createApiRepo(30_000, 1_000, window.localStorage, { userId: 'user-1', getAccessToken, onAuthenticationFailure });

    await expect(repo.list()).rejects.toMatchObject({ name: 'ServerDownError' });
    expect(getAccessToken).toHaveBeenCalledWith(true);
    expect(onAuthenticationFailure).not.toHaveBeenCalled();
  });

  it('ignores a corrupted offline snapshot', () => {
    window.localStorage.setItem(API_CACHE_KEY, '[{"id":"incomplete"}]');
    expect(createApiRepo().cachedList?.()).toBeNull();
  });

  it('clears only the signed-out account and the legacy shared cache', () => {
    window.localStorage.setItem(API_CACHE_KEY, 'legacy');
    window.localStorage.setItem(`${API_CACHE_KEY}.user-1`, 'first');
    window.localStorage.setItem(`${API_CACHE_KEY}.user-2`, 'second');

    clearApiCache(window.localStorage, 'user-1');

    expect(window.localStorage.getItem(API_CACHE_KEY)).toBeNull();
    expect(window.localStorage.getItem(`${API_CACHE_KEY}.user-1`)).toBeNull();
    expect(window.localStorage.getItem(`${API_CACHE_KEY}.user-2`)).toBe('second');
  });

  it('normalises additions and keeps explicit carrier choices', async () => {
    const fetch = vi.fn().mockResolvedValue(response({ package: packageRow, jobIds: [] }));
    vi.stubGlobal('fetch', fetch);

    await createApiRepo().add({
      trackingNumber: '99.34.123456.99999980',
      label: 'Coffee beans',
    });
    expect(fetch).toHaveBeenCalledWith('/api/packages', expect.objectContaining({
      method: 'POST',
      body: JSON.stringify({
        trackingNumber: '993412345699999980',
        label: 'Coffee beans',
        carrier: 'swiss-post',
      }),
      cache: 'no-store',
      redirect: 'manual',
    }));
    const requestHeaders = new Headers(fetch.mock.calls[0][1]?.headers);
    expect(requestHeaders.get('Content-Type')).toBe('application/json');
    expect(requestHeaders.get('X-Requested-With')).toBe('XMLHttpRequest');

    await createApiRepo().add({
      trackingNumber: '44.99.999999.99999980',
      label: 'Quickpac parcel',
    });
    expect(fetch).toHaveBeenLastCalledWith('/api/packages', expect.objectContaining({
      body: JSON.stringify({
        trackingNumber: '449999999999999980',
        label: 'Quickpac parcel',
        carrier: 'quickpac',
      }),
    }));

    await createApiRepo().add({
      trackingNumber: 'ambiguous-123',
      label: '',
      carrier: 'planzer',
    });
    expect(fetch).toHaveBeenLastCalledWith('/api/packages', expect.objectContaining({
      body: JSON.stringify({
        trackingNumber: 'AMBIGUOUS123',
        label: '',
        carrier: 'planzer',
      }),
    }));

    const trackingUrl =
      'https://trackandtrace.planzergroup.com/shared/sendungen/999.90.99999987?accessKey=abcdefghijklmnopqrstuvwxyzABCDEFGH';
    await createApiRepo().add({
      trackingNumber: '999.90.99999987',
      label: 'Plants',
      carrier: 'planzer',
      trackingUrl,
    });
    expect(fetch).toHaveBeenLastCalledWith('/api/packages', expect.objectContaining({
      body: JSON.stringify({
        trackingNumber: '9999099999987',
        label: 'Plants',
        carrier: 'planzer',
        trackingUrl,
      }),
    }));

    await createApiRepo().add({
      trackingNumber: '06080000000002',
      label: 'DPD parcel',
      carrier: 'dpd',
      dpdPostcode: '8000',
    });
    expect(fetch).toHaveBeenLastCalledWith('/api/packages', expect.objectContaining({
      body: JSON.stringify({
        trackingNumber: '06080000000002',
        label: 'DPD parcel',
        carrier: 'dpd',
        dpdPostcode: '8000',
      }),
    }));

    // A postcode from abroad goes with its country; a country without a postcode goes nowhere.
    await createApiRepo().add({
      trackingNumber: '3SABCD1234567',
      label: '',
      carrier: 'spring-gds',
      dpdPostcode: '1000',
      dpdPostcodeCountry: 'BE',
    });
    expect(fetch).toHaveBeenLastCalledWith('/api/packages', expect.objectContaining({
      body: JSON.stringify({
        trackingNumber: '3SABCD1234567',
        label: '',
        carrier: 'spring-gds',
        dpdPostcode: '1000',
        dpdPostcodeCountry: 'BE',
      }),
    }));
    await createApiRepo().add({
      trackingNumber: '3SABCD1234567',
      label: '',
      carrier: 'spring-gds',
      dpdPostcodeCountry: 'BE',
    });
    expect(fetch).toHaveBeenLastCalledWith('/api/packages', expect.objectContaining({
      body: JSON.stringify({ trackingNumber: '3SABCD1234567', label: '', carrier: 'spring-gds' }),
    }));
  });

  it('archives, restores, and queues syncs through the same-origin API', async () => {
    const fetch = vi
      .fn()
      .mockResolvedValueOnce(response({ ok: true }))
      .mockResolvedValueOnce(response({ queued: true, pending: 1, jobIds: [] }, true, 202))
      .mockResolvedValueOnce(response({ packages: [packageRow] }))
      .mockResolvedValueOnce(response({ queued: true, pending: 1, jobIds: [] }, true, 202))
      .mockResolvedValueOnce(response({ packages: [packageRow] }))
      .mockResolvedValueOnce(response(packageRow));
    vi.stubGlobal('fetch', fetch);
    const repo = createApiRepo();

    await repo.remove(packageRow.id);
    expect(fetch).toHaveBeenNthCalledWith(
      1,
      `/api/packages/${packageRow.id}`,
      expect.objectContaining({ method: 'DELETE', cache: 'no-store', redirect: 'manual' }),
    );

    const parcels = await repo.refresh();
    expect(fetch).toHaveBeenNthCalledWith(2, '/api/sync', {
      signal: expect.any(AbortSignal),
      method: 'POST',
      cache: 'no-store',
      headers: expect.any(Headers),
      redirect: 'manual',
    });
    expect(parcels).toHaveLength(1);

    const parcel = await repo.refreshParcel!(packageRow.id);
    expect(fetch).toHaveBeenNthCalledWith(
      4,
      `/api/packages/${packageRow.id}/sync`,
      expect.objectContaining({ method: 'POST', cache: 'no-store', redirect: 'manual' }),
    );
    expect(parcel.id).toBe(packageRow.id);

    const restored = await repo.restore!(packageRow.id);
    expect(fetch).toHaveBeenNthCalledWith(
      6,
      `/api/packages/${packageRow.id}/restore`,
      expect.objectContaining({ method: 'POST', cache: 'no-store', redirect: 'manual' }),
    );
    expect(restored.archivedAt).toBeUndefined();
  });

  it('renames a parcel through the same-origin API', async () => {
    const renamedRow = { ...packageRow, label: 'Espresso beans' };
    const fetch = vi.fn().mockResolvedValue(response(renamedRow));
    vi.stubGlobal('fetch', fetch);

    const parcel = await createApiRepo().rename(packageRow.id, ' Espresso beans ');

    expect(parcel.label).toBe('Espresso beans');
    expect(fetch).toHaveBeenCalledWith(
      `/api/packages/${packageRow.id}`,
      expect.objectContaining({
        method: 'PATCH',
        body: JSON.stringify({ label: 'Espresso beans' }),
        cache: 'no-store',
        redirect: 'manual',
      }),
    );
  });

  it('changes a parcel carrier and starts monitoring its fresh tracking job', async () => {
    const changedRow = {
      ...packageRow,
      carrier: 'ups' as const,
      current_stage: 'pending' as const,
      expected_delivery: null,
      last_status_text: null,
      last_synced_at: null,
      sync_status: 'pending' as const,
      tracking_events: [],
    };
    const fetch = vi.fn().mockResolvedValue(response({
      package: changedRow,
      jobIds: [],
    }));
    vi.stubGlobal('fetch', fetch);

    const parcel = await createApiRepo().changeCarrier!(packageRow.id, { carrier: 'ups' });

    expect(parcel).toMatchObject({ carrier: 'ups', syncStatus: 'pending', events: [] });
    expect(fetch).toHaveBeenCalledWith(
      `/api/packages/${packageRow.id}/carrier`,
      expect.objectContaining({
        method: 'PATCH',
        body: JSON.stringify({ carrier: 'ups' }),
        cache: 'no-store',
        redirect: 'manual',
      }),
    );
  });

  it('preserves the existing parcel id on duplicate creation errors', async () => {
    const fetch = vi.fn().mockResolvedValue(response({
      error: 'This tracking number is already in your delivery box',
      packageId: packageRow.id,
    }, false, 409));
    vi.stubGlobal('fetch', fetch);

    const error = await createApiRepo().add({
      trackingNumber: packageRow.tracking_number,
      label: '',
    }).catch((reason: unknown) => reason);

    expect(error).toBeInstanceOf(ParcelAlreadyExistsError);
    expect(error).toMatchObject({ parcelId: packageRow.id });
  });

  it('permanently deletes a parcel through its guarded endpoint', async () => {
    const fetch = vi.fn().mockResolvedValue(response({ ok: true }));
    vi.stubGlobal('fetch', fetch);
    window.localStorage.setItem(API_CACHE_KEY, JSON.stringify([{
      id: packageRow.id,
      trackingNumber: packageRow.tracking_number,
      label: packageRow.label,
      carrier: packageRow.carrier,
      createdAt: packageRow.created_at,
      syncStatus: packageRow.sync_status,
      events: [],
    }]));

    await createApiRepo().deletePermanently!(packageRow.id);

    expect(fetch).toHaveBeenCalledWith(
      `/api/packages/${packageRow.id}/permanent`,
      expect.objectContaining({ method: 'DELETE', cache: 'no-store', redirect: 'manual' }),
    );
    expect(JSON.parse(window.localStorage.getItem(API_CACHE_KEY) ?? 'null')).toEqual([]);
  });

  it('mutes parcel notifications through the owner-scoped API', async () => {
    const mutedRow = { ...packageRow, notifications_muted: true };
    const fetch = vi.fn().mockResolvedValue(response(mutedRow));
    vi.stubGlobal('fetch', fetch);

    const parcel = await createApiRepo().setNotificationsMuted!(packageRow.id, true);

    expect(parcel.notificationsMuted).toBe(true);
    expect(fetch).toHaveBeenCalledWith(
      `/api/packages/${packageRow.id}/notifications`,
      expect.objectContaining({
        method: 'PATCH',
        body: JSON.stringify({ muted: true }),
      }),
    );
  });

  it('mutes the delivery email of one parcel with a request that names only the email', async () => {
    const fetch = vi.fn().mockResolvedValue(response({ ...packageRow, email_muted: true }));
    vi.stubGlobal('fetch', fetch);

    const parcel = await createApiRepo().setEmailMuted!(packageRow.id, true);

    expect(parcel).toMatchObject({ emailMuted: true, notificationsMuted: packageRow.notifications_muted });
    expect(fetch).toHaveBeenCalledExactlyOnceWith(
      `/api/packages/${packageRow.id}/notifications`,
      expect.objectContaining({ method: 'PATCH', body: JSON.stringify({ emailMuted: true }) }),
    );
  });

  it('reads the email mute of an account’s parcels, and none from an answer that says nothing', async () => {
    const fetch = vi.fn().mockResolvedValue(response({ packages: [
      { ...packageRow, id: 'muted', email_muted: true }, { ...packageRow, id: 'open', email_muted: false }, { ...packageRow, id: 'older' },
    ] }));
    vi.stubGlobal('fetch', fetch);

    expect((await createApiRepo().list()).map((parcel) => [parcel.id, parcel.emailMuted]))
      .toEqual([['muted', true], ['open', false], ['older', undefined]]);
  });

  it('surfaces API errors and malformed success responses', async () => {
    const fetch = vi
      .fn()
      .mockResolvedValueOnce(response({ error: 'The request could not be completed' }, false, 500))
      .mockResolvedValueOnce({ ok: false, status: 400, json: vi.fn().mockRejectedValue(new Error()) })
      .mockResolvedValueOnce(response(null));
    vi.stubGlobal('fetch', fetch);
    const repo = createApiRepo();

    await expect(repo.list()).rejects.toThrow('The request could not be completed');
    await expect(repo.list()).rejects.toThrow('Delivery service failed (400)');
    await expect(repo.list()).rejects.toThrow('empty response');
  });

  it('recognises redirects and expired authentication responses', async () => {
    const fetch = vi
      .fn()
      .mockResolvedValueOnce({
        ...response(null, false, 0),
        type: 'opaqueredirect',
        redirected: false,
      })
      .mockResolvedValueOnce({
        ...response(null, false, 401),
        type: 'basic',
        redirected: false,
      });
    vi.stubGlobal('fetch', fetch);
    const repo = createApiRepo();

    await expect(repo.list()).rejects.toBeInstanceOf(ApiAuthenticationError);
    await expect(repo.list()).rejects.toThrow('sign-in expired');
  });

  it('sends the current bearer token, refreshes once, and isolates the cache', async () => {
    const fetch = vi
      .fn()
      .mockResolvedValueOnce(response({ error: 'Expired' }, false, 401))
      .mockResolvedValueOnce(response({ packages: [packageRow] }));
    vi.stubGlobal('fetch', fetch);
    const getAccessToken = vi
      .fn()
      .mockResolvedValueOnce('old-token')
      .mockResolvedValueOnce('fresh-token');
    const onAuthenticationFailure = vi.fn();
    const repo = createApiRepo(30_000, 1_000, window.localStorage, {
      userId: 'user-1',
      getAccessToken,
      onAuthenticationFailure,
    });

    await expect(repo.list()).resolves.toHaveLength(1);

    expect(getAccessToken).toHaveBeenNthCalledWith(1, false);
    expect(getAccessToken).toHaveBeenNthCalledWith(2, true);
    const firstHeaders = new Headers(fetch.mock.calls[0][1]?.headers);
    const secondHeaders = new Headers(fetch.mock.calls[1][1]?.headers);
    expect(firstHeaders.get('Authorization')).toBe('Bearer old-token');
    expect(secondHeaders.get('Authorization')).toBe('Bearer fresh-token');
    expect(onAuthenticationFailure).not.toHaveBeenCalled();
    expect(window.localStorage.getItem(`${API_CACHE_KEY}.user-1`)).not.toBeNull();
    expect(window.localStorage.getItem(API_CACHE_KEY)).toBeNull();
  });

  it('polls while visible, refreshes on visibility changes, and unsubscribes', async () => {
    let visibility: DocumentVisibilityState = 'visible';
    vi.spyOn(document, 'visibilityState', 'get').mockImplementation(() => visibility);
    const onChange = vi.fn();
    const unsubscribe = createApiRepo(1_000).subscribe?.(onChange);

    await vi.advanceTimersByTimeAsync(1_000);
    expect(onChange).toHaveBeenCalledOnce();

    visibility = 'hidden';
    vi.advanceTimersByTime(1_000);
    expect(onChange).toHaveBeenCalledOnce();

    visibility = 'visible';
    document.dispatchEvent(new Event('visibilitychange'));
    await Promise.resolve();
    expect(onChange).toHaveBeenCalledTimes(2);

    unsubscribe?.();
    await vi.advanceTimersByTimeAsync(1_000);
    expect(onChange).toHaveBeenCalledTimes(2);
  });

  it('says when it reads the list out of sight, which does not count as the account\'s apps open', async () => {
    vi.spyOn(document, 'visibilityState', 'get').mockReturnValue('hidden');
    const fetch = vi.fn().mockResolvedValue(response({ packages: [packageRow] }));
    vi.stubGlobal('fetch', fetch);
    await createApiRepo().list();
    expect(fetch.mock.calls[0][0]).toBe('/api/packages?includeArchived=true&hidden=true');
  });

  it('polls only the lightweight job endpoint before reloading a newly added parcel', async () => {
    vi.spyOn(document, 'visibilityState', 'get').mockReturnValue('visible');
    const pendingRow = {
      ...packageRow,
      last_synced_at: null,
      sync_status: 'pending',
      tracking_events: [
        {
          ...packageRow.tracking_events[0],
          stage: 'pending',
          description: 'Tracking added',
        },
      ],
    };
    const fetch = vi
      .fn()
      .mockResolvedValueOnce(response({
        package: pendingRow,
        jobIds: [contractFixture.job.id],
      }))
      .mockResolvedValueOnce(response({ jobs: [{ ...contractFixture.job, status: 'queued' }] }))
      .mockResolvedValueOnce(response({ jobs: [contractFixture.job] }))
      .mockResolvedValueOnce(response({ packages: [packageRow] }));
    vi.stubGlobal('fetch', fetch);
    const repo = createApiRepo(30_000, 1_000);
    const onChange = vi.fn(async () => {
      await repo.list();
    });
    const unsubscribe = repo.subscribe?.(onChange);

    await repo.add({ trackingNumber: packageRow.tracking_number, label: 'Coffee' });
    await vi.advanceTimersByTimeAsync(0);
    expect(fetch.mock.calls[1][0]).toBe(`/api/sync/jobs?ids=${contractFixture.job.id}`);
    await vi.advanceTimersByTimeAsync(999);
    expect(onChange).not.toHaveBeenCalled();

    await vi.advanceTimersByTimeAsync(1);
    expect(onChange).toHaveBeenCalledOnce();
    expect(fetch).toHaveBeenCalledTimes(4);
    expect(fetch.mock.calls[2][0]).toBe(`/api/sync/jobs?ids=${contractFixture.job.id}`);
    expect(fetch.mock.calls[3][0]).toBe('/api/packages?includeArchived=true');

    await vi.advanceTimersByTimeAsync(1_000);
    expect(onChange).toHaveBeenCalledOnce();
    unsubscribe?.();
  });

  it('finishes five slow jobs without exhausting the real read-rate budget', async () => {
    const start = Date.now();
    const limiter = new RateLimiter(4_096, () => Date.now() / 1_000);
    const ids = Array.from({ length: 5 }, (_, index) => `job-${index}`);
    let reads = 0;
    const fetch = vi.fn(async (path: string) => {
      if (path === '/api/sync') return response({ jobIds: ids });
      if (path.startsWith('/api/sync/jobs?')) {
        reads += 1;
        expect(limiter.retryAfter('reads', { limit: 240, window: 60 })).toBe(0);
        const requested = new URL(path, 'https://delivery.test').searchParams.get('ids')!.split(',');
        // The first job finishes immediately and must disappear from later reads.
        if (reads > 1) expect(requested).not.toContain('job-0');
        return response({ jobs: requested.map((id) => ({
          ...contractFixture.job, id,
          status: id === 'job-0' || Date.now() - start >= 70_000 ? 'succeeded' : 'running',
        })) });
      }
      return response({ packages: [packageRow] });
    });
    vi.stubGlobal('fetch', fetch);
    const refresh = createApiRepo().refresh();
    await vi.advanceTimersByTimeAsync(90_000);
    await expect(refresh).resolves.toHaveLength(1);
    expect(reads).toBeLessThan(20);
  });

  it('ends the wait with a timeout error while the jobs keep running', async () => {
    const fetch = vi.fn(async (path: string) => path === '/api/sync'
      ? response({ jobIds: [contractFixture.job.id] })
      : response({ jobs: [{ ...contractFixture.job, status: 'running' }] }));
    vi.stubGlobal('fetch', fetch);
    const refresh = createApiRepo().refresh();
    const settled = expect(refresh).rejects.toBeInstanceOf(RefreshTimeoutError);
    await vi.advanceTimersByTimeAsync(120_000);
    await settled;
  });

  it.each(['12', new Date('2026-09-06T12:00:12Z').toUTCString()])(
    'honors Retry-After %s without re-queueing the job', async (retryAfter) => {
      vi.setSystemTime(new Date('2026-09-06T12:00:00Z'));
      const fetch = vi.fn()
        .mockResolvedValueOnce(response({ jobIds: [contractFixture.job.id] }))
        .mockResolvedValueOnce(response({ error: 'Slow down' }, false, 429, new Headers({ 'Retry-After': retryAfter })))
        .mockResolvedValueOnce(response({ jobs: [contractFixture.job] }))
        .mockResolvedValueOnce(response({ packages: [packageRow] }));
      vi.stubGlobal('fetch', fetch);
      const refresh = createApiRepo().refresh();
      await vi.advanceTimersByTimeAsync(11_999);
      expect(fetch).toHaveBeenCalledTimes(2);
      await vi.advanceTimersByTimeAsync(1);
      await expect(refresh).resolves.toHaveLength(1);
      expect(fetch.mock.calls.filter(([path]) => path === '/api/sync')).toHaveLength(1);
    },
  );

  it('stops monitoring after unsubscribe and never publishes a late refresh', async () => {
    const fetch = vi.fn()
      .mockResolvedValueOnce(response({ package: packageRow, jobIds: [contractFixture.job.id] }))
      .mockResolvedValue(response({ jobs: [{ ...contractFixture.job, status: 'running' }] }));
    vi.stubGlobal('fetch', fetch);
    const repo = createApiRepo();
    const onChange = vi.fn();
    const unsubscribe = repo.subscribe!(onChange);
    await repo.add({ trackingNumber: packageRow.tracking_number, label: '' });
    await vi.advanceTimersByTimeAsync(0);
    unsubscribe();
    await vi.advanceTimersByTimeAsync(120_000);
    expect(fetch).toHaveBeenCalledTimes(2);
    expect(onChange).not.toHaveBeenCalled();
  });

  it.each([
    { ...contractFixture.job, status: 'failed', error: 'Worker stopped' },
    { ...contractFixture.job, result: { errors: 1 } },
  ])('does not report success for a failed refresh', async (job) => {
    const fetch = vi.fn()
      .mockResolvedValueOnce(response({ jobIds: [job.id] }))
      .mockResolvedValueOnce(response({ jobs: [job] }));
    vi.stubGlobal('fetch', fetch);
    await expect(createApiRepo().refresh()).rejects.toThrow();
    expect(fetch).toHaveBeenCalledTimes(2);
  });
});

it('rejects stale collection data after a mutation without regressing the offline cache', async () => {
  const fetcher = vi.fn().mockResolvedValue(response({ packages: [packageRow] }));
  vi.stubGlobal('fetch', fetcher);
  const repo = createApiRepo();
  await repo.list();
  let finish!: (value: unknown) => void;
  fetcher.mockImplementationOnce(() => new Promise(resolve => { finish = resolve; }));
  const poll = repo.list();
  const rejection = expect(poll).rejects.toMatchObject({ name: 'AbortError' });
  fetcher.mockResolvedValueOnce(response({ ...packageRow, label: 'Renamed offline too' }));
  await repo.rename(packageRow.id, 'Renamed offline too');
  finish(response({ packages: [packageRow] }));
  await rejection;
  expect(repo.cachedList?.()?.[0].label).toBe('Renamed offline too');
  vi.unstubAllGlobals();
});

describe('toEvent', () => {
  it('keeps the day of a scan the carrier dated by its day alone', () => {
    const row = { ...packageRow.tracking_events[0], occurred_at: '2026-10-01T22:00:00.000Z' };
    expect(toEvent({ ...row, day: '2026-10-02' } as Parameters<typeof toEvent>[0]).day).toBe('2026-10-02');
    expect(toEvent(row as Parameters<typeof toEvent>[0]).day).toBeUndefined();
  });

  it('keeps the flag of a status Peek saw at a check, whose time is the check\'s', () => {
    const row = packageRow.tracking_events[0] as Parameters<typeof toEvent>[0];
    expect(toEvent({ ...row, observed: true }).observed).toBe(true);
    expect('observed' in toEvent(row)).toBe(false);
  });
});
