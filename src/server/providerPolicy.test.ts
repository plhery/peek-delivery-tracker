import { beforeEach, describe, expect, it, vi } from 'vitest';
import { providerAllowed, reportProviderSetting, unknownProviders } from './providerPolicy';

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
