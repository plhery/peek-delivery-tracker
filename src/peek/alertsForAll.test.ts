import { afterEach, beforeEach, describe, expect, it, vi } from 'vitest';
import type { TrackingEvent } from '../types';
import { turnOnAlert } from './alerts';
import { alertParcels, alertsForAll, ALERTS_FOR_ALL_STORAGE_KEY, forgetAlertTries, setAlertsForAll } from './alertsForAll';
import { forgetAllLinkNotes, noteLink } from './deviceNotes';
import type { RecentParcel } from './recents';

vi.mock('./alerts', () => ({ alertSupport: vi.fn(() => 'ready'), turnOnAlert: vi.fn(async () => undefined) }));

const ALERTS = { available: true, vapidPublicKey: 'BEl6demo' };

function recent(id: string, stage: TrackingEvent['stage'] = 'in_transit'): RecentParcel {
  const event: TrackingEvent = { id: `${id}-1`, parcelId: id, stage, description: '', occurredAt: '2026-11-18T10:00:00.000Z' };
  return { id, key: `key-${id}`, name: null, snapshot: { link: { alerts: ALERTS }, parcel: { events: [event] } } } as unknown as RecentParcel;
}

describe('alerts for every parcel', () => {
  beforeEach(() => { vi.stubGlobal('Notification', { permission: 'granted' }); });
  afterEach(() => {
    setAlertsForAll(null);
    forgetAllLinkNotes();
    forgetAlertTries();
    vi.mocked(turnOnAlert).mockClear();
    vi.unstubAllGlobals();
  });

  it('is this browser’s choice, with what the alerts announce', () => {
    expect(alertsForAll()).toBeNull();
    setAlertsForAll('delivery');
    expect(window.localStorage.getItem(ALERTS_FOR_ALL_STORAGE_KEY)).toBe('delivery');
    expect(alertsForAll()).toBe('delivery');
    window.localStorage.setItem(ALERTS_FOR_ALL_STORAGE_KEY, 'everything');
    expect(alertsForAll()).toBe('delivery');
  });

  it('turns alerts on for the parcels still on their way, passing over one turned off and one already on', async () => {
    noteLink('MutedParcAB2', { muted: true });
    noteLink('BeepParcAB23', { alert: { preset: 'all', endpoint: 'https://push.example/1' } });
    const parcels = [recent('WayParcAB234'), recent('MutedParcAB2'), recent('BeepParcAB23'), recent('DoneParcAB23', 'delivered')];
    await alertParcels(parcels, 'fr');
    expect(turnOnAlert).not.toHaveBeenCalled();

    setAlertsForAll('important');
    await alertParcels(parcels, 'fr');
    expect(turnOnAlert).toHaveBeenCalledTimes(1);
    expect(turnOnAlert).toHaveBeenCalledWith({ linkId: 'WayParcAB234', key: 'key-WayParcAB234', alerts: ALERTS, preset: 'important', locale: 'fr' });
    // A parcel is tried once a page, whatever the server answered.
    await alertParcels(parcels, 'fr');
    expect(turnOnAlert).toHaveBeenCalledTimes(1);
  });

  it('asks the browser nothing: without its yes, no parcel is turned on', async () => {
    vi.stubGlobal('Notification', { permission: 'default' });
    setAlertsForAll('all');
    await alertParcels([recent('WayParcAB234')], 'en');
    expect(turnOnAlert).not.toHaveBeenCalled();
  });
});
