import { describe, expect, it } from 'vitest';
import { ProviderRefusals } from './providerRefusals';

const MINUTE = 60_000;

describe('provider refusals', () => {
  it('finds a provider blocked once a second number it tracked comes back without history within half an hour', () => {
    const refusals = new ProviderRefusals();
    expect(refusals.refused('ParcelsApp', 'A', 0)).toBe(false);
    // The same number again, or another provider's refusal, is no second number.
    expect(refusals.refused('ParcelsApp', 'A', 5 * MINUTE)).toBe(false);
    expect(refusals.refused('17TRACK', 'B', 6 * MINUTE)).toBe(false);
    expect(refusals.refused('ParcelsApp', 'B', 29 * MINUTE)).toBe(true);
  });
  it('forgets a refusal half an hour on', () => {
    const refusals = new ProviderRefusals();
    expect(refusals.refused('ParcelsApp', 'A', 0)).toBe(false);
    expect(refusals.refused('ParcelsApp', 'B', 30 * MINUTE)).toBe(false);
  });
  it('finds a block still in place from one refusal within half an hour of its pause ending', () => {
    const refusals = new ProviderRefusals();
    refusals.refused('ParcelsApp', 'A', 0);
    expect(refusals.refused('ParcelsApp', 'B', 10 * MINUTE)).toBe(true);
    expect(refusals.refused('ParcelsApp', 'C', 69 * MINUTE)).toBe(true);
    expect(refusals.refused('ParcelsApp', 'D', 129 * MINUTE)).toBe(false);
  });
  it('counts a number behind a block toward no other for a day, so a number it lost does not keep it blocked', () => {
    const refusals = new ProviderRefusals();
    refusals.refused('ParcelsApp', 'A', 0);
    expect(refusals.refused('ParcelsApp', 'B', 10 * MINUTE)).toBe(true);
    // A's own retries at the end of each pause, and B's beside them.
    expect(refusals.refused('ParcelsApp', 'A', 40 * MINUTE)).toBe(false);
    expect(refusals.refused('ParcelsApp', 'B', 41 * MINUTE)).toBe(false);
    expect(refusals.refused('ParcelsApp', 'A', 70 * MINUTE)).toBe(false);
    // Another number still finds a block in place, and a day on A counts again.
    expect(refusals.refused('ParcelsApp', 'C', 45 * MINUTE)).toBe(true);
    expect(refusals.refused('ParcelsApp', 'A', 24 * 60 * MINUTE + 10 * MINUTE)).toBe(false);
    expect(refusals.refused('ParcelsApp', 'D', 24 * 60 * MINUTE + 20 * MINUTE)).toBe(true);
  });
});
