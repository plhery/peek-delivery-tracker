---
title: Blue Dart Sendungsverfolgung: Nummern und Status erklärt
description: Sendung von Blue Dart verfolgen: 11-stellige Waybill-Nummer, Bedeutung von Meldungen wie Shipment Further Connected und Tracking mit Peek.
slug: blue-dart-sendungsverfolgung
published: 2026-11-22
updated: 2026-11-22
---

Blue Dart ist ein indischer Kurierdienst: Der Katalog von Peek führt ihn für Indien und liest seine Scans nach indischer Ortszeit. Eine Blue-Dart-Sendung hat eine 11-stellige Frachtbriefnummer (*Waybill*), die du vom Absender bekommst. Bei einer Online-Bestellung steht sie zum Beispiel in der Versandmail des Shops oder auf der Bestellseite. Füge sie oben ins Feld ein oder gib sie im Tracking auf bluedart.com ein. Da Peek die Website von Blue Dart für diese Seite nicht abrufen konnte, stammen alle Angaben hier aus dem, was der Code von Peek über das Blue-Dart-Tracking weiß.

## Blue Dart Waybill-Nummer: wie sie aussieht

Peek kennt genau ein Format für Blue Dart: 11 Ziffern, wobei die letzte Ziffer eine aus den ersten zehn berechnete Prüfziffer ist. Mit erfundenen Ziffern:

| Nummer (erfunden) | Was es ist |
| --- | --- |
| `48291057360` | 11 Ziffern mit gültiger Prüfziffer: die Form einer Blue-Dart-Waybill. Auch die GLS-Netze, PostLogistics, Japan Post, Mainfreight und andere verwenden 11 Ziffern, sodass die Form allein nicht eindeutig auf Blue Dart hinweist. |
| `48291057361` | Dasselbe Format mit falscher Prüfziffer. Peek schlägt Blue Dart dafür nicht vor: Prüfe die Nummer auf Tippfehler. |
| `4829105736` | 10 Ziffern: keine Blue-Dart-Waybill. Wenn du Blue Dart dafür auswählst, meldet Peek, dass Blue Dart „vergibt keine Nummern wie diese“. Dasselbe gilt für 12 Ziffern oder Nummern mit Buchstaben. |

Die 11-stelligen Frachtbriefe von Aramex und Einschreiben von Japan Post verwenden dieselbe Prüfziffer; selbst eine gültige Nummer kann also von dort stammen. Prüfe, welchen Paketdienst der Shop nennt. Leerzeichen und Bindestriche spielen in Peek keine Rolle. Blue Darts eigener Tracker sucht auch nach anderen Referenzen, Peek benötigt aber die Waybill-Nummer. Findest du sie nicht? Siehe [wo du deine Sendungsnummer findest](guide:find-tracking-number).

## Statusmeldungen von Blue Dart erklärt

Blue Darts Tracker zeigt den aktuellen Status der Sendung und darunter die Scans, beginnend mit dem neuesten, jeweils mit Ort, Datum und Uhrzeit. Die Meldungen, die Peek aus dem Blue-Dart-Tracking kennt, ungefähr in der Reihenfolge eines Versands:

| Blue Dart schreibt | Was das für dich bedeutet | Peek zeigt |
| --- | --- | --- |
| `Pickup Employee Is Out To P/U Shipment` | Ein Blue-Dart-Kurier ist unterwegs, um die Sendung beim Absender abzuholen (*P/U*). Blue Dart hat sie noch nicht. | „Etikett erstellt“ |
| `Shipment Picked Up` | Blue Dart hat die Sendung beim Absender abgeholt. | „Beim Paketdienst eingegangen“ |
| `Shipment Arrived`, `Shipment Arrived At Hub` | An einem Blue-Dart-Standort oder Hub eingetroffen; der Scan-Ort nennt ihn. | „Unterwegs“ |
| `Paper Work Inscan` | In einem Hub gescannt, zwischen Ankunft und Weiterleitung. | „Unterwegs“ |
| `Clubbed Canvas Bag Scan` | Zusammen mit anderen Sendungen in einen Transportsack gescannt. | „Unterwegs“ |
| `Shipment Further Connected` | Von diesem Standort zur nächsten Station weitergeschickt. | „Unterwegs“ |
| `Delivery Scheduled For Next Working Day` | Im zustellenden Depot eingetroffen, aber nach dem täglichen Annahmeschluss: geht am nächsten Werktag in die Zustellung. | „Unterwegs“ |
| `Shipment Out For Delivery` | Auf der Zustelltour. | „In Zustellung“ |
| `Cnee Refused Id/Otp Not Shared-Incorrect` | Nicht zugestellt wegen Annahmeverweigerung durch den Empfänger (*Cnee*, *consignee*) oder weil Ausweis beziehungsweise Einmalcode (*OTP*) nicht genannt wurden oder falsch waren. | „Zustellversuch“ |
| `Delivery Delayed`, `Delay Caused Beyond Control` | Die Zustellung verspätet sich; der zweite Text nennt Gründe außerhalb des Einflusses von Blue Dart. | „Problem gemeldet“ |
| `Shipment Delivered` | Zugestellt. | „Zugestellt“ |
| `Shipment Returned Back To Shipper` | Die Sendung ist wieder beim Absender. Blue Dart zeigt das als Sendungsstatus an, sobald die Rücksendung abgeschlossen ist. | „Zurück an Absender“ |

Eine Zeile, die Peek noch nicht kennt, erscheint im Originalwortlaut von Blue Dart und zählt als „Unterwegs“, sofern das Paket nicht schon in Zustellung oder weiter ist: Dann bleibt der bisherige Status bestehen. Mehr unter [was Tracking-Status bedeuten](guide:tracking-statuses).

## Blue-Dart-Zustellung in Indien: Ausweis und OTP

Da Peek die Zustellregeln von Blue Dart – etwa die Zahl der Versuche oder die Lagerfrist – nicht prüfen konnte, nennt diese Seite keine.

**Ausweis oder OTP.** Die Meldung `Cnee Refused Id/Otp Not Shared-Incorrect` zeigt, dass eine Blue-Dart-Zustellung von einem Ausweis oder einem Einmalcode (*OTP*) abhängen kann. Wenn du einen Code erhalten hast, halte ihn am Liefertag bereit. Erscheint diese Zeile, obwohl du nichts verweigert hast, bitte den Shop um eine erneute Zustellung. Siehe [verpasste Zustellung](guide:missed-delivery) und [nicht abgeholte Pakete](guide:uncollected-parcel).

Fordert eine Nachricht über eine verpasste Zustellung dich auf, über einen Link zu zahlen oder einen Code preiszugeben? Siehe [gefälschte Paket-SMS](guide:delivery-scam-texts).

## Blue-Dart-Sendungen: Zusteller und Zoll

**Eine Blue-Dart-Waybill.** Peek kennt keinen Paketdienst, an den Blue Dart Sendungen im In- oder Ausland übergibt. Nennt der Shop zusätzlich die Nummer eines anderen Paketdienstes, füge auch diese hinzu.

**Versendet mit ICC Worldwide.** Der Tracker von ICC Worldwide kann neben einer Blue-Dart-Nummer Blue Dart nennen. Peek wechselt nur dann zu Blue Dart, wenn diese Nummer 11 Ziffern hat: Es fragt Blue Dart damit an und übernimmt die Sendung dorthin, sobald Blue Dart einen mindestens ebenso aktuellen Scan zeigt, während der Verlauf von ICC Worldwide erhalten bleibt. Bei jeder anderen Nummer bleibt Peek bei ICC Worldwide. Füge die Sendung mit der ICC-Worldwide-Nummer hinzu, tippe auf „Ändern“ und suche nach ICC Worldwide – anhand der Nummer allein schlägt Peek es nicht vor.

**Zoll.** Der Code von Peek kennt Blue Dart nur in Indien und konnte keine Blue-Dart-Seiten zu Auslandssendungen prüfen. Bei Paketen im Zoll siehe [Pakete beim Zoll](guide:customs).

## Blue-Dart-Tracking hängt fest, verspätet sich oder geht zurück

Für eine Erstattung oder Ersatzlieferung ist der Shop dein Ansprechpartner: Er hat Blue Dart beauftragt.

**Noch keine Scans.** Kennt Blue Darts Tracker eine Nummer noch nicht, antwortet er `There is no information on the Waybill/Reference Number/Order Number currently.` Peek prüft weiter. Gleiche die 11 Ziffern mit der Nachricht des Shops ab; tut sich nach einigen Tagen nichts, frage den Shop, ob die Sendung bereits übergeben wurde.

**Steht auf `Pickup Employee Is Out To P/U Shipment`.** Blue Dart hat die Sendung noch nicht beim Absender abgeholt. Frage den Shop nach dem Übergabetermin.

**`Delivery Delayed` oder `Delay Caused Beyond Control`.** Peek zeigt „Problem gemeldet“. Bewegt sich danach nichts mehr, kontaktiere den Shop.

**Tagelang kein Update.** Wenn eine Sendung unterwegs vier Tage lang keinen neuen Scan erhält, kennzeichnet Peek sie mit dem Tag dieses Scans: „Keine Aktualisierung seit Mo 12. Okt“. Siehe [wenn sich das Tracking nicht aktualisiert](guide:tracking-not-updating).

**Zugestellt, aber nicht da.** Frage im Haushalt oder am Empfang des Gebäudes nach und wende dich dann an den Shop. Siehe [zugestellt, aber nicht erhalten](guide:delivered-not-received).

**Verloren oder zurückgesendet.** Bitte den Shop um Erstattung oder Neulieferung: siehe [verlorene Pakete](guide:lost-parcel). `Shipment Returned Back To Shipper` bedeutet, dass die Sendung wieder beim Absender ist: siehe [zurück an den Absender](guide:returned-to-sender).

## Blue Dart Kundenservice und Kontakt

Da Peek die Website von Blue Dart nicht abrufen konnte, nennt diese Seite weder Telefonnummer noch E-Mail-Adresse: Wir zeigen nur Kontakte, die auf den Seiten des Paketdienstes selbst geprüft wurden. Bleibt eine Sendung stehen, verlinkt Peek die Hilfeseite von Blue Dart unter „Hol dir Hilfe bei Blue Dart“.

Bei einer Online-Bestellung wende dich zuerst an den Shop, der Blue Dart beauftragt hat. Peek kann weder Blue Dart noch den Shop für dich kontaktieren.

## Eine Blue-Dart-Sendung mit Peek verfolgen

Füge die Waybill-Nummer in [Peek](/) ein. Da mehrere Paketdienste 11 Ziffern nutzen, zeigt Peek bei einer Blue-Dart-Nummer allein nicht sofort „Erkannter Paketdienst“ an:

- **In Indien** (Region deines Telefons oder im Web dein Standort) zeigt Peek „Tracking-Dienste werden geprüft…“, während es passende Paketdienste fragt – Blue Dart zuerst. Kennt Blue Dart die Nummer, erscheint „Blue Dart hat dieses Paket.“.
- **Außerhalb Indiens** fragt Peek Blue Dart zuletzt, wenn die anderen Paketdienste und die Tracking-Dienste die Nummer nicht kennen. Um die Sendung gleich Blue Dart zuzuordnen, tippe auf „Ändern“ und wähle Blue Dart unter „Passt zu dieser Nummer“.

Kennt noch kein Paketdienst die Nummer, füge sie trotzdem hinzu oder wähle Blue Dart direkt aus: Peek verfolgt mehr als 4500 Paketdienste. Peek listet Blue Dart automatisch nur bei gültiger Prüfziffer. Stimmt sie nicht, du bist aber sicher, suche Blue Dart in der Paketdienstliste: Peek fragt trotzdem nach. Ein Tracking-Link von bluedart.com mit der Waybill (`trackNo=`) funktioniert ebenfalls: Peek liest die Nummer aus und erkennt Blue Dart sofort („Erkannter Paketdienst“).

Unter Blue Dart angelegt, zeigt eine Sendung bis zum ersten Scan „Warten auf Blue Dart“ oder „Sendungsnummer prüfen“, wenn Blue Dart die Nummer nach zwei Tagen noch nicht kennt; ohne Paketdienst hinzugefügt „Noch kein Sendungsverlauf“. Peek liest den öffentlichen Tracker von Blue Dart allein über die Waybill, ohne Konto: den Gesamtstatus und jeden Scan im Wortlaut von Blue Dart samt Ort, gelesen nach indischer Ortszeit. Empfängerdaten werden weggelassen, ein voraussichtliches Lieferdatum gibt es nicht. Der Link „Website von Blue Dart öffnen“ auf der Paketseite öffnet die Waybill direkt im Blue-Dart-Tracker.

Solange du das Paket auf dem Bildschirm geöffnet hast, oder am Liefertag mit aktivierten Benachrichtigungen, prüft Peek bis zu alle 10 Minuten – und ab 6:00 Uhr Ortszeit am Tag, an dem Blue Dart die Sendung in Zustellung scannt, bis zu alle 2 Minuten –, solange der eigene Tracker von Blue Dart antwortet. Ansonsten sowie nach zwei Tagen ohne Neuigkeiten (nach 12 Stunden, solange die Sendung auf „Etikett erstellt“ oder „Beim Paketdienst eingegangen“ steht) prüft Peek stündlich. Solange ein Tracking-Dienst die Sendung führt, prüft Peek stündlich, am Zustelltag bis zu alle 15 Minuten (solange sie geöffnet ist oder Benachrichtigungen aktiv sind), und alle 3 Stunden nach einem Tag ohne neuen Scan. Benachrichtigungen gibt es für „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“.

## Fragen zur Blue-Dart-Sendungsverfolgung

### Wie verfolge ich eine Blue-Dart-Sendung?

Gib die 11-stellige Waybill-Nummer im Tracker auf bluedart.com ein oder füge sie in Peek ein. In Indien fragt Peek Blue Dart zuerst, anderswo zuletzt; du kannst auch auf „Ändern“ tippen und Blue Dart wählen.

### Wie sieht eine Blue-Dart-Waybill-Nummer aus?

11 Ziffern, die letzte ist eine Prüfziffer. Auch Aramex und andere nutzen 11 Ziffern – prüfe daher, ob die Shop-Nachricht Blue Dart nennt.

### Was bedeutet „Shipment Further Connected“ bei Blue Dart?

Blue Dart hat die Sendung von einem Standort zum nächsten auf ihrer Route weitergeleitet. Peek zeigt dafür „Unterwegs“.

### Was bedeutet „Cnee Refused Id/Otp Not Shared-Incorrect“?

Eine fehlgeschlagene Zustellung wegen Annahmeverweigerung durch den Empfänger oder fehlendem beziehungsweise falschem Ausweis oder Einmalcode (OTP). Peek zeigt „Zustellversuch“. Hast du nichts verweigert, bitte den Shop um eine neue Zustellung.

### Wen kontaktiere ich bei einer fehlenden Blue-Dart-Sendung?

Den Shop oder Absender, der Blue Dart beauftragt hat. Da Peek die Website von Blue Dart nicht prüfen konnte, führt diese Seite keine Blue-Dart-Kontaktdaten auf.

:::sources
- [Universal Parcel Scraper: Blue Dart](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/blue-dart) – Indien, 11-stellige Waybill mit Prüfziffer, Tracker und Nicht-gefunden-Meldung, Wortlaute und Stufen, indische Ortszeit, weggelassene Daten, Tracking-Links und Hilfeseite
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – andere 11-stellige Paketdienste, Prüfziffer von Aramex und Japan Post, angefragte Dienste in Indien und im Ausland, Übergabe von ICC Worldwide zu Blue Dart und 11-Ziffern-Regel, keine Weitergabe durch Blue Dart
:::
