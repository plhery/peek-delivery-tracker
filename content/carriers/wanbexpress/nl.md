---
title: WanbExpress pakket volgen: WNB…YQ-nummer en statussen
description: Volg een WanbExpress-pakket (Wanb Express / WANB) uit China: WNB…YQ-nummer, betekenis van scans, lokale bezorgers, EU-douanekosten en hulp bij vertraging.
slug: wanbexpress-pakket-volgen
published: 2026-11-27
updated: 2026-11-27
---

WanbExpress (ook bekend als Wanb Express, WanBang Express of WANB) is een Chinese grensoverschrijdende vervoerder die webshoppakketten vanuit China via export, vlucht en inklaring vervoert en daarna overdraagt aan een lokale pakketdienst voor de eindbezorging — bijvoorbeeld iMile in Duitsland, InPost in het VK of Poste Italiane in Italië. Plak je 17 tekens lange `WNB…YQ`-vrachtbriefnummer in het vak hierboven of kijk op `tracking.wanbexpress.com` (met Google reCAPTCHA-controle) om alle scans en eventueel het lokale trackingnummer van de bezorgdienst te zien.

## Trackingnummer van WanbExpress: hoe het eruitziet

De vrachtbriefnummers van WanbExpress bestaan uit `WNB`, twee letters, tien cijfers en `YQ`, in totaal 17 tekens. Voorbeelden met verzonnen cijfers:

| Nummer | Wat het is |
| --- | --- |
| `WNBKG4829105736YQ` | `WNB`, twee letters, tien cijfers en `YQ`: een geldig WanbExpress-nummer. |
| `WNBKG482910573YQ` | Slechts negen cijfers: past niet bij WanbExpress. Controleer of er een cijfer ontbreekt. |
| `JSEKG4829105736YQ` | Eindigt ook op `YQ`, maar begint met `JSE`: een nummer van [JS Express](carrier:js-express). |
| `JXCKG4829105736YQ` | Begint met `JXC`: een nummer van [Juxi Express](carrier:juxi-express). |

Meerdere Chinese expediteurs eindigen op `YQ` met elk hun eigen voorvoegsel: `WNB` kenmerkt WanbExpress. Spaties, streepjes en kleine letters maken niets uit wanneer je het nummer in Peek plakt.

**Waar je het vindt.** De webwinkel geeft het nummer in de verzendmail of op je bestelpagina. Vind je het niet terug, vraag het dan aan de verkoper.

**Een trackinglink.** Een link naar de tracker van WanbExpress die eindigt op `?trackingNumbers=` en een nummer opent dat specifieke pakket. Het `WNB`-nummer is het deel na het is-teken: kopieer dat stuk in Peek.

**Een tweede nummer.** De tracker van WanbExpress kan ook het trackingnummer van de lokale bezorgdienst tonen. Dat nummer hoort bij die andere vervoerder: volg het op de eigen site van die bezorgdienst of voeg het in Peek toe als apart pakket.

## Betekenis van de statussen bij WanbExpress

De scans van WanbExpress zijn in het Engels, telkens met een tijdstip en soms een plaatsnaam. Omdat de app van Peek niet in het Nederlands is vertaald, staan de eigen fasen van Peek ook in het Engels: "Label created" (label aangemaakt), "Received by carrier" (ontvangen door vervoerder), "In transit" (onderweg), "Out for delivery" (onderweg voor bezorging), "Delivery attempted" (bezorgpoging), "Ready for pickup" (klaar om af te halen) en "Delivered" (bezorgd). De belangrijkste scans in de volgorde waarin de code van Peek ze leest:

| WanbExpress zegt | Wat dit voor jou betekent | Peek toont |
| --- | --- | --- |
| `Shipping Order Created.`, `Parcel Data Received` | De webwinkel heeft de zending aangemeld en de gegevens gestuurd. WanbExpress heeft het pakket fysiek nog niet. | "Label created" |
| `Parcel received at Shenzhen Sorting Centre.` | Het sorteercentrum in Shenzhen, China, heeft het pakket ontvangen: de eerste echte scan. | "Received by carrier" |
| `Parcel processed at Shenzhen Sorting Centre.`, `Parcel departure in Shenzhen Sorting Centre.` | Gesorteerd in Shenzhen en daarna vertrokken uit dat centrum. | "In transit" |
| `Customs declaration completed` | Er is een douaneaangifte voor het pakket ingediend. De tekst zegt niet van welk land en dit is nog niet de vrijgave bij invoer (dat is `Custom clearance completed`). | "In transit" |
| `Parcel departure in Transit Facility, Parcel Departure` | Het pakket heeft een doorvoerfaciliteit verlaten. | "In transit" |
| `Parcel is leaving Airport, Parcel Leaving Port` | Vertrekt met de vlucht richting het land van bestemming. | "In transit" |
| `Parcel arrives at Airport, Parcel Arrival` | Geland in het land van bestemming of in de buurt daarvan. Daarna volgt de douane. | "In transit" |
| `Custom clearance completed` | De invoerdouane heeft het pakket vrijgegeven. | "In transit" |
| `Parcel received by in JFK01.` | WanbExpress boekt dit als ontvangst voor het laatste traject; de regel noemt niet welke partij het ontving. | "In transit" |
| `Your package is being prepared by the sender and will be handed over to us soon`, `Your parcel will soon be handed over to us! It is being prepared by the sender.`, `Order information received. We're expecting your parcel to arrive with us.` | Een melding van een bezorgpartner in eigen woorden zonder WanbExpress-code: het pakket is nog niet aan die partner overgedragen. | "Label created", of een latere fase als een andere scan het pakket al verder bracht |
| `In transit along Poste Italiane’s logistic network` | Onderweg via het netwerk van de lokale bezorger, hier [Poste Italiane](carrier:poste-italiane). | "In transit" |
| `Parcel is out for delivery.` | De bezorger is onderweg om te leveren. | "Out for delivery" |
| `Consignee absent, Consignee could not be found.` | De lokale bezorger trof niemand aan op het adres. | "Delivery attempted" |
| `Placed in Parcelshop/Parcel locker` | Na een gemiste bezorging is het pakket afgegeven bij een pakketpunt of pakketkluis om op te halen. | "Ready for pickup" |
| `Parcel has been delivered.(Front door)` | Bezorgd; `(Front door)` geeft aan waar het is neergelegd. | "Delivered" |

**Scans van de lokale bezorgdienst.** Na de vlucht geeft de tracker van WanbExpress de eigen scans van de bezorgdienst door in diens eigen bewoordingen, vaak zonder WanbExpress-code. Bij een zending naar Duitsland toont de geschiedenis van WanbExpress bijvoorbeeld [iMile](carrier:imile), met elke regel voorafgegaan door het centrum of depot tussen 【haakjes】:

| iMile zegt | Wat dit voor jou betekent | Peek toont |
| --- | --- | --- |
| `Received.` | Het centrum van iMile in het bestemmingsland heeft het pakket ontvangen. | "In transit" |
| `Arrived.` | Aangekomen op het lokale bezorgdepot (delivery station) voor jouw regio. | "In transit" |
| `Dispatch task assigned to DA.` | Er is een bezorger (DA, delivery associate) toegewezen, wat uren vóór vertrek kan gebeuren. | "In transit" |
| `Shipment out for delivery.` | Onderweg met de bezorger. | "Out for delivery" |
| `<Route not completed>`, `Back To DS.` | De rit eindigde zonder levering en het pakket keerde terug naar het bezorgdepot (DS). | "Delivery attempted" |
| `Scheduled to <2026-11-02> .` | De nieuw ingeplande bezorgdag nadat het pakket terugkwam op het depot. | Blijft "Delivery attempted" |

Meldingen van andere partners komen op dezelfde manier door. Op zendingen naar de Verenigde Staten zijn `Parcel received by Transit Facility.`, `In Processing at Transit Facility.`, `In Transit to Next Facility.` en `Parcel received by local Facility.` de ontvangst, verwerking en doorvoer bij het lokale bezorgnetwerk; ook `It is currently in transit to the next facility`, `The package is being transferred to the next facility` en `Shipment arrived to sorting centre / Shipment in the sorting centre` zijn verplaatsingen bij de partner na de vlucht — Peek toont alle zeven als "In transit". Bij zendingen naar het VK kunnen regels van [InPost](carrier:inpost) al verschijnen voordat het sorteercentrum van WanbExpress heeft gescand: `Your parcel is on its way to InPost. Please check back for updates` ("Label created") en `We have your parcel. Please check back for updates` ("Received by carrier"). `We'll apply your My Account preferences to this parcel delivery` is slechts een melding dat accountvoorkeuren gelden, geen scan die de fase wijzigt. Dat geldt ook voor `Consignee Contacted`: een bezorger van de partner die je belde, gelogd in dezelfde minuut als een mislukte poging.

**Tijden.** Elke scan bevat een tijdstip met UTC-offset. Peek leest ze zo in en toont alle tijden in de tijdzone van jouw apparaat, nieuwste bovenaan. Meer over stilteperiodes en overdrachten bij pakketten uit China lees je in de gids over [pakketten uit China volgen](guide:tracking-from-china) (in het Engels).

## Wie bezorgt WanbExpress-pakketten in Nederland en België

De scans van WanbExpress volgen het pakket vanuit China door de douane naar een lokale pakketdienst, die de bezorging uitvoert. De tracker van WanbExpress kan die lokale vervoerder noemen, zijn trackingnummer tonen en soms een bezorgfoto laten zien (Peek laat bezorgfoto's weg). In de code van Peek komen onder meer iMile (Duitsland), Poste Italiane (Italië) en InPost (VK) voor; voor Nederland en België noemt de code geen vaste enkele partner. Zodra Peek kan vaststellen van welke vervoerder het doorgestuurde bezorgnummer is, toont Peek "Delivery with …" en volgt het die vervoerder mee; kijk anders op de tracker van WanbExpress of vraag het aan de verkoper.

**Bezorgpogingen en afhalen.** Het aantal pogingen, of er een briefje wordt achtergelaten en hoe lang een pakket op een afhaalpunt blijft liggen, hangt volledig af van de lokale bezorgdienst. Peek vond daarover geen eigen regels van WanbExpress. Zodra je de lokale vervoerder en het nummer kent, bekijk je diens tracking en vraag je daar om een nieuwe afspraak.

**Douanekosten in de EU.** Voor online aankopen van buiten de EU in pakketten tot € 150 geldt sinds 1 juli 2026 een vast invoerrecht van € 3 per productcategorie. Sinds 1 november 2026 komt daar een EU-afhandelingsvergoeding (Union handling fee) van € 2 per productcategorie bij, ongeacht de waarde van het pakket. Zie ook de gids over [pakketten bij de douane](guide:customs) (in het Engels).

## WanbExpress-pakket komt niet aan of tracking staat stil

**Nummer niet gevonden.** Kent de tracker van WanbExpress het nummer nog niet, dan toont Peek "Waiting for WanbExpress" en blijft het controleren. Weet WanbExpress het na twee dagen nog steeds niet en antwoordt geen enkele andere dienst, dan toont Peek "After two days, the carrier still doesn’t know this number. Check it, choose another carrier or delete the parcel." Controleer het nummer teken voor teken met het bericht van de verkoper: `WNB`, twee letters, tien cijfers, `YQ`.

**Blijft hangen op `Shipping Order Created.` of `Parcel Data Received`.** De winkel heeft de zending aangemeld, maar WanbExpress heeft het pakket nog niet gescand. Vraag de webshop wanneer het pakket fysiek is overgedragen.

**Staat stil na `Parcel arrives at Airport, Parcel Arrival`.** Het pakket wacht waarschijnlijk op de invoerdouane. Wordt je gevraagd invoerkosten te betalen, zie de gids over [pakketten bij de douane](guide:customs) (in het Engels); blijft het dagenlang staan, meld het aan de verkoper.

**Niets meer na `Custom clearance completed`.** De lokale bezorgdienst heeft het pakket of neemt het binnenkort over, en diens eigen tracking kan voorlopen op die van WanbExpress. Zoek naam en nummer van de lokale bezorger op de tracker van WanbExpress of vraag de verkoper.

**Bezorgpoging gemist.** Na een regel zoals `<Route not completed>` bepaalt de lokale bezorgdienst wanneer er opnieuw wordt bezorgd of waar het pakket wacht. Neem contact op met die bezorger, niet met WanbExpress.

**Bezorgd gemeld, maar niets ontvangen.** Controleer op de tracker van WanbExpress of er een bezorgfoto staat en neem daarna contact op met de lokale bezorgdienst en de verkoper. Meer in de gids over [bezorgd maar niet ontvangen](guide:delivered-not-received) (in het Engels).

**Verloren of retour.** De verkoper heeft het pakket bij WanbExpress geboekt; vraag dus de verkoper of het verkoopplatform om terugbetaling of een nieuwe zending.

## Contact en klantenservice van WanbExpress

Peek kon de bedrijfswebsite van WanbExpress niet openen, dus op deze pagina staan geen telefoonnummer of e-mailadres voor WanbExpress. De verkoper is de opdrachtgever van WanbExpress en je eerste aanspreekpunt voor je bestelling (of het platform als de verkoper niet reageert). Voor de daadwerkelijke bezorging wend je je tot de lokale pakketdienst.

## Een WanbExpress-pakket volgen met Peek

[Peek](/) herkent een WanbExpress-nummer direct aan zijn vorm — `WNB`, twee letters, tien cijfers en `YQ`, die geen enkele andere vervoerder in de catalogus gebruikt — en vraagt de tracker van WanbExpress op met alleen dat nummer, zonder account, postcode of telefoonnummer.

De tracker van WanbExpress antwoordt pas nadat Google reCAPTCHA controleert of een mens de aanvraag doet, daarom opent Peek de pagina via een browser. Lukt dat toch niet, dan schakelt Peek over op trackingdiensten en toont hun geschiedenis in hun eigen woorden, wat soms achterloopt op WanbExpress zelf.

Uit de tracker van WanbExpress toont Peek de status, elke scan met plaatsnaam en het land van herkomst en bestemming uit het record. Als dat record het pakket als onderweg, in bezorging of bezorgd markeert, neemt Peek dat over, ook als de laatste regel minder zegt. Peek toont geen verwachte bezorgdatum.

Peek laat bezorgfoto's en het adres van de ontvanger weg. Wanneer de tracker van WanbExpress het nummer van de lokale bezorger doorgeeft en Peek de vervoerder met zekerheid kan herkennen via de catalogus, toont Peek "Delivery with …" en volgt het dat pakket daar verder; anders voeg je dat lokale nummer zelf als extra pakket toe in Peek, dat meer dan 4.500 vervoerders ondersteunt.

Zolang je het pakket open hebt, of op de bezorgdag met meldingen aan, en de tracker van WanbExpress antwoordt, controleert Peek tot elke 10 minuten, en op de dag dat het pakket in bezorging gaat vanaf 6:00 uur lokale tijd tot elke 2 minuten (elke 5 minuten wanneer een browser nodig is); anders elk uur. Via trackingdiensten gebeurt dat elk uur, elke 3 uur na een rustige dag en tot elke 15 minuten op de bezorgdag terwijl je kijkt of meldingen aanstaan. Na twee dagen zonder nieuws (of 12 uur bij "Label created" of "Received by carrier") controleert Peek elk uur. Meldingen stel je in op "Every scan", "Important steps" (bezorgrit, gemiste poging en bezorging — niet de douaneregels van WanbExpress, die Peek als "In transit" telt) of "Delivery only". Peek kan geen contact opnemen met WanbExpress of de verkoper voor je.

## Veelgestelde vragen over WanbExpress

### Wat is WanbExpress (Wanb Express / WANB)?

Een Chinese grensoverschrijdende pakketvervoerder, ook bekend als Wanb Express, WanBang Express of WANB. In de code van Peek beginnen de scans in een sorteercentrum in Shenzhen, volgen ze de vlucht en douane en gaan ze verder met de scans van de lokale bezorgdienst in het bestemmingsland.

### Waar kan ik een WanbExpress-pakket met WNB…YQ volgen?

Plak het volledige nummer van 17 tekens (`WNB`, twee letters, tien cijfers, `YQ`) in het vak hierboven of op `tracking.wanbexpress.com`, dat Google reCAPTCHA gebruikt en ook naam en nummer van de lokale bezorger kan tonen.

### Wat betekent "Customs declaration completed" bij WanbExpress?

Dat de douaneaangifte voor het pakket is afgerond — dit is nog niet de invoervrijgave zelf, die later verschijnt als `Custom clearance completed`. Peek toont beide regels als "In transit".

### Wie bezorgt WanbExpress-pakketten in Europa?

Een lokale pakketdienst waarvan de tracker van WanbExpress de naam en een tweede trackingnummer kan tonen. De code van Peek toont onder meer iMile in Duitsland, InPost in het VK en Poste Italiane in Italië; voor Nederland en België controleer je het lokale nummer op de tracker van WanbExpress of vraag je het de verkoper.

### Met wie neem ik contact op als mijn WanbExpress-pakket vastzit of kwijt is?

Neem eerst contact op met de verkoper of het verkoopplatform, want de winkel boekte de zending bij WanbExpress. Voor bezorgproblemen op het laatste traject neem je contact op met de lokale pakketdienst zodra je diens trackingnummer hebt.

:::sources
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – namen, vorm `WNB…YQ`, tracker met reCAPTCHA en link voor één pakket, scanbewoordingen en fasen, doorgestuurde scans van iMile, InPost, Poste Italiane en Amerikaanse partners, tijden met UTC-offset, wat de tracker toont en wat Peek bewaart of weglaat, en terugval op trackingdiensten
- [Europese Commissie: EU customs reform (in het Engels)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – € 3 douanerecht per productcategorie tot € 150 sinds 1 juli 2026, € 2 EU-afhandelingsvergoeding sinds 1 november 2026
- [Europese Commissie: Guidance on the EUR 3 customs duty and Union handling fee (PDF, in het Engels)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – € 2 per productcategorie ongeacht de waarde
:::
