---
title: UPS pakket volgen: 1Z-nummers, statussen en Access Points
description: Volg een UPS pakket: hoe een 1Z-nummer eruitziet, wat Exception en andere UPS-statussen betekenen, Access Points, invoerkosten en rechten bij verlies.
slug: ups-pakket-volgen
published: 2026-11-27
updated: 2026-11-27
---

Trackingnummers van UPS (United Parcel Service) beginnen meestal met `1Z`. Plak het nummer of een UPS-trackinglink in het zoekvak hierboven om je pakket te volgen; de eigen trackingpagina van UPS staat op ups.com.

## UPS trackingnummer: waar je het vindt en hoe het eruitziet

De winkel die het pakket verstuurt geeft je het nummer, meestal in de verzendbevestiging per e-mail of op je bestelpagina. Een trackinglink van ups.com bevat het nummer ook, achter `tracknum=`. Nog steeds niets gevonden? Zie [waar je nog meer een trackingnummer vindt](guide:find-tracking-number) (Engels).

| Formaat (verzonnen) | Wat het is |
| --- | --- |
| `1Z4829173654829177` | Het pakketnummer van UPS: `1Z` gevolgd door 16 letters en cijfers, 18 tekens in totaal. Het laatste cijfer is een controlecijfer berekend uit de 15 tekens ervoor. |

## Wat UPS trackingstatussen betekenen, inclusief Exception

De trackingpagina van UPS toont een voortgangsbalk (`Label Created`, `We Have Your Package`, `On the Way`, `Out for Delivery`, `Delivered`) met daaronder de afzonderlijke scans. Hieronder staan de Engelse scanteksten die Peek van UPS uitleest, grofweg in de volgorde waarin een pakket ze doorloopt, met de bijbehorende status in Peek.

| Scan van UPS | Peek toont | Wat het betekent |
| --- | --- | --- |
| `Shipper created a label, UPS has not received the package yet.` | Label created | De afzender heeft een label aangemaakt. UPS heeft het pakket nog niet in handen. |
| `Drop-Off`, `Pickup Scan` | Received by carrier | UPS heeft het pakket: de afzender leverde het in bij een afgiftepunt of UPS haalde het op. Ook de eerste herkomstscan van UPS, soms met de tekst `Arrived at Facility`, valt hieronder. |
| `Arrived at Facility`, `Departed from Facility`, `Processing at UPS Facility` | In transit | Het pakket beweegt door het netwerk van UPS. `Export Scan` en `Import Scan` markeren een grensovergang. |
| `Your package is prepared for clearance.` | At customs | Het pakket wordt voorbereid voor inklaring bij de douane. |
| `Your package is pending release from a Government Agency. We'll notify the receiver or sender if information is needed.` | At customs | Een overheidsinstantie houdt het pakket vast tussen de export- en importscan. UPS neemt contact op met jou of de afzender als er gegevens nodig zijn. |
| `Your package has been released by a government agency.` | In transit | De overheidsinstantie heeft het pakket vrijgegeven. |
| `Duties or taxes are due on this package.` | At customs | Je moet invoerrechten of belastingen betalen voordat UPS bezorgt (zie onder). |
| `Loaded on Delivery Vehicle`, `Out For Delivery Today` | Out for delivery | Een chauffeur is ermee onderweg. |
| `Delivery instructions have been provided by the receiver.` | Unchanged | De ontvanger heeft bezorginstructies aan UPS doorgegeven. Dit is een melding, geen scan: Peek behoudt de vorige status, zoals "Out for delivery". |
| `The receiver was not available at the time of the final delivery attempt.` | Delivery attempted | De laatste bezorgpoging is mislukt. |
| `Delivered to UPS Access Point™`, `UPS Access Point™ possession` | Ready for pickup | Het pakket ligt klaar bij een UPS Access Point. |
| `DELIVERED` | Delivered | Bezorgd, of door jou opgehaald bij een Access Point. |
| `Return to Sender` | Returned to sender | Het pakket gaat terug naar de afzender. |

`Exception` is de algemene uitzonderingsstatus van UPS en kan ook gewoon een korte vertraging zijn. Lees altijd de scan eronder: `We're sorry this package may experience a temporary delay.` is slechts een vertraging, terwijl `The company or receiver name is incorrect. This will delay delivery.` betekent dat de bezorging wacht op jouw gegevens (Peek toont dan "Needs attention"). Peek baseert zich op de nieuwste scan, niet op de algemene status.

## Gemiste UPS-bezorging en Access Points

Wanneer de chauffeur niet kan bezorgen, meldt de scan dat de ontvanger niet aanwezig was of het bedrijf gesloten was, en doet UPS een nieuwe poging. Na de laatste poging laat een volgende scan zien wat er daarna gebeurt: UPS houdt het pakket vast, brengt het naar een Access Point of stuurt het terug naar de afzender. Meer in [gemiste bezorgingen](guide:missed-delivery) (Engels).

UPS kan het pakket ook naar een UPS Access Point brengen. Zodra het daar ligt, luiden de scans `Delivered to UPS Access Point™` en vervolgens `UPS Access Point™ possession`, en toont Peek naam en adres van dat punt. Wacht niet te lang met ophalen: wanneer een niet-afgehaald pakket teruggaat, luidt de scan `The UPS Access Point™ location has prepared the package for return to UPS or pickup by UPS.` Sluit een Access Point, dan haalt UPS de pakketten op en geeft de scan aan dat overdracht naar een ander Access Point in behandeling is. Zie [niet-afgehaalde pakketten](guide:uncollected-parcel) (Engels).

## Invoerkosten en douane bij UPS-pakketten

Voor een pakket van buiten het VK, of voor Ierland van buiten de EU, kunnen invoerkosten verschuldigd zijn voordat UPS bezorgt; de tracking toont dan `Duties or taxes are due on this package.` en `Your package is being held until duties and taxes are paid. Awaiting customer action.` Peek toont "At customs" met de melding "Import duties or taxes to pay".

Heeft de douane in plaats daarvan je belastingnummer nodig, dan luidt de UPS-scan `Importer's consumer or business Identification number is required for clearance. Awaiting information from the customer.`: de inklaring wacht op het identificatienummer van de importeur, en jij als ontvanger moet dat aanleveren. Peek toont "At customs" met de melding "Customs needs your tax number".

- **Verenigd Koninkrijk:** volgens GOV.UK stuurt de koerier je een rekening voor eventuele btw, invoerrechten of afhandelingskosten en bewaart het pakket normaal ongeveer 3 weken. Zonder betaling gaat het terug naar de afzender.
- **Ierland:** sinds 1 juli 2026 geldt voor de meeste pakketten tot €150 van buiten de EU €3 invoerrecht per productsoort. Heeft de winkel dat niet bij het afrekenen geïnd, dan vraagt de koerier je volgens Revenue om betaling vóór aflevering en rekent de koerier eigen administratiekosten. Sinds 1 november 2026 geldt voor online bestellingen van buiten de EU bovendien de EU-afhandelingsvergoeding (Union handling fee) van €2 per productsoort, ongeacht de waarde.

Meer in [douanekosten](guide:customs) (Engels).

## UPS-pakket vertraagd, kwijt of als bezorgd gemeld

Als de tracking niet meer beweegt, bekijk dan [tracking die niet bijwerkt](guide:tracking-not-updating) (Engels).

Woon je in Groot-Brittannië of Ierland en heb je bij een webwinkel besteld, dan is de verkoper verantwoordelijk dat het pakket bij jou aankomt, ook wanneer UPS het vervoert:

- **Engeland, Schotland en Wales:** volgens Citizens Advice moet de verkoper achter de koerier aan, niet jij. Komt het artikel niet binnen 30 dagen na aankoop aan, dan kun je annuleren en je geld terugvragen. Is het achtergelaten op een plek waarmee je niet instemde en daarna verdwenen, dan moet de verkoper dat oplossen.
- **Ierland:** volgens de CCPC moet een bestelling zonder afgesproken datum binnen 30 dagen na aankoop aankomen. Gebeurt dat niet, spreek dan een nieuwe bezorgdatum af met het bedrijf; verstrijkt ook die, dan heb je recht op volledige terugbetaling. Een tijdens vervoer beschadigd of verloren pakket moet worden vervangen, en de verkoper blijft verantwoordelijk totdat jij of iemand die jij aanwijst het pakket in handen heeft, ook als de koerier bezorging meldt.

Staat de status op `DELIVERED` maar ligt er niets? De pagina van UPS kan tonen waar het pakket is neergezet (bijvoorbeeld `Left at: Front Door`) en een afleverbewijs, gegevens die Peek niet opslaat. Controleer ups.com en neem daarna contact op met de winkel. Meer in [bezorgd maar niet ontvangen](guide:delivered-not-received) en [verloren pakketten](guide:lost-parcel) (Engels).

## UPS-pakketten volgen met Peek

[Peek](/) herkent UPS meteen bij een `1Z`-nummer met een kloppend controlecijfer; met een fout controlecijfer weigert UPS het als verkeerd ingetikt. Links met een `1Z`-nummer werken ook. Peek vraagt alleen `1Z`-nummers op bij UPS: andere formaten gaan via trackingdiensten.

Peek opent de trackingpagina van ups.com in een browser op de eigen server en leest het antwoord uit — tot elke 10 minuten, en elke 5 minuten vanaf 6:00 uur lokale tijd op de dag dat UPS het pakket als uit voor bezorging scant, zolang UPS zelf antwoordt (via trackingdiensten elke 15 minuten). Na twee dagen zonder nieuws (12 uur zolang het pakket nog "Label created" of "Received by carrier" toont) controleert Peek elk uur. Weigert UPS of is alleen de status leesbaar, dan vullen trackingdiensten de geschiedenis aan. Peek toont de status, elke scan op een kaart met exact tijdstip, de geplande bezorgdag (nooit een tijdvak), het bezorgtijdstip en, zolang het pakket wacht, naam en adres van het Access Point. Meldingen kunnen bij elke scan, bij belangrijke stappen of alleen op de bezorgdag.

Peek laat de ondertekenaar, jouw adres, het afleverbewijs en de achterlaatplek weg en toont geen bezorgfoto of gewicht. Scans verschijnen in het Engels van UPS; aflevering staat er als "DELIVERED". Wanneer UPS een Ground Saver-pakket overdraagt aan [US Postal Service](carrier:usps) en het nummer noemt, volgt Peek het pakket daar verder zodra USPS voortgang toont ("Delivery with USPS").

## Veelgestelde vragen over UPS tracking

### Waarom is mijn UPS trackingnummer ongeldig?

Meestal zit er een typefout in: één verkeerd teken in een `1Z`-nummer maakt het controlecijfer ongeldig, waardoor UPS het nummer afwijst. Of het nummer hoort bij een andere vervoerder: controleer welke vervoerder de e-mail van de winkel noemt.

### Hoe lang duurt het voordat UPS tracking zichtbaar wordt?

De eerste scan, `Shipper created a label, UPS has not received the package yet.`, verschijnt zodra de afzender het label heeft aangemaakt. De tracking beweegt pas verder wanneer de afzender het pakket afgeeft of UPS het ophaalt (`Drop-Off`, `Pickup Scan`).

### Kan ik een UPS-bezorging wijzigen in Peek?

Nee: Peek leest alleen de tracking van UPS uit. Wijzigingen die je rechtstreeks bij UPS doorgeeft verschijnen wel in de scans, zoals `The package will be delivered to the UPS Access Point™ location requested by the receiver.`

### Is een sms over een UPS-bezorging oplichting?

Dat kan. Volgens Citizens Advice is een bericht waarin je moet betalen om een pakket te ontvangen of opnieuw in te plannen meestal oplichting, en vragen bezorgdiensten niet om betaling via een link in een sms of e-mail. Echte invoerkosten verschijnen als douanescans in de tracking, dus controleer het nummer zelf op ups.com of in Peek in plaats van op de link te tikken. In het VK kun je de sms gratis doorsturen naar 7726. Meer in [nepmeldingen over bezorging](guide:delivery-scam-texts) (Engels).

:::sources
- [Citizens Advice: If something you ordered hasn't arrived](https://www.citizensadvice.org.uk/consumer/somethings-gone-wrong-with-a-purchase/if-something-you-ordered-hasnt-been-delivered/) – verantwoordelijkheid verkoper, 30 dagen, afgesproken plek, nepberichten (Engeland, Schotland en Wales)
- [GOV.UK: Tax and customs for goods sent from abroad](https://www.gov.uk/goods-sent-from-abroad/tax-and-duty) – rekening van de koerier, ongeveer 3 weken, retour afzender
- [GOV.UK: Report internet scams and phishing](https://www.gov.uk/report-suspicious-emails-websites-phishing) – nep-sms'jes doorsturen naar 7726
- [CCPC: Your consumer rights if your order is not delivered](https://www.ccpc.ie/consumer-advice/consumer-rights/buying-goods/non-delivery-of-goods) – 30 dagen, nieuwe datum, terugbetaling, verantwoordelijk tot ontvangst (Ierland)
- [Revenue: Buying goods from outside the EU](https://www.revenue.ie/en/customs/individuals/buying-online-personal/outside-eu.aspx) – €3 per productsoort sinds 1 juli 2026, vooraf aan koerier te betalen
- [Revenue: Buying goods online for personal use](https://www.revenue.ie/en/customs/individuals/buying-online-personal/index.aspx) – administratiekosten van koeriers
- [European Commission: EU Customs Reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – €2 EU-afhandelingsvergoeding sinds 1 november 2026
- [European Commission: The temporary EUR 3 customs duty and the Union handling fee](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – €2 per productsoort op verkopen op afstand, ongeacht de waarde
- [Universal Parcel Scraper: UPS](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/ups) – nummerformaten, controlecijfer, UPS-scanteksten (overheidsstop en vrijgave, bezorginstructies ontvanger, verzoek om identificatienummer importeur), wat Peek van UPS leest
:::
