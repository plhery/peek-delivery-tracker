---
title: Kühne+Nagel Sendungsverfolgung — Nummern und Status
description: Verfolge Kühne+Nagel Landverkehr-, Luft- und Seefrachtsendungen per Referenz oder myKN-Link. Nummernformate, Statusmeldungen, Zoll und Kontakt.
slug: kuehne-nagel-sendungsverfolgung
published: 2026-11-23
updated: 2026-11-23
---

Kühne+Nagel ist ein weltweit tätiges Speditions- und Logistikunternehmen mit Sitz in der Schweiz und Standorten in Deutschland, Österreich, der Schweiz und über 100 weiteren Ländern. Privat- und Geschäftskunden begegnen Kühne+Nagel vor allem bei Paletten- und Stückgutsendungen im Landverkehr, bei sperrigen Onlinebestellungen sowie bei internationaler Luft- und Seefracht über die öffentliche Sendungsverfolgung von [myKN Public Tracking](https://mykn.kuehne-nagel.com/public-tracking/).

Die öffentliche myKN-Abfrage deckt Sendungen ab, die in den letzten 90 Tagen gestartet wurden. Da Kühne+Nagel Fracht über Umschlaglager, Flughäfen, Containerterminals und Häfen bewegt, zeigt eine Sendung sowohl physische Strecken-Meilensteine als auch gesonderte Buchungs-, Zoll- und Dokumentenmeldungen.

## Sendungsnummern bei Kühne+Nagel

Die öffentliche Suche von Kühne+Nagel akzeptiert Referenzen mit 6 bis 35 Buchstaben und Ziffern und ignoriert Groß- und Kleinschreibung, Leerzeichen sowie Bindestriche. Gesucht wird immer nach vollständigen Referenzen — sowohl nach eigenen Kühne+Nagel-Tracking-IDs als auch nach Kunden- oder Auftragsreferenzen. Drei Nummernformate tauchen bei Stückgut- und Straßensendungen besonders häufig auf:

- **`RWM` gefolgt von 9 Ziffern** (12 Zeichen, z. B. `RWM482917360`) — eine eindeutige Referenz aus dem Kühne+Nagel-Landverkehr.
- **`RAPI` gefolgt von 8 Ziffern** (12 Zeichen, z. B. `RAPI48291736`) — eine weitere eindeutige Kühne+Nagel-Sendungsnummer.
- **2 Ziffern, `A` und 9 Ziffern** (12 Zeichen, z. B. `26A482917360`) — ein 12-stelliges Format, das sich mit [Australia Post](carrier:australia-post) und [Colis Privé](carrier:colis-prive) überschneidet; Peek prüft daher, welcher Dienstleister die Sendung tatsächlich führt.

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
| Delivery attempt failed / Delivery not possible | Ein Zustellversuch war nicht erfolgreich, etwa wegen Annahmeverweigerung oder Umleitung. |
| Shipment on hold / Delivery instructions pending / Missing/incomplete document | Die Sendung steht an einer Station oder beim Zoll still, bis fehlende Papiere, Zahlungen oder Weisungen vorliegen. |

Einige Hinweise werden leicht missverstanden:

- **Available at CY / Available at CFS / Ready for collection / Available at rail terminal** zeigen an, dass die Fracht am Containerterminal, Luftfrachtlager oder Bahnterminal zur Übergabe bereitliegt. Da häufig noch ein Nachlauf per Lkw folgt, gelten diese Scans weiterhin als unterwegs.
- **Freight handover** bedeutet Übergabe an einen Zollagenten oder nachgelagerten Frachtführer — noch keine Auslieferung an den Empfänger.
- **Reine Dokumentenmeldungen** wie `HAWB available`, `MAWB available`, `Transport document available`, `VGM closure`, `Delivery appointment submission` oder `Customs billing invoice available` dokumentieren Begleitprozesse, ohne den physischen Status zu verändern.
- **Leercontainer-Rückgabe und Fahrzeugabfahrt am Zielort** folgen erst nach erfolgter Zustellung und belassen den Status bei zugestellt.

## Zustellung, Zoll und längere Scanpausen

Kühne+Nagel befördert vor allem Stückgut, Paletten sowie Luft- und Seefracht statt klassischer Briefkastenpakete. Im europäischen Landverkehr vereinbart das zuständige Empfangsdepot bei Privat- oder Firmenadressen häufig vorab einen Zustelltermin (Avisierung). Die Meldung `Delivery appointment submission` hält diese Terminabsprache fest; erst `In delivery` zeigt an, dass die Ware tatsächlich auf dem Zustellfahrzeug rollt.

Bei Sendungen aus Nicht-EU-Ländern übernimmt ein Zollagent die Einfuhranmeldung. Meldungen wie `Awaiting consignee's feedback`, `Awaiting consignee's payment`, `Entry documents required` oder `Unable to file customs entry - missing details or documentation` bedeuten, dass noch Handelsrechnungen, EORI- oder Steuerdaten oder Einfuhrabgaben fehlen. Wie Verzollung und Gebühren ablaufen, erklärt der [Zoll-Ratgeber](guide:customs).

Mehrere Tage ohne neuen Scan sind auf Seereisen, bei mehrstufigen Luftfracht-Konsolidierungen oder über Wochenenden üblich. Auf myKN gezeigte geplante Termine sind Prognosen und noch keine tatsächlichen Scans. Weitere Tipps zu ruhigen Sendungsverläufen stehen im Ratgeber [wenn sich die Sendungsverfolgung nicht aktualisiert](guide:tracking-not-updating).

## Kühne+Nagel mit Peek verfolgen

Fügst du eine `RWM`- oder `RAPI`-Nummer oder einen `mykn.kuehne-nagel.com`-Link ein, erkennt Peek Kühne+Nagel sofort. Bei einer 12-stelligen `26A...`-Nummer (die auch [Australia Post](carrier:australia-post) und [Colis Privé](carrier:colis-prive) nutzen) oder einer freien Kundenreferenz wählst du Kühne+Nagel beim Hinzufügen über **Ändern** oder später über **Paketdienst ändern** gezielt aus.

Peek ruft die öffentliche myKN-Suche, die Strecken-Meilensteine und die Informationsmeldungen direkt über den quelloffenen [universal-parcel-scraper Kühne+Nagel-Adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/kuehne-nagel) ab. Dabei übernimmt Peek ausschließlich tatsächlich erreichte Meilensteine (keine bloßen Plantermine), liest bei Verfügbarkeit das Gewicht in Kilogramm und das Zielland aus und ersetzt eine sekundäre Kundenreferenz durch die kanonische Kühne+Nagel-Trackingnummer, sobald genau eine Sendung gefunden wird. Enthält ein Zeitstempel eine Zeitzonenangabe, speichert Peek den genauen Zeitpunkt; andernfalls zeigt Peek die gemeldete Ortszeit ohne geratene Zeitzone an.

Peek ordnet die Kühne+Nagel-Meilensteine übersichtlich zu: **Etikett erstellt** für Buchungen und Abholbereitschaft, **Beim Paketdienst eingegangen** nach der Abholung, **Unterwegs** für Terminal-, Hafen-, Bahn- und Übergabescans, **Beim Zoll** für Export-/Importabfertigung und Behördenprüfungen, **In Zustellung** ab `In delivery`, **Zustellversuch** bei gescheiterter Auslieferung, **Problem gemeldet** bei Sperren, fehlenden Dokumenten oder Stornierungen sowie **Zugestellt** nach Zustellung, Selbstabholung oder Vorliegen des Abliefernachweises. Nachträgliche Dokumenten- oder Zollmeldungen setzen eine zugestellte Sendung nicht mehr zurück.

Bleibt es unterwegs länger still, zeigt Peek den Hinweis: „Auf langen Flügen, beim Zoll oder übers Wochenende wird es oft still um ein Paket. Peek fragt weiter bei Kuehne+Nagel nach.“ Nach mehr als drei Wochen ohne Bewegung erscheint: „Seit über drei Wochen nichts Neues. Ist das Paket noch nicht angekommen, frag beim Verkäufer nach. Peek fragt weiter bei Kuehne+Nagel nach.“

In den Benachrichtigungseinstellungen kannst du zwischen **Jeden Scan**, **Wichtige Schritte** und **Nur die Zustellung** wählen.

## Kontakt zu Kühne+Nagel

Anfragen zu laufenden Sendungen bearbeitet Kühne+Nagel über die jeweiligen Landesgesellschaften und das betreuende Depot statt über eine zentrale Endkunden-Hotline.

- **Offizielle Websites:** [Kühne+Nagel Deutschland](https://www.kuehne-nagel.com/de), [Kühne+Nagel Schweiz](https://www.kuehne-nagel.com/ch) und [myKN Public Tracking](https://mykn.kuehne-nagel.com/public-tracking/)
- **Standortsuche:** [Kühne+Nagel-Standorte weltweit](https://www.kuehne-nagel.com/de/unternehmen/standorte) für das zuständige Büro oder Empfangsdepot
- **Kontaktformular:** [Sprechen Sie mit unseren Experten](https://www.kuehne-nagel.com/de/kontakt/experten-kontaktieren)
- **Zentrale Deutschland (Impressum):** Kühne + Nagel (AG & Co.) KG, Wilhelm-Kaisen-Brücke 1, 28195 Bremen, Tel. +49 (0)421 3605-0, E-Mail `info.bremen@kuehne-nagel.com` ([Impressum](https://www.kuehne-nagel.com/de/unternehmen/impressum))
- **Zuerst den Absender ansprechen:** Bei Stückgut- und Onlinebestellungen ist der Auftraggeber (Verkäufer oder Lieferant) Vertragspartner von Kühne+Nagel und kann Zustelltermine, fehlende Zollunterlagen oder Reklamationen am schnellsten klären. Falls ein Teil deiner Lieferung über eine andere Spedition läuft, sieh dir auch [DHL Freight](carrier:dhl-freight) oder [DSV](carrier:dsv) an.

## Häufige Fragen

### Warum findet myKN meine Kühne+Nagel-Sendungsnummer nicht?

Die öffentliche Suche deckt nur Sendungen ab, die innerhalb der letzten 90 Tage gestartet wurden, und sucht ausschließlich nach vollständigen Referenzen. Prüfe die Nummer auf Tippfehler und warte bei ganz frischen Buchungen auf den ersten erreichten Meilenstein. Trifft eine allgemeine Referenz auf mehrere Sendungen gleichzeitig zu, bitte den Absender um die eindeutige `RWM`- oder `RAPI`-Tracking-ID.

### Was bedeutet „Freight handover“ oder „Service complete“?

`Freight handover` bedeutet, dass Kühne+Nagel die Fracht an einen Zollagenten oder einen nachgelagerten Frachtführer übergeben hat — die Sendung ist also weiter unterwegs. `Service complete` schließt die beauftragte Kühne+Nagel-Teilstrecke ab (z. B. bis zum Zielhafen); für die Zustellung bis zur Haustür kann eine eigene Nummer des nachgelagerten Spediteurs gelten.

### Warum erscheinen geplante Termine aus myKN nicht als Scans in Peek?

Auf der öffentlichen myKN-Seite stehen geplante Prognosedaten neben bereits erreichten Meilensteinen. Peek übernimmt nur Meilensteine, die Kühne+Nagel als tatsächlich erreicht meldet, damit geplante Ziele nicht wie abgeschlossene Bewegungen wirken.

### Wie lange bleiben Sendungen im öffentlichen Kühne+Nagel-Tracking sichtbar?

Das öffentliche Tracking auf `mykn.kuehne-nagel.com` umfasst Sendungen der letzten 90 Tage. Ältere Frachtdaten lassen sich nur über ein registriertes myKN-Geschäftskonto oder über den Absender abrufen.

:::sources
- [Kühne+Nagel Deutschland](https://www.kuehne-nagel.com/de)
- [Kühne+Nagel Schweiz](https://www.kuehne-nagel.com/ch)
- [myKN Public Tracking](https://mykn.kuehne-nagel.com/public-tracking/)
- [Kühne+Nagel-Standorte weltweit](https://www.kuehne-nagel.com/de/unternehmen/standorte)
- [Kühne+Nagel Experten kontaktieren](https://www.kuehne-nagel.com/de/kontakt/experten-kontaktieren)
- [Kühne+Nagel Deutschland Impressum](https://www.kuehne-nagel.com/de/unternehmen/impressum)
- [universal-parcel-scraper Kühne+Nagel-Adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/kuehne-nagel)
:::
