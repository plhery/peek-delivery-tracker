---
title: TNT Australia Sendungsverfolgung: Nummern, Status & Hilfe
description: Verfolge nationale TNT-Australia-Sendungen: 3 Buchstaben + 9 Ziffern, Depot-Statusmeldungen, Neuzustellung Mo–Fr und Support unter 13 11 50.
slug: tnt-australia-sendungsverfolgung
published: 2026-11-24
updated: 2026-11-24
---

TNT Australia (*TNT Express Australia*, in Integration mit FedEx Express) befördert Pakete, Dokumente und Paletten über seine Depots in ganz Australien ([tnt.com/express/en_au](https://www.tnt.com/express/en_au/site/home.html)). Internationale Sendungen laufen über einen eigenen Tracker (siehe [TNT](carrier:tnt)); für australische Inlandssendungen gibt es eine eigene Abfrage unter [tntexpress.com.au](https://www.tntexpress.com.au/interaction/Trackntrace.aspx).

Du kannst eine australische Inlandssendung auf [TNT Australia Track & Trace](https://www.tntexpress.com.au/interaction/Trackntrace.aspx) prüfen oder deine 12-stellige Frachtbriefnummer oben einfügen.

## TNT Australia Sendungsnummern im Überblick

Peek verfolgt nationale Sendungen von TNT Australia anhand einer 12-stelligen Nummer:

| Format (erfunden) | Was es ist |
| --- | --- |
| `SYD482917360` | **3 Großbuchstaben + 9 Ziffern** (insgesamt 12 Zeichen) — reguläre nationale TNT-Australia-Sendungsnummer für `tntexpress.com.au`. |

Da 3 Buchstaben gefolgt von 9 Ziffern auch zu Nummern von [Purolator](carrier:purolator) passen, fragt Peek beide Paketdienste nacheinander ab: in Kanada zuerst Purolator, sonst zuerst TNT Australia. Sobald TNT Australia antwortet, zeigt Peek „TNT Australia hat dieses Paket.“ Einige Präfixe, etwa `RWM` für Kuehne+Nagel, sind vom TNT-Australia-Format ausgenommen. Ein direkter Link wie `tntexpress.com.au/interaction/ASPs/trackref_detail.asp?con=...` oder `trackref.aspx?con=...` führt sofort zu TNT Australia.

Im [TNT Australia Help Center](https://www.tnt.com/express/en_au/site/support.html) wird die **9-stellige** Zahl auf der linken Rechnungsseite, etwa 3 cm unter der Kundennummer, als Sendungsnummer (*shipment / consignment number*) bezeichnet. Peeks TNT-Australia-Tracker verarbeitet jedoch ausschließlich 3 Buchstaben plus 9 Ziffern. Weitere Formate erklärt der Leitfaden zu [Sendungsnummern-Formaten](guide:tracking-number-formats).

## Bedeutung der TNT Australia Statusmeldungen

Die nationale Detailseite von TNT Australia zeigt vier Spalten — `Status`, `Date`, `Time` und `Depot` — sowie oben das Datum `Estimated Delivery by:`, sofern vorhanden.

| TNT Australia Wortlaut | Was er bedeutet |
| --- | --- |
| `Your shipment data is lodged` | Der Absender hat die Sendungsdaten elektronisch übermittelt; TNT Australia hat das Paket noch nicht abgeholt. |
| `We've collected your shipment` | TNT Australia hat die Sendung beim Absender abgeholt, noch vor dem ersten Depot-Scan. |
| `We're processing your shipment` | Scan in einem Abgangs-, Transit- oder Zustelldepot von TNT Australia. |
| `Your shipment's with a driver` | Unterwegs im Zustellfahrzeug zur Auslieferung an eine Wohn- oder Geschäftsadresse heute. |
| `New delivery address required` | Die Zustellung an die hinterlegte Adresse war nicht möglich; das Paket lagert im örtlichen Depot und wartet auf eine neue Adresse oder Abholung. |
| `We've delivered your shipment` | An der Empfängeradresse zugestellt. |
| `Your shipment was collected` | Der Empfänger hat das Paket persönlich im lagernden TNT-Depot abgeholt (nicht zu verwechseln mit `We've collected your shipment`, der Abholung beim Absender). |
| `Book-In Delivery Requested` / `Book-In Delivery Arranged` | Der Empfänger hat eine Terminzustellung angefragt und TNT hat sie bestätigt. Das sind Hinweise, keine Scans: Sie ändern die Sendungsstufe nicht, und die Bestätigung kann erst nach der Zustellung erscheinen. |

## Neuzustellung, Depotabholung und Verzögerungen

Das [TNT Australia Help Center](https://www.tnt.com/express/en_au/site/support.html) beschreibt die Schritte bei verpasster Zustellung oder Terminänderung:

- **Wenn der Fahrer eine Karte hinterlassen hat:** Buche über den TNT Re-delivery Service einen neuen Zustelltermin.
- **Wenn keine Karte vorliegt (oder noch kein Zustellversuch erfolgt ist):** Kontaktiere TNT Australia mit deiner Sendungsnummer, der vollständigen Lieferadresse und dem gewünschten Zustelldatum.
- **Neuzustellung nur Montag bis Freitag:** Laut TNT Australia sind erneute Zustellungen ausschließlich von Montag bis Freitag möglich.
- **`New delivery address required`:** Das Paket verbleibt im Depot aus der Spalte `Depot`, bis du eine andere Adresse angibst oder es abholst; wende dich an TNT Australia. Mehr Hilfe bieten die Leitfäden zu [verpasster Zustellung](guide:missed-delivery) und [stehengebliebener Sendungsverfolgung](guide:tracking-not-updating).

Wenn deine australische Bestellung mit einem anderen Inlandspaketdienst unterwegs ist, vergleiche die Sendungsverfolgung bei [Australia Post](carrier:australia-post), [Aramex Australia](carrier:aramex-au), [CouriersPlease](carrier:couriersplease) oder [Border Express](carrier:border-express).

## TNT Australia mit Peek verfolgen

Füge deine nationale Nummer aus 3 Buchstaben und 9 Ziffern oder einen `tntexpress.com.au`-Link in Peek ein. Ordnet Peek das Paket einem anderen Dienst zu, wähle beim Hinzufügen „Ändern“ oder auf der Paketseite „Paketdienst ändern“.

Peek ruft die nationale Detailseite von TNT Australia direkt über den Open-Source-[TNT-Australia-Adapter von universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/tnt-au) ab und wiederholt die Anfrage bei Netzwerk-Timeouts automatisch. Ausgelesen werden jeder Scan-Wortlaut, Depot, Uhrzeit sowie `Estimated Delivery by:` bis zur Zustellung. TNT Australia nennt Depotzeiten ohne Zeitzonenangabe: Peek datiert jeden Scan in der Zeitzone seines Depots (Sydney-Zeit für Sydney, Perth-Zeit für Perth usw.), einen Scan ohne Depot in der Zeitzone des Nachbar-Scans, sonst in Sydney-Zeit, und zeigt alle Scans in der Uhrzeit deines Geräts an. Gewicht, Dienstname und Statuscodes liefert die nationale Detailtabelle nicht.

Peek ordnet die Meldungen von TNT Australia klaren Stufen zu: „Etikett erstellt“ (`Your shipment data is lodged`), „Beim Paketdienst eingegangen“ (`We've collected your shipment`), „Unterwegs“ (`We're processing your shipment`), „In Zustellung“ (`Your shipment's with a driver`), „Problem gemeldet“ (`New delivery address required`) und „Zugestellt“ (`We've delivered your shipment` oder `Your shipment was collected`). Hinweise zur Terminbuchung (`Book-In Delivery Requested` und `Book-In Delivery Arranged`) bleiben im Verlauf sichtbar, setzen aber weder eine abgeschlossene Zustellung zurück noch ändern sie die aktuelle Stufe.

Solange du das Paket in Peek geöffnet hast oder am Zustelltag Benachrichtigungen aktiviert sind, fragt Peek bis zu alle 10 Minuten nach; an dem Tag, an dem TNT Australia `Your shipment's with a driver` meldet, ab 6 Uhr auf der Uhr des neuesten Scans alle 2 Minuten, solange der Tracker von TNT Australia antwortet. Andernfalls prüft Peek stündlich. Nach 12 Stunden mit lediglich „Etikett erstellt“ oder „Beim Paketdienst eingegangen“ oder nach zwei Tagen ohne Neuigkeiten prüft Peek stündlich, egal wie du die Sendung verfolgst, und nach sieben Tagen auf „Etikett erstellt“ alle 6 Stunden. Antwortet TNT Australia nicht und Peek verfolgt das Paket allein über Tracking-Dienste, prüft Peek stündlich, nach einem ruhigen Tag alle 3 Stunden und an dem Tag, an dem das Paket in Zustellung ist, bis zu alle 15 Minuten, solange du die Sendung geöffnet hast oder Benachrichtigungen aktiviert sind.

Ist der neueste Scan vier Tage alt und das Paket steht noch auf „Etikett erstellt“, „Beim Paketdienst eingegangen“ oder „Unterwegs“, markiert Peek es mit dem Tag dieses Scans, etwa „Keine Aktualisierung seit Mo 12. Okt“. Auf der Paketseite steht dann „Auf langen Flügen, beim Zoll oder übers Wochenende wird es oft still um ein Paket. Peek fragt weiter bei TNT Australia nach.“ samt dem Link „Hol dir Hilfe bei TNT Australia“ zum Help Center von TNT Australia. Nach drei Wochen ohne neuen Scan heißt es „Seit über drei Wochen nichts Neues. Ist das Paket noch nicht angekommen, frag beim Verkäufer nach. Peek fragt weiter bei TNT Australia nach.“

Wähle in den Peek-Benachrichtigungen „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“, um deine Hinweise festzulegen.

## Kontakt zu TNT Australia

- **Telefon (innerhalb Australiens):** `13 11 50` (auf der gesamten TNT-Australia-Website veröffentlicht)
- **Help Center & Neuzustellung:** [TNT Australia Support](https://www.tnt.com/express/en_au/site/support.html)
- **Nationales Tracking-Portal:** [tntexpress.com.au Track & Trace](https://www.tntexpress.com.au/interaction/Trackntrace.aspx) (verfolgt bis zu 10 nationale Sendungen, Sameday Local Jobs oder 1 elektronische Referenz gleichzeitig)
- **Website & internationales Tracking:** [tnt.com/express/en_au](https://www.tnt.com/express/en_au/site/home.html) und [Track & Trace Übersicht](https://www.tnt.com/express/en_au/site/shipping-tools/tracking.html)

Halte deine 12-stellige nationale Sendungsnummer und deine Lieferadresse bereit, wenn du `13 11 50` anrufst oder eine Neuzustellung an Werktagen vereinbarst.

## Häufig gestellte Fragen

### Was ist der Unterschied zwischen `We've collected your shipment` und `Your shipment was collected`?

`We've collected your shipment` steht am Anfang der Reise, wenn TNT Australia das Paket beim Absender abholt. `Your shipment was collected` steht am Ende, wenn der Empfänger ein im TNT-Depot gelagertes Paket persönlich abholt — Peek markiert die Sendung daher als „Zugestellt“.

### Warum erscheint `Book-In Delivery Arranged`, obwohl mein Paket bereits zugestellt wurde?

`Book-In Delivery Arranged` ist die Bestätigung einer Terminzustellung durch TNT Australia — ein Hinweis statt eines Scans, der auch erst nach der Zustellung gebucht werden kann. Peek belässt das Paket auf „Zugestellt“ und stuft es nicht zurück.

### Liefert TNT Australia am Wochenende zu oder neu zu?

Laut [TNT Australia Help Center](https://www.tnt.com/express/en_au/site/support.html) können umgebuchte Sendungen ausschließlich zwischen **Montag und Freitag** neu zugestellt werden.

### Meine TNT-Nummer hat 9 Ziffern ohne Buchstaben — warum erkennt Peek sie nicht als TNT Australia?

Peeks TNT-Australia-Tracker verlangt 3 Buchstaben gefolgt von 9 Ziffern, also das nationale Format auf `tntexpress.com.au`. Im Help Center bezeichnet TNT eine 9-stellige Zahl auf der Rechnung als Sendungsnummer; teste diese direkt auf [TNT Australia Track & Trace](https://www.tnt.com/express/en_au/site/shipping-tools/tracking.html) oder rufe `13 11 50` an.

:::sources
- [TNT Australia](https://www.tnt.com/express/en_au/site/home.html)
- [TNT Australia Help Center](https://www.tnt.com/express/en_au/site/support.html)
- [TNT Australia Track & Trace](https://www.tnt.com/express/en_au/site/shipping-tools/tracking.html)
- [TNT Express Australia Domestic Tracker](https://www.tntexpress.com.au/interaction/Trackntrace.aspx)
- [universal-parcel-scraper TNT Australia adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/tnt-au)
:::
