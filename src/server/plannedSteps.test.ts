import { afterAll, beforeAll, describe, expect, it, vi } from 'vitest';
import { NoHistoryError } from 'universal-parcel-scraper';
import { atWrittenClock, plannedSteps, plannedStepsOf, PLANNED_STEPS_KEPT, servedPlannedSteps, servedWrittenAhead, servedWrittenSteps } from './plannedSteps';
import type { JsonObject } from './types';

vi.mock('server-only', () => ({}));
const { withEventPlaces } = await import('./eventPlaces');

// A tenant's script, written when the order was made: every step up to the delivery, each dated days ahead.
const script = [
  { time: '2026-10-20T09:00:00+08:00', description: 'Departed the origin facility', stage: 'in_transit', location: 'Shenzhen' },
  { time: '2026-10-24T18:00:00+08:00', description: 'Arrived in the destination country', stage: 'in_transit' },
  { time: '2026-11-03T08:00:00+08:00', description: 'Out for delivery', stage: 'out_for_delivery' },
  { time: '2026-11-04T15:00:00+08:00', description: 'Delivered', stage: 'delivered' },
];
const pickedUp = { id: 'scan', description: 'Picked up', stage: 'accepted', occurred_at: '2026-10-18T02:00:00.000Z', provider_event_id: 'mtscn:1' };
// The plan is read the morning after the parcel was picked up, before any of its steps is due.
beforeAll(() => { vi.useFakeTimers({ toFake: ['Date'] }); vi.setSystemTime(new Date('2026-10-18T03:00:00Z')); });
afterAll(() => { vi.useRealTimers(); });

const row = (overrides: JsonObject = {}, steps: unknown[] = script): JsonObject => ({
  id: 'parcel', carrier: 'mtscn', tracking_number: 'TEST1234', current_stage: 'accepted',
  carrier_data: { routing: { version: 1, planned: { number: 'TEST1234', carriers: { mtscn: steps } } } },
  tracking_events: [pickedUp], ...overrides,
});

describe('plannedStepsOf', () => {
  it('reads an answer\'s planned steps, and a failure\'s through its causes', () => {
    expect(plannedStepsOf({ planned_events: script })).toEqual(script);
    const failure = Object.assign(new NoHistoryError('MTSCN', 'No scans yet'), { plannedEvents: script.slice(0, 1) });
    expect(plannedStepsOf(new Error('wrapped', { cause: failure }))).toEqual(script.slice(0, 1));
    expect(plannedStepsOf(new NoHistoryError('MTSCN', 'No scans yet'))).toEqual([]);
    expect(plannedStepsOf(null)).toEqual([]);
  });

  it('keeps worded, dated steps once each, the earliest ones', () => {
    expect(plannedSteps([script[0], script[0], { time: script[1]!.time, description: ' ' }, { description: 'Undated' }, 'text',
      { local_time: '2026-11-01', description: '  Customs\n cleared ', stage: 'not-a-stage' }]))
      .toEqual([script[0], { local_time: '2026-11-01', description: 'Customs cleared' }]);
    const many = Array.from({ length: PLANNED_STEPS_KEPT + 5 }, (_, hour) => ({
      time: new Date(Date.UTC(2026, 11, 3) - hour * 3_600_000).toISOString(), description: `Step ${hour}` }));
    const kept = plannedSteps(many);
    expect(kept).toHaveLength(PLANNED_STEPS_KEPT);
    expect(kept.map((step) => step.description)).toEqual(many.slice(5).reverse().map((step) => step.description));
  });
});

describe('servedPlannedSteps', () => {
  it('serves the carrier\'s plan still ahead of its scans, oldest first, as no scan', () => {
    expect(servedPlannedSteps(row(), [pickedUp])).toEqual([
      { description: 'Departed the origin facility', stage: 'in_transit', location: 'Shenzhen', occurred_at: '2026-10-20T01:00:00.000Z' },
      { description: 'Arrived in the destination country', stage: 'in_transit', location: null, occurred_at: '2026-10-24T10:00:00.000Z' },
      { description: 'Out for delivery', stage: 'out_for_delivery', location: null, occurred_at: '2026-11-03T00:00:00.000Z' },
      { description: 'Delivered', stage: 'delivered', location: null, occurred_at: '2026-11-04T07:00:00.000Z' },
    ]);
  });

  it('dates a step on its day alone by that day, and keeps it through it', () => {
    const steps = servedPlannedSteps(row({}, [{ local_time: '2026-10-18', description: 'Customs cleared', stage: 'customs' }]), [pickedUp]);
    expect(steps).toEqual([{ description: 'Customs cleared', stage: 'customs', location: null, occurred_at: '2026-10-17T16:00:00.000Z', day: '2026-10-18' }]);
  });

  it('leaves out a step a scan tells, has passed or has come further than', () => {
    const departed = { ...pickedUp, id: 'departed', description: 'Departed  the origin FACILITY', stage: 'in_transit', location: 'Shenzhen', occurred_at: '2026-10-20T00:59:45.000Z' };
    expect(servedPlannedSteps(row(), [pickedUp, departed]).map((step) => step.description))
      .toEqual(['Arrived in the destination country', 'Out for delivery', 'Delivered']);
    expect(servedPlannedSteps(row(), [{ ...departed, location: 'Lyon' }])).toHaveLength(4);
    expect(servedPlannedSteps(row(), [{ ...departed, occurred_at: pickedUp.occurred_at }])).toHaveLength(4);
    const arrived = { ...pickedUp, id: 'arrived', description: 'Arrived at hub', stage: 'in_transit', occurred_at: '2026-10-25T00:00:00.000Z' };
    expect(servedPlannedSteps(row(), [pickedUp, arrived]).map((step) => step.description)).toEqual(['Out for delivery', 'Delivered']);
    // Out for delivery for real: a later step of an earlier stage is stale.
    const out = { ...pickedUp, id: 'out', description: 'With the courier', stage: 'out_for_delivery', occurred_at: '2026-10-19T00:00:00.000Z' };
    expect(servedPlannedSteps(row({ current_stage: 'out_for_delivery' }), [pickedUp, out]).map((step) => step.description))
      .toEqual(['Out for delivery', 'Delivered']);
  });

  it('serves none once the parcel is delivered or returned, for another number or another carrier', () => {
    expect(servedPlannedSteps(row({ current_stage: 'delivered' }), [pickedUp])).toEqual([]);
    expect(servedPlannedSteps(row(), [pickedUp, { ...pickedUp, id: 'back', stage: 'returned', occurred_at: '2026-10-01T00:00:00.000Z' }])).toEqual([]);
    expect(servedPlannedSteps(row({ tracking_number: 'TEST5678' }), [pickedUp])).toEqual([]);
    expect(servedPlannedSteps(row({ carrier: 'ups' }), [pickedUp])).toEqual([]);
    // The carrier delivering now is the one whose plan shows.
    expect(servedPlannedSteps(row({ carrier: 'ups', carrier_data: { active_tracking_carrier: 'mtscn',
      routing: (row().carrier_data as JsonObject).routing } }), [pickedUp])).toHaveLength(4);
  });

  it('leaves out a step whose time has passed, and a step dated by its day alone once that day is over', () => {
    // Out for delivery's planned morning is gone, its pre-written "Delivered" not yet.
    expect(servedPlannedSteps(row(), [pickedUp], Date.parse('2026-11-04T06:59:00Z')).map((step) => step.description)).toEqual(['Delivered']);
    expect(servedPlannedSteps(row(), [pickedUp], Date.parse('2026-11-04T07:00:00Z'))).toEqual([]);
    const customs = row({}, [{ local_time: '2026-10-18', description: 'Customs cleared', stage: 'customs' }]);
    expect(servedPlannedSteps(customs, [pickedUp], Date.parse('2026-10-18T15:59:00Z'))).toHaveLength(1);
    expect(servedPlannedSteps(customs, [pickedUp], Date.parse('2026-10-18T16:00:00Z'))).toEqual([]);
  });

  it('serves a plan before the first scan, Peek\'s own row aside', () => {
    const added = { id: 'added', description: 'Tracking added', stage: 'pending', occurred_at: '2026-11-01T00:00:00.000Z', provider_event_id: 'app:added' };
    expect(servedPlannedSteps(row({ current_stage: 'pending' }), [added])).toHaveLength(4);
  });
});

// The answers held back rows their carrier wrote ahead of their own clocks.
const ahead = (carriers: JsonObject, number = 'TEST1234'): JsonObject => ({ written_ahead: { number, carriers } });

describe('servedWrittenAhead', () => {
  it('names the carrier whose answers held rows back as written ahead', () => {
    expect(servedWrittenAhead(row({ carrier_data: { routing: ahead({ gzyl: ['2026-11-03T08:00:00+08:00'] }) } }))).toBe('gzyl');
  });

  it('names the carrier tracking the parcel first, when several did', () => {
    const both = ahead({ gzyl: ['2026-11-03T08:00:00+08:00'], mtscn: ['2026-11-04T15:00:00+08:00'] });
    expect(servedWrittenAhead(row({ carrier_data: { routing: both } }))).toBe('mtscn');
    expect(servedWrittenAhead(row({ carrier_data: { active_tracking_carrier: 'gzyl', routing: both } }))).toBe('gzyl');
  });

  it('names none for another number, an empty list, an unknown carrier, or once the parcel is delivered or returned', () => {
    const one = { carrier_data: { routing: ahead({ gzyl: ['2026-11-03T08:00:00+08:00'] }) } };
    expect(servedWrittenAhead(row({ carrier_data: { routing: ahead({ gzyl: ['2026-11-03T08:00:00+08:00'] }, 'OTHER1') } }))).toBeNull();
    expect(servedWrittenAhead(row({ carrier_data: { routing: ahead({ gzyl: [] }) } }))).toBeNull();
    expect(servedWrittenAhead(row({ carrier_data: { routing: ahead({ 'not-a-carrier': ['2026-11-03T08:00:00+08:00'] }) } }))).toBeNull();
    expect(servedWrittenAhead(row({ ...one, current_stage: 'delivered' }))).toBeNull();
    expect(servedWrittenAhead(row({ ...one, current_stage: 'returned' }))).toBeNull();
    expect(servedWrittenAhead(row())).toBeNull();
  });
});

// A GZYL script read on 10 November: its order row is a scan, every other row it wrote on 9 October for later dates.
const written = [
  { time: '2026-10-12T09:00:00+08:00', description: 'Departed the origin facility', stage: 'in_transit', location: 'Guangzhou' },
  { time: '2026-10-20T18:00:00+08:00', description: 'Arrived in the destination country', stage: 'in_transit' },
  { local_time: '2026-11-06', description: 'Out for delivery', stage: 'out_for_delivery' },
  { time: '2026-11-07T15:00:00+08:00', description: 'Delivered', stage: 'delivered' },
  { time: '2026-11-20T10:00:00+08:00', description: 'Proof of delivery filed' },
];
const held = ['2026-10-12T09:00:00+08:00', '2026-10-20T18:00:00+08:00', '2026-11-06T10:00:00', '2026-11-07T15:00:00+08:00', '2026-11-20T10:00:00+08:00'];
const ordered = { id: 'order', description: 'Order received', stage: 'registered', occurred_at: '2026-10-09T02:00:00.000Z', provider_event_id: 'gzyl:1' };
const READ = Date.parse('2026-11-10T00:00:00Z');
const scripted = (overrides: JsonObject = {}, clocks: string[] = held, steps: unknown[] = written): JsonObject => ({
  id: 'parcel', carrier: 'gzyl', tracking_number: 'TEST1234', current_stage: 'registered', tracking_events: [ordered],
  carrier_data: { routing: { version: 1, planned: { number: 'TEST1234', carriers: { gzyl: steps } },
    written_ahead: { number: 'TEST1234', carriers: { gzyl: clocks } } } },
  ...overrides,
});

describe('atWrittenClock', () => {
  it('matches a step to a held clock by its instant, its wall clock or its day', () => {
    expect(atWrittenClock({ time: '2026-10-12T09:00:00+08:00' }, ['2026-10-12T01:00:30Z'])).toBe(true);
    expect(atWrittenClock({ time: '2026-10-12T09:00:00+08:00' }, ['2026-10-12T09:00:00'])).toBe(true);
    expect(atWrittenClock({ local_time: '2026-10-12T09:00' }, ['2026-10-12T09:00:59'])).toBe(true);
    expect(atWrittenClock({ local_time: '2026-11-06' }, ['2026-11-06T10:00:00'])).toBe(true);
    expect(atWrittenClock({ time: '2026-10-12T09:00:00+08:00' }, ['2026-10-12T09:02:00+08:00', '2026-10-13T09:00:00'])).toBe(false);
    expect(atWrittenClock({ local_time: '2026-11-06' }, ['2026-11-07T10:00:00'])).toBe(false);
    expect(atWrittenClock({ time: '2026-10-25T02:30:00+02:00', local_time: '2026-10-25T02:30' },
      ['2026-10-25T02:30:00+01:00'])).toBe(false);
    expect(atWrittenClock({}, held)).toBe(false);
  });
});

describe('servedWrittenSteps', () => {
  it('serves the steps the carrier wrote in advance whose time has passed, oldest first, a delivery among them, and plans the rest', () => {
    expect(servedWrittenSteps(scripted(), [ordered], READ)).toEqual([
      { description: 'Departed the origin facility', stage: 'in_transit', location: 'Guangzhou', occurred_at: '2026-10-12T01:00:00.000Z' },
      { description: 'Arrived in the destination country', stage: 'in_transit', location: null, occurred_at: '2026-10-20T10:00:00.000Z' },
      { description: 'Out for delivery', stage: 'out_for_delivery', location: null, occurred_at: '2026-11-05T16:00:00.000Z', day: '2026-11-06' },
      { description: 'Delivered', stage: 'delivered', location: null, occurred_at: '2026-11-07T07:00:00.000Z' },
    ]);
    // The step still ahead is planned, and no step is both.
    expect(servedPlannedSteps(scripted(), [ordered], READ).map((step) => step.description)).toEqual(['Proof of delivery filed']);
  });

  it('serves a written "Delivered" before any real scan, which leaves the parcel as it was', () => {
    const added = { id: 'added', description: 'Tracking added', stage: 'pending', occurred_at: '2026-10-09T00:00:00.000Z', provider_event_id: 'app:added' };
    const parcel = scripted({ current_stage: 'pending', tracking_events: [added] });
    expect(servedWrittenSteps(parcel, [added], READ).at(-1)).toMatchObject({ description: 'Delivered', stage: 'delivered' });
    expect(parcel.current_stage).toBe('pending');
  });

  it('serves a step dated by its day alone once that day is over, and a timed one once its minute has come', () => {
    const outForDelivery = (now: string) => servedWrittenSteps(scripted(), [ordered], Date.parse(now)).map((step) => step.description);
    expect(outForDelivery('2026-11-06T15:59:00Z')).not.toContain('Out for delivery');
    expect(outForDelivery('2026-11-06T16:00:00Z')).toContain('Out for delivery');
    expect(outForDelivery('2026-11-07T06:59:00Z')).not.toContain('Delivered');
    expect(outForDelivery('2026-11-07T07:00:00Z')).toContain('Delivered');
  });

  it('leaves out a step at no held clock: a scan not due yet becomes the carrier\'s scan, and one it showed freed its clock', () => {
    const steps = servedWrittenSteps(scripted({}, held.filter((clock) => !clock.startsWith('2026-10-20'))), [ordered], READ);
    expect(steps.map((step) => step.description)).toEqual(['Departed the origin facility', 'Out for delivery', 'Delivered']);
  });

  it('leaves out a step a shown scan tells: at its minute, or in its words within a day', () => {
    const departed = { ...ordered, id: 'departed', description: 'Departed  the origin FACILITY', stage: 'in_transit', occurred_at: '2026-10-12T20:00:00.000Z' };
    const arrived = { ...ordered, id: 'arrived', description: 'Landed', stage: 'in_transit', occurred_at: '2026-10-20T10:00:30.000Z' };
    expect(servedWrittenSteps(scripted(), [ordered, departed, arrived], READ).map((step) => step.description))
      .toEqual(['Out for delivery', 'Delivered']);
    // The same words days apart are another step.
    const later = { ...departed, occurred_at: '2026-10-15T00:00:00.000Z' };
    expect(servedWrittenSteps(scripted(), [ordered, later], READ)).toHaveLength(4);
  });

  it('serves none once the parcel is delivered or returned, for another number, without a held clock, or for a carrier that holds none', () => {
    expect(servedWrittenSteps(scripted({ current_stage: 'delivered' }), [ordered], READ)).toEqual([]);
    expect(servedWrittenSteps(scripted(), [ordered, { ...ordered, id: 'back', stage: 'returned' }], READ)).toEqual([]);
    expect(servedWrittenSteps(scripted({ tracking_number: 'TEST5678' }), [ordered], READ)).toEqual([]);
    expect(servedWrittenSteps(scripted({}, []), [ordered], READ)).toEqual([]);
    // A carrier entering scans at their planned time names no clock as written ahead: its passed steps are no script.
    expect(servedWrittenSteps(row(), [pickedUp], Date.parse('2026-11-10T00:00:00Z'))).toEqual([]);
  });
});

describe('withEventPlaces', () => {
  it('serves the written steps whose time has passed apart from the scans and the plan, placed, and the stage untouched', () => {
    vi.setSystemTime(READ);
    try {
      const served = withEventPlaces(scripted({ current_stage: 'pending', tracking_events: [] }));
      expect(served).toMatchObject({ current_stage: 'pending', written_ahead_by: 'gzyl', tracking_events: [] });
      expect((served.written_ahead_events as JsonObject[]).map((step) => [step.description, step.stage]))
        .toEqual([['Departed the origin facility', 'in_transit'], ['Arrived in the destination country', 'in_transit'],
          ['Out for delivery', 'out_for_delivery'], ['Delivered', 'delivered']]);
      expect((served.written_ahead_events as JsonObject[])[0]!.place).toMatchObject({ country: 'CN', name: 'Guangzhou' });
      expect((served.planned_events as JsonObject[]).map((step) => step.description)).toEqual(['Proof of delivery filed']);
      expect((served.carrier_data as JsonObject).routing).toEqual({ version: 1 });
      expect(withEventPlaces(scripted({ current_stage: 'delivered' }))).not.toHaveProperty('written_ahead_events');
    } finally {
      vi.setSystemTime(new Date('2026-10-18T03:00:00Z'));
    }
  });

  it('serves the plan beside the scans, and keeps the stored plan out of the carrier data', () => {
    const served = withEventPlaces(row());
    expect(served.planned_events).toHaveLength(4);
    expect((served.tracking_events as JsonObject[]).map((event) => event.description)).toEqual(['Picked up']);
    expect((served.carrier_data as JsonObject).routing).toEqual({ version: 1 });
  });

  it('places each planned step as a scan is placed, so a town written in another script is named', () => {
    const steps = [{ ...script[0], location: '深圳市' }, ...script.slice(1)];
    const served = withEventPlaces(row({}, steps)).planned_events as JsonObject[];
    expect(served[0]!.place).toMatchObject({ precision: 'city', country: 'CN', name: 'Shenzhen' });
    expect(served[1]!.place).toBeNull();
  });

  it('serves the carrier writing the timeline ahead, and keeps the stored clocks out of the carrier data', () => {
    const served = withEventPlaces(row({ carrier_data: { routing: { version: 1, ...ahead({ gzyl: ['2026-11-03T08:00:00+08:00'] }) } } }));
    expect(served.written_ahead_by).toBe('gzyl');
    expect((served.carrier_data as JsonObject).routing).toEqual({ version: 1 });
    expect(withEventPlaces(row())).not.toHaveProperty('written_ahead_by');
    expect(withEventPlaces(row({ tracking_number: '123456789', carrier_data: { routing: { version: 1,
      ...ahead({ gzyl: ['2026-11-03T08:00:00+08:00'] }, '123456789') } } }))).not.toHaveProperty('written_ahead_by');
  });

  it('serves no plan for a delivered parcel, nor for a placeholder number', () => {
    expect(withEventPlaces(row({ current_stage: 'delivered' }))).not.toHaveProperty('planned_events');
    expect(withEventPlaces(row({ tracking_number: '123456789', carrier_data: { routing: { version: 1,
      planned: { number: '123456789', carriers: { mtscn: script } } } } }))).not.toHaveProperty('planned_events');
  });
});
