---
title: Swiss Post Cargo volgen: SSCC, statussen en levering
description: Volg een Swiss Post Cargo vrachtzending (voorheen PostLogistics): 20-cijferige SSCC of PL-referentie, statussen, levering aan de stoep en contact.
slug: swiss-post-cargo-pakket-volgen
published: 2026-11-27
updated: 2026-11-27
---

Swiss Post Cargo (voorheen PostLogistics, actief onder deze merknaam sinds 1 maart 2025) is het vrachtnetwerk van de Zwitserse Post voor pallets en zware goederen die per vrachtwagen worden vervoerd door Zwitserland, Liechtenstein en het buitenland. Volg je 20-cijferige `00`-SSCC-streepjescode of `PL-`-afzenderreferentie op `tt.swisspost-cargo.com` of plak die in het zoekvak hierboven (gewone pakketten van de Zwitserse Post vind je op de [pagina van Swiss Post](carrier:swiss-post)). Hieronder: wat `Data entry POST` en `Loaded for Delivery` betekenen, regels voor levering aan de stoep en contactgegevens.

## Swiss Post Cargo trackingnummer

De tracker van Swiss Post Cargo accepteert een streepjescode voor één colli of een referentie van de afzender. Een referentie haalt elke streepjescode binnen die zending op. De formaten die Peek koppelt aan Swiss Post Cargo (verzonnen cijfers):

| Nummer | Wat het is |
| --- | --- |
| `00482917365428193648` | 20 cijfers beginnend met `00`: een pallet- of collistreepjescode (SSCC). Het laatste cijfer is een controlecijfer. |
| `PL-48291736` | `PL-` gevolgd door 8 cijfers: een klantreferentie. |

Controleer eerst de e-mails van de winkel en vraag het daarna de winkel. [Waar je een trackingnummer vindt](guide:find-tracking-number) (Engels) geeft meer plekken om te zoeken. Vindt de tracker niets, controleer dan de cijfers en neem contact op met de afzender.

Twee soorten nummers die niet als Swiss Post Cargo worden gevolgd:

- **Swiss Post-pakketten:** 18 cijfers beginnend met `99` (afgedrukt als `99.00.482917.36542819`) of twee letters, negen cijfers en `CH` (`RR482917367CH`).
- **PostLogistics-referenties** zoals `48291736-542`: PostLogistics opereert nu als Swiss Post Cargo en de oude tracker tracking.postlogistics.ch opent niet meer. Peek volgt deze referenties als PostLogistics.

## Track & trace-statussen van Swiss Post Cargo

Swiss Post Cargo toont scans in het Engels, Duits, Frans of Italiaans. In het Engels verloopt een afgeleverde zending als volgt:

| Swiss Post Cargo meldt | Wat het voor jou betekent |
| --- | --- |
| `Data entry POST` | De gegevens van de afzender zijn binnengekomen. De goederen zijn nog niet bij Swiss Post Cargo. |
| `Received warehouse POST` | De goederen zijn aangekomen bij een hub of depot van Swiss Post Cargo, vermeld naast de scan. |
| `Loaded for Delivery` | Geladen in de bezorgvrachtwagen: onderweg voor levering. |
| `IMAGE` | Een afleverfoto, vermeld vlak voor de afleverscan. |
| `DELIVERED SCANNED` | Afgeleverd in de getoonde plaats. |
| `SIGNATURE` | De handtekening bij aflevering, op hetzelfde tijdstip als de afleverscan. |

`Data entry POST` betreft alleen gegevens: "POST" hoort bij de tekst en er heeft nog geen vervoer plaatsgevonden. `IMAGE` en `SIGNATURE` zijn bewijsstukken bij de aflevering, geen nieuwe vervoersstappen.

## Levering door Swiss Post Cargo: wanneer, waar en gemiste levering

**Wanneer.** De vrachtbrochure van Swiss Post meldt dat vracht doorgaans op de volgende werkdag wordt geleverd. In Zwitserland en Liechtenstein ontvangen plaatsen die voor een vrachtwagen bereikbaar zijn de zending uiterlijk om 17:00 uur Zwitserse tijd op de volgende werkdag. Moeilijk bereikbare Alpendalen en berggebieden (Wallis, Ticino en Graubünden) ontvangen uiterlijk om 17:00 uur op de tweede werkdag.

**Waar.** Standaard gaat vracht tijdens kantooruren tot de eerste deur op de begane grond, het laadperron of de stoeprand, niet naar een verdieping. De chauffeur rekent op toegang met een palletwagen en het lossen is de taak van de ontvanger: als de chauffeur lost, doet hij dat namens jou. De chauffeur vraagt om een handtekening ter bevestiging van correcte aflevering.

**Extra diensten via de afzender.** Vooraanmelding per telefoon, e-mail of sms, geplande levering, levering op een verdieping en een tweede bezorgpoging zijn extra diensten die je alleen krijgt als de afzender ze heeft bijgeboekt.

**Gemiste levering en afhalen.** De vrachtbrochure van Swiss Post noemt geen afhaalpunten of pakketkluizen, en een tweede levering valt onder de extra diensten hierboven. De brochure vermeldt niet hoe lang goederen op een depot wachten of hoe je zelf kunt verzetten. Vraag het aan de winkel die het transport boekte.

## Swiss Post Cargo zending vertraagd, kwijt of beschadigd

De winkel is de opdrachtgever van Swiss Post Cargo: zakelijke klanten bestellen deze dienst, dus vertraging, schade of een ontbrekend colli meld je eerst bij de winkel. Meld schade direct bij de winkel, inclusief foto's.

**Dagenlang geen update.** Alleen `Data entry POST` betekent dat de goederen mogelijk nog bij de afzender staan: vraag de winkel wanneer ze worden overgedragen. [Tracking die niet bijwerkt](guide:tracking-not-updating) (Engels) licht de gebruikelijke oorzaken toe.

**Afgeleverd maar niet aanwezig.** Kijk in de tracker naar de regels `SIGNATURE` en `IMAGE` en neem daarna contact op met de winkel. [Bezorgd maar niet ontvangen](guide:delivered-not-received) (Engels) beschrijft de volgende stappen.

**Verdachte berichten.** De phishingpagina van Swiss Post meldt dat valse berichten uit naam van Swiss Post binnenkomen via sms, e-mail, WhatsApp, websites, telefoontjes en sociale media, vaak over pakketten, kosten, tracking of persoonsgegevens.

## Contactgegevens van Swiss Post Cargo

Swiss Post Cargo werkt in opdracht van de winkel, dus begin daar: de winkel heeft het vervoer geboekt. De vrachtbrochure van Swiss Post (april 2022) vermeldt één contactadres voor zakelijke klanten zonder telefoonnummer: Customer Service Transport CH, Lagerstrasse 12, 5606 Dintikon, sales.ls4@post.ch.

Voor brieven en pakketten van Swiss Post (niet Cargo) is de lijn +41 848 888 888 (CHF 0,08 per minuut vanaf een Zwitserse vaste lijn), maandag tot en met vrijdag van 07:30 tot 18:00 uur en zaterdag van 08:00 tot 12:00 uur Zwitserse tijd.

## Een Swiss Post Cargo zending volgen met Peek

Een 20-cijferige streepjescode die begint met `00` hoort bij de verzender, niet bij één vaste vervoerder, dus [Peek](/) toont "Checking tracking services…" en wijst Swiss Post Cargo pas aan als Swiss Post Cargo het nummer kent. Een referentie als `PL-48291736` wordt opgevraagd bij Swiss Post Cargo en TNT Italy, die hetzelfde formaat kunnen gebruiken. Voor iedere andere referentie van 6 tot 40 letters en cijfers kies je Swiss Post Cargo met "Change", of plak je een Track & Trace-link van tt.swisspost-cargo.com of apv.swisspost-cargo.com (`/public/trackandtrace/` gevolgd door het nummer). Een Zwitsers pakketnummer in zo'n link wordt gevolgd als Swiss Post.

Peek toont de status en de geschiedenis met de plaats van elke scan op een kaart en het aflevertijdstip. Tijden worden gelezen op Zürichtijd en weergegeven in de tijdzone van je apparaat. De teksten blijven in het Engels; `Loaded for Delivery` telt als uit voor bezorging. De handtekening- en fotoregels worden samengevoegd bij de afleverscan, zonder de foto zelf. Peek laat je naam en adres weg en toont geen verwachte bezorgdag, gewicht of afhaalpunt.

Een referentie die door meerdere zendingen wordt gedeeld is alleen uitleesbaar zolang slechts één ervan scans heeft uit de laatste 60 dagen; anders vindt Swiss Post Cargo niets (Peek toont "Waiting for Swiss Post Cargo" en vraagt na twee dagen het nummer te controleren), gebruik dan de streepjescode. Zolang je de zending open hebt, of op de bezorgdag met meldingen aan, controleert Peek tot elke 10 minuten — of elke 2 minuten vanaf 6:00 uur Zwitserse tijd op de dag dat Swiss Post Cargo de zending als uit voor bezorging scant — zolang de eigen tracker antwoordt, en kan meldingen sturen bij elke scan, belangrijke stappen of alleen op de bezorgdag. Anders, en na twee dagen zonder nieuws (12 uur zolang de status "Label created" of "Received by carrier" blijft), controleert Peek elk uur.

## Veelgestelde vragen over Swiss Post Cargo tracking

### Wat is Swiss Post Cargo?

Het is de merknaam die de goederenlogistiek van de Zwitserse Post sinds 1 maart 2025 gebruikt (inclusief Swiss Post Cargo CH AG, volledig eigendom van Swiss Post) voor zakelijk vrachtvervoer van enkele pallets tot volle vrachtwagens in Zwitserland, Liechtenstein en het buitenland.

### Is PostLogistics hetzelfde als Swiss Post Cargo?

Ja. PostLogistics opereert nu als Swiss Post Cargo; de oude tracker `tracking.postlogistics.ch` opent niet meer en is vervangen door `tt.swisspost-cargo.com`. Peek blijft oudere PostLogistics-referenties zoals `48291736-542` volgen.

### Wat betekenen "Data entry POST" en "Loaded for Delivery"?

`Data entry POST` betekent dat Swiss Post Cargo de elektronische gegevens van de afzender ontving maar de vracht nog niet in handen heeft (`POST` hoort bij de tekst en betekent geen fysieke beweging). `Loaded for Delivery` betekent dat de zending vandaag op de bezorgvrachtwagen staat; `IMAGE` en `SIGNATURE` horen bij `DELIVERED SCANNED` als afleverbewijs.

### Hoe lang duurt levering met Swiss Post Cargo en waar levert de chauffeur?

De vrachtbrochure van Swiss Post vermeldt levering op de volgende werkdag vóór 17:00 uur in bereikbare delen van Zwitserland en Liechtenstein, en op de tweede werkdag vóór 17:00 uur in berggebieden (Wallis, Ticino, Graubünden). Standaard wordt geleverd tot de eerste deur op de begane grond, het perron of de stoeprand, waarbij de ontvanger lost tenzij de afzender extra diensten boekte.

### Hoe neem ik contact op met Swiss Post Cargo over levering, sms of schade?

Begin bij de winkel die het transport heeft geboekt — vooral bij schade (meld direct met foto's), voor het verzetten van levering of het controleren van een sms. De vrachtbrochure noemt het zakelijke adres sales.ls4@post.ch (Dintikon); de pakket- en brievenlijn van Swiss Post is +41 848 888 888.

:::sources
- [Swiss Post: Transport freight brochure](https://www.post.ch/en/-/media/post/gk/dokumente/stueckgut.pdf) – enkele colli tot volle vrachtwagens, bestellingen via Swiss Post Cargo Web voor zakelijke klanten, volgende werkdag, berggebieden, begane grond, lossen, handtekening, extra diensten van de afzender inclusief tweede levering, regelmatige locatie-updates, adres en e-mail Dintikon
- [Swiss Post: Logistics market](https://site.post.ch/en/about-us/company/strategy-and-value/business-activities/logistics) – Swiss Post Cargo is de goederenlogistiek van Swiss Post voor bedrijven in Zwitserland en het buitenland
- [Swiss Post: Group companies](https://site.post.ch/en/about-us/company/governance/organisation/subsidiaries) – Swiss Post Cargo CH AG, 100% Swiss Post
- [Swiss Post: Help and contact](https://www.post.ch/en/help-and-contact) – telefoonlijn brieven en pakketten, tarief, openingstijden
- [Swiss Post: Current fraud attempts](https://site.post.ch/en/about-us/security/phishing-and-fraud/current-fraud-attempts) – valse berichten uit naam van Swiss Post
- [VerkehrsRundschau: Schweizerische Post mit neuem Marktauftritt](https://www.verkehrsrundschau.de/nachrichten/transport-logistik/logistik-schweizerische-post-fuehrt-neue-marke-ein-3626449) – merknaam Swiss Post Cargo vanaf 1 maart 2025, PostLogistics bij de eerste groepsmaatschappijen
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – wat Peek uitleest: streepjescodes en referenties, nummerformaten, Engelse scanteksten, Track & Trace-adressen, PostLogistics nu onder de naam Swiss Post Cargo
:::
