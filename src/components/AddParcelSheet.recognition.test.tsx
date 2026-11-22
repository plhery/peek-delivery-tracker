import { act, render, screen, waitFor } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import { afterEach, expect, it, vi } from 'vitest';
import { AddParcelSheet } from './AddParcelSheet';
import { lookupCarrier } from '../lib/carrierDetection';

vi.mock('../lib/carrierDetection', () => ({ lookupCarrier: vi.fn() }));
afterEach(() => { vi.resetAllMocks(); vi.restoreAllMocks(); });

it('replaces an earlier confirmation with the current recheck and its failure', async () => {
  const trackingNumber = '00000000000001';
  let rejectRecheck!: (error: Error) => void;
  const recheck = new Promise<Awaited<ReturnType<typeof lookupCarrier>>>((_resolve, reject) => {
    rejectRecheck = reject;
  });
  vi.mocked(lookupCarrier)
    .mockResolvedValueOnce({ trackingNumber, carrier: 'seur', asked: ['seur'] })
    .mockReturnValueOnce(recheck);
  const onAdd = vi.fn().mockResolvedValue(undefined);
  const apiAuth = { userId: 'test-user', getAccessToken: async () => 'test-token' };
  const user = userEvent.setup();
  render(<AddParcelSheet apiAuth={apiAuth} onAdd={onAdd} onClose={vi.fn()} initialTrackingInput={trackingNumber} />);

  expect(await screen.findByRole('button', { name: /^SEUR has this parcel/ })).toBeInTheDocument();
  const input = screen.getByRole('textbox', { name: /tracking number/i });
  await user.clear(input);
  await user.type(input, 'X');
  await user.clear(input);
  await user.type(input, trackingNumber);

  await waitFor(() => expect(lookupCarrier).toHaveBeenCalledTimes(2));
  expect(screen.getByRole('button', { name: /^Detect automatically Checking tracking services/ })).toBeInTheDocument();
  expect(screen.queryByRole('button', { name: /^SEUR has this parcel/ })).not.toBeInTheDocument();

  await act(async () => { rejectRecheck(new Error('Unavailable')); });
  expect(await screen.findByText('couldn’t check · Peek will retry after you add it')).toBeInTheDocument();
  expect(screen.queryByRole('button', { name: /^SEUR has this parcel/ })).not.toBeInTheDocument();
  const add = screen.getByRole('button', { name: /^add parcel$/i });
  expect(add).toBeEnabled();
  await user.click(add);
  expect(onAdd).toHaveBeenCalledWith(expect.objectContaining({ trackingNumber, carrier: 'unknown' }));
});

it('names the country the carriers were asked for, and asks again for another the reader picks for this number', async () => {
  vi.spyOn(Intl.DateTimeFormat.prototype, 'resolvedOptions')
    .mockReturnValue({ ...new Intl.DateTimeFormat().resolvedOptions(), timeZone: 'Europe/Zurich' });
  const trackingNumber = '00000000000001';
  vi.mocked(lookupCarrier).mockImplementation(async (_typed, _auth, _signal, country) =>
    ({ trackingNumber, carrier: 'unknown', asked: ['seur'], ...(country ? { lookupCountry: country } : {}) }));
  const onAdd = vi.fn().mockResolvedValue(undefined);
  const apiAuth = { userId: 'test-user', getAccessToken: async () => 'test-token' };
  const user = userEvent.setup();
  render(<AddParcelSheet apiAuth={apiAuth} onAdd={onAdd} onClose={vi.fn()} initialTrackingInput={trackingNumber} />);

  expect(await screen.findByRole('button', { name: 'Detect automatically Switzerland: not found yet · Peek keeps checking Change' })).toBeInTheDocument();
  expect(lookupCarrier).toHaveBeenLastCalledWith(trackingNumber, apiAuth, expect.anything(), 'CH');
  const country = screen.getByRole('combobox', { name: 'Country to look the number up for' });
  expect(country).toHaveValue('CH');

  await user.selectOptions(country, 'FR');
  await waitFor(() => expect(lookupCarrier).toHaveBeenLastCalledWith(trackingNumber, apiAuth, expect.anything(), 'FR'));
  expect(await screen.findByRole('button', { name: 'Detect automatically France: not found yet · Peek keeps checking Change' })).toBeInTheDocument();
  await user.click(screen.getByRole('button', { name: /^add parcel$/i }));
  expect(onAdd).toHaveBeenCalledWith(expect.objectContaining({ trackingNumber, lookupCountry: 'FR' }));
});

it('names the country the server took when the browser clock names none, and leaves an unpicked one to the server', async () => {
  const trackingNumber = '00000000000001';
  vi.mocked(lookupCarrier).mockResolvedValue({ trackingNumber, carrier: 'unknown', asked: ['seur'], lookupCountry: 'CA' });
  const onAdd = vi.fn().mockResolvedValue(undefined);
  const apiAuth = { userId: 'test-user', getAccessToken: async () => 'test-token' };
  const user = userEvent.setup();
  render(<AddParcelSheet apiAuth={apiAuth} onAdd={onAdd} onClose={vi.fn()} initialTrackingInput={trackingNumber} />);

  expect(await screen.findByRole('button', { name: 'Detect automatically Canada: not found yet · Peek keeps checking Change' })).toBeInTheDocument();
  expect(lookupCarrier).toHaveBeenCalledWith(trackingNumber, apiAuth, expect.anything(), null);
  await user.click(screen.getByRole('button', { name: /^add parcel$/i }));
  expect(onAdd).toHaveBeenCalledWith(expect.objectContaining({ trackingNumber, lookupCountry: undefined }));
});
