---
title: China Post-tracking: nummer, statussen en wie bezorgt
description: Volg een China Post- of EMS-pakket eindigend op CN: UPU S10-nummertypen (L, R, C, E, U), vertaalde Chinese scans, overdracht aan lokale post en 11183/11185.
slug: china-post-tracking
published: 2026-11-28
updated: 2026-11-28
---

Volg een pakket van China Post of EMS dat eindigt op `CN` (twee letters, negen cijfers en `CN`, het UPU S10-formaat) op ems.com.cn of in het vak hierboven. Omdat de UPU één S10-nummer per zending toestaat, bezorgt de nationale post in jouw land — zoals USPS in de VS, Royal Mail of Parcelforce in het VK, An Post in Ierland, Canada Post in Canada of Australia Post in Australië — onder precies datzelfde nummer zodra het pakket China heeft verlaten; voor formele navraag vereist China Post dat de afzender contact opneemt met zijn lokale postkantoor (telefoonnummers van China Post: 8610 11183 voor EMS, 8610 11185 voor Postal Business).

## China Post-trackingnummer: hoe het eruitziet

Een nummer van China Post bestaat uit twee letters, negen cijfers en `CN`: het internationale postale formaat van de Wereldpostunie (UPU). De eerste letter geeft aan wat voor soort zending het is. Met verzonnen cijfers:

| Nummer | Wat het is |
| --- | --- |
| `LT482917367CN` | L: een traceerbaar klein pakje (briefpost). |
| `RR482917367CN` | R: aangetekende briefpost. |
| `CP482917367CN` | C: een pakket. |
| `EA482917367CN` | E: EMS, de expresdienst van China Post. |
| `UA482917367CN` | U: een klein pakje met goederen "zonder klantgerichte tracking" ("without customer-oriented tracking"), in de woorden van de UPU. Reken niet op scans in je eigen land. |
| `ZS48291736701` | ZS, ZC of ZA gevolgd door 11 cijfers: wordt ook beantwoord door de tracking van China Post. |
| `LP48291057364829` | Geen China Post: een logistieke referentie van [Cainiao](carrier:cainiao) (AliExpress). |
| `YT4829105736482910` | Geen China Post, maar [YunExpress](carrier:yunexpress). |

Het laatste van de negen cijfers is een controlecijfer, dus een typefout maakt het nummer vrijwel altijd ongeldig. De UPU staat maar één zo'n internationaal postaal nummer per zending toe; daarom werkt hetzelfde nummer op de site van je eigen nationale post zodra het pakket je land bereikt.

Voor sommige zendingen vraagt de tracking van China Post om de laatste vier cijfers van een contacttelefoonnummer op de zending.

## Trackingstatussen van China Post uitgelegd

China Post schrijft zijn scans in het Chinees. Plaatsnamen staan tussen haken, hier weergegeven als `【…】`: een Chinees sorteercentrum of, in het buitenland, de naam van het land in het Chinees, zoals `【英国】` voor het VK of `【美国】` voor de VS. In de volgorde waarin een pakket ze tegenkomt:

| China Post zegt | Wat het voor jou betekent |
| --- | --- |
| `已揽收` | Aangenomen: China Post heeft het pakket. |
| `邮件到达【…】` | Aangekomen bij een postkantoor of sorteercentrum van China Post. |
| `邮件离开【…】，正在发往下一站` | Vertrokken uit dat kantoor naar de volgende halte. |
| `【…】退回，备注:安检退回` | Een sorteercentrum stuurde het terug bij de veiligheidscontrole en handelt het af voordat het verdergaat; het gaat niet terug naar de afzender. Peek toont "Needs attention". |
| `送交出口海关` | Aangeboden bij de Chinese uitvoerdouane. |
| `出口海关/留存待验` | Vastgehouden door de uitvoerdouane voor controle. |
| `出口海关/放行` | Vrijgegeven door de uitvoerdouane. |
| `【…】已出口直封` | Opgenomen in een directe exportzending bij een van China's internationale uitwisselingskantoren. |
| `已交承运商运输` | Overgedragen aan een vervoerder. |
| `航空公司接收`, `航空公司启运` | Een luchtvaartmaatschappij heeft het aangenomen en is er vervolgens mee vertrokken. |
| `飞机进港` | De vlucht is geland. |
| `到达寄达地处理中心` | Aangekomen bij het verwerkingscentrum in jouw land. |
| `送交境外进口海关` | Aangeboden bij de douane in jouw land. |
| `境外进口海关留存待验` | De douane houdt het daar vast voor controle. |
| `寄达地海关允许进口` | De douane heeft het vrijgegeven voor invoer. |
| `离开【…】处理中心` | Vertrokken uit dat verwerkingscentrum. |
| `已到达【…】投递局` | Aangekomen bij jouw bezorgkantoor. |
| `【…】安排投递`, `邮件正在派送中` | Onderweg voor bezorging. |
| `已试投/投递失败` | Je postbedrijf heeft geprobeerd te bezorgen, maar dat is niet gelukt. |
| `【…】已妥投`, `已签收` | Bezorgd. |
| `已退回` | Teruggestuurd naar de afzender. |

De laatste scans komen van het postbedrijf in jouw land en worden door China Post doorgestuurd, soms pas na bezorging. De eigen tracking van je nationale post geeft met hetzelfde nummer uit eerste hand informatie over het laatste stuk.

## Wie bezorgt pakketten van China Post in jouw land

China Post vliegt het pakket het land uit; in jouw land neemt de nationale post het over, via de douane tot aan je deur:

- **Verenigde Staten**: [USPS](carrier:usps).
- **Verenigd Koninkrijk**: [Royal Mail](carrier:royal-mail). Dit bedrijf loodst het pakket door de Britse douane en laat weten of btw of invoerrechten verschuldigd zijn. EMS-nummers (die met E beginnen) gaan in plaats daarvan naar [Parcelforce](carrier:parcelforce), de Britse EMS-uitvoerder.
- **Ierland**: [An Post](carrier:an-post). Sinds 1 november 2026 heft de EU een afhandelingsvergoeding van €2 per soort product; als de winkel die niet bij het afrekenen in rekening bracht, vraagt An Post erom, samen met eventuele invoerrechten en btw, vóór de bezorging.
- **Canada**: [Canada Post](carrier:canada-post). Zoek het nummer op in de Track-tool; als er niets verschijnt, vraagt Canada Post om de afzender een onderzoek te laten instellen via de post in het land van verzending.
- **Australië**: [Australia Post](carrier:australia-post). Het bedrijf zegt dat tracking voor zendingen uit het buitenland afhangt van de gekozen dienst in het land van verzending, en dat de douane tot 15 dagen in beslag kan nemen.

Bezorgpogingen, niet-thuisberichten en hoe lang een pakket op het postkantoor wacht, volgen de regels van je eigen postbedrijf; zie de pagina van dat postbedrijf en [gemiste bezorging (in het Engels)](guide:missed-delivery). Een pakket dat [niet wordt opgehaald (in het Engels)](guide:uncollected-parcel), gaat terug naar de afzender. Invoerrechten, belastingen en afhandelingskosten op pakketten van buiten je land staan in [pakketten bij de douane (in het Engels)](guide:customs).

## China Post-tracking werkt niet bij, pakket kwijt of retour

**Nog geen scans.** China Post toont niets zolang het pakket nog niet is gescand; daarvoor heeft de winkel alleen een verzendlabel aangemaakt. Vraag de winkel wanneer het pakket is afgegeven. Ondertussen toont Peek "Waiting for China Post". Als China Post het nummer na twee dagen nog steeds niet kent en geen enkele andere dienst heeft geantwoord, toont Peek "After two days, the carrier still doesn’t know this number. Check it, choose another carrier or delete the parcel."

**Stil na vertrek uit China.** Tussen de vlucht en de eerste scan van je eigen postbedrijf kunnen dagen zonder nieuws voorbijgaan. Controleer de tracking van je eigen post met hetzelfde nummer. Een nummer dat met U begint krijgt mogelijk nooit scans in je land. Meer in [waarom tracking stopt met bijwerken (in het Engels)](guide:tracking-not-updating) en [een pakket uit China volgen (in het Engels)](guide:tracking-from-china).

**Bezorgd maar niet ontvangen.** Je eigen postbedrijf heeft het bezorgd, dus begin bij de tracking en het afleverbewijs van dat postbedrijf en neem daarna contact op met de winkel. Zie [bezorgd maar niet ontvangen (in het Engels)](guide:delivered-not-received).

**Kwijt of beschadigd.** China Post zegt dat voor formeel onderzoek naar alle internationale zendingen de afzender naar zijn lokale postkantoor moet gaan ("for formal inquiry of all international items, the sender shall go to local post office"). Ook USPS, Canada Post en Australia Post verwijzen ontvangers naar de afzender, die de post in het land van verzending benadert. Neem dus contact op met de winkel of marktplaats voor terugbetaling of een nieuw pakket; zie [verloren pakketten (in het Engels)](guide:lost-parcel).

**Teruggestuurd.** `已退回` betekent dat het pakket teruggaat naar de afzender. Vraag de winkel wat zij doet; zie [terug naar afzender (in het Engels)](guide:returned-to-sender).

## Contactnummer en klantenservice van China Post

De Engelstalige website van China Post vermeldde op 10 november 2026 deze nummers:

- **Logistics and EMS**: 8610 11183.
- **Postal Business**: 8610 11185.

China Post vermeldt niet welke talen er worden gesproken. Formele navraag moet van de afzender komen, dus via de winkel gaat het sneller. Neem voor bezorging, herbezorging of douane contact op met je eigen nationale post.

## Een China Post-pakket volgen met Peek

[Peek](/) herkent twee letters, negen cijfers en `CN` direct als China Post wanneer het controlecijfer klopt, behalve als het begint met YY: dat zijn nummers van YYpost. Met een fout controlecijfer neemt Peek het niet aan als China Post, en als je toch China Post kiest, zegt Peek dat China Post "doesn’t issue numbers like this one": controleer het nummer in de e-mail van de winkel. Een nummer beginnend met ZS, ZC of ZA en 11 cijfers wordt eveneens direct herkend als China Post. Plak elk ander nummer gerust toch in: Peek volgt meer dan 4.500 vervoerders. Kiest Peek de verkeerde vervoerder, selecteer dan China Post met "Change".

Peek leest de eigen tracking van China Post. Wanneer China Post om telefooncijfers vraagt, probeert Peek in plaats daarvan de openbare voorvertoning op ems.com.cn; Peek vraagt je nooit om die cijfers. Als ook die voorvertoning weigert en geen enkele trackingdienst het pakket heeft, meldt Peek dat er meer informatie nodig is: zoek het nummer dan met de vier cijfers op de site van China Post op. China Post toont bezoekers alleen zijn drie nieuwste scans, dus Peek voegt de aannamedatum toe wanneer China Post die geeft, en haalt een uitgebreidere geschiedenis op bij trackingdiensten zodra er één die heeft.

Peek toont de status, de geschiedenis met het kantoor van elke scan (een Chinees kantoor bij zijn plaatsnaam, of zoals geschreven als Peek het niet kan plaatsen) en het land van bestemming. Scans verschijnen in het Engels waar Peek de formulering van China Post kent, anders in het Chinees. China Post tijdstempelt zijn eigen scans op Chinese tijd, zelfs de aankomst in jouw land; de latere scans die het van je nationale post overneemt houden meestal de lokale tijd van die post aan. Peek toont elke scan in de tijdzone van je apparaat; op peektracker.com en in de iPhone- en Android-app eindigt de geschiedenis met "Times are in your time zone:" en de naam van die tijdzone. China Post geeft geen verwachte bezorgdatum, gewicht of afhaalpunt, en Peek laat de dienstnaam, contactgegevens, afzender- en ontvangergegevens, namen en telefoonnummers van koeriers en kaartcoördinaten weg.

Voor een nummer dat eindigt op `CN` vraagt Peek, zodra China Post jouw land noemt, ook je nationale post met hetzelfde nummer (USPS, Royal Mail, An Post, Canada Post of Australia Post; Parcelforce voor EMS-nummers naar het VK) en schakelt naar die post over zodra daar nieuwe scans verschijnen, met behoud van de geschiedenis van China Post. Noemt China Post geen land, dan gaat Peek uit van het jouwe: in de iPhone- en Android-app de regio die op je telefoon is ingesteld; op peektracker.com het land van waaruit je het pakket toevoegde.

Peek controleert China Post elk uur, ook op de dag van bezorging. Zodra jouw postbedrijf de bezorging overneemt, volgt Peek het tempo van de vervoerder die bezorgt. Peek kan je een melding sturen voor "Every scan", "Important steps" of "Delivery only". Het kan niet namens jou contact opnemen met China Post, je eigen postbedrijf of de winkel.

## Vragen over China Post-tracking

### Waar is mijn China Post-pakket?

Bekijk de nieuwste scan: een Chinees sorteercentrum tussen haken (`【…】`) betekent dat het nog in China is; zodra `到达寄达地处理中心` verschijnt, heeft het jouw land bereikt en kun je hetzelfde `CN`-nummer volgen op de site van je eigen nationale post.

### Waarom werkt mijn China Post-tracking niet bij?

De tracking pauzeert meestal tijdens de internationale vlucht of de invoerdouane, tussen de exportscan van China Post en de eerste scan van je nationale post. Let op dat nummers die met `U` beginnen (`UA…CN`) UPU-kleinpakjes zonder klantgerichte tracking zijn en mogelijk geen scans in het buitenland krijgen.

### Hoe lang doet China Post over de bezorging?

De levertijd hangt af van de gekozen dienst (EMS-expres tegenover standaard briefpost of pakket) en het land van bestemming; houd daarom de schatting aan die bij je bestelling in de winkel of op de marktplaats staat.

### Wat betekent 已妥投 bij China Post-tracking?

Het betekent bezorgd (bijvoorbeeld `【英国】已妥投` = bezorgd in het VK). Omdat je lokale nationale post de eindbezorging deed, controleer je eerst de tracking en het afleverbewijs van dat postbedrijf als er niets is aangekomen: zie [bezorgd maar niet ontvangen (in het Engels)](guide:delivered-not-received).

### Wie bezorgt China Post-pakketten in de VS, het VK, Ierland, Canada of Australië?

De nationale post onder precies hetzelfde `CN`-nummer: USPS in de VS, Royal Mail in het VK (Parcelforce voor `E…CN` EMS-zendingen), An Post in Ierland, Canada Post in Canada en Australia Post in Australië.

:::sources
- [China Post: Introduction to China Post Group](https://english.chinapost.com.cn/en/folder/1408/3969-1.htm) – nationale post in staatseigendom
- [China Post: EMS and Logistics](https://english.chinapost.com.cn/en/folder/1408/3923-1.htm) – EMS-merk
- [China Post: Contact us](https://english.chinapost.com.cn/en/category/1408/3925-1.htm) – trackingsites, formele navraag door de afzender
- [China Post: Home page (English)](https://english.chinapost.com.cn/) – nummers voor Logistics and EMS en Postal Business
- [UPU: S10 standard, Identification of postal items](https://www.upu.int/UPU/media/upu/files/postalSolutions/programmesAndServices/standards/S10-12.pdf) – nummerformaat, dienstletters, U-reeks zonder tracking, één nummer per zending
- [GOV.UK: Tax and customs for goods sent from abroad: Overview](https://www.gov.uk/goods-sent-from-abroad) – Royal Mail loodst goederen door de Britse douane en meldt of btw of invoerrechten verschuldigd zijn
- [Revenue: Union Handling Fee press release](https://www.revenue.ie/en/corporate/press-office/press-releases/2026/pr-092926-handling-fee.aspx) – €2 per soort product vanaf 1 november 2026, betaald bij het afrekenen of aan het postbedrijf vóór bezorging
- [Canada Post: Tracking international packages](https://www.canadapost-postescanada.ca/cpc/en/support/kb/tracking/tracking-international-packages.page) – pakketten naar Canada, afzender laat onderzoek doen, termijnen
- [Australia Post: International tracking](https://auspost.com.au/help/parcel-tracking/international-parcel/tracking) – tracking hangt af van de dienst in het land van verzending
- [Australia Post: Customs](https://auspost.com.au/help/parcel-tracking/international-parcel/customs) – tot 15 dagen
- [USPS: International Inquiries – The Basics](https://faq.usps.com/s/article/International-Inquiries-The-Basics) – zendingen uit het buitenland: onderzoek via de post van de afzender
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – scanformuleringen van China Post, controle op telefooncijfers, openbare voorvertoning in een browser en wat Peek toont
:::
