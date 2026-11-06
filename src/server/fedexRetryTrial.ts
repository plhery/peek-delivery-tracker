import 'server-only';

import { fedexRetryTrial, type RetryRouteTrial, type RetryRouteTrialStore } from 'universal-parcel-scraper/node';
import { setRetryTrialSource } from './metrics';
import { errorType, logOperationalEvent } from './observability';
import { serviceClient } from './runtime';
import type { SupabaseServiceClient } from './supabase';

/**
 * Keeps the FedEx retry trial in `retry_route_trial_attempts` and
 * `retry_route_trials`, so a deploy carries its counts, bytes, deadline and end
 * on. A residential attempt begins only once `reserve_retry_route_trial_attempt`
 * has saved its reservation under the trial's lock, so every process counts
 * against one cap; the scraper assigns only the datacenter route while the store
 * fails or is slow.
 */
export function retryTrialStore(client: Pick<SupabaseServiceClient,
  'retryRouteTrialState' | 'saveRetryRouteTrialAttempt' | 'reserveRetryRouteTrialAttempt' | 'endRetryRouteTrial'>): RetryRouteTrialStore {
  return {
    load: (trial) => logged('load', () => client.retryRouteTrialState(trial)),
    save: (attempt) => logged('save', () => client.saveRetryRouteTrialAttempt(attempt)),
    reserve: (request) => logged('reserve', () => client.reserveRetryRouteTrialAttempt(request)),
    end: (trial, end) => logged('end', () => client.endRetryRouteTrial(trial, end)),
  };
}

async function logged<T>(operation: 'load' | 'save' | 'reserve' | 'end', run: () => Promise<T>): Promise<T> {
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
