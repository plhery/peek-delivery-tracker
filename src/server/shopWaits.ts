import 'server-only';

import { shopTrackingLinks, type ShopLinkFailure, type ShopTrackingLink } from 'universal-parcel-scraper';
import type { ApiShopWait } from '../generated/apiContract';
import { shopDisplayName, shopPlatformName } from '../lib/shopNames';
import { captureOperationalError, errorType, logOperationalEvent } from './observability';
import { addNumbersToAccount } from './parcelsFromText';
import { MOST_SHOP_LINKS, parcelNumbers, resolveSafely, type Resolver, type UnresolvedShopLink } from './shopLinks';
import { SupabaseError, type SupabaseServiceClient } from './supabase';

/**
 * A shop's tracking-page link that names no parcel yet, as before the shop ships, waits for one: the server asks
 * the shop's platform again every few hours, for up to 14 days, and adds the parcels it then names under the wait's
 * name. The link opens a page with the reader's name and address. Only the owner's row holds it (no client reads
 * the table), and it goes when the wait turns into parcels (the row is deleted) or runs out (the row keeps its shop
 * and name, to say so). It is never logged, reported, answered, exported, shared or linked.
 */

export const WAIT_DAYS = 14;
/** The waits one account holds open: a reader waits for a few orders at a time. */
export const MOST_OPEN_WAITS = 20;
/** How often a wait is asked again, and the most a platform's refusals stretch that to. */
const CHECK_EVERY_MS = 4 * 3_600_000;
const MOST_BACKOFF_MS = 24 * 3_600_000;
/** After a refusal or a rate limit, the platform is left alone this long, every wait of it. */
const PLATFORM_PAUSE_MS = 3_600_000;
/** The waits one scheduled run asks for, one after another. */
const CHECKS_PER_RUN = 4;
/** A wait asked for by hand (Add's "Wait for"), just answered "nothing yet", is asked again this soon. */
const FIRST_CHECK_MS = 30 * 60_000;
/** A wait that ran out is shown this long, then forgotten. */
const EXPIRED_KEPT_MS = 30 * 86_400_000;

/** Why a link is worth waiting for: the order may still ship, or the platform may answer later. */
const WAITABLE: ReadonlySet<ShopLinkFailure> = new Set(['not_found', 'no_tracking_yet', 'refused', 'rate_limited', 'unavailable']);
const REFUSALS: ReadonlySet<ShopLinkFailure> = new Set(['refused', 'rate_limited']);

const WAIT_COLUMNS = 'id,platform,shop,label,created_at,next_check_at,expires_at,expired_at';

interface WaitRow {
  id: string;
  platform: string;
  shop: string | null;
  label: string;
  created_at: string;
  next_check_at: string;
  expires_at: string;
  expired_at: string | null;
}

interface DueRow extends WaitRow {
  user_id: string;
  link: string;
  checks: number;
}

/** The platform's pause after it refused, by platform. One server asks; a restart forgets, and asks once. */
const pausedUntil = new Map<string, number>();

/** A wait as its owner's apps see it: without its link. */
export function shopWaitResponse(row: WaitRow): ApiShopWait {
  return {
    id: row.id,
    platform: 'parcellab',
    platform_name: shopPlatformName(row.platform),
    shop_name: shopDisplayName(row.shop),
    label: row.label,
    created_at: row.created_at,
    expires_at: row.expires_at,
    expired_at: row.expired_at,
  };
}

function waitRows(value: unknown): WaitRow[] {
  return Array.isArray(value) ? value.filter((row): row is WaitRow => !!row && typeof row === 'object' && typeof row.id === 'string') : [];
}

/** The account's waits, open ones and those that ran out, oldest first. */
export async function accountShopWaits(service: SupabaseServiceClient, userId: string): Promise<ApiShopWait[]> {
  const params = new URLSearchParams({ select: WAIT_COLUMNS, user_id: `eq.${userId}`, order: 'created_at.asc', limit: String(MOST_OPEN_WAITS * 2) });
  return waitRows(await service.request(`/rest/v1/shop_waits?${params}`)).map(shopWaitResponse);
}

/** Removes one of the account's waits, with its link. False when the account has no such wait. */
export async function removeShopWait(service: SupabaseServiceClient, userId: string, id: string): Promise<boolean> {
  const params = new URLSearchParams({ id: `eq.${id}`, user_id: `eq.${userId}`, select: 'id' });
  return waitRows(await service.request(`/rest/v1/shop_waits?${params}`, { method: 'DELETE', prefer: 'return=representation' })).length > 0;
}

/**
 * Keeps a wait for each of `links` the account doesn't wait for already, named `label`, while the account holds
 * fewer than MOST_OPEN_WAITS. `soon` asks the first time within the half hour, for a wait the reader asked for;
 * otherwise after the usual few hours, the link having just been asked. Returns the account's waits for the links.
 */
export async function keepShopWaits(
  service: SupabaseServiceClient,
  userId: string,
  links: readonly ShopTrackingLink[],
  label: string,
  { soon = false, now = Date.now() }: { soon?: boolean; now?: number } = {},
): Promise<ApiShopWait[]> {
  if (!links.length) return [];
  const params = new URLSearchParams({ select: `${WAIT_COLUMNS},link`, user_id: `eq.${userId}`, expired_at: 'is.null', limit: String(MOST_OPEN_WAITS + 1) });
  const open = (waitRows(await service.request(`/rest/v1/shop_waits?${params}`)) as (WaitRow & { link: string })[]);
  const byLink = new Map(open.map((row) => [row.link, row]));
  const kept: ApiShopWait[] = [];
  let room = MOST_OPEN_WAITS - open.length;
  const name = [...label.trim()].slice(0, 80).join('').trim();
  for (const link of links.slice(0, MOST_SHOP_LINKS)) {
    const known = byLink.get(link.url);
    if (known) {
      kept.push(shopWaitResponse(known));
      continue;
    }
    if (room <= 0 || link.url.length > 2048) continue;
    try {
      const created = waitRows(await service.request('/rest/v1/shop_waits', {
        method: 'POST',
        prefer: 'return=representation',
        body: {
          user_id: userId,
          platform: link.platform,
          shop: link.shop?.slice(0, 80) || null,
          link: link.url,
          label: name,
          next_check_at: new Date(now + (soon ? FIRST_CHECK_MS : CHECK_EVERY_MS)).toISOString(),
          expires_at: new Date(now + WAIT_DAYS * 86_400_000).toISOString(),
        },
      }));
      room -= 1;
      kept.push(...created.map(shopWaitResponse));
    } catch (error) {
      // The same link kept by another request meanwhile: one wait is enough.
      if (!(error instanceof SupabaseError && error.code === '23505')) throw error;
    }
  }
  logOperationalEvent('shop_waits_kept', { links: links.length, waits: kept.length });
  return kept;
}

/**
 * The links of a text's unresolved shop links worth waiting for. A mailing tool's redirect never waits: each check
 * would count another click of the reader's.
 */
export function waitableLinks(unresolved: readonly UnresolvedShopLink[]): ShopTrackingLink[] {
  return unresolved.filter(({ reason, link }) => WAITABLE.has(reason) && link.platform !== 'redirect').map(({ link }) => link);
}

/** The shop links a text holds, read for a wait the reader asks for. */
export function textShopLinks(text: string): ShopTrackingLink[] {
  return shopTrackingLinks(text.slice(0, 16_000)).slice(0, MOST_SHOP_LINKS);
}

function delayAfter(reason: ShopLinkFailure, checks: number): number {
  return REFUSALS.has(reason) ? Math.min(MOST_BACKOFF_MS, CHECK_EVERY_MS * 2 ** Math.min(checks, 4)) : CHECK_EVERY_MS;
}

export interface ShopWaitRun {
  checked: number;
  resolved: number;
  expired: number;
}

/**
 * One scheduled run's waits: those run out lose their link, and are forgotten a month later; then the few most
 * overdue are asked, one after another, while their platform isn't paused. A wait whose platform names parcels adds
 * them to its owner's deliveries and goes. A refusal or a rate limit backs that wait off and pauses its platform.
 */
export async function checkShopWaits(
  service: SupabaseServiceClient,
  { now = Date.now(), resolve, signal }: { now?: number; resolve?: Resolver; signal?: AbortSignal } = {},
): Promise<ShopWaitRun> {
  const at = new Date(now).toISOString();
  const expired = waitRows(await service.request(`/rest/v1/shop_waits?${new URLSearchParams({ expired_at: 'is.null', expires_at: `lte.${at}`, select: 'id' })}`, {
    method: 'PATCH', body: { link: null, expired_at: at }, prefer: 'return=representation', signal,
  })).length;
  await service.request(`/rest/v1/shop_waits?${new URLSearchParams({ expired_at: `lt.${new Date(now - EXPIRED_KEPT_MS).toISOString()}` })}`, {
    method: 'DELETE', prefer: 'return=minimal', signal,
  });
  const due = await service.request<DueRow[]>(`/rest/v1/shop_waits?${new URLSearchParams({
    select: `${WAIT_COLUMNS},user_id,link,checks`, expired_at: 'is.null', next_check_at: `lte.${at}`, order: 'next_check_at.asc', limit: String(CHECKS_PER_RUN),
  })}`, { signal });
  let checked = 0;
  let resolved = 0;
  for (const wait of Array.isArray(due) ? due : []) {
    if (signal?.aborted || (pausedUntil.get(wait.platform) ?? 0) > now || typeof wait.link !== 'string') continue;
    checked += 1;
    const resolution = await resolveSafely({ platform: 'parcellab', url: wait.link, ...(wait.shop ? { shop: wait.shop } : {}) }, resolve);
    const update = (body: Record<string, unknown>) => service.request(`/rest/v1/shop_waits?${new URLSearchParams({ id: `eq.${wait.id}` })}`, {
      method: 'PATCH', body: { checks: wait.checks + 1, ...body }, prefer: 'return=minimal',
    });
    if (resolution.status === 'resolved' && resolution.parcels.length) {
      const additions = await addNumbersToAccount(service, wait.user_id, parcelNumbers(resolution.parcels), wait.label);
      if (additions.some(({ outcome }) => outcome === 'added' || outcome === 'known' || outcome === 'refused')) {
        await service.request(`/rest/v1/shop_waits?${new URLSearchParams({ id: `eq.${wait.id}` })}`, { method: 'DELETE', prefer: 'return=minimal' });
        resolved += 1;
      } else {
        // The account's allowance or the database: the parcels are asked for again within the hour.
        await update({ next_check_at: new Date(now + PLATFORM_PAUSE_MS).toISOString() });
      }
      continue;
    }
    const reason = resolution.status === 'unresolved' ? resolution.reason : 'no_tracking_yet';
    if (!WAITABLE.has(reason)) {
      // A link no platform reads any more: nothing to wait for.
      await update({ link: null, expired_at: at, last_reason: null });
      continue;
    }
    if (REFUSALS.has(reason)) pausedUntil.set(wait.platform, now + PLATFORM_PAUSE_MS);
    await update({ last_reason: reason, next_check_at: new Date(now + delayAfter(reason, wait.checks)).toISOString() });
  }
  if (checked || expired) logOperationalEvent('shop_waits_checked', { checked, resolved, expired });
  return { checked, resolved, expired };
}

/** How often the server looks for waits due: each is asked every few hours. */
const RUN_EVERY_MS = 5 * 60_000;

const globalWaits = globalThis as typeof globalThis & { __deliveryShopWaits?: { client: SupabaseServiceClient; timer: ReturnType<typeof setInterval> } };

/**
 * Runs `checkShopWaits` every few minutes, one run at a time, beside the parcel checks. A run that fails is reported,
 * and the next tries again.
 */
export function startShopWaits(service: SupabaseServiceClient): void {
  const current = globalWaits.__deliveryShopWaits;
  if (current?.client === service) return;
  if (current) clearInterval(current.timer);
  let running = false;
  const run = async () => {
    if (running) return;
    running = true;
    try {
      await checkShopWaits(service);
    } catch (error) {
      logOperationalEvent('shop_waits_failed', { error_type: errorType(error) }, 'error');
      captureOperationalError(error, { component: 'shop-waits', operation: 'check' });
    } finally {
      running = false;
    }
  };
  const timer = setInterval(() => { void run(); }, RUN_EVERY_MS);
  timer.unref?.();
  globalWaits.__deliveryShopWaits = { client: service, timer };
}

export function stopShopWaits(): void {
  const current = globalWaits.__deliveryShopWaits;
  if (current) clearInterval(current.timer);
  globalWaits.__deliveryShopWaits = undefined;
}

/** Forgets the platforms' pauses, for tests. */
export function resetShopWaitPauses(): void {
  pausedUntil.clear();
}
