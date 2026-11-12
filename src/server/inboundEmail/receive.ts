import 'server-only';

import { ownSenderAddress } from '../email/config';
import { logOperationalEvent } from '../observability';
import { addParcelsFromText, type ParcelAddition } from '../parcelsFromText';
import type { InboundMailResult, SupabaseServiceClient } from '../supabase';
import { readInboundMail, type InboundMail } from './mail';
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
 * counts it against the allowances and adds the numbers it holds, labelled with its sender. Gmail's request to
 * confirm forwarding is kept for the owner instead, and the server's own emails are dropped. The email itself is never
 * stored.
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
  // The server's own email about a parcel names one the account follows already: a filter that sends it back adds nothing.
  if (mail.from && mail.from === ownSenderAddress()) return finish('dropped', 'own');
  if (mail.confirmation) {
    await service.recordInboundMail(userId, 'confirmation', 0, mail.confirmation);
    return finish('confirmation');
  }
  const additions = await addParcelsFromText(service, userId, mail.text, mail.sender);
  const result = mailResult(additions);
  const added = additions.filter(({ outcome }) => outcome === 'added').length;
  await service.recordInboundMail(userId, result, added);
  return finish(result, undefined, additions.length, added);
}

function finish(outcome: InboundOutcome, reason?: string, numbers = 0, added = 0): InboundOutcome {
  logOperationalEvent('inbound_email', { outcome, ...(reason ? { reason } : {}), numbers, added });
  return outcome;
}
