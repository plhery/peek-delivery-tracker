import { render, screen, within } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import { afterEach, describe, expect, it, vi } from 'vitest';
import { I18nProvider } from '../i18n';
import { APPEARANCE_STORAGE_KEY } from '../lib/appearance';
import { MARK_NEW_UPDATES_STORAGE_KEY } from '../lib/newUpdate';
import { SHOW_PEEK_AI_STORAGE_KEY } from '../lib/peekAi';
import { GuestSettings } from './GuestSettings';

afterEach(() => {
  window.localStorage.clear();
  vi.unstubAllGlobals();
});

describe('GuestSettings', () => {
  it('keeps a visitor’s appearance, units, new-update mark and Peek AI in this browser, without asking the server', async () => {
    const fetch = vi.fn();
    vi.stubGlobal('fetch', fetch);
    const user = userEvent.setup();
    render(<I18nProvider initialLocale="en"><GuestSettings /></I18nProvider>);
    const trigger = screen.getByRole('button', { name: 'Settings' });
    expect(trigger).toHaveAttribute('aria-haspopup', 'dialog');
    await user.click(trigger);
    const sheet = screen.getByRole('dialog', { name: 'Settings' });
    expect(within(sheet).getByText('Kept in this browser only.')).toBeInTheDocument();
    // No language here: the page's own footer holds it.
    expect(within(sheet).queryByRole('combobox', { name: 'Language' })).not.toBeInTheDocument();

    await user.click(within(sheet).getByRole('button', { name: /Dark/ }));
    expect(window.localStorage.getItem(APPEARANCE_STORAGE_KEY)).toBe('dark');
    await user.selectOptions(within(sheet).getByRole('combobox', { name: 'Time format' }), '24');
    expect(window.localStorage.getItem('deliveryTrackerClock')).toBe('24');
    await user.selectOptions(within(sheet).getByRole('combobox', { name: 'Distance' }), 'mi');
    expect(window.localStorage.getItem('deliveryTrackerDistanceUnit')).toBe('mi');
    const weight = within(sheet).getByRole('combobox', { name: 'Weight' });
    // Miles leave weights as they were, which the browser decides here.
    expect(weight).toHaveValue('auto');
    await user.selectOptions(weight, 'lb');
    expect(window.localStorage.getItem('deliveryTrackerWeightUnit')).toBe('lb');
    const mark = within(sheet).getByRole('switch', { name: 'Dot on updated parcels' });
    await user.click(mark);
    expect(mark).not.toBeChecked();
    expect(window.localStorage.getItem(MARK_NEW_UPDATES_STORAGE_KEY)).toBe('off');
    const peekAi = within(sheet).getByRole('switch', { name: 'Peek AI' });
    expect(peekAi).toBeChecked();
    expect(peekAi).toHaveAccessibleDescription('When Peek has a strong hint, it says when it thinks a parcel will come.');
    await user.click(peekAi);
    expect(peekAi).not.toBeChecked();
    expect(window.localStorage.getItem(SHOW_PEEK_AI_STORAGE_KEY)).toBe('off');
    // Usage statistics have no switch: the dot and Peek AI are the only ones.
    expect(within(sheet).getAllByRole('switch')).toEqual([mark, peekAi]);
    expect(within(sheet).queryByText(/usage statistics/i)).not.toBeInTheDocument();
    expect(fetch).not.toHaveBeenCalled();

    await user.click(within(sheet).getByRole('button', { name: 'Close' }));
    await vi.waitFor(() => expect(screen.queryByRole('dialog')).not.toBeInTheDocument());
  });

  it('offers the language on a page whose footer has none, a parcel\'s', async () => {
    const user = userEvent.setup();
    render(<I18nProvider initialLocale="en"><GuestSettings language /></I18nProvider>);
    await user.click(screen.getByRole('button', { name: 'Settings' }));
    await user.selectOptions(within(screen.getByRole('dialog')).getByRole('combobox', { name: 'Language' }), 'fr');
    expect(await screen.findByRole('dialog', { name: 'Réglages' })).toBeVisible();
  });
});
