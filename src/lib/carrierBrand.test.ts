import { describe, expect, it } from 'vitest';
import { CARRIERS, carrierInfo } from './carriers';
import type { CarrierId } from 'universal-parcel-scraper';
import { carrierBrand } from './carrierBrand';

describe('carrier branding', () => {
  it('keeps the same GLS identity across country-specific tracking providers', () => {
    const brands = (['gls-ch', 'gls-de', 'gls-fr'] as const).map(id => carrierBrand(carrierInfo(id)));
    expect(brands.every(brand => brand.name === 'GLS' && brand.family === 'gls')).toBe(true);
    expect(brands[0].style).toEqual(brands[1].style);
    expect(brands[1].style).toEqual(brands[2].style);
  });
  it('writes the hand-set names in capitals, UPS too, never a lowercase logo or a bare id', () => {
    expect((['dhl', 'dhl-express', 'gls-de', 'ups', 'usps', 'dpd-de', 'tnt', 'fedex'] as const).map((id) => carrierBrand(carrierInfo(id)).name))
      .toEqual(['DHL', 'DHL', 'GLS', 'UPS', 'USPS', 'DPD Germany', 'TNT', 'FedEx']);
  });
  it('names every carrier in the catalog by its catalog name or a hand-set one, never by its bare id', () => {
    for (const id of Object.keys(CARRIERS) as CarrierId[]) {
      const info = carrierInfo(id);
      const { name } = carrierBrand(info);
      expect(['DHL', 'GLS', 'UPS', info.name], id).toContain(name);
      // Some brands write their catalog name in lowercase, as their id: only those may read as the id.
      if (name === id) expect(info.name, id).toBe(id);
    }
  });
  it('keeps the DHL and UPS truck liveries distinct from their readable text colors', () => {
    expect(carrierBrand(carrierInfo('dhl')).style).toMatchObject({ '--carrier-truck': '#ffcc00', '--carrier-brand-light': '#d40511' });
    expect(carrierBrand(carrierInfo('ups')).style).toMatchObject({ '--carrier-truck': '#573626', '--carrier-accent': '#f5c86b' });
  });
  it('gives other carriers catalog-based colors and preserves their localized names', () => {
    const swiss = carrierInfo('swiss-post', 'fr');
    expect(carrierBrand(swiss).name).toBe(swiss.name);
    expect(carrierBrand(swiss).style['--carrier-truck' as keyof React.CSSProperties]).toBe(swiss.color.toLowerCase());
    const invalid = carrierBrand({ ...swiss, color: 'not-a-color' });
    expect(Object.values(invalid.style).every(value => /^#[\da-f]{6}$/i.test(value))).toBe(true);
  });
});
