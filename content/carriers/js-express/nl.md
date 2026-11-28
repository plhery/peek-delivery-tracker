---
title: JS Express pakket volgen: JSE…YQ-nummers, status & bezorger
description: Volg een JS Express (急速国际) pakket uit China: hoe een JSE…YQ-nummer eruitziet, wat elke scan betekent, wie bezorgt in Europa en de VS, douane en contact.
slug: js-express-pakket-volgen
published: 2026-11-28
updated: 2026-11-28
---

JS Express (急速国际, ook JS EXP) is een Chinees logistiek bedrijf voor internationale webwinkels, beheerd door 急创速供应链管理（苏州）有限公司 in Suzhou. Heeft een webshop je bestelling met JS Express verstuurd, dan begint je trackingnummer met `JSE` en eindigt het op `YQ`: plak het in het zoekvak hierboven. JS Express brengt het pakket uit China weg en een bezorgdienst in jouw land doet de bezorging: in de trackinggeschiedenis komen USPS, GOFO of SpeedX in de VS voor, Straightship in Canada, Yodel in het VK, DHL, iMile of J&T Express in Duitsland, Colissimo in Frankrijk, CTT Express in Spanje en op sommige lijnen FedEx.

## JS Express trackingnummer: hoe het eruitziet

De vrachtbriefnummers van JS Express bestaan uit `JSE`, twee letters, tien cijfers en `YQ`, in totaal 17 tekens. Met verzonnen cijfers:

| Nummer | Wat het is |
| --- | --- |
| `JSEAA4829105736YQ` | `JSE`, twee letters, tien cijfers en `YQ`: een JS Express-vrachtbrief. |
| `JSEAA482910573YQ` | Slechts negen cijfers: geen JS Express-formaat. Controleer of er een cijfer ontbreekt. |
| `JXCAA4829105736YQ` | Eindigt ook op `YQ`, maar begint met `JXC`: een nummer van [Juxi Express](carrier:juxi-express). |

Andere Chinese expediteurs gebruiken dezelfde opbouw met eigen beginletters: `FEI` voor 17FEIA, `WNB` voor WanbExpress, `SFC` voor SFC, `CJP` voor CJ Packet. `JSE` kenmerkt JS Express. Spaties en kleine letters maken in Peek niet uit. Peek leest een nul op de plek van de tweede letter als een `O`, en een `O` tussen de tien cijfers als een nul. De eigen tracker van JS Express vereist het hele nummer: een deel ervan vindt niets.

**Waar je het vindt.** De webwinkel geeft het nummer door in de verzendmail of op je bestelpagina. JS Express meldt nauw samen te werken met Amazon, Walmart, Shopify, eBay, AliExpress, Wish en Shopee. Vind je het niet, zie [waar je een trackingnummer vindt](guide:find-tracking-number) (Engels).

**Geen directe deellink.** De trackingpagina's van JS Express werken via een formulier, waardoor geen link één pakket opent: kopieer het nummer. De Engelse pagina "Express Query" antwoordt meteen; de Chinese vraagt eerst een afbeeldingscode (`验证码`).

## Trackingstatussen van JS Express uitgelegd

De pagina van JS Express toont elke scan met tijd, plaats tussen 【haakjes】 en tekst, de nieuwste bovenaan met "Latest". De teksten zijn meestal Engels. In de volgorde waarin een pakket ze krijgt:

| JS Express meldt | Wat het voor jou betekent | Peek toont |
| --- | --- | --- |
| `Electronic notification of order creation received.` | De webshop heeft de ordergegevens aan JS Express gestuurd. JS Express heeft het pakket nog niet. | "Label created" |
| `PRE-ADVICE` | Een partner heeft de pakketgegevens ontvangen voordat het magazijn van JS Express het pakket heeft. | De status die het pakket al had |
| `Item has been collected` | Het magazijn van JS Express in China heeft het pakket: de eerste echte scan. | "Received by carrier" |
| `Item inbound in sorting center.`, `Item outbound in sorting center.` | Gesorteerd bij JS Express in China. | "In transit" |
| `Domestic allocation has begun`, `Under domestic allocation` | Nog in het magazijn van JS Express in China, na sortering en vóór vertrek. | "In transit" |
| `Waybill Generated for` | De bezorgdienst in jouw land heeft zijn vrachtbrief gemaakt terwijl het pakket nog bij JS Express in China ligt. | "Label created", of de status die het pakket al had, meestal "In transit" |
| `Arrive at transit center` | In het doorvoercentrum van JS Express in China, tussen sortering en vertrek. | "In transit" |
| `Item departed from origin.`, `Arrive at international airport to abroad` | Onderweg naar buitenland, daarna op de luchthaven in China. | "In transit" |
| `Arrived at export center`, `Exports Center Sorting and Packing`, `Arrived at Origin Port` | Op sommige lijnen tussen die twee in: een exportcentrum in China sorteert en verpakt het pakket, daarna bereikt het de vertrekhaven. | "In transit" |
| `Shipment arrived at facility and measured.`, `Depart from facility to service provider.` | Een vestiging in China weegt en meet het pakket en stuurt het door naar de luchtvrachtlijn. | "In transit" |
| `Start on-site transportation and shipment` | Op de luchthaven in China na aankomst: afhandeling ter plaatse gestart vóór vrijgave door de Chinese uitvoer-douane. | "In transit" |
| `Released From Export Customs` | De Chinese uitvoer-douane is klaar. Niet de douane van jouw land. | "In transit" |
| `DEPART ORIGIN AIRPORT`, `Departure from the original airport`, `Item Flight departure`, `Initial landing`, `Connecting flights flight takeoff` | De vlucht is vertrokken, soms met tussenlanding en aansluitende vlucht. | "In transit" |
| `ARRIVED AT DESTINATION AIRPORT`, `Arrival to the destination airport`, `Item Flight landing`, `NOA-Notification of arrival from GHA` | Geland in het land van bestemming. NOA is de aankomstmelding van de grondafhandelaar. | "In transit" |
| `The customs clearance agent is queuing up to pick up the goods` | De inklaringsagent wacht bij de vrachtterminal van de luchthaven om de zending op te halen: noch de agent noch de douane heeft hem al. | "In transit" |
| `The customs clearance agent has picked up the goods` | De agent heeft de zending opgehaald bij de vrachtterminal voor invoerinklaring. | "At customs" |
| `The goods have been picked up abroad` | De agent in het bestemmingsland heeft de zending van de luchthaven gehaald. Geen nieuwe afhaling bij de verkoper. | "In transit" |
| `Customs clearance agent received the shipment in destnation country.` | De inklaringsagent heeft de zending na de vlucht overgenomen voor invoerinklaring. "destnation" is de spelling van JS Express zelf. | "At customs" |
| `Inbound to customs temporary warehouse` | In een tijdelijk douanemagazijn, wachtend op invoerinklaring. | "At customs" |
| `Released From Import Customs`, `Released from customs: customs cleared.` | De invoerdouane heeft het pakket vrijgegeven. | "In transit" |
| `Depart from the destination distribution center.` | Een distributiecentrum in het land van bestemming stuurt het pakket na de douane door. | "In transit" |
| `In transit, it's progressing through post network` | De post in het bestemmingsland vervoert het pakket door zijn netwerk, vóór de bezorgrit. | "In transit" |
| `collection by overseas warehouse` | Het magazijn van JS Express in het land van bestemming heeft het pakket na de douane ontvangen. | "In transit" |
| `Load cargo overseas`, `Vehicles depart from` | Dat magazijn laadt het pakket voor de bezorger, daarna vertrekt de wagen vanaf de getoonde plaats. | "In transit" |
| `Delivery to local courier`, `Last Mile Delivery` | Overgedragen aan de lokale bezorgdienst, vóór diens eerste scan. Nog niet bij jou bezorgd. | "In transit" |
| `Delivery in progress` | Op sommige routes: de lokale vervoerder heeft het pakket na aankomst op het depot, nog vóór een bezorgrit. Ondanks de tekst nog niet onderweg met de bezorger. | "In transit" |
| `到达待取` | Eigen status van JS Express in het Chinees: aangekomen, klaar om af te halen. Als plaats staat alleen het land. | "Ready for pickup" |
| `The delivery address is incorrect. Please ask the recipient to contact their local area?` | Eigen melding van JS Express na een mislukte bezorgpoging: het adres is onduidelijk of fout. Vraagt contact op te nemen met het lokale kantoor, waarvan het telefoonnummer op de pagina van JS Express staat; Peek laat telefoonnummers weg. | "Needs attention" |

**Scans van de lokale bezorgdienst.** Na `Delivery to local courier` gaat de geschiedenis verder in de woorden van de bezorgdienst. In de VS zijn dat USPS-scans: `Accepted at USPS Origin Facility` of `Accepted at USPS Facility` (eerste scan, geen nieuwe zending), `Out for Delivery`, `Delivered, Front Door/Porch`. Scans van SpeedX verschijnen zonder naam: `Processing at Transit Facility`, `In transit to destination facility`, `Arrival at Destination Facility` en `Processing at Designated Facility`, alle "In transit". Een andere Amerikaanse partner blijft ook naamloos: `Arrived at Origin Hub` (eerste hub bij de luchthaven), `Departed Destination Hub` en `Arrived At Warehouse` (depot vóór de bezorgrit), alle "In transit". In Canada toont Straightship `Package is received at a Straightship station`, dan `Package will be delivered before 9:00pm` (de bezorgrit die dag, "Out for delivery": gevolgd door bezorging of poging) en `Delivered` met de plaats tussen haakjes. In Duitsland toont DHL Engelse regels, soms met punt: `The instruction data for this shipment have been provided by the sender to DHL electronically`, zijn registratie van de verzendgegevens ("Label created", of "In transit" als er al een eerdere scan was), daarna `The shipment is in transit to DHL` ("In transit"); een klein pakket kan ook het Duitse `Die Sendung wurde im Briefzentrum bearbeitet.` uit een DHL-briefcentrum krijgen ("In transit"). Een chauffeur van iMile kan een rit afsluiten met `Route not completed` ("Delivery attempted"). Scans van J&T Express Germany komen in het Duits met plaatsen tussen 【】-haakjes: `【】【】Ihr Sendung wurde abgeholt.` is de eerste scan na vlucht en douane; daarna volgen `【】Die Sendung hat【】verlassen. Weiterversand nach【】` (vertrokken naar de volgende locatie) en `【】Die Sendung ist bei【】 angekommen.` (bij een hub of bezorgdepot vóór de rit), alle "In transit". Waar FedEx bezorgt is `Shipment information sent to FedEx` het label, aangemaakt voordat JS Express het pakket heeft ("Label created"); daarna volgen `Arrived at FedEx location`, `Left FedEx origin facility` en `On the way` ("In transit"). In Hongarije volgen `Arrived to HU HUB`, `Linehaul Transit` (rit van hub naar sorteercentrum) en `Shipment arrived to sorting centre / Shipment in the sorting centre`, alle "In transit". In Spanje betekent `Enviado` van CTT Express dat het pakket na inname is doorgestuurd vóór de bezorgrit ("In transit").

**Een bezorgdienst zonder naam.** Op sommige lijnen geeft JS Express scans van een lokale bezorger in hoofdletters door zonder naam of land. `SHIPMENT RECEIVED`, `PROCESSED AT HUB` en `SHIPMENT PROCESSED` betekenen dat de hub het pakket heeft verwerkt, en `DEPARTED FROM DEPOT` dat het naar het bezorgdepot vertrekt, alle "In transit". `RECEIVED AT LOCAL DEPOT` is dat depot vóór elke rit en na een gemiste poging: het eigen depot van de bezorger, geen afhaalpunt, dus Peek behoudt de vorige status. `DELIVERY ATTEMPTED: 1ST DELIVERY ATTEMPT`, `DELIVERY ATTEMPTED: 2ND DELIVERY ATTEMPT` en `DELIVERY ATTEMPTED: 3RD DELIVERY ATTEMPT` zijn gemiste pogingen ("Delivery attempted"). Na de derde vraagt de bezorger een levering in te plannen; `SHIPMENT REDIRECTED: ON CONSIGNEE'S REQUEST` betekent dan dat het pakket onderweg is naar de door jou gekozen bezorging ("In transit"). `DELIVERY EXCEPTION` is een probleem op de hub vóór een rit, en `DELIVERY EXCEPTION, WAIT FOR INFORMATION CONFIRMATION` na `ARRIVE AT THE WAREHOUSE` ("In transit") betekent dat de bezorger het pakket vasthoudt tot gegevens zijn bevestigd: Peek toont beide als "Needs attention". Toont Peek geen bezorgnummer, vraag de verkoper wie bezorgt en wat de bezorgdienst nodig heeft.

**Regels die geen scans zijn.** `SHIPPING INFORMATION RECEIVED`, `SHIPMENT CREATED`, `Data Received`, USPS's `Shipping Label Created, USPS Awaiting Item`, `Package information is created in the system` en, op een Europese hub, `Shipment Data Uploaded` gevolgd door `Processed` zijn enkel een elektronische vooraanmelding van een partner, soms dagen voordat hij het pakket heeft. `Consignee Contacted`, vastgelegd op het moment van een gemiste bezorging, is zijn telefoontje of bericht aan jou en verandert de status evenmin.

**Tijden niet op volgorde.** De tijden hebben geen tijdzone en mengen klokken: Peking-tijd in China, lokale tijd op luchthavens en bij de meeste bezorgers, en UTC voor Straightship (uren voor op Canadese tijd). Let op de volgorde van de regels, niet op de kloktijden. In Peek tonen de Duitse regels van J&T Express Germany de tijden van de eigen tracker van J&T Express Germany.

## Wie bezorgt JS Express-pakketten

**Verenigde Staten.** De trackinggeschiedenis van JS Express toont [USPS](carrier:usps) tot aan de bezorging, [GOFO](carrier:gofo) vanaf het sorteercentrum tot de bezorgrit, of [SpeedX](carrier:speedx), waarvan de scans geen naam tonen. Zodra USPS het pakket heeft, accepteert USPS een Missing Mail-aanvraag van afzender of ontvanger vanaf 7 dagen na verzending.

**Canada.** [Straightship](carrier:straightship), een Canadese koerier, bezorgt een deel van de JS Express-pakketten; zijn scans staan in de geschiedenis van JS Express.

**Duitsland, Frankrijk, Spanje en Europa.** De Europalijn van JS Express bedient landen als het VK, Frankrijk, Duitsland, Italië, Spanje, Zwitserland en Denemarken, al zijn sommige afgelegen postcodes uitgesloten. Voor Duitsland noemt JS Express DPD en andere bezorgers voor de laatste mijl en meldt dat ruim 98% van de pakketten binnen 8 werkdagen wordt bezorgd; in de praktijk zijn daar scans van [DHL Paket](carrier:dhl), [iMile](carrier:imile) vanuit een Duits centrum en [J&T Express Germany](carrier:j-and-t-de) in het Duits te zien. In Frankrijk zijn scans van [Colissimo](carrier:la-poste) in het Frans gezien, en in Spanje van [CTT Express](carrier:ctt-express).

**VK, Ierland, Australië, Nederland en elders.** Op sommige lijnen toont de geschiedenis scans van [FedEx](carrier:fedex). In het VK toont JS Express scans van Yodel; twee daarvan, `DESPATCHED AND ON ROUTE TO YODEL` en `PARCEL PICKED UP`, verschijnen al terwijl het pakket nog in het magazijn van JS Express in China ligt. Voor Australië noemt JS Express Australia Post en Fastway (nu [Aramex Australia](carrier:aramex-au)) onder 4 kg en DPEX daarboven. Voor Nederland en België noemt JS Express op deze pagina's geen vaste bezorger. De gegevens van JS Express kunnen het nummer van de lokale bezorger bevatten, maar niet zijn naam, en de pagina van JS Express toont geen van beide. Peek noemt de bezorger wanneer dat nummer past bij één vervoerder in jouw land, en vraagt de nationale post voor een [postnummer](guide:tracking-number-formats) (Engels; twee letters, negen cijfers, twee letters); zie hieronder. Noemt Peek geen bezorger, vraag dan aan de verkoper wie bezorgt.

Bezorgpogingen, niet-thuisberichten en afhaalpunten volgen de regels van de lokale bezorger: JS Express publiceert er geen. Een regel `到达待取` noemt alleen het land, dus kijk in de tracking van de bezorger of vraag de verkoper waar het pakket wacht.

## JS Express-pakket komt niet aan of tracking staat stil

**"未获取到任何数据,请提供有效单号!"** De Engelse pagina van JS Express antwoordt dit in het Chinees voor een onbekend nummer: "geen gegevens gevonden, geef een geldig vrachtbriefnummer op". `No track data for now!` betekent dat er nog geen scans zijn. Voor beide toont Peek "Waiting for JS Express" en blijft het controleren. Kent JS Express het nummer twee dagen later nog steeds niet en heeft niets anders ervoor geantwoord, dan toont Peek "After two days, the carrier still doesn’t know this number. Check it, choose another carrier or delete the parcel." Controleer het nummer teken voor teken; verschijnt er na een paar dagen nog niets, vraag de verkoper of het pakket wel is verzonden.

**Blijft op `Electronic notification of order creation received.`** JS Express heeft het pakket nog niet opgehaald. Vraag de webshop wanneer het is overgedragen.

**Blijft op `Inbound to customs temporary warehouse`.** Het pakket wacht op de douane van jouw land. Op sommige routes staan `Start Custom clearance Process` en daarna `customs clearance completed`; een eerdere `Under customs clearance, please wait patiently.` is de Chinese uitvoer-douane op de vertrekluchthaven, dus daarvoor toont Peek "In transit". JS Express verkoopt zijn Europalijn en standaard VS-lijn met betaalde invoerrechten (DDP): het int de invoerbelastingen vooraf bij de webwinkel, zodat je op die lijnen niets hoeft bij te betalen. Vraagt een bezorger toch om betaling, check dan eerst bij de verkoper. In de EU (inclusief Nederland en België) geldt sinds 1 november 2026 een afhandelingsvergoeding van € 2 per soort product; de verkoper of de bezorgende partij betaalt deze en kan hem doorberekenen. Zie [parcels held at customs](guide:customs) (Engels); blijft het pakket dagenlang liggen, meld het bij de verkoper. Meer in [why tracking stops updating](guide:tracking-not-updating) en [tracking a parcel from China](guide:tracking-from-china) (Engels).

**Niets na `Delivery to local courier`.** De lokale bezorger heeft het. Toont Peek het nummer van die bezorger, zoek het dan op in diens eigen tracking; zo niet, vraag de verkoper wie bezorgt.

**Bezorgd, maar niets ontvangen.** Vraag eerst bij de lokale bezorger (USPS in de VS), daarna bij de verkoper. Op de VS-lijn helpt JS Express een door de bezorger gemelde maar niet ontvangen levering na te trekken, maar betaalt er geen vergoeding voor, dus het beleid van de verkoper beslist. Zie [delivered but not received](guide:delivered-not-received) (Engels).

**Verloren, beschadigd of retour.** De verkoper is de klant van JS Express. Volgens de voorwaarden van JS Express moet de klant binnen 30 dagen na ontvangst door JS Express schriftelijk reclameren, en voor verzekerde pakketten via zijn eigen account. Vraag dus op tijd bij de webwinkel of marktplaats om terugbetaling of een nieuw pakket. Een onbezorgbaar pakket (niet thuis, geweigerd, fout adres, douane) wordt op verzoek van de afzender tegen betaling teruggestuurd of vernietigd; op zijn VS-lijn stuurt JS Express een retourpakket noch terug naar China noch opnieuw op pad. Op sommige routes betekenen `Refused` en daarna `Returned` dat het pakket aan de deur is geweigerd en terug is in het sorteercentrum van de bezorger, nog niet bij de verkoper: Peek toont "Needs attention". Zie [lost parcels](guide:lost-parcel) en [returned to sender](guide:returned-to-sender) (Engels).

## JS Express contact en klantenservice

Op de Chinese contactpagina van JS Express stonden op 30 oktober 2026 de volgende gegevens. De Engelse contactpagina is ouder en vermeldt nog andere nummers en adressen.

- **Hotline**: 400-0353-518, een Chinees servicenummer dat ook voor internationale vragen wordt opgegeven. JS Express vermeldt geen openingstijden en zegt niet of het vanuit het buitenland bereikbaar is.
- **QQ en WeChat**: zakelijke QQ 4000353518 en het officiële WeChat-account js-express.
- **Klachten**: een formulier op de Engelse klachtenpagina en dezelfde hotline.
- **Kantoren**: hoofdkantoor en magazijn in Suzhou, meer magazijnen in China, en kantoren van het netwerk in Hongkong en Walnut, Californië. Het enige telefoonnummer van het Amerikaanse kantoor is de Chinese hotline.

Claims bij JS Express verlopen via de verkoper die de zending boekte. Voor je bestelling vraag je de verkoper; voor de bezorging de lokale bezorgdienst.

## Een JS Express-pakket volgen met Peek

[Peek](/) herkent een JS Express-nummer aan zijn vorm: `JSE`, twee letters, tien cijfers en `YQ`, die geen andere vervoerder in de catalogus gebruikt, en vraagt daarmee de tracker van JS Express op. Heb je alleen het nummer van de lokale bezorger, plak dat dan in plaats daarvan: Peek volgt meer dan 4.500 vervoerders.

Peek toont de geschiedenis van de Engelse pagina van JS Express, elke scan met plaats, en het land van bestemming, dat die pagina weglaat. `到达待取` verschijnt als "Arrived, awaiting collection". Telefoonnummers worden verwijderd; de naam van wie tekende blijft staan. Bezorgfoto's en het bestelnummer van de winkel die soms in de gegevens van JS Express zitten laat Peek weg. Als verwachte bezorging toont het de dag van JS Express' schatting; zodra het pakket in bezorging is, inclusief Straightships `Package will be delivered before 9:00pm`, toont het geen dag meer, omdat "Out for delivery" al vandaag betekent. Regels die geen scans zijn veranderen de status niet.

Peek leest regels in China op Peking-tijd, die van Straightship op UTC en Duitse regels van J&T Express Germany met de door elke regel genoemde tijdzone, leidt de klok van overige regels af uit plaats of bestemming en toont alles in de tijdzone van jouw apparaat, nieuwste bovenaan.

Past het nummer van de bezorger bij één vervoerder in jouw land, zoals een USPS-barcode in de VS of een Straightship-nummer in Canada, dan toont Peek "Delivery with" die vervoerder met zijn nummer en vraagt die ook op; een internationaal postnummer gaat naar je nationale post. Zolang die vervoerder alleen de vooraanmelding heeft zonder eigen scan, staat er in plaats daarvan "Announced to …, not received yet". Zodra die vervoerder een scan toont die minstens zo recent is als de nieuwste van JS Express, volgt Peek het pakket daar verder, met beide nummers en behoud van de scans van JS Express. Tot die tijd blijft Peek bij JS Express, wiens geschiedenis vaak ook de scans van de bezorger doorgeeft.

Zolang je het pakket open hebt, of op de bezorgdag met meldingen aan, controleert Peek tot elke 10 minuten, en vanaf 6:00 lokale tijd op de dag dat het pakket in bezorging gaat tot elke 2 minuten (elke 5 als het de JS Express-tracker in een browser moet openen), zolang de eigen tracker van JS Express antwoordt; anders elk uur. Via trackingdiensten is dat elk uur, na een stille dag elke 3 uur en op de bezorgdag onder dezelfde voorwaarde tot elke 15 minuten. Neemt een andere vervoerder de bezorging over, dan volgt Peek het tempo van die vervoerder. Met een SpeedX-nummer voor een pakket naar de VS kan Peek het volgen via [SpeedX](carrier:speedx). Na twee dagen zonder nieuws (12 uur zolang het pakket nog "Label created" of "Received by carrier" toont) controleert Peek elk uur. Peek kan je een melding sturen bij elke scan, alleen bij belangrijke stappen (douane, problemen, "Ready for pickup", in bezorging, bezorging en retour) of met "Delivery only". Peek kan JS Express of de verkoper niet voor je benaderen.

## Vragen over JS Express-tracking

### Wat is JS Express (急速国际)?

Een Chinees logistiek bedrijf voor internationale webwinkels, beheerd door 急创速供应链管理（苏州）有限公司. Het geeft aan in 2010 in Hongkong te zijn gestart en in 2011 zijn basis in Suzhou te hebben geopend.

### Waar kan ik een JS Express-pakket volgen?

In het vak hierboven of op de Engelse "Express Query"-pagina op js-exp.com. De Chinese pagina vraagt eerst om een afbeeldingscode.

### Wat betekent 未获取到任何数据 bij JS Express?

"Geen gegevens gevonden": de tracker van JS Express kent het nummer niet, door een typfout of omdat JS Express het pakket nog niet heeft ontvangen. Controleer het bij de verkoper.

### Wie bezorgt JS Express-pakketten in mijn land?

Een bezorgdienst in jouw land. De geschiedenis van JS Express toont USPS, GOFO of SpeedX in de VS, Straightship in Canada, Yodel in het VK, DHL, iMile of J&T Express in Duitsland, Colissimo in Frankrijk en CTT Express in Spanje, plus FedEx op sommige lijnen. Vraag elders aan de verkoper welk bedrijf bezorgt.

### Met wie neem ik contact op bij een vermist JS Express-pakket?

Met de verkoper bij wie je bestelde: die heeft de zending bij JS Express geboekt en dient claims in. De hotline van JS Express, 400-0353-518, is een Chinees nummer.

:::sources
- [JS Express: 急速国际 (startpagina)](https://www.js-exp.com/) – beherend bedrijf
- [JS Express: Introduction](https://www.js-exp.com/EN/index.php?page=6) – Hongkong 2010, Suzhou 2011, grensoverschrijdende e-commerce
- [JS Express: 企业介绍 (over het bedrijf)](https://www.js-exp.com/index.php?page=6) – Suzhou 2011, vestigingen, platformen
- [JS Express: Express Query](https://www.js-exp.com/EN/index.php?page=19) – Engelse tracker, wat hij toont, zijn antwoorden
- [JS Express: 快件查询 (tracking)](https://www.js-exp.com/index.php?page=19) – afbeeldingscode
- [JS Express: Insurance Claims](https://www.js-exp.com/EN/index.php?page=29) – klantclaims, 30 dagen, onbezorgbare pakketten
- [JS Express: 投保理赔 (verzekeringsclaims)](https://www.js-exp.com/index.php?page=29) – 30 dagen vanaf ontvangst door JS Express, claims via klantaccount
- [JS Express: Complaint](https://www.js-exp.com/EN/index.php?page=32) – formulier, hotline
- [JS Express: Contact Us](https://www.js-exp.com/EN/index.php?page=35) – hotline, QQ, WeChat, oudere nummers en adressen
- [JS Express: 联系我们 (contact)](https://www.js-exp.com/index.php?page=35) – hotline voor internationale vragen, QQ, WeChat, kantoren
- [JS Express: 国际专线 (internationale lijnen)](https://www.js-exp.com/index.php?catid=13) – Duitslandlijn: DPD en anderen, meer dan 98% binnen 8 werkdagen; Australiëlijn: Australia Post, Fastway, DPEX
- [JS Express: 急速美国专线 (VS-lijn)](https://www.js-exp.com/index.php?aid=27) – rechten betaald (DDP), retouren niet terug naar China noch opnieuw verstuurd, bezorgd maar niet ontvangen
- [JS Express: 急速欧洲专线 (Europalijn)](https://www.js-exp.com/index.php?aid=35) – landen, rechten betaald (DDP), afgelegen postcodes
- [Aramex Australia: About Aramex Australia](https://www.aramex.com.au/why-aramex/about-us/) – Fastway werd Aramex Australia
- [Revenue: Union Handling Fee press release](https://www.revenue.ie/en/corporate/press-office/press-releases/2026/pr-092926-handling-fee.aspx) – € 2 per soort product vanaf 1 november 2026, betaald door verkoper of bezorger, mag worden doorberekend
- [USPS: Missing Mail and Lost Packages](https://www.usps.com/help/missing-mail.htm) – afzenders of ontvangers, vanaf 7 dagen
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – nummervormen, scanteksten en stadia, klokken (Duitse regels van J&T Express Germany op hun opgegeven offset sinds 9 november), scans van USPS, GOFO, SpeedX, Straightship, Yodel, DHL, iMile, J&T Express Germany, FedEx, Colissimo en CTT Express, scans van een naamloze bezorger (hub, depot, gemiste poging, omleiding, uitzondering), hubscans van een naamloze VS-partner, Duitse centra van iMile, browser-fallback, douane, weigeringen en retouren, bezorgnummer, wat Peek toont en hoe het pakketten overdraagt
:::
