import { afterEach, expect, it, vi } from 'vitest';
import { claimReload } from './pageReload';

afterEach(() => { sessionStorage.clear(); vi.restoreAllMocks(); });

it('lets a tab reload for one cause once in its window', () => {
  const start = Date.UTC(2026, 0, 1);
  expect(claimReload('test.reload', 60_000, start)).toBe(true);
  expect(claimReload('test.reload', 60_000, start + 59_000)).toBe(false);
  expect(claimReload('test.other', 60_000, start + 59_000)).toBe(true);
  expect(claimReload('test.reload', 60_000, start + 60_000)).toBe(true);
});

it('never reloads when it cannot remember the reload', () => {
  vi.spyOn(Storage.prototype, 'setItem').mockImplementation(() => { throw new Error('full'); });
  expect(claimReload('test.reload', 60_000, Date.UTC(2026, 0, 1))).toBe(false);
});
