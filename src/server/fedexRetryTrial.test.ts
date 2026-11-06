import { afterEach, describe, expect, it, vi } from 'vitest';
import { retryTrialStore } from './fedexRetryTrial';
import * as observability from './observability';

afterEach(() => vi.restoreAllMocks());

describe('retry trial store', () => {
  it('loads and saves attempts through the database, and logs a failure without its message before passing it on', async () => {
    const attempt = { id: '00000000-0000-4000-8000-000000000001', trial: 'example', route: 'datacenter' as const, assignedAt: 1 };
    const client = { retryRouteTrialAttempts: vi.fn().mockResolvedValue([attempt]), saveRetryRouteTrialAttempt: vi.fn().mockResolvedValue(undefined) };
    const store = retryTrialStore(client);
    await expect(store.load('example')).resolves.toEqual([attempt]);
    await store.save(attempt);
    expect(client.saveRetryRouteTrialAttempt).toHaveBeenCalledWith(attempt);
    const log = vi.spyOn(observability, 'logOperationalEvent').mockImplementation(() => {});
    client.saveRetryRouteTrialAttempt.mockRejectedValueOnce(new TypeError('fetch failed'));
    await expect(store.save(attempt)).rejects.toThrow('fetch failed');
    expect(log).toHaveBeenCalledWith('retry_trial_store_failed', { operation: 'save', error_type: 'TypeError' }, 'warning');
  });
});
