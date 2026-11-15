import type { DistanceUnit } from '../components/map/route';

/** What Settings keeps for the clock and for distances: a choice, or `auto` to follow the browser. */
export type ClockPreference = 'auto' | '24' | '12';
export type DistancePreference = 'auto' | DistanceUnit;

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

const MILE_REGIONS = new Set(['US', 'GB', 'LR', 'MM']);

/** Miles where a locale's region measures roads in them, kilometres everywhere else. */
export function localeDistanceUnit(locale: string | undefined): DistanceUnit {
  try {
    return MILE_REGIONS.has(new Intl.Locale(locale ?? '').maximize().region ?? '') ? 'mi' : 'km';
  } catch {
    return 'km';
  }
}

/** The time zones of those regions: the United States, the United Kingdom and its Crown dependencies, Liberia, Myanmar. */
const MILE_TIME_ZONE = new RegExp(
  '^(America/(New_York|Detroit|Chicago|Menominee|Denver|Boise|Phoenix|Los_Angeles|Anchorage|Juneau|Sitka|Metlakatla'
  + '|Yakutat|Nome|Adak|Indianapolis|Fort_Wayne|Knox_IN|Louisville|(Indiana|Kentucky|North_Dakota)/.+)'
  + '|Pacific/Honolulu|US/.+|Europe/(London|Belfast|Guernsey|Isle_of_Man|Jersey)|Africa/Monrovia|Asia/(Yangon|Rangoon))$',
);

/**
 * The units of the place a browser's time zone names, or of its language's region when the zone names no place (UTC).
 * The zone comes first: Chrome's English is en-US or en-GB wherever it runs, so its language alone says miles in Paris.
 */
export function browserDistanceUnit(locale: string | undefined, timeZone: string | undefined): DistanceUnit {
  if (timeZone?.includes('/') && !timeZone.startsWith('Etc/')) return MILE_TIME_ZONE.test(timeZone) ? 'mi' : 'km';
  return localeDistanceUnit(locale);
}
