import { afterEach, beforeEach, describe, expect, it, vi } from 'vitest';
import { parcelAttention } from '../lib/parcelPriority';
import { currentStage } from '../lib/stages';
import { toParcel } from '../store/apiRepo';
import type { ApiPackageRow } from '../generated/apiContract';
import { listParcels } from './assistants/tools';
import { detectCarrier } from './carrierDetection';
import { withEventPlaces } from './eventPlaces';
import { addParcelsFromText } from './parcelsFromText';
import { isReviewDemoParcel, reviewDemoAccounts, reviewDemoCarrier, reviewDemoNumbersInText, withReviewDemo } from './reviewDemo';
import { SupabaseServiceClient, SupabaseUserClient } from './supabase';
import { trackingSyncDueAt } from './trackingSync';
import type { JsonObject } from './types';
import { newPackageValues } from './validation';

// Synthetic account ids only.
const reviewer = '5d000000-0000-4000-a000-0000000000a1';
const reader = '5d000000-0000-4000-a000-0000000000b2';
const HOUR = 3_600_000;
const DAY = 24 * HOUR;

/** The day a time falls on in Zürich, where the scenarios happen and an account without a timezone reads its days. */
function dayOf(time: number): string {
  return new Intl.DateTimeFormat('en-CA', { timeZone: 'Europe/Zurich', year: 'numeric', month: '2-digit', day: '2-digit' }).format(time);
}

let rows = 0;
function row(trackingNumber: string, carrier = 'dhl', label = ''): JsonObject {
  rows += 1;
  return {
    id: `5e000000-0000-4000-a000-${String(rows).padStart(12, '0')}`,
    tracking_number: trackingNumber,
    label,
    carrier,
    created_at: new Date(Date.now() - 5 * DAY).toISOString(),
    sync_status: 'pending',
    carrier_data: {},
    archived_at: null,
    tracking_events: [],
  };
}

/** The parcel the apps read from a row the database gave the owner. */
function served(stored: JsonObject, userId: string | null, now = Date.now()) {
  return toParcel(withEventPlaces(withReviewDemo(stored, userId, now)) as unknown as ApiPackageRow);
}

beforeEach(() => {
  vi.stubEnv('REVIEW_DEMO_ACCOUNTS', `${reviewer}, someone@example.com, *, not-an-id`);
  vi.spyOn(console, 'log').mockImplementation(() => undefined);
});
afterEach(() => { vi.restoreAllMocks(); vi.unstubAllEnvs(); vi.useRealTimers(); });

describe('review demo parcels', () => {
  it('are on in the accounts listed by id only', () => {
    expect([...reviewDemoAccounts()]).toEqual([reviewer]);
    expect(reviewDemoCarrier(reviewer.toUpperCase(), 'TESTOUT01')).toBe('swiss-post');
    expect(reviewDemoCarrier(reader, 'TESTOUT01')).toBeNull();
    vi.stubEnv('REVIEW_DEMO_ACCOUNTS', '');
    expect(reviewDemoCarrier(reviewer, 'TESTOUT01')).toBeNull();
  });

  it('take only a scenario\'s shape: TEST, a scenario and two to six digits', () => {
    for (const number of ['TESTOUT1', 'TESTOUT1234567', 'TESTLOST01', 'XTESTOUT01', 'TEST1234', '1Z999AA10123456784']) {
      expect(reviewDemoCarrier(reviewer, number)).toBeNull();
    }
    expect(reviewDemoCarrier(reviewer, 'TESTDELIVERED03')).toBe('planzer');
  });

  it('are any unknown number for everyone else: unchanged, and checked as usual', () => {
    const stored = { ...row('TESTOUT01', 'swiss-post'), user_id: reader };
    expect(withReviewDemo(stored, reader)).toBe(stored);
    expect(isReviewDemoParcel(stored)).toBe(false);
    expect(trackingSyncDueAt(stored)).toBe(0);
  });

  it('are never checked, while the account\'s real parcels are', () => {
    expect(trackingSyncDueAt({ ...row('TESTOUT01', 'swiss-post'), user_id: reviewer })).toBe(Infinity);
    expect(trackingSyncDueAt({ ...row('1Z999AA10123456784', 'ups'), user_id: reviewer })).toBe(0);
    const real = row('1Z999AA10123456784', 'ups');
    expect(withReviewDemo(real, reviewer)).toBe(real);
  });

  it('play each scenario as a carrier would tell it, with every scan in the past', () => {
    const now = Date.now();
    const today = dayOf(now);
    const expected = {
      TESTTODAY01: { carrier: 'dpd', stage: 'in_transit', day: dayOf(now), attention: null },
      TESTOUT01: { carrier: 'swiss-post', stage: 'out_for_delivery', day: today, attention: null },
      TESTABROAD01: { carrier: 'aliexpress', stage: 'in_transit', day: dayOf(now + 4 * DAY), attention: null },
      TESTCUSTOMS01: { carrier: 'dhl-express', stage: 'customs', day: undefined, attention: 'customs_duties' },
      TESTMISSED01: { carrier: 'ups', stage: 'failed_attempt', day: dayOf(now + DAY), attention: 'failed_attempt' },
      TESTPICKUP01: { carrier: 'swiss-post', stage: 'ready_for_pickup', day: undefined, attention: 'ready_for_pickup' },
      TESTDELIVERED03: { carrier: 'planzer', stage: 'delivered', day: dayOf(now - 3 * DAY), attention: null },
      TESTNOTFOUND01: { carrier: 'dhl', stage: null, day: undefined, attention: null },
      TESTWEEK01: { carrier: 'dhl', stage: 'in_transit', day: dayOf(now + 3 * DAY), attention: null },
      TESTNEW01: { carrier: 'dhl', stage: 'registered', day: dayOf(now + 3 * DAY), attention: null },
    } as const;
    for (const [number, scenario] of Object.entries(expected)) {
      const carrier = reviewDemoCarrier(reviewer, number)!;
      expect(carrier, number).toBe(scenario.carrier);
      // A carrier the add takes, with no postcode or link to give.
      expect(newPackageValues({ trackingNumber: number, label: '', carrier, trackingUrl: '' }).carrier).toBe(carrier);
      const parcel = served(row(number, carrier), reviewer, now);
      expect(currentStage(parcel.events, parcel.returnHeld) ?? null, number).toBe(scenario.stage);
      expect(parcel.expectedDelivery, number).toBe(scenario.day);
      expect(parcelAttention(parcel, now), number).toBe(scenario.attention);
      expect(parcel.syncStatus).toBe('ok');
      for (const event of parcel.events) expect(Date.parse(event.occurredAt), number).toBeLessThanOrEqual(now);
      if (parcel.newsAt) expect(Date.parse(parcel.newsAt)).toBeLessThanOrEqual(now);
    }
    const pickup = served(row('TESTPICKUP01', 'swiss-post'), reviewer, now);
    expect(pickup.pickupPoint).toBe('Zürich Sihlpost post office');
    const unscanned = served(row('TESTNOTFOUND01'), reviewer, now);
    expect(unscanned.events).toEqual([]);
    expect(now - Date.parse(unscanned.createdAt)).toBeLessThan(4 * HOUR);
  });

  it('keep "arriving today" true at every hour of the day, and the scans the same within an hour', () => {
    const stored = row('TESTOUT01', 'swiss-post', 'IKEA');
    // Midnight in Zürich, whatever the clock of the machine running the test.
    const midnight = Date.parse('2026-11-16T23:00:00.000Z');
    for (const minutes of [5, 59, 61, 12 * 60 + 30, 23 * 60 + 55]) {
      const now = midnight + minutes * 60_000;
      const parcel = served(stored, reviewer, now);
      expect(parcel.expectedDelivery).toBe(dayOf(now));
      expect(Date.parse(parcel.events.at(-1)!.occurredAt)).toBeLessThan(now);
      expect(Date.parse(parcel.events.at(-1)!.occurredAt)).toBeGreaterThan(now - 2 * HOUR);
    }
    const first = served(stored, reviewer, midnight + 10 * HOUR + 60_000);
    const later = served(stored, reviewer, midnight + 10 * HOUR + 59 * 60_000);
    expect(later.events).toEqual(first.events);
    expect(first.events.map(({ id }) => id).every((id) => /^[0-9a-f]{8}-[0-9a-f]{4}-4[0-9a-f]{3}-a[0-9a-f]{3}-[0-9a-f]{12}$/.test(id))).toBe(true);
  });

  it('give the delivered scenario its days since delivery from the digits', () => {
    const now = Date.now();
    const yesterday = served(row('TESTDELIVERED01', 'planzer'), reviewer, now).events.at(-1)!;
    const lastWeek = served(row('TESTDELIVERED07', 'planzer'), reviewer, now).events.at(-1)!;
    expect(yesterday.stage).toBe('delivered');
    expect(now - Date.parse(yesterday.occurredAt)).toBeGreaterThan(DAY);
    expect(now - Date.parse(yesterday.occurredAt)).toBeLessThan(DAY + 3 * HOUR);
    expect(now - Date.parse(lastWeek.occurredAt)).toBeGreaterThan(7 * DAY);
  });

  it('are read out of a text only in a listed account, however the text spaces them', () => {
    const text = 'Shipped with DHL. Tracking number: TEST-NEW-01. Also testout 07, TESTNEW01 again and TESTLOST01.';
    expect(reviewDemoNumbersInText(reviewer, text).map(({ normalized, match }) => [normalized, match.carrier]))
      .toEqual([['TESTNEW01', 'dhl'], ['TESTOUT07', 'swiss-post']]);
    expect(reviewDemoNumbersInText(reader, text)).toEqual([]);
  });
});

describe('a review demo account', () => {
  it('adds a demo number from a text without the allowance, a first check or anyone asked', async () => {
    const service = new SupabaseServiceClient('https://database.example', 'service-key');
    vi.spyOn(service, 'accountTrackingNumbers').mockResolvedValue(new Map());
    const create = vi.spyOn(service, 'createAccountPackage').mockResolvedValue('5e000000-0000-4000-a000-0000000000f1');
    const allowance = vi.spyOn(service, 'claimAccountTracking');
    const queue = vi.spyOn(service, 'enqueueSyncJob');
    const text = 'Your order has shipped with DHL. Tracking number: TESTNEW01';
    expect(await addParcelsFromText(service, reviewer, text, 'Example Shop')).toEqual([
      { trackingNumber: 'TESTNEW01', carrier: 'dhl', outcome: 'added', packageId: '5e000000-0000-4000-a000-0000000000f1', jobId: null },
    ]);
    expect(create).toHaveBeenCalledExactlyOnceWith(reviewer, 'TESTNEW01', 'Example Shop', 'dhl', null, null);
    expect(allowance).not.toHaveBeenCalled();
    expect(queue).not.toHaveBeenCalled();
    // Anyone else's text names no number Peek reads.
    expect(await addParcelsFromText(service, reader, text, 'Example Shop')).toEqual([]);
    expect(create).toHaveBeenCalledOnce();
  });

  it('detects a demo number as its scenario\'s carrier without asking anyone', async () => {
    const asking = vi.fn();
    expect(await detectCarrier({ trackingNumber: 'test-missed-01' }, asking, undefined, undefined, null, reviewer))
      .toEqual({ trackingNumber: 'TESTMISSED01', carrier: 'ups' });
    expect(asking).not.toHaveBeenCalled();
  });

  it('reads its demo parcels as their scenarios, through the apps\' client and the assistants\'', async () => {
    const stored = [row('TESTOUT01', 'swiss-post', 'IKEA'), row('TESTCUSTOMS01', 'dhl-express', 'Camera lens'),
      row('TESTDELIVERED01', 'planzer', 'Headphones'), row('TESTDELIVERED03', 'planzer', 'Desk lamp')];
    const read = async () => structuredClone(stored);
    const service = new SupabaseServiceClient('https://database.example', 'service-key');
    vi.spyOn(service, 'request').mockImplementation(read);
    const today = await listParcels(service, reviewer, { status: 'today' });
    expect(today.parcels.map(({ name, carrier, status }) => [name, carrier, status])).toEqual([['IKEA', 'Swiss Post', 'out_for_delivery']]);
    const attention = await listParcels(service, reviewer, { status: 'attention' });
    expect(attention.parcels).toMatchObject([{ name: 'Camera lens', needsAttention: 'customs_duties', expectedDelivery: null }]);
    const delivered = await listParcels(service, reviewer, { status: 'delivered', limit: 1 });
    expect(delivered).toMatchObject({ total: 2, parcels: [{ name: 'Headphones', status: 'delivered' }] });
    expect(await service.getOwnedPackage(String(stored[0]!.id), reviewer)).toMatchObject({ tracking_events: expect.arrayContaining([expect.objectContaining({ stage: 'out_for_delivery' })]) });

    const user = new SupabaseUserClient('https://database.example', 'public-key', 'token');
    vi.spyOn(user, 'request').mockImplementation(read);
    expect((await user.listPackages())[0]!.tracking_events).toEqual([]);
    user.ownerId = reviewer;
    expect(((await user.listPackages())[0]!.tracking_events as unknown[]).length).toBe(5);
    expect(((await user.getPackage(String(stored[0]!.id)))!.tracking_events as unknown[]).length).toBe(5);
    // Anyone else's parcels with such numbers read as stored.
    expect((await listParcels(service, reader, { status: 'all' })).parcels.every(({ status }) => status === 'pending')).toBe(true);
  });
});
