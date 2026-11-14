import { describe, expect, it } from 'vitest';
import { providerAllowed } from './providerPolicy';

describe('provider policy', () => {
  it('allows every provider while the setting is unset or empty', () => {
    for (const setting of [undefined, '', '  ', ',']) {
      expect(providerAllowed('ParcelsApp', setting)).toBe(true);
      expect(providerAllowed('Track123', setting)).toBe(true);
    }
  });
  it('allows only the providers the setting names, however it spells them', () => {
    const setting = 'parcelsapp, 17track,Postal-Ninja';
    expect(['ParcelsApp', 'Ship24', '17TRACK', 'Postal Ninja', 'UPU', 'Track718', 'Track123']
      .filter((provider) => providerAllowed(provider, setting))).toEqual(['ParcelsApp', '17TRACK', 'Postal Ninja']);
  });
  it('allows nobody for none, or for a name of no provider', () => {
    for (const setting of ['none', 'NONE', 'Parcels App Pro']) {
      expect(providerAllowed('ParcelsApp', setting)).toBe(false);
      expect(providerAllowed('UPU', setting)).toBe(false);
    }
  });
});
