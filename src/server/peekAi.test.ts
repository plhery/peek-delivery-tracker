import { afterEach, describe, expect, it, vi } from 'vitest';

vi.mock('./observability', async (original) => ({
  ...await original<typeof import('./observability')>(), captureOperationalError: vi.fn(),
}));
vi.mock('./metrics', async (original) => ({
  ...await original<typeof import('./metrics')>(), recordPeekAiServed: vi.fn(),
}));

import { withEventPlaces } from './eventPlaces';
import { recordPeekAiServed } from './metrics';
import { captureOperationalError } from './observability';
import { closedDays, keepPeekAiModel, peekAiBlob, peekAiBlobFrom, peekAiFor, setPeekAiBlob, shortestRange, type PeekAiBlob } from './peekAi';
import type { PeekAiTree } from './peekAiModel';
import type { JsonObject } from './types';

const logit = (p: number) => Math.log(p / (1 - p));

/** One tree whose chance of arrival within h delivery days is F[h], whatever the scans tell. */
function curve(F: readonly number[]): PeekAiTree {
  const last = F.length - 1;
  const tree = { f: [] as number[], t: [] as number[], d: [] as number[], m: [] as number[], l: [] as number[], r: [] as number[], v: F.map(logit) };
  for (let i = 0; i < last; i++) {
    tree.f.push(0); tree.t.push(i + 0.5); tree.d.push(1); tree.m.push(0); tree.l.push(~i); tree.r.push(i + 1 < last ? i + 1 : ~last);
  }
  return tree;
}

let versions = 0;
/** A synthetic Peek AI: its rules as trained, with a model that gives every parcel the chances F. */
function blob(F: readonly number[] = [0.1, 0.85, 0.95, 0.99]): PeekAiBlob {
  return {
    version: `test-${++versions}`, trainedThrough: '2026-11-19', parcels: 1000,
    deliveryDays: { 'swiss-post': [0, 1, 2, 3, 4, 5], dpd: [0, 1, 2, 3, 4] }, homeCountries: { 'swiss-post': ['CH'], dpd: ['DE'] },
    offsets: { CH: 1, US: -6 }, defaultOffset: 1,
    notToday: { default: { from: 14, sure: { 14: 0.84, 15: 0.875, 16: 0.915, 19: 1 }, parcels: { 14: 90, 15: 89, 16: 86, 19: 84 } }, gofo: { from: null } },
    byTime: {
      'swiss-post': { by: '15:30', startBefore: '10:00', sure: 0.946, days: 300, parcels: 290, shares: [0.02, 0.1, 0.2, 0.35, 0.5, 0.65, 0.8, 0.9, 0.95, 0.97, 0.98, 0.99, 1, 1] },
    },
    lateOut: { default: { from: 20, sure: 0.885, parcels: 508 } },
    ofdUnreliable: ['china-post'],
    claims: { tau: 0.8, byDayWidth: 2, weekTau: 0.8, soonerLead: 2, soonerTau: 0.8, recentCheckHours: 3 },
    sure: { today_or_tomorrow: 0.89, by_day: 0.85, this_week: 0.91, by_end_of_next_week: 0.91, sooner: 0.88, missed_date: 0.89 },
    model: { horizon: F.length - 1, features: ['h'], categories: {}, targetEncoding: { prior: [3, 0.3], k: 8, cap: 12, keys: {} }, trees: [curve(F)] },
  };
}

const scan = (stage: string, occurred_at: string, fields: JsonObject = {}): JsonObject => ({
  id: `e-${stage}-${occurred_at}`, package_id: 'p1', stage, description: stage, location: null, occurred_at, stored_at: occurred_at,
  provider_event_id: `swiss-post:${stage}:${occurred_at}`, ...fields,
});
const ACCEPTED = scan('accepted', '2026-11-18T09:00:00Z');
const IN_TRANSIT = scan('in_transit', '2026-11-19T18:00:00Z');

/** A Swiss Post parcel on Zurich's clock, checked at 15:00 on Friday 20 November. */
function row(fields: JsonObject = {}, events: JsonObject[] = [ACCEPTED, IN_TRANSIT]): JsonObject {
  return {
    id: 'p1', carrier: 'swiss-post', tracking_number: 'TEST00000001', expected_delivery: null, archived_at: null,
    last_synced_at: '2026-11-20T14:00:00Z', carrier_data: { timezone: 'Europe/Zurich' }, tracking_events: events, ...fields,
  };
}
const ai = (parcel: JsonObject, now: string, model: PeekAiBlob | null = blob()) => peekAiFor(parcel, parcel, new Date(now), model);

afterEach(() => {
  setPeekAiBlob(null);
  vi.useRealTimers();
  vi.mocked(captureOperationalError).mockClear();
});

describe('the carrier says today', () => {
  it('says not today once nothing has gone out for delivery in the afternoon, a check having just looked', () => {
    const today = row({ expected_delivery: '2026-11-20' });
    expect(ai(today, '2026-11-20T14:30:00Z')).toEqual({
      says: 'not_today', why: 'no_out_for_delivery', carrier_day: '2026-11-20', sure: 0.875, parcels: 89, ends_at: '2026-11-21T00:00:00+01:00',
    });
    // Late in the evening: right every time it said so, shown as at most 95 in 100.
    expect(ai(row({ expected_delivery: '2026-11-20', last_synced_at: '2026-11-20T19:00:00Z' }), '2026-11-20T19:30:00Z')?.sure).toBe(0.95);
    // Too early, or no recent check.
    expect(ai(today, '2026-11-20T12:30:00Z')).toBeNull();
    expect(ai(row({ expected_delivery: '2026-11-20', last_synced_at: '2026-11-20T10:00:00Z' }), '2026-11-20T14:30:00Z')).toBeNull();
    // A carrier for which it never held.
    expect(ai(row({ carrier: 'gofo', expected_delivery: '2026-11-20' }), '2026-11-20T14:30:00Z')).toBeNull();
  });

  it('says nothing while a slot the carrier names for today is still open', () => {
    const slot = (expected_delivery: string) => ai(row({ expected_delivery, last_synced_at: '2026-11-20T18:00:00Z' }), '2026-11-20T18:30:00Z');
    expect(slot('2026-11-20 19:00-21:00')).toBeNull();
    expect(slot('2026-11-20T21:00:00+01:00')).toBeNull();
    // Once it has passed, the hint holds again.
    expect(slot('2026-11-20 16:00-18:00')).toMatchObject({ says: 'not_today', why: 'no_out_for_delivery' });
  });

  it("counts the hours on the destination's clock when no scan or carrier names a zone", () => {
    // 15:30 in the United States' middle: 22:30 in Zurich would be "19:00 and after".
    const american = row({ expected_delivery: '2026-11-20', last_synced_at: '2026-11-20T21:00:00Z', carrier_data: { destination_country: 'US' } });
    expect(ai(american, '2026-11-20T21:30:00Z')?.sure).toBe(0.875);
  });
});

describe('out for delivery', () => {
  const OUT = scan('out_for_delivery', '2026-11-20T07:10:00Z');
  const out = (fields: JsonObject = {}) => row(fields, [ACCEPTED, IN_TRANSIT, OUT]);

  it("says by the hour the carrier's deliveries mostly come by, with the share of each hour", () => {
    const said = ai(out(), '2026-11-20T09:00:00Z')!;
    expect(said).toMatchObject({
      says: 'by_time', why: 'out_for_delivery', time: '15:30', start_before: '10:00', sure: 0.946, parcels: 290,
      out_since: '2026-11-20T08:10:00+01:00', ends_at: '2026-11-20T15:30:00+01:00',
    });
    expect(said.hours).toHaveLength(13);
    expect(said.hours![0]).toEqual({ time: '08:00', share: 0.1 });
    expect(said.hours!.at(-1)).toEqual({ time: '20:00', share: 0 });
  });

  it('says nothing once the hour has passed, when the carrier names its own hour, or for a carrier whose rounds seldom end in a delivery', () => {
    expect(ai(out(), '2026-11-20T14:45:00Z')).toBeNull();
    expect(ai(out({ expected_delivery: '2026-11-20T13:00:00+01:00' }), '2026-11-20T09:00:00Z')).toBeNull();
    expect(ai(out({ carrier: 'china-post' }), '2026-11-20T09:00:00Z')).toBeNull();
    // Out after the hour: no hour to say.
    expect(ai(row({}, [ACCEPTED, scan('out_for_delivery', '2026-11-20T14:40:00Z')]), '2026-11-20T14:50:00Z')).toBeNull();
  });

  it('names no hour for a round that left at or after the time the hour holds for: later rounds end later', () => {
    const leftAt = (occurred: string, model = blob()) => ai(row({}, [ACCEPTED, IN_TRANSIT, scan('out_for_delivery', occurred)]), '2026-11-20T10:00:00Z', model);
    expect(leftAt('2026-11-20T08:55:00Z')).toMatchObject({ says: 'by_time', time: '15:30', start_before: '10:00' });
    expect(leftAt('2026-11-20T09:00:00Z')).toBeNull();
    expect(leftAt('2026-11-20T09:40:00Z')).toBeNull();
    // A model trained before the rounds' start was looked at: every round that left before the hour.
    const unbounded = blob();
    unbounded.byTime['swiss-post'] = { by: '15:30', sure: 0.946, days: 300, shares: unbounded.byTime['swiss-post']!.shares };
    const said = leftAt('2026-11-20T09:40:00Z', unbounded);
    expect(said).toMatchObject({ says: 'by_time', time: '15:30' });
    expect(said).not.toHaveProperty('start_before');
    expect(said).not.toHaveProperty('parcels');
  });

  it('names no hour for a parcel the carrier sent out on a day with no time, which may have left after it', () => {
    // Stored at the start of its day, or a second after the scan before it that day.
    const dayOnly = (occurred: string) => scan('out_for_delivery', occurred, { time: '2026-11-20' });
    expect(ai(row({}, [ACCEPTED, IN_TRANSIT, dayOnly('2026-11-19T23:00:00Z')]), '2026-11-20T09:00:00Z')).toBeNull();
    const sorted = scan('in_transit', '2026-11-20T05:30:00Z');
    expect(ai(row({}, [ACCEPTED, sorted, dayOnly('2026-11-20T05:30:01Z')]), '2026-11-20T09:00:00Z')).toBeNull();
    // Still out late in the evening, it says not today all the same.
    expect(ai(row({}, [ACCEPTED, IN_TRANSIT, dayOnly('2026-11-19T23:00:00Z')]), '2026-11-20T19:15:00Z')).toMatchObject({ says: 'not_today', why: 'late_out' });
  });

  it('says not today when still out late in the evening', () => {
    expect(ai(out(), '2026-11-20T19:15:00Z')).toEqual({
      says: 'not_today', why: 'late_out', sure: 0.885, parcels: 508, out_since: '2026-11-20T08:10:00+01:00', ends_at: '2026-11-21T00:00:00+01:00',
    });
    // Not inside the carrier's own slot, and again once it has passed.
    expect(ai(out({ expected_delivery: '2026-11-20 19:00-21:00' }), '2026-11-20T19:15:00Z')).toBeNull();
    expect(ai(out({ expected_delivery: '2026-11-20 17:00-19:00' }), '2026-11-20T19:15:00Z')).toMatchObject({ why: 'late_out' });
  });
});

describe("the carrier's day has passed", () => {
  const missed = (fields: JsonObject = {}) => row({ expected_delivery: '2026-11-19', ...fields }, [ACCEPTED, scan('in_transit', '2026-11-20T06:00:00Z')]);

  it('says today or tomorrow when a scan came since, by the next day the carrier delivers on, until that day ends', () => {
    expect(ai(missed(), '2026-11-20T14:30:00Z')).toEqual({
      says: 'today_or_tomorrow', why: 'missed_date', day: '2026-11-21', carrier_day: '2026-11-19', sure: 0.89, ends_at: '2026-11-21T00:00:00+01:00',
    });
    // DPD does not deliver on Saturdays.
    expect(ai(missed({ carrier: 'dpd' }), '2026-11-20T14:30:00Z')).toMatchObject({ says: 'by_day', day: '2026-11-23', ends_at: '2026-11-21T00:00:00+01:00' });
  });

  it('says nothing without a scan in the day since, or once the day is further back', () => {
    expect(ai(row({ expected_delivery: '2026-11-19' }), '2026-11-20T20:30:00Z')).toBeNull();
    expect(ai(missed({ expected_delivery: '2026-11-17' }), '2026-11-20T14:30:00Z')).toBeNull();
  });
});

describe('the model', () => {
  it('names a day when the carrier gives none, from the latest morning or afternoon it was trained at', () => {
    const said = ai(row(), '2026-11-20T14:30:00Z');
    expect(said).toEqual({
      says: 'today_or_tomorrow', why: 'no_date', day: '2026-11-21', sure: 0.89, parcels: 1000, last: { kind: 'scan', at: '2026-11-19T19:00:00+01:00' },
      days: [{ day: '2026-11-20', p: 0.1 }, { day: '2026-11-21', p: 0.75 }, { day: '2026-11-23', p: 0.1 }, { day: '2026-11-24', p: 0.04 }],
      ends_at: '2026-11-22T00:00:00+01:00',
    });
    expect(ai(row({ carrier: 'dpd' }), '2026-11-20T14:30:00Z')).toMatchObject({ says: 'by_day', day: '2026-11-23', sure: 0.85 });
  });

  it('says this week, or by the end of next week, when no shorter run of days is likely enough', () => {
    const tuesday = (F: number[]) => ai(row({ last_synced_at: '2026-11-17T08:30:00Z' }, [scan('accepted', '2026-11-16T09:00:00Z'),
      scan('in_transit', '2026-11-16T18:00:00Z')]), '2026-11-17T09:00:00Z', blob(F));
    expect(tuesday([0.15, 0.3, 0.45, 0.6, 0.88, 0.93, 0.96, 0.99])).toMatchObject({ says: 'this_week', day: '2026-11-22', sure: 0.91 });
    expect(tuesday([0.02, 0.05, 0.1, 0.2, 0.3, 0.5, 0.7, 0.9, 0.95, 0.97, 0.98]))
      .toMatchObject({ says: 'by_end_of_next_week', day: '2026-11-29', sure: 0.91 });
    expect(tuesday([0.02, 0.05, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.65, 0.7, 0.75])).toBeNull();
  });

  it("says maybe sooner when parcels like it usually come well before the carrier's day", () => {
    const later = (expected_delivery: string, F: number[]) => ai(row({ expected_delivery }), '2026-11-20T14:30:00Z', blob(F));
    expect(later('2026-11-24', [0.02, 0.85, 0.95, 0.99])).toMatchObject({
      says: 'sooner', why: 'ahead', carrier_day: '2026-11-24', day: '2026-11-21', sure: 0.88, parcels: 1000, ends_at: '2026-11-24T00:00:00+01:00',
    });
    // Not two days ahead, or not likely enough before it.
    expect(later('2026-11-21', [0.02, 0.85, 0.95, 0.99])).toBeNull();
    expect(later('2026-11-24', [0.02, 0.3, 0.6, 0.99])).toBeNull();
    // The carrier gives a window of days: sooner would only repeat its first.
    const window = row({ expected_delivery: '2026-11-24', carrier_data: { timezone: 'Europe/Zurich', expected_delivery_from: '2026-11-21' } });
    expect(ai(window, '2026-11-20T14:30:00Z', blob([0.02, 0.85, 0.95, 0.99]))).toBeNull();
  });

  it('names no day the carriers of the country the parcel goes to close on, such as Thanksgiving in the US', () => {
    // A DPD parcel on New York's clock, checked on Wednesday 25 November 2026, the day before Thanksgiving.
    const us = (fields: JsonObject = {}, country = 'US', model = blob()) => ai(row({ carrier: 'dpd', last_synced_at: '2026-11-25T19:00:00Z',
      carrier_data: { timezone: 'America/New_York', destination_country: country }, ...fields },
    [scan('accepted', '2026-11-23T14:00:00Z'), scan('in_transit', '2026-11-24T23:00:00Z')]), '2026-11-25T19:30:00Z', model);
    expect(us({}, 'CH')).toMatchObject({ says: 'today_or_tomorrow', day: '2026-11-26' });
    const said = us();
    expect(said).toMatchObject({ says: 'by_day', day: '2026-11-27' });
    expect(said?.days?.map(({ day }) => day)).not.toContain('2026-11-26');
    const sooner = blob([0.02, 0.85, 0.95, 0.99]);
    expect(us({ expected_delivery: '2026-11-30' }, 'CH', sooner)).toMatchObject({ says: 'sooner', carrier_day: '2026-11-30', day: '2026-11-26' });
    expect(us({ expected_delivery: '2026-11-30' }, 'US', sooner)).toMatchObject({ says: 'sooner', carrier_day: '2026-11-30', day: '2026-11-27' });
    // A holiday on a weekend closes the Friday before or the Monday after it.
    expect([...closedDays('US', [2026, 2027])].map((day) => new Date(day * 86_400_000).toISOString().slice(0, 10)).sort()).toEqual([
      '2026-01-01', '2026-05-25', '2026-07-03', '2026-07-04', '2026-09-07', '2026-11-26', '2026-12-25',
      '2027-01-01', '2027-05-31', '2027-07-04', '2027-07-05', '2027-09-06', '2027-11-25', '2027-12-24', '2027-12-25',
    ]);
    expect(closedDays('FR', [2026]).size).toBe(0);
  });

  it("places the scans to tell how far the parcel has come, on the clock of the destination it reached", () => {
    const placed = row({ carrier_data: { destination_country: 'CH' } }, [
      scan('accepted', '2026-11-17T09:00:00Z', { location: 'Paris, France' }),
      scan('customs', '2026-11-18T09:00:00Z', { location: 'Basel, Switzerland', description: 'Import customs clearance' }),
      scan('in_transit', '2026-11-19T18:00:00Z', { location: 'Zürich, Switzerland', description: 'Arrived at the delivery depot' }),
    ]);
    expect(ai(placed, '2026-11-20T14:30:00Z')?.last).toEqual({ kind: 'depot', at: '2026-11-19T19:00:00+01:00' });
    const customs = row({ carrier_data: { destination_country: 'CH' } }, [
      scan('accepted', '2026-11-17T09:00:00Z', { location: 'Paris, France' }),
      scan('customs', '2026-11-19T18:00:00Z', { location: 'Basel, Switzerland', description: 'Import customs clearance' }),
    ]);
    expect(ai(customs, '2026-11-20T14:30:00Z')?.last).toEqual({ kind: 'customs', at: '2026-11-19T19:00:00+01:00' });
    const reached = row({ carrier_data: { destination_country: 'CH' } }, [
      scan('accepted', '2026-11-17T09:00:00Z', { location: 'Paris, France' }),
      scan('in_transit', '2026-11-19T18:00:00Z', { location: 'Basel, Switzerland' }),
    ]);
    expect(ai(reached, '2026-11-20T14:30:00Z')?.last).toEqual({ kind: 'country', at: '2026-11-19T19:00:00+01:00', country: 'CH' });
  });

  it("tells a lookup from an account's parcel by its owner, or by what a link's row says of it", () => {
    // A model that gives a lookup good chances from today on, and an account's parcel poor ones.
    const base = blob();
    const split: PeekAiTree = { f: [1], t: [0.5], d: [1], m: [0], l: [~0], r: [~1], v: [logit(0.2), logit(0.97)] };
    const byOwner: PeekAiBlob = { ...base, model: { ...base.model, features: ['h', 'lookup'], trees: [split] } };
    // Each its own parcel: a parcel's chances are kept with its scans.
    const said = (id: string, fields: JsonObject) => ai(row({ id, ...fields }), '2026-11-20T14:30:00Z', byOwner);
    const lookup = said('p-guest', { user_id: null });
    expect(lookup).not.toBeNull();
    expect(said('p-guest-link', { lookup: true })).toEqual(lookup);
    const account = said('p-account', { user_id: 'an-account' });
    expect(said('p-account-link', { lookup: false })).toEqual(account);
    expect(account).not.toEqual(lookup);
  });

  it('reads again what the carrier adds of the parcel with no new scan, such as its weight', () => {
    // A model that gives a light parcel good chances from today on, and a heavy one poor ones.
    const base = blob();
    const split: PeekAiTree = { f: [1], t: [5], d: [1], m: [0], l: [~0], r: [~1], v: [logit(0.97), logit(0.2)] };
    const byWeight: PeekAiBlob = { ...base, model: { ...base.model, features: ['h', 'weight'], trees: [split] } };
    const said = (weight: string) =>
      ai(row({ id: 'p-weighed', carrier_data: { timezone: 'Europe/Zurich', weight_kg: weight } }), '2026-11-20T14:30:00Z', byWeight);
    const heavy = said('12');
    const light = said('1.2');
    expect(light).not.toBeNull();
    expect(light).not.toEqual(heavy);
  });

  it('waits for a check when the last one is more than a day and a half old', () => {
    expect(ai(row({ last_synced_at: '2026-11-18T12:00:00Z' }), '2026-11-20T14:30:00Z')).toBeNull();
  });
});

describe('parcels Peek AI says nothing of', () => {
  it('leaves out a parcel archived, arrived, waiting at a pickup point or returned, and says nothing with no model', () => {
    const now = '2026-11-20T14:30:00Z';
    expect(ai(row(), now, null)).toBeNull();
    expect(ai(row({ archived_at: '2026-11-20T10:00:00Z' }), now)).toBeNull();
    expect(ai(row({ current_stage: 'delivered' }), now)).toBeNull();
    expect(ai(row({}, [ACCEPTED, IN_TRANSIT, scan('delivered', '2026-11-20T10:00:00Z')]), now)).toBeNull();
    expect(ai(row({}, [ACCEPTED, IN_TRANSIT, scan('ready_for_pickup', '2026-11-20T10:00:00Z')]), now)).toBeNull();
    expect(ai(row({}, [ACCEPTED, scan('returned', '2026-11-19T10:00:00Z'), IN_TRANSIT]), now)).toBeNull();
  });

  it("reads only the carrier's own dated scans, as the model was trained on them", () => {
    const now = '2026-11-20T14:30:00Z';
    // Peek's own row and a scan stamped with when the carrier's system wrote it are not the carrier's journey.
    expect(ai(row({}, [scan('accepted', '2026-11-18T09:00:00Z', { provider_event_id: 'app:added' })]), now)).toBeNull();
    expect(ai(row({}, [scan('accepted', '2026-11-18T09:00:00Z', { written_at: '2026-11-18T09:00:00Z' })]), now)).toBeNull();
    expect(ai(row({}, [scan('accepted', '2026-11-18T09:00:00Z', { observed: true })]), now)).toBeNull();
  });
});

describe('served', () => {
  it("serves Peek AI's word on the parcel, never the scans' status codes or written times", () => {
    vi.useFakeTimers({ toFake: ['Date'] });
    vi.setSystemTime(new Date('2026-11-20T14:30:00Z'));
    setPeekAiBlob(blob());
    const served = withEventPlaces(row({}, [ACCEPTED, scan('in_transit', '2026-11-19T18:00:00Z', { provider_code: '13', written_at: null })]));
    expect(served.peek_ai).toMatchObject({ says: 'today_or_tomorrow', why: 'no_date' });
    // Emails, friends' passports and a link's preview show no estimate, so none is worked out for them.
    expect(withEventPlaces(row({}, [ACCEPTED, scan('in_transit', '2026-11-19T18:00:00Z')]), { peekAi: false })).not.toHaveProperty('peek_ai');
    for (const event of served.tracking_events as JsonObject[]) {
      expect(event).not.toHaveProperty('provider_code');
      expect(event).not.toHaveProperty('written_at');
    }
    setPeekAiBlob(null);
    expect(withEventPlaces(row())).not.toHaveProperty('peek_ai');
  });

  it('says nothing once the carrier has planned the delivery among its steps', () => {
    vi.useFakeTimers({ toFake: ['Date'] });
    vi.setSystemTime(new Date('2026-11-20T14:30:00Z'));
    setPeekAiBlob(blob());
    const planned = (stage: string) => row({ carrier_data: { timezone: 'Europe/Zurich', routing: { version: 1, planned: {
      number: 'TEST00000001', carriers: { 'swiss-post': [{ time: '2026-11-21T09:00:00+01:00', description: 'Planned step', stage }] },
    } } } });
    expect(withEventPlaces(planned('in_transit')).peek_ai).toMatchObject({ says: 'today_or_tomorrow' });
    expect(withEventPlaces(planned('out_for_delivery'))).not.toHaveProperty('peek_ai');
    expect(withEventPlaces(planned('delivered'))).not.toHaveProperty('peek_ai');
  });

  it('counts a parcel once a day for each call, however often a list left open serves it', () => {
    vi.useFakeTimers({ toFake: ['Date'] });
    setPeekAiBlob(blob());
    vi.mocked(recordPeekAiServed).mockClear();
    const counted = (id: string, at: string) => {
      vi.setSystemTime(new Date(at));
      withEventPlaces(row({ id }));
      return vi.mocked(recordPeekAiServed).mock.calls.length;
    };
    expect(counted('counted-1', '2026-11-20T14:30:00Z')).toBe(1);
    expect(counted('counted-1', '2026-11-20T14:30:30Z')).toBe(1);
    expect(counted('counted-2', '2026-11-20T14:31:00Z')).toBe(2);
    // The next day it is counted again.
    expect(counted('counted-1', '2026-11-21T08:00:00Z')).toBe(3);
  });
});

describe('the stored model', () => {
  it('loads the newest, keeps it while its version stands and drops it when none is stored', async () => {
    const stored = blob();
    const source = { latestPeekAiModelVersion: vi.fn().mockResolvedValue(stored.version), peekAiModel: vi.fn().mockResolvedValue(stored) };
    vi.useFakeTimers();
    const stop = keepPeekAiModel(source, 1000);
    await vi.advanceTimersByTimeAsync(0);
    expect(peekAiBlob()?.version).toBe(stored.version);
    await vi.advanceTimersByTimeAsync(1000);
    expect(source.peekAiModel).toHaveBeenCalledTimes(1);
    source.latestPeekAiModelVersion.mockResolvedValue(null);
    await vi.advanceTimersByTimeAsync(1000);
    expect(peekAiBlob()).toBeNull();
    stop();
  });

  it('reports a model it cannot read and keeps the one in place', async () => {
    const kept = blob();
    setPeekAiBlob(kept);
    const source = { latestPeekAiModelVersion: vi.fn().mockResolvedValue('next'), peekAiModel: vi.fn().mockResolvedValue({ version: 'next' }) };
    vi.useFakeTimers();
    const stop = keepPeekAiModel(source, 1000);
    await vi.advanceTimersByTimeAsync(0);
    expect(peekAiBlob()).toBe(kept);
    expect(captureOperationalError).toHaveBeenCalledWith(expect.any(Error), { component: 'api', operation: 'load_peek_ai_model' });
    source.latestPeekAiModelVersion.mockRejectedValue(new Error('down'));
    await vi.advanceTimersByTimeAsync(1000);
    expect(peekAiBlob()).toBe(kept);
    expect(captureOperationalError).toHaveBeenCalledTimes(2);
    stop();
  });

  it('accepts only a document with every part Peek AI reads', () => {
    expect(peekAiBlobFrom(blob())).not.toBeNull();
    expect(peekAiBlobFrom(null)).toBeNull();
    expect(peekAiBlobFrom({ ...blob(), claims: { tau: 'high' } })).toBeNull();
    expect(peekAiBlobFrom({ ...blob(), lateOut: {} })).toBeNull();
    const noModel: Record<string, unknown> = { ...blob() };
    delete noModel.model;
    expect(peekAiBlobFrom(noModel)).toBeNull();
    // A part missing would serve "right NaN times in 10", or say sooner of every parcel.
    const sure: Record<string, number> = { ...blob().sure };
    delete sure.today_or_tomorrow;
    expect(peekAiBlobFrom({ ...blob(), sure })).toBeNull();
    const claims: Record<string, number> = { ...blob().claims };
    delete claims.soonerTau;
    expect(peekAiBlobFrom({ ...blob(), claims })).toBeNull();
  });

  it('says nothing a model measured right less than 8 times in 10', () => {
    const weak = blob();
    weak.sure.today_or_tomorrow = 0.6;
    expect(ai(row(), '2026-11-20T14:30:00Z', weak)).toBeNull();
  });
});

describe('the shortest likely run of days', () => {
  it('is the narrowest run holding enough of the chance, the likeliest of its width', () => {
    expect(shortestRange([0.1, 0.85, 0.95], 0.8)).toEqual({ a: 0, b: 1 });
    expect(shortestRange([0.02, 0.85, 0.95], 0.8)).toEqual({ a: 1, b: 1 });
    expect(shortestRange([0.1, 0.2], 0.8)).toBeNull();
  });
});
