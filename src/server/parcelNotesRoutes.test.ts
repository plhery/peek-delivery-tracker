import { NextRequest } from 'next/server';
import sharp from 'sharp';
import { afterEach, beforeEach, describe, expect, it, vi, type MockInstance } from 'vitest';
import { GET as readFile } from '../../app/api/files/[fileId]/route';
import { PATCH as changeParcel } from '../../app/api/packages/[id]/route';
import { POST as addFile } from '../../app/api/packages/[id]/files/route';
import { DELETE as removeFile } from '../../app/api/packages/[id]/files/[fileId]/route';
import { GET as exportAccount } from '../../app/api/account/export/route';
import { SupabaseAuthenticator } from './auth';
import * as friends from './friends';
import * as observability from './observability';
import { fileAddressExpiry, signedFileAddress } from './parcelNotes';
import { SupabaseError, SupabaseServiceClient, SupabaseUserClient } from './supabase';
import type { JsonObject } from './types';

// Synthetic identifiers, numbers and files only.
const userId = '7b000000-0000-4000-a000-000000000001';
const strangerId = '7b000000-0000-4000-a000-000000000002';
const parcelId = '7b000000-0000-4000-b000-000000000001';
const otherParcelId = '7b000000-0000-4000-b000-000000000002';
const fileId = (index: number) => `7b000000-0000-4000-c000-${String(index).padStart(12, '0')}`;

interface StoredFile { id: string; name: string; content_type: string; size: number; width: number | null; height: number | null; created_at: string }
let note: string | null;
let files: StoredFile[];
let userRequest: MockInstance<SupabaseUserClient['request']>;

/** The account's parcel as PostgREST gives its owner: the columns, with its note and files embedded. */
function storedParcel(): JsonObject {
  return {
    id: parcelId, tracking_number: 'TESTNOTE0001', label: 'Boots', carrier: 'dhl', created_at: '2026-11-20T08:00:00+00:00',
    expected_delivery: null, last_status_text: null, last_synced_at: null, sync_status: 'ok', sync_error: null, tracking_url: null,
    dpd_postcode: null, carrier_data: {}, archived_at: null, notifications_muted: false, tracking_events: [],
    package_notes: note === null ? null : { body: note },
    package_files: files,
  };
}

/** The database's answers to the account's own token, for the requests these routes make. */
async function database(path: string, options: { method?: string; body?: unknown } = {}): Promise<unknown> {
  const url = new URL(path, 'https://database.example');
  const body = (options.body ?? {}) as JsonObject;
  if (url.pathname === '/rest/v1/packages' && (options.method ?? 'GET') === 'GET') {
    expect(url.searchParams.get('select')).toContain('package_notes(body),package_files(');
    return url.searchParams.get('id') === `eq.${parcelId}` ? [storedParcel()] : [];
  }
  if (url.pathname === '/rest/v1/rpc/set_package_note') {
    if (body.p_package_id !== parcelId) return false;
    note = typeof body.p_body === 'string' ? body.p_body : null;
    return true;
  }
  if (url.pathname === '/rest/v1/rpc/add_package_file') {
    const content = Buffer.from(String(body.p_content), 'base64');
    const stored = {
      id: fileId(files.length + 1), name: String(body.p_name), content_type: String(body.p_content_type), size: content.byteLength,
      width: body.p_width as number | null, height: body.p_height as number | null, created_at: `2026-11-25T09:0${files.length}:00+00:00`,
    };
    files.push(stored);
    return stored;
  }
  if (url.pathname === '/rest/v1/rpc/delete_package_file') {
    const before = files.length;
    files = files.filter((file) => file.id !== body.p_file_id);
    return files.length < before;
  }
  throw new Error(`Unexpected request ${options.method ?? 'GET'} ${url.pathname}`);
}

function authorized(path: string, init: { method: string; headers?: Record<string, string>; body?: BodyInit }) {
  return new NextRequest(`https://delivery.example${path}`, {
    ...init, headers: { Authorization: 'Bearer synthetic-token', ...init.headers },
  });
}

const patch = (body: unknown, id = parcelId) => changeParcel(authorized(`/api/packages/${id}`, {
  method: 'PATCH', headers: { 'Content-Type': 'application/json' }, body: JSON.stringify(body),
}), { params: Promise.resolve({ id }) });

const upload = (bytes: Uint8Array, type: string, name: string | null = 'Receipt.jpg', headers: Record<string, string> = {}, id = parcelId) =>
  addFile(authorized(`/api/packages/${id}/files`, {
    method: 'POST',
    headers: { 'Content-Type': type, ...(name === null ? {} : { 'X-File-Name': encodeURIComponent(name) }), ...headers },
    body: Buffer.from(bytes),
  }), { params: Promise.resolve({ id }) });

const remove = (file: string, id = parcelId) => removeFile(authorized(`/api/packages/${id}/files/${file}`, { method: 'DELETE' }), {
  params: Promise.resolve({ id, fileId: file }),
});

const read = (address: string) => {
  const url = new URL(address, 'https://delivery.example');
  return readFile(new NextRequest(url), { params: Promise.resolve({ fileId: url.pathname.split('/').pop()! }) });
};

const PDF = Buffer.from('%PDF-1.4\n% synthetic\n%%EOF\n', 'latin1');
let photo: Buffer;

beforeEach(async () => {
  vi.stubEnv('SUPABASE_URL', 'https://database.example');
  vi.stubEnv('SUPABASE_PUBLISHABLE_KEY', 'public-key');
  vi.stubEnv('SUPABASE_SERVICE_ROLE_KEY', 'synthetic-service-key');
  vi.spyOn(console, 'log').mockImplementation(() => undefined);
  vi.spyOn(console, 'error').mockImplementation(() => undefined);
  vi.spyOn(SupabaseAuthenticator.prototype, 'validate').mockResolvedValue({ id: userId, email: null, authenticatedAt: null, sessionId: null });
  note = null;
  files = [];
  userRequest = vi.spyOn(SupabaseUserClient.prototype, 'request').mockImplementation(database);
  photo ??= await sharp({ create: { width: 800, height: 600, channels: 3, background: { r: 10, g: 120, b: 200 } } }).jpeg().toBuffer();
});
afterEach(() => { vi.restoreAllMocks(); vi.unstubAllEnvs(); });

describe('PATCH /api/packages/{id}', () => {
  it('writes the note, the name or both, and serves the parcel with its note', async () => {
    const rename = vi.spyOn(SupabaseUserClient.prototype, 'updatePackage').mockResolvedValue(undefined);
    const written = await patch({ note: '  Size 42\r\nReturn by Friday  ' });
    expect(written.status).toBe(200);
    expect(await written.json()).toMatchObject({ id: parcelId, label: 'Boots', note: 'Size 42\nReturn by Friday' });
    expect(rename).not.toHaveBeenCalled();

    const both = await patch({ label: 'Winter boots', note: 'Gift for Sam' });
    expect(both.status).toBe(200);
    expect(rename).toHaveBeenCalledExactlyOnceWith(parcelId, { label: 'Winter boots' });
    expect(note).toBe('Gift for Sam');

    // Null or blank deletes it; the parcel is then served without one.
    for (const cleared of [null, ' \n ']) {
      note = 'Something';
      const response = await patch({ note: cleared });
      expect(response.status).toBe(200);
      expect(await response.json()).not.toHaveProperty('note');
    }
    // A name alone leaves the note.
    note = 'Kept';
    await patch({ label: 'Shoes' });
    expect(note).toBe('Kept');
  });

  it('counts the note in characters, not bytes, and refuses one over 1,000 or nothing to change', async () => {
    vi.spyOn(SupabaseUserClient.prototype, 'updatePackage').mockResolvedValue(undefined);
    expect((await patch({ note: '🙂'.repeat(1_000) })).status).toBe(200);
    expect(note).toBe('🙂'.repeat(1_000));
    for (const body of [{ note: 'n'.repeat(1_001) }, { note: 42 }, {}]) {
      expect((await patch(body)).status).toBe(400);
    }
  });

  it('answers 404 for a parcel that is not the account\'s', async () => {
    const written = vi.spyOn(SupabaseUserClient.prototype, 'setPackageNote');
    expect((await patch({ note: 'Mine' }, otherParcelId)).status).toBe(404);
    expect(written).not.toHaveBeenCalled();
  });
});

describe('POST /api/packages/{id}/files', () => {
  it('stores a photo written again, and answers with the file and the parcel, both with signed addresses', async () => {
    const response = await upload(photo, 'image/jpeg', 'Reçu de juin.jpg');
    expect(response.status).toBe(201);
    const body = await response.json();
    expect(body.file).toMatchObject({ id: fileId(1), name: 'Reçu de juin.jpg', content_type: 'image/jpeg', width: 800, height: 600 });
    expect(body.file.url).toMatch(new RegExp(`^/api/files/${fileId(1)}\\?v=full&e=\\d+&s=`));
    expect(body.file.thumbnail_url).toMatch(/\?v=thumb&/);
    expect(body.package.id).toBe(parcelId);
    expect(body.package.files).toEqual([body.file]);
    // The database got the photo as written again, with its small copy.
    const sent = userRequest.mock.calls.find(([path]) => path === '/rest/v1/rpc/add_package_file')![1]!.body as JsonObject;
    expect(sent).toMatchObject({ p_package_id: parcelId, p_name: 'Reçu de juin.jpg', p_content_type: 'image/jpeg', p_width: 800, p_height: 600 });
    expect(await sharp(Buffer.from(String(sent.p_thumbnail), 'base64')).metadata()).toMatchObject({ format: 'jpeg', width: 384, height: 288 });
  });

  it('keeps a PDF as sent, with no small copy', async () => {
    const response = await upload(PDF, 'application/pdf', 'Invoice');
    expect(response.status).toBe(201);
    expect((await response.json()).file).toMatchObject({ name: 'Invoice.pdf', content_type: 'application/pdf', size: PDF.byteLength, thumbnail_url: null });
  });

  it('refuses what is not a photo or PDF Peek keeps, before the database is asked', async () => {
    for (const [bytes, type] of [[photo, 'image/heic'], [photo, 'image/png'], [PDF, 'image/jpeg'], [Buffer.from('<svg/>'), 'image/svg+xml']] as const) {
      const response = await upload(bytes, type);
      expect(response.status).toBe(415);
      expect(await response.json()).toMatchObject({ code: 'file_type' });
    }
    expect(userRequest.mock.calls.some(([path]) => String(path).includes('add_package_file'))).toBe(false);
    expect((await upload(photo, 'image/jpeg', null)).status).toBe(400);
  });

  it('refuses a file over 10 MB with 413, from its declared length without reading it, or as it is read', async () => {
    const declared = await upload(PDF, 'application/pdf', 'Big.pdf', { 'Content-Length': String(10 * 1_048_576 + 1) });
    expect(declared.status).toBe(413);
    expect(await declared.json()).toMatchObject({ code: 'file_too_big' });
    expect(userRequest).not.toHaveBeenCalled();
    const sent = await upload(Buffer.concat([PDF, Buffer.alloc(10 * 1_048_576)]), 'application/pdf', 'Big.pdf');
    expect(sent.status).toBe(413);
    expect(userRequest.mock.calls.some(([path]) => String(path).includes('add_package_file'))).toBe(false);
  });

  it('refuses a sixth file on the parcel before reading it, and says which limit the database held', async () => {
    files = Array.from({ length: 5 }, (_, index) => ({
      id: fileId(index + 1), name: `Page ${index}.pdf`, content_type: 'application/pdf', size: 10, width: null, height: null, created_at: '2026-11-25T08:00:00Z',
    }));
    const full = await upload(PDF, 'application/pdf');
    expect(full.status).toBe(409);
    expect(await full.json()).toMatchObject({ code: 'parcel_files_full' });
    files = [];

    const add = vi.spyOn(SupabaseUserClient.prototype, 'request');
    for (const [code, status, kind] of [
      ['PF001', 409, 'parcel_files_full'], ['PF002', 409, 'account_files_full'], ['PF003', 413, 'file_too_big'], ['PF004', 415, 'file_type'],
    ] as const) {
      add.mockImplementation(async (path, options) => {
        if (path === '/rest/v1/rpc/add_package_file') throw new SupabaseError('Supabase POST request failed (400)', 400, code);
        return database(path, options);
      });
      const response = await upload(PDF, 'application/pdf');
      expect(response.status).toBe(status);
      expect(await response.json()).toMatchObject({ code: kind });
    }
    add.mockImplementation(async (path, options) => {
      if (path === '/rest/v1/rpc/add_package_file') throw new SupabaseError('Supabase POST request failed (500)', 500, 'P0002');
      return database(path, options);
    });
    expect((await upload(PDF, 'application/pdf')).status).toBe(404);
  });

  it('answers 404 for a parcel that is not the account\'s', async () => {
    expect((await upload(PDF, 'application/pdf', 'Invoice.pdf', {}, otherParcelId)).status).toBe(404);
    expect(files).toEqual([]);
  });

  it('takes twenty files a minute from an account', async () => {
    vi.mocked(SupabaseAuthenticator.prototype.validate).mockResolvedValue({ id: strangerId, email: null, authenticatedAt: null, sessionId: null });
    const statuses: number[] = [];
    for (let index = 0; index < 21; index += 1) statuses.push((await upload(PDF, 'image/heic')).status);
    expect(statuses.slice(0, 20).every((status) => status === 415)).toBe(true);
    expect(statuses[20]).toBe(429);
  });

  it('leaves the file out of an error report', async () => {
    const report = vi.spyOn(observability, 'captureOperationalError').mockReturnValue(null);
    userRequest.mockImplementation(async (path, options) => {
      if (path === '/rest/v1/rpc/add_package_file') throw new SupabaseError('Supabase POST request failed (503)', 503);
      return database(path, options);
    });
    expect((await upload(PDF, 'application/pdf')).status).toBe(502);
    expect(report).toHaveBeenCalledOnce();
    expect(JSON.stringify(report.mock.calls)).not.toContain(PDF.toString('base64'));
  });
});

describe('DELETE /api/packages/{id}/files/{fileId}', () => {
  it('removes a file of the parcel, and nothing the parcel does not hold', async () => {
    files = [{ id: fileId(1), name: 'Receipt.jpg', content_type: 'image/jpeg', size: 10, width: 1, height: 1, created_at: '2026-11-25T08:00:00Z' }];
    const deleted = vi.spyOn(SupabaseUserClient.prototype, 'deletePackageFile');
    expect((await remove(fileId(2))).status).toBe(404);
    expect((await remove(fileId(1), otherParcelId)).status).toBe(404);
    expect(deleted).not.toHaveBeenCalled();
    const response = await remove(fileId(1));
    expect(response.status).toBe(200);
    expect(await response.json()).toEqual({ ok: true });
    expect(files).toEqual([]);
    expect((await remove('not-a-file')).status).toBe(400);
  });
});

describe('GET /api/files/{fileId}', () => {
  const stored = { id: fileId(1), userId, name: 'Reçu "juin".jpg', contentType: 'image/jpeg', size: 4 };
  const thumbnail = Buffer.from([0xff, 0xd8, 0xff, 0xdb, 0x01]);
  const full = Buffer.from([0xff, 0xd8, 0xff, 0xe0, 0x02]);
  let lookup: ReturnType<typeof vi.spyOn>;
  let content: ReturnType<typeof vi.spyOn>;
  beforeEach(() => {
    lookup = vi.spyOn(SupabaseServiceClient.prototype, 'packageFile').mockImplementation(async (id) => (id === stored.id ? stored : null));
    content = vi.spyOn(SupabaseServiceClient.prototype, 'packageFileContent')
      .mockImplementation(async (id, owner, small) => (id === stored.id && owner === userId ? (small ? thumbnail : full) : null));
  });

  it('serves the owner\'s file through its signed address, to the reader\'s own cache only', async () => {
    const expires = fileAddressExpiry();
    const response = await read(signedFileAddress(stored.id, userId, 'full', expires)!);
    expect(response.status).toBe(200);
    expect(Buffer.from(await response.arrayBuffer())).toEqual(full);
    expect(content).toHaveBeenCalledExactlyOnceWith(stored.id, userId, false);
    const headers = Object.fromEntries(response.headers);
    expect(headers).toMatchObject({
      'content-type': 'image/jpeg',
      'content-length': String(full.byteLength),
      'content-disposition': 'inline; filename="Recu _juin_.jpg"; filename*=UTF-8\'\'Re%C3%A7u%20%22juin%22.jpg',
      'x-content-type-options': 'nosniff',
      'referrer-policy': 'no-referrer',
      'x-robots-tag': 'noindex',
      'content-security-policy': "default-src 'none'; sandbox",
    });
    const maxAge = Number(/^private, max-age=(\d+), immutable$/.exec(headers['cache-control']!)?.[1]);
    expect(maxAge).toBeGreaterThan(900);
    expect(maxAge).toBeLessThanOrEqual(1_800);
  });

  it('serves the small copy, and saves rather than shows with download=1', async () => {
    const expires = fileAddressExpiry();
    const small = await read(signedFileAddress(stored.id, userId, 'thumb', expires)!);
    expect(small.status).toBe(200);
    expect(Buffer.from(await small.arrayBuffer())).toEqual(thumbnail);
    const saved = await read(`${signedFileAddress(stored.id, userId, 'full', expires)!}&download=1`);
    expect(saved.headers.get('content-disposition')).toMatch(/^attachment; /);
  });

  it('serves a PDF without sandboxing it, which would stop the browser\'s viewer', async () => {
    lookup.mockResolvedValue({ ...stored, name: 'Invoice.pdf', contentType: 'application/pdf' });
    content.mockResolvedValue(PDF);
    const response = await read(signedFileAddress(stored.id, userId, 'full', fileAddressExpiry())!);
    expect(response.headers.get('content-type')).toBe('application/pdf');
    expect(response.headers.get('content-security-policy')).toBe("default-src 'none'");
  });

  it('answers 404, kept by no cache, for an expired, tampered, malformed or another account\'s address', async () => {
    const expires = fileAddressExpiry();
    const valid = signedFileAddress(stored.id, userId, 'full', expires)!;
    const signature = new URL(valid, 'https://delivery.example').searchParams.get('s')!;
    const past = Math.floor(Date.now() / 1_000) - 1;
    const addresses = [
      signedFileAddress(stored.id, userId, 'full', past)!,
      valid.replace(`s=${signature}`, `s=${signature.startsWith('A') ? 'B' : 'A'}${signature.slice(1)}`),
      valid.replace('v=full', 'v=thumb'),
      valid.replace(`e=${expires}`, `e=${expires + 900}`),
      signedFileAddress(stored.id, strangerId, 'full', expires)!,
      signedFileAddress(fileId(9), userId, 'full', expires)!,
      valid.replace('v=full', 'v=other'),
      valid.replace(/&s=.*/, ''),
      `/api/files/not-a-file?v=full&e=${expires}&s=${signature}`,
    ];
    for (const address of addresses) {
      const response = await read(address);
      expect(response.status).toBe(404);
      expect(response.headers.get('cache-control')).toBe('no-store');
    }
    // An expired or malformed address is refused without asking the database.
    lookup.mockClear();
    await read(addresses[0]!);
    await read(addresses[6]!);
    expect(lookup).not.toHaveBeenCalled();
    // The signature is never logged.
    const logged = vi.mocked(console.log).mock.calls.map((call) => call.join(' ')).join('\n');
    expect(logged).toContain('/api/files/:id');
    expect(logged).not.toContain(signature);
  });
});

describe('the account export', () => {
  it('holds each parcel\'s note and its files\' names, types, sizes and dates, without their addresses', async () => {
    note = 'Size 42';
    files = [{ id: fileId(1), name: 'Receipt.jpg', content_type: 'image/jpeg', size: 1_024, width: 10, height: 10, created_at: '2026-11-25T08:00:00Z' }];
    userRequest.mockImplementation(async (path, options) => {
      const url = new URL(path, 'https://database.example');
      if (url.pathname === '/rest/v1/packages') return [storedParcel()];
      if (url.pathname === '/rest/v1/delivery_emails') return [];
      return database(path, options);
    });
    vi.spyOn(friends, 'friendsRPC').mockResolvedValue({ profile: null, ownCard: null, friends: [] });
    vi.spyOn(SupabaseUserClient.prototype, 'getNotificationPreferences').mockResolvedValue({});
    vi.spyOn(SupabaseUserClient.prototype, 'listDeliveryEmails').mockResolvedValue([]);
    vi.spyOn(SupabaseServiceClient.prototype, 'inboundMailbox').mockResolvedValue(null);
    vi.spyOn(SupabaseServiceClient.prototype, 'assistantGrants').mockResolvedValue([]);
    vi.spyOn(SupabaseServiceClient.prototype, 'accountLastOpened').mockResolvedValue(null);
    vi.spyOn(SupabaseServiceClient.prototype, 'twinNumbers').mockResolvedValue([]);
    const response = await exportAccount(authorized('/api/account/export', { method: 'GET' }), { params: Promise.resolve({}) });
    expect(response.status).toBe(200);
    const exported = await response.json();
    expect(exported.packages[0]).toMatchObject({
      note: 'Size 42', files: [{ name: 'Receipt.jpg', content_type: 'image/jpeg', size: 1_024, created_at: '2026-11-25T08:00:00.000Z' }],
    });
    expect(JSON.stringify(exported)).not.toMatch(/api\/files|thumbnail_url|package_notes|package_files/);
  });
});
