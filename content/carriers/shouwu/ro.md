---
title: Shouwu urmărire colet: SWECN…YQ, statusuri și curieri
description: Urmărește un colet Shouwu International (守务国际) din China: cod SWECN…YQ, explicația statusurilor, curierii de livrare în Europa și SUA, vamă și suport.
slug: shouwu-urmarire-colet
published: 2026-11-28
updated: 2026-11-28
---

Shouwu International (守务国际, numită și SWE) este o firmă de logistică din Shanghai care expediază colete pentru vânzătorii online din China către Europa și Statele Unite, unde livrarea finală o face poșta sau un curier local. Dacă un magazin ți-a trimis comanda prin Shouwu, codul începe cu `SWECN` și se termină cu `YQ`: lipește-l în caseta de mai sus sau în pagina de urmărire Shouwu la [admin.shshouwu.com](https://admin.shshouwu.com/track.html). Magazinul îți trimite codul odată cu detaliile de expediere ale comenzii.

## Numărul de urmărire Shouwu: cum arată

Codurile de transport (waybill) Shouwu sunt formate din `SWECN`, douăsprezece cifre și `YQ`, în total 19 caractere. Cu cifre inventate:

| Număr | Ce este |
| --- | --- |
| `SWECN482910573649YQ` | `SWECN`, douăsprezece cifre și `YQ`: un cod Shouwu. |
| `SWECN48291057364YQ` | Doar unsprezece cifre: nu este formatul Shouwu. Verifică dacă lipsește o cifră. |
| `JSEAA4829105736YQ` | Se termină tot cu `YQ`, dar începe cu `JSE`: cod [JS Express](carrier:js-express). |

Și alți expeditori chinezi își termină codurile cu `YQ`, cu propriile litere la început și, în catalogul Peek, de obicei cu zece cifre. `SWECN` și cele douăsprezece cifre identifică Shouwu. Spațiile, cratimele și literele mici nu contează în Peek.

**Unde îl găsești.** Dacă nu apare în e-mailul de expediere al magazinului sau în pagina comenzii tale, vezi [how to find your tracking number](guide:find-tracking-number) (în engleză).

**Este posibil să primești direct codul curierului.** Shouwu lasă vânzătorul să aleagă ce număr transmite mai departe: pe al Shouwu, care urmărește coletul cap-coadă, sau pe cel al curierului care livrează în țara ta. Astfel, un colet Shouwu poate ajunge la tine cu un cod [Royal Mail](carrier:royal-mail), Yodel sau [Evri](carrier:evri) în Regatul Unit ori [USPS](carrier:usps) în SUA. Lipește-l oricum în caseta de mai sus: Peek urmărește peste 4.500 de curieri.

**Un link de urmărire.** Butonul „Copy Link” de pe pagina Shouwu oferă un link către tracker cu `?q=` urmat de număr. Deschide direct acel colet, iar linkul întreg poate fi lipit în Peek. Pagina Shouwu acceptă până la 40 de numere deodată, câte unul pe rând.

## Statusurile de urmărire Shouwu explicate

Pagina de urmărire Shouwu este în engleză sub un titlu în chineză. Afișează pentru fiecare colet un status și țara de destinație, apoi ora și textul fiecărui scan, dar nu și locul acestuia.

| Statusul Shouwu | Ce înseamnă pentru tine | Peek afișează |
| --- | --- | --- |
| `Not Found` | Shouwu nu are încă nimic pe acest număr: „We seem can't identify the number.” | „Waiting for Shouwu International” |
| `Preparing` | Shouwu îl explică drept colet predat depozitului său din China sau aflat acolo la cântărire și sigilare. Poate apărea înainte de primul scan. | „Label created”, apoi „Received by carrier” după preluarea de la partener sau un scan în depozit |
| `In Transit` | Orice etapă, de la predarea către transportator până la vamă și traseul din țara ta. | „In transit” sau etapa ultimului scan, precum „At customs”; „Label created” cât timp există doar datele comenzii și `Carrier received the shipment` |
| `Undelivered` | O încercare de livrare a eșuat. | „Delivery attempted”, sau „Ready for pickup” ori „Returned to sender” dacă ultimul scan spune asta |
| `Delivered` | Livrat destinatarului. | „Delivered” |

Shouwu precizează că statusurile sale sunt „auto-judged by the system” și „only for information reference use”. Scurtele descrieri ale scanărilor spun mai mult:

| Shouwu afișează | Ce înseamnă pentru tine | Peek afișează |
| --- | --- | --- |
| `Shipment information received`, `Order information received. We're expecting your parcel to arrive with us.` | Vânzătorul a rezervat expedierea. Shouwu nu are încă coletul. | „Label created” |
| `Carrier received the shipment` | Shouwu scrie asta la două ore după datele comenzii, cu zile înainte de primul scan în depozit: nimeni nu are încă coletul. | „Label created” |
| `Picked up by Shipping Partner` | Partenerul de transport al Shouwu a ridicat coletul de la vânzător: primul scan după datele comenzii. | „Received by carrier” |
| `Arrival processing center` | Depozitul Shouwu din China a recepționat coletul. | „Received by carrier” |
| `Package processing completed` | Depozitul a terminat procesarea, înainte de zbor. | „Received by carrier” |
| `Shipment started the customs clearance process at origin` | A început vămuirea la export în China, un pas obișnuit înainte de zbor. Vama nu reține coletul. | „In transit” |
| `The package is shipped from the transfer warehouse to the airport Export customs clearance in progress` | Trimis din depozitul de transfer la aeroport, unde are loc vămuirea la export în China: nu este vama țării tale. | „In transit” |
| `Export customs declaration completed`, `Export customs clearance completed`, `Export Released` | China a acordat liberul de vamă la export; nimic nu este blocat. | „In transit” |
| `The parcel has arrived at the airport and is currently being loaded onto a flight` | La aeroportul de plecare după vămuirea la export, în curs de încărcare în avion. | „In transit” |
| `Arrived at Shanghai Pudong Airport`, `Departed from export airport` | La aeroportul din Shanghai, apoi plecat cu avionul din China. | „In transit” |
| `The ship has already set sail` | Pe o rută maritimă: nava a plecat din China, după vămuirea la export și înainte să ajungă în portul din străinătate. | „In transit” |
| `Arrived at airport of Destination`, `Shipment arrived in the destination country` | A aterizat în țara de destinație. | „In transit” |
| `NOA received` | Agentul din țara de destinație a primit avizul de sosire de la compania aeriană; urmează vama. | „In transit” |
| `Shipment started the customs clearance process at destination`, `Customs clearance in progress`, `Currently undergoing customs clearance in the destination country`, `Currently processing import customs clearance`, `Customs clearance for imports` | Vama țării de destinație îl procesează. | „At customs” |
| `Custom clearance completed`, `Shipment completed the customs clearance process at destination`, `Customs clearance has been completed`, `Customs clearance in the destination country has been completed` | Vama țării de destinație i-a dat drumul. | „In transit” |
| `Scheduled For Delivery` | Textul Shouwu după vămuirea la import, cu zile înainte de depozitul și traseul curierului: o livrare este planificată, nu în desfășurare. | „In transit” |
| `Delivery to local courier`, `The goods have been deliverey to the final delivery service provider`, `The goods have been delivered to the carrier` | Predat curierului care livrează în țara de destinație, nu livrat ție („deliverey” este grafia Shouwu). | „In transit” |
| `Your consignment has been picked up` | Curierul l-a ridicat din depozitul Shouwu din străinătate, după vamă: nu este ridicarea de la vânzător (aceea este `Picked up by Shipping Partner`, la început) și nici o ridicare de către tine. | „In transit” |
| `Picked up` | În SUA, FedEx a preluat coletul din depozitul Shouwu după vămuirea la import (rândul menționează orașul și codul poștal). Nu este ridicarea de la vânzător (`Picked up by Shipping Partner`) și nici o ridicare de către tine. | „In transit” |
| `Arrived at FedEx location`, `Departed FedEx location` | Scanările proprii FedEx în centrele sale din SUA, după preluarea din depozitul Shouwu. | „In transit” |
| `The shipment has been processed in the destination parcel center` | DHL l-a sortat în centrul de colete care deservește adresa ta, înaintea traseului de livrare. | „In transit” |
| `Mis routed` | Curierul care livrează în Germania a trimis coletul la centrul greșit și îl redirecționează: o întârziere, nu o oprire. Livrarea poate avea loc chiar în aceeași zi. | „In transit” |
| `Out for delivery` | Pe traseul de livrare al curierului local. | „Out for delivery” |
| `Abnormal delivery` | Livrarea nu a reușit. Motivul curierului apare ca un scan separat între paranteze unghiulare, de exemplu `<Incorrect address.>`. | „Delivery attempted” |
| `The recipient is unknown` | Curierul DHL din Germania nu a găsit acest destinatar la adresă; coletul se întoarce. | „Delivery attempted” |
| `Shipment will be returned to origin-Bad address` | DHL trimite coletul înapoi deoarece nu a putut găsi adresa: este pe drumul de întoarcere, nu a ajuns încă la expeditor. Întreabă vânzătorul. | „Needs attention” |
| `Delivered by post man`, `This parcel has been delivered` | Poșta sau curierul local l-a livrat. | „Delivered”, odată ce statusul Shouwu spune `Delivered` |
| `Shipment sent directly from parcel center to business customer` | DHL l-a livrat direct din centrul său către o adresă de firmă. Dacă DHL returnase coletul, aceeași linie înseamnă că returul a ajuns la expeditor. | „Delivered”, sau „Returned to sender” după un retur |

Când iMile livrează în Italia, istoricul Shouwu preia liniile iMile, cu centrul între paranteze 【】 (al tău poate indica alt centru decât Milan center sau ROM 10):

| Shouwu afișează | Ce înseamnă pentru tine | Peek afișează |
| --- | --- | --- |
| `【Milan center】Received.` | Centrul iMile a primit coletul. | „In transit” |
| `【Milan center】Parcel shipped to【ROM 10】.`, `【ROM 10】Arrived.` | Trimis spre stația de livrare iMile, apoi sosit acolo. | „In transit” |
| `【ROM 10】Shipment out for delivery.` | Pe traseul de livrare al iMile. | „Out for delivery” |
| `<User not at home>`, `【ROM 10】 Back To DS.` | Șoferul nu te-a găsit acasă, iar coletul s-a întors la stația de livrare. | „Delivery attempted” |
| `【ROM 10】Parcel shipped to【Milan Transit Center】.` | După două încercări eșuate, stația îl trimite înapoi la centrul iMile. | „In transit” |
| `Back to Sender for reason: wrong recipient address or address related problem` | După încercările eșuate, iMile trimite coletul înapoi, aici din cauza adresei (după două puncte poate apărea alt motiv): este pe drumul de retur, nu încă la expeditor. Întreabă vânzătorul. | „Needs attention” |
| `【Milan Transit Center】Order Closed.` | iMile închide comanda de livrare fără a livra; întreabă vânzătorul. | „Needs attention” |

**Când livrează DPD.** Pe o rută livrată de [DPD](carrier:dpd-de), istoricul Shouwu continuă cu formulările DPD, fiecare încheiată cu punct: `In transit.`, apoi `At parcel delivery centre.` — depozitul DPD care livrează, înainte de traseu și din nou după o livrare ratată („In transit”) — și `Out for delivery.` („Out for delivery”). `Unfortunately we have not been able to deliver your parcel. Consignee address not correct.` reprezintă o încercare eșuată din cauza unei adrese greșite: coletul se întoarce la depozitul DPD, nu la expeditor („Delivery attempted”).

**Linii în chineză.** Unele colete primesc și linii scrise de Shouwu în chineză, pe care Peek le afișează așa cum sunt scrise. `货物电子信息已经收到` (datele electronice ale mărfii au fost primite) apare odată cu datele comenzii: „Label created”. Apoi, toate „In transit”: `申报过港中` după codul unui lot (lotul este declarat pentru drumul spre Hong Kong), `已到港，安排航班中` (la portul de plecare, în așteptarea unui zbor), `预计23号的航班` (zborul rezervat, aici pentru ziua de 23; cu `二程` în față pentru al doilea segment), `航班已飞` și `二程航班已飞` (zborul sau cel al segmentului al doilea a decolat) și `已落地` (a aterizat). `已落地，清关提货中` (aterizat, în vămuire și preluare de la compania aeriană) este „At customs”; `清关完成` (vămuit) și `已交仓扫描` (scanat la intrarea în depozitul curierului local) sunt din nou „In transit”.

**Ore decalate cu câteva ore.** Sistemul Shouwu înregistrează ora locală a fiecărui scan ca și cum ar fi ora Beijingului, inclusiv pentru scanările din străinătate, iar trackerul său afișează acea oră în fusul tău orar. Astfel, un scan din Europa sau SUA pare cu ore mai devreme, iar zborul pare mai scurt. Urmărește ordinea rândurilor. Peek determină fusul orar al fiecărui scan din locul lui sau din etapa traseului și afișează orele în fusul orar al dispozitivului tău; pe peektracker.com și în aplicațiile iPhone și Android, istoricul se încheie cu „Times are in your time zone:” și numele acestuia.

## Cine livrează coletele Shouwu

Shouwu afirmă că lucrează direct cu USPS, Royal Mail, La Poste, DHL, DPD și Hermes. Paginile sale de rute, în chineză, indică curierii pe țări:

- **Regatul Unit.** Rutele către Regatul Unit acoperă toată țara. Curierii de ultim kilometru sunt Royal Mail, Yodel și Evri (scris ERVI acolo); ruta pentru produse cu baterii merge în principal prin Royal Mail.
- **Statele Unite.** Rutele către SUA acoperă toată țara, cu livrare în principal prin USPS; în istoricele citite de Peek livrează acolo și [FedEx](carrier:fedex).
- **Uniunea Europeană.** Shouwu are rute către Germania, livrate în principal prin [DHL](carrier:dhl), [Hermes](carrier:hermes-de) sau [DPD](carrier:dpd-de) în funcție de rută, Franța (în principal [Colissimo](carrier:la-poste)), Țările de Jos (în principal DHL), Suedia (în principal [Early Bird](carrier:early-bird)) și Italia (o rută economică de 10 până la 12 zile lucrătoare, livrată de UniUni și [Poste Italiane](carrier:poste-italiane); în istoricele citite de Peek livrează acolo și [iMile](carrier:imile), cu liniile de mai sus). Și aici vânzătorul poate transmite direct codul curierului.
- **România și alte țări fără rută listată (inclusiv Irlanda, Canada și Australia).** Shouwu nu listează o rută dedicată și nu numește un curier. Întreabă vânzătorul cine livrează.

Pagina de urmărire Shouwu nu afișează numele sau numărul curierului, dincolo de ce poate apărea în textul unui scan.

**Livrări ratate.** Încercările, avizele și punctele de ridicare urmează regulile curierului local. La `Undelivered`, pagina Shouwu te îndeamnă să contactezi transportatorul pentru o nouă livrare sau să ridici coletul imediat, înainte de termenul curierului, altfel „might be returned to the sender”. Condițiile rutelor Shouwu pentru Regatul Unit și SUA, în chineză, prevăd că un colet nelivrabil nu este trimis înapoi în China și este distrus implicit. Oricum ar fi, acționează repede când primești un aviz. Vezi [missed delivery](guide:missed-delivery) și [uncollected parcels](guide:uncollected-parcel) (în engleză).

## Coletul Shouwu nu ajunge sau urmărirea s-a blocat

**`Not Found`.** Shouwu indică patru motive: nu a acceptat încă coletul, nu l-a scanat încă, numărul este greșit sau coletul a fost expediat demult și datele de urmărire au expirat. Procesarea durează de obicei câteva zile. Peek afișează „Waiting for Shouwu International” și continuă verificările. Dacă Shouwu nu cunoaște numărul nici după două zile și nimic altceva nu a răspuns pentru el, Peek afișează „After two days, the carrier still doesn’t know this number. Check it, choose another carrier or delete the parcel.” Verifică numărul caracter cu caracter; dacă după câteva zile nu apare nimic, întreabă vânzătorul.

**Blocat la `Preparing`, `Shipment information received` sau `Carrier received the shipment`.** Shouwu nu are încă coletul sau nu l-a expediat din depozit. Întreabă vânzătorul când l-a predat.

**Blocat la vamă.** După `Shipment started the customs clearance process at destination`, `Customs clearance in progress`, `Currently undergoing customs clearance in the destination country`, `Currently processing import customs clearance`, `Customs clearance for imports` sau `已落地，清关提货中`, coletul așteaptă vama țării tale. În UE, inclusiv în România, bunurile cumpărate online din afara UE datorează o taxă vamală de 3 € pe tip de produs pentru coletele în valoare de până la 150 € (de la 1 iulie 2026) și taxa de manipulare a UE de 2 € pe tip de produs, la orice valoare (de la 1 noiembrie 2026); paginile de rute Shouwu nu spun cine le plătește. Dacă ți se cer taxe de import, vezi [parcels held at customs](guide:customs) (în engleză).

**Nimic după `Delivery to local courier`.** Curierul îl are, dar trackerul Shouwu nu dă numărul curierului. Cere vânzătorului numărul curierului și lipește-l în Peek. Mai multe detalii în [why tracking stops updating](guide:tracking-not-updating) și [tracking a parcel from China](guide:tracking-from-china) (în engleză).

**Livrat, dar nu a venit nimic.** Condițiile rutelor Shouwu prevăd că atunci când site-ul curierului indică o livrare care nu a ajuns, Shouwu ajută la verificări, dar nu despăgubește nimic. Întreabă deci mai întâi curierul, apoi vânzătorul. În SUA, USPS acceptă o căutare Missing Mail de la expeditor sau destinatar, începând la 7 zile de la expediere. Vezi [delivered but not received](guide:delivered-not-received) (în engleză).

**Pierdut sau deteriorat.** Clientul Shouwu este vânzătorul, iar potrivit condițiilor rutelor Shouwu acesta trebuie să depună reclamație în termen de 30 de zile de la expedierea coletului de către Shouwu. Pentru un colet fără scan de livrare după ce depozitul Shouwu l-a preluat, Shouwu plătește valoarea declarată plus taxa de transport, cel mult 200 de yuani per expediere, și nimic pentru deteriorare în tranzit. Cere așadar magazinului sau platformei o rambursare ori un nou colet și nu amâna. Vezi [lost parcels](guide:lost-parcel) (în engleză).

## Contact și relații cu clienții Shouwu

La data de 30 octombrie 2026, site-ul Shouwu, www.sedel.com, este în chineză și este făcut pentru vânzătorii care rezervă expedieri. Listează numere de telefon mobil pentru sediul din Shanghai și cinci filiale, un link de chat QQ și coduri WeChat. Nu există adresă de e-mail, formular de contact, contact în engleză și nimic pentru destinatari. Pagina de urmărire nu afișează niciun contact.

Pentru comanda ta, întreabă vânzătorul: el a rezervat coletul la Shouwu și face reclamațiile. Pentru livrarea propriu-zisă, contactează curierul din țara ta.

## Urmărește un colet Shouwu cu Peek

[Peek](/) recunoaște un cod Shouwu după forma sa: `SWECN`, douăsprezece cifre și `YQ`, format pe care nu îl mai folosește niciun alt curier din catalogul său, și interoghează trackerul Shouwu pe baza lui. Nu sunt necesare cont, cod poștal sau număr de telefon.

Peek preia etapa coletului din statusul Shouwu, conform tabelelor de mai sus, și afișează țara de destinație și fiecare scan cu locul său, pe care pagina Shouwu îl omite. Un loc ce conține o cifră, precum un cod poștal, este eliminat. Shouwu nu oferă estimare de livrare, așa că Peek nu afișează o dată estimată.

Fără un curier numit, Peek nu afișează linia „Delivery with” și nu predă urmărirea către curier, nici măcar când istoricul preia liniile iMile. Dacă ai numărul curierului, adaugă-l în Peek ca un colet separat.

Cât timp ai coletul deschis, sau în ziua livrării cu notificările pornite, Peek verifică până la fiecare 10 minute, iar de la ora 6:00 ora locală în ziua în care coletul intră în livrare până la fiecare 2 minute, cât timp răspunde propriul tracker Shouwu; altfel din oră în oră. Prin servicii de urmărire verifică din oră în oră, la fiecare 3 ore după o zi fără noutăți și până la fiecare 15 minute în ziua livrării în aceeași condiție. După două zile fără noutăți (12 ore cât timp coletul încă arată „Label created” sau „Received by carrier”), Peek verifică din oră în oră. Îți poate trimite notificări pentru fiecare scan, doar pașii importanți (livrare, ridicare, vamă, încercări ratate și retururi) sau „Delivery only”. Nu poate contacta Shouwu sau vânzătorul în locul tău.

## Întrebări despre urmărirea Shouwu

### Ce este Shouwu International (守务国际)?

O companie de logistică din Shanghai, 上海守务国际物流有限公司, care expediază colete pentru vânzătorii online transfrontalieri. Afirmă că a început în 2018 ca expeditor aerian de marfă și a trecut în 2019 la rute de comerț electronic spre Europa și SUA.

### Unde pot urmări un colet Shouwu?

În caseta de mai sus sau pe pagina de urmărire în engleză a Shouwu la admin.shshouwu.com. Introdu codul complet `SWECN…YQ`.

### Cine livrează coletele Shouwu în Europa și SUA?

Poșta sau un curier local: paginile de rute Shouwu indică Royal Mail, Yodel și Evri în Regatul Unit, în principal USPS în SUA, iar în UE în principal DHL, Hermes, DPD, Colissimo, Early Bird, UniUni și Poste Italiane (cu iMile observat în Italia). Pentru țări fără rută listată, precum România, Irlanda, Canada sau Australia, întreabă vânzătorul.

### Cât durează livrarea prin Shouwu?

Depinde de ruta aleasă de vânzător. Timpii orientativi ai Shouwu sunt 5–8 zile lucrătoare pe ruta standard spre Regatul Unit, 10–12 pe UK economy, 6–8 pe US standard și 8–12 pe US economy. În UE sunt 4–7 zile lucrătoare spre Germania și Franța, 4–8 spre Țările de Jos și Suedia și 10–12 spre Italia.

### Pe cine contactez pentru un colet Shouwu lipsă?

Vânzătorul de la care ai comandat: el a rezervat expedierea la Shouwu și poate reclama în termen de 30 de zile de la expediere. Shouwu nu publică niciun contact pentru destinatari.

:::sources
- [Shouwu International: 守务国际包裹追踪 (urmărire colete)](https://admin.shshouwu.com/track.html) – tracker în engleză, 40 de numere, statusuri și explicațiile lor, scanări fără locuri, „Copy Link”, „Not Found”
- [Shouwu International: 首页 (pagina principală)](http://www.sedel.com/) – listă contacte, chat QQ, denumirea companiei
- [Shouwu International: 关于我们 (despre noi)](http://www.sedel.com/about-us.html) – 2018 și 2019, parteneri direcți
- [Shouwu International: 主营线路 (rute principale)](http://www.sedel.com/main-route.html) – rute pe țări
- [Shouwu International: 英国标快 (UK standard express)](http://www.sedel.com/main-route-secondary.html?groupId=1&groupItemId=2) – Royal Mail, Yodel, ERVI, alegerea numărului, 5–8 zile lucrătoare, fără retur în China, reclamații
- [Shouwu International: 英国经济 (UK economy)](http://www.sedel.com/main-route-secondary.html?groupId=2&groupItemId=3) – 10–12 zile lucrătoare
- [Shouwu International: 英国带电 (UK, baterii)](http://www.sedel.com/main-route-secondary.html?groupId=3&groupItemId=4) – în principal Royal Mail
- [Shouwu International: 美国标快 (US standard express)](http://www.sedel.com/main-route-secondary.html?groupId=1&groupItemId=1) – în principal USPS, 6–8 zile lucrătoare, fără retur în China, reclamații
- [Shouwu International: 美国经济 (US economy)](http://www.sedel.com/main-route-secondary.html?groupId=2&groupItemId=21) – 8–12 zile lucrătoare
- [Shouwu International: 德国标快 (Germania standard express)](http://www.sedel.com/main-route-secondary.html?groupId=1&groupItemId=19) – în principal DHL, alegerea numărului, 4–7 zile lucrătoare, reclamații
- [Shouwu International: 德国经济 (Germania economy)](http://www.sedel.com/main-route-secondary.html?groupId=2&groupItemId=22) – în principal Hermes, 4–7 zile lucrătoare
- [Shouwu International: 德国带电 (Germania, baterii)](http://www.sedel.com/main-route-secondary.html?groupId=3&groupItemId=25) – în principal DPD, 4–7 zile lucrătoare
- [Shouwu International: 法国标快 (Franța standard express)](http://www.sedel.com/main-route-secondary.html?groupId=1&groupItemId=20) – în principal Colissimo, 4–7 zile lucrătoare
- [Shouwu International: 荷兰标快 (Țările de Jos standard express)](http://www.sedel.com/main-route-secondary.html?groupId=4&groupItemId=28) – în principal DHL, 4–8 zile lucrătoare
- [Shouwu International: 瑞典经济 (Suedia economy)](http://www.sedel.com/main-route-secondary.html?groupId=4&groupItemId=29) – în principal EarlyBird, 4–8 zile lucrătoare
- [Shouwu International: 意大利经济 (Italia economy)](http://www.sedel.com/main-route-secondary.html?groupId=4&groupItemId=27) – UniUni și Poste Italiane, alegerea numărului, 10–12 zile lucrătoare
- [European Commission: EU Customs Reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – taxă vamală de 3 € din 1 iulie 2026, taxă de manipulare de 2 € din 1 noiembrie 2026
- [USPS: Missing Mail and Lost Packages](https://www.usps.com/help/missing-mail.htm) – expeditori sau destinatari, de la 7 zile
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – forma numărului, tracker și link, statusuri, texte de scan și etape (rândurile de vămuire la export și destinație, încărcare în avion și predare citite din 9 noiembrie, ridicarea de la vânzător de către partener, ridicarea FedEx din depozitul din SUA și plecarea navei din China din 14 noiembrie, scanările proprii FedEx și livrarea DHL către firme din 15 noiembrie), liniile iMile preluate în Italia (inclusiv returul), liniile DPD, fusuri orare, locuri, ce afișează Peek
:::
