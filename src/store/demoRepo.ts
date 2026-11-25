import demoSamples from '../../shared/delivery-demo.json';
import {
  detectCarrier,
  normalizeTrackingNumber,
  supportsSwissPostHandoff,
} from '../lib/carriers';
import { isLocale, type Locale } from '../lib/locale';
import { clipNote, FILE_MAX_BYTES, FILES_PER_PARCEL, FILES_TOTAL_BYTES, NoteFileError, withoutFile } from '../lib/parcelNote';
import { currentStage, isFinal, latestEvent } from '../lib/stages';
import { uid } from '../lib/uid';
import { DEMO_NOTE, DEMO_NOTE_PARCEL, demoFiles, demoNoteIn, freshDemoFile, isDemoFile } from './demoNote';
import {
  ParcelAlreadyExistsError,
  type EventPlace,
  type NewParcelInput,
  type ParcelCarrierInput,
  type ParcelFile,
  type ParcelFileType,
  type ParcelRepo,
  type ParcelWithEvents,
  type PeekAi,
  type Stage,
  type TrackingEvent,
} from '../types';

export const DEMO_STORAGE_KEY = 'sdt.demo.parcels.v1';

/** One sample parcel of the demo, timed relative to the moment it is seeded. */
interface DemoSample {
  label: string;
  trackingNumber: string;
  carrier: string;
  expectedInDays?: number;
  archivedHoursAgo?: number;
  senderName?: string;
  pickupPoint?: string;
  weightKg?: number;
  events: { stage: string; hoursAgo: number; description: string; location?: string; place?: unknown }[];
  /** Steps the carrier wrote ahead, never scans; a negative `hoursAgo` is still to come. */
  plannedEvents?: { stage: string; hoursAgo: number; description: string; location?: string }[];
  /** What Peek AI says of it, worked out when it is shown (`demoPeekAi`): `by_time` out for delivery, `by_day` with no date and no planned steps. */
  peekAi?: string;
}

const demoCatalog: DemoSample[] = demoSamples;
const HOUR = 3_600_000;
const CATALOG_KEY = 'sdt.demo.catalog.v2';
/** The language the saved demo text is written in; English when absent. */
const LANGUAGE_KEY = 'sdt.demo.language.v1';

/** The demo's own English text and its translation; a language loads when the demo first needs it. */
type DemoDictionary = Record<string, string>;
const dictionaryLoaders: Record<Exclude<Locale, 'en'>, () => Promise<{ default: DemoDictionary }>> = {
  de: () => import('../../shared/demo-locales/de.json'),
  fr: () => import('../../shared/demo-locales/fr.json'),
  it: () => import('../../shared/demo-locales/it.json'),
  es: () => import('../../shared/demo-locales/es.json'),
  pt: () => import('../../shared/demo-locales/pt.json'),
  'pt-br': () => import('../../shared/demo-locales/pt-br.json'),
  pl: () => import('../../shared/demo-locales/pl.json'),
  ru: () => import('../../shared/demo-locales/ru.json'),
  zh: () => import('../../shared/demo-locales/zh.json'),
};
const dictionaries = new Map<Locale, DemoDictionary>([['en', {}]]);

async function demoDictionary(locale: Locale): Promise<DemoDictionary> {
  const loaded = dictionaries.get(locale);
  if (loaded) return loaded;
  const dictionary = (await dictionaryLoaders[locale as Exclude<Locale, 'en'>]()).default;
  dictionaries.set(locale, dictionary);
  return dictionary;
}

/** Says the demo's own text in a language. Text the demo did not write stays as it is. */
export async function demoWording(locale: Locale): Promise<(text: string) => string> {
  const dictionary = await demoDictionary(locale);
  return (text) => dictionary[text] ?? text;
}

function createMemoryStorage(): Storage {
  const values = new Map<string, string>();
  return {
    get length() {
      return values.size;
    },
    clear() {
      values.clear();
    },
    getItem(key) {
      return values.get(key) ?? null;
    },
    key(index) {
      return [...values.keys()][index] ?? null;
    },
    removeItem(key) {
      values.delete(key);
    },
    setItem(key, value) {
      values.set(key, value);
    },
  };
}

function defaultStorage(): Storage {
  if (typeof window !== 'undefined') {
    try {
      const storage = window.localStorage;
      const probeKey = 'sdt.storage.probe';
      const previous = storage.getItem(probeKey);
      storage.setItem(probeKey, '1');
      if (previous === null) storage.removeItem(probeKey);
      else storage.setItem(probeKey, previous);
      return storage;
    } catch {
      // Some privacy modes expose localStorage but deny access to it.
    }
  }
  return createMemoryStorage();
}

/** What the simulated carrier says at each stage, and where the server would place it. */
export const SIMULATED_UPDATES: Record<Stage, { description: string; location?: string; place?: EventPlace }> = {
  pending: {
    description: 'Tracking added; the carrier has not announced it yet',
  },
  registered: {
    description: 'The sender created a shipping label',
  },
  accepted: {
    description: 'Parcel accepted at the counter',
    location: 'Zürich-Mülligen',
    place: { latitude: 47.367, longitude: 8.55, precision: 'city', country: 'CH', name: 'Zürich' },
  },
  in_transit: {
    description: 'Sorted at the parcel centre',
    location: 'Härkingen',
    place: { latitude: 47.305, longitude: 7.821, precision: 'city', country: 'CH', name: 'Härkingen' },
  },
  customs: {
    description: 'Held for customs clearance',
    location: 'Basel',
    place: { latitude: 47.558, longitude: 7.573, precision: 'city', country: 'CH', name: 'Basel' },
  },
  exception: {
    description: 'A problem is holding up the parcel',
    location: 'Härkingen',
    place: { latitude: 47.305, longitude: 7.821, precision: 'city', country: 'CH', name: 'Härkingen' },
  },
  out_for_delivery: {
    description: 'With the courier for delivery today',
    location: 'Your neighbourhood',
  },
  failed_attempt: {
    description: 'Nobody home — a notice was left',
  },
  ready_for_pickup: {
    description: 'Ready for pickup at your branch',
    location: 'Post branch',
  },
  delivered: {
    description: 'Delivered to your letterbox',
    location: 'Home',
  },
  returned: {
    description: 'Returned to the sender',
  },
};

/** Where the simulation goes next from a given stage. */
export function nextStage(stage: Stage): Stage | null {
  switch (stage) {
    case 'pending':
      return 'registered';
    case 'registered':
      return 'accepted';
    case 'accepted':
      return 'in_transit';
    case 'customs':
    case 'exception':
      return 'in_transit';
    case 'in_transit':
      return 'out_for_delivery';
    case 'out_for_delivery':
      return 'delivered';
    case 'failed_attempt':
      return 'ready_for_pickup';
    case 'ready_for_pickup':
      return 'delivered';
    case 'delivered':
    case 'returned':
      return null;
  }
}

function simulatedEvent(
  parcelId: string,
  stage: Stage,
  occurredAt: string,
): TrackingEvent {
  const sim = SIMULATED_UPDATES[stage];
  return {
    id: uid(),
    parcelId,
    stage,
    description: sim.description,
    location: sim.location,
    place: sim.place,
    occurredAt,
  };
}

/** Shared with the iPhone demo; dates are relative to the moment it is seeded. */
export function seedParcels(now: number): ParcelWithEvents[] {
  const iso = (hoursAgo: number) => new Date(now - hoursAgo * HOUR).toISOString();
  return demoCatalog.map((sample) => {
    const id = uid();
    let expectedDelivery: string | undefined;
    if (sample.expectedInDays !== undefined) {
      const date = new Date(now);
      date.setDate(date.getDate() + sample.expectedInDays);
      expectedDelivery = `${date.getFullYear()}-${String(date.getMonth() + 1).padStart(2, '0')}-${String(date.getDate()).padStart(2, '0')}`;
    }
    return {
      id, label: sample.label, trackingNumber: sample.trackingNumber,
      carrier: sample.carrier as ParcelWithEvents['carrier'],
      createdAt: iso(Math.max(...sample.events.map((event) => event.hoursAgo))),
      expectedDelivery, syncStatus: 'ok',
      archivedAt: sample.archivedHoursAgo === undefined ? undefined : iso(sample.archivedHoursAgo),
      dpdPostcode: sample.carrier === 'dpd' ? '8000' : undefined,
      senderName: sample.senderName, pickupPoint: sample.pickupPoint, weightKg: sample.weightKg,
      events: sample.events.map((event) => ({
        id: uid(), parcelId: id, stage: event.stage as Stage,
        description: event.description, location: event.location, occurredAt: iso(event.hoursAgo),
        place: event.place as EventPlace | undefined,
      })),
      ...(sample.plannedEvents ? { plannedEvents: sample.plannedEvents.map((step) => ({
        stage: step.stage as Stage, description: step.description, location: step.location, occurredAt: iso(step.hoursAgo),
      })) } : {}),
      // One sample carries a private note, with a receipt and a photo.
      ...(sample.trackingNumber === DEMO_NOTE_PARCEL ? { note: DEMO_NOTE.en, files: demoFiles(iso(Math.min(...sample.events.map((event) => event.hoursAgo)))) } : {}),
    };
  });
}

/**
 * The files added to the demo's notes, kept by this page alone as object addresses: a demo has no server to send them
 * to, and they go when the page does.
 */
const addedFiles = new Map<string, ParcelFile>();

/** The notes' files as this page can show them: the demo's own with fresh addresses, added ones while the page keeps them. */
function withPageFiles(parcels: ParcelWithEvents[]): ParcelWithEvents[] {
  return parcels.map((parcel) => {
    if (!parcel.files) return parcel;
    const files = parcel.files.flatMap((file) => isDemoFile(file.id) ? [freshDemoFile(file)] : addedFiles.has(file.id) ? [addedFiles.get(file.id)!] : []);
    if (files.length === parcel.files.length && files.every((file, index) => file === parcel.files![index])) return parcel;
    if (files.length) return { ...parcel, files };
    const next: ParcelWithEvents = { ...parcel };
    delete next.files;
    return next;
  });
}

const PROGRESS: readonly Stage[] = ['registered', 'accepted', 'in_transit', 'out_for_delivery', 'delivered'];

/**
 * The planned steps a demo parcel still shows once its scans move on, by the server's rule (plannedSteps.ts): none
 * once it is delivered or returned, and none a scan is as late as or has come further than.
 */
export function stillPlanned(parcel: ParcelWithEvents): ParcelWithEvents {
  if (!parcel.plannedEvents) return parcel;
  const { plannedEvents, ...rest } = parcel;
  if (parcel.events.some((event) => event.stage === 'delivered' || event.stage === 'returned')) return rest;
  const latest = Math.max(...parcel.events.map((event) => Date.parse(event.occurredAt)));
  const reached = Math.max(-1, ...parcel.events.map((event) => PROGRESS.indexOf(event.stage)));
  const kept = plannedEvents.filter((step) => Date.parse(step.occurredAt) > latest
    && !(PROGRESS.indexOf(step.stage) >= 0 && PROGRESS.indexOf(step.stage) < reached));
  return kept.length ? { ...rest, plannedEvents: kept } : rest;
}

function load(storage: Storage): ParcelWithEvents[] | null {
  try {
    const raw = storage.getItem(DEMO_STORAGE_KEY);
    if (!raw) return null;
    const parsed = JSON.parse(raw);
    return Array.isArray(parsed) ? withDemoPlaces(parsed as ParcelWithEvents[]) : null;
  } catch {
    return null;
  }
}

/** Demo parcels saved before scans had places get them from the same sample data. */
function withDemoPlaces(parcels: ParcelWithEvents[]): ParcelWithEvents[] {
  const known = new Map<string, EventPlace>();
  for (const event of [...demoCatalog.flatMap((sample) => sample.events), ...Object.values(SIMULATED_UPDATES)]) {
    if (event.location && event.place) known.set(event.location, event.place as EventPlace);
  }
  return parcels.map((parcel) => parcel.events.every((event) => event.place || !event.location) ? parcel : {
    ...parcel,
    events: parcel.events.map((event) => event.place || !event.location ? event : { ...event, place: known.get(event.location) }),
  });
}

function save(storage: Storage, parcels: ParcelWithEvents[]): void {
  // Peek AI is worked out each time the parcels are shown, never kept.
  storage.setItem(DEMO_STORAGE_KEY, JSON.stringify(parcels.map(withoutPeekAi)));
}

function withoutPeekAi(parcel: ParcelWithEvents): ParcelWithEvents {
  const kept = { ...parcel };
  delete kept.peekAi;
  return kept;
}

const DEMO_PEEK_AI = new Map(demoCatalog.flatMap((sample) => sample.peekAi ? [[sample.trackingNumber, sample.peekAi] as const] : []));
/** The share of a courier's deliveries in each hour of its day. */
const DEMO_HOURS = [0.03, 0.07, 0.12, 0.16, 0.17, 0.14, 0.11, 0.09, 0.05, 0.03, 0.02, 0.01];
const pad = (value: number) => String(value).padStart(2, '0');
const calendarDay = (date: Date) => `${date.getFullYear()}-${pad(date.getMonth() + 1)}-${pad(date.getDate())}`;
/** A time on this device's clock, with its offset, as the server writes a scan's local time. */
function withOffset(date: Date): string {
  const offset = -date.getTimezoneOffset();
  const hours = Math.floor(Math.abs(offset) / 60);
  return `${calendarDay(date)}T${pad(date.getHours())}:${pad(date.getMinutes())}:${pad(date.getSeconds())}${offset < 0 ? '-' : '+'}${pad(hours)}:${pad(Math.abs(offset) % 60)}`;
}

/**
 * What Peek AI says of a sample parcel that shows it, at `now`, in the shapes the server gives (peekAi.ts): out for
 * delivery today, by 16:00 until then; on its way with no carrier date, by the third delivery day from today.
 */
export function demoPeekAi(parcel: ParcelWithEvents, now: number): PeekAi | undefined {
  const kind = DEMO_PEEK_AI.get(parcel.trackingNumber);
  const latest = latestEvent(parcel.events);
  if (!kind || !latest || parcel.archivedAt) return undefined;
  const at = new Date(now);
  if (kind === 'by_time') {
    const out = new Date(latest.occurredAt);
    if (latest.stage !== 'out_for_delivery' || calendarDay(out) !== calendarDay(at) || out.getHours() >= 16 || at.getHours() >= 16) return undefined;
    return { says: 'by_time', why: 'out_for_delivery', time: '16:00', sure: 0.86, out_since: withOffset(out),
      hours: DEMO_HOURS.map((share, index) => ({ time: `${pad(8 + index)}:00`, share })) };
  }
  if (parcel.expectedDelivery || parcel.plannedEvents?.length || !['accepted', 'in_transit', 'customs'].includes(latest.stage)) return undefined;
  // The next delivery days, from today; no courier comes on Sundays.
  const days: string[] = [];
  for (const day = new Date(at); days.length < 5; day.setDate(day.getDate() + 1)) if (day.getDay() !== 0) days.push(calendarDay(day));
  return { says: 'by_day', why: 'no_date', day: days[2]!, sure: 0.81, last: { kind: latest.stage === 'customs' ? 'customs' : 'scan', at: latest.occurredAt },
    days: days.map((day, index) => ({ day, p: [0.04, 0.27, 0.41, 0.18, 0.06][index]! })) };
}

/** The parcels as they are shown: the samples that show Peek AI say what it expects now. */
function withPeekAi(parcels: ParcelWithEvents[], now: number): ParcelWithEvents[] {
  return parcels.map((parcel) => {
    const rest = withoutPeekAi(parcel);
    const ai = demoPeekAi(rest, now);
    return ai ? { ...rest, peekAi: ai } : rest;
  });
}

/**
 * A local, offline backend used when Supabase is not configured.
 * Parcels live in localStorage and "refresh" advances a small simulation,
 * so the app is fully usable (and demoable) with zero setup.
 */
export function createDemoRepo(
  storage: Storage = defaultStorage(),
  now: () => number = Date.now,
): ParcelRepo {
  let language: Locale = 'en';

  function saved(): ParcelWithEvents[] {
    let parcels = load(storage);
    if (!parcels) {
      parcels = seedParcels(now());
      save(storage, parcels);
      storage.setItem(CATALOG_KEY, '1');
      storage.removeItem(LANGUAGE_KEY);
    } else if (!storage.getItem(CATALOG_KEY)) {
      // Upgrade existing demos once, preserving edits, archives, and custom parcels.
      // Empty lists and separately seeded test/showcase data stay as they are.
      const legacyNumbers = ['993412345678901234', '1234567899', 'LX123456789DE'];
      if (parcels.some((parcel) => legacyNumbers.includes(parcel.trackingNumber))) {
        const existing = new Set(parcels.map((parcel) => parcel.trackingNumber));
        parcels = [...parcels, ...seedParcels(now()).filter((parcel) => !legacyNumbers.includes(parcel.trackingNumber) && !existing.has(parcel.trackingNumber))];
        save(storage, parcels);
      }
      storage.setItem(CATALOG_KEY, '1');
    }
    return parcels;
  }

  /**
   * The saved parcels with the demo's own text in the app's language. Names
   * someone edited and text the demo did not write match nothing and stay.
   */
  async function getAll(): Promise<ParcelWithEvents[]> {
    const target = language;
    const wanted = await demoDictionary(target);
    const written = storage.getItem(LANGUAGE_KEY);
    const from = isLocale(written) ? written : 'en';
    if (from === target) return withPeekAi(withPageFiles(saved()), now());
    const english = new Map(Object.entries(await demoDictionary(from)).map(([text, translated]) => [translated, text]));
    const say = (text: string) => {
      const original = english.get(text) ?? text;
      return wanted[original] ?? original;
    };
    const parcels = withPageFiles(saved()).map((parcel) => ({
      ...parcel,
      label: say(parcel.label),
      ...(parcel.note ? { note: demoNoteIn(parcel.note, target) } : {}),
      events: parcel.events.map((scan) => ({
        ...scan, description: say(scan.description), location: scan.location && say(scan.location),
      })),
      ...(parcel.plannedEvents ? { plannedEvents: parcel.plannedEvents.map((step) => ({
        ...step, description: say(step.description), location: step.location && say(step.location),
      })) } : {}),
    }));
    save(storage, parcels);
    storage.setItem(LANGUAGE_KEY, target);
    return withPeekAi(parcels, now());
  }

  /** A simulated scan, said in the language the demo is written in. */
  const event = (parcelId: string, stage: Stage, occurredAt: string): TrackingEvent => {
    const scan = simulatedEvent(parcelId, stage, occurredAt);
    const dictionary = dictionaries.get(language) ?? {};
    return {
      ...scan,
      description: dictionary[scan.description] ?? scan.description,
      location: scan.location && (dictionary[scan.location] ?? scan.location),
    };
  };

  const sortNewestFirst = (parcels: ParcelWithEvents[]) =>
    [...parcels].sort((a, b) => b.createdAt.localeCompare(a.createdAt));

  const advance = (parcel: ParcelWithEvents): ParcelWithEvents => {
    if (parcel.archivedAt) return parcel;
    const stage = currentStage(parcel.events, parcel.returnHeld);
    if (stage === null || isFinal(stage)) return parcel;
    const next = nextStage(stage);
    if (!next) return parcel;
    // Keep timestamps strictly increasing so the newest event always wins,
    // even when refreshing several times in the same instant.
    const lastTs = Date.parse(latestEvent(parcel.events)!.occurredAt);
    const occurredAt = new Date(Math.max(now(), lastTs + 1000)).toISOString();
    const swissPostReady = supportsSwissPostHandoff(parcel.trackingNumber)
      && ['in_transit', 'customs', 'out_for_delivery', 'delivered'].includes(next);
    return stillPlanned({
      ...parcel,
      // The simulated scan is news until the parcel is opened.
      newsAt: new Date(now()).toISOString(),
      seenAt: parcel.seenAt ?? parcel.createdAt,
      trackingSource: swissPostReady ? 'swiss-post' : parcel.trackingSource,
      swissPostReady: swissPostReady || parcel.swissPostReady,
      events: [...parcel.events, event(parcel.id, next, occurredAt)],
    });
  };

  return {
    mode: 'demo',

    async list() {
      return sortNewestFirst(await getAll());
    },

    setLanguage(locale: Locale) {
      language = locale;
    },

    async resetDemo() {
      save(storage, seedParcels(now()));
      storage.setItem(CATALOG_KEY, '1');
      storage.removeItem(LANGUAGE_KEY);
      return sortNewestFirst(await getAll());
    },

    async add(input: NewParcelInput) {
      const parcels = await getAll();
      const trackingNumber = normalizeTrackingNumber(input.trackingNumber);
      const existing = parcels.find((candidate) => candidate.trackingNumber === trackingNumber);
      if (existing) {
        throw new ParcelAlreadyExistsError(
          'This tracking number is already in your delivery box',
          existing.id,
        );
      }
      const createdAt = new Date(now()).toISOString();
      const parcel: ParcelWithEvents = {
        id: uid(),
        trackingNumber,
        label: input.label,
        carrier: input.carrier ?? detectCarrier(trackingNumber),
        trackingUrl: input.trackingUrl?.trim() || undefined,
        dpdPostcode: input.dpdPostcode?.trim() || undefined,
        dpdPostcodeCountry: input.dpdPostcode?.trim() ? input.dpdPostcodeCountry : undefined,
        createdAt,
        syncStatus: 'ok',
        events: [],
      };
      if (supportsSwissPostHandoff(trackingNumber)) {
        parcel.trackingSource = 'aliexpress';
        parcel.swissPostReady = false;
      }
      parcel.events = [event(parcel.id, 'pending', createdAt)];
      save(storage, [...parcels, parcel]);
      return parcel;
    },

    async adopt(followed: ParcelWithEvents, label: string) {
      const parcels = await getAll();
      const existing = parcels.find((candidate) => candidate.trackingNumber === followed.trackingNumber);
      if (existing) {
        throw new ParcelAlreadyExistsError(
          'This tracking number is already in your delivery box',
          existing.id,
        );
      }
      const id = uid();
      const parcel: ParcelWithEvents = {
        ...followed,
        id,
        label: label.trim(),
        archivedAt: undefined,
        events: followed.events.map((scan) => ({ ...scan, id: uid(), parcelId: id })),
      };
      // A followed parcel's link carries no note; what the device noted about it comes after, with setNote and addFile.
      delete parcel.note;
      delete parcel.files;
      save(storage, [...parcels, parcel]);
      return parcel;
    },

    async rename(id: string, nextLabel: string) {
      const label = nextLabel.trim();
      if (label.length > 80) {
        throw new Error('Parcel names can be at most 80 characters');
      }
      const parcels = await getAll();
      const parcel = parcels.find((candidate) => candidate.id === id);
      if (!parcel) throw new Error('Parcel not found');
      const renamed = { ...parcel, label };
      save(
        storage,
        parcels.map((candidate) => candidate.id === id ? renamed : candidate),
      );
      return renamed;
    },

    async setNote(id: string, text: string | null) {
      const parcels = await getAll();
      const parcel = parcels.find((candidate) => candidate.id === id);
      if (!parcel) throw new Error('Parcel not found');
      const updated = { ...parcel, note: text?.trim() ? clipNote(text) : undefined };
      if (!updated.note) delete updated.note;
      save(storage, parcels.map((candidate) => candidate.id === id ? updated : candidate));
      return updated;
    },

    async addFile(id: string, file: Blob, name: string, onProgress?: (sent: number) => void) {
      const parcels = await getAll();
      const parcel = parcels.find((candidate) => candidate.id === id);
      if (!parcel) throw new Error('Parcel not found');
      // The demo keeps the limits an account has.
      if (file.size > FILE_MAX_BYTES) throw new NoteFileError('too-big', file.size);
      if ((parcel.files?.length ?? 0) >= FILES_PER_PARCEL) throw new NoteFileError('parcel-full');
      const used = parcels.flatMap((candidate) => candidate.files ?? []).reduce((total, kept) => total + kept.size, 0);
      if (used + file.size > FILES_TOTAL_BYTES) throw new NoteFileError('account-full');
      const contentType = file.type as ParcelFileType;
      const url = URL.createObjectURL(file);
      const added: ParcelFile = {
        id: uid(), name, contentType, size: file.size, createdAt: new Date(now()).toISOString(), url,
        thumbnailUrl: contentType === 'application/pdf' ? null : url, expiresAt: '9999-12-31T23:59:59.000Z',
      };
      addedFiles.set(added.id, added);
      onProgress?.(file.size);
      const updated = { ...parcel, files: [...parcel.files ?? [], added] };
      save(storage, parcels.map((candidate) => candidate.id === id ? updated : candidate));
      return updated;
    },

    async removeFile(id: string, fileId: string) {
      const parcels = await getAll();
      const removed = addedFiles.get(fileId);
      if (removed) {
        addedFiles.delete(fileId);
        URL.revokeObjectURL(removed.url);
      }
      save(storage, parcels.map((candidate) => candidate.id === id ? withoutFile(candidate, fileId) : candidate));
    },

    async changeCarrier(id: string, input: ParcelCarrierInput) {
      const parcels = await getAll();
      const parcel = parcels.find((candidate) => candidate.id === id);
      if (!parcel) throw new Error('Parcel not found');
      const changedAt = new Date(now()).toISOString();
      const updated: ParcelWithEvents = {
        id: parcel.id,
        trackingNumber: parcel.trackingNumber,
        label: parcel.label,
        carrier: input.carrier,
        createdAt: parcel.createdAt,
        trackingUrl: input.trackingUrl?.trim() || undefined,
        dpdPostcode: input.dpdPostcode?.trim() || undefined,
        dpdPostcodeCountry: input.dpdPostcode?.trim() ? input.dpdPostcodeCountry : undefined,
        archivedAt: parcel.archivedAt,
        notificationsMuted: parcel.notificationsMuted,
        // The note stays with the parcel, whichever carrier tracks it.
        ...(parcel.note ? { note: parcel.note } : {}),
        ...(parcel.files ? { files: parcel.files } : {}),
        syncStatus: 'pending',
        events: [event(parcel.id, 'pending', changedAt)],
      };
      if (supportsSwissPostHandoff(parcel.trackingNumber)) {
        updated.trackingSource = 'aliexpress';
        updated.swissPostReady = false;
      }
      save(
        storage,
        parcels.map((candidate) => candidate.id === id ? updated : candidate),
      );
      return updated;
    },

    async markSeen(id: string) {
      const parcels = await getAll();
      const seenAt = new Date(now()).toISOString();
      save(storage, parcels.map((candidate) => candidate.id === id ? { ...candidate, seenAt } : candidate));
      return seenAt;
    },

    async setNotificationsMuted(id: string, muted: boolean) {
      const parcels = await getAll();
      const parcel = parcels.find((candidate) => candidate.id === id);
      if (!parcel) throw new Error('Parcel not found');
      const updated = { ...parcel, notificationsMuted: muted };
      save(
        storage,
        parcels.map((candidate) => candidate.id === id ? updated : candidate),
      );
      return updated;
    },

    async remove(id: string) {
      const archivedAt = new Date(now()).toISOString();
      save(
        storage,
        (await getAll()).map((parcel) => parcel.id === id ? { ...parcel, archivedAt } : parcel),
      );
    },

    async restore(id: string) {
      const parcels = await getAll();
      const parcel = parcels.find((candidate) => candidate.id === id);
      if (!parcel) throw new Error('Parcel not found');
      const restored = { ...parcel };
      delete restored.archivedAt;
      save(
        storage,
        parcels.map((candidate) => candidate.id === id ? restored : candidate),
      );
      return restored;
    },

    async deletePermanently(id: string) {
      const parcels = await getAll();
      const parcel = parcels.find((candidate) => candidate.id === id);
      if (!parcel) throw new Error('Parcel not found');
      save(storage, parcels.filter((candidate) => candidate.id !== id));
    },

    async refresh() {
      const advanced = (await getAll()).map(advance);
      save(storage, advanced);
      return sortNewestFirst(withPeekAi(advanced, now()));
    },

    async refreshParcel(id: string) {
      const parcels = await getAll();
      const parcel = parcels.find((candidate) => candidate.id === id);
      if (!parcel) throw new Error('Parcel not found');
      const advanced = advance(parcel);
      save(
        storage,
        parcels.map((candidate) => candidate.id === id ? advanced : candidate),
      );
      return withPeekAi([advanced], now())[0]!;
    },
  };
}
