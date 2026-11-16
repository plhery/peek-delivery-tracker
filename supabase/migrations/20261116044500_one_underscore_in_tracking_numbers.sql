-- A tracking number may hold one underscore between letters and digits, as the scraper's number gate allows
-- (Obibox writes its parcel counter after one): the add, the one-off lookup, the support cases and the packages
-- constraint took letters and digits and NACEX's slash only, so such a number was refused when added.
-- create_owned_package builds on 20261116043000_register_carriers_spain_register, create_one_off_parcel on
-- 20261108113000_j_and_t_phone_digits and record_tracking_support_observation on
-- 20261114074500_generic_postal_waits_for_check_end, the last that defined them; only their number check changes.

alter table public.packages
  drop constraint if exists packages_tracking_number_format_check,
  add constraint packages_tracking_number_format_check check (
    tracking_number ~ '^([A-Z0-9]+(_[A-Z0-9]+)?|[0-9]{4}/[0-9]{8})$'
    and (tracking_number ~ '[0-9]' or tracking_number ~ '^[A-Z]{6,}$')
  ) not valid;

alter table public.tracking_support_cases
  drop constraint if exists tracking_support_cases_number_check,
  add constraint tracking_support_cases_number_check check (
    char_length(tracking_number) between 4 and 40
    and (tracking_number ~ '[0-9]' or tracking_number ~ '^[A-Z]{6,}$')
    and tracking_number ~ '^([A-Z0-9]+(_[A-Z0-9]+)?|[0-9]{4}/[0-9]{8})$'
  );

create or replace function public.create_owned_package(
  p_tracking_number text,
  p_label text default '',
  p_carrier text default 'unknown',
  p_tracking_url text default null,
  p_dpd_postcode text default null
)
returns public.packages
language plpgsql
security definer
set search_path = pg_catalog
as $$
declare
  actor_id uuid := auth.uid();
  normalized_tracking text;
  normalized_label text := btrim(coalesce(p_label, ''));
  normalized_url text := nullif(btrim(coalesce(p_tracking_url, '')), '');
  normalized_postcode text := nullif(btrim(coalesce(p_dpd_postcode, '')), '');
  active_count integer;
  total_count integer;
  created public.packages;
begin
  if actor_id is null or exists (
    select 1 from auth.users where id = actor_id and is_anonymous
  ) then
    raise exception 'A permanent account is required' using errcode = '42501';
  end if;

  normalized_tracking := upper(regexp_replace(coalesce(p_tracking_number, ''), '[[:space:].-]', '', 'g'));
  if char_length(normalized_tracking) not between 4 and 40
      or (normalized_tracking !~ '^[A-Z0-9]+(_[A-Z0-9]+)?$' and normalized_tracking !~ '^\d{4}/\d{8}$')
      or (normalized_tracking !~ '[0-9]' and normalized_tracking !~ '^[A-Z]{6,}$') then
    raise exception 'Invalid tracking number' using errcode = '22023';
  end if;
  if char_length(normalized_label) > 80 then
    raise exception 'Parcel names can be at most 80 characters' using errcode = '22023';
  end if;
  if p_carrier is null or p_carrier not in (
    'swiss-post', 'swiss-post-cargo', 'quickpac', 'planzer',
    'aliexpress', 'sunyou', 'hermes', 'spring-gds',
    'postlogistics', 'dachser', 'dhl', 'dhl-ecommerce',
    'ups', 'amazon-logistics', 'amazon-shipping', 'fedex',
    'gls-ch', 'dpd', 'dpd-fr', 'mondial-relay',
    'relais-colis', 'la-poste', 'chronopost', 'gls-fr',
    'colis-prive', 'geodis', 'colisweb', 'c-chez-vous',
    'heppner', 'ciblex', 'paack', 'asendia',
    'shipup', 'india-post', 'hermes-de', 'gls-de',
    'delivengo', 'an-post', 'aramex', 'australia-post',
    'austrian-post', 'blue-dart', 'bpost', 'bring-posten',
    'brt', 'canada-post', 'canpar', 'china-post',
    'correios-br', 'correos-chile', 'correos-express', 'correos-spain',
    'ctt', 'ctt-express', 'delhivery', 'dtdc',
    'ecoscooting', 'ems', 'estafeta', 'evri', 'four-px',
    'gofo', 'hongkong-post', 'inpost', 'j-and-t',
    'japan-post', 'jd-logistics', 'korea-post', 'landmark-global',
    'mrw', 'nacex', 'ninja-van', 'nz-post',
    'old-dominion', 'ontrac', 'packeta', 'parcelforce',
    'poczta-polska', 'pos-malaysia', 'poste-italiane', 'posti',
    'postnord', 'purolator', 'royal-mail', 'seur',
    'sf-express', 'singapore-post', 'spee-dee', 'speedx',
    'sto', 'thailand-post', 'the-courier-guy', 'tipsa',
    'tnt', 'ukrposhta', 'uniuni', 'usps',
    'yamato', 'yanwen', 'yto', 'yunda',
    'yunexpress', 'zto', 'omgo', 'dhl-express',
    'cne', 'dpd-de', 'dpd-uk', 'ekart', 'evri-uk', 'intelcom',
    'lbc-express', 'nova-poshta', 'sagawa', 'speedpak', 'spx-ph', 'xpressbees',
    'j-and-t-cargo',
    'dhl-ecommerce-es', 'dhl-ecommerce-nl', 'dhl-ecommerce-pl', 'dhl-ecommerce-uk',
    'dpd-pl', 'emile', 'gofo-fr', 'gofo-it',
    'abf-freight', 'acs-courier', 'allegro-one', 'apc-overnight', 'aras-kargo',
    'box-now', 'cdek', 'ceska-posta', 'chit-chats', 'correos-de-mexico',
    'cyprus-post', 'day-ross', 'dsv', 'dx', 'dynalogic', 'envialia',
    'epost-global', 'estes', 'fan-courier', 'gebrueder-weiss',
    'geniki-taxydromiki', 'gls-es', 'gls-it', 'jitsu', 'kuehne-nagel',
    'latvijas-pasts', 'loomis-express', 'lso', 'magyar-posta', 'meest',
    'metropolitan-warehouse',
    'citymail', 'correos-cuba', 'correos-el-salvador', 'delnext', 'hispapost',
    'intereuropa', 'iran-post', 'kronos-express', 'm-one', 'pack-man', 'pannon-xp',
    'phlpost', 'redur', 'torrestir',
    'afghan-post', 'corre', 'correos-nicaragua', 'dpd-pt', 'gls-pl', 'kyrgyz-post',
    'la-poste-burkina-faso', 'lao-post', 'pitney-bowes',
    'a1-post', 'direx', 'fermopoint', 'fg-delivery', 'go4', 'happy-post', 'hp-mostar',
    'hr-parcel', 'intex-paketdienst', 'liccardi', 'logoix', 'nox', 'peddler',
    'pigeon-express', 'posta-faroe', 'tusass', 'tws-express',
    'byhd', 'linhan',
    'dpd-at', 'eurodis', 'fedex-pl', 'gls-at', 'gls-nl', 'hermes-at',
    'drop2shop', 'neighbour-express', 'packli-punkt', 'parcelpoint',
    'yilong-logistics',
    'codexpress', 'designer-transport', 'direct-couriers', 'ewe', 'homexpress',
    'postmedia-parcel-services', 'precision-deliveries', 'transport-rdl',
    'energo', 'method', 'sailpost', 't-group',
    'best-overnite-express', 'estes-forwarding', 'globalpost', 'tforce-logistics',
    'united-delivery-service',
    'tamdis',
    'ajex', 'exelot', 'kwick-box', 'labaih', 'shipblu', 'starlinks',
    '247express', 'gtl-goto', 'paxel', 'pickupp', 'pickupp-sg', 'spx-sg',
    'ab-courier', 'apple-express', 'fleetoptics', 'lightning-express', 'straightship',
    'kyoungdong', 'sagawa-global', 'yc-delivery',
    'copa-courier', 'hound-express', 'pdq-chile', 'skyking', 'veloces',
    'bombino', 'icc-worldwide', 'maruti-air', 'noest', 'rivigo', 'shree-mahavir',
    'tccs', 'tej-couriers', 'valmo',
    'bjs-home-delivery', 'norsk-global', 'one-world-express', 'packs', 'transmission',
    'xlcourier',
    'cbl-logistica', 'connect-couriers', 'dragon-star-curier', 'fastdrop',
    'inter-courier', 'sp-express', 'tdn',
    'a-tu-hora-express', 'chronodiali', 'closer-logistics', 'cst-italia',
    'pro-carrier', 'teleship', 'transaher', 'urvaam',
    'gofo-nl', 'next-level-delivery', 'tmm-express',
    'j-and-t-middle-east',
    'hovership', 'pdn-express', 'x-trac',
    'big-smart', 'evdemo-lojistik', 'gta-airskip', 'nova-global', 'proships',
    'pts-worldwide-express', 'shipentegra',
    'bringer-parcel', 'domain-logistics', 'fleetex', 'micro-express', 'postplus',
    'jet-logistic', 'mongol-infinity-post', 'peqasus-az', 'starex-az',
    '4sides', 'dobropost', 'flippost', 'l-post', 'vestovoy',
    'c3x', 'eco-express-ae', 'logistiq', 'quicksilver-sa', 'skyex-ae',
    'team-express-ae', 'tfm-express', 'transcorp',
    'air-star-xpress', 'first-fly-express', 'flyking', 'goodluck-courier',
    'grand-slam-express', 'jetline-couriers', 'pavan-courier', 'sahara-express',
    'shree-mahabali-express', 'speed-and-safe',
    'atlantic-courier', 'daily-xpress', 'dnx-cargo', 'gms-worldwide', 'icl-express',
    'ocs-ana', 'pushpak-courier', 'united-worldwide-couriers',
    'airpak-express', 'best-express-my', 'houng-ah-loun', 'indah-logistik',
    'makati-express', 'netpost-vn', 'pandu-logistics', 'pcp-express',
    'pcs-logistics-vn', 'royal-express-mm', 'rr-express-sg', 'tp-logistics-th',
    'aci-express-kr', 'eparcel-kr', 'exmate', 'gsi-express', 'last-one-mile-coop',
    'today-kr', 'winion-logis',
    'dpex', 'fliway', 'morning-global', 'mrl-global',
    'montenegro-post', 'rpx-hk', 'vitran-express',
    'am-home-delivery', 'american-west-express', 'ccfs', 'custom-companies',
    'hercules-freight',
    'gaash', 'orian',
    'fedex-freight',
    'ait-worldwide', 'qwintry', 'skypostal', 'team-worldwide',
    'dpd-ro', 'huahan',
    'anserx', 'chukou1', 'jumppoint', 'ycs-logistics',
    'j-and-t-au',
    'lingdong',
    'jcex',
    'shiprocket',
    'first-line',
    'j-and-t-de',
    'kun-express',
    'juxi-express',
    'techlink',
    'z-leading',
    'correos-costa-rica',
    'fercam',
    'gig-logistics',
    'inposdom',
    'sendle',
    'trunkrs',
    'zto-international',
    'dayone',
    'shouwu',
    'meihoo-pegasus',
    'yypost',
    'ywe',
    'far-international',
    'castle-parcels',
    'now-couriers',
    'intel-valley',
    'stone3pl',
    'maersk-ecommerce',
    'dpd-hu',
    'fedex-international-connect',
    'pengyou',
    'tusk-logistics',
    'pin-ag',
    'gofo-ca',
    'topyou',
    'yfhex',
    'eushipments',
    'timely-titan',
    'no-limit',
    'starlinks-global',
    'mhi',
    'e-commerce-international',
    'boxtal',
    'huaya-express',
    'quick-rabbit',
    'dealersend',
    'pactic',
    'express-one-bg',
    'gofo-es',
    'j-and-t-uk',
    'logisimple',
    'viadireta',
    'meso-global',
    'jifan',
    'youbang-express',
    'fastbox',
    'ydh',
    'takesend',
    'flyt-express',
    'joom-logistics',
    'veho',
    'hfd',
    'cheetah-delivery',
    'parcel-one',
    'wedo',
    'post-one',
    'polonez',
    'yamato-america',
    'tas-courier',
    'famafutar',
    'first-flight-me',
    'dd-express',
    'boyuan',
    'emex',
    'interlogistica',
    'intime-bg',
    'asigna',
    'pflogistics',
    'costco-logistics',
    'quality-post',
    'rxo',
    'runner-express',
    'jp-home',
    'cargoline',
    'overnite',
    'ambro-express',
    'ait-home-delivery',
    'city-air-express',
    'ameriship',
    'gio-express',
    'sway',
    '3pe-express',
    'xinghe-freight',
    'instapack',
    'obibox',
    'avis-logistics',
    'dakota-cargo',
    'sentral-cargo',
    'depo',
    'e-cargo',
    'sengi-express',
    'trans-ontario',
    'pallex',
    'airmee',
    'fofr',
    'akis-express',
    'collicare',
    'western-bid',
    'ace-lt',
    'ace-lv',
    'kuayue-express',
    'islandwide-express',
    'piggy-express',
    'rozetka-delivery',
    'hrx',
    'cargobus',
    'jetpak',
    'tonami',
    'okaken',
    'trexity',
    'savar-express',
    'xgs',
    'optima-overnight',
    'plycon',
    'peninsula-truck-lines',
    'ross-express',
    'centurion-transport',
    'airroad',
    'cope-sensitive-freight',
    'kiwi-express',
    'inter-express-logistics',
    'ats-healthcare',
    'whiz-delivery',
    'ets-express',
    'rincos',
    'apg-ecommerce',
    'toyou',
    'kec-ecommerce',
    'thelorry',
    'wizmo',
    'ams-egroup',
    'zes-express',
    'profit-fields',
    'alljoy',
    'dpe-express',
    'goodmaji',
    'lex-my',
    'dex-pk',
    'parxl',
    'efs-asia',
    'kart2door',
    'hart2hart-express',
    'top-logistics-au',
    'shipglobal-in',
    'faster-ae',
    'shipter',
    'linear-logistics',
    'tonda-global',
    'liuchen',
    'eac-cn',
    'lbtx',
    'deliverdirect',
    'ace-ee',
    'transnatur',
    'rhenus-it',
    'emons',
    'noerpel',
    'super-parcel-track',
    'ural-express',
    'onex',
    'gcx',
    'tusou',
    'wise-express',
    'rhenus-de',
    'skybox',
    'delivro',
    'tforce-integrated-solutions',
    'holisol',
    'ubx-uk',
    'ttk-europe',
    'inntralog',
    'dg-transporte',
    'bonds-couriers',
    'amalgamated-distribution',
    'victorian-freight-specialists',
    'vt-freight-express',
    'aland-post',
    'aruba-post',
    'gibraltar-post',
    'samoa-post',
    'burundi-post',
    'correo-paraguayo',
    'tcc-co',
    'fm-transportes',
    'xcargo',
    'paket-cl',
    'fez-delivery',
    'famiport',
    'ctc-express',
    'maple-logistics',
    'seino-super-express',
    'taqbin-hk',
    'safexpress',
    'movin',
    'lex-vn',
    'best-express-th',
    'airspeed',
    'j-and-t-eg',
    'egxpress',
    'tawssil',
    'ase-express',
    'zimpost',
    'nege',
    'spt-logistics',
    'world-package',
    'elog-luxembourg',
    'superb-express',
    'togo-post',
    'citi-sprint',
    'thabit-logistics',
    'tapuz-delivery',
    'turkmenpost',
    'hitch',
    'consegnia',
    'fmx',
    'm-xpress',
    'shipx-asia',
    'janco-express',
    'sptexp',
    'jcw-express',
    'patuen',
    'usasd',
    'yuema-express',
    'yoseus',
    'xlmiles',
    'promed-delivery',
    'freightways-global',
    'chinz-logistics',
    'ark-express',
    'xd-express',
    'fastgo',
    'v-xpress',
    'airwings-india',
    'mark-express',
    'apx-logistics',
    'citymail-pk',
    'tuodatong',
    'utec-logistics',
    'dex-i',
    'jy-express',
    'comone-express',
    'geswl',
    'dpx-logistics',
    'citilogistics',
    'arrow-express',
    'uvan',
    'fastwl',
    'tig-freight',
    'yha',
    'ecom-logistics',
    'fragilepak',
    'hkd-express',
    'canada-air-express',
    'bombax',
    'sm-express',
    'asiafly',
    'shunbang-express',
    'topex-logistics',
    '360lion',
    'cititrans',
    'syd',
    'we-world-express',
    'vnlin',
    'xingyunyi',
    'aquiline',
    'wiio',
    'ukp-worldwide',
    'btd',
    'xyl',
    'komon-express',
    'bel',
    'parcelabc',
    'pago',
    'safeway-express-cargo',
    'w-express-ph',
    'ecohai',
    'qls-quince',
    'hongsuda',
    'expedico',
    'pasarex',
    'parceljet',
    'letter-track',
    'frontier-ca',
    'e24metro-ca',
    'hecny-ecom',
    'jaskipin',
    'flashbird-ca',
    'lm-parcel',
    'emirates-courier-express',
    'viet-an-express',
    'espoirer',
    'performance-freight',
    'stg-ltl',
    'tsb-es',
    'senyu',
    'zippd',
    'global-delivery-network',
    'vip-posta',
    'sfyd-express',
    'bfe-express',
    'paisley-freight',
    'max-pakket',
    'diakinisis',
    'shipgce',
    'espeedpost',
    '360zebra',
    'auexpress',
    'ane-logistics',
    'china-railway-express',
    'lhtex',
    'showl',
    'elianpost',
    'bqc-logistics',
    'king-kong-express',
    'libang-logistics',
    'sumxpress',
    'suning-logistics',
    'rrs-logistics',
    'changjiang-express',
    'ees-logistics',
    'annto',
    'jdiex',
    'sxjd-freight',
    'cpszy',
    'valueway',
    'jiachen-express',
    'longcps',
    'bridge-intl',
    'jiufang',
    'cel-changda',
    'too-express',
    'go-world-express',
    'shenghang',
    'hzt',
    'wld-express',
    'wangjing-3cq',
    'banling',
    'jikuaida',
    'hye',
    'jet-global',
    'kuayuan-express',
    'cmt-express',
    '2ebox',
    'antaria',
    'bee-fast',
    'beexprss',
    'carry-flap',
    'choir-express',
    'ckeex',
    'corporate-couriers',
    'correios-angola',
    'correos-ecuador',
    'danniao',
    'ddu-express',
    'dtd-pk',
    'endeavour-delivery',
    'etomars',
    'ezship',
    'fare-rata',
    'fasttrack-ar',
    'fxdc-post',
    'global-business-tw',
    'honducor',
    'hy-express',
    'indopaket',
    'ipostel',
    'libya-post',
    'macau-post',
    'mikropakket',
    'mobi-logistica',
    'myanmar-post',
    'paositra-malagasy',
    'redbox-mv',
    'royale-international',
    'shippify',
    'suto-logistics',
    'swftbox',
    'tolos',
    'vatican-post',
    'viaxpress',
    'wepost',
    'xpressen',
    '66post',
    'ahl-courier',
    'bluestreak-couriers',
    'branding-worldwide',
    'czl-express',
    'dida-international',
    'eli-express',
    'explus-logistics',
    'flickpost',
    'go-rush',
    'jiehang',
    'junfeng-international',
    'ljx-express',
    'maxzeego',
    'ondot-courier',
    'penguin-box',
    'posta-e-kosoves',
    'rayspeed',
    'rex-indonesia',
    'ryder-last-mile',
    'shipglobal-us',
    'sjl-express',
    'sol-express',
    'tiui',
    'tpl-rider',
    'union-express',
    'uof-express',
    'yingtu',
    'cargo-expreso',
    'cupost',
    'rtt',
    'gofast',
    'hiyes',
    'htt-logistics',
    'nas-express',
    'odm-express',
    'online-worldwide-logistics',
    'ontask-express',
    '7hours-express',
    'a2b-express',
    'acex',
    'aics',
    'apg-global',
    'barq',
    'basic-shipping',
    'bluesky-parcel',
    'bolt-kargo',
    'btc-logistic',
    'conveyor-uni-express',
    'coupang-logistics',
    'der-kurier-sk',
    'emiro',
    'fimile',
    'flipxpress',
    'frontdoor-collective',
    'furniture-logis',
    'golilla',
    'logista-parcel',
    'lucky-fast-express',
    'megacity-courier',
    'mgl-express',
    'miller-deliveries',
    'mobel-services',
    'nk-logistic',
    'penta-express',
    'pxc-pacific',
    'servex',
    'skladusa',
    'tamex',
    'transfera',
    'u-envios',
    'us-cargo',
    'vert-chez-vous',
    'wassel',
    'widect',
    'xpress-courier-link',
    'zajel',
    'ecparcel',
    'go-logistics-au',
    'hey-couriers',
    'ahamove',
    'gle-colombia',
    'greenway-vn',
    'mensajeros-urbanos',
    'moova',
    'moovin',
    'ots-chile',
    'rodonaves',
    'taqbin-sg',
    'the-human-express',
    'uparcel',
    'welivery',
    'anc-delivers',
    'bestfulfill',
    'braspress',
    'c807-xpress',
    'cathedis',
    'cps-lastmile',
    'daiichi',
    'deliveright',
    'ecourier-uk',
    'jamef',
    'kerry-express-tw',
    'latam-global-express',
    'lex-id',
    'm24-logistics',
    'multientrega',
    'om-logistics',
    'peru-box-air',
    'premier-deliveries-uk',
    'product-care-group',
    'rangel',
    'route1-fulfilment',
    'seabourne-logistics',
    'sgk-home-delivery',
    'shalom-pe',
    'sorthub',
    'spfly',
    'stop-start',
    'svuum',
    'value-logistics',
    'xpect-same-day',
    'alil-post',
    'emu-express',
    'flecha-amarilla',
    'garantpost',
    'kargoturk',
    'korexgarant',
    'motorex',
    'porlor',
    'spark-kz',
    'tinypack',
    'dimex',
    'dnipro-llc',
    'express-ru',
    'topdelivery',
    'agt-kurye',
    'aqx-logistics',
    'domesa',
    'herona-express',
    'hub-group-final-mile',
    'jetizz',
    'mailex',
    'tnt-au',
    'jiaying-express',
    'ltl-kr',
    'okay-parcel',
    'znlogic',
    'magic-trans',
    'red-express',
    'qatar-post',
    'dpd-gr',
    'wp-ground',
    'global-parcel-service',
    'logistics-solution',
    'quick-fort',
    'flight-logistics',
    'peregrine-ship',
    'southwestern-motor-transport',
    'topnic',
    'magnum-transportation',
    'crane-worldwide',
    'rohlig',
    'jb-hunt',
    'dhx',
    'wooliiporter',
    'matson',
    'schneider',
    'alliance-air-freight',
    'mountain-movers',
    'fmd-distribution',
    'edi-express',
    'cargo-trans-logistik',
    'jas-worldwide',
    'tiptop',
    'jumppoint-international',
    'westweg',
    'bigpost',
    'coship',
    'apd-parcel',
    'bigfoot-express',
    'brink-transport',
    'campos-cadilhe',
    'carrybee',
    'express-freight-ni',
    'faster-shipe',
    'foz-post',
    'garudavega',
    'gruber-logistics',
    'huuneh',
    'intertown-transport',
    'king-solutions',
    'lafasta',
    'laser-logistics',
    'moran-transportation',
    'mte-xpress',
    'nova-kargo',
    'oxperta-express',
    'puntopost',
    'rika-express',
    'rocket-shipping',
    'south-post',
    'tma-companies',
    'total-transportation',
    'transportes-rapex',
    'velocity-express-in',
    'walker-logistics',
    'xpress-freight-management',
    'yuexi-logistics',
    'dongdu', 'zecheng', 'suhu', 'allied-express', 'skynet-my',
    'hub-delivery', 'national-delivery-systems', 'kuqi',
    'first-logistics', 'wmg-group', 'kyrgyz-express-post', 'j-and-t-co',
    'best-express-kh', 'best-express-sg', 'flash-express-la', 'yto-ph',
    'fastship-th', 'boxc', 'the-delivery-group', 'trumpcard', 'crossflight', 'runforint',
    'arco-spedizioni', 'doora-logistics', 'aeronet', 'brauns-express', 'meitetsu-unyu',
    'hlog-transportes', 'blitznow', 'borzo', 'zippee', 'fillo', 'kunzendorf',
    'mzz-briefdienst', 'post-modern', 'qwqer', 'shopini-world', 'international-bridge', 'wpx',
    'teleport', 'lexship', 'tci-freight', 'vichare', 'navata', 'makesend', 'f2-logistics',
    'dago-express', 'jlog', 'time-matters',
    'alphastline',
    'dpdex',
    'crc-post', 'integraa',
    'mixpakk', 'xls-futar', 'szami-group', 'gyujtoszallitas', 'csomagpiac', 'trans-pack', 'qt-express',
    'home-delivery-team',
    'optimus-courier', 'maty-express', 'transilvania-post', 'xpedist', 'irs-romexpress',
    'pink-post', 'time-critical-line', 'world-courier', 'posta-panduri', 'intelligent-logistik',
    'best-courier-ro', 'curiera', 'sinapseria', 'corlan-post-express', 'albatomi', 'cautcurier',
    '24curier',
    'act-argos',
    'aetos-courier',
    'aiolos-courier',
    'ant-courier',
    'asap-couriers',
    'bcs-courier',
    'biz-courier',
    'city-courier-gr',
    'cityxpress',
    'happy-courier-gr',
    'icc-courier-gr',
    'kanga-services',
    'mvp-courier',
    'prisma-courier',
    'rapid-courier-gr',
    'red-courier-gr',
    'direct-courier-gr',
    'west-postal',
    'xp-courier',
    'athinaiki',
    'dk-express-gr',
    'spourgitis',
    'skyex-gr',
    'rgw-express',
    'riders-express',
    'quriers',
    'pointpack',
    'olza-logistic',
    'jet-dp',
    'emole-express',
    'maraton',
    'crosswin',
    'gordom-logistika',
    'intime-hr',
    'webrinq',
    'jiayang',
    'msg-post',
    'u-post',
    'bh-posta',
    'transpress',
    'cvc',
    'gls-us',
    'poste-srpske',
    'trustexpress',
    'sxw-express',
    'xuchuan-logistics',
    'siuntos-autobusais', 'samus', 'zipasgo', 'dk-logistics-ee', 'jet-express-ee',
    'fan-courier-international',
    'jakeluyhtio-suomi', 'xpress-logistics-lu', 'bypost',
    'adex-al', 'express-one-me', 'cargo-express-mk', 'ntc-express', 'hopexpress', 'mr-courier-al',
    'intex-courier', 'tribex-courier', 'elite-post-al', 'dm-express-me', 'greenposta',
    'x-express-rs',
    'delivery-auto', 'fan-courier-md', 'post-international', 'ics-couriers-ua', 'globex-post',
    'unipost-express', 'sofex-post', 'elit-post', 'europoshta',
    'fudu-logistics', 'xyl-ships',
    'night-star-express', 'der-kurier', 'nordbrief', 'lvz-post',
    'swissconnect', 'citytrans', 'veloblitz', 'kurierzentrale',
    'yandex-delivery', 'magnit-post', 'baikal-service', 'skif-cargo', 'rail-continent', 'dalli-service', 'logsis', 'peshkariki',
    'begad', 'ghapex', 'cargomini', 'santos-e-vale', 'xinsu', 'ruiqi', 'brics56', 'extrans', 'yqs', 'yusuda',
    'memphis-courier',
    'travel-express', 'airtrans', 'easycourier', 'primewell', '4a-express',
    'miles-express', 'shiplowcost', 'courier-express-malta', 'oohpod',
    'afimex', 'dylo-express', 'enviopack-ar', 'enviopack-mx',
    'ocolchonespack', 'transgo-mx', 'transportes-condesa',
    'transports-besson', 'gls-ca', 'mebel-taxi', 'senda-insular',
    'ziegler', 'itc-delivery', 'tmg-jp', 'chic-express', 'ock-cn',
    'golucky', 'quick-couriers-pk',
    'xwwt', 'buho-logistics', 'duxiuexp', 'hartmann-international',
    'a2b-fi', 'enset', 'meyership', 'sandahls', 'trobud',
    'apollo-budbiler', 'itide', 'a3-transport', 'hurtig-gutta', 'lillebil',
    'quick-transport', 'smafrakt', 'kurer-as', 'bla-kurer', 'hss-transport',
    'kaukokiito', 'interfjord', 'freja', 'leman', 'veoy',
    'esbjerg-gods', 'tjw-fragt', 'premo', 'zufall', 'gf-logistikk',
    'pakettipiste', 'ramberg',
    'yongma-logis', 'slx-kr', 'kakao-t-same-day',
    'lx-pantos', 'doobalhero', 'kurly-nextmile',
    'post-anatolia', 'bir-gunde-kargo', 'cargox', 'pamukkale-kargo',
    'yol-kargo', 'etkin-dagitim', 'inter-global-kargo',
    'kaizen-express', 'paquexpress-urgente', 'altcamp-express', 'may-courier',
    'mng-kargo', 'nationex', 'post-luxembourg', 'posta-slovenije',
    'posta-srbije', 'ptt', 'redpack', 'rl-carriers', 'russian-post', 'saia',
    'sameday', 'speedy', 'trans-o-flex', 'ubi-smart-parcel', 'wanbexpress',
    'whistl', 'xdp', 'xpo-ltl', 'yurtici-kargo', 'zeleris',
    '99minutos', 'cargus', 'cdl', 'dao', 'dpd-ie', 'early-bird', 'econt',
    'elta', 'express-one', 'foxpost', 'geis', 'go-express', 'helthjem',
    'hrvatska-posta', 'instabox', 'lietuvos-pastas', 'maltapost', 'matkahuolto',
    'omniva', 'orlen-paczka', 'osm-worldwide', 'paquetexpress', 'passport',
    'ppl', 'slovak-parcel-service', 'slovenska-posta', 'stallion-express',
    'venipak', 'vinted-go', 'cacesa', 'dai-post', 'aramex-au', 'aramex-nz',
    '5post', 'a-duie-pyle', 'aaa-cooper', 'andreani', 'anjun', 'anteraja',
    'apc-postal-logistics', 'arrow-xl', 'averitt', 'azul-cargo',
    'barid-al-maghrib', 'belpost', 'best-transport', 'better-trucks',
    'bex-express', 'blue-express', 'bulgarian-posts', 'burd-delivery',
    'castores', 'central-transport', 'ceva-logistics', 'chilexpress',
    'chunghwa-post', 'citylink-express', 'citysprint', 'cj-logistics',
    'cj-packet', 'coordinadora', 'correo-argentino', 'courier-center',
    'couriersplease', 'cycloon', 'd-express', 'danske-fragtmaend',
    'dayton-freight', 'dellin', 'deppon', 'dhl-freight',
    'direct-freight-express', 'egypt-post', 'emirates-post', 'flash-express',
    'forward-air', 'fukuyama', 'gati', 'gdex', 'ghn', 'ghtk', 'hanjin',
    'hepsijet', 'hunter-express', 'ics-courier', 'ids-logistik', 'imile',
    'interrapidisimo', 'israel-post', 'jadlog', 'jersey-post', 'jne', 'kazpost',
    'kex', 'kolay-gelsin', 'logen', 'loggi', 'lotte-global', 'mainfreight',
    'maritime-ontario', 'naqel', 'nipost', 'nippon-express', 'nz-couriers',
    'oca', 'olva-courier', 'overseas-express', 'palletways', 'pecom',
    'pilot-freight', 'pitt-ohio', 'pos-indonesia', 'post-haste',
    'posta-moldovei', 'posta-north-macedonia', 'posta-romana',
    'posta-shqiptare', 'posturinn', 'professional-couriers', 'raben',
    'ram-hand-to-hand', 'roadrunner', 'saudi-post', 'seino', 'seko', 'sendex',
    'sending', 'serpost', 'servientrega', 'sfc', 'shadowfax', 'shree-maruti',
    'sicepat', 'skynet-worldwide', 'smsa-express', 'south-african-post-office',
    'southeastern-freight-lines', 'speedex', 'starken', 'surat-kargo',
    'team-global-express', 'tforce-freight', 'toptrans', 'total-express',
    'trackon', 'trendyol-express', 'vasp-expresso', 'viettel-post', 'vnpost',
    'ward-trucking',
    '17feia', 'celeritas', 'dhlink', 'geodis-pl', 'gzyl', 'hywl', 'jhgj', 'jrt',
    'ktd', 'swiftx', 'thhy', 'tsjy', 'mtscn', 'speedaf', 'mailamericas',
    '139express', '2go', 'akash-ganga', 'alem-tat', 'bangladesh-post', 'blueex',
    'buffalo-ex', 'chunil', 'domex', 'dropp', 'ecms-express', 'ecourier',
    'energia-tk', 'equick', 'fargo-courier', 'flash-express-ph', 'gs-postbox',
    'hapdong', 'hct-logistics', 'idexpress', 'ilyang-logis', 'internet-express',
    'j-and-t-cn', 'janio', 'jrs-express', 'kerry-tj', 'kex-express-hk', 'kit-tk',
    'kunyoung', 'leopards-courier', 'line-clear', 'lion-parcel', 'm-and-p',
    'madhur-courier', 'mds-collivery', 'nepal-post', 'pakistan-post', 'paperfly',
    'pathao-courier', 'paxi', 'pelican-express', 'pfc-express', 'postex',
    'prompt-xpress', 'pronto-lanka', 'quantium-solutions', 'redx', 'sapx-express',
    'sat-ukraine', 'sbs-sokuhai', 'seven-eleven-tw', 'shree-nandan', 'slgtrax',
    'spx-br', 'sri-lanka-post', 'st-courier', 'steadfast', 'sundarban-courier',
    't-cat', 'taxydema', 'tcs-pakistan', 'tiki', 'tirupati-courier', 'tracx-logis',
    'wahana', 'winit', 'xde-logistics', 'yalidine', 'zheldor', 'zr-express',
    'asyad-express', 'border-express', 'bosta', 'correo-uruguayo', 'courier-it',
    'daylight-transport', 'deprisa', 'envia-colombia', 'europochta',
    'midland-transport', 'oak-harbor', 'skroutz-last-mile', 'tci-express', 'urbano',
    'vrl-logistics',
    'aeropost', 'aks-express', 'algerie-poste', 'aymakan', 'azerpost',
    'best-express-vn', 'city-express-rs', 'cse-russia', 'dohrn-transfer',
    'ems-vietnam', 'ethiopian-post', 'euroexpress-ba', 'followmont', 'ghana-post',
    'groupe-guilbault', 'haypost', 'hi-trans', 'jordan-post', 'la-poste-tunisienne',
    'libanpost', 'mauritius-post', 'mylerz', 'ncs-courier', 'nhat-tin', 'nim-express',
    'northline', 'ozon-logistics', 'pargo', 'pbt', 'polaris-transport', 'posta-kenya',
    'postaplus', 'quiqup', 'roadie', 'rpx-indonesia', 'saee', 'scg-express',
    'skynet-sa', 'spx-id', 'spx-my', 'spx-th', 'spx-tw', 'spx-vn', 'tanzania-posts',
    'tst-cf-express', 'uzbekistan-post', 'zajil',
    'tnt-it',
    '4-72', 'aex-paraguay', 'ampm', 'chazki', 'cruz-del-sur', 'forza-delivery',
    'guatex', 'j-and-t-br', 'j-and-t-mx', 'jamaica-post', 'laar-courier',
    'pullman-cargo', 'tealca', 'tufesa', 'via-cargo', 'yimidida', 'zoom-ve',
    'mayiyinfu',
    'cttyex',
    'ameex', 'amerijet', 'anousith', 'ap-cargo', 'bookurier', 'botswana-post',
    'bts-uz', 'budbee', 'bussgods', 'campost', 'correos-bolivia', 'courier-plus-ng',
    'credifin', 'daewoo-fastex', 'dpd-za', 'easy-mail', 'evropat', 'exline-kz',
    'fargo-uz', 'fastfrate', 'first-delivery-tn', 'franch-express', 'gel-express',
    'gobolt', 'gogo-xpress', 'horoz', 'integral-pack', 'jeebly', 'kargoist',
    'la-poste-ci', 'la-poste-senegal', 'mailplus-au', 'major-express', 'mandae',
    'messenger-cz', 'nord-wheel', 'ocasa', 'opt-nc', 'pmm', 'pony-express', 'shipa',
    'shree-anjani', 'ues-uy', 'upaya', 'vimenpaq', 'vozovoz', 'x-express-ba',
    'packlink', 'dpd-be', 'relaytech', 'js-express', 'dpd-nl',
    'intl-post', 'unknown'
  ) then
    raise exception 'Unsupported carrier' using errcode = '22023';
  end if;
  if p_carrier = 'dachser' and normalized_url is null then
    raise exception 'Dachser requires its complete tracking URL' using errcode = '22023';
  end if;
  if normalized_url is not null and not (
    (
      p_carrier = 'planzer'
      and char_length(normalized_url) <= 4096
      and normalized_url ~ '^https://trackandtrace[.]planzergroup[.]com(?::443)?/shared/sendungen/'
    )
    or (
      p_carrier = 'instabox'
      and char_length(normalized_url) <= 4096
      and normalized_url ~ ('^https://track[.]instabox[.]com(?::443)?/[A-Za-z]{2}(-[A-Za-z]{2})?/' || normalized_tracking
        || '(/locker-to-locker-recipient)?/?[?](authCode|c)=[A-Za-z0-9_-]{16,160}$')
    )
    or (
      p_carrier in ('nz-couriers', 'post-haste', 'castle-parcels', 'now-couriers')
      and char_length(normalized_url) <= 4096
      -- The network's domain is its id without the hyphen (nzcouriers.co.nz).
      and normalized_url ~ ('^https://receiver[.]' || replace(p_carrier, '-', '') || '[.]co[.]nz/[?]('
        || 'consignmentid=CU[.][.]' || normalized_tracking || '[.][A-Z0-9][.][A-Z0-9]{3,16}[.][0-9]{1,3}'
        || '|request_id=3&customer_number=[A-Z0-9]{3,16}&consignment_id=' || normalized_tracking || ')$')
    )
    or (
      p_carrier = 'dachser'
      and char_length(normalized_url) <= 4096
      and normalized_url ~ '^https://customeriberia[.]dachser[.]com(?::443)?/customerarea/utilidades/seguimiento-publico/detalle[?]'
      and normalized_url ~ ('[?&]numeroUnico=' || normalized_tracking || '([&#]|$)')
      and (
        normalized_url ~ '[?&]hash=[A-Za-z0-9_-]{4,255}[A-Za-z0-9_-]?([&#]|$)'
        or (
          normalized_url ~ '[?&]clave=[A-Za-z0-9_-]{4,255}[A-Za-z0-9_-]?([&#]|$)'
          and normalized_url ~ '[?&]fecha=[0-9]{8}([&#]|$)'
        )
      )
    )
  ) then
    raise exception 'Invalid tracking URL' using errcode = '22023';
  end if;

  if p_carrier = 'paack' and normalized_postcode is not null then
    normalized_postcode := upper(normalized_postcode);
    if char_length(normalized_postcode) not between 3 and 10
        or normalized_postcode !~ '[0-9]'
        or normalized_postcode !~ '^[A-Z0-9]+([ -][A-Z0-9]+)*$' then
      raise exception 'Invalid Paack delivery postcode' using errcode = '22023';
    end if;
    normalized_postcode := upper(regexp_replace(normalized_postcode, '[[:space:]]', '', 'g'));
  end if;
  if (p_carrier in ('dpd', 'gls-de', 'gls-es', 'mondial-relay', 'xdp', 'gebrueder-weiss', 'asendia', 'gls-pl', 'relaytech', 'dx', 'arrow-xl', 'gls-at', 'gls-nl', 'ids-logistik', 'packs', 'transmission', 'postmedia-parcel-services') or (p_carrier = 'dhl' and normalized_tracking ~ '^[0-9]{12}$')
      or (p_carrier = 'spring-gds' and (normalized_tracking ~ '^3S[A-Z]{1,4}[0-9]+$' and char_length(normalized_tracking) in (13, 15) or normalized_tracking ~ '^3S[A-Z]{4}[0-9]{8}$'))
      or (p_carrier = 'a-tu-hora-express' and normalized_tracking ~ '^([0-9]{10}|[0-9]{16})$')
      or (p_carrier = 'canpar' and normalized_tracking ~ '^[CDKLSUXZ][0-9]{8,20}$'))
      and normalized_postcode is not null then
    normalized_postcode := upper(regexp_replace(normalized_postcode, '[[:space:]]+', ' ', 'g'));
  end if;
  if p_carrier = 'colis-prive' and normalized_postcode is not null then
    -- Capitals without spaces, a written country prefix (B-1000, LU 1234) folded to its letter.
    normalized_postcode := upper(regexp_replace(normalized_postcode, '[[:space:]]', '', 'g'));
    normalized_postcode := regexp_replace(normalized_postcode, '^(B)E?-?([1-9][0-9]{3})$', '\1\2');
    normalized_postcode := regexp_replace(normalized_postcode, '^(L)U?-?([1-9][0-9]{3})$', '\1\2');
  end if;
  if (p_carrier = 'gls-ch' and coalesce(normalized_postcode, '') !~ '^[0-9]{4}$')
      or (
        (p_carrier in ('dpd', 'gls-de', 'gls-es', 'mondial-relay', 'xdp', 'gebrueder-weiss', 'asendia', 'gls-pl', 'relaytech', 'dx', 'arrow-xl', 'gls-at', 'gls-nl', 'ids-logistik', 'packs', 'transmission', 'postmedia-parcel-services') or (p_carrier = 'dhl' and normalized_tracking ~ '^[0-9]{12}$')
          or (p_carrier = 'spring-gds' and (normalized_tracking ~ '^3S[A-Z]{1,4}[0-9]+$' and char_length(normalized_tracking) in (13, 15) or normalized_tracking ~ '^3S[A-Z]{4}[0-9]{8}$'))
          or (p_carrier = 'a-tu-hora-express' and normalized_tracking ~ '^([0-9]{10}|[0-9]{16})$')
          or (p_carrier = 'canpar' and normalized_tracking ~ '^[CDKLSUXZ][0-9]{8,20}$'))
        and normalized_postcode is not null
        and (
          char_length(normalized_postcode) not between 3 and 12
          or normalized_postcode !~ '[0-9]'
          or normalized_postcode !~ '^[A-Z0-9]+([ -][A-Z0-9]+)*$'
        )
      )
      or (p_carrier = 'dpd-de' and normalized_postcode is not null and normalized_postcode !~ '^[0-9]{5}$')
      or (p_carrier = 'mondial-relay' and normalized_postcode is null and (
        normalized_tracking ~ '^[0-9]{8}$'
        or (normalized_tracking ~ '^[0-9]{26}$' and not public.is_valid_mondial_relay_barcode(normalized_tracking))
      ))
      or (p_carrier in ('gls-de', 'gls-es', 'ids-logistik', 'packs', 'transmission', 'postmedia-parcel-services') and normalized_postcode is null)
      or (p_carrier = 'heppner' and coalesce(normalized_postcode, '') !~ '^[0-9]{4,5}$')
      or (
        p_carrier = 'paack'
        and normalized_postcode is not null
        and (
          char_length(normalized_postcode) not between 3 and 10
          or normalized_postcode !~ '[0-9]'
          or normalized_postcode !~ '^[A-Z0-9]+(-[A-Z0-9]+)*$'
        )
      )
      or (
        p_carrier = 'colis-prive'
        and case when normalized_tracking ~ '^[A-Z0-9]{12}$'
          then coalesce(normalized_postcode, '') !~ '^((0[1-9]|[1-8][0-9]|9[0-578])[0-9]{3}|[BL]?[1-9][0-9]{3})$'
          else normalized_postcode is not null end
      )
      or (p_carrier = 'dhl' and normalized_tracking !~ '^[0-9]{12}$' and normalized_postcode is not null)
      or (p_carrier = 'spring-gds' and not ((normalized_tracking ~ '^3S[A-Z]{1,4}[0-9]+$' and char_length(normalized_tracking) in (13, 15) or normalized_tracking ~ '^3S[A-Z]{4}[0-9]{8}$')) and normalized_postcode is not null)
      or (p_carrier = 'a-tu-hora-express' and normalized_tracking !~ '^([0-9]{10}|[0-9]{16})$' and normalized_postcode is not null)
      or (p_carrier = 'canpar' and normalized_tracking !~ '^[CDKLSUXZ][0-9]{8,20}$' and normalized_postcode is not null)
      or (
        p_carrier not in ('dpd', 'dpd-de', 'gls-ch', 'gls-de', 'gls-es', 'mondial-relay', 'heppner', 'paack', 'colis-prive', 'xdp', 'gebrueder-weiss', 'asendia', 'gls-pl', 'relaytech', 'dx', 'arrow-xl', 'gls-at', 'gls-nl', 'ids-logistik', 'packs', 'transmission', 'postmedia-parcel-services', 'dhl', 'spring-gds', 'a-tu-hora-express', 'canpar')
        and normalized_postcode is not null
      ) then
    raise exception 'Invalid delivery postcode' using errcode = '22023';
  end if;

  perform pg_advisory_xact_lock(hashtextextended(actor_id::text, 0));
  select
    count(*) filter (where archived_at is null),
    count(*)
  into active_count, total_count
  from public.packages
  where user_id = actor_id;
  if active_count >= 50 or total_count >= 500 then
    raise exception 'Parcel limit reached' using errcode = 'P0001';
  end if;

  insert into public.packages (
    user_id, tracking_number, label, carrier, tracking_url, dpd_postcode
  ) values (
    actor_id,
    normalized_tracking,
    normalized_label,
    p_carrier,
    normalized_url,
    normalized_postcode
  )
  returning * into created;
  return created;
end;
$$;

create or replace function public.create_one_off_parcel(
  p_tracking_number text,
  p_carrier text,
  p_tracking_url text,
  p_dpd_postcode text,
  p_owner_key_hash text,
  p_phone_digits text default null
)
returns jsonb
language plpgsql
security definer
set search_path = ''
as $$
declare
  normalized_tracking text := upper(regexp_replace(coalesce(p_tracking_number, ''), '[[:space:].-]', '', 'g'));
  normalized_url text := nullif(btrim(coalesce(p_tracking_url, '')), '');
  normalized_postcode text := nullif(btrim(coalesce(p_dpd_postcode, '')), '');
  normalized_digits text := nullif(btrim(coalesce(p_phone_digits, '')), '');
  target_id uuid;
  link_id text;
  created boolean := false;
begin
  if char_length(normalized_tracking) not between 4 and 40
      or (normalized_tracking !~ '^[A-Z0-9]+(_[A-Z0-9]+)?$' and normalized_tracking !~ '^\d{4}/\d{8}$')
      or (normalized_tracking !~ '[0-9]' and normalized_tracking !~ '^[A-Z]{6,}$')
      or p_carrier is null
      or p_owner_key_hash is null then
    raise exception 'Invalid one-off parcel' using errcode = '22023';
  end if;

  perform private.lock_one_off_number(normalized_tracking);
  select package.id into target_id
  from public.packages as package
  where package.one_off
    and package.user_id is null
    and package.tracking_number = normalized_tracking
    and (package.carrier = p_carrier or package.carrier_data->>'auto_changed_from' = p_carrier)
    and package.tracking_url is not distinct from normalized_url
    and package.dpd_postcode is not distinct from normalized_postcode
    and package.phone_digits is not distinct from normalized_digits
  order by package.created_at desc
  limit 1;

  if target_id is null then
    insert into public.packages (user_id, one_off, tracking_number, label, carrier, tracking_url, dpd_postcode, phone_digits)
    values (null, true, normalized_tracking, '', p_carrier, normalized_url, normalized_postcode, normalized_digits)
    returning id into target_id;
    created := true;
  end if;

  insert into public.parcel_links (package_id, owner_key_hash)
  values (target_id, p_owner_key_hash)
  returning id into link_id;

  return public.public_parcel(link_id, p_owner_key_hash) || jsonb_build_object('created', created);
end;
$$;

create or replace function public.record_tracking_support_observation(
  p_tracking_number text,
  p_context jsonb,
  p_evidence jsonb,
  p_observed_at timestamptz,
  p_observation_key text
)
returns uuid language plpgsql security definer set search_path = pg_catalog as $$
declare
  number text := upper(regexp_replace(p_tracking_number, '[[:space:].-]', '', 'g'));
  context jsonb := coalesce(p_context, '{}');
  evidence jsonb := coalesce(p_evidence, '{}');
  reason_tags text[];
  candidate_ids text[];
  support_case public.tracking_support_cases;
  observation public.tracking_support_observations;
  inserted_count integer;
  completed boolean;
  provider text;
  source text;
  direct_progress boolean;
  settled boolean;
begin
  if number is null or length(number) not between 4 and 40 or (number !~ '[0-9]' and number !~ '^[A-Z]{6,}$')
      or number !~ '^([A-Z0-9]+(_[A-Z0-9]+)?|[0-9]{4}/[0-9]{8})$'
      or p_observed_at is null or not isfinite(p_observed_at)
      or p_observation_key is null or length(p_observation_key) not between 1 and 160
      or jsonb_typeof(context) <> 'object' or octet_length(context::text) > 4096
      or jsonb_typeof(evidence) <> 'object' or octet_length(evidence::text) > 4096 then
    raise exception 'Invalid tracking support observation' using errcode = '22023';
  end if;
  if (context ? 'reasons' and jsonb_typeof(context->'reasons') <> 'array')
      or (context ? 'detection_candidates' and jsonb_typeof(context->'detection_candidates') <> 'array') then
    raise exception 'Invalid tracking support context' using errcode = '22023';
  end if;
  reason_tags := array(
    select distinct tag from (
      select jsonb_array_elements_text(coalesce(context->'reasons', '[]')) as tag
      union all select nullif(evidence->>'support_gap_reason', '')
    ) as supplied where tag is not null order by tag
  );
  candidate_ids := array(select candidate from jsonb_array_elements_text(coalesce(context->'detection_candidates', '[]'))
    with ordinality as listed(candidate, position) group by candidate order by min(position));
  if cardinality(reason_tags) > 16 or exists(select 1 from unnest(reason_tags) as tag where tag !~ '^[a-z][a-z0-9_]{0,63}$')
      or cardinality(candidate_ids) > 64 or exists(select 1 from unnest(candidate_ids) as id where length(id) not between 1 and 100) then
    raise exception 'Invalid tracking support tags' using errcode = '22023';
  end if;
  completed := nullif(evidence->>'outcome', '') is not null and evidence->>'outcome' <> 'running';
  if completed and evidence->>'outcome' in ('superseded', 'abandoned', 'interrupted') then return null; end if;
  select * into support_case from public.tracking_support_cases where tracking_number = number for update;
  if not found then
    if cardinality(reason_tags) = 0
        or (evidence->>'support_gap_undecided' = 'true' and reason_tags = array[evidence->>'support_gap_reason']) then return null; end if;
    insert into public.tracking_support_cases(tracking_number, first_seen, last_seen, held_until)
    values(number, p_observed_at, p_observed_at,
      case when context->>'browser_pending' = 'true' then p_observed_at + interval '1 hour' end)
    on conflict(tracking_number) do nothing;
    select * into strict support_case from public.tracking_support_cases where tracking_number = number for update;
  end if;
  insert into public.tracking_support_observations(observation_key, case_id, observed_at)
  values(p_observation_key, support_case.id, p_observed_at) on conflict(observation_key) do nothing;
  get diagnostics inserted_count = row_count;
  select * into strict observation from public.tracking_support_observations where observation_key = p_observation_key for update;
  if observation.case_id <> support_case.id then
    raise exception 'Tracking support observation key reused' using errcode = '22023';
  end if;
  if observation.completed then return support_case.id; end if;
  provider := nullif(left(evidence->>'support_provider', 100), '');
  source := nullif(left(evidence->>'source_carrier', 100), '');
  direct_progress := completed and evidence->>'outcome' in ('updated', 'unchanged')
    and evidence->>'support_direct_progress' = 'true' and provider is null
    and source is not null and source not in ('unknown', 'intl-post')
    and upper(regexp_replace(evidence->>'support_lookup_number', '[[:space:].-]', '', 'g')) = number;
  settled := direct_progress and evidence->>'support_settled' = 'true'
    and support_case.fix_status = 'open' and p_observed_at <= support_case.held_until;
  update public.tracking_support_cases set
    first_seen = least(first_seen, p_observed_at),
    last_seen = greatest(last_seen, p_observed_at),
    configured_carrier = case when p_observed_at >= last_seen and context ? 'configured_carrier' then nullif(left(context->>'configured_carrier', 100), '') else configured_carrier end,
    detection_carrier = case when p_observed_at >= last_seen and context ? 'detection_carrier' then nullif(left(context->>'detection_carrier', 100), '') else detection_carrier end,
    detection_confidence = case when p_observed_at >= last_seen and context ? 'detection_confidence' then nullif(context->>'detection_confidence', '') else detection_confidence end,
    detection_candidates = case when p_observed_at >= last_seen and context ? 'detection_candidates' then candidate_ids else detection_candidates end,
    app_version = case when p_observed_at >= last_seen and context ? 'app_version' then nullif(left(context->>'app_version', 100), '') else app_version end,
    reasons = array(select distinct tag from unnest(case
      when p_observation_key like 'attempt:%' and context ? 'reasons' and p_observed_at >= last_seen
        then array(select tag from unnest(reasons) as tag where tag <> all(array['unknown_shape', 'no_direct_adapter',
          'carrier_mismatch']) and (tag <> all(array['ambiguous_shape', 'generic_postal']) or not completed)) || reason_tags
      else reasons || reason_tags end) as tag order by tag),
    seen_count = seen_count + inserted_count,
    fallback_count = fallback_count + case when completed and provider is not null then 1 else 0 end,
    last_provider = case when completed and p_observed_at >= last_seen then provider else last_provider end,
    last_source_carrier = case when completed and p_observed_at >= last_seen then source else last_source_carrier end,
    last_outcome = case when completed and p_observed_at >= last_seen then nullif(left(evidence->>'outcome', 100), '') else last_outcome end,
    last_error_type = case when completed and p_observed_at >= last_seen then nullif(left(evidence->>'error_type', 100), '') else last_error_type end,
    direct_verified_at = case when direct_progress then greatest(direct_verified_at, p_observed_at) else direct_verified_at end,
    direct_verified_carrier = case when direct_progress and (direct_verified_at is null or p_observed_at >= direct_verified_at) then source else direct_verified_carrier end,
    fix_status = case when direct_progress and fix_status = 'fixed' and p_observed_at >= fixed_at then 'verified'
      when settled then 'ignored' else fix_status end,
    notes = case when settled then format('%s, which recognition asks only through a browser, if at all, tracked the number '
      'within the hour it was held for: no gap left.', source) else notes end
  where id = support_case.id;
  if completed then
    update public.tracking_support_observations set completed = true where observation_key = p_observation_key;
  end if;
  return support_case.id;
end;
$$;

notify pgrst, 'reload schema';

insert into public.applied_migrations (name) values ('20261116044500_one_underscore_in_tracking_numbers') on conflict do nothing;
