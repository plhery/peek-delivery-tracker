---
title: Shouwu-tracking: SWECN…YQ-nummers, scans en wie bezorgt
description: Volg een Shouwu International-pakket (守务国际) uit China: hoe een SWECN…YQ-nummer eruitziet, wat de statussen betekenen en wie bezorgt in het VK en de VS.
slug: shouwu-tracking
published: 2026-11-28
updated: 2026-11-28
---

Shouwu International (守务国际, ook SWE) is een logistiek bedrijf uit Shanghai dat pakketten voor webwinkels in China vervoert naar Europa en de Verenigde Staten, waar een postbedrijf of koerier ze bezorgt. Als een winkel je bestelling met Shouwu heeft verstuurd, begint het nummer met SWECN en eindigt het op YQ: plak het in het vak hierboven of in de tracker van Shouwu op [admin.shshouwu.com](https://admin.shshouwu.com/track.html). De winkel geeft het nummer bij de verzendgegevens van je bestelling.

## Shouwu-trackingnummer: hoe het eruitziet

De vrachtbriefnummers van Shouwu bestaan uit SWECN, twaalf cijfers en YQ, in totaal 19 tekens. Met verzonnen cijfers:

| Nummer | Wat het is |
| --- | --- |
| `SWECN482910573649YQ` | SWECN, twaalf cijfers en YQ: een vrachtbriefnummer van Shouwu. |
| `SWECN48291057364YQ` | Maar elf cijfers: niet het formaat van Shouwu. Controleer of er een cijfer ontbreekt. |
| `JSEAA4829105736YQ` | Eindigt ook op YQ, maar begint met JSE: een nummer van [JS Express](carrier:js-express). |

Ook andere Chinese expediteurs laten hun vrachtbriefnummers eindigen op YQ, met eigen beginletters en in de catalogus van Peek meestal tien cijfers. SWECN en twaalf cijfers kenmerken Shouwu. Spaties, streepjes en kleine letters maken in Peek niet uit.

**Waar je het vindt.** Staat het niet in de verzendmail van de winkel of op je bestelpagina, zie dan [hoe je een trackingnummer vindt (in het Engels)](guide:find-tracking-number).

**Je kunt in plaats daarvan het nummer van de koerier krijgen.** Shouwu laat de verkoper kiezen welk nummer wordt doorgegeven: dat van Shouwu zelf, dat het pakket van begin tot eind volgt, of het nummer van de koerier die in jouw land bezorgt. Een Shouwu-pakket kan je dus bereiken onder een nummer van [Royal Mail](carrier:royal-mail), Yodel of [Evri](carrier:evri) in het VK, of een [USPS](carrier:usps)-nummer in de VS. Plak dat gerust toch in: Peek volgt meer dan 4.500 vervoerders.

**Een trackinglink.** De knop "Copy Link" van Shouwu geeft een link naar zijn tracker met `?q=` en het nummer. Die opent dat pakket, en je kunt de hele link in Peek plakken. De tracker van Shouwu accepteert tot 40 nummers tegelijk, één per regel.

## Trackingstatussen van Shouwu uitgelegd

De tracker van Shouwu is in het Engels onder een Chinese kop. Hij geeft voor elk pakket een status en het bestemmingsland, en vervolgens tijdstip en tekst van elke scan, maar zonder plaatsnaam.

| Status van Shouwu | Wat het voor jou betekent | Peek toont |
| --- | --- | --- |
| `Not Found` | Shouwu heeft nog niets onder dat nummer: "We seem can't identify the number." | "Waiting for Shouwu International" |
| `Preparing` | Shouwu legt dit uit als een pakket dat is afgegeven bij het magazijn in China, of daar wordt gewogen en verzegeld. Dit kan al vóór de eerste scan verschijnen. | "Label created", daarna "Received by carrier" na ophaling door de partner of een magazijnscan |
| `In Transit` | Alles vanaf de overdracht aan een vervoerder tot de douane en het vervoer in jouw land. | "In transit", of de fase van de nieuwste scan zoals "At customs"; "Label created" zolang er alleen de bestelgegevens en `Carrier received the shipment` zijn |
| `Undelivered` | Een bezorgpoging is mislukt. | "Delivery attempted", of "Ready for pickup" of "Returned to sender" als de nieuwste scan dat meldt |
| `Delivered` | Bezorgd bij de geadresseerde. | "Delivered" |

Shouwu noemt zijn statussen automatisch door het systeem beoordeeld ("auto-judged by the system") en alleen ter informatie ("only for information reference use"). De scans zeggen meer:

| Shouwu zegt | Wat het voor jou betekent | Peek toont |
| --- | --- | --- |
| `Shipment information received`, `Order information received. We're expecting your parcel to arrive with us.` | De verkoper heeft het pakket aangemeld. Shouwu heeft het nog niet. | "Label created" |
| `Carrier received the shipment` | Shouwu plaatst dit twee uur na de bestelgegevens, dagen vóór de eerste magazijnscan: niemand heeft het pakket nog. | "Label created" |
| `Picked up by Shipping Partner` | De verzendpartner van Shouwu heeft het pakket bij de verkoper opgehaald: de eerste scan na de bestelgegevens. | "Received by carrier" |
| `Arrival processing center` | Het magazijn van Shouwu in China heeft het pakket ingenomen. | "Received by carrier" |
| `Package processing completed` | Het magazijn is klaar met de verwerking, vóór de vlucht. | "Received by carrier" |
| `Shipment started the customs clearance process at origin` | De uitvoeraangifte in China is begonnen, een vaste stap vóór de vlucht. De douane houdt het pakket niet tegen. | "In transit" |
| `The package is shipped from the transfer warehouse to the airport Export customs clearance in progress` | Verstuurd van het overslagmagazijn naar de luchthaven, waar de Chinese uitvoerdouane bezig is: niet de douane van jouw land. | "In transit" |
| `Export customs declaration completed`, `Export customs clearance completed`, `Export Released` | China heeft het vrijgegeven voor uitvoer; niets wordt vastgehouden. | "In transit" |
| `The parcel has arrived at the airport and is currently being loaded onto a flight` | Op de vertrekluchthaven na de uitvoerdouane, terwijl het op een vlucht wordt geladen. | "In transit" |
| `Arrived at Shanghai Pudong Airport`, `Departed from export airport` | Op de luchthaven in Shanghai en daarna uit China gevlogen. | "In transit" |
| `The ship has already set sail` | Op een zeelijn: het schip is uit China vertrokken, na de uitvoerdouane en voordat het de haven in het buitenland bereikt. | "In transit" |
| `Arrived at airport of Destination`, `Shipment arrived in the destination country` | Geland in jouw land. | "In transit" |
| `NOA received` | De agent in jouw land heeft de aankomstmelding van de luchtvaartmaatschappij ontvangen; de douane volgt hierna. | "In transit" |
| `Shipment started the customs clearance process at destination`, `Customs clearance in progress`, `Currently undergoing customs clearance in the destination country`, `Currently processing import customs clearance`, `Customs clearance for imports` | De douane in jouw land klaart het pakket in. | "At customs" |
| `Custom clearance completed`, `Shipment completed the customs clearance process at destination`, `Customs clearance has been completed`, `Customs clearance in the destination country has been completed` | De douane in jouw land heeft het doorgelaten. | "In transit" |
| `Scheduled For Delivery` | De regel van Shouwu na invoerinklaring, dagen vóór het depot en de bezorgrit van de koerier: bezorging staat gepland, maar is nog niet bezig. | "In transit" |
| `Delivery to local courier`, `The goods have been deliverey to the final delivery service provider`, `The goods have been delivered to the carrier` | Overgedragen aan de koerier die in jouw land bezorgt, niet aan jou bezorgd ("deliverey" is de spelling van Shouwu). | "In transit" |
| `Your consignment has been picked up` | De koerier heeft het na de douane opgehaald bij het buitenlandse depot van Shouwu: niet het ophalen bij de verkoper (dat is `Picked up by Shipping Partner` aan het begin) en niet dat jij het hebt opgehaald. | "In transit" |
| `Picked up` | In de VS heeft FedEx het pakket na invoerinklaring opgehaald bij het depot van Shouwu (de regel vermeldt plaats en postcode). Niet het ophalen bij de verkoper (`Picked up by Shipping Partner`) en niet door jou. | "In transit" |
| `Arrived at FedEx location`, `Departed FedEx location` | Eigen scans van FedEx in zijn vestigingen in de VS nadat het pakket bij het depot van Shouwu is opgehaald. | "In transit" |
| `The shipment has been processed in the destination parcel center` | DHL heeft het gesorteerd in het pakketcentrum dat jouw adres bedient, vóór de bezorgrit. | "In transit" |
| `Mis routed` | De koerier die in Duitsland bezorgt heeft het pakket naar de verkeerde vestiging gestuurd en stuurt het door: een vertraging, geen stop. De bezorgrit kan nog steeds dezelfde dag komen. | "In transit" |
| `Out for delivery` | Op de bezorgrit van de lokale koerier. | "Out for delivery" |
| `Abnormal delivery` | De rit kon niet bezorgen. De reden van de koerier volgt als eigen scan tussen punthaken, zoals `<Incorrect address.>`. | "Delivery attempted" |
| `The recipient is unknown` | De bezorgrit van DHL in Duitsland vond die ontvanger niet op het adres; het pakket gaat terug. | "Delivery attempted" |
| `Shipment will be returned to origin-Bad address` | DHL stuurt het pakket terug omdat het adres niet te vinden was: op de terugweg, nog niet bij de afzender. Vraag de verkoper. | "Needs attention" |
| `Delivered by post man`, `This parcel has been delivered` | De lokale post of koerier heeft het bezorgd. | "Delivered", zodra de status van Shouwu `Delivered` zegt |
| `Shipment sent directly from parcel center to business customer` | DHL heeft het vanuit zijn pakketcentrum rechtstreeks op een zakelijk adres bezorgd. Als DHL het pakket had teruggestuurd, betekent dezelfde regel dat de retour de afzender heeft bereikt. | "Delivered", of "Returned to sender" na een retour |

Wanneer iMile in Italië bezorgt, geeft de geschiedenis van Shouwu de regels van iMile door, met het centrum tussen 【】-haken (bij jou kan een ander centrum staan dan Milan center of ROM 10):

| Shouwu zegt | Wat het voor jou betekent | Peek toont |
| --- | --- | --- |
| `【Milan center】Received.` | Het centrum van iMile heeft het pakket ontvangen. | "In transit" |
| `【Milan center】Parcel shipped to【ROM 10】.`, `【ROM 10】Arrived.` | Doorgestuurd naar het bezorgstation van iMile en daar aangekomen. | "In transit" |
| `【ROM 10】Shipment out for delivery.` | Op de bezorgrit van iMile. | "Out for delivery" |
| `<User not at home>`, `【ROM 10】 Back To DS.` | De bezorger trof je niet thuis en het pakket is terug op het bezorgstation. | "Delivery attempted" |
| `【ROM 10】Parcel shipped to【Milan Transit Center】.` | Na twee mislukte bezorgritten stuurt het station het terug naar het centrum van iMile. | "In transit" |
| `Back to Sender for reason: wrong recipient address or address related problem` | Na de mislukte ritten stuurt iMile het pakket terug, hier vanwege het adres (na de dubbele punt kan een andere reden staan): op de terugweg, nog niet bij de afzender. Vraag de verkoper. | "Needs attention" |
| `【Milan Transit Center】Order Closed.` | iMile sluit de bezorgopdracht zonder te bezorgen; vraag de verkoper. | "Needs attention" |

**Wanneer DPD bezorgt.** Op een lijn waarop [DPD](carrier:dpd-de) bezorgt, gaat de geschiedenis van Shouwu verder in de bewoordingen van DPD, elk eindigend op een punt: `In transit.`, daarna `At parcel delivery centre.`, het bezorgdepot van DPD vóór de rit en opnieuw na een gemiste poging ("In transit"), en `Out for delivery.` ("Out for delivery"). `Unfortunately we have not been able to deliver your parcel. Consignee address not correct.` is een mislukte rit door een fout adres: het pakket gaat terug naar het depot van DPD, niet naar de afzender ("Delivery attempted").

**Regels in het Chinees.** Sommige pakketten krijgen ook regels die Shouwu in het Chinees schrijft; Peek toont ze zoals geschreven. `货物电子信息已经收到` (elektronische gegevens van de goederen ontvangen) komt mee met de bestelgegevens: "Label created". Daarna, allemaal "In transit": `申报过港中` na een batchcode (de zending wordt aangemeld voor vervoer naar Hongkong), `已到港，安排航班中` (op de vertreklocatie, wachtend op een vlucht), `预计23号的航班` (geboekte vlucht, hier voor de 23e; met `二程` ervoor voor het tweede traject), `航班已飞` en `二程航班已飞` (de vlucht, of die van het tweede traject, is vertrokken) en `已落地` (geland). `已落地，清关提货中` (geland, wordt ingeklaard en opgehaald bij de luchtvaartmaatschappij) is "At customs"; `清关完成` (ingeklaard) en `已交仓扫描` (ingescand in het depot van de bezorgende koerier) zijn weer "In transit".

**Tijden die uren afwijken.** Het systeem van Shouwu legt de lokale tijd van elke scan vast alsof het Pekingtijd is, ook bij scans in het buitenland, en zijn tracker zet die om naar jouw tijdzone. Daardoor lijkt een scan in het VK of de VS uren te vroeg en lijkt de vlucht uren korter. Ga uit van de volgorde van de regels. Peek bepaalt de klok van elke scan aan de hand van de plaats of het traject waarop het pakket zat en toont ze in de tijdzone van je apparaat; op peektracker.com en in de iPhone- en Android-app eindigt de geschiedenis met "Times are in your time zone:" en de naam van die tijdzone.

## Wie bezorgt pakketten van Shouwu

Shouwu zegt rechtstreeks samen te werken met USPS, Royal Mail, La Poste, DHL, DPD en Hermes. De Chinese lijnpagina's noemen de koeriers per land:

- **Verenigd Koninkrijk.** De Britse lijnen bestrijken het hele VK. De eindbezorgers zijn Royal Mail, Yodel en Evri (daar gespeld als ERVI); de lijn voor goederen met batterijen gaat voornamelijk via Royal Mail.
- **Verenigde Staten.** De Amerikaanse lijnen bestrijken het hele land, voornamelijk bezorgd door USPS; in geschiedenissen die Peek heeft gelezen bezorgt ook [FedEx](carrier:fedex) daar.
- **Europese Unie.** Shouwu heeft ook lijnen naar Duitsland, afhankelijk van de lijn voornamelijk bezorgd door [DHL](carrier:dhl), [Hermes](carrier:hermes-de) of [DPD](carrier:dpd-de), Frankrijk (voornamelijk [Colissimo](carrier:la-poste)), Nederland (voornamelijk DHL), Zweden (voornamelijk [Early Bird](carrier:early-bird)) en Italië (een economy-lijn van 10 tot 12 werkdagen, bezorgd door UniUni en [Poste Italiane](carrier:poste-italiane); in geschiedenissen die Peek heeft gelezen bezorgt daar ook [iMile](carrier:imile), met de regels hierboven). Ook hier kan de verkoper het nummer van de koerier doorgeven.
- **Ierland, Canada en Australië.** Shouwu vermeldt geen lijn voor deze landen en noemt geen koerier. Vraag de verkoper wie bezorgt.

De tracker van Shouwu geeft geen naam of nummer van de koerier, behalve wat er eventueel in de tekst van een scan staat.

**Gemiste bezorgingen.** Pogingen, niet-thuisberichten en afhaalpunten volgen de regels van de koerier. Bij `Undelivered` zegt de tracker van Shouwu dat je direct contact moet opnemen met de vervoerder voor een nieuwe bezorging of om het pakket vóór de termijn van de vervoerder op te halen, anders kan het worden teruggestuurd ("might be returned to the sender"). De Chinese voorwaarden van Shouwu voor de Britse en Amerikaanse lijnen zeggen dat een onbezorgbaar pakket niet naar China wordt teruggestuurd maar standaard wordt vernietigd. Reageer hoe dan ook snel op een bericht. Zie [gemiste bezorging (in het Engels)](guide:missed-delivery) en [niet-opgehaalde pakketten (in het Engels)](guide:uncollected-parcel).

## Shouwu-pakket komt niet aan of tracking zit vast

**`Not Found`.** Shouwu geeft vier redenen: het heeft het pakket nog niet aangenomen, nog niet gescand, het nummer is onjuist, of het pakket is zo lang geleden verzonden dat de tracking verdwenen is. Verwerking duurt volgens Shouwu meestal enkele dagen. Peek toont "Waiting for Shouwu International" en blijft controleren. Als Shouwu het nummer twee dagen later nog steeds niet kent en niets anders heeft geantwoord, toont Peek "After two days, the carrier still doesn’t know this number. Check it, choose another carrier or delete the parcel." Controleer het nummer teken voor teken; verschijnt er na enkele dagen nog niets, vraag dan de verkoper.

**Blijft hangen op `Preparing`, `Shipment information received` of `Carrier received the shipment`.** Shouwu heeft het pakket nog niet, of heeft het nog niet vanuit zijn magazijn verzonden. Vraag de verkoper wanneer het is afgegeven.

**Vast bij de douane.** Na `Shipment started the customs clearance process at destination`, `Customs clearance in progress`, `Currently undergoing customs clearance in the destination country`, `Currently processing import customs clearance`, `Customs clearance for imports` of `已落地，清关提货中` wacht het pakket op de douane in jouw land. Op de EU-lijnen zijn voor online aankopen van buiten de EU nu €3 invoerrechten per soort product verschuldigd op pakketten tot €150 (sinds 1 juli 2026) en de EU-afhandelingsvergoeding van €2 per soort product bij elke waarde (sinds 1 november 2026); de lijnpagina's van Shouwu vermelden niet wie die betaalt. Als je om invoerkosten wordt gevraagd, zie [pakketten bij de douane (in het Engels)](guide:customs).

**Niets na `Delivery to local courier`.** De koerier heeft het pakket, maar de tracker van Shouwu geeft het nummer van de koerier niet. Vraag de verkoper om het nummer van de koerier en plak dat in Peek. Meer in [waarom tracking stopt met bijwerken (in het Engels)](guide:tracking-not-updating) en [een pakket uit China volgen (in het Engels)](guide:tracking-from-china).

**Bezorgd, maar niets ontvangen.** De lijnvoorwaarden van Shouwu zeggen dat wanneer de site van de koerier een bezorging toont die nooit is aangekomen, Shouwu helpt dit uit te zoeken maar niets uitkeert. Vraag dus eerst de koerier en daarna de verkoper. In de VS neemt USPS een Missing Mail-zoekopdracht aan van de afzender of de ontvanger vanaf 7 dagen na verzending. Zie [bezorgd maar niet ontvangen (in het Engels)](guide:delivered-not-received).

**Kwijt of beschadigd.** De verkoper is de klant van Shouwu en moet volgens de lijnvoorwaarden van Shouwu binnen 30 dagen na verzending door Shouwu een claim indienen. Voor een pakket zonder bezorgscan nadat het magazijn van Shouwu het heeft ingenomen, vergoedt Shouwu de opgegeven waarde plus de verzendkosten, tot maximaal 200 yuan per zending, en niets voor transportschade. Vraag de winkel of marktplaats dus om terugbetaling of een nieuw pakket, en wacht niet af. Zie [verloren pakketten (in het Engels)](guide:lost-parcel).

## Contactnummer en klantenservice van Shouwu

De website van Shouwu, www.sedel.com, is zoals gelezen op 30 oktober 2026 in het Chinees en bedoeld voor verkopers die zendingen boeken. Er staan mobiele nummers voor het hoofdkantoor in Shanghai en vijf vestigingen, een QQ-chatlink en WeChat-codes. Er is geen e-mailadres, geen contactformulier, geen Engelstalig contact en niets voor ontvangers. De tracker geeft helemaal geen contactgegevens.

Vraag voor je bestelling de verkoper: die boekte het pakket bij Shouwu en dient claims in. Vraag voor de bezorging zelf de koerier in jouw land.

## Een Shouwu-pakket volgen met Peek

[Peek](/) herkent een Shouwu-nummer aan de vorm: SWECN, twaalf cijfers en YQ, een vorm die geen andere vervoerder in zijn catalogus gebruikt, en vraagt de tracker van Shouwu op dat nummer op. Er is geen account, postcode of telefoonnummer nodig.

Peek leidt de fase van het pakket af uit de status van Shouwu, zoals in de tabellen hierboven, en toont het bestemmingsland en elke scan met plaatsnaam, wat de pagina van Shouwu weglaat. Een plaatsnaam met een cijfer erin, zoals een postcode, wordt weggelaten. Shouwu geeft geen bezorgschatting, dus Peek toont geen verwachte bezorgdatum.

Omdat er geen koerier wordt genoemd, toont Peek geen regel "Delivery with" en draagt het pakket niet over aan de koerier, zelfs niet wanneer de geschiedenis regels van iMile doorgeeft. Heb je het nummer van de koerier, voeg dat dan als apart pakket toe aan Peek.

Terwijl je het pakket open hebt, of op de bezorgdag met meldingen aan, controleert Peek tot eens per 10 minuten, en vanaf 6:00 lokale tijd op de dag dat het pakket onderweg is voor bezorging tot eens per 2 minuten, zolang de eigen tracker van Shouwu antwoordt; anders elk uur. Via trackingdiensten is dat elk uur, elke 3 uur na een stille dag en tot eens per 15 minuten op de bezorgdag onder dezelfde voorwaarde. Na twee dagen zonder nieuws (12 uur zolang het pakket nog "Label created" of "Received by carrier" toont) controleert Peek elk uur. Peek kan je een melding sturen voor elke scan, alleen belangrijke stappen (bezorging, ophalen, douane, gemiste pogingen en retouren) of "Delivery only". Het kan niet namens jou contact opnemen met Shouwu of de verkoper.

## Vragen over Shouwu-tracking

### Wat is Shouwu International (守务国际)?

Een logistiek bedrijf uit Shanghai, 上海守务国际物流有限公司, dat pakketten verzendt voor grensoverschrijdende webwinkels. Het zegt in 2018 te zijn begonnen als luchtvrachtexpediteur en in 2019 te zijn overgestapt op e-commercelijnen naar Europa en de VS.

### Waar kan ik een Shouwu-pakket volgen?

In het vak hierboven of op de Engelstalige tracker van Shouwu op admin.shshouwu.com. Gebruik het volledige SWECN…YQ-nummer.

### Wie bezorgt Shouwu-pakketten in het VK en de VS?

Een lokale post of koerier: de lijnpagina's van Shouwu noemen Royal Mail, Yodel en Evri in het VK, en voornamelijk USPS in de VS. Voor Ierland, Canada en Australië noemt het niemand, dus vraag de verkoper.

### Hoe lang doet Shouwu over de bezorging?

Dat hangt af van de lijn die de verkoper koos. De richttijden van Shouwu zijn 5 tot 8 werkdagen op de standaardlijn naar het VK, 10 tot 12 op de Britse economy-lijn, 6 tot 8 op de standaardlijn naar de VS en 8 tot 12 op de Amerikaanse economy-lijn. In de EU zijn dat 4 tot 7 werkdagen naar Duitsland en Frankrijk, 4 tot 8 naar Nederland en Zweden, en 10 tot 12 naar Italië.

### Met wie neem ik contact op over een vermist Shouwu-pakket?

De verkoper bij wie je hebt besteld: die heeft het pakket bij Shouwu geboekt en kan binnen 30 dagen na verzending een claim indienen. Shouwu publiceert geen contactgegevens voor ontvangers.

:::sources
- [Shouwu International: 守务国际包裹追踪 (parcel tracking)](https://admin.shshouwu.com/track.html) – Engelstalige tracker, 40 nummers, statussen en uitleg, scans zonder plaatsen, "Copy Link", "Not Found"
- [Shouwu International: 首页 (home page)](http://www.sedel.com/) – contactlijst, QQ-chat, bedrijfsnaam
- [Shouwu International: 关于我们 (about us)](http://www.sedel.com/about-us.html) – 2018 en 2019, rechtstreekse partners
- [Shouwu International: 主营线路 (main lines)](http://www.sedel.com/main-route.html) – lijnen per land
- [Shouwu International: 英国标快 (UK standard express)](http://www.sedel.com/main-route-secondary.html?groupId=1&groupItemId=2) – Royal Mail, Yodel, ERVI, nummerkeuze, 5 tot 8 werkdagen, geen retour naar China, claims
- [Shouwu International: 英国经济 (UK economy)](http://www.sedel.com/main-route-secondary.html?groupId=2&groupItemId=3) – 10 tot 12 werkdagen
- [Shouwu International: 英国带电 (UK, batteries)](http://www.sedel.com/main-route-secondary.html?groupId=3&groupItemId=4) – voornamelijk Royal Mail
- [Shouwu International: 美国标快 (US standard express)](http://www.sedel.com/main-route-secondary.html?groupId=1&groupItemId=1) – voornamelijk USPS, 6 tot 8 werkdagen, geen retour naar China, claims
- [Shouwu International: 美国经济 (US economy)](http://www.sedel.com/main-route-secondary.html?groupId=2&groupItemId=21) – 8 tot 12 werkdagen
- [Shouwu International: 德国标快 (Germany standard express)](http://www.sedel.com/main-route-secondary.html?groupId=1&groupItemId=19) – voornamelijk DHL, nummerkeuze, 4 tot 7 werkdagen, claims
- [Shouwu International: 德国经济 (Germany economy)](http://www.sedel.com/main-route-secondary.html?groupId=2&groupItemId=22) – voornamelijk Hermes, 4 tot 7 werkdagen
- [Shouwu International: 德国带电 (Germany, batteries)](http://www.sedel.com/main-route-secondary.html?groupId=3&groupItemId=25) – voornamelijk DPD, 4 tot 7 werkdagen
- [Shouwu International: 法国标快 (France standard express)](http://www.sedel.com/main-route-secondary.html?groupId=1&groupItemId=20) – voornamelijk Colissimo, 4 tot 7 werkdagen
- [Shouwu International: 荷兰标快 (Netherlands standard express)](http://www.sedel.com/main-route-secondary.html?groupId=4&groupItemId=28) – voornamelijk DHL, 4 tot 8 werkdagen
- [Shouwu International: 瑞典经济 (Sweden economy)](http://www.sedel.com/main-route-secondary.html?groupId=4&groupItemId=29) – voornamelijk EarlyBird, 4 tot 8 werkdagen
- [Shouwu International: 意大利经济 (Italy economy)](http://www.sedel.com/main-route-secondary.html?groupId=4&groupItemId=27) – UniUni en Poste Italiane, nummerkeuze, 10 tot 12 werkdagen
- [European Commission: EU Customs Reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – €3 invoerrechten vanaf 1 juli 2026, €2 afhandelingsvergoeding vanaf 1 november 2026
- [USPS: Missing Mail and Lost Packages](https://www.usps.com/help/missing-mail.htm) – afzenders of ontvangers, vanaf 7 dagen
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – nummervorm, tracker en link, statussen, scanformuleringen en fasen (regels voor uitvoer- en invoerinklaring, luchthavenlading en overdracht gelezen sinds 9 november, ophaling bij verkoper door partner, ophaling door FedEx bij Amerikaans depot en vertrek van het schip uit China sinds 14 november, eigen scans van FedEx en bezorging aan zakelijk adres door DHL sinds 15 november), doorgegeven regels van iMile in Italië (ook retour aan afzender), regels van DPD, klokken, plaatsen, wat Peek toont
:::
