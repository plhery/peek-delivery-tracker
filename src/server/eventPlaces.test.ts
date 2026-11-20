import { afterEach, describe, expect, it, vi } from 'vitest';
import * as observability from './observability';
import * as places from 'universal-parcel-scraper/places';
vi.mock('universal-parcel-scraper/places', { spy: true });
import { currentEvent, withRelays } from '../lib/stages';
import type { Stage, TrackingEvent } from '../types';
import { withEventPlaces, withoutEventSources } from './eventPlaces';

it('keeps addition-only query context out of client package data', () => {
  const row = { carrier_data: { add_recognition_pending: true, lookup_country_hint: 'CH' } };
  expect(withEventPlaces(row)).toEqual({ carrier_data: { lookup_country_hint: 'CH' } });
  expect(row.carrier_data.add_recognition_pending).toBe(true);
});

it('says when the named delivering carrier lists the parcel without a scan of its own, until it is handed over', () => {
  const probe = { at: '2026-11-05T10:00:00Z', origin_update: null, carrier: 'dhl', number: 'TESTPARTNER001', listed: true };
  const served = (data: Record<string, unknown>) => (withEventPlaces({ carrier_data: data }) as { carrier_data: Record<string, unknown> }).carrier_data;
  expect(served({ delivery_carrier: 'dhl', delivery_probe: probe })).toMatchObject({ delivery_announced: true });
  // Found nothing, another partner, or the parcel is followed there now: the line names who delivers.
  for (const data of [
    { delivery_carrier: 'dhl', delivery_probe: { ...probe, listed: undefined } },
    { delivery_carrier: 'dhl', delivery_probe: { ...probe, carrier: 'swiss-post' } },
    { delivery_probe: probe },
    { delivery_carrier: 'dhl', delivery_probe: probe, original_carrier: 'jrt', active_tracking_carrier: 'dhl' },
  ]) expect(served(data)).not.toHaveProperty('delivery_announced');
  // A parcel the earlier carrier delivered or returned is not waiting for the partner.
  for (const current_stage of ['delivered', 'returned']) {
    expect((withEventPlaces({ current_stage, carrier_data: { delivery_carrier: 'dhl', delivery_probe: probe } }) as
      { carrier_data: Record<string, unknown> }).carrier_data).not.toHaveProperty('delivery_announced');
  }
  // The account's rows have no stage: their scans tell it, as the apps read them.
  const scans = (stage: string) => [
    { id: 'e1', stage: 'in_transit', description: 'In transit', occurred_at: '2026-11-04T09:00:00Z', provider_event_id: 'dhl:1' },
    { id: 'e2', stage, description: 'Last scan', occurred_at: '2026-11-06T09:00:00Z', provider_event_id: 'dhl:2' },
  ];
  const account = (carrier: string, stage: string) => (withEventPlaces({ carrier, tracking_number: 'TESTPARCEL0001',
    carrier_data: { delivery_carrier: 'dhl', delivery_probe: probe }, tracking_events: scans(stage) }) as
    { carrier_data: Record<string, unknown> }).carrier_data;
  for (const stage of ['delivered', 'returned']) expect(account('cainiao', stage)).not.toHaveProperty('delivery_announced');
  expect(account('cainiao', 'out_for_delivery')).toMatchObject({ delivery_announced: true });
  // A carrier that writes the delivered step on a schedule delivers nothing itself: the partner still only has it announced.
  expect(account('gzyl', 'delivered')).toMatchObject({ delivery_announced: true });
  expect((withEventPlaces({ carrier: 'gzyl', current_stage: 'delivered', carrier_data: { delivery_carrier: 'dhl', delivery_probe: probe } }) as
    { carrier_data: Record<string, unknown> }).carrier_data).toMatchObject({ delivery_announced: true });
  // The check itself, with the partner's number, stays on the server.
  expect(served({ delivery_carrier: 'dhl', delivery_probe: probe })).not.toHaveProperty('delivery_probe');
  expect(served({ delivery_probe: { ...probe, listed: undefined } })).toEqual({});
  expect(withoutEventSources({ carrier_data: { delivery_probe: probe } })).toEqual({ carrier_data: {} });
});

it('names the partner the hand-off check found holding the parcel when the followed carrier names none', () => {
  const probe = { at: '2026-11-05T10:00:00Z', origin_update: null, carrier: 'dhl', number: 'TESTPARTNER001', held: true };
  const served = (data: Record<string, unknown>, current_stage?: string) =>
    (withEventPlaces({ current_stage, carrier_data: data }) as { carrier_data: Record<string, unknown> }).carrier_data;
  // Scanned there: "Delivery with DHL", with the number it was asked with, delivered or not.
  for (const stage of [undefined, 'delivered']) {
    expect(served({ active_tracking_carrier: 'india-post', delivery_probe: probe }, stage))
      .toEqual({ active_tracking_carrier: 'india-post', delivery_carrier: 'dhl', delivery_tracking_number: 'TESTPARTNER001' });
  }
  // Only told of it: "Announced to DHL, not received yet".
  expect(served({ delivery_probe: { ...probe, listed: true } }))
    .toEqual({ delivery_carrier: 'dhl', delivery_tracking_number: 'TESTPARTNER001', delivery_announced: true });
  // The carrier's own partner and number stay; a partner followed now, or a check that found nothing, names none.
  expect(served({ delivery_carrier: 'dpd', delivery_tracking_number: 'TESTPARTNER002', delivery_probe: probe }))
    .toEqual({ delivery_carrier: 'dpd', delivery_tracking_number: 'TESTPARTNER002' });
  expect(served({ original_carrier: 'india-post', active_tracking_carrier: 'dhl', delivery_probe: probe }))
    .toEqual({ original_carrier: 'india-post', active_tracking_carrier: 'dhl' });
  expect(served({ delivery_probe: { ...probe, held: undefined } })).toEqual({});
});

const event = (id: string, location: string | null, occurredAt: string) => ({
  id, package_id: 'parcel', stage: 'in_transit', description: 'Scan', location, occurred_at: occurredAt,
});

afterEach(() => vi.restoreAllMocks());

describe('withEventPlaces', () => {
  it('locates each scan, in its own order, with the carrier and neighbouring scans as hints', () => {
    const row = {
      id: 'parcel',
      carrier: 'swiss-post',
      carrier_data: { destination_country: 'CH' },
      // Newest first, as the database may return them.
      tracking_events: [
        event('3', 'Dintikon', '2026-09-28T07:00:00+00:00'),
        event('2', null, '2026-09-27T12:00:00+00:00'),
        event('1', 'KOELN, DE', '2026-09-26T09:00:00+00:00'),
      ],
    };
    const located = withEventPlaces(row) as { tracking_events: { id: string; place: places.EventPlace | null }[] };
    expect(located.tracking_events.map((scan) => scan.id)).toEqual(['3', '2', '1']);
    expect(located.tracking_events[0].place).toMatchObject({ country: 'CH', name: 'Dintikon', precision: 'city' });
    expect(located.tracking_events[1].place).toBeNull();
    expect(located.tracking_events[2].place).toMatchObject({ country: 'DE', name: 'Köln' });
  });

  it('knows a carrier\'s country from the catalog, whatever clock its scans keep', () => {
    const row = {
      id: 'parcel',
      carrier: 'royal-mail',
      carrier_data: { active_tracking_carrier: 'la-poste' },
      tracking_events: [
        event('1', 'Norwich MC', '2026-09-26T09:00:00+00:00'),
        event('2', 'LANGLEY', '2026-09-27T12:00:00+00:00'),
        event('3', 'ROISSY', '2026-09-28T07:00:00+00:00'),
      ],
    };
    const located = withEventPlaces(row) as { tracking_events: { place: places.EventPlace | null }[] };
    // A mail centre in England, not Monaco; Langley by Heathrow, not the one in Canada.
    expect(located.tracking_events.map((scan) => scan.place && `${scan.place.name}, ${scan.place.country}`))
      .toEqual(['Norwich, GB', 'Langley, GB', 'Roissy-en-France, FR']);
  });

  it('moves a scan to the carrier\'s own point near its town, and never returns the point', () => {
    const row = {
      id: 'parcel',
      carrier: 'dpd-fr',
      tracking_events: [
        // A depot 6 km from Strasbourg's centre.
        { ...event('1', 'Agence DPD de Strasbourg (67)', '2026-09-18T05:40:00+00:00'), point: { latitude: 48.6305, longitude: 7.7682 } },
        // A point that disagrees with the town is ignored.
        { ...event('2', 'Agence DPD de Strasbourg (67)', '2026-09-18T05:42:00+00:00'), point: { latitude: 43.1512, longitude: 6.0712 } },
      ],
    };
    const [depot, elsewhere] = (withEventPlaces(row) as { tracking_events: Record<string, unknown>[] }).tracking_events;
    expect(depot.place).toMatchObject({ name: 'Strasbourg', latitude: 48.6305, longitude: 7.7682 });
    expect(elsewhere.place).toMatchObject({ name: 'Strasbourg', latitude: expect.closeTo(48.58, 1) });
    expect(depot).not.toHaveProperty('point');
    expect(elsewhere).not.toHaveProperty('point');
  });

  it('keeps a customs clearance filed at the destination off the map, and its text in the timeline', () => {
    const hongKong = 'HONG KONG - HONG KONG SAR, CHINA';
    const row = {
      id: 'parcel',
      carrier: 'dhl-express',
      tracking_events: [
        event('1', hongKong, '2026-01-05T11:47:00+00:00'),
        // Cleared in Basel while the parcel is still sorted in Hong Kong.
        { ...event('2', 'BASEL - SWITZERLAND', '2026-01-05T15:53:00+00:00'), stage: 'customs' },
        event('3', hongKong, '2026-01-05T16:18:00+00:00'),
      ],
    };
    const located = (withEventPlaces(row) as { tracking_events: { location: string; place: places.EventPlace | null }[] }).tracking_events;
    expect(located.map((scan) => scan.place?.country ?? null)).toEqual(['HK', null, 'HK']);
    expect(located[1].location).toBe('BASEL - SWITZERLAND');
  });

  it('serves what a customs scan asks the recipient to do, as stored or, for an older row, as its words say', () => {
    const row = {
      id: 'parcel',
      carrier: 'ups',
      tracking_events: [
        // Flagged by the carrier's code, in words the list of wordings does not hold.
        { ...event('1', null, '2026-01-05T11:47:00+00:00'), stage: 'customs', description: 'Held: duties are due', recipient_action: 'duties' },
        // Stored before the flag, or read through a link's view without it.
        { ...event('2', null, '2026-01-06T09:00:00+00:00'), stage: 'customs', description: 'Need to inform recipient\'s CPF/CNPJ.', recipient_action: null },
        { ...event('3', null, '2026-01-07T09:00:00+00:00'), recipient_action: 'unknown' },
      ],
    };
    const served = (withEventPlaces(row) as { tracking_events: Record<string, unknown>[] }).tracking_events;
    expect(served.map((scan) => scan.recipient_action)).toEqual(['duties', 'tax_id', undefined]);
    expect('recipient_action' in served[2]!).toBe(false);
  });

  it("serves a scan's place among those its source dated alike, and nothing else in its stead", () => {
    const at = '2026-01-05T11:47:00+00:00';
    const row = {
      id: 'parcel',
      carrier: 'ups',
      tracking_events: [
        { ...event('1', null, at), description: 'Arrived', tie_order: 0 },
        { ...event('2', null, at), description: 'Departed', tie_order: 1 },
        { ...event('3', null, '2026-01-06T09:00:00+00:00'), tie_order: null },
        { ...event('4', null, '2026-01-07T09:00:00+00:00'), tie_order: 1.5 },
      ],
    };
    const served = (withEventPlaces(row) as { tracking_events: Record<string, unknown>[] }).tracking_events;
    expect(served.map((scan) => [scan.id, scan.tie_order])).toEqual([['1', 0], ['2', 1], ['3', undefined], ['4', undefined]]);
    expect(served.filter((scan) => 'tie_order' in scan)).toHaveLength(2);
  });

  it('dates the parcel\'s news by when its latest scan was first stored, and serves no row with that time (#72)', () => {
    const stored = (id: string, occurredAt: string, storedAt: string, source = `swiss-post:${id}`) => ({
      ...event(id, null, occurredAt), provider_event_id: source, stored_at: storedAt,
    });
    const news = (events: object[]) => (withEventPlaces({ id: 'parcel', carrier: 'swiss-post', tracking_events: events }) as { news_at?: string }).news_at;
    const first = stored('1', '2026-11-10T08:00:00+00:00', '2026-11-10T09:00:00+00:00');
    const latest = stored('2', '2026-11-12T08:00:00+00:00', '2026-11-12T08:30:00+00:00');
    expect(news([first, latest])).toBe('2026-11-12T08:30:00+00:00');
    // History filled in later is no news: the latest scan is the one the carrier dates last.
    expect(news([first, latest, stored('3', '2026-11-11T08:00:00+00:00', '2026-11-13T10:00:00+00:00')])).toBe('2026-11-12T08:30:00+00:00');
    // Nor is a second row telling the latest scan's moment: the first one stored counts.
    expect(news([first, latest, stored('4', '2026-11-12T08:00:00+00:00', '2026-11-13T10:00:00+00:00')])).toBe('2026-11-12T08:30:00+00:00');
    // A later scan is.
    expect(news([first, latest, stored('5', '2026-11-13T07:00:00+00:00', '2026-11-13T07:10:00+00:00')])).toBe('2026-11-13T07:10:00+00:00');
    // Peek's own rows are no scans, and a parcel without one has no news.
    const own = stored('6', '2026-11-14T08:00:00+00:00', '2026-11-14T08:00:00+00:00', 'app:created');
    expect(news([first, own])).toBe('2026-11-10T09:00:00+00:00');
    expect(withEventPlaces({ id: 'parcel', carrier: 'swiss-post', tracking_events: [own] })).not.toHaveProperty('news_at');
    const served = withEventPlaces({ id: 'parcel', carrier: 'swiss-post', seen_at: '2026-11-12T09:00:00+00:00', tracking_events: [first, latest] }) as
      { seen_at: string; tracking_events: object[] };
    expect(served.seen_at).toBe('2026-11-12T09:00:00+00:00');
    for (const row of served.tracking_events) expect(row).not.toHaveProperty('stored_at');
    expect(JSON.stringify(withoutEventSources({ id: 'parcel', tracking_events: [first] }))).not.toContain('stored_at');
  });

  it('dates the news of a scan a provider told before its carrier did by the provider\'s copy, which the timeline leaves out (#72)', () => {
    const scan = (id: string, source: string, occurredAt: string, storedAt: string, extra: Record<string, unknown> = {}) => ({
      ...event(id, null, occurredAt), provider_event_id: `${source}:${id}`, stored_at: storedAt, ...extra,
    });
    const posted = scan('posted', 'correos-chile', '2026-09-11T13:43:00+00:00', '2026-09-11T14:00:00+00:00');
    // 17TRACK told the arrival while the carrier could not be asked; the carrier told it the next morning.
    const copy = scan('arrived-copy', 'unknown', '2026-09-29T11:05:14+00:00', '2026-09-29T12:00:00+00:00', { universal_provider: '17TRACK' });
    const arrived = scan('arrived', 'correos-chile', '2026-09-29T11:05:00+00:00', '2026-09-30T08:00:00+00:00');
    const row = { id: 'parcel', carrier: 'correos-chile', carrier_data: { routing: { confirmed_carrier: 'correos-chile' } } };
    const served = withEventPlaces({ ...row, tracking_events: [posted, copy, arrived] }) as { news_at?: string; tracking_events: { id: string }[] };
    expect(served.tracking_events.map(({ id }) => id)).toEqual(['posted', 'arrived']);
    expect(served.news_at).toBe('2026-09-29T12:00:00+00:00');
    // Before the carrier told it, the provider's row was the news.
    expect((withEventPlaces({ ...row, tracking_events: [posted, copy] }) as { news_at?: string }).news_at).toBe('2026-09-29T12:00:00+00:00');
  });

  it('counts a scan of the latest moment in another stage or other words as news when it arrives (#72)', () => {
    const scan = (id: string, stage: string, description: string, occurredAt: string, storedAt: string, extra: Record<string, unknown> = {}) => ({
      ...event(id, 'Härkingen', occurredAt), stage, description, provider_event_id: `swiss-post:${id}`, stored_at: storedAt, ...extra,
    });
    const news = (events: object[]) => (withEventPlaces({ id: 'parcel', carrier: 'swiss-post', tracking_events: events }) as
      { news_at?: string; tracking_events: { id: string }[] });
    // A carrier that dates its scans by the day alone tells the delivery at the instant of the round before it.
    const out = scan('out', 'out_for_delivery', 'Out for delivery', '2026-11-14T23:00:00+00:00', '2026-11-15T07:00:00+00:00', { time: '2026-11-15' });
    const delivered = scan('delivered', 'delivered', 'Delivered', '2026-11-14T23:00:00+00:00', '2026-11-15T15:00:00+00:00', { time: '2026-11-15' });
    const served = news([out, delivered]);
    expect(served.tracking_events.map(({ id }) => id).sort()).toEqual(['delivered', 'out']);
    expect(served.news_at).toBe('2026-11-15T15:00:00+00:00');
    // Two steps on one minute, the second told at a later check.
    const arrived = scan('arrived', 'in_transit', 'Arrived at the sorting centre', '2026-11-15T06:40:00+00:00', '2026-11-15T07:00:00+00:00');
    const leaving = scan('leaving', 'in_transit', 'Left the sorting centre', '2026-11-15T06:40:00+00:00', '2026-11-15T09:30:00+00:00');
    expect(news([arrived, leaving]).news_at).toBe('2026-11-15T09:30:00+00:00');
    // Another source's copy of that moment in the same stage is no news.
    const copy = { ...scan('copy', 'in_transit', 'Ankunft im Sortierzentrum', '2026-11-15T06:40:00+00:00', '2026-11-15T09:30:00+00:00'),
      provider_event_id: 'post-ch:copy' };
    expect(news([arrived, copy]).news_at).toBe('2026-11-15T07:00:00+00:00');
  });

  it('dates the news of a step by its first row stored, whatever the timeline shows under it (#72)', () => {
    const scan = (id: string, source: string, stage: string, description: string, occurredAt: string, storedAt: string) => ({
      ...event(id, 'Lyon', occurredAt), stage, description, provider_event_id: `${source}:${id}`, stored_at: storedAt,
    });
    const transit = scan('transit', 'dpd', 'in_transit', 'In transit', '2026-11-15T06:00:00+00:00', '2026-11-15T06:10:00+00:00');
    const delivered = scan('delivered', 'dpd', 'delivered', 'Delivered', '2026-11-15T14:00:37+00:00', '2026-11-15T14:05:00+00:00');
    type Served = { news_at?: string; tracking_events: { id: string; relay_of?: string }[] };
    // Handed over from Chronopost to DPD: Chronopost, asked again, tells DPD's delivery 23 s later at a later check.
    const handed = (events: object[]) => withEventPlaces({ id: 'parcel', carrier: 'dpd', current_stage: 'delivered',
      carrier_data: { original_carrier: 'chronopost', active_tracking_carrier: 'dpd' }, tracking_events: events }) as Served;
    for (const at of ['2026-11-15T14:01:00+00:00', '2026-11-15T14:00:00+00:00']) {
      const copy = scan('livre', 'chronopost', 'delivered', 'Livré', at, '2026-11-15T18:00:00+00:00');
      const served = handed([transit, delivered, copy]);
      expect(served.tracking_events.find(({ id }) => id === 'livre')?.relay_of).toBe('delivered');
      expect(served.news_at).toBe('2026-11-15T14:05:00+00:00');
    }
    // A carrier's repeat of its own scan two minutes later, told at a later check, is shown as one step.
    const failed = scan('failed', 'dpd', 'failed_attempt', 'Delivery attempt failed', '2026-11-15T10:00:00+00:00', '2026-11-15T10:05:00+00:00');
    const again = scan('again', 'dpd', 'failed_attempt', 'Delivery attempt failed', '2026-11-15T10:02:00+00:00', '2026-11-15T16:00:00+00:00');
    const served = withEventPlaces({ id: 'parcel', carrier: 'dpd', tracking_events: [transit, failed, again] }) as Served;
    expect(served.tracking_events.some(({ relay_of }) => relay_of !== undefined)).toBe(true);
    expect(served.news_at).toBe('2026-11-15T10:05:00+00:00');
  });

  it('serves a delivery stored over an hour before its own time in no stage, unless its carrier re-dated it', () => {
    const stored = (id: string, stage: string, occurredAt: string, extra: object = {}) => ({ ...event(id, null, occurredAt), stage,
      description: stage === 'in_transit' ? 'Scan' : 'Delivered', provider_event_id: `quick-fort:${id}`, stored_at: '2026-11-19T12:38:00+00:00', ...extra });
    const serve = (events: object[]) => (withEventPlaces({ id: 'parcel', carrier: 'quick-fort', current_stage: 'in_transit', tracking_events: events }) as
      { tracking_events: { id: string; stage: string; description: string }[] }).tracking_events;
    const stages = (events: object[]) => Object.fromEntries(serve(events).map(({ id, stage }) => [id, stage]));
    const moving = stored('1', 'in_transit', '2026-11-19T08:00:00+00:00');
    // A carrier's script of the journey, its "Delivered" two days ahead: in its words, in no stage, once its time
    // has passed too, as the parcel's stage never took it.
    const scripted = stored('2', 'delivered', '2026-11-21T14:00:00+00:00');
    expect(stages([moving, scripted])).toEqual({ 1: 'in_transit', 2: 'pending' });
    expect(serve([moving, scripted]).find(({ id }) => id === '2')?.description).toBe('Delivered');
    expect(stages([moving, stored('3', 'returned', '2026-11-21T14:00:00+00:00')])).toEqual({ 1: 'in_transit', 3: 'pending' });
    // A delivery 22 minutes ahead is a clock running fast, and one its carrier re-dated keeps its stage.
    expect(stages([moving, stored('4', 'delivered', '2026-11-19T13:00:00+00:00')])).toEqual({ 1: 'in_transit', 4: 'delivered' });
    const retimed = stored('5', 'delivered', '2026-11-21T14:00:00+00:00', { retimed_from: '2026-11-19 10:00' });
    expect(stages([moving, retimed])).toEqual({ 1: 'in_transit', 5: 'delivered' });
    expect(JSON.stringify(serve([moving, retimed]))).not.toContain('retimed_from');
    expect(JSON.stringify(withoutEventSources({ id: 'parcel', tracking_events: [retimed] }))).not.toContain('retimed_from');
  });

  it('leaves packages without scans untouched', () => {
    const row = { id: 'parcel', carrier: 'swiss-post', tracking_events: [] };
    expect(withEventPlaces(row)).toBe(row);
    expect(withEventPlaces({ id: 'parcel' })).toEqual({ id: 'parcel' });
  });

  it('opens the timeline with Peek\'s own row only until the carrier\'s scans reach back to it', () => {
    const added = { ...event('added', null, '2026-09-27T10:00:00+00:00'), stage: 'pending', description: 'Tracking added', provider_event_id: 'app:pending' };
    const scan = (id: string, occurredAt: string) => ({ ...event(id, null, occurredAt), provider_event_id: `swiss-post:${id}` });
    const served = (events: Record<string, unknown>[]) => (withEventPlaces({ id: 'parcel', carrier: 'swiss-post', tracking_events: events }) as {
      tracking_events: Record<string, unknown>[];
    }).tracking_events;

    // Only the row, or only later scans: it starts the journey.
    expect(served([added]).map((row) => row.id)).toEqual(['added']);
    expect(served([scan('later', '2026-09-27T12:00:00+00:00'), added]).map((row) => row.id)).toEqual(['later', 'added']);
    // Added mid-journey or after the delivery: the carrier's scans tell the story alone.
    const delivered = { ...scan('delivered', '2026-09-26T15:00:00+00:00'), stage: 'delivered' };
    expect(served([added, delivered, scan('earlier', '2026-09-25T08:00:00+00:00')]).map((row) => row.id)).toEqual(['delivered', 'earlier']);
    expect(served([added, scan('same', '2026-09-27T10:00:00+00:00')]).map((row) => row.id)).toEqual(['same']);
    // A carrier change waiting for its first answer is Peek's row too.
    expect(served([{ ...added, description: 'Carrier changed; waiting for tracking' }, scan('earlier', '2026-09-25T08:00:00+00:00')])
      .map((row) => row.id)).toEqual(['earlier']);
    // Without a source, as from a link read before its database said it, every row stays.
    const unsourced = { ...added, provider_event_id: undefined };
    expect(served([unsourced, scan('earlier', '2026-09-25T08:00:00+00:00')]).map((row) => row.id)).toEqual(['added', 'earlier']);
    // The source is never served.
    expect(served([added, scan('later', '2026-09-27T12:00:00+00:00')]).every((row) => !('provider_event_id' in row))).toBe(true);
  });

  it('serves a placeholder number\'s parcel for its owner to correct, without what was stored from it', () => {
    const added = { ...event('added', null, '2026-09-27T10:00:00+00:00'), stage: 'pending', description: 'Tracking added', provider_event_id: 'app:pending' };
    const merged = (id: string, occurredAt: string) => ({ ...event(id, 'Lyon', occurredAt), stage: 'delivered', provider_event_id: `unknown:${id}` });
    const row = {
      id: 'parcel', carrier: 'unknown', tracking_number: '1234567890', sync_status: 'ok', sync_error: null,
      last_status_text: 'Delivered', expected_delivery: '2026-09-28', current_stage: 'delivered',
      carrier_data: { destination_country: 'FR', pickup_point: 'Relay', receiver_name: 'Someone', tracking_provider: 'ship24' },
      tracking_events: [merged('other', '2026-09-28T09:00:00+00:00'), merged('earlier', '2026-09-26T09:00:00+00:00'), added],
    };
    const served = withEventPlaces(row) as Record<string, unknown> & { tracking_events: Record<string, unknown>[] };
    // Other people's scans go, so Peek's own row opens the timeline again.
    expect(served.tracking_events.map((scan) => scan.id)).toEqual(['added']);
    expect(served).toMatchObject({
      sync_status: 'error', sync_error: 'carrier:placeholder_number', last_status_text: null, expected_delivery: null, carrier_data: {},
    });
    // The stored row and its stage are left as they are.
    expect(row.tracking_events).toHaveLength(3);
    expect(served.current_stage).toBe('delivered');
    // A parcel's own number is served as stored.
    expect((withEventPlaces({ ...row, tracking_number: 'TEST1234' }) as typeof served).tracking_events.map((scan) => scan.id))
      .toEqual(['other', 'earlier']);
  });

  it('serves an earlier carrier\'s relay copy with the scan it repeats, and keeps both rows', () => {
    const scan = (id: string, source: string, stage: string, occurredAt: string, description: string) => ({
      ...event(id, null, occurredAt), stage, description, provider_event_id: `${source}:${id}`,
    });
    const row = {
      id: 'parcel', carrier: 'dhl', carrier_data: { original_carrier: 'dhl', active_tracking_carrier: 'swiss-post' }, tracking_events: [
        scan('arrived', 'swiss-post', 'in_transit', '2026-09-09T14:16:51+00:00', 'Arrival in destination country'),
        scan('copy', 'dhl', 'in_transit', '2026-09-09T14:16:00+00:00', 'The shipment has arrived in the destination country'),
        // Two scans of their own at the same stage would pair too: both stay, words and all.
        scan('sorted', 'swiss-post', 'in_transit', '2026-09-09T15:40:38+00:00', 'Sorted'),
        scan('loaded', 'dhl', 'in_transit', '2026-09-09T15:40:00+00:00', 'Loaded onto a vehicle'),
        scan('customs', 'dhl', 'customs', '2026-09-09T14:18:00+00:00', 'Customs clearance'),
      ],
    };
    const served = (withEventPlaces(row) as { tracking_events: Record<string, unknown>[] }).tracking_events;
    expect(served.map(({ id, relay_of, description }) => [id, relay_of, description])).toEqual([
      ['arrived', undefined, 'Arrival in destination country'],
      ['copy', 'arrived', 'The shipment has arrived in the destination country'],
      ['sorted', undefined, 'Sorted'],
      ['loaded', 'sorted', 'Loaded onto a vehicle'],
      ['customs', undefined, 'Customs clearance'],
    ]);
    expect(served.every((scan) => !('provider_event_id' in scan))).toBe(true);
    // A parcel of one carrier has none.
    expect((withEventPlaces({ ...row, carrier_data: {} }) as { tracking_events: Record<string, unknown>[] }).tracking_events
      .some((scan) => 'relay_of' in scan)).toBe(false);
  });

  it('serves a relay pair under the row that says where the scan was', () => {
    const scan = (id: string, source: string, occurredAt: string, location: string | null, description = 'Scan') => ({
      ...event(id, location, occurredAt), description, provider_event_id: `${source}:${id}`,
    });
    const row = {
      id: 'parcel', carrier: 'yunexpress', carrier_data: { original_carrier: 'yunexpress', active_tracking_carrier: 'ecoscooting' },
      tracking_events: [
        // The earlier carrier alone names the town: the delivery carrier's scan goes under its copy.
        scan('arrived', 'ecoscooting', '2026-10-21T08:00:00+00:00', null, 'Arrived at the delivery station'),
        scan('arrived-copy', 'yunexpress', '2026-10-21T08:00:00+00:00', 'Salamanca', 'Arrived at the delivery station'),
        // Both or neither name a place: the copy goes under the scan.
        scan('sorted', 'ecoscooting', '2026-10-21T10:00:00+00:00', 'Salamanca'),
        scan('sorted-copy', 'yunexpress', '2026-10-21T10:00:00+00:00', 'Salamanca'),
        scan('out', 'ecoscooting', '2026-10-22T07:00:00+00:00', null),
        scan('out-copy', 'yunexpress', '2026-10-22T07:00:00+00:00', null),
        // Nor does a copy that names none go over a scan that does.
        scan('left', 'ecoscooting', '2026-10-22T09:00:00+00:00', 'Salamanca'),
        scan('left-copy', 'yunexpress', '2026-10-22T09:00:00+00:00', null),
        // Unless the copy alone tells a problem: the parcel held as damaged goes over "on its way", a failed delivery
        // over a customs scan or the delivery round.
        { ...scan('moving', 'ecoscooting', '2026-10-23T09:00:12+00:00', 'Salamanca'), stage: 'in_transit' },
        { ...scan('held', 'yunexpress', '2026-10-23T09:00:12+00:00', null), stage: 'exception' },
        { ...scan('cleared', 'ecoscooting', '2026-10-23T11:00:12+00:00', 'Salamanca'), stage: 'customs' },
        { ...scan('missed', 'yunexpress', '2026-10-23T11:00:12+00:00', null), stage: 'failed_attempt' },
        { ...scan('rounds', 'ecoscooting', '2026-10-23T15:00:12+00:00', 'Salamanca'), stage: 'out_for_delivery' },
        { ...scan('absent', 'yunexpress', '2026-10-23T15:00:12+00:00', null), stage: 'failed_attempt' },
        // A copy behind a scan past transit goes under it, place and all: Royal Mail's row in transit beside An Post's
        // out for delivery. One behind a scan in transit still leads: Poczta Polska's posting beside PostNord's first row.
        { ...scan('round', 'ecoscooting', '2026-10-24T07:13:21+00:00', null), stage: 'out_for_delivery' },
        scan('round-copy', 'yunexpress', '2026-10-24T07:13:21+00:00', 'Salamanca'),
        scan('flown', 'ecoscooting', '2026-10-24T15:28:21+00:00', null),
        { ...scan('flown-copy', 'yunexpress', '2026-10-24T15:28:21+00:00', 'Salamanca'), stage: 'accepted' },
      ],
    };
    const served = (withEventPlaces(row) as { tracking_events: Record<string, unknown>[] }).tracking_events;
    expect(served.map(({ id, relay_of }) => [id, relay_of])).toEqual([
      ['arrived', 'arrived-copy'], ['arrived-copy', undefined],
      ['sorted', undefined], ['sorted-copy', 'sorted'],
      ['out', undefined], ['out-copy', 'out'],
      ['left', undefined], ['left-copy', 'left'],
      ['moving', 'held'], ['held', undefined],
      ['cleared', 'missed'], ['missed', undefined],
      ['rounds', 'absent'], ['absent', undefined],
      ['round', undefined], ['round-copy', 'round'],
      ['flown', 'flown-copy'], ['flown-copy', undefined],
    ]);
  });

  it('serves two providers\' rows of one scan, with no carrier\'s scan beside them, as one step', () => {
    const copy = (id: string, provider: string, stage: string, occurredAt: string, location: string | null, description: string) => ({
      ...event(id, location, occurredAt), stage, description, provider_event_id: `unknown:${id}`, universal_provider: provider,
      stored_at: id.endsWith('late') ? '2026-10-28T00:00:00Z' : '2026-10-27T20:00:00Z',
    });
    const row = {
      id: 'parcel', carrier: 'china-post', carrier_data: {}, tracking_events: [
        // One names the country, the other the town in it: the town's row is the step.
        copy('posted', 'ParcelsApp', 'accepted', '2026-10-27T12:14:00Z', 'China', 'Posted'),
        copy('posted-late', 'Ship24', 'accepted', '2026-10-27T12:14:00Z', 'Shenzhen, China', 'Item accepted'),
        // The same place: the row stored first is the step.
        copy('sorted', '17TRACK', 'in_transit', '2026-10-27T15:00:00Z', 'Shenzhen', 'Sorted'),
        copy('sorted-late', 'ParcelsApp', 'in_transit', '2026-10-27T15:00:00Z', 'Shenzhen', 'Sorted'),
        // A copy to the minute of the one row of that minute to the second: ParcelsApp's minute of 17TRACK's second.
        copy('cleared', '17TRACK', 'in_transit', '2026-10-27T16:32:56Z', 'Shenzhen', 'Released'),
        copy('cleared-late', 'ParcelsApp', 'in_transit', '2026-10-27T16:32:00Z', 'Shenzhen', 'Export cleared'),
        // Two towns, two stages, a minute apart, two seconds of one minute, or a provider's two rows of that instant:
        // each its own step.
        copy('left', '17TRACK', 'in_transit', '2026-10-27T18:00:00Z', 'Shenzhen', 'Departed'),
        copy('left-late', 'ParcelsApp', 'in_transit', '2026-10-27T18:00:00Z', 'Guangzhou', 'Departed'),
        copy('export', '17TRACK', 'customs', '2026-10-27T19:00:00Z', 'Shenzhen', 'Export'),
        copy('export-late', 'ParcelsApp', 'in_transit', '2026-10-27T19:00:00Z', 'Shenzhen', 'Export'),
        copy('flown', '17TRACK', 'in_transit', '2026-10-27T20:00:00Z', 'Shenzhen', 'Flown'),
        copy('flown-late', 'ParcelsApp', 'in_transit', '2026-10-27T20:01:00Z', 'Shenzhen', 'Flown'),
        copy('hub', '17TRACK', 'in_transit', '2026-10-27T20:30:07Z', 'Shenzhen', 'At the hub'),
        copy('hub-late', 'ParcelsApp', 'in_transit', '2026-10-27T20:30:00Z', 'Shenzhen', 'At the hub'),
        copy('hub-other', 'Ship24', 'in_transit', '2026-10-27T20:30:45Z', 'Shenzhen', 'Left the hub'),
        // A provider's two rows of one minute leave each instant its own group: the rows of one instant still pair.
        copy('bagged', '17TRACK', 'in_transit', '2026-10-27T20:40:00Z', 'Shenzhen', 'Bagged'),
        copy('bagged-late', 'ParcelsApp', 'in_transit', '2026-10-27T20:40:00Z', 'Shenzhen', 'Bagged'),
        copy('scanned', '17TRACK', 'in_transit', '2026-10-27T20:40:20Z', 'Shenzhen', 'Scanned'),
        copy('landed', '17TRACK', 'in_transit', '2026-10-27T21:00:00Z', 'Shenzhen', 'Landed'),
        copy('landed-late', 'ParcelsApp', 'in_transit', '2026-10-27T21:00:00Z', 'Shenzhen', 'Landed'),
        copy('unloaded-late', 'ParcelsApp', 'in_transit', '2026-10-27T21:00:00Z', 'Shenzhen', 'Unloaded'),
      ],
    };
    const served = (withEventPlaces(row) as { tracking_events: Record<string, unknown>[] }).tracking_events;
    expect(Object.fromEntries(served.map(({ id, relay_of }) => [id, relay_of ?? null]))).toEqual({
      posted: 'posted-late', 'posted-late': null,
      sorted: null, 'sorted-late': 'sorted',
      cleared: null, 'cleared-late': 'cleared',
      left: null, 'left-late': null, export: null, 'export-late': null, flown: null, 'flown-late': null,
      hub: null, 'hub-late': null, 'hub-other': null, bagged: null, 'bagged-late': 'bagged', scanned: null,
      landed: null, 'landed-late': null, 'unloaded-late': null,
    });
  });

  it('serves a scan that only tells again a step its carrier told before under the copy that has come further', () => {
    const scan = (id: string, source: string, stage: string, occurredAt: string, description: string) => ({
      ...event(id, 'Helsinki', occurredAt), stage, description, provider_event_id: `${source}:${id}`,
    });
    const row = {
      id: 'parcel', carrier: 'posti', carrier_data: { original_carrier: 'posti', active_tracking_carrier: 'la-poste' }, tracking_events: [
        scan('posted', 'la-poste', 'accepted', '2026-11-11T14:08:00+00:00', 'Votre colis a été déposé'),
        scan('posted-copy', 'posti', 'accepted', '2026-11-11T14:08:13+00:00', 'Lähetys on vastaanotettu'),
        // La Poste tells its deposit again when Posti's data reaches it, at the minute of Posti's sort: the sort goes on top.
        scan('again', 'la-poste', 'accepted', '2026-11-12T02:41:00+00:00', 'Votre colis a été déposé'),
        scan('sorted', 'posti', 'in_transit', '2026-11-12T02:41:36+00:00', 'Lähetys on lajiteltu'),
        // A scan in other words, or the copy no further, stays on top; but La Poste's acceptance in other words goes
        // under Posti's row in transit, as at any hand-over: the stage wins.
        scan('left', 'la-poste', 'accepted', '2026-11-13T02:41:00+00:00', 'Votre colis est pris en charge'),
        scan('left-copy', 'posti', 'in_transit', '2026-11-13T02:41:36+00:00', 'Lähetys on lähtenyt'),
        scan('arrived', 'la-poste', 'in_transit', '2026-11-14T02:41:00+00:00', 'Votre colis a été déposé'),
        scan('arrived-copy', 'posti', 'accepted', '2026-11-14T02:41:36+00:00', 'Lähetys on saapunut'),
      ],
    };
    const served = (withEventPlaces(row) as { tracking_events: Record<string, unknown>[] }).tracking_events;
    expect(Object.fromEntries(served.map(({ id, relay_of }) => [id, relay_of ?? null]))).toEqual({
      posted: null, 'posted-copy': 'posted',
      again: 'sorted', sorted: null,
      left: 'left-copy', 'left-copy': null,
      arrived: null, 'arrived-copy': 'arrived',
    });
  });

  it('shows once a scan La Poste lists twice at one instant, for "votre colis" and "votre Colissimo", and its copies', () => {
    const scan = (id: string, source: string, occurredAt: string, description: string, location: string | null, tie?: number) => ({
      ...event(id, location, occurredAt), description, provider_event_id: `${source}:${id}`, ...(tie === undefined ? {} : { tie_order: tie }),
    });
    const colis = 'Votre colis est en transit sur nos plateformes logistiques.';
    const colissimo = 'Votre Colissimo est en transit sur nos plateformes logistiques.';
    const row = {
      id: 'parcel', carrier: 'yunexpress', carrier_data: { active_tracking_carrier: 'la-poste', delivery_carrier: 'la-poste' }, tracking_events: [
        scan('next', 'la-poste', '2026-11-19T05:21:04+00:00', 'Votre Colissimo est arrivé sur son site de distribution.', 'Wissous'),
        // The later answer lists both wordings, the Colissimo one last; YunExpress copies both, one without a place.
        scan('lp-colissimo', 'la-poste', '2026-11-18T06:31:00+00:00', colissimo, 'Wissous', 1),
        scan('lp-colis', 'la-poste', '2026-11-18T06:31:00+00:00', colis, 'Wissous', 0),
        scan('first-colissimo', 'la-poste', '2026-11-16T06:33:25+00:00', colissimo, 'Wissous', 1),
        scan('first-colis', 'la-poste', '2026-11-16T06:33:25+00:00', colis, 'Wissous', 0),
        scan('copy-colissimo', 'yunexpress', '2026-11-16T06:33:25+00:00', colissimo, 'Wissous', 0),
        scan('copy-colis', 'yunexpress', '2026-11-16T06:33:25+00:00', colis, null, 1),
        // Other words at that instant, or the same pair a second apart, are scans of their own.
        scan('other', 'la-poste', '2026-11-14T08:00:00+00:00', 'Votre colis a quitté son pays d\'expédition.', 'Wissous'),
        scan('other-colissimo', 'la-poste', '2026-11-14T08:00:00+00:00', colissimo, 'Wissous'),
        scan('apart-colis', 'la-poste', '2026-11-13T08:00:01+00:00', colis, 'Wissous'),
        scan('apart-colissimo', 'la-poste', '2026-11-13T08:00:00+00:00', colissimo, 'Wissous'),
      ],
    };
    const served = (withEventPlaces(row) as { tracking_events: Record<string, unknown>[] }).tracking_events;
    expect(Object.fromEntries(served.map(({ id, relay_of }) => [id, relay_of ?? null]))).toEqual({
      next: null, 'lp-colissimo': null, 'first-colissimo': null, 'copy-colissimo': 'first-colissimo',
      other: null, 'other-colissimo': null, 'apart-colis': null, 'apart-colissimo': null,
    });
  });

  it('serves a scan that names only a country under the copy that names a town in it, unless the scan is the tracking carrier\'s', () => {
    const scan = (id: string, source: string, occurredAt: string, location: string, extra: Record<string, unknown> = {}) => ({
      ...event(id, location, occurredAt), stage: 'in_transit', description: `Fixture ${id}`, provider_event_id: `${source}:${id}`, ...extra,
    });
    const universal = { universal_provider: 'ParcelsApp' };
    const served = (row: Record<string, unknown>) => Object.fromEntries((withEventPlaces(row) as { tracking_events: Record<string, unknown>[] })
      .tracking_events.map(({ id, relay_of }) => [id, relay_of ?? null]));
    expect(served({
      id: 'parcel', carrier: 'royal-mail', carrier_data: { original_carrier: 'royal-mail', active_tracking_carrier: 'an-post' }, tracking_events: [
        // A universal provider names only the country of Royal Mail's sorting office: the office goes on top.
        scan('sorted', 'unknown', '2026-11-10T08:00:00Z', 'United Kingdom', universal),
        scan('sorted-copy', 'royal-mail', '2026-11-10T08:00:00Z', 'Leeds'),
        // In another stage it does not.
        scan('accepted', 'unknown', '2026-11-09T08:00:07Z', 'United Kingdom', { ...universal, stage: 'accepted' }),
        scan('accepted-copy', 'royal-mail', '2026-11-09T08:00:07Z', 'Leeds'),
        // Nor when the copy too names only the country.
        scan('posted', 'unknown', '2026-11-08T08:00:00Z', 'United Kingdom', universal),
        scan('posted-copy', 'royal-mail', '2026-11-08T08:00:00Z', 'United Kingdom'),
        // Nor over the tracking carrier's own scan.
        scan('arrived', 'an-post', '2026-11-12T08:00:00Z', 'Ireland'),
        scan('arrived-copy', 'royal-mail', '2026-11-12T08:00:00Z', 'Dublin'),
      ],
    })).toEqual({
      'arrived-copy': 'arrived', arrived: null, sorted: 'sorted-copy', 'sorted-copy': null, accepted: null, 'accepted-copy': 'accepted',
      posted: null, 'posted-copy': 'posted',
    });
    // Nor does a town written in another script, which the apps would show in it.
    expect(served({
      id: 'parcel', carrier: 'china-post', carrier_data: { original_carrier: 'china-post', active_tracking_carrier: 'an-post' }, tracking_events: [
        scan('sorted', 'unknown', '2026-11-10T08:00:00Z', 'China', universal),
        scan('sorted-copy', 'china-post', '2026-11-10T08:00:00Z', '广州'),
        scan('left', 'unknown', '2026-11-11T08:00:00Z', 'China', universal),
        scan('left-copy', 'china-post', '2026-11-11T08:00:00Z', 'Guangzhou'),
      ],
    })).toEqual({ 'left-copy': null, left: 'left-copy', sorted: null, 'sorted-copy': 'sorted' });
  });

  it('serves a scan that only registers the parcel under the earlier carrier\'s posting at its office, unless one tells the other\'s words', () => {
    const scan = (id: string, source: string, occurredAt: string, stage: string, location: string | null, description: string) => ({
      ...event(id, location, occurredAt), stage, description, provider_event_id: `${source}:${id}`,
    });
    const served = (...tracking_events: Record<string, unknown>[]) => Object.fromEntries((withEventPlaces({
      id: 'parcel', carrier: 'poczta-polska', carrier_data: { original_carrier: 'poczta-polska', active_tracking_carrier: 'bpost' }, tracking_events,
    }) as { tracking_events: Record<string, unknown>[] }).tracking_events.map(({ id, relay_of }) => [id, relay_of ?? null]));
    // bpost's notice that it has the data, at the minute of the posting, names the country: the posting goes on top.
    const notice = scan('notice', 'bpost', '2026-10-23T08:12:00Z', 'registered', 'Example Country', 'Fixture data received');
    expect(served(notice, scan('posted', 'poczta-polska', '2026-10-23T08:12:27Z', 'accepted', 'Example Office', 'Fixture posted at the office')))
      .toEqual({ notice: 'posted', posted: null });
    // Not a posting that says nowhere.
    expect(served(notice, scan('posted', 'poczta-polska', '2026-10-23T08:12:27Z', 'accepted', null, 'Fixture posted at the office')))
      .toEqual({ notice: null, posted: 'notice' });
    // Nor a consolidator's copy of the notice, its words holding the notice's, or held by them.
    expect(served(notice, scan('copy', 'poczta-polska', '2026-10-23T08:12:27Z', 'in_transit', 'Example Hub', 'Fixture data received by bpost')))
      .toEqual({ notice: null, copy: 'notice' });
    expect(served(notice, scan('copy', 'poczta-polska', '2026-10-23T08:12:27Z', 'accepted', 'Example Hub', 'Fixture data')))
      .toEqual({ notice: null, copy: 'notice' });
    // Nor a row no further on.
    expect(served(notice, scan('label', 'poczta-polska', '2026-10-23T08:12:27Z', 'registered', 'Example Office', 'Fixture label printed')))
      .toEqual({ notice: null, label: 'notice' });
    // Beside the next carrier's acceptance, the earlier carrier's row in transit is on top: the stage wins.
    expect(served(scan('taken', 'bpost', '2026-10-23T08:12:00Z', 'accepted', 'Example Country', 'Fixture taken over'),
      scan('left', 'poczta-polska', '2026-10-23T08:12:27Z', 'in_transit', 'Example Office', 'Fixture left the office')))
      .toEqual({ taken: 'left', left: null });
  });

  it('serves the next carrier\'s row in transit under the earlier carrier\'s posting at its office when that is the first step', () => {
    const scan = (id: string, source: string, occurredAt: string, stage: string, location: string | null, description: string) => ({
      ...event(id, location, occurredAt), stage, description, provider_event_id: `${source}:${id}`,
    });
    const served = (...tracking_events: Record<string, unknown>[]) => Object.fromEntries((withEventPlaces({
      id: 'parcel', carrier: 'poczta-polska', carrier_data: { original_carrier: 'poczta-polska', active_tracking_carrier: 'dhl' }, tracking_events,
    }) as { tracking_events: Record<string, unknown>[] }).tracking_events.map(({ id, relay_of }) => [id, relay_of ?? null]));
    // DHL tells Poczta Polska's posting as an arrival at its origin centre, naming the country: the posting goes on top,
    // a label printed before it or not.
    const arrived = scan('arrived', 'dhl', '2026-10-23T08:17:00Z', 'in_transit', 'Example Country', 'Fixture arrived at the origin centre');
    const posted = (location: string | null = 'Example Office', description = 'Fixture posted at the office', stage = 'accepted') =>
      scan('posted', 'poczta-polska', '2026-10-23T08:17:29Z', stage, location, description);
    expect(served(arrived, posted())).toEqual({ arrived: 'posted', posted: null });
    expect(served(scan('label', 'poczta-polska', '2026-10-22T16:00:00Z', 'registered', 'Example Office', 'Fixture label printed'), arrived, posted()))
      .toEqual({ label: null, arrived: 'posted', posted: null });
    // Not after an earlier step on the way: a collection after the posting the day before.
    expect(served(scan('sorted', 'poczta-polska', '2026-10-22T16:00:00Z', 'in_transit', 'Example Hub', 'Fixture sorted'), arrived, posted()))
      .toEqual({ sorted: null, arrived: null, posted: 'arrived' });
    // Nor a posting that says nowhere, nor a row of the earlier carrier's in transit.
    expect(served(arrived, posted(null))).toEqual({ arrived: null, posted: 'arrived' });
    expect(served(arrived, posted('Example Office', 'Fixture left the office', 'in_transit'))).toEqual({ arrived: null, posted: 'arrived' });
    // Nor beside a row past transit.
    expect(served(scan('arrived', 'dhl', '2026-10-23T08:17:00Z', 'customs', 'Example Country', 'Fixture at customs'), posted()))
      .toEqual({ arrived: null, posted: 'arrived' });
    // Nor one row's words holding the other's.
    expect(served(arrived, posted('Example Office', 'Fixture arrived'))).toEqual({ arrived: null, posted: 'arrived' });
    expect(served(arrived, posted('Example Office', 'Fixture arrived at the origin centre of the post'))).toEqual({ arrived: null, posted: 'arrived' });
  });

  it('serves a relay row in the words of the row it goes under, spelt otherwise, in that row\'s spelling', () => {
    const scan = (id: string, source: string, occurredAt: string, location: string | null, description: string, stage = 'in_transit') => ({
      ...event(id, location, occurredAt), stage, description, provider_event_id: `${source}:${id}`,
    });
    const row = {
      id: 'parcel', carrier: 'four-px', carrier_data: { original_carrier: 'four-px', active_tracking_carrier: 'speedx' },
      tracking_events: [
        // 4PX tells SpeedX's scan at its second, with the ZIP code: no more of the place, so SpeedX's row stays on top.
        scan('facility', 'speedx', '2026-11-07T20:01:49+00:00', 'Carteret, NJ', 'Processing at Designated Facility'),
        scan('facility-copy', 'four-px', '2026-11-07T20:01:49+00:00', 'Carteret,NJ, 7008', 'Processing at designated facility'),
        // A copy that names more of the place, in the same stage and words, goes on top.
        scan('hub', 'speedx', '2026-11-08T08:33:44+00:00', 'Newark', 'In transit to destination facility'),
        scan('hub-copy', 'four-px', '2026-11-08T08:33:44+00:00', 'Newark Airport', 'In transit to destination facility.'),
        // In another stage it does not.
        scan('out', 'speedx', '2026-11-09T07:10:05+00:00', 'Newark', 'Out for delivery', 'out_for_delivery'),
        scan('out-copy', 'four-px', '2026-11-09T07:10:05+00:00', 'Newark Airport', 'Out for delivery', 'in_transit'),
        // Other words keep their own.
        scan('sorted', 'speedx', '2026-11-09T09:00:12+00:00', 'Newark', 'Sorted'),
        scan('sorted-copy', 'four-px', '2026-11-09T09:00:12+00:00', 'Newark', 'Parcel sorted at hub'),
      ],
    };
    const served = (withEventPlaces(row) as { tracking_events: Record<string, unknown>[] }).tracking_events;
    expect(served.map(({ id, relay_of, description, location }) => [id, relay_of, description, location])).toEqual([
      ['facility', undefined, 'Processing at Designated Facility', 'Carteret, NJ'],
      ['facility-copy', 'facility', 'Processing at Designated Facility', 'Carteret,NJ, 7008'],
      ['hub', 'hub-copy', 'In transit to destination facility.', 'Newark'],
      ['hub-copy', undefined, 'In transit to destination facility.', 'Newark Airport'],
      ['out', undefined, 'Out for delivery', 'Newark'],
      ['out-copy', 'out', 'Out for delivery', 'Newark Airport'],
      ['sorted', undefined, 'Sorted', 'Newark'],
      ['sorted-copy', 'sorted', 'Parcel sorted at hub', 'Newark'],
    ]);
    // The rows stay as stored, in the account's export too.
    expect(row.tracking_events[1].description).toBe('Processing at designated facility');
    expect((withoutEventSources(row) as { tracking_events: Record<string, unknown>[] }).tracking_events[1].description)
      .toBe('Processing at designated facility');
  });

  it('serves an earlier carrier\'s copy in words that read as a stage neither row is in, in the words of the scan it tells, but a problem\'s', () => {
    const scan = (id: string, source: string, occurredAt: string, location: string | null, description: string, stage = 'in_transit') => ({
      ...event(id, location, occurredAt), stage, description, provider_event_id: `${source}:${id}`,
    });
    const row = {
      id: 'parcel', carrier: 'topyou', carrier_data: { original_carrier: 'topyou', active_tracking_carrier: 'gofo-it' },
      tracking_events: [
        // TopYou tells GOFO's transfer centre in a code it means as in transit, worded as out for delivery.
        scan('transfer', 'gofo-it', '2026-10-29T10:30:17+00:00', 'Milano', 'Registrato presso il centro di trasferimento'),
        scan('transfer-copy', 'topyou', '2026-10-29T10:30:17+00:00', null, 'Shipment is out for delivery'),
        // On top, as it alone names the place, it is served in the carrier's words too.
        scan('left', 'gofo-it', '2026-10-30T16:25:17+00:00', null, 'Il pacco ha lasciato il centro di smistamento'),
        scan('left-copy', 'topyou', '2026-10-30T16:25:17+00:00', 'Milano', 'Shipment is out for delivery'),
        // Words in the copy's stage, or in the scan's, keep their own; the hand-over's row in transit is on top.
        scan('sorted', 'gofo-it', '2026-10-31T06:49:14+00:00', 'Milano', 'Registrato presso il centro di smistamento'),
        scan('sorted-copy', 'topyou', '2026-10-31T06:49:00+00:00', null, 'In transit'),
        scan('handed', 'gofo-it', '2026-11-01T08:00:12+00:00', 'Milano', 'Presa in carico', 'accepted'),
        scan('handed-copy', 'topyou', '2026-11-01T08:00:12+00:00', null, 'Parcel handed to GOFO'),
        scan('cleared', 'gofo-it', '2026-11-02T09:10:11+00:00', 'Milano', 'Arrivato al centro'),
        scan('cleared-copy', 'topyou', '2026-11-02T09:10:11+00:00', null, 'Customs clearance', 'customs'),
        // On top, as it alone tells a problem, it keeps the problem's words.
        scan('missed', 'gofo-it', '2026-11-03T16:25:17+00:00', 'Milano', 'Arrivato al centro'),
        scan('missed-copy', 'topyou', '2026-11-03T16:25:17+00:00', null, 'Delivery failed, recipient not available', 'exception'),
      ],
    };
    const served = (withEventPlaces(row) as { tracking_events: Record<string, unknown>[] }).tracking_events;
    expect(served.map(({ id, relay_of, description }) => [id, relay_of, description])).toEqual([
      ['transfer', undefined, 'Registrato presso il centro di trasferimento'],
      ['transfer-copy', 'transfer', 'Registrato presso il centro di trasferimento'],
      ['left', 'left-copy', 'Il pacco ha lasciato il centro di smistamento'],
      ['left-copy', undefined, 'Il pacco ha lasciato il centro di smistamento'],
      ['sorted', undefined, 'Registrato presso il centro di smistamento'],
      ['sorted-copy', 'sorted', 'In transit'],
      ['handed', 'handed-copy', 'Presa in carico'],
      ['handed-copy', undefined, 'Parcel handed to GOFO'],
      ['cleared', undefined, 'Arrivato al centro'],
      ['cleared-copy', 'cleared', 'Customs clearance'],
      ['missed', 'missed-copy', 'Arrivato al centro'],
      ['missed-copy', undefined, 'Delivery failed, recipient not available'],
    ]);
    // The rows stay as stored, in the account's export too.
    expect(row.tracking_events[1].description).toBe('Shipment is out for delivery');
    expect((withoutEventSources(row) as { tracking_events: Record<string, unknown>[] }).tracking_events[1].description)
      .toBe('Shipment is out for delivery');
  });

  it('serves an earlier carrier\'s copy telling a scan\'s stage within its minute in words that share none in the scan\'s words', () => {
    const scan = (id: string, source: string, occurredAt: string, description: string, stage = 'in_transit', extra: Record<string, unknown> = {}) => ({
      ...event(id, 'Lyon', occurredAt), stage, description, provider_event_id: `${source}:${id}`, ...extra,
    });
    const row = {
      id: 'parcel', carrier: 'topyou', carrier_data: { original_carrier: 'topyou', active_tracking_carrier: 'gofo-fr' },
      tracking_events: [
        // TopYou tells GOFO France's French scans in English, at their second: the apps show the scan once.
        scan('left', 'gofo-fr', '2026-10-31T08:30:50+00:00', "Colis en cours d'acheminement"),
        scan('left-copy', 'topyou', '2026-10-31T08:30:50+00:00', 'The package left sorting center'),
        scan('delivered', 'gofo-fr', '2026-11-02T10:15:09+00:00', 'Livré', 'delivered'),
        scan('delivered-copy', 'topyou', '2026-11-02T10:15:09+00:00', 'Delivered by hand to recipient', 'delivered'),
        // A copy sharing a word may say more: it keeps its line.
        scan('out', 'gofo-fr', '2026-11-01T20:25:28+00:00', 'Out for delivery', 'out_for_delivery'),
        scan('out-copy', 'topyou', '2026-11-01T20:25:28+00:00', 'The driver is out for delivery', 'out_for_delivery'),
        // Within the minute, on a minute's first second too, it is the scan in the copy's language.
        scan('sorted', 'gofo-fr', '2026-10-30T17:51:05+00:00', 'Colis trié'),
        scan('sorted-copy', 'topyou', '2026-10-30T17:51:06+00:00', 'Arrived at the hub'),
        scan('hub', 'gofo-fr', '2026-10-29T09:12:00+00:00', 'Arrivé au centre'),
        scan('hub-copy', 'topyou', '2026-10-29T09:12:00+00:00', 'Parcel scanned'),
        scan('bagged', 'gofo-fr', '2026-10-29T07:40:37+00:00', 'Colis mis en sac'),
        scan('bagged-copy', 'topyou', '2026-10-29T07:40:00+00:00', 'Parcel bagged'),
        // Dated by its day, or hours away on another clock, it may be a scan of its own.
        scan('day', 'gofo-fr', '2026-10-28T09:12:07+00:00', 'Pris en charge'),
        scan('day-copy', 'topyou', '2026-10-28T09:12:07+00:00', 'Parcel scanned', 'in_transit', { time: '2026-10-28' }),
        scan('posted', 'gofo-fr', '2026-10-27T10:20:30+00:00', 'Déposé'),
        { ...scan('posted-copy', 'topyou', '2026-10-27T09:20:30+00:00', 'Shipment in transit'), location: null },
        // On top, as it alone names the place, the copy is served in the carrier's words too.
        { ...scan('arrived', 'gofo-fr', '2026-11-01T06:02:41+00:00', 'Arrivé au dépôt'), location: null },
        scan('arrived-copy', 'topyou', '2026-11-01T06:02:41+00:00', 'Parcel reached the depot'),
      ],
    };
    const served = (withEventPlaces(row) as { tracking_events: Record<string, unknown>[] }).tracking_events;
    expect(served.map(({ id, relay_of, description }) => [id, relay_of, description])).toEqual([
      ['left', undefined, "Colis en cours d'acheminement"],
      ['left-copy', 'left', "Colis en cours d'acheminement"],
      ['delivered', undefined, 'Livré'],
      ['delivered-copy', 'delivered', 'Livré'],
      ['out', undefined, 'Out for delivery'],
      ['out-copy', 'out', 'The driver is out for delivery'],
      ['sorted', undefined, 'Colis trié'],
      ['sorted-copy', 'sorted', 'Colis trié'],
      ['hub', undefined, 'Arrivé au centre'],
      ['hub-copy', 'hub', 'Arrivé au centre'],
      ['bagged', undefined, 'Colis mis en sac'],
      ['bagged-copy', 'bagged', 'Colis mis en sac'],
      ['day', undefined, 'Pris en charge'],
      ['day-copy', 'day', 'Parcel scanned'],
      ['posted', undefined, 'Déposé'],
      ['posted-copy', 'posted', 'Shipment in transit'],
      ['arrived', 'arrived-copy', 'Arrivé au dépôt'],
      ['arrived-copy', undefined, 'Arrivé au dépôt'],
    ]);
    // The rows stay as stored, in the account's export too.
    expect(row.tracking_events[1].description).toBe('The package left sorting center');
    expect((withoutEventSources(row) as { tracking_events: Record<string, unknown>[] }).tracking_events[1].description)
      .toBe('The package left sorting center');
  });

  it('serves an earlier carrier\'s copy in transit of the new carrier\'s acceptance at its second and place on top, in the new carrier\'s words', () => {
    const scan = (id: string, source: string, occurredAt: string, location: string | null, description: string, stage = 'in_transit') => ({
      ...event(id, location, occurredAt), stage, description, provider_event_id: `${source}:${id}`,
    });
    const row = {
      id: 'parcel', carrier: 'yunexpress', carrier_data: { original_carrier: 'yunexpress', active_tracking_carrier: 'gofo-fr' },
      tracking_events: [
        scan('flown', 'yunexpress', '2026-11-10T13:55:05+00:00', null, 'Arrived at destination airport'),
        scan('handed', 'yunexpress', '2026-11-10T14:24:05+00:00', null, 'Handed over to the last-mile carrier'),
        // YunExpress tells GOFO France's acceptance at its sorting centre as an arrival there, at its second and place.
        scan('accepted', 'gofo-fr', '2026-11-19T09:53:45+00:00', 'Lyon', 'Colis pris en charge au centre de tri', 'accepted'),
        scan('accepted-copy', 'yunexpress', '2026-11-19T09:53:45+00:00', 'Lyon', 'Arrived at the sorting centre'),
        // Naming no place, or on a minute's first second, the copy is on top all the same, in its own words: the stage
        // wins.
        scan('taken', 'gofo-fr', '2026-11-20T08:10:12+00:00', 'Lyon', 'Colis pris en charge', 'accepted'),
        scan('taken-copy', 'yunexpress', '2026-11-20T08:10:12+00:00', null, 'Arrived at the sorting centre'),
        scan('kept', 'gofo-fr', '2026-11-21T08:10:00+00:00', 'Lyon', 'Colis reçu au dépôt', 'accepted'),
        scan('kept-copy', 'yunexpress', '2026-11-21T08:10:00+00:00', 'Lyon', 'Arrived at the sorting centre'),
      ],
    };
    const served = (withEventPlaces(row) as { tracking_events: Record<string, unknown>[] }).tracking_events;
    expect(served.map(({ id, relay_of, description }) => [id, relay_of, description])).toEqual([
      ['flown', undefined, 'Arrived at destination airport'],
      ['handed', undefined, 'Handed over to the last-mile carrier'],
      ['accepted', 'accepted-copy', 'Colis pris en charge au centre de tri'],
      ['accepted-copy', undefined, 'Colis pris en charge au centre de tri'],
      ['taken', 'taken-copy', 'Colis pris en charge'],
      ['taken-copy', undefined, 'Arrived at the sorting centre'],
      ['kept', 'kept-copy', 'Colis reçu au dépôt'],
      ['kept-copy', undefined, 'Arrived at the sorting centre'],
    ]);
    // The apps' headline: the hand-over, in transit, not the scan before it.
    const parcelEvents: TrackingEvent[] = served.filter((event) => ['flown', 'handed', 'accepted', 'accepted-copy'].includes(String(event.id)))
      .map((event) => ({
        id: String(event.id), parcelId: 'parcel', stage: event.stage as Stage, description: String(event.description),
        occurredAt: String(event.occurred_at), ...(typeof event.relay_of === 'string' ? { relayOf: event.relay_of } : {}),
      }));
    expect(currentEvent(parcelEvents)).toMatchObject({ id: 'accepted-copy', stage: 'in_transit' });
    // The rows stay as stored.
    expect(row.tracking_events[3].description).toBe('Arrived at the sorting centre');
  });

  it('heads a hand-over with the earlier carrier\'s row in transit, placed or not, so the apps\' headline does not step back', () => {
    const scan = (id: string, source: string, occurredAt: string, location: string | null, description: string, stage = 'in_transit') => ({
      ...event(id, location, occurredAt), stage, description, provider_event_id: `${source}:${id}`,
    });
    const headline = (accepted: Record<string, unknown>, copy: Record<string, unknown>) => {
      const served = (withEventPlaces({
        id: 'parcel', carrier: 'yanwen', carrier_data: { original_carrier: 'yanwen', active_tracking_carrier: 'postnl' },
        tracking_events: [scan('departed', 'yanwen', '2026-11-10T08:00:00+00:00', 'Example Hub', 'Departed from the sorting centre'), accepted, copy],
      }) as { tracking_events: Record<string, unknown>[] }).tracking_events;
      const events: TrackingEvent[] = served.map((event) => ({
        id: String(event.id), parcelId: 'parcel', stage: event.stage as Stage, description: String(event.description),
        occurredAt: String(event.occurred_at), ...(typeof event.relay_of === 'string' ? { relayOf: event.relay_of } : {}),
      }));
      return { heads: Object.fromEntries(served.map(({ id, relay_of }) => [id, relay_of ?? null])), current: currentEvent(events) };
    };
    // Yanwen tells PostNL's acceptance as a scan in transit at its second: whichever row names no place, the row in
    // transit is on top, and the headline is the hand-over, not Yanwen's departure two days before.
    for (const [acceptedAt, copyAt] of [['Example Depot', null], [null, 'Example Depot'], [null, null]]) {
      const { heads, current } = headline(scan('accepted', 'postnl', '2026-11-12T17:05:17+00:00', acceptedAt, 'Zending ontvangen', 'accepted'),
        scan('accepted-copy', 'yanwen', '2026-11-12T17:05:17+00:00', copyAt, 'Arrived at the delivery partner'));
      expect(heads).toEqual({ departed: null, accepted: 'accepted-copy', 'accepted-copy': null });
      expect(current).toMatchObject({ id: 'accepted-copy', stage: 'in_transit', occurredAt: '2026-11-12T17:05:17+00:00' });
    }
    // So for the copy cut to the minute, and beside the next carrier's notice that it has the parcel's data.
    expect(headline(scan('accepted', 'postnl', '2026-11-12T17:05:17+00:00', 'Example Depot', 'Zending ontvangen', 'accepted'),
      scan('accepted-copy', 'yanwen', '2026-11-12T17:05:00+00:00', 'Example Depot', 'Arrived at the delivery partner')).current)
      .toMatchObject({ id: 'accepted-copy', stage: 'in_transit' });
    expect(headline(scan('accepted', 'postnl', '2026-11-12T17:05:17+00:00', null, 'Zending aangemeld', 'registered'),
      scan('accepted-copy', 'yanwen', '2026-11-12T17:05:17+00:00', null, 'Arrived at the delivery partner')).current)
      .toMatchObject({ id: 'accepted-copy', stage: 'in_transit' });
  });

  it('serves an earlier carrier\'s copy telling a scan\'s second and stage in no word beyond the scan\'s words and place in the scan\'s words', () => {
    const scan = (id: string, source: string, occurredAt: string, location: string | null, description: string, stage = 'in_transit') => ({
      ...event(id, location, occurredAt), stage, description, provider_event_id: `${source}:${id}`,
    });
    const row = {
      id: 'parcel', carrier: 'four-px', carrier_data: { original_carrier: 'four-px', active_tracking_carrier: 'imile' },
      tracking_events: [
        // 4PX tells iMile's scans cut short, at their second: the apps show the scan once.
        scan('arrived', 'imile', '2026-11-03T03:17:59+00:00', 'Varese Hub', 'Your order has arrived at Varese Hub'),
        scan('arrived-copy', 'four-px', '2026-11-03T03:17:59+00:00', 'Varese Hub', '【varese hub】arrived.'),
        scan('received', 'imile', '2026-11-02T14:55:33+00:00', 'Varese Hub', 'Your order has been received.'),
        scan('received-copy', 'four-px', '2026-11-02T14:55:33+00:00', 'Varese Hub', '【varese hub】received.'),
        // A copy that adds a word, the station it is shipped to, keeps its line.
        scan('shipped', 'imile', '2026-11-03T03:32:34+00:00', 'Varese Hub', 'Your order has been shipped from Varese Hub'),
        scan('shipped-copy', 'four-px', '2026-11-03T03:32:34+00:00', 'Varese Hub', '【varese hub】parcel shipped to【station 01】.'),
        // In the scan's very words, it keeps the upper row's spelling.
        scan('sorted', 'imile', '2026-11-04T08:10:11+00:00', 'Varese Hub', 'SORTED AT HUB'),
        scan('sorted-copy', 'four-px', '2026-11-04T08:10:11+00:00', null, 'Sorted at hub'),
      ],
    };
    const served = (withEventPlaces(row) as { tracking_events: Record<string, unknown>[] }).tracking_events;
    expect(served.map(({ id, relay_of, description }) => [id, relay_of, description])).toEqual([
      ['arrived', undefined, 'Your order has arrived at Varese Hub'],
      ['arrived-copy', 'arrived', 'Your order has arrived at Varese Hub'],
      ['received', undefined, 'Your order has been received.'],
      ['received-copy', 'received', 'Your order has been received.'],
      ['shipped', undefined, 'Your order has been shipped from Varese Hub'],
      ['shipped-copy', 'shipped', '【varese hub】parcel shipped to【station 01】.'],
      ['sorted', undefined, 'SORTED AT HUB'],
      ['sorted-copy', 'sorted', 'SORTED AT HUB'],
    ]);
    // The rows stay as stored, in the account's export too.
    expect(row.tracking_events[1].description).toBe('【varese hub】arrived.');
    expect((withoutEventSources(row) as { tracking_events: Record<string, unknown>[] }).tracking_events[1].description)
      .toBe('【varese hub】arrived.');
  });

  it('keeps a copy on another clock under the scan it tells', () => {
    const scan = (id: string, source: string, stage: string, occurredAt: string, location: string | null = null) => ({
      ...event(id, location, occurredAt), stage, description: `Fixture ${stage}`, provider_event_id: `${source}:${id}`,
    });
    // WanB Express tells J&T Australia's failed delivery two hours early, placed; J&T's own rows name no place.
    const row = {
      id: 'parcel', carrier: 'wanbexpress', carrier_data: { original_carrier: 'wanbexpress', active_tracking_carrier: 'j-and-t-au' },
      tracking_events: [
        scan('round', 'j-and-t-au', 'out_for_delivery', '2026-11-18T19:39:32Z'),
        scan('missed-early', 'wanbexpress', 'failed_attempt', '2026-11-18T18:12:28Z', 'VIC, AU'),
        scan('missed', 'j-and-t-au', 'failed_attempt', '2026-11-18T20:12:28Z'),
      ],
    };
    const served = (withEventPlaces(row) as { tracking_events: Record<string, unknown>[] }).tracking_events;
    // The failed delivery is dated by J&T's row, and stays the parcel's current step.
    expect(served.find((item) => item.id === 'missed-early')?.relay_of).toBe('missed');
    expect(served.find((item) => item.id === 'missed')?.relay_of).toBeUndefined();
    expect(currentEvent(served.map((item) => ({ id: String(item.id), stage: item.stage as Stage, occurredAt: String(item.occurred_at),
      relayOf: item.relay_of as string | undefined, description: String(item.description) })) as TrackingEvent[])?.id).toBe('missed');
  });

  it('serves a carrier\'s repeats of one scan under the newest of them', () => {
    const scan = (id: string, occurredAt: string, extra: Record<string, unknown> = {}) => ({
      ...event(id, null, occurredAt), stage: 'failed_attempt', description: 'Address details incorrect',
      provider_event_id: `j-and-t-au:${id}`, ...extra,
    });
    const row = {
      id: 'parcel', carrier: 'j-and-t-au', tracking_events: [
        // Told three times in three minutes: one step.
        scan('first', '2026-10-26T12:29:09Z'), scan('again', '2026-10-26T12:29:33Z'), scan('last', '2026-10-26T12:32:44Z'),
        // Told again more than an hour after the first: the parcel is still there.
        scan('later', '2026-10-26T13:35:00Z'),
        // Elsewhere, in another stage, from a provider, or after another scan: steps of their own.
        scan('elsewhere', '2026-10-26T13:36:00Z', { location: 'Other Town' }),
        scan('held', '2026-10-26T13:37:00Z', { location: 'Other Town', stage: 'exception' }),
        scan('copy', '2026-10-26T13:38:00Z', { location: 'Other Town', stage: 'exception', universal_provider: '17TRACK' }),
        scan('back', '2026-10-26T13:39:00Z', { location: 'Other Town' }),
      ],
    };
    const served = (withEventPlaces(row) as { tracking_events: Record<string, unknown>[] }).tracking_events;
    expect(served.map(({ id, relay_of }) => [id, relay_of])).toEqual([
      ['first', 'last'], ['again', 'last'], ['last', undefined], ['later', undefined],
      ['elsewhere', undefined], ['held', undefined], ['copy', undefined], ['back', undefined],
    ]);
  });

  it('runs a carrier\'s repeats on past its other rows of their instant', () => {
    const scan = (id: string, occurredAt: string, description: string, extra: Record<string, unknown> = {}) => ({
      ...event(id, null, occurredAt), stage: 'out_for_delivery', description, provider_event_id: `fan-courier:${id}`, ...extra,
    });
    const row = {
      id: 'parcel', carrier: 'fan-courier', tracking_events: [
        // Synthetic FAN Courier rows: a delivery round under two codes at one second, the first code told again later.
        scan('first', '2026-11-13T05:55:05Z', 'Fixture round', { tie_order: 1 }),
        scan('other', '2026-11-13T05:55:05Z', 'Fixture other code', { tie_order: 0 }),
        scan('repeat', '2026-11-13T06:49:23Z', 'Fixture round'),
        // A notice told again two minutes later, a scan in another stage at the first one's instant.
        scan('notice', '2026-11-17T05:47:00Z', 'Fixture delay', { stage: 'pending' }),
        scan('depot', '2026-11-17T05:47:00Z', 'Fixture depot', { stage: 'in_transit' }),
        scan('notice-again', '2026-11-17T05:49:00Z', 'Fixture delay', { stage: 'pending' }),
        // A scan of a later instant comes between: the round told after it is news.
        scan('round', '2026-11-18T06:00:00Z', 'Fixture round'),
        scan('sorted', '2026-11-18T06:10:00Z', 'Fixture sorted', { stage: 'in_transit' }),
        scan('round-again', '2026-11-18T06:20:00Z', 'Fixture round'),
      ],
    };
    const served = (withEventPlaces(row) as { tracking_events: Record<string, unknown>[] }).tracking_events;
    expect(Object.fromEntries(served.map(({ id, relay_of }) => [id, relay_of ?? null]))).toEqual({
      first: 'repeat', other: null, repeat: null, notice: 'notice-again', depot: null, 'notice-again': null,
      round: null, sorted: null, 'round-again': null,
    });
  });

  it('serves a carrier\'s notice in the stage of its scan before it under that scan, so the headline stays on the scan', () => {
    // Synthetic DPD Poland rows: a notice its adapter left in the stage of the scan before it (`stage_source` `none`).
    const scan = (id: string, stage: string, occurredAt: string, stageSource: string, extra: Record<string, unknown> = {}) => ({
      ...event(id, null, occurredAt), stage, description: `Fixture ${id}`, provider_event_id: `dpd-pl:${id}`, stage_source: stageSource, ...extra,
    });
    const row = {
      id: 'parcel', carrier: 'dpd-pl', tracking_events: [
        scan('collected', 'accepted', '2026-11-06T22:59:50Z', 'carrier_map'),
        scan('notice', 'accepted', '2026-11-07T00:15:17Z', 'none'),
        // A scan no rule reads is in transit in `none` too: news that the parcel moves, a row of its own.
        scan('depot', 'in_transit', '2026-11-07T05:20:32Z', 'carrier_map'),
        scan('unread', 'in_transit', '2026-11-07T06:00:00Z', 'none'),
        // Unless it is stored as a notice its carrier's map says keeps the stage (keptNotices), which says nothing new.
        scan('kept', 'in_transit', '2026-11-07T06:30:00Z', 'none', { kept_stage: true }),
        scan('point', 'ready_for_pickup', '2026-11-07T09:15:46Z', 'carrier_map'),
        // A mail notice and a text message of one instant both go under the scan.
        scan('mail', 'ready_for_pickup', '2026-11-07T09:19:41Z', 'none'),
        scan('text', 'ready_for_pickup', '2026-11-07T09:19:41Z', 'none'),
        // A provider's row is no notice of the carrier's scan, nor a stage of its own the carrier gave.
        scan('provider', 'ready_for_pickup', '2026-11-07T09:30:00Z', 'none', { provider_event_id: 'unknown:provider', universal_provider: '17TRACK' }),
      ],
    };
    const served = (withEventPlaces(row) as { tracking_events: Record<string, unknown>[] }).tracking_events;
    expect(served.map(({ id, relay_of }) => [id, relay_of])).toEqual([
      ['collected', undefined], ['notice', 'collected'], ['depot', undefined], ['unread', undefined], ['kept', 'unread'],
      ['point', undefined], ['mail', 'point'], ['text', 'point'], ['provider', undefined],
    ]);
    expect(served.some((event) => 'stage_source' in event || 'kept_stage' in event)).toBe(false);
    // The apps' headline: the pickup point's scan, not the notices after it.
    const parcelEvents: TrackingEvent[] = served.filter((event) => event.id !== 'provider').map((event) => ({
      id: String(event.id), parcelId: 'parcel', stage: event.stage as Stage, description: String(event.description),
      occurredAt: String(event.occurred_at), ...(typeof event.relay_of === 'string' ? { relayOf: event.relay_of } : {}),
    }));
    expect(currentEvent(parcelEvents)?.id).toBe('point');
    expect((withoutEventSources(row).tracking_events as Record<string, unknown>[]).some((event) => 'stage_source' in event)).toBe(false);
    // The scan before a notice may be the answer stored when the parcel was added, before its carrier was known: the
    // carrier's own row of that scan joined it. A universal provider's row is no such scan.
    const added = (provider?: string) => ({
      id: 'parcel', carrier: 'dpd-fr', tracking_events: [
        scan('depot', 'in_transit', '2026-11-16T21:20:00Z', 'carrier_map'),
        scan('round', 'out_for_delivery', '2026-11-17T07:28:00Z', 'carrier_map',
          { provider_event_id: 'unknown:round', ...(provider ? { universal_provider: provider } : {}) }),
        scan('text', 'out_for_delivery', '2026-11-17T07:33:00Z', 'none', { kept_stage: true }),
      ],
    });
    const notices = (served: Record<string, unknown>[]) => served.map(({ id, relay_of }) => [id, relay_of]);
    expect(notices((withEventPlaces(added()) as { tracking_events: Record<string, unknown>[] }).tracking_events)).toEqual([
      ['depot', undefined], ['round', undefined], ['text', 'round'],
    ]);
    expect(notices((withEventPlaces(added('17TRACK')) as { tracking_events: Record<string, unknown>[] }).tracking_events)).toEqual([
      ['depot', undefined], ['round', undefined], ['text', undefined],
    ]);
  });

  it('serves a notice its carrier sent twice as one step, headed by the newer row', () => {
    // Synthetic DPD Germany rows: the first step, a stage-less notice, sent again in the same words 19 minutes later.
    const scan = (id: string, stage: string, occurredAt: string, stageSource: string) => ({
      ...event(id, null, occurredAt), stage, description: 'Fixture data exchanged', provider_event_id: `dpd-de:${id}`, stage_source: stageSource,
    });
    const row = {
      id: 'parcel', carrier: 'dpd-de', tracking_events: [
        scan('first', 'registered', '2026-11-16T15:03:56Z', 'none'),
        scan('again', 'registered', '2026-11-16T15:23:02Z', 'none'),
        scan('collected', 'accepted', '2026-11-17T18:47:46Z', 'carrier_map'),
      ],
    };
    const served = (withEventPlaces(row) as { tracking_events: Record<string, unknown>[] }).tracking_events;
    expect(served.map(({ id, relay_of }) => [id, relay_of])).toEqual([['first', 'again'], ['again', undefined], ['collected', undefined]]);
    const listed = withRelays(served.map((event) => ({
      id: String(event.id), ...(typeof event.relay_of === 'string' ? { relayOf: event.relay_of } : {}),
    }))).listed.map(({ id }) => id);
    expect(listed).toEqual(['again', 'collected']);
  });

  it('serves a notice after a relay copy under the step that copy is part of, not an older scan of its carrier', () => {
    // Synthetic rows: the earlier carrier tells each failed delivery of the new one, and a notice of its own after each.
    const scan = (id: string, source: string, occurredAt: string, stageSource = 'carrier_map') => ({
      ...event(id, null, occurredAt), stage: 'failed_attempt', description: `Fixture ${id}`, provider_event_id: `${source}:${id}`,
      stage_source: stageSource,
    });
    const row = {
      id: 'parcel', carrier: 'dhl', carrier_data: { original_carrier: 'dhl', active_tracking_carrier: 'swiss-post' }, tracking_events: [
        scan('first', 'swiss-post', '2026-11-04T12:11:40Z'),
        scan('first-copy', 'dhl', '2026-11-04T12:11:00Z'),
        scan('first-notice', 'dhl', '2026-11-04T17:41:36Z', 'none'),
        scan('second', 'swiss-post', '2026-11-05T11:25:08Z'),
        scan('second-copy', 'dhl', '2026-11-05T11:25:00Z'),
        scan('second-notice', 'dhl', '2026-11-05T18:44:55Z', 'none'),
      ],
    };
    const served = (withEventPlaces(row) as { tracking_events: Record<string, unknown>[] }).tracking_events;
    expect(served.map(({ id, relay_of }) => [id, relay_of])).toEqual([
      ['first', undefined], ['first-copy', 'first'], ['first-notice', 'first'],
      ['second', undefined], ['second-copy', 'second'], ['second-notice', 'second'],
    ]);
  });

  it('serves every row of a scan told several times under the one row on top, and no row under a row that is under another', () => {
    // Synthetic rows: EMS carries on a Parcelforce parcel and Australia Post tells EMS's scan too. Parcelforce's copy
    // alone names the place, and a universal provider stored that copy again.
    const scan = (id: string, source: string, extra: Record<string, unknown> = {}) => ({
      ...event(id, null, '2026-11-13T13:13:00Z'), stage: 'accepted', description: `Fixture ${id}`, provider_event_id: `${source}:${id}`, ...extra,
    });
    const row = {
      id: 'parcel', carrier: 'parcelforce', carrier_data: { original_carrier: 'parcelforce', active_tracking_carrier: 'ems' }, tracking_events: [
        scan('accepted', 'ems'),
        scan('accepted-post', 'australia-post'),
        scan('accepted-copy', 'parcelforce', { occurred_at: '2026-11-13T13:13:33Z', location: 'Example Town' }),
        scan('accepted-provider', 'unknown', {
          occurred_at: '2026-11-13T13:13:33Z', location: 'Example Town', description: 'Fixture accepted-copy', universal_provider: 'ParcelsApp',
        }),
      ],
    };
    const served = (withEventPlaces(row) as { tracking_events: Record<string, unknown>[] }).tracking_events;
    expect(Object.fromEntries(served.map(({ id, relay_of }) => [id, relay_of ?? null]))).toEqual({
      'accepted-copy': null, accepted: 'accepted-copy', 'accepted-post': 'accepted-copy', 'accepted-provider': 'accepted-copy',
    });
  });

  it('leaves out a provider\'s copy of a confirmed carrier\'s scan, and pairs relay copies without it', () => {
    const scan = (id: string, source: string, occurredAt: string, extra: Record<string, unknown> = {}) => ({
      ...event(id, null, occurredAt), provider_event_id: `${source}:${id}`, ...extra,
    });
    const history = { from: '2026-10-20T09:00:00.000Z', to: '2026-10-20T10:00:30.000Z', held: [] };
    const row = {
      id: 'parcel', carrier: 'royal-mail', carrier_data: {
        original_carrier: 'royal-mail', active_tracking_carrier: 'ctt', routing: { confirmed_carrier: 'ctt' }, direct_history: history,
      },
      tracking_events: [
        scan('departed', 'ctt', '2026-10-20T10:00:30+00:00'),
        scan('relay', 'royal-mail', '2026-10-20T10:00:00+00:00'),
        // The provider's copy at the carrier's minute would be the relay's closer pair.
        scan('copy', 'unknown', '2026-10-20T10:00:00+00:00', { universal_provider: 'ParcelsApp', description: 'Item departed' }),
        scan('accepted', 'unknown', '2026-10-19T08:00:00+00:00', { universal_provider: 'ParcelsApp', stage: 'accepted' }),
      ],
    };
    const served = withEventPlaces(row) as { carrier_data: Record<string, unknown>; tracking_events: Record<string, unknown>[] };
    expect(served.tracking_events.map(({ id, relay_of }) => [id, relay_of])).toEqual([
      ['departed', undefined], ['relay', 'departed'], ['accepted', undefined],
    ]);
    expect(served.tracking_events.every((scan) => !('universal_provider' in scan))).toBe(true);
    expect(served.carrier_data).not.toHaveProperty('direct_history');
    // The export keeps every row, without the provider or the carrier's history.
    const exported = withoutEventSources(row) as typeof served;
    expect(exported.tracking_events.map((scan) => scan.id)).toEqual(['departed', 'relay', 'copy', 'accepted']);
    expect(exported.tracking_events.every((scan) => !('universal_provider' in scan))).toBe(true);
    expect(exported.carrier_data).not.toHaveProperty('direct_history');
  });

  it('places a carrier\'s scan that names only its office where the best left-out copy of it says, and no other scan', () => {
    const scan = (id: string, source: string, occurredAt: string, location: string, extra: Record<string, unknown> = {}) => ({
      ...event(id, location, occurredAt), provider_event_id: `${source}:${id}`, ...extra,
    });
    const row = {
      id: 'parcel', carrier: 'correos-chile', carrier_data: { destination_country: 'BR', routing: { confirmed_carrier: 'correos-chile' } },
      tracking_events: [
        scan('posted', 'correos-chile', '2026-09-11T13:43:00+00:00', 'SANTIAGO'),
        scan('arrived', 'correos-chile', '2026-09-29T11:05:00+00:00', 'OFICINA INTERNACIONAL'),
        // 17TRACK at the scan's minute, ParcelsApp three hours early: the copy at the minute places it.
        scan('arrived-copy', 'unknown', '2026-09-29T11:05:14+00:00', 'Curitiba', { universal_provider: '17TRACK' }),
        scan('arrived-early', 'unknown', '2026-09-29T08:05:00+00:00', 'Sao Paulo, Brazil', { universal_provider: 'ParcelsApp' }),
        // A copy never moves a scan the carrier placed itself.
        scan('posted-copy', 'unknown', '2026-09-11T13:43:00+00:00', 'Valparaiso', { universal_provider: 'ParcelsApp', stage: 'accepted' }),
      ],
    };
    const located = (parcel: typeof row) => Object.fromEntries((withEventPlaces(parcel) as { tracking_events: Record<string, unknown>[] })
      .tracking_events.map(({ id, location, place }) => [id, [location, (place as places.EventPlace | null)?.name ?? null]]));
    expect(located(row)).toEqual({ posted: ['SANTIAGO', 'Santiago'], arrived: ['OFICINA INTERNACIONAL', 'Curitiba'] });
    // A copy that repeats two of the carrier's scans places neither; the copy of one alone still places it.
    const customs = scan('customs', 'correos-chile', '2026-09-29T11:05:30+00:00', 'OFICINA INTERNACIONAL', { stage: 'customs' });
    expect(located({ ...row, tracking_events: [...row.tracking_events, customs] })).toEqual({
      posted: ['SANTIAGO', 'Santiago'], arrived: ['OFICINA INTERNACIONAL', 'Sao Paulo'], customs: ['OFICINA INTERNACIONAL', null],
    });
    // Nor does a copy place a scan in a country the parcel is not going to, its carrier does not deliver in and no scan shows.
    expect(located({ ...row, carrier_data: { routing: { confirmed_carrier: 'correos-chile' } } as typeof row.carrier_data }))
      .toEqual({ posted: ['SANTIAGO', 'Santiago'], arrived: ['OFICINA INTERNACIONAL', null] });
  });

  it('keeps a provider row the new carrier\'s answer took over, with the earlier carrier\'s scan of it as its relay', () => {
    // CTT tells Royal Mail's scans to the minute; its answer took over the provider's copies of them, which it holds.
    const scan = (id: string, source: string, stage: string, occurredAt: string) => ({
      ...event(id, null, occurredAt), stage, provider_event_id: `${source}:${id}`,
    });
    const row = {
      id: 'parcel', carrier: 'royal-mail', carrier_data: {
        original_carrier: 'royal-mail', active_tracking_carrier: 'ctt', routing: { confirmed_carrier: 'royal-mail' },
        direct_history: { from: '2026-10-15T00:02:02.000Z', to: '2026-10-20T04:10:08.000Z', held: ['unknown:accepted', 'unknown:forwarded'] },
      },
      tracking_events: [
        scan('accepted', 'unknown', 'accepted', '2026-10-16T17:21:00+00:00'),
        scan('received', 'royal-mail', 'accepted', '2026-10-16T17:21:16+00:00'),
        scan('forwarded', 'unknown', 'in_transit', '2026-10-20T04:10:00+00:00'),
        scan('leaving', 'royal-mail', 'in_transit', '2026-10-20T04:10:08+00:00'),
      ],
    };
    const served = (withEventPlaces(row) as { tracking_events: Record<string, unknown>[] }).tracking_events;
    expect(served.map(({ id, relay_of }) => [id, relay_of])).toEqual([
      ['accepted', undefined], ['received', 'accepted'], ['forwarded', undefined], ['leaving', 'forwarded'],
    ]);
  });

  it('serves the parcel without places when the gazetteer fails, and reports it once', () => {
    vi.spyOn(places, 'placesForEvents').mockImplementation(() => {
      throw new Error('missing gazetteer');
    });
    const capture = vi.spyOn(observability, 'captureOperationalError').mockReturnValue(null);
    const row = { id: 'parcel', carrier: 'not-a-carrier', tracking_events: [event('1', 'Zürich', '2026-09-26T09:00:00+00:00')] };
    expect(withEventPlaces(row)).toEqual(row);
    expect(withEventPlaces(row)).toEqual(row);
    expect(capture).toHaveBeenCalledTimes(1);
    expect(capture).toHaveBeenCalledWith(expect.any(Error), { component: 'api', operation: 'locate_event_places' });
  });
});

it('names the day of a scan the carrier dated without a clock time, flags a status seen at a check, and serves none of its stored times', () => {
  const stored = (id: string, times: Record<string, unknown>) => ({ ...event(id, null, '2026-10-19T00:00:00+00:00'), ...times });
  const row = { id: 'parcel', carrier: 'nacex', tracking_events: [
    stored('local-day', { local_time: '2026-10-19' }),
    stored('day', { time: '18/10/2026' }),
    stored('clock', { time: '2026-10-18T09:30:00Z' }),
    stored('clock-and-day', { time: '2026-10-18T09:30:00Z', local_time: '2026-10-18' }),
    stored('local-clock', { local_time: '2026-10-17T09:30:00', observed: true }),
    stored('observed', { local_time: '2026-10-17', observed: true }),
    // A wall clock in no zone (YunExpress's) dates its scan by its day; with an instant, the instant dates it.
    stored('wall-clock', { local_time: '2026-10-16T18:20:00' }),
    stored('wall-clock-and-time', { time: '2026-10-16T18:20:00+08:00', local_time: '2026-10-16T18:20:00' }),
  ] };
  const days = (served: { tracking_events: Array<Record<string, unknown>> }) => served.tracking_events.map((scan) => [scan.id, scan.day]);
  const expected = [['local-day', '2026-10-19'], ['day', '2026-10-18'], ['clock', undefined], ['clock-and-day', undefined], ['local-clock', undefined], ['observed', undefined],
    ['wall-clock', '2026-10-16'], ['wall-clock-and-time', undefined]];
  const served = withEventPlaces(row) as { tracking_events: Array<Record<string, unknown>> };
  expect(days(served)).toEqual(expected);
  expect(days(withoutEventSources(row) as typeof served)).toEqual(expected);
  expect(served.tracking_events.some((scan) => 'local_time' in scan || 'time' in scan)).toBe(false);
  // Its time is the check's, not the carrier's: the apps show the day alone.
  expect(served.tracking_events.filter((scan) => 'observed' in scan).map((scan) => [scan.id, scan.observed]))
    .toEqual([['local-clock', true], ['observed', true]]);
});

it('serves a scan or stage change seen without the carrier\'s words without Peek\'s stand-in, so the apps name the stage', () => {
  const row = { id: 'parcel', carrier: 'aliexpress', tracking_events: [
    { ...event('seen', null, '2026-10-20T22:25:08+00:00'), stage: 'registered', description: 'Tracking update', observed: true },
    { ...event('scan', null, '2026-10-20T20:00:00+00:00'), stage: 'in_transit', description: 'Tracking update' },
  ] };
  const served = withEventPlaces(row) as { tracking_events: Array<Record<string, unknown>> };
  expect(served.tracking_events.map((scan) => [scan.id, scan.description])).toEqual([['seen', ''], ['scan', '']]);
  expect((withoutEventSources(row) as typeof served).tracking_events[0].description).toBe('Tracking update');
});

it('leaves out a sighting a scan of its source tells in another stage, once the parcel has moved on', () => {
  const at = (id: string, source: string, stage: string, occurredAt: string, description: string, extra: Record<string, unknown> = {}) => ({
    ...event(id, null, occurredAt), provider_event_id: `${source}:${id}`, stage, description, ...extra,
  });
  const words = 'Held at the post office for collection';
  // The summary read the scan as a missed delivery while its code had no stage; its words made it ready for pickup.
  const scan = at('held', 'swiss-post', 'ready_for_pickup', '2026-10-22T14:34:29+00:00', words);
  const sighting = at('seen', 'swiss-post', 'failed_attempt', '2026-10-22T15:54:00+00:00', words, { observed: true });
  const delivered = at('delivered', 'swiss-post', 'delivered', '2026-10-22T16:11:37+00:00', 'Delivered over counter');
  const shown = (tracking_events: object[]) => (withEventPlaces({ id: 'parcel', carrier: 'swiss-post', tracking_events }) as {
    tracking_events: Array<Record<string, unknown>>;
  }).tracking_events.map((row) => row.id);
  expect(shown([scan, sighting, delivered])).toEqual(['held', 'delivered']);
  // While it is the newest row its stage is the parcel's news; in the scan's stage the sync dates it, and the scan
  // must be no later, from its source, in its words. Peek's stand-in words name no scan.
  expect(shown([scan, sighting])).toEqual(['held', 'seen']);
  expect(shown([{ ...scan, stage: 'failed_attempt' }, sighting, delivered])).toEqual(['held', 'seen', 'delivered']);
  expect(shown([{ ...scan, occurred_at: '2026-10-22T15:55:00+00:00' }, sighting, delivered])).toEqual(['held', 'seen', 'delivered']);
  expect(shown([{ ...scan, provider_event_id: 'unknown:held' }, sighting, delivered])).toEqual(['held', 'seen', 'delivered']);
  expect(shown([{ ...scan, description: 'Arrived at the post office' }, sighting, delivered])).toEqual(['held', 'seen', 'delivered']);
  expect(shown([{ ...scan, description: 'Tracking update' }, { ...sighting, description: 'Tracking update' }, delivered]))
    .toEqual(['held', 'seen', 'delivered']);
  // The account's export keeps it.
  const exported = withoutEventSources({ id: 'parcel', tracking_events: [scan, sighting, delivered] }) as { tracking_events: unknown[] };
  expect(exported.tracking_events).toHaveLength(3);
});

describe('withoutEventSources', () => {
  it('keeps every stored row, Peek\'s own included, without its source', () => {
    const row = { id: 'parcel', tracking_events: [
      { ...event('added', null, '2026-09-27T10:00:00+00:00'), provider_event_id: 'app:pending' },
      { ...event('scan', null, '2026-09-25T08:00:00+00:00'), provider_event_id: 'swiss-post:scan' },
    ] };
    expect(withoutEventSources(row)).toEqual({ id: 'parcel', tracking_events: [
      event('added', null, '2026-09-27T10:00:00+00:00'),
      event('scan', null, '2026-09-25T08:00:00+00:00'),
    ] });
    expect(row.tracking_events[0]).toHaveProperty('provider_event_id');
    expect(withoutEventSources({ id: 'parcel' })).toEqual({ id: 'parcel' });
  });
});
