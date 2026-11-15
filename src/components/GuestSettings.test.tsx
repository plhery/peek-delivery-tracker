import { render, screen, within } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import { afterEach, describe, expect, it, vi } from 'vitest';
import { I18nProvider } from '../i18n';
import { APPEARANCE_STORAGE_KEY } from '../lib/appearance';
import { MARK_NEW_UPDATES_STORAGE_KEY } from '../lib/newUpdate';
import { GuestSettings } from './GuestSettings';

afterEach(() => {
  window.localStorage.clear();
  vi.unstubAllGlobals();
});

describe('GuestSettings', () => {
  it('keeps a visitor’s appearance, units and new-update mark in this browser, without asking the server', async () => {
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
    await user.selectOptions(within(sheet).getByRole('combobox', { name: 'Distance units' }), 'mi');
    expect(window.localStorage.getItem('deliveryTrackerDistanceUnit')).toBe('mi');
    const mark = within(sheet).getByRole('switch', { name: 'Mark new updates' });
    await user.click(mark);
    expect(mark).not.toBeChecked();
    expect(window.localStorage.getItem(MARK_NEW_UPDATES_STORAGE_KEY)).toBe('off');
    expect(fetch).not.toHaveBeenCalled();

    await user.click(within(sheet).getByRole('button', { name: 'Close' }));
    await vi.waitFor(() => expect(screen.queryByRole('dialog')).not.toBeInTheDocument());
  });
});
