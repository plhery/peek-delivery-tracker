---
title: MHI Sendungsverfolgung: Trackmail-Status und Zusteller
description: MHI-Nummer von einem Onlineshop erhalten? Wo du sie verfolgst, was Trackmail-Status bedeuten, wer in deinem Land zustellt und wer bei Problemen hilft.
slug: mhi-sendungsverfolgung
published: 2026-11-23
updated: 2026-11-23
---

MHI (Mail Handling International) ist ein Unternehmen in Bristol, das Bestellungen britischer Onlineshops an Käufer im Ausland verschickt. Die Shops senden ihre Pakete an das Verarbeitungszentrum in Bristol; MHI exportiert sie und übergibt jedes Paket an einen Paketdienst oder eine Post in deinem Land, die es unter eigener Nummer zustellt. Verfolge die MHI-Nummer auf MHIs Tracking-Seite [Trackmail](https://www.trackmail.co.uk/) oder oben im Feld. Die Nummer kommt vom Shop: MHI vergibt für jedes Paket einen Tracking-Code an seine Kunden, du findest ihn also in der Versandmail oder in deinen Bestelldetails.

## MHI-Sendungsnummer: So sieht sie aus

MHI veröffentlicht kein offizielles Format für Käufer. Diese Formen haben MHI-Nummern (mit erfundenen Ziffern):

| Nummer | Was sie ist |
| --- | --- |
| `MHI482W736405829173` | MHI, drei Ziffern, dann W und 12 Ziffern: MHI. |
| `MHI482WBB736405829` | MHI, drei Ziffern, dann WBB und 9 Ziffern: MHI. |
| `MHI-482-P736405829` | MHI, drei Ziffern, dann P und 9 Ziffern: MHI. Auf Etiketten steht diese Variante mit Bindestrichen. |
| `MHI482736405829173` | MHI, drei Ziffern, dann 12 weitere Ziffern: MHI. |
| `RR482917367US` | Zwei Buchstaben, 9 Ziffern, zwei Buchstaben: eine Postnummer für dieselbe Sendung, die Trackmail bei manchen ungetrackten Sendungen anzeigt. |
| Jedes andere Format | Die eigene Nummer des zustellenden Paketdienstes, die auf Trackmail bei einer verfolgten Sendung hinter „Tracking number:“ steht. |

Die drei Ziffern nach MHI stehen für das Kundenkonto des Shops bei MHI. Gib eine P-Nummer auf Trackmail mit Bindestrichen genau wie aufgedruckt ein: Trackmail findet sie nur damit. Ein einzelnes Paket kann also zwei Nummern haben – die von MHI und die des Zustellers. Mehr dazu unter [Sendungsnummer-Formate](guide:tracking-number-formats).

## MHI-Sendungsstatus einfach erklärt

Trackmail ist auf Englisch. Über dem Verlauf steht der vom Shop gewählte Service: „The seller requested this item be shipped via the following service:“, gefolgt von `TRACKED` oder `UNTRACKED`. Darunter folgen die Ereignisse, neuestes zuerst, jeweils mit Datum, Uhrzeit und Text, meist samt Kategorie und Ort: zuerst MHIs eigener Export-Scan, dann die Scans des Zustellers in MHIs Wortlaut. In der Reihenfolge, die ein Paket durchläuft:

| Trackmail zeigt an | Was das für dich bedeutet |
| --- | --- |
| `Your order has been processed at the UK export facility. It is now on route to the receiver.` | MHI hat das Paket in Großbritannien bearbeitet und ins Ausland weitergeleitet. Bei einem ungetrackten Tarif bleibt es oft bei dieser einen Zeile. |
| `Shipment information sent to last mile service provider` | Der Paketdienst in deinem Land hat die Sendungsdaten erhalten, das Paket selbst aber noch nicht. |
| `Notification sent to recipient` | Der Zusteller hat dich informiert, dass das Paket unterwegs ist. |
| `Handed Over to Delivery Partner` | Der Paketdienst hat das Paket übernommen. |
| `Arrived at sorting center` | Depot-Scan im Netzwerk des Paketdienstes. |
| `Dispatched from sorting center` | Das Paket hat dieses Depot verlassen. |
| `Assigned to Transport` | Auf dem Transportweg zwischen zwei Depots. |
| `Shipment at final delivery center` | Im Zustelldepot angekommen, das die Auslieferung übernimmt. |
| `Delivery scheduled in the final delivery depot` | Für die heutige Zustelltour eingeplant. |
| `Delivery attempted` | Zustellversuch fehlgeschlagen: Niemand hat das Paket entgegengenommen. |
| `Arrived at Pick-up Point` | Wartet an einem Abholpunkt auf dich. |
| `Successfully delivered` | Zugestellt oder abgeholt. |

Lies immer den Text, nicht die Kategorie daneben. Trackmail ordnet `Shipment at final delivery center` und `Arrived at Pick-up Point` unter „Out for delivery“ ein, obwohl der erste Status vor der Tour kommt und der zweite bedeutet, dass du das Paket abholen musst. `Delivery attempted` steht wiederum unter „Delivery“, wie eine erfolgreiche Zustellung. Tour und Zustellversuch können sich über mehrere Tage wiederholen.

Uhrzeiten auf Trackmail haben keine Zeitzone: MHIs eigener Scan gilt in britischer Zeit, die Scans des Zustellers in dessen Ortszeit.

Kennt Trackmail eine Nummer nicht, erscheint eine einzelne Zeile, die mit `No details found for Tracking Code, or Tracking Code not recognised.` beginnt, samt Hinweis, dass MHI die Daten bei einer ganz frischen Bestellung womöglich noch nicht hat.

## MHI-Zustellung: Wer bringt dein Paket?

MHI stellt im Ausland nicht selbst zu. Laut eigenen Angaben arbeitet das Unternehmen mit über 100 Paketdiensten zusammen, um 252 Länder und Gebiete zu erreichen. Im Zentrum in Bristol wird der Barcode jedes Pakets gescannt und das System wählt je nach Zielland den passenden Dienst für die letzte Meile. Shops können zwischen verfolgten, nicht verfolgten und Express-Tarifen wählen – wer liefert, hängt also von deinem Land und der Wahl des Shops ab.

**Verfolgte Sendungen.** Trackmail kann hinter „Tracking number:“ die Nummer des zustellenden Paketdienstes anzeigen, samt Link „View carrier tracking“ auf dessen Seite. Bei Rückfragen verweist Trackmail auf diesen Link oder deinen Händler.

**Nicht verfolgte Sendungen.** Trackmail weist darauf hin, dass nur eingeschränkte Tracking-Daten vorliegen, und zeigt oft nur MHIs Export-Scan. Für manche Sendungen gibt es eine „local poster number“: Wenn du sie deiner örtlichen Post oder Postfiliale nennst, kann sie laut Trackmail eventuell weiterhelfen. Probiere sie auch direkt in der Sendungsverfolgung deiner Post aus: [USPS](carrier:usps), [Canada Post](carrier:canada-post), [Australia Post](carrier:australia-post) oder An Post.

Benachrichtigungen, Zustellversuche, Neuzustellung und Abholpunkte richten sich nach den Regeln des zustellenden Paketdienstes: Prüfe sein Tracking über „View carrier tracking“ und lies [verpasste Zustellungen](guide:missed-delivery).

## MHI-Paket ohne Update, verloren oder zurückgesandt

**`No details found…`.** Prüfe die Nummer und gib eine P-Nummer mit Bindestrichen ein. Hast du gerade erst bestellt, liegen MHI die Daten eventuell noch nicht vor: Versuche es später erneut.

**Nur der Export-Scan.** Bei einem ungetrackten Tarif zeigt Trackmail oft nicht mehr an: Nutze die „local poster number“ bei deiner Post, falls vorhanden, oder frag im Shop nach. Bei einer verfolgten Sendung hat der Zusteller sie womöglich noch nicht gescannt; sobald dessen Nummer auftaucht, ist sein eigenes Tracking oft aktueller. Siehe [warum die Sendungsverfolgung stehen bleibt](guide:tracking-not-updating).

**Zugestellt, aber nicht da.** Der örtliche Zusteller hat ausgeliefert: Prüfe sein Tracking über „View carrier tracking“ und kontaktiere dann den Shop. Siehe [Paket als zugestellt markiert, aber nicht erhalten](guide:delivered-not-received).

**Verloren oder beschädigt.** Wende dich an den Shop. MHI arbeitet für den Händler, veröffentlicht kein eigenes Reklamationsverfahren für Empfänger, und Trackmail verweist selbst an den Verkäufer. Siehe [verlorene Pakete](guide:lost-parcel).

**Zoll.** MHI bietet Shops verschiedene Zollverfahren an, darunter DDP (Delivered Duty Paid). Ob noch Abgaben auf dich zukommen, hängt also davon ab, was der Shop gewählt hat. Was anfallen kann, steht unter [Pakete beim Zoll](guide:customs).

**Zurückgesandt.** Wie lange ein nicht abgeholtes Paket aufbewahrt wird, bevor es zurückgeht, entscheidet der zustellende Paketdienst: Schau in sein Tracking. Geht es zurück, bitte den Shop um Erstattung oder Neulieferung. Siehe [zurück an den Absender](guide:returned-to-sender).

## MHI-Telefonnummer und Kundenservice

Kontaktdaten von MHI:

- **Tracking-Fragen:** E-Mail an tracking@mhi.co, angegeben auf der Seite eCommerce Delivery und auf der Kontaktseite von Nexae (MHIs Versandplattform).
- **Telefon:** +44 (0) 117 977 6655, eine britische Nummer neben der Adresse in Bristol, 82 Feeder Road, BS2 0TQ. MHI nennt keine Geschäftszeiten und gibt nicht an, darüber Paketanfragen anzunehmen.
- **Kontaktformular (Enquiries):** für allgemeine Anfragen. MHI weist darauf hin, dass Tracking-Anfragen über dieses Formular nicht bearbeitet werden.

MHIs Rat an Käufer: Trackmail prüfen oder den Verkäufer beziehungsweise Marktplatz kontaktieren. Der Shop ist MHIs Auftraggeber und kann nachhaken; bei verpasster Zustellung oder Abholung ist der auf Trackmail genannte Paketdienst der richtige Ansprechpartner.

## MHI-Paket mit Peek verfolgen

[Peek](/) erkennt eine MHI-Nummer am Format in allen vier Serien oben, mit oder ohne Bindestriche. Ältere Etiketten mit nur zwei Ziffern nach MHI oder acht Ziffern am Ende werden nicht abgefragt: Prüfe diese direkt auf Trackmail. Du kannst auch einen Trackmail-Link mit der Nummer einfügen; Peek liest ihn als MHI, wenn die Nummer das MHI-Format hat.

Peek liest Trackmail aus und zeigt Status und Verlauf samt dem Ort jedes Scans auf einer Karte. Trackmail liefert weder voraussichtliches Lieferdatum noch Gewicht, Zustellnachweis oder Namen eines Unterzeichners, daher bekommt Peek nichts davon von MHI, und es lässt deine Adresse sowie die Postleitzahl aus dem Link des Zustellers weg.

Verweist der Link auf Trackmail auf einen Paketdienst, den Peek kennt, steht auf der Paketseite „Zustellung durch“ samt diesem Dienst und dessen Nummer. Hat dieser Paketdienst bisher nur die Ankündigung ohne eigenen Scan, steht stattdessen „Bei … angekündigt, noch nicht eingetroffen“. Liest Peek das Tracking dieses Dienstes selbst aus, fragt es dort mit der Nummer nach und wechselt dorthin, sobald neuere Fortschritte erscheinen. Ein Paketdienst, den Peek nicht kennt, wird nicht genannt: Nutze dann „View carrier tracking“ auf Trackmail. Bei einer nicht verfolgten Sendung mit „local poster number“ kann Peek diese Nummer bei einer nationalen Post testen – meist der des Landes, aus dem du das Paket hinzufügst – und genauso wechseln. Allerdings nicht bei jeder Post: Eine `RR…`-Nummer mit Endung `AU`, die aus Australien hinzugefügt wird, geht nicht an Australia Post, prüfe sie also direkt bei Australia Post.

Solange du das Paket in Peek geöffnet hast oder am Zustelltag Benachrichtigungen aktiviert sind, prüft Peek bis zu alle 10 Minuten, und ab 6 Uhr Ortszeit am Tag, an dem das Paket in Zustellung geht, bis zu alle 2 Minuten, solange Trackmail (oder der eigene Tracker des übernehmenden Paketdienstes) antwortet. Nach zwei Tagen ohne Neuigkeiten (nach 12 Stunden, solange das Paket noch „Etikett erstellt“ oder „Beim Paketdienst eingegangen“ zeigt) prüft Peek stündlich; bleibt es sieben Tage lang bei „Etikett erstellt“, alle 6 Stunden.

Bleibt ein Paket im Status „Etikett erstellt“, „Beim Paketdienst eingegangen“ oder „Unterwegs“ vier Tage ohne neuen Scan, meldet Peek „Seit vier Tagen keine Aktualisierung“ (oder „Keine Aktualisierung seit …“), erklärt: „Auf langen Flügen, beim Zoll oder übers Wochenende wird es oft still um ein Paket. Peek fragt weiter bei MHI nach.“ und bietet „Hol dir Hilfe bei MHI“ an. Du kannst Benachrichtigungen für „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“ einstellen. Peek kann weder MHI noch den Zusteller oder den Shop für dich kontaktieren.

## Häufige Fragen zur MHI-Sendungsverfolgung

### Wo ist mein MHI-Paket?

Die neueste Zeile auf Trackmail zeigt den aktuellen Schritt, die Scans des Zustellers nennen eine Stadt. Vor `Handed Over to Delivery Partner` liegt es bei MHI oder auf dem Weg in dein Land; danach hat es der zustellende Paketdienst, und bei einer verfolgten Sendung steht dessen Nummer auf Trackmail hinter „Tracking number:“.

### Was bedeutet „processed at the UK export facility“?

MHI hat das Paket in seinem Exportzentrum in Großbritannien bearbeitet und in deine Richtung auf den Weg gebracht. Das ist MHIs eigener Scan, und bei einem ungetrackten Tarif oft der einzige.

### Ist trackmail.co.uk seriös?

Ja. Trackmail ist MHIs eigene Tracking-Seite: Der Link „Track my Parcel“ auf der MHI-Website führt direkt dorthin. Bei Companies House ist die Mail Handling International Limited als aktives Unternehmen geführt, gegründet 1996 an der von MHI genannten Adresse in Bristol.

### Hat MHI eine Kundenservice-Nummer?

Angegeben ist +44 (0) 117 977 6655, aber für Tracking-Fragen bittet MHI um eine E-Mail an tracking@mhi.co oder um Kontakt zum Shop. Für die eigentliche Zustellung ist der auf Trackmail genannte Paketdienst zuständig.

:::sources
- [MHI: Home](https://www.mhi.co/) – Track-my-Parcel-Link zu Trackmail, Paketanfragen an den Verkäufer, allgemeines Formular nicht für Tracking, über 100 Paketdienste, 252 Länder und Gebiete, Tracked, Untracked und Express, DDP, Adresse, Telefon
- [MHI: eCommerce Delivery](https://www.mhi.co/ecommerce-delivery) – Zentrum in Bristol, Zusteller nach Zielland, Tracking-Codes für Shops, Export, tracking@mhi.co
- [Nexae: Contact Us](https://nexae.co.uk/ContactUsHomepage) – tracking@mhi.co, Nexae gehört zu MHI
- [MHI: Trackmail](https://www.trackmail.co.uk/) – MHIs Tracking-Seite auf Englisch, Tracking-Kontakt
- [Companies House: Mail Handling International Limited](https://find-and-update.company-information.service.gov.uk/company/03241562) – aktiv, gegründet am 23. August 1996, Sitz 82 Feeder Road, Bristol
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Nummernformate, Wortlaute und Kategorien auf Trackmail, Servicezeile, Zusteller-Link, lokale Postnummer, Unbekannt-Meldung, Zeitzonen, was Peek zeigt und welchen Dienst oder welche Post es abfragt
:::
