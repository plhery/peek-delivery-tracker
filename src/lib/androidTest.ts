/**
 * Peek for Android's closed test on Google Play, and the site's invitation to it.
 *
 * Google opens an app from a personal developer account to everyone only once 12 testers have stayed opted in to a
 * closed test for 14 days in a row. Peek's testers are the members of a Google Group: someone joins the group,
 * becomes a tester on Google Play, then installs the app. The site invites the Android visitors who can install it
 * on the parcel page, in its "Notify me" sheet and on the home page, and walks them through the three steps at
 * `/android`.
 */

/**
 * Whether the site invites anyone. Off, it shows nothing and `/android` is not found: once Google opens the app to
 * everyone, or while the release testers install doesn't work.
 */
export const ANDROID_TEST_OPEN = true;

/** The three steps' links: Peek's own testers' group, Google Play's page to become a tester, and the app on Google Play. */
export const ANDROID_TEST_LINKS = {
  group: 'https://groups.google.com/g/peek-android-testers',
  optIn: 'https://play.google.com/apps/testing/com.plhery.peekdeliverytracker',
  install: 'https://play.google.com/store/apps/details?id=com.plhery.peekdeliverytracker',
} as const;

export type AndroidTestStep = keyof typeof ANDROID_TEST_LINKS;

/** The steps' page. */
export const ANDROID_TEST_PATH = '/android';

/** Peek for Android runs on Android 12 and later. */
const MINIMUM_ANDROID = 12;
/** Chrome's reduced user agent names this version on every Android phone, whatever it runs. */
const REDUCED_ANDROID = 10;
/** The app opens the site's pages in a Custom Tab, which names it as the referrer. */
const APP_REFERRER = 'android-app://com.plhery.peekdeliverytracker';
/** How long × hides the invitation. */
export const ANDROID_TEST_SNOOZE_MS = 7 * 24 * 60 * 60 * 1000;
/** How long the thanks stays at most: the two weeks a tester keeps the app. */
export const ANDROID_TEST_THANKS_MS = 14 * 24 * 60 * 60 * 1000;

/** What the browser says about the device, as User-Agent Client Hints give it where the browser has them. */
export interface AndroidDevice {
  userAgent: string;
  /** `navigator.userAgentData.platform`. */
  platform?: string;
  /** The high-entropy `platformVersion`: the real Android version, as "14.0.0". */
  platformVersion?: string;
}

/**
 * Whether this browser runs on a phone or tablet that can install Peek for Android and finish the steps.
 *
 * The real version comes from the client hints. Without them the user agent's version is believed unless it is the
 * Android 10 Chrome reports for every version: that one could be any, so it is invited. An app's own browser (a
 * WebView) is not: Google lets no one sign in there, so the group can't be joined from it.
 */
export function canInstallAndroidApp({ userAgent, platform, platformVersion }: AndroidDevice): boolean {
  if (!/\bAndroid\b/.test(userAgent) && platform !== 'Android') return false;
  if (/; wv\)/.test(userAgent)) return false;
  const hinted = Number.parseInt(platformVersion ?? '', 10);
  if (Number.isFinite(hinted)) return hinted >= MINIMUM_ANDROID;
  const written = Number.parseInt(/\bAndroid (\d+)/.exec(userAgent)?.[1] ?? '', 10);
  return !Number.isFinite(written) || written === REDUCED_ANDROID || written >= MINIMUM_ANDROID;
}

type UserAgentData = { platform?: string; getHighEntropyValues?: (hints: string[]) => Promise<{ platformVersion?: string }> };

/** Reads the device from this browser, asking for the real version only where the browser is on Android. */
export async function readAndroidDevice(): Promise<boolean> {
  if (typeof navigator === 'undefined') return false;
  const userAgent = navigator.userAgent;
  const data = (navigator as Navigator & { userAgentData?: UserAgentData }).userAgentData;
  const platform = data?.platform;
  if (!/\bAndroid\b/.test(userAgent) && platform !== 'Android') return false;
  let platformVersion: string | undefined;
  try {
    platformVersion = (await data?.getHighEntropyValues?.(['platformVersion']))?.platformVersion;
  } catch { /* No hints: the user agent decides. */ }
  return canInstallAndroidApp({ userAgent, platform, platformVersion });
}

/** What this browser did with the invitation. It stays on the device. */
export interface AndroidTestNote {
  /** × hides the invitation until then (ms since the epoch). */
  hiddenUntil?: number;
  /** "Never show" or "Don't ask me again". */
  never?: boolean;
  /** The steps whose link was followed. */
  steps?: Partial<Record<AndroidTestStep, true>>;
  /** When the thanks for installing was first shown (ms since the epoch). */
  thanked?: number;
  /** The thanks was closed. */
  thanksClosed?: boolean;
  /** This browser opened a page from the app itself. */
  app?: boolean;
}

export type AndroidTestShows = 'invite' | 'thanks' | null;

/**
 * What a place shows: the invitation; once the install link was followed after the group's, the thanks, until it is
 * closed and for two weeks at most (only where `thanks` is drawn); or nothing. An install without the group could
 * not have worked, so it is invited again. The thanks leads back to the steps, for a join or an opt-in that didn't
 * go through, which the site can't see.
 */
export function androidTestShows(note: AndroidTestNote, { open, device, now, thanks }: {
  open: boolean;
  device: boolean;
  now: number;
  thanks: boolean;
}): AndroidTestShows {
  if (!open || !device || note.never || note.app) return null;
  if (note.steps?.group && note.steps.install) {
    return thanks && !note.thanksClosed && (note.thanked === undefined || now - note.thanked < ANDROID_TEST_THANKS_MS) ? 'thanks' : null;
  }
  return (note.hiddenUntil ?? 0) > now ? null : 'invite';
}

export const ANDROID_TEST_STORAGE_KEY = 'sdt.peek.androidTest.v1'; // gitleaks:allow -- public localStorage name

const EMPTY: AndroidTestNote = {};
const listeners = new Set<() => void>();
/** Where the note lives when the browser keeps no storage for the site. */
let memory: string | null = null;
let read: { raw: string | null; note: AndroidTestNote } | null = null;

function parse(raw: string | null): AndroidTestNote {
  try {
    const value: unknown = raw ? JSON.parse(raw) : null;
    if (!value || typeof value !== 'object' || Array.isArray(value)) return EMPTY;
    const given = value as Record<string, unknown>;
    const time = (field: unknown) => typeof field === 'number' && Number.isFinite(field) ? field : undefined;
    const steps = given.steps && typeof given.steps === 'object' ? given.steps as Record<string, unknown> : {};
    const followed = (Object.keys(ANDROID_TEST_LINKS) as AndroidTestStep[]).filter((step) => steps[step] === true);
    const note: AndroidTestNote = {};
    if (time(given.hiddenUntil) !== undefined) note.hiddenUntil = time(given.hiddenUntil);
    if (given.never === true) note.never = true;
    if (followed.length) note.steps = Object.fromEntries(followed.map((step) => [step, true]));
    if (time(given.thanked) !== undefined) note.thanked = time(given.thanked);
    if (given.thanksClosed === true) note.thanksClosed = true;
    if (given.app === true) note.app = true;
    return note;
  } catch { return EMPTY; }
}

function stored(): string | null {
  try { return localStorage.getItem(ANDROID_TEST_STORAGE_KEY) ?? memory; } catch { return memory; }
}

/** This browser's note, the same object until it changes. */
export function readAndroidTestNote(): AndroidTestNote {
  const raw = stored();
  if (read?.raw !== raw) read = { raw, note: parse(raw) };
  return read.note;
}

function write(change: (note: AndroidTestNote) => AndroidTestNote) {
  const raw = JSON.stringify(change(readAndroidTestNote()));
  try { localStorage.setItem(ANDROID_TEST_STORAGE_KEY, raw); memory = null; } catch { memory = raw; }
  for (const listener of [...listeners]) listener();
}

/** Calls `listener` when the note changes, here or in another tab. */
export function subscribeAndroidTest(listener: () => void): () => void {
  listeners.add(listener);
  const other = (event: StorageEvent) => { if (event.key === ANDROID_TEST_STORAGE_KEY || event.key === null) listener(); };
  window.addEventListener('storage', other);
  return () => { listeners.delete(listener); window.removeEventListener('storage', other); };
}

/** ×: nothing for a week. */
export function snoozeAndroidTest(now = Date.now()): void {
  write((note) => ({ ...note, hiddenUntil: now + ANDROID_TEST_SNOOZE_MS }));
}

/** "Never show", "Don't ask me again": nothing in this browser, for good. */
export function neverAndroidTest(): void {
  write((note) => ({ ...note, never: true }));
}

/** A step's link was followed. */
export function noteAndroidTestStep(step: AndroidTestStep): void {
  write((note) => ({ ...note, steps: { ...note.steps, [step]: true } }));
}

/** The thanks is on screen: it stays for this page load only. */
export function noteAndroidTestThanked(now = Date.now()): void {
  write((note) => note.thanked === undefined ? { ...note, thanked: now } : note);
}

export function closeAndroidTestThanks(): void {
  write((note) => ({ ...note, thanksClosed: true }));
}

/** "Didn't work?": the steps start over, and the invitation comes back until they are taken again. */
export function restartAndroidTest(): void {
  write((note) => {
    const restarted = { ...note };
    delete restarted.steps;
    delete restarted.thanked;
    delete restarted.thanksClosed;
    return restarted;
  });
}

/** A page opened from the app itself: this browser's owner has the app. */
export function noteAndroidAppReferrer(referrer: string): void {
  if (referrer.startsWith(APP_REFERRER) && !readAndroidTestNote().app) write((note) => ({ ...note, app: true }));
}
