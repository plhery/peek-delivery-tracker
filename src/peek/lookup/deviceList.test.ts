import { renderHook } from '@testing-library/react';
import { afterEach, beforeEach, describe, expect, it, vi } from 'vitest';
import { LINK_ID, OTHER_LINK_ID, OWNER_KEY, pendingView, testView } from '../../test/parcelLinks';
import type { EventPlace, ParcelWithEvents, Stage } from '../../types';
import { ParcelLinkError } from '../links';
import { forgetAllRecents, MAX_RECENTS, recentFor, RECENTS_STORAGE_KEY, rememberParcel, strandedParcels, useRecents, type RecentParcel } from '../recents';
import { forgetDeviceChecks, forgetDeviceParcels, leadParcel, watchDeviceParcels } from './deviceList';

const mocks = vi.hoisted(() => ({ read: vi.fn(), forget: vi.fn() }));
vi.mock('../links', async (original) => ({ ...await original<typeof import('../links')>(), readParcelLink: mocks.read, forgetParcelLink: mocks.forget }));

const THIRD_LINK_ID = 'p5Rt9wJq2LbX';
const zurich: EventPlace = { latitude: 47.38, longitude: 8.54, precision: 'city', country: 'CH', name: 'Zürich' };
/** The test parcels' scans are an hour apart from this morning on. */
const now = Date.parse('2026-10-01T14:00:00.000Z');
const HOUR = 3_600_000;

/** A parcel of the device. `placed` gives its scans a place, which a lead card needs to draw a route. */
function recent(id: string, stages: Stage[], { placed = true, parcel = {} }: { placed?: boolean; parcel?: Partial<ParcelWithEvents> } = {}): RecentParcel {
  const view = testView({ id, stages, parcel });
  if (placed) view.parcel.events = view.parcel.events.map((event) => ({ ...event, place: zurich }));
  return {
    id, key: null, name: null, carrier: view.parcel.carrier, stage: stages.at(-1) ?? null, syncStatus: view.parcel.syncStatus,
    expectedDelivery: view.parcel.expectedDelivery ?? null, updatedAt: null, lastSeenAt: new Date(now).toISOString(), snapshot: view,
  };
}

describe('leadParcel', () => {
  it('leads with the parcel the deliveries would call Next up, wherever it stands in the list', () => {
    const moving = recent(LINK_ID, ['accepted', 'in_transit']);
    const today = recent(OTHER_LINK_ID, ['accepted', 'out_for_delivery']);
    const waiting = recent(THIRD_LINK_ID, ['accepted', 'ready_for_pickup']);
    expect(leadParcel([moving, today], now)).toBe(today);
    expect(leadParcel([moving, today, waiting], now)).toBe(waiting);
  });

  it('only lets a parcel with a located scan lead', () => {
    const today = recent(LINK_ID, ['accepted', 'out_for_delivery'], { placed: false });
    const moving = recent(OTHER_LINK_ID, ['accepted', 'in_transit']);
    expect(leadParcel([today, moving], now)).toBe(moving);
    expect(leadParcel([today], now)).toBeNull();
    expect(leadParcel([], now)).toBeNull();
  });

  it('leads with a parcel that needs attention when nothing else is on its way', () => {
    const held = recent(LINK_ID, ['accepted', 'customs']);
    const arrived = recent(OTHER_LINK_ID, ['accepted', 'delivered']);
    expect(leadParcel([arrived, held], now)).toBe(held);
  });

  it('leads with a parcel that just arrived, for a day', () => {
    const arrived = recent(LINK_ID, ['accepted', 'delivered']);
    expect(leadParcel([arrived], now)).toBe(arrived);
    // A day after the journey ended the parcel rests among the others.
    expect(leadParcel([arrived], now + 25 * HOUR)).toBeNull();
    const moving = recent(OTHER_LINK_ID, ['accepted', 'in_transit']);
    expect(leadParcel([arrived, moving], now)).toBe(moving);
  });
});

describe('watchDeviceParcels', () => {
  const MINUTE = 60_000;
  const pass = (milliseconds: number) => vi.advanceTimersByTimeAsync(milliseconds);
  const watch = () => watchDeviceParcels(() => [recentFor(LINK_ID)].filter((recent) => recent !== null));
  let hidden = false;

  beforeEach(() => {
    vi.useFakeTimers({ toFake: ['setTimeout', 'clearTimeout', 'Date'] });
    hidden = false;
    vi.spyOn(document, 'hidden', 'get').mockImplementation(() => hidden);
    mocks.read.mockReset();
    mocks.read.mockResolvedValue(testView({ stages: ['registered', 'in_transit', 'out_for_delivery'] }));
  });
  afterEach(() => {
    vi.useRealTimers();
    vi.restoreAllMocks();
    forgetAllRecents();
    forgetDeviceChecks();
  });

  it('reads a parcel on its way again once its answer is stale, while the list stays open', async () => {
    rememberParcel({ id: LINK_ID, key: OWNER_KEY, view: testView(), now: Date.now() });
    const stop = watch();
    await pass(4 * MINUTE);
    expect(mocks.read).not.toHaveBeenCalled();
    await pass(MINUTE);
    expect(mocks.read).toHaveBeenCalledOnce();
    expect(mocks.read).toHaveBeenCalledWith(LINK_ID, { key: OWNER_KEY, signal: expect.any(AbortSignal) });
    expect(recentFor(LINK_ID)?.stage).toBe('out_for_delivery');
    stop();
  });

  it('waits while the list is out of sight, and reads at once when it is shown again', async () => {
    rememberParcel({ id: LINK_ID, key: OWNER_KEY, view: testView(), now: Date.now() });
    const stop = watch();
    hidden = true;
    await pass(10 * MINUTE);
    expect(mocks.read).not.toHaveBeenCalled();
    hidden = false;
    document.dispatchEvent(new Event('visibilitychange'));
    await pass(0);
    expect(mocks.read).toHaveBeenCalledOnce();
    stop();
  });

  it('keeps watching after a round that ran into trouble', async () => {
    rememberParcel({ id: LINK_ID, key: OWNER_KEY, view: testView(), now: Date.now() - 60 * MINUTE });
    mocks.read.mockRejectedValueOnce(new ParcelLinkError('offline'));
    const stop = watch();
    await pass(0);
    expect(mocks.read).toHaveBeenCalledOnce();
    expect(recentFor(LINK_ID)?.stage).toBe('in_transit');
    await pass(MINUTE);
    expect(mocks.read).toHaveBeenCalledTimes(2);
    expect(recentFor(LINK_ID)?.stage).toBe('out_for_delivery');
    stop();
  });

  it('keeps asking, every 10 s at most, about a lookup its carrier has not answered yet', async () => {
    rememberParcel({ id: LINK_ID, key: OWNER_KEY, view: pendingView(), now: Date.now() });
    mocks.read.mockResolvedValue(pendingView());
    const stop = watch();
    // After 2, 3, 4.5 and 6.75 s, then every 10 s.
    await pass(16_250);
    expect(mocks.read).toHaveBeenCalledTimes(4);
    await pass(MINUTE);
    expect(mocks.read).toHaveBeenCalledTimes(10);
    mocks.read.mockResolvedValue(testView());
    await pass(10_000);
    expect(recentFor(LINK_ID)).toMatchObject({ stage: 'in_transit', syncStatus: 'ok' });
    // Answered: the list is back to its pace, and the answer is fresh.
    await pass(MINUTE);
    expect(mocks.read).toHaveBeenCalledTimes(11);
    stop();
  });

  it('stops reading once stopped: no timer, no listener, and a read on its way is called off', async () => {
    rememberParcel({ id: LINK_ID, key: OWNER_KEY, view: testView(), now: Date.now() - 60 * MINUTE });
    // Like a fetch, the read gives up once called off.
    mocks.read.mockImplementationOnce((_id: string, { signal }: { signal: AbortSignal }) => new Promise((_resolve, reject) => {
      signal.addEventListener('abort', () => reject(new DOMException('Aborted', 'AbortError')));
    }));
    const listening = [vi.spyOn(document, 'addEventListener'), vi.spyOn(window, 'addEventListener')];
    const unlistening = [vi.spyOn(document, 'removeEventListener'), vi.spyOn(window, 'removeEventListener')];
    const stop = watch();
    await pass(0);
    expect(mocks.read).toHaveBeenCalledOnce();
    const [, { signal }] = mocks.read.mock.calls[0] as [string, { signal: AbortSignal }];
    stop();
    await pass(0);
    expect(signal.aborted).toBe(true);
    expect(vi.getTimerCount()).toBe(0);
    expect(unlistening.map((spy) => spy.mock.calls)).toEqual(listening.map((spy) => spy.mock.calls));
    document.dispatchEvent(new Event('visibilitychange'));
    window.dispatchEvent(new Event('focus'));
    window.dispatchEvent(new Event('online'));
    await pass(60 * MINUTE);
    expect(mocks.read).toHaveBeenCalledOnce();
  });
});

describe('forgetDeviceParcels', () => {
  const ALPHABET = '23456789ABCDEFGHJKLMNPQRSTUVWXYZ';
  const shared = (index: number) => `q7Qm2xHd9R${ALPHABET[Math.floor(index / 32)]}${ALPHABET[index % 32]}`;
  beforeEach(() => { mocks.forget.mockReset(); mocks.forget.mockResolvedValue(undefined); });
  afterEach(() => { forgetAllRecents(); forgetDeviceChecks(); });

  it('forgets on the server a lookup that twenty newer links did not push out, and one saved in a way it cannot read', async () => {
    rememberParcel({ id: LINK_ID, key: OWNER_KEY, view: testView(), now });
    for (let index = 0; index < MAX_RECENTS + 3; index += 1) {
      rememberParcel({ id: shared(index), view: testView({ id: shared(index), owner: false }), now: now + 1 + index });
    }
    // A parcel another version saved: its answer cannot be read here, its link and key can.
    const saved = JSON.parse(localStorage.getItem(RECENTS_STORAGE_KEY)!) as object[];
    localStorage.setItem(RECENTS_STORAGE_KEY, JSON.stringify([...saved, { id: THIRD_LINK_ID, key: OWNER_KEY, snapshot: null }]));
    const { result } = renderHook(() => useRecents());
    expect(result.current).toHaveLength(MAX_RECENTS);
    expect(strandedParcels()).toEqual([{ id: THIRD_LINK_ID, key: OWNER_KEY }]);

    await expect(forgetDeviceParcels(result.current)).resolves.toBe(0);
    expect(mocks.forget.mock.calls).toEqual([[LINK_ID, OWNER_KEY], [THIRD_LINK_ID, OWNER_KEY]]);
    expect(localStorage.getItem(RECENTS_STORAGE_KEY)).toBeNull();
  });

  it('keeps what the server could not forget', async () => {
    rememberParcel({ id: LINK_ID, key: OWNER_KEY, view: testView(), now });
    localStorage.setItem(RECENTS_STORAGE_KEY, JSON.stringify([
      ...JSON.parse(localStorage.getItem(RECENTS_STORAGE_KEY)!) as object[], { id: THIRD_LINK_ID, key: OWNER_KEY },
    ]));
    mocks.forget.mockImplementation(async (id: string) => { if (id === THIRD_LINK_ID) throw new ParcelLinkError('offline'); });
    await expect(forgetDeviceParcels([recentFor(LINK_ID)!])).resolves.toBe(1);
    expect(recentFor(LINK_ID)).toBeNull();
    expect(strandedParcels()).toEqual([{ id: THIRD_LINK_ID, key: OWNER_KEY }]);
  });
});
