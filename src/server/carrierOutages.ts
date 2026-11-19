import 'server-only';

import type { CarrierId } from 'universal-parcel-scraper';
import { activeTrackingCarrierId, parcelTrackingLinks, type TrackedParcel } from 'universal-parcel-scraper/app';
import { errorType, logOperationalEvent } from './observability';
import type { SupabaseServiceClient } from './supabase';
import { isRecord, type JsonObject } from './types';

/** How long the list of carriers down is read again after. */
const FRESH_MS = 60_000;
/** A list that could not be read again is kept this long, then taken for none. */
const KEPT_MS = 10 * 60_000;
/** The first read of a server waits this long at most before serving parcels without it. */
const FIRST_READ_MS = 2_000;

let known: { at: number; outages: ReadonlyMap<string, string> } | undefined;
let reading: Promise<void> | undefined;

/**
 * Reads which carriers' sites are down (`carrier_outages`), at most once a minute per server, so the parcels served next
 * say so (`servedCarrierOutage`). A list a minute old is served while the next is read; a server's first read is waited
 * for two seconds at most. Never throws: parcels are served without it.
 */
export async function loadCarrierOutages(client: Pick<SupabaseServiceClient, 'carrierOutages'>, now = Date.now()): Promise<void> {
  if (known && now - known.at < FRESH_MS) return;
  reading ??= client.carrierOutages().then(
    (outages) => { known = { at: now, outages }; },
    (error: unknown) => {
      logOperationalEvent('carrier_outages_unread', { error_type: errorType(error) }, 'warning');
      if (known && now - known.at >= KEPT_MS) known = undefined;
    },
  ).finally(() => { reading = undefined; });
  if (known) return;
  let timer: ReturnType<typeof setTimeout> | undefined;
  await Promise.race([reading, new Promise<void>((resolve) => { timer = setTimeout(resolve, FIRST_READ_MS); })]);
  clearTimeout(timer);
}

/** Forgets the list read, for tests. */
export function forgetCarrierOutages(): void {
  known = undefined;
  reading = undefined;
}

/**
 * What a served parcel says of its carrier's outage (`carrier_outage`): the carrier tracking it now, whose site is down
 * (`loadCarrierOutages`), since when, and that carrier's own page for the number, where the reader can see it. None
 * once the parcel is delivered or returned (`events` as served), nor when it got an answer since the site went down,
 * from another source: it is not waiting for that carrier. It ends with the outage, on the next list read.
 */
export function servedCarrierOutage(row: JsonObject, events: readonly JsonObject[]): JsonObject | null {
  if (!known?.outages.size || typeof row.carrier !== 'string' || typeof row.tracking_number !== 'string') return null;
  const data = isRecord(row.carrier_data) ? row.carrier_data : {};
  const text = (value: unknown) => typeof value === 'string' && value ? value : undefined;
  const parcel: TrackedParcel = {
    carrier: row.carrier as CarrierId,
    trackingNumber: row.tracking_number,
    trackingUrl: text(row.tracking_url),
    trackingSource: text(data.active_tracking_carrier) as CarrierId | undefined,
    trackingProvider: text(data.tracking_provider),
    carrierAnswered: data.carrier_answered === true ? true : undefined,
    activeTrackingNumber: text(data.active_tracking_number),
    swissPostReady: data.swiss_post_ready === true ? true : undefined,
    originalCarrier: text(data.original_carrier) as CarrierId | undefined,
    originalTrackingNumber: text(data.original_tracking_number),
    originalTrackingUrl: text(data.original_tracking_url),
    destinationCountry: text(data.destination_country),
  };
  const carrier = activeTrackingCarrierId(parcel);
  const since = known.outages.get(carrier);
  if (!since) return null;
  if (events.some((event) => event.stage === 'delivered' || event.stage === 'returned')) return null;
  const routing = isRecord(data.routing) ? data.routing : {};
  if (Date.parse(String(routing.last_success_at ?? '')) > Date.parse(since)) return null;
  const links = parcelTrackingLinks(parcel).filter((link) => link.carrier.id === carrier);
  const link = links.find((candidate) => candidate.active) ?? links[0];
  return { carrier, since, ...(link ? { tracking_url: link.url } : {}) };
}
