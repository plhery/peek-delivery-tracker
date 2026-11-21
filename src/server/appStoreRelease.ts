import 'server-only';
import { logOperationalEvent } from './observability';
import { appStoreApp } from './siteSettings';

/** The first version of the iPhone app that follows a parcel link without an account and notifies for it. */
export const APP_FOLLOWS_LINKS_FROM = '1.1.0';

const CHECK_EVERY_MS = 60 * 60 * 1_000;
const RETRY_AFTER_MS = 10 * 60 * 1_000;

interface Release {
  /** The app it was read for. */
  id: string;
  version: string | null;
  checkedAt: number;
  ok: boolean;
}

let release: Release | null = null;
let checking: Promise<void> | null = null;

/** Whether `version` is `minimum` or later, both dotted numbers. */
export function versionAtLeast(version: string, minimum: string): boolean {
  const parts = (value: string) => value.split('.').map((part) => Number.parseInt(part, 10) || 0);
  const [have, need] = [parts(version), parts(minimum)];
  for (let index = 0; index < Math.max(have.length, need.length); index += 1) {
    const difference = (have[index] ?? 0) - (need[index] ?? 0);
    if (difference !== 0) return difference > 0;
  }
  return true;
}

async function check(id: string, now: number, fetcher: typeof fetch): Promise<void> {
  try {
    const response = await fetcher(`https://itunes.apple.com/lookup?id=${id}`, {
      signal: AbortSignal.timeout(5_000), cache: 'no-store',
    });
    if (!response.ok) throw new Error(`The App Store answered HTTP ${response.status}`);
    const body: unknown = await response.json();
    const found = typeof body === 'object' && body !== null && 'results' in body && Array.isArray(body.results)
      ? (body.results as unknown[])[0] : undefined;
    const version = typeof found === 'object' && found !== null && 'version' in found && typeof found.version === 'string'
      ? found.version : null;
    release = { id, version, checkedAt: now, ok: true };
  } catch (error) {
    // The last version read stays: the App Store being slow says nothing about the app.
    release = { id, version: release?.id === id ? release.version : null, checkedAt: now, ok: false };
    logOperationalEvent('app_store_lookup_failed', { error: error instanceof Error ? error.name : 'unknown' }, 'warning');
  }
}

/**
 * Whether the iPhone app on the App Store follows parcel links without an
 * account, read from the App Store's own lookup once an hour. Until the first
 * answer it reads as no: the page then offers what works without the app. A
 * site without an iPhone app (`APP_STORE_ID`) asks nothing and offers none.
 */
export function appFollowsLinks(now = Date.now(), fetcher: typeof fetch = fetch, env: NodeJS.ProcessEnv = process.env): boolean {
  const app = appStoreApp(env);
  if (!app) return false;
  const read = release?.id === app.id ? release : null;
  const due = !read || now - read.checkedAt >= (read.ok ? CHECK_EVERY_MS : RETRY_AFTER_MS);
  if (due && !checking) checking = check(app.id, now, fetcher).finally(() => { checking = null; });
  return read?.version != null && versionAtLeast(read.version, APP_FOLLOWS_LINKS_FROM);
}

/** Waits for a lookup under way. Tests only. */
export async function appStoreLookupDone(): Promise<void> {
  await checking;
}

/** Waits for a lookup under way, and forgets what was read. Tests only. */
export async function resetAppStoreRelease(): Promise<void> {
  await checking;
  release = null;
}
