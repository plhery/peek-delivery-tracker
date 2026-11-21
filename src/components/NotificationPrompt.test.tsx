import { act, fireEvent, render, screen, waitFor } from '@testing-library/react';
import { renderOnPeek } from '../test/site';
import userEvent from '@testing-library/user-event';
import { afterEach, beforeEach, describe, expect, it, vi } from 'vitest';
import { trackAction } from '../lib/analytics';
import { dismissNotificationInvitation, notificationInvitationDismissed } from '../lib/notificationInvitation';
import { enablePushNotifications, inspectPushState, type PushState } from '../lib/pushNotifications';
import { IPHONE_LINKEDIN, IPHONE_SAFARI_27 } from '../test/alertBrowser';
import { NotificationPrompt } from './NotificationPrompt';

vi.mock('../lib/analytics', async (original) => ({ ...await original<typeof import('../lib/analytics')>(), trackAction: vi.fn() }));
vi.mock('../lib/pushNotifications', () => ({
  inspectPushState: vi.fn(),
  enablePushNotifications: vi.fn(),
}));

const apiAuth = { userId: 'prompt-user', getAccessToken: vi.fn().mockResolvedValue('token') };
const prompt = { kind: 'prompt', publicKey: 'public' } as const;
const popup = () => screen.queryByRole('region', { name: 'Get delivery updates' });

beforeEach(() => {
  window.localStorage.clear();
  vi.mocked(inspectPushState).mockReset().mockResolvedValue(prompt);
  vi.mocked(enablePushNotifications).mockReset().mockResolvedValue(true);
  vi.mocked(trackAction).mockClear();
  vi.stubGlobal('Notification', { permission: 'default', requestPermission: vi.fn() });
});
afterEach(() => { vi.unstubAllGlobals(); vi.restoreAllMocks(); });

describe('NotificationPrompt', () => {
  it('waits for eligibility without requesting permission or taking focus', async () => {
    const { rerender } = renderOnPeek(<><button>Keep tracking</button><NotificationPrompt apiAuth={apiAuth} eligible={false} /></>);
    screen.getByRole('button', { name: 'Keep tracking' }).focus();
    expect(popup()).not.toBeInTheDocument();
    expect(inspectPushState).not.toHaveBeenCalled();
    rerender(<><button>Keep tracking</button><NotificationPrompt apiAuth={apiAuth} eligible /></>);
    expect(await screen.findByRole('region', { name: 'Get delivery updates' })).toBeInTheDocument();
    expect(screen.getByRole('button', { name: 'Keep tracking' })).toHaveFocus();
    expect(screen.queryByRole('dialog')).not.toBeInTheDocument();
    expect(enablePushNotifications).not.toHaveBeenCalled();
    expect(Notification.requestPermission).not.toHaveBeenCalled();
  });

  it.each<PushState>([
    { kind: 'enabled', publicKey: 'public' }, { kind: 'blocked' },
    { kind: 'unsupported' }, { kind: 'unavailable' },
  ])('stays quiet for $kind browsers', async (state) => {
    vi.mocked(inspectPushState).mockResolvedValue(state);
    renderOnPeek(<NotificationPrompt apiAuth={apiAuth} eligible />);
    await act(async () => {});
    expect(popup()).not.toBeInTheDocument();
    expect(enablePushNotifications).not.toHaveBeenCalled();
  });

  it('stays quiet when checking fails or dismissal cannot be stored', async () => {
    vi.mocked(inspectPushState).mockRejectedValue(new Error('offline'));
    const { unmount } = renderOnPeek(<NotificationPrompt apiAuth={apiAuth} eligible />);
    await act(async () => {});
    expect(popup()).not.toBeInTheDocument();
    unmount();
    vi.mocked(inspectPushState).mockClear();
    vi.spyOn(window.localStorage, 'getItem').mockImplementation(() => { throw new Error('blocked storage'); });
    renderOnPeek(<NotificationPrompt apiAuth={apiAuth} eligible />);
    expect(inspectPushState).not.toHaveBeenCalled();
    expect(popup()).not.toBeInTheDocument();
  });

  it.each(['Not now', 'Close'])('remembers %s across remounts without affecting another account', async (label) => {
    const user = userEvent.setup();
    const { unmount } = renderOnPeek(<NotificationPrompt apiAuth={apiAuth} eligible />);
    await user.click(await screen.findByRole('button', { name: label }));
    expect(notificationInvitationDismissed(apiAuth.userId)).toBe(true);
    unmount();
    const next = renderOnPeek(<NotificationPrompt apiAuth={apiAuth} eligible />);
    expect(popup()).not.toBeInTheDocument();
    next.unmount();
    renderOnPeek(<NotificationPrompt apiAuth={{ ...apiAuth, userId: 'other-user' }} eligible />);
    expect(await screen.findByRole('region', { name: 'Get delivery updates' })).toBeInTheDocument();
  });

  it('responds to a Settings dismissal and cross-tab storage changes', async () => {
    const { unmount } = renderOnPeek(<NotificationPrompt apiAuth={apiAuth} eligible />);
    await screen.findByRole('region', { name: 'Get delivery updates' });
    act(() => dismissNotificationInvitation(apiAuth.userId));
    expect(popup()).not.toBeInTheDocument();
    unmount();
    localStorage.clear();
    renderOnPeek(<NotificationPrompt apiAuth={apiAuth} eligible />);
    await screen.findByRole('region', { name: 'Get delivery updates' });
    localStorage.setItem(`deliveryTrackerNotificationInvitation:${apiAuth.userId}`, 'dismissed');
    fireEvent(window, new StorageEvent('storage'));
    expect(popup()).not.toBeInTheDocument();
  });

  it('enables once after a click, then closes even when the welcome alert was not sent', async () => {
    let finish!: (value: boolean) => void;
    vi.mocked(enablePushNotifications).mockImplementation(() => new Promise(resolve => { finish = resolve; }));
    const user = userEvent.setup();
    renderOnPeek(<NotificationPrompt apiAuth={apiAuth} eligible />);
    await user.click(await screen.findByRole('button', { name: 'Enable notifications' }));
    expect(enablePushNotifications).toHaveBeenCalledExactlyOnceWith('public', apiAuth, 'en');
    expect(screen.getByRole('button', { name: 'Enabling…' })).toBeDisabled();
    expect(screen.getByRole('button', { name: 'Not now' })).toBeDisabled();
    await act(async () => finish(false));
    expect(popup()).not.toBeInTheDocument();
    expect(notificationInvitationDismissed(apiAuth.userId)).toBe(true);
  });

  it.each(['default', 'denied'])('remembers browser permission %s without showing an error', async (permission) => {
    vi.stubGlobal('Notification', { permission });
    vi.mocked(enablePushNotifications).mockRejectedValue(new Error('Notifications were not allowed'));
    const user = userEvent.setup();
    renderOnPeek(<NotificationPrompt apiAuth={apiAuth} eligible />);
    await user.click(await screen.findByRole('button', { name: 'Enable notifications' }));
    expect(popup()).not.toBeInTheDocument();
    expect(screen.queryByRole('alert')).not.toBeInTheDocument();
    expect(notificationInvitationDismissed(apiAuth.userId)).toBe(true);
  });

  it('keeps a retry when permission was granted but registration failed', async () => {
    vi.stubGlobal('Notification', { permission: 'granted' });
    vi.mocked(enablePushNotifications).mockRejectedValueOnce(new Error('offline')).mockResolvedValue(true);
    const user = userEvent.setup();
    renderOnPeek(<NotificationPrompt apiAuth={apiAuth} eligible />);
    await user.click(await screen.findByRole('button', { name: 'Enable notifications' }));
    expect(await screen.findByRole('alert')).toHaveTextContent('Couldn’t turn on notifications');
    expect(notificationInvitationDismissed(apiAuth.userId)).toBe(false);
    await user.click(screen.getByRole('button', { name: 'Retry' }));
    expect(enablePushNotifications).toHaveBeenCalledTimes(2);
    expect(popup()).not.toBeInTheDocument();
  });

  it('offers the app first, then closes once its App Store link is followed', async () => {
    vi.mocked(inspectPushState).mockResolvedValue({ kind: 'install' });
    const user = userEvent.setup();
    renderOnPeek(<NotificationPrompt apiAuth={apiAuth} eligible />);
    const app = await screen.findByRole('link', { name: 'Download on my iPhone' });
    expect(new URL(app.getAttribute('href')!).searchParams.get('ct')).toBe('web-invite');
    expect(screen.getByText('Know when your parcel arrives.')).toBeVisible();
    expect(screen.getByRole('button', { name: 'Not now' })).toBeVisible();
    expect(screen.getByRole('button', { name: 'add Peek to your Home Screen' }).parentElement).toHaveTextContent('Or add Peek to your Home Screen');
    // jsdom follows no link to another site.
    document.addEventListener('click', (event) => event.preventDefault(), { once: true });
    await user.click(app);
    expect(trackAction).toHaveBeenCalledWith('notifications-app-get');
    await waitFor(() => expect(popup()).not.toBeInTheDocument());
    expect(notificationInvitationDismissed(apiAuth.userId)).toBe(true);
    expect(enablePushNotifications).not.toHaveBeenCalled();
  });

  it('names the iPad on an iPad, whose Safari says it is a Mac', async () => {
    vi.spyOn(navigator, 'platform', 'get').mockReturnValue('MacIntel');
    Object.defineProperty(navigator, 'maxTouchPoints', { configurable: true, get: () => 5 });
    try {
      vi.mocked(inspectPushState).mockResolvedValue({ kind: 'install' });
      renderOnPeek(<NotificationPrompt apiAuth={apiAuth} eligible />);
      expect(await screen.findByRole('link', { name: 'Download on my iPad' })).toBeVisible();
    } finally {
      delete (navigator as { maxTouchPoints?: number }).maxTouchPoints;
    }
  });

  it('provides Home Screen steps without requesting permission', async () => {
    vi.mocked(inspectPushState).mockResolvedValue({ kind: 'install' });
    const user = userEvent.setup();
    renderOnPeek(<NotificationPrompt apiAuth={apiAuth} eligible />);
    await user.click(await screen.findByRole('button', { name: 'add Peek to your Home Screen' }));
    expect(screen.getByRole('region', { name: 'Add to your Home Screen' })).toBeInTheDocument();
    expect(screen.getAllByRole('listitem').map((step) => step.textContent))
      .toEqual(['1Open your browser’s Share menu', '2Choose Add to Home Screen', '3Open Peek from your Home Screen and turn on notifications']);
    expect(screen.queryByRole('link', { name: 'Download on my iPhone' })).toBeNull();
    expect(enablePushNotifications).not.toHaveBeenCalled();
    expect(screen.getByRole('button', { name: 'Got it' })).toHaveFocus();
    await user.click(screen.getByRole('button', { name: 'Got it' }));
    expect(screen.queryByRole('region')).not.toBeInTheDocument();
  });

  it('gives the Home Screen steps at once on a site with no iPhone app', async () => {
    vi.mocked(inspectPushState).mockResolvedValue({ kind: 'install' });
    render(<NotificationPrompt apiAuth={apiAuth} eligible />);
    expect(await screen.findByRole('region', { name: 'Add to your Home Screen' })).toBeInTheDocument();
    expect(screen.getAllByRole('listitem')).toHaveLength(3);
    expect(screen.queryByRole('link')).toBeNull();
    expect(screen.queryByRole('button', { name: 'Not now' })).toBeNull();
    expect(screen.getByRole('button', { name: 'Got it' })).toBeVisible();
  });

  it('starts Safari 27 from the page menu of its address bar', async () => {
    vi.spyOn(navigator, 'userAgent', 'get').mockReturnValue(IPHONE_SAFARI_27);
    vi.mocked(inspectPushState).mockResolvedValue({ kind: 'install' });
    const user = userEvent.setup();
    renderOnPeek(<NotificationPrompt apiAuth={apiAuth} eligible />);
    await user.click(await screen.findByRole('button', { name: 'add Peek to your Home Screen' }));
    expect(screen.getAllByRole('listitem').map((step) => step.textContent)).toEqual([
      `1Tap Page Menu in Safari’s address bar${window.location.hostname}`,
      '2Tap Share',
      '3Tap View More, then Add to Home Screen',
      '4Open Peek from your Home Screen and turn on notifications',
    ]);
  });

  it('sends an app’s own browser to Safari instead of steps it cannot follow', async () => {
    vi.spyOn(navigator, 'userAgent', 'get').mockReturnValue(IPHONE_LINKEDIN);
    vi.mocked(inspectPushState).mockResolvedValue({ kind: 'install' });
    const user = userEvent.setup();
    renderOnPeek(<NotificationPrompt apiAuth={apiAuth} eligible />);
    await user.click(await screen.findByRole('button', { name: 'add Peek to your Home Screen' }));
    expect(screen.getByText('Notifications need Safari.')).toBeVisible();
    expect(screen.getByRole('link', { name: 'Open in Safari' })).toHaveAttribute('href', `x-safari-${window.location.href}`);
    expect(screen.queryByRole('listitem')).toBeNull();
  });

  it('rechecks permission on returning to the page and ignores stale results', async () => {
    let finish!: (state: PushState) => void;
    vi.mocked(inspectPushState).mockImplementationOnce(() => new Promise(resolve => { finish = resolve; }));
    const { rerender } = renderOnPeek(<NotificationPrompt apiAuth={apiAuth} eligible />);
    rerender(<NotificationPrompt apiAuth={apiAuth} eligible={false} />);
    await act(async () => finish(prompt));
    expect(popup()).not.toBeInTheDocument();
    rerender(<NotificationPrompt apiAuth={apiAuth} eligible />);
    await screen.findByRole('region', { name: 'Get delivery updates' });
    vi.mocked(inspectPushState).mockResolvedValue({ kind: 'blocked' });
    fireEvent.focus(window);
    await waitFor(() => expect(popup()).not.toBeInTheDocument());
  });
});
