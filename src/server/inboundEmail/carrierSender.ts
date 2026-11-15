import 'server-only';

import { CARRIER_DEFINITIONS } from 'universal-parcel-scraper';
import type { CarrierId } from '../../types';

/**
 * Second-level labels a country's domains are registered under: "example.co.uk" is one site, not "co.uk"'s, and
 * "example.gob.mx" one of Mexico's government sites, not the government's.
 */
const SECOND_LEVELS = new Set(['co', 'com', 'org', 'net', 'gov', 'gob', 'go', 'gouv', 'ac', 'edu', 'or', 'ne']);
/**
 * Shops that also deliver. The catalogue lists their delivery arm on their own domain, but their emails are a
 * shop's: they name the shop's parcels, carried by anyone.
 */
const SHOPS = new Set(['amazon', 'ebay', 'aliexpress', 'allegro', 'ozon', 'wildberries', 'shopee', 'lazada', 'mercadolibre', 'mercadolivre', 'temu', 'shein']);
/** A name short enough to be another company's in another country is not taken across endings. */
const SHORTEST_SHARED_NAME = 3;
/**
 * Sites that show other companies' pages under their own name: a carrier's page there is not its own site. Paxi's
 * tracking page is on Firebase's web.app, which "web.de" would otherwise share a name with.
 */
const PLATFORMS = new Set(['web.app', 'firebaseapp.com', 'github.io', 'herokuapp.com', 'azurewebsites.net', 'vercel.app',
  'netlify.app', 'pages.dev', '17track.net', 'parcelsapp.com']);
/**
 * Free mail whose addresses are people's though a carrier goes by its domain or its name: Yandex's mail is on Yandex
 * Delivery's domain, Belgium's Skynet is an internet provider. A carrier's mail comes from a host under it, if any.
 */
const PEOPLES_MAIL = new Set(['yandex.ru', 'yandex.com', 'yandex.by', 'yandex.kz', 'yandex.ua', 'ya.ru', 'skynet.be']);

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
  /** The sites under each name. */
  domains: Map<string, Set<string>>;
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
  const domains = new Map<string, Set<string>>();
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
      if (!found || SHOPS.has(found.name) || PLATFORMS.has(found.domain)) continue;
      add(byDomain, found.domain, id as CarrierId);
      add(byName, found.name, id as CarrierId);
      domains.set(found.name, (domains.get(found.name) ?? new Set()).add(found.domain));
    }
  }
  sites = { byDomain, byName, domains };
  return sites;
}

/**
 * The carriers an email address writes for: those whose own site is its domain, or else has its name under
 * another ending ("dpd.ch" beside "dpd.com"). Empty for a shop, a person and a shop that also delivers. A name is
 * taken across endings only as one company's: that of a single site, or of carriers that share their first word
 * ("dpd-fr", "dpd-pl"), not one several posts each write under ("post.ch", "post.at").
 */
export function carriersOfAddress(address: string): readonly CarrierId[] {
  const domain = (address.split('@')[1] ?? '').toLowerCase();
  const found = site(domain);
  if (!found || SHOPS.has(found.name) || PEOPLES_MAIL.has(domain)) return [];
  const known = carrierSites();
  const exact = known.byDomain.get(found.domain);
  if (exact) return exact;
  const named = found.name.length >= SHORTEST_SHARED_NAME ? known.byName.get(found.name) ?? [] : [];
  return known.domains.get(found.name)?.size === 1 || new Set(named.map((carrier) => carrier.split('-')[0])).size === 1 ? named : [];
}

/** The names the carriers go by, which their emails write where a shop's would stand. */
export function carrierNames(carriers: readonly CarrierId[]): string[] {
  return carriers.flatMap((carrier) => {
    const definition = CARRIER_DEFINITIONS[carrier];
    return definition ? [definition.displayName, ...(definition.aliases ?? [])] : [];
  });
}
