---
title: DSV sporing — forsendelsesnumre, DB Schenker og status
description: Spor en DSV- eller tidligere DB Schenker-forsendelse med vej-, luft- eller søfragt. Se nummerformater, myDSV-statusser, told, eClaims og kontakt i Danmark.
slug: dsv-sporing
published: 2026-11-23
updated: 2026-11-23
---

**DSV** er en dansk transport- og logistikkoncern med hovedkvarter i Hedehusene, der fragter gods med bil, fly og skib i mere end 90 lande ([dsv.com/da-dk](https://www.dsv.com/da-dk)). I april 2025 gennemførte DSV opkøbet af **Schenker (DB Schenker)** fra Deutsche Bahn, og `dbschenker.com` er integreret på `dsv.com`. Under sporing på [Book and track with myDSV](https://www.dsv.com/en/digital-solutions/book-and-track) findes fortsat to felter — *Track DSV shipment* (myDSV) og *Track Schenker shipment* (tidligere DB Schenker) — uden krav om login. Du kan også indsætte dit nummer i feltet ovenfor.

## DSV og DB Schenker forsendelsesnumre

Forsendelsesnummeret kommer fra afsenderen eller netbutikken, der bestilte transporten. Mens DSV samler Schenker i sine egne systemer, holdes de to sporingssystemer adskilt: hvis det ene ikke kender nummeret, så prøv det andet.

| Nummer (opdigtet) | Hvad det er |
| --- | --- |
| `DKRPL482917360` | 14 tegn: europæisk landekode (fx `DK`, `DE`, `SE`, `PL`) + 3 bogstaver + 9 cifre — tidligere DB Schenker vejfragtsnummer og formatet Peek foreslår DSV for. |
| `SYD4829173` | 3 bogstaver + 7 eller 8 cifre — andet tidligere DB Schenker-vejfragtsnummer (vælg DSV manuelt i Peek). |
| `094829173605148291` | 18 cifre (eller 20 cifre med `00` foran) — kolli-stregkode på en pakke i en tidligere DB Schenker-vejfragtssending. |
| Bogstaver og cifre (5–40 tegn) | Et myDSV-forsendelses-, booking- eller house-bill-nummer med mindst ét ciffer. |

Et direkte sporingslink fra `mydsv.com/track-shipment?query=...` eller Schenker-link med `refNumber=` genkendes straks som DSV i Peek. Kunderelaterede ordrenumre og flyselskabets master air waybill kan nogle gange findes i myDSV's søgning, men Peek følger kun DSV's eget forsendelses-, booking- eller house-bill-nummer. Se også [tracking number formats](guide:tracking-number-formats) (på engelsk).

## Hvad DSV- og DB Schenker-statusser betyder

myDSV registrerer alle hovedscanninger fra booking til levering. Her er de vigtigste myDSV-statusser:

| myDSV-tekst | Hvad det betyder på dansk |
| --- | --- |
| `Booking` / `Creation` | DSV har oprettet bookingen eller vejfragtssendingen i systemet før afhentning. |
| `Actual pickup` / `Cargo received` | DSV har afhentet godset hos afsender eller modtaget det på en terminal. |
| `Actual Departure` / `Actual departure - Origin Port` | Godset er afgået fra terminal, lufthavn eller havn på hovedstrækningen. |
| `Actual arrival` / `Actual arrival- Destination Port` | Ankommet til ankomstterminal, lufthavn eller havn. |
| `Handover responsibilty of cargo` / `Handover of Cargo` | DSV har overdraget godset ved afslutningen af sin opgave (bevarer DSV's egen stavemåde). |
| `Actual delivery` | Leveret til modtageren. |

Planlagte datoer (`Estimated Departure`, `Estimated arrival`, `Estimated pickup`, `Estimated delivery`, `Requested pickup`, `Requested delivery`) er kun prognoser og tæller ikke som udførte scanninger; `Estimated delivery` vises som forventet leveringsdato eller -interval indtil udkørsel.

Det tidligere **DB Schenker**-sporingssystem bruger egne formuleringer på vejfragt:

| DB Schenker-tekst | Hvad det betyder på dansk |
| --- | --- |
| `Booked` / `Out for collection` | Transporten er booket, eller chaufføren er på vej for at hente godset. |
| `Collected` / `Delivered to terminal by shipper` | Afhentet hos afsender eller indleveret på terminal. |
| `Arrived` / `Departed` / `Linehaul departed` / `Terminal inventory` | Ankomst, afgang eller terminalhåndtering undervejs (også `Vessel/Train Loaded` og `Unloaded`). |
| `Customs clearance initiated` / `Customs clearance finalised` | Toldbehandling påbegyndt, henholdsvis afsluttet så godset kører videre. |
| `Out for Delivery` | Ude med chauffør til levering. |
| `Not delivered` | Levering mislykkedes — ofte efterfulgt af en årsag. |
| `To consignee's disposal` / `Ready for pick up` | Klar til afhentning for modtageren. |
| `Delivered` / `Picked up by consignee` / `ePOD available` | Leveret, afhentet af modtager/agent eller elektronisk kvittering klar. |
| `Not Collected` / `Not loaded` | Afhentning mislykkedes, eller godset kom ikke med planlagt afgang. |

## Levering, stykgods, told og reklamationer

- **Paller og stykgods:** DSV driver et stort europæisk stykgodsnetværk i over 36 lande med mere end 400 terminaler og forsendelser op til 2.500 kg — større onlinekøb og møbler ankommer derfor typisk med fragtbil fra nærmeste DSV-terminal. DSV angiver ikke et fast antal leveringsforsøg eller liggetid på hjemmesiden; spørg det lokale DSV-kontor ved behov.
- **Netbutikker og sidste mil:** Med DSV Parcel E-commerce konsolideres pakker på tværs af landegrænser og videregives derefter til lokale pakkedistributører. Hvis netbutikkens e-mail nævner et sekundært nummer fra en lokal pakkedistributør (fx [PostNord](carrier:postnord) eller [Early Bird](carrier:early-bird)), så spor også dette nummer.
- **Told i EU og Danmark:** DSV er autoriseret toldklarerer i de fleste lande, hvor selskabet har kontorer. For varer købt online uden for EU gælder fra 1. juli 2026 en told på € 3 pr. varetype på forsendelser op til € 150, og fra 1. november 2026 et EU-håndteringsgebyr på € 2 pr. varetype uanset værdi — se mere i [customs](guide:customs).
- **Forsinkelse, skader og eClaims:** Hvis sporingen går i stå, tjek begge sporingsfelter på dsv.com og kontakt derefter afsenderen ([tracking not updating](guide:tracking-not-updating)). Skader eller bortkommet gods anmeldes hurtigst muligt via sælgeren eller DSV's [eClaims-portal](https://www.dsv.com/en/support/self-services/claims) ([damaged parcel](guide:damaged-parcel), [lost parcel](guide:lost-parcel)).
- **Misbrug af DSV's navn:** I sin [Legal notice](https://www.dsv.com/en/about-dsv/legal-notice) advarer DSV mod svindlere, der uden tilknytning til DSV misbruger firmanavnet. Tjek altid tvivlsomme henvendelser hos et lokalt DSV-kontor ([delivery scam texts](guide:delivery-scam-texts)).

## Spor DSV med Peek

Indsætter du et 14-tegns nummer som `DKRPL482917360`, foreslår Peek DSV med det samme og spørger myDSV og DB Schenker-vejfragt direkte. Ved andre nummerformater (eller hvis du vil skifte manuelt) trykker du **Change** ved tilføjelse — søg efter DSV eller *Schenker* — eller **Change carrier** på pakkesiden (Peek-appen har engelsk brugerflade). Direkte links fra `mydsv.com` og Schenker-links med `refNumber=` genkendes automatisk.

Peek henter data gennem den åbne [universal-parcel-scraper DSV-adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/dsv). Den spørger myDSV først og falder for vejfragt videre til den tidligere DB Schenker-sporing, hvis myDSV ikke kender nummeret (luft- og søfragt, der kun findes i Schenker, tjekkes direkte under *Track Schenker shipment* på dsv.com). Peek viser status, hvert trin med by og land samt forventet leveringsdato, og udleder tidszonen fra scanningsstedet eller dansk tid, fordi DSV leverer lokal urtid uden tidszone. Fortrolige detaljer som afsenderby, modtageradresse, postnumre og kvitteringsnavn udelades. Hvis svenske [Early Bird](carrier:early-bird) videregiver en pakke til DSV/Schenker, følger Peek overdragelsen videre til DSV.

Peek omsætter DSV- og Schenker-scanningerne til tydelige faser: **Label created** (`Booking`, `Creation`, `Booked`), **Received by carrier** (`Actual pickup`, `Cargo received`, `Collected`), **In transit** (terminal-, havne-, lufthavns- og toldfrigivelsesscanninger), **At customs** (`Customs clearance initiated`), **Out for delivery** (`Out for Delivery`), **Delivery attempted** (`Not delivered`), **Ready for pickup** (`Ready for pick up` / `To consignee's disposal`), **Needs attention** (`Not Collected`, `Not loaded`) og **Delivered** (`Actual delivery`, `Handover of Cargo`, `Delivered`, `ePOD available`).

Ved længere strækninger, told eller weekender viser Peek: „Parcels often go quiet on long flights, at customs or over a weekend. Peek keeps asking DSV.“ Efter mere end tre uger uden nye scanninger vises: „Nothing new for over three weeks. If it still hasn’t arrived, ask the seller. Peek keeps asking DSV.“ Du kan åbne DSV's officielle hjælpeside direkte via **Get help from DSV**.

I Peeks notifikationsindstillinger kan du vælge **Every scan**, **Important steps** eller **Delivery only**.

## Kontakt DSV i Danmark og globalt

DSV's hjælpesider opgiver ikke et separat telefonnummer til pakkesporing, men henviser til onlineformular og lokale kontorer:

- **Kontaktformular:** [DSV Help and contact](https://www.dsv.com/en/support/help-and-contact)
- **Find lokalt DSV-kontor:** [Find your local DSV office](https://www.dsv.com/en/support/find-dsv) (over 90 lande, herunder danske afdelinger)
- **Skadesanmeldelse:** [DSV Claims / eClaims](https://www.dsv.com/en/support/self-services/claims)
- **Hovedkontor (DSV A/S, Danmark):** Hedehusene, Danmark, `+45 43 20 30 40` ([Legal notice](https://www.dsv.com/en/about-dsv/legal-notice) — hovednummer, ikke en pakkesporingslinje)
- **Dansk forside:** [dsv.com/da-dk](https://www.dsv.com/da-dk)

## Ofte stillede spørgsmål

### Er DSV og DB Schenker det samme selskab?

Ja. DSV (grundlagt i Danmark i 1976) gennemførte opkøbet af Schenker fra Deutsche Bahn den 30. april 2025, og `dbschenker.com` er samlet på `dsv.com`. Schenker-forsendelser bruger midlertidigt eget sporingsfelt under overgangen.

### Hvordan sporer jeg en DB Schenker-forsendelse nu?

Indtast nummeret i *Track Schenker shipment* på [dsv.com](https://www.dsv.com/en/digital-solutions/book-and-track) eller sæt det ind i Peek øverst på siden. Hvis nummeret ikke er 14 tegn med landekode forrest, vælger du selv **DSV** som transportør, hvorefter Peek tjekker både myDSV og Schenker-vejfragt.

### Hvad betyder „Handover of Cargo“ i DSV's sporing?

Det betyder, at DSV har overdraget godset ved afslutningen af den aftalte transportydelse, så Peek markerer forsendelsen som **Delivered**. Har du ikke modtaget varen, så spørg afsenderen, hvem DSV overleverede godset til ([delivered not received](guide:delivered-not-received)).

### Har DSV et dansk telefonnummer til pakkesporing?

Nej — DSV's hjælpesider henviser til kontaktformularen på [Help and contact](https://www.dsv.com/en/support/help-and-contact) og det lokale DSV-kontor via [Find DSV](https://www.dsv.com/en/support/find-dsv). Hovedkontorets nummer `+45 43 20 30 40` i Hedehusene er ikke en sporingshotline.

:::sources
- [DSV Danmark](https://www.dsv.com/da-dk)
- [DSV — Book and track with myDSV](https://www.dsv.com/en/digital-solutions/book-and-track)
- [DSV — Help and contact](https://www.dsv.com/en/support/help-and-contact)
- [DSV — Find your local DSV office](https://www.dsv.com/en/support/find-dsv)
- [DSV — Claims (eClaims)](https://www.dsv.com/en/support/self-services/claims)
- [DSV — Legal notice](https://www.dsv.com/en/about-dsv/legal-notice)
- [DSV-adapter i universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/dsv)
:::
