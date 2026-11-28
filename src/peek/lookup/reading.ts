import {
  CARRIER_DEFINITIONS, followsTaxLabel, keywordNumberInText, recognizedNumberInText, TRACKING_CANDIDATE_PATTERNS, validTrackingNumber,
} from 'universal-parcel-scraper';
import {
  type CarrierDetection,
  carrierInfo,
  detectCarrierMatch,
  isValidS10TrackingNumber,
  normalizeTrackingNumber,
  parseTrackingInput,
  type TrackingInputMatch,
} from '../../lib/carriers';
import type { Translate } from '../../lib/messages';
import type { CarrierId } from '../../types';
import rules from '../../../shared/reading.json';

/** One tracking number a text names, with what its shape says about the carrier. */
export interface NumberInText {
  /** The number without spaces, dots or dashes: what makes two spellings the same number. */
  normalized: string;
  match: TrackingInputMatch;
}

/** What the field's text holds. */
export interface Reading {
  /** The one number the Add sheet's parser reads out of the text; its `trackingNumber` is empty when there is none. */
  match: TrackingInputMatch;
  /** Every number the text names with certainty, in the order written. Two or more make the visitor choose. */
  numbers: NumberInText[];
  /** The shop whose order number the text is, which no carrier can track. */
  order: OrderShop | null;
}

/** A shop whose order numbers the door tells from tracking numbers. */
export type OrderShop = 'amazon' | 'mercadolibre' | 'shein' | 'shopee' | 'temu';

const LINK = /https?:\/\/[^\s<>"']+/gi;
/**
 * A percent escape, as a link encoded inside another link prints its "=" ("%3D") or "/" ("%2F"). The two characters
 * after the "%" are no part of the number that follows: "%3D" before nine digits reads as WeDo's digit, letter and
 * nine digits.
 */
const PERCENT_ESCAPE = /%[0-9A-F]{2}/gi;
/**
 * A shop's order label, in the door's languages and a few more: "Order #", "Bestelling", "Bestellnummer:", "N° de
 * commande :", "número de pedido", "nr zamówienia". A number right after one is the order's, whatever carrier its
 * shape fits, as DTDC's "N" and eight digits. "Encomenda" is left out: it names a parcel in Brazil.
 */
const ORDER_WORDS = String.raw`orders?|ordre|bestellung|bestelling|bestell|bestel|best[äa]llning|bestilling|auftrag|commande|ordine|pedido|orden|zam[óo]wieni[ae]|objedn[áa]vk[ay]|tilaus|rendel[ée]s|comand[aă]|заказа?`;
const ORDER_NUMBER_WORDS = String.raw`numbers?|no|nr|nummer|numero|num[ée]ro|n[úu]mero|numer|n[°º]|n\.?º|id|ref|reference|č|номер|szám`;
const ORDER_LABEL = new RegExp(String.raw`(?<![\p{L}0-9])(?:(?:${ORDER_NUMBER_WORDS})[\s.]*(?:(?:de|du|del|do|da|di|of)\s+|d['’]\s*)?)?`
  + String.raw`(?:${ORDER_WORDS})(?:s?(?:nummer|nr|numero|szám))?(?:[\s.\-/]*(?:${ORDER_NUMBER_WORDS})(?![\p{L}]))*[\s.]*[:#]?\s*$`, 'iu');
/** Whether the number starting at `index` of a line follows an order's label on it (ORDER_LABEL). */
function followsOrderLabel(line: string, index: number): boolean {
  return ORDER_LABEL.test(line.slice(Math.max(0, index - 48), index));
}
/** Whether the number starting at `index` of a line is another number's, by the label before it: a tax number's or an order's. */
const followsOtherLabel = (line: string, index: number) => followsTaxLabel(line, index) || followsOrderLabel(line, index);
/** Each country's IBAN length (ISO 13616), for the countries whose shops and carriers print one. */
const IBAN_LENGTHS: Readonly<Record<string, number>> = {
  AD: 24, AT: 20, BE: 16, BG: 22, CH: 21, CY: 28, CZ: 24, DE: 22, DK: 18, EE: 20, ES: 24, FI: 18, FO: 18, FR: 27,
  GB: 22, GI: 23, GL: 18, GR: 27, HR: 21, HU: 28, IE: 22, IS: 26, IT: 27, LI: 21, LT: 20, LU: 20, LV: 21, MC: 27,
  MT: 31, NL: 18, NO: 15, PL: 28, PT: 25, RO: 24, SE: 24, SI: 19, SK: 24, SM: 27,
};
const IBAN_START = /\b[A-Z]{2}\d{2}/g;
/** An IBAN's check digits hold when, its first four characters moved to its end and its letters read as 10 to 35, 97 leaves 1. */
function ibanChecks(iban: string): boolean {
  let rest = 0;
  for (const char of iban.slice(4) + iban.slice(0, 4)) {
    const value = parseInt(char, 36);
    rest = (value > 9 ? rest * 100 + value : rest * 10 + value) % 97;
  }
  return rest === 1;
}
/**
 * A line without its bank account numbers, each set apart by spaces. An IBAN printed in groups of four
 * ("BE68 5390 0754 7034") starts with a country, two digits and ten more, which is the shape of Amazon's numbers,
 * and a shop's footer prints one after its bank's name, which is no label the readers know. Only an IBAN as long as
 * its country's, whose check digits hold, is set apart.
 */
function withoutBankAccounts(line: string): string {
  let out = line;
  for (const start of line.matchAll(IBAN_START)) {
    const length = IBAN_LENGTHS[start[0].slice(0, 2)];
    if (!length) continue;
    let index = start.index, taken = '';
    while (index < line.length && taken.length < length) {
      const char = line[index]!;
      if (/[A-Z0-9]/.test(char)) taken += char;
      // One space between groups of four.
      else if (char !== ' ' || taken.length % 4 !== 0 || !/[A-Z0-9]/.test(line[index + 1] ?? '')) break;
      index += 1;
    }
    if (taken.length === length && !/[A-Za-z0-9]/.test(line[index] ?? '') && ibanChecks(taken)) {
      out = out.slice(0, start.index) + ' '.repeat(index - start.index) + out.slice(index);
    }
  }
  return out;
}
/** Whether a number read out of a link's own text stands in it once its percent escapes are set apart. */
const standsInLink = (link: string, number: string) => link.replace(PERCENT_ESCAPE, ' ').toUpperCase().includes(number.toUpperCase());
/** Each shop's order number, tried in turn and read without regard to case. The iPhone reads the same file. */
const ORDER_NUMBERS = rules.shops.map(({ shop, pattern }) => ({ shop: shop as OrderShop, pattern: new RegExp(pattern, 'i') }));

/** The shop whose order number a text holds. */
const orderShop = (text: string) => ORDER_NUMBERS.find(({ pattern }) => pattern.test(text))?.shop ?? null;

/**
 * Order numbers as a lookup stores them, without hyphens: the door can't tell these from a carrier's numbers before
 * the carriers are asked, but a parcel no carrier answered for is most likely one.
 */
const STORED_ORDER_NUMBERS = rules.storedOrders.map(({ shop, pattern }) => ({ shop: shop as StoredOrderShop, pattern: new RegExp(pattern) }));

/** A shop whose order numbers a parcel page tells from tracking numbers once no carrier answered for one. */
export type StoredOrderShop = OrderShop | 'aliexpress' | 'mercadolibre';

/** Shops by the name a page writes. */
export const SHOP_NAMES: Record<StoredOrderShop, string> = {
  amazon: 'Amazon', aliexpress: 'AliExpress', mercadolibre: 'Mercado Libre', shein: 'Shein', shopee: 'Shopee', temu: 'Temu',
};

/** The names a shop goes by in one language: Mercado Libre is Mercado Livre in Brazil. */
const LOCAL_SHOP_NAMES: Partial<Record<string, Partial<Record<StoredOrderShop, string>>>> = { 'pt-br': { mercadolibre: 'Mercado Livre' } };

/** A shop by the name a page in this language writes. */
export const shopName = (shop: StoredOrderShop, locale?: string) => (locale && LOCAL_SHOP_NAMES[locale]?.[shop]) || SHOP_NAMES[shop];

/** What a looked-up number no carrier answered for looks like instead of a tracking number. */
export type NonTrackingShape = { kind: 'order'; shop: StoredOrderShop } | { kind: 'container' } | MistypedNumber | { kind: 'short' };

/** A number that starts like one carrier's numbers and is one character short of their length, or one over it. */
export interface MistypedNumber {
  kind: 'mistyped';
  carrier: CarrierId;
  change: 'missing' | 'extra';
}

/** Tracking numbers have 8 characters or more, but for a few carriers' own. */
const SHORTEST_TRACKING_NUMBER = 8;

/** ISO 6346's letter values: 10 for A and up from there, leaving out 11, 22 and 33. */
const CONTAINER_LETTER_VALUES = Array.from({ length: 29 }, (_, index) => 10 + index).filter((value) => value % 11 !== 0);

/**
 * Whether a normalized number is a shipping container's (ISO 6346): its owner's three letters, U for a freight
 * container, six digits and a check digit that adds up. No carrier issues one for a parcel.
 */
export function isContainerNumber(normalized: string): boolean {
  if (!/^[A-Z]{3}U[0-9]{7}$/.test(normalized)) return false;
  let sum = 0;
  for (let index = 0; index < 10; index += 1) {
    const value = index < 4 ? CONTAINER_LETTER_VALUES[normalized.charCodeAt(index) - 65] : Number(normalized[index]);
    sum += value * 2 ** index;
  }
  return sum % 11 % 10 === Number(normalized[10]);
}

/** A character, digit or class of a detection rule, taken once or a set number of times. */
const RULE_PART = String.raw`(?:\\d|\[[^\]\\]+\]|[A-Z0-9])(?:\{\d+\})?`;
/**
 * A high-confidence detection rule of one length, written as such parts (`^1Z[A-Z0-9]{16}$`, `^JTAU\d{12}$`), and
 * at most leaving out some numbers of that length (`^JTDE(?!\d{2}00)\d{12}$`). A rule of several lengths, with a
 * lookahead that sets the length or with alternatives doesn't say how long its carrier's numbers are.
 */
const ONE_LENGTH_RULE = new RegExp(String.raw`^\^(?:${RULE_PART}|\(\?!(?:${RULE_PART})+\))+\$$`);

/** The characters a rule spells out at its start, not counting one a count follows. */
function spelledStart(pattern: string): string {
  const start = /^\^([A-Z0-9]*)/.exec(pattern)?.[1] ?? '';
  return pattern[start.length + 1] === '{' ? start.slice(0, -1) : start;
}

interface LengthRule {
  carrier: CarrierId;
  start: string;
  pattern: RegExp;
}

let lengthRules: readonly LengthRule[] | undefined;
/**
 * The rules a number can be one character off: of one length, spelling out at least two characters at their start,
 * one of them a letter, so that a number starting with them is most likely that carrier's. A rule that also reads the
 * number as typed is left out.
 */
function oneLengthRules(): readonly LengthRule[] {
  lengthRules ??= Object.entries(CARRIER_DEFINITIONS).flatMap(([carrier, definition]) => definition.detectionRules.flatMap((rule) => {
    const start = spelledStart(rule.pattern);
    return rule.confidence === 'high' && !rule.rawPattern && ONE_LENGTH_RULE.test(rule.pattern) && start.length >= 2 && /[A-Z]/.test(start)
      ? [{ carrier: carrier as CarrierId, start, pattern: new RegExp(rule.pattern) }] : [];
  }));
  return lengthRules;
}

const CHARACTERS = [...'0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ'];

/** Whether leaving out one character after the rule's start, or adding one, makes the number fit the rule. */
function oneCharacterOff(number: string, rule: LengthRule): MistypedNumber['change'] | null {
  if (!number.startsWith(rule.start) || rule.pattern.test(number)) return null;
  for (let index = rule.start.length; index <= number.length; index += 1) {
    const before = number.slice(0, index);
    const after = number.slice(index);
    if (after && rule.pattern.test(before + after.slice(1))) return 'extra';
    if (CHARACTERS.some((character) => rule.pattern.test(before + character + after))) return 'missing';
  }
  return null;
}

/**
 * A number that starts like one carrier's numbers but is one character short of their length or one over it: it
 * may be mistyped. Only when no high-confidence rule takes the number as it is, and the rules it is one character
 * off are one carrier's.
 */
export function mistypedNumber(normalized: string): MistypedNumber | null {
  if (detectCarrierMatch(normalized).confidence === 'high') return null;
  const found = oneLengthRules().flatMap((rule) => {
    const change = oneCharacterOff(normalized, rule);
    return change ? [{ kind: 'mistyped' as const, carrier: rule.carrier, change }] : [];
  });
  return found.length > 0 && found.every(({ carrier }) => carrier === found[0].carrier) ? found[0] : null;
}

/**
 * Whether a normalized number no carrier answered for looks like a shop's order number or a shipping container's,
 * starts like one carrier's numbers with one character too few or too many, or is shorter than tracking numbers
 * are: the parcel page says so instead of waiting for a carrier.
 */
export function nonTrackingShape(normalized: string): NonTrackingShape | null {
  const shop = orderShop(normalized) ?? STORED_ORDER_NUMBERS.find(({ pattern }) => pattern.test(normalized))?.shop;
  if (shop) return { kind: 'order', shop };
  if (isContainerNumber(normalized)) return { kind: 'container' };
  return mistypedNumber(normalized) ?? (normalized.length < SHORTEST_TRACKING_NUMBER ? { kind: 'short' } : null);
}

/** What a parcel says about its number's shape, where it would say that no carrier knows the number yet. */
export function nonTrackingNote(shape: NonTrackingShape, t: Translate, locale?: string): string {
  if (shape.kind === 'order' && shape.shop === 'mercadolibre') return t('link.notTracking.mercadolibre');
  if (shape.kind === 'order') return t('link.notTracking.order', { shop: shopName(shape.shop, locale) });
  if (shape.kind === 'mistyped') {
    return t(shape.change === 'missing' ? 'link.notTracking.missing' : 'link.notTracking.extra', { carrier: carrierInfo(shape.carrier, locale).name });
  }
  return t(shape.kind === 'container' ? 'link.notTracking.container' : 'link.notTracking.short');
}

const EVERY_ORDER_NUMBER = ORDER_NUMBERS.map(({ pattern }) => new RegExp(pattern.source, 'gi'));
/** A line without its order numbers, so that no part of one is read as a tracking number. */
const withoutOrders = (line: string) => EVERY_ORDER_NUMBER.reduce((rest, order) => rest.replace(order, ' '), line);

/**
 * The most distinct links and candidates one text is read for, the first ones in the order written. An email names a
 * few dozen links, and 16 KB of dense prose about seven hundred words; a paste of thousands would hold the server's
 * thread for seconds.
 */
export const MOST_LINKS_READ = 100;
export const MOST_CANDIDATES_READ = 1_000;
/** The candidate shapes, copied once: reading a text through them leaves each copy as it was. */
const CANDIDATE_PATTERNS = TRACKING_CANDIDATE_PATTERNS.map((pattern) => new RegExp(pattern.source, pattern.flags));

/**
 * Every tracking number in a text that one carrier claims with certainty,
 * the rule the parser itself applies to prose. Links count through their own
 * number; each line is read on its own, so numbers on neighbouring lines
 * never run together. A link or a candidate met again reads as it did the
 * first time, so each is read once: detection reads a candidate in capitals.
 * A candidate holds a digit: some carriers' shapes also fit a word,
 * "additional" for one, and a word in a message is not a number. A percent
 * escape is no part of a number, in a link the parser read as text or in the
 * rest of one an email's text wrapped onto the next line. A number after a
 * tax or company-number label ("VAT: BE0123456789") or inside a bank
 * account's IBAN is a business's, which Amazon's shape also fits, and one
 * after an order's label is the shop's order number.
 */
export function numbersInText(text: string): NumberInText[] {
  const found = new Map<string, NumberInText>();
  const keep = (match: TrackingInputMatch) => {
    const normalized = normalizeTrackingNumber(match.trackingNumber);
    if (normalized && !found.has(normalized)) found.set(normalized, { normalized, match });
  };
  const links = new Set<string>();
  const detections = new Map<string, CarrierDetection>();
  const detection = (candidate: string) => {
    const key = candidate.toUpperCase();
    let known = detections.get(key);
    if (!known && detections.size < MOST_CANDIDATES_READ) {
      known = detectCarrierMatch(candidate);
      detections.set(key, known);
    }
    return known;
  };
  for (const line of text.split(/\r?\n/)) {
    for (const link of line.match(LINK) ?? []) {
      if (links.has(link) || links.size >= MOST_LINKS_READ) continue;
      links.add(link);
      const match = parseTrackingInput(link);
      if (match.trackingNumber && (match.source === 'link' || standsInLink(link, match.trackingNumber))) keep(match);
    }
    const prose = withoutBankAccounts(line).replace(SHIPPING_DATE, ' ').replace(LINK, ' ').replace(PERCENT_ESCAPE, ' ');
    const candidates: { index: number; end: number; text: string; detection: CarrierDetection }[] = [];
    for (const pattern of CANDIDATE_PATTERNS) {
      for (const hit of prose.matchAll(pattern)) {
        const candidate = hit[0].trim();
        if (!/\d/.test(candidate) || followsOtherLabel(prose, hit.index)) continue;
        const detected = detection(candidate);
        if (detected?.confidence !== 'high') continue;
        const index = hit.index, end = index + hit[0].length;
        // A shape inside another certain one is the same number read twice.
        if (!candidates.some((other) => index < other.end && end > other.index)) candidates.push({ index, end, text: candidate, detection: detected });
      }
    }
    for (const candidate of candidates.sort((left, right) => left.index - right.index)) {
      keep({ trackingNumber: candidate.text, source: 'text', ...candidate.detection });
    }
  }
  return [...found.values()];
}

/**
 * How a number read out of a text is detected: a shape that fits words and references as often as tracking numbers,
 * such as R+L's two letters and seven digits, names its carrier only for a number given on its own.
 */
const IN_TEXT = { inText: true } as const;

/** Words that introduce a tracking number in the door's languages. */
const NUMBER_WORDS = new RegExp(rules.numberWords, 'i');

/**
 * A number in prose whose shape fits a carrier without proving one: "Track it
 * with DHL: 1234567899". The parser leaves these alone, since a phone number
 * can look the same; the door takes one when the text holds no other
 * candidate, or when only one stands on a line that speaks of tracking. The
 * carriers are then asked, and the visitor sees which number was read. A
 * candidate holds a digit: some shapes also fit a word, and a word in a
 * message is not a number.
 */
function uncertainNumberInText(text: string): TrackingInputMatch | null {
  const candidates = new Map<string, { match: TrackingInputMatch; introduced: boolean }>();
  for (const line of text.split(/\r?\n/)) {
    const prose = withoutOrders(withoutBankAccounts(line).replace(SHIPPING_DATE, ' ').replace(LINK, ' '));
    for (const pattern of TRACKING_CANDIDATE_PATTERNS) {
      for (const hit of prose.matchAll(new RegExp(pattern.source, pattern.flags))) {
        const candidate = hit[0].trim();
        if (!/\d/.test(candidate) || followsOtherLabel(prose, hit.index)) continue;
        const detection = detectCarrierMatch(candidate, IN_TEXT);
        if (detection.confidence !== 'low') continue;
        const normalized = normalizeTrackingNumber(candidate);
        const known = candidates.get(normalized);
        candidates.set(normalized, {
          match: { trackingNumber: candidate, source: 'text', ...detection },
          introduced: Boolean(known?.introduced) || NUMBER_WORDS.test(prose),
        });
      }
    }
  }
  const all = [...candidates.values()];
  const introduced = all.filter((candidate) => candidate.introduced);
  return all.length === 1 ? all[0].match : introduced.length === 1 ? introduced[0].match : null;
}

/** The most labels read on one line: a row of a shipping email names a parcel or two. */
const MOST_LABELS_PER_LINE = 4;

/** A date written year first ("2026-11-12"), which a label such as "Date d'envoi :" may stand before. */
const YEAR_FIRST_DATE = /^\d{4}([./-])\d{1,2}\1\d{1,2}$/;

// Shipping-date labels contain words that can also introduce a parcel number.
const SHIPPING_DATE = /\b(?:date\s+d['’]envoi|fecha\s+de\s+env[ií]o|shipping\s+date|ship\s+date)\s*[:：-]?\s*\d{8}\b/giu;

/** Where a number the label reader read ends in the text: it joins a number printed in groups ("1234 5678 9012"). */
function numberEnd(prose: string, number: string): number {
  const printed = new RegExp([...number].map((char) => char.replace(/[.*+?^${}()|[\]\\-]/g, '\\$&')).join('\\s?'));
  const found = printed.exec(prose);
  return found ? found.index + found[0].length : prose.length;
}

/**
 * The numbers a text's own words call tracking numbers, whose shape fits a carrier without proving one:
 * "Sendungsnummer 1234567899", "Votre colis 01234567890123 arrive demain". Most couriers' numbers are plain
 * digits, which only such a label tells from an order number or a phone number, so a shipping email is read for
 * them. The label stands right before its number, as the parser reads one (`keywordNumberInText`); the number is
 * as long as tracking numbers are and is no shop's order number. The carriers are then asked. Numbers a carrier
 * claims with certainty are `numbersInText`'s.
 *
 * A text someone chose to send (`trust`) may label a number whose shape fits no carrier: it is taken too, for the
 * universal providers to look for.
 */
export function labelledNumbersInText(text: string, trust?: TextTrust): NumberInText[] {
  const found = new Map<string, NumberInText>();
  for (const line of text.split(/\r?\n/)) {
    let prose = withoutOrders(withoutBankAccounts(line).replace(SHIPPING_DATE, ' ').replace(LINK, ' '));
    for (let label = 0; label < MOST_LABELS_PER_LINE && found.size < MOST_CANDIDATES_READ; label += 1) {
      const number = keywordNumberInText(prose);
      if (!number) break;
      prose = prose.slice(numberEnd(prose, number));
      const normalized = normalizeTrackingNumber(number);
      if (!normalized || found.has(normalized) || YEAR_FIRST_DATE.test(number.trim())) continue;
      if (trust === 'named' ? neverTracked(normalized) : nonTrackingShape(normalized)) continue;
      const detection = detectCarrierMatch(number, IN_TEXT);
      if (detection.confidence === 'low' || (trust && detection.confidence === 'none')) {
        found.set(normalized, { normalized, match: { trackingNumber: number, source: 'text', ...detection } });
      }
    }
  }
  return [...found.values()];
}

/**
 * How far a text is read beyond an email's, when someone chose to send it, as an assistant does. `sent` takes a
 * number the text labels, or the one the whole text is, though its shape fits no carrier, as the Add sheet takes
 * a typed one: the universal providers then look for it. `named` is for a sender who names the carrier, as a
 * shop's tracking page does: the number may then be shorter than most, or a character off another carrier's.
 */
export type TextTrust = 'sent' | 'named';

/** Whether a normalized number is one no carrier tracks whoever names it: a shop's order number or a shipping container's. */
function neverTracked(normalized: string): boolean {
  const shape = nonTrackingShape(normalized);
  return shape?.kind === 'order' || shape?.kind === 'container';
}

/** A date as a carrier prints one: "14.11.2026", "2026-11-14". */
const PRINTED_DATE = /^(?:\d{1,2}([./-])\d{1,2}\1\d{2,4}|\d{4}([./-])\d{1,2}\2\d{1,2})$/;

/**
 * The number a text is, when it is one tracking number and nothing else, as the Add sheet takes a number typed on its
 * own, whatever carrier its shape fits or none: one word, or groups that each hold a digit ("1234 5678 9012"), so no
 * sentence; no link, no date, no shop's order number and no shipping container's. It is as long as tracking numbers
 * are, unless its sender names the carrier (`named`).
 */
export function numberAlone(text: string, named = false): NumberInText | null {
  const whole = text.trim();
  if (!whole || whole.includes('://') || whole.split(/\s+/).some((group) => !/\d/.test(group))) return null;
  if (PRINTED_DATE.test(whole) || orderShop(whole) || !validTrackingNumber(whole)) return null;
  const normalized = normalizeTrackingNumber(whole);
  if (!normalized || (named ? neverTracked(normalized) : nonTrackingShape(normalized))) return null;
  return { normalized, match: { trackingNumber: whole, source: 'number', ...detectCarrierMatch(whole) } };
}

/** A line that gives a phone number, whose digits can fit a courier's shape. */
const PHONE_LINE = /(?:^|[^\p{L}])(?:t[eé]l(?:[eé]fono|[eé]phone|efon)?|phone|fax|hotline|mobile|handy|whatsapp)(?![\p{L}])|☎|📞/iu;

/**
 * The one number of a carrier's own email that stands without a label: "Votre colis\n01234567890123". An email
 * from a carrier is about its parcel, so a number in that carrier's shape is most likely the parcel's, where a
 * shop's email could mean an order or a customer by it. Taken only when the text holds exactly one such number,
 * no shop's order number, no date and not on a line that gives a phone number; two leave the choice open, and
 * none is read.
 */
export function carrierNumberInText(text: string, carriers: readonly CarrierId[]): NumberInText | null {
  if (!carriers.length) return null;
  const found = new Map<string, NumberInText>();
  for (const line of text.split(/\r?\n/)) {
    if (PHONE_LINE.test(line)) continue;
    const prose = withoutOrders(withoutBankAccounts(line).replace(SHIPPING_DATE, ' ').replace(LINK, ' '));
    for (const pattern of CANDIDATE_PATTERNS) {
      for (const hit of prose.matchAll(pattern)) {
        const candidate = hit[0].trim();
        // "+41 44 123 45 67": a number after a plus sign is a phone's.
        if (!/\d/.test(candidate) || PRINTED_DATE.test(candidate) || followsOrderLabel(prose, hit.index)
          || /\+[\d\s()]*$/.test(prose.slice(Math.max(0, hit.index - 8), hit.index + 1))) continue;
        const normalized = normalizeTrackingNumber(candidate);
        if (!normalized || found.has(normalized) || nonTrackingShape(normalized)) continue;
        if (found.size >= MOST_CANDIDATES_READ) return null;
        const detection = detectCarrierMatch(candidate, IN_TEXT);
        if (detection.confidence !== 'low' || !detection.candidates.some((carrier) => carriers.includes(carrier))) continue;
        found.set(normalized, { normalized, match: { trackingNumber: candidate, source: 'text', ...detection } });
      }
    }
  }
  return found.size === 1 ? [...found.values()][0]! : null;
}

/**
 * A number read from a carrier's own email, with that carrier named where the number's shape proved none: the
 * first of the sender's carriers the shape fits, or the sender's only carrier. A number a carrier claims with
 * certainty stays that carrier's.
 */
export function fromCarrier(number: NumberInText, carriers: readonly CarrierId[]): NumberInText {
  if (number.match.confidence === 'high' || !carriers.length) return number;
  const carrier = number.match.candidates.find((candidate) => carriers.includes(candidate)) ?? (carriers.length === 1 ? carriers[0]! : null);
  return carrier ? { ...number, match: { ...number.match, carrier } } : number;
}

const NOTHING = parseTrackingInput('');

/**
 * The number the parser reads out of a text. It knows no shop's order number, and would take "PO-000" from
 * "Parcel PO-000 12345678901234567", so its rules for prose run again on the text without its order numbers, and
 * the whole text stays its last resort.
 */
function parse(text: string): TrackingInputMatch {
  const parsed = parseTrackingInput(text);
  if (parsed.source !== 'text' || !orderShop(text)) return parsed;
  const prose = withoutOrders(text);
  const number = recognizedNumberInText(prose) ?? keywordNumberInText(prose);
  if (number) return { trackingNumber: number, source: 'text', ...detectCarrierMatch(number, IN_TEXT) };
  const whole = text.trim();
  return !whole.includes('://') && validTrackingNumber(whole) ? { trackingNumber: whole, source: 'number', ...detectCarrierMatch(whole) } : NOTHING;
}

// The same text is read several times on its way through an event and a render.
const read = new Map<string, Reading>();

/**
 * Reads the field: the number the parser finds, the others beside it, and whose order number it is instead. The
 * iPhone's Add sheet reads text the same way, and replays the examples in shared/reading.json.
 */
export function readText(text: string): Reading {
  const known = read.get(text);
  if (known) return known;
  const parsed = parse(text);
  const match = parsed.trackingNumber ? parsed : uncertainNumberInText(text) ?? parsed;
  const numbers = numbersInText(text);
  const order = match.confidence === 'high' ? null : orderShop(match.trackingNumber || text);
  const reading: Reading = { match, numbers: numbers.length > 1 ? numbers : [], order };
  if (read.size >= 16) read.delete(read.keys().next().value!);
  read.set(text, reading);
  return reading;
}

/** Typing a number sets it in the label's monospace; a message or a link reads as prose. */
export function looksLikeNumber(text: string): boolean {
  const tokens = text.trim().split(/\s+/).filter(Boolean);
  if (!tokens.length || text.includes('://')) return false;
  return tokens.length === 1 || tokens.every((token) => /\d/.test(token));
}

/** Letters a scanner or a tired eye reads where a label prints a digit, and the other way around. */
const DIGIT_FOR: Record<string, string | undefined> = rules.lookalikes.digitForLetter;
const LETTER_FOR: Record<string, string | undefined> = rules.lookalikes.letterForDigit;

export interface CheckDigitTrouble {
  /** The number with a lookalike letter read as its digit (or back), when that one adds up. */
  suggestion: string | null;
}

/**
 * A postal number (2 letters, 9 digits, 2 letters) whose check digit does not
 * add up. A letter standing where a digit belongs has one likely correction;
 * a wrong digit has many, each as likely as the next, so none is offered.
 * Carriers that reuse the shape without the check digit exist, so this is a
 * question to the visitor, never a refusal.
 */
export function checkDigitTrouble(number: string): CheckDigitTrouble | null {
  const value = normalizeTrackingNumber(number);
  if (!/^[A-Z0-9]{13}$/.test(value) || isValidS10TrackingNumber(value) || detectCarrierMatch(value).confidence === 'high') return null;
  const postal = [...value].map((character, index) => {
    const digit = index >= 2 && index <= 10;
    if (digit) return /\d/.test(character) ? character : DIGIT_FOR[character];
    return /[A-Z]/.test(character) ? character : LETTER_FOR[character];
  });
  if (postal.some((character) => character === undefined)) return null;
  const read = postal.join('');
  if (read === value) return { suggestion: null };
  return isValidS10TrackingNumber(read) ? { suggestion: read } : null;
}
