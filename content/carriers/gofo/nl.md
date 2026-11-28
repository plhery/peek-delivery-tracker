---
title: GOFO Express-tracking: statussen, foto's en ondersteuning
description: Volg een GOFO Express-pakket in de VS: hoe een GOFO-trackingnummer eruitziet, wat de statussen betekenen, gemiste bezorgingen, bezorgfoto's en klantenservice.
slug: gofo-express-tracking
published: 2026-11-28
updated: 2026-11-28
---

GOFO Express is een Amerikaanse pakketvervoerder: het haalt pakketten op bij het magazijn van de afzender en vervoert ze via zijn hubs naar het bezorgstation dat jouw regio bedient. Plak het nummer in het vak hierboven om je pakket te volgen; ook de eigen site van GOFO heeft een tracker. Het nummer komt van de winkel die je bestelling verzond, meestal in de verzendmail of op je bestelpagina. Niets te vinden? Zie [hoe je een trackingnummer vindt (in het Engels)](guide:find-tracking-number).

## GOFO-trackingnummer: hoe het eruitziet

De Amerikaanse nummers van GOFO komen voor in vier vormen. De vormen die Peek aan GOFO koppelt, met verzonnen cijfers:

| Vorm (verzonnen) | Wat het is |
| --- | --- |
| `GFUS48291057364829` | GFUS gevolgd door 14 cijfers: het Amerikaanse vrachtbriefnummer van GOFO. Een letter O direct na GFUS leest Peek als een nul |
| `GF4829105736482` | GF gevolgd door 13 cijfers: een oudere reeks die GOFO nog steeds volgt |
| `CR482910573648` | CR gevolgd door 12 cijfers: nog een oudere reeks |
| `MGP4829105736482` | MGP gevolgd door 13 cijfers: een trackingnummer dat sommige pakketten naast hun GFUS-vrachtbriefnummer dragen |

Sommige pakketten hebben zowel een GFUS-vrachtbriefnummer als een CR-trackingnummer of een MGP-trackingnummer (MGP en 13 cijfers), en met elk daarvan vind je hetzelfde pakket. Op DHgate-pakketten die GOFO voor [DHLink](carrier:dhlink) bezorgt, is het trackingnummer het eigen nummer van DHLink, `FC` en 13 cijfers, en de tracker van GOFO vindt het pakket ook daarmee.

**Nummers die erop lijken.** GOFO beheert netwerken in andere landen, elk met eigen nummers: een `GFCA`-nummer met 14 cijfers hoort bij [GOFO Canada](carrier:gofo-ca), `GFFR`- en `CIFR`-nummers bij [GOFO France](carrier:gofo-fr), `GFIT`- en `CIIT`-nummers bij [GOFO Italy](carrier:gofo-it), `GFES`- en `CIES`-nummers bij GOFO Spain, en `GFNL`- en `CINL`-nummers bij GOFO Netherlands. Peek herkent ze direct en volgt ze via het netwerk van dat land. Een `YT`-nummer is van [YunExpress](carrier:yunexpress), ook wanneer GOFO de eindbezorging doet: de Amerikaanse tracker van GOFO accepteert hetzelfde nummer, en de tracking van YunExpress toont ook de scans van GOFO.

## Trackingstatussen van GOFO uitgelegd

De scans van GOFO zijn in het Engels. Dit zijn de statussen die een pakket kan tegenkomen, grofweg in volgorde; niet elk pakket krijgt ze allemaal:

| Status van GOFO | Wat het voor jou betekent | Peek toont |
| --- | --- | --- |
| `Shipping Label Created` | De afzender heeft het label aangemaakt. GOFO heeft het pakket nog niet. | "Label created" |
| `Pick Up Start` | GOFO is vertrokken om het pakket bij de afzender op te halen, maar heeft het nog niet. | "Label created" |
| `Pickup Exception, GOFO has not Received the Package from the Warehouse yet.` | GOFO heeft het pakket nog niet ontvangen uit het magazijn van de afzender. | "Label created" |
| `Pick Up Success` | GOFO heeft het pakket bij de afzender opgehaald. | "Received by carrier" |
| `Arrived at GOFO Regional Origin Hub` | Aangekomen bij de eerste hub van GOFO. | "Received by carrier" |
| `Arrived at Sorting Center` | Aangekomen bij een sorteercentrum, vóór de regionale hub. | "In transit" |
| `Arrived at GOFO Regional Hub` | Aangekomen bij een hub onderweg naar jou. | "In transit" |
| `Processing at GOFO Facility` | Wordt gesorteerd in een vestiging van GOFO. | "In transit" |
| `Departed GOFO Regional Hub` | Vertrokken uit die hub. | "In transit" |
| `Loaded on Vehicle` | Geladen in een voertuig voor het volgende traject. | "In transit" |
| `In Transit to Next Facility` | Onderweg tussen vestigingen van GOFO. | "In transit" |
| `Arrived at GOFO Delivery Station` | Op het lokale station dat jouw regio bedient. GOFO zegt dat bezorging meestal binnen 1–2 kalenderdagen volgt. | "In transit" |
| `Departed GOFO Delivery Station` | Vertrokken vanaf het station. | "In transit" |
| `Out for Delivery` | Vandaag mee met de bezorger. | "Out for delivery" |
| `Delivered` | De bezorger heeft het pakket bezorgd. | "Delivered" |
| `Delivery Exception, Business Closed` | De bezorger kon niet bezorgen omdat het bedrijf gesloten was. | "Delivery attempted" |
| `Incorrect/Insufficient Address, Additional Details Required for Delivery.` | Het adres is onjuist of onvolledig, soms de dag na een gemiste bezorging genoteerd: het pakket wacht tot iemand de ontbrekende gegevens aanvult. | "Needs attention" |
| `Label Cancelled` | Het label is geannuleerd: er komt geen pakket onder dit nummer. | "Needs attention" |

De bezorgscan van GOFO kan een detail na de komma toevoegen, en de trackingpagina toont ook een geschatte bezorgdatum.

## GOFO-bezorging: foto's en gemiste bezorgingen

**Bezorgfoto's.** Zodra je de naam van de ontvanger invult die bij de bestelling is gebruikt, toont de trackingpagina van GOFO het afleverbewijs, meestal met een foto van de plek waar het pakket is achtergelaten. Peek laat de foto's weg.

**Gemiste bezorging.** `Delivery Exception` betekent dat de bezorger heeft geprobeerd te bezorgen maar dat niet lukte. De reden volgt na de komma en verschilt; `Business Closed` is er één van. Peek toont het pakket dan als "Delivery attempted" en citeert de regel van GOFO onder "What GOFO Express says". GOFO zegt dat het meestal binnen 24 uur opnieuw probeert. Een adresprobleem, zoals een ontbrekend appartementsnummer, wat de tracking van GOFO kan tonen als `Incorrect/Insufficient Address…` (zie de tabel hierboven), kan het pakket terugsturen naar het lokale magazijn: meld dit aan de winkel die het verzond. Je kunt een pakket niet zelf bij GOFO afhalen.

## GOFO-pakket vertraagd, niet ontvangen of niet besteld

**Geen beweging.** Als alleen `Shipping Label Created`, `Pick Up Start` of `Pickup Exception…` zichtbaar is, heeft GOFO het pakket nog niet: vraag de winkel of het al is verzonden. Na een dag meldt Peek dat de afzender het pakket nog niet heeft overgedragen. Als er vier dagen zonder nieuwe scan voorbijgaan voordat het pakket onderweg voor bezorging is, toont Peek een melding met een link "Get help from GOFO Express" naar de klantenservicepagina van GOFO. Meer over [tracking die niet bijwerkt (in het Engels)](guide:tracking-not-updating).

**Je rechten bij een late bestelling.** Onder de Mail, Internet, or Telephone Order Merchandise Rule van de FTC moet een verkoper verzenden binnen de termijn die hij opgeeft, of binnen 30 dagen na je bestelling als hij geen termijn noemt. Bij vertraging moet de verkoper je informeren en laten kiezen tussen akkoord gaan met de vertraging of annuleren met volledige terugbetaling. Als hij niet verzendt, is hij je een volledige terugbetaling verschuldigd, niet slechts een cadeaubon of winkeltegoed. De eerste stap volgens de FTC: neem contact op met de verkoper.

**Bezorgd maar niet ontvangen.** Bekijk de bezorgfoto op de trackingpagina van GOFO, kijk rond je brievenbus en vraag buren of de conciërge van je gebouw. Meld het daarna aan GOFO, informeer de winkel en zie [bezorgd maar niet ontvangen (in het Engels)](guide:delivered-not-received). Als je de bestelling nooit hebt ontvangen en het bedrijf het bedrag niet terugboekt, raadt de FTC aan de afschrijving te betwisten bij je kaartuitgever. Voor een creditcard moet het bezwaar schriftelijk worden ingediend, binnen 60 dagen nadat het eerste overzicht met de afschrijving aan jou is verstuurd.

**Een pakket ontvangen dat je niet hebt besteld.** Volgens Amerikaans recht hoef je niet te betalen voor goederen die je niet hebt besteld en hoef je ze ook niet terug te sturen: de FTC zegt dat je ze als gratis geschenk mag houden.

**Kwijt.** Neem contact op met de winkel: GOFO zegt dat de afzender namens jou een onderzoek bij GOFO kan openen. Vraag de winkel ook om terugbetaling of een nieuwe zending; meer in [verloren pakket (in het Engels)](guide:lost-parcel).

**Teruggestuurd.** GOFO stuurt een pakket terug als het onderweg beschadigd is geraakt, of als het adres fout, onvolledig of niet bediend is, zoals een militair adres. Eenmaal op de terugweg kan het niet worden tegengehouden: vraag de winkel om een nieuwe zending. Meer in [terug naar afzender (in het Engels)](guide:returned-to-sender).

## Klantenservice van GOFO Express

Begin voor alles over de bestelling zelf bij de winkel die deze verzond. Voor de bezorging vermeldde de klantenservicepagina van GOFO — dezelfde pagina die de link "Get help from GOFO Express" in Peek opent — op 4 november 2026 het volgende (tijden in Amerikaanse Eastern Time):

- **Telefoon, hulplijn voor ontvangers:** +1 (949) 688-6032, 9:00 tot 21:00 van maandag tot en met vrijdag, 11:00 tot 19:00 op zaterdag en zondag.
- **WhatsApp:** +1 (787) 469-5811, 9:00 tot 21:00 van maandag tot en met vrijdag, 11:00 tot 20:00 op zaterdag en zondag.
- **E-mail:** cs@mail.gofo.com, of het contactformulier en de chat op dezelfde pagina. Het formulier vraagt om het vrachtbriefnummer, houd je GOFO-nummer dus bij de hand.

## Pakketten van GOFO Express volgen met Peek

[Peek](/) noemt GOFO Express direct bij een `GFUS`-nummer ("Detected carrier"). Bij een `GF`-, `CR`- of `MGP`-nummer vraagt Peek aan GOFO of het pakket daar bekend is en noemt het GOFO Express zodra dat zo is. Spaties in het nummer maken niet uit. Totdat GOFO het nummer kent, blijft Peek controleren; een pakket onder GOFO Express (een `GFUS`-nummer, of een pakket waarvoor je GOFO hebt gekozen) toont ondertussen "Waiting for GOFO Express". Als GOFO het nummer van zo'n pakket twee dagen later nog steeds niet kent en geen andere dienst heeft geantwoord, toont Peek "After two days, the carrier still doesn’t know this number. Check it, choose another carrier or delete the parcel." en controleert Peek het vanaf dan dagelijks, en wekelijks zodra er een week sinds het toevoegen is verstreken. AliExpress / Cainiao volgt sommige AliExpress-pakketten onder het `GFUS`-nummer van GOFO: wanneer GOFO twee dagen lang alleen het label heeft getoond, vraagt Peek ook Cainiao om het traject vóór GOFO. Je kunt GOFO Express ook kiezen met "Change", of een Amerikaanse GOFO-trackinglink met `searchID=` en het nummer erin plakken.

Peek leest de Amerikaanse tracking van GOFO tot eens per 30 minuten, ook op de dag van bezorging, en wacht een uur na een mislukte controle. Na twee dagen zonder nieuws (12 uur zolang het pakket nog "Label created" of "Received by carrier" toont) controleert Peek elk uur. Het toont de status, elke scan met de plaats en staat die GOFO daarvoor geeft, die plaatsen op een kaart, het exacte tijdstip van elke scan, het bezorgtijdstip en de bestemming. Peek verwijdert de ondersteuningsregel van GOFO uit elke scan; een bezorging verschijnt als "Delivered", zonder het detail na de komma, en andere scans staan er zoals GOFO ze schrijft. Peek toont de verwachte bezorgdag van GOFO totdat het pakket onderweg voor bezorging, bezorgd of teruggestuurd is, en laat bezorgfoto's, coördinaten en gewicht weg; open dus de pagina van GOFO voor de foto's. Peek kan je een melding sturen bij elke scan, alleen belangrijke stappen of alleen de bezorgdag. Het kan niet namens jou contact opnemen met GOFO of de winkel.

## Vragen over GOFO-tracking

### Is GOFO Express betrouwbaar?

Ja. GOFO Express is een Amerikaanse pakketvervoerder met eigen tracking, die pakketten volgt vanaf het magazijn van de afzender via de hubs van GOFO tot de bezorgstations. GOFO zegt dat zijn Amerikaanse netwerk meer dan 12.000 ZIP-codes dekt en 75% van de bevolking bereikt. GOFO runt ook netwerken in Canada, Frankrijk, Italië, Spanje en Nederland, elk met eigen nummers.

### Wat betekent Shipping Label Created bij GOFO?

De afzender heeft het verzendlabel aangemaakt en GOFO heeft het pakket nog niet. Daarna volgen `Pick Up Start`, wanneer GOFO het gaat ophalen, en `Pick Up Success`, zodra het pakket is opgehaald.

### Waarom werkt mijn GOFO-trackingnummer niet?

Controleer of het een Amerikaans GOFO-nummer is: GFUS, GF, CR of MGP. `GFCA`-, `GFFR`-, `GFIT`-, `GFES`- en `GFNL`-nummers horen bij GOFO-netwerken in andere landen, en `YT`-nummers bij YunExpress. Een DHgate-pakket dat GOFO bezorgt kan op de tracker van GOFO ook worden opgezocht met het `FC`-nummer van DHLink. Als de winkel je het nummer net heeft gegeven, kent GOFO het mogelijk nog niet: Peek blijft controleren. Als GOFO het twee dagen later nog steeds niet kent en het pakket onder GOFO Express staat, vraagt Peek je het te controleren, een andere vervoerder te kiezen of het pakket te verwijderen. Als het `Label Cancelled` toont, komt er niets onder dat nummer; vraag de winkel dan om het juiste nummer.

### Waarom heeft mijn AliExpress-bestelling een GOFO-nummer?

Cainiao, dat AliExpress-bestellingen volgt, volgt sommige daarvan onder het GFUS-nummer van GOFO wanneer GOFO ze in de VS bezorgt. Bij een GFUS-nummer vraagt Peek het aan GOFO, en ook aan Cainiao zolang GOFO alleen het label toont. Meer in [AliExpress-bestellingen (in het Engels)](guide:aliexpress-orders).

:::sources
- [GOFO: Customer Service](https://www.gofo.com/us/customer-service) – telefoon, WhatsApp, e-mail en openingstijden, formulier en chat, geen zelf ophalen; FAQ: afleverbewijs, herbezorging, adresproblemen, 1–2 dagen vanaf het station, onderzoek door de afzender, retouren
- [GOFO: Home](https://www.gofo.com/us/) – meer dan 12.000 ZIP-codes, 75% van de bevolking
- [FTC: What To Do if You're Billed for Things You Never Got, or You Get Unordered Products](https://consumer.ftc.gov/articles/what-do-if-youre-billed-things-you-never-got-or-you-get-unordered-products) – verzendtermijnen, vertragingen en terugbetalingen, kaartbetwistingen, ongevraagde goederen
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – GOFO-nummervormen en netwerken in andere landen, GFUS-, CR- en MGP-nummers, FC-nummers van DHLink, YunExpress-nummers, tracker en hulppagina van GOFO, scanformuleringen, hun betekenis en wat Peek voor elk toont, wat de pagina van GOFO toont en wat Peek leest, Cainiao
:::
