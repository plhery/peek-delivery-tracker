---
title: SpeedX Sendungsverfolgung: SPX-Nummer, Status & Kontakt
description: SpeedX Paket in den USA verfolgen: Aufbau der SPX-Sendungsnummer, Bedeutung der englischen Statusmeldungen, Zustellfoto per ZIP-Code, Fehlversuche und Support.
slug: speedx-sendungsverfolgung
published: 2026-11-21
updated: 2026-11-21
---

SpeedX (Speed Xpress Inc. mit Sitz in New Hyde Park, Bundesstaat New York) ist ein Letzte-Meile-Zustelldienst, der Onlinebestellungen in den **Vereinigten Staaten** an die Haustür liefert. Du kannst eine Sendung auf [tracking.speedx.io](https://tracking.speedx.io/) verfolgen oder ihre Nummer oben ins Suchfeld einfügen. Den Code erhältst du vom Onlineshop: SpeedX erzeugt das Versandetikett für den Händler, sodass dieser die Nummer bereits kennt, bevor SpeedX das Paket physisch übernimmt.

## SpeedX Sendungsnummer: Format und Fundort

Laut SpeedX beginnen alle eigenen Sendungsnummern mit `SPX`. In der Praxis folgen auf `SPX` drei Buchstaben sowie 12 oder 18 Ziffern. Erkannte Muster (mit frei erfundenen Zeichen):

| Erfundenes Muster | Bedeutung |
| --- | --- |
| `SPXLGA482910573649` | `SPX`, 3 Buchstaben und 12 Ziffern (18 Zeichen). |
| `SPXLGA482910573649182736` | `SPX`, 3 Buchstaben und 18 Ziffern (24 Zeichen). |
| `SPXPH482910573649` | `SPXPH` gefolgt von Ziffern: nicht SpeedX in den USA, sondern SPX Express auf den Philippinen – ein anderes Unternehmen. |

Groß-/Kleinschreibung und Leerzeichen ignoriert Peek.

**Wo sie steht.** Suche in der Versandbestätigung des Händlers oder in deiner Bestellübersicht. Fehlt sie dort, verweist SpeedX an den Verkäufer; weitere Hinweise bietet [Sendungsnummer finden](guide:find-tracking-number). In den USA bietet SpeedX auf seiner Website zudem automatische SMS-Updates an, sobald das Paket in Zustellung geht.

**Ähnliche Nummern.** `SPX` gefolgt ausschließlich von Ziffern passt zu keinem der beiden SpeedX-Formate. Hat ein `SPX…`-Code eine Ziffer zu viel oder zu wenig, gleiche ihn mit der E-Mail des Shops ab. SpeedX-Nummern überschneiden sich auch nicht mit UniUni, [GOFO](carrier:gofo) oder [USPS](carrier:usps) in den USA. Bleibt die Nummer unauffindbar, empfiehlt SpeedX die Rückfrage beim Händler, da ein anderer Letzte-Meile-Dienst beauftragt sein kann. Weitere Muster unter [Formate von Sendungsnummern](guide:tracking-number-formats).

## Was die Statusmeldungen von SpeedX bedeuten

Die Trackingseite von SpeedX ist ausschließlich auf Englisch verfügbar. Typischer Ablauf einer Sendung:

| Status bei SpeedX | Bedeutung für dein Paket | Anzeige in Peek |
| --- | --- | --- |
| `Shipping Label Created` | Der Shop hat das Etikett erstellt; SpeedX hat das Paket noch nicht. | „Etikett erstellt“ |
| `Pickup Requested` | Der Händler hat die Abholung bei SpeedX angemeldet; SpeedX hält das Paket noch nicht. | „Etikett erstellt“ |
| `Parcel Picked Up` | Ein SpeedX-Fahrer hat die Sendung beim Versender abgeholt. | „Beim Paketdienst eingegangen“ |
| `Arrived at Origin Facility` / `Processing at Origin Facility` | Eingang und Sortierung im ersten SpeedX-Standort. | „Unterwegs“ |
| `In transit to destination facility` | Unterwegs zwischen SpeedX-Zentren in den USA. | „Unterwegs“ |
| `Processing at Transit Facility` | Sortierung in einem Zwischenzentrum entlang der Route. | „Unterwegs“ |
| `Arrival at Destination Facility` | Ankunft im Zentrum, das deine Region in den USA beliefert. | „Unterwegs“ |
| `Processing at Designated Facility` | Dort für die Tour eines Zustellers eingeteilt. | „Unterwegs“ |
| `Out for Delivery` | Im Fahrzeug für die heutige Zustellung unterwegs. | „In Zustellung“ |
| `Attempted Delivery: No access` | Der Fahrer kam nicht bis zur Tür: verschlossenes Tor, kein Hauszugang, Baustelle oder Unwetter. | „Zustellversuch“ |
| `Delivered` | Zugestellt. Auf der Website von SpeedX stehen zusätzlich Ablageort (etwa Postraum) und ein Zustellfoto. | „Zugestellt“ |
| `Delivered to PUDO` / `Waiting for collection from Parcelshop` | Nach der Tour wurde das Paket in einem Partner-Paketshop (*parcelshop*) zur Abholung hinterlegt. | „Abholbereit“ |
| `Self picked up from Parcelshop` | Das Paket wurde im *parcelshop* abgeholt. | „Zugestellt“ |
| `Parcel Void` | SpeedX hat ein Problem mit der Sendung vermerkt. Wende dich an den Shop. | „Problem gemeldet“ |
| `Exception- Parcel not found at the sort facility` | Paket im Sortierzentrum nicht auffindbar. Kontaktiere den Händler. | „Problem gemeldet“ |
| `Parcel Disposal` | SpeedX hat eine Störung ohne nähere Begründung gebucht. Kläre das weitere Vorgehen mit dem Shop. | „Problem gemeldet“ |

Solange lediglich `Shipping Label Created` (oder eine gescheiterte Abholung beim Versender wie `Pickup Failure:Closed Business Hours`) angezeigt wird, befindet sich die Ware noch beim Händler oder dessen Lager.

Ein Fehlversuch kann andere Gründe nennen, etwa `Attempted Delivery:Building Closed/Weekend/Holiday` bei geschlossenem Gewerbebetrieb („Zustellversuch“). Darunter ergänzt SpeedX einen Hinweis zum nächsten Schritt: `The package is undeliverable and schedule to redelivery` kündigt einen neuen Zustellversuch an, `The package is undeliverable and return to facility` die Rückkehr ins Depot. Peek zeigt diesen Zusatz hinter der Statuszeile an.

Ist das jüngste Ereignis eine Störung (`Parcel Void`, `Parcel Disposal` oder nicht gefundenes Paket), ordnet SpeedX es einer Kategorie wie `Last Mile Intercept` oder `Last Mile Undelivered` zu. SpeedX erläutert diese Kategorien nicht öffentlich – frage beim Verkäufer nach.

## Zustellung in den USA, Fehlversuche und Abholung

Alle nachstehenden Zustellbedingungen gelten für das Netz von SpeedX in den **USA**:

**Sendungen aus dem Ausland.** SpeedX stellt in den USA auch Bestellungen aus anderen Ländern zu: Der grenzüberschreitende Dienst übernimmt sie an großen US-Flughafen-Hubs, erledigt die US-Zollabfertigung über eigene Zollagenten und speist sie anschließend ins Zustellnetz ein. Allgemeines zu Einfuhrabgaben unter [Paket beim Zoll](guide:customs).

**Wochenende.** In den USA liefert SpeedX samstags von 8:00 bis 20:00 Uhr Ortszeit in allen bedienten Gebieten aus und sonntags zu denselben Zeiten in ausgewählten Regionen. Weder Wunschzeitfenster noch Vorankündigungen kurz vor Ankunft des Fahrers werden angeboten.

**Unterschrift und Zustellfoto.** Die meisten SpeedX-Pakete in den USA benötigen keine Unterschrift; altersbeschränkte Waren wie Alkohol können die Unterschrift einer volljährigen Person ab 21 Jahren erfordern. Ist niemand daheim, legt der Fahrer das Paket ab und macht ein Foto – um es anzusehen, rufe das Tracking auf `speedx.io` auf und gib deinen US-Zustell-ZIP-Code ein.

**Zustellhinweise und Adressänderung.** Hinweise wie Torcodes, gewünschter Ablageort, Geschäftszeiten oder Telefonnummer nimmt SpeedX über seinen Chatbot Miles unter Angabe der Sendungsnummer entgegen; sie werden in Echtzeit auch an den Fahrer übertragen, mit dem kein direktes Gespräch möglich ist. Geht eine Änderung rechtzeitig ein, kann SpeedX in den USA Straße oder Wohnungsnummer korrigieren; für einen Wechsel von Stadt, Bundesstaat oder ZIP-Code musst du den Händler kontaktieren.

**Verpasste Zustellung.** Hältst du einen gemeldeten Fehlversuch für unberechtigt, prüfe zuerst die Lieferadresse im Shopkonto und übermittle Korrekturen oder Zugangshinweise per Chat an Miles – laut SpeedX erfolgt die erneute Zustellung dann in der Regel binnen 1 bis 2 Werktagen. Wie viele Versuche insgesamt unternommen oder wie lange Pakete gelagert werden, veröffentlicht SpeedX nicht. Siehe auch [Zustellung verpasst](guide:missed-delivery).

**Abholung.** Eine Selbstabholung in eigenen SpeedX-Depots in den USA schließt SpeedX aus; das Paket muss zugestellt werden. Einzelne Sendungen gelangen jedoch nach einer Tour in einen Partner-Paketshop (*parcelshop*) mit dem Status `Waiting for collection from Parcelshop` und können dort abgeholt werden.

## SpeedX-Paket bewegt sich nicht, fehlt oder ging verloren

Für Sendungen im US-Netz von SpeedX gilt:

**Kein Fortschritt.** Ändert sich der Status drei Werktage lang nicht, bittet SpeedX darum, den Onlinehändler zu kontaktieren. Mehr unter [Sendungsverfolgung aktualisiert sich nicht](guide:tracking-not-updating).

**Als zugestellt markiert, aber unauffindbar.** SpeedX weist selbst darauf hin, dass Pakete mitunter vor dem tatsächlichen Eintreffen als zugestellt gebucht oder an einer unerwarteten Stelle am Haus abgelegt werden. Prüfe in den USA das Zustellfoto sowie Vorder- und Hintereingang, Briefkasten oder Postraum (*mailroom*), Garage, Hausseiten und wettergeschützte Ecken und frage Mitbewohner oder Nachbarn. Informiere anschließend SpeedX über Miles und wende dich für Ersatz oder Erstattung an den Händler. Siehe [zugestellt, aber nicht erhalten](guide:delivered-not-received).

**Verlust, Beschädigung oder falsche Adresse.** SpeedX nimmt keine Schadens- oder Verlustmeldungen von Empfängern an. Wende dich mit Bestellnummer, Sendungsnummer und bisherigem Schriftverkehr an den Händler (bei Transportschäden ggf. mit Fotos); eine Ausschlussfrist nennt SpeedX nicht. Siehe [verlorenes Paket](guide:lost-parcel).

**Retouren.** Nach erfolgter Zustellung läuft jede Rücksendung über den Shop; SpeedX holt in den USA keine Retouren an der Haustür ab. Vor der Zustellung kannst du Miles bitten, die Auslieferung zu stoppen und das Paket an den Händler zurückzuschicken. Siehe [zurück an den Absender](guide:returned-to-sender).

## SpeedX Kundenservice: Chat, Telefon und E-Mail

Auf der [Supportseite von SpeedX](https://speedx.io/customer-service-and-support/) genannte Kanäle für Empfänger in den **USA**:

- **Chat:** Chatbot Miles, täglich von 8:00 bis 20:00 Uhr ET (US-Ostküstenzeit) direkt im Trackingportal nach Eingabe der Nummer.
- **Telefon in den USA:** `1-949-667-6769`, Montag bis Freitag von 9:00 bis 21:00 Uhr EST sowie am Wochenende von 11:00 bis 20:00 Uhr EST. An anderer Stelle derselben Hilfeseite heißt es allerdings, SpeedX habe derzeit keine Telefonnummer und verweise auf Miles – rechne also mit einer Weiterleitung in den Chat.
- **E-Mail:** `speedxops@speedx.io`.

## SpeedX Pakete mit Peek verfolgen

[Peek](/) erkennt SpeedX sofort anhand eines `SPX`-Codes beider Längen (`SPX` + 3 Buchstaben + 12 oder 18 Ziffern) oder anhand eines Links von `tracking.speedx.io` mit der Nummer und zeigt „Erkannter Paketdienst“ samt „Ändern“.

Peek liest direkt die Trackingseite von SpeedX und zeigt den aktuellen Status, jeden Scan mit Ort auf der Karte zum von SpeedX erfassten Zeitpunkt (umgerechnet in die Zeitzone deines Geräts, mit „Uhrzeiten in deiner Zeitzone: …“ unter dem Verlauf auf `peektracker.com` sowie in der iPhone- und Android-App) sowie die genaue Lieferzeit und, falls die Seite von SpeedX einen nennt, den voraussichtlichen Zustelltag. Straßenadresse und Zustellfoto übernimmt Peek nicht – das Foto rufst du mit deinem US-ZIP-Code direkt auf `speedx.io` ab. Fügt SpeedX einen Hinweis zum Ablageort hinzu, übernimmt die Zustellzeile ihn unverändert auf Englisch; über einen geteilten Link zeigt sie nur „Zugestellt“, ohne diesen Hinweis. Eine Übergabe an einen anderen Paketdienst findet nicht statt.

Kennt SpeedX die Nummer noch nicht, zeigt Peek „Warten auf SpeedX“; bleibt sie zwei Tage lang sowohl bei SpeedX als auch anderswo unbekannt, erscheint „Auch nach zwei Tagen kennt der Paketdienst diese Nummer nicht. Prüfe sie, wähle einen anderen Paketdienst oder lösche das Paket.“ Vergehen auf „Etikett erstellt“, „Beim Paketdienst eingegangen“ oder „Unterwegs“ vier Tage ohne neuen Scan, blendet Peek „Hol dir Hilfe bei SpeedX“ mit Link zur Supportseite ein.

Solange du das Paket geöffnet hast oder am Zustelltag Benachrichtigungen aktiviert sind, prüft Peek bis zu alle 10 Minuten – und ab 6:00 Uhr Ortszeit an dem Tag, an dem SpeedX das Paket mit `Out for Delivery` scannt, bis zu alle 2 Minuten, solange der Tracker von SpeedX antwortet (über externe Trackingdienste allein: stündlich, alle 3 Stunden nach einem ruhigen Tag und am Zustelltag bei geöffnetem Paket oder aktiven Mitteilungen bis zu alle 15 Minuten); ansonsten sowie nach zwei Tagen ohne Änderung (oder 12 Stunden bei „Etikett erstellt“ oder „Beim Paketdienst eingegangen“) stündlich. Benachrichtigungen lassen sich auf „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“ einstellen. Antwortet die Seite von SpeedX nicht, führen andere Trackingquellen selten Daten zu SpeedX-Sendungen, sodass neue Einträge erst wieder erscheinen, wenn sie erreichbar ist.

## Häufige Fragen zur SpeedX Sendungsverfolgung

### Ist SpeedX ein seriöser Zustelldienst?

Ja: SpeedX ist die Marke der 2022 im US-Bundesstaat New York gegründeten Speed Xpress Inc., eines Letzte-Meile-Zustellers für E-Commerce-Bestellungen in den Vereinigten Staaten.

### Kann ich mein SpeedX-Paket selbst abholen?

Nicht in den eigenen Standorten von SpeedX in den USA – das Unternehmen schließt Abholungen in seinen Depots aus. Zeigt der Verlauf jedoch `Waiting for collection from Parcelshop`, wurde das Paket nach der Tour in einem Partner-Paketshop (*parcelshop*) hinterlegt und kann dort abgeholt werden.

### Wie lange braucht SpeedX für die Zustellung?

Verbindliche Laufzeiten für Empfänger veröffentlicht SpeedX nicht. Gegenüber Versendern bietet das Unternehmen in den USA Zustellungen am nächsten Tag (*next-day*) und am zweiten Tag (*2nd-day*) an; ein geschätztes Datum stammt vom Shop und kann laut SpeedX vom tatsächlichen Liefertag abweichen.

### Warum funktioniert meine SpeedX-Sendungsnummer nicht?

Meldet `tracking.speedx.io` noch keine Daten (`No tracking information for … is available at this time`), hat der Absender die Paketdaten eventuell noch nicht übermittelt oder die Nummer ist falsch. SpeedX rät zur Rücksprache mit dem Händler, da die Bestellung auch mit einem anderen Zustelldienst unterwegs sein kann.

:::sources
- [SpeedX: Customer Service and Support](https://speedx.io/customer-service-and-support/) – SPX-Präfix, Glossar, SMS, Samstags- und Sonntagszustellung, Altersprüfung ab 21, Fotos per ZIP-Code, Hinweise und Adresskorrektur über Miles, keine Depotabholung, 3 Werktage ohne Update, Reklamationen über den Händler, Retouren, Telefon, E-Mail und Zeiten
- [SpeedX: Tracking](https://tracking.speedx.io/) – englischsprachiges Trackingportal von SpeedX
- [SpeedX: Tech-Enabled Last Mile Delivery](https://speedx.io/) – Letzte-Meile-Zustellung in den USA, Etiketten von SpeedX, Next-Day- und 2nd-Day-Versand sowie Zustellfotos
- [SpeedX: Cross-Border Shipping](https://speedx.io/cross-border-shipping/) – Annahme an US-Flughafen-Hubs, eigene Zollabwicklung und Einspeisung ins US-Netz
- [SpeedX: About Us](https://speedx.io/about-us/) – Gründung 2022 in New York und US-Abdeckung
- [SpeedX: Privacy Policy](https://speedx.io/privacy-policy/) – Firmenname Speed Xpress Inc. und Anschrift in New Hyde Park
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – erkannte SPX-Formate, Statuscodes, Zusatzhinweise bei Fehlversuchen, Unbekannt-Meldung und Datenschutz bei geteilten Links
:::
