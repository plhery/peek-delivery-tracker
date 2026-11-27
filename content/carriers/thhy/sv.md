---
title: Tianhui Hengyuan (THHY) spårning: AT…TH-nummer & status
description: Spåra ett Tianhui Hengyuan-paket (天汇恒源, THHY) med AT…TH-nummer från Kina: 14-teckens format, skriptade Hualei-rader mot riktiga skanningar och Swiss Post.
slug: tianhui-hengyuan-spara-paket
published: 2026-11-27
updated: 2026-11-27
---

Tianhui Hengyuan (天汇恒源, även skrivet THHY eller TH) är en kinesisk speditör vars 14-teckens fraktsedlar börjar med `AT`, följt av tio siffror och `TH`. Klistra in ditt `AT…TH`-nummer i fältet ovan för att se verkliga skanningar: Peek känner inte till någon webbplats för företaget, och efter företagets egna första skanningar lägger dess Hualei-spårning till skriptade rader som inte visar var ditt paket faktiskt befinner sig.

## Tianhui Hengyuan spårningsnummer: hur det ser ut

Tianhui Hengyuans fraktsedlar består av `AT`, tio siffror och `TH`, 14 tecken totalt. De kommer från en nummerregel i Hualei (华磊), speditörsplattformen som spårningen körs på. Med påhittade siffror:

| Nummer | Vad det är |
| --- | --- |
| `AT4829173654TH` | `AT`, tio siffror och `TH`: formatet för Tianhui Hengyuans fraktsedlar. Peek kontrollerar det mot Tianhui Hengyuans spårning (nedan). |
| `AT482917367TH` | Bara nio siffror. Två bokstäver, nio siffror och `TH` är formatet för ett internationellt postnummer från Thailand Post, inte Tianhui Hengyuan. |
| `AT48291736542TH` | Elva siffror: ingen transportör i Peeks katalog använder denna form. Kontrollera om det är en siffra för mycket. |

Mellanslag och små bokstäver spelar ingen roll när du klistrar in numret i Peek.

**Var du hittar det.** Säljaren bokar paketet hos Tianhui Hengyuan och skickar med numret i leveransuppgifterna. Hittar du det inte, se [hur du hittar ett spårningsnummer](guide:find-tracking-number) (på engelska).

**En butiksreferens.** Tianhui Hengyuans spårning hittar även en order via säljarens egen referens, inskriven exakt som säljaren skrev den inklusive bindestreck.

**Ingen länk att dela.** Spårningen svarar på en numerisk serveradress över okrypterad HTTP, inte på ett webbdomännamn. Peek länkar inte dit: en okrypterad HTTP-länk skulle skicka ditt nummer i klartext.

## Förklaring av Tianhui Hengyuans spårningsstatusar

Spårningens egna rader är få, och två av dem står bara på kinesiska. I den ordning paketet får dem:

| Tianhui Hengyuan skriver | Vad det betyder för dig | Peek visar |
| --- | --- | --- |
| `货物电子信息已经收到` | ”Den elektroniska fraktinformationen har tagits emot”. Plattformen skriver denna rad av sig själv för en order utan skanningar: säljaren har skapat ordern, men Tianhui Hengyuan har ännu inte skannat paketet. | "Label created" |
| `Arrived at the receiving point` | Tianhui Hengyuan har paketet: första fysiska skanningen i deras händer. | "Received by carrier" |
| `货物离开操作中心` | ”Godset lämnar driftcentret”: paketet har lämnat Tianhui Hengyuans driftcenter. | "In transit" |

**Skriptade rader.** Efter dessa fortsätter Tianhui Hengyuans spårning med rader som liknar China Posts skanningar, samt engelska rader om slutsträckan. De kommer från Hualei-plattformens produkt för anpassade spårningar (`轨迹定制`, ”customised track”), som skriver dem efter ett manus. De är inga riktiga skanningar av ditt paket. Några exempel i översättning:

| Tianhui Hengyuan skriver | Läses som |
| --- | --- |
| `已收取邮件` | Post mottagen |
| `邮件已完成分拣` | Sortering slutförd |
| `送交出口` | Överlämnad till exporttull |
| `飞机起飞` | Flyget har avgått |
| `到达寄达地` | Ankommit till mottagarlandet |
| `送交境外进口海关` | Överlämnad till utländsk importtull |

De engelska raderna omfattar `Transporting to the next facility`, `Packages ready for delivery on the next working day` och `Packages in transit for delivery`. Du känner igen de kinesiska på tidsstämplarna: alla faller på samma sekund i minuten, med jämna multiplar av sex minuter emellan. Ta därför inte `送交境外进口海关` som bevis på att paketet nått tullen i ditt land, eller `Packages in transit for delivery` som bevis på att det är på väg ut till dig. Andra spårningssajter som kopierar Tianhui Hengyuans data kan visa samma rader.

**Tider.** Varje klockslag i Tianhui Hengyuans spårning anges i Pekingtid, även för rader utomlands, utan tidszon. Klockan 16:00 där är morgon i London och mitt i natten i New York.

## Vem levererar Tianhui Hengyuan-paket

Peek vet inte vem som levererar Tianhui Hengyuans paket. Det enda levererande företaget dess kod kan lämna över till är [Swiss Post](carrier:swiss-post), och bara när Tianhui Hengyuans post nämner Swiss Post och innehåller ett Swiss Post-nummer (mer nedan). Tianhui Hengyuans poster anger dock inget postnummer, så de skriptade raderna kan inte stämmas av mot något postverks spårning. Ett Swiss Post-nummer är två bokstäver, nio siffror och `CH`, eller 18 siffror som börjar med `98` eller `99`.

Peek hittade inga leveranstider från Tianhui Hengyuan och inga regler för leveransförsök, ombud eller liggtid: det är det levererande bolagets regler som gäller. Fråga säljaren vilket företag som levererar din order och be om dess nummer. I Schweiz täcker Swiss Post-sidan avier och hämtning. Om du blir ombedd att betala importavgifter, se [paket i tullen](guide:customs) (på engelska).

## Tianhui Hengyuan-paket kommer inte fram eller spårningen står stilla

**Endast `货物电子信息已经收到`.** Säljaren har skapat ordern, men Tianhui Hengyuan har inte skannat paketet. Om inget nytt kommer efter några dagar, fråga säljaren om paketet verkligen skickats.

**Stannat efter `货物离开操作中心`.** Det är den sista av Tianhui Hengyuans egna rader. Det som spårningen visar efteråt kommer från manuset, som Peek håller borta från skanningarna, så tystnad i Peek betyder inte att paketet stannat. Fråga säljaren vilket bolag som levererar och be om dess nummer, och lägg till det som ett eget paket i Peek. Mer i [varför spårningen slutar uppdateras](guide:tracking-not-updating) (på engelska).

**Spårningen visar paketet längre fram än Peek.** Peek håller tillbaka de raderna eftersom de är skriptade (ovan) eller skrivna i förväg (nedan). Gå efter butikens angivna leveransdatum och hör av dig till säljaren när det har passerat.

**Inget hittades.** För ett okänt nummer visar Tianhui Hengyuans spårning en rad med ditt nummer under `参考号` (referens) och alla andra fält tomma. Peek visar "Waiting for Tianhui Hengyuan" och ber dig två dagar senare — om inget annat svarat — kontrollera numret, välja en annan transportör eller ta bort paketet. Jämför numret tecken för tecken med säljarens meddelande.

**Levererat men inget kom fram.** En leveransskanning kommer från det levererande bolaget, inte från Tianhui Hengyuans egna rader: fråga det bolaget först och kontakta sedan säljaren. Se [levererat men inte mottaget](guide:delivered-not-received) (på engelska).

**Borttappat eller returnerat.** Säljaren bokade paketet hos Tianhui Hengyuan, så vänd dig till säljaren eller marknadsplatsen för återbetalning eller ny sändning.

## Tianhui Hengyuan kontaktnummer och kundtjänst

Peek hittade inga kontaktuppgifter för Tianhui Hengyuan: det känner inte till någon webbplats för företaget och inget telefonnummer eller e-postadress för mottagare. Säljaren bokade transporten och är den du frågar om din order, och marknadsplatsen om säljaren inte svarar.

## Spåra ett Tianhui Hengyuan-paket med Peek

[Peek](/) kan inte vara säker på Tianhui Hengyuan enbart utifrån numrets form: `AT`, tio siffror och `TH` är bara en svag träff. Klistra in det ändå: Peek följer över 4 500 transportörer. Medan du lägger till paketet frågar Peek Tianhui Hengyuans spårning ("Checking tracking services…") och visar "Tianhui Hengyuan has this parcel." när spårningen känner till numret. Annars trycker du "Change" och väljer Tianhui Hengyuan (att söka på `天汇恒源` eller `THHY` hittar den också), eller använder "Change carrier" på ett redan tillagt paket. Gör likadant för en säljarreferens: då visar Peek orderns eget nummer med "Tianhui Hengyuan tracks this as". Peek tar dock bort bindestreck, så en referens med bindestreck hittas inte via Peek — be säljaren om `AT…TH`-numret.

Peek läser Tianhui Hengyuans egen spårning och visar status, varje rad med sin plats samt destinationslandet när posten ger det. De två kinesiska raderna visas på den engelska som plattformens andra speditörer använder: `Goods leave the operation center` och `The electronic cargo information has been received`. Tider läses i Pekingtid och visas i din enhets tidszon. Peek utelämnar namn, adress, postnummer, vikt, avgifter, signatur och leveransbevis som posten kan innehålla, och visar inget förväntat leveransdatum eftersom Tianhui Hengyuan inte ger något.

Från Tianhui Hengyuans spårning håller Peek tillbaka de skriptade raderna, varje rad som skrivits mer än tolv timmar före tiden den bär (eller i samma sekund som en sådan rad, mer än sex timmar före sin egen tid), samt varje rad daterad efter kontrolltillfället i Pekingtid. Den går efter raderna den behåller, inte efter postens sammanfattningsstatus. En order där alla rader hålls tillbaka har ännu ingen historik i Peek och visar "Waiting for Tianhui Hengyuan" utan uppmaning att kontrollera numret: Peek räknar förhandsskrivna rader som att Tianhui Hengyuan har ordern (precis som en sändning utan skanning) och fortsätter fråga spårningen som vid enbart etikett. Tills paketet levererats eller returnerats inleds historiken med "Tianhui Hengyuan’s site lists the whole route in advance, delivery included. Those steps aren’t real scans." och listar de skriptade och förhandsskrivna raderna i grått under "Show them", varje rad med "Predicted for" eller "Written in advance for" och dess dag. En sådan rad räknas aldrig som en skanning, ändrar ingen status och skickar ingen notis. Oavsett om Peek håller tillbaka vissa eller alla rader sätter den också åt sidan andra spårningstjänsters kopior av dem. När spårningen inte går att nå vänder sig Peek dock till spårningstjänster, och deras kopior kan innehålla de skriptade raderna.

**Swiss Post.** Peek lämnar bara över ett paket när Tianhui Hengyuans post anger Swiss Post som kanal, har ett Swiss Post-nummer och inte anger någon destination utanför Schweiz och Liechtenstein. Paketet visar då "Delivery with Swiss Post" med numret (eller "Announced to Swiss Post, not received yet" medan Swiss Post bara har förhandsanmälan), och när Swiss Post har en minst lika ny skanning följer Peek paketet där och behåller historiken. Annars stannar Peek hos Tianhui Hengyuan: lägg till det lokala numret från säljaren som ett eget paket.

Medan paketet är öppet i Peek kontrollerar Peek upp till var 10:e minut, och varje timme när Tianhui Hengyuan tystnat (12 timmar utan något bortom första raden eller skanningen, eller två dagar utan nytt senare); i övrigt varje timme. Ett väntande paket eller ett på "Label created" utan nytt på sju dagar kontrolleras bara var 6:e timme, om du inte har det öppet eller det väntas den dagen. Tianhui Hengyuans egna rader är enbart tidiga steg, så endast "Every scan" aviserar om dem; "Important steps" och "Delivery only" väntar på senare steg. Peek kan inte kontakta Tianhui Hengyuan eller säljaren åt dig.

## Frågor om Tianhui Hengyuan spårning

### Vad är Tianhui Hengyuan (THHY / 天汇恒源)?

En kinesisk speditör, `天汇恒源` (även THHY eller TH), vars spårning körs på speditörsplattformen Hualei. Peeks katalog listar två företagsnamn: `深圳市天汇恒源供应链有限公司` och `广州市天慧恒源供应链有限公司`. Fraktsedlarna har formatet `AT`, tio siffror och `TH`.

### Tillhör ett AT…TH-spårningsnummer Thailand Post eller Tianhui Hengyuan?

Inte med tio siffror. `AT`, tio siffror och `TH` är Tianhui Hengyuans format. Thailand Posts internationella nummer har nio siffror: två bokstäver, nio siffror och `TH`.

### Varför visar Tianhui Hengyuans spårning fler steg än Peek?

För att Tianhui Hengyuans spårning efter sina första egna rader visar rader som en anpassad spårningsprodukt skriver efter ett manus. De liknar China Posts skanningar men är inga riktiga skanningar av ditt paket, så Peek håller dem utanför skanningarna och listar dem i grått under "Show them".

### Vem levererar Tianhui Hengyuan-paket i mitt land?

Peek vet inte, och Tianhui Hengyuans poster anger inget postnummer: fråga säljaren vilket företag som levererar och be om dess spårningsnummer. Enda levererande företaget Peek kan lämna över till är Swiss Post när posten anger det med ett Swiss Post-nummer.

### Vem kontaktar jag om mitt Tianhui Hengyuan-paket inte kommer fram?

Säljaren du beställde från, eftersom säljaren bokade transporten hos Tianhui Hengyuan. Peek känner inte till någon webbplats eller kontaktuppgifter för Tianhui Hengyuan.

:::sources
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – names and company names, Hualei platform, the tracker on a bare address over plain HTTP and no link published, AT…TH number shape and its weak match, search by number or reference with its dashes, the forwarder's and the platform's own lines and their English, the custom track's scripted lines and how to spot them, Beijing clock, the answer for an unknown number, no post number on the record, the Swiss Post handover rule and Swiss Post's number shapes, what Peek shows, holds back, leaves out and hands over
:::
