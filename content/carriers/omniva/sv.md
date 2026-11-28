---
title: Omniva spårning: JJEE-koder, statusar och paketskåp
description: Spåra ett Omniva-paket (Estlands post) i Estland, Lettland och Litauen: JJEE- och EE-koder, statusar, 4 dagars paketskåp, förlängning och telefonnummer.
slug: omniva-spara-paket
published: 2026-11-27
updated: 2026-11-27
---

Omniva (Estlands post / Eesti Post) sköter postutdelning och paketskåp i Estland, Lettland och Litauen. Spåra en Omniva-försändelse på minu.omniva.ee eller i Omniva-appen med din `JJEE`- eller `EE`-spårningskod, eller klistra in koden i fältet ovan. Koden finns i butikens leveransmejl eller i Omnivas SMS- eller e-postavisering.

## Omniva spårningsnummer: hur det ser ut

Omniva kallar numret "shipment tracking code" eller "shipment code (tracking number)". Den engelska returhjälpen anger exemplet `CEXXXXXXXXEE`. Här är de vanligaste formaten (alla siffror påhittade):

| Kod | Vad det är |
| --- | --- |
| `JJEE48291736405829173` | Ett Omniva-paket: `JJEE` och 17 siffror, 21 tecken totalt. |
| `JJEEMKP48291736405` | Ett annat Omniva-format: `JJEE`, tre bokstäver och 11 siffror. |
| `CE482917367EE` | Två bokstäver, nio siffror, `EE`: en försändelse inlämnad till Omniva i internationellt postformat. Paket till paketskåp i Lettland och Litauen kan också sluta på `EE`. |
| `RR739402812CN` | Ett paket från utlandet behåller avsändarens nummer; detta slutar på `CN` (postat i Kina). |

Den sexsiffriga dörrkoden i leverans-SMS:et öppnar paketskåpet och är inget spårningsnummer; detsamma gäller den sexsiffriga returkoden.

En kod som slutar på `LV` tillhör [Latvijas Pasts](carrier:latvijas-pasts), en som slutar på `LT` [Lietuvos paštas](carrier:lietuvos-pastas) och `JJFI`-koder [Posti](carrier:posti) i Finland. Fler format: [spårningsnummerformat](guide:tracking-number-formats) (på engelska).

## Omnivas spårningsstatusar förklarade

Omnivas spårningssidor finns på engelska, estniska, lettiska, litauiska och ryska, och appen rapporterar stegen på engelska. "Track & trace details" öppnar hela tidslinjen. De vanliga stegen, ungefär i ordning:

| Omniva skriver | Vad det betyder för dig |
| --- | --- |
| `Shipment registered. Shipment has not been handed over to Omniva` | Avsändaren har registrerat försändelsen men inte lämnat den till Omniva än. |
| `Sender has placed the shipment in the parcel machine` | Avsändaren har lagt paketet i ett paketskåp. |
| `The courier has picked up the shipment` | En Omniva-kurir hämtade paketet hos avsändaren. |
| `Shipment has been posted by the sender or collected by the postal service` | Ett paket från utlandet har postats i avsändarlandet. |
| `Shipment held in customs` | Väntar på tullklarering. |
| `Shipment has arrived at a postal facility for further processing` | I Omnivas nätverk, på den terminal eller sorteringslinje som anges bredvid. |
| `The shipment has been handed over to the courier` | Ute för leverans med kurir. |
| `Shipment is ready for collection from the parcel machine` | Väntar i det angivna paketskåpet. Använd den sexsiffriga dörrkoden. |
| `A notification of shipment arrival has been sent` | Omniva skickade SMS, e-post eller appnotis. Paketet har inte rört sig. |
| `Delivery attempt made` | Kuriren gjorde ett leveransförsök som misslyckades. |
| `Shipment delivered` | Utlämnat eller hämtat; paketskåpet eller postkontoret anges. |
| `Shipment is being returned to the sender` | På väg tillbaka till avsändaren. |

Två formuleringar missförstås ofta: `Delivery attempt made` betyder att försöket misslyckades, och `Shipment delivered` efter en retur betyder att paketet är tillbaka hos avsändaren.

## Omniva paketskåp, kurir och missade leveranser

**Aviseringar.** Omniva meddelar via SMS, e-post eller appnotis. I Omniva-appen är SMS-notiser avstängda som standard; slå på dem under Profile → Notification preferences.

**Vid paketskåpet.** Knappa in den sexsiffriga dörrkoden vid skåpet som anges i meddelandet. Vem som helst med koden kan hämta åt dig; ingen legitimation krävs om inte avsändaren beställt ID-kontroll.

**Liggtid.** Paketskåpen håller paketet i 4 dagar i alla tre länderna, och Omniva skickar en påminnelse före fristen. I e-tjänsten kan du förlänga tiden med upp till 4 dagar åt gången mot en avgift, högst 12 extra dagar totalt. Förvaring på ett estniskt postkontor kan förlängas en gång med upp till en månad. Hämtas paketet inte i tid går det tillbaka till avsändaren, som betalar returen.

**Kurir.** Du får ett SMS med leveransdatum och kuriren ringer innan ankomst (i Lettland och Litauen för att boka dag och tid). Kurirer levererar till dörren upp till 30 kg i Estland och Lettland och 25 kg i Litauen. Passar inte tiden kan du be kuriren om en annan dag eller ett närliggande paketskåp.

**Missad kurirleverans.** Paket upp till storlek L går till ett närliggande paketskåp i 4 dagar. I Estland kan ett paket upp till XL vänta 7 dagar på ett postkontor och XXL i 7 dagar på leveranscentret. I Lettland väntar större paket 4 dagar på leveranscentret; i Litauen går de tillbaka till avsändaren.

**Omdirigering.** Kostnadsfri tills paketet når sorteringscentret via Omnivas e-postlänk eller e-tjänsten; därefter, eller vid byte till kurirleverans, tillkommer en avgift. Ett paket kan också omdirigeras om skåpet var fullt.

## Omniva-paket försenat, skadat eller i tullen

**Ej levererat eller ingen uppdatering.** Kontrollera först om Omnivas leveranstid (nedan) har passerat. Har den gjort det ber Omniva mottagaren kontakta avsändaren, som skickar in formuläret "Request parcel delivery status". Se [varför spårningen stannar](guide:tracking-not-updating) (på engelska).

**Tomt fack eller fel plats.** Kontakta Omnivas privatkundtjänst i ditt land.

**Skadat.** I Estland kan mottagaren skicka in formuläret för skadad eller förlorad försändelse inom 6 månader från överlämnandet, men normalt ersätts bara avsändaren. I Lettland och Litauen hänvisar hjälpsidorna mottagare till avsändaren (i Litauen med foton på paket och emballage), som gör reklamationen, men Omnivas lettiska villkor tillåter även skriftlig reklamation från mottagaren inom 6 månader från överlämnandet.

**Tull (Estland).** Sedan 1 juli 2021 måste varje paket från länder utanför EU deklareras oavsett värde inom 30 dagar. Du kan ge Omniva fullmakt eller deklarera själv hos skatte- och tullmyndigheten. Se [paket i tullen](guide:customs) (på engelska).

> Bluffmeddelanden i Omnivas namn ber om en liten leverans- eller tullavgift via en falsk länk. Omniva skickar aldrig SMS från utländska nummer och mejlar bara från omniva.ee, omniva.lv, omniva.lt eller omniva.eu. Kontrollera alltid på minu.omniva.ee.

## Omniva kundtjänst och telefonnummer

Omnivas kontaktsidor för privatkunder anger följande (lokal tid i respektive land):

| Land | Telefon | E-post | Öppettider |
| --- | --- | --- | --- |
| Estland | +372 661 6616 | info@omniva.ee | Mån–fre 8:00–19:00, lör 9:00–15:00 |
| Lettland | +371 27042424 | info@omniva.lv | Mån–fre 8:00–18:00, lör 9:00–15:00 |
| Litauen | +370 700 55077 | info@omniva.lt | Mån–fre 8:00–18:00, lör 9:00–15:00 |

Varje webbplats har även formulär för leveransstatus, skadat eller borttappat paket samt omdirigering. I Litauen följer samtalskostnaden Telias taxa. Omniva anger ingen chatt.

## Spåra Omniva med Peek

[Peek](/) känner genast igen `JJEE`-koder i båda formaten ovan som Omniva. Länkar från minu.omniva.ee, mana.omniva.lv och mano.omniva.lt (`/track/` + koden) samt omniva.ee- eller omniva.lt-länkar med `barcode=` fungerar för alla koder. Inget postnummer behövs.

Ett nummer i postformat som slutar på `EE`, börjar på C, R, E eller L (men inte CR, CV eller RO) och har giltig kontrollsiffra är Omnivas till formen, så Peek känner genast igen det som Omniva. För andra inledande bokstäver frågar Peek både Omniva och Chronopost och väljer den som känner till koden. Känner ingen av dem igen den än visas "Not found at … yet. Peek will keep checking after you add it.", och Peek fortsätter fråga, allt mer sällan ju fler dagar som går. En kod som Peek anger som Omnivas men som Omniva ännu inte listar visar "Waiting for Omniva"; känner Omniva fortfarande inte till den två dagar senare och inget annat har svarat för den, ber Peek dig kontrollera numret, välja en annan transportör eller ta bort paketet och kontrollerar därefter dagligen (och veckovis när en vecka har gått sedan du lade till det). Fel kontrollsiffra avvisas av Omniva även vid manuellt val. Ett utländskt nummer i postformat spåras hos Omniva efter "Change" eller via en Omniva-länk; när avsändarposten anger destination Estland (eller inget land när du spårar från Estland) frågar Peek även Omniva och byter dit när den har en minst lika ny skanning (för Lettland och Litauen frågar den Latvijas Pasts respektive Lietuvos paštas). Ett `EE`-paket utomlands kan på samma sätt följas vidare hos mottagarlandets post.

Peek visar Omnivas engelska statusar, varje skanningsplats på kartan, destinationsland, utlämningstid samt vilket paketskåp eller postkontor paketet väntar i. Adresser, postnummer och orsaker till misslyckade försök utelämnas, och varken förväntad leverans eller vikt visas. Medan paketet är öppet eller på leveransdagen med notiser på frågar Peek Omniva upp till var 10:e minut och varannan minut från 06:00 estnisk tid den dag Omniva skannar ut paketet för leverans, medan Omnivas egen spårning svarar; annars varje timme. Efter två dagar utan nytt (12 timmar vid "Label created" eller "Received by carrier") kontrollerar den varje timme, och var 6:e timme efter sju dagar vid "Label created". Efter fyra dagar utan ny rad vid "Label created", "Received by carrier" eller "In transit" visas "No tracking update since …", "Parcels often go quiet on long flights, at customs or over a weekend. Peek keeps asking Omniva." och länken "Get help from Omniva"; efter tre veckor "Nothing new for over three weeks. If it still hasn’t arrived, ask the seller. Peek keeps asking Omniva." Notiser: "Every scan", "Important steps" eller "Delivery only".

## Frågor om Omniva spårning

### Hur spårar jag ett Omniva-paket (Estlands post)?

Skriv in spårningskoden (t.ex. en `JJEE`-kod eller ett nummer på 13 tecken i postformat som slutar på `EE`) på minu.omniva.ee, i Omniva-appen eller i fältet ovan. Omniva spårar på kod och inte telefonnummer, men ett konto på minu.omniva.ee listar dina inkommande paket.

### Hur lång tid tar Omniva i Estland, Lettland och Litauen?

Omniva anger 1 till 2 arbetsdagar inom ett baltiskt land och 2 till 3 arbetsdagar mellan dem från överlämning. Omnivas estniska och litauiska sidor säger att kurirer levererar nästa dag och senast 3 dagar efter överlämning; den lettiska sidan anger bara de 3 dagarna.

### Hur länge ligger ett paket i ett Omniva-paketskåp, och kan jag förlänga tiden?

Omnivas paketskåp håller paket i 4 dagar i Estland, Lettland och Litauen. I e-tjänsten kan du mot avgift förlänga med upp till 4 dagar åt gången, högst 12 extra dagar totalt, innan paketet returneras till avsändaren.

### Vad betyder "Delivery attempt made" på Omnivas spårning?

Det betyder att kuriren försökte leverera men misslyckades — inte att paketet levererades. Paket upp till storlek L hamnar då i ett närliggande paketskåp i 4 dagar.

### Hur kontaktar jag Omnivas kundtjänst?

Ring +372 661 6616 i Estland (info@omniva.ee), +371 27042424 i Lettland (info@omniva.lv) eller +370 700 55077 i Litauen (info@omniva.lt). Telefonlinjerna har öppet måndag–fredag (8:00–19:00 i Estland, 8:00–18:00 i Lettland och Litauen) och lördag 9:00–15:00.

:::sources
- [Omniva Estonia: Track and receive parcels](https://www.omniva.ee/en/track-and-receive-parcels/) – shipment code, door code, receiving options, courier SMS and call
- [Omniva Latvia: Track and receive](https://www.omniva.lv/en/track-and-receive-parcels/) – receiving in Latvia, courier, 30 kg
- [Omniva Lithuania: Track and receive](https://www.omniva.lt/en/track-and-receive-parcels/) – receiving in Lithuania, courier, 25 kg
- [Omniva: How to track your parcel?](https://www.omniva.ee/help/how-to-track-your-parcel-ee) – tracking code by SMS or email, summary and details, parcels from abroad
- [Omniva: Parcel delivery timeframes](https://www.omniva.ee/help/omniva-parcel-delivery-timeframes-ee) – delivery times, courier next day
- [Omniva Latvia: Omniva sūtījumu piegādes termiņi](https://www.omniva.lv/palidziba/omniva-sutijumu-piegades-termini) – courier within 3 days (Latvian)
- [Omniva Lithuania: OMNIVA siuntų pristatymo terminai](https://www.omniva.lt/pagalba/omniva-siuntu-pristatymo-terminai-lt) – courier next working day (Lithuanian)
- [Omniva: How long items are stored by Omniva?](https://www.omniva.ee/help/how-long-items-are-stored-by-omniva-ee) – storage in each country, returns
- [Omniva Latvia: Cik ilgi Omniva uzglabā sūtījumus?](https://www.omniva.lv/palidziba/cik-ilgi-omniva-uzglaba-sutijumus) – reminder in Latvia (Latvian)
- [Omniva: Extend parcel storage time](https://www.omniva.ee/help/extend-parcel-storage-time-ee) – up to 4 days at a time, 12 extra days at most
- [Omniva: Will I get notified about a delivery?](https://www.omniva.ee/help/will-i-get-notified-about-a-delivery-ee) – notices, courier calls
- [Omniva: Issue with the delivery SMS or email notification](https://www.omniva.ee/help/i-have-an-issue-with-the-delivery-smsemail-notification) – app notification settings
- [Omniva: When courier parcels are delivered?](https://www.omniva.ee/help/when-courier-parcels-are-delivered-ee) – courier hours
- [Omniva: Will the courier deliver parcels to my door?](https://www.omniva.ee/help/will-the-courier-deliver-parcels-to-my-door-ee) – weight limits
- [Omniva: Not at the address when the courier delivers](https://www.omniva.ee/help/what-happens-if-im-not-at-the-delivery-address-when-the-courier-delivers-the-parcel-ee) – missed courier options
- [Omniva: Can someone else pick up my parcel?](https://www.omniva.ee/help/can-someone-else-pick-up-my-parcel-ee) – collection by another person
- [Omniva: Do I need to show ID?](https://www.omniva.ee/help/do-i-need-to-show-id-when-receiving-items-via-omniva-ee) – ID rules
- [Omniva: Redirect a parcel or letter](https://www.omniva.ee/help/redirect-a-parcel-or-letter-ee) – redirection rules and fees
- [Omniva: My parcel hasn't been delivered](https://www.omniva.ee/help/my-parcel-hasn%E2%80%99t-been-delivered-ee) – who acts when a parcel is late
- [Omniva: My parcel is damaged](https://www.omniva.ee/help/my-parcel-is-damaged-ee) – damage form, 6 months
- [Omniva Latvia: Sūtījumu pakalpojumu standarta noteikumi privātajiem klientiem](https://www.omniva.lv/wp-content/uploads/sites/6/2026/06/LV_Omniva-Standard-Terms-and-Conditions-of-Parcel-Services-for-Private-Customers_2026_07_22.pdf) – sender or recipient complains within 6 months (Latvian)
- [Omniva: The parcel machine locker is empty](https://www.omniva.ee/help/the-parcel-machine-locker-is-empty-or-a-shipment-is-missing) – contact support
- [Omniva: How to return a parcel?](https://www.omniva.ee/help/manage-shipments-general/return-a-parcel-containing-items-purchased-in-estonia-latvia-or-lithuania-ee) – example code CEXXXXXXXXEE, return code
- [Omniva Estonia: Customs: receiving](https://www.omniva.ee/en/customs-receiving/) – declaring within 30 days
- [Omniva: Which shipments require a customs declaration](https://www.omniva.ee/help/which-shipments-require-a-customs-declaration) – all non-EU parcels since 1 July 2021
- [Omniva Estonia: Contacts for private customers](https://www.omniva.ee/en/contacts-for-private-customers/) – phone, email, hours, forms, Eesti Post
- [Omniva Latvia: Contacts for private customers](https://www.omniva.lv/en/contacts-for-private-customers/) – phone, email, hours
- [Omniva Lithuania: Contacts for private customers](https://www.omniva.lt/en/contacts-for-private-customers/) – phone, email, hours, Telia tariff
- [Omniva: How to distinguish scams from genuine parcel notifications](https://www.omniva.ee/en/frauds/) – scam signs, phone numbers, email domains
- [Omniva Estonia: Home](https://www.omniva.ee/en/) – scam warning, e-service
- [Omniva: Tracker interface strings](https://minu.omniva.ee/locales/common/common_en.json) – tracker languages
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – what Peek reads
:::
