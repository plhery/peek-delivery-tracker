import { describe, expect, it, vi } from 'vitest';
import { carrierTimezone, withoutWrittenAhead, type CarrierResult } from 'universal-parcel-scraper';
import { atWrittenClock, servedPlannedSteps, servedWrittenSteps } from './plannedSteps';
import type { JsonObject } from './types';

vi.mock('server-only', () => ({}));

// Planned steps on a wall clock in no zone, read on the carrier's clock (GZYL's, Shanghai). Synthetic number and steps.
const ordered = { id: 'order', description: 'Order received', stage: 'registered', occurred_at: '2026-10-09T02:00:00.000Z', provider_event_id: 'gzyl:1' };
const scripted = (steps: unknown[], clocks: string[]): JsonObject => ({
  id: 'parcel', carrier: 'gzyl', tracking_number: 'TEST1234', current_stage: 'registered', tracking_events: [ordered],
  carrier_data: { routing: { version: 1, planned: { number: 'TEST1234', carriers: { gzyl: steps } },
    written_ahead: { number: 'TEST1234', carriers: { gzyl: clocks } } } },
});
const READ = Date.parse('2026-11-10T00:00:00Z');

describe('a step the scraper matches to a held clock by its local_time', () => {
  const step = { time: '2026-11-06T02:00:00Z', local_time: '2026-11-06T10:00:00', description: 'Delivered', stage: 'delivered' };
  const clocks = ['2026-11-06T10:00:00'];

  it('is at that clock for the scraper', () => {
    const kept = withoutWrittenAhead({ status: 'in_transit', last_status_text: 'Order received', last_update: '2026-10-09T02:00:00Z',
      events: [{ time: '2026-10-09T02:00:00Z', description: 'Order received' }, step] } as CarrierResult, clocks);
    expect(kept?.events?.map((event) => event.description)).toEqual(['Order received']);
  });

  it('is written in advance once passed, not planned', () => {
    expect(carrierTimezone('gzyl')).toBe('Asia/Shanghai');
    expect(servedPlannedSteps(scripted([step], clocks), [ordered], READ)).toEqual([]);
    expect(atWrittenClock(step, clocks)).toBe(true);
    expect(servedWrittenSteps(scripted([step], clocks), [ordered], READ).map((served) => served.description)).toEqual(['Delivered']);
  });
});

describe('a step on a wall clock in no zone', () => {
  // A carrier scan in other words at 10:00 Shanghai on the step's day.
  const scan = { id: 'hub', description: 'Processed at the hub', stage: 'in_transit', occurred_at: '2026-11-06T02:00:00.000Z', provider_event_id: 'gzyl:2' };

  it('goes once a scan stands at its minute, given with its offset', () => {
    const step = { time: '2026-11-06T10:00:00+08:00', description: 'Arrived at the sorting centre', stage: 'in_transit' };
    expect(servedWrittenSteps(scripted([step], ['2026-11-06T10:00:00+08:00']), [ordered, scan], READ)).toEqual([]);
  });

  it.each([
    ['time', { time: '2026-11-06T10:00:00' }],
    ['local_time', { local_time: '2026-11-06T10:00:00' }],
  ])('is read at its minute on the carrier\'s clock as %s, and goes once a scan stands there', (_field, clock) => {
    const step = { ...clock, description: 'Arrived at the sorting centre', stage: 'in_transit' };
    expect(servedWrittenSteps(scripted([step], ['2026-11-06T10:00:00']), [ordered], READ))
      .toEqual([{ description: 'Arrived at the sorting centre', stage: 'in_transit', location: null, occurred_at: '2026-11-06T02:00:00.000Z' }]);
    expect(servedWrittenSteps(scripted([step], ['2026-11-06T10:00:00']), [ordered, scan], READ)).toEqual([]);
  });

  it('is no longer planned once its minute has passed on the carrier\'s clock, though its day has not', () => {
    const step = { local_time: '2026-11-06T10:00:00', description: 'Delivered', stage: 'delivered' };
    const elevenAm = Date.parse('2026-11-06T03:00:00Z');
    expect(servedPlannedSteps(scripted([step], ['2026-11-06T10:00:00']), [ordered], elevenAm)).toEqual([]);
    expect(servedWrittenSteps(scripted([step], ['2026-11-06T10:00:00']), [ordered], elevenAm))
      .toEqual([{ description: 'Delivered', stage: 'delivered', location: null, occurred_at: '2026-11-06T02:00:00.000Z' }]);
  });
});
