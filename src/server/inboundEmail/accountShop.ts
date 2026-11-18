import 'server-only';

import type { InboundMailEntry } from '../supabase';

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

/** The email log as the account reads it: an email that held none names the shop whose account shows its number. */
export function withAccountShops<Entry extends Pick<InboundMailEntry, 'sender' | 'result'>>(recent: readonly Entry[]): (Entry & { account?: string })[] {
  return recent.map((entry) => {
    const account = accountShopOf(entry);
    return account ? { ...entry, account } : entry;
  });
}
