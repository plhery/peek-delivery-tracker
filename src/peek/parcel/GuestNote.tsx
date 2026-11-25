import { useEffect, useMemo, useRef, useState, type RefObject } from 'react';
import { ParcelNote, type NoteFile, type NoteStore } from '../../components/note/ParcelNote';
import {
  addDeviceNoteFile, deviceNoteFileBlob, deviceNoteFilesOf, removeDeviceNoteFile, useAllDeviceNoteFiles, type DeviceNoteFile,
} from '../noteFiles';
import { noteParcel } from '../recents';

/** Addresses this page makes for the device's files: one per file, let go with the file or the page. */
function usePageAddresses(files: readonly DeviceNoteFile[]): Record<string, string> {
  const made = useRef(new Map<string, string>());
  const [urls, setUrls] = useState<Record<string, string>>({});
  const ids = files.map((file) => file.id).join(' ');
  useEffect(() => {
    let alive = true;
    const wanted = new Set(ids.split(' ').filter(Boolean));
    const addresses = made.current;
    for (const [id, url] of addresses) {
      if (wanted.has(id)) continue;
      URL.revokeObjectURL(url);
      addresses.delete(id);
    }
    void Promise.all([...wanted].filter((id) => !addresses.has(id)).map(async (id) => [id, await deviceNoteFileBlob(id)] as const)).then((found) => {
      if (!alive) return;
      for (const [id, blob] of found) if (blob && !addresses.has(id)) addresses.set(id, URL.createObjectURL(blob));
      setUrls(Object.fromEntries(addresses));
    });
    return () => { alive = false; };
  }, [ids]);
  useEffect(() => {
    const addresses = made.current;
    return () => {
      for (const url of addresses.values()) URL.revokeObjectURL(url);
      addresses.clear();
    };
  }, []);
  return urls;
}

/**
 * A guest's note on a parcel they looked up: the same card and sheet as an account's, its words kept beside the link
 * on this device and its files in this browser's IndexedDB. Signing in and keeping the parcel moves both to the account.
 */
export function GuestNote({ linkId, name, text, editing, onEditing, fallbackFocus }: {
  linkId: string;
  name: string;
  text: string;
  editing: boolean;
  onEditing: (editing: boolean) => void;
  fallbackFocus?: RefObject<HTMLElement | null>;
}) {
  const all = useAllDeviceNoteFiles();
  const kept = useMemo(() => deviceNoteFilesOf(all, linkId), [all, linkId]);
  const urls = usePageAddresses(kept);
  const files = useMemo<NoteFile[]>(() => kept.flatMap((file) => urls[file.id] ? [{
    id: file.id, name: file.name, contentType: file.type, size: file.size,
    url: urls[file.id], thumbnailUrl: file.type === 'application/pdf' ? null : urls[file.id], downloadUrl: urls[file.id],
  }] : []), [kept, urls]);
  const store = useMemo<NoteStore>(() => ({
    saveText: async (next) => { noteParcel(linkId, next); },
    addFile: async (file, onProgress) => {
      await addDeviceNoteFile(linkId, file);
      onProgress(file.blob.size);
    },
    removeFile: (id) => removeDeviceNoteFile(id),
  }), [linkId]);
  return <ParcelNote text={text} files={files} store={store} name={name} lock="note.guestWeb"
    editing={editing} onEditing={onEditing} fallbackFocus={fallbackFocus} />;
}
