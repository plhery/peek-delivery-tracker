import { render, screen, waitFor } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import { describe, expect, it, vi } from 'vitest';
import { ApiAuthenticationError } from '../lib/apiClient';
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
    expect(repo.markSeen).toHaveBeenCalledWith('tea');
    answer('2026-11-15T10:20:00.000Z');
    expect(await screen.findByText('tea 2026-11-15T10:20:00.000Z')).toBeVisible();
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
