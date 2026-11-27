---
title: GEODIS Poland sledovanie: číslo PL, statusy a doručenie
description: Sledujte zásielku GEODIS Poland (bývalý PEKAES) cez Strefa Klienta: čísla PL + 16 číslic, význam statusov, doručovanie paliet, reklamácie a +48 22 460 26 26.
slug: geodis-poland-sledovanie-zasielky
published: 2026-11-27
updated: 2026-11-27
---

GEODIS Poland (GEODIS Road Network so sídlom v Błonie, do akvizície spoločnosťou GEODIS v roku 2021 známa ako PEKAES) je poľská paletová a balíková sieť skupiny GEODIS patriacej pod SNCF, ktorá doručuje po Poľsku za 24 až 48 hodín (palety na súkromné adresy pondelok až piatok, 8:00 až 16:00). Ďalší postup závisí od toho, čo vám dal odosielateľ:

- **Číslo `PL` a 16 číslic alebo odkaz na Strefa Klienta:** ide o zásielku GEODIS Poland (balík alebo paletu). Vložte číslo či odkaz do poľa vyššie, alebo vyhľadajte číslo priamo na stránke [Strefa Klienta](https://strefaklienta.pekaes.geodis.pl/en/).
- **Iné číslo GEODIS:** Peek číta ešte jeden sledovací systém GEODIS, a to GEODIS France, ktorého čísla začínajú na `1G` (nižšie). Vyhľadávanie na geodis.com nájde letecké, námorné a last-mile zásielky a cestné zásielky presmeruje na Strefa Klienta.
- **Bez čísla:** číslo poskytuje obchod alebo odosielateľ, najčastejšie v potvrdení objednávky alebo v e-maile o odoslaní.

## Sledovacie číslo GEODIS Poland: formát a kde ho nájsť

GEODIS pracuje iba pre firmy, takže číslo má ten, kto vám tovar cez GEODIS poslal — zvyčajne obchod alebo výrobca: ak ho nemáte, spýtajte sa odosielateľa. Stále nič? Pozrite si [ako nájsť sledovacie číslo](guide:find-tracking-number) (v angličtine).

| Číslo (vymyslené) | Čo je to |
| --- | --- |
| `PL4829105736491827` | `PL` a 16 číslic: číslo zásielky GEODIS Poland, ktoré systém nazýva „Shipment No.“. |
| `1G4829105736` | `1G` a 10 veľkých písmen alebo číslic: číslo [GEODIS](carrier:geodis) vo Francúzsku, sledované vo francúzskom portáli pre príjemcov, nie na Strefa Klienta. |

Na Strefa Klienta zadajte číslo do poľa „Enter Shipment No.“ a stlačte „Search“. Ak systém odpovie `Shipment with such number does not exist`, číslo nepozná: kontaktujte obchod alebo odosielateľa, ako GEODIS radí pri nefunkčnom čísle. Poľská odpoveď `Proszę wprowadzić właściwy numer przesyłki.` žiada platné číslo zásielky — skontrolujte preklep.

## Čo znamenajú sledovacie statusy GEODIS Poland

Strefa Klienta funguje v poľštine aj angličtine. Vyhľadávanie najprv zobrazí súhrn: stav, krajinu doručenia, terminály, dátumy, počet kusov a doručovacie PSČ s mestom. Odkaz „Details“ otvorí jednotlivé skeny s časom a miestom terminálu. Anglické texty v obvyklom poradí:

| Čo píše GEODIS | Čo to znamená pre vás |
| --- | --- |
| `Received from the sender` | GEODIS vyzdvihol zásielku od odosielateľa. |
| `Inbound Scan From Driver to Terminal` | Vodič ju priviezol na terminál: zberný po vyzdvihnutí, alebo po `Outbound for Delivery` doručovací na konci trasy. Ak pred ním nie je `Consignee Absent` ani `Failed delivery (SMS)`, na tejto trase neprebehol pokus o doručenie a zásielka čaká na ďalšiu. |
| `Outbound Scan from (Collection) Terminal to Line-haul` | Odišla zo zberného terminálu kamiónom do vášho doručovacieho terminálu. |
| `Inbound Scan from Line-haul to (Delivery) Terminal` | Dorazila na terminál, ktorý ju bude doručovať. |
| `Awaiting Delivery` | Čaká na tomto termináli na doručovaciu trasu. |
| `Outbound for Delivery` | Je na rozvozovom vozidle. |
| `Consignee Absent` | Vodič vás nezastihol na adrese. |
| `Failed delivery (SMS)` | Pokus o doručenie zlyhal, potvrdené SMS správou. |
| `Awaiting designation of new delivery date` | GEODIS čaká na určenie nového dátumu doručenia. |
| `Awaiting Decision` | GEODIS čaká na pokyny odosielateľa. |
| `Delivery Refusal` | Príjemca zásielku odmietol. |
| `Request for Return` | GEODIS dostal pokyn na vrátenie: je na ceste späť k odosielateľovi, ešte tam nie je. |
| `Delivered` | Doručené. `Delivered (SMS)` znamená to isté, potvrdené SMS správou. |
| `Claim Received` | Bola podaná reklamácia. Ide len o záznam; zásielka sa nehýbe. |

Niekoľko skenov signalizuje zdržanie: `Stopped at Hub` a `Stopped at Collection Terminal` (zadržané v triediacom uzle alebo prvom termináli), `Not Dispatched` (zmeškala plánovaný kamión alebo trasu, ale je stále v sieti) a `Failure`. Riadok začínajúci na `Pseudoscan` ruší predchádzajúce záznamy o doručení, vrátení a reklamácii. Poľská verzia ukazuje rovnaké kroky po poľsky, napríklad `wydanie do dostawy` (na rozvoze) a `doręczone` (doručené).

## Doručovanie GEODIS v Poľsku: doručenie na adresu, avízo a vyzdvihnutie

Všeobecné podmienky GEODIS platné od 4. novembra 2024 určujú priebeh doručovania. Zväzujú klienta — firmu, ktorá prepravu objednala (zvyčajne obchod) — a väčšinu pravidiel stanovujú pre paletové (kusové) zásielky:

- **Nezastihnutý príjemca.** Ak nie ste doma, vodič zanechá správu o pokuse. Po druhom neúspešnom pokuse sa zásielka vracia odosielateľovi; vracia sa aj pri odmietnutí alebo nesprávnej adrese od odosielateľa.
- **Palety na súkromnú adresu.** GEODIS ich doručuje iba v pracovných dňoch od pondelka do piatka, 8:00 až 16:00. Klient musí uviesť vaše mobilné číslo: GEODIS naň posiela upozornenia a vopred dohaduje doručenie telefonicky, SMS alebo e-mailom, pričom volá najneskôr v deň možného doručenia. Ak sa vám nedovolá do dvoch pracovných dní, kontaktuje klienta a vráti zásielku odosielateľovi.
- **Vykládka.** Vodič zloží paletu na súkromnej adrese na úrovni terénu alebo na najbližšom bezpečnom spevnenom mieste, ak nákladné auto nemôže bezpečne zastaviť priamo pred vchodom. Vynesenie („wniesienie“), presná hodina či doručenie do 10:00 alebo 12:00 (len vybrané PSČ) sú samostatné služby, ktoré môže objednať klient.
- **Vyzdvihnutie na termináli.** Keď klient požiada o osobný odber na termináli GEODIS, zásielka čaká 48 hodín v pracovných dňoch od tohto pokynu. Vyzdvihuje sa pondelok až piatok od 11:00 do 16:00; potom sa vracia odosielateľovi.
- **Dobierka.** Platiť možno v hotovosti, kartou, cez BLIK alebo inou bezhotovostnou metódou akceptovanou GEODIS až do 10 000 PLN.
- **Podpis.** Podpisujete zariadenie vodiča alebo prepravné doklady. Podľa podmienok GEODIS nemôžete skontrolovať obsah pred podpisom, preto skontrolujte obal a prípadné poškodenie či chýbajúce kusy hneď zapíšte do dokladov.

## Zásielka GEODIS Poland mešká, je poškodená alebo nedoručená

**Neaktualizuje sa.** Ak sa skeny niekoľko dní nemenia, pozrite si [prečo sa sledovanie neaktualizuje](guide:tracking-not-updating) (v angličtine) a spýtajte sa obchodu.

**Meškanie.** Poľský úrad na ochranu spotrebiteľa UOKiK uvádza, že predajca musí dodať tovar bezodkladne a do 30 dní od kúpy, ak ste sa nedohodli inak. Potom mu môžete dať primeranú dodatočnú lehotu a ak tovar stále nedorazí, odstúpiť od zmluvy.

**Poškodený alebo chýbajúci tovar.** Pri doručení spíšte s vodičom škodový zápis („protokół szkody“) na formulári GEODIS s podpismi oboch strán. Skryté poškodenie alebo úbytok nahláste GEODIS do 7 dní od prevzatia; kto reklamuje, musí preukázať, že škoda vznikla pred doručením. Tovar uchovajte na obhliadku a vyhotovte fotografie s dátumom a časom. Reklamačný formulár GEODIS upozorňuje, že samotný škodový zápis nie je reklamáciou. Pozrite aj [poškodený balík](guide:damaged-parcel) (v angličtine).

**Kto reklamuje.** Podľa podmienok GEODIS podáva reklamáciu klient alebo ním písomne splnomocnená osoba — písomne na reklamačné oddelenie v Błonie bez zbytočného odkladu: do 14 dní od prevzatia pri poškodení alebo chýbajúcich kusoch a do 21 dní od plánovaného dňa doručenia pri omeškaní alebo strate. Ako kupujúci riešite vec s obchodom: podľa UOKiK nesie predajca zodpovednosť za zásielku až do prevzatia, aj keď škodu spôsobil dopravca.

## Kontakt a zákaznícky servis GEODIS Poland

Kanály GEODIS v Poľsku (overené 14. novembra 2026):

- **Zákaznícky servis (Dział Obsługi Klienta):** `+48 22 460 26 26`, uvedené na poľskej stránke Strefa Klienta. Otváracie hodiny nie sú uvedené.
- **Reklamácie poštou:** Dział Reklamacji, ul. Spedycyjna 1, 05-870 Błonie. Formuláre reklamácie a škodového zápisu sú na Strefa Klienta.
- **Pobočky:** [poľská kontaktná stránka GEODIS](https://geodis.com/pl-pl/skontaktuj-sie-z-nami) uvádza 42 pobočiek v Poľsku s adresami vrátane pobočiek GEODIS Road Network.
- **Online formulár:** formulár na geodis.com slúži na cenové ponuky a vyžaduje názov firmy (určený pre podniky).

## Sledovanie zásielky GEODIS Poland cez Peek

Formát `PL` a 16 číslic nepatrí výlučne GEODIS (`PL` je kód Poľska), takže Peek ho nevie priradiť len z tvaru čísla. Vložte ho napriek tomu: Peek sleduje viac než 4 500 dopravcov. Opýta sa GEODIS Poland, či číslo pozná, a zobrazí "GEODIS Poland has this parcel.", keď áno. Ak ho GEODIS ešte neeviduje, zobrazí "Probably GEODIS Poland" a "Waiting for GEODIS Poland’s first scan", kým priebežne kontroluje; ak ho nepozná ani po týždni, navrhne overiť číslo u odosielateľa alebo vybrať iného dopravcu. Dopravcu môžete vybrať sami cez "Change" pod "Fits this number" alebo vyhľadaním (nájde ho aj zadanie `Pekaes`); samotné "GEODIS" je GEODIS France. Pri už pridanej zásielke použite "Change carrier". Odkaz na Strefa Klienta s číslom priradí GEODIS Poland automaticky.

Peek číta Strefa Klienta v angličtine a zobrazuje stav, každý sken v anglickom znení GEODIS s miestom terminálu, čas doručenia a krajinu určenia. Skeny na poľských termináloch sa čítajú vo varšavskom čase; zahraničný terminál má vlastný čas. Peek ich ukazuje v časovom pásme vášho zariadenia; na peektracker.com a v aplikáciách iPhone a Android história končí riadkom "Times are in your time zone:" a názvom pásma. Keď `Pseudoscan` zruší staršie skeny, Peek ich odstráni tiež, a `Claim Received` nemení stav. Systém GEODIS neuvádza očakávaný dátum doručenia, hmotnosť, názov služby, meno príjemcu ani potvrdenie o doručení. Peek vynecháva PSČ a adresu doručenia, počet kusov, kódy terminálov a poznámky, ktoré môžu obsahovať meno podpisujúceho.

Kým máte zásielku otvorenú alebo v deň doručenia so zapnutými notifikáciami, Peek kontroluje až každých 10 minút a v deň výjazdu na doručenie od 6:00 miestneho času až každé 2 minúty, pokiaľ vlastný systém GEODIS odpovedá (cez sledovacie služby až každých 15 minút); inak každú hodinu. Po dvoch dňoch bez správ (12 hodín v "Label created" alebo "Received by carrier") kontroluje každú hodinu. Môže upozorňovať na každý sken, dôležité kroky alebo len deň doručenia. Nemôže dohodnúť termín, osobný odber ani podať reklamáciu: zavolajte GEODIS alebo kontaktujte obchod.

## Otázky o sledovaní GEODIS Poland

### Je GEODIS Poland to isté ako PEKAES?

Áno. GEODIS kúpil spoločnosť PEKAES v roku 2021 a sieť dnes prevádzkuje GEODIS Road Network. Jej sledovanie naďalej beží na adrese strefaklienta.pekaes.geodis.pl.

### Ako dlho trvá doručenie balíkov a paliet GEODIS v Poľsku?

GEODIS uvádza štandardne 24 až 48 hodín v rámci Poľska alebo nasledujúci deň do poludnia expresne. Podmienky stanovujú pre balíky do 30 kg 48 hodín od vyzdvihnutia (okrem víkendov a sviatkov) a pre palety 2 pracovné dni, oboje „spravidla“.

### Doručuje GEODIS v Poľsku v sobotu a vynesie vodič zásielku dovnútra?

Podmienky GEODIS sobotné doručovanie nespomínajú: palety na súkromné adresy doručuje pondelok až piatok od 8:00 do 16:00 na úroveň terénu. Vynesenie („wniesienie“) je možné iba pri paletách, ak ho objednal odosielateľ (do 300 kg, do 150 m od miesta vykládky, po druhé poschodie bez výťahu).

### Koľko pokusov o doručenie robí GEODIS Poland a ako dlho čaká zásielka na termináli?

Po druhom neúspešnom pokuse sa zásielka vracia odosielateľovi. Pri vyžiadaní osobného odberu na termináli odosielateľom čaká 48 hodín (v pracovných dňoch) a vyzdvihuje sa pondelok až piatok medzi 11:00 a 16:00.

### Aké je telefónne číslo GEODIS Poland a ako nahlásiť poškodenie?

Zavolajte Dział Obsługi Klienta na `+48 22 460 26 26`. Pri viditeľnom poškodení spíšte s vodičom škodový zápis („protokół szkody“); skryté poškodenie nahláste do 7 dní od prevzatia a pri online nákupoch kontaktujte obchod (klient podáva písomnú reklamáciu do 14 dní pri škode alebo 21 dní pri meškaní či strate).

:::sources
- [GEODIS Poland: Strefa Klienta, shipment tracking](https://strefaklienta.pekaes.geodis.pl/en/) – search field and button (read 14 November 2026), not-found reply, claim and damage report forms
- [GEODIS Poland: Strefa Klienta, Śledzenie przesyłek](https://strefaklienta.pekaes.geodis.pl/pl/) – customer service number, general terms
- [GEODIS Road Network: Ogólny Regulamin Świadczenia Usług, from 4 November 2024](https://strefaklienta.pekaes.geodis.pl/pl/?file=files/pekaes/Dla%20Klienta%20-%20nowe%20www/GEODIS_SK/GEODIS%20RN_Ogolny%20Regulamin%20Swiadczenia%20Uslug_od%2004.11.2024.pdf) – the client, attempts, pallets to private addresses, notice, unloading, carry-in, terminal pickup, cash on delivery, signing, delivery times, claims
- [GEODIS Road Network: Formularz zgłoszenia reklamacji](https://strefaklienta.pekaes.geodis.pl/pl/?file=files/pekaes/Dla%20Klienta%20-%20nowe%20www/GEODIS_SK/GRN%20Formularz%20zgloszenia%20reklamacji.pdf) – claims address, damage report isn't a claim
- [GEODIS: Śledź swoją przesyłkę](https://geodis.com/pl-pl/sledz-swoja-przesylke) – where the number is, air, sea and last-mile shipments on geodis.com, road transport on its own tracker, number not working
- [GEODIS: Twój obszar roboczy](https://geodis.com/pl-pl/twoj-obszar-roboczy) – Strefa Klienta as GEODIS's tracking
- [GEODIS: Transport palet i paczek](https://geodis.com/pl-pl/uslugi-transportowe/transport-palet-i-paczek) – businesses only, transit times
- [GEODIS: Skontaktuj się z nami](https://geodis.com/pl-pl/skontaktuj-sie-z-nami) – contact form, 42 locations
- [GEODIS: Historia](https://geodis.com/pl-pl/historia) – PEKAES bought in 2021, SNCF
- [UOKiK: Odpowiedzialność za przesyłkę](https://prawakonsumenta.uokik.gov.pl/prawa-ogolne/odpowiedzialnosc-sprzedawcy-za-przesylke/) – seller responsible until delivery
- [UOKiK: Wydanie towaru](https://prawakonsumenta.uokik.gov.pl/prawa-ogolne/wydanie-towaru/) – 30 days, extra time, withdrawal
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – number shape, links to Strefa Klienta, status wordings, a delivery round brought back to the terminal, GEODIS France's numbers, what Peek reads
:::
