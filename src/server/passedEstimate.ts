import 'server-only';

import type { ApiPackageRow } from '../generated/apiContract';
import { currentEvent } from '../lib/stages';
import { toParcel } from '../store/apiRepo';
import { deliveryToday } from './trackingSync';
import { isRecord, type JsonObject } from './types';

/** How many days after the carrier's expected day the parcel's page still says when the parcel was expected. */
export const PASSED_ESTIMATE_DAYS = 7;

/** A parcel delivered, returned or waiting to be collected has no delivery due, so it missed no day. */
const NOT_DUE: ReadonlySet<string> = new Set(['delivered', 'returned', 'ready_for_pickup']);

/** The day an estimate names (`2026-11-17`, `2026-11-17 13:00–15:00`, `2026-11-17T18:00:00+00:00`), or null. */
function estimateDay(value: unknown): string | null {
  return typeof value === 'string' ? /^\d{4}-\d{2}-\d{2}/.exec(value.trim())?.[0] ?? null : null;
}

/**
 * The day a parcel's carrier last expected to deliver it, once that day has passed (`expected_delivery_passed`): from
 * the day after it through the seventh day after, on the clock of the carrier delivering it, which a check drops a
 * passed day by (`deliveryToday`). The day is `expected_delivery`'s while the carrier still names it, else the one a
 * check stopped keeping once it had passed (`last_expected_delivery`, trackingSync.ts), so a carrier that names a day
 * ahead again, a later one, gives the apps their estimate and no passed day. None for an archived parcel, nor for one
 * delivered, returned or waiting at a pickup point by the scans the apps show (`served`, the row as withEventPlaces
 * serves it, read by the apps' own `currentEvent`).
 */
export function passedEstimateDay(row: JsonObject, served: JsonObject, now = new Date()): string | null {
  if (row.archived_at) return null;
  const data = isRecord(row.carrier_data) ? row.carrier_data : {};
  const named = typeof row.expected_delivery === 'string' && row.expected_delivery.trim() !== '';
  const day = estimateDay(named ? row.expected_delivery : data.last_expected_delivery);
  if (!day) return null;
  const past = (Date.parse(`${deliveryToday(row, now)}T00:00:00Z`) - Date.parse(`${day}T00:00:00Z`)) / 86_400_000;
  if (!(past >= 1 && past <= PASSED_ESTIMATE_DAYS)) return null;
  const parcel = toParcel(served as unknown as ApiPackageRow);
  const stage = currentEvent(parcel.events, parcel.returnHeld)?.stage;
  return stage && NOT_DUE.has(stage) ? null : day;
}
