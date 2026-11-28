---
title: TopYou urmărire colet: numere TYZR, statusuri și curieri
description: Urmărește un colet TopYou (通邮 / SZTY56) din China: coduri TYZR…YQ, semnificația scanărilor, curierii locali (USPS, GOFO, iMile, DPD) și contact Shenzhen.
slug: topyou-urmarire-colet
published: 2026-11-28
updated: 2026-11-28
---

TopYou (通邮, Tongyou, 通邮集团 sau SZTY56 — 深圳市通邮物流科技（集团）股份有限公司, care afirmă că a fost fondată la Shenzhen în 2016) este un expeditor transfrontalier ce operează rute internaționale din China. Lipește codul tău de 17 caractere `TYZR…YQ` în caseta de mai sus (sau cu majuscule pe track.szty56.com) pentru a-l urmări din Shenzhen prin export, zbor, vamă și predarea către curierul local — precum USPS în SUA, GOFO în Franța și Italia, iMile în Germania sau DPD în Austria.

## Numărul de urmărire TopYou: cum arată

Codurile de transport TopYou sunt formate din `TYZR`, o literă, zece cifre și `YQ`, în total 17 caractere; unele au o altă literă în locul lui `R`. Cu cifre inventate:

| Număr | Ce este |
| --- | --- |
| `TYZRA4829105736YQ` | `TYZR`, o literă, zece cifre și `YQ`: un cod TopYou. |
| `TYZRA482910573YQ` | Doar nouă cifre: nu este formatul TopYou. Verifică dacă lipsește o cifră. |
| `JXCAB4829105736YQ` | Se termină tot cu `YQ`, ca și codurile altor expeditori chinezi, dar începe cu `JXC`: un cod [Juxi Express](carrier:juxi-express). |

Spațiile și literele mici nu contează când lipești codul în Peek. Trackerul propriu TopYou, [track.szty56.com](https://track.szty56.com/), face diferența între majuscule și minuscule, așa că tastează codul acolo cu majuscule. Dacă nu îl găsești în detaliile comenzii, vezi [how to find your tracking number](guide:find-tracking-number) (în engleză).

**Un al doilea număr.** Fișa TopYou poate conține și numărul propriu al curierului care livrează, afișat în trackerul TopYou.

**Copiază numărul, nu un link.** Peek nu citește linkuri din trackerul TopYou: lipește numărul ca atare.

## Statusurile de urmărire TopYou explicate

TopYou își scrie scanările în engleză. În ordinea în care le primește un colet:

| TopYou afișează | Ce înseamnă pentru tine | Peek afișează |
| --- | --- | --- |
| `Parcel Data Received` | Magazinul a trimis către TopYou detaliile coletului, dar TopYou nu are încă coletul. | "Label created" |
| `Arrived at the first mile sorting center` | Depozitul TopYou din China are coletul: primul scan în mâinile TopYou. | "Received by carrier" |
| `Departed from the first mile sorting center` | A plecat din acel depozit. | "In transit" |
| `Arrived at airport of the original country` | La aeroportul de plecare, înainte de zbor. | "In transit" |
| `Departed from Airport to destination country` | Zborul spre țara ta a plecat. | "In transit" |
| `Arrived at destination airport` | A aterizat în țara de destinație. | "In transit" |
| `Departed from customs of` | Vama țării tale l-a eliberat. TopYou lasă gol numele țării după „of”, iar Peek elimină „of”. | "In transit" |
| `At U.S. Postal Service facility, Accepted by U.S. Postal Service` | În SUA: USPS a preluat coletul de la TopYou după zbor. Nu este o expediere nouă. | "In transit" |

**Coduri mai noi.** TopYou formulează diferit unele dintre aceleași etape pe codurile sale mai noi:

| TopYou afișează | Ce înseamnă pentru tine | Peek afișează |
| --- | --- | --- |
| `Carrier received the shipment` | TopYou are coletul în China: primul său scan. | "Received by carrier" |
| `Shipment arrived at a carrier facility` | Merge mai departe din primul depozit TopYou. Coletul nu este preluat din nou. | "In transit" |
| `Shipment started the customs clearance process at origin` | Vămuirea la export din China, un pas obișnuit înainte de zbor. Vama nu îl reține și nu este vama de import din țara ta. | "In transit" |
| `Shipment arrived in the destination country` | A ajuns în țara de destinație. | "In transit" |
| `Shipment started the customs clearance process at destination` | Vama de import din țara ta are coletul. | "At customs" |
| `Shipment completed the customs clearance process at destination` | Vămuit și pe drum spre curierul care livrează. | "In transit" |
| `Shipment is out for delivery` | Încă nu este în livrare, indiferent ce spun cuvintele: în Franța și Italia marchează predarea către GOFO la primul său centru de sortare, cu zile înainte să plece șoferul GOFO. | "In transit" |

**Rute livrate de poștă.** Pe o rută livrată de poșta națională, TopYou formulează scanările în alt mod până la vama țării tale:

| TopYou afișează | Ce înseamnă pentru tine | Peek afișează |
| --- | --- | --- |
| `Accepted by carrier` | TopYou are coletul în China: primul său scan. | "Received by carrier" |
| `Inbound in sorting center` | Înregistrat în primul depozit TopYou, câteva minute mai târziu. Nu a plecat încă. | "Received by carrier" |
| `Outbound in sorting center` | A plecat din acel depozit. TopYou adaugă `, Lastmile Number:` și codul sub care livrează poșta; Peek elimină acea terminație din linie. | "In transit" |
| `Received by line-haul`, `Hand over to airline` | Preluat pentru transportul principal, apoi predat companiei aeriene. | "In transit" |
| `Airline arrived at transit country or district.`, `Airline departed from transit or district country.` | O escală în drum spre țara ta. | "In transit" |
| `Airline arrived at destination country`, `Arrive at destination country` | A ajuns în țara de destinație. | "In transit" |
| `Export clearance success` | Vama de export a Chinei l-a aprobat. Nu este reținut. | "In transit" |
| `Import clearance success` | Vama țării tale l-a eliberat pentru poștă. Poșta poate afișa în continuare propriile scanări vamale. | "In transit" |
| `Customs Clearance Completed Successfully` | Vama țării tale l-a eliberat, la câteva minute după predarea către curierul local, care are acum coletul. | "In transit" |

**Scanările curierului care livrează.** După predare, trackerul TopYou poate continua cu scanările proprii ale curierului local: ale USPS pentru SUA, sau ale GOFO în Franța și Italia, precum `Arrived at sorting center`, `The package left sorting center` și `Package departed from sorting center` (la sau plecând dintr-un centru de sortare GOFO: "In transit"), `Arrived at GOFO Regional Destination Facility` (la depozitul regional GOFO: "In transit") și `The driver is out for delivery` (șoferul GOFO îl aduce: "Out for delivery").

În Germania, scanările iMile vin cu numele iMile pentru etapă, o virgulă și propoziția iMile: `In Transit, Your order has been shipped from 【iMile Köln DC Center】.` (trimis dintr-un centru iMile: "In transit"), `Delivery, Your order has arrived at 【Dortmund01】` (la stația iMile din care se livrează: "In transit"), `Delivery, Your order has assigned delivery drvier.` (a fost alocat un șofer, dar nu a plecat încă; greșeala de ortografie este a iMile: "In transit") și `Delivery, Our delivery associate is out for delivery.` (șoferul iMile îl aduce: "Out for delivery"). O linie care începe cu `Delivery` nu înseamnă deci că șoferul a pornit pe traseu. O linie iMile fără mișcare, precum o inventariere, lasă statusul neschimbat. Un aviz al unui punct de ridicare ce începe cu `Arrived, The parcel has been delivered to 【】. Please collect your parcel promptly at 【】 using your pickup code.` înseamnă că coletul așteaptă acolo să îl ridici cu codul tău: "Ready for pickup".

În Austria, scanările DPD apar în engleza DPD: `In parcel delivery center` (la depozitul de livrare DPD: "In transit"), `In delivery` (șoferul DPD îl livrează: "Out for delivery"), `Unfortunately, consignee not present. Notification was left.` (nu era nimeni acasă, iar DPD a lăsat un aviz: "Delivery attempted"), `Parcel was handed over to Pickup parcel shop` și `Takeover by Pickup parcel shop` (te așteaptă într-un DPD Pickup parcelshop: "Ready for pickup") și `Parcel was not picked up` (neridicat la timp, astfel că DPD îl trimite înapoi la expeditor: "Needs attention").

În Spania, scanarea partenerului de livrare după vamă poate indica `Parcel in transit` (este în drum spre tine: "In transit").

## Cine livrează coletele TopYou

**Statele Unite.** USPS preia coletul de pe ruta TopYou după zbor, iar trackerul TopYou poate continua cu scanările acestuia. Al doilea număr din fișa TopYou este atunci cel prin care îl urmărește [USPS](carrier:usps).

**Franța și Italia.** Poate livra GOFO: [GOFO France](carrier:gofo-fr) sau [GOFO Italy](carrier:gofo-it). Trackerul TopYou arată predarea ca `Shipment is out for delivery`, urmată de scanările GOFO.

**Germania.** Poate livra [iMile](carrier:imile), iar trackerul TopYou continuă cu scanările iMile.

**Austria.** Poate livra [DPD Austria](carrier:dpd-at), iar trackerul TopYou continuă cu scanările DPD.

**România și alte țări.** Fișa TopYou poate indica numărul curierului local, dar nu și numele acestuia, iar denumirea rutei TopYou pe care călătorește coletul nu îți spune nici ea acest lucru: o rută poate purta numele altui transportator. Pe o rută livrată de poștă, numărul poștei încheie și linia `Outbound in sorting center`, după `Lastmile Number:`. Întreabă deci vânzătorul ce companie livrează comanda ta.

## Coletul TopYou nu ajunge sau urmărirea s-a blocat

**Blocat la `Shipment started the customs clearance process at destination`.** Vama țării tale are coletul. Dacă ți se solicită taxe de import — care în România și în întreaga UE includ din 1 noiembrie 2026 o taxă de manipulare de 2 € per tip de produs, indiferent de valoare — vezi [parcels held at customs](guide:customs) (în engleză); dacă rămâne acolo zile întregi, anunță vânzătorul. Mai multe în [why tracking stops updating](guide:tracking-not-updating) (în engleză).

**Nimic după `Departed from customs`, `Shipment completed the customs clearance process at destination`, `Customs Clearance Completed Successfully` sau `Import clearance success`.** Curierul local ar trebui să aibă coletul. Caută numărul lui pe trackerul TopYou sau întreabă vânzătorul ce curier livrează și sub ce număr. Detalii în [tracking a parcel from China](guide:tracking-from-china) (în engleză).

**Negăsit sau fără scanări încă.** Trackerul TopYou răspunde `查询不到` (negăsit) pentru un număr pe care nu îl cunoaște și poate răspunde `暂无轨迹信息` (încă fără date de urmărire) înainte de primul scan. Peek afișează atunci "Waiting for TopYou" și continuă verificările; dacă TopYou tot nu cunoaște numărul după două zile și nimic altceva nu a răspuns pentru el, Peek afișează "After two days, the carrier still doesn’t know this number. Check it, choose another carrier or delete the parcel." Peek poate distinge din răspunsul TopYou când acesta listează expedierea dar nu a scanat-o încă: atunci TopYou cunoaște numărul, iar Peek continuă verificările și afișează "Waiting for TopYou", fără să ceară verificarea numărului. Dacă TopYou a afișat doar `Parcel Data Received` cel puțin o zi, Peek afișează "The sender hasn’t handed it to TopYou yet. Tracking starts when they do." Întreabă magazinul când îl va trimite. Verifică numărul caracter cu caracter față de mesajul vânzătorului; dacă după câteva zile nu apare nimic, întreabă vânzătorul dacă a fost expediat cu adevărat.

**Livrat, dar nu a ajuns nimic.** Pentru un colet din SUA livrat de USPS: întreabă la oficiul poștal, apoi anunță vânzătorul. Mai multe în [delivered but not received](guide:delivered-not-received) (în engleză).

**Pierdut sau returnat.** Vânzătorul a rezervat expedierea la TopYou, deci vânzătorul sau platforma este locul unde ceri rambursare sau un nou colet. Vezi [lost parcels](guide:lost-parcel) și [returned to sender](guide:returned-to-sender) (în engleză).

## Contact și relații cu clienții TopYou

La data de 16 noiembrie 2026, site-ul TopYou, [szty56.com](https://www.szty56.com/), indică un număr de telefon în Shenzhen, +86 755 8181 9898 (0755-81819898 în China), și programul 9:00–18:00 în zilele lucrătoare, fără fus orar menționat. Nu listează un serviciu pentru destinatari: clienții săi sunt magazinele care expediază prin el. Vânzătorul a rezervat coletul la TopYou și este cel pe care trebuie să îl întrebi despre comanda ta, iar platforma dacă vânzătorul nu răspunde.

## Urmărește un colet TopYou cu Peek

[Peek](/) recunoaște un număr TopYou după forma sa: `TYZR`, o literă, zece cifre și `YQ`, pe care nu o folosește niciun alt curier din catalogul său. Cu o altă literă în locul lui `R`, potrivirea este doar slabă: Peek afișează "Checking tracking services…" și întreabă trackerul TopYou dacă cunoaște numărul. Peek întreabă trackerul TopYou doar despre numere de aceste două forme. Dacă ai doar numărul curierului local, lipește-l pe acela: Peek urmărește peste 4.500 de curieri.

Peek citește propriul tracker TopYou și afișează statusul, fiecare scanare în formularea TopYou sau a curierului preluat, țara de destinație și greutatea din fișa TopYou. Un loc care este doar o țară este omis din scanările proprii TopYou, astfel încât scanarea de la aeroportul din China să nu pară din țara ta. Când fișa TopYou marchează coletul ca livrat sau returnat, Peek afișează "Delivered" sau "Returned to sender", chiar dacă formularea ultimului scan nu o spune. Scanările proprii TopYou sunt după ora Beijingului. Pentru scanările curierului local preluate, trackerul TopYou nu indică întotdeauna fusul orar corect, așa că Peek le calculează ora și le afișează pe toate în fusul orar al dispozitivului tău. Nu există dată estimată de livrare: TopYou nu furnizează una.

Când fișa TopYou conține numărul curierului local, Peek îl încearcă la poșta țării tale dacă este un cod poștal internațional valid (două litere, nouă cifre cu cifră de control corectă, două litere), sau la unicul curier căruia îi aparține cu siguranță formatul, atâta timp cât Peek citește propriul tracker al acelui curier. Odată ce acel curier arată un scan cel puțin la fel de recent ca ultimul de la TopYou, Peek urmărește coletul acolo și afișează ambele numere, păstrând scanările TopYou în istoric. Un număr pe care Peek nu îl poate lega de un singur curier — precum 20 de cifre simple, folosite de USPS și alții — nu este încercat: Peek rămâne la trackerul TopYou, care poate arăta și scanările curierului local.

Peek verifică până la fiecare 10 minute cât timp ai coletul deschis, sau în ziua livrării cu notificările pornite (altfel din oră în oră), și din oră în oră după ce TopYou nu mai are noutăți: după 12 ore fără scan nou cât timp coletul este încă "Label created" sau "Received by carrier", ori după două zile în orice altă etapă. Odată ce coletul este în livrare, verifică până la fiecare 2 minute (la fiecare 5 când verificarea deschide un browser): în acea zi, de la ora 6:00 ora locală, cât timp răspunde propriul tracker TopYou. Prin servicii de urmărire verifică din oră în oră, la fiecare 3 ore după o zi fără noutăți și până la fiecare 15 minute în acea zi cât timp ai coletul deschis sau notificările pornite. Când alt curier preia livrarea, Peek urmează ritmul acelui curier. Îți poate trimite notificări la fiecare scanare, doar la pașii importanți sau "Delivery only". "Important steps" (livrare, ridicare, vamă, încercări ratate și retururi) te alertează la vama țării tale (`Shipment started the customs clearance process at destination`), la `The driver is out for delivery` al GOFO, la `Delivery, Our delivery associate is out for delivery.` al iMile și la livrare — nu la vămuirea de export din China, zbor, eliberarea din vamă sau `Shipment is out for delivery`. Peek nu poate contacta TopYou sau vânzătorul în locul tău.

## Întrebări despre urmărirea TopYou

### Ce este TopYou (通邮 / SZTY56)?

Este 深圳市通邮物流科技（集团）股份有限公司, numită și 通邮集团, Tongyou sau SZTY56, un expeditor transfrontalier din Shenzhen, China. Afirmă că a fost fondată în 2016, are filiale și centre de sortare în Shenzhen, Guangzhou, Shanghai și alte orașe din China și gestionează peste 800.000 de comenzi pe zi.

### Cum arată un cod de urmărire TopYou și de ce nu este găsit?

Codurile TopYou au 17 caractere: `TYZR` (sau `TYZ` și altă literă), o literă, zece cifre și `YQ`. Trackerul TopYou de la track.szty56.com ține cont de majuscule și răspunde `查询不到` când este necunoscut sau `暂无轨迹信息` înainte de primul scan.

### De ce afișează TopYou țara mea înainte ca coletul să părăsească China?

Pentru că trackerul TopYou scrie țara de destinație lângă propriile scanări, chiar și la aeroportul de plecare. Urmărește textul: `Arrived at airport of the original country` înseamnă că coletul încă așteaptă zborul.

### Cine livrează coletele TopYou în SUA, Europa și în alte țări?

În Statele Unite preia USPS după zbor. În Franța și Italia poate fi GOFO, în Germania iMile, iar în Austria DPD. În alte țări, fișa TopYou poate afișa un al doilea cod fără să numească curierul: întreabă vânzătorul ce companie livrează.

### Cum contactez TopYou pentru un colet întârziat sau lipsă?

Întreabă mai întâi vânzătorul sau platforma, deoarece magazinul a rezervat expedierea la TopYou. Site-ul TopYou szty56.com indică un număr din Shenzhen (+86 755 8181 9898, zile lucrătoare 9:00–18:00), dar nu un birou de suport pentru destinatari.

:::sources
- [TopYou: 通邮集团 (pagina principală)](https://www.szty56.com/) – telefon, program
- [TopYou: 关于我们 (despre noi)](https://www.szty56.com/view?path=about/about) – fondată în 2016, filiale, comenzi pe zi, servicii
- [TopYou: 轨迹查询 (urmărire)](https://track.szty56.com/) – trackerul TopYou
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – taxă de manipulare a UE de 2 € de la 1 noiembrie 2026
- [European Commission: Guidance on the 3 EUR customs duty and Union handling fee](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 2 € per tip de produs vândut la distanță, indiferent de valoare
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – denumirea companiei și alte denumiri, formatul TYZR, majuscule în trackerul TopYou, fără link citit de Peek, formulări și etape ale scanărilor, țara de destinație lângă scanările TopYou, ora Beijingului și fusurile scanărilor preluate, preluarea de către USPS, GOFO, iMile și DPD, scanările curierilor locali și avizul punctului de ridicare, al doilea număr, numărul Lastmile pe rutele poștale și denumirea rutei, numere necunoscute și fără scanări, datele coletului înainte de preluare, ce afișează Peek și cum predă urmărirea
:::
