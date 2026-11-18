import 'server-only';

import { createHash } from 'node:crypto';
import type { NumberInText } from '../peek/lookup/reading';
import { normalizeTrackingNumber } from '../lib/carriers';
import type { CarrierId, RecipientAction, Stage } from '../types';
import { isRecord, type JsonObject } from './types';

/**
 * Demo parcels for an assistant directory's reviewers. In the accounts `REVIEW_DEMO_ACCOUNTS` lists by id, a number
 * shaped `TEST` + a scenario + digits, such as TESTOUT01, is no carrier's: Peek never checks it, adding it charges no
 * allowance, and reading it gives the scenario's scans, timed back from the current hour so "arriving today" stays
 * true. Elsewhere such a number is any unknown number.
 */

const MINUTE = 60_000;
const HOUR = 60 * MINUTE;
const DAY = 24 * HOUR;
const ACCOUNT_ID = /^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$/;
const DEMO_NUMBER = /^TEST(TODAY|OUT|ABROAD|CUSTOMS|MISSED|PICKUP|DELIVERED|NOTFOUND|WEEK|NEW)(\d{2,6})$/;
/** A demo number as a text writes it, with an optional space or dash between its parts. */
const DEMO_IN_TEXT = /\bTEST[ -]?(?:TODAY|OUT|ABROAD|CUSTOMS|MISSED|PICKUP|DELIVERED|NOTFOUND|WEEK|NEW)[ -]?\d{2,6}\b/gi;

type Code = 'TODAY' | 'OUT' | 'ABROAD' | 'CUSTOMS' | 'MISSED' | 'PICKUP' | 'DELIVERED' | 'NOTFOUND' | 'WEEK' | 'NEW';

interface Scan {
  stage: Stage;
  description: string;
  location: string;
  /** How long before the current hour the carrier scanned it. */
  before: number;
  recipientAction?: RecipientAction;
}

interface Script {
  carrier: CarrierId;
  /** Oldest first. */
  scans: Scan[];
  /** Days from today, on the server's clock, as the assistants' `today` and `week` lists read them; null for none. */
  expectedIn: number | null;
  pickupPoint?: string;
  /** For a parcel no carrier has scanned yet: when it reads as added, before the current hour. */
  addedBefore?: number;
}

const scan = (stage: Stage, description: string, location: string, before: number, recipientAction?: RecipientAction): Scan => (
  { stage, description, location, before, ...recipientAction ? { recipientAction } : {} });

/** Each scenario's carrier and scans. `variant` is the number's digits: for DELIVERED, the days since delivery. */
const SCRIPTS: Record<Code, (variant: number) => Script> = {
  TODAY: () => ({
    carrier: 'dpd',
    expectedIn: 0,
    scans: [
      scan('registered', 'The sender has announced the parcel', 'Basel', 52 * HOUR),
      scan('accepted', 'Picked up from the sender', 'Basel', 28 * HOUR),
      scan('in_transit', 'Sorted at the parcel hub', 'Olten', 20 * HOUR),
      scan('in_transit', 'At the delivery depot: delivery planned today', 'Zürich', 3 * HOUR),
    ],
  }),
  OUT: () => ({
    carrier: 'swiss-post',
    expectedIn: 0,
    scans: [
      scan('registered', 'The sender has registered the parcel', 'Spreitenbach', 50 * HOUR),
      scan('accepted', 'Handed over to Swiss Post', 'Spreitenbach', 26 * HOUR),
      scan('in_transit', 'Sorted at the parcel centre', 'Härkingen', 22 * HOUR),
      scan('in_transit', 'Arrived at the delivery office', 'Zürich', 4 * HOUR),
      scan('out_for_delivery', 'Out for delivery', 'Zürich', HOUR),
    ],
  }),
  ABROAD: () => ({
    carrier: 'aliexpress',
    expectedIn: 4,
    scans: [
      scan('registered', 'Order information received', 'Shenzhen, China', 7 * DAY),
      scan('accepted', 'Picked up by the carrier', 'Shenzhen, China', 6 * DAY + 20 * HOUR),
      scan('in_transit', 'Arrived at the sorting centre', 'Shenzhen, China', 6 * DAY + 2 * HOUR),
      scan('in_transit', 'Cleared export customs', 'Hong Kong', 4 * DAY),
      scan('in_transit', 'Flight departed for the destination country', 'Hong Kong', 20 * HOUR),
    ],
  }),
  CUSTOMS: () => ({
    carrier: 'dhl-express',
    expectedIn: null,
    scans: [
      scan('registered', 'Shipment information received', 'London, United Kingdom', 4 * DAY),
      scan('accepted', 'Shipment picked up', 'London, United Kingdom', 3 * DAY + 18 * HOUR),
      scan('in_transit', 'Departed facility', 'London Heathrow, United Kingdom', 3 * DAY + 6 * HOUR),
      scan('in_transit', 'Arrived at DHL hub', 'Leipzig, Germany', 2 * DAY + 22 * HOUR),
      scan('in_transit', 'Arrived at the destination facility', 'Zürich, Switzerland', 2 * DAY + 4 * HOUR),
      scan('customs', 'Held at customs: import duties and taxes must be paid before delivery', 'Zürich, Switzerland', 6 * HOUR, 'duties'),
    ],
  }),
  MISSED: () => ({
    carrier: 'ups',
    expectedIn: 1,
    scans: [
      scan('registered', 'Shipper created a label', 'Geneva', 3 * DAY),
      scan('accepted', 'Picked up', 'Geneva', 2 * DAY + 6 * HOUR),
      scan('in_transit', 'Departed from facility', 'Lausanne', DAY + 20 * HOUR),
      scan('in_transit', 'Arrived at facility', 'Zürich', DAY + 8 * HOUR),
      scan('out_for_delivery', 'Out for delivery today', 'Zürich', 8 * HOUR),
      scan('failed_attempt', 'Nobody was available to receive the parcel. A second attempt is planned for the next business day.', 'Zürich', 3 * HOUR),
    ],
  }),
  PICKUP: () => ({
    carrier: 'swiss-post',
    expectedIn: null,
    pickupPoint: 'Zürich Sihlpost post office',
    scans: [
      scan('registered', 'The sender has registered the parcel', 'Bern', 3 * DAY),
      scan('accepted', 'Handed over to Swiss Post', 'Bern', 2 * DAY + 4 * HOUR),
      scan('in_transit', 'Sorted at the parcel centre', 'Frauenfeld', DAY + 22 * HOUR),
      scan('in_transit', 'Arrived at the delivery office', 'Zürich', DAY + 6 * HOUR),
      scan('ready_for_pickup', 'Ready for collection at the post office for 7 days', 'Zürich', 5 * HOUR),
    ],
  }),
  DELIVERED: (days) => {
    const at = Math.min(days, 30) * DAY + 2 * HOUR;
    return {
      carrier: 'planzer',
      expectedIn: -Math.min(days, 30),
      scans: [
        scan('registered', 'Order received from the sender', 'Dietikon', at + 48 * HOUR),
        scan('accepted', 'Collected from the sender', 'Dietikon', at + 22 * HOUR),
        scan('in_transit', 'At the distribution centre', 'Zürich', at + 16 * HOUR),
        scan('out_for_delivery', 'Out for delivery', 'Zürich', at + 4 * HOUR),
        scan('delivered', 'Delivered', 'Zürich', at),
      ],
    };
  },
  NOTFOUND: () => ({ carrier: 'dhl', expectedIn: null, scans: [], addedBefore: 3 * HOUR }),
  WEEK: () => ({
    carrier: 'dhl',
    expectedIn: 3,
    scans: [
      scan('registered', 'The shipment has been registered electronically', 'Hamburg, Germany', 2 * DAY),
      scan('accepted', 'The shipment has been picked up', 'Hamburg, Germany', DAY + 18 * HOUR),
      scan('in_transit', 'The shipment has been processed in the parcel centre', 'Hannover, Germany', DAY + 10 * HOUR),
      scan('in_transit', 'The shipment is on its way to the destination country', 'Singen, Germany', 6 * HOUR),
    ],
  }),
  NEW: () => ({
    carrier: 'dhl',
    expectedIn: 3,
    scans: [scan('registered', 'The shipment has been registered electronically', 'Hamburg, Germany', 2 * HOUR)],
  }),
};

let listed: { raw: string; ids: ReadonlySet<string> } | null = null;

/** The accounts `REVIEW_DEMO_ACCOUNTS` lists, by id; entries that aren't an account id are left out. */
export function reviewDemoAccounts(env: Partial<Record<string, string>> = process.env): ReadonlySet<string> {
  const raw = env.REVIEW_DEMO_ACCOUNTS ?? '';
  if (listed?.raw !== raw) {
    listed = { raw, ids: new Set(raw.split(',').map((entry) => entry.trim().toLowerCase()).filter((entry) => ACCOUNT_ID.test(entry))) };
  }
  return listed.ids;
}

/** The scenario a number plays in the account, or null when it is a real number or the account isn't listed. */
function demoScript(userId: unknown, trackingNumber: unknown): Script | null {
  if (typeof userId !== 'string' || typeof trackingNumber !== 'string') return null;
  const match = DEMO_NUMBER.exec(trackingNumber);
  if (!match || !reviewDemoAccounts().has(userId.toLowerCase())) return null;
  return SCRIPTS[match[1] as Code](Number(match[2]));
}

/** The carrier a demo number is filed under in the account, or null when it isn't one there. */
export function reviewDemoCarrier(userId: unknown, trackingNumber: unknown): CarrierId | null {
  return demoScript(userId, trackingNumber)?.carrier ?? null;
}

/** Whether a parcel row, with its `user_id`, is a demo parcel: no check ever asks anyone about it. */
export function isReviewDemoParcel(parcel: JsonObject): boolean {
  return demoScript(parcel.user_id, parcel.tracking_number) !== null;
}

/** The demo numbers a text names, when the account is listed, as the text reader gives numbers. */
export function reviewDemoNumbersInText(userId: string, text: string): NumberInText[] {
  if (!reviewDemoAccounts().has(userId.toLowerCase())) return [];
  const numbers = new Map<string, CarrierId>();
  for (const [written] of text.matchAll(DEMO_IN_TEXT)) {
    const normalized = normalizeTrackingNumber(written);
    const carrier = reviewDemoCarrier(userId, normalized);
    if (carrier && !numbers.has(normalized)) numbers.set(normalized, carrier);
  }
  return [...numbers].map(([normalized, carrier]) => ({
    normalized,
    match: { trackingNumber: normalized, carrier, confidence: 'high', candidates: [carrier], preferred: [], source: 'text' },
  }));
}

/**
 * The day a time falls on in Zürich, where the scenarios happen and where an account that chose no timezone reads
 * "today": on the server's clock, the parcel arriving today was yesterday's there for an hour each night.
 */
const DEMO_DAY = new Intl.DateTimeFormat('en-CA', { timeZone: 'Europe/Zurich', year: 'numeric', month: '2-digit', day: '2-digit' });

function dayOf(time: number): string {
  return DEMO_DAY.format(time);
}

/** A stable id shaped as the database's, so the apps read the scan as any other. */
function scanId(packageId: string, index: number): string {
  const hash = createHash('sha256').update(`review-demo:${packageId}:${index}`).digest('hex');
  return `${hash.slice(0, 8)}-${hash.slice(8, 12)}-4${hash.slice(13, 16)}-a${hash.slice(17, 20)}-${hash.slice(20, 32)}`;
}

/**
 * A parcel row as its owner reads it: a demo parcel in a listed account gets its scenario's scans, status line,
 * expected day and pickup point, timed back from the current hour; any other row is returned as it is.
 */
export function withReviewDemo(row: JsonObject, userId: string | null | undefined, now = Date.now()): JsonObject {
  const script = demoScript(userId, row.tracking_number);
  if (!script) return row;
  const id = String(row.id);
  const hour = Math.floor(now / HOUR) * HOUR;
  const iso = (time: number) => new Date(time).toISOString();
  const events = script.scans.map((step, index) => ({
    id: scanId(id, index),
    package_id: id,
    stage: step.stage,
    description: step.description,
    location: step.location,
    occurred_at: iso(hour - step.before),
    stored_at: iso(hour - step.before + 2 * MINUTE),
    provider_event_id: `review-demo:${index}`,
    recipient_action: step.recipientAction ?? null,
  }));
  const stored = isRecord(row.carrier_data) ? row.carrier_data : {};
  return {
    ...row,
    ...script.addedBefore ? { created_at: iso(hour - script.addedBefore) } : {},
    expected_delivery: script.expectedIn === null ? null : dayOf(now + script.expectedIn * DAY),
    last_status_text: script.scans.at(-1)?.description ?? null,
    last_synced_at: iso(Math.floor(now / (15 * MINUTE)) * 15 * MINUTE),
    sync_status: 'ok',
    sync_error: null,
    carrier_data: { ...stored, destination_country: 'CH', ...script.pickupPoint ? { pickup_point: script.pickupPoint } : {} },
    tracking_events: events,
  };
}
