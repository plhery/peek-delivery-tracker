---
title: Pitney Bowes Sendungsverfolgung: UPAA, UPBE und PBX
description: Pitney Bowes Paket (z. B. internationale eBay-Bestellung) verfolgen: UPAA-, UPBE- und PBX-Nummern, Status erklärt und wer am Ziel zustellt.
slug: pitney-bowes-sendungsverfolgung
published: 2026-11-23
updated: 2026-11-23
---

Pitney Bowes verschickt Pakete ins Ausland: Im Code von Peek gehören die Nummern zu eBay-Sendungen aus den Vereinigten Staaten und dem Vereinigten Königreich sowie zu anderen Auslandspaketen von Pitney Bowes. In den im Code hinterlegten Verläufen durchläuft das Paket ein Pitney-Bowes-Versandzentrum und reist weiter ins Land des Käufers, wo ein Partner – etwa die dortige Post – zustellt. Die Nummer beginnt mit UPAA, UPBE oder PBX und stammt vom Verkäufer oder Marktplatz: Füge sie oben ein. Der eigene Tracker von Pitney Bowes liegt unter trackpb.shipment.co.

## Pitney Bowes Sendungsnummer: wie sie aussieht

Peek kennt drei Formen für Pitney Bowes, jeweils 14 bis 20 Zeichen lang. Mit erfundenen Ziffern:

| Nummer (erfunden) | Was sie bedeutet |
| --- | --- |
| `UPAAK482917364058291` | UPAA, ein Buchstabe und 15 Ziffern: im Code von Peek ein eBay-Paket aus den USA oder dem Vereinigten Königreich. |
| `UPBE48291736A4F02819` | UPBE und 16 Zeichen, Ziffern oder Buchstaben A bis F: ein eBay-Paket aus dem Vereinigten Königreich. |
| `PBXHK482917365` | PBX, zwei Buchstaben und 9 Ziffern: ein anderes Auslandspaket von Pitney Bowes. |
| `PBXHK48291736A4F0281` | PBX, zwei Buchstaben und 15 Zeichen, Ziffern oder Buchstaben A bis F: dasselbe in der langen Form. |
| `UPAA482917364058291` | UPAA ohne den Buchstaben: kein Pitney-Bowes-Format, Peek erkennt keinen Dienst. Wählst du trotzdem Pitney Bowes, meldet Peek „vergibt keine Nummern wie diese“. Prüfe, ob ein Zeichen fehlt. |

Leerzeichen, Bindestriche und Kleinschreibung spielen in Peek keine Rolle. Im Katalog steht Pitney Bowes auch als Pitney Bowes Global Ecommerce, Pitney Bowes Expedited Parcel Service, eBay Global Shipping Program und PBI.

**Wo du sie findest.** In der Versandmail des Verkäufers oder auf deiner Bestellseite. Für eBay siehe [eBay-Bestellungen](guide:ebay-orders), sonst [Sendungsnummer finden](guide:find-tracking-number).

## Pitney Bowes Tracking-Status erklärt

Der Tracker von Pitney Bowes führt jede Zeile mit Datum, Uhrzeit und meist einem Ort, die neueste zuerst. Die Zeilen tragen keinen Code: Peek liest ihren Wortlaut. In der Reihenfolge, in der ein Paket sie durchläuft:

**Im Versandzentrum.** Eigene Zeilen von Pitney Bowes:

| Anzeige bei Pitney Bowes | Was das für dich bedeutet | Anzeige in Peek |
| --- | --- | --- |
| `Tracking details uploaded`, `Label Created` | Der Verkäufer hat die Sendungsdaten übermittelt. Pitney Bowes hat das Paket noch nicht. | „Etikett erstellt“ |
| `Package not received by Hub or First Mile Carrier yet` | Das Etikett existiert, aber weder das Versandzentrum noch der Zubringer-Dienst hat das Paket gescannt. | „Etikett erstellt“ |
| `Processing at Shipping Center` | Das Versandzentrum von Pitney Bowes hat das Paket und bearbeitet es. | „Unterwegs“ |
| `First Dimension Event`, `Second Dimension Event` | Das Zentrum vermisst das Paket. | „Unterwegs“ |
| `Parcel Documentation and Labeling`, `Customs Documentation and Labeling` | Das Zentrum fügt Ausfuhrpapiere und Etiketten hinzu. Noch nicht der Zoll selbst. | „Unterwegs“ |
| `Container Assignment`, `Container Closed` | Für den Transport in einen Container gepackt, danach wird der Container verschlossen. | „Unterwegs“ |
| `Shipped from Global Shipping Center to Destination`, `Departed for Destination` | Hat das Zentrum Richtung Zielland verlassen. | „Unterwegs“ |
| `International Carrier` | Dem Dienst übergeben, der es ins Ausland befördert. | „Unterwegs“ |
| `In Transit` | Unterwegs. Kann ohne Ortsangabe erscheinen. | „Unterwegs“ |

**Weiterleitung, Zoll und Übergabe.** In den Verläufen im Peek-Code übernimmt ab dem britischen Pitney-Bowes-Zentrum der Partner Interpost, der die Einfuhranmeldung einreicht und an die Zielpost übergibt. Die Zeilen dieses Abschnitts, wobei die ersten beiden noch ohne Ort während der Bearbeitung im britischen Zentrum entstehen:

| Anzeige bei Pitney Bowes | Was das für dich bedeutet | Anzeige in Peek |
| --- | --- | --- |
| `Item processed at Interpost` | Das britische Zentrum bearbeitet das Paket für Interpost. | „Unterwegs“ |
| `Interpost handed item over to postal company` | Übergabe durch Interpost an die Zielpost, die zustellt. Kommt im selben Moment wie die Zeile darüber, während das Paket noch im Zentrum sein kann. | „Unterwegs“ |
| `Customs Clearance` | Einfuhranmeldung im Zielland eingereicht. Papierkram: kann wenige Stunden nach Abgang in Großbritannien erscheinen, bevor das Paket vor Ort ist. | „Beim Zoll“ |
| `B2C ClearanceStatus4` | Vom Einfuhrzoll freigegeben. Peek schreibt dafür `Customs import cleared`, wie der Interpost-Tracker diesen Code benennt. | „Unterwegs“ |
| `B2C ClearanceStatus` und eine andere Zahl | Ein Verzollungsschritt, dessen Bedeutung der Peek-Code nicht kennt. Peek schreibt `Customs clearance update`. | „Beim Zoll“ |
| `Cleared customs` | Durch den Zoll. | „Unterwegs“ |
| `Package is created in the system` | Der nächste Dienst legt seinen Datensatz an. Das bewegt nichts. | keine Änderung |

**Zeilen der Post.** Nach der Übergabe reicht der Tracker die Scans der zustellenden Post weiter:

| Anzeige bei Pitney Bowes | Was das für dich bedeutet | Anzeige in Peek |
| --- | --- | --- |
| `Item in transit by postal company` | Die Post befördert das Paket zu dir. | „Unterwegs“ |
| `Parcel is out for delivery` | In der Zustelltour. | „In Zustellung“ |
| `Item is waiting for addressee at local post office` | Wartet in deiner örtlichen Postfiliale auf dich: hol es ab. | „Abholbereit“ |
| `Delivered by Consignee Pickup` | Du als Empfänger hast es abgeholt. | „Zugestellt“ |
| `Item has been delivered` | Zugestellt. | „Zugestellt“ |

Auch `Departed carrier facility` und `Processed by Carrier` erscheinen als „Unterwegs“. Andere Zeilen folgen Peeks allgemeinen Regeln; eine unbekannte Zeile erscheint im Originalwortlaut als „Unterwegs“, außer das Paket ist bereits in Zustellung oder weiter: Dann bleibt der Status unverändert.

**Der Gesamtstatus.** Der Tracker liefert auch einen Gesamtstatus. Meldet dieser zugestellt, zeigt Peek „Zugestellt“, selbst wenn die neueste Zeile das nicht sagt. Die übrigen Gesamtstatus zählen nur, solange keine Zeile einen liefert. Ein Problemstatus – der sogar nach normaler Abholung auftrat – überschreibt niemals eine Zeile.

## Wer Pitney Bowes Pakete zustellt

Peek konnte die Website von Pitney Bowes nicht auslesen, daher nennt diese Seite keine Partner- oder Länderliste. Der Peek-Code zeigt Interpost für den Weitertransport ab dem britischen Zentrum zur jeweiligen Zielpost, und ein Verlauf zeigt [Poste Italiane](carrier:poste-italiane) bei einer Zustellung in Italien. Der Pitney-Bowes-Tracker nennt den übernehmenden Dienst und kann eine Zeile auf dessen Tracking-Seite samt eigener Nummer verlinken.

**Zustellversuche und Abholung.** Versuche, Benachrichtigungskarten und Lagerfristen folgen den Regeln der zustellenden Post: Frag dort nach oder lies deren Seite in Peek, falls vorhanden. Siehe auch [verpasste Zustellung](guide:missed-delivery) und [nicht abgeholte Pakete](guide:uncollected-parcel).

**Zoll.** Ob bei Ankunft Abgaben anfallen, hängt von deinem Land ab und davon, was Verkäufer oder Marktplatz beim Kauf eingezogen haben: Frag zuerst dort. Peek konnte nicht prüfen, wie Pitney Bowes Einfuhrkosten regelt. Siehe [Pakete beim Zoll](guide:customs).

## Pitney Bowes Paket aktualisiert sich nicht, verloren oder zurückgesendet

**Noch nichts zu sehen.** Bei einer unbekannten Nummer oder einem abgelaufenen Verlauf liefert der Pitney-Bowes-Tracker dieselbe Antwort wie bei einem Leseproblem. Peek kann das nicht von einer Störung unterscheiden: Es fragt Tracking-Dienste und zeigt, wenn keiner das Paket kennt, „Warten auf Pitney Bowes“, ab der zweiten Prüfung zusätzlich „Pitney Bowes war noch nicht erreichbar. Peek versucht es weiter.“, während es weiter prüft. Vergleiche die Nummer mit der Tabelle oben. Zeigt sich nach einigen Tagen nichts, frag den Verkäufer, ob das Paket wirklich verschickt wurde.

**Hängt bei `Package not received by Hub or First Mile Carrier yet`.** Der Verkäufer hat das Etikett erstellt, aber weder das Pitney-Bowes-Zentrum noch der Zubringer-Dienst hat das Paket gescannt. Frag den Verkäufer, wann er es aufgegeben hat.

**Hängt bei `Customs Clearance`.** Die Anmeldung wurde eingereicht, obwohl das Paket womöglich noch gar nicht im Zielland ist. Warte auf die Freigabezeile, die Peek als `Customs import cleared` zeigt. Bleibt es lange dabei, melde dich beim Verkäufer.

**Nichts Neues, sobald die Post es hat.** Nach den ersten Zeilen der Post ist deren eigenes Tracking oft weiter als das von Pitney Bowes, und Peek wechselt nicht selbst dorthin. Öffne die Seite der Post über die Zeile im Pitney-Bowes-Tracker oder lege die Nummer der Post als eigenes Paket in Peek an.

**Tagelang kein Update.** Nach vier Tagen ohne Scan bei einem Paket im Status „Etikett erstellt“, „Beim Paketdienst eingegangen“ oder „Unterwegs“ zeigt Peek einen Hinweis („Seit vier Tagen keine Aktualisierung“ bzw. „Keine Aktualisierung seit …“) samt „Auf langen Flügen, beim Zoll oder übers Wochenende wird es oft still um ein Paket. Peek fragt weiter bei Pitney Bowes nach.“ und „Hol dir Hilfe bei Pitney Bowes“. Siehe [Warum sich die Sendungsverfolgung nicht mehr aktualisiert](guide:tracking-not-updating).

**Zugestellt, aber nicht da.** Prüfe den Ablageort und frag Nachbarn, danach die zustellende Post und den Verkäufer. Siehe [zugestellt, aber nicht erhalten](guide:delivered-not-received).

**Verloren oder zurückgesendet.** Bitte den Verkäufer oder Marktplatz um Erstattung oder Ersatzlieferung: Sie haben den Versand gebucht. Siehe [verlorene Pakete](guide:lost-parcel) und [zurück an den Absender](guide:returned-to-sender).

## Pitney Bowes Kundenservice und Kontakt

Peek konnte die Website von Pitney Bowes nicht auslesen, daher nennt diese Seite keine Telefonnummer und keine E-Mail: Peek führt nur Kontaktdaten auf, die direkt von den Seiten des Paketdienstes stammen.

Pitney Bowes befördert das Paket im Auftrag des Verkäufers oder Marktplatzes, frag also zuerst dort – bei einer eBay-Bestellung direkt über eBay. Sobald die örtliche Post das Paket hat, frag für die Zustellung dort nach.

## Pitney Bowes Paket mit Peek verfolgen

[Peek](/) erkennt Pitney Bowes sofort anhand einer UPAA-, UPBE- oder PBX-Nummer („Erkannter Paketdienst“) und fragt den Tracker allein mit dieser Nummer ab: ohne Postleitzahl und ohne Konto. Auch ein Tracking-Link von trackpb.shipment.co oder parceltracking.pb.com funktioniert. Hast du nur die Nummer des ersten Zubringers oder der Post, füge sie trotzdem ein: Peek verfolgt über 4.500 Paketdienste. Für ein anderes Format per „Ändern“ Pitney Bowes auszuwählen hilft nicht: Peek meldet „vergibt keine Nummern wie diese“.

Peek zeigt den Status, jede Zeile mit ihrem Ort sowie das Gewicht in deinen Einheiten (der Tracker liefert Pfund und Unzen). Link und Nummer des nächsten Dienstes blendet es aus. Da der Tracker weder voraussichtliches Lieferdatum noch Zustellnachweis liefert, zeigt Peek beides nicht. „Website von Pitney Bowes öffnen“ auf der Paketseite öffnet die Nummer direkt dort.

Peek übergibt das Paket nicht an die Post: Deren Scans kommen über den Tracker von Pitney Bowes. Ist dieser nicht erreichbar oder kann das Paket nicht lesen, fragt Peek Tracking-Dienste und zeigt deren Verlauf in deren eigenen Worten.

Der Tracker gibt jede Uhrzeit auf der Uhr des jeweiligen Scanners ohne Zeitzone aus. Peek liest jede Zeile auf der Uhr ihres Orts oder Abschnitts. Ohne Ortsangabe stehen `In Transit` und `Item processed at Interpost` auf UTC, `Interpost handed item over to postal company` auf UTC+2 und `Customs Clearance` auf der Uhr des genannten Landes. Alle Zeiten erscheinen in der Zeitzone deines Geräts.

Solange du das Paket in Peek geöffnet hast, oder am Zustelltag mit aktivierten Benachrichtigungen, fragt Peek Pitney Bowes direkt bis zu alle 10 Minuten ab, und ab 6:00 Uhr auf der Uhr des letzten Scans am Tag der Zustellung bis zu alle 2 Minuten, solange der Tracker von Pitney Bowes antwortet. Nach zwei Tagen ohne Neuigkeiten (nach 12 Stunden, solange das Paket noch „Etikett erstellt“ oder „Beim Paketdienst eingegangen“ zeigt) prüft Peek stündlich; nach sieben Tagen ohne Neuigkeiten bei „Etikett erstellt“ alle 6 Stunden. Benachrichtigungen gibt es für „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“. Peek kann weder Pitney Bowes noch den Verkäufer oder die Post für dich kontaktieren.

## Fragen zur Pitney Bowes Sendungsverfolgung

### Was ist eine UPAA-Sendungsnummer?

Eine Pitney-Bowes-Nummer: UPAA, ein Buchstabe und 15 Ziffern. Der Code von Peek ordnet UPAA-Nummern eBay-Paketen aus den USA und dem Vereinigten Königreich zu, UPBE-Nummern eBay-Paketen aus dem Vereinigten Königreich und PBX-Nummern weiteren Auslandspaketen von Pitney Bowes. Der Katalog führt zudem eBay Global Shipping Program als weiteren Namen für Pitney Bowes.

### Was bedeutet „Interpost handed item over to postal company“?

Das ist die Übergabe deines Pakets durch Interpost – den Partner ab dem britischen Pitney-Bowes-Zentrum – an die Zielpost, die es ausliefert. Pitney Bowes schreibt diese Zeile schon während der Bearbeitung im britischen Zentrum, sodass sie vor dem Abgang erscheinen kann. Die Scans der Post folgen später im Pitney-Bowes-Tracker.

### Warum hängt mein Pitney Bowes Tracking bei „Customs Clearance“?

Das ist die Einfuhranmeldung, die oft wenige Stunden nach Abgang aus Großbritannien eingereicht wird, noch vor Ankunft des Pakets. Warte auf die Freigabezeile, in Peek als `Customs import cleared`. Bleibt es lange unverändert, frag den Verkäufer.

### Wie kontaktiere ich Pitney Bowes wegen meines Pakets?

Wende dich zuerst an den Verkäufer oder Marktplatz: Sie haben den Versand gebucht. Da Peek die Website von Pitney Bowes nicht auslesen konnte, nennt diese Seite weder Telefonnummer noch E-Mail.

:::sources
- [Universal Parcel Scraper: Pitney Bowes](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/pitney-bowes) – Namen, die drei Nummernformate und wofür sie stehen, Tracking-Links, der Tracker, seine Wortlaute und Stufen, Interpost-Zeilen und Zollcode, weitergereichte Postzeilen, Poste Italiane bei einem Paket nach Italien, Gesamtstatus, Uhren, Gewicht, was Peek weglässt
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – keine Übergabe an die Zielpost, Tracking-Dienste als Rückfalloption
:::
