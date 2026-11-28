---
title: Urmărire HuaHan Logistics: coduri HHW…YQ și cine livrează
description: Urmărește un colet HuaHan Logistics (华翰物流) din China: formatul HHW…YQ, ce înseamnă statusurile și cum afli cine îl livrează în țara ta.
slug: huahan-logistics-urmarire-colet
published: 2026-11-28
updated: 2026-11-28
---

HuaHan Logistics (华翰物流, cunoscută și ca HuaHan Express sau 华翰速递) este o companie chineză care transportă colete cu avionul din China și le predă unui curier local din țara de destinație, care efectuează livrarea. Dacă un magazin ți-a trimis comanda cu HuaHan, caută un număr care începe cu HHW și se termină cu YQ și lipește-l în caseta de mai sus. Trackerul propriu al HuaHan se află pe site-ul său, hh-exp.cn, și poate numi curierul care livrează în zona ta, împreună cu numărul acelui curier pentru coletul tău.

## Numărul de urmărire HuaHan: cum arată

Peek recunoaște scrisorile de transport HuaHan ca HHW, două litere, zece cifre și YQ, 17 caractere în total. Cu cifre inventate:

| Număr | Ce reprezintă |
| --- | --- |
| `HHWCN4829105736YQ` | HHW, două litere, zece cifre și YQ: o scrisoare de transport HuaHan. |
| `HHWCN482910573YQ` | Doar nouă cifre: nu este formatul HuaHan. Verifică dacă lipsește o cifră. |
| `WNBAA4829105736YQ` | Se termină tot cu YQ, dar începe cu WNB: un număr [WanbExpress](carrier:wanbexpress). |
| `JXCAA4829105736YQ` | Cu JXC la început: un număr [Juxi Express](carrier:juxi-express). |

Mulți expeditori chinezi își termină numerele în YQ, fiecare cu propriile litere la început: HHW este ceea ce distinge HuaHan și niciun alt curier din catalogul Peek nu îl folosește. Spațiile, cratimele și literele mici nu contează atunci când lipești numărul în Peek.

**Unde îl găsești.** Magazinul oferă numărul în e-mailul de expediere sau pe pagina comenzii tale. Dacă nu îl găsești, vezi [cum găsești numărul de urmărire](guide:find-tracking-number) (în engleză).

**Un al doilea număr.** Trackerul HuaHan poate afișa și numărul propriu al curierului local pentru colet. Uneori HuaHan adaugă un sufix după o liniuță de subliniere pe care numărul curierului nu îl are: lasă-l deoparte când cauți numărul pe site-ul acelui curier.

## Statusurile de urmărire HuaHan explicate

Trackerul HuaHan listează fiecare scanare cu ora, textul și locul, de la cea mai nouă la cea mai veche. Scanările sale proprii sunt în engleză, unele precedate de chineză. Iată-le în ordinea în care le listează codul Peek:

| Ce scrie HuaHan | Ce înseamnă pentru tine | Ce afișează Peek |
| --- | --- | --- |
| `快件电子信息已经收到 Shipment information received`, `Shipment information received` | Magazinul a trimis datele coletului către HuaHan. HuaHan nu are încă coletul. | „Label created” |
| `快件到达作业中心 Shipment arrived at facility`, `Has entered the warehouse` | Centrul HuaHan din China are coletul: în istoricul păstrat în codul Peek, în Shenzhen. | „Received by carrier” |
| `快件操作完成 Shipment departed from facility` | Procesat și pe drum spre ieșirea din acel centru. | „In transit” |
| `Transfer to Origin airport` | În drum spre aeroport. | „In transit” |
| `Export customs clearance completed` | Vămuirea de export la plecare s-a încheiat. Nu este vama din țara ta. | „In transit” |
| `Departed from airport of Origin` | Zborul a plecat. | „In transit” |
| `Import Customs clearance started` | Se află la vama din țara de destinație. | „At customs” |
| `Arrived at destination airport` | Zborul a aterizat în țara de destinație. | „In transit” |
| `Import Customs clearance completed` | Vama de import i-a dat drumul. | „In transit” |
| `Has been given to local carrier` | HuaHan l-a predat curierului care îl livrează. Urmează liniile proprii ale acelui curier. | „In transit” |

**Liniile curierului local.** După predare, trackerul HuaHan transmite mai departe scanările curierului local. Peek le interpretează după codul HuaHan pentru pas, acolo unde există unul, sau după formulare. Formulările întâlnite de codul Peek și atribuite acelui curier:

| După predare | Ce înseamnă pentru tine | Ce afișează Peek |
| --- | --- | --- |
| `Ready to Ship, Parcel received and processed` | Curierul local are coletul. | „In transit” |
| `The parcel is in the central warehouse waiting to be processed.`, `In Transit, Shipment arrived to sorting centre / Shipment in the sorting centre`, `In Transit, Shipment remained at sorting hub` | În depozitul central ori într-un centru de sortare al curierului local, sau încă acolo. | „In transit” |
| `In Transit, Arrived to HU HUB`, `In Transit, Parcel has entered the Linehaul network.` | În hub-ul curierului local din Ungaria, apoi în tranzit între hub-urile sale. | „In transit” |
| `In Transit, Picked Up` (urmat de `Einrollung` în germană al DPD), `In Transit, Scanned By DPD At parcel delivery centre.`, `In Transit, At parcel delivery centre.` | La depozitul de livrare DPD: scanarea de sosire acolo, nu o ridicare de la tine, apoi o scanare pentru fiecare zi în care coletul așteaptă acolo. | „In transit” |
| `Out for Delivery, Parcel is at courier, delivery expected soon.` | Pe ruta de livrare. | „Out for delivery” |
| `The courier will deliver the parcel soon. Additional details are available in the SAMEDAY app, email or SMS.` | Pe ruta de livrare, în cuvintele [Sameday](carrier:sameday), un curier din România și Bulgaria. | „Out for delivery” |
| `Delivery Attempted`, `Delivery Attempted, Unfortunately we have not been able to deliver your parcel. Pickup parcelshop delivery after first delivery attempt.` | DPD a încercat să livreze și nu a reușit. Cu linia mai lungă, coletul merge mai departe la un punct de ridicare. | „Delivery attempted” |
| `Awaiting Pickup, Parcel was deposited in Pickup parcel station` | După acea încercare, DPD a lăsat coletul la un punct de ridicare pentru a-l prelua tu. | „Ready for pickup” |
| `The parcel has been successfully delivered.` | Livrat. | „Delivered” |

HuaHan încadrează scanările curierului local pentru ieșirea în livrare, punctul de ridicare și livrare sub coduri proprii, iar Peek le afișează ca „Out for delivery”, „Ready for pickup” sau „Delivered”, dacă textul nu numește o altă etapă: HuaHan pune sub codul său de ieșire în livrare și plecarea USPS dintr-un centru de distribuție.

**Două linii care creează confuzie.** `We will pick up your parcel from the sender soon.` poate apărea aproape de început, înainte ca HuaHan să primească măcar coletul în China: este notificarea curierului local că a fost anunțat despre colet, nu o ridicare de la tine. Peek afișează „Label created”. `The parcel was redirected. As soon as it is in the couriers possession, it will be processed as requested.` reprezintă acel curier care notează o redirecționare cerută de tine, scrisă de asemenea cât coletul este încă în China. Nu mută nimic, iar Peek nu îi acordă un status propriu.

**Date, nu scanări.** `The instruction data for this shipment have been provided by the sender to DHL electronically.`, uneori fără punctul final, este înregistrarea DHL privind datele coletului pentru ultimul segment, transmisă înainte ca HuaHan să aibă coletul în China sau după ce acesta a plecat de acolo. `货物电子信息已经收到` (datele electronice ale mărfii primite), în chineză, reprezintă datele comenzii și poate apărea după ce HuaHan are coletul. Niciuna nu este o mișcare: Peek afișează „Label created” sau păstrează statusul pe care coletul îl avea deja.

**O scanare, două linii.** Trackerul HuaHan poate lista una dintre scanările curierului local de două ori la aceeași oră: în cuvintele acelui curier cu locul ei, și fără loc în cuvintele generice ale HuaHan, precum `Delivered, Parcel was delivered successfully`. Peek păstrează linia cu locul.

**Prin poștă.** HuaHan poate trimite un colet și prin China Post. Trackerul său transmite apoi scanările China Post în chineză, cu oficiul în 【】, iar mai târziu scanările poștei de destinație așa cum le copiază China Post. Peek le afișează în chineză, așa cum le transmite HuaHan:

| Ce transmite HuaHan | Ce înseamnă pentru tine | Ce afișează Peek |
| --- | --- | --- |
| `中国邮政已收取邮件` | China Post a primit coletul de la HuaHan: de aici merge prin poștă. | „In transit” |
| `邮件已在【…】完成分拣，准备发出`, `邮件到达【…】`, `邮件离开【…】，正在发往【…】`, `邮件离开【…】，正在发往下一站` | Sortat la, sosit la sau plecând dintr-un oficiu China Post din China, numit în 【】. | „In transit” |
| `送交出口海关` | Predat vămii de export din China. Nu este vama din țara ta. | „At customs” |
| `出口海关/放行` | Vama de export l-a eliberat. | „In transit” |
| `已交承运商运输` | Predat companiei aeriene care îl transportă în străinătate. | „In transit” |
| `到达寄达地`, `到达寄达地处理中心`, `到达境外经转局` | A sosit în țara de destinație, la centrul de procesare al poștei acesteia sau la un oficiu de tranzit pe drum. | „In transit” |
| `境外进口海关留存待验(等待提交给边境机构/安全部门)` | Reținut pentru vama de import a țării tale; paranteza spune că așteaptă să fie prezentat autorităților de frontieră sau de securitate. | „At customs” |
| `境外进口互换局留存`, `送交境外进口海关` | Păstrat la biroul de schimb al poștei de destinație, apoi predat vămii de import. | „At customs” |
| `寄达地海关允许进口（无需缴税）` | Vama de import i-a permis intrarea, fără taxe vamale de plată. | „In transit” |
| `离开【…】处理中心`, `已到达【…】投递局` | A părăsit centrul de procesare din țara numită în 【】, apoi a ajuns la oficiul de distribuire. | „In transit” |

**Prin Landmark Global.** Pentru unele colete, trackerul HuaHan transmite liniile [Landmark Global](carrier:landmark-global), în engleză. Primele două apar înainte ca HuaHan să aibă coletul în China; celelalte urmează predării Landmark Global către rețeaua [bpost](carrier:bpost), care livrează:

| Ce transmite HuaHan | Ce înseamnă pentru tine | Ce afișează Peek |
| --- | --- | --- |
| `Shipment Data Uploaded` | Înregistrarea Landmark Global a datelor expeditorului. Nu este o mișcare. | „Label created” |
| `Processed` | Procesarea Landmark Global, în același minut cu acea înregistrare. Nu este o recepție a coletului: este o notificare, iar Peek păstrează etapa dinaintea ei. | Etapa dinaintea ei |
| `Incoming scan at facility` | Un depozit are coletul, după predarea către rețeaua bpost. | „In transit” |
| `Outgoing scan at facility` | A părăsit acel depozit. Tura de livrare poate fi încă la câteva zile distanță. | „In transit” |
| `Out for delivery (On the way)` | Pe ruta de livrare. | „Out for delivery” |
| `Delivery attempt (Delivery not possible)` | O tură de livrare care nu l-a putut preda. | „Delivery attempted” |

HuaHan încadrează `Outgoing scan at facility` sub codul său de ieșire în livrare, însă cuvintele indică o plecare dintr-un depozit, așa că Peek afișează „In transit”.

## Cine livrează coletele HuaHan

După `Has been given to local carrier`, un curier din țara de destinație livrează coletul, iar trackerul HuaHan îl poate numi și poate oferi numărul său.

**Ce arată codul Peek.** Un istoric HuaHan arată livrarea prin Sameday: linia sa de ieșire în livrare trimite la aplicația SAMEDAY. Codul Peek se așteaptă și la numere [USPS](carrier:usps) de la HuaHan: când HuaHan dă codul de bare lung de pe eticheta USPS, Peek păstrează din el numărul propriu de urmărire al USPS. [DHL](carrier:dhl) este rezervat pentru ultimul segment al unor colete: linia de date citată mai sus este înregistrarea sa pentru datele expeditorului. DPD livrează unele colete: scanările sale de depozit, cum ar fi `In Transit, Scanned By DPD At parcel delivery centre.`, sunt în tabelul de mai sus. [CTT Express](carrier:ctt-express) este anunțat pentru ultimul segment al altora: trackerul HuaHan transmite notificarea `Pendiente de recepción en CTT Express` („în așteptarea recepției la CTT Express”) la câteva minute după scanarea de sosire a HuaHan în China, pe ceasul Madridului, iar Peek afișează „Label created”. Altele trec prin [Landmark Global](carrier:landmark-global): trackerul HuaHan transmite liniile sale de la înregistrarea datelor expeditorului până la tura de livrare, iar după predarea Landmark Global livrează rețeaua bpost (vezi „Prin Landmark Global” mai sus). Dincolo de acestea, codul nu leagă niciun curier de o anumită țară: Peek se bazează pe cel numit de HuaHan pentru coletul tău.

**Încercări de livrare și ridicare.** Numărul de încercări, avizele și locul unde așteaptă un colet nelivrat, precum și cât timp stă acolo, urmează regulile curierului local: întreabă acel curier, cu numărul lui, pentru a reprograma livrarea. Vezi [livrare ratată](guide:missed-delivery) și [colete neridicate](guide:uncollected-parcel) (în engleză).

**Vama în UE.** Bunurile cumpărate online din afara UE au din 1 iulie 2026 o taxă vamală de 3 € per articol pentru colete în valoare de până la 150 €, și din 1 noiembrie 2026 o taxă de procesare a Uniunii de 2 € per articol, indiferent de valoare. Un articol reprezintă bunurile care împart aceeași clasificare tarifară, descriere și, unde se cere, origine. Peek nu a putut verifica dacă HuaHan le percepe în avans de la vânzător: întreabă magazinul dacă prețul tău le-a inclus. În afara UE, ce datorezi depinde de țara ta. Vezi [colete reținute la vamă](guide:customs) (în engleză).

## Colet HuaHan care nu ajunge sau urmărire blocată

**Număr negăsit.** Când HuaHan nu are nicio înregistrare pentru număr, trackerul său răspunde 没有找到记录 („nicio înregistrare găsită”), iar Peek afișează „Waiting for HuaHan Logistics” și continuă să verifice. Dacă HuaHan tot nu cunoaște numărul două zile mai târziu și nimic altceva nu a răspuns pentru el, Peek afișează „After two days, the carrier still doesn’t know this number. Check it, choose another carrier or delete the parcel.” Verifică numărul față de mesajul vânzătorului, caracter cu caracter: HHW, două litere, zece cifre, YQ. Dacă nu apare nimic după câteva zile, întreabă vânzătorul dacă pachetul a fost expediat într-adevăr.

**Blocat pe `Shipment information received`.** Magazinul a rezervat expedierea, dar HuaHan nu a scanat încă coletul. Întreabă magazinul când l-a predat.

**Blocat la `Import Customs clearance started`.** Vama din țara ta are coletul. Dacă ți se cer taxe de import, vezi [colete reținute la vamă](guide:customs) (în engleză); dacă rămâne acolo mult timp, anunță vânzătorul.

**Nimic după `Has been given to local carrier`.** Curierul local are coletul, iar urmărirea sa proprie poate fi mai avansată decât a HuaHan. Găsește numele și numărul său în trackerul HuaHan, sau pe cardul coletului din Peek atunci când Peek cunoaște acel curier, ori întreabă vânzătorul cine livrează.

**Nicio actualizare de zile întregi.** Când un colet aflat încă pe drum nu a avut nicio scanare nouă timp de patru zile, Peek îl marchează cu ziua ultimei scanări („No tracking update since Mon 12 Oct”) și notează că coletele rămân adesea fără vești pe zboruri lungi, la vamă sau în weekend; după trei săptămâni fără noutăți, îți spune să întrebi vânzătorul. Vezi [de ce nu se actualizează urmărirea](guide:tracking-not-updating) și [urmărirea coletelor din China](guide:tracking-from-china) (în engleză).

**Livrat, dar nu a ajuns nimic.** Verifică unde a fost lăsat și întreabă vecinii, apoi curierul local și vânzătorul. Vezi [marcat livrat, dar neprimit](guide:delivered-not-received) (în engleză).

**Pierdut sau returnat.** Vânzătorul a rezervat coletul la HuaHan, așa că vânzătorul sau platforma este cel căruia să îi ceri rambursarea ori un colet nou. Vezi [colete pierdute](guide:lost-parcel) și [returnat la expeditor](guide:returned-to-sender) (în engleză).

## Număr de contact și serviciu clienți HuaHan

Pe site-ul său ([hh-exp.com/lxwm](https://www.hh-exp.com/lxwm)), HuaHan indică o linie telefonică în **China**, 400-015-6080, fără program publicat, și sediul central din Shenzhen; nu oferă o adresă de e-mail de suport pentru destinatari. Vânzătorul a rezervat coletul cu HuaHan și este cel pe care să îl întrebi despre comanda ta, iar platforma dacă vânzătorul nu răspunde. Pentru livrarea în sine, întreabă curierul local.

## Urmărește un colet HuaHan cu Peek

[Peek](/) numește imediat HuaHan Logistics după un număr HHW…YQ („Detected carrier”) și interoghează trackerul HuaHan doar cu întregul număr: nu este nevoie de cont, cod poștal sau număr de telefon. Dacă ai doar un număr de comandă, cere vânzătorului numărul de transport HHW…YQ. Dacă ai doar numărul curierului local, lipește-l pe acela: Peek urmărește peste 4.500 de curieri.

Peek afișează statusul, fiecare scanare cu locul ei și țara de destinație din fișa HuaHan. Trackerul HuaHan tipărește ore fără fus orar. Peek citește scanările proprii ale HuaHan pe ceasul locului lor sau al etapei în care se afla coletul: ora Beijingului în China. Scanările curierului local de după `Has been given to local carrier` sunt citite pe ceasul țării tale atunci când aceasta are un singur fus orar. Fiecare oră este afișată în fusul orar al dispozitivului tău. HuaHan nu oferă dată estimată de livrare sau greutate, deci Peek nu afișează niciuna.

Atunci când HuaHan numește un curier local cunoscut de Peek, cu un număr în formatul acelui curier, cardul coletului indică acest lucru (de exemplu „Delivery with USPS”, sau „Announced to USPS, not received yet” cât timp curierul respectiv are doar anunțul coletului, fără nicio scanare proprie) și listează numărul acelui curier după cel al HuaHan. Peek interoghează apoi și acel curier. Dacă HuaHan dă un număr poștal internațional (două litere, nouă cifre, două litere) și niciun curier cunoscut de Peek, Peek întreabă poșta țării de destinație; pentru alt număr, unicul curier către care indică formatul acestuia. Odată ce acel curier afișează o scanare cel puțin la fel de recentă ca cea mai nouă de la HuaHan, Peek urmărește coletul acolo și păstrează istoricul HuaHan. Dacă HuaHan nu oferă niciun număr pentru curierul local, Peek rămâne pe trackerul HuaHan, care continuă să transmită scanările acelui curier.

Când trackerul HuaHan nu poate fi contactat, Peek întreabă servicii de urmărire în schimb și afișează istoricul pe care îl au acestea, în cuvintele lor.

Cât timp ai coletul deschis, sau în ziua livrării cu notificările pornite, Peek verifică până la fiecare 10 minute, și până la fiecare 2 minute de la 6:00 ora locală în ziua în care coletul iese în livrare (la fiecare 5 minute când verificarea deschide un browser), cât timp trackerul propriu al HuaHan răspunde; altfel la fiecare oră. Prin servicii de urmărire, verifică la fiecare oră, la fiecare 3 ore după o zi liniștită și până la fiecare 15 minute în ziua livrării în aceeași condiție. Când alt curier preia livrarea, Peek trece la ritmul propriu al acelui curier. După două zile fără noutăți (12 ore cât timp coletul arată încă „Label created” sau „Received by carrier”), Peek verifică o dată pe oră. Te poate notifica pentru „Every scan”, „Important steps” sau „Delivery only”. „Important steps” acoperă `Import Customs clearance started`, scanările vamale ale China Post pe care HuaHan le transmite în chineză pentru un colet trimis prin poștă (precum `送交出口海关` și `送交境外进口海关`), tura de livrare, un punct de ridicare și livrarea, nu zborurile și nici propriile scanări de vămuire la export ale HuaHan: vămuirea la export în drum spre aeroport, `快件从转运仓发出至机场，出口报关中（ The package is shipped from the transfer warehouse to the airport Export customs clearance in progress ）`, este un pas de rutină înainte de zbor, iar Peek îl afișează ca „In transit”. Peek nu poate contacta HuaHan sau vânzătorul în numele tău.

## Întrebări despre urmărirea HuaHan

### Ce este HuaHan Logistics (华翰物流)?

Un transportator chinez, numit și HuaHan Express sau 华翰速递, care trimite colete în străinătate. În istoricele păstrate în codul Peek, scanările sale încep la centrul din Shenzhen, urmăresc zborul și vama și se încheie cu scanările curierului local care livrează în țara de destinație.

### Unde pot urmări un colet HuaHan?

În caseta de mai sus, sau pe trackerul propriu al HuaHan de pe hh-exp.cn. Acel tracker îți cere mai întâi să treci o verificare: clicuri în ordine sau o adunare simplă. Folosește întregul număr HHW…YQ.

### Ce înseamnă „Has been given to local carrier” la urmărirea HuaHan?

Vama de import a eliberat coletul, iar HuaHan l-a predat curierului care îl livrează în țara ta. Scanările acelui curier urmează în istoricul HuaHan.

### Cine livrează coletele HuaHan în țara mea?

Un curier local, pe care trackerul HuaHan îl poate numi împreună cu numărul lui pentru colet. Codul Peek arată Sameday într-un istoric și scanări de depozit ale DPD, citește numere USPS, conține date DHL și notificarea CTT Express pentru ultimul segment al unor colete și citește liniile Landmark Global pentru altele, livrarea făcându-se prin rețeaua bpost după predare: verifică trackerul HuaHan sau întreabă vânzătorul.

### Pe cine contactez pentru un colet HuaHan lipsă?

Vânzătorul de la care ai comandat: el a rezervat coletul cu HuaHan. Linia telefonică a HuaHan este în China (400-015-6080).

:::sources
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – taxă vamală de 3 € per articol până la 150 € din 1 iulie 2026, taxă de procesare a Uniunii de 2 € din 1 noiembrie 2026
- [European Commission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 2 € per articol indiferent de valoare, ce reprezintă un articol
- [HuaHan Express: 联系我们 (contact, în chineză)](https://www.hh-exp.com/lxwm) – sediul central din Shenzhen și linia oficială 400-015-6080.
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – nume, forma numărului HHW, trackerul și verificarea prin adunare, formulările scanărilor, codurile și etapele lor, scanările transmise ale curierului local și liniile duble, formularea Sameday, scanările de depozit, încercările și punctul de ridicare DPD, notificarea CTT Express, scanările China Post și ale poștei de destinație în chineză pe linia poștală și etapele lor, liniile transmise ale Landmark Global, predarea către rețeaua bpost și etapele lor, numele și numărul curierului local cu sufixul după liniuță de subliniere și codurile de bare USPS, ceasuri, țara de destinație, lipsa datei de livrare sau a greutății, predarea
:::
