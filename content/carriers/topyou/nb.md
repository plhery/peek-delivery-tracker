---
title: TopYou sporing: TYZR-numre, statuser og hvem som leverer
description: Spor en TopYou-pakke (通邮 / SZTY56) fra Kina: TYZR…YQ-numre, hva skanningene betyr, lokale bud (USPS, GOFO, iMile, DPD), EU-gebyr og kontakt i Shenzhen.
slug: topyou-sporing
published: 2026-11-28
updated: 2026-11-28
---

TopYou (通邮, Tongyou, 通邮集团 eller SZTY56 — 深圳市通邮物流科技（集团）股份有限公司, som oppgir at det ble grunnlagt i Shenzhen i 2016) er en grensekryssende speditør med internasjonale linjer fra Kina. Lim inn ditt 17-tegns `TYZR…YQ`-fraktbrevnummer i feltet over (eller med store bokstaver på track.szty56.com) for å følge det fra Shenzhen gjennom eksport, flyvning, toll og overlevering til lokal transportør, som USPS i USA, GOFO i Frankrike og Italia, iMile i Tyskland eller DPD i Østerrike.

## TopYou sporingsnummer: hvordan det ser ut

TopYous fraktbrevnumre består av `TYZR`, én bokstav, ti sifre og `YQ`, til sammen 17 tegn; enkelte har en annen bokstav i stedet for `R`. Med oppdiktede sifre:

| Nummer | Hva det er |
| --- | --- |
| `TYZRA4829105736YQ` | `TYZR`, en bokstav, ti sifre og `YQ`: et TopYou-fraktbrev. |
| `TYZRA482910573YQ` | Bare ni sifre: ikke TopYous format. Sjekk om det mangler et siffer. |
| `JXCAB4829105736YQ` | Slutter også på `YQ`, slik andre kinesiske speditørers numre gjør, men starter med `JXC`: et nummer fra [Juxi Express](carrier:juxi-express). |

Mellomrom og små bokstaver spiller ingen rolle når du limer nummeret inn i Peek. TopYous egen sporingsside, [track.szty56.com](https://track.szty56.com/), skiller mellom store og små bokstaver, så skriv nummeret med store bokstaver der. Finner du det ikke i ordredetaljene, se [how to find your tracking number](guide:find-tracking-number) (på engelsk).

**Et annet nummer.** TopYous registrering kan også inneholde den leverende transportørens eget nummer, som TopYous sporingsside viser.

**Kopier nummeret, ikke en lenke.** Peek leser ingen lenke fra TopYous sporingsside: lim inn selve nummeret.

## TopYou sporingsstatuser forklart

TopYou skriver skanningene sine på engelsk. I den rekkefølgen en pakke vanligvis får dem:

| TopYou sier | Hva det betyr for deg | Peek viser |
| --- | --- | --- |
| `Parcel Data Received` | Butikken har sendt pakkens detaljer til TopYou, men TopYou har ikke pakken ennå. | "Label created" |
| `Arrived at the first mile sorting center` | TopYous lager i Kina har pakken: første skanning hos TopYou. | "Received by carrier" |
| `Departed from the first mile sorting center` | Den har forlatt det lageret. | "In transit" |
| `Arrived at airport of the original country` | På avgangsflyplassen før flyvningen. | "In transit" |
| `Departed from Airport to destination country` | Flyet mot landet ditt har lettet. | "In transit" |
| `Arrived at destination airport` | Landet i mottakerlandet. | "In transit" |
| `Departed from customs of` | Mottakerlandets toll har frigitt den. TopYou lar landet etter "of" stå tomt, og Peek fjerner "of". | "In transit" |
| `At U.S. Postal Service facility, Accepted by U.S. Postal Service` | I USA: USPS har overtatt pakken fra TopYou etter flyvningen. Det er ikke en ny sending. | "In transit" |

**Nyere fraktbrev.** TopYou ordlegger noen av de samme trinnene annerledes på nyere fraktbrev:

| TopYou sier | Hva det betyr for deg | Peek viser |
| --- | --- | --- |
| `Carrier received the shipment` | TopYou har pakken i Kina: dens første skanning. | "Received by carrier" |
| `Shipment arrived at a carrier facility` | På vei videre fra TopYous første lager. Pakken tas ikke imot på nytt. | "In transit" |
| `Shipment started the customs clearance process at origin` | Kinas eksportfortolling, et rutinetrinn før flyvningen. Tollen holder den ikke tilbake, og det er ikke ditt lands importtoll. | "In transit" |
| `Shipment arrived in the destination country` | Den har nådd mottakerlandet. | "In transit" |
| `Shipment started the customs clearance process at destination` | Mottakerlandets importtoll har den. | "At customs" |
| `Shipment completed the customs clearance process at destination` | Fortollet og på vei til den leverende transportøren. | "In transit" |
| `Shipment is out for delivery` | Ikke ute til levering ennå, uansett ordlyd: i Frankrike og Italia markerer den overleveringen til GOFO på dets første sorteringssenter, dager før sjåføren kjører ut. | "In transit" |

**Linjer som leveres av posten.** På linjer der landets postvesen leverer, ordlegger TopYou skanningene på en annen måte fram til importtollen:

| TopYou sier | Hva det betyr for deg | Peek viser |
| --- | --- | --- |
| `Accepted by carrier` | TopYou har pakken i Kina: første skanning. | "Received by carrier" |
| `Inbound in sorting center` | Registrert på TopYous første lager minutter senere. Den er ikke på vei ennå. | "Received by carrier" |
| `Outbound in sorting center` | Den har forlatt det lageret. TopYou legger til `, Lastmile Number:` og nummeret posten leverer under; Peek fjerner den slutten fra linjen. | "In transit" |
| `Received by line-haul`, `Hand over to airline` | Tatt inn til hovedtransport og deretter overlevert til flyselskapet. | "In transit" |
| `Airline arrived at transit country or district.`, `Airline departed from transit or district country.` | Et stopp underveis til landet ditt. | "In transit" |
| `Airline arrived at destination country`, `Arrive at destination country` | Den har nådd mottakerlandet. | "In transit" |
| `Export clearance success` | Kinas eksporttoll har klarert den. Den holdes ikke tilbake. | "In transit" |
| `Import clearance success` | Landets toll har klarert den for posten. Posten kan fortsatt vise egne tollskanninger. | "In transit" |
| `Customs Clearance Completed Successfully` | Landets toll har klarert den, minutter etter overleveringen til den lokale transportøren, som nå har pakken. | "In transit" |

**Den lokale transportørens skanninger.** Etter overleveringen kan TopYous sporing fortsette med den lokale transportørens egne skanninger: USPS på pakker til USA, og GOFO i Frankrike og Italia som `Arrived at sorting center`, `The package left sorting center` og `Package departed from sorting center` (ved eller ut fra et av GOFOs sorteringssentre: "In transit"), `Arrived at GOFO Regional Destination Facility` (på GOFOs regionale depot: "In transit") og `The driver is out for delivery` (GOFOs sjåfør leverer den: "Out for delivery").

I Tyskland kommer iMiles skanninger som iMiles navn på trinnet, komma og iMiles setning: `In Transit, Your order has been shipped from 【iMile Köln DC Center】.` (sendt videre fra et iMile-senter: "In transit"), `Delivery, Your order has arrived at 【Dortmund01】` (på iMile-stasjonen den leveres fra: "In transit"), `Delivery, Your order has assigned delivery drvier.` (sjåfør er tildelt, men har ikke kjørt ut; skrivefeilen er iMiles: "In transit") og `Delivery, Our delivery associate is out for delivery.` (iMiles sjåfør leverer den: "Out for delivery"). En linje som begynner med `Delivery` betyr altså ikke at sjåføren har startet ruten. En iMile-linje uten bevegelse, som en varetelling, endrer ikke statusen. En hentesteds-melding som begynner med `Arrived, The parcel has been delivered to 【】. Please collect your parcel promptly at 【】 using your pickup code.` betyr at pakken venter der på at du skal hente den med hentekoden din: "Ready for pickup".

I Østerrike kommer DPDs skanninger på DPDs engelsk: `In parcel delivery center` (på DPDs leveringsdepot: "In transit"), `In delivery` (DPDs sjåfør leverer den: "Out for delivery"), `Unfortunately, consignee not present. Notification was left.` (ingen hjemme, og DPD la igjen beskjed: "Delivery attempted"), `Parcel was handed over to Pickup parcel shop` og `Takeover by Pickup parcel shop` (den venter på deg i en DPD Pickup parcelshop: "Ready for pickup") og `Parcel was not picked up` (ikke hentet i tide, så DPD sender den tilbake til avsender: "Needs attention").

I Spania kan den leverende partnerens skanning etter toll lyde `Parcel in transit` (på vei til deg: "In transit").

## Hvem leverer TopYou-pakker

**USA.** USPS overtar pakken fra TopYous linje etter flyvningen, og TopYous sporing kan fortsette med dens skanninger. Det andre nummeret i TopYous registrering er da det [USPS](carrier:usps) sporer den med.

**Frankrike og Italia.** GOFO kan levere: [GOFO France](carrier:gofo-fr) eller [GOFO Italy](carrier:gofo-it). TopYous sporing viser overleveringen som `Shipment is out for delivery`, deretter GOFOs skanninger.

**Tyskland.** [iMile](carrier:imile) kan levere, og TopYous sporing fortsetter deretter med iMiles skanninger.

**Østerrike.** [DPD Austria](carrier:dpd-at) kan levere, og TopYous sporing fortsetter deretter med DPDs skanninger.

**Norge og andre land.** TopYous registrering kan oppgi den leverende transportørens nummer, men ikke navnet, og navnet på TopYou-linjen pakken reiser med sier det heller ikke: en linje kan bære et annet selskaps navn. På en postlinje avslutter postens nummer også linjen `Outbound in sorting center`, etter `Lastmile Number:`. Spør derfor selgeren hvilket selskap som leverer ordren din.

## TopYou-pakke kommer ikke fram eller sporing står stille

**Fast på `Shipment started the customs clearance process at destination`.** Mottakerlandets toll har pakken. Blir du bedt om importavgifter — i EU inkluderer dette siden 1. november 2026 et håndteringsgebyr på 2 € per varetype uansett verdi (Norge er utenfor EU) — se [parcels held at customs](guide:customs) (på engelsk); blir den liggende i dagevis, gi selgeren beskjed. Mer i [why tracking stops updating](guide:tracking-not-updating) (på engelsk).

**Ingenting etter `Departed from customs`, `Shipment completed the customs clearance process at destination`, `Customs Clearance Completed Successfully` eller `Import clearance success`.** Den leverende transportøren bør ha pakken. Se etter nummeret dens på TopYous sporingsside, eller spør selgeren hvilken transportør som leverer og under hvilket nummer. Bakgrunn i [tracking a parcel from China](guide:tracking-from-china) (på engelsk).

**Ikke funnet eller ingen skanninger ennå.** TopYous sporing svarer `查询不到` (ikke funnet) for et nummer den ikke kjenner, og kan svare `暂无轨迹信息` (ingen sporingsinformasjon ennå) før første skanning. Peek viser da "Waiting for TopYou" og fortsetter å sjekke; kjenner TopYou fortsatt ikke nummeret to dager senere og ingenting annet har svart for det, viser Peek "After two days, the carrier still doesn’t know this number. Check it, choose another carrier or delete the parcel." Peek kan se av TopYous svar når TopYou lister sendingen men ikke har skannet den ennå: da kjenner TopYou nummeret, og Peek fortsetter å sjekke og viser "Waiting for TopYou" uten å be deg sjekke nummeret. Hvis TopYou bare har vist `Parcel Data Received` i minst ett døgn, viser Peek "The sender hasn’t handed it to TopYou yet. Tracking starts when they do." Spør butikken når den skal sendes. Kontroller nummeret tegn for tegn mot selgerens melding; hvis ingenting dukker opp etter noen dager, spør selgeren om pakken faktisk ble sendt.

**Levert, men ingenting kom.** For en USA-pakke levert av USPS: spør postkontoret ditt og meld fra til selgeren. Mer i [delivered but not received](guide:delivered-not-received) (på engelsk).

**Tapt eller returnert.** Selgeren bestilte pakken hos TopYou, så det er selgeren eller markedsplassen du ber om refusjon eller ny pakke fra. Se [lost parcels](guide:lost-parcel) og [returned to sender](guide:returned-to-sender) (på engelsk).

## TopYou kontaktnummer og kundeservice

Per 16. november 2026 oppgir TopYous nettsted, [szty56.com](https://www.szty56.com/), et telefonnummer i Shenzhen, +86 755 8181 9898 (0755-81819898 innenfor Kina), og åpningstiden 9:00 til 18:00 på virkedager uten oppgitt tidssone. Det nevner ingen kundeservice for mottakere: kundene er butikkene som sender med dem. Selgeren bestilte pakken hos TopYou og er den du spør om ordren, og markedsplassen hvis selgeren ikke svarer.

## Spor en TopYou-pakke med Peek

[Peek](/) gjenkjenner et TopYou-nummer på formen: `TYZR`, én bokstav, ti sifre og `YQ`, som ingen annen transportør i katalogen bruker. Med en annen bokstav i stedet for `R` er formen bare et svakt treff: Peek viser "Checking tracking services…" og spør TopYous sporing om den kjenner nummeret. Peek spør bare TopYous sporing om numre med disse to formene. Har du bare den leverende transportørens nummer, lim inn det i stedet: Peek følger over 4 500 transportører.

Peek leser TopYous egen sporing og viser status, hver skanning med TopYous eller den videresendte transportørens ordlyd, mottakerlandet og vekten fra TopYous registrering. Et sted som bare er et land utelates fra TopYous egne skanninger, så flyplasskanningen i Kina ikke ser ut til å være i landet ditt. Når TopYous registrering markerer pakken som levert eller returnert, viser Peek "Delivered" eller "Returned to sender", selv om teksten i siste skanning ikke gjør det. TopYous egne skanninger følger Beijing-tid. For den leverende transportørens videresendte skanninger oppgir TopYous sporing ikke alltid riktig tidssone, så Peek regner ut tiden og viser alt i enhetens tidssone. Det finnes ingen forventet leveringsdato, fordi TopYou ikke oppgir noen.

Når TopYous registrering inneholder den leverende transportørens nummer, prøver Peek det hos landets postvesen hvis det er et gyldig internasjonalt postnummer (to bokstaver, ni sifre med riktig kontrollsiffer, to bokstaver), eller hos den ene transportøren formatet sikkert tilhører, så lenge Peek leser den transportørens egen sporing. Så snart den transportøren viser en skanning minst like fersk som TopYous siste, følger Peek pakken der og viser begge numrene, med TopYous skanninger bevart i historikken. Et nummer Peek ikke kan knytte til én enkelt transportør, som 20 sifre alene (brukt av USPS og andre), prøves ikke: Peek blir hos TopYous sporing, som også kan vise den lokale transportørens skanninger.

Peek sjekker opptil hvert 10. minutt mens du har pakken åpen, eller på leveringsdagen med varsler på (ellers hver time), og hver time når TopYou har vært stille: etter 12 timer uten ny skanning mens pakken fortsatt er "Label created" eller "Received by carrier", eller etter to dager på ethvert annet trinn. Når pakken er ute til levering, sjekker Peek opptil hvert 2. minutt (hvert 5. når sjekken åpner en nettleser): den dagen fra kl. 06:00 lokal tid, så lenge TopYous egen sporing svarer. Gjennom sporingstjenester sjekker Peek hver time, hver 3. time etter en stille dag og opptil hvert 15. minutt på den dagen mens du har pakken åpen eller varsler på. Når en annen transportør overtar leveringen, følger Peek tempoet til transportøren som leverer. Den kan varsle deg om hver skanning, kun viktige trinn eller "Delivery only". "Important steps" (levering, henting, toll, mislykkede forsøk og returer) varsler om landets toll (`Shipment started the customs clearance process at destination`), GOFOs `The driver is out for delivery`, iMiles `Delivery, Our delivery associate is out for delivery.` og selve leveringen — ikke eksportklareringen i Kina, flyvningen, tollfrigivelsen eller `Shipment is out for delivery`. Peek kan ikke kontakte TopYou eller selgeren for deg.

## Spørsmål om TopYou sporing

### Hva er TopYou (通邮 / SZTY56)?

Det er 深圳市通邮物流科技（集团）股份有限公司, også kalt 通邮集团, Tongyou eller SZTY56, en grensekryssende speditør i Shenzhen i Kina. Den sier den ble grunnlagt i 2016, har avdelinger og sorteringssentre i Shenzhen, Guangzhou, Shanghai og andre kinesiske byer, og håndterer mer enn 800 000 ordrer per dag.

### Hvordan ser et TopYou sporingsnummer ut, og hvorfor finnes det ikke?

TopYou-fraktbrev er på 17 tegn: `TYZR` (eller `TYZ` og en annen bokstav), én bokstav, ti sifre og `YQ`. TopYous sporing på track.szty56.com skiller mellom store og små bokstaver og svarer `查询不到` når nummeret er ukjent, eller `暂无轨迹信息` før første skanning.

### Hvorfor viser TopYou landet mitt før pakken forlater Kina?

Fordi TopYous sporing skriver mottakerlandet ved siden av sine egne skanninger, selv på avgangsflyplassen. Følg ordlyden: `Arrived at airport of the original country` betyr at pakken fortsatt venter på flyet sitt.

### Hvem leverer TopYou-pakker i USA, Europa og ellers?

I USA tar USPS over etter flyvningen. I Frankrike og Italia kan det være GOFO, i Tyskland iMile og i Østerrike DPD. Andre steder kan TopYous registrering vise et annet sporingsnummer uten å oppgi transportøren: spør selgeren hvilket selskap som leverer.

### Hvordan kontakter jeg TopYou om en forsinket eller savnet pakke?

Spør selgeren eller markedsplassen først, siden butikken bestilte forsendelsen hos TopYou. TopYous nettsted szty56.com oppgir et telefonnummer i Shenzhen (+86 755 8181 9898, virkedager 9:00–18:00), men ingen mottakerservice.

:::sources
- [TopYou: 通邮集团 (hjemmeside)](https://www.szty56.com/) – telefon, åpningstider
- [TopYou: 关于我们 (om oss)](https://www.szty56.com/view?path=about/about) – grunnlagt 2016, avdelinger, ordrer per dag, tjenester
- [TopYou: 轨迹查询 (sporing)](https://track.szty56.com/) – TopYous sporing
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 2 € EU-håndteringsgebyr fra 1. november 2026
- [European Commission: Guidance on the 3 EUR customs duty and Union handling fee](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 2 € per varetype solgt ved fjernsalg, uansett verdi
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – selskapsnavn og andre navn, TYZR-format, store bokstaver i TopYous sporing, ingen lenke Peek leser, skanningsordlyder og stadier, mottakerland ved TopYous egne skanninger, Beijing-tid og videresendte klokker, overtakelse ved USPS, GOFO, iMile og DPD, lokale skanninger og hentesteds-varsel, det andre nummeret, Lastmile-nummer på postlinje og linjenavn, ukjente numre og manglende skanninger, pakkedata før TopYou har pakken, hva Peek viser og hvordan overlevering skjer
:::
