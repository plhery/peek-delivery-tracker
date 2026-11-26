import { NextRequest } from 'next/server';
import { afterEach, beforeEach, describe, expect, it, vi } from 'vitest';
import { GET as exportAccount } from '../../app/api/account/export/route';
import { DELETE as closeInbox, GET as readInbox, PATCH as notifyInbox, POST as openInbox } from '../../app/api/account/inbox/route';
import { POST as receive } from '../../app/api/inbound/email/route';
import contract from '../../contracts/openapi.json';
import { AMAZON_ACCOUNT_MESSAGE } from '../lib/amazon';
import { sign } from '../../ops/cloudflare/inbound-email-worker.js';
import * as eligibility from './amazonShippingEligibility';
import { HttpError } from './api';
import * as parcels from './parcelsFromText';
import { SupabaseAuthenticator } from './auth';
import * as background from './background';
import * as inboundMail from './inboundEmail/mail';
import { mailResult } from './inboundEmail/receive';
import * as monitoring from './observability';
import * as shopWaits from './shopWaits';
import { addParcelsFromText, trackingNumbersInText, type ParcelAddition } from './parcelsFromText';
import { SupabaseError, SupabaseServiceClient, SupabaseUserClient, type InboundMailbox } from './supabase';

// Synthetic identifiers, addresses and numbers only: UPS's published sample and made-up numbers in valid shapes.
const userId = '5b000000-0000-4000-a000-000000000001';
const UPS = '1Z999AA10123456784';
const DHL = 'JJD000390000000001';
const SECRET = 'synthetic-inbound-secret-0123456789abcdef'; // gitleaks:allow -- made-up signing secret, used only in tests
const token = 'abcdefghijklmnop';
const recipient = `add+${token}@in.example.com`;
const none = { params: Promise.resolve({}) };
const earlier = { at: '2026-11-02T09:00:00.000Z', sender: 'Example Shop', result: 'added' as const, added: 1 };
const mailbox: InboundMailbox = {
  token, createdAt: '2026-11-03T10:00:00.000Z', usedToday: 2, lastMailAt: null, lastResult: null, lastAdded: null, recent: [earlier], confirmation: null,
  notify: true,
};

beforeEach(() => {
  vi.stubEnv('SUPABASE_URL', 'https://database.example');
  vi.stubEnv('SUPABASE_PUBLISHABLE_KEY', 'test-public');
  vi.stubEnv('SUPABASE_SERVICE_ROLE_KEY', 'test-service-key');
  vi.stubEnv('INBOUND_EMAIL_ADDRESS', 'add+{token}@in.example.com');
  vi.stubEnv('INBOUND_EMAIL_SECRET', SECRET);
  vi.stubEnv('INBOUND_EMAIL_ACCOUNTS', userId);
  vi.spyOn(console, 'log').mockImplementation(() => undefined);
  vi.spyOn(console, 'error').mockImplementation(() => undefined);
  vi.spyOn(SupabaseAuthenticator.prototype, 'validate').mockResolvedValue({ id: userId, email: 'alex@example.com', authenticatedAt: null, sessionId: null });
});
afterEach(() => { vi.restoreAllMocks(); vi.unstubAllEnvs(); });

function signedIn(method: string, body?: unknown) {
  return new NextRequest('https://peek.example/api/account/inbox', {
    method,
    headers: { Authorization: 'Bearer inbox-test', ...(body === undefined ? {} : { 'content-type': 'application/json' }) },
    ...(body === undefined ? {} : { body: JSON.stringify(body) }),
  });
}

describe('the account\'s forwarding address', () => {
  it('is not there when the server takes no email, or for an account it is not open to', async () => {
    vi.stubEnv('INBOUND_EMAIL_ADDRESS', '');
    expect((await readInbox(signedIn('GET'), none)).status).toBe(404);
    vi.stubEnv('INBOUND_EMAIL_ADDRESS', 'add+{token}@in.example.com');
    vi.stubEnv('INBOUND_EMAIL_ACCOUNTS', 'someone@example.com');
    expect((await openInbox(signedIn('POST', {}), none)).status).toBe(404);
  });

  it('shows the address, never the bare token, as the contract lists it', async () => {
    vi.spyOn(SupabaseServiceClient.prototype, 'inboundMailbox').mockResolvedValue(mailbox);
    const answer = await (await readInbox(signedIn('GET'), none)).json();
    expect(answer).toEqual({
      inbox: { address: recipient, createdAt: mailbox.createdAt, usedToday: 2, lastMailAt: null, lastResult: null, lastAdded: null, recent: [earlier], confirmation: null, notify: true },
      dailyLimit: 30,
    });
    expect(Object.keys(answer.inbox).sort()).toEqual(Object.keys(contract.components.schemas.AddByEmail.properties).sort());
    expect(contract.components.schemas.AddByEmail.required).not.toContain('notify');
  });

  it('names the shop whose account alone shows the number of an email that held none', async () => {
    const at = '2026-11-03T11:00:00.000Z';
    const recent = [
      ...['Amazon.it', 'amazon.co.uk', 'Amazon', 'Amazon.com.be'].map((sender) => ({ at, sender, result: 'none' as const, added: 0 })),
      { at, sender: 'Amazon.de', result: 'added' as const, added: 1 },
      { at, sender: 'Amazon Shipping', result: 'none' as const, added: 0 },
      { at, sender: 'Example Shop', result: 'none' as const, added: 0 },
    ];
    vi.spyOn(SupabaseServiceClient.prototype, 'inboundMailbox').mockResolvedValue({ ...mailbox, recent });
    const answer = await (await readInbox(signedIn('GET'), none)).json();
    expect(answer.inbox.recent.map((entry: { account?: string }) => entry.account ?? null)).toEqual(['Amazon', 'Amazon', 'Amazon', 'Amazon', null, null, null]);
    // Every field the contract lists, but the wait an email whose shop hasn't shipped has instead.
    expect([...Object.keys(answer.inbox.recent[0]), 'waiting'].sort()).toEqual(Object.keys(contract.components.schemas.AddByEmailEntry.properties).sort());
    expect(answer.inbox.recent[4]).toEqual(recent[4]);
  });

  it('reads an email that started a wait as one that held no number, with the wait on its line', async () => {
    const at = '2026-11-03T11:00:00.000Z';
    const recent = [{ at, sender: 'Example Shop', result: 'waiting' as const, added: 0 }, { at, sender: 'Other Shop', result: 'none' as const, added: 0 }];
    vi.spyOn(SupabaseServiceClient.prototype, 'inboundMailbox').mockResolvedValue({ ...mailbox, lastMailAt: at, lastResult: 'waiting', lastAdded: 0, recent });
    const answer = await (await readInbox(signedIn('GET'), none)).json();
    expect(answer.inbox.recent).toEqual([{ at, sender: 'Example Shop', result: 'none', added: 0, waiting: true }, recent[1]]);
    expect(answer.inbox.lastResult).toBe('none');
  });

  it('is made once, and renewed only when asked', async () => {
    const read = vi.spyOn(SupabaseServiceClient.prototype, 'inboundMailbox').mockResolvedValue(null);
    const open = vi.spyOn(SupabaseServiceClient.prototype, 'openInboundMailbox').mockImplementation(async (_, fresh) => ({ ...mailbox, token: fresh }));
    const first = await openInbox(signedIn('POST', {}), none);
    expect(first.status).toBe(201);
    expect(open).toHaveBeenCalledWith(userId, expect.stringMatching(/^[a-z2-7]{16}$/), false);
    read.mockResolvedValue(mailbox);
    expect(await (await openInbox(signedIn('POST', {}), none)).json()).toMatchObject({ inbox: { address: recipient } });
    expect(open).toHaveBeenCalledTimes(1);
    const renewed = await (await openInbox(signedIn('POST', { renew: true }), none)).json();
    expect(open).toHaveBeenCalledTimes(2);
    expect(renewed.inbox.address).not.toBe(recipient);
    expect((await openInbox(signedIn('POST', { renew: 'yes' }), none)).status).toBe(400);
  });

  it('goes into the account export as the address, without the token alone', async () => {
    vi.spyOn(SupabaseServiceClient.prototype, 'assistantGrants').mockResolvedValue([]);
    vi.spyOn(SupabaseServiceClient.prototype, 'twinNumbers').mockResolvedValue([]);
    vi.spyOn(shopWaits, 'accountShopWaits').mockResolvedValue([]);
    vi.spyOn(SupabaseServiceClient.prototype, 'inboundMailbox').mockResolvedValue(mailbox);
    vi.spyOn(SupabaseServiceClient.prototype, 'accountLastOpened').mockResolvedValue(null);
    vi.spyOn(SupabaseUserClient.prototype, 'request').mockResolvedValue({ profile: null, ownCard: null, friends: [] });
    vi.spyOn(SupabaseUserClient.prototype, 'listPackages').mockResolvedValue([]);
    vi.spyOn(SupabaseUserClient.prototype, 'getNotificationPreferences').mockResolvedValue({ enabled_stages: [], email_on_delivery: null } as never);
    vi.spyOn(SupabaseUserClient.prototype, 'listDeliveryEmails').mockResolvedValue([]);
    const exported = await (await exportAccount(signedIn('GET'), none)).json();
    expect(exported.addByEmail).toEqual({ address: recipient, createdAt: mailbox.createdAt, usedToday: 2, lastMailAt: null, lastResult: null, lastAdded: null, recent: [earlier], confirmation: null, notify: true });
  });

  it('puts the twin numbers its emails named into the account export, read for this account', async () => {
    const twins = [{ packageId: '5b000000-0000-4000-a000-000000000002', earlier: 'LP00000000000001', later: 'JJD000390000000001', createdAt: '2026-11-24T13:10:00+00:00' }];
    vi.spyOn(SupabaseServiceClient.prototype, 'assistantGrants').mockResolvedValue([]);
    const read = vi.spyOn(SupabaseServiceClient.prototype, 'twinNumbers').mockResolvedValue(twins);
    vi.spyOn(shopWaits, 'accountShopWaits').mockResolvedValue([]);
    vi.spyOn(SupabaseServiceClient.prototype, 'inboundMailbox').mockResolvedValue(mailbox);
    vi.spyOn(SupabaseServiceClient.prototype, 'accountLastOpened').mockResolvedValue(null);
    vi.spyOn(SupabaseUserClient.prototype, 'request').mockResolvedValue({ profile: null, ownCard: null, friends: [] });
    vi.spyOn(SupabaseUserClient.prototype, 'listPackages').mockResolvedValue([]);
    vi.spyOn(SupabaseUserClient.prototype, 'getNotificationPreferences').mockResolvedValue({ enabled_stages: [], email_on_delivery: null } as never);
    vi.spyOn(SupabaseUserClient.prototype, 'listDeliveryEmails').mockResolvedValue([]);
    const exported = await (await exportAccount(signedIn('GET'), none)).json();
    expect(exported.twinNumbers).toEqual(twins);
    expect(read).toHaveBeenCalledExactlyOnceWith(userId);
    expect(Object.keys(exported.twinNumbers[0]).sort()).toEqual([...contract.components.schemas.TwinNumbers.required].sort());
  });

  it('switches the notification of each parcel an email adds, once the account has an address', async () => {
    const set = vi.spyOn(SupabaseServiceClient.prototype, 'setInboundMailboxNotify').mockImplementation(async (_, notify) => ({ ...mailbox, notify }));
    const answer = await (await notifyInbox(signedIn('PATCH', { notify: false }), none)).json();
    expect(set).toHaveBeenCalledExactlyOnceWith(userId, false);
    expect(answer).toMatchObject({ inbox: { address: recipient, notify: false }, dailyLimit: 30 });
    expect(answer.inbox).not.toHaveProperty('token');
    expect((await notifyInbox(signedIn('PATCH', { notify: 'no' }), none)).status).toBe(400);
    expect((await notifyInbox(signedIn('PATCH', {}), none)).status).toBe(400);
    expect(set).toHaveBeenCalledOnce();
    set.mockResolvedValue(null);
    expect((await notifyInbox(signedIn('PATCH', { notify: true }), none)).status).toBe(404);
    vi.stubEnv('INBOUND_EMAIL_ACCOUNTS', 'someone@example.com');
    expect((await notifyInbox(signedIn('PATCH', { notify: true }), none)).status).toBe(404);
    expect(set).toHaveBeenCalledTimes(2);
  });

  it('reads a mailbox from before the choice as notified', async () => {
    vi.spyOn(SupabaseServiceClient.prototype, 'request').mockResolvedValue({ token, created_at: mailbox.createdAt, used_today: 0, recent: [] });
    expect((await (await readInbox(signedIn('GET'), none)).json()).inbox.notify).toBe(true);
  });

  it('is turned off, even by an account the setting is no longer open to', async () => {
    const close = vi.spyOn(SupabaseServiceClient.prototype, 'closeInboundMailbox').mockResolvedValue(undefined);
    expect((await closeInbox(signedIn('DELETE'), none)).status).toBe(204);
    vi.stubEnv('INBOUND_EMAIL_ACCOUNTS', 'someone@example.com');
    expect((await closeInbox(signedIn('DELETE'), none)).status).toBe(204);
    expect(close).toHaveBeenCalledTimes(2);
    expect(close).toHaveBeenCalledWith(userId);
  });
});

// The shapes of the emails Peek has been forwarded so far (who sends them, where the shop and the number stand), written
// afresh: made-up shops, wording and numbers in valid shapes.
describe('emails in the shapes readers forward', () => {
  /** An 18-digit Swiss Post parcel number. */
  const POST = '990012345612345678';
  const OTHER_POST = '990012345698765432';
  /** A Colissimo S10 number with its check digit (a plain run of digits reads as made up, and is refused). */
  const COLISSIMO = 'CB704219382FR';
  /** A DPD parcel number. */
  const DPD = '05212678904731';
  const created = (db: ReturnType<typeof account>) => db.create.mock.calls.map(([, number, label, carrier]) => [number, label, carrier]);

  it('reads a post\'s notice: the shop after "parcel from" in its subject, the number on its own line and again in its links', async () => {
    for (const [from, subject, words] of [
      ['"Post CH AG – Sendungsinfo" <notifications@post.ch>', 'Unterwegs: Paket von Beispiel Versand AG', ['Ihre Sendung ist unterwegs.', 'Paket von', 'Beispiel Versand AG', 'Sendungsnummer']],
      ['"Poste CH SA – Statut de l\'envoi" <notifications@post.ch>', 'En route : colis de Beispiel Versand AG', ['Votre envoi arrive bientôt.', 'Colis de', 'Beispiel Versand AG', 'Numéro d’envoi']],
    ] as const) {
      const db = account();
      await posted(mail(from, subject, [...words, POST, `https://service.post.ch/track?parcel=${POST}`, `https://www.post.ch/manage?id=${POST}&lang=de`].join('\n')));
      expect(created(db)).toEqual([[POST, 'Beispiel Versand', 'swiss-post']]);
      vi.restoreAllMocks();
      vi.spyOn(console, 'log').mockImplementation(() => undefined);
    }
  });

  it('leaves a followed parcel of a post\'s notice as it is, with the reader\'s own name, and says it was known', async () => {
    const db = account({ known: [POST] });
    await posted(mail('"Post CH AG – Sendungsinfo" <notifications@post.ch>', 'Unterwegs: Paket von Beispiel Versand AG', ['Paket von', 'Beispiel Versand AG', 'Sendungsnummer', POST].join('\n')));
    expect(db.create).not.toHaveBeenCalled();
    expect(db.name).toHaveBeenCalledExactlyOnceWith(userId, `known-${POST}`, 'Beispiel Versand');
    expect(db.record).toHaveBeenCalledWith(userId, 'known', 0, null, 'Beispiel Versand');
  });

  it('adds nothing from a post\'s notice that names the delivery as done, and says the parcel was delivered', async () => {
    const db = account();
    await posted(mail('"Poste CH SA – Statut de l\'envoi" <notifications@post.ch>', 'Distribution effectuée: Colis de Beispiel Versand AG', [
      'Nous avons distribué l’envoi suivant.', 'Colis de', 'Beispiel Versand AG', 'Numéro d’envoi', POST, `https://service.post.ch/track?parcel=${POST}`,
    ].join('\n')));
    expect(db.create).not.toHaveBeenCalled();
    expect(db.record).toHaveBeenCalledWith(userId, 'delivered', 0, null, 'Beispiel Versand');
  });

  it('adds the post\'s number a shop names in brackets, and leaves the order number and the date alone', async () => {
    const db = account();
    await posted(mail('"Beispiel Möbel" <shop@beispiel-moebel.ch>', 'Ihre Bestellung ist versandt', [
      'Bestellung 4005123 vom 18.11.2026', `Versand (Post): ${POST}`, 'Lieferung in 1 bis 2 Werktagen.',
    ].join('\n')));
    expect(created(db)).toEqual([[POST, 'Beispiel Möbel', 'swiss-post']]);
  });

  it('adds each number of an email that gives several, once, though its links give them again', async () => {
    const db = account();
    await posted(mail('"Beispiel Möbel" <shop@beispiel-moebel.ch>', 'Ihre Bestellung kommt in zwei Paketen', [
      `Paket 1: ${POST}`, `Paket 2: ${OTHER_POST}`, `https://service.post.ch/track?parcel=${POST}`, `https://service.post.ch/track?parcel=${OTHER_POST}`,
    ].join('\n')));
    expect(created(db)).toEqual([[POST, 'Beispiel Möbel', 'swiss-post'], [OTHER_POST, 'Beispiel Möbel', 'swiss-post']]);
    expect(db.record).toHaveBeenCalledWith(userId, 'added', 2, null, 'Beispiel Möbel');
  });

  it('adds nothing from a marketplace\'s dispatch that gives only its order, its own shipment id and an arrival day', async () => {
    const db = account();
    await posted(mail('Example Market <dispatch@example-market.com>', 'Your order has shipped', [
      'Order 302-1234567-1234567 has shipped and arrives Friday.',
      'Track it: https://www.example-market.com/progress?orderId=302-1234567-1234567&shipmentId=Dx7kQ2mZp',
    ].join('\n')));
    expect(db.create).not.toHaveBeenCalled();
    expect(db.record).toHaveBeenCalledWith(userId, 'none', 0, null, 'Example Market');
  });

  it('reads a shop\'s note on a pre-order\'s price that fell for no number, though its encoded help links hold page ids after "%3D"', async () => {
    const db = account();
    const help = 'https://www.example-market.com/r?C=2AB3C&U=https%3A%2F%2Fwww.example-market.com%2Fhelp%3Fie%3DUTF8%26nodeId%3D201234567%26qid%3D1763600000';
    await posted(mail('Example Market <store-news@example-market.com>', 'Good news: you saved on your pre-order', [
      'The price of an item you pre-ordered went down, and we charged you the lower price.',
      'You can follow this order and all your orders in your account. More on the price guarantee in our help pages.',
      help,
    ].join('\n')));
    // "Pre-order" holds the filter's word for "order": the note is read, and adds nothing.
    expect(db.create).not.toHaveBeenCalled();
    expect(db.record).toHaveBeenCalledWith(userId, 'none', 0, null, 'Example Market');
  });

  it('ignores an email whose subject is about no parcel, unread and unkept, though it holds a tracking number', async () => {
    const db = account();
    for (const subject of ['Our autumn collection is here', 'Votre facture de novembre', 'Willkommen in der Community']) {
      await posted(mail('"Example Shop™" <news@example-shop.com>', subject, `Tracking: ${UPS}`));
    }
    expect(db.create).not.toHaveBeenCalled();
    expect(db.keep).not.toHaveBeenCalled();
    expect(db.record.mock.calls).toEqual(Array.from({ length: 3 }, () => [userId, 'ignored', 0, null, 'Example Shop']));
    // They count only as received: the allowance of emails read stays for the shipping ones.
    expect(db.claimReceived).toHaveBeenCalledTimes(3);
    expect(db.claimMail).not.toHaveBeenCalled();
  });

  it('reads a shipping subject in any language, a carrier\'s email whatever its subject, and a delivered one', async () => {
    const db = account();
    await posted(mail('Example Shop <orders@example-shop.com>', 'Fwd: Twoja przesyłka jest w drodze', `Numer: ${UPS}`));
    await posted(mail('DHL <noreply@dhl.com>', 'Information', 'Sendungsnummer 4612345678.'));
    await posted(mail('Example Shop <orders@example-shop.com>', 'Delivered: your order', `Tracking: ${DHL}`));
    expect(db.create.mock.calls.map(([, number]) => number)).toEqual([UPS, '4612345678']);
    expect(db.record.mock.calls.map(([, result]) => result)).toEqual(['added', 'added', 'delivered']);
    expect(db.keep).toHaveBeenCalledTimes(3);
  });

  it('adds a Colissimo number after its label, named after the shop without its trademark sign or capitals', async () => {
    const db = account();
    await posted(mail('"EXEMPLE BOUTIQUE®" <contact@exemple-boutique.fr>', 'Votre commande est en route', [
      'Transporteur : Colissimo', `Numéro de suivi : ${COLISSIMO}`, `Suivre : https://www.example-carrier.com/track?n=${COLISSIMO}`,
    ].join('\n')));
    expect(created(db)).toEqual([[COLISSIMO, 'Exemple Boutique', 'la-poste']]);
  });

  it('takes a DPD notice\'s number as DPD\'s, named after the shop in its subject, and its delivered notice as about a followed parcel', async () => {
    let db = account();
    await posted(mail('"DPD Schweiz" <noreply@dpd.ch>', 'Ihr Paket von Beispiel Versand kommt heute', `Paketnummer ${DPD} wird heute zwischen 10 und 13 Uhr zugestellt.`));
    expect(created(db)).toEqual([[DPD, 'Beispiel Versand', 'dpd']]);
    vi.restoreAllMocks();
    vi.spyOn(console, 'log').mockImplementation(() => undefined);
    db = account({ known: [DPD] });
    await posted(mail('"DPD Schweiz" <noreply@dpd.ch>', 'Ihr Paket von Beispiel Versand wurde zugestellt', `Paketnummer ${DPD} wurde zugestellt.`));
    expect(db.create).not.toHaveBeenCalled();
    expect(db.record).toHaveBeenCalledWith(userId, 'known', 0, null, 'Beispiel Versand');
  });
});

function mail(from: string, subject: string, body: string): Uint8Array<ArrayBuffer> {
  return new TextEncoder().encode(`From: ${from}\r\nTo: ${recipient}\r\nSubject: ${subject}\r\nContent-Type: text/plain; charset=utf-8\r\n\r\n${body}`);
}

async function posted(raw: Uint8Array<ArrayBuffer>, to = recipient, secret = SECRET) {
  const timestamp = Math.floor(Date.now() / 1_000);
  return receive(new NextRequest('https://peek.example/api/inbound/email', {
    method: 'POST',
    headers: { 'content-type': 'message/rfc822', 'x-peek-recipient': to, 'x-peek-signature': `t=${timestamp},v1=${await sign(secret, timestamp, to, raw)}` },
    body: raw,
  }), none);
}

/** The account's parcels as the database would keep them, with its allowance. */
function account(options: { known?: string[]; allowance?: number; country?: string } = {}) {
  const service = SupabaseServiceClient.prototype;
  let allowance = options.allowance ?? 15;
  const known = new Map((options.known ?? []).map((number) => [number, `known-${number}`]));
  return {
    owner: vi.spyOn(service, 'inboundMailboxOwner').mockResolvedValue(userId),
    claimReceived: vi.spyOn(service, 'claimReceivedInboundMail').mockResolvedValue(true),
    claimMail: vi.spyOn(service, 'claimInboundMail').mockResolvedValue({ allowed: true, scope: null }),
    record: vi.spyOn(service, 'recordInboundMail').mockResolvedValue(undefined),
    // The country the account's latest parcel was looked up from.
    country: vi.spyOn(service, 'latestLookupCountry').mockResolvedValue(options.country ?? null),
    known: vi.spyOn(service, 'accountTrackingNumbers').mockImplementation(async (_, numbers) => new Map(numbers.filter((n) => known.has(n)).map((n) => [n, known.get(n)!]))),
    claim: vi.spyOn(service, 'claimAccountTracking').mockImplementation(async () => {
      allowance -= 1;
      return { allowed: allowance >= 0, scope: allowance >= 0 ? null : 'account', overallUsed: 1 };
    }),
    create: vi.spyOn(service, 'createAccountPackage').mockImplementation(async (_, number) => `new-${number}`),
    reload: vi.spyOn(service, 'getPackage').mockImplementation(async (id) => ({ id, carrier_data: {}, dpd_postcode: '' })),
    update: vi.spyOn(service, 'updatePackage').mockResolvedValue(undefined),
    settle: vi.spyOn(service, 'ignoreTypedTrackingSupportCases').mockResolvedValue(0),
    queue: vi.spyOn(service, 'enqueueSyncJob').mockImplementation(async ({ packageId }) => ({ row: { id: `job-${packageId}` }, queued: true })),
    keep: vi.spyOn(service, 'keepInboundMail').mockResolvedValue(true),
    // A followed parcel has a name unless a test says otherwise.
    name: vi.spyOn(service, 'nameUnnamedPackage').mockResolvedValue(false),
    announce: vi.spyOn(service, 'parcelsAddedByEmail').mockResolvedValue(0),
    twin: vi.spyOn(service, 'recordTwinNumbers').mockResolvedValue(true),
  };
}

describe('an email to a forwarding address', () => {
  it('adds the numbers it names, labelled with the shop, and records what it did', async () => {
    const db = account();
    const response = await posted(mail('"Example Shop" <orders@example-shop.com>', 'Shipped', `Your parcels ${UPS} and ${DHL} are on their way.`));
    expect(response.status).toBe(202);
    expect(db.owner).toHaveBeenCalledWith(token);
    expect(db.claimReceived).toHaveBeenCalledWith(userId, 300);
    expect(db.claimMail).toHaveBeenCalledWith(userId, { perAccount: 30, overall: 200 });
    expect(db.create.mock.calls.map(([, number, label, carrier]) => [number, label, carrier])).toEqual([[UPS, 'Example Shop', 'ups'], [DHL, 'Example Shop', 'dhl']]);
    expect(db.queue).toHaveBeenCalledWith({ userId, packageId: `new-${UPS}`, reason: 'added' });
    expect(db.record).toHaveBeenCalledWith(userId, 'added', 2, null, 'Example Shop');
    // Each is new to recognition, as one added on the Add sheet is, before its first check is queued; an email gives
    // no country of the reader's, and this account's parcels have none.
    expect(db.update.mock.calls).toEqual([UPS, DHL].map((number) => [`new-${number}`, { carrier_data: { add_recognition_pending: true } }]));
    expect(db.update.mock.invocationCallOrder[0]).toBeLessThan(db.queue.mock.invocationCallOrder[0]!);
  });

  it('adds the numbers as from the country the account\'s latest parcel was looked up from, read once', async () => {
    const db = account({ country: 'CH' });
    const response = await posted(mail('"Example Shop" <orders@example-shop.com>', 'Shipped', `Your parcels ${UPS} and ${DHL} are on their way.`));
    expect(response.status).toBe(202);
    expect(db.country).toHaveBeenCalledExactlyOnceWith(userId);
    expect(db.update.mock.calls).toEqual([UPS, DHL].map((number) => [`new-${number}`, { carrier_data: { lookup_country_hint: 'CH', add_recognition_pending: true } }]));
  });

  it('still queues the first check of a parcel it could not mark new to recognition', async () => {
    const db = account();
    db.reload.mockRejectedValueOnce(new SupabaseError('The database is unavailable', 503));
    const added = await parcels.addNumbersToAccount(SupabaseServiceClient.prototype, userId, trackingNumbersInText(UPS), 'Example Shop');
    expect(added).toEqual([{ trackingNumber: UPS, carrier: 'ups', outcome: 'added', packageId: `new-${UPS}`, jobId: `job-new-${UPS}` }]);
    expect(db.update).not.toHaveBeenCalled();
  });

  it('retries an unavailable eligibility lookup within the same mailbox addition', async () => {
    const db = account();
    const verify = vi.spyOn(eligibility, 'verifyAmazonShippingAddition')
      .mockRejectedValueOnce(new HttpError(503, 'Try again')).mockResolvedValueOnce(undefined);
    const number = 'FR9999999980';
    const match = { ...trackingNumbersInText(UPS)[0]!.match, carrier: 'amazon-shipping' as const, trackingNumber: number };
    const added = await parcels.addNumbersToAccount(SupabaseServiceClient.prototype, userId, [{ normalized: number, match }],
      'Example Shop', { retryEligibility: true });
    expect(added[0]?.outcome).toBe('added');
    expect(verify).toHaveBeenCalledTimes(2);
    expect(db.create).toHaveBeenCalledOnce();
  });

  it('returns a retryable response when eligibility is temporarily unavailable', async () => {
    const db = account();
    const number = 'FR9999999980';
    vi.spyOn(parcels, 'addParcelsFromText').mockResolvedValueOnce([
      { trackingNumber: number, carrier: 'amazon-shipping', outcome: 'unavailable' },
    ]);
    const response = await posted(mail('Example Shop <shop@example.test>', 'Shipped', 'Tracking number: ' + number));
    expect(response.status).toBe(503);
    expect(db.record).not.toHaveBeenCalled();
  });

  // Synthetic numbers: the one the Add sheet's own Amazon Shipping tests use.
  it('adds a number Amazon Shipping tracks publicly, as the Add sheet does, and claims the allowance only after its check', async () => {
    const db = account();
    const check = vi.spyOn(eligibility, 'verifyAmazonShippingAddition').mockResolvedValue(undefined);
    const response = await posted(mail('"Example Shop" <orders@example-shop.com>', 'Shipped', 'Your order ships with Amazon Shipping.\nTracking ID: FR9999999980'));
    expect(response.status).toBe(202);
    expect(check).toHaveBeenCalledWith('amazon-shipping', 'FR9999999980');
    expect(db.create.mock.calls.map(([, number, label, carrier]) => [number, label, carrier])).toEqual([['FR9999999980', 'Example Shop', 'amazon-shipping']]);
    expect(check.mock.invocationCallOrder[0]).toBeLessThan(db.claim.mock.invocationCallOrder[0]!);
  });

  it('refuses an Amazon number only the buyer\'s Amazon account tracks, at no allowance, and answers 503 while the check is down', async () => {
    let db = account();
    vi.spyOn(eligibility, 'verifyAmazonShippingAddition').mockRejectedValue(new HttpError(400, AMAZON_ACCOUNT_MESSAGE));
    expect((await posted(mail('"Example Shop" <orders@example-shop.com>', 'Shipped', 'Tracking ID: FR9999999980'))).status).toBe(202);
    expect(db.create).not.toHaveBeenCalled();
    expect(db.claim).not.toHaveBeenCalled();
    vi.restoreAllMocks();

    db = account();
    const check = vi.spyOn(eligibility, 'verifyAmazonShippingAddition').mockRejectedValue(new HttpError(503, 'Try again'));
    expect((await posted(mail('"Example Shop" <orders@example-shop.com>', 'Shipped', 'Tracking ID: FR9999999980'))).status).toBe(503);
    expect(check).toHaveBeenCalledTimes(2);
    expect(db.claim).not.toHaveBeenCalled();
    expect(db.record).not.toHaveBeenCalled();
  });

  it('names the parcels after the shop as it signs, without its trademark signs, capitals or legal form', async () => {
    const db = account();
    await posted(mail('"EXAMPLE® SHOP AG" <orders@example-shop.com>', 'Shipped', `Your parcel ${UPS} is on its way.`));
    expect(db.create.mock.calls.map(([, number, label]) => [number, label])).toEqual([[UPS, 'Example Shop']]);
    expect(db.record).toHaveBeenCalledWith(userId, 'added', 1, null, 'Example Shop');
  });

  it('marks the parcels it added unseen and wakes their notifications, and announces none it did not add', async () => {
    const wake = vi.spyOn(background, 'wakeEmailAddedWorker').mockImplementation(() => undefined);
    let db = account({ known: [UPS] });
    db.announce.mockResolvedValue(2);
    await posted(mail('"Example Shop" <orders@example-shop.com>', 'Shipped', `Your parcels ${UPS} and ${DHL} are on their way.`));
    expect(db.announce).toHaveBeenCalledExactlyOnceWith(userId, [`new-${DHL}`]);
    expect(wake).toHaveBeenCalledOnce();
    // Only the parcel it added is new to recognition.
    expect(db.update.mock.calls.map(([id]) => id)).toEqual([`new-${DHL}`]);
    // Switched off, or without a device: nothing is queued, and nothing woken.
    db.announce.mockResolvedValue(0);
    await posted(mail('"Example Shop" <orders@example-shop.com>', 'Shipped', `Tracking: ${DHL}`));
    expect(db.announce).toHaveBeenCalledTimes(2);
    expect(wake).toHaveBeenCalledOnce();
    // Followed already, delivered or stopped by a limit: none is announced.
    vi.restoreAllMocks();
    vi.spyOn(console, 'log').mockImplementation(() => undefined);
    db = account({ known: [UPS], allowance: 0 });
    await posted(mail('Shop <a@example.com>', 'Shipped', `Tracking: ${UPS}`));
    await posted(mail('Shop <a@example.com>', 'Your parcel has been delivered', `Tracking: ${DHL}`));
    await posted(mail('Shop <a@example.com>', 'Shipped', `Tracking: ${DHL}`));
    expect(db.record.mock.calls.map(([, result]) => result)).toEqual(['known', 'delivered', 'limited']);
    expect(db.announce).not.toHaveBeenCalled();
  });

  it('keeps the parcels it added when announcing them fails, and reports it', async () => {
    const db = account();
    const failure = new SupabaseError('database down', 503);
    db.announce.mockRejectedValue(failure);
    const report = vi.spyOn(monitoring, 'captureOperationalError').mockReturnValue(null);
    expect((await posted(mail('"Example Shop" <orders@example-shop.com>', 'Shipped', `Tracking: ${UPS}`))).status).toBe(202);
    expect(report).toHaveBeenCalledExactlyOnceWith(failure, { component: 'inbound-email', operation: 'announce' });
    expect(db.record).toHaveBeenCalledWith(userId, 'added', 1, null, 'Example Shop');
    expect(db.keep).toHaveBeenCalledOnce();
  });

  it('names a carrier\'s parcel after the shop its email carries for, and gives the number its carrier', async () => {
    const db = account();
    await posted(mail('DHL <noreply@dhl.com>', 'Your Example Shop shipment is on its way', 'Sendungsnummer 4612345678 ist unterwegs.'));
    expect(db.create.mock.calls.map(([, number, label, carrier]) => [number, label, carrier])).toEqual([['4612345678', 'Example Shop', 'dhl']]);
    expect(db.record).toHaveBeenCalledWith(userId, 'added', 1, null, 'Example Shop');
  });

  it('leaves a carrier\'s parcel unnamed when its email names no shop, and notes the carrier as the sender', async () => {
    const db = account();
    await posted(mail('DHL Info <noreply@dhl.com>', 'Unterwegs', 'Sendungsnummer 4612345678 ist unterwegs.'));
    expect(db.create.mock.calls.map(([, number, label, carrier]) => [number, label, carrier])).toEqual([['4612345678', '', 'dhl']]);
    expect(db.record).toHaveBeenCalledWith(userId, 'added', 1, null, 'DHL Info');
  });

  it('follows a parcel the reader sent, as a seller\'s notice tells, named so in the account\'s language', async () => {
    const db = account({ known: [DHL] });
    const auth = vi.spyOn(SupabaseServiceClient.prototype, 'getAuthAccount')
      .mockResolvedValue({ email: 'alex@example.com', emailConfirmed: true, locale: 'de-CH' });
    db.name.mockResolvedValue(true);
    await posted(mail('"Example Market" <noreply@example-market.com>', 'Your item is on its way',
      `Your parcel ${UPS} is on its way to the buyer.\nYour other sale: ${DHL}`));
    expect(auth).toHaveBeenCalledWith(userId);
    expect(db.create.mock.calls.map(([, number, label]) => [number, label])).toEqual([[UPS, '(von dir verschickt) Example Market']]);
    expect(db.name).toHaveBeenCalledExactlyOnceWith(userId, `known-${DHL}`, '(von dir verschickt) Example Market');
    // The mailbox's log names who wrote the email.
    expect(db.record).toHaveBeenCalledWith(userId, 'added', 1, null, 'Example Market');
  });

  it('names a parcel the reader dropped off at a carrier in English when the account\'s language can\'t be read', async () => {
    const db = account();
    vi.spyOn(SupabaseServiceClient.prototype, 'getAuthAccount').mockRejectedValue(new SupabaseError('down', 503));
    await posted(mail('DHL Info <noreply@dhl.com>', 'Paket abgegeben', 'Danke für die Abgabe. Sendungsnummer 4612345678 geht an den Käufer.'));
    expect(db.create.mock.calls.map(([, number, label, carrier]) => [number, label, carrier])).toEqual([['4612345678', '(you sent)', 'dhl']]);
  });

  it('reads no buyer\'s email as one the reader sent, and asks nothing of the account for it', async () => {
    const db = account();
    const auth = vi.spyOn(SupabaseServiceClient.prototype, 'getAuthAccount');
    await posted(mail('"Example Market" <noreply@example-market.com>', 'Shipped', `The seller shipped your order. Buyer Protection applies. Tracking: ${UPS}`));
    expect(db.create.mock.calls.map(([, number, label]) => [number, label])).toEqual([[UPS, 'Example Market']]);
    expect(auth).not.toHaveBeenCalled();
  });

  describe('a marketplace\'s email that names its own number and the last mile\'s for one package', () => {
    const MARKET = 'LP00000000000001';
    const twins = (number = MARKET) => mail('"Example Market" <noreply@example-market.com>', 'Your package is with its last carrier',
      `Your package ${DHL} is on its way.\nTrack it: https://global.cainiao.com/detail.htm?mailNoList=${number}`);

    it('adds the marketplace\'s number alone, which follows the whole journey, and keeps the pair for it', async () => {
      const db = account();
      await posted(twins());
      expect(db.create.mock.calls.map(([, number, label, carrier]) => [number, label, carrier])).toEqual([[MARKET, 'Example Market', 'aliexpress']]);
      expect(db.claim).toHaveBeenCalledOnce();
      expect(db.twin).toHaveBeenCalledExactlyOnceWith(userId, `new-${MARKET}`, MARKET, DHL);
      expect(db.record).toHaveBeenCalledWith(userId, 'added', 1, null, 'Example Market');
    });

    it('adds nothing when the account follows either number, keeps the pair for that parcel, and names it', async () => {
      for (const followed of [DHL, MARKET]) {
        const db = account({ known: [followed] });
        db.name.mockResolvedValue(true);
        await posted(twins());
        expect(db.create).not.toHaveBeenCalled();
        expect(db.claim).not.toHaveBeenCalled();
        expect(db.twin).toHaveBeenCalledExactlyOnceWith(userId, `known-${followed}`, MARKET, DHL);
        expect(db.name).toHaveBeenCalledExactlyOnceWith(userId, `known-${followed}`, 'Example Market');
        expect(db.record).toHaveBeenCalledWith(userId, 'named', 0, null, 'Example Market');
        vi.restoreAllMocks();
      }
    });

    it('keeps the pair of two parcels the account follows under the numbers, for the marketplace\'s, so they merge', async () => {
      const db = account({ known: [DHL, MARKET] });
      await posted(twins());
      expect(db.twin).toHaveBeenCalledExactlyOnceWith(userId, `known-${MARKET}`, MARKET, DHL);
      expect(db.record).toHaveBeenCalledWith(userId, 'known', 0, null, 'Example Market');
    });

    it('keeps no pair when the marketplace\'s number could not be added, and reads the last mile\'s as any other', async () => {
      let db = account();
      db.create.mockRejectedValueOnce(new SupabaseError('full', 400, 'P0001'));
      await posted(twins());
      expect(db.create.mock.calls.map(([, number]) => number)).toEqual([MARKET]);
      expect(db.twin).not.toHaveBeenCalled();
      expect(db.record).toHaveBeenCalledWith(userId, 'full', 0, null, 'Example Market');
      vi.restoreAllMocks();
      // Added meanwhile from elsewhere: which parcel it is can't be told.
      db = account();
      db.create.mockRejectedValueOnce(new SupabaseError('exists', 409));
      await posted(twins());
      expect(db.create.mock.calls.map(([, number]) => number)).toEqual([MARKET, DHL]);
      expect(db.twin).not.toHaveBeenCalled();
    });

    it('pairs nothing in an email that names more numbers, two of the marketplace\'s, or none of them', async () => {
      let db = account();
      await posted(mail('"Example Market" <noreply@example-market.com>', 'Your packages are on their way',
        `Package ${DHL}: https://global.cainiao.com/detail.htm?mailNoList=${MARKET}\nPackage 2: https://global.cainiao.com/detail.htm?mailNoList=LP00000000000002`));
      expect(db.create.mock.calls.map(([, number]) => number).sort()).toEqual([DHL, MARKET, 'LP00000000000002'].sort());
      expect(db.twin).not.toHaveBeenCalled();
      vi.restoreAllMocks();
      db = account();
      await posted(mail('"Example Shop" <orders@example-shop.com>', 'Shipped', `Your parcels ${UPS} and ${DHL} are on their way.`));
      expect(db.create).toHaveBeenCalledTimes(2);
      expect(db.twin).not.toHaveBeenCalled();
    });

    it('keeps the parcel it added when keeping the pair fails, and reports it', async () => {
      const db = account();
      db.twin.mockRejectedValue(new SupabaseError('down', 503));
      const captured = vi.spyOn(monitoring, 'captureOperationalError').mockReturnValue(null);
      const response = await posted(twins());
      expect(response.status).toBe(202);
      expect(db.create).toHaveBeenCalledOnce();
      expect(captured).toHaveBeenCalledWith(expect.any(SupabaseError), { component: 'packages', operation: 'record_twin_numbers' });
    });

    it('pairs nothing in a text an assistant sends', async () => {
      const db = account();
      const text = `${DHL} https://global.cainiao.com/detail.htm?mailNoList=${MARKET}`;
      await addParcelsFromText(SupabaseServiceClient.prototype as SupabaseServiceClient, userId, text, '');
      expect(db.create).toHaveBeenCalledTimes(2);
      expect(db.twin).not.toHaveBeenCalled();
    });
  });

  it('adds nothing twice, and a number the account follows costs no allowance', async () => {
    const db = account({ known: [UPS] });
    await posted(mail('Shop <a@example.com>', 'Shipped', `Tracking: ${UPS}`));
    expect(db.claim).not.toHaveBeenCalled();
    expect(db.create).not.toHaveBeenCalled();
    expect(db.name).toHaveBeenCalledExactlyOnceWith(userId, `known-${UPS}`, 'Shop');
    expect(db.record).toHaveBeenCalledWith(userId, 'known', 0, null, 'Shop');
  });

  it('gives a followed parcel without a name the shop\'s name, and renames none', async () => {
    let db = account({ known: [UPS] });
    db.name.mockResolvedValue(true);
    await posted(mail('"Example Shop" <orders@example-shop.com>', 'Shipped', `Tracking: ${UPS}`));
    expect(db.name).toHaveBeenCalledExactlyOnceWith(userId, `known-${UPS}`, 'Example Shop');
    expect(db.record).toHaveBeenCalledWith(userId, 'named', 0, null, 'Example Shop');
    // A carrier's email that names no shop has no name to give.
    vi.restoreAllMocks();
    vi.spyOn(console, 'log').mockImplementation(() => undefined);
    db = account({ known: ['4612345678'] });
    db.name.mockResolvedValue(true);
    await posted(mail('DHL Info <noreply@dhl.com>', 'Unterwegs', 'Sendungsnummer 4612345678 ist unterwegs.'));
    expect(db.name).not.toHaveBeenCalled();
    expect(db.record).toHaveBeenCalledWith(userId, 'known', 0, null, 'DHL Info');
  });

  it('leaves a followed parcel unnamed when naming it fails, and reports it', async () => {
    const db = account({ known: [UPS] });
    const failure = new SupabaseError('database down', 503);
    db.name.mockRejectedValue(failure);
    const report = vi.spyOn(monitoring, 'captureOperationalError').mockReturnValue(null);
    expect((await posted(mail('"Example Shop" <orders@example-shop.com>', 'Shipped', `Tracking: ${UPS}`))).status).toBe(202);
    expect(report).toHaveBeenCalledExactlyOnceWith(failure, { component: 'inbound-email', operation: 'name_parcel' });
    expect(db.record).toHaveBeenCalledWith(userId, 'known', 0, null, 'Example Shop');
  });

  it('adds no parcel from an email that says it was delivered, and names a followed one', async () => {
    let db = account();
    await posted(mail('"Example Shop" <orders@example-shop.com>', 'Your parcel has been delivered', `Tracking: ${DHL}`));
    expect(db.claim).not.toHaveBeenCalled();
    expect(db.create).not.toHaveBeenCalled();
    expect(db.record).toHaveBeenCalledWith(userId, 'delivered', 0, null, 'Example Shop');
    vi.restoreAllMocks();
    vi.spyOn(console, 'log').mockImplementation(() => undefined);
    db = account({ known: [UPS] });
    db.name.mockResolvedValue(true);
    await posted(mail('"Example Shop" <orders@example-shop.com>', 'Delivered: your order', `Parcels ${UPS} and ${DHL}`));
    expect(db.create).not.toHaveBeenCalled();
    expect(db.name).toHaveBeenCalledExactlyOnceWith(userId, `known-${UPS}`, 'Example Shop');
    expect(db.record).toHaveBeenCalledWith(userId, 'named', 0, null, 'Example Shop');
    // A delivery still to come adds its parcel.
    await posted(mail('"Example Shop" <orders@example-shop.com>', 'Your parcel will be delivered today', `Tracking: ${DHL}`));
    expect(db.create.mock.calls.map(([, number]) => number)).toEqual([DHL]);
    expect(db.record).toHaveBeenLastCalledWith(userId, 'added', 1, null, 'Example Shop');
  });

  it('keeps the email it read, as it came, and none it ignored, an allowance stopped, Gmail\'s request or an unknown address', async () => {
    let db = account({ known: [DHL] });
    const raw = mail('"Example Shop" <orders@example-shop.com>', 'Shipped', `Tracking: ${UPS}`);
    await posted(raw);
    await posted(mail('"Example Shop" <orders@example-shop.com>', 'Delivered', `Tracking: ${DHL}`));
    await posted(mail('"Example Shop" <orders@example-shop.com>', 'Your parcel', 'Nothing to track.'));
    await posted(mail('"Example Shop" <orders@example-shop.com>', 'Thanks', 'Nothing to track.'));
    expect(db.keep).toHaveBeenCalledTimes(3);
    expect(db.keep.mock.calls[0]![0]).toBe(userId);
    expect(Buffer.from(db.keep.mock.calls[0]![1]).equals(Buffer.from(raw))).toBe(true);
    expect(db.record.mock.calls.map(([, result]) => result)).toEqual(['added', 'known', 'none', 'ignored']);

    vi.restoreAllMocks();
    vi.spyOn(console, 'log').mockImplementation(() => undefined);
    db = account({ allowance: 0 });
    await posted(mail('Shop <a@example.com>', 'Shipped', UPS));
    db.claimMail.mockResolvedValue({ allowed: false, scope: 'account' });
    await posted(mail('Shop <a@example.com>', 'Shipped', UPS));
    db.claimMail.mockResolvedValue({ allowed: true, scope: null });
    db.claim.mockResolvedValue({ allowed: true, scope: null, overallUsed: 1 });
    db.create.mockRejectedValue(new SupabaseError('full', 400, 'P0001'));
    await posted(mail('Shop <a@example.com>', 'Shipped', UPS));
    await posted(mail('Gmail Team <forwarding-noreply@google.com>', '(#123456789) Gmail Forwarding Confirmation - Receive Mail from alex@example.com',
      'Confirmation code: 123456789\nhttps://mail-settings.google.com/mail/vf-example'));
    db.owner.mockResolvedValue(null);
    await posted(mail('Shop <a@example.com>', 'Shipped', UPS));
    expect(db.record.mock.calls.map(([, result]) => result)).toEqual(['limited', 'limited', 'full', 'confirmation']);
    expect(db.keep).not.toHaveBeenCalled();
  });

  it('adds the parcels and notes the email when keeping it fails, and reports it', async () => {
    const db = account();
    const failure = new SupabaseError('database down', 503);
    db.keep.mockRejectedValue(failure);
    const report = vi.spyOn(monitoring, 'captureOperationalError').mockReturnValue(null);
    const logged = vi.spyOn(monitoring, 'logOperationalEvent');
    expect((await posted(mail('"Example Shop" <orders@example-shop.com>', 'Shipped', `Tracking: ${UPS}`))).status).toBe(202);
    expect(db.create).toHaveBeenCalledOnce();
    expect(db.record).toHaveBeenCalledWith(userId, 'added', 1, null, 'Example Shop');
    expect(report).toHaveBeenCalledExactlyOnceWith(failure, { component: 'inbound-email', operation: 'keep' });
    expect(logged).toHaveBeenCalledWith('inbound_email_keep_failed', { error_type: 'SupabaseError' }, 'error');
    expect(logged).toHaveBeenCalledWith('inbound_email', expect.objectContaining({ outcome: 'added', added: 1 }));
  });

  it('notes what the email did by its most telling outcome', () => {
    const parcel = (outcome: 'added' | 'known' | 'limited' | 'full' | 'unavailable') => ({
      trackingNumber: UPS, carrier: 'ups', outcome, packageId: 'parcel', jobId: null, retryAfter: null,
    }) as ParcelAddition;
    expect(mailResult([parcel('known'), parcel('full'), parcel('added')])).toBe('added');
    expect(mailResult([parcel('known'), parcel('limited'), parcel('full')], { named: 1 })).toBe('full');
    expect(mailResult([parcel('known'), parcel('limited')], { named: 1 })).toBe('limited');
    expect(mailResult([parcel('known')], { named: 1, delivered: true })).toBe('named');
    expect(mailResult([parcel('known')], { delivered: true })).toBe('known');
    expect(mailResult([parcel('unavailable')], { delivered: true })).toBe('delivered');
    expect(mailResult([], { delivered: true })).toBe('delivered');
    expect(mailResult([parcel('unavailable')])).toBe('unavailable');
    expect(mailResult([])).toBe('none');
    expect(mailResult([], { waiting: true })).toBe('waiting');
  });

  it('notes an email whose shop link named no parcel yet as a wait for the shop', async () => {
    const db = account();
    vi.spyOn(parcels, 'addParcelsFromText').mockImplementationOnce(async (_service, _user, _text, _label, _carriers, options) => {
      options?.onWaiting?.([{ id: 'wait', platform: 'parcellab', platform_name: 'parcelLab', shop_name: 'Example Shop', label: 'Example Shop',
        created_at: '2026-11-26T10:00:00.000Z', expires_at: '2026-12-10T10:00:00.000Z', expired_at: null }]);
      return [];
    });
    const response = await posted(mail('Example Shop <shop@example.test>', 'Your order is on its way', 'Follow your package: https://example.delivery-status.com/?orderNo=1&s=x'));
    expect(response.status).toBe(202);
    expect(db.record).toHaveBeenCalledWith(userId, 'waiting', 0, null, 'Example Shop');
  });

  it('drops the server\'s own email about a parcel, which a filter sent back', async () => {
    vi.stubEnv('EMAIL_FROM', 'Peek <Hello@Peek.example>');
    const db = account();
    await posted(mail('Peek <hello@peek.example>', 'Delivered: Example Shop', `Your parcel ${UPS} was delivered.`));
    // A From line that goes on over two lines, with an encoded name and the address in capitals: read from the header too.
    await posted(mail('=?utf-8?q?Peek?=\r\n <HELLO@peek.example>', 'Delivered', `Your parcel ${UPS} was delivered.`));
    expect(db.claimReceived).not.toHaveBeenCalled();
    expect(db.claimMail).not.toHaveBeenCalled();
    expect(db.create).not.toHaveBeenCalled();
    expect(db.record).not.toHaveBeenCalled();
    await posted(mail('Peek fans <hello@peek-fans.example>', 'Shipped', `Your parcel ${UPS} is on its way.`));
    expect(db.record).toHaveBeenCalledWith(userId, 'added', 1, null, expect.any(String));
  });

  it('reads no order number as a tracking number', () => {
    expect(trackingNumbersInText(`Order 111-2345678-9012345 shipped with ${UPS}`).map(({ normalized }) => normalized)).toEqual([UPS]);
    expect(trackingNumbersInText('Your order PO-211-01234567890123456 is confirmed')).toEqual([]);
  });

  it('stops at the account\'s daily allowance, and at its parcel limit', async () => {
    let db = account({ allowance: 1 });
    await posted(mail('Shop <a@example.com>', 'Shipped', `${UPS}\n${DHL}`));
    expect(db.create).toHaveBeenCalledTimes(1);
    expect(db.claim).toHaveBeenCalledTimes(2);
    expect(db.record).toHaveBeenCalledWith(userId, 'added', 1, null, expect.any(String));
    vi.restoreAllMocks();
    vi.spyOn(console, 'log').mockImplementation(() => undefined);
    db = account();
    db.create.mockRejectedValue(new SupabaseError('full', 400, 'P0001'));
    const additions = await addParcelsFromText(SupabaseServiceClient.prototype as SupabaseServiceClient, userId, `${UPS} ${DHL}`, '');
    expect(additions.map(({ outcome }) => outcome)).toEqual(['full', 'full']);
    expect(db.create).toHaveBeenCalledTimes(1);
  });

  it('keeps Gmail\'s confirmation for the owner, and confirms nothing itself', async () => {
    const db = account();
    const fetcher = vi.spyOn(globalThis, 'fetch');
    await posted(mail('Gmail Team <forwarding-noreply@google.com>', '(#123456789) Gmail Forwarding Confirmation - Receive Mail from alex@example.com',
      'Confirmation code: 123456789\nhttps://mail-settings.google.com/mail/vf-example'));
    expect(db.record).toHaveBeenCalledWith(userId, 'confirmation', 0, { code: '123456789', link: 'https://mail-settings.google.com/mail/vf-example' }, 'Gmail Team');
    expect(fetcher).not.toHaveBeenCalled();
    expect(db.create).not.toHaveBeenCalled();
  });

  it('is dropped, with the same answer, when its address names nobody or an allowance is used up', async () => {
    const db = account();
    db.owner.mockResolvedValue(null);
    expect((await posted(mail('a@example.com', 'Shipped', UPS))).status).toBe(202);
    expect((await posted(mail('a@example.com', 'Shipped', UPS), 'other@in.example.com')).status).toBe(202);
    expect(db.claimReceived).not.toHaveBeenCalled();
    expect(db.record).not.toHaveBeenCalled();
    db.owner.mockResolvedValue(userId);
    db.claimMail.mockResolvedValue({ allowed: false, scope: 'account' });
    expect((await posted(mail('a@example.com', 'Shipped', UPS))).status).toBe(202);
    expect(db.record).toHaveBeenLastCalledWith(userId, 'limited', 0);
    // An email ignored for its subject needs no allowance of emails read.
    await posted(mail('a@example.com', 'Our autumn collection', UPS));
    expect(db.record).toHaveBeenLastCalledWith(userId, 'ignored', 0, null, expect.any(String));
    expect(db.create).not.toHaveBeenCalled();
  });

  it('stops at the address\'s allowance of emails received, read or not, before reading one', async () => {
    const db = account();
    db.claimReceived.mockResolvedValue(false);
    const read = vi.spyOn(inboundMail, 'readInboundMail');
    expect((await posted(mail('a@example.com', 'Our autumn collection', UPS))).status).toBe(202);
    expect((await posted(mail('a@example.com', 'Shipped', UPS))).status).toBe(202);
    expect(read).not.toHaveBeenCalled();
    expect(db.claimMail).not.toHaveBeenCalled();
    expect(db.record.mock.calls).toEqual([[userId, 'limited', 0], [userId, 'limited', 0]]);
    expect(db.create).not.toHaveBeenCalled();
    expect(db.keep).not.toHaveBeenCalled();
  });

  it('counts nothing for an account the setting is not open to, by id or by confirmed email', async () => {
    const db = account();
    vi.stubEnv('INBOUND_EMAIL_ACCOUNTS', 'alex@example.com');
    const auth = vi.spyOn(SupabaseServiceClient.prototype, 'getAuthAccount')
      .mockResolvedValue({ email: 'alex@example.com', emailConfirmed: false, locale: null });
    expect((await posted(mail('a@example.com', 'x', UPS))).status).toBe(202);
    expect(db.claimReceived).not.toHaveBeenCalled();
    auth.mockResolvedValue({ email: 'alex@example.com', emailConfirmed: true, locale: null });
    await posted(mail('a@example.com', 'x', UPS));
    expect(db.claimReceived).toHaveBeenCalledOnce();
  });

  it('records an email it cannot read as naming nothing, and takes it', async () => {
    const db = account();
    const parts = Array.from({ length: 600 }, (_, index) => `--m\r\nContent-Type: text/plain\r\n\r\npart ${index}`).join('\r\n');
    const raw = new TextEncoder().encode(`From: a@example.com\r\nSubject: x\r\nContent-Type: multipart/mixed; boundary="m"\r\n\r\n${parts}\r\n--m--`);
    expect((await posted(raw)).status).toBe(202);
    expect(db.record).toHaveBeenCalledWith(userId, 'none', 0);
    expect(vi.mocked(console.log).mock.calls.flat().join('\n')).toContain('unreadable');
  });

  it('is refused without the Worker\'s signature, and when too large', async () => {
    const db = account();
    expect((await posted(mail('a@example.com', 'x', UPS), recipient, `${SECRET}-other`)).status).toBe(401);
    const large = new Uint8Array(1_048_577);
    expect((await posted(large)).status).toBe(413);
    expect(db.owner).not.toHaveBeenCalled();
  });

  it('is not taken when the server takes no email', async () => {
    vi.stubEnv('INBOUND_EMAIL_ADDRESS', '');
    expect((await posted(mail('a@example.com', 'x', UPS))).status).toBe(404);
  });
});
