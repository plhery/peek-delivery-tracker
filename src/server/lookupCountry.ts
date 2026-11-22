import 'server-only';
import { isIP } from 'node:net';
import { captureOperationalError } from './observability';
import { HttpError } from './api';
import { isLocale } from '../lib/locale';
import { withPostcodeCountry } from './postcodeCountry';
import { SupabaseError, type SupabaseServiceClient } from './supabase';
import type { JsonObject } from './types';
import type { ApiCarrierDetectionResponse } from '../generated/apiContract';

const names = new Intl.DisplayNames(['en'], { type: 'region' });
const groupedRegions = new Set(['EU', 'UN', 'QO', 'ZZ', 'XA', 'XB']);

export function normalizedLookupCountry(value: unknown): string | null {
  const code = typeof value === 'string' ? value.trim().toUpperCase() : '';
  return /^[A-Z]{2}$/.test(code) && !groupedRegions.has(code) && names.of(code) !== code ? code : null;
}

/**
 * A weak country hint, never a delivery destination: the region the client sends (the apps' device region, the web's
 * time-zone country, or a country the reader picked for this number), else the trusted Cloudflare edge's.
 */
export function lookupCountry(request: Pick<Request, 'headers'>, deviceRegion?: unknown): string | null {
  if (deviceRegion != null) {
    const code = normalizedLookupCountry(deviceRegion);
    if (!code) throw new HttpError(400, 'Use a two-letter country code for the device region');
    return code;
  }
  if (process.env.TRUST_PROXY_HEADERS !== 'true' || !isIP(request.headers.get('cf-connecting-ip')?.trim() ?? '')) return null;
  return normalizedLookupCountry(request.headers.get('cf-ipcountry'));
}

/** A detection answer with the country it ranked carriers for, which the Add sheet names. */
export function withLookupCountry(answer: ApiCarrierDetectionResponse, country: string | null): ApiCarrierDetectionResponse {
  return country ? { ...answer, lookupCountry: country } : answer;
}

/**
 * The country an account adds a parcel from when no request of the reader's comes with it, as for a forwarded email
 * or an assistant: the lookup country of the account's latest parcel that has one. None when its parcels have none,
 * or the read fails.
 */
export async function accountLookupCountry(service: SupabaseServiceClient, userId: string): Promise<string | null> {
  try {
    return normalizedLookupCountry(await service.latestLookupCountry(userId));
  } catch (error) {
    if (!(error instanceof SupabaseError)) throw error;
    captureOperationalError(error, { component: 'packages', operation: 'account_lookup_country' });
    return null;
  }
}

/**
 * Called only for a newly created parcel, before its first sync is queued, with its lookup country
 * (`lookupCountry`, or `accountLookupCountry` for a parcel added from a text). It also keeps the country of a
 * postcode from abroad (`postcodeCountry`) with it, and the app language a lookup without an account was made in
 * (`lookup_language`).
 */
export async function rememberLookupCountry(service: SupabaseServiceClient, parcel: JsonObject, country: string | null,
  postcodeCountry?: string | null, language?: unknown): Promise<void> {
  const data = { ...withPostcodeCountry(parcel.carrier_data, parcel.dpd_postcode, postcodeCountry),
    add_recognition_pending: true, ...(country ? { lookup_country_hint: country } : {}),
    ...(isLocale(language) ? { lookup_language: language } : {}) };
  try {
    await service.updatePackage(String(parcel.id), { carrier_data: data });
    parcel.carrier_data = data;
  } catch (error) {
    if (!(error instanceof SupabaseError)) throw error;
    captureOperationalError(error, { component: 'packages', operation: 'remember_lookup_country' });
  }
}
