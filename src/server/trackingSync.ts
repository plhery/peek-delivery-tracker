import 'server-only';
import { deferredTrackingFailure, trackingFailureCode } from './trackingFailure';
import { CarrierError, carrierErrorKind, stageSource, resultStage, resultHasUpdate, CARRIER_DEFINITIONS, type CarrierId } from 'universal-parcel-scraper';
export { classifyStage, inferStage, stageSource, resultStage, resultHasUpdate } from 'universal-parcel-scraper';
import { AMAZON_ACCOUNT_MESSAGE, AMAZON_HISTORY_EXPIRED, requiresAmazonAccount } from '../lib/amazon';
import { normalizeTrackingNumber } from '../lib/carriers';

import { createHash } from 'node:crypto';
import { DateTime } from 'luxon';
import { STAGES } from '../generated/apiContract';
import { normalizeCarrierResult, type CarrierResult } from 'universal-parcel-scraper';
import { deliveryHandoff, hasDirectHandoffAdapter, type DeliveryHandoff } from 'universal-parcel-scraper';
import {
  AUTOMATIC_CARRIER_IDS,
  supportsSwissPostHandoff,
} from './carriers';
import type { AdapterRegistry, Recognition, TrackingContext } from 'universal-parcel-scraper/node';
import { trackCarrier } from 'universal-parcel-scraper/node';
import { carrierHomeZone, dateLocalClocks } from 'universal-parcel-scraper/places';
import type { StepRecorder } from 'universal-parcel-scraper/node';
import { createAdapterRegistry, hostAdapterEnvironment } from './adapterRegistry';
import { normalizedLookupCountry } from './lookupCountry';
import { preflightInputNeeded, takePreflightHistory } from './trackingPreflight';
import { refusesNumber } from './numberRefusal';
import { recognizeBrowser, takeBrowserHistory } from './browserRecognition';
import { hostStepRecorder } from './stepRecorder';
import { recordStatusMapping } from './metrics';
import {
  captureOperationalError,
  errorType,
  logOperationalEvent,
  reportRoutingEvent,
  shouldReportRepeatedFailure,
} from './observability';
import type { DeliveryEmailService } from './email/deliveryEmails';
import { PushDispatchError, type CompositePushNotificationService } from './push';
import { REDETECTION_PAGE, STORED_EVENT_IDENTITIES, SyncJobLeaseLost, type SupabaseServiceClient } from './supabase';
import { clockDay, datedObservations, filledPlaceholders, misdatedCopies, restatedIdentities, sameInstantIdentities, scanDay, sharedScans, relayedScans, UNIVERSAL_PROVIDER, UNWORDED_STEP, withIdentities, withoutCopyDrift } from './eventIdentity';
import {
  askedForCheck,
  providerFailureSummary,
  TrackingSyncAudit,
  WorkerShutdown,
  type SyncAnomalyCode,
  type SyncRunContext,
} from './trackingAudit';
import { isRecord, type JsonObject } from './types';
import { UniversalTracker } from 'universal-parcel-scraper/node';
import type { UniversalSource } from 'universal-parcel-scraper';
import { answeredWithoutHistory, catalogRules, clearConfirmation, detectionExpects, directCarrier, freshnessWindow, hasRoutingProgress, RoutingDeferred, routingFailure, routingState, TrackingRouter, type RoutedResult } from './trackingRouting';
import { REDETECTION_WINDOW_MS, rerouteAskable } from './redetection';
import { upuHistory } from './upuHistory';
import { sparseLookupRecheck, UNSEEN_HOURLY_MS, unseenFor, unseenIntervalHours, unseenRecheckDue } from './unseenLookup';
import { collectedPickupPoint } from './collectedPickupPoint';
import { directHistoryNumber, directLocalHistory, directLocalSnapshotIsOlder, hasUnresolvedDirectCurrent, localClockCarrier } from './directLocalHistory';
import { carrierMovedAt, eventStage, showsDelivery, UNCONFIRMED_DELIVERY_MS } from './disputedDelivery';
import { DIRECT_HISTORY, directHistory } from './providerCopies';
import { earlierCarrier } from './relayCopies';
import { eventTimestamp, latestResultTime, resultTimezone, SWISS_POST_GLOBAL_STATUSES } from 'universal-parcel-scraper/app';
import { trackingSupportEvidence } from './trackingSupport';
import { noteTypoCandidate } from './typoCandidate';
import { deployedVersion, statusMapClosings, universalMapAgrees } from './reviewQueues';
import { carrierLookupKey, recognitionKey, SharedLookups, universalLookupKey, type ParcelLookups } from './sharedLookups';

const MAX_PACKAGES_PER_OWNER_PER_SYNC = 5;
/** One-off parcels have no owner: a scheduled run checks this many of them, all together. */
const MAX_ONE_OFF_PACKAGES_PER_SYNC = 10;
/** A scheduled run rechecks at most this many accounts' parcels whose number nobody has answered (unseenRecheckDue). */
const MAX_UNSEEN_RECHECKS_PER_SYNC = 3;
/** A one-off parcel stays on the schedule this long after one of its links was last opened. */
const ONE_OFF_FOLLOWED_MS = 24 * 60 * 60 * 1_000;
/** A catalog queues the checks of parcels its rules point to another carrier this many at a time. */
const MAX_REDETECTIONS = 50;
/** A parcel no notification can reach keeps the daytime cadence this long after its account's apps or one of its links were last opened; then it is checked hourly. */
const WATCHED_AFTER_OPEN_MS = 60 * 60 * 1_000;
const VALID_STAGES = new Set<string>(STAGES);
/**
 * A parcel out for delivery is checked this often by day while its carrier's
 * own adapter answers for it and the carrier sets no longer interval of its own.
 */
const LAST_MILE_MINUTES = 2;
/**
 * The same, while the parcel's lookups open a browser: each takes several seconds of the
 * browser service or a local Chromium, so it is not asked every 2 minutes.
 */
const BROWSER_LAST_MILE_MINUTES = 5;
/** How long a carrier a provider named and the check did not ask keeps the parcel due at every run. */
const NAMED_CARRIER_DUE_MS = 15 * 60_000;
/**
 * A parcel out for delivery with a delivery window keeps the regular cadence
 * until this long before the window opens.
 */
const BEFORE_WINDOW_MS = 30 * 60 * 1_000;
/** Scheduled checks fall back to hourly this long after a parcel's newest carrier event, or after it was added. */
const IDLE_AFTER_MS = 48 * 60 * 60 * 1_000;
/**
 * A parcel still registered or accepted falls back to hourly sooner: once
 * this long has passed since its newest carrier event, or since it was added.
 * By then a check rarely finds anything new, and the first scan can take days.
 */
const EARLY_IDLE_AFTER_MS = 12 * 60 * 60 * 1_000;
const NOT_YET_MOVING = new Set(['registered', 'accepted']);
/** By day, a lookup without an account that nobody watches is checked this often. */
const UNWATCHED_ONE_OFF_HOURS = 3;
/**
 * By day, a lookup without an account filed under no carrier that nobody follows any more is
 * checked this often while it is new (REDETECTION_WINDOW_MS), once a carrier or provider has
 * answered for its number: the next answer can name its carrier.
 */
const UNFILED_ONE_OFF_HOURS = 6;
/**
 * An archived parcel is checked daily until this long after its newest carrier
 * event, or after it was added: one put away at a pickup point still shows its
 * collection.
 */
const ARCHIVED_FOLLOWED_MS = 30 * 24 * 60 * 60 * 1_000;

/**
 * An unsuccessful partner confirmation must not double every refresh's cost.
 * Recheck when origin history advances, the partner/reference changes, or the gap
 * passes: this one after the first miss.
 */
const DELIVERY_PROBE_INTERVAL_MS = 55 * 60 * 1_000;
/**
 * The gap while the partner's first scan is the next thing to happen, or someone
 * waits for the answer: the partner has announced the parcel, or the check was
 * asked for by hand.
 */
const DELIVERY_PROBE_SHORT_INTERVAL_MS = 5 * 60 * 1_000;
/**
 * Each probe the partner misses while the origin has no news doubles the gap before
 * the next, from DELIVERY_PROBE_INTERVAL_MS up to this. A Swiss postal number filed
 * under Swiss Post is followed on Cainiao the same way while Swiss Post does not know it.
 */
const DELIVERY_PROBE_MAX_INTERVAL_MS = 6 * 60 * 60 * 1_000;
/**
 * A carrier publishes some scans after it handed the parcel over. The earlier
 * carrier is asked again this long after the handoff, at most once per
 * DELIVERY_PROBE_INTERVAL_MS, alongside the delivery carrier.
 */
const EARLIER_FOLLOW_UP_MS = 24 * 60 * 60 * 1_000;

interface DeliveryProbe extends JsonObject {
  at: string;
  origin_update: string | null;
  /** The partner had the parcel on record, dated no earlier than the origin's news, without a scan of its own. */
  announced?: true;
  /** The probes in a row that found nothing while the origin (Cainiao for Swiss Post) had no news. */
  misses?: number;
}

/** Whether this check asks a handed-over parcel's earlier carrier again: within a day of the handoff, at most hourly. */
function asksEarlierCarrier(metadata: JsonObject, now: Date): boolean {
  const handedOver = Date.parse(String(metadata.handed_over_at ?? ''));
  const asked = Math.max(handedOver, Date.parse(String(metadata.earlier_checked_at ?? '')) || 0);
  const time = now.getTime();
  return time >= handedOver && time - handedOver < EARLIER_FOLLOW_UP_MS && time - asked >= DELIVERY_PROBE_INTERVAL_MS;
}

function previousDeliveryProbe(parcel: JsonObject, carrier: string, number: string): DeliveryProbe | null {
  const data = isRecord(parcel.carrier_data) ? parcel.carrier_data : {};
  const stored = data.delivery_probe ?? (carrier === 'swiss-post' ? data.swiss_post_probe : undefined);
  if (!isRecord(stored) || typeof stored.at !== 'string' || !Number.isFinite(Date.parse(stored.at))) return null;
  if ((stored.carrier != null && stored.carrier !== carrier) || (stored.number != null && stored.number !== number)) return null;
  return { at: stored.at, origin_update: typeof stored.origin_update === 'string' ? stored.origin_update : null,
    ...(stored.announced === true ? { announced: true as const } : {}),
    ...(Number.isSafeInteger(stored.misses) && Number(stored.misses) > 0 ? { misses: Number(stored.misses) } : {}) };
}

/** How many answers a check took from another copy of its number in the same run, when any. */
function sharedAnswers(checks: ParcelLookups | undefined): JsonObject {
  return checks?.shared ? { shared_answers: checks.shared } : {};
}

/** Which tiers a deferred lookup gave up on, as provider ids and failure kinds only. */
function deferredFetchDetails(error: RoutingDeferred): JsonObject {
  return { providers_attempted: error.attempted, provider_failures: providerFailureSummary(error.routing.failures) ?? '' };
}

export function isTrackingSyncDue(parcel: JsonObject, now: Date): boolean {
  return now.getTime() >= trackingSyncDueAt(parcel);
}

/**
 * When the persisted cooldown lets the parcel be checked again, as epoch
 * milliseconds: a routing deferral or the carrier's refresh interval. Zero when
 * nothing holds it back, infinity when it is never checked.
 */
export function trackingSyncDueAt(parcel: JsonObject): number {
  if (parcel.sync_status === 'unsupported' && parcel.carrier === 'amazon-shipping' && parcel.sync_error === AMAZON_HISTORY_EXPIRED) return Infinity;
  if (parcel.sync_status === 'unsupported' && requiresAmazonAccount(String(parcel.carrier), String(parcel.tracking_number ?? ''))) return Infinity;
  const deferred = Date.parse(String(routingState(parcel).next_check_at ?? ''));
  const after = Number.isFinite(deferred) ? deferred : 0;
  const activeCarrier = isRecord(parcel.carrier_data) ? parcel.carrier_data.active_tracking_carrier : undefined;
  const refresh = CARRIER_DEFINITIONS[String(activeCarrier ?? parcel.carrier) as CarrierId]?.tracking.refresh;
  if (!refresh?.afterFailureMinutes) return after;
  const lastChecked = Date.parse(String(parcel.last_synced_at ?? ''));
  if (!Number.isFinite(lastChecked)) return after;
  const interval = parcel.sync_status === 'error'
    ? refresh.afterFailureMinutes * 60_000 : refresh.minMinutes * 60_000;
  return Math.max(after, lastChecked + interval);
}

/** The newest carrier event or the parcel's creation, whichever is later; null when neither is known. */
function lastActivity(parcel: JsonObject): number | null {
  // Routing keeps the event watermark; parcels outside routing (Amazon Shipping) keep only the summary time.
  const summaryTime = isRecord(parcel.carrier_data) ? parcel.carrier_data.last_update : undefined;
  const times = [routingState(parcel).last_event_at, summaryTime, parcel.created_at]
    .map((value) => Date.parse(String(value ?? ''))).filter(Number.isFinite);
  return times.length ? Math.max(...times) : null;
}

/**
 * Whether the summary a parcel shows came from its carrier's own adapter, not
 * from a universal provider (17TRACK, ParcelsApp and the like): those mark
 * their answers with `tracking_provider`, and the next answer replaces it.
 */
function answeredByCarrier(parcel: JsonObject): boolean {
  return !isRecord(parcel.carrier_data) || typeof parcel.carrier_data.tracking_provider !== 'string';
}

/** Whether the parcel's last check that ran a lookup opened a browser (`browser_check` in its routing). */
function checkedInBrowser(parcel: JsonObject): boolean {
  return isRecord(parcel.carrier_data) && isRecord(parcel.carrier_data.routing) && parcel.carrier_data.routing.browser_check === true;
}

/**
 * When a parcel's delivery window ("2026-10-08 13:00–15:00") opens. Its times
 * are the clock of the carrier that answered: the zone its answer declares,
 * else that carrier's zone in the catalog. Null without a window with both
 * ends, or when the zone is unknown: UTC stands for none.
 */
function deliveryWindowOpens(parcel: JsonObject): number | null {
  const slot = /^(\d{4}-\d{2}-\d{2})[ T]+(\d{2}:\d{2})[–-]\d{2}:\d{2}$/.exec(String(parcel.expected_delivery ?? '').trim());
  if (!slot) return null;
  const data = isRecord(parcel.carrier_data) ? parcel.carrier_data : {};
  const zone = resultTimezone(String(data.active_tracking_carrier ?? parcel.carrier), data as CarrierResult);
  const opens = DateTime.fromISO(`${slot[1]}T${slot[2]}`, { zone });
  return zone !== 'UTC' && opens.isValid ? opens.toMillis() : null;
}

/**
 * The start of the parcel's current hour, which opens at a minute of its own: the
 * parcels checked hourly or less often are spread over the hour rather than all due at
 * its start, so no run takes them all. Copies of one number open at the same minute
 * and still share their lookups. Overnight runs start on the hour and check them all.
 */
function parcelHourStart(parcel: JsonObject, local: DateTime): DateTime {
  const number = normalizeTrackingNumber(String(parcel.tracking_number ?? ''));
  const minute = createHash('sha256').update(number).digest().readUInt32BE(0) % 60;
  return local.minus({ minutes: minute }).startOf('hour').plus({ minutes: minute });
}

interface ScheduleContext {
  /**
   * Nobody is waiting for the parcel, so it is checked hourly, as an idle one is, even out for
   * delivery; a lookup without an account every 3 hours by day, and not overnight.
   */
  unwatched?: boolean;
  /** A lookup without an account, which leaves the schedule when nobody has seen its number for hours. */
  oneOff?: boolean;
  /** A lookup without an account filed under no carrier, with no link opened for a day and no alert on. */
  unfollowed?: boolean;
  /** Someone the parcel was shared with gets its link's alerts, so archiving it keeps the regular cadence. */
  sharedAlerts?: boolean;
  /** One of the parcel's links was just opened: a number nobody has seen is checked daily rather than weekly. */
  opened?: boolean;
}

function isScheduledTrackingSyncDue(
  parcel: JsonObject,
  now: Date,
  { unwatched = false, oneOff = false, unfollowed = false, sharedAlerts = false, opened = false }: ScheduleContext = {},
): boolean {
  if (!isTrackingSyncDue(parcel, now)) return false;
  const unseen = unseenFor(parcel, now);
  if (oneOff && unseen !== null && (unfollowed || unseen >= UNSEEN_HOURLY_MS)) return false;
  // An account's parcel whose number nobody has answered for a day, with the rechecks on: once
  // 1, 3 and 7 days after it was added, by day, archived or not, and no more from 14 days. Any
  // check since the latest of these stands in for it, so a run that finds it done skips it.
  // Opening one of its links still checks it daily.
  const recheck = oneOff || opened ? undefined : unseenRecheckDue(parcel, now);
  if (recheck !== undefined) {
    const hour = DateTime.fromJSDate(now, { zone: 'Europe/Zurich' }).hour;
    return recheck !== null && hour >= 8 && hour < 22 && !(Date.parse(String(parcel.last_synced_at ?? '')) >= recheck);
  }
  const archived = parcel.archived_at != null && !sharedAlerts;
  if (archived) {
    const activity = lastActivity(parcel);
    if (activity === null || now.getTime() - activity >= ARCHIVED_FOLLOWED_MS) return false;
  }
  const lastChecked = Date.parse(String(parcel.last_synced_at ?? ''));
  if (!Number.isFinite(lastChecked)) return true;
  const local = DateTime.fromJSDate(now, { zone: 'Europe/Zurich' });
  const hourStart = parcelHourStart(parcel, local);
  // Archived, daily around the clock, as is a delivery its carrier may yet dispute.
  if (archived || unconfirmedDelivery(parcel, now)) return lastChecked < hourStart.minus({ hours: 23 }).toMillis();
  // A provider filed the parcel under a carrier with an adapter of its own that the check did
  // not ask: the next run asks that carrier. That check clears the mark; failing that, it lapses.
  const sinceNamed = now.getTime() - Date.parse(String(routingState(parcel).carrier_named_at ?? ''));
  if (sinceNamed >= 0 && sinceNamed < NAMED_CARRIER_DUE_MS) return true;
  // A lookup without an account filed under no carrier that nobody follows any more: every 6 hours
  // by day, and not overnight. A provider's next answer, or a carrier recognition asks, can name its carrier.
  if (oneOff && unfollowed) {
    return local.hour >= 8 && local.hour < 22
      && lastChecked < hourStart.minus({ hours: UNFILED_ONE_OFF_HOURS - 1 }).toMillis();
  }
  // A lookup without an account that nobody opened in the last hour and that has no alert on:
  // every 3 hours by day, and not overnight, when nobody reads it. A number no carrier is known
  // for asks every provider in turn. Opening its link still queues a check at once.
  if (oneOff && unwatched) {
    return local.hour >= 8 && local.hour < 22
      && lastChecked < hourStart.minus({ hours: UNWATCHED_ONE_OFF_HOURS - 1 }).toMillis();
  }
  if (unseen !== null) {
    // Hourly, then every 6 h, daily, then weekly, around the clock.
    return lastChecked < hourStart.minus({ hours: unseenIntervalHours(unseen, opened) - 1 }).toMillis();
  }
  // Out for delivery, the news is the delivery. A carrier's own adapter is cheap and as fresh as
  // the carrier, so it is asked every 2 minutes, every 5 while its lookups open a browser, unless
  // the carrier sets a longer interval of its own. A universal provider has quotas and publishes
  // scans late, so it keeps the regular cadence. Until shortly before a delivery window opens, the
  // regular cadence still catches a changed slot.
  const minimum = CARRIER_DEFINITIONS[String(parcel.carrier) as CarrierId]?.tracking.refresh?.minMinutes;
  const delivering = parcel.current_stage === 'out_for_delivery';
  const opens = delivering ? deliveryWindowOpens(parcel) : null;
  const lastMile = delivering && !minimum && answeredByCarrier(parcel)
    && (opens === null || now.getTime() >= opens - BEFORE_WINDOW_MS);
  const intervalMinutes = !lastMile ? Math.max(10, minimum ?? 0)
    : checkedInBrowser(parcel) ? BROWSER_LAST_MILE_MINUTES : LAST_MILE_MINUTES;
  const activity = lastActivity(parcel);
  const idleAfter = NOT_YET_MOVING.has(String(parcel.current_stage)) ? EARLY_IDLE_AFTER_MS : IDLE_AFTER_MS;
  const idle = unwatched || (activity !== null && now.getTime() - activity >= idleAfter);
  // Compare schedule windows so request duration does not skip the next tick.
  const windowStart = !idle && local.hour >= 8 && local.hour < 22
    ? local.startOf('minute').minus({ minutes: local.minute % intervalMinutes })
    : hourStart;
  return lastChecked < windowStart.toMillis();
}

/**
 * A delivery only a provider reported, which the parcel's carrier did not show: checked
 * daily until `unconfirmed_delivery_until`, in case the carrier disputes it.
 */
function unconfirmedDelivery(parcel: JsonObject, now: Date): boolean {
  const until = isRecord(parcel.carrier_data) ? Date.parse(String(parcel.carrier_data.unconfirmed_delivery_until ?? '')) : Number.NaN;
  return parcel.current_stage === 'delivered' && until > now.getTime();
}

/**
 * Whether opening a one-off parcel's link should queue a check: only when the
 * schedule's cadence for the parcel has come round, including for a lookup
 * that left the schedule because nobody has seen its number, which someone
 * looking gets daily rather than weekly. Reading a link never checks a carrier
 * more often than that, however often it is polled.
 */
export function isOpenedParcelSyncDue(parcel: JsonObject, now: Date): boolean {
  const open = !['delivered', 'returned'].includes(String(parcel.current_stage))
    || parcel.last_status_text === 'TO_BE_DELIVERED' || unconfirmedDelivery(parcel, now);
  return open && parcel.archived_at == null && isScheduledTrackingSyncDue(parcel, now, { opened: true });
}

export interface TrackingAdapter {
  fetchUniversal?(source: UniversalSource, trackingNumber: string, timeoutMs: number, dpdPostcode?: string | null, timezone?: string | null, carrier?: string | null): Promise<CarrierResult>;
  /** A carrier's cheap check of whether it knows a number (carrier.json `tracking.recognition`). */
  recognize?(carrierId: string, trackingNumber: string, context?: TrackingContext): Promise<Recognition>;
  recognizeBrowser?(carrierId: string, trackingNumber: string, context?: TrackingContext, previousError?: unknown): Promise<Recognition>;
  /** Whether the carrier is tracked only through its own browser, which fails every parcel alike when it is down. */
  browserOnly?(carrierId: string): boolean;
  fetch(
    carrierId: string,
    trackingNumber: string,
    trackingUrl: string | null,
    dpdPostcode?: string | null,
  ): Promise<CarrierResult>;
}

export class CarrierTrackingAdapter implements TrackingAdapter {
  constructor(
    readonly universal = new UniversalTracker({ environment: hostAdapterEnvironment(), providers: ['ParcelsApp', 'Ship24', '17TRACK', 'UPU'] }),
    readonly registry: AdapterRegistry = createAdapterRegistry(),
    readonly recorder: StepRecorder = hostStepRecorder(),
  ) {}

  async fetchUniversal(source: UniversalSource, trackingNumber: string, timeoutMs: number, dpdPostcode?: string | null, timezone?: string | null, carrier?: string | null): Promise<CarrierResult> {
    // No signal or country hint: the timeout bounds the call, and providers are sent no country.
    return this.universal.fetchSource(source, trackingNumber, timeoutMs, dpdPostcode ?? null, timezone ?? null, undefined, null, carrier ?? null);
  }

  async recognize(carrierId: string, trackingNumber: string, context?: TrackingContext): Promise<Recognition> {
    const registered = this.registry.for(carrierId);
    if (!registered?.recognize) throw new RangeError(`${carrierId} cannot recognize a number`);
    return await registered.recognize(trackingNumber, context);
  }

  recognizeBrowser = recognizeBrowser;

  browserOnly(carrierId: string): boolean {
    const steps = this.registry.for(carrierId)?.steps ?? [];
    return steps.length > 0 && steps.every((step) => step === 'browser');
  }

  async fetch(
    carrierId: string,
    trackingNumber: string,
    trackingUrl: string | null,
    dpdPostcode?: string | null,
  ): Promise<CarrierResult> {
    const prefetched = !trackingUrl && !dpdPostcode ? takeBrowserHistory(carrierId, trackingNumber) : undefined;
    if (prefetched) return dateLocalClocks(carrierId, normalizeCarrierResult(prefetched));
    return trackCarrier(carrierId, { number: trackingNumber, trackingUrl, postcode: dpdPostcode ?? null }, {
      registry: this.registry, universal: this.universal, recorder: this.recorder,
    });
  }
}

export interface SyncSummary extends JsonObject {
  checked: number;
  updated: number;
  unchanged: number;
  waiting: number;
  errors: number;
  unsupported: number;
  superseded: number;
  notifications_sent: number;
  notification_errors: number;
  subscriptions_expired: number;
  emails_sent: number;
  email_errors: number;
}

export function emptySyncSummary(): SyncSummary {
  return {
    checked: 0,
    updated: 0,
    unchanged: 0,
    waiting: 0,
    errors: 0,
    unsupported: 0,
    superseded: 0,
    notifications_sent: 0,
    notification_errors: 0,
    subscriptions_expired: 0,
    emails_sent: 0,
    email_errors: 0,
  };
}

const UNANNOUNCED_PHRASES = [
  'did not return the requested parcel',
  'no parcel found',
  'not announced',
  'not found yet',
  'not registered',
  'shipment not found',
  'tracking number not found',
  'unknown tracking number',
];

export function isUnannouncedTrackingError(error: unknown): boolean {
  const seen = new Set<unknown>();
  let current = error;
  while (current && !seen.has(current)) {
    seen.add(current);
    if (typeof current === 'object' && current !== null) {
      const status = 'status' in current ? Number(current.status)
        : 'statusCode' in current ? Number(current.statusCode)
          : 0;
      if (status === 404) return true;
    }
    const message = String(current).toLocaleLowerCase('en-US').trim().split(/\s+/).join(' ');
    if (UNANNOUNCED_PHRASES.some((phrase) => message.includes(phrase))) return true;
    current = current instanceof Error ? current.cause : null;
  }
  return false;
}

export function providerEventId(
  carrierId: string,
  rawTime: unknown,
  location: string,
  description: string,
): string {
  const material = JSON.stringify([
    carrierId,
    String(rawTime ?? ''),
    location.trim(),
    description.trim(),
  ]);
  return `${carrierId}:${createHash('sha256').update(material).digest('hex')}`;
}

export function buildEvents(
  parcel: JsonObject,
  result: CarrierResult,
  sourceCarrierId?: string,
  observedAt?: Date,
): JsonObject[] {
  const reportedCurrent = resultStage(result);
  const current = reportedCurrent ?? 'in_transit';
  const carrierId = sourceCarrierId ?? String(parcel.carrier ?? '');
  const timezone = resultTimezone(carrierId, result);
  const rows: JsonObject[] = [];
  // Carriers repeat a scan word for word (Chronopost logs one sort up to three
  // times in the same minute). A repeat has the same identity, and a batch that
  // holds an identity twice cannot be saved, so each scan is kept once.
  const identities = new Set<string>();
  // A universal provider's scans name it, so their rows follow its clock until a carrier writes them (eventIdentity.ts).
  // The stage change seen without a provider time names it too: no copy or clock rule reads that row.
  const provider = carrierId === 'unknown' && typeof result.tracking_provider === 'string'
    ? { [UNIVERSAL_PROVIDER]: result.tracking_provider } : {};
  // A scan the carrier dates by its day alone, with no clock (NACEX's calendar headings), is
  // dated in the carrier's order, newest first: a second after the scan listed after it when
  // that one is of the same day, else at the start of its day on the carrier's home clock, the
  // one the scraper dates its wall clocks on. A catalog zone of UTC would start a USPS day on
  // the evening before for a reader in the United States. Scans that join the day later leave
  // those dates as they are. So is a carrier's scan on a wall clock in no zone listed after a
  // dated one (YunExpress's), in an answer of its own lookup that is no fallback; its identity
  // keeps the clock, so two scans of a day stay two.
  const scans = result.events ?? [];
  const dayZone = carrierHomeZone(carrierId, result) ?? timezone;
  const stamped = scans.map((raw) => eventTimestamp(raw.time, timezone) !== null);
  const newestStamped = stamped.indexOf(true);
  const ownClocks = localClockCarrier(carrierId) && typeof result.tracking_provider !== 'string' && result.direct_local_fallback !== true;
  const clockDays = scans.map((raw, index) => ownClocks && !stamped[index] && newestStamped > -1 && newestStamped < index ? clockDay(raw) : null);
  const days = scans.map((raw, index) => stamped[index] ? null : scanDay(raw) ?? clockDays[index]);
  const instants = new Array<string | null>(scans.length).fill(null);
  let older: DateTime | null = null;
  for (let index = scans.length - 1; index >= 0; index -= 1) {
    const day = days[index];
    const timed = day ? null : eventTimestamp(scans[index].time, timezone);
    if (!day && !timed) continue;
    const start = day ? DateTime.fromISO(day, { zone: dayZone }) : null;
    const at: DateTime = start
      ? older && older >= start && older < start.plus({ days: 1 }) ? older.plus({ seconds: 1 }) : start
      : DateTime.fromISO(timed!, { zone: timezone });
    instants[index] = start ? at.toUTC().toISO({ suppressMilliseconds: true }) : timed;
    older = at;
  }
  for (const [index, raw] of scans.entries()) {
    const description = String(raw.description ?? UNWORDED_STEP).trim();
    const location = String(raw.location ?? '').trim();
    const occurredAt = instants[index];
    if (!occurredAt) continue;
    const identity = providerEventId(carrierId, clockDays[index] ? raw.local_time : days[index] ?? raw.time, location, description);
    if (identities.has(identity)) continue;
    identities.add(identity);
    const declaredStage = String(raw.stage ?? '');
    rows.push({
      package_id: parcel.id,
      // Historical scans must not inherit the shipment's current/final stage.
      stage: eventStage(raw),
      description,
      location: location || null,
      occurred_at: occurredAt,
      provider_event_id: identity,
      raw_data: { ...raw, stage_source: stageSource(declaredStage, description, raw.stage_source), ...provider },
    });
  }
  if (rows.length === 0 && current && result.last_status_text) {
    const description = String(result.last_status_text);
    const occurredAt = eventTimestamp(result.last_update, timezone);
    if (occurredAt) {
      rows.push({
        package_id: parcel.id,
        stage: current,
        description,
        location: null,
        occurred_at: occurredAt,
        provider_event_id: providerEventId(carrierId, result.last_update, '', description),
        raw_data: {
          time: result.last_update,
          stage_source: stageSource(String(result.current_stage ?? ''), description, result.current_stage_source),
          ...provider,
        },
      });
    }
  }
  const previousStage = String(parcel.current_stage ?? 'pending');
  const previousData = isRecord(parcel.carrier_data) ? parcel.carrier_data : {};
  const newUpuMilestone = result.tracking_provider === 'UPU'
    && previousData.tracking_provider === 'UPU'
    && typeof result.last_update_local === 'string'
    && result.last_update_local > String(previousData.last_update_local ?? '');
  const currentAlreadyTimed = rows.some((row) => row.stage === current);
  if (
    observedAt
    && !Number.isNaN(observedAt.getTime())
    && reportedCurrent !== null
    && (result.status !== 'pending' || rows.length === 0)
    && current !== 'pending'
    && (current !== previousStage || newUpuMilestone)
    && !currentAlreadyTimed
  ) {
    const matchingEvent = (result.events ?? []).find((raw) => eventStage(raw) === current);
    const description = String(
      matchingEvent?.description ?? result.last_status_text ?? UNWORDED_STEP,
    ).trim() || UNWORDED_STEP;
    const location = String(matchingEvent?.location ?? '').trim();
    const occurredAt = observedAt.toISOString();
    const declaredCurrent = String(result.current_stage ?? '');
    rows.push({
      package_id: parcel.id,
      stage: current,
      description,
      location: location || null,
      occurred_at: occurredAt,
      provider_event_id: providerEventId(
        carrierId,
        result.tracking_provider === 'UPU'
          ? `UPU:observed:${String(matchingEvent?.local_time ?? '')}:${String(matchingEvent?.provider_code ?? '')}`
          : `observed:${previousStage}->${current}`,
        location,
        description,
      ),
      raw_data: {
        ...(result.tracking_provider === 'UPU' || result.direct_local_fallback === true ? matchingEvent : {}),
        observed_without_provider_timestamp: true,
        stage_source: stageSource(
          VALID_STAGES.has(declaredCurrent) ? declaredCurrent : String(matchingEvent?.stage ?? ''),
          description,
          result.current_stage_source ?? matchingEvent?.stage_source,
        ),
        ...provider,
      },
    });
  }
  return rows;
}

// Local-clock scans can need review even though they cannot supply a timed
// timeline row. Their observation has no persisted sample event until a feed
// establishes an instant; its identity is only used by the observation lookup.
function localStatusObservationEvents(parcel: JsonObject, result: CarrierResult, carrierId: string): JsonObject[] {
  const timezone = resultTimezone(carrierId, result);
  return (result.events ?? []).flatMap((raw): JsonObject[] => {
    if (eventTimestamp(raw.time, timezone) || typeof raw.local_time !== 'string' || !raw.local_time
      || typeof raw.stage_source !== 'string') return [];
    const description = String(raw.description ?? '').trim();
    const stage = String(raw.stage ?? '');
    if (!description || !VALID_STAGES.has(stage)) return [];
    return [{
      package_id: parcel.id,
      stage,
      description,
      provider_event_id: providerEventId(carrierId, raw.local_time, String(raw.location ?? ''), description),
      raw_data: { ...raw, stage_source: stageSource(stage, description, raw.stage_source) },
    }];
  });
}

// One sync reports a bounded sample: repeated wording is already deduplicated
// by observation key, and the row's count grows on the next sync instead.
export const MAX_STATUS_OBSERVATIONS_PER_SYNC = 32;

export interface StatusObservation extends JsonObject {
  observation_key: string;
  carrier: string;
  provider_code: string | null;
  description_normalized: string;
  language_guess: string | null;
  stage_source: string;
  chosen_stage: string;
  package_id: string;
  provider_event_id: string;
}

export function normalizeObservedDescription(description: string): string {
  return description.toLocaleLowerCase('en-US').trim().split(/\s+/).join(' ').slice(0, 500);
}

/**
 * How one event's wording is observed: its key, carrier, code, stage source
 * and whether a map gave its stage; null without wording or stage.
 */
function observedWording(event: JsonObject, fallbackCarrierId: string) {
  const raw = isRecord(event.raw_data) ? event.raw_data : {};
  const source = typeof raw.stage_source === 'string' ? raw.stage_source : '';
  const description = normalizeObservedDescription(String(event.description ?? ''));
  const chosenStage = String(event.stage ?? '');
  if (!source || !description || !chosenStage) return null;
  const eventId = String(event.provider_event_id ?? '');
  // Every provider event id is prefixed with the carrier that served it, so a
  // timeline merged from two carriers keeps each wording with its own.
  const carrier = (eventId.split(':')[0] || fallbackCarrierId).slice(0, 100);
  if (!carrier) return null;
  const providerCode = typeof raw.provider_code === 'string' && raw.provider_code
    ? raw.provider_code.slice(0, 100)
    : null;
  const key = createHash('sha256')
    .update(JSON.stringify([carrier, providerCode ?? '', description]))
    .digest('hex');
  const mapped = source === 'carrier_map' || universalMapAgrees({
    carrier, provider_code: providerCode, description_normalized: description, chosen_stage: chosenStage,
  });
  return { key, carrier, providerCode, description, source, chosenStage, eventId, mapped };
}

/**
 * Collects the carrier wording no map gave its stage, so an operator can map
 * it later: the carrier's map, or the universal map for a universal
 * provider's scan (reviewQueues.ts). The package and event identifiers travel
 * with the observation only to resolve one sample event row; they are not stored.
 */
export function collectStatusObservations(
  events: readonly JsonObject[],
  fallbackCarrierId: string,
): StatusObservation[] {
  const observations = new Map<string, StatusObservation>();
  for (const event of events) {
    const observed = observedWording(event, fallbackCarrierId);
    if (!observed || observed.mapped || observations.has(observed.key)) continue;
    observations.set(observed.key, {
      observation_key: observed.key,
      carrier: observed.carrier,
      provider_code: observed.providerCode,
      description_normalized: observed.description,
      language_guess: null,
      stage_source: observed.source.slice(0, 100),
      chosen_stage: observed.chosenStage,
      package_id: String(event.package_id ?? ''),
      provider_event_id: observed.eventId,
    });
    if (observations.size >= MAX_STATUS_OBSERVATIONS_PER_SYNC) break;
  }
  return [...observations.values()];
}

/**
 * The observation keys of wording a map gave its stage in every scan that
 * carried it. An open observation with one of them is covered by the scraper
 * this server runs.
 */
export function collectMappedStatusKeys(events: readonly JsonObject[], fallbackCarrierId: string): string[] {
  const observed = events.flatMap((event) => observedWording(event, fallbackCarrierId) ?? []);
  const unmapped = new Set(observed.filter(({ mapped }) => !mapped).map(({ key }) => key));
  const keys = new Set<string>();
  for (const { key, mapped } of observed) {
    if (!mapped || unmapped.has(key)) continue;
    keys.add(key);
    if (keys.size >= MAX_STATUS_OBSERVATIONS_PER_SYNC) break;
  }
  return [...keys];
}

/** Stages before the carrier moves the parcel, in order. */
const EARLY_STAGES = ['pending', 'registered', 'accepted'];
/** Stages a carrier reports once it moves the parcel, short of a final one. */
const MOVING_STAGES = new Set(['in_transit', 'customs', 'out_for_delivery', 'failed_attempt', 'ready_for_pickup']);

/**
 * The stage to save over the previous one. An earlier stage than the saved
 * one, up to accepted, is a notice or a reworded scan rather than a step back,
 * so the saved stage stays. Delivered and returned have their own rule, and
 * after an exception a new label can start over.
 */
export function stageToSave(previousStage: string, selectedStage: string): string {
  const selected = EARLY_STAGES.indexOf(selectedStage);
  const previous = MOVING_STAGES.has(previousStage) ? EARLY_STAGES.length : EARLY_STAGES.indexOf(previousStage);
  return selected >= 0 && previous > selected ? previousStage : selectedStage;
}

/**
 * The saved stage `stageToSave` may hold: the saved one while a scan the
 * parcel keeps still shows it, else the furthest stage one shows. A scraper
 * fix can re-read the scan that moved the parcel as an earlier stage, and then
 * nothing holds the saved stage over the carrier's. A parcel loaded without
 * its rows proves nothing.
 */
export function heldStage(parcel: JsonObject, previousStage: string, rows: readonly JsonObject[]): string {
  const rank = (stage: string) => MOVING_STAGES.has(stage) ? EARLY_STAGES.length : EARLY_STAGES.indexOf(stage);
  const target = rank(previousStage);
  if (target < 0 || !Array.isArray(parcel[STORED_EVENT_IDENTITIES])) return previousStage;
  let furthest = 'pending';
  for (const row of rows) {
    const stage = String(row.stage ?? '');
    if (rank(stage) >= target) return previousStage;
    if (rank(stage) > rank(furthest)) furthest = stage;
  }
  return furthest;
}

export function detectSyncAnomalies(
  parcel: JsonObject,
  result: CarrierResult,
  events: JsonObject[],
  sourceCarrierId: string,
  selectedStage: string | null,
  now: Date,
): SyncAnomalyCode[] {
  const anomalies = new Set<SyncAnomalyCode>();
  const timezone = resultTimezone(sourceCarrierId, result);
  if ((result.events ?? []).some((event) => (
    typeof event.time === 'string'
    && event.time.trim() !== ''
    && eventTimestamp(event.time, timezone) === null
  ))) {
    anomalies.add('invalid_event_timestamp');
  }
  // A scan dated more than an hour after the check has a clock read in the wrong
  // zone or labelled with the wrong offset. A lead under an hour does not alert.
  const futureBoundary = now.getTime() + 60 * 60 * 1_000;
  if (events.some((event) => {
    const occurredAt = Date.parse(String(event.occurred_at ?? ''));
    return Number.isFinite(occurredAt) && occurredAt > futureBoundary;
  })) {
    anomalies.add('future_event_timestamp');
  }
  if (events.some((event) => (
    isRecord(event.raw_data)
    && event.raw_data.observed_without_provider_timestamp === true
  ))) {
    anomalies.add('observed_without_timestamp');
  }
  const previousStage = String(parcel.current_stage ?? 'pending');
  if (
    (previousStage === 'delivered' || previousStage === 'returned')
    && selectedStage !== null
    && selectedStage !== previousStage
    // A carrier may report a problem after delivery (damage, wrong address,
    // refusal). That is new information, not a rewritten history.
    && selectedStage !== 'exception'
  ) {
    anomalies.add('terminal_stage_regression');
  }
  if (result.status === 'delivered' && selectedStage !== 'delivered') {
    anomalies.add('delivered_status_conflict');
  }
  if (previousStage !== 'pending' && !(previousStage === 'registered' ? resultHasUpdate(result) : hasRoutingProgress(result))) {
    // A fallback provider that knows less than the source of the saved summary
    // contradicts nothing while the carrier's own adapter is asked again within
    // hours. That adapter, or the provider the summary came from, answering
    // without progress does. For a carrier without an adapter, routing has
    // exhausted the eligible providers before returning this thin answer.
    const savedProvider = isRecord(parcel.carrier_data) ? parcel.carrier_data.tracking_provider : undefined;
    anomalies.add(typeof result.tracking_provider === 'string' && result.tracking_provider !== savedProvider
      && directCarrier(String(parcel.carrier)) ? 'fallback_without_progress' : 'progress_disappeared');
  }
  return [...anomalies];
}

type SyncOutcome = 'updated' | 'unchanged' | 'waiting' | 'errors' | 'unsupported' | 'superseded';

/**
 * The copy code a failure is stored with. A number the parcel's own carrier
 * refuses as one it does not issue, such as one chosen by hand that issues no
 * number of its shape, has its own; a refused input, such as a postcode, asks
 * for the parcel's details.
 */
async function failureCopy(code: string | null, carrier: string, parcel: JsonObject): Promise<string | null> {
  return code === 'carrier:invalid_input' && await refusesNumber(carrier, String(parcel.tracking_number ?? ''))
    ? 'carrier:refused_number' : code;
}

/**
 * The work, or its rejection as soon as shutdown aborts it: a carrier that does not
 * watch the signal cannot hold the job's handoff. A lost lease waits for the work.
 */
function untilShutdown<T>(work: Promise<T>, signal?: AbortSignal): Promise<T> {
  if (!signal) return work;
  return new Promise<T>((resolve, reject) => {
    const abort = () => { if (signal.reason instanceof WorkerShutdown) reject(signal.reason); };
    signal.addEventListener('abort', abort, { once: true });
    work.then(resolve, reject).finally(() => signal.removeEventListener('abort', abort));
    if (signal.aborted) abort();
  });
}

class SupersededTrackingSync extends Error {}

export class TrackingSyncService {
  #tail: Promise<void> = Promise.resolve();
  #reportedObservationFailure = false;
  /** Push dispatches in a row that had a delivery fail. */
  #pushFailureStreak = 0;
  /**
   * The catalog rules this process looked for redetections with, whether it has
   * listed them yet, the parcels still to check, the most likely to change first,
   * with when each one's cooldown ends, and the batch queued last.
   */
  #redetections: { rules: string; listed: boolean; waiting: Array<{ packageId: string; userId: string | null; dueAt: number }>;
    batch: string[] } | null = null;

  constructor(
    readonly client: SupabaseServiceClient,
    readonly adapter: TrackingAdapter = new CarrierTrackingAdapter(),
    readonly notifier: CompositePushNotificationService | null = null,
    readonly now: () => Date = () => new Date(),
    readonly emails: DeliveryEmailService | null = null,
  ) {}

  async sync(context: SyncRunContext = { trigger: 'scheduled' }): Promise<SyncSummary> {
    return await this.exclusive(async () => {
      context.signal?.throwIfAborted();
      const summary = emptySyncSummary();
      const now = this.now();
      const unwatched = await this.unwatchedPackages(now, context.signal);
      const active = await this.client.listActivePackages();
      const shared = await this.sharedAlertPackages(active, context.signal);
      const due = (oneOff: boolean, unfollowed = false) => (parcel: JsonObject) => isScheduledTrackingSyncDue(parcel, now, {
        unwatched: unwatched.has(String(parcel.id)), oneOff, unfollowed, sharedAlerts: shared.has(String(parcel.id)),
      });
      const owned = active.filter(due(false));
      // An account's parcel whose number nobody has answered for a day is rechecked after every
      // other parcel and lookup, within its account's share, a few a run.
      const rechecks = new Set(owned.filter((parcel) => unseenRecheckDue(parcel, now) !== undefined));
      const shares = fairSyncPackages([...owned.filter((parcel) => !rechecks.has(parcel)), ...rechecks]);
      const parcels = [...shares.filter((parcel) => !rechecks.has(parcel)),
        ...await this.oneOffPackages(due(true), due(true, true), now, context.signal),
        ...shares.filter((parcel) => rechecks.has(parcel)).slice(0, MAX_UNSEEN_RECHECKS_PER_SYNC)];
      // Copies of one number, in several accounts or followed without one, share their lookups.
      const lookups = new SharedLookups(parcels);
      let legs: Set<string> | null | undefined;
      let announceAtEnd = false;
      for (const [index, parcel] of parcels.entries()) {
        // At shutdown the run stops between checks; its job goes back to the queue, and the
        // replacement checks the parcels still due.
        if (context.stopping?.aborted) throw context.stopping.reason;
        // A check since the run listed it, such as a release's re-check on another server, stands in for a recheck.
        if (rechecks.has(parcel) && !await this.uncheckedSinceListed(parcel, context.signal)) continue;
        summary.checked += 1;
        const outcome = await this.syncOne(parcel, context, lookups);
        summary[outcome] += 1;
        // A parcel's news goes out once it is stored, not after the slowest check of the
        // run; the last parcel's goes with the run's own dispatch. A leg the reconciliation
        // below may merge waits for it, so the merged parcel announces its newest scan
        // once, and so does every parcel checked after one, as a dispatch sends all news.
        if (announceAtEnd || outcome !== 'updated' || index === parcels.length - 1) continue;
        if (legs === undefined) legs = await this.mergeableLegs(parcels, context.signal);
        announceAtEnd = legs === null || parcels.slice(0, index + 1).some((checked) => legs!.has(String(checked.id)));
        if (!announceAtEnd) await this.dispatchNotifications(summary, context.signal);
      }
      await this.linkConfirmedParcels();
      await this.dispatchNotifications(summary, context.signal);
      return summary;
    });
  }

  async syncPackage(
    parcel: JsonObject,
    context: SyncRunContext = { trigger: 'package' },
  ): Promise<SyncSummary> {
    return await this.exclusive(async () => {
      context.signal?.throwIfAborted();
      const summary = emptySyncSummary();
      // A link read queued this check because one was due. The scheduled run this check waited
      // for may have checked the parcel meanwhile, which stands in for it.
      if (context.trigger === 'opened') {
        const current = await this.client.getPackage(String(parcel.id));
        if (!current || !isOpenedParcelSyncDue(current, this.now())) return summary;
        parcel = current;
      }
      // Manual refreshes share the persisted cooldown with scheduled checks.
      if (isTrackingSyncDue(parcel, this.now())) {
        summary.checked = 1;
        summary[await this.syncOne(parcel, context)] += 1;
      }
      if (typeof parcel.user_id === 'string') await this.linkConfirmedParcels(parcel.user_id);
      await this.dispatchNotifications(summary, context.signal);
      return summary;
    });
  }

  /**
   * Once per scraper release: one check of each open parcel the catalog's rules point to a
   * carrier its routing never asked, such as a lookup added before a scraper release
   * brought the number's detection rule, the name a provider gives or the carrier's
   * adapter, whose link nobody opens any more, and of each unfiled one the release
   * taught a carrier to recognize (redetection.ts). The checks
   * most likely to change the parcel's answer go first, 50 at a time: the next batch
   * waits until the last one's checks are done. A parcel still in its cooldown is queued by the first run after it ends.
   * Returns how many checks were queued; they wait behind every other job, a lookup's
   * check too.
   */
  async queueRedetections(signal?: AbortSignal): Promise<number> {
    const rules = catalogRules();
    if (this.#redetections?.rules !== rules) this.#redetections = { rules, listed: false, waiting: [], batch: [] };
    const redetections = this.#redetections;
    if (redetections.listed && !redetections.waiting.length) return 0;
    const now = this.now().getTime();
    let queued = 0;
    try {
      if (!redetections.listed) {
        const leads: Array<{ packageId: string; userId: string | null; dueAt: number; rank: number }> = [];
        // Every page, however many parcels the catalog points elsewhere.
        for (let before: string | undefined, more = true; more;) {
          const candidates = await this.client.listRedetectionCandidates(new Date(now - REDETECTION_WINDOW_MS), rules, before);
          for (const parcel of candidates) {
            const lead = await rerouteAskable(parcel, rules, now);
            if (!lead) continue;
            // A parcel filed under no carrier is checked for a carrier it never asked, or one the
            // release taught its number's shape. Asking again the carriers it asked rarely finds
            // more: its schedule does that, and opening the link of a lookup that left it.
            if (lead.rank === 2 && ['unknown', 'intl-post'].includes(String(parcel.carrier))) continue;
            const dueAt = trackingSyncDueAt(parcel);
            if (dueAt === Infinity) continue;
            leads.push({ packageId: String(parcel.id), userId: typeof parcel.user_id === 'string' ? parcel.user_id : null, dueAt, rank: lead.rank });
          }
          const last = candidates.at(-1)?.created_at;
          more = candidates.length === REDETECTION_PAGE && typeof last === 'string';
          before = String(last);
        }
        // Listed newest first; the sort keeps that order within a rank.
        redetections.waiting = leads.sort((a, b) => a.rank - b.rank)
          .map(({ packageId, userId, dueAt }) => ({ packageId, userId, dueAt }));
        redetections.listed = true;
      }
      if (redetections.batch.length && await this.client.packageSyncJobsPending(redetections.batch)) return 0;
      redetections.batch = [];
      for (const parcel of [...redetections.waiting]) {
        if (queued >= MAX_REDETECTIONS) break;
        if (parcel.dueAt > now) continue;
        await this.client.enqueueSyncJob({ packageId: parcel.packageId, userId: parcel.userId, background: true });
        redetections.waiting.splice(redetections.waiting.indexOf(parcel), 1);
        redetections.batch.push(parcel.packageId);
        queued += 1;
      }
    } catch (error) {
      signal?.throwIfAborted();
      captureOperationalError(error, { component: 'tracking', operation: 'queue_redetections' });
    }
    if (queued > 0) logOperationalEvent('tracking_redetections_queued', { queued, waiting: redetections.waiting.length });
    return queued;
  }

  /**
   * The open parcels nobody is waiting for: no notification can reach anyone
   * about them and nobody opened them in the last hour. When they cannot be
   * read, every parcel keeps the full cadence.
   */
  private async unwatchedPackages(now: Date, signal?: AbortSignal): Promise<Set<string>> {
    try {
      return new Set(await this.client.listUnwatchedPackageIds(new Date(now.getTime() - WATCHED_AFTER_OPEN_MS)));
    } catch (error) {
      signal?.throwIfAborted();
      captureOperationalError(error, { component: 'tracking', operation: 'list_unwatched_packages' });
      return new Set();
    }
  }

  /**
   * The archived parcels among these that someone they were shared with gets
   * alerts for. When they cannot be read, every archived parcel is checked daily.
   */
  private async sharedAlertPackages(parcels: readonly JsonObject[], signal?: AbortSignal): Promise<Set<string>> {
    const archived = parcels.filter((parcel) => parcel.archived_at != null).map((parcel) => String(parcel.id));
    if (archived.length === 0) return new Set();
    try {
      return await this.client.sharedAlertPackageIds(archived);
    } catch (error) {
      signal?.throwIfAborted();
      captureOperationalError(error, { component: 'tracking', operation: 'list_shared_alert_packages' });
      return new Set();
    }
  }

  /**
   * One-off parcels stay on the schedule while a link was opened lately, and
   * those filed under no carrier while they are new, followed or not: a check
   * can find their carrier. They come after every account's share and count as
   * one owner, the followed ones first, each the least recently checked first,
   * so lookups cannot starve accounts. Nor can they stop them: when a list
   * cannot be read, the others are still checked.
   */
  private async oneOffPackages(
    followedDue: (parcel: JsonObject) => boolean,
    unfollowedDue: (parcel: JsonObject) => boolean,
    now: Date,
    signal?: AbortSignal,
  ): Promise<JsonObject[]> {
    const list = async (operation: string, read: () => Promise<JsonObject[]>) => {
      try { return await read(); }
      catch (error) {
        signal?.throwIfAborted();
        captureOperationalError(error, { component: 'tracking', operation });
        return [];
      }
    };
    const followed = await list('list_one_off_packages',
      () => this.client.listFollowedOneOffPackages(new Date(now.getTime() - ONE_OFF_FOLLOWED_MS)));
    const listed = new Set(followed.map((parcel) => String(parcel.id)));
    const unfiled = await list('list_unfiled_one_off_packages',
      () => this.client.listUnfiledOneOffPackages(new Date(now.getTime() - REDETECTION_WINDOW_MS)));
    return [...followed.filter(followedDue), ...unfiled.filter((parcel) => !listed.has(String(parcel.id)) && unfollowedDue(parcel))]
      .slice(0, MAX_ONE_OFF_PACKAGES_PER_SYNC);
  }

  /**
   * Whether no check has run on the parcel since the run listed it. One that cannot be
   * read is left for the next run.
   */
  private async uncheckedSinceListed(parcel: JsonObject, signal?: AbortSignal): Promise<boolean> {
    try {
      const current = await this.client.getPackage(String(parcel.id));
      return current !== null && current.id === parcel.id && (current.last_synced_at ?? null) === (parcel.last_synced_at ?? null);
    } catch (error) {
      signal?.throwIfAborted();
      captureOperationalError(error, { component: 'tracking', operation: 'reread_unseen_recheck' });
      return false;
    }
  }

  /**
   * The parcels of the run's accounts that the run's reconciliation may merge, read when
   * a parcel's news could go out early, or null when they cannot be read.
   */
  private async mergeableLegs(parcels: readonly JsonObject[], signal?: AbortSignal): Promise<Set<string> | null> {
    const owners = [...new Set(parcels.flatMap((parcel) => typeof parcel.user_id === 'string' ? [parcel.user_id] : []))];
    if (owners.length === 0) return new Set();
    try {
      return mergeableLegs(await this.client.unarchivedParcelLegs(owners));
    } catch (error) {
      signal?.throwIfAborted();
      captureOperationalError(error, { component: 'tracking', operation: 'list_parcel_legs' });
      return null;
    }
  }

  private async linkConfirmedParcels(userId?: string): Promise<void> {
    try { await this.client.autoLinkPackages(userId); }
    catch (error) {
      // A transient linking failure must not erase a successful tracking update.
      // The next scheduled or manual refresh retries reconciliation.
      captureOperationalError(error, { component: 'tracking', operation: 'auto_link_packages' });
    }
  }

  private async exclusive<T>(operation: () => Promise<T>): Promise<T> {
    const prior = this.#tail;
    let release!: () => void;
    this.#tail = new Promise<void>((resolve) => { release = resolve; });
    await prior;
    try {
      return await operation();
    } finally {
      release();
    }
  }

  /**
   * Tells what the job found so far: notifications first, then the delivery emails, which
   * need no push channel. A run may dispatch several times, so the counts add up.
   */
  private async dispatchNotifications(summary: SyncSummary, signal?: AbortSignal): Promise<void> {
    await this.dispatchPush(summary, signal);
    await this.dispatchEmails(summary, signal);
  }

  private async dispatchEmails(summary: SyncSummary, signal?: AbortSignal): Promise<void> {
    signal?.throwIfAborted();
    if (!this.emails) return;
    try {
      const emails = await this.emails.dispatch(signal);
      summary.emails_sent += emails.sent;
      summary.email_errors += emails.failed;
    } catch (error) {
      signal?.throwIfAborted();
      // The job's tracking work is done: an email run that could not start is tried by the next job.
      summary.email_errors += 1;
      captureOperationalError(error, { component: 'delivery-email', operation: 'dispatch' });
    }
  }

  private async dispatchPush(summary: SyncSummary, signal?: AbortSignal): Promise<void> {
    signal?.throwIfAborted();
    if (!this.notifier) return;
    try {
      const push = await this.notifier.dispatch(signal);
      summary.notifications_sent += push.sent;
      summary.notification_errors += push.failed;
      summary.subscriptions_expired += push.expired;
      // A failed send stays pending and the next dispatch tries it again, which heals a push
      // service's blip: only deliveries that fail again are reported.
      this.#pushFailureStreak = push.failed > 0 ? this.#pushFailureStreak + 1 : 0;
      if (this.#pushFailureStreak > 1 && shouldReportRepeatedFailure(this.#pushFailureStreak - 1)) {
        captureOperationalError(new Error('Push dispatch returned failed deliveries'), {
          component: 'push',
          operation: 'dispatch',
          failureCount: this.#pushFailureStreak,
        });
      }
    } catch (error) {
      signal?.throwIfAborted();
      if (error instanceof PushDispatchError) {
        summary.notifications_sent += error.summary.sent;
        summary.notification_errors += error.summary.failed;
        summary.subscriptions_expired += error.summary.expired;
      }
      summary.notification_errors += 1;
      captureOperationalError(error, { component: 'push', operation: 'dispatch' });
    }
  }

  /**
   * Forgets the notification receipts of stored scans the check restaged into news, so
   * the next dispatch announces them by the usual rules. A failure leaves them announced
   * as they were, and never fails the check.
   */
  private async reopenAnnouncements(
    parcel: JsonObject,
    restaged: string[],
    context: SyncRunContext,
    audit: TrackingSyncAudit,
  ): Promise<JsonObject> {
    if (restaged.length === 0) return {};
    try {
      return { scans_reannounced: await this.client.reopenScanAnnouncements(String(parcel.id), restaged) };
    } catch (error) {
      if (context.signal?.aborted) throw error;
      audit.reportError(error, 'reopen_announcements');
      return { scans_reannounced: 0, reannouncement_failed: true };
    }
  }

  // Unmapped carrier wording is review material, never a reason to fail a
  // refresh: a failed write is logged each time and reported to Sentry once.
  // The events keep their computed identities, whose prefix names the carrier
  // that worded them; a reused identity only locates the sample row. Wording
  // the scraper mapped is sent too, so its open observation closes, and
  // wording its carrier map leaves unmapped on purpose closes as recorded.
  private async recordStatusObservations(
    events: JsonObject[],
    carrierId: string,
    context: SyncRunContext,
    reusedIdentities: ReadonlyMap<string, string> = new Map(),
    localEvents: JsonObject[] = [],
  ): Promise<void> {
    for (const event of events) {
      const raw = isRecord(event.raw_data) ? event.raw_data : {};
      if (typeof raw.stage_source === 'string') recordStatusMapping(carrierId, raw.stage_source);
    }
    const observations = collectStatusObservations([...events, ...localEvents], carrierId).map((observation) => {
      const stored = reusedIdentities.get(observation.provider_event_id);
      return stored ? { ...observation, provider_event_id: stored } : observation;
    });
    const mapped = collectMappedStatusKeys([...events, ...localEvents], carrierId);
    if (observations.length === 0 && mapped.length === 0) return;
    const version = deployedVersion();
    try {
      await this.client.recordTrackingStatusObservations(observations, mapped, version);
      for (const closing of statusMapClosings(observations)) {
        await this.client.closeTrackingStatusObservations(version, closing);
      }
    } catch (error) {
      if (context.signal?.aborted) return;
      logOperationalEvent('tracking_status_observation_write_failed', {
        carrier: carrierId,
        trigger: context.trigger,
        job_id: context.jobId ?? null,
        observations: observations.length,
        error_type: errorType(error),
      }, 'error');
      if (this.#reportedObservationFailure) return;
      this.#reportedObservationFailure = true;
      captureOperationalError(error, {
        component: 'tracking-status-observations',
        operation: 'record',
        carrier: carrierId,
      });
    }
  }

  private async syncOne(parcel: JsonObject, context: SyncRunContext, lookups?: SharedLookups): Promise<SyncOutcome> {
    context.signal?.throwIfAborted();
    const id = String(parcel.id ?? '');
    if (!id) throw new TypeError('A package id is required for synchronization');
    const now = this.now();
    const routing = routingState(parcel);
    const audit = new TrackingSyncAudit(
      this.client,
      id,
      String(parcel.tracking_number ?? ''),
      String(parcel.carrier ?? '') || 'unknown',
      String(parcel.current_stage ?? 'pending'),
      context,
      now,
      routing.confirmed_number === parcel.tracking_number ? routing.confirmed_carrier ?? null : null,
    );
    await audit.start();
    // A parcel just added, or looked up, with no progress saved yet: its first answer.
    const firstAnswer = context.trigger === 'added' && String(parcel.current_stage ?? 'pending') === 'pending';
    try {
      const outcome = await untilShutdown(this.check(parcel, context, audit, now, lookups), context.signal);
      // A first check that failed or found nothing may be a mistyped number: counted for review, never suggested.
      if (firstAnswer && outcome !== 'updated' && outcome !== 'superseded') {
        noteTypoCandidate(String(parcel.tracking_number ?? ''), typeof parcel.user_id === 'string' ? 'account' : 'lookup');
      }
      return outcome;
    } catch (error) {
      // Shutdown ends the check now, whatever its carrier is still doing: the job goes back to the queue.
      if (context.signal?.reason instanceof WorkerShutdown) await audit.interrupt();
      // A deletion or carrier change that the lease renewal noticed before the check's next write
      // ends it as that write would have, so its attempt is not left running.
      else if (await this.withdrawn(context)) await audit.finish({ outcome: 'superseded' });
      throw error;
    }
  }

  /** Whether the check stopped because a deletion or a carrier change took its job. */
  private async withdrawn(context: SyncRunContext): Promise<boolean> {
    if (!(context.signal?.reason instanceof SyncJobLeaseLost) || !context.lease) return false;
    return await this.client.syncJobWithdrawn(context.lease.jobId).catch(() => false);
  }

  /** One parcel's check, from choosing its carrier to the end of its audit. */
  private async check(
    parcel: JsonObject,
    context: SyncRunContext,
    audit: TrackingSyncAudit,
    now: Date,
    lookups?: SharedLookups,
  ): Promise<SyncOutcome> {
    const carrierId = String(parcel.carrier ?? '');
    const previousStage = String(parcel.current_stage ?? 'pending');
    const addition = isRecord(parcel.carrier_data) && parcel.carrier_data.add_recognition_pending === true;
    let consumeAddition = addition;
    // The delivery probe the check's lookups last made or kept: whatever the check saves carries it, though the
    // origin failed or routing set its answer aside, so its gap counts it.
    let lookupProbe: JsonObject | null = null;

    const persist = async (
      values: JsonObject,
      newEvents: JsonObject[] = [],
      deleteDescriptions: string[] = [],
      deleteEventIds: string[] = [],
    ) => {
      context.signal?.throwIfAborted();
      if (lookupProbe) {
        values.carrier_data = { ...(isRecord(values.carrier_data) ? values.carrier_data : isRecord(parcel.carrier_data) ? parcel.carrier_data : {}),
          delivery_probe: lookupProbe };
      }
      if (consumeAddition) {
        const data = { ...(isRecord(values.carrier_data) ? values.carrier_data : isRecord(parcel.carrier_data) ? parcel.carrier_data : {}) };
        delete data.add_recognition_pending;
        values.carrier_data = data;
      }
      const applied = context.lease
        ? await this.client.applyTrackingSync(parcel, values, newEvents, deleteDescriptions, deleteEventIds, context.lease)
        : await this.client.applyTrackingSync(parcel, values, newEvents, deleteDescriptions, deleteEventIds);
      if (!applied) {
        throw new SupersededTrackingSync('Tracking configuration changed during the check');
      }
      if (consumeAddition) {
        parcel.carrier_data = values.carrier_data;
        consumeAddition = false;
      }
    };
    const superseded = async (): Promise<SyncOutcome> => {
      await audit.finish({ outcome: 'superseded' });
      return 'superseded';
    };

    const accountRequired = requiresAmazonAccount(carrierId, String(parcel.tracking_number ?? ''));
    if (accountRequired || (!AUTOMATIC_CARRIER_IDS.has(carrierId) && !this.adapter.fetchUniversal)) {
      audit.record('selected', 'succeeded', 0, { automatic: false });
      audit.skip('fetch', 'unsupported_carrier');
      audit.skip('normalize', 'unsupported_carrier');
      audit.skip('persist_events', 'unsupported_carrier');
      try {
        await audit.step('persist_package', async () => {
          await persist({
            sync_status: 'unsupported',
            sync_error: accountRequired ? AMAZON_ACCOUNT_MESSAGE : 'Automatic updates are unavailable for this carrier. Change the carrier or check the tracking link.',
            last_synced_at: null,
          });
        });
        await audit.finish({ outcome: 'unsupported' });
        return 'unsupported';
      } catch (error) {
        context.signal?.throwIfAborted();
        if (error instanceof SupersededTrackingSync) return superseded();
        audit.reportError(error, 'persist_package');
        await audit.finish({ outcome: 'error', error });
        throw error;
      }
    }

    let operation: 'selected' | 'fetch' | 'normalize' | 'persist_events' | 'persist_package'
      = 'selected';
    let result: CarrierResult | null = null;
    let sourceCarrierId: string | null = null;
    let reportedStage: string | null = null;
    let selectedStage: string | null = null;
    let events: JsonObject[] = [];
    let anomalies: SyncAnomalyCode[] = [];
    try {
      await audit.step('selected', async () => {
        await persist({ sync_status: 'syncing', sync_error: null });
      }, () => ({ automatic: true }));

      operation = 'fetch';
      let fetched: {
        correction?: { carrier: string; trackingUrl: string | null; postcode: string | null };
        result: CarrierResult;
        sourceCarrierId: string;
        swissPostReady: boolean | null;
        handoffFallbackErrorType: string | null;
        earlierResult?: CarrierResult;
        earlierCarrierId?: string;
        earlierFollowUp?: boolean;
      };
      const fetchStartedAt = performance.now();
      // The check sees the routing its number shares with the other copies in the run.
      const checks = lookups?.for(parcel);
      const subject = checks?.parcel ?? parcel;
      const once = <T>(key: readonly unknown[], lookup: () => Promise<T>) => checks ? checks.once(key, lookup) : lookup();
      // Where the parcel was looked up from stays the parcel's, even for a carrier asked without its data.
      const lookupCountry = normalizedLookupCountry(isRecord(subject.carrier_data) ? subject.carrier_data.lookup_country_hint : undefined);
      // So does its delivery probe's marker, so the probe's gap holds for that carrier too.
      const savedProbe = isRecord(subject.carrier_data) ? subject.carrier_data.delivery_probe : undefined;
      const withSavedProbe = (candidate: JsonObject) => isRecord(savedProbe) && isRecord(candidate.carrier_data)
        && candidate.carrier_data.delivery_probe === undefined
        ? { ...candidate, carrier_data: { ...candidate.carrier_data, delivery_probe: savedProbe } } : candidate;
      const keepProbe = (probe: JsonObject) => { lookupProbe = probe; };
      // A check nobody asked for of a number nobody has answered, an account's for a day or a lookup's
      // that left the schedule, contacts one provider at most.
      const sparse = !askedForCheck(context.trigger)
        && (unseenRecheckDue(subject, now) !== undefined || sparseLookupRecheck(subject, now));
      // Only a check someone asked for, or a lookup's, skips the scheduled cadence: a release's re-check keeps it.
      try {
        fetched = await audit.observeFetch(async () => this.adapter.fetchUniversal && carrierId !== 'amazon-shipping'
          ? await new TrackingRouter({
            direct: (candidate, carrier) => this.fetchResult(withSavedProbe(candidate), carrier, checks, askedForCheck(context.trigger), lookupCountry, keepProbe),
            universal: (source, number, timeout, postcode, timezone, carrier) => once(universalLookupKey(source, number, postcode, timezone, carrier),
              () => this.adapter.fetchUniversal!(source, number, timeout, postcode, timezone, carrier)),
            ...(checks ? { reusedUniversal: (source: UniversalSource, number: string, postcode: string | null, timezone: string | null, carrier: string | null) =>
              checks.reuse<CarrierResult>(universalLookupKey(source, number, postcode, timezone, carrier)) } : {}),
            health: this.client, now: this.now,
            takePrefetchedUniversal: takePreflightHistory, preflightInputNeeded, refuses: refusesNumber,
            withoutCopies: (watermark: number, value: RoutedResult, provider?: CarrierResult) => watermarkWithoutCopies(parcel, watermark, value, provider, now.getTime()),
            ...(this.adapter.recognize ? { recognize: (carrier: string, number: string, context?: TrackingContext) =>
              once(recognitionKey('http', carrier, number), () => this.adapter.recognize!(carrier, number, context)) } : {}),
            ...(this.adapter.recognizeBrowser ? { recognizeBrowser: (carrier: string, number: string, context?: TrackingContext, previousError?: unknown) =>
              once(recognitionKey('browser', carrier, number), () => this.adapter.recognizeBrowser!(carrier, number, context, previousError)) } : {}),
            enablePostalNinja: process.env.TRACKING_ENABLE_POSTAL_NINJA === 'true',
          }).fetch(subject, !askedForCheck(context.trigger), context.signal, addition, sparse)
          : await this.fetchResult(subject, carrierId, checks, askedForCheck(context.trigger), lookupCountry, keepProbe));
      } catch (error) {
        if (carrierId === 'amazon-shipping' && error instanceof CarrierError && error.reason === 'history_expired') {
          audit.skip('normalize', 'history_expired');
          audit.skip('persist_events', 'history_expired');
          await persist({ sync_status: 'unsupported', sync_error: AMAZON_HISTORY_EXPIRED, last_synced_at: this.now().toISOString() });
          await audit.finish({ outcome: 'unsupported' });
          return 'unsupported';
        }
        const hasProgress = previousStage !== 'pending';
        // The parcel's own carrier does not know it yet: fallback providers failing on the
        // same unannounced number is not an outage, and the routing state keeps their cooldowns.
        // Without a carrier to ask, every provider answering without history means the same.
        // A provider resting on a rate limit was not asked, and a universal provider that failed
        // (transport, challenge, unreadable answer) did not answer: neither says anything about
        // the number. A carrier's own adapter failing does. So does the parcel's own carrier asking
        // for an input: only the owner can supply it, so the missed checks show that request. A
        // refused number shows at the first check (below).
        const failures = error instanceof RoutingDeferred
          ? Object.entries(error.routing.failures).filter(([source, failure]) => failure.kind !== 'rate_limited'
            && (directCarrier(source) || ['not_found', 'no_history', 'input_required'].includes(failure.kind)))
            .map(([, failure]) => failure)
          : [];
        const routingUnannounced = error instanceof RoutingDeferred
          && error.routing.failures[carrierId]?.kind !== 'input_required'
          && (Boolean(error.routing.provider_input_needed) || error.routing.failures[carrierId]?.kind === 'not_found'
            || (failures.length > 0 && failures.every((failure) => ['not_found', 'no_history', 'input_required'].includes(failure.kind))));
        if (!hasProgress && (isUnannouncedTrackingError(error) || routingUnannounced)) {
          // A carrier saying the label has no scans is an answer, not a missed check.
          if (error instanceof RoutingDeferred) error.routing.consecutive_failures = 0;
          audit.record('fetch', 'succeeded', performance.now() - fetchStartedAt, {
            disposition: 'unannounced', ...sharedAnswers(checks),
          });
          audit.skip('normalize', 'unannounced');
          audit.skip('persist_events', 'unannounced');
          operation = 'persist_package';
          await audit.step('persist_package', async () => {
            await persist({
              last_synced_at: this.now().toISOString(),
              sync_status: 'waiting',
              sync_error: null,
              ...(error instanceof RoutingDeferred ? { carrier_data: {
                ...(isRecord(parcel.carrier_data) ? parcel.carrier_data : {}), routing: error.routing,
              } } : {}),
            });
          });
          await audit.finish({
            outcome: 'waiting',
            sourceCarrier: carrierId,
            eventsReceived: 0,
            eventsNormalized: 0,
          });
          return 'waiting';
        }
        audit.record('fetch', 'failed', performance.now() - fetchStartedAt,
          { ...(error instanceof RoutingDeferred ? deferredFetchDetails(error) : {}), ...sharedAnswers(checks) }, error);
        throw error;
      }

      ({ result, sourceCarrierId } = fetched);
      const { swissPostReady, handoffFallbackErrorType } = fetched;
      audit.record('fetch', 'succeeded', performance.now() - fetchStartedAt, {
        source_carrier: sourceCarrierId,
        // The carrier answered too, when a provider serves its answer on local clocks or in part.
        carrier_answered: result.carrier_answered === true,
        swiss_post_ready: swissPostReady,
        handoff_fallback_error_type: handoffFallbackErrorType,
        ...sharedAnswers(checks),
      });

      operation = 'normalize';
      const normalized = await audit.step('normalize', () => {
        const normalizedEvents = buildEvents(parcel, result!, sourceCarrierId!, now);
        const normalizedReportedStage = resultStage(result!);
        const newestEvents = [...normalizedEvents].sort(
          (left, right) => String(right.occurred_at).localeCompare(String(left.occurred_at)),
        );
        // A scan no rule reads yet is pending; it does not undo the progress
        // of the scans before it.
        const progressEvent = newestEvents.find((event) => event.stage !== 'pending');
        const latestEventStage = newestEvents[0] ? String(newestEvents[0].stage) : null;
        // A provider's explicit non-pending summary can be newer than its last
        // timestamped milestone. Timed progress remains authoritative only
        // while the provider's summary is still pending.
        const normalizedSelectedStage = normalizedReportedStage && result!.status !== 'pending'
          ? normalizedReportedStage
          : (progressEvent ? String(progressEvent.stage) : null) ?? normalizedReportedStage ?? latestEventStage;
        // The delivery carrier answers for the parcel's stage: the earlier
        // carrier's stage, seen without a time of its own, dates no row.
        const earlierEvents = fetched.earlierResult && fetched.earlierCarrierId
          ? buildEvents(parcel, fetched.earlierResult, fetched.earlierCarrierId) : [];
        return {
          events: [...earlierEvents, ...normalizedEvents],
          relays: relayedScans(earlierEvents, normalizedEvents),
          reportedStage: normalizedReportedStage,
          selectedStage: normalizedSelectedStage,
        };
      }, (value) => ({
        events_received: result?.events?.length ?? 0,
        events_normalized: value.events.length,
        provider_status: result?.status ?? 'unknown',
        reported_stage: value.reportedStage,
        selected_stage: value.selectedStage,
      }));
      events = normalized.events;
      reportedStage = normalized.reportedStage;
      selectedStage = normalized.selectedStage;
      const previousRouting = routingState(parcel);
      anomalies = detectSyncAnomalies(
        parcel,
        result,
        events,
        sourceCarrierId,
        selectedStage,
        now,
      );
      const universalProgressMissing = anomalies.includes('progress_disappeared')
        && typeof result.tracking_provider === 'string' && !directCarrier(String(parcel.carrier));
      const missedChecks = isRecord(result.routing) ? Number(result.routing.consecutive_failures) : previousRouting.consecutive_failures + 1;
      const lastSuccess = Date.parse(previousRouting.last_success_at ?? '');
      const recentProgress = Number.isFinite(lastSuccess) && now.getTime() - lastSuccess < freshnessWindow(now);
      // Thin answers use the same failure threshold as exhausted lookups.
      // They do not count as successful checks or reset the failure streak.
      if (universalProgressMissing && (missedChecks < 2 || recentProgress)) {
        anomalies = anomalies.map((code) => code === 'progress_disappeared' ? 'fallback_without_progress' : code);
      }

      const deleteDescriptions = sourceCarrierId === 'swiss-post' && (result.events?.length ?? 0) > 0
        ? [...SWISS_POST_GLOBAL_STATUSES] : [];
      const hasUpdate = Boolean(
        (selectedStage && selectedStage !== 'pending')
        || events.some((event) => event.stage !== 'pending'),
      );
      const handoff = supportsSwissPostHandoff(String(parcel.tracking_number ?? ''));
      const knownUpdate = hasUpdate || Boolean(handoff && swissPostReady);
      const progressDisappeared = anomalies.includes('progress_disappeared');
      // A fallback that knows less is no error: what is saved stays, as with an older snapshot.
      const fallbackWithoutProgress = anomalies.includes('fallback_without_progress');
      const keepSaved = progressDisappeared || fallbackWithoutProgress;
      // A reworded scan (DPD with and without the postcode) updates its stored row in place,
      // and a scan both a carrier and a universal provider reported is stored once.
      // A handoff's batch also carries the earlier carrier's scans, under that carrier's policy.
      // A scan meets the row an earlier take-over left under an old identity before any policy,
      // then the row whose template placeholder an adapter left unfilled, whatever the carrier.
      const stored = storedEventIdentities(parcel);
      const reworded = restatedIdentities(events, stored);
      const adopt = (matches: ReadonlyMap<string, string>) => {
        const taken = new Set(reworded.values());
        for (const [id, saved] of matches) if (!reworded.has(id) && !taken.has(saved)) reworded.set(id, saved);
      };
      adopt(filledPlaceholders(events, stored));
      const policyCarriers = fetched.earlierResult && fetched.earlierCarrierId && fetched.earlierCarrierId !== sourceCarrierId
        ? [sourceCarrierId, fetched.earlierCarrierId] : [sourceCarrierId];
      for (const policyCarrier of policyCarriers) adopt(sameInstantIdentities(events, stored, policyCarrier));
      // The earlier carrier's relay of a delivery scan stays its own row, at the same second or to the minute.
      const relaying = fetched.earlierCarrierId ?? earlierCarrier(parcel);
      const shared = sharedScans(events.filter((event) => !normalized.relays.has(String(event.provider_event_id))), stored, reworded, relaying);
      const matches = { reused: new Map([...reworded, ...shared.reused]), skipped: shared.skipped };
      // Copies a universal provider read in the wrong zone must not make a result
      // look fresher, or the carrier's own reply older, than it is: neither those
      // the sync matched nor those it could not tell from the carrier's scans.
      const misdated = misdatedCopies(events, stored, now.getTime());
      const previousData = isRecord(parcel.carrier_data) ? parcel.carrier_data : {};
      const previousEventTime = withoutCopyDrift(
        Date.parse(previousRouting.last_event_at ?? ''), events, stored, matches, { ...providerWatermark(previousData, stored), misdated },
      );
      // Read as routing wrote the watermark: a naive local clock in its source's zone.
      const returnedEventTime = withoutCopyDrift(
        Date.parse(eventTimestamp(result.last_update, resultTimezone(sourceCarrierId, result)) ?? ''), events, stored, matches, { misdated },
      );
      // Rows past the answer's newest scan that only its own provider wrote, and that its answer
      // no longer shows, are no scans: a notice the provider dated at the lookup, which a scraper
      // fix now leaves out. Over a summary without movement, an answer that moved is then not
      // older. A reused number's older history is: a row past it is another source's, or shown.
      const shown = new Set([...events.map((event) => String(event.provider_event_id)), ...matches.reused.values(), ...matches.skipped.values()]);
      const ahead = stored.filter((row) => !String(row.provider_event_id).startsWith('app:')
        && row.observed_without_provider_timestamp !== true && Date.parse(String(row.occurred_at)) > returnedEventTime);
      const answeredBy = result.tracking_provider;
      const retracted = previousStage === 'pending' && hasRoutingProgress(result) && typeof answeredBy === 'string'
        && ahead.length > 0 && Math.max(...ahead.map((row) => Date.parse(String(row.occurred_at)))) >= previousEventTime
        && ahead.every((row) => row.universal_provider === answeredBy && !shown.has(String(row.provider_event_id)));
      // The parcel's own carrier answered in this check: its own answer, or the scans it gave on local clocks beside a provider's.
      const filed = String(parcel.carrier);
      const capture = isRecord(result.direct_local_history) ? result.direct_local_history : {};
      const ownAnswer: CarrierResult | null = !directCarrier(filed) || result.original_carrier || previousData.original_carrier ? null
        : typeof result.tracking_provider !== 'string' ? (sourceCarrierId === filed ? result : null)
          : result.carrier_answered === true && capture.carrier === filed && capture.number === directHistoryNumber(parcel, result)
            && Array.isArray(capture.events) ? { events: capture.events.filter(isRecord) } : null;
      const carrierMoved = ownAnswer ? carrierMovedAt(filed, ownAnswer) : Number.NaN;
      // A saved delivery only a provider reported gives way to that answer when it disputes it, as
      // routing's does in a single check (disputedDelivery.ts): the provider's delivery rows go.
      const savedDeliveries = stored.filter((row) => row.stage === 'delivered');
      const disputedRows = previousStage === 'delivered' && selectedStage !== null && selectedStage !== 'delivered'
        && typeof previousData.tracking_provider === 'string' && savedDeliveries.length > 0
        && savedDeliveries.every((row) => String(row.provider_event_id).startsWith('unknown:')
          && Date.parse(String(row.occurred_at)) <= carrierMoved)
        ? savedDeliveries.map((row) => String(row.provider_event_id)) : [];
      // An answer of the parcel's own carrier that disputes the saved delivery is not older either:
      // the watermark came from the provider whose delivery it disputes.
      const olderSnapshot = Number.isFinite(previousEventTime) && Number.isFinite(returnedEventTime)
        && returnedEventTime < previousEventTime && !retracted && !disputedRows.length;
      // UPU wall time cannot prove freshness against another source. It may
      // establish initial progress, or update its own saved local-time summary.
      const unprovenUpuSummary = result.tracking_provider === 'UPU' && (
        (previousStage !== 'pending' && previousData.tracking_provider !== 'UPU')
        || (previousData.tracking_provider === 'UPU' && typeof previousData.last_update_local === 'string'
          && String(result.last_update_local ?? '') < previousData.last_update_local)
      );
      const localOnlyFallback = result.direct_local_fallback === true && hasUnresolvedDirectCurrent(sourceCarrierId, result);
      const previousLocalHistory = isRecord(previousData.direct_local_history) ? previousData.direct_local_history : {};
      const sameLocalSource = previousData.direct_local_fallback === true
        && previousLocalHistory.carrier === sourceCarrierId && previousLocalHistory.number === directHistoryNumber(parcel, result);
      const unprovenLocalSummary = localOnlyFallback && (
        (previousStage !== 'pending' && !sameLocalSource)
        || (sameLocalSource && result.summary_only === true && Array.isArray(previousLocalHistory.events) && previousLocalHistory.events.length > 0)
        || (sameLocalSource && directLocalSnapshotIsOlder(previousLocalHistory, result))
      );
      // A provider answering while the parcel's own carrier is down (UPS's 500s) copies the carrier's
      // scans, often in another stage. Over the carrier's summary, only a scan the carrier has not shown counts.
      const unprovenProviderSummary = typeof result.tracking_provider === 'string' && !fetched.correction
        && directCarrier(String(parcel.carrier)) && answeredByCarrier(parcel) && previousStage !== 'pending'
        && Number.isFinite(previousEventTime) && !(returnedEventTime > previousEventTime);
      const preserveSummary = keepSaved || olderSnapshot || unprovenUpuSummary || unprovenLocalSummary || unprovenProviderSummary
        || (['delivered', 'returned'].includes(previousStage) && selectedStage !== previousStage && !disputedRows.length);
      const carrierData: JsonObject = Object.fromEntries(
        Object.entries(result).filter(([key, value]) => key !== 'events' && value != null),
      );
      // The saved watermark takes the same correction, so later replies are compared with the carrier's clock.
      const routing = isRecord(result.routing) ? { ...result.routing } : null;
      if (routing) {
        // A fallback's scan that is kept out must not make the carrier's next reply look older.
        const keepWatermark = olderSnapshot || keepSaved || unprovenProviderSummary;
        const watermark = keepWatermark ? previousEventTime : withoutCopyDrift(
          Date.parse(String(routing.last_event_at ?? '')), events, stored, matches, { also: [returnedEventTime,
            fetched.earlierResult && !fetched.earlierFollowUp
              ? latestResultTime(fetched.earlierResult, fetched.earlierCarrierId ?? sourceCarrierId) : Number.NaN], misdated },
        );
        const saved = keepWatermark ? previousRouting.last_event_at : routing.last_event_at;
        if (Number.isFinite(watermark) && Date.parse(String(saved ?? '')) !== watermark) routing.last_event_at = new Date(watermark).toISOString();
        else if (saved !== undefined || keepWatermark) routing.last_event_at = saved;
        // A parcel whose lookups open a browser is checked less often on its last mile (see the schedule).
        if (audit.browserLookup) routing.browser_check = true;
        else if (audit.browserLookup === false) delete routing.browser_check;
        carrierData.routing = routing;
      }
      const postalHistory = upuHistory(parcel, result, now);
      if (postalHistory) carrierData.upu_history = postalHistory;
      const localHistory = directLocalHistory(parcel, result);
      if (localHistory) carrierData.direct_local_history = localHistory;
      // Linked journey identity, the postcode the owner gave a provider, and the delivery partner's probe belong to the
      // parcel, not an individual carrier response: a provider answering while the origin is down keeps the probe's gap.
      if (isRecord(parcel.carrier_data)) {
        for (const key of ['lookup_country_hint', 'universal_input', 'original_carrier', 'original_tracking_number', 'original_tracking_url', 'original_package_id', 'active_tracking_carrier', 'active_tracking_number', 'original_canonical_tracking_number', 'auto_changed_from', 'auto_changed_to', 'auto_changed_at', 'handed_over_at', 'earlier_checked_at', 'delivery_probe', DIRECT_HISTORY]) {
          if (parcel.carrier_data[key] != null && carrierData[key] == null) carrierData[key] = parcel.carrier_data[key];
        }
      }
      // Some carrier endpoints omit sender details on subsequent updates.
      const previousSender = isRecord(parcel.carrier_data) ? parcel.carrier_data.sender_name : undefined;
      if (result.sender_name === undefined && typeof previousSender === 'string') {
        carrierData.sender_name = previousSender;
      }
      // A provider taking over the summary may not know where the parcel is going; the carrier that said so still
      // does. A carrier the router swapped to tracks another shipment, so it does not inherit the destination.
      if (result.destination_country == null && typeof previousData.destination_country === 'string' && !fetched.correction) {
        carrierData.destination_country = previousData.destination_country;
      }
      if (handoff) {
        carrierData.active_tracking_carrier = sourceCarrierId;
        carrierData.swiss_post_ready = swissPostReady;
      }
      const values: JsonObject = {
        last_synced_at: this.now().toISOString(),
        sync_status: progressDisappeared ? 'error' : knownUpdate || fallbackWithoutProgress ? 'ok' : 'waiting',
        sync_error: progressDisappeared
          ? 'The carrier temporarily returned no tracking progress. Previous tracking details have been kept.'
          : null,
      };
      if (routing) {
        values.carrier_data = { ...(isRecord(parcel.carrier_data) ? parcel.carrier_data : {}), routing };
      }
      // A verified partner can confirm the same completed milestone with a
      // different timestamp. Keep the saved summary/watermark, but retain the
      // newly verified route and both event histories.
      if (preserveSummary && fetched.earlierCarrierId && result.original_carrier) {
        const data = isRecord(values.carrier_data) ? values.carrier_data : isRecord(parcel.carrier_data) ? parcel.carrier_data : {};
        const preservedData = { ...data };
        for (const key of ['lookup_country_hint', 'original_carrier', 'original_tracking_number', 'original_tracking_url',
          'original_canonical_tracking_number', 'active_tracking_carrier', 'active_tracking_number', 'handed_over_at']) {
          if (carrierData[key] != null) preservedData[key] = carrierData[key];
        }
        values.carrier_data = preservedData;
      }
      if (preserveSummary && isRecord(result.delivery_probe)) {
        values.carrier_data = {
          ...(isRecord(values.carrier_data) ? values.carrier_data : isRecord(parcel.carrier_data) ? parcel.carrier_data : {}),
          delivery_probe: result.delivery_probe,
        };
      }
      if (preserveSummary && typeof result.earlier_checked_at === 'string') {
        values.carrier_data = {
          ...(isRecord(values.carrier_data) ? values.carrier_data : isRecord(parcel.carrier_data) ? parcel.carrier_data : {}),
          earlier_checked_at: result.earlier_checked_at,
        };
      }
      const eventsToPersist = keepSaved || (preserveSummary && (result.tracking_provider === 'UPU' || localOnlyFallback)) ? [] : events;
      const persistedIds = new Set(eventsToPersist.map((event) => String(event.provider_event_id)));
      // The copies the carrier's scans absorbed go with the batch that holds those scans. So does a
      // stage change this carrier was seen to make without a time, once it dates that scan by its day.
      const daysDated = new Set(eventsToPersist.filter((event) => isRecord(event.raw_data)
        && event.raw_data.observed_without_provider_timestamp !== true && (scanDay(event.raw_data) || clockDay(event.raw_data))
        && !event.raw_data.time)
        .map((event) => JSON.stringify([event.stage, event.description])));
      const undated = stored.filter((row) => row.observed_without_provider_timestamp === true
        && String(row.provider_event_id).startsWith(`${sourceCarrierId}:`) && daysDated.has(JSON.stringify([row.stage, row.description])))
        .map((row) => String(row.provider_event_id));
      // A stage change seen without a time goes too once a dated scan holds it, or the scan
      // takes its row over.
      const dated = datedObservations(eventsToPersist.filter((event) => !normalized.relays.has(String(event.provider_event_id))),
        stored, matches);
      const absorbed = eventsToPersist.length > 0 ? [...new Set([...shared.absorbed, ...undated, ...dated.absorbed])] : [];
      const removed = preserveSummary ? absorbed : [...absorbed, ...disputedRows];
      const keptEvents = eventsToPersist.filter((event) => !matches.skipped.has(String(event.provider_event_id))
        && !dated.skipped.has(String(event.provider_event_id)));
      const persistedEvents = withIdentities(keptEvents, new Map([...matches.reused, ...dated.reused]), stored);
      // The universal rows the carriers' scans hold, so the timeline tells a provider's copies from them (providerCopies.ts).
      const history = directHistory(keptEvents, persistedEvents, previousData[DIRECT_HISTORY], stored);
      if (history) carrierData[DIRECT_HISTORY] = history;
      if (!preserveSummary) {
        values.last_status_text = result.last_status_text || null;
        values.expected_delivery = result.expected_delivery ? String(result.expected_delivery) : null;
        values.carrier_data = carrierData;
        if (selectedStage && (hasUpdate || !swissPostReady)) {
          // The parcel's rows once this check is saved.
          const written = new Set([...persistedEvents.map((event) => String(event.provider_event_id)), ...removed]);
          const rows = [...stored.filter((row) => !written.has(String(row.provider_event_id))
            && !deleteDescriptions.includes(String(row.description))), ...persistedEvents];
          // A carrier the router swapped to may rightly know less than a mistaken one did.
          values.current_stage = fetched.correction ? selectedStage : stageToSave(heldStage(parcel, previousStage, rows), selectedStage);
          if (values.current_stage !== selectedStage) anomalies = [...anomalies, 'early_stage_regression'];
        }
        // A delivery only a provider reported, which the parcel's own carrier did not show, is
        // checked again daily for a while: the carrier may yet dispute it (activePackages).
        const followed = previousStage === 'delivered' ? previousData.unconfirmed_delivery_until
          : new Date(now.getTime() + UNCONFIRMED_DELIVERY_MS).toISOString();
        if (values.current_stage === 'delivered' && typeof result.tracking_provider === 'string' && typeof followed === 'string'
          && directCarrier(filed) && !result.original_carrier && !showsDelivery(ownAnswer)) carrierData.unconfirmed_delivery_until = followed;
        // A parcel collected from its pickup point keeps the point once the carrier stops naming it.
        const collectedAt = collectedPickupPoint(parcel, carrierData, String(values.current_stage ?? previousStage),
          fetched.correction?.carrier ?? carrierId, [...stored, ...events]);
        if (collectedAt) carrierData.pickup_point = collectedAt;
      }
      if (preserveSummary && postalHistory) {
        values.carrier_data = {
          ...(isRecord(values.carrier_data) ? values.carrier_data : previousData), upu_history: postalHistory,
        };
      }
      if (preserveSummary && localHistory) {
        values.carrier_data = {
          ...(isRecord(values.carrier_data) ? values.carrier_data : previousData), direct_local_history: localHistory,
        };
      }
      if (preserveSummary && history) {
        values.carrier_data = { ...(isRecord(values.carrier_data) ? values.carrier_data : previousData), [DIRECT_HISTORY]: history };
      }
      // An older reply still says where the parcel is going, when the newer summary kept does not.
      if (preserveSummary && !keepSaved && !fetched.correction && typeof result.destination_country === 'string') {
        const kept = isRecord(values.carrier_data) ? values.carrier_data : previousData;
        if (kept.destination_country == null) values.carrier_data = { ...kept, destination_country: result.destination_country };
      }
      // A provider naming an unknown parcel's carrier identifies the number, however old its
      // copy of the history looks: the parcel is filed under it and keeps the newer summary.
      const identifiedOnOlderCopy = preserveSummary && olderSnapshot && !keepSaved && !unprovenUpuSummary
        && !unprovenLocalSummary && providerIdentification(parcel, result, fetched.correction, routing, persistedEvents, stored, this.now());
      if (fetched.correction && (!preserveSummary || identifiedOnOlderCopy)) {
        values.carrier = fetched.correction.carrier;
        values.tracking_url = fetched.correction.trackingUrl;
        values.dpd_postcode = fetched.correction.postcode;
        if (identifiedOnOlderCopy && isRecord(values.carrier_data)) {
          values.carrier_data = { ...values.carrier_data,
            discovered_carrier: result.discovered_carrier, reported_carriers: result.reported_carriers };
        }
      } else if (fetched.correction && isRecord(values.carrier_data) && isRecord(values.carrier_data.routing)) {
        // A rejected/older summary must not advertise a swap that was not saved.
        values.carrier_data.routing.configured_carrier = carrierId;
      }
      // A parcel left under unknown keeps no carrier confirmed for it, such as one whose
      // local-clock answer could not replace the saved summary: routing would stop filing it
      // any other way, and the database files a parcel a provider names only while none is.
      // What was confirmed stays apart (`cleared_confirmation`): confirming it again is no news.
      if (!values.carrier && carrierId === 'unknown' && routing) clearConfirmation(routing);
      // A scan already stored is rewritten in place, which is no news.
      const storedIds = new Set(stored.map((event) => String(event.provider_event_id)));
      const eventsNew = persistedEvents.filter((event) => !storedIds.has(String(event.provider_event_id))).length;
      // Unless it turns into the delivery or pickup readiness the parcel had not shown yet.
      const restaged = restagedNews(stored, persistedEvents, previousStage, this.now());
      const news = eventsNew > 0 || restaged.length > 0
        || (values.current_stage !== undefined && values.current_stage !== previousStage);
      const outcome = progressDisappeared ? 'error'
        : !knownUpdate || fallbackWithoutProgress ? 'waiting' : news ? 'updated' : 'unchanged';
      operation = 'persist_package';
      await audit.step('persist_package', async () => {
        await persist(values, persistedEvents, keepSaved ? [] : deleteDescriptions, removed);
      }, () => ({
        outcome,
        selected_stage: selectedStage,
      }));
      // A correction to the carrier detection names or asks first is expected: it is logged and is no signal.
      if (values.carrier) audit.reportRouting('carrier_auto_swapped', {
        carrier: carrierId, provider: String(values.carrier), trackingNumber: String(parcel.tracking_number ?? ''),
        ...(detectionExpects(String(parcel.tracking_number ?? ''), String(values.carrier)) ? { category: 'detected' } : {}),
      });
      const reannounced = await this.reopenAnnouncements(parcel, restaged, context, audit);
      audit.record('persist_events', 'succeeded', 0, {
        events_persisted: persistedEvents.length,
        events_new: eventsNew,
        identities_reused: [...matches.reused.keys()].filter((id) => persistedIds.has(id)).length,
        copies_skipped: [...matches.skipped.keys()].filter((id) => persistedIds.has(id)).length,
        copies_absorbed: eventsToPersist.length > 0 ? shared.absorbed.size : 0,
        atomic_with_package: true,
        ...reannounced,
      });
      // One scan from two sources on clocks a zone apart: a provider's clock the scraper should fix. So is
      // a provider's scan stored whole hours after the carrier's copy of it, or dated past the check.
      const misread = eventsToPersist.some((event) => {
        const at = misdated.get(String(event.provider_event_id));
        return at !== undefined && !(Date.parse(String(event.occurred_at)) - at < 60 * 60 * 1_000);
      });
      if ([...shared.shifted].some((id) => persistedIds.has(id)) || misread) anomalies = [...anomalies, 'provider_clock_offset'];
      await this.recordStatusObservations(eventsToPersist, sourceCarrierId, context, new Map([...matches.reused, ...matches.skipped]), [
        ...(fetched.earlierResult && fetched.earlierCarrierId
          ? localStatusObservationEvents(parcel, fetched.earlierResult, fetched.earlierCarrierId) : []),
        ...localStatusObservationEvents(parcel, result, sourceCarrierId),
      ]);
      const completion = {
        outcome,
        sourceCarrier: sourceCarrierId,
        providerStatus: result.status ?? 'unknown',
        reportedStage,
        selectedStage,
        statusText: result.last_status_text,
        eventsReceived: result.events?.length ?? 0,
        eventsNormalized: events.length,
        eventsNew,
        anomalyCodes: anomalies,
        ...(progressDisappeared ? { errorType: 'ProgressDisappeared' } : {}),
        providerFailures: routing?.failures,
        supportEvidence: trackingSupportEvidence(parcel, result, sourceCarrierId, outcome, preserveSummary),
      } as const;
      await audit.finish(completion);
      audit.reportAnomalies(anomalies, completion);
      return outcome === 'error' ? 'errors' : outcome;
    } catch (error) {
      context.signal?.throwIfAborted();
      if (error instanceof SupersededTrackingSync) return superseded();
      if (error instanceof RoutingDeferred) {
        // A parcel with no scans whose own carrier refuses the number, such as one chosen by hand
        // that issues no number of its shape, shows why at the first check: only its details can change that.
        const refused = previousStage === 'pending' && error.routing.failures[carrierId]?.user_error === 'carrier:invalid_input';
        const failing = error.stale || refused;
        const syncError = failing ? await failureCopy(deferredTrackingFailure(error.routing.failures, carrierId), carrierId, parcel) ?? error.message : null;
        try {
          await persist({ last_synced_at: this.now().toISOString(),
            sync_status: failing ? 'error' : previousStage === 'pending' ? 'waiting' : 'ok',
            sync_error: syncError,
            carrier_data: { ...(isRecord(parcel.carrier_data) ? parcel.carrier_data : {}), routing: error.routing },
          });
        } catch (persistenceError) {
          if (persistenceError instanceof SupersededTrackingSync) return superseded();
          audit.reportError(persistenceError, 'persist_routing_state');
          throw persistenceError;
        }
        // A check that contacted nobody says nothing about provider health.
        // Keep the failure evidence even while the parcel's error chip is suppressed.
        await audit.finish({ outcome: failing ? 'error' : 'waiting', sourceCarrier: carrierId,
          evaluateHealth: error.attempted > 0, error, providerFailures: error.routing.failures });
        return failing ? 'errors' : 'waiting';
      }
      let message = error instanceof Error ? error.message.trim() || error.name : String(error);
      if (error instanceof SyntaxError) {
        message = 'The carrier returned a maintenance page instead of tracking data.';
      }
      try {
        await audit.step('persist_package', async () => {
          await persist({
            last_synced_at: this.now().toISOString(),
            sync_status: 'error',
            sync_error: await failureCopy(trackingFailureCode(error), carrierId, parcel) ?? message.slice(0, 500),
          });
        }, () => ({ purpose: 'record_error' }));
      } catch (persistenceError) {
        if (persistenceError instanceof SupersededTrackingSync) return superseded();
        audit.reportError(error, operation);
        audit.reportError(persistenceError, 'persist_error_state');
        await audit.finish({
          outcome: 'error',
          sourceCarrier: sourceCarrierId,
          providerStatus: result?.status ?? null,
          reportedStage,
          selectedStage,
          statusText: result?.last_status_text,
          eventsReceived: result?.events?.length ?? 0,
          eventsNormalized: events.length,
          anomalyCodes: anomalies,
          error,
        });
        throw persistenceError;
      }
      audit.reportError(error, operation);
      await audit.finish({
        outcome: 'error',
        sourceCarrier: sourceCarrierId,
        providerStatus: result?.status ?? null,
        reportedStage,
        selectedStage,
        statusText: result?.last_status_text,
        eventsReceived: result?.events?.length ?? 0,
        eventsNormalized: events.length,
        anomalyCodes: anomalies,
        error,
      });
      return 'errors';
    }
  }

  /** A carrier lookup, asked once per run for the same carrier, number and inputs. */
  private fetchCarrier(checks: ParcelLookups | undefined, carrierId: string, trackingNumber: string,
    trackingUrl: string | null, dpdPostcode: string | null): Promise<CarrierResult> {
    const lookup = () => this.adapter.fetch(carrierId, trackingNumber, trackingUrl, dpdPostcode);
    if (!checks) return lookup();
    const key = carrierLookupKey(carrierId, trackingNumber, trackingUrl, dpdPostcode);
    if (!this.adapter.browserOnly?.(carrierId)) return checks.once(key, lookup);
    // A run asks a carrier tracked only through its own browser once while that browser fails:
    // each failed lookup can take the browser's whole timeout.
    const outage = checks.outage(carrierId);
    if (outage) return Promise.reject(outage);
    return checks.once(key, lookup).catch((error: unknown) => {
      if (['transport', 'budget', 'maintenance'].includes(carrierErrorKind(error) ?? '')) checks.down(carrierId, error);
      throw error;
    });
  }

  private async fetchResult(
    parcel: JsonObject,
    carrierId: string,
    checks?: ParcelLookups,
    byHand = false,
    lookupCountry: string | null = null,
    onProbe?: (probe: JsonObject) => void,
  ): Promise<{
    result: CarrierResult;
    sourceCarrierId: string;
    swissPostReady: boolean | null;
    handoffFallbackErrorType: string | null;
    earlierResult?: CarrierResult;
    earlierCarrierId?: string;
    earlierFollowUp?: boolean;
  }> {
    const trackingNumber = String(parcel.tracking_number ?? '');
    const metadata = isRecord(parcel.carrier_data) ? parcel.carrier_data : {};
    const legacySwissReady = metadata.swiss_post_ready === true && supportsSwissPostHandoff(trackingNumber)
      && ['swiss-post', 'aliexpress', 'intl-post'].includes(carrierId);
    const activeCarrier = typeof metadata.active_tracking_carrier === 'string' ? metadata.active_tracking_carrier
      : legacySwissReady ? 'swiss-post' : '';
    const activeNumber = typeof metadata.active_tracking_number === 'string' ? metadata.active_tracking_number : trackingNumber;
    if ((metadata.original_carrier || legacySwissReady) && hasDirectHandoffAdapter(activeCarrier, activeNumber)) {
      // The earlier carrier still publishes late scans: ask it too, as at the handoff, for a while.
      const followUp = metadata.original_carrier === carrierId && carrierId !== activeCarrier
        && metadata.original_tracking_number === trackingNumber && asksEarlierCarrier(metadata, this.now());
      const [result, earlier] = await Promise.all([
        this.fetchCarrier(checks, activeCarrier, activeNumber, null, null),
        followUp ? this.fetchCarrier(checks, carrierId, trackingNumber,
          typeof parcel.tracking_url === 'string' ? parcel.tracking_url : null,
          typeof parcel.dpd_postcode === 'string' ? parcel.dpd_postcode : null,
        ).then(normalizeCarrierResult).catch((error: unknown) => {
          // The delivery carrier answers for the parcel: a miss here only waits for the next follow-up.
          if (!isUnannouncedTrackingError(error)) reportRoutingEvent('provider_failed', {
            carrier: carrierId, provider: carrierId, trackingNumber, category: routingFailure(error).kind,
            errorClass: errorType(error), error,
          });
          return null;
        }) : null,
      ]);
      return {
        result: { ...normalizeCarrierResult(result), ...(followUp ? { earlier_checked_at: this.now().toISOString() } : {}) },
        sourceCarrierId: activeCarrier, swissPostReady: activeCarrier === 'swiss-post' ? true : null, handoffFallbackErrorType: null,
        ...(earlier ? { earlierResult: earlier, earlierCarrierId: carrierId, earlierFollowUp: true } : {}),
      };
    }
    // Cainiao remains a fallback for an explicitly selected Swiss postal route.
    // An issuer suffix must not choose the delivery partner for other carriers.
    if (!supportsSwissPostHandoff(trackingNumber) || carrierId !== 'swiss-post') {
      let origin: CarrierResult;
      let originError: unknown;
      let savedPartner: DeliveryHandoff | null = null;
      try {
        origin = normalizeCarrierResult(await this.fetchCarrier(checks,
          carrierId, trackingNumber,
          typeof parcel.tracking_url === 'string' ? parcel.tracking_url : null,
          typeof parcel.dpd_postcode === 'string' ? parcel.dpd_postcode : null,
        ));
      } catch (error) {
        // An international operator outage must not hide a confirmed local delivery, nor
        // the post of the country a postal item is looked up from when nothing names one.
        savedPartner = deliveryHandoff(carrierId, trackingNumber, metadata, lookupCountry);
        if (!savedPartner) throw error;
        originError = error;
        origin = { delivery_carrier: savedPartner.carrier, delivery_tracking_number: savedPartner.number };
      }
      let fallbackError: string | null = null;
      // The country the parcel was looked up from proposes a postal item's destination post only when nothing names one.
      const candidate = savedPartner ?? deliveryHandoff(carrierId, trackingNumber, origin, lookupCountry);
      const previousProbe = candidate ? previousDeliveryProbe(parcel, candidate.carrier, candidate.number) : null;
      const originUpdate = typeof origin.last_update === 'string' ? origin.last_update : previousProbe?.origin_update ?? null;
      const sinceProbe = previousProbe ? this.now().getTime() - Date.parse(previousProbe.at) : Number.NaN;
      const misses = previousProbe?.misses ?? 1;
      // While the origin is down, the saved partner alone can show the delivery: its gap does not grow.
      const gap = byHand || previousProbe?.announced ? DELIVERY_PROBE_SHORT_INTERVAL_MS
        : Math.min(DELIVERY_PROBE_MAX_INTERVAL_MS, DELIVERY_PROBE_INTERVAL_MS * 2 ** (originError ? 0 : misses - 1));
      const askedRecently = previousProbe !== null && originUpdate === previousProbe.origin_update
        && sinceProbe >= 0 && sinceProbe < gap;
      if (candidate && askedRecently) {
        // carrier_data is rebuilt from the result on every refresh: carry the marker forward.
        origin.delivery_probe = { ...previousProbe, carrier: candidate.carrier, number: candidate.number };
      } else if (candidate) {
        const probe: DeliveryProbe = { at: this.now().toISOString(), origin_update: originUpdate, carrier: candidate.carrier, number: candidate.number,
          misses: previousProbe && originUpdate === previousProbe.origin_update ? misses + 1 : 1 };
        origin.delivery_probe = probe;
        try {
          const delivery = normalizeCarrierResult(await this.fetchCarrier(checks, candidate.carrier, candidate.number, null, null));
          const originTime = latestResultTime(origin, carrierId);
          const watermark = Math.max(originTime, latestResultTime(metadata, carrierId),
            Date.parse(routingState(parcel).last_event_at || '') || 0);
          const terminal = ['delivered', 'returned'].find((stage) => stage === resultStage(origin) || stage === parcel.current_stage);
          const deliveryTime = latestResultTime(delivery, candidate.carrier);
          // Origin feeds sometimes re-stamp the partner's local completion
          // time with another offset. Explicit, same-day terminal agreement
          // confirms the route without rewriting either provider's timestamps.
          const sameCompletion = terminal && resultStage(origin) === terminal
            && resultStage(delivery) === terminal && deliveryTime > 0 && originTime > 0
            && new Date(deliveryTime).toISOString().slice(0, 10) === new Date(originTime).toISOString().slice(0, 10)
            && new Date(watermark).toISOString().slice(0, 10) === new Date(originTime).toISOString().slice(0, 10);
          const scanned = delivery.status !== 'pending' && !['pending', 'registered'].includes(resultStage(delivery) ?? 'pending');
          // A partner relaying the origin's scans may keep only their minute (CTT's copy of Royal
          // Mail's departure): a time on a whole minute is compared to the minute.
          const fresh = deliveryTime >= (deliveryTime % 60_000 === 0 ? watermark - watermark % 60_000 : watermark);
          if (scanned && (candidate.basis !== 'destination' || deliveryTime > 0)
            && resultHasUpdate(delivery) && (fresh || sameCompletion)
            && (!terminal || resultStage(delivery) === terminal)) {
            // The local delivery hides the origin failure from the router, which reports every other one.
            if (originError && !isUnannouncedTrackingError(originError)) reportRoutingEvent('provider_failed', {
              carrier: carrierId, provider: carrierId, trackingNumber, category: routingFailure(originError).kind,
              errorClass: errorType(originError), error: originError,
            });
            return {
              result: {
                ...delivery, active_tracking_carrier: candidate.carrier,
                active_tracking_number: delivery.canonical_tracking_number || candidate.number,
                ...(origin.canonical_tracking_number ? { original_canonical_tracking_number: origin.canonical_tracking_number } : {}),
                original_carrier: carrierId, original_tracking_number: trackingNumber,
                original_tracking_url: typeof parcel.tracking_url === 'string' ? parcel.tracking_url : null,
                handed_over_at: this.now().toISOString(),
                ...(delivery.sender_name === undefined && origin.sender_name ? { sender_name: origin.sender_name } : {}),
              },
              sourceCarrierId: candidate.carrier, swissPostReady: candidate.carrier === 'swiss-post' ? true : null, handoffFallbackErrorType: null,
              earlierResult: origin, earlierCarrierId: carrierId,
            };
          }
          // It has the parcel on record with news of its own and no scan yet: its first scan comes next.
          if (!scanned && deliveryTime > 0 && fresh) Object.assign(probe, { announced: true, misses: 1 });
        } catch (error) {
          fallbackError = errorType(error);
          // A partner that does not know the item yet, or answers inconclusively
          // (a national post's miss), is waited for, not reported as failing.
          if (!isUnannouncedTrackingError(error) && !answeredWithoutHistory(error)) reportRoutingEvent('provider_failed', {
            carrier: carrierId, provider: candidate.carrier, trackingNumber, category: routingFailure(error).kind,
            errorClass: fallbackError, error,
          });
        }
      }
      // The check saves the probe's marker although the origin's error carries no result, or routing sets the result aside.
      if (isRecord(origin.delivery_probe)) onProbe?.(origin.delivery_probe);
      if (originError) throw originError;
      return {
        result: origin, sourceCarrierId: carrierId,
        swissPostReady: null, handoffFallbackErrorType: fallbackError,
      };
    }
    const wasReady = isRecord(parcel.carrier_data) && parcel.carrier_data.swiss_post_ready === true;
    if (wasReady) {
      const result = await this.fetchCarrier(checks, 'swiss-post', trackingNumber, null, null);
      return {
        result: normalizeCarrierResult(result),
        sourceCarrierId: 'swiss-post',
        swissPostReady: true,
        handoffFallbackErrorType: null,
      };
    }
    const fetchCainiao = async () => normalizeCarrierResult(await this.fetchCarrier(checks, 'aliexpress', trackingNumber, null, null));
    const previousProbe = previousDeliveryProbe(parcel, 'swiss-post', trackingNumber);
    const misses = previousProbe?.misses ?? 1;
    const gap = byHand ? DELIVERY_PROBE_SHORT_INTERVAL_MS
      : Math.min(DELIVERY_PROBE_MAX_INTERVAL_MS, DELIVERY_PROBE_INTERVAL_MS * 2 ** (misses - 1));
    const sinceProbe = previousProbe ? this.now().getTime() - Date.parse(previousProbe.at) : Number.NaN;
    // Within the gap after Swiss Post missed the item, Cainiao answers first. Its news,
    // the usual sign of the handover, asks Swiss Post again at once.
    let cainiao = sinceProbe >= 0 && sinceProbe < gap ? await fetchCainiao() : null;
    if (cainiao && (typeof cainiao.last_update !== 'string' || cainiao.last_update === previousProbe!.origin_update)) {
      return {
        // carrier_data is rebuilt from the result on every refresh: carry the marker forward.
        result: { ...cainiao, delivery_probe: { ...previousProbe!, carrier: 'swiss-post', number: trackingNumber } },
        sourceCarrierId: 'aliexpress',
        swissPostReady: false,
        handoffFallbackErrorType: null,
      };
    }
    const askedAt = this.now().toISOString();
    let handoffFallbackErrorType: string | null = null;
    try {
      const swiss = normalizeCarrierResult(
        await this.fetchCarrier(checks, 'swiss-post', trackingNumber, null, null),
      );
      if (resultHasUpdate(swiss)) {
        return {
          result: swiss,
          sourceCarrierId: 'swiss-post',
          swissPostReady: true,
          handoffFallbackErrorType: null,
        };
      }
    } catch (error) {
      handoffFallbackErrorType = errorType(error);
      if (!isUnannouncedTrackingError(error)) reportRoutingEvent('provider_failed', {
        carrier: carrierId, provider: 'swiss-post', trackingNumber, category: routingFailure(error).kind,
      });
      // Cainiao still covers the international leg if Swiss Post is not ready.
    }
    // Swiss Post was asked: Cainiao failing saves that ask, without a miss while Cainiao cannot say whether it moved.
    cainiao ??= await fetchCainiao().catch((error: unknown) => {
      onProbe?.({ at: askedAt, origin_update: previousProbe?.origin_update ?? null, carrier: 'swiss-post', number: trackingNumber, misses });
      throw error;
    });
    const originUpdate = typeof cainiao.last_update === 'string' ? cainiao.last_update : previousProbe?.origin_update ?? null;
    const probe: DeliveryProbe = { at: askedAt, origin_update: originUpdate, carrier: 'swiss-post', number: trackingNumber,
      misses: previousProbe && originUpdate === previousProbe.origin_update ? misses + 1 : 1 };
    return {
      result: { ...cainiao, delivery_probe: probe },
      sourceCarrierId: 'aliexpress',
      swissPostReady: false,
      handoffFallbackErrorType,
    };
  }
}

const RESTAGED_NEWS = new Set(['delivered', 'ready_for_pickup']);
const RESTAGED_NEWS_MS = 24 * 60 * 60 * 1_000;

/**
 * The stored scans a check restages into a delivery or a pickup readiness that the parcel
 * has not shown, neither as its stage nor in another scan, from the last day: news worth
 * announcing once. Any other scan rewritten in place stays announced as it was.
 */
export function restagedNews(
  stored: readonly JsonObject[],
  persisted: readonly JsonObject[],
  previousStage: string,
  now: Date,
): string[] {
  const storedStages = new Map(stored.map((event) => [String(event.provider_event_id), String(event.stage)]));
  const shown = new Set([previousStage, ...storedStages.values()]);
  const since = now.getTime() - RESTAGED_NEWS_MS;
  return persisted.flatMap((event) => {
    const id = String(event.provider_event_id);
    const stage = String(event.stage);
    const before = storedStages.get(id);
    return before !== undefined && before !== stage && RESTAGED_NEWS.has(stage) && !shown.has(stage)
      && Date.parse(String(event.occurred_at)) >= since ? [id] : [];
  });
}

const IDENTIFYING_PROVIDERS = new Set(['Ship24', 'ParcelsApp', '17TRACK', 'Postal Ninja']);

/**
 * Whether a correction files an unknown parcel under the carrier a provider names, as the
 * database's guard accepts it beside a saved summary: the saved summary is a provider's, and
 * this check brings movement, or the movement it keeps is a stored scan.
 */
function providerIdentification(
  parcel: JsonObject, result: CarrierResult, correction: RoutedResult['correction'], routing: JsonObject | null,
  events: readonly JsonObject[], stored: readonly JsonObject[], now: Date,
): boolean {
  const saved = isRecord(parcel.carrier_data) ? parcel.carrier_data : {};
  const names = result.reported_carriers;
  if (!correction || !routing || parcel.carrier !== 'unknown' || correction.trackingUrl !== null || correction.postcode !== null
    || result.discovered_carrier !== correction.carrier || routing.discovered_carrier !== correction.carrier
    || routing.preferred_number !== parcel.tracking_number || routing.confirmed_carrier != null
    || (isRecord(saved.routing) && saved.routing.confirmed_carrier != null) || saved.original_carrier != null
    || !IDENTIFYING_PROVIDERS.has(String(saved.tracking_provider))
    || !Array.isArray(names) || names.length < 1 || names.length > 4 || names.some((name) => typeof name !== 'string')) return false;
  const progress = (event: JsonObject) => !['pending', 'registered'].includes(String(event.stage)) && typeof event.occurred_at === 'string';
  const watermark = Date.parse(String(routing.last_event_at ?? ''));
  return events.some((event) => progress(event) && Date.parse(String(event.occurred_at)) <= now.getTime() + 3_600_000)
    || stored.some((event) => progress(event) && Date.parse(String(event.occurred_at)) === watermark);
}

/** The stored event identities a sync loader embedded; none when the parcel came from elsewhere. */
function storedEventIdentities(parcel: JsonObject): JsonObject[] {
  const stored = parcel[STORED_EVENT_IDENTITIES];
  return Array.isArray(stored) ? stored.filter(isRecord) : [];
}

/**
 * A routing watermark without what a universal provider's copies of a
 * carrier's own scans, read in the wrong zone, add to it, matched as the sync
 * matches them: in the stored rows and in `provider`'s answer of the same check
 * made at `checkedAt`.
 */
function watermarkWithoutCopies(parcel: JsonObject, watermark: number, value: RoutedResult, provider: CarrierResult | undefined, checkedAt: number): number {
  const events = buildEvents(parcel, value.result, value.sourceCarrierId);
  const stored = storedEventIdentities(parcel);
  const known = new Set(stored.map((row) => row.provider_event_id));
  const rows = [...stored, ...(provider ? buildEvents(parcel, provider, 'unknown') : []).filter((row) => !known.has(row.provider_event_id))];
  return withoutCopyDrift(watermark, events, rows, sharedScans(events, rows, new Map(), earlierCarrier(parcel)),
    { ...providerWatermark(isRecord(parcel.carrier_data) ? parcel.carrier_data : {}, stored), misdated: misdatedCopies(events, rows, checkedAt) });
}

/**
 * How a saved watermark meets the stored rows. One a universal provider's summary set is
 * that provider's newest scan, which the sync stored: past every stored scan, it was set
 * by copies since moved or taken over at their real instant, and comes back to the newest.
 * A UPU summary is on a wall clock, and a parcel loaded without its rows proves nothing.
 */
function providerWatermark(saved: JsonObject, stored: readonly JsonObject[]): { withStored: true; explained?: number[] } {
  const watermark = Date.parse(String(isRecord(saved.routing) ? saved.routing.last_event_at ?? '' : ''));
  return typeof saved.tracking_provider === 'string' && saved.tracking_provider !== 'UPU' && stored.length
    ? { withStored: true, explained: [watermark] } : { withStored: true };
}

/**
 * The parcels `auto_link_package_tracking` may merge into one, an earlier carrier's leg
 * and Swiss Post's delivery leg in one account: those of accounts holding both kinds.
 * It leaves out the stage and number checks, which a check can change.
 */
export function mergeableLegs(parcels: readonly JsonObject[]): Set<string> {
  const legs = new Map<string, { origins: string[]; deliveries: string[] }>();
  for (const parcel of parcels) {
    if (typeof parcel.user_id !== 'string' || parcel.archived_at != null) continue;
    const data = isRecord(parcel.carrier_data) ? parcel.carrier_data : {};
    const owner = legs.get(parcel.user_id) ?? { origins: [], deliveries: [] };
    legs.set(parcel.user_id, owner);
    if (parcel.carrier === 'swiss-post') {
      if (!('original_carrier' in data)) owner.deliveries.push(String(parcel.id));
    } else if (!('original_package_id' in data)
      && (!('original_carrier' in data) || data.original_carrier === parcel.carrier)) {
      owner.origins.push(String(parcel.id));
    }
  }
  return new Set([...legs.values()]
    .filter((owner) => owner.origins.length > 0 && owner.deliveries.length > 0)
    .flatMap((owner) => [...owner.origins, ...owner.deliveries]));
}

export function fairSyncPackages(
  packages: JsonObject[],
  perOwnerLimit = MAX_PACKAGES_PER_OWNER_PER_SYNC,
): JsonObject[] {
  if (!Number.isInteger(perOwnerLimit) || perOwnerLimit < 1) {
    throw new TypeError('Per-owner synchronization limits must be positive');
  }
  const grouped = new Map<string, JsonObject[]>();
  for (const parcel of packages) {
    const owner = String(parcel.user_id ?? `legacy:${parcel.id}`);
    grouped.set(owner, [...(grouped.get(owner) ?? []), parcel]);
  }
  const served = new Map<string, number>();
  const active = [...grouped.keys()];
  const ordered: JsonObject[] = [];
  while (active.length > 0) {
    const owner = active.shift()!;
    const rows = grouped.get(owner)!;
    const count = served.get(owner) ?? 0;
    if (rows.length === 0 || count >= perOwnerLimit) continue;
    ordered.push(rows.shift()!);
    served.set(owner, count + 1);
    if (rows.length > 0 && count + 1 < perOwnerLimit) active.push(owner);
  }
  return ordered;
}
