---
title: HuaHan Logistics tracking: HHW…YQ-nummers en bezorger
description: Volg een HuaHan Logistics-pakket (华翰物流) uit China: hoe een HHW…YQ-nummer eruitziet, wat de scans betekenen en hoe je ziet wie in jouw land bezorgt.
slug: huahan-logistics-pakket-volgen
published: 2026-11-28
updated: 2026-11-28
---

HuaHan Logistics (华翰物流, ook HuaHan Express of 华翰速递) is een Chinese vervoerder die pakketten uit China invliegt en ze overdraagt aan een lokale vervoerder in het land van bestemming, die ze bezorgt. Als een webshop je bestelling met HuaHan heeft verstuurd, zoek dan naar een nummer dat begint met HHW en eindigt op YQ en plak het in het vak hierboven. De eigen tracker van HuaHan staat op zijn website, hh-exp.cn, en kan de vervoerder noemen die bij jou bezorgt, samen met het nummer van die vervoerder voor jouw pakket.

## HuaHan-trackingnummer: hoe het eruitziet

Peek kent de vrachtbrieven van HuaHan als HHW, twee letters, tien cijfers en YQ, 17 tekens in totaal. Met verzonnen cijfers:

| Nummer | Wat het is |
| --- | --- |
| `HHWCN4829105736YQ` | HHW, twee letters, tien cijfers en YQ: een vrachtbrief van HuaHan. |
| `HHWCN482910573YQ` | Slechts negen cijfers: niet de indeling van HuaHan. Controleer of er een cijfer ontbreekt. |
| `WNBAA4829105736YQ` | Eindigt ook op YQ, maar begint met WNB: een nummer van [WanbExpress](carrier:wanbexpress). |
| `JXCAA4829105736YQ` | Begint met JXC: een nummer van [Juxi Express](carrier:juxi-express). |

Veel Chinese expediteurs laten hun vrachtbrieven eindigen op YQ, elk met hun eigen letters vooraan: HHW is wat HuaHan kenmerkt, en geen enkele andere vervoerder in de catalogus van Peek gebruikt het. Spaties, streepjes en kleine letters maken niet uit wanneer je het nummer in Peek plakt.

**Waar je het vindt.** De webshop geeft het nummer in de verzendmail of op je bestelpagina. Kun je het niet vinden, zie dan [hoe je je trackingnummer vindt](guide:find-tracking-number) (in het Engels).

**Een tweede nummer.** De tracker van HuaHan kan ook het eigen nummer van de lokale vervoerder voor het pakket tonen. HuaHan voegt soms een achtervoegsel na een liggend streepje (underscore) toe dat het nummer van de vervoerder niet heeft: laat dat weg wanneer je het nummer op de site van die vervoerder opzoekt.

## HuaHan-trackingstatussen uitgelegd

De tracker van HuaHan toont elke scan met een tijdstip, een tekst en een plaats, nieuwste eerst. De eigen scans zijn in het Engels, sommige met het Chinees ervoor. Hier staan ze in de volgorde waarin de code van Peek ze opsomt:

| HuaHan meldt | Wat het voor jou betekent | Peek toont |
| --- | --- | --- |
| `快件电子信息已经收到 Shipment information received`, `Shipment information received` | De webshop heeft de pakketgegevens naar HuaHan gestuurd. HuaHan heeft het pakket nog niet. | "Label created" |
| `快件到达作业中心 Shipment arrived at facility`, `Has entered the warehouse` | Het centrum van HuaHan in China heeft het pakket: in de geschiedenissen die de code van Peek bevat, in Shenzhen. | "Received by carrier" |
| `快件操作完成 Shipment departed from facility` | Verwerkt en onderweg uit dat centrum. | "In transit" |
| `Transfer to Origin airport` | Onderweg naar de luchthaven. | "In transit" |
| `Export customs clearance completed` | De exportdouane bij vertrek is afgerond. Niet de douane van jouw land. | "In transit" |
| `Departed from airport of Origin` | De vlucht is vertrokken. | "In transit" |
| `Import Customs clearance started` | De douane in het land van bestemming heeft het pakket. | "At customs" |
| `Arrived at destination airport` | De vlucht is geland in het land van bestemming. | "In transit" |
| `Import Customs clearance completed` | De importdouane heeft het pakket vrijgegeven. | "In transit" |
| `Has been given to local carrier` | HuaHan heeft het overgedragen aan de vervoerder die het bezorgt. Daarna volgen de eigen regels van die vervoerder. | "In transit" |

**De regels van de lokale vervoerder.** Na de overdracht geeft de tracker van HuaHan de scans van de lokale vervoerder door. Peek leest ze via de code die HuaHan aan de stap geeft, waar die er is, of aan de hand van de tekst. Teksten die de code van Peek is tegengekomen en aan die vervoerder toeschrijft:

| Na de overdracht | Wat het voor jou betekent | Peek toont |
| --- | --- | --- |
| `Ready to Ship, Parcel received and processed` | De lokale vervoerder heeft het pakket. | "In transit" |
| `The parcel is in the central warehouse waiting to be processed.`, `In Transit, Shipment arrived to sorting centre / Shipment in the sorting centre`, `In Transit, Shipment remained at sorting hub` | In het magazijn of een sorteercentrum van de lokale vervoerder, of daar nog steeds. | "In transit" |
| `In Transit, Arrived to HU HUB`, `In Transit, Parcel has entered the Linehaul network.` | Op de hub van de lokale vervoerder in Hongarije, daarna onderweg tussen zijn hubs. | "In transit" |
| `In Transit, Picked Up` (gevolgd door DPD's Duitse `Einrollung`), `In Transit, Scanned By DPD At parcel delivery centre.`, `In Transit, At parcel delivery centre.` | Op het bezorgdepot van DPD: de aankomstscan daar, geen ophaling bij jou, en daarna een scan voor elke dag dat het pakket daar wacht. | "In transit" |
| `Out for Delivery, Parcel is at courier, delivery expected soon.` | Onderweg met de bezorger. | "Out for delivery" |
| `The courier will deliver the parcel soon. Additional details are available in the SAMEDAY app, email or SMS.` | Onderweg met de bezorger, in de woorden van [Sameday](carrier:sameday), een koerier in Roemenië en Bulgarije. | "Out for delivery" |
| `Delivery Attempted`, `Delivery Attempted, Unfortunately we have not been able to deliver your parcel. Pickup parcelshop delivery after first delivery attempt.` | DPD probeerde te bezorgen en dat lukte niet. Bij de langere regel gaat het pakket door naar een afhaalpunt. | "Delivery attempted" |
| `Awaiting Pickup, Parcel was deposited in Pickup parcel station` | Na die poging heeft DPD het pakket bij een afhaalpunt achtergelaten om door jou op te halen. | "Ready for pickup" |
| `The parcel has been successfully delivered.` | Bezorgd. | "Delivered" |

HuaHan plaatst de scans van de lokale vervoerder voor uitgaan voor bezorging, afhaalpunt en bezorging onder eigen codes, en Peek toont ze als "Out for delivery", "Ready for pickup" of "Delivered", tenzij de woorden een andere stap noemen: HuaHan zet ook het vertrek van USPS uit een distributiecentrum onder zijn code voor uitgaan voor bezorging.

**Twee regels die verwarren.** `We will pick up your parcel from the sender soon.` kan al vlak na het begin opduiken, nog voordat HuaHan het pakket in China heeft ontvangen: het is de melding van de lokale vervoerder dat hij over het pakket is geïnformeerd, geen ophaling bij jou. Peek toont "Label created". `The parcel was redirected. As soon as it is in the couriers possession, it will be processed as requested.` is die vervoerder die een door jou gevraagde omleiding vastlegt, eveneens geschreven terwijl het pakket nog in China is. Het verplaatst niets en Peek geeft er geen eigen status aan.

**Gegevens, geen scans.** `The instruction data for this shipment have been provided by the sender to DHL electronically.`, soms zonder de punt, is de registratie van DHL van de pakketgegevens voor het laatste traject, doorgegeven voordat HuaHan het pakket in China heeft of nadat het daar is vertrokken. `货物电子信息已经收到` (elektronische gegevens van de goederen ontvangen), in het Chinees, zijn de gegevens van de bestelling en kunnen ook verschijnen nadat HuaHan het pakket al heeft. Geen van beide is een beweging: Peek toont "Label created" of behoudt de status die het pakket al had.

**Eén scan, twee regels.** De tracker van HuaHan kan één scan van de lokale vervoerder twee keer op hetzelfde tijdstip vermelden: in de woorden van die vervoerder met zijn plaats, en zonder plaats in generieke woorden van HuaHan, zoals `Delivered, Parcel was delivered successfully`. Peek bewaart de regel met de plaats.

**Per post.** HuaHan kan een pakket ook via China Post versturen. De tracker geeft dan de scans van China Post in het Chinees door, met het kantoor tussen 【】, en later de scans van het postbedrijf van bestemming zoals China Post ze overneemt. Peek toont ze in het Chinees, zoals HuaHan ze doorgeeft:

| HuaHan geeft door | Wat het voor jou betekent | Peek toont |
| --- | --- | --- |
| `中国邮政已收取邮件` | China Post heeft het pakket van HuaHan ontvangen: vanaf hier gaat het per post. | "In transit" |
| `邮件已在【…】完成分拣，准备发出`, `邮件到达【…】`, `邮件离开【…】，正在发往【…】`, `邮件离开【…】，正在发往下一站` | Gesorteerd bij, aangekomen bij of vertrekkend uit een kantoor van China Post in China, genoemd tussen 【】. | "In transit" |
| `送交出口海关` | Overgedragen aan de exportdouane in China. Niet de douane van jouw land. | "At customs" |
| `出口海关/放行` | De exportdouane heeft het vrijgegeven. | "In transit" |
| `已交承运商运输` | Overgedragen aan de luchtvaartmaatschappij die het naar het buitenland vliegt. | "In transit" |
| `到达寄达地`, `到达寄达地处理中心`, `到达境外经转局` | Aangekomen in het land van bestemming, in het verwerkingscentrum van dat postbedrijf of op een doorvoerkantoor onderweg. | "In transit" |
| `境外进口海关留存待验(等待提交给边境机构/安全部门)` | Vastgehouden voor de importdouane van jouw land; tussen haakjes staat dat het wacht op aanbieding aan de grens- of veiligheidsinstanties. | "At customs" |
| `境外进口互换局留存`, `送交境外进口海关` | Bewaard op het uitwisselingskantoor van het postbedrijf van bestemming, daarna overgedragen aan de importdouane. | "At customs" |
| `寄达地海关允许进口（无需缴税）` | De importdouane heeft het toegelaten, zonder te betalen invoerrechten. | "In transit" |
| `离开【…】处理中心`, `已到达【…】投递局` | Heeft het verwerkingscentrum in het land tussen 【】 verlaten en is daarna aangekomen op het bezorgkantoor. | "In transit" |

**Via Landmark Global.** Voor sommige pakketten geeft de tracker van HuaHan de regels van [Landmark Global](carrier:landmark-global) door, in het Engels. De eerste twee komen voordat HuaHan het pakket in China heeft; de andere volgen na de overdracht van Landmark Global aan het netwerk van [bpost](carrier:bpost), dat bezorgt:

| HuaHan geeft door | Wat het voor jou betekent | Peek toont |
| --- | --- | --- |
| `Shipment Data Uploaded` | De registratie bij Landmark Global van de gegevens van de afzender. Geen beweging. | "Label created" |
| `Processed` | De verwerking bij Landmark Global, in dezelfde minuut als die registratie. Geen ontvangst van het pakket: een melding, en Peek houdt de fase ervoor aan. | De fase ervoor |
| `Incoming scan at facility` | Een depot heeft het pakket, na de overdracht aan het netwerk van bpost. | "In transit" |
| `Outgoing scan at facility` | Het heeft dat depot verlaten. De bezorgronde kan nog dagen weg zijn. | "In transit" |
| `Out for delivery (On the way)` | Onderweg op de bezorgronde. | "Out for delivery" |
| `Delivery attempt (Delivery not possible)` | Een bezorgronde waarbij het niet bezorgd kon worden. | "Delivery attempted" |

HuaHan plaatst `Outgoing scan at facility` onder zijn code voor uitgaan voor bezorging, maar de woorden beschrijven een vertrek uit een depot, dus Peek toont "In transit".

## Wie bezorgt HuaHan-pakketten

Na `Has been given to local carrier` bezorgt een vervoerder in het land van bestemming het pakket, en de tracker van HuaHan kan die vervoerder noemen en zijn nummer geven.

**Wat de code van Peek laat zien.** Eén HuaHan-geschiedenis laat Sameday bezorgen: de regel voor uitgaan voor bezorging verwijst naar de SAMEDAY-app. De code van Peek verwacht ook [USPS](carrier:usps)-nummers van HuaHan: wanneer HuaHan de lange labelbarcode van USPS geeft, behoudt Peek het eigen trackingnummer van USPS daaruit. [DHL](carrier:dhl) wordt voor het laatste traject van sommige pakketten geboekt: de hierboven geciteerde gegevensregel is zijn registratie van de gegevens van de afzender. DPD bezorgt sommige pakketten: zijn depotscans, zoals `In Transit, Scanned By DPD At parcel delivery centre.`, staan in de tabel hierboven. [CTT Express](carrier:ctt-express) wordt voor het laatste traject van andere aangekondigd: de tracker van HuaHan geeft minuten na de aankomstscan van HuaHan in China de melding `Pendiente de recepción en CTT Express` ("in afwachting van ontvangst bij CTT Express") door op de klok van Madrid, en Peek toont "Label created". Andere gaan via [Landmark Global](carrier:landmark-global): de tracker van HuaHan geeft diens regels door vanaf de registratie van de afzendergegevens tot de bezorgronde, en na de overdracht van Landmark Global bezorgt het netwerk van bpost (zie "Via Landmark Global" hierboven). Verder koppelt de code geen enkele vervoerder aan een bepaald land: Peek volgt de vervoerder die HuaHan voor jouw pakket noemt.

**Bezorgpogingen en afhalen.** Pogingen, niet-thuisberichten en waar een gemist pakket blijft liggen, en hoe lang, volgen de regels van de lokale vervoerder: vraag die vervoerder, met zijn nummer, om een nieuwe bezorging te regelen. Zie [gemiste bezorging](guide:missed-delivery) en [niet-opgehaalde pakketten](guide:uncollected-parcel) (in het Engels).

**Douane in de EU.** Goederen die online van buiten de EU zijn gekocht hebben sinds 1 juli 2026 een invoerrecht van €3 per artikel op pakketten tot €150, en sinds 1 november 2026 een EU-afhandelingsvergoeding van €2 per artikel, ongeacht de waarde. Een artikel zijn de goederen met dezelfde tariefindeling, omschrijving en, waar vereist, oorsprong. Peek kon niet controleren of HuaHan deze vooraf bij de verkoper int: vraag de webshop of jouw prijs ze omvatte. Elders hangt wat je betaalt af van jouw land. Zie [pakketten vast bij de douane](guide:customs) (in het Engels).

## HuaHan-pakket komt niet aan of tracking staat stil

**Nummer niet gevonden.** Wanneer HuaHan geen gegevens over het nummer heeft, antwoordt de tracker 没有找到记录 ("geen gegevens gevonden"), en toont Peek "Waiting for HuaHan Logistics" terwijl het blijft controleren. Als HuaHan het nummer twee dagen later nog steeds niet kent en niets anders ervoor heeft geantwoord, toont Peek "After two days, the carrier still doesn’t know this number. Check it, choose another carrier or delete the parcel." Controleer het nummer teken voor teken aan de hand van het bericht van de verkoper: HHW, twee letters, tien cijfers, YQ. Komt er na een paar dagen niets, vraag dan aan de verkoper of het pakket echt is verstuurd.

**Blijft hangen op `Shipment information received`.** De webshop heeft de zending aangemeld, maar HuaHan heeft het pakket nog niet gescand. Vraag de webshop wanneer het pakket is afgegeven.

**Blijft hangen op `Import Customs clearance started`.** De douane in jouw land heeft het pakket. Als je om invoerkosten wordt gevraagd, zie [pakketten vast bij de douane](guide:customs) (in het Engels); blijft het daar lang liggen, laat het de verkoper weten.

**Niets na `Has been given to local carrier`.** De lokale vervoerder heeft het pakket, en zijn eigen tracking loopt mogelijk voor op die van HuaHan. Zoek zijn naam en nummer op de tracker van HuaHan, of op de pakketkaart in Peek wanneer Peek die vervoerder kent, of vraag de verkoper welke vervoerder bezorgt.

**Dagenlang geen update.** Wanneer een pakket dat nog onderweg is vier dagen geen nieuwe scan heeft gehad, markeert Peek het met de dag van de laatste scan ("No tracking update since Mon 12 Oct") en merkt op dat pakketten vaak stilvallen op lange vluchten, bij de douane of in een weekend; na drie weken zonder nieuws raadt het in plaats daarvan aan de verkoper te vragen. Zie [waarom tracking stopt met updaten](guide:tracking-not-updating) en [pakketten uit China volgen](guide:tracking-from-china) (in het Engels).

**Bezorgd, maar niets ontvangen.** Controleer waar het is achtergelaten en vraag buren, daarna de lokale vervoerder en de verkoper. Zie [bezorgd maar niet ontvangen](guide:delivered-not-received) (in het Engels).

**Kwijt of retour.** De verkoper heeft het pakket bij HuaHan geboekt, dus de verkoper of de marktplaats is degene aan wie je om terugbetaling of een nieuw pakket vraagt. Zie [verloren pakketten](guide:lost-parcel) en [retour naar afzender](guide:returned-to-sender) (in het Engels).

## Telefoonnummer en klantenservice van HuaHan

Op zijn website ([hh-exp.com/lxwm](https://www.hh-exp.com/lxwm)) geeft HuaHan een hotline in **China**, 400-015-6080, zonder gepubliceerde openingstijden, en het hoofdkantoor in Shenzhen; het vermeldt geen support-e-mailadres voor ontvangers. De verkoper heeft het pakket bij HuaHan geboekt en is het aanspreekpunt voor je bestelling, en de marktplaats als de verkoper niet reageert. Vraag voor de bezorging zelf de lokale vervoerder.

## Een HuaHan-pakket volgen met Peek

[Peek](/) noemt HuaHan Logistics meteen bij een HHW…YQ-nummer ("Detected carrier") en vraagt de tracker van HuaHan alleen op basis van dat volledige nummer: er is geen account, postcode of telefoonnummer nodig. Heb je een bestelnummer, vraag de verkoper dan om het HHW…YQ-vrachtbriefnummer. Heb je alleen het nummer van de lokale vervoerder, plak dat dan in plaats daarvan: Peek volgt meer dan 4.500 vervoerders.

Peek toont de status, elke scan met zijn plaats en het land van bestemming uit de registratie van HuaHan. De tracker van HuaHan drukt tijden zonder tijdzone af. Peek leest de eigen scans van HuaHan op de klok van hun plaats of van het traject waarop het pakket zich bevond: Beijing-tijd in China. De scans van de lokale vervoerder na `Has been given to local carrier` worden op de klok van jouw land gelezen wanneer dat één tijdzone heeft. Elke tijd wordt getoond in de tijdzone van je apparaat. HuaHan geeft geen verwachte bezorgdatum of gewicht, dus Peek toont geen van beide.

Wanneer HuaHan een lokale vervoerder noemt die Peek kent, met een nummer in de vorm van die vervoerder, vermeldt de pakketkaart dat (bijvoorbeeld "Delivery with USPS", of "Announced to USPS, not received yet" zolang die vervoerder alleen de aankondiging van het pakket heeft en nog geen eigen scan) en zet het nummer van die vervoerder achter dat van HuaHan. Peek vraagt dan ook die vervoerder. Als HuaHan een internationaal postaal nummer (twee letters, negen cijfers, twee letters) geeft zonder een vervoerder die Peek kent, vraagt Peek de post van het land van bestemming; bij een ander nummer de ene vervoerder waarop de vorm wijst. Zodra die vervoerder een scan toont die minstens zo recent is als de nieuwste van HuaHan, volgt Peek het pakket daar en bewaart de geschiedenis van HuaHan. Geeft HuaHan geen nummer voor de lokale vervoerder, dan blijft Peek bij de tracker van HuaHan, die de scans van die vervoerder blijft doorgeven.

Wanneer de tracker van HuaHan niet bereikbaar is, vraagt Peek in plaats daarvan trackingdiensten en toont de geschiedenis die zij hebben, in hun woorden.

Zolang je het pakket open hebt staan, of op de bezorgdag met meldingen aan, controleert Peek tot elke 10 minuten, en tot elke 2 minuten vanaf 6:00 uur lokale tijd op de dag waarop het pakket uitgaat voor bezorging (elke 5 minuten wanneer de controle een browser opent), zolang de eigen tracker van HuaHan antwoordt; anders elk uur. Via trackingdiensten is het elk uur, elke 3 uur na een rustige dag en tot elke 15 minuten op de bezorgdag onder dezelfde voorwaarde. Zodra een andere vervoerder de bezorging overneemt, volgt Peek het eigen tempo van die vervoerder. Na twee dagen zonder nieuws (12 uur zolang het pakket nog "Label created" of "Received by carrier" toont) controleert Peek elk uur. Peek kan je een melding sturen voor "Every scan", "Important steps" of "Delivery only". "Important steps" dekt `Import Customs clearance started`, de douanescans van China Post die HuaHan in het Chinees doorgeeft voor een pakket dat per post gaat (zoals `送交出口海关` en `送交境外进口海关`), de bezorgronde, een afhaalpunt en de bezorging, maar geen vluchten of HuaHan's eigen exportinklaringsscans: de exportinklaring onderweg naar de luchthaven, `快件从转运仓发出至机场，出口报关中（ The package is shipped from the transfer warehouse to the airport Export customs clearance in progress ）`, is een routinestap vóór de vlucht en Peek toont die als "In transit". Peek kan geen contact opnemen met HuaHan of de verkoper namens jou.

## Vragen over HuaHan-tracking

### Wat is HuaHan Logistics (华翰物流)?

Een Chinese vervoerder, ook HuaHan Express of 华翰速递 genoemd, die pakketten naar het buitenland verzendt. In de geschiedenissen die de code van Peek bevat, beginnen de scans op het centrum in Shenzhen, volgen de vlucht en de douane en eindigen met de scans van de lokale vervoerder die in het land van bestemming bezorgt.

### Waar kan ik een HuaHan-pakket volgen?

In het vak hierboven, of op de eigen tracker van HuaHan op hh-exp.cn. Die tracker vraagt je eerst een controle te doorlopen: op volgorde klikken of een eenvoudige optelsom. Gebruik het volledige HHW…YQ-nummer.

### Wat betekent "Has been given to local carrier" bij HuaHan-tracking?

De importdouane heeft het pakket vrijgegeven en HuaHan heeft het overgedragen aan de vervoerder die het in jouw land bezorgt. De scans van die vervoerder volgen in de geschiedenis van HuaHan.

### Wie bezorgt HuaHan-pakketten in mijn land?

Een lokale vervoerder, die de tracker van HuaHan kan noemen met zijn nummer voor het pakket. De code van Peek toont Sameday in één geschiedenis en depotscans van DPD, leest USPS-nummers, bevat gegevens van DHL en de melding van CTT Express voor het laatste traject van sommige pakketten, en leest de regels van Landmark Global voor andere, waarbij het netwerk van bpost na de overdracht bezorgt: kijk op de tracker van HuaHan of vraag de verkoper.

### Met wie neem ik contact op voor een vermist HuaHan-pakket?

De verkoper bij wie je hebt besteld: die heeft het pakket bij HuaHan geboekt. De eigen hotline van HuaHan zit in China (400-015-6080).

:::sources
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – €3 invoerrecht per artikel tot €150 sinds 1 juli 2026, €2 EU-afhandelingsvergoeding sinds 1 november 2026
- [European Commission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – €2 per artikel ongeacht de waarde, wat een artikel is
- [HuaHan Express: 联系我们 (contact, in het Chinees)](https://www.hh-exp.com/lxwm) – hoofdkantoor in Shenzhen en de officiële hotline 400-015-6080.
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – namen, HHW-nummervorm, de tracker en zijn optelsomcontrole, scanformuleringen, codes en hun fasen, doorgegeven scans van lokale vervoerders en dubbele regels, tekst van Sameday, DPD-depotscans, pogingen en afhaalpunt, melding van CTT Express, scans van China Post en het postbedrijf van bestemming in het Chinees op de postlijn en hun fasen, doorgegeven regels van Landmark Global, de overdracht aan het netwerk van bpost en hun fasen, naam en nummer van de lokale vervoerder met het achtervoegsel na een liggend streepje en USPS-barcodes, klokken, land van bestemming, geen bezorgdatum of gewicht, de overdracht
:::
