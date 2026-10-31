-- Accept Tuodatong, a carrier tracked through universal providers.
-- The constraint and the functions are built on
-- 20261031010000_spt_express_consegnia_hitch_and_thirty_one_more_carriers, the last that defined them.
-- Database gate: carriers added tuodatong

alter table public.packages
  drop constraint if exists packages_carrier_check,
  add constraint packages_carrier_check
    check (
      carrier in (
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
    'dpd-at', 'eurodis', 'fedex-pl', 'gls-at', 'hermes-at',
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
      )
    ) not valid;

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
      or (normalized_tracking !~ '^[A-Z0-9]+$' and normalized_tracking !~ '^\d{4}/\d{8}$')
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
    'dpd-at', 'eurodis', 'fedex-pl', 'gls-at', 'hermes-at',
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
  if (p_carrier in ('dpd', 'gls-de', 'gls-es', 'mondial-relay', 'xdp', 'gebrueder-weiss', 'asendia', 'gls-pl', 'relaytech', 'dx', 'arrow-xl', 'gls-at', 'ids-logistik', 'packs', 'transmission', 'postmedia-parcel-services') or (p_carrier = 'dhl' and normalized_tracking ~ '^[0-9]{12}$')
      or (p_carrier = 'spring-gds' and normalized_tracking ~ '^3S[A-Z]{1,4}[0-9]+$' and char_length(normalized_tracking) in (13, 15))
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
        (p_carrier in ('dpd', 'gls-de', 'gls-es', 'mondial-relay', 'xdp', 'gebrueder-weiss', 'asendia', 'gls-pl', 'relaytech', 'dx', 'arrow-xl', 'gls-at', 'ids-logistik', 'packs', 'transmission', 'postmedia-parcel-services') or (p_carrier = 'dhl' and normalized_tracking ~ '^[0-9]{12}$')
          or (p_carrier = 'spring-gds' and normalized_tracking ~ '^3S[A-Z]{1,4}[0-9]+$' and char_length(normalized_tracking) in (13, 15))
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
      or (p_carrier = 'spring-gds' and not (normalized_tracking ~ '^3S[A-Z]{1,4}[0-9]+$' and char_length(normalized_tracking) in (13, 15)) and normalized_postcode is not null)
      or (p_carrier = 'a-tu-hora-express' and normalized_tracking !~ '^([0-9]{10}|[0-9]{16})$' and normalized_postcode is not null)
      or (p_carrier = 'canpar' and normalized_tracking !~ '^[CDKLSUXZ][0-9]{8,20}$' and normalized_postcode is not null)
      or (
        p_carrier not in ('dpd', 'dpd-de', 'gls-ch', 'gls-de', 'gls-es', 'mondial-relay', 'heppner', 'paack', 'colis-prive', 'xdp', 'gebrueder-weiss', 'asendia', 'gls-pl', 'relaytech', 'dx', 'arrow-xl', 'gls-at', 'ids-logistik', 'packs', 'transmission', 'postmedia-parcel-services', 'dhl', 'spring-gds', 'a-tu-hora-express', 'canpar')
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

create or replace function public.change_owned_package_carrier(
  p_package_id uuid,
  p_carrier text,
  p_tracking_url text default null,
  p_dpd_postcode text default null
)
returns boolean
language plpgsql
security definer
set search_path = pg_catalog
as $$
declare
  actor_id uuid := auth.uid();
  tracking_number text;
  normalized_carrier text := p_carrier;
  normalized_url text := nullif(btrim(coalesce(p_tracking_url, '')), '');
  normalized_postcode text := nullif(btrim(coalesce(p_dpd_postcode, '')), '');
begin
  if actor_id is null then
    raise exception 'Authentication is required' using errcode = '42501';
  end if;

  select package.tracking_number
  into tracking_number
  from public.packages as package
  where package.id = p_package_id
    and package.user_id = actor_id
  for update;

  if not found then return false; end if;

  if tracking_number ~ '^44[0-9]{16}$' then normalized_carrier := 'quickpac'; end if;
  if normalized_carrier is null or normalized_carrier not in (
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
    'dpd-at', 'eurodis', 'fedex-pl', 'gls-at', 'hermes-at',
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

  if normalized_carrier = 'dachser' and normalized_url is null then
    raise exception 'Dachser requires its complete tracking URL' using errcode = '22023';
  end if;
  if normalized_url is not null and not (
    (
      normalized_carrier = 'planzer'
      and char_length(normalized_url) <= 4096
      and normalized_url ~ '^https://trackandtrace[.]planzergroup[.]com(?::443)?/shared/sendungen/'
    )
    or (
      normalized_carrier = 'instabox'
      and char_length(normalized_url) <= 4096
      and normalized_url ~ ('^https://track[.]instabox[.]com(?::443)?/[A-Za-z]{2}(-[A-Za-z]{2})?/' || tracking_number
        || '(/locker-to-locker-recipient)?/?[?](authCode|c)=[A-Za-z0-9_-]{16,160}$')
    )
    or (
      normalized_carrier in ('nz-couriers', 'post-haste', 'castle-parcels', 'now-couriers')
      and char_length(normalized_url) <= 4096
      -- The network's domain is its id without the hyphen (nzcouriers.co.nz).
      and normalized_url ~ ('^https://receiver[.]' || replace(normalized_carrier, '-', '') || '[.]co[.]nz/[?]('
        || 'consignmentid=CU[.][.]' || tracking_number || '[.][A-Z0-9][.][A-Z0-9]{3,16}[.][0-9]{1,3}'
        || '|request_id=3&customer_number=[A-Z0-9]{3,16}&consignment_id=' || tracking_number || ')$')
    )
    or (
      normalized_carrier = 'dachser'
      and char_length(normalized_url) <= 4096
      and normalized_url ~ '^https://customeriberia[.]dachser[.]com(?::443)?/customerarea/utilidades/seguimiento-publico/detalle[?]'
      and normalized_url ~ ('[?&]numeroUnico=' || tracking_number || '([&#]|$)')
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

  if normalized_carrier = 'paack' and normalized_postcode is not null then
    normalized_postcode := upper(normalized_postcode);
    if char_length(normalized_postcode) not between 3 and 10
        or normalized_postcode !~ '[0-9]'
        or normalized_postcode !~ '^[A-Z0-9]+([ -][A-Z0-9]+)*$' then
      raise exception 'Invalid Paack delivery postcode' using errcode = '22023';
    end if;
    normalized_postcode := upper(regexp_replace(normalized_postcode, '[[:space:]]', '', 'g'));
  end if;
  if (normalized_carrier in ('dpd', 'gls-de', 'gls-es', 'mondial-relay', 'xdp', 'gebrueder-weiss', 'asendia', 'gls-pl', 'relaytech', 'dx', 'arrow-xl', 'gls-at', 'ids-logistik', 'packs', 'transmission', 'postmedia-parcel-services') or (normalized_carrier = 'dhl' and tracking_number ~ '^[0-9]{12}$')
      or (normalized_carrier = 'spring-gds' and tracking_number ~ '^3S[A-Z]{1,4}[0-9]+$' and char_length(tracking_number) in (13, 15))
      or (normalized_carrier = 'a-tu-hora-express' and tracking_number ~ '^([0-9]{10}|[0-9]{16})$')
      or (normalized_carrier = 'canpar' and tracking_number ~ '^[CDKLSUXZ][0-9]{8,20}$'))
      and normalized_postcode is not null then
    normalized_postcode := upper(regexp_replace(normalized_postcode, '[[:space:]]+', ' ', 'g'));
  end if;
  if normalized_carrier = 'colis-prive' and normalized_postcode is not null then
    -- Capitals without spaces, a written country prefix (B-1000, LU 1234) folded to its letter.
    normalized_postcode := upper(regexp_replace(normalized_postcode, '[[:space:]]', '', 'g'));
    normalized_postcode := regexp_replace(normalized_postcode, '^(B)E?-?([1-9][0-9]{3})$', '\1\2');
    normalized_postcode := regexp_replace(normalized_postcode, '^(L)U?-?([1-9][0-9]{3})$', '\1\2');
  end if;
  if (normalized_carrier = 'gls-ch' and coalesce(normalized_postcode, '') !~ '^[0-9]{4}$')
      or (
        (normalized_carrier in ('dpd', 'gls-de', 'gls-es', 'mondial-relay', 'xdp', 'gebrueder-weiss', 'asendia', 'gls-pl', 'relaytech', 'dx', 'arrow-xl', 'gls-at', 'ids-logistik', 'packs', 'transmission', 'postmedia-parcel-services') or (normalized_carrier = 'dhl' and tracking_number ~ '^[0-9]{12}$')
          or (normalized_carrier = 'spring-gds' and tracking_number ~ '^3S[A-Z]{1,4}[0-9]+$' and char_length(tracking_number) in (13, 15))
          or (normalized_carrier = 'a-tu-hora-express' and tracking_number ~ '^([0-9]{10}|[0-9]{16})$')
          or (normalized_carrier = 'canpar' and tracking_number ~ '^[CDKLSUXZ][0-9]{8,20}$'))
        and normalized_postcode is not null
        and (
          char_length(normalized_postcode) not between 3 and 12
          or normalized_postcode !~ '[0-9]'
          or normalized_postcode !~ '^[A-Z0-9]+([ -][A-Z0-9]+)*$'
        )
      )
      or (normalized_carrier = 'dpd-de' and normalized_postcode is not null and normalized_postcode !~ '^[0-9]{5}$')
      or (normalized_carrier = 'mondial-relay' and normalized_postcode is null and (
        tracking_number ~ '^[0-9]{8}$'
        or (tracking_number ~ '^[0-9]{26}$' and not public.is_valid_mondial_relay_barcode(tracking_number))
      ))
      or (normalized_carrier in ('gls-de', 'gls-es', 'ids-logistik', 'packs', 'transmission', 'postmedia-parcel-services') and normalized_postcode is null)
      or (normalized_carrier = 'heppner' and coalesce(normalized_postcode, '') !~ '^[0-9]{4,5}$')
      or (
        normalized_carrier = 'paack'
        and normalized_postcode is not null
        and (
          char_length(normalized_postcode) not between 3 and 10
          or normalized_postcode !~ '[0-9]'
          or normalized_postcode !~ '^[A-Z0-9]+(-[A-Z0-9]+)*$'
        )
      )
      or (
        normalized_carrier = 'colis-prive'
        and case when tracking_number ~ '^[A-Z0-9]{12}$'
          then coalesce(normalized_postcode, '') !~ '^((0[1-9]|[1-8][0-9]|9[0-578])[0-9]{3}|[BL]?[1-9][0-9]{3})$'
          else normalized_postcode is not null end
      )
      or (normalized_carrier = 'dhl' and tracking_number !~ '^[0-9]{12}$' and normalized_postcode is not null)
      or (normalized_carrier = 'spring-gds' and not (tracking_number ~ '^3S[A-Z]{1,4}[0-9]+$' and char_length(tracking_number) in (13, 15)) and normalized_postcode is not null)
      or (normalized_carrier = 'a-tu-hora-express' and tracking_number !~ '^([0-9]{10}|[0-9]{16})$' and normalized_postcode is not null)
      or (normalized_carrier = 'canpar' and tracking_number !~ '^[CDKLSUXZ][0-9]{8,20}$' and normalized_postcode is not null)
      or (
        normalized_carrier not in ('dpd', 'dpd-de', 'gls-ch', 'gls-de', 'gls-es', 'mondial-relay', 'heppner', 'paack', 'colis-prive', 'xdp', 'gebrueder-weiss', 'asendia', 'gls-pl', 'relaytech', 'dx', 'arrow-xl', 'gls-at', 'ids-logistik', 'packs', 'transmission', 'postmedia-parcel-services', 'dhl', 'spring-gds', 'a-tu-hora-express', 'canpar')
        and normalized_postcode is not null
      ) then
    raise exception 'Invalid delivery postcode' using errcode = '22023';
  end if;

  update public.sync_jobs
  set
    state = 'failed',
    completed_at = now(),
    lease_until = null,
    locked_by = null,
    dedupe_key = null,
    last_error = 'Superseded because the package carrier changed.'
  where package_id = p_package_id
    and state in ('queued', 'running');


  update public.packages
  set
    carrier = normalized_carrier,
    tracking_url = normalized_url,
    dpd_postcode = normalized_postcode,
    last_synced_at = null,
    sync_status = 'pending',
    sync_error = null,
    carrier_data = (carrier_data - array['active_tracking_carrier', 'active_tracking_number', 'swiss_post_ready', 'auto_changed_from', 'auto_changed_to', 'auto_changed_at'])
      || case when not (carrier_data ? 'routing') and sync_status = 'ok' then jsonb_build_object('routing',
        jsonb_strip_nulls(jsonb_build_object('version', 1, 'configured_carrier', carrier,
          'confirmed_carrier', carrier, 'confirmed_number', packages.tracking_number,
          'confirmed_tracking_url', case when carrier = normalized_carrier then normalized_url else tracking_url end,
          'confirmed_postcode', case when carrier = normalized_carrier then normalized_postcode else dpd_postcode end,
          'last_success_at', last_synced_at, 'last_event_at', carrier_data->>'last_update')))
        -- The owner's inputs for the confirmed carrier replace the saved ones.
        when carrier_data->'routing'->>'confirmed_carrier' = normalized_carrier then jsonb_build_object('routing',
          ((carrier_data->'routing') - array['confirmed_tracking_url', 'confirmed_postcode'])
            || jsonb_strip_nulls(jsonb_build_object('confirmed_tracking_url', normalized_url,
              'confirmed_postcode', normalized_postcode)))
        else '{}'::jsonb end
  where id = p_package_id
    and user_id = actor_id;

  return true;
end;
$$;

notify pgrst, 'reload schema';


insert into public.applied_migrations (name) values ('20261031020000_tuodatong') on conflict do nothing;
