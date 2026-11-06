import 'server-only';

import { fedexRetryTrial, type RetryRouteTrial, type RetryRouteTrialStore } from 'universal-parcel-scraper/node';
import { setRetryTrialSource } from './metrics';
import { errorType, logOperationalEvent } from './observability';
import { serviceClient } from './runtime';
import type { SupabaseServiceClient } from './supabase';

/**
 * Keeps the FedEx retry trial's attempts in `retry_route_trial_attempts`, so a
 * deploy carries its counts, bytes and deadline on. The scraper assigns only the
 * datacenter route while the store fails.
 */
export function retryTrialStore(client: Pick<SupabaseServiceClient, 'retryRouteTrialAttempts' | 'saveRetryRouteTrialAttempt'>): RetryRouteTrialStore {
  return {
    load: (trial) => logged('load', () => client.retryRouteTrialAttempts(trial)),
    save: (attempt) => logged('save', () => client.saveRetryRouteTrialAttempt(attempt)),
  };
}

async function logged<T>(operation: 'load' | 'save', run: () => Promise<T>): Promise<T> {
  try {
    return await run();
  } catch (error) {
    logOperationalEvent('retry_trial_store_failed', { operation, error_type: errorType(error) }, 'warning');
    throw error;
  }
}

const globalTrial = globalThis as typeof globalThis & { __deliveryRetryTrialStore?: { client: SupabaseServiceClient; store: RetryRouteTrialStore } };

/**
 * The store every adapter environment of the process shares, so all of them count
 * against one trial; null without the database or a trial name.
 */
export function hostRetryTrialStore(env: Record<string, string | undefined>): RetryRouteTrialStore | null {
  if (!env.FEDEX_RETRY_TRIAL?.trim()) return null;
  const client = serviceClient();
  if (!client) return null;
  if (globalTrial.__deliveryRetryTrialStore?.client !== client) {
    globalTrial.__deliveryRetryTrialStore = { client, store: retryTrialStore(client) };
  }
  const { store } = globalTrial.__deliveryRetryTrialStore;
  const trial: RetryRouteTrial | null = fedexRetryTrial(env, store);
  if (trial) setRetryTrialSource(() => trial.snapshot());
  return store;
}
