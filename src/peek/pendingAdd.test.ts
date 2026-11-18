import { afterEach, describe, expect, it } from 'vitest';
import { clearPendingAdd, PENDING_ADD_STORAGE_KEY, rememberPendingAdd, takePendingAdd } from './pendingAdd';

const now = Date.parse('2026-11-18T22:00:00.000Z');

afterEach(() => { clearPendingAdd(); });

describe('pendingAdd', () => {
  it('hands what was typed to the deliveries once, for an hour', () => {
    rememberPendingAdd('  1ZDEMO202600000001\n1ZDEMO202600000029 ', now);
    expect(window.sessionStorage.getItem(PENDING_ADD_STORAGE_KEY)).not.toBeNull();
    expect(takePendingAdd(now + 59 * 60_000)).toBe('1ZDEMO202600000001\n1ZDEMO202600000029');
    expect(takePendingAdd(now + 59 * 60_000)).toBeNull();

    rememberPendingAdd('1ZDEMO202600000001', now);
    expect(takePendingAdd(now + 60 * 60_000)).toBeNull();
  });

  it('notes nothing for an empty field, and forgets what the visitor took back', () => {
    rememberPendingAdd('   ', now);
    expect(takePendingAdd(now)).toBeNull();
    rememberPendingAdd('1ZDEMO202600000001', now);
    clearPendingAdd();
    expect(takePendingAdd(now)).toBeNull();
  });
});
