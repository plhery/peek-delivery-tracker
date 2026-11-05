import { trackingLocationCountry, trackingPlace, type TrackingPlace } from 'universal-parcel-scraper/app';
import type { Locale } from './locale';
import type { EventPlace } from '../types';

/** A final two-letter field, as in "Haiger, DE" or "Haiger (DE)". */
const FINAL_CODE = /^(.*\S)\s*[,;|(]\s*([A-Z]{2})\)?$/;

/**
 * The final code of a location, with what comes before it, when it is the country the server placed the scan in. A
 * code that is also a state, province or canton ("Haiger, DE", "Wilmington, DE", "Toronto, CA", "Los Angeles, CA")
 * is not read as a country on its own; where the scan was placed settles it.
 */
function placedCode(location: string, placed: string | null | undefined): { country: string; place: string } | null {
  const code = placed ? FINAL_CODE.exec(location.trim()) : null;
  return code && code[2] === placed ? { country: placed, place: code[1] } : null;
}

const EAST_ASIAN = /[\p{Script=Han}\p{Script=Hiragana}\p{Script=Katakana}\p{Script=Hangul}]/u;
const KANA_OR_HANGUL = /[\p{Script=Hiragana}\p{Script=Katakana}\p{Script=Hangul}]/u;

/**
 * Whether a location is shown by its town's romanised name rather than as written: it is in Chinese, Japanese or
 * Korean script, which a Chinese reader keeps when it is Chinese characters alone. The stored scan keeps its words.
 */
export function romanisesPlace(location: string, locale?: Locale): boolean {
  return EAST_ASIAN.test(location) && !(locale === 'zh' && !KANA_OR_HANGUL.test(location));
}

/**
 * A scan's location for display: the country it names, and the rest of it. One written in a script the reader may
 * not read (`romanisesPlace`) is named by the town the server placed it in ("深圳市" is Shenzhen), or by the country
 * alone where the server placed it no closer, and kept as written where it placed it nowhere.
 */
export function scanPlace(location: string, placed?: Pick<EventPlace, 'country' | 'name' | 'precision'> | null, locale?: Locale): TrackingPlace {
  if (placed && romanisesPlace(location, locale)) return { country: placed.country, place: placed.precision === 'city' ? placed.name : '' };
  const written = trackingPlace(location);
  return written.country ? written : placedCode(location, placed?.country) ?? written;
}

/** The country a scan's location names in its final field, as the passport counts it: never one guessed from a town. */
export function scanCountry(location: string | undefined, placed?: string | null): string | null {
  return trackingLocationCountry(location) ?? (location ? placedCode(location, placed)?.country ?? null : null);
}
