import { render, screen, within } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import { beforeEach, describe, expect, it, vi } from 'vitest';
import fr from '../../shared/locales/fr.json';
import { ANDROID_TEST_STORAGE_KEY } from '../lib/androidTest';
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
  it('walks through the three steps, each in a new tab, the next one leading', async () => {
    const user = userEvent.setup();
    render(<AndroidTestScreen />);
    expect(screen.getByRole('heading', { level: 1, name: 'Try Peek for Android' })).toBeVisible();
    expect(mocks.screen).toHaveBeenCalledExactlyOnceWith('android-test', 'anonymous');
    expect(mocks.start).toHaveBeenCalledExactlyOnceWith({ open: false });

    const links = steps().map((step) => within(step).getByRole('link'));
    expect(links.map((link) => [link.textContent, link.getAttribute('href'), link.getAttribute('target'), link.className])).toEqual([
      ['Join the group', 'https://groups.google.com/g/peek-android-testers', '_blank', 'button button--primary'],
      ['Open Google Play', 'https://play.google.com/apps/testing/com.plhery.peekdeliverytracker', '_blank', 'button button--secondary'],
      ['Install Peek', 'https://play.google.com/store/apps/details?id=com.plhery.peekdeliverytracker', '_blank', 'button button--secondary'],
    ]);
    for (const link of links) link.addEventListener('click', (event) => event.preventDefault());

    await user.click(links[0]);
    expect(within(steps()[0]).getByText('Done')).toHaveClass('sr-only');
    expect(within(steps()[1]).getByRole('link')).toHaveClass('button--primary');
    await user.click(links[1]);
    await user.click(links[2]);
    expect(steps().map((step) => within(step).getByRole('link').className)).toEqual(Array(3).fill('button button--secondary'));
    expect(stored().steps).toEqual({ group: true, optIn: true, install: true });
    expect(mocks.track.mock.calls).toEqual([['android-test-group'], ['android-test-opt-in'], ['android-test-install']]);
  });

  it('asks no more on “Don’t ask me again”, and says so where the button was', async () => {
    const user = userEvent.setup();
    render(<AndroidTestScreen />);
    await user.click(screen.getByRole('button', { name: 'Don’t ask me again' }));
    expect(stored().never).toBe(true);
    expect(mocks.track).toHaveBeenCalledExactlyOnceWith('android-test-never');
    expect(screen.queryByRole('button', { name: 'Don’t ask me again' })).not.toBeInTheDocument();
    expect(screen.getByText('We won’t ask again in this browser.')).toHaveFocus();
  });

  it('goes back to the home page from anywhere else', () => {
    render(<AndroidTestScreen />);
    expect(screen.getByRole('link', { name: 'Back' })).toHaveAttribute('href', '/');
  });

  it('speaks the visitor’s language', () => {
    render(<AndroidTestScreen initialLocale="fr" initialMessages={fr} />);
    expect(screen.getByRole('heading', { level: 1, name: fr['androidTest.page.title'] })).toBeVisible();
    expect(within(steps()[1]).getByRole('link')).toHaveTextContent(fr['androidTest.step.optIn.action']);
  });
});
