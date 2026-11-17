import { afterEach, describe, expect, it, vi } from 'vitest';
import { standInForPerformance } from './performanceStandIn';

afterEach(() => { vi.useRealTimers(); });

describe('standInForPerformance', () => {
  it('gives a browser whose performance is null a clock from the page start', () => {
    vi.useFakeTimers({ toFake: ['Date'], now: new Date('2026-11-17T09:00:00Z') });
    const holder: { performance?: Performance | null } = { performance: null };
    standInForPerformance(holder);
    // React's scheduler checks this, and reads `now` from it, while the page loads.
    expect('object' === typeof holder.performance && 'function' === typeof holder.performance?.now).toBe(true);
    vi.setSystemTime(new Date('2026-11-17T09:00:01.250Z'));
    expect(holder.performance!.now()).toBe(1250);
    expect(holder.performance!.timeOrigin).toBe(Date.parse('2026-11-17T09:00:00Z'));
    expect(holder.performance!.getEntriesByType('navigation')).toEqual([]);
    expect(() => holder.performance!.mark('start')).not.toThrow();
  });

  it('leaves a browser with its own performance alone', () => {
    const own = { now: () => 42 } as unknown as Performance;
    const holder = { performance: own };
    standInForPerformance(holder);
    expect(holder.performance).toBe(own);
  });

  it('keeps a performance the browser will not let go', () => {
    const holder = Object.freeze({ performance: null });
    expect(() => standInForPerformance(holder)).not.toThrow();
    expect(holder.performance).toBeNull();
  });
});
