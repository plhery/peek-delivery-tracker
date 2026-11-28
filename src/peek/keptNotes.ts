import type { ApiAuth } from '../lib/apiClient';
import { NoteFileError } from '../lib/parcelNote';
import { uploadPackageFile } from '../store/apiRepo';
import type { ParcelRepo } from '../types';
import {
  deviceNoteFileBlob, deviceNoteFiles, deviceNoteFilesOf, handOverDeviceNoteFiles, loadDeviceNoteFiles, removeDeviceNoteFile, sendKeptNoteFiles, type SentFiles,
} from './noteFiles';

/**
 * A guest's note goes with the parcel into the account: its words with the claim (`ParcelClaim.note`), its files
 * afterwards, one upload each to the account's parcel. A file leaves the device once it arrived, or once the service
 * says it never will; one that could not go waits for the next visit, a day or more when the account had no room for
 * it. Each waits for the account that kept its parcel.
 */
export function sendKeptFiles(auth: ApiAuth): Promise<SentFiles> {
  return sendKeptNoteFiles(async (packageId, blob, name) => {
    try {
      await uploadPackageFile(packageId, blob, name, auth);
    } catch (error) {
      // The parcel has left the account since, and its note with it, or the service will never take this file (its
      // size or type): it has nowhere to go.
      if (error instanceof NoteFileError && (error.status === 404 || error.status === 413 || error.status === 415)) return;
      throw error;
    }
  }, Date.now, auth.userId);
}

/** Once the account has the parcel, its files are marked for it, so forgetting the link keeps them, and sent. */
export async function handOverNoteFiles(linkId: string, packageId: string | undefined, auth: ApiAuth): Promise<void> {
  if (!packageId) return;
  if (await handOverDeviceNoteFiles(linkId, packageId, auth.userId)) void sendKeptFiles(auth);
}

/** The demo's counterpart: the note and its files move into the demo parcel, and leave the device. */
export async function moveNoteIntoDemo(repo: ParcelRepo, linkId: string, packageId: string, note: string | undefined): Promise<void> {
  if (note?.trim()) await repo.setNote?.(packageId, note).catch(() => undefined);
  await loadDeviceNoteFiles();
  for (const file of deviceNoteFilesOf(deviceNoteFiles(), linkId)) {
    const blob = await deviceNoteFileBlob(file.id);
    if (!blob || !repo.addFile) continue;
    try {
      await repo.addFile(packageId, blob, file.name);
      await removeDeviceNoteFile(file.id);
    } catch {
      // The demo parcel has no room left: the file stays with the link.
    }
  }
}

