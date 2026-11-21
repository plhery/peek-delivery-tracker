import { NextRequest } from 'next/server';
import { afterEach, describe, expect, it, vi } from 'vitest';
import { GET } from '../../app/api/carriers/route';

afterEach(() => vi.restoreAllMocks());

afterEach(() => vi.unstubAllEnvs());

describe('public carrier catalog route', () => {
  it('serves every carrier with a reusable content fingerprint', async () => {
    vi.spyOn(console, 'log').mockImplementation(() => undefined);
    const context = { params: Promise.resolve({}) };
    const response = await GET(
      new NextRequest('https://delivery.example/api/carriers'),
      context,
    );

    expect(response.status).toBe(200);
    expect(response.headers.get('cache-control')).toContain('max-age=300');
    expect(response.headers.get('cache-control')).not.toContain('no-store');
    const etag = response.headers.get('etag');
    expect(etag).toMatch(/^"[0-9a-f]{64}"$/);
    const body = await response.json() as {
      version: string;
      guidePages: boolean;
      'x-carriers': Record<string, { displayName: string; tracking: { requirements?: { field: string }[] } }>;
    };
    expect(body.version).toMatch(/^sha256-[0-9a-f]{64}$/);
    expect(body.guidePages).toBe(false);
    // The iPhone app refuses a catalog over 4 MiB (CarrierCatalog.maximumResponseBytes).
    expect(Buffer.byteLength(JSON.stringify(body))).toBeLessThan(2 * 1024 * 1024);
    expect(body['x-carriers']['amazon-logistics']?.displayName).toBe('Amazon Logistics');
    expect(body['x-carriers']['india-post']?.displayName).toBe('India Post');
    expect(body['x-carriers'].unknown).toBeDefined();
    // Clients fill a carrier's inputs by these names, which are the app's and not the scraper's.
    const fields = Object.values(body['x-carriers']).flatMap((carrier) => (carrier.tracking.requirements ?? []).map((requirement) => requirement.field));
    expect([...new Set(fields)].sort()).toEqual(['dpdPostcode', 'phoneDigits', 'trackingUrl']);
    expect(body['x-carriers'].dpd?.tracking.requirements?.map((requirement) => requirement.field)).toEqual(['dpdPostcode']);

    const cached = await GET(
      new NextRequest('https://delivery.example/api/carriers', {
        headers: { 'If-None-Match': `W/${etag}` },
      }),
      context,
    );
    expect(cached.status).toBe(304);
    expect(cached.headers.get('etag')).toBe(etag);
    await expect(cached.text()).resolves.toBe('');
  });

  it('says whether the site serves its guides, and changes its fingerprint with it', async () => {
    vi.spyOn(console, 'log').mockImplementation(() => undefined);
    const context = { params: Promise.resolve({}) };
    const read = () => GET(new NextRequest('https://delivery.example/api/carriers'), context);
    const off = await read();
    vi.stubEnv('GUIDE_PAGES', 'on');
    const on = await read();
    expect(await on.json()).toMatchObject({ guidePages: true });
    expect(on.headers.get('etag')).not.toBe(off.headers.get('etag'));
    // An app holding the catalog from before the change gets the whole catalog again.
    const kept = await GET(new NextRequest('https://delivery.example/api/carriers', { headers: { 'If-None-Match': off.headers.get('etag')! } }), context);
    expect(kept.status).toBe(200);
  });
});
