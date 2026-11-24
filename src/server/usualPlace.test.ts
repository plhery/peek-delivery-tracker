import { readdirSync, readFileSync } from 'node:fs';
import path from 'node:path';
import { afterEach, describe, expect, it, vi } from 'vitest';
import type { ApiPackageRow } from '../generated/apiContract';
import { toParcel } from '../store/apiRepo';
import { withEventPlaces } from './eventPlaces';
import * as observability from './observability';
import type { SupabaseUserClient } from './supabase';
import type { JsonObject } from './types';
import { headsFor, servedOwnParcel, usualTown, withUsualPlaces } from './usualPlace';

// Synthetic parcels and public town names only.
type Scan = [stage: string, at: string, location: string | null];

let count = 0;
function stored(scans: Scan[], carrierData: JsonObject = {}): JsonObject {
  const id = `parcel-${count += 1}`;
  return {
    id, tracking_number: `TEST${String(count).padStart(8, '0')}`, label: null, carrier: 'la-poste', created_at: '2026-10-01T08:00:00Z',
    sync_status: 'ok', carrier_data: carrierData,
    tracking_events: scans.map(([stage, occurred_at, location], index) => ({
      id: `${id}-${index}`, package_id: id, stage, description: stage, location, occurred_at, point: null,
    })),
  };
}

/** A parcel delivered on `day` (of November 2026), out for delivery from `town` and delivered there. */
const deliveredIn = (town: string, day: number, delivery: string | null = town) => stored([
  ['in_transit', `2026-11-${String(day).padStart(2, '0')}T02:00:00Z`, 'Paris, FR'],
  ['out_for_delivery', `2026-11-${String(day).padStart(2, '0')}T07:00:00Z`, town],
  ['delivered', `2026-11-${String(day).padStart(2, '0')}T11:00:00Z`, delivery],
]);
const onItsWay = (scans: Scan[], carrierData: JsonObject = {}) => stored([['accepted', '2026-11-20T08:00:00Z', 'Hong Kong, HK'], ...scans], carrierData);
const served = (row: JsonObject) => withEventPlaces(row, { destination: true });
const read = (rows: JsonObject[]) => rows.map((row) => toParcel(served(row) as unknown as ApiPackageRow));
const town = (rows: JsonObject[]) => usualTown(read(rows));
const NO_INPUTS = { phoneDigits: false };

afterEach(() => { vi.restoreAllMocks(); });

describe('the town where an account\'s parcels are usually delivered', () => {
  it('is the town most of its deliveries placed in a town fall within 50 km of', () => {
    expect(town([deliveredIn('Lyon, FR', 1), deliveredIn('Villeurbanne, FR', 2), deliveredIn('Lyon, FR', 3), deliveredIn('Marseille, FR', 4)]))
      .toEqual({ latitude: expect.any(Number), longitude: expect.any(Number), precision: 'city', country: 'FR', name: 'Lyon' });
  });

  it('takes the town more deliveries name, then the newest, between towns as near', () => {
    expect(town([deliveredIn('Villeurbanne, FR', 1), deliveredIn('Lyon, FR', 2)])).toMatchObject({ name: 'Lyon' });
    expect(town([deliveredIn('Lyon, FR', 1), deliveredIn('Villeurbanne, FR', 2)])).toMatchObject({ name: 'Villeurbanne' });
    expect(town([deliveredIn('Villeurbanne, FR', 1), deliveredIn('Villeurbanne, FR', 2), deliveredIn('Lyon, FR', 3)])).toMatchObject({ name: 'Villeurbanne' });
  });

  it('needs at least two deliveries placed in a town, and more than half of them near it', () => {
    expect(town([deliveredIn('Lyon, FR', 1)])).toBeNull();
    expect(town([deliveredIn('Lyon, FR', 1), deliveredIn('Marseille, FR', 2)])).toBeNull();
    expect(town([deliveredIn('Lyon, FR', 1), deliveredIn('Lyon, FR', 2), deliveredIn('Marseille, FR', 3), deliveredIn('Bern, CH', 4)])).toBeNull();
    expect(town([deliveredIn('Lyon, FR', 1), deliveredIn('Lyon, FR', 2)])).toMatchObject({ name: 'Lyon' });
  });

  it('reads the last mile only: a delivery with no town there, a parcel still on its way or a return counts for nothing', () => {
    // Delivered from a depot in a town and handed over with no place: the depot's town counts.
    expect(town([deliveredIn('Lyon, FR', 1, null), deliveredIn('Lyon, FR', 2)])).toMatchObject({ name: 'Lyon' });
    // Only a country at the last mile, and the hub before it, place no delivery.
    expect(town([deliveredIn('FR', 1), deliveredIn('Lyon, FR', 2)])).toBeNull();
    expect(town([onItsWay([['out_for_delivery', '2026-11-21T07:00:00Z', 'Lyon, FR']]), deliveredIn('Lyon, FR', 2)])).toBeNull();
    expect(town([stored([['returned', '2026-11-03T08:00:00Z', 'Lyon, FR']]), deliveredIn('Lyon, FR', 2)])).toBeNull();
  });

  it('counts the newest ten deliveries, so a move takes over within a few parcels', () => {
    const before = Array.from({ length: 10 }, (_, day) => deliveredIn('Marseille, FR', day + 1));
    const after = Array.from({ length: 6 }, (_, day) => deliveredIn('Lyon, FR', day + 11));
    expect(town([...after, ...before])).toMatchObject({ name: 'Lyon' });
    expect(town([...before, ...after.slice(0, 5)])).toBeNull();
  });
});

describe('a parcel heading for that town', () => {
  const lyon = { latitude: 45.764, longitude: 4.836, precision: 'city' as const, country: 'FR', name: 'Lyon' };
  const heads = (row: JsonObject) => headsFor(read([row])[0]!, lyon);

  it('is one on its way in the town\'s country, more than 15 km from it, whose carrier names no closer place', () => {
    expect(heads(onItsWay([['in_transit', '2026-11-21T08:00:00Z', 'Paris, FR']]))).toBe(true);
    expect(heads(onItsWay([['out_for_delivery', '2026-11-21T08:00:00Z', 'Grenoble, FR']], { destination_country: 'FR' }))).toBe(true);
    // An unplaced scan after it leaves the parcel where it was last placed.
    expect(heads(onItsWay([['in_transit', '2026-11-21T08:00:00Z', 'Paris, FR'], ['in_transit', '2026-11-21T09:00:00Z', null]]))).toBe(true);
  });

  it('is not one still abroad, going to another country, almost there, delivered, at a pickup point or addressed', () => {
    expect(heads(onItsWay([['in_transit', '2026-11-21T08:00:00Z', 'Bern, CH']]))).toBe(false);
    expect(heads(onItsWay([['in_transit', '2026-11-21T08:00:00Z', 'Paris, FR']], { destination_country: 'DE' }))).toBe(false);
    expect(heads(onItsWay([['in_transit', '2026-11-21T08:00:00Z', 'Villeurbanne, FR']]))).toBe(false);
    for (const stage of ['delivered', 'ready_for_pickup', 'returned']) {
      expect(heads(onItsWay([['in_transit', '2026-11-21T08:00:00Z', 'Paris, FR'], [stage, '2026-11-21T09:00:00Z', null]]))).toBe(false);
    }
    expect(heads(onItsWay([['in_transit', '2026-11-21T08:00:00Z', 'Paris, FR']], { pickup_point: 'Relay shop, 1 Sample Street' }))).toBe(false);
    expect(heads(onItsWay([['in_transit', '2026-11-21T08:00:00Z', 'Paris, FR']], { receiver_address: '1 Sample Street\n13001 Marseille' }))).toBe(false);
    expect(heads(stored([]))).toBe(false);
  });

  it('is placed where the map places it, without a generated track', () => {
    // Moves at one interval to the second are generated (src/lib/generatedTrack.ts): the parcel is still in Bern.
    const generated = ['Paris, FR', 'Dijon, FR', 'Orléans, FR', 'Nantes, FR'].map((place, index): Scan =>
      ['in_transit', new Date(Date.parse('2026-11-20T09:00:00Z') + (index + 1) * 3_601_000).toISOString(), place]);
    expect(heads(onItsWay([['in_transit', '2026-11-20T09:00:00Z', 'Bern, CH'], ...generated]))).toBe(false);
  });
});

describe('serving it', () => {
  const user = (delivered: JsonObject[] | Error = []) => {
    const listDeliveredPackages = vi.fn(async () => { if (delivered instanceof Error) throw delivered; return delivered; });
    return { client: { listDeliveredPackages } as unknown as SupabaseUserClient, listDeliveredPackages };
  };
  const history = () => [deliveredIn('Lyon, FR', 1), deliveredIn('Lyon, FR', 2), deliveredIn('Villeurbanne, FR', 3)];

  it('puts the town on the parcels of the list that head for it, from the list itself', async () => {
    const paris = onItsWay([['in_transit', '2026-11-21T08:00:00Z', 'Paris, FR']]);
    const bern = onItsWay([['in_transit', '2026-11-21T08:00:00Z', 'Bern, CH']]);
    const parcels = [...history(), paris, bern].map(served);
    const { client, listDeliveredPackages } = user();
    const answer = await withUsualPlaces(client, parcels, { complete: true });
    expect(answer.map((row) => (row.usual_place as JsonObject | undefined)?.name)).toEqual([undefined, undefined, undefined, 'Lyon', undefined]);
    expect(answer[4]).toBe(parcels[4]);
    expect(listDeliveredPackages).not.toHaveBeenCalled();
  });

  it('reads the account\'s delivered parcels for one parcel, only when it could head for the town', async () => {
    const { client, listDeliveredPackages } = user(history());
    const paris = await servedOwnParcel(client, onItsWay([['in_transit', '2026-11-21T08:00:00Z', 'Paris, FR']]), NO_INPUTS);
    expect(paris.usual_place).toMatchObject({ name: 'Lyon', country: 'FR', precision: 'city' });
    expect(listDeliveredPackages).toHaveBeenCalledOnce();
    for (const row of [deliveredIn('Lyon, FR', 4), stored([]), onItsWay([['in_transit', '2026-11-21T08:00:00Z', 'Paris, FR']], { pickup_point: 'Locker 12' })]) {
      expect(await servedOwnParcel(client, row, NO_INPUTS)).not.toHaveProperty('usual_place');
    }
    expect(listDeliveredPackages).toHaveBeenCalledOnce();
  });

  it('serves the parcels as they are when the delivered ones cannot be read, reported once', async () => {
    const capture = vi.spyOn(observability, 'captureOperationalError').mockReturnValue(null);
    const failure = new Error('database unavailable');
    const { client } = user(failure);
    const row = onItsWay([['in_transit', '2026-11-21T08:00:00Z', 'Paris, FR']]);
    expect(await servedOwnParcel(client, row, NO_INPUTS)).toEqual(served(row));
    await servedOwnParcel(client, row, NO_INPUTS);
    expect(capture).toHaveBeenCalledExactlyOnceWith(failure, { component: 'api', operation: 'usual_delivery_town' });
  });
});

it('is served by the account\'s own parcel routes alone: no link, friend, email, assistant or push reads it', () => {
  const root = path.resolve(import.meta.dirname, '../..');
  const files = (directory: string): string[] => readdirSync(path.join(root, directory), { withFileTypes: true }).flatMap((entry) =>
    entry.isDirectory() ? files(path.join(directory, entry.name)) : /\.tsx?$/.test(entry.name) && !/\.test\.tsx?$/.test(entry.name) ? [path.join(directory, entry.name)] : []);
  const importers = [...files('app'), ...files('src')].filter((file) => /from '[./]+(?:src\/server\/)?usualPlace'/.test(readFileSync(path.join(root, file), 'utf8')));
  expect(importers.sort()).toEqual([
    'app/api/packages/[id]/carrier/route.ts', 'app/api/packages/[id]/notifications/route.ts', 'app/api/packages/[id]/restore/route.ts',
    'app/api/packages/[id]/route.ts', 'app/api/packages/route.ts',
  ]);
});
