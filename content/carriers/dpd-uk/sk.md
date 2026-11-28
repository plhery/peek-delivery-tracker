---
title: Sledovanie DPD UK: stavy, odberné miesta Pickup a kontakt
description: Sledujte zásielku DPD UK podľa čísla balíka alebo lístka o nedoručení: význam stavov, 1-hodinové okná Predict, vyše 6 000 Pickup miest a 0121 275 0500.
slug: dpd-uk-tracking
published: 2026-11-28
updated: 2026-11-28
---

DPD UK (súčasť skupiny Geopost a La Poste) doručuje balíky po celom Spojenom kráľovstve pre internetové obchody. Zásielku môžete sledovať na [sledovacej stránke DPD](https://track.dpd.co.uk/) zadaním čísla balíka (pozorované majú 14 číslic, mnohé sa začínajú na `1550` alebo `1597`) alebo čísla z lístka „sorry we missed you“ spolu s PSČ, prípadne vložte číslo balíka do poľa vyššie. V deň doručenia posiela služba DPD Predict jednohodinové časové okno doručenia cez SMS a e-mail a umožňuje presmerovať zásielku k susedovi, na bezpečné miesto alebo do jedného z viac ako 6 000 odberných miest DPD Pickup.

## Kde nájdem svoje sledovacie číslo DPD?

Číslo má obchod, ktorý balík odoslal. Môžete tiež ťuknúť na odkaz v e-maile alebo SMS od DPD. Ak vodič nechal lístok „sorry we missed you“, číslo je vedľa čiarového kódu a sledovacia stránka DPD prijíma aj číslo z tohto lístka. Stratili ste ho? Pozrite [ako nájsť sledovacie číslo (v angličtine)](guide:find-tracking-number).

Vlastné stránky DPD neuvádzajú formát čísla. Čísla balíkov, ktoré sa objavujú v sledovaní DPD UK, majú 14 číslic a mnohé sa začínajú na `1550` alebo `1597`; stránka DPD môže za nimi vypísať kontrolný znak.

| Číslo (vymyslené) | Čo je to |
| --- | --- |
| `15504829173640` | Číslo balíka DPD UK: 14 číslic začínajúcich na `1550` (alebo `1597`). |
| `15504829173640H` | To isté číslo s kontrolným znakom DPD na konci. |
| `19482917364058` | Akýchkoľvek iných 14 číslic: môže ísť o DPD UK, inú krajinu DPD alebo iného prepravcu. |

Aj iné krajiny DPD používajú 14 číslic, rovnako ako [DHL eCommerce UK](carrier:dhl-ecommerce-uk), SEUR a BRT, takže samotné číslice nedokazujú, kto má vašu zásielku. Správy od DPD áno: jeho SMS teraz prichádzajú od „DPD UK“ (alebo „DPD Local“ či „DPDgroup UK“) s názvom obchodu v texte. DPD Local je sesterská značka DPD. V Írsku idú zásielky cez [DPD Ireland](carrier:dpd-ie), ktoré má vlastné čísla.

## Vysvetlenie stavov sledovania DPD

Sledovanie DPD UK je iba v angličtine. Toto sú riadky, ktoré zobrazuje väčšina zásielok, v poradí:

| DPD uvádza | Čo to znamená pre vás | Peek zobrazuje |
| --- | --- | --- |
| `We've received your order details, but have not yet received your parcel` | Obchod objednal doručenie. DPD zatiaľ balík nemá. | „Label created“ |
| `We've received your order details, and we're expecting your parcel shortly` | Štítok je objednaný a DPD očakáva, že odosielateľ odovzdá balík na odbernom mieste DPD Pickup Shop. | „Label created“ |
| `Your parcel has been dropped off at a DPD Pickup Shop` | Balík bol podaný na odbernom mieste DPD Pickup Shop. | „Received by carrier“ |
| `We have your parcel and it's on its way to our depot`, `We have your parcel and it's on its way to you` | DPD ho prevzalo a vezie ho do depa alebo smerom k vám. | „In transit“; riadok o depe zobrazí „Received by carrier“, ak mu nepredchádzalo podanie v DPD Pickup Shop |
| `Your parcel has arrived at our depot`, `Your parcel is at our depot` | V depe DPD, kde sa triedi. | „In transit“ |
| `Your parcel is at our depot ready for delivery` | V depe, pripravený na rozvozovú trasu. Neskôr ten istý riadok znie `Your parcel is at our depot`. | „In transit“ |
| `A notification has been sent to advise you when the delivery will take place` | DPD vám poslalo SMS alebo e-mail s dňom doručenia alebo jednohodinovým oknom. | Predchádzajúci stav zostáva |
| `As requested, your parcel will now be delivered on Thu 05 November 2026 at 11:00` | Zvolili ste iný deň doručenia, niekedy aj s hodinou. Deň a hodina sa líšia. | Predchádzajúci stav zostáva |
| `Your parcel will be with you today`, `Your parcel will be with you today between 14:18 and 15:18` | Dnes na doručení, s hodinou, kedy DPD očakáva príchod, ak ju uvedie. Časy sa líšia. | „Out for delivery“ |
| `As requested, your parcel will now be left in your selected safe place` | Zvolili ste (alebo máte nastavené v aplikácii DPD) bezpečné miesto a vodič tam balík nechá. | Predchádzajúci stav zostáva |
| `We missed you. Your driver won't be back today but we'll be back Wed 28 October with your parcel` | Vodič nedokázal doručiť a vráti sa v deň, ktorý DPD uvádza; deň sa líši. Riadok DPD uvádza aj meno vodiča, ktoré Peek vynecháva. | „Delivery attempted“ |
| `Your parcel has been delivered` | Doručené. Nadpis dopĺňa čas a deň. | „Delivered“ |
| `Your parcel is waiting for you at home (left in safe place)` | Doručené a ponechané na vašom bezpečnom mieste. | „Delivered“ |
| `Your delivery has been cancelled` | Odosielateľ doručenie zrušil. DPD vás žiada kontaktovať obchod, ktorý uvádza. | „Needs attention“ |

`We have X-rayed your parcel as part of our normal security procedures` je rutinná kontrola: netreba nič robiť („In transit“ v Peeke). Pri zásielke, ktorú DPD UK posiela do zahraničia, riadok `Your parcel has left our sortation facility and is on it's onwards journey` (pravopis DPD) znamená, že opustila Spojené kráľovstvo, a história potom obsahuje riadky doručujúceho partnera: `The parcel is in transit to its final destination`, `The parcel is undergoing customs clearance` a `Duties and Taxes paid` („At customs“ v Peeke), `The parcel has now cleared customs`, `The parcel has arrived at the delivery depot`, `The parcel is on the vehicle for delivery` („Out for delivery“) a `The parcel has been delivered` („Delivered“). Riadky bez fázy v zátvorke sa zobrazujú ako „In transit“.

## Možnosti doručenia DPD, Pickup miesta a zmeškané doručenia

**Predict.** DPD posiela jednohodinové okno doručenia cez SMS a e-mail. Prichádza v deň doručenia po tom, čo vodič naloží dodávku, zvyčajne do 12:00. Živá mapa ho potom zúži na 15-minútový interval. Samotný časový slot sa nedá zmeniť.

**Zmena doručenia.** Večer predtým a v deň doručenia vám SMS, e-mail alebo aplikácia DPD umožnia zvoliť iný deň, suseda, bezpečné miesto alebo DPD Pickup Shop, prípadne povýšiť službu na doručenie do 10:30 alebo do 12:00, popoludní či v sobotu alebo v nedeľu. Ak odosielateľ vyžiadal podpis, niektoré z týchto možností sa neponúkajú. Ak chcete zásielku poslať na [inú adresu (v angličtine)](guide:change-delivery-address), kontaktujte obchod.

**Neboli ste doma.** DPD automaticky skúsi suseda. Jeho podmienky definujú suseda ako niekoho, kto býva alebo pracuje do 50 metrov pešo. Vopred môžete určiť preferovaného suseda alebo bezpečné miesto. Inak vodič zanechá lístok a pomocou referenčného čísla a PSČ si môžete zvoliť nový dátum, bezpečné miesto, suseda alebo Pickup Shop. Stránky DPD neuvádzajú, koľko pokusov vykonáva. Ak ste boli doma a vodič neprišiel, sledovacia stránka DPD ukazuje, kde vodič zanechal lístok „sorry we missed you“, a umožní vám zvoliť opätovné doručenie alebo vyzdvihnutie; vodič sa v ten istý deň nemôže vrátiť. Sledovanie uvádza doručené, ale neviete balík nájsť? Najprv skontrolujte svoje bezpečné miesto a susedov, keďže DPD skúša suseda automaticky, a potom pozrite [doručené, ale neprijaté (v angličtine)](guide:delivered-not-received).

**Odberné miesta Pickup.** DPD má viac ako 6 000 odberných miest Pickup vrátane pobočiek Sainsbury's, Post Office, Co-op, Morrisons, Argos a Matalan. Balík, ktorý tam presmerujete, býva pripravený na ďalší deň. Prineste si dva doklady totožnosti: doklad s fotografiou a niečo s vašou adresou, napríklad faktúru za energie. Sledovacia stránka DPD uvádza dátum, dokedy si balík musíte vyzdvihnúť; DPD nezverejňuje pevný počet dní. Aplikácia DPD ponúka aj doručenie do balíkomatu.

## Balík DPD nedoručený, oneskorený alebo poškodený

**Meškania.** Dodacie lehoty DPD sú orientačné. Ak je vo vašej oblasti meškanie, sledovacia stránka ho môže zobraziť s novým odhadovaným dátumom a [stránka Service Disruption DPD](https://www.dpd.co.uk/content/products_services/uk_issues.jsp) overí vaše PSČ. Ak sa celé dni nič nedeje, prečítajte si [prečo sa sledovanie prestáva aktualizovať (v angličtine)](guide:tracking-not-updating) a opýtajte sa obchodu.

**Stratený alebo poškodený.** Zmluvu s DPD má obchod, takže reklamáciu podáva on. Podľa podmienok DPD musí odosielateľ nahlásiť stratu alebo poškodenie do 14 dní od odoslania a uplatniť nárok do jedného roka. Uschovajte obal a urobte fotografie. Pri stratenom balíku vás DPD môže požiadať o podpísanie listu potvrdzujúceho, že balík nikdy nedorazil.

**Nedoručený.** Balík, ktorý DPD nedokáže doručiť, môže vrátiť po najmenej 5 kalendárnych dňoch.

**Dovozné poplatky.** Balíky spoza hraníc Spojeného kráľovstva môžu podliehať DPH alebo clu, ktoré vyúčtuje doručovacia spoločnosť. GOV.UK uvádza, že táto spoločnosť balík zvyčajne podrží približne 3 týždne a pri nezaplatení ho vráti odosielateľovi.

> DPD vás nikdy nežiada zaplatiť „redelivery fee“ cez odkaz v e-maile, SMS alebo iMessage. Správa z e-mailovej adresy alebo náhodného čísla namiesto odosielateľa „DPD“ je podvod. Podozrivé SMS prepošlite na číslo 7726. DPD uvádza, že jeho automatické upozornenia chodia cez SMS, nie iMessage, a jeho odkazy používajú iba vlastné domény dpd.co.uk, dpdlocal.co.uk a dpdgroup.co.uk: podobne vyzerajúce adresy sú podvody. Podozrivé e-maily hláste na report@phishing.gov.uk.

## Kontaktné číslo a zákaznícka podpora DPD UK

Stránka dpd.co.uk uvádzala 14. novembra 2026 tieto kontakty:

- **Telefón:** 0121 275 0500, pondelok až piatok 8:00 až 18:30, sobota 8:00 až 16:00, nedeľa 9:00 až 15:00, okrem štátnych sviatkov. DPD predpokladá čakanie 5 až 10 minút. Pripravte si číslo balíka a PSČ.
- **Čet:** na [kontaktnej stránke](https://www.dpd.co.uk/content/how-can-we-help/contact.jsp), o ktorej uvádza, že beží nepretržite 24/7; na spustenie zadajte referenčné číslo a doručovacie PSČ.
- **Aplikácia DPD:** čet alebo telefonát cez tlačidlá pod mapou sledovania; DPD uvádza, že hovor z aplikácie ide na začiatok poradia.
- **Pošta:** Customer Services, DPD UK, Roebuck Lane, Smethwick, B66 1BY.

## Sledovanie zásielok DPD UK pomocou Peeku

[Peek](/) rozpozná 14-ciferné číslo DPD, ale tento tvar je zdieľaný, takže Peek sa pýta prepravcov, ktorí ho používajú, či číslo poznajú: najprv DPD UK, ak sa číslo začína na `1550` alebo `1597`, inak najprv prepravcov pôsobiacich vo vašej krajine. Keď ho DPD UK pozná, riadok prepravcu zobrazí DPD UK „has this parcel“ a Peek zásielku sleduje tam. Ak riadok zobrazí „not found yet · Peek keeps checking“ alebo pomenuje iného prepravcu, stlačte „Change“ a vyberte „DPD UK“. Odkaz z track.dpd.co.uk alebo tracking.dpd.co.uk s parametrom `reference=` alebo `parcelNumber=` určí DPD UK okamžite. Referencie objednávok a zvozov, ktoré vyžadujú PSČ, sa nesledujú.

Skenovania DPD UK neobsahujú časovú zónu: Peek ich číta podľa britského času, zobrazuje ich vo vašej časovej zóne a uvádza vlastnú históriu DPD. Na peektracker.com a v aplikáciách pre iPhone a Android história končí textom „Times are in your time zone:“ a jej názvom. Stavy si zachovávajú anglické znenie DPD a zrušené doručenie sa zobrazuje ako „Needs attention“. Peek vás môže upozorniť pri zmene stavu.

DPD neuvádza miesto svojich skenovaní a Peek nečíta očakávaný deň ani okno Predict od DPD. Vynecháva vaše údaje, referencie a fotografie z doručenia. Peek zobrazuje obchod, ktorý objednal doručenie, ako „From …“, podľa názvu na stránke DPD, okrem balíka podaného odosielateľom v DPD Pickup Shop: účet, ktorý objednal tento štítok, balík neodoslal (pri vratnom štítku ide o obchod, kam sa balík vracia). DPD po niekoľkých mesiacoch čísla opätovne používa, takže staré číslo môže zobrazovať novšiu zásielku.

Kým máte zásielku otvorenú, alebo v deň doručenia so zapnutými upozorneniami, Peek kontroluje najviac každých 10 minút, alebo od 6:00 britského času v deň, keď DPD uvedie zásielku na doručení, najviac každé 2 minúty, kým odpovedá vlastné sledovanie DPD UK (cez sledovacie služby najviac každých 15 minút); inak každú hodinu. Po dvoch dňoch bez noviniek (po 12 hodinách, kým zásielka stále zobrazuje „Label created“ alebo „Received by carrier“) kontroluje Peek každú hodinu. Jeho upozornenia môžu pokrývať každé skenovanie, len dôležité kroky (doručenie, vyzdvihnutie, colnica, neúspešné pokusy a vratky) alebo len deň doručenia. Nemôže za vás zmeniť doručenie: použite sledovaciu stránku alebo aplikáciu DPD.

## Otázky o sledovaní DPD UK

### Ako dlho trvá doručenie cez DPD UK?

Záleží od služby, ktorú si zvolil obchod. DPD Next Day cieli na nasledujúci pracovný deň; Normanské ostrovy, ostrov Man a súostrovie Scilly trvajú štandardne dva dni.

### Ako môžem sledovať vodiča DPD UK naživo?

V deň doručenia vám SMS alebo e-mail DPD Predict pošle jednohodinové okno, meno vodiča, vaše miesto v jeho poradí a živú mapu, ktorá spresní odhad na 15-minútový interval.

### Ako dlho drží DPD balík v Pickup shope alebo v depe?

DPD UK nezverejňuje pevnú lehotu uloženia v Pickup shope: sledovacia stránka zobrazuje dátum, dokedy si balík musíte vyzdvihnúť. Ak DPD nedokáže balík doručiť, môže ho vrátiť odosielateľovi po najmenej 5 kalendárnych dňoch.

### Aké je telefónne číslo zákazníckej podpory DPD UK?

Zavolajte na 0121 275 0500 (pondelok až piatok 8:00 až 18:30, sobota 8:00 až 16:00, nedeľa 9:00 až 15:00, okrem štátnych sviatkov), použite nepretržitý čet 24/7 na kontaktnej stránke DPD s referenčným číslom a PSČ, alebo zavolajte z aplikácie DPD, čím sa dostanete na začiatok poradia.

### Je DPD súčasťou Royal Mail?

Nie. DPD UK prevádzkuje spoločnosť DPDgroup UK Ltd a patrí do skupiny Geopost, ktorá je súčasťou francúzskej skupiny [La Poste](carrier:la-poste).

:::sources
- [DPD UK: Help](https://www.dpd.co.uk/content/how-can-we-help/index.jsp) – číslo balíka, lístok o nedoručení, jednohodinové okno, sused, bezpečné miesto, odberné miesta Pickup, doklady, zmeškané doručenie, kde vodič zanechal lístok, zmena adresy, spoločnosť (prečítané 8. novembra 2026)
- [DPD UK: Contact Us](https://www.dpd.co.uk/content/how-can-we-help/contact.jsp) – telefón, hodiny a očakávané čakanie, čet, čet v aplikácii a hovory z aplikácie na začiatku poradia (prečítané 14. novembra 2026)
- [DPD UK: Predict & Follow My Parcel](https://www.dpd.co.uk/content/products_services/predict_fmp.jsp) – jednohodinové okno, 15-minútový interval, meno vodiča, možnosti doručenia, povýšenie do 10:30 alebo 12:00, popoludní, v sobotu alebo v nedeľu (prečítané 14. novembra 2026)
- [DPD UK: Follow My Parcel](https://track.dpd.co.uk/) – sledovacie polia, dátum vyzdvihnutia, meškania, dovozné poplatky, znenia stavov
- [DPD UK: Cyber Security, phishing and smishing](https://www.dpd.co.uk/content/about_dpd/phishing.jsp) – mená odosielateľov SMS, varovanie pred podvodmi, 7726, upozornenia cez SMS, domény používané v odkazoch, report@phishing.gov.uk (prečítané 8. novembra 2026)
- [DPD UK: DPD Pickup](https://www.dpd.co.uk/content/products_services/dpd_pickup.jsp) – presmerovanie počas prepravy na odberné miesto Pickup
- [DPD UK: The DPD app](https://www.dpd.co.uk/dpdapp/) – možnosti doručenia v aplikácii, balíkomat
- [DPD UK: App FAQs](https://www.dpd.co.uk/lp/app/index.html) – balíky DPD UK a DPD Local, telefonát a čet v aplikácii
- [DPD UK: Standard Terms & Conditions of Carriage](https://www.dpd.co.uk/pdf/dpd-stcc.pdf) – zákazník a príjemca, sused, odhady, lehoty na reklamácie (prečítané 8. novembra 2026)
- [DPD UK: Complaints Charter](https://www.dpd.co.uk/pdf/dpd-complaints-charter.pdf) – lehoty sťažností, hodiny četu, poštová adresa
- [DPD UK: Return, Disposal and Destruction of Goods Policy](https://www.dpd.co.uk/return-disposal-of-goods-policy.jsp) – vrátenie po 5 kalendárnych dňoch (prečítané 8. novembra 2026)
- [DPD UK: UK Delivery Services](https://www.dpd.co.uk/content/products_services/uk_delivery_services.jsp) – Next Day, 10:30 a 12:00, dodacie časy pre ostrovy
- [DPD UK: Service Disruption](https://www.dpd.co.uk/content/products_services/uk_issues.jsp) – overenie PSČ pre dotknuté oblasti
- [GOV.UK: Tax and customs for goods sent from abroad](https://www.gov.uk/goods-sent-from-abroad/tax-and-duty) – vyúčtovanie od doručovacej spoločnosti, uloženie približne 3 týždne (prečítané 8. novembra 2026)
- [Geopost: About us](https://www.geopost.com/en/about-us/) – súčasť La Poste Groupe
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – čo Peek číta: čísla začínajúce na 1550 alebo 1597, rozpoznávanie, znenia stavov a ich kroky, oznámenia, názov obchodu (prečítané 8. novembra 2026)
:::
