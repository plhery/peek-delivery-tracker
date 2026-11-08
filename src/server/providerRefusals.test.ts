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
});
