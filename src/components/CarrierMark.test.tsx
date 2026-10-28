import { render } from '@testing-library/react';
import { describe, expect, it, vi } from 'vitest';
import { CARRIERS, carrierInfo } from '../lib/carriers';
import { CarrierMark } from './CarrierMark';
import markup from './carrierMark.fixture.json';
import { CARRIER_DECALS, CARRIER_TRUCK, carrierDecal } from '../brand';

/** Recorded SVG markup anchors the shared truck and selected liveries. */
describe('carrier mark', () => {
  it.each(Object.keys(markup) as (keyof typeof markup)[])('draws %s exactly as recorded', (id) => {
    const { container } = render(<CarrierMark carrier={carrierInfo(id)} />);
    expect(container.innerHTML).toBe(markup[id]);
  });

  it.each([
    'ups', 'fedex', 'dpd', 'dpd-fr', 'amazon-logistics', 'amazon-shipping', 'japan-post', 'dhl-ecommerce',
    'dhl-ecommerce-es', 'dhl-ecommerce-nl', 'dhl-ecommerce-pl', 'dhl-ecommerce-uk', 'swiss-post',
    'quickpac', 'la-poste', 'chronopost', 'india-post', 'mondial-relay', 'spring-gds', 'swiss-post-cargo', 'postlogistics',
    'usps', 'royal-mail', 'canada-post', 'australia-post', 'tnt', 'correos-spain', 'correos-express', 'yamato', 'china-post',
    'inpost', 'bpost', 'austrian-post', 'hermes', 'hermes-de', 'nova-poshta', 'brt', 'dpd-de', 'dpd-uk',
    'korea-post', 'nz-post', 'poczta-polska', 'ukrposhta', 'correios-br', 'ctt', 'ctt-express', 'sf-express', 'ninja-van',
    'hongkong-post', 'thailand-post', 'bring-posten', 'packeta', 'gls-ch', 'gls-de', 'gls-fr', 'evri', 'evri-uk',
    'postnord', 'poste-italiane', 'aramex', 'j-and-t', 'aliexpress', 'zto', 'yto', 'sto', 'yunda', 'jd-logistics', 'ems',
    'seur', 'mrw', 'nacex', 'purolator', 'parcelforce', 'posti', 'an-post', 'delhivery', 'blue-dart', 'dtdc', 'sagawa',
    'ontrac', 'estafeta', 'pos-malaysia', 'four-px', 'planzer', 'tipsa', 'gofo', 'dachser',
    'asendia', 'colis-prive', 'ciblex', 'colisweb', 'uniuni', 'yunexpress', 'canpar', 'relais-colis',
    'singapore-post', 'geodis', 'lbc-express', 'spx-ph', 'ekart', 'xpressbees', 'cne', 'yanwen', 'sunyou', 'speedx',
    'spee-dee', 'speedpak', 'the-courier-guy', 'landmark-global', 'old-dominion', 'paack', 'c-chez-vous',
    'ecoscooting', 'correos-chile', 'delivengo', 'intelcom', 'shipup', 'heppner', 'j-and-t-cargo', 'emile',
    'abf-freight', 'acs-courier', 'allegro-one', 'apc-overnight', 'aras-kargo', 'box-now', 'cdek',
    'ceska-posta', 'chit-chats', 'correos-de-mexico', 'cyprus-post', 'day-ross', 'dsv', 'dx', 'dynalogic',
    'envialia', 'epost-global', 'estes', 'fan-courier', 'gebrueder-weiss', 'geniki-taxydromiki', 'jitsu',
    'kuehne-nagel', 'latvijas-pasts', 'loomis-express', 'lso', 'magyar-posta', 'meest', 'nationex',
    'post-luxembourg', 'posta-slovenije', 'posta-srbije', 'ptt', 'redpack', 'rl-carriers', 'russian-post',
    'saia', 'sameday', 'speedy', 'trans-o-flex', 'ubi-smart-parcel', 'wanbexpress', 'whistl', 'xdp', 'xpo-ltl',
    'yurtici-kargo', 'zeleris',
    '99minutos', 'cargus', 'cdl', 'dao', 'early-bird', 'econt', 'elta', 'express-one', 'foxpost', 'geis',
    'go-express', 'helthjem', 'hrvatska-posta', 'instabox', 'lietuvos-pastas', 'maltapost', 'matkahuolto',
    'omniva', 'orlen-paczka', 'osm-worldwide', 'paquetexpress', 'passport', 'ppl', 'slovak-parcel-service',
    'slovenska-posta', 'stallion-express', 'venipak', 'vinted-go',
    '5post', 'a-duie-pyle', 'aaa-cooper', 'andreani', 'anjun', 'anteraja', 'apc-postal-logistics', 'arrow-xl',
    'averitt', 'azul-cargo', 'barid-al-maghrib', 'belpost', 'best-transport', 'better-trucks', 'bex-express',
    'blue-express', 'bulgarian-posts', 'burd-delivery', 'castores', 'central-transport', 'ceva-logistics',
    'chilexpress', 'chunghwa-post', 'citylink-express', 'citysprint', 'cj-logistics', 'cj-packet',
    'coordinadora', 'correo-argentino', 'courier-center', 'couriersplease', 'cycloon', 'd-express',
    'danske-fragtmaend', 'dayton-freight', 'dellin', 'deppon', 'direct-freight-express', 'egypt-post',
    'emirates-post', 'flash-express', 'forward-air', 'fukuyama', 'gati', 'gdex', 'ghn', 'ghtk', 'hanjin',
    'hepsijet', 'hunter-express', 'ics-courier', 'ids-logistik', 'imile', 'interrapidisimo', 'israel-post',
    'jadlog', 'jersey-post', 'jne', 'kazpost', 'kex', 'kolay-gelsin', 'logen', 'loggi', 'lotte-global',
    'mainfreight', 'maritime-ontario', 'naqel', 'nipost', 'nippon-express', 'nz-couriers', 'oca',
    'olva-courier', 'overseas-express', 'palletways', 'pecom', 'pilot-freight', 'pitt-ohio', 'pos-indonesia',
    'post-haste', 'posta-moldovei', 'posta-north-macedonia', 'posta-romana', 'posta-shqiptare', 'posturinn',
    'professional-couriers', 'raben', 'ram-hand-to-hand', 'roadrunner', 'saudi-post', 'seino', 'seko',
    'sendex', 'sending', 'serpost', 'servientrega', 'sfc', 'shadowfax', 'shree-maruti', 'sicepat',
    'skynet-worldwide', 'smsa-express', 'south-african-post-office', 'southeastern-freight-lines', 'speedex',
    'starken', 'surat-kargo', 'team-global-express', 'tforce-freight', 'toptrans', 'total-express', 'trackon',
    'trendyol-express', 'vasp-expresso', 'viettel-post', 'vnpost', 'ward-trucking',
    '17feia', 'tsjy', 'hywl', 'dhlink', 'gzyl', 'thhy', 'jrt', 'jhgj', 'ktd', 'swiftx', 'celeritas',
    'asyad-express', 'border-express', 'bosta', 'correo-uruguayo', 'courier-it', 'daylight-transport',
    'deprisa', 'envia-colombia', 'europochta', 'midland-transport', 'oak-harbor', 'skroutz-last-mile',
    'tci-express', 'urbano', 'vrl-logistics',
    'blueex', 'leopards-courier', 'tcs-pakistan', 'postex', 'slgtrax', 'm-and-p', 'pakistan-post',
    'pathao-courier', 'redx', 'steadfast', 'sundarban-courier', 'paperfly', 'ecourier', 'bangladesh-post',
    'sri-lanka-post', 'domex', 'akash-ganga', 'madhur-courier', 'shree-nandan', 'st-courier',
    'tirupati-courier', 'prompt-xpress', 'pronto-lanka', 'nepal-post', 'idexpress', 'lion-parcel',
    'sapx-express', 'tiki', 'wahana', 'line-clear', '2go', 'flash-express-ph', 'jrs-express', 'xde-logistics',
    'chunil', 'gs-postbox', 'hapdong', 'ilyang-logis', 'kunyoung', 'hct-logistics', 'kerry-tj',
    'pelican-express', 'seven-eleven-tw', 't-cat', 'kex-express-hk', 'sbs-sokuhai', 'tracx-logis',
    'quantium-solutions', 'janio', 'j-and-t-cn', 'equick', 'pfc-express', 'ecms-express', '139express',
    'winit', 'energia-tk', 'zheldor', 'kit-tk', 'alem-tat', 'sat-ukraine', 'taxydema', 'dropp', 'yalidine',
    'zr-express', 'fargo-courier', 'internet-express', 'mds-collivery', 'paxi', 'buffalo-ex',
    'aeropost', 'aks-express', 'algerie-poste', 'aymakan', 'azerpost', 'best-express-vn', 'city-express-rs',
    'cse-russia', 'dohrn-transfer', 'ems-vietnam', 'ethiopian-post', 'euroexpress-ba', 'followmont',
    'ghana-post', 'groupe-guilbault', 'haypost', 'hi-trans', 'jordan-post', 'la-poste-tunisienne', 'libanpost',
    'mauritius-post', 'mylerz', 'ncs-courier', 'nhat-tin', 'nim-express', 'northline', 'ozon-logistics',
    'pargo', 'pbt', 'polaris-transport', 'posta-kenya', 'postaplus', 'quiqup', 'roadie', 'rpx-indonesia',
    'saee', 'scg-express', 'tanzania-posts', 'tst-cf-express', 'uzbekistan-post', 'zajil', '4-72',
    'aex-paraguay', 'ampm', 'chazki', 'cruz-del-sur', 'forza-delivery', 'guatex', 'jamaica-post',
    'laar-courier', 'pullman-cargo', 'tealca', 'tufesa', 'via-cargo', 'yimidida', 'zoom-ve',
    'mayiyinfu',
    'cttyex',
    'ameex', 'amerijet', 'anousith', 'ap-cargo', 'bookurier', 'botswana-post', 'bts-uz', 'budbee', 'bussgods',
    'campost', 'correos-bolivia', 'courier-plus-ng', 'credifin', 'daewoo-fastex', 'easy-mail', 'evropat',
    'exline-kz', 'fargo-uz', 'fastfrate', 'first-delivery-tn', 'franch-express', 'gel-express', 'gobolt',
    'gogo-xpress', 'horoz', 'integral-pack', 'jeebly', 'kargoist', 'la-poste-ci', 'la-poste-senegal',
    'mailplus-au', 'major-express', 'mandae', 'messenger-cz', 'nord-wheel', 'ocasa', 'opt-nc', 'pmm',
    'pony-express', 'shipa', 'shree-anjani', 'ues-uy', 'upaya', 'vimenpaq', 'vozovoz', 'x-express-ba',
    'metropolitan-warehouse',
    'citymail', 'correos-cuba', 'correos-el-salvador', 'delnext', 'hispapost', 'intereuropa', 'iran-post',
    'kronos-express', 'm-one', 'pack-man', 'pannon-xp', 'phlpost', 'redur', 'torrestir',
    'afghan-post', 'corre', 'correos-nicaragua', 'kyrgyz-post', 'la-poste-burkina-faso', 'lao-post',
    'pitney-bowes',
    'a1-post', 'direx', 'fermopoint', 'fg-delivery', 'go4', 'happy-post', 'hp-mostar', 'hr-parcel',
    'intex-paketdienst', 'liccardi', 'logoix', 'nox', 'peddler', 'pigeon-express', 'posta-faroe', 'tusass',
    'tws-express',
    'byhd',
    'eurodis', 'parcelpoint', 'drop2shop', 'neighbour-express', 'packli-punkt', 'yilong-logistics',
    'codexpress', 'designer-transport', 'direct-couriers', 'ewe', 'homexpress', 'postmedia-parcel-services',
    'precision-deliveries', 'transport-rdl', 'energo', 't-group', 'method', 'sailpost',
    'tforce-logistics', 'globalpost', 'united-delivery-service', 'best-overnite-express', 'estes-forwarding',
    'ajex', 'exelot', 'kwick-box', 'labaih', 'shipblu', 'starlinks', 'gtl-goto', 'paxel', 'pickupp',
    '247express',
    'fleetoptics', 'straightship', 'apple-express', 'ab-courier', 'lightning-express', 'kyoungdong',
    'yc-delivery', 'sagawa-global', 'copa-courier', 'hound-express', 'pdq-chile', 'skyking', 'veloces',
    'valmo', 'rivigo', 'bombino', 'tccs', 'tej-couriers', 'shree-mahavir', 'maruti-air', 'icc-worldwide',
    'noest',
    'bjs-home-delivery', 'norsk-global', 'one-world-express', 'xlcourier', 'transmission', 'packs',
    'tdn', 'cbl-logistica', 'inter-courier', 'sp-express', 'dragon-star-curier', 'connect-couriers',
    'fastdrop',
    'pro-carrier', 'teleship', 'a-tu-hora-express', 'transaher', 'urvaam', 'closer-logistics', 'chronodiali',
    'cst-italia', 'tmm-express', 'next-level-delivery',
    'pdn-express', 'x-trac', 'hovership',
    'shipentegra', 'pts-worldwide-express', 'evdemo-lojistik', 'big-smart', 'proships', 'gta-airskip',
    'domain-logistics', 'fleetex', 'postplus', 'bringer-parcel', 'micro-express',
    'l-post', 'vestovoy', '4sides', 'dobropost', 'flippost', 'jet-logistic', 'starex-az',
    'mongol-infinity-post', 'peqasus-az',
    'c3x', 'eco-express-ae', 'logistiq', 'quicksilver-sa', 'skyex-ae', 'team-express-ae', 'tfm-express',
    'transcorp', 'air-star-xpress', 'first-fly-express', 'flyking', 'goodluck-courier', 'grand-slam-express',
    'jetline-couriers', 'pavan-courier', 'sahara-express', 'shree-mahabali-express', 'speed-and-safe',
    'ocs-ana', 'icl-express', 'atlantic-courier', 'gms-worldwide', 'pushpak-courier',
    'united-worldwide-couriers', 'dnx-cargo', 'daily-xpress',
    'best-express-my', 'royal-express-mm', 'airpak-express', 'makati-express', 'indah-logistik',
    'pandu-logistics', 'pcp-express', 'houng-ah-loun', 'netpost-vn', 'tp-logistics-th', 'pcs-logistics-vn',
    'rr-express-sg',
    'eparcel-kr', 'aci-express-kr', 'gsi-express', 'today-kr', 'exmate', 'winion-logis', 'last-one-mile-coop',
    'dpex', 'mrl-global', 'fliway', 'morning-global',
    'rpx-hk', 'montenegro-post', 'vitran-express',
    'am-home-delivery', 'american-west-express', 'ccfs', 'custom-companies', 'hercules-freight',
    'gaash', 'orian',
    'qwintry', 'skypostal', 'ait-worldwide', 'team-worldwide',
    'huahan',
    'anserx', 'chukou1', 'jumppoint', 'ycs-logistics',
    'lingdong',
    'jcex',
    'shiprocket',
    'first-line',
    'kun-express',
    'juxi-express',
    'techlink',
    'dayone',
    'shouwu',
    'ywe',
    'far-international',
    'intel-valley',
    'pengyou',
    'pin-ag',
    'tusk-logistics',
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
  ] as const)(
    'renders the declared decoration for %s', (id) => {
      const { container } = render(<CarrierMark carrier={carrierInfo(id)} />);
      const paths = [...container.querySelectorAll('svg > path')].map(path => path.getAttribute('d'));
      for (const shape of CARRIER_TRUCK.decals[carrierDecal(id)]) {
        if (shape.type === 'circle') {
          expect(container.querySelector(`svg > circle[cx="${shape.cx}"][cy="${shape.cy}"][r="${String(shape.r).replace(/^0\./, '.')}"]`)).not.toBeNull();
        } else expect(paths).toContain(shape.d);
      }
      expect(container.firstChild).toHaveAttribute('aria-label', carrierInfo(id).name);
    },
  );

  it('covers the three liveries and a carrier that has none', () => {
    expect(Object.keys(markup)).toEqual(['dhl', 'ups', 'gls-ch', 'unknown']);
  });

  it('switches between every livery without React warnings or leftover shapes', () => {
    const errors = vi.spyOn(console, 'error').mockImplementation(() => {});
    try {
      const { container, rerender } = render(<CarrierMark carrier={carrierInfo('quickpac')} />);
      const brandedCarriers = Object.values(CARRIERS).filter(({ id }) => CARRIER_DECALS[id]);
      for (const carrier of [...brandedCarriers, carrierInfo('unknown'), carrierInfo('quickpac')]) {
        rerender(<CarrierMark carrier={carrier} />);
        const fresh = render(<CarrierMark carrier={carrier} />);
        expect(container.innerHTML).toBe(fresh.container.innerHTML);
        fresh.unmount();
      }
      expect(errors).not.toHaveBeenCalled();
    } finally {
      errors.mockRestore();
    }
  });
});
