import { afterEach, describe, expect, it, vi } from 'vitest';
import { forgetProviderCoverage, loadProviderCoverage, observedCoverage } from './providerCoverage';
import * as observability from './observability';

const NOW = Date.parse('2026-11-19T18:00:00Z');
const HOUR = 60 * 60_000;
const counts = (history: number) => new Map([['usps', { ParcelsApp: { history, missed: 0 }, Ship24: { history: 1, missed: 27 } }]]);
const client = (history = 12) => ({ providerCoverage: vi.fn(async () => counts(history)) });

afterEach(() => {
  forgetProviderCoverage();
  vi.restoreAllMocks();
});

describe('provider coverage', () => {
  it('gives a carrier\'s counts once read, and reads them again an hour later', async () => {
    expect(observedCoverage('usps')).toBeUndefined();
    const first = client();
    await loadProviderCoverage(first, NOW);
    expect(observedCoverage('usps')).toEqual({ ParcelsApp: { history: 12, missed: 0 }, Ship24: { history: 1, missed: 27 } });
    expect(observedCoverage('fedex')).toBeUndefined();
    await loadProviderCoverage(first, NOW + HOUR - 1);
    expect(first.providerCoverage).toHaveBeenCalledTimes(1);
    await loadProviderCoverage(client(40), NOW + HOUR);
    expect(observedCoverage('usps')?.ParcelsApp).toEqual({ history: 40, missed: 0 });
  });

  it('keeps no check waiting: the counts read last stand while the next are read', async () => {
    await loadProviderCoverage(client(), NOW);
    let answer!: (value: ReturnType<typeof counts>) => void;
    const slow = { providerCoverage: vi.fn(() => new Promise<ReturnType<typeof counts>>((resolve) => { answer = resolve; })) };
    const reading = loadProviderCoverage(slow, NOW + HOUR);
    // A second check meanwhile starts no second read.
    void loadProviderCoverage(slow, NOW + HOUR + 1);
    await Promise.resolve();
    expect(slow.providerCoverage).toHaveBeenCalledTimes(1);
    expect(observedCoverage('usps')?.ParcelsApp).toEqual({ history: 12, missed: 0 });
    answer(counts(40));
    await reading;
    expect(observedCoverage('usps')?.ParcelsApp).toEqual({ history: 40, missed: 0 });
  });

  it('keeps the counts a day when they cannot be read again, asking again five minutes after a failure', async () => {
    const logged = vi.spyOn(observability, 'logOperationalEvent').mockImplementation(() => undefined);
    await loadProviderCoverage(client(), NOW);
    const down = { providerCoverage: vi.fn(async (): Promise<ReturnType<typeof counts>> => { throw new TypeError('Invalid provider coverage response'); }) };
    await loadProviderCoverage(down, NOW + HOUR);
    expect(logged).toHaveBeenCalledWith('provider_coverage_unread', { error_type: 'TypeError' }, 'warning');
    expect(observedCoverage('usps')).toBeDefined();
    await loadProviderCoverage(down, NOW + HOUR + 4 * 60_000);
    expect(down.providerCoverage).toHaveBeenCalledTimes(1);
    await loadProviderCoverage(down, NOW + HOUR + 5 * 60_000);
    expect(down.providerCoverage).toHaveBeenCalledTimes(2);
    expect(observedCoverage('usps')).toBeDefined();
    await loadProviderCoverage(down, NOW + 24 * HOUR);
    expect(observedCoverage('usps')).toBeUndefined();
  });

  it('routes without counts when the client cannot read them', async () => {
    vi.spyOn(observability, 'logOperationalEvent').mockImplementation(() => undefined);
    await expect(loadProviderCoverage({} as Parameters<typeof loadProviderCoverage>[0], NOW)).resolves.toBeUndefined();
    await loadProviderCoverage({ providerCoverage: vi.fn(async () => undefined as never) }, NOW + 5 * 60_000);
    expect(observedCoverage('usps')).toBeUndefined();
  });
});
