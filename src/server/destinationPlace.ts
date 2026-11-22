import 'server-only';

import { locatePlace } from 'universal-parcel-scraper/places';
import { captureOperationalError } from './observability';
import { isRecord, type JsonObject } from './types';

/** The stages of a parcel still on its way to the address: not one waiting at a pickup point, delivered or going back. */
const ON_ITS_WAY: ReadonlySet<string> = new Set(['pending', 'registered', 'accepted', 'in_transit', 'customs', 'exception',
  'out_for_delivery', 'failed_attempt']);

let reported = false;

/**
 * The town the parcel is addressed to (`destination_place`), placed from the address its carrier gives
 * (`receiver_address`: the street first, the postcode and town last), for the owner's map: the route reads
 * "To <town>, N km to go" until the newest scan is within 15 km of it (src/components/map/route.ts, and the apps'
 * ParcelRoute). The address is the owner's (privateDetails.ts), so only the owner's routes ask for it. Only a town in
 * the country the parcel is going to, or, when the carrier names none, in the country of its newest placed scan: a town
 * of the same name elsewhere is no dot at all. Null when the carrier gives no town, once the parcel stops being on its
 * way, and without the gazetteer; the route keeps the destination's country.
 */
export function destinationPlace(row: JsonObject, events: readonly JsonObject[]): JsonObject | null {
  const data = isRecord(row.carrier_data) ? row.carrier_data : {};
  if (typeof data.receiver_address !== 'string' || !ON_ITS_WAY.has(String(row.current_stage))) return null;
  const town = data.receiver_address.split('\n').map((line) => line.trim()).filter(Boolean).at(-1);
  const country = typeof data.destination_country === 'string' && /^[A-Za-z]{2}$/.test(data.destination_country)
    ? data.destination_country.toUpperCase() : newestCountry(events);
  if (!town || !country) return null;
  try {
    const place = locatePlace(town, {
      countries: [country],
      carriers: [data.active_tracking_carrier, row.carrier].filter((carrier): carrier is string => typeof carrier === 'string'),
    });
    if (place?.precision !== 'city' || place.country !== country) return null;
    return { latitude: place.latitude, longitude: place.longitude, precision: 'city', country, name: place.name };
  } catch (error) {
    if (!reported) captureOperationalError(error, { component: 'api', operation: 'locate_destination_place' });
    reported = true;
    return null;
  }
}

/** The country of the newest scan placed in a town, or null. */
function newestCountry(events: readonly JsonObject[]): string | null {
  let newest: { at: string; country: string } | null = null;
  for (const event of events) {
    const place = isRecord(event.place) ? event.place : null;
    if (place?.precision !== 'city' || typeof place.country !== 'string' || typeof event.occurred_at !== 'string') continue;
    if (!newest || event.occurred_at > newest.at) newest = { at: event.occurred_at, country: place.country };
  }
  return newest?.country ?? null;
}
