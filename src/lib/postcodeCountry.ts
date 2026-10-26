import { activeRequirements, carrierInfo, normalizeTrackingNumber } from 'universal-parcel-scraper';
import postcodes from '../../shared/postcode-examples.json';
import type { CarrierInputRequirement } from './carriers';
import type { CarrierId } from '../types';

/**
 * A delivery postcode can be from another country than the carrier's own: a
 * parcel PostNL delivers in Belgium has a Belgian one. The reader names the
 * country beside the postcode, and the lookup sends it with the postcode. A
 * postcode from the carrier's home country goes without one, as it always has.
 */

/** ISO 3166-1 countries, and Kosovo. */
export const COUNTRIES: readonly string[] = (
  'AD AE AF AG AI AL AM AO AQ AR AS AT AU AW AX AZ BA BB BD BE BF BG BH BI BJ BL BM BN BO BQ BR BS BT BV BW BY BZ '
  + 'CA CC CD CF CG CH CI CK CL CM CN CO CR CU CV CW CX CY CZ DE DJ DK DM DO DZ EC EE EG EH ER ES ET FI FJ FK FM FO FR '
  + 'GA GB GD GE GF GG GH GI GL GM GN GP GQ GR GS GT GU GW GY HK HM HN HR HT HU ID IE IL IM IN IO IQ IR IS IT JE JM JO JP '
  + 'KE KG KH KI KM KN KP KR KW KY KZ LA LB LC LI LK LR LS LT LU LV LY MA MC MD ME MF MG MH MK ML MM MN MO MP MQ MR MS MT '
  + 'MU MV MW MX MY MZ NA NC NE NF NG NI NL NO NP NR NU NZ OM PA PE PF PG PH PK PL PM PN PR PS PT PW PY QA RE RO RS RU RW '
  + 'SA SB SC SD SE SG SH SI SJ SK SL SM SN SO SR SS ST SV SX SY SZ TC TD TF TG TH TJ TK TL TM TN TO TR TT TV TW TZ UA UG '
  + 'UM US UY UZ VA VC VE VG VI VN VU WF WS XK YE YT ZA ZM ZW'
).split(' ');

const EXAMPLES: Readonly<Record<string, string>> = postcodes.examples;

/** The shape any country's postcode takes, as the server's check reads it. */
const ANY_POSTCODE = '^(?=.{3,12}$)(?=.*[0-9])[A-Za-z0-9]+(?:[ \\-][A-Za-z0-9]+)*$';

/** The country a postcode given for the carrier is from unless the reader names another. */
export function homePostcodeCountry(carrierId: CarrierId): string | null {
  return carrierInfo(carrierId).countries[0] ?? null;
}

/** Whether the carrier's lookup takes any country's postcode for this number, not only the shapes of its own countries. */
export function takesAnyPostcode(carrierId: CarrierId, trackingNumber: string): boolean {
  return activeRequirements(carrierId, normalizeTrackingNumber(trackingNumber))
    .some(({ field, validator }) => field === 'postcode' && validator === 'internationalPostcode');
}

/** The countries a postcode for this number can be from: any, or the carrier's own when its check reads their shapes. */
export function postcodeCountries(carrierId: CarrierId, trackingNumber: string): readonly string[] {
  return takesAnyPostcode(carrierId, trackingNumber) ? COUNTRIES : carrierInfo(carrierId).countries;
}

/**
 * The country a postcode field starts on: the first of `known` the carrier
 * takes a postcode from (the one given before, the parcel's destination, the
 * device's country), otherwise the carrier's home.
 */
export function initialPostcodeCountry(
  carrierId: CarrierId,
  trackingNumber: string,
  known: readonly (string | null | undefined)[],
): string | null {
  const countries = postcodeCountries(carrierId, trackingNumber);
  return known.find((code): code is string => Boolean(code) && countries.includes(code!)) ?? homePostcodeCountry(carrierId);
}

/** The country sent with the postcode: none for the carrier's home country. */
export function sentPostcodeCountry(carrierId: CarrierId, country: string | null | undefined): string | undefined {
  return country && country !== homePostcodeCountry(carrierId) ? country : undefined;
}

// One field per requirement and country, so callers may compare them as they compare requirements.
const foreignFields = new WeakMap<CarrierInputRequirement, Map<string, CarrierInputRequirement>>();

/**
 * The postcode field for a postcode from `country`. The carrier's own field
 * for its home country, or when it takes only its own countries' shapes; any
 * country's shape otherwise, with that country's example or none.
 */
export function postcodeFieldFor(
  requirement: CarrierInputRequirement,
  carrierId: CarrierId,
  trackingNumber: string,
  country: string | null | undefined,
): CarrierInputRequirement {
  if (requirement.field !== 'dpdPostcode' || !sentPostcodeCountry(carrierId, country)
    || !takesAnyPostcode(carrierId, trackingNumber)) return requirement;
  let fields = foreignFields.get(requirement);
  if (!fields) foreignFields.set(requirement, fields = new Map());
  let field = fields.get(country!);
  if (!field) {
    field = { ...requirement, pattern: ANY_POSTCODE, maxLength: 12, inputMode: 'text',
      placeholder: Object.hasOwn(EXAMPLES, country!) ? EXAMPLES[country!] : '' };
    fields.set(country!, field);
  }
  return field;
}

/**
 * The country stored with the parcel's postcode (`carrier_data.postcode_country`),
 * while the parcel still holds that postcode: a postcode changed since goes without it.
 */
export function storedPostcodeCountry(carrierData: unknown, postcode: string | null | undefined): string | undefined {
  if (!postcode || !carrierData || typeof carrierData !== 'object') return undefined;
  const stored = (carrierData as { postcode_country?: unknown }).postcode_country;
  if (!stored || typeof stored !== 'object') return undefined;
  const { postcode: given, country } = stored as { postcode?: unknown; country?: unknown };
  return given === postcode && typeof country === 'string' && /^[A-Z]{2}$/.test(country) ? country : undefined;
}
