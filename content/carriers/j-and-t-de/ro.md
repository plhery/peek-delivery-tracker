---
title: Urmărire colet J&T Germania: numărul JTDE și statusurile
description: Urmărește un colet J&T Express în Germania: cum arată numărul JTDE, ce înseamnă fiecare status și ce faci dacă coletul întârzie, se pierde sau nu ajunge.
slug: jt-express-germania-urmarire-colet
published: 2026-11-03
updated: 2026-11-06
---

J&T Express Germany (J&T Express Germany GmbH, Frankfurt pe Main) este rețeaua germană a J&T Express: livrează colete în Germania. Dacă un magazin online ți-a trimis comanda cu J&T, numărul de urmărire începe cu `JTDE`: lipește-l în căsuța de mai sus sau caută-l în [trackerul J&T de pe jtexpress.de](https://www.jtexpress.de/tracking). Numărul ți-l dă magazinul: e în emailul de expediere sau pe pagina comenzii.

## Numărul de urmărire J&T Germania: cum arată

Potrivit FAQ-ului J&T, numărul de urmărire J&T Express Germany (AWB-ul) începe cu JTDE. Peek îi cunoaște o singură formă: `JTDE` urmat de 12 cifre, 16 caractere în total. Cu cifre inventate:

| Număr (inventat) | Ce este |
| --- | --- |
| `JTDE000000000000` | `JTDE` și 12 cifre: un colet J&T Express Germany. Peek recunoaște imediat J&T Express Germany. |
| `JTDE00000000000` | Lipsește o cifră: nu are forma unui număr J&T Express Germany, iar Peek nu recunoaște niciun curier. Verifică dacă n-ai sărit o cifră. |

**Unde îl găsești.** Potrivit J&T, ți-l dă expeditorul și îl găsești pe pagina comenzii din magazinul online, în emailul de confirmare a comenzii, în emailul sau SMS-ul de expediere, în emailurile care anunță sau confirmă livrarea și pe eticheta coletului. Dacă nu-l găsești, J&T te trimite la expeditor sau la serviciul clienți al platformei de pe care ai cumpărat.

Trackerul J&T primește până la 10 numere într-o căutare, despărțite prin spații sau virgule.

**Numere care seamănă.** Rețelele J&T din alte țări au trackere și numere proprii: `JTUK` și 12 cifre e [J&T Express UK](carrier:j-and-t-uk), `JTAU` și 12 cifre e [J&T Express Australia](carrier:j-and-t-au), `JTE` și 12 cifre e [J&T Express Middle East](carrier:j-and-t-middle-east), iar `JT` cu 13 cifre îl folosește J&T în Asia de Sud-Est și în China ([J&T Express China](carrier:j-and-t-cn)). Doar `JTDE` ține de rețeaua germană. Dacă trackerul J&T răspunde `Keine Abfrageergebnisse` (`No tracking records yet` în engleză) sau Peek arată în continuare „Waiting for the carrier” (adică așteaptă încă prima scanare J&T), compară numărul cu emailul de la magazin sau întreabă magazinul.

## Ce înseamnă statusurile J&T Germania

În trackerul J&T, fiecare scanare e o singură propoziție, cu data și ora, fără loc. Site-ul se deschide în germană și nu are versiune în română; dacă alegi engleza sau chineza din meniul de limbă, reîncarcă scanările în limba aleasă. Peek cere propozițiile în engleză, așa că pe acestea le vezi în Peek, în ordinea în care apar pe drumul unui colet:

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

**La ce vecin?** În trackerul J&T, sub scanarea aceea apare `Zustelldetails anzeigen` (`Click to view delivery details` pe site-ul în engleză). Apasă pe el și introdu codul poștal al adresei de livrare: vezi numele vecinului și numărul casei lui. După cinci coduri poștale greșite trebuie să aștepți până să încerci din nou. Peek întreabă trackerul doar cu numărul și arată numai că coletul a fost lăsat la un vecin: fără nume și fără adresă. Scoate și orice nume sau număr de telefon dintr-un rând J&T.

## Livrarea J&T în Germania: ce-ți spune urmărirea

În istoricele pe care le are codul Peek, curierul J&T încarcă coletul pentru o cursă de livrare și apoi fie îl livrează, fie scrie de ce n-a reușit livrarea. Codul Peek nu arată nicio predare către alt curier: trackerul J&T urmărește coletul până la ușa ta.

**În livrare.** `Your package has been loaded and is on its way to you` înseamnă că coletul e într-o cursă de livrare. În istoricele acelea, rândul apare cu câteva ore înainte de livrare, în aceeași dimineață. Trackerul J&T nu dă un interval orar sau o zi estimată de livrare, așa că nici Peek nu poate arăta așa ceva.

**Preferințe de livrare.** Emailul care te anunță despre livrare are un link spre [pagina J&T cu preferințele de livrare](https://www.jtexpress.de/delivery-preference). Confirmă codul poștal și alege unul dintre modurile pe care ți le oferă J&T: cu semnătură, la tine, la un vecin (îi dai numele și spui dacă stă alături sau pe același etaj) sau la o recepție; fără semnătură, la ușa de la intrare, în grădină sau în curte, în cutia poștală sau în garaj. J&T spune că curierul îți respectă alegerea. Dacă alegi un loc fără semnătură, pagina te avertizează că, odată lăsat coletul acolo, riscul de pierdere e al tău. Dacă statusul coletului nu mai permite o alegere, pagina îți spune asta.

**Fotografia de livrare.** Potrivit notei J&T despre protecția datelor la dovada foto de livrare, curierul fotografiază coletul când ți-l predă sau când îl lasă în locul convenit și prinde în cadru doar coletul și cât e nevoie din locul acela; cine apare din greșeală în poză e anonimizat automat. De regulă, J&T șterge fotografia la șase luni după livrare, dar o poate păstra până la încheierea unei reclamații făcute în acest timp și o poate arăta expeditorului pentru a lămuri reclamația.

**Refuzat sau adresă negăsită.** După `Delivery failed, customer refused to accept`, în istoricele din codul Peek, curierul a încărcat coletul pentru o nouă cursă a doua zi dimineață. După `Delivery failed because the address could not be found`, verifică adresa din comandă. Dacă lipsește ceva, de exemplu numărul apartamentului sau numele de pe sonerie, anunță imediat magazinul: el a comandat livrarea cu adresa pe care i-ai dat-o. Și FAQ-ul J&T îți spune să contactezi imediat expeditorul sau platforma: după expediere, dacă adresa se mai poate schimba depinde de unde e coletul și de regulile expeditorului. După o livrare nereușită poate urma `Returns have begun`.

**Încercări și ridicare.** Site-ul J&T nu spune de câte ori încearcă J&T să livreze, cât păstrează un colet sau la ce ore livrează și nu dă nicio listă de puncte de ridicare, lockere sau depozite de unde să-ți iei coletul. Dintre rândurile J&T Express Germany pe care le cunoaște codul Peek, niciunul nu trimite coletul la un punct de ridicare sau la un locker, ca să-l iei de acolo. Întreabă magazinul sau uită-te în mesajele despre livrare.

## Colet J&T întârziat, pierdut sau returnat

Magazinul a ales J&T, așa că la o comandă online te adresezi întâi lui. Potrivit legii germane, când cumperi ca persoană fizică de la o firmă, riscul ca marfa să se piardă sau să se strice pe drum îl poartă vânzătorul, cu excepția cazului în care ai angajat tu curierul, fără ca vânzătorul să ți-l fi indicat înainte (§ 475 BGB).

**Nu se mișcă.** J&T nu publică termene de livrare pentru destinatari. Dacă un colet rămâne pe „Received by carrier” sau „In transit” patru zile fără niciun rând nou, Peek îl semnalează cu „No tracking update for four days” (nicio actualizare de patru zile) și îți arată linkul „Get help from J&T Express Germany” (cere ajutor de la J&T Express Germany). Dacă tot nu se mișcă nimic, întreabă magazinul sau sună la linia telefonică J&T și dă numărul de urmărire. Mai multe în ghidul despre [trackingul care nu se actualizează](guide:tracking-not-updating) (în engleză).

**Încă nu apare în tracking.** Un număr pe care trackerul J&T nu-l cunoaște încă apare în Peek ca „Waiting for the carrier”. Magazinele creează adesea numărul înainte să predea coletul. Dacă rămâne așa mai multe zile, verifică numărul cu magazinul și întreabă dacă a trimis cu adevărat coletul.

**Livrat, dar nu l-ai primit.** Uită-te în Peek la rândul care confirmă livrarea: dacă numește un loc, ca `Mailbox` sau `Garage`, uită-te acolo; dacă scrie `Left with neighbor`, întreabă-ți vecinii sau caută vecinul în trackerul J&T (vezi mai sus). FAQ-ul J&T te sfătuiește să verifici și dacă l-au primit membri ai familiei, vecini sau personalul clădirii. Tot nu-l găsești? Anunță problema la linia telefonică J&T și cere magazinului banii înapoi sau un colet nou, cu numărul de urmărire. Vezi și ghidul despre [coletul livrat, dar neprimit](guide:delivered-not-received) (în engleză).

**Pierdut, deteriorat sau returnat.** Cere magazinului banii înapoi sau un produs nou, cu numărul de urmărire. FAQ-ul J&T nu dă niciun formular de reclamație, termen sau despăgubire pentru destinatari: termenele le stabilește legea germană. Potrivit § 438 din Codul comercial german (HGB), o deteriorare vizibilă o semnalezi cel târziu la livrare (e de ajuns să-i spui curierului), iar una ascunsă în cel mult șapte zile de la livrare, în scris, de exemplu printr-un email către J&T. Dacă nu ajungi la o înțelegere cu J&T pentru un colet pierdut sau deteriorat, te poți adresa organismului de conciliere poștală al Bundesnetzagentur (Schlichtungsstelle Post), care e gratuit și primește cereri și de la destinatari, nu doar de la expeditori. Coletele marilor magazine online circulă însă de obicei în condiții speciale care exclud concilierea; atunci îți rămâne serviciul Bundesnetzagentur pentru consumatorii de servicii poștale (Verbraucherservice Post). Mai multe în ghidurile despre [colete pierdute](guide:lost-parcel), [colete deteriorate](guide:damaged-parcel) și [colete returnate expeditorului](guide:returned-to-sender) (în engleză).

## Contact J&T Express Germany

Canalele J&T Express Germany, așa cum apăreau pe [pagina de contact](https://www.jtexpress.de/contact) la 6 noiembrie 2026:

- **Telefon:** +49 6931090650, de luni până vineri, între 8:00 și 20:00, închis în zilele de sărbătoare legală.
- **Email:** de-jtservice@jtexpress.de.
- **Chat live:** „Chat starten” („Start Chat” pe site-ul în engleză), pe paginile de contact și FAQ.
- **Fotografii de livrare și date personale:** de-dpo@jtexpress.de (din nota despre fotografia de livrare).
- **Birou:** J&T Express Germany GmbH, Am Giener 2, 55268 Nieder-Olm. Mențiunile legale dau adresa Wiesenhüttenstraße 11, 60329 Frankfurt am Main.

J&T nu spune în ce limbi răspunde serviciul clienți; site-ul e în germană, engleză și chineză. La o comandă online, începe totuși cu magazinul: el a comandat livrarea la J&T. La o comandă de pe un marketplace, folosește secțiunea de ajutor de pe pagina comenzii. La un colet care nu se mai mișcă, linkul „Get help from J&T Express Germany” din Peek deschide pagina de contact J&T.

## Urmărirea coletelor J&T Express Germany cu Peek

[Peek](/) e în engleză, dar recunoaște imediat J&T Express Germany după un număr `JTDE` („Detected carrier”, adică curier recunoscut), scris cu sau fără spații, cu litere mari sau mici, și întreabă trackerul J&T doar cu numărul: fără cod poștal și fără cont. Merge și un link www.jtexpress.de/tracking cu `?q=` și numărul; dacă în link e alt fel de număr, Peek îl citește ca și cum l-ai fi lipit singur. Dacă ce ai nu e un număr `JTDE`, lipește-l oricum: Peek urmărește peste 3.500 de curieri. Dacă alegi J&T Express Germany cu „Change” (schimbă curierul) pentru un număr de altă formă, nu te ajută: Peek scrie că J&T Express Germany „doesn’t issue numbers like this one”, adică nu emite numere de felul acesta.

Peek arată statusul, fiecare scanare la ora ei exactă și ora livrării. Pentru fiecare scanare, J&T dă momentul exact, așa că Peek o arată în fusul orar al dispozitivului tău. Scanările apar în engleză, cu formulările J&T, chiar dacă pe jtexpress.de le citești în germană. J&T nu dă locul, ziua estimată de livrare sau greutatea, așa că Peek nu arată nicio hartă. La un istoric foarte lung, Peek păstrează cele mai recente 100 de scanări. Pe pagina coletului, „Open the J&T Express Germany website” (deschide site-ul J&T Express Germany) îți deschide numărul în trackerul J&T.

Până când J&T scanează coletul, Peek arată „Waiting for the carrier” și continuă să întrebe. Când trackerul J&T nu poate fi accesat sau nu cunoaște numărul, Peek întreabă servicii de urmărire și îți arată istoricul dat de ele, cu propriile lor formulări.

Peek verifică coletul la fiecare 10 minute și, cât e în livrare, la fiecare 2 minute. Dacă trec două zile fără o scanare nouă (12 ore, cât timp arată „Received by carrier”), îl verifică o dată pe oră. Pentru notificări alegi „Every scan” (fiecare scanare), „Important steps” (pașii importanți) sau „Delivery only” (doar livrarea). Peek nu poate contacta J&T sau magazinul în locul tău, nu poate schimba adresa și nu poate programa o nouă livrare.

## Întrebări despre urmărirea J&T Express în Germania

### Este J&T Express Germany o firmă de încredere?

Da. J&T Express Germany GmbH, cu sediul la Frankfurt pe Main, e firma germană a J&T Express, un grup de curierat fondat în Indonezia în 2015 și listat la bursa din Hong Kong. Din 1 septembrie 2026 e înscrisă în registrul furnizorilor de servicii poștale al Bundesnetzagentur, autoritatea germană de reglementare în domeniul poștal. J&T caută parteneri în franciză în toată Germania pentru livrări și ridicări pe ultima milă. Fiecare propoziție din tabelul cu statusuri de mai sus provine din urmărirea unor colete J&T Express Germany reale.

### La ce vecin e coletul meu J&T?

Îți spune trackerul J&T: sub scanarea de livrare apasă `Zustelldetails anzeigen` (`Click to view delivery details` în engleză) și introdu codul poștal al adresei de livrare, ca să vezi numele vecinului și numărul casei. Peek arată doar `Left with neighbor`, fără nume sau adresă.

### Are J&T Express puncte de ridicare în Germania?

Site-ul german al J&T nu dă niciunul, iar niciun rând J&T Express Germany cunoscut de Peek nu trimite coletul la un punct de ridicare sau la un locker. Pe pagina cu preferințele de livrare poți cere livrarea la tine, la un vecin, la recepție sau într-un loc de la adresa ta.

### De ce nu găsește J&T numărul meu de urmărire?

Verifică dacă începe cu `JTDE` și dacă după el urmează 12 cifre: numerele altor rețele J&T, de exemplu `JTUK` din Marea Britanie, nu sunt germane. Dacă numărul e corect, poate că J&T nu are încă coletul: trackerul J&T răspunde atunci `Keine Abfrageergebnisse` (`No tracking records yet`), iar Peek arată „Waiting for the carrier” până la prima scanare. Dacă durează mai multe zile, întreabă magazinul.

### Cum contactez J&T Express în Germania?

Sună la +49 6931090650 (de luni până vineri, între 8:00 și 20:00), scrie pe chatul de pe jtexpress.de sau la de-jtservice@jtexpress.de. La o comandă online, întreabă însă întâi magazinul: el a comandat livrarea.

:::sources
- [J&T Express Germany: Track & Trace](https://www.jtexpress.de/tracking) – trackerul, până la 10 numere, limbile site-ului, scanări reîncărcate, răspunsul fără rezultate, datele vecinului după codul poștal, cinci încercări
- [J&T Express Germany: Frequently Asked Questions](https://www.jtexpress.de/faq) – numerele JTDE și unde le găsești, livrat dar neprimit, adresă greșită, chat; fără termene de livrare, încercări sau reguli de reclamație
- [J&T Express Germany: Delivery Preferences](https://www.jtexpress.de/delivery-preference) – linkul din email, verificarea codului poștal, moduri de livrare cu și fără semnătură, riscul la locul ales
- [J&T Express Germany: Contact Us](https://www.jtexpress.de/contact) – telefon, program, chat, email, adresa biroului
- [J&T Express Germany: Legal notice](https://www.jtexpress.de/legal-statement) – J&T Express Germany GmbH, Frankfurt pe Main
- [J&T Express Germany: POD Privacy Policy](https://www.jtexpress.de/pod-policy) – fotografia de livrare, șase luni, reclamații, contact pentru date personale
- [J&T Express Germany: Become Our Partner](https://www.jtexpress.de/career) – parteneri în franciză pentru livrări și ridicări pe ultima milă
- [J&T Express Germany: About Us](https://www.jtexpress.de/about) – fondat în 2015 în Indonezia, listat la Hong Kong
- [Universal Parcel Scraper: J&T Express Germany](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/j-and-t-de) – numele, forma JTDE, linkurile de urmărire, propozițiile trackerului de pe colete reale și etapele lor, cursele de livrare, modurile de livrare, ce lasă Peek deoparte, pagina de ajutor
- [Universal Parcel Scraper: căutarea comună a rețelelor J&T](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/j-and-t-au) – propoziții în engleză, ore exacte ale scanărilor, fără loc, zi estimată sau greutate, nume și numere de telefon scoase, cele mai recente 100 de scanări
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formele numerelor altor rețele J&T, nicio predare către alt curier, serviciile de urmărire ca rezervă
- [Gesetze im Internet: § 475 BGB](https://www.gesetze-im-internet.de/bgb/__475.html) – riscul transportului când un consumator cumpără de la o firmă
- [Gesetze im Internet: § 438 HGB](https://www.gesetze-im-internet.de/hgb/__438.html) – deteriorare semnalată la livrare, deteriorare ascunsă în șapte zile, în scris
- [Bundesnetzagentur: Anbieterverzeichnis Post](https://www.bundesnetzagentur.de/DE/Fachthemen/Post/Anbieterverzeichnis/PostAnbieterverzeichnis.html) – registrul furnizorilor de servicii poștale: J&T Express Germany GmbH, înscrisă la 1 septembrie 2026
- [Bundesnetzagentur: Häufig gestellte Fragen zur Schlichtung Post](https://www.bundesnetzagentur.de/DE/Vportal/Schlichtung/Schlichtung_Post/_FAQ_Schlichtung/faqSchlichtung.html) – cerere și din partea destinatarilor, gratuită, condiții speciale pentru coletele magazinelor online, Verbraucherservice Post
:::
