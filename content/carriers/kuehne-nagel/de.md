---
title: Kühne+Nagel Sendungsverfolgung — Nummern und Status
description: Kühne+Nagel Sendungsverfolgung (myKN) für Land-, Luft- und Seefracht: RWM-, RAPI- und 26A-Nummern, 90-Tage-Fenster, Zoll, Freight handover und Kontakt.
slug: kuehne-nagel-sendungsverfolgung
published: 2026-11-23
updated: 2026-11-27
---

Um eine See-, Luft- oder Landverkehrsfracht von **Kühne+Nagel** (fast 92.000 Mitarbeiter an fast 1.300 Standorten in fast 100 Ländern) zu verfolgen, gib deine `RWM`-, `RAPI`- oder `26A`-Sendungsnummer, deinen Frachtbrief (AWB/B/L), deine Kundenreferenz oder einen myKN-Link oben ein — oder suche auf [myKN Public Tracking](https://mykn.kuehne-nagel.com/public-tracking/). Die öffentliche Abfrage zeigt alle Sendungen der letzten 90 Tage samt Strecken-Meilensteinen, Zoll- und Dokumentenmeldungen.

## Sendungsnummern bei Kühne+Nagel

Peek nimmt Kühne+Nagel-Referenzen mit 6 bis 35 Buchstaben und Ziffern an, darunter mindestens eine Ziffer, und entfernt Leerzeichen und Bindestriche. myKN sucht die eigenen Sendungsnummern, Frachtbriefe und Buchungen von Kühne+Nagel in derselben Suche wie Kundenreferenzen. Drei Formen erkennt Peek:

- **`RWM` gefolgt von 9 Ziffern** (12 Zeichen, z. B. `RWM482917360`) — die Sendungsnummer einer Kühne+Nagel-Straßensendung; Peek ordnet sie allein Kühne+Nagel zu.
- **`RAPI` gefolgt von 8 Ziffern** (12 Zeichen, z. B. `RAPI48291736`) — eine Kühne+Nagel-Sendungsnummer, die Peek ebenfalls allein Kühne+Nagel zuordnet.
- **2 Ziffern, `A` und 9 Ziffern** (12 Zeichen, z. B. `26A482917360`) — die Sendungsnummer einer Straßensendung. Die Form passt auch zu [Australia Post](carrier:australia-post) und [Colis Privé](carrier:colis-prive); Peek fragt daher nacheinander Australia Post, Colis Privé und Kühne+Nagel (in der Schweiz und in Deutschland Kühne+Nagel an zweiter Stelle).

Bei Luft- und Seefracht geben Absender häufig zusätzlich Luftfrachtbrief-Nummern (AWB), Konnossementsnummern oder eigene Bestellreferenzen an. Fügst du einen Tracking-Link von `mykn.kuehne-nagel.com/public-tracking/shipments?query=...` ein, liest Peek die Nummer direkt aus und ordnet sie Kühne+Nagel zu. Einen allgemeinen Überblick über Aufbau und Präfixe anderer Anbieter findest du im [Ratgeber zu Sendungsnummern](guide:tracking-number-formats).

## Was die Statusmeldungen von Kühne+Nagel bedeuten

Das öffentliche myKN-Portal trennt den Verlauf in Strecken-Meilensteine an den einzelnen Stationen (Abholung, Abgangsterminal, Hub, Ankunftsterminal, Zustellort) und Informationsmeldungen zu Buchungen, Zollabfertigung und Dokumenten. Die öffentliche Oberfläche gibt Meilensteine überwiegend auf Englisch aus:

| Meldung bei Kühne+Nagel | Bedeutung |
| --- | --- |
| Booking submission / Booking confirmation | Der Auftrag ist bei Kühne+Nagel angelegt oder bestätigt, bevor die Ware abgeholt wurde. Eine spätere Buchungsänderung setzt eine laufende Sendung nicht zurück. |
| Ready for pickup / Arrival (am Abgangsort) | Die Ware steht beim Absender bereit oder der Abhol-Lkw ist eingetroffen; die Übernahme ist noch nicht abgeschlossen. |
| Pickup | Kühne+Nagel hat die Sendung beim Absender übernommen. |
| Arrival / Departure / Load on vehicle / Load on vessel | Physische Scans in Umschlaglagern, Cross-Docks, Bahnterminals, Flughäfen oder Seehäfen entlang der Hauptlaufstrecke. |
| Customs entry start / Customs duties payment / Entry documents required | Export- oder Importzollabfertigung läuft, oder Behörden fordern Unterlagen, Abgaben bzw. eine Prüfung an. |
| Customs entry release / Import customs entry release | Der Zoll oder die zuständige Behörde hat die Ware für den Weitertransport freigegeben. |
| In delivery | Die Sendung befindet sich auf dem Zustellfahrzeug oder der Lkw ist am Zielort eingetroffen. |
| Delivery / Freight collection / Proof of delivery available | Die Fracht wurde zugestellt oder am Terminal abgeholt; `Delivery with discrepancies` markiert eine erfolgte Zustellung mit vermerkter Abweichung oder Beschädigung. |
| Delivery attempt failed / Delivery not possible | Ein Zustellversuch war nicht erfolgreich, etwa wegen Annahmeverweigerung. |
| Shipment on hold / Delivery instructions pending / Missing/incomplete document | Die Sendung steht an einer Station oder beim Zoll still, bis fehlende Papiere, Zahlungen oder Weisungen vorliegen. |

Einige Hinweise werden leicht missverstanden:

- **Available at CY / Available at CFS / Ready for collection / Available at rail terminal** zeigen an, dass die Fracht am Containerterminal, Luftfrachtlager oder Bahnterminal zur Übergabe bereitliegt. Da häufig noch ein Nachlauf per Lkw folgt, gelten diese Scans weiterhin als unterwegs.
- **Freight handover** bedeutet Übergabe an einen Zollagenten oder nachgelagerten Frachtführer — noch keine Auslieferung an den Empfänger.
- **Reine Dokumentenmeldungen** wie `HAWB available`, `MAWB available`, `Transport document available`, `VGM closure`, `Delivery appointment submission` oder `Customs billing invoice available` dokumentieren Begleitprozesse, ohne den physischen Status zu verändern.
- **Leercontainer-Rückgabe und Fahrzeugabfahrt am Zielort** folgen erst nach erfolgter Zustellung und belassen den Status bei zugestellt.

## Zustellung, Zoll und längere Scanpausen

`Delivery appointment submission` hält einen für später vereinbarten Zustelltermin fest und ist kein Scan: Erst `In delivery` oder die Ankunft (`Arrival`) des Lkw an der Lieferadresse setzt die Sendung auf „In Zustellung“.

Meldungen wie `Awaiting consignee's feedback`, `Awaiting consignee's payment` oder `Entry documents required` bedeuten, dass der Zoll auf eine Antwort, eine Zahlung oder Unterlagen des Empfängers wartet; bei `Unable to file customs entry - missing details or documentation` kann die Zollanmeldung erst danach eingereicht werden. Wie Verzollung und Gebühren ablaufen, erklärt der [Zoll-Ratgeber](guide:customs).

Auf myKN gezeigte geplante Termine sind Prognosen und noch keine tatsächlichen Scans. Weitere Tipps zu ruhigen Sendungsverläufen stehen im Ratgeber [wenn sich die Sendungsverfolgung nicht aktualisiert](guide:tracking-not-updating).

## Kühne+Nagel mit Peek verfolgen

Fügst du eine `RWM`- oder `RAPI`-Nummer oder einen `mykn.kuehne-nagel.com/public-tracking/shipments?query=...`-Link ein, erkennt Peek Kühne+Nagel sofort. Bei einer `26A...`-Nummer fragt Peek die oben genannten Paketdienste nacheinander und zeigt „Kuehne+Nagel hat dieses Paket.“, sobald Kühne+Nagel antwortet. Bei einem Frachtbrief oder einer Kundenreferenz ohne diese Formen wählst du Kühne+Nagel beim Hinzufügen über **Ändern** oder später über **Paketdienst ändern** aus.

Peek ruft die öffentliche myKN-Suche, die Strecken-Meilensteine und die Informationsmeldungen direkt über den quelloffenen [universal-parcel-scraper Kühne+Nagel-Adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/kuehne-nagel) ab. Dabei übernimmt Peek ausschließlich tatsächlich erreichte Meilensteine (keine bloßen Plantermine) und liest bei Verfügbarkeit das Gewicht in Kilogramm und das Zielland aus. Findet die Suche die Sendung unter einer Kundenreferenz, zeigt Peek die eigene Kühne+Nagel-Sendungsnummer neben deiner unter „Kuehne+Nagel führt die Sendung unter“; passt eine Referenz zu mehreren Sendungen, kann Peek deine nicht bestimmen. Kennt das scannende System die Zeitzone, kommt die Uhrzeit mit ihrem Versatz, und Peek übernimmt den genauen Zeitpunkt. Sonst datiert Peek den Scan nach der Zeitzone seines Orts (Berliner Zeit für Hamburg, Zürcher Zeit für Basel), einen Scan ohne Ort nach der Zeitzone des Nachbarscans, sonst nach Zürcher Zeit, und zeigt alle auf der Uhr deines Geräts.

Peek ordnet die Kühne+Nagel-Meilensteine übersichtlich zu: **Etikett erstellt** für Buchungen und Abholbereitschaft, **Beim Paketdienst eingegangen** nach der Abholung, **Unterwegs** für Terminal-, Hafen-, Bahn- und Übergabescans, **Beim Zoll** für Export-/Importabfertigung und Behördenprüfungen, **In Zustellung** ab `In delivery`, **Zustellversuch** bei gescheiterter Auslieferung, **Problem gemeldet** bei Sperren, fehlenden Dokumenten oder Stornierungen sowie **Zugestellt** nach Zustellung, Selbstabholung oder Vorliegen des Abliefernachweises. Nachträgliche Dokumenten- oder Zollmeldungen setzen eine zugestellte Sendung nicht mehr zurück.

Solange du die Sendung in Peek geöffnet hast, oder an ihrem Zustelltag mit aktivierten Benachrichtigungen, prüft Peek bis zu alle 10 Minuten; an dem Tag, an dem Kühne+Nagel `In delivery` oder die Ankunft (`Arrival`) des Lkw an der Lieferadresse zeigt, alle 2 Minuten ab 6 Uhr nach der Uhr des neuesten Scans, solange die Kühne+Nagel-Sendungsverfolgung antwortet; sonst stündlich. Nach 12 Stunden im Status „Etikett erstellt“ oder „Beim Paketdienst eingegangen“ oder nach zwei Tagen ohne Neuigkeiten prüft Peek stündlich, egal wie du die Sendung verfolgst, und nach sieben Tagen weiterhin bei „Etikett erstellt“ alle 6 Stunden. Antwortet die Kühne+Nagel-Sendungsverfolgung nicht und verfolgt Peek die Sendung nur über Tracking-Dienste, prüft Peek stündlich, nach einem Tag ohne Neuigkeiten alle 3 Stunden und am Tag der Zustellung bis zu alle 15 Minuten.

Bleibt eine Sendung bei „Etikett erstellt“, „Beim Paketdienst eingegangen“ oder „Unterwegs“ vier Tage lang ohne neuen Scan, markiert Peek sie mit dem Tag dieses letzten Scans (zum Beispiel „Keine Aktualisierung seit Mo 12. Okt“) und zeigt „Auf langen Flügen, beim Zoll oder übers Wochenende wird es oft still um ein Paket. Peek fragt weiter bei Kuehne+Nagel nach.“ Nach mehr als drei Wochen ohne neuen Scan erscheint: „Seit über drei Wochen nichts Neues. Ist das Paket noch nicht angekommen, frag beim Verkäufer nach. Peek fragt weiter bei Kuehne+Nagel nach.“

In den Benachrichtigungseinstellungen kannst du zwischen **Jeden Scan**, **Wichtige Schritte** und **Nur die Zustellung** wählen.

## Kontakt zu Kühne+Nagel

Auf seiner Website bietet Kühne+Nagel eine Standortsuche und ein Kontaktformular:

- **Offizielle Websites:** [Kühne+Nagel Deutschland](https://www.kuehne-nagel.com/de), [Kühne+Nagel Schweiz](https://www.kuehne-nagel.com/ch) und [myKN Public Tracking](https://mykn.kuehne-nagel.com/public-tracking/)
- **Standortsuche:** [Kühne+Nagel-Standorte weltweit](https://www.kuehne-nagel.com/de/unternehmen/standorte) für das nächste Büro
- **Kontaktformular:** [Sprechen Sie mit unseren Experten](https://www.kuehne-nagel.com/de/kontakt/experten-kontaktieren)
- **Zentrale Deutschland (Impressum):** Kühne + Nagel (AG & Co.) KG, Wilhelm-Kaisen-Brücke 1, 28195 Bremen, Tel. +49 (0)421 3605-0, E-Mail `info.bremen@kuehne-nagel.com` ([Impressum](https://www.kuehne-nagel.com/de/unternehmen/impressum))
- **Andere Speditionen:** Falls ein Teil deiner Lieferung über eine andere Spedition läuft, sieh dir auch [DHL Freight](carrier:dhl-freight) oder [DSV](carrier:dsv) an.

## Häufige Fragen

### Wie verfolge ich eine Kühne+Nagel-Sendung mit RWM-, RAPI- oder 26A-Nummer?

Füge eine 12-stellige `RWM`- oder `RAPI`-Nummer oder einen Link von `mykn.kuehne-nagel.com/public-tracking/shipments?query=…` direkt oben ein — Peek erkennt Kühne+Nagel sofort. Bei `26A…` prüft Peek Kühne+Nagel automatisch mit; bei Luftfrachtbriefen (AWB) oder Kundenreferenzen wählst du Kühne+Nagel über **Ändern** aus.

### Warum findet myKN meine Kühne+Nagel-Sendungsnummer nicht?

Die öffentliche Suche auf `mykn.kuehne-nagel.com` deckt nur Sendungen ab, die in den letzten **90 Tagen** gestartet wurden. Prüfe die Nummer auf Tippfehler, warte bei frischen Buchungen auf den ersten erreichten Meilenstein und bitte bei mehrdeutigen Kundenreferenzen den Absender um die eindeutige `RWM`- oder `RAPI`-Nummer.

### Was bedeutet „Freight handover“ oder „Service complete“ bei Kühne+Nagel?

`Freight handover` heißt, dass Kühne+Nagel die Fracht an einen Zollagenten oder nachgelagerten Frachtführer übergeben hat — die Sendung ist weiter unterwegs. `Service complete` schließt die beauftragte Kühne+Nagel-Teilstrecke ab (etwa bis zum Zielhafen); für den Nachlauf bis zur Tür kann eine eigene Speditionsnummer gelten.

### Warum erscheinen geplante Termine aus myKN nicht als Scans in Peek?

Auf der öffentlichen myKN-Seite stehen Prognosetermine neben tatsächlich erreichten Meilensteinen. Peek übernimmt nur real erreichte Meilensteine (und keine bloßen Plantermine oder `Delivery appointment submission`), damit Prognosen nicht wie abgeschlossene Bewegungen wirken.

### Wie erreiche ich den Kühne+Nagel-Kundenservice in Deutschland oder der Schweiz?

Nutze die [Standortsuche](https://www.kuehne-nagel.com/de/unternehmen/standorte) für das zuständige Büro oder das Kontaktformular [Experten kontaktieren](https://www.kuehne-nagel.com/de/kontakt/experten-kontaktieren). Die deutsche Zentrale in Bremen erreichst du laut Impressum unter +49 (0)421 3605-0 oder `info.bremen@kuehne-nagel.com`.

:::sources
- [Kühne+Nagel Deutschland](https://www.kuehne-nagel.com/de)
- [Kühne+Nagel Schweiz](https://www.kuehne-nagel.com/ch)
- [myKN Public Tracking](https://mykn.kuehne-nagel.com/public-tracking/)
- [Kühne+Nagel-Standorte weltweit](https://www.kuehne-nagel.com/de/unternehmen/standorte)
- [Kühne+Nagel Experten kontaktieren](https://www.kuehne-nagel.com/de/kontakt/experten-kontaktieren)
- [Kühne+Nagel Deutschland Impressum](https://www.kuehne-nagel.com/de/unternehmen/impressum)
- [universal-parcel-scraper Kühne+Nagel-Adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/kuehne-nagel)
:::
