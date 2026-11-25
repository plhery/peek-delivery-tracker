import {
  createContext,
  useCallback,
  useContext,
  useEffect,
  useMemo,
  useRef,
  useState,
  type ReactNode,
} from 'react';
import {
  RefreshTimeoutError,
  type NewParcelInput,
  type ParcelCarrierInput,
  type ParcelRepo,
  type ParcelWithEvents,
} from '../types';
import { ApiAuthenticationError } from '../lib/apiClient';
import { withoutFile } from '../lib/parcelNote';
import { quietRetryDelay, readMaintenance, ServerDownError, type MaintenanceNotice } from '../lib/maintenance';
import { useI18n } from '../i18n';

/** What a refresh can change for the reader: the timeline, the status line and the delivery date. */
function trackingState(parcel: ParcelWithEvents | undefined): string | undefined {
  return parcel && JSON.stringify([
    parcel.lastStatusText,
    parcel.expectedDelivery,
    parcel.events.map((event) => [event.id, event.stage, event.description, event.location, event.occurredAt, event.day]),
  ]);
}

/** Whether refreshed parcels bring new tracking; a parcel that replaced another is compared with it. */
function trackingChanged(before: ParcelWithEvents[], after: ParcelWithEvents[]): boolean {
  const earlier = new Map(before.map((parcel) => [parcel.id, parcel]));
  return after.some((parcel) => trackingState(parcel)
    !== trackingState(earlier.get(parcel.id) ?? earlier.get(parcel.originalParcelId ?? '')));
}

interface ParcelsState {
  parcels: ParcelWithEvents[];
  loading: boolean;
  refreshing: boolean;
  error: string | null;
  authenticationRequired: boolean;
  usingCachedData: boolean;
  /**
   * The server is down for maintenance: the list keeps what it shows, says so calmly instead of an error, and asks
   * again by itself until the server answers.
   */
  down: MaintenanceNotice | null;
  /** When the list shown was last read from the server, or null. */
  readAt: string | null;
  mode: ParcelRepo['mode'];
  addParcel: (input: NewParcelInput) => Promise<ParcelWithEvents>;
  renameParcel: (id: string, label: string) => Promise<ParcelWithEvents>;
  /** Saves a parcel's private note; null or blank text deletes it. */
  saveParcelNote: (id: string, note: string | null, options?: { keepalive?: boolean }) => Promise<ParcelWithEvents>;
  /** Adds a photo or PDF to a parcel's note, telling how many bytes have gone. */
  addParcelFile: (id: string, file: Blob, name: string, onProgress?: (sent: number) => void, signal?: AbortSignal) => Promise<ParcelWithEvents>;
  /** Removes one of a parcel's note's files; `keepalive` as the page goes away. */
  removeParcelFile: (id: string, fileId: string, options?: { keepalive?: boolean }) => Promise<void>;
  changeParcelCarrier: (id: string, input: ParcelCarrierInput) => Promise<ParcelWithEvents>;
  setParcelNotificationsMuted: (id: string, muted: boolean) => Promise<void>;
  setParcelEmailMuted: (id: string, muted: boolean) => Promise<void>;
  /** The parcel was opened: its mark for new updates goes at once, here and on the account's other devices. */
  markParcelSeen: (id: string) => Promise<void>;
  removeParcel: (id: string) => Promise<void>;
  restoreParcel: (id: string) => Promise<void>;
  deleteParcel: (id: string) => Promise<void>;
  /** Resolves true when the check brought new tracking. */
  refresh: () => Promise<boolean>;
  /** Resolves true when the check brought new tracking for this parcel. */
  refreshParcel: (id: string) => Promise<boolean>;
  retryLoad: () => Promise<void>;
  resetDemoData: () => Promise<void>;
}

const ParcelsContext = createContext<ParcelsState | null>(null);

/** The later of two moments, either of which may be missing. */
function later(a: string | undefined, b: string): string {
  return a && Date.parse(a) >= Date.parse(b) ? a : b;
}

/**
 * The list as read, each parcel seen as late as this device marked it (`marks`, by id); a mark the read has caught up
 * with, or whose parcel the list no longer holds, goes.
 */
function seenAsHere(list: ParcelWithEvents[], marks: Map<string, string>): ParcelWithEvents[] {
  if (!marks.size) return list;
  const ids = new Set(list.map((parcel) => parcel.id));
  for (const id of marks.keys()) if (!ids.has(id)) marks.delete(id);
  return list.map((parcel) => {
    const mark = marks.get(parcel.id);
    if (!mark) return parcel;
    if (parcel.seenAt && Date.parse(parcel.seenAt) >= Date.parse(mark)) {
      marks.delete(parcel.id);
      return parcel;
    }
    return { ...parcel, seenAt: mark };
  });
}

export function ParcelsProvider({
  repo,
  children,
}: {
  repo: ParcelRepo;
  children: ReactNode;
}) {
  // The last saved collection appears at once; the first request then replaces it.
  const [savedParcels] = useState(() => repo.cachedList?.() ?? null);
  const [parcels, setParcels] = useState<ParcelWithEvents[]>(savedParcels ?? []);
  const [loading, setLoading] = useState(savedParcels === null);
  const [refreshing, setRefreshing] = useState(false);
  const [error, setError] = useState<string | null>(null);
  const [authenticationRequired, setAuthenticationRequired] = useState(false);
  const [usingCachedData, setUsingCachedData] = useState(false);
  const [down, setDown] = useState<MaintenanceNotice | null>(null);
  const [readAt, setReadAt] = useState(() => repo.cachedAt?.() ?? null);
  // While the server is down: how many quiet retries ran, the next one, and whether /maintenance.json was asked in
  // this outage, which `outages` counts.
  const downRetries = useRef(0);
  const downTimer = useRef<ReturnType<typeof setTimeout> | undefined>(undefined);
  const askedEdge = useRef(false);
  const outages = useRef(0);
  const reloadRef = useRef<() => Promise<void>>(async () => undefined);
  const mounted = useRef(true);
  const parcelsRef = useRef<ParcelWithEvents[]>(savedParcels ?? []);
  const revision = useRef(0);
  const loadSequence = useRef(0);
  // The seenAt this device marked parcels with, until a read of the list brings one as late (`seenAsHere`).
  const seenHere = useRef(new Map<string, string>());
  // Only the demo writes its own parcels, so only it reloads with the language.
  const { locale } = useI18n();
  const demoLanguage = repo.setLanguage ? locale : null;

  useEffect(() => {
    parcelsRef.current = parcels;
  }, [parcels]);

  useEffect(() => {
    mounted.current = true;
    return () => {
      mounted.current = false;
      clearTimeout(downTimer.current);
    };
  }, []);

  /** The server is down: no error, and a quiet retry, as soon as it asked for and at least once a minute. */
  const noteDown = useCallback((reason: ServerDownError) => {
    setError(null);
    setAuthenticationRequired(false);
    setDown((current) => reason.maintenance ?? current ?? { planned: false, backAround: null });
    // The edge's own answer says whether the maintenance was announced; a database's or a proxy's does not.
    if (!reason.maintenance && !askedEdge.current) {
      askedEdge.current = true;
      const outage = outages.current;
      void readMaintenance().then((notice) => {
        if (notice && mounted.current && outages.current === outage) setDown(notice);
      });
    }
    clearTimeout(downTimer.current);
    downTimer.current = setTimeout(() => void reloadRef.current(), quietRetryDelay(downRetries.current++, reason.retryAfterMs));
  }, []);

  /** The server answered: the notice goes, and with it the retries. */
  const noteUp = useCallback(() => {
    clearTimeout(downTimer.current);
    downRetries.current = 0;
    askedEdge.current = false;
    outages.current += 1;
    setDown(null);
    setReadAt(repo.cachedAt?.() ?? new Date().toISOString());
  }, [repo]);

  const rememberError = useCallback((reason: unknown) => {
    if (!mounted.current || (reason instanceof DOMException && reason.name === 'AbortError')) return;
    if (reason instanceof ServerDownError) {
      noteDown(reason);
      return;
    }
    setError(reason instanceof Error ? reason.message : String(reason));
    setAuthenticationRequired(reason instanceof ApiAuthenticationError);
  }, [noteDown]);

  const reload = useCallback(async () => {
    const sequence = ++loadSequence.current;
    const startedRevision = revision.current;
    const isCurrent = () => mounted.current && sequence === loadSequence.current && startedRevision === revision.current;
    try {
      const list = await repo.list();
      if (isCurrent()) {
        setParcels(seenAsHere(list, seenHere.current));
        setError(null);
        setAuthenticationRequired(false);
        setUsingCachedData(false);
        noteUp();
      }
    } catch (e) {
      if (e instanceof DOMException && e.name === 'AbortError') return;
      if (!isCurrent()) return;
      const cached = repo.cachedList?.() ?? null;
      if (isCurrent()) {
        if (parcelsRef.current.length === 0 && cached?.length) setParcels(cached);
        setUsingCachedData(parcelsRef.current.length > 0 || Boolean(cached?.length));
      }
      if (isCurrent()) rememberError(e);
    } finally {
      if (mounted.current && sequence === loadSequence.current) setLoading(false);
    }
  }, [repo, rememberError, noteUp]);
  useEffect(() => { reloadRef.current = reload; }, [reload]);

  const retryLoad = useCallback(async () => {
    if (parcelsRef.current.length === 0) setLoading(true);
    await reload();
  }, [reload]);

  const resetDemoData = useCallback(async () => {
    if (repo.mode !== 'demo' || !repo.resetDemo) throw new Error('Demo reset is unavailable');
    const list = await repo.resetDemo();
    revision.current += 1;
    if (mounted.current) {
      setParcels(list);
      setError(null);
      setAuthenticationRequired(false);
      setUsingCachedData(false);
    }
  }, [repo]);

  useEffect(() => {
    // Fetching from and subscribing to the repository is the external system
    // synchronization this provider owns.
    if (demoLanguage) repo.setLanguage?.(demoLanguage);
    const unsubscribe = repo.subscribe?.(reload);
    void reload();
    return unsubscribe;
  }, [repo, reload, demoLanguage]);

  // Show the service's answer immediately; the collection reload runs behind it.
  const addParcel = useCallback(
    async (input: NewParcelInput) => {
      try {
        const parcel = await repo.add(input);
        revision.current += 1;
        if (mounted.current) {
          setParcels((current) => [...current.filter((candidate) => candidate.id !== parcel.id), parcel]);
          setError(null);
          setAuthenticationRequired(false);
        }
        void reload();
        return parcel;
      } catch (error) {
        // The add sheet says why it failed; only a lapsed sign-in concerns the whole list.
        if (error instanceof ApiAuthenticationError) rememberError(error);
        throw error;
      }
    },
    [repo, reload, rememberError],
  );

  const removeParcel = useCallback(
    async (id: string) => {
      try {
        await repo.remove(id);
        revision.current += 1;
        if (mounted.current) {
          const archivedAt = new Date().toISOString();
          setParcels((current) => current.map((parcel) =>
            parcel.id === id && !parcel.archivedAt ? { ...parcel, archivedAt } : parcel));
          setError(null);
          setAuthenticationRequired(false);
        }
        void reload();
      } catch (error) {
        rememberError(error);
        throw error;
      }
    },
    [repo, reload, rememberError],
  );

  const renameParcel = useCallback(
    async (id: string, label: string) => {
      try {
        const renamed = await repo.rename(id, label);
        revision.current += 1;
        if (mounted.current) {
          setParcels((current) =>
            current.map((parcel) => parcel.id === renamed.id ? renamed : parcel),
          );
          setError(null);
          setAuthenticationRequired(false);
        }
        return renamed;
      } catch (error) {
        rememberError(error);
        throw error;
      }
    },
    [repo, rememberError],
  );

  /** A note's changes report their own errors in its sheet: only a lost sign-in or a server down concerns the list. */
  const rememberNoteError = useCallback((error: unknown) => {
    if (error instanceof ApiAuthenticationError || error instanceof ServerDownError) rememberError(error);
  }, [rememberError]);

  const replaceParcel = useCallback((updated: ParcelWithEvents) => {
    revision.current += 1;
    if (mounted.current) setParcels((current) => current.map((parcel) => parcel.id === updated.id ? updated : parcel));
    return updated;
  }, []);

  const saveParcelNote = useCallback(async (id: string, note: string | null, options?: { keepalive?: boolean }) => {
    try {
      if (!repo.setNote) throw new Error('Notes are unavailable');
      return replaceParcel(await repo.setNote(id, note, options));
    } catch (error) {
      rememberNoteError(error);
      throw error;
    }
  }, [repo, replaceParcel, rememberNoteError]);

  const addParcelFile = useCallback(async (id: string, file: Blob, name: string, onProgress?: (sent: number) => void, signal?: AbortSignal) => {
    try {
      if (!repo.addFile) throw new Error('Files are unavailable');
      return replaceParcel(await repo.addFile(id, file, name, onProgress, signal));
    } catch (error) {
      rememberNoteError(error);
      throw error;
    }
  }, [repo, replaceParcel, rememberNoteError]);

  const removeParcelFile = useCallback(async (id: string, fileId: string, options?: { keepalive?: boolean }) => {
    try {
      if (!repo.removeFile) throw new Error('Files are unavailable');
      await repo.removeFile(id, fileId, options);
      revision.current += 1;
      if (mounted.current) setParcels((current) => current.map((parcel) => parcel.id === id ? withoutFile(parcel, fileId) : parcel));
    } catch (error) {
      rememberNoteError(error);
      throw error;
    }
  }, [repo, rememberNoteError]);

  const changeParcelCarrier = useCallback(
    async (id: string, input: ParcelCarrierInput) => {
      try {
        if (!repo.changeCarrier) throw new Error('Changing parcel carriers is unavailable');
        const updated = await repo.changeCarrier(id, input);
        revision.current += 1;
        if (mounted.current) {
          setParcels((current) =>
            current.map((parcel) => parcel.id === updated.id ? updated : parcel),
          );
          setError(null);
          setAuthenticationRequired(false);
        }
        return updated;
      } catch (error) {
        rememberError(error);
        throw error;
      }
    },
    [repo, rememberError],
  );

  const restoreParcel = useCallback(async (id: string) => {
    try {
      if (!repo.restore) throw new Error('Restoring archived parcels is unavailable');
      const restored = await repo.restore(id);
      revision.current += 1;
      if (mounted.current) {
        setParcels((current) =>
          current.map((parcel) => parcel.id === restored.id ? restored : parcel),
        );
        setError(null);
        setAuthenticationRequired(false);
      }
    } catch (error) {
      rememberError(error);
      throw error;
    }
  }, [repo, rememberError]);

  const deleteParcel = useCallback(async (id: string) => {
    try {
      if (!repo.deletePermanently) {
        throw new Error('Permanently deleting parcels is unavailable');
      }
      await repo.deletePermanently(id);
      revision.current += 1;
      if (mounted.current) {
        setParcels((current) => current.filter((parcel) => parcel.id !== id));
        setError(null);
        setAuthenticationRequired(false);
      }
    } catch (error) {
      rememberError(error);
      throw error;
    }
  }, [repo, rememberError]);

  const setParcelNotificationsMuted = useCallback(async (id: string, muted: boolean) => {
    try {
      if (!repo.setNotificationsMuted) {
        throw new Error('Parcel notification settings are unavailable');
      }
      const updated = await repo.setNotificationsMuted(id, muted);
      revision.current += 1;
      if (mounted.current) {
        setParcels((current) =>
          current.map((parcel) => parcel.id === updated.id ? updated : parcel),
        );
        setError(null);
        setAuthenticationRequired(false);
      }
    } catch (error) {
      rememberError(error);
      throw error;
    }
  }, [repo, rememberError]);

  const setParcelEmailMuted = useCallback(async (id: string, muted: boolean) => {
    try {
      if (!repo.setEmailMuted) {
        throw new Error('Parcel email settings are unavailable');
      }
      const updated = await repo.setEmailMuted(id, muted);
      revision.current += 1;
      if (mounted.current) {
        setParcels((current) =>
          current.map((parcel) => parcel.id === updated.id ? updated : parcel),
        );
        setError(null);
        setAuthenticationRequired(false);
      }
    } catch (error) {
      rememberError(error);
      throw error;
    }
  }, [repo, rememberError]);

  // Opening a parcel is no action to report on: a mark that could not be stored comes back with the next read. Unlike
  // the list's other changes, a mark lets a read of the list in flight land, so the open page shows the scans it brings,
  // seen as late as this device marked the parcel.
  const markParcelSeen = useCallback(async (id: string) => {
    if (!repo.markSeen) return;
    const seen = (seenAt: string) => {
      seenHere.current.set(id, seenAt);
      if (mounted.current) setParcels((current) => current.map((parcel) => parcel.id === id ? { ...parcel, seenAt: later(parcel.seenAt, seenAt) } : parcel));
    };
    const opened = parcelsRef.current.find((parcel) => parcel.id === id);
    // What the reader is looking at is seen as of its news, whatever this device's clock says, and so the server stores it.
    // An empty email-added parcel has only its creation to acknowledge. A scan that races this opening stays new.
    const news = opened?.newsAt ?? (opened?.seenAt && Date.parse(opened.seenAt) === 0 ? opened.createdAt : undefined);
    if (news) seen(news);
    try {
      seen(await repo.markSeen(id, news));
    } catch {
      // Offline, or signed out: the next read of the list says what the server holds.
      seenHere.current.delete(id);
    }
  }, [repo]);

  const refresh = useCallback(async () => {
    setRefreshing(true);
    const startedRevision = revision.current;
    // Compared with the list as the refresh began: polling may show its results first.
    const before = parcelsRef.current;
    try {
      const list = await repo.refresh();
      const changed = trackingChanged(before, list);
      if (startedRevision !== revision.current) return changed;
      revision.current += 1;
      if (mounted.current) {
        setParcels(seenAsHere(list, seenHere.current));
        setError(null);
        setAuthenticationRequired(false);
      }
      return changed;
    } catch (e) {
      // Checks that outlive the wait are still running, which is no error.
      if (!(e instanceof RefreshTimeoutError)) rememberError(e);
      throw e;
    } finally {
      if (mounted.current) setRefreshing(false);
    }
  }, [repo, rememberError]);

  const refreshParcel = useCallback(async (id: string) => {
    const before = parcelsRef.current;
    try {
      const parcel = repo.refreshParcel
        ? await repo.refreshParcel(id)
        : (await repo.refresh()).find((candidate) => candidate.id === id);
      if (!parcel) throw new Error('Parcel not found after refreshing');
      revision.current += 1;
      if (mounted.current) {
        setParcels((current) =>
          [...current.filter((candidate) => candidate.id !== id && candidate.id !== parcel.id && candidate.id !== parcel.originalParcelId), parcel],
        );
        setError(null);
        setAuthenticationRequired(false);
      }
      return trackingChanged(before.filter((candidate) => candidate.id === id), [parcel]);
    } catch (error) {
      if (!(error instanceof RefreshTimeoutError)) rememberError(error);
      throw error;
    }
  }, [repo, rememberError]);

  const value = useMemo(
    () => ({
      parcels,
      loading,
      refreshing,
      error,
      authenticationRequired,
      usingCachedData,
      down,
      readAt,
      mode: repo.mode,
      addParcel,
      renameParcel,
      saveParcelNote,
      addParcelFile,
      removeParcelFile,
      changeParcelCarrier,
      setParcelNotificationsMuted,
      setParcelEmailMuted,
      markParcelSeen,
      removeParcel,
      restoreParcel,
      deleteParcel,
      refresh,
      refreshParcel,
      retryLoad,
      resetDemoData,
    }),
    [
      parcels,
      loading,
      refreshing,
      error,
      authenticationRequired,
      usingCachedData,
      down,
      readAt,
      repo.mode,
      addParcel,
      renameParcel,
      saveParcelNote,
      addParcelFile,
      removeParcelFile,
      changeParcelCarrier,
      setParcelNotificationsMuted,
      setParcelEmailMuted,
      markParcelSeen,
      removeParcel,
      restoreParcel,
      deleteParcel,
      refresh,
      refreshParcel,
      retryLoad,
      resetDemoData,
    ],
  );

  return <ParcelsContext.Provider value={value}>{children}</ParcelsContext.Provider>;
}

export function useParcels(): ParcelsState {
  const ctx = useContext(ParcelsContext);
  if (!ctx) throw new Error('useParcels must be used within ParcelsProvider');
  return ctx;
}
