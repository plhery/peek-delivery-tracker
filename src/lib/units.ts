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
