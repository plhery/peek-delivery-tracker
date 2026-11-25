import { afterEach, beforeEach, describe, expect, it, vi } from 'vitest';
import { FILE_MAX_BYTES, NoteFileError } from '../lib/parcelNote';
import { createDemoRepo } from './demoRepo';
import { DEMO_NOTE, DEMO_NOTE_PARCEL } from './demoNote';

const blob = (size: number, type = 'image/jpeg') => {
  const made = new Blob(['x'], { type });
  Object.defineProperty(made, 'size', { value: size });
  return made;
};

let made = 0;
beforeEach(() => {
  window.localStorage.clear();
  made = 0;
  vi.stubGlobal('URL', Object.assign(URL, {
    createObjectURL: vi.fn(() => `blob:http://localhost/demo-${++made}`),
    revokeObjectURL: vi.fn(),
  }));
});

afterEach(() => {
  vi.unstubAllGlobals();
});

describe("the demo's notes", () => {
  it('gives one sample a made-up note with a receipt and a photo, in the demo’s language', async () => {
    const repo = createDemoRepo(window.localStorage);
    const parcels = await repo.list();
    const noted = parcels.filter((parcel) => parcel.note || parcel.files);
    expect(noted.map((parcel) => parcel.trackingNumber)).toEqual([DEMO_NOTE_PARCEL]);
    const [record] = noted;
    expect(record.label).toBe('Kind of Blue · vinyl 🎷');
    expect(record.note).toBe(DEMO_NOTE.en);
    expect(record.files?.map((file) => [file.name, file.contentType])).toEqual([['Receipt.pdf', 'application/pdf'], ['Photo of the box.jpg', 'image/jpeg']]);
    // Nothing that looks like an account's address: the demo's files never reach a server.
    expect(record.files?.every((file) => !file.url.startsWith('/api/'))).toBe(true);

    repo.setLanguage!('fr');
    expect((await repo.list()).find((parcel) => parcel.id === record.id)?.note).toBe(DEMO_NOTE.fr);
    await repo.setNote!(record.id, 'Mon propre mot');
    repo.setLanguage!('de');
    expect((await repo.list()).find((parcel) => parcel.id === record.id)?.note).toBe('Mon propre mot');
  });

  it('saves, clips and deletes a note’s words', async () => {
    const repo = createDemoRepo(window.localStorage);
    const [parcel] = (await repo.list()).filter((candidate) => !candidate.note);
    expect((await repo.setNote!(parcel.id, `${'a'.repeat(1_005)}`)).note).toHaveLength(1_000);
    expect('note' in await repo.setNote!(parcel.id, '   ')).toBe(false);
    expect('note' in (await repo.list()).find((candidate) => candidate.id === parcel.id)!).toBe(false);
    await expect(repo.setNote!('missing', 'Hi')).rejects.toThrow('Parcel not found');
  });

  it('keeps added files for the page, with an account’s limits, and lets them go when removed', async () => {
    const repo = createDemoRepo(window.localStorage);
    const parcels = await repo.list();
    const record = parcels.find((parcel) => parcel.trackingNumber === DEMO_NOTE_PARCEL)!;
    const sent: number[] = [];
    const added = await repo.addFile!(record.id, blob(1_000), 'Door.jpg', (loaded) => sent.push(loaded));
    expect(sent).toEqual([1_000]);
    const door = added.files!.at(-1)!;
    expect(door).toMatchObject({ name: 'Door.jpg', contentType: 'image/jpeg', size: 1_000, url: expect.stringMatching(/^blob:/) });
    expect(door.thumbnailUrl).toBe(door.url);
    expect((await repo.list()).find((parcel) => parcel.id === record.id)?.files).toHaveLength(3);

    await expect(repo.addFile!(record.id, blob(FILE_MAX_BYTES + 1), 'Big.jpg')).rejects.toEqual(new NoteFileError('too-big', FILE_MAX_BYTES + 1));
    await repo.addFile!(record.id, blob(10, 'application/pdf'), 'Four.pdf');
    await repo.addFile!(record.id, blob(10, 'application/pdf'), 'Five.pdf');
    await expect(repo.addFile!(record.id, blob(10), 'Six.jpg')).rejects.toMatchObject({ problem: 'parcel-full' });

    await repo.removeFile!(record.id, door.id);
    expect(URL.revokeObjectURL).toHaveBeenCalledWith(door.url);
    expect((await repo.list()).find((parcel) => parcel.id === record.id)?.files?.map((file) => file.name))
      .toEqual(['Receipt.pdf', 'Photo of the box.jpg', 'Four.pdf', 'Five.pdf']);
  });

  it('forgets files another page added, whose addresses are gone', async () => {
    const repo = createDemoRepo(window.localStorage);
    const [parcel] = (await repo.list()).filter((candidate) => !candidate.files);
    const { files } = await repo.addFile!(parcel.id, blob(10), 'Door.jpg');
    expect(files).toHaveLength(1);
    await repo.removeFile!(parcel.id, 'not-this-one');
    // A new page of the demo: the address was this page's own.
    vi.resetModules();
    const { createDemoRepo: again } = await import('./demoRepo');
    expect((await again(window.localStorage).list()).find((candidate) => candidate.id === parcel.id)?.files).toBeUndefined();
  });

  it('keeps the note when the carrier changes', async () => {
    const repo = createDemoRepo(window.localStorage);
    const record = (await repo.list()).find((parcel) => parcel.trackingNumber === DEMO_NOTE_PARCEL)!;
    const changed = await repo.changeCarrier!(record.id, { carrier: 'dhl' });
    expect(changed.note).toBe(DEMO_NOTE.en);
    expect(changed.files).toHaveLength(2);
  });
});
