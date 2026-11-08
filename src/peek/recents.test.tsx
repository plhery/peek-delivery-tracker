import { act, renderHook } from '@testing-library/react';
import { afterEach, describe, expect, it, vi } from 'vitest';
import {
  cleanParcelName,
  forgetAllRecents,
  forgetRecent,
  MAX_OWNED,
  MAX_RECENTS,
  ownerKeyFor,
  recentFor,
  RECENTS_STORAGE_KEY,
  rememberParcel,
  renameParcel,
  strandedParcels,
  useRecents,
} from './recents';
import { LINK_ID, OTHER_LINK_ID, OWNER_KEY, testView } from '../test/parcelLinks';

const NOW = Date.parse('2026-10-02T08:00:00Z');
const ALPHABET = '23456789ABCDEFGHJKLMNPQRSTUVWXYZ';
const id = (index: number) => `k7Qm2xHd9R${ALPHABET[Math.floor(index / 32)]}${ALPHABET[index % 32]}`;

afterEach(() => { forgetAllRecents(); vi.restoreAllMocks(); });

describe('the parcels of this device', () => {
  it('remembers a parcel with its key, its headline and the last answer', () => {
    rememberParcel({ id: LINK_ID, key: OWNER_KEY, view: testView({ parcel: { expectedDelivery: '2026-10-03' } }), now: NOW });
    expect(recentFor(LINK_ID)).toEqual({
      id: LINK_ID, key: OWNER_KEY, name: null, carrier: 'dhl', stage: 'in_transit', syncStatus: 'ok',
      expectedDelivery: '2026-10-03', updatedAt: '2026-10-01T10:00:00.000Z', lastSeenAt: '2026-10-02T08:00:00.000Z',
      snapshot: testView({ parcel: { expectedDelivery: '2026-10-03' } }),
    });
    expect(JSON.parse(localStorage.getItem(RECENTS_STORAGE_KEY)!)).toHaveLength(1);
    expect(recentFor(OTHER_LINK_ID)).toBeNull();
  });

  it('keeps the key and the name through later answers, and takes a link’s name only when it has none', () => {
    rememberParcel({ id: LINK_ID, key: OWNER_KEY, view: testView(), now: NOW });
    rememberParcel({ id: LINK_ID, view: testView({ stages: ['registered', 'in_transit', 'delivered'] }), suggestedName: '  From   Ada ', now: NOW + 1 });
    expect(recentFor(LINK_ID)).toMatchObject({ key: OWNER_KEY, name: 'From Ada', stage: 'delivered' });
    rememberParcel({ id: LINK_ID, view: testView(), suggestedName: 'Another name', now: NOW + 2 });
    expect(recentFor(LINK_ID)!.name).toBe('From Ada');
    renameParcel(LINK_ID, 'New sneakers');
    rememberParcel({ id: LINK_ID, view: testView(), suggestedName: 'Another name', now: NOW + 3 });
    expect(recentFor(LINK_ID)).toMatchObject({ key: OWNER_KEY, name: 'New sneakers' });
    renameParcel(LINK_ID, '   ');
    expect(recentFor(LINK_ID)!.name).toBeNull();
    renameParcel(OTHER_LINK_ID, 'Nothing to rename');
    expect(recentFor(OTHER_LINK_ID)).toBeNull();
  });

  it('cleans names: no control characters, at most 80 characters', () => {
    expect(cleanParcelName('  a\u0000b​c \n d ')).toBe('a b c d');
    expect([...cleanParcelName('🎁'.repeat(90))!]).toHaveLength(80);
    expect(cleanParcelName('')).toBeNull();
    expect(cleanParcelName(null)).toBeNull();
  });

  it('lists the newest first and keeps twenty', () => {
    for (let index = 0; index < MAX_RECENTS + 3; index += 1) {
      rememberParcel({ id: id(index), view: testView({ id: id(index) }), now: NOW + index });
    }
    const { result } = renderHook(() => useRecents());
    expect(result.current).toHaveLength(MAX_RECENTS);
    expect(result.current[0].id).toBe(id(MAX_RECENTS + 2));
    expect(result.current.at(-1)!.id).toBe(id(3));
    act(() => rememberParcel({ id: id(5), view: testView({ id: id(5) }), now: NOW + 100 }));
    expect(result.current[0].id).toBe(id(5));
    act(() => forgetRecent(id(5)));
    expect(result.current.some((recent) => recent.id === id(5))).toBe(false);
    act(() => forgetAllRecents());
    expect(result.current).toEqual([]);
    expect(localStorage.getItem(RECENTS_STORAGE_KEY)).toBeNull();
  });

  it('keeps its own lookups when newer links push the list past twenty: links opened from someone else make way', () => {
    rememberParcel({ id: LINK_ID, key: OWNER_KEY, view: testView(), name: 'Coffee beans', now: NOW });
    for (let index = 0; index < MAX_RECENTS + 5; index += 1) {
      rememberParcel({ id: id(index), view: testView({ id: id(index), owner: false }), now: NOW + 1 + index });
    }
    const { result } = renderHook(() => useRecents());
    expect(result.current).toHaveLength(MAX_RECENTS);
    expect(recentFor(LINK_ID)).toMatchObject({ key: OWNER_KEY, name: 'Coffee beans' });
    expect(result.current.at(-1)!.id).toBe(LINK_ID);
    // The oldest links opened from someone else went: nineteen of them stay beside the lookup.
    expect(result.current.filter((recent) => !recent.key).map((recent) => recent.id)).toEqual(
      Array.from({ length: MAX_RECENTS - 1 }, (_, index) => id(MAX_RECENTS + 4 - index)),
    );
  });

  it('keeps up to sixty lookups of its own, and no link opened from someone else once they fill twenty', () => {
    rememberParcel({ id: OTHER_LINK_ID, view: testView({ id: OTHER_LINK_ID, owner: false }), now: NOW + 1_000 });
    for (let index = 0; index < MAX_OWNED + 2; index += 1) {
      rememberParcel({ id: id(index), key: OWNER_KEY, view: testView({ id: id(index) }), now: NOW + index });
    }
    const { result } = renderHook(() => useRecents());
    expect(result.current).toHaveLength(MAX_OWNED);
    expect(result.current.every((recent) => recent.key === OWNER_KEY)).toBe(true);
    expect(result.current.at(-1)!.id).toBe(id(2));
    expect(recentFor(OTHER_LINK_ID)).toBeNull();
  });

  it('keeps the owner key of a parcel saved in a way it cannot read, and gives it back when the link answers again', () => {
    const unreadable = { ...validRecent(), id: LINK_ID, key: OWNER_KEY, name: 'Coffee beans', snapshot: { version: 2 } };
    localStorage.setItem(RECENTS_STORAGE_KEY, JSON.stringify([unreadable, validRecent()]));
    const { result } = renderHook(() => useRecents());
    expect(result.current.map((recent) => recent.id)).toEqual([OTHER_LINK_ID]);
    expect(strandedParcels()).toEqual([{ id: LINK_ID, key: OWNER_KEY }]);
    expect(ownerKeyFor(LINK_ID)).toBe(OWNER_KEY);
    // Another parcel's answer is saved: the unreadable one stays as it was.
    act(() => renameParcel(OTHER_LINK_ID, 'Shoes'));
    expect(JSON.parse(localStorage.getItem(RECENTS_STORAGE_KEY)!)).toContainEqual(unreadable);
    // Its link answers: the parcel is listed again, with its key.
    act(() => rememberParcel({ id: LINK_ID, view: testView(), now: NOW }));
    expect(recentFor(LINK_ID)).toMatchObject({ key: OWNER_KEY });
    expect(strandedParcels()).toEqual([]);
    expect(JSON.parse(localStorage.getItem(RECENTS_STORAGE_KEY)!)).toHaveLength(2);
  });

  it('keeps the owner keys a list that is no longer JSON still shows, until the parcel is forgotten', () => {
    rememberParcel({ id: LINK_ID, key: OWNER_KEY, view: testView(), now: NOW });
    rememberParcel({ id: OTHER_LINK_ID, view: testView({ id: OTHER_LINK_ID, owner: false }), now: NOW + 1 });
    localStorage.setItem(RECENTS_STORAGE_KEY, localStorage.getItem(RECENTS_STORAGE_KEY)!.slice(0, -40));
    expect(recentFor(LINK_ID)).toBeNull();
    expect(strandedParcels()).toEqual([{ id: LINK_ID, key: OWNER_KEY }]);
    rememberParcel({ id: id(1), view: testView({ id: id(1), owner: false }), now: NOW + 2 });
    expect(ownerKeyFor(LINK_ID)).toBe(OWNER_KEY);
    forgetRecent(LINK_ID);
    expect(ownerKeyFor(LINK_ID)).toBeNull();
    expect(JSON.parse(localStorage.getItem(RECENTS_STORAGE_KEY)!).map((recent: { id: string }) => recent.id)).toEqual([id(1)]);
  });

  it('returns the same list while nothing changes, and follows another tab', () => {
    rememberParcel({ id: LINK_ID, view: testView(), now: NOW });
    const { result, rerender } = renderHook(() => useRecents());
    const first = result.current;
    rerender();
    expect(result.current).toBe(first);
    const other = [{ ...first[0], id: OTHER_LINK_ID, snapshot: testView({ id: OTHER_LINK_ID }) }];
    act(() => {
      localStorage.setItem(RECENTS_STORAGE_KEY, JSON.stringify(other));
      window.dispatchEvent(new StorageEvent('storage', { key: RECENTS_STORAGE_KEY }));
    });
    expect(result.current.map((recent) => recent.id)).toEqual([OTHER_LINK_ID]);
    act(() => { window.dispatchEvent(new StorageEvent('storage', { key: 'something.else' })); });
    expect(result.current.map((recent) => recent.id)).toEqual([OTHER_LINK_ID]);
  });

  it('ignores what it cannot read and ids that are not links', () => {
    localStorage.setItem(RECENTS_STORAGE_KEY, '{broken');
    expect(recentFor(LINK_ID)).toBeNull();
    localStorage.setItem(RECENTS_STORAGE_KEY, JSON.stringify([{ id: LINK_ID }, 'x', null, { ...validRecent(), id: 'nope' }, validRecent()]));
    const { result } = renderHook(() => useRecents());
    expect(result.current.map((recent) => recent.id)).toEqual([OTHER_LINK_ID]);
    rememberParcel({ id: 'not-a-link', view: testView(), now: NOW });
    expect(recentFor('not-a-link')).toBeNull();
  });

  it('keeps working in memory when storage refuses, without throwing', () => {
    const denied = () => { throw new DOMException('denied', 'SecurityError'); };
    vi.spyOn(window.localStorage, 'getItem').mockImplementation(denied);
    vi.spyOn(window.localStorage, 'setItem').mockImplementation(denied);
    vi.spyOn(window.localStorage, 'removeItem').mockImplementation(denied);
    const { result } = renderHook(() => useRecents());
    expect(result.current).toEqual([]);
    act(() => rememberParcel({ id: LINK_ID, key: OWNER_KEY, view: testView(), now: NOW }));
    expect(result.current.map((recent) => recent.id)).toEqual([LINK_ID]);
    act(() => renameParcel(LINK_ID, 'Kept in memory'));
    expect(recentFor(LINK_ID)).toMatchObject({ key: OWNER_KEY, name: 'Kept in memory' });
    act(() => forgetRecent(LINK_ID));
    expect(result.current).toEqual([]);
    act(() => forgetAllRecents());
  });
});

function validRecent() {
  return {
    id: OTHER_LINK_ID, key: null, name: null, carrier: 'dhl', stage: 'in_transit', syncStatus: 'ok',
    expectedDelivery: null, updatedAt: null, lastSeenAt: '2026-10-02T08:00:00.000Z', snapshot: testView({ id: OTHER_LINK_ID }),
  };
}
