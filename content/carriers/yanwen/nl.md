---
title: Yanwen tracking: UL…YP-nummer, statussen en bezorger
description: Volg een Yanwen-pakket (UL…YP, UK…YP, BYS of YW): wat elke status betekent, welke lokale vervoerder bezorgt, levertijden en contactgegevens van Yanwen.
slug: yanwen-pakket-volgen
published: 2026-11-28
updated: 2026-11-28
---

Een trackingnummer met twee letters (vaak `UL` of `UK`), 9 cijfers en `YP` aan het eind — of `BYS` gevolgd door 9 cijfers — hoort bij Yanwen, een Chinees logistiek bedrijf dat in 1998 in Beijing werd opgericht. Yanwen vervoert online bestellingen naar het buitenland en draagt ze voor het laatste traject over aan een lokale vervoerder. Volg je pakket op de website van Yanwen, [track.yw56.com.cn](https://track.yw56.com.cn/en/index), of in het vak hierboven; het nummer vind je bij de webshop of marktplaats in de verzendgegevens van je bestelling.

## Yanwen-trackingnummer: hoe het eruitziet

Yanwen publiceert geen voorbeeldnummer en geen helppagina voor ontvangers. De vormen die pakketten van Yanwen dragen, met verzonnen cijfers:

| Nummer | Van wie het is |
| --- | --- |
| `BYS482910573` | BYS en 9 cijfers: Yanwen. |
| `UL482917367YP` | Twee letters, 9 cijfers en dan YP: Yanwen. UL en UK zijn veelvoorkomende beginkarakters. |
| `YWABC482910573649` | YW, drie letters en 12 cijfers: het laatste-kilometer-nummer van een pakket dat in de VS wordt bezorgd. |
| `RR482917367CN` | Dezelfde postvorm maar eindigend op CN: [China Post](carrier:china-post), niet Yanwen. |
| `YT4829105736482910` | Niet Yanwen maar [YunExpress](carrier:yunexpress). |
| `LP48291057364829` | Niet Yanwen maar [Cainiao](carrier:cainiao), de logistieke referentie van AliExpress. |

Een postachtig Yanwen-nummer eindigt altijd op YP; elke andere uitgang hoort bij een ander postbedrijf. De resultatenpagina van Yanwen toont twee nummers: het "Tracking Number", dat van Yanwen is, en de "Last Mile Tracking", die hoort bij de vervoerder die in jouw land bezorgt. Als de webshop je dat lokale nummer heeft gegeven, geeft de site van Yanwen daar ook antwoord op. Kun je geen enkel nummer vinden, vraag er dan naar bij de webshop.

De site van Yanwen accepteert tot 100 nummers tegelijk, maar de zelfbedieningstracking dekt alleen vrachtbrieven van het afgelopen jaar. Producten van China Post worden daar niet gevolgd.

## Yanwen-trackingstatussen uitgelegd

De trackingsite van Yanwen is alleen beschikbaar in het Engels en Chinees, en de eigen scanregels van Yanwen zijn in beide talen Engels. De regels die mensen tegenkomen, in de volgorde waarin een pakket ze doorloopt:

| Yanwen meldt | Wat het voor jou betekent |
| --- | --- |
| `Order processed by shipper` | De verkoper heeft de zending aangemeld. Yanwen heeft het pakket nog niet. |
| `Yanwen Pickup Scan` | Yanwen heeft het pakket in China ontvangen. |
| `Yanwen facility - Outbound` | Het heeft het sorteercentrum van Yanwen verlaten. |
| `Tendered for Delivery` | Overgedragen voor de reis naar het buitenland, minuten na vertrek uit het sorteercentrum van Yanwen en nog vóór de exportdouane. Ondanks de woorden gaat het niet om een bezorgronde. |
| `International shipment release - Export` | De exportdouane in China is afgerond. |
| `Port of departure - Departure` | Overgedragen aan de luchtvaartmaatschappij en uitgevlogen. |
| `Port of destination - Arrival` | Aangekomen in of nabij jouw land. |
| `International shipment release - Import` | De importdouane heeft het pakket vrijgegeven. |
| `Batch delivery to carrier` | Overgedragen aan de lokale vervoerder. Daarna volgen diens scans. |
| `Parcel is out for delivery.` | Onderweg naar jou (eigen Amerikaanse bezorgdienst van Yanwen). |
| `<User not at home>` | Een bezorgpoging is mislukt, doorgegeven door de lokale vervoerder. |
| `Delivered.` | Bezorgd, in de bewoordingen van een lokale vervoerder. |

`Order Submited.` en `Waybill Generated` betekenen hetzelfde als de eerste regel: alleen het label bestaat en het pakket hoeft de verkoper nog niet te hebben verlaten. `Shipping Order Created.` is een ordermelding van een partner: Peek houdt de fase aan die het pakket al had. `Pick up by local carrier at destination port` betekent hetzelfde als `Batch delivery to carrier`. Daarna verschijnen de eigen regels van de lokale vervoerder, soms met een landcode vooraan of de naam van een depot tussen haakjes: bijvoorbeeld `Out for Delivery` of `Delivered, In/At Mailbox` van USPS. De regels van één lokale vervoerder verschijnen in hoofdletters, zonder naam: `ARRIVE TRANSITHUB` (zijn doorvoerhub), `TASK ASSIGNED` (zijn bezorgtaak) en `ORDER SORTED` (zijn sortering), alle drie "In transit" in Peek. In Italië is `First parcel scan along Poste Italiane’s logistic network` de eerste scan van Poste Italiane na de overdracht door Yanwen, geen nieuwe ophaling ("In transit"). `Shipping Label Created, USPS Awaiting Item` van USPS kan al opduiken terwijl het pakket nog in China is: het is alleen de labelregistratie bij USPS, dus Peek behoudt de fase die het pakket al had en toont er geen plaats bij. `The recipient has selected a Packstation as the new delivery address.` van DHL kan ook verschijnen terwijl het pakket nog in China is: het legt alleen vast dat je een DHL Packstation als bezorgadres hebt gekozen, en Peek behandelt die melding op dezelfde manier.

Sommige pakketten worden na `Yanwen facility - Outbound` overgedragen aan China Post. Yanwen geeft dan de scans van China Post in het Chinees door, uitgelegd op de pagina over [China Post](carrier:china-post); `中国邮政已收取邮件` is die overdracht. `【…】退回，备注:安检退回` betekent dat een sorteercentrum het pakket bij de veiligheidscontrole heeft teruggestuurd en het afhandelt voordat het verder wordt gestuurd: het is geen retour naar de verkoper. Peek toont dit als "Needs attention".

Boven de scans deelt Yanwen elk pakket in een categorie in. `Info_Received` is de labelfase. `In_Transportation` omvat alles van de vlucht tot de douane en het lokale traject. `End_of_Tracking` betekent dat Yanwen is gestopt met het volgen van het pakket: het zegt niets over waar het pakket is en betekent noch bezorging noch verlies. `Not_Found`, met de regel `No information was found`, betekent dat Yanwen niets onder dat nummer heeft. De regel van een bezorgd pakket vermeldt het aantal dagen dat het duurde, zoals "Delivered（7d）".

## Bezorging, afhalen en mislukte bezorging bij Yanwen

Yanwen bezorgt zelf niet in Europa: het bedrijf zegt samen te werken met lokale vervoerders ("with local carriers to fulfill the last mile package delivery"). Wanneer Yanwen die vervoerder noemt, toont de resultatenpagina een notitievak met "Distributor", "Distributor Website" en "Contact Number". Bij sommige pakketten blijft dat vak leeg.

Wie bezorgt hangt af van het land en de dienst die de webshop heeft gekozen. De productenlijst van Yanwen noemt voor het Verenigd Koninkrijk bijvoorbeeld lijnen via [Royal Mail](carrier:royal-mail), Yodel, Hermes en [Evri](carrier:evri). In de VS zijn pakketten die op de site van Yanwen worden gevolgd bezorgd door [USPS](carrier:usps), [UniUni](carrier:uniuni) of YanwenExpress, een Amerikaans bezorgbedrijf voor de laatste kilometer waarvan de website niet vermeldt of het verbonden is aan Yanwen (zie [YWE](carrier:ywe)), en in Canada door Emile of UniUni. In Australië draagt Yanwen pakketten in partijen over aan [J&T Express Australia](carrier:j-and-t-au) in de aankomsthaven; de scans van J&T verschijnen daarna in de geschiedenis van Yanwen en Peek toont de afhaling door J&T als "In transit".

Bezorgpogingen, niet-thuisberichten, een nieuwe bezorging plannen en hoe lang een pakket blijft liggen vallen onder de regels van de lokale vervoerder: Yanwen publiceert er geen. `Available_For_Pick_Up` betekent dat het pakket klaarligt bij een afhaalpunt; `Undelivered` betekent dat de bezorging is mislukt. Gebruik het nummer bij "Last Mile Tracking" en de contactgegevens in het notitievak om bij die vervoerder naar een nieuwe bezorging te vragen.

Op de resultatenpagina staat bij elke scan de GMT-afwijking vermeld.

## Yanwen-pakket wordt niet bijgewerkt, kwijt of retour

**Nog geen tracking.** Zolang het pakket op `Info_Received` staat, heeft de verkoper een label aangemaakt maar het pakket mogelijk nog niet afgegeven. Vraag aan de webshop wanneer het is verzonden.

**Dagenlang geen verandering.** Zodra het pakket bij de lokale vervoerder is, controleer je de eigen tracking van die vervoerder met het nummer onder "Last Mile Tracking". Meer lees je in [waarom tracking stopt met updaten](guide:tracking-not-updating) (in het Engels).

**Bezorgd maar niets ontvangen.** De lokale vervoerder heeft bezorgd, dus begin daar en neem daarna contact op met de webshop. Zie [bezorgd maar niet ontvangen](guide:delivered-not-received) (in het Engels).

**Kwijt, beschadigd of teruggestuurd.** Yanwen publiceert geen claimprocedure, termijn of schadevergoeding voor ontvangers. De sites zijn bedoeld voor verkopers, en de webshop of marktplaats heeft het contract met Yanwen, dus vraag daar om terugbetaling of een nieuw pakket. `Exception` en `Returned` zijn de categorieën van Yanwen voor deze gevallen.

**Oudere pakketten.** Vrachtbrieven verdwijnen na een jaar uit de zelfbedieningstracking; volgens de mededeling van Yanwen kan een medewerker van de hotline ze nog wel opzoeken.

## Yanwen-telefoonnummer en klantenservice

Yanwen vermeldt deze kanalen:

- **Hotline**: 400-108-5656, maandag tot en met zaterdag van 9:00 tot 18:00 uur, volgens de Chinese contactpagina. Een 400-nummer is een Chinees servicenummer; Yanwen vermeldt niet of het vanuit het buitenland bereikbaar is of welke talen worden gesproken.
- **E-mail**: marketing@yw56.com.cn, het enige adres op de Engelstalige site. Yanwen noemt het geen supportadres.
- **Overige kanalen**: een online chat rechtsonder op yw56.com.cn, een klantenportaal en een WeChat-account, allemaal in het Chinees en gericht op verkopers.
- **Laatste kilometer in de VS**: de supportpagina van YanwenExpress toont geen telefoonnummer en geen e-mailadres.

Yanwen publiceert geen contactgegevens voor enig Europees land. Voor je bestelling is de webshop of marktplaats sneller; gebruik voor de bezorging zelf de vervoerder en het nummer uit het notitievak.

## Een Yanwen-pakket volgen met Peek

[Peek](/) herkent BYS-nummers, YP-nummers en Amerikaanse YW-nummers meteen. Een YP-nummer met een onjuist controlecijfer wordt niet als Yanwen herkend: kies dan Yanwen via "Change". Een link van track.yw56.com.cn met `nums=` wordt als Yanwen gelezen ongeacht welk nummer erin staat, zodat die ook werkt voor het lokale nummer.

Peek toont de status, de geschiedenis met de plaats of regio van elke scan op een kaart, het bezorgtijdstip, het land van bestemming en de lokale vervoerder met diens nummer ("Delivery with" die vervoerder, of "Announced to …, not received yet" zolang die vervoerder alleen de aankondiging van het pakket heeft en nog geen eigen scan). Wanneer Yanwen een lokale vervoerder noemt die in de lijst van Peek staat en het nummer daarbij past, vraagt Peek ook die vervoerder om gegevens en schakelt het naar hem over zodra hij nieuwere voortgang toont. Yanwen geeft geen verwachte bezorgdatum; Peek toont geen afhaalpunt en laat orderreferenties, contactpersonen en postcodes weg.

Scantijden worden gelezen met de GMT-afwijkingen van Yanwen en weergegeven in de tijdzone van je apparaat, en dubbele scans worden verwijderd. Een doorgegeven USPS-stap `In Transit to Next Facility` met middernacht als tijdstip toont alleen de datum, omdat USPS zulke stappen alleen per dag dateert. `Delivered` en `Out for delivery` verschijnen in de eigen bewoordingen van Peek; andere regels zoals geschreven, inclusief `Delivered.`. Zolang je het pakket open hebt staan, of op de bezorgdag met meldingen aan, controleert Peek tot elke 10 minuten, en tot elke 2 minuten vanaf 6:00 uur lokale tijd op de dag dat het pakket uitgaat voor bezorging, zolang de eigen tracker van Yanwen antwoordt; anders elk uur. Via trackingdiensten gebeurt dat elk uur, elke 3 uur na een rustige dag en tot elke 15 minuten op de bezorgdag onder dezelfde voorwaarde. Zodra een andere vervoerder de bezorging overneemt, volgt Peek het eigen tempo van die vervoerder. Peek kan je een melding sturen bij elke scan, alleen bij belangrijke stappen of bij "Delivery only". Na twee dagen zonder nieuws (12 uur zolang het pakket nog "Label created" of "Received by carrier" toont) controleert Peek elk uur. Peek kan geen contact opnemen met Yanwen of de webshop namens jou.

## Vragen over Yanwen-tracking

### Wat betekent een trackingnummer dat eindigt op YP (zoals UL…YP)?

Twee letters (vaak `UL` of `UK`), 9 cijfers en `YP` is het postachtige trackingformaat van Yanwen — `YP` staat voor Yanwen en niet voor het postbedrijf van een land. Yanwen verzorgt het vervoer uit China en draagt het pakket over aan een lokale vervoerder in jouw land, die mogelijk een tweede nummer voor het laatste traject toekent.

### Waar is mijn Yanwen-pakket?

De nieuwste scan toont de fase en, wanneer vastgelegd, een plaats of regio. Zodra `Batch delivery to carrier` verschijnt, heeft een lokale vervoerder in jouw land het pakket: kijk op de resultatenpagina van Yanwen of in Peek voor de naam van die vervoerder en het lokale trackingnummer.

### Wat betekent Yanwen facility - Outbound?

Het pakket heeft het sorteercentrum van Yanwen in China verlaten. Daarna volgen `Arrived at domestic terminal station`, de exportdouane (`International shipment release - Export`) en de internationale vlucht.

### Is Yanwen betrouwbaar?

Ja. Yanwen is een logistiek bedrijf dat in 1998 in Beijing werd opgericht, in 2015 een aanbevolen logistieke partner werd voor AliExpress en eBay, en grensoverschrijdende pakketten verzendt waarbij lokale vervoerders het laatste traject uitvoeren.

### Hoe lang duurt levering met Yanwen?

Yanwen publiceert geen levertijden voor ontvangers, dus ga uit van de verwachte leverdatum die de webshop of marktplaats bij je bestelling toont. De zelfbedieningstracking dekt vrachtbrieven van het afgelopen jaar; zie voor pakketten uit China in het algemeen [een pakket uit China volgen](guide:tracking-from-china) (in het Engels).

:::sources
- [Yanwen: YW Tracking](https://track.yw56.com.cn/en/index) – trackingsite, talen, resultaatkolommen, categorieën, notitievak, hotline
- [Yanwen: 燕文追踪 (tracking, Chinees)](https://track.yw56.com.cn/) – scans in het Engels, mededeling over China Post
- [Yanwen: YANWEN EXPRESS home page](https://www.yw56.com.cn/en/) – bedrijf, 1998 Beijing, laatste kilometer door lokale vervoerders, hotline, e-mail
- [Yanwen: Company Profile](https://www.yw56.com.cn/webfile/Profile/) – AliExpress en eBay
- [Yanwen: Contact Us](https://www.yw56.com.cn/webfile/ContactUs/) – hotline, e-mail
- [Yanwen: 联系我们 (contact)](https://www.yw56.com.cn/webfile/联系我们/) – openingstijden hotline, productlijnen via Royal Mail, Yodel, Hermes, Evri
- [Yanwen: 关于官方快件查询入口及服务的通告 (officiële trackingkanalen)](https://www.yw56.com.cn/webfile/ANNOUNCEMENTS/2025/06-30/7345353199651393536.html) – 100 nummers, limiet van één jaar, telefonisch opzoeken, chat, portaal, WeChat
- [YanwenExpress: home page](https://www.yanwenexpress.com/) – laatste-kilometer-bezorging in de VS
- [YanwenExpress: Customer support](https://www.yanwenexpress.com/contactus.html) – geen telefoonnummer of e-mailadres
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – wat Peek toont, scans van J&T Express Australia na de batchoverdracht, DHL-Packstationmelding, scans in hoofdletters van een lokale vervoerder, eerste scan van Poste Italiane
:::
