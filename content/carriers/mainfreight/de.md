---
title: Mainfreight Sendungsverfolgung: Nummern und Status
description: Mainfreight Fracht in Neuseeland, Australien, den USA oder Europa verfolgen: Nummernformate, Status auf Deutsch, Zoll, Reklamation und Peek.
slug: mainfreight-sendungsverfolgung
published: 2026-11-22
updated: 2026-11-22
---

Mainfreight befördert Fracht per Lkw, Luft- und Seefracht. Peeks Katalog führt den Dienst für Neuseeland (seinen Heimatmarkt), Australien und die Vereinigten Staaten; sein Tracking deckt außerdem europäischen Straßengüterverkehr und Containerbewegungen ab. Um eine Fracht- oder Großsendung über Mainfreight zu verfolgen, füge die Sendungsnummer des Auftraggebers oben ein. Das eigene Tracking von Mainfreight liegt unter www.mainfreight.com.

## Mainfreight Sendungsnummer: wie sie aussieht

Mit erfundenen Ziffern:

| Nummer (erfunden) | Bedeutung |
| --- | --- |
| `48291736542` | 11 Ziffern: eine Straßensendung in Neuseeland, Australien oder den USA — das einzige Muster, das Peeks Katalog Mainfreight zuordnet. |
| Buchstaben und Ziffern | Eine Jobnummer (`S` und 8 Ziffern bei Luft-/Seefracht, `CJ` und 9 Ziffern bei Containern), ein House Bill oder die Absenderreferenz. Sobald du Mainfreight auswählst, fragt Peek mit 4 bis 40 Zeichen nach. |
| Master Bill | Die 11 Ziffern eines Luftfrachtbriefs oder ein Seefracht-Master-Bill: Peek findet den Luft- oder Seeauftrag, wenn genau ein Auftrag dazugehört. |

Peeks Katalog kennt Mainfreight auch als Mainfreight Group, Mainfreight Transport und Mainfreight Air & Ocean.

**Wo du sie findest.** In der Versand-E-Mail oder auf deiner Bestellseite; bei gewerblicher Fracht frage beim Absender nach. Siehe [Sendungsnummer finden](guide:find-tracking-number).

**Andere Dienste mit 11 Ziffern.** Elf Ziffern passen auch zu [GLS Germany](carrier:gls-de), [GLS France](carrier:gls-fr), DHL eCommerce Poland, PostLogistics und anderen. Nennt deine E-Mail einen davon, nutze diesen. Mehr unter [Sendungsnummer-Formate](guide:tracking-number-formats).

## Was Mainfreight Statusmeldungen bedeuten

**Straßenfracht in Neuseeland und Australien:**

| Mainfreight meldet | Was das für dich bedeutet | Peek zeigt |
| --- | --- | --- |
| `Information Received` | Mainfreight hat die Daten, aber noch nicht die Ware. | „Etikett erstellt“ |
| `Pickup Allocated to Driver` | Ein Fahrer ist für die Abholung eingeteilt; die Ware liegt noch beim Absender. | „Etikett erstellt“ |
| `Picked Up` | Beim Absender abgeholt. | „Beim Paketdienst eingegangen“ |
| `In Transit` | Unterwegs. | „Unterwegs“ |
| `At Delivery Depot` | Im zustellenden Depot angekommen. | „Unterwegs“ |
| `On Delivery Vehicle` | Auf dem Zustellfahrzeug. | „In Zustellung“ |
| `Delivered` | Zugestellt. | „Zugestellt“ |

**Straßenfracht in den Vereinigten Staaten.** US-Schritte hängen den Ort an die Meldung an; Peek zeigt ihn neben dem Schritt.

| Mainfreight meldet | Was das für dich bedeutet | Peek zeigt |
| --- | --- | --- |
| `Booked` | Der Transport ist gebucht. | „Etikett erstellt“ |
| `Pickup assigned to driver` | Ein Fahrer ist für die Abholung eingeteilt. | „Etikett erstellt“ |
| `Goods picked up` | Beim Absender abgeholt. | „Beim Paketdienst eingegangen“ |
| `Departed from Terminal`, `Arrived at Terminal` | Hat ein Terminal verlassen oder erreicht. | „Unterwegs“ |
| `On drivers vehicle en-route` | Im Lkw auf dem Weg zu dir. | „In Zustellung“ |
| `Goods delivered` | Zugestellt. | „Zugestellt“ |
| `Collected by Customer` | Vom Kunden am Terminal abgeholt. | „Zugestellt“ |

**Luft- und Seefracht:**

| Mainfreight meldet | Was das für dich bedeutet | Peek zeigt |
| --- | --- | --- |
| `Booking Confirmation Sent` | Buchung bestätigt. | „Etikett erstellt“ |
| `Pickup Cartage Advised` | Ein Abhol-Lkw ist disponiert. | „Etikett erstellt“ |
| `Pick up Cartage Complete` | Für den Export abgeholt. | „Beim Paketdienst eingegangen“ |
| `Origin Customs Cleared` | Ausfuhrzoll vor Abflug/Abfahrt erledigt. | „Unterwegs“ |
| `Departure from First Load Port`, `Arrival at Final Discharge Port` | Vom ersten Hafen/Flughafen abgegangen oder am letzten angekommen. | „Unterwegs“ |
| `Customs Clearance Commenced` | Einfuhrverzollung gestartet. | „Beim Zoll“ |
| `Customs Clearance Finalised` | Verzollung abgeschlossen, wieder unterwegs. | „Unterwegs“ |
| `Cargo Available` | Am Zielort freigegeben, vor Zustellung. | „Unterwegs“ |
| `Cargo Delivered`, `Full Container Delivered` | Zugestellt. | „Zugestellt“ |

Geplante Abgänge und Ankünfte mit dem Zusatz `(Estimated)` sind noch nicht erfolgt und werden von Peek ausgeblendet.

**Weitere Schritte.** Containerbewegungen zwischen Standorten wie `Picked Up (Site Movement)` zeigen „Unterwegs“, und `Final Delivery (Empty to Customer)` zeigt „Zugestellt“. Bei europäischer Straßenfracht stehen `Order received` für „Etikett erstellt“, `Shipment loaded into line haul truck` für „Unterwegs“ und `Out for delivery` für „In Zustellung“. Fehlt ein Einzelereignis, nutzt Peek den Gesamtstatus wie `Created` oder `Final Delivery`.

Peeks Code kennt keine eigenen Mainfreight-Texte für verpasste Zustellungen, Probleme oder Retouren. Andere Zeilen laufen über Peeks allgemeine Regeln oder erscheinen unverändert ohne Statuswechsel. Mehr unter [Sendungsstatus verstehen](guide:tracking-statuses).

## Wie Mainfreight liefert

Peek konnte die Hilfeseiten von Mainfreight nicht auslesen; daher nennt diese Seite keine Laufzeiten, Terminabsprachen, Zustellversuche oder Abholregeln im Depot. Frage den Auftraggeber der Fracht, wie und wann geliefert wird.

Peeks Code übergibt Mainfreight-Sendungen an keinen anderen Paketdienst. Das Tracking kann für Luft- und Seefracht sowie europäische Straßenfracht einen voraussichtlichen Liefertag liefern, den Peek bis zur Zustelltour anzeigt. Es übermittelt Peek weder Zustellnachweis noch Namen des Empfängers.

**Zoll.** Für Einfuhren nach Neuseeland nennt NZ Customs:

- Onlinekäufe im Ausland bis 1.000 NZ$ bleiben zollfrei, und der ausländische Verkäufer erhebt die GST — außer sie sind Teil einer größeren Sendung über 1.000 NZ$. Tabak und Alkohol unterliegen unabhängig vom Wert Zoll und GST.
- Die GST beträgt 15 % auf Warenwert plus etwaigen Zoll, Fracht und Versicherung. Eine „Customs and MPI Goods Levy“ fällt auf nicht befreite Einfuhren an; dein Zollagent oder Spediteur zahlt sie und kann sie weiterberechnen.
- Sendungen ab 1.000 NZ$ brauchen eine Customs Number, und der Zoll gibt Ware erst nach Zahlung der Abgaben frei.

Für andere Länder siehe [Paket hängt beim Zoll](guide:customs) und kläre mit dem Auftraggeber, wer die Abgaben trägt.

## Mainfreight Sendung verspätet, hängt, beschädigt oder verloren

**Noch nichts zu sehen.** Eine unter Mainfreight gespeicherte Sendung, die sein Tracker noch nicht führt, zeigt „Warten auf Mainfreight“, während Peek weiter fragt. Eingefügte 11 Ziffern ohne Treffer bei irgendeinem Dienst zeigen „Noch kein Sendungsverlauf“. Prüfe die Ziffern und frage den Absender, ob Mainfreight die Ware schon abgeholt hat.

**Keine Bewegung.** Nach vier Tagen ohne neuen Schritt bei „Etikett erstellt“, „Beim Paketdienst eingegangen“ oder „Unterwegs“ zeigt Peek in deiner Liste den Tag des letzten Scans („Keine Aktualisierung seit …“) und auf der Paketseite „Auf langen Flügen, beim Zoll oder übers Wochenende wird es oft still um ein Paket. Peek fragt weiter bei Mainfreight nach.“, erst dann samt Link „Hol dir Hilfe bei Mainfreight“ zum Kontaktformular. Frage Shop oder Absender nach dem Grund. Siehe [Sendungsverfolgung aktualisiert sich nicht](guide:tracking-not-updating).

**Beschädigt, unvollständig oder verloren.** Melde es zuerst dem Shop oder Absender als Auftraggeber. In Neuseeland und Australien gelten Mainfreights Bedingungen und das örtliche Recht. Für zwischenstaatliche US-Lkw-Fracht regelt das US-Bundesrecht:

- Der Frachtführer haftet für den tatsächlichen Verlust oder Schaden; die Haftung kann auf einen vom Versender deklarierten oder schriftlich vereinbarten Wert begrenzt sein. Die Frist zur Reklamation darf nicht unter 9 Monaten liegen, die Klagefrist nicht unter 2 Jahren ab schriftlicher Ablehnung eines Teils des Anspruchs.
- Eine Reklamation ist eine schriftliche Forderung, die die Sendung benennt, den Frachtführer haftbar macht und einen bestimmten oder bestimmbaren Betrag fordert. Ein Vermerk über Schaden oder Fehlmenge auf dem Abliefernachweis allein reicht nicht. Der Frachtführer darf Frachtbrief, Frachtkostennachweis und Rechnung verlangen.
- Der Frachtführer bestätigt den Eingang binnen 30 Tagen schriftlich und zahlt, lehnt ab oder unterbreitet ein verbindliches Angebot binnen 120 Tagen; anderenfalls informiert er dann und alle weiteren 60 Tage schriftlich über den Stand.

Steht dort `Delivered`, ohne dass etwas ankam, siehe [Zugestellt, aber nicht erhalten](guide:delivered-not-received). Mehr unter [Beschädigtes Paket](guide:damaged-parcel) und [Verlorenes Paket](guide:lost-parcel).

## Mainfreight Kundenservice und Kontakt

Peek konnte die Website von Mainfreight nicht auslesen; deshalb stehen hier keine Telefonnummer, E-Mail-Adresse oder Niederlassung, da Peek nur Kontakte übernimmt, die direkt auf den offiziellen Seiten des Anbieters gelesen wurden.

Wende dich zuerst an den Auftraggeber der Fracht — bei einer Bestellung an Shop oder Lieferant, sonst an das absendende Unternehmen. Dort liegt die Buchung vor.

## Mainfreight Sendung mit Peek verfolgen

Aus Neuseeland, Australien oder den USA fragt Peek bei 11 Ziffern schon beim Hinzufügen bei Mainfreight an, gleichzeitig mit GLS France, GLS Germany, GLS Switzerland und PostLogistics („Tracking-Dienste werden geprüft…“) und nennt Mainfreight, wenn nur Mainfreight die Sendung kennt; kennen sie mehrere, lässt Peek dich wählen. Aus anderen Ländern fragt Peek Mainfreight beim Hinzufügen nicht automatisch ab: Wähle Mainfreight selbst aus.

Andere Nummern verraten Peek nicht automatisch, dass sie zu Mainfreight gehören. Füge sie trotzdem ein: Peek unterstützt über 4500+ Paketdienste. Wechsle dann den Paketdienst und wähle Mainfreight (bei 11 Ziffern steht es unter den passenden Diensten, sonst per Namenssuche). Ein Link zum Mainfreight-Tracking (mainfreight.com/tracking mit `trackingnumber=` oder mainfreight.com/track/…) mit der Nummer erkennt Mainfreight sofort („Erkannter Paketdienst“).

Peek braucht weder Konto noch Postleitzahl. Findet Mainfreights Suche 11 Ziffern nicht, probiert Peek gleichzeitig die Dienste für Neuseeland, Australien und die USA; melden mehrere einen Treffer, kann Peek nicht entscheiden, welcher deiner ist. Bei einem House Bill, Master Bill oder einer Absenderreferenz zeigt Peek die Hauptnummer von Mainfreight mit „Mainfreight führt die Sendung unter“.

Peek zeigt Status, jeden Schritt in Mainfreights Wortlaut mit Ort sowie das Gewicht in deinen Einheiten, falls gemeldet. Niederlassungskontakte, Postleitzahlen, Absenderreferenz und Dokumente lässt es weg. „Website von Mainfreight öffnen“ öffnet die Nummer im Mainfreight-Tracking. Ist der Tracker nicht erreichbar oder nicht lesbar, fragt Peek [Tracking-Dienste](guide:universal-tracker) und zeigt deren Verlauf in deren Wortlaut.

Straßenschritte mit neuseeländischer, australischer oder US-Zeitzonenangabe (`NZDT`, `AEST`, `PST` usw.) liefern die genaue Uhrzeit. Andere Schritte tragen keine Zeitzone: Peek liest sie auf der Uhrzeit ihres Orts oder benachbarter Schritte, wenn es sie kennt, sonst auf neuseeländischer Zeit (so bei europäischer Straßenfracht und Luft-/Seefracht, deren Uhrzeit dann um mehrere Stunden abweichen kann), und zeigt sie in deiner Zeitzone (der Verlauf endet mit „Uhrzeiten in deiner Zeitzone:“ samt ihrem Namen).

Solange die Sendung auf dem Bildschirm geöffnet ist — oder am Zustelltag mit aktivierten Benachrichtigungen — prüft Peek direkt bei Mainfreight bis zu alle 10 Minuten, und ab 6:00 Uhr nach der Uhrzeit von Mainfreights letztem Scan alle 2 Minuten an dem Tag, an dem Mainfreight sie in Zustellung scannt, solange der eigene Tracker antwortet. Sonst sowie nach zwei Tagen ohne neuen Schritt (12 Stunden bei „Etikett erstellt“ oder „Beim Paketdienst eingegangen“) prüft Peek stündlich. Für Benachrichtigungen wählst du „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“. Peek kann Mainfreight nicht für dich kontaktieren, keinen Termin buchen und keine Reklamation einreichen.

## Fragen zur Mainfreight Sendungsverfolgung

### Wie sieht eine Mainfreight Sendungsnummer aus?

Für Straßenfracht in Neuseeland, Australien und den USA sind es 11 Ziffern (wie `48291736542`). Jobnummern, House Bills und Absenderreferenzen kombinieren Buchstaben und Ziffern: Wähle dafür Mainfreight manuell in Peek.

### Was bedeutet „At Delivery Depot“ bei Mainfreight?

Die Sendung ist im zustellenden Depot angekommen und Peek zeigt „Unterwegs“. Als Nächstes folgt `On Delivery Vehicle`, sobald sie im Zustell-Lkw ist. Bleibt sie tagelang dort, frage beim Absender nach dem Liefertermin.

### Was bedeutet „Pickup Allocated to Driver“?

Ein Fahrer ist für die Abholung eingeteilt, aber die Ware liegt noch beim Absender — Peek zeigt daher „Etikett erstellt“. Der nächste Schritt ist `Picked Up`.

:::sources
- [Universal Parcel Scraper: Mainfreight](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/mainfreight) – Namen und Länder, 11-stelliges Format, weitere Referenzen und Master Bills, Links, Suche und Dienste, Formulierungen und Stufen, geschätzte Schritte, Zeiten, Gewicht, erwarteter Tag, von Peek weggelassene Felder
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – andere Dienste mit 11 Ziffern, Abfrage von Mainfreight nur aus Neuseeland, Australien und den USA, keine Übergabe, Tracking-Dienste als Fallback
- [NZ Customs: Duty and GST](https://www.customs.govt.nz/sending-and-receiving/duty-and-gst) – 1.000 NZ$, GST durch ausländische Händler, Sammelsendungen, Tabak und Alkohol, GST-Berechnung, Goods Levy, Customs Number, Freigabe nach Zahlung
- [GovInfo: 49 U.S.C. 14706, Liability of carriers under receipts and bills of lading](https://www.govinfo.gov/content/pkg/USCODE-2024-title49/html/USCODE-2024-title49-subtitleIV-partB-chap147-sec14706.htm) – Haftung für tatsächlichen Verlust oder Schaden, Wertbegrenzung, 9 Monate Reklamationsfrist und 2 Jahre Klagefrist
- [GovInfo: 49 CFR Part 370, Investigation and voluntary disposition of loss and damage claims and processing salvage](https://www.govinfo.gov/content/pkg/CFR-2025-title49-vol5/xml/CFR-2025-title49-vol5-part370.xml) – Inhalt einer Reklamation, Vermerke auf Ablieferbelegen, Nachweise, 30, 120 und 60 Tage
:::
