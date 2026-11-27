---
title: Urmărire Cyprus Post: coduri CY, statusuri și taxe vamale
description: Urmărește un colet sau o trimitere recomandată Cyprus Post: coduri S10 de 13 caractere cu CY, statusuri UPU, taxe vamale UE de 3 € + 2 € și OCECPR.
slug: cyprus-post-urmarire-colet
published: 2026-11-27
updated: 2026-11-27
---

Urmărește un colet sau o trimitere recomandată Cyprus Post pe cypruspost.post sau în caseta de mai sus folosind codul poștal S10 din 13 caractere (terminat în `CY` sau codul de țară al poștei străine). Cyprus Post (Κυπριακά Ταχυδρομεία), Departamentul Serviciilor Poștale, este operatorul poștal național desemnat al Republicii Cipru; dacă nu ai codul, vezi [unde găsești numărul de urmărire](guide:find-tracking-number) (în engleză).

## Numărul de urmărire Cyprus Post

Sistemul de urmărire Cyprus Post acceptă codurile din 13 caractere ale standardului S10 al Uniunii Poștale Universale, atribuite trimiterilor recomandate, EMS, coletelor și scrisorilor urmărite: două litere, nouă cifre și două litere.

:::anatomy RR 482917367 CY
- RR | Indicator de serviciu: prima literă arată tipul trimiterii
- 482917367 | Număr de serie de opt cifre, urmat de o cifră de control
- CY | Cod de țară: CY pentru un număr emis sub autoritatea Ciprului
:::

| Prima literă (exemple inventate) | Ce reprezintă |
| --- | --- |
| `RR482917367CY` | `R`: trimitere recomandată |
| `CP482917367CY` | `C`: colet poștal |
| `EE482917367CY` | `E`: trimitere EMS |
| `LA482917367CY` | `L`: scrisoare urmărită |
| `UA482917367CY` | `U`: trimitere poștală de corespondență cu bunuri, fără urmărire pentru client |

O trimitere expediată din străinătate păstrează codul dat de poșta din țara respectivă, terminat în codul acelei țări (`…GB`, `…FR`, `…GR`), iar pagina Cyprus Post îl acceptă de asemenea, incluzând scanările poștei străine. Mesajul `There is no information for this item` înseamnă că sistemul nu are încă numărul: verifică-l cu expeditorul.

## Ce înseamnă statusurile de urmărire Cyprus Post

În versiunea în engleză a sistemului Cyprus Post, pe care o citește Peek, fiecare scanare afișează ora, țara, locația, evenimentul, oficiul următor și informații suplimentare. Evenimentele sunt denumirile internaționale poștale (UPU): `(Otb)` marchează o etapă în țara de expediere, iar `(Inb)` una în țara de destinație. Oficiul următor indică unde merge trimiterea mai departe, nu unde se află acum. Pentru o trimitere care vine spre tine:

| Status Cyprus Post | Ce înseamnă pentru tine |
| --- | --- |
| `Receive item from customer (Otb)` | Trimiterea a fost depusă la poștă, în Cipru sau în străinătate. |
| `Insert item into bag (Otb)` | Pusă în sac la biroul de schimb pentru expediere în străinătate. |
| `Receive item at office of exchange (Inb)` | A ajuns în țara de destinație. |
| `Send items to delivery point/ collection point` | În drum spre oficiul care o va livra sau o va păstra pentru ridicare. |
| `Receive item at delivery office (Inb)` | La oficiul responsabil cu livrarea. |
| `Hold item at point of delivery (Inb)` | La oficiul de livrare, așteptând ieșirea în distribuire. |
| `Handover item to delivery agent (Inb)` | La factorul poștal. |
| `Item not delivered/ Pending delivery` | Nelivrată: coloana Extra Information indică motivul. |
| `Receive item at collection point for pick-up (Inb)` | Gata de ridicare de la oficiul poștal. |
| `Deliver item (Inb)` | Livrată sau ridicată de la oficiul poștal. |

## Taxe vamale pentru bunuri din afara UE

Începând cu 1 iulie 2026, bunurile cumpărate online din afara UE — inclusiv trimiterile poștale — nu mai intră scutite de taxe vamale când valoarea expediției este de 150 € sau mai puțin: UE aplică o taxă vamală temporară de 3 € per articol. Un articol cuprinde toate bunurile din expediție care au aceeași clasificare tarifară, descriere și, unde se declară, origine, indiferent de numărul de bucăți. Din 1 noiembrie 2026, UE percepe și o taxă de procesare a Uniunii de 2 € per articol pentru bunurile cumpărate online din afara UE, indiferent de valoare, iar asupra acestei taxe se aplică TVA. Plata revine în primul rând celui care declară bunurile la vamă: platforma, vânzătorul, transportatorul sau agentul. În Cipru, Departamentul de Vamă și Accize aplică taxele vamale de import și TVA pentru bunurile din afara UE. Mai multe în [colete reținute la vamă](guide:customs) (în engleză).

## Colet Cyprus Post întârziat sau neprimit

Dacă urmărirea s-a oprit, vezi [urmărire care nu se actualizează](guide:tracking-not-updating) (în engleză). Dacă apare ca livrat, dar nu îl ai, verifică codul cu expeditorul și consultă [marcat livrat, dar neprimit](guide:delivered-not-received) (în engleză). Pentru colete pierdute sau deteriorate, vezi [colete pierdute](guide:lost-parcel) (în engleză).

**Ai cumpărat online și încă aștepți?** Serviciul pentru Protecția Consumatorilor din Cipru arată că, de regulă, comerciantul trebuie să livreze în 30 de zile dacă nu ați convenit altă dată. Dacă nu livrează, notifică comerciantul și acordă-i un termen suplimentar rezonabil; dacă tot nu livrează, poți rezilia contractul. Linia pentru consumatori `1429` este gratuită.

**Un mesaj îți cere să plătești pentru eliberarea unui colet?** Citește despre [SMS-urile false de livrare](guide:delivery-scam-texts) (în engleză) înainte de a accesa orice link.

## Urmărirea coletelor Cyprus Post cu Peek

Pentru un cod poștal terminat în `CY` cu cifră de control corectă, [Peek](/) verifică Cyprus Post și [Chronopost](carrier:chronopost) și afișează "Cyprus Post has this parcel." când Cyprus Post cunoaște numărul, sau "Not found at … yet. Peek will keep checking after you add it." Cu o cifră de control greșită, Peek nu interoghează Cyprus Post nici dacă o alegi cu "Change": verifică numărul cu expeditorul. Un link cypruspost.post funcționează atunci când conține numărul.

Pentru o trimitere expediată din străinătate, Peek interoghează mai întâi poșta care a emis codul acolo unde poate — cum ar fi [ELTA](carrier:elta) pentru `…GR` — sau servicii de urmărire. Sistemul Cyprus Post acceptă și aceste numere: alege "Cyprus Post" prin "Change". Peek încearcă singur Cyprus Post atunci când poșta străină indică destinația Cipru sau, dacă nu precizează destinația, când îl adaugi din Cipru, și comută de îndată ce Cyprus Post are scanări la fel de recente ca poșta străină. Peek întreabă Cyprus Post doar despre numere de 13 caractere. Nu ești sigur de curier? Lipește numărul oricum: Peek urmărește peste 4.500 de curieri.

Peek afișează statusurile Cyprus Post așa cum sunt scrise, oficiul sau centrul și țara fiecărei scanări pe hartă, ora livrării și oficiul poștal unde trimiterea așteaptă ori a fost ridicată. Scanările cipriote păstrează ora exactă, iar cele ale unei poște străine sunt citite după ceasul țării respective; unde o țară are mai multe fusuri orare, Peek îl deduce din locul menționat, ceea ce poate decala ora. Cât timp trimiterea este deschisă în Peek, sau în ziua livrării cu notificările active, Peek interoghează direct Cyprus Post până la fiecare 10 minute, ori la 2 minute de la ora 6:00 ora Ciprului în ziua în care iese la factorul poștal, cât timp sistemul Cyprus Post răspunde, și te poate notifica la schimbarea statusului. După două zile fără noutăți (12 ore cât timp este la "Label created" sau "Received by carrier"), verifică la fiecare oră. După patru zile fără scanare nouă la "Label created", "Received by carrier" sau "In transit", Peek arată data ultimei scanări ("No tracking update since …") cu nota "Parcels often go quiet on long flights, at customs or over a weekend. Peek keeps asking Cyprus Post." și linkul "Get help from Cyprus Post". Nu afișează livrarea estimată sau greutatea și omite Extra Information, oficiul următor, codurile poștale și cine a semnat: pentru motivul nelivrării consultă direct cypruspost.post.

## Întrebări despre urmărirea Cyprus Post

### Cum urmăresc un colet internațional cu Cyprus Post?

Introdu codul poștal S10 de 13 caractere (două litere, nouă cifre și `CY`, sau codul de țară al poștei de expediere) pe cypruspost.post ori în Peek. În istoricul în engleză, `(Otb)` indică evenimente din țara de origine, iar `(Inb)` evenimente din țara de destinație.

### Este Cyprus Post același lucru cu Department of Postal Services?

Da. Cyprus Post este Departamentul Serviciilor Poștale al Republicii Cipru, listat de Uniunea Poștală Universală ca operator desemnat și numit de OCECPR furnizor de serviciu poștal universal.

### Ce înseamnă CY la finalul numărului?

Indică faptul că numărul S10 din 13 caractere a fost emis sub autoritatea Ciprului conform standardului UPU S10 (ultimele două litere arată țara emitentă, nu neapărat locul depunerii). Peek interoghează Cyprus Post pentru codurile `CY`.

### Pot urmări o trimitere cu cod care începe cu U?

Nu ca trimitere urmărită pentru client. Conform standardului UPU S10, prefixul `U` desemnează trimiteri de corespondență cu bunuri care circulă fără urmărire pentru destinatar, deși Peek întreabă totuși Cyprus Post pentru codurile `U…CY`.

### Cum depun o reclamație privind Cyprus Post?

Scrie mai întâi către Cyprus Post. Dacă nu răspunde în 15 zile sau nu ești mulțumit de răspuns, poți depune o reclamație online (cu formular disponibil și în engleză) la autoritatea de reglementare poștală OCECPR.

:::sources
- [Universal Postal Union: S10, identification of postal items](https://www.upu.int/UPU/media/upu/files/postalSolutions/programmesAndServices/standards/S10-12.pdf) – 13 characters, first letters R, C, E, L and U, check digit, issuing country's code
- [Universal Postal Union: member countries, Cyprus](https://www.upu.int/en/Universal-Postal-Union/About-UPU/Member-Countries?csid=-1&cid=86) – Cyprus Post, designated operator
- [Universal Postal Union: list of designated operators](https://www.upu.int/getmedia/0ff98b1d-9dd4-46e7-bdd1-dda1b654308f/SettlementCircularsEn.pdf) – Department of Postal Services (Cyprus Post)
- [National Open Data Portal: list of post offices](https://www.data.gov.cy/el/dataset/katalogos-kai-theseis-tahydromikon-grafeion) – Department of Postal Services, the state service in charge of postal services (in Greek)
- [OCECPR: universal postal service](https://ocecpr.ee.cy/katanalotes/katholiki-ypiresia/taxidromeia) – Cyprus Post as universal postal service provider (in Greek)
- [OCECPR: complaints](https://ocecpr.ee.cy/katanalotes/parapona) – provider first, in writing; 15 days; online complaint; complaint form in English (in Greek)
- [Consumer Protection Service: online purchases](https://consumer.gov.cy/en/consumer-rights/66/59/?ctype=ar) – delivery within 30 days, extra period, ending the contract (in Greek)
- [Consumer Protection Service: contact us](https://consumer.gov.cy/en/contact-us) – free Consumer Line 1429 (in Greek)
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – €3 duty per item since 1 July 2026, €2 Union handling fee since 1 November 2026
- [European Commission: guidance on the EUR 3 customs duty and the Union handling fee](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – what an item is, postal consignments, the fee on any value, who pays, VAT on the fee
- [Department of Customs and Excise: About us](https://www.mof.gov.cy/mof/customs/customs.nsf/All/289500205CFF33DFC22572AC003A2B02?OpenDocument) – imposes import duty and VAT on imported goods
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – what Peek reads: Cyprus Post's English tracker, its columns and event names
:::
