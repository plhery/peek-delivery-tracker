import type { ApiShopLinkResponse, ApiShopWait, ApiShopWaitsResponse } from '../generated/apiContract';
import { authenticatedFetch, type ApiAuth } from './apiClient';

/**
 * The shop links API: what a shop's tracking-page link names (signed in or not), and the account's waits for shops
 * that haven't shipped. A request's body holds the link, which opens the reader's order: it goes to Peek only.
 */

async function answer<T>(response: Response): Promise<T> {
  if (!response.ok) throw new Error(`Shop links answered ${response.status}`);
  return await response.json() as T;
}

/** What the text's shop links name, or why they name none. */
export async function readShopLink(text: string, auth?: ApiAuth, signal?: AbortSignal): Promise<ApiShopLinkResponse> {
  const init = { method: 'POST', body: JSON.stringify({ text }), signal };
  if (auth) return answer(await authenticatedFetch('/api/shop-links', auth, init));
  return answer(await fetch('/api/public/shop-links', {
    ...init, cache: 'no-store', headers: { 'Content-Type': 'application/json', 'X-Requested-With': 'XMLHttpRequest' },
  }));
}

export async function listShopWaits(auth: ApiAuth, signal?: AbortSignal): Promise<ApiShopWait[]> {
  return (await answer<ApiShopWaitsResponse>(await authenticatedFetch('/api/shop-waits', auth, { signal }))).shop_waits;
}

/** Waits for the parcels of the text's shop links, named `label`. */
export async function keepShopWait(text: string, label: string, auth: ApiAuth): Promise<ApiShopWait[]> {
  const response = await authenticatedFetch('/api/shop-waits', auth, { method: 'POST', body: JSON.stringify({ text, label }) });
  const waits = (await answer<ApiShopWaitsResponse>(response)).shop_waits;
  announceShopWaits();
  return waits;
}

export async function removeShopWait(id: string, auth: ApiAuth): Promise<void> {
  const response = await authenticatedFetch(`/api/shop-waits/${encodeURIComponent(id)}`, auth, { method: 'DELETE' });
  if (!response.ok && response.status !== 404) throw new Error(`Shop links answered ${response.status}`);
}

/** The list's waits are read again: one was just kept. */
export const SHOP_WAITS_CHANGED = 'peek:shop-waits';
export function announceShopWaits(): void {
  if (typeof window !== 'undefined') window.dispatchEvent(new Event(SHOP_WAITS_CHANGED));
}
