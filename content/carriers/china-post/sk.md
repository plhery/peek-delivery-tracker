---
title: China Post sledovanie zásielok: čísla CN, stavy, doručenie
description: Sledujte zásielku China Post alebo EMS s koncovkou CN: typy S10 (L, R, C, E, U), preklad čínskych stavov, prevzatie národnou poštou a linky 11183/11185.
slug: china-post-sledovanie-zasielok
published: 2026-11-28
updated: 2026-11-28
---

Sledujte zásielku China Post alebo EMS končiacu na `CN` (dve písmená, deväť číslic a `CN`, poštový formát UPU S10) na ems.com.cn alebo v poli vyššie. Keďže Svetová poštová únia (UPU) povoľuje len jedno číslo S10 na zásielku, po odchode z Číny ju pod tým istým číslom doručí vaša národná pošta — napríklad Slovenská pošta na Slovensku, USPS v USA, Royal Mail alebo Parcelforce v Spojenom kráľovstve, An Post v Írsku, Canada Post v Kanade či Australia Post v Austrálii; pri formálnom pátraní China Post vyžaduje, aby sa odosielateľ obrátil na svoju miestnu poštu (linky China Post: 8610 11183 pre EMS, 8610 11185 pre Postal Business).

## Sledovacie číslo China Post: ako vyzerá

Číslo China Post má dve písmená, deväť číslic a `CN`: ide o medzinárodný poštový formát stanovený Svetovou poštovou úniou (UPU). Prvé písmeno určuje druh zásielky. S vymyslenými číslicami:

| Číslo | O čo ide |
| --- | --- |
| `LT482917367CN` | `L`: sledovaná malá zásielka (listová pošta). |
| `RR482917367CN` | `R`: doporučená listová zásielka. |
| `CP482917367CN` | `C`: balík. |
| `EA482917367CN` | `E`: EMS, expresná služba China Post. |
| `UA482917367CN` | `U`: malá zásielka s tovarom „without customer-oriented tracking“ (bez sledovania pre zákazníkov), slovami UPU. Nepočítajte so skenmi vo vašej krajine. |
| `ZS48291736701` | `ZS`, `ZC` alebo `ZA` a 11 číslic: sledovanie China Post odpovedá aj na tieto čísla. |
| `LP48291057364829` | Nie China Post: logistický kód [Cainiao](carrier:cainiao) (AliExpress). |
| `YT4829105736482910` | Nie China Post, ale [YunExpress](carrier:yunexpress). |

Posledná z deviatich číslic je kontrolná číslica, takže preklep číslo takmer vždy zneplatní. UPU povoľuje len jedno takéto číslo na zásielku, a preto po príchode do vašej krajiny funguje to isté číslo aj na webe vašej národnej pošty.

Pri niektorých zásielkach vyžaduje sledovanie China Post posledné štyri číslice kontaktného telefónneho čísla uvedeného na zásielke.

## Stavy sledovania China Post: čo znamenajú

China Post zapisuje skeny v čínštine. Názvy miest sú v zátvorkách, tu uvedené ako `【…】`: čínska triediaca pošta alebo v zahraničí názov krajiny v čínštine, napríklad `【英国】` pre Spojené kráľovstvo alebo `【美国】` pre USA. V poradí, v akom ich zásielka stretáva:

| China Post uvádza | Čo to pre vás znamená |
| --- | --- |
| `已揽收` | Prijaté: China Post má zásielku. |
| `邮件到达【…】` | Dorazilo na poštu alebo do triediaceho centra China Post. |
| `邮件离开【…】，正在发往下一站` | Opustilo túto poštu smerom k ďalšej zastávke. |
| `【…】退回，备注:安检退回` | Triediace centrum zásielku vrátilo pri bezpečnostnej kontrole a vybaví ju pred ďalším odoslaním; nevracia sa odosielateľovi. Peek zobrazí „Needs attention“. |
| `送交出口海关` | Predložené čínskej vývoznej colnici. |
| `出口海关/留存待验` | Zadržané vývoznou colnicou na kontrolu. |
| `出口海关/放行` | Vývozná colnica zásielku prepustila. |
| `【…】已出口直封` | Uzavreté do exportného záveru na jednom z medzinárodných vymieňacích úradov v Číne. |
| `已交承运商运输` | Odovzdané prepravcovi na dopravu. |
| `航空公司接收`, `航空公司启运` | Aerolinka zásielku prevzala, potom s ňou odletela. |
| `飞机进港` | Lietadlo pristálo. |
| `到达寄达地处理中心` | Dorazilo do spracovateľského centra vo vašej krajine. |
| `送交境外进口海关` | Predložené colnici vo vašej krajine. |
| `境外进口海关留存待验` | Miestna colnica zásielku zadržiava na kontrolu. |
| `寄达地海关允许进口` | Colnica dovoz povolila. |
| `离开【…】处理中心` | Opustilo toto spracovateľské centrum. |
| `已到达【…】投递局` | Dorazilo na vašu dodaciu poštu. |
| `【…】安排投递`, `邮件正在派送中` | Na doručení. |
| `已试投/投递失败` | Vaša pošta sa pokúsila zásielku doručiť, ale neúspešne. |
| `【…】已妥投`, `已签收` | Doručené. |
| `已退回` | Vrátené odosielateľovi. |

Posledné skeny pochádzajú od pošty vašej krajiny a China Post ich len preberá, niekedy až po doručení. Vlastné sledovanie vašej pošty pod tým istým číslom má posledný úsek z prvej ruky.

## Kto doručuje zásielky China Post vo vašej krajine

China Post zásielku vypraví letecky; vo vašej krajine ju preberá národná pošta cez colnicu až k vašim dverám (na Slovensku [Slovenská pošta](carrier:slovenska-posta)):

- **Spojené štáty**: [USPS](carrier:usps).
- **Spojené kráľovstvo**: [Royal Mail](carrier:royal-mail). Prevedie zásielku britskou colnicou a oznámi, či treba zaplatiť DPH alebo clo. Čísla EMS (začínajúce na `E`) preberá namiesto toho [Parcelforce](carrier:parcelforce), britský operátor EMS.
- **Írsko a krajiny EÚ**: v Írsku [An Post](carrier:an-post). Od 1. novembra 2026 uplatňuje EÚ manipulačný poplatok 2 € za druh tovaru; ak ho obchod nevybral pri pokladni, poštový operátor (napríklad An Post v Írsku) si ho vyžiada spolu s clom a prípadnou DPH pred doručením.
- **Kanada**: [Canada Post](carrier:canada-post). Vyhľadajte číslo v jej nástroji Track; ak sa nič neobjaví, Canada Post žiada, aby odosielateľ dal zásielku prešetriť na pošte v krajine odoslania.
- **Austrália**: [Australia Post](carrier:australia-post). Uvádza, že sledovanie zásielok zo zahraničia závisí od služby použitej v krajine odoslania a že colné konanie môže trvať až 15 dní.

Pokusy o doručenie, oznámenia a dĺžka uloženia na pošte sa riadia pravidlami vašej pošty; pozrite si jej stránku a [missed a delivery](guide:missed-delivery) (v angličtine). Nevyzdvihnutá zásielka ([uncollected](guide:uncollected-parcel), v angličtine) ide späť odosielateľovi. Clo, dane a manipulačné poplatky pri zásielkach mimo vašej krajiny opisuje [parcels held at customs](guide:customs) (v angličtine).

## Sledovanie China Post sa neaktualizuje, zásielka sa stratila alebo vrátila

**Zatiaľ žiadne skeny.** China Post nezobrazí nič, kým zásielku nenaskenuje; predtým obchod iba vytvoril štítok. Opýtajte sa obchodu, kedy ju odovzdal. Peek medzitým zobrazuje „Waiting for China Post“. Ak China Post ani po dvoch dňoch k číslu nič neukáže a nič iné naň neodpovedalo, Peek zobrazí „After two days, the carrier still doesn’t know this number. Check it, choose another carrier or delete the parcel.“

**Ticho po odchode z Číny.** Medzi letom a prvým skenom vašej pošty môžu bez noviniek uplynúť celé dni. Skontrolujte sledovanie svojej pošty pod tým istým číslom. Číslo začínajúce na `U` nemusí vo vašej krajine získať žiadne skeny. Viac v [why tracking stops updating](guide:tracking-not-updating) a [tracking a parcel from China](guide:tracking-from-china) (v angličtine).

**Doručené, ale nič neprišlo.** Doručovala vaša pošta, takže začnite jej sledovaním a dokladom o doručení, potom oslovte obchod. Pozrite si [delivered but not received](guide:delivered-not-received) (v angličtine).

**Stratené alebo poškodené.** China Post uvádza, že „for formal inquiry of all international items, the sender shall go to local post office“ (formálne pátranie pre všetky medzinárodné zásielky musí odosielateľ podať na miestnej pošte). Aj USPS, Canada Post a Australia Post odkazujú príjemcov na odosielateľa, ktorý kontaktuje poštu v krajine odoslania. Informujte preto obchod alebo trhovisko a žiadajte vrátenie peňazí či novú zásielku; pozrite si [lost parcels](guide:lost-parcel) (v angličtine).

**Vrátené.** `已退回` znamená, že zásielka sa vracia odosielateľovi. Opýtajte sa obchodu, ako bude postupovať; pozrite si [returned to sender](guide:returned-to-sender) (v angličtine).

## Kontakt a zákaznícky servis China Post

K 10. novembru 2026 uvádza anglický web China Post tieto linky:

- **Logistics and EMS**: 8610 11183.
- **Postal Business**: 8610 11185.

China Post neuvádza, akými jazykmi operátori hovoria. Formálne pátranie musí podať odosielateľ, takže rýchlejší je obchod. Vo veci doručenia, opätovného doručenia alebo colnice kontaktujte svoju vlastnú poštu.

## Sledovanie zásielky China Post v Peeku

[Peek](/) okamžite rozpozná dve písmená, deväť číslic a `CN` ako China Post, ak sedí kontrolná číslica — pokiaľ nezačína na `YY`, lebo tie patria YYpost. Pri nesprávnej kontrolnej číslici číslo ako China Post neberie, a ak China Post vyberiete ručne, vypíše, že China Post „doesn’t issue numbers like this one“: skontrolujte číslo v e-maile z obchodu. Rovnako okamžite rozpozná ako China Post aj čísla `ZS`, `ZC` alebo `ZA` s 11 číslicami. Akékoľvek iné číslo vložte pokojne tiež: Peek sleduje viac ako 4 500 dopravcov. Ak sa ustáli na nesprávnom dopravcovi, vyberte China Post cez „Change“.

Peek číta vlastné sledovanie China Post. Keď China Post pýta číslice telefónu, Peek namiesto toho skúsi verejný náhľad na ems.com.cn; Peek si ich od vás nikdy nepýta. Ak odmietne aj náhľad a žiadna sledovacia služba zásielku nemá, Peek oznámi, že treba viac údajov: vyhľadajte číslo na webe China Post so štyrmi číslicami. China Post zobrazuje návštevníkom iba tri najnovšie skeny, preto Peek dopĺňa dátum podania, keď ho China Post poskytne, a preberá úplnejšiu históriu zo sledovacích služieb, ak ju niektorá má.

Zobrazuje stav, históriu s poštou každého skenu (čínsku poštu pod názvom jej mesta, alebo tak ako je zapísaná, ak ju Peek nevie zaradiť) a cieľovú krajinu. Skeny sa zobrazujú v angličtine tam, kde Peek pozná znenie China Post, inak v čínštine. China Post časuje vlastné skeny podľa čínskeho času, dokonca aj príchod do vašej krajiny; neskoršie skeny prevzaté z vašej národnej pošty si zvyčajne zachovávajú miestny čas tej pošty. Peek zobrazuje každý sken v časovom pásme vášho zariadenia; na peektracker.com a v aplikáciách pre iPhone a Android história končí riadkom „Times are in your time zone:“ a jeho názvom. China Post neuvádza očakávaný dátum doručenia, hmotnosť ani výdajné miesto a Peek vynecháva názov služby, kontakty, údaje o odosielateľovi a príjemcovi, mená a telefóny kuriérov aj mapové súradnice.

Pri čísle končiacom na `CN`, len čo China Post uvedie vašu krajinu, Peek sa s rovnakým číslom pýta aj vašej národnej pošty (USPS, Royal Mail, An Post, Canada Post alebo Australia Post; Parcelforce pri zásielkach EMS do Spojeného kráľovstva) a prepne na ňu, hneď ako ukáže novšie skeny, pričom históriu China Post zachová. Ak China Post žiadnu krajinu neuvedie, Peek sa riadi vašou: v aplikáciách pre iPhone a Android podľa regiónu nastaveného v telefóne, na peektracker.com podľa krajiny, z ktorej ste zásielku pridali.

Peek kontroluje China Post každú hodinu, aj keď je zásielka na doručení. Keď doručenie prevezme vaša pošta, Peek prejde na tempo doručujúceho dopravcu. Môže vás upozorňovať na „Every scan“, „Important steps“ alebo „Delivery only“. Nemôže za vás kontaktovať China Post, vašu poštu ani obchod.

## Otázky k sledovaniu China Post

### Kde je moja zásielka China Post?

Pozrite sa na posledný sken: čínska triediaca pošta v zátvorkách (`【…】`) znamená, že je stále v Číne; keď sa objaví `到达寄达地处理中心`, dorazila do vašej krajiny a to isté číslo `CN` môžete sledovať na webe vlastnej národnej pošty.

### Prečo sa moje sledovanie China Post neaktualizuje?

Sledovanie sa zvyčajne pozastaví počas medzinárodného letu alebo dovozného colného konania medzi vývozným skenom China Post a prvým skenom vašej národnej pošty. Pamätajte, že čísla začínajúce na `U` (`UA…CN`) sú malé zásielky UPU bez zákazníckeho sledovania a v zahraničí nemusia mať žiadne skeny.

### Ako dlho trvá doručenie cez China Post?

Doba doručenia závisí od zvolenej služby (expresné EMS oproti bežnej listovej zásielke či balíku) a cieľovej krajiny, preto sa riaďte odhadom uvedeným pri objednávke v obchode alebo na trhovisku.

### Čo znamená 已妥投 pri sledovaní China Post?

Znamená doručené (napríklad `【英国】已妥投` = doručené v Spojenom kráľovstve). Keďže konečné doručenie vykonala vaša miestna národná pošta, ak nič neprišlo, skontrolujte najprv jej sledovanie a doklad o doručení: pozrite si [delivered but not received](guide:delivered-not-received) (v angličtine).

### Kto doručuje zásielky China Post v USA, Spojenom kráľovstve, Írsku, Kanade, Austrálii alebo na Slovensku?

Vaša národná pošta pod tým istým číslom `CN`: Slovenská pošta na Slovensku, USPS v USA, Royal Mail v Spojenom kráľovstve (Parcelforce pre zásielky EMS `E…CN`), An Post v Írsku, Canada Post v Kanade a Australia Post v Austrálii.

:::sources
- [China Post: Introduction to China Post Group](https://english.chinapost.com.cn/en/folder/1408/3969-1.htm) – štátna národná pošta
- [China Post: EMS and Logistics](https://english.chinapost.com.cn/en/folder/1408/3923-1.htm) – značka EMS
- [China Post: Contact us](https://english.chinapost.com.cn/en/category/1408/3925-1.htm) – weby na sledovanie, formálne pátranie podáva odosielateľ
- [China Post: Home page (English)](https://english.chinapost.com.cn/) – čísla pre Logistics and EMS a Postal Business
- [UPU: S10 standard, Identification of postal items](https://www.upu.int/UPU/media/upu/files/postalSolutions/programmesAndServices/standards/S10-12.pdf) – formát čísla, písmená služieb, rad U bez sledovania, jedno číslo na zásielku
- [GOV.UK: Tax and customs for goods sent from abroad: Overview](https://www.gov.uk/goods-sent-from-abroad) – Royal Mail zabezpečuje britské colné konanie a oznámi DPH alebo clo
- [Revenue: Union Handling Fee press release](https://www.revenue.ie/en/corporate/press-office/press-releases/2026/pr-092926-handling-fee.aspx) – 2 € za druh tovaru od 1. novembra 2026, platené pri pokladni alebo poštovej službe pred doručením
- [Canada Post: Tracking international packages](https://www.canadapost-postescanada.ca/cpc/en/support/kb/tracking/tracking-international-packages.page) – zásielky do Kanady, pátranie podáva odosielateľ, lehoty
- [Australia Post: International tracking](https://auspost.com.au/help/parcel-tracking/international-parcel/tracking) – sledovanie závisí od služby v krajine odoslania
- [Australia Post: Customs](https://auspost.com.au/help/parcel-tracking/international-parcel/customs) – až 15 dní
- [USPS: International Inquiries – The Basics](https://faq.usps.com/s/article/International-Inquiries-The-Basics) – zásielky zo zahraničia: pátranie cez poštu odosielateľa
- [Slovenská pošta: Sledovanie zásielok](https://www.posta.sk/sledovanie-zasielok) – medzinárodné zásielky s poštovým číslom sa väčšinou dajú sledovať
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – znenia skenov China Post, overenie číslic telefónu, verejný náhľad v prehliadači a čo Peek zobrazuje
:::
