import { generateKeyPairSync } from 'node:crypto';
import { afterEach, beforeEach, describe, expect, it, vi } from 'vitest';
import { stubPeekEnv } from '../test/peekEnv';
import { APP_FOLLOWS_LINKS_FROM, appFollowsLinks, appStoreLookupDone, resetAppStoreRelease, versionAtLeast } from './appStoreRelease';
import { parcelAlerts } from './publicParcels';
import { SupabaseServiceClient } from './supabase';

const now = Date.parse('2026-10-23T20:00:00Z');
const HOUR = 60 * 60 * 1_000;
const lookup = (version: string | null) => vi.fn(async () => Response.json(
  version === null ? { resultCount: 0, results: [] } : { resultCount: 1, results: [{ version }] },
));

beforeEach(async () => {
  stubPeekEnv();
  await resetAppStoreRelease();
  vi.spyOn(console, 'warn').mockImplementation(() => undefined);
});
afterEach(() => { vi.restoreAllMocks(); vi.unstubAllEnvs(); vi.unstubAllGlobals(); });

describe('the App Store release', () => {
  it.each([
    ['1.1.0', '1.1.0', true], ['1.1', '1.1.0', true], ['1.1.1', '1.1.0', true], ['1.10.0', '1.9.0', true], ['2.0', '1.1.0', true],
    ['1.0.1', '1.1.0', false], ['1.0.9', '1.1.0', false], ['0.9', '1.1.0', false],
  ])('%s against %s: %s', (version, minimum, later) => {
    expect(versionAtLeast(version, minimum)).toBe(later);
  });

  it('reads as no until the App Store answers, then as its live version says, asking again after an hour', async () => {
    const fetcher = lookup('1.0.1');
    expect(appFollowsLinks(now, fetcher)).toBe(false);
    await appStoreLookupDone();
    expect(appFollowsLinks(now, fetcher)).toBe(false);
    expect(fetcher).toHaveBeenCalledExactlyOnceWith('https://itunes.apple.com/lookup?id=6823615370', expect.anything());
    await resetAppStoreRelease();
    const released = lookup(APP_FOLLOWS_LINKS_FROM);
    appFollowsLinks(now, released);
    await appStoreLookupDone();
    expect(appFollowsLinks(now + HOUR - 1, released)).toBe(true);
    expect(released).toHaveBeenCalledOnce();
    // A failed lookup keeps the last version read, and is tried again sooner.
    const down = vi.fn(async () => new Response('', { status: 503 }));
    appFollowsLinks(now + HOUR, down);
    await appStoreLookupDone();
    expect(appFollowsLinks(now + HOUR + 1, down)).toBe(true);
    await appStoreLookupDone();
    expect(down).toHaveBeenCalledOnce();
    appFollowsLinks(now + HOUR + 10 * 60 * 1_000, down);
    await appStoreLookupDone();
    expect(down).toHaveBeenCalledTimes(2);
  });

  it('asks the App Store nothing on a site with no app there, and reads as no', async () => {
    vi.stubEnv('APP_STORE_ID', '');
    const fetcher = lookup(APP_FOLLOWS_LINKS_FROM);
    expect(appFollowsLinks(now, fetcher)).toBe(false);
    await appStoreLookupDone();
    expect(appFollowsLinks(now, fetcher)).toBe(false);
    expect(fetcher).not.toHaveBeenCalled();
  });

  it('reads an app the App Store does not list as no', async () => {
    const fetcher = lookup(null);
    appFollowsLinks(now, fetcher);
    await appStoreLookupDone();
    expect(appFollowsLinks(now, fetcher)).toBe(false);
    expect(fetcher).toHaveBeenCalledOnce();
  });
});

describe('the iPhone app on a parcel link\'s page', () => {
  const client = new SupabaseServiceClient('https://database.example', 'test');
  const apns = () => {
    vi.stubEnv('APNS_TEAM_ID', 'TEAM000000');
    vi.stubEnv('APNS_KEY_ID', 'KEY0000000');
    vi.stubEnv('APNS_PRIVATE_KEY', generateKeyPairSync('ec', { namedCurve: 'prime256v1' }).privateKey
      .export({ type: 'pkcs8', format: 'pem' }).toString());
    vi.stubEnv('APNS_BUNDLE_ID', 'com.example.peek');
  };

  it('is offered once the live app follows links and this server sends to iPhones, opened from the older site', async () => {
    const fetcher = lookup('1.1.0');
    vi.stubGlobal('fetch', fetcher);
    vi.stubEnv('CANONICAL_ORIGIN', 'https://peek.example');
    vi.stubEnv('LEGACY_HOSTS', 'old.example');
    // No iPhone push: the app is not offered, and the App Store is not asked.
    expect(parcelAlerts(client)).toEqual({ available: false, vapidPublicKey: null, email: false });
    expect(fetcher).not.toHaveBeenCalled();
    apns();
    await vi.waitFor(() => expect(parcelAlerts(client)).toEqual({
      available: false, vapidPublicKey: null, email: false, app: true, appOrigin: 'https://old.example',
    }));
    // A site with one host has no other site to open the app from.
    vi.stubEnv('LEGACY_HOSTS', '');
    expect(parcelAlerts(client)).toMatchObject({ app: true, appOrigin: null });
  });

  it('is not offered while the live app is older', async () => {
    const fetcher = lookup('1.0.1');
    vi.stubGlobal('fetch', fetcher);
    apns();
    parcelAlerts(client);
    await vi.waitFor(() => expect(fetcher).toHaveBeenCalledOnce());
    expect(parcelAlerts(client)).not.toHaveProperty('app');
  });
});
