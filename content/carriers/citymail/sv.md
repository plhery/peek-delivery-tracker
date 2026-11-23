---
title: Spåra paket med CityMail: BCM-nummer, statusar och ombud
description: Spåra ditt paket från CityMail i Sverige: så ser BCM-numret ut, vad statusarna betyder, leveranstider, DHL-ombud, iBoxen-paketbox och kontaktuppgifter.
slug: spara-paket-citymail
published: 2026-11-23
updated: 2026-11-23
---

CityMail är ett svenskt post- och paketföretag som startade 1991 och har cirka 1 200 anställda, 104 lokalkontor och fyra terminaler. E-handlare skickar paket med CityMail, medan företag och myndigheter skickar brev, direktreklam och tidningar. Du kan följa ditt paket på [CityMails spårningssida](https://www.citymail.se/spara-paket/) (Spåra paket), via spårningslänken i CityMails SMS eller genom att klistra in spårningsnumret i fältet ovan. Numret finns i det SMS eller mejl som CityMail skickade till dig om leveransen.

## CityMails spårningsnummer: så ser det ut

På CityMails spårningssida efterfrågas ett spårningsnummer och exemplet börjar med `BCM`. De format som Peek känner igen för CityMail (med påhittade siffror):

| Nummer (påhittat) | Vad det är |
| --- | --- |
| `BCMKP48291736` | `BCM`, två bokstäver och åtta siffror: en vanlig paketreferens hos CityMail. |
| `BCMR4829173`, `BCMTWX482917365` | Samma struktur med en till tre bokstäver och sju till nio siffror. |
| `BCMKP4829173605142CN` | `BCM`, två bokstäver, 13 siffror och `CN`: en längre referens hos CityMail. |
| Annan avsändarreferens | Peeks kod accepterar även en avsändarreferens på 8 till 35 bokstäver och siffror för CityMail (se avsnittet om Peek nedan). |

Saknar du nummer? Fråga nätbutiken eller företaget som skickade försändelsen. Om mejlet i stället anger [PostNord](carrier:postnord) eller [Bring](carrier:bring) tillhör numret dem och inte CityMail. Läs mer i [hur du hittar ditt spårningsnummer](guide:find-tracking-number) och [format för spårningsnummer](guide:tracking-number-formats).

## Vad CityMails spårningsstatusar betyder

CityMails spårningstjänst visar händelser på svenska och den nyaste visas under ”senaste händelse”. Här är formuleringarna som Peek tolkar, ungefär i den ordning de dyker upp:

| CityMail visar | Vad det innebär för dig |
| --- | --- |
| `Vi har fått beställning på en leverans till dig och väntar på ditt paket` | Avsändaren har bokat en leverans till dig. CityMail väntar fortfarande på att få in paketet. |
| `Vi har fått en beställning på en leverans och väntar på paketet` | Samma betydelse med en något kortare formulering. |
| `Paketet har aviserats, men ännu inte lämnats in till CityMail` | Försändelsen är elektroniskt föranmäld men ännu inte inlämnad till CityMail. |
| `Paketet har ankommit till terminal`, `Paket har ankommit till vår terminal` | Paketet har skannats på en av CityMails terminaler. |
| `Paketet har levererats hem till dig` | Paketet har delats ut till din hemadress. |
| `Paketet är utdelat, vid dörr/finns i påse på brevlåda` | Lämnat utanför din ytterdörr eller i en väderskyddad påse på brevlådan. |
| `Paketet har levererats till din brevlåda/postfack` | Utdelat i din brevlåda eller ditt postfack. |
| `Namn saknades på adressen - leveransen kunde inte genomföras` | Misslyckad utdelning eftersom ditt namn inte fanns uppmärkt på adressen. |
| `Paketet är på väg till ombud` | Paketet transporteras till ett utlämningsställe (ombud). |
| `Paketet har levererats till ombud` | Paketet finns hos ombudet och är klart för upphämtning. |
| `Paketet hämtades inte ut i tid och skickas tillbaka till avsändaren` | Liggetiden har gått ut och paketet returneras till avsändaren. |

De tre första statusarna visar att paketet ännu inte har kommit in i CityMails nätverk. Om utdelningen misslyckades för att namn saknades behöver du se till att ditt namn står tydligt på brevlådan eller dörren.

## Leverans med CityMail i Sverige: tider, ombud och iBoxen

- **När paketet levereras:** Enligt [CityMails hjälpsidor](https://www.citymail.se/hjalp/nar-kommer-mitt-paket/) sker paketutdelning helgfria vardagar mellan kl. 08.00 och 17.00. För e-handlare finns även tjänsten kvällsleverans till dörren (*Hemleverans kväll*). Beräknad leveransdag framgår i spårningslänken i CityMails SMS eller mejl, och om dagen ändras meddelas du på samma sätt. Leveranstiden beror på vilket fraktval butiken bokat: hemleverans tar normalt 1–2 eller 1–4 dagar, medan paket till paketbox eller ombud normalt kommer fram dagen efter att CityMail tagit emot paketet från butiken.
- **Var paketet lämnas:** CityMails egna brevbärare och bud lägger paketet i brevlådan eller postfacket, vid din ytterdörr eller i en påse hängd på brevlådan. Du behöver inte vara hemma och ingen signatur eller legitimation krävs vid vanlig leverans.
- **Om leverans hem inte kan genomföras:** Om paketet inte kan delas ut på din adress kan du välja att hämta det i en iBoxen-paketbox eller hos ett DHL-ombud. Gör du inget val skickar CityMail ett nytt datum för hemleverans. När ombud eller paketbox är möjligt för din försändelse kan du även styra om leveransen via länken i SMS:et. CityMail eftersänder inte paket till en ny adress — läs mer om [att ändra leveransadress](guide:change-delivery-address).
- **Upphämtning hos DHL-ombud:** CityMail samarbetar med [DHL:s ombudsnät](https://www.citymail.se/paket/ombud/). Du får ett SMS med sändningsnummer, namnet på ombudet samt en länk för identifiering med BankID. Ta med giltig legitimation. Ett bud kan hämta åt dig mot uppvisande av både din och sin egen legitimation. Hämtas inte paketet i tid går det tillbaka till avsändaren (CityMails öppna hjälpsidor anger inget fast antal liggedagar). Se [ej uthämtade paket](guide:uncollected-parcel).
- **Upphämtning i iBoxen-paketbox:** Du får ett SMS och ett meddelande i iBoxen-appen där ”Hitta boxen” visar skåpet på karta. Vid skåpet trycker du på ”Hämta ut paket”, bekräftar med BankID och trycker sedan på ”Öppna lucka”. Vill du att någon annan hämtar kan du ange deras telefonnummer i iBoxen-appen; personen behöver då appen och Mobilt BankID.
- **Brevutdelning:** CityMail delar ut post helgfria vardagar enligt ett rullande fyradagarsschema där utdelningsområdet delas in i fyra delar. För att se din utdelningsdag kan du söka på ditt postnummer under ”När kommer posten?” på `citymail.se`. Exakt klockslag beror på var längs turen din adress ligger.

## Försenat, saknat, skadat eller returnerat CityMail-paket

- **Försenat eller nytt leveransdatum:** Om CityMail inte kan leverera på beräknad dag uppdateras det nya datumet i din spårningslänk. För vissa försändelser kan du där även byta till paketbox eller ombud. Se [varför spårningen inte uppdateras](guide:tracking-not-updating).
- **Markerat som levererat, men paketet saknas:** Kontrollera först brevlåda, postfack, området utanför ytterdörren och eventuell påse på brevlådan. Hittar du ändå inte paketet, kontakta CityMails Mottagarservice (nedan). Läs även [levererat men inte mottaget](guide:delivered-not-received).
- **Skadat paket:** Kontrollera om själva innehållet är skadat eller bara ytteremballaget. Är innehållet skadat ska du kontakta nätbutiken, som i sin tur begär en utredning hos CityMail. Se [skadat paket](guide:damaged-parcel).
- **Fel vara eller avbokad beställning:** Kontakta butiken direkt — CityMail kan inte stoppa en pågående leverans.
- **Saknat brev:** Enligt [CityMails guide för saknade brev](https://www.citymail.se/hjalp/var-ar-mitt-brev/) går det oftast snabbast att först fråga avsändaren om brevet verkligen har skickats. Får du inget svar kan du vända dig till Mottagarservice. Se [försvunnet paket](guide:lost-parcel).
- **Returnerat till avsändaren:** Ett paket som inte hämtas ut hos ombudet i tid returneras. Kontakta nätbutiken för återbetalning eller nytt utskick. Se [returnerat till avsändaren](guide:returned-to-sender).

## Kontakt och kundservice hos CityMail

Peek kan inte kontakta CityMail eller nätbutiken åt dig. Enligt [CityMails hjälp- och kontaktsida](https://www.citymail.se/hjalp/):

- **Mottagarservice (för privatpersoner som tar emot post och paket):** telefon `0771-40 60 40`, e-post `mottagarservice@citymail.se`. Öppet måndag–fredag kl. 08.30–17.00 enligt CityMails Frågor och svar.
- **Företagsfrågor och växel:** telefon `08-709 43 00`.
- **Online:** [CityMails hjälpsidor](https://www.citymail.se/hjalp/) och [Frågor och svar](https://www.citymail.se/hjalp/fragor-och-svar/) på svenska. CityMail har även en webbapp där mottagare kan följa leveransen, styra om den, boka retur och få aviseringar.

## Spåra ett CityMail-paket med Peek

- **Ett `BCM`-nummer:** Klistra in numret i [Peek](/) (till exempel `BCMKP48291736`, påhittat) så känner Peek igen CityMail direkt och visar "Detected carrier". Mellanslag, bindestreck och gemener spelar ingen roll.
- **En spårningslänk från CityMail:** En länk till `tracking.citymail.se` med `?search=` och din referens kopplas automatiskt till CityMail oavsett referensformat.
- **Annan avsändarreferens:** Peek kan inte avgöra utifrån ett godtyckligt format att det är CityMail och frågar inte CityMail om det på egen hand. Klistra ändå in numret (Peek följer över 4 500 transportörer), tryck sedan på "Change" vid tillägg eller "Change carrier" på ett redan tillagt paket och välj CityMail. För CityMail godtar Peek 8 till 35 bokstäver och siffror med minst en siffra.

Peek hämtar status och varje skanning direkt från CityMails spårning med tidpunkt (tolkad i svensk tid) och plats, på CityMails svenska formuleringar, tillsammans med avsändarens namn och beräknad leveransdag medan paketet är på väg. Beställning och föranmälan visas som "Label created", terminal och transport till ombud som "In transit" (ingen av CityMails formuleringar betyder "Out for delivery"), levererat till ombud som "Ready for pickup" (med ombudets namn), utdelat som "Delivered", misslyckad leverans som "Delivery attempted" och retur som "Returned to sender". Postnummer och leveransbevis visas inte i Peek — använd i stället "Open the CityMail website", som öppnar ditt nummer i CityMails spårning.

Medan paketet är öppet i Peek, eller på leveransdagen med aviseringar på, kontrollerar Peek det upp till var 10:e minut. Efter två dagar utan nyheter (12 timmar medan paketet fortfarande visar "Label created" eller "Received by carrier") kontrollerar Peek varje timme, och var 6:e timme efter sju dagar utan nyheter på "Label created". Går paketet fyra dagar utan ny rad på "Label created", "Received by carrier" eller "In transit" markerar Peek det med den radens dag, till exempel "No tracking update since Mon 12 Oct", och visar "Parcels often go quiet on long flights, at customs or over a weekend. Peek keeps asking CityMail." med länken "Get help from CityMail" till [CityMails hjälpsidor](https://www.citymail.se/hjalp/). Efter mer än tre veckor utan nya uppgifter visas "Nothing new for over three weeks. If it still hasn’t arrived, ask the seller. Peek keeps asking CityMail."

Du kan välja aviseringar för "Every scan", "Important steps" eller "Delivery only". Peek bevakar endast spårningsstatus och kan inte kontakta CityMail eller ändra leveransplats åt dig.

## Vanliga frågor om CityMail-spårning

### Varför fungerar inte mitt CityMail-spårningsnummer?

Kontrollera att du kopierat hela referensen från CityMails SMS eller mejl. Om CityMails spårning inte hittar referensen visas meddelandet `Ingen spårningsinformation hittades för den angivna referensen`. Kvarstår felet bör du fråga avsändaren som bokade leveransen.

### Vilka tider levererar CityMail paket?

Paket delas ut helgfria vardagar mellan kl. 08.00 och 17.00, och butiker kan även erbjuda kvällsleverans till dörren. Beräknad leveransdag står i spårningslänken i CityMails SMS eller mejl.

### Måste jag vara hemma när CityMail levererar?

Nej. CityMail lämnar paketet i brevlådan eller postfacket, vid din dörr eller i en påse på brevlådan utan signatur. Om paketet inte kan levereras på din adress kan du välja en iBoxen-paketbox eller ett DHL-ombud.

### Kan man spåra ett vanligt CityMail-brev?

CityMails spårningssida heter Spåra paket och är avsedd för spårbara paket. CityMails hjälp för ett saknat brev nämner ingen spårning: fråga först avsändaren och kontakta sedan Mottagarservice.

### Är CityMail ett seriöst företag?

Ja. CityMail har delat ut post i Sverige sedan 1991 och har tillstånd från Post- och telestyrelsen (PTS) för postverksamhet. Om ett SMS i CityMails namn ser misstänkt ut, läs mer om [bluff-SMS om paketleveranser](guide:delivery-scam-texts).

:::sources
- [CityMail: Spåra paket](https://www.citymail.se/spara-paket/) – spårningssida, fält för spårningsnummer och BCM-exempel
- [CityMail: Var är mitt paket?](https://www.citymail.se/hjalp/var-ar-mitt-paket/) – SMS med spårningslänk, senaste händelse och var spårningsnumret finns
- [CityMail: När kommer mitt paket?](https://www.citymail.se/hjalp/nar-kommer-mitt-paket/) – utdelningsdagar, tider (08.00–17.00) och leveranstid 1–2 eller 1–4 dagar
- [CityMail: Frågor och svar](https://www.citymail.se/hjalp/fragor-och-svar/) – utdelningsplatser utan signatur, ombud och paketboxar, legitimation, skador, förseningar, brevens rullande schema, PTS-tillstånd och Mottagarservice öppettider
- [CityMail: Hjälp & kontakt](https://www.citymail.se/hjalp/) – Mottagarservice (telefon, e-post, öppettider) och företagsväxel
- [CityMail: Ombud](https://www.citymail.se/paket/ombud/) – DHL-ombud, SMS med BankID-länk, legitimation vid uthämtning och kvällsleverans
- [CityMail: Var är mitt brev?](https://www.citymail.se/hjalp/var-ar-mitt-brev/) – hantering av saknade brev
- [CityMail: Om CityMail](https://www.citymail.se/om-citymail/) – grundat 1991, medarbetare, kontor, terminaler, egna bud och webbapp
- [CityMail: Integritetspolicy](https://www.citymail.se/integritetspolicy/) – DHL-ombud, iBoxen-paketboxar och att paket inte eftersänds
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – vad Peek läser från CityMail, svenska statusformuleringar och nummerformat
:::
