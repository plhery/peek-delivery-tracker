import en from '../../shared/locales/en.json';
import { describe, expect, it } from 'vitest';
import type { ParcelWithEvents, SyncStatus } from '../types';
import { parcelDeliveryEstimate, localizedParcelCompletionDate, parcelDisplayStatus, parcelDisplayStatusKey, parcelPassedEstimate, parcelStatusLabel, shownCarrier } from './parcelStatus';
import { carrierInfo } from './carriers';
import { parcelAttention } from './parcelPriority';
import { translateMessage } from './messages';

function parcel(syncStatus: SyncStatus, stage: 'pending' | 'in_transit' = 'pending'): ParcelWithEvents {
  return {
    id: 'package-1',
    trackingNumber: '993412345699999980',
    label: 'Coffee',
    carrier: 'swiss-post',
    createdAt: '2026-07-16T10:00:00Z',
    syncStatus,
    events: [
      {
        id: 'event-1',
        parcelId: 'package-1',
        stage,
        description: 'Tracking added',
        occurredAt: '2026-07-16T10:00:00Z',
      },
    ],
  };
}

describe('parcelDisplayStatus', () => {
  it('shows the initial lookup separately from carrier announcement', () => {
    expect(parcelDisplayStatus(parcel('pending'))).toEqual({
      label: "Checking for updates",
      tone: 'ok',
      syncing: true,
    });
    expect(parcelDisplayStatus(parcel('syncing')).label).toBe("Checking for updates");
    expect(parcelDisplayStatus(parcel('waiting')).label).toBe("Waiting for the carrier");
  });

  it('makes first-sync failures and unsupported carriers explicit', () => {
    expect(parcelDisplayStatus({ ...parcel('error'), syncError: 'carrier:several_parcels' }).label).toBe("Update unavailable");
    expect(parcelDisplayStatus(parcel('unsupported')).label).toBe("Check tracking website");
  });

  it('names the carrier a parcel with no scans waits for, which has not listed it yet or could not be reached', () => {
    const t = (key: Parameters<typeof translateMessage>[1], values?: Record<string, string | number>) => translateMessage('en', key, values, en);
    for (const unreached of [parcel('waiting'), parcel('error'), { ...parcel('error'), syncError: 'carrier:transport' }]) {
      expect(parcelDisplayStatusKey(unreached)).toBe('status.waitingFor');
      expect(parcelStatusLabel(unreached, t)).toBe('Waiting for Swiss Post');
      expect(parcelDisplayStatus(unreached)).toEqual({ label: 'Waiting for the carrier', tone: 'ok', syncing: false });
      // Not a missed update.
      if (unreached.syncStatus === 'error') expect(parcelAttention(unreached)).toBeNull();
    }
    // What only its owner can fix, or a postal service not named yet, keeps its words.
    expect(parcelDisplayStatusKey({ ...parcel('error'), syncError: 'carrier:input_required' })).toBe('status.failed');
    expect(parcelAttention({ ...parcel('error'), syncError: 'carrier:input_required' })).toBe('sync_error');
    expect(parcelDisplayStatusKey({ ...parcel('waiting'), carrier: 'intl-post' })).toBe('status.unannounced');
    // Its first scan says more.
    expect(parcelDisplayStatusKey({ ...parcel('error', 'in_transit'), syncError: 'carrier:transport' })).toBe('stage.in_transit');
  });

  it('says a provider did not find the parcel with the postcode its owner gave, rather than asking for one', () => {
    for (const carrier of ['swiss-post', 'unknown'] as const) {
      const asked = { ...parcel('waiting'), carrier, dpdPostcode: '75011', providerInputNeeded: { provider: 'ParcelsApp', field: 'dpdPostcode' as const } };
      expect(parcelDisplayStatusKey(asked)).toBe('status.inputNeeded');
      expect(parcelDisplayStatus(asked)).toEqual({ label: en['status.inputNeeded'], tone: 'warn', syncing: false });
      const refused = { ...asked, providerInputNeeded: { ...asked.providerInputNeeded, refusedPostcode: '75011' } };
      expect(parcelDisplayStatusKey(refused)).toBe('status.postcodeRefused');
      expect(parcelDisplayStatus(refused)).toEqual({ label: en['status.postcodeRefused'], tone: 'warn', syncing: false });
      // Its first scan says more.
      expect(parcelDisplayStatusKey({ ...refused, events: parcel('waiting', 'in_transit').events })).toBe('stage.in_transit');
    }
  });

  it('says its own carrier did not find the parcel with the postcode its owner gave, rather than waiting for it', () => {
    const waiting = { ...parcel('waiting'), carrier: 'spring-gds' as const, trackingNumber: '3SABCD826405193', dpdPostcode: '1234 AB' };
    expect(parcelDisplayStatusKey(waiting)).toBe('status.waitingFor');
    for (const refused of [{ ...waiting, postcodeRefused: true }, { ...waiting, postcodeRefused: true, syncStatus: 'error' as const, syncError: 'carrier:transport' },
      { ...waiting, postcodeRefused: true, syncStatus: 'error' as const, syncError: 'carrier:input_required' }]) {
      expect(parcelDisplayStatusKey(refused)).toBe('status.postcodeRefused');
      expect(parcelDisplayStatus(refused)).toEqual({ label: en['status.postcodeRefused'], tone: 'warn', syncing: false });
      // Its first scan says more; a check under way says so.
      expect(parcelDisplayStatusKey({ ...refused, events: parcel('waiting', 'in_transit').events })).toBe('stage.in_transit');
      expect(parcelDisplayStatusKey({ ...refused, syncStatus: 'syncing' })).toBe('status.syncing');
    }
    // Without a postcode stored there is none to check.
    expect(parcelDisplayStatusKey({ ...waiting, dpdPostcode: undefined, postcodeRefused: true })).toBe('status.waitingFor');
  });

  it.each(['carrier:refused_number', 'carrier:placeholder_number', 'carrier:number_unknown'])('asks to check a number no carrier will answer for (%s)', (syncError) => {
    for (const carrier of ['swiss-post', 'unknown'] as const) {
      const refused = { ...parcel('error'), carrier, syncError };
      expect(parcelDisplayStatusKey(refused)).toBe('status.checkNumber');
      expect(en[parcelDisplayStatusKey(refused)]).toBe(parcelDisplayStatus(refused).label);
    }
    // Scans already shown keep their stage.
    expect(parcelDisplayStatusKey({ ...parcel('error', 'in_transit'), syncError })).toBe('stage.in_transit');
  });

  it('checks universal-backed carriers automatically before their first result', () => {
    for (const carrier of ['fedex', 'asendia'] as const) {
      const saved = { ...parcel('pending'), carrier };
      expect(parcelDisplayStatus(saved)).toEqual({
        label: "Checking for updates", tone: 'ok', syncing: true,
      });
      expect(parcelDisplayStatusKey(saved)).toBe('status.syncing');
      saved.events[0].stage = 'in_transit';
      expect(parcelDisplayStatusKey(saved)).toBe('stage.in_transit');
    }
  });

  it('checks unidentified carriers automatically', () => {
    for (const carrier of ['unknown', 'intl-post'] as const) {
      expect(parcelDisplayStatusKey({ ...parcel('pending'), carrier })).toBe('status.syncing');
    }
  });

  it('waits for the first scan of the carrier a number points to, until a carrier answers', () => {
    const t = (key: Parameters<typeof translateMessage>[1], values?: Record<string, string | number>) => translateMessage('en', key, values, en);
    const likely: ParcelWithEvents = { ...parcel('waiting'), carrier: 'unknown', trackingNumber: '06080000000002',
      likelyCarrier: { carrier: 'dpd', since: '2026-07-16T10:00:00Z' } };
    expect(parcelStatusLabel(likely, t)).toBe('Waiting for DPD Switzerland’s first scan');
    expect(shownCarrier(likely, carrierInfo('unknown'), t)).toMatchObject({ id: 'unknown', name: 'Probably DPD Switzerland' });
    // A shape nobody leads keeps the words of a number no carrier knows yet.
    const shared = { ...likely, likelyCarrier: undefined };
    expect(parcelStatusLabel(shared, t)).toBe('No tracking history yet');
    expect(shownCarrier(shared, carrierInfo('unknown'), t).name).toBe('Unknown carrier');
    // Filed under the carrier that answered, the parcel shows it.
    const filed = { ...likely, carrier: 'seur' as const };
    expect(parcelStatusLabel(filed, t)).toBe('Waiting for SEUR');
    expect(shownCarrier(filed, carrierInfo('seur'), t).name).toBe('SEUR');
  });

  it('keeps a real carrier stage visible during later sync attempts or errors', () => {
    expect(parcelDisplayStatus(parcel('syncing', 'in_transit')).label).toBe('In transit');
    expect(parcelDisplayStatus(parcel('error', 'in_transit')).label).toBe('In transit');
  });

  it('keeps an older carrier event visible when tracking was added later', () => {
    const tracked = parcel('waiting');
    tracked.events.unshift({
      id: 'event-carrier',
      parcelId: tracked.id,
      stage: 'in_transit',
      description: 'In transit',
      occurredAt: '2026-07-15T10:00:00Z',
    });

    expect(parcelDisplayStatus(tracked).label).toBe('In transit');
  });

  it('folds a final event date into one concise status line', () => {
    const delivered = parcel('ok', 'in_transit');
    delivered.events[0].stage = 'delivered';
    // Noon on 16 July where the test runs: the 16th in every zone.
    delivered.events[0].occurredAt = new Date(2026, 6, 16, 12).toISOString();
    expect(localizedParcelCompletionDate(delivered, 'de-CH', (key) => en[key], new Date(2026, 8, 9).getTime())).toBe('Do 16. Juli');
  });
});

describe('useful delivery estimates', () => {
  const now = new Date(2026, 8, 7, 12).getTime();
  it.each(['delivered', 'returned', 'failed_attempt', 'ready_for_pickup', 'exception'] as const)('hides estimates after %s', (stage) => {
    const saved = parcel('ok');
    saved.events[0].stage = stage;
    saved.expectedDelivery = '2026-09-07';
    expect(parcelDeliveryEstimate(saved, now)).toBeNull();
  });
  it('hides the estimate while a returned parcel is carried back', () => {
    const saved = parcel('ok', 'in_transit');
    saved.events[0].stage = 'returned';
    saved.events.push({ id: 'event-2', parcelId: 'package-1', stage: 'in_transit', description: 'Processed', occurredAt: '2026-07-17T10:00:00Z' });
    saved.expectedDelivery = '2026-09-08';
    expect(parcelDeliveryEstimate(saved, now)).toBeNull();
    expect(parcelDisplayStatus(saved).label).toBe(en['stage.returned']);
  });
  it('keeps only today\'s hours while out for delivery, and hides stale estimates', () => {
    const saved = parcel('ok');
    saved.events[0].stage = 'out_for_delivery';
    saved.expectedDelivery = '2026-09-07 14:00–16:00';
    expect(parcelDeliveryEstimate(saved, now)).toBe(saved.expectedDelivery);
    saved.expectedDeliveryFrom = new Date(2026, 8, 7, 14).toISOString();
    saved.expectedDelivery = new Date(2026, 8, 7, 16).toISOString();
    expect(parcelDeliveryEstimate(saved, now)).toBe(saved.expectedDelivery);
    // Out for delivery says today already: a day, or the last day of a window, would only repeat or contradict it.
    for (const [from, to] of [
      [undefined, '2026-09-07'], [undefined, '2026-09-08'], [undefined, '2026-09-08 09:00–12:00'],
      ['2026-09-06', '2026-09-08'], ['2026-09-06', new Date(2026, 8, 7, 18).toISOString()],
    ]) {
      saved.expectedDeliveryFrom = from;
      saved.expectedDelivery = to;
      expect(parcelDeliveryEstimate(saved, now)).toBeNull();
    }
    saved.expectedDeliveryFrom = undefined;
    saved.events[0].stage = 'in_transit';
    saved.expectedDelivery = '2026-09-07';
    expect(parcelDeliveryEstimate(saved, now)).toBe(saved.expectedDelivery);
    for (const value of ['2026-09-06', 'invalid']) {
      saved.expectedDelivery = value;
      expect(parcelDeliveryEstimate(saved, now)).toBeNull();
    }
  });
});

describe('an expected day that passed', () => {
  const now = new Date(2026, 10, 20, 12).getTime();
  it('is the day the server names, once it has passed on this device too', () => {
    const saved = parcel('ok', 'in_transit');
    expect(parcelPassedEstimate(saved, now)).toBeNull();
    saved.expectedDeliveryPassed = '2026-11-17';
    expect(parcelPassedEstimate(saved, now)).toEqual(new Date(2026, 10, 17));
    // Served by a clock that runs ahead of this device's: the day is still today here.
    saved.expectedDeliveryPassed = '2026-11-20';
    expect(parcelPassedEstimate(saved, now)).toBeNull();
    saved.expectedDeliveryPassed = 'invalid';
    expect(parcelPassedEstimate(saved, now)).toBeNull();
  });
  it('is said for a week after the day, even on a copy the device kept that no check updates', () => {
    const saved = { ...parcel('ok', 'in_transit'), expectedDeliveryPassed: '2026-11-13' };
    expect(parcelPassedEstimate(saved, now)).toEqual(new Date(2026, 10, 13));
    saved.expectedDeliveryPassed = '2026-11-12';
    expect(parcelPassedEstimate(saved, now)).toBeNull();
  });
  it('gives way to an estimate the page shows, and goes once the parcel is archived', () => {
    const saved = { ...parcel('ok', 'in_transit'), expectedDeliveryPassed: '2026-11-17' };
    expect(parcelPassedEstimate({ ...saved, expectedDelivery: '2026-11-23' }, now)).toBeNull();
    expect(parcelPassedEstimate({ ...saved, archivedAt: '2026-11-19T10:00:00Z' }, now)).toBeNull();
  });
});

describe('relative completion dates', () => {
  it.each(['delivered', 'returned'] as const)('uses nearby day labels for %s without a time', (stage) => {
    const saved = parcel('ok');
    saved.events[0].stage = stage;
    const now = new Date(2026, 8, 9, 12).getTime();
    for (const [day, expected] of [[8, 'yesterday'], [9, 'today'], [10, 'tomorrow'], [12, 'Sat 12 Sep']] as const) {
      saved.events[0].occurredAt = new Date(2026, 8, day, 23, 55).toISOString();
      expect(localizedParcelCompletionDate(saved, 'en-CH', (key) => en[key], now)).toBe(expected);
    }
    saved.events[0].occurredAt = 'invalid';
    expect(localizedParcelCompletionDate(saved, 'en-CH', (key) => en[key], now)).toBeNull();
  });

  it('dates a completion the carrier dated by its day alone by that day', () => {
    const saved = parcel('ok');
    saved.events[0] = { ...saved.events[0], stage: 'delivered', occurredAt: new Date(2026, 8, 8, 23).toISOString(), day: '2026-09-09' };
    expect(localizedParcelCompletionDate(saved, 'en-CH', (key) => en[key], new Date(2026, 8, 9, 12).getTime())).toBe('today');
  });

  it('does not show completion dates for active parcels', () => {
    expect(localizedParcelCompletionDate(parcel('ok', 'in_transit'), 'en-CH', (key) => en[key])).toBeNull();
  });
});
