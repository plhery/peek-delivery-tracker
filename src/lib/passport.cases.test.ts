import fs from 'node:fs';
import path from 'node:path';
import { describe, expect, it } from 'vitest';
import { passportBook } from './passport';
import type { ParcelWithEvents, Stage } from '../types';

type Scan = [Stage, string, string?, string?];
interface Case {
  name: string;
  timeZone: string;
  parcels: Array<{ id: string; label: string; carrier: string; archivedAt?: string; scans: Scan[] }>;
  expect?: unknown;
}

const file = path.join(__dirname, '../../shared/passport-cases.json');
const data = JSON.parse(fs.readFileSync(file, 'utf8')) as { '//': string; cases: Case[] };

const parcels = (input: Case['parcels']): ParcelWithEvents[] => input.map(({ id, label, carrier, archivedAt, scans }) => ({
  id, label, carrier: carrier as ParcelWithEvents['carrier'], syncStatus: 'ok', trackingNumber: `T${id}`, createdAt: '2026-01-01T00:00:00Z',
  ...(archivedAt ? { archivedAt } : {}),
  events: scans.map(([stage, occurredAt, location, placed], index) => ({ id: `${id}-${index}`, parcelId: id, stage, occurredAt, location, description: stage,
    ...(placed ? { place: { latitude: 0, longitude: 0, precision: 'city' as const, country: placed, name: location ?? placed } } : {}) })),
}));

const iso = (at: number | null) => at === null ? null : new Date(at).toISOString();

/** What a case's passport holds, as the three platforms compare it. */
function outcome(input: Case) {
  const { stats, stamps, countries } = passportBook(parcels(input.parcels), input.timeZone);
  return {
    deliveredCount: stats.deliveredCount,
    carrierCount: stats.carrierCount,
    stamps: Object.fromEntries(stamps.map((stamp) => [stamp.key, { count: stamp.count, earned: stamp.earned, earnedAt: iso(stamp.earnedAt), parcel: stamp.parcelId }])),
    countries: countries.map((country) => ({ code: country.code, number: country.number, firstAt: iso(country.firstAt), parcel: country.parcelId, count: country.count, continent: country.continent })),
  };
}

describe('the passport cases every platform replays', () => {
  if (process.env.WRITE_PASSPORT_CASES) {
    it('writes the expectations', () => {
      const cases = data.cases.map((input) => ({ ...input, expect: outcome(input) }));
      fs.writeFileSync(file, `${JSON.stringify({ ...data, cases }, null, 1)}\n`);
    });
    return;
  }
  it.each(data.cases.map((input) => [input.name, input] as const))('%s', (_, input) => {
    expect(outcome(input)).toEqual(input.expect);
  });
});
