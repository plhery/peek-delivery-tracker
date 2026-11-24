---
title: One by Allegro CZ (WE|DO) Sendungsverfolgung & Abholung
description: Verfolge One-by-Allegro-CZ-Pakete (WE|DO, IN TIME, Uloženka) in Tschechien: 11-stellige Codes, One Box 48 Std., One Point 5 Tage und Support.
slug: one-by-allegro-cz-sendungsverfolgung
published: 2026-11-24
updated: 2026-11-24
---

One by Allegro CZ ist der tschechische Paketdienst der Allegro-Gruppe (die Website [onedelivery.cz](https://onedelivery.cz/) gehört Allegro Retail a.s.). Er liefert Bestellungen von allegro.cz und anderen Online-Shops über drei Wege aus: **One Box** (über 5.244 Paketautomaten einschließlich der Partner AlzaBox und OX Box), **One Point** (1.960 Partner-Paketshops) und **One Courier** (Haustürzustellung in ganz Tschechien mit mehr als 350 Fahrzeugen und Zustellern). Peeks Suche findet den Dienst auch unter WE|DO, IN TIME oder Uloženka.

Du kannst ein tschechisches Paket auf [onedelivery.cz/sledovani-zasilky](https://onedelivery.cz/sledovani-zasilky) prüfen oder deine 11-stellige Sendungsnummer oben einfügen.

## Format der One by Allegro CZ (WE|DO) Sendungsnummer

Tschechische One-by-Allegro- und WE|DO-Sendungen nutzen einen kompakten **11-stelligen alphanumerischen Code**:

| Format (erfunden) | Was es ist |
| --- | --- |
| `2BM48291736` | 1 Ziffer + 1 Buchstabe + 1 Buchstabe/Ziffer + 8 Ziffern (insgesamt 11 Zeichen) — reguläre One-by-Allegro-CZ-(WeDo)-Sendungsnummer. |
| `48P48291736` | 2 Ziffern + 1 Buchstabe + 8 Ziffern (insgesamt 11 Zeichen) — alternatives tschechisches One-by-Allegro-(WeDo)-Format. |

Präfixe ab `7D` + Ziffer oder `7X` + Ziffer sind ausgenommen; alle anderen passenden 11-stelligen Codes weisen eindeutig auf **One by Allegro CZ** hin. Peek wertet außerdem direkte Links von `onedelivery.cz/sledovani-zasilky?numer=...`, `onedelivery.cz/?numer=...`, `trace.wedo.cz/index.php?orderNumber=...` und `trace.intime.cz/index.php?orderNumber=...` aus. Polnische Allegro-One-Pakete laufen über einen eigenen Tracker — siehe [Allegro One](carrier:allegro-one) und den Leitfaden zu [Sendungsnummern-Formaten](guide:tracking-number-formats).

## Bedeutung der One by Allegro CZ Statusmeldungen

Die öffentliche Tracking-API von One by Allegro CZ liefert englische Statusmeldungen mit Zeitstempel und UTC-Offset. Die häufigsten Einträge:

| One by Allegro CZ Wortlaut | Was er bedeutet |
| --- | --- |
| `Parcel has been prepared by the sender` | Der Shop hat das Versandetikett erstellt; der Kurier hat das Paket noch nicht abgeholt. |
| `Parcel has been picked up by the courier` | One by Allegro CZ hat das Paket beim Absender abgeholt. |
| `Parcel has been dispatched to its destination point` | Hat das Ausgangszentrum Richtung Zielregion oder Abholnetz verlassen. |
| `Parcel has been accepted at the sorting center` | In einem Sortierzentrum von One by Allegro bearbeitet. |
| `Parcel has been accepted at the branch` | Im zuständigen Auslieferungsdepot für deine Adresse oder Abholstelle eingetroffen. |
| `Parcel has been released for delivery` | Heute zur Kurierzustellung oder Einlage in deine gewählte One Box bzw. deinen One Point verladen. |
| `Parcel is awaiting pick-up` | Abholbereit in deiner One Box oder deinem One Point (Abhol-PIN per SMS, E-Mail oder App prüfen). |
| `Parcel has been delivered` | Dem Empfänger übergeben oder aus Paketautomat beziehungsweise Paketshop abgeholt. |

Meldet der Tracker einen ungewöhnlichen Freitext, der persönliche Angaben des Empfängers enthalten könnte, ersetzt Peek diese Zeile durch `Tracking update` und behält den Zeitstempel im Verlauf bei.

## Zustell- und Abholregeln für One Box, One Point und One Courier

One by Allegro CZ veröffentlicht klare Abholfristen und Zustellregeln auf [onedelivery.cz](https://onedelivery.cz/):

- **One Box Automaten ([onedelivery.cz/one-box](https://onedelivery.cz/one-box)):** **rund um die Uhr (24/7)** zugänglich an über 5.244 Automaten (One Box, AlzaBox und OX Box). Abholung innerhalb von **48 Stunden** nach Einlage durch Eingabe der Empfänger-Telefonnummer und des per SMS, E-Mail, One-by-Allegro-App oder Allegro-App gesendeten Zahlencodes (am Automaten ist keine App nötig). Nachnahme (*dobírka*) lässt sich über den Zahlungslink in der Benachrichtigung oder am Kartenterminal der One Box bezahlen.
- **One Point Paketshops ([onedelivery.cz/one-point](https://onedelivery.cz/one-point)):** In 1.960 Partnergeschäften während der Öffnungszeiten lagern Pakete regulär **5 Werktage** (kostenlos um bis zu **3 weitere Werktage** über den Kundenservice verlängerbar; in der starken Vorweihnachtszeit verkürzt sich die Lagerzeit im One Point von 5 auf **3 Tage**, während One Box bei 48 Stunden bleibt). Höchstwerte im One Point sind **20 kg**, längste Seite **1,2 m** und Volumen **0,2 m³**. Nachnahme ist bar oder — sofern ein Terminal vorhanden ist — per Karte zahlbar.
- **One Courier Haustürzustellung ([onedelivery.cz/one-courier](https://onedelivery.cz/one-courier)):** Standardzustellung am **nächsten Werktag (`D+1`)** nach Abholung, **werktags zwischen 08:00 und 18:00 Uhr**, bis zur ersten abschließbaren Tür (Zusteller müssen Pakete nicht in obere Stockwerke tragen; Postfächer werden nicht beliefert). Am Zustelltag erhältst du eine Nachricht per SMS, E-Mail oder App. Nachnahme kann bar (möglichst passend) oder ohne Aufpreis per Karte bezahlt werden. Zustelltermin ändern oder ein geeignetes Kurierpaket an eine Abholstelle umleiten kannst du in der One-by-Allegro-App oder beim Kundenservice; Adressänderungen laufen über den Kundenservice.

Wenn du eine Abholfrist verpasst hast oder ein Paket suchst, helfen die Leitfäden zu [Paketstationen](guide:parcel-lockers), [nicht abgeholten Paketen](guide:uncollected-parcel) und [stehengebliebener Sendungsverfolgung](guide:tracking-not-updating). Für andere tschechische Netze vergleiche [Packeta](carrier:packeta), [PPL](carrier:ppl) und [Česká pošta](carrier:ceska-posta).

## One by Allegro CZ mit Peek verfolgen

Füge deinen 11-stelligen One-by-Allegro-CZ-Code oder einen `onedelivery.cz`- beziehungsweise `trace.wedo.cz`-Link in Peek ein — Peek erkennt **One by Allegro CZ** sofort. Musst du den Dienst jemals wechseln, nutze beim Hinzufügen „Ändern“ oder auf der Paketseite „Paketdienst ändern“.

Peek fragt den öffentlichen Tracking-Endpunkt von One by Allegro CZ (`edge.onedelivery.cz`) direkt über den Open-Source-[One-by-Allegro-CZ-Adapter von universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/wedo) ab und wiederholt bei Netzwerk-Timeouts automatisch. Es übernimmt exakte Zeitstempel samt Zeitzonen-Offset und den Zustellzeitpunkt (`delivered_at`), verwirft jedoch Freitext-Details, die persönliche Daten enthalten könnten. Ein geschätztes Lieferdatum, Depotstädte oder das Paketgewicht liefert die öffentliche Gast-API nicht.

Peek ordnet die Meldungen den Stufen zu: „Etikett erstellt“ (`Parcel has been prepared by the sender`), „Beim Paketdienst eingegangen“ (`Parcel has been picked up by the courier`), „Unterwegs“ (Abgang, Sortierzentrum und Filiale), „In Zustellung“ (`Parcel has been released for delivery`), „Abholbereit“ (`Parcel is awaiting pick-up`) und „Zugestellt“ (`Parcel has been delivered`). Auch weitere Allegro-One-Meldungen wie `Parcel has been returned to the sender` werden ausgewertet.

Solange du das Paket in Peek geöffnet hast oder am Zustelltag Benachrichtigungen aktiviert sind, fragt Peek bis zu alle 10 Minuten nach; an dem Tag, an dem One by Allegro CZ `Parcel has been released for delivery` zeigt, ab 6 Uhr tschechischer Zeit alle 2 Minuten, solange der Tracker antwortet. Andernfalls prüft Peek stündlich. Nach 12 Stunden mit lediglich „Etikett erstellt“ oder „Beim Paketdienst eingegangen“ oder nach zwei Tagen ohne Neuigkeiten prüft Peek stündlich, egal wie du die Sendung verfolgst, und nach sieben Tagen auf „Etikett erstellt“ alle 6 Stunden. Antwortet One by Allegro CZ nicht und Peek verfolgt das Paket allein über Tracking-Dienste, prüft Peek stündlich, nach einem ruhigen Tag alle 3 Stunden und an dem Tag, an dem das Paket in Zustellung ist, bis zu alle 15 Minuten, solange du die Sendung geöffnet hast oder Benachrichtigungen aktiviert sind.

Ist der neueste Scan vier Tage alt und das Paket steht noch auf „Etikett erstellt“, „Beim Paketdienst eingegangen“ oder „Unterwegs“, markiert Peek es mit dem Tag dieses Scans, etwa „Keine Aktualisierung seit Mo 12. Okt“. Auf der Paketseite steht dann „Auf langen Flügen, beim Zoll oder übers Wochenende wird es oft still um ein Paket. Peek fragt weiter bei One by Allegro CZ nach.“ samt dem Link „Hol dir Hilfe bei One by Allegro CZ“ zur Kontaktseite. Nach drei Wochen ohne neuen Scan heißt es „Seit über drei Wochen nichts Neues. Ist das Paket noch nicht angekommen, frag beim Verkäufer nach. Peek fragt weiter bei One by Allegro CZ nach.“

Wähle in Peeks Benachrichtigungseinstellungen „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“.

## Kontakt zu One by Allegro CZ

- **Sendungsanfrage & Support:** [onedelivery.cz/kontakt](https://onedelivery.cz/kontakt) (*Dotaz na zásilku* anklicken für Käufer- und Verkäuferfragen, verlorene Abhol-PINs, kostenlose 3-Tage-Verlängerung im One Point oder Adressänderungen)
- **Offizielle Tracking-Seite:** [onedelivery.cz/sledovani-zasilky](https://onedelivery.cz/sledovani-zasilky)
- **Service-Infos:** [One Box](https://onedelivery.cz/one-box), [One Point](https://onedelivery.cz/one-point) und [One Courier](https://onedelivery.cz/one-courier)
- **Hauptwebsite:** [onedelivery.cz](https://onedelivery.cz/)

## Häufig gestellte Fragen

### Sind WE|DO, IN TIME und One by Allegro CZ derselbe Paketdienst?

Peek behandelt sie als einen Dienst: Die Suche findet One by Allegro CZ als WE|DO, IN TIME oder Uloženka, und sowohl `trace.wedo.cz`- und `trace.intime.cz`-Links als auch 11-stellige Sendungsnummern laufen unter One by Allegro CZ. Die Geschichte der Marken erklärt `onedelivery.cz` nicht.

### Wie lange liegt ein Paket in einer One Box oder einem One Point?

In der **One Box** (einschließlich AlzaBox und OX Box) liegen Pakete **48 Stunden** ab Einlage. In **One Point**-Paketshops beträgt die reguläre Lagerzeit **5 Werktage** (kostenlos um bis zu 3 Werktage über den Kundenservice verlängerbar) und verkürzt sich in der Vorweihnachtszeit auf **3 Tage**.

### Kann ich Nachnahme (dobírka) mit Karte bezahlen?

Ja. One-Courier-Zusteller akzeptieren Bargeld (möglichst passend) oder Karte ohne Gebühr für den Empfänger; One Box erlaubt Kartenzahlung über den Zahlungslink in der Nachricht oder am Automatenterminal; und One Point akzeptiert bar oder Karte, sofern der Shop ein Kartenterminal hat.

### Was mache ich, wenn mein Abholcode für One Box oder One Point weg ist?

Abholcodes kommen per SMS, E-Mail sowie in den Apps One by Allegro und Allegro. Findest du den Code nirgends, kontaktiere den Kundenservice über das Anfrageformular auf [onedelivery.cz/kontakt](https://onedelivery.cz/kontakt).

:::sources
- [One by Allegro CZ (onedelivery.cz)](https://onedelivery.cz/)
- [One by Allegro CZ — Sledování zásilky](https://onedelivery.cz/sledovani-zasilky)
- [One by Allegro CZ — One Box](https://onedelivery.cz/one-box)
- [One by Allegro CZ — One Point](https://onedelivery.cz/one-point)
- [One by Allegro CZ — One Courier](https://onedelivery.cz/one-courier)
- [One by Allegro CZ — Kontakt](https://onedelivery.cz/kontakt)
- [universal-parcel-scraper One by Allegro CZ adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/wedo)
:::
