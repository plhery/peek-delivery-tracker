import { describe, expect, it } from 'vitest';
import type { ApiContext } from './api';
import { readShopLinks } from './shopLinkRoutes';

// Made-up order keys.
const LINK = 'https://hm.delivery-status.com/pl/?orderNo=9900000000001&s=0000aaaa1111bbbb2222cccc3333dddd';

function context(body: unknown): ApiContext {
  const request = new Request('https://peektracker.com/api/shop-links', {
    method: 'POST', headers: { 'content-type': 'application/json' }, body: JSON.stringify(body),
  });
  return { request } as unknown as ApiContext;
}

describe('reading a shop link for the Add sheet and the share sheet', () => {
  it('answers the parcels it names, or why it names none, and nothing of the link', async () => {
    const resolved = await readShopLinks(context({ text: `Follow package ${LINK}` }), 'test:1', async () => ({
      platform: 'parcellab', shop: 'hm', status: 'resolved', parcels: [{ trackingNumber: '00340000000000000001', carrier: 'dhl', confidence: 'high' }],
    }));
    expect(await resolved.json()).toEqual({ links: 1, parcels: [{ tracking_number: '00340000000000000001', carrier: 'dhl', confidence: 'high' }], unresolved: [] });
    const waiting = await readShopLinks(context({ text: LINK }), 'test:1', async () => ({ platform: 'parcellab', shop: 'hm', status: 'unresolved', reason: 'not_found' }));
    const answer = await waiting.text();
    expect(JSON.parse(answer)).toEqual({ links: 1, parcels: [], unresolved: [{ platform: 'parcellab', platform_name: 'parcelLab', shop_name: 'H&M', reason: 'not_found', waitable: true }] });
    expect(answer).not.toContain('orderNo');
  });

  it('asks nothing for a text without a shop link, and refuses an empty text or a flood', async () => {
    const none = await readShopLinks(context({ text: 'https://example.com/?orderNo=1' }), 'test:2', async () => { throw new Error('not asked'); });
    expect(await none.json()).toEqual({ links: 0, parcels: [], unresolved: [] });
    await expect(readShopLinks(context({ text: ' ' }), 'test:2')).rejects.toMatchObject({ status: 400 });
    const unresolved = async () => ({ status: 'unresolved' as const, reason: 'unsupported' as const });
    for (let index = 0; index < 20; index += 1) await readShopLinks(context({ text: LINK }), 'test:3', unresolved);
    await expect(readShopLinks(context({ text: LINK }), 'test:3', unresolved)).rejects.toMatchObject({ status: 429 });
  });
});
