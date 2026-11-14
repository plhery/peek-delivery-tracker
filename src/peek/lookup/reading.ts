import {
  CARRIER_DEFINITIONS, keywordNumberInText, recognizedNumberInText, TRACKING_CANDIDATE_PATTERNS, validTrackingNumber,
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

/**
 * A high-confidence detection rule of one length, written as characters, digits and classes each taken once or a
 * set number of times (`^1Z[A-Z0-9]{16}$`, `^JTAU\d{12}$`). A rule of several lengths, with a lookahead or with
 * alternatives doesn't say how long its carrier's numbers are.
 */
const ONE_LENGTH_RULE = /^\^(?:(?:\\d|\[[^\]\\]+\]|[A-Z0-9])(?:\{\d+\})?)+\$$/;

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
  if (shape.kind === 'order') return t('link.notTracking.order', { shop: SHOP_NAMES[shape.shop] });
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
 * "additional" for one, and a word in a message is not a number.
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
      if (match.trackingNumber) keep(match);
    }
    const prose = line.replace(LINK, ' ');
    const candidates: { index: number; end: number; text: string; detection: CarrierDetection }[] = [];
    for (const pattern of CANDIDATE_PATTERNS) {
      for (const hit of prose.matchAll(pattern)) {
        const candidate = hit[0].trim();
        if (!/\d/.test(candidate)) continue;
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

/** The most labels read on one line: a row of a shipping email names a parcel or two. */
const MOST_LABELS_PER_LINE = 4;

/** A date written year first ("2026-11-12"), which a label such as "Date d'envoi :" may stand before. */
const YEAR_FIRST_DATE = /^\d{4}([./-])\d{1,2}\1\d{1,2}$/;

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
 */
export function labelledNumbersInText(text: string): NumberInText[] {
  const found = new Map<string, NumberInText>();
  for (const line of text.split(/\r?\n/)) {
    let prose = withoutOrders(line.replace(LINK, ' '));
    for (let label = 0; label < MOST_LABELS_PER_LINE && found.size < MOST_CANDIDATES_READ; label += 1) {
      const number = keywordNumberInText(prose);
      if (!number) break;
      prose = prose.slice(numberEnd(prose, number));
      const normalized = normalizeTrackingNumber(number);
      if (!normalized || found.has(normalized) || nonTrackingShape(normalized) || YEAR_FIRST_DATE.test(number.trim())) continue;
      const detection = detectCarrierMatch(number);
      if (detection.confidence === 'low') found.set(normalized, { normalized, match: { trackingNumber: number, source: 'text', ...detection } });
    }
  }
  return [...found.values()];
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
