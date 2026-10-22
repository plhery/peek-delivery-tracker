import { afterEach, describe, expect, it, vi } from 'vitest';
import type { CarrierResult } from 'universal-parcel-scraper';
import { retainDetectionSupport, trackingSupportContext, trackingSupportEvidence } from './trackingSupport';
import { SupabaseServiceClient } from './supabase';

// A UPS number its check digit confirms.
const number = '1Z0000000012345670';
const parcel = { tracking_number: number, carrier: 'ups' };
const progress: CarrierResult = {
  status: 'in_transit', current_stage: 'in_transit', last_update: '2026-09-10T11:00:00Z',
  events: [{ time: '2026-09-10T11:00:00Z', description: 'Departed facility', stage: 'in_transit' }],
};

describe('detection support retention', () => {
  afterEach(() => vi.restoreAllMocks());

  it('records choices separately from a number nobody recognizes, without direct verification', async () => {
    vi.spyOn(console, 'log').mockImplementation(() => undefined);
    const client = new SupabaseServiceClient('https://database.example', 'service-key');
    const retain = vi.spyOn(client, 'recordTrackingSupportObservation').mockResolvedValue(undefined);
    await retainDetectionSupport(client, {
      trackingNumber: '12345678901231', carrier: 'unknown', recognized: ['dpd', 'hermes-de'],
    });
    expect(retain).toHaveBeenCalledExactlyOnceWith(
      '12345678901231', expect.objectContaining({ reasons: ['ambiguous_shape', 'recognition_choice'] }),
      { outcome: 'detection_choice' }, expect.any(Date), expect.stringMatching(/^detection:/),
    );
  });

  it('skips the start of a number the account saved, and records it when the check fails', async () => {
    vi.spyOn(console, 'log').mockImplementation(() => undefined);
    const client = new SupabaseServiceClient('https://database.example', 'service-key');
    const retain = vi.spyOn(client, 'recordTrackingSupportObservation').mockResolvedValue(undefined);
    const saved = vi.spyOn(client, 'savedLongerTrackingNumber').mockResolvedValueOnce(true);
    const typing = { trackingNumber: 'ZZ001234', carrier: 'unknown' } as const;
    await retainDetectionSupport(client, typing, 'owner');
    expect(saved).toHaveBeenCalledExactlyOnceWith('owner', 'ZZ001234');
    expect(retain).not.toHaveBeenCalled();
    saved.mockResolvedValueOnce(false).mockRejectedValueOnce(new Error('database unavailable'));
    await retainDetectionSupport(client, typing, 'owner');
    await retainDetectionSupport(client, typing, 'owner');
    // Without an account there is nothing to check.
    await retainDetectionSupport(client, typing);
    expect(saved).toHaveBeenCalledTimes(3);
    expect(retain).toHaveBeenCalledTimes(3);
  });

  it('skips a pickup code', async () => {
    const client = new SupabaseServiceClient('https://database.example', 'service-key');
    const retain = vi.spyOn(client, 'recordTrackingSupportObservation').mockResolvedValue(undefined);
    await retainDetectionSupport(client, { trackingNumber: '204617', carrier: 'unknown' });
    expect(retain).not.toHaveBeenCalled();
  });

  it('logs the submitted number and the actual recognition candidates privately', async () => {
    const log = vi.spyOn(console, 'log').mockImplementation(() => undefined);
    const client = new SupabaseServiceClient('https://database.example', 'service-key');
    vi.spyOn(client, 'recordTrackingSupportObservation').mockResolvedValue(undefined);
    await retainDetectionSupport(client, {
      trackingNumber: '0000000046', carrier: 'unknown', asked: ['relais-colis', 'tipsa'], unanswered: ['tipsa'],
    });
    expect(JSON.parse(log.mock.calls[0][0])).toMatchObject({
      event: 'carrier_detection_support', tracking_number: '0000000046', outcome: 'detection_unknown',
      asked_carriers: 'relais-colis,tipsa', unanswered_carriers: 'tipsa', recognized_carriers: '',
    });
  });
});

describe('a case held for a carrier recognition asks through a browser', () => {
  afterEach(() => vi.restoreAllMocks());

  // A 12-digit number: FedEx's shape, which recognition asks FedEx about only through a browser.
  it.each([
    { name: 'FedEx was not asked, as when a provider already tracks the number', asked: undefined, unanswered: undefined, held: true },
    { name: 'FedEx could not answer', asked: ['fedex'], unanswered: ['fedex'], held: true },
    { name: 'FedEx answered it does not know the number', asked: ['fedex'], unanswered: undefined, held: false },
  ] as const)('holds the case when $name: $held', async ({ asked, unanswered, held }) => {
    vi.spyOn(console, 'log').mockImplementation(() => undefined);
    const client = new SupabaseServiceClient('https://database.example', 'service-key');
    const retain = vi.spyOn(client, 'recordTrackingSupportObservation').mockResolvedValue(undefined);
    await retainDetectionSupport(client, { trackingNumber: '123456789012', carrier: 'unknown',
      ...(asked ? { asked: [...asked] } : {}), ...(unanswered ? { unanswered: [...unanswered] } : {}) });
    expect(retain.mock.calls[0][1].browser_pending).toBe(held || undefined);
  });

  it('settles it when such a carrier tracks the number directly and leaves no gap', () => {
    const fedex = { tracking_number: '123456789012', carrier: 'fedex' };
    expect(trackingSupportEvidence(fedex, progress, 'fedex', 'updated', false).support_settled).toBe(true);
    // DHL is asked over HTTP: its progress on a number recognition missed is a gap.
    expect(trackingSupportEvidence({ ...fedex, carrier: 'dhl' }, progress, 'dhl', 'updated', false).support_settled).toBe(false);
    // A number whose shape no rule claims stays a gap whoever tracks it.
    expect(trackingSupportEvidence({ tracking_number: 'ZZTEST1234', carrier: 'fedex' }, progress, 'fedex', 'updated', false)
      .support_settled).toBe(false);
    expect(trackingSupportEvidence(fedex, progress, 'fedex', 'waiting', false).support_settled).toBe(false);
  });
});

describe('tracking support context', () => {
  it('keeps an unknown shape visible even when a carrier was selected manually', () => {
    expect(trackingSupportContext('zz-test 1234', 'ups')).toEqual({
      tracking_number: 'ZZTEST1234', configured_carrier: 'ups',
      detection_carrier: 'unknown', detection_confidence: 'none',
      detection_candidates: [], reasons: ['unknown_shape'],
    });
  });

  it('gives six digits filed under no carrier no reason: a pickup code, not a shape detection misses', () => {
    expect(trackingSupportContext('204 617', 'unknown')).toMatchObject({ detection_carrier: 'unknown', reasons: [] });
    // A carrier picked by hand takes them as its number.
    expect(trackingSupportContext('204617', 'pannon-xp').reasons).toContain('unknown_shape');
    expect(trackingSupportContext('2046170', 'unknown').reasons).not.toEqual([]);
  });

  it('records ambiguous numbers without treating a plausible selected carrier as a mismatch', () => {
    expect(trackingSupportContext('123456789012', 'fedex')).toMatchObject({
      detection_carrier: 'unknown', detection_confidence: 'low',
      detection_candidates: expect.arrayContaining(['fedex']), reasons: ['ambiguous_shape'],
    });
  });

  it('finds no ambiguity once the filed carrier, one of those the shape fits, tracked the number', () => {
    expect(trackingSupportContext('123456789012', 'fedex', 'fedex').reasons).toEqual([]);
    // Another carrier, or a filed one the shape does not fit, settles nothing.
    expect(trackingSupportContext('123456789012', 'fedex', 'dhl').reasons).toEqual(['ambiguous_shape']);
    expect(trackingSupportContext('HL123456789FR', 'dhl', 'dhl').reasons).toEqual(['ambiguous_shape']);
    expect(trackingSupportContext('zz-test 1234', 'ups', 'ups').reasons).toEqual(['unknown_shape']);
  });

  it('keeps a generic postal match unresolved without assigning its issuer to a carrier', () => {
    expect(trackingSupportContext('RR123456785FI', 'intl-post')).toMatchObject({
      detection_carrier: 'intl-post', detection_confidence: 'high', reasons: ['generic_postal'],
    });
    expect(trackingSupportContext('RR123456785FI', 'royal-mail').reasons)
      .toEqual(['generic_postal']);
  });

  it('does not report a direct-adapter gap for Royal Mail', () => {
    expect(trackingSupportContext('RR123456785GB', 'royal-mail')).toMatchObject({
      detection_carrier: 'royal-mail', detection_confidence: 'high', reasons: [],
    });
  });

  it('records a carrier whose tracking still depends on providers', () => {
    expect(trackingSupportContext('ZZTEST1234', 'shipup')).toMatchObject({
      detection_carrier: 'unknown', reasons: ['unknown_shape', 'no_direct_adapter'],
    });
  });

  it('records a wrong selected carrier separately from the ambiguous shape', () => {
    expect(trackingSupportContext('HL123456789FR', 'dhl')).toMatchObject({
      detection_candidates: ['chronopost'], reasons: ['ambiguous_shape', 'carrier_mismatch'],
    });
  });

  it('takes Amazon Shipping for the Amazon Logistics number detection names', () => {
    expect(trackingSupportContext('ES1234567890', 'amazon-shipping')).toMatchObject({
      detection_carrier: 'amazon-logistics', reasons: [],
    });
  });

  it('finds no mismatch for a parcel its carrier or its local carrier tracked by this number', () => {
    // An origin's number that names its local carrier.
    const local = 'CNPRT12345678901234567890';
    expect(trackingSupportContext(local, 'aliexpress').reasons).toEqual(['carrier_mismatch']);
    expect(trackingSupportContext(local, 'aliexpress', 'aliexpress').reasons).toEqual([]);
    expect(trackingSupportContext(local, 'aliexpress', 'ecoscooting').reasons).toEqual([]);
    // Another carrier the number does not name proves nothing.
    expect(trackingSupportContext(local, 'aliexpress', 'dhl').reasons).toEqual(['carrier_mismatch']);
  });

  it('does not turn a recognized UPS shape into a format case when the carrier is unavailable', () => {
    expect(trackingSupportContext(number, 'ups')).toMatchObject({
      detection_carrier: 'ups', detection_confidence: 'high', reasons: [],
    });
  });
});

describe('tracking support evidence', () => {
  it.each(['updated', 'unchanged'])('verifies accepted direct progress with a source timestamp when %s', (outcome) => {
    expect(trackingSupportEvidence(parcel, progress, 'ups', outcome, false)).toEqual({
      support_lookup_number: number, support_provider: null, support_direct_progress: true, support_settled: true,
    });
  });

  it('keeps successful provider tracking from verifying a direct fix', () => {
    expect(trackingSupportEvidence(parcel, { ...progress, tracking_provider: 'ParcelsApp' }, 'unknown', 'updated', false))
      .toEqual({ support_lookup_number: number, support_provider: 'ParcelsApp', support_direct_progress: false, support_settled: false });
    expect(trackingSupportEvidence(parcel, { ...progress, tracking_provider: 'ParcelsApp' }, 'ups', 'updated', false)
      .support_direct_progress).toBe(false);
  });

  it.each(['waiting', 'error', 'superseded'])('does not verify a direct result whose persistence outcome is %s', outcome => {
    expect(trackingSupportEvidence(parcel, progress, 'ups', outcome, false).support_direct_progress).toBe(false);
  });

  it('does not verify a label-only result, preserved summary, or undated progress', () => {
    const label: CarrierResult = {
      status: 'pending', current_stage: 'registered', last_update: '2026-09-10T11:00:00Z',
      events: [{ time: '2026-09-10T11:00:00Z', description: 'Label created', stage: 'registered' }],
    };
    expect(trackingSupportEvidence(parcel, label, 'ups', 'updated', false).support_direct_progress).toBe(false);
    expect(trackingSupportEvidence(parcel, progress, 'ups', 'updated', true).support_direct_progress).toBe(false);
    expect(trackingSupportEvidence(parcel, { status: 'in_transit', current_stage: 'in_transit' }, 'ups', 'updated', false)
      .support_direct_progress).toBe(false);
  });

  it('counts persisted Royal Mail history as direct progress', () => {
    const royalParcel = { tracking_number: 'RR123456785GB', carrier: 'royal-mail' };
    expect(trackingSupportEvidence(royalParcel, progress, 'royal-mail', 'updated', false)).toEqual({
      support_lookup_number: royalParcel.tracking_number, support_provider: null, support_direct_progress: true, support_settled: true,
    });
  });

  it('does not count a universal-only carrier as a direct fix without a provider label', () => {
    expect(trackingSupportEvidence(parcel, progress, 'intl-post', 'updated', false).support_direct_progress).toBe(false);
  });

  it('binds handoff evidence to the delivery number so it cannot verify the original number', () => {
    const deliveryNumber = '990000000000000001';
    const handoff = {
      original_carrier: 'ups', active_tracking_carrier: 'swiss-post', active_tracking_number: deliveryNumber,
    };
    for (const [input, result] of [
      [parcel, { ...progress, ...handoff }],
      [{ ...parcel, carrier_data: handoff }, progress],
    ] as const) {
      const evidence = trackingSupportEvidence(input, result, 'swiss-post', 'updated', false);
      expect(evidence).toEqual({
        support_lookup_number: deliveryNumber, support_provider: null, support_direct_progress: true, support_settled: true,
      });
      expect(evidence.support_lookup_number).not.toBe(input.tracking_number);
    }
  });
});
