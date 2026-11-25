import { act, render, screen, waitFor } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import { describe, expect, it, vi } from 'vitest';
import { ApiAuthenticationError } from '../lib/apiClient';
import { ServerDownError } from '../lib/maintenance';
import { ParcelsProvider, useParcels } from './ParcelsContext';
import type { ParcelRepo, ParcelWithEvents } from '../types';

describe('a refused addition', () => {
  function Add() {
    const { addParcel, error, authenticationRequired } = useParcels();
    return <><button onClick={() => void addParcel({ trackingNumber: '123459999981', carrier: 'gls-de', label: 'Books' }).catch(() => undefined)}>Add</button>
      <p>{error === null ? 'no error' : authenticationRequired ? 'sign in' : 'error'}</p></>;
  }

  it('leaves its reason to the add sheet, unless the sign-in lapsed', async () => {
    const repo: ParcelRepo = {
      mode: 'api', list: vi.fn().mockResolvedValue([]), rename: vi.fn(), remove: vi.fn(), refresh: vi.fn(), refreshParcel: vi.fn(),
      add: vi.fn().mockRejectedValue(Object.assign(new Error('Today’s tracking allowance is used up. Try again tomorrow.'), { status: 429, scope: 'daily' })),
    };
    render(<ParcelsProvider repo={repo}><Add /></ParcelsProvider>);
    await waitFor(() => expect(repo.list).toHaveBeenCalled());
    await userEvent.setup().click(screen.getByRole('button', { name: 'Add' }));
    await waitFor(() => expect(repo.add).toHaveBeenCalled());
    expect(screen.getByText('no error')).toBeVisible();
    vi.mocked(repo.add).mockRejectedValue(new ApiAuthenticationError());
    await userEvent.setup().click(screen.getByRole('button', { name: 'Add' }));
    expect(await screen.findByText('sign in')).toBeVisible();
  });
});

describe('a parcel marked as seen', () => {
  const tea: ParcelWithEvents = {
    id: 'tea', label: 'Tea', trackingNumber: '993412345612345678', carrier: 'swiss-post', syncStatus: 'ok', createdAt: '2026-11-01T08:00:00.000Z', events: [],
    newsAt: '2026-11-15T10:00:00.000Z', seenAt: '2026-11-15T09:00:00.000Z',
  };
  function Seen() {
    const { parcels, markParcelSeen, error } = useParcels();
    return <><button onClick={() => void markParcelSeen('tea')}>Open</button>
      <p>{parcels.map((parcel) => `${parcel.id} ${parcel.seenAt}`).join()}</p><p>{error === null ? 'no error' : 'error'}</p></>;
  }
  const service = (markSeen?: ParcelRepo['markSeen']): ParcelRepo => ({
    mode: 'api', list: vi.fn().mockResolvedValue([tea]), add: vi.fn(), rename: vi.fn(), remove: vi.fn(), refresh: vi.fn(), markSeen,
  });

  it('counts as seen at once, then from the moment the server kept', async () => {
    let answer: (seenAt: string) => void = () => undefined;
    const repo = service(vi.fn(() => new Promise<string>((resolve) => { answer = resolve; })));
    render(<ParcelsProvider repo={repo}><Seen /></ParcelsProvider>);
    expect(await screen.findByText('tea 2026-11-15T09:00:00.000Z')).toBeVisible();
    await userEvent.setup().click(screen.getByRole('button', { name: 'Open' }));
    // No later than its news, whatever this device's clock says.
    expect(await screen.findByText('tea 2026-11-15T10:00:00.000Z')).toBeVisible();
    // The server stores it as of that news too.
    expect(repo.markSeen).toHaveBeenCalledWith('tea', '2026-11-15T10:00:00.000Z');
    answer('2026-11-15T10:20:00.000Z');
    expect(await screen.findByText('tea 2026-11-15T10:20:00.000Z')).toBeVisible();
  });

  it('lets a read of the list under way when the parcel is opened land, seen as late as it was marked', async () => {
    const newer: ParcelWithEvents = { ...tea, newsAt: '2026-11-15T10:45:00.000Z' };
    let read: (list: ParcelWithEvents[]) => void = () => undefined;
    let reload: () => void = () => undefined;
    const repo: ParcelRepo = {
      ...service(vi.fn().mockResolvedValue('2026-11-15T10:00:00.999Z')),
      list: vi.fn().mockResolvedValueOnce([tea]).mockImplementationOnce(() => new Promise((resolve) => { read = resolve; })),
      subscribe: (listener) => { reload = listener; return () => undefined; },
    };
    function News() {
      const { parcels, markParcelSeen } = useParcels();
      return <><button onClick={() => void markParcelSeen('tea')}>Open</button>
        <p>{parcels.map((parcel) => `${parcel.id} news ${parcel.newsAt} seen ${parcel.seenAt}`).join()}</p></>;
    }
    render(<ParcelsProvider repo={repo}><News /></ParcelsProvider>);
    await screen.findByText('tea news 2026-11-15T10:00:00.000Z seen 2026-11-15T09:00:00.000Z');
    act(() => { reload(); });
    await userEvent.setup().click(screen.getByRole('button', { name: 'Open' }));
    expect(await screen.findByText('tea news 2026-11-15T10:00:00.000Z seen 2026-11-15T10:00:00.999Z')).toBeVisible();
    // The read began before the parcel was seen: it lands with the later scan, which is still news.
    act(() => read([newer]));
    expect(await screen.findByText('tea news 2026-11-15T10:45:00.000Z seen 2026-11-15T10:00:00.999Z')).toBeVisible();
  });

  it('says nothing when the server could not keep it, or where nothing keeps it', async () => {
    const repo = service(vi.fn().mockRejectedValue(new Error('offline')));
    const view = render(<ParcelsProvider repo={repo}><Seen /></ParcelsProvider>);
    await screen.findByText('tea 2026-11-15T09:00:00.000Z');
    await userEvent.setup().click(screen.getByRole('button', { name: 'Open' }));
    await waitFor(() => expect(repo.markSeen).toHaveBeenCalled());
    expect(await screen.findByText('tea 2026-11-15T10:00:00.000Z')).toBeVisible();
    expect(screen.getByText('no error')).toBeVisible();
    view.unmount();

    render(<ParcelsProvider repo={service()}><Seen /></ParcelsProvider>);
    await screen.findByText('tea 2026-11-15T09:00:00.000Z');
    await userEvent.setup().click(screen.getByRole('button', { name: 'Open' }));
    expect(screen.getByText('tea 2026-11-15T09:00:00.000Z')).toBeVisible();
    expect(screen.getByText('no error')).toBeVisible();
  });
});

describe('the server down for maintenance', () => {
  const shoes: ParcelWithEvents = {
    id: 'shoes', label: 'Running shoes', trackingNumber: 'JJD000390007351234', carrier: 'dhl', syncStatus: 'ok', createdAt: '2026-11-10T08:00:00.000Z', events: [],
  };
  function List() {
    const { parcels, error, down, readAt, loading } = useParcels();
    const state = down ? (down.planned ? `planned until ${down.backAround}` : 'down') : 'up';
    return <p>{loading ? 'loading' : `${parcels.map((parcel) => parcel.label).join() || 'none'} · ${error ?? 'no error'} · ${state} · ${readAt}`}</p>;
  }

  it('keeps the list, says so in place of an error, and asks again by itself until the server answers', async () => {
    vi.useFakeTimers({ shouldAdvanceTime: true });
    const fetch = vi.fn().mockResolvedValue(Response.json({ planned: true, mode: 'soft', since: null, backAround: '2026-11-17T09:30:00.000Z' }));
    vi.stubGlobal('fetch', fetch);
    try {
      let reload: () => void = () => undefined;
      const repo: ParcelRepo = {
        mode: 'api', add: vi.fn(), rename: vi.fn(), remove: vi.fn(), refresh: vi.fn(),
        list: vi.fn().mockResolvedValueOnce([shoes]).mockRejectedValueOnce(new ServerDownError(0)).mockResolvedValueOnce([shoes]),
        cachedAt: vi.fn(() => '2026-11-17T08:55:00.000Z'),
        subscribe: (listener) => { reload = listener; return () => undefined; },
      };
      render(<ParcelsProvider repo={repo}><List /></ParcelsProvider>);
      expect(await screen.findByText('Running shoes · no error · up · 2026-11-17T08:55:00.000Z')).toBeVisible();

      act(() => { reload(); });
      // The edge's /maintenance.json, asked once something failed, says it was announced.
      expect(await screen.findByText('Running shoes · no error · planned until 2026-11-17T09:30:00.000Z · 2026-11-17T08:55:00.000Z')).toBeVisible();
      expect(fetch).toHaveBeenCalledTimes(1);
      expect(fetch).toHaveBeenCalledWith('/maintenance.json', expect.anything());

      await act(async () => { await vi.advanceTimersByTimeAsync(5_000); });
      expect(await screen.findByText('Running shoes · no error · up · 2026-11-17T08:55:00.000Z')).toBeVisible();
      expect(repo.list).toHaveBeenCalledTimes(3);
    } finally {
      vi.useRealTimers();
      vi.unstubAllGlobals();
    }
  });

  it('shows no empty list when it has nothing saved to show', async () => {
    vi.stubGlobal('fetch', vi.fn().mockResolvedValue(new Response('<!doctype html>', { status: 404, headers: { 'Content-Type': 'text/html' } })));
    try {
      const repo: ParcelRepo = {
        mode: 'api', add: vi.fn(), rename: vi.fn(), remove: vi.fn(), refresh: vi.fn(),
        list: vi.fn().mockRejectedValue(new ServerDownError(30_000)), cachedList: () => null,
      };
      render(<ParcelsProvider repo={repo}><List /></ParcelsProvider>);
      expect(await screen.findByText('none · no error · down · null')).toBeVisible();
    } finally {
      vi.unstubAllGlobals();
    }
  });
});

describe('an account addition that replaces the number half typed', () => {
  const parcel = (id: string, trackingNumber: string): ParcelWithEvents => ({
    id, label: '', trackingNumber, carrier: 'gls-de', syncStatus: 'pending', createdAt: '2026-11-24T12:00:00.000Z', events: [],
  });
  function Add() {
    const { addParcel, parcels } = useParcels();
    return <><button onClick={() => void addParcel({ trackingNumber: '123459999981', carrier: 'gls-de', label: '' })}>Add</button>
      <p>{parcels.map(({ id }) => id).join() || 'none'}</p></>;
  }

  it('drops the earlier parcel with the read of the list that follows', async () => {
    const start = parcel('start', '12345');
    const whole = parcel('whole', '123459999981');
    // The server deleted the start before it answered the add.
    const repo: ParcelRepo = {
      mode: 'api', list: vi.fn().mockResolvedValueOnce([start]).mockResolvedValue([whole]),
      add: vi.fn().mockResolvedValue(whole), rename: vi.fn(), remove: vi.fn(), refresh: vi.fn(),
    };
    render(<ParcelsProvider repo={repo}><Add /></ParcelsProvider>);
    expect(await screen.findByText('start')).toBeVisible();
    await userEvent.setup().click(screen.getByRole('button', { name: 'Add' }));
    expect(await screen.findByText('whole')).toBeVisible();
    expect(repo.list).toHaveBeenCalledTimes(2);
  });
});

describe("a parcel's note", () => {
  const tea: ParcelWithEvents = {
    id: 'tea', label: 'Tea', trackingNumber: '993412345612345678', carrier: 'swiss-post', syncStatus: 'ok', createdAt: '2026-11-01T08:00:00.000Z', events: [],
    files: [{ id: 'box', name: 'Box.jpg', contentType: 'image/jpeg', size: 10, createdAt: '2026-11-01T08:00:00.000Z', url: '/api/files/box', thumbnailUrl: null, expiresAt: '2026-11-01T09:00:00.000Z' }],
  };
  function Note() {
    const { parcels, saveParcelNote, removeParcelFile, error } = useParcels();
    return <><button onClick={() => void saveParcelNote('tea', 'Door code').catch(() => undefined)}>Save</button>
      <button onClick={() => void removeParcelFile('tea', 'box', { keepalive: true }).catch(() => undefined)}>Remove</button>
      <p>{parcels.map((parcel) => `${parcel.note ?? 'no note'} ${parcel.files?.length ?? 0} files`).join()}</p><p>{error === null ? 'no error' : 'error'}</p></>;
  }

  it('keeps what the server saved in the list, and leaves a refusal to the note’s sheet', async () => {
    const repo: ParcelRepo = {
      mode: 'api', list: vi.fn().mockResolvedValue([tea]), add: vi.fn(), rename: vi.fn(), remove: vi.fn(), refresh: vi.fn(),
      setNote: vi.fn(async (_id: string, note: string | null) => ({ ...tea, note: note ?? undefined })),
      removeFile: vi.fn(async () => undefined),
    };
    render(<ParcelsProvider repo={repo}><Note /></ParcelsProvider>);
    expect(await screen.findByText('no note 1 files')).toBeVisible();
    const user = userEvent.setup();
    await user.click(screen.getByRole('button', { name: 'Save' }));
    expect(await screen.findByText('Door code 1 files')).toBeVisible();
    await user.click(screen.getByRole('button', { name: 'Remove' }));
    expect(await screen.findByText('Door code 0 files')).toBeVisible();
    expect(repo.removeFile).toHaveBeenCalledWith('tea', 'box', { keepalive: true });

    vi.mocked(repo.setNote!).mockRejectedValueOnce(new Error('Broken'));
    await user.click(screen.getByRole('button', { name: 'Save' }));
    await waitFor(() => expect(repo.setNote).toHaveBeenCalledTimes(2));
    expect(screen.getByText('no error')).toBeVisible();
    vi.mocked(repo.setNote!).mockRejectedValueOnce(new ApiAuthenticationError());
    await user.click(screen.getByRole('button', { name: 'Save' }));
    expect(await screen.findByText('error')).toBeVisible();
  });
});
