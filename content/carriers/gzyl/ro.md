---
title: Urmărire colet GZYL: cod YL…YQ, statusuri, vamă și contact
description: Urmărește un colet GZYL (广州云链, Guangzhou Yunlian) din China: format YL…YQ, statusuri, de ce apare Delivered din timp, taxe vamale UE și contact.
slug: gzyl-urmarire-colet
published: 2026-11-27
updated: 2026-11-27
---

GZYL (广州云链, Guangzhou Yunlian, 广州云链货运代理有限公司) este un expeditor din Guangzhou, China, ale cărui coduri de urmărire au forma `YL`, zece cifre și `YQ`. Lipește codul tău `YL…YQ` în caseta de mai sus ca Peek să filtreze predicțiile scrise dinainte: trackerul GZYL (deschis din `小包查询` pe site-ul în chineză `yunlianhouyun.com`, pe o adresă IP fără domeniu) poate scrie din timp întregul traseu al coletului, așa că o linie `Delivered` acolo nu dovedește singură livrarea.

## Codul de urmărire GZYL: cum arată

Codurile de AWB ale GZYL au `YL`, zece cifre și `YQ` — 14 caractere în total. Exemple cu cifre inventate:

| Cod | Ce reprezintă |
| --- | --- |
| `YL4829105736YQ` | `YL`, zece cifre și `YQ`: formatul GZYL. Peek îl verifică pe trackerul GZYL. |
| `YL7384829105YQ` | `YL738`, șapte cifre și `YQ`: din această formă Peek recunoaște GZYL imediat. |
| `YL482910573YQ` | Doar nouă cifre: nu este formatul GZYL. Verifică dacă lipsește o cifră. |
| `GZXH4829105736YQ` | `GZXH`, zece cifre și `YQ`: [Xinghe Freight](carrier:xinghe-freight) (星和货运), alt expeditor pe același tracker GZYL. |
| `JXCAA4829105736YQ` | Se termină tot în `YQ`, ca mulți expeditori chinezi, dar începe cu `JXC`: cod [Juxi Express](carrier:juxi-express). |

Spațiile și literele mici nu contează când lipești codul în Peek.

**Unde îl găsești.** Vânzătorul rezervă transportul cu GZYL și îți trimite codul în confirmarea de expediere sau pe pagina comenzii. Dacă nu îl găsești, cere-l vânzătorului.

## Ce înseamnă statusurile GZYL

GZYL își scrie pașii în engleză, cu greșelile de scriere cu tot. Deoarece interfața Peek nu este în română, etapele Peek apar și ele în engleză: «Label created» (etichetă creată), «Received by carrier» (primit de curier), «In transit» (în tranzit), «At customs» (la vamă) și «Delivered» (livrat). În ordinea obișnuită:

| GZYL scrie | Ce înseamnă pentru tine | Peek afișează |
| --- | --- | --- |
| `Order information received` | Vânzătorul a trimis datele comenzii la GZYL. GZYL nu are încă fizic coletul. | «Label created» |
| `Arrive at the cross border receiving point` | GZYL a preluat coletul: prima scanare reală în mâinile sale. | «Received by carrier» |
| `Goods leave the operation center` | Coletul a părăsit centrul operațional al GZYL. | «In transit» |
| `Arrive at the international airport` | La aeroport, așteptând zborul. | «In transit» |
| `Departing from the airport` | Avionul a decolat. | «In transit» |
| `Item arrives at destination airport` | A aterizat în țara de destinație. | «In transit» |
| `local carrier Agent Import customs clearance` | Un agent din țara ta efectuează vămuirea de import. | «At customs» |
| `The import customs keep it for inspection` | Vama din țara ta inspectează coletul. | «At customs» |
| `Customs clearance completed` | Vămuit și pornit mai departe. | «In transit» |
| `Internationaliy Package received at processing center` | Un centru de procesare din țara ta a primit coletul vămuit. Nu este o preluare nouă. | «In transit» |
| `Leave the email processing center` | Coletul a plecat din acel centru de procesare. | «In transit» |
| `Arrival sorting, distribution center` | La un centru de distribuție din țara ta, cu orașul după text. | «In transit» |
| `Leaving the sorting center` | Pleacă dintr-un centru de sortare, cu orașul după text. | «In transit» |
| `Package is on the way` | În drum prin țara ta, cu un oraș. Nu înseamnă că este ieșit cu curierul la livrare. | «In transit» |
| `Delivered` | Livrare, cu orașul. Poate fi un plan, nu o scanare: vezi mai jos. | «Delivered» cu notă despre programul fix, dacă Peek nu reține pasul (vezi mai jos) |

**Prin Statele Unite.** Poate apărea și `Los Angeles Processing Center has received it` urmat de un oraș în paranteze: un centru de procesare din SUA a preluat coletul în drum spre destinație («In transit»).

**O linie în chineză.** Pentru o comandă fără scanări încă, platforma scrie automat `货物电子信息已经收到` (informațiile electronice ale mărfii au fost primite). Vânzătorul a creat comanda, dar nimeni nu a scanat coletul («Label created»).

**Prin China Post.** Coletul poate arăta și `广州处理中心已收寄` cu orașul în paranteze: acceptarea China Post la Guangzhou (la codurile Xinghe Freight, `香港处理中心已收寄` este aceeași acceptare la Hong Kong). Peek le arată în chineză sub «Received by carrier».

**Orele.** Fiecare oră din trackerul GZYL este la ora Beijingului (`UTC+8`), inclusiv pentru pașii din România, fără fus orar afișat. Iarna, 20:00 la Beijing înseamnă 14:00 ora României (`UTC+2`), iar vara 15:00 (`UTC+3`).

**Pași scriși dinainte.** GZYL poate scrie în avans întreaga cronologie a unui colet, de la preluare până la ultimul pas din țara ta, mult înainte de orele afișate, iar trackerul arată fiecare pas când îi vine ora. Un asemenea pas este un plan, nu o scanare. La fel procedează și [Meiteshun](carrier:mtscn), pe aceeași platformă. Planul GZYL se încheie cu un `Delivered` adăugat în lot seara în ziua afișată: datat a doua zi după ultimul pas al planului, în orele de program din Beijing, indiferent de țara ta. Serviciile de urmărire care copiază datele GZYL pot arăta și ele acești pași, deci `Delivered` acolo nu dovedește sosirea coletului.

## Cine livrează coletele GZYL în România

Peek nu știe ce companie livrează coletele GZYL în România: pașii GZYL din țara de destinație nu menționează niciun curier. Singurul curier local către care Peek poate preda un colet GZYL este [Swiss Post](carrier:swiss-post), în Elveția și Liechtenstein.

Peek nu a găsit termene de livrare de la GZYL și nici reguli despre încercările de livrare, punctele de ridicare sau durata de păstrare: acestea țin de firma locală care livrează. Pe pagina de urmărire GZYL, uită-te în coloana `渠道转单号` (număr de transfer) și în `跟踪号码` (cod de urmărire) când conține altceva decât codul tău `YL…YQ`: poate fi codul curierului local, pe care îl poți adăuga în Peek ca un colet separat. Dacă nu apare nimic, întreabă vânzătorul ce curier livrează și cere-i codul.

**Taxe vamale în UE.** Din 1 iulie 2026, pentru cumpărăturile online din afara UE în colete de până la 150 € se aplică o taxă vamală fixă de 3 € per categorie de produs. Din 1 noiembrie 2026 se adaugă și taxa UE de procesare (Union handling fee) de 2 € per categorie de produs, indiferent de valoare. Detalii în ghidul despre [colete la vamă](guide:customs) (în engleză).

## Colet GZYL care nu ajunge sau urmărire blocată

**Doar `Order information received` sau `货物电子信息已经收到`.** Vânzătorul a creat comanda, dar GZYL nu a scanat încă coletul. Dacă nu apare nimic în câteva zile, întreabă vânzătorul dacă l-a expediat într-adevăr.

**Blocat la vamă.** Dacă ți se solicită taxe de import, consultă ghidul despre [colete la vamă](guide:customs) (în engleză). Dacă stă acolo mai multe zile, anunță vânzătorul.

**Trackerul GZYL sau alt serviciu arată mai mulți pași decât Peek.** Peek reține pașii scriși înainte de ora pe care o afișează, pasul `Delivered` de la finalul planului GZYL și pașii datați după ultima verificare (vezi mai jos). Un pas scris dinainte nu schimbă statusul și nu trimite notificări. Când Peek reține astfel de pași, istoricul începe până la livrare sau retur cu «Other sites may show more steps, but they’re only predictions.», iar «Show them» îi afișează cu gri și ziua («Predicted for Mon 12 Oct»). Urmărește termenul estimat al magazinului și contactează vânzătorul când a trecut. Mai multe în ghidul despre [colete din China](guide:tracking-from-china) (în engleză).

**`Delivered`, dar nu a venit nimic.** Pasul poate face parte din programul fix al GZYL, nu dintr-o scanare. Cu excepția livrărilor prin Swiss Post, Peek arată sub «Delivered»: «GZYL updates on a fixed schedule, with no signature. If nothing came, ask the seller.» Spune-i vânzătorului că nu a ajuns și cere numele și codul curierului local. Mai multe în ghidul despre [livrat, dar neprimit](guide:delivered-not-received) (în engleză).

**Nimic găsit.** Pentru un cod necunoscut, trackerul GZYL întoarce un rezultat cu codul tău sub `参考号` (referință) și toate celelalte câmpuri goale. Peek afișează «Waiting for GZYL», iar după două zile «Check the tracking number». Compară codul cu mesajul vânzătorului.

**Pierdut sau returnat.** Vânzătorul a rezervat coletul cu GZYL, deci solicită rambursare sau reexpediere de la vânzător sau platformă.

## Contact și relații clienți GZYL

Pagina de contact (`联系我们`) a site-ului chinez GZYL (`yunlianhouyun.com`) listează o linie telefonică 24/7 (`全国24小时服务热线`), un cont WeChat și un cont QQ post-vânzare (`售后QQ`), dar nimic pentru destinatari: aceste canale sunt pentru clienții GZYL, adică vânzătorii. Pentru comanda ta întreabă vânzătorul, iar dacă nu răspunde, platforma.

## Urmărește un colet GZYL cu Peek

[Peek](/) recunoaște imediat GZYL pentru un cod care începe cu `YL738`, încă șapte cifre și `YQ` («Detected carrier»). Celelalte coduri `YL`, zece cifre și `YQ` sunt o potrivire slabă. Lipește-l oricum: Peek urmărește peste 4 500 de curieri. La adăugare, Peek întreabă trackerul GZYL («Checking tracking services…») și arată «GZYL has this parcel.» când primește un rezultat (chiar și răspunsul gol pentru un cod necunoscut). Altfel apasă «Change» și alege GZYL sub «Fits this number» (îl găsești și cu `广州云链`), sau folosește «Change carrier». Merge și referința vânzătorului dacă alegi manual GZYL — Peek afișează atunci codul comenzii sub «GZYL tracks this as» (însă elimină cratimele, deci pentru referințe cu cratime cere codul `YL…YQ`).

Peek citește trackerul propriu al GZYL și afișează statusul, fiecare pas cu localitatea și țara de destinație. Orele sunt citite la ora Beijingului și afișate în fusul orar al dispozitivului tău («Times are in your time zone:» la finalul istoricului). Peek omite numele, adresa, codurile poștale, greutatea, taxele, semnătura și dovada livrării și nu afișează o dată estimată de livrare.

Peek reține pașii scriși cu mai mult de 12 ore înainte de ora pe care o arată, pașii scriși în aceeași secundă cu un pas scris cu mai mult de o jumătate de oră înainte de ora lui (cu excepția înregistrării) și pașii de după momentul verificării la ora Beijingului. Pentru comenzile cu pași scriși dinainte reține și `Delivered`. Când interfața de date nu are pași, citește pagina de căutare GZYL și reține acolo orice `Delivered`. Dacă toți pașii sunt reținuți, afișează «Waiting for GZYL» fără cererea de a verifica codul și filtrează și copiile pașilor planificați din alte servicii. Totuși, dacă trackerul GZYL nu poate fi accesat, copiile din servicii terțe pot conține pași scriși dinainte.

În afară de predarea către Swiss Post, Peek rămâne pe GZYL: dacă primești codul curierului local de la vânzător, adaugă-l ca un colet separat. Cât timp ai coletul deschis sau în ziua livrării cu notificările pornite, Peek verifică până la fiecare 10 minute (și la 2 minute în ziua «Out for delivery» de la 06:00 ora locală, deși niciun pas cunoscut GZYL nu setează acest status); altfel și după două zile fără noutăți (12 ore la «Label created» / «Received by carrier»), o dată pe oră (prin servicii de urmărire: o dată pe oră, la 3 ore după o zi liniștită și până la 15 minute în ziua livrării). Notificările se aleg între «Every scan», «Important steps» (cei doi pași vamali și `Delivered`, fără zboruri sau sortare) și «Delivery only». Peek nu poate contacta GZYL sau vânzătorul în numele tău.

## Întrebări frecvente despre GZYL

### Ce este GZYL (广州云链)?

GZYL este 广州云链货运代理有限公司 (Guangzhou Yunlian), un expeditor din Guangzhou, China, cu site-ul în chineză `yunlianhouyun.com`. Codurile sale au 14 caractere: `YL`, zece cifre și `YQ`.

### Aparține fiecare cod YL…YQ companiei GZYL?

Nu automat. `YL`, zece cifre și `YQ` este formatul GZYL — Peek identifică imediat GZYL pentru `YL738…YQ` și verifică trackerul GZYL pentru restul codurilor `YL…YQ`. Nouă sau unsprezece cifre nu se potrivesc cu GZYL, iar `GZXH…YQ` aparține Xinghe Freight.

### De ce afișează GZYL «Delivered» când nu a sosit nimic?

GZYL scrie adesea dinainte mare parte din traseu și adaugă `Delivered` după un program fix, nu dintr-o scanare locală de livrare. Peek reține pașii programați și avertizează despre programul fix — anunță vânzătorul și cere curierul local.

### Cine livrează coletele GZYL în România?

Scanările GZYL nu numesc niciodată curierul local din România (doar în Elveția și Liechtenstein poate preda Peek către Swiss Post). Verifică coloana `渠道转单号` pe pagina GZYL sau întreabă vânzătorul cine livrează și care este codul local.

### Cum contactez GZYL pentru un colet întârziat sau pierdut?

Contactează mai întâi vânzătorul sau platforma. Deși `yunlianhouyun.com` listează o linie chineză 24/7, WeChat și QQ post-vânzare pentru clienții comerciali (vânzători), GZYL nu oferă un canal de suport pentru destinatari.

:::sources
- [广州云链货运代理有限公司 (GZYL): pagina principală (în chineză)](http://yunlianhouyun.com/) – numele companiei, site în chineză, căutare colete (`小包查询`) pe trackerul GZYL
- [广州云链货运代理有限公司 (GZYL): 联系我们 (contact, în chineză)](http://yunlianhouyun.com/contact.html) – linie 24/7, WeChat și QQ post-vânzare
- [Comisia Europeană: EU Customs Reform (în engleză)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – taxă vamală de 3 € per categorie de produs până la 150 € din 1 iulie 2026, taxă UE de procesare de 2 € din 1 noiembrie 2026
- [Comisia Europeană: Guidance on the EUR 3 customs duty and the Union handling fee (PDF, în engleză)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – taxa de 2 € per categorie indiferent de valoare
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – nume, tracker pe adresă IP, formatele `YL…YQ` și `YL738…YQ`, Xinghe Freight pe trackerul GZYL, căutare după referință, formulări de pași, linie în chineză, orașe, ora Beijingului, pași scriși dinainte, răspuns la cod necunoscut, coloanele pentru numărul de transfer, predare către Swiss Post și ce afișează, reține și omite Peek
:::
