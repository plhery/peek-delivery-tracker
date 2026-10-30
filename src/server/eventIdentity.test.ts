import { describe, expect, it } from 'vitest';
import type { JsonObject } from './types';
import { datedObservations, filledPlaceholders, misdatedCopies, requalifiedPlaces, restatedIdentities, retimedScans, rewordedScans, sameInstantIdentities, sharedScans, strippedMarkup, relayedScans, withIdentities, withoutCopyDrift, withStatedClocks } from './eventIdentity';

// New events carry the sync's ISO spelling; stored rows come back from
// PostgREST with an explicit +00:00 offset. Both name the same instant.
// A universal scan names its provider, as the sync's do.
const scan = (id: string, occurredAt: string, description = 'Scan', stage?: string) => ({
  package_id: 'package-1', provider_event_id: id, occurred_at: occurredAt, description, ...(stage ? { stage } : {}),
  ...(id.startsWith('unknown:') ? { raw_data: { universal_provider: 'ParcelsApp' } } : {}),
});
const row = (id: string, occurredAt: string, description?: string, stage?: string) => ({
  provider_event_id: id, occurred_at: occurredAt,
  ...(description === undefined ? {} : { description }), ...(stage ? { stage } : {}),
});
const pairs = (map: ReadonlyMap<string, string>) => [...map].sort(([left], [right]) => left.localeCompare(right));

describe('same-instant identity reuse', () => {
  it.each(['unknown', 'swiss-post'])('enriches distinct %s scans sharing an instant without duplicating their identities', (source) => {
    const at = '2026-07-11T18:05:00Z';
    const saved = ['Arrived at sorting centre', 'Departed sorting centre'].map((description, i) => ({
      ...row(`${source}:old-${i}`, at, description, 'in_transit'), location: null,
      raw_data: { provider_code: `SCAN-${i}` },
    }));
    const incoming = saved.map((item, i) => ({
      ...item, provider_event_id: `${source}:located-${i}`, location: 'Example City, France',
    }));
    const expected = incoming.map((item, i) => [item.provider_event_id, saved[i]!.provider_event_id]);
    expect([...sameInstantIdentities(incoming, saved, source)]).toEqual(expected);
    expect([...sameInstantIdentities(incoming, saved.map((item) => ({ ...item, location: 'Example City, France' })), source)])
      .toEqual(expected);
    expect(sameInstantIdentities([incoming[0]!], [saved[0]!, { ...saved[0]!, provider_event_id: `${source}:duplicate` }], source).size)
      .toBe(0);
    expect(sameInstantIdentities([incoming[0]!, { ...incoming[0]!, provider_event_id: `${source}:duplicate` }], [saved[0]!], source).size)
      .toBe(0);
    for (const different of [
      { ...saved[0]!, stage: 'out_for_delivery' },
      { ...saved[0]!, location: 'Another City, France' },
      { ...saved[0]!, observed_without_provider_timestamp: true },
    ]) expect(sameInstantIdentities([incoming[0]!], [different], source).size).toBe(0);
    if (source === 'swiss-post') {
      expect(sameInstantIdentities([incoming[0]!], [{ ...saved[0]!, raw_data: { provider_code: 'OTHER' } }], source).size).toBe(0);
    }
  });

  it("rewords a Swiss Post scan in its owner's language on the row it was stored with", () => {
    const at = '2026-07-11T18:05:00Z';
    const saved = { ...row('swiss-post:english', at, 'Arrived at the sorting centre', 'in_transit'), location: 'Example City',
      raw_data: { provider_code: 'SORT' } };
    const incoming = { ...scan('swiss-post:french', at, 'Arrivée au centre de tri', 'in_transit'), location: 'Example City',
      raw_data: { provider_code: 'SORT' } };
    expect([...sameInstantIdentities([incoming], [saved], 'swiss-post')]).toEqual([['swiss-post:french', 'swiss-post:english']]);
    for (const different of [
      { ...saved, location: 'Another City' },
      { ...saved, raw_data: { provider_code: 'OTHER' } },
      { ...saved, description: 'Arrived at the sorting centre — Revocation' },
    ]) expect(sameInstantIdentities([incoming], [different], 'swiss-post').size).toBe(0);
  });

  it('updates a UPS scan when its location is filled in', () => {
    const at = '2026-07-11T18:05:00Z';
    const saved = { ...row('ups:without-location', at, 'Package collected', 'accepted'), location: null };
    const incoming = { ...scan('ups:with-location', at, 'Package collected', 'accepted'), location: 'Example City, France' };
    expect([...sameInstantIdentities([incoming], [saved], 'ups')])
      .toEqual([['ups:with-location', 'ups:without-location']]);
    for (const different of [
      { ...saved, description: 'Package departed' },
      { ...saved, stage: 'in_transit' },
      { ...saved, location: 'Another City, France' },
      { ...saved, observed_without_provider_timestamp: true },
      { ...saved, provider_event_id: 'unknown:without-location' },
    ]) expect(sameInstantIdentities([incoming], [different], 'ups').size).toBe(0);
    expect(sameInstantIdentities([incoming], [saved, { ...saved, provider_event_id: 'ups:another' }], 'ups').size).toBe(0);
    expect(sameInstantIdentities([incoming, { ...incoming, provider_event_id: 'ups:departure', description: 'Package departed' }],
      [saved], 'ups').size).toBe(0);
  });

  it('updates a reworded coded scan without changing its saved identity', () => {
    const at = '2026-07-11T18:05:00Z';
    const incoming = { ...scan('india-post:flight-details', at, 'Flight ZZ0101 departed: FRA → CDG'),
      raw_data: { provider_code: 'AircraftTakeOff' } };
    const saved = { ...row('india-post:uplift', at, 'UPLIFT'), provider_code: 'AircraftTakeOff' };
    expect([...sameInstantIdentities([incoming], [saved], 'india-post')])
      .toEqual([['india-post:flight-details', 'india-post:uplift']]);
    for (const code of ['ItemReceived', '', undefined]) {
      expect(sameInstantIdentities([incoming], [{ ...saved, provider_code: code }], 'india-post').size).toBe(0);
    }
    expect(sameInstantIdentities([incoming], [saved, { ...saved, provider_event_id: 'india-post:another' }], 'india-post').size).toBe(0);
    expect(sameInstantIdentities([incoming], [{ ...saved, provider_event_id: 'other:flight' }], 'india-post').size).toBe(0);
    expect(sameInstantIdentities([{ ...incoming, raw_data: { provider_code: 'Unknown' } }],
      [{ ...saved, provider_code: 'Unknown' }], 'india-post').size).toBe(0);
  });

  it('matches reworded scans sharing an instant by their unique provider codes', () => {
    const at = '2026-07-11T18:05:00Z';
    const saved = [
      { ...row('india-post:arrival', at, 'Arrived at sorting centre'), location: 'Example Office 000000', provider_code: 'MailArrived' },
      { ...row('india-post:receipt', at, 'Item received'), location: 'Example Office 000000', provider_code: 'ItemReceived' },
    ];
    const incoming = saved.map(({ provider_code, ...item }) => ({ ...item, provider_event_id: `${item.provider_event_id}-located`,
      location: 'Example Office 000001', raw_data: { provider_code } }));
    expect(pairs(sameInstantIdentities(incoming, saved, 'india-post'))).toEqual([
      ['india-post:arrival-located', 'india-post:arrival'],
      ['india-post:receipt-located', 'india-post:receipt'],
    ]);
    // An unchanged scan in the batch does not prevent the other code's reuse.
    expect([...sameInstantIdentities([incoming[0]!, saved[1]!], saved, 'india-post')])
      .toEqual([['india-post:arrival-located', 'india-post:arrival']]);
    expect([...sameInstantIdentities([incoming[0]!], saved, 'india-post')])
      .toEqual([['india-post:arrival-located', 'india-post:arrival']]);
    // Repeated codes at one instant are still ambiguous in either direction.
    expect([...sameInstantIdentities(incoming, [...saved, { ...saved[0]!, provider_event_id: 'india-post:another-arrival' }], 'india-post')])
      .toEqual([['india-post:receipt-located', 'india-post:receipt']]);
    expect([...sameInstantIdentities([...incoming, { ...incoming[0]!, provider_event_id: 'india-post:another-new-arrival' }], saved, 'india-post')])
      .toEqual([['india-post:receipt-located', 'india-post:receipt']]);
    for (const code of ['', 'Unknown', 'OtherCode']) {
      const changed = [{ ...incoming[0]!, raw_data: { provider_code: code } }, incoming[1]!];
      expect(sameInstantIdentities(changed, saved, 'india-post').has('india-post:arrival-located')).toBe(false);
    }
  });
  it('gives each reworded scan the one stored identity at its instant', () => {
    const stored = [
      row('dpd:unverified-delivered', '2026-07-16T08:12:00+00:00'),
      row('dpd:unverified-out', '2026-07-16T04:10:45+00:00'),
    ];
    const events = [
      scan('dpd:verified-delivered', '2026-07-16T08:12:00Z', 'Your parcel has been delivered successfully'),
      scan('dpd:verified-out', '2026-07-16T04:10:45Z', 'Your parcel is out for delivery'),
      // Only the verified reply lists the customs scan: nothing to take over.
      scan('dpd:verified-customs', '2026-07-15T14:30:00Z', 'Your parcel cleared customs successfully'),
    ];

    const reused = sameInstantIdentities(events, stored, 'dpd');

    expect([...reused]).toEqual([
      ['dpd:verified-delivered', 'dpd:unverified-delivered'],
      ['dpd:verified-out', 'dpd:unverified-out'],
    ]);
    expect(withIdentities(events, reused).map((event) => event.provider_event_id)).toEqual([
      'dpd:unverified-delivered', 'dpd:unverified-out', 'dpd:verified-customs',
    ]);
    // The computed events are left as they were.
    expect(events[0]?.provider_event_id).toBe('dpd:verified-delivered');
  });

  it('takes over a universal provider copy of the same scan', () => {
    expect([...sameInstantIdentities(
      [scan('dpd:delivered', '2026-07-16T08:12:00Z')],
      [row('unknown:ship24-delivered', '2026-07-16T08:12:00+00:00')],
      'dpd',
    )]).toEqual([['dpd:delivered', 'unknown:ship24-delivered']]);
  });

  it('reuses nothing when an instant has several candidates or several new scans', () => {
    const instant = '2026-07-16T08:12:00Z';
    expect(sameInstantIdentities(
      [scan('dpd:new', instant)],
      [row('dpd:unverified', instant), row('unknown:universal', instant)],
      'dpd',
    ).size).toBe(0);
    expect(sameInstantIdentities(
      [scan('dpd:new-a', instant), scan('dpd:new-b', instant)],
      [row('dpd:unverified', instant)],
      'dpd',
    ).size).toBe(0);
    // A scan listed twice is two new scans at one instant, never one identity twice.
    expect(sameInstantIdentities(
      [scan('dpd:twice', instant), scan('dpd:twice', instant)],
      [row('dpd:unverified', instant)],
      'dpd',
    ).size).toBe(0);
  });

  it('never takes over another carrier or the app', () => {
    const instant = '2026-07-16T08:12:00Z';
    const others = [row('swiss-post:scan', instant), row('app:registered', instant), row('dpd-fr:scan', instant)];
    expect(sameInstantIdentities([scan('dpd:new', instant)], others, 'dpd').size).toBe(0);
    // They are not candidates, so they do not make a DPD candidate ambiguous either.
    expect([...sameInstantIdentities([scan('dpd:new', instant)], [...others, row('dpd:old', instant)], 'dpd')])
      .toEqual([['dpd:new', 'dpd:old']]);
    // Another carrier's scan in a merged batch never takes a DPD identity.
    expect(sameInstantIdentities([scan('dhl:origin', instant)], [row('dpd:old', instant)], 'dpd').size).toBe(0);
  });

  it('does not reuse an observation as a carrier scan identity', () => {
    const instant = '2026-07-16T08:12:00Z';
    const observed = { ...row('dpd:observed', instant, 'Delivered'),
      raw_data: { observed_without_provider_timestamp: true } };
    expect(sameInstantIdentities([scan('dpd:dated', instant, 'Delivered')], [observed], 'dpd').size).toBe(0);
    const incomingObservation = { ...scan('dpd:observed', instant, 'Delivered'),
      raw_data: { observed_without_provider_timestamp: true } };
    expect(sameInstantIdentities([incomingObservation], [row('dpd:dated', instant, 'Delivered')], 'dpd').size).toBe(0);
  });

  it('only applies to a source that opted in', () => {
    const instant = '2026-07-16T08:12:00Z';
    for (const source of ['unknown', 'gls-ch', 'swiss-post']) {
      expect(sameInstantIdentities(
        [scan(`${source}:new`, instant)],
        [row(`${source}:old`, instant), row('dpd:old', instant)],
        source,
      ).size).toBe(0);
    }
  });

  it('keeps an identity that is already stored and never hands it to another scan', () => {
    const instant = '2026-07-16T08:12:00Z';
    const stored = [row('dpd:same', instant), row('unknown:universal', '2026-07-16T04:10:45Z')];
    // The scan whose identity is stored keeps it; the new scan beside it
    // finds no unclaimed candidate at that instant.
    expect(sameInstantIdentities(
      [scan('dpd:same', instant), scan('dpd:reworded', instant)],
      stored,
      'dpd',
    ).size).toBe(0);
    // A stored identity another event of the batch carries is not free, even
    // when that event sits at another instant.
    expect(sameInstantIdentities(
      [scan('dpd:reworded', '2026-07-16T04:10:45Z'), scan('unknown:universal', '2026-07-16T09:00:00Z')],
      stored,
      'dpd',
    ).size).toBe(0);
  });

  it('matches the exact instant only', () => {
    const stored = [row('dpd:unverified', '2026-07-16T08:12:00+00:00')];
    for (const occurredAt of ['2026-07-16T08:12:01Z', '2026-07-16T08:12:00.001Z', '2026-07-16T10:12:00Z', 'not a time']) {
      expect(sameInstantIdentities([scan('dpd:new', occurredAt)], stored, 'dpd').size).toBe(0);
    }
    expect(sameInstantIdentities([scan('dpd:new', '2026-07-16T10:12:00+02:00')], stored, 'dpd').size).toBe(1);
  });
});

describe('scans a source once read on one zone\'s clock', () => {
  // La Poste's 07:50 in Paris, relayed by India Post as India's 07:50 (02:20 UTC).
  const location = 'EXAMPLE EXCHANGE OFFICE 999001';
  const labelled = { provider_event_id: 'india-post:labelled', occurred_at: '2026-07-14T02:20:00+00:00',
    time: '2026-07-14T02:20:00Z', stage: 'in_transit', description: 'Item Received', location, provider_code: 'ItemReceived' };
  const abroad = { package_id: 'package-1', provider_event_id: 'india-post:abroad', occurred_at: '2026-07-14T05:50:00.000Z',
    stage: 'in_transit', description: 'Item received at office of exchange (Inb)', location,
    raw_data: { time: '2026-07-14T07:50:00+02:00', provider_code: 'ItemReceived' } };
  // The same scan stored on its own clock, and a reply that reads it as labelled again.
  const own = { ...labelled, provider_event_id: 'india-post:own', occurred_at: '2026-07-14T05:50:00+00:00', time: '2026-07-14T07:50:00+02:00' };
  const relabelled = { ...abroad, provider_event_id: 'india-post:relabelled', occurred_at: '2026-07-14T02:20:00.000Z',
    raw_data: { time: '2026-07-14T02:20:00Z', provider_code: 'ItemReceived' } };

  it('moves the stored row to the scan\'s own clock in place, and back, whatever the wording', () => {
    expect([...sameInstantIdentities([abroad], [labelled], 'india-post')]).toEqual([['india-post:abroad', 'india-post:labelled']]);
    expect([...sameInstantIdentities([relabelled], [own], 'india-post')]).toEqual([['india-post:relabelled', 'india-post:own']]);
    // A stored row without its time string is read as labelled.
    expect(sameInstantIdentities([abroad], [{ ...labelled, time: undefined }], 'india-post').size).toBe(1);
    // Under a zero offset of its own too.
    const london = { ...abroad, occurred_at: '2026-07-14T07:50:00.000Z', raw_data: { ...abroad.raw_data, time: '2026-07-14T07:50:00+00:00' } };
    expect(sameInstantIdentities([london], [labelled], 'india-post').size).toBe(1);
  });

  it('needs the same provider code, location and wall clock, and a row no scan of the batch carries', () => {
    for (const different of [
      { ...labelled, provider_code: 'ItemDispatched' },
      { ...labelled, provider_code: undefined },
      { ...labelled, location: 'ANOTHER EXCHANGE OFFICE 999001' },
      { ...labelled, occurred_at: '2026-07-14T02:21:00+00:00', time: '2026-07-14T02:21:00Z' },
      { ...labelled, observed_without_provider_timestamp: true },
      { ...labelled, provider_event_id: 'unknown:labelled' },
    ]) expect(sameInstantIdentities([abroad], [different], 'india-post').size).toBe(0);
    const unknownCode = { ...abroad, raw_data: { ...abroad.raw_data, provider_code: 'Unknown' } };
    expect(sameInstantIdentities([unknownCode], [{ ...labelled, provider_code: 'Unknown' }], 'india-post').size).toBe(0);
    expect(sameInstantIdentities([abroad, { ...labelled, package_id: 'package-1' }], [labelled], 'india-post').size).toBe(0);
    // Two scans both labelled, 5 h 30 apart, are two scans.
    const later = { ...relabelled, provider_event_id: 'india-post:later', occurred_at: '2026-07-14T07:50:00.000Z',
      raw_data: { time: '2026-07-14T07:50:00Z', provider_code: 'ItemReceived' } };
    expect(sameInstantIdentities([later], [labelled], 'india-post').size).toBe(0);
    // An offset that is the zone's own is a label.
    const indian = { ...abroad, occurred_at: '2026-07-14T02:20:00.000Z', raw_data: { ...abroad.raw_data, time: '2026-07-14T07:50:00+05:30' } };
    expect(sameInstantIdentities([indian], [{ ...labelled, occurred_at: '2026-07-13T20:50:00+00:00', time: '2026-07-13T20:50:00Z' }], 'india-post').size).toBe(0);
  });

  it('matches a scan and a row that carry no code by their wording instead', () => {
    // Posti's copies of an origin post's scans carry no code.
    const uncoded = { ...labelled, provider_code: undefined };
    const scan = { ...abroad, description: ' item  RECEIVED', raw_data: { time: abroad.raw_data.time } };
    expect([...sameInstantIdentities([scan], [uncoded], 'india-post')]).toEqual([['india-post:abroad', 'india-post:labelled']]);
    expect(sameInstantIdentities([{ ...scan, description: 'Item dispatched' }], [uncoded], 'india-post').size).toBe(0);
    expect(sameInstantIdentities([{ ...scan, description: '' }], [{ ...uncoded, description: '' }], 'india-post').size).toBe(0);
    // A code on one side only is no match.
    expect(sameInstantIdentities([scan], [{ ...uncoded, description: 'Item Received', provider_code: 'ItemReceived' }], 'india-post').size).toBe(0);
  });

  it('keeps ambiguous scans and rows unmatched, and other sources out', () => {
    expect(sameInstantIdentities([abroad], [labelled, { ...labelled, provider_event_id: 'india-post:twin' }], 'india-post').size).toBe(0);
    expect(sameInstantIdentities([abroad, { ...abroad, provider_event_id: 'india-post:twin' }], [labelled], 'india-post').size).toBe(0);
    const dpd = (row: Record<string, unknown>) => ({ ...row, provider_event_id: String(row.provider_event_id).replace('india-post', 'dpd') });
    expect(sameInstantIdentities([dpd(abroad)], [dpd(labelled)], 'dpd').size).toBe(0);
  });

  it('leaves the same-instant match first', () => {
    expect([...sameInstantIdentities([abroad], [labelled, own], 'india-post')]).toEqual([['india-post:abroad', 'india-post:own']]);
  });
});

describe('a scan whose source\'s feeds put different offsets on its wall clock', () => {
  // DHL Express's customs record: its global portal labels the wall clock +01:00 and gives
  // the record's code, its mobile API reads the clock on the facility's +02:00 and gives none.
  const location = 'EXAMPLE CITY - ITALY';
  const description = 'Customs clearance status updated';
  const portal = { provider_event_id: 'dhl-express:portal', occurred_at: '2026-10-05T10:00:00+00:00', time: '2026-10-05T11:00:00+01:00',
    stage: 'customs', description, location, provider_code: 'RR' };
  const mobile = { package_id: 'package-1', provider_event_id: 'dhl-express:mobile', occurred_at: '2026-10-05T09:00:00.000Z',
    stage: 'customs', description, location, raw_data: { time: '2026-10-05T11:00:00+02:00' } };
  // The record stored from the mobile API, and the portal's answer meeting it.
  const mobileRow = { provider_event_id: 'dhl-express:mobile-row', occurred_at: '2026-10-05T09:00:00+00:00', time: '2026-10-05T11:00:00+02:00',
    stage: 'customs', description, location };
  const portalScan = { ...mobile, provider_event_id: 'dhl-express:portal-scan', occurred_at: '2026-10-05T10:00:00.000Z',
    raw_data: { time: '2026-10-05T11:00:00+01:00', provider_code: 'RR' } };

  it('lets the record from one feed take over the row the other stored, both ways', () => {
    expect([...sameInstantIdentities([mobile], [portal], 'dhl-express')]).toEqual([['dhl-express:mobile', 'dhl-express:portal']]);
    expect([...sameInstantIdentities([portalScan], [mobileRow], 'dhl-express')])
      .toEqual([['dhl-express:portal-scan', 'dhl-express:mobile-row']]);
    // Under UTC's label, in other case and spacing, and with the same code on both sides.
    const utc = { ...portal, occurred_at: '2026-10-05T11:00:00+00:00', time: '2026-10-05T11:00:00Z', description: 'CUSTOMS CLEARANCE  STATUS UPDATED ' };
    expect(sameInstantIdentities([mobile], [utc], 'dhl-express').size).toBe(1);
    expect(sameInstantIdentities([{ ...mobile, raw_data: { ...mobile.raw_data, provider_code: 'RR' } }], [portal], 'dhl-express').size).toBe(1);
  });

  it('needs the same wall clock to the second, location and wording, codes that agree, and a row no scan of the batch carries', () => {
    for (const different of [
      { ...portal, occurred_at: '2026-10-05T10:01:00+00:00', time: '2026-10-05T11:01:00+01:00' },
      { ...portal, occurred_at: '2026-10-05T10:00:30+00:00', time: '2026-10-05T11:00:30+01:00' },
      { ...portal, time: '2026-10-05T11:00:00' },
      { ...portal, time: undefined },
      { ...portal, location: 'ANOTHER CITY - ITALY' },
      { ...portal, location: null },
      { ...portal, description: 'Clearance processing complete at EXAMPLE CITY - ITALY' },
      { ...portal, observed_without_provider_timestamp: true },
      { ...portal, provider_event_id: 'unknown:portal' },
    ]) expect(sameInstantIdentities([mobile], [different], 'dhl-express').size).toBe(0);
    expect(sameInstantIdentities([{ ...mobile, raw_data: { ...mobile.raw_data, provider_code: 'SD' } }], [portal], 'dhl-express').size).toBe(0);
    expect(sameInstantIdentities([{ ...mobile, description: '' }], [{ ...portal, description: '' }], 'dhl-express').size).toBe(0);
    expect(sameInstantIdentities([mobile, { ...portal, package_id: 'package-1' }], [portal], 'dhl-express').size).toBe(0);
    // A scan without an offset of its own has no wall clock to compare.
    expect(sameInstantIdentities([{ ...mobile, raw_data: { time: '2026-10-05T11:00:00' } }], [portal], 'dhl-express').size).toBe(0);
  });

  it('keeps ambiguous scans and rows unmatched, and other sources out', () => {
    expect(sameInstantIdentities([mobile], [portal, { ...portal, provider_event_id: 'dhl-express:twin' }], 'dhl-express').size).toBe(0);
    expect(sameInstantIdentities([mobile, { ...mobile, provider_event_id: 'dhl-express:twin' }], [portal], 'dhl-express').size).toBe(0);
    const as = (source: string, row: Record<string, unknown>) => ({ ...row, provider_event_id: String(row.provider_event_id).replace('dhl-express', source) });
    for (const source of ['dpd', 'india-post', 'la-poste']) {
      expect(sameInstantIdentities([as(source, mobile)], [as(source, portal)], source).size).toBe(0);
    }
  });

  it('leaves the same-instant match first', () => {
    const own = { ...mobileRow, provider_event_id: 'dhl-express:own' };
    expect([...sameInstantIdentities([mobile], [portal, own], 'dhl-express')]).toEqual([['dhl-express:mobile', 'dhl-express:own']]);
  });
});

describe('a scan its source dates in two feeds, to the minute and to the second', () => {
  // DPD Germany's app service dates a scan to the minute in its own words; its guest API dates
  // it to the second, with DPD's code and no place (synthetic wording and place).
  const app = (id: string, time: string, extra: JsonObject = {}) => ({ provider_event_id: `dpd-de:${id}`, occurred_at: time,
    stage: 'out_for_delivery', description: 'Paket ist in Zustellung', location: 'Example Depot', ...extra });
  const guestRow = { provider_event_id: 'dpd-de:guest', occurred_at: '2026-10-20T08:15:37.000Z', stage: 'out_for_delivery',
    description: 'Out for delivery', location: '', raw_data: { provider_code: '13' } };
  const appScan = app('app', '2026-10-20T08:15:00.000Z');

  it('lets one feed\'s scan take over the row the other stored less than a minute away', () => {
    expect([...sameInstantIdentities([appScan], [guestRow], 'dpd-de')]).toEqual([['dpd-de:app', 'dpd-de:guest']]);
  });

  it('needs less than a minute, the policy\'s match, a row no scan of the batch carries, one match each way and a source that opts in', () => {
    expect(sameInstantIdentities([app('late', '2026-10-20T08:16:37.000Z')], [guestRow], 'dpd-de').size).toBe(0);
    expect(sameInstantIdentities([app('stage', '2026-10-20T08:15:00.000Z', { stage: 'in_transit' })], [guestRow], 'dpd-de').size).toBe(0);
    expect(sameInstantIdentities([appScan, guestRow], [guestRow], 'dpd-de').size).toBe(0);
    expect(sameInstantIdentities([appScan, app('again', '2026-10-20T08:15:59.000Z')], [guestRow], 'dpd-de').size).toBe(0);
    const other = (row: JsonObject) => ({ ...row, provider_event_id: String(row.provider_event_id).replace('dpd-de:', 'dhl-express:') });
    expect(sameInstantIdentities([other(appScan)], [other(guestRow)], 'dhl-express').size).toBe(0);
  });
});

describe('a scan its source restates with or without its milliseconds', () => {
  // Royal Mail first dated a locker drop-off to the millisecond, then to the second (synthetic place).
  const description = 'Sender advised item dropped off at parcel locker';
  const location = 'Example Delivery Office RM Parcel Locker';
  const row = { provider_event_id: 'royal-mail:precise', occurred_at: '2026-10-23T15:31:18.092+00:00', time: '2026-10-23T16:31:18.092+01:00',
    stage: 'accepted', description, location, raw_data: { provider_code: 'LOPPA' } };
  const scan = { package_id: 'package-1', provider_event_id: 'royal-mail:whole', occurred_at: '2026-10-23T15:31:18.000Z',
    stage: 'accepted', description, location, raw_data: { time: '2026-10-23T16:31:18+01:00', provider_code: 'LOPPA' } };

  it('takes over the row of that second, both ways', () => {
    expect([...sameInstantIdentities([scan], [row], 'royal-mail')]).toEqual([['royal-mail:whole', 'royal-mail:precise']]);
    const precise = { ...row, package_id: 'package-1', provider_event_id: 'royal-mail:again' };
    const whole = { ...scan, provider_event_id: 'royal-mail:stored', occurred_at: '2026-10-23T15:31:18+00:00' };
    expect([...sameInstantIdentities([precise], [whole], 'royal-mail')]).toEqual([['royal-mail:again', 'royal-mail:stored']]);
  });

  it('needs one time to be that whole second, the same place and wording, the code the policy requires, and one match', () => {
    for (const different of [
      { ...row, occurred_at: '2026-10-23T15:31:19.092+00:00' },
      { ...row, occurred_at: '2026-10-23T15:31:17.092+00:00' },
      { ...row, location: 'Another Delivery Office' },
      { ...row, description: 'Item collected from parcel locker' },
      { ...row, raw_data: { provider_code: 'EVDAV' } },
      { ...row, raw_data: {} },
      { ...row, observed_without_provider_timestamp: true },
      { ...row, provider_event_id: 'unknown:precise' },
    ]) expect(sameInstantIdentities([scan], [different], 'royal-mail').size).toBe(0);
    // Two times with milliseconds of their own are two scans.
    expect(sameInstantIdentities([{ ...scan, occurred_at: '2026-10-23T15:31:18.500Z' }], [row], 'royal-mail').size).toBe(0);
    expect(sameInstantIdentities([{ ...scan, raw_data: { provider_code: 'Unknown' } }], [{ ...row, raw_data: { provider_code: 'Unknown' } }], 'royal-mail').size)
      .toBe(0);
    expect(sameInstantIdentities([scan], [row, { ...row, provider_event_id: 'royal-mail:twin' }], 'royal-mail').size).toBe(0);
    expect(sameInstantIdentities([scan, { ...scan, provider_event_id: 'royal-mail:twin' }], [row], 'royal-mail').size).toBe(0);
  });

  it('leaves a source without a policy out, and lets codes that agree do for one that requires none', () => {
    const as = (source: string, value: Record<string, unknown>) => ({ ...value, provider_event_id: String(value.provider_event_id).replace('royal-mail', source) });
    expect(sameInstantIdentities([as('example-carrier', scan)], [as('example-carrier', row)], 'example-carrier').size).toBe(0);
    expect(sameInstantIdentities([as('dhl-express', scan)], [as('dhl-express', { ...row, raw_data: {} })], 'dhl-express').size).toBe(1);
    expect(sameInstantIdentities([as('dhl-express', scan)], [as('dhl-express', { ...row, raw_data: { provider_code: 'OTHER' } })], 'dhl-express').size)
      .toBe(0);
  });
});

describe('rows an earlier take-over left under an old identity', () => {
  const at = '2026-09-27T02:03:08+08:00';
  const instant = '2026-09-26T18:03:08+00:00';
  // AliExpress first stored the town in brackets; its policy moved it to the place, in place.
  const scan = { provider_event_id: 'aliexpress:current', occurred_at: '2026-09-26T18:03:08.000Z', stage: 'in_transit',
    description: 'Processing at sorting center', location: 'Example Town', raw_data: { time: at } };
  const saved = { provider_event_id: 'aliexpress:bracketed', occurred_at: instant, stage: 'in_transit',
    description: 'Processing at sorting center', location: 'Example Town', time: at };

  it('lets a scan take over the row whose time, place and wording it carries', () => {
    expect(pairs(restatedIdentities([scan], [saved]))).toEqual([['aliexpress:current', 'aliexpress:bracketed']]);
    expect(pairs(restatedIdentities([scan], [{ ...saved, description: ' Processing at sorting center ', location: 'Example Town ' }])))
      .toEqual([['aliexpress:current', 'aliexpress:bracketed']]);
  });

  it('needs the same source, instant, time, place and wording', () => {
    for (const row of [
      { ...saved, provider_event_id: 'swiss-post:bracketed' },
      { ...saved, provider_event_id: 'app:pending' },
      { ...saved, occurred_at: '2026-09-26T18:03:09+00:00' },
      { ...saved, time: '2026-09-26T18:03:08Z' },
      { ...saved, time: undefined },
      { ...saved, location: 'Other Town' },
      { ...saved, description: 'Departed from sorting center' },
      { ...saved, observed_without_provider_timestamp: true },
    ]) expect(restatedIdentities([scan], [row]).size).toBe(0);
  });

  it('leaves a scan stored under its own identity, a row the batch carries, and two alike rows alone', () => {
    expect(restatedIdentities([scan], [saved, { ...saved, provider_event_id: 'aliexpress:current' }]).size).toBe(0);
    expect(restatedIdentities([scan, { ...scan, provider_event_id: 'aliexpress:bracketed' }], [saved]).size).toBe(0);
    expect(restatedIdentities([scan], [saved, { ...saved, provider_event_id: 'aliexpress:copy' }]).size).toBe(0);
  });
});

describe('rows stored with a placeholder of the carrier\'s template left in', () => {
  const occurred = '2026-10-21T07:41:00.000Z';
  // An adapter left bpost's placeholder for its locker's name unfilled; its fix fills it in.
  const saved = { provider_event_id: 'bpost:placeholder', occurred_at: '2026-10-21T07:41:00+00:00', stage: 'in_transit',
    description: 'Item available at the {{bboxRebrand}}', location: '' };
  const scan = { provider_event_id: 'bpost:filled', occurred_at: occurred, stage: 'ready_for_pickup',
    description: 'Item available at the bbox locker', location: '' };

  it('lets the scan that fills the placeholder in take the row over', () => {
    expect(pairs(filledPlaceholders([scan], [saved]))).toEqual([['bpost:filled', 'bpost:placeholder']]);
    expect(pairs(filledPlaceholders([{ ...scan, description: 'Item available at the  Parcel Locker ' }], [saved])))
      .toEqual([['bpost:filled', 'bpost:placeholder']]);
    const deadline = { ...saved, provider_event_id: 'swiss-post:placeholder', description: 'Registered for collection — Deadline to {{valueParameter}}' };
    expect(pairs(filledPlaceholders([{ ...scan, provider_event_id: 'swiss-post:filled', description: 'Registered for collection — Deadline to 28.10.2026' }], [deadline])))
      .toEqual([['swiss-post:filled', 'swiss-post:placeholder']]);
  });

  it('needs the same source, instant and place, and the template\'s other words', () => {
    for (const row of [
      { ...saved, provider_event_id: 'dhl:placeholder' },
      { ...saved, provider_event_id: 'app:pending' },
      { ...saved, occurred_at: '2026-10-21T07:41:01+00:00' },
      { ...saved, location: 'Example Town' },
      { ...saved, description: 'Item on its way to the {{bboxRebrand}}' },
      { ...saved, description: 'Item available at the bbox locker' },
      { ...saved, observed_without_provider_timestamp: true },
    ]) expect(filledPlaceholders([scan], [row]).size).toBe(0);
    for (const event of [
      { ...scan, description: 'Item available at the ' },
      { ...scan, description: 'Item available at the {{bboxRebrand}}' },
      { ...scan, description: 'Item ready at the bbox locker' },
    ]) expect(filledPlaceholders([event], [saved]).size).toBe(0);
  });

  it('leaves a scan stored under its own identity, a row the batch carries, and two alike scans or rows alone', () => {
    expect(filledPlaceholders([scan], [saved, { ...scan }]).size).toBe(0);
    expect(filledPlaceholders([scan, { ...saved }], [saved]).size).toBe(0);
    expect(filledPlaceholders([scan, { ...scan, provider_event_id: 'bpost:other', description: 'Item available at the Parcel Locker' }], [saved]).size).toBe(0);
    expect(filledPlaceholders([scan], [saved, { ...saved, provider_event_id: 'bpost:copy' }]).size).toBe(0);
  });
});

describe('rows stored with a place the source now names with a part more or less', () => {
  // A release added the state to the carrier's town; another source names the country in one answer and no place in the next.
  const saved = { provider_event_id: 'correios-br:town', occurred_at: '2026-10-21T07:41:00+00:00', stage: 'in_transit',
    description: 'Object in transit', location: 'Example Town' };
  const scan = { provider_event_id: 'correios-br:town-state', occurred_at: '2026-10-21T07:41:00.000Z', stage: 'in_transit',
    description: 'Object in transit', location: 'Example Town, EX' };

  it('lets the scan whose place gains or loses a part take the row over', () => {
    expect(pairs(requalifiedPlaces([scan], [saved]))).toEqual([['correios-br:town-state', 'correios-br:town']]);
    expect(pairs(requalifiedPlaces([{ ...saved, provider_event_id: 'correios-br:again' }], [{ ...scan }])))
      .toEqual([['correios-br:again', 'correios-br:town-state']]);
    const country = { ...saved, provider_event_id: 'dhl:country', location: 'Examplia', provider_code: 'PU' };
    expect(pairs(requalifiedPlaces([{ ...country, provider_event_id: 'dhl:none', location: '' }], [country])))
      .toEqual([['dhl:none', 'dhl:country']]);
    expect(pairs(requalifiedPlaces([{ ...scan, location: '  example  town , EX ' }], [saved])))
      .toEqual([['correios-br:town-state', 'correios-br:town']]);
    const parcelsApp = { raw_data: { universal_provider: 'ParcelsApp' } };
    expect(pairs(requalifiedPlaces([{ ...scan, ...parcelsApp, provider_event_id: 'unknown:town-state' }], [{ ...saved, ...parcelsApp, provider_event_id: 'unknown:town' }])))
      .toEqual([['unknown:town-state', 'unknown:town']]);
  });

  it('needs the same source, instant, wording and stage, codes that agree, and a place whose parts it keeps', () => {
    for (const row of [
      { ...saved, provider_event_id: 'dhl:town' },
      { ...saved, provider_event_id: 'app:pending' },
      // Two universal providers' copies are matched by the copy rules, which weigh the carrier's own scan.
      { ...saved, provider_event_id: 'unknown:town', raw_data: { universal_provider: '17TRACK' } },
      { ...saved, occurred_at: '2026-10-21T07:41:01+00:00' },
      { ...saved, description: 'Object posted' },
      { ...saved, description: '' },
      // A stage the carrier changed is another reading of the scan, which the place alone cannot tell.
      { ...saved, stage: 'out_for_delivery' },
      { ...saved, provider_code: 'MD1' },
      { ...saved, observed_without_provider_timestamp: true },
      // As many parts as the scan: another place, not a qualifier ("Other Town, EX" beside "Example Town, EX").
      { ...saved, location: 'Other Town, EX' },
      { ...saved, location: 'Example Town, EX' },
      // One part more that drops one of the scan's: another place.
      { ...saved, location: 'Example Town, OT, Examplia' },
      { ...saved, location: 'Example' },
    ]) expect(requalifiedPlaces([{ ...scan, provider_code: 'TA' }], [row]).size, JSON.stringify(row)).toBe(0);
  });

  it('leaves a scan stored under its own identity, a row the batch carries, and two alike scans or rows alone', () => {
    expect(requalifiedPlaces([scan], [saved, { ...scan }]).size).toBe(0);
    expect(requalifiedPlaces([scan, { ...saved }], [saved]).size).toBe(0);
    // The same answer naming the scan's place twice is the source's own repetition, not a restatement.
    expect(requalifiedPlaces([scan, { ...scan, provider_event_id: 'correios-br:region', location: 'Example Town, Region, EX' }], [saved]).size).toBe(0);
    expect(requalifiedPlaces([scan], [saved, { ...saved, provider_event_id: 'correios-br:copy', location: 'EX' }]).size).toBe(0);
  });
});

describe('rows stored with the HTML an adapter left in their words', () => {
  // An adapter stored a link around the carrier's note; its fix gives the words alone and reads their stage.
  const saved = { provider_event_id: 'yunexpress:markup', occurred_at: '2026-10-15T02:41:00+00:00', stage: 'in_transit',
    description: "1 item has been transferred to 0000 (example) <b><a href='/Contact?tracNum=REF0000'>CARD LEFT</a></b>", location: '' };
  const scan = { provider_event_id: 'yunexpress:clean', occurred_at: '2026-10-15T02:41:00.000Z', stage: 'failed_attempt',
    description: '1 item has been transferred to 0000 (example) CARD LEFT', location: '' };

  it('lets the scan that gives the words without the markup take the row over, in any stage', () => {
    expect(pairs(strippedMarkup([scan], [saved]))).toEqual([['yunexpress:clean', 'yunexpress:markup']]);
    expect(pairs(strippedMarkup([{ ...scan, description: 'Delivered to the front door' }], [{ ...saved, description: 'Delivered<br/>to the front door<br>' }])))
      .toEqual([['yunexpress:clean', 'yunexpress:markup']]);
  });

  it('needs the same source, instant and place, codes that agree, a tag in the row and none in the scan', () => {
    for (const row of [
      { ...saved, provider_event_id: 'couriersplease:markup' },
      { ...saved, occurred_at: '2026-10-15T02:41:01+00:00' },
      { ...saved, location: 'Example Town' },
      { ...saved, provider_code: 'CL' },
      { ...saved, observed_without_provider_timestamp: true },
      // An airport code in angle brackets is the carrier's words, not markup.
      { ...saved, description: 'Departed <BOG> 1 item has been transferred to 0000 (example) CARD LEFT' },
      { ...saved, description: '1 item has been transferred to 0000 (example) CARD LEFT' },
      // Words beyond the markup's: another scan.
      { ...saved, description: "1 item has been transferred to 0000 (example) <a href='/x'>CARD LEFT</a> again" },
    ]) expect(strippedMarkup([{ ...scan, provider_code: 'DL' }], [row]).size, JSON.stringify(row)).toBe(0);
    expect(strippedMarkup([{ ...scan, description: '<b>1 item has been transferred to 0000 (example) CARD LEFT</b>' }], [saved]).size).toBe(0);
  });

  it('leaves a scan stored under its own identity, a row the batch carries, and two alike rows alone', () => {
    expect(strippedMarkup([scan], [saved, { ...scan }]).size).toBe(0);
    expect(strippedMarkup([scan, { ...saved }], [saved]).size).toBe(0);
    expect(strippedMarkup([scan], [saved, { ...saved, provider_event_id: 'yunexpress:copy', description: `<i>${scan.description}</i>` }]).size).toBe(0);
  });
});

describe('a scan its source rewords', () => {
  // A carrier rewrote a failed attempt when it delivered the parcel, and lists only the new words (synthetic wording and times).
  const at = (id: string, time: string, description: string, stage: string, extra: JsonObject = {}) => ({
    provider_event_id: id, occurred_at: new Date(time).toISOString(), stage, description, location: '', raw_data: { time }, ...extra,
  });
  const out = at('dpd-uk:out', '2026-10-27T09:42:00Z', 'Your parcel is out for delivery', 'out_for_delivery');
  const missed = at('dpd-uk:missed', '2026-10-27T15:18:00Z', 'We were unable to deliver your parcel today', 'failed_attempt');
  const reworded = at('dpd-uk:missed-reworded', '2026-10-27T15:18:00Z', 'We called but you were not in', 'failed_attempt');
  const delivered = at('dpd-uk:delivered', '2026-10-28T10:34:00Z', 'Your parcel has been delivered', 'delivered');
  const stored = [out, missed];

  it('takes over the row of the old words the answer no longer lists, which then shows the new ones', () => {
    const matched = rewordedScans([out, reworded, delivered], stored);
    expect(pairs(matched)).toEqual([['dpd-uk:missed-reworded', 'dpd-uk:missed']]);
    expect(withIdentities([reworded], matched, stored)[0]).toMatchObject({ provider_event_id: 'dpd-uk:missed', description: reworded.description });
  });

  it('keeps a row the answer still lists: the source tells both scans', () => {
    expect(rewordedScans([out, missed, reworded, delivered], stored).size).toBe(0);
  });

  it('needs the same source, instant and stage, and codes that agree', () => {
    const coded = [out, { ...missed, raw_data: { ...missed.raw_data, provider_code: 'MISSED' } }];
    expect(rewordedScans([out, { ...reworded, raw_data: { ...reworded.raw_data, provider_code: 'MISSED' } }], coded).size).toBe(1);
    for (const scan of [
      { ...reworded, provider_event_id: 'dpd:missed-reworded' },
      { ...reworded, occurred_at: '2026-10-27T15:19:00.000Z' },
      { ...reworded, stage: 'exception' },
      { ...reworded, raw_data: { ...reworded.raw_data, provider_code: 'OTHER' } },
    ]) {
      expect(rewordedScans([out, scan, delivered], coded).size).toBe(0);
    }
  });

  it('takes over, in any stage, a row that had no stage of its own: a release gives the words in English and maps them', () => {
    // Stored as the fallback while no map read the carrier's words; the next release renders and stages them.
    const unread = at('meso:unread', '2026-10-27T15:18:00Z', '等待清关', 'in_transit', { stage_source: 'none' });
    const rendered = at('meso:rendered', '2026-10-27T15:18:00Z', 'Awaiting customs clearance', 'customs');
    expect(pairs(rewordedScans([rendered], [unread]))).toEqual([['meso:rendered', 'meso:unread']]);
    // The same as a raw field, as a batch row carries it.
    expect(rewordedScans([rendered], [{ ...unread, stage_source: undefined, raw_data: { time: '2026-10-27T15:18:00Z', stage_source: 'none' } }]).size).toBe(1);
    // A row in a stage of its own still needs the same stage.
    expect(rewordedScans([rendered], [{ ...unread, stage_source: 'carrier_map' }]).size).toBe(0);
    // Beside a row of the scan's stage at that instant, neither is taken.
    expect(rewordedScans([rendered], [unread, at('meso:customs', '2026-10-27T15:18:00Z', 'Customs', 'customs')]).size).toBe(0);
  });

  it('leaves two new scans or two rows of one instant and stage alone, and scans and rows another rule matched', () => {
    const second = at('dpd-uk:missed-second', '2026-10-27T15:18:00Z', 'Calling card left', 'failed_attempt');
    expect(rewordedScans([out, reworded, second, delivered], stored).size).toBe(0);
    expect(rewordedScans([out, reworded, delivered], [...stored, second]).size).toBe(0);
    expect(rewordedScans([out, reworded, delivered], stored, new Map([['dpd-uk:other', 'dpd-uk:missed']])).size).toBe(0);
    expect(rewordedScans([out, reworded, delivered], stored, new Map([['dpd-uk:missed-reworded', 'dpd-uk:elsewhere']])).size).toBe(0);
  });

  it('leaves universal and app rows, observations and scans dated by their day alone', () => {
    const universal = (row: JsonObject) => ({ ...row, provider_event_id: String(row.provider_event_id).replace('dpd-uk:', 'unknown:') });
    expect(rewordedScans([universal(out), universal(reworded)], [universal(out), universal(missed)]).size).toBe(0);
    expect(rewordedScans([out, { ...reworded, observed_without_provider_timestamp: true }], stored).size).toBe(0);
    const day = (row: JsonObject) => ({ ...row, occurred_at: '2026-10-27T00:00:00.000Z', raw_data: { time: '2026-10-27' } });
    expect(rewordedScans([out, day(reworded)], [out, day(missed)]).size).toBe(0);
  });
});

describe('a scan its carrier moves a little later', () => {
  // A carrier moved a departure an hour later and lists only the later time (synthetic place and times).
  const place = 'EXAMPLE CITY DISTRIBUTION CENTER';
  const at = (time: string, id: string, description = 'Departed Facility', extra: JsonObject = {}) => ({
    provider_event_id: id, occurred_at: new Date(time).toISOString(), stage: 'in_transit', description, location: place,
    raw_data: { time }, ...extra,
  });
  const label = at('2026-10-21T17:45:00-07:00', 'usps:label', 'Shipping Label Created', { stage: 'registered' });
  const arrived = at('2026-10-22T21:56:00-07:00', 'usps:arrived', 'Arrived at Facility');
  const departed = at('2026-10-23T01:55:00-07:00', 'usps:departed');
  const later = at('2026-10-23T03:00:00-07:00', 'usps:departed-later');
  const stored = [label, arrived, departed];

  it('takes over the row the answer no longer lists, up to three hours later', () => {
    expect(pairs(retimedScans([label, arrived, later], stored, 'usps'))).toEqual([['usps:departed-later', 'usps:departed']]);
    const threeHours = at('2026-10-23T04:55:00-07:00', 'usps:three-hours');
    expect(pairs(retimedScans([label, arrived, threeHours], stored, 'usps'))).toEqual([['usps:three-hours', 'usps:departed']]);
    // Codes that agree, wording and place apart from case and spacing.
    expect(pairs(retimedScans([label, arrived, { ...later, description: ' departed  facility', raw_data: { time: later.raw_data.time, provider_code: 'DEP' } }],
      [label, arrived, { ...departed, raw_data: { ...departed.raw_data, provider_code: 'DEP' } }], 'usps'))).toEqual([['usps:departed-later', 'usps:departed']]);
  });

  it('keeps a row the answer still lists: the carrier tells both scans', () => {
    expect(retimedScans([label, arrived, departed, later], stored, 'usps').size).toBe(0);
  });

  it('keeps a row the next round of the same scan replaces: a carrier listing only the latest of a run shows another scan', () => {
    // TNT scans a parcel held at its depot each working day and lists only the latest scan.
    expect(retimedScans([label, arrived, at('2026-10-23T04:56:00-07:00', 'usps:over-three-hours')], stored, 'usps').size).toBe(0);
    expect(retimedScans([label, arrived, at('2026-10-24T00:21:00-07:00', 'usps:next-day')], stored, 'usps').size).toBe(0);
    // Nor does a carrier move a scan earlier.
    expect(retimedScans([label, arrived, at('2026-10-23T01:00:00-07:00', 'usps:earlier')], stored, 'usps').size).toBe(0);
  });

  it('needs the same wording, place and stage, and codes that agree', () => {
    for (const scan of [
      { ...later, description: 'Departed Post Office' },
      { ...later, location: 'OTHER CITY DISTRIBUTION CENTER' },
      { ...later, stage: 'out_for_delivery' },
      { ...later, raw_data: { ...later.raw_data, provider_code: 'OTHER' } },
    ]) {
      const rows = [label, arrived, { ...departed, raw_data: { ...departed.raw_data, provider_code: 'DEP' } }];
      expect(retimedScans([label, arrived, { ...scan, raw_data: { provider_code: 'DEP', ...scan.raw_data } }], rows, 'usps').size).toBe(0);
    }
  });

  it('needs nothing of the carrier between the two, and an older scan of the carrier in the answer', () => {
    // A scan between them makes the later one the next visit.
    const between = at('2026-10-23T02:30:00-07:00', 'usps:between', 'Arrived at Facility');
    expect(retimedScans([label, arrived, between, later], stored, 'usps').size).toBe(0);
    expect(retimedScans([label, arrived, later], [...stored, between], 'usps').size).toBe(0);
    // An answer that starts after the row may be a shorter window of the history.
    expect(retimedScans([later], stored, 'usps').size).toBe(0);
  });

  it('keeps the time the row was first stored with while the row shows another', () => {
    const moved = retimedScans([label, arrived, later], stored, 'usps');
    const [row] = withIdentities([later], moved, stored, moved);
    expect(row).toMatchObject({ provider_event_id: 'usps:departed', raw_data: { time: later.raw_data.time, retimed_from: departed.raw_data.time } });
    // Another take-over leaves no mark of its own.
    expect(withIdentities([later], moved, stored)[0]!.raw_data).toEqual(later.raw_data);
    // The next check meets the row under its old identity and keeps the mark; a second move keeps the first time.
    const rows = [label, arrived, row!];
    expect(withIdentities([later], restatedIdentities([label, arrived, later], rows), rows)[0]!.raw_data)
      .toMatchObject({ retimed_from: departed.raw_data.time });
    const again = at('2026-10-23T04:00:00-07:00', 'usps:departed-again');
    const movedAgain = retimedScans([label, arrived, again], rows, 'usps');
    expect(withIdentities([again], movedAgain, rows, movedAgain)[0]).toMatchObject({
      provider_event_id: 'usps:departed', raw_data: { time: again.raw_data.time, retimed_from: departed.raw_data.time },
    });
    // Back at its first time, the row needs no mark.
    expect(withIdentities([departed], new Map(), rows)[0]!.raw_data).toEqual(departed.raw_data);
  });

  it('leaves two alike scans or rows, other sources, observations, days alone and matched scans alone', () => {
    expect(retimedScans([label, arrived, later, at('2026-10-23T03:30:00-07:00', 'usps:departed-again')], stored, 'usps').size).toBe(0);
    expect(retimedScans([label, arrived, later], [...stored, at('2026-10-23T01:56:00-07:00', 'usps:departed-twice')], 'usps').size).toBe(0);
    const universal = (row: JsonObject) => ({ ...row, provider_event_id: String(row.provider_event_id).replace('usps:', 'unknown:') });
    expect(retimedScans([label, arrived, later].map(universal), stored.map(universal), 'unknown').size).toBe(0);
    expect(retimedScans([label, arrived, later], stored, 'fedex').size).toBe(0);
    expect(retimedScans([label, arrived, later], [label, arrived, { ...departed, provider_event_id: 'fedex:departed' }], 'usps').size).toBe(0);
    expect(retimedScans([label, arrived, later], [label, arrived, { ...departed, observed_without_provider_timestamp: true }], 'usps').size).toBe(0);
    const days = [label, arrived, { ...departed, occurred_at: '2026-10-23T00:00:00.000Z', raw_data: { time: '2026-10-23' } }];
    expect(retimedScans([label, arrived, { ...later, occurred_at: '2026-10-23T01:00:00.000Z' }], days, 'usps').size).toBe(0);
    expect(retimedScans([label, arrived, later], stored, 'usps', new Map([['usps:departed-later', 'unknown:copy']])).size).toBe(0);
    expect(retimedScans([label, arrived, later], stored, 'usps', new Map([['usps:other', 'usps:departed']])).size).toBe(0);
  });
});

describe('scans a carrier and a universal provider both report', () => {
  // A carrier's Eastern scans, stored from its own lookup.
  const carrier = [
    row('gofo:delivered', '2026-01-04T21:58:30+00:00', 'Delivered'),
    row('gofo:out', '2026-01-04T12:59:03+00:00', 'Out for Delivery'),
    row('gofo:label', '2026-01-02T12:30:00+00:00', 'Shipping Label Created', 'registered'),
  ];

  it('leaves out a universal copy at the same instant or read in the wrong zone', () => {
    const shared = sharedScans([
      // Pacific offsets on Eastern clocks put these three hours late.
      scan('unknown:delivered', '2026-01-05T00:58:30Z', 'Delivered'),
      scan('unknown:out', '2026-01-04T15:59:03Z', 'OUT FOR  delivery'),
      scan('unknown:label', '2026-01-02T12:30:00Z', 'Label created', 'registered'),
      scan('unknown:exception', '2026-01-03T21:10:00Z', 'Delivery Exception, Business Closed.'),
    ], carrier);

    expect(pairs(shared.skipped)).toEqual([
      ['unknown:delivered', 'gofo:delivered'], ['unknown:label', 'gofo:label'], ['unknown:out', 'gofo:out'],
    ]);
    expect(shared.reused.size).toBe(0);
    expect([...shared.shifted].sort()).toEqual(['unknown:delivered', 'unknown:out']);
  });

  it('lets the carrier take over the universal copy stored while its lookup was down', () => {
    const stored = [
      ...carrier.slice(2),
      row('unknown:out', '2026-01-04T15:59:03+00:00', 'Out for Delivery'),
      row('unknown:exception', '2026-01-03T23:10:00+00:00',
        'Delivery Exception, Business Closed. For Delivery Issues & Tracking Support, Contact GOFO at +1 000 000-0000 or support@example.com'),
    ];
    const events = [
      scan('gofo:out', '2026-01-04T12:59:03Z', 'Out for Delivery'),
      scan('gofo:exception', '2026-01-03T21:10:00Z', 'Delivery Exception, Business Closed.'),
      scan('gofo:label', '2026-01-02T12:30:00Z', 'Shipping Label Created'),
    ];
    const shared = sharedScans(events, stored);

    expect(pairs(shared.reused)).toEqual([['gofo:exception', 'unknown:exception'], ['gofo:out', 'unknown:out']]);
    expect(shared.skipped.size).toBe(0);
    expect(withIdentities(events, shared.reused).map((event) => event.provider_event_id))
      .toEqual(['unknown:out', 'unknown:exception', 'gofo:label']);
    // A carrier never takes over another carrier's row.
    expect(sharedScans([scan('dhl:out', '2026-01-04T12:59:03Z', 'Out for Delivery')], carrier).reused.size).toBe(0);
  });

  it('never lets a universal reply move or reword a row a carrier corrected', () => {
    const corrected = [row('unknown:out', '2026-01-04T12:59:03+00:00', 'Out for Delivery')];
    const late = sharedScans([scan('unknown:out', '2026-01-04T15:59:03Z', 'Out for Delivery')], corrected);
    expect(pairs(late.skipped)).toEqual([['unknown:out', 'unknown:out']]);
    expect([...late.shifted]).toEqual(['unknown:out']);
    const sameInstant = sharedScans([scan('unknown:out', '2026-01-04T12:59:03Z', 'With delivery courier')], corrected);
    expect(pairs(sameInstant.skipped)).toEqual([['unknown:out', 'unknown:out']]);
    expect(sameInstant.reused.size + sameInstant.shifted.size).toBe(0);
    // A carrier's scan whose own identity is stored is left to the upsert.
    const own = sharedScans([scan('gofo:out', '2026-01-04T12:59:03Z', 'Out for Delivery')], [row('gofo:out', '2026-01-04T12:59:03+00:00', 'Out for Delivery')]);
    expect(own.skipped.size + own.reused.size).toBe(0);
  });

  it('matches whole quarter hours up to 14 hours away, times to the minute with the same wording only', () => {
    const stored = [row('gofo:out', '2026-01-04T12:00:00+00:00', 'Out for Delivery')];
    const skipped = (occurredAt: string, description = 'Out for Delivery') => sharedScans(
      [scan('unknown:out', occurredAt, description)], stored,
    ).skipped.size;
    // A copy with seconds of its own meets a carrier's scan in whole minutes at the copy's minute.
    for (const occurredAt of ['2026-01-04T12:45:00Z', '2026-01-04T17:30:00Z', '2026-01-05T02:00:00Z', '2026-01-03T22:00:00Z', '2026-01-04T13:00:01Z']) {
      expect(skipped(occurredAt), occurredAt).toBe(1);
    }
    for (const occurredAt of ['2026-01-04T12:20:00Z', '2026-01-05T02:15:00Z', '2026-01-03T21:45:00Z', '2026-01-04T13:01:01Z', 'not a time']) {
      expect(skipped(occurredAt), occurredAt).toBe(0);
    }
    // A carrier's scan with seconds is compared to the second.
    expect(sharedScans([scan('unknown:out', '2026-01-04T13:00:30Z', 'Out for Delivery')],
      [row('gofo:out', '2026-01-04T12:00:30+00:00', 'Out for Delivery')]).skipped.size).toBe(1);
    expect(sharedScans([scan('unknown:out', '2026-01-04T13:00:01Z', 'Out for Delivery')],
      [row('gofo:out', '2026-01-04T12:00:30+00:00', 'Out for Delivery')]).skipped.size).toBe(0);
    for (const description of ['Out for delivery today', 'Delivered', '']) expect(skipped('2026-01-04T15:00:00Z', description)).toBe(0);
    // A trailing sentence counts only after a finished one.
    expect(sharedScans([scan('unknown:x', '2026-01-04T15:00:00Z', 'Out for Delivery. Call us')],
      [row('gofo:x', '2026-01-04T12:00:00+00:00', 'Out for Delivery.')]).skipped.size).toBe(1);
    // So does a detail after an arrow (17TRACK's), but not a word run on.
    expect(skipped('2026-01-04T13:00:00Z', 'Out for Delivery -> Your item is out for delivery')).toBe(1);
    expect(skipped('2026-01-04T13:00:00Z', 'Out for Delivery->Your item')).toBe(0);
  });

  it('matches the same wording dated to the minute the other scan falls in, without a clock offset', () => {
    const precise = row('ups:label', '2026-01-02T21:25:25+00:00', 'Label Created', 'registered');
    const late = sharedScans([scan('unknown:label', '2026-01-02T21:25:00Z', 'label created')], [precise]);
    expect(pairs(late.skipped)).toEqual([['unknown:label', 'ups:label']]);
    expect(late.shifted.size).toBe(0);
    // The carrier's scan takes over the copy stored to the minute, which then keeps its second.
    const early = sharedScans([scan('ups:label', '2026-01-02T21:25:25Z', 'Label Created')],
      [row('unknown:label', '2026-01-02T21:25:00+00:00', 'Label Created')]);
    expect(pairs(early.reused)).toEqual([['ups:label', 'unknown:label']]);
    expect(early.shifted.size).toBe(0);
    const again = sharedScans([scan('unknown:label', '2026-01-02T21:25:00Z', 'Label Created')],
      [row('unknown:label', '2026-01-02T21:25:25+00:00', 'Label Created')]);
    expect(pairs(again.skipped)).toEqual([['unknown:label', 'unknown:label']]);
    expect(again.shifted.size).toBe(0);
  });

  it('leaves out a copy of a scan the carrier dated by its day alone, without a clock offset', () => {
    // USPS's day alone starts at midnight in New York; a provider copies the scan an hour into that day.
    const printed = { ...row('usps:printed', '2026-01-04T05:00:00+00:00', 'Shipping Label Printed at Post Office', 'registered'),
      raw_data: { local_time: '2026-01-04' } };
    const late = sharedScans([scan('unknown:printed', '2026-01-04T06:00:00Z', printed.description, 'registered')], [printed]);
    expect(pairs(late.skipped)).toEqual([['unknown:printed', 'usps:printed']]);
    expect(late.shifted.size).toBe(0);
    // Nor has such a copy a clock.
    const day = { ...scan('unknown:printed', '2026-01-04T06:00:00Z', printed.description, 'registered'),
      raw_data: { universal_provider: '17TRACK', local_time: '2026-01-04' } };
    expect(sharedScans([day], [{ ...printed, raw_data: {} }]).shifted.size).toBe(0);
    // A scan with a clock still shows one.
    expect([...sharedScans([scan('unknown:printed', '2026-01-04T06:00:00Z', printed.description, 'registered')],
      [{ ...printed, raw_data: {} }]).shifted]).toEqual(['unknown:printed']);
  });

  it('matches the same wording dated to the second the other scan falls in', () => {
    // Nova Poshta keeps milliseconds; ParcelsApp's copy of its scan keeps whole seconds.
    const copy = row('unknown:customs', '2026-01-07T03:15:23+00:00', 'Placed at the customs terminal', 'customs');
    const own = sharedScans([scan('nova-poshta:customs', '2026-01-07T03:15:23.164Z', 'Placed at the customs terminal', 'customs')], [copy]);
    expect(pairs(own.reused)).toEqual([['nova-poshta:customs', 'unknown:customs']]);
    expect(own.shifted.size).toBe(0);
    const late = sharedScans([scan('unknown:customs', '2026-01-07T03:15:23Z', 'Placed at the customs terminal', 'customs')],
      [row('nova-poshta:customs', '2026-01-07T03:15:23.164+00:00', 'Placed at the customs terminal', 'customs')]);
    expect(pairs(late.skipped)).toEqual([['unknown:customs', 'nova-poshta:customs']]);
    // The next second, or two times that both carry milliseconds, are other scans.
    for (const at of ['2026-01-07T03:15:24.164Z', '2026-01-07T03:15:23.500Z']) {
      const shared = sharedScans([scan('nova-poshta:customs', at, 'Placed at the customs terminal', 'customs')],
        [row('unknown:customs', '2026-01-07T03:15:23.164+00:00', 'Placed at the customs terminal', 'customs')]);
      expect(shared.reused.size, at).toBe(0);
    }
  });

  it('leaves a minute apart, other wording, both to the second, or two rows in the minute alone', () => {
    const matched = (at: string, description = 'Label Created', rows = [row('ups:label', '2026-01-02T21:25:25+00:00', 'Label Created')]) => {
      const shared = sharedScans([scan('unknown:label', at, description)], rows);
      return shared.skipped.size + shared.reused.size;
    };
    expect(matched('2026-01-02T21:25:00Z')).toBe(1);
    for (const at of ['2026-01-02T21:26:00Z', '2026-01-02T21:24:00Z', '2026-01-02T21:25:40Z']) expect(matched(at), at).toBe(0);
    expect(matched('2026-01-02T21:25:00Z', 'Picked up')).toBe(0);
    expect(matched('2026-01-02T21:25:00Z', 'Label Created', [
      row('ups:label', '2026-01-02T21:25:25+00:00', 'Label Created'), row('ups:again', '2026-01-02T21:25:50+00:00', 'Label Created'),
    ])).toBe(0);
    // A row at the scan's own instant, or one the batch shows at its own instant, is not a copy's twin.
    expect(matched('2026-01-02T21:25:00Z', 'Label Created', [
      row('ups:label', '2026-01-02T21:25:25+00:00', 'Label Created'), row('ups:other', '2026-01-02T21:25:00+00:00', 'Picked up'),
    ])).toBe(0);
    expect(sharedScans([scan('unknown:label', '2026-01-02T21:25:00Z', 'Label Created'),
      scan('unknown:other', '2026-01-02T21:25:25Z', 'Ready')], [row('ups:label', '2026-01-02T21:25:25+00:00', 'Label Created')])
      .skipped.has('unknown:label')).toBe(false);
    // Two carriers' scans are not a copy.
    expect(sharedScans([scan('dpd:label', '2026-01-02T21:25:00Z', 'Label Created')],
      [row('ups:label', '2026-01-02T21:25:25+00:00', 'Label Created')]).reused.size).toBe(0);
  });

  // A carrier's own scans, and a universal provider's own wording of them, unclassified and two hours early.
  const registered = scan('paack:registered', '2026-01-06T10:50:30Z', 'Shipment registered', 'registered');
  const accepted = scan('paack:accepted', '2026-01-06T16:38:19Z', 'Shipment accepted', 'accepted');
  const received = scan('unknown:received', '2026-01-06T08:50:30Z', 'Order details received', 'pending');
  const centre = scan('unknown:centre', '2026-01-06T14:38:19Z', 'In the distribution centre', 'pending');
  const stored = (event: ReturnType<typeof scan>) => row(String(event.provider_event_id),
    event.occurred_at.replace('Z', '+00:00'), event.description, event.stage);

  it('matches other wording on a clock a zone off, whichever source arrives first', () => {
    const late = sharedScans([received, centre], [registered, accepted].map(stored));
    expect(pairs(late.skipped)).toEqual([['unknown:centre', 'paack:accepted'], ['unknown:received', 'paack:registered']]);
    expect([...late.shifted].sort()).toEqual(['unknown:centre', 'unknown:received']);

    const early = sharedScans([registered, accepted], [received, centre].map(stored));
    expect(pairs(early.reused)).toEqual([['paack:accepted', 'unknown:centre'], ['paack:registered', 'unknown:received']]);
    expect([...early.shifted].sort()).toEqual(['paack:accepted', 'paack:registered']);
    // Classified alike, and some hours later.
    expect(sharedScans([{ ...centre, stage: 'accepted', occurred_at: '2026-01-07T03:38:19Z' }], [stored(accepted)]).skipped.size)
      .toBe(1);
  });

  it('leaves other wording a zone off alone without seconds, a stage that agrees or a carrier on one side', () => {
    const matched = (events: ReturnType<typeof scan>[], rows = [stored(accepted)]) => {
      const shared = sharedScans(events, rows);
      return shared.skipped.size + shared.reused.size;
    };
    // Times to the minute: two scans of a day often fall whole quarter hours apart.
    expect(matched([{ ...centre, occurred_at: '2026-01-06T14:38:00Z' }],
      [stored({ ...accepted, occurred_at: '2026-01-06T16:38:00Z' })])).toBe(0);
    for (const stage of ['in_transit', 'delivered']) expect(matched([{ ...centre, stage }]), stage).toBe(0);
    // Not a zone's offset: 20 minutes, a quarter or half hour (a carrier's follow-up scan), over 14 hours.
    for (const occurredAt of ['2026-01-06T16:18:19Z', '2026-01-06T16:23:19Z', '2026-01-06T16:08:19Z', '2026-01-07T06:53:19Z']) {
      expect(matched([{ ...centre, occurred_at: occurredAt }]), occurredAt).toBe(0);
    }
    // Two copies, or two carriers' scans, are not a carrier's clock against a provider's.
    expect(matched([centre], [stored({ ...accepted, provider_event_id: 'unknown:accepted' })])).toBe(0);
    expect(matched([{ ...accepted, provider_event_id: 'dpd:accepted', occurred_at: centre.occurred_at }])).toBe(0);
    // Two rows a zone away, or a row already at the scan's instant.
    expect(matched([centre], [stored(accepted), stored({ ...accepted, provider_event_id: 'paack:again', occurred_at: '2026-01-06T17:38:19Z' })]))
      .toBe(0);
    expect(pairs(sharedScans([centre], [stored(accepted), stored({ ...registered, occurred_at: centre.occurred_at, stage: 'in_transit' })]).skipped))
      .toEqual([['unknown:centre', 'paack:registered']]);
  });

  // A copy and a carrier's scan, either one stored: the same wording four hours apart, then other wording two hours apart to the second.
  const placed = <T extends object>(event: T, location: string) => ({ ...event, location });
  const matchedIn = (scanAt: string, rowAt: string) => {
    const shared = [
      sharedScans([placed(scan('unknown:departed', '2026-01-04T12:00:00Z', 'Departed facility'), scanAt)],
        [placed(row('gofo:departed', '2026-01-04T08:00:00+00:00', 'Departed facility'), rowAt)]),
      sharedScans([placed(scan('gofo:departed', '2026-01-04T12:00:00Z', 'Departed facility'), scanAt)],
        [placed(row('unknown:departed', '2026-01-04T08:00:00+00:00', 'Departed facility'), rowAt)]),
      sharedScans([placed(centre, scanAt)], [placed(stored(accepted), rowAt)]),
      sharedScans([placed(accepted, scanAt)], [placed(stored(centre), rowAt)]),
    ];
    return shared.map(({ skipped, reused }) => skipped.size + reused.size);
  };

  it('keeps a scan from another place a zone off, in any wording and whichever source arrives first', () => {
    expect(matchedIn('Lyon, France', 'Paris, France')).toEqual([0, 0, 0, 0]);
    expect(matchedIn('Lyon Saint-Exupéry', 'PARIS NORD (FR)')).toEqual([0, 0, 0, 0]);
  });

  it('still matches a zone off with no place, a country only, or the same place written otherwise', () => {
    for (const [scanAt, rowAt] of [['', 'Paris, France'], ['Lyon', ''], ['France', 'Lyon, FR'], ['PARIS', 'Paris CDG'],
      ['Paris, FR', 'paris (75)'], ['ZUERICH, CH', 'Zürich Mülligen'], ['Hub City, EX', 'HUB CITY EX US'], ['深圳市', 'Shenzhen']]) {
      expect(matchedIn(scanAt!, rowAt!), `${scanAt} / ${rowAt}`).toEqual([1, 1, 1, 1]);
    }
  });

  it('never moves a row the batch shows at its own instant', () => {
    // The provider reports the carrier's scan at its real time too: the other scan is its own.
    const sameTime = scan('unknown:accepted', accepted.occurred_at, 'Accepted', 'accepted');
    expect(pairs(sharedScans([centre, sameTime], [stored(accepted)]).skipped)).toEqual([['unknown:accepted', 'paack:accepted']]);
    // A row a carrier took over stays with that carrier's scan, not a later scan whose seconds agree.
    const taken = row('unknown:sorted', '2026-01-06T10:00:07+00:00', 'Sorted', 'in_transit');
    const shared = sharedScans([scan('gofo:sorted', '2026-01-06T10:00:07Z', 'Sorted', 'in_transit'),
      scan('gofo:loaded', '2026-01-06T12:00:07Z', 'Loaded', 'in_transit')], [taken]);
    expect(pairs(shared.reused)).toEqual([['gofo:sorted', 'unknown:sorted']]);
    expect(shared.shifted.size).toBe(0);
  });

  it('matches other wording at the same instant, in whatever stage the provider reads it', () => {
    const skipped = (stage?: string) => sharedScans(
      [scan('unknown:label', '2026-01-02T12:30:00Z', 'Label created', stage)], carrier,
    ).skipped.size;
    expect(skipped('registered')).toBe(1);
    // A copy in wording the provider left unclassified.
    expect(skipped('pending')).toBe(1);
    // A copy the provider reads as another stage: shown, it would look like a second scan.
    expect(skipped('in_transit')).toBe(1);
    expect(skipped('exception')).toBe(1);
    expect(skipped()).toBe(0);
  });

  it('matches a carrier\'s translated scan and an unclassified copy at the same instant, whichever arrives first', () => {
    const instant = '2026-01-09T19:28:00Z';
    const own = scan('la-poste:deposited', instant, 'Votre envoi a été déposé chez notre partenaire postal', 'accepted');
    const copy = scan('unknown:deposited', instant, 'Your shipment has been deposited with postal partner', 'pending');
    const storedRow = (event: ReturnType<typeof scan>) => row(String(event.provider_event_id), event.occurred_at, event.description, event.stage);
    expect(pairs(sharedScans([own], [storedRow(copy)]).reused)).toEqual([['la-poste:deposited', 'unknown:deposited']]);
    expect(pairs(sharedScans([copy], [storedRow(own)]).skipped)).toEqual([['unknown:deposited', 'la-poste:deposited']]);
    // A copy in another classified stage too: the carrier's scan takes it over.
    expect(pairs(sharedScans([{ ...own, stage: 'in_transit' }], [storedRow({ ...copy, stage: 'registered' })]).reused))
      .toEqual([['la-poste:deposited', 'unknown:deposited']]);
    // Two new scans at the instant: the copy is one of them, and the first takes it over.
    expect(pairs(sharedScans([own, scan('la-poste:other', instant, 'Votre envoi est en transit', 'in_transit')], [storedRow(copy)]).reused))
      .toEqual([['la-poste:deposited', 'unknown:deposited']]);
  });

  it('takes over the copy at an instant that words the scan under a heading, absorbing another provider\'s wording', () => {
    const instant = '2026-01-13T16:48:40Z';
    const stored = [
      row('unknown:cainiao', instant, 'Delivery attempt failure,Can\'t contact with customer', 'failed_attempt'),
      row('unknown:headed', instant, 'Delivery Attempt Failure. Your shipment delivery attempt failed [Recipient not at home]', 'failed_attempt'),
    ];
    const shared = sharedScans([scan('ecoscooting:failed', instant, 'Your shipment delivery attempt failed [Recipient not at home]', 'failed_attempt')], stored);
    expect(pairs(shared.reused)).toEqual([['ecoscooting:failed', 'unknown:headed']]);
    expect([...shared.absorbed]).toEqual(['unknown:cainiao']);
    // A heading that is no sentence of its own, or other wording, is not the scan's: the first copy is taken over instead.
    for (const description of ['Attempt failed [Recipient not at home]', 'Second attempt. Your shipment delivery attempt failed']) {
      const other = sharedScans([scan('ecoscooting:failed', instant, 'Your shipment delivery attempt failed [Recipient not at home]', 'failed_attempt')],
        [stored[0]!, row('unknown:x', instant, description, 'failed_attempt')]);
      expect(pairs(other.reused), description).toEqual([['ecoscooting:failed', 'unknown:cainiao']]);
      expect([...other.absorbed], description).toEqual(['unknown:x']);
    }
  });

  it('matches other wording dated to the minute the other scan falls in, in any stage, one to one', () => {
    const copy = { ...row('unknown:arrived', '2026-01-13T12:27:00+00:00', 'Arrived at FedEx location', 'in_transit'), location: 'Fort Worth, TX, US' };
    const own = scan('fedex:arrived', '2026-01-13T12:27:03Z', 'At local FedEx facility', 'in_transit');
    const early = sharedScans([own], [copy]);
    expect(pairs(early.reused)).toEqual([['fedex:arrived', 'unknown:arrived']]);
    expect(early.shifted.size).toBe(0);
    const late = sharedScans([{ ...scan('unknown:arrived', '2026-01-13T12:27:00Z', 'Arrived at FedEx location', 'in_transit'), location: 'Fort Worth, TX, US' }],
      [row('fedex:arrived', '2026-01-13T12:27:03+00:00', 'At local FedEx facility', 'in_transit')]);
    expect(pairs(late.skipped)).toEqual([['unknown:arrived', 'fedex:arrived']]);
    const matched = (events: (ReturnType<typeof scan> & { location?: string })[], rows: ReturnType<typeof row>[] = [copy]) => {
      const shared = sharedScans(events, rows);
      return shared.reused.size + shared.skipped.size;
    };
    // In another stage too; not from another place, or with two scans or two rows in the minute.
    expect(matched([{ ...own, stage: 'out_for_delivery' }])).toBe(1);
    expect(matched([{ ...own, location: 'Memphis, TN' }])).toBe(0);
    expect(matched([own, scan('fedex:sorted', '2026-01-13T12:27:41Z', 'Sorted', 'in_transit')])).toBe(0);
    expect(matched([own], [copy, row('unknown:sorted', '2026-01-13T12:27:00+00:00', 'Sorted', 'in_transit')])).toBe(0);
    // Both to the second: two scans.
    expect(matched([own], [{ ...copy, occurred_at: '2026-01-13T12:27:30+00:00' }])).toBe(0);
  });

  it('matches a provider\'s copy to the second of a carrier\'s scan to the minute in other wording, whichever arrives first', () => {
    // Shiprocket gives the minute; 17TRACK adds seconds of its own and words the scan its own way.
    const hub = { location: 'Example Hub, IN' };
    const own = { ...row('shiprocket:hub', '2026-10-18T06:12:00+00:00', 'Shipment reached the hub', 'in_transit'), ...hub };
    const copy = { ...scan('unknown:hub', '2026-10-18T06:12:37Z', 'Arrival at sorting facility', 'in_transit'), ...hub };
    const late = sharedScans([copy], [own]);
    expect(pairs(late.skipped)).toEqual([['unknown:hub', 'shiprocket:hub']]);
    expect(late.shifted.size).toBe(0);
    const early = sharedScans([{ ...scan('shiprocket:hub', '2026-10-18T06:12:00Z', own.description, 'in_transit'), ...hub }],
      [{ ...row('unknown:hub', '2026-10-18T06:12:37+00:00', copy.description, 'in_transit'), ...hub }]);
    expect(pairs(early.reused)).toEqual([['shiprocket:hub', 'unknown:hub']]);
    expect(early.shifted.size).toBe(0);
    // Not from another place, nor with a second row in the minute.
    expect(sharedScans([{ ...copy, location: 'Other Town, IN' }], [own]).skipped.size).toBe(0);
    expect(sharedScans([copy], [own, { ...own, provider_event_id: 'shiprocket:sorted', description: 'Sorted' }]).skipped.size).toBe(0);
  });

  it('matches a provider\'s copy to the minute that names the carrier\'s place in another language, whichever arrives first', () => {
    // Poczta Polska keeps seconds and words its scans in Polish; ParcelsApp keeps the minute and translates both.
    const own = { ...row('poczta-polska:sent', '2026-10-24T00:58:46+00:00', 'Wysłano z Polski', 'in_transit'), location: 'Sortownia Warszawa' };
    const copy = { ...scan('unknown:sent', '2026-10-24T00:58:00Z', 'Sending the package from Poland', 'in_transit'), location: 'Warsaw Sorting Centre' };
    expect(pairs(sharedScans([copy], [own]).skipped)).toEqual([['unknown:sent', 'poczta-polska:sent']]);
    const early = sharedScans([{ ...scan('poczta-polska:sent', '2026-10-24T00:58:46Z', own.description, 'in_transit'), location: own.location }],
      [{ ...row('unknown:sent', '2026-10-24T00:58:00+00:00', copy.description, 'in_transit'), location: copy.location }]);
    expect(pairs(early.reused)).toEqual([['poczta-polska:sent', 'unknown:sent']]);
    // Another town is another place.
    expect(sharedScans([{ ...copy, location: 'Krakow Sorting Centre' }], [own]).skipped.size).toBe(0);
  });

  it('matches other wording in the second the other scan falls in, one of them to the whole second, whichever arrives first', () => {
    // CTT Express keeps milliseconds; ParcelsApp keeps the whole second and words the scan its own way, unclassified.
    const at = { location: 'Example Customs Centre' };
    const own = row('ctt-express:waiting', '2026-01-21T08:20:57.063+00:00', 'Pendiente de recepción en CTT Express', 'registered');
    const copy = { ...scan('unknown:waiting', '2026-01-21T08:20:57Z', 'Waiting for entry at CTT. The shipment details were received.', 'pending'), ...at };
    const late = sharedScans([copy], [own]);
    expect(pairs(late.skipped)).toEqual([['unknown:waiting', 'ctt-express:waiting']]);
    expect(late.shifted.size).toBe(0);
    const early = sharedScans([scan('ctt-express:waiting', '2026-01-21T08:20:57.063Z', own.description, 'registered')],
      [{ ...row('unknown:waiting', '2026-01-21T08:20:57+00:00', copy.description, 'pending'), ...at }]);
    expect(pairs(early.reused)).toEqual([['ctt-express:waiting', 'unknown:waiting']]);
    expect(early.shifted.size).toBe(0);
    const matched = (event: JsonObject, rows: JsonObject[] = [own], relaying: string | null = null) => {
      const shared = sharedScans([event], rows, new Map(), relaying);
      return shared.skipped.size + shared.reused.size;
    };
    // Not the next second, two times that both keep milliseconds, another place, a second row in the second, or a relay.
    expect(matched({ ...copy, occurred_at: '2026-01-21T08:20:58Z' })).toBe(0);
    expect(matched({ ...copy, occurred_at: '2026-01-21T08:20:57.500Z' })).toBe(0);
    expect(matched(copy, [{ ...own, location: 'Madrid' }])).toBe(0);
    expect(matched(copy, [own, row('ctt-express:sorted', '2026-01-21T08:20:57.800+00:00', 'Clasificado', 'in_transit')])).toBe(0);
    expect(matched(copy, [own], 'ctt-express')).toBe(0);
  });

  it('leaves out every copy to the whole second of the one row a carrier holds in that second', () => {
    // Ship24 copies a CTT Express scan in CTT Express's words and in CTT Portugal's, both without the milliseconds.
    const own = row('ctt-express:sent', '2026-01-21T16:40:50.767+00:00', 'Enviado', 'in_transit');
    const spanish = scan('unknown:sent', '2026-01-21T16:40:50Z', 'Enviado', 'in_transit');
    const portuguese = { ...scan('unknown:arrived', '2026-01-21T16:40:50Z', 'Em trânsito - Chegou ao centro operacional', 'in_transit'),
      location: 'Example Customs Centre' };
    expect(pairs(sharedScans([spanish, portuguese], [own]).skipped))
      .toEqual([['unknown:arrived', 'ctt-express:sent'], ['unknown:sent', 'ctt-express:sent']]);
    const skipped = (rows: JsonObject[]) => sharedScans([spanish, portuguese], rows).skipped.size;
    // Not the next second, nor another place.
    expect(skipped([{ ...own, occurred_at: '2026-01-21T16:40:51.200+00:00' }])).toBe(0);
    // With two of the carrier's rows in the second, the copy in the carrier's words still matches its own.
    expect(pairs(sharedScans([spanish, portuguese], [own, row('ctt-express:sorted', '2026-01-21T16:40:50.900+00:00', 'Clasificado', 'in_transit')]).skipped))
      .toEqual([['unknown:sent', 'ctt-express:sent']]);
    expect(sharedScans([portuguese], [{ ...own, location: 'Madrid' }]).skipped.size).toBe(0);
    // An earlier carrier's relay keeps only the copy in its own words out.
    expect(pairs(sharedScans([spanish, portuguese], [own], new Map(), 'ctt-express').skipped)).toEqual([['unknown:sent', 'ctt-express:sent']]);
  });

  describe('several rows in other wording in the scan\'s minute or second', () => {
    // ParcelsApp dates two Chronopost scans to the minute and adds the reason to one; Chronopost dates both to the second.
    const hub = { location: 'Example Hub' };
    const moving = "Colis en cours d'acheminement";
    const delayed = "Colis retardé durant l'acheminement";
    const reason = `${delayed}. Livraison reportée de 24h`;
    const copies = [
      { ...row('unknown:moving', '2026-01-23T20:45:00+00:00', moving, 'in_transit'), ...hub },
      { ...row('unknown:delayed', '2026-01-23T20:45:00+00:00', reason, 'in_transit'), ...hub },
    ];
    const scans = [
      { ...scan('chronopost:moving', '2026-01-23T20:45:38Z', moving, 'in_transit'), ...hub },
      { ...scan('chronopost:delayed', '2026-01-23T20:45:38Z', delayed, 'in_transit'), ...hub },
    ];
    const own = scans.map((event) => ({ ...row(event.provider_event_id, '2026-01-23T20:45:38+00:00', event.description, event.stage), ...hub }));

    it('match the one whose wording opens with every word of the other\'s, whichever arrives first', () => {
      expect(pairs(sharedScans(scans, copies).reused))
        .toEqual([['chronopost:delayed', 'unknown:delayed'], ['chronopost:moving', 'unknown:moving']]);
      const late = sharedScans([{ ...scan('unknown:delayed', '2026-01-23T20:45:00Z', reason, 'in_transit'), ...hub }], own);
      expect(pairs(late.skipped)).toEqual([['unknown:delayed', 'chronopost:delayed']]);
      // A copy in the carrier's own second, left out beside the carrier's rows there, repeats that one too.
      const twin = sharedScans([{ ...scan('unknown:delayed', '2026-01-23T20:45:38Z', reason, 'in_transit'), ...hub }], own);
      expect(pairs(twin.skipped)).toEqual([['unknown:delayed', 'chronopost:delayed']]);
    });

    it('stay unmatched when two open alike, or none opens with every word', () => {
      const reasons = [...copies, { ...copies[1]!, provider_event_id: 'unknown:again', description: `${delayed}. Raison : météo` }];
      expect(pairs(sharedScans(scans, reasons).reused)).toEqual([['chronopost:moving', 'unknown:moving']]);
      const fewer = [copies[0]!, { ...copies[1]!, description: "Colis retardé à l'agence" }];
      expect(pairs(sharedScans(scans, fewer).reused)).toEqual([['chronopost:moving', 'unknown:moving']]);
    });

    // ParcelsApp gives two Austrian Post scans of one minute in English, dated to that minute.
    const tied = (id: string, description: string, order: number, stage = 'in_transit') => {
      const copy = scan(id, '2026-01-24T05:18:00.000Z', description, stage);
      return { ...copy, raw_data: { ...copy.raw_data, tie_order: order } };
    };
    const english = [tied('unknown:transit', 'Package in transit', 0), tied('unknown:arrived', 'Accepted in the destination country', 1)];
    const german = [row('austrian-post:arrived', '2026-01-24T05:18:09+00:00', 'Sendung im Zielland angekommen', 'in_transit'),
      row('austrian-post:transit', '2026-01-24T05:18:11+00:00', 'Sendung in Zustellung an Partner', 'in_transit')];

    it('pair as many scans of one source with them from the oldest when each pair\'s stages agree', () => {
      expect(pairs(sharedScans(english, german).skipped))
        .toEqual([['unknown:arrived', 'austrian-post:transit'], ['unknown:transit', 'austrian-post:arrived']]);
      // The carrier's scans take over the copies stored first.
      const carrier = german.map((own) => scan(String(own.provider_event_id), String(own.occurred_at), own.description, own.stage));
      expect(pairs(sharedScans(carrier, english.map((copy) => ({ ...row(copy.provider_event_id, '2026-01-24T05:18:00+00:00', copy.description, copy.stage), raw_data: copy.raw_data }))).reused))
        .toEqual([['austrian-post:arrived', 'unknown:transit'], ['austrian-post:transit', 'unknown:arrived']]);
      // Scans of one instant pair by delivery progress first.
      const progress = [tied('unknown:accepted', 'Accepted', 1, 'accepted'), tied('unknown:moving', 'Moving', 0)];
      const rows = [{ ...german[0]!, stage: 'accepted' }, german[1]!];
      expect(pairs(sharedScans(progress, rows).skipped))
        .toEqual([['unknown:accepted', 'austrian-post:arrived'], ['unknown:moving', 'austrian-post:transit']]);
    });

    it('stay unmatched in pairs whose stages disagree, or with fewer or more scans than rows', () => {
      const backwards = [{ ...german[0]!, stage: 'out_for_delivery' }, { ...german[1]!, stage: 'accepted' }];
      expect(sharedScans([tied('unknown:accepted', 'Accepted', 0, 'accepted'), tied('unknown:out', 'Out for delivery', 1, 'out_for_delivery')], backwards).skipped.size).toBe(0);
      expect(sharedScans([english[0]!], german).skipped.size).toBe(0);
      expect(sharedScans([...english, tied('unknown:again', 'Item processed', 2)], german).skipped.size).toBe(0);
    });
  });

  it('keeps every scan a carrier repeats in a minute, and an earlier carrier\'s relay of a delivery scan', () => {
    // The provider repeats a delivery a few seconds apart and shows both.
    const repeated = sharedScans([scan('unknown:first', '2026-01-14T10:37:00Z', 'Delivered', 'delivered'),
      scan('unknown:again', '2026-01-14T10:37:41Z', 'Delivered', 'delivered')],
    [row('unknown:first', '2026-01-14T10:37:00+00:00', 'Delivered', 'delivered')]);
    expect(repeated.skipped.size + repeated.reused.size).toBe(0);
    // A copy to the minute meets the delivery scan and the earlier carrier's relay of it.
    const relayed = sharedScans([scan('unknown:hub', '2026-01-11T21:11:00Z', 'Arrival at regional hub', 'in_transit')], [
      { ...row('gofo:hub', '2026-01-11T21:11:25+00:00', 'Arrived at GOFO Regional Hub', 'in_transit'), location: 'Springfield Gardens, NY' },
      { ...row('nova-poshta:hub', '2026-01-11T21:11:25.412+00:00', 'Arrived at the depot', 'in_transit'), location: 'Springfield Gardens, NY' },
    ]);
    expect(relayed.skipped.size).toBe(0);
    // An earlier carrier's relay to the minute or the millisecond, against a copy of the delivery scan.
    const copy = { ...row('unknown:customs', '2026-01-05T17:11:33+00:00', 'Import formalities complete', 'in_transit'), location: 'FR' };
    const relay = { ...scan('india-post:inbound', '2026-01-05T17:11:00Z', 'Send item to domestic location (Inb)', 'in_transit'), location: 'Office' };
    expect(sharedScans([relay], [copy], new Map(), 'india-post').reused.size).toBe(0);
    expect(sharedScans([{ ...relay, occurred_at: '2026-01-05T17:11:33.412Z' }], [copy], new Map(), 'india-post').reused.size).toBe(0);
    expect(sharedScans([{ ...scan('unknown:customs', copy.occurred_at, copy.description, 'in_transit'), location: 'FR' }],
      [{ ...row('india-post:inbound', '2026-01-05T17:11:00+00:00', relay.description, 'in_transit'), location: 'Office' }],
      new Map(), 'india-post').skipped.size).toBe(0);
    // With no handoff, a carrier's scan to the minute, or in the copy's second, is the one the copy repeats.
    expect(sharedScans([relay], [copy]).reused.size).toBe(1);
    expect(sharedScans([{ ...relay, occurred_at: '2026-01-05T17:11:33.412Z' }], [copy]).reused.size).toBe(1);
  });

  it('matches a zone off when only one of the times keeps milliseconds', () => {
    const own = scan('vinted-go:dropped', '2026-01-15T19:09:46.031Z', 'Parcel dropped off', 'accepted');
    const copy = row('unknown:dropped', '2026-01-15T17:09:46+00:00', 'Parcel dropped off', 'accepted');
    const shared = sharedScans([own], [copy]);
    expect(pairs(shared.reused)).toEqual([['vinted-go:dropped', 'unknown:dropped']]);
    expect([...shared.shifted]).toEqual(['vinted-go:dropped']);
    // Other wording, a stage that agrees, either way round.
    expect(pairs(sharedScans([scan('unknown:dropped', '2026-01-15T17:09:46Z', 'Handed in', 'pending')],
      [row('vinted-go:dropped', '2026-01-15T19:09:46.031+00:00', 'Parcel dropped off', 'accepted')]).skipped))
      .toEqual([['unknown:dropped', 'vinted-go:dropped']]);
    // Two times with their own milliseconds stay two scans.
    expect(sharedScans([own], [{ ...copy, occurred_at: '2026-01-15T17:09:46.500+00:00' }]).reused.size).toBe(0);
  });

  it('keeps ambiguous scans and rows unmatched', () => {
    const instant = '2026-01-04T12:00:00Z';
    // Two rows at the instant, neither worded alike.
    expect(sharedScans([scan('unknown:a', instant, 'Scan')],
      [row('gofo:a', instant, 'Sorted'), row('gofo:b', instant, 'Loaded')]).skipped.size).toBe(0);
    // One row, two new scans: only the one worded alike matches.
    expect(pairs(sharedScans([scan('unknown:a', instant, 'Loaded'), scan('unknown:b', instant, 'Sorted')],
      [row('gofo:a', instant, 'Loaded')]).skipped)).toEqual([['unknown:a', 'gofo:a']]);
    expect(sharedScans([scan('unknown:a', instant, 'Arrived'), scan('unknown:b', instant, 'Sorted')],
      [row('gofo:a', instant, 'Loaded')]).skipped.size).toBe(0);
    // Two shifted rows worded alike, or two scans for one row.
    expect(sharedScans([scan('unknown:a', '2026-01-04T15:00:00Z', 'Loaded')],
      [row('gofo:a', '2026-01-04T13:00:00+00:00', 'Loaded'), row('gofo:b', '2026-01-04T14:00:00+00:00', 'Loaded')]).skipped.size).toBe(0);
    expect(sharedScans([scan('unknown:a', '2026-01-04T15:00:00Z', 'Loaded'), scan('unknown:b', '2026-01-04T16:00:00Z', 'Loaded')],
      [row('gofo:a', '2026-01-04T13:00:00+00:00', 'Loaded')]).skipped.size).toBe(0);
  });

  it('never matches the app, rows this batch carries or took over, or observed transitions', () => {
    const instant = '2026-01-04T12:00:00Z';
    expect(sharedScans([scan('unknown:a', instant, 'Registered')], [row('app:registered', instant, 'Registered')]).skipped.size).toBe(0);
    // The row the batch already carries is not a copy's twin.
    expect(sharedScans([scan('unknown:a', instant, 'Loaded'), scan('gofo:a', '2026-01-04T09:00:00Z', 'Loaded')],
      [row('gofo:a', '2026-01-04T09:00:00+00:00', 'Loaded')]).skipped.size).toBe(0);
    // A row DPD's own rule took over is not offered again.
    expect(sharedScans([scan('dpd:b', instant, 'Delivered')], [row('unknown:a', instant, 'Delivered')],
      new Map([['dpd:a', 'unknown:a']])).reused.size).toBe(0);
    const observed = { ...scan('unknown:observed', instant, 'Delivered'),
      raw_data: { universal_provider: 'ParcelsApp', observed_without_provider_timestamp: true } };
    expect(sharedScans([observed], [row('gofo:delivered', instant, 'Delivered')]).skipped.size).toBe(0);
    expect(sharedScans([scan('unknown:a', instant, 'Delivered')], []).skipped.size).toBe(0);
  });

  it('retains real provider scans when the stored matching row is only an observation', () => {
    const observed = { ...row('mrw:observed', '2026-09-25T12:00:00Z', 'Delivered', 'delivered'),
      raw_data: { observed_without_provider_timestamp: true } };
    expect(sharedScans([scan('unknown:dated', '2026-09-25T12:00:00Z', 'Delivered', 'delivered')],
      [observed]).skipped.size).toBe(0);
    expect(sharedScans([scan('unknown:dated', '2026-09-25T10:00:00Z', 'Delivered', 'delivered')],
      [observed]).skipped.size).toBe(0);
  });
});

describe('a carrier\'s scan whose zone the scraper deduced', () => {
  // A wall clock in no zone, dated on a neighbouring scan's Pacific clock; a provider's copy states the carrier's
  // Mountain offset and adds seconds of its own. Wording and clocks are invented.
  const wording = 'In Transit to Next Facility';
  const deduced = { ...scan('usps:transit', '2026-01-14T17:40:00Z', wording, 'in_transit'), raw_data: {
    time: '2026-01-14T09:40:00-08:00', local_time: '2026-01-14T09:40:00', time_zone: 'America/Los_Angeles', time_zone_basis: 'neighbour' } };
  const stored = { ...row('usps:transit', '2026-01-14T17:40:00+00:00', wording, 'in_transit'),
    time: '2026-01-14T09:40:00-08:00', local_time: '2026-01-14T09:40:00', time_zone_basis: 'neighbour' };
  const stating = { provider_time_iso: '2026-01-14T09:40:18-07:00', time_provenance: 'carrier_reported' };
  const copy = (provenance = 'carrier_reported') => ({ ...scan('unknown:transit', '2026-01-14T16:40:18Z', wording, 'in_transit'),
    location: 'US', raw_data: { universal_provider: '17TRACK', ...stating, time_provenance: provenance } });
  const storedCopy = { ...row('unknown:transit', '2026-01-14T16:40:18+00:00', wording, 'in_transit'), location: 'US',
    universal_provider: '17TRACK', ...stating };

  it('leaves out a copy whose offset the provider inferred, as a clock offset', () => {
    const inferred = sharedScans([copy('provider_inferred')], [stored]);
    expect(pairs(inferred.skipped)).toEqual([['unknown:transit', 'usps:transit']]);
    expect(inferred.reused.size).toBe(0);
    expect([...inferred.shifted]).toEqual(['unknown:transit']);
  });

  it('lets a copy stating the carrier\'s offset take the row over, and the carrier\'s next scans keep that offset', () => {
    const taking = sharedScans([copy()], [stored]);
    expect(pairs(taking.reused)).toEqual([['unknown:transit', 'usps:transit']]);
    expect(taking.skipped.size + taking.shifted.size).toBe(0);
    const taken = { ...row('usps:transit', '2026-01-14T16:40:18+00:00', wording, 'in_transit'), location: 'US', ...stating };
    const [restated] = withStatedClocks([deduced], [taken]);
    expect(restated).toMatchObject({ provider_event_id: 'usps:transit', occurred_at: '2026-01-14T16:40:00Z',
      raw_data: { time: '2026-01-14T09:40:00-07:00', local_time: '2026-01-14T09:40:00', time_zone_basis: 'stated' } });
    expect(restated!.raw_data).not.toHaveProperty('time_zone');
    // The row the restated scan rewrote keeps the offset once no copy states it.
    const kept = { ...row('usps:transit', '2026-01-14T16:40:00+00:00', wording, 'in_transit'),
      time: '2026-01-14T09:40:00-07:00', local_time: '2026-01-14T09:40:00', time_zone_basis: 'stated' };
    expect(withStatedClocks([deduced], [kept])[0]).toMatchObject({ occurred_at: '2026-01-14T16:40:00Z' });
    expect(sharedScans([copy()], [kept]).skipped.size).toBe(1);
  });

  it('takes the offset of a copy stored first, then that copy\'s row within its minute', () => {
    const events = withStatedClocks([deduced], [storedCopy]);
    expect(events[0]).toMatchObject({ occurred_at: '2026-01-14T16:40:00Z' });
    const shared = sharedScans(events, [storedCopy]);
    expect(pairs(shared.reused)).toEqual([['usps:transit', 'unknown:transit']]);
    expect(shared.shifted.size).toBe(0);
  });

  it('keeps the deduced zone without one stated offset for its wall clock and wording', () => {
    for (const witness of [{ ...storedCopy, time_provenance: 'provider_inferred' }, { ...storedCopy, description: 'Arrived at Facility' },
      { ...storedCopy, provider_time_iso: '2026-01-14T09:41:18-07:00' }]) {
      expect(withStatedClocks([deduced], [witness])[0]).toBe(deduced);
    }
    const disagreeing = { ...storedCopy, provider_event_id: 'unknown:other', provider_time_iso: '2026-01-14T09:40:18-06:00' };
    expect(withStatedClocks([deduced], [storedCopy, disagreeing])[0]).toBe(deduced);
    // A provider's own scan, or a carrier's scan in a stated zone, has no deduced zone to replace.
    const universal = { ...deduced, provider_event_id: 'unknown:transit' };
    expect(withStatedClocks([universal], [storedCopy])[0]).toBe(universal);
    const placed = { ...deduced, raw_data: { time: '2026-01-14T09:40:00-08:00' } };
    expect(withStatedClocks([placed], [storedCopy])[0]).toBe(placed);
  });
});

describe('every copy at a carrier scan\'s instant', () => {
  const placed = <T extends object>(event: T, location: string) => ({ ...event, location });
  const named = <T extends object>(event: T, provider: string) => ({ ...event, universal_provider: provider });

  it('lets a lone scan take over one copy and absorb the others, two providers or one provider twice', () => {
    // InPost: two providers' pending copies of one locker scan.
    const instant = '2026-01-14T18:18:43.000Z';
    const stored = [row('unknown:store', instant, 'Received at the local store', 'pending'),
      named(row('unknown:stored', instant, 'Parcel Stored by Customer', 'pending'), 'Ship24')];
    const lone = sharedScans([scan('inpost:sent', instant, 'Sent through Parcel Locker', 'accepted')], stored);
    expect(pairs(lone.reused)).toEqual([['inpost:sent', 'unknown:store']]);
    expect([...lone.absorbed]).toEqual(['unknown:stored']);
    // Speedaf: one provider's three readings of one scan; the one in the same stage is taken over.
    const at = '2026-01-14T09:19:55.000Z';
    const three = sharedScans([scan('speedaf:received', at, 'Received in【Site】', 'in_transit')], [
      named(row('unknown:leaving', at, 'Leaving customs', 'pending'), 'ParcelsApp'),
      row('unknown:local', at, 'Received by local delivery company', 'in_transit'),
      row('unknown:site', at, 'Received in Site', 'in_transit'),
    ]);
    expect(pairs(three.reused)).toEqual([['speedaf:received', 'unknown:local']]);
    expect([...three.absorbed].sort()).toEqual(['unknown:leaving', 'unknown:site']);
  });

  it('absorbs the copies a carrier scan already stored left beside it', () => {
    // Geodis: the carrier's scan is stored, and so are two copies a provider wrote first.
    const instant = '2026-01-13T07:45:00.000Z';
    const own = placed(scan('geodis-pl:inbound', instant, 'Inbound Scan From Driver to Terminal', 'in_transit'), 'EXAMPLE TOWN');
    const shared = sharedScans([own], [row('geodis-pl:inbound', instant, own.description, 'in_transit'),
      row('unknown:unloaded', instant, 'Shipment Unloaded in Collection Terminal', 'in_transit'),
      placed(row('unknown:entrance', instant, 'Entrance to the terminal from the driver', 'in_transit'), 'EXAMPLE TOWN')]);
    expect(shared.reused.size).toBe(0);
    expect([...shared.absorbed].sort()).toEqual(['unknown:entrance', 'unknown:unloaded']);
  });

  it('keeps each of several carrier scans at one instant, and absorbs the copies of them', () => {
    // FedEx: two scans of its own in one minute, and a provider's two copies.
    const instant = '2026-01-08T19:52:00.000Z';
    const scans = [placed(scan('fedex:departed', instant, 'Departed FedEx hub', 'in_transit'), 'EXAMPLE TOWN NL'),
      placed(scan('fedex:way', instant, 'On the way', 'in_transit'), 'EXAMPLE TOWN NL')];
    const copies = [placed(row('unknown:departed', instant, 'Departed FedEx Location', 'in_transit'), 'Example Town, GE, NL'),
      placed(row('unknown:transit', instant, 'In Transit', 'in_transit'), 'Example Town, GE, NL')];
    const stored = scans.map((event) => placed(row(String(event.provider_event_id), instant, event.description, 'in_transit'), event.location));
    const healed = sharedScans(scans, [...stored, ...copies]);
    expect([...healed.absorbed].sort()).toEqual(['unknown:departed', 'unknown:transit']);
    expect(healed.reused.size).toBe(0);
    // New, each scan takes over one copy.
    const fresh = sharedScans(scans, copies);
    expect(pairs(fresh.reused)).toEqual([['fedex:departed', 'unknown:departed'], ['fedex:way', 'unknown:transit']]);
    expect(fresh.absorbed.size).toBe(0);
  });

  it('absorbs a copy in another classified stage, and keeps a row from another place, another instant, or one the batch writes', () => {
    // Austrian Post: the provider adds a gateway in another town and calls the handover an acceptance.
    const instant = '2026-01-16T01:15:00.000Z';
    const scans = [placed(scan('austrian-post:consolidated', instant, 'Konsolidierung für Export', 'in_transit'), 'Example Hub'),
      placed(scan('austrian-post:handed', instant, 'Übergabe an Kooperationspartner', 'in_transit'), 'Example Hub')];
    const stored = [...scans.map((event) => placed(row(String(event.provider_event_id), instant, event.description, 'in_transit'), 'Example Hub')),
      placed(row('unknown:gateway', instant, 'Departed from outbound gateway facility', 'in_transit'), 'Othertown, Austria'),
      placed(row('unknown:handed', instant, 'Item handed over to subcontractor', 'accepted'), 'Example, Austria'),
      placed(row('unknown:consolidated', instant, 'Transit unit consolidation', 'in_transit'), 'Example, Austria'),
      row('unknown:later', '2026-01-16T01:16:00.000Z', 'Transit unit consolidation', 'in_transit')];
    expect([...sharedScans(scans, stored).absorbed].sort()).toEqual(['unknown:consolidated', 'unknown:handed']);
    // The provider's own row in this batch stays, and so does a copy of an observation or an unclassified scan.
    expect([...sharedScans([...scans, scan('unknown:consolidated', instant, 'Transit unit consolidation', 'in_transit')], stored).absorbed])
      .toEqual(['unknown:handed']);
    const claimed = (shared: ReturnType<typeof sharedScans>) => shared.reused.size + shared.absorbed.size;
    expect(claimed(sharedScans([scans[0]!], [stored.at(-2)!]))).toBe(1);
    const observed = { ...scans[0]!, raw_data: { observed_without_provider_timestamp: true } };
    expect(claimed(sharedScans([observed], [stored.at(-2)!]))).toBe(0);
    expect(claimed(sharedScans([{ ...scans[0]!, stage: undefined }], [stored.at(-2)!]))).toBe(0);
  });

  it('leaves out a provider\'s later copies of a scan a carrier holds, so absorbed copies do not come back', () => {
    const instant = '2026-01-09T08:45:32.000Z';
    const carrier = row('speedaf:accepted', instant, 'Guangzhou warehouse', 'accepted');
    const copy = (id: string, description: string, stage: string) => scan(id, instant, description, stage);
    const shared = sharedScans([copy('unknown:a', 'Arrived at departure transport hub', 'pending'),
      copy('unknown:b', 'Guangzhou warehouse, International logistics', 'pending'),
      copy('unknown:c', 'Departed', 'in_transit'), scan('unknown:d', instant, 'Unclassified')], [carrier]);
    // The copy in another stage too; not an unclassified one.
    expect(pairs(shared.skipped)).toEqual([['unknown:a', 'speedaf:accepted'], ['unknown:b', 'speedaf:accepted'], ['unknown:c', 'speedaf:accepted']]);
    // A row the carrier took over names no provider and keeps the carrier's words: the provider's copies stay out, its own too.
    const takenOver = row('unknown:a', instant, 'Guangzhou warehouse', 'accepted');
    const again = sharedScans([copy('unknown:a', 'Arrived at departure transport hub', 'pending'),
      copy('unknown:b', 'Guangzhou warehouse, International logistics', 'pending')], [takenOver]);
    expect(pairs(again.skipped)).toEqual([['unknown:a', 'unknown:a'], ['unknown:b', 'unknown:a']]);
    // The carrier's place too; a row in the provider's own words and place, stored before rows named it, takes its rewrite.
    const placedCopy = { ...copy('unknown:a', 'Guangzhou warehouse', 'accepted'), location: 'Guangzhou, GD, CN' };
    expect(pairs(sharedScans([placedCopy], [{ ...takenOver, location: 'GUANGZHOU CN' }]).skipped)).toEqual([['unknown:a', 'unknown:a']]);
    expect(sharedScans([placedCopy], [{ ...takenOver, location: 'Guangzhou, GD, CN' }]).skipped.size).toBe(0);
    // Another provider's row is no carrier's.
    expect(sharedScans([copy('unknown:b', 'Guangzhou warehouse, International logistics', 'pending'),
      copy('unknown:e', 'Sorted', 'pending')], [named(takenOver, 'Ship24')]).skipped.size).toBe(0);
  });
});

describe('rows only a universal provider wrote', () => {
  // A provider first read a warehouse's clock as UTC, eight hours late; a scraper fix reads it on the warehouse's own.
  const named = <T extends object>(value: T, provider = 'ParcelsApp') => ({ ...value, universal_provider: provider });
  const late = named(row('unknown:sorted', '2026-01-03T21:15:42+00:00', 'Arrived at sorting center'));
  const sorted = scan('unknown:sorted', '2026-01-03T13:15:42Z', 'Arrived at sorting center');

  it('moves its own row to the provider\'s corrected clock, unless a carrier or another provider wrote it, or it predates the name', () => {
    const moved = sharedScans([sorted], [late]);
    expect(pairs(moved.reused)).toEqual([['unknown:sorted', 'unknown:sorted']]);
    expect(moved.skipped.size).toBe(0);
    expect([...moved.shifted]).toEqual(['unknown:sorted']);
    // A row a carrier took over, or one stored before rows named their provider, names none.
    for (const stored of [row('unknown:sorted', '2026-01-03T21:15:42+00:00', 'Arrived at sorting center'), named(late, 'Ship24')]) {
      const kept = sharedScans([sorted], [stored]);
      expect(kept.reused.size).toBe(0);
      expect(pairs(kept.skipped)).toEqual([['unknown:sorted', 'unknown:sorted']]);
      expect([...kept.shifted]).toEqual(['unknown:sorted']);
    }
  });

  it('takes over a row its provider alone wrote across an offset, not within the minute', () => {
    // The fix changed the time the identity hashes, so the scan meets its old row under another identity.
    const unnamed = row('unknown:departed-late', '2026-01-04T02:30:10+00:00', 'Departed from sorting center');
    const departed = named(unnamed);
    const corrected = scan('unknown:departed', '2026-01-03T18:30:10Z', 'Departed from sorting center');
    const moved = sharedScans([corrected], [departed]);
    expect(pairs(moved.reused)).toEqual([['unknown:departed', 'unknown:departed-late']]);
    expect(moved.skipped.size).toBe(0);
    expect([...moved.shifted]).toEqual(['unknown:departed']);
    expect(withIdentities([corrected], moved.reused, [departed])).toMatchObject([
      { provider_event_id: 'unknown:departed-late', occurred_at: '2026-01-03T18:30:10Z', raw_data: { universal_provider: 'ParcelsApp' } },
    ]);
    // A copy to the minute is no clock fix, and an unnamed row stays where it is.
    const minuted = sharedScans([scan('unknown:departed', '2026-01-04T02:30:00Z', 'Departed from sorting center')], [departed]);
    expect(pairs(minuted.skipped)).toEqual([['unknown:departed', 'unknown:departed-late']]);
    expect(minuted.reused.size).toBe(0);
    expect(pairs(sharedScans([corrected], [unnamed]).skipped)).toEqual([['unknown:departed', 'unknown:departed-late']]);
  });

  it('lets a scan a fix now classifies take over the row its provider alone wrote unclassified, at its instant', () => {
    // A Russian Post delivery through ParcelsApp, stored pending before the scraper read its words as a delivery.
    const at = '2026-01-05T00:19:54+00:00';
    const place = { location: 'EXAMPLE TOWN' };
    const pending = named({ ...row('unknown:handed', at, 'Handed to the addressee by the postman', 'pending'), ...place });
    const delivered = { ...scan('unknown:delivered', '2026-01-05T00:19:54Z', 'Delivered', 'delivered'), ...place };
    const taken = sharedScans([delivered], [pending]);
    expect(pairs(taken.reused)).toEqual([['unknown:delivered', 'unknown:handed']]);
    expect(taken.skipped.size + taken.shifted.size).toBe(0);
    expect(withIdentities([delivered], taken.reused, [pending])).toMatchObject([
      { provider_event_id: 'unknown:handed', stage: 'delivered', description: 'Delivered' },
    ]);
    // Another provider's row, a row with no provider's name, or the scan from another place is a copy, left out.
    for (const stored of [named(pending, 'Ship24'), { ...pending, universal_provider: undefined }]) {
      const kept = sharedScans([delivered], [stored]);
      expect(kept.reused.size).toBe(0);
      expect(pairs(kept.skipped)).toEqual([['unknown:delivered', 'unknown:handed']]);
    }
    const elsewhere = sharedScans([{ ...delivered, location: 'OTHER CITY' }], [pending]);
    expect(elsewhere.reused.size).toBe(0);
    expect(pairs(elsewhere.skipped)).toEqual([['unknown:delivered', 'unknown:handed']]);
    // A scan the provider still gives unclassified, or a row it classified, keeps its row as it is.
    expect(sharedScans([{ ...delivered, stage: 'pending' }], [pending]).reused.size).toBe(0);
    expect(sharedScans([delivered], [{ ...pending, stage: 'in_transit' }]).reused.size).toBe(0);
  });

  it('leaves the scan out when another row at the corrected instant already holds it', () => {
    // A carrier's own row of the scan stands where the provider's row would move.
    const carrier = row('speedaf:sorted', '2026-01-03T13:15:42+00:00', 'Arrived at sorting center');
    const twinned = sharedScans([sorted], [late, carrier]);
    expect(twinned.reused.size).toBe(0);
    expect(pairs(twinned.skipped)).toEqual([['unknown:sorted', 'unknown:sorted']]);
    expect([...twinned.shifted]).toEqual(['unknown:sorted']);
    expect([...twinned.absorbed]).toEqual(['unknown:sorted']);
    // Another scan's row there does not hold it.
    const other = row('speedaf:weighed', '2026-01-03T13:15:42+00:00', 'Weighed', 'accepted');
    const moved = sharedScans([sorted], [late, other]);
    expect(pairs(moved.reused)).toEqual([['unknown:sorted', 'unknown:sorted']]);
    expect(moved.absorbed.size).toBe(0);
  });

  it('drops its row on the old clock under another identity once another row holds the scan at the corrected instant', () => {
    // The provider read a hub on the wrong clock, three hours late, beside the scan another leg copied right.
    const hub = 'Carteret, NJ';
    const at = <T extends object>(value: T) => ({ ...value, location: hub });
    const label = scan('unknown:label', '2026-01-01T07:38:40Z', 'Shipping label created');
    const arrived = { ...scan('unknown:arrived', '2026-01-03T00:42:33Z', 'Arrived at origin hub'), location: hub };
    const right = named(at(row('unknown:arrived', '2026-01-03T00:42:33+00:00', 'Arrived at origin hub')));
    const stale = named(at(row('unknown:arrived-late', '2026-01-03T03:42:33+00:00', 'Arrived at origin hub')));
    expect([...sharedScans([label, arrived], [right, stale]).absorbed]).toEqual(['unknown:arrived-late']);
    // It stays while the answer shows its instant, does not reach back past it, or nothing else holds the scan.
    const again = { ...scan('unknown:again', '2026-01-03T03:42:33Z', 'Arrived at origin hub'), location: hub };
    expect(sharedScans([label, arrived, again], [right, stale]).absorbed.size).toBe(0);
    expect(sharedScans([arrived], [right, stale]).absorbed.size).toBe(0);
    expect(sharedScans([label, arrived], [stale]).absorbed.size).toBe(0);
    // Nor does a row another provider wrote, one that names none, or one at another place.
    for (const kept of [named(stale, 'Ship24'), at(row('unknown:arrived-late', '2026-01-03T03:42:33+00:00', 'Arrived at origin hub')),
      { ...stale, location: 'Eastvale, CA' }]) {
      expect(sharedScans([label, arrived], [right, kept]).absorbed.size).toBe(0);
    }
  });

  it('names the provider on its new rows and the rows it named, never on a row a carrier or an earlier release wrote', () => {
    const scans = [sorted, scan('unknown:new', '2026-01-03T15:00:00Z', 'Loaded'), scan('unknown:copy', '2026-01-03T16:00:00Z', 'Sorted')];
    const stored = [late, row('unknown:taken', '2026-01-03T16:00:00+00:00', 'Sorted')];
    const persisted = withIdentities(scans, new Map([['unknown:copy', 'unknown:taken']]), stored);
    expect(persisted.map((event) => [event.provider_event_id, (event.raw_data as Record<string, unknown>).universal_provider])).toEqual([
      ['unknown:sorted', 'ParcelsApp'], ['unknown:new', 'ParcelsApp'], ['unknown:taken', undefined],
    ]);
  });
});

describe('a provider\'s copy a whole number of hours from the carrier\'s row', () => {
  // UPS dates its scans to the second; a provider kept the minute of its customs scan, read 11 h late.
  const place = { location: 'Louisville, KY, US', stage: 'customs', raw_data: { provider_code: 'XC' } };
  const own = { ...row('ups:customs', '2026-01-04T12:09:15+00:00', 'Clearing agency'), ...place };
  const copy = { ...row('unknown:customs', '2026-01-04T23:09:00+00:00', 'Clearing agency'), ...place, universal_provider: 'ParcelsApp' };
  const ownScan = { ...scan('ups:customs', '2026-01-04T12:09:15Z', 'Clearing agency'), ...place };
  const read = (id: string, at: string) => ({ ...scan(id, at, 'Clearing agency'), ...place, universal_provider: 'ParcelsApp' });

  it('goes with any check, and its provider\'s copy at that time stays out', () => {
    expect([...sharedScans([ownScan], [own, copy]).absorbed]).toEqual(['unknown:customs']);
    expect([...sharedScans([scan('unknown:other', '2026-01-05T08:00:00Z', 'Delivered')], [own, copy]).absorbed]).toEqual(['unknown:customs']);
    // The carrier's row reaches the parcel in this check.
    expect([...sharedScans([ownScan], [copy]).absorbed]).toEqual(['unknown:customs']);
    const again = sharedScans([read('unknown:customs', '2026-01-04T23:09:00Z')], [own, copy]);
    expect(pairs(again.skipped)).toEqual([['unknown:customs', 'ups:customs']]);
    expect([...again.absorbed]).toEqual(['unknown:customs']);
    const later = sharedScans([read('unknown:late', '2026-01-04T23:09:00Z')], [own]);
    expect(pairs(later.skipped)).toEqual([['unknown:late', 'ups:customs']]);
  });

  it('stays when it may be a scan of its own', () => {
    const kept = (rows: JsonObject[]) => sharedScans([ownScan], rows).absorbed.size;
    expect(kept([own, { ...copy, location: 'Philadelphia, PA, US' }])).toBe(0);
    expect(kept([own, { ...copy, description: 'Import scan' }])).toBe(0);
    expect(kept([own, { ...copy, raw_data: { provider_code: 'XD' } }])).toBe(0);
    // Seconds of its own, or not a whole number of hours, or more than 14 h away.
    expect(kept([own, { ...copy, occurred_at: '2026-01-04T23:09:30+00:00' }])).toBe(0);
    expect(kept([own, { ...copy, occurred_at: '2026-01-04T22:39:00+00:00' }])).toBe(0);
    expect(kept([own, { ...copy, occurred_at: '2026-01-05T03:09:00+00:00' }])).toBe(0);
    // The carrier has a scan of its own at the copy's minute, or two copies match one row.
    expect(kept([own, copy, { ...own, provider_event_id: 'ups:customs-again', occurred_at: '2026-01-04T23:09:40+00:00' }])).toBe(0);
    expect(kept([own, copy, { ...copy, provider_event_id: 'unknown:customs-again', occurred_at: '2026-01-04T21:09:00+00:00' }])).toBe(0);
    // An earlier release's row that names no provider holds the scan at the copy's minute, or is the only row a whole number of hours away.
    expect(kept([own, copy, { ...own, provider_event_id: 'unknown:legacy', occurred_at: '2026-01-04T23:09:00+00:00' }])).toBe(0);
    const delivered = scan('unknown:other', '2026-01-05T08:00:00Z', 'Delivered');
    expect(sharedScans([delivered], [{ ...own, provider_event_id: 'unknown:legacy' }, copy]).absorbed.size).toBe(0);
    // A copy that names no provider may be the carrier's row an earlier release wrote.
    expect(kept([own, { ...copy, universal_provider: undefined }])).toBe(0);
    // A row the batch's carrier scan takes over is that scan's.
    expect(sharedScans([{ ...scan('ups:other', '2026-01-04T23:09:00Z', 'Clearing agency'), ...place }], [own, copy]).absorbed.size).toBe(0);
    // The provider reads the scan at the carrier's minute too, or still at the copy's under another identity.
    expect(sharedScans([ownScan, read('unknown:read', '2026-01-04T12:09:00Z')], [own, copy]).absorbed.has('unknown:customs')).toBe(false);
    expect(sharedScans([read('unknown:customs-again', '2026-01-04T23:09:00Z')], [own, copy]).absorbed.size).toBe(0);
  });
});

describe('a provider\'s copies on a clock read in another zone', () => {
  // A carrier gives India's wall clock to the minute; a provider read it as US Eastern and added seconds of its own.
  const hub = { location: 'Example Hub, MH' };
  const own = (id: string, at: string, description: string, stage = 'in_transit') => ({ ...row(`shiprocket:${id}`, at, description, stage), ...hub });
  const copy = (id: string, at: string, description: string, stage = 'in_transit', provider = '17TRACK') =>
    ({ ...row(`unknown:${id}`, at, description, stage), ...hub, universal_provider: provider });
  const carrier = [
    own('booked', '2026-10-13T04:04:00+00:00', 'Shipment booked', 'registered'),
    own('picked', '2026-10-13T08:56:00+00:00', 'Picked up', 'accepted'),
    own('hub', '2026-10-13T12:33:00+00:00', 'Reached hub'),
    own('sorted', '2026-10-13T13:05:00+00:00', 'Sorted'),
  ];
  const booked = copy('booked', '2026-10-13T13:34:01+00:00', 'Shipment booked', 'registered');
  const sorted = copy('sorted', '2026-10-13T22:35:21+00:00', 'Sorted');
  // Read as another stage, or in other words.
  const picked = copy('picked', '2026-10-13T18:26:21+00:00', 'Picked up');
  const reached = copy('hub', '2026-10-13T22:03:41+00:00', 'Arrived at hub');
  const delivered = scan('shiprocket:delivered', '2026-10-16T10:00:00Z', 'Delivered', 'delivered');
  const left = (stored: JsonObject[]) => [...sharedScans([delivered], [...carrier, ...stored]).absorbed].sort();

  it('go once two of the provider\'s copies show the shift, in any wording or stage at the carrier row\'s place', () => {
    expect(left([booked, sorted, picked, reached])).toEqual(['unknown:booked', 'unknown:hub', 'unknown:picked', 'unknown:sorted']);
    // Another provider's copies of the same rows go too.
    const again = [copy('booked-again', '2026-10-13T13:34:00+00:00', 'Shipment booked', 'registered', 'Ship24'),
      copy('sorted-again', '2026-10-13T22:35:00+00:00', 'Sorted', 'in_transit', 'Ship24')];
    expect(left([booked, sorted, ...again])).toEqual(['unknown:booked', 'unknown:booked-again', 'unknown:sorted', 'unknown:sorted-again']);
  });

  it('stay when the shift is one copy\'s, or nothing the carrier holds sits there', () => {
    expect(left([booked, picked, reached])).toEqual([]);
    expect(left([booked, sorted, copy('extra', '2026-10-13T23:30:06+00:00', 'Out of hub')])).toEqual(['unknown:booked', 'unknown:sorted']);
    // Another place, or a carrier row at the copy's own minute and place.
    expect(left([booked, sorted, { ...reached, location: 'Other Hub, MH' }])).toEqual(['unknown:booked', 'unknown:sorted']);
    expect(left([booked, sorted, reached, own('late', '2026-10-13T22:03:00+00:00', 'Departed hub')])).toEqual(['unknown:booked', 'unknown:sorted']);
    // Two shifts shown as often: neither is the provider's clock.
    const early = [copy('picked', '2026-10-13T16:26:21+00:00', 'Picked up', 'accepted'), copy('hub', '2026-10-13T20:03:41+00:00', 'Reached hub')];
    expect(left([booked, sorted, ...early])).toEqual([]);
  });
});

describe('a provider\'s copies on a carrier\'s wall clocks under another offset', () => {
  // A carrier gives its US scans their own offsets, its town in brackets; a provider gave the wall clocks as UTC, in its own words and places.
  const own = (id: string, at: string, time: string, description: string, values: JsonObject = {}) =>
    ({ ...row(`aliexpress:${id}`, at, description, 'in_transit'), raw_data: { time }, ...values });
  const copy = (id: string, time: string, description: string, values: JsonObject = {}) => ({
    ...row(`unknown:${id}`, time.replace('.000Z', '+00:00'), description, 'in_transit'), location: 'Usa, Example City',
    raw_data: { universal_provider: 'ParcelsApp', time }, ...values,
  });
  const carrier = [
    own('sorted', '2026-10-22T22:49:00+00:00', '2026-10-22T18:49:00-04:00', '[USA,Example City] Arrived at sorting center'),
    own('relabelled', '2026-10-23T02:09:00+00:00', '2026-10-22T22:09:00-04:00', '[USA,Example City] Tracking number changed'),
    own('depot', '2026-10-24T17:33:00+00:00', '2026-10-24T12:33:00-05:00', 'Arrived at delivery center'),
  ];
  const sorted = copy('sorted', '2026-10-22T18:49:00.000Z', 'At sorting center');
  const relabelled = copy('relabelled', '2026-10-22T22:09:00.000Z', 'Package relabeled');
  const depot = copy('depot', '2026-10-24T12:33:00.000Z', 'Arrived at delivery center', { location: 'Usa, Other Town' });
  const delivered = { ...scan('aliexpress:delivered', '2026-10-25T15:00:00Z', 'Delivered', 'delivered'), raw_data: { time: '2026-10-25T10:00:00-05:00' } };
  const left = (stored: JsonObject[], events: JsonObject[] = [delivered]) => [...sharedScans(events, stored).absorbed].sort();
  const asScan = (stored: JsonObject) => ({ ...stored, package_id: 'package-1', occurred_at: String(stored.occurred_at).replace('+00:00', 'Z') });

  it('go with any check once the provider reads two of them so, in any wording and place', () => {
    expect(left([...carrier, sorted, relabelled, depot])).toEqual(['unknown:depot', 'unknown:relabelled', 'unknown:sorted']);
    // The batch carries the carrier's scans, already stored, and the provider's next copy of one stays out.
    expect(left([...carrier, sorted, relabelled], carrier.map(asScan))).toEqual(['unknown:relabelled', 'unknown:sorted']);
    const again = sharedScans([asScan(sorted)], [...carrier, relabelled]);
    expect(pairs(again.skipped)).toEqual([['unknown:sorted', 'aliexpress:sorted']]);
    // One copy alone goes when its wording opens alike.
    expect(left([...carrier, depot])).toEqual(['unknown:depot']);
  });

  it('are taken over by the carrier\'s scans that reach the parcel after them', () => {
    const shared = sharedScans(carrier.map(asScan), [sorted, relabelled, depot]);
    expect(pairs(shared.reused)).toEqual([
      ['aliexpress:depot', 'unknown:depot'], ['aliexpress:relabelled', 'unknown:relabelled'], ['aliexpress:sorted', 'unknown:sorted'],
    ]);
    expect([...shared.shifted].sort()).toEqual(['aliexpress:depot', 'aliexpress:relabelled', 'aliexpress:sorted']);
    expect(shared.absorbed.size).toBe(0);
  });

  it('stay when one copy alone shows the clock in other wording, or another stage, minute or place', () => {
    expect(left([...carrier, sorted])).toEqual([]);
    expect(left([...carrier, sorted, { ...relabelled, stage: 'delivered' }])).toEqual([]);
    expect(left([...carrier, sorted, { ...relabelled, raw_data: { universal_provider: 'ParcelsApp', time: '2026-10-22T22:10:00.000Z' },
      occurred_at: '2026-10-22T22:10:00+00:00' }])).toEqual([]);
    expect(left([{ ...carrier[0]!, location: 'Example City' }, carrier[1]!, carrier[2]!, { ...sorted, location: 'Usa, Elsewhere' }, relabelled]))
      .toEqual([]);
    // Another provider's copy of the carrier's scan is not the reader's, nor is a copy that names no provider.
    expect(left([...carrier, relabelled, { ...sorted, raw_data: { universal_provider: '17TRACK', time: '2026-10-22T18:49:00.000Z' } }]))
      .toEqual([]);
    expect(left([...carrier, relabelled, { ...sorted, raw_data: { time: '2026-10-22T18:49:00.000Z' } }])).toEqual([]);
    // A copy at the carrier's own instant shows no other clock.
    expect(left([...carrier, sorted, { ...relabelled, raw_data: { universal_provider: 'ParcelsApp', time: '2026-10-22T22:09:00-04:00' },
      occurred_at: '2026-10-23T02:09:00+00:00' }])).toEqual([]);
  });
});

describe('a provider\'s scans on a clock whole hours off another provider\'s', () => {
  // One provider dated a post's scans right; the other read the post's summer time as London's, an hour late, in words of its own.
  const theirs = (id: string, at: string, description: string, values: JsonObject = {}) => ({
    ...row(`unknown:early-${id}`, at, description, 'in_transit'), location: 'United Kingdom',
    raw_data: { universal_provider: 'ParcelsApp' }, ...values,
  });
  const ours = (id: string, at: string, description: string, values: JsonObject = {}) => ({
    ...scan(`unknown:late-${id}`, at, description, 'in_transit'), raw_data: { universal_provider: '17TRACK' }, ...values,
  });
  const stored = [
    theirs('received', '2026-10-22T01:21:00+00:00', 'The item is received by the post'),
    theirs('processed', '2026-10-22T01:22:00+00:00', 'The item is processed at the sorting center'),
    theirs('transport', '2026-10-22T01:23:00+00:00', 'The item is on transport to the country of destination'),
    theirs('at-centre', '2026-10-22T02:21:00+00:00', 'The item is at the sorting center'),
    theirs('sorted', '2026-10-22T02:21:00+00:00', 'The item is processed at the sorting center'),
  ];
  const received = ours('received', '2026-10-22T02:21:00Z', 'RECEIVED BY PARTNER CARRIER');
  const processed = ours('processed', '2026-10-22T02:22:00Z', 'PROCESSED INTERNATIONAL FREIGHT DEPOT');
  const transit = ours('transit', '2026-10-22T02:23:00Z', 'IN TRANSIT');
  const depot = ours('depot', '2026-10-22T03:21:00Z', 'RECEIVED INTERNATIONAL FREIGHT DEPOT');
  const sorted = ours('sorted', '2026-10-22T03:21:00Z', 'PROCESSED INTERNATIONAL FREIGHT DEPOT');
  const departed = ours('departed', '2026-10-22T03:40:41Z', 'DEPARTED SITE');
  const answer = [received, processed, transit, depot, sorted, departed];
  const left = (events: JsonObject[], rows: JsonObject[] = stored) => [...sharedScans(events, rows).skipped.keys()].sort();

  it('stay out with the shift flagged, one at the instant of the other provider\'s own scans too', () => {
    const shared = sharedScans(answer, stored);
    const copies = ['unknown:late-depot', 'unknown:late-processed', 'unknown:late-received', 'unknown:late-sorted', 'unknown:late-transit'];
    expect([...shared.skipped.keys()].sort()).toEqual(copies);
    expect([...shared.shifted].sort()).toEqual(copies);
    expect(shared.skipped.get('unknown:late-received')).toBe('unknown:early-received');
    expect(shared.skipped.get('unknown:late-transit')).toBe('unknown:early-transport');
    expect(['unknown:early-at-centre', 'unknown:early-sorted']).toContain(shared.skipped.get('unknown:late-depot'));
    expect(shared.reused.size + shared.absorbed.size).toBe(0);
  });

  it('stay out when the provider that came first read the clock right, whichever provider it is', () => {
    // China Post's scans as 17TRACK dated them, and ParcelsApp's copies in whole minutes on China's wall clock read as UTC.
    const china = [
      { ...theirs('posted', '2026-10-10T07:55:49+00:00', 'Posted', { stage: 'accepted' }), raw_data: { universal_provider: '17TRACK' } },
      { ...theirs('sorting', '2026-10-10T11:55:37+00:00', 'Sorted, ready to leave'), raw_data: { universal_provider: '17TRACK' } },
      { ...theirs('export', '2026-10-10T13:58:35+00:00', 'Handed to export customs', { stage: 'customs' }),
        raw_data: { universal_provider: '17TRACK' } },
    ];
    const copy = (id: string, at: string, description: string, stage: string) =>
      ({ ...scan(`unknown:${id}`, at, description, stage), location: null });
    expect(left([copy('posting', '2026-10-10T15:55:00Z', 'Posting/Collection', 'accepted'),
      copy('departed', '2026-10-10T19:55:00Z', 'Departed office of exchange', 'in_transit'),
      copy('customs', '2026-10-10T21:58:00Z', 'Presented to export customs', 'customs')], china))
      .toEqual(['unknown:customs', 'unknown:departed', 'unknown:posting']);
  });

  it('are kept when one scan alone shows the shift, when as many meet the other\'s instants, or in another stage or place', () => {
    const flagged = (events: JsonObject[], rows: JsonObject[] = stored) => [...sharedScans(events, rows).shifted].sort();
    expect(flagged([received, departed])).toEqual([]);
    expect(flagged([processed])).toEqual([]);
    // Two scans at the other provider's own instants weigh as much as two at the shift.
    expect(flagged([ours('a', '2026-10-22T01:21:00Z', 'Arrived'), ours('b', '2026-10-22T01:22:00Z', 'Sorted'), transit, depot])).toEqual([]);
    expect(flagged([processed, { ...transit, stage: 'delivered' }])).toEqual([]);
    expect(flagged([processed, transit].map((item) => ({ ...item, location: 'Amsterdam' })),
      stored.map((item) => ({ ...item, location: 'Example City' })))).toEqual([]);
    // A provider's own rows, and scans at whole hours, show no other provider's clock.
    expect(flagged([processed, transit], stored.map((item) => ({ ...item, raw_data: { universal_provider: '17TRACK' } })))).toEqual([]);
    expect(flagged([ours('x', '2026-10-22T03:00:00Z', 'Arrived at hub'), ours('y', '2026-10-22T05:00:00Z', 'Arrived at hub')],
      [theirs('x', '2026-10-22T02:00:00+00:00', 'Hub'), theirs('y', '2026-10-22T04:00:00+00:00', 'Hub')])).toEqual([]);
  });
});

describe('a stage change the sync saw without a time', () => {
  const sighting = { ...row('dpd-uk:observed', '2026-10-20T16:11:32.240+00:00', 'Order details received', 'registered'),
    location: null, observed_without_provider_timestamp: true };
  const dated = (values: JsonObject = {}) => ({ ...scan('dpd-uk:registered', '2026-09-22T18:09:00Z', 'Order details received', 'registered'),
    time: '2026-09-22T19:09:00+01:00', ...values });
  const none = { reused: new Map<string, string>(), skipped: new Map<string, string>() };
  /** Each sighting a scan dates: the scan that takes its row over, or `absorbed`. */
  const dating = (events: JsonObject[], stored: JsonObject[] = [sighting], matches = none) => {
    const found = datedObservations(events, stored, matches);
    return [...[...found.reused].map(([scanId, row]) => [row, scanId]), ...[...found.absorbed].map((row) => [row, 'absorbed']),
      ...[...found.skipped].map((row) => [row, 'skipped'])];
  };

  it('is the scan one source dates with the same stage, wording, code and place', () => {
    expect(dating([dated()])).toEqual([['dpd-uk:observed', 'dpd-uk:registered']]);
    // A place where the sighting names none.
    expect(dating([dated({ location: 'London' })])).toEqual([['dpd-uk:observed', 'dpd-uk:registered']]);
    // The wall clock it kept, in the scan's zone or another.
    const clocked = (local: string) => dating([dated()], [{ ...sighting, local_time: local }]).length;
    expect(clocked('2026-09-22T19:09:00')).toBe(1);
    expect(clocked('2026-09-22T18:09:00')).toBe(1);
    expect(clocked('2026-09-22')).toBe(1);
    expect(clocked('2026-09-22T19:10:00')).toBe(0);
    expect(clocked('2026-09-25')).toBe(0);
  });

  it('takes the row over only for a scan new to the parcel, from its source or dating a provider\'s sighting', () => {
    // A provider's copy of a carrier's sighting would leave the carrier's row holding the provider's clock.
    expect(dating([dated({ provider_event_id: 'unknown:registered', raw_data: { universal_provider: 'ParcelsApp' } })]))
      .toEqual([['dpd-uk:observed', 'absorbed']]);
    expect(dating([dated()], [{ ...sighting, provider_event_id: 'unknown:observed' }])).toEqual([['unknown:observed', 'dpd-uk:registered']]);
    // Stored already, or holding another row.
    expect(dating([dated()], [sighting, dated()])).toEqual([['dpd-uk:observed', 'absorbed']]);
    expect(dating([dated()], [sighting], { ...none, reused: new Map([['dpd-uk:registered', 'unknown:copy']]) }))
      .toEqual([['dpd-uk:observed', 'absorbed']]);
    expect(dating([dated()], [sighting], { ...none, skipped: new Map([['dpd-uk:registered', 'ups:twin']]) }))
      .toEqual([['dpd-uk:observed', 'absorbed']]);
    // A scan dated to the day alone could not find the row again.
    expect(dating([dated({ time: undefined, local_time: '2026-09-22' })])).toEqual([['dpd-uk:observed', 'absorbed']]);
    // One scan takes one row over.
    expect(dating([dated()], [sighting, { ...sighting, provider_event_id: 'dpd-uk:observed-again' }]))
      .toEqual([['dpd-uk:observed', 'dpd-uk:registered'], ['dpd-uk:observed-again', 'absorbed']]);
  });

  it('is a stored row too when it kept the time of day, whether stored or new', () => {
    const clocked = { ...sighting, local_time: '2026-09-22T19:09:00' };
    // A provider dated the scan before the carrier's answer on local clocks was seen.
    expect(dating([], [clocked, dated()])).toEqual([['dpd-uk:observed', 'absorbed']]);
    expect(dating([clocked], [dated()])).toEqual([['dpd-uk:observed', 'skipped']]);
    expect(dating([clocked], [clocked, dated()])).toEqual([['dpd-uk:observed', 'absorbed'], ['dpd-uk:observed', 'skipped']]);
    // Without the time of day, a stage seen twice in the same words may be two scans.
    expect(dating([], [sighting, dated()])).toEqual([]);
    expect(dating([{ ...sighting, local_time: '2026-09-22' }], [dated()])).toEqual([]);
    expect(dating([clocked], [dated({ occurred_at: '2026-09-22T18:10:00Z' })])).toEqual([]);
  });

  it('goes in words its source no longer gives, once the newest scan before it is of its stage', () => {
    const reworded = (values: JsonObject = {}) => dated({ description: 'Order data received', ...values });
    expect(dating([reworded()])).toEqual([['dpd-uk:observed', 'absorbed']]);
    expect(dating([reworded({ provider_event_id: 'dpd-uk:booked', occurred_at: '2026-09-20T10:00:00Z', description: 'Label printed' }), reworded()]))
      .toEqual([['dpd-uk:observed', 'absorbed']]);
    const left = (events: JsonObject[], stored: JsonObject[] = [sighting]) => dating(events, stored).length;
    // The answer still gives its words, in another row or stage.
    expect(left([reworded(), dated({ provider_event_id: 'dpd-uk:again', stage: 'accepted', occurred_at: '2026-10-20T17:00:00Z' })])).toBe(0);
    // A scan of another stage came between, the only scan of its stage came after it, or none came.
    expect(left([reworded(), reworded({ provider_event_id: 'dpd-uk:picked', occurred_at: '2026-09-23T08:00:00Z', stage: 'accepted' })])).toBe(0);
    expect(left([reworded({ occurred_at: '2026-10-20T17:00:00Z' })])).toBe(0);
    expect(left([])).toBe(0);
    // Another source's answer, or another universal provider's.
    expect(left([reworded({ provider_event_id: 'dpd:registered' })])).toBe(0);
    expect(left([reworded({ provider_event_id: 'unknown:registered', universal_provider: 'Ship24' })],
      [{ ...sighting, provider_event_id: 'unknown:observed', universal_provider: 'ParcelsApp' }])).toBe(0);
    // Another code.
    expect(left([reworded({ provider_code: '02' })], [{ ...sighting, provider_code: '01' }])).toBe(0);
  });

  it('stays when no one scan dates it', () => {
    const left = (events: JsonObject[], values: JsonObject = {}) => dating(events, [{ ...sighting, ...values }]).length;
    expect(left([dated({ stage: 'accepted' })])).toBe(0);
    expect(left([dated({ raw_data: { provider_code: '02' } })], { provider_code: '01' })).toBe(0);
    expect(left([dated({ location: 'London' })], { location: 'Leeds' })).toBe(0);
    expect(left([dated({ occurred_at: '2026-10-20T17:00:00Z' })])).toBe(0);
    expect(left([dated({ location: 'London' }), dated({ provider_event_id: 'dpd-uk:again', location: 'Leeds' })])).toBe(0);
    expect(left([dated({ raw_data: { observed_without_provider_timestamp: true } })])).toBe(0);
    expect(left([dated()], { observed_without_provider_timestamp: undefined })).toBe(0);
  });
});

describe('an earlier carrier relaying the delivery carrier\'s scans', () => {
  const at = (id: string, occurredAt: string, description: string, location: string, stage = 'in_transit') => ({
    ...scan(id, occurredAt, description, stage), location,
  });
  const delivery = [
    at('gofo:hub', '2026-01-11T21:11:25Z', 'Arrived at GOFO Regional Hub', 'Springfield Gardens, NY'),
    at('gofo:station', '2026-01-14T09:31:13Z', 'Arrived at GOFO Delivery Station', 'Hollywood, FL'),
  ];

  it('finds its copy of a delivery scan in the same second and place, in any wording', () => {
    const relays = [
      at('nova-poshta:hub', '2026-01-11T21:11:25Z', 'Arrived at the depot', 'Springfield Gardens, NY'),
      at('nova-poshta:station', '2026-01-14T09:31:13.412Z', 'Arrived at the address depot', 'Hollywood, FL'),
      // A copy with no place, or a pending one, is still a copy.
      at('nova-poshta:bare', '2026-01-11T21:11:25Z', 'Arrived at the depot', '', 'pending'),
      // So is one in another stage, which relayCopies.ts pairs too (an origin may call the partner's return an exception).
      at('nova-poshta:customs', '2026-01-11T21:11:25Z', 'Customs clearance in progress', 'Springfield Gardens, NY', 'customs'),
    ];
    expect([...relayedScans(relays, delivery)]).toEqual(['nova-poshta:hub', 'nova-poshta:station', 'nova-poshta:bare', 'nova-poshta:customs']);
    // On a minute's first second, a scan in another stage relays one whose place shares a word with its own.
    const minute = [at('gofo:sort', '2026-01-12T08:20:00Z', 'Processed at GOFO Sort Center', 'Miami, FL', 'accepted')];
    expect([...relayedScans([at('nova-poshta:sort', '2026-01-12T08:20:00Z', 'Sorted', 'Miami Gardens, FL')], minute)])
      .toEqual(['nova-poshta:sort']);
    // One of the two keeps minutes only, the other seconds: in that minute, in any stage, from places whose names do
    // not conflict, as relayCopies.ts pairs them. A postcode or an office number names no other place.
    const minutes = [
      at('nova-poshta:to-the-minute', '2026-01-14T09:31:00Z', 'Customs clearance in progress', 'Hollywood, FL', 'customs'),
      at('nova-poshta:to-the-second', '2026-01-12T08:20:41Z', 'Sorted', '00011'),
    ];
    expect([...relayedScans(minutes, [...delivery, ...minute.map((row) => ({ ...row, location: '00022' }))])])
      .toEqual(['nova-poshta:to-the-minute', 'nova-poshta:to-the-second']);
  });

  it('leaves its own scans: another second, another place, another stage on a minute\'s first second, or an observation', () => {
    const own = [
      at('nova-poshta:handover', '2026-01-11T21:01:29Z', 'Transferred to delivery partner GoFo Express', 'Jamaica, NY'),
      at('nova-poshta:hub', '2026-01-11T21:11:26Z', 'Arrived at the depot', 'Springfield Gardens, NY'),
      at('nova-poshta:lviv', '2026-01-11T21:11:25Z', 'Left the sorting center', 'Lviv'),
      at('nova-poshta:lviv-customs', '2026-01-11T21:11:25Z', 'Customs clearance in progress', 'Lviv', 'customs'),
      { ...at('nova-poshta:seen', '2026-01-11T21:11:25Z', 'In transit', 'Springfield Gardens, NY'), observed_without_provider_timestamp: true },
      // A carrier keeping minutes gives every scan a minute's first second: in another stage, with no place in
      // common, the two are scans of their own.
      at('nova-poshta:minute', '2026-01-12T08:20:00Z', 'Sorted', ''),
      // Nor is a minute's first second and a scan with seconds one when their places' names conflict, or in the
      // next minute.
      at('nova-poshta:lviv-minute', '2026-01-14T09:31:00Z', 'Left the sorting center', 'Lviv'),
      at('nova-poshta:next-minute', '2026-01-14T09:32:00Z', 'Arrived at the address depot', 'Hollywood, FL'),
      at('nova-poshta:minute-before', '2026-01-12T08:19:59Z', 'Sorted', 'Miami, FL'),
    ];
    const minute = [...delivery, at('gofo:sort', '2026-01-12T08:20:00Z', 'Processed at GOFO Sort Center', 'Miami, FL', 'accepted')];
    expect(relayedScans(own, minute).size).toBe(0);
    expect(relayedScans(delivery, []).size).toBe(0);
  });
});

describe('newest-event times set by copies read in the wrong zone', () => {
  const at = (iso: string) => Date.parse(iso);
  const none = { reused: new Map<string, string>(), skipped: new Map<string, string>() };
  const twin = row('gofo:out', '2026-01-04T12:59:03+00:00', 'Out for Delivery');
  const copy = scan('unknown:out', '2026-01-04T15:59:03Z', 'Out for Delivery');
  const skipped = { reused: new Map<string, string>(), skipped: new Map([['unknown:out', 'gofo:out']]) };

  it('reads a skipped copy at its stored twin instant', () => {
    const late = at('2026-01-04T15:59:03Z');
    expect(withoutCopyDrift(late, [copy], [twin], skipped)).toBe(at('2026-01-04T12:59:03Z'));
    // A newer scan of the batch, or a time the batch rows do not carry, still counts.
    expect(withoutCopyDrift(late, [copy, scan('unknown:new', '2026-01-04T14:00:00Z', 'Arrived')], [twin], skipped))
      .toBe(at('2026-01-04T14:00:00Z'));
    expect(withoutCopyDrift(late, [copy], [twin], skipped, { also: [at('2026-01-04T14:30:00Z')] })).toBe(at('2026-01-04T14:30:00Z'));
    // The router caps a future-dated copy at the time of the check: the copy still explains it.
    expect(withoutCopyDrift(at('2026-01-04T15:00:00Z'), [copy], [twin], skipped)).toBe(at('2026-01-04T12:59:03Z'));
  });

  it('keeps a time the copies do not explain', () => {
    expect(withoutCopyDrift(at('2026-01-04T16:30:00Z'), [copy], [twin], skipped)).toBe(at('2026-01-04T16:30:00Z'));
    expect(withoutCopyDrift(at('2026-01-04T12:00:00Z'), [copy], [twin], skipped)).toBe(at('2026-01-04T12:00:00Z'));
    expect(withoutCopyDrift(at('2026-01-04T15:59:03Z'), [copy], [twin], none)).toBe(at('2026-01-04T15:59:03Z'));
    expect(withoutCopyDrift(Number.NaN, [copy], [twin], skipped)).toBeNaN();
  });

  it('reads a copy the carrier took over at the carrier scan instant', () => {
    const stored = [row('gofo:label', '2026-01-02T12:30:00+00:00', 'Shipping Label Created'), row('unknown:out', '2026-01-04T15:59:03+00:00', 'Out for Delivery')];
    const takeover = { reused: new Map([['gofo:out', 'unknown:out']]), skipped: new Map<string, string>() };
    const events = [scan('gofo:out', '2026-01-04T12:59:03Z', 'Out for Delivery'), scan('gofo:label', '2026-01-02T12:30:00Z', 'Shipping Label Created')];
    expect(withoutCopyDrift(at('2026-01-04T15:59:03Z'), events, stored, takeover, { withStored: true })).toBe(at('2026-01-04T12:59:03Z'));
    // A stored scan newer than the corrected one keeps the watermark there.
    const newer = [...stored, row('unknown:sorted', '2026-01-04T14:10:00+00:00', 'Sorted')];
    expect(withoutCopyDrift(at('2026-01-04T15:59:03Z'), events, newer, takeover, { withStored: true })).toBe(at('2026-01-04T14:10:00Z'));
    // A stage change stamped when the sync saw it is not a scan time.
    const observed = { ...scan('gofo:observed', '2026-01-05T11:00:00.123Z', 'Out for Delivery'), raw_data: { observed_without_provider_timestamp: true } };
    expect(withoutCopyDrift(at('2026-01-04T15:59:03Z'), [...events, observed], stored, takeover, { withStored: true })).toBe(at('2026-01-04T12:59:03Z'));
    // Nor is the time a stored row was first seen, its provider giving it no instant (UPU's wall clocks).
    const seen = [...stored, { ...row('unknown:upu', '2026-01-05T11:00:00.123+00:00', 'Departure from outward office of exchange'),
      raw_data: { observed_without_provider_timestamp: true } }];
    expect(withoutCopyDrift(at('2026-01-04T15:59:03Z'), events, seen, takeover, { withStored: true })).toBe(at('2026-01-04T12:59:03Z'));
    // Nor is the time the parcel was added, after its newest scan.
    const added = [...stored, row('app:pending', '2026-01-04T14:40:00.123+00:00', 'Tracking added')];
    expect(withoutCopyDrift(at('2026-01-04T15:59:03Z'), events, added, takeover, { withStored: true })).toBe(at('2026-01-04T12:59:03Z'));
  });

  it('brings back a time copies are known to have set, once their rows moved', () => {
    // The provider's copy already sits at the carrier's instant: the carrier's scan meets it, and no copy claims the excess.
    const stored = [row('gofo:label', '2026-01-02T12:30:00+00:00', 'Shipping Label Created'), row('gofo:out', '2026-01-04T12:59:03+00:00', 'Out for Delivery')];
    const events = [scan('gofo:out', '2026-01-04T12:59:03Z', 'Out for Delivery')];
    const late = at('2026-01-04T15:59:03Z');
    expect(withoutCopyDrift(late, events, stored, none, { withStored: true })).toBe(late);
    expect(withoutCopyDrift(late, events, stored, none, { withStored: true, explained: [late] })).toBe(at('2026-01-04T12:59:03Z'));
    // A newer stored scan still holds it, and a time past what is explained stands.
    const newer = [...stored, row('unknown:sorted', '2026-01-04T14:10:00+00:00', 'Sorted')];
    expect(withoutCopyDrift(late, events, newer, none, { withStored: true, explained: [late] })).toBe(at('2026-01-04T14:10:00Z'));
    expect(withoutCopyDrift(late, events, stored, none, { withStored: true, explained: [at('2026-01-04T15:00:00Z')] })).toBe(late);
  });
});

describe('a provider\'s copies a carrier\'s answer cannot tell from its own scans', () => {
  const at = (iso: string) => Date.parse(iso);
  const checked = at('2026-01-05T12:00:00Z');
  // Swiss Post's scan at a London office of exchange, and ParcelsApp's copy of it, its wall clock read as UTC in winter time.
  const office = { ...row('swiss-post:export', '2026-01-04T10:05:00+00:00', 'Departure from the office of exchange', 'in_transit'),
    location: 'LON E ET BEA' };
  const copy = { ...row('unknown:export', '2026-01-04T11:05:00+00:00', 'Dispatched from the country of origin', 'in_transit'),
    location: 'LONDON BPI', universal_provider: 'ParcelsApp', created_at: '2026-01-04T12:00:00+00:00' };
  const misdated = (scans: JsonObject[], stored: JsonObject[], checkedAt = checked) => Object.fromEntries(misdatedCopies(scans, stored, checkedAt));

  it('counts a copy whole hours late, in any wording, at the carrier\'s scan', () => {
    expect(misdated([], [office, copy])).toEqual({ 'unknown:export': at('2026-01-04T10:05:00Z') });
    expect(misdated([scan('unknown:export', copy.occurred_at, copy.description, 'in_transit')], [office])).toEqual({ 'unknown:export': at('2026-01-04T10:05:00Z') });
    // A copy later in the carrier's minute (17TRACK's seconds), or a copy with no place.
    const minute = row('usps:arrived', '2026-01-04T09:12:00+00:00', 'Arrived at USPS Regional Facility', 'in_transit');
    expect(misdated([scan('unknown:arrived', '2026-01-04T09:12:41Z', 'Arrived at USPS Facility')], [minute]))
      .toEqual({ 'unknown:arrived': at('2026-01-04T09:12:00Z') });
    expect(misdated([], [office, { ...copy, location: null }])).toEqual({ 'unknown:export': at('2026-01-04T10:05:00Z') });
  });

  it('leaves a copy from another place, stage or gap, one earlier than the scan, a carrier\'s row and an unnamed one', () => {
    for (const other of [
      { ...copy, location: 'LYON' },
      { ...copy, stage: 'delivered' },
      { ...copy, occurred_at: '2026-01-04T11:20:00+00:00' },
      { ...copy, occurred_at: '2026-01-04T09:05:00+00:00' },
      { ...copy, universal_provider: undefined },
      { ...copy, provider_event_id: 'royal-mail:export' },
    ]) expect(misdated([], [office, other]), JSON.stringify(other)).toEqual({});
    expect(misdated([], [copy])).toEqual({});
  });

  it('reads no zone into a copy a carrier\'s scan in its own minute dates', () => {
    // UPS's notice and ParcelsApp's unclassified copy of it, to the minute, 12 h after UPS's arrival at the same place.
    const arrived = { ...row('ups:arrived', '2026-01-04T05:01:00+00:00', 'Arrived at Facility', 'in_transit'), location: 'Hubtown' };
    const notice = { ...row('ups:notice', '2026-01-04T17:01:45+00:00', 'The receiver name is incorrect', 'in_transit'), location: 'Hubtown' };
    const relayed = { ...scan('unknown:notice', '2026-01-04T17:01:00Z', 'The receiver name is incorrect'), location: 'Hubtown' };
    expect(misdated([relayed], [arrived, notice])).toEqual({});
    expect(misdated([relayed], [arrived])).toEqual({ 'unknown:notice': at('2026-01-04T05:01:00Z') });
  });

  it('reads no zone into a copy of a scan the carrier dated by its day alone, nor into such a copy', () => {
    // USPS's day alone starts at midnight in New York; a provider copies the scan an hour into that day.
    const printed = { ...row('usps:printed', '2026-01-04T05:00:00+00:00', 'Shipping Label Printed at Post Office', 'registered'),
      raw_data: { local_time: '2026-01-04' } };
    const copied = scan('unknown:printed', '2026-01-04T06:00:00Z', printed.description, 'registered');
    expect(misdated([copied], [printed])).toEqual({});
    const label = row('usps:label', '2026-01-04T02:00:00+00:00', 'Shipping Label Created', 'registered');
    expect(misdated([{ ...copied, raw_data: { ...copied.raw_data, local_time: '2026-01-04' } }], [label])).toEqual({});
    expect(misdated([copied], [label])).toEqual({ 'unknown:printed': at('2026-01-04T02:00:00Z') });
  });

  it('counts a copy dated past the check that first returned it at no instant', () => {
    const ahead = { ...copy, occurred_at: '2026-01-04T12:30:00+00:00' };
    expect(misdated([], [ahead])).toEqual({ 'unknown:export': Number.NaN });
    // A new copy is first returned by this check; a few minutes of clock drift pass.
    expect(misdated([scan('unknown:ahead', '2026-01-05T12:30:00Z', 'Arrived')], [])).toEqual({ 'unknown:ahead': Number.NaN });
    expect(misdated([scan('unknown:ahead', '2026-01-05T12:04:00Z', 'Arrived')], [])).toEqual({});
    expect(misdated([], [{ ...ahead, created_at: undefined }])).toEqual({});
  });

  it('lets the carrier\'s own scan stand for a watermark the copy set', () => {
    const stored = [office, copy];
    const counts = misdatedCopies([], stored, checked);
    const none = { reused: new Map<string, string>(), skipped: new Map<string, string>() };
    expect(withoutCopyDrift(at('2026-01-04T11:05:00Z'), [], stored, none, { withStored: true, misdated: counts })).toBe(at('2026-01-04T10:05:00Z'));
    expect(withoutCopyDrift(at('2026-01-04T11:05:00Z'), [], stored, none, { withStored: true })).toBe(at('2026-01-04T11:05:00Z'));
    // A copy dated past the check (capped there by the router) leaves the newest scan before it.
    const ahead = [office, { ...copy, occurred_at: '2026-01-04T14:00:00+00:00' }];
    expect(withoutCopyDrift(at('2026-01-04T12:00:00Z'), [], ahead, none, { withStored: true, misdated: misdatedCopies([], ahead, checked) }))
      .toBe(at('2026-01-04T10:05:00Z'));
  });
});
