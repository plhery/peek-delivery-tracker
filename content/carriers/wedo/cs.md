---
title: Sledování zásilky One by Allegro CZ (WE|DO) — stavy a boxy
description: Sleduj zásilky One by Allegro CZ (WE|DO, IN TIME, Uloženka). 11místná čísla, úložní doba One Box 48 h a One Point 5 dní, dobírka a kontakt.
slug: sledovani-zasilky-one-by-allegro-cz
published: 2026-11-23
updated: 2026-11-23
---

One by Allegro CZ je česká dceřiná přepravní společnost skupiny Allegro (web [onedelivery.cz](https://onedelivery.cz/) patří společnosti Allegro Retail a.s.). Doručuje objednávky z allegro.cz i z jiných e-shopů. Příjemcům nabízí tři způsoby doručení: **One Box** (síť více než 5 244 výdejních boxů včetně partnerských AlzaBoxů a OX Boxů), **One Point** (1 960 výdejních míst v obchodech) a **One Courier** (kurýrní doručení na adresu flotilou přes 350 vozů). Vyhledávání dopravců v Peeku ji najde i jako WE|DO, IN TIME nebo Uloženka.

Stav své zásilky zjistíš přímo na stránce [Sledování zásilky One by Allegro](https://onedelivery.cz/sledovani-zasilky) nebo vložením 11místného sledovacího čísla do pole nahoře.

## Formát sledovacího čísla One by Allegro CZ (WE|DO)

České zásilky One by Allegro / WE|DO používají jednotné **11znakové alfanumerické číslo**:

| Formát (smyšlený) | O co jde |
| --- | --- |
| `2BM48291736` | 1 číslice + 1 písmeno + 1 písmeno nebo číslice + 8 číslic (celkem 11 znaků) — běžné sledovací číslo One by Allegro CZ (WeDo). |
| `48P48291736` | 2 číslice + 1 písmeno + 8 číslic (celkem 11 znaků) — druhá standardní varianta českých štítků One by Allegro (WeDo). |

Kódy začínající na `7D` + číslice nebo `7X` + číslice do tohoto pravidla nepatří; ostatní odpovídající 11znaková čísla Peek spolehlivě rozpozná jako **One by Allegro CZ**. Stejně tak Peek automaticky rozbalí odkazy `onedelivery.cz/sledovani-zasilky?numer=...`, `onedelivery.cz/?numer=...` i adresy `trace.wedo.cz/index.php?orderNumber=...` a `trace.intime.cz/index.php?orderNumber=...`. Polské zásilky Allegro One mají vlastní rozhraní — viz [Allegro One](carrier:allegro-one) a přehled [formátů sledovacích čísel](guide:tracking-number-formats) (v angličtině).

## Co znamenají stavy zásilky One by Allegro CZ

Veřejné sledovací API One by Allegro CZ vrací anglické stavy, každý s časem a posunem od UTC. Nejčastější jsou tyto:

| Stav z API One by Allegro CZ | Co to znamená česky |
| --- | --- |
| `Parcel has been prepared by the sender` | Odesílatel vytvořil štítek a připravil zásilku; kurýr ji zatím nepřevzal. |
| `Parcel has been picked up by the courier` | Kurýr One by Allegro CZ zásilku vyzvedl u odesílatele. |
| `Parcel has been dispatched to its destination point` | Zásilka byla odeslána směrem k cílovému regionu nebo výdejnímu místu. |
| `Parcel has been accepted at the sorting center` | Zásilka byla přijata na třídicím centru. |
| `Parcel has been accepted at the branch` | Zásilka dorazila na cílové depo odpovědné za doručení. |
| `Parcel has been released for delivery` | Zásilka je naložena u kurýra k dnešnímu doručení na adresu nebo k uložení do boxu či výdejního místa. |
| `Parcel is awaiting pick-up` | Balík čeká na vyzvednutí v One Boxu nebo na One Pointu — zkontroluj SMS, e-mail či aplikaci kvůli vyzvedávacímu kódu. |
| `Parcel has been delivered` | Zásilka byla předána příjemci nebo vyzvednuta z boxu či výdejního místa. |

Pokud se v datech objeví nestandardní poznámka, která by mohla obsahovat osobní údaje příjemce, Peek její text nahradí obecným `Tracking update`, přičemž zachová přesný čas skenu.

## Doručení: One Box, One Point a One Courier

Podmínky uložení, vyzvednutí i platby dobírky jsou přehledně popsané přímo na [onedelivery.cz](https://onedelivery.cz/):

- **One Box ([onedelivery.cz/one-box](https://onedelivery.cz/one-box)):** Přes 5 244 samoobslužných boxů (vlastní One Boxy, partnerské AlzaBoxy a OX Boxy) dostupných **24/7**. Zásilka čeká v boxu **48 hodin** od dodání a lhůtu nelze prodloužit; k otevření stačí zadat na displeji telefonní číslo příjemce a číselný kód zaslaný přes SMS, e-mail, aplikaci One by Allegro nebo Allegro (aplikace není nutná). Dobírku zaplatíš přes platební odkaz v oznámení nebo kartou na terminálu One Boxu.
- **One Point ([onedelivery.cz/one-point](https://onedelivery.cz/one-point)):** Síť 1 960 výdejních míst v provozovnách s vyzvednutím podle otevírací doby. Standardní úložní doba je **5 pracovních dní** s možností bezplatného prodloužení až o další **3 pracovní dny** přes zákaznický servis; během předvánoční hlavní sezóny se úložní doba na One Pointech zkracuje z 5 na **3 dny** (ve One Boxech zůstává beze změny). Limity pro One Point jsou max. **20 kg**, nejdelší hrana **1,2 m** a objem **0,2 m³**. Dobírku uhradíš v hotovosti nebo kartou (pokud má pobočka terminál).
- **One Courier ([onedelivery.cz/one-courier](https://onedelivery.cz/one-courier)):** Standardní doručení probíhá **druhý pracovní den (`D+1`)** od vyzvednutí u odesílatele, v pracovní dny mezi **8:00 a 18:00** k prvním uzamykatelným dveřím (kurýr nemá povinnost vynášet balík do patra; doručení do P.O. Boxu není možné). V den doručení přijde SMS, e-mail či notifikace v aplikaci. Dobírku zaplatíš kurýrovi v hotovosti (v přesné výši) nebo kartou bez příplatku. Termín doručení nebo přesměrování menší zásilky na výdejní místo změníš v aplikaci One by Allegro či přes zákaznický servis; změnu adresy řeší zákaznický servis.

Při potížích s nevyzvednutou nebo stojící zásilkou pomohou i obecné návody (v angličtině) [parcel lockers](guide:parcel-lockers), [uncollected parcel](guide:uncollected-parcel) a [tracking not updating](guide:tracking-not-updating). Další české přepravce v Peek najdeš na stránkách [Zásilkovna / Packeta](carrier:packeta), [PPL](carrier:ppl) a [Česká pošta](carrier:ceska-posta).

## Sledování One by Allegro CZ v aplikaci Peek

Stačí vložit 11znakové číslo One by Allegro CZ nebo odkaz z `onedelivery.cz` či `trace.wedo.cz` a Peek dopravce **One by Allegro CZ** rozpozná okamžitě sám. Pokud někdy potřebuješ dopravce změnit, použij při přidávání tlačítko **Change** nebo na detailu zásilky **Change carrier** (rozhraní Peek je v angličtině).

Peek stahuje stavy přímo z veřejného rozhraní `edge.onedelivery.cz` přes otevřený [adaptér One by Allegro CZ v universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/wedo), včetně automatického opakování při výpadku sítě. Ukládá přesné časy včetně časového pásma a čas doručení, zatímco doplňkové textové poznámky zahazuje kvůli soukromí. Veřejné API neposkytuje předpokládané datum doručení, město depa ani hmotnost balíku.

Peek převádí stavy One by Allegro CZ do fází: **Label created** (připraveno odesílatelem), **Received by carrier** (vyzvednuto kurýrem), **In transit** (na cestě, na třídicím centru nebo na pobočce), **Out for delivery** (předáno k doručení), **Ready for pickup** (připraveno k vyzvednutí v boxu či na výdejním místě) a **Delivered** (doručeno). Čte i další stavy Allegro One, například `Parcel has been returned to the sender`.

Dokud máš zásilku v Peeku otevřenou, nebo v den doručení se zapnutými upozorněními, kontroluje ji Peek nejvýš každých 10 minut; v den, kdy One by Allegro CZ ukáže `Parcel has been released for delivery`, každé 2 minuty od 6:00 českého času, dokud odpovídá samotné sledování One by Allegro CZ. Jinak ji kontroluje jednou za hodinu. Po 12 hodinách ve stavu „Label created“ nebo „Received by carrier“, nebo po dvou dnech bez novinek, ji kontroluje jednou za hodinu, ať ji sleduješ jakkoli, a po sedmi dnech ve stavu „Label created“ každých 6 hodin. Když sledování One by Allegro CZ neodpovídá a Peek zásilku sleduje jen přes sledovací služby, kontroluje ji jednou za hodinu, po klidném dni každé 3 hodiny a v den rozvozu nejvýš každých 15 minut.

Když je poslední sken starý čtyři dny a zásilka je pořád ve stavu „Label created“, „Received by carrier“ nebo „In transit“, Peek ji označí dnem toho skenu, třeba „No tracking update since Mon 12 Oct“. Na stránce zásilky pak stojí „Parcels often go quiet on long flights, at customs or over a weekend. Peek keeps asking One by Allegro CZ.“ s odkazem „Get help from One by Allegro CZ“ na kontaktní stránku One by Allegro CZ. Po třech týdnech bez nového skenu Peek napíše „Nothing new for over three weeks. If it still hasn’t arrived, ask the seller. Peek keeps asking One by Allegro CZ.“

V nastavení upozornění si v Peek můžeš vybrat úroveň **Every scan**, **Important steps** nebo **Delivery only**.

## Kontakt na One by Allegro CZ

- **Kontaktní formulář pro zásilky:** [onedelivery.cz/kontakt](https://onedelivery.cz/kontakt) (tlačítko *Dotaz na zásilku* pro kupující i prodejce — ztracený vyzvedávací kód, prodloužení úložní doby na One Pointu o 3 pracovní dny nebo změna doručovací adresy)
- **Oficiální sledování:** [onedelivery.cz/sledovani-zasilky](https://onedelivery.cz/sledovani-zasilky)
- **Přehled služeb:** [One Box](https://onedelivery.cz/one-box), [One Point](https://onedelivery.cz/one-point) a [One Courier](https://onedelivery.cz/one-courier)
- **Hlavní web:** [onedelivery.cz](https://onedelivery.cz/)

## Často kladené otázky

### Jsou WE|DO, IN TIME a One by Allegro CZ stejný dopravce?

Peek je bere jako jednoho dopravce: vyhledávání ho najde jako WE|DO, IN TIME i Uloženka a odkazy `trace.wedo.cz` a `trace.intime.cz` i 11znaková čísla sleduje pod **One by Allegro CZ**. Web `onedelivery.cz` historii těchto značek nepopisuje.

### Jak dlouho čeká balík ve One Boxu a na výdejním místě One Point?

Ve výdejních boxech **One Box** (včetně AlzaBoxů a OX Boxů) máš na vyzvednutí **48 hodin**. Na výdejních místech **One Point** je standardní úložní doba **5 pracovních dní** (s možností bezplatného prodloužení o 3 pracovní dny přes zákaznický servis), v hlavní předvánoční sezóně zkrácená na **3 dny**.

### Mohu zaplatit dobírku u One by Allegro CZ platební kartou?

Ano. U kurýra One Courier zaplatíš v hotovosti (ideálně přesnou částkou) nebo kartou bez poplatku, ve One Boxu přes platební odkaz v oznámení či na terminálu boxu a na One Pointu v hotovosti nebo kartou, pokud má výdejní místo platební terminál.

### Co dělat, když ztratím kód k vyzvednutí z One Boxu nebo One Pointu?

Kód chodí v SMS, e-mailu i v aplikacích One by Allegro a Allegro. Pokud ho nikde nemůžeš najít, napiš zákaznickému servisu přes formulář *Dotaz na zásilku* na [onedelivery.cz/kontakt](https://onedelivery.cz/kontakt).

:::sources
- [One by Allegro CZ (onedelivery.cz)](https://onedelivery.cz/)
- [One by Allegro CZ — Sledování zásilky](https://onedelivery.cz/sledovani-zasilky)
- [One by Allegro CZ — One Box](https://onedelivery.cz/one-box)
- [One by Allegro CZ — One Point](https://onedelivery.cz/one-point)
- [One by Allegro CZ — One Courier](https://onedelivery.cz/one-courier)
- [One by Allegro CZ — Kontakt](https://onedelivery.cz/kontakt)
- [Adaptér One by Allegro CZ v universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/wedo)
:::
