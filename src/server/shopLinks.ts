import 'server-only';

import {
  normalizeTrackingNumber, resolveShopTrackingLink, shopTrackingLinks, type ShopLinkFailure, type ShopLinkParcel,
  type ShopLinkResolution, type ShopTrackingLink,
} from 'universal-parcel-scraper';
import type { NumberInText } from '../peek/lookup/reading';
import { captureOperationalError } from './observability';

/** The most shop tracking-page links one text is resolved for: an email links its order's page once or twice. */
export const MOST_SHOP_LINKS = 3;
/** The time all of one text's links take, resolved side by side. */
const RESOLVE_MS = 8_000;

/**
 * A shop link that named no parcel, and why. `link` opens a page with the reader's name and address: it stays in
 * memory, for a wait to keep in its owner's row (shopWaits.ts), and is never logged, reported or answered.
 */
export interface UnresolvedShopLink {
  platform: string | null;
  shop: string | null;
  reason: ShopLinkFailure;
  link: ShopTrackingLink;
}

export interface ShopLinkReading {
  numbers: NumberInText[];
  unresolved: UnresolvedShopLink[];
}

export type Resolver = (link: ShopTrackingLink, options: { signal?: AbortSignal; timeoutMs: number }) => Promise<ShopLinkResolution>;

/**
 * The parcels a text's shop tracking-page links name (parcelLab's `delivery-status.com`, as H&M's "Follow package"),
 * each with the carrier the shop's platform names, read with a few anonymous requests to that platform. Nothing of the
 * page but the number and the carrier is kept: no name, no address.
 */
export async function shopLinkNumbers(text: string, resolve: Resolver = resolveShopTrackingLink): Promise<ShopLinkReading> {
  const links = shopTrackingLinks(text).slice(0, MOST_SHOP_LINKS);
  if (!links.length) return { numbers: [], unresolved: [] };
  const resolutions = await Promise.all(links.map((link) => resolveSafely(link, resolve)));
  const numbers: ShopLinkParcel[] = [];
  const unresolved: UnresolvedShopLink[] = [];
  resolutions.forEach((resolution, index) => {
    if (resolution.status === 'resolved') numbers.push(...resolution.parcels);
    else unresolved.push({ platform: resolution.platform ?? null, shop: resolution.shop ?? null, reason: resolution.reason, link: links[index] });
  });
  return { numbers: parcelNumbers(numbers), unresolved };
}

/** What one link names, a failure of the resolver itself read as the platform being unavailable. */
export async function resolveSafely(link: ShopTrackingLink, resolve: Resolver = resolveShopTrackingLink): Promise<ShopLinkResolution> {
  try {
    return await resolve(link, { timeoutMs: RESOLVE_MS });
  } catch (error) {
    captureOperationalError(error, { component: 'shop_links', operation: `resolve_${link.platform}` });
    return { platform: link.platform, ...(link.shop ? { shop: link.shop } : {}), status: 'unresolved', reason: 'unavailable' };
  }
}

/** The numbers a shop's platform named, each once, as a text's numbers are read. */
export function parcelNumbers(parcels: readonly ShopLinkParcel[]): NumberInText[] {
  const numbers = new Map<string, NumberInText>();
  for (const parcel of parcels) {
    const normalized = normalizeTrackingNumber(parcel.trackingNumber);
    if (!normalized || numbers.has(normalized)) continue;
    numbers.set(normalized, { normalized, match: {
      trackingNumber: parcel.trackingNumber, carrier: parcel.carrier, confidence: parcel.confidence,
      candidates: parcel.carrier === 'unknown' ? [] : [parcel.carrier], preferred: [], source: 'link',
    } });
  }
  return [...numbers.values()];
}

/**
 * A text's numbers with its shop links' parcels: a number the text names itself keeps its place, and takes the
 * carrier the shop's platform names when the text's own reading was less sure; the links' other parcels follow.
 */
export function withShopLinkNumbers(numbers: readonly NumberInText[], fromLinks: readonly NumberInText[]): NumberInText[] {
  const linked = new Map(fromLinks.map((number) => [number.normalized, number]));
  const merged = numbers.map((number) => {
    const link = linked.get(number.normalized);
    return link && number.match.confidence !== 'high' && link.match.confidence === 'high' ? { ...number, match: { ...link.match, trackingNumber: number.match.trackingNumber } } : number;
  });
  const named = new Set(numbers.map(({ normalized }) => normalized));
  return [...merged, ...fromLinks.filter(({ normalized }) => !named.has(normalized))];
}
