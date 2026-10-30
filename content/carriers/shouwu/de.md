---
title: Shouwu Sendungsverfolgung: SWECN…YQ, Status und Zustellung
description: Shouwu-International-Paket (守务国际) aus China verfolgen: wie die SWECN…YQ-Nummer aussieht, was die Status bedeuten und wer in Deutschland zustellt.
slug: shouwu-sendungsverfolgung
published: 2026-11-02
updated: 2026-11-02
---

Shouwu International (守务国际, auch SWE) ist ein Logistikunternehmen aus Shanghai. Es bringt Pakete chinesischer Onlinehändler nach Europa und in die USA, wo eine Post oder ein Paketdienst sie zustellt. Hat ein Shop deine Bestellung mit Shouwu verschickt, beginnt deine Sendungsnummer mit SWECN und endet auf YQ: Füg sie oben ins Feld ein oder gib sie in Shouwus Sendungsverfolgung auf [admin.shshouwu.com](https://admin.shshouwu.com/track.html) ein.

## Shouwu Sendungsnummer: so sieht sie aus

Shouwus Sendungsnummern bestehen aus SWECN, zwölf Ziffern und YQ, zusammen 19 Zeichen. Mit erfundenen Ziffern:

| Nummer | Was sie ist |
| --- | --- |
| `SWECN000000000000YQ` | SWECN, zwölf Ziffern und YQ: eine Shouwu-Nummer. |
| `SWECN00000000000YQ` | Nur elf Ziffern: So sehen Shouwus Nummern nicht aus. Prüf, ob eine Ziffer fehlt. |
| `JSEAA0000000000YQ` | Endet auch auf YQ, beginnt aber mit JSE: eine Nummer von JS Express. |

Auch andere chinesische Spediteure lassen ihre Nummern auf YQ enden, mit eigenen Buchstaben davor und, in Peeks Katalog, zehn Ziffern. SWECN und zwölf Ziffern stehen für Shouwu. Leerzeichen, Bindestriche und Kleinbuchstaben stören Peek nicht.

**Wo du sie findest.** Steht sie weder in der Versandmail des Shops noch auf der Bestellseite, lies, [wo du die Sendungsnummer findest](guide:find-tracking-number).

**Vielleicht hast du die Nummer des Paketdiensts.** Shouwu lässt den Händler wählen, welche Nummer er weitergibt: Shouwus eigene, die das Paket von Anfang bis Ende begleitet, oder die des Paketdiensts, der in deinem Land zustellt. In Deutschland kann ein Shouwu-Paket also mit einer Nummer von DHL, Hermes oder DPD bei dir ankommen (mehr unten). Füg sie trotzdem ein: Peek verfolgt mehr als 3.500 Paketdienste.

**Ein Tracking-Link.** Mit „Copy Link“ kopiert Shouwus Sendungsverfolgung einen Link mit `?q=` und der Nummer. Er öffnet genau dieses Paket, und du kannst ihn komplett in Peek einfügen. Shouwus Seite nimmt bis zu 40 Nummern auf einmal, eine pro Zeile.

## Shouwu Sendungsverfolgung: was die Status bedeuten

Shouwus Sendungsverfolgung ist auf Englisch, unter einer chinesischen Überschrift. Sie zeigt zu jedem Paket einen Status und das Zielland, dann zu jedem Scan Uhrzeit und Text, aber keinen Ort.

| Shouwus Status | Was es für dich heißt | Peek zeigt |
| --- | --- | --- |
| `Not Found` | Shouwu hat unter dieser Nummer noch nichts: „We seem can't identify the number.“ | „Warten auf den Paketdienst“ |
| `Preparing` | Laut Shouwu wurde das Paket seinem Lager in China übergeben oder wird dort gewogen und verschlossen. Der Status kann schon vor dem ersten Scan stehen. | „Etikett erstellt“, nach einem Lagerscan „Beim Paketdienst eingegangen“ |
| `In Transit` | Alles von der Übergabe an einen Transporteur über den Zoll bis zur Fahrt in deinem Land. | „Unterwegs“ oder die Phase des neuesten Scans, etwa „Beim Zoll“; „Etikett erstellt“, solange es nur die Bestelldaten und `Carrier received the shipment` gibt |
| `Undelivered` | Ein Zustellversuch ist gescheitert. | „Zustellversuch“ oder, wenn der neueste Scan das sagt, „Abholbereit“ bzw. „Zurück an Absender“ |
| `Delivered` | An den Empfänger zugestellt. | „Zugestellt“ |

Shouwu nennt seine Status „auto-judged by the system“, also automatisch vergeben, und „only for information reference use“, nur zur Orientierung. Mehr verraten die Scans:

| Shouwu schreibt | Was es für dich heißt | Peek zeigt |
| --- | --- | --- |
| `Shipment information received`, `Order information received. We're expecting your parcel to arrive with us.` | Der Händler hat den Versand gebucht. Shouwu hat das Paket noch nicht. | „Etikett erstellt“ |
| `Carrier received the shipment` | Diese Zeile schreibt Shouwu zwei Stunden nach den Bestelldaten, Tage vor dem ersten Lagerscan: Noch hat niemand das Paket. | „Etikett erstellt“ |
| `Arrival processing center` | Shouwus Lager in China hat das Paket angenommen. | „Beim Paketdienst eingegangen“ |
| `Package processing completed` | Das Lager ist damit fertig, der Flug steht noch aus. | „Beim Paketdienst eingegangen“ |
| `Arrived at Shanghai Pudong Airport`, `Departed from export airport` | Am Flughafen Shanghai, dann aus China abgeflogen. | „Unterwegs“ |
| `Arrived at airport of Destination` | In deinem Land gelandet. | „Unterwegs“ |
| `Shipment started the customs clearance process at destination` | Der Zoll in deinem Land fertigt es ab. | „Beim Zoll“ |
| `Custom clearance completed`, `Shipment completed the customs clearance process at destination` | Der Zoll in deinem Land hat es freigegeben. | „Unterwegs“ |
| `Delivery to local courier` | An den Paketdienst übergeben, der bei dir zustellt, nicht an dich. | „Unterwegs“ |
| `Out for delivery` | Die Zustelltour des Paketdiensts vor Ort. | „In Zustellung“ |
| `Delivered by post man` | Die Post vor Ort hat es zugestellt. | „Zugestellt“, sobald auch Shouwus Status `Delivered` lautet |

Manche Verläufe zeigen andere englische Zeilen. Die wichtigsten:

| Shouwu schreibt | Was es für dich heißt | Peek zeigt |
| --- | --- | --- |
| `Shipment arrived at facility` | Der erste Scan beim Spediteur, der das Paket zum Flug bringt. | „Beim Paketdienst eingegangen“ |
| `Shipment arrived at a carrier facility` | Beim Transporteur angekommen, der den Flug übernimmt. | „Unterwegs“ |
| `Shipment started the customs clearance process at origin` | Die Ausfuhrabfertigung in China, nicht der Zoll bei dir. | „Beim Zoll“ |
| `Export customs declaration completed` | Die Ausfuhranmeldung in China ist erledigt. Das Paket hängt nicht beim Zoll. | „Unterwegs“ |
| `Flight departure`, `Flight arrival` | Abflug und Landung. | „Unterwegs“ |
| `Shipment arrived in the destination country` | Im Zielland angekommen. | „Unterwegs“ |
| `Customs clearance has been completed` | Der Zoll im Zielland hat es freigegeben. | „Unterwegs“ |
| `Abnormal delivery` | Die Zustelltour hat nicht geklappt. Den Grund nennt ein eigener Scan danach. | „Zustellversuch“ |
| `<Incorrect address.>` | So nennt der Paketdienst vor Ort den Grund, in spitzen Klammern: Die Adresse stimmt nicht. | „Zustellversuch“ |

Zeilen wie `Shipment departed from facility`, `Depart from the sorting center` oder `Shipment arrived at an air hub` sind Zwischenstationen: Peek zeigt „Unterwegs“.

**Uhrzeiten um Stunden verschoben.** Shouwus System speichert die Ortszeit jedes Scans, als wäre sie Pekinger Zeit (UTC+8), auch bei Scans im Ausland, und seine Sendungsverfolgung rechnet sie in deine Zeitzone um. Ein Scan um 10:00 Uhr in Deutschland, Österreich oder der Schweiz erscheint bei Shouwu deshalb um 3:00 Uhr (Winterzeit, MEZ, UTC+1) oder um 4:00 Uhr (Sommerzeit, MESZ, UTC+2), und ein Flug nach Mitteleuropa wirkt sieben Stunden kürzer, im Sommer sechs. Verlass dich auf die Reihenfolge der Zeilen. Peek bestimmt die Uhrzeit jedes Scans nach seinem Ort oder nach der Etappe, auf der das Paket gerade war, und zeigt sie in der Zeitzone deines Geräts.

## Wer Shouwu-Pakete in Deutschland, Österreich und der Schweiz zustellt

Shouwu sagt, es arbeite direkt mit USPS, Royal Mail, La Poste, DHL, DPD und Hermes zusammen. Auf seinen chinesischen Linienseiten nennt es die Paketdienste je Land:

- **Deutschland.** Shouwu hat drei Linien nach ganz Deutschland. Die letzte Meile übernimmt laut diesen Seiten derzeit vor allem DHL bei der Standardlinie, Hermes bei der Economy-Linie und DPD bei der Linie für Waren mit Batterien. Welche Linie dein Paket nimmt, entscheidet der Händler beim Buchen. Mit der Nummer des Paketdiensts regelst du eine neue Zustellung oder die Abholung direkt bei ihm; wie das geht, steht auf den Seiten zu [DHL](carrier:dhl), [Hermes](carrier:hermes-de) und [DPD](carrier:dpd-de).
- **Österreich und die Schweiz.** Dafür führt Shouwu keine Linie und nennt keinen Paketdienst. Frag den Shop, wer zustellt.

Shouwus Sendungsverfolgung nennt weder Namen noch Nummer des Paketdiensts, außer was im Text eines Scans steht.

**Zustellung verpasst.** Zustellversuche, Benachrichtigungskarten und Abholstellen richten sich nach dem Paketdienst. Als mögliche Gründe für `Undelivered` nennt Shouwu: niemand angetroffen, Zustellung verschoben oder auf Wunsch später, Adresse nicht auffindbar, abgelegene Gegend. Dann rät Shouwus Sendungsverfolgung, beim Paketdienst eine neue Zustellung zu vereinbaren oder das Paket sofort abzuholen, bevor seine Frist abläuft, sonst könne es an den Absender zurückgehen („might be returned to the sender“). Reagier also schnell auf eine Benachrichtigung. Mehr unter [Paket verpasst](guide:missed-delivery) und [Paket nicht abgeholt](guide:uncollected-parcel).

## Shouwu Paket kommt nicht an oder Tracking hängt

**`Not Found`.** Shouwu nennt vier Gründe: Es hat das Paket noch nicht angenommen oder noch nicht gescannt, die Nummer ist falsch, oder das Paket wurde vor so langer Zeit verschickt, dass seine Daten weg sind. Die Bearbeitung dauere meist ein paar Tage. Peek zeigt „Warten auf den Paketdienst“ und fragt weiter nach. Prüf die Nummer Zeichen für Zeichen; tut sich nach ein paar Tagen nichts, frag den Shop.

**Hängt bei `Preparing`, `Shipment information received` oder `Carrier received the shipment`.** Shouwu hat das Paket noch nicht oder hat es noch nicht aus seinem Lager losgeschickt. Frag den Shop, wann er es übergeben hat.

**Hängt beim Zoll.** Nach `Shipment started the customs clearance process at destination` wartet es auf den Zoll in deinem Land. Sollst du Einfuhrabgaben zahlen, lies [Paket beim Zoll](guide:customs).

**Nichts mehr nach `Delivery to local courier`.** Der Paketdienst hat das Paket, aber Shouwus Sendungsverfolgung nennt seine Nummer nicht. Frag den Shop nach der Nummer des Paketdiensts und füg sie in Peek ein. Mehr unter [Sendungsverfolgung aktualisiert sich nicht](guide:tracking-not-updating) und [Paket aus China verfolgen](guide:tracking-from-china).

**Zugestellt, aber nicht da.** Zeigt die Seite des Paketdiensts eine Zustellung, die nie ankam, hilft Shouwu laut seinen Linienbedingungen bei der Suche, zahlt aber nichts. Fang beim Paketdienst an, dann frag den Shop. Siehe [Paket zugestellt, aber nicht erhalten](guide:delivered-not-received).

**Verloren oder beschädigt.** Shouwus Kunde ist der Händler, und laut denselben Bedingungen muss er innerhalb von 30 Tagen nach dem Versand durch Shouwu reklamieren. Fehlt nach der Annahme in Shouwus Lager jeder Zustellscan, zahlt Shouwu den deklarierten Warenwert plus Versandkosten, höchstens 200 Yuan pro Sendung, und für Transportschäden nichts. Bitte also den Shop oder Marktplatz gleich um Erstattung oder ein neues Paket. Siehe [Paket verloren](guide:lost-parcel).

## Shouwu Kontakt und Kundenservice

Stand 30. Oktober 2026 ist Shouwus Website, www.sedel.com, auf Chinesisch und für Händler gemacht, die Sendungen buchen. Sie nennt Handynummern für die Zentrale in Shanghai und fünf Niederlassungen, einen QQ-Chat-Link und WeChat-QR-Codes. Eine E-Mail-Adresse, ein Kontaktformular, einen Kontakt auf Englisch oder etwas für Empfänger gibt es nicht. Die Sendungsverfolgung nennt gar keinen Kontakt.

Für deine Bestellung ist der Shop zuständig: Er hat das Paket bei Shouwu gebucht und reklamiert dort. Für die Zustellung selbst fragst du den Paketdienst in deinem Land.

## Shouwu Paket verfolgen mit Peek

[Peek](/) erkennt eine Shouwu-Nummer an ihrer Form: SWECN, zwölf Ziffern und YQ nutzt kein anderer Paketdienst in seinem Katalog. Mit dieser Nummer fragt Peek bei Shouwus Sendungsverfolgung nach. Ein Konto, eine Postleitzahl oder eine Telefonnummer brauchst du nicht.

Peek übernimmt die Phase des Pakets aus Shouwus Status, wie in den Tabellen oben, und zeigt das Zielland und jeden Scan mit seinem Ort, den Shouwus Seite weglässt. Orte mit einer Ziffer, etwa einer Postleitzahl, fallen dabei weg. Shouwu nennt keinen voraussichtlichen Liefertermin, also zeigt Peek auch keinen.

Shouwu nennt den Paketdienst nicht eigens, deshalb zeigt Peek keine Zeile „Zustellung durch“ und verfolgt das Paket nicht beim Paketdienst weiter. Hast du dessen Nummer, füg sie in Peek als eigenes Paket hinzu.

Peek schaut alle 10 Minuten nach und alle 2 Minuten, sobald das Paket in Zustellung ist. Mitteilungen bekommst du für „Jeden Scan“, für „Wichtige Schritte“ (Zustellung, Abholung, Zoll, fehlgeschlagene Zustellversuche und Rücksendungen) oder für „Nur die Zustellung“. Shouwu oder den Shop kontaktieren kann Peek nicht für dich.

## Fragen zur Shouwu Sendungsverfolgung

### Was ist Shouwu International (守务国际)?

Ein Logistikunternehmen aus Shanghai, 上海守务国际物流有限公司, das Pakete für grenzüberschreitende Onlinehändler verschickt. Nach eigenen Angaben begann es 2018 als Luftfrachtspediteur und setzt seit 2019 auf E-Commerce-Linien nach Europa und in die USA.

### Wo kann ich ein Shouwu-Paket verfolgen?

Oben im Feld oder in Shouwus englischer Sendungsverfolgung auf admin.shshouwu.com. Gib die ganze SWECN…YQ-Nummer ein.

### Wer stellt Shouwu-Pakete in Deutschland zu?

Ein Paketdienst vor Ort, je nachdem, welche Linie der Händler gewählt hat: Shouwus Linienseiten nennen vor allem DHL, Hermes und DPD. Für Österreich und die Schweiz nennt Shouwu keinen, frag also den Shop.

### Wie lange braucht ein Shouwu-Paket nach Deutschland?

Shouwu gibt für alle drei Deutschland-Linien eine Richtzeit von 4 bis 7 Arbeitstagen an. Für Österreich und die Schweiz nennt es keine.

### Wen frage ich, wenn mein Shouwu-Paket nicht ankommt?

Den Shop, bei dem du bestellt hast: Er hat das Paket bei Shouwu gebucht und kann innerhalb von 30 Tagen nach dem Versand reklamieren. Für Empfänger veröffentlicht Shouwu keinen Kontakt.

:::sources
- [Shouwu International: 守务国际包裹追踪 (Sendungsverfolgung)](https://admin.shshouwu.com/track.html) – englische Sendungsverfolgung, 40 Nummern, Status und ihre Erklärungen, Scans ohne Ort, „Copy Link“, „Not Found“
- [Shouwu International: 首页 (Startseite)](http://www.sedel.com/) – Kontakte, QQ-Chat, Firmenname
- [Shouwu International: 关于我们 (Über uns)](http://www.sedel.com/about-us.html) – 2018 und 2019, direkte Partner
- [Shouwu International: 主营线路 (Hauptlinien)](http://www.sedel.com/main-route.html) – Linien nach Ländern, keine nach Österreich oder in die Schweiz
- [Shouwu International: 德国标快 (Deutschland, Standard)](http://www.sedel.com/main-route-secondary.html?groupId=1&groupItemId=19) – ganz Deutschland, vor allem DHL, Wahl der Nummer, 4 bis 7 Arbeitstage, Reklamationen
- [Shouwu International: 德国经济 (Deutschland, Economy)](http://www.sedel.com/main-route-secondary.html?groupId=2&groupItemId=22) – vor allem Hermes, 4 bis 7 Arbeitstage
- [Shouwu International: 德国带电 (Deutschland, mit Batterien)](http://www.sedel.com/main-route-secondary.html?groupId=3&groupItemId=25) – vor allem DPD, 4 bis 7 Arbeitstage
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Nummernform, Sendungsverfolgung und Link, Status, Scantexte und ihre Phasen, Uhrzeiten, Orte, was Peek zeigt
:::
