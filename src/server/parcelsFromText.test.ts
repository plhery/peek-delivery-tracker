import { afterEach, describe, expect, it, vi } from 'vitest';
import { addParcelsFromText, trackingNumbersInText } from './parcelsFromText';
import { SupabaseServiceClient } from './supabase';

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

  it('reads a label alone on its line above its number, as a shop\'s table prints one', () => {
    expect(read('Livraison\n\nExpress \n\nVotre numéro de colis\n\n1234567899 \n\nPaiement')).toEqual(['1234567899 unknown']);
    expect(read('Tracking number\n1234567899')).toEqual(['1234567899 unknown']);
    expect(read('Sendungsnummer\n\n1234 5678 99')).toEqual(['1234567899 unknown']);
    for (const text of [
      // Words that label no tracking number, a sentence, and a number that shares its line with words stay unread.
      'Numéro de commande\n\n1234567899', 'Service client\n\n0800 123 456', 'Merci pour votre commande !\n\n1234567899',
      'Tracking number\n\nPlease allow a day for 1234567899', 'Livraison\n\nExpress',
    ]) expect(read(text), text).toEqual([]);
  });

  it('reads a label and its number set in bold or italics, as a forwarded email\'s plain text marks them', () => {
    for (const text of ['*Sendungsnummer*: 1234567899', '*Sendungsnummer:* 1234567899', '**Tracking number:** 1234567899',
      '_Numéro de suivi_ : 1234567899', 'Sendungsnummer: *1234567899*', '*Sendungsnummer*\n\n1234567899', '*Sendungsnummer:*\n*1234567899*']) {
      expect(read(text), text).toEqual(['1234567899 unknown']);
    }
    // Bold words that label no tracking number stay unread, and a mark inside a word is no mark.
    for (const text of ['*Bestellnummer*: 1234567899', '*Kundennummer:* *1234567899*', 'Code *WINTER2026*', 'Ref_1234567899_x'])
      expect(read(text), text).toEqual([]);
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

describe('a postcode that comes with a text', () => {
  afterEach(() => { vi.restoreAllMocks(); });

  /** A database that adds each number as a new parcel, without asking anyone. */
  function database() {
    const service = new SupabaseServiceClient('https://database.example', 'service-key');
    vi.spyOn(service, 'latestLookupCountry').mockResolvedValue(null);
    vi.spyOn(service, 'accountTrackingNumbers').mockResolvedValue(new Map());
    vi.spyOn(service, 'claimAccountTracking').mockResolvedValue({ allowed: true, scope: null, overallUsed: 1 } as never);
    const create = vi.spyOn(service, 'createAccountPackage').mockImplementation(async (_, number) => `new-${number}`);
    vi.spyOn(service, 'getPackage').mockImplementation(async (id) => ({ id, carrier_data: {}, dpd_postcode: null }));
    const update = vi.spyOn(service, 'updatePackage').mockResolvedValue(undefined as never);
    vi.spyOn(service, 'ignoreTypedTrackingSupportCases').mockResolvedValue(0);
    vi.spyOn(service, 'enqueueSyncJob').mockImplementation(async ({ packageId }) => ({ row: { id: `job-${packageId}` }, queued: true }) as never);
    return { service, create, update };
  }
  // A synthetic DPD Germany number, whose carrier takes a postcode, and UPS's published sample, whose carrier takes none.
  const DPD = '09981234567452';
  const text = `DPD tracking number: ${DPD}\nUPS tracking number: ${UPS}`;

  it('goes to each parcel whose carrier takes one, as the Add sheet sends it, and to no other', async () => {
    const { service, create } = database();
    const added = await addParcelsFromText(service, 'user', text, 'Example Shop', ['dpd-de'], {
      trust: 'named', postcode: { postcode: ' 10115 ', country: '' },
    });
    expect(added.map(({ trackingNumber, carrier, outcome, ...rest }) => [trackingNumber, carrier, outcome, 'postcode' in rest]))
      .toEqual([[UPS, 'ups', 'added', false], [DPD, 'dpd-de', 'added', true]]);
    expect(create).toHaveBeenCalledWith('user', DPD, 'Example Shop', 'dpd-de', null, '10115');
    expect(create).toHaveBeenCalledWith('user', UPS, 'Example Shop', 'ups', null, null);
  });

  it('refuses a parcel whose carrier takes no postcode from its country, as the Add sheet does', async () => {
    const { service, create } = database();
    const [ups, dpd] = await addParcelsFromText(service, 'user', text, 'Example Shop', ['dpd-de'], {
      trust: 'named', postcode: { postcode: '75011', country: 'FR' },
    });
    expect(dpd).toMatchObject({ carrier: 'dpd-de', outcome: 'refused', reason: expect.stringContaining('takes no delivery postcode from that country') });
    expect(ups).toMatchObject({ carrier: 'ups', outcome: 'added' });
    expect(create).toHaveBeenCalledOnce();
  });
});
