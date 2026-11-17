---
title: Paack Sendungsverfolgung: Bestellnummer, PLZ und Status
description: Paack-Paket verfolgen: Bestellnummer und Postleitzahl der Lieferadresse, Bedeutung der Statusmeldungen, Zeitfenster, PaackGo Points, Chat und Colis Privé.
slug: paack-sendungsverfolgung
published: 2026-11-17
updated: 2026-11-17
---

Paack stellt Bestellungen von Onlineshops auf der letzten Meile zu – an der Haustür oder an einem Abholpunkt (PaackGo Point). Eigene Zustellnetze betreibt Paack in über 50 Städten in Spanien, Frankreich, Italien und Portugal sowie für Sendungen aus Großbritannien über [Evri](carrier:evri) International; in Deutschland, Österreich und der Schweiz stellt Paack nicht selbst zu, begegnet dir aber bei Bestellungen an eine Urlaubs-, Zweit- oder Empfängeradresse in diesen Ländern. Auf der [Tracking-Seite von Paack](https://paack.co/shipments/tracking?lng=en) gibst du die **Bestellnummer** (`Order number`) und die **Postleitzahl** der Lieferadresse (`Postal code`) ein – oder du fügst die Nummer oben ein, wählst Paack und ergänzt die Postleitzahl. Paack prüft beide Angaben als Paar: Stimmt eine davon nicht, wird nichts angezeigt.

## Paack-Sendungsnummer: Wo sie steht und wie sie aussieht

Paacks Tracking-Seite verlangt die Bestellnummer des Shops oder die Barcodenummer vom Paketetikett zusammen mit der Postleitzahl der Lieferadresse. Da Paack meist die Bestellnummer des Händlers übernimmt, gibt es kein einheitliches Format: Schau in die Bestell- oder Versandmail des Shops, in die Tracking-Mail von Paack (sobald das Paket im Lager erfasst ist) oder auf das Etikett. Keine Nummer zur Hand? Frag beim Händler nach oder lies [Sendungsnummer finden](guide:find-tracking-number).

Einige Etiketten folgen festen Mustern, während Händler-Bestellnummern wie Sendungsnummern anderer Paketdienste aussehen können (erfundene Beispiele):

| Bestell- oder Etikettennummer | Was dahintersteckt |
| --- | --- |
| `PK123456789012345678` | `PK` und 18 Ziffern: Paket von [Evri](carrier:evri) International, das Paack zustellt; in Peek auch ohne Postleitzahl verfolgbar. |
| `534012345678901234` | `5340` und 14 Ziffern (18 Ziffern gesamt): Etikettennummer, die regelmäßig auf Paack-Paketen vorkommt. |
| `C26111512345678` | `C` oder `E`, 6-stelliges Datum (`JJMMTT`) und 8 Ziffern: Paketnummer von [SHEIN-Bestellungen](guide:shein-orders), in Spanien auch bei [Ecoscooting](carrier:ecoscooting) im Einsatz. |
| `123456789AB` | Buchstaben und Ziffern ohne festes Muster: gehört zu keinem bestimmten Paketdienst (12 alfanumerische Zeichen passen auch zu Colis Privé). |
| `12345678` | 8 Ziffern: gleiches Format wie bei Mondial Relay, Colisweb oder [Heppner](carrier:heppner). |
| `123456789012` | 12 Ziffern: gleiches Format wie bei [FedEx](carrier:fedex), [Poste Italiane](carrier:poste-italiane), [BRT](carrier:brt) oder Colis Privé. |

Nennt der Onlineshop Paack als Zusteller, gehört die Nummer zu Paack – unabhängig von ihrer Länge; mehr dazu unter [Formate von Sendungsnummern](guide:tracking-number-formats). Ist Nummer oder Postleitzahl falsch, meldet die Seite `Order not found` bzw. `Incorrect order number or postal code`. Solange Paack dir noch keinen Eingang im Lager bestätigt hat, rät der Paack-Support, direkt beim Verkäufer nachzufragen.

## Was die Paack-Tracking-Status bedeuten

Paacks Sendungsverfolgung fasst den Weg in vier Hauptschritten zusammen (Bestelldaten erhalten, im Verteilzentrum von Paack, in Zustellung, zugestellt) und ergänzt im Verlauf die Zwischenschritte. Peek zeigt die Schritte mit seinen eigenen deutschen Statusbezeichnungen an; drei Zeilen bleiben auf Englisch:

| Anzeige in Peek | Was das für dich bedeutet |
| --- | --- |
| `Sendungsdaten erhalten` | Der Shop hat deine Bestelldaten an Paack übermittelt; das Paket selbst ist noch nicht bei Paack. |
| `Sendung übernommen` | Erster Scan des Pakets im Verteilzentrum von Paack. |
| `Unterwegs` | Paack hat die Sendung im Netz, sie ist aber noch nicht in der Zustelltour. |
| `Courier assigned` | Ein Fahrer wurde für die Tour eingeteilt. Die Meldung erscheint vor jedem neuen Zustellversuch erneut. |
| `In Zustellung` | Das Paket ist beim Fahrer und kommt im angezeigten Zeitfenster. |
| `Delivery attempt failed`, `Zustellproblem` | Diesmal nicht zugestellt: Der Fahrer konnte an der Adresse nicht zustellen, oder dein PaackGo Point war geschlossen, voll oder hat das Paket abgelehnt. |
| `New delivery attempt scheduled` | Paack plant einen neuen Zustellversuch im neu angezeigten Zeitfenster. Bucht Paack ihn exakt im Moment des Fehlversuchs, zählt er zu diesem Schritt und Peek blendet die Zusatzzeile aus. |
| `Abholbereit` | Liegt im PaackGo Point bereit; Paacks Seite nennt den letzten Abholtag. |
| `Zugestellt` | An der Adresse zugestellt oder im PaackGo Point abgeholt. |
| `Auf dem Rückweg zum Absender`, `Zurück an Absender` | Unterwegs zurück zum Shop oder dort bereits eingetroffen – etwa nach mehreren Fehlversuchen, abgelaufener Abholfrist, Annahmeverweigerung oder offener Zahlung im PaackGo Point. |
| `Problem mit der Sendung` | Störung am Paket, etwa Beschädigung oder Verlust im PaackGo Point: Wende dich an den Shop. |

Weitere typische Meldungen erklärt der Ratgeber [Sendungsstatus verstehen](guide:tracking-statuses).

## Lieferzeitfenster, verpasste Zustellung und PaackGo Points

**Zeitfenster.** Paacks Tracking-Seite nennt den voraussichtlichen Liefertag und das Zeitfenster in der Ortszeit der Lieferadresse (oder ein mehrtägiges Lieferfenster). Nach einem Fehlversuch gilt das dort gezeigte Fenster für den nächsten Versuch. Je nach vom Händler gebuchtem Tarif kannst du bestimmte Zeitfenster wählen; andernfalls lassen sich über den Paack-Chat Zustellhinweise für den Fahrer hinterlegen, ohne Garantie auf eine bestimmte Uhrzeit.

**Nicht angetroffen.** Jede Sendung im Paack-Netz umfasst **mindestens zwei Zustellversuche** (die genaue Anzahl richtet sich nach dem Vertrag zwischen Händler und Paack); der zweite folgt am nächsten Tag oder am nächsten Werktag – siehe auch [Zustellung verpasst](guide:missed-delivery). Kann Paack nicht an deiner Tür übergeben, gibt es zwei Alternativen, sofern du ihnen nicht vorab widersprochen hast: Abgabe beim **nächstgelegenen erreichbaren Nachbarn** (`Delivery to a neighbor`) oder beim **Hausmeister bzw. Concierge** der Wohnanlage (`Condo concierge`). Gab es beim ersten Versuch ein Adressproblem, schickt Paack eine E-Mail mit Änderungslink; auch über den Chat lässt sich die Adresse anpassen oder ergänzen, solange der Händler Änderungen erlaubt und das Paket noch nicht in Zustellung ist. Nach mehreren erfolglosen Versuchen geht die Sendung an den Händler zurück (deshalb ändert sich im Tracking mitunter die Lieferadresse). Papierzustellbelege stellt Paack nicht aus: Alle Empfangsbestätigungen werden elektronisch erfasst und sind über den Verkäufer einsehbar.

**PaackGo Points.** Das sind Paacks Partnershops zur Abholung. Sobald das Paket dort liegt, zeigt Paacks Seite Name und Anschrift des PaackGo Points, Abholcode, QR-Code und das letzte Abholdatum. Wird es nicht rechtzeitig abgeholt, geht es zurück an den Shop; mehr unter [Paket nicht abgeholt](guide:uncollected-parcel).

## Paack-Paket verspätet, nicht angekommen oder beschädigt

Dein Vertragspartner ist der Onlineshop: In der EU haftet der Verkäufer für Schäden an der Ware „ab dem Zeitpunkt des Versands bis zum Erhalt“ und muss – sofern nichts anderes vereinbart wurde – innerhalb von 30 Tagen liefern. Erstattungen, Ersatzlieferungen und Stornierungen laufen deshalb immer über den Shop:

- **Tracking hängt oder Lieferung verspätet:** Lies [Sendungsverfolgung aktualisiert sich nicht](guide:tracking-not-updating); kommt die Ware nicht fristgerecht, setze dem Händler eine angemessene Nachfrist, bevor du vom Kauf zurücktrittst.
- **Als zugestellt gemeldet, aber nichts da:** Frag Mitbewohner, Nachbarn und Concierge/Hausverwaltung und wende dich dann an den Verkäufer, der Zugriff auf Paacks elektronischen Abliefernachweis hat. Mehr unter [zugestellt, aber nicht erhalten](guide:delivered-not-received).
- **Beschädigt, verloren oder schon zurückgeschickt:** Ist ein Paket an den Händler zurückgegangen, befindet es sich nicht mehr bei Paack. Kontaktiere direkt den Shop; Hinweise bieten [beschädigtes Paket](guide:damaged-parcel) und [verlorenes Paket](guide:lost-parcel).

## Paack-Kontakt und Kundenservice

Stand auf Paacks englischsprachigem Hilfeportal im November 2026:

- **Online-Chat:** Unten rechts im [Paack-Hilfecenter](https://paackcustomerservice.freshdesk.com/en/support/home) sowie auf der [Tracking-Seite von Paack](https://paack.co/shipments/tracking?lng=en) – für Adresszusätze, freigegebene Adressänderungen und Hinweise an den Fahrer.
- **Automatische E-Mails:** Paack verschickt den Tracking-Link nach dem Versand sowie Benachrichtigungen bei Adressproblemen oder Verzögerungen.
- **Telefon oder Kunden-E-Mail:** Für den deutschsprachigen Raum (und auf der internationalen Hilfeseite) veröffentlicht Paack keine Empfänger-Telefonnummer und keine Support-E-Mail-Adresse; das Kontaktformular auf der Website richtet sich an Geschäftskunden.

## Paack-Pakete mit Peek verfolgen

[Peek](/) erkennt Sendungsnummern aus `PK` und 18 Ziffern – die Nummer auf Paketen, die Paack für [Evri](carrier:evri) International ausliefert – sofort als Paack und verfolgt sie ohne Postleitzahl. Bei Etikettennummern aus `5340` und 14 Ziffern setzt „Ändern“ Paack unter „Passt zu dieser Nummer“ an die erste Stelle vor andere Paketdienste mit 18-stelligen Nummern (in Belgien nach [bpost](carrier:bpost)). Auch bei einer SHEIN-Paketnummer (`C` oder `E`, sechsstelliges Datum aus Jahr, Monat und Tag sowie 8 Ziffern) steht Paack dort zur Auswahl, während Peek zuerst bei [Ecoscooting](carrier:ecoscooting) anfragt. Andere Paack-Nummern haben kein eigenes Muster, und ohne Postleitzahl kann Peek bei Paack nicht vorab nachfragen: Nummern mit Buchstaben bleiben meist ohne Zuordnung („Peek sucht den Paketdienst nach dem Hinzufügen.“), reine Ziffernfolgen werden dagegen Paketdiensten mit passender Länge zugeordnet (etwa [FedEx](carrier:fedex), [DHL](carrier:dhl), [Packeta](carrier:packeta), [Poste Italiane](carrier:poste-italiane) oder [BRT](carrier:brt)). Tippe auf „Ändern“ und wähle Paack, oder füge einen Link mit `tracking_number=` von `mydeliveries.paack.app` oder `paack.co/shipments/tracking` ein.

Trage anschließend das Feld „Postleitzahl der Lieferadresse“ ein. Es ist als optional markiert, weil eine `PK`-Nummer ohne auskommt; alle anderen Bestellungen zeigt Paack jedoch nur mit der passenden Postleitzahl der Lieferadresse an, und eine im Link enthaltene Postleitzahl wird nicht übernommen. Hast du zuvor schon eine Postleitzahl eingegeben, schlägt Peek sie unter dem Feld vor („… verwenden“), sowohl beim Hinzufügen als auch unter „Paketdienst ändern“. Die Postleitzahl geht an Paack und bei Bedarf an Tracking-Dienste; stimmt das Paar aus Nummer und Postleitzahl nicht überein, wirkt die Nummer wie unbekannt. Peek wandelt Buchstaben in Großbuchstaben um und entfernt Leerzeichen, Punkte und Bindestriche – falls nichts erscheint, prüfe die Nummer auch unverändert direkt auf Paacks Seite.

Du siehst den aktuellen Status, den Verlauf sowie Liefertag und Zeitfenster in der Ortszeit der Lieferadresse (bei mehrtägigen Fenstern den ersten und letzten Tag; bei spanischen Postleitzahlen mit `35` oder `38` gilt die Zeit der Kanarischen Inseln). Die Uhrzeiten im Verlauf erscheinen in deiner eigenen Zeitzone; auf peektracker.com und in der iPhone-App steht darunter der Hinweis „Uhrzeiten in deiner Zeitzone: …“. Wartet ein Paket im PaackGo Point, zeigt Peek „Abholbereit“ samt Name und Anschrift des Shops (aus dem Seitencode von Paack vorbereitet, auf einem echten Paket noch nicht beobachtet), jedoch niemals Abholcode, QR-Code oder Abholfrist – öffne dafür Paacks Tracking-Seite. Eine Abholung im PaackGo Point erscheint als „Zugestellt“. Paacks eigene Seite liefert keine Scan-Orte für die Karte und nennt keinen Folge-Paketdienst. Bei einer `PK`-Nummer ohne Postleitzahl stammt der Verlauf dagegen aus dem Tracker von Evri International – auf Englisch, mit Orten und Gewicht, aber ohne Zeitfenster; gibst du bei einer `PK`-Nummer zusätzlich die Postleitzahl an, fragt Peek zuerst Paacks eigene Seite ab, um exakte Zeitstempel und das Lieferfenster zu laden, und greift auf Evri International zurück, falls Paacks Seite das Paar nicht kennt. Shopname sowie dein Name und deine Anschrift werden weggelassen. Peek prüft alle 10 Minuten auf Updates, am Zustelltag ab 6:00 Uhr Ortszeit (oder eine halbe Stunde vor Beginn des Zeitfensters) alle 2 Minuten, solange der eigene Tracker von Paack oder Evri antwortet (über Tracking-Dienste alle 15 Minuten) – mit Mitteilungen für „Alle Updates“, „Nur wichtige“ oder „Nur am Liefertag“. Nach zwei Tagen ohne Neuigkeiten oder nach 12 Stunden auf „Etikett erstellt“ bzw. „Beim Paketdienst eingegangen“ prüft Peek stündlich.

## Häufige Fragen zur Paack-Sendungsverfolgung

### Was ist Paack und wie hängt Paack mit Colis Privé zusammen?

Paack ist ein auf planbare Zeitfenster spezialisierter Letzte-Meile-Zusteller für Onlineshops mit eigener Präsenz in Spanien, Frankreich, Italien und Portugal. Am 16. Oktober 2026 gab Colis Privé bekannt, dass sich Colis Privé und Paack nach dem Abschluss ihrer Partnerschaft im Sommer 2026 unter der gemeinsamen Marke **Paack** zusammenschließen, die ab Januar 2027 schrittweise ausgerollt wird (mit gemeinsamer Präsenz in Frankreich, Belgien, Luxemburg, Spanien und Portugal). Bis dahin laufen Zustellung und Sendungsverfolgung unverändert über den Paketdienst weiter, den dir der Shop nennt.

### Warum fragt Paack nach der Postleitzahl – und warum erscheint „Order not found“?

Paack gibt Sendungsdaten nur heraus, wenn Bestell- oder Etikettennummer und Postleitzahl der Lieferadresse zusammenpassen – das Paar wirkt wie ein Zugangsschlüssel. Tippst du beispielsweise deine deutsche Rechnungs-PLZ statt der spanischen oder italienischen Liefer-PLZ ein, antwortet Paack mit `Order not found`, als gäbe es die Bestellung nicht.

### Was bedeutet es, wenn Paack die Zustellung neu plant?

Paack unternimmt einen weiteren Zustellversuch, regulär am nächsten Tag oder nächsten Werktag. Nach einem Fehlversuch zeigt Peek `Delivery attempt failed` und übernimmt das neue Zeitfenster von Paacks Seite. Wird der neue Versuch im selben Moment wie der Fehlversuch angelegt, gehört er zu diesem Schritt und wird in Peek nicht doppelt aufgeführt; ein später gebuchter Termin erscheint als `New delivery attempt scheduled`. Scheitert die Anlieferung an einen geschlossenen oder vollen PaackGo Point, siehst du stattdessen `Zustellproblem`.

### Wie lange dauert die Lieferung mit Paack?

Paack bietet Händlern taggleiche Lieferung, Zustellung am nächsten Tag und feste Zeitfenster an; deinen konkreten Tag samt Zeitfenster siehst du nach der Übernahme auf Paacks Seite und in Peek. Ist das Paket noch nicht bei Paack eingetroffen, gilt die Lieferzusage des Onlineshops (in der EU mangels anderer Vereinbarung maximal 30 Tage).

:::sources
- [Paack: Track your order (Englisch)](https://paack.co/shipments/tracking?lng=en) – Eingabefelder `Order number` und `Postal code`
- [Paack Support: Home (Englisch)](https://paackcustomerservice.freshdesk.com/en/support/home) – Zweitversuch, Abgabe bei Nachbar oder Concierge, Adressänderung und Hinweise per Chat
- [Paack Support: Status of my order (Englisch)](https://paackcustomerservice.freshdesk.com/en/support/solutions/folders/44001208238) – geänderte Adresse bei Rücksendung, elektronische Zustellbelege statt Papierbeleg
- [Paack Support: Other questions (Englisch)](https://paackcustomerservice.freshdesk.com/en/support/solutions/folders/44001208240) – mindestens zwei Zustellversuche, E-Mail bei Adressproblemen
- [Paack Support: About Paack (Englisch)](https://paackcustomerservice.freshdesk.com/en/support/solutions/folders/44001208241) – lokale Präsenz in über 50 Städten in Spanien, Frankreich, Italien und Portugal, Same-Day- und Next-Day-Optionen
- [Colis Privé: Colis Privé and Paack unite under one brand: Paack (Englisch)](https://colisprive.fr/en/blog/colis-prive-and-paack-unite-under-one-brand-paack/) – Mitteilung vom 16. Oktober 2026: Zusammenschluss im Sommer 2026, gemeinsame Marke Paack ab Januar 2027, unverändertes Tracking
- [Your Europe: Versand und Lieferung](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_de.htm) – Händlerhaftung bis zum Erhalt und 30-Tage-Lieferfrist in der EU
- [Universal Parcel Scraper: Paack (Englisch)](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/paack) – Nummernformen (`PK`, `5340`, SHEIN), PLZ-Prüfung, Statusmeldungen in Peek, Zeitfenster und PaackGo Points
:::
