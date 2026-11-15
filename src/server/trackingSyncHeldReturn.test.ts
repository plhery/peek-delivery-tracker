// A return held over a carrier's summary of a problem (`return_held`) while a row an earlier check dated at the check
// in that problem's stage sits after the return: the server holds the parcel as returned from the answer's scans alone,
// and the apps read every stored row (currentEvent) with the parcel's hold.
import { describe, expect, it, vi } from 'vitest';
import type { CarrierResult } from 'universal-parcel-scraper';
import { currentEvent } from '../lib/stages';
import type { Stage, TrackingEvent } from '../types';
import { STORED_EVENT_IDENTITIES, type SupabaseServiceClient } from './supabase';
import { TrackingSyncService } from './trackingSync';
import type { JsonObject } from './types';

function store() {
  const rows = new Map<string, JsonObject>();
  let created = 0;
  const client = {
    listActivePackages: vi.fn().mockResolvedValue([]),
    getPackage: vi.fn().mockResolvedValue(null),
    syncJobQueuedAhead: vi.fn().mockResolvedValue(null),
    listFollowedOneOffPackages: vi.fn().mockResolvedValue([]),
    listUnfiledOneOffPackages: vi.fn().mockResolvedValue([]),
    listSyncCandidates: vi.fn().mockResolvedValue([]),
    listUnwatchedPackageIds: vi.fn().mockResolvedValue([]),
    sharedAlertPackageIds: vi.fn().mockResolvedValue(new Set()),
    autoLinkPackages: vi.fn().mockResolvedValue(0),
    reopenScanAnnouncements: vi.fn(async (_id: string, ids: string[]) => ids.length),
    updatePackage: vi.fn().mockResolvedValue(undefined),
    insertEvents: vi.fn().mockResolvedValue(undefined),
    deleteEventsByDescriptions: vi.fn().mockResolvedValue(undefined),
    startSyncAttempt: vi.fn().mockResolvedValue(undefined),
    completeSyncAttempt: vi.fn().mockResolvedValue(true),
    recordTrackingHealth: vi.fn().mockResolvedValue([]),
    trackingProviderIncidentOpen: vi.fn().mockResolvedValue(false),
    ackTrackingHealth: vi.fn().mockResolvedValue(undefined),
    previousThinAnswer: vi.fn().mockResolvedValue(null),
    recordTrackingStatusObservations: vi.fn().mockResolvedValue(undefined),
    closeTrackingStatusObservations: vi.fn().mockResolvedValue(1),
    acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: '' }),
    finishTrackingProvider: vi.fn().mockResolvedValue(undefined),
    applyTrackingSync: vi.fn(async (_parcel: JsonObject, _values: JsonObject, events: JsonObject[] = [], descriptions: string[] = [], absorbed: string[] = []) => {
      const ids = events.map((event) => String(event.provider_event_id));
      for (const event of events) {
        const id = String(event.provider_event_id);
        rows.set(id, { ...event, id: rows.get(id)?.id ?? `row-${++created}` });
      }
      for (const [id, row] of rows) if (descriptions.includes(String(row.description))) rows.delete(id);
      for (const id of absorbed) if (!ids.includes(id)) rows.delete(id);
      return true;
    }),
  };
  const identities = () => [...rows.values()].map((row) => ({
    provider_event_id: row.provider_event_id,
    occurred_at: new Date(String(row.occurred_at)).toISOString().replace('.000Z', '+00:00'),
    stage: row.stage, description: row.description, location: row.location,
    time: (row.raw_data as JsonObject | undefined)?.time,
    observed_without_provider_timestamp: (row.raw_data as JsonObject | undefined)?.observed_without_provider_timestamp,
  }));
  return { client, rows, identities };
}

type Scan = { time: string; description: string; stage: string };
// Invented wording and clocks, as in trackingSync.test.ts's "a return on its way back".
const trip: Scan[] = [
  { time: '2026-11-04T15:00:00+01:00', description: 'Destinatario assente', stage: 'failed_attempt' },
  { time: '2026-11-04T08:00:00+01:00', description: 'In consegna', stage: 'out_for_delivery' },
  { time: '2026-11-03T20:00:00+01:00', description: 'In transito', stage: 'in_transit' }];
const returnScan: Scan = { time: '2026-11-05T12:00:00+01:00', description: 'In restituzione al mittente', stage: 'returned' };
const back: Scan = { time: '2026-11-07T15:30:00+01:00', description: 'Centro di origine', stage: 'in_transit' };
// The carrier sums the parcel up as a problem.
const problem = (...events: Scan[]): CarrierResult => ({
  status: 'exception', current_stage: 'exception', last_status_text: 'Destinatario sconosciuto', last_update: events[0]!.time, events });

function checks() {
  const { client, rows, identities } = store();
  const adapter = { fetch: vi.fn(), fetchUniversal: vi.fn().mockRejectedValue(new Error('not asked')) };
  let now = new Date();
  const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => now);
  let parcel: JsonObject = { id: 'going-back', carrier: 'poste-italiane', tracking_number: 'RA314159265IT',
    current_stage: 'pending', created_at: '2026-11-03T09:00:00Z' };
  return async (at: string, answer: CarrierResult) => {
    now = new Date(at);
    adapter.fetch.mockResolvedValue(answer);
    await service.syncPackage({ ...parcel, [STORED_EVENT_IDENTITIES]: identities() });
    parcel = { ...parcel, ...(client.applyTrackingSync.mock.calls.at(-1)![1] as JsonObject) };
    // What the apps show: the current event of every stored row, with the hold the parcel's carrier data carries.
    const events: TrackingEvent[] = [...rows.values()].map((row) => ({ id: String(row.id), parcelId: 'going-back',
      stage: row.stage as Stage, description: String(row.description), occurredAt: new Date(String(row.occurred_at)).toISOString() }));
    const held = (parcel.carrier_data as JsonObject | undefined)?.return_held === true;
    return { parcel, shown: currentEvent(events, held)?.stage, unheld: currentEvent(events)?.stage };
  };
}

describe('a return held over a summary of a problem, beside a problem row an earlier check dated', () => {
  it('reads as returned in the apps as it is stored, when the problem was first seen after the return scan', async () => {
    const sync = checks();
    // 14:00 on the carrier's clock: the summary already says the problem; the return scan of 12:00 is not listed yet.
    const first = await sync('2026-11-05T13:00:00Z', problem(...trip));
    expect(first.parcel).toMatchObject({ current_stage: 'exception' });
    // Two days on, the return scan of 12:00 and a scan carrying the parcel back are listed, the summary unchanged.
    const held = await sync('2026-11-07T18:00:00Z', problem(back, returnScan, ...trip));
    expect(held.parcel).toMatchObject({ current_stage: 'returned', carrier_data: { return_held: true } });
    expect(held.shown).toBe('returned');
    // The rows alone end on the trip back after the problem row.
    expect(held.unheld).toBe('in_transit');
  });

  it('reads as returned in the apps when the return is the newest scan', async () => {
    const sync = checks();
    await sync('2026-11-05T13:00:00Z', problem(...trip));
    const held = await sync('2026-11-07T18:00:00Z', problem(returnScan, ...trip));
    expect(held.parcel).toMatchObject({ current_stage: 'returned', carrier_data: { return_held: true } });
    expect(held.shown).toBe('returned');
    expect(held.unheld).toBe('exception');
  });

  it('the problem first seen before the return scan leaves the apps and the server agreeing', async () => {
    const sync = checks();
    await sync('2026-11-05T10:00:00Z', problem(...trip));
    const held = await sync('2026-11-07T18:00:00Z', problem(back, returnScan, ...trip));
    expect(held.parcel).toMatchObject({ current_stage: 'returned', carrier_data: { return_held: true } });
    expect(held.shown).toBe('returned');
  });
});
