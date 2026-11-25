import { act } from '@testing-library/react';
import { createElement } from 'react';
import { hydrateRoot } from 'react-dom/client';
import { renderToString } from 'react-dom/server';
import { afterEach, beforeEach, describe, expect, it, vi } from 'vitest';
import { FILE_MAX_BYTES, NoteFileError, type PreparedFile } from '../lib/parcelNote';
import { MemoryIndexedDB } from '../test/memoryIndexedDB';
import {
  addDeviceNoteFile, deviceNoteFileBlob, deviceNoteFiles, deviceNoteFilesOf, forgetAllDeviceNoteFiles, forgetDeviceNoteFiles,
  handOverDeviceNoteFiles, loadDeviceNoteFiles, removeDeviceNoteFile, resetDeviceNoteFilesForTests, sendKeptNoteFiles,
  useAllDeviceNoteFiles, type DeviceNoteFile,
} from './noteFiles';

let idb: MemoryIndexedDB;
const persist = vi.fn(async () => true);

/** A file of `size` bytes, as the sheet hands it over after preparing it. */
function picked(name: string, size = 4, type: PreparedFile['type'] = 'image/jpeg'): PreparedFile {
  return { blob: { size, type } as Blob, name, type };
}

async function problemOf(work: Promise<unknown>) {
  const error = await work.then(() => null, (failure: unknown) => failure);
  return error instanceof NoteFileError ? error.problem : error;
}

beforeEach(() => {
  resetDeviceNoteFilesForTests();
  idb = new MemoryIndexedDB();
  vi.stubGlobal('indexedDB', idb);
  Object.defineProperty(navigator, 'storage', { configurable: true, value: { persist } });
  persist.mockClear();
});

afterEach(() => {
  resetDeviceNoteFilesForTests();
  vi.unstubAllGlobals();
  Reflect.deleteProperty(navigator, 'storage');
});

describe("a guest's note files", () => {
  it('keeps each file with its link in IndexedDB, and asks the browser to keep them on the first', async () => {
    const box = picked('Box.jpg', 120);
    const kept = await addDeviceNoteFile('link-1', box, Date.parse('2026-11-25T10:00:00Z'));
    expect(kept).toEqual({ id: expect.any(String), linkId: 'link-1', name: 'Box.jpg', type: 'image/jpeg', size: 120, created: '2026-11-25T10:00:00.000Z' });
    expect(persist).toHaveBeenCalledTimes(1);
    await addDeviceNoteFile('link-1', picked('Receipt.pdf', 80, 'application/pdf'), Date.parse('2026-11-25T10:01:00Z'));
    expect(persist).toHaveBeenCalledTimes(1);

    expect(idb.rows('peek-parcel-files', 'files')).toEqual([
      expect.objectContaining({ linkId: 'link-1', name: 'Box.jpg', blob: box.blob }),
      expect.objectContaining({ linkId: 'link-1', name: 'Receipt.pdf', type: 'application/pdf' }),
    ]);
    expect(await deviceNoteFileBlob(kept.id)).toBe(box.blob);
    expect(deviceNoteFilesOf(deviceNoteFiles(), 'link-1').map((file) => file.name)).toEqual(['Box.jpg', 'Receipt.pdf']);
    expect(deviceNoteFilesOf(deviceNoteFiles(), 'link-2')).toEqual([]);

    // A new page reads them back, oldest first, without their bytes.
    resetDeviceNoteFilesForTests();
    await loadDeviceNoteFiles();
    expect(deviceNoteFiles().map((file) => [file.name, 'blob' in file])).toEqual([['Box.jpg', false], ['Receipt.pdf', false]]);
  });

  it('holds an account’s limits: 10 MB a file, five a parcel, 200 MB for the device', async () => {
    expect(await problemOf(addDeviceNoteFile('link-1', picked('Big.jpg', FILE_MAX_BYTES + 1)))).toBe('too-big');
    for (let index = 0; index < 5; index++) await addDeviceNoteFile('link-1', picked(`Photo ${index}.jpg`));
    expect(await problemOf(addDeviceNoteFile('link-1', picked('Sixth.jpg')))).toBe('parcel-full');

    // 200 MB already on the device: another parcel's 10 MB doesn't fit.
    for (let parcel = 2; parcel < 6; parcel++) {
      for (let index = 0; index < 5; index++) await addDeviceNoteFile(`link-${parcel}`, picked('Scan.pdf', 10_000_000, 'application/pdf'));
    }
    expect(await problemOf(addDeviceNoteFile('link-9', picked('Door.jpg', 10_000_000)))).toBe('device-full');
    expect(deviceNoteFilesOf(deviceNoteFiles(), 'link-9')).toEqual([]);
  });

  it("says the device is full when the browser's quota is, and when it keeps no database", async () => {
    idb.failWrites = new DOMException('Quota', 'QuotaExceededError');
    expect(await problemOf(addDeviceNoteFile('link-1', picked('Box.jpg')))).toBe('device-full');
    expect(deviceNoteFiles()).toEqual([]);

    idb.failWrites = new DOMException('Broken', 'UnknownError');
    expect(await problemOf(addDeviceNoteFile('link-1', picked('Box.jpg')))).toBe('failed');

    resetDeviceNoteFilesForTests();
    idb.refuseOpen = true;
    expect(await problemOf(addDeviceNoteFile('link-1', picked('Box.jpg')))).toBe('device-full');
    expect(await deviceNoteFileBlob('anything')).toBeNull();
  });

  it('deletes a forgotten parcel’s files, and all of them with the list, but keeps those on their way to an account', async () => {
    const first = await addDeviceNoteFile('link-1', picked('One.jpg'));
    await addDeviceNoteFile('link-2', picked('Two.jpg'));
    await addDeviceNoteFile('link-3', picked('Three.jpg'));
    await removeDeviceNoteFile(first.id);
    expect(deviceNoteFiles().map((file) => file.name)).toEqual(['Two.jpg', 'Three.jpg']);

    expect(await handOverDeviceNoteFiles('link-3', 'package-3')).toBe(1);
    expect(await handOverDeviceNoteFiles('link-3', 'package-3')).toBe(0);
    await forgetDeviceNoteFiles('link-2');
    await forgetDeviceNoteFiles('link-3');
    expect(deviceNoteFiles()).toEqual([expect.objectContaining({ name: 'Three.jpg', packageId: 'package-3' })]);
    // The parcel's page no longer shows the handed-over file.
    expect(deviceNoteFilesOf(deviceNoteFiles(), 'link-3')).toEqual([]);
    expect(idb.rows('peek-parcel-files', 'files')).toEqual([expect.objectContaining({ name: 'Three.jpg', packageId: 'package-3' })]);

    await addDeviceNoteFile('link-4', picked('Four.jpg'));
    await forgetAllDeviceNoteFiles();
    expect(deviceNoteFiles().map((file) => file.name)).toEqual(['Three.jpg']);
  });

  it('sends handed-over files one at a time, deletes each once it arrived, and keeps a failure for the next visit', async () => {
    await addDeviceNoteFile('link-1', picked('Box.jpg', 10));
    await addDeviceNoteFile('link-1', picked('Receipt.pdf', 20, 'application/pdf'));
    await addDeviceNoteFile('link-2', picked('Door.jpg', 30));
    await handOverDeviceNoteFiles('link-1', 'package-1');

    const send = vi.fn(async (_packageId: string, _blob: Blob, name: string) => {
      if (name === 'Receipt.pdf') throw new Error('offline');
    });
    expect(await sendKeptNoteFiles(send)).toEqual({ sent: 1, left: 1 });
    expect(send.mock.calls.map(([packageId, blob, name]) => [packageId, blob.size, name])).toEqual([['package-1', 10, 'Box.jpg'], ['package-1', 20, 'Receipt.pdf']]);
    expect(deviceNoteFiles().map((file) => file.name)).toEqual(['Receipt.pdf', 'Door.jpg']);

    // The next visit sends what stayed; the guest's own files never leave.
    send.mockClear();
    send.mockResolvedValue(undefined);
    resetDeviceNoteFilesForTests();
    expect(await sendKeptNoteFiles(send)).toEqual({ sent: 1, left: 0 });
    expect(send.mock.calls.map(([, , name]) => name)).toEqual(['Receipt.pdf']);
    expect(deviceNoteFiles().map((file) => file.name)).toEqual(['Door.jpg']);
  });

  it('joins a send already on its way, and sends what was handed over meanwhile', async () => {
    await addDeviceNoteFile('link-1', picked('Box.jpg'));
    await handOverDeviceNoteFiles('link-1', 'package-1');
    let release!: () => void;
    const send = vi.fn<(packageId: string, blob: Blob, name: string) => Promise<void>>()
      .mockImplementationOnce(() => new Promise<void>((resolve) => { release = resolve; }))
      .mockResolvedValue(undefined);
    const first = sendKeptNoteFiles(send);
    await vi.waitFor(() => expect(send).toHaveBeenCalledTimes(1));
    await addDeviceNoteFile('link-2', picked('Door.jpg'));
    await handOverDeviceNoteFiles('link-2', 'package-2');
    const second = sendKeptNoteFiles(send);
    expect(second).toBe(first);
    release();
    expect(await first).toEqual({ sent: 2, left: 0 });
    expect(send.mock.calls.map(([packageId, , name]) => [packageId, name])).toEqual([['package-1', 'Box.jpg'], ['package-2', 'Door.jpg']]);
    expect(deviceNoteFiles()).toEqual([]);
  });

  it('lets a page the server drew take the device’s files once it is live, without a word from React', async () => {
    await addDeviceNoteFile('link-1', picked('Box.jpg'));
    resetDeviceNoteFilesForTests();
    const complaints = vi.spyOn(console, 'error');
    let seen: readonly DeviceNoteFile[] = [];
    function Probe() {
      seen = useAllDeviceNoteFiles();
      return createElement('span', null, seen.length);
    }
    const container = document.createElement('div');
    container.innerHTML = renderToString(createElement(Probe));
    expect(container).toHaveTextContent('0');
    document.body.append(container);
    const root = await act(async () => hydrateRoot(container, createElement(Probe)));
    await vi.waitFor(() => expect(seen.map((file) => file.name)).toEqual(['Box.jpg']));
    expect(complaints).not.toHaveBeenCalled();
    act(() => root.unmount());
    container.remove();
    complaints.mockRestore();
  });
});
