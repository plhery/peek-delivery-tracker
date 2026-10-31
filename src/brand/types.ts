/**
 * Brand vocabulary.
 *
 * What it is: the shape of the nine brand properties, of a palette declared in
 * `src/brand/identities.json`, and of the truck geometry the web renders.
 * What it is not: no data and no behaviour; every declaration here is erased at
 * build time. `src/generated/brand.ts` annotates its constants
 * with these types, which is why they live in their own module: the generated
 * file must not import the runtime side of `src/brand`.
 */

/** The nine CSS custom properties a carrier identity resolves to. */
export type BrandProperty =
  | 'surface-light'
  | 'surface-dark'
  | 'ink-light'
  | 'ink-dark'
  | 'brand-light'
  | 'brand-dark'
  | 'truck'
  | 'edge'
  | 'accent';

export type CarrierPalette = Record<BrandProperty, string>;

/** Which livery the truck wears. Carriers without a custom decoration use `default`. */
export type CarrierDecal = 'default' | 'dhl' | 'ups' | 'fedex' | 'dpd' | 'amazon' | 'japan-post'
  | 'swiss-post' | 'quickpac' | 'la-poste' | 'chronopost' | 'india-post' | 'mondial-relay' | 'postnl'
  | 'usps' | 'royal-mail' | 'canada-post' | 'australia-post' | 'tnt' | 'correos' | 'yamato'
  | 'china-post' | 'inpost' | 'bpost' | 'austrian-post' | 'hermes' | 'nova-poshta' | 'korea-post'
  | 'nz-post' | 'poczta-polska' | 'ukrposhta' | 'correios-br' | 'ctt' | 'sf-express' | 'ninja-van'
  | 'hongkong-post' | 'thailand-post' | 'bring' | 'packeta' | 'gls' | 'evri' | 'postnord' | 'poste-italiane'
  | 'aramex' | 'j-and-t' | 'aliexpress' | 'zto' | 'yto' | 'sto' | 'yunda' | 'jd-logistics' | 'ems' | 'seur'
  | 'mrw' | 'nacex' | 'purolator' | 'parcelforce' | 'posti' | 'an-post' | 'delhivery' | 'blue-dart' | 'dtdc'
  | 'sagawa' | 'ontrac' | 'estafeta' | 'pos-malaysia' | 'four-px' | 'planzer' | 'tipsa' | 'gofo' | 'dachser'
  | 'asendia' | 'colis-prive' | 'ciblex' | 'colisweb' | 'uniuni' | 'yunexpress' | 'canpar' | 'relais-colis'
  | 'singapore-post' | 'geodis' | 'lbc-express' | 'spx' | 'ekart' | 'xpressbees' | 'cne' | 'yanwen'
  | 'sunyou' | 'speedx' | 'spee-dee' | 'speedpak' | 'the-courier-guy' | 'landmark-global' | 'old-dominion'
  | 'paack' | 'c-chez-vous' | 'ecoscooting' | 'correos-chile' | 'intelcom' | 'shipup' | 'heppner'
  | 'j-and-t-cargo' | 'emile' | 'abf-freight' | 'acs-courier' | 'allegro-one' | 'apc-overnight'
  | 'aras-kargo' | 'box-now' | 'cdek' | 'ceska-posta' | 'chit-chats' | 'correos-de-mexico'
  | 'cyprus-post' | 'day-ross' | 'dsv' | 'dx' | 'dynalogic' | 'envialia' | 'epost-global' | 'estes'
  | 'fan-courier' | 'gebrueder-weiss' | 'geniki-taxydromiki' | 'jitsu' | 'kuehne-nagel'
  | 'latvijas-pasts' | 'loomis-express' | 'lso' | 'magyar-posta' | 'meest' | 'nationex'
  | 'post-luxembourg' | 'posta-slovenije' | 'posta-srbije' | 'ptt' | 'redpack' | 'rl-carriers'
  | 'russian-post' | 'saia' | 'sameday' | 'speedy' | 'trans-o-flex' | 'ubi-smart-parcel'
  | 'wanbexpress' | 'whistl' | 'xdp' | 'xpo-ltl' | 'yurtici-kargo' | 'zeleris' | '99minutos'
  | 'cargus' | 'cdl' | 'dao' | 'early-bird' | 'econt' | 'elta' | 'express-one' | 'foxpost' | 'geis'
  | 'go-express' | 'helthjem' | 'hrvatska-posta' | 'instabox' | 'lietuvos-pastas' | 'maltapost'
  | 'matkahuolto' | 'omniva' | 'orlen-paczka' | 'osm-worldwide' | 'paquetexpress' | 'passport'
  | 'ppl' | 'slovak-parcel-service' | 'slovenska-posta' | 'stallion-express' | 'venipak'
  | 'vinted-go' | '5post' | 'a-duie-pyle' | 'aaa-cooper' | 'andreani' | 'anjun' | 'anteraja'
  | 'apc-postal-logistics' | 'arrow-xl' | 'averitt' | 'azul-cargo' | 'barid-al-maghrib' | 'belpost'
  | 'best-transport' | 'better-trucks' | 'bex-express' | 'blue-express' | 'bulgarian-posts'
  | 'burd-delivery' | 'castores' | 'central-transport' | 'ceva-logistics' | 'chilexpress'
  | 'chunghwa-post' | 'citylink-express' | 'citysprint' | 'cj-logistics' | 'cj-packet'
  | 'coordinadora' | 'correo-argentino' | 'courier-center' | 'couriersplease' | 'cycloon'
  | 'd-express' | 'danske-fragtmaend' | 'dayton-freight' | 'dellin' | 'deppon'
  | 'direct-freight-express' | 'egypt-post' | 'emirates-post' | 'flash-express' | 'forward-air'
  | 'fukuyama' | 'gati' | 'gdex' | 'ghn' | 'ghtk' | 'hanjin' | 'hepsijet' | 'hunter-express'
  | 'ics-courier' | 'ids-logistik' | 'imile' | 'interrapidisimo' | 'israel-post' | 'jadlog'
  | 'jersey-post' | 'jne' | 'kazpost' | 'kex' | 'kolay-gelsin' | 'logen' | 'loggi' | 'lotte-global'
  | 'mainfreight' | 'maritime-ontario' | 'naqel' | 'nipost' | 'nippon-express' | 'nz-couriers'
  | 'oca' | 'olva-courier' | 'overseas-express' | 'palletways' | 'pecom' | 'pilot-freight'
  | 'pitt-ohio' | 'pos-indonesia' | 'post-haste' | 'posta-moldovei' | 'posta-north-macedonia'
  | 'posta-romana' | 'posta-shqiptare' | 'posturinn' | 'professional-couriers' | 'raben'
  | 'ram-hand-to-hand' | 'roadrunner' | 'saudi-post' | 'seino' | 'seko' | 'sendex' | 'sending'
  | 'serpost' | 'servientrega' | 'sfc' | 'shadowfax' | 'shree-maruti' | 'sicepat'
  | 'skynet-worldwide' | 'smsa-express' | 'south-african-post-office' | 'southeastern-freight-lines'
  | 'speedex' | 'starken' | 'surat-kargo' | 'team-global-express' | 'tforce-freight' | 'toptrans'
  | 'total-express' | 'trackon' | 'trendyol-express' | 'vasp-expresso' | 'viettel-post' | 'vnpost'
  | 'ward-trucking' | '17feia' | 'tsjy' | 'hywl' | 'dhlink' | 'gzyl' | 'thhy' | 'jrt' | 'jhgj'
  | 'ktd' | 'swiftx' | 'celeritas' | 'asyad-express' | 'border-express' | 'bosta'
  | 'correo-uruguayo' | 'courier-it' | 'daylight-transport' | 'deprisa' | 'envia-colombia'
  | 'europochta' | 'midland-transport' | 'oak-harbor' | 'skroutz-last-mile' | 'tci-express'
  | 'urbano' | 'vrl-logistics' | 'blueex' | 'leopards-courier' | 'tcs-pakistan' | 'postex'
  | 'slgtrax' | 'm-and-p' | 'pakistan-post' | 'pathao-courier' | 'redx' | 'steadfast'
  | 'sundarban-courier' | 'paperfly' | 'ecourier' | 'bangladesh-post' | 'sri-lanka-post' | 'domex'
  | 'akash-ganga' | 'madhur-courier' | 'shree-nandan' | 'st-courier' | 'tirupati-courier'
  | 'prompt-xpress' | 'pronto-lanka' | 'nepal-post' | 'idexpress' | 'lion-parcel' | 'sapx-express'
  | 'tiki' | 'wahana' | 'line-clear' | '2go' | 'flash-express-ph' | 'jrs-express' | 'xde-logistics'
  | 'chunil' | 'gs-postbox' | 'hapdong' | 'ilyang-logis' | 'kunyoung' | 'hct-logistics' | 'kerry-tj'
  | 'pelican-express' | 'seven-eleven-tw' | 't-cat' | 'kex-express-hk' | 'sbs-sokuhai'
  | 'tracx-logis' | 'quantium-solutions' | 'janio' | 'j-and-t-cn' | 'equick' | 'pfc-express'
  | 'ecms-express' | '139express' | 'winit' | 'energia-tk' | 'zheldor' | 'kit-tk' | 'alem-tat'
  | 'sat-ukraine' | 'taxydema' | 'dropp' | 'yalidine' | 'zr-express' | 'fargo-courier'
  | 'internet-express' | 'mds-collivery' | 'paxi' | 'buffalo-ex' | 'aeropost' | 'aks-express'
  | 'algerie-poste' | 'aymakan' | 'azerpost' | 'best-express-vn' | 'city-express-rs' | 'cse-russia'
  | 'dohrn-transfer' | 'ems-vietnam' | 'ethiopian-post' | 'euroexpress-ba' | 'followmont'
  | 'ghana-post' | 'groupe-guilbault' | 'haypost' | 'hi-trans' | 'jordan-post'
  | 'la-poste-tunisienne' | 'libanpost' | 'mauritius-post' | 'mylerz' | 'ncs-courier' | 'nhat-tin'
  | 'nim-express' | 'northline' | 'ozon-logistics' | 'pargo' | 'pbt' | 'polaris-transport'
  | 'posta-kenya' | 'postaplus' | 'quiqup' | 'roadie' | 'rpx-indonesia' | 'saee' | 'scg-express'
  | 'tanzania-posts' | 'tst-cf-express' | 'uzbekistan-post' | 'zajil' | '4-72' | 'aex-paraguay'
  | 'ampm' | 'chazki' | 'cruz-del-sur' | 'forza-delivery' | 'guatex' | 'jamaica-post'
  | 'laar-courier' | 'pullman-cargo' | 'tealca' | 'tufesa' | 'via-cargo' | 'yimidida' | 'zoom-ve'
  | 'mayiyinfu' | 'cttyex' | 'ameex' | 'amerijet' | 'anousith' | 'ap-cargo' | 'bookurier'
  | 'botswana-post' | 'bts-uz' | 'budbee' | 'bussgods' | 'campost' | 'correos-bolivia'
  | 'courier-plus-ng' | 'credifin' | 'daewoo-fastex' | 'easy-mail' | 'evropat' | 'exline-kz'
  | 'fargo-uz' | 'fastfrate' | 'first-delivery-tn' | 'franch-express' | 'gel-express' | 'gobolt'
  | 'gogo-xpress' | 'horoz' | 'integral-pack' | 'jeebly' | 'kargoist' | 'la-poste-ci'
  | 'la-poste-senegal' | 'mailplus-au' | 'major-express' | 'mandae' | 'messenger-cz' | 'nord-wheel'
  | 'ocasa' | 'opt-nc' | 'pmm' | 'pony-express' | 'shipa' | 'shree-anjani' | 'ues-uy' | 'upaya'
  | 'vimenpaq' | 'vozovoz' | 'x-express-ba' | 'metropolitan-warehouse' | 'citymail' | 'correos-cuba'
  | 'correos-el-salvador' | 'delnext' | 'hispapost' | 'intereuropa' | 'iran-post' | 'kronos-express'
  | 'm-one' | 'pack-man' | 'pannon-xp' | 'phlpost' | 'redur' | 'torrestir' | 'afghan-post' | 'corre'
  | 'correos-nicaragua' | 'kyrgyz-post' | 'la-poste-burkina-faso' | 'lao-post' | 'pitney-bowes'
  | 'a1-post' | 'direx' | 'fermopoint' | 'fg-delivery' | 'go4' | 'happy-post' | 'hp-mostar'
  | 'hr-parcel' | 'intex-paketdienst' | 'liccardi' | 'logoix' | 'nox' | 'peddler' | 'pigeon-express'
  | 'posta-faroe' | 'tusass' | 'tws-express' | 'byhd' | 'eurodis' | 'parcelpoint' | 'drop2shop'
  | 'neighbour-express' | 'packli-punkt' | 'yilong-logistics' | 'codexpress' | 'designer-transport'
  | 'direct-couriers' | 'ewe' | 'homexpress' | 'postmedia-parcel-services' | 'precision-deliveries'
  | 'transport-rdl' | 'energo' | 't-group' | 'method' | 'sailpost' | 'tforce-logistics'
  | 'globalpost' | 'united-delivery-service' | 'best-overnite-express' | 'estes-forwarding' | 'ajex'
  | 'exelot' | 'kwick-box' | 'labaih' | 'shipblu' | 'starlinks' | 'gtl-goto' | 'paxel' | 'pickupp'
  | '247express' | 'fleetoptics' | 'straightship' | 'apple-express' | 'ab-courier'
  | 'lightning-express' | 'kyoungdong' | 'yc-delivery' | 'sagawa-global' | 'copa-courier'
  | 'hound-express' | 'pdq-chile' | 'skyking' | 'veloces' | 'valmo' | 'rivigo' | 'bombino' | 'tccs'
  | 'tej-couriers' | 'shree-mahavir' | 'maruti-air' | 'icc-worldwide' | 'noest'
  | 'bjs-home-delivery' | 'norsk-global' | 'one-world-express' | 'xlcourier' | 'transmission'
  | 'packs' | 'tdn' | 'cbl-logistica' | 'inter-courier' | 'sp-express' | 'dragon-star-curier'
  | 'connect-couriers' | 'fastdrop' | 'pro-carrier' | 'teleship' | 'a-tu-hora-express' | 'transaher'
  | 'urvaam' | 'closer-logistics' | 'chronodiali' | 'cst-italia' | 'tmm-express'
  | 'next-level-delivery' | 'pdn-express' | 'x-trac' | 'hovership' | 'shipentegra'
  | 'pts-worldwide-express' | 'evdemo-lojistik' | 'big-smart' | 'proships' | 'gta-airskip'
  | 'domain-logistics' | 'fleetex' | 'postplus' | 'bringer-parcel' | 'micro-express' | 'l-post'
  | 'vestovoy' | '4sides' | 'dobropost' | 'flippost' | 'jet-logistic' | 'starex-az'
  | 'mongol-infinity-post' | 'peqasus-az' | 'c3x' | 'eco-express-ae' | 'logistiq' | 'quicksilver-sa'
  | 'skyex-ae' | 'team-express-ae' | 'tfm-express' | 'transcorp' | 'air-star-xpress'
  | 'first-fly-express' | 'flyking' | 'goodluck-courier' | 'grand-slam-express' | 'jetline-couriers'
  | 'pavan-courier' | 'sahara-express' | 'shree-mahabali-express' | 'speed-and-safe' | 'ocs-ana'
  | 'icl-express' | 'atlantic-courier' | 'gms-worldwide' | 'pushpak-courier'
  | 'united-worldwide-couriers' | 'dnx-cargo' | 'daily-xpress' | 'best-express-my'
  | 'royal-express-mm' | 'airpak-express' | 'makati-express' | 'indah-logistik' | 'pandu-logistics'
  | 'pcp-express' | 'houng-ah-loun' | 'netpost-vn' | 'tp-logistics-th' | 'pcs-logistics-vn'
  | 'rr-express-sg' | 'eparcel-kr' | 'aci-express-kr' | 'gsi-express' | 'today-kr' | 'exmate'
  | 'winion-logis' | 'last-one-mile-coop' | 'dpex' | 'mrl-global' | 'fliway' | 'morning-global'
  | 'rpx-hk' | 'montenegro-post' | 'vitran-express' | 'am-home-delivery' | 'american-west-express'
  | 'ccfs' | 'custom-companies' | 'hercules-freight' | 'gaash' | 'orian' | 'qwintry' | 'skypostal'
  | 'ait-worldwide' | 'team-worldwide' | 'huahan' | 'anserx' | 'chukou1' | 'jumppoint'
  | 'ycs-logistics' | 'lingdong' | 'jcex' | 'shiprocket' | 'first-line' | 'kun-express'
  | 'juxi-express' | 'techlink' | 'dayone' | 'shouwu' | 'ywe' | 'far-international'
  | 'intel-valley' | 'pengyou' | 'pin-ag' | 'tusk-logistics'
  | 'eushipments' | 'timely-titan' | 'no-limit' | 'starlinks-global' | 'mhi'
  | 'e-commerce-international' | 'boxtal'
  | 'huaya-express' | 'quick-rabbit'
  | 'dealersend' | 'pactic' | 'express-one-bg'
  | 'logisimple' | 'viadireta'
  | 'meso-global'
  | 'jifan' | 'youbang-express'
  | 'fastbox'
  | 'ydh'
  | 'takesend'
  | 'flyt-express'
  | 'joom-logistics'
  | 'veho'
  | 'hfd'
  | 'cheetah-delivery'
  | 'parcel-one'
  | 'post-one'
  | 'polonez'
  | 'tas-courier'
  | 'famafutar'
  | 'first-flight-me'
  | 'dd-express'
  | 'boyuan'
  | 'emex'
  | 'interlogistica'
  | 'intime-bg'
  | 'asigna'
  | 'pflogistics'
  | 'costco-logistics'
  | 'quality-post'
  | 'rxo'
  | 'runner-express'
  | 'jp-home'
  | 'cargoline'
  | 'overnite'
  | 'ambro-express'
  | 'city-air-express'
  | 'ameriship'
  | 'gio-express'
  | 'sway'
  | '3pe-express'
  | 'xinghe-freight'
  | 'instapack'
  | 'obibox'
  | 'avis-logistics'
  | 'dakota-cargo'
  | 'sentral-cargo'
  | 'depo'
  | 'e-cargo'
  | 'sengi-express'
  | 'trans-ontario'
  | 'pallex'
  | 'airmee'
  | 'fofr'
  | 'akis-express'
  | 'collicare'
  | 'western-bid'
  | 'ace-lt'
  | 'ace-lv'
  | 'kuayue-express'
  | 'islandwide-express'
  | 'piggy-express'
  | 'rozetka-delivery'
  | 'hrx'
  | 'cargobus'
  | 'jetpak'
  | 'tonami'
  | 'okaken'
  | 'trexity'
  | 'savar-express'
  | 'xgs'
  | 'optima-overnight'
  | 'plycon'
  | 'peninsula-truck-lines'
  | 'ross-express'
  | 'centurion-transport'
  | 'airroad'
  | 'cope-sensitive-freight'
  | 'kiwi-express'
  | 'inter-express-logistics'
  | 'ats-healthcare'
  | 'whiz-delivery'
  | 'ets-express'
  | 'rincos'
  | 'apg-ecommerce'
  | 'toyou'
  | 'kec-ecommerce'
  | 'thelorry'
  | 'wizmo'
  | 'ams-egroup'
  | 'zes-express'
  | 'profit-fields'
  | 'alljoy'
  | 'dpe-express'
  | 'goodmaji'
  | 'lex-my'
  | 'dex-pk'
  | 'parxl'
  | 'efs-asia'
  | 'kart2door'
  | 'hart2hart-express'
  | 'top-logistics-au'
  | 'shipglobal-in'
  | 'faster-ae'
  | 'shipter'
  | 'linear-logistics'
  | 'tonda-global'
  | 'liuchen'
  | 'eac-cn'
  | 'lbtx'
  | 'deliverdirect'
  | 'ace-ee'
  | 'transnatur'
  | 'rhenus-it'
  | 'emons'
  | 'noerpel'
  | 'super-parcel-track'
  | 'ural-express'
  | 'onex'
  | 'gcx'
  | 'tusou'
  | 'wise-express'
  | 'rhenus-de'
  | 'skybox'
  | 'delivro'
  | 'tforce-integrated-solutions'
  | 'holisol'
  | 'ubx-uk'
  | 'ttk-europe'
  | 'inntralog'
  | 'dg-transporte'
  | 'bonds-couriers'
  | 'amalgamated-distribution'
  | 'victorian-freight-specialists'
  | 'vt-freight-express'
  | 'aland-post'
  | 'aruba-post'
  | 'gibraltar-post'
  | 'samoa-post'
  | 'burundi-post'
  | 'correo-paraguayo'
  | 'tcc-co'
  | 'fm-transportes'
  | 'xcargo'
  | 'paket-cl'
  | 'fez-delivery'
  | 'famiport'
  | 'ctc-express'
  | 'maple-logistics'
  | 'seino-super-express'
  | 'taqbin-hk'
  | 'safexpress'
  | 'movin'
  | 'lex-vn'
  | 'best-express-th'
  | 'airspeed'
  | 'j-and-t-eg'
  | 'egxpress'
  | 'tawssil'
  | 'ase-express'
  | 'zimpost'
  | 'airwings-india'
  | 'apx-logistics'
  | 'ark-express'
  | 'chinz-logistics'
  | 'citi-sprint'
  | 'citymail-pk'
  | 'consegnia'
  | 'elog-luxembourg'
  | 'fastgo'
  | 'fmx'
  | 'freightways-global'
  | 'hitch'
  | 'janco-express'
  | 'jcw-express'
  | 'm-xpress'
  | 'mark-express'
  | 'nege'
  | 'patuen'
  | 'promed-delivery'
  | 'shipx-asia'
  | 'spt-logistics'
  | 'sptexp'
  | 'superb-express'
  | 'tapuz-delivery'
  | 'thabit-logistics'
  | 'togo-post'
  | 'turkmenpost'
  | 'usasd'
  | 'v-xpress'
  | 'world-package'
  | 'xd-express'
  | 'xlmiles'
  | 'yoseus'
  | 'yuema-express'
  | 'tuodatong'
  | 'utec-logistics'
  | 'dex-i'
  | 'jy-express'
  | 'citilogistics'
  | 'comone-express'
  | 'arrow-express'
  | 'geswl'
  | 'dpx-logistics';

/** How one property is mixed out of the carrier's catalog color. */
export interface BrandDerivationStep {
  property: BrandProperty;
  base: string;
  amount: number;
}

/** `[x, y]` in the truck's own 32x21 coordinate space. */
export type TruckPoint = readonly [number, number];

/** A paint is either a hex literal or the name of one of the nine properties. */
export type TruckPaint = string;

export interface TruckBody {
  x: number;
  y: number;
  width: number;
  height: number;
  rx: number;
  fill: TruckPaint;
  stroke: TruckPaint;
}

/**
 * A filled and stroked outline, as the SVG draws it. `truck.json` spells every
 * outline a second time in straight lines (`points`, `segments`) for the
 * SwiftUI canvas; those reach the iPhone app in `Brand.json` and are left out
 * of the web's data.
 */
export interface TruckPanel {
  d: string;
  fill: TruckPaint;
  stroke: TruckPaint;
}

/** Same, without a stroke. */
export interface TruckGlass {
  d: string;
  fill: TruckPaint;
}

export interface TruckWheels {
  centers: readonly TruckPoint[];
  tire: { r: number; fill: TruckPaint };
  hub: { r: number; fill: TruckPaint };
}

/** Stroked segments. */
export interface TruckDecalLine {
  type: 'line';
  d: string;
  stroke: TruckPaint;
  strokeWidth: number;
}

export interface TruckDecalCircle {
  type: 'circle';
  cx: number;
  cy: number;
  r: number;
  fill: TruckPaint;
}

/** A filled outline. */
export interface TruckDecalPolygon {
  type: 'polygon';
  d: string;
  fill: TruckPaint;
}

export type TruckDecalShape = TruckDecalLine | TruckDecalCircle | TruckDecalPolygon;

export interface TruckGeometry {
  viewBox: { width: number; height: number };
  strokeWidth: number;
  body: TruckBody;
  cab: TruckPanel;
  windshield: TruckGlass;
  wheels: TruckWheels;
  decals: Record<CarrierDecal, readonly TruckDecalShape[]>;
}
