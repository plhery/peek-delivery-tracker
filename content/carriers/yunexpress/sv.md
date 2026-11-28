---
title: YunExpress spårning: YT-nummer, statusar och leverans
description: Spåra ett YunExpress-paket (YT / YT2) på yuntrack.com: vilket bolag som använder YT-nummer, statusar, vem som levererar i Storbritannien eller USA och tider.
slug: yunexpress-spara-paket
published: 2026-11-28
updated: 2026-11-28
---

YunExpress (även skrivet Yun Express) är logistikbolaget i Shenzhen bakom spårningsnummer som börjar med `YT` — inklusive `YT2` och `YT22` — följt av 16 siffror (18 tecken totalt), som används av säljare på Amazon, Temu, SHEIN och AliExpress. Spåra en `YT`-försändelse på [yuntrack.com](https://www.yuntrack.com/), YunExpress egen spårningsplattform, eller klistra in numret i rutan ovan. YunExpress nämner inga SMS eller mejl till mottagare, så leta i butikens leveransmejl eller på din beställningssida.

## Spårningsnummer hos YunExpress: YT och 16 siffror

Yuntrack ber om "your YunExpress tracking number (starting with YT)" och anger ingen längd. Påhittade exempel, tillsammans med [andra fraktbolags liknande nummer](guide:tracking-number-formats) (på engelska):

| Nummer | Peek tolkar det som |
| --- | --- |
| `YT4829105736482910` | YunExpress (YT och 16 siffror). |
| `YT4829105736482` | [YTO Express](carrier:yto), ett annat fraktbolag (YT och 13 siffror). |
| `YT482910573648291` | Inte YunExpress (YT och 15 siffror): leta efter en siffra som saknas. |
| `LP48291057364829` | En logistikreferens från AliExpress (LP och 14 siffror), se [Cainiao](carrier:cainiao). |

Börjar det på YT2 eller YT22? Det är fortfarande ett YunExpress-nummer: YT och därefter 16 siffror, oavsett hur de börjar. YunExpress förklarar inte vad siffrorna står för, så räkna dem i stället för att läsa in något i dem.

Hittar du det inte i butikens meddelanden? Se [hur du hittar ditt spårningsnummer](guide:find-tracking-number) (på engelska).

Yuntrack tar upp till 100 nummer per sökning, åtskilda med kommatecken, mellanslag eller radbrytningar. När ett lokalt fraktbolag tar över kan paketets detaljsida visa det bolagets eget nummer under "Additional Notes". YunExpress anger ingen tid innan ett nytt nummer syns; om yuntrack svarar `Not Found`, kontrollera YT och alla 16 siffror och be sedan butiken bekräfta det.

## Vad spårningsstatusarna hos YunExpress betyder

Yuntrack finns bara på engelska. Där läggs varje paket under en flik (`Processing`, `Transit`, `Delivered`, `Not Found`, `Alert` eller `Returned`) med sina skanningar listade. De vanliga, i ungefärlig ordning:

| YunExpress skriver | Vad det betyder för dig |
| --- | --- |
| `Shipment information received` | Säljaren har skickat uppgifterna; YunExpress har kanske inte fått paketet ännu. |
| `Shipment picked up` | YunExpress har hämtat det hos säljaren. |
| `The country of origin commences customs declaration.` | Exporttull där det skickas ifrån, inte i ditt land. |
| `International flight has departed`, `Departed from AIRPORT` | På sitt internationella flyg. |
| `Start Customs Clearence` | Importtullen har påbörjats (YunExpress egen stavning). |
| `Customs inspection - Import` | Utvalt för tullkontroll. |
| `Clearance processing completed - Import` | Har gått igenom tullen. |
| `Delivered to local carrier` | Överlämnat till bolaget som levererar det, inte till dig. |
| `Item accepted` | Vidarebefordrat från det bolaget: det har tagit emot paketet från YunExpress. En överlämning, inte en leverans till dig. |
| `Arrived at GOFO Regional Destination Facility` | Har nått GOFO, det lokala budbolag som nämns i skanningen. |
| `The driver is out for delivery` | Ute med föraren. |
| `Your parcel is out for delivery.` | Vidarebefordrat från J&T Express Australia för ett paket till Australien: ute med föraren. Kan sluta med J&T:s kundtjänstnummer. |
| `Item is out for delivery` | Vidarebefordrat från bolaget bakom `Item accepted`: ute med föraren. |
| `Delivered by Mailbox` | I din brevlåda. |

`Transit` betyder på väg; den senaste skanningen anger steget. YunExpress förklarar inte `Alert`, sin flik för paket med problem: läs raden under den och fråga butiken om inget ändras.

Efter `Delivered to local carrier` följer det lokala fraktbolagets egna rader, också på engelska ([vad de betyder](guide:tracking-statuses), på engelska). DPD:s `Sender goods issue` är en dataregistrering gjord medan paketet fortfarande är i Kina, inte ett problem med dina varor. Om DHL i Tyskland tar över vidarebefordrar YunExpress dess rader: `The instruction data for this shipment have been provided by the sender to DHL electronically` är DHL:s dataregistrering, "Label created" i Peek; `Pick-up was successful.` är DHL:s hämtning efter överlämningen, inte den första hämtningen. Därefter följer `The shipment has been processed in the parcel center of origin`, `Loaded to movement / tour vehicle` (mellan paketcentraler, inte utkörningen) och `The shipment has been processed in the destination parcel center`, centralen nära dig, samt `Movement / tour vehicle arrived`, vid depån som levererar, innan rundan lastas: allt visas som "In transit" i Peek. `Not delivered-Consignee could not be reached` är ett leveransförsök där ingen var anträffbar: "Delivery attempted". Yuntracks sidkod har också en förloppsrad (`Pickup`, `Departed from origin`, `Arrived at destination`, `Local carrier on the way`, `Delivered successfully`); dess `Pickup` avser hämtning hos säljaren, inte ett ombud. För vissa paket visar yuntrack även `Estimated delivery time`.

## Vem levererar YunExpress-paket i Storbritannien och USA

YunExpress flyger ut paket från Kina, låter dem tullklareras och lämnar dem sedan till ett lokalt fraktbolag, [den vanliga vägen för paket från Kina](guide:tracking-from-china) (på engelska). Raderna efter `Delivered to local carrier` namnger ibland det bolag som har ditt paket.

**Storbritannien och Irland.** YunExpress lanserade en egen linje till Storbritannien 2016, men sidorna namnger ingen leveranspartner i Storbritannien eller Irland och inget kontor eller telefonnummer för mottagare där: fråga för leveransen det bolag som nämns i din spårning. En av linjerna levererar i hela Storbritannien, inklusive öar inom landet, men inte till öar utanför som Jersey och Guernsey. Sidorna säger inget till brittiska köpare om moms eller tull; se [vad du kan behöva betala](guide:customs) (på engelska).

**USA.** YunExpress kinesiska webbplats erbjuder säljare en lokalleveranstjänst, CIRRO-E-COMMERCE, utan att ange vilka paket den kör. Bolaget lanserade även linjen "US Standard Postal Service" 2020, men anger inte vilket lokalt bolag som levererar ett visst USA-paket. Spårningen av vissa paket i USA visar att [GOFO](carrier:gofo) levererar: skanningarna vidarebefordras från GOFO, den sista-milen-transportör som nämns i dem, och GOFO:s egen spårning känner igen de paketen på samma YT-nummer.

YunExpress säger inget till mottagare om leveransförsök, avier, liggtider eller ombokning; kontrollera det hos det lokala bolaget som anges i spårningen.

En odaterad artikel från YunExpress på kinesiska beskriver ombudslinjer i Europa: YunExpress väljer det ombud eller paketskåp som ligger närmast dig, inom 3 km, eller så väljer du själv. Den anger i genomsnitt 6 till 10 kalenderdagar från inlämning till ombudet och säger att spårningen visar ombudets adress.

## YunExpress-spårning uppdateras inte, försenat eller borttappat

YunExpress ger mottagare inga steg, reklamationsformulär eller tidsfrister för ett paket som fastnat, är försenat, försvunnet eller skadat. Kunden är kontoinnehavaren som tecknar avtalet, så säljaren eller butiken äger rätten att reklamera: kontakta butiken eller marknadsplatsen med ditt order- och YT-nummer. Vid långa uppehåll mellan skanningar, se [varför spårning slutar uppdateras](guide:tracking-not-updating) (på engelska).

Fast vid `Customs inspection - Import`? YunExpress blogg för säljare säger att ett urval inte i sig betyder ett långt stopp; kontrollnivån avgör. En dokumentkontroll lägger vanligtvis till några arbetsdagar upp till omkring en vecka; en skanning eller fysisk inspektion ofta en till två veckor, ibland med lager- eller kontrollavgifter; en prövning hos myndigheterna veckor till över en månad. Vad du kan behöva betala finns i [paket som hålls i tullen](guide:customs) (på engelska).

Markerat levererat men saknas? Spårningssidan kan visa ett leveransbevis; fråga därefter det lokala bolaget och butiken och se [vad mer du bör kontrollera](guide:delivered-not-received) (på engelska). `Returned` betyder att paketet går i retur; YunExpress publicerar inga returregler för mottagare, så fråga butiken.

## Kontaktnummer och kundtjänst för YunExpress

I oktober 2026 angav YunExpress inget telefonnummer, formulär eller kontor för mottagare i Storbritannien, Irland eller USA. Följande uppgifter fanns listade:

- **E-post** info@yunexpress.com, listad som "Customer Service" i sidfoten på yunexpress.com, 8:00 till 23:00 utan angiven tidszon. Det framgår inte om mottagare får hjälp där; startsidan erbjuder "Human experts with rapid responses (EN/CN)".
- **Telefon** 400-8575-500 på yunexpress.cn, ett nummer i Fastlandskina; det anges inte om det fungerar från utlandet.
- **Regionala kontakter** endast för Asien och Australien.

Feedback-rutan på Yuntrack gäller webbplatsen. Börja med butiken, som har avtalet, och därefter det lokala fraktbolag som nämns i spårningen. Om det är GOFO i USA finns dess kundtjänst på dess amerikanska sajt, gofo.com.

## Spåra ett YunExpress-paket med Peek

[Peek](/) känner igen YT och 16 siffror som YunExpress och tar emot länkar från yuntrack.com. Medan du har paketet öppet, eller på leveransdagen med aviseringar påslagna, kontrollerar Peek upp till var 10:e minut (var 5:e minut från 06:00 lokal tid den dag paketet skannas som ute för leverans, medan YunExpress egen spårare svarar); annars varje timme. Via spårningstjänster är det varje timme, var 3:e timme efter en tyst dag och upp till var 15:e minut på leveransdagen under samma villkor. Peek placerar varje skanning på en karta och kan meddela dig när statusen ändras. Efter två dagar utan nytt (12 timmar medan paketet fortfarande visar "Label created" eller "Received by carrier") kontrollerar Peek en gång i timmen. Det visar status, historik, plats för varje skanning och leveranstid. Det läser skanningar i Kina enligt kinesisk tid; flygets landning och rader som slutar på `- Import`, när de inte nämner någon ort, enligt klockan för den ankomstflygplats YunExpress anger; J&T Express Australias skanningar av ett paket till Australien enligt Sydneytid, oavsett vilken delstat depån ligger i; och de flesta andra enligt klockan på sin plats eller, när ingen plats nämns, klockan för skanningarna före eller efter. Varje tid visas i din enhets tidszon.

När YunExpress anger det lokala fraktbolaget och dess nummer kan Peek följa paketet vidare till det bolaget, dock inte för varje paket. Peek visar ingen beräknad leveransdag och inget ombud för YunExpress-paket, utelämnar orderreferenser och kan inte kontakta YunExpress eller lämna in ett krav åt dig.

## Frågor om YunExpress-spårning

### Vilket fraktbolag använder spårningsnummer som börjar på YT och YT2?

YunExpress, när `YT` följs av 16 siffror (totalt 18 tecken), inklusive nummer som börjar på `YT2` eller `YT22`. Både YunExpress webbplats och yuntrack ber om ett nummer "starting with YT". `YT` och 13 siffror tillhör [YTO Express](carrier:yto), ett annat kinesiskt bolag. Spåra ett YunExpress-nummer på yuntrack.com eller klistra in det i rutan ovan; när ett lokalt bolag tar över kan spårningen visa det bolagets nummer också.

### Vad är yuntrack.com och vilken bransch verkar det i?

`yuntrack.com` är YunExpress egen Track & Trace-plattform. YunExpress är ett gränsöverskridande paketlogistik- och fraktbolag grundat i Shenzhen 2014. YunExpress länkar dit som "Tracking" från yunexpress.com, och spårningsrutan på yunexpress.com öppnar resultaten där. Sidan är på engelska, tar upp till 100 YT-nummer per sökning och kräver inget konto.

### Är YunExpress ett seriöst bolag?

Ja, det är ett riktigt logistikföretag: Shenzhen Qianhai YunExpress Logistics Co., Ltd., grundat i Shenzhen 2014. Enligt bolagets egna uppgifter blev det en rekommenderad logistikpartner för Amazon 2017 och en officiell partner för AliExpress i april 2025.

### Var ligger YunExpress?

I distriktet Longgang i Shenzhen, Kina. Bolaget listar inget kontor för mottagare i Storbritannien, Irland eller USA; dess integritetspolicy från 2024 anger Hongkong YunExpress Logistics Limited som personuppgiftsansvarig utanför Fastlandskina.

### Hur lång tid tar leverans med YunExpress?

Enligt YunExpress egna beräkningar för säljare tar Standard Express 3 till 5 arbetsdagar till Storbritannien, 5 till 8 till USA, 8 till 10 till Kanada och 4 till 7 till Australien. Priority Express, som lanserades 2024 och täcker Storbritannien och USA, anger samma 3 till 5 dagar till Storbritannien, bortsett från avlägsna postnummer, och 5 till 8 till USA. En kinesisk produktsida för en annan linje anger 5 till 8 arbetsdagar till Storbritannien, 6 till 10 till Irland och 8 till 15 till USA. Vanligtvis går det inte att se vilken linje säljaren valde, och tullkontroller lägger till tid.

:::sources
- [YunExpress: Track & Trace Platform, Yuntrack](https://www.yuntrack.com/) – YunExpress spårningssida, YT-nummer, endast engelska, Feedback
- [YunExpress: Yuntrack page code](https://www.yuntrack.com/static/js/0.38a2ccebfb56bd50819e.js) – "starting with YT", avgränsare, flikar, Alert, förloppsrad, Additional Notes, leveransbevis, beräknad leveranstid
- [YunExpress: Yuntrack results page code](https://www.yuntrack.com/static/js/6.9b7c1a51737b2bcaa67c.js) – flikar, Not Found, Last Mile Tracking, 100 nummer per sökning, inget konto krävs
- [YunExpress: the Best Logistics Service Provider](https://www.yunexpress.com/) – företag, adress, e-post, tider, "starting with YT", spårningsruta och "Tracking"-länk till yuntrack
- [YunExpress: About Us](https://www.yunexpress.com/about-us) – 2014, UK-linje 2016, USA-postlinje 2020, Priority Express 2024, Amazon
- [YunExpress: Privacy Policy](https://www.yunexpress.com/privacy-policy) – avtalsparten som kund, bolaget i Hongkong
- [YunExpress: Partners](https://www.yunexpress.com/yun-ecosystem/partners) – marknadsplatser
- [YunExpress: Standard Express](https://www.yunexpress.com/our-solutions/b2c/33) – standardtider
- [YunExpress: Priority Express](https://www.yunexpress.com/our-solutions/b2c/priority-express) – linjer för Storbritannien och USA, tider, avlägsna postnummer
- [YunExpress: 全球专线挂号（特惠带电）](https://www.yunexpress.com/our-solutions/b2c/7) – tider för en annan linje, brittisk täckning utan Jersey eller Guernsey
- [YunExpress: 最高省21%！云途自提服务上线](https://www.yunexpress.com/newsroom/detail/3) – ombudslinjer
- [YunExpress: 云途物流成为速卖通官方物流合作伙伴](https://www.yunexpress.com/newsroom/detail/1) – AliExpress-partner
- [YunExpress: 為什麼你的包裹會被海關抽查？](https://hk.yunexpress.com/blog/2179/) – tullkontroller
- [YunExpress: 云途物流YunExpress](https://www.yunexpress.cn/) – småpaket, nummer i Kina, CIRRO-E-COMMERCE
- [YunExpress: Contact Us](https://sg.yunexpress.com/contact/) – regionala kontakter
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – vad Peek läser och visar, GOFO:s skanningar i YunExpress spårningshistorik, skanningarnas klockor
:::
