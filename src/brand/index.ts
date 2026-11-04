/** Shared palette and truck inputs for web and native rendering. */
import {
  CARRIER_DECALS as GENERATED_DECALS,
  CARRIER_FAMILIES as GENERATED_FAMILIES,
  CARRIER_PALETTES as GENERATED_PALETTES,
  CARRIER_TRUCK as GENERATED_TRUCK,
} from '../generated/brand';
import paletteData from './palette.json';
import type {
  BrandDerivationStep,
  BrandLitEdge,
  BrandProperty,
  BrandTone,
  CarrierDecal,
  CarrierPalette,
  TruckGeometry,
} from './types';

export * from './types';

/** When a truck's outline lights up on a dark card. */
export const BRAND_LIT_EDGE = paletteData.litEdge as BrandLitEdge;

/** The neutral color for unknown carriers and newly scaffolded entries. */
export const DEFAULT_CARRIER_COLOR: string = paletteData.defaultColor;

/** Stands in for a catalog color that is not a `#rrggbb` literal. */
export const FALLBACK_BRAND_COLOR: string = paletteData.fallbackColor;

/** The nine properties, in the order a palette declares them. */
export const BRAND_PROPERTIES = paletteData.properties as readonly BrandProperty[];

/** How each property is mixed out of the carrier color when none is declared. */
export const BRAND_DERIVATION = paletteData.derivation as readonly BrandDerivationStep[];

/** Carrier ids whose identity belongs to another family (the GLS countries). */
export const CARRIER_FAMILIES = GENERATED_FAMILIES;

/** Explicit palettes, by carrier id. Family members repeat the family's palette. */
export const CARRIER_PALETTES = GENERATED_PALETTES;

/** Declared liveries, by carrier id. Everything else wears `default`. */
export const CARRIER_DECALS = GENERATED_DECALS;

/** One truck, rendered by `CarrierMark` on the web and `CarrierFleetMark` on iOS. */
export const CARRIER_TRUCK: TruckGeometry = GENERATED_TRUCK;

const HEX = /^#[\da-f]{6}$/i;

/** Blends `color` towards `base`; an unusable color falls back to the brand grey-green. */
export function mix(color: string, base: string, amount: number): string {
  const hex = HEX.test(color) ? color : FALLBACK_BRAND_COLOR;
  return '#' + [1, 3, 5].map(offset => Math.round(
    parseInt(hex.slice(offset, offset + 2), 16) * (1 - amount)
    + parseInt(base.slice(offset, offset + 2), 16) * amount,
  ).toString(16).padStart(2, '0')).join('');
}

const linear = (channel: number) => (channel <= 0.04045 ? channel / 12.92 : ((channel + 0.055) / 1.055) ** 2.4);
const encoded = (channel: number) => {
  const clamped = Math.min(1, Math.max(0, channel));
  return clamped <= 0.0031308 ? 12.92 * clamped : 1.055 * clamped ** (1 / 2.4) - 0.055;
};
const channels = (hex: string) => [1, 3, 5].map((offset) => parseInt(hex.slice(offset, offset + 2), 16) / 255);

/** WCAG 2 relative luminance of a `#rrggbb` colour. */
export function luminance(hex: string): number {
  const [red, green, blue] = channels(hex).map(linear);
  return 0.2126 * red + 0.7152 * green + 0.0722 * blue;
}

/** WCAG 2 contrast ratio of two `#rrggbb` colours. */
export function contrast(first: string, second: string): number {
  const [light, dark] = [luminance(first), luminance(second)].sort((a, b) => b - a);
  return (light + 0.05) / (dark + 0.05);
}

/** A `#rrggbb` colour as OKLCH: lightness, chroma, hue in degrees. */
export function oklch(hex: string): [number, number, number] {
  const [red, green, blue] = channels(hex).map(linear);
  const l = Math.cbrt(0.4122214708 * red + 0.5363325363 * green + 0.0514459929 * blue);
  const m = Math.cbrt(0.2119034982 * red + 0.6806995451 * green + 0.1073969566 * blue);
  const s = Math.cbrt(0.0883024619 * red + 0.2817188376 * green + 0.6299787005 * blue);
  const a = 1.9779984951 * l - 2.428592205 * m + 0.4505937099 * s;
  const b = 0.0259040371 * l + 0.7827717662 * m - 0.808675766 * s;
  return [0.2104542553 * l + 0.793617785 * m - 0.0040720468 * s, Math.hypot(a, b), ((Math.atan2(b, a) * 180) / Math.PI + 360) % 360];
}

/** An OKLCH colour as `#rrggbb`, each channel clamped into sRGB. */
export function fromOklch(lightness: number, chroma: number, hue: number): string {
  const a = chroma * Math.cos((hue * Math.PI) / 180);
  const b = chroma * Math.sin((hue * Math.PI) / 180);
  const l = (lightness + 0.3963377774 * a + 0.2158037573 * b) ** 3;
  const m = (lightness - 0.1055613458 * a - 0.0638541728 * b) ** 3;
  const s = (lightness - 0.0894841775 * a - 1.291485548 * b) ** 3;
  return '#' + [
    4.0767416621 * l - 3.3077115913 * m + 0.2309699292 * s,
    -1.2684380046 * l + 2.6097574011 * m - 0.3413193965 * s,
    -0.0041960863 * l - 0.7034186147 * m + 1.707614701 * s,
  ].map((channel) => Math.round(encoded(channel) * 255).toString(16).padStart(2, '0')).join('');
}

/** `color` at the tone's lightness, unless that would make it brighter (see `BrandTone`). */
export function tone(color: string, { lightness, chroma, maxChroma, amber }: BrandTone): string {
  const [, sourceChroma, sourceHue] = oklch(color);
  const hue = sourceHue > amber.from && sourceHue < amber.to ? amber.hue + (sourceHue - amber.from) * amber.slope : sourceHue;
  const toned = fromOklch(lightness, Math.min(maxChroma, sourceChroma * chroma), hue);
  return luminance(toned) > luminance(color) ? color : toned;
}

/** The truck's outline on its dark card: `edge`, unless the body sinks into the card (see `BrandLitEdge`). */
export function litEdge(truck: string, surface: string, edge: string, { below, lift, floor, maxChroma }: BrandLitEdge = BRAND_LIT_EDGE): string {
  if (!HEX.test(truck) || !HEX.test(surface) || contrast(truck, surface) >= below) return edge;
  const [lightness, chroma, hue] = oklch(truck);
  return fromOklch(Math.max(lightness + lift, floor), Math.min(chroma, maxChroma), hue);
}

/**
 * The nine properties for one carrier color. A declared palette wins property
 * by property, so a carrier can pin the two colors a wordmark requires and let
 * the rest follow from its catalog color.
 */
export function carrierBrand(color: string, palette?: Partial<CarrierPalette>): CarrierPalette {
  return Object.fromEntries(BRAND_DERIVATION.map((step) =>
    [step.property, palette?.[step.property] ?? derived(color, step)],
  )) as CarrierPalette;
}

function derived(color: string, step: BrandDerivationStep): string {
  const mixed = mix(color, step.base, step.amount);
  return step.tone ? tone(mixed, step.tone) : mixed;
}

/** The identity a carrier id belongs to: its own, or the family it declares. */
export function carrierBrandFamily(id: string): string {
  return CARRIER_FAMILIES[id] ?? id;
}

/** The livery a carrier id wears. */
export function carrierDecal(id: string): CarrierDecal {
  return CARRIER_DECALS[id] ?? 'default';
}
