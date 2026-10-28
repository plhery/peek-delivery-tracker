---
title: bpost pakje volgen: barcode, statussen en Afhaalpunt
description: Volg je bpost-pakje: waar je de barcode vindt, wat Track & Trace toont, hoelang een Afhaalpunt of bbox-automaat je pakje bewaart en hoe je bpost bereikt.
slug: bpost-pakje-volgen
published: 2026-10-22
updated: 2026-10-28
---

bpost is het Belgische postbedrijf. Binnen de bnode-groep, tot december 2025 bpostgroup, is bpost het merk dat pakjes en post aan de deur brengt in België en Nederland. Je volgt je pakje met de barcode in [bpost Track & Trace](https://track.bpost.cloud/) of in de My bpost app, of je plakt de barcode in het veld hierboven. Die barcode staat in de verzendmail van de webshop, in de e-mail of sms van bpost, op het verzendetiket of op het afwezigheidsbericht in je brievenbus.

## Barcode van je bpost-pakje: waar vind je hem?

bpost spreekt niet van een trackingnummer maar van een barcode. Je vindt hem op het verzendetiket, op het afgifte- of betalingsbewijs van het Postkantoor, op het afwezigheidsbericht, in de bevestigingsmail en in de berichten van de afzender. Een webshop mailt hem meestal zelf, soms als link naar Track & Trace. Geen barcode gekregen? Vraag hem aan de afzender: zonder barcode kan bpost je niet verder helpen.

De barcodes die bpost zelf het vaakst noemt (voorbeelden verzonnen):

| Vorm | Voorbeeld | Wat het is |
| --- | --- | --- |
| 24 cijfers, begint met 3232 of 3299 | `323200000000000000000000` | Barcode van een pakje |
| 18 cijfers, begint met 3232 | `323200000000000000` | Barcode van een pakje |
| 27 tekens, begint met JJBEA | `JJBEA0000000000000000000001` | Barcode van een pakje |
| 2 letters, 9 cijfers, BE | `CE000000005BE` | Internationaal postnummer uit België |
| R, een letter, 9 cijfers, 2 letters | `RR000000005BE` | Internationale aangetekende zending |
| 30 cijfers, begint met 010 | `010000000000000000000000000000` | Aangetekende zending binnen België |

**Begint je barcode met een U?** Dan kan je de zending niet volgen. bpost maakt zo'n barcode alleen aan om de zending door de douane te laten gaan: de afzender, of jij bij je aankoop, koos voor levering zonder Track & Trace. Ze wordt verwerkt als een brief en komt binnen maximaal 5 werkdagen nadat de douane ze vrijgeeft.

**Track & Trace vraagt ook een postcode.** Met de barcode alleen toont bpost om privacyredenen maar beperkte informatie. Vul je ook de postcode van het afleveradres in, dan zie je alle details en kan je eventuele invoerkosten betalen. Je kan tot 100 barcodes tegelijk opzoeken.

**Nummers die op elkaar lijken.** Een postnummer dat op de letters van een ander land eindigt, komt van de post van dat land. Een tikfout in een nummer dat op BE eindigt, klopt niet meer met het controlecijfer; dan kan Peek [Chronopost](carrier:chronopost) voorstellen. Sommige nummers van 24 cijfers zijn van [InPost](carrier:inpost), [Ciblex](carrier:ciblex) of [Austrian Post](carrier:austrian-post): kijk bij twijfel welke vervoerder de webshop in zijn mail noemt.

## Wat de statussen in Track & Trace betekenen

Track & Trace kan je in het Nederlands openen. Kent bpost de dag al, dan zie je een `Verwachte leveringsdatum` of `Geplande levering`; ligt je pakje klaar, dan staat bij `Beschikbaar tot` de laatste dag om het af te halen.

**‘In voorbereiding’.** Blijft je pakje op die status staan, dan weet bpost dat het je pakje zal leveren, maar maakt de afzender het nog klaar, of moet bpost het nog sorteren. Volgens bpost worden de meeste pakjes verwerkt binnen 5 dagen nadat de afzender bpost als pakjesbezorger heeft gekozen.

**Niet gevonden?** Dan meldt Track & Trace `Het is mogelijk dat je zending nog niet gekend is in ons systeem.` Probeer het later opnieuw of contacteer de afzender.

In Peek, en in de Engelstalige Track & Trace, lees je de scans in het Engels. Dit kom je het vaakst tegen, ongeveer in deze volgorde:

| bpost schrijft (Engels) | Wat het voor jou betekent |
| --- | --- |
| `Confirmation of preparation of the shipment received` | De afzender heeft het pakje aangekondigd. bpost heeft het nog niet. |
| `Item is announced / Landmark Global (bpost) received the information` | Een zending over de grens is aangekondigd bij [Landmark Global](carrier:landmark-global), het merk van de groep voor pakjes tussen landen. |
| `Landmark Global (bpost) has received the item` | Landmark Global heeft het pakje in handen. |
| `Arrival at facilities` | Je pakje uit het buitenland is aangekomen in een sorteercentrum in het land van verzending; bpost zelf heeft het nog niet gescand. |
| `Awaiting presentation to Customs` | Een zending van buiten de EU wacht op de douane. |
| `Your item has been sorted` | Gesorteerd en verder onderweg. |
| `Departure to country of destination` | Je zending naar het buitenland vertrekt naar het land van bestemming. |
| `Your shipment has arrived at the postal operator of the country of destination and will be delivered in the coming days` | De buitenlandse post heeft je zending en levert ze. Vanaf nu hangt bpost af van haar informatie. |
| `The postman's delivery round for your item has started` | De postbode heeft je pakje vandaag mee op ronde. |
| `Item available at Pick-up point` | Het ligt klaar in een Afhaalpunt. |
| `Item available at the bbox locker` | Het ligt klaar in de bbox-automaat. |
| `Item delivered` | Geleverd, of afgehaald in het Afhaalpunt. |
| `Item delivered in mailbox` | In je brievenbus gestoken. |

## Levering aan huis, Afhaalpunt en bbox-automaat

**Niet thuis.** Heb je geen leveringsvoorkeuren ingesteld, dan probeert de postbode het doorgaans eerst bij een buur of op een veilige plaats rond je woning. Lukt dat niet, dan gaat je pakje naar een Afhaalpunt in de buurt, waar het vanaf de volgende werkdag klaarligt. Zodra het daar ligt, krijg je een e-mail en zie je het in Track & Trace of de My bpost app; een afwezigheidsbericht op papier krijg je alleen als bpost je niet digitaal kan bereiken. Het Afhaalpunt kan uitzonderlijk nog wijzigen, dus kijk de status na voor je vertrekt. De postbode komt dezelfde dag niet terug.

**Hoelang je pakje wacht.** In een Afhaalpunt 14 kalenderdagen. In een bbox-automaat 5 kalenderdagen, en die termijn kan je niet verlengen. Daarna gaat het terug naar de afzender. Iedereen met de barcode mag een pakje afhalen. Een aangetekende zending (barcode die begint met 010 of met R) haal je zelf af met je identiteitsbewijs en de barcode, of iemand anders doet het met een volmacht: je tekent daarvoor het afwezigheidsbericht en geeft het mee. Een pakje dat al in een Afhaalpunt ligt, kan je niet naar een ander laten overbrengen.

**bbox-automaten.** bpost spreekt afwisselend van bbox-automaat, Bbox en Pakjesautomaat. Bijna alle automaten zijn 24/7 open, zonder account. Bij een automaat met scherm scan je de QR-code uit de e-mail of de My bpost app; koos je zelf voor levering in de automaat, dan staat onder de QR-code ook een code van 6 cijfers. Een automaat zonder scherm open je alleen met de app, via bluetooth en met je locatie aan. Is de automaat vol of defect, is je pakje te groot of te zwaar, of is het een aangetekende zending of een pakje met betaling bij levering, dan gaat het naar een ander Afhaalpunt in de buurt.

**Je levering aanpassen.** In MijnVoorkeuren of de My bpost app kies je waar je pakjes naartoe gaan als je niet thuis bent: een veilige plaats, een buur, een Afhaalpunt of rechtstreeks een bbox-automaat. Zit je pakje onderweg, dan krijg je een mail met de leveringsdatum en kan je het nog op een ander adres in de buurt laten leveren of de levering een werkdag uitstellen, tot net voor de postbode 's morgens op ronde vertrekt. Een buur of veilige plaats wijzig je tot 5 minuten voor de levering, een Afhaalpunt of automaat tot de avond ervoor.

**Wanneer je voorkeur niet telt.** Een klein pakje probeert de postbode eerst in je brievenbus te steken. Een veilige plaats of een buur kan niet bij betaling bij levering, een beveiligde leveringscode of een aangetekende zending. Je voorkeuren hangen vast aan het e-mailadres in MijnVoorkeuren: bestel je met een ander adres, voeg het daar dan toe.

## Pakje vertraagd, verloren, beschadigd of terug naar afzender

**Track & Trace beweegt niet meer.** Voor een zending naar een buurland wacht je minstens 5 werkdagen zonder nieuwe status, voor het Verenigd Koninkrijk en alle andere landen minstens 10. Vraag daarna de afzender om de klantendienst van bpost te contacteren. Lees ook [waarom tracking niet meer bijwerkt](guide:tracking-not-updating) (in het Engels).

**Wie dient een klacht in?** Als ontvanger kan je zelf geen klacht indienen: alleen de afzender kan bij bpost een onderzoek starten. De meeste webshops hebben vaste afspraken met bpost voor verloren of beschadigde pakjes, dus vraag de webshop om je bestelling opnieuw te verzenden of je terug te betalen.

**Afgeleverd, maar niks ontvangen.** Vraag het na bij je buren en huisgenoten. Een klein pakje kan zonder aanbellen in je brievenbus zijn beland. Kijk je leveringsvoorkeuren na; werd het op een veilige plaats gezet, dan zie je een foto van die plek in de app of Track & Trace. Zoek ook naar een afwezigheidsbericht. Nog altijd niets? Contacteer de afzender. bpost heeft ook een online formulier om een niet-geleverde zending te melden.

**Beschadigd.** Zie je de schade al in het Afhaalpunt, weiger het pakje dan: het gaat terug naar de afzender. Komt een beschadigd pakje uit België, contacteer dan binnen 7 dagen na ontvangst de afzender, met foto's van de verpakking en de inhoud. Komt het uit het buitenland, contacteer dan binnen 7 dagen bpost én de afzender.

**Terug naar de afzender.** Dat gebeurt als de afzender je adres verkeerd ingaf, als je het pakje aan de deur weigerde, of als je het niet binnen 14 dagen in het Afhaalpunt of 5 dagen in de automaat afhaalde. Vraag de afzender dan om het opnieuw te verzenden of je terug te betalen.

**Douane.** Een pakje van buiten de EU gaat langs de douane. Betaalde je de btw en invoerkosten niet al bij de webshop, dan betaal je ze vooraf, bij voorkeur in de My bpost app of Track & Trace, met Bancontact, Mastercard, Visa of mobiel bankieren (geen PayPal of overschrijving). Uitzonderlijk kan het in een Postkantoor. Je hebt 14 dagen om te betalen. Voor een online aankoop van buiten de EU tot € 150 betaal je sinds 1 juli 2026, door nieuwe EU-regels, € 3 invoerrechten per type product: twee identieke broeken kosten € 3, een broek en een paar schoenen € 6. Vanaf 1 november 2026 komen daar € 2 Europese afhandelingskosten per type product bij: de twee broeken kosten dan € 5, de broek en de schoenen € 10. Daarnaast betaal je btw (meestal 21 %) en € 21,50 administratiekosten van bpost, die laatste alleen als je de btw en invoerrechten niet vooraf bij de webshop betaalde. Boven € 150 betaal je invoerrechten volgens het soort product, btw, € 40 administratiekosten en, vanaf 1 november 2026, de € 2 afhandelingskosten. In Track & Trace zie je dan meldingen als `Betaling vereist` of `Factuur ontbreekt`. Meer in de gids over [pakjes bij de douane](guide:customs) (in het Engels).

> Voor zendingen binnen de EU vraagt bpost je nooit om te betalen terwijl je pakje onderweg is, en nooit om persoonlijke gegevens via e-mail of sms. bpost dreigt ook nooit met een deurwaarder. Stuur verdachte berichten door naar abuse@bpost.be.

## Klantendienst van bpost contacteren

Zo stond het op de Nederlandstalige pagina's van bpost op 27 oktober 2026:

- **Telefoon:** 02 278 51 26 op de Nederlandstalige en Engelstalige pagina's van bpost, 02 278 51 27 op de Franstalige en Duitstalige; maandag tot vrijdag van 08.00 tot 12.30 u. en van 13.00 tot 18.00 u., zaterdag van 09.00 tot 13.00 u. Zonaal tarief.
- **Chat:** voor sommige diensten, waaronder Track & Trace.
- **Ter plaatse:** in een Postkantoor, PostPunt of PakjesPunt.
- **Per brief, zonder postzegel:** bpost, Postbus 5000, 1000 Brussel.
- **Online:** klachtenformulieren.

Hou je barcode bij de hand. Een e-mailadres voor de klantendienst vermeldt bpost niet.

## Je bpost-pakje volgen met Peek

[Peek](/) herkent bpost meteen aan een geldig postnummer van twee letters, negen cijfers en BE, en aan `JJBE` gevolgd door een letter en 18 of 22 cijfers. Een barcode van 24 cijfers die met 3232 of 3299 begint, of van 18 cijfers die met 3232 begint, kan ook bij andere vervoerders passen: Peek vraagt hem na bij bpost en bij de andere vervoerders waarbij die vorm past. Kent bpost het pakje, dan kiest Peek bpost; alleen als Ecoscooting een barcode van 18 cijfers ook kent, kies je zelf, met ‘Choose’. Een aangetekende zending van 30 cijfers volg je door bpost te kiezen met ‘Change’. Een U-barcode koppelt Peek niet aan bpost, want bpost volgt hem niet. Plak je een link van bpost.be of van de Track & Trace-zoekpagina waarin de barcode als `itemCodes` staat, dan kiest Peek bpost.

Een pakje uit het buitenland met een internationaal postnummer volgt Peek eerst bij de post van het land van verzending. Meldt die dat het naar België gaat, of noemt ze geen land en ben je zelf in België, dan kan Peek ook bpost vragen. Zodra bpost nieuwe scans toont, volgt Peek het pakje daar verder, en de eerdere scans blijven staan.

Peek vraagt bpost rechtstreeks, zonder postcode. Je ziet de scans van bpost in het Engels, met de plaats, het gewicht en de afmetingen. Scans in België leest Peek op Belgische tijd en toont ze in de tijdzone van je toestel. Ligt je pakje klaar, dan toont Peek de naam en het adres van het Afhaalpunt of de automaat, ook nadat je het daar hebt afgehaald. Voor een pakje dat in België is geleverd, zie je het uur van levering, en een pakje dat terug bij de afzender is, staat als teruggestuurd. Overdag kijkt Peek elke 10 minuten, en elke 2 minuten zodra je pakje met de postbode op ronde is. 's Nachts kijkt Peek elk uur of om de twee uur. Als je wil, krijg je een melding bij elke nieuwe status.

Peek toont geen verwachte leveringsdag en laat de afzender en de ontvanger, het adres van de ontvanger, contactgegevens, leveringsvoorkeuren en het leveringsbewijs weg. Peek kan bpost of de webshop niet voor je contacteren.

## Vragen over bpost pakje volgen

### Hoe snel levert bpost een pakje binnen België?

bpost probeert elk pakje binnen België de eerstvolgende werkdag te leveren nadat de afzender het heeft afgegeven. De datum en het tijdslot die je krijgt, zijn een indicatie: volgens bpost komt 95 % van de pakjes binnen dat tijdslot aan.

### Hoelang blijft een pakje in het Afhaalpunt liggen?

14 kalenderdagen in een Afhaalpunt en 5 kalenderdagen in een bbox-automaat; die laatste termijn kan je niet verlengen. Daarna gaat het terug naar de afzender.

### Levert bpost op zaterdag en zondag?

Pakjes soms, als de afzender er een afspraak over heeft met bpost: op zaterdag tussen 8 en 17 u. in een Afhaalpunt of Pakjesautomaat, op zondag tussen 9 en 19 u. aan huis. Brieven en aangetekende zendingen komen alleen op weekdagen.

### Waarom kan ik mijn bpost-pakje niet volgen?

Meestal heeft de afzender het nog niet aan bpost gegeven: volgen kan pas vanaf dan. Een barcode die met U begint, heeft helemaal geen Track & Trace. En zonder de postcode van het afleveradres toont Track & Trace maar beperkte informatie.

### Kan ik een bpost-pakje volgen zonder barcode?

Niet echt. Track & Trace aanvaardt ook een referentie, maar toont dan om privacyredenen slechts beperkte informatie, en zonder barcode kan bpost je niet verder helpen. Vraag de barcode aan de webshop.

:::sources
- [bpost: Hoe ziet een barcode eruit en waar vind ik deze?](https://www.bpost.be/nl/faq/hoe-ziet-een-barcode-eruit-en-waar-vind-ik-deze) – vormen, waar je hem vindt, U-barcodes
- [bpost: Kan ik mijn pakje online volgen?](https://www.bpost.be/nl/faq/can-i-follow-my-parcel-online) – Track & Trace, app, zendingen naar het buitenland
- [bpost: Vanaf wanneer kan ik mijn pakje volgen via Track & Trace?](https://www.bpost.be/nl/faq/when-can-i-follow-my-parcel-track-trace) – volgen vanaf de overhandiging
- [bpost: Ik verwacht een pakje, maar heb geen barcode ontvangen](https://www.bpost.be/nl/faq/i-am-expecting-parcel-have-not-received-barcode-what-do-i-do-now) – mail van de webshop
- [bpost: Mijn pakje blijft ‘in voorbereiding’ staan](https://www.bpost.be/nl/faq/mijn-pakje-blijft-voorbereiding-staan-wat-betekent-dit-en-wat-kan-ik-doen) – betekenis, 5 dagen
- [bpost: Volg je zending](https://track.bpost.cloud/) – postcode, beperkte informatie, 100 barcodes, labels
- [bpost: Wanneer wordt mijn nationale pakje binnen België geleverd?](https://www.bpost.be/nl/faq/wanneer-wordt-mijn-pakje-geleverd) – eerstvolgende werkdag, 95 %
- [bpost: Mijn pakje kon niet bij me thuis geleverd worden](https://www.bpost.be/nl/faq/my-parcel-could-not-be-delivered-my-home-where-and-when-can-i-pick-it) – buur, veilige plaats, volgende werkdag, e-mail
- [bpost: Kan de postbode vandaag nog eens langskomen?](https://www.bpost.be/nl/faq/i-just-missed-my-parcel-can-postman-pass-again-today) – geen tweede passage
- [bpost: Pakje ontvangen in een Afhaalpunt](https://www.bpost.be/nl/pakje-ontvangen/afhaalpunt) – 14 en 5 dagen, afhalen, volmacht
- [bpost: Hoe haal ik een pakje uit een bbox-automaat?](https://www.bpost.be/nl/faq/how-do-i-remove-parcel-parcel-locker) – QR-code, bluetooth, geen verlenging
- [bpost: Bbox](https://www.bpost.be/nl/bbox) – 24/7, geen account
- [bpost: Waarom is mijn pakje niet geleverd in een bbox-automaat?](https://www.bpost.be/nl/faq/waarom-mijn-pakje-niet-geleverd-een-pakjesautomaat) – redenen
- [bpost: Wat als ik niet thuis ben?](https://www.bpost.be/nl/faq/what-if-i-am-not-home-when-postman-delivers-my-parcel) – voorkeuren, ander adres, uitstellen
- [bpost: Jouw leveringsvoorkeuren, jouw regels](https://www.bpost.be/nl/mybpostapp-pref-parcel) – termijnen, uitzonderingen
- [bpost: Levert bpost op zaterdag en zondag?](https://www.bpost.be/nl/faq/which-mail-or-shipments-does-bpost-deliver-saturday-and-sunday) – weekend
- [bpost: Mijn pakje lijkt ergens vast te zitten](https://www.bpost.be/nl/faq/my-parcel-seems-be-stuck-somewhere-it-has-not-moved-while-track-trace) – 5 en 10 werkdagen
- [bpost: Klacht over een beschadigd, verloren of niet bezorgd pakje](https://www.bpost.be/nl/faq/how-do-i-file-complaint-if-my-parcel-damaged-or-lost-or-hasnt-been-delivered) – ontvanger, 7 dagen
- [bpost: Pakje afgeleverd, maar niks ontvangen](https://www.bpost.be/nl/faq/i-get-message-my-parcel-has-been-delivered-i-havent-received-anything-what-now) – tips, onderzoek door de afzender
- [bpost: Ik wil een niet-geleverde zending melden](https://www.bpost.be/nl/formulieren/pakje/niet_ontvangen/step1) – formulier
- [bpost: Mijn pakje is terug naar de afzender](https://www.bpost.be/nl/faq/mijn-pakje-zou-thuis-geleverd-worden-maar-terug-naar-de-afzender-waarom-en-hoe-kan-ik-het-toch-nog) – redenen
- [bpost: Een pakje ontvangen uit België](https://www.bpost.be/nl/pakje-ontvangen) – 14 dagen om invoerkosten te betalen
- [bpost: Pakje langs de douane](https://www.bpost.be/nl/pakje-ontvangen/douane) – vooraf betalen, Postkantoor
- [bpost: Een pakje van buiten de EU?](https://www.bpost.be/nl/shoppen-buiten-eu) – € 3 en € 2 per item, btw, € 21,50 en € 40
- [bpost: Wanneer betaal ik € 3 en € 2 voor elk verschillend type product?](https://www.bpost.be/nl/faq/wanneer-betaal-ik-eu-3-en-eu-2-voor-elk-verschillend-type-product-voor-pakjes-van-buiten-de-eu) – per type product, broeken en schoenen
- [bpost: Hoe kan ik de invoerkosten betalen?](https://www.bpost.be/nl/faq/hoe-kan-ik-de-invoerkosten-betalen) – betaalmiddelen
- [bpost: Verdachte sms of e-mail](https://www.bpost.be/nl/faq/i-received-suspicious-text-message-or-email-allegedly-bpost-what-should-i-do) – phishing
- [bpost: Hoe contacteer ik de klantendienst van bpost?](https://www.bpost.be/nl/faq/how-do-i-contact-bposts-customer-services) – telefoon, uren, chat, Postbus 5000
- [bpost: Comment contacter le service clientèle de bpost ?](https://www.bpost.be/fr/faq/comment-contacter-le-service-clientele-de-bpost) – 02 278 51 27 (Franstalige pagina)
- [bnode: bpostgroup wordt bnode](https://press.bnode.com/bpostgroup-wordt-bnode) – groep, bpost, Landmark Global
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – wat Peek bij bpost leest
:::
