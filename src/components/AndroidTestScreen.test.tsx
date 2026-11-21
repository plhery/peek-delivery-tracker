import { render, screen, within } from '@testing-library/react';
import { PEEK_SITE, renderOnPeek } from '../test/site';
import userEvent from '@testing-library/user-event';
import { beforeEach, describe, expect, it, vi } from 'vitest';
import fr from '../../shared/locales/fr.json';
import { guideAddresses } from '../guides/guideAddresses';
import { ANDROID_TEST_STORAGE_KEY } from '../lib/androidTest';
import { SiteProvider } from '../SiteContext';
import { AndroidTestScreen } from './AndroidTestScreen';

const mocks = vi.hoisted(() => ({ track: vi.fn(), screen: vi.fn(), start: vi.fn(async () => undefined) }));
vi.mock('../lib/analytics', async (original) => ({
  ...await original<typeof import('../lib/analytics')>(), trackAction: mocks.track, trackScreen: mocks.screen, startAnalytics: mocks.start,
}));

const stored = () => JSON.parse(localStorage.getItem(ANDROID_TEST_STORAGE_KEY) ?? '{}');
const steps = () => within(screen.getByRole('list')).getAllByRole('listitem');

beforeEach(() => {
  localStorage.clear();
  mocks.track.mockReset();
  mocks.screen.mockReset();
  mocks.start.mockClear();
});

describe('the steps page', () => {
  it('walks through the three steps, each in a new tab, the next one leading, the last two locked until the group', async () => {
    const user = userEvent.setup();
    renderOnPeek(<AndroidTestScreen />);
    expect(screen.getByRole('heading', { level: 1, name: 'Try Peek for Android' })).toBeVisible();
    expect(mocks.screen).toHaveBeenCalledExactlyOnceWith('android-test', 'anonymous');
    expect(mocks.start).toHaveBeenCalledExactlyOnceWith({ open: false });

    const group = within(steps()[0]).getByRole('link');
    expect([group.textContent, group.getAttribute('href'), group.getAttribute('target'), group.className])
      .toEqual(['Join the group', 'https://groups.google.com/g/peek-android-testers', '_blank', 'button button--primary']);
    for (const step of steps().slice(1)) {
      expect(within(step).queryByRole('link')).not.toBeInTheDocument();
      expect(within(step).getByRole('button', { name: 'Join the group first' })).toBeDisabled();
    }
    group.addEventListener('click', (event) => event.preventDefault());

    await user.click(group);
    expect(within(steps()[0]).getByText('Done')).toHaveClass('sr-only');
    const links = steps().map((step) => within(step).getByRole('link'));
    expect(links.slice(1).map((link) => [link.textContent, link.getAttribute('href'), link.getAttribute('target'), link.className])).toEqual([
      ['Open Google Play', 'https://play.google.com/apps/testing/com.plhery.peekdeliverytracker', '_blank', 'button button--primary'],
      ['Install Peek', 'https://play.google.com/store/apps/details?id=com.plhery.peekdeliverytracker', '_blank', 'button button--secondary'],
    ]);
    for (const link of links) link.addEventListener('click', (event) => event.preventDefault());
    await user.click(links[1]);
    await user.click(links[2]);
    expect(steps().map((step) => within(step).getByRole('link').className)).toEqual(Array(3).fill('button button--secondary'));
    expect(stored().steps).toEqual({ group: true, optIn: true, install: true });
    expect(mocks.track.mock.calls).toEqual([['android-test-group'], ['android-test-opt-in'], ['android-test-install']]);
  });

  it('locks the last two steps again for a browser that took them without the group', () => {
    localStorage.setItem(ANDROID_TEST_STORAGE_KEY, JSON.stringify({ steps: { optIn: true, install: true } }));
    renderOnPeek(<AndroidTestScreen />);
    expect(within(steps()[0]).getByRole('link')).toHaveClass('button--primary');
    for (const step of steps().slice(1)) {
      expect(within(step).queryByText('Done')).not.toBeInTheDocument();
      expect(within(step).getByRole('button', { name: 'Join the group first' })).toBeDisabled();
    }
  });

  it('asks no more on “Don’t ask me again”, and says so where the button was', async () => {
    const user = userEvent.setup();
    renderOnPeek(<AndroidTestScreen />);
    await user.click(screen.getByRole('button', { name: 'Don’t ask me again' }));
    expect(stored().never).toBe(true);
    expect(mocks.track).toHaveBeenCalledExactlyOnceWith('android-test-never');
    expect(screen.queryByRole('button', { name: 'Don’t ask me again' })).not.toBeInTheDocument();
    expect(screen.getByText('We won’t ask again in this browser.')).toHaveFocus();
  });

  it('offers the same app without Google Play: F-Droid’s address to copy, or GitHub', async () => {
    const user = userEvent.setup();
    const writeText = vi.spyOn(navigator.clipboard, 'writeText').mockResolvedValue();
    renderOnPeek(<AndroidTestScreen />);
    const other = screen.getByRole('region', { name: 'Without Google Play' });
    const address = 'https://raw.githubusercontent.com/plhery/peek-delivery-tracker/fdroid/repo?fingerprint=2BA268AF2C76EFB2C262DA7CA8499284B7CC7D0F76DCBE47A8ABA5703067F1A7';
    expect(within(other).getByText(address)).toHaveAttribute('translate', 'no');
    await user.click(within(other).getByRole('button', { name: 'Copy the address' }));
    expect(writeText).toHaveBeenCalledExactlyOnceWith(address);
    expect(within(other).getByRole('button', { name: 'Copied' })).toBeVisible();
    const github = within(other).getByRole('link', { name: 'Open GitHub' });
    expect([github.getAttribute('href'), github.getAttribute('target')]).toEqual(['https://github.com/plhery/peek-delivery-tracker/releases', '_blank']);
    github.addEventListener('click', (event) => event.preventDefault());
    await user.click(github);
    expect(mocks.track.mock.calls).toEqual([['android-test-fdroid'], ['android-test-github']]);
    // Nothing here counts as a step of the test.
    expect(stored()).toEqual({});
    writeText.mockRestore();
  });

  it('leaves out the ways around Google Play that the site names none of', () => {
    const app = { ...PEEK_SITE.android!, fdroid: null };
    const { unmount } = render(<AndroidTestScreen />, { wrapper: ({ children }) => <SiteProvider site={{ ...PEEK_SITE, android: app }}>{children}</SiteProvider> });
    const other = screen.getByRole('region', { name: 'Without Google Play' });
    expect(within(other).queryByRole('button', { name: 'Copy the address' })).toBeNull();
    expect(within(other).getByRole('link', { name: 'Open GitHub' })).toBeVisible();
    unmount();
    render(<AndroidTestScreen />, { wrapper: ({ children }) => <SiteProvider site={{ ...PEEK_SITE, android: { ...app, releases: null } }}>{children}</SiteProvider> });
    expect(screen.queryByRole('region', { name: 'Without Google Play' })).toBeNull();
    expect(steps()).toHaveLength(3);
  });

  it('goes back to the home page from anywhere else', () => {
    renderOnPeek(<AndroidTestScreen />);
    expect(screen.getByRole('link', { name: 'Back' })).toHaveAttribute('href', '/');
  });

  it('links the app’s guide, in the visitor’s language', () => {
    const { unmount } = renderOnPeek(<AndroidTestScreen guide={guideAddresses('android-app')} />);
    expect(screen.getByRole('link', { name: /^Peek for Android/ })).toHaveAttribute('href', '/guides/parcel-tracker-app-android');
    unmount();
    renderOnPeek(<AndroidTestScreen guide={guideAddresses('android-app')} initialLocale="fr" initialMessages={fr} />);
    expect(screen.getByRole('link', { name: /^Peek pour Android/ })).toHaveAttribute('href', '/fr/guides/application-suivi-colis-android');
  });

  it('speaks the visitor’s language', () => {
    renderOnPeek(<AndroidTestScreen initialLocale="fr" initialMessages={fr} />);
    expect(screen.getByRole('heading', { level: 1, name: fr['androidTest.page.title'] })).toBeVisible();
    expect(within(steps()[1]).getByRole('button')).toHaveTextContent(fr['androidTest.step.locked']);
  });
});
