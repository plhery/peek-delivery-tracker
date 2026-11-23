---
title: Urmărire colet DPD România: AWB, statusuri și Smart Control
description: Urmărire colet DPD România (AWB): formatul de 11 cifre, explicația statusurilor, Smart Control, termene de ridicare la locker și oficiu, taxe și contact.
slug: urmarire-colet-dpd-romania
published: 2026-11-23
updated: 2026-11-23
---

DPD România (*Dynamic Parcel Distribution S.A.*) este compania de curierat a grupului Geopost în România, activă local din 2008, cu sediul central în Mogoșoaia, județul Ilfov. Fiecare colet primește propriul număr de AWB (scrisoare de transport): îl poți verifica pe pagina de urmărire `tracking.dpd.ro`, prin linkul Smart Control primit în SMS-ul de livrare de la DPD sau lipind AWB-ul în caseta de mai sus.

## Numărul de AWB DPD România: cum arată și unde îl găsești

DPD folosește termenul AWB (*waybill number*). Paginile DPD nu publică o regulă rigidă de lungime, însă AWB-urile pe care Peek le asociază cu DPD România au 11 cifre și încep cu `8` (exemple inventate):

| Număr (inventat) | Ce reprezintă |
| --- | --- |
| `84829173605` | 11 cifre care încep cu `8`: un AWB tipic DPD România. Aceeași lungime apare și la [GLS Germania](carrier:gls-de), [GLS Franța](carrier:gls-fr), [GLS Elveția](carrier:gls-ch), [GLS Austria](carrier:gls-at), [PPL](carrier:ppl) și alți curieri. |
| `01248291736051` | 14 cifre: formatul obișnuit al altor filiale DPD, precum [DPD Germania](carrier:dpd-de), [DPD UK](carrier:dpd-uk), [DPD Franța](carrier:dpd-fr) sau [DPD Portugalia](carrier:dpd-pt). Peek urmărește DPD România doar după AWB-ul de 11 cifre. |

Caută numărul în e-mailul de confirmare al magazinului sau în contul tău de client. În aplicația mobilă DPD există un tablou de bord pentru destinatari în care te conectezi cu un cod primit prin SMS sau e-mail. Dacă magazinul menționează [Cargus](carrier:cargus), [FAN Courier](carrier:fan-courier) sau [Sameday](carrier:sameday), numărul aparține acelui curier. Pentru alte cazuri, consultă ghidul [cum găsești numărul de urmărire](guide:find-tracking-number) și [formate de numere de urmărire](guide:tracking-number-formats).

## Ce înseamnă statusurile de urmărire DPD România

Sistemul DPD afișează evenimentele fie în română (într-o formă scurtă mai veche sau ca propoziții complete), fie în engleză. În ordinea obișnuită a livrării:

| Status DPD | Ce înseamnă pentru tine |
| --- | --- |
| `Shipment data received`, `Receptionare informatii colet`, `Expedierea ta a fost înregistrată de către expeditor.` | Expeditorul a generat AWB-ul. DPD are datele electronice, dar nu a preluat încă fizic coletul. |
| `Expedierea nu a putut fi preluată de către curierul DPD.` | Curierul nu a reușit ridicarea de la expeditor; motivul este afișat în continuare. |
| `Courier Pick-up`, `Expedierea ta a fost preluată de la expeditor de către curierul DPD.` | Curierul DPD a ridicat coletul de la expeditor. |
| `Received in Office`, `Receptie la sediu` | Coletul a fost recepționat într-un oficiu DPD, de la curier sau direct de la ghișeu. |
| `Departure Scan`, `Arrival Scan`, `Intrare in depozit`, `Expedierea ta a fost recepționată în depozitul DPD.`, `Expedierea ta a fost procesată în depozitul DPD.` | Coletul a intrat, a fost procesat sau a plecat dintr-un depozit DPD. |
| `Expedierea ta se află în tranzit spre depozitul de destinație.` | Expedierea este în tranzit către depozitul local de livrare. |
| `Expedierea ta a ajuns în depozitul de destinație.` | Coletul a ajuns în depozitul local; urmează ieșirea în livrare. |
| `Predict`, `Predict – s-au trimis informațiile cu privire la intervalul orar de livrare.` | DPD ți-a transmis notificarea cu intervalul orar estimat de livrare (mesaj informativ, nu scanare fizică). |
| `Out for Delivery`, `Iesire in livrare`, `Expedierea ta a fost preluată spre livrare de către curierul DPD.` | Coletul este la curier și pleacă spre adresa ta. |
| `Livrare nereusita`, `Expedierea ta nu a putut fi livrată.` | Livrarea a eșuat; motivul (adresă incorectă, destinatar absent, refuz) apare alături. |
| `Expedierea ta a fost returnată în depozitul DPD.` | Coletul s-a întors în depozit după o încercare nereușită de livrare — nu înseamnă încă retur la expeditor. |
| `Delivered`, `Livrat` | Coletul a fost livrat; trackerul DPD adaugă și numele persoanei care l-a preluat. |
| `Retur catre expeditor` | Expedierea se întoarce la expeditor sub un nou număr AWB de retur menționat în notă. |
| `Retur livrat catre expeditor` | Expeditorul a primit coletul înapoi. |
| `Canceled`, `Expedierea ta a fost anulată.` | AWB-ul a fost anulat; ia legătura cu magazinul. |

Scanarea `Shipment data received` indică adesea data estimată de livrare, iar scanările din depozitele DPD din străinătate afișează denumirea depozitului și țara.

## Livrarea DPD în România: Smart Control, oficii și lockere

- **Termen de livrare și ramburs:** Serviciul [DPD Standard național](https://www.dpd.com/ro/en/parcel-shipping/national-services/) livrează oriunde în România în **1 până la 2 zile lucrătoare**. Sumele ramburs se pot achita curierului în numerar sau, în zonele urbane, cu cardul.
- **Smart Control:** Înainte de livrare, DPD trimite un SMS cu ziua livrării și linkul [Smart Control](https://www.dpd.com/ro/ro/smart-control/) (gratuit). De acolo poți confirma sau modifica adresa, urmări poziția coletului, redirecționa expedierea către un depozit, oficiu DPD, locker sau DPD Shop ori amâna livrarea pentru următoarea zi lucrătoare. Redirecționarea în aceeași localitate este gratuită. Livrarea este fără contact (nu se mai solicită semnătura electronică), iar DPD poate verifica identitatea cu actul de identitate și un cod PIN trimis prin SMS sau e-mail.
- **Încercări de livrare eșuate:** Conform [Termenilor și condițiilor DPD România](https://www.dpd.com/ro/ro/termeni-si-conditii/), DPD face **două încercări de livrare** în zile lucrătoare consecutive. După a doua încercare lasă un aviz și păstrează coletul gratuit timp de **5 zile calendaristice** într-un punct DPD cu personal, interval în care poți stabili o ultimă încercare. Coletele Express și Livrare specială beneficiază de o singură încercare, după care revin la expeditor. DPD nu livrează la cutii poștale. Dacă refuzi coletul sau plata rambursului, returul către expeditor pornește chiar în acea zi, fără perioadă de păstrare.
- **Termene de ridicare din rețeaua [DPD Out of Home](https://www.dpd.com/ro/ro/dpd-ooh/):**
  - Oficiu DPD: **7 zile calendaristice**; plată în numerar, cu cardul sau prin Pay by Link.
  - Locker DPD: **3 zile calendaristice** (72 de ore de la notificare, conform termenilor DPD); lockerele exterioare sunt accesibile 24/7; plată cu cardul sau Pay by Link.
  - DPD Parcel Shop: **5 zile calendaristice**; plată prin Pay by Link sau card (fără card în locațiile Pay Point).

După expirarea termenului de păstrare, coletul este returnat expeditorului în cel mult 5 zile lucrătoare (10 zile lucrătoare pentru colete internaționale). Vezi și ghidurile pentru [colete neridicate](guide:uncollected-parcel) și [livrări ratate](guide:missed-delivery).

## Colet DPD România întârziat, pierdut, deteriorat sau la vamă

Pentru rambursare sau reexpediere, contactează în primul rând magazinul: acesta este titularul contractului cu DPD.

- **Fără actualizări:** Dacă statusul rămâne blocat sau termenul estimat a trecut, contactează `client@dpd.ro` sau `031 824 90 90`. Citește și [de ce nu se actualizează urmărirea](guide:tracking-not-updating).
- **Livrat, dar nu l-ai primit:** Pe `tracking.dpd.ro` apare numele persoanei care a preluat coletul — verifică mai întâi cu ea, apoi contactează DPD și magazinul. Vezi [colet marcat livrat, dar neprimit](guide:delivered-not-received).
- **Retur către expeditor:** Statusul `Retur catre expeditor` indică faptul că pachetul se întoarce la magazin. Solicită magazinului reexpedierea sau rambursarea. Vezi [colet returnat expeditorului](guide:returned-to-sender).
- **Reclamații și despăgubiri:** Conform [mecanismului de soluționare a reclamațiilor DPD](https://www.dpd.com/ro/en/support/tips-and-tricks-dpd-romania/resolution-mechanism-of-complaints/), atât expeditorul cât și destinatarul pot depune o reclamație în termen de **6 luni** de la expediere (prin formularul DPD, pe e-mail la `reclamatii@dpd.ro`, într-un punct DPD cu personal sau prin poștă recomandată). DPD soluționează reclamația în cel mult **3 luni** și achită despăgubirea în **15 zile** de la aprobare. Pentru un colet pierdut sau deteriorat fără valoare declarată și fără ramburs, despăgubirea este de 5 ori tariful serviciului. În termen de **1 an** de la expediere te poți adresa și ANCOM sau instanței. Vezi [colet pierdut](guide:lost-parcel).
- **Taxe vamale și comenzi din afara UE:** Conform [reformei vamale a Comisiei Europene](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en), comenzile online din afara UE cu valoare până la 150 EUR sunt supuse taxei vamale UE de 3 EUR pe categorie de produs (din 1 iulie 2026) și taxei UE de procesare de 2 EUR pe categorie de produs, indiferent de valoare (din 1 noiembrie 2026); paginile DPD România nu precizează încă modul de încasare al acestor taxe, așa că verifică la magazin dacă sunt incluse în preț. În plus, [comunicatul DPD România din 22 decembrie 2025](https://www.dpd.com/ro/en/2025/12/22/25-ron-fee-for-non-eu-parcels-under-eur-150-2/) explică taxa națională fixă de **25 RON** pe colet non-UE declarat sub 150 EUR (introdusă la 1 ianuarie 2026), achitată de vânzător, expeditor sau marketplace și colectată de DPD de la aceștia. Vezi și [colete blocate la vamă](guide:customs).

## Contact și relații clienți DPD România

Peek nu poate contacta DPD sau magazinul în numele tău. Conform [paginii de contact DPD România](https://www.dpd.com/ro/en/support/contact-dpd/):

- **Colete naționale:** telefon `031 824 90 90`, luni–vineri, `08:30–16:30`; solicitări prin e-mail la `client@dpd.ro`.
- **Colete internaționale:** telefon `031 824 90 32` sau e-mail `international@dpd.ro`, același program.
- **Reclamații:** `reclamatii@dpd.ro` sau formularul online de reclamații.
- **Adresă poștală:** *Dynamic Parcel Distribution S.A.*, Str. Buiacului nr. 2, LogIQ Logistic Park, Corp A (A1-A2), 077135 Mogoșoaia, Ilfov.

> DPD România avertizează în [campania sa anti-phishing](https://www.dpd.com/ro/ro/2025/08/01/campanie-de-informare-phishing/) asupra SMS-urilor și e-mailurilor false care trimit către site-uri clonă ce cer datele cardului bancar sau plăți suplimentare. DPD România nu solicită niciodată date bancare prin SMS sau e-mail. Nu accesa linkul, marchează mesajul ca spam și anunță DPD. Vezi [mesaje false despre livrări](guide:delivery-scam-texts).

## Urmărirea unui colet DPD România cu Peek

Lipește AWB-ul de 11 cifre (`84829173605`, inventat) în [Peek](/). Și alți curieri folosesc numere de 11 cifre, așa că Peek îi întreabă pe cei mai probabili, după țara ta (regiunea telefonului sau, pe web, locul de unde te conectezi). Din România întreabă prima dată DPD România și afișează „DPD Romania has this parcel.” când DPD cunoaște AWB-ul. Din alte țări întreabă mai întâi ceilalți curieri și serviciile de urmărire, apoi DPD România, și îl numește când DPD cunoaște AWB-ul. Dacă vezi „not found yet · Peek keeps checking”, apasă „Change” și alege DPD România sau folosește „Change carrier” pe un colet deja adăugat. Lipește-l oricum: Peek urmărește peste 4.500 de curieri. Funcționează și linkurile de pe `tracking.dpd.ro` sau `services.dpd.ro/tracking` cu `shipmentNumber=` și de pe `dpd.com/ro/` sau `dpdgroup.com/ro/` cu `parcelNumber=`.

Peek interoghează trackerul DPD și afișează etapa („Label created”, „Received by carrier”, „In transit”, „Out for delivery”, „Delivery attempted”, „Delivered”, „Returned to sender”), istoricul scanărilor cu localitatea și țara, data estimată de livrare cât coletul este pe drum, cu intervalul orar când mesajul Predict al DPD îl indică, și ora livrării. Peek nu afișează numele persoanei care a preluat coletul, semnătura și AWB-ul de retur; pentru acestea folosește „Open the DPD Romania website”.

Când DPD predă un colet către [ACS Courier](carrier:acs-courier) pentru ultima etapă (de exemplu spre Cipru) și indică un cod ACS de 10 cifre, Peek interoghează și ACS și preia urmărirea acolo imediat ce ACS are o scanare cel puțin la fel de recentă ca DPD.

Cât timp coletul e deschis în Peek sau în ziua livrării cu notificările pornite, Peek îl verifică până la o dată la 10 minute, iar în ziua în care DPD îl scanează în livrare, la fiecare 2 minute: de la ora 06:00, ora României, sau, când mesajul Predict al DPD dă un interval, de la o jumătate de oră înainte de începutul lui, oricare e mai târziu. După două zile fără noutăți (12 ore cât coletul arată încă „Label created” sau „Received by carrier”), Peek verifică din oră în oră, iar după șapte zile fără noutăți la „Label created”, la fiecare 6 ore. Când cea mai nouă scanare are patru zile, iar coletul e încă la „Label created”, „Received by carrier” sau „In transit”, Peek îl marchează cu ziua acelei scanări, de exemplu „No tracking update since Mon 12 Oct”, și spune „Parcels often go quiet on long flights, at customs or over a weekend. Peek keeps asking DPD Romania.”, cu linkul „Get help from DPD Romania” către [pagina de contact DPD România](https://www.dpd.com/ro/ro/suport-dpd/contact/). După trei săptămâni fără o scanare nouă, spune „Nothing new for over three weeks. If it still hasn’t arrived, ask the seller. Peek keeps asking DPD Romania.”

Poți alege notificări pentru „Every scan”, „Important steps” sau „Delivery only”. Pentru redirecționarea sau reprogramarea livrării folosește linkul DPD Smart Control; Peek doar monitorizează statusurile.

## Întrebări frecvente despre DPD România

### În cât timp livrează DPD în România?

Serviciul DPD Standard livrează în **1–2 zile lucrătoare** la nivel național. Pentru localitățile cu zile fixe de vizită, termenii DPD prevăd până la 7 zile calendaristice.

### Cât timp păstrează DPD un colet la oficiu sau locker?

**7 zile calendaristice** la oficiul DPD, **5 zile** la DPD Parcel Shop, **3 zile** (72 de ore) la locker DPD și **5 zile** într-un punct DPD cu personal după două încercări eșuate de livrare. După aceea, coletul se întoarce la expeditor.

### Cum redirecționez un colet DPD România către un locker sau altă adresă?

Accesează linkul gratuit [Smart Control](https://www.dpd.com/ro/ro/smart-control/) din SMS-ul primit de la DPD înainte de livrare. Poți alege un locker, un oficiu, un Parcel Shop, o altă adresă din aceeași localitate sau amânarea livrării pentru ziua lucrătoare următoare.

### Care este numărul de telefon DPD România?

Pentru colete naționale sună la `031 824 90 90`, iar pentru colete internaționale la `031 824 90 32`, de luni până vineri între `08:30` și `16:30`.

:::sources
- [DPD România: Pagina principală](https://www.dpd.com/ro/ro/) – opțiuni Smart Control și servicii de livrare
- [DPD România: Smart Control](https://www.dpd.com/ro/ro/smart-control/) – SMS de notificare, redirecționare gratuită și livrare fără contact
- [DPD România: DPD Out of Home](https://www.dpd.com/ro/ro/dpd-ooh/) – termene de ridicare și metode de plată la oficiu, locker și Parcel Shop
- [DPD România: Termeni și condiții](https://www.dpd.com/ro/ro/termeni-si-conditii/) – reguli AWB, PIN, încercări de livrare, păstrare, refuz, retur și ANCOM
- [DPD România: Campanie de informare phishing](https://www.dpd.com/ro/ro/2025/08/01/campanie-de-informare-phishing/) – avertismente privind SMS-urile și e-mailurile false
- [DPD România: Contact](https://www.dpd.com/ro/en/support/contact-dpd/) – numere de telefon, adrese de e-mail, program și sediu
- [DPD România: National services](https://www.dpd.com/ro/en/parcel-shipping/national-services/) – termen de livrare 1–2 zile lucrătoare și plata cu cardul
- [DPD România: Resolution mechanism of complaints](https://www.dpd.com/ro/en/support/tips-and-tricks-dpd-romania/resolution-mechanism-of-complaints/) – termene de reclamație și despăgubire
- [DPD România: 25 RON fee for non-EU parcels under EUR 150](https://www.dpd.com/ro/en/2025/12/22/25-ron-fee-for-non-eu-parcels-under-eur-150-2/) – taxa națională de 25 RON pentru colete din afara UE sub 150 EUR
- [Comisia Europeană: Reforma vamală a UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – taxa vamală de 3 EUR (sub 150 EUR) și taxa de procesare de 2 EUR
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – ce citește Peek din DPD România, formulările de status și predarea către ACS Courier
:::
