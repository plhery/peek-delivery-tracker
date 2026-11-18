import { render, screen, waitFor, within } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import { beforeEach, describe, expect, it, vi } from 'vitest';
import App from './App';
import { I18nProvider } from './i18n';
import { MARK_NEW_UPDATES_STORAGE_KEY } from './lib/newUpdate';
import { getNotificationPreferences, inspectPushState, saveNotificationPreferences } from './lib/pushNotifications';
import { createDemoRepo } from './store/demoRepo';
import { ParcelsProvider } from './store/ParcelsContext';
import type { ParcelRepo, ParcelWithEvents } from './types';

vi.mock('./lib/pushNotifications', async (original) => ({
  ...await original<typeof import('./lib/pushNotifications')>(),
  inspectPushState: vi.fn(),
  getNotificationPreferences: vi.fn(),
  saveNotificationPreferences: vi.fn(),
}));

const EMAIL_ON = {
  enabledStages: ['out_for_delivery', 'delivered'] as const, quietHoursStart: null, quietHoursEnd: null, timezone: 'Europe/Zurich',
  emailOnDelivery: true, emailAvailable: true,
};
const session = () => ({ userId: 'account', getAccessToken: vi.fn().mockResolvedValue('token') });

/** An account's deliveries: the demo's parcels behind the API's interface, with the email mute the server has. */
function accountRepo() {
  const demo = createDemoRepo(localStorage);
  const muted = new Set<string>();
  const withEmail = (parcel: ParcelWithEvents): ParcelWithEvents => ({ ...parcel, emailMuted: muted.has(parcel.id) });
  const setEmailMuted = vi.fn(async (id: string, value: boolean) => {
    if (value) muted.add(id); else muted.delete(id);
    return withEmail((await demo.list()).find((parcel) => parcel.id === id)!);
  });
  const repo: ParcelRepo = { ...demo, mode: 'api', list: async () => (await demo.list()).map(withEmail), setEmailMuted };
  return { repo, setEmailMuted };
}

beforeEach(() => {
  localStorage.clear();
  window.history.replaceState({}, '', '/');
  vi.mocked(inspectPushState).mockReset().mockResolvedValue({ kind: 'unsupported' });
  vi.mocked(getNotificationPreferences).mockReset().mockResolvedValue({ ...EMAIL_ON, enabledStages: [...EMAIL_ON.enabledStages] });
  // The server answers with what it kept: the request, over what was stored.
  vi.mocked(saveNotificationPreferences).mockReset().mockImplementation(async (preferences) => ({
    ...await vi.mocked(getNotificationPreferences).mock.results.at(-1)?.value, ...preferences,
  }));
});

describe('the delivery email in the deliveries', () => {
  it('reads the preferences once per sign-in and mutes the email for one parcel from its bell', async () => {
    const { repo, setEmailMuted } = accountRepo();
    const apiAuth = session();
    const user = userEvent.setup();
    render(<ParcelsProvider repo={repo}><App apiAuth={apiAuth} accountEmail="alex@example.com" /></ParcelsProvider>);

    await user.click(await screen.findByText('Belgian chocolate 🍫'));
    const detail = screen.getByRole('dialog', { name: 'Belgian chocolate 🍫' });
    await user.click(await within(detail).findByRole('button', { name: 'Notifications for this parcel' }));
    const sheet = screen.getByRole('dialog', { name: 'Notifications for Belgian chocolate 🍫' });
    expect(within(sheet).getByRole('switch', { name: 'Notifications' })).toHaveAccessibleDescription('Delivery only, as in Settings');
    const email = within(sheet).getByRole('switch', { name: 'Email when it arrives' });
    expect(email).toHaveAccessibleDescription('To alex@example.com');
    await user.click(email);
    await waitFor(() => expect(email).not.toBeChecked());
    const [id, muted] = setEmailMuted.mock.calls[0];
    expect(muted).toBe(true);
    expect((await repo.list()).find((parcel) => parcel.id === id)).toMatchObject({ label: 'Belgian chocolate 🍫', emailMuted: true });
    await user.click(within(sheet).getByRole('button', { name: 'Done' }));
    await user.click(within(detail).getByRole('button', { name: /back/i }));

    // Another parcel: its bell knows the account's choice without asking the server again.
    await user.click(await screen.findByText('Moon lamp 🌙'));
    expect(await within(screen.getByRole('dialog', { name: 'Moon lamp 🌙' })).findByRole('button', { name: 'Notifications for this parcel' })).toBeVisible();
    expect(getNotificationPreferences).toHaveBeenCalledExactlyOnceWith(apiAuth);
  });

  it('offers the email on a delivered parcel of an account that never chose', async () => {
    vi.mocked(getNotificationPreferences).mockResolvedValue({ ...EMAIL_ON, enabledStages: [...EMAIL_ON.enabledStages], emailOnDelivery: null });
    const user = userEvent.setup();
    render(<ParcelsProvider repo={accountRepo().repo}><App apiAuth={session()} accountEmail="alex@example.com" /></ParcelsProvider>);
    await user.click(await screen.findByText('Coffee beans ☕'));
    const detail = screen.getByRole('dialog', { name: 'Coffee beans ☕' });
    expect(await within(detail).findByRole('region', { name: 'An email when the next one arrives?' })).toBeVisible();
    // A delivered parcel has nothing left to announce: its bell is the plain mute.
    expect(within(detail).getByRole('button', { name: 'Turn off parcel notifications' })).toBeVisible();
  });

  it('tells the account how the app writes a time and a distance, for the email to write them the same way', async () => {
    localStorage.setItem('deliveryTrackerClock', '12');
    localStorage.setItem('deliveryTrackerDistanceUnit', 'mi');
    const apiAuth = session();
    render(<I18nProvider initialLocale="en"><ParcelsProvider repo={accountRepo().repo}><App apiAuth={apiAuth} accountEmail="alex@example.com" /></ParcelsProvider></I18nProvider>);
    await waitFor(() => expect(saveNotificationPreferences).toHaveBeenCalledOnce());
    // The events go as the account has them, and its email choice stays as it is.
    expect(saveNotificationPreferences).toHaveBeenCalledWith({
      clock: '12', distance: 'mi',
    }, apiAuth);
    expect(await screen.findByText('Belgian chocolate 🍫')).toBeVisible();
    expect(saveNotificationPreferences).toHaveBeenCalledOnce();
  });

  it('asks nothing of an account that already writes them as the app does', async () => {
    localStorage.setItem('deliveryTrackerClock', '24');
    localStorage.setItem('deliveryTrackerDistanceUnit', 'km');
    vi.mocked(getNotificationPreferences).mockResolvedValue({ ...EMAIL_ON, enabledStages: [...EMAIL_ON.enabledStages], clock: '24', distance: 'km' });
    render(<I18nProvider initialLocale="en"><ParcelsProvider repo={accountRepo().repo}><App apiAuth={session()} accountEmail="alex@example.com" /></ParcelsProvider></I18nProvider>);
    expect(await screen.findByText('Belgian chocolate 🍫')).toBeVisible();
    await waitFor(() => expect(getNotificationPreferences).toHaveBeenCalledOnce());
    expect(saveNotificationPreferences).not.toHaveBeenCalled();
  });

  it('takes the account’s choice to mark new updates from its other devices without Settings opening', async () => {
    vi.mocked(getNotificationPreferences).mockResolvedValue({ ...EMAIL_ON, enabledStages: [...EMAIL_ON.enabledStages], markNewUpdates: false });
    render(<I18nProvider initialLocale="en"><ParcelsProvider repo={accountRepo().repo}><App apiAuth={session()} accountEmail="alex@example.com" /></ParcelsProvider></I18nProvider>);
    expect(await screen.findByText('Belgian chocolate 🍫')).toBeVisible();
    await waitFor(() => expect(localStorage.getItem(MARK_NEW_UPDATES_STORAGE_KEY)).toBe('off'));
    expect(screen.queryByRole('dialog')).toBeNull();
  });

  it('leaves the demo as it is: no preferences are read, the bell mutes, nothing is offered', async () => {
    const user = userEvent.setup();
    render(<ParcelsProvider repo={createDemoRepo(localStorage)}><App apiAuth={session()} accountEmail="alex@example.com" /></ParcelsProvider>);
    await user.click(await screen.findByText('Belgian chocolate 🍫'));
    const detail = screen.getByRole('dialog', { name: 'Belgian chocolate 🍫' });
    await user.click(within(detail).getByRole('button', { name: 'Turn off parcel notifications' }));
    expect(await within(detail).findByRole('button', { name: 'Turn on parcel notifications' })).toHaveAttribute('aria-pressed', 'true');
    await user.click(within(detail).getByRole('button', { name: /back/i }));
    await user.click(await screen.findByText('Coffee beans ☕'));
    expect(within(screen.getByRole('dialog', { name: 'Coffee beans ☕' })).queryByRole('region', { name: /next one arrives/ })).toBeNull();
    expect(getNotificationPreferences).not.toHaveBeenCalled();
  });
});
