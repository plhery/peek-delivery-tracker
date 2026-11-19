import { afterEach, describe, expect, it, vi, type Mock } from 'vitest';
import { adapterHoldStore, logAdapterHoldsTakenUp } from './adapterHolds';
import * as observability from './observability';
import type { SupabaseServiceClient } from './supabase';

const UNTIL = Date.parse('2026-11-19T18:00:00Z');

afterEach(() => vi.restoreAllMocks());

type Database = Pick<SupabaseServiceClient, 'adapterHolds' | 'saveAdapterHold' | 'dropEndedAdapterHolds'>;

function client(overrides: Partial<{ [K in keyof Database]: Mock<Database[K]> }> = {}) {
  return {
    adapterHolds: vi.fn<Database['adapterHolds']>().mockResolvedValue([]),
    saveAdapterHold: vi.fn<Database['saveAdapterHold']>().mockResolvedValue(undefined),
    dropEndedAdapterHolds: vi.fn<Database['dropEndedAdapterHolds']>().mockResolvedValue(undefined),
    ...overrides,
  };
}

describe('adapter hold store', () => {
  it('drops the holds that ended, then loads those still running, and saves each hold', async () => {
    const holds = [{ key: 'direct-rest/mailamericas 1a2b3c', until: UNTIL }];
    const database = client({ adapterHolds: vi.fn<Database['adapterHolds']>().mockResolvedValue(holds) });
    const logged = vi.spyOn(observability, 'logOperationalEvent').mockImplementation(() => undefined);
    const store = adapterHoldStore(database);
    await expect(store.load()).resolves.toBe(holds);
    expect(database.dropEndedAdapterHolds.mock.invocationCallOrder[0]).toBeLessThan(database.adapterHolds.mock.invocationCallOrder[0]!);
    await store.save({ key: 'read-window/correios-br 1a2b3c', until: UNTIL, value: { reads: [1, 2] } });
    expect(database.saveAdapterHold).toHaveBeenCalledWith({ key: 'read-window/correios-br 1a2b3c', until: UNTIL, value: { reads: [1, 2] } });
    expect(logged).not.toHaveBeenCalled();
  });

  it('logs a failing database without the hold\'s key and lets the adapters carry on without it', async () => {
    const database = client({
      dropEndedAdapterHolds: vi.fn<Database['dropEndedAdapterHolds']>().mockRejectedValue(new Error('Unavailable')),
      saveAdapterHold: vi.fn<Database['saveAdapterHold']>().mockRejectedValue(new TypeError('fetch failed')),
    });
    const logged = vi.spyOn(observability, 'logOperationalEvent').mockImplementation(() => undefined);
    const store = adapterHoldStore(database);
    // Ended holds left in place still let the running ones load.
    await expect(store.load()).resolves.toEqual([]);
    await expect(store.save({ key: 'imile-cainiao-unknown/SYNTHETIC0001', until: UNTIL })).rejects.toThrow('fetch failed');
    expect(logged.mock.calls).toEqual([
      ['adapter_hold_store_failed', { operation: 'drop', error_type: 'Error' }, 'warning'],
      ['adapter_hold_store_failed', { operation: 'save', error_type: 'TypeError' }, 'warning'],
    ]);
    expect(JSON.stringify(logged.mock.calls)).not.toContain('SYNTHETIC');
  });

  it('logs how many holds the scraper took up, and nothing more when loading failed', async () => {
    const logged = vi.spyOn(observability, 'logOperationalEvent').mockImplementation(() => undefined);
    await logAdapterHoldsTakenUp(Promise.resolve(3));
    await logAdapterHoldsTakenUp(Promise.reject(new Error('Unavailable')));
    expect(logged.mock.calls).toEqual([['adapter_holds_taken_up', { count: 3 }]]);
  });
});
