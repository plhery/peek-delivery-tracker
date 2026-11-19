import { renderHook, waitFor } from '@testing-library/react';
import { afterEach, describe, expect, it, vi } from 'vitest';
import { LINK_ID, OTHER_LINK_ID, OWNER_KEY, testView } from '../test/parcelLinks';
import type { ParcelLinkRead, ParcelLinkReadOptions } from './links';
import { archiveRecent, forgetAllRecents, freezeRecent, recentFor, rememberParcel, renameParcel, type RecentParcel } from './recents';
import { moveURL, parcelsInMove, receiveMove, takeMove, useMoveArrival, type MovedParcel } from './transfer';

const mocks = vi.hoisted(() => ({ read: vi.fn(), announce: vi.fn() }));
vi.mock('./links', async (original) => ({ ...await original<typeof import('./links')>(), readParcelLink: mocks.read }));
vi.mock('./parcel/Toast', () => ({ announceNotice: mocks.announce }));

const THIRD_ID = 'p4Rt6yUw8ZaB';
const OTHER_KEY = 'B'.repeat(43);
const readAs = (owners: Record<string, string>, unavailable: string[] = []) => vi.fn(async (id: string, { key }: ParcelLinkReadOptions): Promise<ParcelLinkRead> =>
  unavailable.includes(id) ? 'unavailable' : testView({ id, owner: !!key && owners[id] === key }));

afterEach(() => {
  forgetAllRecents();
  window.history.replaceState(null, '', '/');
  vi.restoreAllMocks();
});

describe('the address that moves the parcels on this device', () => {
  it('carries each link, its key, its fold and its name after the #, and the other device reads them back', () => {
    rememberParcel({ id: LINK_ID, key: OWNER_KEY, view: testView() });
    renameParcel(LINK_ID, 'Shoes & socks #2 + 50% off. Café');
    archiveRecent(LINK_ID, true);
    rememberParcel({ id: OTHER_LINK_ID, key: null, view: testView({ id: OTHER_LINK_ID, owner: false }) });
    const url = new URL(moveURL(onDevice(), 'https://peek.example'));
    expect(`${url.origin}${url.pathname}${url.search}`).toBe('https://peek.example/move');
    const moved = parcelsInMove(url.hash);
    expect(moved).toHaveLength(2);
    expect(moved).toEqual(expect.arrayContaining<MovedParcel>([
      { id: OTHER_LINK_ID, key: null, name: null, archived: false },
      { id: LINK_ID, key: OWNER_KEY, name: 'Shoes & socks #2 + 50% off. Café', archived: true },
    ]));
  });

  it('leaves out a copy the service forgot: its link is gone', () => {
    rememberParcel({ id: LINK_ID, key: OWNER_KEY, view: testView() });
    freezeRecent(LINK_ID);
    expect(parcelsInMove(new URL(moveURL(onDevice(), 'https://peek.example')).hash)).toEqual([]);
  });

  it('passes over a part that is not a parcel, and a key of the wrong shape', () => {
    expect(parcelsInMove(`#p=nope.${OWNER_KEY}..&p=${LINK_ID}.short..&x=1&p=${LINK_ID}.${OWNER_KEY}..`)).toEqual([
      { id: LINK_ID, key: null, name: null, archived: false },
    ]);
  });

  it('takes the parcels from the address it was opened with, and leaves nothing of them there', () => {
    window.history.replaceState({ kept: true }, '', `/move?utm=qr#p=${LINK_ID}.${OWNER_KEY}..Lamp`);
    expect(takeMove()).toEqual([{ id: LINK_ID, key: OWNER_KEY, name: 'Lamp', archived: false }]);
    expect(`${window.location.pathname}${window.location.search}${window.location.hash}`).toBe('/?utm=qr');
    expect(window.history.state).toEqual({ kept: true });
    window.history.replaceState(null, '', `/#p=${LINK_ID}.${OWNER_KEY}..`);
    expect(takeMove()).toEqual([]);
    expect(window.location.hash).not.toBe('');
  });
});

describe('receiving a move', () => {
  it('puts each parcel on the device as its link reads, keeping a key only when the link takes it as its owner’s', async () => {
    const read = readAs({ [LINK_ID]: OWNER_KEY });
    const outcome = await receiveMove([
      { id: LINK_ID, key: OWNER_KEY, name: 'Lamp', archived: true },
      { id: OTHER_LINK_ID, key: OTHER_KEY, name: null, archived: false },
      { id: THIRD_ID, key: null, name: null, archived: false },
    ], read);
    expect(outcome).toEqual({ added: 3, missed: 0, failed: 0 });
    expect(read).toHaveBeenCalledWith(LINK_ID, { key: OWNER_KEY, hidden: true });
    expect(recentFor(LINK_ID)).toMatchObject({ key: OWNER_KEY, name: 'Lamp', archivedAt: expect.any(String) });
    expect(recentFor(OTHER_LINK_ID)).toMatchObject({ key: null });
    expect(recentFor(THIRD_ID)).toMatchObject({ key: null, snapshot: { link: { role: 'viewer' } } });
  });

  it('passes over a parcel the service no longer keeps, and one a read could not reach', async () => {
    const read = vi.fn(async (id: string): Promise<ParcelLinkRead> => {
      if (id === OTHER_LINK_ID) throw new Error('offline');
      return 'unavailable';
    });
    expect(await receiveMove([
      { id: LINK_ID, key: OWNER_KEY, name: null, archived: false },
      { id: OTHER_LINK_ID, key: null, name: null, archived: false },
    ], read)).toEqual({ added: 0, missed: 1, failed: 1 });
    expect(recentFor(LINK_ID)).toBeNull();
  });

  it('leaves a parcel already here as it is, unless it gains the key it lacked', async () => {
    rememberParcel({ id: LINK_ID, key: OWNER_KEY, view: testView() });
    renameParcel(LINK_ID, 'Mine');
    rememberParcel({ id: OTHER_LINK_ID, key: null, view: testView({ id: OTHER_LINK_ID, owner: false }) });
    renameParcel(OTHER_LINK_ID, 'Kept name');
    const read = readAs({ [OTHER_LINK_ID]: OTHER_KEY });
    expect(await receiveMove([
      { id: LINK_ID, key: OTHER_KEY, name: 'Theirs', archived: true },
      { id: OTHER_LINK_ID, key: OTHER_KEY, name: 'New name', archived: true },
    ], read)).toEqual({ added: 1, missed: 0, failed: 0 });
    expect(read).toHaveBeenCalledOnce();
    expect(recentFor(LINK_ID)).toMatchObject({ key: OWNER_KEY, name: 'Mine' });
    expect(recentFor(LINK_ID)?.archivedAt).toBeUndefined();
    expect(recentFor(OTHER_LINK_ID)).toMatchObject({ key: OTHER_KEY, name: 'Kept name' });
    expect(recentFor(OTHER_LINK_ID)?.archivedAt).toBeUndefined();
  });
});

describe('a page opened with a move', () => {
  it('says how many parcels it added, that it could not reach them, or that Peek forgot them', async () => {
    window.history.replaceState(null, '', `/move#p=${LINK_ID}.${OWNER_KEY}..&p=${OTHER_LINK_ID}...`);
    mocks.read.mockImplementation(async (id: string) => id === LINK_ID ? testView() : 'unavailable');
    renderHook(() => useMoveArrival());
    await waitFor(() => expect(mocks.announce).toHaveBeenCalledWith('move.received.many', { count: 1 }, 'success'));
    expect(window.location.hash).toBe('');

    forgetAllRecents();
    mocks.announce.mockClear();
    window.history.replaceState(null, '', `/move#p=${LINK_ID}...`);
    mocks.read.mockRejectedValue(new Error('offline'));
    renderHook(() => useMoveArrival());
    await waitFor(() => expect(mocks.announce).toHaveBeenCalledWith('move.failed'));

    mocks.announce.mockClear();
    window.history.replaceState(null, '', `/move#p=${LINK_ID}...`);
    mocks.read.mockResolvedValue('unavailable');
    renderHook(() => useMoveArrival());
    await waitFor(() => expect(mocks.announce).toHaveBeenCalledWith('move.missed'));
  });
});

/** The parcels of these tests the device holds now. */
function onDevice(): RecentParcel[] {
  return [LINK_ID, OTHER_LINK_ID, THIRD_ID].map((id) => recentFor(id)).filter((recent) => recent !== null);
}
