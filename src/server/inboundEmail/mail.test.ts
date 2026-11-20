import { describe, expect, it } from 'vitest';
import { trackingNumbersInText } from '../parcelsFromText';
import { carriersOfAddress } from './carrierSender';
import { headerFromAddress, htmlLinks, htmlText, readInboundMail } from './mail';
import { shopNamedIn } from './shopName';

// Synthetic mails only: example.com senders, UPS's published sample number and made-up numbers in valid shapes.
const UPS = '1Z999AA10123456784';
const POSTAL = 'LX472930165CN';

function mime(headers: Record<string, string>, body: string, type = 'text/plain'): Uint8Array {
  const lines = Object.entries({ 'MIME-Version': '1.0', 'Content-Type': `${type}; charset=utf-8`, ...headers })
    .map(([name, value]) => `${name}: ${value}`);
  return new TextEncoder().encode(`${lines.join('\r\n')}\r\n\r\n${body.replace(/\n/g, '\r\n')}`);
}

function alternative(headers: Record<string, string>, text: string | null, html: string): Uint8Array {
  const parts = [
    ...(text === null ? [] : [`--b\r\nContent-Type: text/plain; charset=utf-8\r\n\r\n${text}`]),
    `--b\r\nContent-Type: text/html; charset=utf-8\r\n\r\n${html}`,
    '--b--',
  ];
  return mime(headers, parts.join('\r\n'), 'multipart/alternative; boundary="b"').slice();
}

describe('reading a forwarded shipping email', () => {
  it('reads the HTML a line per block, with a table row on one line and its links', async () => {
    const mail = await readInboundMail(alternative(
      { From: '"Example Shop" <orders@mail.example-shop.com>', To: 'add+abc@in.example.com', Subject: 'Your order has shipped' },
      null,
      `<html><head><style>p{color:red}</style><title>Shipped</title></head><body>
        <p>Hello Alex,</p><table><tr><td>Tracking&nbsp;number:</td><td><b>${UPS}</b></td></tr></table>
        <a href="https://carrier.example/track?num=${POSTAL}&amp;lang=en">Track it</a>
        <script>var n = "${POSTAL}";</script></body></html>`,
    ));
    expect(mail.sender).toBe('Example Shop');
    expect(mail.text.split('\n')).toContain(`Tracking number: ${UPS}`);
    expect(mail.text).toContain(`https://carrier.example/track?num=${POSTAL}&lang=en`);
    expect(mail.text).not.toContain('var n');
    expect(mail.text).not.toContain('color:red');
    expect(mail.confirmation).toBeNull();
  });

  it('prefers the plain text part, and keeps the HTML\'s links beside it', async () => {
    const mail = await readInboundMail(alternative(
      { From: 'Example Shop <orders@example-shop.com>', Subject: 'Shipped' },
      `Your parcel ${UPS} is on its way.`,
      '<p>Your parcel is on its way.</p><a href="https://carrier.example/t/abc">Track</a>',
    ));
    expect(mail.text).toBe(`Shipped\nYour parcel ${UPS} is on its way.\nhttps://carrier.example/t/abc`);
  });

  it('names a sender without a display name by its domain, without the mail system\'s part', async () => {
    const mail = await readInboundMail(mime({ From: 'shipment-tracking@email.example-shop.co.uk', Subject: 'Shipped' }, UPS));
    expect(mail.sender).toBe('example-shop.co.uk');
  });

  it.each([
    ['Gmail', 'Fwd: Your order has shipped', '---------- Forwarded message ---------\nFrom: Example Shop <orders@example-shop.com>\nDate: Mon, 2 Nov 2026'],
    ['Apple Mail', 'Fwd: Shipped', 'Begin forwarded message:\n\nFrom: "Example Shop" <orders@example-shop.com>\nSubject: Shipped'],
    ['Outlook in French', 'TR: Expédié', '________________________________\nDe : Example Shop <orders@example-shop.com>\nEnvoyé : lundi'],
    ['Outlook in German', 'WG: Versendet', 'Von: Example Shop [mailto:orders@example-shop.com]\nGesendet: Montag'],
  ])('labels a message forwarded by hand in %s with the shop that wrote it', async (_, subject, block) => {
    const mail = await readInboundMail(mime({ From: 'Alex <alex@example.com>', Subject: subject }, `See below\n\n${block}\n\nTracking: ${UPS}`));
    expect(mail.sender).toBe('Example Shop');
  });

  it('keeps a long sender name within a parcel name\'s 80 characters', async () => {
    const mail = await readInboundMail(mime({ From: `"${'Shop '.repeat(30)}" <a@example.com>`, Subject: 'x' }, UPS));
    expect([...mail.sender].length).toBeLessThanOrEqual(80);
  });

  it('never opens an attachment', async () => {
    const raw = mime({ From: 'Shop <a@example.com>', Subject: 'Invoice' }, [
      '--m\r\nContent-Type: text/plain; charset=utf-8\r\n\r\nYour invoice is attached.',
      `--m\r\nContent-Type: text/plain; name="label.txt"\r\nContent-Disposition: attachment; filename="label.txt"\r\n\r\n${UPS}`,
      '--m--',
    ].join('\r\n'), 'multipart/mixed; boundary="m"');
    const mail = await readInboundMail(raw);
    expect(mail.text).not.toContain(UPS);
  });
});

describe('the From address in an email\'s header', () => {
  const raw = (text: string) => new TextEncoder().encode(text);

  it('reads the address of the email\'s own From line, and nothing below its header', () => {
    expect(headerFromAddress(raw('Subject: Hi\r\nFrom: Shop <Orders@Shop.example>\r\n\r\nFrom: other@example.com'))).toBe('orders@shop.example');
    expect(headerFromAddress(raw('from:orders@shop.example\n\nbody'))).toBe('orders@shop.example');
    expect(headerFromAddress(raw('From:\r\n "Shop, Inc." <orders@shop.example>\r\n\r\n'))).toBe('orders@shop.example');
    expect(headerFromAddress(raw('X-Original-From: orders@shop.example\r\nReply-To: a@shop.example\r\n\r\nFrom: b@shop.example'))).toBe('');
    expect(headerFromAddress(raw('From: Shop\r\n\r\n'))).toBe('');
  });
});

describe('the numbers a shipping email names', () => {
  const numbers = async (subject: string, html: string) => {
    const mail = await readInboundMail(alternative({ From: 'Example Shop <orders@example-shop.com>', Subject: subject }, null, html));
    return trackingNumbersInText(mail.text).map(({ normalized, match }) => ({ number: normalized, carrier: match.carrier }));
  };

  it('takes no word for a number, though a carrier\'s shape fits it', async () => {
    expect(await numbers('Dispatched: your order', `<p>Order # 302-4571983-2294617</p>
      <p>For additional information on the scope of this notice, please contact our customer service.</p>
      <a href="https://shop.example/help/additional">Help</a>`)).toEqual([]);
  });

  it('takes the number a label names though its shape proves no carrier, and no order or customer number', async () => {
    expect(await numbers('Your order 12345678 has shipped', `<table>
      <tr><td>Order number:</td><td>87654321</td></tr>
      <tr><td>Customer number:</td><td>4455667788</td></tr>
      <tr><td>Tracking Number:</td><td><b>1234567899</b></td></tr></table>
      <p>Questions? Call 044 123 45 67.</p>`)).toEqual([{ number: '1234567899', carrier: 'unknown' }]);
    expect(await numbers('Votre colis arrive', '<p>Votre colis 01234567890123 de Example Shop sera livré demain.</p>'))
      .toEqual([{ number: '01234567890123', carrier: 'unknown' }]);
  });

  it('lists a certain number before a labelled one, each once', async () => {
    expect(await numbers('Shipped', `<p>Sendungsnummer 1234567899</p><p>Tracking number: ${UPS}</p><p>Sendungsnummer: 1234567899</p>`))
      .toEqual([{ number: UPS, carrier: 'ups' }, { number: '1234567899', carrier: 'unknown' }]);
  });

  it('reads past the invisible padding of a preview line', async () => {
    const padding = '&#847;&zwnj;&nbsp;&shy; \u200B'.repeat(4000);
    expect(await numbers('Your parcel is on its way', `<div style="display:none">On its way${padding}</div><p>Parcel ${POSTAL}</p>`))
      .toEqual([{ number: POSTAL, carrier: expect.any(String) }]);
  });
});

describe('a shop\'s order email forwarded by hand from Gmail', () => {
  // A made-up DPD-shaped number, and a shop's mail system's click-tracking links, each some 420 characters long.
  const DPD = '01234567890123';
  const link = (index: number) => `https://t.email.shop.example/r/?id=h${String(index).padStart(6, '0')}${'a1b2c3d4'.repeat(5)},${'e5f6'.repeat(40)}&p1=${'Q2xpY2s'.repeat(30)}`;
  const nav = ['Nouveautés', 'Femme', 'Homme', 'Enfant', 'Maison', 'Grandes tailles', 'Marques', 'Bons plans', 'Soldes', 'Inspirations'];
  const products = ['Robe portefeuille', 'Pull col rond', 'Jean droit', 'Housse de couette', 'Coussin velours', 'Baskets cuir', 'Sac cabas', 'Écharpe laine'];
  let linked = 0;
  const next = () => link(linked += 1);
  // Gmail writes a link's address after its words in the plain text it makes, as `words <address>`.
  const plain = [
    '---------- Forwarded message ---------',
    'De : Example Mode <noreply@email.shop.example>',
    'Date: lun. 10 nov. 2026 à 09:12',
    'Subject: Votre commande 123456789 a été expédiée',
    'To: <alex@example.com>',
    '',
    '',
    `Voir la version en ligne <${next()}>`,
    `[image: Example Mode] <${next()}>`,
    nav.map((name) => `${name} <${next()}>`).join(' '),
    `[image: Livraison offerte dès 49 €] <${next()}>`,
    'Bonjour Alex,',
    'Bonne nouvelle, votre commande n° 123456789 du 8 novembre a été expédiée.',
    ...products.flatMap((name) => [`[image: ${name}] <${next()}>`, `${name} <${next()}>`, 'Taille : 38 - Quantité : 1']),
    'Votre colis est confié à DPD.',
    `Numéro de suivi : ${DPD}`,
    `Suivre mon colis <${next()}>`,
    ...nav.map((name) => `${name} <${next()}>`),
    'Example Mode, service client.',
  ].join('\n');
  const html = `<div>---------- Forwarded message ---------<br>De : Example Mode &lt;noreply@email.shop.example&gt;</div>
    <div class="gmail_quote"><table>${[...Array(linked)].map((_, index) => `<tr><td><a href="${link(index + 1)}">lien</a></td></tr>`).join('')}
    <tr><td>Numéro de suivi :</td><td>${DPD}</td></tr></table></div>`;

  it('reads the number past the addresses of its links', async () => {
    expect(plain.indexOf(DPD)).toBeGreaterThan(12_000);
    const mail = await readInboundMail(alternative({ From: 'Alex <alex@example.com>', Subject: 'Fwd: Votre commande 123456789 a été expédiée' }, plain, html));
    expect(mail.sender).toBe('Example Mode');
    expect(mail.text.length).toBeLessThanOrEqual(16_000);
    expect(trackingNumbersInText(mail.text).map(({ normalized }) => normalized)).toEqual([DPD]);
  });

  it('reads a link to the carrier\'s site first, after however many of the shop\'s own', async () => {
    const shop = [...Array(80)].map((_, index) => `https://www.shop.example/c/${String(index).padStart(4, '0')}?utm_source=email&utm_medium=x`);
    const tracking = `https://trace.dpd.fr/fr/trace/${DPD}`;
    const forwarded = `Votre colis est en route.\n${shop.map((address) => `Voir <${address}>`).join('\n')}\nSuivre mon colis <${tracking}>`;
    const mail = await readInboundMail(alternative({ From: 'Example Mode <noreply@email.shop.example>', Subject: 'Votre colis est en route' },
      forwarded, `${shop.map((address) => `<a href="${address}">Voir</a>`).join('')}<a href="${tracking}">Suivre mon colis</a>`));
    expect(shop.join('\n').length).toBeGreaterThan(3_000);
    expect(trackingNumbersInText(mail.text).map(({ normalized, match }) => [normalized, match.carrier])).toEqual([[DPD, 'dpd-fr']]);
    // A text without HTML has its addresses read as links too.
    const text = await readInboundMail(mime({ From: 'Example Mode <noreply@email.shop.example>', Subject: 'Votre colis est en route' }, forwarded));
    expect(text.text.split('\n')[1]).toBe('Votre colis est en route.');
    expect(trackingNumbersInText(text.text).map(({ normalized }) => normalized)).toEqual([DPD]);
  });
});

describe('a carrier\'s own email', () => {
  async function read(from: string, subject: string, body: string) {
    const mail = await readInboundMail(mime({ From: from, Subject: subject }, body));
    return trackingNumbersInText(mail.text, carriersOfAddress(mail.origin)).map(({ normalized, match }) => ({ number: normalized, carrier: match.carrier }));
  }

  it('gives its carrier to a number whose shape proves none', async () => {
    expect(await read('DHL <noreply@dhl.com>', 'Unterwegs', 'Sendungsnummer 4612345678 von Example Shop wurde abgeholt.'))
      .toEqual([{ number: '4612345678', carrier: 'dhl' }]);
    expect(await read('info@planzergroup.com', 'Sendung unterwegs', 'Sendungsnummer: 92123.0012345678'))
      .toEqual([{ number: '921230012345678', carrier: 'planzer' }]);
    expect(await read('info@planzergroup.com', 'Ihre Sendung ist unterwegs zu Ihnen', 'Zustelltag:\nMontag\nSendungsnummer:\n\n92123.0012345678\nBitte überprüfen Sie Ihre Adresse.'))
      .toEqual([{ number: '921230012345678', carrier: 'planzer' }]);
    expect(await read('info@planzergroup.com', 'Ihre Sendung ist unterwegs zu Ihnen', 'Zustelltag:\tMontag\nSendungsnummer:\t92123.0012345678\nBitte überprüfen Sie Ihre Adresse.'))
      .toEqual([{ number: '921230012345678', carrier: 'planzer' }]);
  });

  it('leaves a number another carrier claims with that carrier', async () => {
    expect(await read('DHL <noreply@dhl.com>', 'Unterwegs', `Tracking number: ${UPS}`)).toEqual([{ number: UPS, carrier: 'ups' }]);
  });

  it('takes its one number that stands without a label', async () => {
    expect(await read('DHL <noreply@dhl.com>', 'Livré', 'Le bordereau N° 4612345678 de Example Shop a été livré.'))
      .toEqual([{ number: '4612345678', carrier: 'dhl' }]);
    const [parcel] = await read('DPD <noreply@dpd.ch>', 'Colis de Example Shop livré', 'Votre colis\n01234567890123\na été livré.');
    expect(parcel).toEqual({ number: '01234567890123', carrier: expect.stringMatching(/^dpd/) });
  });

  it('takes none when two could be it, or when the one is a phone number', async () => {
    expect(await read('DHL <noreply@dhl.com>', 'Info', 'Kunde 4612345678\nReferenz 4698765432')).toEqual([]);
    expect(await read('DHL <noreply@dhl.com>', 'Info', 'Fragen? Telefon 0848123456')).toEqual([]);
    expect(await read('DHL <noreply@dhl.com>', 'Info', 'Fragen? +41 848123456')).toEqual([]);
  });

  it('reads an HTML email whose accents are written as entities', async () => {
    const mail = await readInboundMail(alternative({ From: 'DHL <noreply@dhl.com>', Subject: 'Livré' }, null,
      '<p>Le bordereau N&deg;&nbsp;4612345678 a &eacute;t&eacute; livr&eacute;.</p><p>Exp&eacute;diteur&#8239;: Example Shop</p>'));
    expect(mail.text.split('\n').slice(1)).toEqual(['Le bordereau N° 4612345678 a été livré.', 'Expéditeur\u202F: Example Shop']);
    expect(shopNamedIn(mail.subject, mail.text)).toBe('Example Shop');
    expect(trackingNumbersInText(mail.text, carriersOfAddress(mail.origin)).map(({ normalized, match }) => [normalized, match.carrier]))
      .toEqual([['4612345678', 'dhl']]);
  });

  it('takes no unlabelled number from a shop', async () => {
    expect(await read('Example Shop <orders@example-shop.com>', 'Livré', 'Le bordereau N° 4612345678 a été livré.')).toEqual([]);
  });

  it('knows the carrier of an email forwarded by hand', async () => {
    expect(await read('Alex <alex@example.com>', 'Fwd: Unterwegs',
      '---------- Forwarded message ---------\nFrom: DHL <noreply@dhl.com>\nSubject: Unterwegs\n\nSendungsnummer 4612345678 ist unterwegs.'))
      .toEqual([{ number: '4612345678', carrier: 'dhl' }]);
  });
});

describe('a hostile email', () => {
  const big = 900_000;
  it.each([
    ['an unclosed tag', '<'.repeat(big), 'text/html'],
    ['links that never end', '<a'.repeat(big / 2), 'text/html'],
    ['quotes that never close', '<a href="'.repeat(big / 9), 'text/html'],
    ['styles that never close', '<style'.repeat(big / 6), 'text/html'],
    ['comments that never close', '<!--'.repeat(big / 4), 'text/html'],
    ['breaks that never close', '<br'.repeat(big / 3), 'text/html'],
    ['a forwarded From line of spaces', `From: a${' '.repeat(big)}x`, 'text/plain'],
    ['a forwarded named sender of spaces', `From: ${'a '.repeat(big / 2)}<a@example.com>`, 'text/plain'],
  ])('is read in bounded time, with %s', async (_, body, type) => {
    const started = performance.now();
    const mail = await readInboundMail(mime({ From: `"${'Shop '.repeat(2_000)}via x" <a@example.com>`, Subject: 'Fwd: x' }, body, type));
    trackingNumbersInText(mail.text);
    expect(performance.now() - started).toBeLessThan(2_000);
  });
});

describe('Gmail\'s request to confirm forwarding', () => {
  const confirmation = (from: string, link: string) => mime({
    From: `Gmail Team <${from}>`,
    Subject: '(#123456789) Gmail Forwarding Confirmation - Receive Mail from alex@example.com',
  }, `alex@example.com has requested to automatically forward mail to your email address.\nConfirmation code: 123456789\n\nTo allow alex@example.com to automatically forward mail to your address, please click the link below to confirm the request:\n\n${link}\n`);

  it('keeps its code and its link on Google', async () => {
    const mail = await readInboundMail(confirmation('forwarding-noreply@google.com', 'https://mail-settings.google.com/mail/vf-%5Bexample%5D-example'));
    expect(mail.confirmation).toEqual({ code: '123456789', link: 'https://mail-settings.google.com/mail/vf-%5Bexample%5D-example' });
  });

  it('keeps the link written as the database takes it, and none with a user or a port', async () => {
    const normalised = await readInboundMail(confirmation('forwarding-noreply@google.com', 'https://MAIL.google.com?vf=x'));
    expect(normalised.confirmation?.link).toBe('https://mail.google.com/?vf=x');
    for (const link of ['https://x@mail.google.com/vf', 'https://mail.google.com:443/vf', 'https://mail.google.com:8443/vf']) {
      const mail = await readInboundMail(confirmation('forwarding-noreply@google.com', link));
      expect(mail.confirmation?.link ?? '').not.toMatch(/@|:\d/);
    }
  });

  it('never keeps a link that leads off Google', async () => {
    const mail = await readInboundMail(confirmation('forwarding-noreply@google.com', 'https://mail-settings.google.com.example.net/vf'));
    expect(mail.confirmation).toEqual({ code: '123456789', link: null });
  });

  it('is only Gmail\'s own', async () => {
    const mail = await readInboundMail(confirmation('forwarding-noreply@example.com', 'https://mail-settings.google.com/mail/vf-x'));
    expect(mail.confirmation).toBeNull();
  });
});

describe('the HTML helpers', () => {
  it('decode entities and keep only web links', () => {
    expect(htmlText('<p>A&amp;B&#33;&#x21;</p><div>next</div>')).toBe('A&B!!\nnext');
    expect(htmlText('<p>&Eacute;t&eacute; &agrave; 19&euro;&ensp;&rsaquo;</p><p>?a=1&section=2&amp;b=3</p>')).toBe('Été à 19€ ›\n?a=1&section=2&b=3');
    expect(htmlLinks('<a data-href="https://wrong.example" href=https://carrier.example/parcel>Track</a>'))
      .toEqual(['https://carrier.example/parcel']);
    expect(htmlLinks('<a href="mailto:a@example.com">m</a><a href=\'https://x.example/?a=1&amp;b=2\'>x</a>')).toEqual(['https://x.example/?a=1&b=2']);
  });
});
