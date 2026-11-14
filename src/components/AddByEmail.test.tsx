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
  lastMailAt: null, lastResult: null, lastAdded: null, recent: [], confirmation: null,
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
    expect(screen.queryByRole('button', { name: /Add parcels by email/ })).not.toBeInTheDocument();
  });

  it('makes the address on request, and copies it', async () => {
    vi.mocked(readAddByEmail).mockResolvedValue(offer(null));
    vi.mocked(openAddByEmail).mockResolvedValue(offer(inbox));
    const copy = vi.spyOn(navigator.clipboard, 'writeText').mockResolvedValue();
    const user = userEvent.setup();
    render(<AccountMenu email="owner@example.test" apiAuth={apiAuth} />);
    await user.click(screen.getByLabelText('Account options for owner@example.test'));
    await user.click(await screen.findByRole('button', { name: /^Add parcels by email ?Beta$/ }));
    expect(screen.getByRole('heading', { name: 'Add parcels by email Beta' })).toBeInTheDocument();
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
    // Forwarding by a filter is folded away: one email forwarded by hand needs none of it.
    expect(screen.getByText('Forward automatically').closest('details')).not.toHaveAttribute('open');
    expect(screen.queryByRole('heading', { name: 'Recent emails' })).not.toBeInTheDocument();
    await user.click(screen.getByText('Forward automatically'));
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

  it('shows Gmail\'s confirmation and what the last emails did, renews only once confirmed, and turns off', async () => {
    const onChange = vi.fn();
    const now = Date.now();
    const shown: ApiAddByEmail = {
      ...inbox, usedToday: 15, lastMailAt: new Date(now).toISOString(), lastResult: 'added', lastAdded: 2,
      recent: [
        { at: new Date(now).toISOString(), sender: 'Example Shop', result: 'added', added: 2 },
        { at: new Date(now - 60_000).toISOString(), sender: 'Other Shop', result: 'none', added: 0 },
        { at: new Date(now - 120_000).toISOString(), sender: '', result: 'limited', added: 0 },
        ...Array.from({ length: 4 }, (_, index) => ({ at: new Date(now - 3_600_000 * (index + 1)).toISOString(), sender: `Old Shop ${index}`, result: 'known' as const, added: 0 })),
      ],
      confirmation: { code: '123456789', link: 'https://mail-settings.google.com/mail/vf-example', receivedAt: new Date().toISOString() },
    };
    vi.mocked(openAddByEmail).mockResolvedValue(offer({ ...inbox, address: 'add+qrstuvwxyz234567@in.example.com' }));
    vi.mocked(closeAddByEmail).mockResolvedValue();
    const user = userEvent.setup();
    render(<AddByEmailPage apiAuth={apiAuth} offer={offer(shown)} onChange={onChange} />);
    expect(screen.getByText('Enter this code in Gmail’s forwarding settings: 123456789')).toBeInTheDocument();
    expect(screen.getByRole('link', { name: /Gmail’s confirmation link/ })).toHaveAttribute('href', shown.confirmation!.link);
    // The steps open by themselves while Gmail's confirmation waits.
    expect(screen.getByText('Forward automatically').closest('details')).toHaveAttribute('open');
    const recent = within(screen.getByRole('region', { name: 'Recent emails' }));
    expect(recent.getAllByRole('listitem').map((item) => item.querySelector('.add-by-email__what')?.textContent)).toEqual([
      'Example Shop2 parcels added', 'Other ShopNo tracking number in it', 'Not read: today’s limit is reached', 'Old Shop 0Already in your deliveries', 'Old Shop 1Already in your deliveries',
    ]);
    expect(screen.getByText('Some shops, like Amazon, put no tracking number in their emails.')).toBeInTheDocument();
    expect(screen.getByText('15 of 30 emails today')).toBeInTheDocument();

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
