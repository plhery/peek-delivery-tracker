---
title: Spåra paket från GZYL: YL…YQ-nummer, status och kontakt
description: Spåra ett paket från GZYL (广州云链, Guangzhou Yunlian): YL…YQ-format, statusar, varför Delivered kan vara förprogrammerat, tull i EU och kontakt.
slug: gzyl-spara-paket
published: 2026-11-27
updated: 2026-11-27
---

GZYL (广州云链, Guangzhou Yunlian, 广州云链货运代理有限公司) är en speditör i Guangzhou i Kina vars fraktsedelsnummer består av `YL`, tio siffror och `YQ`. Klistra in ditt `YL…YQ`-nummer i fältet ovan för att följa paketet och filtrera bort förprogrammerade förutsägelser: GZYL:s spårning (länkad via `小包查询` på den kinesiska webbplatsen yunlianhouyun.com, på en ren IP-adress utan domännamn) kan skriva ett pakets hela tidslinje i förväg, så en `Delivered`-rad där är inte alltid en riktig leveransskanning.

## Spårningsnummer hos GZYL: så ser det ut

GZYL:s fraktsedelsnummer består av `YL`, tio siffror och `YQ`, totalt 14 tecken. Exempel med påhittade siffror:

| Nummer | Vad det är |
| --- | --- |
| `YL4829105736YQ` | `YL`, tio siffror och `YQ`: GZYL:s standardformat. Peek kontrollerar det hos GZYL:s spårning. |
| `YL7384829105YQ` | `YL738`, sju siffror och `YQ`: med denna form känner Peek igen GZYL direkt. |
| `YL482910573YQ` | Bara nio siffror: inte GZYL:s format. Kontrollera om en siffra saknas. |
| `GZXH4829105736YQ` | `GZXH`, tio siffror och `YQ`: [Xinghe Freight](carrier:xinghe-freight) (星和货运), en annan speditör som kör på GZYL:s spårning med samma textrader. |
| `JXCAA4829105736YQ` | Slutar också på `YQ`, som många kinesiska speditörer, men börjar med `JXC`: ett nummer från [Juxi Express](carrier:juxi-express). |

Mellanslag och små bokstäver spelar ingen roll när du klistrar in numret i Peek.

**Var du hittar numret.** Säljaren bokar frakten hos GZYL och ger dig numret i order- eller leveransbekräftelsen. Hittar du det inte, kontakta säljaren.

## Vad statusarna hos GZYL betyder

GZYL skriver sina steg på engelska, inklusive stavfel. Eftersom Peeks appgränssnitt inte finns på svenska visas Peeks egna etapper på engelska: «Label created» (etikett skapad), «Received by carrier» (mottaget av transportören), «In transit» (under transport), «At customs» (i tullen) och «Delivered» (levererat). I den ordning ett paket brukar få dem:

| GZYL skriver | Vad det betyder för dig | Peek visar |
| --- | --- | --- |
| `Order information received` | Säljaren har skickat orderuppgifterna till GZYL. GZYL har inte paketet fysiskt ännu. | «Label created» |
| `Arrive at the cross border receiving point` | GZYL har paketet: första skanningen i deras händer. | «Received by carrier» |
| `Goods leave the operation center` | Paketet har lämnat GZYL:s driftcenter. | «In transit» |
| `Arrive at the international airport` | På flygplatsen, väntar på sitt flyg. | «In transit» |
| `Departing from the airport` | Flyget har avgått. | «In transit» |
| `Item arrives at destination airport` | Landat i destinationslandet. | «In transit» |
| `local carrier Agent Import customs clearance` | Ett ombud i ditt land klarerar paketet genom importtullen. | «At customs» |
| `The import customs keep it for inspection` | Tullen i ditt land inspekterar paketet. | «At customs» |
| `Customs clearance completed` | Genom tullen och på väg vidare. | «In transit» |
| `Internationaliy Package received at processing center` | Ett bearbetningscenter i ditt land har tagit emot det tullklarerade paketet. Det är inte en ny upphämtning. | «In transit» |
| `Leave the email processing center` | Paketet har lämnat det bearbetningscentret. | «In transit» |
| `Arrival sorting, distribution center` | Vid ett distributionscenter i ditt land, med orten efter texten. | «In transit» |
| `Leaving the sorting center` | Lämnar ett sorteringscenter, med orten efter texten. | «In transit» |
| `Package is on the way` | På väg vidare i ditt land, med en ort. Det betyder inte att paketet är ute för utdelning. | «In transit» |
| `Delivered` | Leveransnotering med orten. Kan vara en plan, inte en skanning: se nedan. | «Delivered», med en notering om fast schema om inte Peek håller tillbaka den (se nedan) |

**Via USA.** Ett paket kan även visa `Los Angeles Processing Center has received it` följt av en stad inom klamrar: ett bearbetningscenter i USA har tagit emot paketet på vägen mot destinationen. Peek visar «In transit».

**En rad på kinesiska.** För en order utan skanningar ännu skriver plattformen själv `货物电子信息已经收到` (elektroniska uppgifter om godset mottagna). Säljaren har skapat ordern, men ingen har skannat paketet. Peek visar raden på kinesiska under «Label created».

**Via China Post.** Ett paket kan också visa `广州处理中心已收寄` med staden inom klamrar: China Posts mottagning på centret i Guangzhou, förmedlad på kinesiska. På Xinghe Freights nummer är `香港处理中心已收寄` samma mottagning i Hongkong. Peek visar båda på kinesiska under «Received by carrier».

**Tider.** Varje klockslag på GZYL:s spårning står i Pekingtid (`UTC+8`), inklusive steg i Sverige, utan angiven tidszon. Vintertid är 20:00 i Peking lika med 13:00 svensk tid (`UTC+1`), och sommartid 14:00 (`UTC+2`).

**Steg skrivna i förväg.** GZYL kan skriva ett pakets hela tidsplan i förväg — från inlämning till sista steget i ditt land — långt före de tider stegen visar, och spårningen visar varje steg först när dess klockslag infaller. Ett sådant steg är en plan, inte en skanning av ditt paket. [Meiteshun](carrier:mtscn), som använder samma plattform, gör likadant. GZYL:s plan slutar sedan med ett `Delivered` som plattformen lägger till i en kvällskörning samma dag som datumet anger: daterat dagen efter planens sista steg under kontorstid i Peking, oavsett vilket land du bor i. Spårningstjänster som kopierar GZYL kan också visa dessa steg, så ett `Delivered` där bevisar inte att paketet kommit fram.

## Vem levererar GZYL-paket i Sverige?

Peek vet inte vilket företag som levererar GZYL-paket i Sverige: GZYL:s steg i destinationslandet anger inget lokalt företag. Det enda leveransföretag som Peek kan lämna över ett GZYL-paket till är [Swiss Post](carrier:swiss-post), i Schweiz och Liechtenstein.

Peek har inte hittat några leveranstider från GZYL och inga regler om leveransförsök, utlämningsställen eller liggetider: allt det avgörs av den lokala leverantören. På GZYL:s spårningssida kan du titta i kolumnen `渠道转单号` (överföringsnummer) och i `跟踪号码` (spårningsnummer) om där står något annat än ditt `YL…YQ`-nummer: endera kan vara det lokala företagets nummer som du kan lägga till som ett eget paket i Peek. Finns inget där, fråga säljaren vilket företag som levererar och be om dess nummer.

**Tull och EU-avgifter.** Sedan 1 juli 2026 tas en schablontull på 3 € per varukategori ut på nätköp från länder utanför EU i försändelser upp till 150 €. Sedan 1 november 2026 tillkommer en EU-hanteringsavgift (Union handling fee) på 2 € per varukategori oavsett paketets värde. Mer i guiden om [paket i tullen](guide:customs) (på engelska).

## Om GZYL-paketet inte kommer fram eller står still

**Bara `Order information received` eller `货物电子信息已经收到`.** Säljaren har skapat ordern, men GZYL har inte skannat paketet ännu. Händer inget på några dagar, fråga säljaren om paketet verkligen har skickats.

**Fast i tullen.** Om du ombeds betala importavgifter, se guiden om [paket i tullen](guide:customs) (på engelska). Står paketet still i flera dagar, meddela säljaren.

**GZYL:s spårning eller en annan tjänst visar fler steg än Peek.** Peek håller tillbaka steg skrivna före den tid de visar, `Delivered` som avslutar GZYL:s plan och steg daterade efter senaste kontrollen (se nedan), så de extra stegen kan vara GZYL:s plan och inte skanningar. Ett förprogrammerat steg ändrar aldrig status eller skickar aviseringar. När Peek håller tillbaka förhandssteg öppnas historiken till leverans eller retur med raden «Other sites may show more steps, but they’re only predictions.», och «Show them» visar stegen i grått med sin dag («Predicted for Mon 12 Oct»). Gå efter butikens uppskattade leveranstid och fråga säljaren när den passerat. Mer i guiden om [paket från Kina](guide:tracking-from-china) (på engelska).

**`Delivered`, men inget har kommit.** Steget kan vara del av GZYL:s fasta plan, inte en skanning. Om inte Swiss Post levererar visar Peek under «Delivered»: «GZYL updates on a fixed schedule, with no signature. If nothing came, ask the seller.» Berätta för säljaren att inget kommit och fråga vilket företag som levererar och under vilket nummer. Mer i guiden om [levererat men inte mottaget](guide:delivered-not-received) (på engelska).

**Inget hittades.** För ett okänt nummer visar GZYL:s spårning ett resultat med ditt nummer under `参考号` (referens) och alla andra fält tomma. Peek tolkar svaret som ej funnet: det visar «Waiting for GZYL» och två dagar senare «Check the tracking number». Jämför numret tecken för tecken med säljarens meddelande.

**Försvunnet eller returnerat.** Säljaren bokade paketet med GZYL, så be säljaren eller marknadsplatsen om återbetalning eller ett nytt paket.

## Kontakt och kundtjänst hos GZYL

På kontaktsidan (`联系我们`) på GZYL:s kinesiska webbplats (`yunlianhouyun.com`) listas en 24-timmars servicelinje (`全国24小时服务热线`), ett WeChat-konto och ett QQ-konto för eftermarknad (`售后QQ`), men inget för mottagare: dessa kanaler är för GZYL:s kunder, alltså säljarna. Kontakta säljaren om din beställning, och marknadsplatsen om säljaren inte svarar.

## Spåra ett GZYL-paket med Peek

[Peek](/) känner genast igen ett nummer som börjar med `YL738`, sju ytterligare siffror och `YQ` («Detected carrier»). Övriga nummer med `YL`, tio siffror och `YQ` är en svag träff. Klistra in numret ändå: Peek följer över 4 500 transportörer. Medan du lägger till det frågar Peek GZYL:s spårning («Checking tracking services…») och visar «GZYL has this parcel.» när spårningen ger ett resultat (även det tomma svaret för ett okänt nummer). Annars trycker du på «Change» och väljer GZYL under «Fits this number» (du hittar den även med `广州云链`), eller använder «Change carrier» på ett redan tillagt paket. En säljarreferens fungerar också om du väljer GZYL manuellt, då visas orderns eget nummer under «GZYL tracks this as»; eftersom Peek tar bort bindestreck hittas dock inte referenser med bindestreck, så be säljaren om `YL…YQ`-numret.

Peek läser GZYL:s egen spårning och visar status, varje steg med plats och destinationsland när posten innehåller det. Tiderna läses i Pekingtid och visas i din enhets tidszon («Times are in your time zone:» i slutet av historiken). Peek utelämnar namn, adress, postnummer, vikt, avgifter, signatur och leveransbevis och visar inget beräknat leveransdatum eftersom GZYL inte anger något.

Peek håller tillbaka steg skrivna mer än tolv timmar före den tid de visar, steg skrivna i samma sekund som ett steg skrivet mer än en halvtimme före sin tid (utom orderregistreringen) samt steg daterade efter kontrolltidpunkten i Pekingtid. På en order med förhandssteg hålls även `Delivered` tillbaka, även om det skrevs samma dag. Om spårningens datagränssnitt inte har några steg läser Peek GZYL:s söksida i stället; där syns inte när ett steg skrevs, så där håller Peek tillbaka varje `Delivered`. Peek utgår från de behållna stegen och inte från postens sammanfattning. Om alla steg hålls tillbaka visas «Waiting for GZYL» utan uppmaning att kontrollera numret, eftersom förhandssteg visar att GZYL har ordern. Peek rensar också bort spårningstjänsters kopior av steg som hålls tillbaka som plan (`Delivered` inräknat). När GZYL:s spårning inte går att nå frågar Peek spårningstjänster, vars kopior kan innehålla förprogrammerade steg.

Bortsett från överlämning till Swiss Post stannar Peek hos GZYL: får du det lokala utdelningsföretagets nummer av säljaren, lägg till det som ett eget paket. Medan du har paketet öppet eller på leveransdagen med aviseringar på kontrollerar Peek upp till var 10:e minut, och upp till var 2:a minut den dag paketet visar «Out for delivery» (från kl. 06.00 lokal tid medan GZYL:s eller Swiss Posts egen spårning svarar, även om inget känt GZYL-steg ger den statusen). Annars samt efter två dagar utan nytt (12 timmar vid «Label created» eller «Received by carrier») kontrollerar Peek varje timme. Via spårningstjänster sker kontrollen varje timme, var 3:e timme efter en tyst dag och upp till var 15:e minut på leveransdagen. Du väljer aviseringar mellan «Every scan», «Important steps» (de två tullstegen och `Delivered`, inte flyg eller sortering) och «Delivery only». Peek kan inte kontakta GZYL eller säljaren åt dig.

## Vanliga frågor om GZYL

### Vad är GZYL (广州云链)?

GZYL är 广州云链货运代理有限公司 (Guangzhou Yunlian), en speditör i Guangzhou i Kina med den kinesiska webbplatsen `yunlianhouyun.com`. Fraktsedelsnumren har 14 tecken: `YL`, tio siffror och `YQ`.

### Tillhör varje YL…YQ-nummer GZYL?

Inte automatiskt. `YL`, tio siffror och `YQ` är GZYL:s format — Peek väljer GZYL direkt för `YL738…YQ` och kontrollerar GZYL:s spårning för övriga `YL…YQ`-nummer. Nio eller elva siffror stämmer inte med GZYL, och `GZXH…YQ` tillhör Xinghe Freight.

### Varför visar GZYL «Delivered» när inget paket har kommit?

GZYL skriver ofta stora delar av resan i förväg och lägger till `Delivered` enligt fast schema snarare än från en lokal leveransskanning. Peek håller tillbaka förhandssteg och varnar om det fasta schemat — meddela säljaren att inget kommit och fråga vilket företag som levererar.

### Vem levererar GZYL-paket i Sverige?

GZYL:s skanningar anger aldrig det lokala utdelningsföretaget i Sverige (bara i Schweiz och Liechtenstein kan Peek lämna över till Swiss Post). Kontrollera kolumnen `渠道转单号` på GZYL:s sida eller fråga säljaren om den lokala leverantören och dess spårningsnummer.

### Hur kontaktar jag GZYL om ett försenat eller saknat paket?

Kontakta säljaren eller marknadsplatsen först. Även om `yunlianhouyun.com` visar en kinesisk 24-timmarslinje, WeChat och QQ för företagskunder (säljare) erbjuder GZYL ingen kundtjänst för mottagare.

:::sources
- [广州云链货运代理有限公司 (GZYL): startsida (på kinesiska)](http://yunlianhouyun.com/) – företagsnamn, kinesisk webbplats, paketsökning (`小包查询`) mot GZYL:s spårning
- [广州云链货运代理有限公司 (GZYL): 联系我们 (kontakt, på kinesiska)](http://yunlianhouyun.com/contact.html) – 24-timmarslinje, WeChat och QQ för eftermarknad
- [Europeiska kommissionen: EU Customs Reform (på engelska)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 3 € schablontull per varukategori upp till 150 € sedan 1 juli 2026, 2 € EU-hanteringsavgift sedan 1 november 2026
- [Europeiska kommissionen: Guidance on the EUR 3 customs duty and the Union handling fee (PDF, på engelska)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 2 € avgift per varukategori oavsett värde
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – namn, spårning på ren IP-adress, formaten `YL…YQ` och `YL738…YQ`, Xinghe Freight i GZYL:s spårning, sökning på referens, stegformuleringar, kinesisk rad, städer, Pekingtid, förhandssteg, svar för okänt nummer, kolumnerna för överföringsnummer, Swiss Post-överlämning samt vad Peek visar, håller tillbaka och utelämnar
:::
