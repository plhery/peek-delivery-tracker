---
title: PigeonShip Sendungsverfolgung: Nummern, Status & Hilfe
description: Verfolge Same-Day- und Last-Mile-Lieferungen von PigeonShip in den USA und Kanada: 18-stellige Codes, Live-Status, Lieferzeiten und Support-Kontakte.
slug: pigeonship-sendungsverfolgung
published: 2026-11-24
updated: 2026-11-24
---

PigeonShip ist ein Zustelldienst für die letzte Meile in den USA und Kanada mit Büros in Provo (Utah) und Oakville (Ontario). Einzelhändler, E-Commerce-Versender und lokale Geschäfte nutzen PigeonShip für taggleiche, Next-Day- und regionale Lieferungen über Dienste wie **Pigeon Flex** (landesweite Zustellung in 2–5 Tagen), **Pigeon Express** (landesweite Zustellung in 2 Tagen), **Pigeon Connect** (Same-Day auf Abruf) und **Pigeon Flash** (geplante Same-Day-Lieferung).

PigeonShip arbeitet mit selbstständigen Fahrern und betreibt eigene Lieferprogramme für Unternehmen. Empfänger erhalten einen Tracking-Link auf `admin.pigeonship.com`, der den Auftragsstatus und die Zielzeit zeigt und nach Abschluss auf eine Bestätigungsseite wechselt.

## Format der Sendungsnummer von PigeonShip

Peek akzeptiert PigeonShip-Nummern mit 8 bis 30 Buchstaben und Ziffern, entfernt Leerzeichen und wandelt Buchstaben in Großbuchstaben um. Peek erkennt ein 18-stelliges Muster mit wechselnden Ziffern und Großbuchstaben:

- **18 alphanumerische Zeichen** nach dem Muster `Ziffer-Buchstabe-Ziffer-Buchstabe-3 Ziffern-Buchstabe-3 Ziffern-Buchstabe-2 Ziffern-Buchstabe-Ziffer-2 Buchstaben` (zum Beispiel `7P4K829H631N58R2TM`).

Tracking-Links von PigeonShip haben die Form `admin.pigeonship.com/shipping/tracking/package/<trackingNumber>/`. Fügst du diesen Link in Peek ein, liest Peek den Paketcode aus und fragt direkt PigeonShip ab. Bei einem 18-stelligen Code in diesem Format fragt Peek ausschließlich PigeonShip an und zeigt „PigeonShip hat dieses Paket.“, sobald eine Antwort eintrifft. Vergleiche mit weiteren US- und kanadischen Kurierformaten findest du im [Leitfaden zu Sendungsnummern-Formaten](guide:tracking-number-formats).

## So funktioniert das Live-Tracking von PigeonShip

Die Empfängerseite von PigeonShip ist eine Statusübersicht statt einer Scan-Historie. Solange der Auftrag aktiv ist, zeigt sie „In transit“ und den Fahrer auf einer Karte samt Zielzeit (`dueDate`), sofern eine festgelegt ist; im Hintergrund trägt der Auftrag einen Statuscode mit Bezeichnung wie `16` und `Assigned`. Erst bei Code `64` zeigt die Seite „Delivered“. Nach abgeschlossener Lieferung ersetzt PigeonShip die Seite unter derselben URL durch eine Abschlussseite mit `Your delivery has been completed successfully.` und einem Link zu den Zustellfotos.

| Status / Seite von PigeonShip | Bedeutung |
| --- | --- |
| Status `16` — `Assigned` | Der Auftrag liegt auf der Route eines Fahrers; die Seite zeigt „In transit“. |
| Zielzeit / `dueDate` | Die geplante Abschlusszeit auf aktiven Aufträgen vor der Ablage. |
| Status `64` / `Delivered` oder `Your delivery has been completed successfully.` | Der Fahrer hat die Zustellung abgeschlossen; über den Link von PigeonShip selbst siehst du das Zustellfoto. |
| `Page not found` (404) | Die Sendungsnummer ist im Live-System von PigeonShip nicht aktiv oder enthält einen Tippfehler. |

Da PigeonShip auf der Empfängerseite kein zeitgestempeltes Scan-Protokoll veröffentlicht, siehst du keine Zwischenscans aus Lagern oder Sortierzentren wie bei [UPS](carrier:ups), [USPS](carrier:usps), [Canada Post](carrier:canada-post) oder [Intelcom](carrier:intelcom). Stattdessen aktualisiert die Seite ihren Live-Zustand während der Fahrt und schaltet nach der Zustellung auf den Abschlussbildschirm um.

## Same-Day-Zustellung und Prüfungen bei Verzögerung

PigeonShip bietet Same-Day- und Next-Day-Lieferungen überall in den USA und Kanada an. Bewertungen auf der Website erwähnen eine SMS vor Ankunft des Fahrers und ein Foto nach der Ablage.

Steht dein Auftrag über die erwartete Lieferzeit hinaus auf `Assigned` oder meldet der Link `Page not found`:

- **Tippfehler prüfen:** Kontrolliere den vollständigen Code oder den gesamten `admin.pigeonship.com`-Link aus der E-Mail oder SMS des Händlers; für Nummern ohne aktive Seite antwortet PigeonShip mit `Page not found`.
- **PigeonShip kontaktieren:** Nutze das [Kontaktformular](https://www.pigeonship.com/contact-us), wenn ein Auftrag nach der Zielzeit auf „In transit“ stehen bleibt.
- **Zustellhinweise prüfen:** Kundenbewertungen auf der PigeonShip-Website erwähnen Fahrer, die den Etikettenhinweisen folgen, sowie ein Ablagefoto; öffne nach Abschluss den ursprünglichen Link auf `admin.pigeonship.com`, um die Fotos anzusehen. Weitere Schritte findest du unter [wenn die Sendungsverfolgung stehen bleibt](guide:tracking-not-updating) und im [Ratgeber zu verpassten Lieferungen](guide:missed-delivery).

## PigeonShip mit Peek verfolgen

Füge entweder deine PigeonShip-Sendungsnummer oder den vollständigen Link `admin.pigeonship.com/shipping/tracking/package/...` in Peek ein. Wählt Peek PigeonShip bei einem abweichenden Format nicht vor, tippe beim Hinzufügen auf **Ändern** oder auf der Paketseite auf **Paketdienst ändern**.

Peek ruft die serverseitig gerenderte Tracking-Seite von PigeonShip direkt über den Open-Source-[PigeonShip-Adapter von universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/pigeonship) ab. Ausgelesen werden Statuscode und -bezeichnung, ob zugestellt wurde sowie die Zielzeit (`dueDate`), die Peek als erwartete Lieferung anzeigt. Empfängername, Kartenkoordinaten des Fahrers, Fortschrittsbalken und Zustellfotos lässt Peek weg — öffne den Link von PigeonShip selbst, um das Foto zu sehen.

Weil PigeonShip nur den aktuellen Zustand anzeigt, fügt Peek bei jedem beobachteten Phasenwechsel eine datierte Zeile hinzu: Ein aktiver `Assigned`-Auftrag erscheint als **Unterwegs** mit erwarteter Lieferzeit, eine abgeschlossene Lieferung als **Zugestellt**.

Solange du das Paket in Peek geöffnet hast oder am Zustelltag Benachrichtigungen aktiviert sind, fragt Peek bis zu alle 10 Minuten nach, solange die Tracking-Seite von PigeonShip antwortet. Andernfalls prüft Peek stündlich. Da die Tracking-Seite von PigeonShip keinen eigenen Auslieferungsschritt zeigt, wechselt Peek nie auf seine 2-Minuten-Abfragen am Liefertag. Nach 12 Stunden mit lediglich „Etikett erstellt“ oder „Beim Paketdienst eingegangen“ oder nach zwei Tagen ohne Neuigkeiten fragt Peek stündlich nach, egal wie du die Sendung verfolgst, und nach sieben Tagen auf „Etikett erstellt“ alle 6 Stunden. Antwortet die Seite von PigeonShip nicht und Peek verfolgt das Paket allein über Tracking-Dienste, prüft Peek stündlich, nach einem ruhigen Tag alle 3 Stunden und an dem Tag, an dem das Paket in Zustellung ist, bis zu alle 15 Minuten, solange du die Sendung geöffnet hast oder Benachrichtigungen aktiviert sind.

Ist der neueste Scan vier Tage alt und das Paket steht noch auf „Etikett erstellt“, „Beim Paketdienst eingegangen“ oder „Unterwegs“, markiert Peek es mit dem Tag dieses Scans, etwa „Keine Aktualisierung seit Mo 12. Okt“. Auf der Paketseite steht dann „Auf langen Flügen, beim Zoll oder übers Wochenende wird es oft still um ein Paket. Peek fragt weiter bei PigeonShip nach.“, samt Link „Hol dir Hilfe bei PigeonShip“ zur Kontaktseite von PigeonShip. Nach drei Wochen ohne neuen Scan heißt es „Seit über drei Wochen nichts Neues. Ist das Paket noch nicht angekommen, frag beim Verkäufer nach. Peek fragt weiter bei PigeonShip nach.“

In den Peek-Benachrichtigungen kannst du **Jeden Scan**, **Wichtige Schritte** oder **Nur die Zustellung** wählen, um zu bestimmen, welche Updates dich benachrichtigen.

## Kontakt zu PigeonShip

- **Offizielles Kontaktformular:** [PigeonShip Contact Us](https://www.pigeonship.com/contact-us) (wähle United States, Canada oder Other und schildere dein Anliegen)
- **Offizielle Website:** [pigeonship.com](https://www.pigeonship.com/)
- **US-Büro:** PigeonShip USA, 1675 N Freedom Blvd Ste 9B, Provo, Utah 84604-6929
- **Kanada-Büro:** PigeonShip Canada, 405 Dundas St W, Oakville, ON L6M 5P9

## Häufige Fragen

### Warum zeigt das PigeonShip-Tracking keine Liste vergangener Scans an?

Die Live-Empfängerseite von PigeonShip bietet nur eine Zusammenfassung: Sie zeigt den aktuellen Auftragsstatus („In transit“ oder „Delivered“) und die Zielzeit statt einer mehrstufigen Lager-Scan-Historie.

### Wo kann ich das Zustellfoto von PigeonShip sehen?

Peek speichert oder zeigt keine Zustellfotos von PigeonShip. Sobald dein Paket als zugestellt markiert ist, öffne den ursprünglichen Link `admin.pigeonship.com/shipping/tracking/package/...` aus deiner E-Mail oder SMS, um die Abschlussseite und die Fotos von PigeonShip zu sehen.

### Was bedeutet der Status `Assigned` bei PigeonShip?

`Assigned` (Statuscode `16`) ist der interne Status hinter der PigeonShip-Seite, während der Auftrag auf einer Fahrerroute liegt; die Seite selbst zeigt „In transit“. Peek zeigt ihn als **Unterwegs**.

### Liefert PigeonShip sowohl in den USA als auch in Kanada aus?

Ja. PigeonShip betreibt Same-Day-, Next-Day- und landesweite Last-Mile-Lieferdienste in den USA und Kanada mit Büros in Provo (Utah) und Oakville (Ontario).

:::sources
- [PigeonShip](https://www.pigeonship.com/)
- [PigeonShip Contact Us](https://www.pigeonship.com/contact-us)
- [universal-parcel-scraper PigeonShip-Adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/pigeonship)
:::
