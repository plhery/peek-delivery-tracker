---
title: Amazon Shipping Sendungsverfolgung: Nummer und Hilfe
description: Amazon-Shipping-Paket verfolgen: wie die Tracking-ID aussieht, welcher Tracker sie kennt und an wen du dich wendest, wenn das Paket nicht ankommt.
slug: amazon-shipping-sendungsverfolgung
published: 2026-10-12
updated: 2026-10-27
---

Ein Paket von Amazon Shipping verfolgst du ohne Amazon-Konto mit seiner Tracking-ID auf Amazons öffentlichem Amazon-Shipping-Tracker, etwa track.amazon.fr. Der zeigt nur Pakete, die nicht aus einer Bestellung bei Amazon selbst stammen. Was du direkt bei Amazon bestellt hast, verfolgst du dagegen unter „Meine Bestellungen“.

## Amazon Shipping Tracking-ID: wie sie aussieht

Eine deutsche Version gibt es nicht: Amazons europäischer Amazon-Shipping-Tracker führt Seiten nur für Großbritannien (track.amazon.co.uk), Frankreich (track.amazon.fr), Italien, Spanien und Indien. Für Nummern mit `DE`, `AT` oder `CH` fragt Peek den französischen.

Diese Formen erkennt Peek (alle Ziffern erfunden):

| Form | Was sie ist |
| --- | --- |
| `DE0000000000` | Ein europäisches Länderkürzel und 10 Ziffern, etwa `DE`, `AT`, `CH`, `FR` oder `UK`. |
| `TBA123456789012` | `TBA`, `TBC` oder `TBM` und 12 Ziffern. |

Der Haken: Amazon Logistics, das Amazons eigene Bestellungen ausliefert, nutzt genau dieselben Formen. Nur Amazons Tracker trennt die beiden, denn er kennt nur Amazon-Shipping-Pakete und meldet für alle anderen „nicht gefunden“. Hast du bei Amazon selbst bestellt, lies [Amazon Logistics Tracking](carrier:amazon-logistics). Eine Bestellnummer wie `000-0000000-0000000` ist keine Trackingnummer, und zwei andere Buchstaben mit 10 Ziffern, etwa `US0000000000`, hält Peek nicht für eine Amazon-Nummer. Keine Nummer? Die hat der Shop: [Sendungsnummer finden](guide:find-tracking-number).

## Amazon Shipping Paket nicht angekommen oder verloren

Dein Ansprechpartner ist der Shop, bei dem du bestellt hast. In der EU gilt laut Your Europe: Der Händler ist für die Lieferung verantwortlich und haftet für Schäden an der Ware ab dem Versand, bis du sie erhältst.

**Als zugestellt markiert, aber nicht da.** Der Händler muss sich an den Zustelldienst wenden und nachweisen, dass geliefert wurde. Kann er das nicht, muss er dir einen identischen Artikel schicken oder den Kaufpreis erstatten. Mehr dazu: [Paket zugestellt, aber nicht erhalten](guide:delivered-not-received).

**Beschädigt.** Der Händler trägt das Transportrisiko: Er muss die Ware reparieren oder ersetzen oder dir den Kaufpreis erstatten.

**Verloren oder verspätet.** Ist nichts anderes vereinbart, sollte der Händler die Ware innerhalb von 30 Tagen liefern. Kommt sie nicht, erinnerst du ihn daran und setzt ihm eine weitere angemessene Frist. Hält er auch die nicht ein, kannst du die Bestellung stornieren, und er muss dir dein Geld so schnell wie möglich erstatten. Steht nur das Tracking still, hilft [Sendungsverfolgung aktualisiert sich nicht](guide:tracking-not-updating).

## Amazon Shipping Kontakt

Für ein Paket, das nicht ankommt oder beschädigt ist, ist wie oben der Shop dein Ansprechpartner.

> Amazon warnt: Bekommst du eine Nachricht zu einem Kauf, antworte nicht und klick auf keinen Link. Amazon fordert dich nicht per Telefon oder E-Mail zu Zahlungen auf und nie zum Kauf einer Geschenkkarte. Ruf keine Nummern an, die du per SMS oder E-Mail bekommst oder in der Online-Suche findest. Verdächtiges meldest du mit Amazons Selfservice-Tool. Mehr dazu: [Fake-Paket-SMS erkennen](guide:delivery-scam-texts).

## Amazon Shipping Paket mit Peek verfolgen

Füge die Nummer oder einen track.amazon-Link (`/tracking/` und die Nummer) in [Peek](/) ein. Peek fragt Amazons Tracker, ob er die Nummer kennt. Antwortet Amazon, siehst du „Amazon Shipping bietet eine öffentliche Sendungsverfolgung an. Peek hält diese Lieferung für dich aktuell.“ Kennt Amazon sie nicht, gilt sie als Amazon-Logistics-Lieferung, die du meist unter „Meine Bestellungen“ verfolgst. Klappt die Prüfung gerade nicht, versuch es noch einmal. In der Liste unter „Ändern“ fehlt Amazon Shipping: Diese Prüfung ist der einzige Weg. Eine Postleitzahl ist nicht nötig.

Zu sehen sind der Status, der Verlauf mit Ort, Region und Land jedes Scans, der erwartete Liefertag und der Zeitpunkt der Zustellung. Peek schaut tagsüber alle 10 Minuten nach, alle 2 Minuten, sobald das Paket in Zustellung ist, nachts alle ein bis zwei Stunden, setzt jeden Scan auf eine Karte und schickt dir auf Wunsch Mitteilungen: zu jedem Scan, nur zu wichtigen Schritten oder nur am Liefertag. Statt Amazons eigener Statustexte zeigt es eigene kurze Bezeichnungen auf Deutsch, etwa „In Zustellung“ oder „Zugestellt“. Nur „Arrived at sorting center“, „Arrived at delivery center“ und „Departed facility“ bleiben Englisch. Den Namen des Shops, deinen Namen, die Adressen, die Unterschrift und das Zustellfoto zeigt es nicht. Amazon ist die einzige Quelle: Gibt Amazon den Verlauf nicht mehr heraus, sagt Peek das und fragt nicht weiter nach. Einem anderen Paketdienst folgt es nicht.

## Fragen zu Amazon Shipping

### Ist Amazon Shipping dasselbe wie Amazon?

Ein Amazon-Shipping-Paket verfolgst du mit der Tracking-ID auf Amazons eigenem Tracker, aber was dieser öffentlich zeigt, stammt nicht aus einer Bestellung bei Amazon selbst. Bei Problemen wendest du dich an den Shop. Was du bei Amazon selbst bestellt hast, steht unter „Meine Bestellungen“.

### Gibt es Amazon Shipping in Deutschland?

Einen deutschen Tracker von Amazon Shipping gibt es nicht. Amazons europäischer Tracker hat Seiten nur für Großbritannien, Frankreich, Italien, Spanien und Indien; Peek fragt für Nummern mit `DE`, `AT` oder `CH` den französischen.

### Wie lange dauert Amazon Shipping?

Gibt Amazons Tracker einen erwarteten Liefertag an, zeigt Peek ihn, außer bei Nummern mit `TBA`, `TBC` oder `TBM`. Ist mit dem Shop nichts anderes vereinbart, sollte eine Bestellung in der EU laut Your Europe innerhalb von 30 Tagen geliefert werden.

:::sources
- [Your Europe: Versand und Lieferung](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_de.htm) – Haftung des Händlers vom Versand bis zum Erhalt, 30 Tage, Nachfrist, Stornierung und Erstattung
- [Your Europe: Fragen und Antworten zu Versand und Lieferung](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/faq/index_de.htm) – Händler verantwortlich für die Lieferung, Nachweis der Zustellung, identischer Artikel oder Erstattung, Transportrisiko
- [About Amazon: 5 praktische Tipps gegen Betrugsmaschen](https://www.aboutamazon.de/news/amazon-prime-und-shopping/5-praktische-tipps-die-dir-helfen-sicher-bei-amazon-einzukaufen-und-betrugsmaschen-zu-verhindern) – Nachrichten zu einem Kauf, Zahlungen, Geschenkkarten, Nummern, Verdächtiges melden
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – was Peek erkennt und liest, welcher Tracker welche Nummern kennt, Länderseiten des europäischen Trackers, Amazon-Bestellungen nur im Konto
:::
