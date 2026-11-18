import 'server-only';

import { wakeEmailAddedWorker } from '../background';
import { ownSenderAddress } from '../email/config';
import { captureOperationalError, errorType, logOperationalEvent } from '../observability';
import { addParcelsFromText, type ParcelAddition } from '../parcelsFromText';
import type { InboundMailResult, SupabaseServiceClient } from '../supabase';
import { carrierNames, carriersOfAddress } from './carrierSender';
import { deliveredSubject } from './delivered';
import { headerFromAddress, readInboundMail, type InboundMail } from './mail';
import { shopNameOf, shopNamedIn } from './shopName';
import { inboundEmailOpenTo, tokenFromRecipient, type InboundEmailSettings } from './settings';

/** What an email did; `dropped` when its address names nobody who takes email. */
export type InboundOutcome = InboundMailResult | 'dropped';

/**
 * What an email did, said once for the whole email, its most telling outcome first: a parcel it added; then one it
 * could not add for the parcel limit or an allowance, which the owner can act on; then a followed parcel it gave a
 * name, a change the owner sees in the list; then parcels already followed, left as they were; then a delivery it
 * told of, whose parcel nobody follows and it does not add; then nothing.
 */
export function mailResult(additions: readonly ParcelAddition[], { named = 0, delivered = false } = {}): InboundMailResult {
  const outcomes = new Set(additions.map(({ outcome }) => outcome));
  if (outcomes.has('added')) return 'added';
  if (outcomes.has('full')) return 'full';
  if (outcomes.has('limited')) return 'limited';
  if (named > 0) return 'named';
  if (outcomes.has('known')) return 'known';
  if (delivered) return 'delivered';
  return 'none';
}

/**
 * Gives the email's name for its parcels to those the account follows without a name, such as one a carrier's email
 * added before the shop's came: a name the owner or an earlier email gave is never changed. Returns how many it
 * named. A failure leaves the parcel unnamed and is reported.
 */
async function nameFollowedParcels(
  service: SupabaseServiceClient,
  userId: string,
  additions: readonly ParcelAddition[],
  label: string,
): Promise<number> {
  // A parcel's name holds 80 characters at most, as when it is added.
  const name = [...label.trim()].slice(0, 80).join('').trim();
  if (!name) return 0;
  const parcels = new Set(additions.flatMap((addition) => (addition.outcome === 'known' && addition.packageId ? [addition.packageId] : [])));
  let named = 0;
  for (const packageId of parcels) {
    try {
      if (await service.nameUnnamedPackage(userId, packageId, name)) named += 1;
    } catch (error) {
      logOperationalEvent('inbound_email_name_failed', { error_type: errorType(error) }, 'error');
      captureOperationalError(error, { component: 'inbound-email', operation: 'name_parcel' });
    }
  }
  return named;
}

/** Keeps the email 30 days, to learn to read emails better. A failure is reported, and the email's parcels stay. */
/**
 * The parcels the email added start unseen, and are notified to the account's browsers and phones unless it switched
 * that off. A failure leaves them added, seen and unannounced, and is reported.
 */
async function announce(service: SupabaseServiceClient, userId: string, additions: readonly ParcelAddition[]): Promise<void> {
  const added = additions.flatMap((addition) => (addition.outcome === 'added' ? [addition.packageId] : []));
  if (!added.length) return;
  try {
    if (await service.parcelsAddedByEmail(userId, added) > 0) wakeEmailAddedWorker();
  } catch (error) {
    logOperationalEvent('inbound_email_announce_failed', { error_type: errorType(error) }, 'error');
    captureOperationalError(error, { component: 'inbound-email', operation: 'announce' });
  }
}

async function keep(service: SupabaseServiceClient, userId: string, raw: Uint8Array): Promise<void> {
  try {
    await service.keepInboundMail(userId, raw);
  } catch (error) {
    logOperationalEvent('inbound_email_keep_failed', { error_type: errorType(error) }, 'error');
    captureOperationalError(error, { component: 'inbound-email', operation: 'keep' });
  }
}

/**
 * Takes one email the Worker passed on: finds the account its address names and, when that account takes email,
 * counts it against the allowances and adds the numbers it holds, labelled with its sender, or with the shop a
 * carrier's email names. A followed parcel without a name takes that name. An email that says its parcel was
 * delivered adds none. Gmail's request to confirm forwarding is kept for the owner instead, and the server's own
 * emails are dropped. What an email did is noted with its sender's name, and the email itself is kept 30 days,
 * but for one an allowance or the parcel limit stopped and Gmail's request, which holds a code.
 */
export async function receiveInboundMail(
  service: SupabaseServiceClient,
  settings: InboundEmailSettings,
  recipient: string,
  raw: Uint8Array,
): Promise<InboundOutcome> {
  const token = tokenFromRecipient(settings, recipient);
  if (!token) return finish('dropped', 'address');
  const userId = await service.inboundMailboxOwner(token);
  if (!userId) return finish('dropped', 'unknown');
  if (!settings.accounts.everyone && !settings.accounts.ids.has(userId)) {
    const account = await service.getAuthAccount(userId);
    const email = account?.emailConfirmed ? account.email : null;
    if (!account || !inboundEmailOpenTo(settings, { id: userId, email })) return finish('dropped', 'closed');
  }
  // The server's own email about a parcel names one the account follows already: a filter that sends it back adds
  // nothing, and spends no allowance. Its header is read first; the parsed email is checked again below.
  const own = ownSenderAddress();
  if (own && headerFromAddress(raw) === own) return finish('dropped', 'own');
  const claim = await service.claimInboundMail(userId, { perAccount: settings.perAccountPerDay, overall: settings.perHour });
  if (!claim.allowed) {
    await service.recordInboundMail(userId, 'limited', 0);
    return finish('limited', claim.scope ?? 'account');
  }

  let mail: InboundMail;
  try {
    mail = await readInboundMail(raw);
  } catch {
    // An email the parser refuses, such as one of too many parts, names no number: sending it again changes nothing.
    await service.recordInboundMail(userId, 'none', 0);
    return finish('none', 'unreadable');
  }
  if (mail.from && mail.from === own) return finish('dropped', 'own');
  if (mail.confirmation) {
    await service.recordInboundMail(userId, 'confirmation', 0, mail.confirmation, mail.sender);
    return finish('confirmation');
  }
  // A carrier's own email: its numbers are that carrier's, and its parcels take the name of the shop it says it
  // carries for, or none. The carrier's mailbox name ("Info", "noreply") says nothing of the parcel. A shop's own
  // email takes the shop's name, tidied the same way.
  const carriers = carriersOfAddress(mail.origin);
  const label = carriers.length ? shopNamedIn(mail.subject, mail.text, carrierNames(carriers)) : shopNameOf(mail.sender);
  // A delivery confirmation comes too late for following its parcel to help: it names one already followed, if any.
  const delivered = deliveredSubject(mail.subject);
  const additions = await addParcelsFromText(service, userId, mail.text, label, carriers, { addNew: !delivered });
  const named = await nameFollowedParcels(service, userId, additions, label);
  await announce(service, userId, additions);
  const result = mailResult(additions, { named, delivered });
  const added = additions.filter(({ outcome }) => outcome === 'added').length;
  await service.recordInboundMail(userId, result, added, null, label || mail.sender);
  if (result !== 'limited' && result !== 'full') await keep(service, userId, raw);
  return finish(result, carriers.length ? 'carrier' : undefined, additions.length, added, named);
}

function finish(outcome: InboundOutcome, reason?: string, numbers = 0, added = 0, named = 0): InboundOutcome {
  logOperationalEvent('inbound_email', { outcome, ...(reason ? { reason } : {}), numbers, added, named });
  return outcome;
}
