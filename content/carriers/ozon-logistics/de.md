---
title: Ozon Sendungsverfolgung: Nummern und Status auf Deutsch
description: Ozon-Bestellung (Ozon Rocket, Ozon Доставка) verfolgen: Aufbau der Nummern mit Bindestrichen, Bedeutung der russischen Status auf Deutsch und Peek.
slug: ozon-sendungsverfolgung
published: 2026-11-21
updated: 2026-11-21
---

Der Lieferdienst von Ozon befördert Bestellungen, die Verkäufer an ihn übergeben, bis an deine Tür oder an eine Abholstelle. Peeks Katalog führt ihn für Russland, Belarus und Kasachstan, auch unter den Namen Ozon Rocket, OZON Rocket Доставка, Ozon Доставка und Ozon Dostavka. Füge deine Nummer oben samt Bindestrichen ein. Der eigene Tracker von Ozon ist tracking.ozon.ru, den Peek wie unten beschrieben für dich ausliest.

## Ozon-Sendungsnummer: So sieht sie aus

Peek kennt zwei Formate für Ozon, beide mit Bindestrichen. Die Bindestriche sind wichtig: Ohne sie sehen die Ziffern aus wie Nummern anderer Paketdienste. Erfundene Beispiele:

| Nummer (erfunden) | Was es ist |
| --- | --- |
| `48291057-0362-1` | 8 Ziffern, 4 Ziffern und 1 Ziffer mit Bindestrichen. Peek erkennt Ozon sofort. |
| `4829105736-2841-2` | Dasselbe Format mit 10 Ziffern am Anfang. Auch hier erkennt Peek Ozon sofort. |
| `4829105703621` | Die erste Nummer ohne Bindestriche oder mit Leerzeichen. DPD France, Ukrposhta und weitere Dienste nutzen 13-stellige Nummern, sodass die Ziffern allein nicht auf Ozon schließen lassen. Aus Russland, Belarus oder Kasachstan fragt Peek zuerst Ozon; von anderswo fragt es Ozon zuletzt, wenn die anderen Dienste und die Tracking-Dienste die Nummer nicht gefunden haben. Tippe die Bindestriche wieder ein oder wähle Ozon selbst (unten). Dasselbe gilt für 15 Ziffern, die auch FedEx und andere verwenden. |
| `PL-482910-57362` | `PL`, 6 Ziffern, dann weitere Buchstaben oder Ziffern mit Bindestrichen. Peek erkennt Ozon. |
| `PL-482910-5736` | `PL` mit genau 10 Ziffern entspricht zugleich dem Format von [Amazon](carrier:amazon-logistics). Peek behandelt sie als Amazon-Nummer und lässt Ozon dafür nicht auswählen. |

Kleinschreibung spielt keine Rolle: `pl-` wirkt wie `PL-`. Nummer nicht gefunden? Siehe [Sendungsnummer finden](guide:find-tracking-number) und [Sendungsnummern-Formate](guide:tracking-number-formats).

## Ozon-Statusmeldungen auf Deutsch

Peeks Code listet die Schritte des Ozon-Trackers auf Russisch auf. Peek liest diesen Tracker aus und zeigt die Schritte im russischen Original an – außer `Заказ в работе`, `Заказ на пути к границе страны назначения` und `Заказ прибывает на приграничный пункт`, die ausgefiltert werden (siehe „Eine Ozon-Bestellung mit Peek verfolgen“).

**Im Inland.** Grob in der Reihenfolge ihres Auftretens:

| Meldung von Ozon | Was das für dich bedeutet |
| --- | --- |
| `Передается в доставку` | Der Verkäufer hat die Bestellung verpackt und übergibt sie zur Lieferung. Ozon hat sie möglicherweise noch nicht. |
| `Заказ принят перевозчиком.` | Der Paketdienst hat die Bestellung übernommen: Sie ist unterwegs. |
| `Заказ отправили на сортировочный терминал`, `Заказ отправлен на сортировочный терминал.` | An ein Sortierzentrum geschickt. |
| `Заказ покинул сортировочный терминал.` | Hat das Sortierzentrum verlassen. |
| `Заказ ожидает отправки в город получателя.` | Wartet auf den Weiterversand in deine Stadt. |
| `Заказ везут в город получателя` | Unterwegs in deine Stadt. |
| `Заказ везут` | Unterwegs. |
| `Заказ передали в курьерскую доставку` | An die Kurierzustellung übergeben; der Kurier ruft an, um eine Zeit zu vereinbaren. |
| `Заказ в пункте выдачи` | Liegt an deiner Abholstelle bereit. |
| `Заказ получен в пункте выдачи.` | An der Abholstelle oder Postfiliale abgeholt: zugestellt. |
| `Заказ успешно доставлен получателю.` | Zugestellt. Erscheint, wenn die Bestellung nicht über eine Abholstelle oder Postfiliale lief, etwa bei Kurierzustellung. |
| `Заказ отменён` | Die Bestellung wurde storniert. |
| `Заказ в работе` | In Bearbeitung: Platzhalter im Ozon-Tracker, kein echter Transportschritt. |

**Aus dem Ausland.** Bei grenzüberschreitenden Bestellungen kommen Zollschritte hinzu, ungefähr in dieser Reihenfolge:

| Meldung von Ozon | Was das für dich bedeutet |
| --- | --- |
| `Заказ везут на таможню в стране отправления` | Auf dem Weg zum Zoll im Absendeland. |
| `Заказ привезли на таможню для экспортного таможенного оформления` | Beim Ausfuhrzoll im Absendeland eingetroffen. |
| `Заказ покинул зону экспортного таможенного оформления` | Hat die Ausfuhrabfertigung verlassen. |
| `Заказ на пути к границе страны назначения` | Auf dem Weg zur Grenze deines Landes. |
| `Заказ прибывает на приграничный пункт` | Trifft am Grenzübergang ein. |
| `Заказ привезли в страну назначения` | Im Zielland angekommen. |
| `Заказ передан на импортное таможенное оформление.` | An den Einfuhrzoll in deinem Land übergeben. |
| `Заказ проходит импортное таможенное оформление` | Durchläuft die Einfuhrabfertigung. |
| `Заказ выпущен импортной таможней.` | Vom Einfuhrzoll freigegeben: wieder unterwegs. |
| `Импортная таможня задержала заказ` | Der Einfuhrzoll hält die Bestellung zur weiteren Prüfung zurück. |

## Wie Ozon zustellt: Kurier oder Abholstelle

Die Statuszeilen zeigen, auf welchem Weg deine Bestellung kommt.

**An deine Tür.** Nach `Заказ передали в курьерскую доставку` (in Peek als „Unterwegs“ geführt) bringt ein Kurier die Sendung und ruft vorher an, um eine Zeit auszumachen. Der letzte Schritt ist `Заказ успешно доставлен получателю.`

**An eine Abholstelle.** Warte auf `Заказ в пункте выдачи` („Abholbereit“) und hole die Bestellung ab. Danach zeigt der Tracker `Заказ получен в пункте выдачи.` – derselbe Wortlaut gilt bei Abholung in einer Postfiliale.

Da Peek die Website von Ozon nicht auslesen konnte, nennt diese Seite weder Lieferzeiten noch Öffnungszeiten, Lagerfristen oder Regeln zu Zustellversuchen: Frage dazu Ozon. Siehe auch [nicht abgeholtes Paket](guide:uncollected-parcel) und [verpasste Zustellung](guide:missed-delivery).

**Aus dem Ausland.** Die Zollschritte oben zeigen den Stand grenzüberschreitender Sendungen. Zollregeln oder Gebühren nennt diese Seite nicht, da Peek sie auf keiner offiziellen Seite prüfen konnte. Siehe [Paket beim Zoll](guide:customs).

## Ozon-Bestellung kommt nicht an oder Status steht still

**Noch nichts oder nur `Передается в доставку`.** Der Verkäufer hat die Sendung eventuell noch nicht übergeben, oder der Ozon-Tracker führt die Nummer noch nicht. Peek fragt weiter nach. Hast du die Nummer ohne Bindestriche hinzugefügt, wähle Ozon über „Paketdienst ändern“.

**Peek hinkt tracking.ozon.ru hinterher.** Antwortet der Ozon-Tracker Peek nicht, springt ein Tracking-Dienst ein, der hinterherhinken kann. Vergleiche zuerst mit tracking.ozon.ru.

**Tagelang kein neuer Schritt.** Ist der letzte Eintrag mindestens vier Tage alt, während Peek „Etikett erstellt“, „Beim Paketdienst eingegangen“ oder „Unterwegs“ zeigt, meldet Peek das mit dem Tag dieses Eintrags („Keine Aktualisierung seit Mo 12. Okt“) und verlinkt auf die Ozon-Hilfeseite („Hol dir Hilfe bei Ozon“). Mehr in [Sendungsverfolgung aktualisiert sich nicht](guide:tracking-not-updating).

**`Импортная таможня задержала заказ`.** Der Zoll prüft die Bestellung. Warte auf `Заказ выпущен импортной таможней.`; ändert sich lange nichts, frage bei Ozon nach.

**`Заказ отменён`.** Die Bestellung wurde storniert und wird nicht geliefert. Kläre eine Erstattung mit Ozon oder dem Shop.

**Zugestellt, aber nichts erhalten.** Prüfe, ob jemand das Paket für dich abgeholt hat, und kontaktiere Ozon. Siehe [Paket zugestellt, aber nicht erhalten](guide:delivered-not-received).

**Verloren.** Wende dich wegen deiner Bestellung an Ozon oder den Shop, bei dem du gekauft hast. Siehe [verlorenes Paket](guide:lost-parcel).

## Ozon Kundenservice und Kontakt

Da Peek die Website von Ozon nicht auslesen konnte, nennt diese Seite weder Telefonnummer noch E-Mail-Adresse: Peek gibt nur Kontakte an, die auf den Seiten des Paketdienstes selbst geprüft wurden. Ozons eigener Tracker liegt unter tracking.ozon.ru.

Bei einer direkt auf Ozon gekauften Bestellung kontaktiere Ozon. Stammt die Bestellung aus einem anderen Shop, wende dich zuerst an diesen Shop, der die Lieferung beauftragt hat.

## Eine Ozon-Bestellung mit Peek verfolgen

[Peek](/) verfolgt eine Ozon-Nummer allein anhand der Nummer – ohne Konto, Telefonnummer oder Code.

- **Mit Bindestrichen.** Peek erkennt Ozon sofort („Erkannter Paketdienst“) und legt das Paket unter Ozon an.
- **Ohne Bindestriche.** An den bloßen Ziffern erkennt Peek den Dienst nicht sicher. Füge sie dennoch ein: Peek verfolgt über 4500 Paketdienste. Während es infrage kommende Dienste und Tracking-Dienste prüft, zeigt es „Tracking-Dienste werden geprüft…“. Aus Russland, Belarus oder Kasachstan fragt Peek Ozon zuerst, von anderswo zuletzt, wenn die anderen Dienste und die Tracking-Dienste die Nummer nicht gefunden haben. Nennen Tracking-Dienste Ozon mit datierten Schritten, ordnet Peek das Paket selbst dort ein. Oder tippe auf „Ändern“ und suche nach Ozon (bzw. „Paketdienst ändern“ bei einem bereits gespeicherten Paket).
- **Ein Link.** Füge einen Link zu tracking.ozon.ru mit `track=` oder rocket.ozon.ru mit `SearchId=` ein, und Peek verfolgt die Nummer bei Ozon.

**Woher die Schritte stammen.** Peek liest den Ozon-Tracker und zeigt jeden Schritt im russischen Original mit Uhrzeit sowie Ozons voraussichtlichen Lieferdaten. Zeitstempel mit Zeitzonenangabe liest Peek exakt, solche ohne Versatz als Moskauer Zeit, und zeigt alle Zeiten in der Zeitzone deines Geräts; auf peektracker.com sowie in den iPhone- und Android-Apps endet der Verlauf mit „Uhrzeiten in deiner Zeitzone:“ und deren Namen. Da Ozon keine Orte nennt, erscheint keine Karte. Solange Ozon die Nummer nicht kennt, zeigt Peek „Warten auf Ozon“ – oder „Noch kein Sendungsverlauf“ bei einer Nummer ohne Paketdienst. Antwortet Ozons Tracker nicht, fragt Peek der Reihe nach ParcelsApp, Ship24 und 17TRACK und zeigt den Verlauf des ersten Dienstes, der das Paket kennt, in dessen Worten (mit möglichem Rückstand gegenüber tracking.ozon.ru). Peeks direkte Ozon-Abfrage nimmt keine `PL-`-Nummern an: Dafür steht „Sendungsnummer prüfen“, bis ein Tracking-Dienst die Sendung findet.

**Der Link auf der Paketseite.** „Website von Ozon öffnen“ öffnet die Nummer auf tracking.ozon.ru. Ist ein Tracking-Dienst eingesprungen, führt der Link dorthin, etwa „Website von ParcelsApp öffnen“.

**Sendung eines anderen Paketdienstes.** Peek speichert die Ozon-Nummer ohne Bindestriche. Solange Ozons Tracker sie noch nicht kennt, fragt eine ergebnislose Prüfung in den ersten 30 Tagen vor den Tracking-Diensten auch ausgewählte Dienste derselben Ziffernlänge mit ab, etwa Ukrposhta und Delhivery bei 13 oder Hermes Germany und BRT bei 15 Ziffern. Meldet einer davon Bewegung, wechselt Peek dorthin und zeigt „Automatisch von Ozon gewechselt“.

**Zustellung durch einen anderen Dienst.** Nennt ein Tracking-Dienst einen anderen Paketdienst samt eigener Nummer und liest Peek dessen eigenes Tracking, fragt Peek auch dort nach; sobald ein mindestens gleich aktueller Scan erscheint, folgt Peek dem Paket dort weiter.

**Prüftakt und Mitteilungen.** Solange du das Paket geöffnet hast oder am Zustelltag mit aktivierten Mitteilungen und Ozons Tracker antwortet, prüft Peek bis zu alle 10 Minuten; sonst stündlich. Solange ein Tracking-Dienst das Paket führt, prüft Peek stündlich, am Zustelltag bis zu alle 15 Minuten (solange es geöffnet ist oder Mitteilungen aktiv sind), und alle 3 Stunden nach einem Tag ohne neuen Schritt. Mitteilungen gibt es für „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“. Für eine Abholstelle wähle „Wichtige Schritte“: „Nur die Zustellung“ meldet nicht, wenn das Paket abholbereit liegt. Peek kann weder Ozon noch den Verkäufer für dich kontaktieren.

## Fragen zur Ozon-Sendungsverfolgung

### Wie sieht eine Ozon-Sendungsnummer aus?

Peek ordnet Ozon zwei Formate mit Bindestrichen zu: drei Zifferngruppen (8 oder 10 Ziffern, dann 4, dann 1, wie `48291057-0362-1`) sowie Nummern ab `PL-`, etwa `PL-482910-57362`. Gib die Bindestriche unbedingt mit ein, sonst kann Peek Ozon nicht eindeutig erkennen.

### Warum erkennt Peek meine Ozon-Nummer nicht?

Wahrscheinlich hast du sie ohne Bindestriche oder mit Leerzeichen eingegeben. Gib sie mit deinen eigenen Ziffern als `48291057-0362-1` ein oder tippe auf „Ändern“ und wähle Ozon.

### Was bedeutet „Заказ в пункте выдачи“?

Deine Bestellung liegt an deiner Abholstelle bereit und kann abgeholt werden. Nach der Abholung zeigt der Ozon-Tracker `Заказ получен в пункте выдачи.`

### Kann ich eine Ozon-Bestellung auf Deutsch verfolgen?

Ja. Peek zeigt den Gesamtstatus auf Deutsch an (zum Beispiel „Unterwegs“) und die einzelnen Einträge im russischen Wortlaut von Ozon; die Tabellen oben übersetzen alle Schritte.

:::sources
- [Universal Parcel Scraper: Ozon](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/ozon-logistics) – Namen, bediente Länder (`RU`, `BY`, `KZ`), Formate mit Bindestrichen, Links tracking.ozon.ru und rocket.ozon.ru, Hilfeseite `docs.ozon.ru/common/dostavka/`, gelesene Felder und ausgefilterte Platzhalter (`FantomDelivery` und Grenz-Platzhalter), russische Schritte und Stufen, Kurieranruf, Abholung an Abholstelle oder Postfiliale
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Paketdienste mit 13 und 15 Ziffern, Amazon-Nummern (`PL-` + 10 Ziffern), Reihenfolge der Tracking-Dienste, in den ersten 30 Tagen abgefragte Dienste, Übergaben aus Tracking-Diensten
:::
