import type { DistanceUnit } from '../components/map/route';

/** What Settings keeps for the clock, distances and weights: a choice, or `auto` to follow the browser. */
export type ClockPreference = 'auto' | '24' | '12';
export type DistancePreference = 'auto' | DistanceUnit;
export type WeightUnit = 'kg' | 'lb';
export type WeightPreference = 'auto' | WeightUnit;

/** A saved clock choice; anything else, or nothing, follows the browser. */
export function clockPreference(saved: string | null): ClockPreference {
  return saved === '24' || saved === '12' ? saved : 'auto';
}

/** A saved choice of units; anything else, or nothing, follows the browser. */
export function distancePreference(saved: string | null): DistancePreference {
  return saved === 'km' || saved === 'mi' ? saved : 'auto';
}

/** A saved choice of weight unit; anything else, or nothing, follows the browser. */
export function weightPreference(saved: string | null): WeightPreference {
  return saved === 'kg' || saved === 'lb' ? saved : 'auto';
}

/**
 * The weight unit for a reader who chose none yet: the one their distance units brought before weights had a choice of
 * their own, kilograms with kilometres and pounds with miles.
 */
export function weightFromDistance(distance: DistancePreference): WeightPreference {
  return distance === 'km' ? 'kg' : distance === 'mi' ? 'lb' : 'auto';
}

/** Whether a locale's clock runs to 24 hours: the browser's own locale when none is named. */
export function localeUses24HourTime(locale?: string): boolean {
  const cycle = new Intl.DateTimeFormat(locale, { hour: 'numeric' }).resolvedOptions().hourCycle;
  return cycle === 'h23' || cycle === 'h24';
}

/**
 * The regions that measure in US units: the United States and Liberia. The UK and Myanmar write miles but kilograms,
 * as Apple's and ICU's data say.
 */
const US_UNIT_REGIONS = new Set(['US', 'LR']);
const MILE_REGIONS = new Set([...US_UNIT_REGIONS, 'GB', 'MM']);

function localeRegion(locale: string | undefined): string {
  try {
    return new Intl.Locale(locale ?? '').maximize().region ?? '';
  } catch {
    return '';
  }
}

/** Miles where a locale's region measures roads in them, kilometres everywhere else. */
export function localeDistanceUnit(locale: string | undefined): DistanceUnit {
  return MILE_REGIONS.has(localeRegion(locale)) ? 'mi' : 'km';
}

/** Pounds where a locale's region measures in US units, kilograms everywhere else. */
export function localeWeightUnit(locale: string | undefined): WeightUnit {
  return US_UNIT_REGIONS.has(localeRegion(locale)) ? 'lb' : 'kg';
}

/** The United States' time zones. */
const US_ZONES = '(America/(New_York|Detroit|Chicago|Menominee|Denver|Boise|Phoenix|Los_Angeles|Anchorage|Juneau|Sitka|Metlakatla'
  + '|Yakutat|Nome|Adak|Indianapolis|Fort_Wayne|Knox_IN|Louisville|(Indiana|Kentucky|North_Dakota)/.+)'
  + '|Pacific/Honolulu|US/.+)';
export const US_TIME_ZONE = new RegExp(`^${US_ZONES}$`);
/**
 * The time zones of the regions that measure in US units; with the United Kingdom's, its Crown dependencies' and
 * Myanmar's, those that write miles.
 */
const US_UNIT_ZONES = `(${US_ZONES}|Africa/Monrovia)`;
const MILE_TIME_ZONE = new RegExp(`^(${US_UNIT_ZONES}|Europe/(London|Belfast|Guernsey|Isle_of_Man|Jersey)|Asia/(Yangon|Rangoon))$`);
const US_UNIT_TIME_ZONE = new RegExp(`^${US_UNIT_ZONES}$`);

/** Whether a time zone names a place, rather than UTC or an offset. */
const placeZone = (timeZone: string | undefined): timeZone is string => Boolean(timeZone?.includes('/') && !timeZone.startsWith('Etc/'));

/**
 * The units of the place a browser's time zone names, or of its language's region when the zone names no place (UTC).
 * The zone comes first: Chrome's English is en-US or en-GB wherever it runs, so its language alone says miles in Paris.
 */
export function browserDistanceUnit(locale: string | undefined, timeZone: string | undefined): DistanceUnit {
  if (placeZone(timeZone)) return MILE_TIME_ZONE.test(timeZone) ? 'mi' : 'km';
  return localeDistanceUnit(locale);
}

/** The weight unit of the place a browser's time zone names, or of its language's region when the zone names none. */
export function browserWeightUnit(locale: string | undefined, timeZone: string | undefined): WeightUnit {
  if (placeZone(timeZone)) return US_UNIT_TIME_ZONE.test(timeZone) ? 'lb' : 'kg';
  return localeWeightUnit(locale);
}

const KILOGRAMS_PER_POUND = 0.45359237;

/**
 * A weight Peek read as a number of kilograms, in `unit` and the language's digits: kilograms to three decimals,
 * pounds to one, or to three under a tenth of a pound. The iPhone and Android apps write it the same way.
 */
export function formatWeight(kilograms: number, unit: WeightUnit, languageTag: string): string {
  if (unit === 'kg') return new Intl.NumberFormat(languageTag, { style: 'unit', unit: 'kilogram', maximumFractionDigits: 3 }).format(kilograms);
  const pounds = kilograms / KILOGRAMS_PER_POUND;
  return new Intl.NumberFormat(languageTag, { style: 'unit', unit: 'pound', maximumFractionDigits: pounds < 0.1 ? 3 : 1 }).format(pounds);
}

const CENTIMETRES_PER_INCH = 2.54;
/** Two or three sides in centimetres, as the carriers' adapters write them: "40 × 30 × 20 cm", "40 x 30.5 cm". */
const SIDES_IN_CENTIMETRES = /^(\d+(?:\.\d+)?)\s*[x×]\s*(\d+(?:\.\d+)?)(?:\s*[x×]\s*(\d+(?:\.\d+)?))?\s*cm$/i;

/**
 * A parcel's dimensions as its carrier wrote them, in inches where weights are written in pounds: two or three sides
 * in centimetres, each to one decimal in the language's digits. Any other text is shown as written. The iPhone and
 * Android apps write it the same way.
 */
export function formatDimensions(text: string, unit: WeightUnit, languageTag: string): string {
  const written = text.trim();
  const sides = unit === 'lb' ? SIDES_IN_CENTIMETRES.exec(written) : null;
  if (!sides) return written;
  const inches = new Intl.NumberFormat(languageTag, { maximumFractionDigits: 1 });
  return `${sides.slice(1).filter(Boolean).map((side) => inches.format(Number(side) / CENTIMETRES_PER_INCH)).join(' × ')} in`;
}
