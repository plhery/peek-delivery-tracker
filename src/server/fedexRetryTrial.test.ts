import { afterEach, describe, expect, it, vi } from 'vitest';
import { retryTrialStore } from './fedexRetryTrial';
import * as observability from './observability';

afterEach(() => vi.restoreAllMocks());

describe('retry trial store', () => {
  it('loads, saves, reserves and ends through the database, and logs a failure without its message before passing it on', async () => {
    const attempt = { id: '00000000-0000-4000-8000-000000000001', trial: 'example', route: 'residential' as const, assignedAt: 1,
      reservedBytes: 11_000_000, chargedBytes: 11_000_000 };
    const state = { attempts: [attempt], startedAt: 1, end: { reason: 'bytes' as const, at: 5 } };
    const client = {
      retryRouteTrialState: vi.fn().mockResolvedValue(state),
      saveRetryRouteTrialAttempt: vi.fn().mockResolvedValue(undefined),
      reserveRetryRouteTrialAttempt: vi.fn().mockResolvedValue({ granted: true, startedAt: 1 }),
      endRetryRouteTrial: vi.fn().mockResolvedValue(undefined),
    };
    const store = retryTrialStore(client);
    await expect(store.load('example')).resolves.toEqual(state);
    await store.save(attempt);
    expect(client.saveRetryRouteTrialAttempt).toHaveBeenCalledWith(attempt);
    const request = { attempt, targetAttempts: 100, maxResidentialBytes: 250_000_000, durationMs: 21_600_000, leaseMs: 180_000 };
    await expect(store.reserve(request)).resolves.toEqual({ granted: true, startedAt: 1 });
    expect(client.reserveRetryRouteTrialAttempt).toHaveBeenCalledWith(request);
    await store.end('example', { reason: 'samples', at: 9 });
    expect(client.endRetryRouteTrial).toHaveBeenCalledWith('example', { reason: 'samples', at: 9 });
    const log = vi.spyOn(observability, 'logOperationalEvent').mockImplementation(() => {});
    client.reserveRetryRouteTrialAttempt.mockRejectedValueOnce(new TypeError('fetch failed'));
    await expect(store.reserve(request)).rejects.toThrow('fetch failed');
    expect(log).toHaveBeenCalledWith('retry_trial_store_failed', { operation: 'reserve', error_type: 'TypeError' }, 'warning');
  });
});
