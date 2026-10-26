import { NextRequest } from 'next/server';
import { afterEach, beforeEach, expect, it, vi } from 'vitest';
import { PATCH } from '../../app/api/packages/[id]/carrier/route';
import { SupabaseAuthenticator } from './auth';
import { SupabaseServiceClient, SupabaseUserClient } from './supabase';
import * as metrics from './metrics';
vi.mock('./background', () => ({ wakeSyncWorker: vi.fn() }));
const id = '55000000-0000-4000-a000-000000000002';
const parcel = { id, tracking_number: '1234567891', carrier: 'unknown', tracking_url: null, dpd_postcode: null, archived_at: null,
  carrier_data: { routing: { version: 1, provider_input_needed: { provider: 'ParcelsApp', field: 'dpdPostcode' } } } };
const patch = (body: object) => PATCH(new NextRequest(`https://delivery.example/api/packages/${id}/carrier`, {
  method: 'PATCH', headers: { Authorization: 'Bearer synthetic-token', 'Content-Type': 'application/json' }, body: JSON.stringify(body),
}), { params: Promise.resolve({ id }) });
beforeEach(() => {
  vi.stubEnv('SUPABASE_URL', 'https://database.example'); vi.stubEnv('SUPABASE_PUBLISHABLE_KEY', 'public-key'); vi.stubEnv('SUPABASE_SERVICE_ROLE_KEY', 'service-key');
  vi.spyOn(SupabaseAuthenticator.prototype, 'validate').mockResolvedValue({ id: '55000000-0000-4000-a000-000000000001', email: null, authenticatedAt: null, sessionId: null });
  vi.spyOn(SupabaseUserClient.prototype, 'getPackage').mockResolvedValue(parcel);
  vi.spyOn(SupabaseUserClient.prototype, 'setProviderPostcode').mockResolvedValue(true);
  vi.spyOn(SupabaseUserClient.prototype, 'changePackageCarrier').mockResolvedValue(true);
  vi.spyOn(SupabaseServiceClient.prototype, 'enqueueSyncJob').mockResolvedValue({ row: { id: '55000000-0000-4000-a000-000000000003' }, queued: true });
  vi.spyOn(console, 'log').mockImplementation(() => undefined);
  vi.spyOn(metrics, 'recordProviderInput').mockImplementation(() => undefined);
});
afterEach(() => { vi.restoreAllMocks(); vi.unstubAllEnvs(); });
it.each(['ParcelsApp', '17TRACK'])('saves normalized %s input through the owner RPC and queues a fresh check without resetting the carrier', async provider => {
  vi.mocked(SupabaseUserClient.prototype.getPackage).mockResolvedValue({ ...parcel,
    carrier_data: { routing: { version: 1, provider_input_needed: { provider, field: 'dpdPostcode' } } } });
  expect((await patch({ carrier: 'unknown', providerPostcode: 'm5v 3l9' })).status).toBe(200);
  expect(SupabaseUserClient.prototype.setProviderPostcode).toHaveBeenCalledWith(id, 'M5V 3L9');
  expect(SupabaseUserClient.prototype.changePackageCarrier).not.toHaveBeenCalled();
  expect(SupabaseServiceClient.prototype.enqueueSyncJob).toHaveBeenCalledOnce();
  expect(metrics.recordProviderInput).toHaveBeenCalledWith(provider, 'supplied');
});
it('rejects malformed input and a combined carrier change before any mutation', async () => {
  expect((await patch({ carrier: 'unknown', providerPostcode: '<script>123' })).status).toBe(400);
  expect((await patch({ carrier: 'dhl-express', providerPostcode: '8000' })).status).toBe(400);
  expect(SupabaseUserClient.prototype.setProviderPostcode).not.toHaveBeenCalled();
  expect(SupabaseServiceClient.prototype.enqueueSyncJob).not.toHaveBeenCalled();
  expect(metrics.recordProviderInput).not.toHaveBeenCalled();
});
it('does not mutate a parcel the authenticated user cannot read', async () => {
  vi.mocked(SupabaseUserClient.prototype.getPackage).mockResolvedValue(null);
  expect((await patch({ carrier: 'unknown', providerPostcode: '8000' })).status).toBe(404);
  expect(SupabaseUserClient.prototype.setProviderPostcode).not.toHaveBeenCalled();
});
it('keeps the country of a postcode from abroad with the postcode the change stores, and ends the carrier’s wait', async () => {
  const postnl = { ...parcel, tracking_number: '3SABCD826405193', carrier_data: {} };
  const stored = { ...postnl, carrier: 'spring-gds', dpd_postcode: '1000', carrier_data: {
    routing: { version: 1, failures: { 'spring-gds': { count: 1 }, dhl: { count: 1 } }, next_check_at: '2026-10-24T09:00:00Z' } } };
  vi.mocked(SupabaseUserClient.prototype.getPackage).mockResolvedValueOnce(postnl).mockResolvedValue(stored);
  const update = vi.spyOn(SupabaseServiceClient.prototype, 'updatePackage').mockResolvedValue(undefined);
  expect((await patch({ carrier: 'spring-gds', dpdPostcode: '1000', dpdPostcodeCountry: 'BE' })).status).toBe(200);
  expect(SupabaseUserClient.prototype.changePackageCarrier).toHaveBeenCalledWith(id, 'spring-gds', null, '1000');
  expect(update).toHaveBeenCalledExactlyOnceWith(id, {
    carrier_data: { routing: { version: 1, failures: { dhl: { count: 1 } } }, postcode_country: { postcode: '1000', country: 'BE' } },
    dpd_postcode: '1000',
  });
  expect(update.mock.invocationCallOrder[0])
    .toBeLessThan(vi.mocked(SupabaseServiceClient.prototype.enqueueSyncJob).mock.invocationCallOrder[0]!);
  // The same postcode from the same country changes nothing; from the carrier's home it goes without one.
  update.mockClear();
  vi.mocked(SupabaseUserClient.prototype.changePackageCarrier).mockClear();
  const abroad = { ...stored, carrier_data: { postcode_country: { postcode: '1000', country: 'BE' } } };
  vi.mocked(SupabaseUserClient.prototype.getPackage).mockReset().mockResolvedValue(abroad);
  expect((await patch({ carrier: 'spring-gds', dpdPostcode: '1000', dpdPostcodeCountry: 'BE' })).status).toBe(200);
  expect(SupabaseUserClient.prototype.changePackageCarrier).not.toHaveBeenCalled();
  expect((await patch({ carrier: 'spring-gds', dpdPostcode: '1000' })).status).toBe(200);
  expect(SupabaseUserClient.prototype.changePackageCarrier).toHaveBeenCalledWith(id, 'spring-gds', null, '1000');
  expect(update).toHaveBeenCalledExactlyOnceWith(id, { carrier_data: {}, dpd_postcode: '1000' });
});
