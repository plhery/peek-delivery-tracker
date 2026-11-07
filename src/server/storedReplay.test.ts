import { describe, expect, it } from 'vitest';
import { providerEventId } from './eventIdentity';
import { replayParcel, replaySql, steppedBack, type StoredParcel, type StoredScan } from './storedReplay';
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

  it('leaves a scan its adapter gave the stage of the scan before it, and stages one an earlier release left pending', () => {
    const kept = replayParcel(parcel(
      scan('round', 'chronopost', '2026-10-19T08:00:00Z', 'out_for_delivery', 'Colis en cours de livraison', { provider_code: 'TA', stage: 'out_for_delivery' }),
      scan('drop-off', 'chronopost', '2026-10-19T09:00:00Z', 'out_for_delivery', 'Colis en cours de livraison au point de retrait',
        { provider_code: 'RB', stage: 'out_for_delivery', stage_source: 'none' }),
    ));
    const unread = replayParcel(parcel(
      scan('label', 'chronopost', '2026-10-18T08:00:00Z', 'pending', 'Unread scan', { stage: 'pending', stage_source: 'none' }),
      scan('drop-off', 'chronopost', '2026-10-19T09:00:00Z', 'pending', 'Colis en cours de livraison au point de retrait',
        { provider_code: 'RB', stage: 'pending', stage_source: 'none' }),
    ));

    expect(kept).toEqual([]);
    expect(unread).toEqual([expect.objectContaining({ row: 'drop-off', from: 'pending', to: 'in_transit', reason: 'mapped' })]);
  });

  it("gives a notice stored as a scan the stage of its source's scan before it, and leaves one stored as a notice", () => {
    const changes = replayParcel(parcel(
      scan('data', 'dpd-de', '2026-10-18T10:00:00Z', 'registered', 'Order data received', { provider_code: 'DATA', stage: 'registered' }),
      scan('read', 'dpd-de', '2026-10-18T11:00:00Z', 'in_transit', 'Data exchanged', { provider_code: 'ENA', stage_source: 'wording:language' }),
      scan('kept', 'dpd-de', '2026-10-18T12:00:00Z', 'accepted', 'Data exchanged again', { provider_code: 'ENA', stage: 'accepted', stage_source: 'none' }),
    ));

    expect(changes).toEqual([expect.objectContaining({ row: 'read', from: 'in_transit', to: 'registered', reason: 'notice', stageSource: 'none' })]);
  });

  it('reads a wording Postal Ninja relays for PostNL with PostNL\'s map, as its adapter does', () => {
    const relayed = (id: string, stage: string, description: string, raw: JsonObject) => scan(id, 'unknown', `2026-11-0${id.length % 9}T10:00:00Z`,
      stage, description, { universal_provider: 'Postal Ninja', stage, ...raw });
    // PostNL's map stages a received shipment as accepted, which the shared reading of the providers' wording reads
    // as in transit: the replay keeps the stage the adapter gave, and reads a row with PostNL's map as it now stands.
    // A row stored before Postal Ninja named its service is read as before.
    const changes = replayParcel(parcel(
      relayed('kept', 'accepted', 'Shipment received by PostNL', { reporting_carrier: 'PostNL' }),
      relayed('reread', 'in_transit', 'Driver is en route', { reporting_carrier: 'PostNL', stage_source: 'wording:provider' }),
      relayed('unnamed', 'in_transit', 'Shipment received by PostNL', {}),
    ));
    // Another provider names the carrier without staging its wording with the carrier's map.
    const other = replayParcel(parcel(scan('17track', 'unknown', '2026-11-01T10:00:00Z', 'in_transit', 'Shipment received by PostNL',
      { universal_provider: '17TRACK', reporting_carrier: 'PostNL', stage: 'in_transit' })));

    expect(changes).toEqual([expect.objectContaining({ row: 'reread', from: 'in_transit', to: 'out_for_delivery', reason: 'mapped', stageSource: 'carrier_map' })]);
    expect(other).toEqual([]);
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

  it('steps an open parcel back with its rows once none shows its progress, guarded on its saved stage', () => {
    // A label scan an earlier release read in transit, the parcel's only progress.
    const label = scan('label', 'gofo', '2026-10-19T10:00:00Z', 'in_transit', 'Shipping Label Created', { provider_code: '100', stage: 'in_transit' });
    const origin = scan('origin', 'gofo', '2026-10-18T10:00:00Z', 'in_transit', 'Arrived at GOFO Regional Origin Hub', { provider_code: '202', stage: 'in_transit' });
    const app = scan('app', 'app', '2026-10-17T10:00:00Z', 'pending', 'Added', {});
    const open = { ...parcel(label, app), stage: 'in_transit' };
    const changes = replayParcel(open);
    expect(changes).toEqual([expect.objectContaining({ row: 'label', from: 'in_transit', to: 'registered' })]);
    expect(steppedBack([open], changes)).toEqual([{ parcel: 'parcel-1', from: 'in_transit', to: 'registered' }]);
    const sql = replaySql(changes, steppedBack([open], changes));
    expect(sql).toContain("update public.packages set current_stage = 'registered'\n   where id = 'parcel-1' and current_stage = 'in_transit';");
    expect(sql).toContain('if stepped <> 1 then raise exception');
    // The review reads the step back as the replay's, not a check's.
    expect(sql).toContain("begin;\nset local peek.hand_fix = 'stored-scan replay';\n");

    // A row still in transit, a closed or delivered parcel, or an export without the stage: no step back.
    const moved = { ...parcel(label, origin), stage: 'in_transit' };
    expect(steppedBack([moved], replayParcel(moved))).toEqual([]);
    for (const other of [{ ...open, open: false }, { ...open, stage: 'exception' }, parcel(label, app)]) {
      expect(steppedBack([other], replayParcel(other))).toEqual([]);
    }
    expect(replaySql(changes)).toContain('if stepped <> 0 then raise exception');
  });

  it('steps a delivered parcel still checked back once no row shows the delivery, to its newest row', () => {
    // An undelivered attempt an earlier release read as delivered, its row read again as the attempt.
    const round = scan('round', 'ekart', '2026-10-30T02:30:00Z', 'out_for_delivery', 'Out for delivery', { provider_code: 'OFD', stage: 'out_for_delivery' });
    const attempt = scan('attempt', 'ekart', '2026-10-30T09:50:00Z', 'delivered', 'Delivery attempted', { provider_code: 'UA', stage: 'delivered' });
    const notice = scan('notice', 'ekart', '2026-10-30T10:00:00Z', 'pending', 'Message sent', { stage_source: 'none' });
    const reread = { kind: 'stage', parcel: 'parcel-1', row: 'attempt', source: 'ekart', open: false, from: 'delivered', to: 'failed_attempt',
      code: 'UA', reason: 'mapped', declared: 'failed_attempt', stageSource: 'carrier_map' } as const;
    const delivered = { ...parcel(round, attempt, notice), open: false, stage: 'delivered', archived: false };
    expect(steppedBack([delivered], [reread])).toEqual([{ parcel: 'parcel-1', from: 'delivered', to: 'failed_attempt' }]);
    // A pickup notice once read as delivered, which the carrier's summary named over a later round: the stage its scan
    // reads now, not the round's. Read as a notice, it leaves the delivery to the operator.
    const later = scan('later', 'ekart', '2026-10-30T11:00:00Z', 'out_for_delivery', 'Out for delivery', { provider_code: 'OFD', stage: 'out_for_delivery' });
    expect(steppedBack([{ ...delivered, events: [round, attempt, later] }], [{ ...reread, to: 'ready_for_pickup' }]))
      .toEqual([{ parcel: 'parcel-1', from: 'delivered', to: 'ready_for_pickup' }]);
    expect(steppedBack([delivered], [{ ...reread, to: 'pending' }])).toEqual([]);
    // Of two deliveries read again, the newer one's.
    const handed = scan('handed', 'ekart', '2026-10-29T12:00:00Z', 'delivered', 'Delivered to the counter', { provider_code: 'DL', stage: 'delivered' });
    expect(steppedBack([{ ...delivered, events: [round, handed, attempt] }], [reread, { ...reread, row: 'handed', to: 'ready_for_pickup' }]))
      .toEqual([{ parcel: 'parcel-1', from: 'delivered', to: 'failed_attempt' }]);

    // Another row still showing a delivery, an archived parcel, an export without the field, or rows that showed none: no step back.
    for (const other of [{ ...delivered, events: [round, handed, attempt] }, { ...delivered, archived: true },
      { ...delivered, archived: undefined }, { ...delivered, events: [round, { ...attempt, stage: 'in_transit' }] }]) {
      expect(steppedBack([other], [reread])).toEqual([]);
    }
  });
});
