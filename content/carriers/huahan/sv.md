---
title: HuaHan Logistics spårning: HHW…YQ-nummer och leverans
description: Spåra ett HuaHan Logistics-paket (华翰物流) från Kina: hur ett HHW…YQ-nummer ser ut, vad skanningarna betyder och hur du ser vem som levererar i ditt land.
slug: huahan-logistics-spara-paket
published: 2026-11-28
updated: 2026-11-28
---

HuaHan Logistics (华翰物流, även HuaHan Express eller 华翰速递) är ett kinesiskt fraktbolag som flyger paket ut ur Kina och överlämnar dem till ett lokalt fraktbolag i mottagarlandet, som levererar dem. Om en butik skickade din beställning med HuaHan, leta efter ett nummer som börjar med HHW och slutar med YQ och klistra in det i rutan ovan. HuaHans egen spårare finns på webbplatsen hh-exp.cn och kan namnge bolaget som levererar nära dig, tillsammans med det bolagets nummer för ditt paket.

## Spårningsnummer hos HuaHan: hur det ser ut

Peek känner till HuaHans fraktsedlar som HHW, två bokstäver, tio siffror och YQ, totalt 17 tecken. Med påhittade siffror:

| Nummer | Vad det är |
| --- | --- |
| `HHWCN4829105736YQ` | HHW, två bokstäver, tio siffror och YQ: en HuaHan-fraktsedel. |
| `HHWCN482910573YQ` | Bara nio siffror: inte HuaHans upplägg. Kontrollera om en siffra saknas. |
| `WNBAA4829105736YQ` | Slutar också på YQ men börjar med WNB: ett [WanbExpress](carrier:wanbexpress)-nummer. |
| `JXCAA4829105736YQ` | JXC först: ett [Juxi Express](carrier:juxi-express)-nummer. |

Många kinesiska speditörer avslutar sina fraktsedlar med YQ, var och en med sina egna bokstäver i början: HHW är det som utmärker HuaHan, och inget annat fraktbolag i Peeks katalog använder det. Mellanslag, bindestreck och gemener spelar ingen roll när du klistrar in numret i Peek.

**Var du hittar det.** Butiken lämnar numret i leveransmejlet eller på din beställningssida. Hittar du det inte, se [hur du hittar ditt spårningsnummer](guide:find-tracking-number) (på engelska).

**Ett andra nummer.** HuaHans spårare kan även visa det lokala fraktbolagets eget nummer för paketet. HuaHan lägger ibland till ett tillägg efter ett understreck som fraktbolagets nummer inte har: utelämna det när du söker på det bolagets webbplats.

## HuaHans spårningsstatusar förklarade

HuaHans spårare listar varje skanning med tidpunkt, text och plats, nyast först. Dess egna skanningar är på engelska, vissa med kinesiska först. Här är de i den ordning Peeks kod listar dem:

| HuaHan skriver | Vad det betyder för dig | Peek visar |
| --- | --- | --- |
| `快件电子信息已经收到 Shipment information received`, `Shipment information received` | Butiken har skickat paketuppgifterna till HuaHan. HuaHan har inte paketet ännu. | "Label created" |
| `快件到达作业中心 Shipment arrived at facility`, `Has entered the warehouse` | HuaHans anläggning i Kina har paketet: i de historiker Peeks kod innehåller, i Shenzhen. | "Received by carrier" |
| `快件操作完成 Shipment departed from facility` | Behandlat och på väg ut från den anläggningen. | "In transit" |
| `Transfer to Origin airport` | På väg till flygplatsen. | "In transit" |
| `Export customs clearance completed` | Exporttullklareringen på vägen ut är klar. Inte ditt lands tull. | "In transit" |
| `Departed from airport of Origin` | Flyget har avgått. | "In transit" |
| `Import Customs clearance started` | Tullen i mottagarlandet har paketet. | "At customs" |
| `Arrived at destination airport` | Flyget har landat i mottagarlandet. | "In transit" |
| `Import Customs clearance completed` | Importtullen har släppt igenom det. | "In transit" |
| `Has been given to local carrier` | HuaHan har överlämnat det till fraktbolaget som levererar det. Det bolagets egna rader följer. | "In transit" |

**Det lokala fraktbolagets rader.** Efter överlämningen vidarebefordrar HuaHans spårare det lokala fraktbolagets skanningar. Peek läser dem via HuaHans kod för steget, där sådan finns, eller via ordalydelsen. Formuleringar som Peeks kod har stött på och tillskriver det bolaget:

| Efter överlämningen | Vad det betyder för dig | Peek visar |
| --- | --- | --- |
| `Ready to Ship, Parcel received and processed` | Det lokala fraktbolaget har paketet. | "In transit" |
| `The parcel is in the central warehouse waiting to be processed.`, `In Transit, Shipment arrived to sorting centre / Shipment in the sorting centre`, `In Transit, Shipment remained at sorting hub` | På det lokala bolagets lager eller sorteringscentral, eller fortfarande kvar där. | "In transit" |
| `In Transit, Arrived to HU HUB`, `In Transit, Parcel has entered the Linehaul network.` | På det lokala bolagets nav i Ungern och därefter i rörelse mellan dess nav. | "In transit" |
| `In Transit, Picked Up` (följt av DPD:s tyska `Einrollung`), `In Transit, Scanned By DPD At parcel delivery centre.`, `In Transit, At parcel delivery centre.` | På DPD:s utkörningsdepå: ankomstskanningen där, inte en upphämtning hos dig, och sedan en skanning för varje dag paketet väntar där. | "In transit" |
| `Out for Delivery, Parcel is at courier, delivery expected soon.` | Ute på utkörningsrundan. | "Out for delivery" |
| `The courier will deliver the parcel soon. Additional details are available in the SAMEDAY app, email or SMS.` | Ute på utkörningsrundan, med orden från [Sameday](carrier:sameday), en kurir i Rumänien och Bulgarien. | "Out for delivery" |
| `Delivery Attempted`, `Delivery Attempted, Unfortunately we have not been able to deliver your parcel. Pickup parcelshop delivery after first delivery attempt.` | DPD försökte leverera utan framgång. Med den längre raden går paketet vidare till ett ombud. | "Delivery attempted" |
| `Awaiting Pickup, Parcel was deposited in Pickup parcel station` | Efter det försöket har DPD lämnat paketet hos ett utlämningsställe för dig att hämta. | "Ready for pickup" |
| `The parcel has been successfully delivered.` | Levererat. | "Delivered" |

HuaHan sorterar det lokala bolagets skanningar för utkörning, ombud och leverans under egna koder, och Peek visar dem som "Out for delivery", "Ready for pickup" eller "Delivered", såvida inte orden anger ett annat steg: HuaHan lägger även USPS avfärd från en distributionscentral under sin kod för utkörning.

**Två rader som förvirrar.** `We will pick up your parcel from the sender soon.` kan dyka upp nära starten, redan innan HuaHan har fått paketet i Kina: det är det lokala fraktbolagets meddelande om att det fått förhandsbesked om paketet, inte en hämtning hos dig. Peek visar "Label created". `The parcel was redirected. As soon as it is in the couriers possession, it will be processed as requested.` är det bolaget som noterar en omdirigering du bett om, också skriven medan paketet fortfarande är i Kina. Det flyttar inget och Peek ger det ingen egen status.

**Uppgifter, inte skanningar.** `The instruction data for this shipment have been provided by the sender to DHL electronically.`, ibland utan punkt, är DHL:s registrering av paketets uppgifter för den sista sträckan, vidarebefordrad innan HuaHan har paketet i Kina eller efter att det lämnat Kina. `货物电子信息已经收到` (elektroniska uppgifter om varorna mottagna), på kinesiska, är beställningens data och kan komma efter att HuaHan redan har paketet. Inget av dem är en förflyttning: Peek visar "Label created" eller behåller den status paketet redan hade.

**En skanning, två rader.** HuaHans spårare kan lista en av det lokala bolagets skanningar två gånger vid samma tidpunkt: med det bolagets ord och plats, samt utan plats i HuaHans allmänna ordalag, som `Delivered, Parcel was delivered successfully`. Peek behåller raden med platsen.

**Med post.** HuaHan kan också skicka ett paket via China Post. Spåraren vidarebefordrar då China Posts skanningar på kinesiska, med kontoret i 【】, och senare mottagarpostens skanningar så som China Post kopierar dem. Peek visar dem på kinesiska, precis som HuaHan skickar dem vidare:

| HuaHan skickar vidare | Vad det betyder för dig | Peek visar |
| --- | --- | --- |
| `中国邮政已收取邮件` | China Post har tagit emot paketet från HuaHan: härifrån går det med post. | "In transit" |
| `邮件已在【…】完成分拣，准备发出`, `邮件到达【…】`, `邮件离开【…】，正在发往【…】`, `邮件离开【…】，正在发往下一站` | Sorterat vid, anlänt till eller avgående från ett China Post-kontor i Kina, angivet i 【】. | "In transit" |
| `送交出口海关` | Överlämnat till exporttullen i Kina. Inte ditt lands tull. | "At customs" |
| `出口海关/放行` | Exporttullen har släppt det. | "In transit" |
| `已交承运商运输` | Överlämnat till flygbolaget som flyger det utomlands. | "In transit" |
| `到达寄达地`, `到达寄达地处理中心`, `到达境外经转局` | Anlänt i mottagarlandet, till dess posts behandlingscentral eller till ett transitkontor på vägen. | "In transit" |
| `境外进口海关留存待验(等待提交给边境机构/安全部门)` | Hålls för ditt lands importtull; parentesen säger att det väntar på att läggas fram för gräns- eller säkerhetsmyndigheterna. | "At customs" |
| `境外进口互换局留存`, `送交境外进口海关` | Kvarhålls vid mottagarpostens utväxlingskontor, sedan överlämnat till importtullen. | "At customs" |
| `寄达地海关允许进口（无需缴税）` | Importtullen har släppt in det utan tull att betala. | "In transit" |
| `离开【…】处理中心`, `已到达【…】投递局` | Lämnade behandlingscentralen i landet angivet i 【】 och anlände sedan till utdelningskontoret. | "In transit" |

**Via Landmark Global.** För vissa paket vidarebefordrar HuaHans spårare raderna från [Landmark Global](carrier:landmark-global) på engelska. De första två kommer innan HuaHan har paketet i Kina; de övriga följer efter Landmark Globals överlämning till [bpost](carrier:bpost):s nätverk, som levererar:

| HuaHan vidarebefordrar | Vad det betyder för dig | Peek visar |
| --- | --- | --- |
| `Shipment Data Uploaded` | Landmark Globals registrering av avsändarens uppgifter. Ingen förflyttning. | "Label created" |
| `Processed` | Landmark Globals bearbetning, i samma minut som den registreringen. Inte ett mottagande av paketet: en notis, och Peek behåller steget före den. | Steget före den |
| `Incoming scan at facility` | En depå har paketet efter överlämningen till bposts nätverk. | "In transit" |
| `Outgoing scan at facility` | Det har lämnat den depån. Utkörningsrundan kan fortfarande ligga dagar framåt. | "In transit" |
| `Out for delivery (On the way)` | Ute på utkörningsrundan. | "Out for delivery" |
| `Delivery attempt (Delivery not possible)` | En utkörningsrunda som inte lyckades leverera det. | "Delivery attempted" |

HuaHan lägger `Outgoing scan at facility` under sin kod för utkörning, men orden anger avgång från en depå, så Peek visar "In transit".

## Vem levererar HuaHan-paket

Efter `Has been given to local carrier` levererar ett fraktbolag i mottagarlandet paketet, och HuaHans spårare kan namnge det och ge dess nummer.

**Vad Peeks kod visar.** En HuaHan-historik visar att Sameday levererar: dess utkörningsrad hänvisar till SAMEDAY-appen. Peeks kod räknar även med [USPS](carrier:usps)-nummer från HuaHan: när HuaHan anger USPS långa etikettstreckkod behåller Peek USPS eget spårningsnummer ur den. [DHL](carrier:dhl) bokas för den sista sträckan på vissa paket: dataraden som citeras ovan är dess registrering av avsändarens uppgifter. DPD levererar en del: dess depåskanningar, såsom `In Transit, Scanned By DPD At parcel delivery centre.`, finns i tabellen ovan. [CTT Express](carrier:ctt-express) aviseras för den sista sträckan på andra: HuaHans spårare skickar vidare meddelandet `Pendiente de recepción en CTT Express` ("väntar på mottagande hos CTT Express") minuter efter HuaHans ankomstskanning i Kina, enligt Madridtid, och Peek visar "Label created". Andra går via [Landmark Global](carrier:landmark-global): HuaHans spårare vidarebefordrar dess rader från registreringen av avsändarens uppgifter till utkörningen, och efter Landmark Globals överlämning levererar bposts nätverk (se "Via Landmark Global" ovan). Utöver dessa binder koden inget bolag till något land: Peek går på det bolag HuaHan anger för ditt paket.

**Leveransförsök och hämtning.** Försök, avier och var ett missat paket väntar, och hur länge, följer det lokala fraktbolagets regler: fråga det bolaget, med dess nummer, om att boka om en leverans. Se [missad leverans](guide:missed-delivery) och [outlösta paket](guide:uncollected-parcel) (på engelska).

**Tull i EU.** Varor köpta online utanför EU beläggs med 3 € i tull per artikel för paket värda upp till 150 € sedan 1 juli 2026, och en unionshanteringsavgift på 2 € per artikel oavsett värde sedan 1 november 2026. En artikel utgörs av varor som delar tulltaxenummer, beskrivning och, där det krävs, ursprung. Peek kunde inte kontrollera om HuaHan tar ut dessa av säljaren i förväg: fråga butiken om ditt pris inkluderade dem. Utanför EU beror vad du betalar på ditt land. Se [paket som hålls i tullen](guide:customs) (på engelska).

## HuaHan-paket kommer inte fram eller spårningen står still

**Numret hittades inte.** När HuaHan inte har några uppgifter om numret svarar spåraren 没有找到记录 ("inga uppgifter hittades"), och Peek visar "Waiting for HuaHan Logistics" och fortsätter kontrollera. Om HuaHan två dagar senare fortfarande inte känner till numret och inget annat har svarat på det, visar Peek "After two days, the carrier still doesn’t know this number. Check it, choose another carrier or delete the parcel." Kontrollera numret tecken för tecken mot säljarens meddelande: HHW, två bokstäver, tio siffror, YQ. Kommer inget efter några dagar, fråga säljaren om paketet verkligen skickades.

**Fast på `Shipment information received`.** Butiken har bokat försändelsen, men HuaHan har inte skannat paketet ännu. Fråga butiken när den lämnade över paketet.

**Fast på `Import Customs clearance started`.** Tullen i ditt land har paketet. Om du ombeds betala importavgifter, se [paket som hålls i tullen](guide:customs) (på engelska); blir det kvar där länge, säg till säljaren.

**Inget efter `Has been given to local carrier`.** Det lokala fraktbolaget har paketet, och dess egen spårning kan ligga före HuaHans. Hitta dess namn och nummer i HuaHans spårare, eller på Peeks paketkort när Peek känner till det bolaget, eller fråga säljaren vilket bolag som levererar.

**Ingen uppdatering på flera dagar.** När ett paket som fortfarande är på väg inte har fått någon ny skanning på fyra dagar flaggar Peek det med dagen för senaste skanning ("No tracking update since Mon 12 Oct") och noterar att paket ofta blir tysta på långa flygningar, i tullen eller över en helg; efter tre veckor utan nytt ber den dig fråga säljaren i stället. Se [varför spårning slutar uppdateras](guide:tracking-not-updating) och [spåra paket från Kina](guide:tracking-from-china) (på engelska).

**Levererat, men inget kom.** Kolla var det lämnades och fråga grannar, sedan det lokala bolaget och säljaren. Se [levererat men inte mottaget](guide:delivered-not-received) (på engelska).

**Försvunnet eller returnerat.** Säljaren bokade paketet med HuaHan, så säljaren eller marknadsplatsen är den du ber om återbetalning eller ett nytt paket. Se [förlorade paket](guide:lost-parcel) och [returnerat till avsändaren](guide:returned-to-sender) (på engelska).

## Kontaktnummer och kundtjänst hos HuaHan

På sin webbplats ([hh-exp.com/lxwm](https://www.hh-exp.com/lxwm)) anger HuaHan en hotline i **Kina**, 400-015-6080, utan publicerade öppettider, samt huvudkontoret i Shenzhen; bolaget anger ingen supportmejl för mottagare. Säljaren bokade paketet med HuaHan och är den du frågar om din beställning, och marknadsplatsen om säljaren inte svarar. För själva leveransen, fråga det lokala fraktbolaget.

## Spåra ett HuaHan-paket med Peek

[Peek](/) känner genast igen HuaHan Logistics utifrån ett HHW…YQ-nummer ("Detected carrier") och frågar HuaHans spårare enbart med hela det numret: inget konto, postnummer eller telefonnummer behövs. Har du ett ordernummer, be säljaren om HHW…YQ-fraktsedeln. Har du bara det lokala bolagets nummer, klistra in det i stället: Peek följer fler än 4 500 fraktbolag.

Peek visar statusen, varje skanning med sin plats och mottagarlandet i HuaHans post. HuaHans spårare skriver ut tider utan tidszon. Peek läser HuaHans egna skanningar enligt klockan på platsen eller för den sträcka paketet var på: Pekingtid i Kina. Det lokala bolagets skanningar efter `Has been given to local carrier` läses enligt ditt lands klocka när landet har en tidszon. Varje tid visas i din enhets tidszon. HuaHan ger ingen beräknad leveransdag eller vikt, så Peek visar ingetdera.

När HuaHan anger ett lokalt fraktbolag som Peek känner till, med ett nummer i det bolagets form, säger paketkortet det (till exempel "Delivery with USPS", eller "Announced to USPS, not received yet" medan det bolaget bara har förhandsbeskedet om paketet utan någon egen skanning) och listar det bolagets nummer efter HuaHans. Peek frågar då det bolaget också. Om HuaHan anger ett internationellt försändelsenummer (två bokstäver, nio siffror, två bokstäver) utan något bolag Peek känner till, frågar Peek mottagarlandets post; för ett annat nummer, det enda bolag som formen pekar på. När det bolaget visar en skanning minst lika färsk som HuaHans nyaste följer Peek paketet där och behåller HuaHans historik. Ger HuaHan inget nummer för det lokala bolaget stannar Peek hos HuaHans spårare, som fortsätter skicka vidare det bolagets skanningar.

När HuaHans spårare inte går att nå frågar Peek spårningstjänster i stället och visar den historik de har, med deras ord.

Medan du har paketet öppet, eller på leveransdagen med aviseringar påslagna, kontrollerar Peek upp till var 10:e minut, och upp till var 2:a minut från 06:00 lokal tid den dag paketet går ut för leverans (var 5:e minut när kontrollen öppnar en webbläsare), medan HuaHans egen spårare svarar; annars varje timme. Via spårningstjänster är det varje timme, var 3:e timme efter en tyst dag och upp till var 15:e minut på leveransdagen under samma villkor. När ett annat bolag tar över leveransen följer Peek det bolagets egen takt. Efter två dagar utan nytt (12 timmar medan paketet fortfarande visar "Label created" eller "Received by carrier") kontrollerar Peek en gång i timmen. Peek kan avisera dig om "Every scan", "Important steps" eller "Delivery only". "Important steps" omfattar `Import Customs clearance started`, de tullskanningar från China Post som HuaHan skickar vidare på kinesiska för ett paket som går med post (såsom `送交出口海关` och `送交境外进口海关`), utkörningen, ett ombud och leveransen, men inte flyg eller HuaHans egna exportklareringsskanningar: exportklareringen på väg till flygplatsen, `快件从转运仓发出至机场，出口报关中（ The package is shipped from the transfer warehouse to the airport Export customs clearance in progress ）`, är ett rutinsteg före flyget, och Peek visar den som "In transit". Peek kan inte kontakta HuaHan eller säljaren åt dig.

## Frågor om HuaHan-spårning

### Vad är HuaHan Logistics (华翰物流)?

Ett kinesiskt fraktbolag, även kallat HuaHan Express eller 华翰速递, som skickar paket utomlands. I de historiker Peeks kod innehåller börjar skanningarna på anläggningen i Shenzhen, följer flyget och tullen och slutar med skanningarna från det lokala bolag som levererar i mottagarlandet.

### Var kan jag spåra ett HuaHan-paket?

I rutan ovan, eller på HuaHans egen spårare på hh-exp.cn. Den spåraren ber dig först klara en kontroll: klicka i ordning eller räkna ut en enkel summa. Använd hela HHW…YQ-numret.

### Vad betyder "Has been given to local carrier" på HuaHan-spårning?

Importtullen har släppt igenom paketet och HuaHan har överlämnat det till det bolag som levererar det i ditt land. Det bolagets skanningar följer i HuaHans historik.

### Vem levererar HuaHan-paket i mitt land?

Ett lokalt fraktbolag, som HuaHans spårare kan namnge tillsammans med dess nummer för paketet. Peeks kod visar Sameday i en historik och DPD:s depåskanningar, läser USPS-nummer, innehåller DHL:s data och CTT Express meddelande för den sista sträckan på vissa paket, och läser Landmark Globals rader för andra, där bposts nätverk levererar efter överlämningen: kolla HuaHans spårare eller fråga säljaren.

### Vem kontaktar jag om ett saknat HuaHan-paket?

Säljaren du beställde av: den bokade paketet med HuaHan. HuaHans egen hotline ligger i Kina (400-015-6080).

:::sources
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 3 € tull per artikel upp till 150 € sedan 1 juli 2026, 2 € unionshanteringsavgift sedan 1 november 2026
- [European Commission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 2 € per artikel oavsett värde, vad en artikel är
- [HuaHan Express: 联系我们 (kontakt, på kinesiska)](https://www.hh-exp.com/lxwm) – huvudkontor i Shenzhen och den officiella hotlinen 400-015-6080.
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – namn, HHW-nummerform, spåraren och dess summakontroll, skanningstexter, koder och deras faser, det lokala bolagets vidarebefordrade skanningar och dubbla rader, Samedays text, DPD:s depåskanningar, försök och ombud, CTT Express meddelande, China Posts och mottagarpostens skanningar på kinesiska på postlinjen och deras faser, Landmark Globals vidarebefordrade rader, överlämningen till bposts nätverk och deras faser, det lokala bolagets namn och nummer med understreckstillägget och USPS-streckkoder, klockor, mottagarland, ingen leveransdag eller vikt, överlämningen
:::
