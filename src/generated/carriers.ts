// Generated from content/carriers by scripts/generate-guides.mjs. Do not edit.
import type { Locale, PageLanguage } from '../lib/locale';

/**
 * A carrier's page in one language: the carrier's id, its id in Universal Parcel Scraper's catalog, its name
 * and brand colour in every language, and the page's address and title in this one.
 */
export interface CarrierLink { id: string; catalog: string; slug: string; title: string; name: string; color: string }

export const CARRIER_LINKS: Record<PageLanguage, readonly CarrierLink[]> = {
  "en": [
    {
      "id": "quickpac",
      "catalog": "quickpac",
      "slug": "quickpac-tracking",
      "title": "Quickpac tracking: 18-digit number, statuses and contact",
      "name": "Quickpac",
      "color": "#ed1c24"
    },
    {
      "id": "planzer",
      "catalog": "planzer",
      "slug": "planzer-tracking",
      "title": "Planzer tracking: what each status means and what to do",
      "name": "Planzer",
      "color": "#e30613"
    },
    {
      "id": "colis-prive",
      "catalog": "colis-prive",
      "slug": "colis-prive-tracking",
      "title": "Colis Privé tracking: French statuses and what to do",
      "name": "Colis Privé",
      "color": "#aa78ff"
    },
    {
      "id": "brt",
      "catalog": "brt",
      "slug": "brt-tracking",
      "title": "BRT tracking: numbers, statuses and missed deliveries",
      "name": "BRT",
      "color": "#dc0032"
    },
    {
      "id": "correos-express",
      "catalog": "correos-express",
      "slug": "correos-express-tracking",
      "title": "Correos Express tracking: statuses in English and contact",
      "name": "Correos Express",
      "color": "#ffcd00"
    },
    {
      "id": "nacex",
      "catalog": "nacex",
      "slug": "nacex-tracking",
      "title": "NACEX tracking: number, statuses in English and contact",
      "name": "NACEX",
      "color": "#fe5000"
    },
    {
      "id": "yunexpress",
      "catalog": "yunexpress",
      "slug": "yunexpress-tracking",
      "title": "YunExpress tracking: YT numbers, statuses and who delivers",
      "name": "YunExpress",
      "color": "#008284"
    },
    {
      "id": "cainiao",
      "catalog": "aliexpress",
      "slug": "cainiao-tracking",
      "title": "Cainiao tracking: numbers, statuses and who to contact",
      "name": "Cainiao",
      "color": "#e62e04"
    },
    {
      "id": "mondial-relay",
      "catalog": "mondial-relay",
      "slug": "mondial-relay-tracking",
      "title": "Mondial Relay tracking: statuses in English and pickup",
      "name": "Mondial Relay",
      "color": "#ef3026"
    },
    {
      "id": "gls-de",
      "catalog": "gls-de",
      "slug": "gls-tracking-germany",
      "title": "GLS tracking in Germany: statuses, ParcelShops and contact",
      "name": "GLS Germany",
      "color": "#ffdd00"
    },
    {
      "id": "dpd-de",
      "catalog": "dpd-de",
      "slug": "dpd-tracking-germany",
      "title": "DPD tracking in Germany: what statuses mean and what to do",
      "name": "DPD Germany",
      "color": "#dc0032"
    },
    {
      "id": "hermes-de",
      "catalog": "hermes-de",
      "slug": "hermes-tracking",
      "title": "Hermes tracking: German statuses explained in English",
      "name": "Hermes",
      "color": "#0091cd"
    },
    {
      "id": "gls-ch",
      "catalog": "gls-ch",
      "slug": "gls-switzerland-tracking",
      "title": "GLS Switzerland tracking: statuses, Swiss Post and contact",
      "name": "GLS Switzerland",
      "color": "#ffdd00"
    },
    {
      "id": "dpd-ch",
      "catalog": "dpd",
      "slug": "dpd-switzerland-tracking",
      "title": "DPD Switzerland tracking: statuses, delivery and contact",
      "name": "DPD Switzerland",
      "color": "#dc0032"
    },
    {
      "id": "inpost",
      "catalog": "inpost",
      "slug": "inpost-tracking",
      "title": "InPost tracking: numbers, statuses and locker pickup",
      "name": "InPost",
      "color": "#ffcc05"
    },
    {
      "id": "paack",
      "catalog": "paack",
      "slug": "paack-tracking",
      "title": "Paack tracking: order number, postcode and delivery slots",
      "name": "Paack",
      "color": "#5b2dee"
    },
    {
      "id": "evri",
      "catalog": "evri-uk",
      "slug": "evri-tracking",
      "title": "Evri tracking: statuses, ParcelShops and missed deliveries",
      "name": "Evri",
      "color": "#00014d"
    },
    {
      "id": "chronopost",
      "catalog": "chronopost",
      "slug": "chronopost-tracking",
      "title": "Chronopost tracking: numbers, statuses and pickup points",
      "name": "Chronopost",
      "color": "#0056a7"
    },
    {
      "id": "relais-colis",
      "catalog": "relais-colis",
      "slug": "relais-colis-tracking",
      "title": "Relais Colis tracking: statuses, pickup and contact",
      "name": "Relais Colis",
      "color": "#7bb928"
    },
    {
      "id": "poste-italiane",
      "catalog": "poste-italiane",
      "slug": "poste-italiane-tracking",
      "title": "Poste Italiane tracking: numbers, statuses and pickup",
      "name": "Poste Italiane",
      "color": "#eedc00"
    },
    {
      "id": "seur",
      "catalog": "seur",
      "slug": "seur-tracking",
      "title": "SEUR tracking: numbers, statuses and pickup points",
      "name": "SEUR",
      "color": "#0063b3"
    },
    {
      "id": "mrw",
      "catalog": "mrw",
      "slug": "mrw-tracking",
      "title": "MRW tracking: number, statuses in English and contact",
      "name": "MRW",
      "color": "#19133b"
    },
    {
      "id": "ctt",
      "catalog": "ctt",
      "slug": "ctt-tracking",
      "title": "CTT tracking: numbers, statuses and pickup in Portugal",
      "name": "CTT",
      "color": "#df0024"
    },
    {
      "id": "packeta",
      "catalog": "packeta",
      "slug": "packeta-tracking",
      "title": "Packeta tracking: Z numbers, statuses and pickup points",
      "name": "Packeta",
      "color": "#ba1b02"
    },
    {
      "id": "dpd-pl",
      "catalog": "dpd-pl",
      "slug": "dpd-poland-tracking",
      "title": "DPD Poland tracking: statuses, pickup points and contact",
      "name": "DPD Poland",
      "color": "#dc0032"
    },
    {
      "id": "4px",
      "catalog": "four-px",
      "slug": "4px-tracking",
      "title": "4PX tracking: numbers, statuses and who to contact",
      "name": "4PX",
      "color": "#005add"
    },
    {
      "id": "swiss-post",
      "catalog": "swiss-post",
      "slug": "swiss-post-tracking",
      "title": "Swiss Post tracking: statuses, collection notes and contact",
      "name": "Swiss Post",
      "color": "#ffcc00"
    },
    {
      "id": "ctt-express",
      "catalog": "ctt-express",
      "slug": "ctt-express-tracking",
      "title": "CTT Express tracking: statuses in English and contact",
      "name": "CTT Express",
      "color": "#df0024"
    },
    {
      "id": "dpd-fr",
      "catalog": "dpd-fr",
      "slug": "dpd-france-tracking",
      "title": "DPD France tracking: numbers, statuses and Pickup relays",
      "name": "DPD France",
      "color": "#dc0032"
    },
    {
      "id": "gls-fr",
      "catalog": "gls-fr",
      "slug": "gls-france-tracking",
      "title": "GLS France tracking: statuses, Relais GLS and contact",
      "name": "GLS France",
      "color": "#ffdd00"
    },
    {
      "id": "gls-it",
      "catalog": "gls-it",
      "slug": "gls-italy-tracking",
      "title": "GLS Italy tracking: numbers, statuses and missed deliveries",
      "name": "GLS Italy",
      "color": "#ffdd00"
    },
    {
      "id": "poczta-polska",
      "catalog": "poczta-polska",
      "slug": "poczta-polska-tracking",
      "title": "Poczta Polska tracking: numbers, statuses and pickups",
      "name": "Poczta Polska",
      "color": "#c43c2b"
    },
    {
      "id": "orlen-paczka",
      "catalog": "orlen-paczka",
      "slug": "orlen-paczka-tracking",
      "title": "ORLEN Paczka tracking: numbers, statuses and pickups",
      "name": "ORLEN Paczka",
      "color": "#db3830"
    },
    {
      "id": "austrian-post",
      "catalog": "austrian-post",
      "slug": "austrian-post-tracking",
      "title": "Austrian Post tracking: numbers, statuses and pickup",
      "name": "Austrian Post",
      "color": "#ffdc00"
    },
    {
      "id": "tipsa",
      "catalog": "tipsa",
      "slug": "tipsa-tracking",
      "title": "TIPSA tracking: numbers, statuses and missed deliveries",
      "name": "TIPSA",
      "color": "#001e62"
    },
    {
      "id": "yanwen",
      "catalog": "yanwen",
      "slug": "yanwen-tracking",
      "title": "Yanwen tracking number: UL…YP format and who delivers",
      "name": "Yanwen",
      "color": "#8cbe1e"
    },
    {
      "id": "an-post",
      "catalog": "an-post",
      "slug": "an-post-tracking",
      "title": "An Post tracking: statuses, customs and contact",
      "name": "An Post",
      "color": "#00a76a"
    },
    {
      "id": "vinted-go",
      "catalog": "vinted-go",
      "slug": "vinted-go-tracking",
      "title": "Vinted Go tracking: statuses, lockers and who to contact",
      "name": "Vinted Go",
      "color": "#004654"
    },
    {
      "id": "amazon-logistics",
      "catalog": "amazon-logistics",
      "slug": "amazon-logistics-tracking",
      "title": "Amazon Logistics tracking: where to look and what to do",
      "name": "Amazon Logistics",
      "color": "#ff9900"
    },
    {
      "id": "dpd-uk",
      "catalog": "dpd-uk",
      "slug": "dpd-uk-tracking",
      "title": "DPD UK tracking: statuses, Pickup shops and contact",
      "name": "DPD UK",
      "color": "#dc0032"
    },
    {
      "id": "parcelforce",
      "catalog": "parcelforce",
      "slug": "parcelforce-worldwide-tracking",
      "title": "Parcelforce Worldwide tracking: statuses and customs",
      "name": "Parcelforce Worldwide",
      "color": "#9d0f11"
    },
    {
      "id": "bpost",
      "catalog": "bpost",
      "slug": "bpost-tracking",
      "title": "bpost tracking: statuses, Pick-up points and contact",
      "name": "bpost",
      "color": "#ef2637"
    },
    {
      "id": "dpd-ie",
      "catalog": "dpd-ie",
      "slug": "dpd-ireland-tracking",
      "title": "DPD Ireland tracking: statuses, Pickup points and contact",
      "name": "DPD Ireland",
      "color": "#dc0032"
    },
    {
      "id": "gls-es",
      "catalog": "gls-es",
      "slug": "gls-spain-tracking",
      "title": "GLS Spain tracking: statuses, Parcel Shops and contact",
      "name": "GLS Spain",
      "color": "#ffdd00"
    },
    {
      "id": "dhl-ecommerce-es",
      "catalog": "dhl-ecommerce-es",
      "slug": "dhl-ecommerce-iberia-tracking",
      "title": "DHL eCommerce Iberia tracking: statuses and ServicePoints",
      "name": "DHL eCommerce Iberia",
      "color": "#ffcc00"
    },
    {
      "id": "dhl-ecommerce-pl",
      "catalog": "dhl-ecommerce-pl",
      "slug": "dhl-ecommerce-poland-tracking",
      "title": "DHL eCommerce Poland tracking: statuses, pickup and help",
      "name": "DHL eCommerce Poland",
      "color": "#ffcc00"
    },
    {
      "id": "dhl-ecommerce-uk",
      "catalog": "dhl-ecommerce-uk",
      "slug": "dhl-ecommerce-uk-tracking",
      "title": "DHL eCommerce UK tracking: statuses, ServicePoints, contact",
      "name": "DHL eCommerce UK",
      "color": "#ffcc00"
    },
    {
      "id": "gofo-fr",
      "catalog": "gofo-fr",
      "slug": "gofo-france-tracking",
      "title": "GOFO France tracking: GFFR numbers, statuses and help",
      "name": "GOFO France",
      "color": "#fc4c02"
    },
    {
      "id": "gofo-it",
      "catalog": "gofo-it",
      "slug": "gofo-italy-tracking",
      "title": "GOFO Italy tracking: GFIT numbers, statuses and complaints",
      "name": "GOFO Italy",
      "color": "#fc4c02"
    },
    {
      "id": "speedpak",
      "catalog": "speedpak",
      "slug": "speedpak-tracking",
      "title": "SpeedPAK tracking: eBay parcels, statuses and who to ask",
      "name": "SpeedPAK",
      "color": "#ee7532"
    },
    {
      "id": "asendia",
      "catalog": "asendia",
      "slug": "asendia-tracking",
      "title": "Asendia tracking: statuses, delivery and who to contact",
      "name": "Asendia",
      "color": "#005d69"
    },
    {
      "id": "colisweb",
      "catalog": "colisweb",
      "slug": "colisweb-tracking",
      "title": "Colisweb tracking: French messages and delivery slots",
      "name": "Colisweb",
      "color": "#15cbe3"
    },
    {
      "id": "ciblex",
      "catalog": "ciblex",
      "slug": "ciblex-tracking",
      "title": "Ciblex tracking: numbers, French statuses and contact",
      "name": "Ciblex",
      "color": "#0160ab"
    },
    {
      "id": "hermes-einrichtungs-service",
      "catalog": "hermes",
      "slug": "hermes-einrichtungs-service-tracking",
      "title": "Hermes Einrichtungs-Service tracking: furniture deliveries",
      "name": "Hermes Einrichtungs-Service",
      "color": "#0091cd"
    },
    {
      "id": "landmark-global",
      "catalog": "landmark-global",
      "slug": "landmark-global-tracking",
      "title": "Landmark Global tracking: statuses, partners and contacts",
      "name": "Landmark Global",
      "color": "#e62337"
    },
    {
      "id": "dhl-ecommerce",
      "catalog": "dhl-ecommerce",
      "slug": "dhl-ecommerce-tracking",
      "title": "DHL eCommerce tracking: GM numbers, statuses and contacts",
      "name": "DHL eCommerce",
      "color": "#ffcc00"
    },
    {
      "id": "allegro-one",
      "catalog": "allegro-one",
      "slug": "allegro-one-tracking",
      "title": "Allegro One tracking: One Box, One Punkt and One Kurier",
      "name": "Allegro One",
      "color": "#006d57"
    },
    {
      "id": "cacesa",
      "catalog": "cacesa",
      "slug": "cacesa-tracking",
      "title": "CACESA tracking: customs, statuses and who delivers",
      "name": "CACESA",
      "color": "#C9032D"
    },
    {
      "id": "sunyou",
      "catalog": "sunyou",
      "slug": "sunyou-tracking",
      "title": "SunYou tracking: SY numbers, statuses and who delivers",
      "name": "SunYou",
      "color": "#02a232"
    },
    {
      "id": "amazon-shipping",
      "catalog": "amazon-shipping",
      "slug": "amazon-shipping-tracking",
      "title": "Amazon Shipping tracking: numbers, problems and help",
      "name": "Amazon Shipping",
      "color": "#ff9900"
    },
    {
      "id": "postnord",
      "catalog": "postnord",
      "slug": "postnord-tracking",
      "title": "PostNord tracking: numbers, statuses and pickup times",
      "name": "PostNord",
      "color": "#00a0d6"
    },
    {
      "id": "bring",
      "catalog": "bring-posten",
      "slug": "bring-tracking",
      "title": "Bring tracking: numbers, statuses and pickup deadlines",
      "name": "Bring",
      "color": "#56b529"
    },
    {
      "id": "posti",
      "catalog": "posti",
      "slug": "posti-tracking",
      "title": "Posti tracking: item ID, statuses, lockers and contact",
      "name": "Posti",
      "color": "#ff8000"
    },
    {
      "id": "omniva",
      "catalog": "omniva",
      "slug": "omniva-tracking",
      "title": "Omniva tracking: codes, statuses and parcel machines",
      "name": "Omniva",
      "color": "#f86300"
    },
    {
      "id": "ppl",
      "catalog": "ppl",
      "slug": "ppl-tracking",
      "title": "PPL tracking: shipment numbers, statuses and Parcelshops",
      "name": "PPL",
      "color": "#004b93"
    },
    {
      "id": "box-now",
      "catalog": "box-now",
      "slug": "box-now-tracking",
      "title": "BOX NOW tracking: lockers, PIN codes and statuses",
      "name": "BOX NOW",
      "color": "#44d62c"
    },
    {
      "id": "dhl-ecommerce-nl",
      "catalog": "dhl-ecommerce-nl",
      "slug": "dhl-ecommerce-netherlands-tracking",
      "title": "DHL eCommerce Netherlands tracking: statuses and contact",
      "name": "DHL eCommerce Netherlands",
      "color": "#ffcc00"
    },
    {
      "id": "heppner",
      "catalog": "heppner",
      "slug": "heppner-tracking",
      "title": "Heppner tracking: number, postcode and delivery slots",
      "name": "Heppner",
      "color": "#005baa"
    },
    {
      "id": "c-chez-vous",
      "catalog": "c-chez-vous",
      "slug": "c-chez-vous-tracking",
      "title": "C Chez Vous tracking: order number, statuses and contact",
      "name": "C chez vous",
      "color": "#e50046"
    },
    {
      "id": "geodis",
      "catalog": "geodis",
      "slug": "geodis-tracking",
      "title": "GEODIS tracking: 1G number, statuses and delivery options",
      "name": "GEODIS",
      "color": "#3100e6"
    },
    {
      "id": "delivengo",
      "catalog": "delivengo",
      "slug": "delivengo-tracking",
      "title": "Delivengo tracking: follow a La Poste parcel from France",
      "name": "Delivengo",
      "color": "#ffcf00"
    },
    {
      "id": "ecoscooting",
      "catalog": "ecoscooting",
      "slug": "ecoscooting-tracking",
      "title": "Ecoscooting tracking: statuses, pickup points and contact",
      "name": "Ecoscooting",
      "color": "#3fa7ff"
    },
    {
      "id": "swiss-post-cargo",
      "catalog": "swiss-post-cargo",
      "slug": "swiss-post-cargo-tracking",
      "title": "Swiss Post Cargo tracking: statuses, delivery and contact",
      "name": "Swiss Post Cargo",
      "color": "#ffcc00"
    },
    {
      "id": "dachser",
      "catalog": "dachser",
      "slug": "dachser-tracking",
      "title": "Dachser tracking in Spain and Portugal: link and statuses",
      "name": "Dachser",
      "color": "#005ca9"
    },
    {
      "id": "meest",
      "catalog": "meest",
      "slug": "meest-tracking",
      "title": "Meest tracking: numbers, statuses and contacts",
      "name": "Meest",
      "color": "#0061af"
    },
    {
      "id": "nova-poshta",
      "catalog": "nova-poshta",
      "slug": "nova-poshta-tracking",
      "title": "Nova Poshta tracking: numbers, statuses and branches",
      "name": "Nova Poshta",
      "color": "#e30613"
    },
    {
      "id": "sameday",
      "catalog": "sameday",
      "slug": "sameday-tracking",
      "title": "Sameday tracking: AWB, easybox and statuses explained",
      "name": "Sameday",
      "color": "#e92427"
    },
    {
      "id": "speedy",
      "catalog": "speedy",
      "slug": "speedy-tracking",
      "title": "Speedy tracking: waybill number, statuses and lockers",
      "name": "Speedy",
      "color": "#dc0032"
    },
    {
      "id": "cargus",
      "catalog": "cargus",
      "slug": "cargus-tracking",
      "title": "Cargus tracking: AWB number, statuses and SHIP & GO",
      "name": "Cargus",
      "color": "#f68121"
    },
    {
      "id": "fan-courier",
      "catalog": "fan-courier",
      "slug": "fan-courier-tracking",
      "title": "FAN Courier tracking: AWB numbers, statuses and FANbox",
      "name": "FAN Courier",
      "color": "#0f2345"
    },
    {
      "id": "econt",
      "catalog": "econt",
      "slug": "econt-tracking",
      "title": "Econt tracking: shipment number, statuses and offices",
      "name": "Econt",
      "color": "#234182"
    },
    {
      "id": "ceska-posta",
      "catalog": "ceska-posta",
      "slug": "ceska-posta-czech-post-tracking",
      "title": "Česká pošta (Czech Post) tracking: numbers and statuses",
      "name": "Česká pošta",
      "color": "#002776"
    },
    {
      "id": "express-one",
      "catalog": "express-one",
      "slug": "express-one-tracking",
      "title": "Express One tracking: statuses, parcel points, contacts",
      "name": "Express One",
      "color": "#ec6500"
    },
    {
      "id": "foxpost",
      "catalog": "foxpost",
      "slug": "foxpost-tracking",
      "title": "Foxpost tracking: statuses, lockers and contacts",
      "name": "Foxpost",
      "color": "#b70100"
    },
    {
      "id": "slovak-parcel-service",
      "catalog": "slovak-parcel-service",
      "slug": "slovak-parcel-service-tracking",
      "title": "Slovak Parcel Service tracking: statuses and what to do",
      "name": "Slovak Parcel Service",
      "color": "#f58220"
    },
    {
      "id": "slovenska-posta",
      "catalog": "slovenska-posta",
      "slug": "slovenska-posta-tracking",
      "title": "Slovenská pošta tracking: Slovak Post numbers and statuses",
      "name": "Slovenská pošta",
      "color": "#f9bb00"
    },
    {
      "id": "posta-slovenije",
      "catalog": "posta-slovenije",
      "slug": "posta-slovenije-tracking",
      "title": "Pošta Slovenije tracking: statuses, pickup and contacts",
      "name": "Pošta Slovenije",
      "color": "#ffc600"
    },
    {
      "id": "hrvatska-posta",
      "catalog": "hrvatska-posta",
      "slug": "hrvatska-posta-tracking",
      "title": "Hrvatska pošta tracking: statuses, lockers and contacts",
      "name": "Hrvatska pošta",
      "color": "#ffd100"
    },
    {
      "id": "posta-srbije",
      "catalog": "posta-srbije",
      "slug": "posta-srbije-serbian-post-tracking",
      "title": "Pošta Srbije: Serbian Post tracking, statuses and help",
      "name": "Pošta Srbije",
      "color": "#fbba00"
    },
    {
      "id": "venipak",
      "catalog": "venipak",
      "slug": "venipak-tracking",
      "title": "Venipak tracking: statuses, lockers and missed deliveries",
      "name": "Venipak",
      "color": "#9339f2"
    },
    {
      "id": "lietuvos-pastas",
      "catalog": "lietuvos-pastas",
      "slug": "lietuvos-pastas-lithuania-post-tracking",
      "title": "Lietuvos paštas: Lithuania Post tracking, statuses, lockers",
      "name": "Lietuvos paštas",
      "color": "#fedd00"
    },
    {
      "id": "latvijas-pasts",
      "catalog": "latvijas-pasts",
      "slug": "latvijas-pasts-tracking",
      "title": "Latvijas Pasts tracking: numbers, statuses and lockers",
      "name": "Latvijas Pasts",
      "color": "#0c72ba"
    },
    {
      "id": "dao",
      "catalog": "dao",
      "slug": "dao-tracking",
      "title": "DAO tracking: statuses, daoSHOP and missed deliveries",
      "name": "DAO",
      "color": "#e30613"
    },
    {
      "id": "helthjem",
      "catalog": "helthjem",
      "slug": "helthjem-tracking",
      "title": "Helthjem tracking: statuses, pickup points and contact",
      "name": "Helthjem",
      "color": "#ffb202"
    },
    {
      "id": "matkahuolto",
      "catalog": "matkahuolto",
      "slug": "matkahuolto-tracking",
      "title": "Matkahuolto tracking: statuses, pickup points and contact",
      "name": "Matkahuolto",
      "color": "#001e60"
    },
    {
      "id": "ukrposhta",
      "catalog": "ukrposhta",
      "slug": "ukrposhta-tracking",
      "title": "Ukrposhta tracking: Ukraine Post numbers and statuses",
      "name": "Ukrposhta",
      "color": "#ffbe0c"
    },
    {
      "id": "acs-courier",
      "catalog": "acs-courier",
      "slug": "acs-courier-tracking",
      "title": "ACS Courier tracking: numbers, statuses and contacts",
      "name": "ACS Courier",
      "color": "#e42229"
    },
    {
      "id": "elta",
      "catalog": "elta",
      "slug": "elta-hellenic-post-tracking",
      "title": "ELTA Hellenic Post tracking: statuses, pickup, contacts",
      "name": "ELTA Hellenic Post",
      "color": "#0063a9"
    },
    {
      "id": "geniki-taxydromiki",
      "catalog": "geniki-taxydromiki",
      "slug": "geniki-taxydromiki-tracking",
      "title": "Geniki Taxydromiki tracking: vouchers, statuses, contacts",
      "name": "Geniki Taxydromiki",
      "color": "#004fa0"
    },
    {
      "id": "cyprus-post",
      "catalog": "cyprus-post",
      "slug": "cyprus-post-tracking",
      "title": "Cyprus Post tracking: numbers, statuses and customs",
      "name": "Cyprus Post",
      "color": "#e4a50e"
    },
    {
      "id": "post-luxembourg",
      "catalog": "post-luxembourg",
      "slug": "post-luxembourg-tracking",
      "title": "POST Luxembourg tracking: numbers, statuses and contacts",
      "name": "POST Luxembourg",
      "color": "#008736"
    },
    {
      "id": "maltapost",
      "catalog": "maltapost",
      "slug": "maltapost-tracking",
      "title": "MaltaPost tracking: numbers, statuses and collection",
      "name": "MaltaPost",
      "color": "#d43b2c"
    },
    {
      "id": "whistl",
      "catalog": "whistl",
      "slug": "whistl-tracking",
      "title": "Whistl tracking: who delivers your parcel and what to do",
      "name": "Whistl",
      "color": "#fd4f00"
    },
    {
      "id": "xdp",
      "catalog": "xdp",
      "slug": "xdp-tracking",
      "title": "XDP tracking: consignment numbers, statuses and delivery",
      "name": "XDP",
      "color": "#53b6ed"
    },
    {
      "id": "apc-overnight",
      "catalog": "apc-overnight",
      "slug": "apc-overnight-tracking",
      "title": "APC Overnight tracking: numbers, statuses, missed deliveries",
      "name": "APC Overnight",
      "color": "#243269"
    },
    {
      "id": "ups",
      "catalog": "ups",
      "slug": "ups-tracking",
      "title": "UPS tracking: numbers, statuses and missed deliveries",
      "name": "UPS",
      "color": "#351c15"
    },
    {
      "id": "fedex",
      "catalog": "fedex",
      "slug": "fedex-tracking",
      "title": "FedEx tracking: numbers, statuses and missed deliveries",
      "name": "FedEx",
      "color": "#4d148c"
    },
    {
      "id": "dhl-express",
      "catalog": "dhl-express",
      "slug": "dhl-express-tracking",
      "title": "DHL Express tracking: waybill numbers and statuses",
      "name": "DHL Express",
      "color": "#ffcc00"
    },
    {
      "id": "dhl",
      "catalog": "dhl",
      "slug": "dhl-paket-tracking",
      "title": "DHL Paket tracking: numbers, statuses and pickup help",
      "name": "DHL Paket",
      "color": "#ffcc00"
    },
    {
      "id": "la-poste",
      "catalog": "la-poste",
      "slug": "la-poste-colissimo-tracking",
      "title": "La Poste / Colissimo tracking: numbers and statuses",
      "name": "La Poste / Colissimo",
      "color": "#ffcf00"
    },
    {
      "id": "correos-spain",
      "catalog": "correos-spain",
      "slug": "correos-tracking",
      "title": "Correos tracking: Spain's post, numbers and statuses",
      "name": "Correos",
      "color": "#ffcd00"
    },
    {
      "id": "royal-mail",
      "catalog": "royal-mail",
      "slug": "royal-mail-tracking",
      "title": "Royal Mail tracking: numbers, statuses and redelivery",
      "name": "Royal Mail",
      "color": "#da202a"
    },
    {
      "id": "usps",
      "catalog": "usps",
      "slug": "usps-tracking",
      "title": "USPS tracking: numbers, statuses and missed deliveries",
      "name": "USPS",
      "color": "#004b87"
    },
    {
      "id": "canada-post",
      "catalog": "canada-post",
      "slug": "canada-post-tracking",
      "title": "Canada Post tracking: numbers, statuses and notice cards",
      "name": "Canada Post",
      "color": "#e4002b"
    },
    {
      "id": "purolator",
      "catalog": "purolator",
      "slug": "purolator-tracking",
      "title": "Purolator tracking: PIN, statuses and missed deliveries",
      "name": "Purolator",
      "color": "#da291c"
    },
    {
      "id": "intelcom",
      "catalog": "intelcom",
      "slug": "intelcom-dragonfly-tracking",
      "title": "Intelcom / Dragonfly tracking: numbers, statuses, contact",
      "name": "Intelcom / Dragonfly",
      "color": "#13a58f"
    },
    {
      "id": "canpar",
      "catalog": "canpar",
      "slug": "canpar-tracking",
      "title": "Canpar tracking: numbers, statuses and missed deliveries",
      "name": "Canpar",
      "color": "#003b70"
    },
    {
      "id": "uniuni",
      "catalog": "uniuni",
      "slug": "uniuni-tracking",
      "title": "UniUni tracking: statuses, missed deliveries and contact",
      "name": "UniUni",
      "color": "#f68841"
    },
    {
      "id": "ontrac",
      "catalog": "ontrac",
      "slug": "ontrac-tracking",
      "title": "OnTrac tracking: numbers, statuses and missed deliveries",
      "name": "OnTrac",
      "color": "#cc2027"
    },
    {
      "id": "lso",
      "catalog": "lso",
      "slug": "lso-tracking",
      "title": "LSO tracking: airbill numbers, statuses and delivery help",
      "name": "LSO",
      "color": "#0047ba"
    },
    {
      "id": "gofo",
      "catalog": "gofo",
      "slug": "gofo-express-tracking",
      "title": "GOFO Express tracking: statuses, photos and support",
      "name": "GOFO Express",
      "color": "#fc4c02"
    },
    {
      "id": "speedx",
      "catalog": "speedx",
      "slug": "speedx-tracking",
      "title": "SpeedX tracking: statuses, missed deliveries and contact",
      "name": "SpeedX",
      "color": "#1aa3ff"
    },
    {
      "id": "australia-post",
      "catalog": "australia-post",
      "slug": "australia-post-tracking",
      "title": "Australia Post tracking: numbers, statuses and lockers",
      "name": "Australia Post",
      "color": "#dc1928"
    },
    {
      "id": "aramex-au",
      "catalog": "aramex-au",
      "slug": "aramex-australia-tracking",
      "title": "Aramex Australia tracking: numbers, statuses, contacts",
      "name": "Aramex Australia",
      "color": "#dc291e"
    },
    {
      "id": "couriersplease",
      "catalog": "couriersplease",
      "slug": "couriersplease-tracking",
      "title": "CouriersPlease tracking: numbers, statuses and help",
      "name": "CouriersPlease",
      "color": "#c21e36"
    },
    {
      "id": "nz-post",
      "catalog": "nz-post",
      "slug": "nz-post-tracking",
      "title": "NZ Post tracking: numbers, statuses and missed parcels",
      "name": "NZ Post",
      "color": "#d8291c"
    },
    {
      "id": "aramex-nz",
      "catalog": "aramex-nz",
      "slug": "aramex-new-zealand-tracking-number",
      "title": "Aramex New Zealand tracking number: statuses, contacts",
      "name": "Aramex New Zealand",
      "color": "#dc291e"
    },
    {
      "id": "magyar-posta",
      "catalog": "magyar-posta",
      "slug": "magyar-posta-tracking",
      "title": "Magyar Posta tracking: Hungarian statuses in English",
      "name": "Magyar Posta",
      "color": "#346631"
    },
    {
      "id": "shiprocket",
      "catalog": "shiprocket",
      "slug": "shiprocket-tracking",
      "title": "Shiprocket tracking: AWB numbers, statuses and RTO",
      "name": "Shiprocket",
      "color": "#735ae5"
    },
    {
      "id": "first-line",
      "catalog": "first-line",
      "slug": "first-line-tracking",
      "title": "FIRST LINE tracking: 1ST numbers, statuses and delivery",
      "name": "FIRST LINE",
      "color": "#55207a"
    },
    {
      "id": "j-and-t-de",
      "catalog": "j-and-t-de",
      "slug": "jt-express-germany-tracking",
      "title": "J&T Express Germany tracking: JTDE numbers and statuses",
      "name": "J&T Express Germany",
      "color": "#e60000"
    },
    {
      "id": "kun-express",
      "catalog": "kun-express",
      "slug": "kun-express-tracking",
      "title": "Kun Express tracking: KUN numbers, statuses and USPS",
      "name": "Kun Express",
      "color": "#0761ff"
    },
    {
      "id": "juxi-express",
      "catalog": "juxi-express",
      "slug": "juxi-express-tracking",
      "title": "Juxi Express tracking: JXC numbers, delivery and help",
      "name": "Juxi Express",
      "color": "#e2393c"
    },
    {
      "id": "jcex",
      "catalog": "jcex",
      "slug": "jcex-tracking",
      "title": "JCEX tracking: JCZ numbers, statuses and who delivers",
      "name": "JCEX",
      "color": "#0080cc"
    },
    {
      "id": "lingdong",
      "catalog": "lingdong",
      "slug": "lingdong-tracking",
      "title": "Lingdong International tracking: SZLD numbers and statuses",
      "name": "Lingdong International",
      "color": "#0171bb"
    },
    {
      "id": "j-and-t-au",
      "catalog": "j-and-t-au",
      "slug": "jt-express-australia-tracking",
      "title": "J&T Express Australia tracking: JTAU numbers and statuses",
      "name": "J&T Express Australia",
      "color": "#e60000"
    },
    {
      "id": "china-post",
      "catalog": "china-post",
      "slug": "china-post-tracking",
      "title": "China Post tracking: number, statuses and who delivers",
      "name": "China Post",
      "color": "#006d46"
    },
    {
      "id": "fedex-pl",
      "catalog": "fedex-pl",
      "slug": "fedex-poland-tracking",
      "title": "FedEx Poland tracking: 13-digit numbers and statuses",
      "name": "FedEx Poland",
      "color": "#4d148c"
    },
    {
      "id": "techlink",
      "catalog": "techlink",
      "slug": "techlink-tracking",
      "title": "Techlink tracking: ZHPHD numbers, statuses and help",
      "name": "Techlink",
      "color": "#e73547"
    },
    {
      "id": "z-leading",
      "catalog": "z-leading",
      "slug": "z-leading-tracking",
      "title": "Z-Leading (Zhongling) tracking: numbers, delivery and help",
      "name": "Z-Leading",
      "color": "#005ab0"
    },
    {
      "id": "imile",
      "catalog": "imile",
      "slug": "imile-tracking",
      "title": "iMile tracking: numbers, statuses and who delivers",
      "name": "iMile",
      "color": "#0032a0"
    },
    {
      "id": "postnl",
      "catalog": "spring-gds",
      "slug": "postnl-tracking",
      "title": "PostNL tracking: numbers, statuses and delivery abroad",
      "name": "PostNL",
      "color": "#ef7d00"
    },
    {
      "id": "dhlink",
      "catalog": "dhlink",
      "slug": "dhlink-tracking",
      "title": "DHLink tracking: numbers, statuses and who delivers",
      "name": "DHLink",
      "color": "#143167"
    },
    {
      "id": "dpd-pt",
      "catalog": "dpd-pt",
      "slug": "dpd-portugal-tracking",
      "title": "DPD Portugal tracking: statuses, Pickup points and contact",
      "name": "DPD Portugal",
      "color": "#dc0032"
    },
    {
      "id": "tnt-it",
      "catalog": "tnt-it",
      "slug": "tnt-italy-tracking",
      "title": "TNT Italy tracking: waybill, statuses and missed delivery",
      "name": "TNT Italy",
      "color": "#ff6600"
    },
    {
      "id": "tnt",
      "catalog": "tnt",
      "slug": "tnt-tracking",
      "title": "TNT tracking: consignment number, statuses and delivery",
      "name": "TNT",
      "color": "#ff6600"
    },
    {
      "id": "sf-express",
      "catalog": "sf-express",
      "slug": "sf-express-tracking",
      "title": "SF Express tracking: waybill numbers, statuses and delivery",
      "name": "SF Express",
      "color": "#dc1e32"
    },
    {
      "id": "straightship",
      "catalog": "straightship",
      "slug": "straightship-tracking",
      "title": "Straightship tracking: STRTD numbers, statuses and delivery",
      "name": "Straightship",
      "color": "#6aae66"
    },
    {
      "id": "correios-br",
      "catalog": "correios-br",
      "slug": "correios-brazil-tracking",
      "title": "Correios Brazil tracking: codes, statuses and customs",
      "name": "Correios Brazil",
      "color": "#ffd500"
    },
    {
      "id": "yto",
      "catalog": "yto",
      "slug": "yto-express-tracking",
      "title": "YTO Express tracking: YT numbers, statuses and contact",
      "name": "YTO Express",
      "color": "#4d148c"
    },
    {
      "id": "spx-br",
      "catalog": "spx-br",
      "slug": "spx-express-brazil-tracking",
      "title": "SPX Express Brazil tracking: Shopee orders and statuses",
      "name": "SPX Express Brazil",
      "color": "#ee4d2d"
    },
    {
      "id": "eushipments",
      "catalog": "eushipments",
      "slug": "eushipments-tracking",
      "title": "euShipments tracking: statuses and who delivers your parcel",
      "name": "euShipments",
      "color": "#0a8fcf"
    },
    {
      "id": "mhi",
      "catalog": "mhi",
      "slug": "mhi-tracking",
      "title": "MHI tracking: Trackmail statuses and who delivers",
      "name": "MHI",
      "color": "#00075c"
    },
    {
      "id": "pengyou",
      "catalog": "pengyou",
      "slug": "pengyou-tracking",
      "title": "Pengyou tracking: PY numbers, scans and who to ask",
      "name": "Pengyou",
      "color": "#043a82"
    },
    {
      "id": "linhan",
      "catalog": "linhan",
      "slug": "linhan-tracking",
      "title": "LinHan International tracking: LHGJ numbers and statuses",
      "name": "LinHan International",
      "color": "#eb5c20"
    },
    {
      "id": "mtscn",
      "catalog": "mtscn",
      "slug": "meiteshun-tracking",
      "title": "Meiteshun tracking: AMTS numbers, scans and who to ask",
      "name": "Meiteshun Logistics",
      "color": "#f89f3b"
    },
    {
      "id": "cttyex",
      "catalog": "cttyex",
      "slug": "cttyex-tracking",
      "title": "CTTYEX tracking: CTTY numbers, scans and Beijing time",
      "name": "CTTYEX",
      "color": "#0068AC"
    },
    {
      "id": "speedaf",
      "catalog": "speedaf",
      "slug": "speedaf-tracking",
      "title": "Speedaf Express tracking: numbers, statuses and contacts",
      "name": "Speedaf",
      "color": "#ff8300"
    },
    {
      "id": "yilong-logistics",
      "catalog": "yilong-logistics",
      "slug": "yilong-logistics-tracking",
      "title": "Yilong Logistics tracking: YIL numbers and statuses",
      "name": "Yilong Logistics",
      "color": "#ec0307"
    },
    {
      "id": "quick-rabbit",
      "catalog": "quick-rabbit",
      "slug": "quick-rabbit-tracking",
      "title": "Quick Rabbit tracking: PKG codes, statuses and Citibox",
      "name": "Quick Rabbit",
      "color": "#f6851f"
    },
    {
      "id": "huaya-express",
      "catalog": "huaya-express",
      "slug": "huaya-express-tracking",
      "title": "Huaya Express tracking: HYYT numbers and where they stop",
      "name": "Huaya Express",
      "color": "#e60013"
    },
    {
      "id": "gofo-ca",
      "catalog": "gofo-ca",
      "slug": "gofo-canada-tracking",
      "title": "GOFO Canada tracking: GFCA numbers, statuses and delays",
      "name": "GOFO Canada",
      "color": "#fc4c02"
    },
    {
      "id": "metropolitan-warehouse",
      "catalog": "metropolitan-warehouse",
      "slug": "metropolitan-warehouse-tracking",
      "title": "Metropolitan Warehouse tracking (MWD): furniture delivery",
      "name": "Metropolitan Warehouse & Delivery",
      "color": "#0062b7"
    },
    {
      "id": "topyou",
      "catalog": "topyou",
      "slug": "topyou-tracking",
      "title": "TopYou tracking: TYZR numbers, scans and who delivers",
      "name": "TopYou",
      "color": "#045cd7"
    },
    {
      "id": "byhd",
      "catalog": "byhd",
      "slug": "byhd-tracking",
      "title": "BYHD tracking: BYHD numbers, scans and who delivers",
      "name": "BYHD",
      "color": "#084282"
    },
    {
      "id": "zto-international",
      "catalog": "zto-international",
      "slug": "zto-international-tracking",
      "title": "ZTO International tracking: 771 numbers and statuses",
      "name": "ZTO International",
      "color": "#3a8eff"
    },
    {
      "id": "mayiyinfu",
      "catalog": "mayiyinfu",
      "slug": "mayi-yinfu-tracking",
      "title": "Mayi Yinfu tracking: MY numbers, flight lines and DHL",
      "name": "Mayi Yinfu",
      "color": "#000000"
    },
    {
      "id": "jrt",
      "catalog": "jrt",
      "slug": "jrt-tracking",
      "title": "JRT tracking: JRT…MM numbers, flight scans and DHL",
      "name": "JRT",
      "color": "#023053"
    },
    {
      "id": "thhy",
      "catalog": "thhy",
      "slug": "tianhui-hengyuan-tracking",
      "title": "Tianhui Hengyuan (THHY) tracking: AT numbers and scans",
      "name": "Tianhui Hengyuan",
      "color": "#00a2e9"
    },
    {
      "id": "js-express",
      "catalog": "js-express",
      "slug": "js-express-tracking",
      "title": "JS Express tracking: JSE…YQ numbers, scans and who delivers",
      "name": "JS Express",
      "color": "#fe9b00"
    },
    {
      "id": "wanbexpress",
      "catalog": "wanbexpress",
      "slug": "wanbexpress-tracking",
      "title": "WanbExpress tracking: WNB…YQ numbers, scans and who delivers",
      "name": "WanbExpress",
      "color": "#da381b"
    },
    {
      "id": "gzyl",
      "catalog": "gzyl",
      "slug": "gzyl-tracking",
      "title": "GZYL tracking: YL…YQ numbers, scans and who to ask",
      "name": "GZYL",
      "color": "#4e79e1"
    },
    {
      "id": "shouwu",
      "catalog": "shouwu",
      "slug": "shouwu-tracking",
      "title": "Shouwu tracking: SWECN…YQ numbers, scans and who delivers",
      "name": "Shouwu International",
      "color": "#be2c33"
    },
    {
      "id": "geodis-pl",
      "catalog": "geodis-pl",
      "slug": "geodis-poland-tracking",
      "title": "GEODIS Poland tracking: shipment number, statuses, delivery",
      "name": "GEODIS Poland",
      "color": "#3100e6"
    },
    {
      "id": "sending",
      "catalog": "sending",
      "slug": "sending-tracking",
      "title": "Sending tracking in Spain: number, statuses and phone",
      "name": "Sending",
      "color": "#e52021"
    },
    {
      "id": "hywl",
      "catalog": "hywl",
      "slug": "hywl-tracking",
      "title": "HYWL tracking: Huiyi Logistics TRK numbers and scans",
      "name": "Huiyi Logistics",
      "color": "#0b8f91"
    },
    {
      "id": "ywe",
      "catalog": "ywe",
      "slug": "ywe-tracking",
      "title": "YWE (YanwenExpress) tracking: numbers, scans and who to ask",
      "name": "YWE",
      "color": "#055aa1"
    },
    {
      "id": "roadie",
      "catalog": "roadie",
      "slug": "roadie-tracking",
      "title": "Roadie tracking: 1R numbers, statuses and delivery photos",
      "name": "Roadie",
      "color": "#76cccd"
    },
    {
      "id": "ecms-express",
      "catalog": "ecms-express",
      "slug": "ecms-express-tracking",
      "title": "ECMS Express tracking: numbers, scans and who delivers",
      "name": "ECMS Express",
      "color": "#12823d"
    },
    {
      "id": "chunghwa-post",
      "catalog": "chunghwa-post",
      "slug": "chunghwa-post-tracking",
      "title": "Chunghwa Post (Taiwan Post) tracking: numbers and statuses",
      "name": "Chunghwa Post",
      "color": "#e8171f"
    },
    {
      "id": "raben",
      "catalog": "raben",
      "slug": "raben-tracking",
      "title": "Raben tracking: shipment number, statuses and delivery",
      "name": "Raben",
      "color": "#df0020"
    },
    {
      "id": "jd-logistics",
      "catalog": "jd-logistics",
      "slug": "jd-logistics-tracking",
      "title": "JD Logistics tracking: JD numbers, statuses and contact",
      "name": "JD Logistics",
      "color": "#e1251b"
    },
    {
      "id": "singapore-post",
      "catalog": "singapore-post",
      "slug": "singapore-post-tracking",
      "title": "Singapore Post (SingPost) tracking: numbers and statuses",
      "name": "Singapore Post",
      "color": "#2e5da8"
    },
    {
      "id": "zto",
      "catalog": "zto",
      "slug": "zto-express-tracking",
      "title": "ZTO Express tracking: 中通快递 numbers, statuses, contact",
      "name": "ZTO Express",
      "color": "#0279ff"
    },
    {
      "id": "cne",
      "catalog": "cne",
      "slug": "cne-express-tracking",
      "title": "CNE Express tracking: 3A5V numbers, statuses and contact",
      "name": "CNE Express",
      "color": "#e83828"
    },
    {
      "id": "japan-post",
      "catalog": "japan-post",
      "slug": "japan-post-tracking",
      "title": "Japan Post tracking: EMS numbers and statuses explained",
      "name": "Japan Post",
      "color": "#cc0000"
    },
    {
      "id": "russian-post",
      "catalog": "russian-post",
      "slug": "russian-post-tracking",
      "title": "Russian Post tracking (Почта России): numbers and steps",
      "name": "Russian Post",
      "color": "#1928dc"
    },
    {
      "id": "huahan",
      "catalog": "huahan",
      "slug": "huahan-logistics-tracking",
      "title": "HuaHan Logistics tracking: HHW…YQ numbers and who delivers",
      "name": "HuaHan Logistics",
      "color": "#0069e8"
    },
    {
      "id": "epost-global",
      "catalog": "epost-global",
      "slug": "epost-global-tracking",
      "title": "ePost Global tracking: EPG numbers and who delivers",
      "name": "ePost Global",
      "color": "#27967a"
    },
    {
      "id": "gls-at",
      "catalog": "gls-at",
      "slug": "gls-austria-tracking",
      "title": "GLS Austria tracking: statuses, Parcel Shops and contact",
      "name": "GLS Austria",
      "color": "#ffdd00"
    },
    {
      "id": "india-post",
      "catalog": "india-post",
      "slug": "india-post-tracking",
      "title": "India Post tracking: Speed Post numbers and statuses",
      "name": "India Post",
      "color": "#b82025"
    },
    {
      "id": "morning-global",
      "catalog": "morning-global",
      "slug": "morning-global-tracking",
      "title": "Morning Global tracking: BR…MG numbers, statuses, contact",
      "name": "Morning Global",
      "color": "#050059"
    },
    {
      "id": "border-express",
      "catalog": "border-express",
      "slug": "border-express-tracking",
      "title": "Border Express tracking: consignment numbers and statuses",
      "name": "Border Express",
      "color": "#cc092f"
    },
    {
      "id": "delhivery",
      "catalog": "delhivery",
      "slug": "delhivery-tracking",
      "title": "Delhivery tracking: AWB numbers, statuses and support",
      "name": "Delhivery",
      "color": "#ed4136"
    },
    {
      "id": "aras-kargo",
      "catalog": "aras-kargo",
      "slug": "aras-kargo-tracking",
      "title": "Aras Kargo tracking: numbers and statuses in English",
      "name": "Aras Kargo",
      "color": "#dc0812"
    },
    {
      "id": "emirates-post",
      "catalog": "emirates-post",
      "slug": "emirates-post-tracking",
      "title": "Emirates Post tracking: AE numbers and statuses explained",
      "name": "Emirates Post",
      "color": "#004a98"
    },
    {
      "id": "blue-dart",
      "catalog": "blue-dart",
      "slug": "blue-dart-tracking",
      "title": "Blue Dart tracking: waybill numbers and statuses explained",
      "name": "Blue Dart",
      "color": "#0059a9"
    },
    {
      "id": "ekart",
      "catalog": "ekart",
      "slug": "ekart-tracking",
      "title": "Ekart tracking: tracking IDs, statuses and RTO explained",
      "name": "Ekart",
      "color": "#0074D9"
    },
    {
      "id": "j-and-t-middle-east",
      "catalog": "j-and-t-middle-east",
      "slug": "jt-express-middle-east-tracking",
      "title": "J&T Express tracking in Saudi Arabia, UAE and Kuwait",
      "name": "J&T Express Middle East",
      "color": "#e60000"
    },
    {
      "id": "dpd-ro",
      "catalog": "dpd-ro",
      "slug": "dpd-romania-tracking",
      "title": "DPD Romania tracking: statuses, Smart Control and contact",
      "name": "DPD Romania",
      "color": "#dc0032"
    },
    {
      "id": "globalpost",
      "catalog": "globalpost",
      "slug": "globalpost-tracking",
      "title": "GlobalPost tracking: GP numbers and who delivers",
      "name": "GlobalPost",
      "color": "#11003a"
    },
    {
      "id": "cdek",
      "catalog": "cdek",
      "slug": "cdek-tracking",
      "title": "CDEK tracking (СДЭК): order numbers and statuses in English",
      "name": "CDEK",
      "color": "#00b33c"
    },
    {
      "id": "forza-delivery",
      "catalog": "forza-delivery",
      "slug": "forza-delivery-tracking",
      "title": "Forza Delivery tracking: guía numbers, statuses, contact",
      "name": "Forza Delivery Express",
      "color": "#ea5c1d"
    },
    {
      "id": "quantium-solutions",
      "catalog": "quantium-solutions",
      "slug": "quantium-solutions-tracking",
      "title": "Quantium Solutions tracking: numbers, statuses and help",
      "name": "Quantium Solutions",
      "color": "#00474f"
    },
    {
      "id": "correos-chile",
      "catalog": "correos-chile",
      "slug": "correos-chile-tracking",
      "title": "Correos de Chile tracking: numbers and statuses explained",
      "name": "Correos de Chile",
      "color": "#d9232d"
    },
    {
      "id": "posta-shqiptare",
      "catalog": "posta-shqiptare",
      "slug": "posta-shqiptare-tracking",
      "title": "Posta Shqiptare tracking: Albanian Post numbers and statuses",
      "name": "Posta Shqiptare",
      "color": "#1a3292"
    },
    {
      "id": "dsv",
      "catalog": "dsv",
      "slug": "dsv-tracking",
      "title": "DSV tracking: shipment numbers, statuses and DB Schenker",
      "name": "DSV",
      "color": "#002664"
    },
    {
      "id": "pin-ag",
      "catalog": "pin-ag",
      "slug": "pin-ag-tracking",
      "title": "PIN AG tracking: registered letters, parcels and pickup",
      "name": "PIN AG",
      "color": "#003366"
    },
    {
      "id": "pitney-bowes",
      "catalog": "pitney-bowes",
      "slug": "pitney-bowes-tracking",
      "title": "Pitney Bowes tracking: UPAA, UPBE and PBX numbers explained",
      "name": "Pitney Bowes",
      "color": "#3e53a4"
    },
    {
      "id": "rl-carriers",
      "catalog": "rl-carriers",
      "slug": "rl-carriers-tracking",
      "title": "R+L Carriers tracking: PRO numbers, statuses and claims",
      "name": "R+L Carriers",
      "color": "#006647"
    },
    {
      "id": "nox",
      "catalog": "nox",
      "slug": "nox-tracking",
      "title": "nox NachtExpress tracking: statuses, drop-off and contact",
      "name": "nox",
      "color": "#fadb00"
    },
    {
      "id": "far-international",
      "catalog": "far-international",
      "slug": "far-international-tracking",
      "title": "FAR International tracking: numbers, statuses and contact",
      "name": "FAR International",
      "color": "#003b75"
    },
    {
      "id": "jifan",
      "catalog": "jifan",
      "slug": "jifan-tracking",
      "title": "Jifan tracking: JFGJ numbers, scans and Beijing time",
      "name": "Jifan",
      "color": "#c81011"
    },
    {
      "id": "posta-romana",
      "catalog": "posta-romana",
      "slug": "posta-romana-tracking",
      "title": "Poșta Română tracking: numbers, statuses and customs",
      "name": "Poșta Română",
      "color": "#ea0029"
    },
    {
      "id": "dpd-nl",
      "catalog": "dpd-nl",
      "slug": "dpd-netherlands-tracking",
      "title": "DPD Netherlands tracking: statuses, delivery and contact",
      "name": "DPD Netherlands",
      "color": "#dc0032"
    },
    {
      "id": "dpd-at",
      "catalog": "dpd-at",
      "slug": "dpd-austria-tracking",
      "title": "DPD Austria tracking: statuses, Pickup shops and contact",
      "name": "DPD Austria",
      "color": "#dc0032"
    },
    {
      "id": "ozon-logistics",
      "catalog": "ozon-logistics",
      "slug": "ozon-tracking",
      "title": "Ozon tracking: order numbers and statuses in English",
      "name": "Ozon",
      "color": "#005bff"
    },
    {
      "id": "citymail",
      "catalog": "citymail",
      "slug": "citymail-tracking",
      "title": "CityMail tracking: numbers, statuses and delivery in Sweden",
      "name": "CityMail",
      "color": "#fc4c02"
    },
    {
      "id": "dayone",
      "catalog": "dayone",
      "slug": "dayone-tracking",
      "title": "DayOne tracking: DOT numbers, statuses and who delivers",
      "name": "DayOne",
      "color": "#e86d1c"
    },
    {
      "id": "dpd-be",
      "catalog": "dpd-be",
      "slug": "dpd-belgium-tracking",
      "title": "DPD Belgium tracking: statuses, delivery and contact",
      "name": "DPD Belgium",
      "color": "#dc0032"
    },
    {
      "id": "packlink",
      "catalog": "packlink",
      "slug": "packlink-tracking",
      "title": "Packlink tracking: reference, statuses and who to contact",
      "name": "Packlink",
      "color": "#ff4f00"
    },
    {
      "id": "sto",
      "catalog": "sto",
      "slug": "sto-express-tracking",
      "title": "STO Express tracking: 申通快递 numbers, statuses, contact",
      "name": "STO Express",
      "color": "#ff6700"
    },
    {
      "id": "j-and-t-cn",
      "catalog": "j-and-t-cn",
      "slug": "jt-express-china-tracking",
      "title": "J&T Express China tracking: 极兔速递 numbers, statuses, contact",
      "name": "J&T Express China",
      "color": "#e62019"
    },
    {
      "id": "tforce-freight",
      "catalog": "tforce-freight",
      "slug": "tforce-freight-tracking",
      "title": "TForce Freight tracking: PRO numbers, statuses and claims",
      "name": "TForce Freight",
      "color": "#0053a1"
    },
    {
      "id": "andreani",
      "catalog": "andreani",
      "slug": "andreani-tracking",
      "title": "Andreani tracking: Argentina parcel numbers and statuses",
      "name": "Andreani",
      "color": "#d71920"
    },
    {
      "id": "mainfreight",
      "catalog": "mainfreight",
      "slug": "mainfreight-tracking",
      "title": "Mainfreight tracking: consignment numbers and statuses",
      "name": "Mainfreight",
      "color": "#005eb8"
    },
    {
      "id": "spx-sg",
      "catalog": "spx-sg",
      "slug": "spx-express-singapore-tracking",
      "title": "SPX Express Singapore tracking: Shopee parcels and statuses",
      "name": "SPX Express Singapore",
      "color": "#ee4d2d"
    },
    {
      "id": "dai-post",
      "catalog": "dai-post",
      "slug": "dai-post-tracking",
      "title": "DAI Post tracking: numbers, statuses and delivery help",
      "name": "DAI Post",
      "color": "#008faf"
    },
    {
      "id": "eurodis",
      "catalog": "eurodis",
      "slug": "eurodis-tracking",
      "title": "EURODIS tracking: shipment numbers and delivery partners",
      "name": "EURODIS",
      "color": "#0b418b"
    },
    {
      "id": "chronodiali",
      "catalog": "chronodiali",
      "slug": "chronodiali-tracking",
      "title": "Chronodiali tracking: CHPU numbers, statuses and contact",
      "name": "Chronodiali",
      "color": "#009adf"
    },
    {
      "id": "17feia",
      "catalog": "17feia",
      "slug": "17feia-tracking",
      "title": "17FEIA tracking: FEI…YQ numbers, statuses and delivery",
      "name": "17FEIA",
      "color": "#00a9e4"
    },
    {
      "id": "tsjy",
      "catalog": "tsjy",
      "slug": "tsjy-tracking",
      "title": "TSJY tracking (通世集运): order numbers and statuses",
      "name": "TSJY",
      "color": "#4042e2"
    },
    {
      "id": "stone3pl",
      "catalog": "stone3pl",
      "slug": "stone3pl-tracking",
      "title": "Stone3PL tracking: numbers, statuses and contact",
      "name": "Stone3PL",
      "color": "#003d98"
    },
    {
      "id": "j-and-t-uk",
      "catalog": "j-and-t-uk",
      "slug": "jt-express-uk-tracking",
      "title": "J&T Express UK tracking: JTUK numbers and statuses",
      "name": "J&T Express UK",
      "color": "#e60000"
    },
    {
      "id": "m-one",
      "catalog": "m-one",
      "slug": "m-one-tracking",
      "title": "M-ONE tracking (Milšped ONE): statuses, delivery and help",
      "name": "M-ONE",
      "color": "#024da0"
    },
    {
      "id": "early-bird",
      "catalog": "early-bird",
      "slug": "early-bird-tracking",
      "title": "Early Bird tracking: statuses, morning delivery and lockers",
      "name": "Early Bird",
      "color": "#dd7f4a"
    },
    {
      "id": "xinghe-freight",
      "catalog": "xinghe-freight",
      "slug": "xinghe-freight-tracking",
      "title": "Xinghe Freight tracking: GZXH…YQ numbers and scans",
      "name": "Xinghe Freight",
      "color": "#0e3a6b"
    },
    {
      "id": "cvc",
      "catalog": "cvc",
      "slug": "cvc-tracking",
      "title": "CVC tracking (Си Ви Си): follow a parcel in Bulgaria",
      "name": "CVC",
      "color": "#652b9e"
    },
    {
      "id": "transpress",
      "catalog": "transpress",
      "slug": "transpress-tracking",
      "title": "Transpress tracking (Транспрес): track a parcel in Bulgaria",
      "name": "Transpress",
      "color": "#1e6ee2"
    },
    {
      "id": "gls-us",
      "catalog": "gls-us",
      "slug": "gls-us-tracking",
      "title": "GLS US tracking (GSO): numbers, statuses and delivery",
      "name": "GLS US",
      "color": "#ffdd00"
    },
    {
      "id": "sxw-express",
      "catalog": "sxw-express",
      "slug": "sxw-express-tracking",
      "title": "SXW Express tracking: SXW numbers, scans and who delivers",
      "name": "SXW Express",
      "color": "#003876"
    },
    {
      "id": "xuchuan-logistics",
      "catalog": "xuchuan-logistics",
      "slug": "xuchuan-logistics-tracking",
      "title": "Xuchuan Logistics tracking: XC numbers, delivery and help",
      "name": "Xuchuan International Logistics",
      "color": "#0a2463"
    },
    {
      "id": "samus",
      "catalog": "samus",
      "slug": "samus-tracking",
      "title": "SAMUS tracking: numbers, statuses and Vilnius pickup",
      "name": "SAMUS",
      "color": "#e50079"
    },
    {
      "id": "siuntos-autobusais",
      "catalog": "siuntos-autobusais",
      "slug": "siuntos-autobusais-tracking",
      "title": "Siuntos autobusais tracking: bus parcels in Lithuania",
      "name": "Siuntos autobusais",
      "color": "#00843d"
    },
    {
      "id": "zipasgo",
      "catalog": "zipasgo",
      "slug": "zipasgo-tracking",
      "title": "ZipasGo tracking: Parcel Stars, partner couriers and help",
      "name": "ZipasGo",
      "color": "#ffdb1c"
    },
    {
      "id": "dk-logistics-ee",
      "catalog": "dk-logistics-ee",
      "slug": "dk-logistics-tracking",
      "title": "DK Logistics tracking: waybill, delivery and contact",
      "name": "DK Logistics",
      "color": "#f58428"
    },
    {
      "id": "jet-express-ee",
      "catalog": "jet-express-ee",
      "slug": "jet-express-tracking",
      "title": "Jet Express tracking: order number, delivery and contact",
      "name": "Jet Express",
      "color": "#d61b22"
    },
    {
      "id": "fan-courier-international",
      "catalog": "fan-courier-international",
      "slug": "fan-courier-international-tracking",
      "title": "FAN Courier International tracking: AWB and statuses",
      "name": "FAN Courier International",
      "color": "#2daae1"
    },
    {
      "id": "j-and-t-br",
      "catalog": "j-and-t-br",
      "slug": "jt-express-brazil-tracking",
      "title": "J&T Express Brazil tracking: status, pickup and contact",
      "name": "J&T Express Brazil",
      "color": "#e60000"
    },
    {
      "id": "anjun",
      "catalog": "anjun",
      "slug": "anjun-logistics-tracking",
      "title": "Anjun Logistics tracking: China-to-Brazil statuses and SAC",
      "name": "Anjun Logistics",
      "color": "#009944"
    },
    {
      "id": "xyl-ships",
      "catalog": "xyl-ships",
      "slug": "xyl-ships-tracking",
      "title": "XYL-Ships tracking: NUDE and XLWL numbers and statuses",
      "name": "XYL-Ships",
      "color": "#b13c3c"
    },
    {
      "id": "jadlog",
      "catalog": "jadlog",
      "slug": "jadlog-tracking",
      "title": "Jadlog tracking: 14-digit codes, Pickup, Predict and status",
      "name": "Jadlog",
      "color": "#dc0032"
    },
    {
      "id": "gls-pl",
      "catalog": "gls-pl",
      "slug": "gls-poland-tracking",
      "title": "GLS Poland tracking: statuses, GLS Points, lockers and help",
      "name": "GLS Poland",
      "color": "#ffdd00"
    },
    {
      "id": "j-and-t-mx",
      "catalog": "j-and-t-mx",
      "slug": "jt-express-mexico-tracking",
      "title": "J&T Express Mexico tracking: JMX codes, phone digits, help",
      "name": "J&T Express Mexico",
      "color": "#e60012"
    },
    {
      "id": "bulgarian-posts",
      "catalog": "bulgarian-posts",
      "slug": "bulgarian-posts-tracking",
      "title": "Bulgarian Posts tracking: PS and BG codes, customs, claims",
      "name": "Bulgarian Posts",
      "color": "#ffd100"
    },
    {
      "id": "haypost",
      "catalog": "haypost",
      "slug": "haypost-tracking",
      "title": "HayPost tracking: AM postal codes, statuses and contacts",
      "name": "HayPost",
      "color": "#fa4616"
    },
    {
      "id": "360lion",
      "catalog": "360lion",
      "slug": "360lion-tracking",
      "title": "360Lion tracking: WSH and AR codes, statuses and contacts",
      "name": "360Lion",
      "color": "#cf5d15"
    },
    {
      "id": "paquetexpress",
      "catalog": "paquetexpress",
      "slug": "paquetexpress-tracking",
      "title": "Paquetexpress tracking: guías, statuses, Ocurre and claims",
      "name": "Paquetexpress",
      "color": "#002a52"
    },
    {
      "id": "ydh",
      "catalog": "ydh",
      "slug": "ydh-tracking",
      "title": "YDH tracking: YDH...YQ and AZ codes, handoffs and contacts",
      "name": "YDH",
      "color": "#015db9"
    },
    {
      "id": "a-tu-hora-express",
      "catalog": "a-tu-hora-express",
      "slug": "a-tu-hora-express-tracking",
      "title": "A Tu Hora Express tracking: codes, postcode & help",
      "name": "A Tu Hora Express",
      "color": "#1d2a84"
    },
    {
      "id": "dtdc",
      "catalog": "dtdc",
      "slug": "dtdc-tracking",
      "title": "DTDC tracking: AWB formats, RTO statuses, delivery and help",
      "name": "DTDC",
      "color": "#0d237d"
    },
    {
      "id": "gofo-es",
      "catalog": "gofo-es",
      "slug": "gofo-spain-tracking",
      "title": "GOFO Spain tracking: GFES and CIES codes, 3 attempts, help",
      "name": "GOFO Spain",
      "color": "#fc4c02"
    },
    {
      "id": "korea-post",
      "catalog": "korea-post",
      "slug": "korea-post-tracking",
      "title": "Korea Post tracking: EMS KR codes, 13-digit parcels & help",
      "name": "Korea Post",
      "color": "#da291c"
    },
    {
      "id": "nova-global",
      "catalog": "nova-global",
      "slug": "nova-global-tracking",
      "title": "Nova Global tracking: NPG & NGUS codes, customs and help",
      "name": "Nova Global",
      "color": "#da291c"
    },
    {
      "id": "xlcourier",
      "catalog": "xlcourier",
      "slug": "xlcourier-tracking",
      "title": "XLCourier tracking: XLC codes, heavy UK parcels and help",
      "name": "XLCourier",
      "color": "#044ad6"
    },
    {
      "id": "estafeta",
      "catalog": "estafeta",
      "slug": "estafeta-tracking",
      "title": "Estafeta tracking: 10- & 22-char codes, 2 attempts & help",
      "name": "Estafeta",
      "color": "#e30613"
    },
    {
      "id": "gls-hu",
      "catalog": "gls-hu",
      "slug": "gls-hungary-tracking",
      "title": "GLS Hungary tracking: parcel numbers, lockers and contacts",
      "name": "GLS Hungary",
      "color": "#ffdd00"
    },
    {
      "id": "dhl-freight",
      "catalog": "dhl-freight",
      "slug": "dhl-freight-tracking",
      "title": "DHL Freight tracking: ActiveTracing IDs, statuses & help",
      "name": "DHL Freight",
      "color": "#ffcc00"
    },
    {
      "id": "anserx",
      "catalog": "anserx",
      "slug": "anserx-tracking",
      "title": "AnserX tracking: BTYCN...YQ codes, handoffs & contacts",
      "name": "AnserX",
      "color": "#2478be"
    },
    {
      "id": "bjs-home-delivery",
      "catalog": "bjs-home-delivery",
      "slug": "bjs-home-delivery-tracking",
      "title": "BJS Home Delivery tracking: B-codes, 3-hour slots & help",
      "name": "BJS Home Delivery",
      "color": "#feba30"
    },
    {
      "id": "chukou1",
      "catalog": "chukou1",
      "slug": "chukou1-tracking",
      "title": "Chukou1 tracking: EC-FirstClass codes, handoffs & help",
      "name": "Chukou1",
      "color": "#0b4da2"
    },
    {
      "id": "cj-packet",
      "catalog": "cj-packet",
      "slug": "cj-packet-tracking",
      "title": "CJ Packet tracking: CJP...YQ codes, handoffs & contacts",
      "name": "CJ Packet",
      "color": "#ff7700"
    },
    {
      "id": "cmt-express",
      "catalog": "cmt-express",
      "slug": "cmt-express-tracking",
      "title": "CMT Express tracking: CMT codes, Shenzhen hub & contacts",
      "name": "CMT Express",
      "color": "#ed010b"
    },
    {
      "id": "correos-de-mexico",
      "catalog": "correos-de-mexico",
      "slug": "correos-de-mexico-tracking",
      "title": "Correos de México tracking: S10 MX codes, claims & help",
      "name": "Correos de México",
      "color": "#ed007d"
    },
    {
      "id": "dobropost",
      "catalog": "dobropost",
      "slug": "dobropost-tracking",
      "title": "DobroPost tracking: DBRPST codes, statuses & China hubs",
      "name": "DobroPost",
      "color": "#ffbe2e"
    },
    {
      "id": "ems",
      "catalog": "ems",
      "slug": "ems-tracking",
      "title": "EMS tracking: S10 item numbers, statuses & postal help",
      "name": "EMS",
      "color": "#003b7a"
    },
    {
      "id": "jiayang",
      "catalog": "jiayang",
      "slug": "jiayang-tracking",
      "title": "Jiayang International tracking: JYGJ codes, statuses & help",
      "name": "Jiayang International",
      "color": "#105bcc"
    },
    {
      "id": "jumppoint-international",
      "catalog": "jumppoint-international",
      "slug": "jumppoint-international-tracking",
      "title": "Jumppoint International tracking: JP codes, statuses & help",
      "name": "Jumppoint International Express",
      "color": "#0f4ae1"
    },
    {
      "id": "kuayuan-express",
      "catalog": "kuayuan-express",
      "slug": "kuayuan-express-tracking",
      "title": "Kuayuan Express tracking: waybill codes, handoffs & help",
      "name": "Kuayuan Express",
      "color": "#084b86"
    },
    {
      "id": "liccardi",
      "catalog": "liccardi",
      "slug": "liccardi-tracking",
      "title": "Liccardi Express Courier tracking: LDV codes & support",
      "name": "Liccardi Express Courier",
      "color": "#014895"
    },
    {
      "id": "maersk-ecommerce",
      "catalog": "maersk-ecommerce",
      "slug": "maersk-ecommerce-tracking",
      "title": "Maersk eCommerce tracking: 1M parcel codes & scan status",
      "name": "Maersk eCommerce",
      "color": "#42b0d5"
    },
    {
      "id": "pdu",
      "catalog": "pdu",
      "slug": "pdu-tracking",
      "title": "PDU tracking: Parcel Delivery Union codes, statuses & help",
      "name": "PDU",
      "color": "#005cfe"
    },
    {
      "id": "post-haste",
      "catalog": "post-haste",
      "slug": "post-haste-tracking",
      "title": "Post Haste tracking: NZ tickets, consignment codes & help",
      "name": "Post Haste",
      "color": "#24b2d8"
    },
    {
      "id": "redur",
      "catalog": "redur",
      "slug": "redur-tracking",
      "title": "Redur tracking: 14-digit codes, Spain & Portugal statuses",
      "name": "Redur",
      "color": "#0c4c49"
    },
    {
      "id": "sengi-express",
      "catalog": "sengi-express",
      "slug": "sengi-express-tracking",
      "title": "Sengi Express tracking: China–Italy codes & Poste handoff",
      "name": "Sengi Express",
      "color": "#1a44a4"
    },
    {
      "id": "serpost",
      "catalog": "serpost",
      "slug": "serpost-tracking",
      "title": "Serpost tracking: Peru S10 codes, customs statuses & help",
      "name": "Serpost",
      "color": "#00308a"
    },
    {
      "id": "skypostal",
      "catalog": "skypostal",
      "slug": "skypostal-tracking",
      "title": "SkyPostal tracking: SKAMZUS & SP codes, statuses & support",
      "name": "SkyPostal",
      "color": "#088aea"
    },
    {
      "id": "thailand-post",
      "catalog": "thailand-post",
      "slug": "thailand-post-tracking",
      "title": "Thailand Post tracking: TH S10 codes, EMS statuses & help",
      "name": "Thailand Post",
      "color": "#ef172f"
    },
    {
      "id": "yunda",
      "catalog": "yunda",
      "slug": "yunda-tracking",
      "title": "Yunda Express tracking: waybill codes, statuses & 95546",
      "name": "Yunda Express",
      "color": "#ffce00"
    },
    {
      "id": "onlog-express",
      "catalog": "onlog-express",
      "slug": "onlog-express-tracking",
      "title": "Onlog Express tracking: AA codes, Brazil statuses & help",
      "name": "Onlog Express",
      "color": "#1a345d"
    },
    {
      "id": "a-duie-pyle",
      "catalog": "a-duie-pyle",
      "slug": "a-duie-pyle-tracking",
      "title": "A. Duie Pyle tracking: PRO numbers, LTL statuses & support",
      "name": "A. Duie Pyle",
      "color": "#f27629"
    },
    {
      "id": "buffalo-ex",
      "catalog": "buffalo-ex",
      "slug": "buffalo-ex-tracking",
      "title": "BUFFALO tracking: BUFZA codes, South Africa customs & help",
      "name": "BUFFALO",
      "color": "#bc8c4d"
    },
    {
      "id": "chit-chats",
      "catalog": "chit-chats",
      "slug": "chit-chats-tracking",
      "title": "Chit Chats tracking: 10-char IDs, USPS/Canada Post handoff",
      "name": "Chit Chats",
      "color": "#db2b35"
    },
    {
      "id": "dpd-hu",
      "catalog": "dpd-hu",
      "slug": "dpd-hu-tracking",
      "title": "DPD Hungary tracking: 14-digit codes, FOXPOST & support",
      "name": "DPD Hungary",
      "color": "#dc0032"
    },
    {
      "id": "e24metro-ca",
      "catalog": "e24metro-ca",
      "slug": "e24metro-ca-tracking",
      "title": "E24 (Metro Supply Chain) tracking: Canada last-mile & help",
      "name": "E24",
      "color": "#545859"
    },
    {
      "id": "go-express",
      "catalog": "go-express",
      "slug": "go-express-tracking",
      "title": "GO! Express tracking: 12-digit waybills, statuses & help",
      "name": "GO! Express",
      "color": "#b9242b"
    },
    {
      "id": "kuehne-nagel",
      "catalog": "kuehne-nagel",
      "slug": "kuehne-nagel-tracking",
      "title": "Kuehne+Nagel tracking — numbers, statuses, and delivery",
      "name": "Kuehne+Nagel",
      "color": "#003369"
    },
    {
      "id": "memphis-courier",
      "catalog": "memphis-courier",
      "slug": "memphis-courier-tracking",
      "title": "Memphis Courier tracking — numbers, statuses, and customs",
      "name": "Memphis Courier",
      "color": "#1f3c5b"
    },
    {
      "id": "oca",
      "catalog": "oca",
      "slug": "oca-tracking",
      "title": "OCA tracking — numbers, statuses, branches, and delivery",
      "name": "OCA",
      "color": "#592c82"
    },
    {
      "id": "pigeonship",
      "catalog": "pigeonship",
      "slug": "pigeonship-tracking",
      "title": "PigeonShip tracking — numbers, statuses, and delivery",
      "name": "PigeonShip",
      "color": "#f2b02c"
    },
    {
      "id": "seko",
      "catalog": "seko",
      "slug": "seko-logistics-tracking",
      "title": "SEKO Logistics tracking — numbers, OmniParcel, and delivery",
      "name": "SEKO Logistics",
      "color": "#ef3e42"
    },
    {
      "id": "bfe-express",
      "catalog": "bfe-express",
      "slug": "bfe-express-tracking",
      "title": "BFE Express tracking — numbers, statuses, and delivery",
      "name": "BFE Express",
      "color": "#122a88"
    },
    {
      "id": "pasarex",
      "catalog": "pasarex",
      "slug": "pasarex-tracking",
      "title": "PasarEX tracking — Amazon AMZPSR codes, customs & help",
      "name": "PasarEX",
      "color": "#124381"
    },
    {
      "id": "spee-dee",
      "catalog": "spee-dee",
      "slug": "spee-dee-tracking",
      "title": "Spee-Dee Delivery tracking — SP numbers, states & help",
      "name": "Spee-Dee",
      "color": "#ff4734"
    },
    {
      "id": "tnt-au",
      "catalog": "tnt-au",
      "slug": "tnt-australia-tracking",
      "title": "TNT Australia tracking — domestic numbers, statuses & help",
      "name": "TNT Australia",
      "color": "#ff6600"
    },
    {
      "id": "wedo",
      "catalog": "wedo",
      "slug": "one-by-allegro-cz-tracking",
      "title": "One by Allegro CZ (WE|DO) tracking — numbers & delivery",
      "name": "One by Allegro CZ",
      "color": "#006d57"
    },
    {
      "id": "5post",
      "catalog": "5post",
      "slug": "5post-tracking",
      "title": "5Post tracking — numbers, Pyaterochka pickup & statuses",
      "name": "5Post",
      "color": "#64b441"
    },
    {
      "id": "shipter",
      "catalog": "shipter",
      "slug": "shipter-tracking",
      "title": "Shipter tracking — H3 Korean export numbers & statuses",
      "name": "Shipter",
      "color": "#005eff"
    }
  ],
  "de": [
    {
      "id": "quickpac",
      "catalog": "quickpac",
      "slug": "quickpac-tracking",
      "title": "Quickpac Tracking: Paket verfolgen und Status verstehen",
      "name": "Quickpac",
      "color": "#ed1c24"
    },
    {
      "id": "planzer",
      "catalog": "planzer",
      "slug": "planzer-sendungsverfolgung",
      "title": "Planzer Sendungsverfolgung: Status, Zustellung und Hilfe",
      "name": "Planzer",
      "color": "#e30613"
    },
    {
      "id": "colis-prive",
      "catalog": "colis-prive",
      "slug": "colis-prive-sendungsverfolgung",
      "title": "Colis Privé Sendungsverfolgung: französische Status erklärt",
      "name": "Colis Privé",
      "color": "#aa78ff"
    },
    {
      "id": "brt",
      "catalog": "brt",
      "slug": "brt-sendungsverfolgung",
      "title": "BRT Sendungsverfolgung: Paket aus Italien, Status",
      "name": "BRT",
      "color": "#dc0032"
    },
    {
      "id": "correos-express",
      "catalog": "correos-express",
      "slug": "correos-express-sendungsverfolgung",
      "title": "Correos Express Sendungsverfolgung: Status und Kontakt",
      "name": "Correos Express",
      "color": "#ffcd00"
    },
    {
      "id": "nacex",
      "catalog": "nacex",
      "slug": "nacex-sendungsverfolgung",
      "title": "NACEX Sendungsverfolgung: Nummer, Status und Kontakt",
      "name": "NACEX",
      "color": "#fe5000"
    },
    {
      "id": "yunexpress",
      "catalog": "yunexpress",
      "slug": "yunexpress-sendungsverfolgung",
      "title": "YunExpress Sendungsverfolgung: Status, Zusteller, Kontakt",
      "name": "YunExpress",
      "color": "#008284"
    },
    {
      "id": "cainiao",
      "catalog": "aliexpress",
      "slug": "cainiao-sendungsverfolgung",
      "title": "Cainiao Sendungsverfolgung: Nummern, Status und Kontakt",
      "name": "Cainiao",
      "color": "#e62e04"
    },
    {
      "id": "mondial-relay",
      "catalog": "mondial-relay",
      "slug": "mondial-relay-sendungsverfolgung",
      "title": "Mondial Relay Sendungsverfolgung: Status, Vinted & Abholung",
      "name": "Mondial Relay",
      "color": "#ef3026"
    },
    {
      "id": "gls-de",
      "catalog": "gls-de",
      "slug": "gls-sendungsverfolgung-deutschland",
      "title": "GLS Sendungsverfolgung Deutschland: Status und Paketshop",
      "name": "GLS Germany",
      "color": "#ffdd00"
    },
    {
      "id": "dpd-de",
      "catalog": "dpd-de",
      "slug": "dpd-sendungsverfolgung-deutschland",
      "title": "DPD Sendungsverfolgung Deutschland: Status und Hilfe",
      "name": "DPD Germany",
      "color": "#dc0032"
    },
    {
      "id": "hermes-de",
      "catalog": "hermes-de",
      "slug": "hermes-sendungsverfolgung",
      "title": "Hermes Sendungsverfolgung: Status, PaketShop und Kontakt",
      "name": "Hermes",
      "color": "#0091cd"
    },
    {
      "id": "gls-ch",
      "catalog": "gls-ch",
      "slug": "gls-schweiz-sendungsverfolgung",
      "title": "GLS Schweiz Sendungsverfolgung: Status, Post und Kontakt",
      "name": "GLS Switzerland",
      "color": "#ffdd00"
    },
    {
      "id": "dpd-ch",
      "catalog": "dpd",
      "slug": "dpd-schweiz-tracking",
      "title": "DPD Schweiz Tracking: Status, Zustellung und Kontakt",
      "name": "DPD Switzerland",
      "color": "#dc0032"
    },
    {
      "id": "inpost",
      "catalog": "inpost",
      "slug": "inpost-sendungsverfolgung",
      "title": "InPost Sendungsverfolgung: Nummern, Status & Abholung",
      "name": "InPost",
      "color": "#ffcc05"
    },
    {
      "id": "paack",
      "catalog": "paack",
      "slug": "paack-sendungsverfolgung",
      "title": "Paack Sendungsverfolgung: Bestellnummer, PLZ und Status",
      "name": "Paack",
      "color": "#5b2dee"
    },
    {
      "id": "evri",
      "catalog": "evri-uk",
      "slug": "evri-sendungsverfolgung",
      "title": "Evri Sendungsverfolgung: Nummer, Status, Zoll und Zustellung",
      "name": "Evri",
      "color": "#00014d"
    },
    {
      "id": "chronopost",
      "catalog": "chronopost",
      "slug": "chronopost-sendungsverfolgung",
      "title": "Chronopost Sendungsverfolgung: Paket aus Frankreich, Status",
      "name": "Chronopost",
      "color": "#0056a7"
    },
    {
      "id": "relais-colis",
      "catalog": "relais-colis",
      "slug": "relais-colis-sendungsverfolgung",
      "title": "Relais Colis Sendungsverfolgung: Status, Abholung & Kontakt",
      "name": "Relais Colis",
      "color": "#7bb928"
    },
    {
      "id": "poste-italiane",
      "catalog": "poste-italiane",
      "slug": "poste-italiane-sendungsverfolgung",
      "title": "Poste Italiane Sendungsverfolgung: 1UW, 3UW, Status",
      "name": "Poste Italiane",
      "color": "#eedc00"
    },
    {
      "id": "seur",
      "catalog": "seur",
      "slug": "seur-sendungsverfolgung",
      "title": "SEUR Sendungsverfolgung: Nummer, Status und Pickup",
      "name": "SEUR",
      "color": "#0063b3"
    },
    {
      "id": "mrw",
      "catalog": "mrw",
      "slug": "mrw-sendungsverfolgung",
      "title": "MRW Sendungsverfolgung: 12-stellige Nummer, Status & Kontakt",
      "name": "MRW",
      "color": "#19133b"
    },
    {
      "id": "ctt",
      "catalog": "ctt",
      "slug": "ctt-portugal-sendungsverfolgung",
      "title": "CTT Portugal Sendungsverfolgung: Status, Abholung und Zoll",
      "name": "CTT",
      "color": "#df0024"
    },
    {
      "id": "packeta",
      "catalog": "packeta",
      "slug": "packeta-sendungsverfolgung",
      "title": "Packeta Sendungsverfolgung: Z-Nummer, Status und Abholung",
      "name": "Packeta",
      "color": "#ba1b02"
    },
    {
      "id": "dpd-pl",
      "catalog": "dpd-pl",
      "slug": "dpd-poland-tracking",
      "title": "DPD Polen (DPD Polska) Sendungsverfolgung: Status & Pickup",
      "name": "DPD Poland",
      "color": "#dc0032"
    },
    {
      "id": "4px",
      "catalog": "four-px",
      "slug": "4px-sendungsverfolgung",
      "title": "4PX Sendungsverfolgung: Nummer, Status und Kontakt",
      "name": "4PX",
      "color": "#005add"
    },
    {
      "id": "swiss-post",
      "catalog": "swiss-post",
      "slug": "swiss-post-sendungsverfolgung",
      "title": "Swiss Post Sendungsverfolgung: Status, Abholung, Kontakt",
      "name": "Swiss Post",
      "color": "#ffcc00"
    },
    {
      "id": "ctt-express",
      "catalog": "ctt-express",
      "slug": "ctt-express-sendungsverfolgung",
      "title": "CTT Express Sendungsverfolgung: Status, Punkte & Kontakt",
      "name": "CTT Express",
      "color": "#df0024"
    },
    {
      "id": "dpd-fr",
      "catalog": "dpd-fr",
      "slug": "dpd-fr-sendungsverfolgung",
      "title": "DPD France Sendungsverfolgung: Nummern und Status",
      "name": "DPD France",
      "color": "#dc0032"
    },
    {
      "id": "gls-fr",
      "catalog": "gls-fr",
      "slug": "gls-france-sendungsverfolgung",
      "title": "GLS France Sendungsverfolgung: Status, Relais GLS, Kontakt",
      "name": "GLS France",
      "color": "#ffdd00"
    },
    {
      "id": "gls-it",
      "catalog": "gls-it",
      "slug": "gls-italy-sendungsverfolgung",
      "title": "GLS Italy Sendungsverfolgung: Nummern, Status & Kontakt",
      "name": "GLS Italy",
      "color": "#ffdd00"
    },
    {
      "id": "poczta-polska",
      "catalog": "poczta-polska",
      "slug": "poczta-polska-sendungsverfolgung",
      "title": "Poczta Polska Sendungsverfolgung: Nummern, Status, Abholung",
      "name": "Poczta Polska",
      "color": "#c43c2b"
    },
    {
      "id": "orlen-paczka",
      "catalog": "orlen-paczka",
      "slug": "orlen-paczka-sendungsverfolgung",
      "title": "ORLEN Paczka Sendungsverfolgung: Nummern und Status",
      "name": "ORLEN Paczka",
      "color": "#db3830"
    },
    {
      "id": "austrian-post",
      "catalog": "austrian-post",
      "slug": "austrian-post-sendungsverfolgung",
      "title": "Austrian Post Sendungsverfolgung: Status, Abholung, Hilfe",
      "name": "Austrian Post",
      "color": "#ffdc00"
    },
    {
      "id": "tipsa",
      "catalog": "tipsa",
      "slug": "tipsa-sendungsverfolgung",
      "title": "TIPSA Sendungsverfolgung: 22-stellige Nummer und Status",
      "name": "TIPSA",
      "color": "#001e62"
    },
    {
      "id": "yanwen",
      "catalog": "yanwen",
      "slug": "yanwen-sendungsverfolgung",
      "title": "Yanwen Sendungsverfolgung: Nummern, Status und Kontakt",
      "name": "Yanwen",
      "color": "#8cbe1e"
    },
    {
      "id": "an-post",
      "catalog": "an-post",
      "slug": "an-post-sendungsverfolgung",
      "title": "An Post Sendungsverfolgung: Status, Zoll und Kontakt",
      "name": "An Post",
      "color": "#00a76a"
    },
    {
      "id": "vinted-go",
      "catalog": "vinted-go",
      "slug": "vinted-go-sendungsverfolgung",
      "title": "Vinted Go Sendungsverfolgung: Status, Fristen & Hilfe",
      "name": "Vinted Go",
      "color": "#004654"
    },
    {
      "id": "amazon-logistics",
      "catalog": "amazon-logistics",
      "slug": "amazon-logistics-tracking",
      "title": "Amazon Logistics Tracking: Paket verfolgen und Hilfe",
      "name": "Amazon Logistics",
      "color": "#ff9900"
    },
    {
      "id": "dpd-uk",
      "catalog": "dpd-uk",
      "slug": "dpd-uk-sendungsverfolgung",
      "title": "DPD UK Sendungsverfolgung: 14-stellige Nummer und Status",
      "name": "DPD UK",
      "color": "#dc0032"
    },
    {
      "id": "parcelforce",
      "catalog": "parcelforce",
      "slug": "parcelforce-worldwide-sendungsverfolgung",
      "title": "Parcelforce Worldwide Sendungsverfolgung: Status & Zoll",
      "name": "Parcelforce Worldwide",
      "color": "#9d0f11"
    },
    {
      "id": "bpost",
      "catalog": "bpost",
      "slug": "bpost-sendungsverfolgung",
      "title": "bpost Sendungsverfolgung: Status, Abholung, bbox, Zoll",
      "name": "bpost",
      "color": "#ef2637"
    },
    {
      "id": "dpd-ie",
      "catalog": "dpd-ie",
      "slug": "dpd-ireland-sendungsverfolgung",
      "title": "DPD Ireland Sendungsverfolgung: Status, Pickup & Kontakt",
      "name": "DPD Ireland",
      "color": "#dc0032"
    },
    {
      "id": "gls-es",
      "catalog": "gls-es",
      "slug": "gls-spanien-sendungsverfolgung",
      "title": "GLS Spanien Sendungsverfolgung: Status, Shops & Kontakt",
      "name": "GLS Spain",
      "color": "#ffdd00"
    },
    {
      "id": "dhl-ecommerce-es",
      "catalog": "dhl-ecommerce-es",
      "slug": "dhl-ecommerce-iberia-sendungsverfolgung",
      "title": "DHL eCommerce Iberia Sendungsverfolgung: Status & Paketshops",
      "name": "DHL eCommerce Iberia",
      "color": "#ffcc00"
    },
    {
      "id": "dhl-ecommerce-pl",
      "catalog": "dhl-ecommerce-pl",
      "slug": "dhl-ecommerce-polen-sendungsverfolgung",
      "title": "DHL eCommerce Polen Sendungsverfolgung: Status & Abholung",
      "name": "DHL eCommerce Poland",
      "color": "#ffcc00"
    },
    {
      "id": "dhl-ecommerce-uk",
      "catalog": "dhl-ecommerce-uk",
      "slug": "dhl-ecommerce-uk-sendungsverfolgung",
      "title": "DHL eCommerce UK Sendungsverfolgung: Status & Kontakt",
      "name": "DHL eCommerce UK",
      "color": "#ffcc00"
    },
    {
      "id": "gofo-fr",
      "catalog": "gofo-fr",
      "slug": "gofo-france-sendungsverfolgung",
      "title": "GOFO France Sendungsverfolgung: GFFR-Nummern, Status & Hilfe",
      "name": "GOFO France",
      "color": "#fc4c02"
    },
    {
      "id": "gofo-it",
      "catalog": "gofo-it",
      "slug": "gofo-italy-sendungsverfolgung",
      "title": "GOFO Italy Sendungsverfolgung: GFIT-Nummern und Status",
      "name": "GOFO Italy",
      "color": "#fc4c02"
    },
    {
      "id": "speedpak",
      "catalog": "speedpak",
      "slug": "speedpak-sendungsverfolgung",
      "title": "SpeedPAK Sendungsverfolgung: eBay-Pakete, Status, Kontakt",
      "name": "SpeedPAK",
      "color": "#ee7532"
    },
    {
      "id": "asendia",
      "catalog": "asendia",
      "slug": "asendia-sendungsverfolgung",
      "title": "Asendia Sendungsverfolgung: Status, Zustellung, Kontakt",
      "name": "Asendia",
      "color": "#005d69"
    },
    {
      "id": "colisweb",
      "catalog": "colisweb",
      "slug": "colisweb-sendungsverfolgung",
      "title": "Colisweb Sendungsverfolgung: Status, Zeitfenster & Kontakt",
      "name": "Colisweb",
      "color": "#15cbe3"
    },
    {
      "id": "ciblex",
      "catalog": "ciblex",
      "slug": "ciblex-sendungsverfolgung",
      "title": "Ciblex Sendungsverfolgung: Nummern, Status & Kontakt",
      "name": "Ciblex",
      "color": "#0160ab"
    },
    {
      "id": "hermes-einrichtungs-service",
      "catalog": "hermes",
      "slug": "hermes-einrichtungs-service-sendungsverfolgung",
      "title": "Hermes Einrichtungs-Service Sendungsverfolgung: Liefertermin",
      "name": "Hermes Einrichtungs-Service",
      "color": "#0091cd"
    },
    {
      "id": "landmark-global",
      "catalog": "landmark-global",
      "slug": "landmark-global-sendungsverfolgung",
      "title": "Landmark Global Sendungsverfolgung: Status, Partner, Kontakt",
      "name": "Landmark Global",
      "color": "#e62337"
    },
    {
      "id": "dhl-ecommerce",
      "catalog": "dhl-ecommerce",
      "slug": "dhl-ecommerce-sendungsverfolgung",
      "title": "DHL eCommerce Sendungsverfolgung: GM-Nummer, Status, Kontakt",
      "name": "DHL eCommerce",
      "color": "#ffcc00"
    },
    {
      "id": "allegro-one",
      "catalog": "allegro-one",
      "slug": "allegro-one-sendungsverfolgung",
      "title": "Allegro One Sendungsverfolgung: One Box, Punkt & Kurier",
      "name": "Allegro One",
      "color": "#006d57"
    },
    {
      "id": "cacesa",
      "catalog": "cacesa",
      "slug": "cacesa-sendungsverfolgung",
      "title": "CACESA Sendungsverfolgung: Zoll, Status und Zustellung",
      "name": "CACESA",
      "color": "#C9032D"
    },
    {
      "id": "sunyou",
      "catalog": "sunyou",
      "slug": "sunyou-sendungsverfolgung",
      "title": "SunYou Tracking: Sendungsverfolgung, Status und Kontakt",
      "name": "SunYou",
      "color": "#02a232"
    },
    {
      "id": "amazon-shipping",
      "catalog": "amazon-shipping",
      "slug": "amazon-shipping-sendungsverfolgung",
      "title": "Amazon Shipping Sendungsverfolgung: Nummer und Hilfe",
      "name": "Amazon Shipping",
      "color": "#ff9900"
    },
    {
      "id": "postnord",
      "catalog": "postnord",
      "slug": "postnord-sendungsverfolgung",
      "title": "PostNord Sendungsverfolgung: Nummern, Status und Abholung",
      "name": "PostNord",
      "color": "#00a0d6"
    },
    {
      "id": "bring",
      "catalog": "bring-posten",
      "slug": "bring-sendungsverfolgung",
      "title": "Bring Sendungsverfolgung (Posten): Nummern, Status, Fristen",
      "name": "Bring",
      "color": "#56b529"
    },
    {
      "id": "posti",
      "catalog": "posti",
      "slug": "posti-sendungsverfolgung",
      "title": "Posti Sendungsverfolgung: JJFI-Code, Status & Kontakt",
      "name": "Posti",
      "color": "#ff8000"
    },
    {
      "id": "omniva",
      "catalog": "omniva",
      "slug": "omniva-sendungsverfolgung",
      "title": "Omniva Sendungsverfolgung: Codes, Status und Paketautomaten",
      "name": "Omniva",
      "color": "#f86300"
    },
    {
      "id": "ppl",
      "catalog": "ppl",
      "slug": "ppl-sendungsverfolgung",
      "title": "PPL Sendungsverfolgung: 11-stellige Nummer und Boxen",
      "name": "PPL",
      "color": "#004b93"
    },
    {
      "id": "box-now",
      "catalog": "box-now",
      "slug": "box-now-sendungsverfolgung",
      "title": "BOX NOW Sendungsverfolgung: Paketstationen, PIN & Status",
      "name": "BOX NOW",
      "color": "#44d62c"
    },
    {
      "id": "dhl-ecommerce-nl",
      "catalog": "dhl-ecommerce-nl",
      "slug": "dhl-ecommerce-netherlands-sendungsverfolgung",
      "title": "DHL eCommerce Niederlande Sendungsverfolgung & Kontakt",
      "name": "DHL eCommerce Netherlands",
      "color": "#ffcc00"
    },
    {
      "id": "heppner",
      "catalog": "heppner",
      "slug": "heppner-sendungsverfolgung",
      "title": "Heppner Sendungsverfolgung: Nummer, PLZ und Liefertermin",
      "name": "Heppner",
      "color": "#005baa"
    },
    {
      "id": "c-chez-vous",
      "catalog": "c-chez-vous",
      "slug": "c-chez-vous-sendungsverfolgung",
      "title": "C Chez Vous Sendungsverfolgung: Nummer, Status & Kontakt",
      "name": "C chez vous",
      "color": "#e50046"
    },
    {
      "id": "geodis",
      "catalog": "geodis",
      "slug": "geodis-sendungsverfolgung",
      "title": "GEODIS Sendungsverfolgung: 1G-Nummer, Status & Kontakt",
      "name": "GEODIS",
      "color": "#3100e6"
    },
    {
      "id": "delivengo",
      "catalog": "delivengo",
      "slug": "delivengo-tracking",
      "title": "Delivengo Sendungsverfolgung: Paket von La Poste verfolgen",
      "name": "Delivengo",
      "color": "#ffcf00"
    },
    {
      "id": "ecoscooting",
      "catalog": "ecoscooting",
      "slug": "ecoscooting-sendungsverfolgung",
      "title": "Ecoscooting Sendungsverfolgung: Status, Paketshops & Kontakt",
      "name": "Ecoscooting",
      "color": "#3fa7ff"
    },
    {
      "id": "swiss-post-cargo",
      "catalog": "swiss-post-cargo",
      "slug": "swiss-post-cargo-sendungsverfolgung",
      "title": "Swiss Post Cargo Tracking: Status, Zustellung, Kontakt",
      "name": "Swiss Post Cargo",
      "color": "#ffcc00"
    },
    {
      "id": "dachser",
      "catalog": "dachser",
      "slug": "dachser-sendungsverfolgung",
      "title": "Dachser Sendungsverfolgung Spanien & Portugal: Link, Status",
      "name": "Dachser",
      "color": "#005ca9"
    },
    {
      "id": "meest",
      "catalog": "meest",
      "slug": "meest-sendungsverfolgung",
      "title": "Meest Sendungsverfolgung: Nummern, Status und Kontakt",
      "name": "Meest",
      "color": "#0061af"
    },
    {
      "id": "nova-poshta",
      "catalog": "nova-poshta",
      "slug": "nova-poshta-sendungsverfolgung",
      "title": "Nova Poshta Sendungsverfolgung: Nummern, Status & Filialen",
      "name": "Nova Poshta",
      "color": "#e30613"
    },
    {
      "id": "sameday",
      "catalog": "sameday",
      "slug": "sameday-sendungsverfolgung",
      "title": "Sameday Sendungsverfolgung: AWB, easybox und Status",
      "name": "Sameday",
      "color": "#e92427"
    },
    {
      "id": "speedy",
      "catalog": "speedy",
      "slug": "speedy-sendungsverfolgung",
      "title": "Speedy Sendungsverfolgung: Nummer, Status & Automaten",
      "name": "Speedy",
      "color": "#dc0032"
    },
    {
      "id": "cargus",
      "catalog": "cargus",
      "slug": "cargus-sendungsverfolgung",
      "title": "Cargus Sendungsverfolgung: AWB-Nummer, Status & SHIP & GO",
      "name": "Cargus",
      "color": "#f68121"
    },
    {
      "id": "fan-courier",
      "catalog": "fan-courier",
      "slug": "fan-courier-sendungsverfolgung",
      "title": "FAN Courier Sendungsverfolgung: AWB, Status und FANbox",
      "name": "FAN Courier",
      "color": "#0f2345"
    },
    {
      "id": "econt",
      "catalog": "econt",
      "slug": "econt-sendungsverfolgung",
      "title": "Econt Sendungsverfolgung: Nummer, Status und Büros",
      "name": "Econt",
      "color": "#234182"
    },
    {
      "id": "ceska-posta",
      "catalog": "ceska-posta",
      "slug": "ceska-posta-sendungsverfolgung",
      "title": "Česká pošta (Tschechische Post): Sendungsverfolgung",
      "name": "Česká pošta",
      "color": "#002776"
    },
    {
      "id": "express-one",
      "catalog": "express-one",
      "slug": "express-one-sendungsverfolgung",
      "title": "Express One Sendungsverfolgung: Status & Kontakt",
      "name": "Express One",
      "color": "#ec6500"
    },
    {
      "id": "foxpost",
      "catalog": "foxpost",
      "slug": "foxpost-sendungsverfolgung",
      "title": "Foxpost Sendungsverfolgung: Status, Locker & Kontakt",
      "name": "Foxpost",
      "color": "#b70100"
    },
    {
      "id": "slovak-parcel-service",
      "catalog": "slovak-parcel-service",
      "slug": "slovak-parcel-service-sendungsverfolgung",
      "title": "Slovak Parcel Service (SPS) Tracking: Status und Hilfe",
      "name": "Slovak Parcel Service",
      "color": "#f58220"
    },
    {
      "id": "slovenska-posta",
      "catalog": "slovenska-posta",
      "slug": "slovenska-posta-sendungsverfolgung",
      "title": "Slovenská pošta Sendungsverfolgung: Nummern und Status",
      "name": "Slovenská pošta",
      "color": "#f9bb00"
    },
    {
      "id": "posta-slovenije",
      "catalog": "posta-slovenije",
      "slug": "posta-slovenije-sendungsverfolgung",
      "title": "Pošta Slovenije Sendungsverfolgung: Status und Kontakt",
      "name": "Pošta Slovenije",
      "color": "#ffc600"
    },
    {
      "id": "hrvatska-posta",
      "catalog": "hrvatska-posta",
      "slug": "hrvatska-posta-sendungsverfolgung",
      "title": "Hrvatska pošta Sendungsverfolgung: Status, Locker & Kontakt",
      "name": "Hrvatska pošta",
      "color": "#ffd100"
    },
    {
      "id": "posta-srbije",
      "catalog": "posta-srbije",
      "slug": "posta-srbije-sendungsverfolgung",
      "title": "Pošta Srbije Sendungsverfolgung: Status, Fristen & Kontakt",
      "name": "Pošta Srbije",
      "color": "#fbba00"
    },
    {
      "id": "venipak",
      "catalog": "venipak",
      "slug": "venipak-sendungsverfolgung",
      "title": "Venipak Sendungsverfolgung: Status, Automaten und Kontakt",
      "name": "Venipak",
      "color": "#9339f2"
    },
    {
      "id": "lietuvos-pastas",
      "catalog": "lietuvos-pastas",
      "slug": "lietuvos-pastas-sendungsverfolgung",
      "title": "Lietuvos paštas: Litauische Post & LP Express Tracking",
      "name": "Lietuvos paštas",
      "color": "#fedd00"
    },
    {
      "id": "latvijas-pasts",
      "catalog": "latvijas-pasts",
      "slug": "latvijas-pasts-sendungsverfolgung",
      "title": "Latvijas Pasts Sendungsverfolgung: Nummern und Status",
      "name": "Latvijas Pasts",
      "color": "#0c72ba"
    },
    {
      "id": "dao",
      "catalog": "dao",
      "slug": "dao-sendungsverfolgung",
      "title": "DAO Sendungsverfolgung: Status, daoSHOP & Kontakt",
      "name": "DAO",
      "color": "#e30613"
    },
    {
      "id": "helthjem",
      "catalog": "helthjem",
      "slug": "helthjem-sendungsverfolgung",
      "title": "Helthjem Sendungsverfolgung: Status, Abholstellen, Kontakt",
      "name": "Helthjem",
      "color": "#ffb202"
    },
    {
      "id": "matkahuolto",
      "catalog": "matkahuolto",
      "slug": "matkahuolto-sendungsverfolgung",
      "title": "Matkahuolto Sendungsverfolgung: Status, Abholung, Kontakt",
      "name": "Matkahuolto",
      "color": "#001e60"
    },
    {
      "id": "ukrposhta",
      "catalog": "ukrposhta",
      "slug": "ukrposhta-sendungsverfolgung",
      "title": "Ukrposhta Sendungsverfolgung: Nummern, Status & Abholung",
      "name": "Ukrposhta",
      "color": "#ffbe0c"
    },
    {
      "id": "acs-courier",
      "catalog": "acs-courier",
      "slug": "acs-courier-sendungsverfolgung",
      "title": "ACS Courier Sendungsverfolgung: Nummern, Status, Kontakt",
      "name": "ACS Courier",
      "color": "#e42229"
    },
    {
      "id": "elta",
      "catalog": "elta",
      "slug": "elta-hellenic-post-sendungsverfolgung",
      "title": "ELTA Hellenic Post Sendungsverfolgung: Status & Abholung",
      "name": "ELTA Hellenic Post",
      "color": "#0063a9"
    },
    {
      "id": "geniki-taxydromiki",
      "catalog": "geniki-taxydromiki",
      "slug": "geniki-taxydromiki-sendungsverfolgung",
      "title": "Geniki Taxydromiki Sendungsverfolgung: Status & Kontakt",
      "name": "Geniki Taxydromiki",
      "color": "#004fa0"
    },
    {
      "id": "cyprus-post",
      "catalog": "cyprus-post",
      "slug": "cyprus-post-sendungsverfolgung",
      "title": "Cyprus Post Sendungsverfolgung: Nummern, Status & Zoll",
      "name": "Cyprus Post",
      "color": "#e4a50e"
    },
    {
      "id": "post-luxembourg",
      "catalog": "post-luxembourg",
      "slug": "post-luxembourg-sendungsverfolgung",
      "title": "POST Luxembourg Sendungsverfolgung: PackUp, Abholung, Zoll",
      "name": "POST Luxembourg",
      "color": "#008736"
    },
    {
      "id": "maltapost",
      "catalog": "maltapost",
      "slug": "maltapost-sendungsverfolgung",
      "title": "MaltaPost Sendungsverfolgung: Nummern, Status & Abholung",
      "name": "MaltaPost",
      "color": "#d43b2c"
    },
    {
      "id": "whistl",
      "catalog": "whistl",
      "slug": "whistl-sendungsverfolgung",
      "title": "Whistl Sendungsverfolgung: wer liefert und was tun",
      "name": "Whistl",
      "color": "#fd4f00"
    },
    {
      "id": "xdp",
      "catalog": "xdp",
      "slug": "xdp-sendungsverfolgung",
      "title": "XDP Sendungsverfolgung: Sendungsnummer, Status & Kontakt",
      "name": "XDP",
      "color": "#53b6ed"
    },
    {
      "id": "apc-overnight",
      "catalog": "apc-overnight",
      "slug": "apc-overnight-sendungsverfolgung",
      "title": "APC Overnight Sendungsverfolgung: Nummern und Status",
      "name": "APC Overnight",
      "color": "#243269"
    },
    {
      "id": "ups",
      "catalog": "ups",
      "slug": "ups-sendungsverfolgung",
      "title": "UPS Sendungsverfolgung: Nummer, Status und Zustellung",
      "name": "UPS",
      "color": "#351c15"
    },
    {
      "id": "fedex",
      "catalog": "fedex",
      "slug": "fedex-sendungsverfolgung",
      "title": "FedEx Tracking und Sendungsverfolgung: Status und Hilfe",
      "name": "FedEx",
      "color": "#4d148c"
    },
    {
      "id": "dhl-express",
      "catalog": "dhl-express",
      "slug": "dhl-express-sendungsverfolgung",
      "title": "DHL Express Sendungsverfolgung: Nummer, Status, Kontakt",
      "name": "DHL Express",
      "color": "#ffcc00"
    },
    {
      "id": "dhl",
      "catalog": "dhl",
      "slug": "dhl-paket-sendungsverfolgung",
      "title": "DHL Paket Sendungsverfolgung: Nummer, Status, Packstation",
      "name": "DHL Paket",
      "color": "#ffcc00"
    },
    {
      "id": "la-poste",
      "catalog": "la-poste",
      "slug": "colissimo-la-poste-sendungsverfolgung",
      "title": "Colissimo & La Poste Sendungsverfolgung: Status & Zoll",
      "name": "La Poste / Colissimo",
      "color": "#ffcf00"
    },
    {
      "id": "correos-spain",
      "catalog": "correos-spain",
      "slug": "correos-spanien-sendungsverfolgung",
      "title": "Correos Sendungsverfolgung: Pakete aus Spanien verfolgen",
      "name": "Correos",
      "color": "#ffcd00"
    },
    {
      "id": "royal-mail",
      "catalog": "royal-mail",
      "slug": "royal-mail-sendungsverfolgung",
      "title": "Royal Mail Sendungsverfolgung: Nummer, Status und Zoll",
      "name": "Royal Mail",
      "color": "#da202a"
    },
    {
      "id": "usps",
      "catalog": "usps",
      "slug": "usps-sendungsverfolgung",
      "title": "USPS Sendungsverfolgung: Nummer, Status, Paket aus den USA",
      "name": "USPS",
      "color": "#004b87"
    },
    {
      "id": "canada-post",
      "catalog": "canada-post",
      "slug": "canada-post-sendungsverfolgung",
      "title": "Canada Post Sendungsverfolgung: Paket aus Kanada, Status",
      "name": "Canada Post",
      "color": "#e4002b"
    },
    {
      "id": "purolator",
      "catalog": "purolator",
      "slug": "purolator-sendungsverfolgung",
      "title": "Purolator Sendungsverfolgung: PIN, Status und Abholung",
      "name": "Purolator",
      "color": "#da291c"
    },
    {
      "id": "intelcom",
      "catalog": "intelcom",
      "slug": "intelcom-dragonfly-sendungsverfolgung",
      "title": "Intelcom / Dragonfly Sendungsverfolgung: Status & Kontakt",
      "name": "Intelcom / Dragonfly",
      "color": "#13a58f"
    },
    {
      "id": "canpar",
      "catalog": "canpar",
      "slug": "canpar-sendungsverfolgung",
      "title": "Canpar Sendungsverfolgung: Nummern, Status und SMARTSpot",
      "name": "Canpar",
      "color": "#003b70"
    },
    {
      "id": "uniuni",
      "catalog": "uniuni",
      "slug": "uniuni-tracking",
      "title": "UniUni Sendungsverfolgung: Status, Zustellung und Kontakt",
      "name": "UniUni",
      "color": "#f68841"
    },
    {
      "id": "ontrac",
      "catalog": "ontrac",
      "slug": "ontrac-sendungsverfolgung",
      "title": "OnTrac Sendungsverfolgung: Nummern, Status und Hilfe",
      "name": "OnTrac",
      "color": "#cc2027"
    },
    {
      "id": "lso",
      "catalog": "lso",
      "slug": "lso-sendungsverfolgung",
      "title": "LSO Sendungsverfolgung: Nummern, Status und Lieferung",
      "name": "LSO",
      "color": "#0047ba"
    },
    {
      "id": "gofo",
      "catalog": "gofo",
      "slug": "gofo-express-sendungsverfolgung",
      "title": "GOFO Express Sendungsverfolgung: GFUS-Nummer & Status",
      "name": "GOFO Express",
      "color": "#fc4c02"
    },
    {
      "id": "speedx",
      "catalog": "speedx",
      "slug": "speedx-sendungsverfolgung",
      "title": "SpeedX Sendungsverfolgung: SPX-Nummer, Status & Kontakt",
      "name": "SpeedX",
      "color": "#1aa3ff"
    },
    {
      "id": "australia-post",
      "catalog": "australia-post",
      "slug": "australia-post-sendungsverfolgung",
      "title": "Australia Post Sendungsverfolgung: Nummern, Status & Fach",
      "name": "Australia Post",
      "color": "#dc1928"
    },
    {
      "id": "aramex-au",
      "catalog": "aramex-au",
      "slug": "aramex-australien-sendungsverfolgung",
      "title": "Aramex Australien Tracking: Nummern, Status & Kontakt",
      "name": "Aramex Australia",
      "color": "#dc291e"
    },
    {
      "id": "couriersplease",
      "catalog": "couriersplease",
      "slug": "couriersplease-sendungsverfolgung",
      "title": "CouriersPlease Sendungsverfolgung: Nummern & Hilfe",
      "name": "CouriersPlease",
      "color": "#c21e36"
    },
    {
      "id": "nz-post",
      "catalog": "nz-post",
      "slug": "nz-post-sendungsverfolgung",
      "title": "NZ Post Sendungsverfolgung: Nummern, Status und Abholung",
      "name": "NZ Post",
      "color": "#d8291c"
    },
    {
      "id": "aramex-nz",
      "catalog": "aramex-nz",
      "slug": "aramex-neuseeland-sendungsverfolgung",
      "title": "Aramex Neuseeland Sendungsverfolgung: Status & Kontakte",
      "name": "Aramex New Zealand",
      "color": "#dc291e"
    },
    {
      "id": "magyar-posta",
      "catalog": "magyar-posta",
      "slug": "magyar-posta-sendungsverfolgung",
      "title": "Magyar Posta Sendungsverfolgung: Status, PostaPont und Zoll",
      "name": "Magyar Posta",
      "color": "#346631"
    },
    {
      "id": "shiprocket",
      "catalog": "shiprocket",
      "slug": "shiprocket-sendungsverfolgung",
      "title": "Shiprocket Sendungsverfolgung: AWB-Nummern, Status & RTO",
      "name": "Shiprocket",
      "color": "#735ae5"
    },
    {
      "id": "first-line",
      "catalog": "first-line",
      "slug": "first-line-sendungsverfolgung",
      "title": "FIRST LINE Sendungsverfolgung: 1ST-Nummern und Status",
      "name": "FIRST LINE",
      "color": "#55207a"
    },
    {
      "id": "j-and-t-de",
      "catalog": "j-and-t-de",
      "slug": "jt-express-sendungsverfolgung",
      "title": "J&T Express Sendungsverfolgung: JTDE-Nummer und Status",
      "name": "J&T Express Germany",
      "color": "#e60000"
    },
    {
      "id": "kun-express",
      "catalog": "kun-express",
      "slug": "kun-express-sendungsverfolgung",
      "title": "Kun Express Sendungsverfolgung: KUN-Nummern und USPS",
      "name": "Kun Express",
      "color": "#0761ff"
    },
    {
      "id": "juxi-express",
      "catalog": "juxi-express",
      "slug": "juxi-express-sendungsverfolgung",
      "title": "Juxi Express Sendungsverfolgung: JXC-Nummer, Zusteller, Zoll",
      "name": "Juxi Express",
      "color": "#e2393c"
    },
    {
      "id": "lingdong",
      "catalog": "lingdong",
      "slug": "lingdong-sendungsverfolgung",
      "title": "Lingdong Sendungsverfolgung: SZLD-Nummer, Status und Zoll",
      "name": "Lingdong International",
      "color": "#0171bb"
    },
    {
      "id": "j-and-t-au",
      "catalog": "j-and-t-au",
      "slug": "jt-express-australia-sendungsverfolgung",
      "title": "J&T Express Australia Tracking: JTAU-Nummern und Status",
      "name": "J&T Express Australia",
      "color": "#e60000"
    },
    {
      "id": "china-post",
      "catalog": "china-post",
      "slug": "china-post-sendungsverfolgung",
      "title": "China Post Sendungsverfolgung: Nummer, Status, Zustellung",
      "name": "China Post",
      "color": "#006d46"
    },
    {
      "id": "fedex-pl",
      "catalog": "fedex-pl",
      "slug": "fedex-polen-sendungsverfolgung",
      "title": "FedEx Polen Sendungsverfolgung: 13-stellige Nummern & Status",
      "name": "FedEx Poland",
      "color": "#4d148c"
    },
    {
      "id": "techlink",
      "catalog": "techlink",
      "slug": "techlink-sendungsverfolgung",
      "title": "Techlink Sendungsverfolgung: ZHPHD…ML-Nummer und Status",
      "name": "Techlink",
      "color": "#e73547"
    },
    {
      "id": "z-leading",
      "catalog": "z-leading",
      "slug": "z-leading-sendungsverfolgung",
      "title": "Z-Leading (Zhongling) Tracking: Nummern, Lieferung, Hilfe",
      "name": "Z-Leading",
      "color": "#005ab0"
    },
    {
      "id": "imile",
      "catalog": "imile",
      "slug": "imile-sendungsverfolgung",
      "title": "iMile Sendungsverfolgung: Nummer, Status, Zustellung",
      "name": "iMile",
      "color": "#0032a0"
    },
    {
      "id": "postnl",
      "catalog": "spring-gds",
      "slug": "postnl-sendungsverfolgung",
      "title": "PostNL Sendungsverfolgung: 3S- und NL-Nummern & Status",
      "name": "PostNL",
      "color": "#ef7d00"
    },
    {
      "id": "dhlink",
      "catalog": "dhlink",
      "slug": "dhlink-sendungsverfolgung",
      "title": "DHLink Sendungsverfolgung: Nummer, Status und Zustellung",
      "name": "DHLink",
      "color": "#143167"
    },
    {
      "id": "dpd-pt",
      "catalog": "dpd-pt",
      "slug": "dpd-portugal-sendungsverfolgung",
      "title": "DPD Portugal Sendungsverfolgung: Status, Pickup & Kontakt",
      "name": "DPD Portugal",
      "color": "#dc0032"
    },
    {
      "id": "tnt-it",
      "catalog": "tnt-it",
      "slug": "tnt-italien-sendungsverfolgung",
      "title": "TNT Italien Sendungsverfolgung: Frachtbrief und Status",
      "name": "TNT Italy",
      "color": "#ff6600"
    },
    {
      "id": "tnt",
      "catalog": "tnt",
      "slug": "tnt-sendungsverfolgung",
      "title": "TNT Sendungsverfolgung: Sendungsnummer, Status, Zustellung",
      "name": "TNT",
      "color": "#ff6600"
    },
    {
      "id": "sf-express",
      "catalog": "sf-express",
      "slug": "sf-express-sendungsverfolgung",
      "title": "SF Express Sendungsverfolgung: Nummer, Status, Zustellung",
      "name": "SF Express",
      "color": "#dc1e32"
    },
    {
      "id": "straightship",
      "catalog": "straightship",
      "slug": "straightship-sendungsverfolgung",
      "title": "Straightship Sendungsverfolgung: STRTD-Nummern & Zustellung",
      "name": "Straightship",
      "color": "#6aae66"
    },
    {
      "id": "correios-br",
      "catalog": "correios-br",
      "slug": "correios-brasilien-sendungsverfolgung",
      "title": "Correios Brasilien Sendungsverfolgung: Codes, Status, Zoll",
      "name": "Correios Brazil",
      "color": "#ffd500"
    },
    {
      "id": "yto",
      "catalog": "yto",
      "slug": "yto-express-sendungsverfolgung",
      "title": "YTO Express Sendungsverfolgung: YT-Nummer & Status",
      "name": "YTO Express",
      "color": "#4d148c"
    },
    {
      "id": "spx-br",
      "catalog": "spx-br",
      "slug": "spx-express-brasilien-sendungsverfolgung",
      "title": "SPX Express Brasilien Sendungsverfolgung: Shopee & Status",
      "name": "SPX Express Brazil",
      "color": "#ee4d2d"
    },
    {
      "id": "eushipments",
      "catalog": "eushipments",
      "slug": "eushipments-sendungsverfolgung",
      "title": "euShipments Sendungsverfolgung: Status und Zusteller",
      "name": "euShipments",
      "color": "#0a8fcf"
    },
    {
      "id": "mhi",
      "catalog": "mhi",
      "slug": "mhi-sendungsverfolgung",
      "title": "MHI Sendungsverfolgung: Trackmail-Status und Zusteller",
      "name": "MHI",
      "color": "#00075c"
    },
    {
      "id": "pengyou",
      "catalog": "pengyou",
      "slug": "pengyou-sendungsverfolgung",
      "title": "Pengyou Sendungsverfolgung: PY-Nummer, Status & Hilfe",
      "name": "Pengyou",
      "color": "#043a82"
    },
    {
      "id": "linhan",
      "catalog": "linhan",
      "slug": "linhan-sendungsverfolgung",
      "title": "LinHan International Sendungsverfolgung: LHGJ und Status",
      "name": "LinHan International",
      "color": "#eb5c20"
    },
    {
      "id": "mtscn",
      "catalog": "mtscn",
      "slug": "meiteshun-sendungsverfolgung",
      "title": "Meiteshun (MTSCN) Sendungsverfolgung: AMTS-Nummer, Status",
      "name": "Meiteshun Logistics",
      "color": "#f89f3b"
    },
    {
      "id": "cttyex",
      "catalog": "cttyex",
      "slug": "cttyex-sendungsverfolgung",
      "title": "CTTYEX Sendungsverfolgung: CTTY…CN-Nummern & Pekinger Zeit",
      "name": "CTTYEX",
      "color": "#0068AC"
    },
    {
      "id": "speedaf",
      "catalog": "speedaf",
      "slug": "speedaf-sendungsverfolgung",
      "title": "Speedaf Express Sendungsverfolgung: Nummer und Status",
      "name": "Speedaf",
      "color": "#ff8300"
    },
    {
      "id": "yilong-logistics",
      "catalog": "yilong-logistics",
      "slug": "yilong-logistics-sendungsverfolgung",
      "title": "Yilong Logistics Sendungsverfolgung: YIL-Nummern & Status",
      "name": "Yilong Logistics",
      "color": "#ec0307"
    },
    {
      "id": "quick-rabbit",
      "catalog": "quick-rabbit",
      "slug": "quick-rabbit-sendungsverfolgung",
      "title": "Quick Rabbit Sendungsverfolgung: PKG-Code, Status, Citibox",
      "name": "Quick Rabbit",
      "color": "#f6851f"
    },
    {
      "id": "huaya-express",
      "catalog": "huaya-express",
      "slug": "huaya-express-sendungsverfolgung",
      "title": "Huaya Express Sendungsverfolgung: HYYT-Nummern & Status",
      "name": "Huaya Express",
      "color": "#e60013"
    },
    {
      "id": "gofo-ca",
      "catalog": "gofo-ca",
      "slug": "gofo-ca-sendungsverfolgung",
      "title": "GOFO Canada Sendungsverfolgung: GFCA-Nummern und Status",
      "name": "GOFO Canada",
      "color": "#fc4c02"
    },
    {
      "id": "metropolitan-warehouse",
      "catalog": "metropolitan-warehouse",
      "slug": "metropolitan-warehouse-sendungsverfolgung",
      "title": "Metropolitan Warehouse (MWD) Sendungsverfolgung",
      "name": "Metropolitan Warehouse & Delivery",
      "color": "#0062b7"
    },
    {
      "id": "topyou",
      "catalog": "topyou",
      "slug": "topyou-sendungsverfolgung",
      "title": "TopYou Sendungsverfolgung: TYZR-Nummer, Status, Zustellung",
      "name": "TopYou",
      "color": "#045cd7"
    },
    {
      "id": "byhd",
      "catalog": "byhd",
      "slug": "byhd-sendungsverfolgung",
      "title": "BYHD Sendungsverfolgung: Nummern, Status und Zusteller",
      "name": "BYHD",
      "color": "#084282"
    },
    {
      "id": "zto-international",
      "catalog": "zto-international",
      "slug": "zto-international-sendungsverfolgung",
      "title": "ZTO International Sendungsverfolgung: 771-Nummer & Status",
      "name": "ZTO International",
      "color": "#3a8eff"
    },
    {
      "id": "mayiyinfu",
      "catalog": "mayiyinfu",
      "slug": "mayi-yinfu-sendungsverfolgung",
      "title": "Mayi Yinfu Sendungsverfolgung: MY-Nummern, Flüge und DHL",
      "name": "Mayi Yinfu",
      "color": "#000000"
    },
    {
      "id": "jrt",
      "catalog": "jrt",
      "slug": "jrt-sendungsverfolgung",
      "title": "JRT Sendungsverfolgung: JRT…MM-Nummer, Flüge und DHL",
      "name": "JRT",
      "color": "#023053"
    },
    {
      "id": "thhy",
      "catalog": "thhy",
      "slug": "tianhui-hengyuan-sendungsverfolgung",
      "title": "Tianhui Hengyuan (THHY) Sendungsverfolgung: AT…TH-Nummer",
      "name": "Tianhui Hengyuan",
      "color": "#00a2e9"
    },
    {
      "id": "js-express",
      "catalog": "js-express",
      "slug": "js-express-sendungsverfolgung",
      "title": "JS Express Sendungsverfolgung: JSE-Nummer, Status, Zusteller",
      "name": "JS Express",
      "color": "#fe9b00"
    },
    {
      "id": "wanbexpress",
      "catalog": "wanbexpress",
      "slug": "wanbexpress-sendungsverfolgung",
      "title": "WanbExpress Sendungsverfolgung: WNB…YQ-Nummern und Status",
      "name": "WanbExpress",
      "color": "#da381b"
    },
    {
      "id": "gzyl",
      "catalog": "gzyl",
      "slug": "gzyl-sendungsverfolgung",
      "title": "GZYL Sendungsverfolgung: YL…YQ-Nummer, Status und Zustellung",
      "name": "GZYL",
      "color": "#4e79e1"
    },
    {
      "id": "shouwu",
      "catalog": "shouwu",
      "slug": "shouwu-sendungsverfolgung",
      "title": "Shouwu Sendungsverfolgung: SWECN…YQ, Status und Zustellung",
      "name": "Shouwu International",
      "color": "#be2c33"
    },
    {
      "id": "geodis-pl",
      "catalog": "geodis-pl",
      "slug": "geodis-polen-sendungsverfolgung",
      "title": "GEODIS Polen Sendungsverfolgung: PL-Nummer & Zustellung",
      "name": "GEODIS Poland",
      "color": "#3100e6"
    },
    {
      "id": "sending",
      "catalog": "sending",
      "slug": "sending-sendungsverfolgung",
      "title": "Sending Sendungsverfolgung: Nummer, Status und Telefon",
      "name": "Sending",
      "color": "#e52021"
    },
    {
      "id": "ywe",
      "catalog": "ywe",
      "slug": "ywe-sendungsverfolgung",
      "title": "YWE (YanwenExpress) Sendungsverfolgung: Nummer & Status",
      "name": "YWE",
      "color": "#055aa1"
    },
    {
      "id": "roadie",
      "catalog": "roadie",
      "slug": "roadie-sendungsverfolgung",
      "title": "Roadie Sendungsverfolgung: 1R-Nummern, Status & Fotos",
      "name": "Roadie",
      "color": "#76cccd"
    },
    {
      "id": "ecms-express",
      "catalog": "ecms-express",
      "slug": "ecms-express-sendungsverfolgung",
      "title": "ECMS Express Sendungsverfolgung: 16-stellige Nummern & Zoll",
      "name": "ECMS Express",
      "color": "#12823d"
    },
    {
      "id": "chunghwa-post",
      "catalog": "chunghwa-post",
      "slug": "chunghwa-post-tracking",
      "title": "Chunghwa Post (Taiwan Post) Tracking: TW-Nummern & Status",
      "name": "Chunghwa Post",
      "color": "#e8171f"
    },
    {
      "id": "raben",
      "catalog": "raben",
      "slug": "raben-sendungsverfolgung",
      "title": "Raben Sendungsverfolgung: Nummer, Status & Lieferung",
      "name": "Raben",
      "color": "#df0020"
    },
    {
      "id": "jd-logistics",
      "catalog": "jd-logistics",
      "slug": "jd-logistics-sendungsverfolgung",
      "title": "JD Logistics Sendungsverfolgung: Nummern, Status & Kontakt",
      "name": "JD Logistics",
      "color": "#e1251b"
    },
    {
      "id": "singapore-post",
      "catalog": "singapore-post",
      "slug": "singapore-post-sendungsverfolgung",
      "title": "Singapore Post (SingPost) Sendungsverfolgung & Status",
      "name": "Singapore Post",
      "color": "#2e5da8"
    },
    {
      "id": "zto",
      "catalog": "zto",
      "slug": "zto-express-sendungsverfolgung",
      "title": "ZTO Express Sendungsverfolgung: 中通快递-Nummer und Status",
      "name": "ZTO Express",
      "color": "#0279ff"
    },
    {
      "id": "cne",
      "catalog": "cne",
      "slug": "cne-express-sendungsverfolgung",
      "title": "CNE Express Sendungsverfolgung: 3A5V-Nummern & Status",
      "name": "CNE Express",
      "color": "#e83828"
    },
    {
      "id": "japan-post",
      "catalog": "japan-post",
      "slug": "japan-post-sendungsverfolgung",
      "title": "Japan Post Sendungsverfolgung: EMS-Nummern und Status",
      "name": "Japan Post",
      "color": "#cc0000"
    },
    {
      "id": "russian-post",
      "catalog": "russian-post",
      "slug": "russian-post-sendungsverfolgung",
      "title": "Russian Post Sendungsverfolgung (Почта России)",
      "name": "Russian Post",
      "color": "#1928dc"
    },
    {
      "id": "huahan",
      "catalog": "huahan",
      "slug": "huahan-logistics-sendungsverfolgung",
      "title": "HuaHan Logistics Sendungsverfolgung: HHW…YQ & Zusteller",
      "name": "HuaHan Logistics",
      "color": "#0069e8"
    },
    {
      "id": "epost-global",
      "catalog": "epost-global",
      "slug": "epost-global-sendungsverfolgung",
      "title": "ePost Global Sendungsverfolgung: EPG-Nummern & Zusteller",
      "name": "ePost Global",
      "color": "#27967a"
    },
    {
      "id": "gls-at",
      "catalog": "gls-at",
      "slug": "gls-austria-sendungsverfolgung",
      "title": "GLS Austria Sendungsverfolgung: Status, Shops & Kontakt",
      "name": "GLS Austria",
      "color": "#ffdd00"
    },
    {
      "id": "india-post",
      "catalog": "india-post",
      "slug": "india-post-sendungsverfolgung",
      "title": "India Post Sendungsverfolgung: Speed-Post-Nummer und Status",
      "name": "India Post",
      "color": "#b82025"
    },
    {
      "id": "morning-global",
      "catalog": "morning-global",
      "slug": "morning-global-sendungsverfolgung",
      "title": "Morning Global Sendungsverfolgung: BR…MG-Nummern & Status",
      "name": "Morning Global",
      "color": "#050059"
    },
    {
      "id": "border-express",
      "catalog": "border-express",
      "slug": "border-express-sendungsverfolgung",
      "title": "Border Express (BEX) Sendungsverfolgung: Nummern & Status",
      "name": "Border Express",
      "color": "#cc092f"
    },
    {
      "id": "delhivery",
      "catalog": "delhivery",
      "slug": "delhivery-sendungsverfolgung",
      "title": "Delhivery Sendungsverfolgung: AWB, Status und Support",
      "name": "Delhivery",
      "color": "#ed4136"
    },
    {
      "id": "aras-kargo",
      "catalog": "aras-kargo",
      "slug": "aras-kargo-sendungsverfolgung",
      "title": "Aras Kargo Sendungsverfolgung: Nummern und Status",
      "name": "Aras Kargo",
      "color": "#dc0812"
    },
    {
      "id": "emirates-post",
      "catalog": "emirates-post",
      "slug": "emirates-post-sendungsverfolgung",
      "title": "Emirates Post Sendungsverfolgung: AE-Nummern und Status",
      "name": "Emirates Post",
      "color": "#004a98"
    },
    {
      "id": "blue-dart",
      "catalog": "blue-dart",
      "slug": "blue-dart-sendungsverfolgung",
      "title": "Blue Dart Sendungsverfolgung: Nummern und Status erklärt",
      "name": "Blue Dart",
      "color": "#0059a9"
    },
    {
      "id": "ekart",
      "catalog": "ekart",
      "slug": "ekart-sendungsverfolgung",
      "title": "Ekart Sendungsverfolgung: Tracking-ID, Status & RTO erklärt",
      "name": "Ekart",
      "color": "#0074D9"
    },
    {
      "id": "j-and-t-middle-east",
      "catalog": "j-and-t-middle-east",
      "slug": "jt-express-middle-east-sendungsverfolgung",
      "title": "J&T Express Middle East Tracking: JTE-Nummern & Status",
      "name": "J&T Express Middle East",
      "color": "#e60000"
    },
    {
      "id": "dpd-ro",
      "catalog": "dpd-ro",
      "slug": "dpd-romania-sendungsverfolgung",
      "title": "DPD Romania Sendungsverfolgung: Status & Smart Control",
      "name": "DPD Romania",
      "color": "#dc0032"
    },
    {
      "id": "globalpost",
      "catalog": "globalpost",
      "slug": "globalpost-sendungsverfolgung",
      "title": "GlobalPost Sendungsverfolgung: GP-Nummern, Status & Zoll",
      "name": "GlobalPost",
      "color": "#11003a"
    },
    {
      "id": "cdek",
      "catalog": "cdek",
      "slug": "cdek-sendungsverfolgung",
      "title": "CDEK Sendungsverfolgung (СДЭК): Nummern und Status",
      "name": "CDEK",
      "color": "#00b33c"
    },
    {
      "id": "forza-delivery",
      "catalog": "forza-delivery",
      "slug": "forza-delivery-sendungsverfolgung",
      "title": "Forza Delivery Tracking: Guía-Nummern, Status und Kontakt",
      "name": "Forza Delivery Express",
      "color": "#ea5c1d"
    },
    {
      "id": "quantium-solutions",
      "catalog": "quantium-solutions",
      "slug": "quantium-solutions-sendungsverfolgung",
      "title": "Quantium Solutions Sendungsverfolgung: Nummern & Hilfe",
      "name": "Quantium Solutions",
      "color": "#00474f"
    },
    {
      "id": "correos-chile",
      "catalog": "correos-chile",
      "slug": "correos-chile-sendungsverfolgung",
      "title": "Correos de Chile Sendungsverfolgung: Nummern und Status",
      "name": "Correos de Chile",
      "color": "#d9232d"
    },
    {
      "id": "posta-shqiptare",
      "catalog": "posta-shqiptare",
      "slug": "posta-shqiptare-sendungsverfolgung",
      "title": "Posta Shqiptare Sendungsverfolgung: AL-Nummern und Zoll",
      "name": "Posta Shqiptare",
      "color": "#1a3292"
    },
    {
      "id": "dsv",
      "catalog": "dsv",
      "slug": "dsv-sendungsverfolgung",
      "title": "DSV Sendungsverfolgung: Nummern, Status & DB Schenker",
      "name": "DSV",
      "color": "#002664"
    },
    {
      "id": "pin-ag",
      "catalog": "pin-ag",
      "slug": "pin-ag-sendungsverfolgung",
      "title": "PIN AG Sendungsverfolgung: Einschreiben, Pakete & Abholung",
      "name": "PIN AG",
      "color": "#003366"
    },
    {
      "id": "pitney-bowes",
      "catalog": "pitney-bowes",
      "slug": "pitney-bowes-sendungsverfolgung",
      "title": "Pitney Bowes Sendungsverfolgung: UPAA, UPBE und PBX",
      "name": "Pitney Bowes",
      "color": "#3e53a4"
    },
    {
      "id": "rl-carriers",
      "catalog": "rl-carriers",
      "slug": "rl-carriers-sendungsverfolgung",
      "title": "R+L Carriers Sendungsverfolgung: PRO-Nummer und Status",
      "name": "R+L Carriers",
      "color": "#006647"
    },
    {
      "id": "nox",
      "catalog": "nox",
      "slug": "nox-sendungsverfolgung",
      "title": "nox NachtExpress Sendungsverfolgung: Status & Abstellplatz",
      "name": "nox",
      "color": "#fadb00"
    },
    {
      "id": "far-international",
      "catalog": "far-international",
      "slug": "far-international-sendungsverfolgung",
      "title": "FAR International Sendungsverfolgung: Status und Kontakt",
      "name": "FAR International",
      "color": "#003b75"
    },
    {
      "id": "jifan",
      "catalog": "jifan",
      "slug": "jifan-sendungsverfolgung",
      "title": "Jifan Sendungsverfolgung: JFGJ-Nummern & Pekinger Zeit",
      "name": "Jifan",
      "color": "#c81011"
    },
    {
      "id": "posta-romana",
      "catalog": "posta-romana",
      "slug": "posta-romana-sendungsverfolgung",
      "title": "Poșta Română Sendungsverfolgung: RO-Nummern und Zoll",
      "name": "Poșta Română",
      "color": "#ea0029"
    },
    {
      "id": "dpd-nl",
      "catalog": "dpd-nl",
      "slug": "dpd-niederlande-sendungsverfolgung",
      "title": "DPD Niederlande Sendungsverfolgung: Status & Kontakt",
      "name": "DPD Netherlands",
      "color": "#dc0032"
    },
    {
      "id": "dpd-at",
      "catalog": "dpd-at",
      "slug": "dpd-austria-sendungsverfolgung",
      "title": "DPD Austria Sendungsverfolgung: Status, Pickup & Kontakt",
      "name": "DPD Austria",
      "color": "#dc0032"
    },
    {
      "id": "ozon-logistics",
      "catalog": "ozon-logistics",
      "slug": "ozon-sendungsverfolgung",
      "title": "Ozon Sendungsverfolgung: Nummern und Status auf Deutsch",
      "name": "Ozon",
      "color": "#005bff"
    },
    {
      "id": "citymail",
      "catalog": "citymail",
      "slug": "citymail-sendungsverfolgung",
      "title": "CityMail Sendungsverfolgung: Nummern & Status Schweden",
      "name": "CityMail",
      "color": "#fc4c02"
    },
    {
      "id": "dayone",
      "catalog": "dayone",
      "slug": "dayone-sendungsverfolgung",
      "title": "DayOne Sendungsverfolgung: DOT-Nummern, Status & Zusteller",
      "name": "DayOne",
      "color": "#e86d1c"
    },
    {
      "id": "dpd-be",
      "catalog": "dpd-be",
      "slug": "dpd-belgium-sendungsverfolgung",
      "title": "DPD Belgium Sendungsverfolgung: Status, Pickup & Kontakt",
      "name": "DPD Belgium",
      "color": "#dc0032"
    },
    {
      "id": "packlink",
      "catalog": "packlink",
      "slug": "packlink-sendungsverfolgung",
      "title": "Packlink Sendungsverfolgung: Referenz, Status und Kontakt",
      "name": "Packlink",
      "color": "#ff4f00"
    },
    {
      "id": "sto",
      "catalog": "sto",
      "slug": "sto-express-sendungsverfolgung",
      "title": "STO Express Sendungsverfolgung: 申通快递 Nummern & Status",
      "name": "STO Express",
      "color": "#ff6700"
    },
    {
      "id": "j-and-t-cn",
      "catalog": "j-and-t-cn",
      "slug": "jt-express-china-sendungsverfolgung",
      "title": "J&T Express China Sendungsverfolgung: JT-Nummern & Status",
      "name": "J&T Express China",
      "color": "#e62019"
    },
    {
      "id": "tforce-freight",
      "catalog": "tforce-freight",
      "slug": "tforce-freight-sendungsverfolgung",
      "title": "TForce Freight Sendungsverfolgung: PRO-Nummer & Status",
      "name": "TForce Freight",
      "color": "#0053a1"
    },
    {
      "id": "andreani",
      "catalog": "andreani",
      "slug": "andreani-sendungsverfolgung",
      "title": "Andreani Sendungsverfolgung: Nummern & Status Argentinien",
      "name": "Andreani",
      "color": "#d71920"
    },
    {
      "id": "mainfreight",
      "catalog": "mainfreight",
      "slug": "mainfreight-sendungsverfolgung",
      "title": "Mainfreight Sendungsverfolgung: Nummern und Status",
      "name": "Mainfreight",
      "color": "#005eb8"
    },
    {
      "id": "spx-sg",
      "catalog": "spx-sg",
      "slug": "spx-express-singapore-sendungsverfolgung",
      "title": "SPX Express Singapur Sendungsverfolgung & Shopee-Status",
      "name": "SPX Express Singapore",
      "color": "#ee4d2d"
    },
    {
      "id": "dai-post",
      "catalog": "dai-post",
      "slug": "dai-post-sendungsverfolgung",
      "title": "DAI Post Sendungsverfolgung: Nummern, Status & Hilfe",
      "name": "DAI Post",
      "color": "#008faf"
    },
    {
      "id": "eurodis",
      "catalog": "eurodis",
      "slug": "eurodis-sendungsverfolgung",
      "title": "EURODIS Sendungsverfolgung: Nummern und Zustellpartner",
      "name": "EURODIS",
      "color": "#0b418b"
    },
    {
      "id": "chronodiali",
      "catalog": "chronodiali",
      "slug": "chronodiali-sendungsverfolgung",
      "title": "Chronodiali Sendungsverfolgung: CHPU-Nummern & Kontakt",
      "name": "Chronodiali",
      "color": "#009adf"
    },
    {
      "id": "17feia",
      "catalog": "17feia",
      "slug": "17feia-sendungsverfolgung",
      "title": "17FEIA Sendungsverfolgung: FEI…YQ-Nummern und Status",
      "name": "17FEIA",
      "color": "#00a9e4"
    },
    {
      "id": "tsjy",
      "catalog": "tsjy",
      "slug": "tsjy-sendungsverfolgung",
      "title": "TSJY Sendungsverfolgung (通世集运): Nummern und Status",
      "name": "TSJY",
      "color": "#4042e2"
    },
    {
      "id": "stone3pl",
      "catalog": "stone3pl",
      "slug": "stone3pl-sendungsverfolgung",
      "title": "Stone3PL Sendungsverfolgung: Nummern, Status & Kontakt",
      "name": "Stone3PL",
      "color": "#003d98"
    },
    {
      "id": "j-and-t-uk",
      "catalog": "j-and-t-uk",
      "slug": "jt-express-uk-sendungsverfolgung",
      "title": "J&T Express UK Sendungsverfolgung: JTUK-Nummern und Status",
      "name": "J&T Express UK",
      "color": "#e60000"
    },
    {
      "id": "m-one",
      "catalog": "m-one",
      "slug": "m-one-sendungsverfolgung",
      "title": "M-ONE Sendungsverfolgung (Milšped ONE): Status & Hilfe",
      "name": "M-ONE",
      "color": "#024da0"
    },
    {
      "id": "early-bird",
      "catalog": "early-bird",
      "slug": "early-bird-sendungsverfolgung",
      "title": "Early Bird Sendungsverfolgung: Status, Nacht & Locker",
      "name": "Early Bird",
      "color": "#dd7f4a"
    },
    {
      "id": "xinghe-freight",
      "catalog": "xinghe-freight",
      "slug": "xinghe-freight-sendungsverfolgung",
      "title": "Xinghe Freight Sendungsverfolgung: GZXH…YQ-Nummer & Status",
      "name": "Xinghe Freight",
      "color": "#0e3a6b"
    },
    {
      "id": "cvc",
      "catalog": "cvc",
      "slug": "cvc-sendungsverfolgung",
      "title": "CVC Sendungsverfolgung (Си Ви Си): Paket in Bulgarien",
      "name": "CVC",
      "color": "#652b9e"
    },
    {
      "id": "transpress",
      "catalog": "transpress",
      "slug": "transpress-sendungsverfolgung",
      "title": "Transpress (Транспрес) Sendungsverfolgung in Bulgarien",
      "name": "Transpress",
      "color": "#1e6ee2"
    },
    {
      "id": "gls-us",
      "catalog": "gls-us",
      "slug": "gls-us-sendungsverfolgung",
      "title": "GLS US Sendungsverfolgung (GSO): Nummern, Status & Kontakt",
      "name": "GLS US",
      "color": "#ffdd00"
    },
    {
      "id": "sxw-express",
      "catalog": "sxw-express",
      "slug": "sxw-express-sendungsverfolgung",
      "title": "SXW Express Sendungsverfolgung: SXW-Nummer und Status",
      "name": "SXW Express",
      "color": "#003876"
    },
    {
      "id": "xuchuan-logistics",
      "catalog": "xuchuan-logistics",
      "slug": "xuchuan-logistics-sendungsverfolgung",
      "title": "Xuchuan Logistics Tracking: XC-Nummern, Lieferung, Hilfe",
      "name": "Xuchuan International Logistics",
      "color": "#0a2463"
    },
    {
      "id": "samus",
      "catalog": "samus",
      "slug": "samus-sendungsverfolgung",
      "title": "SAMUS Sendungsverfolgung: Nummern, Status & Abholung",
      "name": "SAMUS",
      "color": "#e50079"
    },
    {
      "id": "siuntos-autobusais",
      "catalog": "siuntos-autobusais",
      "slug": "siuntos-autobusais-sendungsverfolgung",
      "title": "Siuntos autobusais Sendungsverfolgung: Buspakete in Litauen",
      "name": "Siuntos autobusais",
      "color": "#00843d"
    },
    {
      "id": "zipasgo",
      "catalog": "zipasgo",
      "slug": "zipasgo-sendungsverfolgung",
      "title": "ZipasGo Tracking: Parcel Stars, Partnerdienste und Hilfe",
      "name": "ZipasGo",
      "color": "#ffdb1c"
    },
    {
      "id": "dk-logistics-ee",
      "catalog": "dk-logistics-ee",
      "slug": "dk-logistics-sendungsverfolgung",
      "title": "DK Logistics Sendungsverfolgung: Frachtbrief & Kontakt",
      "name": "DK Logistics",
      "color": "#f58428"
    },
    {
      "id": "jet-express-ee",
      "catalog": "jet-express-ee",
      "slug": "jet-express-sendungsverfolgung",
      "title": "Jet Express Tracking: Auftragsnummer, Lieferung, Kontakt",
      "name": "Jet Express",
      "color": "#d61b22"
    },
    {
      "id": "fan-courier-international",
      "catalog": "fan-courier-international",
      "slug": "fan-courier-international-sendungsverfolgung",
      "title": "FAN Courier International Tracking: AWB und Status",
      "name": "FAN Courier International",
      "color": "#2daae1"
    },
    {
      "id": "j-and-t-br",
      "catalog": "j-and-t-br",
      "slug": "jt-express-brazil-sendungsverfolgung",
      "title": "J&T Express Brazil Sendungsverfolgung: Status & Kontakt",
      "name": "J&T Express Brazil",
      "color": "#e60000"
    },
    {
      "id": "anjun",
      "catalog": "anjun",
      "slug": "anjun-logistics-sendungsverfolgung",
      "title": "Anjun Logistics Sendungsverfolgung: Status & SAC-Kontakt",
      "name": "Anjun Logistics",
      "color": "#009944"
    },
    {
      "id": "xyl-ships",
      "catalog": "xyl-ships",
      "slug": "xyl-ships-sendungsverfolgung",
      "title": "XYL-Ships Sendungsverfolgung: NUDE, XLWL & Status",
      "name": "XYL-Ships",
      "color": "#b13c3c"
    },
    {
      "id": "jadlog",
      "catalog": "jadlog",
      "slug": "jadlog-sendungsverfolgung",
      "title": "Jadlog Sendungsverfolgung: 14 Ziffern, Pickup & Predict",
      "name": "Jadlog",
      "color": "#dc0032"
    },
    {
      "id": "gls-pl",
      "catalog": "gls-pl",
      "slug": "gls-poland-sendungsverfolgung",
      "title": "GLS Poland Sendungsverfolgung: Status, Points & Locker",
      "name": "GLS Poland",
      "color": "#ffdd00"
    },
    {
      "id": "j-and-t-mx",
      "catalog": "j-and-t-mx",
      "slug": "jt-express-mexico-sendungsverfolgung",
      "title": "J&T Express Mexico Sendungsverfolgung: JMX-Codes & Hilfe",
      "name": "J&T Express Mexico",
      "color": "#e60012"
    },
    {
      "id": "bulgarian-posts",
      "catalog": "bulgarian-posts",
      "slug": "bulgarian-posts-sendungsverfolgung",
      "title": "Bulgarian Posts Sendungsverfolgung: PS/BG, Zoll & Hilfe",
      "name": "Bulgarian Posts",
      "color": "#ffd100"
    },
    {
      "id": "haypost",
      "catalog": "haypost",
      "slug": "haypost-sendungsverfolgung",
      "title": "HayPost Sendungsverfolgung: AM-Codes, Status & Kontakt",
      "name": "HayPost",
      "color": "#fa4616"
    },
    {
      "id": "360lion",
      "catalog": "360lion",
      "slug": "360lion-sendungsverfolgung",
      "title": "360Lion Sendungsverfolgung: WSH- & AR-Codes & Kontakt",
      "name": "360Lion",
      "color": "#cf5d15"
    },
    {
      "id": "paquetexpress",
      "catalog": "paquetexpress",
      "slug": "paquetexpress-sendungsverfolgung",
      "title": "Paquetexpress Sendungsverfolgung: Guías, Ocurre & Hilfe",
      "name": "Paquetexpress",
      "color": "#002a52"
    },
    {
      "id": "ydh",
      "catalog": "ydh",
      "slug": "ydh-sendungsverfolgung",
      "title": "YDH Sendungsverfolgung: YDH...YQ & AZ-Codes & Hilfe",
      "name": "YDH",
      "color": "#015db9"
    },
    {
      "id": "a-tu-hora-express",
      "catalog": "a-tu-hora-express",
      "slug": "a-tu-hora-express-sendungsverfolgung",
      "title": "A Tu Hora Express Sendungsverfolgung: Codes, PLZ & Hilfe",
      "name": "A Tu Hora Express",
      "color": "#1d2a84"
    },
    {
      "id": "dtdc",
      "catalog": "dtdc",
      "slug": "dtdc-sendungsverfolgung",
      "title": "DTDC Sendungsverfolgung: AWB-Formate, RTO & Kontakt",
      "name": "DTDC",
      "color": "#0d237d"
    },
    {
      "id": "gofo-es",
      "catalog": "gofo-es",
      "slug": "gofo-spain-sendungsverfolgung",
      "title": "GOFO Spain Sendungsverfolgung: GFES & CIES & Kontakt",
      "name": "GOFO Spain",
      "color": "#fc4c02"
    },
    {
      "id": "korea-post",
      "catalog": "korea-post",
      "slug": "korea-post-sendungsverfolgung",
      "title": "Korea Post Sendungsverfolgung: EMS-KR-Codes, Status & Hilfe",
      "name": "Korea Post",
      "color": "#da291c"
    },
    {
      "id": "nova-global",
      "catalog": "nova-global",
      "slug": "nova-global-sendungsverfolgung",
      "title": "Nova Global Sendungsverfolgung: NPG- & NGUS-Codes",
      "name": "Nova Global",
      "color": "#da291c"
    },
    {
      "id": "xlcourier",
      "catalog": "xlcourier",
      "slug": "xlcourier-sendungsverfolgung",
      "title": "XLCourier Sendungsverfolgung: XLC-Codes & UK-Sperrgut",
      "name": "XLCourier",
      "color": "#044ad6"
    },
    {
      "id": "estafeta",
      "catalog": "estafeta",
      "slug": "estafeta-sendungsverfolgung",
      "title": "Estafeta Sendungsverfolgung: 10 & 22 Zeichen & Hilfe",
      "name": "Estafeta",
      "color": "#e30613"
    },
    {
      "id": "gls-hu",
      "catalog": "gls-hu",
      "slug": "gls-hungary-sendungsverfolgung",
      "title": "GLS Hungary Sendungsverfolgung: Nummern, Locker & Hilfe",
      "name": "GLS Hungary",
      "color": "#ffdd00"
    },
    {
      "id": "dhl-freight",
      "catalog": "dhl-freight",
      "slug": "dhl-freight-sendungsverfolgung",
      "title": "DHL Freight Sendungsverfolgung: ActiveTracing & Hilfe",
      "name": "DHL Freight",
      "color": "#ffcc00"
    },
    {
      "id": "anserx",
      "catalog": "anserx",
      "slug": "anserx-sendungsverfolgung",
      "title": "AnserX Sendungsverfolgung: BTYCN...YQ-Codes & Kontakt",
      "name": "AnserX",
      "color": "#2478be"
    },
    {
      "id": "bjs-home-delivery",
      "catalog": "bjs-home-delivery",
      "slug": "bjs-home-delivery-sendungsverfolgung",
      "title": "BJS Home Delivery Sendungsverfolgung: B-Codes & Hilfe",
      "name": "BJS Home Delivery",
      "color": "#feba30"
    },
    {
      "id": "chukou1",
      "catalog": "chukou1",
      "slug": "chukou1-sendungsverfolgung",
      "title": "Chukou1 Sendungsverfolgung: EC-FirstClass-Codes & Hilfe",
      "name": "Chukou1",
      "color": "#0b4da2"
    },
    {
      "id": "cj-packet",
      "catalog": "cj-packet",
      "slug": "cj-packet-sendungsverfolgung",
      "title": "CJ Packet Sendungsverfolgung: CJP...YQ-Codes & Kontakt",
      "name": "CJ Packet",
      "color": "#ff7700"
    },
    {
      "id": "cmt-express",
      "catalog": "cmt-express",
      "slug": "cmt-express-sendungsverfolgung",
      "title": "CMT Express Sendungsverfolgung: CMT-Codes & Kontakte",
      "name": "CMT Express",
      "color": "#ed010b"
    },
    {
      "id": "correos-de-mexico",
      "catalog": "correos-de-mexico",
      "slug": "correos-de-mexico-sendungsverfolgung",
      "title": "Correos de México Sendungsverfolgung: S10-MX-Codes & Hilfe",
      "name": "Correos de México",
      "color": "#ed007d"
    },
    {
      "id": "dobropost",
      "catalog": "dobropost",
      "slug": "dobropost-sendungsverfolgung",
      "title": "DobroPost Sendungsverfolgung: DBRPST-Codes & China-Hubs",
      "name": "DobroPost",
      "color": "#ffbe2e"
    },
    {
      "id": "ems",
      "catalog": "ems",
      "slug": "ems-sendungsverfolgung",
      "title": "EMS Sendungsverfolgung: S10-Nummern, Status & Hilfe",
      "name": "EMS",
      "color": "#003b7a"
    },
    {
      "id": "jiayang",
      "catalog": "jiayang",
      "slug": "jiayang-sendungsverfolgung",
      "title": "Jiayang International Sendungsverfolgung: JYGJ & Hilfe",
      "name": "Jiayang International",
      "color": "#105bcc"
    },
    {
      "id": "jumppoint-international",
      "catalog": "jumppoint-international",
      "slug": "jumppoint-international-sendungsverfolgung",
      "title": "Jumppoint International Sendungsverfolgung: JP & Hilfe",
      "name": "Jumppoint International Express",
      "color": "#0f4ae1"
    },
    {
      "id": "kuayuan-express",
      "catalog": "kuayuan-express",
      "slug": "kuayuan-express-sendungsverfolgung",
      "title": "Kuayuan Express Sendungsverfolgung: Codes, Übergabe & Hilfe",
      "name": "Kuayuan Express",
      "color": "#084b86"
    },
    {
      "id": "liccardi",
      "catalog": "liccardi",
      "slug": "liccardi-sendungsverfolgung",
      "title": "Liccardi Express Courier Sendungsverfolgung: LDV & Hilfe",
      "name": "Liccardi Express Courier",
      "color": "#014895"
    },
    {
      "id": "maersk-ecommerce",
      "catalog": "maersk-ecommerce",
      "slug": "maersk-ecommerce-sendungsverfolgung",
      "title": "Maersk eCommerce Sendungsverfolgung: 1M-Codes & Status",
      "name": "Maersk eCommerce",
      "color": "#42b0d5"
    },
    {
      "id": "pdu",
      "catalog": "pdu",
      "slug": "pdu-sendungsverfolgung",
      "title": "PDU Sendungsverfolgung: Parcel Delivery Union & Hilfe",
      "name": "PDU",
      "color": "#005cfe"
    },
    {
      "id": "post-haste",
      "catalog": "post-haste",
      "slug": "post-haste-sendungsverfolgung",
      "title": "Post Haste Sendungsverfolgung: NZ-Tickets, Status & Hilfe",
      "name": "Post Haste",
      "color": "#24b2d8"
    },
    {
      "id": "redur",
      "catalog": "redur",
      "slug": "redur-sendungsverfolgung",
      "title": "Redur Sendungsverfolgung: 14-stellige Codes & Hilfe",
      "name": "Redur",
      "color": "#0c4c49"
    },
    {
      "id": "sengi-express",
      "catalog": "sengi-express",
      "slug": "sengi-express-sendungsverfolgung",
      "title": "Sengi Express Sendungsverfolgung: China–Italien & Hilfe",
      "name": "Sengi Express",
      "color": "#1a44a4"
    },
    {
      "id": "serpost",
      "catalog": "serpost",
      "slug": "serpost-sendungsverfolgung",
      "title": "Serpost Sendungsverfolgung: Peru S10-Codes, Zoll & Hilfe",
      "name": "Serpost",
      "color": "#00308a"
    },
    {
      "id": "skypostal",
      "catalog": "skypostal",
      "slug": "skypostal-sendungsverfolgung",
      "title": "SkyPostal Sendungsverfolgung: SKAMZUS, SP-Codes & Hilfe",
      "name": "SkyPostal",
      "color": "#088aea"
    },
    {
      "id": "thailand-post",
      "catalog": "thailand-post",
      "slug": "thailand-post-sendungsverfolgung",
      "title": "Thailand Post Sendungsverfolgung: TH-Codes, EMS & 1545",
      "name": "Thailand Post",
      "color": "#ef172f"
    },
    {
      "id": "yunda",
      "catalog": "yunda",
      "slug": "yunda-sendungsverfolgung",
      "title": "Yunda Express Sendungsverfolgung: Nummern, Status & 95546",
      "name": "Yunda Express",
      "color": "#ffce00"
    },
    {
      "id": "onlog-express",
      "catalog": "onlog-express",
      "slug": "onlog-express-sendungsverfolgung",
      "title": "Onlog Express Sendungsverfolgung: AA-Codes & Hilfe",
      "name": "Onlog Express",
      "color": "#1a345d"
    },
    {
      "id": "a-duie-pyle",
      "catalog": "a-duie-pyle",
      "slug": "a-duie-pyle-sendungsverfolgung",
      "title": "A. Duie Pyle Sendungsverfolgung: PRO-Nummern & LTL-Hilfe",
      "name": "A. Duie Pyle",
      "color": "#f27629"
    },
    {
      "id": "buffalo-ex",
      "catalog": "buffalo-ex",
      "slug": "buffalo-ex-sendungsverfolgung",
      "title": "BUFFALO Sendungsverfolgung: BUFZA-Codes, Zoll & Hilfe",
      "name": "BUFFALO",
      "color": "#bc8c4d"
    },
    {
      "id": "chit-chats",
      "catalog": "chit-chats",
      "slug": "chit-chats-sendungsverfolgung",
      "title": "Chit Chats Sendungsverfolgung: 10-stellige IDs & Übergabe",
      "name": "Chit Chats",
      "color": "#db2b35"
    },
    {
      "id": "dpd-hu",
      "catalog": "dpd-hu",
      "slug": "dpd-hu-sendungsverfolgung",
      "title": "DPD Hungary Sendungsverfolgung: 14-stellige Codes & Hilfe",
      "name": "DPD Hungary",
      "color": "#dc0032"
    },
    {
      "id": "e24metro-ca",
      "catalog": "e24metro-ca",
      "slug": "e24metro-ca-sendungsverfolgung",
      "title": "E24 (Metro Supply Chain) Sendungsverfolgung & Kontakt",
      "name": "E24",
      "color": "#545859"
    },
    {
      "id": "go-express",
      "catalog": "go-express",
      "slug": "go-express-sendungsverfolgung",
      "title": "GO! Express Sendungsverfolgung: Frachtbrief, Status & Hilfe",
      "name": "GO! Express",
      "color": "#b9242b"
    },
    {
      "id": "kuehne-nagel",
      "catalog": "kuehne-nagel",
      "slug": "kuehne-nagel-sendungsverfolgung",
      "title": "Kühne+Nagel Sendungsverfolgung — Nummern und Status",
      "name": "Kuehne+Nagel",
      "color": "#003369"
    },
    {
      "id": "memphis-courier",
      "catalog": "memphis-courier",
      "slug": "memphis-courier-sendungsverfolgung",
      "title": "Memphis Courier Sendungsverfolgung: M-MIA, Zoll & Hilfe",
      "name": "Memphis Courier",
      "color": "#1f3c5b"
    },
    {
      "id": "oca",
      "catalog": "oca",
      "slug": "oca-sendungsverfolgung",
      "title": "OCA Sendungsverfolgung: Nummern, Status, Filialen & Hilfe",
      "name": "OCA",
      "color": "#592c82"
    },
    {
      "id": "pigeonship",
      "catalog": "pigeonship",
      "slug": "pigeonship-sendungsverfolgung",
      "title": "PigeonShip Sendungsverfolgung: Nummern, Status & Hilfe",
      "name": "PigeonShip",
      "color": "#f2b02c"
    },
    {
      "id": "seko",
      "catalog": "seko",
      "slug": "seko-logistics-sendungsverfolgung",
      "title": "SEKO Logistics Sendungsverfolgung: Nummern & OmniParcel",
      "name": "SEKO Logistics",
      "color": "#ef3e42"
    },
    {
      "id": "bfe-express",
      "catalog": "bfe-express",
      "slug": "bfe-express-sendungsverfolgung",
      "title": "BFE Express Sendungsverfolgung: Nummern, Status & Hilfe",
      "name": "BFE Express",
      "color": "#122a88"
    },
    {
      "id": "pasarex",
      "catalog": "pasarex",
      "slug": "pasarex-sendungsverfolgung",
      "title": "PasarEX Sendungsverfolgung: Amazon-AMZPSR, Zoll & Hilfe",
      "name": "PasarEX",
      "color": "#124381"
    },
    {
      "id": "spee-dee",
      "catalog": "spee-dee",
      "slug": "spee-dee-sendungsverfolgung",
      "title": "Spee-Dee Delivery Sendungsverfolgung: SP-Codes & Hilfe",
      "name": "Spee-Dee",
      "color": "#ff4734"
    },
    {
      "id": "tnt-au",
      "catalog": "tnt-au",
      "slug": "tnt-australia-sendungsverfolgung",
      "title": "TNT Australia Sendungsverfolgung: Nummern, Status & Hilfe",
      "name": "TNT Australia",
      "color": "#ff6600"
    },
    {
      "id": "wedo",
      "catalog": "wedo",
      "slug": "one-by-allegro-cz-sendungsverfolgung",
      "title": "One by Allegro CZ (WE|DO) Sendungsverfolgung & Abholung",
      "name": "One by Allegro CZ",
      "color": "#006d57"
    },
    {
      "id": "5post",
      "catalog": "5post",
      "slug": "5post-sendungsverfolgung",
      "title": "5Post Sendungsverfolgung: Nummern, Abholung & Status",
      "name": "5Post",
      "color": "#64b441"
    },
    {
      "id": "shipter",
      "catalog": "shipter",
      "slug": "shipter-sendungsverfolgung",
      "title": "Shipter Sendungsverfolgung: H3-Nummern aus Südkorea",
      "name": "Shipter",
      "color": "#005eff"
    }
  ],
  "fr": [
    {
      "id": "quickpac",
      "catalog": "quickpac",
      "slug": "quickpac-tracking",
      "title": "Quickpac tracking\u00a0: suivi du colis, statuts et contact",
      "name": "Quickpac",
      "color": "#ed1c24"
    },
    {
      "id": "planzer",
      "catalog": "planzer",
      "slug": "planzer-suivi-colis",
      "title": "Planzer suivi colis\u00a0: statuts, livraison et contact",
      "name": "Planzer",
      "color": "#e30613"
    },
    {
      "id": "colis-prive",
      "catalog": "colis-prive",
      "slug": "suivi-colis-prive",
      "title": "Colis Privé suivi\u00a0: statuts, relais et service client",
      "name": "Colis Privé",
      "color": "#aa78ff"
    },
    {
      "id": "brt",
      "catalog": "brt",
      "slug": "brt-suivi",
      "title": "Suivi colis BRT\u00a0: numéro, statuts et livraison d’Italie",
      "name": "BRT",
      "color": "#dc0032"
    },
    {
      "id": "correos-express",
      "catalog": "correos-express",
      "slug": "correos-express-suivi",
      "title": "Suivi Correos Express\u00a0: numéro, statuts et contact",
      "name": "Correos Express",
      "color": "#ffcd00"
    },
    {
      "id": "nacex",
      "catalog": "nacex",
      "slug": "nacex-suivi",
      "title": "NACEX suivi\u00a0: numéro, statuts espagnols et contact",
      "name": "NACEX",
      "color": "#fe5000"
    },
    {
      "id": "yunexpress",
      "catalog": "yunexpress",
      "slug": "yunexpress-suivi",
      "title": "YunExpress suivi\u00a0: numéro YT, statuts et qui livre",
      "name": "YunExpress",
      "color": "#008284"
    },
    {
      "id": "cainiao",
      "catalog": "aliexpress",
      "slug": "cainiao-suivi",
      "title": "Cainiao suivi\u00a0: numéros, statuts et qui contacter",
      "name": "Cainiao",
      "color": "#e62e04"
    },
    {
      "id": "mondial-relay",
      "catalog": "mondial-relay",
      "slug": "mondial-relay-suivi",
      "title": "Mondial Relay suivi\u00a0: statuts, Point Relais et contact",
      "name": "Mondial Relay",
      "color": "#ef3026"
    },
    {
      "id": "gls-de",
      "catalog": "gls-de",
      "slug": "gls-allemagne-suivi-colis",
      "title": "GLS Allemagne suivi colis\u00a0: statuts, relais et contact",
      "name": "GLS Germany",
      "color": "#ffdd00"
    },
    {
      "id": "dpd-de",
      "catalog": "dpd-de",
      "slug": "suivi-dpd-allemagne",
      "title": "Suivi DPD Allemagne (DPD Germany)\u00a0: numéro, statuts et aide",
      "name": "DPD Germany",
      "color": "#dc0032"
    },
    {
      "id": "hermes-de",
      "catalog": "hermes-de",
      "slug": "suivi-hermes",
      "title": "Suivi Hermes Allemagne\u00a0: statuts expliqués, relais et suivi",
      "name": "Hermes",
      "color": "#0091cd"
    },
    {
      "id": "gls-ch",
      "catalog": "gls-ch",
      "slug": "gls-suisse-suivi-colis",
      "title": "GLS Suisse suivi colis\u00a0: statuts, La Poste et contact",
      "name": "GLS Switzerland",
      "color": "#ffdd00"
    },
    {
      "id": "dpd-ch",
      "catalog": "dpd",
      "slug": "suivi-dpd-suisse",
      "title": "DPD Suisse\u00a0: suivre un colis, points relais et contact",
      "name": "DPD Switzerland",
      "color": "#dc0032"
    },
    {
      "id": "inpost",
      "catalog": "inpost",
      "slug": "inpost-suivi",
      "title": "Suivi InPost\u00a0: numéros, statuts, Lockers et Mondial Relay",
      "name": "InPost",
      "color": "#ffcc05"
    },
    {
      "id": "paack",
      "catalog": "paack",
      "slug": "paack-suivi",
      "title": "Paack suivi colis\u00a0: numéro, code postal et statuts",
      "name": "Paack",
      "color": "#5b2dee"
    },
    {
      "id": "evri",
      "catalog": "evri-uk",
      "slug": "evri-suivi",
      "title": "Suivi colis Evri\u00a0: numéro, statuts, douane et livraison",
      "name": "Evri",
      "color": "#00014d"
    },
    {
      "id": "chronopost",
      "catalog": "chronopost",
      "slug": "chronopost-suivi",
      "title": "Suivi colis Chronopost\u00a0: numéro, statuts, délais et relais",
      "name": "Chronopost",
      "color": "#0056a7"
    },
    {
      "id": "relais-colis",
      "catalog": "relais-colis",
      "slug": "relais-colis-suivi",
      "title": "Relais Colis suivi\u00a0: statuts, retrait en relais et contact",
      "name": "Relais Colis",
      "color": "#7bb928"
    },
    {
      "id": "poste-italiane",
      "catalog": "poste-italiane",
      "slug": "suivi-poste-italiane",
      "title": "Suivi Poste Italiane\u00a0: codes 1UW et 3UW, statuts et délais",
      "name": "Poste Italiane",
      "color": "#eedc00"
    },
    {
      "id": "seur",
      "catalog": "seur",
      "slug": "suivi-seur",
      "title": "Suivi SEUR\u00a0: numéro, statuts espagnols et points Pickup",
      "name": "SEUR",
      "color": "#0063b3"
    },
    {
      "id": "mrw",
      "catalog": "mrw",
      "slug": "mrw-suivi",
      "title": "Suivi MRW\u00a0: numéro à 12 chiffres, statuts et contacts",
      "name": "MRW",
      "color": "#19133b"
    },
    {
      "id": "ctt",
      "catalog": "ctt",
      "slug": "suivi-ctt-portugal",
      "title": "Suivi CTT Portugal\u00a0: numéros, statuts, relais et douane",
      "name": "CTT",
      "color": "#df0024"
    },
    {
      "id": "packeta",
      "catalog": "packeta",
      "slug": "packeta-suivi-colis",
      "title": "Suivi Packeta\u00a0: numéro Z, statuts, relais et contact",
      "name": "Packeta",
      "color": "#ba1b02"
    },
    {
      "id": "dpd-pl",
      "catalog": "dpd-pl",
      "slug": "dpd-poland-tracking",
      "title": "Suivi DPD Pologne (DPD Polska)\u00a0: statuts et points Pickup",
      "name": "DPD Poland",
      "color": "#dc0032"
    },
    {
      "id": "4px",
      "catalog": "four-px",
      "slug": "4px-suivi",
      "title": "4PX suivi\u00a0: numéro, statuts et qui contacter",
      "name": "4PX",
      "color": "#005add"
    },
    {
      "id": "swiss-post",
      "catalog": "swiss-post",
      "slug": "swiss-post-suivi-colis",
      "title": "Swiss Post\u00a0: suivi colis La Poste suisse et statuts",
      "name": "Swiss Post",
      "color": "#ffcc00"
    },
    {
      "id": "ctt-express",
      "catalog": "ctt-express",
      "slug": "suivi-ctt-express",
      "title": "Suivi CTT Express\u00a0: statuts en français, relais et contact",
      "name": "CTT Express",
      "color": "#df0024"
    },
    {
      "id": "dpd-fr",
      "catalog": "dpd-fr",
      "slug": "dpd-france-suivi-colis",
      "title": "DPD France suivi colis\u00a0: numéro, statuts et relais Pickup",
      "name": "DPD France",
      "color": "#dc0032"
    },
    {
      "id": "gls-fr",
      "catalog": "gls-fr",
      "slug": "gls-france-suivi-colis",
      "title": "GLS France suivi colis\u00a0: statuts, relais et contact",
      "name": "GLS France",
      "color": "#ffdd00"
    },
    {
      "id": "gls-it",
      "catalog": "gls-it",
      "slug": "suivi-gls-italy",
      "title": "Suivi GLS Italy\u00a0: numéros, statuts et livraisons manquées",
      "name": "GLS Italy",
      "color": "#ffdd00"
    },
    {
      "id": "poczta-polska",
      "catalog": "poczta-polska",
      "slug": "suivi-poczta-polska",
      "title": "Suivi Poczta Polska\u00a0: numéros, statuts et points relais",
      "name": "Poczta Polska",
      "color": "#c43c2b"
    },
    {
      "id": "orlen-paczka",
      "catalog": "orlen-paczka",
      "slug": "suivi-orlen-paczka",
      "title": "Suivi ORLEN Paczka\u00a0: numéros, statuts et retraits",
      "name": "ORLEN Paczka",
      "color": "#db3830"
    },
    {
      "id": "austrian-post",
      "catalog": "austrian-post",
      "slug": "suivi-austrian-post",
      "title": "Suivi Austrian Post\u00a0: numéros, statuts et retrait",
      "name": "Austrian Post",
      "color": "#ffdc00"
    },
    {
      "id": "tipsa",
      "catalog": "tipsa",
      "slug": "suivi-tipsa",
      "title": "Suivi TIPSA\u00a0: numéro à 22 chiffres, statuts et livraisons",
      "name": "TIPSA",
      "color": "#001e62"
    },
    {
      "id": "yanwen",
      "catalog": "yanwen",
      "slug": "yanwen-suivi",
      "title": "Yanwen suivi\u00a0: numéros, statuts et qui contacter",
      "name": "Yanwen",
      "color": "#8cbe1e"
    },
    {
      "id": "an-post",
      "catalog": "an-post",
      "slug": "suivi-an-post",
      "title": "Suivi An Post\u00a0: statuts, douane et contact en Irlande",
      "name": "An Post",
      "color": "#00a76a"
    },
    {
      "id": "vinted-go",
      "catalog": "vinted-go",
      "slug": "vinted-go-suivi",
      "title": "Vinted Go suivi\u00a0: statuts, consigne, point relais et contact",
      "name": "Vinted Go",
      "color": "#004654"
    },
    {
      "id": "amazon-logistics",
      "catalog": "amazon-logistics",
      "slug": "amazon-logistics-suivi",
      "title": "Amazon Logistics suivi\u00a0: où suivre ton colis et que faire",
      "name": "Amazon Logistics",
      "color": "#ff9900"
    },
    {
      "id": "dpd-uk",
      "catalog": "dpd-uk",
      "slug": "suivi-dpd-uk",
      "title": "Suivi DPD UK\u00a0: numéros à 14 chiffres, statuts et contact",
      "name": "DPD UK",
      "color": "#dc0032"
    },
    {
      "id": "parcelforce",
      "catalog": "parcelforce",
      "slug": "suivi-parcelforce-worldwide",
      "title": "Suivi Parcelforce Worldwide\u00a0: statuts, douane et contact",
      "name": "Parcelforce Worldwide",
      "color": "#9d0f11"
    },
    {
      "id": "bpost",
      "catalog": "bpost",
      "slug": "bpost-suivi",
      "title": "bpost suivi\u00a0: code-barres, statuts et point relais",
      "name": "bpost",
      "color": "#ef2637"
    },
    {
      "id": "dpd-ie",
      "catalog": "dpd-ie",
      "slug": "suivi-dpd-ireland",
      "title": "Suivi DPD Ireland\u00a0: statuts, points Pickup et contact",
      "name": "DPD Ireland",
      "color": "#dc0032"
    },
    {
      "id": "gls-es",
      "catalog": "gls-es",
      "slug": "suivi-gls-espagne",
      "title": "Suivi GLS Espagne\u00a0: statuts, Parcel Shops et contact",
      "name": "GLS Spain",
      "color": "#ffdd00"
    },
    {
      "id": "dhl-ecommerce-es",
      "catalog": "dhl-ecommerce-es",
      "slug": "suivi-dhl-ecommerce-iberia",
      "title": "Suivi DHL eCommerce Iberia\u00a0: statuts et ServicePoints",
      "name": "DHL eCommerce Iberia",
      "color": "#ffcc00"
    },
    {
      "id": "dhl-ecommerce-pl",
      "catalog": "dhl-ecommerce-pl",
      "slug": "suivi-dhl-ecommerce-pologne",
      "title": "Suivi DHL eCommerce Pologne\u00a0: statuts, retrait et aide",
      "name": "DHL eCommerce Poland",
      "color": "#ffcc00"
    },
    {
      "id": "dhl-ecommerce-uk",
      "catalog": "dhl-ecommerce-uk",
      "slug": "suivi-dhl-ecommerce-uk",
      "title": "Suivi DHL eCommerce UK\u00a0: statuts, ServicePoints, contact",
      "name": "DHL eCommerce UK",
      "color": "#ffcc00"
    },
    {
      "id": "gofo-fr",
      "catalog": "gofo-fr",
      "slug": "gofo-france-suivi",
      "title": "GOFO France suivi colis\u00a0: numéros GFFR, statuts et aide",
      "name": "GOFO France",
      "color": "#fc4c02"
    },
    {
      "id": "gofo-it",
      "catalog": "gofo-it",
      "slug": "suivi-gofo-italy",
      "title": "Suivi GOFO Italy\u00a0: numéros GFIT, statuts et réclamations",
      "name": "GOFO Italy",
      "color": "#fc4c02"
    },
    {
      "id": "speedpak",
      "catalog": "speedpak",
      "slug": "speedpak-suivi",
      "title": "SpeedPAK suivi\u00a0: colis eBay, statuts et qui contacter",
      "name": "SpeedPAK",
      "color": "#ee7532"
    },
    {
      "id": "asendia",
      "catalog": "asendia",
      "slug": "asendia-suivi",
      "title": "Asendia suivi\u00a0: colis, statuts et qui contacter",
      "name": "Asendia",
      "color": "#005d69"
    },
    {
      "id": "colisweb",
      "catalog": "colisweb",
      "slug": "suivi-colisweb",
      "title": "Suivi Colisweb\u00a0: messages, créneau de livraison et contact",
      "name": "Colisweb",
      "color": "#15cbe3"
    },
    {
      "id": "ciblex",
      "catalog": "ciblex",
      "slug": "suivi-ciblex",
      "title": "Suivi Ciblex\u00a0: numéro, statuts du colis et contact",
      "name": "Ciblex",
      "color": "#0160ab"
    },
    {
      "id": "hermes-einrichtungs-service",
      "catalog": "hermes",
      "slug": "suivi-hermes-einrichtungs-service",
      "title": "Suivi Hermes Einrichtungs-Service\u00a0: meubles et RDV",
      "name": "Hermes Einrichtungs-Service",
      "color": "#0091cd"
    },
    {
      "id": "landmark-global",
      "catalog": "landmark-global",
      "slug": "landmark-global-suivi",
      "title": "Landmark Global suivi\u00a0: statuts, livreur et contact",
      "name": "Landmark Global",
      "color": "#e62337"
    },
    {
      "id": "dhl-ecommerce",
      "catalog": "dhl-ecommerce",
      "slug": "suivi-dhl-ecommerce",
      "title": "Suivi DHL eCommerce\u00a0: numéros GM, statuts et contacts",
      "name": "DHL eCommerce",
      "color": "#ffcc00"
    },
    {
      "id": "allegro-one",
      "catalog": "allegro-one",
      "slug": "suivi-allegro-one",
      "title": "Suivi Allegro One\u00a0: One Box, One Punkt et One Kurier",
      "name": "Allegro One",
      "color": "#006d57"
    },
    {
      "id": "cacesa",
      "catalog": "cacesa",
      "slug": "suivi-cacesa",
      "title": "Suivi CACESA\u00a0: douane, statuts et transporteur final",
      "name": "CACESA",
      "color": "#C9032D"
    },
    {
      "id": "sunyou",
      "catalog": "sunyou",
      "slug": "sunyou-suivi",
      "title": "SunYou tracking\u00a0: suivi du colis, statuts et qui livre",
      "name": "SunYou",
      "color": "#02a232"
    },
    {
      "id": "amazon-shipping",
      "catalog": "amazon-shipping",
      "slug": "amazon-shipping-suivi",
      "title": "Amazon Shipping suivi\u00a0: numéro de suivi et colis non reçu",
      "name": "Amazon Shipping",
      "color": "#ff9900"
    },
    {
      "id": "postnord",
      "catalog": "postnord",
      "slug": "suivi-postnord",
      "title": "Suivi PostNord\u00a0: numéros, statuts et retrait",
      "name": "PostNord",
      "color": "#00a0d6"
    },
    {
      "id": "bring",
      "catalog": "bring-posten",
      "slug": "suivi-bring",
      "title": "Suivi Bring (Posten)\u00a0: numéros, statuts et délais de retrait",
      "name": "Bring",
      "color": "#56b529"
    },
    {
      "id": "posti",
      "catalog": "posti",
      "slug": "suivi-posti",
      "title": "Suivi Posti\u00a0: code JJFI, statuts, consignes et contact",
      "name": "Posti",
      "color": "#ff8000"
    },
    {
      "id": "omniva",
      "catalog": "omniva",
      "slug": "suivi-omniva",
      "title": "Suivi Omniva\u00a0: codes, statuts et consignes baltes",
      "name": "Omniva",
      "color": "#f86300"
    },
    {
      "id": "ppl",
      "catalog": "ppl",
      "slug": "suivi-ppl",
      "title": "Suivi PPL\u00a0: numéro à 11 chiffres, statuts et Parcelbox",
      "name": "PPL",
      "color": "#004b93"
    },
    {
      "id": "box-now",
      "catalog": "box-now",
      "slug": "suivi-box-now",
      "title": "Suivi BOX NOW\u00a0: casiers, codes PIN et statuts",
      "name": "BOX NOW",
      "color": "#44d62c"
    },
    {
      "id": "dhl-ecommerce-nl",
      "catalog": "dhl-ecommerce-nl",
      "slug": "dhl-ecommerce-netherlands-suivi-colis",
      "title": "DHL eCommerce Netherlands suivi colis\u00a0: statuts et contact",
      "name": "DHL eCommerce Netherlands",
      "color": "#ffcc00"
    },
    {
      "id": "heppner",
      "catalog": "heppner",
      "slug": "heppner-suivi",
      "title": "Heppner suivi\u00a0: numéro, code postal et rendez-vous",
      "name": "Heppner",
      "color": "#005baa"
    },
    {
      "id": "c-chez-vous",
      "catalog": "c-chez-vous",
      "slug": "c-chez-vous-suivi",
      "title": "C Chez Vous suivi\u00a0: numéro de commande, étapes et contact",
      "name": "C chez vous",
      "color": "#e50046"
    },
    {
      "id": "geodis",
      "catalog": "geodis",
      "slug": "geodis-suivi",
      "title": "GEODIS suivi\u00a0: numéro 1G, étapes et livraison",
      "name": "GEODIS",
      "color": "#3100e6"
    },
    {
      "id": "delivengo",
      "catalog": "delivengo",
      "slug": "delivengo-suivi",
      "title": "Delivengo suivi\u00a0: ton colis La Poste envoyé de France",
      "name": "Delivengo",
      "color": "#ffcf00"
    },
    {
      "id": "ecoscooting",
      "catalog": "ecoscooting",
      "slug": "ecoscooting-suivi",
      "title": "Ecoscooting suivi colis\u00a0: statuts, point relais et contact",
      "name": "Ecoscooting",
      "color": "#3fa7ff"
    },
    {
      "id": "swiss-post-cargo",
      "catalog": "swiss-post-cargo",
      "slug": "swiss-post-cargo-suivi",
      "title": "Swiss Post Cargo suivi\u00a0: statuts, livraison et contact",
      "name": "Swiss Post Cargo",
      "color": "#ffcc00"
    },
    {
      "id": "dachser",
      "catalog": "dachser",
      "slug": "suivi-dachser",
      "title": "Suivi Dachser en Espagne et au Portugal\u00a0: lien et statuts",
      "name": "Dachser",
      "color": "#005ca9"
    },
    {
      "id": "meest",
      "catalog": "meest",
      "slug": "suivi-meest",
      "title": "Suivi Meest\u00a0: numéros, statuts et contacts",
      "name": "Meest",
      "color": "#0061af"
    },
    {
      "id": "nova-poshta",
      "catalog": "nova-poshta",
      "slug": "suivi-nova-poshta",
      "title": "Suivi Nova Poshta\u00a0: numéros, statuts et agences",
      "name": "Nova Poshta",
      "color": "#e30613"
    },
    {
      "id": "sameday",
      "catalog": "sameday",
      "slug": "suivi-sameday",
      "title": "Suivi Sameday\u00a0: AWB, easybox et statuts expliqués",
      "name": "Sameday",
      "color": "#e92427"
    },
    {
      "id": "speedy",
      "catalog": "speedy",
      "slug": "suivi-speedy",
      "title": "Suivi Speedy\u00a0: numéro de bordereau, statuts et consignes",
      "name": "Speedy",
      "color": "#dc0032"
    },
    {
      "id": "cargus",
      "catalog": "cargus",
      "slug": "suivi-cargus",
      "title": "Suivi Cargus\u00a0: numéro AWB, statuts et SHIP & GO",
      "name": "Cargus",
      "color": "#f68121"
    },
    {
      "id": "fan-courier",
      "catalog": "fan-courier",
      "slug": "suivi-fan-courier",
      "title": "Suivi FAN Courier\u00a0: numéros AWB, statuts et FANbox",
      "name": "FAN Courier",
      "color": "#0f2345"
    },
    {
      "id": "econt",
      "catalog": "econt",
      "slug": "suivi-econt",
      "title": "Suivi Econt\u00a0: numéro d'envoi, statuts et agences",
      "name": "Econt",
      "color": "#234182"
    },
    {
      "id": "ceska-posta",
      "catalog": "ceska-posta",
      "slug": "suivi-ceska-posta",
      "title": "Suivi Česká pošta (Poste tchèque)\u00a0: numéros et statuts",
      "name": "Česká pošta",
      "color": "#002776"
    },
    {
      "id": "express-one",
      "catalog": "express-one",
      "slug": "suivi-express-one",
      "title": "Suivi Express One\u00a0: statuts, points relais et contacts",
      "name": "Express One",
      "color": "#ec6500"
    },
    {
      "id": "foxpost",
      "catalog": "foxpost",
      "slug": "suivi-foxpost",
      "title": "Suivi Foxpost\u00a0: statuts, casiers et contacts",
      "name": "Foxpost",
      "color": "#b70100"
    },
    {
      "id": "slovak-parcel-service",
      "catalog": "slovak-parcel-service",
      "slug": "suivi-slovak-parcel-service",
      "title": "Suivi Slovak Parcel Service (SPS)\u00a0: statuts et retrait",
      "name": "Slovak Parcel Service",
      "color": "#f58220"
    },
    {
      "id": "slovenska-posta",
      "catalog": "slovenska-posta",
      "slug": "suivi-slovenska-posta",
      "title": "Suivi Slovenská pošta\u00a0: numéros, statuts et BalíkoBOX",
      "name": "Slovenská pošta",
      "color": "#f9bb00"
    },
    {
      "id": "posta-slovenije",
      "catalog": "posta-slovenije",
      "slug": "suivi-posta-slovenije",
      "title": "Suivi Pošta Slovenije\u00a0: statuts, retrait et contacts",
      "name": "Pošta Slovenije",
      "color": "#ffc600"
    },
    {
      "id": "hrvatska-posta",
      "catalog": "hrvatska-posta",
      "slug": "suivi-hrvatska-posta",
      "title": "Suivi Hrvatska pošta\u00a0: statuts, paketomat et contacts",
      "name": "Hrvatska pošta",
      "color": "#ffd100"
    },
    {
      "id": "posta-srbije",
      "catalog": "posta-srbije",
      "slug": "suivi-posta-srbije",
      "title": "Suivi Pošta Srbije\u00a0: poste serbe, statuts et contacts",
      "name": "Pošta Srbije",
      "color": "#fbba00"
    },
    {
      "id": "venipak",
      "catalog": "venipak",
      "slug": "suivi-venipak",
      "title": "Suivi Venipak\u00a0: statuts, consignes et livraisons manquées",
      "name": "Venipak",
      "color": "#9339f2"
    },
    {
      "id": "lietuvos-pastas",
      "catalog": "lietuvos-pastas",
      "slug": "lietuvos-pastas-suivi",
      "title": "Suivi colis Lietuvos paštas\u00a0: poste lituanienne, LP EXPRESS",
      "name": "Lietuvos paštas",
      "color": "#fedd00"
    },
    {
      "id": "latvijas-pasts",
      "catalog": "latvijas-pasts",
      "slug": "suivi-latvijas-pasts",
      "title": "Suivi Latvijas Pasts\u00a0: numéros, statuts et consignes",
      "name": "Latvijas Pasts",
      "color": "#0c72ba"
    },
    {
      "id": "dao",
      "catalog": "dao",
      "slug": "suivi-dao",
      "title": "Suivi DAO\u00a0: statuts, daoSHOP et livraisons manquées",
      "name": "DAO",
      "color": "#e30613"
    },
    {
      "id": "helthjem",
      "catalog": "helthjem",
      "slug": "suivi-helthjem",
      "title": "Suivi Helthjem\u00a0: statuts, points de retrait et contact",
      "name": "Helthjem",
      "color": "#ffb202"
    },
    {
      "id": "matkahuolto",
      "catalog": "matkahuolto",
      "slug": "suivi-matkahuolto",
      "title": "Suivi Matkahuolto\u00a0: statuts, points relais et contact",
      "name": "Matkahuolto",
      "color": "#001e60"
    },
    {
      "id": "ukrposhta",
      "catalog": "ukrposhta",
      "slug": "suivi-ukrposhta",
      "title": "Suivi Ukrposhta\u00a0: numéros, statuts et retrait en bureau",
      "name": "Ukrposhta",
      "color": "#ffbe0c"
    },
    {
      "id": "acs-courier",
      "catalog": "acs-courier",
      "slug": "suivi-acs-courier",
      "title": "Suivi ACS Courier\u00a0: numéros, statuts et contacts",
      "name": "ACS Courier",
      "color": "#e42229"
    },
    {
      "id": "elta",
      "catalog": "elta",
      "slug": "suivi-elta-hellenic-post",
      "title": "Suivi ELTA Hellenic Post\u00a0: statuts, retrait et contacts",
      "name": "ELTA Hellenic Post",
      "color": "#0063a9"
    },
    {
      "id": "geniki-taxydromiki",
      "catalog": "geniki-taxydromiki",
      "slug": "suivi-geniki-taxydromiki",
      "title": "Suivi Geniki Taxydromiki\u00a0: numéros, statuts et contacts",
      "name": "Geniki Taxydromiki",
      "color": "#004fa0"
    },
    {
      "id": "cyprus-post",
      "catalog": "cyprus-post",
      "slug": "suivi-cyprus-post",
      "title": "Suivi Cyprus Post\u00a0: numéros, statuts et douane",
      "name": "Cyprus Post",
      "color": "#e4a50e"
    },
    {
      "id": "post-luxembourg",
      "catalog": "post-luxembourg",
      "slug": "post-luxembourg-suivi-colis",
      "title": "POST Luxembourg\u00a0: suivi de colis, PackUp et douane",
      "name": "POST Luxembourg",
      "color": "#008736"
    },
    {
      "id": "maltapost",
      "catalog": "maltapost",
      "slug": "suivi-maltapost",
      "title": "Suivi MaltaPost\u00a0: numéros, statuts et retrait",
      "name": "MaltaPost",
      "color": "#d43b2c"
    },
    {
      "id": "whistl",
      "catalog": "whistl",
      "slug": "suivi-whistl",
      "title": "Suivi Whistl\u00a0: qui livre ton colis et que faire",
      "name": "Whistl",
      "color": "#fd4f00"
    },
    {
      "id": "xdp",
      "catalog": "xdp",
      "slug": "suivi-xdp",
      "title": "Suivi XDP\u00a0: numéros d’envoi, statuts et livraison",
      "name": "XDP",
      "color": "#53b6ed"
    },
    {
      "id": "apc-overnight",
      "catalog": "apc-overnight",
      "slug": "suivi-apc-overnight",
      "title": "Suivi APC Overnight\u00a0: numéros, statuts et livraisons",
      "name": "APC Overnight",
      "color": "#243269"
    },
    {
      "id": "ups",
      "catalog": "ups",
      "slug": "ups-tracking-suivi-colis",
      "title": "UPS tracking\u00a0: suivi de colis, statuts et livraison",
      "name": "UPS",
      "color": "#351c15"
    },
    {
      "id": "fedex",
      "catalog": "fedex",
      "slug": "fedex-suivi-colis",
      "title": "FedEx suivi\u00a0: numéro, statuts et livraison manquée",
      "name": "FedEx",
      "color": "#4d148c"
    },
    {
      "id": "dhl-express",
      "catalog": "dhl-express",
      "slug": "dhl-express-suivi",
      "title": "DHL Express suivi\u00a0: numéro de bordereau et statuts",
      "name": "DHL Express",
      "color": "#ffcc00"
    },
    {
      "id": "dhl",
      "catalog": "dhl",
      "slug": "dhl-paket-suivi-colis",
      "title": "Suivi colis DHL Paket\u00a0: numéro, statuts et livraison",
      "name": "DHL Paket",
      "color": "#ffcc00"
    },
    {
      "id": "la-poste",
      "catalog": "la-poste",
      "slug": "suivi-la-poste-colissimo",
      "title": "La Poste / Colissimo\u00a0: suivi de colis, numéro et statuts",
      "name": "La Poste / Colissimo",
      "color": "#ffcf00"
    },
    {
      "id": "correos-spain",
      "catalog": "correos-spain",
      "slug": "correos-espagne-suivi",
      "title": "Suivi Correos\u00a0: suivre un colis d’Espagne vers la France",
      "name": "Correos",
      "color": "#ffcd00"
    },
    {
      "id": "royal-mail",
      "catalog": "royal-mail",
      "slug": "suivi-royal-mail",
      "title": "Suivi colis Royal Mail\u00a0: numéro, statuts, douane et relais",
      "name": "Royal Mail",
      "color": "#da202a"
    },
    {
      "id": "usps",
      "catalog": "usps",
      "slug": "suivi-usps",
      "title": "Suivi USPS\u00a0: numéro, statuts et colis depuis les États-Unis",
      "name": "USPS",
      "color": "#004b87"
    },
    {
      "id": "canada-post",
      "catalog": "canada-post",
      "slug": "canada-post-suivi",
      "title": "Postes Canada (Canada Post) suivi\u00a0: numéro, statuts, avis",
      "name": "Canada Post",
      "color": "#e4002b"
    },
    {
      "id": "purolator",
      "catalog": "purolator",
      "slug": "purolator-suivi",
      "title": "Purolator suivi\u00a0: NIC, statuts et livraison manquée",
      "name": "Purolator",
      "color": "#da291c"
    },
    {
      "id": "intelcom",
      "catalog": "intelcom",
      "slug": "intelcom-dragonfly-suivi-colis",
      "title": "Intelcom / Dragonfly suivi colis\u00a0: statuts et contact",
      "name": "Intelcom / Dragonfly",
      "color": "#13a58f"
    },
    {
      "id": "canpar",
      "catalog": "canpar",
      "slug": "canpar-suivi",
      "title": "Canpar suivi\u00a0: numéro, avis de livraison et SMARTSpot",
      "name": "Canpar",
      "color": "#003b70"
    },
    {
      "id": "uniuni",
      "catalog": "uniuni",
      "slug": "uniuni-suivi-colis",
      "title": "UniUni suivi colis\u00a0: statuts, livraison et contact",
      "name": "UniUni",
      "color": "#f68841"
    },
    {
      "id": "ontrac",
      "catalog": "ontrac",
      "slug": "suivi-ontrac",
      "title": "Suivi OnTrac\u00a0: numéros, statuts et livraisons manquées",
      "name": "OnTrac",
      "color": "#cc2027"
    },
    {
      "id": "lso",
      "catalog": "lso",
      "slug": "suivi-lso",
      "title": "Suivi LSO\u00a0: numéros d’envoi, statuts et livraisons",
      "name": "LSO",
      "color": "#0047ba"
    },
    {
      "id": "gofo",
      "catalog": "gofo",
      "slug": "gofo-express-suivi",
      "title": "Suivi GOFO Express\u00a0: numéro GFUS, statuts et aide",
      "name": "GOFO Express",
      "color": "#fc4c02"
    },
    {
      "id": "speedx",
      "catalog": "speedx",
      "slug": "suivi-speedx",
      "title": "Suivi SpeedX\u00a0: numéros SPX, statuts et livraison manquée",
      "name": "SpeedX",
      "color": "#1aa3ff"
    },
    {
      "id": "australia-post",
      "catalog": "australia-post",
      "slug": "suivi-australia-post",
      "title": "Suivi Australia Post\u00a0: numéros, statuts et consignes",
      "name": "Australia Post",
      "color": "#dc1928"
    },
    {
      "id": "aramex-au",
      "catalog": "aramex-au",
      "slug": "suivi-aramex-australie",
      "title": "Suivi Aramex Australie\u00a0: numéros, statuts et contacts",
      "name": "Aramex Australia",
      "color": "#dc291e"
    },
    {
      "id": "couriersplease",
      "catalog": "couriersplease",
      "slug": "suivi-couriersplease",
      "title": "Suivi CouriersPlease\u00a0: numéros, statuts et aide",
      "name": "CouriersPlease",
      "color": "#c21e36"
    },
    {
      "id": "nz-post",
      "catalog": "nz-post",
      "slug": "suivi-nz-post",
      "title": "Suivi NZ Post\u00a0: numéros, statuts et colis manqués",
      "name": "NZ Post",
      "color": "#d8291c"
    },
    {
      "id": "aramex-nz",
      "catalog": "aramex-nz",
      "slug": "suivi-aramex-nouvelle-zelande",
      "title": "Suivi Aramex Nouvelle-Zélande\u00a0: numéros, statuts, contacts",
      "name": "Aramex New Zealand",
      "color": "#dc291e"
    },
    {
      "id": "magyar-posta",
      "catalog": "magyar-posta",
      "slug": "suivi-magyar-posta",
      "title": "Suivi Magyar Posta\u00a0: statuts hongrois, PostaPont et douane",
      "name": "Magyar Posta",
      "color": "#346631"
    },
    {
      "id": "shiprocket",
      "catalog": "shiprocket",
      "slug": "suivi-shiprocket",
      "title": "Suivi Shiprocket\u00a0: numéros AWB, statuts et RTO",
      "name": "Shiprocket",
      "color": "#735ae5"
    },
    {
      "id": "first-line",
      "catalog": "first-line",
      "slug": "suivi-first-line",
      "title": "Suivi FIRST LINE\u00a0: numéros 1ST, statuts et livraison",
      "name": "FIRST LINE",
      "color": "#55207a"
    },
    {
      "id": "j-and-t-de",
      "catalog": "j-and-t-de",
      "slug": "jt-express-allemagne-suivi",
      "title": "Suivi J&T Express Allemagne\u00a0: numéro JTDE et statuts",
      "name": "J&T Express Germany",
      "color": "#e60000"
    },
    {
      "id": "kun-express",
      "catalog": "kun-express",
      "slug": "suivi-kun-express",
      "title": "Suivi Kun Express\u00a0: numéros KUN, statuts et USPS",
      "name": "Kun Express",
      "color": "#0761ff"
    },
    {
      "id": "juxi-express",
      "catalog": "juxi-express",
      "slug": "juxi-express-suivi",
      "title": "Juxi Express suivi\u00a0: numéro JXC, livraison et contact",
      "name": "Juxi Express",
      "color": "#e2393c"
    },
    {
      "id": "lingdong",
      "catalog": "lingdong",
      "slug": "suivi-lingdong",
      "title": "Suivi Lingdong International\u00a0: numéro SZLD et statuts",
      "name": "Lingdong International",
      "color": "#0171bb"
    },
    {
      "id": "j-and-t-au",
      "catalog": "j-and-t-au",
      "slug": "suivi-jt-express-australia",
      "title": "Suivi J&T Express Australia\u00a0: numéros JTAU et statuts",
      "name": "J&T Express Australia",
      "color": "#e60000"
    },
    {
      "id": "china-post",
      "catalog": "china-post",
      "slug": "china-post-suivi",
      "title": "Suivi China Post\u00a0: numéro, statuts chinois et livraison",
      "name": "China Post",
      "color": "#006d46"
    },
    {
      "id": "fedex-pl",
      "catalog": "fedex-pl",
      "slug": "suivi-fedex-pologne",
      "title": "Suivi FedEx Pologne\u00a0: numéros à 13 chiffres et statuts",
      "name": "FedEx Poland",
      "color": "#4d148c"
    },
    {
      "id": "techlink",
      "catalog": "techlink",
      "slug": "suivi-techlink",
      "title": "Suivi Techlink\u00a0: numéros ZHPHD…ML, statuts et livraison",
      "name": "Techlink",
      "color": "#e73547"
    },
    {
      "id": "z-leading",
      "catalog": "z-leading",
      "slug": "suivi-z-leading",
      "title": "Suivi Z-Leading (Zhongling)\u00a0: numéros, livraison et aide",
      "name": "Z-Leading",
      "color": "#005ab0"
    },
    {
      "id": "imile",
      "catalog": "imile",
      "slug": "imile-suivi",
      "title": "Suivi colis iMile\u00a0: numéro, statuts et livraison",
      "name": "iMile",
      "color": "#0032a0"
    },
    {
      "id": "postnl",
      "catalog": "spring-gds",
      "slug": "postnl-suivi",
      "title": "Suivi PostNL\u00a0: numéros 3S et NL, statuts et livraison",
      "name": "PostNL",
      "color": "#ef7d00"
    },
    {
      "id": "dhlink",
      "catalog": "dhlink",
      "slug": "dhlink-suivi",
      "title": "Suivi colis DHLink\u00a0: numéro DHgate, statuts et livraison",
      "name": "DHLink",
      "color": "#143167"
    },
    {
      "id": "dpd-pt",
      "catalog": "dpd-pt",
      "slug": "dpd-portugal-suivi",
      "title": "Suivi DPD Portugal\u00a0: statuts, points Pickup et contact",
      "name": "DPD Portugal",
      "color": "#dc0032"
    },
    {
      "id": "tnt-it",
      "catalog": "tnt-it",
      "slug": "suivi-tnt-italie",
      "title": "Suivi TNT Italie\u00a0: lettre de voiture, statuts et livraison",
      "name": "TNT Italy",
      "color": "#ff6600"
    },
    {
      "id": "tnt",
      "catalog": "tnt",
      "slug": "tnt-suivi-colis",
      "title": "TNT suivi colis\u00a0: bon de transport, statuts et livraison",
      "name": "TNT",
      "color": "#ff6600"
    },
    {
      "id": "sf-express",
      "catalog": "sf-express",
      "slug": "suivi-sf-express",
      "title": "Suivi SF Express\u00a0: numéro, statuts, douane et livraison",
      "name": "SF Express",
      "color": "#dc1e32"
    },
    {
      "id": "straightship",
      "catalog": "straightship",
      "slug": "suivi-colis-straightship",
      "title": "Suivi Straightship\u00a0: numéros STRTD, statuts et livraison",
      "name": "Straightship",
      "color": "#6aae66"
    },
    {
      "id": "correios-br",
      "catalog": "correios-br",
      "slug": "suivi-correios-bresil",
      "title": "Suivi Correios Brésil\u00a0: codes, statuts et douane",
      "name": "Correios Brazil",
      "color": "#ffd500"
    },
    {
      "id": "yto",
      "catalog": "yto",
      "slug": "suivi-yto-express",
      "title": "Suivi YTO Express\u00a0: numéro YT, statuts et contacts",
      "name": "YTO Express",
      "color": "#4d148c"
    },
    {
      "id": "spx-br",
      "catalog": "spx-br",
      "slug": "suivi-spx-express-bresil",
      "title": "Suivi SPX Express Brésil\u00a0: commandes Shopee et statuts",
      "name": "SPX Express Brazil",
      "color": "#ee4d2d"
    },
    {
      "id": "eushipments",
      "catalog": "eushipments",
      "slug": "suivi-eushipments",
      "title": "Suivi euShipments\u00a0: statuts et transporteur local",
      "name": "euShipments",
      "color": "#0a8fcf"
    },
    {
      "id": "mhi",
      "catalog": "mhi",
      "slug": "suivi-mhi",
      "title": "Suivi MHI\u00a0: statuts Trackmail et transporteur final",
      "name": "MHI",
      "color": "#00075c"
    },
    {
      "id": "pengyou",
      "catalog": "pengyou",
      "slug": "suivi-pengyou",
      "title": "Suivi Pengyou\u00a0: numéros PY, étapes et qui contacter",
      "name": "Pengyou",
      "color": "#043a82"
    },
    {
      "id": "linhan",
      "catalog": "linhan",
      "slug": "suivi-linhan",
      "title": "Suivi LinHan International\u00a0: numéros LHGJ et statuts",
      "name": "LinHan International",
      "color": "#eb5c20"
    },
    {
      "id": "mtscn",
      "catalog": "mtscn",
      "slug": "meiteshun-suivi-colis",
      "title": "Suivi colis Meiteshun\u00a0: numéro AMTS…FS, statuts et contact",
      "name": "Meiteshun Logistics",
      "color": "#f89f3b"
    },
    {
      "id": "cttyex",
      "catalog": "cttyex",
      "slug": "suivi-cttyex",
      "title": "Suivi CTTYEX\u00a0: numéros CTTY…CN, statuts et heure de Pékin",
      "name": "CTTYEX",
      "color": "#0068AC"
    },
    {
      "id": "speedaf",
      "catalog": "speedaf",
      "slug": "speedaf-suivi",
      "title": "Suivi colis Speedaf Express\u00a0: numéro, statuts et contacts",
      "name": "Speedaf",
      "color": "#ff8300"
    },
    {
      "id": "yilong-logistics",
      "catalog": "yilong-logistics",
      "slug": "suivi-yilong-logistics",
      "title": "Suivi Yilong Logistics\u00a0: numéros YIL…WL et statuts",
      "name": "Yilong Logistics",
      "color": "#ec0307"
    },
    {
      "id": "quick-rabbit",
      "catalog": "quick-rabbit",
      "slug": "suivi-quick-rabbit",
      "title": "Suivi Quick Rabbit\u00a0: codes PKG, statuts et Citibox",
      "name": "Quick Rabbit",
      "color": "#f6851f"
    },
    {
      "id": "huaya-express",
      "catalog": "huaya-express",
      "slug": "suivi-huaya-express",
      "title": "Suivi Huaya Express\u00a0: numéros HYYT…GZ, statuts et relais",
      "name": "Huaya Express",
      "color": "#e60013"
    },
    {
      "id": "gofo-ca",
      "catalog": "gofo-ca",
      "slug": "suivi-gofo-ca",
      "title": "Suivi GOFO Canada\u00a0: numéros GFCA, statuts et retards",
      "name": "GOFO Canada",
      "color": "#fc4c02"
    },
    {
      "id": "metropolitan-warehouse",
      "catalog": "metropolitan-warehouse",
      "slug": "suivi-metropolitan-warehouse",
      "title": "Suivi Metropolitan Warehouse (MWD)\u00a0: meubles et aide",
      "name": "Metropolitan Warehouse & Delivery",
      "color": "#0062b7"
    },
    {
      "id": "topyou",
      "catalog": "topyou",
      "slug": "topyou-suivi-colis",
      "title": "TopYou suivi colis\u00a0: numéro TYZR, statuts et livraison",
      "name": "TopYou",
      "color": "#045cd7"
    },
    {
      "id": "byhd",
      "catalog": "byhd",
      "slug": "suivi-byhd",
      "title": "Suivi BYHD\u00a0: numéros BYHD, statuts et transporteur final",
      "name": "BYHD",
      "color": "#084282"
    },
    {
      "id": "zto-international",
      "catalog": "zto-international",
      "slug": "suivi-zto-international",
      "title": "Suivi ZTO International\u00a0: numéro 771, statuts et contact",
      "name": "ZTO International",
      "color": "#3a8eff"
    },
    {
      "id": "mayiyinfu",
      "catalog": "mayiyinfu",
      "slug": "suivi-mayi-yinfu",
      "title": "Suivi Mayi Yinfu\u00a0: numéros MY, vols et relais DHL",
      "name": "Mayi Yinfu",
      "color": "#000000"
    },
    {
      "id": "jrt",
      "catalog": "jrt",
      "slug": "jrt-suivi-colis",
      "title": "Suivi colis JRT\u00a0: numéro JRT…MM, vols, douane et DHL",
      "name": "JRT",
      "color": "#023053"
    },
    {
      "id": "thhy",
      "catalog": "thhy",
      "slug": "suivi-tianhui-hengyuan",
      "title": "Suivi Tianhui Hengyuan (THHY)\u00a0: numéros AT et scans",
      "name": "Tianhui Hengyuan",
      "color": "#00a2e9"
    },
    {
      "id": "js-express",
      "catalog": "js-express",
      "slug": "js-express-suivi",
      "title": "Suivi colis JS Express\u00a0: numéro JSE, statuts et livraison",
      "name": "JS Express",
      "color": "#fe9b00"
    },
    {
      "id": "wanbexpress",
      "catalog": "wanbexpress",
      "slug": "suivi-wanbexpress",
      "title": "Suivi WanbExpress\u00a0: numéros WNB…YQ, statuts et livraison",
      "name": "WanbExpress",
      "color": "#da381b"
    },
    {
      "id": "gzyl",
      "catalog": "gzyl",
      "slug": "gzyl-suivi-colis",
      "title": "Suivi colis GZYL\u00a0: numéro YL…YQ, statuts et qui contacter",
      "name": "GZYL",
      "color": "#4e79e1"
    },
    {
      "id": "shouwu",
      "catalog": "shouwu",
      "slug": "shouwu-suivi",
      "title": "Suivi colis Shouwu\u00a0: numéro SWECN…YQ, statuts et livraison",
      "name": "Shouwu International",
      "color": "#be2c33"
    },
    {
      "id": "geodis-pl",
      "catalog": "geodis-pl",
      "slug": "geodis-pologne-suivi",
      "title": "GEODIS Pologne suivi\u00a0: numéro PL, statuts et livraison",
      "name": "GEODIS Poland",
      "color": "#3100e6"
    },
    {
      "id": "sending",
      "catalog": "sending",
      "slug": "suivi-sending",
      "title": "Suivi Sending en Espagne\u00a0: numéro, statuts et téléphone",
      "name": "Sending",
      "color": "#e52021"
    },
    {
      "id": "hywl",
      "catalog": "hywl",
      "slug": "hywl-suivi-colis",
      "title": "Suivi colis HYWL (Huiyi Logistics)\u00a0: numéro TRK et statuts",
      "name": "Huiyi Logistics",
      "color": "#0b8f91"
    },
    {
      "id": "ywe",
      "catalog": "ywe",
      "slug": "suivi-ywe",
      "title": "Suivi YWE (YanwenExpress)\u00a0: numéro, scans et aide",
      "name": "YWE",
      "color": "#055aa1"
    },
    {
      "id": "roadie",
      "catalog": "roadie",
      "slug": "suivi-roadie",
      "title": "Suivi Roadie\u00a0: numéros 1R, statuts et photos de livraison",
      "name": "Roadie",
      "color": "#76cccd"
    },
    {
      "id": "ecms-express",
      "catalog": "ecms-express",
      "slug": "suivi-ecms-express",
      "title": "Suivi ECMS Express\u00a0: numéros à 16 caractères et statuts",
      "name": "ECMS Express",
      "color": "#12823d"
    },
    {
      "id": "chunghwa-post",
      "catalog": "chunghwa-post",
      "slug": "chunghwa-post-tracking",
      "title": "Suivi Chunghwa Post (Taiwan Post)\u00a0: numéro TW et statuts",
      "name": "Chunghwa Post",
      "color": "#e8171f"
    },
    {
      "id": "raben",
      "catalog": "raben",
      "slug": "suivi-raben",
      "title": "Suivi Raben\u00a0: numéro d'envoi, statuts et livraison",
      "name": "Raben",
      "color": "#df0020"
    },
    {
      "id": "jd-logistics",
      "catalog": "jd-logistics",
      "slug": "suivi-colis-jd-logistics",
      "title": "Suivi colis JD Logistics\u00a0: numéros JD, statuts et contact",
      "name": "JD Logistics",
      "color": "#e1251b"
    },
    {
      "id": "singapore-post",
      "catalog": "singapore-post",
      "slug": "suivi-singapore-post",
      "title": "Suivi Singapore Post (SingPost)\u00a0: numéros et statuts",
      "name": "Singapore Post",
      "color": "#2e5da8"
    },
    {
      "id": "zto",
      "catalog": "zto",
      "slug": "suivi-zto-express",
      "title": "Suivi ZTO Express\u00a0: numéros 中通快递, statuts et contact",
      "name": "ZTO Express",
      "color": "#0279ff"
    },
    {
      "id": "cne",
      "catalog": "cne",
      "slug": "suivi-cne-express",
      "title": "Suivi CNE Express\u00a0: numéros 3A5V, statuts et livraison",
      "name": "CNE Express",
      "color": "#e83828"
    },
    {
      "id": "japan-post",
      "catalog": "japan-post",
      "slug": "suivi-japan-post",
      "title": "Suivi Japan Post\u00a0: numéros EMS et statuts expliqués",
      "name": "Japan Post",
      "color": "#cc0000"
    },
    {
      "id": "russian-post",
      "catalog": "russian-post",
      "slug": "suivi-russian-post",
      "title": "Suivi Russian Post (Почта России)\u00a0: numéros et statuts",
      "name": "Russian Post",
      "color": "#1928dc"
    },
    {
      "id": "huahan",
      "catalog": "huahan",
      "slug": "suivi-colis-huahan-logistics",
      "title": "Suivi HuaHan Logistics\u00a0: numéro HHW…YQ et livraison",
      "name": "HuaHan Logistics",
      "color": "#0069e8"
    },
    {
      "id": "epost-global",
      "catalog": "epost-global",
      "slug": "suivi-epost-global",
      "title": "Suivi ePost Global\u00a0: numéros EPG, statuts et livraison",
      "name": "ePost Global",
      "color": "#27967a"
    },
    {
      "id": "gls-at",
      "catalog": "gls-at",
      "slug": "suivi-gls-austria",
      "title": "Suivi GLS Austria\u00a0: statuts, Parcel Shops et contact",
      "name": "GLS Austria",
      "color": "#ffdd00"
    },
    {
      "id": "india-post",
      "catalog": "india-post",
      "slug": "suivi-india-post",
      "title": "Suivi India Post et Speed Post\u00a0: numéros, statuts et douane",
      "name": "India Post",
      "color": "#b82025"
    },
    {
      "id": "morning-global",
      "catalog": "morning-global",
      "slug": "suivi-morning-global",
      "title": "Suivi Morning Global\u00a0: numéros BR…MG, statuts et contacts",
      "name": "Morning Global",
      "color": "#050059"
    },
    {
      "id": "border-express",
      "catalog": "border-express",
      "slug": "suivi-border-express",
      "title": "Suivi Border Express (BEX)\u00a0: numéros d’envoi et statuts",
      "name": "Border Express",
      "color": "#cc092f"
    },
    {
      "id": "delhivery",
      "catalog": "delhivery",
      "slug": "suivi-delhivery",
      "title": "Suivi Delhivery\u00a0: numéros AWB, statuts et assistance",
      "name": "Delhivery",
      "color": "#ed4136"
    },
    {
      "id": "aras-kargo",
      "catalog": "aras-kargo",
      "slug": "suivi-aras-kargo",
      "title": "Suivi Aras Kargo\u00a0: numéros et statuts en français",
      "name": "Aras Kargo",
      "color": "#dc0812"
    },
    {
      "id": "emirates-post",
      "catalog": "emirates-post",
      "slug": "suivi-emirates-post",
      "title": "Suivi Emirates Post\u00a0: numéros AE et statuts expliqués",
      "name": "Emirates Post",
      "color": "#004a98"
    },
    {
      "id": "blue-dart",
      "catalog": "blue-dart",
      "slug": "suivi-blue-dart",
      "title": "Suivi Blue Dart\u00a0: numéros de lettre de transport et statuts",
      "name": "Blue Dart",
      "color": "#0059a9"
    },
    {
      "id": "ekart",
      "catalog": "ekart",
      "slug": "suivi-ekart",
      "title": "Suivi Ekart\u00a0: identifiants, statuts InscannedAtDH et RTO",
      "name": "Ekart",
      "color": "#0074D9"
    },
    {
      "id": "j-and-t-middle-east",
      "catalog": "j-and-t-middle-east",
      "slug": "suivi-jt-express-middle-east",
      "title": "Suivi J&T Express Middle East\u00a0: numéros JTE et statuts",
      "name": "J&T Express Middle East",
      "color": "#e60000"
    },
    {
      "id": "dpd-ro",
      "catalog": "dpd-ro",
      "slug": "suivi-dpd-romania",
      "title": "Suivi DPD Romania\u00a0: statuts, Smart Control et contact",
      "name": "DPD Romania",
      "color": "#dc0032"
    },
    {
      "id": "globalpost",
      "catalog": "globalpost",
      "slug": "suivi-globalpost",
      "title": "Suivi GlobalPost\u00a0: numéros GP, statuts et livraison",
      "name": "GlobalPost",
      "color": "#11003a"
    },
    {
      "id": "cdek",
      "catalog": "cdek",
      "slug": "suivi-cdek",
      "title": "Suivi CDEK (СДЭК)\u00a0: numéros de commande et statuts",
      "name": "CDEK",
      "color": "#00b33c"
    },
    {
      "id": "forza-delivery",
      "catalog": "forza-delivery",
      "slug": "suivi-forza-delivery",
      "title": "Suivi Forza Delivery\u00a0: numéros de guía, statuts, contact",
      "name": "Forza Delivery Express",
      "color": "#ea5c1d"
    },
    {
      "id": "quantium-solutions",
      "catalog": "quantium-solutions",
      "slug": "suivi-quantium-solutions",
      "title": "Suivi Quantium Solutions\u00a0: numéros, statuts et assistance",
      "name": "Quantium Solutions",
      "color": "#00474f"
    },
    {
      "id": "correos-chile",
      "catalog": "correos-chile",
      "slug": "suivi-correos-chile",
      "title": "Suivi Correos de Chile\u00a0: numéros et statuts expliqués",
      "name": "Correos de Chile",
      "color": "#d9232d"
    },
    {
      "id": "posta-shqiptare",
      "catalog": "posta-shqiptare",
      "slug": "suivi-posta-shqiptare",
      "title": "Suivi Posta Shqiptare\u00a0: numéros AL, statuts et douane",
      "name": "Posta Shqiptare",
      "color": "#1a3292"
    },
    {
      "id": "dsv",
      "catalog": "dsv",
      "slug": "suivi-dsv",
      "title": "Suivi DSV\u00a0: numéros, statuts et DB Schenker",
      "name": "DSV",
      "color": "#002664"
    },
    {
      "id": "pin-ag",
      "catalog": "pin-ag",
      "slug": "suivi-pin-ag",
      "title": "Suivi PIN AG\u00a0: recommandés, colis et retrait en dépôt",
      "name": "PIN AG",
      "color": "#003366"
    },
    {
      "id": "pitney-bowes",
      "catalog": "pitney-bowes",
      "slug": "suivi-pitney-bowes",
      "title": "Suivi Pitney Bowes\u00a0: numéros UPAA, UPBE et PBX expliqués",
      "name": "Pitney Bowes",
      "color": "#3e53a4"
    },
    {
      "id": "rl-carriers",
      "catalog": "rl-carriers",
      "slug": "suivi-rl-carriers",
      "title": "Suivi R+L Carriers\u00a0: numéro PRO, statuts et réclamations",
      "name": "R+L Carriers",
      "color": "#006647"
    },
    {
      "id": "nox",
      "catalog": "nox",
      "slug": "suivi-nox",
      "title": "Suivi nox NachtExpress\u00a0: statuts, dépôt et contact",
      "name": "nox",
      "color": "#fadb00"
    },
    {
      "id": "far-international",
      "catalog": "far-international",
      "slug": "suivi-far-international",
      "title": "Suivi FAR International\u00a0: numéros, statuts et contact",
      "name": "FAR International",
      "color": "#003b75"
    },
    {
      "id": "jifan",
      "catalog": "jifan",
      "slug": "suivi-jifan",
      "title": "Suivi Jifan\u00a0: numéros JFGJ, statuts et heure de Pékin",
      "name": "Jifan",
      "color": "#c81011"
    },
    {
      "id": "posta-romana",
      "catalog": "posta-romana",
      "slug": "suivi-posta-romana",
      "title": "Suivi Poșta Română\u00a0: numéros RO, statuts et douane",
      "name": "Poșta Română",
      "color": "#ea0029"
    },
    {
      "id": "dpd-nl",
      "catalog": "dpd-nl",
      "slug": "suivi-dpd-pays-bas",
      "title": "Suivi DPD Pays-Bas\u00a0: statuts, livraison et contact",
      "name": "DPD Netherlands",
      "color": "#dc0032"
    },
    {
      "id": "dpd-at",
      "catalog": "dpd-at",
      "slug": "suivi-dpd-austria",
      "title": "Suivi DPD Austria\u00a0: statuts, points Pickup et contact",
      "name": "DPD Austria",
      "color": "#dc0032"
    },
    {
      "id": "ozon-logistics",
      "catalog": "ozon-logistics",
      "slug": "suivi-ozon",
      "title": "Suivi Ozon\u00a0: numéros de commande et statuts en français",
      "name": "Ozon",
      "color": "#005bff"
    },
    {
      "id": "citymail",
      "catalog": "citymail",
      "slug": "suivi-citymail",
      "title": "Suivi CityMail\u00a0: numéros, statuts et livraison en Suède",
      "name": "CityMail",
      "color": "#fc4c02"
    },
    {
      "id": "dayone",
      "catalog": "dayone",
      "slug": "suivi-dayone",
      "title": "Suivi DayOne\u00a0: numéros DOT, statuts et livraison",
      "name": "DayOne",
      "color": "#e86d1c"
    },
    {
      "id": "dpd-be",
      "catalog": "dpd-be",
      "slug": "suivi-dpd-belgium",
      "title": "Suivi DPD Belgium\u00a0: statuts, livraison et contact",
      "name": "DPD Belgium",
      "color": "#dc0032"
    },
    {
      "id": "packlink",
      "catalog": "packlink",
      "slug": "suivi-packlink",
      "title": "Suivi Packlink\u00a0: référence, statuts et qui contacter",
      "name": "Packlink",
      "color": "#ff4f00"
    },
    {
      "id": "sto",
      "catalog": "sto",
      "slug": "suivi-sto-express",
      "title": "Suivi STO Express\u00a0: numéros 申通快递, statuts et contact",
      "name": "STO Express",
      "color": "#ff6700"
    },
    {
      "id": "j-and-t-cn",
      "catalog": "j-and-t-cn",
      "slug": "suivi-jt-express-china",
      "title": "Suivi J&T Express China\u00a0: numéros JT, statuts et contact",
      "name": "J&T Express China",
      "color": "#e62019"
    },
    {
      "id": "tforce-freight",
      "catalog": "tforce-freight",
      "slug": "suivi-tforce-freight",
      "title": "Suivi TForce Freight\u00a0: numéros PRO, statuts et litiges",
      "name": "TForce Freight",
      "color": "#0053a1"
    },
    {
      "id": "andreani",
      "catalog": "andreani",
      "slug": "suivi-andreani",
      "title": "Suivi Andreani\u00a0: numéros de colis et statuts en Argentine",
      "name": "Andreani",
      "color": "#d71920"
    },
    {
      "id": "mainfreight",
      "catalog": "mainfreight",
      "slug": "suivi-mainfreight",
      "title": "Suivi Mainfreight\u00a0: numéros de fret et statuts",
      "name": "Mainfreight",
      "color": "#005eb8"
    },
    {
      "id": "spx-sg",
      "catalog": "spx-sg",
      "slug": "suivi-spx-express-singapore",
      "title": "Suivi SPX Express Singapour\u00a0: colis Shopee et statuts",
      "name": "SPX Express Singapore",
      "color": "#ee4d2d"
    },
    {
      "id": "dai-post",
      "catalog": "dai-post",
      "slug": "suivi-dai-post",
      "title": "Suivi DAI Post\u00a0: numéros, statuts et aide à la livraison",
      "name": "DAI Post",
      "color": "#008faf"
    },
    {
      "id": "eurodis",
      "catalog": "eurodis",
      "slug": "suivi-eurodis",
      "title": "Suivi EURODIS\u00a0: numéros d’envoi et partenaires locaux",
      "name": "EURODIS",
      "color": "#0b418b"
    },
    {
      "id": "chronodiali",
      "catalog": "chronodiali",
      "slug": "suivi-chronodiali",
      "title": "Suivi Chronodiali\u00a0: numéros CHPU, statuts et contact",
      "name": "Chronodiali",
      "color": "#009adf"
    },
    {
      "id": "17feia",
      "catalog": "17feia",
      "slug": "suivi-17feia",
      "title": "Suivi 17FEIA\u00a0: numéros FEI…YQ, statuts et livraison",
      "name": "17FEIA",
      "color": "#00a9e4"
    },
    {
      "id": "tsjy",
      "catalog": "tsjy",
      "slug": "suivi-tsjy",
      "title": "Suivi TSJY (通世集运)\u00a0: numéros de commande et statuts",
      "name": "TSJY",
      "color": "#4042e2"
    },
    {
      "id": "stone3pl",
      "catalog": "stone3pl",
      "slug": "suivi-stone3pl",
      "title": "Suivi Stone3PL\u00a0: numéros, statuts et contact",
      "name": "Stone3PL",
      "color": "#003d98"
    },
    {
      "id": "j-and-t-uk",
      "catalog": "j-and-t-uk",
      "slug": "suivi-jt-express-uk",
      "title": "Suivi J&T Express UK\u00a0: numéros JTUK et statuts",
      "name": "J&T Express UK",
      "color": "#e60000"
    },
    {
      "id": "m-one",
      "catalog": "m-one",
      "slug": "suivi-m-one",
      "title": "Suivi M-ONE (Milšped ONE)\u00a0: statuts, livraison et aide",
      "name": "M-ONE",
      "color": "#024da0"
    },
    {
      "id": "early-bird",
      "catalog": "early-bird",
      "slug": "suivi-early-bird",
      "title": "Suivi Early Bird\u00a0: statuts, livraison matinale et casiers",
      "name": "Early Bird",
      "color": "#dd7f4a"
    },
    {
      "id": "xinghe-freight",
      "catalog": "xinghe-freight",
      "slug": "xinghe-freight-suivi-colis",
      "title": "Suivi colis Xinghe Freight\u00a0: numéro GZXH…YQ et statuts",
      "name": "Xinghe Freight",
      "color": "#0e3a6b"
    },
    {
      "id": "cvc",
      "catalog": "cvc",
      "slug": "suivi-cvc",
      "title": "Suivi CVC (Си Ви Си)\u00a0: suivre un colis en Bulgarie",
      "name": "CVC",
      "color": "#652b9e"
    },
    {
      "id": "transpress",
      "catalog": "transpress",
      "slug": "suivi-transpress",
      "title": "Suivi Transpress (Транспрес)\u00a0: suivre un colis en Bulgarie",
      "name": "Transpress",
      "color": "#1e6ee2"
    },
    {
      "id": "gls-us",
      "catalog": "gls-us",
      "slug": "suivi-gls-us",
      "title": "Suivi GLS US (GSO)\u00a0: numéros, statuts et livraison",
      "name": "GLS US",
      "color": "#ffdd00"
    },
    {
      "id": "sxw-express",
      "catalog": "sxw-express",
      "slug": "suivi-sxw-express",
      "title": "Suivi SXW Express\u00a0: numéros SXW, statuts et livraison",
      "name": "SXW Express",
      "color": "#003876"
    },
    {
      "id": "xuchuan-logistics",
      "catalog": "xuchuan-logistics",
      "slug": "suivi-xuchuan-logistics",
      "title": "Suivi Xuchuan Logistics\u00a0: numéros XC, livraison et aide",
      "name": "Xuchuan International Logistics",
      "color": "#0a2463"
    },
    {
      "id": "samus",
      "catalog": "samus",
      "slug": "suivi-samus",
      "title": "Suivi SAMUS\u00a0: numéros, statuts et retrait à Vilnius",
      "name": "SAMUS",
      "color": "#e50079"
    },
    {
      "id": "siuntos-autobusais",
      "catalog": "siuntos-autobusais",
      "slug": "suivi-siuntos-autobusais",
      "title": "Suivi Siuntos autobusais\u00a0: colis par bus en Lituanie",
      "name": "Siuntos autobusais",
      "color": "#00843d"
    },
    {
      "id": "zipasgo",
      "catalog": "zipasgo",
      "slug": "suivi-zipasgo",
      "title": "Suivi ZipasGo\u00a0: Parcel Stars, partenaires et aide",
      "name": "ZipasGo",
      "color": "#ffdb1c"
    },
    {
      "id": "dk-logistics-ee",
      "catalog": "dk-logistics-ee",
      "slug": "suivi-dk-logistics",
      "title": "Suivi DK Logistics\u00a0: bordereau, livraison et contact",
      "name": "DK Logistics",
      "color": "#f58428"
    },
    {
      "id": "jet-express-ee",
      "catalog": "jet-express-ee",
      "slug": "suivi-jet-express",
      "title": "Suivi Jet Express\u00a0: numéro de commande, livraison, contact",
      "name": "Jet Express",
      "color": "#d61b22"
    },
    {
      "id": "fan-courier-international",
      "catalog": "fan-courier-international",
      "slug": "suivi-fan-courier-international",
      "title": "Suivi FAN Courier International\u00a0: AWB et statuts",
      "name": "FAN Courier International",
      "color": "#2daae1"
    },
    {
      "id": "j-and-t-br",
      "catalog": "j-and-t-br",
      "slug": "suivi-jt-express-brazil",
      "title": "Suivi J&T Express Brazil\u00a0: numéro, statuts et contact",
      "name": "J&T Express Brazil",
      "color": "#e60000"
    },
    {
      "id": "anjun",
      "catalog": "anjun",
      "slug": "suivi-anjun-logistics",
      "title": "Suivi Anjun Logistics\u00a0: statuts Chine-Brésil et contact SAC",
      "name": "Anjun Logistics",
      "color": "#009944"
    },
    {
      "id": "xyl-ships",
      "catalog": "xyl-ships",
      "slug": "suivi-xyl-ships",
      "title": "Suivi XYL-Ships\u00a0: numéros NUDE et XLWL, statuts et aide",
      "name": "XYL-Ships",
      "color": "#b13c3c"
    },
    {
      "id": "jadlog",
      "catalog": "jadlog",
      "slug": "suivi-jadlog",
      "title": "Suivi Jadlog\u00a0: code à 14 chiffres, Pickup, Predict et aide",
      "name": "Jadlog",
      "color": "#dc0032"
    },
    {
      "id": "gls-pl",
      "catalog": "gls-pl",
      "slug": "suivi-gls-poland",
      "title": "Suivi GLS Poland\u00a0: statuts, GLS Points, consignes et aide",
      "name": "GLS Poland",
      "color": "#ffdd00"
    },
    {
      "id": "j-and-t-mx",
      "catalog": "j-and-t-mx",
      "slug": "suivi-jt-express-mexico",
      "title": "Suivi J&T Express Mexico\u00a0: codes JMX, téléphone et aide",
      "name": "J&T Express Mexico",
      "color": "#e60012"
    },
    {
      "id": "bulgarian-posts",
      "catalog": "bulgarian-posts",
      "slug": "suivi-bulgarian-posts",
      "title": "Suivi Bulgarian Posts\u00a0: codes PS et BG, douane et aide",
      "name": "Bulgarian Posts",
      "color": "#ffd100"
    },
    {
      "id": "haypost",
      "catalog": "haypost",
      "slug": "suivi-haypost",
      "title": "Suivi HayPost\u00a0: numéros AM, statuts et contacts",
      "name": "HayPost",
      "color": "#fa4616"
    },
    {
      "id": "360lion",
      "catalog": "360lion",
      "slug": "suivi-360lion",
      "title": "Suivi 360Lion\u00a0: codes WSH et AR, statuts et contacts",
      "name": "360Lion",
      "color": "#cf5d15"
    },
    {
      "id": "paquetexpress",
      "catalog": "paquetexpress",
      "slug": "suivi-paquetexpress",
      "title": "Suivi Paquetexpress\u00a0: guías, statuts, Ocurre et aide",
      "name": "Paquetexpress",
      "color": "#002a52"
    },
    {
      "id": "ydh",
      "catalog": "ydh",
      "slug": "suivi-ydh",
      "title": "Suivi YDH\u00a0: codes YDH...YQ et AZ, relais et contacts",
      "name": "YDH",
      "color": "#015db9"
    },
    {
      "id": "a-tu-hora-express",
      "catalog": "a-tu-hora-express",
      "slug": "suivi-a-tu-hora-express",
      "title": "Suivi A Tu Hora Express\u00a0: codes, code postal et aide",
      "name": "A Tu Hora Express",
      "color": "#1d2a84"
    },
    {
      "id": "dtdc",
      "catalog": "dtdc",
      "slug": "suivi-dtdc",
      "title": "Suivi DTDC\u00a0: formats AWB, statuts RTO, délais et aide",
      "name": "DTDC",
      "color": "#0d237d"
    },
    {
      "id": "gofo-es",
      "catalog": "gofo-es",
      "slug": "suivi-gofo-spain",
      "title": "Suivi GOFO Spain\u00a0: codes GFES et CIES, 3 tentatives",
      "name": "GOFO Spain",
      "color": "#fc4c02"
    },
    {
      "id": "korea-post",
      "catalog": "korea-post",
      "slug": "suivi-korea-post",
      "title": "Suivi Korea Post\u00a0: codes EMS KR, colis 13 chiffres et aide",
      "name": "Korea Post",
      "color": "#da291c"
    },
    {
      "id": "nova-global",
      "catalog": "nova-global",
      "slug": "suivi-nova-global",
      "title": "Suivi Nova Global\u00a0: codes NPG et NGUS, douane et aide",
      "name": "Nova Global",
      "color": "#da291c"
    },
    {
      "id": "xlcourier",
      "catalog": "xlcourier",
      "slug": "suivi-xlcourier",
      "title": "Suivi XLCourier\u00a0: codes XLC, colis lourds UK et aide",
      "name": "XLCourier",
      "color": "#044ad6"
    },
    {
      "id": "estafeta",
      "catalog": "estafeta",
      "slug": "suivi-estafeta",
      "title": "Suivi Estafeta\u00a0: codes 10 et 22 car., 2 tentatives, aide",
      "name": "Estafeta",
      "color": "#e30613"
    },
    {
      "id": "gls-hu",
      "catalog": "gls-hu",
      "slug": "suivi-gls-hungary",
      "title": "Suivi GLS Hungary\u00a0: numéros, consignes, délais et aide",
      "name": "GLS Hungary",
      "color": "#ffdd00"
    },
    {
      "id": "dhl-freight",
      "catalog": "dhl-freight",
      "slug": "suivi-dhl-freight",
      "title": "Suivi DHL Freight\u00a0: codes ActiveTracing, statuts et aide",
      "name": "DHL Freight",
      "color": "#ffcc00"
    },
    {
      "id": "anserx",
      "catalog": "anserx",
      "slug": "suivi-anserx",
      "title": "Suivi AnserX\u00a0: codes BTYCN...YQ, relais locaux et contact",
      "name": "AnserX",
      "color": "#2478be"
    },
    {
      "id": "bjs-home-delivery",
      "catalog": "bjs-home-delivery",
      "slug": "suivi-bjs-home-delivery",
      "title": "Suivi BJS Home Delivery\u00a0: codes B, créneaux 3 h et aide",
      "name": "BJS Home Delivery",
      "color": "#feba30"
    },
    {
      "id": "chukou1",
      "catalog": "chukou1",
      "slug": "suivi-chukou1",
      "title": "Suivi Chukou1\u00a0: codes EC-FirstClass, relais locaux et aide",
      "name": "Chukou1",
      "color": "#0b4da2"
    },
    {
      "id": "cj-packet",
      "catalog": "cj-packet",
      "slug": "suivi-cj-packet",
      "title": "Suivi CJ Packet\u00a0: codes CJP...YQ, relais locaux et contact",
      "name": "CJ Packet",
      "color": "#ff7700"
    },
    {
      "id": "cmt-express",
      "catalog": "cmt-express",
      "slug": "suivi-cmt-express",
      "title": "Suivi CMT Express\u00a0: codes CMT, hub Shenzhen et contacts",
      "name": "CMT Express",
      "color": "#ed010b"
    },
    {
      "id": "correos-de-mexico",
      "catalog": "correos-de-mexico",
      "slug": "suivi-correos-de-mexico",
      "title": "Suivi Correos de México\u00a0: codes S10 MX, réclamations et aide",
      "name": "Correos de México",
      "color": "#ed007d"
    },
    {
      "id": "dobropost",
      "catalog": "dobropost",
      "slug": "suivi-dobropost",
      "title": "Suivi DobroPost\u00a0: codes DBRPST, statuts et hubs en Chine",
      "name": "DobroPost",
      "color": "#ffbe2e"
    },
    {
      "id": "ems",
      "catalog": "ems",
      "slug": "suivi-ems",
      "title": "Suivi EMS\u00a0: numéros S10, statuts et aide postale",
      "name": "EMS",
      "color": "#003b7a"
    },
    {
      "id": "jiayang",
      "catalog": "jiayang",
      "slug": "suivi-jiayang",
      "title": "Suivi Jiayang International\u00a0: codes JYGJ, statuts et aide",
      "name": "Jiayang International",
      "color": "#105bcc"
    },
    {
      "id": "jumppoint-international",
      "catalog": "jumppoint-international",
      "slug": "suivi-jumppoint-international",
      "title": "Suivi Jumppoint International\u00a0: codes JP, statuts et aide",
      "name": "Jumppoint International Express",
      "color": "#0f4ae1"
    },
    {
      "id": "kuayuan-express",
      "catalog": "kuayuan-express",
      "slug": "suivi-kuayuan-express",
      "title": "Suivi Kuayuan Express\u00a0: codes, relais locaux et aide",
      "name": "Kuayuan Express",
      "color": "#084b86"
    },
    {
      "id": "liccardi",
      "catalog": "liccardi",
      "slug": "suivi-liccardi",
      "title": "Suivi Liccardi Express Courier\u00a0: codes LDV, statuts et aide",
      "name": "Liccardi Express Courier",
      "color": "#014895"
    },
    {
      "id": "maersk-ecommerce",
      "catalog": "maersk-ecommerce",
      "slug": "suivi-maersk-ecommerce",
      "title": "Suivi Maersk eCommerce\u00a0: codes 1M, statuts et aide",
      "name": "Maersk eCommerce",
      "color": "#42b0d5"
    },
    {
      "id": "pdu",
      "catalog": "pdu",
      "slug": "suivi-pdu",
      "title": "Suivi PDU\u00a0: codes Parcel Delivery Union, statuts et aide",
      "name": "PDU",
      "color": "#005cfe"
    },
    {
      "id": "post-haste",
      "catalog": "post-haste",
      "slug": "suivi-post-haste",
      "title": "Suivi Post Haste\u00a0: tickets NZ, codes d’envoi et aide",
      "name": "Post Haste",
      "color": "#24b2d8"
    },
    {
      "id": "redur",
      "catalog": "redur",
      "slug": "suivi-redur",
      "title": "Suivi Redur\u00a0: codes à 14 chiffres, statuts Espagne et aide",
      "name": "Redur",
      "color": "#0c4c49"
    },
    {
      "id": "sengi-express",
      "catalog": "sengi-express",
      "slug": "suivi-sengi-express",
      "title": "Suivi Sengi Express\u00a0: codes Chine–Italie et relais Poste",
      "name": "Sengi Express",
      "color": "#1a44a4"
    },
    {
      "id": "serpost",
      "catalog": "serpost",
      "slug": "suivi-serpost",
      "title": "Suivi Serpost\u00a0: codes S10 du Pérou, douane SUNAT et aide",
      "name": "Serpost",
      "color": "#00308a"
    },
    {
      "id": "skypostal",
      "catalog": "skypostal",
      "slug": "suivi-skypostal",
      "title": "Suivi SkyPostal\u00a0: codes SKAMZUS et SP, statuts et aide",
      "name": "SkyPostal",
      "color": "#088aea"
    },
    {
      "id": "thailand-post",
      "catalog": "thailand-post",
      "slug": "suivi-thailand-post",
      "title": "Suivi Thailand Post\u00a0: codes TH S10, statuts EMS et 1545",
      "name": "Thailand Post",
      "color": "#ef172f"
    },
    {
      "id": "yunda",
      "catalog": "yunda",
      "slug": "suivi-yunda",
      "title": "Suivi Yunda Express\u00a0: bordereaux chinois, statuts et 95546",
      "name": "Yunda Express",
      "color": "#ffce00"
    },
    {
      "id": "onlog-express",
      "catalog": "onlog-express",
      "slug": "suivi-onlog-express",
      "title": "Suivi Onlog Express\u00a0: codes AA, statuts Brésil et aide",
      "name": "Onlog Express",
      "color": "#1a345d"
    },
    {
      "id": "a-duie-pyle",
      "catalog": "a-duie-pyle",
      "slug": "suivi-a-duie-pyle",
      "title": "Suivi A. Duie Pyle\u00a0: numéros PRO, statuts LTL et support",
      "name": "A. Duie Pyle",
      "color": "#f27629"
    },
    {
      "id": "buffalo-ex",
      "catalog": "buffalo-ex",
      "slug": "suivi-buffalo-ex",
      "title": "Suivi BUFFALO\u00a0: codes BUFZA, douane Afrique du Sud et aide",
      "name": "BUFFALO",
      "color": "#bc8c4d"
    },
    {
      "id": "chit-chats",
      "catalog": "chit-chats",
      "slug": "suivi-chit-chats",
      "title": "Suivi Chit Chats\u00a0: codes 10 car., relais USPS/Postes Canada",
      "name": "Chit Chats",
      "color": "#db2b35"
    },
    {
      "id": "dpd-hu",
      "catalog": "dpd-hu",
      "slug": "suivi-dpd-hu",
      "title": "Suivi DPD Hungary\u00a0: codes 14 chiffres, FOXPOST et contact",
      "name": "DPD Hungary",
      "color": "#dc0032"
    },
    {
      "id": "e24metro-ca",
      "catalog": "e24metro-ca",
      "slug": "suivi-e24metro-ca",
      "title": "Suivi E24 (Metro Supply Chain)\u00a0: colis au Canada et aide",
      "name": "E24",
      "color": "#545859"
    },
    {
      "id": "go-express",
      "catalog": "go-express",
      "slug": "suivi-go-express",
      "title": "Suivi GO! Express\u00a0: lettres de voiture 12 chiffres et aide",
      "name": "GO! Express",
      "color": "#b9242b"
    },
    {
      "id": "kuehne-nagel",
      "catalog": "kuehne-nagel",
      "slug": "suivi-kuehne-nagel",
      "title": "Suivi Kuehne+Nagel\u00a0: numéros, statuts, douane et livraison",
      "name": "Kuehne+Nagel",
      "color": "#003369"
    },
    {
      "id": "memphis-courier",
      "catalog": "memphis-courier",
      "slug": "suivi-memphis-courier",
      "title": "Suivi Memphis Courier\u00a0: codes M-MIA, douane ANVISA et aide",
      "name": "Memphis Courier",
      "color": "#1f3c5b"
    },
    {
      "id": "oca",
      "catalog": "oca",
      "slug": "suivi-oca",
      "title": "Suivi OCA\u00a0: numéros 19 chiffres, statuts, agences et aide",
      "name": "OCA",
      "color": "#592c82"
    },
    {
      "id": "pigeonship",
      "catalog": "pigeonship",
      "slug": "suivi-pigeonship",
      "title": "Suivi PigeonShip\u00a0: numéros 18 caractères, statuts et aide",
      "name": "PigeonShip",
      "color": "#f2b02c"
    },
    {
      "id": "seko",
      "catalog": "seko",
      "slug": "suivi-seko-logistics",
      "title": "Suivi SEKO Logistics\u00a0: numéros, OmniParcel et livraison",
      "name": "SEKO Logistics",
      "color": "#ef3e42"
    },
    {
      "id": "bfe-express",
      "catalog": "bfe-express",
      "slug": "suivi-bfe-express",
      "title": "Suivi BFE Express\u00a0: numéros CK et WYB, statuts et aide",
      "name": "BFE Express",
      "color": "#122a88"
    },
    {
      "id": "pasarex",
      "catalog": "pasarex",
      "slug": "suivi-pasarex",
      "title": "Suivi PasarEX\u00a0: codes Amazon AMZPSR, douane et aide",
      "name": "PasarEX",
      "color": "#124381"
    },
    {
      "id": "spee-dee",
      "catalog": "spee-dee",
      "slug": "suivi-spee-dee",
      "title": "Suivi Spee-Dee Delivery\u00a0: numéros SP, États et aide",
      "name": "Spee-Dee",
      "color": "#ff4734"
    },
    {
      "id": "tnt-au",
      "catalog": "tnt-au",
      "slug": "suivi-tnt-australia",
      "title": "Suivi TNT Australia\u00a0: numéros, statuts et aide en Australie",
      "name": "TNT Australia",
      "color": "#ff6600"
    },
    {
      "id": "wedo",
      "catalog": "wedo",
      "slug": "suivi-one-by-allegro-cz",
      "title": "Suivi One by Allegro CZ (WE|DO)\u00a0: codes et livraison",
      "name": "One by Allegro CZ",
      "color": "#006d57"
    },
    {
      "id": "5post",
      "catalog": "5post",
      "slug": "suivi-5post",
      "title": "Suivi 5Post\u00a0: numéros, retrait Pyaterochka et statuts",
      "name": "5Post",
      "color": "#64b441"
    },
    {
      "id": "shipter",
      "catalog": "shipter",
      "slug": "suivi-shipter",
      "title": "Suivi Shipter\u00a0: numéros H3 de Corée du Sud et statuts",
      "name": "Shipter",
      "color": "#005eff"
    }
  ],
  "it": [
    {
      "id": "quickpac",
      "catalog": "quickpac",
      "slug": "quickpac-tracking",
      "title": "Quickpac tracking: segui il pacco e capisci gli stati",
      "name": "Quickpac",
      "color": "#ed1c24"
    },
    {
      "id": "planzer",
      "catalog": "planzer",
      "slug": "planzer-tracking",
      "title": "Planzer tracking: stati del pacco, ritiro e contatti",
      "name": "Planzer",
      "color": "#e30613"
    },
    {
      "id": "colis-prive",
      "catalog": "colis-prive",
      "slug": "tracciamento-colis-prive",
      "title": "Tracciamento Colis Privé: stati francesi e cosa fare",
      "name": "Colis Privé",
      "color": "#aa78ff"
    },
    {
      "id": "brt",
      "catalog": "brt",
      "slug": "brt-tracking",
      "title": "BRT tracking: dov'è il pacco e cosa fare se non arriva",
      "name": "BRT",
      "color": "#dc0032"
    },
    {
      "id": "correos-express",
      "catalog": "correos-express",
      "slug": "correos-express-tracciamento",
      "title": "Tracciamento Correos Express: numero, stati e contatti",
      "name": "Correos Express",
      "color": "#ffcd00"
    },
    {
      "id": "nacex",
      "catalog": "nacex",
      "slug": "nacex-tracciamento",
      "title": "Tracciamento NACEX: numero, stati in spagnolo e contatti",
      "name": "NACEX",
      "color": "#fe5000"
    },
    {
      "id": "yunexpress",
      "catalog": "yunexpress",
      "slug": "yunexpress-tracking",
      "title": "YunExpress tracking: stati, consegna in Italia e contatti",
      "name": "YunExpress",
      "color": "#008284"
    },
    {
      "id": "cainiao",
      "catalog": "aliexpress",
      "slug": "cainiao-tracking",
      "title": "Cainiao tracking: numero, stati e pacco che non arriva",
      "name": "Cainiao",
      "color": "#e62e04"
    },
    {
      "id": "mondial-relay",
      "catalog": "mondial-relay",
      "slug": "tracciamento-mondial-relay",
      "title": "Tracciamento Mondial Relay: stati, Vinted, InPost e ritiro",
      "name": "Mondial Relay",
      "color": "#ef3026"
    },
    {
      "id": "gls-de",
      "catalog": "gls-de",
      "slug": "tracciamento-gls-germania",
      "title": "Tracciamento GLS Germania: Track-ID, stati e contatti",
      "name": "GLS Germany",
      "color": "#ffdd00"
    },
    {
      "id": "dpd-de",
      "catalog": "dpd-de",
      "slug": "tracciamento-dpd-germania",
      "title": "Tracciamento DPD Germania: numero, stati e consegna",
      "name": "DPD Germany",
      "color": "#dc0032"
    },
    {
      "id": "hermes-de",
      "catalog": "hermes-de",
      "slug": "tracking-hermes",
      "title": "Tracking Hermes Germania: stati spiegati, consegna e dogana",
      "name": "Hermes",
      "color": "#0091cd"
    },
    {
      "id": "gls-ch",
      "catalog": "gls-ch",
      "slug": "gls-svizzera-tracking",
      "title": "GLS Svizzera tracking: stati, Posta Svizzera e contatti",
      "name": "GLS Switzerland",
      "color": "#ffdd00"
    },
    {
      "id": "dpd-ch",
      "catalog": "dpd",
      "slug": "dpd-svizzera-tracking",
      "title": "DPD Svizzera tracking: stati, giacenza e contatti",
      "name": "DPD Switzerland",
      "color": "#dc0032"
    },
    {
      "id": "inpost",
      "catalog": "inpost",
      "slug": "inpost-tracking",
      "title": "InPost tracking: codice, stati e ritiro al locker",
      "name": "InPost",
      "color": "#ffcc05"
    },
    {
      "id": "paack",
      "catalog": "paack",
      "slug": "paack-tracking",
      "title": "Paack tracking: tracciare pacco, codice postale e stati",
      "name": "Paack",
      "color": "#5b2dee"
    },
    {
      "id": "evri",
      "catalog": "evri-uk",
      "slug": "tracciamento-evri",
      "title": "Tracciamento Evri: numero, stati, dogana e consegna",
      "name": "Evri",
      "color": "#00014d"
    },
    {
      "id": "chronopost",
      "catalog": "chronopost",
      "slug": "chronopost-tracking",
      "title": "Chronopost tracking: pacco dalla Francia, stati e ritiro",
      "name": "Chronopost",
      "color": "#0056a7"
    },
    {
      "id": "relais-colis",
      "catalog": "relais-colis",
      "slug": "tracciamento-relais-colis",
      "title": "Tracciamento Relais Colis: stati, ritiro e contatti",
      "name": "Relais Colis",
      "color": "#7bb928"
    },
    {
      "id": "poste-italiane",
      "catalog": "poste-italiane",
      "slug": "poste-italiane-tracking",
      "title": "Poste Italiane tracking: codici 1UW e 3UW, stati e giacenza",
      "name": "Poste Italiane",
      "color": "#eedc00"
    },
    {
      "id": "seur",
      "catalog": "seur",
      "slug": "tracciamento-seur",
      "title": "Tracciamento SEUR: numero, stati spagnoli e punti Pickup",
      "name": "SEUR",
      "color": "#0063b3"
    },
    {
      "id": "mrw",
      "catalog": "mrw",
      "slug": "tracciamento-mrw",
      "title": "Tracciamento MRW: numero a 12 cifre, stati, Point e contatti",
      "name": "MRW",
      "color": "#19133b"
    },
    {
      "id": "ctt",
      "catalog": "ctt",
      "slug": "ctt-portugal-tracking",
      "title": "Tracking CTT Portugal: codici, stati, ritiro e dogana",
      "name": "CTT",
      "color": "#df0024"
    },
    {
      "id": "packeta",
      "catalog": "packeta",
      "slug": "packeta-tracking",
      "title": "Packeta tracking: codice Z, stati, ritiro e contatti",
      "name": "Packeta",
      "color": "#ba1b02"
    },
    {
      "id": "dpd-pl",
      "catalog": "dpd-pl",
      "slug": "dpd-poland-tracking",
      "title": "Tracking DPD Polonia (DPD Polska): stati e punti Pickup",
      "name": "DPD Poland",
      "color": "#dc0032"
    },
    {
      "id": "4px",
      "catalog": "four-px",
      "slug": "4px-traccia-pacco",
      "title": "4PX traccia pacco: numero, stati e chi consegna",
      "name": "4PX",
      "color": "#005add"
    },
    {
      "id": "swiss-post",
      "catalog": "swiss-post",
      "slug": "swiss-post-tracking-posta-svizzera",
      "title": "Swiss Post tracking: Posta Svizzera, stati e ritiro",
      "name": "Swiss Post",
      "color": "#ffcc00"
    },
    {
      "id": "ctt-express",
      "catalog": "ctt-express",
      "slug": "ctt-express-tracking",
      "title": "Tracking CTT Express: stati in italiano, punti e contatti",
      "name": "CTT Express",
      "color": "#df0024"
    },
    {
      "id": "dpd-fr",
      "catalog": "dpd-fr",
      "slug": "tracciamento-dpd-fr",
      "title": "Tracciamento DPD France: codici, stati e punti Pickup",
      "name": "DPD France",
      "color": "#dc0032"
    },
    {
      "id": "gls-fr",
      "catalog": "gls-fr",
      "slug": "tracciamento-gls-france",
      "title": "Tracciamento GLS France: stati, Relais GLS e contatti",
      "name": "GLS France",
      "color": "#ffdd00"
    },
    {
      "id": "gls-it",
      "catalog": "gls-it",
      "slug": "gls-italy-tracking",
      "title": "GLS Italy tracking: pacco, stati, giacenza e contatti",
      "name": "GLS Italy",
      "color": "#ffdd00"
    },
    {
      "id": "poczta-polska",
      "catalog": "poczta-polska",
      "slug": "tracciamento-poczta-polska",
      "title": "Tracciamento Poczta Polska: numeri, stati e ritiri",
      "name": "Poczta Polska",
      "color": "#c43c2b"
    },
    {
      "id": "orlen-paczka",
      "catalog": "orlen-paczka",
      "slug": "tracciamento-orlen-paczka",
      "title": "Tracciamento ORLEN Paczka: numeri, stati e ritiri",
      "name": "ORLEN Paczka",
      "color": "#db3830"
    },
    {
      "id": "austrian-post",
      "catalog": "austrian-post",
      "slug": "tracciamento-austrian-post",
      "title": "Tracciamento Austrian Post: numeri, stati e ritiro",
      "name": "Austrian Post",
      "color": "#ffdc00"
    },
    {
      "id": "tipsa",
      "catalog": "tipsa",
      "slug": "tracking-tipsa",
      "title": "Tracking TIPSA: codice a 22 cifre, stati e consegne",
      "name": "TIPSA",
      "color": "#001e62"
    },
    {
      "id": "yanwen",
      "catalog": "yanwen",
      "slug": "yanwen-tracking",
      "title": "Yanwen tracking: numero, stati e chi consegna in Italia",
      "name": "Yanwen",
      "color": "#8cbe1e"
    },
    {
      "id": "an-post",
      "catalog": "an-post",
      "slug": "tracciamento-an-post",
      "title": "Tracciamento An Post: stati, dogana e contatti Irlanda",
      "name": "An Post",
      "color": "#00a76a"
    },
    {
      "id": "vinted-go",
      "catalog": "vinted-go",
      "slug": "vinted-go-tracking",
      "title": "Vinted Go tracking: stati, ritiro in Italia e assistenza",
      "name": "Vinted Go",
      "color": "#004654"
    },
    {
      "id": "amazon-logistics",
      "catalog": "amazon-logistics",
      "slug": "amazon-logistics-tracking",
      "title": "Amazon Logistics tracking: dove seguire il pacco e contatti",
      "name": "Amazon Logistics",
      "color": "#ff9900"
    },
    {
      "id": "dpd-uk",
      "catalog": "dpd-uk",
      "slug": "tracciamento-dpd-uk",
      "title": "Tracciamento DPD UK: numero a 14 cifre, stati e contatti",
      "name": "DPD UK",
      "color": "#dc0032"
    },
    {
      "id": "parcelforce",
      "catalog": "parcelforce",
      "slug": "tracciamento-parcelforce-worldwide",
      "title": "Tracciamento Parcelforce Worldwide: stati, dogana e aiuto",
      "name": "Parcelforce Worldwide",
      "color": "#9d0f11"
    },
    {
      "id": "bpost",
      "catalog": "bpost",
      "slug": "bpost-tracciamento",
      "title": "Tracciamento bpost: codice a barre, stati e punti di ritiro",
      "name": "bpost",
      "color": "#ef2637"
    },
    {
      "id": "dpd-ie",
      "catalog": "dpd-ie",
      "slug": "tracking-dpd-ireland",
      "title": "Tracking DPD Ireland: stati, punti Pickup e contatti",
      "name": "DPD Ireland",
      "color": "#dc0032"
    },
    {
      "id": "gls-es",
      "catalog": "gls-es",
      "slug": "tracciamento-gls-spagna",
      "title": "Tracciamento GLS Spagna: stati, Parcel Shop e contatti",
      "name": "GLS Spain",
      "color": "#ffdd00"
    },
    {
      "id": "dhl-ecommerce-es",
      "catalog": "dhl-ecommerce-es",
      "slug": "tracciamento-dhl-ecommerce-iberia",
      "title": "Tracciamento DHL eCommerce Iberia: stati e ServicePoint",
      "name": "DHL eCommerce Iberia",
      "color": "#ffcc00"
    },
    {
      "id": "dhl-ecommerce-pl",
      "catalog": "dhl-ecommerce-pl",
      "slug": "tracciamento-dhl-ecommerce-polonia",
      "title": "Tracciamento DHL eCommerce Polonia: stati, ritiro e aiuto",
      "name": "DHL eCommerce Poland",
      "color": "#ffcc00"
    },
    {
      "id": "dhl-ecommerce-uk",
      "catalog": "dhl-ecommerce-uk",
      "slug": "tracciamento-dhl-ecommerce-uk",
      "title": "Tracciamento DHL eCommerce UK: stati e contatti",
      "name": "DHL eCommerce UK",
      "color": "#ffcc00"
    },
    {
      "id": "gofo-fr",
      "catalog": "gofo-fr",
      "slug": "gofo-france-tracciamento",
      "title": "Tracciamento GOFO France: codici GFFR, stati e assistenza",
      "name": "GOFO France",
      "color": "#fc4c02"
    },
    {
      "id": "gofo-it",
      "catalog": "gofo-it",
      "slug": "gofo-italy-tracking",
      "title": "GOFO Italy tracking: codici GFIT, stati e reclami",
      "name": "GOFO Italy",
      "color": "#fc4c02"
    },
    {
      "id": "speedpak",
      "catalog": "speedpak",
      "slug": "speedpak-tracking",
      "title": "SpeedPAK tracking: pacchi eBay, stati e chi consegna",
      "name": "SpeedPAK",
      "color": "#ee7532"
    },
    {
      "id": "asendia",
      "catalog": "asendia",
      "slug": "asendia-tracking",
      "title": "Asendia tracking: stati, consegna in Italia e contatti",
      "name": "Asendia",
      "color": "#005d69"
    },
    {
      "id": "colisweb",
      "catalog": "colisweb",
      "slug": "tracking-colisweb",
      "title": "Tracking Colisweb: messaggi in francese e fasce orarie",
      "name": "Colisweb",
      "color": "#15cbe3"
    },
    {
      "id": "ciblex",
      "catalog": "ciblex",
      "slug": "tracciamento-ciblex",
      "title": "Tracciamento Ciblex: numeri, stati in francese e contatti",
      "name": "Ciblex",
      "color": "#0160ab"
    },
    {
      "id": "hermes-einrichtungs-service",
      "catalog": "hermes",
      "slug": "tracciamento-hermes-einrichtungs-service",
      "title": "Tracciamento Hermes Einrichtungs-Service: mobili e cita",
      "name": "Hermes Einrichtungs-Service",
      "color": "#0091cd"
    },
    {
      "id": "landmark-global",
      "catalog": "landmark-global",
      "slug": "landmark-global-tracking",
      "title": "Landmark Global tracking: stati, consegna, dogana e aiuto",
      "name": "Landmark Global",
      "color": "#e62337"
    },
    {
      "id": "dhl-ecommerce",
      "catalog": "dhl-ecommerce",
      "slug": "tracciamento-dhl-ecommerce",
      "title": "Tracciamento DHL eCommerce: codici GM, stati e contatti",
      "name": "DHL eCommerce",
      "color": "#ffcc00"
    },
    {
      "id": "allegro-one",
      "catalog": "allegro-one",
      "slug": "tracciamento-allegro-one",
      "title": "Tracciamento Allegro One: One Box, One Punkt e One Kurier",
      "name": "Allegro One",
      "color": "#006d57"
    },
    {
      "id": "cacesa",
      "catalog": "cacesa",
      "slug": "tracciamento-cacesa",
      "title": "Tracciamento CACESA: dogana, stati e chi consegna",
      "name": "CACESA",
      "color": "#C9032D"
    },
    {
      "id": "sunyou",
      "catalog": "sunyou",
      "slug": "sunyou-tracking",
      "title": "SunYou tracking: numero SY, stati e chi consegna in Italia",
      "name": "SunYou",
      "color": "#02a232"
    },
    {
      "id": "amazon-shipping",
      "catalog": "amazon-shipping",
      "slug": "amazon-shipping-tracking",
      "title": "Amazon Shipping tracking: dov'è il pacco e chi contattare",
      "name": "Amazon Shipping",
      "color": "#ff9900"
    },
    {
      "id": "postnord",
      "catalog": "postnord",
      "slug": "tracciamento-postnord",
      "title": "Tracciamento PostNord: numeri, stati e ritiro",
      "name": "PostNord",
      "color": "#00a0d6"
    },
    {
      "id": "bring",
      "catalog": "bring-posten",
      "slug": "tracciamento-bring",
      "title": "Tracciamento Bring (Posten): codici, stati e ritiro",
      "name": "Bring",
      "color": "#56b529"
    },
    {
      "id": "posti",
      "catalog": "posti",
      "slug": "tracciamento-posti",
      "title": "Tracciamento Posti: codice JJFI, stati, locker e contatti",
      "name": "Posti",
      "color": "#ff8000"
    },
    {
      "id": "omniva",
      "catalog": "omniva",
      "slug": "tracking-omniva",
      "title": "Tracking Omniva: codici, stati e armadietti automatici",
      "name": "Omniva",
      "color": "#f86300"
    },
    {
      "id": "ppl",
      "catalog": "ppl",
      "slug": "tracciamento-ppl",
      "title": "Tracciamento PPL: codice a 11 cifre, stati e PPL box",
      "name": "PPL",
      "color": "#004b93"
    },
    {
      "id": "box-now",
      "catalog": "box-now",
      "slug": "tracciamento-box-now",
      "title": "Tracciamento BOX NOW: locker, codici PIN e stati",
      "name": "BOX NOW",
      "color": "#44d62c"
    },
    {
      "id": "dhl-ecommerce-nl",
      "catalog": "dhl-ecommerce-nl",
      "slug": "tracciamento-dhl-ecommerce-netherlands",
      "title": "Tracciamento DHL eCommerce Paesi Bassi: stati e contatti",
      "name": "DHL eCommerce Netherlands",
      "color": "#ffcc00"
    },
    {
      "id": "heppner",
      "catalog": "heppner",
      "slug": "tracciamento-heppner",
      "title": "Tracciamento Heppner: numero, CAP e fasce di consegna",
      "name": "Heppner",
      "color": "#005baa"
    },
    {
      "id": "c-chez-vous",
      "catalog": "c-chez-vous",
      "slug": "tracciamento-c-chez-vous",
      "title": "Tracciamento C Chez Vous: numero d’ordine, stati e contatti",
      "name": "C chez vous",
      "color": "#e50046"
    },
    {
      "id": "geodis",
      "catalog": "geodis",
      "slug": "geodis-tracciamento",
      "title": "Tracciamento GEODIS: numero 1G, stati e contatti",
      "name": "GEODIS",
      "color": "#3100e6"
    },
    {
      "id": "delivengo",
      "catalog": "delivengo",
      "slug": "delivengo-tracking",
      "title": "Tracking Delivengo: segui un pacco La Poste dalla Francia",
      "name": "Delivengo",
      "color": "#ffcf00"
    },
    {
      "id": "ecoscooting",
      "catalog": "ecoscooting",
      "slug": "ecoscooting-tracking",
      "title": "Ecoscooting tracking: stati, punti di ritiro e contatti",
      "name": "Ecoscooting",
      "color": "#3fa7ff"
    },
    {
      "id": "swiss-post-cargo",
      "catalog": "swiss-post-cargo",
      "slug": "swiss-post-cargo-tracking",
      "title": "Swiss Post Cargo tracking: stati, consegna e contatti",
      "name": "Swiss Post Cargo",
      "color": "#ffcc00"
    },
    {
      "id": "dachser",
      "catalog": "dachser",
      "slug": "tracciamento-dachser",
      "title": "Tracciamento Dachser in Spagna e Portogallo: link e stati",
      "name": "Dachser",
      "color": "#005ca9"
    },
    {
      "id": "meest",
      "catalog": "meest",
      "slug": "tracciamento-meest",
      "title": "Tracciamento Meest: numeri, stati e contatti",
      "name": "Meest",
      "color": "#0061af"
    },
    {
      "id": "nova-poshta",
      "catalog": "nova-poshta",
      "slug": "tracciamento-nova-poshta",
      "title": "Tracciamento Nova Poshta: numeri, stati e filiali",
      "name": "Nova Poshta",
      "color": "#e30613"
    },
    {
      "id": "sameday",
      "catalog": "sameday",
      "slug": "tracciamento-sameday",
      "title": "Tracciamento Sameday: AWB, easybox e stati spiegati",
      "name": "Sameday",
      "color": "#e92427"
    },
    {
      "id": "speedy",
      "catalog": "speedy",
      "slug": "tracciamento-speedy",
      "title": "Tracciamento Speedy: codice, stati, uffici e locker",
      "name": "Speedy",
      "color": "#dc0032"
    },
    {
      "id": "cargus",
      "catalog": "cargus",
      "slug": "tracciamento-cargus",
      "title": "Tracciamento Cargus: numero AWB, stati e SHIP & GO",
      "name": "Cargus",
      "color": "#f68121"
    },
    {
      "id": "fan-courier",
      "catalog": "fan-courier",
      "slug": "tracciamento-fan-courier",
      "title": "Tracciamento FAN Courier: codici AWB, stati e FANbox",
      "name": "FAN Courier",
      "color": "#0f2345"
    },
    {
      "id": "econt",
      "catalog": "econt",
      "slug": "tracciamento-econt",
      "title": "Tracciamento Econt: numero di spedizione, stati e uffici",
      "name": "Econt",
      "color": "#234182"
    },
    {
      "id": "ceska-posta",
      "catalog": "ceska-posta",
      "slug": "tracciamento-ceska-posta",
      "title": "Tracciamento Česká pošta (Poste Ceche) e Balíkovna",
      "name": "Česká pošta",
      "color": "#002776"
    },
    {
      "id": "express-one",
      "catalog": "express-one",
      "slug": "tracciamento-express-one",
      "title": "Tracciamento Express One: stati, punti e contatti",
      "name": "Express One",
      "color": "#ec6500"
    },
    {
      "id": "foxpost",
      "catalog": "foxpost",
      "slug": "tracking-foxpost",
      "title": "Tracking Foxpost: stati, locker e contatti",
      "name": "Foxpost",
      "color": "#b70100"
    },
    {
      "id": "slovak-parcel-service",
      "catalog": "slovak-parcel-service",
      "slug": "tracciamento-slovak-parcel-service",
      "title": "Tracciamento Slovak Parcel Service (SPS): stati e aiuto",
      "name": "Slovak Parcel Service",
      "color": "#f58220"
    },
    {
      "id": "slovenska-posta",
      "catalog": "slovenska-posta",
      "slug": "tracciamento-slovenska-posta",
      "title": "Tracciamento Slovenská pošta: numeri, stati e BalíkoBOX",
      "name": "Slovenská pošta",
      "color": "#f9bb00"
    },
    {
      "id": "posta-slovenije",
      "catalog": "posta-slovenije",
      "slug": "tracciamento-posta-slovenije",
      "title": "Tracciamento Pošta Slovenije: stati, ritiro e contatti",
      "name": "Pošta Slovenije",
      "color": "#ffc600"
    },
    {
      "id": "hrvatska-posta",
      "catalog": "hrvatska-posta",
      "slug": "tracciamento-hrvatska-posta",
      "title": "Tracciamento Hrvatska pošta: stati, locker e contatti",
      "name": "Hrvatska pošta",
      "color": "#ffd100"
    },
    {
      "id": "posta-srbije",
      "catalog": "posta-srbije",
      "slug": "tracciamento-posta-srbije",
      "title": "Tracciamento Pošta Srbije: poste serbe, stati e contatti",
      "name": "Pošta Srbije",
      "color": "#fbba00"
    },
    {
      "id": "venipak",
      "catalog": "venipak",
      "slug": "tracciamento-venipak",
      "title": "Tracciamento Venipak: stati, locker e consegne mancate",
      "name": "Venipak",
      "color": "#9339f2"
    },
    {
      "id": "lietuvos-pastas",
      "catalog": "lietuvos-pastas",
      "slug": "tracciamento-lietuvos-pastas",
      "title": "Tracciamento Lietuvos paštas (Poste Lituane) e LP Express",
      "name": "Lietuvos paštas",
      "color": "#fedd00"
    },
    {
      "id": "latvijas-pasts",
      "catalog": "latvijas-pasts",
      "slug": "tracciamento-latvijas-pasts",
      "title": "Tracciamento Latvijas Pasts: numeri, stati e locker",
      "name": "Latvijas Pasts",
      "color": "#0c72ba"
    },
    {
      "id": "dao",
      "catalog": "dao",
      "slug": "tracciamento-dao",
      "title": "Tracciamento DAO: stati, daoSHOP e consegne mancate",
      "name": "DAO",
      "color": "#e30613"
    },
    {
      "id": "helthjem",
      "catalog": "helthjem",
      "slug": "tracciamento-helthjem",
      "title": "Tracciamento Helthjem: stati, ritiro e contatti",
      "name": "Helthjem",
      "color": "#ffb202"
    },
    {
      "id": "matkahuolto",
      "catalog": "matkahuolto",
      "slug": "tracciamento-matkahuolto",
      "title": "Tracciamento Matkahuolto: stati, ritiro e contatti",
      "name": "Matkahuolto",
      "color": "#001e60"
    },
    {
      "id": "ukrposhta",
      "catalog": "ukrposhta",
      "slug": "tracciamento-ukrposhta",
      "title": "Tracciamento Ukrposhta: numeri, stati e ritiro in ufficio",
      "name": "Ukrposhta",
      "color": "#ffbe0c"
    },
    {
      "id": "acs-courier",
      "catalog": "acs-courier",
      "slug": "tracciamento-acs-courier",
      "title": "Tracciamento ACS Courier: numeri, stati e contatti",
      "name": "ACS Courier",
      "color": "#e42229"
    },
    {
      "id": "elta",
      "catalog": "elta",
      "slug": "tracciamento-elta-hellenic-post",
      "title": "Tracciamento ELTA Hellenic Post: stati, ritiro e contatti",
      "name": "ELTA Hellenic Post",
      "color": "#0063a9"
    },
    {
      "id": "geniki-taxydromiki",
      "catalog": "geniki-taxydromiki",
      "slug": "tracciamento-geniki-taxydromiki",
      "title": "Tracciamento Geniki Taxydromiki: stati, ritiro e contatti",
      "name": "Geniki Taxydromiki",
      "color": "#004fa0"
    },
    {
      "id": "cyprus-post",
      "catalog": "cyprus-post",
      "slug": "tracciamento-cyprus-post",
      "title": "Tracciamento Cyprus Post: numeri, stati e dogana",
      "name": "Cyprus Post",
      "color": "#e4a50e"
    },
    {
      "id": "post-luxembourg",
      "catalog": "post-luxembourg",
      "slug": "tracciamento-post-luxembourg",
      "title": "Tracciamento POST Luxembourg: numeri, stati e contatti",
      "name": "POST Luxembourg",
      "color": "#008736"
    },
    {
      "id": "maltapost",
      "catalog": "maltapost",
      "slug": "tracciamento-maltapost",
      "title": "Tracciamento MaltaPost: numeri, stati e ritiro",
      "name": "MaltaPost",
      "color": "#d43b2c"
    },
    {
      "id": "whistl",
      "catalog": "whistl",
      "slug": "tracciamento-whistl",
      "title": "Tracciamento Whistl: chi consegna il pacco e cosa fare",
      "name": "Whistl",
      "color": "#fd4f00"
    },
    {
      "id": "xdp",
      "catalog": "xdp",
      "slug": "tracciamento-xdp",
      "title": "Tracciamento XDP: codici di spedizione, stati e consegna",
      "name": "XDP",
      "color": "#53b6ed"
    },
    {
      "id": "apc-overnight",
      "catalog": "apc-overnight",
      "slug": "tracciamento-apc-overnight",
      "title": "Tracciamento APC Overnight: numeri, stati e consegne",
      "name": "APC Overnight",
      "color": "#243269"
    },
    {
      "id": "ups",
      "catalog": "ups",
      "slug": "ups-tracking",
      "title": "UPS tracking: numero, stati del pacco e consegna",
      "name": "UPS",
      "color": "#351c15"
    },
    {
      "id": "fedex",
      "catalog": "fedex",
      "slug": "fedex-tracking",
      "title": "FedEx tracking: numero, stati e consegna del pacco",
      "name": "FedEx",
      "color": "#4d148c"
    },
    {
      "id": "dhl-express",
      "catalog": "dhl-express",
      "slug": "dhl-express-tracking",
      "title": "DHL Express tracking: lettera di vettura, stati e dogana",
      "name": "DHL Express",
      "color": "#ffcc00"
    },
    {
      "id": "dhl",
      "catalog": "dhl",
      "slug": "dhl-paket-tracking",
      "title": "DHL Paket tracking: numero, stati e consegna in Italia",
      "name": "DHL Paket",
      "color": "#ffcc00"
    },
    {
      "id": "la-poste",
      "catalog": "la-poste",
      "slug": "tracciamento-colissimo-la-poste",
      "title": "Tracciamento Colissimo e La Poste: stati, consegna e FAQ",
      "name": "La Poste / Colissimo",
      "color": "#ffcf00"
    },
    {
      "id": "correos-spain",
      "catalog": "correos-spain",
      "slug": "correos-spagna-tracciamento",
      "title": "Correos tracciamento: seguire un pacco dalla Spagna",
      "name": "Correos",
      "color": "#ffcd00"
    },
    {
      "id": "royal-mail",
      "catalog": "royal-mail",
      "slug": "tracciamento-royal-mail",
      "title": "Tracciamento Royal Mail: numero, stati, dogana e Poste",
      "name": "Royal Mail",
      "color": "#da202a"
    },
    {
      "id": "usps",
      "catalog": "usps",
      "slug": "tracciamento-usps",
      "title": "Tracciamento USPS: numero, stati e pacchi dagli Stati Uniti",
      "name": "USPS",
      "color": "#004b87"
    },
    {
      "id": "canada-post",
      "catalog": "canada-post",
      "slug": "canada-post-tracking",
      "title": "Tracking Canada Post: codici, stati, avviso e dogana",
      "name": "Canada Post",
      "color": "#e4002b"
    },
    {
      "id": "purolator",
      "catalog": "purolator",
      "slug": "tracciamento-purolator",
      "title": "Tracciamento Purolator: PIN, stati e ritiri",
      "name": "Purolator",
      "color": "#da291c"
    },
    {
      "id": "intelcom",
      "catalog": "intelcom",
      "slug": "tracciamento-intelcom-dragonfly",
      "title": "Tracciamento Intelcom / Dragonfly: numeri e contatti",
      "name": "Intelcom / Dragonfly",
      "color": "#13a58f"
    },
    {
      "id": "canpar",
      "catalog": "canpar",
      "slug": "tracciamento-canpar",
      "title": "Tracciamento Canpar: numeri, stati e mancate consegne",
      "name": "Canpar",
      "color": "#003b70"
    },
    {
      "id": "uniuni",
      "catalog": "uniuni",
      "slug": "uniuni-tracking",
      "title": "Tracking UniUni: codici, stati, consegna, foto e contatti",
      "name": "UniUni",
      "color": "#f68841"
    },
    {
      "id": "ontrac",
      "catalog": "ontrac",
      "slug": "tracciamento-ontrac",
      "title": "Tracciamento OnTrac: codici, stati e mancate consegne",
      "name": "OnTrac",
      "color": "#cc2027"
    },
    {
      "id": "lso",
      "catalog": "lso",
      "slug": "tracciamento-lso",
      "title": "Tracciamento LSO: numeri airbill, stati e consegne",
      "name": "LSO",
      "color": "#0047ba"
    },
    {
      "id": "gofo",
      "catalog": "gofo",
      "slug": "gofo-express-tracciamento",
      "title": "Tracciamento GOFO Express: numero GFUS, stati e aiuto",
      "name": "GOFO Express",
      "color": "#fc4c02"
    },
    {
      "id": "speedx",
      "catalog": "speedx",
      "slug": "tracciamento-speedx",
      "title": "Tracciamento SpeedX: numeri SPX, stati e assistenza",
      "name": "SpeedX",
      "color": "#1aa3ff"
    },
    {
      "id": "australia-post",
      "catalog": "australia-post",
      "slug": "tracciamento-australia-post",
      "title": "Tracciamento Australia Post: codici, stati e locker",
      "name": "Australia Post",
      "color": "#dc1928"
    },
    {
      "id": "aramex-au",
      "catalog": "aramex-au",
      "slug": "tracciamento-aramex-australia",
      "title": "Tracciamento Aramex Australia: numeri, stati e contatti",
      "name": "Aramex Australia",
      "color": "#dc291e"
    },
    {
      "id": "couriersplease",
      "catalog": "couriersplease",
      "slug": "tracciamento-couriersplease",
      "title": "Tracciamento CouriersPlease: numeri, stati e aiuto",
      "name": "CouriersPlease",
      "color": "#c21e36"
    },
    {
      "id": "nz-post",
      "catalog": "nz-post",
      "slug": "tracciamento-nz-post",
      "title": "Tracciamento NZ Post: numeri, stati e mancate consegne",
      "name": "NZ Post",
      "color": "#d8291c"
    },
    {
      "id": "aramex-nz",
      "catalog": "aramex-nz",
      "slug": "tracciamento-aramex-nuova-zelanda",
      "title": "Tracciamento Aramex Nuova Zelanda: numeri e stati",
      "name": "Aramex New Zealand",
      "color": "#dc291e"
    },
    {
      "id": "magyar-posta",
      "catalog": "magyar-posta",
      "slug": "tracking-magyar-posta",
      "title": "Tracking Magyar Posta: stati ungheresi, PostaPont e dogana",
      "name": "Magyar Posta",
      "color": "#346631"
    },
    {
      "id": "shiprocket",
      "catalog": "shiprocket",
      "slug": "tracciamento-shiprocket",
      "title": "Tracciamento Shiprocket: codici AWB, stati e RTO",
      "name": "Shiprocket",
      "color": "#735ae5"
    },
    {
      "id": "first-line",
      "catalog": "first-line",
      "slug": "tracciamento-first-line",
      "title": "Tracciamento FIRST LINE: codici 1ST, stati e consegna",
      "name": "FIRST LINE",
      "color": "#55207a"
    },
    {
      "id": "j-and-t-de",
      "catalog": "j-and-t-de",
      "slug": "jt-express-germania-tracking",
      "title": "Tracking J&T Express in Germania: numero JTDE e stati",
      "name": "J&T Express Germany",
      "color": "#e60000"
    },
    {
      "id": "kun-express",
      "catalog": "kun-express",
      "slug": "tracking-kun-express",
      "title": "Tracking Kun Express: codici KUN, stati e USPS",
      "name": "Kun Express",
      "color": "#0761ff"
    },
    {
      "id": "juxi-express",
      "catalog": "juxi-express",
      "slug": "tracciamento-juxi-express",
      "title": "Tracciamento Juxi Express: numero JXC, consegna, dogana",
      "name": "Juxi Express",
      "color": "#e2393c"
    },
    {
      "id": "lingdong",
      "catalog": "lingdong",
      "slug": "tracciamento-lingdong",
      "title": "Tracciamento Lingdong International: numero SZLD e stati",
      "name": "Lingdong International",
      "color": "#0171bb"
    },
    {
      "id": "j-and-t-au",
      "catalog": "j-and-t-au",
      "slug": "tracciamento-jt-express-australia",
      "title": "Tracciamento J&T Express Australia: codici JTAU e stati",
      "name": "J&T Express Australia",
      "color": "#e60000"
    },
    {
      "id": "china-post",
      "catalog": "china-post",
      "slug": "china-post-tracking",
      "title": "Tracking China Post: codice, stati in cinese e consegna",
      "name": "China Post",
      "color": "#006d46"
    },
    {
      "id": "fedex-pl",
      "catalog": "fedex-pl",
      "slug": "tracciamento-fedex-polonia",
      "title": "Tracciamento FedEx Polonia: codici a 13 cifre e stati",
      "name": "FedEx Poland",
      "color": "#4d148c"
    },
    {
      "id": "techlink",
      "catalog": "techlink",
      "slug": "techlink-tracking",
      "title": "Techlink tracking: numero ZHPHD…ML, stati e chi consegna",
      "name": "Techlink",
      "color": "#e73547"
    },
    {
      "id": "z-leading",
      "catalog": "z-leading",
      "slug": "tracciamento-z-leading",
      "title": "Tracciamento Z-Leading (Zhongling): numeri, consegna, aiuto",
      "name": "Z-Leading",
      "color": "#005ab0"
    },
    {
      "id": "imile",
      "catalog": "imile",
      "slug": "imile-tracking",
      "title": "Tracking iMile: codici, stati e consegna in Italia",
      "name": "iMile",
      "color": "#0032a0"
    },
    {
      "id": "postnl",
      "catalog": "spring-gds",
      "slug": "postnl-tracking",
      "title": "Tracking PostNL: codici, stati e consegna in Italia",
      "name": "PostNL",
      "color": "#ef7d00"
    },
    {
      "id": "dhlink",
      "catalog": "dhlink",
      "slug": "dhlink-tracking",
      "title": "Tracking DHLink: codice DHgate, stati e chi consegna",
      "name": "DHLink",
      "color": "#143167"
    },
    {
      "id": "dpd-pt",
      "catalog": "dpd-pt",
      "slug": "tracciamento-dpd-portugal",
      "title": "Tracciamento DPD Portugal: stati, punti Pickup e contatti",
      "name": "DPD Portugal",
      "color": "#dc0032"
    },
    {
      "id": "tnt-it",
      "catalog": "tnt-it",
      "slug": "tnt-italy-tracking",
      "title": "TNT tracking: lettera di vettura, stati e giacenza",
      "name": "TNT Italy",
      "color": "#ff6600"
    },
    {
      "id": "tnt",
      "catalog": "tnt",
      "slug": "tracciamento-tnt",
      "title": "Tracciamento TNT: Connote a 9 cifre, stati e consegna",
      "name": "TNT",
      "color": "#ff6600"
    },
    {
      "id": "sf-express",
      "catalog": "sf-express",
      "slug": "tracciamento-sf-express",
      "title": "Tracciamento SF Express: numero, stati, dogana e consegna",
      "name": "SF Express",
      "color": "#dc1e32"
    },
    {
      "id": "straightship",
      "catalog": "straightship",
      "slug": "tracciamento-straightship",
      "title": "Tracciamento Straightship: codici STRTD, stati e consegna",
      "name": "Straightship",
      "color": "#6aae66"
    },
    {
      "id": "correios-br",
      "catalog": "correios-br",
      "slug": "tracciamento-correios-brasile",
      "title": "Tracciamento Correios Brasile: codici, stati e dogana",
      "name": "Correios Brazil",
      "color": "#ffd500"
    },
    {
      "id": "yto",
      "catalog": "yto",
      "slug": "tracciamento-yto-express",
      "title": "Tracciamento YTO Express: numero YT, stati e consegna",
      "name": "YTO Express",
      "color": "#4d148c"
    },
    {
      "id": "spx-br",
      "catalog": "spx-br",
      "slug": "tracciamento-spx-express-brasile",
      "title": "Tracciamento SPX Express Brasile: ordini Shopee e stati",
      "name": "SPX Express Brazil",
      "color": "#ee4d2d"
    },
    {
      "id": "eushipments",
      "catalog": "eushipments",
      "slug": "tracciamento-eushipments",
      "title": "Tracciamento euShipments: stati e corriere locale",
      "name": "euShipments",
      "color": "#0a8fcf"
    },
    {
      "id": "mhi",
      "catalog": "mhi",
      "slug": "tracciamento-mhi",
      "title": "Tracciamento MHI: stati Trackmail e chi consegna",
      "name": "MHI",
      "color": "#00075c"
    },
    {
      "id": "pengyou",
      "catalog": "pengyou",
      "slug": "tracciamento-pengyou",
      "title": "Tracciamento Pengyou: numeri PY, stati e chi sentire",
      "name": "Pengyou",
      "color": "#043a82"
    },
    {
      "id": "linhan",
      "catalog": "linhan",
      "slug": "tracciamento-linhan",
      "title": "Tracciamento LinHan International: codici LHGJ e stati",
      "name": "LinHan International",
      "color": "#eb5c20"
    },
    {
      "id": "mtscn",
      "catalog": "mtscn",
      "slug": "meiteshun-tracking",
      "title": "Tracking Meiteshun: numero AMTS…FS, stati e chi contattare",
      "name": "Meiteshun Logistics",
      "color": "#f89f3b"
    },
    {
      "id": "cttyex",
      "catalog": "cttyex",
      "slug": "tracciamento-cttyex",
      "title": "Tracciamento CTTYEX: codici CTTY…CN, stati e ora Pechino",
      "name": "CTTYEX",
      "color": "#0068AC"
    },
    {
      "id": "speedaf",
      "catalog": "speedaf",
      "slug": "tracciamento-speedaf",
      "title": "Tracciamento Speedaf Express: numero, stati e contatti",
      "name": "Speedaf",
      "color": "#ff8300"
    },
    {
      "id": "yilong-logistics",
      "catalog": "yilong-logistics",
      "slug": "tracciamento-yilong-logistics",
      "title": "Tracciamento Yilong Logistics: codici YIL…WL e stati",
      "name": "Yilong Logistics",
      "color": "#ec0307"
    },
    {
      "id": "quick-rabbit",
      "catalog": "quick-rabbit",
      "slug": "tracciamento-quick-rabbit",
      "title": "Tracciamento Quick Rabbit: codici PKG, stati e Citibox",
      "name": "Quick Rabbit",
      "color": "#f6851f"
    },
    {
      "id": "huaya-express",
      "catalog": "huaya-express",
      "slug": "tracciamento-huaya-express",
      "title": "Tracciamento Huaya Express: codici HYYT…GZ e stati",
      "name": "Huaya Express",
      "color": "#e60013"
    },
    {
      "id": "gofo-ca",
      "catalog": "gofo-ca",
      "slug": "tracciamento-gofo-ca",
      "title": "Tracciamento GOFO Canada: codici GFCA, stati e ritardi",
      "name": "GOFO Canada",
      "color": "#fc4c02"
    },
    {
      "id": "metropolitan-warehouse",
      "catalog": "metropolitan-warehouse",
      "slug": "tracciamento-metropolitan-warehouse",
      "title": "Tracciamento Metropolitan Warehouse (MWD): mobili",
      "name": "Metropolitan Warehouse & Delivery",
      "color": "#0062b7"
    },
    {
      "id": "topyou",
      "catalog": "topyou",
      "slug": "topyou-tracking",
      "title": "TopYou tracking: codice TYZR, stati e consegna in Italia",
      "name": "TopYou",
      "color": "#045cd7"
    },
    {
      "id": "byhd",
      "catalog": "byhd",
      "slug": "tracciamento-byhd",
      "title": "Tracciamento BYHD: codici BYHD, stati e corriere finale",
      "name": "BYHD",
      "color": "#084282"
    },
    {
      "id": "zto-international",
      "catalog": "zto-international",
      "slug": "tracciamento-zto-international",
      "title": "Tracciamento ZTO International: numeri 771, stati e contatti",
      "name": "ZTO International",
      "color": "#3a8eff"
    },
    {
      "id": "mayiyinfu",
      "catalog": "mayiyinfu",
      "slug": "tracciamento-mayi-yinfu",
      "title": "Tracciamento Mayi Yinfu: codici MY, voli e consegna DHL",
      "name": "Mayi Yinfu",
      "color": "#000000"
    },
    {
      "id": "jrt",
      "catalog": "jrt",
      "slug": "jrt-tracking",
      "title": "JRT tracking: numero JRT…MM, stati, dogana e DHL",
      "name": "JRT",
      "color": "#023053"
    },
    {
      "id": "thhy",
      "catalog": "thhy",
      "slug": "tracciamento-tianhui-hengyuan",
      "title": "Tracciamento Tianhui Hengyuan (THHY): codici AT e scansioni",
      "name": "Tianhui Hengyuan",
      "color": "#00a2e9"
    },
    {
      "id": "js-express",
      "catalog": "js-express",
      "slug": "js-express-tracking",
      "title": "JS Express tracking: numero JSE…YQ, stati e consegna",
      "name": "JS Express",
      "color": "#fe9b00"
    },
    {
      "id": "wanbexpress",
      "catalog": "wanbexpress",
      "slug": "tracking-wanbexpress",
      "title": "Tracking WanbExpress: codici WNB…YQ, stati e consegna",
      "name": "WanbExpress",
      "color": "#da381b"
    },
    {
      "id": "gzyl",
      "catalog": "gzyl",
      "slug": "gzyl-tracking",
      "title": "GZYL tracking: numero YL…YQ, stati e pacco non arrivato",
      "name": "GZYL",
      "color": "#4e79e1"
    },
    {
      "id": "shouwu",
      "catalog": "shouwu",
      "slug": "shouwu-tracking",
      "title": "Shouwu tracking: numero SWECN…YQ, stati e chi consegna",
      "name": "Shouwu International",
      "color": "#be2c33"
    },
    {
      "id": "geodis-pl",
      "catalog": "geodis-pl",
      "slug": "geodis-polonia-tracciamento",
      "title": "Tracciamento GEODIS Polonia: numero PL, stati e consegna",
      "name": "GEODIS Poland",
      "color": "#3100e6"
    },
    {
      "id": "sending",
      "catalog": "sending",
      "slug": "tracciamento-sending",
      "title": "Tracciamento Sending in Spagna: numero, stati e telefono",
      "name": "Sending",
      "color": "#e52021"
    },
    {
      "id": "hywl",
      "catalog": "hywl",
      "slug": "hywl-tracking",
      "title": "HYWL tracking: numero TRK e stati di Huiyi Logistics",
      "name": "Huiyi Logistics",
      "color": "#0b8f91"
    },
    {
      "id": "ywe",
      "catalog": "ywe",
      "slug": "tracciamento-ywe",
      "title": "Tracciamento YWE (YanwenExpress): numero, stati e aiuto",
      "name": "YWE",
      "color": "#055aa1"
    },
    {
      "id": "roadie",
      "catalog": "roadie",
      "slug": "tracciamento-roadie",
      "title": "Tracciamento Roadie: codici 1R, stati e foto di consegna",
      "name": "Roadie",
      "color": "#76cccd"
    },
    {
      "id": "ecms-express",
      "catalog": "ecms-express",
      "slug": "tracciamento-ecms-express",
      "title": "Tracciamento ECMS Express: codici da 16 caratteri e dogana",
      "name": "ECMS Express",
      "color": "#12823d"
    },
    {
      "id": "chunghwa-post",
      "catalog": "chunghwa-post",
      "slug": "chunghwa-post-tracking",
      "title": "Tracking Chunghwa Post (Taiwan): codice, stati e consegna",
      "name": "Chunghwa Post",
      "color": "#e8171f"
    },
    {
      "id": "raben",
      "catalog": "raben",
      "slug": "tracciamento-raben",
      "title": "Tracciamento Raben: numero, stati e consegna",
      "name": "Raben",
      "color": "#df0020"
    },
    {
      "id": "jd-logistics",
      "catalog": "jd-logistics",
      "slug": "tracciamento-jd-logistics",
      "title": "Tracciamento JD Logistics: codici JD, stati e contatti",
      "name": "JD Logistics",
      "color": "#e1251b"
    },
    {
      "id": "singapore-post",
      "catalog": "singapore-post",
      "slug": "tracciamento-singapore-post",
      "title": "Tracciamento Singapore Post (SingPost): numeri e stati",
      "name": "Singapore Post",
      "color": "#2e5da8"
    },
    {
      "id": "zto",
      "catalog": "zto",
      "slug": "tracciamento-zto-express",
      "title": "Tracciamento ZTO Express: numeri 中通快递, stati e contatti",
      "name": "ZTO Express",
      "color": "#0279ff"
    },
    {
      "id": "cne",
      "catalog": "cne",
      "slug": "tracciamento-cne-express",
      "title": "Tracciamento CNE Express: numeri 3A5V, stati e consegna",
      "name": "CNE Express",
      "color": "#e83828"
    },
    {
      "id": "japan-post",
      "catalog": "japan-post",
      "slug": "tracciamento-japan-post",
      "title": "Tracciamento Japan Post: codici EMS e stati spiegati",
      "name": "Japan Post",
      "color": "#cc0000"
    },
    {
      "id": "russian-post",
      "catalog": "russian-post",
      "slug": "tracciamento-russian-post",
      "title": "Tracciamento Russian Post (Почта России): numeri e stati",
      "name": "Russian Post",
      "color": "#1928dc"
    },
    {
      "id": "huahan",
      "catalog": "huahan",
      "slug": "tracciamento-huahan-logistics",
      "title": "Tracciamento HuaHan Logistics: codice HHW…YQ e consegna",
      "name": "HuaHan Logistics",
      "color": "#0069e8"
    },
    {
      "id": "epost-global",
      "catalog": "epost-global",
      "slug": "tracciamento-epost-global",
      "title": "Tracciamento ePost Global: numeri EPG, stati e consegna",
      "name": "ePost Global",
      "color": "#27967a"
    },
    {
      "id": "gls-at",
      "catalog": "gls-at",
      "slug": "tracking-gls-austria",
      "title": "Tracking GLS Austria: stati, Parcel Shop e contatti",
      "name": "GLS Austria",
      "color": "#ffdd00"
    },
    {
      "id": "india-post",
      "catalog": "india-post",
      "slug": "tracciamento-india-post",
      "title": "Tracciamento India Post e Speed Post: numeri, stati e dogana",
      "name": "India Post",
      "color": "#b82025"
    },
    {
      "id": "morning-global",
      "catalog": "morning-global",
      "slug": "tracciamento-morning-global",
      "title": "Tracciamento Morning Global: codici BR…MG, stati e contatti",
      "name": "Morning Global",
      "color": "#050059"
    },
    {
      "id": "border-express",
      "catalog": "border-express",
      "slug": "tracciamento-border-express",
      "title": "Tracciamento Border Express (BEX): codici e stati",
      "name": "Border Express",
      "color": "#cc092f"
    },
    {
      "id": "delhivery",
      "catalog": "delhivery",
      "slug": "tracciamento-delhivery",
      "title": "Tracciamento Delhivery: numeri AWB, stati e assistenza",
      "name": "Delhivery",
      "color": "#ed4136"
    },
    {
      "id": "aras-kargo",
      "catalog": "aras-kargo",
      "slug": "tracciamento-aras-kargo",
      "title": "Tracciamento Aras Kargo: numeri e stati in italiano",
      "name": "Aras Kargo",
      "color": "#dc0812"
    },
    {
      "id": "emirates-post",
      "catalog": "emirates-post",
      "slug": "tracciamento-emirates-post",
      "title": "Tracciamento Emirates Post: codici AE e stati spiegati",
      "name": "Emirates Post",
      "color": "#004a98"
    },
    {
      "id": "blue-dart",
      "catalog": "blue-dart",
      "slug": "tracciamento-blue-dart",
      "title": "Tracciamento Blue Dart: numeri waybill e stati spiegati",
      "name": "Blue Dart",
      "color": "#0059a9"
    },
    {
      "id": "ekart",
      "catalog": "ekart",
      "slug": "tracciamento-ekart",
      "title": "Tracciamento Ekart: codici ID, stati InscannedAtDH e RTO",
      "name": "Ekart",
      "color": "#0074D9"
    },
    {
      "id": "j-and-t-middle-east",
      "catalog": "j-and-t-middle-east",
      "slug": "tracciamento-jt-express-middle-east",
      "title": "Tracciamento J&T Express Middle East: codici JTE e stati",
      "name": "J&T Express Middle East",
      "color": "#e60000"
    },
    {
      "id": "dpd-ro",
      "catalog": "dpd-ro",
      "slug": "tracking-dpd-romania",
      "title": "Tracking DPD Romania: stati, Smart Control e contatti",
      "name": "DPD Romania",
      "color": "#dc0032"
    },
    {
      "id": "globalpost",
      "catalog": "globalpost",
      "slug": "tracciamento-globalpost",
      "title": "Tracciamento GlobalPost: numeri GP, stati e consegna",
      "name": "GlobalPost",
      "color": "#11003a"
    },
    {
      "id": "cdek",
      "catalog": "cdek",
      "slug": "tracciamento-cdek",
      "title": "Tracciamento CDEK (СДЭК): codici e stati in italiano",
      "name": "CDEK",
      "color": "#00b33c"
    },
    {
      "id": "forza-delivery",
      "catalog": "forza-delivery",
      "slug": "tracciamento-forza-delivery",
      "title": "Tracciamento Forza Delivery: guía, stati e contatti",
      "name": "Forza Delivery Express",
      "color": "#ea5c1d"
    },
    {
      "id": "quantium-solutions",
      "catalog": "quantium-solutions",
      "slug": "tracciamento-quantium-solutions",
      "title": "Tracciamento Quantium Solutions: codici, stati e supporto",
      "name": "Quantium Solutions",
      "color": "#00474f"
    },
    {
      "id": "correos-chile",
      "catalog": "correos-chile",
      "slug": "tracciamento-correos-chile",
      "title": "Tracciamento Correos de Chile: codici e stati spiegati",
      "name": "Correos de Chile",
      "color": "#d9232d"
    },
    {
      "id": "posta-shqiptare",
      "catalog": "posta-shqiptare",
      "slug": "tracking-posta-shqiptare",
      "title": "Tracking Posta Shqiptare: codici AL, stati e dogana",
      "name": "Posta Shqiptare",
      "color": "#1a3292"
    },
    {
      "id": "dsv",
      "catalog": "dsv",
      "slug": "tracking-dsv",
      "title": "Tracking DSV: numeri di spedizione, stati e DB Schenker",
      "name": "DSV",
      "color": "#002664"
    },
    {
      "id": "pin-ag",
      "catalog": "pin-ag",
      "slug": "tracciamento-pin-ag",
      "title": "Tracciamento PIN AG: raccomandate, pacchi e ritiro",
      "name": "PIN AG",
      "color": "#003366"
    },
    {
      "id": "pitney-bowes",
      "catalog": "pitney-bowes",
      "slug": "tracking-pitney-bowes",
      "title": "Tracking Pitney Bowes: codici UPAA, UPBE e PBX spiegati",
      "name": "Pitney Bowes",
      "color": "#3e53a4"
    },
    {
      "id": "rl-carriers",
      "catalog": "rl-carriers",
      "slug": "tracciamento-rl-carriers",
      "title": "Tracciamento R+L Carriers: numero PRO, stati e reclami",
      "name": "R+L Carriers",
      "color": "#006647"
    },
    {
      "id": "nox",
      "catalog": "nox",
      "slug": "tracciamento-nox",
      "title": "Tracciamento nox NachtExpress: stati, deposito e contatti",
      "name": "nox",
      "color": "#fadb00"
    },
    {
      "id": "far-international",
      "catalog": "far-international",
      "slug": "tracciamento-far-international",
      "title": "Tracciamento FAR International: numeri, stati e contatti",
      "name": "FAR International",
      "color": "#003b75"
    },
    {
      "id": "jifan",
      "catalog": "jifan",
      "slug": "tracciamento-jifan",
      "title": "Tracciamento Jifan: numeri JFGJ, stati e ora di Pechino",
      "name": "Jifan",
      "color": "#c81011"
    },
    {
      "id": "posta-romana",
      "catalog": "posta-romana",
      "slug": "tracking-posta-romana",
      "title": "Tracking Poșta Română: codici RO, stati e dogana",
      "name": "Poșta Română",
      "color": "#ea0029"
    },
    {
      "id": "dpd-nl",
      "catalog": "dpd-nl",
      "slug": "tracciamento-dpd-paesi-bassi",
      "title": "Tracciamento DPD Paesi Bassi: stati, consegna e contatti",
      "name": "DPD Netherlands",
      "color": "#dc0032"
    },
    {
      "id": "dpd-at",
      "catalog": "dpd-at",
      "slug": "tracking-dpd-austria",
      "title": "Tracking DPD Austria: stati, punti Pickup e contatti",
      "name": "DPD Austria",
      "color": "#dc0032"
    },
    {
      "id": "ozon-logistics",
      "catalog": "ozon-logistics",
      "slug": "tracking-ozon",
      "title": "Tracking Ozon: codici d'ordine e stati in italiano",
      "name": "Ozon",
      "color": "#005bff"
    },
    {
      "id": "citymail",
      "catalog": "citymail",
      "slug": "tracciamento-citymail",
      "title": "Tracciamento CityMail: numeri, stati e consegne Svezia",
      "name": "CityMail",
      "color": "#fc4c02"
    },
    {
      "id": "dayone",
      "catalog": "dayone",
      "slug": "tracciamento-dayone",
      "title": "Tracciamento DayOne: numeri DOT, stati e chi consegna",
      "name": "DayOne",
      "color": "#e86d1c"
    },
    {
      "id": "dpd-be",
      "catalog": "dpd-be",
      "slug": "tracking-dpd-belgium",
      "title": "Tracking DPD Belgium: stati, consegna e contatti",
      "name": "DPD Belgium",
      "color": "#dc0032"
    },
    {
      "id": "packlink",
      "catalog": "packlink",
      "slug": "tracking-packlink",
      "title": "Tracking Packlink: riferimento, stati e chi contattare",
      "name": "Packlink",
      "color": "#ff4f00"
    },
    {
      "id": "sto",
      "catalog": "sto",
      "slug": "tracciamento-sto-express",
      "title": "Tracciamento STO Express: numeri 申通快递, stati e contatti",
      "name": "STO Express",
      "color": "#ff6700"
    },
    {
      "id": "j-and-t-cn",
      "catalog": "j-and-t-cn",
      "slug": "tracciamento-jt-express-china",
      "title": "Tracciamento J&T Express China: numeri JT, stati e contatti",
      "name": "J&T Express China",
      "color": "#e62019"
    },
    {
      "id": "tforce-freight",
      "catalog": "tforce-freight",
      "slug": "tracciamento-tforce-freight",
      "title": "Tracciamento TForce Freight: numeri PRO, stati e reclami",
      "name": "TForce Freight",
      "color": "#0053a1"
    },
    {
      "id": "andreani",
      "catalog": "andreani",
      "slug": "tracciamento-andreani",
      "title": "Tracciamento Andreani: numeri e stati in Argentina",
      "name": "Andreani",
      "color": "#d71920"
    },
    {
      "id": "mainfreight",
      "catalog": "mainfreight",
      "slug": "tracciamento-mainfreight",
      "title": "Tracciamento Mainfreight: codici merce e stati",
      "name": "Mainfreight",
      "color": "#005eb8"
    },
    {
      "id": "spx-sg",
      "catalog": "spx-sg",
      "slug": "tracciamento-spx-express-singapore",
      "title": "Tracciamento SPX Express Singapore: Shopee e stati",
      "name": "SPX Express Singapore",
      "color": "#ee4d2d"
    },
    {
      "id": "dai-post",
      "catalog": "dai-post",
      "slug": "tracciamento-dai-post",
      "title": "Tracciamento DAI Post: numeri, stati e aiuto consegna",
      "name": "DAI Post",
      "color": "#008faf"
    },
    {
      "id": "eurodis",
      "catalog": "eurodis",
      "slug": "tracciamento-eurodis",
      "title": "Tracciamento EURODIS: codici spedizione e partner locali",
      "name": "EURODIS",
      "color": "#0b418b"
    },
    {
      "id": "chronodiali",
      "catalog": "chronodiali",
      "slug": "tracciamento-chronodiali",
      "title": "Tracciamento Chronodiali: numeri CHPU, stati e contatti",
      "name": "Chronodiali",
      "color": "#009adf"
    },
    {
      "id": "17feia",
      "catalog": "17feia",
      "slug": "tracking-17feia",
      "title": "Tracking 17FEIA: codici FEI…YQ, stati e consegna",
      "name": "17FEIA",
      "color": "#00a9e4"
    },
    {
      "id": "tsjy",
      "catalog": "tsjy",
      "slug": "tracciamento-tsjy",
      "title": "Tracciamento TSJY (通世集运): numeri d’ordine e stati",
      "name": "TSJY",
      "color": "#4042e2"
    },
    {
      "id": "stone3pl",
      "catalog": "stone3pl",
      "slug": "tracciamento-stone3pl",
      "title": "Tracciamento Stone3PL: codici, stati e contatti",
      "name": "Stone3PL",
      "color": "#003d98"
    },
    {
      "id": "j-and-t-uk",
      "catalog": "j-and-t-uk",
      "slug": "tracking-jt-express-uk",
      "title": "Tracking J&T Express UK: codici JTUK e stati",
      "name": "J&T Express UK",
      "color": "#e60000"
    },
    {
      "id": "m-one",
      "catalog": "m-one",
      "slug": "tracciamento-m-one",
      "title": "Tracciamento M-ONE (Milšped ONE): stati, consegna e aiuto",
      "name": "M-ONE",
      "color": "#024da0"
    },
    {
      "id": "early-bird",
      "catalog": "early-bird",
      "slug": "tracking-early-bird",
      "title": "Tracking Early Bird: stati, consegna mattutina e locker",
      "name": "Early Bird",
      "color": "#dd7f4a"
    },
    {
      "id": "xinghe-freight",
      "catalog": "xinghe-freight",
      "slug": "xinghe-freight-tracking",
      "title": "Xinghe Freight tracking: numeri GZXH, stati e consegna",
      "name": "Xinghe Freight",
      "color": "#0e3a6b"
    },
    {
      "id": "cvc",
      "catalog": "cvc",
      "slug": "tracciamento-cvc",
      "title": "Tracciamento CVC (Си Ви Си): seguire un pacco in Bulgaria",
      "name": "CVC",
      "color": "#652b9e"
    },
    {
      "id": "transpress",
      "catalog": "transpress",
      "slug": "tracciamento-transpress",
      "title": "Tracciamento Transpress (Транспрес): pacchi in Bulgaria",
      "name": "Transpress",
      "color": "#1e6ee2"
    },
    {
      "id": "gls-us",
      "catalog": "gls-us",
      "slug": "tracking-gls-us",
      "title": "Tracking GLS US (GSO): numeri, stati e consegne",
      "name": "GLS US",
      "color": "#ffdd00"
    },
    {
      "id": "sxw-express",
      "catalog": "sxw-express",
      "slug": "tracciamento-sxw-express",
      "title": "Tracciamento SXW Express: numeri SXW, stati e dogana",
      "name": "SXW Express",
      "color": "#003876"
    },
    {
      "id": "xuchuan-logistics",
      "catalog": "xuchuan-logistics",
      "slug": "tracciamento-xuchuan-logistics",
      "title": "Tracciamento Xuchuan Logistics: numeri XC, consegna, aiuto",
      "name": "Xuchuan International Logistics",
      "color": "#0a2463"
    },
    {
      "id": "samus",
      "catalog": "samus",
      "slug": "tracciamento-samus",
      "title": "Tracciamento SAMUS: numeri, stati e ritiro a Vilnius",
      "name": "SAMUS",
      "color": "#e50079"
    },
    {
      "id": "siuntos-autobusais",
      "catalog": "siuntos-autobusais",
      "slug": "tracciamento-siuntos-autobusais",
      "title": "Tracciamento Siuntos autobusais: pacchi in bus in Lituania",
      "name": "Siuntos autobusais",
      "color": "#00843d"
    },
    {
      "id": "zipasgo",
      "catalog": "zipasgo",
      "slug": "tracciamento-zipasgo",
      "title": "Tracciamento ZipasGo: Parcel Stars, partner e aiuto",
      "name": "ZipasGo",
      "color": "#ffdb1c"
    },
    {
      "id": "dk-logistics-ee",
      "catalog": "dk-logistics-ee",
      "slug": "tracciamento-dk-logistics",
      "title": "Tracciamento DK Logistics: lettera di vettura e contatti",
      "name": "DK Logistics",
      "color": "#f58428"
    },
    {
      "id": "jet-express-ee",
      "catalog": "jet-express-ee",
      "slug": "tracciamento-jet-express",
      "title": "Tracciamento Jet Express: numero d’ordine, consegna, aiuto",
      "name": "Jet Express",
      "color": "#d61b22"
    },
    {
      "id": "fan-courier-international",
      "catalog": "fan-courier-international",
      "slug": "tracciamento-fan-courier-international",
      "title": "Tracciamento FAN Courier International: AWB e stati",
      "name": "FAN Courier International",
      "color": "#2daae1"
    },
    {
      "id": "j-and-t-br",
      "catalog": "j-and-t-br",
      "slug": "tracciamento-jt-express-brazil",
      "title": "Tracking J&T Express Brazil: codice, stati e contatti",
      "name": "J&T Express Brazil",
      "color": "#e60000"
    },
    {
      "id": "anjun",
      "catalog": "anjun",
      "slug": "tracciamento-anjun-logistics",
      "title": "Tracking Anjun Logistics: stati Cina-Brasile e contatti SAC",
      "name": "Anjun Logistics",
      "color": "#009944"
    },
    {
      "id": "xyl-ships",
      "catalog": "xyl-ships",
      "slug": "tracciamento-xyl-ships",
      "title": "Tracciamento XYL-Ships: codici NUDE e XLWL e stati",
      "name": "XYL-Ships",
      "color": "#b13c3c"
    },
    {
      "id": "jadlog",
      "catalog": "jadlog",
      "slug": "tracciamento-jadlog",
      "title": "Tracciamento Jadlog: 14 cifre, Pickup, Predict e stati",
      "name": "Jadlog",
      "color": "#dc0032"
    },
    {
      "id": "gls-pl",
      "catalog": "gls-pl",
      "slug": "tracciamento-gls-poland",
      "title": "Tracciamento GLS Poland: stati, GLS Points, locker e aiuto",
      "name": "GLS Poland",
      "color": "#ffdd00"
    },
    {
      "id": "j-and-t-mx",
      "catalog": "j-and-t-mx",
      "slug": "tracciamento-jt-express-mexico",
      "title": "Tracking J&T Express Mexico: codici JMX, telefono e aiuto",
      "name": "J&T Express Mexico",
      "color": "#e60012"
    },
    {
      "id": "bulgarian-posts",
      "catalog": "bulgarian-posts",
      "slug": "tracciamento-bulgarian-posts",
      "title": "Tracciamento Bulgarian Posts: codici PS/BG, dogana e aiuto",
      "name": "Bulgarian Posts",
      "color": "#ffd100"
    },
    {
      "id": "haypost",
      "catalog": "haypost",
      "slug": "tracciamento-haypost",
      "title": "Tracciamento HayPost: codici AM, stati e contatti",
      "name": "HayPost",
      "color": "#fa4616"
    },
    {
      "id": "360lion",
      "catalog": "360lion",
      "slug": "tracciamento-360lion",
      "title": "Tracciamento 360Lion: codici WSH e AR, stati e contatti",
      "name": "360Lion",
      "color": "#cf5d15"
    },
    {
      "id": "paquetexpress",
      "catalog": "paquetexpress",
      "slug": "tracciamento-paquetexpress",
      "title": "Tracciamento Paquetexpress: guías, Ocurre e reclami",
      "name": "Paquetexpress",
      "color": "#002a52"
    },
    {
      "id": "ydh",
      "catalog": "ydh",
      "slug": "tracciamento-ydh",
      "title": "Tracciamento YDH: codici YDH...YQ e AZ, passaggi e aiuto",
      "name": "YDH",
      "color": "#015db9"
    },
    {
      "id": "a-tu-hora-express",
      "catalog": "a-tu-hora-express",
      "slug": "tracciamento-a-tu-hora-express",
      "title": "Tracciamento A Tu Hora Express: codici, CAP e aiuto",
      "name": "A Tu Hora Express",
      "color": "#1d2a84"
    },
    {
      "id": "dtdc",
      "catalog": "dtdc",
      "slug": "tracciamento-dtdc",
      "title": "Tracciamento DTDC: formati AWB, stati RTO e assistenza",
      "name": "DTDC",
      "color": "#0d237d"
    },
    {
      "id": "gofo-es",
      "catalog": "gofo-es",
      "slug": "tracciamento-gofo-spain",
      "title": "Tracciamento GOFO Spain: codici GFES e CIES e aiuto",
      "name": "GOFO Spain",
      "color": "#fc4c02"
    },
    {
      "id": "korea-post",
      "catalog": "korea-post",
      "slug": "tracciamento-korea-post",
      "title": "Tracciamento Korea Post: codici EMS KR, 13 cifre e aiuto",
      "name": "Korea Post",
      "color": "#da291c"
    },
    {
      "id": "nova-global",
      "catalog": "nova-global",
      "slug": "tracciamento-nova-global",
      "title": "Tracciamento Nova Global: codici NPG e NGUS e dogana",
      "name": "Nova Global",
      "color": "#da291c"
    },
    {
      "id": "xlcourier",
      "catalog": "xlcourier",
      "slug": "tracciamento-xlcourier",
      "title": "Tracciamento XLCourier: codici XLC, pacchi pesanti UK",
      "name": "XLCourier",
      "color": "#044ad6"
    },
    {
      "id": "estafeta",
      "catalog": "estafeta",
      "slug": "tracciamento-estafeta",
      "title": "Tracciamento Estafeta: codici 10 e 22 car. e contatti",
      "name": "Estafeta",
      "color": "#e30613"
    },
    {
      "id": "gls-hu",
      "catalog": "gls-hu",
      "slug": "tracciamento-gls-hungary",
      "title": "Tracciamento GLS Hungary: numeri, locker e contatti",
      "name": "GLS Hungary",
      "color": "#ffdd00"
    },
    {
      "id": "dhl-freight",
      "catalog": "dhl-freight",
      "slug": "tracciamento-dhl-freight",
      "title": "Tracciamento DHL Freight: codici ActiveTracing e aiuto",
      "name": "DHL Freight",
      "color": "#ffcc00"
    },
    {
      "id": "anserx",
      "catalog": "anserx",
      "slug": "tracciamento-anserx",
      "title": "Tracciamento AnserX: codici BTYCN...YQ, consegna e aiuto",
      "name": "AnserX",
      "color": "#2478be"
    },
    {
      "id": "bjs-home-delivery",
      "catalog": "bjs-home-delivery",
      "slug": "tracciamento-bjs-home-delivery",
      "title": "Tracciamento BJS Home Delivery: codici B, fasce 3 h e aiuto",
      "name": "BJS Home Delivery",
      "color": "#feba30"
    },
    {
      "id": "chukou1",
      "catalog": "chukou1",
      "slug": "tracciamento-chukou1",
      "title": "Tracciamento Chukou1: codici EC-FirstClass e contatti",
      "name": "Chukou1",
      "color": "#0b4da2"
    },
    {
      "id": "cj-packet",
      "catalog": "cj-packet",
      "slug": "tracciamento-cj-packet",
      "title": "Tracciamento CJ Packet: codici CJP...YQ, consegna e aiuto",
      "name": "CJ Packet",
      "color": "#ff7700"
    },
    {
      "id": "cmt-express",
      "catalog": "cmt-express",
      "slug": "tracciamento-cmt-express",
      "title": "Tracciamento CMT Express: codici CMT, hub Shenzhen e aiuto",
      "name": "CMT Express",
      "color": "#ed010b"
    },
    {
      "id": "correos-de-mexico",
      "catalog": "correos-de-mexico",
      "slug": "tracciamento-correos-de-mexico",
      "title": "Tracciamento Correos de México: codici S10 MX e reclami",
      "name": "Correos de México",
      "color": "#ed007d"
    },
    {
      "id": "dobropost",
      "catalog": "dobropost",
      "slug": "tracciamento-dobropost",
      "title": "Tracciamento DobroPost: codici DBRPST, stati e hub Cina",
      "name": "DobroPost",
      "color": "#ffbe2e"
    },
    {
      "id": "ems",
      "catalog": "ems",
      "slug": "tracciamento-ems",
      "title": "Tracciamento EMS: codici S10, stati e aiuto postale",
      "name": "EMS",
      "color": "#003b7a"
    },
    {
      "id": "jiayang",
      "catalog": "jiayang",
      "slug": "tracciamento-jiayang",
      "title": "Tracciamento Jiayang International: codici JYGJ e aiuto",
      "name": "Jiayang International",
      "color": "#105bcc"
    },
    {
      "id": "jumppoint-international",
      "catalog": "jumppoint-international",
      "slug": "tracciamento-jumppoint-international",
      "title": "Tracciamento Jumppoint International: codici JP e aiuto",
      "name": "Jumppoint International Express",
      "color": "#0f4ae1"
    },
    {
      "id": "kuayuan-express",
      "catalog": "kuayuan-express",
      "slug": "tracciamento-kuayuan-express",
      "title": "Tracciamento Kuayuan Express: codici, passaggi e aiuto",
      "name": "Kuayuan Express",
      "color": "#084b86"
    },
    {
      "id": "liccardi",
      "catalog": "liccardi",
      "slug": "tracciamento-liccardi",
      "title": "Tracciamento Liccardi Express Courier: LDV, stati e aiuto",
      "name": "Liccardi Express Courier",
      "color": "#014895"
    },
    {
      "id": "maersk-ecommerce",
      "catalog": "maersk-ecommerce",
      "slug": "tracciamento-maersk-ecommerce",
      "title": "Tracciamento Maersk eCommerce: codici 1M, stati e aiuto",
      "name": "Maersk eCommerce",
      "color": "#42b0d5"
    },
    {
      "id": "pdu",
      "catalog": "pdu",
      "slug": "tracciamento-pdu",
      "title": "Tracciamento PDU: codici Parcel Delivery Union e aiuto",
      "name": "PDU",
      "color": "#005cfe"
    },
    {
      "id": "post-haste",
      "catalog": "post-haste",
      "slug": "tracciamento-post-haste",
      "title": "Tracciamento Post Haste: ticket NZ, codici e assistenza",
      "name": "Post Haste",
      "color": "#24b2d8"
    },
    {
      "id": "redur",
      "catalog": "redur",
      "slug": "tracciamento-redur",
      "title": "Tracciamento Redur: codici 14 cifre, Spagna e aiuto",
      "name": "Redur",
      "color": "#0c4c49"
    },
    {
      "id": "sengi-express",
      "catalog": "sengi-express",
      "slug": "tracciamento-sengi-express",
      "title": "Tracciamento Sengi Express: codici Cina–Italia e Poste",
      "name": "Sengi Express",
      "color": "#1a44a4"
    },
    {
      "id": "serpost",
      "catalog": "serpost",
      "slug": "tracciamento-serpost",
      "title": "Tracciamento Serpost: codici S10 Perù, dogana e aiuto",
      "name": "Serpost",
      "color": "#00308a"
    },
    {
      "id": "skypostal",
      "catalog": "skypostal",
      "slug": "tracciamento-skypostal",
      "title": "Tracciamento SkyPostal: codici SKAMZUS e SP, stati e aiuto",
      "name": "SkyPostal",
      "color": "#088aea"
    },
    {
      "id": "thailand-post",
      "catalog": "thailand-post",
      "slug": "tracciamento-thailand-post",
      "title": "Tracciamento Thailand Post: codici TH S10, EMS e 1545",
      "name": "Thailand Post",
      "color": "#ef172f"
    },
    {
      "id": "yunda",
      "catalog": "yunda",
      "slug": "tracciamento-yunda",
      "title": "Tracciamento Yunda Express: codici, stati e 95546",
      "name": "Yunda Express",
      "color": "#ffce00"
    },
    {
      "id": "onlog-express",
      "catalog": "onlog-express",
      "slug": "tracciamento-onlog-express",
      "title": "Tracciamento Onlog Express: codici AA, stati e aiuto",
      "name": "Onlog Express",
      "color": "#1a345d"
    },
    {
      "id": "a-duie-pyle",
      "catalog": "a-duie-pyle",
      "slug": "tracciamento-a-duie-pyle",
      "title": "Tracciamento A. Duie Pyle: codici PRO, LTL e supporto",
      "name": "A. Duie Pyle",
      "color": "#f27629"
    },
    {
      "id": "buffalo-ex",
      "catalog": "buffalo-ex",
      "slug": "tracciamento-buffalo-ex",
      "title": "Tracciamento BUFFALO: codici BUFZA, dogana Sudafrica e aiuto",
      "name": "BUFFALO",
      "color": "#bc8c4d"
    },
    {
      "id": "chit-chats",
      "catalog": "chit-chats",
      "slug": "tracciamento-chit-chats",
      "title": "Tracciamento Chit Chats: codici 10 car., USPS/Canada Post",
      "name": "Chit Chats",
      "color": "#db2b35"
    },
    {
      "id": "dpd-hu",
      "catalog": "dpd-hu",
      "slug": "tracciamento-dpd-hu",
      "title": "Tracciamento DPD Hungary: codici 14 cifre, FOXPOST e aiuto",
      "name": "DPD Hungary",
      "color": "#dc0032"
    },
    {
      "id": "e24metro-ca",
      "catalog": "e24metro-ca",
      "slug": "tracciamento-e24metro-ca",
      "title": "Tracciamento E24 (Metro Supply Chain): Canada e aiuto",
      "name": "E24",
      "color": "#545859"
    },
    {
      "id": "go-express",
      "catalog": "go-express",
      "slug": "tracciamento-go-express",
      "title": "Tracciamento GO! Express: codici a 12 cifre, stati e aiuto",
      "name": "GO! Express",
      "color": "#b9242b"
    },
    {
      "id": "kuehne-nagel",
      "catalog": "kuehne-nagel",
      "slug": "tracciamento-kuehne-nagel",
      "title": "Tracciamento Kuehne+Nagel: numeri, stati e consegne",
      "name": "Kuehne+Nagel",
      "color": "#003369"
    },
    {
      "id": "memphis-courier",
      "catalog": "memphis-courier",
      "slug": "tracciamento-memphis-courier",
      "title": "Tracciamento Memphis Courier: codici M-MIA, dogana e aiuto",
      "name": "Memphis Courier",
      "color": "#1f3c5b"
    },
    {
      "id": "oca",
      "catalog": "oca",
      "slug": "tracciamento-oca",
      "title": "Tracciamento OCA: numeri a 19 cifre, stati, filiali e aiuto",
      "name": "OCA",
      "color": "#592c82"
    },
    {
      "id": "pigeonship",
      "catalog": "pigeonship",
      "slug": "tracciamento-pigeonship",
      "title": "Tracciamento PigeonShip: numeri, stati e consegna",
      "name": "PigeonShip",
      "color": "#f2b02c"
    },
    {
      "id": "seko",
      "catalog": "seko",
      "slug": "tracciamento-seko-logistics",
      "title": "Tracciamento SEKO Logistics: codici, OmniParcel e consegne",
      "name": "SEKO Logistics",
      "color": "#ef3e42"
    },
    {
      "id": "bfe-express",
      "catalog": "bfe-express",
      "slug": "tracciamento-bfe-express",
      "title": "Tracciamento BFE Express: codici CK e WYB, stati e contatti",
      "name": "BFE Express",
      "color": "#122a88"
    },
    {
      "id": "pasarex",
      "catalog": "pasarex",
      "slug": "tracciamento-pasarex",
      "title": "Tracciamento PasarEX: codici Amazon AMZPSR, dogana e aiuto",
      "name": "PasarEX",
      "color": "#124381"
    },
    {
      "id": "spee-dee",
      "catalog": "spee-dee",
      "slug": "tracciamento-spee-dee",
      "title": "Tracciamento Spee-Dee Delivery: codici SP, stati e aiuto",
      "name": "Spee-Dee",
      "color": "#ff4734"
    },
    {
      "id": "tnt-au",
      "catalog": "tnt-au",
      "slug": "tracciamento-tnt-australia",
      "title": "Tracciamento TNT Australia: codici, stati e assistenza",
      "name": "TNT Australia",
      "color": "#ff6600"
    },
    {
      "id": "wedo",
      "catalog": "wedo",
      "slug": "tracciamento-one-by-allegro-cz",
      "title": "Tracciamento One by Allegro CZ (WE|DO): codici e ritiro",
      "name": "One by Allegro CZ",
      "color": "#006d57"
    },
    {
      "id": "5post",
      "catalog": "5post",
      "slug": "tracciamento-5post",
      "title": "Tracciamento 5Post: codici, ritiro Pyaterochka e stati",
      "name": "5Post",
      "color": "#64b441"
    },
    {
      "id": "shipter",
      "catalog": "shipter",
      "slug": "tracciamento-shipter",
      "title": "Tracciamento Shipter: codici H3 dalla Corea del Sud",
      "name": "Shipter",
      "color": "#005eff"
    }
  ],
  "es": [
    {
      "id": "quickpac",
      "catalog": "quickpac",
      "slug": "seguimiento-quickpac",
      "title": "Seguimiento Quickpac: número de 18 dígitos y contacto",
      "name": "Quickpac",
      "color": "#ed1c24"
    },
    {
      "id": "planzer",
      "catalog": "planzer",
      "slug": "seguimiento-planzer",
      "title": "Seguimiento Planzer: qué significa cada estado y contacto",
      "name": "Planzer",
      "color": "#e30613"
    },
    {
      "id": "colis-prive",
      "catalog": "colis-prive",
      "slug": "seguimiento-colis-prive",
      "title": "Seguimiento Colis Privé: estados franceses y qué hacer",
      "name": "Colis Privé",
      "color": "#aa78ff"
    },
    {
      "id": "brt",
      "catalog": "brt",
      "slug": "brt-seguimiento",
      "title": "Seguimiento BRT: paquete desde Italia, estados y contacto",
      "name": "BRT",
      "color": "#dc0032"
    },
    {
      "id": "correos-express",
      "catalog": "correos-express",
      "slug": "correos-express-seguimiento",
      "title": "Correos Express seguimiento: estados, entrega y teléfono",
      "name": "Correos Express",
      "color": "#ffcd00"
    },
    {
      "id": "nacex",
      "catalog": "nacex",
      "slug": "seguimiento-nacex",
      "title": "NACEX seguimiento: número de envío, estados y teléfono",
      "name": "NACEX",
      "color": "#fe5000"
    },
    {
      "id": "yunexpress",
      "catalog": "yunexpress",
      "slug": "yunexpress-seguimiento",
      "title": "YunExpress seguimiento: estados, quién entrega y contacto",
      "name": "YunExpress",
      "color": "#008284"
    },
    {
      "id": "cainiao",
      "catalog": "aliexpress",
      "slug": "cainiao-seguimiento",
      "title": "Cainiao seguimiento en España: estados, entregas y teléfono",
      "name": "Cainiao",
      "color": "#e62e04"
    },
    {
      "id": "mondial-relay",
      "catalog": "mondial-relay",
      "slug": "mondial-relay-seguimiento",
      "title": "Mondial Relay seguimiento: estados, Punto Pack e InPost",
      "name": "Mondial Relay",
      "color": "#ef3026"
    },
    {
      "id": "gls-de",
      "catalog": "gls-de",
      "slug": "seguimiento-gls-alemania",
      "title": "Seguimiento GLS Alemania: Track-ID, estados y contacto",
      "name": "GLS Germany",
      "color": "#ffdd00"
    },
    {
      "id": "dpd-de",
      "catalog": "dpd-de",
      "slug": "seguimiento-dpd-alemania",
      "title": "Seguimiento DPD Alemania: número, estados y ayuda",
      "name": "DPD Germany",
      "color": "#dc0032"
    },
    {
      "id": "hermes-de",
      "catalog": "hermes-de",
      "slug": "hermes-seguimiento",
      "title": "Hermes seguimiento: paquetes de Alemania a España",
      "name": "Hermes",
      "color": "#0091cd"
    },
    {
      "id": "gls-ch",
      "catalog": "gls-ch",
      "slug": "seguimiento-gls-suiza",
      "title": "Seguimiento GLS Suiza: estados, Swiss Post y contacto",
      "name": "GLS Switzerland",
      "color": "#ffdd00"
    },
    {
      "id": "dpd-ch",
      "catalog": "dpd",
      "slug": "dpd-suiza-seguimiento",
      "title": "DPD Suiza seguimiento: rastrear paquete, Pickup y contacto",
      "name": "DPD Switzerland",
      "color": "#dc0032"
    },
    {
      "id": "inpost",
      "catalog": "inpost",
      "slug": "inpost-seguimiento",
      "title": "InPost seguimiento: estados, PIN, Locker y Punto Pack",
      "name": "InPost",
      "color": "#ffcc05"
    },
    {
      "id": "paack",
      "catalog": "paack",
      "slug": "paack-seguimiento",
      "title": "Paack seguimiento: estados, entrega fallida y teléfono",
      "name": "Paack",
      "color": "#5b2dee"
    },
    {
      "id": "evri",
      "catalog": "evri-uk",
      "slug": "seguimiento-evri",
      "title": "Seguimiento Evri: número, estados, aduanas y entrega",
      "name": "Evri",
      "color": "#00014d"
    },
    {
      "id": "chronopost",
      "catalog": "chronopost",
      "slug": "chronopost-seguimiento",
      "title": "Chronopost seguimiento: paquete desde Francia y estados",
      "name": "Chronopost",
      "color": "#0056a7"
    },
    {
      "id": "relais-colis",
      "catalog": "relais-colis",
      "slug": "seguimiento-relais-colis",
      "title": "Seguimiento Relais Colis: estados, recogida y contacto",
      "name": "Relais Colis",
      "color": "#7bb928"
    },
    {
      "id": "poste-italiane",
      "catalog": "poste-italiane",
      "slug": "seguimiento-poste-italiane",
      "title": "Seguimiento Poste Italiane: códigos 1UW y 3UW y estados",
      "name": "Poste Italiane",
      "color": "#eedc00"
    },
    {
      "id": "seur",
      "catalog": "seur",
      "slug": "seur-seguimiento",
      "title": "SEUR seguimiento: número, estados y puntos Pickup",
      "name": "SEUR",
      "color": "#0063b3"
    },
    {
      "id": "mrw",
      "catalog": "mrw",
      "slug": "mrw-seguimiento",
      "title": "MRW seguimiento: número de envío, estados y teléfono",
      "name": "MRW",
      "color": "#19133b"
    },
    {
      "id": "ctt",
      "catalog": "ctt",
      "slug": "ctt-portugal-seguimiento",
      "title": "Seguimiento CTT Portugal: números, estados y recogida",
      "name": "CTT",
      "color": "#df0024"
    },
    {
      "id": "packeta",
      "catalog": "packeta",
      "slug": "packeta-seguimiento",
      "title": "Seguimiento Packeta: código Z, estados y puntos de recogida",
      "name": "Packeta",
      "color": "#ba1b02"
    },
    {
      "id": "dpd-pl",
      "catalog": "dpd-pl",
      "slug": "dpd-poland-tracking",
      "title": "Seguimiento DPD Polonia (DPD Polska): estados y Pickup",
      "name": "DPD Poland",
      "color": "#dc0032"
    },
    {
      "id": "4px",
      "catalog": "four-px",
      "slug": "4px-seguimiento",
      "title": "4PX seguimiento: estados, quién entrega y contacto",
      "name": "4PX",
      "color": "#005add"
    },
    {
      "id": "swiss-post",
      "catalog": "swiss-post",
      "slug": "swiss-post-seguimiento",
      "title": "Seguimiento Swiss Post: correo suizo y entrega en España",
      "name": "Swiss Post",
      "color": "#ffcc00"
    },
    {
      "id": "ctt-express",
      "catalog": "ctt-express",
      "slug": "ctt-express-seguimiento",
      "title": "CTT Express seguimiento: número, estados y teléfono",
      "name": "CTT Express",
      "color": "#df0024"
    },
    {
      "id": "dpd-fr",
      "catalog": "dpd-fr",
      "slug": "seguimiento-dpd-fr",
      "title": "Seguimiento DPD France: números, estados y Pickup",
      "name": "DPD France",
      "color": "#dc0032"
    },
    {
      "id": "gls-fr",
      "catalog": "gls-fr",
      "slug": "seguimiento-gls-france",
      "title": "Seguimiento GLS France: estados, Relais GLS y contacto",
      "name": "GLS France",
      "color": "#ffdd00"
    },
    {
      "id": "gls-it",
      "catalog": "gls-it",
      "slug": "seguimiento-gls-italy",
      "title": "Seguimiento GLS Italy: números, estados y entregas",
      "name": "GLS Italy",
      "color": "#ffdd00"
    },
    {
      "id": "poczta-polska",
      "catalog": "poczta-polska",
      "slug": "seguimiento-poczta-polska",
      "title": "Seguimiento Poczta Polska: números, estados y recogidas",
      "name": "Poczta Polska",
      "color": "#c43c2b"
    },
    {
      "id": "orlen-paczka",
      "catalog": "orlen-paczka",
      "slug": "seguimiento-orlen-paczka",
      "title": "Seguimiento ORLEN Paczka: números, estados y recogidas",
      "name": "ORLEN Paczka",
      "color": "#db3830"
    },
    {
      "id": "austrian-post",
      "catalog": "austrian-post",
      "slug": "seguimiento-austrian-post",
      "title": "Seguimiento Austrian Post: números, estados y recogida",
      "name": "Austrian Post",
      "color": "#ffdc00"
    },
    {
      "id": "tipsa",
      "catalog": "tipsa",
      "slug": "tipsa-seguimiento",
      "title": "TIPSA seguimiento: número, estados y teléfono",
      "name": "TIPSA",
      "color": "#001e62"
    },
    {
      "id": "yanwen",
      "catalog": "yanwen",
      "slug": "yanwen-seguimiento",
      "title": "Yanwen seguimiento: estados, quién entrega y contacto",
      "name": "Yanwen",
      "color": "#8cbe1e"
    },
    {
      "id": "an-post",
      "catalog": "an-post",
      "slug": "seguimiento-an-post",
      "title": "Seguimiento An Post: estados, aduanas y contacto",
      "name": "An Post",
      "color": "#00a76a"
    },
    {
      "id": "vinted-go",
      "catalog": "vinted-go",
      "slug": "vinted-go-seguimiento",
      "title": "Vinted Go seguimiento: estados, taquillas y contacto",
      "name": "Vinted Go",
      "color": "#004654"
    },
    {
      "id": "amazon-logistics",
      "catalog": "amazon-logistics",
      "slug": "amazon-logistics-seguimiento",
      "title": "Amazon Logistics seguimiento: dónde mirarlo y qué hacer",
      "name": "Amazon Logistics",
      "color": "#ff9900"
    },
    {
      "id": "dpd-uk",
      "catalog": "dpd-uk",
      "slug": "seguimiento-dpd-uk",
      "title": "Seguimiento de DPD UK: número de 14 dígitos y estados",
      "name": "DPD UK",
      "color": "#dc0032"
    },
    {
      "id": "parcelforce",
      "catalog": "parcelforce",
      "slug": "seguimiento-parcelforce-worldwide",
      "title": "Seguimiento Parcelforce Worldwide: estados y aduanas",
      "name": "Parcelforce Worldwide",
      "color": "#9d0f11"
    },
    {
      "id": "bpost",
      "catalog": "bpost",
      "slug": "bpost-seguimiento",
      "title": "Seguimiento bpost: código de barras, estados y recogida",
      "name": "bpost",
      "color": "#ef2637"
    },
    {
      "id": "dpd-ie",
      "catalog": "dpd-ie",
      "slug": "seguimiento-dpd-ireland",
      "title": "Seguimiento DPD Ireland: estados, puntos Pickup y contacto",
      "name": "DPD Ireland",
      "color": "#dc0032"
    },
    {
      "id": "gls-es",
      "catalog": "gls-es",
      "slug": "gls-spain-seguimiento",
      "title": "GLS Spain seguimiento: estados, Parcel Shop y teléfono",
      "name": "GLS Spain",
      "color": "#ffdd00"
    },
    {
      "id": "dhl-ecommerce-es",
      "catalog": "dhl-ecommerce-es",
      "slug": "dhl-ecommerce-iberia-seguimiento",
      "title": "DHL eCommerce Iberia seguimiento: estados y ServicePoints",
      "name": "DHL eCommerce Iberia",
      "color": "#ffcc00"
    },
    {
      "id": "dhl-ecommerce-pl",
      "catalog": "dhl-ecommerce-pl",
      "slug": "seguimiento-dhl-ecommerce-polonia",
      "title": "Seguimiento DHL eCommerce Polonia: estados y recogida",
      "name": "DHL eCommerce Poland",
      "color": "#ffcc00"
    },
    {
      "id": "dhl-ecommerce-uk",
      "catalog": "dhl-ecommerce-uk",
      "slug": "seguimiento-dhl-ecommerce-uk",
      "title": "Seguimiento DHL eCommerce UK: estados y contacto",
      "name": "DHL eCommerce UK",
      "color": "#ffcc00"
    },
    {
      "id": "gofo-fr",
      "catalog": "gofo-fr",
      "slug": "gofo-france-seguimiento",
      "title": "GOFO France seguimiento: número GFFR, estados y ayuda",
      "name": "GOFO France",
      "color": "#fc4c02"
    },
    {
      "id": "gofo-it",
      "catalog": "gofo-it",
      "slug": "seguimiento-gofo-italy",
      "title": "Seguimiento GOFO Italy: códigos GFIT, estados y reclamos",
      "name": "GOFO Italy",
      "color": "#fc4c02"
    },
    {
      "id": "speedpak",
      "catalog": "speedpak",
      "slug": "speedpak-seguimiento",
      "title": "SpeedPAK seguimiento: paquetes de eBay, estados y contacto",
      "name": "SpeedPAK",
      "color": "#ee7532"
    },
    {
      "id": "asendia",
      "catalog": "asendia",
      "slug": "asendia-seguimiento",
      "title": "Seguimiento Asendia: estados, reparto en España y aduanas",
      "name": "Asendia",
      "color": "#005d69"
    },
    {
      "id": "colisweb",
      "catalog": "colisweb",
      "slug": "seguimiento-colisweb",
      "title": "Seguimiento Colisweb: mensajes en francés y franjas",
      "name": "Colisweb",
      "color": "#15cbe3"
    },
    {
      "id": "ciblex",
      "catalog": "ciblex",
      "slug": "seguimiento-ciblex",
      "title": "Seguimiento Ciblex: números, estados en francés y contacto",
      "name": "Ciblex",
      "color": "#0160ab"
    },
    {
      "id": "hermes-einrichtungs-service",
      "catalog": "hermes",
      "slug": "seguimiento-hermes-einrichtungs-service",
      "title": "Seguimiento Hermes Einrichtungs-Service: muebles y cita",
      "name": "Hermes Einrichtungs-Service",
      "color": "#0091cd"
    },
    {
      "id": "landmark-global",
      "catalog": "landmark-global",
      "slug": "landmark-global-seguimiento",
      "title": "Landmark Global seguimiento: estados, entrega y contacto",
      "name": "Landmark Global",
      "color": "#e62337"
    },
    {
      "id": "dhl-ecommerce",
      "catalog": "dhl-ecommerce",
      "slug": "seguimiento-dhl-ecommerce",
      "title": "Seguimiento DHL eCommerce: números GM, estados y contacto",
      "name": "DHL eCommerce",
      "color": "#ffcc00"
    },
    {
      "id": "allegro-one",
      "catalog": "allegro-one",
      "slug": "seguimiento-allegro-one",
      "title": "Seguimiento Allegro One: One Box, One Punkt y One Kurier",
      "name": "Allegro One",
      "color": "#006d57"
    },
    {
      "id": "cacesa",
      "catalog": "cacesa",
      "slug": "cacesa-seguimiento",
      "title": "CACESA seguimiento: aduanas, estados y quién entrega",
      "name": "CACESA",
      "color": "#C9032D"
    },
    {
      "id": "sunyou",
      "catalog": "sunyou",
      "slug": "sunyou-seguimiento",
      "title": "SunYou seguimiento: número SY, estados y quién entrega",
      "name": "SunYou",
      "color": "#02a232"
    },
    {
      "id": "amazon-shipping",
      "catalog": "amazon-shipping",
      "slug": "amazon-shipping-seguimiento",
      "title": "Amazon Shipping seguimiento: número y qué hacer si no llega",
      "name": "Amazon Shipping",
      "color": "#ff9900"
    },
    {
      "id": "postnord",
      "catalog": "postnord",
      "slug": "seguimiento-postnord",
      "title": "Seguimiento PostNord: números, estados y recogida",
      "name": "PostNord",
      "color": "#00a0d6"
    },
    {
      "id": "bring",
      "catalog": "bring-posten",
      "slug": "seguimiento-bring",
      "title": "Seguimiento Bring (Posten): números, estados y plazos",
      "name": "Bring",
      "color": "#56b529"
    },
    {
      "id": "posti",
      "catalog": "posti",
      "slug": "seguimiento-posti",
      "title": "Seguimiento Posti: código JJFI, estados y contacto",
      "name": "Posti",
      "color": "#ff8000"
    },
    {
      "id": "omniva",
      "catalog": "omniva",
      "slug": "seguimiento-omniva",
      "title": "Seguimiento Omniva: códigos, estados y taquillas",
      "name": "Omniva",
      "color": "#f86300"
    },
    {
      "id": "ppl",
      "catalog": "ppl",
      "slug": "seguimiento-ppl",
      "title": "Seguimiento PPL: número de 11 dígitos, estados y boxes",
      "name": "PPL",
      "color": "#004b93"
    },
    {
      "id": "box-now",
      "catalog": "box-now",
      "slug": "seguimiento-box-now",
      "title": "Seguimiento BOX NOW: taquillas, códigos PIN y estados",
      "name": "BOX NOW",
      "color": "#44d62c"
    },
    {
      "id": "dhl-ecommerce-nl",
      "catalog": "dhl-ecommerce-nl",
      "slug": "seguimiento-dhl-ecommerce-netherlands",
      "title": "Seguimiento DHL eCommerce Países Bajos: estados y contacto",
      "name": "DHL eCommerce Netherlands",
      "color": "#ffcc00"
    },
    {
      "id": "heppner",
      "catalog": "heppner",
      "slug": "seguimiento-heppner",
      "title": "Seguimiento Heppner: número, código postal y entregas",
      "name": "Heppner",
      "color": "#005baa"
    },
    {
      "id": "c-chez-vous",
      "catalog": "c-chez-vous",
      "slug": "seguimiento-c-chez-vous",
      "title": "Seguimiento C Chez Vous: número, estados y contacto",
      "name": "C chez vous",
      "color": "#e50046"
    },
    {
      "id": "geodis",
      "catalog": "geodis",
      "slug": "geodis-seguimiento",
      "title": "Seguimiento GEODIS: número 1G, estados y contacto",
      "name": "GEODIS",
      "color": "#3100e6"
    },
    {
      "id": "delivengo",
      "catalog": "delivengo",
      "slug": "delivengo-tracking",
      "title": "Seguimiento Delivengo: paquete de La Poste desde Francia",
      "name": "Delivengo",
      "color": "#ffcf00"
    },
    {
      "id": "ecoscooting",
      "catalog": "ecoscooting",
      "slug": "ecoscooting-seguimiento",
      "title": "Ecoscooting seguimiento: estados, entregas y teléfono",
      "name": "Ecoscooting",
      "color": "#3fa7ff"
    },
    {
      "id": "swiss-post-cargo",
      "catalog": "swiss-post-cargo",
      "slug": "seguimiento-swiss-post-cargo",
      "title": "Seguimiento Swiss Post Cargo: estados, entrega y contacto",
      "name": "Swiss Post Cargo",
      "color": "#ffcc00"
    },
    {
      "id": "dachser",
      "catalog": "dachser",
      "slug": "dachser-seguimiento",
      "title": "Dachser seguimiento: enlace, estados y entrega con cita",
      "name": "Dachser",
      "color": "#005ca9"
    },
    {
      "id": "meest",
      "catalog": "meest",
      "slug": "seguimiento-meest",
      "title": "Seguimiento Meest: números, estados y contacto",
      "name": "Meest",
      "color": "#0061af"
    },
    {
      "id": "nova-poshta",
      "catalog": "nova-poshta",
      "slug": "seguimiento-nova-poshta",
      "title": "Seguimiento Nova Poshta: números, estados y oficinas",
      "name": "Nova Poshta",
      "color": "#e30613"
    },
    {
      "id": "sameday",
      "catalog": "sameday",
      "slug": "seguimiento-sameday",
      "title": "Seguimiento Sameday: AWB, easybox y estados",
      "name": "Sameday",
      "color": "#e92427"
    },
    {
      "id": "speedy",
      "catalog": "speedy",
      "slug": "seguimiento-speedy",
      "title": "Seguimiento Speedy: albarán, estados, oficinas y taquillas",
      "name": "Speedy",
      "color": "#dc0032"
    },
    {
      "id": "cargus",
      "catalog": "cargus",
      "slug": "seguimiento-cargus",
      "title": "Seguimiento Cargus: número AWB, estados y SHIP & GO",
      "name": "Cargus",
      "color": "#f68121"
    },
    {
      "id": "fan-courier",
      "catalog": "fan-courier",
      "slug": "seguimiento-fan-courier",
      "title": "Seguimiento FAN Courier: números AWB, estados y FANbox",
      "name": "FAN Courier",
      "color": "#0f2345"
    },
    {
      "id": "econt",
      "catalog": "econt",
      "slug": "seguimiento-econt",
      "title": "Seguimiento de Econt: número de envío, estados y oficinas",
      "name": "Econt",
      "color": "#234182"
    },
    {
      "id": "ceska-posta",
      "catalog": "ceska-posta",
      "slug": "seguimiento-ceska-posta",
      "title": "Seguimiento Česká pošta (Correos Checos) y Balíkovna",
      "name": "Česká pošta",
      "color": "#002776"
    },
    {
      "id": "express-one",
      "catalog": "express-one",
      "slug": "seguimiento-express-one",
      "title": "Seguimiento Express One: estados, puntos y contacto",
      "name": "Express One",
      "color": "#ec6500"
    },
    {
      "id": "foxpost",
      "catalog": "foxpost",
      "slug": "seguimiento-foxpost",
      "title": "Seguimiento Foxpost: estados, taquillas y contacto",
      "name": "Foxpost",
      "color": "#b70100"
    },
    {
      "id": "slovak-parcel-service",
      "catalog": "slovak-parcel-service",
      "slug": "seguimiento-slovak-parcel-service",
      "title": "Seguimiento Slovak Parcel Service (SPS): estados y ayuda",
      "name": "Slovak Parcel Service",
      "color": "#f58220"
    },
    {
      "id": "slovenska-posta",
      "catalog": "slovenska-posta",
      "slug": "seguimiento-slovenska-posta",
      "title": "Seguimiento Slovenská pošta: números, estados y entrega",
      "name": "Slovenská pošta",
      "color": "#f9bb00"
    },
    {
      "id": "posta-slovenije",
      "catalog": "posta-slovenije",
      "slug": "seguimiento-posta-slovenije",
      "title": "Seguimiento Pošta Slovenije: estados, recogida y contacto",
      "name": "Pošta Slovenije",
      "color": "#ffc600"
    },
    {
      "id": "hrvatska-posta",
      "catalog": "hrvatska-posta",
      "slug": "seguimiento-hrvatska-posta",
      "title": "Seguimiento Hrvatska pošta: estados, taquillas y contacto",
      "name": "Hrvatska pošta",
      "color": "#ffd100"
    },
    {
      "id": "posta-srbije",
      "catalog": "posta-srbije",
      "slug": "seguimiento-posta-srbije",
      "title": "Seguimiento Pošta Srbije: estados, taquillas y contacto",
      "name": "Pošta Srbije",
      "color": "#fbba00"
    },
    {
      "id": "venipak",
      "catalog": "venipak",
      "slug": "seguimiento-venipak",
      "title": "Seguimiento Venipak: estados, taquillas y contacto",
      "name": "Venipak",
      "color": "#9339f2"
    },
    {
      "id": "lietuvos-pastas",
      "catalog": "lietuvos-pastas",
      "slug": "seguimiento-lietuvos-pastas",
      "title": "Seguimiento Lietuvos paštas (Correos de Lituania)",
      "name": "Lietuvos paštas",
      "color": "#fedd00"
    },
    {
      "id": "latvijas-pasts",
      "catalog": "latvijas-pasts",
      "slug": "seguimiento-latvijas-pasts",
      "title": "Seguimiento Latvijas Pasts: números, estados y taquillas",
      "name": "Latvijas Pasts",
      "color": "#0c72ba"
    },
    {
      "id": "dao",
      "catalog": "dao",
      "slug": "seguimiento-dao",
      "title": "Seguimiento DAO: estados, daoSHOP y entregas fallidas",
      "name": "DAO",
      "color": "#e30613"
    },
    {
      "id": "helthjem",
      "catalog": "helthjem",
      "slug": "seguimiento-helthjem",
      "title": "Seguimiento Helthjem: estados, recogida y contacto",
      "name": "Helthjem",
      "color": "#ffb202"
    },
    {
      "id": "matkahuolto",
      "catalog": "matkahuolto",
      "slug": "seguimiento-matkahuolto",
      "title": "Seguimiento Matkahuolto: estados, recogida y contacto",
      "name": "Matkahuolto",
      "color": "#001e60"
    },
    {
      "id": "ukrposhta",
      "catalog": "ukrposhta",
      "slug": "seguimiento-ukrposhta",
      "title": "Seguimiento Ukrposhta: números, estados y recogida",
      "name": "Ukrposhta",
      "color": "#ffbe0c"
    },
    {
      "id": "acs-courier",
      "catalog": "acs-courier",
      "slug": "seguimiento-acs-courier",
      "title": "Seguimiento ACS Courier: números, estados y contacto",
      "name": "ACS Courier",
      "color": "#e42229"
    },
    {
      "id": "elta",
      "catalog": "elta",
      "slug": "seguimiento-elta-hellenic-post",
      "title": "Seguimiento ELTA Hellenic Post: estados, recogida y ayuda",
      "name": "ELTA Hellenic Post",
      "color": "#0063a9"
    },
    {
      "id": "geniki-taxydromiki",
      "catalog": "geniki-taxydromiki",
      "slug": "seguimiento-geniki-taxydromiki",
      "title": "Seguimiento Geniki Taxydromiki: estados y contacto",
      "name": "Geniki Taxydromiki",
      "color": "#004fa0"
    },
    {
      "id": "cyprus-post",
      "catalog": "cyprus-post",
      "slug": "seguimiento-cyprus-post",
      "title": "Seguimiento Cyprus Post: números, estados y aduanas",
      "name": "Cyprus Post",
      "color": "#e4a50e"
    },
    {
      "id": "post-luxembourg",
      "catalog": "post-luxembourg",
      "slug": "seguimiento-post-luxembourg",
      "title": "Seguimiento POST Luxembourg: números, estados y contacto",
      "name": "POST Luxembourg",
      "color": "#008736"
    },
    {
      "id": "maltapost",
      "catalog": "maltapost",
      "slug": "seguimiento-maltapost",
      "title": "Seguimiento MaltaPost: números, estados y recogida",
      "name": "MaltaPost",
      "color": "#d43b2c"
    },
    {
      "id": "whistl",
      "catalog": "whistl",
      "slug": "seguimiento-whistl",
      "title": "Seguimiento Whistl: quién entrega tu paquete y qué hacer",
      "name": "Whistl",
      "color": "#fd4f00"
    },
    {
      "id": "xdp",
      "catalog": "xdp",
      "slug": "seguimiento-xdp",
      "title": "Seguimiento XDP: números de envío, estados y entrega",
      "name": "XDP",
      "color": "#53b6ed"
    },
    {
      "id": "apc-overnight",
      "catalog": "apc-overnight",
      "slug": "seguimiento-apc-overnight",
      "title": "Seguimiento APC Overnight: números, estados y entregas",
      "name": "APC Overnight",
      "color": "#243269"
    },
    {
      "id": "ups",
      "catalog": "ups",
      "slug": "ups-seguimiento",
      "title": "UPS seguimiento en España: número 1Z, estados y aduanas",
      "name": "UPS",
      "color": "#351c15"
    },
    {
      "id": "fedex",
      "catalog": "fedex",
      "slug": "fedex-seguimiento",
      "title": "FedEx seguimiento: número, estados del envío y aduanas",
      "name": "FedEx",
      "color": "#4d148c"
    },
    {
      "id": "dhl-express",
      "catalog": "dhl-express",
      "slug": "dhl-express-seguimiento",
      "title": "DHL Express seguimiento: número de albarán y estados",
      "name": "DHL Express",
      "color": "#ffcc00"
    },
    {
      "id": "dhl",
      "catalog": "dhl",
      "slug": "dhl-paket-seguimiento",
      "title": "DHL Paket seguimiento: estados y envíos a España",
      "name": "DHL Paket",
      "color": "#ffcc00"
    },
    {
      "id": "la-poste",
      "catalog": "la-poste",
      "slug": "colissimo-la-poste-seguimiento",
      "title": "Colissimo y La Poste seguimiento: número, estados y entrega",
      "name": "La Poste / Colissimo",
      "color": "#ffcf00"
    },
    {
      "id": "correos-spain",
      "catalog": "correos-spain",
      "slug": "seguimiento-correos",
      "title": "Correos seguimiento: estados, entregas, aduanas y teléfono",
      "name": "Correos",
      "color": "#ffcd00"
    },
    {
      "id": "royal-mail",
      "catalog": "royal-mail",
      "slug": "seguimiento-royal-mail",
      "title": "Seguimiento Royal Mail: número, estados, aduanas y Correos",
      "name": "Royal Mail",
      "color": "#da202a"
    },
    {
      "id": "usps",
      "catalog": "usps",
      "slug": "usps-seguimiento",
      "title": "USPS seguimiento: estados, reintento de entrega y envíos",
      "name": "USPS",
      "color": "#004b87"
    },
    {
      "id": "canada-post",
      "catalog": "canada-post",
      "slug": "canada-post-tracking",
      "title": "Seguimiento Canada Post: números, estados y aviso postal",
      "name": "Canada Post",
      "color": "#e4002b"
    },
    {
      "id": "purolator",
      "catalog": "purolator",
      "slug": "seguimiento-purolator",
      "title": "Seguimiento Purolator: PIN, estados y recogidas",
      "name": "Purolator",
      "color": "#da291c"
    },
    {
      "id": "intelcom",
      "catalog": "intelcom",
      "slug": "seguimiento-intelcom-dragonfly",
      "title": "Seguimiento Intelcom / Dragonfly: números y contacto",
      "name": "Intelcom / Dragonfly",
      "color": "#13a58f"
    },
    {
      "id": "canpar",
      "catalog": "canpar",
      "slug": "seguimiento-canpar",
      "title": "Seguimiento Canpar: números, estados y entregas fallidas",
      "name": "Canpar",
      "color": "#003b70"
    },
    {
      "id": "uniuni",
      "catalog": "uniuni",
      "slug": "uniuni-tracking",
      "title": "Seguimiento UniUni: estados, entregas fallidas y contacto",
      "name": "UniUni",
      "color": "#f68841"
    },
    {
      "id": "ontrac",
      "catalog": "ontrac",
      "slug": "seguimiento-ontrac",
      "title": "Seguimiento de OnTrac: números, estados y entregas fallidas",
      "name": "OnTrac",
      "color": "#cc2027"
    },
    {
      "id": "lso",
      "catalog": "lso",
      "slug": "seguimiento-lso",
      "title": "Seguimiento LSO: números de envío, estados y ayuda",
      "name": "LSO",
      "color": "#0047ba"
    },
    {
      "id": "gofo",
      "catalog": "gofo",
      "slug": "gofo-express-rastreo",
      "title": "GOFO Express rastreo: número GFUS, estados y qué hacer",
      "name": "GOFO Express",
      "color": "#fc4c02"
    },
    {
      "id": "speedx",
      "catalog": "speedx",
      "slug": "seguimiento-speedx",
      "title": "Seguimiento de SpeedX: estados, entregas fallidas y contacto",
      "name": "SpeedX",
      "color": "#1aa3ff"
    },
    {
      "id": "australia-post",
      "catalog": "australia-post",
      "slug": "seguimiento-australia-post",
      "title": "Seguimiento de Australia Post: códigos, estados y taquillas",
      "name": "Australia Post",
      "color": "#dc1928"
    },
    {
      "id": "aramex-au",
      "catalog": "aramex-au",
      "slug": "seguimiento-aramex-australia",
      "title": "Seguimiento Aramex Australia: números, estados y contacto",
      "name": "Aramex Australia",
      "color": "#dc291e"
    },
    {
      "id": "couriersplease",
      "catalog": "couriersplease",
      "slug": "seguimiento-couriersplease",
      "title": "Seguimiento CouriersPlease: números, estados y ayuda",
      "name": "CouriersPlease",
      "color": "#c21e36"
    },
    {
      "id": "nz-post",
      "catalog": "nz-post",
      "slug": "seguimiento-nz-post",
      "title": "Seguimiento NZ Post: números, estados y entrega fallida",
      "name": "NZ Post",
      "color": "#d8291c"
    },
    {
      "id": "aramex-nz",
      "catalog": "aramex-nz",
      "slug": "seguimiento-aramex-nueva-zelanda",
      "title": "Seguimiento Aramex Nueva Zelanda: números y estados",
      "name": "Aramex New Zealand",
      "color": "#dc291e"
    },
    {
      "id": "magyar-posta",
      "catalog": "magyar-posta",
      "slug": "seguimiento-magyar-posta",
      "title": "Seguimiento Magyar Posta: estados húngaros y PostaPont",
      "name": "Magyar Posta",
      "color": "#346631"
    },
    {
      "id": "shiprocket",
      "catalog": "shiprocket",
      "slug": "seguimiento-shiprocket",
      "title": "Seguimiento Shiprocket: números AWB, estados y RTO",
      "name": "Shiprocket",
      "color": "#735ae5"
    },
    {
      "id": "first-line",
      "catalog": "first-line",
      "slug": "first-line-seguimiento",
      "title": "FIRST LINE seguimiento: números 1ST, estados y entrega",
      "name": "FIRST LINE",
      "color": "#55207a"
    },
    {
      "id": "j-and-t-de",
      "catalog": "j-and-t-de",
      "slug": "jt-express-alemania-seguimiento",
      "title": "Seguimiento J&T Express Alemania: número JTDE y estados",
      "name": "J&T Express Germany",
      "color": "#e60000"
    },
    {
      "id": "kun-express",
      "catalog": "kun-express",
      "slug": "seguimiento-kun-express",
      "title": "Seguimiento Kun Express: números KUN, estados y USPS",
      "name": "Kun Express",
      "color": "#0761ff"
    },
    {
      "id": "juxi-express",
      "catalog": "juxi-express",
      "slug": "seguimiento-juxi-express",
      "title": "Seguimiento Juxi Express: número JXC, reparto y aduanas",
      "name": "Juxi Express",
      "color": "#e2393c"
    },
    {
      "id": "lingdong",
      "catalog": "lingdong",
      "slug": "lingdong-seguimiento",
      "title": "Lingdong International seguimiento: número SZLD y estados",
      "name": "Lingdong International",
      "color": "#0171bb"
    },
    {
      "id": "j-and-t-au",
      "catalog": "j-and-t-au",
      "slug": "seguimiento-jt-express-australia",
      "title": "Seguimiento J&T Express Australia: códigos JTAU y estados",
      "name": "J&T Express Australia",
      "color": "#e60000"
    },
    {
      "id": "china-post",
      "catalog": "china-post",
      "slug": "china-post-seguimiento",
      "title": "China Post seguimiento: número, estados y entrega en España",
      "name": "China Post",
      "color": "#006d46"
    },
    {
      "id": "fedex-pl",
      "catalog": "fedex-pl",
      "slug": "seguimiento-fedex-polonia",
      "title": "Seguimiento FedEx Polonia: números de 13 cifras y estados",
      "name": "FedEx Poland",
      "color": "#4d148c"
    },
    {
      "id": "techlink",
      "catalog": "techlink",
      "slug": "seguimiento-techlink",
      "title": "Seguimiento de Techlink: números ZHPHD…ML y estados",
      "name": "Techlink",
      "color": "#e73547"
    },
    {
      "id": "z-leading",
      "catalog": "z-leading",
      "slug": "seguimiento-z-leading",
      "title": "Seguimiento Z-Leading (Zhongling): números, entrega y ayuda",
      "name": "Z-Leading",
      "color": "#005ab0"
    },
    {
      "id": "imile",
      "catalog": "imile",
      "slug": "imile-seguimiento",
      "title": "Seguimiento iMile: números, estados y quién reparte",
      "name": "iMile",
      "color": "#0032a0"
    },
    {
      "id": "postnl",
      "catalog": "spring-gds",
      "slug": "postnl-seguimiento",
      "title": "PostNL seguimiento: números, estados y entrega en España",
      "name": "PostNL",
      "color": "#ef7d00"
    },
    {
      "id": "dhlink",
      "catalog": "dhlink",
      "slug": "dhlink-seguimiento",
      "title": "Seguimiento DHLink: número de DHgate, estados y reparto",
      "name": "DHLink",
      "color": "#143167"
    },
    {
      "id": "dpd-pt",
      "catalog": "dpd-pt",
      "slug": "seguimiento-dpd-portugal",
      "title": "Seguimiento DPD Portugal: estados, puntos Pickup y contacto",
      "name": "DPD Portugal",
      "color": "#dc0032"
    },
    {
      "id": "tnt-it",
      "catalog": "tnt-it",
      "slug": "seguimiento-tnt-italia",
      "title": "Seguimiento TNT Italia: albarán, estados y entrega fallida",
      "name": "TNT Italy",
      "color": "#ff6600"
    },
    {
      "id": "tnt",
      "catalog": "tnt",
      "slug": "tnt-seguimiento",
      "title": "TNT seguimiento: número de envío, estados y entrega",
      "name": "TNT",
      "color": "#ff6600"
    },
    {
      "id": "sf-express",
      "catalog": "sf-express",
      "slug": "seguimiento-sf-express",
      "title": "Seguimiento SF Express: número de guía, estados y entrega",
      "name": "SF Express",
      "color": "#dc1e32"
    },
    {
      "id": "straightship",
      "catalog": "straightship",
      "slug": "seguimiento-straightship",
      "title": "Seguimiento de Straightship: números STRTD y entregas",
      "name": "Straightship",
      "color": "#6aae66"
    },
    {
      "id": "correios-br",
      "catalog": "correios-br",
      "slug": "correios-brasil-seguimiento",
      "title": "Correios Brasil seguimiento: código, estados y aduanas",
      "name": "Correios Brazil",
      "color": "#ffd500"
    },
    {
      "id": "yto",
      "catalog": "yto",
      "slug": "seguimiento-yto-express",
      "title": "Seguimiento YTO Express: números YT, estados y ayuda",
      "name": "YTO Express",
      "color": "#4d148c"
    },
    {
      "id": "spx-br",
      "catalog": "spx-br",
      "slug": "seguimiento-spx-express-brasil",
      "title": "Seguimiento de SPX Express Brasil: Shopee y estados",
      "name": "SPX Express Brazil",
      "color": "#ee4d2d"
    },
    {
      "id": "eushipments",
      "catalog": "eushipments",
      "slug": "seguimiento-eushipments",
      "title": "Seguimiento euShipments: estados y mensajería local",
      "name": "euShipments",
      "color": "#0a8fcf"
    },
    {
      "id": "mhi",
      "catalog": "mhi",
      "slug": "seguimiento-mhi",
      "title": "Seguimiento MHI: estados de Trackmail y quién entrega",
      "name": "MHI",
      "color": "#00075c"
    },
    {
      "id": "pengyou",
      "catalog": "pengyou",
      "slug": "pengyou-seguimiento",
      "title": "Seguimiento Pengyou: números PY, estados y entrega",
      "name": "Pengyou",
      "color": "#043a82"
    },
    {
      "id": "linhan",
      "catalog": "linhan",
      "slug": "seguimiento-linhan",
      "title": "Seguimiento LinHan International: números LHGJ y estados",
      "name": "LinHan International",
      "color": "#eb5c20"
    },
    {
      "id": "mtscn",
      "catalog": "mtscn",
      "slug": "meiteshun-seguimiento",
      "title": "Meiteshun seguimiento: número AMTS, estados y a quién acudir",
      "name": "Meiteshun Logistics",
      "color": "#f89f3b"
    },
    {
      "id": "cttyex",
      "catalog": "cttyex",
      "slug": "seguimiento-cttyex",
      "title": "Seguimiento CTTYEX: códigos CTTY…CN, estados y hora Pekín",
      "name": "CTTYEX",
      "color": "#0068AC"
    },
    {
      "id": "speedaf",
      "catalog": "speedaf",
      "slug": "seguimiento-speedaf",
      "title": "Seguimiento Speedaf Express: número, estados y ayuda",
      "name": "Speedaf",
      "color": "#ff8300"
    },
    {
      "id": "yilong-logistics",
      "catalog": "yilong-logistics",
      "slug": "seguimiento-yilong-logistics",
      "title": "Seguimiento Yilong Logistics: códigos YIL…WL y estados",
      "name": "Yilong Logistics",
      "color": "#ec0307"
    },
    {
      "id": "quick-rabbit",
      "catalog": "quick-rabbit",
      "slug": "quick-rabbit-seguimiento",
      "title": "Quick Rabbit seguimiento: código PKG, estados y Citibox",
      "name": "Quick Rabbit",
      "color": "#f6851f"
    },
    {
      "id": "huaya-express",
      "catalog": "huaya-express",
      "slug": "seguimiento-huaya-express",
      "title": "Seguimiento Huaya Express: códigos HYYT…GZ y estados",
      "name": "Huaya Express",
      "color": "#e60013"
    },
    {
      "id": "gofo-ca",
      "catalog": "gofo-ca",
      "slug": "seguimiento-gofo-ca",
      "title": "Seguimiento GOFO Canada: números GFCA, estados y retrasos",
      "name": "GOFO Canada",
      "color": "#fc4c02"
    },
    {
      "id": "metropolitan-warehouse",
      "catalog": "metropolitan-warehouse",
      "slug": "seguimiento-metropolitan-warehouse",
      "title": "Seguimiento Metropolitan Warehouse (MWD): muebles y ayuda",
      "name": "Metropolitan Warehouse & Delivery",
      "color": "#0062b7"
    },
    {
      "id": "topyou",
      "catalog": "topyou",
      "slug": "topyou-seguimiento",
      "title": "TopYou seguimiento: número TYZR, estados y entrega en España",
      "name": "TopYou",
      "color": "#045cd7"
    },
    {
      "id": "byhd",
      "catalog": "byhd",
      "slug": "seguimiento-byhd",
      "title": "Seguimiento BYHD: números BYHD, estados y quién entrega",
      "name": "BYHD",
      "color": "#084282"
    },
    {
      "id": "zto-international",
      "catalog": "zto-international",
      "slug": "seguimiento-zto-international",
      "title": "Seguimiento ZTO International: número 771 y estados",
      "name": "ZTO International",
      "color": "#3a8eff"
    },
    {
      "id": "mayiyinfu",
      "catalog": "mayiyinfu",
      "slug": "seguimiento-mayi-yinfu",
      "title": "Seguimiento de Mayi Yinfu: códigos MY, vuelos y DHL",
      "name": "Mayi Yinfu",
      "color": "#000000"
    },
    {
      "id": "jrt",
      "catalog": "jrt",
      "slug": "jrt-seguimiento",
      "title": "JRT seguimiento: número JRT…MM, vuelos, DHL y UPS",
      "name": "JRT",
      "color": "#023053"
    },
    {
      "id": "thhy",
      "catalog": "thhy",
      "slug": "seguimiento-tianhui-hengyuan",
      "title": "Seguimiento Tianhui Hengyuan (THHY): números AT y escaneos",
      "name": "Tianhui Hengyuan",
      "color": "#00a2e9"
    },
    {
      "id": "js-express",
      "catalog": "js-express",
      "slug": "js-express-seguimiento",
      "title": "JS Express seguimiento: número JSE…YQ, estados y reparto",
      "name": "JS Express",
      "color": "#fe9b00"
    },
    {
      "id": "wanbexpress",
      "catalog": "wanbexpress",
      "slug": "seguimiento-wanbexpress",
      "title": "Seguimiento WanbExpress: números WNB…YQ, estados y entrega",
      "name": "WanbExpress",
      "color": "#da381b"
    },
    {
      "id": "gzyl",
      "catalog": "gzyl",
      "slug": "gzyl-seguimiento",
      "title": "Rastreo y seguimiento GZYL: número YL…YQ y estados",
      "name": "GZYL",
      "color": "#4e79e1"
    },
    {
      "id": "shouwu",
      "catalog": "shouwu",
      "slug": "shouwu-seguimiento",
      "title": "Seguimiento Shouwu: número SWECN…YQ, estados y reparto",
      "name": "Shouwu International",
      "color": "#be2c33"
    },
    {
      "id": "geodis-pl",
      "catalog": "geodis-pl",
      "slug": "geodis-polonia-seguimiento",
      "title": "Seguimiento GEODIS Polonia: número PL, estados y entrega",
      "name": "GEODIS Poland",
      "color": "#3100e6"
    },
    {
      "id": "sending",
      "catalog": "sending",
      "slug": "sending-seguimiento",
      "title": "Sending seguimiento: número, estados y teléfono",
      "name": "Sending",
      "color": "#e52021"
    },
    {
      "id": "ywe",
      "catalog": "ywe",
      "slug": "seguimiento-ywe",
      "title": "Seguimiento YWE (YanwenExpress): número, estados y ayuda",
      "name": "YWE",
      "color": "#055aa1"
    },
    {
      "id": "roadie",
      "catalog": "roadie",
      "slug": "seguimiento-roadie",
      "title": "Seguimiento Roadie: números 1R, estados y foto de entrega",
      "name": "Roadie",
      "color": "#76cccd"
    },
    {
      "id": "ecms-express",
      "catalog": "ecms-express",
      "slug": "seguimiento-ecms-express",
      "title": "Seguimiento ECMS Express: códigos de 16 caracteres y aduana",
      "name": "ECMS Express",
      "color": "#12823d"
    },
    {
      "id": "chunghwa-post",
      "catalog": "chunghwa-post",
      "slug": "chunghwa-post-tracking",
      "title": "Seguimiento Chunghwa Post: número TW, estados y aduanas",
      "name": "Chunghwa Post",
      "color": "#e8171f"
    },
    {
      "id": "raben",
      "catalog": "raben",
      "slug": "seguimiento-raben",
      "title": "Seguimiento Raben: número de envío, estados y entrega",
      "name": "Raben",
      "color": "#df0020"
    },
    {
      "id": "jd-logistics",
      "catalog": "jd-logistics",
      "slug": "seguimiento-jd-logistics",
      "title": "Seguimiento de JD Logistics: números JD, estados y contacto",
      "name": "JD Logistics",
      "color": "#e1251b"
    },
    {
      "id": "singapore-post",
      "catalog": "singapore-post",
      "slug": "seguimiento-singapore-post",
      "title": "Seguimiento Singapore Post (SingPost): números y estados",
      "name": "Singapore Post",
      "color": "#2e5da8"
    },
    {
      "id": "zto",
      "catalog": "zto",
      "slug": "seguimiento-zto-express",
      "title": "Seguimiento de ZTO Express: números 中通快递 y estados",
      "name": "ZTO Express",
      "color": "#0279ff"
    },
    {
      "id": "cne",
      "catalog": "cne",
      "slug": "seguimiento-cne-express",
      "title": "Seguimiento CNE Express: números 3A5V, estados y entrega",
      "name": "CNE Express",
      "color": "#e83828"
    },
    {
      "id": "japan-post",
      "catalog": "japan-post",
      "slug": "seguimiento-japan-post",
      "title": "Seguimiento Japan Post: números EMS y estados explicados",
      "name": "Japan Post",
      "color": "#cc0000"
    },
    {
      "id": "russian-post",
      "catalog": "russian-post",
      "slug": "seguimiento-russian-post",
      "title": "Seguimiento Russian Post (Почта России): números y estados",
      "name": "Russian Post",
      "color": "#1928dc"
    },
    {
      "id": "huahan",
      "catalog": "huahan",
      "slug": "seguimiento-huahan-logistics",
      "title": "Seguimiento de HuaHan Logistics: número HHW…YQ y entrega",
      "name": "HuaHan Logistics",
      "color": "#0069e8"
    },
    {
      "id": "epost-global",
      "catalog": "epost-global",
      "slug": "seguimiento-epost-global",
      "title": "Seguimiento ePost Global: números EPG, estados y reparto",
      "name": "ePost Global",
      "color": "#27967a"
    },
    {
      "id": "gls-at",
      "catalog": "gls-at",
      "slug": "seguimiento-gls-austria",
      "title": "Seguimiento GLS Austria: estados, Parcel Shops y contacto",
      "name": "GLS Austria",
      "color": "#ffdd00"
    },
    {
      "id": "india-post",
      "catalog": "india-post",
      "slug": "seguimiento-india-post",
      "title": "Seguimiento de India Post: números Speed Post y estados",
      "name": "India Post",
      "color": "#b82025"
    },
    {
      "id": "morning-global",
      "catalog": "morning-global",
      "slug": "seguimiento-morning-global",
      "title": "Seguimiento Morning Global: códigos BR…MG, estados y ayuda",
      "name": "Morning Global",
      "color": "#050059"
    },
    {
      "id": "border-express",
      "catalog": "border-express",
      "slug": "seguimiento-border-express",
      "title": "Seguimiento Border Express (BEX): números y estados",
      "name": "Border Express",
      "color": "#cc092f"
    },
    {
      "id": "delhivery",
      "catalog": "delhivery",
      "slug": "seguimiento-delhivery",
      "title": "Seguimiento de Delhivery: números AWB, estados y soporte",
      "name": "Delhivery",
      "color": "#ed4136"
    },
    {
      "id": "aras-kargo",
      "catalog": "aras-kargo",
      "slug": "seguimiento-aras-kargo",
      "title": "Seguimiento Aras Kargo: números y estados en español",
      "name": "Aras Kargo",
      "color": "#dc0812"
    },
    {
      "id": "emirates-post",
      "catalog": "emirates-post",
      "slug": "seguimiento-emirates-post",
      "title": "Seguimiento Emirates Post: números AE y estados",
      "name": "Emirates Post",
      "color": "#004a98"
    },
    {
      "id": "blue-dart",
      "catalog": "blue-dart",
      "slug": "seguimiento-blue-dart",
      "title": "Seguimiento Blue Dart: números de guía y estados explicados",
      "name": "Blue Dart",
      "color": "#0059a9"
    },
    {
      "id": "ekart",
      "catalog": "ekart",
      "slug": "seguimiento-ekart",
      "title": "Seguimiento Ekart: códigos, estados InscannedAtDH y RTO",
      "name": "Ekart",
      "color": "#0074D9"
    },
    {
      "id": "j-and-t-middle-east",
      "catalog": "j-and-t-middle-east",
      "slug": "seguimiento-jt-express-middle-east",
      "title": "Seguimiento J&T Express Middle East: códigos JTE y estados",
      "name": "J&T Express Middle East",
      "color": "#e60000"
    },
    {
      "id": "dpd-ro",
      "catalog": "dpd-ro",
      "slug": "seguimiento-dpd-romania",
      "title": "Seguimiento DPD Romania: estados, Smart Control y contacto",
      "name": "DPD Romania",
      "color": "#dc0032"
    },
    {
      "id": "globalpost",
      "catalog": "globalpost",
      "slug": "seguimiento-globalpost",
      "title": "Seguimiento GlobalPost: números GP, estados y entrega",
      "name": "GlobalPost",
      "color": "#11003a"
    },
    {
      "id": "cdek",
      "catalog": "cdek",
      "slug": "seguimiento-cdek",
      "title": "Seguimiento CDEK (СДЭК): números y estados en español",
      "name": "CDEK",
      "color": "#00b33c"
    },
    {
      "id": "forza-delivery",
      "catalog": "forza-delivery",
      "slug": "forza-delivery-rastreo",
      "title": "Forza Delivery rastreo: número de guía, estados y teléfono",
      "name": "Forza Delivery Express",
      "color": "#ea5c1d"
    },
    {
      "id": "quantium-solutions",
      "catalog": "quantium-solutions",
      "slug": "seguimiento-quantium-solutions",
      "title": "Seguimiento Quantium Solutions: números, estados y ayuda",
      "name": "Quantium Solutions",
      "color": "#00474f"
    },
    {
      "id": "correos-chile",
      "catalog": "correos-chile",
      "slug": "seguimiento-correos-chile",
      "title": "Seguimiento Correos de Chile: números y estados",
      "name": "Correos de Chile",
      "color": "#d9232d"
    },
    {
      "id": "posta-shqiptare",
      "catalog": "posta-shqiptare",
      "slug": "seguimiento-posta-shqiptare",
      "title": "Seguimiento Posta Shqiptare: números AL y aduanas",
      "name": "Posta Shqiptare",
      "color": "#1a3292"
    },
    {
      "id": "dsv",
      "catalog": "dsv",
      "slug": "seguimiento-dsv",
      "title": "Seguimiento DSV: números, estados y DB Schenker",
      "name": "DSV",
      "color": "#002664"
    },
    {
      "id": "pin-ag",
      "catalog": "pin-ag",
      "slug": "seguimiento-pin-ag",
      "title": "Seguimiento PIN AG: cartas certificadas, paquetes y retiro",
      "name": "PIN AG",
      "color": "#003366"
    },
    {
      "id": "pitney-bowes",
      "catalog": "pitney-bowes",
      "slug": "seguimiento-pitney-bowes",
      "title": "Seguimiento Pitney Bowes: números UPAA, UPBE y PBX",
      "name": "Pitney Bowes",
      "color": "#3e53a4"
    },
    {
      "id": "rl-carriers",
      "catalog": "rl-carriers",
      "slug": "seguimiento-rl-carriers",
      "title": "Seguimiento R+L Carriers: número PRO, estados y reclamos",
      "name": "R+L Carriers",
      "color": "#006647"
    },
    {
      "id": "nox",
      "catalog": "nox",
      "slug": "seguimiento-nox",
      "title": "Seguimiento nox NachtExpress: estados, depósito y contacto",
      "name": "nox",
      "color": "#fadb00"
    },
    {
      "id": "far-international",
      "catalog": "far-international",
      "slug": "seguimiento-far-international",
      "title": "Seguimiento FAR International: números, estados y contacto",
      "name": "FAR International",
      "color": "#003b75"
    },
    {
      "id": "jifan",
      "catalog": "jifan",
      "slug": "seguimiento-jifan",
      "title": "Seguimiento Jifan: números JFGJ, estados y hora de Pekín",
      "name": "Jifan",
      "color": "#c81011"
    },
    {
      "id": "posta-romana",
      "catalog": "posta-romana",
      "slug": "seguimiento-posta-romana",
      "title": "Seguimiento Poșta Română: números RO, estados y aduanas",
      "name": "Poșta Română",
      "color": "#ea0029"
    },
    {
      "id": "dpd-nl",
      "catalog": "dpd-nl",
      "slug": "seguimiento-dpd-paises-bajos",
      "title": "Seguimiento DPD Países Bajos: estados, entrega y contacto",
      "name": "DPD Netherlands",
      "color": "#dc0032"
    },
    {
      "id": "dpd-at",
      "catalog": "dpd-at",
      "slug": "seguimiento-dpd-austria",
      "title": "Seguimiento DPD Austria: estados, Pickup y contacto",
      "name": "DPD Austria",
      "color": "#dc0032"
    },
    {
      "id": "ozon-logistics",
      "catalog": "ozon-logistics",
      "slug": "seguimiento-ozon",
      "title": "Seguimiento Ozon: números de pedido y estados en español",
      "name": "Ozon",
      "color": "#005bff"
    },
    {
      "id": "citymail",
      "catalog": "citymail",
      "slug": "seguimiento-citymail",
      "title": "Seguimiento CityMail: números, estados y entrega Suecia",
      "name": "CityMail",
      "color": "#fc4c02"
    },
    {
      "id": "dayone",
      "catalog": "dayone",
      "slug": "seguimiento-dayone",
      "title": "Seguimiento DayOne: números DOT, estados y quién entrega",
      "name": "DayOne",
      "color": "#e86d1c"
    },
    {
      "id": "dpd-be",
      "catalog": "dpd-be",
      "slug": "seguimiento-dpd-belgium",
      "title": "Seguimiento DPD Belgium: estados, entrega y contacto",
      "name": "DPD Belgium",
      "color": "#dc0032"
    },
    {
      "id": "packlink",
      "catalog": "packlink",
      "slug": "seguimiento-packlink",
      "title": "Seguimiento Packlink: referencia, estados y contacto",
      "name": "Packlink",
      "color": "#ff4f00"
    },
    {
      "id": "sto",
      "catalog": "sto",
      "slug": "seguimiento-sto-express",
      "title": "Seguimiento STO Express: números 申通快递, estados y contacto",
      "name": "STO Express",
      "color": "#ff6700"
    },
    {
      "id": "j-and-t-cn",
      "catalog": "j-and-t-cn",
      "slug": "seguimiento-jt-express-china",
      "title": "Seguimiento J&T Express China: números JT, estados y ayuda",
      "name": "J&T Express China",
      "color": "#e62019"
    },
    {
      "id": "tforce-freight",
      "catalog": "tforce-freight",
      "slug": "seguimiento-tforce-freight",
      "title": "Seguimiento TForce Freight: números PRO y reclamaciones",
      "name": "TForce Freight",
      "color": "#0053a1"
    },
    {
      "id": "andreani",
      "catalog": "andreani",
      "slug": "seguimiento-andreani",
      "title": "Seguimiento Andreani: números y estados en Argentina",
      "name": "Andreani",
      "color": "#d71920"
    },
    {
      "id": "mainfreight",
      "catalog": "mainfreight",
      "slug": "seguimiento-mainfreight",
      "title": "Seguimiento Mainfreight: números de carga y estados",
      "name": "Mainfreight",
      "color": "#005eb8"
    },
    {
      "id": "spx-sg",
      "catalog": "spx-sg",
      "slug": "seguimiento-spx-express-singapore",
      "title": "Seguimiento SPX Express Singapur: Shopee y estados",
      "name": "SPX Express Singapore",
      "color": "#ee4d2d"
    },
    {
      "id": "dai-post",
      "catalog": "dai-post",
      "slug": "seguimiento-dai-post",
      "title": "Seguimiento DAI Post: números, estados y ayuda de entrega",
      "name": "DAI Post",
      "color": "#008faf"
    },
    {
      "id": "eurodis",
      "catalog": "eurodis",
      "slug": "seguimiento-eurodis",
      "title": "Seguimiento EURODIS: números de envío y socios locales",
      "name": "EURODIS",
      "color": "#0b418b"
    },
    {
      "id": "chronodiali",
      "catalog": "chronodiali",
      "slug": "seguimiento-chronodiali",
      "title": "Seguimiento Chronodiali: números CHPU, estados y contacto",
      "name": "Chronodiali",
      "color": "#009adf"
    },
    {
      "id": "17feia",
      "catalog": "17feia",
      "slug": "seguimiento-17feia",
      "title": "Seguimiento 17FEIA: números FEI…YQ, estados y entrega",
      "name": "17FEIA",
      "color": "#00a9e4"
    },
    {
      "id": "tsjy",
      "catalog": "tsjy",
      "slug": "seguimiento-tsjy",
      "title": "Seguimiento TSJY (通世集运): números de pedido y estados",
      "name": "TSJY",
      "color": "#4042e2"
    },
    {
      "id": "stone3pl",
      "catalog": "stone3pl",
      "slug": "seguimiento-stone3pl",
      "title": "Seguimiento Stone3PL: números, estados y contacto",
      "name": "Stone3PL",
      "color": "#003d98"
    },
    {
      "id": "j-and-t-uk",
      "catalog": "j-and-t-uk",
      "slug": "seguimiento-jt-express-uk",
      "title": "Seguimiento J&T Express UK: números JTUK y estados",
      "name": "J&T Express UK",
      "color": "#e60000"
    },
    {
      "id": "m-one",
      "catalog": "m-one",
      "slug": "seguimiento-m-one",
      "title": "Seguimiento M-ONE (Milšped ONE): estados, entrega y ayuda",
      "name": "M-ONE",
      "color": "#024da0"
    },
    {
      "id": "early-bird",
      "catalog": "early-bird",
      "slug": "seguimiento-early-bird",
      "title": "Seguimiento Early Bird: estados, entregas y taquillas",
      "name": "Early Bird",
      "color": "#dd7f4a"
    },
    {
      "id": "xinghe-freight",
      "catalog": "xinghe-freight",
      "slug": "xinghe-freight-seguimiento",
      "title": "Seguimiento Xinghe Freight: número GZXH…YQ y estados",
      "name": "Xinghe Freight",
      "color": "#0e3a6b"
    },
    {
      "id": "cvc",
      "catalog": "cvc",
      "slug": "seguimiento-cvc",
      "title": "Seguimiento CVC (Си Ви Си): seguir un paquete en Bulgaria",
      "name": "CVC",
      "color": "#652b9e"
    },
    {
      "id": "transpress",
      "catalog": "transpress",
      "slug": "seguimiento-transpress",
      "title": "Seguimiento Transpress (Транспрес): paquetes en Bulgaria",
      "name": "Transpress",
      "color": "#1e6ee2"
    },
    {
      "id": "gls-us",
      "catalog": "gls-us",
      "slug": "seguimiento-gls-us",
      "title": "Seguimiento GLS US (GSO): números, estados y entregas",
      "name": "GLS US",
      "color": "#ffdd00"
    },
    {
      "id": "sxw-express",
      "catalog": "sxw-express",
      "slug": "seguimiento-sxw-express",
      "title": "Seguimiento de SXW Express: números SXW y estados",
      "name": "SXW Express",
      "color": "#003876"
    },
    {
      "id": "xuchuan-logistics",
      "catalog": "xuchuan-logistics",
      "slug": "seguimiento-xuchuan-logistics",
      "title": "Seguimiento Xuchuan Logistics: números XC, entrega y ayuda",
      "name": "Xuchuan International Logistics",
      "color": "#0a2463"
    },
    {
      "id": "samus",
      "catalog": "samus",
      "slug": "seguimiento-samus",
      "title": "Seguimiento SAMUS: números, estados y recogida en Vilna",
      "name": "SAMUS",
      "color": "#e50079"
    },
    {
      "id": "siuntos-autobusais",
      "catalog": "siuntos-autobusais",
      "slug": "seguimiento-siuntos-autobusais",
      "title": "Seguimiento Siuntos autobusais: paquetes en bus en Lituania",
      "name": "Siuntos autobusais",
      "color": "#00843d"
    },
    {
      "id": "zipasgo",
      "catalog": "zipasgo",
      "slug": "seguimiento-zipasgo",
      "title": "Seguimiento ZipasGo: Parcel Stars, socios y ayuda",
      "name": "ZipasGo",
      "color": "#ffdb1c"
    },
    {
      "id": "dk-logistics-ee",
      "catalog": "dk-logistics-ee",
      "slug": "seguimiento-dk-logistics",
      "title": "Seguimiento DK Logistics: guía, entregas y contacto",
      "name": "DK Logistics",
      "color": "#f58428"
    },
    {
      "id": "jet-express-ee",
      "catalog": "jet-express-ee",
      "slug": "seguimiento-jet-express",
      "title": "Seguimiento Jet Express: número de pedido, entrega, contacto",
      "name": "Jet Express",
      "color": "#d61b22"
    },
    {
      "id": "fan-courier-international",
      "catalog": "fan-courier-international",
      "slug": "seguimiento-fan-courier-international",
      "title": "Seguimiento FAN Courier International: AWB y estados",
      "name": "FAN Courier International",
      "color": "#2daae1"
    },
    {
      "id": "j-and-t-br",
      "catalog": "j-and-t-br",
      "slug": "seguimiento-jt-express-brazil",
      "title": "Seguimiento J&T Express Brazil: estados, aduanas y contacto",
      "name": "J&T Express Brazil",
      "color": "#e60000"
    },
    {
      "id": "anjun",
      "catalog": "anjun",
      "slug": "seguimiento-anjun-logistics",
      "title": "Seguimiento Anjun Logistics: estados China-Brasil y SAC",
      "name": "Anjun Logistics",
      "color": "#009944"
    },
    {
      "id": "xyl-ships",
      "catalog": "xyl-ships",
      "slug": "seguimiento-xyl-ships",
      "title": "Seguimiento XYL-Ships: códigos NUDE y XLWL y estados",
      "name": "XYL-Ships",
      "color": "#b13c3c"
    },
    {
      "id": "jadlog",
      "catalog": "jadlog",
      "slug": "seguimiento-jadlog",
      "title": "Seguimiento Jadlog: 14 dígitos, Pickup, Predict y estados",
      "name": "Jadlog",
      "color": "#dc0032"
    },
    {
      "id": "gls-pl",
      "catalog": "gls-pl",
      "slug": "seguimiento-gls-poland",
      "title": "Seguimiento GLS Poland: estados, GLS Points y taquillas",
      "name": "GLS Poland",
      "color": "#ffdd00"
    },
    {
      "id": "j-and-t-mx",
      "catalog": "j-and-t-mx",
      "slug": "rastreo-jt-express-mexico",
      "title": "Rastreo J&T Express México: guías JMX, teléfono y contacto",
      "name": "J&T Express Mexico",
      "color": "#e60012"
    },
    {
      "id": "bulgarian-posts",
      "catalog": "bulgarian-posts",
      "slug": "seguimiento-bulgarian-posts",
      "title": "Seguimiento Bulgarian Posts: códigos PS/BG y aduanas",
      "name": "Bulgarian Posts",
      "color": "#ffd100"
    },
    {
      "id": "haypost",
      "catalog": "haypost",
      "slug": "seguimiento-haypost",
      "title": "Seguimiento HayPost: códigos AM, estados y contactos",
      "name": "HayPost",
      "color": "#fa4616"
    },
    {
      "id": "360lion",
      "catalog": "360lion",
      "slug": "seguimiento-360lion",
      "title": "Seguimiento 360Lion: códigos WSH y AR, estados y ayuda",
      "name": "360Lion",
      "color": "#cf5d15"
    },
    {
      "id": "paquetexpress",
      "catalog": "paquetexpress",
      "slug": "rastreo-paquetexpress",
      "title": "Rastreo Paquetexpress: guías, estados, Ocurre y contacto",
      "name": "Paquetexpress",
      "color": "#002a52"
    },
    {
      "id": "ydh",
      "catalog": "ydh",
      "slug": "seguimiento-ydh",
      "title": "Seguimiento YDH: códigos YDH...YQ y AZ y contactos",
      "name": "YDH",
      "color": "#015db9"
    },
    {
      "id": "a-tu-hora-express",
      "catalog": "a-tu-hora-express",
      "slug": "seguimiento-a-tu-hora-express",
      "title": "Seguimiento A Tu Hora Express: códigos y código postal",
      "name": "A Tu Hora Express",
      "color": "#1d2a84"
    },
    {
      "id": "dtdc",
      "catalog": "dtdc",
      "slug": "seguimiento-dtdc",
      "title": "Seguimiento DTDC: formatos AWB, estados RTO y soporte",
      "name": "DTDC",
      "color": "#0d237d"
    },
    {
      "id": "gofo-es",
      "catalog": "gofo-es",
      "slug": "seguimiento-gofo-espana",
      "title": "Seguimiento GOFO España: códigos GFES/CIES y ayuda",
      "name": "GOFO Spain",
      "color": "#fc4c02"
    },
    {
      "id": "korea-post",
      "catalog": "korea-post",
      "slug": "seguimiento-korea-post",
      "title": "Seguimiento Korea Post: códigos EMS KR, 13 dígitos y ayuda",
      "name": "Korea Post",
      "color": "#da291c"
    },
    {
      "id": "nova-global",
      "catalog": "nova-global",
      "slug": "seguimiento-nova-global",
      "title": "Seguimiento Nova Global: códigos NPG y NGUS y aduanas",
      "name": "Nova Global",
      "color": "#da291c"
    },
    {
      "id": "xlcourier",
      "catalog": "xlcourier",
      "slug": "seguimiento-xlcourier",
      "title": "Seguimiento XLCourier: códigos XLC, paquetes pesados UK",
      "name": "XLCourier",
      "color": "#044ad6"
    },
    {
      "id": "estafeta",
      "catalog": "estafeta",
      "slug": "rastreo-estafeta",
      "title": "Rastreo Estafeta: guías, 2 intentos, reclamos y ayuda",
      "name": "Estafeta",
      "color": "#e30613"
    },
    {
      "id": "gls-hu",
      "catalog": "gls-hu",
      "slug": "seguimiento-gls-hungary",
      "title": "Seguimiento GLS Hungary: códigos, taquillas y contacto",
      "name": "GLS Hungary",
      "color": "#ffdd00"
    },
    {
      "id": "dhl-freight",
      "catalog": "dhl-freight",
      "slug": "seguimiento-dhl-freight",
      "title": "Seguimiento DHL Freight: ActiveTracing, estados y ayuda",
      "name": "DHL Freight",
      "color": "#ffcc00"
    },
    {
      "id": "anserx",
      "catalog": "anserx",
      "slug": "seguimiento-anserx",
      "title": "Seguimiento AnserX: códigos BTYCN...YQ, traspaso y ayuda",
      "name": "AnserX",
      "color": "#2478be"
    },
    {
      "id": "bjs-home-delivery",
      "catalog": "bjs-home-delivery",
      "slug": "seguimiento-bjs-home-delivery",
      "title": "Seguimiento BJS Home Delivery: códigos B, franja 3 h y ayuda",
      "name": "BJS Home Delivery",
      "color": "#feba30"
    },
    {
      "id": "chukou1",
      "catalog": "chukou1",
      "slug": "seguimiento-chukou1",
      "title": "Seguimiento Chukou1: códigos EC-FirstClass, traspaso y ayuda",
      "name": "Chukou1",
      "color": "#0b4da2"
    },
    {
      "id": "cj-packet",
      "catalog": "cj-packet",
      "slug": "seguimiento-cj-packet",
      "title": "Seguimiento CJ Packet: códigos CJP...YQ, traspaso y ayuda",
      "name": "CJ Packet",
      "color": "#ff7700"
    },
    {
      "id": "cmt-express",
      "catalog": "cmt-express",
      "slug": "seguimiento-cmt-express",
      "title": "Seguimiento CMT Express: códigos CMT, hub Shenzhen y ayuda",
      "name": "CMT Express",
      "color": "#ed010b"
    },
    {
      "id": "correos-de-mexico",
      "catalog": "correos-de-mexico",
      "slug": "rastreo-correos-de-mexico",
      "title": "Rastreo Correos de México: guías MX, ventanilla y reclamos",
      "name": "Correos de México",
      "color": "#ed007d"
    },
    {
      "id": "dobropost",
      "catalog": "dobropost",
      "slug": "seguimiento-dobropost",
      "title": "Seguimiento DobroPost: códigos DBRPST, estados y hubs",
      "name": "DobroPost",
      "color": "#ffbe2e"
    },
    {
      "id": "ems",
      "catalog": "ems",
      "slug": "seguimiento-ems",
      "title": "Seguimiento EMS: códigos S10, estados y ayuda postal",
      "name": "EMS",
      "color": "#003b7a"
    },
    {
      "id": "jiayang",
      "catalog": "jiayang",
      "slug": "seguimiento-jiayang",
      "title": "Seguimiento Jiayang International: códigos JYGJ y ayuda",
      "name": "Jiayang International",
      "color": "#105bcc"
    },
    {
      "id": "jumppoint-international",
      "catalog": "jumppoint-international",
      "slug": "seguimiento-jumppoint-international",
      "title": "Seguimiento Jumppoint International: códigos JP y ayuda",
      "name": "Jumppoint International Express",
      "color": "#0f4ae1"
    },
    {
      "id": "kuayuan-express",
      "catalog": "kuayuan-express",
      "slug": "seguimiento-kuayuan-express",
      "title": "Seguimiento Kuayuan Express: códigos, relevos y ayuda",
      "name": "Kuayuan Express",
      "color": "#084b86"
    },
    {
      "id": "liccardi",
      "catalog": "liccardi",
      "slug": "seguimiento-liccardi",
      "title": "Seguimiento Liccardi Express Courier: códigos LDV y ayuda",
      "name": "Liccardi Express Courier",
      "color": "#014895"
    },
    {
      "id": "maersk-ecommerce",
      "catalog": "maersk-ecommerce",
      "slug": "seguimiento-maersk-ecommerce",
      "title": "Seguimiento Maersk eCommerce: códigos 1M, estados y ayuda",
      "name": "Maersk eCommerce",
      "color": "#42b0d5"
    },
    {
      "id": "pdu",
      "catalog": "pdu",
      "slug": "seguimiento-pdu",
      "title": "Seguimiento PDU: códigos Parcel Delivery Union y ayuda",
      "name": "PDU",
      "color": "#005cfe"
    },
    {
      "id": "post-haste",
      "catalog": "post-haste",
      "slug": "seguimiento-post-haste",
      "title": "Seguimiento Post Haste: códigos NZ, estados y ayuda",
      "name": "Post Haste",
      "color": "#24b2d8"
    },
    {
      "id": "redur",
      "catalog": "redur",
      "slug": "seguimiento-redur",
      "title": "Seguimiento Redur: códigos de 14 dígitos, estados y ayuda",
      "name": "Redur",
      "color": "#0c4c49"
    },
    {
      "id": "sengi-express",
      "catalog": "sengi-express",
      "slug": "seguimiento-sengi-express",
      "title": "Seguimiento Sengi Express: códigos China–Italia y ayuda",
      "name": "Sengi Express",
      "color": "#1a44a4"
    },
    {
      "id": "serpost",
      "catalog": "serpost",
      "slug": "seguimiento-serpost",
      "title": "Seguimiento de Serpost: códigos S10, aduanas y contacto",
      "name": "Serpost",
      "color": "#00308a"
    },
    {
      "id": "skypostal",
      "catalog": "skypostal",
      "slug": "seguimiento-skypostal",
      "title": "Seguimiento SkyPostal: códigos SKAMZUS, SP y soporte",
      "name": "SkyPostal",
      "color": "#088aea"
    },
    {
      "id": "thailand-post",
      "catalog": "thailand-post",
      "slug": "seguimiento-thailand-post",
      "title": "Seguimiento Thailand Post: códigos TH S10, EMS y 1545",
      "name": "Thailand Post",
      "color": "#ef172f"
    },
    {
      "id": "yunda",
      "catalog": "yunda",
      "slug": "seguimiento-yunda",
      "title": "Seguimiento Yunda Express: códigos, estados y 95546",
      "name": "Yunda Express",
      "color": "#ffce00"
    },
    {
      "id": "onlog-express",
      "catalog": "onlog-express",
      "slug": "seguimiento-onlog-express",
      "title": "Seguimiento Onlog Express: códigos AA, estados y ayuda",
      "name": "Onlog Express",
      "color": "#1a345d"
    },
    {
      "id": "a-duie-pyle",
      "catalog": "a-duie-pyle",
      "slug": "seguimiento-a-duie-pyle",
      "title": "Seguimiento A. Duie Pyle: números PRO, LTL y soporte",
      "name": "A. Duie Pyle",
      "color": "#f27629"
    },
    {
      "id": "buffalo-ex",
      "catalog": "buffalo-ex",
      "slug": "seguimiento-buffalo-ex",
      "title": "Seguimiento BUFFALO: códigos BUFZA, aduana Sudáfrica y ayuda",
      "name": "BUFFALO",
      "color": "#bc8c4d"
    },
    {
      "id": "chit-chats",
      "catalog": "chit-chats",
      "slug": "seguimiento-chit-chats",
      "title": "Seguimiento Chit Chats: códigos de 10 car. y traspaso USPS",
      "name": "Chit Chats",
      "color": "#db2b35"
    },
    {
      "id": "dpd-hu",
      "catalog": "dpd-hu",
      "slug": "seguimiento-dpd-hu",
      "title": "Seguimiento DPD Hungary: códigos 14 dígitos, FOXPOST y ayuda",
      "name": "DPD Hungary",
      "color": "#dc0032"
    },
    {
      "id": "e24metro-ca",
      "catalog": "e24metro-ca",
      "slug": "seguimiento-e24metro-ca",
      "title": "Seguimiento E24 (Metro Supply Chain): Canadá y ayuda",
      "name": "E24",
      "color": "#545859"
    },
    {
      "id": "go-express",
      "catalog": "go-express",
      "slug": "seguimiento-go-express",
      "title": "Seguimiento GO! Express: albaranes de 12 dígitos y ayuda",
      "name": "GO! Express",
      "color": "#b9242b"
    },
    {
      "id": "kuehne-nagel",
      "catalog": "kuehne-nagel",
      "slug": "seguimiento-kuehne-nagel",
      "title": "Seguimiento Kuehne+Nagel: números, estados y entregas",
      "name": "Kuehne+Nagel",
      "color": "#003369"
    },
    {
      "id": "memphis-courier",
      "catalog": "memphis-courier",
      "slug": "seguimiento-memphis-courier",
      "title": "Seguimiento Memphis Courier: códigos M-MIA, aduana y ayuda",
      "name": "Memphis Courier",
      "color": "#1f3c5b"
    },
    {
      "id": "oca",
      "catalog": "oca",
      "slug": "seguimiento-oca",
      "title": "Seguimiento OCA — números, estados, sucursales y entregas",
      "name": "OCA",
      "color": "#592c82"
    },
    {
      "id": "pigeonship",
      "catalog": "pigeonship",
      "slug": "seguimiento-pigeonship",
      "title": "Seguimiento PigeonShip: códigos, estados y entrega",
      "name": "PigeonShip",
      "color": "#f2b02c"
    },
    {
      "id": "seko",
      "catalog": "seko",
      "slug": "seguimiento-seko-logistics",
      "title": "Seguimiento SEKO Logistics: números, OmniParcel y entregas",
      "name": "SEKO Logistics",
      "color": "#ef3e42"
    },
    {
      "id": "bfe-express",
      "catalog": "bfe-express",
      "slug": "seguimiento-bfe-express",
      "title": "Seguimiento BFE Express — códigos CK, WYB, aduana y ayuda",
      "name": "BFE Express",
      "color": "#122a88"
    },
    {
      "id": "pasarex",
      "catalog": "pasarex",
      "slug": "rastreo-pasarex",
      "title": "Rastreo PasarEX — códigos AMZPSR, aduana, PQR y teléfonos",
      "name": "PasarEX",
      "color": "#124381"
    },
    {
      "id": "spee-dee",
      "catalog": "spee-dee",
      "slug": "seguimiento-spee-dee",
      "title": "Seguimiento Spee-Dee Delivery: códigos SP, estados y ayuda",
      "name": "Spee-Dee",
      "color": "#ff4734"
    },
    {
      "id": "tnt-au",
      "catalog": "tnt-au",
      "slug": "seguimiento-tnt-australia",
      "title": "Seguimiento TNT Australia: números, estados y ayuda",
      "name": "TNT Australia",
      "color": "#ff6600"
    },
    {
      "id": "wedo",
      "catalog": "wedo",
      "slug": "seguimiento-one-by-allegro-cz",
      "title": "Seguimiento One by Allegro CZ (WE|DO): códigos y entrega",
      "name": "One by Allegro CZ",
      "color": "#006d57"
    },
    {
      "id": "5post",
      "catalog": "5post",
      "slug": "seguimiento-5post",
      "title": "Seguimiento 5Post: códigos, recogida Pyaterochka y estados",
      "name": "5Post",
      "color": "#64b441"
    },
    {
      "id": "shipter",
      "catalog": "shipter",
      "slug": "seguimiento-shipter",
      "title": "Seguimiento Shipter: números H3 desde Corea del Sur",
      "name": "Shipter",
      "color": "#005eff"
    }
  ],
  "pt": [
    {
      "id": "quickpac",
      "catalog": "quickpac",
      "slug": "seguimento-quickpac",
      "title": "Seguimento Quickpac: número de 18 dígitos e contacto",
      "name": "Quickpac",
      "color": "#ed1c24"
    },
    {
      "id": "planzer",
      "catalog": "planzer",
      "slug": "seguimento-planzer",
      "title": "Seguimento Planzer: significado dos estados e contacto",
      "name": "Planzer",
      "color": "#e30613"
    },
    {
      "id": "colis-prive",
      "catalog": "colis-prive",
      "slug": "seguimento-colis-prive",
      "title": "Seguimento Colis Privé: estados franceses e o que fazer",
      "name": "Colis Privé",
      "color": "#aa78ff"
    },
    {
      "id": "brt",
      "catalog": "brt",
      "slug": "brt-rastreio",
      "title": "Rastreio BRT: encomenda de Itália, estados e contacto",
      "name": "BRT",
      "color": "#dc0032"
    },
    {
      "id": "correos-express",
      "catalog": "correos-express",
      "slug": "correos-express-rastreio",
      "title": "Correos Express rastreio: estados, entregas e contacto",
      "name": "Correos Express",
      "color": "#ffcd00"
    },
    {
      "id": "nacex",
      "catalog": "nacex",
      "slug": "nacex-rastreio",
      "title": "NACEX rastreio: código, estados e contacto em Portugal",
      "name": "NACEX",
      "color": "#fe5000"
    },
    {
      "id": "yunexpress",
      "catalog": "yunexpress",
      "slug": "yunexpress-rastreio",
      "title": "YunExpress rastreio: código YT, estados e contactos",
      "name": "YunExpress",
      "color": "#008284"
    },
    {
      "id": "cainiao",
      "catalog": "aliexpress",
      "slug": "cainiao-rastreio",
      "title": "Cainiao em Portugal: rastreio, estados e contactos",
      "name": "Cainiao",
      "color": "#e62e04"
    },
    {
      "id": "mondial-relay",
      "catalog": "mondial-relay",
      "slug": "mondial-relay-rastreio",
      "title": "Mondial Relay rastreio: estados, InPost e levantamento",
      "name": "Mondial Relay",
      "color": "#ef3026"
    },
    {
      "id": "gls-de",
      "catalog": "gls-de",
      "slug": "seguimento-gls-alemanha",
      "title": "Seguimento GLS Alemanha: Track-ID, estados e contactos",
      "name": "GLS Germany",
      "color": "#ffdd00"
    },
    {
      "id": "dpd-de",
      "catalog": "dpd-de",
      "slug": "seguimento-dpd-alemanha",
      "title": "Seguimento DPD Alemanha: número, estados, Predict e ajuda",
      "name": "DPD Germany",
      "color": "#dc0032"
    },
    {
      "id": "hermes-de",
      "catalog": "hermes-de",
      "slug": "rastreamento-hermes",
      "title": "Rastreamento Hermes Alemanha: estados explicados e entrega",
      "name": "Hermes",
      "color": "#0091cd"
    },
    {
      "id": "gls-ch",
      "catalog": "gls-ch",
      "slug": "rastreio-gls-suica",
      "title": "Rastreio GLS Suíça: estados, Swiss Post e contactos",
      "name": "GLS Switzerland",
      "color": "#ffdd00"
    },
    {
      "id": "dpd-ch",
      "catalog": "dpd",
      "slug": "rastreio-dpd-suica",
      "title": "DPD Suíça rastreio: seguir encomenda, Pickup e contacto",
      "name": "DPD Switzerland",
      "color": "#dc0032"
    },
    {
      "id": "inpost",
      "catalog": "inpost",
      "slug": "inpost-rastreio",
      "title": "InPost rastreio: estados, Lockers e prazo para levantar",
      "name": "InPost",
      "color": "#ffcc05"
    },
    {
      "id": "paack",
      "catalog": "paack",
      "slug": "paack-rastreio",
      "title": "Paack rastreio: número, código postal e janela horária",
      "name": "Paack",
      "color": "#5b2dee"
    },
    {
      "id": "evri",
      "catalog": "evri-uk",
      "slug": "seguimento-evri",
      "title": "Seguimento Evri: número, estados, alfândega e entrega",
      "name": "Evri",
      "color": "#00014d"
    },
    {
      "id": "chronopost",
      "catalog": "chronopost",
      "slug": "chronopost-rastreio",
      "title": "Chronopost rastreio: estados e entrega em Portugal",
      "name": "Chronopost",
      "color": "#0056a7"
    },
    {
      "id": "relais-colis",
      "catalog": "relais-colis",
      "slug": "seguimento-relais-colis",
      "title": "Seguimento Relais Colis: estados, recolha e contacto",
      "name": "Relais Colis",
      "color": "#7bb928"
    },
    {
      "id": "poste-italiane",
      "catalog": "poste-italiane",
      "slug": "poste-italiane-rastreio",
      "title": "Poste Italiane rastreio: códigos 1UW e 3UW e estados",
      "name": "Poste Italiane",
      "color": "#eedc00"
    },
    {
      "id": "seur",
      "catalog": "seur",
      "slug": "seur-encomenda",
      "title": "SEUR encomenda: rastreio, estados e contactos em Portugal",
      "name": "SEUR",
      "color": "#0063b3"
    },
    {
      "id": "mrw",
      "catalog": "mrw",
      "slug": "mrw-rastreio",
      "title": "MRW rastreio: número, estados e contactos em Portugal",
      "name": "MRW",
      "color": "#19133b"
    },
    {
      "id": "ctt",
      "catalog": "ctt",
      "slug": "ctt-rastreio",
      "title": "CTT rastreio: números, estados, levantamento e contactos",
      "name": "CTT",
      "color": "#df0024"
    },
    {
      "id": "packeta",
      "catalog": "packeta",
      "slug": "packeta-seguimento",
      "title": "Seguimento Packeta: código Z, estados e levantamento",
      "name": "Packeta",
      "color": "#ba1b02"
    },
    {
      "id": "dpd-pl",
      "catalog": "dpd-pl",
      "slug": "dpd-poland-tracking",
      "title": "Seguimento DPD Polónia (DPD Polska): estados e DPD Pickup",
      "name": "DPD Poland",
      "color": "#dc0032"
    },
    {
      "id": "4px",
      "catalog": "four-px",
      "slug": "4px-rastreio",
      "title": "4PX rastreio: números, estados e contacto em Portugal",
      "name": "4PX",
      "color": "#005add"
    },
    {
      "id": "swiss-post",
      "catalog": "swiss-post",
      "slug": "correios-suicos-rastreio",
      "title": "Swiss Post rastreio: correios suíços e entrega em Portugal",
      "name": "Swiss Post",
      "color": "#ffcc00"
    },
    {
      "id": "ctt-express",
      "catalog": "ctt-express",
      "slug": "ctt-expresso-rastreio",
      "title": "CTT Express (Expresso) rastreio: estados e contactos",
      "name": "CTT Express",
      "color": "#df0024"
    },
    {
      "id": "dpd-fr",
      "catalog": "dpd-fr",
      "slug": "seguimento-dpd-fr",
      "title": "Seguimento DPD France: números, estados e pontos Pickup",
      "name": "DPD France",
      "color": "#dc0032"
    },
    {
      "id": "gls-fr",
      "catalog": "gls-fr",
      "slug": "seguimento-gls-france",
      "title": "Seguimento GLS France: estados, Relais GLS e contactos",
      "name": "GLS France",
      "color": "#ffdd00"
    },
    {
      "id": "gls-it",
      "catalog": "gls-it",
      "slug": "seguimento-gls-italy",
      "title": "Seguimento GLS Italy: números, estados e entregas",
      "name": "GLS Italy",
      "color": "#ffdd00"
    },
    {
      "id": "poczta-polska",
      "catalog": "poczta-polska",
      "slug": "seguimento-poczta-polska",
      "title": "Seguimento Poczta Polska: números, estados e levantamentos",
      "name": "Poczta Polska",
      "color": "#c43c2b"
    },
    {
      "id": "orlen-paczka",
      "catalog": "orlen-paczka",
      "slug": "seguimento-orlen-paczka",
      "title": "Seguimento ORLEN Paczka: números, estados e recolhas",
      "name": "ORLEN Paczka",
      "color": "#db3830"
    },
    {
      "id": "austrian-post",
      "catalog": "austrian-post",
      "slug": "rastreio-austrian-post",
      "title": "Rastreio Austrian Post: números, estados e levantamento",
      "name": "Austrian Post",
      "color": "#ffdc00"
    },
    {
      "id": "tipsa",
      "catalog": "tipsa",
      "slug": "tipsa-rastreio",
      "title": "TIPSA rastreio: número, estados, agências e contactos",
      "name": "TIPSA",
      "color": "#001e62"
    },
    {
      "id": "yanwen",
      "catalog": "yanwen",
      "slug": "rastrear-encomenda-yanwen",
      "title": "Yanwen tracking: rastrear encomenda, estados e contactos",
      "name": "Yanwen",
      "color": "#8cbe1e"
    },
    {
      "id": "an-post",
      "catalog": "an-post",
      "slug": "seguimento-an-post",
      "title": "Seguimento An Post: estados, alfândega e contactos",
      "name": "An Post",
      "color": "#00a76a"
    },
    {
      "id": "vinted-go",
      "catalog": "vinted-go",
      "slug": "vinted-go-encomenda",
      "title": "Vinted Go encomenda: rastreio, cacifos e levantamento",
      "name": "Vinted Go",
      "color": "#004654"
    },
    {
      "id": "amazon-logistics",
      "catalog": "amazon-logistics",
      "slug": "amazon-logistics-rastreio",
      "title": "Amazon Logistics rastreio: onde seguir e o que fazer",
      "name": "Amazon Logistics",
      "color": "#ff9900"
    },
    {
      "id": "dpd-uk",
      "catalog": "dpd-uk",
      "slug": "seguimento-dpd-uk",
      "title": "Seguimento DPD UK: número de 14 dígitos, estados e apoio",
      "name": "DPD UK",
      "color": "#dc0032"
    },
    {
      "id": "parcelforce",
      "catalog": "parcelforce",
      "slug": "seguimento-parcelforce-worldwide",
      "title": "Seguimento Parcelforce Worldwide: estados e alfândega",
      "name": "Parcelforce Worldwide",
      "color": "#9d0f11"
    },
    {
      "id": "bpost",
      "catalog": "bpost",
      "slug": "bpost-seguimento",
      "title": "Seguimento bpost: código de barras, estados e levantamento",
      "name": "bpost",
      "color": "#ef2637"
    },
    {
      "id": "dpd-ie",
      "catalog": "dpd-ie",
      "slug": "seguimento-dpd-ireland",
      "title": "Seguimento DPD Ireland: estados, pontos Pickup e contacto",
      "name": "DPD Ireland",
      "color": "#dc0032"
    },
    {
      "id": "gls-es",
      "catalog": "gls-es",
      "slug": "seguimento-gls-espanha",
      "title": "Seguimento GLS Espanha: estados, Parcel Shops e contacto",
      "name": "GLS Spain",
      "color": "#ffdd00"
    },
    {
      "id": "dhl-ecommerce-es",
      "catalog": "dhl-ecommerce-es",
      "slug": "dhl-ecommerce-iberia-rastreio",
      "title": "DHL eCommerce Iberia rastreio: estados e ServicePoints",
      "name": "DHL eCommerce Iberia",
      "color": "#ffcc00"
    },
    {
      "id": "dhl-ecommerce-pl",
      "catalog": "dhl-ecommerce-pl",
      "slug": "seguimento-dhl-ecommerce-polonia",
      "title": "Seguimento DHL eCommerce Polónia: estados e recolha",
      "name": "DHL eCommerce Poland",
      "color": "#ffcc00"
    },
    {
      "id": "dhl-ecommerce-uk",
      "catalog": "dhl-ecommerce-uk",
      "slug": "seguimento-dhl-ecommerce-uk",
      "title": "Seguimento DHL eCommerce UK: estados e contacto",
      "name": "DHL eCommerce UK",
      "color": "#ffcc00"
    },
    {
      "id": "gofo-fr",
      "catalog": "gofo-fr",
      "slug": "gofo-france-rastreio",
      "title": "Rastreio GOFO France: códigos GFFR, estados e apoio",
      "name": "GOFO France",
      "color": "#fc4c02"
    },
    {
      "id": "gofo-it",
      "catalog": "gofo-it",
      "slug": "rastreio-gofo-italy",
      "title": "Rastreio GOFO Italy: códigos GFIT, estados e reclamações",
      "name": "GOFO Italy",
      "color": "#fc4c02"
    },
    {
      "id": "speedpak",
      "catalog": "speedpak",
      "slug": "seguimento-speedpak",
      "title": "Seguimento SpeedPAK: encomendas eBay, estados e contacto",
      "name": "SpeedPAK",
      "color": "#ee7532"
    },
    {
      "id": "asendia",
      "catalog": "asendia",
      "slug": "asendia-rastreio",
      "title": "Rastreio Asendia: estados, entrega em Portugal e alfândega",
      "name": "Asendia",
      "color": "#005d69"
    },
    {
      "id": "colisweb",
      "catalog": "colisweb",
      "slug": "seguimento-colisweb",
      "title": "Seguimento Colisweb: mensagens em francês e janelas",
      "name": "Colisweb",
      "color": "#15cbe3"
    },
    {
      "id": "ciblex",
      "catalog": "ciblex",
      "slug": "rastreio-ciblex",
      "title": "Rastreio Ciblex: números, estados em francês e contactos",
      "name": "Ciblex",
      "color": "#0160ab"
    },
    {
      "id": "hermes-einrichtungs-service",
      "catalog": "hermes",
      "slug": "seguimento-hermes-einrichtungs-service",
      "title": "Seguimento Hermes Einrichtungs-Service: móveis e marcação",
      "name": "Hermes Einrichtungs-Service",
      "color": "#0091cd"
    },
    {
      "id": "landmark-global",
      "catalog": "landmark-global",
      "slug": "landmark-global-rastreio",
      "title": "Rastreio Landmark Global: estados, parceiros e alfândega",
      "name": "Landmark Global",
      "color": "#e62337"
    },
    {
      "id": "dhl-ecommerce",
      "catalog": "dhl-ecommerce",
      "slug": "seguimento-dhl-ecommerce",
      "title": "Seguimento DHL eCommerce: números GM, estados e contacto",
      "name": "DHL eCommerce",
      "color": "#ffcc00"
    },
    {
      "id": "allegro-one",
      "catalog": "allegro-one",
      "slug": "seguimento-allegro-one",
      "title": "Seguimento Allegro One: One Box, One Punkt e One Kurier",
      "name": "Allegro One",
      "color": "#006d57"
    },
    {
      "id": "cacesa",
      "catalog": "cacesa",
      "slug": "cacesa-rastreio",
      "title": "CACESA rastreio: alfândega, estados e quem entrega",
      "name": "CACESA",
      "color": "#C9032D"
    },
    {
      "id": "sunyou",
      "catalog": "sunyou",
      "slug": "sunyou-rastreamento",
      "title": "SunYou rastreamento: número SY, estados e quem entrega",
      "name": "SunYou",
      "color": "#02a232"
    },
    {
      "id": "amazon-shipping",
      "catalog": "amazon-shipping",
      "slug": "amazon-shipping-seguimento",
      "title": "Amazon Shipping seguimento: número PT ou ES e o que fazer",
      "name": "Amazon Shipping",
      "color": "#ff9900"
    },
    {
      "id": "postnord",
      "catalog": "postnord",
      "slug": "seguimento-postnord",
      "title": "Seguimento PostNord: números, estados e levantamento",
      "name": "PostNord",
      "color": "#00a0d6"
    },
    {
      "id": "bring",
      "catalog": "bring-posten",
      "slug": "seguimento-bring",
      "title": "Seguimento Bring (Posten): números, estados e prazos",
      "name": "Bring",
      "color": "#56b529"
    },
    {
      "id": "posti",
      "catalog": "posti",
      "slug": "rastreio-posti",
      "title": "Rastreio Posti: código JJFI, estados, cacifos e contactos",
      "name": "Posti",
      "color": "#ff8000"
    },
    {
      "id": "omniva",
      "catalog": "omniva",
      "slug": "seguimento-omniva",
      "title": "Seguimento Omniva: códigos, estados e cacifos bálticos",
      "name": "Omniva",
      "color": "#f86300"
    },
    {
      "id": "ppl",
      "catalog": "ppl",
      "slug": "seguimento-ppl",
      "title": "Seguimento PPL: código de 11 dígitos, estados e boxes",
      "name": "PPL",
      "color": "#004b93"
    },
    {
      "id": "box-now",
      "catalog": "box-now",
      "slug": "seguimento-box-now",
      "title": "Seguimento BOX NOW: cacifos, códigos PIN e estados",
      "name": "BOX NOW",
      "color": "#44d62c"
    },
    {
      "id": "dhl-ecommerce-nl",
      "catalog": "dhl-ecommerce-nl",
      "slug": "seguimento-dhl-ecommerce-netherlands",
      "title": "Seguimento DHL eCommerce Países Baixos: estados e contacto",
      "name": "DHL eCommerce Netherlands",
      "color": "#ffcc00"
    },
    {
      "id": "heppner",
      "catalog": "heppner",
      "slug": "rastreio-heppner",
      "title": "Rastreio Heppner: número, código postal e entregas",
      "name": "Heppner",
      "color": "#005baa"
    },
    {
      "id": "c-chez-vous",
      "catalog": "c-chez-vous",
      "slug": "seguimento-c-chez-vous",
      "title": "Seguimento C Chez Vous: número, estados e contacto",
      "name": "C chez vous",
      "color": "#e50046"
    },
    {
      "id": "geodis",
      "catalog": "geodis",
      "slug": "geodis-rastreio",
      "title": "Rastreio GEODIS: número 1G, estados, entrega e contacto",
      "name": "GEODIS",
      "color": "#3100e6"
    },
    {
      "id": "delivengo",
      "catalog": "delivengo",
      "slug": "delivengo-tracking",
      "title": "Seguimento Delivengo: encomenda La Poste vinda de França",
      "name": "Delivengo",
      "color": "#ffcf00"
    },
    {
      "id": "ecoscooting",
      "catalog": "ecoscooting",
      "slug": "ecoscooting-rastreio",
      "title": "Ecoscooting rastreio: estados, pontos de recolha e contacto",
      "name": "Ecoscooting",
      "color": "#3fa7ff"
    },
    {
      "id": "swiss-post-cargo",
      "catalog": "swiss-post-cargo",
      "slug": "rastreio-swiss-post-cargo",
      "title": "Rastreio Swiss Post Cargo: estados, entrega e contactos",
      "name": "Swiss Post Cargo",
      "color": "#ffcc00"
    },
    {
      "id": "dachser",
      "catalog": "dachser",
      "slug": "rastrear-encomenda-dachser",
      "title": "Dachser tracking: rastrear encomenda, estados e contactos",
      "name": "Dachser",
      "color": "#005ca9"
    },
    {
      "id": "meest",
      "catalog": "meest",
      "slug": "seguimento-meest",
      "title": "Seguimento Meest: números, estados e contactos",
      "name": "Meest",
      "color": "#0061af"
    },
    {
      "id": "nova-poshta",
      "catalog": "nova-poshta",
      "slug": "seguimento-nova-poshta",
      "title": "Seguimento Nova Poshta: números, estados e balcões",
      "name": "Nova Poshta",
      "color": "#e30613"
    },
    {
      "id": "sameday",
      "catalog": "sameday",
      "slug": "seguimento-sameday",
      "title": "Seguimento Sameday: AWB, easybox e estados explicados",
      "name": "Sameday",
      "color": "#e92427"
    },
    {
      "id": "speedy",
      "catalog": "speedy",
      "slug": "seguimento-speedy",
      "title": "Seguimento Speedy: guia de transporte, estados e cacifos",
      "name": "Speedy",
      "color": "#dc0032"
    },
    {
      "id": "cargus",
      "catalog": "cargus",
      "slug": "seguimento-cargus",
      "title": "Seguimento Cargus: número AWB, estados e SHIP & GO",
      "name": "Cargus",
      "color": "#f68121"
    },
    {
      "id": "fan-courier",
      "catalog": "fan-courier",
      "slug": "rastreio-fan-courier",
      "title": "Rastreio FAN Courier: números AWB, estados e FANbox",
      "name": "FAN Courier",
      "color": "#0f2345"
    },
    {
      "id": "econt",
      "catalog": "econt",
      "slug": "seguimento-econt",
      "title": "Seguimento Econt: número de envio, estados e balcões",
      "name": "Econt",
      "color": "#234182"
    },
    {
      "id": "ceska-posta",
      "catalog": "ceska-posta",
      "slug": "rastreio-ceska-posta",
      "title": "Rastreio Česká pošta (Correios Checos) e Balíkovna",
      "name": "Česká pošta",
      "color": "#002776"
    },
    {
      "id": "express-one",
      "catalog": "express-one",
      "slug": "seguimento-express-one",
      "title": "Seguimento Express One: estados, pontos e contactos",
      "name": "Express One",
      "color": "#ec6500"
    },
    {
      "id": "foxpost",
      "catalog": "foxpost",
      "slug": "seguimento-foxpost",
      "title": "Seguimento Foxpost: estados, cacifos e contactos",
      "name": "Foxpost",
      "color": "#b70100"
    },
    {
      "id": "slovak-parcel-service",
      "catalog": "slovak-parcel-service",
      "slug": "seguimento-slovak-parcel-service",
      "title": "Seguimento Slovak Parcel Service (SPS): estados e ajuda",
      "name": "Slovak Parcel Service",
      "color": "#f58220"
    },
    {
      "id": "slovenska-posta",
      "catalog": "slovenska-posta",
      "slug": "rastreio-slovenska-posta",
      "title": "Rastreio Slovenská pošta: números, estados e BalíkoBOX",
      "name": "Slovenská pošta",
      "color": "#f9bb00"
    },
    {
      "id": "posta-slovenije",
      "catalog": "posta-slovenije",
      "slug": "seguimento-posta-slovenije",
      "title": "Seguimento Pošta Slovenije: estados, levantamento e contacto",
      "name": "Pošta Slovenije",
      "color": "#ffc600"
    },
    {
      "id": "hrvatska-posta",
      "catalog": "hrvatska-posta",
      "slug": "rastreio-hrvatska-posta",
      "title": "Rastreio Hrvatska pošta: estados, cacifos e contactos",
      "name": "Hrvatska pošta",
      "color": "#ffd100"
    },
    {
      "id": "posta-srbije",
      "catalog": "posta-srbije",
      "slug": "rastreio-posta-srbije",
      "title": "Rastreio Pošta Srbije: correios da Sérvia, estados e ajuda",
      "name": "Pošta Srbije",
      "color": "#fbba00"
    },
    {
      "id": "venipak",
      "catalog": "venipak",
      "slug": "seguimento-venipak",
      "title": "Seguimento Venipak: estados, cacifos e entregas falhadas",
      "name": "Venipak",
      "color": "#9339f2"
    },
    {
      "id": "lietuvos-pastas",
      "catalog": "lietuvos-pastas",
      "slug": "rastreio-lietuvos-pastas",
      "title": "Rastreio Lietuvos paštas (Correios da Lituânia)",
      "name": "Lietuvos paštas",
      "color": "#fedd00"
    },
    {
      "id": "latvijas-pasts",
      "catalog": "latvijas-pasts",
      "slug": "seguimento-latvijas-pasts",
      "title": "Seguimento Latvijas Pasts: números, estados e cacifos",
      "name": "Latvijas Pasts",
      "color": "#0c72ba"
    },
    {
      "id": "dao",
      "catalog": "dao",
      "slug": "seguimento-dao",
      "title": "Seguimento DAO: estados, daoSHOP e entregas falhadas",
      "name": "DAO",
      "color": "#e30613"
    },
    {
      "id": "helthjem",
      "catalog": "helthjem",
      "slug": "seguimento-helthjem",
      "title": "Seguimento Helthjem: estados, pontos de recolha e contacto",
      "name": "Helthjem",
      "color": "#ffb202"
    },
    {
      "id": "matkahuolto",
      "catalog": "matkahuolto",
      "slug": "seguimento-matkahuolto",
      "title": "Seguimento Matkahuolto: estados, pontos e contacto",
      "name": "Matkahuolto",
      "color": "#001e60"
    },
    {
      "id": "ukrposhta",
      "catalog": "ukrposhta",
      "slug": "rastreio-ukrposhta",
      "title": "Rastreio Ukrposhta: números, estados e levantamento",
      "name": "Ukrposhta",
      "color": "#ffbe0c"
    },
    {
      "id": "acs-courier",
      "catalog": "acs-courier",
      "slug": "seguimento-acs-courier",
      "title": "Seguimento ACS Courier: números, estados e contactos",
      "name": "ACS Courier",
      "color": "#e42229"
    },
    {
      "id": "elta",
      "catalog": "elta",
      "slug": "seguimento-elta-hellenic-post",
      "title": "Seguimento ELTA Hellenic Post: estados, recolha e apoio",
      "name": "ELTA Hellenic Post",
      "color": "#0063a9"
    },
    {
      "id": "geniki-taxydromiki",
      "catalog": "geniki-taxydromiki",
      "slug": "rastreio-geniki-taxydromiki",
      "title": "Rastreio Geniki Taxydromiki: estados, entregas e contacto",
      "name": "Geniki Taxydromiki",
      "color": "#004fa0"
    },
    {
      "id": "cyprus-post",
      "catalog": "cyprus-post",
      "slug": "seguimento-cyprus-post",
      "title": "Seguimento Cyprus Post: números, estados e alfândega",
      "name": "Cyprus Post",
      "color": "#e4a50e"
    },
    {
      "id": "post-luxembourg",
      "catalog": "post-luxembourg",
      "slug": "seguimento-post-luxembourg",
      "title": "Seguimento POST Luxembourg: números, estados e contacto",
      "name": "POST Luxembourg",
      "color": "#008736"
    },
    {
      "id": "maltapost",
      "catalog": "maltapost",
      "slug": "rastreio-maltapost",
      "title": "Rastreio MaltaPost: números, estados e levantamento",
      "name": "MaltaPost",
      "color": "#d43b2c"
    },
    {
      "id": "whistl",
      "catalog": "whistl",
      "slug": "seguimento-whistl",
      "title": "Seguimento Whistl: quem entrega a encomenda e o que fazer",
      "name": "Whistl",
      "color": "#fd4f00"
    },
    {
      "id": "xdp",
      "catalog": "xdp",
      "slug": "seguimento-xdp",
      "title": "Seguimento XDP: números de expedição, estados e entrega",
      "name": "XDP",
      "color": "#53b6ed"
    },
    {
      "id": "apc-overnight",
      "catalog": "apc-overnight",
      "slug": "seguimento-apc-overnight",
      "title": "Seguimento APC Overnight: números, estados e entregas",
      "name": "APC Overnight",
      "color": "#243269"
    },
    {
      "id": "ups",
      "catalog": "ups",
      "slug": "ups-tracking",
      "title": "UPS tracking: número 1Z, estados e entregas em Portugal",
      "name": "UPS",
      "color": "#351c15"
    },
    {
      "id": "fedex",
      "catalog": "fedex",
      "slug": "fedex-tracking",
      "title": "FedEx tracking: número, estados e entregas em Portugal",
      "name": "FedEx",
      "color": "#4d148c"
    },
    {
      "id": "dhl-express",
      "catalog": "dhl-express",
      "slug": "dhl-express-tracking",
      "title": "DHL Express tracking: carta de porte, estados e alfândega",
      "name": "DHL Express",
      "color": "#ffcc00"
    },
    {
      "id": "dhl",
      "catalog": "dhl",
      "slug": "dhl-paket-rastreio",
      "title": "DHL Paket rastreio: estados e envios para Portugal",
      "name": "DHL Paket",
      "color": "#ffcc00"
    },
    {
      "id": "la-poste",
      "catalog": "la-poste",
      "slug": "colissimo-la-poste-rastreio",
      "title": "Colissimo e La Poste rastreio: estados e entrega em Portugal",
      "name": "La Poste / Colissimo",
      "color": "#ffcf00"
    },
    {
      "id": "correos-spain",
      "catalog": "correos-spain",
      "slug": "correos-espanha-rastreio",
      "title": "Correos rastreio: encomendas de Espanha para Portugal",
      "name": "Correos",
      "color": "#ffcd00"
    },
    {
      "id": "royal-mail",
      "catalog": "royal-mail",
      "slug": "royal-mail-rastreio",
      "title": "Royal Mail rastreio: estados, IVA e entrega em Portugal",
      "name": "Royal Mail",
      "color": "#da202a"
    },
    {
      "id": "usps",
      "catalog": "usps",
      "slug": "seguimento-usps",
      "title": "Seguimento USPS: número, estados e encomendas dos EUA",
      "name": "USPS",
      "color": "#004b87"
    },
    {
      "id": "canada-post",
      "catalog": "canada-post",
      "slug": "canada-post-tracking",
      "title": "Seguimento Canada Post: códigos, estados e alfândega",
      "name": "Canada Post",
      "color": "#e4002b"
    },
    {
      "id": "purolator",
      "catalog": "purolator",
      "slug": "seguimento-purolator",
      "title": "Seguimento Purolator: PIN, estados e levantamentos",
      "name": "Purolator",
      "color": "#da291c"
    },
    {
      "id": "intelcom",
      "catalog": "intelcom",
      "slug": "seguimento-intelcom-dragonfly",
      "title": "Seguimento Intelcom / Dragonfly: números e contacto",
      "name": "Intelcom / Dragonfly",
      "color": "#13a58f"
    },
    {
      "id": "canpar",
      "catalog": "canpar",
      "slug": "seguimento-canpar",
      "title": "Seguimento Canpar: números, estados e entregas falhadas",
      "name": "Canpar",
      "color": "#003b70"
    },
    {
      "id": "uniuni",
      "catalog": "uniuni",
      "slug": "uniuni-tracking",
      "title": "Seguimento UniUni: códigos, estados, entrega e contactos",
      "name": "UniUni",
      "color": "#f68841"
    },
    {
      "id": "ontrac",
      "catalog": "ontrac",
      "slug": "seguimento-ontrac",
      "title": "Seguimento OnTrac: números, estados e entregas falhadas",
      "name": "OnTrac",
      "color": "#cc2027"
    },
    {
      "id": "lso",
      "catalog": "lso",
      "slug": "seguimento-lso",
      "title": "Seguimento LSO: números airbill, estados e entregas",
      "name": "LSO",
      "color": "#0047ba"
    },
    {
      "id": "gofo",
      "catalog": "gofo",
      "slug": "gofo-express-seguimento",
      "title": "Seguimento GOFO Express: número GFUS, estados e ajuda",
      "name": "GOFO Express",
      "color": "#fc4c02"
    },
    {
      "id": "speedx",
      "catalog": "speedx",
      "slug": "seguimento-speedx",
      "title": "Seguimento SpeedX: números SPX, estados e contactos",
      "name": "SpeedX",
      "color": "#1aa3ff"
    },
    {
      "id": "australia-post",
      "catalog": "australia-post",
      "slug": "seguimento-australia-post",
      "title": "Seguimento Australia Post: códigos, estados e cacifos",
      "name": "Australia Post",
      "color": "#dc1928"
    },
    {
      "id": "aramex-au",
      "catalog": "aramex-au",
      "slug": "seguimento-aramex-australia",
      "title": "Seguimento Aramex Austrália: números, estados e contactos",
      "name": "Aramex Australia",
      "color": "#dc291e"
    },
    {
      "id": "couriersplease",
      "catalog": "couriersplease",
      "slug": "seguimento-couriersplease",
      "title": "Seguimento CouriersPlease: números, estados e ajuda",
      "name": "CouriersPlease",
      "color": "#c21e36"
    },
    {
      "id": "nz-post",
      "catalog": "nz-post",
      "slug": "seguimento-nz-post",
      "title": "Seguimento NZ Post: números, estados e entregas falhadas",
      "name": "NZ Post",
      "color": "#d8291c"
    },
    {
      "id": "aramex-nz",
      "catalog": "aramex-nz",
      "slug": "rastreio-aramex-nova-zelandia",
      "title": "Rastreio Aramex Nova Zelândia: números, estados, contactos",
      "name": "Aramex New Zealand",
      "color": "#dc291e"
    },
    {
      "id": "magyar-posta",
      "catalog": "magyar-posta",
      "slug": "seguimento-magyar-posta",
      "title": "Seguimento Magyar Posta: estados húngaros e PostaPont",
      "name": "Magyar Posta",
      "color": "#346631"
    },
    {
      "id": "shiprocket",
      "catalog": "shiprocket",
      "slug": "seguimento-shiprocket",
      "title": "Seguimento Shiprocket: números AWB, estados e RTO",
      "name": "Shiprocket",
      "color": "#735ae5"
    },
    {
      "id": "first-line",
      "catalog": "first-line",
      "slug": "rastreio-first-line",
      "title": "Rastreio FIRST LINE: códigos 1ST, estados e entrega",
      "name": "FIRST LINE",
      "color": "#55207a"
    },
    {
      "id": "j-and-t-de",
      "catalog": "j-and-t-de",
      "slug": "jt-express-alemanha-rastreio",
      "title": "J&T Express Alemanha rastreio: código JTDE e estados",
      "name": "J&T Express Germany",
      "color": "#e60000"
    },
    {
      "id": "kun-express",
      "catalog": "kun-express",
      "slug": "seguimento-kun-express",
      "title": "Seguimento Kun Express: códigos KUN, estados e USPS",
      "name": "Kun Express",
      "color": "#0761ff"
    },
    {
      "id": "juxi-express",
      "catalog": "juxi-express",
      "slug": "seguimento-juxi-express",
      "title": "Seguimento Juxi Express: código JXC, entrega e alfândega",
      "name": "Juxi Express",
      "color": "#e2393c"
    },
    {
      "id": "lingdong",
      "catalog": "lingdong",
      "slug": "seguimento-lingdong",
      "title": "Seguimento Lingdong International: número SZLD e estados",
      "name": "Lingdong International",
      "color": "#0171bb"
    },
    {
      "id": "j-and-t-au",
      "catalog": "j-and-t-au",
      "slug": "seguimento-jt-express-australia",
      "title": "Seguimento J&T Express Australia: códigos JTAU e estados",
      "name": "J&T Express Australia",
      "color": "#e60000"
    },
    {
      "id": "china-post",
      "catalog": "china-post",
      "slug": "china-post-rastreio",
      "title": "China Post rastreio: número, estados e entrega em Portugal",
      "name": "China Post",
      "color": "#006d46"
    },
    {
      "id": "fedex-pl",
      "catalog": "fedex-pl",
      "slug": "seguimento-fedex-polonia",
      "title": "Seguimento FedEx Polónia: números de 13 dígitos e estados",
      "name": "FedEx Poland",
      "color": "#4d148c"
    },
    {
      "id": "techlink",
      "catalog": "techlink",
      "slug": "seguimento-techlink",
      "title": "Seguimento Techlink: números ZHPHD…ML, estados e entrega",
      "name": "Techlink",
      "color": "#e73547"
    },
    {
      "id": "z-leading",
      "catalog": "z-leading",
      "slug": "seguimento-z-leading",
      "title": "Seguimento Z-Leading (Zhongling): números, entrega, ajuda",
      "name": "Z-Leading",
      "color": "#005ab0"
    },
    {
      "id": "imile",
      "catalog": "imile",
      "slug": "imile-rastreio",
      "title": "iMile rastreio: números, estados e entrega em Portugal",
      "name": "iMile",
      "color": "#0032a0"
    },
    {
      "id": "postnl",
      "catalog": "spring-gds",
      "slug": "postnl-rastreio",
      "title": "Rastreio PostNL: códigos 3S e NL, estados e entrega",
      "name": "PostNL",
      "color": "#ef7d00"
    },
    {
      "id": "dhlink",
      "catalog": "dhlink",
      "slug": "dhlink-rastreio",
      "title": "DHLink rastreio: número da DHgate, estados e quem entrega",
      "name": "DHLink",
      "color": "#143167"
    },
    {
      "id": "dpd-pt",
      "catalog": "dpd-pt",
      "slug": "dpd-portugal-rastreio",
      "title": "DPD Portugal rastreio: estados, pontos Pickup e contactos",
      "name": "DPD Portugal",
      "color": "#dc0032"
    },
    {
      "id": "tnt-it",
      "catalog": "tnt-it",
      "slug": "seguimento-tnt-italia",
      "title": "Seguimento TNT Itália: carta de porte, estados e entrega",
      "name": "TNT Italy",
      "color": "#ff6600"
    },
    {
      "id": "tnt",
      "catalog": "tnt",
      "slug": "tnt-rastreio",
      "title": "TNT rastreio: carta de porte, estados e entrega",
      "name": "TNT",
      "color": "#ff6600"
    },
    {
      "id": "sf-express",
      "catalog": "sf-express",
      "slug": "seguimento-sf-express",
      "title": "Seguimento SF Express: número de carta, estados e entrega",
      "name": "SF Express",
      "color": "#dc1e32"
    },
    {
      "id": "straightship",
      "catalog": "straightship",
      "slug": "seguimento-straightship",
      "title": "Seguimento Straightship: números STRTD e entregas",
      "name": "Straightship",
      "color": "#6aae66"
    },
    {
      "id": "correios-br",
      "catalog": "correios-br",
      "slug": "seguimento-correios-brasil",
      "title": "Seguimento Correios Brasil: códigos, estados e alfândega",
      "name": "Correios Brazil",
      "color": "#ffd500"
    },
    {
      "id": "yto",
      "catalog": "yto",
      "slug": "seguimento-yto-express",
      "title": "Seguimento YTO Express: número YT, estados e entrega",
      "name": "YTO Express",
      "color": "#4d148c"
    },
    {
      "id": "spx-br",
      "catalog": "spx-br",
      "slug": "seguimento-spx-express-brasil",
      "title": "Seguimento SPX Express Brasil: encomendas Shopee e estados",
      "name": "SPX Express Brazil",
      "color": "#ee4d2d"
    },
    {
      "id": "eushipments",
      "catalog": "eushipments",
      "slug": "seguimento-eushipments",
      "title": "Seguimento euShipments: estados e estafeta local",
      "name": "euShipments",
      "color": "#0a8fcf"
    },
    {
      "id": "mhi",
      "catalog": "mhi",
      "slug": "seguimento-mhi",
      "title": "Seguimento MHI: estados Trackmail e quem entrega",
      "name": "MHI",
      "color": "#00075c"
    },
    {
      "id": "pengyou",
      "catalog": "pengyou",
      "slug": "seguimento-pengyou",
      "title": "Seguimento Pengyou: números PY, estados e contactos",
      "name": "Pengyou",
      "color": "#043a82"
    },
    {
      "id": "linhan",
      "catalog": "linhan",
      "slug": "seguimento-linhan",
      "title": "Seguimento LinHan International: números LHGJ e estados",
      "name": "LinHan International",
      "color": "#eb5c20"
    },
    {
      "id": "mtscn",
      "catalog": "mtscn",
      "slug": "meiteshun-rastreio",
      "title": "Rastreio Meiteshun: número AMTS, estados e a quem perguntar",
      "name": "Meiteshun Logistics",
      "color": "#f89f3b"
    },
    {
      "id": "cttyex",
      "catalog": "cttyex",
      "slug": "seguimento-cttyex",
      "title": "Seguimento CTTYEX: números CTTY…CN, estados e hora Pequim",
      "name": "CTTYEX",
      "color": "#0068AC"
    },
    {
      "id": "speedaf",
      "catalog": "speedaf",
      "slug": "seguimento-speedaf",
      "title": "Seguimento Speedaf Express: número, estados e apoio",
      "name": "Speedaf",
      "color": "#ff8300"
    },
    {
      "id": "yilong-logistics",
      "catalog": "yilong-logistics",
      "slug": "rastreio-yilong-logistics",
      "title": "Rastreio Yilong Logistics: códigos YIL…WL e estados",
      "name": "Yilong Logistics",
      "color": "#ec0307"
    },
    {
      "id": "quick-rabbit",
      "catalog": "quick-rabbit",
      "slug": "seguimento-quick-rabbit",
      "title": "Seguimento Quick Rabbit: códigos PKG, estados e Citibox",
      "name": "Quick Rabbit",
      "color": "#f6851f"
    },
    {
      "id": "huaya-express",
      "catalog": "huaya-express",
      "slug": "seguimento-huaya-express",
      "title": "Seguimento Huaya Express: números HYYT…GZ e estados",
      "name": "Huaya Express",
      "color": "#e60013"
    },
    {
      "id": "gofo-ca",
      "catalog": "gofo-ca",
      "slug": "seguimento-gofo-ca",
      "title": "Seguimento GOFO Canada: números GFCA, estados e atrasos",
      "name": "GOFO Canada",
      "color": "#fc4c02"
    },
    {
      "id": "metropolitan-warehouse",
      "catalog": "metropolitan-warehouse",
      "slug": "seguimento-metropolitan-warehouse",
      "title": "Seguimento Metropolitan Warehouse (MWD): móveis",
      "name": "Metropolitan Warehouse & Delivery",
      "color": "#0062b7"
    },
    {
      "id": "topyou",
      "catalog": "topyou",
      "slug": "topyou-rastreio",
      "title": "TopYou rastreio: número TYZR…YQ, estados e entrega",
      "name": "TopYou",
      "color": "#045cd7"
    },
    {
      "id": "byhd",
      "catalog": "byhd",
      "slug": "rastreio-byhd",
      "title": "Rastreio BYHD: números BYHD, estados e quem entrega",
      "name": "BYHD",
      "color": "#084282"
    },
    {
      "id": "zto-international",
      "catalog": "zto-international",
      "slug": "seguimento-zto-international",
      "title": "Seguimento ZTO International: números 771, estados e ajuda",
      "name": "ZTO International",
      "color": "#3a8eff"
    },
    {
      "id": "mayiyinfu",
      "catalog": "mayiyinfu",
      "slug": "seguimento-mayi-yinfu",
      "title": "Seguimento Mayi Yinfu: números MY, voos e entrega DHL",
      "name": "Mayi Yinfu",
      "color": "#000000"
    },
    {
      "id": "jrt",
      "catalog": "jrt",
      "slug": "jrt-rastreio",
      "title": "Rastreio JRT: número JRT…MM, voos, alfândega e DHL",
      "name": "JRT",
      "color": "#023053"
    },
    {
      "id": "thhy",
      "catalog": "thhy",
      "slug": "seguimento-tianhui-hengyuan",
      "title": "Seguimento Tianhui Hengyuan (THHY): números AT e registos",
      "name": "Tianhui Hengyuan",
      "color": "#00a2e9"
    },
    {
      "id": "js-express",
      "catalog": "js-express",
      "slug": "js-express-rastreio",
      "title": "JS Express rastreio: número JSE…YQ, estados e entrega",
      "name": "JS Express",
      "color": "#fe9b00"
    },
    {
      "id": "wanbexpress",
      "catalog": "wanbexpress",
      "slug": "rastreio-wanbexpress",
      "title": "Rastreio WanbExpress: números WNB…YQ, estados e entrega",
      "name": "WanbExpress",
      "color": "#da381b"
    },
    {
      "id": "gzyl",
      "catalog": "gzyl",
      "slug": "gzyl-rastreio",
      "title": "Rastreio GZYL: número YL…YQ, estados e a quem perguntar",
      "name": "GZYL",
      "color": "#4e79e1"
    },
    {
      "id": "shouwu",
      "catalog": "shouwu",
      "slug": "shouwu-rastreio",
      "title": "Rastreio Shouwu: número SWECN…YQ, estados e entrega",
      "name": "Shouwu International",
      "color": "#be2c33"
    },
    {
      "id": "geodis-pl",
      "catalog": "geodis-pl",
      "slug": "geodis-polonia-rastreio",
      "title": "Rastreio GEODIS Polónia: número PL, estados e entrega",
      "name": "GEODIS Poland",
      "color": "#3100e6"
    },
    {
      "id": "sending",
      "catalog": "sending",
      "slug": "seguimento-sending",
      "title": "Seguimento Sending: número, estados e contacto telefónico",
      "name": "Sending",
      "color": "#e52021"
    },
    {
      "id": "ywe",
      "catalog": "ywe",
      "slug": "seguimento-ywe",
      "title": "Seguimento YWE (YanwenExpress): número, estados e ajuda",
      "name": "YWE",
      "color": "#055aa1"
    },
    {
      "id": "roadie",
      "catalog": "roadie",
      "slug": "seguimento-roadie",
      "title": "Seguimento Roadie: números 1R, estados e fotos de entrega",
      "name": "Roadie",
      "color": "#76cccd"
    },
    {
      "id": "ecms-express",
      "catalog": "ecms-express",
      "slug": "seguimento-ecms-express",
      "title": "Seguimento ECMS Express: códigos de 16 caracteres e entrega",
      "name": "ECMS Express",
      "color": "#12823d"
    },
    {
      "id": "chunghwa-post",
      "catalog": "chunghwa-post",
      "slug": "chunghwa-post-tracking",
      "title": "Seguimento Chunghwa Post: código TW, estados e alfândega",
      "name": "Chunghwa Post",
      "color": "#e8171f"
    },
    {
      "id": "raben",
      "catalog": "raben",
      "slug": "seguimento-raben",
      "title": "Seguimento Raben: número de envio, estados e entrega",
      "name": "Raben",
      "color": "#df0020"
    },
    {
      "id": "jd-logistics",
      "catalog": "jd-logistics",
      "slug": "seguimento-jd-logistics",
      "title": "Seguimento JD Logistics: códigos JD, estados e contactos",
      "name": "JD Logistics",
      "color": "#e1251b"
    },
    {
      "id": "singapore-post",
      "catalog": "singapore-post",
      "slug": "seguimento-singapore-post",
      "title": "Seguimento Singapore Post (SingPost): números e estados",
      "name": "Singapore Post",
      "color": "#2e5da8"
    },
    {
      "id": "zto",
      "catalog": "zto",
      "slug": "seguimento-zto-express",
      "title": "Seguimento ZTO Express: números 中通快递, estados e apoio",
      "name": "ZTO Express",
      "color": "#0279ff"
    },
    {
      "id": "cne",
      "catalog": "cne",
      "slug": "seguimento-cne-express",
      "title": "Seguimento CNE Express: números 3A5V, estados e entrega",
      "name": "CNE Express",
      "color": "#e83828"
    },
    {
      "id": "japan-post",
      "catalog": "japan-post",
      "slug": "seguimento-japan-post",
      "title": "Seguimento Japan Post: números EMS e estados explicados",
      "name": "Japan Post",
      "color": "#cc0000"
    },
    {
      "id": "russian-post",
      "catalog": "russian-post",
      "slug": "seguimento-russian-post",
      "title": "Seguimento Russian Post (Почта России): números e estados",
      "name": "Russian Post",
      "color": "#1928dc"
    },
    {
      "id": "huahan",
      "catalog": "huahan",
      "slug": "seguimento-huahan-logistics",
      "title": "Seguimento HuaHan Logistics: código HHW…YQ e entrega",
      "name": "HuaHan Logistics",
      "color": "#0069e8"
    },
    {
      "id": "epost-global",
      "catalog": "epost-global",
      "slug": "seguimento-epost-global",
      "title": "Seguimento ePost Global: números EPG, estados e entrega",
      "name": "ePost Global",
      "color": "#27967a"
    },
    {
      "id": "gls-at",
      "catalog": "gls-at",
      "slug": "seguimento-gls-austria",
      "title": "Seguimento GLS Austria: estados, Parcel Shops e contacto",
      "name": "GLS Austria",
      "color": "#ffdd00"
    },
    {
      "id": "india-post",
      "catalog": "india-post",
      "slug": "seguimento-india-post",
      "title": "Seguimento India Post e Speed Post: números e estados",
      "name": "India Post",
      "color": "#b82025"
    },
    {
      "id": "morning-global",
      "catalog": "morning-global",
      "slug": "seguimento-morning-global",
      "title": "Seguimento Morning Global: números BR…MG, estados e apoio",
      "name": "Morning Global",
      "color": "#050059"
    },
    {
      "id": "border-express",
      "catalog": "border-express",
      "slug": "seguimento-border-express",
      "title": "Seguimento Border Express (BEX): números e estados",
      "name": "Border Express",
      "color": "#cc092f"
    },
    {
      "id": "delhivery",
      "catalog": "delhivery",
      "slug": "seguimento-delhivery",
      "title": "Seguimento Delhivery: números AWB, estados e apoio",
      "name": "Delhivery",
      "color": "#ed4136"
    },
    {
      "id": "aras-kargo",
      "catalog": "aras-kargo",
      "slug": "rastreio-aras-kargo",
      "title": "Rastreio Aras Kargo: números e estados em português",
      "name": "Aras Kargo",
      "color": "#dc0812"
    },
    {
      "id": "emirates-post",
      "catalog": "emirates-post",
      "slug": "seguimento-emirates-post",
      "title": "Seguimento Emirates Post: números AE e estados",
      "name": "Emirates Post",
      "color": "#004a98"
    },
    {
      "id": "blue-dart",
      "catalog": "blue-dart",
      "slug": "seguimento-blue-dart",
      "title": "Seguimento Blue Dart: números waybill e estados explicados",
      "name": "Blue Dart",
      "color": "#0059a9"
    },
    {
      "id": "ekart",
      "catalog": "ekart",
      "slug": "seguimento-ekart",
      "title": "Seguimento Ekart: códigos ID, estados InscannedAtDH e RTO",
      "name": "Ekart",
      "color": "#0074D9"
    },
    {
      "id": "j-and-t-middle-east",
      "catalog": "j-and-t-middle-east",
      "slug": "seguimento-jt-express-middle-east",
      "title": "Seguimento J&T Express Middle East: códigos JTE e estados",
      "name": "J&T Express Middle East",
      "color": "#e60000"
    },
    {
      "id": "dpd-ro",
      "catalog": "dpd-ro",
      "slug": "seguimento-dpd-romania",
      "title": "Seguimento DPD Romania: estados, Smart Control e contacto",
      "name": "DPD Romania",
      "color": "#dc0032"
    },
    {
      "id": "globalpost",
      "catalog": "globalpost",
      "slug": "seguimento-globalpost",
      "title": "Seguimento GlobalPost: números GP, estados e entrega",
      "name": "GlobalPost",
      "color": "#11003a"
    },
    {
      "id": "cdek",
      "catalog": "cdek",
      "slug": "rastreio-cdek",
      "title": "Rastreio CDEK (СДЭК): números e estados em português",
      "name": "CDEK",
      "color": "#00b33c"
    },
    {
      "id": "forza-delivery",
      "catalog": "forza-delivery",
      "slug": "seguimento-forza-delivery",
      "title": "Seguimento Forza Delivery: números de guía, estados, ajuda",
      "name": "Forza Delivery Express",
      "color": "#ea5c1d"
    },
    {
      "id": "quantium-solutions",
      "catalog": "quantium-solutions",
      "slug": "seguimento-quantium-solutions",
      "title": "Seguimento Quantium Solutions: números, estados e apoio",
      "name": "Quantium Solutions",
      "color": "#00474f"
    },
    {
      "id": "correos-chile",
      "catalog": "correos-chile",
      "slug": "seguimento-correos-chile",
      "title": "Seguimento Correos de Chile: números e estados",
      "name": "Correos de Chile",
      "color": "#d9232d"
    },
    {
      "id": "posta-shqiptare",
      "catalog": "posta-shqiptare",
      "slug": "seguimento-posta-shqiptare",
      "title": "Seguimento Posta Shqiptare: códigos AL, estados e alfândega",
      "name": "Posta Shqiptare",
      "color": "#1a3292"
    },
    {
      "id": "dsv",
      "catalog": "dsv",
      "slug": "seguimento-dsv",
      "title": "Seguimento DSV: números, estados e DB Schenker",
      "name": "DSV",
      "color": "#002664"
    },
    {
      "id": "pin-ag",
      "catalog": "pin-ag",
      "slug": "seguimento-pin-ag",
      "title": "Seguimento PIN AG: correio registado, encomendas e depósito",
      "name": "PIN AG",
      "color": "#003366"
    },
    {
      "id": "pitney-bowes",
      "catalog": "pitney-bowes",
      "slug": "seguimento-pitney-bowes",
      "title": "Seguimento Pitney Bowes: códigos UPAA, UPBE e PBX",
      "name": "Pitney Bowes",
      "color": "#3e53a4"
    },
    {
      "id": "rl-carriers",
      "catalog": "rl-carriers",
      "slug": "seguimento-rl-carriers",
      "title": "Seguimento R+L Carriers: número PRO, estados e reclamações",
      "name": "R+L Carriers",
      "color": "#006647"
    },
    {
      "id": "nox",
      "catalog": "nox",
      "slug": "seguimento-nox",
      "title": "Seguimento nox NachtExpress: estados, depósito e contacto",
      "name": "nox",
      "color": "#fadb00"
    },
    {
      "id": "far-international",
      "catalog": "far-international",
      "slug": "rastreio-far-international",
      "title": "Rastreio FAR International: números, estados e contacto",
      "name": "FAR International",
      "color": "#003b75"
    },
    {
      "id": "jifan",
      "catalog": "jifan",
      "slug": "seguimento-jifan",
      "title": "Seguimento Jifan: números JFGJ, estados e hora de Pequim",
      "name": "Jifan",
      "color": "#c81011"
    },
    {
      "id": "posta-romana",
      "catalog": "posta-romana",
      "slug": "seguimento-posta-romana",
      "title": "Seguimento Poșta Română: códigos RO, estados e alfândega",
      "name": "Poșta Română",
      "color": "#ea0029"
    },
    {
      "id": "dpd-nl",
      "catalog": "dpd-nl",
      "slug": "seguimento-dpd-paises-baixos",
      "title": "Seguimento DPD Países Baixos: estados, entrega e contacto",
      "name": "DPD Netherlands",
      "color": "#dc0032"
    },
    {
      "id": "dpd-at",
      "catalog": "dpd-at",
      "slug": "seguimento-dpd-austria",
      "title": "Seguimento DPD Austria: estados, Pickup e contacto",
      "name": "DPD Austria",
      "color": "#dc0032"
    },
    {
      "id": "ozon-logistics",
      "catalog": "ozon-logistics",
      "slug": "rastreio-ozon",
      "title": "Rastreio Ozon: números de encomenda e estados em português",
      "name": "Ozon",
      "color": "#005bff"
    },
    {
      "id": "citymail",
      "catalog": "citymail",
      "slug": "seguimento-citymail",
      "title": "Seguimento CityMail: números, estados e entrega Suécia",
      "name": "CityMail",
      "color": "#fc4c02"
    },
    {
      "id": "dayone",
      "catalog": "dayone",
      "slug": "seguimento-dayone",
      "title": "Seguimento DayOne: números DOT, estados e quem entrega",
      "name": "DayOne",
      "color": "#e86d1c"
    },
    {
      "id": "dpd-be",
      "catalog": "dpd-be",
      "slug": "seguimento-dpd-belgium",
      "title": "Seguimento DPD Belgium: estados, entrega e contacto",
      "name": "DPD Belgium",
      "color": "#dc0032"
    },
    {
      "id": "packlink",
      "catalog": "packlink",
      "slug": "seguimento-packlink",
      "title": "Seguimento Packlink: referência, estados e contactos",
      "name": "Packlink",
      "color": "#ff4f00"
    },
    {
      "id": "sto",
      "catalog": "sto",
      "slug": "seguimento-sto-express",
      "title": "Seguimento STO Express: números 申通快递, estados e contacto",
      "name": "STO Express",
      "color": "#ff6700"
    },
    {
      "id": "j-and-t-cn",
      "catalog": "j-and-t-cn",
      "slug": "seguimento-jt-express-china",
      "title": "Seguimento J&T Express China: números JT, estados e ajuda",
      "name": "J&T Express China",
      "color": "#e62019"
    },
    {
      "id": "tforce-freight",
      "catalog": "tforce-freight",
      "slug": "seguimento-tforce-freight",
      "title": "Seguimento TForce Freight: números PRO e reclamações",
      "name": "TForce Freight",
      "color": "#0053a1"
    },
    {
      "id": "andreani",
      "catalog": "andreani",
      "slug": "seguimento-andreani",
      "title": "Seguimento Andreani: números e estados na Argentina",
      "name": "Andreani",
      "color": "#d71920"
    },
    {
      "id": "mainfreight",
      "catalog": "mainfreight",
      "slug": "rastreio-mainfreight",
      "title": "Rastreio Mainfreight: números de carga e estados",
      "name": "Mainfreight",
      "color": "#005eb8"
    },
    {
      "id": "spx-sg",
      "catalog": "spx-sg",
      "slug": "seguimento-spx-express-singapore",
      "title": "Seguimento SPX Express Singapura: Shopee e estados",
      "name": "SPX Express Singapore",
      "color": "#ee4d2d"
    },
    {
      "id": "dai-post",
      "catalog": "dai-post",
      "slug": "seguimento-dai-post",
      "title": "Seguimento DAI Post: números, estados e ajuda de entrega",
      "name": "DAI Post",
      "color": "#008faf"
    },
    {
      "id": "eurodis",
      "catalog": "eurodis",
      "slug": "seguimento-eurodis",
      "title": "Seguimento EURODIS: números de envio e parceiros locais",
      "name": "EURODIS",
      "color": "#0b418b"
    },
    {
      "id": "chronodiali",
      "catalog": "chronodiali",
      "slug": "seguimento-chronodiali",
      "title": "Seguimento Chronodiali: números CHPU, estados e contacto",
      "name": "Chronodiali",
      "color": "#009adf"
    },
    {
      "id": "17feia",
      "catalog": "17feia",
      "slug": "rastreio-17feia",
      "title": "Rastreio 17FEIA: números FEI…YQ, estados e entrega",
      "name": "17FEIA",
      "color": "#00a9e4"
    },
    {
      "id": "tsjy",
      "catalog": "tsjy",
      "slug": "seguimento-tsjy",
      "title": "Seguimento TSJY (通世集运): números de pedido e estados",
      "name": "TSJY",
      "color": "#4042e2"
    },
    {
      "id": "stone3pl",
      "catalog": "stone3pl",
      "slug": "seguimento-stone3pl",
      "title": "Seguimento Stone3PL: números, estados e contacto",
      "name": "Stone3PL",
      "color": "#003d98"
    },
    {
      "id": "j-and-t-uk",
      "catalog": "j-and-t-uk",
      "slug": "rastreio-jt-express-uk",
      "title": "Rastreio J&T Express UK: números JTUK e estados",
      "name": "J&T Express UK",
      "color": "#e60000"
    },
    {
      "id": "m-one",
      "catalog": "m-one",
      "slug": "seguimento-m-one",
      "title": "Seguimento M-ONE (Milšped ONE): estados, entrega e ajuda",
      "name": "M-ONE",
      "color": "#024da0"
    },
    {
      "id": "early-bird",
      "catalog": "early-bird",
      "slug": "seguimento-early-bird",
      "title": "Seguimento Early Bird: estados, entregas e cacifos",
      "name": "Early Bird",
      "color": "#dd7f4a"
    },
    {
      "id": "xinghe-freight",
      "catalog": "xinghe-freight",
      "slug": "xinghe-freight-rastreio",
      "title": "Rastreio Xinghe Freight: números GZXH, estados e entrega",
      "name": "Xinghe Freight",
      "color": "#0e3a6b"
    },
    {
      "id": "cvc",
      "catalog": "cvc",
      "slug": "seguimento-cvc",
      "title": "Seguimento CVC (Си Ви Си): seguir encomenda na Bulgária",
      "name": "CVC",
      "color": "#652b9e"
    },
    {
      "id": "transpress",
      "catalog": "transpress",
      "slug": "seguimento-transpress",
      "title": "Seguimento Transpress (Транспрес): encomendas na Bulgária",
      "name": "Transpress",
      "color": "#1e6ee2"
    },
    {
      "id": "gls-us",
      "catalog": "gls-us",
      "slug": "seguimento-gls-us",
      "title": "Seguimento GLS US (GSO): números, estados e entregas",
      "name": "GLS US",
      "color": "#ffdd00"
    },
    {
      "id": "sxw-express",
      "catalog": "sxw-express",
      "slug": "seguimento-sxw-express",
      "title": "Seguimento SXW Express: números SXW, estados e entrega",
      "name": "SXW Express",
      "color": "#003876"
    },
    {
      "id": "xuchuan-logistics",
      "catalog": "xuchuan-logistics",
      "slug": "seguimento-xuchuan-logistics",
      "title": "Seguimento Xuchuan Logistics: números XC, entrega e ajuda",
      "name": "Xuchuan International Logistics",
      "color": "#0a2463"
    },
    {
      "id": "samus",
      "catalog": "samus",
      "slug": "seguimento-samus",
      "title": "Seguimento SAMUS: números, estados e recolha em Vilnius",
      "name": "SAMUS",
      "color": "#e50079"
    },
    {
      "id": "siuntos-autobusais",
      "catalog": "siuntos-autobusais",
      "slug": "seguimento-siuntos-autobusais",
      "title": "Seguimento Siuntos autobusais: encomendas em autocarro",
      "name": "Siuntos autobusais",
      "color": "#00843d"
    },
    {
      "id": "zipasgo",
      "catalog": "zipasgo",
      "slug": "seguimento-zipasgo",
      "title": "Seguimento ZipasGo: Parcel Stars, parceiros e ajuda",
      "name": "ZipasGo",
      "color": "#ffdb1c"
    },
    {
      "id": "dk-logistics-ee",
      "catalog": "dk-logistics-ee",
      "slug": "seguimento-dk-logistics",
      "title": "Seguimento DK Logistics: guia, entregas e contacto",
      "name": "DK Logistics",
      "color": "#f58428"
    },
    {
      "id": "jet-express-ee",
      "catalog": "jet-express-ee",
      "slug": "seguimento-jet-express",
      "title": "Seguimento Jet Express: número de pedido, entrega, ajuda",
      "name": "Jet Express",
      "color": "#d61b22"
    },
    {
      "id": "fan-courier-international",
      "catalog": "fan-courier-international",
      "slug": "seguimento-fan-courier-international",
      "title": "Seguimento FAN Courier International: AWB e estados",
      "name": "FAN Courier International",
      "color": "#2daae1"
    },
    {
      "id": "j-and-t-br",
      "catalog": "j-and-t-br",
      "slug": "rastreio-jt-express-brazil",
      "title": "Rastreio J&T Express Brazil: estado, alfândega e contacto",
      "name": "J&T Express Brazil",
      "color": "#e60000"
    },
    {
      "id": "anjun",
      "catalog": "anjun",
      "slug": "rastreio-anjun-logistics",
      "title": "Rastreio Anjun Logistics: estados China-Brasil e SAC",
      "name": "Anjun Logistics",
      "color": "#009944"
    },
    {
      "id": "xyl-ships",
      "catalog": "xyl-ships",
      "slug": "rastreio-xyl-ships",
      "title": "Rastreio XYL-Ships: códigos NUDE e XLWL, estados e ajuda",
      "name": "XYL-Ships",
      "color": "#b13c3c"
    },
    {
      "id": "jadlog",
      "catalog": "jadlog",
      "slug": "rastreio-jadlog",
      "title": "Rastreio Jadlog: códigos de 14 dígitos, Pickup e Predict",
      "name": "Jadlog",
      "color": "#dc0032"
    },
    {
      "id": "gls-pl",
      "catalog": "gls-pl",
      "slug": "rastreio-gls-poland",
      "title": "Rastreio GLS Poland: estados, GLS Points, cacifos e ajuda",
      "name": "GLS Poland",
      "color": "#ffdd00"
    },
    {
      "id": "j-and-t-mx",
      "catalog": "j-and-t-mx",
      "slug": "rastreio-jt-express-mexico",
      "title": "Rastreio J&T Express Mexico: códigos JMX, telefone e ajuda",
      "name": "J&T Express Mexico",
      "color": "#e60012"
    },
    {
      "id": "bulgarian-posts",
      "catalog": "bulgarian-posts",
      "slug": "rastreio-bulgarian-posts",
      "title": "Rastreio Bulgarian Posts: códigos PS/BG, alfândega e ajuda",
      "name": "Bulgarian Posts",
      "color": "#ffd100"
    },
    {
      "id": "haypost",
      "catalog": "haypost",
      "slug": "rastreio-haypost",
      "title": "Rastreio HayPost: códigos AM, estados e contactos",
      "name": "HayPost",
      "color": "#fa4616"
    },
    {
      "id": "360lion",
      "catalog": "360lion",
      "slug": "rastreio-360lion",
      "title": "Rastreio 360Lion: códigos WSH e AR, estados e contactos",
      "name": "360Lion",
      "color": "#cf5d15"
    },
    {
      "id": "paquetexpress",
      "catalog": "paquetexpress",
      "slug": "rastreio-paquetexpress",
      "title": "Rastreio Paquetexpress: guías, Ocurre e reclamações",
      "name": "Paquetexpress",
      "color": "#002a52"
    },
    {
      "id": "ydh",
      "catalog": "ydh",
      "slug": "rastreio-ydh",
      "title": "Rastreio YDH: códigos YDH...YQ e AZ, entregas e ajuda",
      "name": "YDH",
      "color": "#015db9"
    },
    {
      "id": "a-tu-hora-express",
      "catalog": "a-tu-hora-express",
      "slug": "rastreio-a-tu-hora-express",
      "title": "Rastreio A Tu Hora Express: códigos, código postal e ajuda",
      "name": "A Tu Hora Express",
      "color": "#1d2a84"
    },
    {
      "id": "dtdc",
      "catalog": "dtdc",
      "slug": "rastreio-dtdc",
      "title": "Rastreio DTDC: formatos AWB, estados RTO e contactos",
      "name": "DTDC",
      "color": "#0d237d"
    },
    {
      "id": "gofo-es",
      "catalog": "gofo-es",
      "slug": "rastreio-gofo-spain",
      "title": "Rastreio GOFO Spain: códigos GFES e CIES, 3 tentativas",
      "name": "GOFO Spain",
      "color": "#fc4c02"
    },
    {
      "id": "korea-post",
      "catalog": "korea-post",
      "slug": "rastreio-korea-post",
      "title": "Rastreio Korea Post: códigos EMS KR, 13 dígitos e ajuda",
      "name": "Korea Post",
      "color": "#da291c"
    },
    {
      "id": "nova-global",
      "catalog": "nova-global",
      "slug": "rastreio-nova-global",
      "title": "Rastreio Nova Global: códigos NPG e NGUS e alfândega",
      "name": "Nova Global",
      "color": "#da291c"
    },
    {
      "id": "xlcourier",
      "catalog": "xlcourier",
      "slug": "rastreio-xlcourier",
      "title": "Rastreio XLCourier: códigos XLC, volumes pesados UK",
      "name": "XLCourier",
      "color": "#044ad6"
    },
    {
      "id": "estafeta",
      "catalog": "estafeta",
      "slug": "rastreio-estafeta",
      "title": "Rastreio Estafeta: códigos 10 e 22 car., 2 tentativas",
      "name": "Estafeta",
      "color": "#e30613"
    },
    {
      "id": "gls-hu",
      "catalog": "gls-hu",
      "slug": "rastreio-gls-hungary",
      "title": "Rastreio GLS Hungary: números, cacifos e contactos",
      "name": "GLS Hungary",
      "color": "#ffdd00"
    },
    {
      "id": "dhl-freight",
      "catalog": "dhl-freight",
      "slug": "rastreio-dhl-freight",
      "title": "Rastreio DHL Freight: códigos ActiveTracing e ajuda",
      "name": "DHL Freight",
      "color": "#ffcc00"
    },
    {
      "id": "anserx",
      "catalog": "anserx",
      "slug": "rastreio-anserx",
      "title": "Rastreio AnserX: códigos BTYCN...YQ, entrega local e ajuda",
      "name": "AnserX",
      "color": "#2478be"
    },
    {
      "id": "bjs-home-delivery",
      "catalog": "bjs-home-delivery",
      "slug": "rastreio-bjs-home-delivery",
      "title": "Rastreio BJS Home Delivery: códigos B, janela 3 h e ajuda",
      "name": "BJS Home Delivery",
      "color": "#feba30"
    },
    {
      "id": "chukou1",
      "catalog": "chukou1",
      "slug": "rastreio-chukou1",
      "title": "Rastreio Chukou1: códigos EC-FirstClass, entrega e ajuda",
      "name": "Chukou1",
      "color": "#0b4da2"
    },
    {
      "id": "cj-packet",
      "catalog": "cj-packet",
      "slug": "rastreio-cj-packet",
      "title": "Rastreio CJ Packet: códigos CJP...YQ, entrega e contactos",
      "name": "CJ Packet",
      "color": "#ff7700"
    },
    {
      "id": "cmt-express",
      "catalog": "cmt-express",
      "slug": "rastreio-cmt-express",
      "title": "Rastreio CMT Express: códigos CMT, hub Shenzhen e ajuda",
      "name": "CMT Express",
      "color": "#ed010b"
    },
    {
      "id": "correos-de-mexico",
      "catalog": "correos-de-mexico",
      "slug": "rastreio-correos-de-mexico",
      "title": "Rastreio Correos de México: códigos S10 MX e reclamações",
      "name": "Correos de México",
      "color": "#ed007d"
    },
    {
      "id": "dobropost",
      "catalog": "dobropost",
      "slug": "rastreio-dobropost",
      "title": "Rastreio DobroPost: códigos DBRPST, estados e hubs China",
      "name": "DobroPost",
      "color": "#ffbe2e"
    },
    {
      "id": "ems",
      "catalog": "ems",
      "slug": "rastreio-ems",
      "title": "Rastreio EMS: códigos S10, estados e apoio postal",
      "name": "EMS",
      "color": "#003b7a"
    },
    {
      "id": "jiayang",
      "catalog": "jiayang",
      "slug": "rastreio-jiayang",
      "title": "Rastreio Jiayang International: códigos JYGJ e apoio",
      "name": "Jiayang International",
      "color": "#105bcc"
    },
    {
      "id": "jumppoint-international",
      "catalog": "jumppoint-international",
      "slug": "rastreio-jumppoint-international",
      "title": "Rastreio Jumppoint International: códigos JP e apoio",
      "name": "Jumppoint International Express",
      "color": "#0f4ae1"
    },
    {
      "id": "kuayuan-express",
      "catalog": "kuayuan-express",
      "slug": "rastreio-kuayuan-express",
      "title": "Rastreio Kuayuan Express: códigos, entregas locais e apoio",
      "name": "Kuayuan Express",
      "color": "#084b86"
    },
    {
      "id": "liccardi",
      "catalog": "liccardi",
      "slug": "rastreio-liccardi",
      "title": "Rastreio Liccardi Express Courier: códigos LDV e apoio",
      "name": "Liccardi Express Courier",
      "color": "#014895"
    },
    {
      "id": "maersk-ecommerce",
      "catalog": "maersk-ecommerce",
      "slug": "rastreio-maersk-ecommerce",
      "title": "Rastreio Maersk eCommerce: códigos 1M, estados e apoio",
      "name": "Maersk eCommerce",
      "color": "#42b0d5"
    },
    {
      "id": "pdu",
      "catalog": "pdu",
      "slug": "rastreio-pdu",
      "title": "Rastreio PDU: códigos Parcel Delivery Union e apoio",
      "name": "PDU",
      "color": "#005cfe"
    },
    {
      "id": "post-haste",
      "catalog": "post-haste",
      "slug": "rastreio-post-haste",
      "title": "Rastreio Post Haste: códigos NZ, estados e apoio",
      "name": "Post Haste",
      "color": "#24b2d8"
    },
    {
      "id": "redur",
      "catalog": "redur",
      "slug": "rastreio-redur",
      "title": "Rastreio Redur: códigos de 14 dígitos, Espanha e apoio",
      "name": "Redur",
      "color": "#0c4c49"
    },
    {
      "id": "sengi-express",
      "catalog": "sengi-express",
      "slug": "rastreio-sengi-express",
      "title": "Rastreio Sengi Express: códigos China–Itália e apoio",
      "name": "Sengi Express",
      "color": "#1a44a4"
    },
    {
      "id": "serpost",
      "catalog": "serpost",
      "slug": "rastreio-serpost",
      "title": "Rastreio Serpost: códigos S10 do Peru, alfândega e apoio",
      "name": "Serpost",
      "color": "#00308a"
    },
    {
      "id": "skypostal",
      "catalog": "skypostal",
      "slug": "rastreio-skypostal",
      "title": "Rastreio SkyPostal: códigos SKAMZUS, SP e apoio",
      "name": "SkyPostal",
      "color": "#088aea"
    },
    {
      "id": "thailand-post",
      "catalog": "thailand-post",
      "slug": "rastreio-thailand-post",
      "title": "Rastreio Thailand Post: códigos TH S10, EMS e 1545",
      "name": "Thailand Post",
      "color": "#ef172f"
    },
    {
      "id": "yunda",
      "catalog": "yunda",
      "slug": "rastreio-yunda",
      "title": "Rastreio Yunda Express: códigos chineses, estados e 95546",
      "name": "Yunda Express",
      "color": "#ffce00"
    },
    {
      "id": "onlog-express",
      "catalog": "onlog-express",
      "slug": "rastreio-onlog-express",
      "title": "Rastreio Onlog Express: códigos AA, estados e apoio",
      "name": "Onlog Express",
      "color": "#1a345d"
    },
    {
      "id": "a-duie-pyle",
      "catalog": "a-duie-pyle",
      "slug": "rastreio-a-duie-pyle",
      "title": "Rastreio A. Duie Pyle: números PRO, LTL e apoio",
      "name": "A. Duie Pyle",
      "color": "#f27629"
    },
    {
      "id": "buffalo-ex",
      "catalog": "buffalo-ex",
      "slug": "rastreio-buffalo-ex",
      "title": "Rastreio BUFFALO: códigos BUFZA, alfândega África do Sul",
      "name": "BUFFALO",
      "color": "#bc8c4d"
    },
    {
      "id": "chit-chats",
      "catalog": "chit-chats",
      "slug": "rastreio-chit-chats",
      "title": "Rastreio Chit Chats: códigos 10 car. e entrega USPS/Canadá",
      "name": "Chit Chats",
      "color": "#db2b35"
    },
    {
      "id": "dpd-hu",
      "catalog": "dpd-hu",
      "slug": "rastreio-dpd-hu",
      "title": "Rastreio DPD Hungary: códigos 14 dígitos, FOXPOST e apoio",
      "name": "DPD Hungary",
      "color": "#dc0032"
    },
    {
      "id": "e24metro-ca",
      "catalog": "e24metro-ca",
      "slug": "rastreio-e24metro-ca",
      "title": "Rastreio E24 (Metro Supply Chain): Canadá e contactos",
      "name": "E24",
      "color": "#545859"
    },
    {
      "id": "go-express",
      "catalog": "go-express",
      "slug": "rastreio-go-express",
      "title": "Rastreio GO! Express: guias de 12 dígitos, estados e apoio",
      "name": "GO! Express",
      "color": "#b9242b"
    },
    {
      "id": "kuehne-nagel",
      "catalog": "kuehne-nagel",
      "slug": "rastreio-kuehne-nagel",
      "title": "Rastreio Kuehne+Nagel: números, estados e entregas",
      "name": "Kuehne+Nagel",
      "color": "#003369"
    },
    {
      "id": "memphis-courier",
      "catalog": "memphis-courier",
      "slug": "rastreio-memphis-courier",
      "title": "Rastreio Memphis Courier: códigos M-MIA, alfândega e apoio",
      "name": "Memphis Courier",
      "color": "#1f3c5b"
    },
    {
      "id": "oca",
      "catalog": "oca",
      "slug": "rastreio-oca",
      "title": "Rastreio OCA: números de 19 dígitos, estados e agências",
      "name": "OCA",
      "color": "#592c82"
    },
    {
      "id": "pigeonship",
      "catalog": "pigeonship",
      "slug": "rastreio-pigeonship",
      "title": "Rastreio PigeonShip: códigos, estados e entrega",
      "name": "PigeonShip",
      "color": "#f2b02c"
    },
    {
      "id": "seko",
      "catalog": "seko",
      "slug": "rastreio-seko-logistics",
      "title": "Rastreio SEKO Logistics: números, OmniParcel e entrega",
      "name": "SEKO Logistics",
      "color": "#ef3e42"
    },
    {
      "id": "bfe-express",
      "catalog": "bfe-express",
      "slug": "rastreio-bfe-express",
      "title": "Rastreio BFE Express: códigos CK e WYB, estados e apoio",
      "name": "BFE Express",
      "color": "#122a88"
    },
    {
      "id": "pasarex",
      "catalog": "pasarex",
      "slug": "rastreio-pasarex",
      "title": "Rastreio PasarEX: códigos Amazon AMZPSR, alfândega e apoio",
      "name": "PasarEX",
      "color": "#124381"
    },
    {
      "id": "spee-dee",
      "catalog": "spee-dee",
      "slug": "rastreio-spee-dee",
      "title": "Rastreio Spee-Dee Delivery: números SP, estados e apoio",
      "name": "Spee-Dee",
      "color": "#ff4734"
    },
    {
      "id": "tnt-au",
      "catalog": "tnt-au",
      "slug": "rastreio-tnt-australia",
      "title": "Rastreio TNT Australia: números, estados e apoio",
      "name": "TNT Australia",
      "color": "#ff6600"
    },
    {
      "id": "wedo",
      "catalog": "wedo",
      "slug": "rastreio-one-by-allegro-cz",
      "title": "Rastreio One by Allegro CZ (WE|DO): códigos e levantamento",
      "name": "One by Allegro CZ",
      "color": "#006d57"
    },
    {
      "id": "5post",
      "catalog": "5post",
      "slug": "rastreio-5post",
      "title": "Rastreio 5Post: números, levantamento Pyaterochka e ajuda",
      "name": "5Post",
      "color": "#64b441"
    },
    {
      "id": "shipter",
      "catalog": "shipter",
      "slug": "rastreio-shipter",
      "title": "Rastreio Shipter: números H3 da Coreia do Sul e estados",
      "name": "Shipter",
      "color": "#005eff"
    }
  ],
  "pt-br": [
    {
      "id": "colis-prive",
      "catalog": "colis-prive",
      "slug": "rastreamento-colis-prive",
      "title": "Rastreamento Colis Privé: código, CEP e status em francês",
      "name": "Colis Privé",
      "color": "#aa78ff"
    },
    {
      "id": "brt",
      "catalog": "brt",
      "slug": "rastreamento-brt",
      "title": "Rastreamento BRT: códigos, status e entregas na Itália",
      "name": "BRT",
      "color": "#dc0032"
    },
    {
      "id": "correos-express",
      "catalog": "correos-express",
      "slug": "rastreamento-correos-express",
      "title": "Rastreamento Correos Express: códigos, status e contato",
      "name": "Correos Express",
      "color": "#ffcd00"
    },
    {
      "id": "nacex",
      "catalog": "nacex",
      "slug": "rastreamento-nacex",
      "title": "Rastreamento NACEX: código com barra, status e contato",
      "name": "NACEX",
      "color": "#fe5000"
    },
    {
      "id": "yunexpress",
      "catalog": "yunexpress",
      "slug": "rastreamento-yunexpress",
      "title": "Rastreamento YunExpress: código YT, status e entrega",
      "name": "YunExpress",
      "color": "#008284"
    },
    {
      "id": "cainiao",
      "catalog": "aliexpress",
      "slug": "rastreamento-cainiao",
      "title": "Rastreamento Cainiao (AliExpress): códigos, status e ajuda",
      "name": "Cainiao",
      "color": "#e62e04"
    },
    {
      "id": "mondial-relay",
      "catalog": "mondial-relay",
      "slug": "rastreamento-mondial-relay",
      "title": "Rastreamento Mondial Relay: status, retirada e contato",
      "name": "Mondial Relay",
      "color": "#ef3026"
    },
    {
      "id": "gls-de",
      "catalog": "gls-de",
      "slug": "rastreamento-gls-alemanha",
      "title": "Rastreamento GLS Alemanha: status, ParcelShops e contato",
      "name": "GLS Germany",
      "color": "#ffdd00"
    },
    {
      "id": "dpd-de",
      "catalog": "dpd-de",
      "slug": "rastreamento-dpd-alemanha",
      "title": "Rastreamento DPD Alemanha: status, janelas e contato",
      "name": "DPD Germany",
      "color": "#dc0032"
    },
    {
      "id": "hermes-de",
      "catalog": "hermes-de",
      "slug": "rastreamento-hermes-alemanha",
      "title": "Rastreamento Hermes Alemanha: status em alemão e entregas",
      "name": "Hermes",
      "color": "#0091cd"
    },
    {
      "id": "dpd-ch",
      "catalog": "dpd",
      "slug": "rastreamento-dpd-suica",
      "title": "Rastreamento DPD Suíça: status, entrega e contato",
      "name": "DPD Switzerland",
      "color": "#dc0032"
    },
    {
      "id": "inpost",
      "catalog": "inpost",
      "slug": "rastreamento-inpost",
      "title": "Rastreamento InPost: códigos, status e retirada em armário",
      "name": "InPost",
      "color": "#ffcc05"
    },
    {
      "id": "paack",
      "catalog": "paack",
      "slug": "rastreamento-paack",
      "title": "Rastreamento Paack: pedido, código postal e entregas",
      "name": "Paack",
      "color": "#5b2dee"
    },
    {
      "id": "evri",
      "catalog": "evri-uk",
      "slug": "rastreamento-evri",
      "title": "Rastreamento Evri: status, ParcelShops e ausências",
      "name": "Evri",
      "color": "#00014d"
    },
    {
      "id": "chronopost",
      "catalog": "chronopost",
      "slug": "rastreamento-chronopost",
      "title": "Rastreamento Chronopost: códigos, status e retirada",
      "name": "Chronopost",
      "color": "#0056a7"
    },
    {
      "id": "poste-italiane",
      "catalog": "poste-italiane",
      "slug": "rastreamento-poste-italiane",
      "title": "Rastreamento Poste Italiane: códigos, status e retirada",
      "name": "Poste Italiane",
      "color": "#eedc00"
    },
    {
      "id": "seur",
      "catalog": "seur",
      "slug": "rastreamento-seur",
      "title": "Rastreamento SEUR: códigos, status e pontos Pickup",
      "name": "SEUR",
      "color": "#0063b3"
    },
    {
      "id": "mrw",
      "catalog": "mrw",
      "slug": "rastreamento-mrw",
      "title": "Rastreamento MRW: código de 12 dígitos, status e MRW Point",
      "name": "MRW",
      "color": "#19133b"
    },
    {
      "id": "ctt",
      "catalog": "ctt",
      "slug": "rastreamento-ctt-portugal",
      "title": "Rastreamento CTT Portugal: códigos, status e alfândega",
      "name": "CTT",
      "color": "#df0024"
    },
    {
      "id": "packeta",
      "catalog": "packeta",
      "slug": "rastreamento-packeta",
      "title": "Rastreamento Packeta (Zásilkovna): códigos Z e pontos",
      "name": "Packeta",
      "color": "#ba1b02"
    },
    {
      "id": "dpd-pl",
      "catalog": "dpd-pl",
      "slug": "rastreamento-dpd-polonia",
      "title": "Rastreamento DPD Poland: status, pontos Pickup e contato",
      "name": "DPD Poland",
      "color": "#dc0032"
    },
    {
      "id": "4px",
      "catalog": "four-px",
      "slug": "rastreamento-4px",
      "title": "Rastreamento 4PX: códigos, status, alfândega e contato",
      "name": "4PX",
      "color": "#005add"
    },
    {
      "id": "swiss-post",
      "catalog": "swiss-post",
      "slug": "rastreamento-swiss-post",
      "title": "Rastreamento Swiss Post: status, retirada e contato",
      "name": "Swiss Post",
      "color": "#ffcc00"
    },
    {
      "id": "ctt-express",
      "catalog": "ctt-express",
      "slug": "rastreamento-ctt-express",
      "title": "Rastreamento CTT Express: status em espanhol e contato",
      "name": "CTT Express",
      "color": "#df0024"
    },
    {
      "id": "dpd-fr",
      "catalog": "dpd-fr",
      "slug": "rastreamento-dpd-france",
      "title": "Rastreamento DPD France: códigos, status e pontos Pickup",
      "name": "DPD France",
      "color": "#dc0032"
    },
    {
      "id": "gls-fr",
      "catalog": "gls-fr",
      "slug": "rastreamento-gls-france",
      "title": "Rastreamento GLS France: status, Relais GLS e contato",
      "name": "GLS France",
      "color": "#ffdd00"
    },
    {
      "id": "gls-it",
      "catalog": "gls-it",
      "slug": "rastreamento-gls-italia",
      "title": "Rastreamento GLS Itália: códigos, status e pontos GLS",
      "name": "GLS Italy",
      "color": "#ffdd00"
    },
    {
      "id": "poczta-polska",
      "catalog": "poczta-polska",
      "slug": "rastreamento-poczta-polska",
      "title": "Rastreamento Poczta Polska: códigos, status e retirada",
      "name": "Poczta Polska",
      "color": "#c43c2b"
    },
    {
      "id": "orlen-paczka",
      "catalog": "orlen-paczka",
      "slug": "rastreamento-orlen-paczka",
      "title": "Rastreamento ORLEN Paczka: código de 13 dígitos e retirada",
      "name": "ORLEN Paczka",
      "color": "#db3830"
    },
    {
      "id": "austrian-post",
      "catalog": "austrian-post",
      "slug": "rastreamento-austrian-post",
      "title": "Rastreamento Austrian Post: códigos, status e retirada",
      "name": "Austrian Post",
      "color": "#ffdc00"
    },
    {
      "id": "tipsa",
      "catalog": "tipsa",
      "slug": "rastreamento-tipsa",
      "title": "Rastreamento TIPSA: código de 22 dígitos, status e contato",
      "name": "TIPSA",
      "color": "#001e62"
    },
    {
      "id": "yanwen",
      "catalog": "yanwen",
      "slug": "rastreamento-yanwen",
      "title": "Rastreamento Yanwen: códigos UL…YP, status e entrega",
      "name": "Yanwen",
      "color": "#8cbe1e"
    },
    {
      "id": "vinted-go",
      "catalog": "vinted-go",
      "slug": "rastreamento-vinted-go",
      "title": "Rastreamento Vinted Go: status, armários e contato",
      "name": "Vinted Go",
      "color": "#004654"
    },
    {
      "id": "dpd-uk",
      "catalog": "dpd-uk",
      "slug": "rastreamento-dpd-uk",
      "title": "Rastreamento DPD UK: status, lojas Pickup e contato",
      "name": "DPD UK",
      "color": "#dc0032"
    },
    {
      "id": "parcelforce",
      "catalog": "parcelforce",
      "slug": "parcelforce-worldwide-tracking",
      "title": "Rastreamento Parcelforce Worldwide: status e alfândega",
      "name": "Parcelforce Worldwide",
      "color": "#9d0f11"
    },
    {
      "id": "bpost",
      "catalog": "bpost",
      "slug": "rastreamento-bpost",
      "title": "Rastreamento bpost: correio da Bélgica, status e alfândega",
      "name": "bpost",
      "color": "#ef2637"
    },
    {
      "id": "gls-es",
      "catalog": "gls-es",
      "slug": "rastreamento-gls-espanha",
      "title": "Rastreamento GLS Espanha: status, Parcel Shops e contato",
      "name": "GLS Spain",
      "color": "#ffdd00"
    },
    {
      "id": "dhl-ecommerce-pl",
      "catalog": "dhl-ecommerce-pl",
      "slug": "rastreamento-dhl-ecommerce-polonia",
      "title": "Rastreamento DHL eCommerce Polônia: código e DHL BOX",
      "name": "DHL eCommerce Poland",
      "color": "#ffcc00"
    },
    {
      "id": "dhl-ecommerce-uk",
      "catalog": "dhl-ecommerce-uk",
      "slug": "dhl-ecommerce-uk-tracking",
      "title": "Rastreamento DHL eCommerce UK: status e ServicePoints",
      "name": "DHL eCommerce UK",
      "color": "#ffcc00"
    },
    {
      "id": "gofo-fr",
      "catalog": "gofo-fr",
      "slug": "rastreamento-gofo-franca",
      "title": "Rastreamento GOFO France: códigos GFFR, status e ajuda",
      "name": "GOFO France",
      "color": "#fc4c02"
    },
    {
      "id": "gofo-it",
      "catalog": "gofo-it",
      "slug": "rastreamento-gofo-italia",
      "title": "Rastreamento GOFO Italy: códigos GFIT, status e reclamações",
      "name": "GOFO Italy",
      "color": "#fc4c02"
    },
    {
      "id": "speedpak",
      "catalog": "speedpak",
      "slug": "rastreamento-speedpak",
      "title": "Rastreamento SpeedPAK (eBay): códigos de 28 caracteres",
      "name": "SpeedPAK",
      "color": "#ee7532"
    },
    {
      "id": "asendia",
      "catalog": "asendia",
      "slug": "asendia-tracking",
      "title": "Rastreamento Asendia: status, entrega local e contato",
      "name": "Asendia",
      "color": "#005d69"
    },
    {
      "id": "ciblex",
      "catalog": "ciblex",
      "slug": "ciblex-tracking",
      "title": "Rastreamento Ciblex: códigos, status em francês e contato",
      "name": "Ciblex",
      "color": "#0160ab"
    },
    {
      "id": "landmark-global",
      "catalog": "landmark-global",
      "slug": "rastreamento-landmark-global",
      "title": "Rastreamento Landmark Global: status, parceiros e contato",
      "name": "Landmark Global",
      "color": "#e62337"
    },
    {
      "id": "dhl-ecommerce",
      "catalog": "dhl-ecommerce",
      "slug": "rastreamento-dhl-ecommerce",
      "title": "Rastreamento DHL eCommerce: códigos GM, status e contato",
      "name": "DHL eCommerce",
      "color": "#ffcc00"
    },
    {
      "id": "allegro-one",
      "catalog": "allegro-one",
      "slug": "rastreamento-allegro-one",
      "title": "Rastreamento Allegro One: One Box, One Punkt e One Kurier",
      "name": "Allegro One",
      "color": "#006d57"
    },
    {
      "id": "sunyou",
      "catalog": "sunyou",
      "slug": "rastreamento-sunyou",
      "title": "Rastreamento SunYou (SYPost): códigos SY, status e entrega",
      "name": "SunYou",
      "color": "#02a232"
    },
    {
      "id": "amazon-shipping",
      "catalog": "amazon-shipping",
      "slug": "rastreamento-amazon-shipping",
      "title": "Rastreamento Amazon Shipping: códigos, problemas e ajuda",
      "name": "Amazon Shipping",
      "color": "#ff9900"
    },
    {
      "id": "bring",
      "catalog": "bring-posten",
      "slug": "rastreamento-bring",
      "title": "Rastreamento Bring: códigos, status e prazos de retirada",
      "name": "Bring",
      "color": "#56b529"
    },
    {
      "id": "posti",
      "catalog": "posti",
      "slug": "rastreamento-posti",
      "title": "Rastreamento Posti: código JJFI, status e armários",
      "name": "Posti",
      "color": "#ff8000"
    },
    {
      "id": "omniva",
      "catalog": "omniva",
      "slug": "omniva-tracking",
      "title": "Rastreamento Omniva: códigos, status e parcel machines",
      "name": "Omniva",
      "color": "#f86300"
    },
    {
      "id": "ppl",
      "catalog": "ppl",
      "slug": "rastreamento-ppl",
      "title": "Rastreamento PPL: código de 11 dígitos, status e Parcelshops",
      "name": "PPL",
      "color": "#004b93"
    },
    {
      "id": "box-now",
      "catalog": "box-now",
      "slug": "rastreamento-box-now",
      "title": "Rastreamento BOX NOW: armários, códigos PIN e status",
      "name": "BOX NOW",
      "color": "#44d62c"
    },
    {
      "id": "dhl-ecommerce-nl",
      "catalog": "dhl-ecommerce-nl",
      "slug": "rastreamento-dhl-ecommerce-holanda",
      "title": "Rastreamento DHL eCommerce Holanda: códigos JVGL e status",
      "name": "DHL eCommerce Netherlands",
      "color": "#ffcc00"
    },
    {
      "id": "ecoscooting",
      "catalog": "ecoscooting",
      "slug": "rastreamento-ecoscooting",
      "title": "Rastreamento Ecoscooting: status, retirada e contato",
      "name": "Ecoscooting",
      "color": "#3fa7ff"
    },
    {
      "id": "meest",
      "catalog": "meest",
      "slug": "rastreamento-meest",
      "title": "Rastreamento Meest: códigos, status e contatos",
      "name": "Meest",
      "color": "#0061af"
    },
    {
      "id": "nova-poshta",
      "catalog": "nova-poshta",
      "slug": "rastreamento-nova-poshta",
      "title": "Rastreamento Nova Poshta (Nova Post): códigos EW e agências",
      "name": "Nova Poshta",
      "color": "#e30613"
    },
    {
      "id": "speedy",
      "catalog": "speedy",
      "slug": "speedy-tracking",
      "title": "Rastreamento Speedy: códigos, status e lockers na Bulgária",
      "name": "Speedy",
      "color": "#dc0032"
    },
    {
      "id": "fan-courier",
      "catalog": "fan-courier",
      "slug": "rastreamento-fan-courier",
      "title": "Rastreamento FAN Courier: códigos AWB, status e FANbox",
      "name": "FAN Courier",
      "color": "#0f2345"
    },
    {
      "id": "econt",
      "catalog": "econt",
      "slug": "rastreamento-econt",
      "title": "Rastreamento Econt: número da remessa, status e agências",
      "name": "Econt",
      "color": "#234182"
    },
    {
      "id": "foxpost",
      "catalog": "foxpost",
      "slug": "foxpost-tracking",
      "title": "Rastreamento Foxpost: status, lockers e contatos na Hungria",
      "name": "Foxpost",
      "color": "#b70100"
    },
    {
      "id": "slovenska-posta",
      "catalog": "slovenska-posta",
      "slug": "rastreamento-slovenska-posta",
      "title": "Rastreamento Slovenská pošta: códigos e status",
      "name": "Slovenská pošta",
      "color": "#f9bb00"
    },
    {
      "id": "posta-slovenije",
      "catalog": "posta-slovenije",
      "slug": "rastreamento-posta-slovenije",
      "title": "Rastreamento Pošta Slovenije: correio da Eslovênia e locker",
      "name": "Pošta Slovenije",
      "color": "#ffc600"
    },
    {
      "id": "hrvatska-posta",
      "catalog": "hrvatska-posta",
      "slug": "rastreamento-hrvatska-posta",
      "title": "Rastreamento Hrvatska pošta: status, armários e contato",
      "name": "Hrvatska pošta",
      "color": "#ffd100"
    },
    {
      "id": "posta-srbije",
      "catalog": "posta-srbije",
      "slug": "posta-srbije-serbian-post-tracking",
      "title": "Rastreamento Pošta Srbije: Correios da Sérvia e status",
      "name": "Pošta Srbije",
      "color": "#fbba00"
    },
    {
      "id": "lietuvos-pastas",
      "catalog": "lietuvos-pastas",
      "slug": "rastreamento-lietuvos-pastas",
      "title": "Rastreamento Lietuvos paštas: correio da Lituânia e lockers",
      "name": "Lietuvos paštas",
      "color": "#fedd00"
    },
    {
      "id": "latvijas-pasts",
      "catalog": "latvijas-pasts",
      "slug": "rastreamento-latvijas-pasts",
      "title": "Rastreamento Latvijas Pasts: códigos, status e armários",
      "name": "Latvijas Pasts",
      "color": "#0c72ba"
    },
    {
      "id": "dao",
      "catalog": "dao",
      "slug": "dao-tracking",
      "title": "Rastreamento DAO: status, daoSHOP e entregas não realizadas",
      "name": "DAO",
      "color": "#e30613"
    },
    {
      "id": "helthjem",
      "catalog": "helthjem",
      "slug": "rastreamento-helthjem",
      "title": "Rastreamento Helthjem: status, pontos de retirada e contato",
      "name": "Helthjem",
      "color": "#ffb202"
    },
    {
      "id": "ukrposhta",
      "catalog": "ukrposhta",
      "slug": "ukrposhta-tracking",
      "title": "Rastreamento Ukrposhta: códigos e status na Ucrânia",
      "name": "Ukrposhta",
      "color": "#ffbe0c"
    },
    {
      "id": "acs-courier",
      "catalog": "acs-courier",
      "slug": "acs-courier-tracking",
      "title": "Rastreamento ACS Courier: códigos, status e contatos",
      "name": "ACS Courier",
      "color": "#e42229"
    },
    {
      "id": "geniki-taxydromiki",
      "catalog": "geniki-taxydromiki",
      "slug": "rastreamento-geniki-taxydromiki",
      "title": "Rastreamento Geniki Taxydromiki: voucher, status e contato",
      "name": "Geniki Taxydromiki",
      "color": "#004fa0"
    },
    {
      "id": "xdp",
      "catalog": "xdp",
      "slug": "xdp-tracking",
      "title": "Rastreamento XDP: códigos, status e entregas no Reino Unido",
      "name": "XDP",
      "color": "#53b6ed"
    },
    {
      "id": "ups",
      "catalog": "ups",
      "slug": "rastreamento-ups",
      "title": "Rastreamento UPS: códigos 1Z, status Exception e alfândega",
      "name": "UPS",
      "color": "#351c15"
    },
    {
      "id": "fedex",
      "catalog": "fedex",
      "slug": "rastreamento-fedex",
      "title": "Rastreamento FedEx: códigos, Delivery exception e alfândega",
      "name": "FedEx",
      "color": "#4d148c"
    },
    {
      "id": "dhl-express",
      "catalog": "dhl-express",
      "slug": "rastreamento-dhl-express",
      "title": "Rastreamento DHL Express: número de conhecimento e status",
      "name": "DHL Express",
      "color": "#ffcc00"
    },
    {
      "id": "dhl",
      "catalog": "dhl",
      "slug": "rastreamento-dhl-paket",
      "title": "Rastreamento DHL Paket: códigos, status e Packstation",
      "name": "DHL Paket",
      "color": "#ffcc00"
    },
    {
      "id": "la-poste",
      "catalog": "la-poste",
      "slug": "rastreamento-la-poste-colissimo",
      "title": "Rastreamento La Poste / Colissimo: códigos e status",
      "name": "La Poste / Colissimo",
      "color": "#ffcf00"
    },
    {
      "id": "correos-spain",
      "catalog": "correos-spain",
      "slug": "rastreamento-correos-espanha",
      "title": "Rastreamento Correos Espanha: códigos, status e Citypaq",
      "name": "Correos",
      "color": "#ffcd00"
    },
    {
      "id": "royal-mail",
      "catalog": "royal-mail",
      "slug": "rastreamento-royal-mail",
      "title": "Rastreamento Royal Mail: códigos, status e nova entrega",
      "name": "Royal Mail",
      "color": "#da202a"
    },
    {
      "id": "usps",
      "catalog": "usps",
      "slug": "rastreamento-usps",
      "title": "Rastreamento USPS: códigos, status, entrega e contato",
      "name": "USPS",
      "color": "#004b87"
    },
    {
      "id": "canada-post",
      "catalog": "canada-post",
      "slug": "rastreamento-canada-post",
      "title": "Rastreamento Canada Post: códigos, status e avisos",
      "name": "Canada Post",
      "color": "#e4002b"
    },
    {
      "id": "uniuni",
      "catalog": "uniuni",
      "slug": "rastreamento-uniuni",
      "title": "Rastreamento UniUni: status, entrega não realizada e contato",
      "name": "UniUni",
      "color": "#f68841"
    },
    {
      "id": "ontrac",
      "catalog": "ontrac",
      "slug": "rastreamento-ontrac",
      "title": "Rastreamento OnTrac: códigos, status e entregas nos EUA",
      "name": "OnTrac",
      "color": "#cc2027"
    },
    {
      "id": "gofo",
      "catalog": "gofo",
      "slug": "rastreamento-gofo-express",
      "title": "Rastreamento GOFO Express: códigos GFUS, fotos e suporte",
      "name": "GOFO Express",
      "color": "#fc4c02"
    },
    {
      "id": "speedx",
      "catalog": "speedx",
      "slug": "rastreamento-speedx",
      "title": "Rastreamento SpeedX: status, tentativas de entrega e contato",
      "name": "SpeedX",
      "color": "#1aa3ff"
    },
    {
      "id": "australia-post",
      "catalog": "australia-post",
      "slug": "rastreamento-australia-post",
      "title": "Rastreamento Australia Post: códigos, status e lockers",
      "name": "Australia Post",
      "color": "#dc1928"
    },
    {
      "id": "aramex-nz",
      "catalog": "aramex-nz",
      "slug": "aramex-new-zealand-tracking-number",
      "title": "Rastreamento Aramex Nova Zelândia: status e contatos",
      "name": "Aramex New Zealand",
      "color": "#dc291e"
    },
    {
      "id": "magyar-posta",
      "catalog": "magyar-posta",
      "slug": "rastreamento-magyar-posta",
      "title": "Rastreamento Magyar Posta: status em húngaro explicados",
      "name": "Magyar Posta",
      "color": "#346631"
    },
    {
      "id": "first-line",
      "catalog": "first-line",
      "slug": "rastreamento-first-line",
      "title": "Rastreamento FIRST LINE: códigos 1ST, status e entrega",
      "name": "FIRST LINE",
      "color": "#55207a"
    },
    {
      "id": "j-and-t-de",
      "catalog": "j-and-t-de",
      "slug": "rastreamento-jt-express-alemanha",
      "title": "Rastreamento J&T Express Alemanha: códigos JTDE e status",
      "name": "J&T Express Germany",
      "color": "#e60000"
    },
    {
      "id": "juxi-express",
      "catalog": "juxi-express",
      "slug": "rastreamento-juxi-express",
      "title": "Rastreamento Juxi Express: códigos JXC, entregas e suporte",
      "name": "Juxi Express",
      "color": "#e2393c"
    },
    {
      "id": "lingdong",
      "catalog": "lingdong",
      "slug": "rastreamento-lingdong",
      "title": "Rastreamento Lingdong International: códigos SZLD e status",
      "name": "Lingdong International",
      "color": "#0171bb"
    },
    {
      "id": "j-and-t-au",
      "catalog": "j-and-t-au",
      "slug": "rastreamento-jt-express-australia",
      "title": "Rastreamento J&T Express Austrália: códigos JTAU e status",
      "name": "J&T Express Australia",
      "color": "#e60000"
    },
    {
      "id": "china-post",
      "catalog": "china-post",
      "slug": "rastreamento-china-post",
      "title": "Rastreamento China Post: códigos CN, status e entrega",
      "name": "China Post",
      "color": "#006d46"
    },
    {
      "id": "fedex-pl",
      "catalog": "fedex-pl",
      "slug": "fedex-poland-tracking",
      "title": "Rastreamento FedEx Polônia: códigos de 13 dígitos e status",
      "name": "FedEx Poland",
      "color": "#4d148c"
    },
    {
      "id": "techlink",
      "catalog": "techlink",
      "slug": "rastreamento-techlink",
      "title": "Rastreamento Techlink: códigos ZHPHD, status e ajuda",
      "name": "Techlink",
      "color": "#e73547"
    },
    {
      "id": "imile",
      "catalog": "imile",
      "slug": "rastreamento-imile",
      "title": "Rastreamento iMile: códigos, status, alfândega e contato",
      "name": "iMile",
      "color": "#0032a0"
    },
    {
      "id": "postnl",
      "catalog": "spring-gds",
      "slug": "rastreamento-postnl",
      "title": "Rastreamento PostNL: códigos NL e 3S, status e entrega",
      "name": "PostNL",
      "color": "#ef7d00"
    },
    {
      "id": "dhlink",
      "catalog": "dhlink",
      "slug": "rastreamento-dhlink",
      "title": "Rastreamento DHLink: códigos, status e quem entrega",
      "name": "DHLink",
      "color": "#143167"
    },
    {
      "id": "dpd-pt",
      "catalog": "dpd-pt",
      "slug": "rastreamento-dpd-portugal",
      "title": "Rastreamento DPD Portugal: códigos, status e pontos Pickup",
      "name": "DPD Portugal",
      "color": "#dc0032"
    },
    {
      "id": "tnt-it",
      "catalog": "tnt-it",
      "slug": "rastreamento-tnt-italia",
      "title": "Rastreamento TNT Italy: guia, status e entrega não realizada",
      "name": "TNT Italy",
      "color": "#ff6600"
    },
    {
      "id": "tnt",
      "catalog": "tnt",
      "slug": "rastreamento-tnt",
      "title": "Rastreamento TNT: número da remessa, status e contato",
      "name": "TNT",
      "color": "#ff6600"
    },
    {
      "id": "sf-express",
      "catalog": "sf-express",
      "slug": "rastreamento-sf-express",
      "title": "Rastreamento SF Express: códigos SF, status e contato",
      "name": "SF Express",
      "color": "#dc1e32"
    },
    {
      "id": "straightship",
      "catalog": "straightship",
      "slug": "rastreamento-straightship",
      "title": "Rastreamento Straightship: códigos STRTD, status e entrega",
      "name": "Straightship",
      "color": "#6aae66"
    },
    {
      "id": "correios-br",
      "catalog": "correios-br",
      "slug": "rastreamento-correios-brasil",
      "title": "Rastreamento Correios — códigos BR e Minhas Importações",
      "name": "Correios Brazil",
      "color": "#ffd500"
    },
    {
      "id": "yto",
      "catalog": "yto",
      "slug": "rastreio-yto-express",
      "title": "Rastreio YTO Express: código YT, status e contato",
      "name": "YTO Express",
      "color": "#4d148c"
    },
    {
      "id": "spx-br",
      "catalog": "spx-br",
      "slug": "rastreio-spx-express",
      "title": "Rastreio SPX Express: pedidos da Shopee, status e prazos",
      "name": "SPX Express Brazil",
      "color": "#ee4d2d"
    },
    {
      "id": "pengyou",
      "catalog": "pengyou",
      "slug": "rastreamento-pengyou",
      "title": "Rastreamento Pengyou: códigos PY, etapas e a quem perguntar",
      "name": "Pengyou",
      "color": "#043a82"
    },
    {
      "id": "mtscn",
      "catalog": "mtscn",
      "slug": "rastreamento-meiteshun",
      "title": "Rastreamento Meiteshun: AMTS, etapas e a quem perguntar",
      "name": "Meiteshun Logistics",
      "color": "#f89f3b"
    },
    {
      "id": "cttyex",
      "catalog": "cttyex",
      "slug": "rastreamento-cttyex",
      "title": "Rastreamento CTTYEX: códigos CTTY, eventos e horário",
      "name": "CTTYEX",
      "color": "#0068AC"
    },
    {
      "id": "speedaf",
      "catalog": "speedaf",
      "slug": "rastreamento-speedaf",
      "title": "Rastreamento Speedaf Express: códigos, status e contatos",
      "name": "Speedaf",
      "color": "#ff8300"
    },
    {
      "id": "quick-rabbit",
      "catalog": "quick-rabbit",
      "slug": "rastreamento-quick-rabbit",
      "title": "Rastreamento Quick Rabbit: códigos PKG, status e Citibox",
      "name": "Quick Rabbit",
      "color": "#f6851f"
    },
    {
      "id": "huaya-express",
      "catalog": "huaya-express",
      "slug": "rastreamento-huaya-express",
      "title": "Rastreamento Huaya Express: códigos HYYT e onde param",
      "name": "Huaya Express",
      "color": "#e60013"
    },
    {
      "id": "gofo-ca",
      "catalog": "gofo-ca",
      "slug": "rastreamento-gofo-canada",
      "title": "Rastreamento GOFO Canada: códigos GFCA, status e atrasos",
      "name": "GOFO Canada",
      "color": "#fc4c02"
    },
    {
      "id": "topyou",
      "catalog": "topyou",
      "slug": "rastreamento-topyou",
      "title": "Rastreamento TopYou: códigos TYZR, status e entrega",
      "name": "TopYou",
      "color": "#045cd7"
    },
    {
      "id": "mayiyinfu",
      "catalog": "mayiyinfu",
      "slug": "mayi-yinfu-tracking",
      "title": "Rastreamento Mayi Yinfu: códigos MY, voos e entrega DHL",
      "name": "Mayi Yinfu",
      "color": "#000000"
    },
    {
      "id": "jrt",
      "catalog": "jrt",
      "slug": "rastreamento-jrt",
      "title": "Rastreamento JRT: códigos JRT…MM, voos e entrega DHL",
      "name": "JRT",
      "color": "#023053"
    },
    {
      "id": "thhy",
      "catalog": "thhy",
      "slug": "rastreamento-tianhui-hengyuan",
      "title": "Rastreamento Tianhui Hengyuan (THHY): códigos AT e leituras",
      "name": "Tianhui Hengyuan",
      "color": "#00a2e9"
    },
    {
      "id": "js-express",
      "catalog": "js-express",
      "slug": "rastreamento-js-express",
      "title": "Rastreamento JS Express: códigos JSE…YQ, status e entrega",
      "name": "JS Express",
      "color": "#fe9b00"
    },
    {
      "id": "wanbexpress",
      "catalog": "wanbexpress",
      "slug": "rastreamento-wanbexpress",
      "title": "Rastreamento WanbExpress: códigos WNB…YQ e quem entrega",
      "name": "WanbExpress",
      "color": "#da381b"
    },
    {
      "id": "gzyl",
      "catalog": "gzyl",
      "slug": "rastreamento-gzyl",
      "title": "Rastreamento GZYL: códigos YL…YQ, etapas e entregas",
      "name": "GZYL",
      "color": "#4e79e1"
    },
    {
      "id": "shouwu",
      "catalog": "shouwu",
      "slug": "rastreamento-shouwu",
      "title": "Rastreamento Shouwu: códigos SWECN…YQ, status e entrega",
      "name": "Shouwu International",
      "color": "#be2c33"
    },
    {
      "id": "geodis-pl",
      "catalog": "geodis-pl",
      "slug": "rastreamento-geodis-polonia",
      "title": "Rastreamento GEODIS Poland: código, status e entregas",
      "name": "GEODIS Poland",
      "color": "#3100e6"
    },
    {
      "id": "sending",
      "catalog": "sending",
      "slug": "rastreamento-sending",
      "title": "Rastreamento Sending na Espanha: código, status e contato",
      "name": "Sending",
      "color": "#e52021"
    },
    {
      "id": "ywe",
      "catalog": "ywe",
      "slug": "rastreamento-ywe",
      "title": "Rastreamento YWE (YanwenExpress): códigos, status e suporte",
      "name": "YWE",
      "color": "#055aa1"
    },
    {
      "id": "ecms-express",
      "catalog": "ecms-express",
      "slug": "rastreamento-ecms-express",
      "title": "Rastreamento ECMS Express: códigos, status e quem entrega",
      "name": "ECMS Express",
      "color": "#12823d"
    },
    {
      "id": "chunghwa-post",
      "catalog": "chunghwa-post",
      "slug": "rastreamento-chunghwa-post",
      "title": "Rastreamento Chunghwa Post (Taiwan): códigos e status",
      "name": "Chunghwa Post",
      "color": "#e8171f"
    },
    {
      "id": "raben",
      "catalog": "raben",
      "slug": "raben-tracking",
      "title": "Rastreamento Raben: código de envio, status e entregas",
      "name": "Raben",
      "color": "#df0020"
    },
    {
      "id": "jd-logistics",
      "catalog": "jd-logistics",
      "slug": "rastreamento-jd-logistics",
      "title": "Rastreamento JD Logistics: códigos JD, status e contato",
      "name": "JD Logistics",
      "color": "#e1251b"
    },
    {
      "id": "singapore-post",
      "catalog": "singapore-post",
      "slug": "rastreamento-singapore-post",
      "title": "Rastreamento Singapore Post (SingPost): códigos e status",
      "name": "Singapore Post",
      "color": "#2e5da8"
    },
    {
      "id": "zto",
      "catalog": "zto",
      "slug": "rastreamento-zto-express",
      "title": "Rastreamento ZTO Express: códigos, status e contato",
      "name": "ZTO Express",
      "color": "#0279ff"
    },
    {
      "id": "cne",
      "catalog": "cne",
      "slug": "rastreamento-cne-express",
      "title": "Rastreamento CNE Express: códigos 3A5V, status e contato",
      "name": "CNE Express",
      "color": "#e83828"
    },
    {
      "id": "japan-post",
      "catalog": "japan-post",
      "slug": "rastreamento-japan-post",
      "title": "Rastreamento Japan Post: códigos EMS e status explicados",
      "name": "Japan Post",
      "color": "#cc0000"
    },
    {
      "id": "russian-post",
      "catalog": "russian-post",
      "slug": "rastreamento-russian-post",
      "title": "Rastreamento Russian Post (Почта России): códigos e etapas",
      "name": "Russian Post",
      "color": "#1928dc"
    },
    {
      "id": "huahan",
      "catalog": "huahan",
      "slug": "rastreamento-huahan-logistics",
      "title": "Rastreamento HuaHan Logistics: códigos HHW…YQ e entrega",
      "name": "HuaHan Logistics",
      "color": "#0069e8"
    },
    {
      "id": "epost-global",
      "catalog": "epost-global",
      "slug": "rastreamento-epost-global",
      "title": "Rastreamento ePost Global: códigos EPG e entrega final",
      "name": "ePost Global",
      "color": "#27967a"
    },
    {
      "id": "gls-at",
      "catalog": "gls-at",
      "slug": "gls-austria-tracking",
      "title": "Rastreamento GLS Áustria: status, Parcel Shops e contato",
      "name": "GLS Austria",
      "color": "#ffdd00"
    },
    {
      "id": "india-post",
      "catalog": "india-post",
      "slug": "rastreamento-india-post",
      "title": "Rastreamento India Post: códigos Speed Post e status",
      "name": "India Post",
      "color": "#b82025"
    },
    {
      "id": "morning-global",
      "catalog": "morning-global",
      "slug": "morning-global-tracking",
      "title": "Rastreamento Morning Global: código BR…MG, status e ajuda",
      "name": "Morning Global",
      "color": "#050059"
    },
    {
      "id": "delhivery",
      "catalog": "delhivery",
      "slug": "rastreamento-delhivery",
      "title": "Rastreamento Delhivery: códigos AWB, status e atendimento",
      "name": "Delhivery",
      "color": "#ed4136"
    },
    {
      "id": "emirates-post",
      "catalog": "emirates-post",
      "slug": "rastreamento-emirates-post",
      "title": "Rastreamento Emirates Post: códigos AE e status explicados",
      "name": "Emirates Post",
      "color": "#004a98"
    },
    {
      "id": "blue-dart",
      "catalog": "blue-dart",
      "slug": "rastreamento-blue-dart",
      "title": "Rastreamento Blue Dart: código de 11 dígitos e status",
      "name": "Blue Dart",
      "color": "#0059a9"
    },
    {
      "id": "ekart",
      "catalog": "ekart",
      "slug": "rastreamento-ekart",
      "title": "Rastreamento Ekart Índia: códigos, status e RTO explicados",
      "name": "Ekart",
      "color": "#0074D9"
    },
    {
      "id": "j-and-t-middle-east",
      "catalog": "j-and-t-middle-east",
      "slug": "rastreamento-jt-express-oriente-medio",
      "title": "Rastreamento J&T Express na Arábia Saudita, EAU e Kuwait",
      "name": "J&T Express Middle East",
      "color": "#e60000"
    },
    {
      "id": "globalpost",
      "catalog": "globalpost",
      "slug": "globalpost-tracking",
      "title": "Rastreamento GlobalPost: códigos GP e quem faz a entrega",
      "name": "GlobalPost",
      "color": "#11003a"
    },
    {
      "id": "cdek",
      "catalog": "cdek",
      "slug": "rastreamento-cdek",
      "title": "Rastreamento CDEK (СДЭК): números de pedido e status",
      "name": "CDEK",
      "color": "#00b33c"
    },
    {
      "id": "dsv",
      "catalog": "dsv",
      "slug": "rastreamento-dsv",
      "title": "Rastreamento DSV: códigos, status e DB Schenker",
      "name": "DSV",
      "color": "#002664"
    },
    {
      "id": "rl-carriers",
      "catalog": "rl-carriers",
      "slug": "rastreamento-rl-carriers",
      "title": "Rastreamento R+L Carriers: código PRO, status e indenizações",
      "name": "R+L Carriers",
      "color": "#006647"
    },
    {
      "id": "far-international",
      "catalog": "far-international",
      "slug": "rastreamento-far-international",
      "title": "Rastreamento FAR International: códigos FAR…YQ e status",
      "name": "FAR International",
      "color": "#003b75"
    },
    {
      "id": "jifan",
      "catalog": "jifan",
      "slug": "rastreamento-jifan",
      "title": "Rastreamento Jifan: códigos JFGJ, eventos e horário",
      "name": "Jifan",
      "color": "#c81011"
    },
    {
      "id": "ozon-logistics",
      "catalog": "ozon-logistics",
      "slug": "rastreamento-ozon",
      "title": "Rastreamento Ozon: códigos de pedido e status em russo",
      "name": "Ozon",
      "color": "#005bff"
    },
    {
      "id": "dpd-be",
      "catalog": "dpd-be",
      "slug": "rastreamento-dpd-belgium",
      "title": "Rastreamento DPD Belgium: status, entrega e contato",
      "name": "DPD Belgium",
      "color": "#dc0032"
    },
    {
      "id": "sto",
      "catalog": "sto",
      "slug": "rastreamento-sto-express",
      "title": "Rastreamento STO Express (申通快递): códigos e status",
      "name": "STO Express",
      "color": "#ff6700"
    },
    {
      "id": "j-and-t-cn",
      "catalog": "j-and-t-cn",
      "slug": "rastreamento-jt-express-china",
      "title": "Rastreamento J&T Express China (极兔速递): códigos e status",
      "name": "J&T Express China",
      "color": "#e62019"
    },
    {
      "id": "andreani",
      "catalog": "andreani",
      "slug": "rastreamento-andreani",
      "title": "Rastreamento Andreani: códigos e status na Argentina",
      "name": "Andreani",
      "color": "#d71920"
    },
    {
      "id": "17feia",
      "catalog": "17feia",
      "slug": "rastreamento-17feia",
      "title": "Rastreamento 17FEIA (一起飞): códigos FEI…YQ e entrega",
      "name": "17FEIA",
      "color": "#00a9e4"
    },
    {
      "id": "tsjy",
      "catalog": "tsjy",
      "slug": "tsjy-tracking",
      "title": "Rastreamento TSJY (通世集运): códigos de pedido e status",
      "name": "TSJY",
      "color": "#4042e2"
    },
    {
      "id": "j-and-t-uk",
      "catalog": "j-and-t-uk",
      "slug": "rastreamento-jt-express-uk",
      "title": "Rastreamento J&T Express UK: códigos JTUK e status",
      "name": "J&T Express UK",
      "color": "#e60000"
    },
    {
      "id": "early-bird",
      "catalog": "early-bird",
      "slug": "rastreamento-early-bird",
      "title": "Rastreamento Early Bird: status, entrega noturna e armários",
      "name": "Early Bird",
      "color": "#dd7f4a"
    },
    {
      "id": "xinghe-freight",
      "catalog": "xinghe-freight",
      "slug": "rastreamento-xinghe-freight",
      "title": "Rastreamento Xinghe Freight: códigos GZXH…YQ e etapas",
      "name": "Xinghe Freight",
      "color": "#0e3a6b"
    },
    {
      "id": "sxw-express",
      "catalog": "sxw-express",
      "slug": "rastreamento-sxw-express",
      "title": "Rastreamento SXW Express: códigos SXW, etapas e entrega",
      "name": "SXW Express",
      "color": "#003876"
    },
    {
      "id": "j-and-t-br",
      "catalog": "j-and-t-br",
      "slug": "rastreio-jt-express-brasil",
      "title": "Rastreio J&T Express Brasil: status, entrega e contato",
      "name": "J&T Express Brazil",
      "color": "#e60000"
    },
    {
      "id": "anjun",
      "catalog": "anjun",
      "slug": "rastreio-anjun-express",
      "title": "Rastreio Anjun Express: encomendas da China, status e SAC",
      "name": "Anjun Logistics",
      "color": "#009944"
    },
    {
      "id": "xyl-ships",
      "catalog": "xyl-ships",
      "slug": "rastreamento-xyl-ships",
      "title": "Rastreamento XYL-Ships: códigos NUDE e XLWL e status",
      "name": "XYL-Ships",
      "color": "#b13c3c"
    },
    {
      "id": "jadlog",
      "catalog": "jadlog",
      "slug": "rastreio-jadlog",
      "title": "Rastreio Jadlog: código de 14 dígitos, Pickup e status",
      "name": "Jadlog",
      "color": "#dc0032"
    },
    {
      "id": "gls-pl",
      "catalog": "gls-pl",
      "slug": "gls-poland-tracking",
      "title": "Rastreamento GLS Polônia: status, GLS Points e lockers",
      "name": "GLS Poland",
      "color": "#ffdd00"
    },
    {
      "id": "j-and-t-mx",
      "catalog": "j-and-t-mx",
      "slug": "rastreamento-jt-express-mexico",
      "title": "Rastreamento J&T Express México: códigos JMX e atendimento",
      "name": "J&T Express Mexico",
      "color": "#e60012"
    },
    {
      "id": "bulgarian-posts",
      "catalog": "bulgarian-posts",
      "slug": "bulgarian-posts-tracking",
      "title": "Rastreamento Bulgarian Posts: códigos PS/BG, taxas e ajuda",
      "name": "Bulgarian Posts",
      "color": "#ffd100"
    },
    {
      "id": "haypost",
      "catalog": "haypost",
      "slug": "haypost-tracking",
      "title": "Rastreamento HayPost: códigos AM, status e contatos",
      "name": "HayPost",
      "color": "#fa4616"
    },
    {
      "id": "360lion",
      "catalog": "360lion",
      "slug": "rastreamento-360lion",
      "title": "Rastreamento 360Lion: códigos WSH e AR, status e contato",
      "name": "360Lion",
      "color": "#cf5d15"
    },
    {
      "id": "a-tu-hora-express",
      "catalog": "a-tu-hora-express",
      "slug": "a-tu-hora-express-tracking",
      "title": "Rastreamento A Tu Hora Express: códigos, status e ajuda",
      "name": "A Tu Hora Express",
      "color": "#1d2a84"
    },
    {
      "id": "gls-hu",
      "catalog": "gls-hu",
      "slug": "gls-hungary-tracking",
      "title": "Rastreamento GLS Hungria: códigos, lockers e contatos",
      "name": "GLS Hungary",
      "color": "#ffdd00"
    },
    {
      "id": "jumppoint-international",
      "catalog": "jumppoint-international",
      "slug": "jumppoint-international-tracking",
      "title": "Rastreamento Jumppoint International: códigos JP e suporte",
      "name": "Jumppoint International Express",
      "color": "#0f4ae1"
    },
    {
      "id": "skypostal",
      "catalog": "skypostal",
      "slug": "rastreamento-skypostal",
      "title": "Rastreamento SkyPostal: códigos SP...BR, status e suporte",
      "name": "SkyPostal",
      "color": "#088aea"
    },
    {
      "id": "onlog-express",
      "catalog": "onlog-express",
      "slug": "rastreamento-onlog-express",
      "title": "Rastreamento Onlog Express: códigos AA, status e contato",
      "name": "Onlog Express",
      "color": "#1a345d"
    },
    {
      "id": "memphis-courier",
      "catalog": "memphis-courier",
      "slug": "rastreio-memphis-courier",
      "title": "Rastreamento Memphis Courier — códigos, alfândega e ANVISA",
      "name": "Memphis Courier",
      "color": "#1f3c5b"
    },
    {
      "id": "bfe-express",
      "catalog": "bfe-express",
      "slug": "rastreamento-bfe-express",
      "title": "Rastreamento BFE Express: códigos CK, WYB e status",
      "name": "BFE Express",
      "color": "#122a88"
    }
  ],
  "pl": [
    {
      "id": "quickpac",
      "catalog": "quickpac",
      "slug": "sledzenie-quickpac",
      "title": "Śledzenie Quickpac: 18-cyfrowy numer, statusy i kontakt",
      "name": "Quickpac",
      "color": "#ed1c24"
    },
    {
      "id": "planzer",
      "catalog": "planzer",
      "slug": "sledzenie-planzer",
      "title": "Śledzenie Planzer: znaczenie statusów i kontakt",
      "name": "Planzer",
      "color": "#e30613"
    },
    {
      "id": "colis-prive",
      "catalog": "colis-prive",
      "slug": "sledzenie-colis-prive",
      "title": "Śledzenie Colis Privé: francuskie statusy i co zrobić",
      "name": "Colis Privé",
      "color": "#aa78ff"
    },
    {
      "id": "brt",
      "catalog": "brt",
      "slug": "brt-sledzenie",
      "title": "Śledzenie przesyłek BRT: paczka z Włoch, statusy i kontakt",
      "name": "BRT",
      "color": "#dc0032"
    },
    {
      "id": "correos-express",
      "catalog": "correos-express",
      "slug": "correos-express-sledzenie",
      "title": "Śledzenie Correos Express: numer, statusy i kontakt",
      "name": "Correos Express",
      "color": "#ffcd00"
    },
    {
      "id": "nacex",
      "catalog": "nacex",
      "slug": "nacex-sledzenie",
      "title": "Śledzenie przesyłek NACEX: numer, statusy i kontakt",
      "name": "NACEX",
      "color": "#fe5000"
    },
    {
      "id": "yunexpress",
      "catalog": "yunexpress",
      "slug": "yunexpress-sledzenie",
      "title": "YunExpress śledzenie przesyłki: statusy, kurier, kontakt",
      "name": "YunExpress",
      "color": "#008284"
    },
    {
      "id": "cainiao",
      "catalog": "aliexpress",
      "slug": "cainiao-sledzenie",
      "title": "Cainiao śledzenie przesyłki: numery, statusy i kontakt",
      "name": "Cainiao",
      "color": "#e62e04"
    },
    {
      "id": "mondial-relay",
      "catalog": "mondial-relay",
      "slug": "sledzenie-mondial-relay",
      "title": "Śledzenie Mondial Relay: statusy, InPost, Vinted i odbiór",
      "name": "Mondial Relay",
      "color": "#ef3026"
    },
    {
      "id": "gls-de",
      "catalog": "gls-de",
      "slug": "sledzenie-gls-niemcy",
      "title": "Śledzenie GLS Niemcy: numer, Track-ID, statusy i kontakt",
      "name": "GLS Germany",
      "color": "#ffdd00"
    },
    {
      "id": "dpd-de",
      "catalog": "dpd-de",
      "slug": "sledzenie-dpd-niemcy",
      "title": "Śledzenie DPD Niemcy (DPD Germany): numer, statusy i pomoc",
      "name": "DPD Germany",
      "color": "#dc0032"
    },
    {
      "id": "hermes-de",
      "catalog": "hermes-de",
      "slug": "sledzenie-hermes",
      "title": "Śledzenie Hermes Niemcy: statusy po polsku i doręczenie",
      "name": "Hermes",
      "color": "#0091cd"
    },
    {
      "id": "gls-ch",
      "catalog": "gls-ch",
      "slug": "sledzenie-gls-szwajcaria",
      "title": "Śledzenie GLS Szwajcaria: statusy, Swiss Post i kontakt",
      "name": "GLS Switzerland",
      "color": "#ffdd00"
    },
    {
      "id": "dpd-ch",
      "catalog": "dpd",
      "slug": "dpd-szwajcaria-sledzenie",
      "title": "DPD Szwajcaria śledzenie: status paczki, Pickup i kontakt",
      "name": "DPD Switzerland",
      "color": "#dc0032"
    },
    {
      "id": "inpost",
      "catalog": "inpost",
      "slug": "inpost-sledzenie-przesylki",
      "title": "InPost śledzenie przesyłki: numer, statusy i Paczkomat",
      "name": "InPost",
      "color": "#ffcc05"
    },
    {
      "id": "paack",
      "catalog": "paack",
      "slug": "paack-sledzenie",
      "title": "Paack śledzenie przesyłek: numer zamówienia, kod i statusy",
      "name": "Paack",
      "color": "#5b2dee"
    },
    {
      "id": "evri",
      "catalog": "evri-uk",
      "slug": "sledzenie-evri",
      "title": "Śledzenie przesyłek Evri: numer, statusy, cło i doręczenie",
      "name": "Evri",
      "color": "#00014d"
    },
    {
      "id": "chronopost",
      "catalog": "chronopost",
      "slug": "chronopost-sledzenie-paczki",
      "title": "Chronopost śledzenie paczki: z Francji do Polski i statusy",
      "name": "Chronopost",
      "color": "#0056a7"
    },
    {
      "id": "relais-colis",
      "catalog": "relais-colis",
      "slug": "sledzenie-relais-colis",
      "title": "Śledzenie Relais Colis: statusy, odbiór i kontakt",
      "name": "Relais Colis",
      "color": "#7bb928"
    },
    {
      "id": "poste-italiane",
      "catalog": "poste-italiane",
      "slug": "poste-italiane-sledzenie",
      "title": "Poste Italiane śledzenie: kody 1UW i 3UW oraz statusy",
      "name": "Poste Italiane",
      "color": "#eedc00"
    },
    {
      "id": "seur",
      "catalog": "seur",
      "slug": "sledzenie-seur",
      "title": "Śledzenie przesyłek SEUR: numer, statusy i punkty Pickup",
      "name": "SEUR",
      "color": "#0063b3"
    },
    {
      "id": "mrw",
      "catalog": "mrw",
      "slug": "sledzenie-mrw",
      "title": "Śledzenie MRW: 12-cyfrowy numer, statusy, Point i kontakt",
      "name": "MRW",
      "color": "#19133b"
    },
    {
      "id": "ctt",
      "catalog": "ctt",
      "slug": "sledzenie-ctt-portugal",
      "title": "Śledzenie paczek CTT Portugal: numery, statusy i odbiór",
      "name": "CTT",
      "color": "#df0024"
    },
    {
      "id": "packeta",
      "catalog": "packeta",
      "slug": "packeta-sledzenie",
      "title": "Packeta śledzenie: numer Z, statusy i punkty odbioru",
      "name": "Packeta",
      "color": "#ba1b02"
    },
    {
      "id": "dpd-pl",
      "catalog": "dpd-pl",
      "slug": "dpd-poland-sledzenie-paczki",
      "title": "DPD Poland śledzenie paczki: statusy, Pickup i kontakt",
      "name": "DPD Poland",
      "color": "#dc0032"
    },
    {
      "id": "4px",
      "catalog": "four-px",
      "slug": "4px-sledzenie-przesylki",
      "title": "4PX śledzenie przesyłki: numery, statusy i kontakt",
      "name": "4PX",
      "color": "#005add"
    },
    {
      "id": "swiss-post",
      "catalog": "swiss-post",
      "slug": "poczta-szwajcarska-sledzenie",
      "title": "Swiss Post śledzenie przesyłek: paczki ze Szwajcarii",
      "name": "Swiss Post",
      "color": "#ffcc00"
    },
    {
      "id": "ctt-express",
      "catalog": "ctt-express",
      "slug": "sledzenie-ctt-express",
      "title": "Śledzenie CTT Express: statusy po polsku, punkty i kontakt",
      "name": "CTT Express",
      "color": "#df0024"
    },
    {
      "id": "dpd-fr",
      "catalog": "dpd-fr",
      "slug": "sledzenie-dpd-fr",
      "title": "Śledzenie DPD France: numery, statusy i punkty Pickup",
      "name": "DPD France",
      "color": "#dc0032"
    },
    {
      "id": "gls-fr",
      "catalog": "gls-fr",
      "slug": "sledzenie-gls-france",
      "title": "GLS France śledzenie: statusy, Relais GLS i kontakt",
      "name": "GLS France",
      "color": "#ffdd00"
    },
    {
      "id": "gls-it",
      "catalog": "gls-it",
      "slug": "sledzenie-gls-italy",
      "title": "Śledzenie GLS Italy: numery, statusy i nieudane dostawy",
      "name": "GLS Italy",
      "color": "#ffdd00"
    },
    {
      "id": "poczta-polska",
      "catalog": "poczta-polska",
      "slug": "poczta-polska-sledzenie",
      "title": "Poczta Polska śledzenie: numer, statusy, awizo i kontakt",
      "name": "Poczta Polska",
      "color": "#c43c2b"
    },
    {
      "id": "orlen-paczka",
      "catalog": "orlen-paczka",
      "slug": "orlen-paczka-sledzenie",
      "title": "ORLEN Paczka śledzenie: numer, statusy i odbiór paczki",
      "name": "ORLEN Paczka",
      "color": "#db3830"
    },
    {
      "id": "austrian-post",
      "catalog": "austrian-post",
      "slug": "sledzenie-austrian-post",
      "title": "Śledzenie Austrian Post: numery, statusy i odbiór",
      "name": "Austrian Post",
      "color": "#ffdc00"
    },
    {
      "id": "tipsa",
      "catalog": "tipsa",
      "slug": "sledzenie-tipsa",
      "title": "Śledzenie TIPSA: 22-cyfrowy numer, statusy i doręczenia",
      "name": "TIPSA",
      "color": "#001e62"
    },
    {
      "id": "yanwen",
      "catalog": "yanwen",
      "slug": "yanwen-sledzenie",
      "title": "Yanwen śledzenie przesyłki: statusy, kurier, kontakt",
      "name": "Yanwen",
      "color": "#8cbe1e"
    },
    {
      "id": "an-post",
      "catalog": "an-post",
      "slug": "sledzenie-an-post",
      "title": "Śledzenie An Post: statusy, cło i kontakt w Irlandii",
      "name": "An Post",
      "color": "#00a76a"
    },
    {
      "id": "vinted-go",
      "catalog": "vinted-go",
      "slug": "vinted-go-sledzenie",
      "title": "Vinted Go śledzenie przesyłki: statusy, automaty i pomoc",
      "name": "Vinted Go",
      "color": "#004654"
    },
    {
      "id": "amazon-logistics",
      "catalog": "amazon-logistics",
      "slug": "amazon-logistics-sledzenie-przesylki",
      "title": "Amazon Logistics tracking: gdzie śledzić paczkę Amazon",
      "name": "Amazon Logistics",
      "color": "#ff9900"
    },
    {
      "id": "dpd-uk",
      "catalog": "dpd-uk",
      "slug": "sledzenie-dpd-uk",
      "title": "Śledzenie DPD UK: 14-cyfrowy numer, statusy i kontakt",
      "name": "DPD UK",
      "color": "#dc0032"
    },
    {
      "id": "parcelforce",
      "catalog": "parcelforce",
      "slug": "sledzenie-parcelforce-worldwide",
      "title": "Śledzenie Parcelforce Worldwide: statusy, cło i kontakt",
      "name": "Parcelforce Worldwide",
      "color": "#9d0f11"
    },
    {
      "id": "bpost",
      "catalog": "bpost",
      "slug": "bpost-sledzenie",
      "title": "Śledzenie bpost: kod kreskowy, statusy i punkty odbioru",
      "name": "bpost",
      "color": "#ef2637"
    },
    {
      "id": "dpd-ie",
      "catalog": "dpd-ie",
      "slug": "sledzenie-dpd-ireland",
      "title": "Śledzenie DPD Ireland: statusy, punkty Pickup i kontakt",
      "name": "DPD Ireland",
      "color": "#dc0032"
    },
    {
      "id": "gls-es",
      "catalog": "gls-es",
      "slug": "sledzenie-gls-hiszpania",
      "title": "Śledzenie GLS Hiszpania: statusy, punkty i kontakt",
      "name": "GLS Spain",
      "color": "#ffdd00"
    },
    {
      "id": "dhl-ecommerce-es",
      "catalog": "dhl-ecommerce-es",
      "slug": "sledzenie-dhl-ecommerce-iberia",
      "title": "Śledzenie DHL eCommerce Iberia: statusy i ServicePoint",
      "name": "DHL eCommerce Iberia",
      "color": "#ffcc00"
    },
    {
      "id": "dhl-ecommerce-pl",
      "catalog": "dhl-ecommerce-pl",
      "slug": "dhl-ecommerce-poland-sledzenie-przesylki",
      "title": "DHL eCommerce Poland śledzenie przesyłki: statusy i punkty",
      "name": "DHL eCommerce Poland",
      "color": "#ffcc00"
    },
    {
      "id": "dhl-ecommerce-uk",
      "catalog": "dhl-ecommerce-uk",
      "slug": "sledzenie-dhl-ecommerce-uk",
      "title": "Śledzenie DHL eCommerce UK: statusy i kontakt",
      "name": "DHL eCommerce UK",
      "color": "#ffcc00"
    },
    {
      "id": "gofo-fr",
      "catalog": "gofo-fr",
      "slug": "gofo-france-sledzenie",
      "title": "Śledzenie GOFO France: numery GFFR, statusy i pomoc",
      "name": "GOFO France",
      "color": "#fc4c02"
    },
    {
      "id": "gofo-it",
      "catalog": "gofo-it",
      "slug": "sledzenie-gofo-italy",
      "title": "Śledzenie GOFO Italy: numery GFIT, statusy i reklamacje",
      "name": "GOFO Italy",
      "color": "#fc4c02"
    },
    {
      "id": "speedpak",
      "catalog": "speedpak",
      "slug": "speedpak-sledzenie",
      "title": "SpeedPAK śledzenie paczki: statusy, eBay i kto doręcza",
      "name": "SpeedPAK",
      "color": "#ee7532"
    },
    {
      "id": "asendia",
      "catalog": "asendia",
      "slug": "asendia-sledzenie-przesylek",
      "title": "Asendia śledzenie przesyłek: statusy, doręczenie i cło",
      "name": "Asendia",
      "color": "#005d69"
    },
    {
      "id": "colisweb",
      "catalog": "colisweb",
      "slug": "sledzenie-colisweb",
      "title": "Śledzenie Colisweb: komunikaty po francusku i okna dostaw",
      "name": "Colisweb",
      "color": "#15cbe3"
    },
    {
      "id": "ciblex",
      "catalog": "ciblex",
      "slug": "sledzenie-ciblex",
      "title": "Śledzenie Ciblex: numery, francuskie statusy i kontakt",
      "name": "Ciblex",
      "color": "#0160ab"
    },
    {
      "id": "hermes-einrichtungs-service",
      "catalog": "hermes",
      "slug": "sledzenie-hermes-einrichtungs-service",
      "title": "Śledzenie Hermes Einrichtungs-Service: meble i termin",
      "name": "Hermes Einrichtungs-Service",
      "color": "#0091cd"
    },
    {
      "id": "landmark-global",
      "catalog": "landmark-global",
      "slug": "landmark-global-sledzenie-przesylek",
      "title": "Landmark Global śledzenie przesyłek: LTN, statusy i cło",
      "name": "Landmark Global",
      "color": "#e62337"
    },
    {
      "id": "dhl-ecommerce",
      "catalog": "dhl-ecommerce",
      "slug": "sledzenie-dhl-ecommerce",
      "title": "Śledzenie DHL eCommerce: numery GM, statusy i kontakt",
      "name": "DHL eCommerce",
      "color": "#ffcc00"
    },
    {
      "id": "allegro-one",
      "catalog": "allegro-one",
      "slug": "allegro-one-sledzenie",
      "title": "Allegro One śledzenie przesyłki: One Box, Punkt i Kurier",
      "name": "Allegro One",
      "color": "#006d57"
    },
    {
      "id": "cacesa",
      "catalog": "cacesa",
      "slug": "sledzenie-cacesa",
      "title": "Śledzenie CACESA: cło, statusy i kto doręcza paczkę",
      "name": "CACESA",
      "color": "#C9032D"
    },
    {
      "id": "sunyou",
      "catalog": "sunyou",
      "slug": "sunyou-sledzenie",
      "title": "SunYou tracking: numer SY, statusy i kontakt",
      "name": "SunYou",
      "color": "#02a232"
    },
    {
      "id": "amazon-shipping",
      "catalog": "amazon-shipping",
      "slug": "amazon-shipping-sledzenie",
      "title": "Amazon Shipping śledzenie: numer przesyłki, statusy i pomoc",
      "name": "Amazon Shipping",
      "color": "#ff9900"
    },
    {
      "id": "postnord",
      "catalog": "postnord",
      "slug": "sledzenie-postnord",
      "title": "Śledzenie PostNord: numery, statusy i odbiór",
      "name": "PostNord",
      "color": "#00a0d6"
    },
    {
      "id": "bring",
      "catalog": "bring-posten",
      "slug": "sledzenie-bring",
      "title": "Śledzenie Bring (Posten): numery, statusy i odbiór",
      "name": "Bring",
      "color": "#56b529"
    },
    {
      "id": "posti",
      "catalog": "posti",
      "slug": "sledzenie-posti",
      "title": "Śledzenie Posti: kod JJFI, statusy, automaty i kontakt",
      "name": "Posti",
      "color": "#ff8000"
    },
    {
      "id": "omniva",
      "catalog": "omniva",
      "slug": "omniva-sledzenie",
      "title": "Omniva śledzenie paczki: kody, statusy, automaty i kontakt",
      "name": "Omniva",
      "color": "#f86300"
    },
    {
      "id": "ppl",
      "catalog": "ppl",
      "slug": "sledzenie-ppl",
      "title": "Śledzenie PPL: 11-cyfrowy numer, statusy i PPL boxy",
      "name": "PPL",
      "color": "#004b93"
    },
    {
      "id": "box-now",
      "catalog": "box-now",
      "slug": "sledzenie-box-now",
      "title": "Śledzenie BOX NOW: automaty, kody PIN i statusy",
      "name": "BOX NOW",
      "color": "#44d62c"
    },
    {
      "id": "dhl-ecommerce-nl",
      "catalog": "dhl-ecommerce-nl",
      "slug": "sledzenie-dhl-ecommerce-netherlands",
      "title": "Śledzenie DHL eCommerce Holandia: statusy i kontakt",
      "name": "DHL eCommerce Netherlands",
      "color": "#ffcc00"
    },
    {
      "id": "heppner",
      "catalog": "heppner",
      "slug": "sledzenie-heppner",
      "title": "Śledzenie Heppner: numer, kod pocztowy i terminy dostawy",
      "name": "Heppner",
      "color": "#005baa"
    },
    {
      "id": "c-chez-vous",
      "catalog": "c-chez-vous",
      "slug": "sledzenie-c-chez-vous",
      "title": "Śledzenie C Chez Vous: numer zamówienia, statusy i kontakt",
      "name": "C chez vous",
      "color": "#e50046"
    },
    {
      "id": "geodis",
      "catalog": "geodis",
      "slug": "geodis-sledzenie",
      "title": "Śledzenie przesyłek GEODIS: numer 1G, statusy i kontakt",
      "name": "GEODIS",
      "color": "#3100e6"
    },
    {
      "id": "delivengo",
      "catalog": "delivengo",
      "slug": "delivengo-tracking",
      "title": "Śledzenie Delivengo: paczka La Poste wysłana z Francji",
      "name": "Delivengo",
      "color": "#ffcf00"
    },
    {
      "id": "ecoscooting",
      "catalog": "ecoscooting",
      "slug": "ecoscooting-sledzenie",
      "title": "Ecoscooting śledzenie przesyłek: statusy, punkty i kontakt",
      "name": "Ecoscooting",
      "color": "#3fa7ff"
    },
    {
      "id": "swiss-post-cargo",
      "catalog": "swiss-post-cargo",
      "slug": "sledzenie-swiss-post-cargo",
      "title": "Śledzenie Swiss Post Cargo: statusy, dostawa i kontakt",
      "name": "Swiss Post Cargo",
      "color": "#ffcc00"
    },
    {
      "id": "dachser",
      "catalog": "dachser",
      "slug": "sledzenie-dachser",
      "title": "Dachser śledzenie Hiszpania i Portugalia: link i statusy",
      "name": "Dachser",
      "color": "#005ca9"
    },
    {
      "id": "meest",
      "catalog": "meest",
      "slug": "meest-sledzenie-przesylki",
      "title": "Meest tracking: śledzenie przesyłki, statusy i kontakt",
      "name": "Meest",
      "color": "#0061af"
    },
    {
      "id": "nova-poshta",
      "catalog": "nova-poshta",
      "slug": "nova-poshta-sledzenie",
      "title": "Nova Poshta (Nova Post) śledzenie: numer, statusy, odbiór",
      "name": "Nova Poshta",
      "color": "#e30613"
    },
    {
      "id": "sameday",
      "catalog": "sameday",
      "slug": "sledzenie-sameday",
      "title": "Śledzenie Sameday: numer AWB, easybox i statusy",
      "name": "Sameday",
      "color": "#e92427"
    },
    {
      "id": "speedy",
      "catalog": "speedy",
      "slug": "sledzenie-speedy",
      "title": "Śledzenie Speedy: numer listu, statusy i automaty paczkowe",
      "name": "Speedy",
      "color": "#dc0032"
    },
    {
      "id": "cargus",
      "catalog": "cargus",
      "slug": "sledzenie-cargus",
      "title": "Śledzenie Cargus: numer AWB, statusy i SHIP & GO",
      "name": "Cargus",
      "color": "#f68121"
    },
    {
      "id": "fan-courier",
      "catalog": "fan-courier",
      "slug": "sledzenie-fan-courier",
      "title": "Śledzenie FAN Courier: numery AWB, statusy i FANbox",
      "name": "FAN Courier",
      "color": "#0f2345"
    },
    {
      "id": "econt",
      "catalog": "econt",
      "slug": "sledzenie-econt",
      "title": "Śledzenie przesyłek Econt: numer, statusy i oddziały",
      "name": "Econt",
      "color": "#234182"
    },
    {
      "id": "ceska-posta",
      "catalog": "ceska-posta",
      "slug": "sledzenie-ceska-posta",
      "title": "Śledzenie Česká pošta (Poczta Czeska) i Balíkovna",
      "name": "Česká pošta",
      "color": "#002776"
    },
    {
      "id": "express-one",
      "catalog": "express-one",
      "slug": "sledzenie-express-one",
      "title": "Śledzenie Express One: statusy, punkty i kontakt",
      "name": "Express One",
      "color": "#ec6500"
    },
    {
      "id": "foxpost",
      "catalog": "foxpost",
      "slug": "sledzenie-foxpost",
      "title": "Śledzenie Foxpost: statusy, automaty i kontakt",
      "name": "Foxpost",
      "color": "#b70100"
    },
    {
      "id": "slovak-parcel-service",
      "catalog": "slovak-parcel-service",
      "slug": "sledzenie-slovak-parcel-service",
      "title": "Śledzenie Slovak Parcel Service (SPS): statusy i pomoc",
      "name": "Slovak Parcel Service",
      "color": "#f58220"
    },
    {
      "id": "slovenska-posta",
      "catalog": "slovenska-posta",
      "slug": "sledzenie-slovenska-posta",
      "title": "Śledzenie Slovenská pošta: numery, statusy i BalíkoBOX",
      "name": "Slovenská pošta",
      "color": "#f9bb00"
    },
    {
      "id": "posta-slovenije",
      "catalog": "posta-slovenije",
      "slug": "sledzenie-posta-slovenije",
      "title": "Śledzenie Pošta Slovenije: statusy, odbiór i kontakt",
      "name": "Pošta Slovenije",
      "color": "#ffc600"
    },
    {
      "id": "hrvatska-posta",
      "catalog": "hrvatska-posta",
      "slug": "sledzenie-hrvatska-posta",
      "title": "Śledzenie Hrvatska pošta: statusy, automaty i kontakt",
      "name": "Hrvatska pošta",
      "color": "#ffd100"
    },
    {
      "id": "posta-srbije",
      "catalog": "posta-srbije",
      "slug": "sledzenie-posta-srbije",
      "title": "Śledzenie Pošta Srbije: Poczta Serbska, statusy i kontakt",
      "name": "Pošta Srbije",
      "color": "#fbba00"
    },
    {
      "id": "venipak",
      "catalog": "venipak",
      "slug": "sledzenie-venipak",
      "title": "Śledzenie Venipak: statusy, automaty paczkowe i kontakt",
      "name": "Venipak",
      "color": "#9339f2"
    },
    {
      "id": "lietuvos-pastas",
      "catalog": "lietuvos-pastas",
      "slug": "sledzenie-lietuvos-pastas",
      "title": "Śledzenie Lietuvos paštas (Poczta Litewska) i LP Express",
      "name": "Lietuvos paštas",
      "color": "#fedd00"
    },
    {
      "id": "latvijas-pasts",
      "catalog": "latvijas-pasts",
      "slug": "sledzenie-latvijas-pasts",
      "title": "Śledzenie Latvijas Pasts: numery, statusy i automaty",
      "name": "Latvijas Pasts",
      "color": "#0c72ba"
    },
    {
      "id": "dao",
      "catalog": "dao",
      "slug": "sledzenie-dao",
      "title": "Śledzenie DAO: statusy, daoSHOP i nieudane doręczenia",
      "name": "DAO",
      "color": "#e30613"
    },
    {
      "id": "helthjem",
      "catalog": "helthjem",
      "slug": "sledzenie-helthjem",
      "title": "Śledzenie Helthjem: statusy, punkty odbioru i kontakt",
      "name": "Helthjem",
      "color": "#ffb202"
    },
    {
      "id": "matkahuolto",
      "catalog": "matkahuolto",
      "slug": "sledzenie-matkahuolto",
      "title": "Śledzenie Matkahuolto: statusy, punkty odbioru i kontakt",
      "name": "Matkahuolto",
      "color": "#001e60"
    },
    {
      "id": "ukrposhta",
      "catalog": "ukrposhta",
      "slug": "sledzenie-ukrposhta",
      "title": "Śledzenie Ukrposhta: numery, statusy i odbiór w placówce",
      "name": "Ukrposhta",
      "color": "#ffbe0c"
    },
    {
      "id": "acs-courier",
      "catalog": "acs-courier",
      "slug": "sledzenie-acs-courier",
      "title": "Śledzenie ACS Courier: numery, statusy i kontakt",
      "name": "ACS Courier",
      "color": "#e42229"
    },
    {
      "id": "elta",
      "catalog": "elta",
      "slug": "sledzenie-elta-hellenic-post",
      "title": "Śledzenie ELTA Hellenic Post: statusy, odbiór i kontakt",
      "name": "ELTA Hellenic Post",
      "color": "#0063a9"
    },
    {
      "id": "geniki-taxydromiki",
      "catalog": "geniki-taxydromiki",
      "slug": "sledzenie-geniki-taxydromiki",
      "title": "Śledzenie Geniki Taxydromiki: numery, statusy i kontakt",
      "name": "Geniki Taxydromiki",
      "color": "#004fa0"
    },
    {
      "id": "cyprus-post",
      "catalog": "cyprus-post",
      "slug": "sledzenie-cyprus-post",
      "title": "Śledzenie Cyprus Post: numery, statusy i cło",
      "name": "Cyprus Post",
      "color": "#e4a50e"
    },
    {
      "id": "post-luxembourg",
      "catalog": "post-luxembourg",
      "slug": "sledzenie-post-luxembourg",
      "title": "Śledzenie POST Luxembourg: numery, statusy i kontakt",
      "name": "POST Luxembourg",
      "color": "#008736"
    },
    {
      "id": "maltapost",
      "catalog": "maltapost",
      "slug": "sledzenie-maltapost",
      "title": "Śledzenie MaltaPost: numery, statusy i odbiór",
      "name": "MaltaPost",
      "color": "#d43b2c"
    },
    {
      "id": "whistl",
      "catalog": "whistl",
      "slug": "sledzenie-whistl",
      "title": "Śledzenie Whistl: kto doręcza paczkę i co zrobić",
      "name": "Whistl",
      "color": "#fd4f00"
    },
    {
      "id": "xdp",
      "catalog": "xdp",
      "slug": "sledzenie-xdp",
      "title": "Śledzenie XDP: numery przesyłek, statusy i doręczenie",
      "name": "XDP",
      "color": "#53b6ed"
    },
    {
      "id": "apc-overnight",
      "catalog": "apc-overnight",
      "slug": "sledzenie-apc-overnight",
      "title": "Śledzenie APC Overnight: numery, statusy i doręczenia",
      "name": "APC Overnight",
      "color": "#243269"
    },
    {
      "id": "ups",
      "catalog": "ups",
      "slug": "ups-tracking-sledzenie-paczki",
      "title": "UPS tracking: śledzenie paczki w Polsce, statusy, reklamacje",
      "name": "UPS",
      "color": "#351c15"
    },
    {
      "id": "fedex",
      "catalog": "fedex",
      "slug": "fedex-sledzenie",
      "title": "FedEx śledzenie: numer przesyłki, statusy i cło",
      "name": "FedEx",
      "color": "#4d148c"
    },
    {
      "id": "dhl-express",
      "catalog": "dhl-express",
      "slug": "dhl-express-sledzenie-przesylki",
      "title": "DHL Express śledzenie przesyłki: numer, statusy i kontakt",
      "name": "DHL Express",
      "color": "#ffcc00"
    },
    {
      "id": "dhl",
      "catalog": "dhl",
      "slug": "dhl-paket-sledzenie-przesylki",
      "title": "DHL Paket śledzenie przesyłki: numer, statusy i odbiór",
      "name": "DHL Paket",
      "color": "#ffcc00"
    },
    {
      "id": "la-poste",
      "catalog": "la-poste",
      "slug": "sledzenie-colissimo-la-poste",
      "title": "Śledzenie Colissimo i La Poste: statusy, doręczenie i cło",
      "name": "La Poste / Colissimo",
      "color": "#ffcf00"
    },
    {
      "id": "correos-spain",
      "catalog": "correos-spain",
      "slug": "correos-hiszpania-sledzenie",
      "title": "Correos śledzenie przesyłek: paczki z Hiszpanii do Polski",
      "name": "Correos",
      "color": "#ffcd00"
    },
    {
      "id": "royal-mail",
      "catalog": "royal-mail",
      "slug": "sledzenie-royal-mail",
      "title": "Śledzenie Royal Mail: numer GB, statusy, cło i doręczenie",
      "name": "Royal Mail",
      "color": "#da202a"
    },
    {
      "id": "usps",
      "catalog": "usps",
      "slug": "sledzenie-usps",
      "title": "Śledzenie przesyłek USPS: numer, statusy i paczki z USA",
      "name": "USPS",
      "color": "#004b87"
    },
    {
      "id": "canada-post",
      "catalog": "canada-post",
      "slug": "canada-post-tracking",
      "title": "Śledzenie Canada Post: numery, statusy, awizo i cło",
      "name": "Canada Post",
      "color": "#e4002b"
    },
    {
      "id": "purolator",
      "catalog": "purolator",
      "slug": "sledzenie-purolator",
      "title": "Śledzenie Purolator: PIN, statusy i odbiór paczek",
      "name": "Purolator",
      "color": "#da291c"
    },
    {
      "id": "intelcom",
      "catalog": "intelcom",
      "slug": "sledzenie-intelcom-dragonfly",
      "title": "Śledzenie Intelcom / Dragonfly: numery, statusy, kontakt",
      "name": "Intelcom / Dragonfly",
      "color": "#13a58f"
    },
    {
      "id": "canpar",
      "catalog": "canpar",
      "slug": "sledzenie-canpar",
      "title": "Śledzenie Canpar: numery, statusy i odbiór SMARTSpot",
      "name": "Canpar",
      "color": "#003b70"
    },
    {
      "id": "uniuni",
      "catalog": "uniuni",
      "slug": "uniuni-tracking",
      "title": "Śledzenie UniUni: numery, statusy, zdjęcia i kontakt",
      "name": "UniUni",
      "color": "#f68841"
    },
    {
      "id": "ontrac",
      "catalog": "ontrac",
      "slug": "sledzenie-ontrac",
      "title": "Śledzenie przesyłek OnTrac: numery, statusy i doręczenia",
      "name": "OnTrac",
      "color": "#cc2027"
    },
    {
      "id": "lso",
      "catalog": "lso",
      "slug": "sledzenie-lso",
      "title": "Śledzenie LSO: numery airbill, statusy i doręczenia",
      "name": "LSO",
      "color": "#0047ba"
    },
    {
      "id": "gofo",
      "catalog": "gofo",
      "slug": "gofo-express-sledzenie",
      "title": "GOFO Express śledzenie: numer GFUS, statusy i dostawa w USA",
      "name": "GOFO Express",
      "color": "#fc4c02"
    },
    {
      "id": "speedx",
      "catalog": "speedx",
      "slug": "sledzenie-speedx",
      "title": "Śledzenie SpeedX: numery SPX, statusy i obsługa klienta",
      "name": "SpeedX",
      "color": "#1aa3ff"
    },
    {
      "id": "australia-post",
      "catalog": "australia-post",
      "slug": "sledzenie-australia-post",
      "title": "Śledzenie Australia Post: numery, statusy i automaty",
      "name": "Australia Post",
      "color": "#dc1928"
    },
    {
      "id": "aramex-au",
      "catalog": "aramex-au",
      "slug": "sledzenie-aramex-australia",
      "title": "Śledzenie Aramex Australia: numery, statusy i kontakt",
      "name": "Aramex Australia",
      "color": "#dc291e"
    },
    {
      "id": "couriersplease",
      "catalog": "couriersplease",
      "slug": "sledzenie-couriersplease",
      "title": "Śledzenie CouriersPlease: numery, statusy i pomoc",
      "name": "CouriersPlease",
      "color": "#c21e36"
    },
    {
      "id": "nz-post",
      "catalog": "nz-post",
      "slug": "sledzenie-nz-post",
      "title": "Śledzenie NZ Post: numery, statusy i odbiór paczek",
      "name": "NZ Post",
      "color": "#d8291c"
    },
    {
      "id": "aramex-nz",
      "catalog": "aramex-nz",
      "slug": "sledzenie-aramex-nowa-zelandia",
      "title": "Śledzenie Aramex Nowa Zelandia: numery, statusy i kontakt",
      "name": "Aramex New Zealand",
      "color": "#dc291e"
    },
    {
      "id": "magyar-posta",
      "catalog": "magyar-posta",
      "slug": "sledzenie-magyar-posta",
      "title": "Śledzenie Magyar Posta: statusy węgierskie i PostaPont",
      "name": "Magyar Posta",
      "color": "#346631"
    },
    {
      "id": "shiprocket",
      "catalog": "shiprocket",
      "slug": "sledzenie-shiprocket",
      "title": "Śledzenie Shiprocket: numery AWB, statusy i RTO",
      "name": "Shiprocket",
      "color": "#735ae5"
    },
    {
      "id": "first-line",
      "catalog": "first-line",
      "slug": "sledzenie-first-line",
      "title": "Śledzenie FIRST LINE: numery 1ST, statusy i doręczenie",
      "name": "FIRST LINE",
      "color": "#55207a"
    },
    {
      "id": "j-and-t-de",
      "catalog": "j-and-t-de",
      "slug": "jt-express-niemcy-sledzenie",
      "title": "J&T Express Germany śledzenie: numer JTDE i statusy",
      "name": "J&T Express Germany",
      "color": "#e60000"
    },
    {
      "id": "kun-express",
      "catalog": "kun-express",
      "slug": "sledzenie-kun-express",
      "title": "Śledzenie Kun Express: numery KUN, statusy i USPS",
      "name": "Kun Express",
      "color": "#0761ff"
    },
    {
      "id": "juxi-express",
      "catalog": "juxi-express",
      "slug": "sledzenie-przesylek-juxi-express",
      "title": "Śledzenie przesyłek Juxi Express: numer JXC, dostawa i cło",
      "name": "Juxi Express",
      "color": "#e2393c"
    },
    {
      "id": "jcex",
      "catalog": "jcex",
      "slug": "jcex-sledzenie",
      "title": "JCEX śledzenie przesyłki: numer JCZ, statusy, kto doręcza",
      "name": "JCEX",
      "color": "#0080cc"
    },
    {
      "id": "lingdong",
      "catalog": "lingdong",
      "slug": "sledzenie-lingdong",
      "title": "Śledzenie Lingdong International: numer SZLD i statusy",
      "name": "Lingdong International",
      "color": "#0171bb"
    },
    {
      "id": "j-and-t-au",
      "catalog": "j-and-t-au",
      "slug": "sledzenie-jt-express-australia",
      "title": "Śledzenie J&T Express Australia: numery JTAU i statusy",
      "name": "J&T Express Australia",
      "color": "#e60000"
    },
    {
      "id": "china-post",
      "catalog": "china-post",
      "slug": "china-post-sledzenie",
      "title": "China Post śledzenie przesyłki: numer, statusy, doręczenie",
      "name": "China Post",
      "color": "#006d46"
    },
    {
      "id": "fedex-pl",
      "catalog": "fedex-pl",
      "slug": "fedex-polska-sledzenie-przesylki",
      "title": "FedEx Polska śledzenie przesyłki krajowej: numer i statusy",
      "name": "FedEx Poland",
      "color": "#4d148c"
    },
    {
      "id": "techlink",
      "catalog": "techlink",
      "slug": "sledzenie-techlink",
      "title": "Śledzenie Techlink: numery ZHPHD…ML, statusy i dostawa",
      "name": "Techlink",
      "color": "#e73547"
    },
    {
      "id": "z-leading",
      "catalog": "z-leading",
      "slug": "sledzenie-z-leading",
      "title": "Śledzenie Z-Leading (Zhongling): numery, dostawa i pomoc",
      "name": "Z-Leading",
      "color": "#005ab0"
    },
    {
      "id": "imile",
      "catalog": "imile",
      "slug": "imile-sledzenie",
      "title": "Śledzenie iMile: numery, statusy i dostawa w Polsce",
      "name": "iMile",
      "color": "#0032a0"
    },
    {
      "id": "postnl",
      "catalog": "spring-gds",
      "slug": "postnl-sledzenie-przesylki",
      "title": "PostNL śledzenie przesyłki: numer, statusy, doręczenie",
      "name": "PostNL",
      "color": "#ef7d00"
    },
    {
      "id": "dhlink",
      "catalog": "dhlink",
      "slug": "dhlink-sledzenie",
      "title": "Śledzenie DHLink: numer z DHgate, statusy i doręczenie",
      "name": "DHLink",
      "color": "#143167"
    },
    {
      "id": "dpd-pt",
      "catalog": "dpd-pt",
      "slug": "sledzenie-dpd-portugal",
      "title": "Śledzenie DPD Portugal: statusy, punkty Pickup i kontakt",
      "name": "DPD Portugal",
      "color": "#dc0032"
    },
    {
      "id": "tnt-it",
      "catalog": "tnt-it",
      "slug": "sledzenie-tnt-wlochy",
      "title": "Śledzenie TNT Włochy: list przewozowy, statusy i odbiór",
      "name": "TNT Italy",
      "color": "#ff6600"
    },
    {
      "id": "tnt",
      "catalog": "tnt",
      "slug": "tnt-sledzenie-przesylki",
      "title": "TNT śledzenie przesyłki: numer, statusy i doręczenie",
      "name": "TNT",
      "color": "#ff6600"
    },
    {
      "id": "sf-express",
      "catalog": "sf-express",
      "slug": "sledzenie-sf-express",
      "title": "Śledzenie SF Express: numer listu, statusy, cło i dostawa",
      "name": "SF Express",
      "color": "#dc1e32"
    },
    {
      "id": "straightship",
      "catalog": "straightship",
      "slug": "sledzenie-przesylek-straightship",
      "title": "Śledzenie przesyłek Straightship: numery STRTD i doręczenia",
      "name": "Straightship",
      "color": "#6aae66"
    },
    {
      "id": "correios-br",
      "catalog": "correios-br",
      "slug": "sledzenie-correios-brazylia",
      "title": "Śledzenie Correios Brazylia: kody, statusy i cło",
      "name": "Correios Brazil",
      "color": "#ffd500"
    },
    {
      "id": "yto",
      "catalog": "yto",
      "slug": "sledzenie-yto-express",
      "title": "Śledzenie YTO Express: numer YT, statusy i dostawa",
      "name": "YTO Express",
      "color": "#4d148c"
    },
    {
      "id": "spx-br",
      "catalog": "spx-br",
      "slug": "sledzenie-spx-express-brazylia",
      "title": "Śledzenie SPX Express Brazylia: zamówienia Shopee i statusy",
      "name": "SPX Express Brazil",
      "color": "#ee4d2d"
    },
    {
      "id": "eushipments",
      "catalog": "eushipments",
      "slug": "sledzenie-eushipments",
      "title": "Śledzenie euShipments: statusy i lokalny kurier",
      "name": "euShipments",
      "color": "#0a8fcf"
    },
    {
      "id": "mhi",
      "catalog": "mhi",
      "slug": "sledzenie-mhi",
      "title": "Śledzenie MHI: statusy Trackmail i kto doręcza",
      "name": "MHI",
      "color": "#00075c"
    },
    {
      "id": "pengyou",
      "catalog": "pengyou",
      "slug": "sledzenie-pengyou",
      "title": "Śledzenie Pengyou: numery PY, statusy i kontakt",
      "name": "Pengyou",
      "color": "#043a82"
    },
    {
      "id": "linhan",
      "catalog": "linhan",
      "slug": "sledzenie-linhan",
      "title": "Śledzenie LinHan International: numery LHGJ i statusy",
      "name": "LinHan International",
      "color": "#eb5c20"
    },
    {
      "id": "mtscn",
      "catalog": "mtscn",
      "slug": "meiteshun-sledzenie",
      "title": "Śledzenie Meiteshun (MTSCN): numery AMTS i statusy",
      "name": "Meiteshun Logistics",
      "color": "#f89f3b"
    },
    {
      "id": "cttyex",
      "catalog": "cttyex",
      "slug": "sledzenie-cttyex",
      "title": "Śledzenie CTTYEX: numery CTTY…CN, statusy i czas pekiński",
      "name": "CTTYEX",
      "color": "#0068AC"
    },
    {
      "id": "speedaf",
      "catalog": "speedaf",
      "slug": "sledzenie-speedaf",
      "title": "Śledzenie przesyłek Speedaf Express: numery i statusy",
      "name": "Speedaf",
      "color": "#ff8300"
    },
    {
      "id": "yilong-logistics",
      "catalog": "yilong-logistics",
      "slug": "sledzenie-yilong-logistics",
      "title": "Śledzenie Yilong Logistics: numery YIL…WL i statusy",
      "name": "Yilong Logistics",
      "color": "#ec0307"
    },
    {
      "id": "quick-rabbit",
      "catalog": "quick-rabbit",
      "slug": "sledzenie-quick-rabbit",
      "title": "Śledzenie Quick Rabbit: kody PKG, statusy i Citibox",
      "name": "Quick Rabbit",
      "color": "#f6851f"
    },
    {
      "id": "huaya-express",
      "catalog": "huaya-express",
      "slug": "sledzenie-huaya-express",
      "title": "Śledzenie Huaya Express: numery HYYT…GZ, statusy i dostawa",
      "name": "Huaya Express",
      "color": "#e60013"
    },
    {
      "id": "gofo-ca",
      "catalog": "gofo-ca",
      "slug": "sledzenie-gofo-ca",
      "title": "Śledzenie GOFO Canada: numery GFCA, statusy i opóźnienia",
      "name": "GOFO Canada",
      "color": "#fc4c02"
    },
    {
      "id": "metropolitan-warehouse",
      "catalog": "metropolitan-warehouse",
      "slug": "sledzenie-metropolitan-warehouse",
      "title": "Śledzenie Metropolitan Warehouse (MWD): dostawy mebli",
      "name": "Metropolitan Warehouse & Delivery",
      "color": "#0062b7"
    },
    {
      "id": "topyou",
      "catalog": "topyou",
      "slug": "topyou-sledzenie",
      "title": "TopYou śledzenie przesyłki: numer TYZR, statusy",
      "name": "TopYou",
      "color": "#045cd7"
    },
    {
      "id": "byhd",
      "catalog": "byhd",
      "slug": "sledzenie-byhd",
      "title": "Śledzenie BYHD: numery BYHD, statusy i doręczyciel",
      "name": "BYHD",
      "color": "#084282"
    },
    {
      "id": "zto-international",
      "catalog": "zto-international",
      "slug": "sledzenie-zto-international",
      "title": "Śledzenie ZTO International: numery 771, statusy i kontakt",
      "name": "ZTO International",
      "color": "#3a8eff"
    },
    {
      "id": "mayiyinfu",
      "catalog": "mayiyinfu",
      "slug": "sledzenie-mayi-yinfu",
      "title": "Śledzenie Mayi Yinfu: numery MY, loty i doręczenie DHL",
      "name": "Mayi Yinfu",
      "color": "#000000"
    },
    {
      "id": "jrt",
      "catalog": "jrt",
      "slug": "jrt-sledzenie",
      "title": "JRT śledzenie przesyłki: numer JRT…MM, statusy i DHL",
      "name": "JRT",
      "color": "#023053"
    },
    {
      "id": "thhy",
      "catalog": "thhy",
      "slug": "sledzenie-tianhui-hengyuan",
      "title": "Śledzenie Tianhui Hengyuan (THHY): numery AT i skany",
      "name": "Tianhui Hengyuan",
      "color": "#00a2e9"
    },
    {
      "id": "js-express",
      "catalog": "js-express",
      "slug": "js-express-sledzenie",
      "title": "JS Express śledzenie przesyłki: numer JSE, statusy",
      "name": "JS Express",
      "color": "#fe9b00"
    },
    {
      "id": "wanbexpress",
      "catalog": "wanbexpress",
      "slug": "sledzenie-wanbexpress",
      "title": "Śledzenie WanbExpress: numery WNB…YQ, statusy i doręczenie",
      "name": "WanbExpress",
      "color": "#da381b"
    },
    {
      "id": "gzyl",
      "catalog": "gzyl",
      "slug": "gzyl-sledzenie",
      "title": "GZYL śledzenie przesyłki: numer YL…YQ, statusy i kontakt",
      "name": "GZYL",
      "color": "#4e79e1"
    },
    {
      "id": "shouwu",
      "catalog": "shouwu",
      "slug": "shouwu-sledzenie",
      "title": "Śledzenie Shouwu: numer SWECN…YQ, statusy i dostawa",
      "name": "Shouwu International",
      "color": "#be2c33"
    },
    {
      "id": "geodis-pl",
      "catalog": "geodis-pl",
      "slug": "geodis-polska-sledzenie",
      "title": "GEODIS Polska (Pekaes): śledzenie przesyłki i statusy",
      "name": "GEODIS Poland",
      "color": "#3100e6"
    },
    {
      "id": "sending",
      "catalog": "sending",
      "slug": "sledzenie-sending",
      "title": "Sending śledzenie w Hiszpanii: numer, statusy i telefon",
      "name": "Sending",
      "color": "#e52021"
    },
    {
      "id": "ywe",
      "catalog": "ywe",
      "slug": "sledzenie-ywe",
      "title": "Śledzenie YWE (YanwenExpress): numer, statusy i pomoc",
      "name": "YWE",
      "color": "#055aa1"
    },
    {
      "id": "roadie",
      "catalog": "roadie",
      "slug": "sledzenie-roadie",
      "title": "Śledzenie Roadie: numery 1R, statusy i zdjęcia z doręczenia",
      "name": "Roadie",
      "color": "#76cccd"
    },
    {
      "id": "ecms-express",
      "catalog": "ecms-express",
      "slug": "sledzenie-ecms-express",
      "title": "Śledzenie ECMS Express: 16-znakowe numery, cło i statusy",
      "name": "ECMS Express",
      "color": "#12823d"
    },
    {
      "id": "chunghwa-post",
      "catalog": "chunghwa-post",
      "slug": "chunghwa-post-tracking",
      "title": "Śledzenie Chunghwa Post: numer TW, statusy i odprawa celna",
      "name": "Chunghwa Post",
      "color": "#e8171f"
    },
    {
      "id": "raben",
      "catalog": "raben",
      "slug": "sledzenie-raben",
      "title": "Śledzenie Raben: numer przesyłki, statusy i dostawa",
      "name": "Raben",
      "color": "#df0020"
    },
    {
      "id": "jd-logistics",
      "catalog": "jd-logistics",
      "slug": "sledzenie-przesylek-jd-logistics",
      "title": "Śledzenie przesyłek JD Logistics: numery, statusy i kontakt",
      "name": "JD Logistics",
      "color": "#e1251b"
    },
    {
      "id": "singapore-post",
      "catalog": "singapore-post",
      "slug": "sledzenie-singapore-post",
      "title": "Śledzenie Singapore Post (SingPost): numery i statusy",
      "name": "Singapore Post",
      "color": "#2e5da8"
    },
    {
      "id": "zto",
      "catalog": "zto",
      "slug": "sledzenie-zto-express",
      "title": "Śledzenie ZTO Express: numery 中通快递, statusy i kontakt",
      "name": "ZTO Express",
      "color": "#0279ff"
    },
    {
      "id": "cne",
      "catalog": "cne",
      "slug": "sledzenie-cne-express",
      "title": "Śledzenie CNE Express: numery 3A5V, statusy i doręczenie",
      "name": "CNE Express",
      "color": "#e83828"
    },
    {
      "id": "japan-post",
      "catalog": "japan-post",
      "slug": "sledzenie-japan-post",
      "title": "Śledzenie Japan Post: numery EMS i znaczenie statusów",
      "name": "Japan Post",
      "color": "#cc0000"
    },
    {
      "id": "russian-post",
      "catalog": "russian-post",
      "slug": "sledzenie-russian-post",
      "title": "Russian Post śledzenie (Почта России): numery i statusy",
      "name": "Russian Post",
      "color": "#1928dc"
    },
    {
      "id": "huahan",
      "catalog": "huahan",
      "slug": "sledzenie-przesylek-huahan-logistics",
      "title": "Śledzenie HuaHan Logistics: numer HHW…YQ i doręczenie",
      "name": "HuaHan Logistics",
      "color": "#0069e8"
    },
    {
      "id": "epost-global",
      "catalog": "epost-global",
      "slug": "sledzenie-epost-global",
      "title": "Śledzenie ePost Global: numery EPG, statusy i doręczenie",
      "name": "ePost Global",
      "color": "#27967a"
    },
    {
      "id": "gls-at",
      "catalog": "gls-at",
      "slug": "sledzenie-gls-austria",
      "title": "Śledzenie GLS Austria: statusy, Parcel Shopy i kontakt",
      "name": "GLS Austria",
      "color": "#ffdd00"
    },
    {
      "id": "india-post",
      "catalog": "india-post",
      "slug": "sledzenie-india-post",
      "title": "Śledzenie India Post: numery Speed Post, statusy i cło",
      "name": "India Post",
      "color": "#b82025"
    },
    {
      "id": "morning-global",
      "catalog": "morning-global",
      "slug": "sledzenie-morning-global",
      "title": "Śledzenie Morning Global: numery BR…MG, statusy i kontakt",
      "name": "Morning Global",
      "color": "#050059"
    },
    {
      "id": "border-express",
      "catalog": "border-express",
      "slug": "sledzenie-border-express",
      "title": "Śledzenie Border Express (BEX): numery przesyłek i statusy",
      "name": "Border Express",
      "color": "#cc092f"
    },
    {
      "id": "delhivery",
      "catalog": "delhivery",
      "slug": "sledzenie-delhivery",
      "title": "Śledzenie przesyłek Delhivery: numery AWB, statusy i pomoc",
      "name": "Delhivery",
      "color": "#ed4136"
    },
    {
      "id": "aras-kargo",
      "catalog": "aras-kargo",
      "slug": "sledzenie-aras-kargo",
      "title": "Śledzenie Aras Kargo: numery i statusy po polsku",
      "name": "Aras Kargo",
      "color": "#dc0812"
    },
    {
      "id": "emirates-post",
      "catalog": "emirates-post",
      "slug": "sledzenie-emirates-post",
      "title": "Śledzenie Emirates Post: numery AE i statusy",
      "name": "Emirates Post",
      "color": "#004a98"
    },
    {
      "id": "blue-dart",
      "catalog": "blue-dart",
      "slug": "sledzenie-blue-dart",
      "title": "Blue Dart śledzenie: numery listów przewozowych i statusy",
      "name": "Blue Dart",
      "color": "#0059a9"
    },
    {
      "id": "ekart",
      "catalog": "ekart",
      "slug": "sledzenie-ekart",
      "title": "Śledzenie Ekart: numery ID, statusy InscannedAtDH i RTO",
      "name": "Ekart",
      "color": "#0074D9"
    },
    {
      "id": "j-and-t-middle-east",
      "catalog": "j-and-t-middle-east",
      "slug": "sledzenie-jt-express-middle-east",
      "title": "Śledzenie J&T Express Middle East: numery JTE i statusy",
      "name": "J&T Express Middle East",
      "color": "#e60000"
    },
    {
      "id": "dpd-ro",
      "catalog": "dpd-ro",
      "slug": "sledzenie-dpd-romania",
      "title": "Śledzenie DPD Romania: statusy, Smart Control i kontakt",
      "name": "DPD Romania",
      "color": "#dc0032"
    },
    {
      "id": "globalpost",
      "catalog": "globalpost",
      "slug": "sledzenie-globalpost",
      "title": "Śledzenie GlobalPost: numery GP, statusy i doręczenie",
      "name": "GlobalPost",
      "color": "#11003a"
    },
    {
      "id": "cdek",
      "catalog": "cdek",
      "slug": "sledzenie-cdek",
      "title": "Śledzenie CDEK (СДЭК): numery zamówień i statusy",
      "name": "CDEK",
      "color": "#00b33c"
    },
    {
      "id": "forza-delivery",
      "catalog": "forza-delivery",
      "slug": "sledzenie-forza-delivery",
      "title": "Śledzenie Forza Delivery: numery guía, statusy i kontakt",
      "name": "Forza Delivery Express",
      "color": "#ea5c1d"
    },
    {
      "id": "quantium-solutions",
      "catalog": "quantium-solutions",
      "slug": "sledzenie-quantium-solutions",
      "title": "Śledzenie Quantium Solutions: numery, statusy i pomoc",
      "name": "Quantium Solutions",
      "color": "#00474f"
    },
    {
      "id": "correos-chile",
      "catalog": "correos-chile",
      "slug": "sledzenie-correos-chile",
      "title": "Śledzenie Correos de Chile: numery i statusy",
      "name": "Correos de Chile",
      "color": "#d9232d"
    },
    {
      "id": "posta-shqiptare",
      "catalog": "posta-shqiptare",
      "slug": "sledzenie-posta-shqiptare",
      "title": "Śledzenie Posta Shqiptare: numery AL, statusy i cło",
      "name": "Posta Shqiptare",
      "color": "#1a3292"
    },
    {
      "id": "dsv",
      "catalog": "dsv",
      "slug": "sledzenie-dsv",
      "title": "Śledzenie DSV: numery przesyłek, statusy i DB Schenker",
      "name": "DSV",
      "color": "#002664"
    },
    {
      "id": "pin-ag",
      "catalog": "pin-ag",
      "slug": "sledzenie-pin-ag",
      "title": "Śledzenie PIN AG: listy polecone, paczki i odbiór w bazie",
      "name": "PIN AG",
      "color": "#003366"
    },
    {
      "id": "pitney-bowes",
      "catalog": "pitney-bowes",
      "slug": "sledzenie-pitney-bowes",
      "title": "Śledzenie Pitney Bowes: numery UPAA, UPBE i PBX",
      "name": "Pitney Bowes",
      "color": "#3e53a4"
    },
    {
      "id": "rl-carriers",
      "catalog": "rl-carriers",
      "slug": "sledzenie-rl-carriers",
      "title": "Śledzenie R+L Carriers: numer PRO, statusy i reklamacje",
      "name": "R+L Carriers",
      "color": "#006647"
    },
    {
      "id": "nox",
      "catalog": "nox",
      "slug": "sledzenie-nox",
      "title": "Śledzenie nox NachtExpress: statusy, miejsce dostawy",
      "name": "nox",
      "color": "#fadb00"
    },
    {
      "id": "far-international",
      "catalog": "far-international",
      "slug": "sledzenie-far-international",
      "title": "Śledzenie FAR International: numery, statusy i kontakt",
      "name": "FAR International",
      "color": "#003b75"
    },
    {
      "id": "jifan",
      "catalog": "jifan",
      "slug": "sledzenie-jifan",
      "title": "Śledzenie Jifan: numery JFGJ, statusy i czas pekiński",
      "name": "Jifan",
      "color": "#c81011"
    },
    {
      "id": "posta-romana",
      "catalog": "posta-romana",
      "slug": "sledzenie-posta-romana",
      "title": "Śledzenie Poșta Română: numery RO, statusy i cło",
      "name": "Poșta Română",
      "color": "#ea0029"
    },
    {
      "id": "dpd-nl",
      "catalog": "dpd-nl",
      "slug": "sledzenie-dpd-holandia",
      "title": "Śledzenie DPD Holandia: statusy, doręczenie i kontakt",
      "name": "DPD Netherlands",
      "color": "#dc0032"
    },
    {
      "id": "dpd-at",
      "catalog": "dpd-at",
      "slug": "sledzenie-dpd-austria",
      "title": "Śledzenie DPD Austria: statusy, punkty Pickup i kontakt",
      "name": "DPD Austria",
      "color": "#dc0032"
    },
    {
      "id": "ozon-logistics",
      "catalog": "ozon-logistics",
      "slug": "sledzenie-ozon",
      "title": "Śledzenie Ozon: numery zamówień i statusy po polsku",
      "name": "Ozon",
      "color": "#005bff"
    },
    {
      "id": "citymail",
      "catalog": "citymail",
      "slug": "sledzenie-citymail",
      "title": "Śledzenie CityMail: numery, statusy i dostawa w Szwecji",
      "name": "CityMail",
      "color": "#fc4c02"
    },
    {
      "id": "dayone",
      "catalog": "dayone",
      "slug": "sledzenie-dayone",
      "title": "Śledzenie DayOne: numery DOT, statusy i kto doręcza",
      "name": "DayOne",
      "color": "#e86d1c"
    },
    {
      "id": "dpd-be",
      "catalog": "dpd-be",
      "slug": "sledzenie-dpd-belgium",
      "title": "Śledzenie DPD Belgium: statusy, doręczenie i kontakt",
      "name": "DPD Belgium",
      "color": "#dc0032"
    },
    {
      "id": "packlink",
      "catalog": "packlink",
      "slug": "sledzenie-packlink",
      "title": "Śledzenie Packlink: numer referencyjny, statusy i kontakt",
      "name": "Packlink",
      "color": "#ff4f00"
    },
    {
      "id": "sto",
      "catalog": "sto",
      "slug": "sledzenie-sto-express",
      "title": "Śledzenie STO Express: numery 申通快递, statusy i kontakt",
      "name": "STO Express",
      "color": "#ff6700"
    },
    {
      "id": "j-and-t-cn",
      "catalog": "j-and-t-cn",
      "slug": "sledzenie-jt-express-china",
      "title": "Śledzenie J&T Express China: numery JT, statusy i kontakt",
      "name": "J&T Express China",
      "color": "#e62019"
    },
    {
      "id": "tforce-freight",
      "catalog": "tforce-freight",
      "slug": "sledzenie-tforce-freight",
      "title": "Śledzenie TForce Freight: numery PRO, statusy i szkody",
      "name": "TForce Freight",
      "color": "#0053a1"
    },
    {
      "id": "andreani",
      "catalog": "andreani",
      "slug": "sledzenie-andreani",
      "title": "Śledzenie Andreani: numery i statusy w Argentynie",
      "name": "Andreani",
      "color": "#d71920"
    },
    {
      "id": "mainfreight",
      "catalog": "mainfreight",
      "slug": "sledzenie-mainfreight",
      "title": "Śledzenie Mainfreight: numery listów i statusy",
      "name": "Mainfreight",
      "color": "#005eb8"
    },
    {
      "id": "spx-sg",
      "catalog": "spx-sg",
      "slug": "sledzenie-spx-express-singapore",
      "title": "Śledzenie SPX Express Singapur: paczki Shopee i statusy",
      "name": "SPX Express Singapore",
      "color": "#ee4d2d"
    },
    {
      "id": "dai-post",
      "catalog": "dai-post",
      "slug": "sledzenie-dai-post",
      "title": "Śledzenie DAI Post: numery, statusy i pomoc w dostawie",
      "name": "DAI Post",
      "color": "#008faf"
    },
    {
      "id": "eurodis",
      "catalog": "eurodis",
      "slug": "sledzenie-eurodis",
      "title": "Śledzenie EURODIS: numery przesyłek i lokalni partnerzy",
      "name": "EURODIS",
      "color": "#0b418b"
    },
    {
      "id": "chronodiali",
      "catalog": "chronodiali",
      "slug": "sledzenie-chronodiali",
      "title": "Śledzenie Chronodiali: numery CHPU, statusy i kontakt",
      "name": "Chronodiali",
      "color": "#009adf"
    },
    {
      "id": "17feia",
      "catalog": "17feia",
      "slug": "sledzenie-17feia",
      "title": "Śledzenie 17FEIA: numery FEI…YQ, statusy i doręczenie",
      "name": "17FEIA",
      "color": "#00a9e4"
    },
    {
      "id": "tsjy",
      "catalog": "tsjy",
      "slug": "sledzenie-tsjy",
      "title": "Śledzenie TSJY (通世集运): numery zamówień i statusy",
      "name": "TSJY",
      "color": "#4042e2"
    },
    {
      "id": "stone3pl",
      "catalog": "stone3pl",
      "slug": "sledzenie-stone3pl",
      "title": "Śledzenie Stone3PL: numery, statusy i kontakt",
      "name": "Stone3PL",
      "color": "#003d98"
    },
    {
      "id": "j-and-t-uk",
      "catalog": "j-and-t-uk",
      "slug": "sledzenie-jt-express-uk",
      "title": "Śledzenie J&T Express UK: numery JTUK i statusy",
      "name": "J&T Express UK",
      "color": "#e60000"
    },
    {
      "id": "m-one",
      "catalog": "m-one",
      "slug": "sledzenie-m-one",
      "title": "Śledzenie M-ONE (Milšped ONE): statusy, dostawa i pomoc",
      "name": "M-ONE",
      "color": "#024da0"
    },
    {
      "id": "early-bird",
      "catalog": "early-bird",
      "slug": "sledzenie-early-bird",
      "title": "Śledzenie Early Bird: statusy, poranne dostawy i automaty",
      "name": "Early Bird",
      "color": "#dd7f4a"
    },
    {
      "id": "xinghe-freight",
      "catalog": "xinghe-freight",
      "slug": "xinghe-freight-sledzenie",
      "title": "Śledzenie Xinghe Freight: numery GZXH, statusy i dostawa",
      "name": "Xinghe Freight",
      "color": "#0e3a6b"
    },
    {
      "id": "cvc",
      "catalog": "cvc",
      "slug": "sledzenie-cvc",
      "title": "Śledzenie CVC (Си Ви Си): paczka w Bułgarii",
      "name": "CVC",
      "color": "#652b9e"
    },
    {
      "id": "transpress",
      "catalog": "transpress",
      "slug": "sledzenie-transpress",
      "title": "Śledzenie Transpress (Транспрес): paczki w Bułgarii",
      "name": "Transpress",
      "color": "#1e6ee2"
    },
    {
      "id": "gls-us",
      "catalog": "gls-us",
      "slug": "sledzenie-gls-us",
      "title": "Śledzenie GLS US (GSO): numery, statusy i doręczenia",
      "name": "GLS US",
      "color": "#ffdd00"
    },
    {
      "id": "sxw-express",
      "catalog": "sxw-express",
      "slug": "sledzenie-sxw-express",
      "title": "Śledzenie SXW Express: numery SXW, statusy i dostawa",
      "name": "SXW Express",
      "color": "#003876"
    },
    {
      "id": "xuchuan-logistics",
      "catalog": "xuchuan-logistics",
      "slug": "sledzenie-xuchuan-logistics",
      "title": "Śledzenie Xuchuan Logistics: numery XC, dostawa i pomoc",
      "name": "Xuchuan International Logistics",
      "color": "#0a2463"
    },
    {
      "id": "samus",
      "catalog": "samus",
      "slug": "sledzenie-samus",
      "title": "Śledzenie SAMUS: numery, statusy i odbiór w Wilnie",
      "name": "SAMUS",
      "color": "#e50079"
    },
    {
      "id": "siuntos-autobusais",
      "catalog": "siuntos-autobusais",
      "slug": "sledzenie-siuntos-autobusais",
      "title": "Śledzenie Siuntos autobusais: paczki autobusowe na Litwie",
      "name": "Siuntos autobusais",
      "color": "#00843d"
    },
    {
      "id": "zipasgo",
      "catalog": "zipasgo",
      "slug": "sledzenie-zipasgo",
      "title": "Śledzenie ZipasGo: Parcel Stars, partnerzy i pomoc",
      "name": "ZipasGo",
      "color": "#ffdb1c"
    },
    {
      "id": "dk-logistics-ee",
      "catalog": "dk-logistics-ee",
      "slug": "sledzenie-dk-logistics",
      "title": "Śledzenie DK Logistics: list przewozowy, dostawa i kontakt",
      "name": "DK Logistics",
      "color": "#f58428"
    },
    {
      "id": "jet-express-ee",
      "catalog": "jet-express-ee",
      "slug": "sledzenie-jet-express",
      "title": "Śledzenie Jet Express: numer zamówienia, dostawa, kontakt",
      "name": "Jet Express",
      "color": "#d61b22"
    },
    {
      "id": "fan-courier-international",
      "catalog": "fan-courier-international",
      "slug": "sledzenie-fan-courier-international",
      "title": "Śledzenie FAN Courier International: AWB i statusy",
      "name": "FAN Courier International",
      "color": "#2daae1"
    },
    {
      "id": "j-and-t-br",
      "catalog": "j-and-t-br",
      "slug": "sledzenie-jt-express-brazil",
      "title": "Śledzenie J&T Express Brazil: statusy, cło i kontakt",
      "name": "J&T Express Brazil",
      "color": "#e60000"
    },
    {
      "id": "anjun",
      "catalog": "anjun",
      "slug": "sledzenie-anjun-logistics",
      "title": "Śledzenie Anjun Logistics: statusy Chiny–Brazylia i SAC",
      "name": "Anjun Logistics",
      "color": "#009944"
    },
    {
      "id": "xyl-ships",
      "catalog": "xyl-ships",
      "slug": "sledzenie-xyl-ships",
      "title": "Śledzenie XYL-Ships: numery NUDE i XLWL oraz statusy",
      "name": "XYL-Ships",
      "color": "#b13c3c"
    },
    {
      "id": "jadlog",
      "catalog": "jadlog",
      "slug": "sledzenie-jadlog",
      "title": "Śledzenie Jadlog: 14 cyfr, Pickup, Predict i statusy",
      "name": "Jadlog",
      "color": "#dc0032"
    },
    {
      "id": "gls-pl",
      "catalog": "gls-pl",
      "slug": "sledzenie-gls-polska",
      "title": "Śledzenie GLS Polska: statusy, punkty GLS, automaty i pomoc",
      "name": "GLS Poland",
      "color": "#ffdd00"
    },
    {
      "id": "j-and-t-mx",
      "catalog": "j-and-t-mx",
      "slug": "sledzenie-jt-express-mexico",
      "title": "Śledzenie J&T Express Mexico: kody JMX, telefon i pomoc",
      "name": "J&T Express Mexico",
      "color": "#e60012"
    },
    {
      "id": "bulgarian-posts",
      "catalog": "bulgarian-posts",
      "slug": "sledzenie-bulgarian-posts",
      "title": "Śledzenie Bulgarian Posts: kody PS/BG, cło i pomoc",
      "name": "Bulgarian Posts",
      "color": "#ffd100"
    },
    {
      "id": "haypost",
      "catalog": "haypost",
      "slug": "sledzenie-haypost",
      "title": "Śledzenie HayPost: kody AM, statusy i kontakty",
      "name": "HayPost",
      "color": "#fa4616"
    },
    {
      "id": "360lion",
      "catalog": "360lion",
      "slug": "sledzenie-360lion",
      "title": "Śledzenie 360Lion: kody WSH i AR, statusy i kontakt",
      "name": "360Lion",
      "color": "#cf5d15"
    },
    {
      "id": "paquetexpress",
      "catalog": "paquetexpress",
      "slug": "sledzenie-paquetexpress",
      "title": "Śledzenie Paquetexpress: guías, Ocurre i reklamacje",
      "name": "Paquetexpress",
      "color": "#002a52"
    },
    {
      "id": "ydh",
      "catalog": "ydh",
      "slug": "sledzenie-ydh",
      "title": "Śledzenie YDH: kody YDH...YQ i AZ, przekazania i pomoc",
      "name": "YDH",
      "color": "#015db9"
    },
    {
      "id": "a-tu-hora-express",
      "catalog": "a-tu-hora-express",
      "slug": "sledzenie-a-tu-hora-express",
      "title": "Śledzenie A Tu Hora Express: kody, kod pocztowy i pomoc",
      "name": "A Tu Hora Express",
      "color": "#1d2a84"
    },
    {
      "id": "dtdc",
      "catalog": "dtdc",
      "slug": "sledzenie-dtdc",
      "title": "Śledzenie DTDC: formaty AWB, statusy RTO i pomoc",
      "name": "DTDC",
      "color": "#0d237d"
    },
    {
      "id": "gofo-es",
      "catalog": "gofo-es",
      "slug": "sledzenie-gofo-spain",
      "title": "Śledzenie GOFO Spain: kody GFES i CIES, 3 próby",
      "name": "GOFO Spain",
      "color": "#fc4c02"
    },
    {
      "id": "korea-post",
      "catalog": "korea-post",
      "slug": "sledzenie-korea-post",
      "title": "Śledzenie Korea Post: kody EMS KR, 13 cyfr i pomoc",
      "name": "Korea Post",
      "color": "#da291c"
    },
    {
      "id": "nova-global",
      "catalog": "nova-global",
      "slug": "sledzenie-nova-global",
      "title": "Śledzenie Nova Global: kody NPG i NGUS, cło i pomoc",
      "name": "Nova Global",
      "color": "#da291c"
    },
    {
      "id": "xlcourier",
      "catalog": "xlcourier",
      "slug": "sledzenie-xlcourier",
      "title": "Śledzenie XLCourier: kody XLC, ciężkie paczki w UK",
      "name": "XLCourier",
      "color": "#044ad6"
    },
    {
      "id": "estafeta",
      "catalog": "estafeta",
      "slug": "sledzenie-estafeta",
      "title": "Śledzenie Estafeta: kody 10 i 22 znaki, 2 próby",
      "name": "Estafeta",
      "color": "#e30613"
    },
    {
      "id": "gls-hu",
      "catalog": "gls-hu",
      "slug": "sledzenie-gls-hungary",
      "title": "Śledzenie GLS Hungary: numery, automaty i kontakt",
      "name": "GLS Hungary",
      "color": "#ffdd00"
    },
    {
      "id": "dhl-freight",
      "catalog": "dhl-freight",
      "slug": "sledzenie-dhl-freight",
      "title": "Śledzenie DHL Freight: kody ActiveTracing, statusy i pomoc",
      "name": "DHL Freight",
      "color": "#ffcc00"
    },
    {
      "id": "anserx",
      "catalog": "anserx",
      "slug": "sledzenie-anserx",
      "title": "Śledzenie AnserX: kody BTYCN...YQ, przekazanie i pomoc",
      "name": "AnserX",
      "color": "#2478be"
    },
    {
      "id": "bjs-home-delivery",
      "catalog": "bjs-home-delivery",
      "slug": "sledzenie-bjs-home-delivery",
      "title": "Śledzenie BJS Home Delivery: kody B, okna 3 h i pomoc",
      "name": "BJS Home Delivery",
      "color": "#feba30"
    },
    {
      "id": "chukou1",
      "catalog": "chukou1",
      "slug": "sledzenie-chukou1",
      "title": "Śledzenie Chukou1: kody EC-FirstClass, przekazanie i pomoc",
      "name": "Chukou1",
      "color": "#0b4da2"
    },
    {
      "id": "cj-packet",
      "catalog": "cj-packet",
      "slug": "sledzenie-cj-packet",
      "title": "Śledzenie CJ Packet: kody CJP...YQ, przekazanie i kontakt",
      "name": "CJ Packet",
      "color": "#ff7700"
    },
    {
      "id": "cmt-express",
      "catalog": "cmt-express",
      "slug": "sledzenie-cmt-express",
      "title": "Śledzenie CMT Express: kody CMT, hub Shenzhen i kontakt",
      "name": "CMT Express",
      "color": "#ed010b"
    },
    {
      "id": "correos-de-mexico",
      "catalog": "correos-de-mexico",
      "slug": "sledzenie-correos-de-mexico",
      "title": "Śledzenie Correos de México: kody S10 MX i reklamacje",
      "name": "Correos de México",
      "color": "#ed007d"
    },
    {
      "id": "dobropost",
      "catalog": "dobropost",
      "slug": "sledzenie-dobropost",
      "title": "Śledzenie DobroPost: kody DBRPST, statusy i magazyny",
      "name": "DobroPost",
      "color": "#ffbe2e"
    },
    {
      "id": "ems",
      "catalog": "ems",
      "slug": "sledzenie-ems",
      "title": "Śledzenie EMS: kody S10, statusy i pomoc pocztowa",
      "name": "EMS",
      "color": "#003b7a"
    },
    {
      "id": "jiayang",
      "catalog": "jiayang",
      "slug": "sledzenie-jiayang",
      "title": "Śledzenie Jiayang International: kody JYGJ i pomoc",
      "name": "Jiayang International",
      "color": "#105bcc"
    },
    {
      "id": "jumppoint-international",
      "catalog": "jumppoint-international",
      "slug": "sledzenie-jumppoint-international",
      "title": "Śledzenie Jumppoint International: kody JP i pomoc",
      "name": "Jumppoint International Express",
      "color": "#0f4ae1"
    },
    {
      "id": "kuayuan-express",
      "catalog": "kuayuan-express",
      "slug": "sledzenie-kuayuan-express",
      "title": "Śledzenie Kuayuan Express: kody, przekazania i pomoc",
      "name": "Kuayuan Express",
      "color": "#084b86"
    },
    {
      "id": "liccardi",
      "catalog": "liccardi",
      "slug": "sledzenie-liccardi",
      "title": "Śledzenie Liccardi Express Courier: kody LDV i pomoc",
      "name": "Liccardi Express Courier",
      "color": "#014895"
    },
    {
      "id": "maersk-ecommerce",
      "catalog": "maersk-ecommerce",
      "slug": "sledzenie-maersk-ecommerce",
      "title": "Śledzenie Maersk eCommerce: kody 1M, statusy i pomoc",
      "name": "Maersk eCommerce",
      "color": "#42b0d5"
    },
    {
      "id": "pdu",
      "catalog": "pdu",
      "slug": "sledzenie-pdu",
      "title": "Śledzenie PDU: kody Parcel Delivery Union, statusy i pomoc",
      "name": "PDU",
      "color": "#005cfe"
    },
    {
      "id": "post-haste",
      "catalog": "post-haste",
      "slug": "sledzenie-post-haste",
      "title": "Śledzenie Post Haste: kody NZ, statusy i pomoc",
      "name": "Post Haste",
      "color": "#24b2d8"
    },
    {
      "id": "redur",
      "catalog": "redur",
      "slug": "sledzenie-redur",
      "title": "Śledzenie Redur: 14-cyfrowe kody, Hiszpania i pomoc",
      "name": "Redur",
      "color": "#0c4c49"
    },
    {
      "id": "sengi-express",
      "catalog": "sengi-express",
      "slug": "sledzenie-sengi-express",
      "title": "Śledzenie Sengi Express: kody Chiny–Włochy i pomoc",
      "name": "Sengi Express",
      "color": "#1a44a4"
    },
    {
      "id": "serpost",
      "catalog": "serpost",
      "slug": "sledzenie-serpost",
      "title": "Śledzenie Serpost: kody S10 z Peru, cło SUNAT i pomoc",
      "name": "Serpost",
      "color": "#00308a"
    },
    {
      "id": "skypostal",
      "catalog": "skypostal",
      "slug": "sledzenie-skypostal",
      "title": "Śledzenie SkyPostal: kody SKAMZUS i SP, statusy i pomoc",
      "name": "SkyPostal",
      "color": "#088aea"
    },
    {
      "id": "thailand-post",
      "catalog": "thailand-post",
      "slug": "sledzenie-thailand-post",
      "title": "Śledzenie Thailand Post: kody TH S10, statusy EMS i 1545",
      "name": "Thailand Post",
      "color": "#ef172f"
    },
    {
      "id": "yunda",
      "catalog": "yunda",
      "slug": "sledzenie-yunda",
      "title": "Śledzenie Yunda Express: chińskie numery, statusy i 95546",
      "name": "Yunda Express",
      "color": "#ffce00"
    },
    {
      "id": "onlog-express",
      "catalog": "onlog-express",
      "slug": "sledzenie-onlog-express",
      "title": "Śledzenie Onlog Express: kody AA, statusy Brazylia i pomoc",
      "name": "Onlog Express",
      "color": "#1a345d"
    },
    {
      "id": "a-duie-pyle",
      "catalog": "a-duie-pyle",
      "slug": "sledzenie-a-duie-pyle",
      "title": "Śledzenie A. Duie Pyle: numery PRO, LTL i pomoc",
      "name": "A. Duie Pyle",
      "color": "#f27629"
    },
    {
      "id": "buffalo-ex",
      "catalog": "buffalo-ex",
      "slug": "sledzenie-buffalo-ex",
      "title": "Śledzenie BUFFALO: kody BUFZA, cło w RPA i kontakt",
      "name": "BUFFALO",
      "color": "#bc8c4d"
    },
    {
      "id": "chit-chats",
      "catalog": "chit-chats",
      "slug": "sledzenie-chit-chats",
      "title": "Śledzenie Chit Chats: 10-znakowe ID i przekazanie USPS",
      "name": "Chit Chats",
      "color": "#db2b35"
    },
    {
      "id": "dpd-hu",
      "catalog": "dpd-hu",
      "slug": "sledzenie-dpd-hu",
      "title": "Śledzenie DPD Hungary: kody 14-cyfrowe, FOXPOST i pomoc",
      "name": "DPD Hungary",
      "color": "#dc0032"
    },
    {
      "id": "e24metro-ca",
      "catalog": "e24metro-ca",
      "slug": "sledzenie-e24metro-ca",
      "title": "Śledzenie E24 (Metro Supply Chain): Kanada i kontakt",
      "name": "E24",
      "color": "#545859"
    },
    {
      "id": "go-express",
      "catalog": "go-express",
      "slug": "sledzenie-go-express",
      "title": "Śledzenie GO! Express: 12-cyfrowe listy przewozowe i pomoc",
      "name": "GO! Express",
      "color": "#b9242b"
    },
    {
      "id": "kuehne-nagel",
      "catalog": "kuehne-nagel",
      "slug": "sledzenie-kuehne-nagel",
      "title": "Śledzenie Kuehne+Nagel: numery, statusy i doręczenia",
      "name": "Kuehne+Nagel",
      "color": "#003369"
    },
    {
      "id": "memphis-courier",
      "catalog": "memphis-courier",
      "slug": "sledzenie-memphis-courier",
      "title": "Śledzenie Memphis Courier: kody M-MIA, cło ANVISA i pomoc",
      "name": "Memphis Courier",
      "color": "#1f3c5b"
    },
    {
      "id": "oca",
      "catalog": "oca",
      "slug": "sledzenie-oca",
      "title": "Śledzenie OCA: 19-cyfrowe numery, statusy, oddziały i pomoc",
      "name": "OCA",
      "color": "#592c82"
    },
    {
      "id": "pigeonship",
      "catalog": "pigeonship",
      "slug": "sledzenie-pigeonship",
      "title": "Śledzenie PigeonShip: 18-znakowe kody, statusy i pomoc",
      "name": "PigeonShip",
      "color": "#f2b02c"
    },
    {
      "id": "seko",
      "catalog": "seko",
      "slug": "sledzenie-seko-logistics",
      "title": "Śledzenie SEKO Logistics: numery, OmniParcel i doręczenie",
      "name": "SEKO Logistics",
      "color": "#ef3e42"
    },
    {
      "id": "bfe-express",
      "catalog": "bfe-express",
      "slug": "sledzenie-bfe-express",
      "title": "Śledzenie BFE Express: numery CK i WYB, statusy i pomoc",
      "name": "BFE Express",
      "color": "#122a88"
    },
    {
      "id": "pasarex",
      "catalog": "pasarex",
      "slug": "sledzenie-pasarex",
      "title": "Śledzenie PasarEX: kody Amazon AMZPSR, cło i pomoc",
      "name": "PasarEX",
      "color": "#124381"
    },
    {
      "id": "spee-dee",
      "catalog": "spee-dee",
      "slug": "sledzenie-spee-dee",
      "title": "Śledzenie Spee-Dee Delivery: kody SP, stany i pomoc",
      "name": "Spee-Dee",
      "color": "#ff4734"
    },
    {
      "id": "tnt-au",
      "catalog": "tnt-au",
      "slug": "sledzenie-tnt-australia",
      "title": "Śledzenie TNT Australia: numery, statusy i pomoc",
      "name": "TNT Australia",
      "color": "#ff6600"
    },
    {
      "id": "wedo",
      "catalog": "wedo",
      "slug": "sledzenie-one-by-allegro-cz",
      "title": "Śledzenie One by Allegro CZ (WE|DO): kody i odbiór",
      "name": "One by Allegro CZ",
      "color": "#006d57"
    },
    {
      "id": "5post",
      "catalog": "5post",
      "slug": "sledzenie-5post",
      "title": "Śledzenie 5Post: numery, odbiór Pyaterochka i statusy",
      "name": "5Post",
      "color": "#64b441"
    },
    {
      "id": "shipter",
      "catalog": "shipter",
      "slug": "sledzenie-shipter",
      "title": "Śledzenie Shipter: koreańskie numery H3 i statusy",
      "name": "Shipter",
      "color": "#005eff"
    }
  ],
  "ru": [
    {
      "id": "econt",
      "catalog": "econt",
      "slug": "econt-otslezhivanie",
      "title": "Эконт (Econt): отслеживание посылки, статусы и офисы",
      "name": "Econt",
      "color": "#234182"
    },
    {
      "id": "posta-srbije",
      "catalog": "posta-srbije",
      "slug": "pochta-serbii-otslezhivanie",
      "title": "Почта Сербии (Pošta Srbije): отслеживание посылок",
      "name": "Pošta Srbije",
      "color": "#fbba00"
    },
    {
      "id": "lietuvos-pastas",
      "catalog": "lietuvos-pastas",
      "slug": "pochta-litvy-otslezhivanie",
      "title": "Почта Литвы (Lietuvos paštas): отслеживание посылок",
      "name": "Lietuvos paštas",
      "color": "#fedd00"
    },
    {
      "id": "latvijas-pasts",
      "catalog": "latvijas-pasts",
      "slug": "latvijskaya-pochta-otslezhivanie",
      "title": "Латвийская почта (Latvijas Pasts): отслеживание посылки",
      "name": "Latvijas Pasts",
      "color": "#0c72ba"
    },
    {
      "id": "helthjem",
      "catalog": "helthjem",
      "slug": "helthjem-otslezhivanie",
      "title": "Отслеживание Helthjem: трек-номер, статусы и пункты выдачи",
      "name": "Helthjem",
      "color": "#ffb202"
    },
    {
      "id": "ukrposhta",
      "catalog": "ukrposhta",
      "slug": "ukrposhta-otslezhivanie-posylki",
      "title": "Укрпошта (Укрпошта): отслеживание по трек-номеру и статусы",
      "name": "Ukrposhta",
      "color": "#ffbe0c"
    },
    {
      "id": "dhl",
      "catalog": "dhl",
      "slug": "dhl-paket-otslezhivanie",
      "title": "Отслеживание посылки DHL Paket: статусы и Packstation",
      "name": "DHL Paket",
      "color": "#ffcc00"
    },
    {
      "id": "j-and-t-de",
      "catalog": "j-and-t-de",
      "slug": "jt-express-germaniya-otslezhivanie",
      "title": "Отслеживание J&T Express в Германии: трек-номер и статусы",
      "name": "J&T Express Germany",
      "color": "#e60000"
    },
    {
      "id": "china-post",
      "catalog": "china-post",
      "slug": "pochta-kitaya-otslezhivanie",
      "title": "Почта Китая (China Post): отслеживание, статусы, доставка",
      "name": "China Post",
      "color": "#006d46"
    },
    {
      "id": "yto",
      "catalog": "yto",
      "slug": "yto-express-otslezhivanie",
      "title": "Отслеживание YTO Express: трек-номер, статусы и телефон",
      "name": "YTO Express",
      "color": "#4d148c"
    },
    {
      "id": "topyou",
      "catalog": "topyou",
      "slug": "topyou-otslezhivanie-posylki",
      "title": "Отслеживание посылки TopYou: трек-номер TYZR и статусы",
      "name": "TopYou",
      "color": "#045cd7"
    },
    {
      "id": "gzyl",
      "catalog": "gzyl",
      "slug": "gzyl-otslezhivanie",
      "title": "Отслеживание посылки GZYL: трек-номер YL…YQ и статусы",
      "name": "GZYL",
      "color": "#4e79e1"
    },
    {
      "id": "russian-post",
      "catalog": "russian-post",
      "slug": "pochta-rossii-otslezhivanie",
      "title": "Отслеживание посылок Почты России: трек-номер и статусы",
      "name": "Russian Post",
      "color": "#1928dc"
    },
    {
      "id": "cdek",
      "catalog": "cdek",
      "slug": "cdek-otslezhivanie",
      "title": "Отслеживание СДЭК (CDEK): номер заказа, статусы и ПВЗ",
      "name": "CDEK",
      "color": "#00b33c"
    },
    {
      "id": "ozon-logistics",
      "catalog": "ozon-logistics",
      "slug": "ozon-otslezhivanie",
      "title": "Отслеживание Ozon: трек-номер заказа и статусы доставки",
      "name": "Ozon",
      "color": "#005bff"
    },
    {
      "id": "dobropost",
      "catalog": "dobropost",
      "slug": "dobropost-otslezhivanie",
      "title": "Отслеживание DobroPost: трек DBRPST, статусы и ПВЗ",
      "name": "DobroPost",
      "color": "#ffbe2e"
    },
    {
      "id": "5post",
      "catalog": "5post",
      "slug": "otslezhivanie-5post",
      "title": "Отслеживание 5Post — статусы, постаматы и контакты",
      "name": "5Post",
      "color": "#64b441"
    }
  ],
  "zh": [
    {
      "id": "colis-prive",
      "catalog": "colis-prive",
      "slug": "colis-prive-kuaidi-chaxun",
      "title": "法国 Colis Privé 快递查询：12 位单号、邮编与状态",
      "name": "Colis Privé",
      "color": "#aa78ff"
    },
    {
      "id": "brt",
      "catalog": "brt",
      "slug": "brt-tracking",
      "title": "BRT 意大利快递查询：单号、状态与未妥投处理",
      "name": "BRT",
      "color": "#dc0032"
    },
    {
      "id": "correos-express",
      "catalog": "correos-express",
      "slug": "correos-express-kuaidi-chaxun",
      "title": "Correos Express 快递查询：单号、西班牙语状态与客服",
      "name": "Correos Express",
      "color": "#ffcd00"
    },
    {
      "id": "nacex",
      "catalog": "nacex",
      "slug": "nacex-kuaidi-chaxun",
      "title": "NACEX 快递查询：单号格式、西班牙语状态与客服",
      "name": "NACEX",
      "color": "#fe5000"
    },
    {
      "id": "yunexpress",
      "catalog": "yunexpress",
      "slug": "yuntu-yunexpress-wuliu-chaxun",
      "title": "云途物流（YunExpress）查询：YT单号、状态与尾程",
      "name": "YunExpress",
      "color": "#008284"
    },
    {
      "id": "cainiao",
      "catalog": "aliexpress",
      "slug": "cainiao-wuliu-chaxun",
      "title": "菜鸟（Cainiao）国际物流查询：单号、状态与客服",
      "name": "Cainiao",
      "color": "#e62e04"
    },
    {
      "id": "mondial-relay",
      "catalog": "mondial-relay",
      "slug": "mondial-relay-tracking",
      "title": "Mondial Relay 物流查询：单号、法语状态与取件点",
      "name": "Mondial Relay",
      "color": "#ef3026"
    },
    {
      "id": "gls-de",
      "catalog": "gls-de",
      "slug": "gls-tracking-germany",
      "title": "GLS 德国物流查询：状态、ParcelShop 与客服",
      "name": "GLS Germany",
      "color": "#ffdd00"
    },
    {
      "id": "dpd-de",
      "catalog": "dpd-de",
      "slug": "dpd-tracking-germany",
      "title": "DPD 德国物流查询：14 位单号、状态与自提点",
      "name": "DPD Germany",
      "color": "#dc0032"
    },
    {
      "id": "hermes-de",
      "catalog": "hermes-de",
      "slug": "hermes-de-tracking",
      "title": "Hermes 德国快递查询：单号、德语状态说明与网点自提",
      "name": "Hermes",
      "color": "#0091cd"
    },
    {
      "id": "dpd-ch",
      "catalog": "dpd",
      "slug": "dpd-ruishi-kuaidi-chaxun",
      "title": "DPD 瑞士（DPD Switzerland）查询：14 位单号与状态",
      "name": "DPD Switzerland",
      "color": "#dc0032"
    },
    {
      "id": "inpost",
      "catalog": "inpost",
      "slug": "inpost-tracking",
      "title": "InPost 物流查询：单号格式、状态说明与自提柜取件",
      "name": "InPost",
      "color": "#ffcc05"
    },
    {
      "id": "paack",
      "catalog": "paack",
      "slug": "paack-tracking",
      "title": "Paack 快递查询：订单号、邮编与 PaackGo 自提点",
      "name": "Paack",
      "color": "#5b2dee"
    },
    {
      "id": "evri",
      "catalog": "evri-uk",
      "slug": "evri-yingguo-kuaidi-chaxun",
      "title": "Evri（原 Hermes UK）查询：单号、状态与 ParcelShop",
      "name": "Evri",
      "color": "#00014d"
    },
    {
      "id": "chronopost",
      "catalog": "chronopost",
      "slug": "chronopost-tracking",
      "title": "Chronopost 物流查询：单号格式、法语状态与取件点",
      "name": "Chronopost",
      "color": "#0056a7"
    },
    {
      "id": "poste-italiane",
      "catalog": "poste-italiane",
      "slug": "poste-italiane-tracking",
      "title": "Poste Italiane 物流查询：单号、状态与取件指南",
      "name": "Poste Italiane",
      "color": "#eedc00"
    },
    {
      "id": "seur",
      "catalog": "seur",
      "slug": "seur-tracking",
      "title": "SEUR 西班牙快递查询：单号、西语状态与 Pickup 自提点",
      "name": "SEUR",
      "color": "#0063b3"
    },
    {
      "id": "mrw",
      "catalog": "mrw",
      "slug": "mrw-kuaidi-chaxun",
      "title": "西班牙 MRW 快递查询：12 位单号、状态与 MRW Point",
      "name": "MRW",
      "color": "#19133b"
    },
    {
      "id": "ctt",
      "catalog": "ctt",
      "slug": "ctt-putaoya-youzheng-chaxun",
      "title": "葡萄牙邮政 CTT 快递查询：单号、状态、自提与关税",
      "name": "CTT",
      "color": "#df0024"
    },
    {
      "id": "packeta",
      "catalog": "packeta",
      "slug": "packeta-zasilkovna-kuaidi-chaxun",
      "title": "Packeta（Zásilkovna）快递查询：Z 单号、状态与自提",
      "name": "Packeta",
      "color": "#ba1b02"
    },
    {
      "id": "dpd-pl",
      "catalog": "dpd-pl",
      "slug": "dpd-poland-tracking",
      "title": "DPD Poland 波兰快递查询：14 位单号、状态与自提柜",
      "name": "DPD Poland",
      "color": "#dc0032"
    },
    {
      "id": "4px",
      "catalog": "four-px",
      "slug": "disifang-4px-wuliu-chaxun",
      "title": "递四方（4PX）国际物流查询：单号格式、状态与客服",
      "name": "4PX",
      "color": "#005add"
    },
    {
      "id": "swiss-post",
      "catalog": "swiss-post",
      "slug": "swiss-post-tracking",
      "title": "Swiss Post 瑞士邮政查询：单号、状态说明与取件通知",
      "name": "Swiss Post",
      "color": "#ffcc00"
    },
    {
      "id": "ctt-express",
      "catalog": "ctt-express",
      "slug": "ctt-express-tracking",
      "title": "CTT Express 西班牙物流查询：单号、状态与客服",
      "name": "CTT Express",
      "color": "#df0024"
    },
    {
      "id": "dpd-fr",
      "catalog": "dpd-fr",
      "slug": "dpd-france-kuaidi-chaxun",
      "title": "DPD France 快递查询：单号、法语状态与 Pickup 自提点",
      "name": "DPD France",
      "color": "#dc0032"
    },
    {
      "id": "gls-fr",
      "catalog": "gls-fr",
      "slug": "gls-faguo-kuaidi-chaxun",
      "title": "GLS 法国（GLS France）快递查询：单号、状态与自提",
      "name": "GLS France",
      "color": "#ffdd00"
    },
    {
      "id": "gls-it",
      "catalog": "gls-it",
      "slug": "gls-yidali-kuaidi-chaxun",
      "title": "GLS 意大利快递查询：单号、状态、GLS Point 与客服",
      "name": "GLS Italy",
      "color": "#ffdd00"
    },
    {
      "id": "poczta-polska",
      "catalog": "poczta-polska",
      "slug": "poczta-polska-tracking",
      "title": "Poczta Polska 波兰邮政查询：单号、状态与取件",
      "name": "Poczta Polska",
      "color": "#c43c2b"
    },
    {
      "id": "orlen-paczka",
      "catalog": "orlen-paczka",
      "slug": "orlen-paczka-kuaidi-chaxun",
      "title": "波兰 ORLEN Paczka 快递查询：13 位单号、状态与自提",
      "name": "ORLEN Paczka",
      "color": "#db3830"
    },
    {
      "id": "austrian-post",
      "catalog": "austrian-post",
      "slug": "austrian-post-kuaidi-chaxun",
      "title": "奥地利邮政（Austrian Post）查询：单号、状态与取件",
      "name": "Austrian Post",
      "color": "#ffdc00"
    },
    {
      "id": "tipsa",
      "catalog": "tipsa",
      "slug": "tipsa-kuaidi-chaxun",
      "title": "TIPSA 快递查询：22 位单号、西班牙语状态与派送失败",
      "name": "TIPSA",
      "color": "#001e62"
    },
    {
      "id": "yanwen",
      "catalog": "yanwen",
      "slug": "yanwen-wuliu-chaxun",
      "title": "燕文物流（Yanwen）单号查询：UL…YP格式、状态与尾程",
      "name": "Yanwen",
      "color": "#8cbe1e"
    },
    {
      "id": "vinted-go",
      "catalog": "vinted-go",
      "slug": "vinted-go-tracking",
      "title": "Vinted Go 物流查询：单号、状态与自提柜取件",
      "name": "Vinted Go",
      "color": "#004654"
    },
    {
      "id": "dpd-uk",
      "catalog": "dpd-uk",
      "slug": "dpd-uk-tracking",
      "title": "DPD UK 英国快递查询：14 位单号、状态与自提点",
      "name": "DPD UK",
      "color": "#dc0032"
    },
    {
      "id": "parcelforce",
      "catalog": "parcelforce",
      "slug": "parcelforce-worldwide-tracking",
      "title": "Parcelforce Worldwide 物流查询：单号、状态、清关与客服",
      "name": "Parcelforce Worldwide",
      "color": "#9d0f11"
    },
    {
      "id": "bpost",
      "catalog": "bpost",
      "slug": "bpost-bilishi-youzheng-chaxun",
      "title": "比利时邮政 bpost 快递查询：单号、状态、自提与关税",
      "name": "bpost",
      "color": "#ef2637"
    },
    {
      "id": "gls-es",
      "catalog": "gls-es",
      "slug": "gls-xibanya-kuaidi-chaxun",
      "title": "GLS 西班牙（GLS Spain）查询：单号、邮编与自提点",
      "name": "GLS Spain",
      "color": "#ffdd00"
    },
    {
      "id": "dhl-ecommerce-pl",
      "catalog": "dhl-ecommerce-pl",
      "slug": "dhl-ecommerce-bolan-kuaidi-chaxun",
      "title": "波兰 DHL eCommerce 快递查询：11 位单号、状态与自提",
      "name": "DHL eCommerce Poland",
      "color": "#ffcc00"
    },
    {
      "id": "dhl-ecommerce-uk",
      "catalog": "dhl-ecommerce-uk",
      "slug": "dhl-ecommerce-uk-tracking",
      "title": "DHL eCommerce UK 物流查询：14 位单号、状态、ServicePoint 与客服",
      "name": "DHL eCommerce UK",
      "color": "#ffcc00"
    },
    {
      "id": "gofo-fr",
      "catalog": "gofo-fr",
      "slug": "gofo-france-tracking",
      "title": "GOFO France 物流查询：GFFR 单号、状态与客服",
      "name": "GOFO France",
      "color": "#fc4c02"
    },
    {
      "id": "gofo-it",
      "catalog": "gofo-it",
      "slug": "gofo-it-tracking",
      "title": "GOFO Italy 意大利快递查询：GFIT 单号、状态与投诉指南",
      "name": "GOFO Italy",
      "color": "#fc4c02"
    },
    {
      "id": "speedpak",
      "catalog": "speedpak",
      "slug": "ebay-speedpak-wuliu-chaxun",
      "title": "eBay SpeedPAK 物流查询：28位单号、状态与尾程",
      "name": "SpeedPAK",
      "color": "#ee7532"
    },
    {
      "id": "asendia",
      "catalog": "asendia",
      "slug": "asendia-tracking",
      "title": "Asendia 物流查询：单号格式、状态含义、本地派送与客服",
      "name": "Asendia",
      "color": "#005d69"
    },
    {
      "id": "ciblex",
      "catalog": "ciblex",
      "slug": "ciblex-tracking",
      "title": "Ciblex Express 物流查询：运单号、法语状态与联系方式",
      "name": "Ciblex",
      "color": "#0160ab"
    },
    {
      "id": "landmark-global",
      "catalog": "landmark-global",
      "slug": "landmark-global-wuliu-chaxun",
      "title": "Landmark Global 物流查询：LTN 单号、状态与派送商",
      "name": "Landmark Global",
      "color": "#e62337"
    },
    {
      "id": "dhl-ecommerce",
      "catalog": "dhl-ecommerce",
      "slug": "dhl-ecommerce-kuaidi-chaxun",
      "title": "DHL eCommerce 快递查询：GM 单号、状态与客服",
      "name": "DHL eCommerce",
      "color": "#ffcc00"
    },
    {
      "id": "allegro-one",
      "catalog": "allegro-one",
      "slug": "allegro-one-tracking",
      "title": "Allegro One 物流查询：One Box 自提柜与状态",
      "name": "Allegro One",
      "color": "#006d57"
    },
    {
      "id": "sunyou",
      "catalog": "sunyou",
      "slug": "shunyou-wuliu-sunyou-chaxun",
      "title": "顺友物流（SunYou / SYPost）查询：SY单号、状态与派送",
      "name": "SunYou",
      "color": "#02a232"
    },
    {
      "id": "amazon-shipping",
      "catalog": "amazon-shipping",
      "slug": "amazon-shipping-tracking",
      "title": "Amazon Shipping 快递查询：单号、问题与求助",
      "name": "Amazon Shipping",
      "color": "#ff9900"
    },
    {
      "id": "bring",
      "catalog": "bring-posten",
      "slug": "bring-wuliu-chaxun",
      "title": "Bring（Posten）物流查询：单号、状态与取件期限",
      "name": "Bring",
      "color": "#56b529"
    },
    {
      "id": "posti",
      "catalog": "posti",
      "slug": "posti-fenlan-kuaidi-chaxun",
      "title": "芬兰邮政（Posti）查询：JJFI 单号、状态与自提柜",
      "name": "Posti",
      "color": "#ff8000"
    },
    {
      "id": "omniva",
      "catalog": "omniva",
      "slug": "omniva-tracking",
      "title": "Omniva 物流查询：爱沙尼亚、拉脱维亚与立陶宛单号、状态与快递柜",
      "name": "Omniva",
      "color": "#f86300"
    },
    {
      "id": "ppl",
      "catalog": "ppl",
      "slug": "ppl-wuliu-chaxun",
      "title": "PPL（PPL CZ）物流查询：11 位单号、状态与 Parcelshop",
      "name": "PPL",
      "color": "#004b93"
    },
    {
      "id": "box-now",
      "catalog": "box-now",
      "slug": "box-now-wuliu-chaxun",
      "title": "BOX NOW 物流查询：快递柜、取件 PIN 码与状态",
      "name": "BOX NOW",
      "color": "#44d62c"
    },
    {
      "id": "dhl-ecommerce-nl",
      "catalog": "dhl-ecommerce-nl",
      "slug": "dhl-ecommerce-helan-kuaidi-chaxun",
      "title": "DHL eCommerce 荷兰快递查询：JVGL 单号、状态与自提点",
      "name": "DHL eCommerce Netherlands",
      "color": "#ffcc00"
    },
    {
      "id": "ecoscooting",
      "catalog": "ecoscooting",
      "slug": "ecoscooting-tracking",
      "title": "Ecoscooting 物流查询：单号格式、状态与取件点",
      "name": "Ecoscooting",
      "color": "#3fa7ff"
    },
    {
      "id": "meest",
      "catalog": "meest",
      "slug": "meest-wuliu-chaxun",
      "title": "Meest 物流查询：运单号、状态含义与联系方式",
      "name": "Meest",
      "color": "#0061af"
    },
    {
      "id": "nova-poshta",
      "catalog": "nova-poshta",
      "slug": "nova-poshta-kuaidi-chaxun",
      "title": "乌克兰 Nova Poshta（Nova Post）快递查询：EW 单号",
      "name": "Nova Poshta",
      "color": "#e30613"
    },
    {
      "id": "speedy",
      "catalog": "speedy",
      "slug": "speedy-tracking",
      "title": "保加利亚 Speedy 物流查询：11 位运单号、状态与快递柜",
      "name": "Speedy",
      "color": "#dc0032"
    },
    {
      "id": "fan-courier",
      "catalog": "fan-courier",
      "slug": "fan-courier-tracking",
      "title": "FAN Courier 快递查询：AWB 运单号、状态与 FANbox 自提柜",
      "name": "FAN Courier",
      "color": "#0f2345"
    },
    {
      "id": "econt",
      "catalog": "econt",
      "slug": "econt-wuliu-chaxun",
      "title": "Econt 物流查询：运单号、状态与保加利亚网点",
      "name": "Econt",
      "color": "#234182"
    },
    {
      "id": "foxpost",
      "catalog": "foxpost",
      "slug": "foxpost-tracking",
      "title": "匈牙利 Foxpost 物流查询：单号、状态、快递柜与客服",
      "name": "Foxpost",
      "color": "#b70100"
    },
    {
      "id": "slovenska-posta",
      "catalog": "slovenska-posta",
      "slug": "slovenska-posta-kuaidi-chaxun",
      "title": "斯洛伐克邮政（Slovenská pošta）查询：单号、状态与取件",
      "name": "Slovenská pošta",
      "color": "#f9bb00"
    },
    {
      "id": "posta-slovenije",
      "catalog": "posta-slovenije",
      "slug": "posta-slovenije-siluowenniya-youzheng-chaxun",
      "title": "斯洛文尼亚邮政 Pošta Slovenije 查询：单号与自提",
      "name": "Pošta Slovenije",
      "color": "#ffc600"
    },
    {
      "id": "hrvatska-posta",
      "catalog": "hrvatska-posta",
      "slug": "hrvatska-posta-wuliu-chaxun",
      "title": "克罗地亚邮政（Hrvatska pošta）物流查询：状态与快递柜",
      "name": "Hrvatska pošta",
      "color": "#ffd100"
    },
    {
      "id": "posta-srbije",
      "catalog": "posta-srbije",
      "slug": "posta-srbije-serbian-post-tracking",
      "title": "塞尔维亚邮政（Pošta Srbije）物流查询：单号、状态与客服",
      "name": "Pošta Srbije",
      "color": "#fbba00"
    },
    {
      "id": "lietuvos-pastas",
      "catalog": "lietuvos-pastas",
      "slug": "lietuvos-pastas-litaowan-youzheng-chaxun",
      "title": "立陶宛邮政 Lietuvos paštas 查询：单号、状态与快递柜",
      "name": "Lietuvos paštas",
      "color": "#fedd00"
    },
    {
      "id": "latvijas-pasts",
      "catalog": "latvijas-pasts",
      "slug": "latvijas-pasts-kuaidi-chaxun",
      "title": "拉脱维亚邮政（Latvijas Pasts）查询：单号、状态与自提柜",
      "name": "Latvijas Pasts",
      "color": "#0c72ba"
    },
    {
      "id": "dao",
      "catalog": "dao",
      "slug": "dao-tracking",
      "title": "丹麦 DAO 物流查询：状态含义、daoSHOP 取件与派送未成功",
      "name": "DAO",
      "color": "#e30613"
    },
    {
      "id": "helthjem",
      "catalog": "helthjem",
      "slug": "helthjem-wuliu-chaxun",
      "title": "Helthjem 物流查询：挪威夜间派送、状态与取件点",
      "name": "Helthjem",
      "color": "#ffb202"
    },
    {
      "id": "ukrposhta",
      "catalog": "ukrposhta",
      "slug": "ukrposhta-tracking",
      "title": "乌克兰邮政（Ukrposhta）物流查询：单号格式与状态含义",
      "name": "Ukrposhta",
      "color": "#ffbe0c"
    },
    {
      "id": "acs-courier",
      "catalog": "acs-courier",
      "slug": "acs-courier-tracking",
      "title": "ACS Courier 物流查询：希腊与塞浦路斯单号、状态与客服",
      "name": "ACS Courier",
      "color": "#e42229"
    },
    {
      "id": "geniki-taxydromiki",
      "catalog": "geniki-taxydromiki",
      "slug": "geniki-taxydromiki-wuliu-chaxun",
      "title": "Geniki Taxydromiki 物流查询：运单号、状态与联系",
      "name": "Geniki Taxydromiki",
      "color": "#004fa0"
    },
    {
      "id": "xdp",
      "catalog": "xdp",
      "slug": "xdp-tracking",
      "title": "XDP 物流查询：英国大件快递托运单号、状态与派送说明",
      "name": "XDP",
      "color": "#53b6ed"
    },
    {
      "id": "ups",
      "catalog": "ups",
      "slug": "ups-guoji-kuaidi-chaxun",
      "title": "UPS 国际快递单号查询：1Z 运单、Exception 状态与清关",
      "name": "UPS",
      "color": "#351c15"
    },
    {
      "id": "fedex",
      "catalog": "fedex",
      "slug": "fedex-wuliu-chaxun",
      "title": "FedEx 联邦快递物流查询：单号格式、状态含义与派送失败",
      "name": "FedEx",
      "color": "#4d148c"
    },
    {
      "id": "dhl-express",
      "catalog": "dhl-express",
      "slug": "dhl-express-tracking",
      "title": "DHL Express 物流查询：10 位运单号与状态说明",
      "name": "DHL Express",
      "color": "#ffcc00"
    },
    {
      "id": "dhl",
      "catalog": "dhl",
      "slug": "dhl-paket-deguo-chaxun",
      "title": "DHL Paket 德国包裹查询：单号格式、Packstation 自提与客服",
      "name": "DHL Paket",
      "color": "#ffcc00"
    },
    {
      "id": "la-poste",
      "catalog": "la-poste",
      "slug": "la-poste-colissimo-tracking",
      "title": "La Poste / Colissimo 物流查询：单号格式与状态说明",
      "name": "La Poste / Colissimo",
      "color": "#ffcf00"
    },
    {
      "id": "correos-spain",
      "catalog": "correos-spain",
      "slug": "correos-spain-tracking",
      "title": "Correos 西班牙邮政查询：单号、状态、Citypaq 与清关",
      "name": "Correos",
      "color": "#ffcd00"
    },
    {
      "id": "royal-mail",
      "catalog": "royal-mail",
      "slug": "royal-mail-tracking",
      "title": "Royal Mail 物流查询：单号格式、状态与重新派送",
      "name": "Royal Mail",
      "color": "#da202a"
    },
    {
      "id": "usps",
      "catalog": "usps",
      "slug": "usps-meiguo-youzheng-chaxun",
      "title": "USPS 美国邮政单号查询：运单格式、英文状态与客服电话",
      "name": "USPS",
      "color": "#004b87"
    },
    {
      "id": "canada-post",
      "catalog": "canada-post",
      "slug": "canada-post-kuaidi-chaxun",
      "title": "加拿大邮政（Canada Post）查询：单号、状态与通知单",
      "name": "Canada Post",
      "color": "#e4002b"
    },
    {
      "id": "uniuni",
      "catalog": "uniuni",
      "slug": "uniuni-tracking",
      "title": "UniUni 快递查询：北美单号格式、物流状态与客服电话",
      "name": "UniUni",
      "color": "#f68841"
    },
    {
      "id": "ontrac",
      "catalog": "ontrac",
      "slug": "ontrac-kuaidi-chaxun",
      "title": "OnTrac 快递查询：单号、物流状态与派送未成功",
      "name": "OnTrac",
      "color": "#cc2027"
    },
    {
      "id": "gofo",
      "catalog": "gofo",
      "slug": "gofo-express-meiguo-chaxun",
      "title": "GOFO Express 美国快递查询：GFUS 单号、签收照片与客服",
      "name": "GOFO Express",
      "color": "#fc4c02"
    },
    {
      "id": "speedx",
      "catalog": "speedx",
      "slug": "speedx-tracking",
      "title": "SpeedX 物流查询：单号格式、状态说明与客服联系",
      "name": "SpeedX",
      "color": "#1aa3ff"
    },
    {
      "id": "australia-post",
      "catalog": "australia-post",
      "slug": "aozhou-youzheng-wuliu-chaxun",
      "title": "澳洲邮政（Australia Post）物流查询：单号、状态与快递柜",
      "name": "Australia Post",
      "color": "#dc1928"
    },
    {
      "id": "aramex-nz",
      "catalog": "aramex-nz",
      "slug": "aramex-new-zealand-tracking-number",
      "title": "Aramex 新西兰（Aramex NZ）物流查询：单号、状态与网点电话",
      "name": "Aramex New Zealand",
      "color": "#dc291e"
    },
    {
      "id": "magyar-posta",
      "catalog": "magyar-posta",
      "slug": "magyar-posta-wuliu-chaxun",
      "title": "匈牙利邮政（Magyar Posta）物流查询：状态与快递柜",
      "name": "Magyar Posta",
      "color": "#346631"
    },
    {
      "id": "first-line",
      "catalog": "first-line",
      "slug": "yihao-zhuanxian-first-line-chaxun",
      "title": "壹号专线（FIRST LINE）物流查询：1ST单号、状态与美国派送",
      "name": "FIRST LINE",
      "color": "#55207a"
    },
    {
      "id": "j-and-t-de",
      "catalog": "j-and-t-de",
      "slug": "jt-express-deguo-chaxun",
      "title": "极兔德国（J&T Express Germany）单号查询、邻居代收与客服",
      "name": "J&T Express Germany",
      "color": "#e60000"
    },
    {
      "id": "juxi-express",
      "catalog": "juxi-express",
      "slug": "juxi-wuliu-juxi-express-chaxun",
      "title": "驹隙物流（Juxi Express）查询：JXC单号、尾程与售后",
      "name": "Juxi Express",
      "color": "#e2393c"
    },
    {
      "id": "jcex",
      "catalog": "jcex",
      "slug": "jcex-wuliu-chaxun",
      "title": "JCEX（佳成国际）物流查询：JCZ 单号、状态与派送",
      "name": "JCEX",
      "color": "#0080cc"
    },
    {
      "id": "lingdong",
      "catalog": "lingdong",
      "slug": "lingdong-guoji-wuliu-chaxun",
      "title": "领东国际（Lingdong）物流查询：SZLD单号与状态",
      "name": "Lingdong International",
      "color": "#0171bb"
    },
    {
      "id": "j-and-t-au",
      "catalog": "j-and-t-au",
      "slug": "jt-express-australia-chaxun",
      "title": "极兔澳大利亚（J&T Express Australia）单号查询与客服",
      "name": "J&T Express Australia",
      "color": "#e60000"
    },
    {
      "id": "china-post",
      "catalog": "china-post",
      "slug": "zhongguo-youzheng-wuliu-chaxun",
      "title": "中国邮政（China Post）国际物流查询：单号、状态与尾程",
      "name": "China Post",
      "color": "#006d46"
    },
    {
      "id": "fedex-pl",
      "catalog": "fedex-pl",
      "slug": "fedex-poland-tracking",
      "title": "FedEx 波兰（FedEx Poland）物流查询：13 位单号与波兰语状态",
      "name": "FedEx Poland",
      "color": "#4d148c"
    },
    {
      "id": "techlink",
      "catalog": "techlink",
      "slug": "zhihe-techlink-wuliu-chaxun",
      "title": "智禾国际（Techlink）物流查询：ZHPHD单号与状态",
      "name": "Techlink",
      "color": "#e73547"
    },
    {
      "id": "imile",
      "catalog": "imile",
      "slug": "imile-guoji-kuaidi-chaxun",
      "title": "iMile 国际快递查询：单号格式、英文轨迹与客服联系",
      "name": "iMile",
      "color": "#0032a0"
    },
    {
      "id": "postnl",
      "catalog": "spring-gds",
      "slug": "postnl-helan-kuaidi-chaxun",
      "title": "荷兰邮政（PostNL）查询：NL 与 3S 单号、状态与派送",
      "name": "PostNL",
      "color": "#ef7d00"
    },
    {
      "id": "dhlink",
      "catalog": "dhlink",
      "slug": "tuofeixia-dhlink-wuliu-chaxun",
      "title": "驼飞侠（DHLink）物流查询：敦煌网单号、状态与尾程",
      "name": "DHLink",
      "color": "#143167"
    },
    {
      "id": "dpd-pt",
      "catalog": "dpd-pt",
      "slug": "dpd-portugal-kuaidi-chaxun",
      "title": "DPD Portugal 葡萄牙快递查询：14 位单号、状态与自提点",
      "name": "DPD Portugal",
      "color": "#dc0032"
    },
    {
      "id": "tnt-it",
      "catalog": "tnt-it",
      "slug": "tnt-italy-tracking",
      "title": "TNT Italy 意大利快递查询：运单号、状态与派送失败处理",
      "name": "TNT Italy",
      "color": "#ff6600"
    },
    {
      "id": "tnt",
      "catalog": "tnt",
      "slug": "tnt-kuaidi-chaxun",
      "title": "TNT 快递查询：9 位运单号、物流状态与客服电话",
      "name": "TNT",
      "color": "#ff6600"
    },
    {
      "id": "sf-express",
      "catalog": "sf-express",
      "slug": "shunfeng-sf-express-wuliu-chaxun",
      "title": "顺丰国际（SF Express）物流查询：单号、状态与客服",
      "name": "SF Express",
      "color": "#dc1e32"
    },
    {
      "id": "straightship",
      "catalog": "straightship",
      "slug": "straightship-wuliu-chaxun",
      "title": "Straightship（迅联捷航）物流查询：STRTD 单号、状态与派送",
      "name": "Straightship",
      "color": "#6aae66"
    },
    {
      "id": "correios-br",
      "catalog": "correios-br",
      "slug": "correios-brazil-tracking",
      "title": "Correios 巴西邮政物流查询：单号、状态与清关指南",
      "name": "Correios Brazil",
      "color": "#ffd500"
    },
    {
      "id": "yto",
      "catalog": "yto",
      "slug": "yto-yuantong-wuliu-chaxun",
      "title": "圆通速递（YTO）物流查询：YT 单号、物流记录与客服电话",
      "name": "YTO Express",
      "color": "#4d148c"
    },
    {
      "id": "spx-br",
      "catalog": "spx-br",
      "slug": "spx-express-baxi-chaxun",
      "title": "巴西 SPX Express（Shopee Xpress）单号查询、状态与关税",
      "name": "SPX Express Brazil",
      "color": "#ee4d2d"
    },
    {
      "id": "pengyou",
      "catalog": "pengyou",
      "slug": "pengyou-wuliu-chaxun",
      "title": "捧友供应链（Pengyou）查询：PY单号与预设轨迹说明",
      "name": "Pengyou",
      "color": "#043a82"
    },
    {
      "id": "mtscn",
      "catalog": "mtscn",
      "slug": "meiteshun-wuliu-chaxun",
      "title": "美特顺物流（Meiteshun）查询：AMTS单号与预设轨迹",
      "name": "Meiteshun Logistics",
      "color": "#f89f3b"
    },
    {
      "id": "cttyex",
      "catalog": "cttyex",
      "slug": "chengtai-tongyun-cttyex-chaxun",
      "title": "诚泰通运（CTTYEX）查询：CTTY单号、英文轨迹与北京时间",
      "name": "CTTYEX",
      "color": "#0068AC"
    },
    {
      "id": "speedaf",
      "catalog": "speedaf",
      "slug": "speedaf-tracking",
      "title": "速达非（Speedaf Express）快递查询：单号、状态与客服",
      "name": "Speedaf",
      "color": "#ff8300"
    },
    {
      "id": "quick-rabbit",
      "catalog": "quick-rabbit",
      "slug": "quick-rabbit-wuliu-chaxun",
      "title": "Quick Rabbit 物流查询：PKG 单号、状态与 Citibox",
      "name": "Quick Rabbit",
      "color": "#f6851f"
    },
    {
      "id": "huaya-express",
      "catalog": "huaya-express",
      "slug": "huaya-yuntong-wuliu-chaxun",
      "title": "华亚运通（Huaya Express）查询：HYYT单号与状态",
      "name": "Huaya Express",
      "color": "#e60013"
    },
    {
      "id": "gofo-ca",
      "catalog": "gofo-ca",
      "slug": "gofo-ca-tracking",
      "title": "GOFO Canada 加拿大快递查询：GFCA 单号、状态与延误",
      "name": "GOFO Canada",
      "color": "#fc4c02"
    },
    {
      "id": "topyou",
      "catalog": "topyou",
      "slug": "tongyou-topyou-wuliu-chaxun",
      "title": "通邮（TopYou）国际物流查询：TYZR单号、状态与尾程",
      "name": "TopYou",
      "color": "#045cd7"
    },
    {
      "id": "mayiyinfu",
      "catalog": "mayiyinfu",
      "slug": "mayi-yinfu-wuliu-chaxun",
      "title": "蚂蚁银服（Mayi Yinfu）物流查询：MY单号、航班轨迹与DHL派送",
      "name": "Mayi Yinfu",
      "color": "#000000"
    },
    {
      "id": "jrt",
      "catalog": "jrt",
      "slug": "jinritong-jrt-wuliu-chaxun",
      "title": "今日通（JRT）物流查询：JRT…MM单号、航班轨迹与尾程",
      "name": "JRT",
      "color": "#023053"
    },
    {
      "id": "thhy",
      "catalog": "thhy",
      "slug": "tianhui-hengyuan-thhy-wuliu-chaxun",
      "title": "天汇恒源（THHY）物流查询：AT…TH单号与轨迹真伪识别",
      "name": "Tianhui Hengyuan",
      "color": "#00a2e9"
    },
    {
      "id": "js-express",
      "catalog": "js-express",
      "slug": "jisu-guoji-js-express-wuliu-chaxun",
      "title": "急速国际（JS Express）物流查询：JSE单号、状态与尾程",
      "name": "JS Express",
      "color": "#fe9b00"
    },
    {
      "id": "wanbexpress",
      "catalog": "wanbexpress",
      "slug": "wanbang-suda-wanbexpress-wuliu-chaxun",
      "title": "万邦速达（WanbExpress）查询：WNB…YQ单号与状态",
      "name": "WanbExpress",
      "color": "#da381b"
    },
    {
      "id": "gzyl",
      "catalog": "gzyl",
      "slug": "guangzhou-yunlian-gzyl-wuliu-chaxun",
      "title": "广州云链（GZYL）物流查询：YL…YQ单号与预设轨迹说明",
      "name": "GZYL",
      "color": "#4e79e1"
    },
    {
      "id": "shouwu",
      "catalog": "shouwu",
      "slug": "shouwu-guoji-wuliu-chaxun",
      "title": "守务国际（Shouwu）物流查询：SWECN单号、状态与尾程",
      "name": "Shouwu International",
      "color": "#be2c33"
    },
    {
      "id": "geodis-pl",
      "catalog": "geodis-pl",
      "slug": "geodis-poland-tracking",
      "title": "GEODIS Poland 波兰物流查询：PL 单号、状态与派送",
      "name": "GEODIS Poland",
      "color": "#3100e6"
    },
    {
      "id": "sending",
      "catalog": "sending",
      "slug": "sending-kuaidi-chaxun",
      "title": "Sending 西班牙快递查询：12 位单号、状态与电话",
      "name": "Sending",
      "color": "#e52021"
    },
    {
      "id": "hywl",
      "catalog": "hywl",
      "slug": "huiyi-wuliu-hywl-chaxun",
      "title": "汇一物流（HYWL）单号查询：TRK单号、中英文状态",
      "name": "Huiyi Logistics",
      "color": "#0b8f91"
    },
    {
      "id": "ywe",
      "catalog": "ywe",
      "slug": "ywe-tracking",
      "title": "YWE（YanwenExpress）快递查询：单号、状态与美国尾程派送",
      "name": "YWE",
      "color": "#055aa1"
    },
    {
      "id": "ecms-express",
      "catalog": "ecms-express",
      "slug": "yikeman-ecms-express-wuliu-chaxun",
      "title": "易客满（ECMS Express）物流查询：16位单号与状态",
      "name": "ECMS Express",
      "color": "#12823d"
    },
    {
      "id": "chunghwa-post",
      "catalog": "chunghwa-post",
      "slug": "zhonghua-youzheng-chunghwa-post-chaxun",
      "title": "中华邮政（Chunghwa Post）物流查询：TW单号、状态与联系",
      "name": "Chunghwa Post",
      "color": "#e8171f"
    },
    {
      "id": "raben",
      "catalog": "raben",
      "slug": "raben-tracking",
      "title": "Raben 物流查询：欧洲托盘与零担单号、状态与卸货说明",
      "name": "Raben",
      "color": "#df0020"
    },
    {
      "id": "jd-logistics",
      "catalog": "jd-logistics",
      "slug": "jingdong-wuliu-jd-logistics-chaxun",
      "title": "京东物流（JD Logistics）国际查询：单号、状态与客服",
      "name": "JD Logistics",
      "color": "#e1251b"
    },
    {
      "id": "singapore-post",
      "catalog": "singapore-post",
      "slug": "singapore-post-wuliu-chaxun",
      "title": "新加坡邮政（Singapore Post / SingPost）物流查询",
      "name": "Singapore Post",
      "color": "#2e5da8"
    },
    {
      "id": "zto",
      "catalog": "zto",
      "slug": "zhongtong-kuaidi-zto-wuliu-chaxun",
      "title": "中通快递（ZTO Express）单号查询：状态、驿站与客服",
      "name": "ZTO Express",
      "color": "#0279ff"
    },
    {
      "id": "cne",
      "catalog": "cne",
      "slug": "diyi-wuliu-cne-express-chaxun",
      "title": "递一物流（CNE Express）查询：3A5V单号与尾程",
      "name": "CNE Express",
      "color": "#e83828"
    },
    {
      "id": "japan-post",
      "catalog": "japan-post",
      "slug": "japan-post-tracking",
      "title": "日本邮政（Japan Post）快递查询：EMS 单号与状态说明",
      "name": "Japan Post",
      "color": "#cc0000"
    },
    {
      "id": "russian-post",
      "catalog": "russian-post",
      "slug": "russian-post-wuliu-chaxun",
      "title": "俄罗斯邮政（Russian Post / Почта России）物流查询",
      "name": "Russian Post",
      "color": "#1928dc"
    },
    {
      "id": "huahan",
      "catalog": "huahan",
      "slug": "huahan-wuliu-chaxun",
      "title": "华翰物流（HuaHan）单号查询：HHW…YQ状态与尾程",
      "name": "HuaHan Logistics",
      "color": "#0069e8"
    },
    {
      "id": "epost-global",
      "catalog": "epost-global",
      "slug": "epost-global-kuaidi-chaxun",
      "title": "ePost Global 快递查询：EPG 单号、物流状态与末端派送",
      "name": "ePost Global",
      "color": "#27967a"
    },
    {
      "id": "gls-at",
      "catalog": "gls-at",
      "slug": "gls-austria-tracking",
      "title": "GLS 奥地利（GLS Austria）物流查询：状态、自提点与联系方式",
      "name": "GLS Austria",
      "color": "#ffdd00"
    },
    {
      "id": "india-post",
      "catalog": "india-post",
      "slug": "india-post-tracking",
      "title": "India Post 印度邮政查询：Speed Post 单号与状态",
      "name": "India Post",
      "color": "#b82025"
    },
    {
      "id": "morning-global",
      "catalog": "morning-global",
      "slug": "morning-global-mg-wuliu-chaxun",
      "title": "Morning Global 物流查询：BR…MG单号、状态与客服联系",
      "name": "Morning Global",
      "color": "#050059"
    },
    {
      "id": "delhivery",
      "catalog": "delhivery",
      "slug": "delhivery-yindu-kuaidi-chaxun",
      "title": "Delhivery 印度快递查询：AWB 单号、状态与客服",
      "name": "Delhivery",
      "color": "#ed4136"
    },
    {
      "id": "emirates-post",
      "catalog": "emirates-post",
      "slug": "emirates-post-wuliu-chaxun",
      "title": "阿联酋邮政（Emirates Post）物流查询：AE 单号与状态",
      "name": "Emirates Post",
      "color": "#004a98"
    },
    {
      "id": "blue-dart",
      "catalog": "blue-dart",
      "slug": "blue-dart-wuliu-chaxun",
      "title": "Blue Dart 物流查询：11 位运单号与状态说明",
      "name": "Blue Dart",
      "color": "#0059a9"
    },
    {
      "id": "ekart",
      "catalog": "ekart",
      "slug": "ekart-yindu-kuaidi-chaxun",
      "title": "印度 Ekart 快递查询：14 位单号、状态与 RTO 退件说明",
      "name": "Ekart",
      "color": "#0074D9"
    },
    {
      "id": "j-and-t-middle-east",
      "catalog": "j-and-t-middle-east",
      "slug": "jt-express-middle-east-chaxun",
      "title": "极兔中东（J&T Express 沙特/阿联酋/科威特）查询与客服",
      "name": "J&T Express Middle East",
      "color": "#e60000"
    },
    {
      "id": "globalpost",
      "catalog": "globalpost",
      "slug": "globalpost-tracking",
      "title": "GlobalPost 物流查询：GP 运单号、状态与末端派送公司",
      "name": "GlobalPost",
      "color": "#11003a"
    },
    {
      "id": "cdek",
      "catalog": "cdek",
      "slug": "cdek-wuliu-chaxun",
      "title": "CDEK（СДЭК）物流查询：订单号与状态含义",
      "name": "CDEK",
      "color": "#00b33c"
    },
    {
      "id": "dsv",
      "catalog": "dsv",
      "slug": "dsv-wuliu-chaxun",
      "title": "DSV 物流查询：运单号、状态与 DB Schenker",
      "name": "DSV",
      "color": "#002664"
    },
    {
      "id": "rl-carriers",
      "catalog": "rl-carriers",
      "slug": "rl-carriers-wuliu-chaxun",
      "title": "R+L Carriers 货运查询：PRO 单号、状态与理赔",
      "name": "R+L Carriers",
      "color": "#006647"
    },
    {
      "id": "far-international",
      "catalog": "far-international",
      "slug": "fanyuan-guoji-far-international-chaxun",
      "title": "泛远国际（FAR International）物流查询：单号、状态与联系",
      "name": "FAR International",
      "color": "#003b75"
    },
    {
      "id": "jifan",
      "catalog": "jifan",
      "slug": "jifan-guoji-wuliu-chaxun",
      "title": "吉凡国际物流（Jifan）查询：JFGJ单号、状态与北京时间",
      "name": "Jifan",
      "color": "#c81011"
    },
    {
      "id": "ozon-logistics",
      "catalog": "ozon-logistics",
      "slug": "ozon-eluosi-kuaidi-chaxun",
      "title": "Ozon 物流（Ozon Rocket）查询：单号格式与俄语状态",
      "name": "Ozon",
      "color": "#005bff"
    },
    {
      "id": "dpd-be",
      "catalog": "dpd-be",
      "slug": "dpd-belgium-wuliu-chaxun",
      "title": "DPD Belgium 比利时物流查询：状态、派送与联系",
      "name": "DPD Belgium",
      "color": "#dc0032"
    },
    {
      "id": "sto",
      "catalog": "sto",
      "slug": "shentong-kuaidi-sto-chaxun",
      "title": "申通快递（STO Express）查询：单号规则、签收状态与客服",
      "name": "STO Express",
      "color": "#ff6700"
    },
    {
      "id": "j-and-t-cn",
      "catalog": "j-and-t-cn",
      "slug": "jt-express-china-jitu-chaxun",
      "title": "极兔速递（J&T Express China）单号查询、状态与 956025 客服",
      "name": "J&T Express China",
      "color": "#e62019"
    },
    {
      "id": "andreani",
      "catalog": "andreani",
      "slug": "andreani-wuliu-chaxun",
      "title": "Andreani 物流查询：阿根廷运单号与状态说明",
      "name": "Andreani",
      "color": "#d71920"
    },
    {
      "id": "spx-sg",
      "catalog": "spx-sg",
      "slug": "spx-express-singapore-chaxun",
      "title": "新加坡 SPX Express（Shopee Xpress）单号查询与状态说明",
      "name": "SPX Express Singapore",
      "color": "#ee4d2d"
    },
    {
      "id": "17feia",
      "catalog": "17feia",
      "slug": "yiqifei-17feia-wuliu-chaxun",
      "title": "一起飞（17FEIA）国际物流查询：FEI…YQ单号与状态",
      "name": "17FEIA",
      "color": "#00a9e4"
    },
    {
      "id": "tsjy",
      "catalog": "tsjy",
      "slug": "tongshi-jiyun-tsjy-wuliu-chaxun",
      "title": "通世集运（TSJY）物流查询：TSJY单号、状态与17FEIA转单",
      "name": "TSJY",
      "color": "#4042e2"
    },
    {
      "id": "stone3pl",
      "catalog": "stone3pl",
      "slug": "stone3pl-kuajing-chaxun",
      "title": "Stone3PL 国际物流单号查询：S 运单格式、状态与客服",
      "name": "Stone3PL",
      "color": "#003d98"
    },
    {
      "id": "j-and-t-uk",
      "catalog": "j-and-t-uk",
      "slug": "jt-express-uk-kuaidi-chaxun",
      "title": "英国极兔速递 J&T Express UK 查询：JTUK 单号与状态",
      "name": "J&T Express UK",
      "color": "#e60000"
    },
    {
      "id": "early-bird",
      "catalog": "early-bird",
      "slug": "early-bird-wuliu-chaxun",
      "title": "Early Bird 物流查询：瑞典夜间派送、状态与快递柜",
      "name": "Early Bird",
      "color": "#dd7f4a"
    },
    {
      "id": "xinghe-freight",
      "catalog": "xinghe-freight",
      "slug": "xinghe-huoyun-wuliu-chaxun",
      "title": "星和货运（Xinghe Freight）物流查询：GZXH单号与状态",
      "name": "Xinghe Freight",
      "color": "#0e3a6b"
    },
    {
      "id": "sxw-express",
      "catalog": "sxw-express",
      "slug": "shengxingwang-sxw-express-wuliu-chaxun",
      "title": "胜兴旺（SXW Express）物流查询：SXW单号与状态",
      "name": "SXW Express",
      "color": "#003876"
    },
    {
      "id": "j-and-t-br",
      "catalog": "j-and-t-br",
      "slug": "jt-express-brazil-tracking",
      "title": "极兔巴西（J&T Express Brazil）查询：单号、状态与清关",
      "name": "J&T Express Brazil",
      "color": "#e60000"
    },
    {
      "id": "anjun",
      "catalog": "anjun",
      "slug": "anjun-wuliu-chaxun",
      "title": "安骏物流（Anjun Logistics）查询：中巴专线与巴西清关",
      "name": "Anjun Logistics",
      "color": "#009944"
    },
    {
      "id": "xyl-ships",
      "catalog": "xyl-ships",
      "slug": "xyl-ships-fxyl-wuliu-chaxun",
      "title": "XYL-Ships（FXYL）物流查询：NUDE与XLWL单号及状态",
      "name": "XYL-Ships",
      "color": "#b13c3c"
    },
    {
      "id": "jadlog",
      "catalog": "jadlog",
      "slug": "jadlog-wuliu-chaxun",
      "title": "Jadlog 物流查询：14 位单号、Pickup、Predict 与状态",
      "name": "Jadlog",
      "color": "#dc0032"
    },
    {
      "id": "gls-pl",
      "catalog": "gls-pl",
      "slug": "gls-poland-tracking",
      "title": "GLS 波兰（GLS Poland）物流查询：状态、自提点、快递柜与客服",
      "name": "GLS Poland",
      "color": "#ffdd00"
    },
    {
      "id": "j-and-t-mx",
      "catalog": "j-and-t-mx",
      "slug": "jt-express-mexico-kuaidi-chaxun",
      "title": "墨西哥极兔速递 J&T Express Mexico 查询：JMX 单号与客服",
      "name": "J&T Express Mexico",
      "color": "#e60012"
    },
    {
      "id": "bulgarian-posts",
      "catalog": "bulgarian-posts",
      "slug": "bulgarian-posts-tracking",
      "title": "保加利亚邮政（Bulgarian Posts）物流查询：PS 与 BG 单号、清关和理赔",
      "name": "Bulgarian Posts",
      "color": "#ffd100"
    },
    {
      "id": "haypost",
      "catalog": "haypost",
      "slug": "haypost-tracking",
      "title": "亚美尼亚邮政（HayPost）物流查询：AM 单号、状态与联系方式",
      "name": "HayPost",
      "color": "#fa4616"
    },
    {
      "id": "360lion",
      "catalog": "360lion",
      "slug": "360lion-wuliu-chaxun",
      "title": "纬狮物流（360Lion）单号查询：WSH 与 AR 格式、轨迹与客服",
      "name": "360Lion",
      "color": "#cf5d15"
    },
    {
      "id": "ydh",
      "catalog": "ydh",
      "slug": "ydh-yida-kuajing-chaxun",
      "title": "义达跨境（YDH）快递查询：YQ 与 AZ 单号、状态与客服",
      "name": "YDH",
      "color": "#015db9"
    },
    {
      "id": "a-tu-hora-express",
      "catalog": "a-tu-hora-express",
      "slug": "a-tu-hora-express-tracking",
      "title": "A Tu Hora Express 物流查询：单号、邮编要求与客服",
      "name": "A Tu Hora Express",
      "color": "#1d2a84"
    },
    {
      "id": "gls-hu",
      "catalog": "gls-hu",
      "slug": "gls-hungary-tracking",
      "title": "GLS 匈牙利（GLS Hungary）物流查询：单号、快递柜与客服",
      "name": "GLS Hungary",
      "color": "#ffdd00"
    },
    {
      "id": "anserx",
      "catalog": "anserx",
      "slug": "anserx-bantouyan-chaxun",
      "title": "斑头雁国际物流（AnserX）查询：BTYCN 单号、状态与客服",
      "name": "AnserX",
      "color": "#2478be"
    },
    {
      "id": "chukou1",
      "catalog": "chukou1",
      "slug": "chukou1-chukouyi-chaxun",
      "title": "出口易（Chukou1 / CK1）单号查询：EC-FirstClass 轨迹与客服",
      "name": "Chukou1",
      "color": "#0b4da2"
    },
    {
      "id": "cj-packet",
      "catalog": "cj-packet",
      "slug": "cj-packet-kuaidi-chaxun",
      "title": "CJ Packet 快递查询：CJP 单号、汇总状态与客服联系",
      "name": "CJ Packet",
      "color": "#ff7700"
    },
    {
      "id": "cmt-express",
      "catalog": "cmt-express",
      "slug": "cmt-express-chengmaotong-chaxun",
      "title": "诚贸通（CMT Express）单号查询：CMT 轨迹、深圳仓与客服",
      "name": "CMT Express",
      "color": "#ed010b"
    },
    {
      "id": "dobropost",
      "catalog": "dobropost",
      "slug": "dobropost-jiyun-chaxun",
      "title": "DobroPost 集运单号查询：DBRPST 轨迹、清关与自提点",
      "name": "DobroPost",
      "color": "#ffbe2e"
    },
    {
      "id": "jiayang",
      "catalog": "jiayang",
      "slug": "jiayang-guoji-chaxun",
      "title": "东莞嘉扬国际（Jiayang）单号查询：JYGJ 轨迹、清关与客服",
      "name": "Jiayang International",
      "color": "#105bcc"
    },
    {
      "id": "jumppoint-international",
      "catalog": "jumppoint-international",
      "slug": "jumppoint-international-chaxun",
      "title": "Jumppoint 国际快递查询：JP 单号、香港跨境时效与客服",
      "name": "Jumppoint International Express",
      "color": "#0f4ae1"
    },
    {
      "id": "kuayuan-express",
      "catalog": "kuayuan-express",
      "slug": "kuayuan-express-chaxun",
      "title": "跨源速递（Kuayuan Express）单号查询：14 位运单与客服",
      "name": "Kuayuan Express",
      "color": "#084b86"
    },
    {
      "id": "pdu",
      "catalog": "pdu",
      "slug": "pdu-youdilian-chaxun",
      "title": "PDU 友递联单号查询：PD 尾缀运单、轨迹状态与上海客服",
      "name": "PDU",
      "color": "#005cfe"
    },
    {
      "id": "sengi-express",
      "catalog": "sengi-express",
      "slug": "sengi-express-senji-chaxun",
      "title": "森吉物流（Sengi Express）查询：中意单号、清关与客服",
      "name": "Sengi Express",
      "color": "#1a44a4"
    },
    {
      "id": "yunda",
      "catalog": "yunda",
      "slug": "yunda-kuaidi-chaxun",
      "title": "韵达快递单号查询：13 与 15 位运单、轨迹状态与 95546 客服",
      "name": "Yunda Express",
      "color": "#ffce00"
    },
    {
      "id": "buffalo-ex",
      "catalog": "buffalo-ex",
      "slug": "buffalo-ex-nanfei-chaxun",
      "title": "BUFFALO 国际物流单号查询：BUFZA 轨迹、南非清关与客服",
      "name": "BUFFALO",
      "color": "#bc8c4d"
    },
    {
      "id": "bfe-express",
      "catalog": "bfe-express",
      "slug": "bfe-express-kuajing-chaxun",
      "title": "BFE Express 国际快递查询：CK 与 WYB 单号、状态与客服",
      "name": "BFE Express",
      "color": "#122a88"
    }
  ],
  "nl": [
    {
      "id": "colis-prive",
      "catalog": "colis-prive",
      "slug": "colis-prive-pakket-volgen",
      "title": "Colis Privé pakket volgen: statussen, afhaalpunt en contact",
      "name": "Colis Privé",
      "color": "#aa78ff"
    },
    {
      "id": "mondial-relay",
      "catalog": "mondial-relay",
      "slug": "mondial-relay-pakket-volgen",
      "title": "Mondial Relay-pakket volgen: Afhaalpunt, status, klachten",
      "name": "Mondial Relay",
      "color": "#ef3026"
    },
    {
      "id": "vinted-go",
      "catalog": "vinted-go",
      "slug": "vinted-go-pakket-volgen",
      "title": "Vinted Go-pakket volgen: status, locker en afhaalcode",
      "name": "Vinted Go",
      "color": "#004654"
    },
    {
      "id": "bpost",
      "catalog": "bpost",
      "slug": "bpost-pakje-volgen",
      "title": "bpost pakje volgen: barcode, statussen en Afhaalpunt",
      "name": "bpost",
      "color": "#ef2637"
    },
    {
      "id": "dhl-ecommerce-nl",
      "catalog": "dhl-ecommerce-nl",
      "slug": "dhl-ecommerce-pakket-volgen",
      "title": "DHL eCommerce pakket volgen: status, ServicePoint, klacht",
      "name": "DHL eCommerce Netherlands",
      "color": "#ffcc00"
    },
    {
      "id": "heppner",
      "catalog": "heppner",
      "slug": "heppner-zending-volgen",
      "title": "Heppner zending volgen: nummer, postcode en leverafspraak",
      "name": "Heppner",
      "color": "#005baa"
    },
    {
      "id": "postnl",
      "catalog": "spring-gds",
      "slug": "postnl-pakket-volgen",
      "title": "PostNL pakket volgen: track & trace, statussen, bezorging",
      "name": "PostNL",
      "color": "#ef7d00"
    },
    {
      "id": "gzyl",
      "catalog": "gzyl",
      "slug": "gzyl-pakket-volgen",
      "title": "GZYL track & trace: pakket volgen met een YL…YQ-nummer",
      "name": "GZYL",
      "color": "#4e79e1"
    },
    {
      "id": "dpd-nl",
      "catalog": "dpd-nl",
      "slug": "dpd-nederland-volgen",
      "title": "DPD Nederland volgen — pakketnummer, tijdvak en contact",
      "name": "DPD Netherlands",
      "color": "#dc0032"
    },
    {
      "id": "dpd-be",
      "catalog": "dpd-be",
      "slug": "dpd-belgie-volgen",
      "title": "DPD België volgen — pakketnummer, Pickup en bezorging",
      "name": "DPD Belgium",
      "color": "#dc0032"
    }
  ],
  "sv": [
    {
      "id": "postnord",
      "catalog": "postnord",
      "slug": "postnord-spara-paket",
      "title": "Spåra paket med PostNord: statusar, ombud och tull",
      "name": "PostNord",
      "color": "#00a0d6"
    },
    {
      "id": "bring",
      "catalog": "bring-posten",
      "slug": "bring-spara-paket",
      "title": "Spåra Bring-paket: statusar, ombud, paketbox och tull",
      "name": "Bring",
      "color": "#56b529"
    },
    {
      "id": "posti",
      "catalog": "posti",
      "slug": "posti-spara-paket",
      "title": "Spåra Posti-paket: försändelsekod, automat och kundtjänst",
      "name": "Posti",
      "color": "#ff8000"
    },
    {
      "id": "matkahuolto",
      "catalog": "matkahuolto",
      "slug": "matkahuolto-spara-paket",
      "title": "Spåra Matkahuolto-paket: statusar, hämtning och kundtjänst",
      "name": "Matkahuolto",
      "color": "#001e60"
    },
    {
      "id": "topyou",
      "catalog": "topyou",
      "slug": "topyou-spara-paket",
      "title": "Spåra paket med TopYou: TYZR-nummer, statusar och leverans",
      "name": "TopYou",
      "color": "#045cd7"
    },
    {
      "id": "dsv",
      "catalog": "dsv",
      "slug": "dsv-sparning",
      "title": "DSV-spårning: sändningsnummer, status och DB Schenker",
      "name": "DSV",
      "color": "#002664"
    },
    {
      "id": "citymail",
      "catalog": "citymail",
      "slug": "spara-paket-citymail",
      "title": "Spåra paket med CityMail: BCM-nummer, statusar och ombud",
      "name": "CityMail",
      "color": "#fc4c02"
    },
    {
      "id": "early-bird",
      "catalog": "early-bird",
      "slug": "early-bird-sparning",
      "title": "Early Bird spårning — nattleverans, paketskåp och hjälp",
      "name": "Early Bird",
      "color": "#dd7f4a"
    }
  ],
  "da": [
    {
      "id": "postnord",
      "catalog": "postnord",
      "slug": "postnord-spor-pakke",
      "title": "PostNord Track & Trace: spor din pakke i Danmark",
      "name": "PostNord",
      "color": "#00a0d6"
    },
    {
      "id": "bring",
      "catalog": "bring-posten",
      "slug": "bring-spor-pakke",
      "title": "Bring sporing: spor din pakke i Danmark",
      "name": "Bring",
      "color": "#56b529"
    },
    {
      "id": "dao",
      "catalog": "dao",
      "slug": "dao-spor-pakke",
      "title": "DAO Track & Trace: find din pakke, daoSHOP og natlevering",
      "name": "DAO",
      "color": "#e30613"
    },
    {
      "id": "dsv",
      "catalog": "dsv",
      "slug": "dsv-sporing",
      "title": "DSV sporing — forsendelsesnumre, DB Schenker og status",
      "name": "DSV",
      "color": "#002664"
    }
  ],
  "nb": [
    {
      "id": "postnord",
      "catalog": "postnord",
      "slug": "postnord-sporing",
      "title": "PostNord-sporing: sendingsnummer, statuser og toll",
      "name": "PostNord",
      "color": "#00a0d6"
    },
    {
      "id": "bring",
      "catalog": "bring-posten",
      "slug": "bring-sporing",
      "title": "Posten- og Bring-sporing: nummer, status og hentefrister",
      "name": "Bring",
      "color": "#56b529"
    },
    {
      "id": "helthjem",
      "catalog": "helthjem",
      "slug": "helthjem-sporing",
      "title": "Helthjem sporing — sporingsnummer, statuser og hentested",
      "name": "Helthjem",
      "color": "#ffb202"
    }
  ],
  "fi": [
    {
      "id": "vinted-go",
      "catalog": "vinted-go",
      "slug": "vinted-go-seuranta",
      "title": "Vinted Go -paketin seuranta: koodit, automaatit ja apu",
      "name": "Vinted Go",
      "color": "#004654"
    },
    {
      "id": "postnord",
      "catalog": "postnord",
      "slug": "postnord-seuranta",
      "title": "PostNord-seuranta: lähetystunnus, tilat ja tulli",
      "name": "PostNord",
      "color": "#00a0d6"
    },
    {
      "id": "posti",
      "catalog": "posti",
      "slug": "posti-seuranta",
      "title": "Postin seuranta: lähetystunnus, tilat ja pakettiautomaatit",
      "name": "Posti",
      "color": "#ff8000"
    },
    {
      "id": "matkahuolto",
      "catalog": "matkahuolto",
      "slug": "matkahuolto-seuranta",
      "title": "Matkahuollon seuranta: lähetystunnus, nouto ja tilat",
      "name": "Matkahuolto",
      "color": "#001e60"
    }
  ],
  "cs": [
    {
      "id": "packeta",
      "catalog": "packeta",
      "slug": "packeta-sledovani-zasilky",
      "title": "Sledování zásilky Zásilkovny: číslo Z, stavy a Z-BOX",
      "name": "Packeta",
      "color": "#ba1b02"
    },
    {
      "id": "ppl",
      "catalog": "ppl",
      "slug": "ppl-sledovani-zasilky",
      "title": "PPL sledování zásilky: číslo, stavy a výdejní místa",
      "name": "PPL",
      "color": "#004b93"
    },
    {
      "id": "ceska-posta",
      "catalog": "ceska-posta",
      "slug": "ceska-posta-sledovani-zasilek",
      "title": "Česká pošta sledování zásilek: podací číslo a stavy",
      "name": "Česká pošta",
      "color": "#002776"
    },
    {
      "id": "wedo",
      "catalog": "wedo",
      "slug": "sledovani-zasilky-one-by-allegro-cz",
      "title": "Sledování zásilky One by Allegro CZ (WE|DO) — stavy a boxy",
      "name": "One by Allegro CZ",
      "color": "#006d57"
    }
  ],
  "sk": [
    {
      "id": "packeta",
      "catalog": "packeta",
      "slug": "packeta-sledovanie-zasielky",
      "title": "Sledovanie zásielky Packeta: číslo Z, stavy a Z-BOX",
      "name": "Packeta",
      "color": "#ba1b02"
    },
    {
      "id": "slovak-parcel-service",
      "catalog": "slovak-parcel-service",
      "slug": "sps-sledovanie-zasielky",
      "title": "Sledovanie zásielky SPS: číslo, stavy a Balíkovo",
      "name": "Slovak Parcel Service",
      "color": "#f58220"
    },
    {
      "id": "slovenska-posta",
      "catalog": "slovenska-posta",
      "slug": "slovenska-posta-sledovanie-zasielok",
      "title": "Slovenská pošta sledovanie zásielok: podacie číslo a stavy",
      "name": "Slovenská pošta",
      "color": "#f9bb00"
    },
    {
      "id": "gzyl",
      "catalog": "gzyl",
      "slug": "gzyl-sledovanie-zasielky",
      "title": "Sledovanie zásielky GZYL z Číny: číslo YL…YQ a stavy",
      "name": "GZYL",
      "color": "#4e79e1"
    }
  ],
  "hu": [
    {
      "id": "packeta",
      "catalog": "packeta",
      "slug": "packeta-csomagkovetes",
      "title": "Packeta csomagkövetés: Z-szám, státuszok, átvétel",
      "name": "Packeta",
      "color": "#ba1b02"
    },
    {
      "id": "express-one",
      "catalog": "express-one",
      "slug": "express-one-csomagkovetes",
      "title": "Express One csomagkövetés: státuszok, Exopoint, elérhetőség",
      "name": "Express One",
      "color": "#ec6500"
    },
    {
      "id": "foxpost",
      "catalog": "foxpost",
      "slug": "foxpost-csomagkovetes",
      "title": "Foxpost csomagkövetés: státuszok, nyitókód, átvétel",
      "name": "Foxpost",
      "color": "#b70100"
    },
    {
      "id": "magyar-posta",
      "catalog": "magyar-posta",
      "slug": "magyar-posta-nyomkovetes",
      "title": "Magyar Posta nyomkövetés: státuszok, PostaPont, vám",
      "name": "Magyar Posta",
      "color": "#346631"
    },
    {
      "id": "gzyl",
      "catalog": "gzyl",
      "slug": "gzyl-csomagkovetes",
      "title": "GZYL csomagkövetés Kínából: YL…YQ szám, státuszok",
      "name": "GZYL",
      "color": "#4e79e1"
    },
    {
      "id": "gls-hu",
      "catalog": "gls-hu",
      "slug": "gls-magyarorszag-csomagkovetes",
      "title": "GLS Hungary csomagkövetés: számok, automaták, segítség",
      "name": "GLS Hungary",
      "color": "#ffdd00"
    },
    {
      "id": "dpd-hu",
      "catalog": "dpd-hu",
      "slug": "dpd-hu-csomagkovetes",
      "title": "DPD Hungary csomagkövetés: 14 jegyű kódok, FOXPOST és súgó",
      "name": "DPD Hungary",
      "color": "#dc0032"
    }
  ],
  "ro": [
    {
      "id": "packeta",
      "catalog": "packeta",
      "slug": "packeta-urmarire-colet",
      "title": "Urmărire colet Packeta: numărul Z, statusuri și Z-BOX",
      "name": "Packeta",
      "color": "#ba1b02"
    },
    {
      "id": "sameday",
      "catalog": "sameday",
      "slug": "urmarire-colet-sameday",
      "title": "Urmărire colet Sameday — AWB, easybox, statusuri și contact",
      "name": "Sameday",
      "color": "#e92427"
    },
    {
      "id": "speedy",
      "catalog": "speedy",
      "slug": "urmarire-colet-speedy",
      "title": "Urmărire colet Speedy — AWB 11 cifre, statusuri și contact",
      "name": "Speedy",
      "color": "#dc0032"
    },
    {
      "id": "cargus",
      "catalog": "cargus",
      "slug": "cargus-urmarire-colet",
      "title": "Urmărire colet Cargus: AWB, statusuri și SHIP & GO",
      "name": "Cargus",
      "color": "#f68121"
    },
    {
      "id": "fan-courier",
      "catalog": "fan-courier",
      "slug": "fan-courier-urmarire-awb",
      "title": "Urmărire colet FAN Courier: AWB, statusuri și FANbox",
      "name": "FAN Courier",
      "color": "#0f2345"
    },
    {
      "id": "j-and-t-de",
      "catalog": "j-and-t-de",
      "slug": "jt-express-germania-urmarire-colet",
      "title": "Urmărire colet J&T Germania: numărul JTDE și statusurile",
      "name": "J&T Express Germany",
      "color": "#e60000"
    },
    {
      "id": "dpd-ro",
      "catalog": "dpd-ro",
      "slug": "urmarire-colet-dpd-romania",
      "title": "Urmărire colet DPD România: AWB, statusuri și Smart Control",
      "name": "DPD Romania",
      "color": "#dc0032"
    },
    {
      "id": "posta-romana",
      "catalog": "posta-romana",
      "slug": "urmarire-colet-posta-romana",
      "title": "Urmărire colet Poșta Română — coduri RO, vamă și contact",
      "name": "Poșta Română",
      "color": "#ea0029"
    },
    {
      "id": "fan-courier-international",
      "catalog": "fan-courier-international",
      "slug": "fan-courier-international-urmarire-awb",
      "title": "Urmărire colet FAN Courier International: AWB și statusuri",
      "name": "FAN Courier International",
      "color": "#2daae1"
    }
  ],
  "bg": [
    {
      "id": "box-now",
      "catalog": "box-now",
      "slug": "box-now-prosledyavane-na-pratka",
      "title": "Проследяване на пратка BOX NOW: номер, статуси и ПИН",
      "name": "BOX NOW",
      "color": "#44d62c"
    },
    {
      "id": "sameday",
      "catalog": "sameday",
      "slug": "sameday-prosledyavane-na-pratka",
      "title": "Проследяване на пратка Sameday: AWB, easybox и статуси",
      "name": "Sameday",
      "color": "#e92427"
    },
    {
      "id": "speedy",
      "catalog": "speedy",
      "slug": "prosledyavane-spidi",
      "title": "Проследяване на пратка Спиди — товарителница и автомати",
      "name": "Speedy",
      "color": "#dc0032"
    },
    {
      "id": "econt",
      "catalog": "econt",
      "slug": "prosledyavane-ekont",
      "title": "Проследяване на пратка Еконт — номера, офиси и Еконтомат",
      "name": "Econt",
      "color": "#234182"
    },
    {
      "id": "eushipments",
      "catalog": "eushipments",
      "slug": "prosledyavane-eushipments",
      "title": "Проследяване на euShipments: статуси, куриери и контакт",
      "name": "euShipments",
      "color": "#0a8fcf"
    },
    {
      "id": "gzyl",
      "catalog": "gzyl",
      "slug": "gzyl-prosledyavane-na-pratka",
      "title": "Проследяване на пратка GZYL: номер YL…YQ и статуси",
      "name": "GZYL",
      "color": "#4e79e1"
    },
    {
      "id": "cvc",
      "catalog": "cvc",
      "slug": "cvc-prosledyavane-na-pratka",
      "title": "Проследяване на пратка CVC (Си Ви Си): номер и статуси",
      "name": "CVC",
      "color": "#652b9e"
    },
    {
      "id": "transpress",
      "catalog": "transpress",
      "slug": "transpress-prosledyavane-na-pratka",
      "title": "Транспрес (Transpress): проследяване на пратка и статуси",
      "name": "Transpress",
      "color": "#1e6ee2"
    },
    {
      "id": "bulgarian-posts",
      "catalog": "bulgarian-posts",
      "slug": "prosledyavane-balgarski-poshti",
      "title": "Проследяване Български пощи: кодове, статуси, митница",
      "name": "Bulgarian Posts",
      "color": "#ffd100"
    }
  ],
  "el": [
    {
      "id": "box-now",
      "catalog": "box-now",
      "slug": "box-now-entopismos-dematos",
      "title": "Εντοπισμός δέματος BOX NOW: αριθμός, καταστάσεις, PIN",
      "name": "BOX NOW",
      "color": "#44d62c"
    },
    {
      "id": "acs-courier",
      "catalog": "acs-courier",
      "slug": "acs-courier-entopismos-apostolis",
      "title": "Εντοπισμός αποστολής ACS: αριθμός, καταστάσεις, lockers",
      "name": "ACS Courier",
      "color": "#e42229"
    },
    {
      "id": "elta",
      "catalog": "elta",
      "slug": "elta-entopismos-dematos",
      "title": "Εντοπισμός δέματος ΕΛΤΑ: κωδικός, καταστάσεις, PostBox",
      "name": "ELTA Hellenic Post",
      "color": "#0063a9"
    },
    {
      "id": "geniki-taxydromiki",
      "catalog": "geniki-taxydromiki",
      "slug": "geniki-taxydromiki-entopismos-apostolis",
      "title": "Εντοπισμός αποστολής Γενικής Ταχυδρομικής: voucher, θυρίδες",
      "name": "Geniki Taxydromiki",
      "color": "#004fa0"
    },
    {
      "id": "cyprus-post",
      "catalog": "cyprus-post",
      "slug": "kypriaka-tachydromeia-entopismos-dematos",
      "title": "Κυπριακά Ταχυδρομεία: εντοπισμός δέματος, κωδικός, τελωνείο",
      "name": "Cyprus Post",
      "color": "#e4a50e"
    },
    {
      "id": "gzyl",
      "catalog": "gzyl",
      "slug": "gzyl-entopismos-dematos",
      "title": "Εντοπισμός δέματος GZYL: αριθμός YL…YQ και καταστάσεις",
      "name": "GZYL",
      "color": "#4e79e1"
    }
  ],
  "hr": [
    {
      "id": "box-now",
      "catalog": "box-now",
      "slug": "box-now-pracenje-posiljke",
      "title": "Praćenje BOX NOW pošiljke: paketomat, PIN i statusi",
      "name": "BOX NOW",
      "color": "#44d62c"
    },
    {
      "id": "hrvatska-posta",
      "catalog": "hrvatska-posta",
      "slug": "hrvatska-posta-pracenje-posiljke",
      "title": "Praćenje pošiljke Hrvatske pošte: statusi, rokovi i kontakt",
      "name": "Hrvatska pošta",
      "color": "#ffd100"
    },
    {
      "id": "j-and-t-de",
      "catalog": "j-and-t-de",
      "slug": "jt-express-njemacka-pracenje-posiljke",
      "title": "Praćenje J&T pošiljke u Njemačkoj: JTDE broj i statusi",
      "name": "J&T Express Germany",
      "color": "#e60000"
    },
    {
      "id": "gzyl",
      "catalog": "gzyl",
      "slug": "gzyl-pracenje-posiljke",
      "title": "Praćenje GZYL pošiljke iz Kine: broj YL…YQ i statusi",
      "name": "GZYL",
      "color": "#4e79e1"
    }
  ],
  "sl": [
    {
      "id": "box-now",
      "catalog": "box-now",
      "slug": "box-now-sledenje-posiljke",
      "title": "Sledenje pošiljke BOX NOW: paketomat, PIN-koda in statusi",
      "name": "BOX NOW",
      "color": "#44d62c"
    },
    {
      "id": "posta-slovenije",
      "catalog": "posta-slovenije",
      "slug": "posta-slovenije-sledenje-posiljk",
      "title": "Sledenje pošiljk Pošte Slovenije: statusi, prevzem, kontakt",
      "name": "Pošta Slovenije",
      "color": "#ffc600"
    }
  ],
  "lt": [
    {
      "id": "omniva",
      "catalog": "omniva",
      "slug": "omniva-siuntu-sekimas",
      "title": "Omniva siuntų sekimas: statusai, paštomatai ir kontaktai",
      "name": "Omniva",
      "color": "#f86300"
    },
    {
      "id": "venipak",
      "catalog": "venipak",
      "slug": "venipak-siuntu-sekimas",
      "title": "Venipak siuntų sekimas: statusai, paštomatai ir kurjeris",
      "name": "Venipak",
      "color": "#9339f2"
    },
    {
      "id": "lietuvos-pastas",
      "catalog": "lietuvos-pastas",
      "slug": "lietuvos-pastas-siuntu-sekimas",
      "title": "Lietuvos paštas siuntų sekimas: statusai ir paštomatai",
      "name": "Lietuvos paštas",
      "color": "#fedd00"
    },
    {
      "id": "samus",
      "catalog": "samus",
      "slug": "samus-siuntu-sekimas",
      "title": "SAMUS siuntų sekimas: numeris, būsenos ir atsiėmimas",
      "name": "SAMUS",
      "color": "#e50079"
    },
    {
      "id": "siuntos-autobusais",
      "catalog": "siuntos-autobusais",
      "slug": "siuntos-autobusais-siuntu-sekimas",
      "title": "Siuntos autobusais: sekimas, terminalai ir atsiėmimas",
      "name": "Siuntos autobusais",
      "color": "#00843d"
    },
    {
      "id": "zipasgo",
      "catalog": "zipasgo",
      "slug": "zipasgo-siuntu-sekimas",
      "title": "ZipasGo siuntų sekimas: Parcel Stars, kurjeriai ir pagalba",
      "name": "ZipasGo",
      "color": "#ffdb1c"
    }
  ],
  "lv": [
    {
      "id": "omniva",
      "catalog": "omniva",
      "slug": "omniva-sutijumu-izsekosana",
      "title": "Omniva sūtījumu izsekošana: statusi, pakomāti un kontakti",
      "name": "Omniva",
      "color": "#f86300"
    },
    {
      "id": "venipak",
      "catalog": "venipak",
      "slug": "venipak-sutijumu-izsekosana",
      "title": "Venipak sūtījumu izsekošana: statusi, pakomāti un kurjers",
      "name": "Venipak",
      "color": "#9339f2"
    },
    {
      "id": "latvijas-pasts",
      "catalog": "latvijas-pasts",
      "slug": "latvijas-pasts-sutijumu-izsekosana",
      "title": "Latvijas Pasts sūtījumu izsekošana — numuri, pakomāti",
      "name": "Latvijas Pasts",
      "color": "#0c72ba"
    }
  ],
  "et": [
    {
      "id": "posti",
      "catalog": "posti",
      "slug": "smartposti-paki-jalgimine",
      "title": "SmartPosti paki jälgimine: olekud, pakiautomaat ja toll",
      "name": "Posti",
      "color": "#ff8000"
    },
    {
      "id": "omniva",
      "catalog": "omniva",
      "slug": "omniva-saadetise-jalgimine",
      "title": "Omniva saadetise jälgimine: olekud, pakiautomaat ja toll",
      "name": "Omniva",
      "color": "#f86300"
    },
    {
      "id": "venipak",
      "catalog": "venipak",
      "slug": "venipak-saadetise-jalgimine",
      "title": "Venipaki saadetise jälgimine: olekud, pakiautomaat ja kuller",
      "name": "Venipak",
      "color": "#9339f2"
    },
    {
      "id": "dk-logistics-ee",
      "catalog": "dk-logistics-ee",
      "slug": "dk-logistics-saadetise-jalgimine",
      "title": "DK Logisticsi saadetise jälgimine: saateleht ja kontaktid",
      "name": "DK Logistics",
      "color": "#f58428"
    },
    {
      "id": "jet-express-ee",
      "catalog": "jet-express-ee",
      "slug": "jet-expressi-saadetise-jalgimine",
      "title": "Jet Expressi saadetise jälgimine: tarneajad ja kontakt",
      "name": "Jet Express",
      "color": "#d61b22"
    }
  ],
  "uk": [
    {
      "id": "gls-de",
      "catalog": "gls-de",
      "slug": "gls-vidstezhennia-posylky-nimechchyna",
      "title": "GLS відстеження посилки в Німеччині: статуси, ParcelShop",
      "name": "GLS Germany",
      "color": "#ffdd00"
    },
    {
      "id": "meest",
      "catalog": "meest",
      "slug": "meest-vidstezhennia-posylky",
      "title": "Meest відстеження посилки: номер, статуси, отримання",
      "name": "Meest",
      "color": "#0061af"
    },
    {
      "id": "nova-poshta",
      "catalog": "nova-poshta",
      "slug": "nova-poshta-vidstezhennia",
      "title": "Нова пошта відстеження: номер ЕН, статуси, зберігання",
      "name": "Nova Poshta",
      "color": "#e30613"
    },
    {
      "id": "ukrposhta",
      "catalog": "ukrposhta",
      "slug": "vidstezhennya-ukrposhta",
      "title": "Відстеження Укрпошта — трек-номери, статуси та митниця",
      "name": "Ukrposhta",
      "color": "#ffbe0c"
    },
    {
      "id": "nova-global",
      "catalog": "nova-global",
      "slug": "vidstezhennia-nova-global",
      "title": "Відстеження Nova Global: коди NPG і NGUS, митниця, контакти",
      "name": "Nova Global",
      "color": "#da291c"
    }
  ],
  "sr": [
    {
      "id": "posta-srbije",
      "catalog": "posta-srbije",
      "slug": "posta-srbije-pracenje-posiljke",
      "title": "Praćenje pošiljke Pošte Srbije: statusi, rokovi i kontakt",
      "name": "Pošta Srbije",
      "color": "#fbba00"
    },
    {
      "id": "m-one",
      "catalog": "m-one",
      "slug": "pracenje-posiljki-m-one",
      "title": "Praćenje pošiljki M-ONE (Milšped ONE) — statusi i kontakt",
      "name": "M-ONE",
      "color": "#024da0"
    }
  ]
};

/**
 * Peek's languages a carrier's page is written in. Each has its carriers' own page; a language without one
 * sends its readers to the English one until a carrier's page is written in it.
 */
export const CARRIER_LOCALES: readonly Locale[] = ["en","de","fr","it","es","pt","pt-br","pl","ru","zh"];
