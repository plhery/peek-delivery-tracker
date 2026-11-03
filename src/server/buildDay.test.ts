import { afterEach, describe, expect, it, vi } from 'vitest';
import { shownDay, shownDays } from './buildDay';

vi.mock('next/headers', () => ({
  cookies: async () => ({ get: () => undefined }),
  headers: async () => new Headers({ host: 'peek.example', 'x-forwarded-proto': 'https' }),
}));
// Outside a request there is nothing to wait for.
vi.mock('next/server', async (original) => ({ ...await original<typeof import('next/server')>(), connection: async () => undefined }));

afterEach(() => { vi.unstubAllEnvs(); vi.resetModules(); });

describe('the days the site shows', () => {
  it('keeps a day up to the build and shows the build day for a later one', () => {
    expect(shownDay('2026-11-02', '2026-11-03')).toBe('2026-11-02');
    expect(shownDay('2026-11-03', '2026-11-03')).toBe('2026-11-03');
    expect(shownDay('2026-11-07', '2026-11-03')).toBe('2026-11-03');
  });

  it('moves a page’s published and updated days together, and leaves a page with past days as it is', () => {
    expect(shownDays({ title: 'A', published: '2026-11-01', updated: '2026-11-05' }, '2026-11-03'))
      .toEqual({ title: 'A', published: '2026-11-01', updated: '2026-11-03' });
    expect(shownDays({ published: '2026-11-06', updated: '2026-11-07' }, '2026-11-03'))
      .toEqual({ published: '2026-11-03', updated: '2026-11-03' });
    const past = { published: '2026-10-01', updated: '2026-10-02' };
    expect(shownDays(past, '2026-11-03')).toBe(past);
  });

  it('dates no carrier’s page, guide or sitemap entry after the build', async () => {
    vi.stubEnv('PEEK_BUILD_DAY', '2000-01-01');
    const { carrierLinks, carrierText, carrierUpdated } = await import('./carrierTexts');
    const { guide, guideLinks, guideUpdated } = await import('./guides');
    const [carrier] = carrierLinks('en');
    const [first] = guideLinks('en');
    expect(carrierText('en', carrier!.id)).toMatchObject({ published: '2000-01-01', updated: '2000-01-01' });
    expect(carrierUpdated('en', carrier!.id)).toBe('2000-01-01');
    expect(guide('en', first!.id)).toMatchObject({ published: '2000-01-01', updated: '2000-01-01' });
    expect(guideUpdated('en', first!.id)).toBe('2000-01-01');
    const { default: sitemap } = await import('../../app/sitemap');
    const dated = (await sitemap()).flatMap(({ lastModified }) => (lastModified === undefined ? [] : [String(lastModified)]));
    expect(dated.length).toBeGreaterThan(0);
    expect(dated.filter((day) => day > '2000-01-01')).toEqual([]);
  });
});
