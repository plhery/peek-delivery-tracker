/**
 * Replays stored scans through the installed scraper release, asking no provider: what the release
 * would write for each stored row of a carrier's or universal provider's scan, from the row's own
 * fields, each answer's rows together: a carrier's own, or one universal provider's. Two things a
 * release changes replay so. A wall clock the app dated on a zone it deduced
 * is dated again (`dateLocalClocks`); a scan whose time came with its offset keeps it, as does one
 * on its carrier's home clock, which the stored row cannot tell from a clock its adapter states
 * itself (ZTO International dates every scan on Shanghai's). A scan's stage is read again from
 * what its source's status map says of its code and wording (`statusMapAnswer`): a stage the map
 * now gives otherwise, a notice the map says keeps the stage it had, which takes the stage its
 * adapter gave the source's scan before it and is stored as the sync stores it (`scanStages`:
 * pending after the delivery round), and, where the map knows neither, the wording rules for a
 * scan its adapter gave no stage. A notice stored as one
 * keeps its stage, as do differences that come only from the order of the scans around a row:
 * the next check of the parcel settles those. Peek's own rows and the stage changes it saw without
 * a scan time are left out. It is a dry run: it lists the rows that would change and the SQL that
 * would change them, guarded on their stored values; applying it is the operator's
 * ([docs/SCRAPER.md](../../docs/SCRAPER.md)).
 */
import { classifyStage, type CarrierResult } from 'universal-parcel-scraper';
import { statusMapAnswer } from 'universal-parcel-scraper/app';
import { carrierHomeZone, dateLocalClocks } from 'universal-parcel-scraper/places';
import { STAGES } from '../generated/apiContract';
import { scanStages } from './disputedDelivery';
import { providerEventId, UNIVERSAL_PROVIDER } from './eventIdentity';
import { isRecord, type JsonObject } from './types';

export interface StoredScan {
  id: string;
  providerEventId: string;
  stage: string;
  occurredAt: string;
  description: string;
  location: string | null;
  raw: JsonObject;
}

export interface StoredParcel {
  id: string;
  destination: string | null;
  /** Neither archived nor delivered or returned: still checked. */
  open: boolean;
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
  /** Where the new stage comes from: the map, a notice keeping the stage before it, or the wording. */
  reason: 'mapped' | 'notice' | 'wording';
  /** The stage its adapter would give the scan, which the sync keeps in the row's raw data; null for none. */
  declared: string | null;
  stageSource: string;
}
export type ReplayedChange = ClockChange | StageChange;

/** The read-only export the replay reads, one parcel a line. */
export const STORED_SCANS_QUERY = `set default_transaction_read_only = on;
select json_build_object('id', p.id, 'destination', p.carrier_data->>'destination_country',
  'open', p.archived_at is null and coalesce(p.current_stage, '') not in ('delivered', 'returned'),
  'events', (select json_agg(json_build_object('id', e.id, 'providerEventId', e.provider_event_id, 'stage', e.stage,
    'occurredAt', e.occurred_at, 'description', e.description, 'location', e.location, 'raw', e.raw_data)) from tracking_events e where e.package_id = p.id))
from packages p where exists (select 1 from tracking_events e where e.package_id = p.id);`;

/** The bases `dateLocalClocks` gives a zone it deduced; a stated offset has none of them. */
const DEDUCED = new Set(['place', 'neighbour', 'destination', 'carrier']);
const KEEPS_STAGE = /keeps? the stage/;
const VALID_STAGES = new Set<string>(STAGES);

const sourceOf = (scan: StoredScan) => scan.providerEventId.slice(0, Math.max(0, scan.providerEventId.indexOf(':')));
const text = (value: unknown) => typeof value === 'string' ? value : null;
const deduced = (scan: StoredScan) => text(scan.raw.local_time) !== null && DEDUCED.has(String(scan.raw.time_zone_basis))
  && !(scan.raw.time_zone_basis === 'carrier' && scan.raw.time_zone === carrierHomeZone(sourceOf(scan)));

/** Every stored row of the parcel the installed release would write otherwise. */
export function replayParcel(parcel: StoredParcel): ReplayedChange[] {
  // The rows of one answer replay together, as the sync read them: a carrier's own, or one universal provider's,
  // which share the source `unknown` with each other and with an adapter's that named no carrier.
  const groups = new Map<string, { source: string; scans: StoredScan[] }>();
  for (const scan of parcel.events) {
    const source = sourceOf(scan);
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
  return changes;
}

function replayedClocks(parcel: StoredParcel, source: string, ordered: StoredScan[]): ClockChange[] {
  if (!ordered.some(deduced)) return [];
  // Newest first, as carriers list them.
  const listed = [...ordered].reverse();
  const result = dateLocalClocks(source, {
    events: listed.map((scan) => ({
      ...(deduced(scan) ? { local_time: scan.raw.local_time } : text(scan.raw.time) ? { time: scan.raw.time } : {}),
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
    if (!deduced(scan) || !time || Date.parse(time) === Date.parse(scan.occurredAt)) return [];
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
    const answer = statusMapAnswer({ carrier: source, providerCode: text(scan.raw.provider_code), description: scan.description });
    const declared = VALID_STAGES.has(String(scan.raw.stage)) ? String(scan.raw.stage) : null;
    const wording = classifyStage(scan.description);
    let given: JsonObject = { ...(declared ? { stage: declared } : {}), stage_source: scan.raw.stage_source ?? null };
    let reason: StageChange['reason'] | null = null;
    if (answer.kind === 'mapped') {
      if (answer.stage !== declared) [given, reason] = [{ stage: answer.stage, stage_source: 'carrier_map' }, 'mapped'];
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
  const stages = scanStages(scans, ordered.map((scan) => scan.occurredAt));
  return ordered.flatMap((scan, index): StageChange[] => {
    const reason = reasons[index];
    const to = stages[index];
    if (!reason || to === scan.stage) return [];
    return [{
      kind: 'stage', parcel: parcel.id, row: scan.id, source, open: parcel.open, from: scan.stage, to,
      code: text(scan.raw.provider_code), reason, declared: text(scans[index].stage),
      stageSource: reason === 'mapped' ? 'carrier_map' : reason === 'wording' ? classifyStage(scan.description).source : 'none',
    }];
  });
}

const literal = (value: string) => `'${value.replaceAll("'", "''")}'`;

/**
 * One transaction that writes the changes as the release would, each guarded on the row's stored
 * values, failing unless every one applies. It ends open: the operator rolls it back for a dry run
 * or commits it.
 */
export function replaySql(changes: readonly ReplayedChange[]): string {
  const statements = changes.map((change) => change.kind === 'clock'
    ? `  update public.tracking_events set occurred_at = ${literal(change.to)}, provider_event_id = ${literal(change.providerEventId)},
    raw_data = raw_data || ${literal(JSON.stringify({ time: change.time, time_zone: change.timeZone, time_zone_basis: change.basis }))}::jsonb
   where id = ${literal(change.row)} and occurred_at = ${literal(change.from)} and provider_event_id = ${literal(change.previousProviderEventId)};
  get diagnostics n = row_count; total := total + n;`
    : `  update public.tracking_events set stage = ${literal(change.to)},
    raw_data = raw_data || ${literal(JSON.stringify(change.declared
      ? { stage: change.declared, stage_source: change.stageSource } : { stage_source: change.stageSource }))}::jsonb
   where id = ${literal(change.row)} and stage = ${literal(change.from)};
  get diagnostics n = row_count; total := total + n;`);
  return `\\set ON_ERROR_STOP on
begin;
do $$
declare n int; total int := 0;
begin
${statements.join('\n')}
  if total <> ${changes.length} then raise exception 'replay: % of ${changes.length} rows changed', total; end if;
end $$;
`;
}
