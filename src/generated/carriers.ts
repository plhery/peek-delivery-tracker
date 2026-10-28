// Generated from content/carriers by scripts/generate-guides.mjs. Do not edit.
import type { PageLanguage } from '../lib/locale';

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
      "title": "An Post tracking: statuses, missed deliveries and contact",
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
      "title": "GOFO France tracking: statuses, delivery and contact",
      "name": "GOFO France",
      "color": "#fc4c02"
    },
    {
      "id": "gofo-it",
      "catalog": "gofo-it",
      "slug": "gofo-italy-tracking",
      "title": "GOFO Italy tracking: statuses, delivery and contact",
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
      "title": "Amazon Shipping tracking: numbers, statuses and help",
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
      "title": "J&T Express Germany tracking: statuses, neighbours, contact",
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
      "id": "packeta",
      "catalog": "packeta",
      "slug": "packeta-sendungsverfolgung",
      "title": "Packeta Sendungsverfolgung: Z-Nummer, Status und Abholung",
      "name": "Packeta",
      "color": "#ba1b02"
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
      "id": "austrian-post",
      "catalog": "austrian-post",
      "slug": "austrian-post-sendungsverfolgung",
      "title": "Austrian Post Sendungsverfolgung: Status, Abholung, Hilfe",
      "name": "Austrian Post",
      "color": "#ffdc00"
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
      "id": "amazon-logistics",
      "catalog": "amazon-logistics",
      "slug": "amazon-logistics-tracking",
      "title": "Amazon Logistics Tracking: Paket verfolgen und Hilfe",
      "name": "Amazon Logistics",
      "color": "#ff9900"
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
      "title": "Amazon Shipping Sendungsverfolgung: Status und Hilfe",
      "name": "Amazon Shipping",
      "color": "#ff9900"
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
      "id": "swiss-post-cargo",
      "catalog": "swiss-post-cargo",
      "slug": "swiss-post-cargo-sendungsverfolgung",
      "title": "Swiss Post Cargo Tracking: Status, Zustellung, Kontakt",
      "name": "Swiss Post Cargo",
      "color": "#ffcc00"
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
      "id": "j-and-t-de",
      "catalog": "j-and-t-de",
      "slug": "jt-express-sendungsverfolgung",
      "title": "J&T Express Sendungsverfolgung: Status, Nachbar, Kontakt",
      "name": "J&T Express Germany",
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
      "id": "imile",
      "catalog": "imile",
      "slug": "imile-sendungsverfolgung",
      "title": "iMile Sendungsverfolgung: Nummer, Status, Zustellung",
      "name": "iMile",
      "color": "#0032a0"
    },
    {
      "id": "tnt",
      "catalog": "tnt",
      "slug": "tnt-sendungsverfolgung",
      "title": "TNT Sendungsverfolgung: Sendungsnummer, Status, Zustellung",
      "name": "TNT",
      "color": "#ff6600"
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
      "id": "paack",
      "catalog": "paack",
      "slug": "paack-suivi",
      "title": "Paack suivi colis\u00a0: numéro, code postal et statuts",
      "name": "Paack",
      "color": "#5b2dee"
    },
    {
      "id": "chronopost",
      "catalog": "chronopost",
      "slug": "chronopost-suivi",
      "title": "Chronopost suivi\u00a0: numéro, statuts et point relais",
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
      "id": "yanwen",
      "catalog": "yanwen",
      "slug": "yanwen-suivi",
      "title": "Yanwen suivi\u00a0: numéros, statuts et qui contacter",
      "name": "Yanwen",
      "color": "#8cbe1e"
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
      "id": "bpost",
      "catalog": "bpost",
      "slug": "bpost-suivi",
      "title": "bpost suivi\u00a0: code-barres, statuts et point relais",
      "name": "bpost",
      "color": "#ef2637"
    },
    {
      "id": "gofo-fr",
      "catalog": "gofo-fr",
      "slug": "gofo-france-suivi",
      "title": "GOFO France suivi colis\u00a0: statuts, livraison et contact",
      "name": "GOFO France",
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
      "id": "landmark-global",
      "catalog": "landmark-global",
      "slug": "landmark-global-suivi",
      "title": "Landmark Global suivi\u00a0: statuts, livreur et contact",
      "name": "Landmark Global",
      "color": "#e62337"
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
      "title": "Amazon Shipping suivi\u00a0: numéro, statuts et colis non reçu",
      "name": "Amazon Shipping",
      "color": "#ff9900"
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
      "id": "swiss-post-cargo",
      "catalog": "swiss-post-cargo",
      "slug": "swiss-post-cargo-suivi",
      "title": "Swiss Post Cargo suivi\u00a0: statuts, livraison et contact",
      "name": "Swiss Post Cargo",
      "color": "#ffcc00"
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
      "id": "juxi-express",
      "catalog": "juxi-express",
      "slug": "juxi-express-suivi",
      "title": "Juxi Express suivi\u00a0: numéro JXC, livraison et contact",
      "name": "Juxi Express",
      "color": "#e2393c"
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
      "id": "tnt",
      "catalog": "tnt",
      "slug": "tnt-suivi-colis",
      "title": "TNT suivi colis\u00a0: bon de transport, statuts et livraison",
      "name": "TNT",
      "color": "#ff6600"
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
      "id": "brt",
      "catalog": "brt",
      "slug": "brt-tracking",
      "title": "BRT tracking: dov'è il pacco e cosa fare se non arriva",
      "name": "BRT",
      "color": "#dc0032"
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
      "id": "poste-italiane",
      "catalog": "poste-italiane",
      "slug": "poste-italiane-tracking",
      "title": "Poste Italiane tracking: codici 1UW e 3UW, stati e giacenza",
      "name": "Poste Italiane",
      "color": "#eedc00"
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
      "id": "gls-it",
      "catalog": "gls-it",
      "slug": "gls-italy-tracking",
      "title": "GLS Italy tracking: pacco, stati, giacenza e contatti",
      "name": "GLS Italy",
      "color": "#ffdd00"
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
      "id": "amazon-logistics",
      "catalog": "amazon-logistics",
      "slug": "amazon-logistics-tracking",
      "title": "Amazon Logistics tracking: dove seguire il pacco e contatti",
      "name": "Amazon Logistics",
      "color": "#ff9900"
    },
    {
      "id": "gofo-it",
      "catalog": "gofo-it",
      "slug": "gofo-italy-tracking",
      "title": "GOFO Italy tracking: stati, consegna e contatti",
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
      "id": "swiss-post-cargo",
      "catalog": "swiss-post-cargo",
      "slug": "swiss-post-cargo-tracking",
      "title": "Swiss Post Cargo tracking: stati, consegna e contatti",
      "name": "Swiss Post Cargo",
      "color": "#ffcc00"
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
      "title": "DHL Express tracking: lettera di vettura, stati e contatti",
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
      "id": "china-post",
      "catalog": "china-post",
      "slug": "china-post-tracking",
      "title": "Tracking China Post: codice, stati in cinese e consegna",
      "name": "China Post",
      "color": "#006d46"
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
      "id": "tnt-it",
      "catalog": "tnt-it",
      "slug": "tnt-italy-tracking",
      "title": "TNT tracking: lettera di vettura, stati e giacenza",
      "name": "TNT Italy",
      "color": "#ff6600"
    }
  ],
  "es": [
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
      "id": "hermes-de",
      "catalog": "hermes-de",
      "slug": "hermes-seguimiento",
      "title": "Hermes seguimiento: paquetes de Alemania a España",
      "name": "Hermes",
      "color": "#0091cd"
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
      "id": "4px",
      "catalog": "four-px",
      "slug": "4px-seguimiento",
      "title": "4PX seguimiento: estados, quién entrega y contacto",
      "name": "4PX",
      "color": "#005add"
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
      "title": "DHL eCommerce Iberia seguimiento: estados y teléfono",
      "name": "DHL eCommerce Iberia",
      "color": "#ffcc00"
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
      "id": "landmark-global",
      "catalog": "landmark-global",
      "slug": "landmark-global-seguimiento",
      "title": "Landmark Global seguimiento: estados, entrega y contacto",
      "name": "Landmark Global",
      "color": "#e62337"
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
      "title": "Amazon Shipping seguimiento: estados, entrega y contacto",
      "name": "Amazon Shipping",
      "color": "#ff9900"
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
      "id": "dachser",
      "catalog": "dachser",
      "slug": "dachser-seguimiento",
      "title": "Dachser seguimiento: enlace, estados y entrega con cita",
      "name": "Dachser",
      "color": "#005ca9"
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
      "id": "usps",
      "catalog": "usps",
      "slug": "usps-seguimiento",
      "title": "USPS seguimiento: estados, reintento de entrega y envíos",
      "name": "USPS",
      "color": "#004b87"
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
      "id": "imile",
      "catalog": "imile",
      "slug": "imile-seguimiento",
      "title": "Seguimiento iMile: números, estados y quién reparte",
      "name": "iMile",
      "color": "#0032a0"
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
      "id": "correios-br",
      "catalog": "correios-br",
      "slug": "correios-brasil-seguimiento",
      "title": "Correios Brasil seguimiento: código, estados y aduanas",
      "name": "Correios Brazil",
      "color": "#ffd500"
    }
  ],
  "pt": [
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
      "id": "chronopost",
      "catalog": "chronopost",
      "slug": "chronopost-rastreio",
      "title": "Chronopost rastreio: estados e entrega em Portugal",
      "name": "Chronopost",
      "color": "#0056a7"
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
      "id": "dhl-ecommerce-es",
      "catalog": "dhl-ecommerce-es",
      "slug": "dhl-ecommerce-iberia-rastreio",
      "title": "DHL eCommerce Iberia rastreio: estados e ServicePoints",
      "name": "DHL eCommerce Iberia",
      "color": "#ffcc00"
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
      "id": "ecoscooting",
      "catalog": "ecoscooting",
      "slug": "ecoscooting-rastreio",
      "title": "Ecoscooting rastreio: estados, pontos de recolha e contacto",
      "name": "Ecoscooting",
      "color": "#3fa7ff"
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
      "title": "DHL Express tracking: carta de porte, estados e contactos",
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
      "id": "china-post",
      "catalog": "china-post",
      "slug": "china-post-rastreio",
      "title": "China Post rastreio: número, estados e entrega em Portugal",
      "name": "China Post",
      "color": "#006d46"
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
      "id": "tnt",
      "catalog": "tnt",
      "slug": "tnt-rastreio",
      "title": "TNT rastreio: carta de porte, estados e entrega",
      "name": "TNT",
      "color": "#ff6600"
    }
  ],
  "pl": [
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
      "id": "inpost",
      "catalog": "inpost",
      "slug": "inpost-sledzenie-przesylki",
      "title": "InPost śledzenie przesyłki: numer, statusy i Paczkomat",
      "name": "InPost",
      "color": "#ffcc05"
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
      "id": "yanwen",
      "catalog": "yanwen",
      "slug": "yanwen-sledzenie",
      "title": "Yanwen śledzenie przesyłki: statusy, kurier, kontakt",
      "name": "Yanwen",
      "color": "#8cbe1e"
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
      "id": "dhl-ecommerce-pl",
      "catalog": "dhl-ecommerce-pl",
      "slug": "dhl-ecommerce-poland-sledzenie-przesylki",
      "title": "DHL eCommerce Poland śledzenie przesyłki: statusy i punkty",
      "name": "DHL eCommerce Poland",
      "color": "#ffcc00"
    },
    {
      "id": "allegro-one",
      "catalog": "allegro-one",
      "slug": "allegro-one-sledzenie",
      "title": "Allegro One śledzenie przesyłki: statusy i odbiór paczki",
      "name": "Allegro One",
      "color": "#006d57"
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
      "id": "jcex",
      "catalog": "jcex",
      "slug": "jcex-sledzenie",
      "title": "JCEX śledzenie przesyłki: numer JCZ, statusy, kto doręcza",
      "name": "JCEX",
      "color": "#0080cc"
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
      "id": "postnl",
      "catalog": "spring-gds",
      "slug": "postnl-sledzenie-przesylki",
      "title": "PostNL śledzenie przesyłki: numer, statusy, doręczenie",
      "name": "PostNL",
      "color": "#ef7d00"
    },
    {
      "id": "tnt",
      "catalog": "tnt",
      "slug": "tnt-sledzenie-przesylki",
      "title": "TNT śledzenie przesyłki: numer, statusy i doręczenie",
      "name": "TNT",
      "color": "#ff6600"
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
    }
  ],
  "fi": [
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
    }
  ],
  "uk": [
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
    }
  ]
};
