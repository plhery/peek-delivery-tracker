import { render, screen, within } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import { afterEach, beforeEach, describe, expect, it, vi, type MockInstance } from 'vitest';
import {
  disablePushNotifications,
  enablePushNotifications,
  getNotificationPreferences,
  inspectPushState,
  saveNotificationPreferences,
} from '../lib/pushNotifications';
import { NotificationControl } from './NotificationControl';
import { notificationInvitationDismissed } from '../lib/notificationInvitation';
import { IPHONE_LINKEDIN, IPHONE_SAFARI_27 } from '../test/alertBrowser';

const mocks = vi.hoisted(() => ({ track: vi.fn() }));
vi.mock('../lib/analytics', async (original) => ({ ...await original<typeof import('../lib/analytics')>(), trackAction: mocks.track }));

vi.mock('../lib/pushNotifications', () => ({
  inspectPushState: vi.fn(),
  enablePushNotifications: vi.fn(),
  disablePushNotifications: vi.fn(),
  getNotificationPreferences: vi.fn(),
  saveNotificationPreferences: vi.fn(),
  ALL_NOTIFICATION_STAGES: [
    'registered', 'accepted', 'in_transit', 'customs', 'out_for_delivery',
    'failed_attempt', 'ready_for_pickup', 'delivered', 'returned',
  ],
  IMPORTANT_NOTIFICATION_STAGES: [
    'customs', 'out_for_delivery', 'failed_attempt', 'ready_for_pickup',
    'delivered', 'returned',
  ],
  DELIVERY_DAY_NOTIFICATION_STAGES: ['out_for_delivery', 'delivered'],
}));

let agent: MockInstance | undefined;
function browse(userAgent: string) {
  agent = vi.spyOn(navigator, 'userAgent', 'get').mockReturnValue(userAgent);
}
// A link followed in a test would leave the page.
const stay = (event: Event) => event.preventDefault();

afterEach(() => {
  agent?.mockRestore();
  agent = undefined;
  document.removeEventListener('click', stay, true);
});

beforeEach(() => {
  mocks.track.mockReset();
  vi.mocked(inspectPushState).mockReset();
  vi.mocked(enablePushNotifications).mockReset();
  vi.mocked(disablePushNotifications).mockReset();
  vi.mocked(getNotificationPreferences).mockReset().mockResolvedValue({
    enabledStages: ['out_for_delivery', 'delivered'],
    quietHoursStart: null,
    quietHoursEnd: null,
    timezone: 'Europe/Zurich',
  });
  vi.mocked(saveNotificationPreferences).mockReset().mockImplementation(
    async (preferences) => preferences,
  );
});

describe('NotificationControl', () => {
  it('enables alerts and shows the configured cadence', async () => {
    vi.mocked(inspectPushState).mockResolvedValue({ kind: 'prompt', publicKey: 'public' });
    vi.mocked(enablePushNotifications).mockResolvedValue(true);
    const apiAuth = {
      userId: 'user-1',
      getAccessToken: vi.fn().mockResolvedValue('token'),
    };
    const user = userEvent.setup();
    render(<NotificationControl apiAuth={apiAuth} />);

    await user.click(screen.getByRole('button', { name: 'Notifications' }));
    expect(await screen.findByText(/every 10 minutes from 08:00 to 22:00/i)).toBeInTheDocument();
    await user.click(screen.getByRole('switch', { name: 'Notifications' }));
    expect(inspectPushState).toHaveBeenCalledWith(apiAuth);
    expect(enablePushNotifications).toHaveBeenCalledWith('public', apiAuth, 'en');
    expect(await screen.findByText("You’re all set for parcel updates")).toBeInTheDocument();
    expect(notificationInvitationDismissed(apiAuth.userId)).toBe(true);
  });

  it('turns alerts off for only this device', async () => {
    vi.mocked(inspectPushState)
      .mockResolvedValueOnce({ kind: 'enabled', publicKey: 'public' })
      .mockResolvedValueOnce({ kind: 'prompt', publicKey: 'public' });
    vi.mocked(disablePushNotifications).mockResolvedValue();
    const user = userEvent.setup();
    render(<NotificationControl />);
    await user.click(await screen.findByRole('button', { name: 'Notifications enabled' }));
    await user.click(screen.getByRole('switch', { name: 'Notifications' }));
    expect(disablePushNotifications).toHaveBeenCalled();
    expect(await screen.findByText(/Know when your parcel needs you/i)).toBeInTheDocument();
  });

  it('remembers an explicit switch-off even if server cleanup fails', async () => {
    vi.mocked(inspectPushState).mockResolvedValue({ kind: 'enabled', publicKey: 'public' });
    vi.mocked(disablePushNotifications).mockRejectedValue(new Error('offline'));
    const apiAuth = { userId: 'user-1', getAccessToken: vi.fn() };
    const user = userEvent.setup();
    render(<NotificationControl apiAuth={apiAuth} />);
    await user.click(await screen.findByRole('button', { name: 'Notifications enabled' }));
    await user.click(screen.getByRole('switch', { name: 'Notifications' }));
    expect(notificationInvitationDismissed(apiAuth.userId)).toBe(true);
    expect(await screen.findByRole('alert')).toBeInTheDocument();
  });

  it('says a browser without notifications can’t show them, wherever it runs', async () => {
    vi.mocked(inspectPushState).mockResolvedValue({ kind: 'unsupported' });
    const user = userEvent.setup();
    render(<NotificationControl />);
    await user.click(screen.getByRole('button', { name: 'Notifications' }));
    expect(await screen.findByText('This browser can’t show notifications. Try another browser, or the Peek app on iPhone.')).toBeInTheDocument();
    expect(screen.queryByText('Notifications in the Peek app')).toBeNull();
  });

  describe('in Safari on an iPhone or iPad, outside the Home Screen', () => {
    const apiAuth = { userId: 'user-1', getAccessToken: vi.fn().mockResolvedValue('token') };
    async function open() {
      vi.mocked(inspectPushState).mockResolvedValue({ kind: 'install' });
      const user = userEvent.setup();
      render(<NotificationControl apiAuth={apiAuth} />);
      await user.click(screen.getByRole('button', { name: 'Notifications' }));
      const sheet = screen.getByRole('dialog', { name: 'Notifications' });
      await within(sheet).findByText('Notifications in the Peek app');
      return { user, sheet };
    }

    it('offers the Peek app signed in to the account, then the Home Screen with its steps a tap away', async () => {
      browse(IPHONE_SAFARI_27);
      const { user, sheet } = await open();
      expect(within(sheet).getByText('Know when your parcel needs you')).toBeVisible();
      expect(within(sheet).queryByText(/add Peek to your Home Screen, then open it there/)).toBeNull();
      expect(within(sheet).getByText('Sign in with this account. Your parcels come along.')).toBeVisible();
      expect(within(sheet).queryByRole('switch', { name: 'Notifications' })).toBeNull();
      expect(sheet.querySelector('.notification-app-qr')).toBeNull();
      // The choices still show, for every device of the account.
      expect(await within(sheet).findByRole('group', { name: 'Which updates? On all your devices' })).toBeVisible();

      const get = within(sheet).getByRole('link', { name: 'Get' });
      expect(new URL(get.getAttribute('href')!).searchParams.get('ct')).toBe('web-settings');
      document.addEventListener('click', stay, true);
      await user.click(get);
      expect(mocks.track).toHaveBeenCalledWith('notifications-app-get');

      expect(within(sheet).getByText('Or put Peek on your Home Screen.')).toBeVisible();
      expect(within(sheet).queryByRole('list')).toBeNull();
      await user.click(within(sheet).getByRole('button', { name: 'Show the steps' }));
      expect(within(sheet).getAllByRole('listitem')).toHaveLength(4);
      expect(within(sheet).getByText('Open Peek from your Home Screen and turn on notifications')).toBeVisible();
      expect(within(sheet).queryByRole('button', { name: 'Show the steps' })).toBeNull();
    });

    it('keeps the way to Safari in an app’s own browser, after the app', async () => {
      browse(IPHONE_LINKEDIN);
      const { sheet } = await open();
      expect(within(sheet).getByText('Notifications in the Peek app').compareDocumentPosition(within(sheet).getByText('Notifications need Safari.'))
        & Node.DOCUMENT_POSITION_FOLLOWING).toBeTruthy();
      expect(within(sheet).queryByText('Or put Peek on your Home Screen.')).toBeNull();
    });
  });

  it.each(['prompt', 'enabled'] as const)('when %s, says the choices are for every device and shows a computer the app’s code under the switch', async (kind) => {
    vi.mocked(inspectPushState).mockResolvedValue({ kind, publicKey: 'public' });
    const apiAuth = { userId: 'user-1', getAccessToken: vi.fn().mockResolvedValue('token') };
    const user = userEvent.setup();
    render(<NotificationControl apiAuth={apiAuth} />);
    await user.click(await screen.findByRole('button', { name: kind === 'enabled' ? 'Notifications enabled' : 'Notifications' }));
    expect(await screen.findByRole('group', { name: 'Which updates? On all your devices' })).toBeVisible();
    // The row is drawn here and shown by the stylesheet on a computer only, as the landing's code is.
    const code = screen.getByText('On your iPhone too').closest('.notification-app-qr');
    expect(code?.querySelector('img')).toHaveAttribute('src', '/settings/app-store-qr.svg');
    expect(within(code as HTMLElement).getByText('Get the Peek app and sign in with this account.')).toBeInTheDocument();
    expect(screen.getByRole('switch', { name: 'Notifications' }).closest('.settings-device-control')?.nextElementSibling).toBe(code);
    expect(screen.queryByText('Notifications in the Peek app')).toBeNull();
  });

  it('saves event presets and clears the retired quiet hours', async () => {
    vi.mocked(getNotificationPreferences).mockResolvedValue({
      enabledStages: ['out_for_delivery', 'delivered'], quietHoursStart: '22:00', quietHoursEnd: '08:00', timezone: 'Europe/Zurich',
    });
    vi.mocked(inspectPushState).mockResolvedValue({ kind: 'enabled', publicKey: 'public' });
    const apiAuth = {
      userId: 'user-1',
      getAccessToken: vi.fn().mockResolvedValue('token'),
    };
    const user = userEvent.setup();
    render(<NotificationControl apiAuth={apiAuth} />);

    await user.click(await screen.findByRole('button', { name: 'Notifications enabled' }));
    await user.click(await screen.findByRole('radio', { name: /important steps/i }));
    expect(screen.queryByRole('checkbox', { name: /quiet hours/i })).not.toBeInTheDocument();
    await user.click(screen.getByRole('button', { name: 'Save preferences' }));

    expect(saveNotificationPreferences).toHaveBeenCalledWith({
      enabledStages: [
        'customs', 'out_for_delivery', 'failed_attempt', 'ready_for_pickup',
        'delivered', 'returned',
      ],
      quietHoursStart: null,
      quietHoursEnd: null,
      timezone: expect.any(String),
    }, apiAuth);
    expect(await screen.findByText('Preferences saved')).toBeInTheDocument();
  });
});
