import { afterEach, describe, expect, it, vi } from 'vitest';
import contractFixture from '../../contracts/fixtures/delivery-api.json';
import { ApiAuthenticationError } from '../lib/apiClient';
import { NoteFileError } from '../lib/parcelNote';
import type { ApiPackageFile, ApiPackageRow } from '../generated/apiContract';
import { createApiRepo, toParcel, uploadPackageFile } from './apiRepo';

const packageRow = contractFixture.packageList.packages[0] as unknown as ApiPackageRow;
const fileRow: ApiPackageFile = {
  id: 'file-1', name: 'Box.jpg', content_type: 'image/jpeg', size: 140_000, width: 2560, height: 1920, created_at: '2026-11-25T10:00:00Z',
  url: '/api/files/file-1?v=full&e=1764068400&s=signature', thumbnail_url: '/api/files/file-1?v=thumb&e=1764068400&s=signature', expires_at: '2026-11-25T11:00:00Z',
};

const json = (body: unknown, status = 200) => new Response(JSON.stringify(body), { status, headers: { 'Content-Type': 'application/json' } });

/** An upload as the browser makes it: what was sent, and the answer the test gives. */
class FakeUpload {
  static sent: FakeUpload[] = [];
  static answers: { status: number; body?: unknown; progress?: number[]; fail?: 'error' | 'timeout' }[] = [];
  method = '';
  path = '';
  headers: Record<string, string> = {};
  body: unknown;
  status = 0;
  responseText = '';
  timeout = 0;
  private listeners = new Map<string, (event: { loaded?: number }) => void>();
  upload = { addEventListener: (type: string, listener: (event: { loaded: number }) => void) => { this.listeners.set(`upload:${type}`, listener as never); } };
  open(method: string, path: string) { this.method = method; this.path = path; }
  setRequestHeader(name: string, value: string) { this.headers[name] = value; }
  addEventListener(type: string, listener: (event: { loaded?: number }) => void) { this.listeners.set(type, listener); }
  abort() { this.listeners.get('abort')?.({}); }
  send(body: unknown) {
    this.body = body;
    FakeUpload.sent.push(this);
    const answer = FakeUpload.answers.shift() ?? { status: 201 };
    setTimeout(() => {
      for (const loaded of answer.progress ?? []) this.listeners.get('upload:progress')?.({ loaded });
      if (answer.fail) { this.listeners.get(answer.fail)?.({}); return; }
      this.status = answer.status;
      this.responseText = answer.body === undefined ? '' : JSON.stringify(answer.body);
      this.listeners.get('load')?.({});
    }, 0);
  }
}

function stubUploads(...answers: typeof FakeUpload.answers) {
  FakeUpload.sent = [];
  FakeUpload.answers = answers;
  vi.stubGlobal('XMLHttpRequest', FakeUpload);
}

const auth = (tokens = ['token-1', 'token-2']) => {
  const getAccessToken = vi.fn(async (renew?: boolean) => renew ? tokens[1] : tokens[0]);
  return { userId: 'user-1', getAccessToken, onAuthenticationFailure: vi.fn(async () => undefined) };
};

afterEach(() => {
  vi.unstubAllGlobals();
  vi.restoreAllMocks();
  Reflect.deleteProperty(navigator, 'onLine');
});

describe("an account parcel's note", () => {
  it('reads the note and its files, and leaves both out when there are none', () => {
    const noted = toParcel({ ...packageRow, note: 'Door code 4812', files: [fileRow] });
    expect(noted.note).toBe('Door code 4812');
    expect(noted.files).toEqual([{
      id: 'file-1', name: 'Box.jpg', contentType: 'image/jpeg', size: 140_000, width: 2560, height: 1920, createdAt: '2026-11-25T10:00:00Z',
      url: fileRow.url, thumbnailUrl: fileRow.thumbnail_url, expiresAt: '2026-11-25T11:00:00Z',
    }]);
    const plain = toParcel({ ...packageRow, files: [] });
    expect('note' in plain).toBe(false);
    expect('files' in plain).toBe(false);
  });

  it('saves the words with the parcel’s PATCH, a blank note as null, and keeps the request alive as the page goes', async () => {
    const fetch = vi.fn<typeof globalThis.fetch>(async () => json({ ...packageRow, note: 'Door code 4812' }));
    vi.stubGlobal('fetch', fetch);
    const repo = createApiRepo(30_000, 1_000, null);
    const saved = await repo.setNote!(packageRow.id, 'Door code 4812');
    expect(saved.note).toBe('Door code 4812');
    expect(fetch).toHaveBeenLastCalledWith(`/api/packages/${packageRow.id}`, expect.objectContaining({ method: 'PATCH', body: JSON.stringify({ note: 'Door code 4812' }) }));
    expect(fetch.mock.lastCall?.[1]).not.toHaveProperty('keepalive');

    fetch.mockResolvedValueOnce(json(packageRow));
    await repo.setNote!(packageRow.id, '   ', { keepalive: true });
    expect(fetch).toHaveBeenLastCalledWith(`/api/packages/${packageRow.id}`, expect.objectContaining({ body: JSON.stringify({ note: null }), keepalive: true }));
  });

  it('removes a file, counting one already gone as removed', async () => {
    const fetch = vi.fn<typeof globalThis.fetch>()
      .mockResolvedValueOnce(json({ ok: true }))
      .mockResolvedValueOnce(json({ error: 'Not found' }, 404))
      .mockResolvedValueOnce(json({ error: 'Broken' }, 500));
    vi.stubGlobal('fetch', fetch);
    const repo = createApiRepo(30_000, 1_000, null);
    await repo.removeFile!(packageRow.id, 'file-1', { keepalive: true });
    expect(fetch).toHaveBeenLastCalledWith(`/api/packages/${packageRow.id}/files/file-1`, expect.objectContaining({ method: 'DELETE', keepalive: true }));
    await expect(repo.removeFile!(packageRow.id, 'file-1')).resolves.toBeUndefined();
    await expect(repo.removeFile!(packageRow.id, 'file-1')).rejects.toThrow();
  });
});

describe('uploading a file', () => {
  it('sends the bytes with their type and name, tells how much has gone, and returns the parcel', async () => {
    stubUploads({ status: 201, body: { file: fileRow, package: { ...packageRow, files: [fileRow] } }, progress: [40, 100] });
    const sent: number[] = [];
    const repo = createApiRepo(30_000, 1_000, null, auth());
    const blob = new Blob(['photo'], { type: 'image/jpeg' });
    const parcel = await repo.addFile!(packageRow.id, blob, 'Boîte à chaussures.jpg', (loaded) => sent.push(loaded));
    const [upload] = FakeUpload.sent;
    expect(upload.method).toBe('POST');
    expect(upload.path).toBe(`/api/packages/${packageRow.id}/files`);
    expect(upload.body).toBe(blob);
    expect(upload.headers).toMatchObject({
      'Content-Type': 'image/jpeg', 'X-File-Name': 'Bo%C3%AEte%20%C3%A0%20chaussures.jpg', Authorization: 'Bearer token-1', 'X-Requested-With': 'XMLHttpRequest',
    });
    expect(sent).toEqual([40, 100]);
    expect(parcel.files?.map((file) => file.id)).toEqual(['file-1']);
  });

  it('says why the server refused a file, with its status', async () => {
    stubUploads(
      { status: 413, body: { error: 'Too big', code: 'file_too_big' } },
      { status: 415, body: { error: 'Type', code: 'file_type' } },
      { status: 409, body: { error: 'Full', code: 'parcel_files_full' } },
      { status: 409, body: { error: 'Full', code: 'account_files_full' } },
      { status: 404 },
      { status: 502 },
    );
    const blob = new Blob(['x'], { type: 'application/pdf' });
    const refusal = async () => uploadPackageFile('package-1', blob, 'Receipt.pdf', auth()).then(() => null, (error: unknown) => error);
    const problems: unknown[] = [];
    for (let index = 0; index < 6; index++) {
      const error = await refusal();
      problems.push(error instanceof NoteFileError ? [error.problem, error.status] : error);
    }
    expect(problems).toEqual([['too-big', 413], ['type', 415], ['parcel-full', 409], ['account-full', 409], ['failed', 404], ['failed', 502]]);
  });

  it('renews an expired token once, then gives up as signed out', async () => {
    stubUploads({ status: 401 }, { status: 201, body: { file: fileRow, package: packageRow } });
    const signedIn = auth();
    await uploadPackageFile('package-1', new Blob(['x']), 'Box.jpg', signedIn);
    expect(FakeUpload.sent.map((upload) => upload.headers.Authorization)).toEqual(['Bearer token-1', 'Bearer token-2']);

    stubUploads({ status: 401 }, { status: 401 });
    const expired = auth();
    await expect(uploadPackageFile('package-1', new Blob(['x']), 'Box.jpg', expired)).rejects.toBeInstanceOf(ApiAuthenticationError);
    expect(expired.onAuthenticationFailure).toHaveBeenCalledTimes(1);
  });

  it('fails as offline without a connection, and stops when asked', async () => {
    Object.defineProperty(navigator, 'onLine', { configurable: true, value: false });
    stubUploads({ status: 0, fail: 'error' });
    await expect(uploadPackageFile('package-1', new Blob(['x']), 'Box.jpg', auth())).rejects.toMatchObject({ problem: 'offline' });

    stubUploads({ status: 201 });
    const stop = new AbortController();
    stop.abort();
    await expect(uploadPackageFile('package-1', new Blob(['x']), 'Box.jpg', auth(), undefined, stop.signal)).rejects.toMatchObject({ name: 'AbortError' });
    expect(FakeUpload.sent).toEqual([]);
  });
});
