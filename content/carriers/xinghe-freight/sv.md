---
title: Spåra paket från Xinghe Freight: GZXH…YQ-nummer och status
description: Spåra ett paket från Xinghe Freight (星和货运) med GZXH…YQ-nummer: varför spårningen går via GZYL, förprogrammerade steg, tullavgifter i EU och kontakt.
slug: xinghe-freight-spara-paket
published: 2026-11-27
updated: 2026-11-27
---

Xinghe Freight (星和货运, 广州星和货运代理有限公司) är en speditör i Guangzhou i Kina som grundades 2014 och hanterar sjö-, flyg- och landfrakt samt tullklarering. Om en kinesisk butik skickade din beställning med Xinghe Freight har spårningsnumret 16 tecken och ser ut som `GZXH`, tio siffror och `YQ`: klistra in det i fältet ovan. Xinghe Freights egen webbplats skickar spårningssökningar vidare till [GZYL:s](carrier:gzyl) okrypterade HTTP-spårning, där steg — inklusive `Delivered` — kan vara skrivna i förväg i stället för att vara riktiga skanningar. Peek läser den spårningen åt dig, skiljer förutsagda steg från riktiga skanningar och frågar spårningstjänster i reserv när det behövs.

## Spårningsnummer hos Xinghe Freight: så ser det ut

De nummer som Peek känner igen som Xinghe Freights består av `GZXH`, tio siffror och `YQ`, sammanlagt 16 tecken. Om du råkar utelämna det sista `Q`:et (`GZXH`, tio siffror och `Y`, 15 tecken) känner Peek ändå igen numret och lägger tillbaka `Q` innan det frågar spårningen. Exempel med påhittade siffror:

| Nummer | Vad det är |
| --- | --- |
| `GZXH4829105736YQ` | `GZXH`, tio siffror och `YQ`: Xinghe Freights standardformat. Peek väljer Xinghe Freight direkt. |
| `GZXH4829105736Y` | `GZXH`, tio siffror och `Y`: saknar sista `Q`, men Peek lägger tillbaka `Q` automatiskt. |
| `GZXH482910573YQ` | Bara nio siffror: inte Xinghe Freights format. Peek avvisar numret direkt så att du kan kontrollera om en siffra saknas. |
| `YL4829105736YQ` | `YL`, tio siffror och `YQ`: fraktsedelsformatet för [GZYL](carrier:gzyl). Samma spårningssystem, men en annan speditör. |
| `JXCAA4829105736YQ` | Slutar också på `YQ`, som många kinesiska speditörers nummer gör, men börjar med `JXC`: ett nummer från [Juxi Express](carrier:juxi-express). |

Mellanslag, bindestreck och gemener spelar ingen roll när du klistrar in numret i Peek.

**Var du hittar numret.** Det är säljaren som bokar frakten med Xinghe Freight och skickar spårningsnumret till dig i leveransbekräftelsen eller på din beställningssida. Hittar du det inte, kontakta säljaren direkt.

## Vad statusarna hos Xinghe Freight betyder

Xinghe Freights spårning körs på GZYL:s spårningsplattform, så varje steg skrivs med GZYL:s engelska formuleringar, inklusive stavfel. Eftersom Peeks appgränssnitt inte finns på svenska visas även Peeks egna etapper på engelska: "Label created" (etikett skapad), "Received by carrier" (mottaget av transportören), "In transit" (under transport), "At customs" (i tullen) och "Delivered" (levererat). Stegen i den ordning ett paket vanligen passerar dem:

| Spårningen skriver | Vad det betyder för dig | Peek visar |
| --- | --- | --- |
| `Order information received` | Säljaren har skickat orderuppgifterna. Ingen har tagit emot paketet fysiskt ännu. | "Label created" |
| `Arrive at the cross border receiving point` | Speditören har tagit emot paketet: första skanningen i dess händer. | "Received by carrier" |
| `Goods leave the operation center` | Paketet har lämnat driftcentret. | "In transit" |
| `Arrive at the international airport` | På den internationella flygplatsen i väntan på flyg. | "In transit" |
| `Departing from the airport` | Flyget har avgått. | "In transit" |
| `Item arrives at destination airport` | Paketet har landat i destinationslandet. | "In transit" |
| `local carrier Agent Import customs clearance` | Ett ombud i ditt land klarerar paketet genom importtullen. | "At customs" |
| `The import customs keep it for inspection` | Tullen i ditt land har tagit ut paketet för kontroll. | "At customs" |
| `Customs clearance completed` | Tullklareringen är klar och paketet fortsätter sin resa. | "In transit" |
| `Internationaliy Package received at processing center` | Ett bearbetningscenter i ditt land har tagit emot det tullklarerade paketet. Det är inte en ny upphämtning. | "In transit" |
| `Leave the email processing center` | Paketet har lämnat det bearbetningscentret. | "In transit" |
| `Arrival sorting, distribution center` | Framkommet till ett sortering- eller distributionscenter i ditt land, med orten skriven efter texten. | "In transit" |
| `Leaving the sorting center` | Lämnar ett sorteringscenter, med orten efter texten. | "In transit" |
| `Package is on the way` | På väg vidare inom landet, med en ort. Det betyder inte att paketet är ute med budet för utdelning. | "In transit" |
| `Delivered` | Leveransnotering, med orten. Kan vara ett förprogrammerat steg i stället för en verklig skanning: se nedan. | "Delivered", med en notering om fast schema om inte Peek håller tillbaka steget (se nedan) |

**Via Hongkong.** Ett paket kan även visa `香港处理中心已收寄【Hong Kong】`: en postinlämning på bearbetningscentret i Hongkong som spårningen återger på kinesiska med staden inom klamrar. Peek visar raden som den är under "Received by carrier".

**En rad på kinesiska.** För en beställning utan fysiska skanningar ännu kan spårningsplattformen själv skriva `货物电子信息已经收到`, vilket betyder att de elektroniska uppgifterna om godset har mottagits. Säljaren har skapat ordern, men ingen har skannat paketet ännu. Peek visar raden på kinesiska under "Label created".

**Tider.** Peek tolkar alla klockslag i spårningen som Pekingtid (`UTC+8`), även steg som anger en ort i Sverige, och visar dem i din enhets tidszon. Vintertid motsvarar 20:00 i Peking 13:00 svensk tid (`UTC+1`), och sommartid 14:00 (`UTC+2`).

**Steg skrivna i förväg.** På den här spårningsplattformen kan speditörer lägga in ett pakets hela tidsplan i förväg — från inlämning till sista steget i ditt land — och spårningen visar varje rad när dess klockslag infaller. Ett sådant steg är en plan, inte en skanning av ditt paket. På GZYL:s spårning avslutas planen sedan med ett `Delivered` som plattformen lägger till i en kvällskörning samma dag som datumet visar: daterat dagen efter planens sista steg och under kontorstid i Peking, oavsett vilket land du bor i. Peek håller tillbaka sådana steg (se nedan), men en spårningstjänsts kopia kan ändå visa dem, så ett `Delivered` där bevisar inte att paketet kommit fram.

## Vem levererar paket från Xinghe Freight i Sverige?

Peek vet inte vilket företag som levererar Xinghe Freights paket i Sverige. Stegen i destinationslandet anger inget transportföretag, och Xinghe Freights egen webbplats nämner inget om paket för nätkunder. Det enda lokala företag som Peek kan lämna över spårningen av ett Xinghe-paket till är [Swiss Post](carrier:swiss-post), i Schweiz och Liechtenstein, när Xinghes post uttryckligen anger det.

Xinghe Freights webbplats ger inga leveranstider, antal utkörningsförsök, utlämningsställen eller liggetider — allt detta styrs av det lokala företag som delar ut paketet i Sverige. Fråga säljaren vilket företag som levererar din beställning och be om det företagets eget spårningsnummer.

**Tull och EU-avgifter.** Sedan den 1 juli 2026 gäller en schablontull på 3 € per varukategori för nätköp från länder utanför EU i försändelser värda upp till 150 €. Sedan den 1 november 2026 tillkommer dessutom en EU-hanteringsavgift (Union handling fee) på 2 € per varukategori oavsett paketets värde. En kategori omfattar varor i samma paket med samma tullklassificering och beskrivning (samt ursprung där det krävs), oavsett antal exemplar. Läs mer i guiden om [paket i tullen](guide:customs) (på engelska).

## Om paketet från Xinghe Freight inte kommer fram eller står still

**Bara `Order information received` eller `货物电子信息已经收到`.** Säljaren har skapat beställningen, men ingen har skannat paketet ännu. Om inget nytt händer på några dagar, fråga säljaren om paketet verkligen har skickats.

**"Waiting for Xinghe Freight".** Peek har inga bekräftade steg för ditt nummer ännu: antingen känner GZYL:s spårning inte till numret, eller så innehåller den bara en förhandsplan (som kan sluta i `Delivered`), och spårningstjänsterna har inte heller något. Peek fortsätter kontrollera. För ett okänt nummer ger GZYL:s spårning en rad med numret som referens och övriga fält tomma. Peek tolkar det som att numret inte hittats: först visas "Waiting for Xinghe Freight" och efter två dagar "Check the tracking number". En förhandsplan räknas däremot som att Xinghe har ordern, så då står "Waiting for Xinghe Freight" kvar utan uppmaning att kontrollera numret. Jämför ändå numret tecken för tecken med säljarens meddelande.

**Fast i tullen.** Om du uppmanas betala importavgifter, se guiden om [paket i tullen](guide:customs) (på engelska). Står paketet kvar i flera dagar, meddela säljaren.

**Spårningen eller en annan sida visar fler steg än Peek.** De extra stegen — inklusive `Delivered` — kan vara en förhandsplan som Peek håller tillbaka i stället för att räkna som riktiga skanningar. Gå efter butikens uppskattade leveranstid och hör av dig till säljaren om den har passerat. Mer i guiden om [spårning av paket från Kina](guide:tracking-from-china) (på engelska).

**`Delivered`, men inget paket har kommit.** Steget kan vara del av en tidsplan skriven i förväg. Om inte Swiss Post levererar paketet lägger Peek till en förklaring under "Delivered": "Xinghe Freight updates on a fixed schedule, with no signature. If nothing came, ask the seller." (Xinghe Freight uppdaterar enligt fast schema, utan signatur; kom inget fram, fråga säljaren). Meddela säljaren att paketet saknas och be om namnet och spårningsnumret för det lokala utdelningsföretaget. Mer i guiden om [levererat men inte mottaget](guide:delivered-not-received) (på engelska).

**Försvunnet eller returnerat.** Det är säljaren som bokade frakten med Xinghe Freight, så vänd dig till säljaren eller marknadsplatsen för återbetalning eller ny sändning.

## Kontakt och kundtjänst hos Xinghe Freight

Enligt Xinghe Freights kinesiska webbplats (`xinghe-express.cn`) listas följande kontaktuppgifter:

- **Telefon (dygnet runt):** 19924483103 (+86 199 2448 3103 från utlandet), angiven som öppen 7×24 alla dagar i veckan.
- **E-post:** 3975605292@qq.com, för affärsförfrågningar och offerter.
- **Adress:** Guangzhou, Guangdong-provinsen (ingen gatuadress anges; besökare ombeds ta kontakt i förväg).

Webbplatsen är enbart på kinesiska och har inga särskilda kanaler för mottagare utomlands — kontaktformuläret öppnar bara ett e-postmeddelande till adressen ovan. Eftersom säljaren är Xinghes kund, kontakta säljaren först och marknadsplatsen om du inte får svar.

## Spåra ett Xinghe Freight-paket i Peek

[Peek](/) känner omedelbart igen ett `GZXH…YQ`-nummer (och även ett avkortat `GZXH…Y`-nummer) som Xinghe Freight med "Detected carrier". Om säljaren gav dig ett nummer i ett annat format kan du klistra in det ändå: Peek följer mer än 4 500 transportörer. För att välja Xinghe Freight manuellt trycker du på "Change" och söker på Xinghe Freight (eller `星和货运`), eller använder "Change carrier" på ett paket du redan lagt till.

Peek frågar GZYL:s spårning direkt, precis som sökfältet på Xinghe Freights egen sida. Det visar aktuell status, varje steg med plats samt destinationsland när posten innehåller det. Tiderna läses i Pekingtid och omvandlas till din enhets tidszon; längst ner i historiken på peektracker.com och i iPhone- och Android-apparna står "Times are in your time zone:" följt av tidszonens namn. Peek utelämnar namn, adress, postnummer, vikt, betalningsuppgifter, signatur och leveransbevis som kan finnas i spårningsposten, och visar inget beräknat leveransdatum eftersom spårningen inte lämnar något.

När spårningen inte känner till ditt nummer frågar Peek spårningstjänster i stället och visar deras kopia av stegen. Så länge ingen av dem har något steg visas "Waiting for Xinghe Freight", och efter två dagar "Check the tracking number".

Peek håller tillbaka varje steg som skrevs mer än 12 timmar före den tid det visar, varje steg som skrevs i samma sekund som ett steg skrivet mer än en halvtimme före sin tid (utom registreringen av ordern) samt varje steg daterat efter tidpunkten för Peeks kontroll i Pekingtid. På en försändelse med förhandssteg håller Peek även tillbaka `Delivered`, även om det skrevs samma dag. Peek går efter de steg det behåller, inte efter postens sammanfattningsstatus, och sorterar även bort spårningstjänsters kopior av steg det håller tillbaka som förhandsplan, inklusive `Delivered`. När spårningen helt saknar post eller inte svarar kan Peek däremot inte avgöra om ett steg hos en spårningstjänst är en plan eller en riktig skanning. När Peek håller tillbaka förhandssteg inleds historiken med raden "Other sites may show more steps, but they’re only predictions." tills paketet är levererat eller returnerat, och "Show them" visar dessa steg i grått med sin dag ("Predicted for Mon 12 Oct"). Ett sådant steg räknas aldrig som en skanning: det ändrar ingen status och skickar inga aviseringar.

Bortsett från Swiss Post följer Peek inte paketet vidare hos det lokala utdelningsföretaget: får du det lokala numret av säljaren, lägg till det som ett eget paket i Peek. Medan du har paketet öppet i Peek, eller på leveransdagen med aviseringar på, kontrollerar Peek upp till var 10:e minut; i övrigt och när paketet blivit stilla en gång i timmen. Du kan välja aviseringar för "Every scan", "Important steps" (de två tullstegen och `Delivered`, inte flyg eller sortering) eller "Delivery only". Peek kan inte kontakta Xinghe Freight, GZYL eller säljaren åt dig.

## Vanliga frågor om Xinghe Freight

### Vad är Xinghe Freight (星和货运)?

广州星和货运代理有限公司 (Guangzhou Xinghe Freight Forwarding) är en speditör i Guangzhou i Kina som grundades 2014 och arbetar med sjö-, flyg- och landfrakt, fartygsagentur och tullklarering. Paketnumren har 16 tecken: `GZXH`, tio siffror och `YQ`.

### Varför visar mitt paket från Xinghe Freight spårning från GZYL?

Sökfältet på Xinghe Freights webbplats skickar spårningsfrågor direkt till GZYL:s spårning, så formuleringarna för varje steg kommer från GZYL. Peek behåller paketet under Xinghe Freight och läser den spårningen åt dig.

### Vem levererar Xinghe Freight i Sverige?

Varken Xinghe Freights webbplats eller spårningen anger vilket lokalt företag som levererar i Sverige (Peek kan bara lämna över spårning till Swiss Post i Schweiz och Liechtenstein när den anges). Fråga säljaren vilket företag som delar ut ditt paket och be om dess lokala spårningsnummer.

### Varför visar Peek färre steg eller ingen spårning alls för mitt GZXH-nummer?

Antingen finns numret inte i spårningen ännu, eller så innehåller posten förprogrammerade steg — inklusive ett planerat `Delivered` — som Peek håller tillbaka bakom "Show them" i stället för att räkna som riktiga skanningar. Kontrollera numret mot säljarens mejl och hör av dig till säljaren om inget ändras efter några dagar.

### Hur kontaktar jag Xinghe Freight?

Kontakta säljaren eller marknadsplatsen först, eftersom det var säljaren som bokade frakten. Xinghes kinesiska webbplats (`xinghe-express.cn`) anger den dygnet runt-öppna linjen 19924483103 (+86 199 2448 3103 från utlandet) och affärsmejl 3975605292@qq.com, men saknar särskild kundtjänst för utländska mottagare.

:::sources
- [Xinghe Freight: 广州星和货运代理有限公司 (startsida, på kinesiska)](http://xinghe-express.cn/) – företagsnamn, speditionstjänster, sökfält mot GZYL:s spårning, telefonnummer och e-post
- [Xinghe Freight: 关于我们 (om oss, på kinesiska)](http://xinghe-express.cn/about.html) – grundat 2014, sjö-, flyg- och landfrakt, fartygsagentur och tullklarering
- [Xinghe Freight: 产品服务 (tjänster, på kinesiska)](http://xinghe-express.cn/services.html) – tjänsteutbud, inga uppgifter om paket för nätkunder
- [Xinghe Freight: 联系我们 (kontakt, på kinesiska)](http://xinghe-express.cn/contact.html) – 7×24-linje, affärse-post, enbart Guangzhou angivet som adress, kontaktformulär som öppnar e-post
- [Europeiska kommissionen: EU Customs Reform (på engelska)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 3 € schablontull per varukategori upp till 150 € sedan 1 juli 2026, 2 € hanteringsavgift sedan 1 november 2026
- [Europeiska kommissionen: Guidance on the EUR 3 customs duty and the Union handling fee (PDF, på engelska)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 2 € hanteringsavgift oavsett värde och definition av varukategori
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – namn, formaten `GZXH…YQ` och avkortat `GZXH…Y`, avvisning av felaktig längd, GZYL:s spårning, stegformuleringar, Hongkong-inlämning, kinesisk rad, Pekingtid, förhandssteg, spårningstjänster i reserv, överlämning till Swiss Post samt vad Peek visar, håller tillbaka och utelämnar
:::
