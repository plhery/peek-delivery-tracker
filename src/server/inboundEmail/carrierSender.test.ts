import { describe, expect, it } from 'vitest';
import { carrierNames, carriersOfAddress } from './carrierSender';

describe('the carriers an email address writes for', () => {
  it('knows a carrier by its own site, whatever the mail system in front of it', () => {
    expect(carriersOfAddress('noreply@post.ch')).toEqual(['swiss-post']);
    expect(carriersOfAddress('info@notifications.ups.com')).toEqual(['ups']);
    expect(carriersOfAddress('noreply@advance.dachser.com')).toEqual(['dachser']);
  });

  it('gives every carrier of a site several share', () => {
    expect(carriersOfAddress('noreply@dhl.com')).toEqual(expect.arrayContaining(['dhl', 'dhl-express']));
  });

  it('knows a carrier under another country\'s ending', () => {
    expect(carriersOfAddress('noreply@dpd.ch')).toEqual(expect.arrayContaining(['dpd']));
  });

  it('takes a shop, a person and a shop that also delivers for no carrier', () => {
    expect(carriersOfAddress('orders@example-shop.com')).toEqual([]);
    expect(carriersOfAddress('alex@gmail.com')).toEqual([]);
    expect(carriersOfAddress('shipment-tracking@amazon.de')).toEqual([]);
    expect(carriersOfAddress('ebay@ebay.com')).toEqual([]);
    expect(carriersOfAddress('')).toEqual([]);
    expect(carriersOfAddress('nobody')).toEqual([]);
  });

  it('names the carriers as their emails do', () => {
    expect(carrierNames(['dhl-express'])).toContain('DHL Express');
  });
});
