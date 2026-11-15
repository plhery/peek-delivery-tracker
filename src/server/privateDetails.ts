import 'server-only';
import { hasPrivateDeliveryDetails } from 'universal-parcel-scraper';

/** What a viewer reads of a delivery whose wording names a signatory, a code or a door. */
export const PRIVATE_DELIVERY_DESCRIPTION = 'Delivered';

interface Scan {
  stage: string;
  description: string;
  location: string | null;
  place?: unknown;
}

/**
 * A scan as someone other than the parcel's owner reads it, as a shared link's viewers do: without what lets
 * another collect the parcel or find its door (`hasPrivateDeliveryDetails`). A delivery that names one reads
 * PRIVATE_DELIVERY_DESCRIPTION; any other scan that names one is left out. A place that names one goes, with
 * the point it was found at. The owner reads every scan as the carrier wrote it.
 */
export function viewerScan<T extends Scan>(scan: T): T | null {
  const privateWording = hasPrivateDeliveryDetails(scan.description);
  if (privateWording && scan.stage !== 'delivered') return null;
  const privatePlace = scan.location !== null && hasPrivateDeliveryDetails(scan.location);
  if (!privateWording && !privatePlace) return scan;
  return {
    ...scan,
    ...(privateWording ? { description: PRIVATE_DELIVERY_DESCRIPTION } : {}),
    ...(privatePlace ? { location: null, ...('place' in scan ? { place: null } : {}) } : {}),
  };
}

/** The scans a viewer reads, in their order. */
export function viewerScans<T extends Scan>(scans: readonly T[]): T[] {
  return scans.flatMap((scan) => viewerScan(scan) ?? []);
}

/** A carrier's status line as a viewer reads it: none when it names a signatory, a code or a door. */
export function viewerStatusText(text: string | null): string | null {
  return text !== null && hasPrivateDeliveryDetails(text) ? null : text;
}
