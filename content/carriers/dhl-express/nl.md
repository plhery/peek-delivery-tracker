---
title: DHL Express pakket volgen: 10-cijferige waybill en status
description: Volg een DHL Express pakket: waar je het 10-cijferige waybill-nummer vindt, betekenis van DHL Express-scans, douanekosten, gemiste bezorging en hulp.
slug: dhl-express-pakket-volgen
published: 2026-11-27
updated: 2026-11-27
---

Plak een DHL Express waybill-nummer of een MyDHL+-trackinglink in het zoekvak hierboven om je pakket te volgen. De eigen tracking van DHL staat op de website (MyDHL+) en in de mobiele app van DHL Express.

## DHL Express trackingnummer: waar je het waybill-nummer vindt

DHL Express noemt het trackingnummer het waybill-nummer (vrachtbriefnummer). Kijk in de verzendbevestiging van de winkel of op je bestelpagina; een trackinglink van MyDHL+ bevat het nummer ook, achter `id=`. Nog steeds niets gevonden? Zie [waar je een trackingnummer vindt](guide:find-tracking-number) (Engels).

| Formaat (verzonnen) | Wat het is |
| --- | --- |
| `4829173650` | De waybill: 10 cijfers, het nummer dat de volledige zending volgt. Het laatste cijfer is een controlecijfer. |
| `JD014628194829173650` | Een stuknummer (piece number): `JD01` en 16 cijfers, afgedrukt als `(J)JD…` onder de onderste streepjescode op het label. Elk pakket in een zending heeft er een, en het leidt terug naar de waybill. |

Tien cijfers passen ook bij [Mondial Relay](carrier:mondial-relay), [Relais Colis](carrier:relais-colis), Estafeta, [Packeta](carrier:packeta), [DHL eCommerce Iberia](carrier:dhl-ecommerce-es) en [DHL Paket](carrier:dhl), dus controleer welke vervoerder in de e-mail van de winkel staat.

DHL hergebruikt waybill-nummers: een nummer kan oude scans van een eerdere zending bevatten, die Peek negeert.

## Wat de trackingstatussen van DHL Express betekenen

Dit zijn de Engelse scanteksten die Peek van DHL Express uitleest, grofweg in de volgorde waarin een pakket ze doorloopt, met de bijbehorende status in Peek. Veel meldingen eindigen met de vestiging, bijvoorbeeld `Processed at` gevolgd door een plaats en land.

| Scan van DHL Express | Peek toont | Wat het betekent |
| --- | --- | --- |
| `Shipment information received`, `Supplementary information has been received and merged with applicable shipment data` | Label created | De afzender heeft de zendingsgegevens aan DHL doorgegeven. |
| `Shipment picked up`, `Shipment accepted` | Received by carrier | DHL Express heeft het pakket in handen. |
| `Processed at`, `Arrived at DHL Sort Facility`, `Shipment has departed from a DHL facility`, `Shipment is in transit to destination` | In transit | Het pakket beweegt door het netwerk van DHL. |
| `Scheduled to depart on the next planned movement` | In transit | Wacht op een locatie op de eerstvolgende vrachtwagen of vlucht. |
| `Customs clearance status updated. Note - The Customs clearance process` …, `Processed for clearance at`, `Clearance event` | At customs | Het pakket doorloopt de douane (zie onder). |
| `On hold awaiting for payment of shipment related fees` | At customs | Er staan invoerrechten of kosten open, en DHL houdt het pakket vast tot die betaald zijn. Er is niets mis met de zending. |
| `Payment is received and recorded for shipment related fees` | At customs | De rechten of kosten zijn betaald; het pakket blijft bij de douane tot de inklaring klaar is. |
| `Clearance processing complete at` | In transit | De douane is klaar en het pakket gaat weer verder. |
| `Arrived at DHL Delivery Facility` | In transit | Aangekomen bij de DHL-vestiging die bezorgt. |
| `Shipment is out with courier for delivery` | Out for delivery | Een koerier is ermee onderweg naar jou. |
| `Delivery attempted but no response at Consignee address`, `Delivery attempt could not be completed` | Delivery attempted | De bezorging kon niet doorgaan. "Consignee" ben jij, de ontvanger. |
| `Shipment is scheduled for delivery` | In transit | Er is een bezorgronde ingepland, bijvoorbeeld na een gemiste poging. |
| `Awaiting collection by the consignee` | Ready for pickup | Het pakket wacht op een DHL-locatie tot je het ophaalt. |
| `Delivered` | Delivered | Bezorgd. |
| `Shipment is on hold` | Needs attention | DHL heeft de zending voorlopig tegengehouden. |
| `Shipment arrived at incorrect facility. It will be sent to the correct destination` | Needs attention | Verkeerd gesorteerd; gaat met vertraging door naar de juiste bestemming. |
| `Returned to shipper` | Returned to sender | Onderweg terug naar de afzender. |

## Gemiste bezorging van DHL Express

Na een mislukte poging toont de tracking `Delivery attempted but no response at Consignee address` of `Delivery attempt could not be completed`. Een latere `Shipment is scheduled for delivery` betekent dat er een nieuwe poging is gepland; `Awaiting collection by the consignee` betekent dat het pakket voor je klaarligt op een DHL-locatie. Neem voor het verzetten van de bezorging contact op met DHL Express via de website of app: Peek kan geen bezorging wijzigen. Meer in [gemiste bezorgingen](guide:missed-delivery) en [niet-afgehaalde pakketten](guide:uncollected-parcel) (Engels).

## DHL Express pakket bij de douane

Voor een pakket van buiten het VK, of voor Ierland van buiten de EU, kunnen invoerkosten verschuldigd zijn. De tracking toont `Customs clearance status updated…`, `Processed for clearance at` of `Clearance event` tijdens de douanebehandeling, `Payment is received and recorded for shipment related fees` na betaling, en `Clearance processing complete at` zodra de douane het vrijgeeft.

- **Verenigd Koninkrijk:** volgens GOV.UK neemt de koerier contact op met een rekening voor btw, invoerrechten of afhandelingskosten en bewaart het pakket normaal ongeveer 3 weken. Zonder betaling gaat het terug naar de afzender.
- **Ierland:** sinds 1 juli 2026 geldt voor de meeste pakketten tot €150 van buiten de EU €3 invoerrecht per productsoort. Heeft de winkel dat niet bij het afrekenen geïnd, dan vraagt de koerier volgens Revenue om betaling vóór aflevering. De koerier kan eigen administratiekosten rekenen, dus controleer dat bedrag bij de koerier.

Meer in [pakketten bij de douane](guide:customs) (Engels).

## DHL Express pakket vertraagd, kwijt of als bezorgd gemeld

Als de tracking stilstaat of `Shipment is on hold` toont, zie [tracking die niet bijwerkt](guide:tracking-not-updating) (Engels).

Woon je in Groot-Brittannië of Ierland en heb je bij een winkel gekocht, dan is de winkel verantwoordelijk dat het pakket bij jou aankomt, ook wanneer DHL Express het vervoert:

- **Engeland, Schotland en Wales:** volgens Citizens Advice moet de verkoper achter de koerier aan, niet jij. Komt het artikel niet binnen 30 dagen na aankoop aan, dan kun je annuleren en je geld terugvragen. Is het op een niet-afgesproken plek achtergelaten en verdwenen, dan moet de verkoper dat oplossen.
- **Ierland:** volgens de CCPC moet de bestelling zonder afgesproken datum binnen 30 dagen na aankoop aankomen. Gebeurt dat niet, spreek dan een nieuwe bezorgdatum af met de winkel; verstrijkt ook die, dan heb je recht op volledige terugbetaling. Een tijdens vervoer beschadigd of verloren pakket moet worden vervangen, en de verkoper blijft verantwoordelijk totdat jij of iemand die jij aanwijst het pakket in handen heeft.

Staat er `Delivered` maar heb je niets ontvangen? De trackingpagina van DHL kan de handtekening tonen, die Peek niet opslaat. Controleer de tracking op de website van DHL en neem daarna contact op met de winkel. Meer in [bezorgd maar niet ontvangen](guide:delivered-not-received) en [verloren pakketten](guide:lost-parcel) (Engels).

## DHL Express pakketten volgen met Peek

[Peek](/) herkent een 10-cijferige waybill die het controlecijfer van DHL Express haalt, zet DHL Express bovenaan en vraagt het meteen op samen met andere vervoerders die hetzelfde formaat gebruiken, zoals [ACS Courier](carrier:acs-courier), Geniki Taxydromiki (deze twee alleen bij cijfers zonder spaties of streepjes) en DHL eCommerce Iberia, en toont wie het pakket kent. ACS en Geniki delen het controlecijfer, dus als een van hen het nummer ook kent maak je zelf de keuze. Een 10-cijferig nummer dat het controlecijfer niet haalt wordt niet aan DHL Express voorgelegd: kies het dan via "Change". `JD01`-stuknummers en `JJD0146`-labels (`JJD014628194829173650`) worden direct als DHL Express herkend en teruggeleid naar hun waybill. Links van mydhl.express.dhl werken ook. Plak bij een dhl.com-link liever het waybill-nummer zelf; komt een pakket onder "DHL" terecht, schakel dan via "Change" over naar "DHL Express".

Peek leest DHL Express uit via de dienst achter de mobiele app, of opent anders de trackingpagina van dhl.com in een browser op de eigen server (wat trager is en geweigerd kan worden; dan vullen trackingdiensten aan). Peek controleert tot elke 10 minuten, of elke 2 tot 5 minuten (5 wanneer dhl.com in een browser geopend moet worden) vanaf 6:00 uur lokale tijd op de dag dat DHL het pakket als uit voor bezorging scant, zolang de eigen tracking van DHL antwoordt (via trackingdiensten elke 15 minuten), en kan meldingen sturen bij elke scan, bij belangrijke stappen of alleen op de bezorgdag. Na twee dagen zonder nieuws (12 uur zolang het pakket nog "Label created" of "Received by carrier" toont) controleert Peek elk uur.

Peek toont de status, elke scan met vestiging op een kaart en het bestemmingsland. Scantijden worden gelezen op de lokale klok van de plaats van de vestiging of, als dat niet volstaat, van de omliggende scans. Scans blijven in het Engels van DHL, behalve `Delivered`, `Shipment picked up` en `Shipment information received`, die in Peek's eigen woorden verschijnen. Peek laat de handtekening weg, negeert oude scans van een hergebruikte waybill en toont geen gewicht of Service Point. De verwachte bezorgdag verschijnt wanneer Peek de pagina van dhl.com leest; de dienst achter de DHL-app geeft die niet. Peek kan geen invoerrechten betalen of een bezorging wijzigen.

## Veelgestelde vragen over DHL Express tracking

### Is het DHL waybill-nummer hetzelfde als het trackingnummer?

Ja. Bij DHL Express volgt het 10-cijferige waybill-nummer de hele zending; een `JD01`-stuknummer hoort bij één afzonderlijk pakket en verwijst terug naar de waybill.

### Is DHL hetzelfde als DHL Express?

Niet in Peek: DHL Express is een eigen vervoerder, los van [DHL Paket](carrier:dhl) en de DHL eCommerce-vervoerders, elk met eigen nummers en tracking. Een pakket zonder 10-cijferige waybill of `JD01`-stuknummer hoort meestal bij een van die andere diensten.

### Waarom is mijn DHL waybill-nummer ongeldig?

Vaak gaat het om een nummer van een andere DHL-dienst: waybills van DHL Express hebben precies 10 cijfers en het laatste is een controlecijfer, dus één typefout maakt het meestal ongeldig. Een nieuwe waybill toont bovendien vaak alleen `Shipment information received` totdat DHL het pakket heeft opgehaald.

### Kan ik DHL Express volgen zonder waybill-nummer?

Niet in Peek: daarvoor is het 10-cijferige waybill-nummer of een `JD01`-stuknummer van het label nodig. Vraag het waybill-nummer op bij de winkel of afzender.

### Is een sms van DHL Express waarin om betaling wordt gevraagd oplichting?

Dat kan. Volgens Citizens Advice is een bericht waarin je moet betalen om een pakket te ontvangen of opnieuw in te plannen meestal oplichting. Tik niet op de link, maar zoek de waybill zelf op de website van DHL of in Peek op. In het VK kun je de sms gratis doorsturen naar 7726. In de VS adviseert de FTC om contact op te nemen met de vervoerder via een website of telefoonnummer waarvan je zeker weet dat het echt is. Meer in [nepmeldingen over bezorging](guide:delivery-scam-texts) (Engels).

:::sources
- [Citizens Advice: If something you ordered hasn't arrived](https://www.citizensadvice.org.uk/consumer/somethings-gone-wrong-with-a-purchase/if-something-you-ordered-hasnt-been-delivered/) – verantwoordelijkheid verkoper, 30 dagen, afgesproken plek, nepberichten (Engeland, Schotland en Wales)
- [GOV.UK: Tax and customs for goods sent from abroad](https://www.gov.uk/goods-sent-from-abroad/tax-and-duty) – rekening van de koerier, ongeveer 3 weken, retour afzender
- [GOV.UK: Report internet scams and phishing](https://www.gov.uk/report-suspicious-emails-websites-phishing) – nep-sms'jes doorsturen naar 7726
- [CCPC: Your consumer rights if your order is not delivered](https://www.ccpc.ie/consumer-advice/consumer-rights/buying-goods/non-delivery-of-goods) – 30 dagen, nieuwe datum, terugbetaling, verantwoordelijk tot ontvangst (Ierland)
- [Revenue: Buying goods from outside the EU](https://www.revenue.ie/en/customs/individuals/buying-online-personal/outside-eu.aspx) – €3 per productsoort sinds 1 juli 2026, vooraf aan koerier te betalen
- [Revenue: Buying goods online for personal use](https://www.revenue.ie/en/customs/individuals/buying-online-personal/index.aspx) – administratiekosten van koeriers, die kunnen verschillen
- [FTC: Fake shipping notification emails and text messages](https://consumer.ftc.gov/consumer-alerts/2023/12/fake-shipping-notification-emails-text-messages-what-you-need-know-holiday-season) – contact opnemen via een bekende website of bekend nummer
- [Universal Parcel Scraper: DHL Express](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/dhl-express) – formaten waybill en stuknummer, controlecijfer, 10-cijferige lookalikes, hergebruikte waybills, scanteksten van DHL Express en Peek-status, handtekening bij DHL, wat Peek uitleest
:::
