import 'server-only';

import type { ApiShopLinkResponse, ApiShopWaitsResponse } from '../generated/apiContract';
import { shopDisplayName, shopPlatformName } from '../lib/shopNames';
import { HttpError, json, readJsonObject, requireService, requireUser, type ApiContext } from './api';
import { RateLimiter } from './rateLimit';
import { MOST_SHOP_LINKS, shopLinkNumbers, type Resolver } from './shopLinks';
import { keepShopWaits, textShopLinks } from './shopWaits';

/** A pasted or shared text is a message or a link, never a whole email. */
const MOST_TEXT = 4_000;
/** Each reading asks the shop's platform: an account reads a few links a day. */
const READS = { limit: 20, window: 600 };
const reads = new RateLimiter();

function requestText(body: Record<string, unknown>): string {
  const text = body.text;
  if (typeof text !== 'string' || !text.trim() || text.length > MOST_TEXT) throw new HttpError(400, 'Send the text holding the link');
  return text;
}

/**
 * What a text's shop links name, read for `key` (an account, or a visitor's network): the numbers and carriers, or
 * why a link named none. Nothing of the page or the link is answered.
 */
export async function readShopLinks(context: ApiContext, key: string, resolve?: Resolver): Promise<Response> {
  const text = requestText(await readJsonObject(context.request));
  const retryAfter = reads.retryAfter(key, READS);
  if (retryAfter) throw new HttpError(429, 'Too many requests. Try again shortly.', { 'Retry-After': String(retryAfter) });
  const links = textShopLinks(text).length;
  const reading = links ? await shopLinkNumbers(text, resolve) : { numbers: [], unresolved: [] };
  const answer: ApiShopLinkResponse = {
    links: Math.min(links, MOST_SHOP_LINKS),
    parcels: reading.numbers.map(({ match }) => ({
      tracking_number: match.trackingNumber, carrier: match.carrier, confidence: match.confidence,
    })),
    unresolved: reading.unresolved.map(({ link, reason }) => ({
      platform: 'parcellab',
      platform_name: shopPlatformName(link.platform),
      shop_name: shopDisplayName(link.shop),
      reason,
      waitable: reason !== 'unsupported',
    })),
  };
  return json(answer);
}

/** Waits for the parcels of a text's shop links, for the signed-in account. */
export async function keepShopWaitsRoute(context: ApiContext): Promise<Response> {
  const user = requireUser(context);
  const body = await readJsonObject(context.request);
  const text = requestText(body);
  const label = typeof body.label === 'string' ? body.label : '';
  if ([...label].length > 80) throw new HttpError(400, 'Invalid label');
  const answer: ApiShopWaitsResponse = {
    shop_waits: await keepShopWaits(requireService(context), user.id, textShopLinks(text), label, { soon: true }),
  };
  return json(answer);
}
