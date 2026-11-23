---
title: Helthjem sporing — sporingsnummer, statuser og hentested
description: Spor Helthjem-pakker i Norge: 18- og 20-sifrede numre, nattlevering på dørmatten, hentested, overlevering til PostNord, manglende pakker og kontakt.
slug: helthjem-sporing
published: 2026-11-23
updated: 2026-11-23
---

**Helthjem** (*Helthjem Netthandel AS*, registrert i Oslo — [Brønnøysundregistrene](https://virksomhet.brreg.no/nb/oppslag/enheter/914919010)) er en norsk pakketjeneste for nettbutikker som legger pakker på dørmatten eller i postkassen, også på nattrunder, eller sender dem til et hentested. Lim inn sporingsnummeret i feltet øverst for å følge pakken din, eller søk direkte på `helthjem.no`.

## Slik finner du sporingsnummeret fra Helthjem

Du får sporingsnummeret fra nettbutikken som sendte pakken, som regel i sendingsbekreftelsen på e-post eller inne på ordresiden. Finner du ikke noe nummer? Spør nettbutikken, eller se [how to find a tracking number](guide:find-tracking-number) (på engelsk).

Her er formatene Peek kobler til Helthjem (med oppdiktede sifre), sammenlignet med lignende numre fra [Bring](carrier:bring):

| Nummer (oppdiktet) | Hvem det tilhører |
| --- | --- |
| `370724764829173600` | Helthjem: 18 sifre som starter med `37072476`, med kontrollsiffer til slutt. |
| `00370724764829173600` | Samme Helthjem-nummer med `00` foran (som under en strekkode): 20 sifre. |
| `370482917360514822` | [Bring](carrier:bring): egne 18-sifrede numre starter også på `370` eller `373`. |
| `RR482917367NO` | Bring: postnumre som slutter på `NO`. |

Siste siffer i disse 18- og 20-sifrede kodene er et kontrollsiffer. Helthjems eget søk på `helthjem.no` tar også 17-sifrede sendingsnumre (med eller uten `401` foran). Fordi Helthjems nummerserie ligner sterkt på Brings `370`-numre, skiller Peek dem ved starten `37072476` og kontrollsifferet. Hvis Helthjem ikke kjenner et nummer, eller ikke lenger har det, finner søket ingenting: dobbeltsjekk sifrene, prøv Bring, eller spør nettbutikken hvilken transportør den booket. Se også [tracking number formats](guide:tracking-number-formats).

## Hva sporingsstatusene hos Helthjem betyr

Helthjems sporingsside viser pakkens status, skanninger med sted, beregnet leveringsdag, avsenderbutikk og — når det gjelder — navn og adresse på hentestedet samt et bilde av leveringen på dørmatten eller ved postkassen. Helthjem plasserer sendingen i tolv hovedtilstander på norsk bokmål:

| Helthjem-status | Hva det betyr for deg |
| --- | --- |
| `Nettbutikken har meldt at en pakke er på vei!` | Nettbutikken har forhåndsmeldt pakken; Helthjem har ikke mottatt den fysisk ennå. |
| `Vi har mottatt pakken din, og gjør den klar for veien videre.` | Pakken er registrert ved Helthjem-terminalen som oppgis i skanningen og klargjøres for videre transport. |
| `Pakken er under transport.` | Underveis i nettverket — skannet ved terminal eller sorteringsanlegg. |
| `Planlagt levering natt til 8. oktober!` | Satt opp på nattrunden natt til oppgitt dato. Budet har den ikke med seg ennå; den kjøres ut den natten. |
| `Budet fikk ikke med seg pakken din ut i natt.` | Nattbudet fikk ikke tatt med pakken på runden; den venter på en senere leveringsnatt. |
| `Pakken er levert på dørmatten din!` | Levert utenfor inngangsdøren på dørmatten. |
| `Pakken er levert i postkassen din!` | Levert i postkassen din. |
| `På vei til hentested` / `Leveres til hentested` | Sendt til et lokalt hentested i stedet for hjemmelevering. |
| `Klar til henting` | Klar til henting på utleveringsstedet som oppgis på Helthjems side. |
| `Pakken er hentet` | Hentet ut på hentestedet. |
| `Pakke ikke hentet` | Ikke hentet innen fristen; pakken returneres til nettbutikken. |
| `Returneres` | På vei tilbake til avsender, eller allerede returnert. |
| `Pakken er kansellert` | Sendingen er kansellert. |

**Hvorfor står det et annet sted enn adressen din?** I `Vi har mottatt pakken din…` og `Pakken er under transport.` er stedet Helthjem-terminalen eller sorteringsanlegget som behandlet pakken, ikke adressen din. I 2023 drev Helthjem sin egen pakketerminal på Vestby utenfor Oslo, og Enhetsregisteret oppfører fortsatt en Helthjem-avdeling der, så en skanning på Vestby sier hvor pakken ble sortert, ikke hvor den skal.

## Levering med Helthjem: nattrunder, dørmatte og hentested

- **Hvem som leverer:** I 2023 solgte Helthjem pakkelevering til nettbutikker og ga pakkene til avisdistributørene i Helthjem-nettverket, som alle leverte seks dager i uken. Større pakker som ikke kunne settes ved døren, gikk med [PostNord](carrier:postnord).
- **Nattrunder:** Dagen før hjemmelevering melder Helthjem gjerne `Planlagt levering natt til ...` — det betyr at pakken er booket på runden natt til den angitte datoen, ikke at budet allerede står utenfor.
- **Levering på dørmatte eller i postkasse:** Den siste meldingen angir om pakken er lagt på dørmatten eller i postkassen. Helthjems sporingsside kan vise et bilde av leveringen; Peek viser det ikke, så se der.
- **Hentested:** I 2023 delte Helthjem ut pakker i butikker: for store pakker, for dem som heller ville hente selv, og der distributørene ikke leverte. En pakke som sendes dit i stedet for hjem til deg, får `På vei til hentested`, og Helthjem setter en ny etikett med nytt nummer på den; nummeret ditt finner den fortsatt. Når den er klar, endres statusen til `Klar til henting`, og Helthjems side viser hentestedet og adressen. Hentes ikke pakken i tide, følger `Pakke ikke hentet`, og den går tilbake til butikken.
- **PostNord-pakker og pakker fra Kina via 4PX:** Når Helthjem markerer en pakke som et PostNord-produkt, sender Helthjems sporingsside deg videre til sporingen hos [PostNord](carrier:postnord) med sendingsnummeret. En `Parcel arrival notified`-linje i historikken hos 4PX er Helthjems melding om at butikken har meldt pakken, videreformidlet på engelsk når 4PX overleverer den.

## Forsinket, savnet eller skadet Helthjem-pakke

- **Ingen oppdatering:** Etter `Planlagt levering natt til ...` eller `Budet fikk ikke med seg pakken din ut i natt.` venter pakken på neste nattrunde. Skjer det ingenting over flere dager, kontakt nettbutikken — se også [tracking that isn't updating](guide:tracking-not-updating).
- **Levert, men borte:** Sjekk om siste status sier dørmatte eller postkasse, se etter leveringsbilde på `helthjem.no`, sjekk begge steder og spør husstanden. Er den fortsatt borte, kontakt nettbutikken ([delivered not received](guide:delivered-not-received)).
- **Mistet, skadet eller forsinket:** Det er nettbutikken som er Helthjems oppdragsgiver. [Forbrukerrådet](https://www.forbrukerradet.no/forside/okonomi-og-betaling/netthandel-misfornoyd/) anbefaler å klage til selger først; med en norsk nettbutikk kan saken deretter bringes inn for Forbrukertilsynet til mekling, og ved betaling med kredittkort har du lovfestet rett til å kreve pengene tilbake fra banken dersom varen ikke kommer frem ([lost parcel](guide:lost-parcel)).
- **Returnert eller kansellert:** Ved `Pakke ikke hentet`, `Returneres` eller `Pakken er kansellert` kontakter du nettbutikken for refusjon eller ny utsending.

> **Advarsel mot SMS-svindel:** [Nkom](https://nkom.no/aktuelt/slik-kan-du-avslore-sms-svindel) advarer mot å klikke på lenker i pakke-SMS som leder til falske sider og ber om kortopplysninger — vær ekstra kritisk dersom adressen ikke slutter på `.no`. [Politiet](https://www.politiet.no/rad/beskytt-deg-mot-svindel-og-id-tyveri) råder til å skrive inn nettsiden selv i nettleseren ([delivery scam texts](guide:delivery-scam-texts)).

## Spor Helthjem med Peek

[Peek](/) kjenner igjen Helthjem med en gang fra 18 sifre som starter med `37072476`, eller de samme med `00` foran, når kontrollsifferet stemmer. Andre 18-sifrede numre som starter med `370` eller `373`, kan være Brings: når GS1-kontrollsifferet er gyldig, spør Peek [Bring](carrier:bring), sammen med Matkahuolto for `373`; kjenner flere transportører pakken, velger du. Helthjems 17-sifrede sendingsnumre og andre 20-sifrede strekkoder krever "Change" (eller "Change carrier" på pakkesiden), eller en lenke til pakken på Helthjems sporingsside. Peeks grensesnitt er på engelsk.

Peek leser Helthjems sporing gjennom den åpne [universal-parcel-scraper Helthjem-adapteren](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/helthjem). Peek viser status, stedet for hver skanning (også sorteringsskanninger som Helthjems side skjuler), tidspunktet pakken ble levert, butikken som sendte den, og hentestedets navn mens pakken venter der eller når den er hentet. Når Helthjem setter ny etikett på pakken for et hentested, viser Peek også nummeret på den nye etiketten. Statusene beholder Helthjems norske ordlyd, og tidene stemmer med Helthjems. Når Helthjem booker nattrunden, viser Peek datoen i `Planlagt levering natt til …` som forventet dag. Peek utelater den forventede dagen Helthjems side viser, hentestedets adresse, bildet og postnumre.

Når Helthjem markerer en pakke som PostNord leverer, står det "Delivery with PostNord" på kortet, og Peek kan følge den hos [PostNord](carrier:postnord) under sendingsnummeret. Så lenge PostNord bare har forhåndsmeldingen, uten egen skanning, står det "Announced to PostNord, not received yet" i stedet.

Peek plasserer Helthjems statuser i sine trinn: **Label created** (forhåndsmeldt av nettbutikken), **In transit** (mottatt ved terminalen, under transport, booket på nattrunden, ikke tatt med av nattbudet og på vei til hentested), **Out for delivery** (på runden), **Ready for pickup** (`Klar til henting`), **Delivered** (levert på dørmatten, i postkassen eller hentet), **Needs attention** (`Pakke ikke hentet`, `Pakken er kansellert` og `Returneres` mens pakken er på vei tilbake) og **Returned to sender** (når Helthjem melder pakken som returnert).

Er den nyeste skanningen fire dager gammel og pakken fortsatt står som "Label created", "Received by carrier" eller "In transit", merker Peek den med skanningens dag, for eksempel "No tracking update since Mon 12 Oct". Pakkens side sier da "Parcels often go quiet on long flights, at customs or over a weekend. Peek keeps asking Helthjem." med lenken "Get help from Helthjem" til Helthjems hjelpe- og kontaktside. Etter tre uker uten ny skanning står det "Nothing new for over three weeks. If it still hasn’t arrived, ask the seller. Peek keeps asking Helthjem."

Mens du har pakken åpen i Peek, eller på leveringsdagen med varsler slått på, sjekker Peek den opptil hvert 10. minutt, og opptil hvert 2. minutt fra kl. 6 norsk tid den dagen Helthjem (eller PostNord etter overleveringen) melder pakken ute til levering, så lenge den transportørens egen sporing svarer. Ellers, og etter to dager uten nytt (12 timer mens pakken fortsatt står som "Label created" eller "Received by carrier"), sjekker Peek hver time, og hver 6. time etter sju dager uten nytt på "Label created". Varslene kan følge "Every scan", "Important steps" eller "Delivery only". Peek kan ikke kontakte Helthjem for deg.

## Kontakt Helthjem kundeservice

Helthjems eget nettsted, helthjem.no, har en hjelpe- og kontaktside (`helthjem.no/kontakt-oss`). Gjelder henvendelsen selve bestillingen, refusjon, erstatning eller ny utsending, kontakter du nettbutikken som sendte pakken: det er den som booket Helthjem.

## Ofte stilte spørsmål

### Når på døgnet leverer Helthjem?

Hjemmeleveringer kan komme på en nattrunde. Når Helthjem booker en, viser sporingen `Planlagt levering natt til …` dagen før, med datoen natten leder inn i. Peek viser den datoen som forventet dag.

### Hva betyr "Budet fikk ikke med seg pakken din ut i natt"?

Det betyr at nattbudet ikke fikk tatt med pakken på den planlagte runden. Pakken ligger fortsatt hos Helthjem og settes opp på en ny nattrunde.

### Hvorfor finner ikke Helthjem sporingsnummeret mitt?

Enten har ikke Helthjem mottatt pakken ennå, Helthjem har ikke lenger nummeret, eller nummeret tilhører en annen transportør som [Bring](carrier:bring) (som også starter på `370` eller `373`). Kontroller sifrene eller spør nettbutikken.

### Hvordan kontakter jeg Helthjem ved manglende eller skadet pakke?

Bruk kontaktsiden på `helthjem.no` for leveringsspørsmål, og kontakt alltid nettbutikken først for reklamasjon, erstatning eller ny vare.

:::sources
- [Brønnøysundregistrene — Helthjem Netthandel AS](https://virksomhet.brreg.no/nb/oppslag/enheter/914919010)
- [Brønnøysundregistrene — Helthjem Netthandel AS avd Vestby](https://virksomhet.brreg.no/nb/oppslag/underenheter/925669946)
- [Konkurransetilsynet — Schibsted Delivery AS / Amedia Distribusjon AS (23. oktober 2023)](https://konkurransetilsynet.no/wp-content/uploads/2023/10/2023_0474-1-OFF-Schibsted-Delivery-AS-Amedia-Distribusjon-AS-1.pdf)
- [Forbrukerrådet — Misfornøyd med netthandel](https://www.forbrukerradet.no/forside/okonomi-og-betaling/netthandel-misfornoyd/)
- [Nkom — Slik kan du avsløre SMS-svindel](https://nkom.no/aktuelt/slik-kan-du-avslore-sms-svindel)
- [Politiet — Beskytt deg mot svindel og ID-tyveri](https://www.politiet.no/rad/beskytt-deg-mot-svindel-og-id-tyveri)
- [universal-parcel-scraper Helthjem-adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/helthjem)
:::
