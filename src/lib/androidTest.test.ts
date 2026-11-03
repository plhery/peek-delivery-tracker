import { afterEach, beforeEach, describe, expect, it, vi } from 'vitest';
import {
  ANDROID_TEST_LINKS, ANDROID_TEST_SNOOZE_MS, ANDROID_TEST_STORAGE_KEY, androidTestShows, canInstallAndroidApp, closeAndroidTestThanks,
  neverAndroidTest, noteAndroidAppReferrer, noteAndroidTestStep, noteAndroidTestThanked, readAndroidDevice, readAndroidTestNote,
  snoozeAndroidTest, subscribeAndroidTest, type AndroidTestNote,
} from './androidTest';

/** Chrome's reduced user agent: every Android phone says Android 10. */
const CHROME_REDUCED = 'Mozilla/5.0 (Linux; Android 10; K) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/142.0.0.0 Mobile Safari/537.36';
const FIREFOX = (version: number) => `Mozilla/5.0 (Android ${version}; Mobile; rv:144.0) Gecko/144.0 Firefox/144.0`;
const WEBVIEW = 'Mozilla/5.0 (Linux; Android 10; K; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/142.0.0.0 Mobile Safari/537.36';
const IPHONE = 'Mozilla/5.0 (iPhone; CPU iPhone OS 26_1 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/26.1 Mobile/15E148 Safari/604.1';
const DESKTOP = 'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/142.0.0.0 Safari/537.36';

describe('who is invited', () => {
  it('reads the real Android version from the client hints, and invites Android 12 and later', () => {
    expect(canInstallAndroidApp({ userAgent: CHROME_REDUCED, platform: 'Android', platformVersion: '16.0.0' })).toBe(true);
    expect(canInstallAndroidApp({ userAgent: CHROME_REDUCED, platform: 'Android', platformVersion: '12.0.0' })).toBe(true);
    expect(canInstallAndroidApp({ userAgent: CHROME_REDUCED, platform: 'Android', platformVersion: '11.0.0' })).toBe(false);
    expect(canInstallAndroidApp({ userAgent: CHROME_REDUCED, platform: 'Android', platformVersion: '10.0.0' })).toBe(false);
  });

  it('without hints, invites Chrome’s Android 10, which could be any version, and believes any other version', () => {
    expect(canInstallAndroidApp({ userAgent: CHROME_REDUCED })).toBe(true);
    expect(canInstallAndroidApp({ userAgent: FIREFOX(14) })).toBe(true);
    expect(canInstallAndroidApp({ userAgent: FIREFOX(12) })).toBe(true);
    expect(canInstallAndroidApp({ userAgent: FIREFOX(11) })).toBe(false);
    expect(canInstallAndroidApp({ userAgent: FIREFOX(9) })).toBe(false);
    expect(canInstallAndroidApp({ userAgent: 'Mozilla/5.0 (Linux; Android; Mobile)' })).toBe(true);
    // Hints that say nothing leave it to the user agent.
    expect(canInstallAndroidApp({ userAgent: FIREFOX(9), platform: 'Android', platformVersion: '' })).toBe(false);
  });

  it('invites a phone asking for the desktop site, whose hints still say Android, but no app’s WebView, iPhone or computer', () => {
    expect(canInstallAndroidApp({ userAgent: DESKTOP, platform: 'Android', platformVersion: '15.0.0' })).toBe(true);
    expect(canInstallAndroidApp({ userAgent: WEBVIEW, platform: 'Android', platformVersion: '15.0.0' })).toBe(false);
    expect(canInstallAndroidApp({ userAgent: IPHONE })).toBe(false);
    expect(canInstallAndroidApp({ userAgent: DESKTOP, platform: 'Linux' })).toBe(false);
  });

  describe('in the browser', () => {
    afterEach(() => vi.unstubAllGlobals());

    it('asks Android’s browser for the platform version, and nobody else', async () => {
      const getHighEntropyValues = vi.fn(async () => ({ platformVersion: '11.0.0' }));
      vi.stubGlobal('navigator', { userAgent: CHROME_REDUCED, userAgentData: { platform: 'Android', getHighEntropyValues } });
      await expect(readAndroidDevice()).resolves.toBe(false);
      expect(getHighEntropyValues).toHaveBeenCalledExactlyOnceWith(['platformVersion']);

      getHighEntropyValues.mockClear();
      vi.stubGlobal('navigator', { userAgent: DESKTOP, userAgentData: { platform: 'macOS', getHighEntropyValues } });
      await expect(readAndroidDevice()).resolves.toBe(false);
      expect(getHighEntropyValues).not.toHaveBeenCalled();
    });

    it('falls back on the user agent where the hints fail or are missing', async () => {
      vi.stubGlobal('navigator', { userAgent: CHROME_REDUCED, userAgentData: { platform: 'Android', getHighEntropyValues: () => Promise.reject(new Error('no')) } });
      await expect(readAndroidDevice()).resolves.toBe(true);
      vi.stubGlobal('navigator', { userAgent: FIREFOX(11) });
      await expect(readAndroidDevice()).resolves.toBe(false);
    });
  });
});

describe('what a place shows', () => {
  const NOW = Date.UTC(2026, 10, 3, 12);
  const PAGE_START = NOW - 60_000;
  const shows = (note: AndroidTestNote, { open = true, device = true, thanks = true } = {}) =>
    androidTestShows(note, { open, device, now: NOW, pageStart: PAGE_START, thanks });

  it('invites on every visit until × or never, and only while switched on, on a device that can install', () => {
    expect(shows({})).toBe('invite');
    expect(shows({}, { open: false })).toBeNull();
    expect(shows({}, { device: false })).toBeNull();
    expect(shows({ never: true })).toBeNull();
    expect(shows({ app: true })).toBeNull();
  });

  it('hides it for a week after ×', () => {
    expect(shows({ hiddenUntil: NOW + 1 })).toBeNull();
    expect(shows({ hiddenUntil: NOW })).toBe('invite');
  });

  it('thanks once the install link was followed, for that page load only, where the place draws a thanks', () => {
    const installed = { steps: { group: true, optIn: true, install: true } } satisfies AndroidTestNote;
    expect(shows(installed)).toBe('thanks');
    expect(shows(installed, { thanks: false })).toBeNull();
    expect(shows({ ...installed, thanked: PAGE_START })).toBe('thanks');
    expect(shows({ ...installed, thanked: PAGE_START - 1 })).toBeNull();
    expect(shows({ ...installed, thanksClosed: true })).toBeNull();
    // Snoozed or not, the invitation is over.
    expect(shows({ ...installed, thanked: PAGE_START - 1, hiddenUntil: 0 })).toBeNull();
    // The first two steps alone keep it coming.
    expect(shows({ steps: { group: true, optIn: true } })).toBe('invite');
  });
});

describe('the note this browser keeps', () => {
  beforeEach(() => localStorage.clear());
  const stored = () => JSON.parse(localStorage.getItem(ANDROID_TEST_STORAGE_KEY) ?? 'null');

  it('notes ×, never, the steps followed and the thanks, and tells whoever listens', () => {
    const listener = vi.fn();
    const stop = subscribeAndroidTest(listener);
    snoozeAndroidTest(1_000);
    expect(readAndroidTestNote()).toEqual({ hiddenUntil: 1_000 + ANDROID_TEST_SNOOZE_MS });
    expect(ANDROID_TEST_SNOOZE_MS).toBe(7 * 24 * 60 * 60 * 1000);
    noteAndroidTestStep('group');
    noteAndroidTestStep('install');
    noteAndroidTestThanked(5);
    noteAndroidTestThanked(9);
    closeAndroidTestThanks();
    neverAndroidTest();
    expect(stored()).toEqual({
      hiddenUntil: 1_000 + ANDROID_TEST_SNOOZE_MS, steps: { group: true, install: true }, thanked: 5, thanksClosed: true, never: true,
    });
    expect(listener).toHaveBeenCalledTimes(7);
    stop();
    neverAndroidTest();
    expect(listener).toHaveBeenCalledTimes(7);
  });

  it('keeps the same note until it changes, and hears another tab', () => {
    const first = readAndroidTestNote();
    expect(readAndroidTestNote()).toBe(first);
    const listener = vi.fn();
    const stop = subscribeAndroidTest(listener);
    localStorage.setItem(ANDROID_TEST_STORAGE_KEY, JSON.stringify({ never: true }));
    window.dispatchEvent(new StorageEvent('storage', { key: ANDROID_TEST_STORAGE_KEY }));
    window.dispatchEvent(new StorageEvent('storage', { key: 'something.else' }));
    expect(listener).toHaveBeenCalledTimes(1);
    expect(readAndroidTestNote()).toEqual({ never: true });
    stop();
  });

  it('reads only what it wrote, whatever else is stored', () => {
    for (const raw of ['not json', '[]', '"never"', '{"never":"yes","hiddenUntil":"soon","steps":{"group":1,"install":true,"other":true}}']) {
      localStorage.setItem(ANDROID_TEST_STORAGE_KEY, raw);
      expect(readAndroidTestNote()).toEqual(raw.includes('install') ? { steps: { install: true } } : {});
    }
  });

  it('marks a browser that opened a page from the app, and only from Peek', () => {
    noteAndroidAppReferrer('https://www.google.com/');
    noteAndroidAppReferrer('android-app://com.example.other/');
    expect(readAndroidTestNote()).toEqual({});
    noteAndroidAppReferrer('android-app://com.plhery.peekdeliverytracker/');
    expect(readAndroidTestNote()).toEqual({ app: true });
  });

  it('keeps it in memory where the browser keeps no storage for the site', () => {
    const setItem = vi.spyOn(Storage.prototype, 'setItem').mockImplementation(() => { throw new DOMException('full', 'QuotaExceededError'); });
    try {
      neverAndroidTest();
      expect(readAndroidTestNote()).toEqual({ never: true });
    } finally { setItem.mockRestore(); }
  });
});

it('leads to Peek’s own testers’ group and Peek’s own page on Google Play', () => {
  expect(ANDROID_TEST_LINKS).toEqual({
    group: 'https://groups.google.com/g/peek-android-testers',
    optIn: 'https://play.google.com/apps/testing/com.plhery.peekdeliverytracker',
    install: 'https://play.google.com/store/apps/details?id=com.plhery.peekdeliverytracker',
  });
});
