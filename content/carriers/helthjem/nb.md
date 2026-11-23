---
title: Helthjem sporing — sporingsnummer, statuser og hentested
description: Spor Helthjem-pakker i Norge: 18- og 20-sifrede numre, nattlevering på dørmatten, hentested, overlevering til PostNord, manglende pakker og kontakt.
slug: helthjem-sporing
published: 2026-11-23
updated: 2026-11-23
---

**Helthjem** (*Helthjem Netthandel AS*, registrert i Oslo — [Brønnøysundregistrene](https://virksomhet.brreg.no/nb/oppslag/enheter/914919010)) er en norsk pakketjeneste for nettbutikker som leverer pakker direkte på dørmatten eller i postkassen — ofte på nattrunder sammen med avisbudene — eller til et lokalt hentested. Lim inn sporingsnummeret i feltet øverst for å følge pakken din, eller søk direkte på `helthjem.no`.

## Slik finner du sporingsnummeret fra Helthjem

Du får sporingsnummeret fra nettbutikken som sendte pakken, som regel i sendingsbekreftelsen på e-post eller inne på ordresiden. Finner du ikke noe nummer? Spør nettbutikken, eller se [how to find a tracking number](guide:find-tracking-number) (på engelsk).

Her er formatene Peek kobler til Helthjem (med oppdiktede sifre), sammenlignet med lignende numre fra [Bring](carrier:bring):

| Nummer (oppdiktet) | Hvem det tilhører |
| --- | --- |
| `370724764829173600` | Helthjem: 18 sifre som starter med `37072476`, med kontrollsiffer til slutt. |
| `00370724764829173600` | Samme Helthjem-nummer med `00` foran (som under en strekkode): 20 sifre. |
| `370482917360514822` | [Bring](carrier:bring): egne 18-sifrede numre starter også på `370` eller `373`. |
| `RR482917367NO` | Bring: postnumre som slutter på `NO`. |

Siste siffer i disse 18- og 20-sifrede kodene er et kontrollsiffer. Helthjems eget søk på `helthjem.no` tar også 17-sifrede sendingsnumre (med eller uten `401` foran). Fordi Helthjems nummerserie ligner sterkt på Brings `370`-numre, skiller Peek dem ved starten `37072476` og kontrollsifferet. Hvis Helthjem ikke kjenner et nummer eller har slettet gammel historikk, finner søket ingenting — dobbeltsjekk sifrene, prøv Bring, eller hør med nettbutikken. Se også [tracking number formats](guide:tracking-number-formats).

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

**Hvorfor står det et annet sted enn adressen din?** I meldinger som `Vi har mottatt pakken din...` og `Pakken er under transport.` viser stedsnavnet terminalen eller sorteringsanlegget som behandlet pakken — for eksempel Helthjems avdeling på Vestby utenfor Oslo — ikke hvor pakken skal leveres.

## Levering med Helthjem: nattrunder, dørmatte og hentested

- **Avisbud og seksdagers levering:** Helthjem samarbeider med avisdistribusjonsnettverket i Norge (seks leveringsdager i uken) og overlater større pakker som ikke kan legges ved døren til [PostNord](carrier:postnord).
- **Nattrunder:** Dagen før hjemmelevering melder Helthjem gjerne `Planlagt levering natt til ...` — det betyr at pakken er booket på runden natt til den angitte datoen, ikke at budet allerede står utenfor.
- **Levering på dørmatte eller i postkasse:** Den siste meldingen angir om pakken er lagt på dørmatten eller i postkassen. På Helthjems egen sporingsside kan du ofte se et leveringsbilde fra budet (Peek viser ikke bildet).
- **Hentested:** Store pakker, adresser uten nattdistribusjon eller sendinger der du valgte hentested får meldingen `På vei til hentested`. Helthjem setter da en ny etikett med nytt nummer på pakken, men det opprinnelige nummeret ditt finner den fortsatt. Når statusen endres til `Klar til henting`, viser `helthjem.no` navnet og adressen på hentestedet. Hentes ikke pakken i tide, følger `Pakke ikke hentet` og retur til butikken.
- **Overlevering til PostNord og pakker fra Kina via 4PX:** Når Helthjem markerer en pakke som et PostNord-produkt, viser sporingen videre til [PostNord](carrier:postnord) med sendingsnummeret. Ved 4PX-sendinger fra Kina betyr en engelsk `Parcel arrival notified`-linje at Helthjem har fått forhåndsmeldingen fra nettbutikken.

## Forsinket, savnet eller skadet Helthjem-pakke

- **Ingen oppdatering:** Etter `Planlagt levering natt til ...` eller `Budet fikk ikke med seg pakken din ut i natt.` venter pakken på neste nattrunde. Skjer det ingenting over flere dager, kontakt nettbutikken — se også [tracking that isn't updating](guide:tracking-not-updating).
- **Levert, men borte:** Sjekk om siste status sier dørmatte eller postkasse, se etter leveringsbilde på `helthjem.no`, sjekk begge steder og spør husstanden. Er den fortsatt borte, kontakt nettbutikken ([delivered not received](guide:delivered-not-received)).
- **Mistet, skadet eller forsinket:** Det er nettbutikken som er Helthjems oppdragsgiver. [Forbrukerrådet](https://www.forbrukerradet.no/forside/okonomi-og-betaling/netthandel-misfornoyd/) anbefaler å klage til selger først; med en norsk nettbutikk kan saken deretter bringes inn for Forbrukertilsynet til mekling, og ved betaling med kredittkort har du lovfestet rett til å kreve pengene tilbake fra banken dersom varen ikke kommer frem ([lost parcel](guide:lost-parcel)).
- **Returnert eller kansellert:** Ved `Pakke ikke hentet`, `Returneres` eller `Pakken er kansellert` kontakter du nettbutikken for refusjon eller ny utsending.

> **Advarsel mot SMS-svindel:** [Nkom](https://nkom.no/aktuelt/slik-kan-du-avslore-sms-svindel) advarer mot å klikke på lenker i pakke-SMS som leder til falske sider og ber om kortopplysninger — vær ekstra kritisk dersom adressen ikke slutter på `.no`. [Politiet](https://www.politiet.no/rad/beskytt-deg-mot-svindel-og-id-tyveri) råder til å skrive inn nettsiden selv i nettleseren ([delivery scam texts](guide:delivery-scam-texts)).

## Spor Helthjem med Peek

Lim inn et 18-sifret nummer som starter med `37072476` (eller 20 sifre med `00` foran) med gyldig kontrollsiffer i Peek, så gjenkjennes **Helthjem** umiddelbart. Andre numre som starter på `370` eller `373` kan tilhøre [Bring](carrier:bring), så Peek sjekker relevante transportører. For Helthjems 17-sifrede sendingsnumre eller andre 20-sifrede strekkoder velger du Helthjem manuelt via **Change** ved tilføyelse eller **Change carrier** på pakkesiden — eller limer inn sporinglenken fra `helthjem.no` (Peek-grensesnittet er på engelsk).

Peek leser sporingen direkte via den åpne [universal-parcel-scraper Helthjem-adapteren](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/helthjem). Den viser status, alle skannesteder (også sorteringsskanninger som Helthjems egen side skjuler), leveringstidspunkt, nettbutikkens navn, hentestedets navn samt eventuelt nytt etikettnummer ved omadressering til hentested, og bruker datoen i `Planlagt levering natt til ...` som forventet leveringsdag. Leveringsbilde, gateadresse og postnummer utelates av personvernhensyn. Når Helthjem overfører pakken til [PostNord](carrier:postnord), følger Peek sendingen videre hos PostNord så snart PostNord registrerer nye skanninger.

Peek oversetter Helthjems statuser til tydelige trinn: **Label created** (forhåndsmeldt av nettbutikken), **Received by carrier** (mottatt ved Helthjem-terminal), **In transit** (under transport, planlagt nattrunde og på vei til hentested), **Delivery attempted** (`Budet fikk ikke med seg pakken din ut i natt.`), **Ready for pickup** (`Klar til henting`), **Delivered** (levert på dørmatte, i postkasse eller hentet), **Returned to sender** (`Pakke ikke hentet` / `Returneres`) og **Needs attention** (`Pakken er kansellert`).

Hvis det er stille mellom skanninger eller i helgen, viser Peek: „Parcels often go quiet on long flights, at customs or over a weekend. Peek keeps asking Helthjem.“ Etter mer enn tre uker uten nytt skann vises: „Nothing new for over three weeks. If it still hasn’t arrived, ask the seller. Peek keeps asking Helthjem.“ Du kan åpne Helthjems kundeservice direkte fra pakkekortet med **Get help from Helthjem**.

I Peeks varslingsinnstillinger velger du mellom **Every scan**, **Important steps** eller **Delivery only**.

## Kontakt Helthjem kundeservice

Helthjem tilbyr hjelpesenter og kontaktskjema på `helthjem.no` (`helthjem.no/kontakt-oss`). Gjelder henvendelsen selve bestillingen, refusjon, erstatning eller ny utsending, skal du alltid kontakte nettbutikken som sendte pakken, ettersom nettbutikken er Helthjems avtalepart.

## Ofte stilte spørsmål

### Når på døgnet leverer Helthjem?

Hjemmeleveringer går ofte på nattrunder med avisbudene. Når Helthjem legger pakken på en nattrunde, vises `Planlagt levering natt til ...` dagen før med datoen leveringen gjelder.

### Hva betyr „Budet fikk ikke med seg pakken din ut i natt.“?

Det betyr at nattbudet ikke fikk tatt med pakken på den planlagte runden. Pakken ligger fortsatt hos Helthjem og settes opp på en ny nattrunde.

### Hvorfor finner ikke Helthjem sporingsnummeret mitt?

Enten har ikke Helthjem mottatt pakken ennå, gammel historikk er utløpt, eller nummeret tilhører en annen transportør som [Bring](carrier:bring) (som også starter på `370` eller `373`). Kontroller sifrene eller spør nettbutikken.

### Hvordan kontakter jeg Helthjem ved manglende eller skadet pakke?

Bruk kontaktsiden på `helthjem.no` for leveringsspørsmål, og kontakt alltid nettbutikken først for reklamasjon, erstatning eller ny vare.

:::sources
- [Brønnøysundregistrene — Helthjem Netthandel AS](https://virksomhet.brreg.no/nb/oppslag/enheter/914919010)
- [Forbrukerrådet — Misfornøyd med netthandel](https://www.forbrukerradet.no/forside/okonomi-og-betaling/netthandel-misfornoyd/)
- [Nkom — Slik kan du avsløre SMS-svindel](https://nkom.no/aktuelt/slik-kan-du-avslore-sms-svindel)
- [Politiet — Beskytt deg mot svindel og ID-tyveri](https://www.politiet.no/rad/beskytt-deg-mot-svindel-og-id-tyveri)
- [universal-parcel-scraper Helthjem-adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/helthjem)
:::
