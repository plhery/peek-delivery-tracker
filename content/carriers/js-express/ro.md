---
title: JS Express urmărire colet: JSE…YQ, statusuri și curieri
description: Urmărește un colet JS Express (急速国际) din China: codul JSE…YQ, explicația scanărilor, curierii de livrare în Europa, SUA și Canada, vamă DDP și contact.
slug: js-express-urmarire-colet
published: 2026-11-28
updated: 2026-11-28
---

JS Express (急速国际, numit și JS EXP) este o companie chineză de logistică pentru magazine online transfrontaliere, operată de 急创速供应链管理（苏州）有限公司 din Suzhou. Dacă un magazin ți-a trimis comanda prin JS Express, codul începe cu `JSE` și se termină cu `YQ`: lipește-l în caseta de mai sus. JS Express scoate coletul din China, iar un curier din țara ta îl livrează: istoricul său arată USPS, GOFO sau SpeedX în SUA, Straightship în Canada, Yodel în Regatul Unit, DHL, iMile sau J&T Express în Germania, Colissimo în Franța, CTT Express în Spania și FedEx pe anumite rute.

## Numărul de urmărire JS Express: cum arată

Codurile de transport JS Express sunt formate din `JSE`, două litere, zece cifre și `YQ`, în total 17 caractere. Cu cifre inventate:

| Număr | Ce este |
| --- | --- |
| `JSEAA4829105736YQ` | `JSE`, două litere, zece cifre și `YQ`: un cod JS Express. |
| `JSEAA482910573YQ` | Doar nouă cifre: nu este formatul JS Express. Verifică dacă lipsește o cifră. |
| `JXCAA4829105736YQ` | Se termină tot cu `YQ`, dar începe cu `JXC`: cod [Juxi Express](carrier:juxi-express). |

Alți expeditori chinezi folosesc aceeași structură cu literele lor la început: `FEI` pentru 17FEIA, `WNB` pentru WanbExpress, `SFC` pentru SFC, `CJP` pentru CJ Packet. `JSE` identifică JS Express. Spațiile și literele mici nu contează în Peek. Peek citește un zero tastat în locul celei de-a doua litere ca `O`, iar un `O` între cele zece cifre ca zero. Pagina proprie de urmărire JS Express cere numărul întreg: o parte din el nu găsește nimic.

**Unde îl găsești.** Magazinul îți trimite codul în e-mailul de expediere sau pe pagina comenzii. JS Express afirmă că lucrează îndeaproape cu Amazon, Walmart, Shopify, eBay, AliExpress, Wish și Shopee. Dacă nu îl găsești, vezi [how to find your tracking number](guide:find-tracking-number) (în engleză).

**Fără link de partajat.** Paginile de urmărire JS Express primesc numărul printr-un formular, așa că niciun link nu deschide un singur colet: copiază numărul. Pagina în engleză „Express Query” răspunde imediat; cea în chineză cere mai întâi un cod din imagine (`验证码`).

## Statusurile de urmărire JS Express explicate

Pagina JS Express listează fiecare scanare cu ora, locul între 【paranteze】 și formularea, cele mai noi sus, cu „Latest” în vârf. Formulările sunt în mare parte în engleză. În ordinea în care le primește un colet:

| JS Express afișează | Ce înseamnă pentru tine | Peek afișează |
| --- | --- | --- |
| `Electronic notification of order creation received.` | Magazinul a trimis către JS Express detaliile comenzii. JS Express nu are încă coletul. | "Label created" |
| `PRE-ADVICE` | Un partener are detaliile coletului, înainte ca depozitul JS Express să primească coletul. | Etapa pe care o avea deja coletul |
| `Item has been collected` | Depozitul JS Express din China a primit coletul: primul scan real. | "Received by carrier" |
| `Item inbound in sorting center.`, `Item outbound in sorting center.` | Sortat la JS Express în China. | "In transit" |
| `Domestic allocation has begun`, `Under domestic allocation` | Încă în depozitul JS Express din China, după sortare și înainte de plecare. | "In transit" |
| `Waybill Generated for` | Curierul din țara ta și-a emis propriul AWB cât timp coletul este încă în depozitul JS Express din China. | "Label created", sau etapa anterioară, de obicei "In transit" |
| `Arrive at transit center` | În centrul de tranzit JS Express din China, între sortare și plecare. | "In transit" |
| `Item departed from origin.`, `Arrive at international airport to abroad` | Pe drumul de ieșire, apoi la aeroportul din China. | "In transit" |
| `Arrived at export center`, `Exports Center Sorting and Packing`, `Arrived at Origin Port` | Pe unele rute, între cele două etape: un centru de export din China sortează și ambalează coletul, apoi acesta ajunge în portul de plecare. | "In transit" |
| `Shipment arrived at facility and measured.`, `Depart from facility to service provider.` | Un centru din China cântărește și măsoară coletul, apoi îl trimite spre linia aeriană. | "In transit" |
| `Start on-site transportation and shipment` | La aeroportul din China, după sosire: a început manipularea la fața locului înainte ca vama chineză de export să elibereze coletul. | "In transit" |
| `Released From Export Customs` | Vămuirea la export în China s-a încheiat. Nu este vama țării tale. | "In transit" |
| `DEPART ORIGIN AIRPORT`, `Departure from the original airport`, `Item Flight departure`, `Initial landing`, `Connecting flights flight takeoff` | Zborul a plecat, uneori cu escală și zbor de legătură. | "In transit" |
| `ARRIVED AT DESTINATION AIRPORT`, `Arrival to the destination airport`, `Item Flight landing`, `NOA-Notification of arrival from GHA` | A aterizat în țara de destinație. NOA este notificarea de sosire din partea operatorului de sol al aeroportului. | "In transit" |
| `The customs clearance agent is queuing up to pick up the goods` | Agentul vamal din țara de destinație așteaptă la terminalul cargo al aeroportului să ridice marfa: încă nu o are nici agentul, nici vama. | "In transit" |
| `The customs clearance agent has picked up the goods` | Agentul a ridicat marfa din terminalul cargo pentru vămuirea la import. | "At customs" |
| `The goods have been picked up abroad` | Agentul din țara de destinație a preluat expedierea din aeroport. Nu este o nouă ridicare de la vânzător. | "In transit" |
| `Customs clearance agent received the shipment in destnation country.` | Agentul vamal a preluat expedierea pentru vămuirea la import, după zbor. „destnation” este grafia JS Express. | "At customs" |
| `Inbound to customs temporary warehouse` | Într-un depozit vamal temporar, în așteptarea vămuirii la import. | "At customs" |
| `Released From Import Customs`, `Released from customs: customs cleared.` | Vama țării de destinație a eliberat coletul. | "In transit" |
| `Depart from the destination distribution center.` | Un centru de distribuție din țara de destinație trimite coletul mai departe după vamă. | "In transit" |
| `In transit, it's progressing through post network` | Poșta din țara de destinație transportă coletul prin rețeaua sa, înainte de traseul de livrare. | "In transit" |
| `collection by overseas warehouse` | Depozitul JS Express din țara de destinație a preluat coletul după vamă. | "In transit" |
| `Load cargo overseas`, `Vehicles depart from` | Acel depozit încarcă coletul pentru curier, apoi mașina pleacă din locul indicat în scanare. | "In transit" |
| `Delivery to local courier`, `Last Mile Delivery` | Predat curierului local, înainte de prima sa scanare. Încă nu ți-a fost livrat. | "In transit" |
| `Delivery in progress` | Pe unele rute: curierul local are coletul după ce a ajuns la stația sa, înainte de traseul de livrare. În ciuda formulării, nu a ieșit încă în livrare. | "In transit" |
| `到达待取` | Status propriu JS Express în chineză: sosit, așteaptă ridicarea. Ca loc este indicată doar țara. | "Ready for pickup" |
| `The delivery address is incorrect. Please ask the recipient to contact their local area?` | Notificarea JS Express după ce curierul nu a reușit livrarea: adresa este neclară sau greșită. Îți cere să contactezi sediul local, al cărui număr de telefon apare pe pagina JS Express; Peek omite numerele de telefon. | "Needs attention" |

**Scanările curierului local.** După `Delivery to local courier`, istoricul continuă în cuvintele curierului. În SUA, cele ale USPS: `Accepted at USPS Origin Facility` sau `Accepted at USPS Facility` (primul său scan, nu o expediere nouă), `Out for Delivery`, `Delivered, Front Door/Porch`. Cele ale SpeedX vin fără numele companiei: `Processing at Transit Facility`, `In transit to destination facility`, `Arrival at Destination Facility` și `Processing at Designated Facility`, toate "In transit". Și scanările unui alt partener american apar fără nume: `Arrived at Origin Hub` (primul hub, lângă aeroportul de sosire), `Departed Destination Hub` și `Arrived At Warehouse` (depozitul de ultim kilometru, înainte de livrare), toate "In transit". În Canada, Straightship afișează `Package is received at a Straightship station`, apoi `Package will be delivered before 9:00pm` (traseul șoferului din acea zi, "Out for delivery": urmează livrarea sau o încercare) și `Delivered` cu orașul în paranteze. În Germania, DHL scrie în engleză, uneori cu punct la final: `The instruction data for this shipment have been provided by the sender to DHL electronically`, înregistrarea datelor coletului ("Label created", sau "In transit" dacă a existat un scan înainte), apoi `The shipment is in transit to DHL` ("In transit"); un colet mic poate primi și `Die Sendung wurde im Briefzentrum bearbeitet.`, în germană, de la un centru poștal DHL ("In transit"). Șoferul iMile poate încheia traseul cu `Route not completed` ("Delivery attempted"). Scanările J&T Express Germany vin în germană, cu locurile în paranteze 【】: `【】【】Ihr Sendung wurde abgeholt.` este primul scan la preluarea coletului după zbor și vamă; urmează `【】Die Sendung hat【】verlassen. Weiterversand nach【】` (a plecat dintr-un punct spre următorul) și `【】Die Sendung ist bei【】 angekommen.` (la un hub sau la depozitul de livrare, înainte de traseu), toate "In transit". Unde livrează FedEx, `Shipment information sent to FedEx` este eticheta sa, emisă înainte ca depozitul JS Express să primească coletul ("Label created"); urmează `Arrived at FedEx location`, `Left FedEx origin facility` și `On the way` ("In transit"). În Ungaria urmează `Arrived to HU HUB`, `Linehaul Transit` (transportul din hub spre un centru de sortare) și `Shipment arrived to sorting centre / Shipment in the sorting centre`, toate "In transit". În Spania, `Enviado` de la CTT Express înseamnă că a trimis coletul mai departe, după preluare și înainte de traseul de livrare ("In transit").

**Un curier fără nume.** Pe unele rute, JS Express preia scanările unui curier local cu majuscule, fără a-i menționa numele sau țara. `SHIPMENT RECEIVED`, `PROCESSED AT HUB` și `SHIPMENT PROCESSED` arată intrarea și procesarea în hub-ul curierului, iar `DEPARTED FROM DEPOT` plecarea spre depozitul de livrare, toate "In transit". `RECEIVED AT LOCAL DEPOT` este acel depozit înainte de fiecare traseu și din nou după o încercare ratată: depozitul propriu al curierului, nu un punct de ridicare, așa că Peek păstrează statusul liniei anterioare. `DELIVERY ATTEMPTED: 1ST DELIVERY ATTEMPT`, `DELIVERY ATTEMPTED: 2ND DELIVERY ATTEMPT` și `DELIVERY ATTEMPTED: 3RD DELIVERY ATTEMPT` sunt livrări ratate ("Delivery attempted"). La a treia, curierul îți cere să programezi o livrare; `SHIPMENT REDIRECTED: ON CONSIGNEE'S REQUEST` înseamnă apoi că coletul merge spre livrarea stabilită de tine ("In transit"). `DELIVERY EXCEPTION` indică o problemă în hub înainte de traseu, iar `DELIVERY EXCEPTION, WAIT FOR INFORMATION CONFIRMATION`, după `ARRIVE AT THE WAREHOUSE` ("In transit"), înseamnă că curierul reține coletul până la confirmarea datelor: Peek afișează ambele ca "Needs attention". Dacă Peek nu afișează niciun număr de curier, întreabă vânzătorul cine livrează și de ce are nevoie curierul.

**Linii care nu sunt scanări.** `SHIPPING INFORMATION RECEIVED`, `SHIPMENT CREATED`, `Data Received`, linia USPS `Shipping Label Created, USPS Awaiting Item`, `Package information is created in the system` și, la un hub european, `Shipment Data Uploaded`, apoi `Processed` sunt doar înregistrarea datelor coletului la un partener, uneori cu zile înainte să îl primească fizic. `Consignee Contacted`, scrisă în momentul în care curierul înregistrează o livrare ratată, este apelul sau mesajul său către tine și nu schimbă statusul.

**Ore amestecate.** Orele nu au fus orar și combină ceasuri diferite: ora Beijingului în China, ora locală la aeroporturi și la majoritatea curierilor și UTC pentru Straightship, cu ore înaintea ceasurilor din Canada. Urmărește ordinea rândurilor, nu orele. În Peek, liniile germane ale J&T Express Germany afișează orele oferite de trackerul propriu J&T Express Germany.

## Cine livrează coletele JS Express

**Statele Unite.** Istoricul JS Express arată livrare prin [USPS](carrier:usps), cu scanările USPS până la predare, prin [GOFO](carrier:gofo), de la centrul de sortare până la livrare, sau prin [SpeedX](carrier:speedx), ale cărui scanări nu îi menționează numele. Odată ce USPS are coletul, acceptă o cerere Missing Mail de la expeditor sau destinatar începând la 7 zile de la expediere.

**Canada.** [Straightship](carrier:straightship), un curier canadian, livrează unele colete JS Express; scanările sale apar în istoricul JS Express.

**Germania, Franța și Spania.** Ruta europeană a JS Express deservește țări precum Regatul Unit, Franța, Germania, Italia, Spania, Elveția și Danemarca, cu excepția unor coduri poștale izolate. Pentru Germania, JS Express menționează DPD și alți curieri de ultim kilometru și spune că peste 98% dintre colete sunt livrate în 8 zile lucrătoare; istoricul de acolo a arătat scanări [DHL Paket](carrier:dhl), [iMile](carrier:imile) dintr-un centru german și [J&T Express Germany](carrier:j-and-t-de) în germană. În Franța a arătat scanări [Colissimo](carrier:la-poste) în franceză, iar în Spania [CTT Express](carrier:ctt-express).

**Regatul Unit, Irlanda, Australia, România și alte țări.** Pe unele rute istoricul arată scanări [FedEx](carrier:fedex). În Regatul Unit apar scanări Yodel; două dintre ele, `DESPATCHED AND ON ROUTE TO YODEL` și `PARCEL PICKED UP`, apar încă din timp ce coletul este în depozitul JS Express din China. Pentru Australia, JS Express menționează Australia Post și Fastway, acum [Aramex Australia](carrier:aramex-au), sub 4 kg, și DPEX peste 4 kg. Înregistrarea JS Express poate conține numărul curierului local, dar nu și numele acestuia, iar pagina JS Express nu afișează niciuna dintre ele. Peek indică curierul când acel număr corespunde unui singur transportator din țara ta și întreabă poșta națională pentru un [cod poștal](guide:tracking-number-formats) (în engleză; două litere, nouă cifre, două litere); detalii mai jos. Dacă Peek nu indică niciun curier, întreabă vânzătorul cine livrează.

Încercările de livrare, avizele și punctele de ridicare urmează regulile curierului local: JS Express nu publică niciuna. Un rând `到达待取` indică doar țara, deci verifică urmărirea curierului sau întreabă vânzătorul unde așteaptă coletul.

## Coletul JS Express nu ajunge sau urmărirea s-a blocat

**„未获取到任何数据,请提供有效单号!”** Pagina în engleză a JS Express răspunde astfel, în chineză, la un număr necunoscut: „nu s-au găsit date, introduceți un număr de AWB valid”. `No track data for now!` înseamnă că nu există încă scanări. Pentru ambele, Peek afișează "Waiting for JS Express" și continuă verificările. Dacă JS Express tot nu cunoaște numărul după două zile și nimic altceva nu a răspuns pentru el, Peek afișează "After two days, the carrier still doesn’t know this number. Check it, choose another carrier or delete the parcel." Verifică numărul caracter cu caracter; dacă după câteva zile nu apare nimic, întreabă vânzătorul dacă a fost expediat coletul.

**Blocat la `Electronic notification of order creation received.`** JS Express nu a preluat încă coletul. Întreabă magazinul când l-a predat.

**Blocat la `Inbound to customs temporary warehouse`.** Coletul așteaptă vama țării tale. Pe unele rute apar `Start Custom clearance Process`, apoi `customs clearance completed`; un `Under customs clearance, please wait patiently.` anterior ține de vama chineză de export la aeroportul de plecare, așa că Peek îl afișează ca "In transit". JS Express își vinde ruta europeană și ruta standard către SUA cu taxe plătite (DDP): încasează taxele de import în avans de la magazin, deci pe acele rute nu ar trebui să ți se ceară nimic. Dacă un curier cere plată, verifică cu vânzătorul înainte de a plăti. În UE, de la 1 noiembrie 2026 se aplică o taxă de manipulare de 2 € per tip de produs; o plătește vânzătorul sau firma care livrează și o poate transfera destinatarului. Vezi [parcels held at customs](guide:customs) (în engleză); dacă rămâne blocat zile întregi, anunță vânzătorul. Mai multe în [why tracking stops updating](guide:tracking-not-updating) și [tracking a parcel from China](guide:tracking-from-china) (în engleză).

**Nimic după `Delivery to local courier`.** Curierul local îl are. Dacă Peek arată numărul curierului, caută-l pe site-ul acelui curier; altfel, întreabă vânzătorul cine livrează.

**Livrat, dar nu a ajuns nimic.** Întreabă mai întâi curierul local (USPS în SUA), apoi vânzătorul. Pe ruta către SUA, JS Express ajută la verificarea unei livrări indicate de curier dar neprimite, însă nu plătește despăgubiri pentru aceasta, deci decide politica vânzătorului. Vezi [delivered but not received](guide:delivered-not-received) (în engleză).

**Pierdut, deteriorat sau returnat.** Clientul JS Express este vânzătorul. Potrivit termenilor JS Express, clientul reclamă în scris în termen de 30 de zile de la primirea coletului de către JS Express, iar pentru coletele asigurate prin propriul cont. Așadar cere magazinului sau platformei o rambursare ori un nou colet și nu amâna. Un colet nelivrabil (nimeni acasă, refuzat, adresă greșită, vamă) este returnat contra cost la cererea expeditorului sau distrus; pe ruta către SUA, JS Express nici nu aduce înapoi în China un colet returnat, nici nu îl reexpediază. Pe unele rute, `Refused` urmat de `Returned` înseamnă că coletul a fost refuzat la livrare și a ajuns înapoi la centrul de sortare al curierului, nu încă la vânzător: Peek afișează "Needs attention". Vezi [lost parcels](guide:lost-parcel) și [returned to sender](guide:returned-to-sender) (în engleză).

## Contact și relații cu clienții JS Express

La data de 30 octombrie 2026, pagina de contact în chineză a JS Express lista următoarele detalii. Pagina de contact în engleză este mai veche și încă oferă alte numere și adrese.

- **Linie telefonică**: 400-0353-518, un număr chinezesc de servicii, indicat și pentru solicitări internaționale. JS Express nu publică programul și nu precizează dacă funcționează din străinătate.
- **QQ și WeChat**: QQ corporate 4000353518 și contul oficial WeChat js-express.
- **Reclamații**: un formular pe pagina de reclamații în engleză și aceeași linie telefonică.
- **Sedii**: sediu central și depozit în Suzhou, alte depozite în China și birouri ale rețelei în Hong Kong și în Walnut, California. Singurul telefon al biroului din SUA este linia telefonică din China.

Reclamațiile JS Express trec prin vânzătorul care a rezervat expedierea. Pentru comandă, contactează vânzătorul; pentru livrare, curierul local.

## Urmărește un colet JS Express cu Peek

[Peek](/) recunoaște un număr JS Express după forma lui: `JSE`, două litere, zece cifre și `YQ`, pe care nu o mai folosește niciun alt curier din catalogul său, și interoghează pe baza lui trackerul JS Express. Dacă ai doar numărul curierului local, lipește-l pe acela: Peek urmărește peste 4.500 de curieri.

Peek afișează istoricul paginii în engleză a JS Express, fiecare scanare cu locul ei și țara de destinație, pe care acea pagină o omite. `到达待取` apare ca "Arrived, awaiting collection". Numerele de telefon sunt eliminate; numele persoanei care a semnat se păstrează. Peek omite fotografiile de livrare și numărul de comandă al magazinului pe care le poate conține fișa JS Express. Ca livrare estimată, afișează ziua din estimarea JS Express; odată ce coletul intră în livrare, inclusiv `Package will be delivered before 9:00pm` al Straightship, nu mai afișează nicio zi, deoarece "Out for delivery" înseamnă deja astăzi. Liniile care nu sunt scanări nu schimbă statusul.

Peek citește liniile din China după ora Beijingului, pe cele ale Straightship în UTC și liniile germane ale J&T Express Germany la decalajul indicat în fiecare linie, deduce fusurile orare ale celorlalte din loc sau destinație și le afișează pe toate în fusul orar al dispozitivului tău, cele mai noi sus.

Când numărul curierului corespunde unui singur transportator din țara ta, precum un cod de bare USPS în SUA sau un număr Straightship în Canada, Peek afișează "Delivery with" acel transportator, cu numărul său, și îl interoghează și pe el; un număr poștal internațional merge la poșta țării tale. Cât timp acel transportator are doar anunțul coletului, fără o scanare proprie, apare în schimb "Announced to …, not received yet". Odată ce transportatorul afișează o scanare cel puțin la fel de recentă ca ultima a JS Express, Peek urmărește coletul acolo, afișând ambele numere și păstrând scanările JS Express. Până atunci rămâne la JS Express, al cărui istoric include adesea și scanările curierului.

Cât timp ai coletul deschis, sau în ziua livrării cu notificările pornite, Peek verifică până la fiecare 10 minute, iar de la ora 6:00 ora locală în ziua în care coletul intră în livrare până la fiecare 2 minute (la fiecare 5 când trebuie să deschidă trackerul JS Express într-un browser), cât timp răspunde propriul tracker JS Express; altfel din oră în oră. Prin servicii de urmărire verifică din oră în oră, la fiecare 3 ore după o zi fără noutăți și până la fiecare 15 minute în ziua livrării în aceeași condiție. Când alt curier preia livrarea, Peek urmează ritmul curierului care livrează. Cu numărul SpeedX al unui colet către SUA, Peek îl poate urmări pe [SpeedX](carrier:speedx). După două zile fără noutăți (12 ore cât timp coletul încă arată "Label created" sau "Received by carrier"), Peek verifică din oră în oră. Te poate notifica la fiecare scanare, doar la pașii importanți (vamă, probleme, "Ready for pickup", în livrare, livrare și retur) sau "Delivery only". Nu poate contacta JS Express sau vânzătorul în locul tău.

## Întrebări despre urmărirea JS Express

### Ce este JS Express (急速国际)?

O companie chineză de logistică pentru magazine online transfrontaliere, operată de 急创速供应链管理（苏州）有限公司. Afirmă că a început în Hong Kong în 2010 și a deschis baza din Suzhou în 2011.

### Unde pot urmări un colet JS Express?

În caseta de mai sus sau pe pagina în engleză „Express Query” a JS Express de la js-exp.com. Pagina în chineză cere mai întâi un cod din imagine.

### Ce înseamnă 未获取到任何数据 în urmărirea JS Express?

„Nu s-au găsit date”: trackerul JS Express nu cunoaște numărul, fie din cauza unei greșeli de tastare, fie pentru că JS Express nu l-a primit încă. Verifică-l cu vânzătorul.

### Cine livrează coletele JS Express în țara mea?

Un curier din țara ta. Istoricul JS Express arată USPS, GOFO sau SpeedX în SUA, Straightship în Canada, Yodel în Regatul Unit, DHL, iMile sau J&T Express în Germania, Colissimo în Franța, CTT Express în Spania și FedEx pe unele rute. În alte țări, întreabă vânzătorul ce companie livrează.

### Pe cine contactez pentru un colet JS Express lipsă?

Vânzătorul de la care ai comandat: el a rezervat expedierea la JS Express și depune reclamațiile. Linia telefonică a JS Express, 400-0353-518, este un număr chinezesc.

:::sources
- [JS Express: 急速国际 (pagina principală)](https://www.js-exp.com/) – compania operatoare
- [JS Express: Introduction](https://www.js-exp.com/EN/index.php?page=6) – Hong Kong 2010, Suzhou 2011, comerț electronic transfrontalier
- [JS Express: 企业介绍 (prezentare)](https://www.js-exp.com/index.php?page=6) – Suzhou 2011, filiale, platforme
- [JS Express: Express Query](https://www.js-exp.com/EN/index.php?page=19) – tracker în engleză, ce afișează, răspunsurile sale
- [JS Express: 快件查询 (urmărire)](https://www.js-exp.com/index.php?page=19) – cod din imagine
- [JS Express: Insurance Claims](https://www.js-exp.com/EN/index.php?page=29) – reclamațiile clientului, 30 de zile, colete nelivrabile
- [JS Express: 投保理赔 (reclamații de asigurare)](https://www.js-exp.com/index.php?page=29) – 30 de zile de la primirea coletului de către JS Express, reclamații din contul clientului
- [JS Express: Complaint](https://www.js-exp.com/EN/index.php?page=32) – formular, linie telefonică
- [JS Express: Contact Us](https://www.js-exp.com/EN/index.php?page=35) – linie telefonică, QQ, WeChat, numere și adrese mai vechi
- [JS Express: 联系我们 (contact)](https://www.js-exp.com/index.php?page=35) – linie telefonică pentru solicitări internaționale, QQ, WeChat, birouri
- [JS Express: 国际专线 (rute internaționale)](https://www.js-exp.com/index.php?catid=13) – ruta Germania: DPD și alții, peste 98% în 8 zile lucrătoare; ruta Australia: Australia Post, Fastway, DPEX
- [JS Express: 急速美国专线 (ruta SUA)](https://www.js-exp.com/index.php?aid=27) – taxe plătite (DDP), retururile nu se aduc înapoi în China și nu se reexpediază, livrat dar neprimit
- [JS Express: 急速欧洲专线 (ruta Europa)](https://www.js-exp.com/index.php?aid=35) – țări, taxe plătite (DDP), coduri poștale izolate
- [Aramex Australia: About Aramex Australia](https://www.aramex.com.au/why-aramex/about-us/) – Fastway a devenit Aramex Australia
- [Revenue: Union Handling Fee press release](https://www.revenue.ie/en/corporate/press-office/press-releases/2026/pr-092926-handling-fee.aspx) – 2 € per tip de produs de la 1 noiembrie 2026, plătit de vânzător sau firma care livrează, poate fi transferat
- [USPS: Missing Mail and Lost Packages](https://www.usps.com/help/missing-mail.htm) – expeditori sau destinatari, de la 7 zile
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – forme de numere, texte de scanare și etape, fusuri orare (liniile J&T Express Germany la decalajul indicat din 9 noiembrie), scanări USPS, GOFO, SpeedX, Straightship, Yodel, DHL, iMile, J&T Express Germany, FedEx, Colissimo și CTT Express, scanările unui curier fără nume (hub, depozit, încercare ratată, redirecționare, excepție), scanările hub-ului unui partener american fără nume, centrele germane iMile, modul de rezervă browser, vamă, refuzuri și retururi, numărul curierului, ce afișează Peek și cum predă urmărirea
:::
