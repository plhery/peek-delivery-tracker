---
title: Česká pošta sledování zásilek: podací číslo a stavy
description: Sledování a hledání zásilky České pošty a Balíkovny: podací číslo, stavy, úložní doba, vyzvednutí na kód a kontakt (+420 210 123 456 / Balíkovna 218 218 218).
slug: ceska-posta-sledovani-zasilek
published: 2026-10-22
updated: 2026-11-27
---

Česká pošta je národní poštovní operátor a Balíkovna je její balíková služba s vlastními výdejními místy a boxy. Hledání a sledování zásilky najdeš ve [Sledování zásilek](https://www.postaonline.cz/trackandtrace) na postaonline.cz (až 20 čísel najednou), v aplikaci Pošta Online nebo po vložení podacího čísla do pole nahoře. Podací číslo je na podacím lístku, v SMS či e-mailu od pošty a na výzvě ve schránce; zákaznická linka České pošty je +420 210 123 456 (po–pá 8:00–18:00, info@cpost.cz) a linka Balíkovny 218 218 218.

## Podací číslo: jak vypadá a kde ho najdeš

Česká pošta skládá podací číslo ze tří částí. Na začátku je prefix, obvykle dvě písmena podle druhu zásilky (třeba DR je Balík Do ruky), pak 9 nebo 10 číslic a na konci suffix: většinou písmeno pro typ podavatele, nebo kód země původu, třeba GB. Na velikosti písmen nezáleží. Na postaonline.cz můžeš hledat 1 až 20 zásilek najednou, stačí čísla oddělit čárkou nebo středníkem.

| Tvar (vymyšlený) | Co to je |
| --- | --- |
| `DR4829173654E` | Vnitrostátní balík: dvě písmena, 10 číslic a písmeno. |
| `RR482917367CZ` | Zásilka podaná v Česku: dvě písmena, 9 číslic a `CZ`. Takové číslo mají zásilky do zahraničí (RR je doporučená zásilka), ale `CZ` na konci může mít i doporučené psaní nebo EMS v rámci Česka. |
| `RR482917367CN` | Zásilka ze zahraničí. Má číslo zahraniční pošty s kódem země původu na konci (tady Čína), případně i úplně jiný formát. |

Šestimístný kód pro vyzvednutí, který ti přijde v SMS nebo e-mailu, podací číslo není: slouží jen k vyzvednutí balíku.

Pozor na podobná čísla. `Z` a 10 číslic je číslo [Zásilkovny](carrier:packeta) a 11 číslic mívá [PPL](carrier:ppl); to nejsou čísla České pošty, sleduj je u nich. U zásilky ze zahraničí Česká pošta odpovídá jen za stavy, které vzniknou v Česku. Co se děje před vstupem do Česka nebo po jeho opuštění, záleží na tom, jaká data jí předá zahraniční pošta.

## Co znamenají stavy zásilky

Sledování zásilek na postaonline.cz je česky i anglicky a česká verze popisuje kroky česky. Peek ale čte data České pošty v angličtině, takže v něm uvidíš kroky anglicky, přesně jak je Česká pošta píše v anglické verzi; názvy pošt zůstávají české. Proto tabulka uvádí anglická znění. Každý řádek má datum, ale ne čas. Kroky jsou seřazené zhruba tak, jak jimi zásilka prochází:

| Anglické znění České pošty (ukazuje ho i Peek) | Co to znamená pro tebe |
| --- | --- |
| `Receipt of data about consignment.` | Odesílatel poslal poště údaje o zásilce. Samotnou zásilku pošta ještě nemá. |
| `The consignment was sent for transportation.` | Zásilka je podaná: první řádek s poštou nebo depem. |
| `The consignment is transported.` | Je na cestě sítí České pošty. |
| `The consignment left the logistics hub.` | Opustila logistické centrum. |
| `The consignment is being transported to the delivering post office.` | Jede na poštu, která ji doručí. |
| `The consignment is being prepared for delivery.` | Dorazila na dodací poštu nebo do depa a chystá se k doručení. |
| `The consignment is being delivered.` | Doručovatel ji dnes veze k tobě. |
| `The consignment was deposited - addressee not at home.` | Nezastihli tě doma. Zásilka je uložená na poště, kterou řádek uvádí. |
| `The consignment was deposited to Balíkovna.` | Čeká na výdejním místě nebo v boxu Balíkovny, který řádek uvádí. |
| `The consignment was sent back to the sender - the consignment was not picked up by the addressee in the set Collection Time.` | Nevyzvedl sis ji v úložní době a vrací se odesílateli. |
| `The consignment was sent back - the consignment was not picked up by the addressee in the set pickup period.` | Totéž u Balíkovny: nevyzvednutý balík jde zpátky. |
| `The consignment was returned to the sender.` | Zásilka je zpátky u odesílatele. |

`SMS alert for the addressee - consignment delivery date.` a e-mailová obdoba `Email alert for the addressee - consignment delivery date.` jen zaznamenávají zprávu, kterou ti pošta poslala. `Geographical data from the delivery attempt.` zapisuje, kde byl doručovatel při pokusu o doručení. Žádný z těchto řádků zásilku neposouvá.

Když Česká pošta číslo nezná, napíše česky „Zásilka tohoto podacího čísla není v evidenci.“, anglicky `There is no record for a consignment with this posting number.` Zkontroluj číslo; pokud sedí, pošta zásilku ani data k ní zatím nemá. Hláška „Pro tento druh zásilek Česká pošta informace nezobrazuje.“ (anglicky `Czech Post does not display information for this consignment type.`) znamená něco jiného: u tohoto druhu zásilek Česká pošta stavy neukazuje vůbec, takže z ní nepoznáš, jestli zásilka existuje.

Česká jména stavů Česká pošta uvádí v dokumentaci aplikace ePOST pro firemní podavatele, třeba „Podaná“, „Uložená“, „Uloženo na poště“, „Doručená“, „Dosílka na jinou adresu“ nebo „Vrácená (prošlá odběrní lhůta)“. To jsou názvy z ePOST: Sledování zásilek může tytéž kroky popisovat jinými slovy a Peek je ukazuje anglicky.

## Nezastihli tě doma: uložení na poště a Balíkovna

**Pošta.** Když tě doručovatel nezastihne, nechá ve schránce výzvu a zásilku uloží na ukládací poště. Obvyklá úložní doba je 15 dní, odesílatel ji ale může zkrátit na 10. Přes Změnu doručení online na postaonline.cz nebo telefonem na číslo ukládací pošty, které je na výzvě, můžeš (první dvě možnosti i v aplikaci Pošta Online):

- nechat si zásilku zdarma doručit znovu, v pracovní den mezi 8. a 16. hodinou. Požádej aspoň tři pracovní dny předem a o termín, který nepřekročí odběrní lhůtu;
- zdarma prodloužit úložní dobu až na měsíc ode dne, kdy byla zásilka připravená k vyzvednutí. Nejde to, když odesílatel zvolil „Uložit jen 10 dní“ nebo připsal „Neukládat“ či „Neprodlužovat odběrní lhůtu“, ani u Balíku Do ruky;
- zdarma přeložit zásilku na jinou poštu, na kterou to máš blíž;
- nechat ji doslat na jinou adresu v Česku, to už za poplatek podle ceníku.

**Vyzvednutí na kód.** Většinu vnitrostátních balíků České pošty a Balíkovny vyzvedneš bez občanky. Na přepážce řekneš šestimístný kód pro vyzvednutí ze SMS, e-mailu nebo aplikace a k tomu jméno adresáta nebo číslo zásilky. Kód můžeš předat i někomu blízkému, kdo balík vyzvedne za tebe. Doporučenou ani cennou zásilku na kód nevyzvedneš.

SMS a e-maily o cestě balíku dostaneš zdarma, pokud pošta zná tvůj telefon nebo e-mail. Ty jí předává e-shop, takže je při objednávce vyplň.

**Balíkovna.** Balík si vyzvedneš v Balíkovně na poště, v Balíkovně u partnera (třeba v potravinách, papírnictví, trafice nebo drogerii), nebo v samoobslužném boxu: AlzaBox, OX Point, Penguin Box. Na poště a u partnera na tebe čeká 7 kalendářních dní, v boxu 2 pracovní dny. Prodloužit se to nedá; před koncem lhůty ti Balíkovna napíše e-mailem nebo SMS. Na poště stačí kód a jméno příjemce, případně doklad totožnosti; v boxu zadáš kód na displeji. Dobírku v boxu musíš zaplatit předem kartou online, jinak se schránka neotevře.

Když je tvoje Balíkovna plná, převeze balík na náhradní výdejní místo a dá ti vědět SMS nebo e-mailem. Box nefunguje? Zavolej na číslo zákaznické podpory, které je na boxu; když balík nejde vyzvednout, volej linku Balíkovny 218 218 218. Balíkovna má i vlastní [sledování balíku](https://www.balikovna.cz/cs/sledovat-balik), kde si můžeš nechat kód poslat znovu.

**Na adresu.** U Balíkovny na adresu ti v den doručení přijde SMS a řidič se ti předem ozve telefonem. Když nejsi k zastižení, uloží balík na nejbližším výdejním místě.

## Zásilka se nehýbe, ztratila se nebo je poškozená

Když se stav dlouho nemění, projdi si návod [co dělat, když se sledování neaktualizuje](guide:tracking-not-updating) (v angličtině).

**Clo.** Zásilka ze země mimo EU prochází celním řízením. Česká pošta tě může e-mailem, SMS nebo dopisem vyzvat, abys vyplnil formulář na postaonline.cz/celni-rizeni. Když znáš podací číslo, můžeš ho vyplnit i sám, ještě než zásilka do Česka dorazí. Vyměřené DPH, případně clo, a poplatek České poště za zastoupení v celním řízení zaplatíš při převzetí. Víc v návodu [zásilka zadržená na celnici](guide:customs) (v angličtině).

**Ztracená nebo zpožděná zásilka.** Tu reklamuje odesílatel. Elektronicky to udělá ve Sledování zásilek tlačítkem „Reklamovat zásilku“ nebo v aplikaci Pošta Online v detailu zásilky pod „Možnosti“ a „Reklamace“; na kterékoli poště potřebuje podací stvrzenku. Lhůta je u vnitrostátních zásilek nejvýš jeden rok od podání, u mezinárodních šest měsíců. Napiš tedy e-shopu nebo tomu, kdo zásilku poslal.

**Poškození nebo chybějící obsah** reklamuješ jako příjemce na kterékoli poště, nejpozději do dvou pracovních dnů po převzetí. Vezmi zásilku i s vnějším a vnitřním obalem, nebo poškození dolož fotkami; u mezinárodních zásilek může být lhůta kratší. Je-li poškození zřejmé už před doručením, doručovatel zásilku uloží na poště a dá ti vědět. Když si ho nevšimne a zásilku ti doručuje, máš tři možnosti: požádat doručovatele, ať ti nechá výzvu a zásilku uloží na poště, kde ji pak reklamuješ; zásilku odmítnout a nechat ji vrátit odesílateli; nebo ji převzít, čímž ale o reklamaci přijdeš.

Když s vyřízením reklamace nejsi spokojený, můžeš podat návrh u ČTÚ nebo ČOI.

## Kontakt na Českou poštu

Kontakty, jak je Česká pošta uvádí na svých webech:

- **Zákaznická linka** +420 210 123 456, v pracovní dny od 8:00 do 18:00 pražského času. Nejdřív se ozve hlasová asistentka Eliška; když dotaz nevyřeší, přepojí tě na operátora.
- **E-mail** info@cpost.cz, pro dotazy i stížnosti.
- **Balíkovna**: linka 218 218 218.

> Česká pošta upozorňuje na podvodné zprávy, které se za ni vydávají, například o nedoručené zásilce nebo o doplatku. Nikdy po tobě nechce platební, přihlašovací ani osobní údaje přes odkazy v e-mailu, SMS nebo na sociálních sítích. Stav si ověř sám na webu nebo v aplikaci pošty a podezřelý e-mail můžeš přeposlat na spam@cpost.cz.

## Sledování zásilek České pošty v Peeku

[Peek](/) pozná vnitrostátní čísla České pošty, která začínají `B`, `D`, `NB`, `NP` nebo `NR` (dvě písmena, 10 číslic, písmeno), hned podle tvaru. Číslo stejného tvaru s jinými písmeny na začátku žádnému dopravci nepřiřadí. I tak ho vlož (Peek sleduje přes 4 500 dopravců) a Českou poštu vyber přes „Change“.

U mezinárodních čísel končících `CZ` se správnou kontrolní číslicí se Peek zeptá České pošty (a [Chronopostu](carrier:chronopost)). Když pošta číslo zná, uvidíš „Česká pošta has this parcel.“, jinak „Not found at … yet. Peek will keep checking after you add it.“ S chybnou kontrolní číslicí se České pošty neptá, ani když ji vybereš přes „Change“: zkontroluj číslo. Číslo zahraniční pošty, třeba končící `CN`, Peek přiřadí poště země původu, i když zásilka míří do Česka. České poště zásilku předá, když zahraniční pošta uvede Česko jako cíl (nebo cíl neuvede a ty jsi v Česku) a Česká pošta ukáže datovaný sken ne starší než poslední sken zahraniční pošty, a to se nestane u každé zásilky; jakmile je zásilka v Česku, můžeš Českou poštu vybrat přes „Change“. Vložit můžeš i odkaz z postaonline.cz, který obsahuje `/trackandtrace/-/zasilka/` a číslo.

Česká pošta dává každému kroku jen datum, bez času. Peek proto hledá historii s přesnými časy i u univerzálních sledovacích služeb; když ji žádná nemá, ukáže kroky České pošty, každý jen s datem. Kroky jsou anglicky, jak je píše Česká pošta. Peek ukáže poštu nebo místo Balíkovny, kde zásilka čeká (název, ne adresu), a hmotnost. Odhad doručení od České pošty nemá a PSČ, údaje o dobírce ani počet kusů neukazuje. Když se stav změní, může ti poslat upozornění.

## Časté otázky ke sledování České pošty

### Jak sledovat zásilku České pošty?

Zadej podací číslo ve Sledování zásilek na postaonline.cz, v aplikaci Pošta Online nebo v Peeku. Na postaonline.cz jich najednou zadáš až 20. Balíky Balíkovny sleduješ i na balikovna.cz.

### Jak dlouho čeká zásilka na poště?

Obvykle 15 dní, pokud ji odesílatel nezkrátil na 10, a zdarma si ji můžeš prodloužit až na měsíc. V Balíkovně na poště nebo u partnera čeká balík 7 kalendářních dní, v boxu 2 pracovní dny, a to prodloužit nejde.

### Proč Česká pošta píše, že zásilka není v evidenci?

Hláška „Zásilka tohoto podacího čísla není v evidenci.“ znamená, že Česká pošta číslo nezná. Zkontroluj ho. U zásilky ze zahraničí ukáže stavy až podle dat od zahraniční pošty nebo poté, co zásilka dorazí do Česka.

### Jaký je kontakt na Českou poštu a Balíkovnu při sledování zásilky?

Zákaznická linka České pošty je +420 210 123 456 v pracovní dny od 8:00 do 18:00 (e-mail info@cpost.cz). Pro problémy s balíkem nebo boxem v Balíkovně volej linku Balíkovny 218 218 218 (případně technickou podporu uvedenou na boxu).

### Můžu balík vyzvednout bez občanky?

Většinu vnitrostátních balíků ano: stačí šestimístný kód pro vyzvednutí a jméno adresáta nebo číslo zásilky. Neplatí to pro doporučené a cenné zásilky.

:::sources
- [Česká pošta: Sledování zásilek](https://www.postaonline.cz/trackandtrace) – 1 až 20 čísel, příklad DR1234567890E, česky i anglicky, hláška pro neznámé číslo
- [Česká pošta: Seznam druhů zásilek s možností sledování](https://www.ceskaposta.cz/rady-a-tipy/pri-podani/seznam-druhu-zasilek-s-moznosti-sledovani) – stavba podacího čísla, kde ho najdeš, zásilky ze zahraničí
- [Česká pošta: Co dělat, když vás doručovatel nezastihne doma](https://www.ceskaposta.cz/rady-a-tipy/pri-dodani/co-delat-kdyz-vas-dorucovatel-nezastihne-doma) – opakované doručení, prodloužení, jiná pošta, doslání
- [Česká pošta: Online služby při dodání](https://www.ceskaposta.cz/sluzby/online-sluzby/online-sluzby-pri-dodani) – 15 a 10 dní, tři pracovní dny, Balík Do ruky
- [Česká pošta: Avizování balíkových zásilek](https://www.ceskaposta.cz/sluzby/ostatni-sluzby/avizovani-balikovych-zasilek) – SMS a e-maily zdarma, kontakty od obchodníka
- [Česká pošta: Výdej balíku na kód](https://www.ceskaposta.cz/sluzby/zasilky-cr/ostatni-zasilky/vydej-baliku-na-kod) – šestimístný kód, bez průkazu, výjimky
- [Česká pošta: Reklamace a stížnosti](https://www.ceskaposta.cz/rady-a-tipy/jine/reklamace-a-stiznosti) – lhůty, podací stvrzenka, poškození při doručení, ČTÚ a ČOI
- [Česká pošta: FAQ k celnímu řízení](https://www.ceskaposta.cz/sluzby/zasilky-zahranicni/celni-rizeni/faq-caste-otazky-k-celnimu-rizeni) – formulář, výzva e-mailem, SMS nebo dopisem, platba při převzetí
- [Česká pošta: Pomoc klientům](https://www.ceskaposta.cz/kontakty/pomoc-klientum) – linka, hodiny, Eliška, e-mail
- [Česká pošta: Kontakty](https://www.postaonline.cz/kontakty) – +420 210 123 456, pracovní dny 8:00 až 18:00
- [Česká pošta: Bezpečnostní upozornění](https://www.ceskaposta.cz/o-spolecnosti/bezpecnostni-informace/bezpecnostni-upozorneni) – podvodné zprávy, spam@cpost.cz
- [Česká pošta: Služba ParcelHistory](https://b2c.cpost.cz/) – česká hláška pro druh zásilek, u kterého stavy nezobrazuje
- [Česká pošta: Hledání zásilky podle čárového kódu (ePOST)](https://www.postaonline.cz/podanionline/ePOST-dokumentace/1231hledani.html) – české názvy stavů
- [Balíkovna: Časté dotazy](https://www.balikovna.cz/cs/faq) – 7 dní a 2 pracovní dny, bez prodloužení, boxy, 218 218 218, doručení na adresu
- [Balíkovna: Úvodní stránka](https://www.balikovna.cz/cs/) – doručení druhý den, sítě boxů
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – co Peek čte
:::
