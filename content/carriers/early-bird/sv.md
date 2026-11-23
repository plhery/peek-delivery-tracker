---
title: Early Bird spårning — nattleverans, paketskåp och hjälp
description: Spåra Early Bird-paket i Sverige: 20-siffriga kollinummer, leverans 01:00–07:00, iBoxen-paketskåp i 5 dagar, överlämning till PostNord/DSV och kontakt.
slug: early-bird-sparning
published: 2026-11-23
updated: 2026-11-23
---

**Early Bird** är ett svenskt paketföretag som ägs av Sveriges ledande mediehus och levererar e-handelspaket med tidningsbuden — nattetid eller tidigt på morgonen direkt till din postlåda eller ytterdörr i nästan hela Sverige ([earlybird.se](https://earlybird.se/)). Du kan spåra paketet på [Early Bird Spåra och hjälp](https://earlybird.se/spara-och-hjalp), via länken i sms eller mejl från Early Bird, eller genom att klistra in kollinumret i fältet ovan.

## Early Bird kollinummer och format

Kollinumret hittar du i sms/mejl från Early Bird eller i e-handlarens orderbekräftelse (även om du råkade ange fel mobilnummer fungerar kollinumret från butiken). Formaten Peek känner igen (med påhittade siffror):

| Nummer (påhittat) | Vad det är |
| --- | --- |
| `00373402216482917364` | 20 siffror: `00`, valfri siffra, `73402216` och nio siffror där den sista är en kontrollsiffra (streckkodsnummer). |
| Annan referens | 8 till 30 bokstäver och siffror — kan spåras hos Early Bird i Peek om du väljer Early Bird manuellt. |

Eftersom både [PostNord](carrier:postnord) och [Bring](carrier:bring) också använder 20-siffriga koder som börjar med `00` bekräftar Peek vilken transportör som har paketet. Saknar du nummer? Se [how to find a tracking number](guide:find-tracking-number) (på engelska).

## Vad Early Birds spårningsstatusar betyder

Early Birds spårning visar varje steg på svenska på den svenska sidan och på engelska på den engelska sidan. De vanligaste formuleringarna i turordning:

| Early Bird på svenska | Engelsk motsvarighet (i Peek) | Vad det betyder |
| --- | --- | --- |
| `Din leverans är bokad med Early Bird.` | `Your delivery is booked with Early Bird.` | Butiken har bokat leveransen; Early Bird har inte tagit emot paketet fysiskt ännu. |
| `Early Bird har tagit emot ditt paket.` | `Early Bird has received your package.` | Early Bird har tagit emot paketet — leveranstiden börjar räknas härifrån. |
| `Ditt paket har kommit till terminalen i Stockholm.` | `Your package has arrived at the terminal in Stockholm.` | Skannat på en sorteringsterminal eller rutt (visar terminalen, inte din hemadress). |
| `Vi misslyckades med att leverera ditt paket som planerat. Vi meddelar dig snarast med information om hur vi gör för att ditt paket ska komma fram.` | `We failed to deliver your package as planned. We will notify you as soon as possible...` | Leveransförsöket misslyckades; Early Bird meddelar nästa steg (nytt försök eller ombokning). |
| `Vi kunde inte komma in i porten och misslyckades därför med att leverera ditt paket. Vi kommer göra ett nytt försök så snart som möjligt.` | `We were unable to access the entrance and therefore failed to deliver your package...` | Budet kom inte in genom porten; Early Bird gör ett nytt försök. |
| `Vi har bokat in ett nytt försök att leverera ditt paket.` | `We have scheduled a new attempt to deliver your package.` | Ett nytt utkörningsförsök är inbokat. |
| `Eftersom vi inte kunde leverera paketet hem till dig så kommer vi boka om det till ett paketskåp. Vi meddelar dig så snart vi vet vilket skåp det blir.` | `Since we were unable to deliver the parcel to your home, we will reroute it to a parcel locker...` | Paketet styrs om till ett paketskåp i närheten. |
| `Paketet är på väg. Du får ett meddelande när det är redo att hämtas ur paketskåpet.` | `Your package is on its way. You will receive a notification when it is ready to be collected from the parcel locker.` | På väg ut till valt paketskåp. |
| `Ditt paket finns nu redo att hämtas från paketskåpet. Du behöver iBoxens app för att öppna luckan.` | `Your package is now ready for collection from the parcel locker. The iBoxen app is required to open the box.` | Klart att hämta i iBoxen-skåpet. |
| `Paketet har hämtats ur paketskåpet.` | `The package has been collected from the parcel locker.` | Uthämtat ur paketskåpet. |
| `Paketet är levererat till din dörr.` | `The package has been delivered to your door.` | Lämnat utanför din ytterdörr. |
| `Paketet är levererat till din postlåda.` | `The package has been delivered to your mailbox.` | Lagt i din postlåda. |
| `Paketet är levererat i en påse på din postlåda.` | `The package has been delivered in a bag on your mailbox.` | Hängt i en påse på din postlåda (eller dörr). |

Äldre försändelser i Early Birds tidigare system visas alltid på svenska med texter som `Leveransen till dig är bokad av e-handlaren.`, `Paketet har ankommit till sorteringsterminal Göteborg` och `Ditt paket har nu levererats av Early Bird. Om paketet inte fick plats i postlådan så har det hängts i en påse på din postlåda alternativt dörr`.

## Leveranstider, nattleverans och iBoxen-paketskåp

Enligt [Early Birds vanliga frågor](https://earlybird.se/vanliga-fragor):

- **När paketet kommer (01:00–07:00):** Hemleveranser sker normalt nattetid eller tidig morgon mellan **01:00 och 07:00**, så paketet ligger där senast kl. 07:00 utan att du behöver vara vaken eller hemma. Utbärning sker upp till **sex dagar i veckan** (inklusive helg beroende på ort).
- **Leveranstid:** Normal hemleverans tar **1 till 3 dagar** från det att Early Bird tagit emot paketet (inte från orderdagen). Med **Early Bird Express** levereras beställningen samma natt, senast kl. 07:00 morgonen efter. Planerad leveransdag visas i länken i Early Birds sms eller mejl.
- **Var paketet lämnas:** I postlådan, i påse på postlådan eller utanför din lägenhets- eller ytterdörr (tidningsbuden har ofta tillgång till portar). Ett sms eller mejl berättar exakt var paketet lämnades.
- **Om leveransen misslyckas:** Beroende på vilken tjänst e-handlaren valt gör Early Bird ett nytt försök, bokar om till paketskåp eller lämnar över paketet till en annan transportör (som [PostNord](carrier:postnord) eller [DSV](carrier:dsv)) för leverans till ombud ([missed delivery](guide:missed-delivery)).
- **Paketskåp (Early Bird Box / iBoxen):** Väljer du Early Bird Box går paketet till ett utomhusskåp från **iBoxen** som är öppet dygnet runt. Du öppnar luckan med **iBoxens app och Mobilt BankID** (registrera samma telefonnummer som vid köpet). Paketet ligger kvar i **5 dagar** och liggtiden kan normalt inte förlängas innan det returneras till e-handlaren ([uncollected parcels](guide:uncollected-parcel)). Är skåpet fullt flyttas paketet till ett närliggande skåp. Via iBoxens app kan du vid stöd även dela upphämtningen med en annan person med BankID.
- **Ändringar:** När paketet väl är på väg kan Early Bird inte ändra leveransdag eller adress, och normalt inte byta leveranssätt eller avboka — kontakta e-handlaren före utskick ([change delivery address](guide:change-delivery-address)).

## Försenat, saknat eller skadat paket

- **Står bara som "bokad":** Early Bird har inte fått paketet från e-handlaren ännu; kontakta butiken ([tracking that isn't updating](guide:tracking-not-updating)).
- **Markerat som levererat men borta:** Kontrollera adressen i orderbekräftelsen, titta i och bredvid postlådan samt utanför dörren, fråga hushållet och grannar, och skicka sedan formuläret *Saknar paket trots bekräftelse* på [Kontakta oss](https://earlybird.se/kontakta-oss) ([delivered not received](guide:delivered-not-received)).
- **Skadat paket:** Fyll i formuläret för skadat paket med tydligt foto och kontakta även e-handlaren, som ansvarar för varan och eventuell ersättning ([damaged parcel](guide:damaged-parcel)).

> Early Bird tar aldrig ut någon extra avgift via sms för att släppa ett paket — frakten betalas normalt i kassan. Klicka inte på oväntade betallänkar; kolla alltid statusen direkt på [earlybird.se/spara-och-hjalp](https://earlybird.se/spara-och-hjalp) ([delivery scam texts](guide:delivery-scam-texts)).

## Spåra Early Bird med Peek

- **Ett 20-siffrigt Early Bird-nummer.** Klistra in det i [Peek](/). Eftersom PostNords nummer ser likadana ut visar Peek "Checking tracking services…" och frågar Early Bird först, tillsammans med PostNord och andra transportörer som numret passar. När Early Bird känner till det säger Peek "Early Bird has this parcel." Om ingen transportör känner till det ännu utser Peek Early Bird som trolig transportör: paketet visar "Probably Early Bird" och "Waiting for Early Bird’s first scan", och Peek fortsätter fråga. För att lägga det under Early Bird trycker du på "Change" innan du spårar och väljer Early Bird under "Fits this number", eller använder "Change carrier" senare.
- **En länk till Early Birds spårning.** En earlybird.se-länk till `spara-och-hjalp`, `en/track-and-help` eller `tracking` med `id=`, eller en track.earlybird.se-länk med `a=`, utser Early Bird direkt, vilket nummer det än gäller.
- **En annan referens.** Klistra in den ändå: Peek följer över 4 500 transportörer. För att spåra den hos Early Bird trycker du på "Change" och söker efter Early Bird, eller använder "Change carrier" senare. För Early Bird tar Peek 8 till 30 bokstäver och siffror, och ett 20-siffrigt nummer som börjar med `00` behöver en giltig kontrollsiffra. Annars säger Peek att Early Bird "doesn’t issue numbers like this one".
- **Inte listat ännu.** Ett paket under Early Bird visar "Waiting for Early Bird" tills Early Bird listar det, och Peek fortsätter fråga. Om Early Bird säger att numret är fel visar Peek "Check the tracking number".

Peeks gränssnitt är på engelska.

Peek läser Early Birds spårning genom den öppna [universal-parcel-scraper Early Bird-adaptern](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/early-bird). Peek visar varje steg i Early Birds engelska formulering (äldre rader står kvar på svenska), med klockslag, platsen som varje terminalskanning anger och leveranstidpunkten. Peek visar ingen planerad leveransdag och namnger inte skåpet: den planerade dagen finns i länken i Early Birds sms eller mejl, skåpet i meddelandet och i iBoxens app.

När en annan transportör tar över länkar Early Birds spårning till den transportörens. Peeks kod känner till tre: [PostNord](carrier:postnord), DSV och DB Schenker, som Peek lägger under [DSV](carrier:dsv). Kortet säger då "Delivery with PostNord" eller "Delivery with DSV", och Peek följer den transportören under samma nummer när den visar nya skanningar. Så länge den transportören bara har aviseringen av paketet, utan egen skanning, säger kortet i stället "Announced to PostNord, not received yet" eller "Announced to DSV, not received yet".

Peek visar bokningen som "Label created", mottagandet som "Received by carrier", terminaler och vägen till ett skåp som "In transit", misslyckade leveranser som "Delivery attempted", skåpet som "Ready for pickup" och leverans eller uthämtning som "Delivered". Ett nytt inbokat försök behåller steget före det.

När den senaste skanningen är fyra dagar gammal och paketet fortfarande står som "Label created", "Received by carrier" eller "In transit", flaggar Peek det med skanningens dag, till exempel "No tracking update since Mon 12 Oct". Paketets sida säger då "Parcels often go quiet on long flights, at customs or over a weekend. Peek keeps asking Early Bird." med länken "Get help from Early Bird" till Early Birds kontaktsida. Efter tre veckor utan ny skanning står det "Nothing new for over three weeks. If it still hasn’t arrived, ask the seller. Peek keeps asking Early Bird."

Medan du har paketet öppet i Peek kollar Peek det upp till var tionde minut. Efter två dagar utan nyheter (12 timmar medan paketet fortfarande står som "Label created" eller "Received by carrier") kollar Peek varje timme, och var sjätte timme efter sju dagar utan nyheter på "Label created". Peek kan meddela dig om "Every scan", "Important steps" eller "Delivery only". Peek kan inte kontakta Early Bird eller butiken åt dig.

## Kontakta Early Bird kundservice

Early Bird anger inget telefonnummer eller någon öppen e-postadress på sin kundservicesida, utan hanterar ärenden via ämnesindelade kontaktformulär:

- **Kontaktformulär (svenska):** [earlybird.se/kontakta-oss](https://earlybird.se/kontakta-oss) (välj ärende som skadat paket, felaktig leverans eller saknat paket och ange kollinummer)
- **Spårning & vanliga frågor:** [earlybird.se/spara-och-hjalp](https://earlybird.se/spara-och-hjalp) och [earlybird.se/vanliga-fragor](https://earlybird.se/vanliga-fragor)
- **Engelska sidor:** [Track & help](https://earlybird.se/en/track-and-help) och [Contact us](https://earlybird.se/en/contact-us)

För retur, återbetalning eller ersättningsvara kontaktar du alltid e-handlaren du köpte varan av.

## Vanliga frågor

### Vilken tid på dygnet levererar Early Bird?

Vanligtvis på natten eller tidigt på morgonen mellan **01:00 och 07:00**, upp till sex dagar i veckan beroende på var du bor. Du behöver varken vara hemma eller vaken.

### Varför står det bara att min leverans är bokad hos Early Bird?

Det betyder att e-handlaren har bokat frakten men ännu inte lämnat in paketet till Early Bird. Spårningen och planerad leveransdag uppdateras först när Early Bird scannar paketet.

### Hur länge ligger ett Early Bird-paket i iBoxen-paketskåp?

Paketet ligger kvar i **5 dagar** och tiden kan normalt inte förlängas innan paketet skickas tillbaka till e-handlaren.

### Har Early Bird telefonnummer till kundservice?

Nej — Early Bird hanterar kundärenden via kontaktformulären på [earlybird.se/kontakta-oss](https://earlybird.se/kontakta-oss) där du uppger ditt kollinummer.

:::sources
- [Early Bird Sverige](https://earlybird.se/)
- [Early Bird — Spåra och hjälp](https://earlybird.se/spara-och-hjalp)
- [Early Bird — Vanliga frågor](https://earlybird.se/vanliga-fragor)
- [Early Bird — Kontakta oss](https://earlybird.se/kontakta-oss)
- [Early Bird — Track & help (EN)](https://earlybird.se/en/track-and-help)
- [Early Bird — Contact us (EN)](https://earlybird.se/en/contact-us)
- [Early Bird — Frequently asked questions (EN)](https://earlybird.se/en/frequently-asked-questions)
- [Early Bird — About Early Bird (EN)](https://earlybird.se/en/about-us)
- [Early Bird — Home Delivery (EN)](https://earlybird.se/en/home-delivery)
- [Early Bird — Parcel locker delivery (EN)](https://earlybird.se/en/parcel-locker-delivery)
- [Early Bird — Early Bird Express (EN)](https://earlybird.se/en/express-delivery)
- [universal-parcel-scraper Early Bird-adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/early-bird)
:::
