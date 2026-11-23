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
| `KLM4829173` | 3 bogstaver + 7 eller 8 cifre — andet tidligere DB Schenker-vejfragtsnummer. Ud fra denne form angiver Peek ikke DSV (vælg DSV selv i Peek). |
| `094827361549283711` | 18 cifre (eller 20 cifre med `00` foran) — kolli-stregkode på en pakke i en tidligere DB Schenker-vejfragtssending. |
| Bogstaver og cifre (5–40 tegn) | Et myDSV-forsendelses-, booking- eller house-bill-nummer med mindst ét ciffer. |

Et direkte sporingslink fra `mydsv.com/track-shipment?query=...` eller Schenker-link med `refNumber=` genkendes straks som DSV i Peek. Kunderelaterede ordrenumre og flyselskabets master air waybill kan nogle gange findes i myDSV's søgning, men Peek følger kun DSV's eget forsendelses-, booking- eller house-bill-nummer. Se også [tracking number formats](guide:tracking-number-formats) (på engelsk).

## Hvad DSV- og DB Schenker-statusser betyder

myDSV registrerer alle hovedscanninger fra booking til levering. Her er de vigtigste myDSV-statusser:

| myDSV-tekst | Hvad det betyder på dansk |
| --- | --- |
| `Booking` / `Creation` | DSV har oprettet bookingen eller vejfragtssendingen i systemet før afhentning. |
| `Actual pickup` / `Cargo received` | DSV har afhentet godset hos afsender eller modtaget det på en terminal. |
| `Actual Departure` / `Actual departure - Origin Port` | Godset har forladt en havn eller lufthavn på hovedstrækningen. |
| `Actual arrival` / `Actual arrival- Destination Port` | Ankommet til en havn eller lufthavn på hovedstrækningen. |
| `Handover responsibilty of cargo` / `Handover of Cargo` | DSV har overdraget godset ved afslutningen af sin opgave (bevarer DSV's egen stavemåde). |
| `Actual delivery` | Leveret til modtageren. |

Planlagte datoer (`Estimated Departure`, `Estimated arrival`, `Estimated pickup`, `Estimated delivery`, `Requested pickup`, `Requested delivery`) er kun prognoser og tæller ikke som udførte scanninger; Peek udelader dem af historikken og viser `Estimated delivery` som forventet leveringsdag eller interval af dage, indtil forsendelsen er leveret.

Det tidligere **DB Schenker**-sporingssystem bruger egne formuleringer på vejfragt:

| DB Schenker-tekst | Hvad det betyder på dansk |
| --- | --- |
| `Booked` / `Out for collection` | Transporten er booket, eller chaufføren er på vej for at hente godset. |
| `Collected` / `Delivered to terminal by shipper` | Afhentet hos afsender eller indleveret på terminal. |
| `Arrived` / `Departed` / `Linehaul departed` / `Terminal inventory` | Ankomst, afgang eller terminalhåndtering undervejs (også `Vessel/Train Loaded` og `Unloaded`). |
| `Customs clearance initiated` / `Customs clearance finalised` | Toldbehandling påbegyndt, henholdsvis afsluttet så godset kører videre. |
| `Out for Delivery` | Ude med chauffør til levering. |
| `Not delivered` | Levering mislykkedes; en årsag kan følge. |
| `To consignee's disposal` / `Ready for pick up` | Klar til afhentning for modtageren. |
| `Delivered` / `Picked up by consignee` / `ePOD available` | Leveret, afhentet af modtager/agent eller elektronisk kvittering klar. |
| `Not Collected` / `Not loaded` | Afhentning mislykkedes, eller godset kom ikke med planlagt afgang. |

## Levering, stykgods, told og reklamationer

- **Paller og stykgods:** DSV driver et stort europæisk stykgodsnetværk i over 36 lande med mere end 400 terminaler og forsendelser op til 2.500 kg. DSV angiver ikke et fast antal leveringsforsøg eller liggetid på hjemmesiden; spørg det lokale DSV-kontor ved behov.
- **Netbutikker og sidste mil:** Med DSV Parcel E-commerce konsolideres pakker på tværs af landegrænser og videregives derefter til lokale pakkedistributører. Hvis netbutikkens e-mail nævner et sekundært nummer fra en lokal pakkedistributør, så spor også dette nummer.
- **Told i EU og Danmark:** DSV er autoriseret toldklarerer i de fleste lande, hvor selskabet har kontorer. For varer købt online uden for EU gælder fra 1. juli 2026 en told på € 3 pr. varetype på forsendelser op til € 150, og fra 1. november 2026 et EU-håndteringsgebyr på € 2 pr. varetype uanset værdi — se mere i [customs](guide:customs).
- **Forsinkelse, skader og eClaims:** Hvis sporingen går i stå, tjek begge sporingsfelter på dsv.com og kontakt derefter afsenderen ([tracking not updating](guide:tracking-not-updating)). Skader eller bortkommet gods anmeldes hurtigst muligt via sælgeren eller DSV's [eClaims-portal](https://www.dsv.com/en/support/self-services/claims) ([damaged parcel](guide:damaged-parcel), [lost parcel](guide:lost-parcel)).
- **Misbrug af DSV's navn:** I sin [Legal notice](https://www.dsv.com/en/about-dsv/legal-notice) advarer DSV mod svindlere, der uden tilknytning til DSV misbruger firmanavnet. Tjek altid tvivlsomme henvendelser hos et lokalt DSV-kontor ([delivery scam texts](guide:delivery-scam-texts)).

## Spor DSV med Peek

Indsætter du et nummer på 14 tegn som `DKRPL482917360`, spørger Peek DSV, mens du tilføjer det ("Checking tracking services…"), og viser "DSV has this parcel.", når DSV kender det. Har DSV det ikke endnu, står der "Waiting for DSV’s first scan" og "Probably DSV", mens Peek bliver ved med at spørge; efter en uge beder Peek dig tjekke nummeret eller vælge en anden transportør. Andre numre kan Peek ikke genkende som DSV's ud fra formen: tryk "Change" ved tilføjelsen og søg efter DSV (eller "Schenker"), eller brug "Change carrier" på en pakke, du allerede har tilføjet (Peek-appen har engelsk brugerflade). Et sporingslink fra `mydsv.com` og et Schenker-link med `refNumber=` placerer straks forsendelsen hos DSV.

Peek henter data gennem den åbne [universal-parcel-scraper DSV-adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/dsv) og spørger myDSV først. Et tidligere DB Schenker-vejfragtsnummer eller en kolli-stregkode, som myDSV ikke kender, går videre til Schenker-sporingen, som Peek kun læser for vejfragt: luft- og søfragt, der kun findes dér, tjekker du under *Track Schenker shipment* på dsv.com. Peek viser status, hvert trin i DSV's ordlyd med by og land samt forventet leveringsdag. DSV giver hver scannings lokale tid uden tidszone; Peek udleder zonen fra scanningsstedet (en by, en havn, en lufthavnskode) eller scanningerne omkring den, ellers dansk tid, så den en sjælden gang kan tage fejl. Modtagerens navn, når DSV oplyser det, vises under "Recipient" på din egen pakkeside, og byen i leveringsadressen kan afslutte ruten på dit eget kort, mens forsendelsen er undervejs; delte links viser ingen af delene. Peek udelader servicenavnet, afsenderens by, gadeadressen, postnumre og DSV's fremdriftslinje samt fra Schenker-sporingen afsenderen, referencer, gods og navnet på den, der kvitterede. Hvis det svenske leveringsselskab Early Bird overdrager en pakke til DSV eller DB Schenker, angiver Peek DSV som den transportør, der leverer, og følger DSV, når DSV viser nye scanninger.

Peek omsætter DSV- og Schenker-scanningerne til tydelige faser: **Label created** (`Booking`, `Creation`, `Booked`), **Received by carrier** (`Actual pickup`, `Cargo received`, `Collected`), **In transit** (terminal-, havne-, lufthavns- og toldfrigivelsesscanninger), **At customs** (`Customs clearance initiated`), **Out for delivery** (`Out for Delivery`), **Delivery attempted** (`Not delivered`), **Ready for pickup** (`Ready for pick up` / `To consignee's disposal`), **Needs attention** (`Not Collected`, `Not loaded`) og **Delivered** (`Actual delivery`, `Handover of Cargo`, `Delivered`, `ePOD available`).

Er den nyeste scanning fire dage gammel, og står forsendelsen stadig som "Label created", "Received by carrier" eller "In transit", markerer Peek den med scanningens dag, fx "No tracking update since Mon 12 Oct". Forsendelsens side skriver så "Parcels often go quiet on long flights, at customs or over a weekend. Peek keeps asking DSV." med linket "Get help from DSV" til DSV's hjælpe- og kontaktside. Efter tre uger uden ny scanning står der "Nothing new for over three weeks. If it still hasn’t arrived, ask the seller. Peek keeps asking DSV."

Mens du har forsendelsen åben i Peek, eller på leveringsdagen med notifikationer slået til, tjekker Peek den op til hvert 10. minut, og op til hvert 2. minut fra kl. 6 lokal tid den dag, DSV viser `Out for Delivery`, så længe DSV's egen sporing svarer; ellers hver time. Efter to dage uden nyt (12 timer, mens forsendelsen stadig står som "Label created" eller "Received by carrier") tjekker Peek hver time, og hver 6. time, når den har stået som "Label created" i syv dage. Svarer DSV's sporing ikke, og følger Peek forsendelsen alene via sporingstjenester, tjekker Peek hver time, hver 3. time efter et døgn uden nyt og op til hvert 15. minut på udbringningsdagen. Peek kan give besked ved "Every scan", "Important steps" eller "Delivery only".

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

Indtast nummeret i *Track Schenker shipment* på [dsv.com](https://www.dsv.com/en/digital-solutions/book-and-track) eller sæt det ind i Peek øverst på siden. Hvis nummeret ikke er 14 tegn med landekode forrest, vælger du selv **DSV** som transportør. Peek spørger myDSV og derefter Schenker-sporingen, hvis det er et tidligere DB Schenker-vejfragtsnummer eller en kolli-stregkode.

### Hvad betyder "Handover of Cargo" i DSV's sporing?

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
- [DSV — Groupage transport](https://www.dsv.com/en/our-solutions/modes-of-transport/road-transport/groupage)
- [DSV — E-commerce delivery solutions](https://www.dsv.com/en/our-solutions/contract-logistics/ecommerce-solutions/last-mile-delivery)
- [DSV — Customs clearance](https://www.dsv.com/en/our-solutions/modes-of-transport/value-added-services/customs-clearance)
- [DSV — History](https://www.dsv.com/en/about-dsv/history)
- [DSV — DSV completes acquisition of Schenker](https://www.dsv.com/en/about-dsv/press/news/dsv-completes-acquisition-of-schenker-2628130)
- [Europa-Kommissionen — EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en)
- [Europa-Kommissionen — Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf)
- [DSV-adapter i universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/dsv)
:::
