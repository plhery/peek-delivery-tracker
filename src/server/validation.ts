import { isAmazonTrackingNumber, requiresAmazonAccount } from '../lib/amazon';
import {
  CARRIER_IDS,
  type ApiGiftWords,
  type ApiParcelAlertPreset,
  type ApiParcelFeedbackAnswer,
  type ApiParcelFeedbackReason,
} from '../generated/apiContract';
import { carrierInfo, KEPT_NUMBER_SHAPE, normalizeTrackingNumber, notTrackingNumber, validTrackingNumber } from '../lib/carriers';
import { readsPostcodeCountry, sentPostcodeCountry, takesAnyPostcode } from '../lib/postcodeCountry';
import type { CarrierId } from '../types';
import { amazonAccountMessage } from './amazonGuide';
import { ALERT_PRESET_STAGES, ALL_NOTIFICATION_STAGES } from '../lib/notificationPresets';
import { isLocale, type Locale } from '../lib/locale';
import { createECDH } from 'node:crypto';
import { HttpError, parseUuid } from './api';
import { normalizeCarrierInputs } from './carriers';
import { normalizedLookupCountry } from './lookupCountry';
import { isPublicHost, urlHost } from './publicAddress';
import type { WebPushKeys } from './supabase';
import { isRecord, type JsonObject } from './types';

const VALID_CARRIERS = new Set<string>(CARRIER_IDS);
/** Why a placeholder or a phone number is refused; clients show their own wording for it (userErrorKey). */
export const PLACEHOLDER_MESSAGE = 'This is not a real tracking number: enter the one from your order';
const NOTIFICATION_STAGES = new Set<string>(ALL_NOTIFICATION_STAGES);
const PUSH_ENDPOINT_HOSTS = new Set([
  'android.googleapis.com',
  'fcm.googleapis.com',
  'push.services.mozilla.com',
  'updates.push.services.mozilla.com',
  'web.push.apple.com',
]);
const PUSH_ENDPOINT_HOST_SUFFIXES = ['.notify.windows.com'];

function codePointLength(value: string): number {
  return [...value].length;
}

export interface NewPackageValues {
  trackingNumber: string;
  label: string;
  carrier: string;
  trackingUrl: string | null;
  dpdPostcode: string | null;
  /** The postcode's country, when it is not the carrier's home country. */
  dpdPostcodeCountry: string | null;
  /** The last four digits of a phone, for a carrier that shows a parcel only with them (J&T Express Mexico and China). */
  phoneDigits: string | null;
}

export type PackageCarrierValues = Pick<
  NewPackageValues,
  'carrier' | 'trackingUrl' | 'dpdPostcode' | 'dpdPostcodeCountry' | 'phoneDigits'
>;

/**
 * The country a supplied postcode is from: any country for a carrier that
 * takes any country's postcode, else one of its own. Its home country, no
 * postcode, or a carrier whose lookup reads the postcode alone keeps none.
 */
function postcodeCountry(carrier: CarrierId, trackingNumber: string, postcode: string | null, raw: string): string | null {
  const code = raw.trim().toUpperCase();
  if (!code || !postcode) return null;
  if (normalizedLookupCountry(code) !== code
    || (!takesAnyPostcode(carrier, trackingNumber) && !carrierInfo(carrier).countries.includes(code))) {
    throw new HttpError(400, `${carrierInfo(carrier).name} takes no delivery postcode from that country`);
  }
  return readsPostcodeCountry(carrier, trackingNumber) ? sentPostcodeCountry(carrier, code) ?? null : null;
}

/** `country`: the reader's (lookupCountry), where a mobile number as dialled there is refused. */
export function newPackageValues(payload: JsonObject, country: string | null = null): NewPackageValues {
  const rawTracking = payload.trackingNumber ?? '';
  const rawLabel = payload.label ?? '';
  const rawCarrier = payload.carrier ?? 'unknown';
  const rawTrackingUrl = payload.trackingUrl ?? '';
  const rawDpdPostcode = payload.dpdPostcode ?? '';
  const rawPostcodeCountry = payload.dpdPostcodeCountry ?? '';
  const rawPhoneDigits = payload.phoneDigits ?? '';
  if (
    typeof rawTracking !== 'string'
    || typeof rawLabel !== 'string'
    || typeof rawCarrier !== 'string'
    || typeof rawTrackingUrl !== 'string'
    || typeof rawDpdPostcode !== 'string'
    || typeof rawPostcodeCountry !== 'string'
    || typeof rawPhoneDigits !== 'string'
  ) {
    throw new HttpError(
      400,
      'Tracking number, label, carrier, tracking URL, postcode, its country and phone digits must be text',
    );
  }
  // The number as detection reads it: the scraper decides what "the same number" means.
  const trackingNumber = normalizeTrackingNumber(rawTracking);
  if (trackingNumber.length < 4 || trackingNumber.length > 40) {
    throw new HttpError(400, 'Enter a tracking number between 4 and 40 characters');
  }
  // The scraper decides what a number may be: a digit, or letters alone in a shape a carrier issues.
  if (!KEPT_NUMBER_SHAPE.test(trackingNumber) || !validTrackingNumber(rawTracking)) {
    throw new HttpError(400, 'Tracking numbers use letters and numbers and include a digit, unless a carrier issues them as letters alone');
  }
  // Every carrier's lookup refuses a placeholder, such as the app's demo number, and no carrier issues a phone number,
  // so neither is added.
  if (notTrackingNumber(trackingNumber, country)) throw new HttpError(400, PLACEHOLDER_MESSAGE);
  if (codePointLength(rawLabel) > 80) {
    throw new HttpError(400, 'Parcel names can be at most 80 characters');
  }
  let carrier = rawCarrier;
  if (!VALID_CARRIERS.has(carrier)) throw new HttpError(400, 'Choose a supported carrier');
  if (carrier === 'amazon-shipping' && !isAmazonTrackingNumber(trackingNumber)) throw new HttpError(400, 'Invalid Amazon tracking number');
  if (requiresAmazonAccount(carrier, trackingNumber)) throw new HttpError(400, amazonAccountMessage());
  if (/^44\d{16}$/.test(trackingNumber)) carrier = 'quickpac';
  let extras: ReturnType<typeof normalizeCarrierInputs>;
  try {
    extras = normalizeCarrierInputs(
      carrier,
      trackingNumber,
      rawTrackingUrl,
      rawDpdPostcode,
      rawPhoneDigits,
    );
  } catch (error) {
    throw new HttpError(400, error instanceof Error ? error.message : 'Invalid carrier details', undefined, {
      cause: error,
    });
  }
  return {
    trackingNumber,
    label: rawLabel.trim(),
    carrier,
    ...extras,
    dpdPostcodeCountry: postcodeCountry(carrier as CarrierId, trackingNumber, extras.dpdPostcode, rawPostcodeCountry),
  };
}

export function packageCarrierValues(
  payload: JsonObject,
  trackingNumber: string,
): PackageCarrierValues {
  if (typeof payload.carrier !== 'string') {
    throw new HttpError(400, 'Carrier must be text');
  }
  const values = newPackageValues({
    trackingNumber,
    label: '',
    carrier: payload.carrier,
    trackingUrl: payload.trackingUrl ?? '',
    dpdPostcode: payload.dpdPostcode ?? '',
    dpdPostcodeCountry: payload.dpdPostcodeCountry ?? '',
    phoneDigits: payload.phoneDigits ?? '',
  });
  return {
    carrier: values.carrier,
    trackingUrl: values.trackingUrl,
    dpdPostcode: values.dpdPostcode,
    dpdPostcodeCountry: values.dpdPostcodeCountry,
    phoneDigits: values.phoneDigits,
  };
}

export function packageLabel(payload: JsonObject): string {
  if (typeof payload.label !== 'string') throw new HttpError(400, 'Parcel name must be text');
  if (codePointLength(payload.label) > 80) {
    throw new HttpError(400, 'Parcel names can be at most 80 characters');
  }
  return payload.label.trim();
}

export function pushEndpoint(value: unknown): string {
  if (typeof value !== 'string' || value.length < 1 || value.length > 4_096) {
    throw new HttpError(400, 'Send a valid push endpoint');
  }
  let url: URL;
  try {
    url = new URL(value);
  } catch (error) {
    throw new HttpError(400, 'Send a valid push endpoint', undefined, { cause: error });
  }
  const hostname = url.hostname.replace(/\.$/, '').toLowerCase();
  const allowed = PUSH_ENDPOINT_HOSTS.has(hostname)
    || PUSH_ENDPOINT_HOST_SUFFIXES.some((suffix) => hostname.endsWith(suffix));
  if (
    url.protocol !== 'https:'
    || !allowed
    || (url.port && url.port !== '443')
    || url.username
    || url.password
    || url.hash
  ) throw new HttpError(400, 'Send a valid push endpoint');
  return value;
}

function decodeBase64Url(value: string): Buffer {
  if (!value || !/^[A-Za-z0-9_-]+$/.test(value)) {
    throw new HttpError(400, 'Send valid push encryption keys');
  }
  try {
    return Buffer.from(value, 'base64url');
  } catch (error) {
    throw new HttpError(400, 'Send valid push encryption keys', undefined, { cause: error });
  }
}

export function pushSubscription(payload: JsonObject): {
  endpoint: string;
  p256dh: string;
  auth: string;
  locale?: NativePushLocale;
} {
  const endpoint = pushEndpoint(payload.endpoint);
  if (typeof payload.keys !== 'object' || payload.keys === null || Array.isArray(payload.keys)) {
    throw new HttpError(400, 'Send valid push encryption keys');
  }
  const keys = payload.keys as JsonObject;
  if (typeof keys.p256dh !== 'string' || typeof keys.auth !== 'string') {
    throw new HttpError(400, 'Send valid push encryption keys');
  }
  const publicKey = decodeBase64Url(keys.p256dh);
  const authSecret = decodeBase64Url(keys.auth);
  if (publicKey.length !== 65 || publicKey[0] !== 4 || authSecret.length !== 16) {
    throw new HttpError(400, 'Send valid push encryption keys');
  }
  return { endpoint, p256dh: keys.p256dh, auth: keys.auth,
    ...(payload.locale == null ? {} : { locale: nativePushLocale(payload.locale) }) };
}

export interface ParcelAlertValues {
  endpoint: string;
  p256dh: string;
  auth: string;
  locale: NativePushLocale;
  preset: ApiParcelAlertPreset;
}

/**
 * An alert for a parcel link: the browser's subscription, checked as an
 * account's is, what to announce and in which language. The key must also be a
 * point of the curve, so a made-up one is refused here and not at every send.
 */
export function parcelAlert(payload: JsonObject): ParcelAlertValues {
  if (!isRecord(payload.subscription)) throw new HttpError(400, 'Send a valid push subscription');
  const { endpoint, p256dh, auth } = pushSubscription({
    endpoint: payload.subscription.endpoint, keys: payload.subscription.keys,
  });
  onCurve(p256dh);
  return { endpoint, p256dh, auth, locale: nativePushLocale(payload.locale), preset: alertPreset(payload.preset) };
}

/** Refuses a key that is no point of the P-256 curve, which no message could be encrypted to. */
function onCurve(p256dh: string): void {
  try {
    const exchange = createECDH('prime256v1');
    exchange.generateKeys();
    exchange.computeSecret(Buffer.from(p256dh, 'base64url'));
  } catch (error) {
    throw new HttpError(400, 'Send valid push encryption keys', undefined, { cause: error });
  }
}

/**
 * A UnifiedPush endpoint: an HTTPS URL on a public host, without credentials or fragment, as the URL parser
 * writes it. An address in another form (`0x7f.1`, `[::ffff:127.0.0.1]`) is read as the address it is, and a
 * host of a private network (`localhost`, `.local`, `.internal`…) is refused. Each send resolves the name again
 * and connects only to public addresses (`postToPublicEndpoint`).
 */
export function unifiedPushEndpoint(value: unknown): string {
  if (typeof value !== 'string' || value.length < 1 || value.length > 4_096) {
    throw new HttpError(400, 'Send a valid UnifiedPush endpoint');
  }
  let url: URL;
  try {
    url = new URL(value);
  } catch (error) {
    throw new HttpError(400, 'Send a valid UnifiedPush endpoint', undefined, { cause: error });
  }
  if (url.protocol !== 'https:' || url.username || url.password || url.hash || url.href.length > 4_096) {
    throw new HttpError(400, 'Send a valid UnifiedPush endpoint');
  }
  if (!isPublicHost(urlHost(url))) throw new HttpError(400, 'Send a UnifiedPush endpoint on a public host');
  return url.href;
}

/**
 * The UnifiedPush endpoint and keys a request brings: both with `transport` unifiedpush, neither with any
 * other. The keys are checked as a browser's are, and kept in unpadded base64url.
 */
function webPushTarget(payload: JsonObject, transport: NativePushTransport): WebPushKeys | null {
  if (transport !== 'unifiedpush') {
    if (payload.endpoint != null || payload.keys != null) {
      throw new HttpError(400, 'Send an endpoint and keys only with the unifiedpush transport');
    }
    return null;
  }
  const endpoint = unifiedPushEndpoint(payload.endpoint);
  if (!isRecord(payload.keys) || typeof payload.keys.p256dh !== 'string' || typeof payload.keys.auth !== 'string') {
    throw new HttpError(400, 'Send valid push encryption keys');
  }
  const publicKey = decodeBase64Url(payload.keys.p256dh);
  const authSecret = decodeBase64Url(payload.keys.auth);
  if (publicKey.length !== 65 || publicKey[0] !== 4 || authSecret.length !== 16) {
    throw new HttpError(400, 'Send valid push encryption keys');
  }
  const p256dh = publicKey.toString('base64url');
  onCurve(p256dh);
  return { endpoint, p256dh, auth: authSecret.toString('base64url') };
}

function alertPreset(preset: unknown): ApiParcelAlertPreset {
  if (typeof preset !== 'string' || !Object.hasOwn(ALERT_PRESET_STAGES, preset)) {
    throw new HttpError(400, 'Choose what the alert announces');
  }
  return preset as ApiParcelAlertPreset;
}

export interface ParcelAppAlertValues {
  token: string;
  environment: NativePushEnvironment;
  locale: NativePushLocale;
  preset: ApiParcelAlertPreset;
  transport: NativePushTransport;
  /** A UnifiedPush alert's endpoint and keys. */
  webPush?: WebPushKeys;
}

/** An alert for a parcel link in the iPhone or Android app: its device token, checked as an account's is. */
export function parcelAppAlert(payload: JsonObject): ParcelAppAlertValues {
  const transport = nativePushTransport(payload.transport);
  const token = appToken(payload.token, transport);
  const webPush = webPushTarget(payload, transport);
  return {
    token,
    environment: transport === 'apns' ? nativePushEnvironment(payload.environment) : 'production',
    locale: nativePushLocale(payload.locale),
    preset: alertPreset(payload.preset),
    transport,
    ...webPush ? { webPush } : {},
  };
}

/** The device token whose alert for a link is turned off. */
export function parcelAppAlertToken(payload: JsonObject): string {
  return appToken(payload.token, nativePushTransport(payload.transport));
}

/**
 * The switches of a shared link named in `fields`: each true or false when
 * sent, with an optional protected gift message. At least one change is required
 * unless `optional`.
 */
export function shareSwitches<Field extends 'showNumber' | 'gift' | 'shared'>(
  payload: JsonObject,
  fields: readonly Field[],
  optional = false,
): Partial<Record<Field, boolean>> & { giftWords?: ApiGiftWords } {
  const switches: Partial<Record<Field, boolean>> & { giftWords?: ApiGiftWords } = {};
  for (const field of fields) {
    const value = payload[field];
    if (value === undefined) continue;
    if (typeof value !== 'boolean') throw new HttpError(400, `${field} must be true or false`);
    Object.assign(switches, { [field]: value });
  }
  if (payload.giftWords !== undefined) {
    if (!isRecord(payload.giftWords)) throw new HttpError(400, 'giftWords must be an object');
    const words: ApiGiftWords = { name: null, note: null, from: null };
    for (const [field, limit] of [['name', 80], ['note', 280], ['from', 60]] as const) {
      const value = payload.giftWords[field];
      if (value !== null && (typeof value !== 'string' || codePointLength(value) > limit)) {
        throw new HttpError(400, `giftWords.${field} must be text of at most ${limit} characters or null`);
      }
      words[field] = typeof value === 'string'
        ? value.replace(/[\p{Cc}\p{Cf}]/gu, ' ').replace(/\s+/g, ' ').trim() || null : null;
    }
    switches.giftWords = words;
  }
  if (!optional && Object.keys(switches).length === 0) {
    throw new HttpError(400, `Send ${fields.join(', ')} to change`);
  }
  return switches;
}

export function pushSubscriptionLocale(payload: JsonObject): { endpoint: string; locale: NativePushLocale } {
  return { endpoint: pushEndpoint(payload.endpoint), locale: nativePushLocale(payload.locale) };
}

export interface NativePushDeviceValues {
  token: string;
  transport: NativePushTransport;
  /** A UnifiedPush device's endpoint and keys. */
  webPush: WebPushKeys | null;
  installationId: string | null;
  environment: 'development' | 'production';
  locale: Locale;
  deviceName: string | null;
  sendTest: boolean;
}

type NativePushEnvironment = NativePushDeviceValues['environment'];
type NativePushLocale = NativePushDeviceValues['locale'];
/**
 * APNs for the iPhone app, Firebase Cloud Messaging for the Android app, and for the Android app without Google's
 * services UnifiedPush or the inbox it polls.
 */
export type NativePushTransport = 'apns' | 'fcm' | 'unifiedpush' | 'inbox';

/** The service a device token is for; a request that does not say is the iPhone app's. */
export function nativePushTransport(value: unknown): NativePushTransport {
  if (value === undefined || value === null || value === 'apns') return 'apns';
  if (value === 'fcm' || value === 'unifiedpush' || value === 'inbox') return value;
  throw new HttpError(400, 'Choose apns, fcm, unifiedpush or inbox as the transport');
}

/**
 * The Android app's installation token, which names it for UnifiedPush and the inbox: 32 random bytes in
 * unpadded base64url. Like an FCM token it is case-sensitive.
 */
export function installationToken(value: unknown): string {
  if (typeof value !== 'string' || !/^[A-Za-z0-9_-]{43}$/.test(value)) {
    throw new HttpError(400, 'Send a valid installation token');
  }
  return value;
}

/**
 * An FCM registration token. Unlike an APNs token it is case-sensitive, and stays within what the
 * database's unique index on tokens can hold.
 */
function fcmToken(value: unknown): string {
  if (typeof value !== 'string') throw new HttpError(400, 'Send a valid FCM registration token');
  const token = value.trim();
  if (token.length < 32 || token.length > 2048 || !/^[A-Za-z0-9:_-]+$/.test(token)) {
    throw new HttpError(400, 'Send a valid FCM registration token');
  }
  return token;
}

function appToken(value: unknown, transport: NativePushTransport): string {
  if (transport === 'unifiedpush' || transport === 'inbox') return installationToken(value);
  return transport === 'fcm' ? fcmToken(value) : apnsToken(value);
}

function apnsToken(value: unknown, label = 'APNs device token'): string {
  if (typeof value !== 'string') throw new HttpError(400, `Send a valid ${label}`);
  const token = value.trim().toLowerCase();
  if (
    token.length < 32
    || token.length > 512
    || token.length % 2 !== 0
    || !/^[0-9a-f]+$/.test(token)
  ) throw new HttpError(400, `Send a valid ${label}`);
  return token;
}

function nativePushEnvironment(value: unknown): NativePushEnvironment {
  if (value !== 'development' && value !== 'production') {
    throw new HttpError(400, 'Choose a valid APNs environment');
  }
  return value;
}

function nativePushLocale(value: unknown): NativePushLocale {
  if (!isLocale(value)) throw new HttpError(400, 'Choose a supported notification locale');
  return value;
}

function installationId(value: unknown): string {
  if (typeof value !== 'string') throw new HttpError(400, 'Send a valid installation id');
  return parseUuid(value, 'installation id');
}

function liveActivityId(value: unknown): string {
  if (
    typeof value !== 'string'
    || value.length < 1
    || value.length > 128
    || !/^[A-Za-z0-9-]+$/.test(value)
  ) throw new HttpError(400, 'Send a valid Live Activity id');
  return value;
}

export function nativePushDevice(
  payload: JsonObject,
  registration = true,
): NativePushDeviceValues {
  const transport = nativePushTransport(payload.transport);
  const token = appToken(payload.token, transport);
  if (!registration) {
    return {
      token,
      transport,
      webPush: null,
      installationId: null,
      environment: 'production',
      locale: 'en',
      deviceName: null,
      sendTest: false,
    };
  }
  const webPush = webPushTarget(payload, transport);
  // Only APNs has a development service: whatever an Android phone sends, its device is a production one.
  const environment = transport === 'apns' ? nativePushEnvironment(payload.environment) : 'production';
  const locale = nativePushLocale(payload.locale);
  const parsedInstallationId = payload.installationId == null
    ? null
    : installationId(payload.installationId);
  if (typeof payload.sendTest !== 'boolean') throw new HttpError(400, 'SendTest must be true or false');
  if (payload.deviceName != null && typeof payload.deviceName !== 'string') {
    throw new HttpError(400, 'Device name must be text');
  }
  const deviceName = typeof payload.deviceName === 'string' ? payload.deviceName.trim() : '';
  if (codePointLength(deviceName) > 100) {
    throw new HttpError(400, 'Device name can be at most 100 characters');
  }
  return {
    token,
    transport,
    webPush,
    installationId: parsedInstallationId,
    environment,
    locale,
    deviceName: deviceName || null,
    sendTest: payload.sendTest,
  };
}

export interface LiveActivityDeviceValues {
  revocationToken?: string;
  installationId: string;
  token: string;
  environment: NativePushEnvironment;
  locale: NativePushLocale;
}

export function liveActivityDevice(payload: JsonObject): LiveActivityDeviceValues {
  return {
    ...(payload.revocationToken === undefined ? {} : { revocationToken: liveActivityRevocationToken(payload.revocationToken) }),
    installationId: installationId(payload.installationId),
    token: apnsToken(payload.token, 'Live Activity push-to-start token'),
    environment: nativePushEnvironment(payload.environment),
    locale: nativePushLocale(payload.locale),
  };
}

export function deleteLiveActivityDevice(payload: JsonObject): { installationId: string } {
  return { installationId: installationId(payload.installationId) };
}

export interface LiveActivityUpdateTokenValues extends LiveActivityDeviceValues {
  activityId: string;
  parcelId: string;
}

export function liveActivityUpdateToken(payload: JsonObject): LiveActivityUpdateTokenValues {
  if (typeof payload.parcelId !== 'string') throw new HttpError(400, 'Invalid parcel id');
  return {
    ...liveActivityDevice(payload),
    activityId: liveActivityId(payload.activityId),
    parcelId: parseUuid(payload.parcelId, 'parcel id'),
  };
}

export function deleteLiveActivityUpdateToken(payload: JsonObject): {
  installationId: string;
  activityId: string;
} {
  return {
    installationId: installationId(payload.installationId),
    activityId: liveActivityId(payload.activityId),
  };
}

export interface NotificationPreferencesValues {
  enabledStages: string[] | undefined;
  quietHoursStart: string | null | undefined;
  quietHoursEnd: string | null | undefined;
  timezone: string | undefined;
  /** The delivery email, switched on or off. Null keeps the stored choice: the request left it out. */
  emailOnDelivery: boolean | null;
  /** The list's mark on a parcel with news, switched on or off. Null keeps the stored choice. */
  markNewUpdates: boolean | null;
  /** How the account's app shows a time and a distance, which its emails follow. Null keeps the stored one. */
  clock: '24' | '12' | null;
  distance: 'km' | 'mi' | null;
  /** Peek AI's own estimates on cards and pages, switched on or off. Null keeps the stored choice. */
  showPeekAi: boolean | null;
}

export function notificationPreferences(payload: JsonObject): NotificationPreferencesValues {
  const rawStages = payload.enabledStages;
  let enabledStages: string[] | undefined;
  if (rawStages !== undefined) {
    if (
      !Array.isArray(rawStages)
      || rawStages.length === 0
      || rawStages.length > NOTIFICATION_STAGES.size
      || rawStages.some((stage) => typeof stage !== 'string')
    ) throw new HttpError(400, 'Choose at least one notification event');
    enabledStages = rawStages as string[];
    if (
      new Set(enabledStages).size !== enabledStages.length
      || enabledStages.some((stage) => !NOTIFICATION_STAGES.has(stage))
    ) throw new HttpError(400, 'Choose valid notification events');
  }
  const quietHoursStart = payload.quietHoursStart;
  const quietHoursEnd = payload.quietHoursEnd;
  if ((quietHoursStart === undefined) !== (quietHoursEnd === undefined) || (quietHoursStart == null) !== (quietHoursEnd == null)) {
    throw new HttpError(400, 'Set both quiet-hour times or turn quiet hours off');
  }
  for (const value of [quietHoursStart, quietHoursEnd]) {
    if (value != null && (typeof value !== 'string' || !/^(?:[01]\d|2[0-3]):[0-5]\d$/.test(value))) {
      throw new HttpError(400, 'Use valid quiet-hour times');
    }
  }
  if (quietHoursStart != null && quietHoursStart === quietHoursEnd) {
    throw new HttpError(400, 'Quiet hours must have different start and end times');
  }
  if (payload.timezone !== undefined) {
    if (typeof payload.timezone !== 'string' || payload.timezone.length < 1 || payload.timezone.length > 64) {
      throw new HttpError(400, 'Use a valid timezone');
    }
    try {
      new Intl.DateTimeFormat('en', { timeZone: payload.timezone }).format();
    } catch (error) {
      throw new HttpError(400, 'Use a valid timezone', undefined, { cause: error });
    }
  }
  // Apps from before the delivery email send neither field; `emailAvailable` is the server's to say.
  const emailOnDelivery = payload.emailOnDelivery ?? null;
  if (emailOnDelivery !== null && typeof emailOnDelivery !== 'boolean') {
    throw new HttpError(400, 'Email on delivery must be true or false');
  }
  // Apps from before the mark leave it out too.
  const markNewUpdates = payload.markNewUpdates ?? null;
  if (markNewUpdates !== null && typeof markNewUpdates !== 'boolean') {
    throw new HttpError(400, 'Dot on updated parcels must be true or false');
  }
  // And the clock and units.
  const clock = payload.clock ?? null;
  if (clock !== null && clock !== '24' && clock !== '12') throw new HttpError(400, 'Clock must be 24 or 12');
  const distance = payload.distance ?? null;
  if (distance !== null && distance !== 'km' && distance !== 'mi') throw new HttpError(400, 'Distance must be km or mi');
  // And Peek AI.
  const showPeekAi = payload.showPeekAi ?? null;
  if (showPeekAi !== null && typeof showPeekAi !== 'boolean') throw new HttpError(400, 'Peek AI must be true or false');
  return {
    enabledStages,
    quietHoursStart: quietHoursStart as string | null,
    quietHoursEnd: quietHoursEnd as string | null,
    timezone: payload.timezone,
    emailOnDelivery,
    markNewUpdates,
    clock,
    distance,
    showPeekAi,
  };
}

/** The preferences as stored, and whether this server can email the account at all. */
export function notificationPreferencesResponse(row: JsonObject, emailAvailable: boolean): JsonObject {
  const shortTime = (value: unknown) => typeof value === 'string' ? value.slice(0, 5) : null;
  return {
    enabledStages: Array.isArray(row.enabled_stages) ? row.enabled_stages : [],
    quietHoursStart: shortTime(row.quiet_hours_start),
    quietHoursEnd: shortTime(row.quiet_hours_end),
    timezone: typeof row.timezone === 'string' ? row.timezone : 'Europe/Zurich',
    emailOnDelivery: typeof row.email_on_delivery === 'boolean' ? row.email_on_delivery : null,
    emailAvailable,
    markNewUpdates: row.mark_new_updates !== false,
    showPeekAi: row.show_peek_ai !== false,
    // None until an app has sent them.
    ...(row.clock === '24' || row.clock === '12' ? { clock: row.clock } : {}),
    ...(row.distance === 'km' || row.distance === 'mi' ? { distance: row.distance } : {}),
  };
}

/** What to change about one parcel's notifications and delivery email: one of them, or both. */
const FEEDBACK_ANSWERS: readonly ApiParcelFeedbackAnswer[] = ['right', 'wrong', 'found_elsewhere'];
const FEEDBACK_REASONS: readonly ApiParcelFeedbackReason[] = ['arrived', 'status', 'steps', 'time_place', 'carrier', 'other'];

export interface ParcelFeedbackValues {
  id: string;
  answer: ApiParcelFeedbackAnswer;
  reasons: ApiParcelFeedbackReason[];
  note: string | null;
  carrierName: string | null;
  trackingPage: string | null;
  asked: 'page' | 'back';
  app: 'web' | 'ios' | 'android';
  locale: NativePushLocale;
}

/** Words a reader typed: trimmed, without control characters, null when nothing is left. */
function feedbackWords(value: unknown, limit: number, label: string): string | null {
  if (value == null) return null;
  if (typeof value !== 'string') throw new HttpError(400, `${label} must be text`);
  const words = value.replace(/[\u0000-\u0008\u000b\u000c\u000e-\u001f\u007f]/g, '').trim();
  if (codePointLength(words) > limit) throw new HttpError(400, `${label} is limited to ${limit} characters`);
  return words || null;
}

/**
 * What a reader says of a parcel. Each answer carries only its own words:
 * `right` none, `wrong` reasons or a note, `found_elsewhere` a carrier or the
 * address of its tracking page.
 */
export function parcelFeedbackValues(payload: JsonObject): ParcelFeedbackValues {
  if (typeof payload.id !== 'string') throw new HttpError(400, 'Send a valid feedback id');
  const id = parseUuid(payload.id, 'feedback id');
  const answer = FEEDBACK_ANSWERS.find((known) => known === payload.answer);
  if (!answer) throw new HttpError(400, 'Choose a supported answer');
  const given = payload.reasons ?? [];
  if (!Array.isArray(given) || given.length > FEEDBACK_REASONS.length
      || given.some((reason) => !FEEDBACK_REASONS.some((known) => known === reason))
      || new Set(given).size !== given.length) {
    throw new HttpError(400, 'Choose supported reasons');
  }
  const reasons = given as ApiParcelFeedbackReason[];
  const note = feedbackWords(payload.note, 4_000, 'A note');
  const carrierName = feedbackWords(payload.carrierName, 120, 'A carrier name');
  const trackingPage = feedbackWords(payload.trackingPage, 500, 'A tracking page');
  if (payload.asked !== 'page' && payload.asked !== 'back') throw new HttpError(400, 'Say where the question was asked');
  if (payload.app !== 'web' && payload.app !== 'ios' && payload.app !== 'android') throw new HttpError(400, 'Say which app is asking');
  const said = reasons.length > 0 || note !== null;
  const found = carrierName !== null || trackingPage !== null;
  if (answer === 'right' ? said || found : answer === 'wrong' ? !said || found : said || !found) {
    throw new HttpError(400, answer === 'right' ? 'This answer takes no words'
      : answer === 'wrong' ? 'Say what is off' : 'Name the carrier or its tracking page');
  }
  return {
    id, answer, reasons, note, carrierName, trackingPage,
    asked: payload.asked, app: payload.app, locale: nativePushLocale(payload.locale),
  };
}

export function packageNotificationValues(payload: JsonObject): { muted?: boolean; emailMuted?: boolean } {
  const { muted, emailMuted } = payload;
  if (muted !== undefined && typeof muted !== 'boolean') throw new HttpError(400, 'Muted must be true or false');
  if (emailMuted !== undefined && typeof emailMuted !== 'boolean') {
    throw new HttpError(400, 'Email muted must be true or false');
  }
  if (muted === undefined && emailMuted === undefined) {
    throw new HttpError(400, 'Muted or emailMuted must be true or false');
  }
  return { ...(muted === undefined ? {} : { muted }), ...(emailMuted === undefined ? {} : { emailMuted }) };
}

/** An instant as the API serves one: a date, a time to the second or finer, and its offset. */
const INSTANT = /^\d{4}-\d{2}-\d{2}T\d{2}:\d{2}:\d{2}(?:\.\d{1,9})?(?:Z|[+-]\d{2}:\d{2})$/;

/** The news a parcel's page showed as it was opened (`news_at`), or null when the request names none. */
export function shownNews(payload: JsonObject): string | null {
  const news = payload.news_at;
  if (news === undefined || news === null) return null;
  if (typeof news !== 'string' || !INSTANT.test(news) || !Number.isFinite(Date.parse(news))) {
    throw new HttpError(400, 'news_at must be a date and time');
  }
  return news;
}

/** The token and the wanted state from the page an email links to. A switch left out turns the email off. */
export function deliveryEmailSwitch(payload: JsonObject): { token: unknown; enabled: boolean } {
  if (payload.enabled !== undefined && typeof payload.enabled !== 'boolean') {
    throw new HttpError(400, 'Enabled must be true or false');
  }
  return { token: payload.token, enabled: payload.enabled === true };
}

export function syncJobResponse(row: JsonObject): JsonObject {
  return {
    id: row.id ?? null,
    status: row.state ?? null,
    packageId: row.package_id ?? null,
    requestedAt: row.requested_at ?? null,
    startedAt: row.started_at ?? null,
    completedAt: row.completed_at ?? null,
    result: row.result ?? null,
    error: row.last_error ?? null,
  };
}

export function liveActivityRevocationToken(value: unknown): string {
  if (typeof value !== 'string' || !/^[0-9a-f]{64}$/.test(value)) {
    throw new HttpError(400, 'Invalid Live Activity revocation proof');
  }
  return value;
}
