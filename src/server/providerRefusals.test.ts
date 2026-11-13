import { describe, expect, it } from 'vitest';
import { ProviderRefusals } from './providerRefusals';

const MINUTE = 60_000;

describe('provider refusals', () => {
  it('finds a provider blocked once a second number it tracked comes back without history within half an hour', () => {
    const refusals = new ProviderRefusals();
    expect(refusals.refused('ParcelsApp', 'A', 0)).toBeUndefined();
    // The same number again, or another provider's refusal, is no second number.
    expect(refusals.refused('ParcelsApp', 'A', 5 * MINUTE)).toBeUndefined();
    expect(refusals.refused('17TRACK', 'B', 6 * MINUTE)).toBeUndefined();
    expect(refusals.refused('ParcelsApp', 'B', 29 * MINUTE)).toBe('blocked');
  });
  it('forgets a refusal half an hour on', () => {
    const refusals = new ProviderRefusals();
    expect(refusals.refused('ParcelsApp', 'A', 0)).toBeUndefined();
    expect(refusals.refused('ParcelsApp', 'B', 30 * MINUTE)).toBeUndefined();
  });
  it('finds a block still in place from one refusal within half an hour of its pause ending', () => {
    const refusals = new ProviderRefusals();
    refusals.refused('ParcelsApp', 'A', 0);
    expect(refusals.refused('ParcelsApp', 'B', 10 * MINUTE)).toBe('blocked');
    expect(refusals.refused('ParcelsApp', 'C', 69 * MINUTE)).toBe('blocked');
    expect(refusals.refused('ParcelsApp', 'D', 129 * MINUTE)).toBeUndefined();
  });
  it('counts a number behind a block toward no other for a day, so a number it lost does not keep it blocked', () => {
    const refusals = new ProviderRefusals();
    refusals.refused('ParcelsApp', 'A', 0);
    expect(refusals.refused('ParcelsApp', 'B', 10 * MINUTE)).toBe('blocked');
    // A's own retries at the end of each pause, and B's beside them, find neither a block nor their lack of history.
    expect(refusals.refused('ParcelsApp', 'A', 40 * MINUTE)).toBe('behind');
    expect(refusals.refused('ParcelsApp', 'B', 41 * MINUTE)).toBe('behind');
    expect(refusals.refused('ParcelsApp', 'A', 70 * MINUTE)).toBe('behind');
    // Another number still finds a block in place, and a day on A counts again.
    expect(refusals.refused('ParcelsApp', 'C', 45 * MINUTE)).toBe('blocked');
    expect(refusals.refused('ParcelsApp', 'A', 24 * 60 * MINUTE + 10 * MINUTE)).toBeUndefined();
    expect(refusals.refused('ParcelsApp', 'D', 24 * 60 * MINUTE + 20 * MINUTE)).toBe('blocked');
  });
});
