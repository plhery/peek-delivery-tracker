---
title: Urmărire colet Xinghe Freight: cod GZXH…YQ și statusuri
description: Urmărește un colet Xinghe Freight (星和货运) din China: formatul GZXH…YQ, de ce folosește trackerul GZYL, pași programați din timp, taxe vamale UE și contact.
slug: xinghe-freight-urmarire-colet
published: 2026-11-27
updated: 2026-11-27
---

Xinghe Freight (星和货运, 广州星和货运代理有限公司) este un expeditor de marfă din Guangzhou, China, înființat în 2014, care oferă transport maritim, aerian și terestru, servicii de agenturare navală și declarare vamală. Pentru a urmări coletul, lipește în caseta de mai sus codul de 16 caractere format din `GZXH`, zece cifre și `YQ`. Site-ul Xinghe Freight trimite căutările către trackerul HTTP necriptat al [GZYL](carrier:gzyl), unde pașii — inclusiv `Delivered` — pot fi programați dinainte în loc să fie scanări reale. Peek citește acel tracker pentru tine, separă pașii prezisi de scanările reale și apelează la servicii de urmărire atunci când este nevoie.

## Codul de urmărire Xinghe Freight: cum arată

Codurile pe care Peek le recunoaște ca aparținând Xinghe Freight au `GZXH`, zece cifre și `YQ` — 16 caractere în total. Dacă ai omis ultima literă `Q` (`GZXH`, zece cifre și `Y`, 15 caractere), Peek recunoaște totuși codul și pune `Q` la loc înainte de interogare. Exemple cu cifre inventate:

| Cod | Ce reprezintă |
| --- | --- |
| `GZXH4829105736YQ` | `GZXH`, zece cifre și `YQ`: formatul Xinghe Freight. Peek identifică Xinghe Freight pe loc. |
| `GZXH4829105736Y` | `GZXH`, zece cifre și `Y`: lipsește ultima literă `Q`, dar Peek o adaugă automat. |
| `GZXH482910573YQ` | Doar nouă cifre: nu este formatul Xinghe Freight. Peek respinge codul imediat ca să verifici dacă lipsește o cifră. |
| `YL4829105736YQ` | `YL`, zece cifre și `YQ`: formatul de AWB al [GZYL](carrier:gzyl). Același tracker, alt expeditor. |
| `JXCAA4829105736YQ` | Se termină tot cu `YQ`, ca mulți expeditori chinezi, dar începe cu `JXC`: cod [Juxi Express](carrier:juxi-express). |

Spațiile, cratimele și literele mici nu contează când lipești codul în Peek.

**Unde îl găsești.** Vânzătorul rezervă expedierea cu Xinghe Freight și îți trimite codul în e-mailul de expediere sau pe pagina comenzii. Dacă nu îl găsești, cere-l vânzătorului.

## Ce înseamnă statusurile Xinghe Freight

Urmărirea Xinghe Freight rulează pe platforma GZYL, astfel că mesajele vin în formulările în engleză ale GZYL, cu greșelile de scriere cu tot. Deoarece interfața Peek nu are versiune în română, etapele Peek apar tot în engleză: „Label created” (etichetă creată), „Received by carrier” (primit de curier), „In transit” (în tranzit), „At customs” (la vamă) și „Delivered” (livrat). Pașii în ordinea obișnuită:

| Trackerul scrie | Ce înseamnă pentru tine | Peek afișează |
| --- | --- | --- |
| `Order information received` | Vânzătorul a trimis datele comenzii. Nimeni nu are încă coletul fizic. | „Label created” |
| `Arrive at the cross border receiving point` | Expeditorul a preluat coletul: prima scanare reală. | „Received by carrier” |
| `Goods leave the operation center` | Coletul a plecat din centrul operațional. | „In transit” |
| `Arrive at the international airport` | La aeroportul internațional, așteptând zborul. | „In transit” |
| `Departing from the airport` | Avionul a decolat. | „In transit” |
| `Item arrives at destination airport` | A aterizat în țara de destinație. | „In transit” |
| `local carrier Agent Import customs clearance` | Un agent din țara ta efectuează vămuirea de import. | „At customs” |
| `The import customs keep it for inspection` | Vama din țara ta a reținut coletul pentru verificare. | „At customs” |
| `Customs clearance completed` | Vămuirea s-a încheiat și coletul își continuă drumul. | „In transit” |
| `Internationaliy Package received at processing center` | Un centru de procesare din țara ta a primit coletul vămuit. Nu este o nouă preluare de la expeditor. | „In transit” |
| `Leave the email processing center` | Coletul a părăsit acel centru de procesare. | „In transit” |
| `Arrival sorting, distribution center` | Sosit la un centru de sortare sau distribuție din țara ta, urmat de oraș. | „In transit” |
| `Leaving the sorting center` | Pleacă dintr-un centru de sortare, urmat de oraș. | „In transit” |
| `Package is on the way` | În drum prin țara de destinație, cu un oraș. Nu înseamnă că este ieșit în livrare cu curierul. | „In transit” |
| `Delivered` | Livrarea, cu orașul. Poate fi un plan, nu o scanare: vezi mai jos. | „Delivered”, cu o notă despre programul fix, dacă Peek nu reține pasul (vezi mai jos) |

**Prin Hong Kong.** Poate apărea și `香港处理中心已收寄【Hong Kong】`: o acceptare poștală la centrul de procesare din Hong Kong, transmisă de tracker în chineză cu orașul între paranteze. Peek o afișează ca atare, la etapa „Received by carrier”.

**O linie în chineză.** Pentru o comandă fără scanări încă, platforma scrie automat `货物电子信息已经收到` (informațiile electronice ale mărfii au fost primite). Vânzătorul a creat comanda, dar nimeni nu a scanat încă coletul. Peek o afișează în chineză sub „Label created”.

**Orele.** Peek citește fiecare oră din tracker la ora Beijingului (`UTC+8`), inclusiv pașii din România, și o convertește la fusul orar al dispozitivului tău. Iarna, 20:00 la Beijing înseamnă 14:00 ora României (`UTC+2`), iar vara 15:00 (`UTC+3`).

**Pași scriși dinainte.** Pe această platformă de urmărire, expeditorii pot scrie în avans întregul calendar al coletului, de la preluare până la ultimul pas din țara ta, iar trackerul afișează fiecare pas odată ce îi vine ora. Un astfel de pas este un plan, nu o scanare. Pe trackerul GZYL, planul se încheie cu un `Delivered` adăugat în lot seara în ziua pe care o arată: datat a doua zi după ultimul pas al planului și în orele de program din Beijing, indiferent de țara ta. Peek reține acești pași (vezi mai jos), dar o copie de pe alte servicii de urmărire îi poate afișa, așa că un `Delivered` acolo nu dovedește că a ajuns coletul.

## Cine livrează coletele Xinghe Freight în România

Peek nu știe ce firmă livrează coletele Xinghe Freight în România. Pașii din țara de destinație nu menționează niciun curier, iar site-ul Xinghe nu spune nimic despre coletele cumpărătorilor online. Singurul curier local către care Peek poate preda urmărirea unui colet Xinghe este [Swiss Post](carrier:swiss-post), în Elveția și Liechtenstein, atunci când fișa Xinghe o menționează.

Site-ul Xinghe Freight nu publică termene de livrare, număr de încercări sau puncte de ridicare: acestea depind de curierul local care livrează coletul. Întreabă vânzătorul ce curier livrează comanda ta și cere numărul lui de urmărire.

**Taxe vamale în UE.** Din 1 iulie 2026, bunurile cumpărate online din afara Uniunii Europene în colete cu valoare de până la 150 € suportă o taxă vamală fixă de 3 € per categorie de produs. Din 1 noiembrie 2026 se adaugă și taxa UE de procesare (Union handling fee) de 2 € per categorie de produs, indiferent de valoare. O categorie include bunurile din același colet cu aceeași clasificare tarifară, descriere și (unde este cerută) origine, indiferent de numărul de bucăți. Mai multe detalii în ghidul despre [colete la vamă](guide:customs) (în engleză).

## Colet Xinghe Freight care nu ajunge sau urmărire blocată

**Doar `Order information received` sau `货物电子信息已经收到`.** Vânzătorul a creat comanda, dar nimeni nu a scanat coletul încă. Dacă nu apare nimic după câteva zile, întreabă vânzătorul dacă a fost expediat cu adevărat.

**„Waiting for Xinghe Freight”.** Peek nu are încă pași confirmați pentru codul tău: fie trackerul GZYL nu îl cunoaște, fie conține doar pași programați dinainte (care pot ajunge până la `Delivered`), iar serviciile de urmărire nu au nici ele nimic. Peek continuă să verifice. Pentru un cod necunoscut, trackerul GZYL întoarce un rând cu codul ca referință și toate celelalte câmpuri goale; Peek îl tratează ca negăsit, afișează „Waiting for Xinghe Freight” și, după două zile, „Check the tracking number”. Un plan scris dinainte contează ca dovadă că Xinghe are comanda, deci „Waiting for Xinghe Freight” rămâne fără solicitarea de a verifica codul. Compară totuși codul caracter cu caracter cu mesajul vânzătorului.

**Blocat la vamă.** Dacă ți se cer taxe de import, consultă ghidul despre [colete la vamă](guide:customs) (în engleză). Dacă stă acolo mai multe zile, anunță vânzătorul.

**Trackerul sau alt site arată mai mulți pași decât Peek.** Pașii în plus, inclusiv `Delivered`, pot fi un plan scris dinainte pe care Peek îl reține. Ghidează-te după termenul estimat dat de magazin și contactează vânzătorul după ce a trecut. Mai multe în ghidul despre [urmărirea coletelor din China](guide:tracking-from-china) (în engleză).

**`Delivered`, dar nu a venit nimic.** Pasul poate face parte dintr-un program scris dinainte. Cu excepția livrărilor prin Swiss Post, Peek afișează sub „Delivered” nota: „Xinghe Freight updates on a fixed schedule, with no signature. If nothing came, ask the seller.” Spune-i vânzătorului că nu ai primit coletul și cere numele și codul curierului local. Mai multe în ghidul despre [colet marcat livrat, dar neprimit](guide:delivered-not-received) (în engleză).

**Pierdut sau returnat.** Vânzătorul a rezervat transportul la Xinghe Freight, așa că cere rambursare sau reexpediere de la vânzător sau de pe platforma unde ai comandat.

## Contact și relații clienți Xinghe Freight

Site-ul în chineză al Xinghe Freight (`xinghe-express.cn`) publică:

- **Linie telefonică (24/7):** 19924483103 (+86 199 2448 3103 din străinătate), declarată disponibilă non-stop, zilnic.
- **E-mail:** 3975605292@qq.com, pentru solicitări comerciale și oferte de preț.
- **Adresă:** Guangzhou, provincia Guangdong, fără stradă (pentru adresa completă cere să îi contactezi mai întâi).

Site-ul este exclusiv în chineză și nu are un canal dedicat destinatarilor din străinătate: formularul de contact doar deschide un e-mail către adresa de mai sus. Clientul Xinghe este vânzătorul, deci întreabă mai întâi vânzătorul și apoi platforma dacă nu primești răspuns.

## Urmărește un colet Xinghe Freight cu Peek

[Peek](/) recunoaște pe loc codul `GZXH…YQ` (și varianta trunchiată `GZXH…Y`) ca Xinghe Freight cu eticheta „Detected carrier”. Dacă vânzătorul ți-a dat un cod în alt format, lipește-l oricum: Peek urmărește peste 4 500 de curieri. Pentru a selecta Xinghe Freight manual, apasă „Change” și caută Xinghe Freight (sau `星和货运`), ori folosește „Change carrier” pe un colet deja adăugat.

Peek interoghează direct trackerul GZYL, la fel ca formularul de pe site-ul Xinghe. Afișează statusul, fiecare pas cu localitatea lui și țara de destinație când fișa o include. Orele sunt citite la ora Beijingului și convertite la fusul orar al dispozitivului tău („Times are in your time zone:” la finalul istoricului). Peek omite numele, adresa, codurile poștale, greutatea, detaliile de plată, semnătura și dovada livrării și nu afișează o dată estimată de livrare, deoarece trackerul nu oferă una.

Când trackerul nu cunoaște codul, Peek întreabă servicii de urmărire și arată copia lor de pași. Cât timp niciuna nu are pași, apare „Waiting for Xinghe Freight”, iar după două zile „Check the tracking number”.

Peek reține orice pas scris cu mai mult de 12 ore înainte de ora pe care o afișează, orice pas scris în aceeași secundă cu un pas scris cu mai mult de o jumătate de oră înainte de ora lui (cu excepția înregistrării comenzii) și orice pas datat după momentul verificării la ora Beijingului. Pentru fișele cu pași scriși dinainte, Peek reține și `Delivered`, chiar dacă a fost scris în aceeași zi. Se ghidează după pașii păstrați, nu după statusul rezumat al fișei, și elimină și copiile acestor pași din serviciile de urmărire, inclusiv `Delivered`. Totuși, dacă trackerul nu are fișa sau nu răspunde, Peek nu poate deosebi un pas planificat dintr-un serviciu terț de o scanare reală. Când reține predicții, istoricul începe până la livrare sau retur cu „Other sites may show more steps, but they’re only predictions.”, iar „Show them” le afișează cu gri și cu ziua dată de tracker („Predicted for Mon 12 Oct”). Acești pași nu schimbă statusul și nu declanșează notificări.

În afară de Swiss Post, Peek nu trece automat la curierul local: dacă vânzătorul îți dă numărul acestuia, adaugă-l ca un colet separat. Cât timp ai coletul deschis sau în ziua livrării cu notificările pornite, Peek verifică până la fiecare 10 minute; altfel și când nu mai apar noutăți, o dată pe oră. Poți alege notificări pentru „Every scan”, „Important steps” (cei doi pași de vamă și `Delivered`, fără zboruri sau sortare) sau „Delivery only”. Peek nu poate contacta Xinghe Freight, GZYL sau vânzătorul în numele tău.

## Întrebări frecvente despre Xinghe Freight

### Ce este Xinghe Freight (星和货运)?

广州星和货运代理有限公司 (Guangzhou Xinghe Freight Forwarding), un expeditor de marfă din Guangzhou, China, înființat în 2014, care oferă transport maritim, aerian și terestru, agenturare navală și declarare vamală. Codurile sale de colet au 16 caractere: `GZXH`, zece cifre și `YQ`.

### De ce arată coletul meu Xinghe Freight urmărirea GZYL?

Caseta de urmărire de pe site-ul Xinghe Freight trimite căutările direct la trackerul GZYL, așa că formulările pașilor sunt ale GZYL. Peek păstrează coletul sub Xinghe Freight și citește acel tracker pentru tine.

### Cine livrează coletele Xinghe Freight în România?

Nici site-ul Xinghe, nici trackerul nu numesc curierul local din România (Peek predă automat doar către Swiss Post în Elveția și Liechtenstein atunci când apare în fișă). Întreabă vânzătorul ce firmă livrează coletul tău și cere numărul ei de urmărire.

### De ce arată Peek mai puțini pași sau niciun pas pentru codul meu GZXH?

Fie trackerul nu are încă nicio înregistrare, fie conține pași scriși dinainte — inclusiv un `Delivered` programat — pe care Peek îi ascunde sub „Show them” în loc să îi numere ca scanări. Verifică codul în e-mailul vânzătorului și scrie-i dacă nu apare nicio actualizare după câteva zile.

### Cum contactez Xinghe Freight?

Întreabă mai întâi vânzătorul sau platforma unde ai comandat, deoarece vânzătorul a rezervat transportul. Site-ul chinez (`xinghe-express.cn`) afișează linia non-stop 19924483103 (+86 199 2448 3103 din străinătate) și adresa comercială 3975605292@qq.com, fără suport dedicat destinatarilor din afara Chinei.

:::sources
- [Xinghe Freight: 广州星和货运代理有限公司 (pagina principală, în chineză)](http://xinghe-express.cn/) – numele companiei, servicii de expediție, casetă de căutare spre trackerul GZYL, telefon și e-mail
- [Xinghe Freight: 关于我们 (despre noi, în chineză)](http://xinghe-express.cn/about.html) – înființare în 2014, transport maritim, aerian și terestru, agenturare navală și declarare vamală
- [Xinghe Freight: 产品服务 (servicii, în chineză)](http://xinghe-express.cn/services.html) – servicii oferite, fără detalii despre colete pentru cumpărători online
- [Xinghe Freight: 联系我们 (contact, în chineză)](http://xinghe-express.cn/contact.html) – linie 7×24, e-mail comercial, adresă doar Guangzhou, formular care deschide clientul de e-mail
- [Comisia Europeană: EU Customs Reform (în engleză)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – taxă vamală de 3 € per categorie de produs până la 150 € din 1 iulie 2026, taxă UE de procesare de 2 € din 1 noiembrie 2026
- [Comisia Europeană: Guidance on the EUR 3 customs duty and the Union handling fee (PDF, în engleză)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – taxa de 2 € indiferent de valoare și definiția categoriei de produs
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – nume, formatele `GZXH…YQ` și trunchiat `GZXH…Y`, respingerea lungimii greșite, trackerul GZYL, formulări de pași, acceptare Hong Kong, linie în chineză, ora Beijingului, pași scriși dinainte, servicii de urmărire de rezervă, predare către Swiss Post și ce afișează, reține și omite Peek
:::
