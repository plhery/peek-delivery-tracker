import { afterEach, describe, expect, it, vi } from 'vitest';
import en from '../../shared/locales/en.json';
import { translateMessage, type MessageKey, type Messages } from './messages';
import {
  clipNote, FILE_MAX_BYTES, fileProblemLine, hasNote, NOTE_MAX_LENGTH, NoteFileError, noteFileName, noteLength, notePieces,
  noteSearchText, prepareNoteFile, retriable, uploadProblem, withoutFile,
} from './parcelNote';
import type { ParcelFile } from '../types';

const t = (key: MessageKey, variables?: Record<string, string | number>) => translateMessage('en', key, variables, en as Messages);
const file = (id: string, name = `${id}.jpg`): ParcelFile => ({
  id, name, contentType: 'image/jpeg', size: 10, createdAt: '2026-11-25T10:00:00Z', url: `/api/files/${id}`, thumbnailUrl: `/api/files/${id}?v=thumb`, expiresAt: '2026-11-25T11:00:00Z',
});

afterEach(() => {
  vi.unstubAllGlobals();
});

describe('a note’s words', () => {
  it('counts code points, as the server does, and cuts at the limit without splitting a character', () => {
    expect(noteLength('Box 📦')).toBe(5);
    const long = `${'a'.repeat(NOTE_MAX_LENGTH - 1)}📦📦`;
    expect(noteLength(clipNote(long))).toBe(NOTE_MAX_LENGTH);
    expect(clipNote(long).endsWith('📦')).toBe(true);
    expect(clipNote('short')).toBe('short');
  });

  it('has a note with words or a file, not with blank text', () => {
    expect(hasNote({ note: '  ' })).toBe(false);
    expect(hasNote({ note: 'Door code 4812' })).toBe(true);
    expect(hasNote({ files: [file('one')] })).toBe(true);
    expect(hasNote({ files: [] })).toBe(false);
  });

  it('drops a file, and the list with its last one', () => {
    expect(withoutFile({ files: [file('one'), file('two')] }, 'one').files?.map((kept) => kept.id)).toEqual(['two']);
    expect(withoutFile({ note: 'Hi', files: [file('one')] }, 'one')).toEqual({ note: 'Hi' });
  });

  it('is searched by its words and its files’ names', () => {
    expect(noteSearchText('Door code', ['Receipt.pdf'])).toBe('Door code Receipt.pdf');
    expect(noteSearchText(undefined)).toBe('');
  });
});

describe('links in a note', () => {
  it('makes web and e-mail addresses into links and leaves the rest as it is', () => {
    expect(notePieces('Exchange at little-forest.example/exchange, or write to help@shop.example.')).toEqual([
      { text: 'Exchange at ' },
      { text: 'little-forest.example/exchange', href: 'https://little-forest.example/exchange' },
      { text: ', or write to ' },
      { text: 'help@shop.example', href: 'mailto:help@shop.example' },
      { text: '.' },
    ]);
    expect(notePieces('See (https://shop.example/a_(b)) and www.peek.example!')).toEqual([
      { text: 'See (' },
      { text: 'https://shop.example/a_(b)', href: 'https://shop.example/a_(b)' },
      { text: ') and ' },
      { text: 'www.peek.example', href: 'https://www.peek.example' },
      { text: '!' },
    ]);
  });

  it('leaves names that only look like addresses: a file name, a decimal, an unknown ending', () => {
    expect(notePieces('Scan.jpg weighs 1.2 kg, see label.v2')).toEqual([{ text: 'Scan.jpg weighs 1.2 kg, see label.v2' }]);
  });
});

describe('a file’s problems', () => {
  it('reads the server’s refusals', () => {
    expect(uploadProblem(413)).toBe('too-big');
    expect(uploadProblem(400, 'file_too_big')).toBe('too-big');
    expect(uploadProblem(415)).toBe('type');
    expect(uploadProblem(409, 'parcel_files_full')).toBe('parcel-full');
    expect(uploadProblem(409, 'account_files_full')).toBe('account-full');
    expect(uploadProblem(500)).toBe('failed');
    expect(retriable('failed')).toBe(true);
    expect(retriable('offline')).toBe(true);
    expect(retriable('too-big')).toBe(false);
  });

  it('says each in one plain line', () => {
    expect(fileProblemLine('too-big', 'Return label.pdf', 24 * 1_048_576, t, 'en')).toBe('Return label.pdf is 24 MB. Files can be up to 10 MB.');
    expect(fileProblemLine('too-big', 'Box.jpg', 10.6 * 1_048_576, t, 'en')).toBe('Box.jpg is 10.6 MB. Files can be up to 10 MB.');
    expect(fileProblemLine('parcel-full', 'Box.jpg', undefined, t, 'en')).toBe('A parcel holds up to 5 photos or files.');
    expect(fileProblemLine('type', 'Song.mp3', undefined, t, 'en')).toBe('Peek takes photos and PDFs.');
    expect(fileProblemLine('account-full', 'Box.jpg', undefined, t, 'en')).toBe('Your 200 MB for photos and files is full. Remove some to add more.');
    expect(fileProblemLine('device-full', 'Box.jpg', undefined, t, 'en')).toBe('This device has no room left for photos and files.');
    expect(fileProblemLine('failed', 'Receipt.jpg', undefined, t, 'en')).toBe('Couldn’t add Receipt.jpg.');
  });
});

describe('a file’s name', () => {
  it('keeps its extension when the type stays, and follows a conversion', () => {
    expect(noteFileName('Receipt.PDF', 'application/pdf')).toBe('Receipt.pdf');
    expect(noteFileName('IMG_0042.HEIC', 'image/jpeg')).toBe('IMG_0042.jpg');
    expect(noteFileName('Door.jpeg', 'image/jpeg')).toBe('Door.jpeg');
    expect(noteFileName('  Door\u0000code  .png', 'image/png')).toBe('Door code.png');
    expect(noteFileName('.png', 'image/png')).toBe('File.png');
    expect(noteFileName('', 'image/webp')).toBe('File.webp');
  });

  it('fits in 120 characters with its extension', () => {
    const name = noteFileName(`${'é'.repeat(200)}.pdf`, 'application/pdf');
    expect(Array.from(name)).toHaveLength(120);
    expect(name.endsWith('é.pdf')).toBe(true);
  });
});

describe('readying a chosen file', () => {
  const chosen = (name: string, type: string, size = 100) => new File([new Uint8Array(size)], name, { type });

  it('sends a PDF as it is, and refuses one over 10 MB or another kind of file', async () => {
    const pdf = chosen('Receipt.pdf', 'application/pdf');
    await expect(prepareNoteFile(pdf)).resolves.toEqual({ blob: pdf, name: 'Receipt.pdf', type: 'application/pdf' });
    const big = chosen('Scan.pdf', 'application/pdf', FILE_MAX_BYTES + 1);
    await expect(prepareNoteFile(big)).rejects.toEqual(new NoteFileError('too-big', FILE_MAX_BYTES + 1));
    await expect(prepareNoteFile(chosen('Song.mp3', 'audio/mpeg'))).rejects.toMatchObject({ problem: 'type' });
  });

  it('knows a photo by its extension when the browser names no type', async () => {
    const photo = chosen('Door.JPG', '');
    await expect(prepareNoteFile(photo)).resolves.toEqual({ blob: photo, name: 'Door.jpg', type: 'image/jpeg' });
  });

  it('sends a photo it cannot read as it is, but not a HEIC one', async () => {
    const png = chosen('Screen.png', 'image/png');
    await expect(prepareNoteFile(png)).resolves.toMatchObject({ blob: png, type: 'image/png' });
    await expect(prepareNoteFile(chosen('IMG_0042.heic', ''))).rejects.toMatchObject({ problem: 'unreadable' });
  });

  it('makes a large photo fit 2,560 px as a JPEG, and converts a HEIC one', async () => {
    const drawn: number[][] = [];
    vi.stubGlobal('createImageBitmap', vi.fn(async () => ({ width: 4032, height: 3024, close: vi.fn() })));
    vi.stubGlobal('OffscreenCanvas', class {
      constructor(readonly width: number, readonly height: number) { drawn.push([width, height]); }
      getContext() { return { fillRect: vi.fn(), drawImage: vi.fn(), fillStyle: '' }; }
      async convertToBlob(options: { type: string; quality: number }) { return new Blob([`${options.type} ${options.quality}`], { type: options.type }); }
    });
    const large = await prepareNoteFile(chosen('Box.jpg', 'image/jpeg'));
    expect(large).toMatchObject({ name: 'Box.jpg', type: 'image/jpeg' });
    expect(await large.blob.text()).toBe('image/jpeg 0.85');
    expect(drawn).toEqual([[2560, 1920]]);
    await expect(prepareNoteFile(chosen('IMG_0042.HEIC', 'image/heic'))).resolves.toMatchObject({ name: 'IMG_0042.jpg', type: 'image/jpeg' });
    // A large screenshot stays a PNG while it fits.
    await expect(prepareNoteFile(chosen('Screen.png', 'image/png'))).resolves.toMatchObject({ name: 'Screen.png', type: 'image/png' });
  });
});
