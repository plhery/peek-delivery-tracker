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

Eftersom både [PostNord](carrier:postnord) och [Bring](carrier:bring) också använder 20-siffriga koder som börjar med `00` bekräftar Peek vilken transportör som har paketet. Saknar du nummer? Se [how to find a tracking number](guide:find-tracking-number) (på engelsk).

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
| `Eftersom vi inte kunde leverera paketet hem till dig så kommer vi boka om det till ett paketskåp. Vi meddelar dig snarast så snart vi vet vilket skåp det blir.` | `Since we were unable to deliver the parcel to your home, we will reroute it to a parcel locker...` | Paketet styrs om till ett paketskåp i närheten. |
| `Paketet är på väg. Du får ett meddelande när det är redo att hämtas ur paketskåpet.` | `Your package is on its way. You will receive a notification when it is ready to be collected from the parcel locker.` | På väg ut till valt paketskåp. |
| `Ditt paket finns nu redo att hämtas från paketskåpet. Du behöver iBoxens app för att öppna luckan.` | `Your package is now ready for collection from the parcel locker. The iBoxen app is required to open the box.` | Klart att hämta i iBoxen-skåpet. |
| `Paketet har hämtats ur paketskåpet.` | `The package has been collected from the parcel locker.` | Uthämtat ur paketskåpet. |
| `Paketet är levererat till din dörr.` | `The package has been delivered to your door.` | Lämnat utanför din ytterdörr. |
| `Paketet är levererat till din postlåda.` | `The package has been delivered to your postlåda / mailbox.` | Lagt i din postlåda. |
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

- **Står bara som „bokad“:** Early Bird har inte fått paketet från e-handlaren ännu; kontakta butiken ([tracking that isn't updating](guide:tracking-not-updating)).
- **Markerat som levererat men borta:** Kontrollera adressen i orderbekräftelsen, titta i och bredvid postlådan samt utanför dörren, fråga hushållet och grannar, och skicka sedan formuläret *Saknat paket trots leveransbekräftelse* på [Kontakta oss](https://earlybird.se/kontakta-oss) ([delivered not received](guide:delivered-not-received)).
- **Skadat paket:** Fyll i formuläret för skadat paket med tydligt foto och kontakta även e-handlaren, som ansvarar för varan och eventuell ersättning ([damaged parcel](guide:damaged-parcel)).

> Early Bird tar aldrig ut någon extra avgift via sms för att släppa ett paket — frakten betalas normalt i kassan. Klicka inte på oväntade betallänkar; kolla alltid statusen direkt på [earlybird.se/spara-och-hjalp](https://earlybird.se/spara-och-hjalp) ([delivery scam texts](guide:delivery-scam-texts)).

## Spåra Early Bird med Peek

Klistra in ditt 20-siffriga kollinummer (`00...73402216...` med giltig kontrollsiffra) eller en spårningslänk från `earlybird.se` (`spara-och-hjalp`, `en/track-and-help`, `tracking?id=...`) eller `track.earlybird.se` (`a=...`) i Peek. Eftersom [PostNord](carrier:postnord) använder liknande 20-siffriga koder kontrollerar Peek Early Bird först tillsammans med övriga matchande transportörer. För andra referenser (8–30 tecken) eller om du vill välja manuellt, tryck **Change** när du lägger till paketet eller **Change carrier** på paketsidan (Peeks gränssnitt är på engelsk).

Peek läser Early Birds spårning direkt genom den öppna [universal-parcel-scraper Early Bird-adaptern](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/early-bird) och visar varje skanning (engelska texter, eller svenska för äldre system), klockslag, terminalort och leveranstidpunkt. Planerad leveransdag och specifikt iBoxen-skåp visas inte i Peek utan i Early Birds sms/mejl och iBoxens app. Lämnar Early Bird över paketet till [PostNord](carrier:postnord) eller [DSV](carrier:dsv) (inklusive DB Schenker), visar Peek den mottagande transportören och följer automatiskt vidare där så fort nya skanningar kommer in.

Peek samlar stegen i tydliga faser: **Label created** (bokad leverans), **Received by carrier** (mottaget av Early Bird), **In transit** (terminaler och transport till paketskåp), **Delivery attempted** (misslyckat leveransförsök), **Ready for pickup** (klart i iBoxen-skåpet) och **Delivered** (levererat till dörr/postlåda eller uthämtat ur skåp).

Blir det tyst över natten eller under en helg visar Peek: „Parcels often go quiet on long flights, at customs or over a weekend. Peek keeps asking Early Bird.“ Efter mer än tre veckor utan uppdatering visas: „Nothing new for over three weeks. If it still hasn’t arrived, ask the seller. Peek keeps asking Early Bird.“ Du når Early Birds officiella hjälpsida direkt via **Get help from Early Bird**.

I Peeks aviseringsinställningar väljer du **Every scan**, **Important steps** eller **Delivery only**.

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
- [universal-parcel-scraper Early Bird-adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/early-bird)
:::
