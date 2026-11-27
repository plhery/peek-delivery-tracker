import 'server-only';

import { DateTime } from 'luxon';
import { CARRIER_DEFINITIONS, type CarrierId } from 'universal-parcel-scraper';
import type { ApiTrackingEventRow } from '../generated/apiContract';
import { currentEvent } from '../lib/stages';
import { toEvent } from '../store/apiRepo';
import { deliveryDayEnd } from './trackingSync';
import { isRecord, type JsonObject } from './types';

/**
 * Carriers whose "out for delivery", as they or a relay copy it, is the destination post's round with that post's
 * attempts and pickup notices left out: the parcel often comes days later with no scan between. Such a round ends
 * that many days after its own day, unless the destination post's own scan took the parcel out.
 */
const RELAYED_ROUND_DAYS: Readonly<Partial<Record<string, number>>> = { 'china-post': 3 };

/**
 * When the round a parcel went out on is over (`delivery_round_ends_at`): the end of the day of its newest scan, out
 * for delivery, on the clock of the carrier delivering it (`deliveryDayEnd`), or for a carrier that relays the
 * destination post's round (`RELAYED_ROUND_DAYS`) some days later; and when that day is over (`delivery_round_over_at`).
 * The apps say the parcel has had no tracking update since that scan once the round's end has passed, whatever the
 * device's clock, and stop showing it out for delivery (widgets, Live Activities) once its day has. None unless the
 * newest scan the apps show (`served`, the row as withEventPlaces serves it, read by the apps' own `currentEvent`) is
 * out for delivery, nor for an archived parcel.
 */
export function deliveryRound(row: JsonObject, served: JsonObject): { ends: string; over: string } | null {
  if (row.archived_at || !Array.isArray(served.tracking_events)) return null;
  const events = served.tracking_events.filter(isRecord).map((event) => toEvent(event as unknown as ApiTrackingEventRow));
  const held = isRecord(served.carrier_data) && served.carrier_data.return_held === true ? true : undefined;
  const current = currentEvent(events, held);
  if (current?.stage !== 'out_for_delivery') return null;
  const over = deliveryDayEnd(row, current.occurredAt);
  if (!over) return null;
  // The scans served leave out their source: the stored row has it.
  const stored = Array.isArray(row.tracking_events) ? row.tracking_events.filter(isRecord).find((scan) => scan.id === current.id) : undefined;
  const days = relayedRoundDays(row, stored);
  const ends = days ? DateTime.fromISO(over, { setZone: true }).plus({ days }).toISO({ suppressMilliseconds: true }) : over;
  return { ends: ends ?? over, over };
}

/**
 * The days a relayed round lasts after its own (`RELAYED_ROUND_DAYS`): for a parcel filed under such a carrier and not
 * handed to another, whose scan out for delivery is that carrier's or a provider's copy, not another carrier's own.
 */
function relayedRoundDays(row: JsonObject, scan: JsonObject | undefined): number {
  const days = RELAYED_ROUND_DAYS[String(row.carrier)];
  const data = isRecord(row.carrier_data) ? row.carrier_data : {};
  const active = typeof data.active_tracking_carrier === 'string' ? data.active_tracking_carrier : '';
  if (!days || (active && active !== row.carrier)) return 0;
  const source = String(scan?.provider_event_id ?? '').split(':')[0]!;
  return source !== row.carrier && source !== 'unknown' && CARRIER_DEFINITIONS[source as CarrierId] ? 0 : days;
}
