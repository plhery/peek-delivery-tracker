import { describe, expect, it } from 'vitest';
import { readInboundMail } from './inboundEmail/mail';
import { shopLinkNumbers, withShopLinkNumbers } from './shopLinks';
import { trackingNumbersInText } from './parcelsFromText';

// Made-up numbers and order keys; UPS's is its published sample.
const UPS = '1Z999AA10123456784';
const DHL = '00340000000000000001';
const ORDER = 'https://hm.delivery-status.com/pl/?orderNo=9900000000001&s=0000aaaa1111bbbb2222cccc3333dddd';

describe('shop tracking-page links in a text', () => {
  it('adds the parcels the shop\'s platform names, with its carrier, and says why a link named none', async () => {
    const asked: string[] = [];
    const reading = await shopLinkNumbers(`Follow package: ${ORDER}\nhttps://shop.delivery-status.com/?orderNo=2&s=x`, async (link) => {
      asked.push(link.url);
      return link.shop === 'hm'
        ? { platform: 'parcellab', shop: 'hm', status: 'resolved', parcels: [{ trackingNumber: DHL, carrier: 'dhl', confidence: 'high', courierCode: 'dhl-germany' }] }
        : { platform: 'parcellab', shop: link.shop, status: 'unresolved', reason: 'no_tracking_yet' };
    });
    expect(asked).toHaveLength(2);
    expect(reading.numbers.map(({ normalized, match }) => `${normalized} ${match.carrier} ${match.confidence}`)).toEqual([`${DHL} dhl high`]);
    // The link stays in memory, for a wait to keep.
    expect(reading.unresolved).toEqual([{ platform: 'parcellab', shop: 'shop', reason: 'no_tracking_yet', link: { platform: 'parcellab', shop: 'shop', url: 'https://shop.delivery-status.com/?orderNo=2&s=x' } }]);
    await expect(shopLinkNumbers('no link here', async () => { throw new Error('not asked'); })).resolves.toEqual({ numbers: [], unresolved: [] });
    // A platform that fails is no answer, and the text's own numbers stay.
    await expect(shopLinkNumbers(ORDER, async () => { throw new TypeError('fetch failed'); }))
      .resolves.toMatchObject({ numbers: [], unresolved: [{ reason: 'unavailable' }] });
  });

  it('follows an email\'s worded redirects after the text\'s own shop links, one at a time until one names a parcel', async () => {
    const redirect = (p: string) => ({ platform: 'redirect' as const, url: `https://link.shop.example/u/nrd.php?p=${p}` });
    const asked: string[] = [];
    const reading = await shopLinkNumbers(`Follow package: ${ORDER}`, async (link) => {
      asked.push(link.url);
      if (link.platform !== 'redirect') return { platform: 'parcellab', shop: 'hm', status: 'unresolved', reason: 'not_found' };
      return link.url.endsWith('b')
        ? { platform: 'redirect', status: 'resolved', parcels: [{ trackingNumber: UPS, carrier: 'ups', confidence: 'high' }] }
        : { platform: 'redirect', status: 'unresolved', reason: 'unsupported' };
    }, [redirect('a'), redirect('a'), redirect('b'), redirect('c')]);
    // Each followed once, and none after the one that named the parcel.
    expect(asked).toEqual([ORDER, redirect('a').url, redirect('b').url]);
    expect(reading.numbers.map(({ normalized, match }) => `${normalized} ${match.carrier}`)).toEqual([`${UPS} ups`]);
    expect(reading.unresolved.map(({ reason }) => reason)).toEqual(['not_found', 'unsupported']);
  });

  it('keeps the number the text names, with the platform\'s carrier when the text was less sure', () => {
    const text = trackingNumbersInText(`Tracking number: ${DHL}\nShipped with UPS: ${UPS}`);
    const linked = [{ normalized: DHL, match: { trackingNumber: DHL, carrier: 'dhl' as const, confidence: 'high' as const, candidates: ['dhl' as const], preferred: [], source: 'link' as const } }];
    const merged = withShopLinkNumbers(text, linked);
    expect(merged.map(({ normalized, match }) => `${normalized} ${match.carrier}`)).toEqual([`${UPS} ups`, `${DHL} dhl`]);
    expect(withShopLinkNumbers([], linked)).toEqual(linked);
  });

  it('reads an HTML email\'s shop link before its other links', async () => {
    const products = Array.from({ length: 60 }, (_, index) => `<a href="https://www2.hm.com/pl_pl/productpage.${String(index).padStart(10, '0')}.html?utm_source=transactional">Item</a>`).join('');
    const html = `<html><body><p>Your order is on its way</p>${products}<a href="${ORDER.replace('&', '&amp;')}">Follow package</a></body></html>`;
    const raw = new TextEncoder().encode(`From: H&M <noreply@email.hm.com>\r\nTo: reader@example.test\r\nSubject: Your order has been shipped\r\nContent-Type: text/html; charset=utf-8\r\n\r\n${html}`);
    const mail = await readInboundMail(raw);
    expect(mail.text).toContain(ORDER);
  });
});
