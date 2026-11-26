import { beforeEach, describe, expect, it, vi } from 'vitest';
import type { ShopLinkResolution } from 'universal-parcel-scraper';

const added = vi.hoisted(() => ({ outcome: 'added' as string, calls: [] as unknown[][] }));
vi.mock('./parcelsFromText', () => ({
  addNumbersToAccount: vi.fn(async (...args: unknown[]) => {
    added.calls.push(args);
    return [{ trackingNumber: 'x', carrier: 'dhl', outcome: added.outcome, packageId: 'p', jobId: null }];
  }),
}));

import { SupabaseError, type SupabaseServiceClient } from './supabase';
import { checkShopWaits, keepShopWaits, resetShopWaitPauses, shopWaitResponse, waitableLinks } from './shopWaits';

// Made-up order keys.
const LINK = 'https://hm.delivery-status.com/pl/?orderNo=9900000000001&s=0000aaaa1111bbbb2222cccc3333dddd';
const OTHER = 'https://shop.delivery-status.com/?orderNo=2&s=x';
const USER = '1a000000-0000-4000-8000-000000000001';
const NOW = Date.parse('2026-11-26T01:00:00Z');

interface Call { path: string; method: string; body?: Record<string, unknown> }

function service(answer: (call: Call) => unknown) {
  const calls: Call[] = [];
  const client = {
    request: vi.fn(async (path: string, options: { method?: string; body?: Record<string, unknown> } = {}) => {
      const call = { path: decodeURIComponent(path), method: options.method ?? 'GET', body: options.body };
      calls.push(call);
      return answer(call);
    }),
  };
  return { client: client as unknown as SupabaseServiceClient, calls };
}

const row = (id: string, extra: Record<string, unknown> = {}) => ({
  id, platform: 'parcellab', shop: 'hm', label: 'H&M', created_at: '2026-11-25T00:00:00Z', next_check_at: '2026-11-26T00:00:00Z',
  expires_at: '2026-12-09T00:00:00Z', expired_at: null, ...extra,
});

beforeEach(() => {
  added.outcome = 'added';
  added.calls = [];
  resetShopWaitPauses();
});

describe('a wait for a shop to ship', () => {
  it('answers without its link, with the shop and platform named', () => {
    const wait = shopWaitResponse({ ...row('w1'), link: LINK } as never);
    expect(wait).toEqual({
      id: 'w1', platform: 'parcellab', platform_name: 'parcelLab', shop_name: 'H&M', label: 'H&M',
      created_at: '2026-11-25T00:00:00Z', expires_at: '2026-12-09T00:00:00Z', expired_at: null,
    });
    expect(JSON.stringify(wait)).not.toContain('orderNo');
  });

  it('waits only for links that may still name a parcel', () => {
    const link = { platform: 'parcellab' as const, url: LINK, shop: 'hm' };
    expect(waitableLinks([
      { platform: 'parcellab', shop: 'hm', reason: 'no_tracking_yet', link },
      { platform: 'parcellab', shop: 'hm', reason: 'not_found', link },
      { platform: 'parcellab', shop: 'hm', reason: 'unsupported', link },
    ])).toHaveLength(2);
  });

  it('keeps one wait per link, for 14 days, asked again within the half hour when the reader asked', async () => {
    const { client, calls } = service((call) => call.method === 'GET'
      ? [{ ...row('w1'), link: LINK }]
      : [{ ...row('w2', { shop: 'shop', label: 'Lamp' }) }]);
    const waits = await keepShopWaits(client, USER, [
      { platform: 'parcellab', url: LINK, shop: 'hm' }, { platform: 'parcellab', url: OTHER, shop: 'shop' },
    ], '  Lamp ', { soon: true, now: NOW });
    expect(waits.map(({ id }) => id)).toEqual(['w1', 'w2']);
    const insert = calls.find(({ method }) => method === 'POST');
    expect(insert?.body).toMatchObject({
      user_id: USER, platform: 'parcellab', shop: 'shop', link: OTHER, label: 'Lamp',
      next_check_at: '2026-11-26T01:30:00.000Z', expires_at: '2026-12-10T01:00:00.000Z',
    });
    // The link never travels in an address.
    expect(calls.every(({ path }) => !path.includes('orderNo'))).toBe(true);
  });

  it('keeps none past the account\'s 20 open waits, and one kept meanwhile is no failure', async () => {
    const full = service((call) => call.method === 'GET' ? Array.from({ length: 20 }, (_, index) => ({ ...row(`w${index}`), link: `${OTHER}${index}` })) : []);
    await expect(keepShopWaits(full.client, USER, [{ platform: 'parcellab', url: LINK }], '')).resolves.toEqual([]);
    expect(full.calls.some(({ method }) => method === 'POST')).toBe(false);
    const raced = service((call) => {
      if (call.method === 'POST') throw new SupabaseError('Supabase POST request failed (409)', 409, '23505');
      return [];
    });
    await expect(keepShopWaits(raced.client, USER, [{ platform: 'parcellab', url: LINK }], '')).resolves.toEqual([]);
  });
});

describe('the scheduled check of waits', () => {
  const due = (extra: Record<string, unknown> = {}) => ({ ...row('w1'), user_id: USER, link: LINK, checks: 2, ...extra });

  function run(resolution: ShopLinkResolution, rows = [due()]) {
    const { client, calls } = service((call) => {
      if (call.method === 'GET') return rows;
      if (call.method === 'PATCH' && call.body?.expired_at && !call.path.includes('id=eq.')) return [{ id: 'old' }];
      return null;
    });
    const resolve = vi.fn(async () => resolution);
    return { calls, resolve, result: checkShopWaits(client, { now: NOW, resolve }) };
  }

  it('turns a wait whose shop shipped into its parcels, under its name, and forgets it with its link', async () => {
    const { calls, resolve, result } = run({ platform: 'parcellab', shop: 'hm', status: 'resolved', parcels: [{ trackingNumber: '00340000000000000001', carrier: 'dhl', confidence: 'high' }] });
    await expect(result).resolves.toEqual({ checked: 1, resolved: 1, expired: 1 });
    expect(resolve).toHaveBeenCalledWith({ platform: 'parcellab', url: LINK, shop: 'hm' }, expect.anything());
    expect(added.calls[0][1]).toBe(USER);
    expect(added.calls[0][3]).toBe('H&M');
    expect(calls.some(({ method, path }) => method === 'DELETE' && path.includes('id=eq.w1'))).toBe(true);
    // Those 14 days old lose their link; those that ran out a month ago go.
    expect(calls.find(({ method, body }) => method === 'PATCH' && body?.link === null)?.path).toContain('expires_at=lte.2026-11-26T01:00:00.000Z');
    expect(calls.find(({ method, path }) => method === 'DELETE' && path.includes('expired_at=lt.'))?.path).toContain('2026-10-27T01:00:00.000Z');
  });

  it('asks again in a few hours while the shop hasn\'t shipped, and within the hour when the account could not add', async () => {
    const waiting = run({ platform: 'parcellab', shop: 'hm', status: 'unresolved', reason: 'no_tracking_yet' });
    await waiting.result;
    expect(waiting.calls.find(({ path }) => path.includes('id=eq.w1'))?.body).toEqual({ checks: 3, last_reason: 'no_tracking_yet', next_check_at: '2026-11-26T05:00:00.000Z' });
    added.outcome = 'limited';
    const limited = run({ platform: 'parcellab', shop: 'hm', status: 'resolved', parcels: [{ trackingNumber: '00340000000000000001', carrier: 'dhl', confidence: 'high' }] });
    await expect(limited.result).resolves.toMatchObject({ resolved: 0 });
    expect(limited.calls.find(({ path }) => path.includes('id=eq.w1'))?.body).toEqual({ checks: 3, next_check_at: '2026-11-26T02:00:00.000Z' });
  });

  it('backs off a refused wait and leaves its platform alone for an hour', async () => {
    const refused = run({ platform: 'parcellab', shop: 'hm', status: 'unresolved', reason: 'rate_limited' }, [due(), due({ id: 'w2' })]);
    await expect(refused.result).resolves.toMatchObject({ checked: 1 });
    // 4 h doubled for each check before (two), at most a day.
    expect(refused.calls.find(({ path }) => path.includes('id=eq.w1'))?.body).toMatchObject({ next_check_at: '2026-11-26T17:00:00.000Z' });
    expect(refused.resolve).toHaveBeenCalledTimes(1);
  });

  it('stops waiting for a link no platform reads any more', async () => {
    const gone = run({ status: 'unresolved', reason: 'unsupported' });
    await gone.result;
    expect(gone.calls.find(({ path }) => path.includes('id=eq.w1'))?.body).toEqual({ checks: 3, link: null, expired_at: '2026-11-26T01:00:00.000Z', last_reason: null });
  });
});
