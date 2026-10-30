import { keywordNumberInText, recognizedNumberInText, TRACKING_CANDIDATE_PATTERNS, validTrackingNumber } from 'universal-parcel-scraper';
import {
  detectCarrierMatch,
  isValidS10TrackingNumber,
  normalizeTrackingNumber,
  parseTrackingInput,
  type TrackingInputMatch,
} from '../../lib/carriers';
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
export type OrderShop = 'amazon' | 'shein' | 'shopee' | 'temu';

const LINK = /https?:\/\/[^\s<>"']+/gi;
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
export type StoredOrderShop = OrderShop | 'aliexpress';

/** Shops by the name a page writes. */
export const SHOP_NAMES: Record<StoredOrderShop, string> = { amazon: 'Amazon', aliexpress: 'AliExpress', shein: 'Shein', shopee: 'Shopee', temu: 'Temu' };

/** What a looked-up number no carrier answered for looks like instead of a tracking number. */
export type NonTrackingShape = { kind: 'order'; shop: StoredOrderShop } | { kind: 'short' };

/** Tracking numbers have 8 characters or more, but for a few carriers' own. */
const SHORTEST_TRACKING_NUMBER = 8;

/**
 * Whether a normalized number no carrier answered for looks like a shop's order number, or is shorter than tracking
 * numbers are: the parcel page says so instead of waiting for a carrier.
 */
export function nonTrackingShape(normalized: string): NonTrackingShape | null {
  const shop = orderShop(normalized) ?? STORED_ORDER_NUMBERS.find(({ pattern }) => pattern.test(normalized))?.shop;
  if (shop) return { kind: 'order', shop };
  return normalized.length < SHORTEST_TRACKING_NUMBER ? { kind: 'short' } : null;
}

const EVERY_ORDER_NUMBER = ORDER_NUMBERS.map(({ pattern }) => new RegExp(pattern.source, 'gi'));
/** A line without its order numbers, so that no part of one is read as a tracking number. */
const withoutOrders = (line: string) => EVERY_ORDER_NUMBER.reduce((rest, order) => rest.replace(order, ' '), line);

/**
 * Every tracking number in a text that one carrier claims with certainty,
 * the rule the parser itself applies to prose. Links count through their own
 * number; each line is read on its own, so numbers on neighbouring lines
 * never run together.
 */
export function numbersInText(text: string): NumberInText[] {
  const found = new Map<string, NumberInText>();
  const keep = (match: TrackingInputMatch) => {
    const normalized = normalizeTrackingNumber(match.trackingNumber);
    if (normalized && !found.has(normalized)) found.set(normalized, { normalized, match });
  };
  for (const line of text.split(/\r?\n/)) {
    for (const link of line.match(LINK) ?? []) {
      const match = parseTrackingInput(link);
      if (match.trackingNumber) keep(match);
    }
    const prose = line.replace(LINK, ' ');
    const candidates: { index: number; end: number; text: string }[] = [];
    for (const pattern of TRACKING_CANDIDATE_PATTERNS) {
      for (const hit of prose.matchAll(new RegExp(pattern.source, pattern.flags))) {
        const candidate = hit[0].trim();
        if (detectCarrierMatch(candidate).confidence !== 'high') continue;
        const index = hit.index, end = index + hit[0].length;
        // A shape inside another certain one is the same number read twice.
        if (!candidates.some((other) => index < other.end && end > other.index)) candidates.push({ index, end, text: candidate });
      }
    }
    for (const candidate of candidates.sort((left, right) => left.index - right.index)) {
      keep({ trackingNumber: candidate.text, source: 'text', ...detectCarrierMatch(candidate.text) });
    }
  }
  return [...found.values()];
}

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
    const prose = withoutOrders(line.replace(LINK, ' '));
    for (const pattern of TRACKING_CANDIDATE_PATTERNS) {
      for (const hit of prose.matchAll(new RegExp(pattern.source, pattern.flags))) {
        const candidate = hit[0].trim();
        if (!/\d/.test(candidate)) continue;
        const detection = detectCarrierMatch(candidate);
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
  if (number) return { trackingNumber: number, source: 'text', ...detectCarrierMatch(number) };
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
