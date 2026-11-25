import { afterEach, beforeEach, describe, expect, it, vi } from 'vitest';
import {
  assistantNote,
  fileAddressExpiry,
  hasNote,
  servedFile,
  signedFileAddress,
  validFileSignature,
  withExportedNote,
  withOwnerNote,
} from './parcelNotes';

// Synthetic identifiers only.
const owner = '7a000000-0000-4000-a000-000000000001';
const stranger = '7a000000-0000-4000-a000-000000000002';
const photoId = '7a000000-0000-4000-b000-000000000001';
const pdfId = '7a000000-0000-4000-b000-000000000002';
const NOW = Date.parse('2026-11-25T10:07:00Z');

const signature = (address: string) => new URL(address, 'https://peek.example').searchParams.get('s')!;
const expiry = (address: string) => Number(new URL(address, 'https://peek.example').searchParams.get('e'));

beforeEach(() => { vi.stubEnv('SUPABASE_SERVICE_ROLE_KEY', 'synthetic-service-key'); });
afterEach(() => { vi.unstubAllEnvs(); });

describe('a file\'s signed address', () => {
  it('stays the same through a quarter hour and lasts until the next one has passed', () => {
    const expires = fileAddressExpiry(NOW);
    expect(new Date(expires * 1_000).toISOString()).toBe('2026-11-25T10:30:00.000Z');
    expect(fileAddressExpiry(Date.parse('2026-11-25T10:14:59Z'))).toBe(expires);
    expect(fileAddressExpiry(Date.parse('2026-11-25T10:15:01Z'))).toBe(expires + 900);
    expect(signedFileAddress(photoId, owner, 'full', expires)).toBe(signedFileAddress(photoId, owner, 'full', fileAddressExpiry(NOW + 60_000)));
  });

  it('is read for its file, owner and size, until it expires', () => {
    const expires = fileAddressExpiry(NOW);
    const address = signedFileAddress(photoId, owner, 'full', expires)!;
    expect(address).toMatch(new RegExp(`^/api/files/${photoId}\\?v=full&e=${expires}&s=[A-Za-z0-9_-]{43}$`));
    const given = signature(address);
    expect(validFileSignature(photoId, owner, 'full', expires, given, NOW)).toBe(true);
    expect(validFileSignature(photoId.toUpperCase(), owner, 'full', expires, given, NOW)).toBe(true);
    // Another file, owner, size or expiry is another address.
    expect(validFileSignature(pdfId, owner, 'full', expires, given, NOW)).toBe(false);
    expect(validFileSignature(photoId, stranger, 'full', expires, given, NOW)).toBe(false);
    expect(validFileSignature(photoId, owner, 'thumb', expires, given, NOW)).toBe(false);
    expect(validFileSignature(photoId, owner, 'full', expires + 900, given, NOW)).toBe(false);
    // Expired, tampered or malformed.
    expect(validFileSignature(photoId, owner, 'full', expires, given, expires * 1_000)).toBe(false);
    expect(validFileSignature(photoId, owner, 'full', expires, `${given.slice(0, -1)}${given.endsWith('A') ? 'B' : 'A'}`, NOW)).toBe(false);
    expect(validFileSignature(photoId, owner, 'full', expires, given.slice(1), NOW)).toBe(false);
    expect(validFileSignature(photoId, owner, 'full', Number.NaN, given, NOW)).toBe(false);
  });

  it('is signed with the service-role key: another key reads none, and without one there is none', () => {
    const expires = fileAddressExpiry(NOW);
    const given = signature(signedFileAddress(photoId, owner, 'full', expires)!);
    vi.stubEnv('SUPABASE_SERVICE_ROLE_KEY', 'another-synthetic-key');
    expect(validFileSignature(photoId, owner, 'full', expires, given, NOW)).toBe(false);
    vi.stubEnv('SUPABASE_SERVICE_ROLE_KEY', '');
    expect(signedFileAddress(photoId, owner, 'full', expires)).toBeNull();
    expect(validFileSignature(photoId, owner, 'full', expires, given, NOW)).toBe(false);
  });
});

const storedRow = {
  id: '7a000000-0000-4000-c000-000000000001',
  label: 'Boots',
  package_notes: { body: 'Size 42, return by Friday' },
  package_files: [
    { id: pdfId, name: 'Invoice.pdf', content_type: 'application/pdf', size: 2_048, width: null, height: null, created_at: '2026-11-25T09:05:00+00:00' },
    { id: photoId, name: 'Receipt.jpg', content_type: 'image/jpeg', size: 1_024, width: 1_200, height: 900, created_at: '2026-11-25T09:00:00+00:00' },
  ],
};

describe('the owner\'s parcel', () => {
  it('carries its note and its files, oldest first, with signed addresses in place of the embedded rows', () => {
    const served = withOwnerNote(storedRow, owner, NOW);
    expect(served).not.toHaveProperty('package_notes');
    expect(served).not.toHaveProperty('package_files');
    expect(served.note).toBe('Size 42, return by Friday');
    const files = served.files as Array<Record<string, unknown>>;
    expect(files.map((file) => file.name)).toEqual(['Receipt.jpg', 'Invoice.pdf']);
    expect(files[0]).toMatchObject({
      id: photoId, content_type: 'image/jpeg', size: 1_024, width: 1_200, height: 900,
      created_at: '2026-11-25T09:00:00.000Z', expires_at: '2026-11-25T10:30:00.000Z',
    });
    expect(String(files[0]!.url)).toMatch(/^\/api\/files\/.+\?v=full&/);
    expect(String(files[0]!.thumbnail_url)).toMatch(/^\/api\/files\/.+\?v=thumb&/);
    expect(validFileSignature(photoId, owner, 'thumb', expiry(String(files[0]!.thumbnail_url)), signature(String(files[0]!.thumbnail_url)), NOW)).toBe(true);
    // A PDF has no small copy, nor a size in pixels.
    expect(files[1]).toMatchObject({ content_type: 'application/pdf', thumbnail_url: null });
    expect(files[1]).not.toHaveProperty('width');
  });

  it('leaves out what there is none of, and the files when no address can be signed for the owner', () => {
    expect(withOwnerNote({ id: 'p', package_notes: null, package_files: [] }, owner, NOW)).toEqual({ id: 'p' });
    expect(withOwnerNote({ id: 'p', package_notes: [], package_files: [] }, owner, NOW)).toEqual({ id: 'p' });
    expect(withOwnerNote({ id: 'p', package_notes: [{ body: 'Gate code at the back' }] }, owner, NOW)).toEqual({ id: 'p', note: 'Gate code at the back' });
    expect(withOwnerNote(storedRow, null, NOW)).toEqual({ id: storedRow.id, label: 'Boots', note: 'Size 42, return by Friday' });
    vi.stubEnv('SUPABASE_SERVICE_ROLE_KEY', '');
    expect(withOwnerNote(storedRow, owner, NOW)).not.toHaveProperty('files');
    // A row read without the embed is left as it is.
    const plain = { id: 'p', label: '' };
    expect(withOwnerNote(plain, owner, NOW)).toBe(plain);
  });

  it('is exported with its note and its files\' names, types, sizes and dates, never their addresses', () => {
    const exported = withExportedNote(withOwnerNote(storedRow, owner, NOW));
    expect(exported.note).toBe('Size 42, return by Friday');
    expect(exported.files).toEqual([
      { name: 'Receipt.jpg', content_type: 'image/jpeg', size: 1_024, created_at: '2026-11-25T09:00:00.000Z' },
      { name: 'Invoice.pdf', content_type: 'application/pdf', size: 2_048, created_at: '2026-11-25T09:05:00.000Z' },
    ]);
    expect(JSON.stringify(exported)).not.toMatch(/api\/files|thumbnail|expires/);
  });

  it('serves no file a stored row does not type as one Peek keeps', () => {
    const file = servedFile({ id: photoId, name: 'Receipt.jpg', content_type: 'image/jpeg', size: 0, created_at: 'never' }, owner, fileAddressExpiry(NOW));
    expect(file).toMatchObject({ size: 1, created_at: '1970-01-01T00:00:00.000Z' });
    expect(withOwnerNote({ id: 'p', package_files: [{ id: photoId, name: 'page.html', content_type: 'text/html' }] }, owner, NOW)).toEqual({ id: 'p' });
  });
});

describe('an assistant\'s parcel', () => {
  it('reads the note\'s text and its files\' names and types, and whether there is any', () => {
    expect(assistantNote(storedRow)).toEqual({
      note: 'Size 42, return by Friday',
      files: [{ name: 'Receipt.jpg', type: 'image/jpeg' }, { name: 'Invoice.pdf', type: 'application/pdf' }],
    });
    expect(assistantNote({ package_notes: null, package_files: [] })).toEqual({ note: null, files: [] });
    expect(hasNote(storedRow)).toBe(true);
    expect(hasNote({ package_notes: null, package_files: [{ id: photoId }] })).toBe(true);
    expect(hasNote({ package_notes: [], package_files: [] })).toBe(false);
    expect(hasNote({})).toBe(false);
  });
});
