import 'server-only';

import rules from '../../../shared/email-filter.json';

/**
 * Whether an email's subject is a shipping email's: it holds a word of the mail filter's lists
 * (shared/email-filter.json), in any of their languages. An email that holds none, such as a newsletter, a bill or a
 * sign-up, is about no parcel, even when the reader forwards all their mail: Peek neither reads nor keeps it.
 *
 * Case and accents count for nothing, as in Gmail's search, and a word may go on: "parcels", "Pakete",
 * "Paketzustellung", "consegnato". Chinese has no spaces, so its words count wherever they stand.
 */
const fold = (text: string) => text.normalize('NFD').replace(/\p{M}/gu, '').toLowerCase();
const escape = (text: string) => text.replace(/[.*+?^${}()|[\]\\]/g, '\\$&');
const words = [...new Set(Object.values(rules.words).flat().map(fold))];
const spaced = words.filter((word) => /[\p{Script=Latin}\p{Script=Cyrillic}]/u.test(word));
const unspaced = words.filter((word) => !spaced.includes(word));
const pattern = (list: string[]) => list.map((word) => word.split(/\s+/).map(escape).join(String.raw`\s+`)).join('|');
const SHIPPING_WORD = new RegExp(String.raw`(?<![\p{L}\p{N}])(?:${pattern(spaced)})|${pattern(unspaced)}`, 'u');

export function shippingSubject(subject: string): boolean {
  return SHIPPING_WORD.test(fold(subject));
}
