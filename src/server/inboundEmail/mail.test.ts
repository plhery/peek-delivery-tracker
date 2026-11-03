import { describe, expect, it } from 'vitest';
import { trackingNumbersInText } from '../parcelsFromText';
import { htmlLinks, htmlText, readInboundMail } from './mail';

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
    expect(htmlLinks('<a href="mailto:a@example.com">m</a><a href=\'https://x.example/?a=1&amp;b=2\'>x</a>')).toEqual(['https://x.example/?a=1&b=2']);
  });
});
