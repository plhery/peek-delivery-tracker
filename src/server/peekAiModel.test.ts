import { describe, expect, it } from 'vitest';
import {
  codePoints, deliveryDaysBetween, featureVector, orderedScans, predictArrival, predictArrivalRaw, pythonLower, pythonPattern,
  rawScore, roundTenth, scanMilestones, serviceClass, snapshotFeatures, statusKey, withTargetEncoding,
  type AiParcel, type AiScan, type PeekAiModelDoc, type PeekAiTree,
} from './peekAiModel';

/** A synthetic model: features `x` (a number), `k` (a category) and `h`. */
const model = (trees: PeekAiTree[], horizon = 3): PeekAiModelDoc => ({
  horizon, features: ['x', 'k', 'h'], categories: { k: ['a', 'b', '?'] },
  targetEncoding: { prior: [4, 0.25], k: 8, cap: 12, keys: { 'dpd|c:13': [40, 4, 8], 'dpd|?': [80, 0, 8] } }, trees,
});
/** One split on feature `f`: left leaf -1, right leaf 1. */
const split = (f: number, t: number | number[], d = 1, m = 0): PeekAiTree => ({ f: [f], t: [t], d: [d], m: [m], l: [~0], r: [~1], v: [-1, 1] });
const score = (tree: PeekAiTree, x: number, k = 0, h = 0) => rawScore(model([tree]), Float64Array.from([x, k, h]));
const sigmoid = (z: number) => 1 / (1 + Math.exp(-z));
const day = (iso: string) => Date.parse(`${iso}T00:00:00Z`) / 86_400_000;
const at = (iso: string) => Date.parse(iso) / 1000;

describe('the trees', () => {
  it('splits a number at its threshold, a missing one as zero unless the split learned where missing ones go', () => {
    expect(score(split(0, 1.5), 1)).toBe(-1);
    expect(score(split(0, 1.5), 2)).toBe(1);
    // No missing values in training: a missing value reads as 0.
    expect(score(split(0, -0.5), NaN)).toBe(1);
    // Missing values learned: they go the split's default way.
    expect(score(split(0, 1.5, 0, 2), NaN)).toBe(1);
    expect(score(split(0, 1.5, 1, 2), NaN)).toBe(-1);
    // Zeros as missing: a zero, or a missing value, goes the default way.
    expect(score(split(0, -5, 1, 1), 0)).toBe(-1);
    expect(score(split(0, -5, 0, 1), NaN)).toBe(1);
  });

  it('sends a category left when the split lists it, and a missing or unseen one right', () => {
    expect(score(split(1, [0, 2]), 0, 0)).toBe(-1);
    expect(score(split(1, [0, 2]), 0, 1)).toBe(1);
    expect(score(split(1, [0, 2]), 0, 2)).toBe(-1);
    expect(score(split(1, [0, 2]), 0, NaN)).toBe(1);
    expect(score(split(1, [0, 2]), 0, -1)).toBe(1);
  });

  it('adds every tree, a tree of one leaf as its value', () => {
    expect(rawScore(model([split(0, 1.5), { leaf: 0.25 }]), Float64Array.from([2, 0, 0]))).toBe(1.25);
  });

  it('gives a chance for each delivery day that never falls back', () => {
    // h 0: +1, h 1: -1 (a dip), h 2 and 3: +2.
    const byDay: PeekAiTree = { f: [2, 2], t: [0.5, 1.5], d: [1, 1], m: [0, 0], l: [~0, ~1], r: [1, ~2], v: [1, -1, 2] };
    expect(predictArrivalRaw(model([byDay]), {})).toEqual([sigmoid(1), sigmoid(-1), sigmoid(2), sigmoid(2)]);
    expect(predictArrival(model([byDay]), {})).toEqual([sigmoid(1), sigmoid(1), sigmoid(2), sigmoid(2)]);
  });
});

describe('the features as the model reads them', () => {
  it('puts each in the model order, a category as its index and a missing value as NaN', () => {
    expect(Array.from(featureVector({ x: 3, k: 'b' }, model([]), 2))).toEqual([3, 1, 2]);
    expect(Array.from(featureVector({ x: null, k: null }, model([]), 0))).toEqual([NaN, 2, 0]);
    expect(Array.from(featureVector({ x: 'text', k: 'unseen' }, model([]), 0))).toEqual([NaN, NaN, 0]);
  });

  it("encodes the newest scan's status by the carrier's parcels after it, else the carrier's rare ones, else all", () => {
    const m = model([]);
    expect(withTargetEncoding({ c: 'dpd' }, 'c:13', m)).toMatchObject({ te_mean: (40 + 32) / 16, te_p1: (4 + 2) / 16, te_n: Math.log1p(8) });
    expect(withTargetEncoding({ c: 'dpd' }, 'c:99', m)).toMatchObject({ te_mean: (80 + 32) / 16, te_p1: 2 / 16 });
    expect(withTargetEncoding({ c: 'ups' }, 'c:13', m)).toMatchObject({ te_mean: 4, te_p1: 0.25, te_n: 0 });
  });

  it("keys a status by the carrier's code, else by its words without digits", () => {
    expect(statusKey('13', 'Ignored')).toBe('c:13');
    expect(statusKey('x'.repeat(45), null)).toBe(`c:${'x'.repeat(40)}`);
    expect(statusKey(null, '  Arrived at  Hub 42\nZurich ')).toBe('d:arrived at hub # zurich');
    expect(statusKey(null, null)).toBe('d:');
    expect(statusKey(null, 'É'.repeat(60))).toBe(`d:${'é'.repeat(50)}`);
  });

  it("reads words as the training's Python did", () => {
    // \b between any letters, not only ASCII ones; $ also before a final newline.
    expect(pythonPattern(String.raw`\bpac\b`).test('pacé')).toBe(false);
    expect(pythonPattern(String.raw`\bpac\b`).test('le pac.')).toBe(true);
    expect(pythonPattern('done$').test('done\n')).toBe(true);
    expect(pythonLower('ΟΔΟΣ')).toBe('οδοσ');
    expect(codePoints('😀😀😀', 2)).toBe('😀😀');
  });

  it("tells a scan's milestones by its words, a customs scan that tells no export as an import", () => {
    expect(scanMilestones('in_transit', 'Arrived at the delivery depot')).toContain('depot');
    expect(scanMilestones('customs', 'Held by customs')).toContain('import');
    expect(scanMilestones('customs', 'Export customs cleared')).not.toContain('import');
    expect(scanMilestones('in_transit', null).size).toBe(0);
  });

  it('sorts the service by its name', () => {
    expect(serviceClass(null)).toBe('unknown');
    expect(serviceClass('DHL Express Worldwide')).toBe('express');
    expect(serviceClass('UPS Ground')).toBe('economy');
    expect(serviceClass('Parcel')).toBe('other');
  });

  it("rounds to a tenth as Python's round does, an exact half to even", () => {
    expect(roundTenth(0.25)).toBe('0.2');
    expect(roundTenth(0.75)).toBe('0.8');
    expect(roundTenth(-0.25)).toBe('-0.2');
    expect(roundTenth(0.35)).toBe('0.3');
    expect(roundTenth(47.3768)).toBe('47.4');
    expect(roundTenth(-0.04)).toBe('0.0');
    expect(roundTenth(NaN)).toBe('NaN');
  });

  it('counts delivery days after a day, up to and including another', () => {
    const weekdays = [0, 1, 2, 3, 4];
    // Friday 20 November to Monday 23: the Monday only.
    expect(deliveryDaysBetween(weekdays, day('2026-11-20'), day('2026-11-23'))).toBe(1);
    expect(deliveryDaysBetween([0, 1, 2, 3, 4, 5], day('2026-11-20'), day('2026-11-23'))).toBe(2);
    expect(deliveryDaysBetween(weekdays, day('2026-11-20'), day('2026-11-20'))).toBe(0);
  });

  it('orders scans by time, then stage', () => {
    const scan = (stage: string, ts: number): AiScan => ({ stage, ts, description: null, providerCode: null, source: 's', country: null, lat: null, lon: null, precision: null });
    expect(orderedScans([scan('in_transit', 2), scan('customs', 2), scan('accepted', 1)]).map((s) => s.stage)).toEqual(['accepted', 'customs', 'in_transit']);
  });
});

describe('a snapshot of the scans', () => {
  const parcel: AiParcel = { c: 'dpd', destinationCountry: 'DE', serviceName: null, weightKg: '1.5', lookup: false, offset: 3600 };
  const context = { deliveryDays: { dpd: [0, 1, 2, 3, 4] }, homeCountries: { dpd: ['DE'] } };
  const scan = (stage: string, iso: string, fields: Partial<AiScan> = {}): AiScan => ({
    stage, ts: at(iso), description: stage, providerCode: null, source: 'dpd', country: null, lat: null, lon: null, precision: null, ...fields,
  });
  const scans = orderedScans([
    scan('pending', '2026-11-16T08:00:00Z'),
    scan('accepted', '2026-11-17T09:00:00Z', { country: 'FR', lat: 48.85, lon: 2.35 }),
    scan('in_transit', '2026-11-18T22:00:00Z', { description: 'Arrived at the delivery depot', country: 'DE', lat: 52.52, lon: 13.4 }),
  ]);
  const features = (iso: string, list = scans) => {
    const t = at(iso);
    const local = t + parcel.offset;
    return snapshotFeatures(parcel, list, t, Math.floor(local / 86_400), Math.floor((local % 86_400) / 3600), context);
  };

  it('reads where the parcel is, how far it came and what its scans tell', () => {
    const got = features('2026-11-19T07:00:00Z')!;
    expect(got).toMatchObject({
      c: 'dpd', stage: 'in_transit', hour: 8, dow: 3, deliv_today: 1, n_scans: 3, now_depot: 1, ever_depot: 1, placed: 1,
      n_countries: 2, reached: 1, pos_in_dest: 1, abroad: 0, origin: 'FR', dest: 'DE', lane: 'cross', dest_declared: 1,
      carrier_home_dest: 1, service: 'unknown', weight: 1.5, lookup: 0, h_since_depot: 9, n_places: 2,
    });
    expect(got.km_from_first).toBeCloseTo(877.7, 0);
  });

  it('reads nothing before the first physical scan, after an arrival, or after a month without a scan', () => {
    expect(features('2026-11-16T12:00:00Z')).toBeNull();
    expect(features('2026-11-20T12:00:00Z', orderedScans([...scans, scan('delivered', '2026-11-19T10:00:00Z')]))).toBeNull();
    expect(features('2026-12-20T12:00:00Z')).toBeNull();
    expect(features('2026-11-20T12:00:00Z', orderedScans([...scans, scan('returned', '2026-11-19T10:00:00Z')]))).toBeNull();
  });
});
