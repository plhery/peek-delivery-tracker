import { describe, expect, it } from 'vitest';
import { trackingNumbersInText } from './parcelsFromText';

// Every number here is made up, but UPS's published sample.
const UPS = '1Z999AA10123456784';
const read = (...args: Parameters<typeof trackingNumbersInText>) => trackingNumbersInText(...args)
  .map(({ normalized, match }) => `${normalized} ${match.carrier}`);

describe('the numbers a text names, by how far its sender is trusted', () => {
  it('reads an email for the numbers a carrier claims or a label names, never for one standing alone', () => {
    expect(read(`Shipped with UPS: ${UPS}`)).toEqual([`${UPS} ups`]);
    expect(read('Tracking number: 1234567899')).toEqual(['1234567899 unknown']);
    expect(read('1234567899')).toEqual([]);
    // A labelled number no carrier's shape fits is not an email's to add.
    expect(read('Tracking ID: AB12CD34EF')).toEqual([]);
  });

  it('takes from a text an assistant sends a number alone or labelled, though no carrier\'s shape fits it', () => {
    expect(read('1234567899', [], 'sent')).toEqual(['1234567899 unknown']);
    expect(read(' 1234 5678 9012 ', [], 'sent')).toEqual(['123456789012 unknown']);
    expect(read('AB12CD34EF', [], 'sent')).toEqual(['AB12CD34EF unknown']);
    expect(read('Tracking ID: AB12CD34EF', [], 'sent')).toEqual(['AB12CD34EF unknown']);
    expect(read('Delivery By VIR_TR\nTracking ID: AB12CD34EF', [], 'sent')).toEqual(['AB12CD34EF unknown']);
    for (const text of [
      // A sentence, a word, a date, a link, an Amazon order number and a label with no number after it are no number.
      'your order 12345678 shipped', 'additional', '2026-11-21', '21.11.2026', 'https://example.test/track/1234567899', '123-1234567-1234567',
      'Tracking number: pending', 'Tracking number: N/A', 'Tracking number: 2 of 3',
      // One too short for a tracking number needs its carrier named.
      '123456', 'Tracking number: TR4X9',
    ]) expect(read(text, [], 'sent'), text).toEqual([]);
  });

  it('takes a number however short when the assistant names its carrier', () => {
    expect(read('AB12CD34EF', ['jp-home'], 'named')).toEqual(['AB12CD34EF jp-home']);
    expect(read('Tracking ID: AB12CD34EF', ['jp-home'], 'named')).toEqual(['AB12CD34EF jp-home']);
    expect(read('123456', ['jp-home'], 'named')).toEqual(['123456 jp-home']);
    expect(read('1234567899', ['jp-home'], 'named')).toEqual(['1234567899 jp-home']);
    // A number another carrier claims stays that carrier's.
    expect(read(UPS, ['jp-home'], 'named')).toEqual([`${UPS} ups`]);
    // Still no order number, no sentence and no link.
    for (const text of ['123-1234567-1234567', 'Order 123-1234567-1234567', 'your order 12345678 shipped', 'https://example.test/track/AB12CD34EF']) {
      expect(read(text, ['jp-home'], 'named'), text).toEqual([]);
    }
  });
});

// The Gmail filter's word for "order" forwards order confirmations and offers too: made-up ones, as shops write them.
const ORDER_EMAILS = {
  confirmation: `Thank you for shopping with us!
Order number: 60123456789
Order date: 21 Nov 2026
Customer number: 4012345678
Art. no. 1234567001 Wool-blend jumper, size M, 1 × 39.99 EUR
Delivery: Standard, 3-5 working days
We'll let you know when your order is on its way.`,
  commande: `Confirmation de commande
Bonjour, merci pour votre commande !
Numéro de commande : 1234567890
Commande n° 405123456789 du 21/11/2026
Référence client : 98765432
Total TTC : 59,90 €
Vous recevrez un e-mail avec le numéro de suivi dès l'expédition.`,
  offer: `Order now: 20% off everything until Sunday!
Use code WINTER2026 at checkout. Orders over 50 € ship free.
Call us on +44 20 7946 0123. Offer ref 202611210001.
Unsubscribe: https://example.test/u/1234567899123456`,
};

describe('an order confirmation or an offer', () => {
  it('adds no number: an order number, a customer number, a code or a phone number is no tracking number', () => {
    for (const [name, text] of Object.entries(ORDER_EMAILS)) expect(read(text), name).toEqual([]);
  });

  it('still adds the tracking number an order email names', () => {
    expect(read(`${ORDER_EMAILS.commande}\nNuméro de suivi : ${UPS}`)).toEqual([`${UPS} ups`]);
  });
});
