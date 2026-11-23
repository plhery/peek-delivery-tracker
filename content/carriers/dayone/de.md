---
title: DayOne Sendungsverfolgung: DOT-Nummern, Status & Zusteller
description: DayOne-Paket aus China verfolgen: wie eine DOT-Nummer aussieht, Bedeutung der DayOne-Status, wer zustellt und wer bei Verspätung hilft.
slug: dayone-sendungsverfolgung
published: 2026-11-23
updated: 2026-11-23
---

DayOne ist das Versandlager, aus dem Onlineshops ihre Bestellungen aus China verschicken. Es ist kein Paketdienst: DayOne bezeichnet sich selbst als „order fulfillment warehouse, not a courier delivery service provider“. Andere Unternehmen transportieren das Paket, und DayOnes Tracker [DayOneTrack](https://www.dayonetrack.com/) auf Englisch und Chinesisch zeigt deren Scans an. Prüfe deine DOT-Nummer dort oder füge sie oben in das Feld ein. Die Nummer bekommst du vom Shop zusammen mit den Versanddaten deiner Bestellung.

## DayOne-Sendungsnummer: So sieht sie aus

DayOne veröffentlicht kein offizielles Format. Diese Form erkennt Peek als DayOne (mit erfundenen Ziffern):

| Nummer | Was sie ist |
| --- | --- |
| `DOT0482917364058291` | DOT und 16 Ziffern: eine DayOne-Nummer. |
| `DOT1482917364058291` | DOT1 und 15 weitere Ziffern: ebenfalls DayOne. Der DayOne-Tracker schlägt diese in einem anderen System nach. |
| `DOT048291736405829` | Nur 15 Ziffern: kein DayOne-Format, und Peek erkennt DayOne nicht. Prüfe, ob eine Ziffer fehlt. |

In Peek spielen Kleinschreibung, Leerzeichen und Bindestriche keine Rolle, wenn du die Nummer allein einfügst. In einer ganzen Nachricht schreibe DOT direkt vor die Ziffern: Steht ein Leerzeichen dazwischen, liest Peek möglicherweise nur die Ziffern und nennt einen anderen Paketdienst.

**Wo du sie findest.** In der Versandmail des Shops oder auf deiner Bestellseite. Falls du sie nicht findest, siehe [Sendungsnummer finden](guide:find-tracking-number).

**Im DayOne-Tracker.** Du kannst bis zu 10 Nummern gleichzeitig prüfen, eine pro Zeile. Der Tracker sucht nur Nummern mit DOT0 oder DOT1: Jede andere Nummer, etwa die deiner örtlichen Post, zeigt `Not found`. Neben der Sendungsnummer kann ein Ergebnis eine `Reference Number` (die eigene Bestellreferenz des Shops) und das Zielland anzeigen.

## DayOne-Sendungsstatus einfach erklärt

Der DayOne-Tracker vergibt für jedes Paket einen Gesamtstatus auf Englisch und Chinesisch und listet unter `Package Track` die Scans, neuestes zuerst:

| DayOne-Status | Was das für dich bedeutet | Peek zeigt |
| --- | --- | --- |
| `Not found` (查询不到) | DayOne hat unter dieser Nummer noch nichts erfasst. Der Tracker ergänzt: „The order information has not been updated yet“. | „Warten auf DayOne“ |
| `Info received` (收到信息) | Die Versanddaten liegen vor. Das Paket hat das Lager womöglich noch nicht verlassen. | „Etikett erstellt“ |
| `In transit` (运输途中) | Unterwegs. | „Unterwegs“ |
| `Pick up` (到达待取) | Angekommen und wartet auf deine Abholung. | „Abholbereit“ |
| `Out for Delivery` (派送途中) | In Zustellung. | „In Zustellung“ |
| `Undelivered` (投递失败) | Ein Zustellversuch ist fehlgeschlagen. | „Zustellversuch“ |
| `Delivered` (成功签收) | Zugestellt. | „Zugestellt“ |
| `Alert` (可能异常) | Möglicherweise liegt ein Problem vor. | „Problem gemeldet“ |
| `Expired` (运输过久) | Auf Chinesisch „zu lange unterwegs“: Das Paket ist schon sehr lange unterwegs. | Status des neuesten Scans |

Peek richtet sich nach dem neuesten Scan, den es versteht. DayOnes Gesamtstatus nutzt es nur, wenn keiner der Scans zugeordnet werden kann.

Jeder Scan nennt Uhrzeit, Ort und den Wortlaut des Unternehmens, das das Paket gerade hat: Yanwen in China, danach die Post in deinem Land. Die häufigsten Scans in der üblichen Reihenfolge:

| DayOne meldet | Was das für dich bedeutet | Peek zeigt |
| --- | --- | --- |
| `Order processed by shipper` | Yanwen hat die Auftragsdaten. Das Paket wurde noch nicht physisch gescannt. | „Etikett erstellt“ |
| `Yanwen Pickup Scan`, `Processing information input` | Yanwen hat das Paket in China übernommen und bearbeitet es. | „Beim Paketdienst eingegangen“ |
| `Yanwen facility - Outbound`, `Arrived at domestic terminal station` | Hat Yanwens Sortierzentrum verlassen und ein Terminal in China vor dem Ausfuhrzoll erreicht. | „Unterwegs“ |
| `Port of departure - Received by carrier`, `International shipment release - Export`, `Port of departure - Departure` | Am Abflughafen, durch den chinesischen Ausfuhrzoll (nicht deinen) und dann abgeflogen. | „Unterwegs“ |
| `Port of destination - Arrival`, `ARRIVED AT DESTINATION AIRPORT` | In deinem Land oder in der Nähe gelandet. Der Einfuhrzoll steht noch aus. | „Unterwegs“ |
| `CLEARED BY CUSTOMS`, `COLLECTED FROM AIRPORT TERMINAL`, `ARRIVED AT FACILITY IN DESTINATION COUNTRY` | Dein Zoll hat es freigegeben, und es wurde vom Flughafen in eine Sortieranlage gebracht. | „Unterwegs“ |
| `RECEIVED BY AUSTRALIA POST FOR TRANSPORTATION TO PROCESSING FACILITY` | In Australien: an Australia Post übergeben. | „Unterwegs“ |
| `ITEM PROCESSED AT POST FACILITY`, `PROCESSED THROUGH AUSTRALIA POST FACILITY`, `TRANSFERRED` | Von der Post sortiert und weitergeleitet. | „Unterwegs“ |
| `WITH AUSTRALIA POST FOR DELIVERY TODAY` | Heute in Zustellung. | „In Zustellung“ |
| `DELIVERED WITH SAFE DROP` | Zugestellt und an einem sicheren Ablageort hinterlegt. | „Zugestellt“ |

`SHIPPING INFORMATION RECEIVED`, `SHIPPING INFORMATION RECEIVED BY AUSTRALIA POST` und `SHIPPING INFORMATION APPROVED BY AUSTRALIA POST` bedeuten, dass der Post die Paketdaten vorliegen. Sie können auch nach späteren Scans auftauchen: Das ist kein Rückschritt, und Peek behält den späteren Stand bei.

Ist der einzige Scan `Shipment has begun. In transit to destination city`, hat DayOne das Paket an [YunExpress](carrier:yunexpress) übergeben, und der weitere Verlauf steht bei YunExpress unter einer YunExpress-Nummer (`YT` und 16 Ziffern). `Clearence processing completed - Export` ist YunExpress’ Ausfuhrfreigabe in deren Schreibweise.

## Wer DayOne-Pakete zustellt

DayOne gibt nicht an, wer in deinem Land ausliefert. Die Scans zeigen, wer das Paket übernimmt: Yanwen in China, danach die Post deines Landes, wie [Australia Post](carrier:australia-post) für Sendungen nach Australien.

DayOne veröffentlicht keine Regeln für Zustellversuche, Benachrichtigungskarten oder Abholpunkte – diese legt das zustellende Unternehmen fest. Schau nach `Pick up` oder `Undelivered` nach einer Karte oder Nachricht dieses Zustellers. Siehe [verpasste Zustellungen](guide:missed-delivery) und [nicht abgeholte Pakete](guide:uncollected-parcel).

**Zoll in der EU.** Online gekaufte Waren aus Nicht-EU-Ländern tragen seit dem 1. Juli 2026 einen EU-Zoll von 3 € pro Artikel für Pakete bis 150 € Warenwert sowie seit dem 1. November 2026 eine Unions-Bearbeitungsgebühr von 2 € pro Artikel unabhängig vom Wert. Als ein Artikel gelten Waren mit derselben zolltariflichen Einreihung, Beschreibung und – sofern erforderlich – demselben Ursprung. DayOnes Website sagt nichts über Zölle, Gebühren oder deren Einzug. Anderswo gelten die Vorschriften deines Landes: siehe [Pakete beim Zoll](guide:customs).

## DayOne-Paket ohne Update, verloren oder zurückgesandt

DayOne verweist an den Shop. Im Tracker heißt es sinngemäß, dass man sich bei allen Fragen zu Logistik, Verlust, Erstattung oder Neuversand an den Kundenservice des bestellenden Shops wenden soll.

**`Not found`.** DayOne hat unter dieser Nummer noch nichts. Prüfe die Eingabe: DOT und 16 Ziffern. Stimmt sie, wurde das Paket vielleicht noch nicht verschickt. Peek zeigt „Warten auf DayOne“ und prüft weiter. Kennt DayOne die Nummer zwei Tage später immer noch nicht und hat auch kein anderer Dienst geantwortet, zeigt Peek „Auch nach zwei Tagen kennt der Paketdienst diese Nummer nicht. Prüfe sie, wähle einen anderen Paketdienst oder lösche das Paket.“ und prüft ab dann täglich, und nach einer Woche seit dem Hinzufügen wöchentlich. Kommt nach einigen Tagen nichts, frag beim Shop nach.

**Nur `Info received` oder `Order processed by shipper`.** Noch niemand hat das Paket gescannt. Bleibt das über mehrere Tage so, frag den Shop, wann es verschickt wurde.

**Tagelang kein neuer Scan.** Auf langen Flügen oder beim Zoll bleiben Pakete oft still. Bleibt ein Paket im Status „Etikett erstellt“, „Beim Paketdienst eingegangen“ oder „Unterwegs“ vier Tage ohne neuen Scan, meldet Peek „Seit vier Tagen keine Aktualisierung“ (oder „Keine Aktualisierung seit …“), erklärt: „Auf langen Flügen, beim Zoll oder übers Wochenende wird es oft still um ein Paket. Peek fragt weiter bei DayOne nach.“ und bietet „Hol dir Hilfe bei DayOne“ an. Nach drei Wochen ohne neuen Scan zeigt Peek: „Seit über drei Wochen nichts Neues. Ist das Paket noch nicht angekommen, frag beim Verkäufer nach. Peek fragt weiter bei DayOne nach.“ Siehe [warum die Sendungsverfolgung stehen bleibt](guide:tracking-not-updating) und [Pakete aus China verfolgen](guide:tracking-from-china).

**`Alert` oder `Expired`.** Lies die neuesten Scans und bitte dann den Shop unter Angabe deiner DOT-Nummer um Hilfe.

**Verloren, beschädigt oder zurückgesandt.** DayOne veröffentlicht kein Reklamationsverfahren für Empfänger. Bitte den Shop um Erstattung oder Ersatzlieferung. Siehe [verlorene Pakete](guide:lost-parcel) und [zurück an den Absender](guide:returned-to-sender).

**Zugestellt, aber nicht da.** `DELIVERED WITH SAFE DROP` bedeutet, dass das Paket an der Adresse sicher abgelegt wurde. Schau rund ums Haus nach, frag Nachbarn und melde es dann dem Shop. Siehe [Paket als zugestellt markiert, aber nicht erhalten](guide:delivered-not-received).

## DayOne Kontakt

Angaben auf dem [DayOne-Tracker](https://www.dayonetrack.com/):

- **Zu deinem Paket:** der Shop, bei dem du bestellt hast – wie DayOne es vorgibt.
- **Geschäftliche E-Mail:** info@dayone-shipping.com, von DayOne für Kooperationen und geschäftliche Anfragen außerhalb von Paketfragen genannt.

DayOnes Tracker nennt weder Telefonnummer noch Chat, Kontaktformular oder Anschrift. Laut eigenen Angaben speichert die Seite nur Frachtbriefnummern und keine Bestelldaten.

## Ein DayOne-Paket mit Peek verfolgen

[Peek](/) erkennt DayOne anhand einer DOT-Nummer mit 16 Ziffern sofort („Erkannter Paketdienst“). Auch ein `dayonetrack.com`-Link mit `trackNo=` funktioniert. Jede andere Nummer fügst du trotzdem einfach ein: Peek verfolgt mehr als 4.500 Paketdienste. Passt das Format zu mehreren Diensten, zeigt Peek beim Prüfen „Tracking-Dienste werden geprüft…“. Nennt Peek für deine Nummer nicht DayOne, tippe auf „Ändern“ und wähle DayOne unter „Alle Paketdienste“ (auch die Suche nach „Day One“ findet es), oder nutze „Paketdienst ändern“ bei einem bereits hinzugefügten Paket.

Bei einer `DOT0`-Nummer liest Peek direkt DayOnes eigenen Tracker aus: Status, jeden Scan mit Ort, Zielland und Zustellzeit. Bei einer `DOT1`-Nummer fragt es stattdessen Tracking-Dienste ab.

Nennt DayOne bei einem Scan den Zeitversatz seines Ortes, übernimmt Peek ihn. Fehlt er, leitet Peek die Uhrzeit aus dem Ort oder den umliegenden Scans ab (die Zeit deines Landes, sobald ein Ankunftsscan vorliegt). Alle Zeiten erscheinen in der Zeitzone deines Geräts. Peek lässt Postleitzahlen, den Namen der Person, die ein Paket entgegengenommen oder unterschrieben hat, sowie die `Reference Number` des Shops weg. DayOne nennt weder ein voraussichtliches Lieferdatum noch ein Gewicht.

DayOnes Website zeigt die Nummer des zustellenden Unternehmens nicht an, doch seine Abfrage liefert sie manchmal mit. Peek fragt dann auch dort nach: bei der Post deines Landes für eine Postnummer (zwei Buchstaben, neun Ziffern, zwei Buchstaben) wie Royal Mail, USPS, Canada Post, An Post oder Australia Post, oder beim eindeutigen Dienst für das Format, etwa YunExpress bei einer `YT`-Nummer. Sobald dieser Dienst einen Scan mindestens so aktuell wie DayOnes neuesten liefert, verfolgt Peek das Paket dort weiter, zeigt dessen Nummer neben der DOT-Nummer und behält DayOnes Verlauf.

Bei einer `DOT0`-Nummer prüft Peek – solange du das Paket in Peek geöffnet hast oder am Zustelltag Benachrichtigungen aktiviert sind – bis zu alle 10 Minuten, und ab 6 Uhr Ortszeit am Tag, an dem DayOne das Paket in Zustellung scannt, bis zu alle 2 Minuten, solange DayOnes eigener Tracker antwortet; nach zwei Tagen ohne Neuigkeiten (nach 12 Stunden bei „Etikett erstellt“ oder „Beim Paketdienst eingegangen“) stündlich, und nach sieben Tagen bei „Etikett erstellt“ alle 6 Stunden. Bei einer `DOT1`-Nummer (über Tracking-Dienste) prüft Peek stündlich, alle 3 Stunden nach einem Tag ohne neuen Scan, alle 6 Stunden nach sieben Tagen bei „Etikett erstellt“, und an dem Tag, an dem das Paket in Zustellung ist, bis zu alle 15 Minuten, solange du das Paket geöffnet hast oder Benachrichtigungen aktiviert sind. Peek kann dich für „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“ benachrichtigen. Es kann weder DayOne noch den Shop für dich kontaktieren.

## Häufige Fragen zur DayOne-Sendungsverfolgung

### Ist DayOne ein Paketdienst?

Nein. DayOne bezeichnet sich selbst als „order fulfillment warehouse, not a courier delivery service provider“. Andere Unternehmen übernehmen den Transport: laut DayOnes Scans zunächst Yanwen in China und dann die Post in deinem Land.

### Wo kann ich eine DOT-Nummer verfolgen?

Im DayOne-Tracker unter [dayonetrack.com](https://www.dayonetrack.com/) auf Englisch oder Chinesisch, bis zu 10 Nummern auf einmal – oder direkt in Peek.

### Warum zeigt DayOnes Tracker Not found?

DayOne hat unter dieser Nummer noch keine Daten. Prüfe, ob du DOT und alle 16 Ziffern richtig eingegeben hast. Stimmt die Nummer, warte ein bis zwei Tage und frag dann im Shop, ob das Paket schon abgeschickt wurde.

### Warum wirken die Uhrzeiten im DayOne-Tracker durcheinander?

Jeder Scan zeigt die Ortszeit des jeweiligen Standorts ohne Zeitzone, und der Tracker sortiert die Einträge nach diesen bloßen Uhrzeiten. Im europäischen Winter liegt Peking 8 Stunden vor London und 13 vor New York, weshalb ein Scan in deinem Land älter wirken kann als der letzte aus China. Sydney liegt im australischen Sommer 3 Stunden vor Peking, sodass Scans dort neuer aussehen. Peek rechnet jede Zeit in die Zeitzone deines Geräts um.

:::sources
- [DayOne: Dayone Tracking](https://www.dayonetrack.com/) – Lager, kein Kurier; Kundendienst über den Shop; Datenschutzhinweis; geschäftliche E-Mail; Englisch und Chinesisch; 10 Nummern gleichzeitig; DOT0- und DOT1-Abfragen; Status; Ergebnisfelder; Zeiten
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 3-€-Zoll seit 1. Juli 2026, 2-€-Unions-Bearbeitungsgebühr seit 1. November 2026
- [European Commission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 2 € pro Artikel unabhängig vom Wert, Definition eines Artikels
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Nummernformat, Links und Abfragen, Scan-Wortlaute und Codes, Zeitzonen, Nummer des Zustellers, Übergabe, was Peek ausliest
:::
