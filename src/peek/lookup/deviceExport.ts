import { downloadAccountExport } from '../../lib/account';
import { trackAction } from '../../lib/analytics';
import type { ParcelWithEvents } from '../../types';
import { deviceNoteFiles, deviceNoteFilesOf, loadDeviceNoteFiles, type DeviceNoteFile } from '../noteFiles';
import type { RecentParcel } from '../recents';
import { parcelLinkURL } from '../route';

export interface DeviceExport {
  exportedAt: string;
  mode: 'device';
  packages: Array<Omit<ParcelWithEvents, 'note' | 'files'> & { link: string; archivedAt?: string; note?: string; files?: ExportedFile[] }>;
}

/** A note's file as the export lists it: what it is, without its bytes. */
export interface ExportedFile { name: string; type: string; size: number; added: string }

/**
 * The parcels on this device as the apps write a guest's file: each as last shown, with its name, its link, when it
 * was archived, and its private note with its files' names. The owner keys stay out: a file passed around would let
 * anyone forget the lookups or keep them.
 */
export function deviceExport(
  recents: readonly RecentParcel[], now = new Date(), origin = window.location.origin, files: readonly DeviceNoteFile[] = [],
): DeviceExport {
  return {
    exportedAt: now.toISOString(),
    mode: 'device',
    packages: recents.map((recent) => {
      const kept = deviceNoteFilesOf(files, recent.id);
      // A link's answer never carries a private note: the device's own is added after.
      const shown: Omit<ParcelWithEvents, 'note' | 'files'> = recent.snapshot.parcel;
      return {
        ...shown,
        label: recent.name ?? recent.snapshot.parcel.label,
        link: parcelLinkURL(recent.id, origin),
        ...(recent.archivedAt ? { archivedAt: recent.archivedAt } : {}),
        ...(recent.note ? { note: recent.note } : {}),
        ...(kept.length ? { files: kept.map((file) => ({ name: file.name, type: file.type, size: file.size, added: file.created })) } : {}),
      };
    }),
  };
}

export async function downloadDeviceExport(recents: readonly RecentParcel[]): Promise<void> {
  await loadDeviceNoteFiles();
  downloadAccountExport(deviceExport(recents, new Date(), window.location.origin, deviceNoteFiles()));
  trackAction('account-export', 'success');
}
