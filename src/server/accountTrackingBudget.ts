import 'server-only';
import { HttpError } from './api';
import { reportOverallAllowance, secondsUntilUtcMidnight } from './publicParcels';
import type { SupabaseServiceClient } from './supabase';

/**
 * Parcel additions (`lookup`) and numbers carriers are asked about
 * (`detection`) per account and UTC day, and by every account together per UTC
 * hour: accounts cost nothing to make. 0 turns that work off.
 */
const DEFAULTS = {
  lookup: { perAccount: 15, overall: 50 },
  detection: { perAccount: 60, overall: 200 },
} as const;
/** The share of the hourly allowance past which the server says it is running out. */
const RUNNING_OUT = 0.5;

function setting(name: string, fallback: number): number {
  const configured = process.env[name];
  return configured && /^\d{1,6}$/.test(configured) ? Number(configured) : fallback;
}

export function accountTrackingLimits(kind: 'lookup' | 'detection'): { perAccount: number; overall: number } {
  const name = kind === 'lookup' ? 'LOOKUPS' : 'DETECTIONS';
  return {
    perAccount: setting(`ACCOUNT_${name}_PER_DAY`, DEFAULTS[kind].perAccount),
    overall: setting(`ACCOUNT_${name}_PER_HOUR`, DEFAULTS[kind].overall),
  };
}

export function secondsUntilNextUtcHour(now: Date): number {
  const hour = 3_600_000;
  return Math.max(1, Math.ceil(((Math.floor(now.getTime() / hour) + 1) * hour - now.getTime()) / 1_000));
}

/**
 * Counts one use before the work starts, or refuses it: until the next hour
 * when every account's allowance is used up, until midnight UTC when the
 * account's own is.
 */
export async function claimAccountTracking(service: SupabaseServiceClient, userId: string, kind: 'lookup' | 'detection') {
  const now = new Date();
  const limits = accountTrackingLimits(kind);
  const claimed = await service.claimAccountTracking(userId, kind, limits);
  const overallUsedUp = !claimed.allowed && claimed.scope === 'overall';
  await reportOverallAllowance(service, { holder: 'account', kind, period: 'hour', limit: limits.overall, runningOut: RUNNING_OUT },
    claimed.overallUsed, overallUsedUp, now);
  if (claimed.allowed) return;
  if (overallUsedUp) {
    throw new HttpError(429, 'Too many requests. Try again within the hour.', {
      'Retry-After': String(secondsUntilNextUtcHour(now)),
    });
  }
  throw new HttpError(429, 'Today’s tracking allowance is used up. Try again tomorrow.', {
    'Retry-After': String(secondsUntilUtcMidnight(now)),
  });
}
