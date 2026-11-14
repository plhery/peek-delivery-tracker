import 'server-only';

import { ownSenderAddress } from '../email/config';
import { logOperationalEvent } from '../observability';
import { addParcelsFromText, type ParcelAddition } from '../parcelsFromText';
import type { InboundMailResult, SupabaseServiceClient } from '../supabase';
import { carrierNames, carriersOfAddress } from './carrierSender';
import { headerFromAddress, readInboundMail, type InboundMail } from './mail';
import { shopNamedIn } from './shopName';
import { inboundEmailOpenTo, tokenFromRecipient, type InboundEmailSettings } from './settings';

/** What an email did; `dropped` when its address names nobody who takes email. */
export type InboundOutcome = InboundMailResult | 'dropped';

/** What an email's numbers did, said once for the whole email, its most telling outcome first. */
export function mailResult(additions: readonly ParcelAddition[]): InboundMailResult {
  const outcomes = new Set(additions.map(({ outcome }) => outcome));
  if (outcomes.has('added')) return 'added';
  if (outcomes.has('full')) return 'full';
  if (outcomes.has('limited')) return 'limited';
  if (outcomes.has('known')) return 'known';
  return 'none';
}

/**
 * Takes one email the Worker passed on: finds the account its address names and, when that account takes email,
 * counts it against the allowances and adds the numbers it holds, labelled with its sender, or with the shop a
 * carrier's email names. Gmail's request to confirm forwarding is kept for the owner instead, and the server's own
 * emails are dropped. The email itself is never stored: what it did is noted with its sender's name.
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
  // carries for, or none. The carrier's mailbox name ("Info", "noreply") says nothing of the parcel.
  const carriers = carriersOfAddress(mail.origin);
  const label = carriers.length ? shopNamedIn(mail.subject, mail.text, carrierNames(carriers)) : mail.sender;
  const additions = await addParcelsFromText(service, userId, mail.text, label, carriers);
  const result = mailResult(additions);
  const added = additions.filter(({ outcome }) => outcome === 'added').length;
  await service.recordInboundMail(userId, result, added, null, label || mail.sender);
  return finish(result, carriers.length ? 'carrier' : undefined, additions.length, added);
}

function finish(outcome: InboundOutcome, reason?: string, numbers = 0, added = 0): InboundOutcome {
  logOperationalEvent('inbound_email', { outcome, ...(reason ? { reason } : {}), numbers, added });
  return outcome;
}
