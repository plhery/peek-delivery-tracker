import 'server-only';

import type { ObservedCoverage } from 'universal-parcel-scraper';
import type { AdapterHold, DpdSession } from 'universal-parcel-scraper/node';
import type { ApiGiftWords, ApiNativePushTransport, ApiTwinNumbers } from '../generated/apiContract';
import { ALL_NOTIFICATION_STAGES } from '../lib/notificationPresets';
import type { TrackingEvent } from '../types';
import { EMAIL_STAGES, type DeliveredTime, type EmailStage } from './email/types';
import type { AssistantClient } from './assistants/clients';
import type { SyncQueue } from './metrics';
import { SCOPES, type Scope } from './assistants/settings';
import { scanSource } from './scanRepeats';
import type { PeekAiCallRecord, PeekAiCase } from './peekAi';
import { DIRECT_HISTORY, heldRows } from './providerCopies';
import { providerLimit } from './providerPolicy';
import { withReviewDemo } from './reviewDemo';
import { keptStage } from './stageNotices';
import { ASSISTANT_NOTE_EMBED, NOTE_EMBED, withOwnerNote } from './parcelNotes';
import type { SyncReason } from './trackingAudit';
import { isRecord, type JsonObject } from './types';

const REQUEST_TIMEOUT_MS = 20_000;
/** Keeps a closing's filter list well inside a request line. */
const STATUS_OBSERVATION_KEYS_PER_REQUEST = 100;
const PACKAGE_COLUMNS = [
  'id',
  'tracking_number',
  'label',
  'carrier',
  'created_at',
  'expected_delivery',
  'last_status_text',
  'last_synced_at',
  'sync_status',
  'sync_error',
  'tracking_url',
  'dpd_postcode',
  'phone_digits',
  'carrier_data',
  'archived_at',
  'notifications_muted',
  'email_muted',
  'seen_at',
].join(',');
/**
 * The package shape the API returns once eventPlaces.ts has served it. A
 * scan's `point`, the carrier's own coordinates, gives its place, and its
 * provider_event_id tells Peek's own rows from the carrier's: the response
 * carries neither, nor when a scan was stored (`stored_at`), which dates the parcel's news (`news_at`), nor the time a
 * carrier re-dated it from (`retimed_from`), which tells its own re-dating from a row stored ahead (`shownEvents`).
 * The scraper's `recipient_action` and `tie_order` are served as they were stored. Peek AI reads a scan's status code
 * (`provider_code`) and when the carrier's system wrote it (`written_at`), which the response leaves out too (peekAi.ts).
 */
const PACKAGE_EVENTS = 'tracking_events(id,package_id,stage,description,location,occurred_at,stored_at:created_at,provider_event_id,point:raw_data->point,time:raw_data->>time,local_time:raw_data->>local_time,observed:raw_data->observed_without_provider_timestamp,universal_provider:raw_data->>universal_provider,recipient_action:raw_data->>recipient_action,tie_order:raw_data->tie_order,stage_source:raw_data->>stage_source,kept_stage:raw_data->kept_stage,retimed_from:raw_data->>retimed_from,provider_code:raw_data->>provider_code,written_at:raw_data->>written_at)';
const PACKAGE_SELECT = `${PACKAGE_COLUMNS},${PACKAGE_EVENTS}`;
/**
 * What a shared Passport is worked out from (`withSharedPassports` in friends.ts): the parcel's account, its scans
 * as `PACKAGE_SELECT` reads them and the columns serving them reads (eventPlaces.ts: the number, carrier and carrier
 * data, which tell the scans shown, their places and the rows shown under others). Its name, status, postcode,
 * archive date and the rest of what only the owner's apps show are left out: a shared Passport counts none of them.
 */
const PASSPORT_PACKAGE_SELECT = `id,user_id,tracking_number,carrier,carrier_data,${PACKAGE_EVENTS}`;
/**
 * The columns the schedule reads, `one_off` among them: a lookup without an account leaves the
 * schedule once nobody has answered for its number (unseenLookup.ts).
 */
const ACTIVE_PACKAGE_SELECT = 'id,user_id,one_off,tracking_number,label,carrier,current_stage,last_status_text,tracking_url,dpd_postcode,phone_digits,created_at,last_synced_at,sync_status,sync_error,expected_delivery,carrier_data,tracking_generation,sync_revision,archived_at';
/**
 * Where the sync loaders put each stored event's identity, instant, stage and
 * wording, so a reworded scan can update its row in place and another source's
 * copy of a stored scan is not stored again (see eventIdentity.ts). Only the
 * service client's sync loaders embed it, under this alias: no API response or
 * mapper reads it.
 */
export const STORED_EVENT_IDENTITIES = 'stored_event_identities';
const SYNC_EVENT_IDENTITIES = `${STORED_EVENT_IDENTITIES}:tracking_events(provider_event_id,occurred_at,stage,description,location,time:raw_data->>time,provider_code:raw_data->>provider_code,observed_without_provider_timestamp:raw_data->observed_without_provider_timestamp,universal_provider:raw_data->>universal_provider,local_time:raw_data->>local_time,time_zone:raw_data->>time_zone,time_zone_basis:raw_data->>time_zone_basis,time_provenance:raw_data->>time_provenance,provider_time_iso:raw_data->>provider_time_iso,retimed_from:raw_data->>retimed_from,stage_source:raw_data->>stage_source,summary_snapshot:raw_data->summary_snapshot,provider_leg:raw_data->>provider_leg,created_at)`;
/**
 * What the sync loaders read besides the parcel's columns: the owner's app language (a computed
 * field, migration 20261025180000), in which a carrier that words its scans in several languages
 * answers, and the stored event identities.
 */
const SYNC_EXTRAS = `owner_locale,${SYNC_EVENT_IDENTITIES}`;

/** Where each channel records that a scan was announced, or handled without an alert. */
const SCAN_RECEIPT_TABLES = ['push_deliveries', 'native_push_deliveries', 'parcel_link_alert_deliveries', 'live_activity_event_deliveries'];

export class SupabaseError extends Error {
  /**
   * By name: the build's bundles each hold their own copy of this class, and
   * the routes share the process's database client, which instrumentation's
   * bundle usually builds. The errors it throws are that copy's.
   */
  static [Symbol.hasInstance](value: unknown): value is SupabaseError {
    return value instanceof Error && value.name === 'SupabaseError';
  }

  /** The call that failed, as `databaseCall` names it. */
  readonly operation?: string;

  constructor(
    message: string,
    readonly status?: number,
    readonly code?: string,
    options?: ErrorOptions & { operation?: string },
  ) {
    super(message, options);
    this.name = 'SupabaseError';
    if (options?.operation) this.operation = options.operation;
  }
}

/**
 * A database call as error reports name it: the method and the path, without its query and with
 * a segment of six characters or more holding a digit, such as a user id, read as `:id`.
 */
export function databaseCall(method: string, path: string): string {
  return `${method} ${path.split(/[?#]/, 1)[0]!.split('/')
    .map((segment) => (segment.length >= 6 && /\d/.test(segment) ? ':id' : segment)).join('/')}`;
}

/**
 * A worker no longer holds the job it works on: its lease expired, another
 * worker took it, or the job went with its parcel. The name stays "Error",
 * which error reports are grouped by.
 */
export class SyncJobLeaseLost extends Error {
  constructor() {
    super('Synchronization job lease was lost');
  }
}

/** What `claim_sync_job` writes on a job it fails because its worker stopped three times. */
export const LOST_SYNC_JOB_ERROR = 'The sync worker stopped before completing this job.';

export interface LostScheduledRun {
  id: string;
  attempts: number;
  checkIn: { checkInId: string; monitorSlug: string; startedAt: number };
}

function query(
  entries: ReadonlyArray<readonly [string, string]> | Record<string, string>,
): string {
  if (Array.isArray(entries)) {
    return new URLSearchParams(
      entries.map(([key, value]) => [key, value]),
    ).toString();
  }
  return new URLSearchParams(entries as Record<string, string>).toString();
}

/**
 * What replay reads of a support case and closes it on: every check of its number counts and stamps it, and may
 * change the rest.
 */
export const SUPPORT_CASE_REVISION = ['configured_carrier', 'direct_verified_carrier', 'last_outcome', 'reasons', 'last_seen', 'seen_count'] as const;

/** A PostgREST `in` list of text values, each quoted with its quotes and backslashes escaped: a comma stays within it. */
function quotedList(values: readonly string[]): string {
  return `in.(${values.map((value) => `"${value.replace(/["\\]/g, '\\$&')}"`).join(',')})`;
}

function rows(value: unknown): JsonObject[] {
  return Array.isArray(value) ? value.filter(isRecord) : [];
}

const INBOUND_MAIL_RESULTS = new Set(['added', 'known', 'named', 'delivered', 'none', 'limited', 'full', 'confirmation', 'ignored', 'waiting']);

function inboundMailbox(value: unknown): InboundMailbox | null {
  const row = Array.isArray(value) ? value[0] : value;
  if (!isRecord(row) || typeof row.token !== 'string' || typeof row.created_at !== 'string') return null;
  const text = (field: unknown) => (typeof field === 'string' ? field : null);
  const confirmedAt = text(row.confirmation_at);
  return {
    token: row.token,
    createdAt: row.created_at,
    usedToday: Number(row.used_today ?? 0),
    lastMailAt: text(row.last_mail_at),
    lastResult: INBOUND_MAIL_RESULTS.has(String(row.last_result)) ? row.last_result as InboundMailResult : null,
    lastAdded: typeof row.last_added === 'number' ? row.last_added : null,
    recent: rows(row.recent).flatMap((entry) => (typeof entry.at === 'string' && INBOUND_MAIL_RESULTS.has(String(entry.result))
      ? [{ at: entry.at, sender: text(entry.sender) ?? '', result: entry.result as InboundMailResult, added: Number(entry.added) || 0 }]
      : [])),
    confirmation: confirmedAt
      ? { code: text(row.confirmation_code), link: text(row.confirmation_link), receivedAt: confirmedAt }
      : null,
    notify: row.notify_added !== false,
  };
}

function forgotten(value: unknown): { links: number; packages: number } {
  const counts = isRecord(value) ? value : {};
  return { links: Number(counts.links ?? 0), packages: Number(counts.packages ?? 0) };
}

/** A link and its whole package row, as the database returns them to the server. */
export interface StoredParcelLink { link: JsonObject; package: JsonObject }
/**
 * Why someone waits for an open parcel (listWaitingPackages): it is on screen, an alert is on, or its link or
 * account's apps were only read lately.
 */
export type Waiting = 'on_screen' | 'alert' | 'opened';

/** One use to count: against the client, its network when it has one, and everyone together. */
export interface PublicAllowanceClaim {
  bucket: string;
  limit: number;
  overall: { bucket: 'global' | 'detection'; limit: number };
  network?: { bucket: string; limit: number } | null;
}

export interface PublicAllowance {
  allowed: boolean;
  scope: 'bucket' | 'network' | 'global' | null;
  overallUsed: number;
  /** The lookups the claim's IPv6 /48 made today, this one included if it counted, where it has one. */
  networkUsed?: number;
}

/** A claim by an account: `overall` is every account's this hour, `account` the account's today. */
export interface AccountAllowance {
  allowed: boolean;
  scope: 'overall' | 'account' | null;
  overallUsed: number;
}

export interface PublicUsageStats { buckets: number; p50: number; p90: number; max: number }

function usageStats(value: unknown): PublicUsageStats {
  const stats = isRecord(value) ? value : {};
  return {
    buckets: Number(stats.buckets ?? 0),
    p50: Number(stats.p50 ?? 0),
    p90: Number(stats.p90 ?? 0),
    max: Number(stats.max ?? 0),
  };
}

/** The live link an account shares a parcel through. */
export interface ParcelShare { id: string; showNumber: boolean; gift: boolean; createdAt: string; giftWords?: ApiGiftWords }

function parcelShare(value: unknown): ParcelShare {
  if (!isRecord(value) || typeof value.id !== 'string' || typeof value.created_at !== 'string') {
    throw new SupabaseError('Supabase did not return the shared link');
  }
  const words = value.gift_words;
  return {
    id: value.id, showNumber: value.show_number === true, gift: value.gift === true, createdAt: value.created_at,
    ...(isRecord(words) ? { giftWords: {
      name: typeof words.name === 'string' ? words.name : null,
      note: typeof words.note === 'string' ? words.note : null,
      from: typeof words.from === 'string' ? words.from : null,
      ...(words.early === true ? { early: true } : {}),
    } } : {}),
  };
}

/** A scan claimed for an email, delivered or ready to collect: what must be sent now. */
export interface DeliveryEmailClaim {
  /** The claim, to end it with. */
  id: string;
  packageId: string;
  userId: string;
  eventId: string;
  /** What the email tells. */
  stage: EmailStage;
  /** The account's time zone, which the scan's time is told in. */
  timezone: string;
  /** How the account's app shows a time and a distance; null until an app has sent them. */
  clock: '24' | '12' | null;
  distance: 'km' | 'mi' | null;
  /** What the scan knows of its time, as the notification queues read it. */
  deliveredTime: DeliveredTime;
}

/** How a claimed delivery email ended. A failed one is claimed again while it is fresh and attempts are left. */
/** The service that reaches a phone: APNs, FCM, UnifiedPush or the inbox. */
type NativePushTransport = ApiNativePushTransport;

/** Where UnifiedPush sends a phone's notices: its distributor's endpoint and the Web Push keys the app made for it. */
export interface WebPushKeys {
  endpoint: string;
  p256dh: string;
  auth: string;
}

export type DeliveryEmailOutcome = 'sent' | 'failed' | 'skipped';

/** One of a package's newest scans, with its words and its source (`scanSource`). */
/** A stored scan as push reads it; `held`: a universal row its carrier's answer took over (`heldRows`), that carrier's scan. */
export type RecentScan = Pick<TrackingEvent, 'id' | 'stage' | 'location' | 'occurredAt' | 'description'>
  & { source: string; stageSource?: string; kept?: boolean; storedAt?: string; held?: boolean };

/** A connected assistant or personal token of an account, as its owner sees it in Settings. */
export interface AssistantGrant {
  id: string;
  kind: 'oauth' | 'personal';
  name: string;
  /** Where the assistant's sign-in returned: the site that holds its tokens. */
  redirectHost: string | null;
  scopes: Scope[];
  createdAt: string;
  lastUsedAt: string | null;
}

/** What a redeemed code or refresh token issued, and for whom. */
export interface AssistantIssue {
  userId: string;
  grantId: string;
  scopes: Scope[];
}

/** Whose an access or personal token is, and what it may do. */
export interface AssistantTokenOwner extends AssistantIssue {
  kind: 'access' | 'personal';
  /** The OAuth client, or null for a personal token. */
  clientId: string | null;
  /** Seconds since the epoch; null for a personal token, which lasts until revoked. */
  expiresAt: number | null;
}

function grantedScopes(value: unknown): Scope[] {
  return Array.isArray(value) ? SCOPES.filter((scope) => value.includes(scope)) : [];
}

function isoTime(value: unknown): string | null {
  if (typeof value !== 'string') return null;
  const time = new Date(value);
  return Number.isFinite(time.getTime()) ? time.toISOString() : null;
}

function assistantGrant(value: unknown): AssistantGrant | null {
  if (!isRecord(value) || typeof value.id !== 'string' || typeof value.name !== 'string') return null;
  if (value.kind !== 'oauth' && value.kind !== 'personal') return null;
  return {
    id: value.id,
    kind: value.kind,
    name: value.name,
    redirectHost: typeof value.redirect_host === 'string' ? value.redirect_host : null,
    scopes: grantedScopes(value.scopes),
    createdAt: isoTime(value.created_at) ?? new Date(0).toISOString(),
    lastUsedAt: isoTime(value.last_used_at),
  };
}

function assistantIssue(value: unknown): AssistantIssue | null {
  if (!isRecord(value) || typeof value.user_id !== 'string' || typeof value.grant_id !== 'string') return null;
  return { userId: value.user_id, grantId: value.grant_id, scopes: grantedScopes(value.scopes) };
}

/** An account's forwarding address for adding parcels by email, and what its last email did. */
export interface InboundMailbox {
  token: string;
  createdAt: string;
  /** Emails read today (UTC), counted against the daily allowance. */
  usedToday: number;
  lastMailAt: string | null;
  lastResult: InboundMailResult | null;
  lastAdded: number | null;
  /** What the last emails did, newest first, twenty at most. */
  recent: InboundMailEntry[];
  /** What Gmail sent to confirm forwarding, for the owner to finish its setup. */
  confirmation: { code: string | null; link: string | null; receivedAt: string } | null;
  /** Whether each parcel an email adds is notified to the account's browsers and phones. */
  notify: boolean;
}

/** `waiting`: the email's shop link named no parcel yet, and the account waits for the shop (shop_waits). */
export type InboundMailResult = 'added' | 'known' | 'named' | 'delivered' | 'none' | 'limited' | 'full' | 'confirmation' | 'ignored' | 'waiting';

/** One email to a forwarding address: when it came, who sent it and what it did. */
export interface InboundMailEntry {
  at: string;
  /** The shop or sender's name; empty when the email was not read. */
  sender: string;
  result: InboundMailResult;
  added: number;
}

/** Whom an email's address names, and whether its allowances let it through. */
export interface InboundMailClaim {
  allowed: boolean;
  scope: 'overall' | 'account' | null;
}

/** What the Auth server knows of an account that an email to it needs. */
export interface AuthAccount {
  email: string | null;
  emailConfirmed: boolean;
  /** The language the account last used, as its clients store it. */
  locale: string | null;
}

/** A parcel that is not the caller's answers like one that does not exist. */
function ownedPackageError(error: unknown): never {
  if (error instanceof SupabaseError && error.code === 'P0002') throw new SupabaseError('Package not found', 404, error.code, { cause: error });
  throw error;
}

export class SupabaseClient {
  readonly url: string;

  constructor(
    url: string,
    readonly apiKey: string,
    readonly accessToken = apiKey,
    readonly timeoutMs = REQUEST_TIMEOUT_MS,
  ) {
    this.url = url.replace(/\/+$/, '');
  }

  async request<T = unknown>(
    path: string,
    options: {
      method?: string;
      body?: unknown;
      prefer?: string;
      timeoutMs?: number;
      signal?: AbortSignal;
    } = {},
  ): Promise<T | null> {
    const method = options.method ?? 'GET';
    const headers = new Headers({
      Accept: 'application/json',
      apikey: this.apiKey,
      Authorization: `Bearer ${this.accessToken}`,
    });
    let body: string | undefined;
    if (options.body !== undefined) {
      body = JSON.stringify(options.body);
      headers.set('Content-Type', 'application/json');
    }
    if (options.prefer) headers.set('Prefer', options.prefer);

    let response: Response;
    let text: string;
    try {
      response = await fetch(`${this.url}${path}`, {
        method,
        headers,
        body,
        cache: 'no-store',
        redirect: 'error',
        signal: options.signal
          ? AbortSignal.any([options.signal, AbortSignal.timeout(options.timeoutMs ?? this.timeoutMs)])
          : AbortSignal.timeout(options.timeoutMs ?? this.timeoutMs),
      });
      text = await response.text();
    } catch (error) {
      // No answer, or one cut off before its end. The code tells it from a refusal.
      throw new SupabaseError('The delivery database is unreachable', undefined, 'unreachable', {
        cause: error, operation: databaseCall(method, path),
      });
    }

    let payload: unknown = null;
    if (text) {
      try {
        payload = JSON.parse(text);
      } catch (error) {
        if (response.ok) {
          throw new SupabaseError('The delivery database returned invalid JSON', response.status, undefined, {
            cause: error, operation: databaseCall(method, path),
          });
        }
      }
    }

    if (!response.ok) {
      const code = isRecord(payload) && typeof payload.code === 'string' ? payload.code : undefined;
      throw new SupabaseError(
        `Supabase ${method} request failed (${response.status})`,
        response.status,
        code,
        { operation: databaseCall(method, path) },
      );
    }
    return payload as T | null;
  }

  /** What a read of whole parcels selects: the API's package shape. The owner's own client adds its note. */
  protected packageSelect(): string {
    return PACKAGE_SELECT;
  }

  async listPackages(includeArchived = false): Promise<JsonObject[]> {
    const params: Array<[string, string]> = [
      ['select', this.packageSelect()],
      ['order', 'created_at.desc'],
    ];
    if (!includeArchived) params.push(['archived_at', 'is.null']);
    return rows(await this.request(`/rest/v1/packages?${query(params)}`));
  }

  async getPackage(packageId: string): Promise<JsonObject | null> {
    const params: Array<[string, string]> = [
      ['select', this.packageSelect()],
      ['id', `eq.${packageId}`],
      ['limit', '1'],
    ];
    const parcel = rows(await this.request(`/rest/v1/packages?${query(params)}`))[0];
    if (parcel) return parcel;
    return rows(await this.request(`/rest/v1/packages?${query({
      select: this.packageSelect(), 'carrier_data->>original_package_id': `eq.${packageId}`, limit: '1',
    })}`))[0] ?? null;
  }

  async getPackageByTrackingNumber(trackingNumber: string): Promise<JsonObject | null> {
    const params: Array<[string, string]> = [
      ['select', this.packageSelect()],
      ['tracking_number', `eq.${trackingNumber}`],
      ['limit', '1'],
    ];
    const parcel = rows(await this.request(`/rest/v1/packages?${query(params)}`))[0];
    if (parcel) return parcel;
    return rows(await this.request(`/rest/v1/packages?${query({
      select: this.packageSelect(), 'carrier_data->>original_tracking_number': `eq.${trackingNumber}`, limit: '1',
    })}`))[0] ?? null;
  }

  async archivePackage(packageId: string): Promise<void> {
    await this.updatePackage(packageId, { archived_at: new Date().toISOString() });
  }

  async restorePackage(packageId: string): Promise<void> {
    await this.updatePackage(packageId, { archived_at: null });
  }

  async deleteArchivedPackage(packageId: string): Promise<boolean> {
    const params = query({ id: `eq.${packageId}`, archived_at: 'not.is.null' });
    const deleted = rows(await this.request(`/rest/v1/packages?${params}`, {
      method: 'DELETE',
      prefer: 'return=representation',
    }));
    return deleted.length === 1;
  }

  async deletePackage(packageId: string): Promise<boolean> {
    const deleted = rows(await this.request(`/rest/v1/packages?${query({ id: `eq.${packageId}` })}`, {
      method: 'DELETE',
      prefer: 'return=representation',
    }));
    return deleted.length === 1;
  }

  async archiveDeliveredBefore(cutoff: Date): Promise<number> {
    if (!Number.isFinite(cutoff.getTime())) throw new TypeError('Archive cutoff must be valid');
    const params = query([
      ['archived_at', 'is.null'],
      ['current_stage', 'eq.delivered'],
      ['last_synced_at', `lt.${cutoff.toISOString()}`],
      // A one-off parcel has no list to leave: it is forgotten with its links.
      ['one_off', 'is.false'],
    ]);
    const archived = rows(await this.request(`/rest/v1/packages?${params}`, {
      method: 'PATCH',
      body: { archived_at: new Date().toISOString() },
      prefer: 'return=representation',
    }));
    return archived.length;
  }

  /** The open parcels on the list, archived ones left out: what refreshing every parcel checks. */
  async listActivePackages(): Promise<JsonObject[]> {
    return await this.activePackages(ACTIVE_PACKAGE_SELECT, [['archived_at', 'is.null']]);
  }

  /**
   * The parcels a check can still change: not delivered or returned, unless the delivery is
   * only announced, or only a provider reported it and the carrier may yet dispute it, or the return
   * is held while the carrier's later scans carry the parcel back (trackingSync.ts).
   */
  protected async activePackages(
    select: string,
    filters: ReadonlyArray<readonly [string, string]> = [],
  ): Promise<JsonObject[]> {
    return await this.everyCandidate('/rest/v1/packages', [
      ['select', select],
      ['or', `(current_stage.not.in.(delivered,returned),last_status_text.eq.TO_BE_DELIVERED,carrier_data->>unconfirmed_delivery_until.gt."${new Date().toISOString()}",carrier_data->>return_held.eq.true)`],
      ...filters,
    ]);
  }

  /**
   * Every row a read of parcels answers, the least recently checked first. They come in pages
   * by id until one adds none: the database's API ends every answer at its row limit without
   * saying so, and a parcel left past it would never be checked.
   */
  protected async everyCandidate(
    path: string,
    params: ReadonlyArray<readonly [string, string]>,
    options: { method?: string; body?: unknown } = {},
  ): Promise<JsonObject[]> {
    const found = new Map<string, JsonObject>();
    for (let after: string | undefined; ;) {
      const page = rows(await this.request(`${path}?${query([...params, ['order', 'id.asc'], ['limit', String(CANDIDATE_PAGE)],
        ...(after === undefined ? [] : [['id', `gt.${after}`] as const])])}`, options))
        .filter((row) => typeof row.id === 'string' && !found.has(row.id));
      if (page.length === 0) return leastRecentlyChecked([...found.values()]);
      for (const row of page) found.set(String(row.id), row);
      after = String(page.at(-1)!.id);
    }
  }

  async updatePackage(packageId: string, values: JsonObject): Promise<void> {
    await this.request(`/rest/v1/packages?${query({ id: `eq.${packageId}` })}`, {
      method: 'PATCH',
      body: values,
      prefer: 'return=minimal',
    });
  }

  async acquireTrackingProvider(provider: string, background = false): Promise<{ token: string | null; retry_at: string; held?: boolean; limited?: true }> {
    const limit = providerLimit(provider, background);
    const result = await this.request('/rest/v1/rpc/acquire_tracking_provider', {
      method: 'POST', body: { p_provider: provider, ...(limit ? { p_hourly_limit: limit.limit, p_reserve: limit.reserve } : {}) },
    });
    if (!isRecord(result) || !(typeof result.token === 'string' || result.token === null)
      || typeof result.retry_at !== 'string') throw new TypeError('Invalid provider health response');
    return { token: result.token, retry_at: result.retry_at, ...(result.held === true ? { held: true } : {}),
      ...(result.limited === true ? { limited: true } : {}) };
  }

  /**
   * What each universal provider had for each carrier's parcels over the last two weeks (`provider_coverage`): by
   * carrier and provider, the parcels it returned history for and those it had none for while another provider did.
   */
  async providerCoverage(): Promise<Map<string, Record<string, ObservedCoverage>>> {
    const result = await this.request('/rest/v1/rpc/provider_coverage', { method: 'POST', body: { p_days: 14 } });
    if (!isRecord(result)) throw new TypeError('Invalid provider coverage response');
    const count = (value: unknown) => typeof value === 'number' && Number.isSafeInteger(value) && value >= 0 ? value : 0;
    return new Map(Object.entries(result).flatMap(([carrier, providers]) => isRecord(providers)
      ? [[carrier, Object.fromEntries(Object.entries(providers).flatMap(([provider, counted]) => isRecord(counted)
        ? [[provider, { history: count(counted.history), missed: count(counted.missed) }]] : []))] as const] : []));
  }

  async finishTrackingProvider(provider: string, token: string, kind: string | null, retryAfterMs: number, durationMs: number): Promise<void> {
    await this.request('/rest/v1/rpc/finish_tracking_provider', { method: 'POST', body: {
      p_provider: provider, p_token: token, p_kind: kind,
      p_retry_ms: Math.ceil(Math.max(0, retryAfterMs)), p_duration_ms: Math.round(durationMs),
    } });
  }

  async insertEvents(events: JsonObject[]): Promise<void> {
    if (events.length === 0) return;
    await this.request(`/rest/v1/tracking_events?${query({
      on_conflict: 'package_id,provider_event_id',
    })}`, {
      method: 'POST',
      body: events,
      prefer: 'resolution=merge-duplicates,return=minimal',
    });
  }

  async deleteEventsByDescriptions(packageId: string, descriptions: Set<string>): Promise<void> {
    if (descriptions.size === 0) return;
    const params = query([
      ['package_id', `eq.${packageId}`],
      ['description', `in.(${[...descriptions].sort().join(',')})`],
    ]);
    await this.request(`/rest/v1/tracking_events?${params}`, {
      method: 'DELETE',
      prefer: 'return=minimal',
    });
  }

  async upsertPushSubscription(
    userId: string,
    endpoint: string,
    p256dh: string,
    auth: string,
    userAgent?: string | null,
    locale?: string,
  ): Promise<JsonObject> {
    const now = new Date().toISOString();
    const result = rows(await this.request(`/rest/v1/push_subscriptions?${query({
      on_conflict: 'endpoint',
    })}`, {
      method: 'POST',
      body: {
        user_id: userId,
        endpoint,
        p256dh,
        auth,
        user_agent: userAgent ?? null,
        ...(locale ? { locale } : {}),
        subscribed_at: now,
        disabled_at: null,
        last_error: null,
        updated_at: now,
      },
      prefer: 'resolution=merge-duplicates,return=representation',
    }));
    if (!result[0]) throw new SupabaseError('Supabase did not return the push subscription');
    return result[0];
  }

  async hasActivePushSubscription(userId: string, endpoint: string): Promise<boolean> {
    const params = query({
      select: 'id', user_id: `eq.${userId}`, endpoint: `eq.${endpoint}`, disabled_at: 'is.null', limit: '1',
    });
    return rows(await this.request(`/rest/v1/push_subscriptions?${params}`)).length > 0;
  }

  async deletePushSubscription(userId: string, endpoint: string): Promise<void> {
    const params = query({ user_id: `eq.${userId}`, endpoint: `eq.${endpoint}` });
    await this.request(`/rest/v1/push_subscriptions?${params}`, {
      method: 'DELETE',
      prefer: 'return=minimal',
    });
  }

  async updatePushSubscriptionLocale(userId: string, endpoint: string, locale: string): Promise<void> {
    const params = query({ user_id: `eq.${userId}`, endpoint: `eq.${endpoint}`, disabled_at: 'is.null' });
    await this.request(`/rest/v1/push_subscriptions?${params}`, {
      method: 'PATCH', body: { locale }, prefer: 'return=minimal',
    });
  }

  /**
   * Stores a phone for the account's notifications. A UnifiedPush device brings its endpoint and Web Push keys
   * (`webPush`); a UnifiedPush or inbox device that switches to the other transport keeps its row, and loses them.
   */
  async upsertNativePushDevice(
    userId: string,
    token: string,
    environment: string,
    locale: string,
    installationId?: string | null,
    deviceName?: string | null,
    transport: NativePushTransport = 'apns',
    webPush: WebPushKeys | null = null,
  ): Promise<JsonObject> {
    const now = new Date().toISOString();
    const result = rows(await this.request(`/rest/v1/native_push_devices?${query({
      on_conflict: 'environment,token',
    })}`, {
      method: 'POST',
      body: {
        user_id: userId,
        token,
        environment,
        transport,
        ...transport === 'unifiedpush' || transport === 'inbox'
          ? { endpoint: webPush?.endpoint ?? null, p256dh: webPush?.p256dh ?? null, auth: webPush?.auth ?? null }
          : {},
        locale,
        installation_id: installationId ?? null,
        device_name: deviceName ?? null,
        subscribed_at: now,
        disabled_at: null,
        last_error: null,
        retry_failures: 0,
        retry_at: null,
        updated_at: now,
      },
      prefer: 'resolution=merge-duplicates,return=representation',
    }));
    if (!result[0]) throw new SupabaseError('Supabase did not return the native push device');
    return result[0];
  }

  async deleteNativePushDevice(userId: string, token: string, transport: NativePushTransport = 'apns'): Promise<void> {
    const params = query({ user_id: `eq.${userId}`, token: `eq.${token}`, transport: `eq.${transport}` });
    await this.request(`/rest/v1/native_push_devices?${params}`, {
      method: 'DELETE',
      prefer: 'return=minimal',
    });
  }

  async upsertLiveActivityDevice(
    userId: string,
    installationId: string,
    token: string,
    environment: string,
    locale: string,
    sessionId: string,
    revocationHash: string | null,
  ): Promise<JsonObject> {
    const now = new Date().toISOString();
    const result = rows(await this.request(`/rest/v1/live_activity_devices?${query({
      on_conflict: 'installation_id',
    })}`, {
      method: 'POST',
      body: {
        user_id: userId,
        installation_id: installationId,
        session_id: sessionId,
        revocation_hash: revocationHash,
        token,
        environment,
        locale,
        disabled_at: null,
        last_error: null,
        updated_at: now,
      },
      prefer: 'resolution=merge-duplicates,return=representation',
    }));
    if (!result[0]) throw new SupabaseError('Supabase did not return the Live Activity device');
    return result[0];
  }

  async revokeLiveActivityDevice(installationId: string, revocationHash: string): Promise<void> {
    await this.request('/rest/v1/rpc/revoke_live_activity_device', {
      method: 'POST',
      body: { p_installation_id: installationId, p_revocation_hash: revocationHash },
    });
  }

  async getLiveActivityDevice(
    userId: string,
    installationId: string,
  ): Promise<JsonObject | null> {
    const params = query({
      user_id: `eq.${userId}`,
      installation_id: `eq.${installationId}`,
      disabled_at: 'is.null',
      limit: '1',
    });
    return rows(await this.request(`/rest/v1/live_activity_devices?${params}`))[0] ?? null;
  }

  async deleteLiveActivityDevice(userId: string, installationId: string): Promise<void> {
    const params = query({
      user_id: `eq.${userId}`,
      installation_id: `eq.${installationId}`,
    });
    await this.request(`/rest/v1/live_activity_devices?${params}`, {
      method: 'DELETE',
      prefer: 'return=minimal',
    });
  }

  async upsertLiveActivityUpdateToken(values: {
    deviceId: string;
    packageId: string;
    activityId: string;
    token: string;
    environment: string;
    locale: string;
  }): Promise<JsonObject> {
    const starts = rows(await this.request(`/rest/v1/live_activity_event_deliveries?${query({
      device_id: `eq.${values.deviceId}`,
      package_id: `eq.${values.packageId}`,
      delivery_kind: 'eq.start',
      select: 'event_created_at',
      order: 'event_created_at.desc',
      limit: '1',
    })}`));
    let startedAt = typeof starts[0]?.event_created_at === 'string'
      ? starts[0].event_created_at
      : null;
    if (!startedAt) {
      const events = rows(await this.request(`/rest/v1/tracking_events?${query({
        package_id: `eq.${values.packageId}`,
        stage: 'eq.out_for_delivery',
        select: 'created_at',
        order: 'created_at.desc',
        limit: '1',
      })}`));
      startedAt = typeof events[0]?.created_at === 'string'
        ? events[0].created_at
        : new Date().toISOString();
    }
    const result = rows(await this.request(`/rest/v1/live_activity_update_tokens?${query({
      on_conflict: 'device_id,package_id',
    })}`, {
      method: 'POST',
      body: {
        device_id: values.deviceId,
        package_id: values.packageId,
        activity_id: values.activityId,
        token: values.token,
        environment: values.environment,
        locale: values.locale,
        started_at: startedAt,
        last_error: null,
        updated_at: new Date().toISOString(),
      },
      prefer: 'resolution=merge-duplicates,return=representation',
    }));
    if (!result[0]) throw new SupabaseError('Supabase did not return the Live Activity update token');
    return result[0];
  }

  async deleteLiveActivityUpdateToken(
    userId: string,
    installationId: string,
    activityId: string,
  ): Promise<void> {
    const device = await this.getLiveActivityDevice(userId, installationId);
    if (typeof device?.id !== 'string') return;
    const params = query({
      device_id: `eq.${device.id}`,
      activity_id: `eq.${activityId}`,
    });
    await this.request(`/rest/v1/live_activity_update_tokens?${params}`, {
      method: 'DELETE',
      prefer: 'return=minimal',
    });
  }

  /**
   * Each package's newest scans, enough to tell a generated track by (`generatedScans`), a carrier's
   * repeats of a scan (`repeatedScans`), its notices in the stage of the scan before them (`stageNotices`)
   * and its scans stored after a newer one (`storedLate`).
   */
  async recentScans(packageIds: string[]): Promise<Map<string, RecentScan[]>> {
    const ids = [...new Set(packageIds)];
    const [held, found] = await Promise.all([this.heldScans(ids), Promise.all(ids.map(async (packageId) => [packageId, rows(await this.request(
      `/rest/v1/tracking_events?${query([
        ['package_id', `eq.${packageId}`],
        ['select', 'id,stage,location,occurred_at,created_at,description,provider_event_id,universal_provider:raw_data->>universal_provider,stage_source:raw_data->>stage_source,kept_stage:raw_data->kept_stage'],
        ['order', 'occurred_at.desc'],
        ['limit', '60'],
      ])}`))] as const))]);
    return new Map(found.map(([packageId, events]) => [packageId, events.flatMap(({ id, stage, location, occurred_at, created_at, description, provider_event_id, universal_provider, stage_source, kept_stage }) =>
      typeof id === 'string' && typeof occurred_at === 'string' ? [{
        id, stage: stage as TrackingEvent['stage'], occurredAt: occurred_at, ...(typeof location === 'string' ? { location } : {}),
        ...(typeof created_at === 'string' ? { storedAt: created_at } : {}),
        description: typeof description === 'string' ? description.trim() : '', source: scanSource(provider_event_id, universal_provider),
        ...(typeof stage_source === 'string' ? { stageSource: stage_source } : {}),
        ...(keptStage(kept_stage) ? { kept: true } : {}),
        ...(typeof provider_event_id === 'string' && held.get(packageId)?.has(provider_event_id) ? { held: true } : {}),
      }] : [])]));
  }

  /** The universal rows each package's carrier answer holds (`heldRows`), for the packages that hold any. */
  private async heldScans(packageIds: string[]): Promise<Map<string, Set<string>>> {
    const pages = Array.from({ length: Math.ceil(packageIds.length / 100) }, (_, page) => packageIds.slice(page * 100, page * 100 + 100));
    const found = await Promise.all(pages.map(async (page) => rows(await this.request(`/rest/v1/packages?${query([
      ['id', `in.(${page.join(',')})`],
      ['carrier_data->direct_history->>held', 'not.is.null'],
      ['select', 'id,direct_history:carrier_data->direct_history'],
    ])}`))));
    return new Map(found.flat().flatMap(({ id, direct_history }) => typeof id === 'string'
      ? [[id, heldRows({ carrier_data: { [DIRECT_HISTORY]: direct_history ?? null } })] as const] : []));
  }

  /**
   * Each package's newest scan instant up to now, ignoring the app's own
   * placeholder rows, future-dated scans (forecasts, clocks read ahead) and
   * scans in no stage, which no alert announces.
   */
  async latestScanTimes(packageIds: string[]): Promise<Map<string, string>> {
    const latest = new Map<string, string>();
    const now = new Date().toISOString();
    await Promise.all([...new Set(packageIds)].map(async (packageId) => {
      const events = rows(await this.request(`/rest/v1/tracking_events?${query([
        ['package_id', `eq.${packageId}`],
        ['or', '(provider_event_id.is.null,provider_event_id.not.like.app:*)'],
        ['occurred_at', `lte.${now}`],
        ['stage', 'neq.pending'],
        ['select', 'occurred_at'],
        ['order', 'occurred_at.desc'],
        ['limit', '1'],
      ])}`));
      if (typeof events[0]?.occurred_at === 'string') latest.set(packageId, events[0].occurred_at);
    }));
    return latest;
  }

  /** When each package joined its account: added to it, or kept from a lookup; its creation for older rows. */
  async packageJoinTimes(packageIds: string[]): Promise<Map<string, string>> {
    const joined = new Map<string, string>();
    const ids = [...new Set(packageIds)];
    const pages = Array.from({ length: Math.ceil(ids.length / 100) }, (_, page) => ids.slice(page * 100, page * 100 + 100));
    await Promise.all(pages.map(async (page) => {
      for (const row of rows(await this.request(`/rest/v1/packages?${query([
        ['id', `in.(${page.join(',')})`],
        ['select', 'id,created_at,owned_since'],
      ])}`))) {
        const at = row.owned_since ?? row.created_at;
        if (typeof row.id === 'string' && typeof at === 'string') joined.set(row.id, at);
      }
    }));
    return joined;
  }

  /** The packages among these that their account archived. */
  async archivedPackageIds(packageIds: string[]): Promise<Set<string>> {
    const ids = [...new Set(packageIds)];
    const pages = Array.from({ length: Math.ceil(ids.length / 100) }, (_, page) => ids.slice(page * 100, page * 100 + 100));
    const found = await Promise.all(pages.map(async (page) => rows(await this.request(`/rest/v1/packages?${query([
      ['id', `in.(${page.join(',')})`],
      ['archived_at', 'not.is.null'],
      ['select', 'id'],
    ])}`))));
    return new Set(found.flat().flatMap((row) => typeof row.id === 'string' ? [row.id] : []));
  }

  /**
   * The packages among these with a link shared with someone who turned its
   * alerts on: alerts a lookup's owner turned on are left out.
   */
  async sharedAlertPackageIds(packageIds: string[]): Promise<Set<string>> {
    const ids = [...new Set(packageIds)];
    const pages = Array.from({ length: Math.ceil(ids.length / 100) }, (_, page) => ids.slice(page * 100, page * 100 + 100));
    const found = await Promise.all(pages.map(async (page) => rows(await this.request(`/rest/v1/parcel_links?${query([
      ['package_id', `in.(${page.join(',')})`],
      ['shared', 'is.true'],
      ['parcel_link_alerts.owner', 'is.false'],
      ['select', 'package_id,parcel_link_alerts!inner(id)'],
    ])}`))));
    return new Set(found.flat().flatMap((row) => typeof row.package_id === 'string' ? [row.package_id] : []));
  }

  /**
   * The packages handed over from one carrier to another, each with its
   * carriers and its stored scans, saying when each was stored, so a relay copy
   * can be told from news (relayCopies.ts), with the words some pairs need. A package of one carrier is left out.
   */
  async handoffScans(packageIds: string[]): Promise<JsonObject[]> {
    const ids = [...new Set(packageIds)];
    const pages = Array.from({ length: Math.ceil(ids.length / 100) }, (_, page) => ids.slice(page * 100, page * 100 + 100));
    const found = await Promise.all(pages.map(async (page) => rows(await this.request(`/rest/v1/packages?${query([
      ['id', `in.(${page.join(',')})`],
      ['carrier_data->>original_carrier', 'not.is.null'],
      ['select', 'id,carrier,original_carrier:carrier_data->>original_carrier,active_tracking_carrier:carrier_data->>active_tracking_carrier,'
        + 'tracking_events(id,stage,description,location,occurred_at,created_at,provider_event_id)'],
    ])}`))));
    return found.flat().map(({ original_carrier, active_tracking_carrier, ...row }) => ({
      ...row, carrier_data: { original_carrier, active_tracking_carrier },
    }));
  }

  /**
   * The parcels whose new rows (`eventIds`) include a universal provider's, among those with a
   * confirmed carrier, with what providerCopies.ts reads: the confirmed carrier, the copy record
   * and the parcel's rows. No other parcel's rows are loaded.
   */
  async providerCopyScans(eventIds: string[]): Promise<JsonObject[]> {
    const pages = (ids: string[]) => Array.from({ length: Math.ceil(ids.length / 100) }, (_, page) => ids.slice(page * 100, page * 100 + 100));
    const universal = await Promise.all(pages([...new Set(eventIds)]).map(async (page) => rows(await this.request(`/rest/v1/tracking_events?${query([
      ['id', `in.(${page.join(',')})`],
      ['provider_event_id', 'like.unknown:*'],
      ['select', 'package_id'],
    ])}`))));
    const packageIds = [...new Set(universal.flat().flatMap((row) => typeof row.package_id === 'string' ? [row.package_id] : []))];
    const found = await Promise.all(pages(packageIds).map(async (page) => rows(await this.request(`/rest/v1/packages?${query([
      ['id', `in.(${page.join(',')})`],
      ['carrier_data->routing->>confirmed_carrier', 'not.is.null'],
      ['select', 'id,confirmed_carrier:carrier_data->routing->>confirmed_carrier,direct_history:carrier_data->direct_history,'
        + 'tracking_events(id,stage,description,location,occurred_at,provider_event_id,time:raw_data->>time,local_time:raw_data->>local_time,'
        + 'observed:raw_data->observed_without_provider_timestamp,universal_provider:raw_data->>universal_provider,'
        + 'retimed_from:raw_data->>retimed_from)'],
    ])}`))));
    return found.flat().map(({ confirmed_carrier, direct_history, ...row }) => ({
      ...row, carrier_data: { routing: { confirmed_carrier }, ...(direct_history == null ? {} : { direct_history }) },
    }));
  }

  async listPendingPushNotifications(): Promise<JsonObject[]> {
    const params = query({ select: '*', order: 'event_created_at.asc', limit: '1000' });
    return rows(await this.request(`/rest/v1/pending_push_notifications?${params}`));
  }

  /**
   * `native` claims the iPhones' notices, `fcm`, `unifiedpush` and `inbox` the Android phones' of each transport;
   * each row says its `transport`, and a UnifiedPush one carries its endpoint and keys.
   */
  async claimFriendshipPush(web: boolean, native: boolean, fcm = false, unifiedpush = false, inbox = false): Promise<JsonObject[]> {
    return rows(await this.request('/rest/v1/rpc/claim_friendship_push', {
      method: 'POST', body: { p_web: web, p_native: native, p_limit: 10, p_fcm: fcm, p_unifiedpush: unifiedpush, p_inbox: inbox },
    }));
  }

  async finishFriendshipPush(id: string, lease: string, success: boolean): Promise<void> {
    await this.request('/rest/v1/rpc/finish_friendship_push', {
      method: 'POST', body: { p_id: id, p_lease: lease, p_success: success },
    });
  }

  /** The account notifications waiting for the devices of one transport. */
  async listPendingNativePushNotifications(transport: NativePushTransport = 'apns'): Promise<JsonObject[]> {
    const params = query({ select: '*', transport: `eq.${transport}`, order: 'event_created_at.asc', limit: '1000' });
    return rows(await this.request(`/rest/v1/pending_native_push_notifications?${params}`));
  }

  /** What the apps read to show the parcels under a carrier (`displayedCarrierId`), as parcel rows (`toParcel`). */
  async shownCarrierRows(packageIds: string[]): Promise<JsonObject[]> {
    const ids = [...new Set(packageIds)];
    // A hundred ids a request keeps the address short.
    const chunks = Array.from({ length: Math.ceil(ids.length / 100) }, (_, index) => ids.slice(index * 100, index * 100 + 100));
    const found = (await Promise.all(chunks.map(async (chunk) => rows(await this.request(`/rest/v1/packages?${query([
      ['id', `in.(${chunk.join(',')})`],
      ['select', 'id,carrier,tracking_number,active_tracking_carrier:carrier_data->>active_tracking_carrier,original_carrier:carrier_data->>original_carrier,served_elsewhere:carrier_data->routing->served_elsewhere'],
    ])}`))))).flat();
    return found.map(({ active_tracking_carrier, original_carrier, served_elsewhere, ...row }) => ({
      ...row,
      carrier_data: {
        ...typeof active_tracking_carrier === 'string' ? { active_tracking_carrier } : {},
        ...typeof original_carrier === 'string' ? { original_carrier } : {},
        ...isRecord(served_elsewhere) ? { routing: { served_elsewhere } } : {},
      },
    }));
  }

  async listPendingLiveActivityEvents(): Promise<JsonObject[]> {
    const params = query({ select: '*', order: 'event_created_at.asc', limit: '1000' });
    return rows(await this.request(`/rest/v1/pending_live_activity_events?${params}`));
  }

  async recordPushDeliveries(subscriptionId: string, eventIds: string[]): Promise<void> {
    if (eventIds.length === 0) return;
    await this.request(`/rest/v1/push_deliveries?${query({
      on_conflict: 'subscription_id,event_id',
    })}`, {
      method: 'POST',
      body: eventIds.map((eventId) => ({ subscription_id: subscriptionId, event_id: eventId })),
      prefer: 'resolution=ignore-duplicates,return=minimal',
    });
  }

  /**
   * Lets the notifications announce these stored scans of a package once more:
   * forgets every push, link alert and Live Activity receipt for them. Returns how
   * many scans it found.
   */
  async reopenScanAnnouncements(packageId: string, providerEventIds: string[]): Promise<number> {
    if (providerEventIds.length === 0) return 0;
    const identities = [...new Set(providerEventIds)].map((id) => `"${id.replace(/["\\]/g, '\\$&')}"`);
    const events = rows(await this.request(`/rest/v1/tracking_events?${query([
      ['package_id', `eq.${packageId}`],
      ['provider_event_id', `in.(${identities.join(',')})`],
      ['select', 'id'],
    ])}`)).flatMap((event) => typeof event.id === 'string' ? [event.id] : []);
    if (events.length === 0) return 0;
    await Promise.all(SCAN_RECEIPT_TABLES.map(async (table) => {
      await this.request(`/rest/v1/${table}?${query({ event_id: `in.(${events.join(',')})` })}`, {
        method: 'DELETE',
        prefer: 'return=minimal',
      });
    }));
    return events.length;
  }

  async recordNativePushDeliveries(deviceId: string, eventIds: string[]): Promise<void> {
    if (eventIds.length === 0) return;
    await this.request(`/rest/v1/native_push_deliveries?${query({
      on_conflict: 'device_id,event_id',
    })}`, {
      method: 'POST',
      body: eventIds.map((eventId) => ({ device_id: deviceId, event_id: eventId })),
      prefer: 'resolution=ignore-duplicates,return=minimal',
    });
  }

  async recordLiveActivityDeliveries(values: Array<{
    deviceId: string;
    eventId: string;
    packageId: string;
    deliveryKind: string;
    eventCreatedAt: string;
  }>): Promise<void> {
    if (values.length === 0) return;
    await this.request(`/rest/v1/live_activity_event_deliveries?${query({
      on_conflict: 'device_id,event_id',
    })}`, {
      method: 'POST',
      body: values.map((value) => ({
        device_id: value.deviceId,
        event_id: value.eventId,
        package_id: value.packageId,
        delivery_kind: value.deliveryKind,
        event_created_at: value.eventCreatedAt,
      })),
      prefer: 'resolution=ignore-duplicates,return=minimal',
    });
  }

  async updatePushSubscription(subscriptionId: string, values: JsonObject): Promise<void> {
    await this.request(`/rest/v1/push_subscriptions?${query({ id: `eq.${subscriptionId}` })}`, {
      method: 'PATCH',
      body: { ...values, updated_at: new Date().toISOString() },
      prefer: 'return=minimal',
    });
  }

  /** The devices among these whose sends were refused in a row: how many, and when the next may go (`retry_at`). */
  async nativePushRetries(deviceIds: string[]): Promise<Map<string, { failures: number; at: number }>> {
    const ids = [...new Set(deviceIds)];
    const pages = Array.from({ length: Math.ceil(ids.length / 100) }, (_, page) => ids.slice(page * 100, page * 100 + 100));
    const found = (await Promise.all(pages.map(async (page) => rows(await this.request(`/rest/v1/native_push_devices?${query([
      ['id', `in.(${page.join(',')})`],
      ['retry_failures', 'gt.0'],
      ['select', 'id,retry_failures,retry_at'],
    ])}`))))).flat();
    return new Map(found.flatMap(({ id, retry_failures, retry_at }) => typeof id === 'string' && Number.isInteger(retry_failures)
      ? [[id, { failures: retry_failures as number, at: typeof retry_at === 'string' ? Date.parse(retry_at) || 0 : 0 }] as const] : []));
  }

  async updateNativePushDevice(deviceId: string, values: JsonObject): Promise<void> {
    await this.request(`/rest/v1/native_push_devices?${query({ id: `eq.${deviceId}` })}`, {
      method: 'PATCH',
      body: { ...values, updated_at: new Date().toISOString() },
      prefer: 'return=minimal',
    });
  }

  async updateLiveActivityDevice(deviceId: string, values: JsonObject): Promise<void> {
    await this.request(`/rest/v1/live_activity_devices?${query({ id: `eq.${deviceId}` })}`, {
      method: 'PATCH',
      body: { ...values, updated_at: new Date().toISOString() },
      prefer: 'return=minimal',
    });
  }

  async updateLiveActivityToken(tokenId: string, values: JsonObject): Promise<void> {
    await this.request(`/rest/v1/live_activity_update_tokens?${query({ id: `eq.${tokenId}` })}`, {
      method: 'PATCH',
      body: { ...values, updated_at: new Date().toISOString() },
      prefer: 'return=minimal',
    });
  }

  async deleteLiveActivityTokenById(tokenId: string): Promise<void> {
    await this.request(`/rest/v1/live_activity_update_tokens?${query({ id: `eq.${tokenId}` })}`, {
      method: 'DELETE',
      prefer: 'return=minimal',
    });
  }

  async deleteAuthUser(userId: string): Promise<void> {
    await this.request(`/auth/v1/admin/users/${encodeURIComponent(userId)}`, { method: 'DELETE' });
  }

  /** An account as the Auth server holds it, read with the service key. Null when it is gone. */
  async getAuthAccount(userId: string): Promise<AuthAccount | null> {
    let user: unknown;
    try {
      user = await this.request(`/auth/v1/admin/users/${encodeURIComponent(userId)}`);
    } catch (error) {
      if (error instanceof SupabaseError && error.status === 404) return null;
      throw error;
    }
    if (!isRecord(user) || typeof user.id !== 'string') return null;
    const metadata = isRecord(user.user_metadata) ? user.user_metadata : {};
    return {
      email: typeof user.email === 'string' && user.email ? user.email : null,
      emailConfirmed: typeof user.email_confirmed_at === 'string' && user.email_confirmed_at !== '',
      locale: typeof metadata.locale === 'string' ? metadata.locale : null,
    };
  }

  /** The Apple user IDs an account signs in with, read with the service key. */
  async appleSubjects(userId: string): Promise<string[]> {
    const user = await this.request(`/auth/v1/admin/users/${encodeURIComponent(userId)}`);
    if (!isRecord(user) || !Array.isArray(user.identities)) return [];
    return user.identities.flatMap((identity) => {
      if (!isRecord(identity) || identity.provider !== 'apple') return [];
      const data = isRecord(identity.identity_data) ? identity.identity_data : {};
      const subject = typeof data.sub === 'string' ? data.sub : identity.id;
      return typeof subject === 'string' && subject ? [subject] : [];
    });
  }
}

/** How many parcels a page of redetection candidates holds. */
export const REDETECTION_PAGE = 500;
/** How many ids a page of the parcels nobody is waiting for holds: the API's row limit. */
const UNWATCHED_PAGE = 1000;
/** How many parcels a page of scheduled sync candidates holds: the API's row limit. */
const CANDIDATE_PAGE = 1000;
/** How many parcels a page of a Friends circle's Passports holds: the API's row limit. */
const PASSPORT_PAGE = 1000;
/** How many parcels listSyncCandidates reads by id at once, which keeps their ids well inside a request line. */
const SYNC_CANDIDATES_PER_READ = 100;

/**
 * The parcels the least recently checked first, the order the schedule keeps among those due as
 * long: never checked first, then the first added, then by id, so that parcels checked at the
 * same time keep one order from run to run.
 */
function leastRecentlyChecked(parcels: JsonObject[]): JsonObject[] {
  const time = (value: unknown) => {
    const parsed = typeof value === 'string' ? Date.parse(value) : Number.NaN;
    return Number.isFinite(parsed) ? parsed : -Infinity;
  };
  const compare = <T extends number | string>(left: T, right: T) => left === right ? 0 : left < right ? -1 : 1;
  return parcels.sort((a, b) => compare(time(a.last_synced_at), time(b.last_synced_at))
    || compare(time(a.created_at), time(b.created_at)) || compare(String(a.id), String(b.id)));
}

/** The priority of a re-check nobody asked for, such as a scraper release's: it waits behind every other job. */
export const BACKGROUND_SYNC_PRIORITY = -30;
/** The scheduled run's priority: an account's check and a lookup's go ahead of it. */
export const SCHEDULED_SYNC_PRIORITY = -10;
/**
 * A lookup's check, such as a new lookup's first check or a link opened: behind every account's
 * check, ahead of the scheduled run, which stops for it between checks.
 */
export const LOOKUP_SYNC_PRIORITY = -5;
/** An account's check, such as a refresh or a new parcel: ahead of every other job. */
export const ACCOUNT_SYNC_PRIORITY = 10;
/** The checks a reader waits for, which the readers' lane takes (claim_reader_sync_job): a first check, a link opened, a refresh. */
export const READER_SYNC_REASONS: readonly string[] = ['added', 'opened', 'refresh'];

/** A Friends profile that shares its Passport: the account behind it, and whether it shares the countries too. */
export interface PassportSharer { owner: string; countries: boolean }

export class SupabaseServiceClient extends SupabaseClient {
  /**
   * Adds a parcel to an account, with the checks create_owned_package makes
   * for a signed-in user. Only the server can, after charging the account's
   * daily allowance. Returns the new parcel's id.
   */
  async createAccountPackage(
    userId: string,
    trackingNumber: string,
    label: string,
    carrier: string,
    trackingUrl?: string | null,
    dpdPostcode?: string | null,
    phoneDigits: string | null = null,
  ): Promise<string> {
    let result = await this.request('/rest/v1/rpc/create_account_package_with_digits', {
      method: 'POST',
      body: {
        p_user_id: userId,
        p_tracking_number: trackingNumber,
        p_label: label,
        p_carrier: carrier,
        p_tracking_url: trackingUrl ?? null,
        p_dpd_postcode: dpdPostcode ?? null,
        p_phone_digits: phoneDigits,
      },
    });
    if (Array.isArray(result) && result.length === 1) [result] = result;
    if (!isRecord(result) || typeof result.id !== 'string') {
      throw new SupabaseError('Supabase did not return the new package');
    }
    return result.id;
  }

  /** Notes the sign-in an account's parcel was added from with Add. */
  async markAddedSession(userId: string, packageId: string, sessionId: string): Promise<void> {
    await this.request(`/rest/v1/packages?${query({ id: `eq.${packageId}`, user_id: `eq.${userId}` })}`, {
      method: 'PATCH',
      body: { added_session: sessionId },
      prefer: 'return=minimal',
    });
  }

  /** The account's open parcels added from this sign-in since `since`, with their scans' stages and whether they hold a note. */
  async listAddedFromSession(userId: string, sessionId: string, since: Date): Promise<JsonObject[]> {
    return rows(await this.request(`/rest/v1/packages?${query({
      select: 'id,tracking_number,label,archived_at,current_stage,created_at,tracking_events(stage),package_notes(package_id),package_files(id)',
      user_id: `eq.${userId}`,
      added_session: `eq.${sessionId}`,
      archived_at: 'is.null',
      created_at: `gte.${since.toISOString()}`,
    })}`));
  }

  /**
   * Deletes a parcel this sign-in added since `since` while it is still open, unnamed and without a scan. False when
   * it no longer is.
   */
  async deleteHalfTypedPackage(userId: string, sessionId: string, packageId: string, since: Date): Promise<boolean> {
    const deleted = rows(await this.request(`/rest/v1/packages?${query({
      select: 'id',
      id: `eq.${packageId}`,
      user_id: `eq.${userId}`,
      added_session: `eq.${sessionId}`,
      archived_at: 'is.null',
      label: 'eq.',
      current_stage: 'eq.pending',
      created_at: `gte.${since.toISOString()}`,
    })}`, { method: 'DELETE', prefer: 'return=representation' }));
    return deleted.length === 1;
  }

  /**
   * The sync worker's snapshot of one package. It embeds the stored event
   * identities instead of the public event list, which the worker never reads.
   */
  override async getPackage(packageId: string): Promise<JsonObject | null> {
    const select = `${PACKAGE_COLUMNS},current_stage,tracking_generation,user_id,one_off,sync_revision,${SYNC_EXTRAS}`;
    const params = query({
      select,
      id: `eq.${packageId}`,
      limit: '1',
    });
    const parcel = rows(await this.request(`/rest/v1/packages?${params}`))[0];
    if (parcel) return parcel;
    return rows(await this.request(`/rest/v1/packages?${query({
      select,
      'carrier_data->>original_package_id': `eq.${packageId}`, limit: '1',
    })}`))[0] ?? null;
  }

  /**
   * Scheduled sync candidates, with their stored event identities: every
   * account's open parcels, archived ones included. One-off parcels come from
   * listFollowedOneOffPackages and listUnfiledOneOffPackages.
   */
  override async listActivePackages(): Promise<JsonObject[]> {
    return await this.activePackages(`${ACTIVE_PACKAGE_SELECT},${SYNC_EXTRAS}`, [['one_off', 'is.false']]);
  }

  /**
   * The open one-off parcels with a link opened since `openedSince` or with
   * an alert on, the least recently checked first, in the shape of
   * listActivePackages.
   */
  async listFollowedOneOffPackages(openedSince: Date): Promise<JsonObject[]> {
    return await this.everyCandidate('/rest/v1/rpc/followed_one_off_packages', [['select', `${ACTIVE_PACKAGE_SELECT},${SYNC_EXTRAS}`]], {
      method: 'POST', body: { p_opened_since: openedSince.toISOString() },
    });
  }

  /**
   * The open, unarchived one-off parcels filed under one of `carriers` (no
   * carrier, or one with no adapter of its own) and added since `addedSince`,
   * followed or not, the least recently checked first, in the shape of
   * listActivePackages without what the sync loaders read besides the columns
   * (SYNC_EXTRAS): thousands of them, most of which have left the schedule, and
   * the cadence of one nobody follows reads none of it. listSyncCandidates reads
   * the ones a run takes with it. The carriers go in the body: hundreds of them in
   * the URL pass the gateway's limit on its length.
   */
  async listUnfiledOneOffPackages(addedSince: Date, carriers: readonly string[]): Promise<JsonObject[]> {
    return await this.everyCandidate('/rest/v1/rpc/unfiled_one_off_packages', [['select', ACTIVE_PACKAGE_SELECT]], {
      method: 'POST', body: { p_added_since: addedSince.toISOString(), p_carriers: [...carriers] },
    });
  }

  /**
   * These parcels by id, in the shape of listActivePackages: the ones a scheduled
   * run takes from listUnfiledOneOffPackages, with their stored event identities.
   * One deleted since is left out.
   */
  async listSyncCandidates(ids: readonly string[]): Promise<JsonObject[]> {
    const found: JsonObject[] = [];
    for (let start = 0; start < ids.length; start += SYNC_CANDIDATES_PER_READ) {
      found.push(...rows(await this.request(`/rest/v1/packages?${query({
        select: `${ACTIVE_PACKAGE_SELECT},${SYNC_EXTRAS}`, id: `in.(${ids.slice(start, start + SYNC_CANDIDATES_PER_READ).join(',')})`,
      })}`)));
    }
    return found;
  }

  /**
   * The open, unarchived parcels added since `addedSince`, with or without an
   * account, that were last routed with other catalog rules than `rules`, and the
   * delivered or returned ones filed under no carrier, the newest first: what
   * redetection.ts reads, without their events. A page holds
   * {@link REDETECTION_PAGE} of them; the next one starts `before` the last one's `created_at`.
   */
  async listRedetectionCandidates(addedSince: Date, rules: string, before?: string): Promise<JsonObject[]> {
    const params = query([
      ['select', 'id,user_id,tracking_number,carrier,current_stage,last_synced_at,sync_status,sync_error,carrier_data,created_at'],
      ['archived_at', 'is.null'],
      ['created_at', `gte.${addedSince.toISOString()}`],
      ...(before ? [['created_at', `lt.${before}`] as [string, string]] : []),
      ['and', '(or(current_stage.not.in.(delivered,returned),carrier.in.(unknown,intl-post)),'
        + `or(carrier_data->routing->>rules.is.null,carrier_data->routing->>rules.neq.${rules}))`],
      ['order', 'created_at.desc'],
      ['limit', String(REDETECTION_PAGE)],
    ]);
    return rows(await this.request(`/rest/v1/packages?${params}`));
  }

  /**
   * The open parcels nobody is waiting for: no notification can reach anyone
   * about them, and neither their account's apps nor one of their links were
   * opened since `openedSince`. They come in pages by id until one adds none:
   * the database's API ends every answer at its row limit, and a parcel left
   * past it would be checked as one someone is waiting for.
   */
  async listUnwatchedPackageIds(openedSince: Date): Promise<string[]> {
    const ids = new Set<string>();
    for (;;) {
      const after = [...ids].at(-1);
      const page = rows(await this.request('/rest/v1/rpc/unwatched_package_ids', {
        method: 'POST', body: { p_opened_since: openedSince.toISOString(), p_after: after ?? null, p_limit: UNWATCHED_PAGE },
      })).flatMap((row) => typeof row.id === 'string' && !ids.has(row.id) ? [row.id] : []);
      if (page.length === 0) return [...ids];
      for (const id of page) ids.add(id);
    }
  }

  /**
   * The open parcels someone is waiting for, the ones listUnwatchedPackageIds leaves out, by id, and why: `on_screen`
   * when one of their links or their account's apps were read since `onScreenSince`, otherwise `alert` when a
   * notification can reach someone about them, otherwise `opened`, read since `openedSince` only. They come in pages
   * by id, as the unwatched ones do.
   */
  async listWaitingPackages(openedSince: Date, onScreenSince: Date): Promise<Map<string, Waiting>> {
    const waiting = new Map<string, Waiting>();
    for (;;) {
      const after = [...waiting.keys()].at(-1);
      const params = query([['select', 'id,on_screen,alerted'], ['order', 'id.asc'], ['limit', String(UNWATCHED_PAGE)],
        ...(after === undefined ? [] : [['id', `gt.${after}`] as const])]);
      const page = rows(await this.request(`/rest/v1/rpc/waiting_package_ids?${params}`, {
        method: 'POST', body: { p_opened_since: openedSince.toISOString(), p_on_screen_since: onScreenSince.toISOString() },
      })).filter((row) => typeof row.id === 'string' && !waiting.has(row.id));
      if (page.length === 0) return waiting;
      for (const row of page) {
        waiting.set(String(row.id), row.on_screen === true ? 'on_screen' : row.alerted === true ? 'alert' : 'opened');
      }
    }
  }

  /** When the account's apps last read its parcels, as its export shows it; null when no read was recorded. */
  async accountLastOpened(userId: string): Promise<string | null> {
    const params = query({ select: 'last_opened_at', user_id: `eq.${userId}`, limit: '1' });
    const [activity] = rows(await this.request(`/rest/v1/account_activity?${params}`));
    return typeof activity?.last_opened_at === 'string' ? activity.last_opened_at : null;
  }

  /**
   * Which of these numbers an account already follows, with the parcel's id: by the number each was saved under, the
   * one a merged leg kept, one its carrier names as its next leg's, or an email's twin of its number
   * (`account_tracking_numbers`).
   */
  async accountTrackingNumbers(userId: string, numbers: string[]): Promise<Map<string, string>> {
    const known = new Map<string, string>();
    if (!numbers.length) return known;
    const found = await this.request('/rest/v1/rpc/account_tracking_numbers', {
      method: 'POST',
      body: { p_user_id: userId, p_numbers: numbers },
    });
    for (const row of rows(found)) {
      if (typeof row.tracking_number === 'string' && typeof row.package_id === 'string') known.set(row.tracking_number, row.package_id);
    }
    return known;
  }

  /**
   * Keeps an email's two numbers for one package, the marketplace's logistics number and the last-mile carrier's, for
   * the account's parcel that holds one of them (`package_twin_numbers`). False when that parcel is gone.
   */
  async recordTwinNumbers(userId: string, packageId: string, earlier: string, later: string): Promise<boolean> {
    return await this.request('/rest/v1/rpc/record_twin_numbers', {
      method: 'POST',
      body: { p_user_id: userId, p_package_id: packageId, p_earlier: earlier, p_later: later },
    }) === true;
  }

  /** The pairs of numbers the account's emails named for one package, oldest first, as its export shows them. */
  async twinNumbers(userId: string): Promise<ApiTwinNumbers[]> {
    const params = query({ select: 'package_id,earlier,later,created_at', user_id: `eq.${userId}`, order: 'created_at.asc,package_id.asc' });
    return rows(await this.request(`/rest/v1/package_twin_numbers?${params}`)).flatMap((row) =>
      typeof row.package_id === 'string' && typeof row.earlier === 'string' && typeof row.later === 'string' && typeof row.created_at === 'string'
        ? [{ packageId: row.package_id, earlier: row.earlier, later: row.later, createdAt: row.created_at }]
        : []);
  }

  /** The account's forwarding address, or null when it has none. */
  async inboundMailbox(userId: string): Promise<InboundMailbox | null> {
    return inboundMailbox(await this.request('/rest/v1/rpc/inbound_mailbox', { method: 'POST', body: { p_user_id: userId } }));
  }

  /** Gives the account a forwarding address with this token, in place of the one it had. */
  async openInboundMailbox(userId: string, token: string, renew: boolean): Promise<InboundMailbox> {
    const mailbox = inboundMailbox(await this.request('/rest/v1/rpc/open_inbound_mailbox', {
      method: 'POST',
      body: { p_user_id: userId, p_token: token, p_renew: renew },
    }));
    if (!mailbox) throw new SupabaseError('Supabase did not return the forwarding address');
    return mailbox;
  }

  /** Takes the account's forwarding address away: emails to it are dropped from then on. */
  async closeInboundMailbox(userId: string): Promise<void> {
    await this.request('/rest/v1/rpc/close_inbound_mailbox', { method: 'POST', body: { p_user_id: userId } });
  }

  /** Switches the notification of each parcel an email adds on or off. Null for an account without an address. */
  async setInboundMailboxNotify(userId: string, notify: boolean): Promise<InboundMailbox | null> {
    return inboundMailbox(await this.request('/rest/v1/rpc/set_inbound_mailbox_notify', {
      method: 'POST', body: { p_user_id: userId, p_notify: notify },
    }));
  }

  /**
   * The parcels an email just added to the account: each starts unseen and, unless the account switched it off, is
   * queued to be notified to each of its browsers and phones. Returns how many notifications were queued.
   */
  async parcelsAddedByEmail(userId: string, packageIds: readonly string[]): Promise<number> {
    const queued = await this.request('/rest/v1/rpc/parcels_added_by_email', {
      method: 'POST', body: { p_user_id: userId, p_package_ids: packageIds },
    });
    return typeof queued === 'number' ? queued : 0;
  }

  async claimEmailAddedPush(web: boolean, native: boolean, fcm = false, unifiedpush = false, inbox = false): Promise<JsonObject[]> {
    return rows(await this.request('/rest/v1/rpc/claim_email_added_push', {
      method: 'POST', body: { p_web: web, p_native: native, p_limit: 10, p_fcm: fcm, p_unifiedpush: unifiedpush, p_inbox: inbox },
    }));
  }

  async finishEmailAddedPush(id: string, lease: string, success: boolean): Promise<void> {
    await this.request('/rest/v1/rpc/finish_email_added_push', {
      method: 'POST', body: { p_id: id, p_lease: lease, p_success: success },
    });
  }

  /** Whether each parcel an assistant adds is notified to the account's browsers and phones: on until switched off. */
  async assistantAddedNotify(userId: string): Promise<boolean> {
    return await this.request('/rest/v1/rpc/assistant_added_notify', { method: 'POST', body: { p_user_id: userId } }) !== false;
  }

  /** Switches the notification of each parcel an assistant adds on or off, and answers the choice saved. */
  async setAssistantAddedNotify(userId: string, notify: boolean): Promise<boolean> {
    return await this.request('/rest/v1/rpc/set_assistant_added_notify', {
      method: 'POST', body: { p_user_id: userId, p_notify: notify },
    }) === true;
  }

  /**
   * The parcels an assistant just added to the account through one of its grants: each starts unseen and, unless the
   * account switched it off, is queued to be notified to each of its browsers and phones. Returns how many
   * notifications were queued.
   */
  async parcelsAddedByAssistant(userId: string, grantId: string, packageIds: readonly string[]): Promise<number> {
    const queued = await this.request('/rest/v1/rpc/parcels_added_by_assistant', {
      method: 'POST', body: { p_user_id: userId, p_grant_id: grantId, p_package_ids: packageIds },
    });
    return typeof queued === 'number' ? queued : 0;
  }

  async claimAssistantAddedPush(web: boolean, native: boolean, fcm = false, unifiedpush = false, inbox = false): Promise<JsonObject[]> {
    return rows(await this.request('/rest/v1/rpc/claim_assistant_added_push', {
      method: 'POST', body: { p_web: web, p_native: native, p_limit: 10, p_fcm: fcm, p_unifiedpush: unifiedpush, p_inbox: inbox },
    }));
  }

  async finishAssistantAddedPush(id: string, lease: string, success: boolean): Promise<void> {
    await this.request('/rest/v1/rpc/finish_assistant_added_push', {
      method: 'POST', body: { p_id: id, p_lease: lease, p_success: success },
    });
  }

  /** The account a forwarding address's token names, or null. Counts nothing. */
  async inboundMailboxOwner(token: string): Promise<string | null> {
    const result = await this.request('/rest/v1/rpc/inbound_mailbox_owner', { method: 'POST', body: { p_token: token } });
    if (result === null || result === undefined) return null;
    if (typeof result !== 'string') throw new SupabaseError('Supabase did not return the forwarding address\'s account');
    return result;
  }

  /**
   * Counts one email to the account against its allowance of emails received today (UTC), read or not, unless it is
   * used up.
   */
  async claimReceivedInboundMail(userId: string, limit: number): Promise<boolean> {
    const result = await this.request('/rest/v1/rpc/claim_received_inbound_mail', {
      method: 'POST',
      body: { p_user_id: userId, p_limit: limit },
    });
    if (typeof result !== 'boolean') throw new SupabaseError('Supabase did not return the email allowance');
    return result;
  }

  /**
   * Counts one email the server reads for the account against the hour's allowance for every address and the
   * account's own for today (UTC), unless one is used up.
   */
  async claimInboundMail(userId: string, limits: { perAccount: number; overall: number }): Promise<InboundMailClaim> {
    const result = await this.request('/rest/v1/rpc/claim_inbound_mail', {
      method: 'POST',
      body: { p_user_id: userId, p_limit: limits.perAccount, p_overall_limit: limits.overall },
    });
    if (!isRecord(result) || typeof result.allowed !== 'boolean') throw new SupabaseError('Supabase did not return the email allowance');
    return {
      allowed: result.allowed,
      scope: result.scope === 'overall' || result.scope === 'account' ? result.scope : null,
    };
  }

  /**
   * Notes what an email to the account's address did and who sent it, and what Gmail sent to confirm forwarding
   * when it was that.
   */
  async recordInboundMail(
    userId: string,
    result: InboundMailResult,
    added: number,
    confirmation: { code: string | null; link: string | null } | null = null,
    sender = '',
  ): Promise<void> {
    await this.request('/rest/v1/rpc/record_inbound_mail', {
      method: 'POST',
      body: {
        p_user_id: userId,
        p_result: result,
        p_added: added,
        p_confirmation_code: confirmation?.code ?? null,
        p_confirmation_link: confirmation?.link ?? null,
        p_sender: sender,
      },
    });
  }

  /**
   * Keeps an email the account's address received, as received, for 30 days (forgetOldInboundMail). False when the
   * account has no address any more.
   */
  async keepInboundMail(userId: string, raw: Uint8Array): Promise<boolean> {
    return await this.request('/rest/v1/rpc/keep_inbound_mail', {
      method: 'POST',
      body: { p_user_id: userId, p_raw: Buffer.from(raw.buffer, raw.byteOffset, raw.byteLength).toString('base64') },
    }) === true;
  }

  /** Deletes the emails kept over 30 days ago; returns how many. */
  async forgetOldInboundMail(): Promise<number> {
    return Number(await this.request('/rest/v1/rpc/forget_old_inbound_mail', { method: 'POST', body: {} }) ?? 0);
  }

  /** Names the account's parcel, unless it has a name: false when it had one, or is not the account's. */
  async nameUnnamedPackage(userId: string, packageId: string, label: string): Promise<boolean> {
    return rows(await this.request(`/rest/v1/packages?${query({ id: `eq.${packageId}`, user_id: `eq.${userId}`, label: 'eq.', select: 'id' })}`, {
      method: 'PATCH',
      body: { label },
      prefer: 'return=representation',
    })).length > 0;
  }

  /** Renames or mutes the account's parcel: false when it is not the account's. */
  async updateAccountPackage(
    userId: string,
    packageId: string,
    values: { label?: string; notifications_muted?: boolean; email_muted?: boolean },
  ): Promise<boolean> {
    return rows(await this.request(`/rest/v1/packages?${query({ id: `eq.${packageId}`, user_id: `eq.${userId}`, select: 'id' })}`, {
      method: 'PATCH',
      body: values,
      prefer: 'return=representation',
    })).length > 0;
  }

  /**
   * Changes the account's parcel's carrier with the owner's own function (change_owned_package_carrier): false when it
   * is not the account's; an input the carrier refuses throws 22023.
   */
  async changeAccountPackageCarrier(
    userId: string,
    packageId: string,
    carrier: string,
    trackingUrl: string | null,
    dpdPostcode: string | null,
    phoneDigits: string | null = null,
  ): Promise<boolean> {
    return await this.request('/rest/v1/rpc/change_account_package_carrier_with_digits', {
      method: 'POST',
      body: { p_user_id: userId, p_package_id: packageId, p_carrier: carrier, p_tracking_url: trackingUrl, p_dpd_postcode: dpdPostcode, p_phone_digits: phoneDigits },
    }) === true;
  }

  /**
   * Archives or restores the account's parcel with the owner's own function (set_owned_package_archived): false when it
   * is not the account's; a restore past the account's parcel limit throws P0001.
   */
  async setAccountPackageArchived(userId: string, packageId: string, archived: boolean): Promise<boolean> {
    return await this.request('/rest/v1/rpc/set_account_package_archived', {
      method: 'POST',
      body: { p_user_id: userId, p_package_id: packageId, p_archived: archived },
    }) === true;
  }

  /** Stores a client that registered itself. */
  async registerAssistantClient(client: AssistantClient): Promise<void> {
    await this.request('/rest/v1/rpc/register_assistant_client', {
      method: 'POST',
      body: { p_id: client.id, p_name: client.name, p_uri: client.uri, p_redirect_uris: client.redirectUris },
    });
  }

  /** A client that registered itself, or null. */
  async assistantClient(id: string): Promise<AssistantClient | null> {
    const row = await this.request('/rest/v1/rpc/assistant_client', { method: 'POST', body: { p_id: id } });
    if (!isRecord(row) || typeof row.id !== 'string' || typeof row.name !== 'string' || !Array.isArray(row.redirect_uris)) return null;
    return {
      id: row.id,
      name: row.name,
      uri: typeof row.uri === 'string' ? row.uri : null,
      redirectUris: row.redirect_uris.filter((uri): uri is string => typeof uri === 'string'),
    };
  }

  /** The owner allowed a client: a grant, and the code (as its digest) the client redeems within ten minutes. */
  async openAssistantCode(input: {
    userId: string; clientId: string; name: string; redirectHost: string; rotate: boolean; scopes: Scope[];
    codeDigest: string; redirectUri: string; challenge: string;
  }): Promise<void> {
    await this.request('/rest/v1/rpc/open_assistant_code', {
      method: 'POST',
      body: {
        p_user_id: input.userId, p_client_id: input.clientId, p_name: input.name, p_redirect_host: input.redirectHost,
        p_rotate: input.rotate, p_scopes: input.scopes, p_hash: input.codeDigest, p_redirect_uri: input.redirectUri,
        p_challenge: input.challenge,
      },
    });
  }

  /** Redeems a code for the tokens whose digests are given; null when the code, client, address or challenge is wrong. */
  async redeemAssistantCode(input: {
    codeDigest: string; clientId: string; redirectUri: string; challenge: string;
    accessDigest: string; accessExpires: Date; refreshDigest: string; refreshExpires: Date;
  }): Promise<AssistantIssue | null> {
    return assistantIssue(await this.request('/rest/v1/rpc/redeem_assistant_code', {
      method: 'POST',
      body: {
        p_hash: input.codeDigest, p_client_id: input.clientId, p_redirect_uri: input.redirectUri, p_challenge: input.challenge,
        p_access: input.accessDigest, p_access_expires: input.accessExpires.toISOString(),
        p_refresh: input.refreshDigest, p_refresh_expires: input.refreshExpires.toISOString(),
      },
    }));
  }

  /** Trades a refresh token for a new access token, and a new refresh token unless the grant keeps its own. */
  async refreshAssistantTokens(input: {
    scopes?: string[]; refreshDigest: string; clientId: string; accessDigest: string; accessExpires: Date; newRefreshDigest: string; refreshExpires: Date;
  }): Promise<(AssistantIssue & { rotated: boolean }) | 'invalid_scope' | null> {
    const result = await this.request('/rest/v1/rpc/refresh_assistant_tokens', {
      method: 'POST',
      body: {
        ...(input.scopes ? { p_scopes: input.scopes } : {}),
        p_refresh: input.refreshDigest, p_client_id: input.clientId, p_access: input.accessDigest,
        p_access_expires: input.accessExpires.toISOString(), p_new_refresh: input.newRefreshDigest,
        p_refresh_expires: input.refreshExpires.toISOString(),
      },
    });
    if (isRecord(result) && result.error === 'invalid_scope') return 'invalid_scope';
    const issue = assistantIssue(result);
    return issue && isRecord(result) ? { ...issue, rotated: result.rotated === true } : null;
  }

  /** Whose an access or personal token is; null when it is unknown, expired or revoked. */
  async assistantToken(digest: string): Promise<AssistantTokenOwner | null> {
    const result = await this.request('/rest/v1/rpc/assistant_token', { method: 'POST', body: { p_hash: digest } });
    const issue = assistantIssue(result);
    if (!issue || !isRecord(result) || (result.kind !== 'access' && result.kind !== 'personal')) return null;
    const expires = isoTime(result.expires_at);
    return {
      ...issue,
      kind: result.kind,
      clientId: typeof result.client_id === 'string' ? result.client_id : null,
      expiresAt: expires ? Math.floor(Date.parse(expires) / 1_000) : null,
    };
  }

  /** An account's connected assistants and personal tokens, newest first. */
  async assistantGrants(userId: string): Promise<AssistantGrant[]> {
    const result = await this.request('/rest/v1/rpc/assistant_grants', { method: 'POST', body: { p_user_id: userId } });
    return Array.isArray(result) ? result.map(assistantGrant).filter((grant): grant is AssistantGrant => grant !== null) : [];
  }

  /** Ends one of the account's grants and its tokens at once; false when it has no such grant. */
  async revokeAssistantGrant(userId: string, grantId: string): Promise<boolean> {
    const result = await this.request('/rest/v1/rpc/revoke_assistant_grant', {
      method: 'POST',
      body: { p_user_id: userId, p_grant_id: grantId },
    });
    return result === true;
  }

  /** Makes a personal token, given as its digest. Throws P0001 past ten. */
  async createPersonalToken(userId: string, name: string, scopes: Scope[], digest: string): Promise<AssistantGrant> {
    const grant = assistantGrant(await this.request('/rest/v1/rpc/create_personal_token', {
      method: 'POST',
      body: { p_user_id: userId, p_name: name, p_scopes: scopes, p_hash: digest },
    }));
    if (!grant) throw new SupabaseError('Supabase did not return the personal token');
    return grant;
  }

  /** A client giving a token back: an access token ends alone, a refresh token with its grant. */
  async revokeAssistantToken(digest: string, clientId: string | null): Promise<void> {
    await this.request('/rest/v1/rpc/revoke_assistant_token', { method: 'POST', body: { p_hash: digest, p_client_id: clientId } });
  }

  /**
   * One parcel of an account in the shape the API gives its owner, events
   * included. Null when the parcel is gone or belongs to someone else.
   */
  /**
   * One parcel of the account as its apps read it. `note`: with its private note's text and files' names, which only an
   * assistant the owner connected reads (`ASSISTANT_NOTE_EMBED`); an e-mail never does.
   */
  async getOwnedPackage(packageId: string, userId: string, { note = false }: { note?: boolean } = {}): Promise<JsonObject | null> {
    const select = note ? `${PACKAGE_SELECT},${ASSISTANT_NOTE_EMBED}` : PACKAGE_SELECT;
    const params = query({ select, id: `eq.${packageId}`, user_id: `eq.${userId}`, limit: '1' });
    const parcel = rows(await this.request(`/rest/v1/packages?${params}`))[0];
    return parcel ? withReviewDemo(parcel, userId) : null;
  }

  /** The lookup country (`lookup_country_hint`) of the account's latest parcel that has one. */
  async latestLookupCountry(userId: string): Promise<string | null> {
    const params = query({
      select: 'country:carrier_data->>lookup_country_hint',
      user_id: `eq.${userId}`,
      'carrier_data->>lookup_country_hint': 'not.is.null',
      order: 'created_at.desc',
      limit: '1',
    });
    const country = rows(await this.request(`/rest/v1/packages?${params}`))[0]?.country;
    return typeof country === 'string' ? country : null;
  }

  /** The timezone the account chose for its notifications, or null when it chose none. */
  async accountTimeZone(userId: string): Promise<string | null> {
    const params = query({ select: 'timezone,timezone_chosen', user_id: `eq.${userId}`, limit: '1' });
    const saved = rows(await this.request(`/rest/v1/notification_preferences?${params}`))[0];
    return saved?.timezone_chosen !== false && typeof saved?.timezone === 'string' && saved.timezone ? saved.timezone : null;
  }

  /** Whether the account shows Peek AI's estimates: on until switched off. */
  async accountShowsPeekAi(userId: string): Promise<boolean> {
    const params = query({ select: 'show_peek_ai', user_id: `eq.${userId}`, limit: '1' });
    return rows(await this.request(`/rest/v1/notification_preferences?${params}`))[0]?.show_peek_ai !== false;
  }

  /** An account's parcels as its apps read them, archived ones included; `note` as `getOwnedPackage` reads it. */
  async listAccountPackages(userId: string, { note = false }: { note?: boolean } = {}): Promise<JsonObject[]> {
    const select = note ? `${PACKAGE_SELECT},${ASSISTANT_NOTE_EMBED}` : PACKAGE_SELECT;
    const params = query({ select, user_id: `eq.${userId}`, order: 'created_at.desc' });
    return rows(await this.request(`/rest/v1/packages?${params}`)).map((parcel) => withReviewDemo(parcel, userId));
  }

  /**
   * The postcodes an account gave its parcels' carriers, newest parcel first, with what the carrier
   * made of each (accountPostcode.ts). All of them, as the Add sheet reads them: an account holds at
   * most 500 parcels, and the one given the same carrier can be the oldest.
   */
  async accountPostcodes(userId: string): Promise<JsonObject[]> {
    return rows(await this.request(`/rest/v1/packages?${query({
      select: 'carrier,dpd_postcode,postcode_country:carrier_data->postcode_country,'
        + 'dpd_postcode_verified:carrier_data->dpd_postcode_verified,postcode_refused:carrier_data->routing->postcode_refused',
      user_id: `eq.${userId}`, dpd_postcode: 'not.is.null', order: 'created_at.desc,id.desc', limit: '500',
    })}`));
  }

  /** Writes a parcel's values unless its tracking changed since it was read: a carrier or postcode saved since renews it. */
  async updatePackageAtGeneration(packageId: string, generation: string, values: JsonObject): Promise<boolean> {
    return rows(await this.request(`/rest/v1/packages?${query({
      id: `eq.${packageId}`, tracking_generation: `eq.${generation}`, select: 'id',
    })}`, { method: 'PATCH', body: values, prefer: 'return=representation' })).length > 0;
  }

  /** Gives a parcel its owner's postcode, unless its tracking changed or it got a postcode since it was read. */
  async giveAccountPostcode(packageId: string, generation: string, values: JsonObject): Promise<boolean> {
    return rows(await this.request(`/rest/v1/packages?${query({
      id: `eq.${packageId}`, tracking_generation: `eq.${generation}`, dpd_postcode: 'is.null', select: 'id',
    })}`, { method: 'PATCH', body: values, prefer: 'return=representation' })).length > 0;
  }

  /**
   * The accounts behind those Friends profiles that share their Passport, by profile id, and whether they share its
   * countries too. Given the account that reads them (`viewer`), only its own profile and those of the friends it is
   * still connected with.
   */
  async passportSharers(profileIds: string[], viewer?: string): Promise<Map<string, PassportSharer>> {
    if (!profileIds.length) return new Map();
    const params = query({ select: 'id,user_id,share_countries', id: `in.(${[...new Set(profileIds)].join(',')})`, share_stats: 'is.true' });
    const sharers = new Map(rows(await this.request(`/rest/v1/friend_profiles?${params}`))
      .flatMap((row) => typeof row.id === 'string' && typeof row.user_id === 'string'
        ? [[row.id, { owner: row.user_id, countries: row.share_countries === true }] as const] : []));
    if (viewer === undefined) return sharers;
    // The viewer's own profile needs no connection: its connections are read only when a friend's profile shares.
    const friends = new Set<unknown>();
    if ([...sharers.values()].some(({ owner }) => owner !== viewer)) {
      const params = query({ select: 'user_low,user_high', or: `(user_low.eq.${viewer},user_high.eq.${viewer})` });
      for (const { user_low: low, user_high: high } of rows(await this.request(`/rest/v1/friend_connections?${params}`))) {
        if (low === viewer) friends.add(high);
        else if (high === viewer) friends.add(low);
      }
    }
    return new Map([...sharers].filter(([, { owner }]) => owner === viewer || friends.has(owner)));
  }

  /**
   * The parcels of those accounts, archived ones included, by account: what their shared Passports are worked out
   * from, as their apps read them (`withReviewDemo`) with only the columns that takes (`PASSPORT_PACKAGE_SELECT`).
   * One read for a whole Friends circle, in pages by id: the database's API ends every answer at its row limit, and
   * an answer short of it is the last.
   */
  async passportParcels(userIds: string[]): Promise<Map<string, JsonObject[]>> {
    const owners = [...new Set(userIds)];
    const parcels = new Map(owners.map((owner) => [owner, [] as JsonObject[]]));
    if (!owners.length) return parcels;
    for (let after: string | undefined; ;) {
      const page = rows(await this.request(`/rest/v1/packages?${query([
        ['select', PASSPORT_PACKAGE_SELECT], ['user_id', `in.(${owners.join(',')})`], ['order', 'id.asc'], ['limit', String(PASSPORT_PAGE)],
        ...(after === undefined ? [] : [['id', `gt.${after}`] as const]),
      ])}`));
      for (const parcel of page) {
        const owner = String(parcel.user_id);
        parcels.get(owner)?.push(withReviewDemo(parcel, owner));
      }
      if (page.length < PASSPORT_PAGE) return parcels;
      after = String(page.at(-1)!.id);
    }
  }

  /**
   * Claims the scans to email now, delivered or ready to collect, at most
   * `limit` of them: a claimed parcel is never handed out again, to this
   * server or another. `perAccount` and `perDay` are the emails an account,
   * and everyone, may get in 24 hours; what is beyond one is recorded as
   * skipped and counted here.
   */
  async claimDeliveryEmails(
    limit: number,
    perAccount: number,
    perDay: number,
  ): Promise<{ send: DeliveryEmailClaim[]; accountCap: number; serviceCap: number }> {
    const result = await this.request('/rest/v1/rpc/claim_delivery_emails', {
      method: 'POST', body: { p_limit: limit, p_per_account: perAccount, p_per_day: perDay, p_stages: EMAIL_STAGES },
    });
    if (!isRecord(result) || !Array.isArray(result.send)) throw new SupabaseError('Supabase did not return the delivery emails');
    return {
      send: result.send.filter(isRecord).map((claim) => ({
        id: String(claim.id),
        packageId: String(claim.package_id),
        userId: String(claim.user_id),
        eventId: String(claim.event_id),
        stage: claim.stage === 'ready_for_pickup' ? 'ready_for_pickup' : 'delivered',
        timezone: typeof claim.timezone === 'string' ? claim.timezone : 'Europe/Zurich',
        clock: claim.clock === '24' || claim.clock === '12' ? claim.clock : null,
        distance: claim.distance === 'km' || claim.distance === 'mi' ? claim.distance : null,
        deliveredTime: claim.delivered_time === 'timed' || claim.delivered_time === 'date' ? claim.delivered_time : 'none',
      })),
      accountCap: Number(result.account_cap ?? 0),
      serviceCap: Number(result.service_cap ?? 0),
    };
  }

  /** Ends a claimed delivery email, with a short reason code when it was not sent. False when the claim is not open. */
  async finishDeliveryEmail(id: string, outcome: DeliveryEmailOutcome, reason: string | null = null): Promise<boolean> {
    return await this.request('/rest/v1/rpc/finish_delivery_email', {
      method: 'POST', body: { p_id: id, p_status: outcome, p_reason: reason },
    }) === true;
  }

  /**
   * Switches an account's delivery email, for the link its emails carry. The
   * account id travels in the body. Answers the stored choice, or null when
   * there is no such account.
   */
  async setDeliveryEmail(userId: string, enabled: boolean): Promise<boolean | null> {
    const stored = await this.request('/rest/v1/rpc/set_delivery_email', {
      method: 'POST', body: { p_user_id: userId, p_enabled: enabled },
    });
    return typeof stored === 'boolean' ? stored : null;
  }

  async autoLinkPackages(userId?: string): Promise<number> {
    return Number(await this.request('/rest/v1/rpc/auto_link_package_tracking', {
      method: 'POST', body: { p_user_id: userId ?? null },
    }));
  }

  /**
   * Writes a check's result over the parcel it read. True when it applied: the parcel's
   * `sync_revision` then follows the write, so the check's own writes never fence its next one.
   * False when the parcel was deleted or its carrier, number or inputs changed. Null when another
   * check wrote the parcel since this one read it: only the scans new to the parcel it read were
   * stored, and the newer write keeps the rest (migration 20261108140000).
   */
  async applyTrackingSync(
    parcel: JsonObject,
    values: JsonObject,
    events: JsonObject[] = [],
    deleteDescriptions: string[] = [],
    deleteEventIds: string[] = [],
    lease?: { jobId: string; workerId: string },
  ): Promise<boolean | null> {
    if (typeof parcel.tracking_generation !== 'string') {
      // Fail closed if a caller did not load the configuration snapshot.
      throw new TypeError('A tracking generation is required for synchronization');
    }
    const revision = parcel.sync_revision;
    if (typeof revision !== 'number' || !Number.isSafeInteger(revision)) {
      throw new TypeError('A sync revision is required for synchronization');
    }
    // A loader without the stored identities knows of no scan as new: an overtaken write keeps none.
    const stored = Array.isArray(parcel[STORED_EVENT_IDENTITIES])
      ? new Set(parcel[STORED_EVENT_IDENTITIES].flatMap((row) => isRecord(row) && typeof row.provider_event_id === 'string' ? [row.provider_event_id] : []))
      : null;
    const newEventIds = stored ? events.flatMap((event) => typeof event.provider_event_id === 'string'
      && !stored.has(event.provider_event_id) ? [event.provider_event_id] : []) : [];
    const applied = await this.request(`/rest/v1/rpc/${lease ? 'apply_leased_tracking_sync' : 'apply_tracking_sync'}`, {
      method: 'POST',
      body: {
        ...(lease ? { p_job_id: lease.jobId, p_worker_id: lease.workerId } : {}),
        p_package_id: parcel.id,
        p_tracking_generation: parcel.tracking_generation,
        p_values: values,
        p_events: events,
        p_delete_descriptions: deleteDescriptions,
        p_delete_event_ids: deleteEventIds,
        p_sync_revision: revision,
        p_new_event_ids: newEventIds,
      },
    });
    if (applied === true) {
      parcel.sync_revision = revision + 1;
      return true;
    }
    return applied === null ? null : false;
  }

  async startSyncAttempt(attemptId: string, values: JsonObject, lease?: { jobId: string; workerId: string }): Promise<void> {
    if (lease) {
      const started = await this.request('/rest/v1/rpc/start_leased_sync_attempt', {
        method: 'POST', body: { p_attempt_id: attemptId, p_job_id: lease.jobId, p_worker_id: lease.workerId, p_values: values },
      });
      if (started !== true) throw new SyncJobLeaseLost();
      return;
    }
    await this.request('/rest/v1/tracking_sync_attempts', {
      method: 'POST',
      body: { id: attemptId, ...values },
      prefer: 'return=minimal',
    });
  }

  async completeSyncAttempt(
    attemptId: string,
    values: JsonObject,
    steps: JsonObject[],
    options: { timeoutMs?: number } = {},
  ): Promise<boolean> {
    return await this.request('/rest/v1/rpc/complete_tracking_sync_attempt', {
      method: 'POST',
      timeoutMs: options.timeoutMs,
      body: {
        p_attempt_id: attemptId,
        p_values: values,
        p_steps: steps,
      },
    }) === true;
  }

  async recordTrackingHealth(attemptId: string, packageId: string, samples: JsonObject[]): Promise<JsonObject[]> {
    return rows(await this.request('/rest/v1/rpc/record_tracking_health', {
      method: 'POST', timeoutMs: 3_000,
      body: { p_attempt_id: attemptId, p_package_id: packageId, p_samples: samples },
    }));
  }

  async trackingProviderIncidentOpen(provider: string, options: { signal?: AbortSignal; timeoutMs?: number } = {}): Promise<boolean> {
    const params = query({ select: 'active', kind: 'eq.provider', subject: `eq.${provider}`, limit: '1' });
    const answer = await this.request(`/rest/v1/tracking_health_incidents?${params}`, { timeoutMs: 1_000, ...options });
    if (!Array.isArray(answer) || answer.length > 1 || (answer.length && typeof answer[0]?.active !== 'boolean')) {
      throw new TypeError('Unreadable tracking provider incident state');
    }
    return answer[0]?.active === true;
  }

  /**
   * The thin answer the parcel's latest scheduled refresh under `carrier` in the last 24 hours kept in its
   * health sample (trackingAudit.ts), if any. Samples are kept a week; the health rules read a day.
   */
  async previousThinAnswer(packageId: string, carrier: string): Promise<string | null> {
    const params = query({
      select: 'details', kind: 'eq.refresh', subject: `eq.${carrier}`, package_id: `eq.${packageId}`,
      observed_at: `gte.${new Date(Date.now() - 24 * 60 * 60_000).toISOString()}`,
      order: 'observed_at.desc,attempt_id.asc', limit: '1',
    });
    const details = rows(await this.request(`/rest/v1/tracking_health_samples?${params}`, { timeoutMs: 3_000 }))[0]?.details;
    return isRecord(details) && typeof details.thin_answer === 'string' ? details.thin_answer : null;
  }

  async recordTrackingSupportObservation(
    trackingNumber: string,
    context: JsonObject,
    evidence: JsonObject,
    observedAt: Date,
    observationKey: string,
  ): Promise<void> {
    await this.request('/rest/v1/rpc/record_tracking_support_observation', {
      method: 'POST', timeoutMs: 3_000,
      body: {
        p_tracking_number: trackingNumber, p_context: context, p_evidence: evidence,
        p_observed_at: observedAt.toISOString(), p_observation_key: observationKey,
      },
    });
  }

  /** A check's routing signals (observability.ts), kept for the review queues. */
  async recordTrackingRoutingSignals(
    attemptId: string, packageId: string, trackingNumber: string,
    signals: ReadonlyArray<{ signal: string; carrier: string; subject: string }>,
  ): Promise<void> {
    await this.request('/rest/v1/rpc/record_tracking_routing_signals', {
      method: 'POST', timeoutMs: 3_000,
      body: { p_attempt_id: attemptId, p_package_id: packageId, p_tracking_number: trackingNumber, p_signals: signals },
    });
  }

  async ackTrackingHealth(ids: string[]): Promise<void> {
    await this.request('/rest/v1/rpc/ack_tracking_health', {
      method: 'POST', timeoutMs: 3_000, body: { p_ids: ids },
    });
  }

  // Carrier wording whose stage the sync had to classify or fall back to. The
  // package and provider event ids resolve one sample event inside the
  // function; the stored row keeps only that opaque event id. The mapped keys
  // name wording the server's scraper mapped, which closes its open row.
  async recordTrackingStatusObservations(
    observations: JsonObject[],
    mappedKeys: string[] = [],
    version: string | null = null,
  ): Promise<void> {
    if (observations.length === 0 && mappedKeys.length === 0) return;
    await this.request('/rest/v1/rpc/record_tracking_status_observations', {
      method: 'POST',
      body: { p_observations: observations, p_mapped_keys: mappedKeys, p_version: version },
    });
  }

  /** The open status observations, the most recently seen first. */
  async listOpenTrackingStatusObservations(limit: number): Promise<JsonObject[]> {
    const params = query({
      select: 'observation_key,carrier,provider_code,description_normalized,chosen_stage,last_seen_version',
      reviewed_at: 'is.null',
      order: 'last_seen.desc',
      limit: String(limit),
    });
    return rows(await this.request(`/rest/v1/tracking_status_observations?${params}`));
  }

  /**
   * Closes the open observations among the keys with one resolution and note,
   * as this server version; answers how many it closed. A row reviewed or
   * closed meanwhile is left alone.
   */
  async closeTrackingStatusObservations(
    version: string,
    closing: { resolution: 'mapped' | 'ignored'; note: string; keys: readonly string[] },
  ): Promise<number> {
    // Observation keys are SHA-256 digests, which a filter list carries as they are.
    const keys = closing.keys.filter((key) => /^[0-9a-f]{64}$/.test(key));
    let closed = 0;
    for (let start = 0; start < keys.length; start += STATUS_OBSERVATION_KEYS_PER_REQUEST) {
      const params = query([
        ['observation_key', `in.(${keys.slice(start, start + STATUS_OBSERVATION_KEYS_PER_REQUEST).join(',')})`],
        ['reviewed_at', 'is.null'],
        ['select', 'observation_key'],
      ]);
      closed += rows(await this.request(`/rest/v1/tracking_status_observations?${params}`, {
        method: 'PATCH',
        body: {
          reviewed_at: new Date().toISOString(), resolution: closing.resolution,
          reviewed_version: version.slice(0, 100) || null, review_note: closing.note.slice(0, 500),
        },
        prefer: 'return=representation',
      })).length;
    }
    return closed;
  }

  /**
   * Expires the open observations of a carrier's wording that no sighting refreshed since
   * `before`, as this server version; answers how many. Wording a universal provider relays,
   * filed under the carrier `unknown`, is the provider's own and stays open.
   */
  async expireTrackingStatusObservations(version: string, before: Date): Promise<number> {
    const params = query([
      ['reviewed_at', 'is.null'],
      ['carrier', 'neq.unknown'],
      ['last_seen', `lt.${before.toISOString()}`],
      ['select', 'observation_key'],
    ]);
    return rows(await this.request(`/rest/v1/tracking_status_observations?${params}`, {
      method: 'PATCH',
      body: {
        reviewed_at: new Date().toISOString(), resolution: 'expired', reviewed_version: version.slice(0, 100) || null,
        review_note: 'No check showed this wording for 8 days: no followed parcel carries it any more.',
      },
      prefer: 'return=representation',
    })).length;
  }

  /** Whether this server is to replay the review queues for its version, another one is, or one did. */
  async claimTrackingReviewRun(version: string, leaseSeconds: number): Promise<'claimed' | 'running' | 'done'> {
    const state = await this.request('/rest/v1/rpc/claim_tracking_review_run', {
      method: 'POST', body: { p_version: version, p_lease_seconds: leaseSeconds },
    });
    if (state === 'claimed' || state === 'running' || state === 'done') return state;
    throw new SupabaseError('Supabase did not return the review run state');
  }

  async finishTrackingReviewRun(version: string, result: JsonObject): Promise<void> {
    await this.request('/rest/v1/rpc/finish_tracking_review_run', {
      method: 'POST', body: { p_version: version, p_result: result },
    });
  }

  /** The open support cases, the most recently seen first. */
  async listOpenTrackingSupportCases(limit: number): Promise<JsonObject[]> {
    const params = query({
      select: 'id,tracking_number,configured_carrier,direct_verified_carrier,reasons,last_outcome,last_seen,seen_count',
      fix_status: 'eq.open',
      order: 'last_seen.desc',
      limit: String(limit),
    });
    return rows(await this.request(`/rest/v1/tracking_support_cases?${params}`));
  }

  /** The stages of the saved parcels with these numbers, by number: a number can be saved more than once. */
  async trackingNumberStages(numbers: string[]): Promise<Map<string, string[]>> {
    const stages = new Map<string, string[]>();
    for (let start = 0; start < numbers.length; start += 100) {
      const page = numbers.slice(start, start + 100).map((number) => `"${number.replace(/["\\]/g, '')}"`);
      const params = query({ select: 'tracking_number,current_stage', tracking_number: `in.(${page.join(',')})` });
      for (const row of rows(await this.request(`/rest/v1/packages?${params}`))) {
        const number = String(row.tracking_number);
        stages.set(number, [...stages.get(number) ?? [], String(row.current_stage)]);
      }
    }
    return stages;
  }

  /** Whether the account saved a longer number that starts with this one: the user is still typing it. */
  async savedLongerTrackingNumber(userId: string, trackingNumber: string): Promise<boolean> {
    const params = query([
      ['select', 'id'],
      ['user_id', `eq.${userId}`],
      ['tracking_number', `like.${trackingNumber}*`],
      ['tracking_number', `neq.${trackingNumber}`],
      ['limit', '1'],
    ]);
    return rows(await this.request(`/rest/v1/packages?${params}`, { timeoutMs: 2_000 })).length > 0;
  }

  /** The saved parcels with these numbers, by number, as far as unseenLookup.ts reads them. */
  async trackingNumberParcels(numbers: string[]): Promise<Map<string, JsonObject[]>> {
    const parcels = new Map<string, JsonObject[]>();
    for (let start = 0; start < numbers.length; start += 100) {
      const page = numbers.slice(start, start + 100).map((number) => `"${number.replace(/["\\]/g, '')}"`);
      const params = query({
        select: 'tracking_number,one_off,carrier,current_stage,created_at,carrier_data,parcel_links(shared,parcel_link_alerts(owner))',
        tracking_number: `in.(${page.join(',')})`,
      });
      for (const row of rows(await this.request(`/rest/v1/packages?${params}`))) {
        const number = String(row.tracking_number);
        parcels.set(number, [...parcels.get(number) ?? [], row]);
      }
    }
    return parcels;
  }

  /**
   * Marks the replayed cases that need no change ignored, with their notes, while each still holds what replay read
   * of it (`revision`): a check that changed it since leaves it open for the next replay. Answers how many.
   */
  async ignoreReplayedTrackingSupportCases(
    version: string,
    cases: Array<{ id: string; note: string; revision: JsonObject }>,
  ): Promise<number> {
    let ignored = 0;
    for (const { id, note, revision } of cases) {
      const params = query([['id', `eq.${id}`], ['fix_status', 'eq.open'], ...SUPPORT_CASE_REVISION.map((field) => {
        const value = revision[field];
        if (value === null || value === undefined) return [field, 'is.null'] as const;
        if (Array.isArray(value)) return [field, `eq.{${value.map((item) => `"${String(item).replace(/["\\]/g, '\\$&')}"`).join(',')}}`] as const;
        return [field, `eq.${String(value)}`] as const;
      })]);
      ignored += rows(await this.request(`/rest/v1/tracking_support_cases?${params}`, {
        method: 'PATCH', prefer: 'return=representation',
        body: { fix_status: 'ignored', fix_reference: version.slice(0, 500), notes: note.slice(0, 4000) },
      })).length;
    }
    return ignored;
  }

  /**
   * Marks ignored the open cases on the start of this number that only detection saw, since
   * `since`: that number being typed before it was saved. Answers how many.
   */
  async ignoreTypedTrackingSupportCases(trackingNumber: string, since: Date, version: string, note: string): Promise<number> {
    const starts = Array.from({ length: Math.max(0, trackingNumber.length - 4) }, (_, index) => trackingNumber.slice(0, index + 4));
    if (!starts.length) return 0;
    const params = query([
      ['tracking_number', quotedList(starts)],
      ['fix_status', 'eq.open'],
      ['last_outcome', 'in.(detection_unknown,detection_choice)'],
      ['last_seen', `gte.${since.toISOString()}`],
    ]);
    return rows(await this.request(`/rest/v1/tracking_support_cases?${params}`, {
      method: 'PATCH', prefer: 'return=representation', timeoutMs: 2_000,
      body: { fix_status: 'ignored', fix_reference: version.slice(0, 500), notes: note.slice(0, 4000) },
    })).length;
  }

  /** Marks fixed the replayed cases still open with the same configured carrier; answers how many. */
  async fixReplayedTrackingSupportCases(
    version: string,
    cases: Array<{ id: string; configured_carrier: string | null; note: string }>,
  ): Promise<number> {
    return Number(await this.request('/rest/v1/rpc/fix_replayed_tracking_support_cases', {
      method: 'POST', body: { p_version: version, p_cases: cases },
    }) ?? 0);
  }

  async maintainSyncAudit(): Promise<{ abandoned: number; purged: number }> {
    const result = rows(await this.request('/rest/v1/rpc/maintain_tracking_sync_audit', {
      method: 'POST',
      body: {},
    }))[0];
    return {
      abandoned: Number(result?.abandoned ?? 0),
      purged: Number(result?.purged ?? 0),
    };
  }

  async enqueueSyncJob(options: {
    userId?: string | null;
    packageId?: string | null;
    scheduled?: boolean;
    /** A re-check nobody asked for, such as a scraper release's: it waits behind every other job. */
    background?: boolean;
    /** Why a reader's check is queued, which the check log records as its trigger. */
    reason?: Exclude<SyncReason, 'release'>;
  }): Promise<{ row: JsonObject; queued: boolean }> {
    const scheduled = options.scheduled ?? false;
    let kind: string;
    let dedupeKey: string;
    let priority: number;
    let reason: SyncReason | null = null;
    if (scheduled) {
      if (options.userId != null || options.packageId != null) {
        throw new TypeError('Scheduled sync jobs cannot target an account');
      }
      kind = 'scheduled';
      dedupeKey = 'scheduled';
      priority = SCHEDULED_SYNC_PRIORITY;
    } else {
      if (!options.packageId) throw new TypeError('Package sync jobs require a package');
      kind = 'package';
      dedupeKey = `package:${options.packageId}`;
      // A one-off parcel has no owner. Its check waits behind every account's own check and goes
      // ahead of the scheduled run, accounts' parcels' scheduled checks included.
      priority = options.background ? BACKGROUND_SYNC_PRIORITY : options.userId ? ACCOUNT_SYNC_PRIORITY : LOOKUP_SYNC_PRIORITY;
      reason = options.background ? 'release' : options.reason ?? null;
    }

    const params = query({
      // The reason too: a reader's check takes over one a link queued (below).
      select: 'id,user_id,package_id,kind,state,priority,requested_at,reason',
      dedupe_key: `eq.${dedupeKey}`,
      limit: '1',
    });
    let existing: JsonObject[] = [];
    // The job in the way may finish between the two requests, which frees its key: queue again.
    for (let attempt = 0; attempt < 2 && !existing[0]; attempt += 1) {
      const created = rows(await this.request('/rest/v1/sync_jobs?on_conflict=dedupe_key', {
        method: 'POST',
        body: {
          user_id: options.userId ?? null,
          package_id: options.packageId ?? null,
          kind,
          dedupe_key: dedupeKey,
          priority,
          ...(kind === 'package' ? { reason } : {}),
        },
        prefer: 'resolution=ignore-duplicates,return=representation',
      }));
      if (created[0]) return { row: created[0], queued: true };
      existing = rows(await this.request(`/rest/v1/sync_jobs?${params}`));
    }
    if (!existing[0]) throw new SupabaseError('The durable sync job could not be queued');
    // Asking for a check already queued behind others, such as a release's re-check, moves it up.
    // A reader's check takes over one a link queued, which is dropped once the parcel is not due.
    const claims = reason !== null && reason !== 'opened' && existing[0].reason === 'opened';
    if (existing[0].state === 'queued' && (Number(existing[0].priority) < priority || claims)) {
      const raised = rows(await this.request(`/rest/v1/sync_jobs?${query({ id: `eq.${existing[0].id}`, state: 'eq.queued' })}`, {
        method: 'PATCH', body: { priority: Math.max(priority, Number(existing[0].priority)), ...(claims ? { reason } : {}) },
        prefer: 'return=representation',
      }));
      if (raised[0]) return { row: raised[0], queued: false };
    }
    return { row: existing[0], queued: false };
  }

  async claimSyncJob(workerId: string, leaseSeconds = 90): Promise<JsonObject | null> {
    const result = rows(await this.request('/rest/v1/rpc/claim_sync_job', {
      method: 'POST', timeoutMs: 3_000,
      body: { p_worker_id: workerId, p_lease_seconds: leaseSeconds },
    }));
    return result[0] ?? null;
  }

  /** Claims the next job of the readers' lane (READER_SYNC_REASONS), or one whose worker stopped. */
  async claimReaderSyncJob(workerId: string, leaseSeconds = 90): Promise<JsonObject | null> {
    const result = rows(await this.request('/rest/v1/rpc/claim_reader_sync_job', {
      method: 'POST', timeoutMs: 3_000,
      body: { p_worker_id: workerId, p_lease_seconds: leaseSeconds },
    }));
    return result[0] ?? null;
  }

  async renewSyncJobLease(jobId: string, workerId: string, leaseSeconds = 90): Promise<boolean> {
    return await this.request('/rest/v1/rpc/renew_sync_job_lease', {
      method: 'POST', body: { p_job_id: jobId, p_worker_id: workerId, p_lease_seconds: leaseSeconds },
    }) === true;
  }

  async releaseSyncJob(jobId: string, workerId: string): Promise<boolean> {
    return await this.request('/rest/v1/rpc/release_sync_job', {
      method: 'POST', timeoutMs: 3_000, body: { p_job_id: jobId, p_worker_id: workerId },
    }) === true;
  }

  /** Takes a lease on work one server does at a time, or extends it for its owner: false while another owner holds it. */
  async claimWorkerLease(name: string, owner: string, seconds: number): Promise<boolean> {
    return await this.request('/rest/v1/rpc/claim_worker_lease', {
      method: 'POST', timeoutMs: 3_000, body: { p_name: name, p_owner: owner, p_seconds: seconds },
    }) === true;
  }

  /** Extends a lease its owner still holds: false once it ran out or another owner took it. */
  async renewWorkerLease(name: string, owner: string, seconds: number): Promise<boolean> {
    return await this.request('/rest/v1/rpc/renew_worker_lease', {
      method: 'POST', timeoutMs: 3_000, body: { p_name: name, p_owner: owner, p_seconds: seconds },
    }) === true;
  }

  /** Gives a lease up, when this owner holds it. */
  async releaseWorkerLease(name: string, owner: string): Promise<boolean> {
    return await this.request('/rest/v1/rpc/release_worker_lease', {
      method: 'POST', timeoutMs: 3_000, body: { p_name: name, p_owner: owner },
    }) === true;
  }

  async setSyncJobCheckIn(jobId: string, workerId: string, checkIn: { checkInId: string; monitorSlug: string; startedAt: number }): Promise<void> {
    const saved = await this.request('/rest/v1/rpc/set_sync_job_check_in', {
      method: 'POST', body: { p_job_id: jobId, p_worker_id: workerId, p_check_in: checkIn },
    });
    if (saved !== true) throw new SyncJobLeaseLost();
  }

  async finishSyncJob(
    jobId: string,
    workerId: string,
    options: { result?: JsonObject | null; error?: string | null },
  ): Promise<void> {
    const finished = await this.request('/rest/v1/rpc/finish_sync_job', {
      method: 'POST', body: { p_job_id: jobId, p_worker_id: workerId,
        p_result: options.result ?? null, p_error: options.error?.slice(0, 500) ?? null },
    });
    if (finished !== true) throw new SyncJobLeaseLost();
  }

  /**
   * The highest priority of the jobs that go ahead of `priority` and wait to be claimed, such as an
   * account's refresh or a lookup's check during a scheduled run; null when none waits. Without
   * `readers`, the jobs the readers' lane takes are left out.
   */
  async syncJobQueuedAhead(priority: number, readers = true): Promise<number | null> {
    const params = query([
      ['select', 'priority'],
      ['state', 'eq.queued'],
      ['priority', `gt.${priority}`],
      ['run_after', `lte.${new Date().toISOString()}`],
      ...readers ? [] : [['or', `(reason.is.null,reason.not.in.(${READER_SYNC_REASONS.join(',')}))`] as [string, string]],
      ['order', 'priority.desc'],
      ['limit', '1'],
    ]);
    const ahead = rows(await this.request(`/rest/v1/sync_jobs?${params}`, { timeoutMs: 3_000 }))[0];
    return ahead ? Number(ahead.priority) : null;
  }

  /**
   * The checks waiting for a worker, by whose, with how long the oldest has waited since it was
   * queued. The database counts every queued job before returning the four groups.
   */
  async syncQueue(now = new Date()): Promise<SyncQueue> {
    const queue: SyncQueue = {
      account: { queued: 0, oldestSeconds: 0 },
      lookup: { queued: 0, oldestSeconds: 0 },
      scheduled: { queued: 0, oldestSeconds: 0 },
      release: { queued: 0, oldestSeconds: 0 },
    };
    for (const group of rows(await this.request('/rest/v1/rpc/sync_queue_counts', { method: 'POST', timeoutMs: 3_000 }))) {
      const kind = group.kind;
      if (kind !== 'account' && kind !== 'lookup' && kind !== 'scheduled' && kind !== 'release') continue;
      queue[kind] = { queued: Number(group.queued), oldestSeconds: Math.max(0, Math.round((now.getTime() - Date.parse(String(group.oldest))) / 1000)) };
    }
    return queue;
  }

  /** When the last scheduled run finished, however it ended; null when none has. */
  async lastScheduledRunFinishedAt(): Promise<Date | null> {
    const params = query([
      ['select', 'completed_at'],
      ['kind', 'eq.scheduled'],
      ['completed_at', 'not.is.null'],
      ['order', 'completed_at.desc'],
      ['limit', '1'],
    ]);
    const finished = Date.parse(String(rows(await this.request(`/rest/v1/sync_jobs?${params}`, { timeoutMs: 3_000 }))[0]?.completed_at ?? ''));
    return Number.isFinite(finished) ? new Date(finished) : null;
  }

  /**
   * The scheduled runs failed since `since` because their worker stopped three times,
   * with the Sentry check-in each left open. Taking one clears its check-in, so each is
   * handed out once, whichever worker asks.
   */
  async takeLostScheduledRuns(since: Date): Promise<LostScheduledRun[]> {
    const params = query([
      ['kind', 'eq.scheduled'],
      ['state', 'eq.failed'],
      ['last_error', `eq.${LOST_SYNC_JOB_ERROR}`],
      ['completed_at', `gte.${since.toISOString()}`],
      ['check_in', 'not.is.null'],
      ['select', 'id,attempts,check_in'],
    ]);
    const taken = rows(await this.request(`/rest/v1/sync_jobs?${params}`, {
      method: 'PATCH', body: { check_in: null }, prefer: 'return=representation', timeoutMs: 3_000,
    }));
    return taken.flatMap((job) => {
      const checkIn = isRecord(job.check_in) ? job.check_in : {};
      return typeof job.id === 'string' && typeof checkIn.checkInId === 'string'
        && typeof checkIn.monitorSlug === 'string' && typeof checkIn.startedAt === 'number'
        ? [{ id: job.id, attempts: Number(job.attempts ?? 0),
          checkIn: { checkInId: checkIn.checkInId, monitorSlug: checkIn.monitorSlug, startedAt: checkIn.startedAt } }]
        : [];
    });
  }

  /**
   * Whether a job was taken from its worker on purpose: its row went with its
   * parcel or account, or a carrier change ended it as superseded. A lease that
   * expired, or that another worker took, is not withdrawn.
   */
  async syncJobWithdrawn(jobId: string): Promise<boolean> {
    const params = query({ select: 'state,last_error', id: `eq.${jobId}`, limit: '1' });
    const job = rows(await this.request(`/rest/v1/sync_jobs?${params}`, { timeoutMs: 3_000 }))[0];
    if (!job) return true;
    return job.state === 'failed' && typeof job.last_error === 'string' && job.last_error.startsWith('Superseded');
  }

  async probeReadiness(): Promise<boolean> {
    const result = await this.request('/rest/v1/sync_jobs?select=id,check_in&limit=0', { timeoutMs: 2_500 });
    return Array.isArray(result);
  }

  async getSyncJob(jobId: string, userId: string): Promise<JsonObject | null> {
    const params = query({
      select: 'id,user_id,package_id,state,requested_at,started_at,completed_at,result,last_error',
      id: `eq.${jobId}`,
      user_id: `eq.${userId}`,
      limit: '1',
    });
    return rows(await this.request(`/rest/v1/sync_jobs?${params}`))[0] ?? null;
  }

  async getSyncJobs(jobIds: string[], userId: string): Promise<JsonObject[]> {
    const params = query({
      select: 'id,package_id,state,requested_at,started_at,completed_at,result,last_error',
      id: `in.(${jobIds.join(',')})`,
      user_id: `eq.${userId}`,
      limit: '20',
    });
    return rows(await this.request(`/rest/v1/sync_jobs?${params}`));
  }

  /**
   * Counts one use without an account against today's allowances: the
   * client's, its network's when it has one, and everyone's together. Every
   * bucket but the overall one is a keyed hash. `scope` says which allowance
   * ran out, and `overallUsed` how much of the overall one is used.
   */
  async claimPublicAllowance(claim: PublicAllowanceClaim): Promise<PublicAllowance> {
    const result = await this.request('/rest/v1/rpc/claim_public_lookup', {
      method: 'POST',
      body: {
        p_bucket: claim.bucket,
        p_limit: claim.limit,
        p_global_bucket: claim.overall.bucket,
        p_global_limit: claim.overall.limit,
        ...(claim.network ? { p_network_bucket: claim.network.bucket, p_network_limit: claim.network.limit } : {}),
      },
    });
    if (!isRecord(result) || typeof result.allowed !== 'boolean') throw new SupabaseError('Supabase did not return the lookup allowance');
    return {
      allowed: result.allowed,
      scope: result.scope === 'bucket' || result.scope === 'network' || result.scope === 'global' ? result.scope : null,
      overallUsed: Number(result.overall ?? 0),
      ...(typeof result.network === 'number' ? { networkUsed: result.network } : {}),
    };
  }

  /**
   * Whether this server is the first to report an overall allowance's state for its UTC day
   * (`2026-11-05`) or hour (`2026-11-05T10`); `report` is `holder:kind:state`.
   */
  async claimAllowanceReport(report: string, period: string): Promise<boolean> {
    const result = await this.request('/rest/v1/rpc/claim_allowance_report', {
      method: 'POST',
      body: { p_report: report, p_period: period },
    });
    if (typeof result !== 'boolean') throw new SupabaseError('Supabase did not say whether to report the allowance');
    return result;
  }

  /**
   * Counts one use by an account against this hour's allowance for every
   * account and the account's own for today (UTC). `scope` says which ran out,
   * and `overallUsed` how much of the hour's is used.
   */
  async claimAccountTracking(
    userId: string,
    kind: 'lookup' | 'detection',
    limits: { perAccount: number; overall: number },
  ): Promise<AccountAllowance> {
    const result = await this.request('/rest/v1/rpc/claim_account_tracking', {
      method: 'POST',
      body: { p_user_id: userId, p_kind: kind, p_limit: limits.perAccount, p_overall_limit: limits.overall },
    });
    if (!isRecord(result) || typeof result.allowed !== 'boolean') throw new SupabaseError('Supabase did not return the tracking allowance');
    return {
      allowed: result.allowed,
      scope: result.scope === 'overall' || result.scope === 'account' ? result.scope : null,
      overallUsed: Number(result.overall ?? 0),
    };
  }

  /**
   * Stores a lookup: a new link to a new or already stored one-off parcel.
   * Answers like publicParcel, plus whether the parcel is new.
   */
  async createOneOffParcel(
    values: { trackingNumber: string; carrier: string; trackingUrl: string | null; dpdPostcode: string | null; phoneDigits?: string | null;
      dpdPostcodeCountry?: string | null },
    ownerKeyHash: string,
  ): Promise<{ link: JsonObject; package: JsonObject; created: boolean }> {
    const result = await this.request('/rest/v1/rpc/create_one_off_parcel', {
      method: 'POST',
      body: {
        p_tracking_number: values.trackingNumber,
        p_carrier: values.carrier,
        p_tracking_url: values.trackingUrl,
        p_dpd_postcode: values.dpdPostcode,
        p_owner_key_hash: ownerKeyHash,
        ...(values.phoneDigits ? { p_phone_digits: values.phoneDigits } : {}),
        // A parcel is shared only with lookups whose postcode is from the same country, and stored with it.
        ...(values.dpdPostcode && values.dpdPostcodeCountry ? { p_postcode_country: values.dpdPostcodeCountry } : {}),
      },
    });
    if (!isRecord(result) || !isRecord(result.link) || !isRecord(result.package)) {
      throw new SupabaseError('Supabase did not return the new parcel link');
    }
    return { link: result.link, package: result.package, created: result.created === true };
  }

  /**
   * A parcel link, the role its caller has and the package's columns the server
   * reads (parcel_link_view names them, never the whole row) with its events, each
   * with the fields an account's events carry (`PACKAGE_SELECT`). Null when the link is unknown or past its forget date, and
   * `stopped` when its sharing was stopped and the caller does not hold the
   * owner key. The link id and key hash travel in the body, never in a logged
   * URL. `touch` records that the link was opened.
   */
  async publicParcel(linkId: string, ownerKeyHash: string | null, touch: boolean): Promise<StoredParcelLink | 'stopped' | null> {
    const result = await this.request('/rest/v1/rpc/parcel_link_view', {
      method: 'POST', body: { p_link_id: linkId, p_owner_key_hash: ownerKeyHash, p_touch: touch },
    });
    if (result === null) return null;
    if (isRecord(result) && result.stopped === true) return 'stopped';
    if (!isRecord(result) || !isRecord(result.link) || !isRecord(result.package)) {
      throw new SupabaseError('Supabase did not return the parcel link');
    }
    return { link: result.link, package: result.package };
  }

  /**
   * Changes what a lookup's link shows, or stops and resumes its sharing, for
   * the holder of its owner key. Null for an unknown link and for a wrong key
   * alike. `transition` says whether the sharing stopped or resumed.
   */
  async updateParcelLink(
    linkId: string,
    ownerKeyHash: string,
    changes: { showNumber?: boolean; gift?: boolean; shared?: boolean; giftWords?: ParcelShare['giftWords'] },
  ): Promise<StoredParcelLink & { transition: 'stopped' | 'started' | null } | null> {
    const result = await this.request('/rest/v1/rpc/update_parcel_link', {
      method: 'POST',
      body: {
        p_link_id: linkId,
        p_owner_key_hash: ownerKeyHash,
        p_show_number: changes.showNumber ?? null,
        p_gift: changes.gift ?? null,
        p_shared: changes.shared ?? null,
        p_gift_words: changes.giftWords ?? null,
      },
    });
    if (result === null) return null;
    if (!isRecord(result) || !isRecord(result.link) || !isRecord(result.package)) {
      throw new SupabaseError('Supabase did not return the parcel link');
    }
    return {
      link: result.link,
      package: result.package,
      transition: result.transition === 'stopped' || result.transition === 'started' ? result.transition : null,
    };
  }

  /**
   * Counts a delivery postcode about to be sent to the carrier a lookup waits
   * for, for the holder of its owner key. A link has `limit` tries a day.
   * Answers the try and the number to send it with, when the next try is
   * allowed, `not_asked` when the lookup waits for no postcode from that
   * carrier, or null for an unknown link and a wrong key alike.
   */
  async claimParcelLinkPostcode(
    linkId: string,
    ownerKeyHash: string,
    carrier: string,
    limit: number,
  ): Promise<{ attempt: string; trackingNumber: string } | { retryAt: string } | 'not_asked' | null> {
    const result = await this.request('/rest/v1/rpc/claim_parcel_link_postcode', {
      method: 'POST', body: { p_link_id: linkId, p_owner_key_hash: ownerKeyHash, p_carrier: carrier, p_limit: limit },
    });
    if (result === null) return null;
    if (isRecord(result) && result.asked === false) return 'not_asked';
    if (isRecord(result) && typeof result.attempt === 'string' && typeof result.tracking_number === 'string') {
      return { attempt: result.attempt, trackingNumber: result.tracking_number };
    }
    if (isRecord(result) && typeof result.retry_at === 'string') return { retryAt: result.retry_at };
    throw new SupabaseError('Supabase did not return the postcode try');
  }

  /** Gives back a postcode try the carrier did not refuse. */
  async releaseParcelLinkPostcode(attempt: string): Promise<void> {
    await this.request('/rest/v1/rpc/release_parcel_link_postcode', { method: 'POST', body: { p_attempt_id: attempt } });
  }

  /**
   * Files a lookup under the carrier that accepted its delivery postcode: the
   * link moves to the one-off parcel with that carrier and postcode, found or
   * new. Answers like publicParcel, plus whether that parcel is new; null for
   * an unknown link and a wrong key, `not_asked` as claimParcelLinkPostcode.
   */
  async fileParcelLinkPostcode(
    linkId: string,
    ownerKeyHash: string,
    carrier: string,
    dpdPostcode: string,
    postcodeCountry?: string | null,
  ): Promise<StoredParcelLink & { created: boolean } | 'not_asked' | null> {
    const result = await this.request('/rest/v1/rpc/file_parcel_link_postcode', {
      method: 'POST',
      body: { p_link_id: linkId, p_owner_key_hash: ownerKeyHash, p_carrier: carrier, p_dpd_postcode: dpdPostcode,
        ...(postcodeCountry ? { p_postcode_country: postcodeCountry } : {}) },
    });
    if (result === null) return null;
    if (isRecord(result) && result.asked === false) return 'not_asked';
    if (!isRecord(result) || !isRecord(result.link) || !isRecord(result.package)) {
      throw new SupabaseError('Supabase did not return the filed parcel link');
    }
    return { link: result.link, package: result.package, created: result.created === true };
  }

  /**
   * Changes a lookup's carrier, or the inputs that carrier takes, on the same
   * link, for the holder of its owner key: the link moves to the one-off parcel
   * of its number with those details, found or new, and the parcel it leaves
   * goes once no link reaches it. Answers like publicParcel, plus whether that
   * parcel is new and whether the link moved; null for an unknown link and a
   * wrong key alike.
   */
  async changeParcelLinkCarrier(
    linkId: string,
    ownerKeyHash: string,
    values: { carrier: string; trackingUrl: string | null; dpdPostcode: string | null; phoneDigits: string | null;
      dpdPostcodeCountry: string | null },
  ): Promise<StoredParcelLink & { created: boolean; changed: boolean } | null> {
    const result = await this.request('/rest/v1/rpc/change_parcel_link_carrier', {
      method: 'POST',
      body: {
        p_link_id: linkId,
        p_owner_key_hash: ownerKeyHash,
        p_carrier: values.carrier,
        p_tracking_url: values.trackingUrl,
        p_dpd_postcode: values.dpdPostcode,
        p_phone_digits: values.phoneDigits,
        p_postcode_country: values.dpdPostcode ? values.dpdPostcodeCountry : null,
      },
    });
    if (result === null) return null;
    if (!isRecord(result) || !isRecord(result.link) || !isRecord(result.package)) {
      throw new SupabaseError('Supabase did not return the changed parcel link');
    }
    return { link: result.link, package: result.package, created: result.created === true, changed: result.changed === true };
  }

  /**
   * Turns on an alert for a link, or updates the one its browser has. `full`
   * when the link has ten already, `finished` when the journey is over and
   * nothing was stored, `stopped` like publicParcel, null for an unknown link.
   */
  async addParcelLinkAlert(
    linkId: string,
    ownerKeyHash: string | null,
    alert: { endpoint: string; p256dh: string; auth: string; locale: string; preset: string },
  ): Promise<'added' | 'updated' | 'full' | 'finished' | 'stopped' | null> {
    const result = await this.request('/rest/v1/rpc/add_parcel_link_alert', {
      method: 'POST',
      body: {
        p_link_id: linkId,
        p_owner_key_hash: ownerKeyHash,
        p_endpoint: alert.endpoint,
        p_p256dh: alert.p256dh,
        p_auth: alert.auth,
        p_locale: alert.locale,
        p_preset: alert.preset,
      },
    });
    if (result === null) return null;
    if (result === 'added' || result === 'updated' || result === 'full' || result === 'finished' || result === 'stopped') return result;
    throw new SupabaseError('Supabase did not return the alert');
  }

  /**
   * Turns on an alert in the iPhone or Android app for a link, or updates the one its phone has.
   * Answers as addParcelLinkAlert.
   */
  async addParcelLinkAppAlert(
    linkId: string,
    ownerKeyHash: string | null,
    alert: {
      token: string; environment: string; locale: string; preset: string; transport?: NativePushTransport; webPush?: WebPushKeys;
    },
  ): Promise<'added' | 'updated' | 'full' | 'finished' | 'stopped' | null> {
    const result = await this.request('/rest/v1/rpc/add_parcel_link_app_alert', {
      method: 'POST',
      body: {
        p_link_id: linkId,
        p_owner_key_hash: ownerKeyHash,
        p_token: alert.token,
        p_environment: alert.environment,
        p_locale: alert.locale,
        p_preset: alert.preset,
        p_transport: alert.transport ?? 'apns',
        ...alert.webPush ? { p_endpoint: alert.webPush.endpoint, p_p256dh: alert.webPush.p256dh, p_auth: alert.webPush.auth } : {},
      },
    });
    if (result === null) return null;
    if (result === 'added' || result === 'updated' || result === 'full' || result === 'finished' || result === 'stopped') return result;
    throw new SupabaseError('Supabase did not return the alert');
  }

  /** Turns a phone's alert for a link off, whichever service its token is for; false when there was none. */
  async removeParcelLinkAppAlert(linkId: string, token: string): Promise<boolean> {
    return await this.request('/rest/v1/rpc/remove_parcel_link_app_alert', {
      method: 'POST', body: { p_link_id: linkId, p_token: token },
    }) === true;
  }

  /** Puts a notice in a phone's inbox, in place of the one waiting with the same collapse key. */
  async putPushInbox(token: string, collapseKey: string, data: Record<string, string>, deviceId: string | null): Promise<void> {
    await this.request('/rest/v1/rpc/put_push_inbox', {
      method: 'POST', body: { p_token: token, p_collapse_key: collapseKey, p_data: data, p_device_id: deviceId },
    });
  }

  /** The notices waiting in a phone's inbox, oldest first, removed as they are read. */
  async takePushInbox(token: string): Promise<Record<string, string>[]> {
    const result = await this.request('/rest/v1/rpc/take_push_inbox', { method: 'POST', body: { p_token: token } });
    if (!Array.isArray(result)) throw new SupabaseError('Supabase did not return the inbox');
    return result.filter(isRecord).map((message) => Object.fromEntries(
      Object.entries(message).filter((entry): entry is [string, string] => typeof entry[1] === 'string'),
    ));
  }

  /** Forgets the inbox notices no phone came for within their day; answers how many. */
  async forgetExpiredPushInbox(): Promise<number> {
    return Number(await this.request('/rest/v1/rpc/forget_expired_push_inbox', { method: 'POST', body: {} }) ?? 0);
  }

  /** Turns a browser's alert for a link off; false when there was none. */
  async removeParcelLinkAlert(linkId: string, endpoint: string): Promise<boolean> {
    return await this.request('/rest/v1/rpc/remove_parcel_link_alert', {
      method: 'POST', body: { p_link_id: linkId, p_endpoint: endpoint },
    }) === true;
  }

  /**
   * The scans no link alert has handled yet, one row per alert and scan, with the alert's push credentials:
   * a browser's (`endpoint`), an iPhone's (`apns_token`) or an Android phone's (`fcm_token`, or `app_token` with
   * `app_transport` and a UnifiedPush endpoint and keys), only those of the channels named.
   */
  async listPendingParcelLinkAlerts(
    channels: Record<'web' | 'apns' | 'fcm' | 'unifiedpush' | 'inbox', boolean> = {
      web: true, apns: true, fcm: true, unifiedpush: true, inbox: true,
    },
  ): Promise<JsonObject[]> {
    const named = ([
      ['web', 'endpoint.not.is.null'], ['apns', 'apns_token.not.is.null'], ['fcm', 'fcm_token.not.is.null'],
      ['unifiedpush', 'app_transport.eq.unifiedpush'], ['inbox', 'app_transport.eq.inbox'],
    ] as const).filter(([channel]) => channels[channel]).map(([, filter]) => filter);
    if (named.length === 0) return [];
    const only: Record<string, string> = named.length === 5 ? {} : { or: `(${named.join(',')})` };
    const params = query({ select: '*', ...only, order: 'event_created_at.asc', limit: '1000' });
    return rows(await this.request(`/rest/v1/pending_parcel_link_alerts?${params}`));
  }

  /**
   * The parcels of these links that hide the number from their viewers, by link: each with its number and its carrier
   * data, where the numbers its alerts must not tell are (hiddenNumbers.ts). A link that shows its number is left out.
   */
  async numberHidingParcels(linkIds: string[]): Promise<Map<string, JsonObject>> {
    const ids = [...new Set(linkIds)];
    const pages = Array.from({ length: Math.ceil(ids.length / 100) }, (_, page) => ids.slice(page * 100, page * 100 + 100));
    const found = await Promise.all(pages.map(async (page) => rows(await this.request(`/rest/v1/parcel_links?${query([
      ['id', `in.(${page.join(',')})`],
      ['show_number', 'is.false'],
      // The carrier and stage find the planned steps the page masks numbers from.
      ['select', 'id,package:packages(tracking_number,carrier,current_stage,carrier_data,last_status_text,tracking_events(description,location))'],
    ])}`))));
    return new Map(found.flat().flatMap((row) => typeof row.id === 'string' && isRecord(row.package) ? [[row.id, row.package]] : []));
  }

  /** These fields of each link's parcel's carrier data: the recipient details a viewer's alert names in no scan's place. */
  async linkRecipientDetails(linkIds: string[], keys: readonly string[]): Promise<Map<string, JsonObject>> {
    const ids = [...new Set(linkIds)];
    const pages = Array.from({ length: Math.ceil(ids.length / 100) }, (_, page) => ids.slice(page * 100, page * 100 + 100));
    const fields = keys.map((key) => `${key}:carrier_data->${key}`).join(',');
    const found = await Promise.all(pages.map(async (page) => rows(await this.request(`/rest/v1/parcel_links?${query([
      ['id', `in.(${page.join(',')})`],
      ['select', `id,package:packages(${fields})`],
    ])}`))));
    return new Map(found.flat().flatMap((row) => typeof row.id === 'string' && isRecord(row.package) ? [[row.id, row.package]] : []));
  }

  async recordParcelLinkAlertDeliveries(alertId: string, eventIds: string[]): Promise<void> {
    if (eventIds.length === 0) return;
    await this.request(`/rest/v1/parcel_link_alert_deliveries?${query({ on_conflict: 'alert_id,event_id' })}`, {
      method: 'POST',
      body: eventIds.map((eventId) => ({ alert_id: alertId, event_id: eventId })),
      prefer: 'resolution=ignore-duplicates,return=minimal',
    });
  }

  async setParcelLinkAlertFailures(alertId: string, failures: number): Promise<void> {
    await this.request(`/rest/v1/parcel_link_alerts?${query({ id: `eq.${alertId}` })}`, {
      method: 'PATCH', body: { failures }, prefer: 'return=minimal',
    });
  }

  async deleteParcelLinkAlert(alertId: string): Promise<void> {
    await this.request(`/rest/v1/parcel_link_alerts?${query({ id: `eq.${alertId}` })}`, {
      method: 'DELETE', prefer: 'return=minimal',
    });
  }

  /** Forgets a lookup when the key hash matches; a one-off parcel goes with its last link. */
  async forgetParcelLink(linkId: string, ownerKeyHash: string): Promise<{ links: number; packages: number }> {
    return forgotten(await this.request('/rest/v1/rpc/forget_parcel_link', {
      method: 'POST', body: { p_link_id: linkId, p_owner_key_hash: ownerKeyHash },
    }));
  }

  /**
   * Keeps what a reader said of a parcel, or replaces the words of an answer
   * given under the same id within the hour. `full` when the parcel had its
   * day's answers, `closed` when the id belongs to another parcel or answer.
   */
  async recordParcelFeedback(feedback: JsonObject): Promise<'stored' | 'replaced' | 'full' | 'closed'> {
    const result = await this.request('/rest/v1/rpc/record_parcel_feedback', {
      method: 'POST', body: { p_feedback: feedback },
    });
    if (result !== 'stored' && result !== 'replaced' && result !== 'full' && result !== 'closed') {
      throw new SupabaseError('Supabase did not record the parcel feedback');
    }
    return result;
  }

  /** Deletes the answers readers gave more than 90 days ago. Returns how many. */
  async forgetOldParcelFeedback(): Promise<number> {
    return Number(await this.request('/rest/v1/rpc/forget_old_parcel_feedback', { method: 'POST', body: {} }) ?? 0);
  }

  /**
   * Deletes the support cases and routing signals of numbers no parcel has followed for 10
   * days, last checked 10 days ago. Returns how many of each.
   */
  async forgetUnfollowedTrackingReview(): Promise<{ cases: number; signals: number }> {
    const result = await this.request('/rest/v1/rpc/forget_unfollowed_tracking_review', { method: 'POST', body: {} });
    const counts = isRecord(result) ? result : {};
    return { cases: Number(counts.cases ?? 0), signals: Number(counts.signals ?? 0) };
  }

  /** Rewrites today's counts for the usage dashboard, and the additions of the day before. */
  async recordUsageMetrics(): Promise<void> {
    await this.request('/rest/v1/rpc/record_usage_metrics', { method: 'POST', body: {} });
  }

  /** Counts an install as seen today, by the keyed hash of its counting id. */
  async recordInstallSeen(install: string, platform: string, signedIn: boolean, parcels: number): Promise<void> {
    await this.request('/rest/v1/rpc/record_install_seen', {
      method: 'POST',
      body: { p_install: install, p_platform: platform, p_signed_in: signedIn, p_parcels: parcels },
    });
  }

  /**
   * The DPD app sessions DPD has not refused, newest first: the scraper takes up
   * the newest, whatever its age, and checks the others for a week after they opened.
   */
  async dpdAppSessions(): Promise<DpdSession[]> {
    return rows(await this.request(`/rest/v1/dpd_app_sessions?${query([
      ['select', 'token,opened_at,checked_at'],
      ['refused_at', 'is.null'],
      ['order', 'opened_at.desc'],
      ['limit', '50'],
    ])}`)).flatMap((row) => {
      const openedAt = Date.parse(String(row.opened_at));
      const checkedAt = typeof row.checked_at === 'string' ? Date.parse(row.checked_at) : Number.NaN;
      if (typeof row.token !== 'string' || !Number.isFinite(openedAt)) return [];
      return [{ token: row.token, openedAt, ...(Number.isFinite(checkedAt) ? { checkedAt } : {}) }];
    });
  }

  /** Saves a DPD app session, replacing what was saved under its token. */
  async saveDpdAppSession(session: DpdSession): Promise<void> {
    const time = (ms: number | undefined) => ms === undefined ? null : new Date(ms).toISOString();
    await this.request(`/rest/v1/dpd_app_sessions?${query({ on_conflict: 'token' })}`, {
      method: 'POST',
      body: [{ token: session.token, opened_at: time(session.openedAt), checked_at: time(session.checkedAt), refused_at: time(session.refusedAt) }],
      prefer: 'resolution=merge-duplicates,return=minimal',
    });
  }

  /** The scraper adapters' holds still running. */
  /** The carriers whose own site is down now, each with the time it went down (carrier_outages, carrierOutages.ts). */
  async carrierOutages(): Promise<Map<string, string>> {
    const result = await this.request('/rest/v1/rpc/carrier_outages', { method: 'POST', body: {} });
    return new Map(rows(result).flatMap((row) => {
      const since = Date.parse(String(row.since));
      return typeof row.carrier === 'string' && Number.isFinite(since) ? [[row.carrier, new Date(since).toISOString()]] : [];
    }));
  }

  async adapterHolds(): Promise<AdapterHold[]> {
    return rows(await this.request(`/rest/v1/scraper_holds?${query([
      ['select', 'key,until,value'],
      ['until', `gt.${new Date().toISOString()}`],
      ['limit', '10000'],
    ])}`)).flatMap((row) => {
      const until = Date.parse(String(row.until));
      if (typeof row.key !== 'string' || !Number.isFinite(until)) return [];
      return [{ key: row.key, until, ...(row.value !== null && row.value !== undefined ? { value: row.value } : {}) }];
    });
  }

  /** Saves an adapter's hold, replacing what was saved under its key. */
  async saveAdapterHold(hold: AdapterHold): Promise<void> {
    await this.request(`/rest/v1/scraper_holds?${query({ on_conflict: 'key' })}`, {
      method: 'POST',
      body: [{ key: hold.key, until: new Date(hold.until).toISOString(), value: hold.value ?? null }],
      prefer: 'resolution=merge-duplicates,return=minimal',
    });
  }

  /** Deletes the adapters' holds that ended. */
  async dropEndedAdapterHolds(): Promise<void> {
    await this.request(`/rest/v1/scraper_holds?${query({ until: `lte.${new Date().toISOString()}` })}`, {
      method: 'DELETE',
      prefer: 'return=minimal',
    });
  }

  /** The version of the newest stored Peek AI model, or null when none is stored. */
  async latestPeekAiModelVersion(): Promise<string | null> {
    const [row] = rows(await this.request(`/rest/v1/peek_ai_models?${query([
      ['select', 'version'], ['order', 'created_at.desc'], ['limit', '1'],
    ])}`));
    return row && typeof row.version === 'string' ? row.version : null;
  }

  /** A stored Peek AI model's document, or null when its version is gone. */
  async peekAiModel(version: string): Promise<unknown> {
    const [row] = rows(await this.request(`/rest/v1/peek_ai_models?${query([
      ['select', 'model'], ['version', `eq.${version}`], ['limit', '1'],
    ])}`));
    return row?.model ?? null;
  }

  /** Stores a case of a Peek AI call the server proves on its own, keeping the first noted for the parcel and day. */
  async notePeekAiCase(found: PeekAiCase): Promise<void> {
    await this.request(`/rest/v1/peek_ai_call_cases?${query({ on_conflict: 'package_id,why,carrier_day' })}`, {
      method: 'POST',
      body: found,
      prefer: 'resolution=ignore-duplicates,return=minimal',
    });
  }

  /** How a Peek AI call turned out on the server's `latest` known cases whose time ended in the last `days` days. */
  async peekAiCallRecord(why: PeekAiCase['why'], latest: number, days: number): Promise<PeekAiCallRecord> {
    const result = await this.request('/rest/v1/rpc/peek_ai_call_record', { method: 'POST',
      body: { p_why: why, p_cases: latest, p_days: days } });
    const cases = isRecord(result) ? result.cases : null;
    const right = isRecord(result) ? result.right : null;
    if (!Number.isInteger(cases) || !Number.isInteger(right)) throw new Error('Peek AI call record is malformed');
    return { cases: cases as number, right: right as number };
  }

  /**
   * Forgets the lookups past their forget date and the one-off parcels left
   * without a link, the links from accounts stopped 30 days ago (`stopped`),
   * and the alerts of journeys that are over (`alerts`).
   */
  async forgetExpiredParcelLinks(): Promise<{ links: number; packages: number; stopped: number; alerts: number }> {
    const result = await this.request('/rest/v1/rpc/forget_expired_parcel_links', { method: 'POST', body: {} });
    const counts = isRecord(result) ? result : {};
    return { ...forgotten(result), stopped: Number(counts.stopped ?? 0), alerts: Number(counts.alerts ?? 0) };
  }

  /**
   * Yesterday's lookups per client: how many clients, and their median, 90th
   * percentile and maximum. `detection` says the same of the numbers each
   * client had carriers asked about.
   */
  async publicLookupUsageSummary(): Promise<PublicUsageStats & { detection: PublicUsageStats }> {
    const result = await this.request('/rest/v1/rpc/public_lookup_usage_summary', { method: 'POST', body: {} });
    const summary = isRecord(result) ? result : {};
    return { ...usageStats(summary), detection: usageStats(summary.detection) };
  }

  async pendingSyncJobCount(userId?: string | null): Promise<number> {
    const params: Record<string, string> = {
      select: 'id',
      state: 'in.(queued,running)',
      limit: '1000',
    };
    if (userId) params.user_id = `eq.${userId}`;
    return rows(await this.request(`/rest/v1/sync_jobs?${query(params)}`)).length;
  }

  /** Whether a check of any of these parcels is still queued or running. */
  async packageSyncJobsPending(packageIds: string[]): Promise<boolean> {
    const params = { select: 'id', package_id: `in.(${packageIds.join(',')})`, state: 'in.(queued,running)', limit: '1' };
    return rows(await this.request(`/rest/v1/sync_jobs?${query(params)}`)).length > 0;
  }

  /** A file of a parcel's note: whose it is, its name, type and size; never its bytes. */
  async packageFile(fileId: string): Promise<PackageFileRow | null> {
    const file = rows(await this.request(`/rest/v1/package_files?${query({
      select: 'id,user_id,name,content_type,size', id: `eq.${fileId}`, limit: '1',
    })}`))[0];
    return file && typeof file.user_id === 'string' && typeof file.name === 'string' && typeof file.content_type === 'string'
      ? { id: String(file.id), userId: file.user_id, name: file.name, contentType: file.content_type, size: Number(file.size) || 0 }
      : null;
  }

  /** The bytes of a file of `userId`, or of its thumbnail; null when there are none. */
  async packageFileContent(fileId: string, userId: string, thumbnail: boolean): Promise<Buffer | null> {
    const content = await this.request('/rest/v1/rpc/package_file_content', {
      method: 'POST', body: { p_file_id: fileId, p_user_id: userId, p_thumbnail: thumbnail },
    });
    return typeof content === 'string' && content ? Buffer.from(content, 'base64') : null;
  }
}

/** A file of a parcel's note as the server reads it before serving its bytes. */
export interface PackageFileRow { id: string; userId: string; name: string; contentType: string; size: number }

/** A file to add to a parcel's note, as it is stored (parcelFiles.ts). */
export interface NewPackageFile {
  name: string;
  type: string;
  content: Uint8Array;
  thumbnail: Uint8Array | null;
  width: number | null;
  height: number | null;
}

export class SupabaseUserClient extends SupabaseClient {
  /** The account the token signs in, when the server read it: a review demo parcel reads as its scenario (reviewDemo.ts). */
  ownerId: string | null = null;

  /** The owner's reads carry the parcel's private note and its files (parcelNotes.ts); nobody else's do. */
  protected override packageSelect(): string {
    return `${PACKAGE_SELECT},${NOTE_EMBED}`;
  }

  private owned(parcel: JsonObject): JsonObject {
    return withOwnerNote(withReviewDemo(parcel, this.ownerId), this.ownerId);
  }

  override async listPackages(includeArchived = false): Promise<JsonObject[]> {
    return (await super.listPackages(includeArchived)).map((parcel) => this.owned(parcel));
  }

  override async getPackage(packageId: string): Promise<JsonObject | null> {
    const parcel = await super.getPackage(packageId);
    return parcel ? this.owned(parcel) : null;
  }

  /**
   * The account's own newest delivered parcels, archived ones too, which tell the town its parcels are usually
   * delivered to (usualPlace.ts) on a route that serves one parcel.
   */
  async listDeliveredPackages(): Promise<JsonObject[]> {
    return rows(await this.request(`/rest/v1/packages?${query({
      select: PACKAGE_SELECT, current_stage: 'eq.delivered', order: 'created_at.desc', limit: '50',
    })}`)).map((parcel) => withReviewDemo(parcel, this.ownerId));
  }

  override async getPackageByTrackingNumber(trackingNumber: string): Promise<JsonObject | null> {
    const parcel = await super.getPackageByTrackingNumber(trackingNumber);
    return parcel ? this.owned(parcel) : null;
  }

  /** Writes the parcel's private note; null or blank deletes its text. False for a parcel that is not the account's. */
  async setPackageNote(packageId: string, note: string | null): Promise<boolean> {
    return await this.request('/rest/v1/rpc/set_package_note', {
      method: 'POST', body: { p_package_id: packageId, p_body: note },
    }) === true;
  }

  /**
   * Adds a file to the parcel's note: the stored row, without its bytes. The database refuses past its limits with its
   * own codes (PF001 the parcel's five, PF002 the account's 200 MB, PF003 10 MB, PF004 a type its bytes don't show), and
   * a parcel not the account's with P0002.
   */
  async addPackageFile(packageId: string, file: NewPackageFile): Promise<JsonObject> {
    const added = await this.request('/rest/v1/rpc/add_package_file', {
      method: 'POST',
      body: {
        p_package_id: packageId,
        p_name: file.name,
        p_content_type: file.type,
        p_content: Buffer.from(file.content).toString('base64'),
        p_thumbnail: file.thumbnail ? Buffer.from(file.thumbnail).toString('base64') : null,
        p_width: file.width,
        p_height: file.height,
      },
      timeoutMs: 60_000,
    }).catch(ownedPackageError);
    if (!isRecord(added) || typeof added.id !== 'string') throw new SupabaseError('Supabase did not return the added file');
    return added;
  }

  /** Deletes a file of the account's notes; false when it is not the account's. */
  async deletePackageFile(fileId: string): Promise<boolean> {
    return await this.request('/rest/v1/rpc/delete_package_file', { method: 'POST', body: { p_file_id: fileId } }) === true;
  }

  /**
   * Keeps the parcel behind a link in this account. Null when the link is
   * unknown or the caller has no right to it; the database does not say which.
   */
  async claimParcelLink(
    linkId: string,
    ownerKeyHash: string | null,
    label: string,
    note: string | null = null,
  ): Promise<{ outcome: 'kept' | 'already' | 'quota'; packageId: string | null } | null> {
    let result: unknown;
    try {
      result = await this.request('/rest/v1/rpc/claim_parcel_link', {
        method: 'POST',
        body: { p_link_id: linkId, p_owner_key_hash: ownerKeyHash, p_label: label, ...(note ? { p_note: note } : {}) },
      });
    } catch (error) {
      if (error instanceof SupabaseError && error.code === 'P0002') return null;
      throw error;
    }
    if (!isRecord(result) || !(result.outcome === 'kept' || result.outcome === 'already' || result.outcome === 'quota')) {
      throw new SupabaseError('Supabase did not return the kept parcel');
    }
    return { outcome: result.outcome, packageId: typeof result.package_id === 'string' ? result.package_id : null };
  }

  /** Records that the account read its parcels: they keep the full schedule for the next hour. */
  async recordOpened(): Promise<void> {
    await this.request('/rest/v1/rpc/record_account_opened', { method: 'POST', body: {} });
  }

  /** The account opened the parcel: the moment stored (`seen_at`), or null for a parcel that is not its own. */
  async markPackageSeen(packageId: string, newsAt: string | null = null): Promise<string | null> {
    const mark = async (id: string) => {
      const seen = await this.request('/rest/v1/rpc/mark_owned_package_seen', {
        method: 'POST', body: { p_package_id: id, ...(newsAt ? { p_news_at: newsAt } : {}) },
      });
      return typeof seen === 'string' ? seen : null;
    };
    const seen = await mark(packageId);
    if (seen) return seen;
    // A parcel merged into another still answers to its old id.
    const merged = rows(await this.request(`/rest/v1/packages?${query({
      select: 'id', 'carrier_data->>original_package_id': `eq.${packageId}`, limit: '1',
    })}`))[0];
    return merged ? await mark(String(merged.id)) : null;
  }

  /** The live link this account shares the parcel through, or null. A parcel of another account is a 404. */
  async packageShare(packageId: string): Promise<ParcelShare | null> {
    const result = await this.request('/rest/v1/rpc/owned_package_share', {
      method: 'POST', body: { p_package_id: packageId },
    }).catch(ownedPackageError);
    return result === null ? null : parcelShare(result);
  }

  /** Shares the parcel: makes its link when none is live, else changes what the live one shows. */
  async sharePackage(
    packageId: string,
    shown: { showNumber?: boolean; gift?: boolean; giftWords?: ParcelShare['giftWords'] },
  ): Promise<ParcelShare & { created: boolean }> {
    const result = await this.request('/rest/v1/rpc/share_owned_package', {
      method: 'POST',
      body: { p_package_id: packageId, p_show_number: shown.showNumber ?? null, p_gift: shown.gift ?? null, p_gift_words: shown.giftWords ?? null },
    }).catch(ownedPackageError);
    return { ...parcelShare(result), created: isRecord(result) && result.created === true };
  }

  /** Stops sharing the parcel; false when it was not shared. */
  async stopPackageShare(packageId: string): Promise<boolean> {
    return await this.request('/rest/v1/rpc/stop_owned_package_share', {
      method: 'POST', body: { p_package_id: packageId },
    }).catch(ownedPackageError) === true;
  }

  override async updatePackage(packageId: string, values: JsonObject): Promise<void> {
    const keys = Object.keys(values);
    let changed: unknown;
    if (keys.length === 1 && keys[0] === 'label' && typeof values.label === 'string') {
      changed = await this.request('/rest/v1/rpc/rename_owned_package', {
        method: 'POST',
        body: { p_package_id: packageId, p_label: values.label },
      });
    } else if (keys.length === 1 && keys[0] === 'archived_at') {
      changed = await this.request('/rest/v1/rpc/set_owned_package_archived', {
        method: 'POST',
        body: { p_package_id: packageId, p_archived: values.archived_at != null },
      });
    } else if (
      keys.length === 1
      && keys[0] === 'notifications_muted'
      && typeof values.notifications_muted === 'boolean'
    ) {
      changed = await this.request('/rest/v1/rpc/set_owned_package_notifications_muted', {
        method: 'POST',
        body: { p_package_id: packageId, p_muted: values.notifications_muted },
      });
    } else if (keys.length === 1 && keys[0] === 'email_muted' && typeof values.email_muted === 'boolean') {
      changed = await this.request('/rest/v1/rpc/set_owned_package_email_muted', {
        method: 'POST',
        body: { p_package_id: packageId, p_muted: values.email_muted },
      });
    } else {
      throw new TypeError('User-scoped package updates must use an approved mutation');
    }
    if (changed !== true) throw new SupabaseError('Package not found', 404, 'P0002');
  }

  async setProviderPostcode(packageId: string, postcode: string): Promise<boolean> {
    return await this.request('/rest/v1/rpc/set_owned_package_provider_postcode', {
      method: 'POST', body: { p_package_id: packageId, p_postcode: postcode },
    }) === true;
  }

  async changePackageCarrier(
    packageId: string,
    carrier: string,
    trackingUrl?: string | null,
    dpdPostcode?: string | null,
    phoneDigits: string | null = null,
  ): Promise<boolean> {
    return await this.request('/rest/v1/rpc/change_owned_package_carrier_with_digits', {
      method: 'POST',
      body: {
        p_package_id: packageId,
        p_carrier: carrier,
        p_tracking_url: trackingUrl ?? null,
        p_dpd_postcode: dpdPostcode ?? null,
        p_phone_digits: phoneDigits,
      },
    }) === true;
  }

  override async deleteArchivedPackage(packageId: string): Promise<boolean> {
    return await this.request('/rest/v1/rpc/delete_owned_archived_package', {
      method: 'POST',
      body: { p_package_id: packageId },
    }) === true;
  }

  override async deletePackage(packageId: string): Promise<boolean> {
    return await this.request('/rest/v1/rpc/delete_owned_package', {
      method: 'POST',
      body: { p_package_id: packageId },
    }) === true;
  }

  async getNotificationPreferences(): Promise<JsonObject> {
    const params = query({
      select: 'enabled_stages,quiet_hours_start,quiet_hours_end,timezone,email_on_delivery,mark_new_updates,clock,distance,show_peek_ai',
      limit: '1',
    });
    const result = rows(await this.request(`/rest/v1/notification_preferences?${params}`));
    return result[0] ?? {
      enabled_stages: [...ALL_NOTIFICATION_STAGES],
      quiet_hours_start: null,
      quiet_hours_end: null,
      timezone: 'Europe/Zurich',
      email_on_delivery: null,
      mark_new_updates: true,
      show_peek_ai: true,
    };
  }

  /**
   * `emailOnDelivery` switches the delivery email, `markNewUpdates` the list's mark on a parcel with
   * news, `showPeekAi` Peek AI's own estimates, and `clock` and `distance` are how the account's app shows
   * a time and a distance; left out or null, the stored choice stays.
   */
  async setNotificationPreferences(
    enabledStages: string[] | undefined,
    quietHoursStart: string | null | undefined,
    quietHoursEnd: string | null | undefined,
    timezone: string | undefined,
    emailOnDelivery: boolean | null = null,
    markNewUpdates: boolean | null = null,
    clock: '24' | '12' | null = null,
    distance: 'km' | 'mi' | null = null,
    showPeekAi: boolean | null = null,
  ): Promise<JsonObject> {
    // Every save patches the row, so it records whether the save sent a time zone (`timezone_chosen`).
    let result = await this.request('/rest/v1/rpc/patch_owned_notification_preferences', {
      method: 'POST',
      body: {
        p_set_quiet: quietHoursStart !== undefined,
        p_enabled_stages: enabledStages,
        p_quiet_hours_start: quietHoursStart,
        p_quiet_hours_end: quietHoursEnd,
        p_timezone: timezone,
        ...(emailOnDelivery === null ? {} : { p_email_on_delivery: emailOnDelivery }),
        ...(markNewUpdates === null ? {} : { p_mark_new_updates: markNewUpdates }),
        ...(clock === null ? {} : { p_clock: clock }),
        ...(distance === null ? {} : { p_distance: distance }),
        ...(showPeekAi === null ? {} : { p_show_peek_ai: showPeekAi }),
      },
    });
    if (Array.isArray(result) && result.length === 1) [result] = result;
    if (!isRecord(result)) {
      throw new SupabaseError('Supabase did not return notification preferences');
    }
    return result;
  }

  /** The delivery emails this account was sent: the parcel, when it still exists, what each told, and the time. */
  async listDeliveryEmails(): Promise<Array<{ packageId: string | null; stage: EmailStage; sentAt: string }>> {
    const sent = rows(await this.request('/rest/v1/rpc/owned_delivery_emails', { method: 'POST', body: {} }));
    return sent.filter((email) => typeof email.sent_at === 'string').map((email) => ({
      packageId: typeof email.package_id === 'string' ? email.package_id : null,
      stage: email.stage === 'ready_for_pickup' ? 'ready_for_pickup' : 'delivered',
      sentAt: String(email.sent_at),
    }));
  }
}
