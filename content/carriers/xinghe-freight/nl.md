---
title: Xinghe Freight pakket volgen: GZXH…YQ-nummer en statussen
description: Volg een Xinghe Freight-pakket (星和货运) uit China: GZXH…YQ-nummer, waarom het via GZYL loopt, vooraf geplande Delivered-stappen, EU-douanekosten en contact.
slug: xinghe-freight-pakket-volgen
published: 2026-11-27
updated: 2026-11-27
---

Xinghe Freight (星和货运, 广州星和货运代理有限公司) is een expediteur uit Guangzhou in China, opgericht in 2014, die zee-, lucht- en wegvracht, scheepsagentuur en douaneaangiften verzorgt. Om je pakket te volgen plak je het trackingnummer van 16 tekens — `GZXH`, tien cijfers en `YQ` — in het vak hierboven. De website van Xinghe Freight stuurt trackingvragen door naar de onversleutelde HTTP-tracker van [GZYL](carrier:gzyl), waarop stappen — tot en met `Delivered` — vooraf ingepland kunnen zijn in plaats van echte scans. Peek leest die tracker voor je uit, scheidt voorspelde stappen van echte scans en schakelt zo nodig trackingdiensten in.

## Trackingnummer van Xinghe Freight: hoe het eruitziet

De nummers die Peek herkent als van Xinghe Freight bestaan uit `GZXH`, tien cijfers en `YQ`, samen 16 tekens. Laat je per ongeluk de laatste `Q` weg (`GZXH`, tien cijfers en `Y`, 15 tekens), dan herkent Peek het nummer toch en zet het de `Q` terug voordat de tracker wordt geraadpleegd. Voorbeelden met verzonnen cijfers:

| Nummer | Wat het is |
| --- | --- |
| `GZXH4829105736YQ` | `GZXH`, tien cijfers en `YQ`: de standaardindeling van Xinghe Freight. Peek kiest direct Xinghe Freight. |
| `GZXH4829105736Y` | `GZXH`, tien cijfers en `Y`: de laatste `Q` ontbreekt, maar Peek vult die automatisch aan. |
| `GZXH482910573YQ` | Maar negen cijfers: geen geldig Xinghe-formaat. Peek weigert het nummer meteen zodat je kunt nakijken of er een cijfer ontbreekt. |
| `YL4829105736YQ` | `YL`, tien cijfers en `YQ`: het vrachtbriefformaat van [GZYL](carrier:gzyl). Dezelfde tracker, een andere expediteur. |
| `JXCAA4829105736YQ` | Eindigt ook op `YQ`, zoals bij veel Chinese expediteurs, maar begint met `JXC`: een nummer van [Juxi Express](carrier:juxi-express). |

Spaties, streepjes en kleine letters maken niets uit wanneer je het nummer in Peek plakt.

**Waar je het vindt.** De verkoper boekt de zending bij Xinghe Freight en geeft het nummer aan jou door in de verzendmail of op je bestelpagina. Vind je het niet, vraag het dan aan de verkoper.

## Betekenis van de statussen bij Xinghe Freight

De tracking van Xinghe Freight draait op het platform van GZYL, dus de stappen staan in de Engelse bewoordingen van GZYL, spelfouten inbegrepen. Omdat de app van Peek niet in het Nederlands is vertaald, zie je ook de eigen fasen van Peek in het Engels: "Label created" (label aangemaakt), "Received by carrier" (ontvangen door vervoerder), "In transit" (onderweg), "At customs" (bij de douane) en "Delivered" (bezorgd). De stappen in de volgorde waarin een pakket ze meestal krijgt:

| De tracker zegt | Wat dit voor jou betekent | Peek toont |
| --- | --- | --- |
| `Order information received` | De verkoper heeft de ordergegevens doorgestuurd. Niemand heeft het pakket fysiek in handen. | "Label created" |
| `Arrive at the cross border receiving point` | De expediteur heeft het pakket ontvangen: de eerste echte scan. | "Received by carrier" |
| `Goods leave the operation center` | Het pakket heeft het operationele centrum verlaten. | "In transit" |
| `Arrive at the international airport` | Op de internationale luchthaven, wachtend op de vlucht. | "In transit" |
| `Departing from the airport` | Het vliegtuig is vertrokken. | "In transit" |
| `Item arrives at destination airport` | Geland in het land van bestemming. | "In transit" |
| `local carrier Agent Import customs clearance` | Een agent in jouw land klaart het pakket in bij de invoerdouane. | "At customs" |
| `The import customs keep it for inspection` | De douane in jouw land houdt het pakket vast voor controle. | "At customs" |
| `Customs clearance completed` | De inklaring is afgerond en het pakket gaat weer verder. | "In transit" |
| `Internationaliy Package received at processing center` | Een verwerkingscentrum in jouw land heeft het ingeklaarde pakket ontvangen. Dit is geen nieuwe ophaling bij de afzender. | "In transit" |
| `Leave the email processing center` | Het pakket heeft dat verwerkingscentrum verlaten. | "In transit" |
| `Arrival sorting, distribution center` | Aangekomen in een sorteer- of distributiecentrum in jouw land, met de plaatsnaam erachter. | "In transit" |
| `Leaving the sorting center` | Vertrekt uit een sorteercentrum, met de plaatsnaam erachter. | "In transit" |
| `Package is on the way` | Onderweg binnen het land van bestemming, met een plaatsnaam. Dat betekent nog niet dat een bezorger onderweg is naar je deur. | "In transit" |
| `Delivered` | Bezorgmelding met plaatsnaam. Kan een planning zijn in plaats van een scan: zie hieronder. | "Delivered", met een melding over het vaste schema, tenzij Peek de stap achterhoudt (zie hieronder) |

**Via Hongkong.** Soms zie je `香港处理中心已收寄【Hong Kong】`: een postale inname op het verwerkingscentrum in Hongkong, die de tracker in het Chinees met de stad tussen haakjes doorgeeft. Peek toont de regel zoals die is onder "Received by carrier".

**Een regel in het Chinees.** Bij een bestelling zonder scans zet het trackingplatform er zelf `货物电子信息已经收到` neer (elektronische goedereninformatie ontvangen). De verkoper heeft de bestelling aangemaakt, maar niemand heeft het pakket al gescand. Peek toont die regel in het Chinees onder "Label created".

**Tijden.** Peek leest elk tijdstip op de tracker als Pekingtijd (`UTC+8`), ook bij stappen in Nederland of België, en rekent het om naar de tijdzone van je apparaat. In de winter is 20:00 in Peking gelijk aan 13:00 Nederlandse en Belgische tijd (`UTC+1`), en in de zomer 14:00 (`UTC+2`).

**Vooraf geschreven stappen.** Op dit trackingplatform kunnen expediteurs de hele tijdlijn van een pakket vooruit invullen — van inname tot de laatste stap in jouw land — waarna de tracker elke regel toont zodra het tijdstip aanbreekt. Zo'n stap is een planning, geen scan van je pakket. Op de tracker van GZYL eindigt die planning met een `Delivered` dat het platform 's avonds in een batch toevoegt op de datum die het toont: gedateerd de dag na de laatste geplande stap, tijdens kantooruren in Peking, ongeacht je woonland. Peek houdt deze stappen achter (zie hieronder), maar kopieën op andere trackingsites kunnen ze wel tonen, dus `Delivered` daar bewijst niet dat je pakket is aangekomen.

## Wie bezorgt Xinghe Freight-pakketten in Nederland en België

Peek weet niet welke pakketdienst Xinghe Freight-pakketten in Nederland of België bezorgt. De stappen in het land van bestemming noemen geen bedrijf, en de site van Xinghe Freight vermeldt niets over pakketten voor online kopers. De enige bezorgdienst waaraan Peek een Xinghe-pakket automatisch kan overdragen is [Swiss Post](carrier:swiss-post), in Zwitserland en Liechtenstein, wanneer het record van Xinghe die noemt.

De website van Xinghe Freight geeft geen levertijden, bezorgpogingen of afhaalpunten: die regels horen bij het lokale bezorgbedrijf. Vraag de verkoper welke vervoerder je bestelling bezorgt en om het bijbehorende trackingnummer.

**Douanekosten in de EU.** Sinds 1 juli 2026 geldt voor online aankopen van buiten de EU in pakketten tot € 150 een vast invoerrecht van € 3 per productcategorie. Sinds 1 november 2026 komt daar een EU-afhandelingsvergoeding (Union handling fee) van € 2 per productcategorie bij, ongeacht de waarde. Eén categorie omvat goederen in hetzelfde pakket met dezelfde tariefindeling, omschrijving en (waar vereist) oorsprong, ongeacht het aantal stuks. Meer lees je in de gids over [pakketten bij de douane](guide:customs) (in het Engels).

## Xinghe Freight-pakket komt niet aan of tracking staat stil

**Alleen `Order information received` of `货物电子信息已经收到`.** De verkoper heeft de order aangemaakt, maar niemand heeft het pakket gescand. Komt er na een paar dagen niets bij, vraag de verkoper dan of het echt is verzonden.

**"Waiting for Xinghe Freight".** Peek heeft nog geen echte scan voor je nummer: de tracker van GZYL kent het niet of bevat alleen een vooraf opgestelde planning (die kan eindigen met `Delivered`), en andere trackingdiensten hebben ook niets. Peek blijft controleren. Voor een onbekend nummer geeft de tracker van GZYL één resultaat met het nummer als referentie en alle andere velden leeg; Peek ziet dat als niet gevonden, toont "Waiting for Xinghe Freight" en na twee dagen "Check the tracking number". Een vooraf geschreven planning telt wel als bewijs dat Xinghe de order kent, dus dan blijft "Waiting for Xinghe Freight" staan zonder verzoek om het nummer te controleren. Vergelijk het nummer toch teken voor teken met het bericht van de verkoper.

**Vast bij de douane.** Als je gevraagd wordt invoerkosten te betalen, zie de gids over [pakketten bij de douane](guide:customs) (in het Engels). Blijft het dagenlang hangen, laat het de verkoper weten.

**De tracker of een andere site toont meer stappen dan Peek.** De extra regels, inclusief `Delivered`, kunnen een vooraf gemaakte planning zijn die Peek achterhoudt. Houd de geschatte levertijd van de winkel aan en neem contact op met de verkoper zodra die is verstreken. Meer in de gids over [pakketten uit China volgen](guide:tracking-from-china) (in het Engels).

**`Delivered`, maar niets ontvangen.** Die melding kan onderdeel zijn van een vooraf ingevuld schema. Behalve wanneer Swiss Post bezorgt, toont Peek onder "Delivered": "Xinghe Freight updates on a fixed schedule, with no signature. If nothing came, ask the seller." Laat de verkoper weten dat het pakket niet is aangekomen en vraag welke lokale vervoerder bezorgt en onder welk nummer. Meer in de gids over [bezorgd maar niet ontvangen](guide:delivered-not-received) (in het Engels).

**Verloren of retour.** De verkoper heeft de zending bij Xinghe Freight geboekt, dus vraag de verkoper of het verkoopplatform om terugbetaling of een nieuwe zending.

## Contact en klantenservice van Xinghe Freight

De Chinese website van Xinghe Freight (`xinghe-express.cn`) vermeldt:

- **Telefoon (24/7):** 19924483103 (+86 199 2448 3103 vanuit het buitenland), opgegeven als 24 uur per dag, elke dag bereikbaar.
- **E-mail:** 3975605292@qq.com, voor zakelijke aanvragen en offertes.
- **Adres:** Guangzhou, provincie Guangdong, zonder straatnaam (voor het volledige adres vraagt de site eerst contact op te nemen).

De site is alleen in het Chinees en biedt niets voor buitenlandse ontvangers: het contactformulier opent enkel een e-mail naar dat adres. De verkoper is de klant van Xinghe, dus neem eerst contact op met de verkoper en met het platform als die niet reageert.

## Een Xinghe Freight-pakket volgen met Peek

[Peek](/) herkent een `GZXH…YQ`-nummer (en ook een afgekapt `GZXH…Y`-nummer) meteen als Xinghe Freight met "Detected carrier". Gaf de verkoper een nummer met een andere vorm, plak het dan toch: Peek volgt meer dan 4.500 vervoerders. Om Xinghe Freight handmatig te kiezen druk je op "Change" en zoek je op Xinghe Freight (of `星和货运`), of gebruik je "Change carrier" bij een al toegevoegd pakket.

Peek vraagt direct de tracker van GZYL op, net als het zoekvak op de site van Xinghe. Je ziet de status, elke stap met plaatsnaam en het bestemmingsland wanneer dat in het record staat. Tijden worden gelezen in Pekingtijd en getoond in de tijdzone van je apparaat ("Times are in your time zone:" onderaan de geschiedenis). Peek laat namen, adres, postcodes, gewicht, betaalgegevens, handtekening en afleverbewijs weg en toont geen verwachte bezorgdatum omdat de tracker die niet geeft.

Als de tracker je nummer niet kent, raadpleegt Peek trackingdiensten en toont hun kopie van de stappen. Zolang geen van beide stappen heeft, staat er "Waiting for Xinghe Freight" en na twee dagen "Check the tracking number".

Peek houdt elke stap achter die meer dan 12 uur vóór het getoonde tijdstip is geschreven, elke stap die in dezelfde seconde is geschreven als een stap die meer dan een halfuur vóór zijn tijdstip is geschreven (behalve de orderregistratie) en elke stap met een tijdstip in de toekomst in Pekingtijd op het moment van controle. Bij een record met vooraf geschreven stappen houdt Peek ook `Delivered` achter, zelfs als dat op dezelfde dag is geschreven. Peek baseert zich op de bewaarde stappen, niet op de samenvattingsstatus, en filtert ook kopieën van die geplande stappen uit trackingdiensten, inclusief `Delivered`. Wanneer de tracker het nummer niet kent of onbereikbaar is, kan Peek echter geen geplande stap van een trackingdienst onderscheiden van een echte scan. Zodra Peek voorspellingen achterhoudt, opent de geschiedenis tot aan bezorging of retour met "Other sites may show more steps, but they’re only predictions.", en laat "Show them" die regels in het grijs zien met de dag van de tracker ("Predicted for Mon 12 Oct"). Zo'n stap telt nooit als scan: hij verandert geen status en stuurt geen melding.

Op Swiss Post na volgt Peek het pakket niet door naar het lokale bezorgbedrijf: krijg je dat lokale nummer van de verkoper, voeg het dan als apart pakket toe. Zolang je het pakket geopend houdt, of op de bezorgdag met meldingen aan, controleert Peek tot elke 10 minuten; anders en wanneer het stil blijft elk uur. Je kunt meldingen kiezen voor "Every scan", "Important steps" (de twee douanestappen en `Delivered`, niet vluchten of sortering) of "Delivery only". Peek kan geen contact opnemen met Xinghe Freight, GZYL of de verkoper voor je.

## Veelgestelde vragen over Xinghe Freight

### Wat is Xinghe Freight (星和货运)?

广州星和货运代理有限公司 (Guangzhou Xinghe Freight Forwarding), een expediteur in Guangzhou, China, opgericht in 2014 voor zee-, lucht- en wegvracht, scheepsagentuur en douaneaangiften. De pakketnummers tellen 16 tekens: `GZXH`, tien cijfers en `YQ`.

### Waarom toont mijn Xinghe Freight-pakket de tracking van GZYL?

Het zoekvak op de site van Xinghe Freight stuurt vragen rechtstreeks naar de tracker van GZYL, waardoor de tekst van de stappen gelijk is aan die van GZYL. Peek houdt het pakket onder Xinghe Freight en leest die tracker voor je uit.

### Wie bezorgt Xinghe Freight in Nederland en België?

Noch de website van Xinghe noch de tracker noemt een lokale bezorgpartner (Peek draagt alleen automatisch over aan Swiss Post in Zwitserland en Liechtenstein wanneer die wordt genoemd). Vraag de verkoper welk lokaal bedrijf je pakket bezorgt en om het bijbehorende trackingnummer.

### Waarom toont Peek minder stappen of geen tracking voor mijn GZXH-nummer?

Ofwel staat het nummer nog niet in de tracker, ofwel bevat het record vooraf geschreven stappen — inclusief een gepland `Delivered` — die Peek achterhoudt achter "Show them" in plaats van ze als scans te tellen. Controleer het nummer met de mail van de verkoper en vraag de verkoper na een paar dagen om uitleg.

### Hoe neem ik contact op met Xinghe Freight?

Neem eerst contact op met de verkoper of het platform, want de verkoper heeft de zending geboekt. De Chinese site (`xinghe-express.cn`) noemt de 24-uurslijn 19924483103 (+86 199 2448 3103 vanuit het buitenland) en het zakelijke e-mailadres 3975605292@qq.com, maar heeft geen speciaal kanaal voor buitenlandse ontvangers.

:::sources
- [Xinghe Freight: 广州星和货运代理有限公司 (startpagina, in het Chinees)](http://xinghe-express.cn/) – bedrijfsnaam, expeditiediensten, zoekvak naar de tracker van GZYL, telefoonnummer en e-mail
- [Xinghe Freight: 关于我们 (over ons, in het Chinees)](http://xinghe-express.cn/about.html) – opgericht in 2014, zee-, lucht- en wegvracht, scheepsagentuur en douaneaangiften
- [Xinghe Freight: 产品服务 (diensten, in het Chinees)](http://xinghe-express.cn/services.html) – aangeboden diensten, niets over pakketten voor webshopklanten
- [Xinghe Freight: 联系我们 (contact, in het Chinees)](http://xinghe-express.cn/contact.html) – 7×24-lijn, zakelijk e-mailadres, alleen Guangzhou als adres, contactformulier dat e-mail opent
- [Europese Commissie: EU Customs Reform (in het Engels)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – € 3 douanerecht per productcategorie tot € 150 sinds 1 juli 2026, € 2 EU-afhandelingsvergoeding sinds 1 november 2026
- [Europese Commissie: Guidance on the EUR 3 customs duty and the Union handling fee (PDF, in het Engels)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – € 2 vergoeding ongeacht de waarde en definitie van productcategorie
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – namen, formaten `GZXH…YQ` en afgekapt `GZXH…Y`, weigering van foutieve lengte, lezen via GZYL's tracker, stapteksten, inname Hongkong, Chinese regel, Pekingtijd, vooraf geschreven stappen, trackingdiensten als fallback, overdracht aan Swiss Post en wat Peek toont, achterhoudt en weglaat
:::
