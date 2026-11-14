import 'server-only';

import { CARRIER_DEFINITIONS } from 'universal-parcel-scraper';
import type { CarrierId } from '../../types';

/** Second-level labels a country's domains are registered under: "example.co.uk" is one site, not "co.uk"'s. */
const SECOND_LEVELS = new Set(['co', 'com', 'org', 'net', 'gov', 'ac', 'edu', 'or', 'ne']);
/**
 * Shops that also deliver. The catalogue lists their delivery arm on their own domain, but their emails are a
 * shop's: they name the shop's parcels, carried by anyone.
 */
const SHOPS = new Set(['amazon', 'ebay', 'aliexpress', 'allegro', 'ozon', 'wildberries', 'shopee', 'lazada', 'mercadolibre', 'mercadolivre', 'temu', 'shein']);
/** A name short enough to be another company's in another country is not taken across endings. */
const SHORTEST_SHARED_NAME = 3;

/** The domain a site is registered as, and its name: "tracking.example.co.uk" is "example.co.uk", named "example". */
function site(host: string): { domain: string; name: string } | null {
  const labels = host.toLowerCase().split('.').filter(Boolean);
  if (labels.length < 2) return null;
  const count = labels.length >= 3 && labels.at(-1)!.length === 2 && SECOND_LEVELS.has(labels.at(-2)!) ? 3 : 2;
  return { domain: labels.slice(-count).join('.'), name: labels.at(-count)! };
}

function host(url: string | undefined): string | null {
  if (!url) return null;
  try { return new URL(url.replace(/\{[^{}]*\}/g, 'x')).hostname; } catch { return null; }
}

interface CarrierSites {
  byDomain: Map<string, CarrierId[]>;
  byName: Map<string, CarrierId[]>;
}

let sites: CarrierSites | undefined;
/**
 * Each carrier's own sites, as the catalogue names them: where it shows a parcel, its help page and the links it
 * sends. A carrier writes its emails from one of them, or from the same name under another country's ending.
 */
function carrierSites(): CarrierSites {
  if (sites) return sites;
  const byDomain = new Map<string, CarrierId[]>();
  const byName = new Map<string, CarrierId[]>();
  const add = (map: Map<string, CarrierId[]>, key: string, carrier: CarrierId) => {
    const carriers = map.get(key);
    if (!carriers) map.set(key, [carrier]);
    else if (!carriers.includes(carrier)) carriers.push(carrier);
  };
  for (const [id, definition] of Object.entries(CARRIER_DEFINITIONS)) {
    if (id === 'unknown') continue;
    const hosts = [host(definition.trackingUrlTemplate), host(definition.trackingSearchUrl), host(definition.helpUrl),
      ...definition.linkRules.flatMap((rule) => rule.domains)];
    for (const name of hosts) {
      const found = name ? site(name) : null;
      if (!found || SHOPS.has(found.name)) continue;
      add(byDomain, found.domain, id as CarrierId);
      add(byName, found.name, id as CarrierId);
    }
  }
  sites = { byDomain, byName };
  return sites;
}

/**
 * The carriers an email address writes for: those whose own site is its domain, or else has its name under
 * another ending ("dpd.ch" beside "dpd.com"). Empty for a shop, a person and a shop that also delivers.
 */
export function carriersOfAddress(address: string): readonly CarrierId[] {
  const found = site(address.split('@')[1] ?? '');
  if (!found || SHOPS.has(found.name)) return [];
  const known = carrierSites();
  return known.byDomain.get(found.domain) ?? (found.name.length >= SHORTEST_SHARED_NAME ? known.byName.get(found.name) : undefined) ?? [];
}

/** The names the carriers go by, which their emails write where a shop's would stand. */
export function carrierNames(carriers: readonly CarrierId[]): string[] {
  return carriers.flatMap((carrier) => {
    const definition = CARRIER_DEFINITIONS[carrier];
    return definition ? [definition.displayName, ...(definition.aliases ?? [])] : [];
  });
}
