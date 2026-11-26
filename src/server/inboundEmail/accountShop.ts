import 'server-only';

import type { InboundMailEntry, InboundMailResult } from '../supabase';

/**
 * Shops whose shipping emails hold no tracking number: they show it only in the reader's account with them. The
 * email log says so for an email of theirs that held none, instead of "No tracking number in it". They are known by
 * the sender's name the log keeps: Amazon signs with its store's domain ("Amazon.it", "Amazon.co.uk") or "Amazon".
 * A shop goes here once the email log shows its emails ending without a number while they link to its order page.
 */
const ACCOUNT_SHOPS: readonly { name: string; sender: RegExp }[] = [
  { name: 'Amazon', sender: /^amazon(?:\.[a-z]{2,3}){0,2}$/i },
];

/** The shop whose account alone shows the tracking number of an email that held none, or null. */
export function accountShopOf(entry: Pick<InboundMailEntry, 'sender' | 'result'>): string | null {
  if (entry.result !== 'none') return null;
  const sender = entry.sender.trim();
  return ACCOUNT_SHOPS.find((shop) => shop.sender.test(sender))?.name ?? null;
}

/**
 * An email's result as the account reads it: one that started a wait for its shop reads `none`, with `waiting` on its
 * line (withAccountShops), so that apps from before it read the log as they did.
 */
export function servedMailResult(result: InboundMailResult): Exclude<InboundMailResult, 'waiting'> {
  return result === 'waiting' ? 'none' : result;
}

/**
 * The email log as the account reads it: an email that held none names the shop whose account shows its number, and
 * one whose shop link named no parcel yet says that Peek waits for the shop (`waiting`).
 */
export function withAccountShops<Entry extends Pick<InboundMailEntry, 'sender' | 'result'>>(recent: readonly Entry[]):
  (Omit<Entry, 'result'> & { result: Exclude<InboundMailResult, 'waiting'>; account?: string; waiting?: true })[] {
  return recent.map((entry) => {
    if (entry.result === 'waiting') return { ...entry, result: 'none', waiting: true };
    const account = accountShopOf(entry);
    return account ? { ...entry, result: servedMailResult(entry.result), account } : { ...entry, result: servedMailResult(entry.result) };
  });
}
