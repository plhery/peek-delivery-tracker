import type { Locale } from './lib/locale';
import type {
  ApiCarrierId,
  ApiEventPlace,
  ApiImportCharges,
  ApiRecipientAction,
  ApiStage,
  ApiSyncStatus,
} from './generated/apiContract';

export type CarrierId = ApiCarrierId;

export type SyncStatus = ApiSyncStatus;

/** The duties, taxes and fees the carrier lists for importing a parcel. */
export type ImportCharges = ApiImportCharges;

/** What a carrier asks its recipient to do before customs lets the parcel go: give a tax number, pay duties or taxes, or send documents. */
export type RecipientAction = ApiRecipientAction;

/**
 * The lifecycle of a parcel. The first six are the "happy path" in order;
 * the rest are exceptions that can happen along the way.
 */
export type Stage = ApiStage;

export interface Parcel {
  id: string;
  trackingNumber: string;
  label: string;
  carrier: CarrierId;
  createdAt: string; // ISO timestamp
  expectedDelivery?: string;
  senderName?: string;
  expectedDeliveryFrom?: string;
  pickupPoint?: string;
  receiverName?: string;
  weightKg?: number;
  dimensionsText?: string;
  importCharges?: ImportCharges;
  autoChangedFrom?: CarrierId;
  autoChangedTo?: CarrierId;
  autoChangedAt?: string;
  /** A carrier that knows the number but needs this input before it can track the parcel. */
  providerInputNeeded?: { provider: string; field: 'dpdPostcode' };
  nextCheckAt?: string;
  inputNeeded?: { carrier: CarrierId; field: 'dpdPostcode' | 'trackingUrl' };
  /** A carrier the number points to that needs the delivery postcode before it can be asked about it. */
  inputSuggested?: { carrier: CarrierId; field: 'dpdPostcode' };
  /** For a parcel filed under no carrier, the only carrier whose own format its number has, asked and not listing it since `since`. */
  likelyCarrier?: { carrier: CarrierId; since: string };
  /** The carrier whose tracker writes the parcel's timeline in advance: Peek shows its real scans as they arrive. */
  writtenAheadBy?: CarrierId;
  originalParcelId?: string;
  originalCarrier?: CarrierId;
  originalTrackingNumber?: string;
  originalTrackingUrl?: string;
  lastStatusText?: string;
  lastSyncedAt?: string;
  syncStatus: SyncStatus;
  syncError?: string;
  trackingUrl?: string;
  dpdPostcode?: string;
  /** The country of `dpdPostcode`, when the owner named one other than the carrier's home country. */
  dpdPostcodeCountry?: string;
  /** The last 4 digits of a phone the owner gave J&T Express Mexico or China. */
  phoneDigits?: string;
  /** False when DPD rejected the stored postcode and tracked without it. */
  dpdPostcodeVerified?: boolean;
  /** The parcel's own carrier refused the stored postcode on its last answer (PostNL finding no parcel with it). */
  postcodeRefused?: boolean;
  /** Carrier currently supplying automatic updates for a multi-carrier journey. */
  trackingSource?: CarrierId;
  trackingProvider?: string;
  /** The carrier answered the check the provider's result came from; links stay with the carrier. */
  carrierAnswered?: boolean;
  activeTrackingNumber?: string;
  /** Who delivers, before that carrier is followed: the carrier the followed one names, else the one Peek's hand-off check found holding the parcel, and its number. */
  deliveryCarrier?: CarrierId;
  deliveryTrackingNumber?: string;
  /** That carrier lists the parcel without a scan of its own: the earlier carrier announced it there, and it has not been received. */
  deliveryAnnounced?: boolean;
  /** The number the answering carrier tracks the parcel by, when it names one: a reference resolves to its barcode. */
  canonicalTrackingNumber?: string;
  /** Whether Swiss Post has announced a Swiss-issued inbound shipment. */
  swissPostReady?: boolean;
  archivedAt?: string;
  notificationsMuted?: boolean;
  /** The account's delivery email is off for this parcel. */
  emailMuted?: boolean;
  /** Where the carrier says the parcel is heading, when it says. */
  destinationCountry?: string;
}

/** Where a scan happened, located on the server from its free-text location. */
export type EventPlace = ApiEventPlace;

export interface TrackingEvent {
  id: string;
  parcelId: string;
  stage: Stage;
  description: string;
  location?: string;
  place?: EventPlace;
  occurredAt: string; // ISO timestamp
  /** The day (YYYY-MM-DD) of a scan the carrier dated by its day alone; `occurredAt` then only puts it in order. */
  day?: string;
  /** The scan of the new carrier this scan of the earlier one repeats; the timeline shows it under that scan. */
  relayOf?: string;
  /** What the carrier asks the recipient to do in this scan; the parcel needs action while it is the newest. */
  recipientAction?: RecipientAction;
  /** The scan's place among those its source dated alike, from the oldest, in the order the source listed them. */
  tieOrder?: number;
}

/**
 * A step the carrier listed before it happened, such as a script of the whole journey written when the order was
 * made: no scan. The timeline shows it greyed as planned and not confirmed; nothing else reads it.
 */
export interface PlannedEvent {
  stage: Stage;
  description: string;
  location?: string;
  occurredAt: string; // ISO timestamp
  /** The day (YYYY-MM-DD) of a step the carrier dated by its day alone. */
  day?: string;
  /** Where the server places its location, which names its town as a scan's place does; no map shows it. */
  place?: EventPlace;
}

export interface ParcelWithEvents extends Parcel {
  events: TrackingEvent[];
  /** The carrier's planned steps still ahead of its scans, oldest first. */
  plannedEvents?: PlannedEvent[];
}

export interface NewParcelInput {
  trackingNumber: string;
  label: string;
  carrier?: CarrierId;
  trackingUrl?: string;
  dpdPostcode?: string;
  /** The postcode's country, sent only when it is not the carrier's home country. */
  dpdPostcodeCountry?: string;
  phoneDigits?: string;
}

export interface ParcelCarrierInput {
  providerPostcode?: string;
  carrier: CarrierId;
  trackingUrl?: string;
  dpdPostcode?: string;
  dpdPostcodeCountry?: string;
  phoneDigits?: string;
}

export class ParcelAlreadyExistsError extends Error {
  constructor(
    message: string,
    readonly parcelId: string,
  ) {
    super(message);
    this.name = 'ParcelAlreadyExistsError';
  }
}

/** The tracking checks outlived the wait; they keep running and their results arrive with the next poll. */
export class RefreshTimeoutError extends Error {
  constructor() {
    super('The tracking check is taking longer than expected. Updates will appear automatically.');
    this.name = 'RefreshTimeoutError';
  }
}

/** Storage backends: the shared server API in production, local demo in development. */
export interface ParcelRepo {
  readonly mode: 'api' | 'demo';
  list(): Promise<ParcelWithEvents[]>;
  /** The local demo writes its sample parcels in this language. */
  setLanguage?(locale: Locale): void;
  /** Restore the original sample parcels; available only in the local demo. */
  resetDemo?(): Promise<ParcelWithEvents[]>;
  add(input: NewParcelInput): Promise<ParcelWithEvents>;
  /** Take in a parcel followed through a parcel link, with its history; available only in the local demo. */
  adopt?(parcel: ParcelWithEvents, label: string): Promise<ParcelWithEvents>;
  rename(id: string, label: string): Promise<ParcelWithEvents>;
  changeCarrier?(id: string, input: ParcelCarrierInput): Promise<ParcelWithEvents>;
  setNotificationsMuted?(id: string, muted: boolean): Promise<ParcelWithEvents>;
  /** Turns the account's delivery email off or back on for one parcel; only an account has it. */
  setEmailMuted?(id: string, muted: boolean): Promise<ParcelWithEvents>;
  /** Soft-delete an active parcel so it can still be restored. */
  remove(id: string): Promise<void>;
  restore?(id: string): Promise<ParcelWithEvents>;
  /** Permanently delete an owned parcel and all of its tracking history. */
  deletePermanently?(id: string): Promise<void>;
  /** Re-sync tracking; in demo mode this advances the simulation. */
  refresh(): Promise<ParcelWithEvents[]>;
  /** Re-sync one parcel without waiting for every active carrier. */
  refreshParcel?(id: string): Promise<ParcelWithEvents>;
  /** Optional shared-data polling. Returns unsubscribe. */
  subscribe?(onChange: () => void | Promise<void>): () => void;
  /** Last successfully loaded API snapshot for read-only offline fallback. */
  cachedList?(): ParcelWithEvents[] | null;
}
