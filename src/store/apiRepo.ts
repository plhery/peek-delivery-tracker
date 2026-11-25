import { carrierTrackingNumber, detectCarrier, normalizeTrackingNumber } from '../lib/carriers';
import { storedPostcodeCountry } from '../lib/postcodeCountry';
import { timeZoneRegion } from '../lib/postcodeExample';
import { ApiAuthenticationError, authenticatedFetch, CLIENT_INPUTS, type ApiAuth } from '../lib/apiClient';
import { NoteFileError, uploadProblem, withoutFile } from '../lib/parcelNote';
import { announcedMaintenance, deviceOnline, ServerDownError, serverDown, unanswered } from '../lib/maintenance';
import type {
  ApiAddPackageFileResponse,
  ApiCreatePackageResponse,
  ApiCreatePackageRequest,
  ApiChangePackageCarrierRequest,
  ApiChangePackageCarrierResponse,
  ApiOkResponse,
  ApiPackageFile,
  ApiPackageListResponse,
  ApiPackageRow,
  ApiQueueResponse,
  ApiRenamePackageRequest,
  ApiPackageNotificationRequest,
  ApiPackageSeenRequest,
  ApiPackageSeenResponse,
  ApiPlannedTrackingEvent,
  ApiTrackingEventRow,
  ApiSyncJobListResponse,
} from '../generated/apiContract';
import {
  ParcelAlreadyExistsError,
  RefreshTimeoutError,
  type NewParcelInput,
  type ParcelCarrierInput,
  type ParcelFile,
  type ParcelRepo,
  type ParcelWithEvents,
  type PlannedEvent,
  type TrackingEvent,
} from '../types';

export const API_CACHE_KEY = 'parcel-post.api-cache.v1';
/** Beside a cache, when its list was last read from the server. */
const READ_AT_SUFFIX = '.at';
/** Posted by the service worker when a push notification reports new server state. */
export const SERVER_UPDATE_MESSAGE = 'sdt:server-update';

export function browserStorage(): Storage | null {
  if (typeof window === 'undefined') return null;
  try {
    return window.localStorage;
  } catch {
    return null;
  }
}

export function clearApiCache(storage: Storage | null, userId: string): void {
  if (!storage) return;
  try {
    for (const key of [API_CACHE_KEY, `${API_CACHE_KEY}.${userId}`]) {
      storage.removeItem(key);
      storage.removeItem(key + READ_AT_SUFFIX);
    }
  } catch {
    // Storage can be unavailable in private browsing.
  }
}

function cachedParcels(storage: Storage | null, cacheKey: string): ParcelWithEvents[] | null {
  return parseCachedParcels(readCache(storage, cacheKey));
}

function readCache(storage: Storage | null, cacheKey: string): string | null {
  try {
    return storage?.getItem(cacheKey) ?? null;
  } catch {
    return null;
  }
}

function parseCachedParcels(serialized: string | null): ParcelWithEvents[] | null {
  if (serialized === null) return null;
  try {
    const value: unknown = JSON.parse(serialized);
    if (!Array.isArray(value)) return null;
    const valid = value.every((parcel) => {
      if (!parcel || typeof parcel !== 'object') return false;
      const candidate = parcel as Partial<ParcelWithEvents>;
      return typeof candidate.id === 'string'
        && typeof candidate.trackingNumber === 'string'
        && typeof candidate.label === 'string'
        && typeof candidate.carrier === 'string'
        && typeof candidate.createdAt === 'string'
        && typeof candidate.syncStatus === 'string'
        && Array.isArray(candidate.events);
    });
    return valid ? value as ParcelWithEvents[] : null;
  } catch {
    return null;
  }
}

/** Notes that the list was just read from the server, for a notice to say how old the saved one is. */
function saveReadAt(storage: Storage | null, cacheKey: string) {
  try {
    storage?.setItem(cacheKey + READ_AT_SUFFIX, new Date().toISOString());
  } catch {
    // Storage can be unavailable in private browsing or on a full device.
  }
}

function saveCachedParcels(
  storage: Storage | null,
  cacheKey: string,
  parcels: ParcelWithEvents[] | string,
) {
  if (!storage) return;
  try {
    storage.setItem(cacheKey, typeof parcels === 'string' ? parcels : JSON.stringify(parcels));
  } catch {
    // Storage can be unavailable in private browsing or on a full device.
  }
}

/** A tracking event row as the client model. */
export function toEvent(row: ApiTrackingEventRow): TrackingEvent {
  return {
    id: row.id,
    parcelId: row.package_id,
    stage: row.stage,
    description: row.description,
    location: row.location ?? undefined,
    place: row.place ?? undefined,
    occurredAt: row.occurred_at,
    day: row.day ?? undefined,
    ...(row.observed ? { observed: true } : {}),
    relayOf: row.relay_of ?? undefined,
    recipientAction: row.recipient_action ?? undefined,
    tieOrder: row.tie_order ?? undefined,
  };
}

/** A provider's postcode request, naming the postcode it asked again with while that is still the parcel's. */
function providerInputNeeded(row: ApiPackageRow): ParcelWithEvents['providerInputNeeded'] {
  const needed = row.carrier_data?.routing?.provider_input_needed;
  if (!needed) return undefined;
  const refused = needed.refused_postcode?.trim();
  return { provider: needed.provider, field: needed.field,
    ...(refused && refused === row.dpd_postcode?.trim() ? { refusedPostcode: refused } : {}) };
}

/** A package row as the client model; a parcel link's package maps through it too. */
export function toParcel(row: ApiPackageRow): ParcelWithEvents {
  // A carrier picked by hand that refuses the number stays the parcel's, shown as it, while another carrier's news fills it.
  const served = row.carrier_data?.routing?.served_elsewhere;
  const servedElsewhere = served?.picked === row.carrier ? served.carrier : undefined;
  return {
    id: row.id,
    trackingNumber: row.tracking_number,
    label: row.label,
    carrier: row.carrier,
    createdAt: row.created_at,
    expectedDelivery: row.expected_delivery ?? undefined,
    ...(row.expected_delivery_passed ? { expectedDeliveryPassed: row.expected_delivery_passed } : {}),
    ...(row.delivery_round_ends_at ? { deliveryRoundEndsAt: row.delivery_round_ends_at } : {}),
    ...(row.peek_ai ? { peekAi: row.peek_ai } : {}),
    autoChangedFrom: row.carrier_data?.auto_changed_from ?? undefined,
    autoChangedTo: row.carrier_data?.auto_changed_to ?? undefined,
    autoChangedAt: row.carrier_data?.auto_changed_at ?? undefined,
    providerInputNeeded: providerInputNeeded(row),
    nextCheckAt: row.carrier_data?.routing?.next_check_at,
    inputNeeded: row.carrier_data?.routing?.input_needed ?? undefined,
    inputSuggested: row.carrier_data?.routing?.input_suggested ?? undefined,
    likelyCarrier: row.carrier_data?.routing?.likely_carrier ?? undefined,
    writtenAheadBy: row.written_ahead_by ?? undefined,
    senderName: row.carrier_data?.sender_name?.trim() || undefined,
    expectedDeliveryFrom: row.carrier_data?.expected_delivery_from ?? undefined,
    pickupPoint: row.carrier_data?.pickup_point?.trim() || undefined,
    receiverName: row.carrier_data?.receiver_name?.trim() || undefined,
    weightKg: row.carrier_data?.weight_kg ?? undefined,
    dimensionsText: row.carrier_data?.dimensions_text?.trim() || undefined,
    importCharges: row.carrier_data?.import_charges ?? undefined,
    originalParcelId: row.carrier_data?.original_package_id ?? undefined,
    originalCarrier: row.carrier_data?.original_carrier ?? undefined,
    originalTrackingNumber: row.carrier_data?.original_tracking_number ?? undefined,
    originalTrackingUrl: row.carrier_data?.original_tracking_url ?? undefined,
    lastStatusText: row.last_status_text ?? undefined,
    lastSyncedAt: row.last_synced_at ?? undefined,
    syncStatus: row.sync_status,
    syncError: row.sync_error ?? undefined,
    trackingUrl: row.tracking_url ?? undefined,
    dpdPostcode: row.dpd_postcode ?? undefined,
    dpdPostcodeCountry: storedPostcodeCountry(row.carrier_data, row.dpd_postcode),
    phoneDigits: row.phone_digits ?? undefined,
    dpdPostcodeVerified: row.carrier_data?.dpd_postcode_verified ?? undefined,
    postcodeRefused: row.carrier_data?.routing?.postcode_refused?.carrier === row.carrier || undefined,
    servedElsewhere,
    trackingProvider: row.carrier_data?.tracking_provider ?? undefined,
    carrierAnswered: row.carrier_data?.carrier_answered ?? undefined,
    trackingSource: servedElsewhere ? undefined : row.carrier_data?.active_tracking_carrier ?? undefined,
    activeTrackingNumber: row.carrier_data?.active_tracking_number ?? undefined,
    deliveryCarrier: row.carrier_data?.delivery_carrier ?? undefined,
    deliveryTrackingNumber: row.carrier_data?.delivery_tracking_number ?? undefined,
    deliveryAnnounced: row.carrier_data?.delivery_announced ?? undefined,
    canonicalTrackingNumber: row.carrier_data?.canonical_tracking_number ?? undefined,
    swissPostReady: row.carrier_data?.swiss_post_ready ?? undefined,
    returnHeld: row.carrier_data?.return_held ?? undefined,
    destinationCountry: row.carrier_data?.destination_country ?? undefined,
    destinationPlace: row.destination_place ?? undefined,
    usualPlace: row.usual_place ?? undefined,
    archivedAt: row.archived_at ?? undefined,
    ...(row.note ? { note: row.note } : {}),
    ...(row.files?.length ? { files: row.files.map(toParcelFile) } : {}),
    newsAt: row.news_at ?? undefined,
    seenAt: row.seen_at ?? undefined,
    notificationsMuted: row.notifications_muted,
    emailMuted: row.email_muted,
    events: (row.tracking_events ?? []).map(toEvent),
    ...(row.planned_events?.length ? { plannedEvents: row.planned_events.map(toPlannedEvent) } : {}),
    ...(row.written_ahead_events?.length ? { writtenAheadEvents: row.written_ahead_events.map(toPlannedEvent) } : {}),
    ...(row.carrier_outage ? { carrierOutage: {
      carrier: row.carrier_outage.carrier, since: row.carrier_outage.since,
      ...(row.carrier_outage.tracking_url ? { trackingUrl: row.carrier_outage.tracking_url } : {}),
    } } : {}),
  };
}

/** A photo or PDF of the parcel's note. Its addresses are relative to the API, which the web app shares an origin with. */
export function toParcelFile(row: ApiPackageFile): ParcelFile {
  return {
    id: row.id,
    name: row.name,
    contentType: row.content_type,
    size: row.size,
    ...(row.width ? { width: row.width } : {}),
    ...(row.height ? { height: row.height } : {}),
    createdAt: row.created_at,
    url: row.url,
    thumbnailUrl: row.thumbnail_url,
    expiresAt: row.expires_at,
  };
}

/** A step the carrier planned, which only the timeline shows. */
export function toPlannedEvent(row: ApiPlannedTrackingEvent): PlannedEvent {
  return {
    ...(row.expires_at ? { expiresAt: row.expires_at } : {}),
    stage: row.stage,
    description: row.description,
    location: row.location ?? undefined,
    occurredAt: row.occurred_at,
    day: row.day ?? undefined,
    place: row.place ?? undefined,
  };
}

class ApiResponseError extends Error {
  constructor(message: string, readonly status: number, readonly retryAfterMs: number, readonly scope?: string) {
    super(message);
  }
}

/**
 * Adds a photo or PDF to an account's parcel, telling how much has gone. A refusal is a NoteFileError that says why,
 * with the server's status.
 */
export async function uploadPackageFile(
  packageId: string, file: Blob, name: string, auth: ApiAuth | undefined, onProgress?: (sent: number) => void, signal?: AbortSignal,
): Promise<ApiAddPackageFileResponse> {
  const path = `/api/packages/${encodeURIComponent(packageId)}/files`;
  const stop = AbortSignal.any([...(signal ? [signal] : []), ...(auth?.signal ? [auth.signal] : [])]);
  let answer = await sendFile(path, file, name, await auth?.getAccessToken() ?? null, onProgress, stop);
  // An expired token is renewed once, as for every other request.
  if (answer.status === 401 && auth) {
    const renewed = await auth.getAccessToken(true);
    if (renewed) answer = await sendFile(path, file, name, renewed, onProgress, stop);
  }
  if (answer.status === 401) {
    void auth?.onAuthenticationFailure?.().catch(() => undefined);
    throw new ApiAuthenticationError();
  }
  if (answer.status !== 200 && answer.status !== 201) {
    const code = (answer.body as { code?: unknown } | null)?.code;
    throw new NoteFileError(uploadProblem(answer.status, typeof code === 'string' ? code : undefined), file.size, answer.status);
  }
  return answer.body as ApiAddPackageFileResponse;
}

/** One upload's answer: its status, and its body when it was JSON. */
interface UploadAnswer { status: number; body: unknown }

/**
 * Sends a file's bytes as they are, with its type and name in the headers. XMLHttpRequest, unlike fetch, tells how much
 * of the body has gone.
 */
function sendFile(path: string, file: Blob, name: string, accessToken: string | null, onProgress?: (sent: number) => void, signal?: AbortSignal): Promise<UploadAnswer> {
  return new Promise((resolve, reject) => {
    if (signal?.aborted) { reject(signal.reason); return; }
    const request = new XMLHttpRequest();
    request.open('POST', path);
    request.timeout = 120_000;
    request.setRequestHeader('Content-Type', file.type);
    request.setRequestHeader('X-File-Name', encodeURIComponent(name));
    request.setRequestHeader('X-Requested-With', 'XMLHttpRequest');
    request.setRequestHeader('X-Peek-Inputs', CLIENT_INPUTS);
    if (accessToken) request.setRequestHeader('Authorization', `Bearer ${accessToken}`);
    const abort = () => request.abort();
    signal?.addEventListener('abort', abort, { once: true });
    const done = () => signal?.removeEventListener('abort', abort);
    request.upload.addEventListener('progress', (event) => onProgress?.(event.loaded));
    request.addEventListener('load', () => {
      done();
      let body: unknown = null;
      try { body = request.responseText ? JSON.parse(request.responseText) : null; } catch { /* Not JSON: the status says enough. */ }
      resolve({ status: request.status, body });
    });
    const failed = () => {
      done();
      reject(new NoteFileError(typeof navigator !== 'undefined' && navigator.onLine === false ? 'offline' : 'failed'));
    };
    request.addEventListener('error', failed);
    request.addEventListener('timeout', failed);
    request.addEventListener('abort', () => { done(); reject(signal?.reason ?? new DOMException('The upload was stopped', 'AbortError')); });
    request.send(file);
  });
}

function retryAfterMilliseconds(value: string | null): number {
  if (!value) return 0;
  const milliseconds = /^\d+$/.test(value)
    ? Number(value) * 1_000 : Date.parse(value) - Date.now();
  return Number.isFinite(milliseconds) ? Math.max(0, milliseconds) : 0;
}

async function apiRequest<T>(path: string, auth: ApiAuth | undefined, init?: RequestInit): Promise<T> {
  let response: Response;
  try {
    response = await authenticatedFetch(path, auth, init);
  } catch (error) {
    // No answer while the device is online, or no new token from a sign-in server that is down: the server is down.
    if (unanswered(error) && deviceOnline() && !init?.signal?.aborted) throw new ServerDownError(0, null, { cause: error });
    throw error;
  }
  const payload = (await response.json().catch(() => null)) as (
    T & { error?: string; packageId?: string; scope?: string }
  ) | null;
  if (!response.ok) {
    if (serverDown(response.status, payload)) {
      throw new ServerDownError(retryAfterMilliseconds(response.headers?.get('Retry-After') ?? null), announcedMaintenance(payload));
    }
    if (response.status === 409 && typeof payload?.packageId === 'string') {
      throw new ParcelAlreadyExistsError(
        payload.error ?? 'This tracking number is already in your delivery box',
        payload.packageId,
      );
    }
    throw new ApiResponseError(
      payload?.error ?? `Delivery service failed (${response.status})`,
      response.status,
      retryAfterMilliseconds(response.headers?.get('Retry-After') ?? null),
      typeof payload?.scope === 'string' ? payload.scope : undefined,
    );
  }
  if (payload === null) throw new Error('The delivery service returned an empty response');
  return payload;
}

export function createApiRepo(
  pollIntervalMs = 30_000,
  jobPollIntervalMs = 1_000,
  storage: Storage | null = browserStorage(),
  auth?: ApiAuth,
): ParcelRepo {
  let notifySubscriber: (() => void) | null = null;
  const monitoredJobIds = new Set<string>();
  let monitorTask: Promise<void> | null = null;
  let lifecycle = new AbortController();
  let revision = 0;
  let listSequence = 0;
  // An unchanged poll returns the same array, so the screen does not re-render.
  let latest: { serialized: string; parcels: ParcelWithEvents[] } | null = null;
  const cacheKey = auth ? `${API_CACHE_KEY}.${auth.userId}` : API_CACHE_KEY;

  async function request<T>(path: string, requestAuth: ApiAuth | undefined, init?: RequestInit): Promise<T> {
    // Capture this lifecycle: StrictMode may subscribe again before an old response arrives.
    const signal = AbortSignal.any([lifecycle.signal, ...(init?.signal ? [init.signal] : []),
      ...(auth?.signal ? [auth.signal] : [])]);
    signal.throwIfAborted();
    const value = await apiRequest<T>(path, requestAuth, { ...init, signal });
    signal.throwIfAborted();
    if (init?.method && init.method !== 'GET') {
      revision += 1;
      latest = null;
    }
    return value;
  }

  const wait = (milliseconds: number, signal: AbortSignal) => new Promise<void>((resolve, reject) => {
    signal.throwIfAborted();
    const onAbort = () => {
      window.clearTimeout(timer);
      reject(signal.reason);
    };
    const timer = window.setTimeout(() => {
      signal.removeEventListener('abort', onAbort);
      resolve();
    }, milliseconds);
    signal.addEventListener('abort', onAbort, { once: true });
  });

  async function waitForJobs(jobIds: string[]): Promise<void> {
    if (jobIds.length === 0) return;
    const pending = new Set(jobIds);
    const signal = lifecycle.signal;
    const deadline = Date.now() + 120_000;
    let interval = Math.max(1_000, jobPollIntervalMs);
    while (Date.now() < deadline) {
      signal.throwIfAborted();
      let retryAfter = 0;
      try {
        // One owner-scoped read for up to twenty active jobs, never one request
        // per parcel. Completed jobs leave the set immediately.
        const ids = [...pending].slice(0, 20);
        const { jobs } = await request<ApiSyncJobListResponse>(
          `/api/sync/jobs?ids=${ids.map(encodeURIComponent).join(',')}`,
          auth,
          { signal: AbortSignal.any([signal, AbortSignal.timeout(30_000)]) },
        );
        signal.throwIfAborted();
        if (!Array.isArray(jobs) || jobs.length !== ids.length || ids.some((id) => !jobs.some((job) => job.id === id))) {
          throw new Error('The delivery service returned incomplete job statuses');
        }
        const failed = jobs.find((job) => job.status === 'failed' || (job.result?.errors ?? 0) > 0);
        if (failed) {
          notifySubscriber?.();
          throw new Error(failed.error ?? 'Tracking refresh failed. Try again.');
        }
        for (const job of jobs) {
          if (job.status === 'succeeded') pending.delete(job.id);
        }
        if (pending.size === 0) return;
      } catch (error) {
        if (signal.aborted) throw error;
        if (error instanceof ServerDownError || (error instanceof ApiResponseError && error.status === 429)) {
          retryAfter = error.retryAfterMs;
        } else if (!(error instanceof TypeError) && !(error instanceof DOMException && error.name === 'TimeoutError')) {
          throw error;
        }
      }
      await wait(Math.min(Math.max(interval, retryAfter), Math.max(0, deadline - Date.now())), signal);
      interval = Math.min(interval * 1.5, 10_000);
    }
    throw new RefreshTimeoutError();
  }

  function monitorJobs(jobIds: string[]) {
    jobIds.forEach((jobId) => monitoredJobIds.add(jobId));
    if (monitorTask || monitoredJobIds.size === 0) return;
    monitorTask = (async () => {
      try {
        while (monitoredJobIds.size > 0) {
          const ids = [...monitoredJobIds];
          try {
            await waitForJobs(ids);
          } finally {
            ids.forEach((jobId) => monitoredJobIds.delete(jobId));
          }
        }
        notifySubscriber?.();
      } catch {
        // The normal collection poll remains the recovery path for a transient
        // status request failure or a job that outlives the foreground page.
      } finally {
        monitorTask = null;
        if (monitoredJobIds.size > 0) monitorJobs([]);
      }
    })();
  }

  async function list(): Promise<ParcelWithEvents[]> {
    const sequence = ++listSequence;
    const startedRevision = revision;
    const signal = lifecycle.signal;
    // A read while the page is out of sight, after a notification, does not count as the account's apps open.
    const payload = await request<ApiPackageListResponse>(
      `/api/packages?includeArchived=true${typeof document === 'undefined' || document.visibilityState === 'visible' ? '' : '&hidden=true'}`,
      auth,
    );
    signal.throwIfAborted();
    auth?.signal?.throwIfAborted();
    if (sequence !== listSequence || startedRevision !== revision) {
      throw new DOMException('A newer parcel request or mutation completed', 'AbortError');
    }
    const parcels = payload.packages.map(toParcel);
    const serialized = JSON.stringify(parcels);
    saveReadAt(storage, cacheKey);
    if (latest?.serialized === serialized) return latest.parcels;
    latest = { serialized, parcels };
    saveCachedParcels(storage, cacheKey, serialized);
    return parcels;
  }

  function rememberParcel(parcel: ParcelWithEvents): ParcelWithEvents {
    const cached = cachedParcels(storage, cacheKey);
    if (cached) saveCachedParcels(storage, cacheKey, [
      ...cached.filter((current) => current.id !== parcel.id), parcel,
    ]);
    return parcel;
  }

  /** Mutes one of a parcel's alerts, or brings it back. Each request names only the alert it changes. */
  async function setAlerts(id: string, body: ApiPackageNotificationRequest): Promise<ParcelWithEvents> {
    const row = await request<ApiPackageRow>(
      `/api/packages/${encodeURIComponent(id)}/notifications`,
      auth,
      {
        method: 'PATCH',
        body: JSON.stringify(body),
      },
    );
    return rememberParcel(toParcel(row));
  }

  return {
    mode: 'api',
    list,
    cachedList() {
      if (lifecycle.signal.aborted || auth?.signal?.aborted) return null;
      const serialized = readCache(storage, cacheKey);
      const parcels = parseCachedParcels(serialized);
      if (parcels && serialized !== null && revision === 0) latest = { serialized, parcels };
      return parcels;
    },
    cachedAt() {
      const value = readCache(storage, cacheKey + READ_AT_SUFFIX);
      return value && Number.isFinite(Date.parse(value)) ? value : null;
    },

    async add(input: NewParcelInput): Promise<ParcelWithEvents> {
      const carrier = input.carrier ?? detectCarrier(normalizeTrackingNumber(input.trackingNumber));
      const body: ApiCreatePackageRequest = {
        // A Tiui folio and a JRS Express reference keep their hyphens, which their trackers need.
        trackingNumber: carrierTrackingNumber(input.trackingNumber, carrier),
        label: input.label,
        carrier,
        trackingUrl: input.trackingUrl?.trim() || undefined,
        dpdPostcode: input.dpdPostcode?.trim() || undefined,
        dpdPostcodeCountry: input.dpdPostcode?.trim() ? input.dpdPostcodeCountry : undefined,
        phoneDigits: input.phoneDigits?.trim() || undefined,
        lookupCountryHint: input.lookupCountry ?? timeZoneRegion() ?? undefined,
      };
      const payload = await request<ApiCreatePackageResponse>('/api/packages', auth, {
        method: 'POST',
        body: JSON.stringify(body),
      });
      const parcel = rememberParcel(toParcel(payload.package));
      monitorJobs(payload.jobIds);
      return parcel;
    },

    async rename(id: string, label: string): Promise<ParcelWithEvents> {
      const body: ApiRenamePackageRequest = { label: label.trim() };
      const row = await request<ApiPackageRow>(
        `/api/packages/${encodeURIComponent(id)}`,
        auth,
        {
          method: 'PATCH',
          body: JSON.stringify(body),
        },
      );
      return rememberParcel(toParcel(row));
    },

    async setNote(id: string, note: string | null, options?: { keepalive?: boolean }): Promise<ParcelWithEvents> {
      const body: ApiRenamePackageRequest = { note: note?.trim() ? note : null };
      const row = await request<ApiPackageRow>(`/api/packages/${encodeURIComponent(id)}`, auth, {
        method: 'PATCH',
        body: JSON.stringify(body),
        ...(options?.keepalive ? { keepalive: true } : {}),
      });
      return rememberParcel(toParcel(row));
    },

    async addFile(id: string, file: Blob, name: string, onProgress?: (sent: number) => void, signal?: AbortSignal): Promise<ParcelWithEvents> {
      const stop = AbortSignal.any([lifecycle.signal, ...(signal ? [signal] : [])]);
      const added = await uploadPackageFile(id, file, name, auth, onProgress, stop);
      revision += 1;
      latest = null;
      return rememberParcel(toParcel(added.package));
    },

    async removeFile(id: string, fileId: string, options?: { keepalive?: boolean }): Promise<void> {
      try {
        await request<ApiOkResponse>(`/api/packages/${encodeURIComponent(id)}/files/${encodeURIComponent(fileId)}`, auth, {
          method: 'DELETE',
          ...(options?.keepalive ? { keepalive: true } : {}),
        });
      } catch (error) {
        // Already gone: what was asked is done.
        if (!(error instanceof ApiResponseError && error.status === 404)) throw error;
      }
      const cached = cachedParcels(storage, cacheKey);
      if (cached) saveCachedParcels(storage, cacheKey, cached.map((parcel) => parcel.id !== id ? parcel : withoutFile(parcel, fileId)));
    },

    async changeCarrier(id: string, input: ParcelCarrierInput): Promise<ParcelWithEvents> {
      const body: ApiChangePackageCarrierRequest = {
        carrier: input.carrier,
        providerPostcode: input.providerPostcode?.trim() || undefined,
        trackingUrl: input.trackingUrl?.trim() || undefined,
        dpdPostcode: input.dpdPostcode?.trim() || undefined,
        dpdPostcodeCountry: input.dpdPostcode?.trim() ? input.dpdPostcodeCountry : undefined,
        phoneDigits: input.phoneDigits?.trim() || undefined,
      };
      const payload = await request<ApiChangePackageCarrierResponse>(
        `/api/packages/${encodeURIComponent(id)}/carrier`,
        auth,
        {
          method: 'PATCH',
          body: JSON.stringify(body),
        },
      );
      monitorJobs(payload.jobIds);
      return rememberParcel(toParcel(payload.package));
    },

    async setNotificationsMuted(id: string, muted: boolean): Promise<ParcelWithEvents> {
      return setAlerts(id, { muted });
    },

    async setEmailMuted(id: string, emailMuted: boolean): Promise<ParcelWithEvents> {
      return setAlerts(id, { emailMuted });
    },

    async markSeen(id: string, newsAt?: string): Promise<string> {
      const body: ApiPackageSeenRequest = { news_at: newsAt };
      const { seen_at: seenAt } = await request<ApiPackageSeenResponse>(
        `/api/packages/${encodeURIComponent(id)}/seen`, auth, { method: 'POST', ...(newsAt ? { body: JSON.stringify(body) } : {}) });
      const cached = cachedParcels(storage, cacheKey);
      if (cached) saveCachedParcels(storage, cacheKey, cached.map((parcel) => parcel.id === id ? { ...parcel, seenAt } : parcel));
      return seenAt;
    },

    async remove(id: string): Promise<void> {
      await request<ApiOkResponse>(`/api/packages/${encodeURIComponent(id)}`, auth, {
        method: 'DELETE',
      });
      const cached = cachedParcels(storage, cacheKey);
      if (cached) saveCachedParcels(storage, cacheKey, cached.map((parcel) =>
        parcel.id === id ? { ...parcel, archivedAt: new Date().toISOString() } : parcel));
    },

    async restore(id: string): Promise<ParcelWithEvents> {
      const row = await request<ApiPackageRow>(
        `/api/packages/${encodeURIComponent(id)}/restore`,
        auth,
        { method: 'POST' },
      );
      return rememberParcel(toParcel(row));
    },

    async deletePermanently(id: string): Promise<void> {
      await request<ApiOkResponse>(
        `/api/packages/${encodeURIComponent(id)}/permanent`,
        auth,
        { method: 'DELETE' },
      );
      const cached = cachedParcels(storage, cacheKey);
      if (cached) {
        saveCachedParcels(
          storage,
          cacheKey,
          cached.filter((parcel) => parcel.id !== id),
        );
      }
    },

    async refresh(): Promise<ParcelWithEvents[]> {
      const queued = await request<ApiQueueResponse>('/api/sync', auth, { method: 'POST' });
      await waitForJobs(queued.jobIds);
      return list();
    },

    async refreshParcel(id: string): Promise<ParcelWithEvents> {
      const queued = await request<ApiQueueResponse>(
        `/api/packages/${encodeURIComponent(id)}/sync`,
        auth,
        { method: 'POST' },
      );
      await waitForJobs(queued.jobIds);
      const parcels = await list();
      const parcel = parcels.find((candidate) => candidate.id === id || candidate.originalParcelId === id);
      if (!parcel) throw new Error('Package not found after queueing its tracking check');
      return parcel;
    },

    subscribe(onChange: () => void | Promise<void>): () => void {
      if (lifecycle.signal.aborted) lifecycle = new AbortController();
      let pollInFlight = false;
      let pollAgain = false;
      let stopped = false;
      let timer: number | null = null;

      const schedule = () => {
        if (timer !== null) window.clearTimeout(timer);
        if (stopped) return;
        timer = window.setTimeout(() => {
          timer = null;
          if (document.visibilityState !== 'visible') {
            schedule();
            return;
          }
          void trigger();
        }, pollIntervalMs);
      };

      const trigger = async () => {
        if (pollInFlight || stopped) return;
        pollInFlight = true;
        try {
          await onChange();
        } finally {
          pollInFlight = false;
          if (pollAgain && !stopped) {
            pollAgain = false;
            void trigger();
          } else {
            schedule();
          }
        }
      };

      const onVisible = () => {
        if (document.visibilityState !== 'visible') return;
        if (timer !== null) window.clearTimeout(timer);
        timer = null;
        void trigger();
      };
      // A push notification means the server already has the new tracking
      // state: show it now rather than at the next poll. A request that was
      // already running may predate it, so it is followed by one more.
      const onServerUpdate = (event: MessageEvent) => {
        if (event.data?.type !== SERVER_UPDATE_MESSAGE) return;
        if (pollInFlight) pollAgain = true;
        else void trigger();
      };
      const worker = 'serviceWorker' in navigator ? navigator.serviceWorker : null;
      notifySubscriber = () => { void trigger(); };
      document.addEventListener('visibilitychange', onVisible);
      worker?.addEventListener('message', onServerUpdate);
      worker?.startMessages?.();
      schedule();
      return () => {
        stopped = true;
        lifecycle.abort();
        monitoredJobIds.clear();
        if (timer !== null) window.clearTimeout(timer);
        notifySubscriber = null;
        document.removeEventListener('visibilitychange', onVisible);
        worker?.removeEventListener('message', onServerUpdate);
      };
    },
  };
}
