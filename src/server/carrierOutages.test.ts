import { afterEach, describe, expect, it, vi } from 'vitest';
import { forgetCarrierOutages, loadCarrierOutages } from './carrierOutages';
import { withEventPlaces } from './eventPlaces';
import { publicParcelResponse } from './publicParcels';
import type { JsonObject } from './types';

const SINCE = '2026-11-19T12:20:41.000Z';
const NOW = Date.parse('2026-11-19T18:00:00Z');
const client = (outages: Record<string, string>) => ({ carrierOutages: vi.fn(async () => new Map(Object.entries(outages))) });

function parcel(overrides: JsonObject = {}): JsonObject {
  return {
    id: 'parcel', tracking_number: 'TESTCP00001', carrier: 'colis-prive', sync_status: 'error', sync_error: 'carrier:transport',
    created_at: '2026-11-18T08:00:00Z', carrier_data: { routing: { last_success_at: '2026-11-19T11:20:00Z' } },
    tracking_events: [{ id: 'scan', stage: 'in_transit', description: 'Colis en transit', location: null,
      occurred_at: '2026-11-19T07:00:00Z', created_at: '2026-11-19T07:05:00Z' }],
    ...overrides,
  };
}

afterEach(() => {
  forgetCarrierOutages();
  vi.restoreAllMocks();
});

describe('carrier outages', () => {
  it('tells a waiting parcel its carrier is down, with the carrier\'s page for the number, until the carrier answers again', async () => {
    const down = client({ 'colis-prive': SINCE });
    await loadCarrierOutages(down, NOW);
    expect(withEventPlaces(parcel()).carrier_outage).toEqual({
      carrier: 'colis-prive', since: SINCE,
      tracking_url: 'https://colisprive.com/moncolis/pages/DetailColis.aspx?numColis=TESTCP00001&lang=fr',
    });
    // Read once a minute: the list a minute old stands until then.
    await loadCarrierOutages(down, NOW + 30_000);
    expect(down.carrierOutages).toHaveBeenCalledTimes(1);

    const back = client({});
    await loadCarrierOutages(back, NOW + 60_000);
    await vi.waitFor(() => expect(withEventPlaces(parcel())).not.toHaveProperty('carrier_outage'));
    expect(back.carrierOutages).toHaveBeenCalledTimes(1);
  });

  it('says nothing for a parcel whose own check failed while its carrier answers others', async () => {
    await loadCarrierOutages(client({ nacex: SINCE }), NOW);
    expect(withEventPlaces(parcel())).not.toHaveProperty('carrier_outage');
    expect(withEventPlaces(parcel({ carrier: 'nacex', tracking_number: 'TESTNX00001' })).carrier_outage).toMatchObject({ carrier: 'nacex' });
    // Another source answered for it since the site went down: it is not waiting for the carrier.
    expect(withEventPlaces(parcel({ carrier: 'nacex', tracking_number: 'TESTNX00001',
      carrier_data: { routing: { last_success_at: '2026-11-19T13:00:00Z' } } }))).not.toHaveProperty('carrier_outage');
  });

  it('says nothing once the parcel is delivered or returned', async () => {
    await loadCarrierOutages(client({ 'colis-prive': SINCE }), NOW);
    for (const stage of ['delivered', 'returned']) {
      const ended = parcel({ tracking_events: [...parcel().tracking_events as JsonObject[], { id: 'end', stage, description: 'Fin',
        location: null, occurred_at: '2026-11-19T10:00:00Z', created_at: '2026-11-19T10:05:00Z' }] });
      expect(withEventPlaces(ended)).not.toHaveProperty('carrier_outage');
    }
  });

  it('serves parcels without it when the list cannot be read, and keeps a list read lately', async () => {
    const failing = { carrierOutages: vi.fn(async () => { throw new Error('down'); }) };
    await loadCarrierOutages(failing, NOW);
    expect(withEventPlaces(parcel())).not.toHaveProperty('carrier_outage');
    await loadCarrierOutages(client({ 'colis-prive': SINCE }), NOW + 1);
    await loadCarrierOutages(failing, NOW + 120_000);
    await vi.waitFor(() => expect(failing.carrierOutages).toHaveBeenCalledTimes(2));
    expect(withEventPlaces(parcel()).carrier_outage).toBeDefined();
  });

  it('leaves the carrier\'s page out for a link\'s viewer not shown the number, and says nothing on a wrapped gift', async () => {
    await loadCarrierOutages(client({ 'colis-prive': SINCE }), NOW);
    const alerts = { available: true, vapidPublicKey: `B${'A'.repeat(86)}` };
    const shown = (link: JsonObject) => publicParcelResponse({
      link: { id: 'k7Qm2xHd9RtW', created_at: '2026-11-18T08:00:00Z', forget_at: null, ...link }, package: parcel(),
    } as Parameters<typeof publicParcelResponse>[0], alerts).package;
    expect(shown({ owner: true }).carrier_outage?.tracking_url).toContain('TESTCP00001');
    expect(shown({ owner: false, show_number: true }).carrier_outage?.tracking_url).toContain('TESTCP00001');
    expect(shown({ owner: false }).carrier_outage).toEqual({ carrier: 'colis-prive', since: SINCE });
    expect(JSON.stringify(shown({ owner: false }))).not.toContain('TESTCP00001');
    expect(shown({ owner: false, gift: true, show_number: true })).not.toHaveProperty('carrier_outage');
  });
});
