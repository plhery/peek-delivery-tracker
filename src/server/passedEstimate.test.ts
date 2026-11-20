import { afterEach, beforeEach, describe, expect, it, vi } from 'vitest';
import { withEventPlaces } from './eventPlaces';
import { passedEstimateDay } from './passedEstimate';
import type { JsonObject } from './types';

/** 11:00 in Zurich, on Friday 20 November. */
const NOW = new Date('2026-11-20T10:00:00Z');

const scan = (stage: string, occurred_at = '2026-11-16T08:00:00Z') => ({
  id: `e-${stage}`, package_id: 'p1', stage, description: stage, location: null, occurred_at, provider_event_id: `swiss-post:${stage}`,
});

/** A Swiss Post parcel in transit, the carrier's clock Zurich's. */
function row(fields: JsonObject = {}): JsonObject {
  return {
    id: 'p1', carrier: 'swiss-post', tracking_number: 'TEST00000001', expected_delivery: null, archived_at: null,
    carrier_data: {}, tracking_events: [scan('in_transit')], ...fields,
  };
}

const served = (fields: JsonObject = {}) => withEventPlaces(row(fields)).expected_delivery_passed;

describe('expected_delivery_passed', () => {
  beforeEach(() => { vi.useFakeTimers({ toFake: ['Date'] }); vi.setSystemTime(NOW); });
  afterEach(() => { vi.useRealTimers(); });

  it('names the expected day from the day after it through the seventh day after', () => {
    // Day 0: the day is today, the apps' estimate.
    expect(served({ expected_delivery: '2026-11-20' })).toBeUndefined();
    expect(served({ expected_delivery: '2026-11-19' })).toBe('2026-11-19');
    expect(served({ expected_delivery: '2026-11-13' })).toBe('2026-11-13');
    // Day 8: too long ago to tell the reader anything.
    expect(served({ expected_delivery: '2026-11-12' })).toBeUndefined();
    expect(served({ expected_delivery: '2026-11-23' })).toBeUndefined();
  });

  it('gives the last day of a slot, a moment or a window', () => {
    expect(served({ expected_delivery: '2026-11-18 13:00–15:00' })).toBe('2026-11-18');
    expect(served({ expected_delivery: '2026-11-18T18:00:00+01:00' })).toBe('2026-11-18');
    expect(served({ expected_delivery: '2026-11-18', carrier_data: { expected_delivery_from: '2026-11-16' } })).toBe('2026-11-18');
  });

  it('counts the days on the clock of the carrier delivering the parcel', () => {
    // 00:30 on the 21st in Zurich, still the 20th in UTC: the 20th has passed for Swiss Post.
    const lateEvening = new Date('2026-11-20T23:30:00Z');
    expect(passedEstimateDay(row({ expected_delivery: '2026-11-20' }), row(), lateEvening)).toBe('2026-11-20');
    // The day ends at midnight on its clock, not before.
    expect(passedEstimateDay(row({ expected_delivery: '2026-11-20' }), row(), new Date('2026-11-20T22:30:00Z'))).toBeNull();
  });

  it('keeps the day a check stopped keeping once it passed, until the carrier names a day again', () => {
    expect(served({ carrier_data: { last_expected_delivery: '2026-11-17' } })).toBe('2026-11-17');
    // A later day the carrier names is the apps' estimate: nothing passed.
    expect(served({ expected_delivery: '2026-11-23', carrier_data: { last_expected_delivery: '2026-11-17' } })).toBeUndefined();
    // Moved later and passed again: the later day is the one that passed.
    expect(served({ expected_delivery: '2026-11-19', carrier_data: { last_expected_delivery: '2026-11-17' } })).toBe('2026-11-19');
    // The kept day is the service's: the apps read only the served field.
    const shown = withEventPlaces(row({ carrier_data: { last_expected_delivery: '2026-11-17', sender_name: 'Shop' } }));
    expect(shown.carrier_data).toEqual({ sender_name: 'Shop' });
  });

  it('names none without an estimate', () => {
    expect(served()).toBeUndefined();
    expect(served({ expected_delivery: '' })).toBeUndefined();
    expect(served({ expected_delivery: 'next week' })).toBeUndefined();
  });

  it('names none once the parcel is delivered, returned, waiting at a pickup point or archived', () => {
    for (const stage of ['delivered', 'returned', 'ready_for_pickup']) {
      expect(served({ expected_delivery: '2026-11-18', tracking_events: [scan('in_transit'), scan(stage, '2026-11-19T09:00:00Z')] }))
        .toBeUndefined();
    }
    expect(served({ expected_delivery: '2026-11-18', archived_at: '2026-11-19T09:00:00Z' })).toBeUndefined();
  });

  it('names the day while the parcel is out for delivery, held up or has no scan yet', () => {
    for (const stage of ['out_for_delivery', 'failed_attempt', 'exception', 'customs']) {
      expect(served({ expected_delivery: '2026-11-18', tracking_events: [scan('in_transit'), scan(stage, '2026-11-18T09:00:00Z')] }))
        .toBe('2026-11-18');
    }
    expect(served({ expected_delivery: '2026-11-18', tracking_events: [] })).toBe('2026-11-18');
  });
});
