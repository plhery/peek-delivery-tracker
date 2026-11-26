import { useEffect, useSyncExternalStore } from 'react';
import { FILE_MAX_BYTES, FILES_PER_PARCEL, FILES_TOTAL_BYTES, NoteFileError, type PreparedFile } from '../lib/parcelNote';
import type { ParcelFileType } from '../types';

/**
 * The photos and PDFs of a guest's notes, kept in this browser's IndexedDB beside the note's words in `recents.ts`: a
 * Blob per file with its name, type, size, link and when it was added. Kept with a parcel in an account, each one is
 * marked with the account's parcel, sent there, and deleted here once it arrived; one that failed waits for the next
 * visit, and one the account had no room for waits a day.
 */
const DATABASE = 'peek-parcel-files';
const STORE = 'files';

export interface DeviceNoteFile {
  id: string;
  linkId: string;
  name: string;
  type: ParcelFileType;
  size: number;
  created: string;
  /** The account's parcel it goes to, once the parcel was kept there. */
  packageId?: string;
  /** When the account last had no room for it (ms): it is offered again a day later. */
  refusedAt?: number;
}

interface StoredFile extends DeviceNoteFile { blob: Blob }

let files: readonly DeviceNoteFile[] = [];
let loading: Promise<void> | null = null;
const listeners = new Set<() => void>();

function publish(next: readonly DeviceNoteFile[]) {
  files = next;
  for (const listener of [...listeners]) listener();
}

const asPromise = <T>(request: IDBRequest<T>) => new Promise<T>((resolve, reject) => {
  request.onsuccess = () => resolve(request.result);
  request.onerror = () => reject(request.error);
});

const finished = (transaction: IDBTransaction) => new Promise<void>((resolve, reject) => {
  transaction.oncomplete = () => resolve();
  transaction.onerror = () => reject(transaction.error);
  transaction.onabort = () => reject(transaction.error ?? new DOMException('The write was stopped', 'AbortError'));
});

let opening: Promise<IDBDatabase | null> | null = null;
/** The database, or null where the browser keeps none (a private window in some browsers). */
function database(): Promise<IDBDatabase | null> {
  opening ??= new Promise((resolve) => {
    try {
      if (typeof indexedDB === 'undefined') { resolve(null); return; }
      const request = indexedDB.open(DATABASE, 1);
      request.onupgradeneeded = () => {
        if (!request.result.objectStoreNames.contains(STORE)) request.result.createObjectStore(STORE, { keyPath: 'id' });
      };
      request.onsuccess = () => resolve(request.result);
      request.onerror = () => resolve(null);
      request.onblocked = () => resolve(null);
    } catch {
      resolve(null);
    }
  });
  return opening;
}

/** What a stored file is, without its bytes. */
function described({ id, linkId, name, type, size, created, packageId, refusedAt }: StoredFile): DeviceNoteFile {
  return { id, linkId, name, type, size, created, ...(packageId ? { packageId } : {}), ...(refusedAt ? { refusedAt } : {}) };
}

/** Reads the device's files once per page; later calls wait for that read. */
export function loadDeviceNoteFiles(): Promise<void> {
  loading ??= (async () => {
    const db = await database();
    if (!db) return;
    try {
      const stored = await asPromise(db.transaction(STORE).objectStore(STORE).getAll() as IDBRequest<StoredFile[]>);
      publish(stored.map(described).sort((first, second) => first.created.localeCompare(second.created)));
    } catch {
      // Unreadable: the notes show no files rather than failing.
    }
  })();
  return loading;
}

function subscribe(listener: () => void) {
  listeners.add(listener);
  return () => { listeners.delete(listener); };
}
const snapshot = () => files;
/** The server's answer is the same empty list every time, as React asks of it. */
const NO_FILES: readonly DeviceNoteFile[] = [];
const serverSnapshot = () => NO_FILES;

/** Every file kept on this device, for the list's marks and its search. */
export function useAllDeviceNoteFiles(): readonly DeviceNoteFile[] {
  useEffect(() => { void loadDeviceNoteFiles(); }, []);
  return useSyncExternalStore(subscribe, snapshot, serverSnapshot);
}

/** Every file kept on this device, as far as the page has read them. */
export const deviceNoteFiles = (): readonly DeviceNoteFile[] => files;

/** The files of one parcel's note on this device, oldest first. */
export function deviceNoteFilesOf(all: readonly DeviceNoteFile[], linkId: string): DeviceNoteFile[] {
  return all.filter((file) => file.linkId === linkId && !file.packageId);
}

const newId = () => typeof crypto !== 'undefined' && typeof crypto.randomUUID === 'function'
  ? crypto.randomUUID() : `${Date.now().toString(36)}-${Math.random().toString(36).slice(2)}`;

const quotaError = (error: unknown) => error instanceof DOMException && (error.name === 'QuotaExceededError' || error.code === 22);

/**
 * Keeps a file with a parcel's note on this device, within the same limits as an account: five per parcel, 10 MB
 * each, and 200 MB for the browser, or less where the browser has less room. The first one asks the browser to keep
 * them through a clean-up.
 */
export async function addDeviceNoteFile(linkId: string, file: PreparedFile, now = Date.now()): Promise<DeviceNoteFile> {
  await loadDeviceNoteFiles();
  if (file.blob.size > FILE_MAX_BYTES) throw new NoteFileError('too-big', file.blob.size);
  if (deviceNoteFilesOf(files, linkId).length >= FILES_PER_PARCEL) throw new NoteFileError('parcel-full');
  const total = files.reduce((sum, kept) => sum + kept.size, 0);
  if (total + file.blob.size > FILES_TOTAL_BYTES) throw new NoteFileError('device-full');
  const db = await database();
  if (!db) throw new NoteFileError('device-full');
  if (!files.length) void navigator.storage?.persist?.().catch(() => false);
  const record: StoredFile = {
    id: newId(), linkId, name: file.name, type: file.type, size: file.blob.size, created: new Date(now).toISOString(), blob: file.blob,
  };
  try {
    const transaction = db.transaction(STORE, 'readwrite');
    transaction.objectStore(STORE).put(record);
    await finished(transaction);
  } catch (error) {
    if (quotaError(error) || quotaError((error as { cause?: unknown })?.cause)) throw new NoteFileError('device-full');
    throw new NoteFileError('failed');
  }
  const kept = described(record);
  publish([...files, kept]);
  return kept;
}

/** A file's bytes, to show, share or send it. */
export async function deviceNoteFileBlob(id: string): Promise<Blob | null> {
  const db = await database();
  if (!db) return null;
  try {
    const stored = await asPromise(db.transaction(STORE).objectStore(STORE).get(id) as IDBRequest<StoredFile | undefined>);
    return stored?.blob ?? null;
  } catch {
    return null;
  }
}

async function deleteStored(ids: readonly string[]): Promise<void> {
  if (!ids.length) return;
  const gone = new Set(ids);
  publish(files.filter((file) => !gone.has(file.id)));
  const db = await database();
  if (!db) return;
  const transaction = db.transaction(STORE, 'readwrite');
  for (const id of ids) transaction.objectStore(STORE).delete(id);
  await finished(transaction).catch(() => undefined);
}

export function removeDeviceNoteFile(id: string): Promise<void> {
  return deleteStored([id]);
}

/** Deletes a forgotten parcel's files; those on their way to an account stay until they arrive. */
export async function forgetDeviceNoteFiles(linkId: string): Promise<void> {
  await loadDeviceNoteFiles();
  await deleteStored(files.filter((file) => file.linkId === linkId && !file.packageId).map((file) => file.id));
}

/** Deletes every file of the device's notes, but those on their way to an account. */
export async function forgetAllDeviceNoteFiles(): Promise<void> {
  await loadDeviceNoteFiles();
  await deleteStored(files.filter((file) => !file.packageId).map((file) => file.id));
}

/** Changes a link's files, here and in the database; returns how many. */
async function relabel(linkId: string, change: Partial<Pick<DeviceNoteFile, 'linkId' | 'packageId'>>): Promise<number> {
  await loadDeviceNoteFiles();
  const changed = files.filter((file) => file.linkId === linkId && !file.packageId);
  if (!changed.length) return 0;
  const ids = new Set(changed.map((file) => file.id));
  publish(files.map((file) => ids.has(file.id) ? { ...file, ...change } : file));
  const db = await database();
  if (!db) return changed.length;
  const transaction = db.transaction(STORE, 'readwrite');
  const store = transaction.objectStore(STORE);
  for (const file of changed) {
    const request = store.get(file.id) as IDBRequest<StoredFile | undefined>;
    request.onsuccess = () => { if (request.result) store.put({ ...request.result, ...change }); };
  }
  await finished(transaction).catch(() => undefined);
  return changed.length;
}

/** Marks a parcel's files for the account's parcel it was kept as: they are sent there, and forgetting the link keeps them. */
export function handOverDeviceNoteFiles(linkId: string, packageId: string): Promise<number> {
  return relabel(linkId, { packageId });
}

/** Moves a frozen copy's files to the new lookup of the same number that takes its place. */
export function moveDeviceNoteFiles(fromLinkId: string, toLinkId: string): Promise<number> {
  return relabel(fromLinkId, { linkId: toLinkId });
}

/**
 * Sends one file to the account's parcel; resolves too when the file can never go there (the parcel has gone, or the
 * service refuses its size or type), and the file leaves the device. A 409 `NoteFileError` says the account had no room.
 */
export type SendNoteFile = (packageId: string, blob: Blob, name: string) => Promise<void>;

/** How long a file the account had no room for waits before it is offered again. */
export const REFUSED_WAIT_MS = 24 * 60 * 60 * 1000;

/** Notes when the account last had no room for a file, here and in the database. */
async function markRefused(id: string, at: number): Promise<void> {
  publish(files.map((file) => file.id === id ? { ...file, refusedAt: at } : file));
  const db = await database();
  if (!db) return;
  const transaction = db.transaction(STORE, 'readwrite');
  const store = transaction.objectStore(STORE);
  const request = store.get(id) as IDBRequest<StoredFile | undefined>;
  request.onsuccess = () => { if (request.result) store.put({ ...request.result, refusedAt: at }); };
  await finished(transaction).catch(() => undefined);
}

/** How many files reached the account, and how many wait for the next visit. */
export interface SentFiles { sent: number; left: number }

async function sendOnce(send: SendNoteFile, now: () => number): Promise<SentFiles> {
  await loadDeviceNoteFiles();
  const sent: SentFiles = { sent: 0, left: 0 };
  for (const file of files.filter((candidate) => candidate.packageId)) {
    // A file the account had no room for isn't sent again, ten megabytes at a time, on every visit.
    if (file.refusedAt && now() - file.refusedAt < REFUSED_WAIT_MS) {
      sent.left += 1;
      continue;
    }
    try {
      const blob = await deviceNoteFileBlob(file.id);
      if (blob) await send(file.packageId!, blob, file.name);
      await deleteStored([file.id]);
      if (blob) sent.sent += 1;
    } catch (error) {
      if (error instanceof NoteFileError && error.status === 409) await markRefused(file.id, now());
      sent.left += 1;
    }
  }
  return sent;
}

let sending: Promise<SentFiles> | null = null;
let again = false;

/**
 * Sends the files of parcels kept in the account, one at a time, deleting each from the device once it arrived. One
 * that fails stays for the next visit; one the account had no room for, for a visit a day later. A call while files
 * are on their way joins them, and what was handed over meanwhile goes next.
 */
export function sendKeptNoteFiles(send: SendNoteFile, now: () => number = Date.now): Promise<SentFiles> {
  if (sending) {
    again = true;
    return sending;
  }
  sending = (async () => {
    const total: SentFiles = { sent: 0, left: 0 };
    do {
      again = false;
      const pass = await sendOnce(send, now);
      total.sent += pass.sent;
      total.left = pass.left;
    } while (again);
    return total;
  })().finally(() => { sending = null; });
  return sending;
}

/** Forgets the page's copy of the list, for tests. */
export function resetDeviceNoteFilesForTests(): void {
  files = [];
  loading = null;
  opening = null;
  sending = null;
  again = false;
  listeners.clear();
}
