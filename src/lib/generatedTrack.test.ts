import { describe, expect, it } from 'vitest';
import { generatedScans, withoutGeneratedScans } from './generatedTrack';
import type { Stage, TrackingEvent } from '../types';

const START = Date.parse('2026-10-01T09:00:00Z');

/** A scan `seconds` after the first, at its own place unless given one. */
const scan = (id: string, seconds: number, location = `Place ${id}`, stage: Stage = 'in_transit'): TrackingEvent => ({
  id, parcelId: 'parcel-1', stage, description: 'In transit', occurredAt: new Date(START + seconds * 1_000).toISOString(), location,
});

/** The first scan and a move after each gap. */
const track = (...gaps: number[]) => gaps.reduce<TrackingEvent[]>((scans, gap, index) =>
  [...scans, scan(`move-${index + 1}`, (Date.parse(scans.at(-1)!.occurredAt) - START) / 1_000 + gap)], [scan('first', 0)]);
const ids = (scans: readonly TrackingEvent[]) => scans.map((event) => event.id);

describe('generated tracks', () => {
  it('reads three moves to new places at one interval to the second as generated, up to the newest', () => {
    const scans = track(86_111, 86_112, 86_110);
    expect(ids(generatedScans(scans))).toEqual(['move-3', 'move-2', 'move-1']);
    // Where the parcel was before the track started stays.
    expect(ids(withoutGeneratedScans(scans))).toEqual(['first']);
  });

  it('keeps reading a track generated when its interval changes', () => {
    expect(ids(generatedScans(track(86_111, 86_111, 86_111, 75_311)))).toEqual(['move-4', 'move-3', 'move-2', 'move-1']);
  });

  it('starts the track at the first repeated interval: moves before it at another may be real', () => {
    const scans = [scan('posted', -40_000, 'Origin', 'accepted'), ...track(40_123, 86_111, 86_111, 86_111)];
    expect(ids(withoutGeneratedScans(scans))).toEqual(['posted', 'first', 'move-1']);
  });

  it('leaves a track in whole minutes, as a carrier that rounds its clock gives, and one moved on fewer than three times', () => {
    expect(generatedScans(track(86_400, 86_400, 86_400, 86_400))).toEqual([]);
    expect(generatedScans(track(86_111, 86_111))).toEqual([]);
    expect(generatedScans(track(86_111, 50_123, 86_111))).toEqual([]);
  });

  it('leaves a parcel whose newest scan moved it no further', () => {
    const scans = track(86_111, 86_111, 86_111);
    const last = Date.parse(scans.at(-1)!.occurredAt) - START;
    expect(generatedScans([...scans, scan('delivered', last / 1_000 + 86_111, 'Place delivered', 'delivered')])).toEqual([]);
    expect(generatedScans([...scans, scan('again', last / 1_000 + 86_111, 'Place move-3')])).toEqual([]);
    expect(generatedScans([...scans, scan('unplaced', last / 1_000 + 86_111, '')])).toEqual([]);
  });

  it("reads past Peek's own rows and a place told twice at one instant", () => {
    const scans = track(86_111, 86_111, 86_111);
    const added = scan('added', 100, 'Peek', 'pending');
    const copy = { ...scans.at(-1)!, id: 'copy', location: ' PLACE MOVE-3' };
    expect(ids(generatedScans([...scans, added, copy]))).toEqual(['move-3', 'copy', 'move-2', 'move-1']);
  });
});
