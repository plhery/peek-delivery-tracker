import 'server-only';

import type { ApiParcelNumberHint } from '../generated/apiContract';
import { maskedNumber } from '../peek/linkModel';
import { isRecord, type JsonObject } from './types';

/**
 * A parcel's numbers in the carriers' words, for whoever is not shown its
 * number: each one reads as the app writes a masked number ("••• 3456"),
 * written with spaces or dashes, in capitals or not, inside a web address too.
 */

/** A shorter number, or one without a digit, would match ordinary words and figures: it is left as written. */
const SHORTEST = 6;
const LONGEST = 64;

/**
 * The end of a masked number: its last quarter, four characters at most. The
 * start stays hidden: `head` is empty, and stays in the answer for the
 * clients that read it.
 */
export function numberHint(trackingNumber: string): ApiParcelNumberHint {
  const tail = Math.min(4, Math.floor(trackingNumber.length / 4));
  return { head: '', tail: trackingNumber.slice(trackingNumber.length - tail) };
}

/** A number as it is compared: its letters and digits, in capitals. */
function bare(value: string): string {
  return value.toUpperCase().replace(/[^A-Z0-9]/g, '');
}

/**
 * The numbers a parcel is stored with: its own, and each one its carrier data
 * keeps under a key named `number` or ending in `_number`, at any depth but in
 * lists: another leg's, the one its carrier tracks it by, a delivery
 * partner's, the ones its routing tried.
 */
export function parcelNumbers(row: JsonObject): string[] {
  const found = typeof row.tracking_number === 'string' ? [row.tracking_number] : [];
  const visit = (value: unknown, depth: number) => {
    if (!isRecord(value) || depth > 4) return;
    for (const [key, member] of Object.entries(value)) {
      if (typeof member === 'string' && (key === 'number' || key.endsWith('_number'))) found.push(member);
      else visit(member, depth + 1);
    }
  };
  visit(row.carrier_data, 0);
  return found;
}

/** A word of letters and digits, with dashes inside it: what a number named in the carrier's words is. */
const WORD = '([A-Z0-9]+(?:-[A-Z0-9]+)*)';
/** The word after a label that names a number: "Tracking No.: …", "tracking number …", "Waybill generated for …", "AWB …". */
const LABELLED = new RegExp(String.raw`\b(?:tracking\s*(?:no\b\.?|nr\b\.?|number|id|code|#)|waybill(?:\s*(?:no\b\.?|number|#))?(?:\s+generated\s+for)?`
  + String.raw`|awb(?:\s*(?:no\b\.?|number|#))?)\s*[:#=]?\s*${WORD}`, 'gi');
const ADDRESS = /\bhttps?:\/\/[^\s<>"']+/gi;
/** The value of a web address's query or fragment parameter: "?nums=…", "#n=…". */
const PARAMETER = new RegExp(String.raw`[?&#;][\w.%-]{1,40}=${WORD}`, 'gi');

/**
 * The numbers a carrier's words name that the parcel may not store, as its
 * delivery partner's: a label's word (`LABELLED`), and a web address's
 * parameter value (`PARAMETER`). Only those of six letters and digits or more
 * with a digit count.
 */
function namedNumbers(text: string): string[] {
  const addresses = [...text.matchAll(ADDRESS)].flatMap(([address]) => [...address.matchAll(PARAMETER)]);
  return [...text.matchAll(LABELLED), ...addresses].map((match) => match[1]!);
}

/**
 * What a viewer who is not shown the parcel's number reads of a text: every
 * one of the numbers given and of those the texts name (`namedNumbers`), the
 * longest first, masked as the app masks the parcel's own.
 */
export function numberMask(numbers: readonly unknown[], texts: readonly unknown[]): (text: string) => string {
  const named = texts.filter((text): text is string => typeof text === 'string').flatMap(namedNumbers);
  const known = [...new Set([...numbers, ...named].filter((value): value is string => typeof value === 'string').map(bare))]
    .filter((number) => number.length >= SHORTEST && number.length <= LONGEST && /\d/.test(number))
    .sort((a, b) => b.length - a.length);
  if (!known.length) return (text) => text;
  const pattern = new RegExp(known.map((number) => [...number].join('[\\s-]*')).join('|'), 'gi');
  return (text) => text.replace(pattern, (number) => maskedNumber(numberHint(bare(number))));
}

/** The carrier data a viewer reads in the carrier's words, which may name a number. */
export const WORDED_CARRIER_DATA = ['sender_name', 'pickup_point', 'dimensions_text'];

/**
 * What a viewer who is not shown the parcel's number reads of a text of the parcel (`numberMask`): the numbers it is
 * stored with, and those its status line, worded carrier data and steps name. A link's page and its alerts mask alike.
 */
export function parcelNumberMask(row: JsonObject, steps: readonly JsonObject[]): (text: string) => string {
  const data = isRecord(row.carrier_data) ? row.carrier_data : {};
  return numberMask(parcelNumbers(row), [row.last_status_text, ...WORDED_CARRIER_DATA.map((key) => data[key]),
    ...steps.flatMap((step) => [step.description, step.location])]);
}
