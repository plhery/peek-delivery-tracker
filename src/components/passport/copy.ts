import { dateShowsYear } from '../../lib/format';
import type { StampKey, StampProgress } from '../../lib/passport';
import type { MessageKey, Translate } from '../../i18n';

/** Each stamp's name and what earns it. */
export const STAMP_COPY: Record<StampKey, { title: MessageKey; explanation: MessageKey }> = {
  first: { title: 'passport.firstArrival', explanation: 'passport.firstExplanation' },
  rightNextDoor: { title: 'passport.rightNextDoor', explanation: 'passport.domesticExplanation' },
  express: { title: 'passport.expressArrival', explanation: 'passport.expressExplanation' },
  familiarFace: { title: 'passport.familiarFace', explanation: 'passport.familiarExplanation' },
  pickedUp: { title: 'passport.pickedUp', explanation: 'passport.pickupExplanation' },
  acrossBorders: { title: 'passport.acrossBorders', explanation: 'passport.acrossExplanation' },
  connected: { title: 'passport.wellConnected', explanation: 'passport.carrierExplanation' },
  secondTry: { title: 'passport.secondTry', explanation: 'passport.secondTryExplanation' },
  worthTheWait: { title: 'passport.worthTheWait', explanation: 'passport.waitExplanation' },
  aroundWorld: { title: 'passport.aroundWorld', explanation: 'passport.aroundExplanation' },
  newHorizons: { title: 'passport.newHorizons', explanation: 'passport.horizonsExplanation' },
  ten: { title: 'passport.doubleDigits', explanation: 'passport.tenExplanation' },
  fourSeasons: { title: 'passport.fourSeasons', explanation: 'passport.seasonsExplanation' },
  theRegular: { title: 'passport.theRegular', explanation: 'passport.regularExplanation' },
  busyDoorstep: { title: 'passport.busyDoorstep', explanation: 'passport.busyExplanation' },
  homeForHolidays: { title: 'passport.homeForHolidays', explanation: 'passport.holidayExplanation' },
  tenCountries: { title: 'passport.tenCountries', explanation: 'passport.tenCountriesExplanation' },
  firstAnniversary: { title: 'passport.firstAnniversary', explanation: 'passport.anniversaryExplanation' },
  fifty: { title: 'passport.fifty', explanation: 'passport.fiftyExplanation' },
};

/** The day a stamp was earned, as it is written under it: "12 Aug", with the year when it is not this one's. */
export function stampDay(at: number, languageTag: string, now = Date.now()): string {
  const date = new Date(at);
  return new Intl.DateTimeFormat(languageTag, { day: 'numeric', month: 'short', ...(dateShowsYear(date, now) && { year: 'numeric' }) }).format(date);
}

/** The day in full, for the stamp's details and a screen reader: "12 August 2026". */
export function stampFullDay(at: number, languageTag: string): string {
  return new Intl.DateTimeFormat(languageTag, { day: 'numeric', month: 'long', year: 'numeric' }).format(new Date(at));
}

/** How far an unearned stamp has come: "3 of 5", or "Not yet" for one a single parcel earns. */
export function stampProgress(stamp: StampProgress, t: Translate): string {
  return stamp.total > 1 ? t('passport.progress', { count: Math.min(stamp.count, stamp.total), total: stamp.total }) : t('passport.notYet');
}
