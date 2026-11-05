import { describe, expect, it, vi } from 'vitest';
import { NoHistoryError } from 'universal-parcel-scraper';
import { plannedSteps, plannedStepsOf, PLANNED_STEPS_KEPT, servedPlannedSteps } from './plannedSteps';
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
    const departed = { ...pickedUp, id: 'departed', description: 'Departed  the origin FACILITY', stage: 'in_transit', occurred_at: '2026-10-19T00:00:00.000Z' };
    expect(servedPlannedSteps(row(), [pickedUp, departed]).map((step) => step.description))
      .toEqual(['Arrived in the destination country', 'Out for delivery', 'Delivered']);
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

  it('serves a plan before the first scan, Peek\'s own row aside', () => {
    const added = { id: 'added', description: 'Tracking added', stage: 'pending', occurred_at: '2026-11-01T00:00:00.000Z', provider_event_id: 'app:added' };
    expect(servedPlannedSteps(row({ current_stage: 'pending' }), [added])).toHaveLength(4);
  });
});

describe('withEventPlaces', () => {
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

  it('serves no plan for a delivered parcel, nor for a placeholder number', () => {
    expect(withEventPlaces(row({ current_stage: 'delivered' }))).not.toHaveProperty('planned_events');
    expect(withEventPlaces(row({ tracking_number: '123456789', carrier_data: { routing: { version: 1,
      planned: { number: '123456789', carriers: { mtscn: script } } } } }))).not.toHaveProperty('planned_events');
  });
});
