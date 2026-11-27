---
title: Urmărire Meiteshun (MTSCN): cod AMTS…FS și statusuri
description: Urmărește un colet Meiteshun Logistics (MTSCN / 美特顺) din Shenzhen: formatul AMTS…FS, de ce Delivered poate fi planificat dinainte, livrarea locală și contact.
slug: meiteshun-urmarire-colet
published: 2026-11-27
updated: 2026-11-27
---

Meiteshun Logistics (MTSCN, 深圳市美特顺物流有限公司) este un expeditor transfrontalier din Shenzhen, China, ale cărui scrisori de trăsură au formatul `AMTS`, nouă cifre și `FS`. Lipește codul tău `AMTS…FS` în caseta de mai sus pentru a urmări comanda și a filtra pașii scriși în avans: sistemul de urmărire Meiteshun scrie întreaga cronologie a coletului — inclusiv `Delivered` — chiar la crearea comenzii, astfel încât o linie `Delivered` de pe pagina sa nu dovedește singură că coletul a ajuns.

## Numărul de urmărire Meiteshun: cum arată

Codurile Meiteshun constau din `AMTS`, nouă cifre și `FS`. Cu cifre inventate:

| Număr | Ce reprezintă |
| --- | --- |
| `AMTS482910517FS` | `AMTS`, nouă cifre și `FS`: un cod de urmărire Meiteshun. |
| `AMTS48291051FS` | Doar opt cifre: nu corespunde formatului Meiteshun. Verifică dacă lipsește o cifră. |
| `RR482910576CN` | Două litere, nouă cifre și `CN`: un cod [China Post](carrier:china-post), nu Meiteshun. |

Literele mici și spațiile nu contează când lipești codul în Peek.

**Unde îl găsești.** Vânzătorul rezervă expedierea la Meiteshun și îți transmite codul. Dacă nu îl găsești, vezi [cum găsești numărul de urmărire](guide:find-tracking-number) (în engleză).

**Referința magazinului.** Sistemul Meiteshun găsește comanda și după referința proprie a vânzătorului, introdusă exact așa cum ți-a fost trimisă, inclusiv cu cratime, apoi afișează numărul `AMTS`.

**Un link cu cifre în loc de nume de site.** Sistemul Meiteshun rulează pe o adresă de server numerică prin HTTP simplu, nu pe un domeniu web. Un link către acesta conține acea adresă numerică în locul numelui site-ului și include `trackIndex.htm`, `documentCode=` și numărul sau referința ta: îl poți lipi în Peek exact așa cum este.

## Statusurile de urmărire Meiteshun explicate

Fiecare comandă Meiteshun urmează același scenariu, iar majoritatea pașilor sunt scriși în avans (vezi mai jos). Formulările în ordinea scenariului și ce afișează Peek cât timp fiecare este cel mai recent:

| Ce scrie Meiteshun | Ce înseamnă pentru tine | Ce afișează Peek |
| --- | --- | --- |
| `The package has been collected and is ready to be sent to the distribution center` | Primul pas: preluarea de către Meiteshun în Shenzhen. | "Received by carrier" |
| `item inbound in sorting center` | Într-un centru de sortare, cu locul `ShenZhen`. | "In transit" |
| `item outbound in sorting center` | Părăsește acel centru de sortare. | "In transit" |
| `Arriving at the terminal` | La un terminal aeroportuar, cu locul `Hong Kong`. | "In transit" |
| `Leaving the terminal, the airline will take off` | Zborul decolează din Hong Kong. | "In transit" |
| `Arriving at the destination country` | A aterizat în țara de destinație, indicată prin codul de două litere (`US` pentru SUA). | "In transit" |
| `Customs clearanceisin progress` | Vămuire la import în țara ta. Spațiile lipsă aparțin Meiteshun. | "At customs" |
| `Customs clearance completed` | A trecut de vamă și își continuă drumul. | "In transit" |
| `Arriving at the mail processing center` | Într-un centru de procesare poștală din țara ta. | "In transit" |
| `Arrive at the sorting and distribution center` | Într-un centru de sortare din apropierea ta, cu orașul sau regiunea ta. | "In transit" |
| `package is on the way` | Pasul dinaintea livrării. Nu înseamnă că coletul este deja la curier pentru livrare. | "In transit" |
| `Delivered` | Ultimul pas al scenariului (mai jos). | "Delivered", dacă Peek nu reține pasul (mai jos) |

Pentru o comandă fără pași încă, platforma scrie doar `货物电子信息已经收到` („informațiile electronice ale mărfii au fost primite”). Vânzătorul a creat comanda, dar nimeni nu a scanat încă coletul; Peek afișează linia în chineză sub "Label created".

Locul unui pas apare înaintea unei virgule la început, iar pașii din țara ta adaugă orașul sau regiunea și codul poștal după text. Fiecare oră este după ceasul Beijingului, chiar și pentru pașii din țara ta: ora 21:00 pe sistemul Meiteshun înseamnă după-amiaza la Londra și dimineața la New York.

**Pași scriși în avans.** Meiteshun își rulează sistemul pe Hualei (华磊), o platformă de expediere folosită și de alți expeditori chinezi precum [Pengyou](carrier:pengyou). La Meiteshun, toți pașii unei comenzi sunt scriși în momentul creării comenzii, fiecare cu ora planificată, de la preluare până la `Delivered`. Aceștia sunt o prognoză, nu scanări ale coletului tău: arată unde era planificat să fie coletul la o anumită dată. Serviciile de urmărire care copiază înregistrarea Meiteshun afișează aceiași pași.

## Cine livrează coletele Meiteshun

Pașii Meiteshun din țara ta nu numesc compania care face livrarea locală. Numele serviciului din înregistrări este `MTS全球小包` (colet mic global MTS). Pe pagina de urmărire Meiteshun, un cod din coloana `渠道转单号` (număr de transfer) sau din `跟踪号码` (număr de urmărire), atunci când diferă de numărul tău `AMTS`, poate fi codul curierului local: adaugă-l în Peek ca un colet separat.

Peek nu a găsit termene de livrare publicate de Meiteshun și nici reguli pentru încercările de livrare, punctele de ridicare sau durata de păstrare. Întreabă vânzătorul ce companie livrează comanda și cere codul acesteia: urmărirea și regulile acelei companii sunt cele care contează. Dacă ți se cer taxe de import — care în UE includ din 1 noiembrie 2026 și o taxă de procesare a Uniunii de 2 € per tip de produs, indiferent de valoarea coletului — consultă ghidul [colete reținute la vamă](guide:customs) (în engleză).

## Coletul Meiteshun nu ajunge sau urmărirea s-a blocat

**«Delivered», dar nu a venit nimic.** Pasul poate fi prognoza scenariului, nu o scanare reală. Spune-i vânzătorului că coletul nu a ajuns și cere numărul companiei de livrare. Mai multe în [marcat livrat, dar neprimit](guide:delivered-not-received) (în engleză).

**Fiecare pas apare la timp, dar coletul lipsește.** Pașii au fost scriși la crearea comenzii, deci arată planul, nu unde se află coletul. Orientează-te după estimarea de livrare dată de magazin și contactează vânzătorul după ce expiră.

**Blocat în Shenzhen în Peek.** Dacă Peek afișează doar primii pași din Shenzhen, în timp ce un link de la vânzător arată mai mulți, pașii care lipsesc din Peek au fost scriși în avans sau au date ulterioare verificării (mai jos). Un pas scris în avans nu contează niciodată ca scanare: nu schimbă statusul sau data estimată și nu trimite alertă. Până când coletul este livrat sau returnat, Peek deschide istoricul cu "Other sites may show more steps, but they’re only predictions." și listează cu gri pașii reținuți sub "Show them", fiecare cu "Predicted for" și ziua sa. Caută pe pagina Meiteshun compania locală și codul ei; dacă nu există, întreabă vânzătorul. Mai multe în [de ce urmărirea nu se actualizează](guide:tracking-not-updating) (în engleză).

**Nimic găsit.** Pentru un număr necunoscut, sistemul Meiteshun îl arată ca referință cu toate celelalte câmpuri goale. Peek tratează acel răspuns ca negăsit: afișează "Waiting for Meiteshun Logistics", iar după două zile "Check the tracking number". Verifică numărul cu vânzătorul.

**Pierdut, deteriorat sau returnat.** Vânzătorul a rezervat expedierea la Meiteshun, deci cere rambursare sau un colet nou de la vânzător ori de la platforma de cumpărături.

## Contact și relații clienți Meiteshun

Pentru orice problemă legată de comandă, întreabă mai întâi vânzătorul, care a contractat expedierea cu Meiteshun. Dacă nu răspunde, apelează la platformă. Dacă niciuna nu ajută, pagina 17TRACK pentru Meiteshun indică adresa de e-mail `szmeiteshun@163.com`, site-ul `mtswl.com.cn` și un birou în districtul Longgang din Shenzhen, fără număr de telefon (conform verificării din 10 noiembrie 2026): scrie incluzând numărul `AMTS` și numele vânzătorului.

## Urmărește un colet Meiteshun cu Peek

[Peek](/) recunoaște codul Meiteshun după formă: `AMTS`, nouă cifre și `FS`, format pe care niciun alt curier din catalog nu îl folosește. Poți lipi și un link către sistemul Meiteshun care conține `documentCode=` și numărul sau referința vânzătorului. Peek nu poate recunoaște o referință de vânzător după formă: lipește-o totuși (Peek urmărește peste 4.500 de curieri), apasă "Change" și alege Meiteshun Logistics (funcționează și căutarea `美特顺` sau `MTSCN`), ori folosește "Change carrier" pe un colet deja adăugat. Peek va afișa și codul `AMTS` al comenzii. Totuși, Peek elimină cratimele atât la tastare, cât și din linkuri, deci verifică o referință cu cratime direct pe pagina Meiteshun.

Peek citește direct sistemul Meiteshun și arată statusul, fiecare scanare cu locul ei și țara de destinație. Scanările apar așa cum le scrie Meiteshun: `Customs clearanceisin progress` contează ca vamă, iar `package is on the way` ca în tranzit, nu în curs de livrare. Peek păstrează orașul tău din pașii locali și omite codul poștal. Orele sunt citite după ora Beijingului și afișate în fusul orar al dispozitivului tău. Peek omite numele, adresa, greutatea, taxele, semnătura și dovada de livrare pe care le poate conține înregistrarea Meiteshun și nu arată o dată estimată de livrare, deoarece Meiteshun nu oferă una.

Peek reține orice pas scris cu mai mult de douăsprezece ore înainte de ora pe care o afișează, precum și orice pas datat după momentul verificării după ora Beijingului. Se ghidează după pașii păstrați, nu după statusul rezumat al înregistrării. Când întregul scenariu al comenzii a fost scris la crearea ei, Peek păstrează doar pașii datați înainte de acel moment sau la cel mult șase ore după, astfel că `Delivered` scris atunci este reținut. Când Peek trebuie să citească pagina `trackIndex.htm` deoarece interogarea de date nu listează pași, reține fiecare `Delivered` de acolo, întrucât pagina nu arată când a fost scris un pas. O comandă ai cărei pași sunt toți reținuți nu are încă istoric în Peek și afișează "Waiting for Meiteshun Logistics", fără solicitarea de a verifica numărul: Peek consideră pașii scriși în avans drept dovadă că Meiteshun are comanda (la fel ca o expediere fără scanări) și continuă să întrebe sistemul. Fie că reține o parte sau toți pașii, Peek pune deoparte și copiile făcute de alte servicii de urmărire pentru aceștia.

Peek urmărește coletul pe sistemul Meiteshun. Înregistrările Meiteshun nu numesc niciodată curierul care livrează în străinătate, deci Peek nu face preluare automată: adaugă numărul curierului local de la vânzător ca un colet separat.

Cât timp ai coletul deschis, sau în ziua livrării cu notificările pornite, Peek verifică până la o dată la 10 minute și până la o dată la 2 minute în ziua în care apare "Out for delivery" (de la 6:00 ora locală sau cu jumătate de oră înainte de intervalul anunțat), cât timp răspunde sistemul Meiteshun — deși niciun pas cunoscut de la Meiteshun nu dă acest status. În rest verifică la fiecare oră. Prin servicii de urmărire: la fiecare oră, la 3 ore după o zi liniștită și până la 15 minute în ziua livrării în aceleași condiții. După două zile fără noutăți (sau 12 ore cât statusul rămâne "Label created" sau "Received by carrier"), verifică la oră, prag la care o comandă cu scenariu pre-scris ajunge rapid. Te poate notifica pentru fiecare scanare, doar pașii importanți sau "Delivery only", și nu poate contacta Meiteshun sau vânzătorul în numele tău.

## Întrebări despre urmărirea Meiteshun

### Ce este Meiteshun Logistics (MTSCN / 美特顺)?

Este 深圳市美特顺物流有限公司 (Shenzhen Meiteshun Logistics), un expeditor de marfă din Shenzhen, China. Numerele sale de urmărire au formatul `AMTS`, nouă cifre și `FS`, iar înregistrările indică serviciul `MTS全球小包`.

### Un cod de urmărire AMTS…FS provine de la China Post?

Nu. Formatul `AMTS`, nouă cifre și `FS` aparține Meiteshun (opt cifre indică o cifră lipsă). Numerele internaționale China Post au două litere, nouă cifre și `CN`.

### De ce afișează Meiteshun «Delivered» când coletul nu a ajuns?

Sistemul Hualei al Meiteshun scrie întreaga cronologie planificată — inclusiv livrarea — în momentul creării comenzii. Peek reține acești pași prognozați pentru a nu declanșa alerte false; anunță vânzătorul că nu a sosit coletul și cere numărul curierului local.

### Cine livrează coletele Meiteshun (MTSCN) în țara mea?

Pașii Meiteshun nu numesc niciodată compania locală de livrare. Verifică pagina Meiteshun în coloanele `渠道转单号` (număr de transfer) sau `跟踪号码` pentru un al doilea cod, ori întreabă vânzătorul ce companie livrează local și adaugă acel cod în Peek.

### Cum contactez Meiteshun Logistics pentru un colet lipsă?

Începe cu vânzătorul (sau platforma), deoarece el a rezervat expedierea. Dacă niciunul nu răspunde, 17TRACK listează `szmeiteshun@163.com` și site-ul `mtswl.com.cn` (birou în Longgang, Shenzhen, fără telefon); include codul `AMTS` și numele vânzătorului.

:::sources
- [17TRACK: MTSCN tracking](https://www.17track.net/en/carriers/mtscn) – email address, website and Shenzhen office listed for Meiteshun, no phone
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – €2 Union handling fee from 1 November 2026
- [European Commission: Guidance on the 3 EUR customs duty and Union handling fee](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – €2 per kind of product sold at a distance, whatever its value, counted per declaration line (one per kind of product) like the €3 duty
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – company name, Hualei platform, AMTS number shape, the tracker and its links, search by reference, the script and its wordings, places and Beijing clock, steps written in advance, the answer for an unknown number, the platform's Chinese line, service name, no delivery company in the steps, the tracking page's transfer-number and tracking-number columns, what Peek shows, holds back and leaves out
:::
