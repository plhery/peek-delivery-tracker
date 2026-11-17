import type { DistanceUnit } from '../components/map/route';

/** What Settings keeps for the clock and for distances: a choice, or `auto` to follow the browser. */
export type ClockPreference = 'auto' | '24' | '12';
export type DistancePreference = 'auto' | DistanceUnit;
export type WeightUnit = 'kg' | 'lb';

/** A saved clock choice; anything else, or nothing, follows the browser. */
export function clockPreference(saved: string | null): ClockPreference {
  return saved === '24' || saved === '12' ? saved : 'auto';
}

/** A saved choice of units; anything else, or nothing, follows the browser. */
export function distancePreference(saved: string | null): DistancePreference {
  return saved === 'km' || saved === 'mi' ? saved : 'auto';
}

/** Whether a locale's clock runs to 24 hours: the browser's own locale when none is named. */
export function localeUses24HourTime(locale?: string): boolean {
  const cycle = new Intl.DateTimeFormat(locale, { hour: 'numeric' }).resolvedOptions().hourCycle;
  return cycle === 'h23' || cycle === 'h24';
}

/** The regions that measure in US units: the United States, Liberia and Myanmar. The UK writes miles but kilograms. */
const US_UNIT_REGIONS = new Set(['US', 'LR', 'MM']);
const MILE_REGIONS = new Set([...US_UNIT_REGIONS, 'GB']);

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

/** The time zones of the regions that measure in US units; with the United Kingdom's and its Crown dependencies', those that write miles. */
const US_UNIT_ZONES = '(America/(New_York|Detroit|Chicago|Menominee|Denver|Boise|Phoenix|Los_Angeles|Anchorage|Juneau|Sitka|Metlakatla'
  + '|Yakutat|Nome|Adak|Indianapolis|Fort_Wayne|Knox_IN|Louisville|(Indiana|Kentucky|North_Dakota)/.+)'
  + '|Pacific/Honolulu|US/.+|Africa/Monrovia|Asia/(Yangon|Rangoon))';
const MILE_TIME_ZONE = new RegExp(`^(${US_UNIT_ZONES}|Europe/(London|Belfast|Guernsey|Isle_of_Man|Jersey))$`);
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

/**
 * The unit weights are written in: the one that goes with the distance units chosen in Settings, kilograms with
 * kilometres and pounds with miles, else the browser's.
 */
export function resolvedWeightUnit(distance: DistancePreference, automatic: WeightUnit): WeightUnit {
  return distance === 'km' ? 'kg' : distance === 'mi' ? 'lb' : automatic;
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
