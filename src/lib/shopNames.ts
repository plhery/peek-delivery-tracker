/**
 * The names a wait for a shop's parcel shows: the shop, as its tracking page's address names it
 * (`hm.delivery-status.com` is H&M's), and the platform that runs the page.
 */

/** Shops whose address is no plain spelling of their name. */
const SHOP_NAMES: Readonly<Record<string, string>> = {
  hm: 'H&M',
  cos: 'COS',
  stories: '& Other Stories',
  arket: 'ARKET',
  weekday: 'Weekday',
  monki: 'Monki',
  ikea: 'IKEA',
  otto: 'OTTO',
  lidl: 'Lidl',
  dm: 'dm',
};

const PLATFORM_NAMES: Readonly<Record<string, string>> = { parcellab: 'parcelLab' };

/** The shop's name from its address's word (`hm`, `new-yorker`), or null without one. */
export function shopDisplayName(shop: string | null | undefined): string | null {
  const word = shop?.trim().toLowerCase();
  if (!word || !/^[a-z\d][a-z\d-]{0,79}$/.test(word)) return null;
  return SHOP_NAMES[word] ?? word.split('-').filter(Boolean).map((part) => part[0].toUpperCase() + part.slice(1)).join(' ');
}

/** The platform's own spelling of its name. */
export function shopPlatformName(platform: string): string {
  return PLATFORM_NAMES[platform] ?? platform;
}
