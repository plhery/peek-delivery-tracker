import { describe, expect, it } from 'vitest';
import { lateScans, relayCopies, relayRepeats } from './relayCopies';

/** A stored scan: its id, the carrier that stored it, its stage, when it happened and when a check stored it. */
const scan = (id: string, source: string, stage: string, occurred_at: string, created_at = '2026-09-10T08:00:00Z') => ({
  id, stage, occurred_at, created_at, provider_event_id: `${source}:${id}`,
});

/** A parcel DHL handed over to Swiss Post. */
const handedOver = (tracking_events: object[]) => ({
  carrier: 'dhl', carrier_data: { original_carrier: 'dhl', active_tracking_carrier: 'swiss-post' }, tracking_events,
});

describe('relayCopies', () => {
  it('pairs each scan the earlier carrier tells again with the new carrier\'s, at the same stage within a minute', () => {
    expect(relayCopies(handedOver([
      // DHL drops the seconds of Swiss Post's scans.
      scan('arrived', 'swiss-post', 'in_transit', '2026-09-09T14:16:51Z'),
      scan('arrived-copy', 'dhl', 'in_transit', '2026-09-09T14:16:00Z'),
      scan('customs', 'swiss-post', 'customs', '2026-09-09T14:18:51Z'),
      scan('customs-copy', 'dhl', 'customs', '2026-09-09T14:18:00Z'),
      // Chronopost tells DPD's scans after them, and a whole minute counts.
      scan('round', 'swiss-post', 'out_for_delivery', '2026-09-10T06:15:00Z'),
      scan('round-copy', 'dhl', 'out_for_delivery', '2026-09-10T06:16:00Z'),
      scan('app', 'app', 'pending', '2026-09-09T14:16:30Z'),
    ]))).toEqual(new Map([['arrived-copy', 'arrived'], ['customs-copy', 'customs'], ['round-copy', 'round']]));
  });

  it('leaves a scan of another stage, or more than a minute away, on its own', () => {
    expect(relayCopies(handedOver([
      scan('customs', 'swiss-post', 'customs', '2026-09-09T13:15:11Z'),
      scan('exchange', 'dhl', 'in_transit', '2026-09-09T13:15:04Z'),
      scan('sorted', 'swiss-post', 'in_transit', '2026-09-09T15:42:29Z'),
      scan('later', 'dhl', 'in_transit', '2026-09-09T15:43:30Z'),
      // Nor are two scans of the new carrier, or of the earlier one, a pair.
      scan('sorted-again', 'swiss-post', 'in_transit', '2026-09-09T15:42:20Z'),
      scan('later-again', 'dhl', 'in_transit', '2026-09-09T15:43:31Z'),
    ]))).toEqual(new Map());
  });

  it('pairs a hand-over both carriers tell at the same second, each in its own stage', () => {
    const placed = (id: string, source: string, stage: string, at: string, location?: string) => ({ ...scan(id, source, stage, at), location });
    const parcel = (tracking_events: object[]) => ({
      carrier: 'four-px', carrier_data: { original_carrier: 'four-px', active_tracking_carrier: 'dpd' }, tracking_events,
    });
    expect(relayCopies(parcel([
      // 4PX's "Parcel handed to dpd" and DPD's acceptance; a post office's posting beside a row placed in its country.
      placed('handed', 'four-px', 'in_transit', '2026-10-20T11:08:41Z'),
      placed('accepted', 'dpd', 'accepted', '2026-10-20T11:08:41Z'),
      placed('received', 'four-px', 'in_transit', '2026-10-21T09:20:28Z', 'PL'),
      placed('posted', 'dpd', 'accepted', '2026-10-21T09:20:28Z', 'Post office Lublin'),
      // On a minute's first second, the places share a word.
      placed('office', 'four-px', 'in_transit', '2026-10-22T09:56:00Z', 'Covington, KY, US'),
      placed('office-scan', 'dpd', 'ready_for_pickup', '2026-10-22T09:56:00Z', 'COVINGTON, KY 41011'),
    ]))).toEqual(new Map([['handed', 'accepted'], ['received', 'posted'], ['office', 'office-scan']]));
    // Seconds apart, at places that share no word, or on a minute's first second with no word in common (a carrier
    // keeping minutes gives every scan that second), the two are scans of their own: an arrival measured a minute
    // before the next post registers the data, an order created three seconds after a sort elsewhere.
    expect(relayCopies(parcel([
      placed('measured', 'four-px', 'in_transit', '2026-10-19T12:20:14Z', 'Nancheng'),
      placed('registered', 'dpd', 'registered', '2026-10-19T12:19:15Z'),
      placed('sorted', 'four-px', 'in_transit', '2026-10-16T14:58:49Z'),
      placed('created', 'dpd', 'registered', '2026-10-16T14:58:52Z'),
      placed('depot', 'four-px', 'in_transit', '2026-10-17T02:59:28Z', 'Krostitz'),
      placed('elsewhere', 'dpd', 'exception', '2026-10-17T02:59:28Z', 'Leipzig'),
      placed('minute', 'four-px', 'in_transit', '2026-10-18T08:20:00Z'),
      placed('minute-scan', 'dpd', 'accepted', '2026-10-18T08:20:00Z', 'Lublin'),
    ]))).toEqual(new Map());
  });

  it('pairs a row of a carrier keeping minutes with the other carrier\'s row of that minute, in any stage', () => {
    const placed = (id: string, source: string, stage: string, at: string, location?: string) => ({ ...scan(id, source, stage, at), location });
    const parcel = (tracking_events: object[]) => ({
      carrier: 'poczta-polska', carrier_data: { original_carrier: 'poczta-polska', active_tracking_carrier: 'dhl' }, tracking_events,
    });
    expect(relayCopies(parcel([
      // A post office's posting, and the new carrier's first row stamped to the minute in that country, or at a code.
      placed('posted', 'poczta-polska', 'accepted', '2026-10-23T08:20:59Z', 'Post office Lodz'),
      placed('origin', 'dhl', 'in_transit', '2026-10-23T08:20:00Z', 'Poland'),
      placed('posted-again', 'poczta-polska', 'accepted', '2026-10-23T09:50:34Z', 'Post office Lodz'),
      placed('announced', 'dhl', 'registered', '2026-10-23T09:50:00Z', 'PL12345, PL'),
      // The earlier carrier keeping minutes: its failed delivery beside the new carrier's, filed in transit.
      placed('missed', 'poczta-polska', 'failed_attempt', '2026-10-25T11:28:00Z'),
      placed('not-possible', 'dhl', 'in_transit', '2026-10-25T11:28:40Z', 'Germany'),
    ]))).toEqual(new Map([['posted', 'origin'], ['posted-again', 'announced'], ['missed', 'not-possible']]));
    // Two rows on a minute's first second (two carriers keeping minutes), the next minute's row, and places whose names
    // conflict stay two.
    expect(relayCopies(parcel([
      placed('minutes', 'poczta-polska', 'accepted', '2026-10-23T08:20:00Z'),
      placed('minutes-too', 'dhl', 'in_transit', '2026-10-23T08:20:00Z'),
      placed('before', 'poczta-polska', 'accepted', '2026-10-23T09:49:59Z'),
      placed('next-minute', 'dhl', 'registered', '2026-10-23T09:50:00Z'),
      placed('here', 'poczta-polska', 'accepted', '2026-10-23T10:20:59Z', 'Post office Lodz'),
      placed('there', 'dhl', 'in_transit', '2026-10-23T10:20:00Z', 'Warszawa'),
    ]))).toEqual(new Map());
    // A pair of the same stage comes first: the customs office's two steps of one minute stay apart.
    expect(relayCopies(parcel([
      placed('sent', 'unknown', 'customs', '2026-10-10T11:37:00Z', 'Langley'),
      placed('returned', 'poczta-polska', 'in_transit', '2026-10-10T11:37:01Z'),
      placed('received', 'dhl', 'in_transit', '2026-10-10T11:37:53Z', 'Langley'),
    ]))).toEqual(new Map([['returned', 'received']]));
  });

  it('makes the closest pairs first, and one scan pairs once', () => {
    // Two Swiss Post scans of one minute that DHL tells once stay two rows.
    expect(relayCopies(handedOver([
      scan('first', 'swiss-post', 'in_transit', '2026-09-09T15:40:38Z'),
      scan('second', 'swiss-post', 'in_transit', '2026-09-09T15:40:53Z'),
      scan('copy', 'dhl', 'in_transit', '2026-09-09T15:40:00Z'),
    ]))).toEqual(new Map([['copy', 'first']]));
    // Three scans of a minute against two: each takes its closest free partner.
    expect(relayCopies(handedOver([
      scan('a', 'dhl', 'in_transit', '2026-09-09T07:48:00Z'),
      scan('b', 'dhl', 'in_transit', '2026-09-09T07:48:00Z'),
      scan('c', 'dhl', 'in_transit', '2026-09-09T07:48:00Z'),
      scan('arrival', 'swiss-post', 'in_transit', '2026-09-09T07:47:00Z'),
      scan('border', 'swiss-post', 'in_transit', '2026-09-09T07:48:00Z'),
    ]))).toEqual(new Map([['a', 'border'], ['b', 'arrival']]));
  });

  it('pairs the new post\'s record of a step the earlier one sent it under that post\'s latest scan of the stage', () => {
    // Slovenská pošta stamps Poczta Polska's posting and departure when their messages arrive, placed in Poland alone.
    const placed = (id: string, source: string, stage: string, at: string, location: string) => ({ ...scan(id, source, stage, at), location });
    const rows = [
      placed('posted', 'poczta-polska', 'accepted', '2026-10-23T07:44:21Z', 'Post office Łódź'),
      placed('posted-message', 'slovenska-posta', 'accepted', '2026-10-23T08:45:12Z', 'Poland'),
      placed('transport', 'poczta-polska', 'in_transit', '2026-10-23T23:37:48Z', 'Transshipment'),
      placed('departed', 'poczta-polska', 'in_transit', '2026-10-24T00:40:19Z', 'Sorting centre Warszawa'),
      placed('departed-message', 'slovenska-posta', 'in_transit', '2026-10-24T02:05:01Z', 'Poland'),
    ];
    const parcel = (tracking_events: object[]) => ({
      carrier: 'poczta-polska', carrier_data: { original_carrier: 'poczta-polska', active_tracking_carrier: 'slovenska-posta' }, tracking_events,
    });
    expect(relayCopies(parcel(rows))).toEqual(new Map([['posted-message', 'posted'], ['departed-message', 'departed']]));
    // The new post's own scans, a town, a step before the earlier post's latest of its stage (the arrival at the office
    // of exchange, ahead of the departure), one over three hours on or whole hours away (one record on two clocks), and
    // a provider's row stay on their own.
    expect(relayCopies(parcel([...rows.slice(0, 4),
      placed('home', 'slovenska-posta', 'in_transit', '2026-10-24T02:05:01Z', 'Slovakia'),
      placed('town', 'slovenska-posta', 'in_transit', '2026-10-24T02:06:01Z', 'Warszawa, Poland'),
      placed('exchange', 'slovenska-posta', 'in_transit', '2026-10-24T00:05:01Z', 'Poland'),
      placed('provider', 'unknown', 'in_transit', '2026-10-24T02:05:01Z', 'Poland'),
    ]))).toEqual(new Map([['posted-message', 'posted']]));
    for (const at of ['2026-10-24T03:41:00Z', '2026-10-24T01:40:19Z', '2026-10-24T02:40:00Z']) {
      expect(relayCopies(parcel([...rows.slice(0, 4), placed('message', 'slovenska-posta', 'in_transit', at, 'Poland')])))
        .toEqual(new Map([['posted-message', 'posted']]));
    }
    // A copy within a minute pairs first.
    expect(relayCopies(parcel([...rows, placed('copy', 'slovenska-posta', 'in_transit', '2026-10-24T00:40:00Z', 'Poland')])))
      .toEqual(new Map([['posted-message', 'posted'], ['departed', 'copy']]));
  });

  it('pairs with a universal provider\'s row of the new leg, and only in a parcel that was handed over', () => {
    const rows = [
      scan('in-france', 'unknown', 'in_transit', '2026-10-06T05:18:36Z'),
      scan('exchange', 'india-post', 'in_transit', '2026-10-06T05:18:00Z'),
    ];
    const indiaPost = (data: object) => ({ carrier: 'india-post', carrier_data: data, tracking_events: rows });
    expect(relayCopies(indiaPost({ original_carrier: 'india-post', active_tracking_carrier: 'la-poste' })))
      .toEqual(new Map([['exchange', 'in-france']]));
    // Two legs merged into one parcel name the earlier carrier without an active one.
    expect(relayCopies({ ...indiaPost({ original_carrier: 'india-post' }), carrier: 'la-poste' }))
      .toEqual(new Map([['exchange', 'in-france']]));
    expect(relayCopies(indiaPost({}))).toEqual(new Map());
    expect(relayCopies(indiaPost({ original_carrier: 'india-post', active_tracking_carrier: 'india-post' }))).toEqual(new Map());
    expect(relayCopies({ carrier_data: { original_carrier: 'india-post' } })).toEqual(new Map());
  });
});

describe('relayRepeats', () => {
  it('names the row of each pair that reached the parcel second, and the copy of a pair stored together', () => {
    expect(relayRepeats(handedOver([
      // DHL told it first; Swiss Post's history came with the handover.
      scan('arrived-copy', 'dhl', 'in_transit', '2026-09-09T14:16:00Z', '2026-09-09T15:30:01Z'),
      scan('arrived', 'swiss-post', 'in_transit', '2026-09-09T14:16:51Z', '2026-09-10T08:00:05Z'),
      // Swiss Post told it first; DHL's copy came with a later check.
      scan('depot', 'swiss-post', 'in_transit', '2026-09-10T04:44:36Z', '2026-09-10T05:00:00Z'),
      scan('depot-copy', 'dhl', 'in_transit', '2026-09-10T04:44:00Z', '2026-09-10T06:00:03Z'),
      // One check stored both.
      scan('delivered', 'swiss-post', 'delivered', '2026-09-10T10:08:49Z', '2026-09-10T10:24:02Z'),
      scan('delivered-copy', 'dhl', 'delivered', '2026-09-10T10:08:00Z', '2026-09-10T10:24:02Z'),
      scan('alone', 'swiss-post', 'in_transit', '2026-09-09T20:00:00Z', '2026-09-10T08:00:05Z'),
    ]))).toEqual(new Set(['arrived', 'depot-copy', 'delivered-copy']));
  });

  it('names the routine scan beside a copy that alone tells a problem, however the two came', () => {
    // Chronopost holds the parcel as damaged in the second DPD tells it "on its way".
    const parcel = (tracking_events: object[]) => ({
      carrier: 'chronopost', carrier_data: { original_carrier: 'chronopost', active_tracking_carrier: 'dpd-de' }, tracking_events,
    });
    expect(relayRepeats(parcel([
      scan('held', 'chronopost', 'exception', '2026-10-26T11:31:22Z', '2026-10-26T11:51:00Z'),
      scan('on-its-way', 'dpd-de', 'in_transit', '2026-10-26T11:31:22Z', '2026-10-26T11:51:00Z'),
      // DPD's scan came first; Chronopost's problem is news when it comes.
      scan('missed', 'chronopost', 'failed_attempt', '2026-10-27T09:12:40Z', '2026-10-27T11:00:00Z'),
      scan('round', 'dpd-de', 'in_transit', '2026-10-27T09:12:40Z', '2026-10-27T10:00:00Z'),
      // A problem both tell stays the copy's to repeat.
      scan('damaged', 'chronopost', 'exception', '2026-10-28T08:00:12Z', '2026-10-28T09:00:00Z'),
      scan('damaged-scan', 'dpd-de', 'exception', '2026-10-28T08:00:12Z', '2026-10-28T09:00:00Z'),
    ]))).toEqual(new Set(['on-its-way', 'round', 'damaged']));
  });
});

describe('lateScans', () => {
  it('names the earlier carrier\'s scans that reached the parcel after a later one was stored', () => {
    // A parcel Chronopost handed over to DPD Germany, then told a scan of its own late.
    const chronopost = (data: object, tracking_events: object[]) => ({ carrier: 'chronopost', carrier_data: data, tracking_events });
    const handoff = { original_carrier: 'chronopost', active_tracking_carrier: 'dpd-de' };
    const rows = [
      // The handoff's check stored both histories, older scans with newer ones.
      scan('sorted', 'chronopost', 'in_transit', '2026-10-08T09:00:00Z', '2026-10-08T14:30:00Z'),
      scan('received', 'dpd-de', 'in_transit', '2026-10-08T14:24:00Z', '2026-10-08T14:30:00Z'),
      scan('late', 'chronopost', 'in_transit', '2026-10-08T14:12:00Z', '2026-10-08T15:25:00Z'),
      // Newer than anything stored before it, and Peek's own rows date no scan.
      scan('added', 'app', 'pending', '2026-10-08T14:50:00Z', '2026-10-08T14:50:00Z'),
      scan('newer', 'chronopost', 'in_transit', '2026-10-08T14:40:00Z', '2026-10-08T15:25:00Z'),
      // The new carrier's own scans are never late.
      scan('depot', 'dpd-de', 'in_transit', '2026-10-08T14:20:00Z', '2026-10-08T16:25:00Z'),
    ];
    expect(lateScans(chronopost(handoff, rows))).toEqual(new Set(['late']));
    expect(lateScans(chronopost({}, rows))).toEqual(new Set());
    expect(lateScans({ carrier_data: handoff })).toEqual(new Set());
  });
});
