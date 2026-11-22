import { afterEach, describe, expect, it, vi } from 'vitest';
import { accountLookupCountry, lookupCountry, rememberLookupCountry, withLookupCountry } from './lookupCountry';
import * as observability from './observability';
import { SupabaseError, type SupabaseServiceClient } from './supabase';
import type { JsonObject } from './types';

const request = (country?: string, ip = '198.51.100.7') => ({ headers: new Headers({ 'cf-connecting-ip': ip, ...(country ? { 'cf-ipcountry': country } : {}) }) });
afterEach(() => { vi.restoreAllMocks(); vi.unstubAllEnvs(); });

describe('lookup country hint', () => {
  it('uses only a recognized country from the trusted Cloudflare edge', () => {
    vi.stubEnv('TRUST_PROXY_HEADERS', 'true');
    expect(lookupCountry(request(' fr '))).toBe('FR');
    expect(lookupCountry(request('CH', '2001:db8::7'))).toBe('CH');
    for (const country of [undefined, 'XX', 'T1', 'ZZ', 'France', 'FR,CH']) expect(lookupCountry(request(country))).toBeNull();
    expect(lookupCountry(request('FR', 'invalid'))).toBeNull();
    expect(lookupCountry({ headers: new Headers({ 'x-real-ip': '198.51.100.7', 'cf-ipcountry': 'FR' }) })).toBeNull();
    vi.stubEnv('TRUST_PROXY_HEADERS', 'false');
    expect(lookupCountry(request('FR'))).toBeNull();
  });

  it('persists only the country before queueing, without setting the destination', async () => {
    const updatePackage = vi.fn().mockResolvedValue(undefined);
    const service = { updatePackage } as unknown as SupabaseServiceClient;
    const parcel: JsonObject = { id: 'parcel', carrier_data: { routing: { version: 1 } } };
    await rememberLookupCountry(service, parcel, 'FR');
    expect(updatePackage).toHaveBeenCalledExactlyOnceWith('parcel', { carrier_data: { routing: { version: 1 }, lookup_country_hint: 'FR', add_recognition_pending: true } });
    expect(parcel.carrier_data).not.toHaveProperty('destination_country');
  });

  it('prefers a valid device region without treating it as an IP-derived destination', () => {
    vi.stubEnv('TRUST_PROXY_HEADERS', 'true');
    expect(lookupCountry(request('FR'), ' ch ')).toBe('CH');
    vi.stubEnv('TRUST_PROXY_HEADERS', 'false');
    expect(lookupCountry(request('FR'), 'DE')).toBe('DE');
    for (const region of ['ZZ', 'XX', 'EU', 'UN', 'QO', '001', 'France', 1, {}]) expect(() => lookupCountry(request('FR'), region)).toThrow();
  });

  it('marks a new addition even when it has no country hint', async () => {
    const updatePackage = vi.fn().mockResolvedValue(undefined);
    const parcel: JsonObject = { id: 'parcel', carrier_data: {} };
    await rememberLookupCountry({ updatePackage } as unknown as SupabaseServiceClient, parcel, null);
    expect(updatePackage).toHaveBeenCalledExactlyOnceWith('parcel', { carrier_data: { add_recognition_pending: true } });
  });

  it('continues without a hint when the optional persistence fails', async () => {
    const report = vi.spyOn(observability, 'captureOperationalError').mockImplementation(() => null);
    const service = { updatePackage: vi.fn().mockRejectedValue(new SupabaseError('unavailable')) } as unknown as SupabaseServiceClient;
    const parcel: JsonObject = { id: 'parcel', carrier_data: {} };
    await rememberLookupCountry(service, parcel, 'FR');
    expect(parcel.carrier_data).toEqual({});
    expect(report).toHaveBeenCalledOnce();
  });

  it('names the country a detection answer ranked carriers for, when one is known', () => {
    const answer = { trackingNumber: '1Z999AA10123456784', carrier: 'ups' as const };
    expect(withLookupCountry(answer, 'CH')).toEqual({ ...answer, lookupCountry: 'CH' });
    expect(withLookupCountry(answer, null)).toEqual(answer);
  });

  it('takes the country of the account\'s latest parcel that has one, and none when the read fails', async () => {
    const latestLookupCountry = vi.fn().mockResolvedValueOnce('fr').mockResolvedValueOnce(null).mockResolvedValueOnce('EU');
    const service = { latestLookupCountry } as unknown as SupabaseServiceClient;
    expect(await accountLookupCountry(service, 'user')).toBe('FR');
    expect(await accountLookupCountry(service, 'user')).toBeNull();
    expect(await accountLookupCountry(service, 'user')).toBeNull();
    expect(latestLookupCountry).toHaveBeenCalledWith('user');
    const report = vi.spyOn(observability, 'captureOperationalError').mockImplementation(() => null);
    latestLookupCountry.mockRejectedValueOnce(new SupabaseError('unavailable'));
    expect(await accountLookupCountry(service, 'user')).toBeNull();
    expect(report).toHaveBeenCalledOnce();
    latestLookupCountry.mockRejectedValueOnce(new TypeError('bug'));
    await expect(accountLookupCountry(service, 'user')).rejects.toThrow(TypeError);
  });
});
