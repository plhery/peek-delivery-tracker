import { afterEach, describe, expect, it, vi } from 'vitest';
import { announcedMaintenance, pageServerDown, quietRetryDelay, readMaintenance, serverDown, unanswered } from './maintenance';

describe('the server down for maintenance', () => {
  afterEach(() => {
    vi.unstubAllGlobals();
  });

  it('is what a proxy or Cloudflare answers in its place, the database’s 502 and the edge’s 503 alike', () => {
    for (const status of [502, 503, 504, 520, 521, 522, 523, 524, 525, 526, 527, 530]) expect(serverDown(status, null), `${status}`).toBe(true);
    expect(serverDown(502, { error: 'The delivery database is temporarily unavailable' })).toBe(true);
    expect(serverDown(503, { error: 'Peek is down for maintenance', maintenance: { planned: true, mode: 'soft', since: null, backAround: null } })).toBe(true);
    expect(serverDown(503, { error: 'Supabase authentication is temporarily unavailable' })).toBe(true);
  });

  it('is never a 404: a route a deploy removed, a CDN’s 404 or the proxy’s own text is an error, not maintenance', () => {
    expect(serverDown(404, null)).toBe(false);
    expect(serverDown(404, '404 page not found\n')).toBe(false);
    expect(serverDown(404, { error: 'Parcel unavailable' })).toBe(false);
  });

  it('is a page a proxy, Cloudflare or the edge guard answered in the server’s place, never a 404', () => {
    const page = (status: number, headers: Record<string, string> = {}) => new Response(null, { status, headers });
    expect(pageServerDown(page(530, { 'Content-Type': 'text/html' }))).toBe(true);
    expect(pageServerDown(page(503, { 'Content-Type': 'text/html', 'X-Maintenance': 'planned' }))).toBe(true);
    // The edge guard's saved copy of a page.
    expect(pageServerDown(page(200, { 'Content-Type': 'text/html', 'X-Maintenance': 'unplanned' }))).toBe(true);
    expect(pageServerDown(page(404, { 'Content-Type': 'text/plain; charset=utf-8' }))).toBe(false);
    expect(pageServerDown(page(404, { 'Content-Type': 'text/html' }))).toBe(false);
    expect(pageServerDown(page(500, { 'Content-Type': 'text/html' }))).toBe(false);
    expect(pageServerDown(page(200, { 'Content-Type': 'text/html' }))).toBe(false);
  });

  it('is not the app refusing, failing on its own, or never set up for something', () => {
    for (const status of [400, 401, 403, 409, 410, 429, 500]) expect(serverDown(status, { error: 'No' }), `${status}`).toBe(false);
    expect(serverDown(503, { error: 'Push notifications are not configured' })).toBe(false);
    expect(serverDown(503, { error: 'Amazon Shipping could not be checked. Try again.' })).toBe(false);
    expect(serverDown(503, { error: 'The carrier could not be asked. Try again later.' })).toBe(false);
    expect(serverDown(503, { error: 'Browser verification is temporarily unavailable. Try again shortly.' })).toBe(false);
  });

  it('is a request that got no answer, or a sign-in server that is down, but not one the caller cancelled', () => {
    expect(unanswered(new TypeError('Failed to fetch'))).toBe(true);
    expect(unanswered(new DOMException('Timed out', 'TimeoutError'))).toBe(true);
    expect(unanswered(Object.assign(new Error('Bad gateway'), { name: 'AuthRetryableFetchError', status: 530 }))).toBe(true);
    expect(unanswered(new DOMException('Stopped', 'AbortError'))).toBe(false);
    expect(unanswered(new Error('Invalid Refresh Token'))).toBe(false);
  });

  it('reads what the edge announced, and only an announced maintenance’s end', () => {
    expect(announcedMaintenance({ error: 'x', maintenance: { planned: true, mode: 'soft', since: '2026-11-17T09:00:00Z', backAround: '2026-11-17T09:30:00Z' } }))
      .toEqual({ planned: true, backAround: '2026-11-17T09:30:00Z' });
    expect(announcedMaintenance({ maintenance: { planned: false, backAround: '2026-11-17T09:30:00Z' } })).toEqual({ planned: false, backAround: null });
    expect(announcedMaintenance({ maintenance: { planned: true, backAround: 'soon' } })).toEqual({ planned: true, backAround: null });
    expect(announcedMaintenance({ error: 'The delivery database is temporarily unavailable' })).toBeNull();
    expect(announcedMaintenance(null)).toBeNull();
  });

  it('is asked again as soon as the server asks, and at least once a minute', () => {
    expect([0, 1, 2, 3, 4, 5].map((attempt) => quietRetryDelay(attempt))).toEqual([5_000, 10_000, 20_000, 40_000, 60_000, 60_000]);
    expect(quietRetryDelay(0, 30_000)).toBe(30_000);
    expect(quietRetryDelay(0, 900_000)).toBe(60_000);
  });

  it('reads /maintenance.json without the cache, and nothing from a page or a 404 before the edge is in front', async () => {
    const answers = [
      Response.json({ planned: true, mode: 'soft', since: null, backAround: '2026-11-17T09:30:00Z' }),
      Response.json({ error: 'x', maintenance: { planned: false, mode: null, since: null, backAround: null } }, { status: 503 }),
      new Response('<!doctype html><title>Not found</title>', { status: 404, headers: { 'Content-Type': 'text/html' } }),
      new Response('<!doctype html><title>Peek</title>', { status: 200, headers: { 'Content-Type': 'text/html' } }),
    ];
    const request = vi.fn(async () => answers.shift()!);

    await expect(readMaintenance(undefined, request)).resolves.toEqual({ planned: true, backAround: '2026-11-17T09:30:00Z' });
    await expect(readMaintenance(undefined, request)).resolves.toEqual({ planned: false, backAround: null });
    await expect(readMaintenance(undefined, request)).resolves.toBeNull();
    await expect(readMaintenance(undefined, request)).resolves.toBeNull();
    await expect(readMaintenance(undefined, vi.fn().mockRejectedValue(new TypeError('Failed to fetch')))).resolves.toBeNull();
    expect(request).toHaveBeenCalledWith('/maintenance.json', expect.objectContaining({ cache: 'no-store', credentials: 'omit' }));
  });
});
