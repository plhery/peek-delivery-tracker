import { render, screen, waitFor, within } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import { afterEach, describe, expect, it, vi } from 'vitest';
import type { ApiAddByEmail, ApiAddByEmailResponse } from '../generated/apiContract';
import { closeAddByEmail, openAddByEmail, readAddByEmail } from '../lib/addByEmail';
import { gmailFilter } from '../lib/emailFilter';
import { AccountMenu } from './AccountMenu';
import { AddByEmailPage } from './AddByEmail';

vi.mock('../lib/addByEmail', () => ({ readAddByEmail: vi.fn(), openAddByEmail: vi.fn(), closeAddByEmail: vi.fn() }));
vi.mock('./NotificationControl', () => ({ NotificationControl: () => null }));

const apiAuth = { userId: 'user-a', getAccessToken: async () => 'token' };
const inbox: ApiAddByEmail = {
  address: 'add+abcdefghijklmnop@in.example.com', createdAt: '2026-11-03T10:00:00.000Z', usedToday: 0,
  lastMailAt: null, lastResult: null, lastAdded: null, confirmation: null,
};
const offer = (value: ApiAddByEmail | null): ApiAddByEmailResponse => ({ inbox: value, dailyLimit: 30 });

afterEach(() => vi.clearAllMocks());

describe('adding parcels by email in Settings', () => {
  it('shows no row while the server offers the account no address', async () => {
    vi.mocked(readAddByEmail).mockResolvedValue(null);
    const user = userEvent.setup();
    render(<AccountMenu email="owner@example.test" apiAuth={apiAuth} />);
    await user.click(screen.getByLabelText('Account options for owner@example.test'));
    await waitFor(() => expect(readAddByEmail).toHaveBeenCalled());
    expect(screen.queryByRole('button', { name: 'Add parcels by email' })).not.toBeInTheDocument();
  });

  it('makes the address on request, and copies it', async () => {
    vi.mocked(readAddByEmail).mockResolvedValue(offer(null));
    vi.mocked(openAddByEmail).mockResolvedValue(offer(inbox));
    const copy = vi.spyOn(navigator.clipboard, 'writeText').mockResolvedValue();
    const user = userEvent.setup();
    render(<AccountMenu email="owner@example.test" apiAuth={apiAuth} />);
    await user.click(screen.getByLabelText('Account options for owner@example.test'));
    await user.click(await screen.findByRole('button', { name: 'Add parcels by email' }));
    expect(screen.getByRole('heading', { name: 'Add parcels by email' })).toBeInTheDocument();
    await user.click(screen.getByRole('button', { name: 'Get my address' }));
    expect(openAddByEmail).toHaveBeenCalledWith(apiAuth);
    expect(await screen.findByText(inbox.address)).toBeInTheDocument();
    await user.click(screen.getByRole('button', { name: 'Copy address' }));
    expect(copy).toHaveBeenCalledWith(inbox.address);
    expect(screen.getByRole('button', { name: 'Copied' })).toBeInTheDocument();
  });

  it('offers the filter for the languages the reader picks, and copies it', async () => {
    vi.spyOn(navigator, 'languages', 'get').mockReturnValue(['fr-CH', 'en']);
    const copy = vi.spyOn(navigator.clipboard, 'writeText').mockResolvedValue();
    const user = userEvent.setup();
    render(<AddByEmailPage apiAuth={apiAuth} offer={offer(inbox)} onChange={vi.fn()} />);
    const languages = screen.getByRole('group', { name: 'Languages your shops and carriers write in' });
    const chosen = () => within(languages).getAllByRole('button', { pressed: true }).map((button) => button.textContent);
    expect(chosen()).toEqual(['English', 'French']);
    expect(screen.getByText(/^subject:\(shipped OR .* OR "en transit"\)$/)).toBeInTheDocument();

    await user.click(within(languages).getByRole('button', { name: 'German' }));
    await user.click(within(languages).getByRole('button', { name: 'English' }));
    expect(chosen()).toEqual(['German', 'French']);
    await user.click(screen.getByRole('button', { name: 'Copy filter' }));
    expect(copy).toHaveBeenCalledWith(gmailFilter(['de', 'fr']));
    expect(screen.getByRole('button', { name: 'Copied' })).toBeInTheDocument();

    await user.click(within(languages).getByRole('button', { name: 'German' }));
    await user.click(within(languages).getByRole('button', { name: 'French' }));
    expect(chosen()).toEqual(['French']);
    expect(screen.getByRole('button', { name: 'Copy filter' })).toBeInTheDocument();
  });

  it('shows Gmail\'s confirmation and the last email, renews only once confirmed, and turns off', async () => {
    const onChange = vi.fn();
    const shown: ApiAddByEmail = {
      ...inbox, usedToday: 3, lastMailAt: new Date().toISOString(), lastResult: 'added', lastAdded: 2,
      confirmation: { code: '123456789', link: 'https://mail-settings.google.com/mail/vf-example', receivedAt: new Date().toISOString() },
    };
    vi.mocked(openAddByEmail).mockResolvedValue(offer({ ...inbox, address: 'add+qrstuvwxyz234567@in.example.com' }));
    vi.mocked(closeAddByEmail).mockResolvedValue();
    const user = userEvent.setup();
    render(<AddByEmailPage apiAuth={apiAuth} offer={offer(shown)} onChange={onChange} />);
    expect(screen.getByText('Enter this code in Gmail’s forwarding settings: 123456789')).toBeInTheDocument();
    expect(screen.getByRole('link', { name: /Gmail’s confirmation link/ })).toHaveAttribute('href', shown.confirmation!.link);
    expect(screen.getByText('2 parcels added')).toBeInTheDocument();
    expect(screen.getByText('3 of 30 emails today')).toBeInTheDocument();

    await user.click(screen.getByRole('button', { name: 'Get a new address' }));
    expect(openAddByEmail).not.toHaveBeenCalled();
    expect(screen.getByText('The current address stops working at once.')).toBeInTheDocument();
    await user.click(screen.getByRole('button', { name: 'Get a new address' }));
    expect(openAddByEmail).toHaveBeenCalledWith(apiAuth, true);

    await user.click(screen.getByRole('button', { name: 'Turn off' }));
    expect(closeAddByEmail).toHaveBeenCalledWith(apiAuth);
    expect(onChange).toHaveBeenLastCalledWith(offer(null));
  });
});
