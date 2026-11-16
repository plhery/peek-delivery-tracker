import { fireEvent, render, screen, waitFor, within } from '@testing-library/react';
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

  it('reads the offer again when the reader comes back to the tab, so Gmail\'s confirmation shows', async () => {
    vi.mocked(readAddByEmail).mockResolvedValue(offer(inbox));
    const user = userEvent.setup();
    render(<AccountMenu email="owner@example.test" apiAuth={apiAuth} />);
    await user.click(screen.getByLabelText('Account options for owner@example.test'));
    await waitFor(() => expect(readAddByEmail).toHaveBeenCalledTimes(1));
    vi.useFakeTimers({ toFake: ['Date'] });
    vi.setSystemTime(Date.now() + 5_000);
    fireEvent(document, new Event('visibilitychange'));
    // The window's focus tells of the same return: one more read, not two.
    fireEvent(window, new Event('focus'));
    vi.useRealTimers();
    await waitFor(() => expect(readAddByEmail).toHaveBeenCalledTimes(2));
  });

  it('makes the address on request, and copies it', async () => {
    vi.mocked(readAddByEmail).mockResolvedValue(offer(null));
    vi.mocked(openAddByEmail).mockResolvedValue(offer(inbox));
    const copy = vi.spyOn(navigator.clipboard, 'writeText').mockResolvedValue();
    const user = userEvent.setup();
    render(<AccountMenu email="owner@example.test" apiAuth={apiAuth} />);
    await user.click(screen.getByLabelText('Account options for owner@example.test'));
    await user.click(await screen.findByRole('button', { name: /^Add parcels by email ?Experimental$/ }));
    expect(screen.getByRole('heading', { name: 'Add parcels by email Experimental' })).toBeInTheDocument();
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
    await user.click(screen.getByRole('button', { name: 'Copy search' }));
    expect(copy).toHaveBeenCalledWith(gmailFilter(['de', 'fr']));
    expect(screen.getByRole('button', { name: 'Copied' })).toBeInTheDocument();

    await user.click(within(languages).getByRole('button', { name: 'German' }));
    await user.click(within(languages).getByRole('button', { name: 'French' }));
    expect(chosen()).toEqual(['French']);
    expect(screen.getByRole('button', { name: 'Copy search' })).toBeInTheDocument();
  });

  it('walks through Gmail in its order: the address confirmed, forwarding of all mail left off, then the filter', async () => {
    vi.spyOn(navigator, 'languages', 'get').mockReturnValue(['fr-CH', 'en']);
    const user = userEvent.setup();
    render(<AddByEmailPage apiAuth={apiAuth} email="owner@example.test" offer={offer(inbox)} onChange={vi.fn()} />);
    await user.click(screen.getByText('Forward automatically'));
    // An address of a domain no service owns starts on Gmail.
    const services = screen.getByRole('group', { name: 'Your mail service' });
    expect(within(services).getAllByRole('button').map((button) => button.textContent)).toEqual(['Gmail', 'Outlook', 'iCloud', 'Yahoo', 'Other']);
    expect(within(services).getByRole('button', { pressed: true })).toHaveTextContent('Gmail');
    const steps = screen.getAllByRole('listitem');
    expect(steps).toHaveLength(6);
    expect(within(steps[0]!).getByRole('link', { name: /Open Gmail’s forwarding settings/ })).toHaveAttribute('href', 'https://mail.google.com/mail/#settings/fwdandpop');
    // Gmail's own button names stand out, and its options button, which has no name on screen, is drawn.
    expect(steps[0]).toHaveTextContent('click the Settings gear at the top right, then See all settings › Forwarding and POP/IMAP.');
    expect(within(steps[0]!).getAllByText('Add a forwarding address').map((name) => name.tagName)).toEqual(['STRONG', 'STRONG']);
    // Where the button isn't: a phone, another account, or an administrator who turned forwarding off.
    expect(within(steps[0]!).getByText(/^No/).closest('p')).toHaveTextContent(
      'No Add a forwarding address button? Check that you’re on a computer, in the account that gets your shipping emails: your picture at the top right switches accounts. On a work or school account, the administrator may have turned forwarding off: ask them, or forward your shipping emails by hand.');
    expect(steps[1]).toHaveTextContent(/confirmation link to your Peek address. It shows up below/);
    expect(within(steps[2]!).getByText('Disable forwarding').tagName).toBe('STRONG');
    expect(steps[3]).toHaveTextContent(/^Choose the languages your shops write in, then copy the search below/);
    expect(steps[4]!.querySelector('svg.add-by-email__glyph')).not.toBeNull();
    expect(steps[4]).toHaveTextContent('Paste the search into Has the words, then click Create filter at the bottom.');
    expect(steps[5]).toHaveTextContent(/^Select Forward it, choose your Peek address/);
    expect(screen.queryByText(/\*\*|\{\{|\[\[/)).not.toBeInTheDocument();
    // No link fills Gmail's filter: the search is copied and pasted.
    expect(screen.getAllByRole('link')).toHaveLength(1);
    expect(within(steps[3]!).getByText(gmailFilter(['en', 'fr']))).toBeInTheDocument();
    await user.click(screen.getByRole('button', { name: 'German' }));
    expect(within(steps[3]!).getByText(gmailFilter(['en', 'de', 'fr']))).toBeInTheDocument();
  });

  it('starts on the service the account\'s address belongs to, and gives a rule its words one at a time', async () => {
    vi.spyOn(navigator, 'languages', 'get').mockReturnValue(['fr-CH', 'en']);
    const copy = vi.spyOn(navigator.clipboard, 'writeText').mockResolvedValue();
    const user = userEvent.setup();
    render(<AddByEmailPage apiAuth={apiAuth} email="owner@hotmail.fr" offer={offer(inbox)} onChange={vi.fn()} />);
    await user.click(screen.getByText('Forward automatically'));
    const services = screen.getByRole('group', { name: 'Your mail service' });
    expect(within(services).getByRole('button', { pressed: true })).toHaveTextContent('Outlook');
    expect(screen.getByRole('link', { name: /Open Outlook’s rules/ })).toHaveAttribute('href', 'https://outlook.live.com/mail/0/options/mail/rules');
    expect(screen.getByText(/^A work or school account opens Outlook at outlook.office.com instead/)).toHaveTextContent(/organization may block forwarding to outside addresses/);
    expect(screen.queryByRole('button', { name: 'Copy search' })).not.toBeInTheDocument();
    const words = screen.getByRole('group', { name: 'Words to look for in the subject' });
    // Each language's first word comes before any second one.
    expect(within(words).getAllByRole('button').map((button) => button.textContent).slice(0, 4)).toEqual(['shipped', 'colis', 'shipment', 'livraison']);
    expect(screen.getByText('Tap a word to copy it.')).toBeInTheDocument();
    await user.click(within(words).getByRole('button', { name: 'colis' }));
    expect(copy).toHaveBeenCalledWith('colis');
    expect(screen.getByRole('status')).toHaveTextContent('Copied');

    await user.click(within(services).getByRole('button', { name: 'iCloud' }));
    expect(screen.getByRole('link', { name: /Open iCloud Mail/ })).toHaveAttribute('href', 'https://www.icloud.com/mail');
    expect(screen.getByText('Access iCloud Data on the Web').tagName).toBe('STRONG');
    expect(screen.getByText(/Each rule looks for one word/)).toBeInTheDocument();

    // Yahoo forwards no chosen email: it says so and asks for nothing else.
    await user.click(within(services).getByRole('button', { name: 'Yahoo' }));
    expect(screen.getByText(/Yahoo Mail’s filters can only move emails into folders/)).toBeInTheDocument();
    expect(screen.queryByRole('group', { name: 'Languages your shops and carriers write in' })).not.toBeInTheDocument();
    expect(screen.queryByRole('listitem')).not.toBeInTheDocument();

    await user.click(within(services).getByRole('button', { name: 'Other' }));
    expect(screen.getByText(/like Proton, first send the address an invitation/)).toBeInTheDocument();
    expect(within(screen.getByRole('group', { name: 'Words to look for in the subject' })).getAllByRole('button').length).toBeGreaterThan(5);
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
    render(<AddByEmailPage apiAuth={apiAuth} email="owner@outlook.com" offer={offer(shown)} onChange={onChange} />);
    // The steps open by themselves while Gmail's confirmation waits, on Gmail whatever the account's address, with the
    // confirmation in the step that asks for it.
    expect(screen.getByText('Forward automatically').closest('details')).toHaveAttribute('open');
    expect(within(screen.getByRole('group', { name: 'Your mail service' })).getByRole('button', { pressed: true })).toHaveTextContent('Gmail');
    const confirming = within(within(screen.getByText('Forward automatically').closest('details')!).getAllByRole('listitem')[1]!);
    expect(confirming.getByText('If Gmail asks for a code, enter this one: 123456789')).toBeInTheDocument();
    expect(confirming.getByRole('link', { name: /Gmail’s confirmation link/ })).toHaveAttribute('href', shown.confirmation!.link);
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
