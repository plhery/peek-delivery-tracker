import { useSyncExternalStore } from 'react';
import { CARRIERS, requirementSatisfied, type CarrierInputRequirement } from './carriers';
import { homePostcodeCountry, mayReadPostcodeCountry, postcodeCountries, postcodeFieldFor, readsPostcodeCountry } from './postcodeCountry';
import type { CarrierId } from '../types';

/**
 * The postcodes this browser gave carriers for lookups without an account,
 * newest first. They are kept apart from the parcels, so a parcel that is
 * forgotten leaves its postcode for the next one. A signed-in account's
 * postcodes stay with its parcels and are never kept here.
 */
export const POSTCODES_STORAGE_KEY = 'sdt.postcodes.v1'; // gitleaks:allow -- public localStorage name
/** Enough for a home, a workplace and a few others. */
export const MAX_POSTCODES = 5;
const eventName = 'postcodes-change';

/** A postcode given to a carrier for an earlier parcel. */
export interface GivenPostcode {
  carrier: CarrierId;
  postcode?: string | null;
  /** The country it was given from; none for one this device kept before it kept countries. */
  country?: string | null;
}

/** A postcode given before, and the country it is from: the carrier's home where its lookup reads the postcode alone. */
export interface RememberedPostcode {
  postcode: string;
  country: string | null;
}

/** A postcode as carriers print it: trimmed, in capitals, with single spaces. */
const printed = (postcode: string) => postcode.trim().toUpperCase().replace(/\s+/g, ' ');

/**
 * The country a postcode was given from. One kept without it is from the home country of the carrier it was given to,
 * unless that carrier's lookup can read another country's: then it is not known.
 */
function givenFrom({ carrier, country }: GivenPostcode): string | null | undefined {
  if (country) return country;
  return mayReadPostcodeCountry(carrier) ? undefined : homePostcodeCountry(carrier);
}

/**
 * The postcode to suggest for a carrier's field, from earlier parcels newest
 * first: the last one given to the same carrier, otherwise the last one given
 * to any carrier that this field accepts. Most parcels go to the same address,
 * but a Swiss postcode never fills a field that wants five digits. A field that
 * reads the postcode's country takes one only from a country it knows (the
 * reader's choice once they make one), and the postcode brings its country.
 */
export function rememberedPostcode(
  carrier: CarrierId,
  trackingNumber: string,
  requirement: CarrierInputRequirement,
  given: readonly GivenPostcode[],
  chosenCountry?: string | null,
): RememberedPostcode | undefined {
  const readsCountry = readsPostcodeCountry(carrier, trackingNumber);
  const countries = postcodeCountries(carrier, trackingNumber);
  const accepted = given.flatMap((entry) => {
    const value = entry.postcode ? printed(entry.postcode) : '';
    const country = readsCountry ? givenFrom(entry) : homePostcodeCountry(carrier);
    if (!value || (readsCountry && (!country || !countries.includes(country) || (chosenCountry && country !== chosenCountry)))) return [];
    const field = postcodeFieldFor(requirement, carrier, trackingNumber, country);
    return requirementSatisfied(field, value) ? [{ to: entry.carrier, postcode: value, country }] : [];
  });
  const found = accepted.find(({ to }) => to === carrier) ?? accepted[0];
  return found && { postcode: found.postcode, country: found.country ?? null };
}

/**
 * A carrier's postcode field, the postcode it offers, and the country of the
 * postcode it starts from. What the reader typed stays, even emptied; until
 * then a required field starts from the postcode given before that fits, and
 * an optional one stays empty and only offers it.
 */
export function rememberedPostcodeField(
  carrier: CarrierId,
  trackingNumber: string,
  requirement: CarrierInputRequirement | undefined,
  typed: string | undefined,
  given: readonly GivenPostcode[],
  chosenCountry?: string | null,
): { value: string; offered?: RememberedPostcode; country?: string | null } {
  const remembered = requirement ? rememberedPostcode(carrier, trackingNumber, requirement, given, chosenCountry) : undefined;
  const filled = typed === undefined && !requirement?.optional ? remembered : undefined;
  const value = typed ?? filled?.postcode ?? '';
  return { value, offered: requirement?.optional && !value ? remembered : undefined, country: filled?.country };
}

interface Kept { carrier: CarrierId; postcode: string; country?: string }
const EMPTY: Kept[] = [];
// Storage that cannot be read or written leaves the memory working for as long as the page lives.
let memory: string | null = null;
let storageFailed = false;
let cached: { raw: string | null; list: Kept[] } = { raw: null, list: EMPTY };

function stored(): string | null {
  if (storageFailed) return memory;
  try { return window.localStorage.getItem(POSTCODES_STORAGE_KEY); } catch { return memory; }
}

function isKept(value: unknown): value is Kept {
  if (!value || typeof value !== 'object') return false;
  const { carrier, postcode, country } = value as Partial<Kept>;
  return typeof carrier === 'string' && Object.hasOwn(CARRIERS, carrier)
    && typeof postcode === 'string' && /^[A-Z0-9][A-Z0-9 -]{1,14}$/.test(postcode)
    && (country === undefined || (typeof country === 'string' && /^[A-Z]{2}$/.test(country)));
}

function read(): Kept[] {
  const raw = stored();
  if (raw === cached.raw) return cached.list;
  let list = EMPTY;
  try {
    const value: unknown = raw ? JSON.parse(raw) : null;
    if (Array.isArray(value)) list = value.filter(isKept).slice(0, MAX_POSTCODES);
  } catch { /* A damaged memory is an empty one. */ }
  cached = { raw, list };
  return list;
}

function write(list: Kept[]) {
  memory = list.length ? JSON.stringify(list.slice(0, MAX_POSTCODES)) : null;
  try {
    if (memory) window.localStorage.setItem(POSTCODES_STORAGE_KEY, memory);
    else window.localStorage.removeItem(POSTCODES_STORAGE_KEY);
    storageFailed = false;
  } catch {
    storageFailed = true;
  }
  window.dispatchEvent(new Event(eventName));
}

function subscribe(notify: () => void) {
  const onStorage = (event: StorageEvent) => {
    if (event.key !== null && event.key !== POSTCODES_STORAGE_KEY) return;
    // Another tab's memory replaces this page's copy.
    storageFailed = false;
    notify();
  };
  window.addEventListener(eventName, notify);
  window.addEventListener('storage', onStorage);
  return () => {
    window.removeEventListener(eventName, notify);
    window.removeEventListener('storage', onStorage);
  };
}

/** The postcodes this browser gave, newest first. */
export function usePostcodeMemory(): readonly GivenPostcode[] {
  return useSyncExternalStore(subscribe, read, () => EMPTY);
}

/**
 * Keeps a postcode a lookup without an account was given, with the country sent with it (none for the carrier's
 * home); giving it again moves it to the front.
 */
export function rememberPostcode(carrier: CarrierId, postcode: string, country?: string | null): void {
  const from = country || homePostcodeCountry(carrier);
  const kept: Kept = { carrier, postcode: printed(postcode), ...(from ? { country: from } : {}) };
  if (!isKept(kept)) return;
  write([kept, ...read().filter((entry) => entry.carrier !== carrier || entry.postcode !== kept.postcode)]);
}

/** Forgets a postcode, whichever carriers it was given to. */
export function forgetPostcode(postcode: string): void {
  const list = read();
  const forgotten = printed(postcode);
  if (list.some((entry) => entry.postcode === forgotten)) write(list.filter((entry) => entry.postcode !== forgotten));
}
