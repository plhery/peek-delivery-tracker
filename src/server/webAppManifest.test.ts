import { afterEach, describe, expect, it, vi } from 'vitest';
import { stubPeekEnv } from '../test/peekEnv';
import { webAppManifestResponse } from './webAppManifest';

afterEach(() => { vi.unstubAllEnvs(); });

/** A language's manifest as its address answers it. */
async function manifestIn(locale: 'en' | 'de') {
  const response = webAppManifestResponse(locale);
  expect(response.headers.get('Content-Type')).toBe('application/manifest+json');
  return await response.json() as Record<string, unknown>;
}

describe('the installed app', () => {
  it('is Peek on Peek’s own site, opening at its root and receiving what is shared to it', async () => {
    stubPeekEnv();
    const manifest = await manifestIn('en');
    expect(manifest).toMatchObject({
      name: 'Peek — Universal Parcel Tracker',
      short_name: 'Peek',
      description: 'Private parcel tracking, with notifications and history synced across your devices.',
      start_url: '/',
      scope: '/',
      display: 'standalone',
      id: '/',
    });
    expect(manifest.share_target).toEqual({
      action: '/share-target',
      method: 'POST',
      enctype: 'multipart/form-data',
      params: { title: 'title', text: 'text', url: 'url' },
    });
  });

  it('is the same app installed from a page in any language, described in that language', async () => {
    stubPeekEnv();
    const [manifest, german] = await Promise.all([manifestIn('en'), manifestIn('de')]);
    expect(german.lang).toBe('de');
    expect(german.description).not.toBe(manifest.description);
    expect({ ...german, name: manifest.name, description: manifest.description, lang: manifest.lang }).toEqual(manifest);
  });

  it('takes the site’s name at each request, and is a plain parcel tracker without one', async () => {
    expect((await manifestIn('en')).short_name).toBe('Parcel Tracker');
    vi.stubEnv('SITE_NAME', 'Colis');
    expect((await manifestIn('en')).short_name).toBe('Colis');
  });
});
