import { normalizeTrackingNumber } from '../lib/carriers';
import { captureOperationalError } from './observability';
import { SupabaseError, type SupabaseServiceClient } from './supabase';
import type { JsonObject } from './types';

/** How long a number half typed in Add stays replaceable, as for a lookup without an account (src/peek/recents.ts). */
export const HALF_TYPED_MS = 10 * 60_000;

/**
 * Notes the sign-in an account's parcel was just added from with Add, and deletes for good the account's parcels whose
 * number the new one starts with that the same sign-in added at most 10 minutes before, while they have no scan yet,
 * no name and no archive date: they were the number half typed. Older parcels, and ones added another way or from
 * another sign-in, stay. A token that names no sign-in replaces nothing. Returns the ids deleted; the account's apps
 * drop them with their next read of the list.
 */
export async function replaceHalfTyped(
  service: SupabaseServiceClient,
  userId: string,
  sessionId: string | null,
  added: { id: string; trackingNumber: string },
  now = new Date(),
): Promise<string[]> {
  if (!sessionId) return [];
  try {
    await service.markAddedSession(userId, added.id, sessionId);
    const since = new Date(now.getTime() - HALF_TYPED_MS);
    const typed = normalizeTrackingNumber(added.trackingNumber);
    const earlier = (await service.listAddedFromSession(userId, sessionId, since))
      .filter((row) => row.id !== added.id && halfTyped(row, typed));
    const deleted: string[] = [];
    for (const row of earlier) {
      if (await service.deleteHalfTypedPackage(userId, sessionId, String(row.id), since)) deleted.push(String(row.id));
    }
    return deleted;
  } catch (error) {
    // The parcel is added either way; the earlier one stays for the user to delete.
    if (!(error instanceof SupabaseError)) throw error;
    captureOperationalError(error, { component: 'packages', operation: 'replace_half_typed' });
    return [];
  }
}

function halfTyped(row: JsonObject, typed: string): boolean {
  const start = normalizeTrackingNumber(String(row.tracking_number ?? ''));
  const events = Array.isArray(row.tracking_events) ? row.tracking_events : [];
  return start.length > 0 && typed.length > start.length && typed.startsWith(start)
    && row.label === '' && row.archived_at === null && row.current_stage === 'pending'
    && events.every((event) => typeof event === 'object' && event !== null && (event as JsonObject).stage === 'pending');
}
