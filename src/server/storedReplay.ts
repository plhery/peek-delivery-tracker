/**
 * Replays stored scans through the installed scraper release, asking no provider: what the release
 * would write for each stored row of a carrier's or universal provider's scan, from the row's own
 * fields, each answer's rows together: a carrier's own, or one universal provider's. A carrier's
 * scan that took over a universal provider's copy replays with its carrier's (`writerOf`). Two things a
 * release changes replay so. A wall clock the app dated on a zone it deduced
 * is dated again (`dateLocalClocks`); a scan whose time came with its offset keeps it, as does one
 * on its carrier's home clock, which the stored row cannot tell from a clock its adapter states
 * itself (ZTO International dates every scan on Shanghai's). A scan's stage is read again from
 * what its source's status map says of its code and wording (`statusMapAnswer`): a stage the map
 * now gives otherwise, a notice the map says keeps the stage it had, which takes the stage its
 * adapter gave the source's scan before it and is stored as the sync stores it (`scanStages`:
 * pending after the delivery round), and, where the map knows neither, the wording rules for a
 * scan its adapter gave no stage. A scan its adapter gave the stage of the scan before it, with no
 * source of its own, keeps it whatever the map says of its code. A registration dated over a minute after the answer's
 * first movement is in transit, as the scraper reads each answer (`resolveResult`), unless the sync stored it or its
 * source answered since Peek ran a release that reads it so: its stage is then that answer's reading, which a
 * movement the carrier no longer lists does not change, and the next check would undo the change. A notice stored as one
 * keeps its stage, as do differences that come only from the order of the scans around a row:
 * the next check of the parcel settles those. A wording Postal Ninja relays for a carrier whose map reads it
 * whole is read with that map first (`relayedAnswer`), as its adapter reads it. A universal provider's copy of a scan to
 * the minute, stored beside its copy to the second when its answer listed the scan twice, goes where the release leaves
 * it out (`withoutMinuteCopies`), and the copy to the second takes its place. Peek's own rows and the stage changes it saw without
 * a scan time are left out. It is a dry run: it lists the rows that would change, the parcels
 * that would step back with them, and the SQL that would change both, guarded on their stored
 * values; applying it is the operator's
 * ([docs/SCRAPER.md](../../docs/SCRAPER.md)).
 */
import { classifyStage, resolveResult, type CarrierResult } from 'universal-parcel-scraper';
import * as scraperApp from 'universal-parcel-scraper/app';
import { carrierIdFromName, statusMapAnswer, type StatusMapAnswer } from 'universal-parcel-scraper/app';
import { carrierHomeZone, dateLocalClocks } from 'universal-parcel-scraper/places';
import { STAGES } from '../generated/apiContract';
import { currentScan, sortEventsDesc, tieOrder } from '../lib/stages';
import type { Stage } from '../types';
import { KEEPS_STAGE, scanStages, UNCONFIRMED_DELIVERY_MS } from './disputedDelivery';
import { providerEventId, scanDay, UNIVERSAL_PROVIDER, unnamedRowCarrier } from './eventIdentity';
import { isRecord, type JsonObject } from './types';

export interface StoredScan {
  id: string;
  providerEventId: string;
  stage: string;
  occurredAt: string;
  description: string;
  location: string | null;
  raw: JsonObject;
  /** When the sync stored it. Exports before the field have none. */
  createdAt?: string;
}

export interface StoredParcel {
  id: string;
  destination: string | null;
  /** Neither archived nor delivered or returned, unless the return is held (`return_held`): still checked. */
  open: boolean;
  /** Its saved stage. Exports before the field have none. */
  stage?: string | null;
  /** Put away by its follower, and no longer checked. Exports before the field have none. */
  archived?: boolean;
  /** The carriers whose answers it stores: its own, and the one tracking it since a hand-over. Exports before the field have none. */
  carriers?: string[];
  /** When each source last answered one of its checks, by source; null when none did. Exports before the field have none. */
  answered?: Record<string, string> | null;
  events: StoredScan[];
}

interface Change { parcel: string; row: string; source: string; open: boolean; from: string; to: string }
export interface ClockChange extends Change {
  kind: 'clock';
  /** The source's time, the zone it is read on, how that zone was chosen and the identity that follows. */
  time: string;
  timeZone: string | null;
  basis: string | null;
  providerEventId: string;
  previousProviderEventId: string;
  /** Another stored row of the parcel already holds that identity, or that wording at that instant. */
  clash: boolean;
}
export interface StageChange extends Change {
  kind: 'stage';
  code: string | null;
  /**
   * Where the new stage comes from: the map, a notice keeping the stage before it, the wording, or a registration
   * dated after the answer's first movement, which the scraper reads in transit.
   */
  reason: 'mapped' | 'notice' | 'wording' | 'late';
  /** The stage its adapter would give the scan, which the sync keeps in the row's raw data; null for none. */
  declared: string | null;
  stageSource: string;
}
/**
 * A universal provider's row to the minute the release leaves out as a copy of its row to the second (`row`, at the
 * time `from`, is deleted; `to` is the row that stays).
 */
export interface CopyChange extends Change {
  kind: 'copy';
  provider: string | null;
  previousProviderEventId: string;
  /** The deleted row's place, which the row that stays takes when it has none; null when it keeps its own. */
  location: string | null;
  /** The identity of the row that stays, and the one it takes with the place when its identity hashed its strings. */
  keptProviderEventId: string;
  providerEventId: string;
  /** Another stored row already holds that identity: the row that stays keeps its place and identity. */
  clash: boolean;
}
export type ReplayedChange = ClockChange | StageChange | CopyChange;
/**
 * A parcel whose saved stage the changes move: back, as no row shows it then (`steppedBack`), or with its current scan
 * (`followedScans`). `hold` keeps what the sync keeps with that stage: a return the scans after it only carry back stays
 * on the schedule (`return_held`), and a delivery only a universal provider's row shows is checked daily for a while
 * (`unconfirmed_delivery_until`).
 */
export interface StepBack { parcel: string; from: string; to: string; hold?: 'return' | 'delivery' }

/**
 * What a parcel's stage is derived from: each scan's id, stage and instant. A check rewrites the scans it
 * reads again (a new row version, the same values), which leaves this as it was; a scan added, removed,
 * restaged or redated changes it.
 */
const eventRevision = "md5(string_agg(e.id::text || ':' || coalesce(e.stage, '') || ':' || coalesce(extract(epoch from e.occurred_at)::text, ''), ',' order by e.id))";

/** The read-only export the replay reads, one parcel a line. */

export const STORED_SCANS_QUERY = `set default_transaction_read_only = on;
select json_build_object('id', p.id, 'destination', p.carrier_data->>'destination_country',
  'open', p.archived_at is null and (coalesce(p.current_stage, '') not in ('delivered', 'returned')
    or p.carrier_data->>'return_held' = 'true'), 'stage', p.current_stage,
  'eventRevision', (select ${eventRevision} from tracking_events e where e.package_id = p.id),
  'archived', p.archived_at is not null,
  'carriers', (select coalesce(json_agg(distinct c), '[]') from unnest(array[p.carrier, p.carrier_data->>'original_carrier', p.carrier_data->>'active_tracking_carrier']) c
    where c is not null and c <> 'unknown'),
  'answered', (select json_object_agg(a.source_carrier, a.at) from (select source_carrier, max(completed_at) at from tracking_sync_attempts
    where package_id = p.id and outcome in ('updated', 'unchanged') and source_carrier is not null group by source_carrier) a),
  'events', (select json_agg(json_build_object('id', e.id, 'providerEventId', e.provider_event_id, 'stage', e.stage,
    'occurredAt', e.occurred_at, 'description', e.description, 'location', e.location, 'raw', e.raw_data, 'createdAt', e.created_at))
    from tracking_events e where e.package_id = p.id))
from packages p where exists (select 1 from tracking_events e where e.package_id = p.id);`;

/** The bases `dateLocalClocks` gives a zone it deduced; a stated offset has none of them. */
const DEDUCED = new Set(['place', 'neighbour', 'destination', 'carrier']);
const VALID_STAGES = new Set<string>(STAGES);
/** Stages past the label, as the sync's `rereadStage` counts progress. */
const PROGRESS_STAGES = new Set<string>(STAGES.filter((stage) => stage !== 'pending' && stage !== 'registered'));
const HELD_STAGES = new Set(['delivered', 'returned', 'exception']);
/** The stages of a parcel on its way, which the sync keeps over an acceptance (`stageToSave`, trackingSync.ts). */
const ON_THE_WAY = new Set(['in_transit', 'customs', 'out_for_delivery', 'failed_attempt', 'ready_for_pickup']);

const sourceOf = (scan: StoredScan) => scan.providerEventId.slice(0, Math.max(0, scan.providerEventId.indexOf(':')));
/** The universal providers whose adapter stages a scan with the map of the carrier that reported it. */
const RELAYING_PROVIDERS = new Set(['Postal Ninja']);
/**
 * A release before 0.1005.0, which `--from` replays with, reads no relayed wording: looked up, not imported, so
 * the module still loads with it.
 */
const relayedWordingStage = (scraperApp as Partial<typeof scraperApp>).relayedWordingStage;
/** A release before 0.1022.0 keeps a provider's copies to the minute: looked up the same way. */
const withoutMinuteCopies = (scraperApp as Partial<typeof scraperApp>).withoutMinuteCopies;
const UNIVERSAL = 'unknown';
/**
 * By when Peek ran a release that reads late registrations (0.740.0, the first it adopted that does). The sync writes
 * every row an answer lists with that answer's reading, so a row stored since, or whose source answered since, holds the
 * scraper's reading of the answer it last came in, which may no longer list the movement the stored rows date it after.
 * A release that reads them otherwise moves it.
 */
const LATE_REGISTRATIONS_READ_SINCE = Date.parse('2026-10-25T22:30:00Z');
const MINUTE_MS = 60_000;
const words = (value: string) => value.trim().replace(/\s+/g, ' ').toLowerCase();
const text = (value: unknown) => typeof value === 'string' ? value : null;
const deduced = (scan: StoredScan, source: string) => text(scan.raw.local_time) !== null && DEDUCED.has(String(scan.raw.time_zone_basis))
  && !(scan.raw.time_zone_basis === 'carrier' && scan.raw.time_zone === carrierHomeZone(source));

/**
 * The source whose scan a row shows. A carrier's scan that takes over a universal provider's copy keeps the copy's
 * identity and drops the provider's name (eventIdentity.ts): the row is the carrier's, under `unknown`, and its
 * identity no longer hashes its strings on any of the times the sync keys a scan by. It goes with the parcel's
 * carrier, or with the one of a handed-over parcel's two whose status map knows its code and wording; with neither,
 * it stays where its identity puts it.
 */
function writerOf(parcel: StoredParcel, scan: StoredScan): string {
  const source = sourceOf(scan);
  if (source !== 'unknown' || text(scan.raw[UNIVERSAL_PROVIDER]) !== null) return source;
  const times = [scan.raw.time, scan.raw.local_time, scanDay(scan.raw)];
  if (times.some((time) => providerEventId(source, time, scan.location ?? '', scan.description) === scan.providerEventId)) return source;
  return unnamedRowCarrier(parcel.carriers ?? [], text(scan.raw.provider_code), scan.description) ?? source;
}

/**
 * What the map of the carrier that reported a universal provider's scan says of its wording, as the provider's
 * adapter reads it: Postal Ninja names the service behind each scan (`reporting_carrier`), and a carrier whose map
 * reads such a wording whole stages it (`relayedWordingStage`). Null when no such map stages it, and for the other
 * providers, which name a scan's carrier without staging it so.
 */
function relayedAnswer(scan: StoredScan): StatusMapAnswer | null {
  const service = text(scan.raw.reporting_carrier);
  const provider = text(scan.raw[UNIVERSAL_PROVIDER]);
  if (!service || !provider || !RELAYING_PROVIDERS.has(provider)) return null;
  const carrier = carrierIdFromName(service);
  const stage = carrier && relayedWordingStage ? relayedWordingStage(carrier, scan.description) : undefined;
  return stage ? { kind: 'mapped', stage } : null;
}

/** Every stored row of the parcel the installed release would write otherwise. */
export function replayParcel(parcel: StoredParcel): ReplayedChange[] {
  // The rows of one answer replay together, as the sync read them: a carrier's own, or one universal provider's,
  // which share the source `unknown` with each other and with an adapter's that named no carrier.
  const groups = new Map<string, { source: string; scans: StoredScan[] }>();
  for (const scan of parcel.events) {
    const source = writerOf(parcel, scan);
    if (!source || source === 'app' || scan.raw.observed_without_provider_timestamp === true) continue;
    const key = JSON.stringify([source, text(scan.raw[UNIVERSAL_PROVIDER])]);
    const group = groups.get(key) ?? { source, scans: [] };
    group.scans.push(scan);
    groups.set(key, group);
  }
  const changes: ReplayedChange[] = [];
  for (const { source, scans } of groups.values()) {
    // Oldest first, as notices take the stage of the scan before them.
    const ordered = [...scans].sort((a, b) => Date.parse(a.occurredAt) - Date.parse(b.occurredAt) || a.id.localeCompare(b.id));
    changes.push(...replayedClocks(parcel, source, ordered), ...replayedStages(parcel, source, ordered));
  }
  // A row the release leaves out as a copy goes; nothing else is written to it.
  const copies = replayedCopies(parcel);
  const gone = new Set(copies.map((copy) => copy.row));
  return [...changes.filter((change) => !gone.has(change.row)), ...copies];
}

/**
 * The rows of each universal provider, read as one answer by `withoutMinuteCopies`: a row it leaves out is a copy to
 * the minute of the one row to the second in that minute with the same wording and stage, and goes; that row takes the
 * copy's place when it has none, and the identity the sync gives it with the place when its own hashed its strings.
 * Rows that name no provider are read together.
 */
function replayedCopies(parcel: StoredParcel): CopyChange[] {
  if (!withoutMinuteCopies) return [];
  const groups = new Map<string | null, StoredScan[]>();
  for (const scan of parcel.events) {
    if (sourceOf(scan) !== UNIVERSAL || scan.raw.observed_without_provider_timestamp === true) continue;
    const provider = text(scan.raw[UNIVERSAL_PROVIDER]);
    groups.set(provider, [...groups.get(provider) ?? [], scan]);
  }
  return [...groups].flatMap(([provider, scans]): CopyChange[] => {
    if (scans.length < 2) return [];
    // Newest first, as providers list them; each event names its row.
    const listed = [...scans].sort((a, b) => Date.parse(b.occurredAt) - Date.parse(a.occurredAt) || a.id.localeCompare(b.id));
    const result = withoutMinuteCopies({
      events: listed.map((scan) => ({
        ...(text(scan.raw.time) ? { time: scan.raw.time } : {}),
        ...(text(scan.raw.local_time) ? { local_time: scan.raw.local_time } : {}),
        description: scan.description, location: scan.location ?? '', stage: scan.stage,
        ...(text(scan.raw.provider_code) ? { provider_code: scan.raw.provider_code } : {}),
        row: scan.id,
      })),
    } as unknown as CarrierResult);
    const kept = new Map(((result.events ?? []) as unknown as JsonObject[]).map((event) => [String(event.row), event]));
    if (kept.size === listed.length) return [];
    return listed.flatMap((copy): CopyChange[] => {
      if (kept.has(copy.id)) return [];
      // The row it copies: the one kept in its minute, to the second, with its wording and stage.
      const at = Date.parse(copy.occurredAt);
      const [stays, ...others] = listed.filter((scan) => kept.has(scan.id) && Date.parse(scan.occurredAt) % MINUTE_MS !== 0
        && Date.parse(scan.occurredAt) - (Date.parse(scan.occurredAt) % MINUTE_MS) === at
        && words(scan.description) === words(copy.description) && scan.stage === copy.stage);
      if (!stays || others.length) return [];
      const place = stays.location ? null : text(kept.get(stays.id)?.location) || null;
      const hashed = providerEventId(UNIVERSAL, stays.raw.time, stays.location ?? '', stays.description) === stays.providerEventId;
      const identity = place && hashed ? providerEventId(UNIVERSAL, stays.raw.time, place, stays.description) : stays.providerEventId;
      const clash = identity !== stays.providerEventId
        && parcel.events.some((other) => other.id !== stays.id && other.id !== copy.id && other.providerEventId === identity);
      return [{
        kind: 'copy', parcel: parcel.id, row: copy.id, source: UNIVERSAL, open: parcel.open,
        from: new Date(at).toISOString(), to: stays.id, provider, previousProviderEventId: copy.providerEventId,
        location: clash ? null : place, keptProviderEventId: stays.providerEventId,
        providerEventId: clash ? stays.providerEventId : identity, clash,
      }];
    });
  });
}

function replayedClocks(parcel: StoredParcel, source: string, ordered: StoredScan[]): ClockChange[] {
  if (!ordered.some((scan) => deduced(scan, source))) return [];
  // Newest first, as carriers list them.
  const listed = [...ordered].reverse();
  const result = dateLocalClocks(source, {
    events: listed.map((scan) => ({
      ...(deduced(scan, source) ? { local_time: scan.raw.local_time } : text(scan.raw.time) ? { time: scan.raw.time } : {}),
      ...(scan.location ? { location: scan.location } : {}),
      ...(isRecord(scan.raw.point) ? { point: scan.raw.point } : {}),
      stage: scan.stage,
      description: scan.description,
    })),
    ...(parcel.destination ? { destination_country: parcel.destination } : {}),
  } as unknown as CarrierResult);
  const dated = (result.events ?? []) as unknown as JsonObject[];
  return listed.flatMap((scan, index): ClockChange[] => {
    const time = text(dated[index]?.time);
    if (!deduced(scan, source) || !time || Date.parse(time) === Date.parse(scan.occurredAt)) return [];
    const to = new Date(Date.parse(time)).toISOString();
    const identity = providerEventId(source, time, scan.location ?? '', scan.description);
    const clash = parcel.events.some((other) => other.id !== scan.id && (other.providerEventId === identity
      || (other.description === scan.description && Date.parse(other.occurredAt) === Date.parse(to))));
    return [{
      kind: 'clock', parcel: parcel.id, row: scan.id, source, open: parcel.open, from: new Date(Date.parse(scan.occurredAt)).toISOString(), to,
      time, timeZone: text(dated[index]?.time_zone), basis: text(dated[index]?.time_zone_basis),
      providerEventId: identity, previousProviderEventId: scan.providerEventId, clash,
    }];
  });
}

function replayedStages(parcel: StoredParcel, source: string, ordered: StoredScan[]): StageChange[] {
  // What the adapter would give each scan, as the sync reads the answer: its stage and how it came by it.
  const scans: JsonObject[] = [];
  const reasons: (StageChange['reason'] | null)[] = [];
  // The stage the adapter gives the scan before, the one a notice keeps.
  let previous: string | null = null;
  for (const scan of ordered) {
    const answer = relayedAnswer(scan)
      ?? statusMapAnswer({ carrier: source, providerCode: text(scan.raw.provider_code), description: scan.description });
    const declared = VALID_STAGES.has(String(scan.raw.stage)) ? String(scan.raw.stage) : null;
    const wording = classifyStage(scan.description);
    let given: JsonObject = { ...(declared ? { stage: declared } : {}), stage_source: scan.raw.stage_source ?? null };
    let reason: StageChange['reason'] | null = null;
    if (answer.kind === 'mapped') {
      // A scan its adapter staged from the scans around it keeps the stage of the scan before it with no source of
      // its own, as Chronopost's RB keeps a delivery round's or a pickup point's: the map's stage is not its. Pending
      // is no stage kept: a scan an earlier release could not read, which the map now reads.
      const kept = scan.raw.stage_source === 'none' && declared !== null && declared !== 'pending' && declared === previous;
      if (answer.stage !== declared && !kept) [given, reason] = [{ stage: answer.stage, stage_source: 'carrier_map' }, 'mapped'];
    } else if (answer.kind === 'intentional_gap' && KEEPS_STAGE.test(answer.note)) {
      // A notice stored as one kept the stage its adapter saw before it, which the stored rows may not show.
      if (!(declared && scan.raw.stage_source === 'none')) [given, reason] = [{ stage: previous ?? 'registered', stage_source: 'none' }, 'notice'];
    } else if (answer.kind === 'unknown' && !declared && wording.source !== 'none') {
      reason = 'wording';
    }
    previous = text(given.stage) ?? (reason === 'wording' ? wording.stage : scan.stage);
    scans.push({ ...given, description: scan.description });
    reasons.push(reason);
  }
  for (const index of lateRegistrations(scans, ordered)) {
    if (readSinceLateRegistrations(parcel, source, ordered[index])) continue;
    scans[index] = { ...scans[index], stage: 'in_transit', stage_source: 'carrier_map' };
    reasons[index] = 'late';
  }
  const stages = scanStages(scans, ordered.map((scan) => scan.occurredAt));
  return ordered.flatMap((scan, index): StageChange[] => {
    const reason = reasons[index];
    const to = stages[index];
    if (!reason || to === scan.stage) return [];
    return [{
      kind: 'stage', parcel: parcel.id, row: scan.id, source, open: parcel.open, from: scan.stage, to,
      code: text(scan.raw.provider_code), reason, declared: text(scans[index].stage),
      stageSource: reason === 'mapped' || reason === 'late' ? 'carrier_map'
        : reason === 'wording' ? classifyStage(scan.description).source : 'none',
    }];
  });
}

/**
 * The scans of an answer the scraper reads as registrations written once the parcel moved (`resolveResult`, which
 * every answer goes through): a label or order row dated over a minute after the answer's first movement is in
 * transit, as a partner's label is.
 */
function lateRegistrations(scans: readonly JsonObject[], ordered: readonly StoredScan[]): number[] {
  const events: JsonObject[] = scans.map((scan, index) => ({ ...scan, time: ordered[index].occurredAt }));
  const read = (resolveResult({ status: 'unknown', events } as unknown as CarrierResult).events ?? []) as unknown as JsonObject[];
  return events.flatMap((event, index) => (text(event.stage) ?? classifyStage(String(event.description)).stage) === 'registered'
    && read[index]?.stage === 'in_transit' ? [index] : []);
}

/** The sync stored the row, or its source answered, since Peek ran a release that reads late registrations. */
function readSinceLateRegistrations(parcel: StoredParcel, source: string, scan: StoredScan): boolean {
  return [scan.createdAt, parcel.answered?.[source]].some((at) => typeof at === 'string' && Date.parse(at) >= LATE_REGISTRATIONS_READ_SINCE);
}

/**
 * The parcels the changes step back. A parcel past the label whose rows showed progress keeps its stage while a
 * row still shows it; once the changes read every such row as the label or earlier, the carrier's next answer shows
 * none, and the sync keeps the saved stage with a `stages_reread` signal. The parcel steps back with its rows: to the
 * label when a row shows one, else to pending, as `rereadStage` reads a universal provider's rows. Returned and
 * exception parcels keep their stage. So does a delivered one, unless the changes read every row that showed the
 * delivery in another stage, one of them past pending: the sync keeps a delivery no row shows, so a parcel still checked takes
 * the stage the newest of those rows reads then (an undelivered attempt an earlier release read as delivered), and
 * its next check takes the carrier's answer from there.
 */
export function steppedBack(parcels: readonly StoredParcel[], changes: readonly ReplayedChange[]): StepBack[] {
  const restaged = new Map(changes.flatMap((change) => change.kind === 'stage' ? [[change.row, change.to] as const] : []));
  return parcels.flatMap((parcel): StepBack[] => {
    const from = parcel.stage ?? '';
    if (!parcel.events.some((scan) => restaged.has(scan.id))) return [];
    const before = parcel.events.map((scan) => scan.stage);
    const after = parcel.events.map((scan) => restaged.get(scan.id) ?? scan.stage);
    if (from === 'delivered') {
      if (parcel.archived !== false || after.includes('delivered')) return [];
      // The rows that showed the delivery, newest first, as the changes read them.
      const [to] = parcel.events.filter((scan) => scan.stage === 'delivered')
        .sort((a, b) => Date.parse(b.occurredAt) - Date.parse(a.occurredAt))
        .map((scan) => restaged.get(scan.id)!).filter((stage) => stage !== 'pending');
      return to ? [{ parcel: parcel.id, from, to }] : [];
    }
    if (!parcel.open || !PROGRESS_STAGES.has(from) || HELD_STAGES.has(from)) return [];
    if (!before.some((stage) => PROGRESS_STAGES.has(stage)) || after.some((stage) => PROGRESS_STAGES.has(stage))) return [];
    return [{ parcel: parcel.id, from, to: after.includes('registered') ? 'registered' : 'pending' }];
  });
}

/**
 * A parcel whose saved stage is its current scan's, as the apps read it (`currentScan`), when the changes to its rows
 * change that stage: the parcel moves with it, as the carrier's next answer would move it (a DPD Poland return an
 * earlier release read as a problem, or a newest scan now read as a notice, so the one before it is current), and one
 * no longer checked would otherwise keep the old stage over its newest scan. A delivered or returned parcel keeps its
 * stage (a delivery steps back above), as do one stepped back, one whose saved stage no current scan gave, one the
 * changes would leave at its label, which `steppedBack` reads, and one on its way that would take an acceptance, which
 * the sync keeps the moving stage over (`stageToSave`, trackingSync.ts).
 */
export function followedScans(parcels: readonly StoredParcel[], changes: readonly ReplayedChange[], stepBacks: readonly StepBack[] = []): StepBack[] {
  const restaged = new Map(changes.flatMap((change) => change.kind === 'stage' ? [[change.row, change.to] as const] : []));
  const deleted = new Set(changes.flatMap((change) => change.kind === 'copy' ? [change.row] : []));
  const redated = new Map(changes.flatMap((change) => change.kind === 'clock' ? [[change.row, change.to] as const] : []));
  const stepped = new Set(stepBacks.map((step) => step.parcel));
  // Before the changes, each scan at its stored time; after them, a re-dated one at its new time.
  const ordered = (scans: readonly StoredScan[], stage: (scan: StoredScan) => string, changed = false) => sortEventsDesc(scans
    .map((scan) => ({ id: scan.id, stage: stage(scan) as Stage, occurredAt: changed ? redated.get(scan.id) ?? scan.occurredAt : scan.occurredAt,
      tieOrder: tieOrder(scan.raw.tie_order) }))
    .filter((scan) => scan.stage !== 'pending'));
  const current = (scans: readonly StoredScan[], stage: (scan: StoredScan) => string, changed = false) => currentScan(ordered(scans, stage, changed));
  return parcels.flatMap((parcel): StepBack[] => {
    const from = parcel.stage ?? '';
    if (stepped.has(parcel.id) || ['delivered', 'returned'].includes(from) || !parcel.events.some((scan) => restaged.has(scan.id) || redated.has(scan.id))) return [];
    const before = current(parcel.events, (scan) => scan.stage);
    const kept = parcel.events.filter((scan) => !deleted.has(scan.id));
    const read = (scan: StoredScan) => restaged.get(scan.id) ?? scan.stage;
    const after = current(kept, read, true);
    if (before?.stage !== from || !after || after.stage === from || !PROGRESS_STAGES.has(after.stage)) return [];
    if (after.stage === 'accepted' && ON_THE_WAY.has(from)) return [];
    // A return that newer scans carry back, as the sync holds it; a delivery no row of the carrier's own shows.
    const providers = (scan: StoredScan) => typeof scan.raw[UNIVERSAL_PROVIDER] === 'string';
    const hold = after.stage === 'returned' && ordered(kept, read, true)[0]?.id !== after.id ? 'return' as const
      : after.stage === 'delivered' && kept.every((scan) => read(scan) !== 'delivered' || providers(scan)) ? 'delivery' as const
        : undefined;
    return [{ parcel: parcel.id, from, to: after.stage, ...(hold ? { hold } : {}) }];
  });
}

const literal = (value: string) => `'${value.replaceAll("'", "''")}'`;

const idList = (ids: readonly string[]) => `array[${[...new Set(ids)].sort().map(literal).join(', ')}]::uuid[]`;

/**
 * The rows the transaction changes, as they stand inside it before any change, one JSON line each
 * (`{"table": ..., "row": ...}`): every row it re-dates, re-stages or deletes, every row that takes a
 * deleted copy's place, and each parcel it moves (its id and saved stage). psql, quiet and
 * printing tuples only, prints them alone, so its output is the backup.
 */
function backupSql(changes: readonly ReplayedChange[], placed: readonly CopyChange[], stepBacks: readonly StepBack[]): string {
  const rows = [...changes.map((change) => change.row), ...placed.map((copy) => copy.to)];
  return [
    ...rows.length ? [`select json_build_object('table', 'tracking_events', 'row', to_jsonb(e)) from public.tracking_events e
 where e.id = any(${idList(rows)}) order by e.id;`] : [],
    ...stepBacks.length ? [`select json_build_object('table', 'packages', 'row', jsonb_build_object('id', p.id, 'current_stage', p.current_stage))
  from public.packages p where p.id = any(${idList(stepBacks.map((step) => step.parcel))}) order by p.id;`] : [],
    // The holds a move sets, as they stood.
    ...stepBacks.some((step) => step.hold) ? [`select json_build_object('table', 'packages', 'row', jsonb_build_object('id', p.id,
    'return_held', p.carrier_data->'return_held', 'unconfirmed_delivery_until', p.carrier_data->'unconfirmed_delivery_until'))
  from public.packages p where p.id = any(${idList(stepBacks.filter((step) => step.hold).map((step) => step.parcel))}) order by p.id;`] : [],
  ].join('\n');
}

/**
 * One transaction that writes the changes as the release would, each guarded on the row's stored
 * values, and moves the parcels, each guarded on its saved stage, failing unless every one
 * applies. A copy is deleted after the other changes, and the row that stays then takes its place.
 * Before any change it prints the rows it changes (`backupSql`), so its run keeps its own backup.
 * It ends open: the operator rolls it back for a dry run or commits it.
 */
export function replaySql(changes: readonly ReplayedChange[], stepBacks: readonly StepBack[] = [],
  snapshots: readonly { id: string; eventRevision?: string }[] = []): string {
  const revisions = new Map(snapshots.map((snapshot) => [snapshot.id, snapshot.eventRevision]));
  const guards = stepBacks.map(({ parcel }) => {
    const revision = revisions.get(parcel);
    if (!revision) throw new Error('Export a fresh event snapshot before moving parcel stages');
    // The parent lock excludes inserts through the foreign key. Row locks also exclude
    // edits and deletions until the transaction commits.
    return `do $$ begin
  perform 1 from public.packages where id = ${literal(parcel)} for update;
  perform 1 from public.tracking_events where package_id = ${literal(parcel)} order by id for update;
  if (select ${eventRevision} from public.tracking_events e where e.package_id = ${literal(parcel)}) is distinct from ${literal(revision)}
    then raise exception 'Event snapshot changed since export'; end if;
end $$;`;
  });
  const copies = changes.filter((change): change is CopyChange => change.kind === 'copy');
  const placed = copies.filter((copy) => copy.location !== null);
  const statements = changes.filter((change) => change.kind !== 'copy').map((change) => change.kind === 'clock'
    ? `  update public.tracking_events set occurred_at = ${literal(change.to)}, provider_event_id = ${literal(change.providerEventId)},
    raw_data = raw_data || ${literal(JSON.stringify({ time: change.time, time_zone: change.timeZone, time_zone_basis: change.basis }))}::jsonb
   where id = ${literal(change.row)} and occurred_at = ${literal(change.from)} and provider_event_id = ${literal(change.previousProviderEventId)};
  get diagnostics n = row_count; total := total + n;`
    // A row restaged as a notice is marked one, as the sync marks it (keptNotices); any other restaging unmarks it.
    : `  update public.tracking_events set stage = ${literal(change.to)},
    raw_data = (raw_data - 'kept_stage') || ${literal(JSON.stringify({ ...change.declared ? { stage: change.declared } : {},
      stage_source: change.stageSource, ...change.reason === 'notice' ? { kept_stage: true } : {} }))}::jsonb
   where id = ${literal(change.row)} and stage = ${literal(change.from)};
  get diagnostics n = row_count; total := total + n;`);
  const deletions = copies.map((copy) => `  delete from public.tracking_events
   where id = ${literal(copy.row)} and package_id = ${literal(copy.parcel)} and occurred_at = ${literal(copy.from)}
     and provider_event_id = ${literal(copy.previousProviderEventId)};
  get diagnostics n = row_count; total := total + n;`);
  const places = placed.map((copy) => `  update public.tracking_events set location = ${literal(copy.location!)},
    provider_event_id = ${literal(copy.providerEventId)}, raw_data = raw_data || ${literal(JSON.stringify({ location: copy.location }))}::jsonb
   where id = ${literal(copy.to)} and coalesce(location, '') = '' and provider_event_id = ${literal(copy.keptProviderEventId)};
  get diagnostics n = row_count; filled := filled + n;`);
  const held = (step: StepBack) => step.hold === 'return'
    ? `, carrier_data = coalesce(carrier_data, '{}'::jsonb) || '{"return_held": true}'::jsonb`
    : step.hold === 'delivery'
      ? `, carrier_data = coalesce(carrier_data, '{}'::jsonb) || jsonb_build_object('unconfirmed_delivery_until',
    to_char((now() + interval '${UNCONFIRMED_DELIVERY_MS / 1000} seconds') at time zone 'UTC', 'YYYY-MM-DD"T"HH24:MI:SS.MS"Z"'))`
      : '';
  const parcels = stepBacks.map((step) => `  update public.packages set current_stage = ${literal(step.to)}${held(step)}
   where id = ${literal(step.parcel)} and current_stage = ${literal(step.from)};
  get diagnostics n = row_count; stepped := stepped + n;`);
  return `\\set ON_ERROR_STOP on
\\set QUIET on
\\pset tuples_only on
\\pset format unaligned
begin;
set local peek.hand_fix = 'stored-scan replay';
${guards.join('\n')}
${backupSql(changes, placed, stepBacks)}
do $$
declare n int; total int := 0; filled int := 0; stepped int := 0;
begin
${[...statements, ...deletions, ...places, ...parcels].join('\n')}
  if total <> ${changes.length} then raise exception 'replay: % of ${changes.length} rows changed', total; end if;
  if filled <> ${placed.length} then raise exception 'replay: % of ${placed.length} rows given a copy''s place', filled; end if;
  if stepped <> ${stepBacks.length} then raise exception 'replay: % of ${stepBacks.length} parcels moved', stepped; end if;
end $$;
`;
}
