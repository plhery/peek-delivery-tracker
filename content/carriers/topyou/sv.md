---
title: Spåra paket med TopYou: TYZR-nummer, statusar och leverans
description: Spåra ditt TopYou-paket (通邮) från Kina: så ser TYZR-numret ut, det här betyder de engelska skanningarna och så går det till när paketet når Sverige.
slug: topyou-spara-paket
published: 2026-11-03
updated: 2026-11-03
---

TopYou (通邮, Tongyou, även 通邮集团 eller SZTY56) är 深圳市通邮物流科技（集团）股份有限公司, en speditör i Shenzhen i Kina som kör internationella fraktlinjer och skickar beställningar från kinesiska webbutiker till kunder utomlands. Skickar butiken din beställning med TopYou får du ett spårningsnummer som börjar med TYZR och slutar på YQ: klistra in det i fältet ovanför. TopYous spårning följer paketet från lagret i Kina, genom flyget och tullen, och kan sedan fortsätta med skanningar från bolaget som delar ut det.

## TopYous spårningsnummer

TopYous fraktsedelsnummer består av TYZR, en bokstav, tio siffror och YQ, sammanlagt 17 tecken. Med påhittade siffror:

| Nummer (påhittat) | Vad det är |
| --- | --- |
| `TYZRA0000000017YQ` | TYZR, en bokstav, tio siffror och YQ: ett nummer från TopYou. |
| `TYZRA000000017YQ` | Bara nio siffror: så ser TopYous nummer inte ut. Kontrollera om en siffra saknas. |
| `JXCAB0000000017YQ` | Slutar också på YQ, liksom andra kinesiska speditörers nummer, men börjar med JXC: ett nummer från [Juxi Express](carrier:juxi-express). |

Mellanslag och små bokstäver spelar ingen roll när du klistrar in numret i Peek. TopYous egen spårningssida skiljer däremot på stora och små bokstäver, så där skriver du numret med versaler. Hittar du inte numret i orderuppgifterna, fråga säljaren.

**Ett andra nummer.** I TopYous uppgifter om paketet kan det också finnas numret hos leveransbolaget, alltså bolaget som delar ut paketet. Det numret syns på TopYous spårningssida.

**Kopiera numret, inte en länk.** Peek läser inga länkar från TopYous spårningssida, så klistra in själva numret.

## Statusar i TopYous spårning

TopYou skriver sina skanningar på engelska. I den ordning paketet får dem:

| TopYou skriver | Vad det betyder för dig | Peek visar |
| --- | --- | --- |
| `Arrived at the first mile sorting center` | TopYous lager i Kina har tagit emot paketet: den första skanningen hos TopYou. | "Received by carrier" (mottaget av transportören) |
| `Departed from the first mile sorting center` | Paketet har lämnat lagret. | "In transit" (på väg) |
| `Arrived at airport of the original country` | På flygplatsen i Kina, före flyget. | "In transit" |
| `Departed from Airport to destination country` | Flyget mot Sverige har lyft. | "In transit" |
| `Arrived at destination airport` | Paketet har landat i Sverige. | "In transit" |
| `Departed from customs` | Tullen i Sverige har släppt paketet. På TopYous egen sida står det `Departed from customs of`, utan något land efter, och det sista ordet tar Peek bort. | "In transit" |

**Nyare fraktsedlar.** På nyare fraktsedlar beskriver TopYou några av stegen med andra ord:

| TopYou skriver | Vad det betyder för dig | Peek visar |
| --- | --- | --- |
| `Carrier received the shipment` | TopYou har paketet i Kina: den första skanningen. | "Received by carrier" |
| `Shipment arrived at a carrier facility` | Paketet lämnar TopYous första lager och går vidare. Det har inte tagits emot på nytt. | "In transit" |
| `Shipment started the customs clearance process at origin` | Exporttullen i Kina, före flyget. Det är inte tullen i Sverige. | "At customs" (i tullen) |
| `Shipment arrived in the destination country` | Paketet har kommit fram till Sverige. | "In transit" |
| `Shipment started the customs clearance process at destination` | Paketet tullbehandlas i Sverige. | "At customs" |
| `Shipment completed the customs clearance process at destination` | Paketet är tullklarerat och på väg till leveransbolaget. | "In transit" |
| `Shipment is out for delivery` | Trots ordalydelsen är paketet inte ute för leverans än: i Frankrike och Italien markerar raden överlämningen till GOFO på dess första sorteringscentral, flera dagar innan GOFOs bud kör ut paketet. | "In transit" |

**Linjer där posten delar ut.** På linjer där posten i mottagarlandet delar ut paketen formulerar TopYou skanningarna på ett tredje sätt, fram till tullen:

| TopYou skriver | Vad det betyder för dig | Peek visar |
| --- | --- | --- |
| `Accepted by carrier` | TopYou har paketet i Kina: den första skanningen. | "Received by carrier" |
| `Inbound in sorting center` | Paketet registreras på TopYous första lager några minuter senare. Det har inte börjat röra sig än. | "Received by carrier" |
| `Outbound in sorting center` | Paketet har lämnat lagret. TopYou lägger till `, Lastmile Number:` och numret som posten delar ut paketet under, och Peek visar raden med det numret. | "In transit" |
| `Received by line-haul`, `Hand over to airline` | Paketet har tagits emot för transport och sedan lämnats till flygbolaget. | "In transit" |
| `Airline arrived at transit country or district.`, `Airline departed from transit or district country.` | Ett stopp på vägen till Sverige. | "In transit" |
| `Airline arrived at destination country`, `Arrive at destination country` | Paketet har kommit fram till Sverige. | "In transit" |
| `Export clearance success` | Exporttullen i Kina har klarerat paketet. Det har inte fastnat. | "In transit" |
| `Import clearance success` | Tullen i Sverige har klarerat paketet för posten. Posten kan ändå visa egna tullskanningar efteråt. | "In transit" |

**Skanningar från leveransbolaget.** Efter överlämningen kan TopYous spårningssida fortsätta med leveransbolagets skanningar, med dess egna ord. Så gör den till exempel med USPS i USA, GOFO i Frankrike och Italien och iMile i Tyskland. GOFOs `The driver is out for delivery` och iMiles `Delivery, Our delivery associate is out for delivery.` visar Peek som "Out for delivery" (ute för leverans), medan till exempel GOFOs `Arrived at sorting center` blir "In transit". En rad från iMile som inte flyttar paketet, som en inventering, lämnar statusen som den var.

## Vem som delar ut TopYou-paket i Sverige

Efter flyget och tullen lämnar TopYou över paketet till ett bolag som delar ut det. I USA tar [USPS](carrier:usps) över, i Frankrike och Italien kan [GOFO France](carrier:gofo-fr) eller [GOFO Italy](carrier:gofo-it) dela ut paketet och i Tyskland [iMile](carrier:imile). Vilket bolag som delar ut i Sverige framgår inte av TopYous uppgifter: de kan innehålla bolagets nummer men inte dess namn. Namnet på TopYou-linjen som paketet reser med säger inte heller något, eftersom en linje kan bära ett annat bolags namn. På en linje där posten delar ut står postens nummer också sist på raden `Outbound in sorting center`, efter `Lastmile Number:`.

**PostNord.** Är det andra numret ett giltigt nummer i postens internationella format (två bokstäver, nio siffror med rätt kontrollsiffra och två bokstäver) och anger TopYou Sverige som destinationsland, frågar Peek [PostNord](carrier:postnord) om numret. Det är en kontroll, inget bevis på att PostNord delar ut paketet. Fråga därför säljaren vilket bolag som levererar din beställning och under vilket nummer.

## TopYou-paketet kommer inte eller spårningen står still

**Står still på `Shipment started the customs clearance process at destination`.** Paketet är i tullen i Sverige. Blir du ombedd att betala importavgifter, läs om [paket som fastnat i tullen](guide:customs) (på engelska). Ligger paketet kvar där i flera dagar, hör av dig till säljaren.

**Inget händer efter `Departed from customs` eller `Import clearance success`.** Nu bör leveransbolaget ha paketet. Leta efter dess nummer på TopYous spårningssida, eller fråga säljaren vilket bolag som levererar och under vilket nummer. Mer bakgrund finns i guiden om [att spåra paket från Kina](guide:tracking-from-china) (på engelska).

**Hittas inte, eller inga skanningar än.** Känner TopYous spårningssida inte till numret svarar den 查询不到 (hittades inte). Känner den till numret men har inte skannat paketet än kan den svara 暂无轨迹信息 (ingen spårningsinformation än). Peek visar "Waiting for the carrier" (väntar på transportören) i båda fallen och frågar igen vid nästa kontroll. Jämför numret med säljarens meddelande, tecken för tecken. Händer inget på några dagar, fråga säljaren om paketet verkligen har skickats.

**Levererat, men inget paket.** Hör först med bolaget som delade ut paketet och kontakta sedan säljaren.

**Borttappat eller returnerat.** Det var säljaren som bokade frakten hos TopYou, så det är säljaren, eller marknadsplatsen, som du ber om pengarna tillbaka eller ett nytt paket.

## TopYous kundtjänst och kontaktuppgifter

Peek har inte kunnat kontrollera TopYous webbplats, så här finns inga kontaktuppgifter till TopYou. Frågor om din beställning ställer du till säljaren, som bokade frakten hos TopYou, och till marknadsplatsen om säljaren inte svarar.

## Spåra TopYou-paket med Peek

[Peek](/) känner igen ett TopYou-nummer på formen: TYZR, en bokstav, tio siffror och YQ. Ingen annan transportör i Peeks katalog använder den formen, och Peek slår bara upp sådana nummer hos TopYou. Har du bara leveransbolagets nummer klistrar du in det i stället: Peek följer fler än 3 500 transportörer.

Peek läser TopYous egen spårning och visar statusen, varje skanning med TopYous ord (eller leveransbolagets, när TopYou för vidare dess skanningar), destinationslandet och vikten som TopYou anger. Från TopYous egna skanningar tar Peek bort en ort som bara är ett land, så att skanningen på flygplatsen i Kina inte ser ut att vara gjord i Sverige. När TopYous uppgifter säger att paketet är levererat eller returnerat visar Peek "Delivered" (levererat) eller "Returned to sender" (returnerat till avsändaren), även om den sista skanningen säger något annat. TopYous egna skanningar har Pekingtid (UTC+8). För leveransbolagets skanningar anger TopYous spårning inte alltid rätt tidszon, så där räknar Peek ut tiden själv. Alla tider visas i din enhets tidszon. Något beräknat leveransdatum visar Peek inte, eftersom TopYou inte anger något.

Fram till en överlämning visar Peek bara TopYou-numret. När TopYous uppgifter innehåller leveransbolagets nummer frågar Peek posten i destinationslandet, i Sverige PostNord, om numret, förutsatt att det är ett giltigt nummer i postens internationella format. Annars frågar Peek det enda bolag som numrets form säkert hör till, förutsatt att Peek läser det bolagets egen spårning. När det bolaget visar en skanning som är minst lika ny som TopYous senaste följer Peek paketet där och visar båda numren, och TopYous skanningar finns kvar i historiken. Ett nummer som Peek inte kan knyta till ett enda bolag, till exempel bara 20 siffror, som både USPS och andra använder, provas inte. Då stannar Peek hos TopYous spårning, som också kan visa leveransbolagets skanningar.

Peek kollar paketet var tionde minut och varannan minut när det är ute för leverans. Efter 12 timmar utan ny skanning medan paketet står på "Label created" (etikett skapad) eller "Received by carrier", och annars efter 48 timmar utan ny skanning, kollar Peek en gång i timmen. Peek kan meddela dig, och du väljer mellan "Every scan" (varje skanning), "Important steps" (viktiga steg) och "Delivery only" (bara leveransen). "Important steps" omfattar leverans, paket som väntar på att hämtas, tull, missade leveransförsök och returer. Du får alltså besked vid tullrader som `Shipment started the customs clearance process at origin` och `Shipment started the customs clearance process at destination`, när Peek visar "Out for delivery" och vid leveransen, men inte vid flyget, när tullen släpper paketet eller vid `Shipment is out for delivery`. Peek kan inte kontakta TopYou eller säljaren åt dig.

## Frågor om att spåra paket med TopYou

### Vad är TopYou (通邮)?

Det är 深圳市通邮物流科技（集团）股份有限公司, även kallat 通邮集团, Tongyou eller SZTY56, en speditör i Shenzhen i Kina med internationella fraktlinjer. TopYous nummer består av TYZR, en bokstav, tio siffror och YQ.

### Hur spårar jag ett paket från Kina med ett TYZR-nummer?

Klistra in numret i fältet ovanför. Peek känner igen TYZR, en bokstav, tio siffror och YQ som ett TopYou-nummer och läser TopYous egen spårning. Mellanslag och små bokstäver spelar ingen roll.

### Varför visar TopYou Sverige fast paketet inte har lämnat Kina?

För att TopYous spårningssida skriver destinationslandet vid sina egna skanningar, även på avgångsflygplatsen i Kina. Gå efter texten: `Arrived at airport of the original country` betyder att paketet fortfarande väntar på sitt flyg i Kina.

### Vem delar ut TopYou-paket i Sverige?

Det framgår inte av TopYous uppgifter: de kan innehålla numret hos bolaget som delar ut paketet, men inte bolagets namn. Är numret ett giltigt nummer i postens internationella format frågar Peek PostNord om det, men det bevisar inte att PostNord delar ut paketet. Fråga säljaren vilket bolag som levererar.

### Vem kontaktar jag om mitt TopYou-paket inte kommer?

Säljaren du beställde från, eftersom det var säljaren som bokade frakten hos TopYou. Peek har inte kunnat kontrollera TopYous webbplats för kontaktuppgifter.

:::sources
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – företagets namn och andra namn, TYZR-formen, versaler på TopYous sida, ingen länk som Peek läser, skanningarnas ord och vad Peek visar för dem, destinationslandet vid TopYous egna skanningar, Pekingtid och tiderna på leveransbolagets skanningar, USPS i USA, GOFO i Frankrike och Italien och iMile i Tyskland, leveransbolagets skanningar, det andra numret, Lastmile-numret på en postlinje och linjens namn, okända nummer och nummer utan skanningar, PostNord som Peek frågar i Sverige och hur Peek lämnar över paketet
:::
