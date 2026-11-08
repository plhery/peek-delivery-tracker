import { useSyncExternalStore } from 'react';
import { currentEvent } from '../lib/stages';
import type { CarrierId, Stage, SyncStatus } from '../types';
import { forgetAllLinkNotes, forgetLinkNote } from './deviceNotes';
import { isParcelLinkId, type ParcelLinkView } from './linkModel';

/**
 * "On this device": the parcels this browser looked up or opened through a
 * link. Their names and owner keys live here and nowhere else.
 */
export const RECENTS_STORAGE_KEY = 'sdt.peek.parcels.v1'; // gitleaks:allow -- public localStorage name
/** The list keeps twenty parcels; links opened from someone else make way first. */
export const MAX_RECENTS = 20;
/**
 * A lookup of this device's own stays beyond twenty, up to sixty: its owner key
 * is the only way to forget the parcel or keep it in an account.
 */
export const MAX_OWNED = 60;
/** The longest name a parcel can carry into an account. */
export const MAX_NAME_LENGTH = 80;
const eventName = 'peek-recents-change';

export interface RecentParcel {
  id: string;
  /** The owner key, when this device made the lookup. */
  key: string | null;
  /** The name this device gave the parcel. */
  name: string | null;
  carrier: CarrierId;
  /** What a list row needs for its headline, without opening the snapshot. */
  stage: Stage | null;
  syncStatus: SyncStatus;
  expectedDelivery: string | null;
  /** When the carrier last reported something. */
  updatedAt: string | null;
  /** When this device last got an answer for the parcel. */
  lastSeenAt: string;
  /** The last answer, shown while offline. */
  snapshot: ParcelLinkView;
}

/**
 * A saved parcel this version cannot read, such as one another version wrote,
 * that still has its link and owner key. It is kept as it was saved, never
 * listed, and forgotten with the others.
 */
export interface StrandedParcel {
  id: string;
  key: string;
}

type Stored = StrandedParcel & Record<string, unknown>;

const EMPTY: RecentParcel[] = [];
const NONE: Stored[] = [];
// Storage that cannot be read or written leaves the list working for as long as the page lives.
let memory: string | null = null;
let storageFailed = false;
let cached: { raw: string | null; list: RecentParcel[]; stranded: Stored[] } = { raw: null, list: EMPTY, stranded: NONE };

function isRecent(value: unknown): value is RecentParcel {
  if (!value || typeof value !== 'object') return false;
  const recent = value as Partial<RecentParcel>;
  return isParcelLinkId(recent.id) && typeof recent.carrier === 'string' && typeof recent.lastSeenAt === 'string'
    && (recent.key === null || typeof recent.key === 'string') && (recent.name === null || typeof recent.name === 'string')
    && !!recent.snapshot && typeof recent.snapshot === 'object'
    && !!recent.snapshot.link && !!recent.snapshot.parcel && Array.isArray(recent.snapshot.parcel.events);
}

function stored(): string | null {
  if (storageFailed) return memory;
  try { return window.localStorage.getItem(RECENTS_STORAGE_KEY); } catch { return memory; }
}

function isStranded(value: unknown): value is Stored {
  if (!value || typeof value !== 'object') return false;
  const { id, key } = value as Partial<StrandedParcel>;
  return isParcelLinkId(id) && typeof key === 'string' && key.length > 0;
}

/** The owner keys a list that is no longer JSON still shows: every parcel is saved with its id and key first. */
function keysIn(raw: string): Stored[] {
  return [...raw.matchAll(/"id":"([^"\\]+)","key":"([A-Za-z0-9_-]+)"/g)]
    .map(([, id, key]) => ({ id, key }))
    .filter((found) => isParcelLinkId(found.id));
}

function load(): typeof cached {
  const raw = stored();
  if (raw === cached.raw) return cached;
  let list = EMPTY;
  let stranded = NONE;
  try {
    const value: unknown = raw ? JSON.parse(raw) : null;
    if (Array.isArray(value)) {
      list = value.filter(isRecent);
      stranded = value.filter((entry) => !isRecent(entry) && isStranded(entry));
    }
  } catch {
    // A damaged list lists nothing, and keeps the owner keys it still shows.
    stranded = raw ? keysIn(raw) : NONE;
  }
  cached = { raw, list, stranded };
  return cached;
}

function read(): RecentParcel[] {
  return load().list;
}

function write(list: RecentParcel[], stranded: readonly Stored[] = load().stranded) {
  const ordered = [...list].sort((a, b) => b.lastSeenAt.localeCompare(a.lastSeenAt));
  const owned = ordered.filter((recent) => recent.key).slice(0, MAX_OWNED);
  const viewed = ordered.filter((recent) => !recent.key).slice(0, Math.max(0, MAX_RECENTS - owned.length));
  const kept = new Set([...owned, ...viewed]);
  const listed = new Set([...kept].map((recent) => recent.id));
  const saved: object[] = [
    ...ordered.filter((recent) => kept.has(recent)),
    ...stranded.filter((entry, index) => !listed.has(entry.id) && stranded.findIndex((other) => other.id === entry.id) === index).slice(0, MAX_OWNED),
  ];
  memory = saved.length ? JSON.stringify(saved) : null;
  try {
    if (memory) window.localStorage.setItem(RECENTS_STORAGE_KEY, memory);
    else window.localStorage.removeItem(RECENTS_STORAGE_KEY);
    storageFailed = false;
  } catch {
    storageFailed = true;
  }
  if (typeof window !== 'undefined') window.dispatchEvent(new Event(eventName));
}

function subscribe(notify: () => void) {
  const onStorage = (event: StorageEvent) => {
    if (event.key !== null && event.key !== RECENTS_STORAGE_KEY) return;
    // Another tab's list replaces this page's copy.
    storageFailed = false;
    notify();
  };
  window.addEventListener(eventName, notify);
  window.addEventListener('storage', onStorage);
  return () => {
    window.removeEventListener(eventName, notify);
    window.removeEventListener('storage', onStorage);
  };
}

/** A name as a parcel can keep it: trimmed, without control characters, at most 80 characters. */
export function cleanParcelName(value: string | null | undefined): string | null {
  const name = [...(value ?? '').replace(/[\p{Cc}\p{Cf}]/gu, ' ').replace(/\s+/g, ' ').trim()]
    .slice(0, MAX_NAME_LENGTH).join('').trim();
  return name || null;
}

/** The parcels of this device, newest first. */
export function useRecents(): RecentParcel[] {
  return useSyncExternalStore(subscribe, read, () => EMPTY);
}

export function recentFor(id: string): RecentParcel | null {
  return read().find((recent) => recent.id === id) ?? null;
}

/** The parcels this device holds the owner key of but cannot list. */
export function strandedParcels(): StrandedParcel[] {
  return load().stranded.map(({ id, key }) => ({ id, key }));
}

/** The owner key this device holds for a link, listed or not. */
export function ownerKeyFor(id: string): string | null {
  const { list, stranded } = load();
  return list.find((recent) => recent.id === id)?.key ?? stranded.find((entry) => entry.id === id)?.key ?? null;
}

/**
 * Saves the latest answer for a parcel. A key or a name the device already
 * has stays; `suggestedName` (the name a shared link carries) is taken only
 * when the device has none.
 */
export function rememberParcel({ id, view, key, name, suggestedName, now = Date.now() }: {
  id: string;
  view: ParcelLinkView;
  key?: string | null;
  name?: string | null;
  suggestedName?: string | null;
  now?: number;
}): void {
  if (!isParcelLinkId(id)) return;
  const { list, stranded } = load();
  const existing = list.find((recent) => recent.id === id);
  const current = currentEvent(view.parcel.events);
  const recent: RecentParcel = {
    id,
    // A parcel saved in a way this version could not read gets its key back.
    key: key ?? existing?.key ?? stranded.find((entry) => entry.id === id)?.key ?? null,
    name: (name !== undefined ? cleanParcelName(name) : existing?.name) ?? cleanParcelName(suggestedName),
    carrier: view.parcel.carrier,
    stage: current?.stage ?? null,
    syncStatus: view.parcel.syncStatus,
    expectedDelivery: view.parcel.expectedDelivery ?? null,
    updatedAt: current?.occurredAt ?? null,
    lastSeenAt: new Date(now).toISOString(),
    snapshot: view,
  };
  write([recent, ...list.filter((candidate) => candidate.id !== id)], stranded.filter((entry) => entry.id !== id));
}

/** Names a parcel on this device; an empty name removes it. */
export function renameParcel(id: string, name: string | null): void {
  const list = read();
  if (!list.some((recent) => recent.id === id)) return;
  write(list.map((recent) => recent.id === id ? { ...recent, name: cleanParcelName(name) } : recent));
}

/** Drops the device's copy, with what it noted about the link. The server's is forgotten with `forgetParcelLink`. */
export function forgetRecent(id: string): void {
  const { list, stranded } = load();
  if (list.some((recent) => recent.id === id) || stranded.some((entry) => entry.id === id)) {
    write(list.filter((recent) => recent.id !== id), stranded.filter((entry) => entry.id !== id));
  }
  forgetLinkNote(id);
}

export function forgetAllRecents(): void {
  write([], []);
  forgetAllLinkNotes();
}
