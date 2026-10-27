import { describe, expect, it } from 'vitest';
import { directHistory, providerCopies, providerCopyScans } from './providerCopies';

const scan = (id: string, source: string, occurredAt: string, extra: Record<string, unknown> = {}) => ({
  id, stage: 'in_transit', description: `Scan ${id}`, location: null, occurred_at: occurredAt,
  provider_event_id: `${source}:${id}`, ...extra,
});
const named = { universal_provider: 'ParcelsApp' };
const confirmed = { routing: { confirmed_carrier: 'posti' } };

describe('providerCopies', () => {
  it('leaves out a named provider row copying a confirmed carrier\'s scan, reworded, a zone away or minutes off', () => {
    const row = {
      carrier_data: confirmed,
      tracking_events: [
        scan('c1', 'posti', '2026-10-01T11:09:18Z'),
        scan('c2', 'posti', '2026-10-01T16:48:10Z', { stage: 'registered' }),
        scan('c3', 'posti', '2026-10-02T20:06:44Z', { stage: 'delivered' }),
        // An hour off to the minute, in its own words.
        scan('u1', 'unknown', '2026-10-01T12:09:00Z', { ...named, description: 'Your package is on its way' }),
        // Two minutes off, in the scan's stage.
        scan('u2', 'unknown', '2026-10-01T16:50:00Z', { ...named, stage: 'registered' }),
        // At the scan's minute, read as another stage.
        scan('u3', 'unknown', '2026-10-02T20:06:00Z', { ...named, stage: 'pending' }),
      ],
    };
    expect([...providerCopies(row)].sort()).toEqual(['u1', 'u2', 'u3']);
  });

  it('leaves out a provider\'s copy within an hour in the scan\'s words, alone or followed by its place, and only that', () => {
    const departed = { description: 'Departed  facility', location: 'NORTH HUB' };
    const row = {
      carrier_data: confirmed,
      tracking_events: [
        scan('c1', 'posti', '2026-10-23T06:07:00Z', departed),
        // The time the carrier first gave the departure, with the place after its words.
        scan('u1', 'unknown', '2026-10-23T05:39:00Z', { ...named, description: 'Departed Facility, North Hub', location: 'North Hub' }),
        // Later in the hour, in its words.
        scan('u2', 'unknown', '2026-10-23T07:00:00Z', { ...named, description: 'departed facility' }),
        // Other words, another stage, or over an hour away.
        scan('u3', 'unknown', '2026-10-23T05:40:00Z', { ...named, description: 'Departed facility early' }),
        scan('u4', 'unknown', '2026-10-23T05:41:00Z', { ...named, ...departed, stage: 'customs' }),
        scan('u5', 'unknown', '2026-10-23T04:59:00Z', { ...named, ...departed }),
      ],
    };
    expect(providerCopyScans(row)).toEqual(new Map([['u1', new Set(['c1'])], ['u2', new Set(['c1'])]]));
  });

  it('leaves out a provider\'s announcement within an hour of the carrier\'s, in any words, and no other step in other words', () => {
    const row = {
      carrier_data: confirmed,
      tracking_events: [
        scan('c1', 'posti', '2026-10-26T12:44:00Z', { stage: 'registered', description: 'Votre envoi sera bientôt transmis' }),
        scan('c2', 'posti', '2026-10-27T09:10:00Z', { description: 'Trip arrived' }),
        // The announcement in the provider's language.
        scan('u1', 'unknown', '2026-10-26T12:10:00Z', { ...named, stage: 'registered', description: 'Registered' }),
        // Over an hour away, another step in other words, or the next step of the carrier's in its stage.
        scan('u2', 'unknown', '2026-10-26T11:43:00Z', { ...named, stage: 'registered', description: 'Registered' }),
        scan('u3', 'unknown', '2026-10-26T13:20:00Z', { ...named, stage: 'in_transit', description: 'Accepted' }),
        scan('u4', 'unknown', '2026-10-27T08:51:00Z', { ...named, description: 'Bag added to trip' }),
      ],
    };
    expect(providerCopyScans(row)).toEqual(new Map([['u1', new Set(['c1'])]]));
  });

  it('keeps a provider\'s own step, the carrier\'s rows and every row without a confirmed carrier', () => {
    const events = [
      scan('c1', 'posti', '2026-10-01T11:09:18Z'),
      scan('c2', 'posti', '2026-10-03T09:00:00Z'),
      // Another leg's departure, in no stage or time of the carrier's scans.
      scan('u1', 'unknown', '2026-10-02T03:15:00Z', named),
      // An hour and a half away in the same stage, and three hours away in another.
      scan('u2', 'unknown', '2026-10-01T12:39:00Z', named),
      scan('u3', 'unknown', '2026-10-01T14:09:00Z', { ...named, stage: 'customs' }),
      // Peek's own row and a sighting at a scan's minute.
      scan('a1', 'app', '2026-10-01T11:09:00Z'),
      scan('o1', 'unknown', '2026-10-01T11:09:00Z', { ...named, observed: true }),
    ];
    expect(providerCopies({ carrier_data: confirmed, tracking_events: events })).toEqual(new Set());
    const copy = scan('u4', 'unknown', '2026-10-01T11:09:00Z', named);
    expect(providerCopies({ carrier_data: confirmed, tracking_events: [...events, copy] })).toEqual(new Set(['u4']));
    expect(providerCopies({ carrier_data: {}, tracking_events: [...events, copy] })).toEqual(new Set());
    expect(providerCopies({ carrier_data: { routing: { confirmed_carrier: 'unknown' } }, tracking_events: [...events, copy] }))
      .toEqual(new Set());
  });

  it('tells an unnamed provider row from a carrier\'s by the rows the carrier\'s answers held', () => {
    const events = [
      scan('c1', 'posti', '2026-10-01T11:09:18Z'),
      // A row the carrier's scan took over, and one stored before rows named their provider.
      scan('u1', 'unknown', '2026-10-01T15:09:18Z'),
      scan('u2', 'unknown', '2026-10-01T12:09:00Z'),
      scan('c2', 'posti', '2026-10-02T09:00:00Z'),
    ];
    // Without the carrier's history, neither can be told from the carrier's.
    expect(providerCopies({ carrier_data: confirmed, tracking_events: events })).toEqual(new Set());
    const history = { from: '2026-10-01T11:09:18.000Z', to: '2026-10-02T09:00:00.000Z', held: ['unknown:u1'] };
    expect(providerCopies({ carrier_data: { ...confirmed, direct_history: history }, tracking_events: events }))
      .toEqual(new Set(['u2']));
    // Outside the time the carrier's answers covered, an unnamed row stays.
    const before = scan('u3', 'unknown', '2026-10-01T10:09:00Z');
    expect(providerCopies({ carrier_data: { ...confirmed, direct_history: history }, tracking_events: [...events, before] }))
      .toEqual(new Set(['u2']));
  });

  it('leaves out a provider\'s other rows of the minute it read a zone away, and only those', () => {
    // Swiss Post dates a London office's departure on Swiss time; ParcelsApp tells it on London's, twice.
    const events = [
      scan('c1', 'swiss-post', '2026-10-16T15:27:00Z', { description: 'Arrival at the processing point' }),
      scan('c2', 'swiss-post', '2026-10-16T15:57:00Z', { description: 'The consignment has left the border point' }),
      scan('u1', 'unknown', '2026-10-16T16:57:00Z', { ...named, description: 'Departure from office of exchange' }),
      scan('u2', 'unknown', '2026-10-16T16:57:00Z', { ...named, stage: 'pending', description: 'Item has passed a checkpoint' }),
      // Another provider's row of that minute, and the provider's row of another minute, stay.
      scan('u3', 'unknown', '2026-10-16T16:57:00Z', { universal_provider: '17TRACK', stage: 'customs' }),
      scan('u4', 'unknown', '2026-10-16T16:58:00Z', { ...named, stage: 'customs' }),
    ];
    const carrierData = { routing: { confirmed_carrier: 'landmark-global' } };
    expect([...providerCopies({ carrier_data: carrierData, tracking_events: events })].sort()).toEqual(['u1', 'u2']);
    // A copy also close to a carrier's scan keeps the carrier's clock: the provider's other rows of its minute stay.
    const near = scan('c3', 'swiss-post', '2026-10-16T16:50:00Z');
    expect([...providerCopies({ carrier_data: carrierData, tracking_events: [...events, near] })].sort()).toEqual(['u1']);
  });

  it('leaves out a provider\'s twin of its copy, in its words, place and stage whole hours from it, and its rows of that minute', () => {
    // ParcelsApp tells a posting at the carrier's minute, and again on the posting office's wall clock read as UTC.
    const events = [
      scan('c1', 'correos-chile', '2026-10-08T00:12:00Z', { description: 'Dispatched', location: 'Origin' }),
      scan('u1', 'unknown', '2026-10-08T00:12:00Z', { ...named, stage: 'accepted', description: 'Posting', location: 'Office 1' }),
      scan('u2', 'unknown', '2026-10-07T21:12:00Z', { ...named, stage: 'accepted', description: 'Posting', location: 'Office 1' }),
      scan('u3', 'unknown', '2026-10-07T21:12:00Z', { ...named, stage: 'accepted', description: 'Item posted', location: 'Country' }),
      // 90 minutes away, in other words, at another place or in another stage, a provider's row is no twin.
      scan('u4', 'unknown', '2026-10-07T22:42:00Z', { ...named, stage: 'accepted', description: 'Posting', location: 'Office 1' }),
      scan('u5', 'unknown', '2026-10-07T19:12:00Z', { ...named, stage: 'accepted', description: 'Posting', location: 'Office 2' }),
      scan('u6', 'unknown', '2026-10-07T18:12:00Z', { ...named, stage: 'customs', description: 'Posting', location: 'Office 1' }),
      // Another provider's row of the twin's minute stays.
      scan('u7', 'unknown', '2026-10-07T21:12:00Z', { universal_provider: '17TRACK', stage: 'accepted', description: 'Posted' }),
    ];
    const row = { carrier_data: { routing: { confirmed_carrier: 'correos-chile' } }, tracking_events: events };
    expect([...providerCopies(row)].sort()).toEqual(['u1', 'u2', 'u3']);
    // Each repeats the carrier's scan its copy repeats.
    expect(providerCopyScans(row)).toEqual(new Map([['u1', new Set(['c1'])], ['u2', new Set(['c1'])], ['u3', new Set(['c1'])]]));
  });

  it('leaves out a provider\'s row, in any stage, by the shift its copy at the same place shows from the carrier\'s scans', () => {
    // 17TRACK tells the departure three hours early, and the export-unit scan before it on the same clock, as information received.
    const events = [
      scan('c1', 'correos-chile', '2026-10-08T14:18:00Z', { description: 'Ready to leave', location: 'Airport' }),
      scan('c2', 'correos-chile', '2026-10-08T23:28:00Z', { description: 'Left the country', location: 'Airport' }),
      scan('u1', 'unknown', '2026-10-08T20:28:00Z', { universal_provider: '17TRACK', description: 'In transfer', location: 'CL' }),
      scan('u2', 'unknown', '2026-10-08T11:18:00Z', { universal_provider: '17TRACK', stage: 'registered', location: 'CL' }),
      // Another shift, at that place or another, or another provider is no reading of that clock.
      scan('u3', 'unknown', '2026-10-08T12:18:00Z', { universal_provider: '17TRACK', stage: 'registered', location: 'CL' }),
      scan('u4', 'unknown', '2026-10-08T11:19:00Z', { universal_provider: '17TRACK', stage: 'registered', location: 'BR' }),
      scan('u5', 'unknown', '2026-10-08T11:20:00Z', { ...named, stage: 'registered', location: 'CL' }),
    ];
    const row = { carrier_data: { routing: { confirmed_carrier: 'correos-chile' } }, tracking_events: events };
    expect([...providerCopies(row)].sort()).toEqual(['u1', 'u2']);
    // A copy also close to a carrier's scan shows no shift.
    const near = scan('c3', 'correos-chile', '2026-10-08T20:30:00Z');
    expect([...providerCopies({ ...row, tracking_events: [...events, near] })].sort()).toEqual(['u1']);
  });

  it('leaves out a provider\'s copy of a scan the carrier dated by its day, showing the scan\'s wall clock in its words', () => {
    // YunExpress gives the scan a wall clock in no zone; the provider dates it on Shanghai's clock.
    const day = scan('c2', 'yunexpress', '2026-10-11T00:00:01Z', { local_time: '2026-10-11T18:20:00', description: 'Sorted' });
    const events = [scan('c1', 'yunexpress', '2026-10-11T00:00:00Z', { time: '2026-10-11T08:00:00+08:00' }), day,
      scan('u1', 'unknown', '2026-10-11T10:20:00Z', { ...named, description: 'Sorted' }),
      // Other words, or a time that is no zone's reading of the clock, stay.
      scan('u2', 'unknown', '2026-10-11T10:20:00Z', { ...named, description: 'Departed' }),
      scan('u3', 'unknown', '2026-10-11T10:27:00Z', { ...named, description: 'Sorted' })];
    expect(providerCopies({ carrier_data: { routing: { confirmed_carrier: 'yunexpress' } }, tracking_events: events }))
      .toEqual(new Set(['u1']));
  });

  it('leaves out a provider\'s copy whole hours from a carrier\'s scan to its second, in any stage and words', () => {
    // ParcelsApp reads a Japanese scan an hour off, as another stage, with a Chinese line added.
    const events = [
      scan('c1', 'ecms-express', '2026-10-21T11:46:56Z', { stage: 'accepted', description: 'Arrived at origin facility.', location: 'NRT' }),
      scan('c2', 'ecms-express', '2026-10-22T15:00:00Z', { stage: 'customs' }),
      scan('u1', 'unknown', '2026-10-21T12:46:56Z', { ...named, description: 'Arrived at origin facility. 已到达', location: 'Narita' }),
      // Three hours away at another second, or a minute's first second hours from a scan on one, is no proof of a
      // reading on another clock: a provider's own step (a delivery office's notice, the out-for-delivery round) stays.
      scan('u2', 'unknown', '2026-10-21T14:46:13Z', named),
      scan('u3', 'unknown', '2026-10-22T17:00:00Z', named),
      scan('u4', 'unknown', '2026-10-21T13:16:56Z', named),
    ];
    const row = { carrier_data: { routing: { confirmed_carrier: 'ecms-express' } }, tracking_events: events };
    expect(providerCopyScans(row)).toEqual(new Map([['u1', new Set(['c1'])]]));
  });

  it('leaves out a provider\'s copy of a scan dated by its day at that scan\'s second or at its wall clock read as UTC', () => {
    const events = [
      scan('c1', 'paack', '2026-10-07T00:00:00Z', { stage: 'registered', description: 'Created', local_time: '2026-10-07T12:09:07' }),
      scan('c2', 'mailamericas', '2026-10-14T00:00:00Z', { description: 'In transit to the airport', local_time: '2026-10-14T11:30:00' }),
      scan('c3', 'mrw', '2026-10-13T00:00:00Z', { stage: 'registered', description: 'Pendiente de recoger', local_time: '2026-10-13T15:14:00' }),
      // An hour off at the wall clock's second, in its own words and stage.
      scan('u1', 'unknown', '2026-10-07T11:09:07Z', { stage: 'accepted', description: 'Shipment registered' }),
      // The wall clock read as UTC, in the scan's stage and other words.
      scan('u2', 'unknown', '2026-10-14T11:30:00Z', { description: 'On the way to the airport' }),
      // The scan's words a zone away, with no stage of its own.
      scan('u3', 'unknown', '2026-10-13T13:14:00Z', { stage: 'pending', description: 'Pendiente de recoger' }),
      // The delivery company's own notice three seconds before, a step of another stage at the wall clock read as
      // UTC, and a sorting scan an hour after a wall clock with no seconds, stay.
      scan('u4', 'unknown', '2026-10-07T12:09:04Z', { stage: 'registered', description: 'We are expecting your parcel' }),
      scan('u5', 'unknown', '2026-10-14T11:30:00Z', { stage: 'customs', description: 'Customs' }),
      scan('u6', 'unknown', '2026-10-14T12:30:16Z', { description: 'Received by sorting center' }),
    ].map((event) => ({ ...event, ...named }));
    expect([...providerCopies({ carrier_data: confirmed, tracking_events: events })].sort()).toEqual(['u1', 'u2', 'u3']);
  });

  it('leaves out a provider\'s row, at any place, by the shift its copies show', () => {
    // ParcelsApp reads Pitney Bowes's customs scans an hour late; its clearance has no place, and the carrier's
    // scan of it is read as another stage. It reads the depot's sorting scan on the same clock.
    const events = [
      scan('c1', 'pitney-bowes', '2026-10-21T07:55:00Z', { stage: 'customs', description: 'Customs Clearance', location: 'IT' }),
      scan('c2', 'pitney-bowes', '2026-10-22T10:42:00Z', { description: 'B2C ClearanceStatus4', location: 'NL' }),
      scan('c3', 'pitney-bowes', '2026-10-23T09:00:00Z', { description: 'Sorted', location: 'NL' }),
      scan('u1', 'unknown', '2026-10-21T08:55:00Z', { ...named, stage: 'customs', description: 'Customs Import submitted' }),
      scan('u2', 'unknown', '2026-10-22T11:42:00Z', { ...named, stage: 'customs', description: 'Customs Import cleared' }),
      scan('u5', 'unknown', '2026-10-23T10:00:00Z', { ...named, stage: 'customs', location: 'Venlo' }),
      // Another shift, with no place or at one, or another provider's row at that shift, stays: a step of its own.
      scan('u3', 'unknown', '2026-10-22T12:42:00Z', { ...named, stage: 'customs' }),
      scan('u6', 'unknown', '2026-10-23T11:00:00Z', { ...named, stage: 'customs', location: 'Venlo' }),
      scan('u4', 'unknown', '2026-10-22T11:42:00Z', { universal_provider: '17TRACK', stage: 'customs' }),
    ];
    const row = { carrier_data: { routing: { confirmed_carrier: 'pitney-bowes' } }, tracking_events: events };
    expect(providerCopyScans(row)).toEqual(new Map([['u1', new Set(['c1'])], ['u2', new Set(['c2'])], ['u5', new Set(['c3'])]]));
  });
});

describe('directHistory', () => {
  it('keeps the time a carrier\'s dated scans cover and the universal rows they hold, with the earlier answers\'', () => {
    const scans = [
      scan('c1', 'posti', '2026-10-01T11:09:18Z'),
      scan('c2', 'posti', '2026-10-02T09:00:00Z'),
      scan('c3', 'posti', '2026-10-02T10:00:00Z', { raw_data: { observed_without_provider_timestamp: true } }),
    ];
    const persisted = [{ ...scans[0], provider_event_id: 'unknown:u1' }, scans[1], scans[2]];
    const previous = { from: '2026-09-30T08:00:00.000Z', to: '2026-10-01T11:09:18.000Z', held: ['unknown:u0', 'unknown:gone'] };
    const stored = [{ provider_event_id: 'unknown:u0' }, { provider_event_id: 'unknown:u1' }];
    expect(directHistory(scans, persisted, previous, stored)).toEqual({
      from: '2026-09-30T08:00:00.000Z', to: '2026-10-02T09:00:00.000Z', held: ['unknown:u0', 'unknown:u1'],
    });
    expect(directHistory(scans, persisted, undefined, stored)).toEqual({
      from: '2026-10-01T11:09:18.000Z', to: '2026-10-02T09:00:00.000Z', held: ['unknown:u1'],
    });
  });

  it('is none for a check that stores no carrier\'s dated scan', () => {
    const universal = [scan('u1', 'unknown', '2026-10-01T11:09:18Z', named), scan('a1', 'app', '2026-10-01T10:00:00Z')];
    expect(directHistory(universal, universal, undefined, [])).toBeNull();
    expect(directHistory([], [], { from: '2026-10-01T00:00:00Z', to: '2026-10-02T00:00:00Z', held: [] }, [])).toBeNull();
  });
});
