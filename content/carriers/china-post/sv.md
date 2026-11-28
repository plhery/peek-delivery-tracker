---
title: China Post spårning: CN-nummer, statusar och leverans
description: Spåra ett China Post- eller EMS-paket som slutar på CN: S10-koder (L, R, C, E, U), kinesiska statusar på svenska, överlämning till lokal post och kontakt.
slug: china-post-spara-paket
published: 2026-11-28
updated: 2026-11-28
---

Spåra ett China Post- eller EMS-paket som slutar på `CN` (två bokstäver, nio siffror och `CN`, det internationella postformatet UPU S10) på ems.com.cn eller i fältet ovan. Eftersom Världspostföreningen (UPU) bara tillåter ett S10-nummer per försändelse levererar den nationella posten i mottagarlandet — som USPS i USA, Royal Mail eller Parcelforce i Storbritannien, An Post på Irland, Canada Post i Kanada eller Australia Post i Australien — under exakt samma nummer när paketet lämnat Kina; för formella efterlysningar kräver China Post att avsändaren vänder sig till sitt lokala postkontor (China Posts linjer: 8610 11183 för EMS, 8610 11185 för Postal Business).

## China Post spårningsnummer: hur det ser ut

Ett China Post-nummer består av två bokstäver, nio siffror och `CN`: det internationella postformatet från Världspostföreningen (UPU). Den första bokstaven talar om vilken typ av försändelse det är. Med påhittade siffror:

| Nummer | Vad det är |
| --- | --- |
| `LT482917367CN` | `L`: ett spårbart småpaket (brevpost). |
| `RR482917367CN` | `R`: rekommenderad brevpost. |
| `CP482917367CN` | `C`: ett paket. |
| `EA482917367CN` | `E`: EMS, China Posts expresstjänst. |
| `UA482917367CN` | `U`: ett småpaket med varor "without customer-oriented tracking", med UPU:s ord. Räkna inte med skanningar i ditt land. |
| `ZS48291736701` | `ZS`, `ZC` eller `ZA` och 11 siffror: besvaras också av China Posts spårning. |
| `LP48291057364829` | Inte China Post: en logistikkod från [Cainiao](carrier:cainiao) (AliExpress). |
| `YT4829105736482910` | Inte China Post, utan [YunExpress](carrier:yunexpress). |

Den sista av de nio siffrorna är en kontrollsiffra, så en felskriven siffra gör nästan alltid numret ogiltigt. UPU tillåter bara ett sådant nummer per försändelse, vilket är anledningen till att samma nummer fungerar på din egen posts webbplats när paketet kommit till ditt land.

För vissa försändelser frågar China Posts spårning efter de fyra sista siffrorna i ett kontakttelefonnummer som står på försändelsen.

## China Post spårningsstatusar förklarade

China Post skriver sina skanningar på kinesiska. Ortsnamn står inom hakparenteser, här skrivna `【…】`: ett kinesiskt sorteringscenter eller, utomlands, landets namn på kinesiska, som `【英国】` för Storbritannien eller `【美国】` för USA. I den ordning ett paket brukar få dem:

| China Post anger | Vad det betyder för dig |
| --- | --- |
| `已揽收` | Mottaget: China Post har tagit emot paketet. |
| `邮件到达【…】` | Kom fram till ett postkontor eller sorteringscenter hos China Post. |
| `邮件离开【…】，正在发往下一站` | Lämnade det kontoret mot nästa stopp. |
| `【…】退回，备注:安检退回` | Ett sorteringscenter skickade tillbaka det vid säkerhetskontrollen och hanterar det innan det skickas vidare; det går inte tillbaka till avsändaren. Peek visar "Needs attention". |
| `送交出口海关` | Lämnat till kinesisk exporttull. |
| `出口海关/留存待验` | Hålls kvar av exporttullen för kontroll. |
| `出口海关/放行` | Exporttullen har släppt igenom det. |
| `【…】已出口直封` | Förseglat för export på ett av Kinas internationella utbyteskontor. |
| `已交承运商运输` | Överlämnat till transportör. |
| `航空公司接收`, `航空公司启运` | Ett flygbolag tog emot det och flög sedan iväg med det. |
| `飞机进港` | Flyget har landat. |
| `到达寄达地处理中心` | Kom fram till behandlingscentralen i ditt land. |
| `送交境外进口海关` | Lämnat till tullen i ditt land. |
| `境外进口海关留存待验` | Tullen där håller det för kontroll. |
| `寄达地海关允许进口` | Tullen har släppt in det. |
| `离开【…】处理中心` | Lämnade den centralen. |
| `已到达【…】投递局` | Kom fram till ditt lokala utdelningskontor. |
| `【…】安排投递`, `邮件正在派送中` | Ute för leverans. |
| `已试投/投递失败` | Din post försökte leverera det men misslyckades. |
| `【…】已妥投`, `已签收` | Levererat. |
| `已退回` | Returnerat till avsändaren. |

De sista skanningarna kommer från ditt lands post och återges av China Post, ibland först efter leveransen. Din egen posts spårning, med samma nummer, har sista sträckan från första hand.

## Vem levererar China Post-paket i ditt land

China Post flyger ut paketet; i mottagarlandet tar den nationella posten över, genom tullen hela vägen hem till dig:

- **USA**: [USPS](carrier:usps).
- **Storbritannien**: [Royal Mail](carrier:royal-mail). Tar paketet genom brittisk tull och meddelar om moms eller tull ska betalas. EMS-nummer (som börjar med `E`) går i stället till [Parcelforce](carrier:parcelforce), Storbritanniens EMS-operatör.
- **Irland och EU**: på Irland [An Post](carrier:an-post). Sedan den 1 november 2026 tar EU ut en hanteringsavgift på 2 € per varuslag; om butiken inte tog ut den i kassan ber postoperatören (som An Post på Irland) om den, tillsammans med tull och eventuell moms, före leverans.
- **Kanada**: [Canada Post](carrier:canada-post). Sök numret i verktyget Track; om inget visas ber Canada Post att du låter avsändaren utreda saken med posten i avsändarlandet.
- **Australien**: [Australia Post](carrier:australia-post). Anger att spårning för försändelser från utlandet beror på tjänsten som använts i avsändarlandet, och att tullen kan ta upp till 15 dagar.

Leveransförsök, avier och hur länge ett paket ligger på ombudet följer din posts regler; se dess sida och [missed a delivery](guide:missed-delivery) (på engelska). Ett paket som inte hämtas ut ([uncollected](guide:uncollected-parcel), på engelska) går tillbaka till avsändaren. Tull, skatter och hanteringsavgifter för paket utanför ditt land finns i [parcels held at customs](guide:customs) (på engelska).

## China Post-spårning uppdateras inte, paket försvunnet eller returnerat

**Inga skanningar än.** China Post visar ingenting förrän paketet har skannats; före det har butiken bara skapat en etikett. Fråga butiken när den lämnade in paketet. Peek visar "Waiting for China Post" under tiden. Om China Post fortfarande inte visar något för numret två dagar senare och inget annat har svarat för det visar Peek "After two days, the carrier still doesn’t know this number. Check it, choose another carrier or delete the parcel."

**Tyst efter att det lämnat Kina.** Mellan flyget och din egen posts första skanning kan flera dagar gå utan nyheter. Kontrollera din egen posts spårning med samma nummer. Ett nummer som börjar med `U` får kanske aldrig några skanningar i ditt land. Mer i [why tracking stops updating](guide:tracking-not-updating) och [tracking a parcel from China](guide:tracking-from-china) (på engelska).

**Levererat men inget kom fram.** Din post skötte leveransen, så börja med dess spårning och leveransbevis, och kontakta sedan butiken. Se [delivered but not received](guide:delivered-not-received) (på engelska).

**Försvunnet eller skadat.** China Post anger att "for formal inquiry of all international items, the sender shall go to local post office" (för formell efterlysning av alla internationella försändelser måste avsändaren gå till sitt lokala postkontor). USPS, Canada Post och Australia Post hänvisar också mottagare till avsändaren, som kontaktar posten i avsändarlandet. Meddela alltså butiken eller marknadsplatsen och begär återbetalning eller ett nytt paket; se [lost parcels](guide:lost-parcel) (på engelska).

**Returnerat.** `已退回` betyder att paketet är på väg tillbaka till avsändaren. Fråga butiken vad den gör; se [returned to sender](guide:returned-to-sender) (på engelska).

## China Post kontaktnummer och kundservice

Per den 10 november 2026 anger China Posts engelska webbplats dessa nummer:

- **Logistics and EMS**: 8610 11183.
- **Postal Business**: 8610 11185.

China Post anger inte vilka språk som talas där. En formell efterlysning måste komma från avsändaren, så butiken går snabbare. För leverans, ny utkörning eller tull kontaktar du din egen post.

## Spåra ett China Post-paket med Peek

[Peek](/) känner igen två bokstäver, nio siffror och `CN` som China Post direkt när kontrollsiffran stämmer, om det inte börjar med `YY`: de numren tillhör YYpost. Med fel kontrollsiffra tar Peek inte numret för China Post, och om du väljer China Post säger den att China Post "doesn’t issue numbers like this one": kontrollera numret i butikens mejl. Även ett `ZS`-, `ZC`- eller `ZA`-nummer med 11 siffror känns igen som China Post direkt. Klistra in andra nummer ändå: Peek följer mer än 4 500 transportörer. Hamnar den på fel transportör väljer du China Post via "Change".

Peek läser China Posts egen spårning. När China Post ber om telefonsiffror provar Peek den öppna förhandsvisningen på ems.com.cn i stället; Peek ber dig aldrig om dem. Om förhandsvisningen också nekar och ingen spårningstjänst har paketet säger Peek att mer information behövs: slå då upp numret på China Posts webbplats med de fyra siffrorna. China Post visar besökare bara de tre senaste skanningarna, så Peek lägger till inlämningsdatumet när China Post ger ett, och hämtar en fullständigare historik från spårningstjänster när någon har den.

Peek visar status, historik med kontoret för varje skanning (ett kinesiskt kontor angivet med stad, eller som det står om Peek inte kan placera det) och destinationsland. Skanningar visas på engelska där Peek känner igen China Posts formulering, annars på kinesiska. China Post tidsätter sina egna skanningar efter kinesisk tid, även ankomsten till ditt land; senare skanningar som den kopierar från din nationella post behåller vanligen den postens lokala tid. Peek visar varje skanning i din enhets tidszon; på peektracker.com och i iPhone- och Android-apparna avslutas historiken med "Times are in your time zone:" och dess namn. China Post ger inget beräknat leveransdatum, ingen vikt och inget utlämningsställe, och Peek utelämnar tjänstenamn, kontakter, avsändar- och mottagaruppgifter, budens namn och telefonnummer samt kartkoordinater.

För ett nummer som slutar på `CN` frågar Peek, så snart China Post nämner ditt land, även din nationella post med samma nummer (USPS, Royal Mail, An Post, Canada Post eller Australia Post; Parcelforce för EMS-nummer till Storbritannien) och går över till den så snart den visar färska skanningar, med China Posts historik bevarad. Om China Post inte anger något land går Peek efter ditt: i iPhone- och Android-apparna regionen i telefonen, och på peektracker.com landet du lade till paketet från.

Peek kontrollerar China Post varje timme, även under utkörning. När din post tar över leveransen följer Peek den levererande transportörens takt. Peek kan avisera dig vid "Every scan", "Important steps" eller "Delivery only". Den kan inte kontakta China Post, din post eller butiken åt dig.

## Frågor om China Post spårning

### Var är mitt China Post-paket?

Titta på den senaste skanningen: ett kinesiskt sorteringscenter inom hakparenteser (`【…】`) betyder att det fortfarande är i Kina; så snart `到达寄达地处理中心` visas har det nått ditt land och du kan följa samma `CN`-nummer på din egen nationella posts webbplats.

### Varför uppdateras inte min China Post-spårning?

Spårningen pausar ofta under den internationella flygningen eller importtullen mellan China Posts exportskanning och din nationella posts första skanning. Observera att nummer som börjar med `U` (`UA…CN`) är UPU-småpaket utan kundinriktad spårning och kanske inte får några skanningar utomlands.

### Hur lång tid tar China Post att leverera?

Leveranstiden beror på vald tjänst (EMS express jämfört med vanligt brev eller paket) och destinationsland, så utgå från beräkningen som visas på din order i butiken eller marknadsplatsen.

### Vad betyder 已妥投 i China Posts spårning?

Det betyder levererat (till exempel `【英国】已妥投` = levererat i Storbritannien). Eftersom din lokala nationella post gjorde slutleveransen bör du först kontrollera dess spårning och leveransbevis om inget kom fram: se [delivered but not received](guide:delivered-not-received) (på engelska).

### Vem levererar China Post-paket i USA, Storbritannien, Irland, Kanada eller Australien?

Den nationella posten under exakt samma `CN`-nummer: USPS i USA, Royal Mail i Storbritannien (Parcelforce för EMS-försändelser `E…CN`), An Post på Irland, Canada Post i Kanada och Australia Post i Australien.

:::sources
- [China Post: Introduction to China Post Group](https://english.chinapost.com.cn/en/folder/1408/3969-1.htm) – statlig nationell post
- [China Post: EMS and Logistics](https://english.chinapost.com.cn/en/folder/1408/3923-1.htm) – varumärket EMS
- [China Post: Contact us](https://english.chinapost.com.cn/en/category/1408/3925-1.htm) – spårningssidor, formell efterlysning via avsändaren
- [China Post: Home page (English)](https://english.chinapost.com.cn/) – nummer för Logistics and EMS och Postal Business
- [UPU: S10 standard, Identification of postal items](https://www.upu.int/UPU/media/upu/files/postalSolutions/programmesAndServices/standards/S10-12.pdf) – nummerformat, tjänstebokstäver, U-serien utan spårning, ett nummer per försändelse
- [GOV.UK: Tax and customs for goods sent from abroad: Overview](https://www.gov.uk/goods-sent-from-abroad) – Royal Mail tar varor genom brittisk tull och meddelar om moms eller tull ska betalas
- [Revenue: Union Handling Fee press release](https://www.revenue.ie/en/corporate/press-office/press-releases/2026/pr-092926-handling-fee.aspx) – 2 € per varuslag från 1 november 2026, betalas i kassan eller till posttjänsten före leverans
- [Canada Post: Tracking international packages](https://www.canadapost-postescanada.ca/cpc/en/support/kb/tracking/tracking-international-packages.page) – paket till Kanada, avsändaren utreder, tidsramar
- [Australia Post: International tracking](https://auspost.com.au/help/parcel-tracking/international-parcel/tracking) – spårning beror på tjänsten i avsändarlandet
- [Australia Post: Customs](https://auspost.com.au/help/parcel-tracking/international-parcel/customs) – upp till 15 dagar
- [USPS: International Inquiries – The Basics](https://faq.usps.com/s/article/International-Inquiries-The-Basics) – försändelser från utlandet: efterlysning via avsändarens post
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – China Posts skanningsformuleringar, kontroll av telefonsiffror, öppen förhandsvisning i webbläsare och vad Peek visar
:::
