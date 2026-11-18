import 'server-only';

import { wakeSyncWorker } from './background';
import { SupabaseError, type SupabaseServiceClient } from './supabase';

/**
 * The check a carrier change starts, by the edit sheet or an assistant: the parcel's new carrier is asked at once. When
 * the check can't be queued the parcel says so. Returns the queued job's id.
 */
export async function queueCarrierCheck(service: SupabaseServiceClient, userId: string, packageId: string): Promise<string[]> {
  try {
    const job = await service.enqueueSyncJob({ userId, packageId, reason: 'changed' });
    wakeSyncWorker();
    return typeof job.row.id === 'string' ? [job.row.id] : [];
  } catch (error) {
    if (!(error instanceof SupabaseError)) throw error;
    await service.updatePackage(packageId, {
      sync_status: 'error',
      sync_error: 'The tracking check for the new carrier could not be queued. Try again shortly.',
    });
    return [];
  }
}
