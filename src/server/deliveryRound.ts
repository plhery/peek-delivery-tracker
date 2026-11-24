import 'server-only';

import type { ApiTrackingEventRow } from '../generated/apiContract';
import { currentEvent } from '../lib/stages';
import { toEvent } from '../store/apiRepo';
import { deliveryDayEnd } from './trackingSync';
import { isRecord, type JsonObject } from './types';

/**
 * When the round a parcel went out on is over (`delivery_round_ends_at`): the end of the day of its newest scan, out
 * for delivery, on the clock of the carrier delivering it (`deliveryDayEnd`). The apps say the parcel has had no
 * tracking update since that scan once this moment has passed, whatever the device's clock. None unless the newest
 * scan the apps show (`served`, the row as withEventPlaces serves it, read by the apps' own `currentEvent`) is out for
 * delivery, nor for an archived parcel.
 */
export function deliveryRoundEnd(row: JsonObject, served: JsonObject): string | null {
  if (row.archived_at || !Array.isArray(served.tracking_events)) return null;
  const events = served.tracking_events.filter(isRecord).map((event) => toEvent(event as unknown as ApiTrackingEventRow));
  const held = isRecord(served.carrier_data) && served.carrier_data.return_held === true ? true : undefined;
  const current = currentEvent(events, held);
  return current?.stage === 'out_for_delivery' ? deliveryDayEnd(row, current.occurredAt) : null;
}
