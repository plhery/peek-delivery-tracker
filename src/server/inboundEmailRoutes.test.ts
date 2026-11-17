import { NextRequest } from 'next/server';
import { afterEach, beforeEach, describe, expect, it, vi } from 'vitest';
import { GET as exportAccount } from '../../app/api/account/export/route';
import { DELETE as closeInbox, GET as readInbox, PATCH as notifyInbox, POST as openInbox } from '../../app/api/account/inbox/route';
import { POST as receive } from '../../app/api/inbound/email/route';
import contract from '../../contracts/openapi.json';
import { sign } from '../../ops/cloudflare/inbound-email-worker.js';
import { SupabaseAuthenticator } from './auth';
import * as background from './background';
import { mailResult } from './inboundEmail/receive';
import * as monitoring from './observability';
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

  it('is made once, and renewed only when asked', async () => {
    const read = vi.spyOn(SupabaseServiceClient.prototype, 'inboundMailbox').mockResolvedValue(null);
    const open = vi.spyOn(SupabaseServiceClient.prototype, 'openInboundMailbox').mockImplementation(async (_, fresh) => ({ ...mailbox, token: fresh }));
    const first = await openInbox(signedIn('POST', {}), none);
    expect(first.status).toBe(201);
    expect(open).toHaveBeenCalledWith(userId, expect.stringMatching(/^[a-z2-7]{16}$/));
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
    vi.spyOn(SupabaseServiceClient.prototype, 'inboundMailbox').mockResolvedValue(mailbox);
    vi.spyOn(SupabaseServiceClient.prototype, 'accountLastOpened').mockResolvedValue(null);
    vi.spyOn(SupabaseUserClient.prototype, 'request').mockResolvedValue({ profile: null, ownCard: null, friends: [] });
    vi.spyOn(SupabaseUserClient.prototype, 'listPackages').mockResolvedValue([]);
    vi.spyOn(SupabaseUserClient.prototype, 'getNotificationPreferences').mockResolvedValue({ enabled_stages: [], email_on_delivery: null } as never);
    vi.spyOn(SupabaseUserClient.prototype, 'listDeliveryEmails').mockResolvedValue([]);
    const exported = await (await exportAccount(signedIn('GET'), none)).json();
    expect(exported.addByEmail).toEqual({ address: recipient, createdAt: mailbox.createdAt, usedToday: 2, lastMailAt: null, lastResult: null, lastAdded: null, recent: [earlier], confirmation: null, notify: true });
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
function account(options: { known?: string[]; allowance?: number } = {}) {
  const service = SupabaseServiceClient.prototype;
  let allowance = options.allowance ?? 15;
  const known = new Map((options.known ?? []).map((number) => [number, `known-${number}`]));
  return {
    owner: vi.spyOn(service, 'inboundMailboxOwner').mockResolvedValue(userId),
    claimMail: vi.spyOn(service, 'claimInboundMail').mockResolvedValue({ allowed: true, scope: null }),
    record: vi.spyOn(service, 'recordInboundMail').mockResolvedValue(undefined),
    known: vi.spyOn(service, 'accountTrackingNumbers').mockImplementation(async (_, numbers) => new Map(numbers.filter((n) => known.has(n)).map((n) => [n, known.get(n)!]))),
    claim: vi.spyOn(service, 'claimAccountTracking').mockImplementation(async () => {
      allowance -= 1;
      return { allowed: allowance >= 0, scope: allowance >= 0 ? null : 'account', overallUsed: 1 };
    }),
    create: vi.spyOn(service, 'createAccountPackage').mockImplementation(async (_, number) => `new-${number}`),
    settle: vi.spyOn(service, 'ignoreTypedTrackingSupportCases').mockResolvedValue(0),
    queue: vi.spyOn(service, 'enqueueSyncJob').mockImplementation(async ({ packageId }) => ({ row: { id: `job-${packageId}` }, queued: true })),
    keep: vi.spyOn(service, 'keepInboundMail').mockResolvedValue(true),
    // A followed parcel has a name unless a test says otherwise.
    name: vi.spyOn(service, 'nameUnnamedPackage').mockResolvedValue(false),
    announce: vi.spyOn(service, 'parcelsAddedByEmail').mockResolvedValue(0),
  };
}

describe('an email to a forwarding address', () => {
  it('adds the numbers it names, labelled with the shop, and records what it did', async () => {
    const db = account();
    const response = await posted(mail('"Example Shop" <orders@example-shop.com>', 'Shipped', `Your parcels ${UPS} and ${DHL} are on their way.`));
    expect(response.status).toBe(202);
    expect(db.owner).toHaveBeenCalledWith(token);
    expect(db.claimMail).toHaveBeenCalledWith(userId, { perAccount: 30, overall: 200 });
    expect(db.create.mock.calls.map(([, number, label, carrier]) => [number, label, carrier])).toEqual([[UPS, 'Example Shop', 'ups'], [DHL, 'Example Shop', 'dhl']]);
    expect(db.queue).toHaveBeenCalledWith({ userId, packageId: `new-${UPS}`, reason: 'added' });
    expect(db.record).toHaveBeenCalledWith(userId, 'added', 2, null, 'Example Shop');
  });

  it('marks the parcels it added unseen and wakes their notifications, and announces none it did not add', async () => {
    const wake = vi.spyOn(background, 'wakeEmailAddedWorker').mockImplementation(() => undefined);
    let db = account({ known: [UPS] });
    db.announce.mockResolvedValue(2);
    await posted(mail('"Example Shop" <orders@example-shop.com>', 'Shipped', `Your parcels ${UPS} and ${DHL} are on their way.`));
    expect(db.announce).toHaveBeenCalledExactlyOnceWith(userId, [`new-${DHL}`]);
    expect(wake).toHaveBeenCalledOnce();
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

  it('keeps the email it read, as it came, and none an allowance stopped, Gmail\'s request or an unknown address', async () => {
    let db = account({ known: [DHL] });
    const raw = mail('"Example Shop" <orders@example-shop.com>', 'Shipped', `Tracking: ${UPS}`);
    await posted(raw);
    await posted(mail('"Example Shop" <orders@example-shop.com>', 'Delivered', `Tracking: ${DHL}`));
    await posted(mail('"Example Shop" <orders@example-shop.com>', 'Thanks', 'Nothing to track.'));
    expect(db.keep).toHaveBeenCalledTimes(3);
    expect(db.keep.mock.calls[0]![0]).toBe(userId);
    expect(Buffer.from(db.keep.mock.calls[0]![1]).equals(Buffer.from(raw))).toBe(true);
    expect(db.record.mock.calls.map(([, result]) => result)).toEqual(['added', 'known', 'none']);

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
    expect(mailResult([parcel('unavailable')])).toBe('none');
    expect(mailResult([])).toBe('none');
  });

  it('drops the server\'s own email about a parcel, which a filter sent back', async () => {
    vi.stubEnv('EMAIL_FROM', 'Peek <Hello@Peek.example>');
    const db = account();
    await posted(mail('Peek <hello@peek.example>', 'Delivered: Example Shop', `Your parcel ${UPS} was delivered.`));
    // A From line that goes on over two lines, with an encoded name and the address in capitals: read from the header too.
    await posted(mail('=?utf-8?q?Peek?=\r\n <HELLO@peek.example>', 'Delivered', `Your parcel ${UPS} was delivered.`));
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

  it('is dropped, with the same answer, when its address names nobody or its allowance is used up', async () => {
    const db = account();
    db.owner.mockResolvedValue(null);
    expect((await posted(mail('a@example.com', 'x', UPS))).status).toBe(202);
    expect((await posted(mail('a@example.com', 'x', UPS), 'other@in.example.com')).status).toBe(202);
    expect(db.claimMail).not.toHaveBeenCalled();
    expect(db.record).not.toHaveBeenCalled();
    db.owner.mockResolvedValue(userId);
    db.claimMail.mockResolvedValue({ allowed: false, scope: 'account' });
    expect((await posted(mail('a@example.com', 'x', UPS))).status).toBe(202);
    expect(db.record).toHaveBeenCalledWith(userId, 'limited', 0);
    expect(db.create).not.toHaveBeenCalled();
  });

  it('counts nothing for an account the setting is not open to, by id or by confirmed email', async () => {
    const db = account();
    vi.stubEnv('INBOUND_EMAIL_ACCOUNTS', 'alex@example.com');
    const auth = vi.spyOn(SupabaseServiceClient.prototype, 'getAuthAccount')
      .mockResolvedValue({ email: 'alex@example.com', emailConfirmed: false, locale: null });
    expect((await posted(mail('a@example.com', 'x', UPS))).status).toBe(202);
    expect(db.claimMail).not.toHaveBeenCalled();
    auth.mockResolvedValue({ email: 'alex@example.com', emailConfirmed: true, locale: null });
    await posted(mail('a@example.com', 'x', UPS));
    expect(db.claimMail).toHaveBeenCalledOnce();
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
