import 'server-only';

import { statusMapAnswer } from 'universal-parcel-scraper/app';
import manifest from '../../package.json';
import { captureOperationalError, databaseUnavailable, errorType, inBackgroundScope, logOperationalEvent } from './observability';
import type { SupabaseServiceClient } from './supabase';
import { trackingSupportContext } from './trackingSupport';
import type { JsonObject } from './types';
import { leftScheduleUnseen } from './unseenLookup';

/** Long enough for one replay; a server that dies holding it is relieved after this. */
const REPLAY_LEASE_SECONDS = 600;
/** After startup, so a server that keeps restarting does not replay on every start. */
const REPLAY_START_DELAY_MS = 60_000;
/** While another server holds the replay, or after a failure. */
const REPLAY_RETRY_MS = 5 * 60_000;
/** The most recently seen open cases one replay reads. */
export const MAX_REPLAYED_CASES = 500;
/** The most recently seen open status observations one replay reads. */
export const MAX_REPLAYED_OBSERVATIONS = 1000;

/**
 * The tracking code this server runs: the scraper release the app pins and,
 * in an image, the app's commit. Review queue closings record it.
 */
export function deployedVersion(environment: Record<string, string | undefined> = process.env): string {
  const scraper = `universal-parcel-scraper@${manifest.dependencies['universal-parcel-scraper']}`;
  const commit = environment.IMAGE_COMMIT?.trim();
  return commit && /^[0-9a-f]{40}$/i.test(commit) ? `${scraper} app@${commit.slice(0, 12).toLowerCase()}` : scraper;
}

/**
 * Replays an open support case through this server's detection and
 * adapters: a note saying why no gap is left, or null while one is.
 */
export function replaySupportCase(trackingNumber: string, configuredCarrier: string | null, verifiedCarrier: string | null = null): string | null {
  const context = trackingSupportContext(trackingNumber, configuredCarrier ?? 'unknown', verifiedCarrier);
  if ((context.reasons as string[]).length > 0) return null;
  if (context.detection_carrier === 'unknown') {
    return `Replay found no gap: ${String(verifiedCarrier)}, one of the carriers its shape fits, tracks the number directly.`;
  }
  return `Replay found no gap: detection names ${String(context.detection_carrier)} with ${String(context.detection_confidence)} confidence.`;
}

/** Stages after which a parcel is no longer checked. */
const FINISHED_STAGES = new Set(['delivered', 'returned']);

/**
 * Whether an open case only waits for its carrier to confirm the number: the shape
 * fits several carriers, the parcel's carrier among them, and that carrier has not
 * tracked the number directly. Only a later check can close such a case.
 */
export function awaitsConfirmation(supportCase: JsonObject): boolean {
  const configured = typeof supportCase.configured_carrier === 'string' ? supportCase.configured_carrier : null;
  if (!configured || configured === 'unknown' || configured === 'intl-post') return false;
  const verified = typeof supportCase.direct_verified_carrier === 'string' ? supportCase.direct_verified_carrier : null;
  const reasons = trackingSupportContext(String(supportCase.tracking_number ?? ''), configured, verified).reasons as string[];
  return reasons.length === 1 && reasons[0] === 'ambiguous_shape';
}

/**
 * A case that only waits for its carrier's confirmation, on a number whose saved
 * parcels have all finished: no check is left to confirm it, so it needs no change.
 */
export function finishedParcelNote(supportCase: JsonObject, stages: readonly string[] | undefined): string | null {
  if (!stages?.length || !stages.every((stage) => FINISHED_STAGES.has(stage)) || !awaitsConfirmation(supportCase)) return null;
  return `Its parcel was ${stages.includes('delivered') ? 'delivered' : 'returned'} before ${String(supportCase.configured_carrier)}, `
    + 'one of the carriers its shape fits, confirmed the number directly: no check is left to confirm it.';
}

/**
 * A case no check is left to update: its saved parcels are all one-off lookups that left
 * the schedule before any carrier or provider answered for the number, or no parcel with
 * the number is saved any more and the last check of one got no answer for it. A case
 * only the Add sheet's recognition saw stays open: detection could still learn its shape.
 */
export function unseenLookupNote(
  supportCase: JsonObject,
  parcels: readonly JsonObject[] | undefined,
  now: Date,
): string | null {
  if (!parcels?.length) {
    if (supportCase.last_outcome !== 'waiting' || supportCase.direct_verified_carrier) return null;
    return 'No parcel with the number is saved any more, and its last check got no answer for it: '
      + 'no check is left to update the case.';
  }
  if (!parcels.every((parcel) => leftScheduleUnseen(parcel, now))) return null;
  return 'Every parcel with the number is a one-off lookup that left the schedule before any carrier or provider '
    + 'answered for it: no check is left to update the case.';
}

/** How long before a number is saved a case on its start counts as that number being typed. */
const TYPING_WINDOW_MS = 60 * 60_000;

/**
 * Closes the cases detection opened on the start of a number just saved: most likely that
 * number being typed. A case has no owner, so only one detection saw within the hour counts.
 * A failure leaves them to expire with the other cases nobody follows.
 */
export async function settleTypedSupportCases(client: SupabaseServiceClient, trackingNumber: string, now = new Date()): Promise<void> {
  try {
    await client.ignoreTypedTrackingSupportCases(trackingNumber, new Date(now.getTime() - TYPING_WINDOW_MS), deployedVersion(),
      'Only detection saw the number, within the hour before a longer number starting with it was saved: '
      + 'that number being typed.');
  } catch (error) {
    // Without the number: a lookup's never reaches the logs.
    logOperationalEvent('tracking_support_write_failed', { operation: 'typed', error_type: errorType(error) }, 'error');
  }
}

/** Open status observations to close with one resolution and note. */
export interface StatusObservationClosing {
  resolution: 'mapped' | 'ignored';
  note: string;
  keys: string[];
}

/** The carrier the router files a universal provider's scans under (trackingRouting.ts). */
const UNIVERSAL_CARRIER = 'unknown';

/** What the status map of the scraper this server runs says about an observed wording. */
function observedAnswer(observation: JsonObject) {
  return statusMapAnswer({
    carrier: String(observation.carrier ?? ''),
    providerCode: typeof observation.provider_code === 'string' ? observation.provider_code : null,
    description: String(observation.description_normalized ?? ''),
  });
}

/**
 * Whether a universal provider's scan counts as mapped. No carrier map stands
 * behind those scans: the scraper's universal map, the reading the providers
 * give a scan, is theirs. A scan it gives the stage the sync chose is mapped;
 * wording it does not read, or reads as another stage, stays for review.
 * Other carriers' scans are mapped only by their own map.
 */
export function universalMapAgrees(observation: JsonObject): boolean {
  if (observation.carrier !== UNIVERSAL_CARRIER) return false;
  const answer = observedAnswer(observation);
  return answer.kind === 'mapped' && answer.stage === observation.chosen_stage;
}

/**
 * How the scraper this server runs closes an observed wording: as mapped when
 * its carrier's status map gives it a stage, as ignored with the map's note
 * when the map leaves it without one on purpose. Null while no map knows it.
 */
export function statusMapClosing(observation: JsonObject): Omit<StatusObservationClosing, 'keys'> | null {
  const answer = observedAnswer(observation);
  if (answer.kind === 'intentional_gap') return { resolution: 'ignored', note: answer.note.slice(0, 500) };
  if (answer.kind !== 'mapped') return null;
  // A different stage from the sightings' says their stored scans may need a repair.
  const seen = typeof observation.chosen_stage === 'string' ? observation.chosen_stage : '';
  return {
    resolution: 'mapped',
    note: seen && seen !== answer.stage
      ? `The carrier status map gives it ${answer.stage}; it was last seen as ${seen}.`
      : `The carrier status map gives it ${answer.stage}.`,
  };
}

/** The closings for these observations, one per resolution and note; `mapped` false keeps only the gaps. */
export function statusMapClosings(
  observations: readonly JsonObject[],
  options: { mapped?: boolean; version?: string } = {},
): StatusObservationClosing[] {
  const closings = new Map<string, StatusObservationClosing>();
  for (const observation of observations) {
    const key = String(observation.observation_key ?? '');
    const closing = statusMapClosing(observation);
    if (!key || !closing) continue;
    if (closing.resolution === 'mapped') {
      // Wording this version saw unmapped is covered by its map only sometimes.
      if (!options.mapped || (options.version && observation.last_seen_version === options.version)) continue;
    }
    const group = `${closing.resolution}\n${closing.note}`;
    const keys = closings.get(group)?.keys;
    if (keys) keys.push(key);
    else closings.set(group, { ...closing, keys: [key] });
  }
  return [...closings.values()];
}

export interface ReviewReplaySummary extends JsonObject {
  cases_replayed: number;
  cases_fixed: number;
  cases_ignored: number;
  observations_replayed: number;
  observations_mapped: number;
  observations_ignored: number;
}

/**
 * Replays the review queues once for this version, across servers and
 * restarts. 'running' while another server holds the replay, 'done' once one
 * finished it. Each sync closes the wording its scraper maps or leaves
 * unmapped on purpose as it sees it (trackingSync.ts); the replay closes the
 * rest, such as the wording of finished parcels, which no sync sees again.
 */
export async function replayReviewQueues(
  client: SupabaseServiceClient,
  version: string,
  now = new Date(),
): Promise<'running' | 'done' | ReviewReplaySummary> {
  const claim = await client.claimTrackingReviewRun(version, REPLAY_LEASE_SECONDS);
  if (claim !== 'claimed') return claim;
  const cases = await client.listOpenTrackingSupportCases(MAX_REPLAYED_CASES);
  const resolved = cases.flatMap((supportCase) => {
    const configuredCarrier = typeof supportCase.configured_carrier === 'string' ? supportCase.configured_carrier : null;
    const verifiedCarrier = typeof supportCase.direct_verified_carrier === 'string' ? supportCase.direct_verified_carrier : null;
    // Each check of a saved parcel replaces its shape and carrier reasons: one it
    // left without a reason found no gap, whatever its carrier confirmed.
    const note = replaySupportCase(String(supportCase.tracking_number ?? ''), configuredCarrier, verifiedCarrier)
      ?? (Array.isArray(supportCase.reasons) && supportCase.reasons.length === 0
        ? 'Replay found no gap: the latest check of its parcel gave no reason.' : null);
    return note ? [{ id: String(supportCase.id), configured_carrier: configuredCarrier, note }] : [];
  });
  const fixed = resolved.length ? await client.fixReplayedTrackingSupportCases(version, resolved) : 0;
  // Finished parcels are not checked again, so a case waiting for a check would stay open for ever.
  const resolvedIds = new Set(resolved.map(({ id }) => id));
  const waiting = cases.filter((supportCase) => !resolvedIds.has(String(supportCase.id)) && awaitsConfirmation(supportCase));
  const stages = waiting.length ? await client.trackingNumberStages(waiting.map((supportCase) => String(supportCase.tracking_number))) : new Map();
  const finished = waiting.flatMap((supportCase) => {
    const note = finishedParcelNote(supportCase, stages.get(String(supportCase.tracking_number)));
    return note ? [{ id: String(supportCase.id), note }] : [];
  });
  // Nor are lookups nobody answered for once they leave the schedule, or once they are gone.
  const settled = new Set([...resolvedIds, ...finished.map(({ id }) => id)]);
  const unfiled = cases.filter((supportCase) => !settled.has(String(supportCase.id))
    && ['unknown', 'intl-post', null, undefined].includes(supportCase.configured_carrier as string | null | undefined));
  const parcels = unfiled.length ? await client.trackingNumberParcels(unfiled.map((supportCase) => String(supportCase.tracking_number))) : new Map();
  const unseen = unfiled.flatMap((supportCase) => {
    const note = unseenLookupNote(supportCase, parcels.get(String(supportCase.tracking_number)), now);
    return note ? [{ id: String(supportCase.id), note }] : [];
  });
  const closings = [...finished, ...unseen];
  const ignored = closings.length ? await client.ignoreReplayedTrackingSupportCases(version, closings) : 0;
  const observations = await client.listOpenTrackingStatusObservations(MAX_REPLAYED_OBSERVATIONS);
  const closed = { mapped: 0, ignored: 0 };
  for (const closing of statusMapClosings(observations, { mapped: true, version })) {
    closed[closing.resolution] += await client.closeTrackingStatusObservations(version, closing);
  }
  const summary = {
    cases_replayed: cases.length, cases_fixed: fixed, cases_ignored: ignored, observations_replayed: observations.length,
    observations_mapped: closed.mapped, observations_ignored: closed.ignored,
  };
  await client.finishTrackingReviewRun(version, summary);
  return summary;
}

/** Runs the replay a while after startup, then retries until a server has done it for this version. */
export class ReviewQueueReplay {
  #timer: NodeJS.Timeout | null = null;
  #running = false;
  #stopped = false;
  #done = false;
  #reported = false;

  constructor(
    readonly client: SupabaseServiceClient,
    readonly version = deployedVersion(),
  ) {}

  start(): void {
    this.#stopped = false;
    if (!this.#timer && !this.#running && !this.#done) this.schedule(REPLAY_START_DELAY_MS);
  }

  stop(): void {
    this.#stopped = true;
    if (this.#timer) clearTimeout(this.#timer);
    this.#timer = null;
  }

  private schedule(delayMs: number): void {
    this.#timer = setTimeout(() => {
      this.#timer = null;
      void inBackgroundScope('review-queues', () => this.run());
    }, delayMs);
    this.#timer.unref();
  }

  private async run(): Promise<void> {
    this.#running = true;
    try {
      const outcome = await replayReviewQueues(this.client, this.version);
      if (outcome === 'running') return;
      this.#done = true;
      if (outcome !== 'done') logOperationalEvent('review_queues_replayed', { version: this.version, ...outcome });
    } catch (error) {
      logOperationalEvent('review_queue_replay_failed', { version: this.version, error_type: errorType(error) }, 'error');
      // The sync worker reports a database that stays unreachable.
      if (!databaseUnavailable(error) && !this.#reported) {
        this.#reported = true;
        captureOperationalError(error, { component: 'review-queues', operation: 'replay' });
      }
    } finally {
      this.#running = false;
      if (!this.#stopped && !this.#done) this.schedule(REPLAY_RETRY_MS);
    }
  }
}
