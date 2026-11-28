import { afterEach, beforeEach, describe, expect, it, vi } from 'vitest';
import type { PreparedFile } from '../lib/parcelNote';
import { MemoryIndexedDB } from '../test/memoryIndexedDB';
import { LINK_ID, OTHER_LINK_ID, OWNER_KEY, testView } from '../test/parcelLinks';
import type { ParcelRepo, ParcelWithEvents } from '../types';
import { deviceExport } from './lookup/deviceExport';
import { handOverNoteFiles, moveNoteIntoDemo, sendKeptFiles } from './keptNotes';
import { addDeviceNoteFile, deviceNoteFiles, deviceNoteFilesOf, loadDeviceNoteFiles, resetDeviceNoteFilesForTests } from './noteFiles';
import {
  forgetAllRecents, forgetRecent, freezeRecent, HALF_TYPED_MS, halfTypedLookups, noteParcel, recentFor, rememberParcel,
} from './recents';
import { moveURL, parcelsInMove } from './transfer';

const NOW = Date.parse('2026-10-02T08:00:00Z');
const picked = (name: string, size = 4): PreparedFile => ({ blob: new Blob(['x'.repeat(size)], { type: 'image/jpeg' }), name, type: 'image/jpeg' });
const auth = { userId: 'user-1', getAccessToken: async () => 'token' };
/** Lets the deletes the list starts in the background land. */
const settle = () => new Promise((resolve) => setTimeout(resolve, 20));

class FakeUpload {
  static sent: { path: string; name: string }[] = [];
  static status = 201;
  status = 0;
  responseText = '';
  timeout = 0;
  private path = '';
  private headers: Record<string, string> = {};
  private listeners = new Map<string, () => void>();
  upload = { addEventListener: () => undefined };
  open(_method: string, path: string) { this.path = path; }
  setRequestHeader(name: string, value: string) { this.headers[name] = value; }
  addEventListener(type: string, listener: () => void) { this.listeners.set(type, listener); }
  abort() {}
  send() {
    FakeUpload.sent.push({ path: this.path, name: decodeURIComponent(this.headers['X-File-Name']) });
    setTimeout(() => {
      this.status = FakeUpload.status;
      this.responseText = JSON.stringify({ error: 'x' });
      this.listeners.get('load')?.();
    }, 0);
  }
}

let idb: MemoryIndexedDB;
beforeEach(() => {
  resetDeviceNoteFilesForTests();
  idb = new MemoryIndexedDB();
  vi.stubGlobal('indexedDB', idb);
  FakeUpload.sent = [];
  FakeUpload.status = 201;
  vi.stubGlobal('XMLHttpRequest', FakeUpload);
});

afterEach(async () => {
  forgetAllRecents();
  await settle();
  resetDeviceNoteFilesForTests();
  vi.unstubAllGlobals();
});

describe("a guest's note", () => {
  it('keeps its words beside the link, trimmed and within 1,000 characters, and blank text removes them', () => {
    rememberParcel({ id: LINK_ID, key: OWNER_KEY, view: testView(), now: NOW });
    noteParcel(LINK_ID, '  Door code 4812  ');
    expect(recentFor(LINK_ID)?.note).toBe('Door code 4812');
    // Later answers keep it.
    rememberParcel({ id: LINK_ID, view: testView(), now: NOW + 1 });
    expect(recentFor(LINK_ID)?.note).toBe('Door code 4812');
    noteParcel(LINK_ID, 'a'.repeat(1_200));
    expect(recentFor(LINK_ID)?.note).toHaveLength(1_000);
    noteParcel(LINK_ID, '  ');
    expect(recentFor(LINK_ID)).not.toHaveProperty('note');
    // A parcel the device doesn't have gets none.
    noteParcel(OTHER_LINK_ID, 'Hi');
    expect(recentFor(OTHER_LINK_ID)).toBeNull();
  });

  it('goes with the parcel when it is forgotten, files and all, and stays with a frozen copy', async () => {
    rememberParcel({ id: LINK_ID, key: OWNER_KEY, view: testView(), now: NOW });
    rememberParcel({ id: OTHER_LINK_ID, key: OWNER_KEY, view: testView({ id: OTHER_LINK_ID, parcel: { trackingNumber: '1234567898' } }), now: NOW });
    noteParcel(LINK_ID, 'Door code');
    await addDeviceNoteFile(LINK_ID, picked('Box.jpg'));
    await addDeviceNoteFile(OTHER_LINK_ID, picked('Door.jpg'));

    freezeRecent(LINK_ID);
    expect(recentFor(LINK_ID)?.note).toBe('Door code');
    await settle();
    expect(deviceNoteFilesOf(deviceNoteFiles(), LINK_ID)).toHaveLength(1);

    forgetRecent(LINK_ID);
    await settle();
    expect(deviceNoteFiles().map((file) => file.name)).toEqual(['Door.jpg']);
    expect(idb.rows('peek-parcel-files', 'files').map((row) => row.name)).toEqual(['Door.jpg']);
    forgetAllRecents();
    await settle();
    expect(deviceNoteFiles()).toEqual([]);
  });

  it('moves to a new lookup of the same number that takes a frozen copy’s place', async () => {
    rememberParcel({ id: LINK_ID, key: OWNER_KEY, view: testView(), now: NOW });
    noteParcel(LINK_ID, 'Door code');
    await addDeviceNoteFile(LINK_ID, picked('Box.jpg'));
    freezeRecent(LINK_ID);
    rememberParcel({ id: OTHER_LINK_ID, key: OWNER_KEY, view: testView({ id: OTHER_LINK_ID }), now: NOW + 1 });
    expect(recentFor(LINK_ID)).toBeNull();
    expect(recentFor(OTHER_LINK_ID)?.note).toBe('Door code');
    await settle();
    expect(deviceNoteFilesOf(deviceNoteFiles(), OTHER_LINK_ID).map((file) => file.name)).toEqual(['Box.jpg']);
  });

  it('keeps a lookup with a note or a file from being taken for a number half typed', async () => {
    const view = testView({ id: OTHER_LINK_ID, parcel: { trackingNumber: '1234567', syncStatus: 'pending' }, stages: ['pending'] });
    const halfTyped = { ...view, link: { ...view.link, createdAt: new Date(NOW - 60_000).toISOString() } };
    rememberParcel({ id: OTHER_LINK_ID, key: OWNER_KEY, view: halfTyped, now: NOW });
    expect(halfTypedLookups('1234567899', NOW)).toHaveLength(1);
    noteParcel(OTHER_LINK_ID, 'Door code');
    expect(halfTypedLookups('1234567899', NOW)).toEqual([]);
    noteParcel(OTHER_LINK_ID, null);
    await addDeviceNoteFile(OTHER_LINK_ID, picked('Box.jpg'));
    expect(halfTypedLookups('1234567899', NOW)).toEqual([]);
    expect(halfTypedLookups('1234567899', NOW + HALF_TYPED_MS + 1)).toEqual([]);
  });

  it('never travels in a move to another device', () => {
    rememberParcel({ id: LINK_ID, key: OWNER_KEY, view: testView(), name: 'Shoes', now: NOW });
    noteParcel(LINK_ID, 'Door code 4812');
    const url = moveURL([recentFor(LINK_ID)!], 'https://peek.example');
    expect(url).not.toContain('Door');
    expect(parcelsInMove(new URL(url).hash)).toEqual([{ id: LINK_ID, key: OWNER_KEY, name: 'Shoes', archived: false }]);
  });

  it('is in the list’s download with its files’ names, never their bytes', async () => {
    rememberParcel({ id: LINK_ID, key: OWNER_KEY, view: testView(), now: NOW });
    noteParcel(LINK_ID, 'Door code 4812');
    const kept = await addDeviceNoteFile(LINK_ID, picked('Box.jpg', 12), NOW);
    const [parcel] = deviceExport([recentFor(LINK_ID)!], new Date(NOW), 'https://peek.example', [kept]).packages;
    expect(parcel).toMatchObject({ note: 'Door code 4812', files: [{ name: 'Box.jpg', type: 'image/jpeg', size: 12, added: '2026-10-02T08:00:00.000Z' }] });
    expect(JSON.stringify(parcel)).not.toContain(OWNER_KEY);
  });
});

describe("a guest's note kept in an account", () => {
  it('sends the files to the account’s parcel, then lets them go from the device; one that fails waits', async () => {
    rememberParcel({ id: LINK_ID, key: OWNER_KEY, view: testView(), now: NOW });
    await addDeviceNoteFile(LINK_ID, picked('Box.jpg'));
    await addDeviceNoteFile(LINK_ID, picked('Receipt.jpg'));
    FakeUpload.status = 500;
    await handOverNoteFiles(LINK_ID, 'package-1', auth);
    // Forgetting the link, as keeping it does, keeps the files on their way.
    forgetRecent(LINK_ID);
    await vi.waitFor(() => expect(FakeUpload.sent).toHaveLength(2));
    await settle();
    expect(deviceNoteFiles()).toHaveLength(2);

    FakeUpload.sent = [];
    FakeUpload.status = 201;
    resetDeviceNoteFilesForTests();
    expect(await sendKeptFiles(auth)).toEqual({ sent: 2, left: 0 });
    expect(FakeUpload.sent).toEqual([
      { path: '/api/packages/package-1/files', name: 'Box.jpg' },
      { path: '/api/packages/package-1/files', name: 'Receipt.jpg' },
    ]);
    expect(deviceNoteFiles()).toEqual([]);
  });

  it('drops a file whose parcel has left the account', async () => {
    await addDeviceNoteFile(LINK_ID, picked('Box.jpg'));
    await handOverNoteFiles(LINK_ID, undefined, auth);
    expect(deviceNoteFiles()[0].packageId).toBeUndefined();
    FakeUpload.status = 404;
    await loadDeviceNoteFiles();
    await handOverNoteFiles(LINK_ID, 'package-gone', auth);
    await vi.waitFor(() => expect(deviceNoteFiles()).toEqual([]));
  });

  it.each([413, 415])('drops a file the service will never take (%i)', async (status) => {
    await addDeviceNoteFile(LINK_ID, picked('Box.jpg'));
    FakeUpload.status = status;
    await handOverNoteFiles(LINK_ID, 'package-1', auth);
    await vi.waitFor(() => expect(deviceNoteFiles()).toEqual([]));
  });

  it('keeps a file the account has no room for', async () => {
    await addDeviceNoteFile(LINK_ID, picked('Box.jpg'));
    FakeUpload.status = 409;
    await handOverNoteFiles(LINK_ID, 'package-1', auth);
    await vi.waitFor(() => expect(deviceNoteFiles()[0]?.refusedAt).toBeGreaterThan(0));
    expect(deviceNoteFiles()).toHaveLength(1);
  });

  it('leaves a file to the account that kept its parcel when another signs in', async () => {
    await addDeviceNoteFile(LINK_ID, picked('Box.jpg'));
    FakeUpload.status = 500;
    await handOverNoteFiles(LINK_ID, 'package-1', auth);
    await vi.waitFor(() => expect(FakeUpload.sent).toHaveLength(1));
    await settle();

    // Another account: the file isn't sent to its account, where the parcel isn't, and isn't dropped for it.
    FakeUpload.sent = [];
    FakeUpload.status = 404;
    expect(await sendKeptFiles({ ...auth, userId: 'user-2' })).toEqual({ sent: 0, left: 0 });
    expect(FakeUpload.sent).toEqual([]);
    expect(deviceNoteFiles()).toHaveLength(1);

    FakeUpload.status = 201;
    expect(await sendKeptFiles(auth)).toEqual({ sent: 1, left: 0 });
    expect(deviceNoteFiles()).toEqual([]);
  });

  it('moves into the demo’s parcel, words and files', async () => {
    await addDeviceNoteFile(LINK_ID, picked('Box.jpg'));
    const added: string[] = [];
    const repo = {
      setNote: vi.fn(async () => ({}) as ParcelWithEvents),
      addFile: vi.fn(async (_id: string, _blob: Blob, name: string) => { added.push(name); return {} as ParcelWithEvents; }),
    } as unknown as ParcelRepo;
    await moveNoteIntoDemo(repo, LINK_ID, 'demo-1', 'Door code');
    expect(repo.setNote).toHaveBeenCalledWith('demo-1', 'Door code');
    expect(added).toEqual(['Box.jpg']);
    expect(deviceNoteFiles()).toEqual([]);
  });
});
