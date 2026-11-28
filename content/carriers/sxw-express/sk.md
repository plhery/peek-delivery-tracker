---
title: Sledovanie SXW Express: číslo SXW, stavy a doručenie
description: Sleduj zásielku SXW Express (Shengxingwang) zo Šen-čenu: formát čísla SXW + 12 číslic, vlastný tracker, význam stavov, clá v EÚ a koho kontaktovať.
slug: sxw-express-sledovanie-zasielky
published: 2026-11-27
updated: 2026-11-27
---

SXW Express je názov, pod ktorým Peek vedie spoločnosť Shenzhen Shengxingwang Supply Chain Management Co., Ltd. (深圳胜兴旺供应链管理有限公司) — logistickú firmu zo Šen-čenu v Číne, ktorá zabezpečuje medzinárodnú prepravu pre firmy a internetových predajcov. Ak čínsky obchod odoslal tvoju objednávku cez SXW, hľadaj 15-znakové číslo tvorené písmenami `SXW` a dvanástimi číslicami a vlož ho do poľa vyššie. Zásielku si môžeš skontrolovať aj priamo v trackeri SXW, ktorý sa otvára z poľa „Express Tracking“ na webe `sxwexpress.cn`. Peek číta ten istý tracker za teba.

## Sledovacie číslo SXW Express: ako vyzerá

Web SXW neuvádza popis formátu čísla. Tvar, ktorý Peek pozná ako SXW Express, tvorí `SXW` a dvanásť číslic — spolu 15 znakov. Príklady s vymyslenými číslicami:

| Číslo | Čo to je |
| --- | --- |
| `SXW482910573617` | `SXW` a dvanásť číslic: štandardný formát SXW Express. Peek hneď rozpozná SXW Express. |
| `SXW48291057317` | Len jedenásť číslic: nie je to formát SXW. Skontroluj, či nechýba číslica. |
| `GFUS48291057361817` | `GFUS` a štrnásť číslic: číslo [GOFO Express](carrier:gofo), ktoré môže záznam SXW uviesť pre zásielky do USA (pozri nižšie). |

Medzery, pomlčky a malé písmená pri vložení do Peeku nezavážia.

**Kde ho nájsť.** Predajca objedná prepravu u SXW a pošle ti sledovacie číslo v potvrdení o odoslaní alebo na stránke objednávky. Ak ho nevieš nájsť, spýtaj sa predajcu.

**Kontrola priamo na webe SXW.** Na domovskej stránke `sxwexpress.cn` má karta „Express Tracking“ pole „Enter your tracking number here!“ a tlačidlo „Track“. Výsledok sa otvorí na novej karte v trackeri SXW po čínsky, pričom tracker má aj anglickú verziu. Logo 17TRACK vedľa poľa vedie na samostatnú službu 17TRACK — to nie je vlastný tracker SXW.

## Význam sledovacích stavov SXW Express

Väčšina krokov v trackeri SXW je po anglicky; tri sú po čínsky a Peek ich zobrazuje po anglicky. Keďže rozhranie aplikácie Peek nie je v slovenčine, hlavné etapy Peeku sa tiež zobrazujú v angličtine: „Label created“ (štítok vytvorený), „Received by carrier“ (prijaté dopravcom) a „In transit“ (v preprave). Hlavné kroky:

| Tracker SXW uvádza | Čo to pre teba znamená | Peek zobrazuje |
| --- | --- | --- |
| `Parcel Data Received` | Predajca poslal údaje o zásielke. Fyzicky ju ešte nikto neprevzal. | „Label created“ |
| `货物电子信息已经收到` → `The electronic cargo information has been received` | To isté: elektronické údaje o zásielke boli prijaté sledovacím systémom SXW. | „Label created“ |
| `到达收货点` → `Arrived at the receiving point` | Zásielka dorazila na príjmové miesto SXW: prvé fyzické skenovanie v ich rukách. | „Received by carrier“ |
| `Parcel picked up` | Balík bol vyzdvihnutý: prvé skenovanie na prepravnej linke, ktorá ho vezie. | „Received by carrier“ |
| `货物离开操作中心` → `Goods leave the operation center` | Zásielka opustila vlastné operačné centrum SXW. | „In transit“ |
| `Parcel received at Shenzhen Sorting Centre.` | Zásielku prijalo triediace centrum v Šen-čene. | „In transit“ |
| `Parcel processed at Shenzhen Sorting Centre.` | Zásielka bola tam roztriedená. | „In transit“ |
| `Parcel departure in Shenzhen Sorting Centre.` | Zásielka odchádza z tohto triediaceho centra. | „In transit“ |
| `Parcel departure in Transit Facility, Parcel Departure` | Opustila tranzitné pracovisko a pokračuje ďalej. | „In transit“ |
| `Customs declaration completed` | Vývozné colné vyhlásenie v Číne pred letom je hotové. Ide o čínsku colnicu, nie o slovenskú, a balík nie je zadržaný. | „In transit“ |
| `Parcel is leaving Airport, Parcel Leaving Port` | Let so zásielkou odchádza z letiska v Číne po vývoznom vyhlásení. | „In transit“ |

**Čínske a anglické riadky.** Čínske riadky pochádzajú z vlastného systému SXW a väčšina anglických z prepravnej linky, ktorá balík vezie. Dva riadky preto môžu povedať takmer to isté: `货物电子信息已经收到` aj `Parcel Data Received` znamenajú prijatie údajov a `到达收货点` aj `Parcel picked up` znamenajú fyzické prevzatie.

**Ďalšie riadky.** Tracker SXW môže zobraziť aj riadky mimo tejto tabuľky. Peek ich podľa znenia priradí k etape, kde to ide, a zobrazí ich v pôvodnom znení z trackera SXW.

**Časy.** Časy v Číne Peek číta v pekinskom čase (`UTC+8`) a zobrazuje v časovom pásme tvojho zariadenia; od prvého riadku, ktorého miesto končí kódom inej krajiny, ponecháva miestny čas tej krajiny. V zime je 20:00 v Pekingu 13:00 slovenského času (`UTC+1`), v lete 14:00 (`UTC+2`). Krok datovaný po okamihu kontroly v pekinskom čase čaká, kým nastane jeho čas.

## Kto doručuje zásielky SXW Express na Slovensku

Web SXW uvádza, že ponúka doručenie od dverí k dverám („DDU/DDP door-to-door delivery“) a miestne doručenie na poslednej míli („local last-mile delivery“) so sieťou pokrývajúcou Európu, Ameriku, juhovýchodnú Áziu, Blízky východ a Afriku. Zoznam produktov spomína linky UPS z Hongkongu, „ARAMEX Special Line“, „Middle East and Europe small package“ a „US Express Mail Small Packet“, no neuvádza, ktorý konkrétny dopravca doručuje na Slovensku.

**Čo ukazuje kód Peeku.** Pre zásielky do USA môže záznam SXW vedľa čísla SXW uviesť číslo [GOFO Express](carrier:gofo) (`GFUS` a 14 číslic); Peek vtedy zobrazí „Delivery with GOFO Express“ a prepne na GOFO hneď, ako GOFO pridá novšie skenovanie (výnimkou je záznam, ktorého kanál menuje inú firmu, napr. UPS). Rovnako Peek odovzdáva sledovanie [Swiss Post](carrier:swiss-post) vo Švajčiarsku a Lichtenštajnsku, ak je v zázname uvedená platným číslom. Na Slovensku žiadneho pevného partnera neuvádza.

**Pokusy o doručenie a vyzdvihnutie.** Riadia sa pravidlami miestneho dopravcu, keďže web SXW žiadne neuvádza. Spýtaj sa predajcu, ktorá miestna firma balík doručuje a aké je jej sledovacie číslo.

**Colné poplatky v EÚ.** Od 1. júla 2026 sa na online nákupy z krajín mimo EÚ v zásielkach do 150 € uplatňuje paušálne clo 3 € za druh tovaru. Od 1. novembra 2026 pribudol aj manipulačný poplatok EÚ (Union handling fee) 2 € za druh tovaru bez ohľadu na hodnotu zásielky. Jedným druhom tovaru sa rozumejú položky v balíku s rovnakým colným zatriedením, opisom a prípadne rovnakým pôvodom bez ohľadu na počet kusov. Viac nájdeš v sprievodcovi o [zásielkach na colnici](guide:customs) (po anglicky).

## Balík SXW Express neprichádza alebo sledovanie stojí

**Len `Parcel Data Received` alebo `货物电子信息已经收到`.** Predajca vytvoril zásielku, ale balík ešte nikto neoskenoval. Ak sa po niekoľkých dňoch nič nezmení, spýtaj sa predajcu, či ju naozaj odoslal.

**„Waiting for SXW Express“.** Peek zatiaľ nemá potvrdený krok pre tvoje číslo: tracker SXW ho nepozná, nemá k nemu skenovania alebo drží len kroky zapísané vopred, a ani sledovacie služby nič nenašli. Peek ďalej kontroluje. Ak SXW zásielku eviduje bez skenovania alebo len s krokmi zapísanými vopred, ostáva „Waiting for SXW Express“. Ak číslo nepozná ani SXW, ani sledovacie služby, po dvoch dňoch sa zobrazí „After two days, the carrier still doesn’t know this number. Check it, choose another carrier or delete the parcel.“ Porovnaj číslo so správou od predajcu: `SXW` a dvanásť číslic.

**`Customs declaration completed`.** Ide o vývozné vyhlásenie v Číne pred odletom, nie o slovenskú colnicu. Zásielka nie je zadržaná, preto Peek ukazuje „In transit“. Ak ťa neskôr vyzvú zaplatiť dovozné poplatky, pozri sprievodcu o [zásielkach na colnici](guide:customs) (po anglicky).

**Žiadny nový krok niekoľko dní.** Riaď sa odhadom doručenia z obchodu a po jeho uplynutí napíš predajcovi. Viac v sprievodcovi o [sledovaní balíkov z Číny](guide:tracking-from-china) (po anglicky).

**Označené ako doručené, ale nič neprišlo.** Obráť sa na miestneho doručovateľa a predajcu. Viac v sprievodcovi o [doručenom, ale neprijatom balíku](guide:delivered-not-received) (po anglicky).

**Stratené alebo vrátené.** Prepravu u SXW objednal predajca, preto žiadaj vrátenie peňazí alebo opätovné zaslanie od predajcu či cez nákupnú platformu.

## Kontakt a zákaznícka podpora SXW Express

Web SXW (`sxwexpress.cn`) neuvádza žiadne telefónne číslo, e-mail ani poštovú adresu. Stránka „Contact US“ obsahuje len nadpis bez kontaktov a panel „Online Support“ je prázdny. Prihlásenie na webe slúži iba firemným účtom SXW — na sledovanie balíka účet nepotrebuješ.

Zákazníkom SXW je predajca, preto sa ohľadom objednávky obráť najprv na predajcu, a ak neodpovedá, na nákupnú platformu.

## Sledovanie balíka SXW Express v Peeki

[Peek](/) okamžite rozpozná číslo z `SXW` a dvanástich číslic ako SXW Express s označením „Detected carrier“ — žiadny iný dopravca v katalógu Peeku tento formát nepoužíva. Ak ti predajca poslal číslo iného tvaru, vlož ho aj tak: Peek sleduje viac než 4 500 dopravcov. Ručne vyberieš SXW cez „Change“ a vyhľadanie SXW Express (funguje aj Shengxingwang alebo `胜兴旺`), alebo cez „Change carrier“ pri už pridanom balíku.

Peek číta priamo vlastný tracker SXW bez účtu, PSČ či telefónneho čísla. Zobrazuje stav, každý krok s miestom (ak ho tracker uvádza) a cieľovú krajinu. Vynecháva mená, adresu, poštové smerovacie čísla, hmotnosť, počet kusov, zákaznícku referenciu, platobné údaje, podpis aj potvrdenie o doručení. Odhadovaný dátum doručenia nezobrazuje, lebo tracker SXW žiadny neuvádza.

Peek zadrží každý krok datovaný po okamihu kontroly, ako aj krok zapísaný viac než 12 hodín pred časom, ktorý ukazuje, alebo viac než 6 hodín vopred v tej istej sekunde ako takýto krok: kroky zapísané vopred sú plány, nie skenovania. História vtedy až do doručenia alebo vrátenia začína vetou „Other sites may show more steps, but they’re only predictions.“ a cez „Show them“ ich uvidíš sivou s ich dňom („Predicted for Mon 12 Oct“) bez zmeny stavu či upozornení. Ak tracker SXW číslo nepozná alebo neodpovedá, Peek sa opýta sledovacích služieb a zobrazí ich kópiu.

Okrem odovzdania pre GOFO Express (v USA) a Swiss Post zostáva Peek pri trackeri SXW: ak ti predajca dá číslo miestneho dopravcu na Slovensku, pridaj ho do Peeku ako samostatnú zásielku. Kým máš balík otvorený alebo v deň doručenia so zapnutými upozorneniami, Peek kontroluje až každých 10 minút; inak a po utíchnutí raz za hodinu. Upozornenia si nastavíš na „Every scan“, „Important steps“ (colnica, problémy, rozvoz, neúspešné doručenie, vyzdvihnutie, doručenie a vrátenie — teda žiaden z krokov v tabuľke vyššie vrátane `Customs declaration completed` ho nespustí) alebo „Delivery only“. Peek nemôže kontaktovať SXW Express ani predajcu za teba.

## Časté otázky o SXW Express

### Čo je SXW Express?

Názov, pod ktorým Peek vedie firmu Shenzhen Shengxingwang Supply Chain Management Co., Ltd. (`深圳胜兴旺供应链管理有限公司`) na webe `sxwexpress.cn`. Jej stránka ju opisuje ako logistického poskytovateľa v Šen-čene pre námornú, leteckú a železničnú prepravu do Európy, medzinárodný expres a doručenie od dverí k dverám.

### Kde si môžem balík SXW Express skontrolovať sám?

V poli „Express Tracking“ na úvodnej stránke `sxwexpress.cn`: zadaj 15-znakové číslo `SXW…` a klikni na „Track“. Otvorí sa tracker SXW po čínsky (má aj anglickú stránku); logo 17TRACK vedľa poľa vedie na samostatnú službu.

### Čo znamená „Customs declaration completed“ pri SXW Express?

Že vývozné colné vyhlásenie v Číne pred letom je hotové. Peek ho zobrazuje ako „In transit“: ide o colnicu v Číne, nie na Slovensku, a balík nie je zadržaný.

### Kto doručuje balíky SXW Express na Slovensku?

Web SXW neuvádza konkrétneho slovenského dopravcu. Spýtaj sa predajcu, ktorá miestna spoločnosť doručuje tvoju objednávku, a vyžiadaj si jej vlastné sledovacie číslo.

### Koho kontaktovať, ak balík SXW Express neprišiel?

Predajcu, u ktorého si objednal, pretože on dohodol prepravu so SXW. Stránka SXW neuvádza žiadny telefón ani e-mail. Ak predajca nereaguje, otvor spor na nákupnej platforme.

:::sources
- [SXW Express: Shenzhen Shengxingwang Supply Chain Management Co., Ltd. (domovská stránka, po anglicky)](http://www.sxwexpress.cn/) – názov spoločnosti, pole „Express Tracking“ otvárajúce tracker SXW, logo 17TRACK, prihlásenie, produkty a „DDU/DDP door-to-door delivery“
- [SXW Express: About US (po anglicky)](http://www.sxwexpress.cn/about.html) – sídlo v Šen-čene, cezhraničná logistika, námorná, letecká, železničná a expresná preprava, DDP/DDU a „local last-mile delivery“, pokrytie Európy, Ameriky, juhovýchodnej Ázie, Blízkeho východu a Afriky
- [SXW Express: Products (po anglicky)](http://www.sxwexpress.cn/products/default.html) – linky UPS z Hongkongu, ARAMEX Special Line, Middle East and Europe small package, US Express Mail Small Packet
- [SXW Express: Contact US (po anglicky)](http://www.sxwexpress.cn/contact.html) – prázdna kontaktná stránka bez telefónu, e-mailu či adresy, prázdny panel „Online Support“
- [SXW Express: 会员登录 (prihlásenie, po čínsky)](http://www.sxwexpress.cn:8082/) – čínsky názov firmy v sledovacom systéme SXW
- [SXW Express: trace (tracker, anglická stránka)](http://www.sxwexpress.cn:8082/en/trackIndex.htm) – anglická verzia trackera SXW
- [Európska komisia: EU Customs Reform (po anglicky)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – paušálne clo 3 € za druh tovaru do 150 € od 1. júla 2026, manipulačný poplatok EÚ 2 € od 1. novembra 2026
- [Európska komisia: Guidance on the EUR 3 customs duty and the Union handling fee (PDF, po anglicky)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – poplatok 2 € bez ohľadu na hodnotu a definícia druhu tovaru
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – názvy, formát `SXW` + 12 číslic, čítanie trackera SXW, znenia a etapy krokov, pekinský čas, zadržiavanie krokov zapísaných vopred, záložné sledovacie služby, odovzdanie GOFO Express a Swiss Post a čo Peek zobrazuje a vynecháva
:::
