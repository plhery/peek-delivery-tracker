---
title: Urmărire colet J&T Germania: numărul JTDE și statusurile
description: Urmărește un colet J&T Express în Germania: cum arată numărul JTDE, ce înseamnă fiecare status și ce faci dacă coletul întârzie, se pierde sau nu ajunge.
slug: jt-express-germania-urmarire-colet
published: 2026-11-11
updated: 2026-11-11
---

J&T Express Germany este rețeaua germană a J&T Express: livrează colete în Germania. Dacă un magazin online ți-a trimis comanda cu J&T, numărul de urmărire începe cu `JTDE`: lipește-l în căsuța de mai sus sau caută-l în trackerul J&T, la www.jtexpress.de/tracking. Numărul ți-l dă magazinul: e în emailul de expediere sau pe pagina comenzii. Pagina aceasta nu se bazează pe site-ul J&T Express Germany, ci pe ce știe codul Peek despre trackerul J&T și pe legea germană.

## Numărul de urmărire J&T Germania: cum arată

Numărul de urmărire J&T Express Germany (AWB-ul) e `JTDE` urmat de 12 cifre: 16 caractere în total. Peek nu-i cunoaște altă formă. Cu cifre inventate:

| Număr (inventat) | Ce este |
| --- | --- |
| `JTDE000000000000` | `JTDE` și 12 cifre: un colet J&T Express Germany. Peek recunoaște imediat J&T Express Germany. |
| `JTDE00000000000` | Lipsește o cifră: nu are forma unui număr J&T Express Germany, iar Peek nu recunoaște niciun curier. Verifică dacă n-ai sărit o cifră. |

**Unde îl găsești.** În emailul de expediere de la magazin, pe pagina comenzii din magazin sau de pe marketplace ori în orice mesaj despre livrare. Dacă nu-l găsești, întreabă magazinul.

**Numere care seamănă.** Rețelele J&T din alte țări au trackere și numere proprii: `JTUK` și 12 cifre e [J&T Express UK](carrier:j-and-t-uk), `JTAU` și 12 cifre e [J&T Express Australia](carrier:j-and-t-au), `JTE` și 12 cifre e [J&T Express Middle East](carrier:j-and-t-middle-east), iar `JT` cu 13 cifre îl folosește J&T în Asia de Sud-Est și în China ([J&T Express China](carrier:j-and-t-cn)). Doar `JTDE` ține de rețeaua germană. Dacă Peek arată în continuare „Waiting for the carrier” (adică așteaptă încă prima scanare J&T), compară numărul cu emailul de la magazin sau întreabă magazinul.

## Ce înseamnă statusurile J&T Germania

În trackerul J&T, fiecare scanare e o singură propoziție, cu data și ora, fără loc. Peek cere propozițiile în engleză, așa că pe acestea le vezi în Peek, în ordinea în care apar pe drumul unui colet:

| Ce scrie J&T | Ce înseamnă pentru tine | Ce arată Peek |
| --- | --- | --- |
| `The shipment has been successfully picked up and is being prepared for transportation` | J&T a ridicat coletul de la expeditor. | „Received by carrier” (preluat de curier) |
| `The shipment arrives at the distribution center and is prepared for transport to the next destination.` | A ajuns într-un centru de sortare J&T. | „In transit” (în tranzit) |
| `The shipment is dispatched from the transit center to the next destination` | A plecat din centru spre următorul punct de pe traseu. | „In transit” |
| `Your package has been loaded and is on its way to you` | E în duba curierului: coletul e în livrare. | „Out for delivery” (în livrare) |
| `Delivery failed because the address could not be found` | Curierul nu ți-a găsit adresa. | „Delivery attempted” (încercare de livrare) |
| `Delivery failed, customer refused to accept` | Destinatarul a refuzat coletul. | „Delivery attempted” |
| `Your package has been successfully delivered.` | Livrat. J&T adaugă și cum (vezi mai jos). | „Delivered” (livrat) |
| `Returns have begun` | După o livrare nereușită, coletul se întoarce la expeditor. | „Returned to sender” (returnat la expeditor) |
| `The shipment has been returned` | Returul s-a încheiat. | „Returned to sender” |

O propoziție care nu e în tabel e citită după regulile pe care Peek le folosește pentru toți curierii. Dacă Peek n-o recunoaște nici așa, o arată exact cum a scris-o J&T și o socotește „In transit” (în tranzit). Dacă însă coletul e deja în livrare sau mai departe, statusul rămâne cel de dinainte.

Rândul despre încărcare poate apărea de mai multe ori: J&T îl scrie la fiecare cursă de livrare. Rândul care confirmă livrarea continuă cu modul de livrare între paranteze 【】 și se încheie cu o propoziție despre serviciul clienți. Peek scoate propoziția despre serviciul clienți și păstrează modul de livrare, fără paranteze, doar dacă e unul dintre aceste șase:

- `Left with neighbor`: la un vecin;
- `Front Door Porch`: la ușa de la intrare;
- `Front Desk Reception`: la o recepție;
- `Garden Yard`: în grădină sau în curte;
- `Mailbox`: în cutia poștală;
- `Garage`: în garaj.

Un colet lăsat la un vecin apare deci în Peek ca `Your package has been successfully delivered. The delivery type is Left with neighbor.`

**La ce vecin?** Peek arată doar că coletul a fost lăsat la un vecin: fără nume și fără adresă. Scoate și orice nume sau număr de telefon dintr-un rând J&T.

## Livrarea J&T în Germania: ce-ți spune urmărirea

În istoricele pe care le are codul Peek, curierul J&T încarcă coletul pentru o cursă de livrare și apoi fie îl livrează, fie scrie de ce n-a reușit livrarea. Codul Peek nu arată nicio predare către alt curier: trackerul J&T urmărește coletul până la ușa ta.

**În livrare.** `Your package has been loaded and is on its way to you` înseamnă că coletul e într-o cursă de livrare. În istoricele acelea, rândul apare cu câteva ore înainte de livrare, în aceeași dimineață. Trackerul J&T nu dă un interval orar sau o zi estimată de livrare, așa că nici Peek nu poate arăta așa ceva.

**Refuzat sau adresă negăsită.** După `Delivery failed, customer refused to accept`, în istoricele din codul Peek, curierul a încărcat coletul pentru o nouă cursă a doua zi dimineață. După `Delivery failed because the address could not be found`, verifică adresa din comandă. Dacă lipsește ceva, de exemplu numărul apartamentului sau numele de pe sonerie, anunță imediat magazinul: el a comandat livrarea cu adresa pe care i-ai dat-o. După o livrare nereușită poate urma `Returns have begun`.

**Încercări, locul unde e lăsat coletul, ridicare.** Site-ul J&T nu poate fi folosit ca sursă pentru pagina aceasta, așa că ea nu spune de câte ori încearcă J&T să livreze, dacă poți alege unde e lăsat coletul sau cât păstrează J&T un colet pe care nu l-a putut livra. Dintre rândurile J&T Express Germany pe care le cunoaște codul Peek, niciunul nu trimite coletul la un punct de ridicare sau la un locker, ca să-l iei de acolo. Întreabă magazinul sau uită-te în mesajele despre livrare.

## Colet J&T întârziat, pierdut sau returnat

Magazinul a ales J&T, așa că la o comandă online te adresezi întâi lui. Potrivit legii germane, când cumperi ca persoană fizică de la o firmă, riscul ca marfa să se piardă sau să se strice pe drum îl poartă vânzătorul, cu excepția cazului în care ai angajat tu curierul, fără ca vânzătorul să ți-l fi indicat înainte (§ 475 BGB).

**Nu se mișcă.** Dacă un colet rămâne pe „Received by carrier” sau „In transit” patru zile fără niciun rând nou, Peek îl semnalează cu „No tracking update for four days” (nicio actualizare de patru zile) și îți arată linkul „Get help from J&T Express Germany” (cere ajutor de la J&T Express Germany). Dacă tot nu se mișcă nimic, întreabă magazinul. Mai multe în ghidul despre [trackingul care nu se actualizează](guide:tracking-not-updating) (în engleză).

**Încă nu apare în tracking.** Un număr pe care trackerul J&T nu-l cunoaște încă apare în Peek ca „Waiting for the carrier”. Magazinele creează adesea numărul înainte să predea coletul. Dacă rămâne așa mai multe zile, verifică numărul cu magazinul și întreabă dacă a trimis cu adevărat coletul.

**Livrat, dar nu l-ai primit.** Uită-te în Peek la rândul care confirmă livrarea: dacă numește un loc, ca `Mailbox` sau `Garage`, uită-te acolo; dacă scrie `Left with neighbor`, întreabă-ți vecinii. Tot nu-l găsești? Anunță magazinul, cu numărul de urmărire, și cere banii înapoi sau un colet nou. Vezi și ghidul despre [coletul livrat, dar neprimit](guide:delivered-not-received) (în engleză).

**Pierdut, deteriorat sau returnat.** Cere magazinului banii înapoi sau un produs nou, cu numărul de urmărire. Pagina aceasta nu descrie regulile J&T pentru reclamații.

## Contact J&T Express Germany

La 2 noiembrie 2026, pagina aceasta nu dă niciun număr de telefon, email sau chat pentru J&T Express Germany: Peek publică doar contacte luate de pe paginile oficiale ale curierului, iar site-ul J&T nu poate fi folosit ca sursă pentru pagina aceasta.

Începe cu magazinul: el a comandat livrarea la J&T. La o comandă de pe un marketplace, folosește secțiunea de ajutor de pe pagina comenzii. Trackerul J&T e la www.jtexpress.de/tracking. La un colet care nu se mai mișcă, linkul „Get help from J&T Express Germany” din Peek deschide pagina de contact J&T.

## Urmărirea coletelor J&T Express Germany cu Peek

[Peek](/) e în engleză, dar recunoaște imediat J&T Express Germany după un număr `JTDE` („Detected carrier”, adică curier recunoscut), scris cu sau fără spații, cu litere mari sau mici, și întreabă trackerul J&T doar cu numărul: fără cod poștal și fără cont. Merge și un link www.jtexpress.de/tracking cu `?q=` și numărul; dacă în link e alt fel de număr, Peek îl citește ca și cum l-ai fi lipit singur. Dacă ce ai nu e un număr `JTDE`, lipește-l oricum: Peek urmărește peste 3.500 de curieri. Dacă alegi J&T Express Germany cu „Change” (schimbă curierul) pentru un număr de altă formă, nu te ajută: Peek scrie că J&T Express Germany „doesn’t issue numbers like this one”, adică nu emite numere de felul acesta.

Peek arată statusul, fiecare scanare la ora ei exactă și ora livrării. Pentru fiecare scanare, J&T dă momentul exact, așa că Peek o arată în fusul orar al dispozitivului tău. Scanările apar în engleză, cu formulările J&T. J&T nu dă locul, ziua estimată de livrare sau greutatea, așa că Peek nu arată nicio hartă. La un istoric foarte lung, Peek păstrează cele mai recente 100 de scanări. Pe pagina coletului, „Open the J&T Express Germany website” (deschide site-ul J&T Express Germany) îți deschide numărul în trackerul J&T.

Până când J&T scanează coletul, Peek arată „Waiting for the carrier” și continuă să întrebe. Când trackerul J&T nu poate fi accesat sau nu cunoaște numărul, Peek întreabă servicii de urmărire și îți arată istoricul dat de ele, cu propriile lor formulări.

Peek verifică coletul la fiecare 10 minute și, cât e în livrare, la fiecare 2 minute. Dacă trec două zile fără o scanare nouă (12 ore, cât timp arată „Received by carrier”), îl verifică o dată pe oră. Pentru notificări alegi „Every scan” (fiecare scanare), „Important steps” (pașii importanți) sau „Delivery only” (doar livrarea). Peek nu poate contacta J&T sau magazinul în locul tău, nu poate schimba adresa și nu poate programa o nouă livrare.

## Întrebări despre urmărirea J&T Express în Germania

### Este J&T Express Germany o firmă de încredere?

E un curier real: rețeaua germană a J&T Express, pe care catalogul Peek o înregistrează și sub numele J&T Express Germany GmbH. Trackerul ei răspunde la numerele `JTDE`, iar fiecare propoziție din tabelul cu statusuri de mai sus provine din urmărirea unor colete J&T Express Germany reale. Site-ul J&T nu poate fi folosit ca sursă pentru pagina aceasta, așa că ea nu dă date despre firmă.

### La ce vecin e coletul meu J&T?

Peek nu-ți poate spune. Când rândul care confirmă livrarea scrie `Left with neighbor`, Peek arată asta, fără nume sau adresă. Întreabă-ți vecinii; dacă nu-l are nimeni, anunță magazinul.

### Are J&T Express puncte de ridicare în Germania?

Niciunul pe care să-l cunoască codul Peek. În rândul care confirmă livrarea, Peek arată doar aceste moduri de livrare: la un vecin, la ușa de la intrare, la recepție, în grădină, în cutia poștală sau în garaj. Niciun rând J&T Express Germany cunoscut de Peek nu trimite coletul la un punct de ridicare sau la un locker.

### De ce nu găsește J&T numărul meu de urmărire?

Verifică dacă începe cu `JTDE` și dacă după el urmează 12 cifre: numerele altor rețele J&T, de exemplu `JTUK` din Marea Britanie, nu sunt germane. Dacă numărul e corect, poate că J&T nu are încă coletul. Peek arată „Waiting for the carrier” până la prima scanare; dacă durează mai multe zile, întreabă magazinul.

### Cum contactez J&T Express în Germania?

Întreabă întâi magazinul: el a comandat livrarea. Pagina aceasta nu dă un număr de telefon sau un email, dar la un colet care nu se mai mișcă, linkul „Get help from J&T Express Germany” din Peek deschide pagina de contact J&T.

:::sources
- [Universal Parcel Scraper: J&T Express Germany](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/j-and-t-de) – numele, forma JTDE, linkurile de urmărire, propozițiile trackerului de pe colete reale și etapele lor, cursele de livrare, modurile de livrare, ce lasă Peek deoparte, pagina de ajutor
- [Universal Parcel Scraper: căutarea comună a rețelelor J&T](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/j-and-t-au) – propoziții în engleză, ore exacte ale scanărilor, fără loc, zi estimată sau greutate, nume și numere de telefon scoase, cele mai recente 100 de scanări
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formele numerelor altor rețele J&T, nicio predare către alt curier, serviciile de urmărire ca rezervă
- [Gesetze im Internet: § 475 BGB](https://www.gesetze-im-internet.de/bgb/__475.html) – riscul transportului când un consumator cumpără de la o firmă
:::
