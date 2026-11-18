import 'server-only';
import { hasPrivateDeliveryDetails } from 'universal-parcel-scraper';
import { isRecord } from './types';

/** What a viewer reads of a delivery: who took it, where it was left and with what code are the recipient's. */
export const VIEWER_DELIVERY_DESCRIPTION = 'Delivered';

/** The recipient details a carrier gave, which only the parcel's owner reads. */
export const PRIVATE_CARRIER_DATA = ['signed_by', 'receiver_name', 'receiver_address', 'pickup_code'] as const;

interface Scan {
  stage: string;
  description: string;
  location: string | null;
  place?: unknown;
}

/**
 * What tells a viewer's text apart from the owner's: the recipient details the parcel's carrier data holds,
 * each of three characters or more, found whatever their case.
 */
export function privateValues(carrierData: unknown): string[] {
  if (!isRecord(carrierData)) return [];
  return PRIVATE_CARRIER_DATA.map((key) => carrierData[key])
    .filter((value): value is string => typeof value === 'string' && value.trim().length >= 3)
    .map((value) => value.trim().toLocaleLowerCase());
}

/** Whether a text names the recipient or what lets someone else collect the parcel or find its door. */
export function namesRecipient(text: string, values: readonly string[]): boolean {
  if (hasPrivateDeliveryDetails(text)) return true;
  const lower = text.toLocaleLowerCase();
  return values.some((value) => lower.includes(value));
}

/**
 * A scan as someone other than the parcel's owner reads it, as a shared link's viewers do. A delivery reads
 * VIEWER_DELIVERY_DESCRIPTION, since carriers word it with who took the parcel, where it was left or the
 * code it took. Any other scan that names the recipient or such a detail (`namesRecipient`) is left out, and
 * so is a place that does, with the point it was found at. The owner reads every scan as the carrier wrote it.
 */
export function viewerScan<T extends Scan>(scan: T, values: readonly string[] = []): T | null {
  const delivered = scan.stage === 'delivered';
  if (!delivered && namesRecipient(scan.description, values)) return null;
  const privatePlace = scan.location !== null && namesRecipient(scan.location, values);
  const reworded = delivered && scan.description !== VIEWER_DELIVERY_DESCRIPTION;
  if (!reworded && !privatePlace) return scan;
  return {
    ...scan,
    ...(reworded ? { description: VIEWER_DELIVERY_DESCRIPTION } : {}),
    ...(privatePlace ? { location: null, ...('place' in scan ? { place: null } : {}) } : {}),
  };
}

/** The scans a viewer reads, in their order. */
export function viewerScans<T extends Scan>(scans: readonly T[], values: readonly string[] = []): T[] {
  return scans.flatMap((scan) => viewerScan(scan, values) ?? []);
}

/**
 * The scans in their order as `viewerScans` reads them, except that a scan it leaves out stays, in words of its stage
 * (`stageWords`) and without its place. An assistant reads the owner's parcel this way: the parcel's status and what
 * it needs follow every scan, while the recipient's details stay out.
 */
export function redactedScans<T extends Scan>(scans: readonly T[], stageWords: (stage: string) => string, values: readonly string[] = []): T[] {
  return scans.map((scan) => viewerScan(scan, values)
    ?? { ...scan, description: stageWords(scan.stage), location: null, ...('place' in scan ? { place: null } : {}) });
}

/** A carrier's status line as a viewer reads it: none for a delivered parcel, or one that names the recipient. */
export function viewerStatusText(text: string | null, stage: unknown, values: readonly string[] = []): string | null {
  return text === null || stage === 'delivered' || namesRecipient(text, values) ? null : text;
}
