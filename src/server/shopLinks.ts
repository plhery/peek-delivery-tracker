import 'server-only';

import {
  normalizeTrackingNumber, resolveShopTrackingLink, shopTrackingLinks, type ShopLinkFailure, type ShopLinkResolution,
  type ShopTrackingLink,
} from 'universal-parcel-scraper';
import type { NumberInText } from '../peek/lookup/reading';
import { captureOperationalError } from './observability';

/** The most shop tracking-page links one text is resolved for: an email links its order's page once or twice. */
export const MOST_SHOP_LINKS = 3;
/** The time all of one text's links take, resolved side by side. */
const RESOLVE_MS = 8_000;

/** A shop link that named no parcel, and why. Only its platform and shop: the link holds a key to the order. */
export interface UnresolvedShopLink {
  platform: string | null;
  shop: string | null;
  reason: ShopLinkFailure;
}

export interface ShopLinkReading {
  numbers: NumberInText[];
  unresolved: UnresolvedShopLink[];
}

type Resolver = (link: ShopTrackingLink, options: { signal?: AbortSignal; timeoutMs: number }) => Promise<ShopLinkResolution>;

/**
 * The parcels a text's shop tracking-page links name (parcelLab's `delivery-status.com`, as H&M's "Follow package"),
 * each with the carrier the shop's platform names, read with a few anonymous requests to that platform. Nothing of the
 * page but the number and the carrier is kept: no name, no address.
 */
export async function shopLinkNumbers(text: string, resolve: Resolver = resolveShopTrackingLink): Promise<ShopLinkReading> {
  const links = shopTrackingLinks(text).slice(0, MOST_SHOP_LINKS);
  if (!links.length) return { numbers: [], unresolved: [] };
  const resolutions = await Promise.all(links.map(async (link): Promise<ShopLinkResolution> => {
    try {
      return await resolve(link, { timeoutMs: RESOLVE_MS });
    } catch (error) {
      captureOperationalError(error, { component: 'shop_links', operation: `resolve_${link.platform}` });
      return { platform: link.platform, ...(link.shop ? { shop: link.shop } : {}), status: 'unresolved', reason: 'unavailable' };
    }
  }));
  const numbers = new Map<string, NumberInText>();
  const unresolved: UnresolvedShopLink[] = [];
  for (const resolution of resolutions) {
    if (resolution.status === 'unresolved') {
      unresolved.push({ platform: resolution.platform ?? null, shop: resolution.shop ?? null, reason: resolution.reason });
      continue;
    }
    for (const parcel of resolution.parcels) {
      const normalized = normalizeTrackingNumber(parcel.trackingNumber);
      if (!normalized || numbers.has(normalized)) continue;
      numbers.set(normalized, { normalized, match: {
        trackingNumber: parcel.trackingNumber, carrier: parcel.carrier, confidence: parcel.confidence,
        candidates: parcel.carrier === 'unknown' ? [] : [parcel.carrier], preferred: [], source: 'link',
      } });
    }
  }
  return { numbers: [...numbers.values()], unresolved };
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
