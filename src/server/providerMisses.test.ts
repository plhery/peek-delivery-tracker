import { describe, expect, it } from 'vitest';
import { ProviderMisses } from './providerMisses';

const MINUTE = 60_000;

describe('provider misses', () => {
  it('spends a provider’s budget on its last answer without history in half an hour', () => {
    const misses = new ProviderMisses({ ParcelsApp: 3 });
    expect(misses.missed('ParcelsApp', 0)).toBe(false);
    expect(misses.missed('ParcelsApp', MINUTE)).toBe(false);
    expect(misses.spent('ParcelsApp', 2 * MINUTE)).toBe(false);
    expect(misses.missed('ParcelsApp', 2 * MINUTE)).toBe(true);
    expect(misses.spent('ParcelsApp', 3 * MINUTE)).toBe(true);
    // The first answer ages out half an hour on.
    expect(misses.spent('ParcelsApp', 30 * MINUTE)).toBe(false);
  });
  it('gives a provider without a budget as many answers as it likes', () => {
    const misses = new ProviderMisses({ ParcelsApp: 1 });
    for (let minute = 0; minute < 10; minute++) expect(misses.missed('17TRACK', minute * MINUTE)).toBe(false);
    expect(misses.spent('17TRACK', 10 * MINUTE)).toBe(false);
  });
  it('reports a spent budget once in half an hour', () => {
    const misses = new ProviderMisses({ ParcelsApp: 2 });
    misses.missed('ParcelsApp', 0);
    expect(misses.missed('ParcelsApp', MINUTE)).toBe(true);
    // Still spent, or spent again soon after it freed up: no new report.
    expect(misses.missed('ParcelsApp', 2 * MINUTE)).toBe(false);
    expect(misses.missed('ParcelsApp', 31 * MINUTE)).toBe(false);
    // Half an hour after the last spending answer, spending it is news again.
    misses.missed('ParcelsApp', 70 * MINUTE);
    expect(misses.missed('ParcelsApp', 72 * MINUTE)).toBe(true);
  });
});
