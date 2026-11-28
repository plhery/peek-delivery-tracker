---
title: JRT pakket volgen: JRT…MM-nummer, scans en lokale bezorging
description: Volg een JRT-pakket (今日通, Jinritong) uit Xiamen, China: JRT…MM-formaat, Chinese en Engelse vluchtscans, overdracht aan DHL/UPS/FedEx en contact.
slug: jrt-pakket-volgen
published: 2026-11-27
updated: 2026-11-27
---

JRT (今日通, Jinritong, 厦门今日通商贸有限公司) is een grensoverschrijdende expediteur uit Xiamen in China met trackingnummers van `JRT`, tien cijfers en `MM`. Plak je `JRT…MM`-nummer in het vak hierboven om het pakket te volgen vanuit China via vluchtvertrek, invoerdouane en overdracht aan de lokale bezorgdienst: de tracker van JRT geeft scans door van DHL in Europa (CTT Express in Spanje, InPost in Polen en FedEx op de Ierland-route), UPS in de Verenigde Staten en J&T Express in Mexico.

## JRT-trackingnummer: hoe het eruitziet

De eigen vrachtbrieven van JRT bestaan uit `JRT`, tien cijfers en `MM`, in totaal 15 tekens; `JRT` zijn de initialen van 今日通. Voorbeelden met verzonnen cijfers:

| Nummer | Wat het is |
| --- | --- |
| `JRT4829105736MM` | `JRT`, tien cijfers en `MM`: een geldig JRT-vrachtbriefnummer. |
| `JRT482910573MM` | Slechts negen cijfers: past niet bij JRT. Controleer of er een cijfer ontbreekt. |
| `JRTO4829105736MM` | Een letter `O` na `JRT`: Peek leest dit als `JRT4829105736MM`, de enige vorm die de tracker van JRT kent. |
| `UP4829105736182940` | `UP` en zestien cijfers: een referentie van de afzender die een JRT-order kan terugvinden, al bewijst niets dat alleen JRT-verkopers die vorm gebruiken. |
| `RR482910576CN` | Twee letters, negen cijfers en `CN`: een nummer van [China Post](carrier:china-post), niet van JRT. |

Spaties en kleine letters maken in Peek niets uit. De tracker van JRT vindt een zending ook op het eigen referentienummer van de verkoper als je dat exact zo intypt als opgegeven, inclusief streepjes.

**Een link met cijfers in plaats van een domeinnaam.** De tracker van JRT draait op een kaal serveradres over onversleuteld HTTP zonder webdomein, en Peek kent geen andere website van JRT. Een link ernaartoe heeft dat numerieke adres op de plek van de sitenaam en bevat `trackIndex.htm`, `documentCode=` en je nummer of referentie: die link kun je zo in Peek plakken. Peek linkt zelf niet naar de tracker, omdat een onversleutelde HTTP-link je nummer leesbaar zou meesturen.

## Betekenis van de statussen bij JRT

Veel regels van JRT staan in het Chinees gevolgd door JRT's eigen Engelse vertaling, en Peek toont ze volledig. Omdat de app van Peek niet in het Nederlands is vertaald, zie je de eigen fasen van Peek in het Engels: "Label created" (label aangemaakt), "Received by carrier" (ontvangen door vervoerder), "In transit" (onderweg), "At customs" (bij de douane), "Out for delivery" (in bezorging), "Delivery attempted" (bezorgpoging), "Ready for pickup" (klaar om af te halen), "Delivered" (bezorgd) en "Returned to sender" (retour afzender). In de gebruikelijke volgorde:

| JRT zegt | Wat dit voor jou betekent | Peek toont |
| --- | --- | --- |
| `货物电子信息已经收到` | "Elektronische vrachtinformatie ontvangen": de verkoper heeft de order aangemaakt. Automatisch geschreven door platform Hualei (华磊), geen fysieke scan. | "Label created" |
| `Shipment information received` | De verzendgegevens zijn binnengekomen bij JRT; het pakket is nog niet gescand. | "Label created" |
| `到达收货点` | Aangekomen bij het ontvangstpunt van JRT: de eerste fysieke scan in handen van JRT. | "Received by carrier" |
| `货物离开操作中心` | Het pakket heeft het verwerkingscentrum van JRT verlaten. | "In transit" |
| `深圳集散中心 已收货` | De hub van JRT in Shenzhen heeft het pakket ontvangen, na het verwerkingscentrum en vóór de vlucht. Peek toont dit in het Engels: "Received at the Shenzhen distribution center". | "In transit" |
| `货物打包完成，已发往航司` | Ingepakt en onderweg naar de luchtvaartmaatschappij vóór de vlucht. Peek toont "Goods packed and sent to the airline". | "In transit" |
| `抵达航司已起飞` | Aangekomen bij de luchtvaartmaatschappij en vertrokken vanaf de genoemde luchthaven. Peek toont "Arrived at the airline, flight departed". | "In transit" |
| `货物已交机场,等待航班安排中 Cargo delivered to airport, pending flight arrangement` | Afgeleverd op de luchthaven in afwachting van een vlucht. "Delivered" betekent hier op de luchthaven, niet bij jou thuis. | "In transit" |
| `报关已放行等待…号航班起飞 Customs released,Awaiting departure on flight of the …` | Vrijgegeven voor export in China, wachtend op de vlucht van de genoemde dag. | "In transit" |
| `航班起飞，以实际抵达为准 Flight departure subject to actual arrival time` | Het vliegtuig is vertrokken. | "In transit" |
| `航班落地，拆板中 Flight landing, dismantling board in progress` | Geland. De luchtvrachtpallets worden uit elkaar gehaald vóór de douane. | "In transit" |
| `机场内等待提货，等待清关 Waiting for cargo pickup and customs clearance at the airport.` | Op de aankomstluchthaven, wachtend op ophaling en invoerdouane. | "At customs" |
| `理货较慢，导致提货缓慢，已催促尽快处理 Slow cargo sorting has resulted in delayed pickup. We have followed up for prompt handling.` | Trage sortering op de luchthaven vertraagt de ophaling en JRT heeft aangedrongen op versnelling. Een vertraging, geen verloren pakket. | Geen nieuwe status: vorige fase blijft staan |
| `已提货，清关中 The goods have been picked up and are currently undergoing customs clearance` | De invoerinklaring is bezig. | "At customs" |
| `清关完成，卡车发运 Customs clearance completed; truck dispatched.` | Door de douane heen en verder per vrachtwagen. | "In transit" |
| `已交服务商 Delivered to service provider` | Overgedragen aan de lokale vervoerder voor het laatste stuk. Niet aan jou bezorgd. | "In transit" |

Sommige regels die JRT alleen in het Chinees schrijft, toont Peek in het Engels zoals andere expediteurs ze schrijven: `货物电子信息已经收到` als "The electronic cargo information has been received", `到达收货点` als "Arrived at the receiving point", `货物离开操作中心` als "Goods leave the operation center" en `快件操作完成` als "Shipment departed from facility". De luchthavenregel `货物已交机场,预计…号的航班 Goods delivered to airport, scheduled on flight of the …` toont Peek als "Goods delivered to airport", omdat de vlucht nog moet plaatsvinden. Drie andere puur Chinese regels vóór de vlucht verschijnen ook onder "In transit": `分拣包裹已出库` ("Sorted parcel has left the warehouse"), `货物到达口岸，预计…号航班起飞` ("Arrived at the port, booked on a flight") en `预计…号航班飞` ("Booked on a flight").

Op de route naar Europa verschijnen nog drie latere regels alleen in het Chinees, die Peek in het Engels toont onder "In transit": `航班已抵达` ("Flight has arrived"), `清关完成，中转德国中` ("Customs cleared, in transit to Germany") en `抵达德国，已交服务商` ("Arrived in Germany, handed to the delivering carrier", overgedragen aan het lokale bezorgbedrijf zoals DHL — geen bezorging aan jou).

**Over de weg via Kazachstan.** Op de wegtransportroute van JRT naar Europa verlaat het pakket China over land in plaats van per vliegtuig, met vijf Chinese regels die Peek in het Engels toont onder "In transit": `已装柜发车，待抵达新疆` ("Loaded into a container and on its way to Xinjiang"), `已抵达新疆，换装/报关，待出境` ("Arrived in Xinjiang for transloading and export declaration, waiting to leave China"), `因哈萨克斯坦拥堵，外车入境新疆拥堵缓慢，在等待外车倒装后报关出境。` ("Congestion in Kazakhstan is slowing foreign trucks into Xinjiang; the parcel will be declared for export once it is reloaded onto one" — wachttijd aan de grens, geen kwijtgeraakt pakket), `已出境，哈萨克斯坦阿拉木图` ("Left China, Almaty, Kazakhstan") en `哈俄边境，待办理手续进入俄罗斯` ("At the Kazakhstan–Russia border, awaiting the formalities to enter Russia", een doorvoergrens onderweg, niet de douane in Nederland of België).

**Regels alleen in het Engels.** Andere JRT-regels komen uitsluitend in het Engels, alle als "In transit" tenzij anders vermeld:

- `Shipment arrived at facility`, `Sending to the airport`, `The goods have been packed and sent to the airline`, `Package has been packed and delivered to airport`, `Cargo handed over to the airline`: onderweg naar de vlucht. `Domestic customs clearance completed` is de Chinese exportinklaring, niet jouw eigen douane.
- `Arriving at the airport - arranging flights`, `Arriving at the airline, in the process of booking a flight`: vlucht wordt ingeboekt.
- `Flight is expected to take off on …`, `The flight is expected to depart on … and land on …`, `Estimated departure is …` of `Estimated arrival date is …`: de vlucht is geboekt.
- `Flight has flown`, `The flight has departed and is expected to … land`, `The flight has taken off`: in de lucht.
- `Sorry, the second flight has been delayed and is expected to … land`: aansluitende vlucht is vertraagd, met nieuwe landingsdatum.
- `During transportation to …`: onderweg naar het genoemde bestemmingsland.
- `Flight has arrived`, `The flight has arrived and is awaiting dismantling`, `Arrived at Destinated Airport`, `The NOA has been received` (aankomstbericht van de luchtvaartmaatschappij), `Dismantling and picking up goods in progress`: geland, nog vóór de douane.
- `Customs clearance of the cargo is in progress` en `Arrived at customs clearance warehouse`: "At customs". Daarna `Customs clearance completed` of `Customs clearance in destination country completed`.
- `In transit to final service provider`, `Delivery to final service provider`, `The goods have been handed over to the carrier`, `The cargo has been picked up and arrived at the warehouse，awaiting outbound shipment`, `It has been handed over to the service provider.` en `It has been transferred to the provider`: op weg naar of overgedragen aan de lokale bezorger, geen levering aan jouw deur.
- `returned shipment has been received by processing center`: het retourpakket is terug bij het centrum van JRT ("Returned to sender").

In `The flight is expected to depart on … and land on …`, `The flight has departed and is expected to … land` en `Sorry, the second flight has been delayed and is expected to … land` staan de datums als maand.dag: `11.12` is 12 november.

**Regels met schuine streep of komma.** Sommige pakketten combineren Chinees en Engels met een schuine streep of komma, alle onder "In transit": `预计…航班/Flight scheduled for …`, `航班已启运/Package in transit to destination country`, `航班已落地，拆板中/Package arrived at destination country`, `货物已清关完成/Import customs clearance complete`, `国内清关完成` (Chinese exportinklaring), `预计配载航班…月…日,Expected flight on …` (geplande vluchtdag, geen bezorgdatum) en `航班已起飞,The flight has taken off from the origin`.

**Doorgestuurde regels van DHL en FedEx.** Naar Europa kopieert de tracker de scans van DHL, vaak in kleine letters: `the instruction data for this shipment have been provided by the sender to dhl electronically` ("Label created"), `shipment in transit to dhl`, `A preferred location was designated as a reception option for this shipment.` (voorkeursplek vastgelegd, verandert de fase niet), `The shipment has been posted by the sender at the retail outlet` (door JRT ingeleverd bij een Deutsche Post-punt), `pick-up was successful.` (opgehaald door DHL, niet door jou), `the shipment has been processed in the parcel center of origin`, `loaded to movement / tour vehicle`, `The shipment has arrived in the destination country/destination area.`, `The shipment has left the import parcel center in the destination country/destination area.` en `The shipment is being prepared for delivery in the delivery depot` ("In transit"); `The shipment has been loaded onto the delivery vehicle` ("Out for delivery"); `not delivered-consignee could not be reached`, `The shipment could not be delivered. Please find more information here.`, `The recipient was not present. Please find more information here.` en `not delivered-bad address` ("Delivery attempted"); `held for consignee collection` en `The shipment is ready for collection from a ParcelShop/retail outlet. Please find more information here.` ("Ready for pickup"); `shipment will be returned to origin-not ordered` en `shipment will be returned to origin-bad address` ("Returned to sender"); en `the shipment has been successfully delivered` ("Delivered", of "Returned to sender" als het op een retourmelding volgt). Op de FedEx-route naar Ierland openen sommige zendingen met `Shipment information sent to FedEx` ("Label created"), gevolgd door FedEx-scans zoals `Arrived at FedEx hub`, `Departed FedEx hub`, `On the way` en `Delivery option requested, hold request received - check back later for shipment status` ("In transit").

**Doorgestuurde regels naar de VS (UPS) en Mexico (J&T Express Mexico).** Naar de Verenigde Staten schrijft JRT eigen Chinese regels (`…号航班`, `航班已飞`, `航班抵达`, `提货中`, `清关完成，已提货，待交仓`, `已交仓`) die Peek in het Engels toont onder "In transit", gevolgd door Chinese UPS-regels die Peek omzet naar de Engelse termen van UPS (`Shipper created a label, UPS has not received the package yet.`, `Awaiting delivery to a UPS Access Point location.`, `Arrived at Facility`, `Departed from Facility`, `Processing at UPS Facility`, `Picked Up`, `Loaded on Delivery Vehicle`, `Out For Delivery Today`, `Delivered`). Naar Mexico kopieert de tracker de Spaanse regels van J&T Express Mexico met stad en vestiging tussen 【】-haakjes tot aan `…está en camino a tu domicilio.` ("Out for delivery").

**Tijden.** Op de route naar Europa staan de eigen regels van JRT in Pekingtijd (`UTC+8`): in de winter is 17:00 in Peking gelijk aan 10:00 Nederlandse en Belgische tijd (`UTC+1`). Gekopieerde DHL-regels leest Peek op de lokale tijd van de genoemde plaats (of Duitse tijd / tijd van het bestemmingsland zonder plaatsnaam), UPS-regels op Amerikaanse lokale tijd en J&T Express Mexico-regels op de tijd van Mexico-Stad.

## Wie bezorgt JRT-pakketten in Nederland, België en Europa

**Naar Europa** eindigen de meeste routes van JRT bij [DHL](carrier:dhl), behalve de FedEx-route naar Ierland ([FedEx](carrier:fedex)), de Spanje-route met CTT Express en de Polen-route met InPost. Op de DHL-routes levert JRT het pakket in bij een punt van Deutsche Post en neemt de tracker de DHL-scans over. Zodra JRT het pakket heeft overgedragen, staat in het record van JRT het DHL-nummer in plaats van het JRT-nummer. Herkent Peek dat nummer als DHL, dan toont het "Delivery with DHL" met het nummer (of "Announced to DHL, not received yet" zolang DHL alleen de vooraanmelding heeft) en schakelt het over naar de eigen tracking van DHL zodra DHL een eigen scan plaatst. Staat er geen DHL-nummer of bezorgt een andere lokale partner in Nederland of België, vraag de verkoper dan om naam en trackingnummer van die lokale vervoerder. Bezorgpogingen, afhaalpunten en bewaartermijnen volgen de regels van die lokale bezorgdienst.

**Douanekosten in de EU.** Op de luchtvrachtroutes van JRT gaat het pakket op de aankomstluchthaven door de invoerdouane (de "At customs"-regels hierboven). Sinds 1 november 2026 rekent de Europese Unie een afhandelingsvergoeding (Union handling fee) van € 2 per productcategorie voor online aankopen van buiten de EU, naast eventuele btw en invoerrechten. Zie de gids over [pakketten bij de douane](guide:customs) (in het Engels).

**Naar de Verenigde Staten en Mexico.** Naar de VS draagt JRT over aan UPS (`已交仓`) en naar Mexico aan J&T Express Mexico; elders weet Peek niet welke lokale bezorger het overneemt.

## JRT-pakket komt niet aan of tracking staat stil

**Alleen de eerste regels.** `货物电子信息已经收到`, `Shipment information received` of alleen een DHL-/FedEx-labelregel betekent dat de verkoper de zending heeft aangemeld, maar dat niemand het pakket fysiek heeft gescand. Vraag na een paar dagen aan de verkoper of het echt is verzonden.

**Wachten op een vlucht.** Een vluchtregel noemt de geplande vertrekdag, en `航班延误，预计…号飞出 Flight delayed, estimated departure on …` geeft een nieuwe datum. Is die dag voorbij zonder vertrekscan, meld het aan de verkoper.

**Niets meer na `已交服务商`, `Delivery to final service provider` of `已交仓`.** Het deel van JRT is klaar. Als de tracker geen scans van DHL, UPS, FedEx of J&T kopieert en Peek geen lokaal nummer toont, staan de volgende stappen op de tracking van de lokale bezorger: vraag de verkoper om dat nummer. Meer in de gids over [pakketten uit China volgen](guide:tracking-from-china) (in het Engels).

**Niets gevonden.** Voor een onbekend nummer geeft de tracker van JRT één lege rij met het nummer als referentie. Peek behandelt dat als niet gevonden: eerst "Waiting for JRT", en twee dagen later "Check the tracking number". Controleer elk teken met het bericht van de verkoper.

**Adres niet gevonden.** Controleer na `not delivered-bad address` meteen het afleveradres van je bestelling en neem direct contact op met de verkoper. Volgt `shipment will be returned to origin-bad address`, dan gaat het pakket terug: vraag de verkoper om terugbetaling of een nieuwe verzending.

**Bezorgd, maar niets ontvangen.** De bezorgscan komt van de lokale bezorgdienst, niet van JRT: neem eerst contact op met die bezorger en daarna met de verkoper. Meer in de gids over [bezorgd maar niet ontvangen](guide:delivered-not-received) (in het Engels).

**Verloren of retour.** De verkoper heeft het pakket bij JRT geboekt, dus vraag de verkoper of het verkoopplatform om terugbetaling of een nieuw pakket.

## Contact en klantenservice van JRT

Peek vond geen contactgegevens voor JRT: geen website naast de tracker en geen telefoonnummer, e-mailadres of bezoekadres voor ontvangers. De verkoper is de opdrachtgever van JRT en je eerste aanspreekpunt, of het verkoopplatform als de verkoper niet reageert.

## Een JRT-pakket volgen met Peek

[Peek](/) herkent een `JRT…MM`-nummer meteen aan zijn vorm (`JRT`, tien cijfers en `MM`, uniek in de catalogus) en aan een link naar de tracker van JRT. Voor een 16-cijferige `UP`-referentie vraagt Peek de tracker van JRT of die het nummer kent en kiest het JRT bij een treffer. Elke andere referentie plak je ook gewoon in Peek — dat meer dan 4.500 vervoerders volgt — waarna je via "Change" handmatig JRT kiest (zoeken op 今日通 of Jinritong werkt ook), of via "Change carrier" bij een al toegevoegd pakket. Bij een referentie toont Peek het JRT-nummer onder "JRT tracks this as" tot aan overdracht; let op dat Peek streepjes weghaalt, dus vraag voor referenties met streepjes het `JRT…MM`-nummer aan de verkoper.

Peek toont de status, elke regel met plaatsnaam en het bestemmingsland uit het record, en baseert zich op de regels zelf in plaats van op de samenvatting. Tijden worden in je eigen tijdzone getoond. Peek houdt regels achter die meer dan twaalf uur vóór hun opgegeven tijdstip zijn geschreven (of meer dan zes uur vóór hun tijdstip in dezelfde seconde als zo'n regel) en regels die na het controlemoment vallen. De geschiedenis opent dan tot aan bezorging of retour met "Other sites may show more steps, but they’re only predictions." en "Show them" toont die regels in het grijs ("Predicted for …") zonder dat ze een status wijzigen of meldingen sturen. Namen, adres, postcodes, gewicht, kosten, handtekening en afleverbewijs laat Peek weg, en er is geen verwachte bezorgdatum omdat JRT die niet geeft.

Wanneer het record van JRT overgaat op een DHL-, FedEx-, [Swiss Post](carrier:swiss-post)- of internationaal postnummer (twee letters, negen cijfers, twee letters) of een nummer dat eenduidig bij één andere vervoerder past, vraagt Peek ook die vervoerder op en schakelt het over zodra die een minstens zo recente scan heeft, met behoud van JRT's eerdere scans. Voor elk ander lokaal nummer van de verkoper voeg je het toe als apart pakket.

Zolang je het pakket open hebt, of op de bezorgdag met meldingen aan, controleert Peek tot elke 10 minuten; anders en wanneer JRT stilvalt (na 12 uur op de eerste regels of na twee dagen zonder nieuws) elk uur. Zodra het pakket "Out for delivery" staat, controleert Peek vanaf 06:00 lokale tijd tot elke 2 minuten zolang JRT of de overgenomen vervoerder direct antwoordt (elke 5 minuten als het opzoeken bij die vervoerder een browser nodig heeft, zoals bij DHL, FedEx en UPS kan). Via trackingdiensten controleert Peek elk uur, elke 3 uur na een rustige dag en tot elke 15 minuten op de bezorgdag, onder dezelfde voorwaarde. Meldingen stel je in op "Every scan", "Important steps" (de "At customs"-regels van JRT en `returned shipment has been received by processing center`, de doorgestuurde bezorgrit-, mislukte poging-, afhaalpunt-, bezorg- en retourregels van DHL, de bezorgrit- en bezorgregels van UPS en `…está en camino a tu domicilio.` van J&T Express Mexico — niet de vluchtregels, `已交服务商` of `已交仓`) of "Delivery only". Peek kan geen contact opnemen met JRT of de verkoper voor je.

## Veelgestelde vragen over JRT

### Wat is JRT (今日通 / Jinritong) en hoe ziet een JRT…MM-nummer eruit?

JRT is 厦门今日通商贸有限公司 (Xiamen Jinritong), een expediteur uit Xiamen in China. De vrachtbriefnummers tellen 15 tekens (`JRT`, tien cijfers en `MM`; negen cijfers betekent dat er een cijfer mist) en de tracker draait op het platform Hualei (华磊).

### Waarom zegt JRT "delivered" terwijl mijn pakket nog niet is aangekomen?

Door de Engelse vertalingen van JRT: `Cargo delivered to airport` betekent dat JRT het pakket op de luchthaven heeft afgeleverd voor de vlucht, en `Delivered to service provider` of `Delivery to final service provider` betekent overdracht aan de lokale vervoerder. Geen van beide betekent bezorging bij jou thuis; Peek houdt ze op "In transit".

### Wie bezorgt JRT-pakketten in Europa, de VS en Mexico?

Naar Europa gaan de meeste JRT-routes over op DHL (met CTT Express in Spanje, InPost in Polen en FedEx op de Ierland-route). Naar de Verenigde Staten bezorgt UPS en naar Mexico J&T Express Mexico. Vraag voor overige bestemmingen de verkoper om de lokale vervoerder en het lokale trackingnummer.

### Moet ik EU-douanekosten betalen voor een JRT-pakket uit China?

Voor online bestellingen van buiten de EU geldt sinds 1 november 2026 de EU-afhandelingsvergoeding (Union handling fee) van € 2 per productcategorie naast eventuele invoerrechten en btw; lees de gids over [pakketten bij de douane](guide:customs) (in het Engels) voor hoe vervoerders die innen.

### Hoe neem ik contact op met JRT over een vertraagd of vermist pakket?

Neem contact op met de verkoper of het verkoopplatform waar je bestelde: de verkoper heeft de verzending bij JRT geboekt. Naast de onversleutelde HTTP-tracker vond Peek geen website, telefoonnummer of e-mailadres voor ontvangers.

:::sources
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – bedrijfsnaam en initialen, Hualei-platform, tracker op kaal serveradres via HTTP, links zonder gepubliceerde link, `JRT…MM`- en `UP`-vormen, `O` na `JRT` gelezen als `JRT`, zoeken op nummer of referentie, regelteksten en fasen, Engels voor Chinese regels, wegroute door Xinjiang en Kazachstan, doorgestuurde DHL-, FedEx-, UPS- en J&T Express Mexico-scans, overdracht naar DHL, FedEx en Swiss Post, Pekingtijd en lokale tijdzones, antwoord bij onbekend nummer en wat Peek toont, achterhoudt en weglaat
- [Europese Commissie: EU Customs Reform (in het Engels)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – € 2 EU-afhandelingsvergoeding sinds 1 november 2026
:::
