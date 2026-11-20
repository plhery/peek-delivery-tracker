import { beforeEach, describe, expect, it, vi } from 'vitest';
import { BROWSER_SOURCES, UNIVERSAL_SOURCES } from 'universal-parcel-scraper';
import { PROVIDER_HOURLY_LIMITS, providerAllowed, providerLimit, reportProviderSetting, unknownProviders } from './providerPolicy';

const observability = vi.hoisted(() => ({ log: vi.fn(), capture: vi.fn() }));
vi.mock('./observability', () => ({ logOperationalEvent: observability.log, captureProviderSetting: observability.capture }));

describe('provider policy', () => {
  it('allows every provider while the setting is unset or empty', () => {
    for (const setting of [undefined, '', '  ']) {
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
    for (const setting of ['none', 'NONE', 'Parcels App Pro', '-', ',,,', ' - , .']) {
      expect(providerAllowed('ParcelsApp', setting)).toBe(false);
      expect(providerAllowed('UPU', setting)).toBe(false);
    }
  });
});

describe('hourly limits', () => {
  it('name providers the scraper has, and give ParcelsApp less than the providers that could be lost', () => {
    const providers = new Set<string>([...UNIVERSAL_SOURCES, ...BROWSER_SOURCES]);
    expect(Object.keys(PROVIDER_HOURLY_LIMITS).filter((provider) => !providers.has(provider))).toEqual([]);
    expect(PROVIDER_HOURLY_LIMITS.ParcelsApp).toBeLessThan(PROVIDER_HOURLY_LIMITS.Ship24!);
  });
  it('leave a reserve only on checks nobody waits for, one the database accepts', () => {
    expect(providerLimit('ParcelsApp', false)).toEqual({ limit: 90, reserve: 0 });
    expect(providerLimit('ParcelsApp', true)).toEqual({ limit: 90, reserve: 15 });
    // acquire_tracking_provider refuses a reserve that leaves no lookup in half an hour of the limit.
    for (const provider of Object.keys(PROVIDER_HOURLY_LIMITS)) {
      const { limit, reserve } = providerLimit(provider, true)!;
      expect(reserve + 1).toBeLessThanOrEqual(limit / 2);
    }
  });
  it('are none for a provider without one', () => {
    expect(providerLimit('UPU', true)).toBeUndefined();
    expect(providerLimit('toString', true)).toBeUndefined();
  });
});

describe('unknown provider names', () => {
  beforeEach(() => {
    observability.log.mockReset();
    observability.capture.mockReset();
    Reflect.deleteProperty(globalThis, Symbol.for('peek.providerSettingReported'));
  });
  it('are the names that spell no provider, as written, none aside', () => {
    for (const setting of [undefined, '', ' ', ',,,', 'none', 'None', 'parcelsapp, 17track,Postal-Ninja,track 718,TRACK123,upu,ship-24']) {
      expect(unknownProviders(setting)).toEqual([]);
    }
    expect(unknownProviders(' Parcels App Pro ,Ship24,-,none')).toEqual(['Parcels App Pro', '-']);
  });
  it('are logged and reported once at start, with nothing else of the setting', () => {
    reportProviderSetting('Ship24,ParcelApp');
    reportProviderSetting('Ship24,ParcelApp');
    expect(observability.log).toHaveBeenCalledOnce();
    expect(observability.log).toHaveBeenCalledWith('provider_setting_unknown', { unknown: 'ParcelApp', providers_allowed: 1 }, 'error');
    expect(observability.capture).toHaveBeenCalledOnce();
    expect(observability.capture).toHaveBeenCalledWith(['ParcelApp'], 1);
  });
  it('say when no provider is left, and nothing is said of a setting without them', () => {
    reportProviderSetting('none');
    reportProviderSetting('ParcelsApp,Ship24');
    reportProviderSetting(undefined);
    expect(observability.log).not.toHaveBeenCalled();
    reportProviderSetting('Parcels App Pro');
    expect(observability.capture).toHaveBeenCalledWith(['Parcels App Pro'], 0);
  });
  it('never stop the server', () => {
    observability.capture.mockImplementation(() => { throw new Error('Sentry is down'); });
    expect(() => reportProviderSetting('ParcelApp')).not.toThrow();
  });
});
