---
title: PostNL spårning: nummer, statusar och leverans utomlands
description: Spåra PostNL utanför Nederländerna: vad NL- och 3S-nummer betyder, vad statusarna på postnl.post säger, vem som levererar i ditt land och vem du kontaktar.
slug: postnl-spara-paket
published: 2026-11-28
updated: 2026-11-28
---

PostNL är den nederländska posten. Den skickar paket och brev från nederländska butiker utomlands, och via Spring GDS, sin internationella del, fraktar den paket åt nätbutiker över hela världen. Båda spåras på PostNL:s internationella webbplats, [postnl.post](https://postnl.post/), eller i rutan ovan. I ditt land levereras paketet av ett lokalt fraktbolag, ofta den nationella posten. Numret finns i butikens leveransmejl eller på din beställningssida.

## Spårningsnummer hos PostNL: hur det ser ut

PostNL använder två typer av streckkoder enligt sin utvecklardokumentation. Med påhittade siffror:

| Nummer | Vad det är |
| --- | --- |
| `CW482917367NL` | Två bokstäver, 9 siffror och sedan NL: PostNL:s internationella försändelsenummer. Ett inledande C är ett paket till ett land utanför EU, som Storbritannien, USA, Kanada eller Australien. |
| `LA482917367NL` | L: ett småpaket eller brev med track & trace. `LW` är samma tjänst. |
| `RI482917367NL` | R: ett rekommenderat brev. `RF` är samma tjänst. |
| `UL482917367NL` | U: en ospårad försändelse. Räkna inte med skanningar i ditt land. |
| `3SABCD482910573` | 3S, en kundkod av bokstäver och sedan siffror, 15 tecken totalt: en PostNL-paketstreckkod. |
| `3SABCD4829105` | Samma streckkod i sin 13-teckensform. PostNL använder den för Parcel EU, sin pakettjänst från Nederländerna till andra EU-länder såsom Irland. |

Den sista av de nio siffrorna i ett NL-nummer är en kontrollsiffra, så en felaktig siffra gör vanligtvis numret ogiltigt.

postnl.post tar upp till 150 nummer samtidigt. Sidan svarar på NL-nummer och 3S-streckkoder på 15 tecken, men avvisar sådana på 13 tecken med "The shipment barcode was incorrect.". För dem — till exempel en Parcel EU-streckkod till Irland — fråga butiken efter partnerns nummer. När en butik bokar en Parcel EU-etikett ger PostNL den streckkoden för partnern utomlands som ska leverera paketet, och den streckkoden går att spåra hos partnern. Den kan fortfarande ändras medan paketet är i Nederländerna.

## Förklaring av PostNL:s spårningsstatusar

postnl.post visar en förloppsindikator, `Pre-advised`, `Preparing`, `Departed`, `Transit`, `Arrived`, `Customs`, `Processing`, `Delivered`, med skanningarna nedanför på engelska. De flesta skanningar anger vilket land de skedde i. Raderna du möter, i ungefärlig ordning:

| postnl.post skriver | Vad det betyder för dig |
| --- | --- |
| `Pre-advised`, `Preparing` | Avsändaren har bokat försändelsen och skickat uppgifterna till PostNL. PostNL har kanske inte paketet ännu. |
| `Consignment received at the PostNL Acceptance Centre`, `The shipment is handed over in bulk final acceptance of the item to be confirmed` | PostNL har tagit emot avsändarens sändning; din enskilda försändelses egen skanning kan fortfarande dröja. |
| `The item is received by PostNL` | PostNL har skannat din försändelse. Detta kan komma flera dagar efter raden om mottagningscentret. |
| `Departed`, `Transit` | På väg mellan länder, ofta med flyg. |
| `Arrived` | Har nått ett land längs rutten, vanligtvis ditt. |
| `Customs` | Hos tullen i mottagarlandet. |
| `The item has arrived at the domestic sorting centre`, `The item is at the local sorting centre` | Sorteras i landet som visas bredvid skanningen. |
| `The item is at the local delivery office` | På det kontor som ska leverera det. |
| `The item is out for delivery` | På väg till dig. |
| `Driver is en route to the pickup location` | På väg till ett utlämningsställe, inte till din dörr. |
| `Pick-up point` | Väntar på dig hos ett utlämningsställe. |
| `Unsuccesfull`, `Undelivered` | Ett leveransförsök misslyckades. Stavningen är PostNL:s egen. |
| `Undeliverable item, has been returned to shipper` | På väg tillbaka till avsändaren. |
| `The item has been returned and arrived at PostNL` | Returen har nått PostNL. |
| `Delivered` | Levererat. |

`The shipment barcode was not found.` betyder att postnl.post ännu inte har något under det numret, eller att ett tecken är fel. "Destination: unknown" på resultatsidan betyder att ingen skanning ännu har angett mottagarlandet. Om du ger Peek leveranspostnumret för ett 3S-paket läser den även PostNL:s egen spårare, vars rader skiljer sig åt: för ett paket som PostNL levererar utomlands visas `Your parcel has crossed the border`, `Your parcel's at a sorting centre in the destination country`, `Your shipment is on its way to the recipient` (ute för leverans) och `We missed you; we’ll try again` (missad leverans).

## Vem levererar PostNL-paket i ditt land

PostNL eller Spring GDS transporterar paketet till ditt land. Spring GDS säger att det sköter den internationella sträckan medan en lokal partner sköter slutleveransen ("manages the international leg, while a local partner manages the final delivery"), och att paketet när det kommer in i ditt land ofta överlämnas till den lokala posttjänsten ("it is often handed over to your local postal service (e.g., USPS, Royal Mail, Correos)").

- **USA**: [USPS](carrier:usps). En förfrågan om en försändelse från utlandet går genom avsändaren, som tar det med sitt eget postbolag. För företagspaket till det amerikanska fastlandet och Puerto Rico undertecknar butiken ett tillägg som låter PostNL tullklarera och förskottsbetala importtullarna åt den.
- **Storbritannien**: [Royal Mail](carrier:royal-mail), en av de lokala postoperatörer Spring GDS namnger.
- **Irland**: [An Post](carrier:an-post) för postförsändelser. En Parcel EU-streckkod går i stället till PostNL:s partner i Irland, som PostNL inte namnger.
- **Kanada**: [Canada Post](carrier:canada-post). Om dess spårningsverktyg inte visar något ber Canada Post dig att låta avsändaren be sitt eget lands post utreda ärendet.
- **Australien**: [Australia Post](carrier:australia-post).

Ett NL-nummer följer UPU:s S10-standard, den numrering som postoperatörer delar för försändelser de utbyter, så du kan prova det på din nationella posts webbplats när paketet väl kommit fram. En ospårad `UL`-försändelse visar kanske inget där.

Leveransförsök, avier och hur länge ett paket väntar följer ditt lokala fraktbolags regler; se [missad leverans](guide:missed-delivery) och [outlösta paket](guide:uncollected-parcel) (på engelska). Tull och avgifter beskrivs i [paket som hålls i tullen](guide:customs) (på engelska).

## PostNL-paket uppdateras inte, försvunnet eller returnerat

**Inga skanningar ännu.** `Pre-advised` eller `Preparing` betyder att säljaren har bokat försändelsen men kanske ännu inte lämnat in den. Spring GDS säger att du bör ge det 24 till 48 timmar efter att du fått numret innan den första skanningen kommer.

**Fast i transit.** Efter `Departed` eller `Transit` får ett paket som reser med flyg eller båt inga skanningar förrän det kommer fram, och tullen håller ofta paket i flera dagar. Prova sedan samma nummer hos din nationella post. Efter fyra dagar utan ny skanning vid "Label created", "Received by carrier" eller "In transit" visar Peek dagen för senaste skanning i din lista ("No tracking update since Mon 12 Oct") och på paketsidan: "Parcels often go quiet on long flights, at customs or over a weekend. Peek keeps asking PostNL.", med länken "Get help from PostNL" till PostNL:s kundtjänst. Efter tre veckor utan skanning står det i stället: "Nothing new for over three weeks. If it still hasn’t arrived, ask the seller. Peek keeps asking PostNL." Mer i [varför spårning slutar uppdateras](guide:tracking-not-updating) (på engelska).

**Levererat men saknas.** Spring GDS föreslår att du kollar med grannar, säkra platser som en veranda eller ett garage och eventuell reception. De nämner också att fraktbolag ibland markerar en försändelse som levererad upp till 24 timmar innan den faktiskt kommer fram. Kontakta sedan säljaren. Se [levererat men inte mottaget](guide:delivered-not-received) (på engelska).

**Borttappat eller skadat.** Säg till säljaren direkt och fotografera skadad förpackning: säljaren behöver bilderna för att lämna in ett krav hos Spring GDS. Se [förlorat paket](guide:lost-parcel) (på engelska).

**Returnerat.** Be säljaren skicka om beställningen eller återbetala dig. Spring GDS anger "Incorrect Address" och "Unclaimed" efter liggtiden som vanliga orsaker och säger att en retur inte kan stoppas när den väl har påbörjats. Se [returnerat till avsändaren](guide:returned-to-sender) (på engelska).

## Kontakt och kundtjänst hos PostNL

Uppgifter lästa den 22 oktober 2026:

- **postnl.post**: "Questions about your delivery outside The Netherlands? Please contact your webshop." För leveranser inom Nederländerna hänvisar sidan till PostNL:s kundtjänst.
- **Spring GDS**: kan inte diskutera försändelser med konsumenter på grund av dataskydd och avtalen med säljarna. Säljaren har kontot och inleder utredningar hos Spring GDS.
- **Ditt lokala fraktbolag**: för en missad leverans eller ett nytt leveransdatum, kontakta det direkt; Spring GDS kan inte ordna lokala omleveranser.

För allt annat är butiken eller marknadsplatsen du köpte ifrån den snabbaste vägen.

## Spåra ett PostNL-paket med Peek

[Peek](/) känner omedelbart igen NL-nummer med giltig kontrollsiffra och 3S-streckkoder med en kundkod på fyra bokstäver. En 3S-streckkod med tre bokstäver går till [DHL eCommerce Netherlands](carrier:dhl-ecommerce-nl), även om PostNL:s egna riktlinjer visar koder med tre bokstäver också. För en kortare kundkod kan Peek inte avgöra fraktbolaget utifrån formen (med två bokstäver frågar Peek även DHL eCommerce Netherlands): klistra in det ändå, Peek följer fler än 4 500 fraktbolag. Det visar "Checking tracking services…" medan det kollar; om fel fraktbolag kommer upp, välj PostNL med "Change". Länkar från postnl.post (`/track?barcodes=`) och mailingtechnology.com, Spring GDS spårare (`/tracking?tn=`), läses som PostNL. För en 3S-streckkod erbjuder formuläret även det valfria fältet "Delivery postcode": "PostNL shows a parcel sent within the Netherlands only with the delivery postcode." Med det, och landet för ett postnummer utanför Nederländerna, frågar Peek först PostNL:s egen spårare; utan det frågas andra källor.

Peek läser postnl.post, samt PostNL:s egen spårare när du angett postnumret. Det visar statusen, historiken med landet för varje skanning, leveranstiden och mottagarlandet. Skanningstider visas i din enhets tidszon (på peektracker.com och i apparna för iPhone och Android avslutas historiken med "Times are in your time zone:" och tidszonens namn); i länder med flera tidszoner, som USA, Kanada och Australien, måste Peek anta en, så en skanning kan ligga några timmar fel. PostNL anger varken beräknad leveransdag eller avsändare. För ett NL-nummer frågar Peek, så snart PostNL anger mottagarlandet, det landets nationella post — som Royal Mail eller USPS — under samma nummer, och växlar över till den när den visar nyare framsteg. postnl.post kallar 3S-streckkoder på 13 tecken felaktiga, så för dem, utan postnumret, söker Peek efter paketet via [universella spårningstjänster](guide:universal-tracker) (på engelska).

Peek kontrollerar PostNL upp till var 30:e minut, även under utkörning, även om en manuell uppdatering kan kontrollera tidigare. När ett annat fraktbolag tar över leveransen följer Peek takten för bolaget som levererar paketet. Peek kan meddela dig vid varje skanning, vid viktiga steg eller bara på leveransdagen. Det kan inte kontakta PostNL eller butiken åt dig.

## Frågor om PostNL-spårning

### Var är mitt PostNL-paket?

Den senaste skanningen visar vilken fas det är i och vilket land det befinner sig i. Så snart en skanning visar ditt land, prova samma nummer på din nationella posts webbplats: den har vanligtvis mer detaljerade lokala skanningar.

### Vad betyder "The shipment barcode was incorrect"?

postnl.post tar inte emot den streckkoden. Det rör sig vanligtvis om en 3S-streckkod på 13 tecken, PostNL:s Parcel EU-format. Be butiken om leveranspartnerns nummer och spåra det hos partnern.

### Vem levererar PostNL-paket i USA eller Storbritannien?

Vanligtvis den nationella posten. Spring GDS, PostNL:s internationella gren, säger att ett paket som kommer in i ditt land ofta överlämnas till den lokala posttjänsten, såsom USPS eller Royal Mail. Så snart en skanning visar ditt land, prova samma NL-nummer på det postbolagets webbplats.

:::sources
- [PostNL: International Tracking](https://postnl.post/) – 150 nummer, förloppsindikator, skanningstexter, svaren "not found" och "incorrect", destination, kontakta din webshop
- [PostNL Developer: Barcode guidelines](https://developer.postnl.nl/docs/#/http/api-endpoints/send-track/barcode/guidelines) – 3S-streckkoder på 13 eller 15 tecken, kundkod med bokstäver, S10-serien NL
- [PostNL Developer: Product codes International](https://developer.postnl.nl/docs/#/http/reference-data/product-codes-international) – Parcel EU 3S med 13 tecken och destinationer, partnerstreckkod, Parcel Non-EU C och CW, LA/LW, RI/RF, UL, USA-tillägg för tullklarering och förskottsbetalda tullar
- [Spring GDS: We are Spring GDS](https://www.spring-gds.com/about-us/) – del av PostNL-koncernen, gränsöverskridande e-handel
- [Spring GDS: Track and Trace](https://www.spring-gds.com/track/) – lokal posttjänst, lokal partner, nummerformat, första skanning, transit- och tullpauser, levererat men inte mottaget, reklamationer, returer, omleveranser, ingen konsumentkontakt
- [Spring GDS: Contact us](https://www.spring-gds.com/contact-us/) – spårningsfrågor går till webshopen
- [Canada Post: Tracking international packages](https://www.canadapost-postescanada.ca/cpc/en/support/kb/tracking/tracking-international-packages.page) – avsändaren låter sin egen post utreda
- [USPS: International Inquiries – The Basics](https://faq.usps.com/s/article/International-Inquiries-The-Basics) – en avsändare utanför USA går via sitt eget postbolag
- [UPU: S10 standard, Identification of postal items](https://www.upu.int/UPU/media/upu/files/postalSolutions/programmesAndServices/standards/S10-12.pdf) – numrering som delas mellan postoperatörer, kontrollsiffra
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – vad Peek läser och visar
:::
