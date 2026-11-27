import { describe, expect, it } from 'vitest';
import { withEventPlaces } from './eventPlaces';
import type { JsonObject } from './types';

const scan = (stage: string, occurred_at: string) => ({
  id: `e-${stage}`, package_id: 'p1', stage, description: stage, location: null, occurred_at, provider_event_id: `swiss-post:${stage}`,
});

/** A Swiss Post parcel, the carrier's clock Zurich's. */
function row(fields: JsonObject = {}): JsonObject {
  return {
    id: 'p1', carrier: 'swiss-post', tracking_number: 'TEST00000001', expected_delivery: null, archived_at: null,
    carrier_data: {}, tracking_events: [scan('in_transit', '2026-11-19T15:00:00Z')], ...fields,
  };
}

const served = (fields: JsonObject = {}) => withEventPlaces(row(fields)).delivery_round_ends_at;
const dayOver = (fields: JsonObject = {}) => withEventPlaces(row(fields)).delivery_round_over_at;
const out = (occurredAt: string) => [scan('in_transit', '2026-11-19T15:00:00Z'), scan('out_for_delivery', occurredAt)];

describe('delivery_round_ends_at', () => {
  it('ends the round with the day of the scan that took the parcel out, on the carrier\'s clock', () => {
    // 07:10 in Zurich on the 20th: the round is over at Zurich's midnight.
    expect(served({ tracking_events: out('2026-11-20T06:10:00Z') })).toBe('2026-11-21T00:00:00+01:00');
    // 00:30 on the 21st in Zurich, still the 20th in UTC: the round is the 21st's.
    expect(served({ tracking_events: out('2026-11-20T23:30:00Z') })).toBe('2026-11-22T00:00:00+01:00');
    // The round's day ends with it.
    expect(dayOver({ tracking_events: out('2026-11-20T06:10:00Z') })).toBe('2026-11-21T00:00:00+01:00');
  });

  it('reads the day on the clock the carrier declared, not UTC\'s', () => {
    // 22:00 on the 20th in New York, the 21st in UTC.
    expect(served({ carrier_data: { timezone: 'America/New_York' }, tracking_events: out('2026-11-21T03:00:00Z') }))
      .toBe('2026-11-21T00:00:00-05:00');
  });

  it('is left out for a parcel whose newest scan is not out for delivery, or that is archived', () => {
    expect(served()).toBeUndefined();
    expect(served({ tracking_events: [...out('2026-11-20T06:10:00Z'), scan('delivered', '2026-11-20T11:00:00Z')] })).toBeUndefined();
    expect(served({ tracking_events: [...out('2026-11-20T06:10:00Z'), scan('failed_attempt', '2026-11-20T11:00:00Z')] })).toBeUndefined();
    expect(served({ archived_at: '2026-11-21T08:00:00Z', tracking_events: out('2026-11-20T06:10:00Z') })).toBeUndefined();
    expect(dayOver()).toBeUndefined();
    expect(dayOver({ archived_at: '2026-11-21T08:00:00Z', tracking_events: out('2026-11-20T06:10:00Z') })).toBeUndefined();
  });
  it('ends a China Post round three days after its own: the relay leaves out the destination post\'s attempts', () => {
    const china = (events: JsonObject[], fields: JsonObject = {}) => served({ carrier: 'china-post', tracking_events: events, ...fields });
    const round = (source: string) => ({ ...scan('out_for_delivery', '2026-11-20T08:00:00Z'), provider_event_id: `${source}:ofd` });
    const [relayed, own, destination] = [round('unknown'), round('china-post'), round('la-poste')];
    const before = scan('in_transit', '2026-11-19T15:00:00Z');
    expect(china([before, relayed], { carrier_data: { timezone: 'Europe/Paris' } })).toBe('2026-11-24T00:00:00+01:00');
    // Its day is over that night: the apps stop showing it out for delivery then, and flag it three days later.
    expect(withEventPlaces(row({ carrier: 'china-post', tracking_events: [before, relayed], carrier_data: { timezone: 'Europe/Paris' } }))
      .delivery_round_over_at).toBe('2026-11-21T00:00:00+01:00');
    expect(china([before, own], { carrier_data: { timezone: 'Europe/Paris' } })).toBe('2026-11-24T00:00:00+01:00');
    // The destination post's own scan, or a parcel handed to it, keeps the day's round.
    expect(china([before, destination], { carrier_data: { timezone: 'Europe/Paris' } })).toBe('2026-11-21T00:00:00+01:00');
    expect(china([before, relayed], { carrier_data: { timezone: 'Europe/Paris', original_carrier: 'china-post', active_tracking_carrier: 'la-poste' } }))
      .toBe('2026-11-21T00:00:00+01:00');
  });
});
