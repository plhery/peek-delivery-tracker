import { render, screen, waitFor } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import { describe, expect, it, vi } from 'vitest';
import { ApiAuthenticationError } from '../lib/apiClient';
import { ParcelsProvider, useParcels } from './ParcelsContext';
import type { ParcelRepo } from '../types';

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
