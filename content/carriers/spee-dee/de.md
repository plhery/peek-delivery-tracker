---
title: Spee-Dee Delivery Sendungsverfolgung: SP-Codes & Hilfe
description: Verfolge Spee-Dee-Delivery-Pakete im US-Mittleren Westen: 18- und 20-stellige SP-Barcodes, wiederverwendete Nummern, Liefergebiet und 800-862-5578.
slug: spee-dee-sendungsverfolgung
published: 2026-11-24
updated: 2026-11-24
---

Spee-Dee Delivery (*Spee-Dee Delivery Service, Inc.*) ist ein Familienunternehmen in zweiter Generation, das Don Weeres 1978 als Abruf-Lieferdienst gründete. Das Unternehmen bezeichnet sich als unangefochtenen Marktführer für die letzte Meile im Mittleren Westen der USA mit einem Next-Day-Netz und bietet zudem LTL-Stückgut, Lagerung, Annahmestellen für Laufkundschaft und EZR Returns ([speedeedelivery.com](https://speedeedelivery.com/)).

Du kannst eine Sendung auf [Spee-Dees Seite „Track a Shipment“](https://speedeedelivery.com/track-a-shipment/) prüfen oder deinen `SP`-Barcode oben einfügen.

## Spee-Dee Sendungsnummern im Überblick

Peek kennt die Barcodes von Spee-Dee als `SP` gefolgt von 16 oder 18 Ziffern:

| Format (erfunden) | Was es ist |
| --- | --- |
| `SP4829173605148291` | `SP` + 16 Ziffern (insgesamt 18 Zeichen) — regulärer Spee-Dee-Barcode; beachte, dass Spee-Dee 16-stellige Nummern im Laufe der Zeit wiederverwendet. |
| `SP482917360514829173` | `SP` + 18 Ziffern (insgesamt 20 Zeichen) — längeres Spee-Dee-Barcode-Format. |

Beide `SP`-Formate weisen eindeutig auf Spee-Dee hin und überschneiden sich nicht mit landesweiten US-Diensten wie [UPS](carrier:ups), [FedEx](carrier:fedex) oder [USPS](carrier:usps). Hast du einen Link wie `speedeedelivery.com/...?tracking=...`, liest Peek die `SP`-Nummer direkt aus. Weitere US-Formate erklärt der Leitfaden zu [Sendungsnummern-Formaten](guide:tracking-number-formats).

## Liefergebiet von Spee-Dee im Mittleren Westen

Laut [Spee-Dees Service Area Maps](https://speedeedelivery.com/services/service-area-maps/) liefert Spee-Dee regulär auf dem Landweg in **alle** Orte von sechs Bundesstaaten des Mittleren Westens sowie in ausgewählte Städte und Postleitzahlen (*ZIP Codes*) von vier Nachbarstaaten:

- **Flächendeckend:** Illinois, Iowa, Minnesota, North Dakota, South Dakota und Wisconsin.
- **Ausgewählte Städte und ZIP Codes:** Kansas (einschließlich der erweiterten ZIPs `660` und `666`), Michigan, Missouri (einschließlich der erweiterten ZIPs `634–635`, `644–646` und `650–653`) und Nebraska.

Für Laufzeiten zeigt die Seite *Service Area Maps* eine *Days in Transit*-Karte für jede Absender-Postleitzahl, und das *Zip Transit File* nennt die Laufzeiten für jeden bedienten ZIP Code.

## Bedeutung der Spee-Dee Statusmeldungen

Die Verlaufstabelle von Spee-Dee zeigt drei Spalten — `Date / Time`, `Location` und `Activity` — beginnend mit dem neuesten Eintrag, sowie oben eine Zusammenfassung mit `Status:`, `Delivered on:`, `Signed by:` und `Delivered to:` nach Abschluss.

| Spee-Dee Aktivität | Was sie bedeutet |
| --- | --- |
| `ARRIVAL SCAN` | Scan in einem Spee-Dee-Terminal oder Sortierzentrum im Midwest-Straßennetz. |
| `OUT FOR DELIVERY` *(oft gefolgt von einer Tourennummer in Klammern)* | Heute in einen örtlichen Spee-Dee-Zustellwagen zur Auslieferung geladen. |
| `Delivered` | Zustellung abgeschlossen; die Zusammenfassung auf Spee-Dees eigener Seite nennt zudem Zustellzeit, Lieferadresse und Unterschrift des Empfängers, sofern erfasst. |
| `No packages were found matching the barcode supplied.` | Der Barcode wurde noch nicht im System von Spee-Dee gescannt oder enthält einen Tippfehler. |

### Wiederverwendete 16-stellige Barcodes

Spee-Dee vergibt ältere 16-stellige `SP`-Barcodes erneut für neue Sendungen. Auf Spee-Dees eigener Verlaufsseite können daher Zeilen eines früher zugestellten Pakets unterhalb der Scans deines aktuellen Pakets stehen. Prüfe bei einem wiederverwendeten Barcode auf Spee-Dees Website die Daten oben in der Tabelle und lies nicht weiter, sobald ein früheres `Delivered` aus einer alten Sendung erscheint.

## Verzögerungen, fehlende Scans und Reklamationen

Einige Hinweise helfen, wenn der Verlauf ungewöhnlich wirkt:

- **Noch keine Scans:** Zeigt Spee-Dees Seite `No packages were found matching the barcode supplied.`, hat Spee-Dee das Paket noch nicht gescannt oder der Barcode ist falsch. Prüfe die Nummer, frage den Verkäufer nach der Übergabe an Spee-Dee oder lies [stehengebliebene Sendungsverfolgung](guide:tracking-not-updating).
- **Anfragelimits bei häufigem Neuladen:** Der Tracking-Server von Spee-Dee begrenzt Abfragen pro Minute und meldet bei schnellen Wiederholungen vorübergehend HTTP 503. Kurz warten oder Peek automatisch prüfen lassen umgeht das Limit.
- **Reklamationen bei Nichtzustellung oder Schaden:** Das [Kundenservice-Formular von Spee-Dee](https://speedeedelivery.com/customer-service/) bietet eigene Kategorien **Claims – Non-Delivery** und **Claims – Damage** und bittet bei Tracking-Anfragen um die **vollständige Lieferadresse**. Siehe auch [zugestellt, aber nicht erhalten](guide:delivered-not-received), falls ein Paket als zugestellt gilt, aber fehlt.

## Spee-Dee mit Peek verfolgen

Füge einen 18-stelligen (`SP` + 16 Ziffern) oder 20-stelligen (`SP` + 18 Ziffern) Spee-Dee-Barcode oder einen `speedeedelivery.com`-Link in Peek ein — Peek erkennt Spee-Dee sofort. Falls du den Paketdienst wechseln willst, nutze beim Hinzufügen „Ändern“ oder auf der Paketseite „Paketdienst ändern“.

Peek ruft die Verlaufsseite von Spee-Dee direkt über den Open-Source-[Spee-Dee-Adapter von universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/spee-dee) ab und weicht auf ein entferntes Netz aus, wenn der Host von Spee-Dee direkte Verbindungen ablehnt. Bei einem **wiederverwendeten Barcode** behält Peek nur die Zeilen seit der vorherigen Zustellung mindestens einen Tag zuvor. HTML-Debug-Kommentare und die Tourennummer nach `OUT FOR DELIVERY` werden entfernt; Stadt oder Terminal sowie Uhrzeit jedes Scans bleiben erhalten. Spee-Dee nennt Ortszeiten ohne Offset: Peek datiert jeden Scan auf die Zeitzone seines Ortes (Chicago-Zeit für St. Cloud, Fargo, Milwaukee oder Sioux Falls, Detroit-Zeit für Detroit), einen Scan ohne Ort nach dem Nachbar-Scan, sonst auf New-York-Zeit, und zeigt jede Zeit auf der Uhr deines Geräts. Ein voraussichtliches Lieferdatum und das Gewicht nennt der Spee-Dee-Tracker nicht.

Peek ordnet `ARRIVAL SCAN` als „Unterwegs“, `OUT FOR DELIVERY` als „In Zustellung“ und `Delivered` als „Zugestellt“ ein und stuft sonstige Aktivitäten nach seinen gemeinsamen Statusregeln ein.

Solange du das Paket in Peek geöffnet hast oder am Zustelltag Benachrichtigungen aktiviert sind, fragt Peek bis zu alle 10 Minuten nach; an dem Tag, an dem Spee-Dee `OUT FOR DELIVERY` zeigt, ab 6 Uhr auf der Uhr des neuesten Scans alle 2 Minuten — oder alle 5 Minuten, wenn die letzte Prüfung einen Browser öffnen musste —, solange der Spee-Dee-Tracker antwortet. Andernfalls prüft Peek stündlich. Nach 12 Stunden mit lediglich „Etikett erstellt“ oder „Beim Paketdienst eingegangen“ oder nach zwei Tagen ohne Neuigkeiten prüft Peek unabhängig von deiner Nutzung stündlich, und nach sieben Tagen auf „Etikett erstellt“ alle 6 Stunden. Antwortet Spee-Dee nicht und Peek verfolgt das Paket allein über Tracking-Dienste, prüft Peek stündlich, nach einem ruhigen Tag alle 3 Stunden und an dem Tag, an dem das Paket in Zustellung ist, bis zu alle 15 Minuten, solange du die Sendung geöffnet hast oder Benachrichtigungen aktiviert sind.

Ist der neueste Scan vier Tage alt und das Paket steht noch auf „Etikett erstellt“, „Beim Paketdienst eingegangen“ oder „Unterwegs“, markiert Peek es mit dem Tag dieses Scans, etwa „Keine Aktualisierung seit Mo 12. Okt“. Auf der Paketseite steht dann „Auf langen Flügen, beim Zoll oder übers Wochenende wird es oft still um ein Paket. Peek fragt weiter bei Spee-Dee nach.“ samt dem Link „Hol dir Hilfe bei Spee-Dee“ zur Kundenservice-Seite. Nach drei Wochen ohne neuen Scan heißt es „Seit über drei Wochen nichts Neues. Ist das Paket noch nicht angekommen, frag beim Verkäufer nach. Peek fragt weiter bei Spee-Dee nach.“

In Peeks Benachrichtigungseinstellungen kannst du „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“ wählen.

## Kontakt zu Spee-Dee Delivery

Auf seiner [Kundenservice-Seite](https://speedeedelivery.com/customer-service/) hebt Spee-Dee hervor, dass Anrufer direkt mit einer Person sprechen statt mit einem automatisierten Telefonmenü:

- **Gebührenfreier Kundenservice:** `800-862-5578`
- **Lokale Rufnummer:** `320-251-6697`
- **Fax:** `320-251-1846` (LTL-Fax: `320-240-7995`)
- **Online-Kontakt- und Reklamationsformular:** [Spee-Dee Customer Service](https://speedeedelivery.com/customer-service/) (bei Tracking-Fragen `SP`-Barcode und vollständige Lieferadresse angeben)
- **Hausanschrift:** Spee-Dee Delivery Service Inc., 4101 Clearwater Road, St. Cloud, MN 56301
- **Postanschrift:** P.O. Box 1417, St. Cloud, MN 56302
- **Unternehmen & Sendungsverfolgung:** [Über Spee-Dee](https://speedeedelivery.com/about-us/) und [Track a Shipment](https://speedeedelivery.com/track-a-shipment/)

## Häufig gestellte Fragen

### Warum zeigt meine Spee-Dee-Seite eine Zustellung von vor Monaten an?

Spee-Dee verwendet 16-stellige `SP`-Barcodes im Laufe der Zeit erneut, sodass auf der Originalwebseite eine ältere, abgeschlossene Sendung unter den Scans deines aktuellen Pakets stehen kann. Peek erkennt diese Grenze automatisch und blendet Zeilen früherer Sendungen aus.

### Welche US-Bundesstaaten bedient Spee-Dee Delivery?

Spee-Dee deckt Illinois, Iowa, Minnesota, North Dakota, South Dakota und Wisconsin vollständig ab sowie ausgewählte Städte und ZIP Codes in Kansas, Michigan, Missouri und Nebraska.

### Zeigt Spee-Dee Delivery ein geschätztes Lieferdatum auf der Tracking-Seite an?

Nein. Die Tracking-Seite von Spee-Dee zeigt Ankunftsscans im Terminal, `OUT FOR DELIVERY` und Angaben zur Zustellung ohne geschätztes Lieferdatum. Übliche Laufzeiten nach Postleitzahl findest du auf [Spee-Dees Service Area Maps](https://speedeedelivery.com/services/service-area-maps/).

### Welche Angaben sollte ich bei Kontakt mit Spee-Dee bereithalten?

Halte deine vollständige 18- oder 20-stellige `SP`-Sendungsnummer sowie die vollständige Lieferadresse bereit — Spee-Dees Kundenservice-Formular verlangt bei Tracking-Anfragen ausdrücklich die volle Lieferadresse.

:::sources
- [Spee-Dee Delivery](https://speedeedelivery.com/)
- [Spee-Dee — About Us](https://speedeedelivery.com/about-us/)
- [Spee-Dee — Track a Shipment](https://speedeedelivery.com/track-a-shipment/)
- [Spee-Dee — Service Area Maps](https://speedeedelivery.com/services/service-area-maps/)
- [Spee-Dee — Customer Service](https://speedeedelivery.com/customer-service/)
- [universal-parcel-scraper Spee-Dee adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/spee-dee)
:::
