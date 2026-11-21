import { act, render, screen, waitFor } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import type { ReactElement } from 'react';
import { beforeEach, describe, expect, it, vi } from 'vitest';
import { ANDROID_TEST_SNOOZE_MS, ANDROID_TEST_STORAGE_KEY } from '../lib/androidTest';
import type { Site } from '../lib/site';

const mocks = vi.hoisted(() => ({ device: vi.fn(async () => true), track: vi.fn() }));
vi.mock('../lib/androidTest', async (original) => ({
  ...await original<typeof import('../lib/androidTest')>(),
  readAndroidDevice: mocks.device,
}));
vi.mock('../lib/analytics', async (original) => ({ ...await original<typeof import('../lib/analytics')>(), trackAction: mocks.track }));

let fresh: { site: typeof import('../test/site'); context: typeof import('../SiteContext') };

/** A fresh page load: the device is asked again and every place counts its first showing again. */
async function load() {
  vi.resetModules();
  fresh = { site: await import('../test/site'), context: await import('../SiteContext') };
  return import('./AndroidTest');
}

/** Renders on Peek's own site, or on `site`, as the page just loaded knows it. */
function show(ui: ReactElement, site?: Site) {
  if (!site) return fresh.site.renderOnPeek(ui);
  const { SiteProvider } = fresh.context;
  return render(<SiteProvider site={site}>{ui}</SiteProvider>);
}
const stored = () => JSON.parse(localStorage.getItem(ANDROID_TEST_STORAGE_KEY) ?? '{}');
const events = () => mocks.track.mock.calls.map(([name, , spot]) => [name, spot]);

beforeEach(() => {
  localStorage.clear();
  mocks.device.mockReset().mockResolvedValue(true);
  mocks.track.mockReset();
});

describe('the parcel page’s row', () => {
  it('invites an Android visitor to the steps, and counts it shown once per page load', async () => {
    const { AndroidTestRow } = await load();
    const view = show(<AndroidTestRow />);
    const link = await screen.findByRole('link', { name: 'Try our Android app Get it before anyone else' });
    expect(link).toHaveAttribute('href', '/android');
    view.rerender(<><AndroidTestRow /></>);
    view.unmount();
    show(<AndroidTestRow />);
    await screen.findByRole('link', { name: /Try our Android app/ });
    await userEvent.click(screen.getByRole('link', { name: /Try our Android app/ }));
    expect(events()).toEqual([['android-test-view', 'parcel'], ['android-test-open', 'parcel']]);
    expect(mocks.device).toHaveBeenCalledTimes(1);
  });

  it('hides it for a week on ×, says so, and offers never to show it again', async () => {
    const user = userEvent.setup();
    const { AndroidTestRow } = await load();
    show(<AndroidTestRow />);
    const before = Date.now();
    await user.click(await screen.findByRole('button', { name: 'Not now' }));
    expect(screen.queryByRole('link', { name: /Try our Android app/ })).not.toBeInTheDocument();
    expect(screen.getByRole('status')).toHaveTextContent('Hidden for a week');
    expect(stored().hiddenUntil).toBeGreaterThanOrEqual(before + ANDROID_TEST_SNOOZE_MS);
    await user.click(screen.getByRole('button', { name: 'Never show' }));
    expect(stored().never).toBe(true);
    expect(screen.queryByRole('status')).not.toBeInTheDocument();
    expect(events()).toEqual([['android-test-view', 'parcel'], ['android-test-dismiss', 'parcel'], ['android-test-never', 'parcel']]);
  });

  it('lets the toast go by itself', async () => {
    vi.useFakeTimers({ shouldAdvanceTime: true });
    try {
      const { AndroidTestRow } = await load();
      show(<AndroidTestRow />);
      (await screen.findByRole('button', { name: 'Not now' })).click();
      expect(await screen.findByRole('status')).toBeInTheDocument();
      await act(async () => { vi.advanceTimersByTime(6_000); });
      expect(screen.queryByRole('status')).not.toBeInTheDocument();
    } finally { vi.useRealTimers(); }
  });

  it('shows nothing without a testers’ group or an Android app, without asking the browser anything', async () => {
    const { AndroidTestRow } = await load();
    const { PEEK_SITE } = fresh.site;
    for (const android of [{ ...PEEK_SITE.android!, testGroup: null }, null]) {
      const { container, unmount } = show(<AndroidTestRow />, { ...PEEK_SITE, android });
      await act(async () => { await Promise.resolve(); });
      expect(container).toBeEmptyDOMElement();
      unmount();
    }
    expect(mocks.device).not.toHaveBeenCalled();
    expect(mocks.track).not.toHaveBeenCalled();
  });

  it('shows nothing on a device that can’t install the app, after never, or within the week', async () => {
    mocks.device.mockResolvedValue(false);
    let { AndroidTestRow } = await load();
    let view = show(<AndroidTestRow />);
    await waitFor(() => expect(mocks.device).toHaveBeenCalled());
    await act(async () => { await Promise.resolve(); });
    expect(view.container).toBeEmptyDOMElement();
    view.unmount();

    mocks.device.mockResolvedValue(true);
    for (const note of [{ never: true }, { hiddenUntil: Date.now() + 60_000 }]) {
      localStorage.setItem(ANDROID_TEST_STORAGE_KEY, JSON.stringify(note));
      ({ AndroidTestRow } = await load());
      view = show(<AndroidTestRow />);
      await act(async () => { await Promise.resolve(); });
      expect(view.container).toBeEmptyDOMElement();
      view.unmount();
    }
    expect(mocks.track).not.toHaveBeenCalled();
  });

  it('thanks after the install link, on every page load until it is closed', async () => {
    const user = userEvent.setup();
    localStorage.setItem(ANDROID_TEST_STORAGE_KEY, JSON.stringify({ steps: { group: true, optIn: true, install: true } }));
    let { AndroidTestRow } = await load();
    const view = show(<AndroidTestRow />);
    expect(await screen.findByText('Thanks for testing Peek for Android')).toBeVisible();
    expect(screen.getByText('Keep it two weeks: that opens it to everyone.')).toBeVisible();
    expect(stored().thanked).toEqual(expect.any(Number));
    view.unmount();

    // The next page load: still there, until closed.
    ({ AndroidTestRow } = await load());
    show(<AndroidTestRow />);
    await user.click(await screen.findByRole('button', { name: 'Close' }));
    expect(screen.queryByText('Thanks for testing Peek for Android')).not.toBeInTheDocument();
    expect(stored().thanksClosed).toBe(true);
    expect(mocks.track).not.toHaveBeenCalled();
  });

  it('leads from the thanks back to the steps, which start over', async () => {
    localStorage.setItem(ANDROID_TEST_STORAGE_KEY, JSON.stringify({
      steps: { group: true, optIn: true, install: true }, thanked: Date.now() - 60_000, hiddenUntil: 5,
    }));
    const { AndroidTestRow } = await load();
    show(<AndroidTestRow />);
    const link = await screen.findByRole('link', { name: 'Didn’t work? See the steps' });
    expect(link).toHaveAttribute('href', '/android');
    link.addEventListener('click', (event) => event.preventDefault());
    await userEvent.click(link);
    expect(stored()).toEqual({ hiddenUntil: 5 });
    expect(events()).toEqual([['android-test-open', 'parcel']]);
  });

  it('never shows in a browser the app itself opened a page in', async () => {
    Object.defineProperty(document, 'referrer', { value: 'android-app://com.plhery.peekdeliverytracker/', configurable: true });
    try {
      const { AndroidTestRow } = await load();
      show(<AndroidTestRow />);
      await waitFor(() => expect(stored().app).toBe(true));
      expect(screen.queryByRole('link')).not.toBeInTheDocument();
    } finally {
      Object.defineProperty(document, 'referrer', { value: '', configurable: true });
    }
  });
});

describe('the “Notify me” row', () => {
  it('offers the app early, with no × and no thanks', async () => {
    const { AndroidTestOffer } = await load();
    show(<AndroidTestOffer />);
    expect(await screen.findByText('Peek for Android')).toBeVisible();
    expect(screen.getByText('Try our Android app before anyone else.')).toBeVisible();
    await userEvent.click(screen.getByRole('link', { name: 'Get it early' }));
    expect(screen.getByRole('link', { name: 'Get it early' })).toHaveAttribute('href', '/android');
    expect(screen.queryByRole('button')).not.toBeInTheDocument();
    expect(events()).toEqual([['android-test-view', 'alerts'], ['android-test-open', 'alerts']]);
  });

  it('goes with the rest once the install link was followed', async () => {
    localStorage.setItem(ANDROID_TEST_STORAGE_KEY, JSON.stringify({ steps: { group: true, install: true } }));
    const { AndroidTestOffer } = await load();
    const { container } = show(<AndroidTestOffer />);
    await act(async () => { await Promise.resolve(); });
    expect(container).toBeEmptyDOMElement();
  });
});

describe('the home page’s banner', () => {
  it('invites, hides for a week on ×, and thanks in its place', async () => {
    const user = userEvent.setup();
    let { AndroidTestBanner } = await load();
    const view = show(<AndroidTestBanner />);
    const link = await screen.findByRole('link', { name: 'Get the Android app early Join' });
    expect(link).toHaveAttribute('href', '/android');
    await user.click(screen.getByRole('button', { name: 'Not now' }));
    expect(screen.getByRole('status')).toHaveTextContent('Hidden for a week');
    expect(events()).toEqual([['android-test-view', 'home'], ['android-test-dismiss', 'home']]);
    view.unmount();

    localStorage.setItem(ANDROID_TEST_STORAGE_KEY, JSON.stringify({ steps: { group: true, install: true } }));
    ({ AndroidTestBanner } = await load());
    show(<AndroidTestBanner />);
    expect(await screen.findByText('Thanks for testing Peek for Android')).toBeVisible();
    expect(screen.queryByText('Keep it two weeks: that opens it to everyone.')).not.toBeInTheDocument();
    expect(screen.getByRole('link', { name: 'Didn’t work? See the steps' })).toHaveAttribute('href', '/android');
  });
});
