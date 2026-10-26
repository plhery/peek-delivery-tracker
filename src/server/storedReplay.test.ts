import { describe, expect, it } from 'vitest';
import { providerEventId } from './eventIdentity';
import { replayParcel, replaySql, type StoredParcel, type StoredScan } from './storedReplay';
import type { JsonObject } from './types';

const scan = (id: string, source: string, occurredAt: string, stage: string, description: string, raw: JsonObject): StoredScan => ({
  id, providerEventId: `${source}:${id}`, stage, occurredAt, description, location: null, raw: { stage_source: 'carrier_map', ...raw },
});
const parcel = (...events: StoredScan[]): StoredParcel => ({ id: 'parcel-1', destination: 'PL', open: true, events });

describe('stored scan replay', () => {
  it('stages a scan again where the map now reads its code and wording otherwise', () => {
    const changes = replayParcel(parcel(
      scan('origin', 'gofo', '2026-10-19T10:00:00Z', 'in_transit', 'Arrived at GOFO Regional Origin Hub', { provider_code: '202', stage: 'in_transit' }),
      scan('hub', 'gofo', '2026-10-20T10:00:00Z', 'in_transit', 'Arrived at GOFO Regional Hub', { provider_code: '202', stage: 'in_transit' }),
    ));

    expect(changes).toEqual([expect.objectContaining({ kind: 'stage', row: 'origin', from: 'in_transit', to: 'accepted', reason: 'mapped', stageSource: 'carrier_map' })]);
  });

  it("gives a notice stored as a scan the stage of its source's scan before it, and leaves one stored as a notice", () => {
    const changes = replayParcel(parcel(
      scan('data', 'dpd-de', '2026-10-18T10:00:00Z', 'registered', 'Order data received', { provider_code: 'DATA', stage: 'registered' }),
      scan('read', 'dpd-de', '2026-10-18T11:00:00Z', 'in_transit', 'Data exchanged', { provider_code: 'ENA', stage_source: 'wording:language' }),
      scan('kept', 'dpd-de', '2026-10-18T12:00:00Z', 'accepted', 'Data exchanged again', { provider_code: 'ENA', stage: 'accepted', stage_source: 'none' }),
    ));

    expect(changes).toEqual([expect.objectContaining({ row: 'read', from: 'in_transit', to: 'registered', reason: 'notice', stageSource: 'none' })]);
  });

  it('stores a notice after the delivery round as the sync does, pending', () => {
    const changes = replayParcel(parcel(
      scan('delivered', 'dpd-de', '2026-10-18T10:00:00Z', 'delivered', 'Delivered', { provider_code: 'DELIVERED', stage: 'delivered' }),
      scan('mapped', 'dpd-de', '2026-10-18T11:00:00Z', 'delivered', 'Delivery notice sent', { provider_code: 'MSDLO', stage: 'delivered' }),
      scan('pending', 'dpd-de', '2026-10-18T12:00:00Z', 'pending', 'Delivery notice sent again', { provider_code: 'MSDLO', stage_source: 'wording:language' }),
    ));

    expect(changes).toEqual([expect.objectContaining({ row: 'mapped', from: 'delivered', to: 'pending', reason: 'notice', declared: 'delivered', stageSource: 'none' })]);
    const sql = replaySql(changes);
    expect(sql).toContain("set stage = 'pending'");
    expect(sql).toContain(`'{"stage":"delivered","stage_source":"none"}'::jsonb`);
  });

  it('dates a wall clock again on the zone the release deduces, with the identity the sync would give it', () => {
    const [change] = replayParcel(parcel(
      scan('clock', 'poczta-polska', '2026-10-20T10:00:00Z', 'in_transit', 'Posted abroad', { local_time: '2026-10-20T10:00:00', time_zone: 'UTC', time_zone_basis: 'carrier', stage: 'in_transit' }),
    ));

    expect(change).toEqual(expect.objectContaining({
      kind: 'clock', row: 'clock', from: '2026-10-20T10:00:00.000Z', to: '2026-10-20T08:00:00.000Z', timeZone: 'Europe/Warsaw',
      providerEventId: providerEventId('poczta-polska', '2026-10-20T10:00:00+02:00', '', 'Posted abroad'), previousProviderEventId: 'poczta-polska:clock', clash: false,
    }));
  });

  it("leaves Peek's own rows, sightings without a scan time and clocks that came with their offset", () => {
    expect(replayParcel(parcel(
      scan('own', 'app', '2026-10-19T10:00:00Z', 'in_transit', 'Arrived at GOFO Regional Origin Hub', { provider_code: '202' }),
      scan('seen', 'gofo', '2026-10-19T10:00:00Z', 'in_transit', 'Arrived at GOFO Regional Origin Hub', { provider_code: '202', observed_without_provider_timestamp: true }),
      scan('stated', 'poczta-polska', '2026-10-20T10:00:00Z', 'in_transit', 'Posted abroad', { time: '2026-10-20T10:00:00Z', stage: 'in_transit' }),
    ))).toEqual([]);
  });

  it("dates an answer's wall clocks on its own places, not on a universal provider's copies beside it", () => {
    expect(replayParcel(parcel(
      {
        ...scan('copy', 'unknown', '2026-10-21T09:00:00Z', 'in_transit', 'Arrived in the destination country', {
          time: '2026-10-21T09:00:00.000Z', universal_provider: '17TRACK', stage: 'in_transit',
        }),
        location: 'Helsinki, Finland',
      },
      scan('own', 'unknown', '2026-10-21T12:09:00Z', 'out_for_delivery', 'Out for delivery', {
        local_time: '2026-10-21T20:09:00', time: '2026-10-21T20:09:00+08:00', time_zone: 'Asia/Shanghai', time_zone_basis: 'carrier', stage: 'out_for_delivery',
      }),
    ))).toEqual([]);
  });

  it("leaves a scan on its carrier's home clock, which its adapter may state itself", () => {
    expect(replayParcel(parcel({
      ...scan('home', 'zto-international', '2026-10-20T02:00:00Z', 'in_transit', 'Arrived at the sorting centre', {
        local_time: '2026-10-20T10:00:00', time: '2026-10-20T10:00:00+08:00', time_zone: 'Asia/Shanghai', time_zone_basis: 'carrier', stage: 'in_transit',
      }),
      location: 'Bangkok, Thailand',
    }))).toEqual([]);
  });

  it("replays a carrier's scan that took over a universal provider's copy with its carrier's rows", () => {
    // The copy's identity, which no longer hashes the carrier's strings, and no provider's name.
    const taken = {
      ...scan('taken', 'unknown', '2026-10-19T10:00:00Z', 'in_transit', 'Arrived at GOFO Regional Origin Hub', { provider_code: '202', stage: 'in_transit' }),
      providerEventId: providerEventId('unknown', '2026-10-19T09:00:00Z', '', 'Arrived at origin'),
    };

    expect(replayParcel({ ...parcel(taken), carriers: ['gofo'] }))
      .toEqual([expect.objectContaining({ kind: 'stage', row: 'taken', source: 'gofo', from: 'in_transit', to: 'accepted', reason: 'mapped' })]);
    // A handed-over parcel's carrier whose map knows the scan; with both or neither, the row stays apart.
    expect(replayParcel({ ...parcel(taken), carriers: ['yunexpress', 'gofo'] })).toEqual([expect.objectContaining({ row: 'taken', source: 'gofo' })]);
    expect(replayParcel({ ...parcel(taken), carriers: ['usps', 'gofo'] })).toEqual([]);
    expect(replayParcel(parcel(taken))).toEqual([]);
  });

  it("leaves a universal provider's copy stored before rows named their provider with the universal rows", () => {
    const time = '2026-10-19T10:00:00Z';
    const copy = scan('copy', 'unknown', time, 'in_transit', 'Arrived at GOFO Regional Origin Hub', { time, provider_code: '202', stage: 'in_transit' });

    expect(replayParcel({ ...parcel({ ...copy, providerEventId: providerEventId('unknown', time, '', copy.description) }), carriers: ['gofo'] })).toEqual([]);
  });

  it('writes one transaction, left open, that fails unless every guarded row changes', () => {
    const changes = replayParcel(parcel(
      scan('origin', 'gofo', '2026-10-19T10:00:00Z', 'in_transit', 'Arrived at GOFO Regional Origin Hub', { provider_code: '202', stage: 'in_transit' }),
    ));
    const sql = replaySql(changes);

    expect(sql).toMatch(/^\\set ON_ERROR_STOP on\nbegin;\n/);
    expect(sql).toContain("set stage = 'accepted'");
    expect(sql).toContain("where id = 'origin' and stage = 'in_transit'");
    expect(sql).toContain('if total <> 1 then raise exception');
    expect(sql).not.toMatch(/\bcommit\b|\brollback\b/);
  });
});
