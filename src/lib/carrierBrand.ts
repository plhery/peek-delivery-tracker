import type { CSSProperties } from 'react';
import {
  CARRIER_PALETTES,
  DEFAULT_CARRIER_COLOR,
  carrierBrand as carrierPalette,
  carrierBrandFamily,
  carrierDecal,
  litEdge,
} from '../brand';
import type { CarrierInfo } from './carriers';

export { DEFAULT_CARRIER_COLOR, carrierBrandFamily, carrierDecal };

/** The wordmarks we set by hand; every other carrier shows its catalog name. */
const WORDMARKS: Record<string, string> = { dhl: 'DHL', gls: 'GLS', ups: 'ups' };

/**
 * Only the approved wordmarks need special treatment; new carriers use catalog
 * colors. Both the palette and the livery are app data, read
 * through `src/brand`.
 */
export function carrierBrand(carrier: CarrierInfo) {
  const family = carrierBrandFamily(carrier.id);
  const palette = carrierPalette(carrier.color, CARRIER_PALETTES[carrier.id]);
  const style = Object.fromEntries([
    ...Object.entries(palette).map(([property, value]) => [`--carrier-${property}`, value]),
    // The truck's outline on the dark card, which the truck wears in dark only.
    ['--carrier-edge-dark', litEdge(palette.truck, palette['surface-dark'], palette.edge)],
  ]) as CSSProperties;
  return { family, name: WORDMARKS[family] ?? carrier.name, decal: carrierDecal(carrier.id), style };
}
