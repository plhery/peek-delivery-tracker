---
title: DHLink tracking: nummers, statussen en wie bezorgt
description: Volg een DHLink-pakket van een DHgate-bestelling: welke nummers DHLink accepteert, wat de Engelse scans betekenen, wie in jouw land bezorgt en contact.
slug: dhlink-pakket-volgen
published: 2026-11-28
updated: 2026-11-28
---

DHLink (驼飞侠) is het grensoverschrijdende logistieke platform van DHgate (敦煌网). Het werd opgericht in 2014, verzendt bestellingen voor DHgate-verkopers en andere klanten vanuit China of vanuit zijn magazijnen in het buitenland, en draagt ze voor het laatste traject over aan een vervoerder in jouw land. Volg het nummer op de [trackingpagina van DHLink](https://www.dhlink.com/trackQuery?lang=en) of in het vak hierboven. Haal het nummer uit je DHgate-bestelling of vraag het aan de verkoper.

## DHLink-trackingnummer: hoe het eruitziet

DHLink publiceert geen vast nummerformaat. De tracker accepteert tot 40 nummers tegelijk en aanvaardt wat hij de Global Tracking No., de Logistics Delivery No. en het DHLink pickup number noemt. Vormen die op DHLink-pakketten voorkomen, met verzonnen cijfers:

| Nummer | Wat het is |
| --- | --- |
| `FC4829105736482` | FC en 13 cijfers: het eigen nummer van DHLink, de "DHLink No." Op sommige lijnen is het ook het trackingnummer. |
| `XZG482910573WL` | XZG, 9 cijfers, WL: nummer van een lijnpartner dat DHLink vindt als het trackingnummer. |
| `YLUS4829105736` | YLUS en 10 cijfers: een Amerikaanse lijn die GOFO bezorgt. |
| `QH482910573648FS` | QH, 12 cijfers, FS: een andere Amerikaanse lijn die GOFO bezorgt. |
| `EYT4829105736HZ` | EYT, 10 cijfers, HZ: een Amerikaanse lijn die SwiftX bezorgt. |
| `CYS48291057364` | CYS en 11 cijfers: een Amerikaanse lijn die [UniUni](carrier:uniuni) bezorgt. |
| `JXCYI4829105736YQ` | JXC, YI, ZO, US of ZI, 10 cijfers, YQ: een vorm van vrachtbrieven van [Juxi Express](carrier:juxi-express) waarop ook de tracker van DHLink antwoordt voor sommige pakketten op een Amerikaanse lijn. Peek vraagt DHLink naar JXC-nummers; als de verkoper met Juxi verzond, kies je Juxi Express via "Change". |
| `RR482917367CN` | Twee letters, 9 cijfers en CN: een nummer van [China Post](carrier:china-post). Sommige DHLink-pakketten reizen onder zo'n nummer met China Post. |
| `GFUS48291057364829` | Niet van DHLink: een nummer van [GOFO Express](carrier:gofo), het laatste traject van sommige Amerikaanse lijnen. |

Bij een resultaat vermeldt DHLink de "DHLink No." en de "Tracking No.", en wanneer een andere vervoerder bezorgt een "Last Leg No." voor die vervoerder. Verschilt het trackingnummer van het DHLink-nummer, gebruik dan het trackingnummer: gezocht op alleen het DHLink-nummer verschijnt zo'n pakket mogelijk niet. Zonder `lang=en` in het adres opent de pagina in het Chinees.

## DHLink-trackingstatussen uitgelegd

Een resultaat toont de status (`In transit`, `Delivered`), de landcodes van herkomst en bestemming, de lijncode, een voortgangsbalk met `Original Leaving`, `Destination Arrived`, `Pending delivery` en `Delivered`, en elke scan met zijn tijdstip. Een bezorgd pakket toont ook "Signed for - Signed for time". De regels komen uit meerdere bronnen, zodat dezelfde scan vaak meer dan eens voorkomt. De Engelse regels van DHLink, in de volgorde waarin een pakket ze tegenkomt:

| DHLink meldt | Wat het voor jou betekent |
| --- | --- |
| `Data Received; Data transferred and received by carrier` | Op een lijn naar Zuidoost-Europa: Pactic, het platform in Hongarije waarlangs die lijn loopt, heeft de pakketgegevens. Vóór de magazijnscan van DHLink toont Peek "Label created"; wanneer de regel na de vlucht terugkomt, blijft het pakket "In transit". |
| `Arrive at the collection warehouse` | Het verzamelmagazijn van DHLink in China heeft het pakket. |
| `The collection warehouse has completed sorting` | Daar gesorteerd. |
| `Package received & scanned at warehouse` | Ingeboekt in het magazijn van DHLink. |
| `Parcel handed over to carrier,waiting for transportation` | Het verzamelpunt heeft het en het wacht op vervoer. |
| `Package departed from the warehouse`, `The package has sent from the warehouse` | Het heeft het magazijn verlaten. |
| `Warehouse scanning bagging`, `Master airway bill number received`, `Leave the warehouse` | Op sommige lijnen: het magazijn van de lijn in China heeft het pakket in zakken verpakt voor de vlucht, het hoofdluchtvrachtbriefnummer is binnengekomen en het pakket heeft het magazijn verlaten. Peek toont "In transit". |
| `Customs export clearance completed`, `Package handed over to airline` | De Chinese exportdouane heeft het vrijgegeven en een luchtvaartmaatschappij heeft het overgenomen. |
| `Flight departure`, `The flight has taken off` | Uit China vertrokken per vliegtuig. |
| `Shipment Departed From Original Country at …` | Hetzelfde: het heeft China verlaten. |
| `Departed from airport of origin` | De vlucht is uit China vertrokken ("In transit" in Peek). |
| `The flight has arrived and the board is being dismantled` | Geland; de pallets worden uitgepakt. |
| `Dismantling the board` | Hetzelfde: de pallet wordt op de aankomstluchthaven uitgepakt ("In transit" in Peek). |
| `Shipment has arriveved at destination Country at …` | In jouw land. De spelling is van DHLink zelf. |
| `Arrived to … hub`, `Linehaul transit; Parcel has entered the linehaul network.` | Na de vlucht: een hub van een partner in Europa, aangeduid met een code op de plaats van de puntjes, en daarna het hoofdtransport verder. Op de lijn naar Zuidoost-Europa zijn dit de regels van Pactic vanuit zijn hub in Hongarije (code HU). Peek toont "In transit". |
| `Customs clearance completed`, `Customs import clearance completed, awaiting pickup by local carrier` | De importdouane heeft het vrijgegeven; de vervoerder in jouw land haalt het hierna op. |
| `UPS/USPS/Fedex` | Een overdracht aan de Amerikaanse vervoerder komt eraan. |
| `货物已交UPS/USPS/Fedex,The goods have been delivered to UPS/USPS/Fedex` | Overgedragen aan de Amerikaanse vervoerder. Nog niet bij jou bezorgd. |
| `Package picked up by last-mile carrier` | De vervoerder in jouw land heeft het overgenomen. |
| `Scheduled for Delivery` | Onderweg voor bezorging. |
| `Shipment has delivered at …` | Bezorgd. |

Amerikaanse lijnen voegen hub-scans toe zoals `Arrived at Origin Hub` en `Departed Destination Hub`; op één lijn is `Shipping Order Created.` de opdracht van de lijn, een uur voordat het magazijn het pakket heeft ("Label created" in Peek). Een locatie van een lokale bezorger kan `Parcel received by ABC01.` schrijven, met een eigen code waar ABC01 staat, na het ophalen van het pakket uit het magazijn van DHLink, en minuten later `Parcel departed from Facility.`, beide "In transit". Na de overdracht volgen de regels van de bezorgende vervoerder, zoals `Arrived at GOFO Delivery Station` van GOFO of `No Access to Delivery Location` van USPS (een mislukte poging). Die van UniUni zijn `Parcel received at UniUni sorting hub.`, `Parcel processed.` (op die hub, minuten voor de bezorgronde) en `Parcel scanned and in transit.`, soms na de plaats en staat van de hub, alle "In transit" in Peek. Op China Post-lijnen verschijnen ook de regels van China Post, sommige alleen in het Chinees: `中国邮政 已收取邮件` (China Post nam het stuk aan), `送交出口海关` (aangeboden aan exportdouane), `已妥投` na een landcode (bezorgd). Voor een onbekend nummer meldt de pagina `There is no track for this waybill number`.

## Wie bezorgt DHLink-pakketten

De site van DHLink is geschreven voor verkopers en noemt geen laatste-kilometer-partner per land. De tracker toont wie bezorgt. Op Amerikaanse lijnen meldt één scan dat het pakket naar "UPS/USPS/Fedex" ging zonder te zeggen welke; de regels die volgen zijn van die vervoerder, zoals [USPS](carrier:usps). Sommige Amerikaanse lijnen eindigen bij [GOFO Express](carrier:gofo) of SwiftX, waarvan het nummer als "Last Leg No." verschijnt (de eigen tracker van GOFO vindt de DHgate-pakketten die hij bezorgt ook op hun DHLink-nummer, FC en 13 cijfers); in Zwitserland kan dat nummer van [DPD Switzerland](carrier:dpd-ch) zijn. Pakketten die met China Post reizen behouden hun CN-nummer tot het eind: zie [China Post](carrier:china-post) voor wie ze in jouw land bezorgt.

Zodra je de vervoerder en zijn nummer kent, heeft zijn eigen tracking de lokale details en gelden zijn regels voor bezorgpogingen, niet-thuisberichten en afhalen: zie [gemiste bezorging](guide:missed-delivery) (in het Engels). Sinds 1 november 2026 rekent de EU een afhandelingsvergoeding van €2 per soort product op online bestellingen van buiten de EU; de verkoper of de bezorgende vervoerder betaalt die en kan hem aan jou doorberekenen bij het afrekenen of vóór bezorging. Zie voor invoerrechten en andere kosten op goederen uit China [pakketten vast bij de douane](guide:customs) (in het Engels).

## DHLink-pakket wordt niet bijgewerkt, kwijt of bezorgd maar niet ontvangen

**Niet gevonden.** Controleer het nummer bij je DHgate-bestelling en probeer het trackingnummer in plaats van het DHLink-nummer. Een lokaal nummer vindt niets op de pagina van DHLink: controleer dat bij die vervoerder. Heb je DHLink gekozen via "Change" voor een nummer dat DHLink niet kent, dan toont Peek "Waiting for DHLink" en blijft het controleren. Kent DHLink het nummer twee dagen later nog steeds niet en heeft niets anders ervoor geantwoord, dan toont Peek "After two days, the carrier still doesn’t know this number. Check it, choose another carrier or delete the parcel."

**Blijft hangen na de vlucht of douane.** Zoek naar een "Last Leg No." en controleer dat nummer bij die vervoerder, wiens scans mogelijk nieuwer zijn dan die van DHLink. Meer in [waarom tracking stopt met updaten](guide:tracking-not-updating) en [een pakket uit China volgen](guide:tracking-from-china) (in het Engels).

**Bezorgd maar niet aanwezig, kwijt of te laat.** De terugbetalings- en vergoedingsregels van DHLink voor probleempakketten zijn geschreven voor verkopers: de verkoper claimt bij DHLink, en jij handelt het af met de verkoper of DHgate. Ze sluiten uit wat de koper veroorzaakt: een late bezorging omdat het pakket niet werd opgehaald of getekend, of mislukte inklaring omdat de koper niet op tijd hielp. Haal het pakket dus op, beantwoord douaneverzoeken snel en waarschuw de verkoper op tijd: hij kan alleen binnen 90 dagen na het boeken van de zending een claim indienen. Zie [bezorgd maar niet ontvangen](guide:delivered-not-received) en [verloren pakketten](guide:lost-parcel) (in het Engels).

## Contact en klantenservice van DHLink

Op de contactpagina van DHLink, gelezen op 4 november 2026, staat één klantenserviceadres, dhlinkservice@dhlink.com, en een aanvraagformulier op het platform. De site heeft ook een chatassistent, "AI 小飞". De contactpagina is in het Chinees en vermeldt geen telefoonnummer. Neem voor je bestelling eerst via DHgate contact op met de verkoper; voor een bezorgprobleem in jouw land met de bezorgende vervoerder.

## Een DHLink-pakket volgen met Peek

[Peek](/) herkent een `YLUS`-nummer met 10 cijfers meteen als DHLink. De `FC`-, `XZG`-, `QH`-, `EYT`- en `CYS`-nummers zijn op vorm alleen slechts zwakke overeenkomsten: plak het nummer toch, Peek volgt meer dan 4.500 vervoerders. Het toont "Checking tracking services…" en daarna "DHLink has this parcel." wanneer DHLink het nummer kent; kies anders DHLink via "Change". Een `JXC`-nummer (`JXCYI`, `JXCZO`, `JXCUS` of `JXCZI` met 10 cijfers en `YQ`) deelt zijn vorm met Juxi Express: Peek vraagt DHLink naar deze `JXC`-nummers, en als de verkoper met Juxi heeft verzonden, kies je Juxi Express via "Change". Een China Post-nummer (twee letters, 9 cijfers, CN) gaat naar China Post.

Peek leest de tracker van DHLink. Het toont de status, de geschiedenis met de plaats van elke scan en het land van bestemming, en vermeldt elke scan één keer. Scans in China worden gelezen op Beijing-tijd, USPS-scans op de tijd van hun Amerikaanse staat en de regels van Pactic op een lijn via Hongarije op Boedapest-tijd; andere behouden de tijd die DHLink geeft. DHLink geeft geen bezorgtijd, gewicht of servicenaam, en Peek laat postcodes en orderreferenties weg. De enige bezorgschatting is een doorgegeven USPS-scan "Out for Delivery, Expected Delivery …" gelezen op de tijd van die Amerikaanse staat: zolang die scan de nieuwste is, toont Peek het tijdvak van die dag wanneer de scan er een geeft; een scan met alleen een uiterste tijd voegt geen schatting toe, omdat "Out for delivery" al vandaag betekent. Wanneer de "Last Leg No." een GOFO-nummer (GFUS en 14 cijfers), een SwiftX-nummer (SWX en 18 cijfers) of een nummer van DPD Switzerland (14 cijfers beginnend met 060 of 061) is, toont Peek die vervoerder met zijn nummer, vraagt hem ook en schakelt over zodra hij nieuwere voortgang toont. Andere laatste-traject-nummers blijven op de geschiedenis van DHLink.

Zolang de tracker van DHLink antwoordt, controleert Peek tot elke 10 minuten met het pakket op het scherm, of met meldingen aan als bezorging vandaag wordt verwacht. Vanaf 6:00 uur op de klok van de vervoerder op de dag dat het pakket uitgaat voor bezorging, kunnen controles elke 2 minuten lopen met het pakket op het scherm of meldingen aan. Geeft de vervoerder een bezorgvak, dan starten de snellere controles op zijn vroegst een halfuur voordat het ingaat. Buiten beeld en zonder meldingen zijn controles normaal elk uur. Wanneer alleen een trackingdienst antwoordt, controleert Peek elk uur, elke 3 uur zodra de laatste scan een dag oud is, en tot elke 15 minuten op de dag waarop het pakket uitgaat voor bezorging terwijl je het open hebt of meldingen aanstaan. Zodra Peek het pakket volgt bij de vervoerder die het bezorgt, controleert Peek in het eigen tempo van die vervoerder. Peek kan je een melding sturen bij elke scan, bij belangrijke stappen of alleen op de bezorgdag. Voor controles bij de vervoerder zelf schakelt Peek na twee dagen zonder nieuws (12 uur zolang het pakket nog "Label created" of "Received by carrier" toont) over op elk uur. Peek kan geen contact opnemen met DHLink, DHgate of de verkoper namens jou.

## Vragen over DHLink-tracking

### Wat is DHLink?

Het grensoverschrijdende logistieke platform van DHgate, geëxploiteerd door 驼飞侠科技（北京）有限公司 in Beijing. Opgericht in 2014 verzendt het bestellingen vanuit China en vanuit buitenlandse magazijnen via eigen lijnen en partnervervoerders.

### Wie bezorgt mijn DHLink-pakket?

Een vervoerder in jouw land. Op Amerikaanse lijnen draagt DHLink pakketten over aan UPS, USPS of FedEx, of aan GOFO, SwiftX of UniUni; in Zwitserland soms aan DPD. Kijk naar de "Last Leg No." op het resultaat van DHLink.

### Wat betekent "The goods have been delivered to UPS/USPS/Fedex"?

DHLink heeft het pakket overgedragen aan de Amerikaanse vervoerder. Het is nog niet bij jou bezorgd: de scans van die vervoerder volgen daarna.

:::sources
- [DHLink: About us (关于我们)](https://www.dhlink.com/about/introduce) – opgericht 2014, grensoverschrijdend logistiek platform van DHgate, voor DHgate en andere klanten
- [DHLink: Home page (首页)](https://www.dhlink.com/) – rechtstreekse verzending vanuit China, buitenlandse magazijnen, link naar ordestracking van DHgate
- [DHLink: Tracking (轨迹查询)](https://www.dhlink.com/trackQuery?lang=en) – 40 nummers, geaccepteerde nummers, resultaatvelden, DHLink No., Tracking No., Last Leg No., voortgangsbalk, "There is no track for this waybill number"
- [DHLink: Contact us (联系我们)](https://www.dhlink.com/about/connect) – e-mailadres klantenservice, aanvraagformulier, chat
- [DHLink: Service agreement (服务协议)](https://www.dhlink.com/agreementPage/service) – exploitant 驼飞侠科技（北京）有限公司
- [DHgate Seller Help: DHLink物流异常件赔付规则 (vergoedingsregels DHLink)](https://seller.dhgate.com/help/c7502/516002.html) – vergoeding voor verkopers, logistieke typen, uitsluitingen voor door de koper veroorzaakte vertraging en mislukte inklaring, claims binnen 90 dagen na de logistieke order
- [Revenue: Union Handling Fee press release](https://www.revenue.ie/en/corporate/press-office/press-releases/2026/pr-092926-handling-fee.aspx) – €2 per soort product vanaf 1 november 2026, voor goederen gekocht buiten de EU, betaald door de verkoper of het bezorgbedrijf, dat dit bij het afrekenen of vóór bezorging kan doorberekenen
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – nummervormen (YLUS direct herkend; CYS op een UniUni-VS-lijn; JXC gedeeld met Juxi Express), scanteksten (gegevens-, hub- en linehaul-regels van Pactic; inpak-, luchtvrachtbrief- en vluchtregels van een lijn; hub- en transitscans van UniUni; order van een VS-lijn; ontvangst en vertrek bij een lokale vestiging), laatste-traject-partners, GOFO-tracker die DHLink-FC-nummers neemt, wat Peek leest en toont
:::
