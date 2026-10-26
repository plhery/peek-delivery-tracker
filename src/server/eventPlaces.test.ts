import { afterEach, describe, expect, it, vi } from 'vitest';
import * as observability from './observability';
import * as places from 'universal-parcel-scraper/places';
vi.mock('universal-parcel-scraper/places', { spy: true });
import { withEventPlaces, withoutEventSources } from './eventPlaces';

it('keeps addition-only query context out of client package data', () => {
  const row = { carrier_data: { add_recognition_pending: true, lookup_country_hint: 'CH' } };
  expect(withEventPlaces(row)).toEqual({ carrier_data: { lookup_country_hint: 'CH' } });
  expect(row.carrier_data.add_recognition_pending).toBe(true);
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
        // Unless the copy alone tells a problem: the parcel held as damaged goes over "on its way".
        { ...scan('moving', 'ecoscooting', '2026-10-23T09:00:12+00:00', 'Salamanca'), stage: 'in_transit' },
        { ...scan('held', 'yunexpress', '2026-10-23T09:00:12+00:00', null), stage: 'exception' },
      ],
    };
    const served = (withEventPlaces(row) as { tracking_events: Record<string, unknown>[] }).tracking_events;
    expect(served.map(({ id, relay_of }) => [id, relay_of])).toEqual([
      ['arrived', 'arrived-copy'], ['arrived-copy', undefined],
      ['sorted', undefined], ['sorted-copy', 'sorted'],
      ['out', undefined], ['out-copy', 'out'],
      ['left', undefined], ['left-copy', 'left'],
      ['moving', 'held'], ['held', undefined],
    ]);
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

it('names the day of a scan the carrier dated without a clock time, and serves none of its stored times', () => {
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
  expect(served.tracking_events.some((scan) => 'local_time' in scan || 'observed' in scan || 'time' in scan)).toBe(false);
});

it('serves a stage change seen without the carrier\'s words without Peek\'s stand-in, so the apps name the stage', () => {
  const row = { id: 'parcel', carrier: 'aliexpress', tracking_events: [
    { ...event('seen', null, '2026-10-20T22:25:08+00:00'), stage: 'registered', description: 'Tracking update', observed: true },
    { ...event('scan', null, '2026-10-20T20:00:00+00:00'), stage: 'in_transit', description: 'Tracking update' },
  ] };
  const served = withEventPlaces(row) as { tracking_events: Array<Record<string, unknown>> };
  expect(served.tracking_events.map((scan) => [scan.id, scan.description])).toEqual([['seen', ''], ['scan', 'Tracking update']]);
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
