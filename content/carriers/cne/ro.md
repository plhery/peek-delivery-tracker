---
title: Urmărire CNE Express: cod 3A5V, statusuri și contact
description: Urmărește un colet CNE Express (递一物流) din China: formatul 3A5V, statusuri Arrived at destination și vamă, parteneri locali, timp de tranzit și contact.
slug: cne-express-urmarire-colet
published: 2026-11-27
updated: 2026-11-27
---

Urmărește un colet CNE Express (递一物流, CNE EXPRESS CO LTD, fondată în Shanghai în 2003) din China lipind numărul `3A5V` urmat de 9 cifre în caseta de mai sus sau pe [pagina de urmărire în engleză CNE](https://www.cne.com/en/track). CNE expediază comenzi transfrontaliere pentru vânzători de pe eBay, Walmart, TikTok, Temu și SHEIN și predă livrarea finală unor parteneri locali precum USPS, Royal Mail, DHL, La Poste, Evri, GLS sau DPD — verifică `Last mile number` sub `Shipment Information` la CNE sau lasă Peek să preia automat predările recunoscute.

## Numărul de urmărire CNE Express: cum arată

CNE nu publică un format oficial. Forma pe care Peek o recunoaște ca aparținând CNE (cu cifre inventate):

| Număr | Ce reprezintă |
| --- | --- |
| `3A5V482910573` | `3A5V` și 9 cifre: un cod CNE Express. |
| `3A5V48291057` | Doar 8 cifre: nu este formatul CNE. Peek nu numește CNE și, cum nu se potrivește formatului niciunui curier, îl caută la servicii de urmărire după ce îl adaugi. Verifică dacă lipsește o cifră. |

Literele mici, spațiile și cratimele nu contează în Peek. Sistemul CNE acceptă mai multe numere simultan, separate prin spații sau virgule.

**Unde îl găsești.** În e-mailul de expediere de la vânzător sau în pagina comenzii. Dacă nu îl găsești, vezi [cum găsești numărul de urmărire](guide:find-tracking-number) (în engleză) ori ghidurile pentru [comenzi Temu](guide:temu-orders) și [comenzi SHEIN](guide:shein-orders) (în engleză).

**Alte numere.** Sub `Shipment Information`, pagina CNE poate afișa și `Order number` al vânzătorului precum și un `Last mile number`: numărul curierului local pentru coletul tău, alături de site-ul și telefonul acestuia.

**Colete poștale CNE.** CNE vinde și serviciile proprii ale China Post, cum ar fi International E-packet și China Post Packet. Pentru un cod în format China Post, vezi [China Post](carrier:china-post).

## Statusurile de urmărire CNE Express explicate

Sistemul CNE afișează un status sumar deasupra liniilor coletului. În engleză, așa cum le etichetează CNE:

| Statusul CNE | Ce înseamnă pentru tine | Ce afișează Peek |
| --- | --- | --- |
| `Shipping` | Pe drum. | statusul celei mai noi linii |
| `Customs inspection` | Reținut la vamă (`扣关` pe pagina chineză CNE). | „At customs” |
| `Delivered` | Livrat. | „Delivered” |
| `Invalid address`, `Lost`, `Exception`, `Destroyed` | Adresă greșită, colet pierdut sau distrus, ori altă problemă. | „Needs attention” |
| `Returned` | Trimis înapoi la vânzător. | „Returned to sender” |
| `Not delivered` | Pagina chineză CNE scrie `未发送` („netrimis”): coletul nu a plecat încă. | statusul celei mai noi linii |

Pot apărea și `Time out` sau `Unknown`, unde Peek se ghidează după linii. Fiecare linie include ora, formularea CNE și locul. Liniile obișnuite în ordinea traseului:

| Ce scrie CNE | Ce înseamnă pentru tine | Ce afișează Peek |
| --- | --- | --- |
| `Parcel Infomation Received` | Vânzătorul a trimis datele coletului către CNE (greșeala de ortografie este a CNE). CNE nu are încă coletul. | „Label created” |
| `Picked Up by Courier` | CNE a ridicat coletul de la vânzător în China. | „Received by carrier” |
| `Arrived at CNE Facility`, `Inbound scan`, `Shipment Processed`, `Shipment Loaded` | Într-un centru CNE: scanat, sortat, încărcat. | „In transit” |
| `Departed CNE Facility`, `Departure Scan`, `Depart From Local Facility` | A părăsit centrul. | „In transit” |
| `Arrived Export Airport`, `Released From Export Customs` | La aeroportul din China, apoi trecut de vama de export a Chinei. Nu este vama din țara ta. | „In transit” |
| `Shipment Departed From Original Country` | Zborul a plecat din China. | „In transit” |
| `Arrived At Transit Airport`, `Shipment Depart From Transit Airport` | Escală pe traseu. | „In transit” |
| `Arrived at Import Airport`, `Arrived at destination` | A aterizat în țara ta. Urmează sortarea și vama: nu se află încă la oficiul local de livrare. | „In transit” |
| `The shipment is picked up by customs broker` | Un comisionar vamal l-a preluat pentru vămuirea la import. | „At customs” |
| `Released From Import Customs`, `Pickup from airport` | A trecut de vama de import și a fost ridicat din aeroport. | „In transit” |
| `Handled by local carrier` | CNE l-a predat curierului care livrează în țara ta. Urmează recepția acelui curier. | „In transit” |
| `Arrived At The Delivery Company`, `Your shipment has arrived at sorting center`, `Your shipment is in process at sorting center`, `Parcel Unloaded From Container` | La curierul local sau într-un centru de sortare înainte de acesta. | „In transit” |
| `Your shipment has been processed at sorting center and ready for shipping`, `Your shipment has departed from the transit origin`, `Your shipment has arrived at the transit destination` | Sortat în țara ta și trimis la stația locală a curierului înainte de livrare. „Transit” se referă la această ultimă etapă, nu la o escală în străinătate. | „In transit” |
| `On Board with Courier` | În mașina curierului: în curs de livrare. | „Out for delivery” |
| `Your shipment delivery attempt failed [Recipient not at home]` | Curierul nu a găsit pe nimeni acasă. Livrarea se reîncearcă. | „Delivery attempted”, sau „Needs attention” cât statusul CNE este `Exception` |
| `Sorting failed []` | Sortarea a eșuat. Parantezele goale aparțin CNE. | „Needs attention” |
| `It was returned to customer's address by CNE` | CNE l-a returnat vânzătorului, clientul său. | „Returned to sender” |

După predare, în istoricul CNE apar propriile linii ale curierului local, adesea ca o etichetă scurtă urmată de o explicație. Peek citește eticheta, iar pentru formulări necunoscute preia statusul asociat liniei de CNE. Sistemul CNE afișează ore fără fus orar și nu toate folosesc același ceas (vezi întrebările de mai jos).

## Cine livrează coletele CNE Express

CNE operează linii proprii către Europa, SUA, Canada, Australia, Brazilia și alte țări și colaborează cu parteneri locali pentru livrare. Pagina sa de parteneri listează USPS, Royal Mail, Austrian Post, Correos, Poste Italiane, GLS, La Poste, DHL, Evri, DPD și POST Luxembourg printre partenerii globali de logistică.

Peek recunoaște trei dintre codurile pe care CNE le atribuie furnizorilor de ultim kilometru: DHL în Germania ([DHL Paket](carrier:dhl)), [Royal Mail](carrier:royal-mail) în Marea Britanie și [USPS](carrier:usps) în SUA. Când CNE numește unul dintre aceștia și oferă numărul propriu al curierului, cardul din Peek afișează acest lucru (de exemplu „Delivery with USPS”) cu numărul. Cât timp curierul are doar anunțul fără scanare proprie, cardul afișează „Announced to …, not received yet”. Fără acel număr, Peek nu numește un curier local, deoarece acesta urmărește coletul după propriul cod, nu după codul CNE. Alte coduri, precum US SPX, nu indică un curier în Peek.

CNE nu publică reguli privind încercările de livrare, avizele sau punctele de ridicare: se aplică regulile curierului local, precum [Evri](carrier:evri), [La Poste / Colissimo](carrier:la-poste), [Correos](carrier:correos-spain), [Poste Italiane](carrier:poste-italiane), [Austrian Post](carrier:austrian-post) sau [POST Luxembourg](carrier:post-luxembourg).

**Vama în UE.** Bunurile cumpărate online din afara UE au din 1 iulie 2026 o taxă vamală de 3 € per articol pentru colete de până la 150 €, iar din 1 noiembrie 2026 și o taxă de procesare a Uniunii de 2 € per articol indiferent de valoare. Un articol reprezintă bunurile cu aceeași clasificare tarifară, descriere și, unde se cere, origine. În zona TVA a UE se datorează și TVA la import dacă nu a fost perceput la achiziție prin IOSS. CNE și-a anunțat vânzătorii pe 22 octombrie 2026 că pe rutele sale către UE colectează în avans taxa de 2 € de la aceștia începând cu 26 octombrie, pentru fiecare cod tarifar din colet, pe lângă taxa de 3 € percepută similar din iulie, și face vămuirea pe baza declarației vânzătorului. Anunțul nu spune nimic despre TVA sau despre eventuale taxe cerute de curierul local. În afara UE, ce datorezi depinde de țara ta: vezi [colete reținute la vamă](guide:customs) (în engleză).

**Întârzieri.** CNE publică alerte privind întârzieri pe rute (greve, vreme nefavorabilă) pe pagina sa de știri în chineză (`新闻公告`).

## Colet CNE Express care nu se actualizează, pierdut sau returnat

Clienții CNE sunt vânzătorii care comandă transportul: pentru un colet întârziat, pierdut, deteriorat sau returnat, contactează mai întâi vânzătorul sau platforma.

**Nimic găsit încă.** Dacă pagina CNE nu găsește nimic, nu există încă o înregistrare: Peek afișează „Waiting for CNE Express” și continuă verificările. Dacă CNE nu cunoaște codul nici după două zile și niciun serviciu de urmărire nu îl găsește, Peek afișează: „After two days, the carrier still doesn’t know this number. Check it, choose another carrier or delete the parcel.” `Not delivered` înseamnă că nu a fost expediat încă (`未发送`); Peek arată statusul celei mai noi linii, cum ar fi „Label created” pentru `Parcel Infomation Received`. Dacă nimic nu se schimbă câteva zile, verifică codul cu vânzătorul.

**Fără actualizări de zile întregi.** Când un colet aflat pe drum nu are nicio linie nouă timp de patru zile, Peek îl marchează cu data ultimei linii („No tracking update since Mon 12 Oct”) și menționează că coletele rămân adesea fără actualizări pe zboruri lungi, la vamă sau în weekend; după trei săptămâni fără noutăți recomandă să întrebi vânzătorul. Cât timp Peek îl urmărește la CNE, nota include linkul „Get help from CNE Express” către pagina de contact CNE. Vezi [de ce nu se actualizează urmărirea](guide:tracking-not-updating) (în engleză).

**La vamă.** `Customs inspection` (`扣关`) sau `The shipment is picked up by customs broker` înseamnă că vama deține coletul. CNE face vămuirea pe baza datelor vânzătorului, deci întreabă mai întâi vânzătorul dacă rămâne blocat.

**Pierdut, distrus, adresă greșită sau returnat.** Pentru `Lost`, `Destroyed` sau `Invalid address`, anunță imediat vânzătorul și solicită rambursarea sau o nouă expediere. `Returned` sau `It was returned to customer's address by CNE` înseamnă că s-a întors la vânzător: cere magazinului reexpedierea sau rambursarea.

**Livrat, dar nu a ajuns.** Pe pagina CNE, o linie cu `View POD` conține dovada livrării (precum o fotografie), pentru care CNE cere numele destinatarului sau codul poștal. Verifică unde a fost lăsat, întreabă vecinii și apoi contactează vânzătorul. Vezi [marcat livrat, dar neprimit](guide:delivered-not-received) (în engleză).

## Serviciu clienți și contact CNE Express

Pe pagina [Contact us a CNE](https://www.cne.com/en/about/contact):

- **Linie telefonică:** 400-021-5600, număr chinez de servicii (National Service Hotline).
- **Sediul principal Shanghai:** 021-5109 6677, 15/F, Block G, Lane 1777, Hualong Road, Qingpu District, Shanghai.
- **Program:** 08:30 – 18:00, luni–duminică. CNE nu precizează fusul orar; centrele de sortare funcționează non-stop.
- **Formular online:** formularul de consultanță de pe aceeași pagină. Alege `Complaint` ca tip și completează telefonul, e-mailul, numele, numărul de urmărire și mesajul.

CNE nu afișează un e-mail de suport clienți; celelalte birouri din China deservesc vânzătorii. Pentru comanda ta întreabă mai întâi vânzătorul.

## Urmărește un colet CNE Express cu Peek

[Peek](/) recunoaște imediat CNE Express după un cod `3A5V` („Detected carrier”). Un link de urmărire cne.com cu `?no=` încadrează coletul la CNE Express indiferent de format; dacă linkul are mai multe numere, Peek îl preia pe primul. Pentru orice alt cod CNE, lipește-l oricum (Peek urmărește peste 4.500 de curieri). Când forma se potrivește mai multor curieri, Peek afișează „Checking tracking services…” în timp ce verifică; dacă nu se potrivește niciunuia, îl caută după adăugare. Dacă Peek nu selectează CNE, apasă „Change” și alege CNE Express sub „All carriers” (funcționează și căutarea `CNE` sau `递一物流`), ori folosește „Change carrier” pe un colet deja adăugat.

Peek citește direct sistemul CNE: statusul, fiecare linie cu locul ei, țara de destinație și curierul local când CNE numește unul dintre cei trei de mai sus cu codul său. Citește `Arrived at destination` și predarea către curierul local pe ora Beijingului, deduce fusul celorlalte linii după loc sau etapă și afișează fiecare oră în fusul dispozitivului tău. Omite numărul de comandă al vânzătorului, site-ul și telefonul curierului local, precum și dovada livrării CNE. CNE nu oferă dată estimată de livrare sau greutate.

Când CNE indică curierul local și codul acestuia, Peek întreabă și acel curier: unul dintre cei trei de mai sus, poșta țării tale pentru un cod poștal (două litere, nouă cifre, două litere) sau curierul unic indicat de formatul codului. Odată ce acel curier afișează o scanare cel puțin la fel de recentă ca ultima de la CNE, Peek continuă urmărirea acolo păstrând istoricul CNE.

Cât timp ai coletul deschis sau în ziua livrării cu notificările pornite, Peek verifică până la fiecare 10 minute și până la fiecare 2 minute de la ora 6:00 locală în ziua în care CNE îl scanează în curs de livrare, cât timp sistemul CNE răspunde (prin servicii de urmărire până la fiecare 15 minute); altfel la fiecare oră. Când preia alt curier, Peek trece la ritmul lui. După două zile fără noutăți (12 ore în „Label created” sau „Received by carrier”), verifică la oră. Te poate notifica pentru „Every scan”, „Important steps” sau „Delivery only”, și nu poate contacta CNE sau vânzătorul pentru tine.

## Întrebări despre urmărirea CNE Express

### Este CNE Express (递一物流) o firmă reală?

Da. CNE EXPRESS CO LTD (递一国际物流有限公司), fondată în 2003 cu sediul în Shanghai, operează centre de sortare în Jiaxing și Dongguan care, potrivit CNE, pot procesa peste 2 milioane de expedieri pe zi din 2020, și livrează pentru vânzători de pe Amazon, eBay, Walmart, TikTok, Temu și SHEIN.

### Cine livrează coletele CNE Express și unde este codul local?

CNE predă livrarea finală unui partener poștal sau curier local — pagina de parteneri include USPS, Royal Mail, DHL, La Poste, Correos, Poste Italiane, Austrian Post, GLS, DPD, Evri și POST Luxembourg. Caută `Last mile number` sub `Shipment Information` pe pagina CNE (Peek urmărește automat predările către DHL Germania, Royal Mail, USPS și poștele naționale eligibile).

### Ce înseamnă „Arrived at destination” la CNE Express?

Coletul a aterizat în țara ta, nu la oficiul local de livrare — urmează sortarea și vămuirea de import înainte de predarea către curierul local. CNE înregistrează acest scan pe ora Beijingului în timp ce curierul local poate folosi ora locală, astfel încât orele pot părea dezordonate.

### Cât durează livrarea prin CNE Express?

Paginile de servicii CNE indică timpi de referință de 3–8 zile lucrătoare pentru CNE Priority Express (către SUA, Marea Britanie, Franța și Germania) și 5–20 de zile lucrătoare pentru CNE Standard Express; orientează-te după estimarea magazinului.

### Cum contactez CNE Express pentru un colet blocat sau pierdut?

Întreabă mai întâi vânzătorul, deoarece el are contractul cu CNE. Pentru contact direct, sună la linia din China `400-021-5600` sau biroul din Shanghai `021-5109 6677` (08:30–18:00, luni–duminică, fără fus orar precizat) sau trimite formularul de pe [cne.com/en/about/contact](https://www.cne.com/en/about/contact) alegând `Complaint`.

:::sources
- [CNE: Track your parcel](https://www.cne.com/en/track) – tracker, status labels, lines, Shipment Information, View POD
- [CNE: 运单追踪 (tracking, Chinese)](https://www.cne.com/track) – 未发送 and 扣关 behind the English labels
- [CNE: Home](https://www.cne.com/en) – 2003, Shanghai, local partners abroad, destinations, platforms, hotline
- [CNE: 首页 (home, Chinese)](https://www.cne.com/) – 递一物流, 递一国际物流有限公司
- [CNE: About us](https://www.cne.com/en/about/intro) – sorting centres, capacity of over 2 million shipments a day since 2020, platforms
- [CNE: Partner](https://www.cne.com/en/about/partner) – global logistics partners
- [CNE: Contact us](https://www.cne.com/en/about/contact) – hotline, Shanghai office, hours, consulting form
- [CNE: Postal Parcel](https://www.cne.com/en/solutions/economyParcel) – China Post's services sold by CNE
- [CNE: CNE Priority Express](https://www.cne.com/en/solutions/product/36024) – 3 to 8 business days, routes
- [CNE: CNE Standard Express](https://www.cne.com/en/solutions/product/36025) – 5 to 20 business days
- [CNE: 新闻公告 (news and notices)](https://www.cne.com/news/notice) – delay notices by destination
- [CNE: 关于欧盟国家2026年11月税改通知 (EU tax change notice)](https://www.cne.com/news/article/rDe4ANa6KYOW) – €2 collected from sellers in advance from 26 October, on top of the €3, per parcel and tariff code; customs cleared on the seller's declaration
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – €3 duty since 1 July 2026, €2 Union handling fee since 1 November 2026
- [European Commission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – €2 per item whatever the value, what an item is
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – number shape and links, wordings and codes (last-mile sorting, station and missed-delivery lines since 9 November 2026), last-mile suppliers, clocks, hand-over, what Peek reads
:::
