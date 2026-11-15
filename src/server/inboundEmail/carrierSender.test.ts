import { describe, expect, it } from 'vitest';
import { trackingNumbersInText } from '../parcelsFromText';
import { carrierNames, carriersOfAddress } from './carrierSender';
import { readInboundMail } from './mail';
import { shopNamedIn } from './shopName';

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

  it('takes a person writing from free mail that shares a carrier\'s domain or name for no carrier', async () => {
    for (const domain of ['web.de', 'yandex.ru', 'laposte.net', 'post.cz', 'post.sk', 'post.com', 'skynet.be']) {
      expect(carriersOfAddress(`alex@${domain}`)).toEqual([]);
    }
    // A carrier's mail from a host under it still is the carrier's.
    expect(carriersOfAddress('noreply@delivery.yandex.ru')).toEqual(['yandex-delivery']);
    // The person's parcel is named after them, and its number after no carrier.
    const text = 'Hallo, hier die Sendungsnummer: 123456789012\nGruss Alex';
    const mail = await readInboundMail(new TextEncoder().encode(['MIME-Version: 1.0', 'Content-Type: text/plain; charset=utf-8',
      'From: Alex <alex@web.de>', 'Subject: Sendungsnummer', '', ...text.split('\n')].join('\r\n')));
    const carriers = carriersOfAddress(mail.origin);
    expect(carriers.length ? shopNamedIn(mail.subject, mail.text, carrierNames(carriers)) : mail.sender).toBe('Alex');
    expect(trackingNumbersInText(mail.text, carriers)[0]?.match.carrier).toBe('unknown');
  });

  it('takes a name across endings as one company\'s, not a word several posts or a government write under', () => {
    expect(carriersOfAddress('noreply@jtexpress.my')).toEqual(expect.arrayContaining(['j-and-t']));
    expect(carriersOfAddress('noreply@royalmail.co.uk')).toEqual(expect.arrayContaining(['royal-mail']));
    expect(carriersOfAddress('info@post.ee')).toEqual([]);
    expect(carriersOfAddress('info@ems.fr')).toEqual([]);
    expect(carriersOfAddress('info@hacienda.gob.mx')).toEqual([]);
  });

  it('names the carriers as their emails do', () => {
    expect(carrierNames(['dhl-express'])).toContain('DHL Express');
  });
});
