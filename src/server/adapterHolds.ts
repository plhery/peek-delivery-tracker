import 'server-only';

import type { AdapterHoldStore } from 'universal-parcel-scraper/node';
import { errorType, logOperationalEvent } from './observability';
import type { SupabaseServiceClient } from './supabase';

/**
 * Keeps the scraper adapters' pauses and pacing in the database (`scraper_holds`), so that a
 * deploy takes up the ones still running instead of asking again what they held off: the
 * direct step's rest after a check only a browser passes, USPS's outage pause, Correios's read
 * window, iMile's day without asking Cainiao about a number. Loading drops the holds that
 * ended. A hold's key can hold a tracking number: nothing here logs it. The adapters carry on
 * without the store when it fails.
 */
export function adapterHoldStore(
  client: Pick<SupabaseServiceClient, 'adapterHolds' | 'saveAdapterHold' | 'dropEndedAdapterHolds'>,
): AdapterHoldStore {
  return {
    load: () => logged('load', async () => {
      // The ended ones go first; failing that, those still running are taken up all the same.
      await client.dropEndedAdapterHolds().catch((error: unknown) => logFailure('drop', error));
      return await client.adapterHolds();
    }),
    save: (hold) => logged('save', () => client.saveAdapterHold(hold)),
  };
}

/** Logs how many holds the scraper took up at start, or that the store could not be read. */
export function logAdapterHoldsTakenUp(taken: Promise<number>): Promise<void> {
  return taken.then(
    (count) => logOperationalEvent('adapter_holds_taken_up', { count }),
    () => undefined,
  );
}

async function logged<T>(operation: 'load' | 'save', run: () => Promise<T>): Promise<T> {
  try {
    return await run();
  } catch (error) {
    logFailure(operation, error);
    throw error;
  }
}

function logFailure(operation: 'load' | 'save' | 'drop', error: unknown): void {
  logOperationalEvent('adapter_hold_store_failed', { operation, error_type: errorType(error) }, 'warning');
}
