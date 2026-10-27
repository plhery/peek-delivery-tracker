---
title: Slovenská pošta sledovanie zásielok: podacie číslo a stavy
description: Ako sledovať zásielku Slovenskej pošty: ako vyzerá podacie číslo, čo znamenajú stavy, ako dlho zásielka čaká na pošte či v BalíkoBOXe, reklamácie a kontakty.
slug: slovenska-posta-sledovanie-zasielok
published: 2026-10-22
updated: 2026-10-27
---

Slovenská pošta je národný poštový operátor. Zásielku môžete sledovať na stránke [Sledovanie zásielok](https://www.posta.sk/sledovanie-zasielok) na posta.sk, v mobilnej aplikácii Slovenskej pošty alebo tak, že podacie číslo vložíte do poľa vyššie. Podacie číslo vám dá odosielateľ: e-shop ho zvyčajne uvedie v e-maile o odoslaní alebo pri objednávke.

## Podacie číslo: ako vyzerá

Slovenská pošta používa medzinárodný formát poštových čísel s 13 znakmi. Na začiatku je prefix, dve písmená s kódom služby, potom osem číslic, kontrolná číslica a na konci kód krajiny, v ktorej bola zásielka podaná. Pri zásielkach podaných na Slovensku je to vždy `SK`. Medzery a malé písmená v poli vyššie nevadia.

| Tvar (vymyslený) | Čo to je |
| --- | --- |
| `BA000000005SK` | Balík v rámci Slovenska: prefix začína na B, D alebo W. Balík od zmluvného zákazníka má prefix ZA až ZN. |
| `RR000000005SK` | Doporučený list: prefix začína na R. |
| `EK000000005SK` | Expres zásielka: prefix začína na E. |
| `CP000000005SK` | Balík do zahraničia: prefix začína na C. |
| `RR000000005CN` | Zásielka zo zahraničia. Má číslo zahraničnej pošty s kódom krajiny, odkiaľ prišla (tu Čína). |

Nie každá zásielka má číslo, ktoré sa dá sledovať. Ak ste ho od odosielateľa nedostali, podľa Slovenskej pošty ju pravdepodobne sledovať nemožno. Ak odosielateľ zadal váš e-mail alebo telefón, zásielka sa vám zobrazí v mobilnej aplikácii, keď overíte, že ste adresát.

Pozor na podobné čísla. Číslo zo 14 číslic, ktoré sa začína na `703`, má tvar čísel [Slovak Parcel Service](carrier:slovak-parcel-service) (stránka v angličtine), nie Slovenskej pošty. Poštové číslo s `CZ` na konci patrí [Českej pošte](carrier:ceska-posta) (v angličtine). A ak vám príde SMS alebo e-mail s „číslom zásielky“ v inom tvare než dve písmená, deväť číslic a kód krajiny, Slovenská pošta to považuje za znak podvodu.

## Čo znamenajú stavy zásielky

Okrem zoznamu udalostí ukazuje Sledovanie zásielok na posta.sk aj celkový stav zásielky: `Podaná`, `V preprave`, `Prevzatá na doručenie`, `Uložená na pošte`, `Uložená v BalíkoBOXe`, `Uložená na výdajnom mieste`, `Doručená`, `V preprave späť`, `Vrátená`, `Nedoručiteľná`, `Zrušená` alebo `eColné konanie`. Pri uloženej zásielke uvidíte aj, koľko dní ešte počká, napríklad „ešte zajtra“.

Peek číta údaje Slovenskej pošty v angličtine, takže v ňom uvidíte udalosti anglicky. Tabuľka preto uvádza anglické znenie Slovenskej pošty a vedľa neho slovenský názov tej istej udalosti z číselníka, ktorý Slovenská pošta zverejňuje k API sledovania zásielok. Na posta.sk môže byť udalosť opísaná podrobnejšie, napríklad s názvom pošty. Kroky v zahraničí hlási zahraničná pošta a uvádzajú len krajinu; slovenský názov pri nich v tabuľke neuvádzame.

| V Peeku (anglicky) | Názov v číselníku Slovenskej pošty | Čo to znamená pre vás |
| --- | --- | --- |
| `Posted` | `Podaná na pošte` | Zásielku podali na pošte. Prvý krok. |
| `In transit` | `Zásielka v preprave` | Putuje medzi poštovými centrami alebo smeruje k vám. |
| `Departure from outward office of exchange` | – | Opustila krajinu, z ktorej ju poslali. |
| `Arrival at inward office of exchange` | – | Dorazila na medzinárodnú výmennú poštu cieľovej krajiny. |
| `Presented to import Customs` | – | Je na colnici v cieľovej krajine. |
| `Out for delivery` | `Prevzatá na doručenie` | Doručovateľ ju prevzal a je s ňou na ceste k vám. |
| `Deposited - addressee not available` | `Uložená na pošte - adresát nezastihnutý` | Nezastihli vás doma, zásielka čaká na pošte. |
| `Picked up` | `Vydaná na pošte` | Vyzdvihli ste si ju na pošte vy alebo niekto za vás. Je doručená. |
| `Delivered to addressee` | `Doručená` | Doručená. V zahraničí ju doručila tamojšia pošta. |
| `Unclaimed - sent back` | `Neprevzatá v odbernej lehote - odoslaná späť` | Nevyzdvihli ste ju v odbernej lehote a vracia sa odosielateľovi. |
| `Returned` | `Vrátená` | Je späť u odosielateľa. |

V číselníku nájdete aj udalosti pre BalíkoBOX a výdajné miesta (`Zásielka uložená v BalíkoBOXe`, `Vydaná z BalíkoBOXu`, `Uložená v PoštaPOINTe`) a pre zmeny, o ktoré ste požiadali (`Predĺženie odbernej lehoty - na žiadosť adresáta`, `Doposlaná - na žiadosť adresáta`, `Pripravená na opakované doručenie`). Zásielka zo zahraničia, ktorá ide na colné konanie, má udalosť `Smerovaná na colnicu, kde bude nasledujúci pracovný deň predložená na colné konanie. Adresát bude informovaný.`

Niekedy namiesto udalostí uvidíte hlásenie. „Zásielka s uvedeným podacím číslom neexistuje“ a „Pre zásielku neevidujeme v informačnom systéme žiadne údaje“ znamenajú, že pošta k číslu nemá údaje: skontrolujte ho, prípadne kontaktujte zákaznícky servis. „Zásielka nebola doteraz odosielateľom podaná“ znamená, že odosielateľ má číslo, ale zásielku ešte nepodal; na termín sa pýtajte jeho. A „Pre tento druh zásielky sa služba Sledovanie zásielok neposkytuje.“ znamená, že tento druh zásielky sa nesleduje vôbec.

## Doručenie, BalíkoBOX a vyzdvihnutie na pošte

Slovenská pošta doručuje každý pracovný deň aspoň raz. Zásielka môže ísť:

- **Na adresu alebo do schránky.** Ak vás doručovateľ nezastihne alebo sa zásielka do schránky nezmestí, čaká na pobočke. Oznámenie dostanete do aplikácie, SMS alebo e-mailom, ak to máte aktivované, inak do schránky.
- **Do BalíkoBOXu**, kde si balík alebo expres zásielku vyzdvihnete nonstop. PIN kód vám príde v SMS, e-mailom alebo do aplikácie a dobierku zaplatíte kartou.
- **Na PoštaPOINT** u partnerov Slovenskej pošty, **na pobočku** alebo **do vlastného P.O.BOXu**.

**Ako dlho zásielka čaká.** Balíky a expres zásielky 10 kalendárnych dní, listové zásielky až 18, ak odosielateľ odbernú lehotu neskrátil. Na PoštaPOINTe je to tiež 10 dní. V BalíkoBOXe zostane zásielka len krátko: podľa poštových podmienok účinných od 15. 10. 2026 spravidla 3 kalendárne dni, v odôvodnených prípadoch aj menej (stránky Prijímanie zásielok a BalíkoBOX na posta.sk uvádzajú 2 dni). Potom ju prevezú na pobočku, kde počká do uplynutia celkovej 10-dňovej lehoty. Deň, keď vás nezastihli alebo keď bola zásielka pripravená na vyzdvihnutie, sa do lehoty nepočíta. Nevyzdvihnutá zásielka sa vracia odosielateľovi.

**Kto si ju môže prevziať.** Adresát, splnomocnenec alebo osoba z rovnakej domácnosti. Zásielku do vlastných rúk len adresát alebo splnomocnenec s oprávnením na takéto zásielky, zásielku s PIN kódom ten, komu ste PIN dali. Ak sa pri prevzatí podpisuje, potrebujete doklad totožnosti (občiansky preukaz, cestovný doklad, doklad o pobyte alebo aplikáciu eDOKLADY), pokiaľ vás zamestnanec pošty osobne nepozná alebo nepreberáte cez PIN zo SMS či e-mailu. Zásielku si môžete prevziať už od 15 rokov s platným dokladom.

**Zmena doručenia.** Po zadaní čísla v Sledovaní zásielok alebo v aplikácii uvidíte, čo sa dá zmeniť:

- opakované doručenie zdarma, raz a najskôr nasledujúci pracovný deň po žiadosti;
- predĺženie odbernej lehoty až na 30 dní, raz pri každej zásielke;
- doručenie na inú adresu alebo výdajné miesto zdarma, raz a najneskôr 4 dni pred koncom odbernej lehoty (aj cez Infolinku 0850 122 413);
- zmena dňa doručenia zdarma pri balíku a Exprese na adresu, kým ich doručovateľ neprevzal na doručenie; nový deň môže byť najneskôr 8. pracovný deň po podaní;
- odopretie prijatia.

V niektorých fázach doručovania sa zmena urobiť nedá. Slovenská pošta vtedy odporúča skúsiť to neskôr.

## Zásielka mešká, nehýbe sa alebo sa stratila

Ak sa stav niekoľko dní nemení, zásielka môže byť podľa Slovenskej pošty ešte v spracovaní, čakať na preclenie alebo byť na ceste do cieľovej krajiny. Ak to trvá dlhšie, kontaktujte zákaznícke centrum. Pri zásielke zo zahraničia niektoré krajiny poskytujú len základné údaje alebo aktualizujú stav len v určitých bodoch. Viac v návode [keď sa sledovanie neaktualizuje](guide:tracking-not-updating) (v angličtine).

**Clo.** Zásielka z krajiny mimo EÚ prechádza colným konaním. Slovenská pošta môže byť vaším colným deklarantom: pokyny pošle cez aplikáciu, e-mailom alebo Poštovým oznámením a dokumenty nahráte cez Sledovanie zásielok, aplikáciu alebo ich pošlete e-mailom vyclievaciemu oddeleniu. Od 1. 7. 2025 vyžaduje Finančná správa SR rodné číslo prijímateľa, bez neho pošta zastupovanie odmietne. Clo a DPH zaplatíte na účet Finančnej správy, poplatok za colno-deklaračné služby pri prevzatí. Zásielka čaká 18 dní od oznámenia a lehotu možno predĺžiť až na 90 dní. Viac v návode [o zásielkach na colnici](guide:customs) (v angličtine).

**Doručená, ale neprišla.** Najprv zistite, či ju neprevzal niekto z domácnosti alebo kolega v práci. Ak ju stále nemáte, kontaktujte Slovenskú poštu, aby situáciu preverila.

**Reklamácia.** Pri vnútroštátnej zásielke môže reklamovať odosielateľ, adresát alebo nimi poverená osoba, pri medzinárodnej len odosielateľ. Pri zásielke zo zahraničného e-shopu sa teda obráťte na e-shop. Reklamáciu podáte do 6 mesiacov od podania zásielky; poškodenie, ktoré pri dodaní nebolo vidieť, najneskôr nasledujúci pracovný deň po dodaní. Podať ju môžete na ktorejkoľvek pobočke alebo e-mailom s podpísaným reklamačným listom (nie pri poškodení). Zásielku podanú cez aplikácie môžete reklamovať aj v ePodacom hárku a vo vybraných prípadoch v mobilnej aplikácii. Pri poškodení prineste zásielku aj s vonkajším obalom a vnútorným balením. Vnútroštátnu reklamáciu vybavia do 30 dní, medzinárodnú do 60.

## Kontakt na Slovenskú poštu

Kontakty podľa posta.sk k 22. októbru 2026:

- **Zo Slovenska:** Infolinka 0850 122 413, pracovné dni od 8:00 do 17:00.
- **Zo zahraničia:** +421 48 437 87 77, pracovné dni od 8:00 do 16:00.
- **E-mail:** zakaznickyservis@slposta.sk, na otázky aj reklamácie. Pri sťažnosti napíšte do predmetu „Sťažnosť“.
- **Formulár:** [Napíšte nám](https://www.posta.sk/podpora/napiste-nam). Formulár v Sledovaní zásielok nie je určený na reklamácie.
- **Osobne:** na ktorejkoľvek pobočke.

> Slovenská pošta varuje pred falošnými SMS a e-mailmi, ktoré žiadajú doplatiť poštovné či clo alebo doplniť chýbajúce údaje. Píše z domén slposta.sk (aj tie však môžu podvodníci zneužiť), platby za doručenie cez e-mail ani SMS nikdy nežiada a nedoručené zásielky nepredáva. Neklikajte na odkazy, nič neplaťte, podvod nahláste a zásielku si overte v aplikácii.

## Sledovanie Slovenskej pošty v Peeku

Pri poštovom čísle so `SK` na konci a správnou kontrolnou číslicou, ktorého prefix sa začína na C, E, L alebo R, určí [Peek](/) Slovenskú poštu priamo podľa čísla. Pri inom prefixe, napríklad pri vnútroštátnom balíku na B alebo D, sa Peek opýta Slovenskej pošty (a [Chronopostu](carrier:chronopost)): ak číslo pozná, ukáže „Slovenská pošta has this parcel.“, ak ešte nie, „Not found at … yet. Peek will keep checking after you add it.“ So zlou kontrolnou číslicou sa Slovenskej pošty nepýta, preto číslo skontrolujte. Číslo zahraničnej pošty Slovenskej pošte neodovzdá: vložte ho aj tak (Peek sleduje viac ako 3 500 prepravcov), kliknite na „Change“ a vyberte „Slovenská pošta“. Vložiť môžete aj odkaz z posta.sk/sledovanie-zasielok alebo z tandt.posta.sk.

Peek kontroluje zásielku cez deň každých 10 minút, keď ju doručovateľ vezie k vám, každé 2 minúty, v noci raz za hodinu až dve, a pri zmene stavu vás môže upozorniť. Každý krok ukáže na mape: poštu, BalíkoBOX alebo PoštaPOINT na Slovensku a krajinu v zahraničí. Ukáže aj hmotnosť, cieľovú krajinu, čas doručenia a názov miesta, kde zásielka čaká (nie jeho adresu ani dátum, dokedy čaká). Predpokladaný deň doručenia Slovenská pošta neuvádza. Časy krokov na Slovensku Peek číta podľa slovenského času a ukazuje ich v časovom pásme vášho zariadenia; krok v krajine s viacerými časovými pásmami sa nezobrazí. Udalosti majú anglické znenie Slovenskej pošty, okrem `In transit` a `Out for delivery`, ktoré Peek nahradí vlastným znením; `Picked up` sa počíta ako doručenie. Zásielku do krajiny, ktorej národná pošta je v zozname Peeku, môžete sledovať aj tam. Kontaktovať Slovenskú poštu ani podať reklamáciu za vás Peek nemôže.

## Časté otázky o sledovaní zásielok Slovenskej pošty

### Ako sledovať zásielku Slovenskej pošty?

Zadajte podacie číslo, napríklad v tvare `RR000000005SK`, na posta.sk/sledovanie-zasielok, v aplikácii Slovenskej pošty alebo do poľa vyššie. Na posta.sk si po zadaní čísla môžete zapnúť upozornenia o každej zmene stavu.

### Dá sa sledovať aj zásielka zo zahraničia?

Áno, ak má podacie číslo. Podľa Slovenskej pošty sa dá sledovať väčšina medzinárodných zásielok, rozsah údajov však závisí od zahraničnej pošty a niektoré krajiny aktualizujú stav len v určitých bodoch.

### Ako dlho trvá doručenie balíka Slovenskou poštou?

Poštové podmienky pre Balík uvádzajú lehotu D+2: doručenie druhý pracovný deň po dni podania. Pre zásielky zo zahraničia Slovenská pošta lehotu neuvádza.

### Ako dlho čaká zásielka na pošte?

Balík a expres zásielka 10 kalendárnych dní, list až 18, ak odosielateľ lehotu neskrátil. Raz si ju môžete predĺžiť až na 30 dní.

### Čo znamená „Prevzatá na doručenie“?

Zásielku prevzal doručovateľ a je na ceste k vám. Ak vás nezastihne, zásielka počká na pošte a dostanete oznámenie.

:::sources
- [Slovenská pošta: Sledovanie zásielok](https://www.posta.sk/sledovanie-zasielok) – vyhľadávanie, celkové stavy, hlásenia, časté otázky, zmeny doručenia
- [Slovenská pošta: Manuál pre implementáciu T&T REST API](https://www.posta.sk/files/68377bedf3102ab4bded66c4/sledovanie-zasielok-api.pdf) – číselník udalostí, slovenské a anglické odpovede
- [Slovenská pošta: Technické parametre – Označovanie zásielok a zväzkov](https://www.posta.sk/files/68492d1985877009ff408a51/technicke-parametre-pre-oznacovanie-zasielok-a-zvazkov.pdf) – štruktúra podacieho čísla, prefixy, SK
- [Slovenská pošta: Prijímanie zásielok](https://www.posta.sk/prijimanie/prijimanie-zasielok) – spôsoby doručenia, odberné lehoty (BalíkoBOX 2 dni), kto môže prevziať, doklady
- [Slovenská pošta: Služby k doručovaniu](https://www.posta.sk/prijimanie/sluzby-k-dorucovaniu) – opakované doručenie, predĺženie, iná adresa, zmena dňa
- [Slovenská pošta: Poštové podmienky, všeobecná časť](https://www.posta.sk/files/68384ff484936d59ffefcb96/postove-podmienky-vseobecne-na-slovensko.pdf) – účinnosť od 15. 10. 2026: doručovanie, lehoty, BalíkoBOX spravidla 3 dni, podmienky zmien
- [Slovenská pošta: Balík](https://www.posta.sk/posielanie/balik) – doručenie do 2 pracovných dní, notifikácie
- [Slovenská pošta: Poštové podmienky Balík](https://www.posta.sk/files/68384fef84936d59ffefcb60/postove-podmienky-balik-na-slovensko.pdf) – lehota D+2
- [Slovenská pošta: BalíkoBOX](https://www.posta.sk/balikobox) – PIN kód, platba kartou, úložná lehota 2 dni
- [Slovenská pošta: Pobočky a BalíkoBOXy](https://www.posta.sk/pobocky-a-balikoboxy) – pobočky, BalíkoBOXy, PoštaPOINTy
- [Slovenská pošta: Mobilná aplikácia](https://www.posta.sk/mobilna-aplikacia) – zásielky v aplikácii
- [Slovenská pošta: Preclenie zásielok](https://www.posta.sk/prijimanie/preclenie-zasielok) – colný deklarant, rodné číslo, 18 a 90 dní
- [Slovenská pošta: Reklamácie a sťažnosti](https://www.posta.sk/podpora/reklamacie) – kto reklamuje, lehoty, spôsoby podania, aplikácie
- [Slovenská pošta: Kontakty](https://www.posta.sk/podpora/kontakty) – telefón, hodiny, e-mail
- [Slovenská pošta: Napíšte nám](https://www.posta.sk/podpora/napiste-nam) – kontaktný formulár
- [Slovenská pošta: Varovania pred podvodmi](https://www.posta.sk/podpora/varovania-pred-podvodmi) – falošné správy, formát čísla
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – čo Peek číta u Slovenskej pošty, rozpoznanie čísla
:::
