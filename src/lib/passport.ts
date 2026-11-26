import type { ApiFriendStamp, ApiFriendStats } from '../generated/apiContract';
import { scanCountry } from './scanPlace';
import { collectedFromPickupPoint, currentStage } from './stages';
import type { ParcelWithEvents, Stage, TrackingEvent } from '../types';
import passportData from '../../shared/passport.json';

export { trackingLocationCountry as firstScanCountry } from 'universal-parcel-scraper/app';

/** A scan's country as evidence: its final field, a code that is also a state counting once the scan was placed in that country. */
const countryOf = (event: TrackingEvent) => scanCountry(event.location, event.place?.country);
/** Where a scan was, as the passport's countries count it: the country its words name, else the one the server placed it in. */
const passportCountryOf = (event: TrackingEvent) => countryOf(event) ?? event.place?.country ?? null;

const stageOrder: Stage[] = ['registered', 'accepted', 'in_transit', 'customs', 'exception', 'out_for_delivery', 'failed_attempt', 'ready_for_pickup', 'delivered', 'returned'];
/** Carrier ids that stand for "not known yet" rather than a carrier: they never count toward Well connected. */
const PLACEHOLDER_CARRIERS = new Set(['unknown', 'intl-post']);

export type Continent = keyof typeof passportData.outlines;
export type StampTone = 'green' | 'lilac' | 'blue' | 'peach' | 'ochre' | 'rose';
export type StampKey = typeof passportData.stamps[number]['key'];
export interface StampDefinition { key: StampKey; tone: StampTone; glyph?: string; numeral?: string; total: number }
/** Every stamp in page order: the reachable ones first, so a new passport's next three are within reach. */
export const STAMPS = passportData.stamps as StampDefinition[];
export const STAMP_GLYPHS: Record<string, string> = passportData.glyphs;
const countryTable = passportData.countries as unknown as Record<string, [Continent, StampTone, number]>;
/** A country's continent, which gives its stamp's outline, and its ink and tilt: fixed per code, so a stamp never changes colour or jumps. */
export function countryInk(code: string): { continent: Continent; tone: StampTone; tilt: number } {
  const [continent, tone, tilt] = countryTable[code] ?? ['europe', 'blue', -3];
  return { continent, tone, tilt };
}
export const continentOf = (code: string) => countryInk(code).continent;

export interface DeliveryRecord { parcelId: string; label: string; duration: number; deliveredAt: number }

/** What one delivered parcel brings to the passport, in the order its scans happened. */
export interface PassportDelivery {
  parcelId: string;
  label: string;
  carrier: string;
  deliveredAt: number;
  /** Its delivery's day on the reader's calendar, YYYY-MM-DD. */
  day: string;
  december: boolean;
  pickup: boolean;
  crossBorder: boolean;
  crossContinent: boolean;
  /** A delivery came after a missed attempt. */
  secondTry: boolean;
  /** Its quarter of the year by month: 0 for December to February, 1 for March to May, and so on. */
  season: number;
  domestic: boolean;
  longWait: boolean;
  /** From its first physical scan to its delivery, when both are known. */
  duration: number | null;
  /** Each country it was scanned in, with the first instant it was there. */
  countries: Map<string, number>;
}

/** Match iOS: the clock starts at a physical scan, never when a parcel is added. */
export function passportStatistics(parcels: readonly ParcelWithEvents[], timeZone = Intl.DateTimeFormat().resolvedOptions().timeZone) {
  let deliveredCount = 0;
  let activeCount = 0;
  const records: DeliveryRecord[] = [];
  const deliveries: PassportDelivery[] = [];
  const countries = new Map<string, number>();
  const deliveredCarriers = new Set<string>();
  const deliveredDays = new Map<string, number>();
  const calendar = new Intl.DateTimeFormat('en-US', { timeZone, calendar: 'gregory', year: 'numeric', month: '2-digit', day: '2-digit' });
  let crossBorderCount = 0, domesticDeliveryCount = 0, longWaitDeliveryCount = 0, pickupDeliveryCount = 0, decemberDeliveryCount = 0;
  for (const parcel of parcels) {
    const meaningful = parcel.events.filter((event) => event.stage !== 'pending');
    const dated = meaningful.map((event) => ({ event, date: Date.parse(event.occurredAt) }))
      .filter(({ date }) => Number.isFinite(date))
      .sort((a, b) => a.date - b.date || stageOrder.indexOf(a.event.stage) - stageOrder.indexOf(b.event.stage) || a.event.id.localeCompare(b.event.id));
    const datesAreComplete = dated.length === meaningful.length;
    const stage = datesAreComplete ? dated.at(-1)?.event.stage : currentStage(parcel.events, parcel.returnHeld);
    if (stage === 'delivered') {
      deliveredCount += 1;
      if (!PLACEHOLDER_CARRIERS.has(parcel.carrier)) deliveredCarriers.add(parcel.carrier);
    }
    if (!parcel.archivedAt && stage !== 'delivered' && stage !== 'returned') activeCount += 1;
    const physical = dated.filter(({ event }) => event.stage !== 'registered');
    const first = physical[0];
    const completion = physical.find(({ event }) => event.stage === 'delivered');
    let delivery: PassportDelivery | null = null;
    if (datesAreComplete && stage === 'delivered' && completion) {
      // One completion per parcel, even when the carrier repeats its delivery scan.
      const parts = calendar.formatToParts(completion.date);
      const part = (type: string) => parts.find((value) => value.type === type)?.value ?? '';
      const day = `${part('year')}-${part('month')}-${part('day')}`;
      deliveredDays.set(day, (deliveredDays.get(day) ?? 0) + 1);
      const month = Number(part('month'));
      const december = month === 12;
      if (december) decemberDeliveryCount += 1;
      // Collected where it waited: its last movement before the delivery made it ready for pickup, not a trip to the door after.
      const pickup = Boolean(collectedFromPickupPoint(physical.filter(({ date }) => date < completion.date).map(({ event }) => event)));
      if (pickup) pickupDeliveryCount += 1;
      const located = physical.filter(({ date }) => date <= completion.date).map(({ event, date }) => ({ country: passportCountryOf(event), date }))
        .filter((scan): scan is { country: string; date: number } => Boolean(scan.country));
      // Different countries must be observed at different instants; tied scans do not prove travel.
      const travelled = (apart: (a: string, b: string) => boolean) => located.some((scan, index) => located.slice(0, index).some((prior) => prior.date < scan.date && apart(prior.country, scan.country)));
      const crossBorder = travelled((a, b) => a !== b);
      if (crossBorder) crossBorderCount += 1;
      const visited = new Map<string, number>();
      for (const scan of located) if (!visited.has(scan.country)) visited.set(scan.country, scan.date);
      delivery = { parcelId: parcel.id, label: parcel.label, carrier: parcel.carrier, deliveredAt: completion.date, day, december, pickup, crossBorder,
        crossContinent: travelled((a, b) => continentOf(a) !== continentOf(b)),
        secondTry: physical.some(({ event, date }) => event.stage === 'failed_attempt' && date < completion.date),
        season: Math.floor((month % 12) / 3), domestic: false, longWait: false, duration: null, countries: visited };
      deliveries.push(delivery);
    }
    if (!datesAreComplete || !first || !['accepted', 'in_transit'].includes(first.event.stage)) continue;
    const country = countryOf(first.event);
    if (country) countries.set(country, (countries.get(country) ?? 0) + 1);
    if (stage !== 'delivered' || !completion || completion.date <= first.date) continue;
    const origin = passportCountryOf(first.event);
    if (origin && origin === passportCountryOf(completion.event)) {
      domesticDeliveryCount += 1;
      if (delivery) delivery.domestic = true;
    }
    if (completion.date - first.date > 30 * 86400_000) {
      longWaitDeliveryCount += 1;
      if (delivery) delivery.longWait = true;
    }
    if (delivery) delivery.duration = completion.date - first.date;
    records.push({ parcelId: parcel.id, label: parcel.label, duration: completion.date - first.date, deliveredAt: completion.date });
  }
  records.sort((a, b) => a.duration - b.duration || a.parcelId.localeCompare(b.parcelId));
  deliveries.sort((a, b) => a.deliveredAt - b.deliveredAt || a.parcelId.localeCompare(b.parcelId));
  return {
    crossBorderCount, domesticDeliveryCount, longWaitDeliveryCount, pickupDeliveryCount, decemberDeliveryCount,
    maxDeliveriesInOneDay: Math.max(0, ...deliveredDays.values()),
    deliveredCount, activeCount, carrierCount: deliveredCarriers.size,
    durationSampleCount: records.length,
    averageDeliveryDuration: records.length ? records.reduce((sum, record) => sum + record.duration, 0) / records.length : null,
    fastestDelivery: records[0] ?? null,
    originCountries: [...countries].map(([code, count]) => ({ code, count })).sort((a, b) => b.count - a.count || a.code.localeCompare(b.code)),
    deliveries,
  };
}

export type PassportStatistics = ReturnType<typeof passportStatistics>;

/** A stamp as the passport shows it: how far the reader is, and once earned, the day and the parcel that earned it. */
export interface StampProgress extends StampDefinition {
  count: number;
  earned: boolean;
  /** When the delivery that crossed its rule arrived; null for an earned stamp whose deliveries are not all dated. */
  earnedAt: number | null;
  parcelId: string | null;
  label: string | null;
}

/** A country's ink stamp: the first instant a delivered parcel of the reader's was scanned there, and how many went through it. */
export interface CountryStamp {
  code: string;
  /** Its running number in the order the countries were reached, from 1. */
  number: number;
  firstAt: number;
  parcelId: string;
  label: string;
  count: number;
  continent: Continent;
}

const DAY = 86400_000;

/**
 * The whole passport: today's numbers, every stamp with its progress and the day it was earned, and the countries. A
 * stamp's day is the delivery that crossed its rule, replaying the deliveries in the order they arrived, so deleting a
 * parcel can move or remove it.
 */
export function passportBook(parcels: readonly ParcelWithEvents[], timeZone?: string) {
  const stats = passportStatistics(parcels, timeZone);
  const earned = new Map<StampKey, PassportDelivery>();
  const earn = (key: StampKey, delivery: PassportDelivery, when = true) => { if (when && !earned.has(key)) earned.set(key, delivery); };
  const carriers = new Set<string>();
  const reached = new Map<string, { firstAt: number; delivery: PassportDelivery; count: number }>();
  const days = new Map<string, number>();
  const byCarrier = new Map<string, number>();
  const seasons = new Set<number>();
  const firstDelivery = stats.deliveries[0]?.deliveredAt ?? 0;
  stats.deliveries.forEach((delivery, index) => {
    const n = index + 1;
    earn('first', delivery); earn('ten', delivery, n >= 10); earn('theRegular', delivery, n >= 25); earn('fifty', delivery, n >= 50);
    if (!PLACEHOLDER_CARRIERS.has(delivery.carrier)) {
      carriers.add(delivery.carrier);
      byCarrier.set(delivery.carrier, (byCarrier.get(delivery.carrier) ?? 0) + 1);
    }
    earn('connected', delivery, carriers.size >= 3);
    earn('familiarFace', delivery, (byCarrier.get(delivery.carrier) ?? 0) >= 3);
    earn('secondTry', delivery, delivery.secondTry);
    seasons.add(delivery.season);
    earn('fourSeasons', delivery, seasons.size >= 4);
    earn('firstAnniversary', delivery, delivery.deliveredAt - firstDelivery >= 365 * DAY);
    earn('express', delivery, delivery.duration !== null && delivery.duration <= 2 * DAY);
    earn('acrossBorders', delivery, delivery.crossBorder);
    earn('newHorizons', delivery, delivery.crossContinent);
    for (const [code, at] of delivery.countries) {
      const known = reached.get(code);
      if (!known) reached.set(code, { firstAt: at, delivery, count: 1 });
      else {
        known.count += 1;
        if (at < known.firstAt) Object.assign(known, { firstAt: at, delivery });
      }
    }
    earn('aroundWorld', delivery, reached.size >= 5);
    earn('tenCountries', delivery, reached.size >= 10);
    earn('rightNextDoor', delivery, delivery.domestic);
    earn('worthTheWait', delivery, delivery.longWait);
    days.set(delivery.day, (days.get(delivery.day) ?? 0) + 1);
    earn('busyDoorstep', delivery, (days.get(delivery.day) ?? 0) >= 3);
    earn('pickedUp', delivery, delivery.pickup);
    earn('homeForHolidays', delivery, delivery.december);
  });
  const fastest = stats.fastestDelivery;
  const counts: Record<StampKey, number> = {
    first: stats.deliveredCount, ten: stats.deliveredCount, theRegular: stats.deliveredCount, fifty: stats.deliveredCount,
    connected: stats.carrierCount, express: fastest && fastest.duration <= 2 * DAY ? 1 : 0, acrossBorders: stats.crossBorderCount,
    newHorizons: stats.deliveries.filter((delivery) => delivery.crossContinent).length,
    aroundWorld: reached.size, tenCountries: reached.size, rightNextDoor: stats.domesticDeliveryCount,
    worthTheWait: stats.longWaitDeliveryCount, busyDoorstep: stats.maxDeliveriesInOneDay, pickedUp: stats.pickupDeliveryCount,
    homeForHolidays: stats.decemberDeliveryCount,
    familiarFace: Math.max(0, ...byCarrier.values()), secondTry: stats.deliveries.filter((delivery) => delivery.secondTry).length,
    fourSeasons: seasons.size,
    firstAnniversary: earned.has('firstAnniversary') ? 12 : Math.min(11, Math.floor(((stats.deliveries.at(-1)?.deliveredAt ?? firstDelivery) - firstDelivery) / (30.4375 * DAY))),
  };
  const stamps: StampProgress[] = STAMPS.map((stamp) => {
    const count = counts[stamp.key];
    const delivery = count >= stamp.total ? earned.get(stamp.key) : undefined;
    return { ...stamp, count, earned: count >= stamp.total, earnedAt: delivery?.deliveredAt ?? null, parcelId: delivery?.parcelId ?? null, label: delivery?.label ?? null };
  });
  const countries: CountryStamp[] = [...reached].sort(([a, x], [b, y]) => x.firstAt - y.firstAt || a.localeCompare(b))
    .map(([code, { firstAt, delivery, count }], index) => ({ code, number: index + 1, firstAt, parcelId: delivery.parcelId, label: delivery.label, count, continent: continentOf(code) }));
  return { stats, stamps, countries };
}

export type PassportBook = ReturnType<typeof passportBook>;

/**
 * "Your <year> in parcels": the year's deliveries on the reader's calendar, the countries they went through (the six
 * with the most of them, in the passport's order, when there are more), the stamps they earned and the fastest one.
 * Aggregates only: no parcel, shop, carrier or place below the country.
 */
export function yearInParcels(book: PassportBook, year: number) {
  const deliveries = book.stats.deliveries.filter((delivery) => delivery.day.startsWith(`${year}-`));
  const parcels = new Set(deliveries.map((delivery) => delivery.parcelId));
  const counts = new Map<string, number>();
  // A country's day on the card is the year's first delivery through it, never an earlier year's.
  const firsts = new Map<string, number>();
  for (const delivery of deliveries) {
    for (const code of delivery.countries.keys()) {
      counts.set(code, (counts.get(code) ?? 0) + 1);
      firsts.set(code, Math.min(firsts.get(code) ?? Infinity, delivery.deliveredAt));
    }
  }
  const countries = book.countries.filter((country) => counts.has(country.code))
    .map((country) => ({ ...country, firstAt: firsts.get(country.code)!, count: counts.get(country.code)! }));
  const shown = countries.length <= 6 ? countries
    : [...countries].sort((a, b) => b.count - a.count || a.number - b.number).slice(0, 6).sort((a, b) => a.number - b.number);
  const durations = deliveries.flatMap((delivery) => delivery.duration === null ? [] : [delivery.duration]);
  return {
    year,
    deliveredCount: deliveries.length,
    countryCount: countries.length,
    countries: shown,
    stamps: book.stamps.filter((stamp) => stamp.earned && stamp.parcelId !== null && parcels.has(stamp.parcelId)),
    fastest: durations.length ? Math.min(...durations) : null,
  };
}

export type YearInParcels = ReturnType<typeof yearInParcels>;

/** The keys a reader marks as seen: a stamp by its key, a country by its code. */
export const stampSeenKey = (stamp: Pick<StampProgress, 'key'>) => `stamp:${stamp.key}`;
export const countrySeenKey = (country: Pick<CountryStamp, 'code'>) => `country:${country.code}`;

/** What each delivered parcel earned: its stamps and its countries, by parcel. */
export function earnedByParcel(book: PassportBook) {
  const byParcel = new Map<string, Array<{ seen: string; stamp?: StampProgress; country?: CountryStamp }>>();
  const add = (parcelId: string | null, item: { seen: string; stamp?: StampProgress; country?: CountryStamp }) => {
    if (!parcelId) return;
    byParcel.set(parcelId, [...(byParcel.get(parcelId) ?? []), item]);
  };
  for (const country of book.countries) add(country.parcelId, { seen: countrySeenKey(country), country });
  for (const stamp of book.stamps) if (stamp.earned) add(stamp.parcelId, { seen: stampSeenKey(stamp), stamp });
  return byParcel;
}

/** The stamps Friends shares, in the order the apps before the new stamps know them. */
const SHARED_STAMPS: ApiFriendStamp[] = ['first', 'ten', 'connected', 'express', 'acrossBorders', 'aroundWorld', 'theRegular', 'rightNextDoor', 'worthTheWait', 'busyDoorstep', 'pickedUp', 'homeForHolidays'];

/** What Friends shows of a Passport: its arrivals, the average journey in whole days and its stamps, the calendar ones in UTC. */
export function sharedPassport(parcels: readonly ParcelWithEvents[]): ApiFriendStats {
  const { stats, stamps } = passportBook(parcels, 'UTC');
  const earned = new Set(stamps.filter((stamp) => stamp.earned).map((stamp) => stamp.key as string));
  return {
    deliveredCount: stats.deliveredCount,
    averageDays: stats.averageDeliveryDuration == null ? null : Math.max(1, Math.ceil(stats.averageDeliveryDuration / 86400_000)),
    stamps: SHARED_STAMPS.filter((key) => earned.has(key)),
  };
}

export function formatJourneyDuration(duration: number, languageTag: string) {
  const totalMinutes = Math.max(1, Math.floor(duration / 60_000));
  const days = Math.floor(totalMinutes / 1440);
  const hours = Math.floor((totalMinutes % 1440) / 60);
  const minutes = totalMinutes % 60;
  const unit = (value: number, name: 'day' | 'hour' | 'minute') => new Intl.NumberFormat(languageTag, { style: 'unit', unit: name, unitDisplay: 'narrow' }).format(value);
  return days ? [unit(days, 'day'), hours ? unit(hours, 'hour') : ''].filter(Boolean).join(' ')
    : hours ? [unit(hours, 'hour'), minutes ? unit(minutes, 'minute') : ''].filter(Boolean).join(' ')
      : unit(totalMinutes, 'minute');
}
