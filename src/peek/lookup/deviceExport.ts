import { downloadAccountExport } from '../../lib/account';
import { trackAction } from '../../lib/analytics';
import type { ParcelWithEvents } from '../../types';
import type { RecentParcel } from '../recents';
import { parcelLinkURL } from '../route';

export interface DeviceExport {
  exportedAt: string;
  mode: 'device';
  packages: Array<ParcelWithEvents & { link: string; archivedAt?: string }>;
}

/**
 * The parcels on this device as the apps write a guest's file: each as last shown, with its name, its link and when it
 * was archived. The owner keys stay out: a file passed around would let anyone forget the lookups or keep them.
 */
export function deviceExport(recents: readonly RecentParcel[], now = new Date(), origin = window.location.origin): DeviceExport {
  return {
    exportedAt: now.toISOString(),
    mode: 'device',
    packages: recents.map((recent) => ({
      ...recent.snapshot.parcel,
      label: recent.name ?? recent.snapshot.parcel.label,
      link: parcelLinkURL(recent.id, origin),
      ...(recent.archivedAt ? { archivedAt: recent.archivedAt } : {}),
    })),
  };
}

export function downloadDeviceExport(recents: readonly RecentParcel[]): void {
  downloadAccountExport(deviceExport(recents));
  trackAction('account-export', 'success');
}
