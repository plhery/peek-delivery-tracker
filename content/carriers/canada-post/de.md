---
title: Canada Post Sendungsverfolgung: Paket aus Kanada, Status
description: Canada-Post-Paket verfolgen: wie die Trackingnummer aussieht, was die Status bedeuten, wer in Deutschland, Österreich und der Schweiz zustellt, Zoll.
slug: canada-post-sendungsverfolgung
published: 2026-11-03
updated: 2026-11-03
---

Canada Post ist die Post Kanadas. Ein Paket verfolgst du in der Sendungsverfolgung von Canada Post auf canadapost-postescanada.ca oder hier: Füg die Trackingnummer oben ins Feld ein. Die Nummer bekommst du vom Absender, meist in der Versand-E-Mail des Shops. Endet sie auf `CA`, fragt Peek mit derselben Nummer auch die Post deines Landes.

## Canada Post Trackingnummer: wie sie aussieht

Diese zwei Formen ordnet Peek Canada Post zu, hier mit erfundenen Ziffern:

| Nummer (erfunden) | Was es ist |
| --- | --- |
| `1234 5678 9012 3452` | 16 Ziffern, die letzte ist eine Prüfziffer. 16 Ziffern haben auch Nummern von DHL eCommerce, TNT, Correos Express und Evri UK. |
| `RR000000005CA` | Eine internationale Postnummer: 2 Buchstaben, 9 Ziffern, `CA`. Die letzte Ziffer vor `CA` ist eine Prüfziffer. Mit dieser Nummer fragt Peek auch die Post deines Landes. |

Canada Post findet ein Paket auch über die 15-stellige Nummer der Benachrichtigungskarte (delivery notice card), die ein Zusteller in Kanada hinterlässt, und über Referenznummern, die nur aus Ziffern bestehen. Peek nutzt beide, wenn du Canada Post selbst auswählst; wie, steht weiter unten.

Purolator, ein anderer Paketdienst aus Kanada, vergibt eigene Nummern: 12 Ziffern oder 3 Buchstaben und 9 Ziffern. Bei solchen Nummern fragt Peek von sich aus nicht Canada Post. Findet Canada Post nichts, schau, welchen Paketdienst die Versand-E-Mail nennt. Mehr in [Sendungsnummer finden](guide:find-tracking-number) und [Sendungsnummer: Welcher Paketdienst steckt dahinter?](guide:tracking-number-formats)

## Canada Post Sendungsverfolgung: was die Status bedeuten

Peek liest die Scans von Canada Post auf Englisch. Die Meldungen unten stehen wörtlich so in den Daten von Canada Post, in der Reihenfolge, in der ein Paket sie meist bekommt; rechts steht die Stufe, die Peek dafür zeigt.

**Von Kanada zu dir**

| Canada Post schreibt | Was es für dich heißt | In Peek |
| --- | --- | --- |
| `Electronic information submitted by shipper` | Der Absender hat ein Etikett erstellt, Canada Post hat das Paket aber noch nicht. Fragen gehen an den Shop. | „Etikett erstellt“ |
| `Item accepted at the Post Office` | Das Paket wurde in einer Postfiliale in Kanada aufgegeben. | „Beim Paketdienst eingegangen“ |
| `Item picked up by Canada Post` | Canada Post hat es beim Absender abgeholt. | „Beim Paketdienst eingegangen“ |
| `Item processed` | Es ist im Netz von Canada Post unterwegs. Die Zeile kann sich mehrmals wiederholen. | „Unterwegs“ |
| `International item being forwarded to destination country` | Es wird ins Zielland weitergeleitet. | „Unterwegs“ |
| `Item has arrived in foreign country` | Es ist im Zielland angekommen. | „Unterwegs“ |
| `Item presented to customs` | Es wurde im Zielland dem Zoll vorgelegt. | „Beim Zoll“ |
| `Customs has released item to post office` | Der Zoll hat es freigegeben und an die Post übergeben. | „Unterwegs“ |
| `Item has arrived at the delivery office in the destination country` | Es ist in der Zustellbasis im Zielland. | „Unterwegs“ |

Statt `Item has arrived in foreign country` kann auch `International shipment has arrived in a foreign country` stehen. `Item has been sent to customs in the destination country` zeigt Peek wie `Item presented to customs` als „Beim Zoll“.

**In Kanada**

| Canada Post schreibt | Was es für dich heißt | In Peek |
| --- | --- | --- |
| `International item mailed in originating country` | Ein Paket nach Kanada wurde im Ausland aufgegeben. Canada Post verfolgt es unter der Nummer der Post, die es verschickt hat. | „Beim Paketdienst eingegangen“ |
| `International item has left originating country and is en route to Canada` | Es hat das Absenderland verlassen und ist auf dem Weg nach Kanada. | „Unterwegs“ |
| `Item has arrived in Canada and will be presented for review` | Es ist in Kanada und wartet auf den Zoll. | „Beim Zoll“ |
| `Item was released by Customs and is now with Canada Post for processing` | Der Zoll ist fertig, Canada Post übernimmt. | „Unterwegs“ |
| `Item out for delivery` | Ein Zusteller bringt es heute. | „In Zustellung“ |
| `Notice card left indicating where and when to pick up item` | Die Zustellung hat nicht geklappt. Auf der Karte, die der Zusteller hinterlassen hat, steht, wo und ab wann du das Paket abholen kannst; es geht in eine Postfiliale. | „Zustellversuch“ |
| `Item available for pickup at Post Office` | Es liegt zur Abholung in der Postfiliale. | „Abholbereit“ |
| `Final Notice; Item will be returned to sender if not collected within 10 days` | Letzte Erinnerung: Holst du es nicht innerhalb von 10 Tagen ab, geht es zurück. | „Abholbereit“ |
| `Delivered to your community mailbox, parcel locker or apt./condo mailbox` | In den Gemeinschaftsbriefkasten, ein Paketfach oder den Briefkasten im Wohnhaus zugestellt. | „Zugestellt“ |
| `Delivered to recipient's safe drop location` | An deinem sicheren Ablageort abgelegt. | „Zugestellt“ |
| `Recipient not located at address provided. Item being returned to sender.` | Der Empfänger war unter der Adresse nicht zu finden; das Paket geht zurück. | „Problem gemeldet“ |
| `Item has been returned and is enroute to the Sender` | Es ist auf dem Rückweg zum Absender. | „Unterwegs“ |

`Item re-routed due to processing error; Possible delay` und `Delivery may be delayed due to transportation delay` kündigen eine Verzögerung an; Peek zeigt dafür „Problem gemeldet“. `Expected delivery date updated` heißt, dass Canada Post einen neuen Liefertag nennt. Bewegt hat sich das Paket damit nicht; Peek zeigt den neuen Tag als voraussichtlichen Liefertag.

Im Verlauf zeigt Peek einige Scans in eigenen Worten: Jede Zustellung heißt dort „Zugestellt“, eine Zustellung auf dem Rückweg „Zurück an Absender“ und `Out for delivery` „In Zustellung“. Alles andere bleibt auf Englisch, wie Canada Post es schreibt, auch `Item out for delivery`.

## Paket aus Kanada: Zustellung in Deutschland, Österreich und der Schweiz

**Wer zustellt.** Ist die Nummer deines Pakets eine internationale Postnummer, die auf `CA` endet und deren Prüfziffer stimmt, fragt Peek mit derselben Nummer auch die Post deines Landes: [DHL](carrier:dhl) in Deutschland, die [Österreichische Post](carrier:austrian-post) in Österreich und die [Schweizerische Post](carrier:swiss-post) in der Schweiz. Canada Post nennt Peek kein Zielland, darum zählt dein Land: in der iPhone-App die eingestellte Region, im Browser das Land, aus dem du das Paket hinzugefügt hast. Zeigt deine Post einen Scan, der mindestens so neu ist wie der neueste von Canada Post, verfolgt Peek das Paket dort weiter und behält den Verlauf von Canada Post. Einen Tag lang fragt Peek zusätzlich noch bei Canada Post nach, höchstens alle 55 Minuten. Bei 16-stelligen Nummern und in Liechtenstein fragt Peek keine andere Post.

**Zoll.** Ein Paket aus Kanada muss durch den Zoll. Was du wem zahlst, steht in [Paket beim Zoll](guide:customs). In Deutschland und Österreich gilt, wie in der ganzen EU: Für Online-Bestellungen von außerhalb der EU bis 150 € fällt seit dem 1. Juli 2026 ein pauschaler Zoll von 3 € je Warenkategorie an. Seit dem 1. November 2026 kommt eine Bearbeitungsgebühr der EU (Union handling fee) von 2 € je Warenkategorie dazu, egal wie viel die Ware wert ist. Die Schweiz gehört nicht zur EU, dort gelten eigene Regeln.

**In Kanada verpasst.** Hinterlässt der Zusteller eine Karte, zeigt Peek „Zustellversuch“; liegt das Paket in der Postfiliale bereit, zeigt es „Abholbereit“. Nach der letzten Erinnerung bleiben dir 10 Tage, dann geht es an den Absender zurück. Mehr in [Paket verpasst](guide:missed-delivery) und [Paket nicht abgeholt](guide:uncollected-parcel).

## Canada-Post-Paket verspätet, verloren oder zurück

**Keine neuen Scans.** Steht ein Paket vier Tage lang ohne neuen Scan auf „Etikett erstellt“, „Beim Paketdienst eingegangen“ oder „Unterwegs“, schreibt Peek: „Auf langen Flügen, beim Zoll oder übers Wochenende wird es oft still um ein Paket. Peek fragt weiter bei Canada Post nach.“ Dazu kommt ein Link zur Hilfe des Paketdiensts, der das Paket gerade hat, etwa „Hol dir Hilfe bei Canada Post“. Mehr in [Sendungsverfolgung aktualisiert sich nicht](guide:tracking-not-updating).

**Verloren.** Hast du online bestellt, wende dich zuerst an den Shop, der das Paket verschickt hat. Mehr in [Paket verloren](guide:lost-parcel).

**Zugestellt, aber nicht da.** Hat DHL, die Österreichische oder die Schweizerische Post zugestellt, schau zuerst in deren Sendungsverfolgung nach. Eine Zustellung von Canada Post in Kanada zeigt Peek nur als „Zugestellt“; wo das Paket liegt, steht in der Sendungsverfolgung von Canada Post, etwa `Delivered to your concierge or building manager`, `Delivered to mailroom` oder `Delivered to recipient's safe drop location`. Siehe auch [zugestellt, aber nicht erhalten](guide:delivered-not-received).

**Zurück.** `Recipient not located at address provided. Item being returned to sender.` und `Item has been returned and is enroute to the Sender` heißen, dass das Paket zurückgeht. Kommt es beim Absender an, zeigt Peek „Zurück an Absender“. Siehe [Paket zurück an Absender](guide:returned-to-sender).

## Canada Post Kundenservice und Kontakt

Für ein Paket aus Kanada ist meist der Absender oder der Shop dein erster Ansprechpartner. Ist das Paket schon bei deiner Post, fragst du bei Zustellung und Abholung dort nach; wie du sie erreichst, steht auf den Seiten zu [DHL](carrier:dhl), zur [Österreichischen Post](carrier:austrian-post) und zur [Schweizerischen Post](carrier:swiss-post). Wie du Canada Post selbst erreichst, steht auf canadapost-postescanada.ca.

## Canada Post Paket verfolgen mit Peek

[Peek](/) erkennt Canada Post sofort an internationalen Postnummern auf `CA`, deren Prüfziffer stimmt. Eine 16-stellige Nummer mit gültiger Prüfziffer passt zu mehreren Paketdiensten: Dann zeigt Peek „Tracking-Dienste werden geprüft…“, fragt Canada Post, DHL eCommerce, TNT, Correos Express und Evri UK zugleich und nimmt den, der die Nummer kennt. Stimmt ihre Prüfziffer nicht, ist Canada Post nicht dabei. Für die Nummer einer Benachrichtigungskarte oder eine Referenznummer tippst du auf „Ändern“ und wählst Canada Post unter „Alle Paketdienste“ oder über die Suche. Einen Tracking-Link von Canada Post mit `searchFor=` kannst du auch einfügen; bei Links mit `#/search` klappt das nur mit Postnummern auf `CA`, eine 16-stellige Nummer fügst du dann selbst ein.

Peek fragt Canada Post direkt, ohne Browser. Du siehst den Status, den Verlauf mit dem Ort jedes Scans (in Kanada mit Provinz), den voraussichtlichen Liefertag, ein Zeitfenster, sobald das Paket in Kanada in Zustellung ist und Canada Post eines nennt, die Uhrzeit der Zustellung und, wenn Canada Post ihn nennt, den Namen der Firma, die das Paket verschickt hat. Scans in Kanada liest Peek mit der Uhrzeit, die Canada Post angibt, Scans aus Ländern mit nur einer Zeitzone, etwa Deutschland, Österreich oder der Schweiz, in deren Ortszeit; angezeigt werden alle in der Zeitzone deines Geräts. Zustelloptionen, Empfängerdaten, Adressen, Unterschriften und Zustellfotos lässt Peek weg.

Peek schaut alle 10 Minuten nach, alle 2 Minuten, sobald das Paket in Zustellung ist und Canada Post selbst antwortet. Nach zwei Tagen ohne neuen Scan (nach 12 Stunden, solange es „Etikett erstellt“ oder „Beim Paketdienst eingegangen“ zeigt) schaut Peek nur noch stündlich nach. Für Mitteilungen wählst du „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“.

**Von dir nach Kanada.** Geht ein Paket mit internationaler Postnummer auf `DE`, `AT` oder `CH` nach Kanada, fragt Peek mit derselben Nummer auch Canada Post: sobald DHL oder die Schweizerische Post Kanada als Ziel nennt, oder wenn dein Land für Peek Kanada ist, etwa beim Empfänger dort. Die Österreichische Post nennt kein Ziel; bei ihr zählt nur dein Land. Für Nummern der Schweizerischen Post, die mit `L` beginnen, fragt Peek Canada Post nicht. Kontaktieren kann Peek Canada Post oder den Shop nicht für dich.

## Fragen zur Canada Post Sendungsverfolgung

### Wer bringt mir ein Paket von Canada Post in Deutschland, Österreich oder der Schweiz?

Bei einer internationalen Postnummer auf `CA` die Post deines Landes: DHL, die Österreichische Post oder die Schweizerische Post. Peek fragt sie mit derselben Nummer und zeigt ihre Scans, sobald sie mindestens so neu sind wie die von Canada Post. Bei 16-stelligen Nummern fragt Peek keine andere Post und bleibt bei Canada Post.

### Warum findet Peek meine Canada-Post-Nummer nicht?

Canada Post gibt dieselbe Antwort, wenn eine Nummer unbekannt, noch nicht gescannt oder abgelaufen ist. Peek zeigt dann „Warten auf den Paketdienst“. Prüf jede Ziffer: Bei 16-stelligen Nummern ist die letzte eine Prüfziffer, bei Postnummern die letzte vor `CA`.

### Was bedeutet „Item processed“ bei Canada Post?

Das Paket ist im Netz von Canada Post unterwegs. Die Zeile kann mehrmals hintereinander kommen; Peek zeigt dafür „Unterwegs“.

### Kann ich ein Paket mit der Nummer der Benachrichtigungskarte verfolgen?

Ja. Gib die 15-stellige Nummer der Karte ein, tipp auf „Ändern“ und wähl Canada Post unter „Alle Paketdienste“. Peek sucht damit das Paket bei Canada Post.

:::sources
- [Europäische Kommission: EU customs reform (Englisch)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 3 € Zoll je Warenkategorie bis 150 € seit 1. Juli 2026, 2 € Union handling fee seit 1. November 2026
- [Europäische Kommission: Customs Guidance on EUR 3 customs duty (Englisch)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – je Warenkategorie, Bearbeitungsgebühr unabhängig vom Wert
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Nummernformen, Wortlaute und ihre Stufen, Uhrzeiten, was Peek zeigt und wie es an die Post deines Landes übergibt
:::
