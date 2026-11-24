import 'server-only';

import type { ApiPackageRow } from '../generated/apiContract';
import { distanceKm } from '../components/map/route';
import { withoutGeneratedScans } from '../lib/generatedTrack';
import { currentEvent, sortEventsDesc } from '../lib/stages';
import { toParcel } from '../store/apiRepo';
import type { EventPlace, ParcelWithEvents, Stage } from '../types';
import { ON_ITS_WAY } from './destinationPlace';
import { withEventPlaces } from './eventPlaces';
import { captureOperationalError } from './observability';
import { withSuggestedInputs, type ClientInputs } from './suggestedInputs';
import type { SupabaseUserClient } from './supabase';
import type { JsonObject } from './types';

/** Scans of the last mile: out for delivery, a missed attempt, a pickup point, the delivery. */
const LAST_MILE: ReadonlySet<Stage> = new Set(['out_for_delivery', 'failed_attempt', 'ready_for_pickup', 'delivered']);
/** Deliveries this close to a town count as that town's. */
export const USUAL_TOWN_KM = 50;
/** The newest deliveries that count: after a move, the new town takes over within a few parcels. */
export const USUAL_TOWN_DELIVERIES = 10;
/** A parcel scanned this close to the town has as good as reached it: the routes end there (src/components/map/route.ts). */
const REACHED_KM = 15;

type Delivery = { place: EventPlace; at: string };

const stageOf = (parcel: ParcelWithEvents): Stage | undefined => currentEvent(parcel.events, parcel.returnHeld)?.stage;

/** Where a delivered parcel was delivered: the town of its newest last-mile scan placed in one, or null. */
function deliveredIn(parcel: ParcelWithEvents): Delivery | null {
  if (stageOf(parcel) !== 'delivered') return null;
  const scan = sortEventsDesc(parcel.events).find((event) => LAST_MILE.has(event.stage) && event.place?.precision === 'city');
  return scan?.place ? { place: scan.place, at: scan.occurredAt } : null;
}

/**
 * The town where the account's parcels are usually delivered, from its own delivered parcels as the apps read them:
 * of its newest ten deliveries placed in a town, at least two, the town with most of them within 50 km, when that is
 * more than half. Ties go to the town more of them name, then to the newest. Null otherwise.
 */
export function usualTown(parcels: readonly ParcelWithEvents[]): EventPlace | null {
  const deliveries = parcels.map(deliveredIn).filter((delivery): delivery is Delivery => delivery !== null)
    .sort((a, b) => (Date.parse(a.at) || 0) - (Date.parse(b.at) || 0)).slice(-USUAL_TOWN_DELIVERIES);
  if (deliveries.length < 2) return null;
  const at = (place: EventPlace) => [place.longitude, place.latitude] as [number, number];
  let best: { place: EventPlace; near: number; same: number } | null = null;
  for (const { place } of deliveries) {
    const near = deliveries.filter((other) => distanceKm(at(place), at(other.place)) <= USUAL_TOWN_KM).length;
    const same = deliveries.filter((other) => other.place.country === place.country && other.place.name === place.name).length;
    // Later deliveries win ties: the loop runs oldest first.
    if (!best || near > best.near || (near === best.near && same >= best.same)) best = { place, near, same };
  }
  if (!best || best.near * 2 <= deliveries.length) return null;
  const { latitude, longitude, country, name } = best.place;
  return { latitude, longitude, precision: 'city', country, name };
}

/**
 * Whether the parcel's map may head for the usual town: it is on its way, in the town's country by its newest placed
 * scan (a generated track's left out, as the map leaves it), going to that country if the carrier names one, still
 * more than 15 km from the town, and its carrier names no closer place: neither the town of the address
 * (`destination_place`) nor a pickup point.
 */
export function headsFor(parcel: ParcelWithEvents, town: EventPlace): boolean {
  if (parcel.destinationPlace || parcel.pickupPoint) return false;
  if (parcel.destinationCountry && parcel.destinationCountry.toUpperCase() !== town.country) return false;
  if (!ON_ITS_WAY.has(stageOf(parcel) ?? '')) return false;
  const newest = sortEventsDesc(withoutGeneratedScans(parcel.events)).find((event) => event.place)?.place;
  return newest !== undefined && newest.country === town.country
    && distanceKm([newest.longitude, newest.latitude], [town.longitude, town.latitude]) >= REACHED_KM;
}

let reported = false;

/**
 * The account's parcels as withEventPlaces serves them, with the town where its parcels are usually delivered
 * (`usual_place`) on each one whose map heads there: the apps draw the rest of the way to it as an estimate, "usually
 * delivered near Lyon". Only the account's own delivered parcels count, read through its own token, and only its own
 * routes serve it: never a link, a friend, an email or an assistant, nor a guess from the reader's network or clock.
 * `parcels` holds every parcel of the account (the list with archived ones) or, for a route that serves one, just it:
 * the account's delivered parcels are then read, only when a parcel could take the town. Without them, as when they
 * cannot be read, the parcels are served as they are: the town is a nice extra.
 */
export async function withUsualPlaces(client: SupabaseUserClient, parcels: JsonObject[], { complete }: { complete: boolean }): Promise<JsonObject[]> {
  try {
    const read = parcels.map((row) => toParcel(row as unknown as ApiPackageRow));
    const could = read.map((parcel) => !parcel.destinationPlace && !parcel.pickupPoint && ON_ITS_WAY.has(stageOf(parcel) ?? '')
      && parcel.events.some((event) => event.place));
    if (!could.some(Boolean)) return parcels;
    const town = usualTown(complete ? read : (await client.listDeliveredPackages())
      .map((row) => toParcel(withEventPlaces(row) as unknown as ApiPackageRow)));
    if (!town) return parcels;
    return parcels.map((row, index) => could[index] && headsFor(read[index]!, town) ? { ...row, usual_place: town } : row);
  } catch (error) {
    if (!reported) captureOperationalError(error, { component: 'api', operation: 'usual_delivery_town' });
    reported = true;
    return parcels;
  }
}

/**
 * One of the account's parcels as its own routes serve it (renaming, muting, restoring, changing its carrier, adding
 * it): with the town it is addressed to and, when its map heads there, the town its parcels are usually delivered to,
 * and the inputs the app can ask for that may find it (suggestedInputs.ts).
 */
export async function servedOwnParcel(client: SupabaseUserClient, row: JsonObject, inputs: ClientInputs): Promise<JsonObject> {
  return (await withUsualPlaces(client, [withEventPlaces(withSuggestedInputs(row, inputs), { destination: true })], { complete: false }))[0]!;
}
