---
title: CTTYEX Sendungsverfolgung: CTTY…CN-Nummern & Pekinger Zeit
description: Verfolge ein CTTYEX-Paket (诚泰通运) aus Shenzhen: CTTY…CN-Format, englische Scans im chinesischen Tracker, Pekinger Uhrzeit und Peek.
slug: cttyex-sendungsverfolgung
published: 2026-11-21
updated: 2026-11-21
---

CTTYEX steht für *Shenzhen Chengtai Tongyun International Logistics* (`深圳诚泰通运国际物流`, kurz `诚泰通运`), einen grenzüberschreitenden E-Commerce-Weiterleiter in Shenzhen in Festlandchina, der Auslandspakete für Online-Händler versendet. Laut eigener Website besteht das Unternehmen seit 2011, deckt über 200 Länder und Regionen ab, unterhält mehr als sechs Niederlassungen in Festlandchina und arbeitet für Händler auf Amazon, Wish, Tophatter, VOVA und Shopify mit Charterflügen in die USA, nach Großbritannien, Kanada, Deutschland und Australien. Seine Sendungsnummern bestehen aus `CTTY`, zehn Ziffern und `CN` (`CTTY` für *Chengtai Tongyun*): Prüfe deine Nummer auf [cttyvip888.com](https://www.cttyvip888.com/assets/html/search.html) oder oben im Suchfeld.

## Sendungsnummer von CTTYEX: Aufbau und Beispiele

CTTYEX beschreibt das Nummernformat auf seiner Website nicht, vergibt aber ein festes Muster (erfundene Beispiele):

| Nummer (erfunden) | Bedeutung |
| --- | --- |
| `CTTY4829105736CN` | `CTTY`, zehn Ziffern und `CN`: eine Sendungsnummer von CTTYEX. |
| `CTTY482910573CN` | Nur neun Ziffern: Eine Stelle fehlt. Antwortet kein Dienst darauf, meldet die Seite auf `peektracker.com`: „Das beginnt wie eine Sendungsnummer von CTTYEX, aber ein Zeichen fehlt. Vielleicht ist es ein Tippfehler: Vergleiche die Nummer mit deiner Versandnachricht.“ |
| `LX482910576CN` | Zwei Buchstaben, neun Ziffern und `CN`: eine Nummer von [China Post](carrier:china-post), trotz `CN`-Endung nicht von CTTYEX. |

Peek akzeptiert Kleinbuchstaben und Leerzeichen. CTTYEXs eigene Website nicht: Das Suchfeld lässt nur Buchstaben und Ziffern zu, und der Tracker findet Nummern ausschließlich in Großbuchstaben (`CTTY…CN`, ohne Leerzeichen).

**Wo du die Nummer findest.** Da Online-Händler die Auftraggeber von CTTYEX sind, steht die Nummer in deiner Versandbestätigung oder Bestellübersicht ([Sendungsnummer finden](guide:find-tracking-number)).

**Auf der Website von CTTYEX.** Öffne `物流查询` (`search.html`), gib eine Nummer pro Zeile in Großbuchstaben ein und klicke auf `提交` (Absenden): Dadurch öffnet sich der Tracker `快件跟踪` (`index/tracknumber/track`), der nur Pakete der letzten zwei Monate findet.

## Tracking-Status von CTTYEX erklärt

Die Oberfläche des Trackers ist chinesisch, die einzelnen Scans stehen jedoch auf Englisch – der neueste oben – unter einer chinesischen Kopfzeile mit Zielland (`目的地`) und Gesamtstatus (`状态`):

| CTTYEX-Kopfzeile | Was das für dich bedeutet | Peek zeigt |
| --- | --- | --- |
| `运输途中` (englisch `In Transit`) | Unterwegs; kann bereits stehen, solange nur Auftragsdaten vorliegen – lies daher die einzelnen Scans darunter. | „Unterwegs“ |
| `查询不到` (englisch `Not Found`) | Nicht auffindbar: Der Tracker kennt kein Paket unter dieser Nummer und listet keine Scans. | „Warten auf CTTYEX“ |

Zuerst folgen CTTYEXs Schritte in Festlandchina, jeweils ohne Ortsangabe:

| CTTYEX meldet | Was das für dein Paket bedeutet | Peek zeigt |
| --- | --- | --- |
| `Order information received` | Die Auftragsdaten des Händlers sind bei CTTYEX eingegangen; das Paket ist noch beim Verkäufer. | „Etikett erstellt“ |
| `Arrive at the cross border receiving point`, `Arrive at the receiving point` | Am grenzüberschreitenden Annahmepunkt in Festlandchina übernommen: CTTYEX hat das Paket. | „Beim Paketdienst eingegangen“ |
| `Goods leave the operation center` | Hat das Betriebszentrum von CTTYEX in Festlandchina verlassen. | „Unterwegs“ |
| `Arrival at domestic internationality airport` | Am Abflughafen in Festlandchina vor dem Abflug (`internationality` ist CTTYEXs Schreibweise). | „Unterwegs“ |
| `Departure from the airport of origin domestic` | Aus Festlandchina abgeflogen. | „Unterwegs“ |

Nach dem Flug stammen die Einträge von CTTYEXs Agenten im Zielland und dem zustellenden lokalen Netzwerk:

| CTTYEX meldet | Was das für dein Paket bedeutet | Peek zeigt |
| --- | --- | --- |
| `Arrive at the destination international airport`, `Item arrives at destination airport` | Das Flugzeug ist in deinem Land gelandet. | „Unterwegs“ |
| `Import customs clearance` | Einfuhrverzollung (hinter dem Ort des Eingangshafens geschrieben); noch nicht freigegeben. | „Beim Zoll“ |
| `transfer to local acting company International self-operation team` | Nach dem Zoll an CTTYEXs Agenten im Zielland übergeben. | „Unterwegs“ |
| `Leave the … processing center` | Hat ein Bearbeitungszentrum verlassen (der Ortsname steht anstelle der Punkte). | „Unterwegs“ |
| `Internationaliy Package received at processing center` | Erster Scan des Zustellnetzwerks (`Internationaliy` ist CTTYEXs Schreibweise; kein neuer Versandbeginn). | „Unterwegs“ |
| `Arrived at logistics carrier Facility In Transit to Next Facility` | In einer Anlage des Zustellnetzwerks (nur `logistics carrier` genannt), unterwegs zur nächsten. | „Unterwegs“ |
| `Accepted at logistics carrier Regional Destination Facility` | Erster Scan des Zustellnetzwerks in deiner Region. | „Unterwegs“ |
| `Arrive at the last mile sorting and distribution center` | Im Verteilzentrum für die letzte Meile in deiner Nähe. | „Unterwegs“ |
| `Leaving the sorting center` | Hat dieses Sortierzentrum verlassen. | „Unterwegs“ |
| `Arrive at the sorting and distribution center` | In einem Sortierzentrum eingetroffen (der Ort folgt hinter `Arrive`). | „Unterwegs“ |
| `package is on the way` | Folgt hinter dem Ort auf den Sortierzentrum-Scan; heißt noch nicht, dass das Zustellfahrzeug gestartet ist. | „Unterwegs“ |
| `Delivered` | Vom Zustellnetzwerk zugestellt (beginnt mit Ort und Postleitzahl). | „Zugestellt“ |

In den USA beginnen die drei Zeilen von `Accepted at logistics carrier Regional Destination Facility` bis `Leaving the sorting center` mit Ort, Bundesstaat und ZIP-Code. Das sind die bisher aus CTTYEXs Tracker erfassten Meldungen; jede andere Zeile zeigt Peek im englischen Wortlaut von CTTYEX und ordnet sie anhand ihrer Wörter ein.

**Jede Uhrzeit ist Pekinger Zeit.** CTTYEXs Tracker druckt Zeiten ohne Zeitzone, doch jede Angabe gilt in Pekinger Zeit (`UTC+8`) – **auch für Scans in deinem Land**. Da in Peking um `16:00 UTC` der nächste Kalendertag beginnt, trägt ein Scan in Deutschland, Österreich oder der Schweiz nach `17:00` Uhr im Winter (bzw. nach `18:00` Uhr im Sommer) im Tracker bereits das Datum des Folgetags.

## Wer stellt Pakete von CTTYEX zu?

CTTYEX übernimmt das Paket in Festlandchina und fliegt es aus; nach der Landung erledigt sein Agent den Einfuhrzoll und ein lokales Zustellnetz liefert aus. Weder Website noch Tracker nennen dieses Netz (`logistics carrier`) oder eine zweite Sendungsnummer – die `CTTY…CN`-Nummer bleibt bis zur Zustellung gültig. Angaben zu Lieferzeiten, Zustellversuchen oder Abholfristen veröffentlicht CTTYEX nicht: Richte dich nach der Schätzung des Shops und bei einer Benachrichtigungskarte nach [Zustellung verpasst](guide:missed-delivery).

## CTTYEX-Paket kommt nicht an oder Tracking steht still

**Nur `Order information received`.** CTTYEX hat bisher nur die Daten, auch wenn oben `运输途中` steht. Bleibt es tagelang dabei, frag beim Händler nach, ob das Paket übergeben wurde.

**Ruhe nach `Departure from the airport of origin domestic` oder vier Tage Pause.** Zwischen Abflug und Landung bzw. Einfuhrzoll erscheint nichts Neues; nach vier Tagen ohne neuen Scan unterwegs meldet Peek „Keine Aktualisierung seit“ mit dem Datum des letzten Scans ([Warum sich das Tracking nicht aktualisiert](guide:tracking-not-updating), [Pakete aus China verfolgen](guide:tracking-from-china)).

**Hängt bei `Import customs clearance`.** Der Tracker nennt weder Gebühren noch den lokalen Zusteller und bestätigt daher keine Zahlungsaufforderung per SMS oder E-Mail. Lies zuerst [Gefälschte Paket-SMS erkennen](guide:delivery-scam-texts) sowie [Paket hängt beim Zoll](guide:customs).

**`查询不到`, verloren oder zurückgeschickt.** Prüfe die Nummer beim Händler und tippe Großbuchstaben (nach zwei Monaten löscht der Tracker Sendungen ebenfalls). Da CTTYEX keine Reklamationsregeln für Empfänger führt, wende dich für Ersatz oder Erstattung an den Verkäufer oder Marktplatz ([Paket verloren](guide:lost-parcel), [Zurück an den Absender](guide:returned-to-sender)).

## Kontakt zu CTTYEX

CTTYEXs chinesischsprachige [Kontaktseite 联系我们](https://www.cttyvip888.com/assets/html/contact.html) nennt eine Anschrift im Straßenviertel Fuyong, Bezirk Bao'an, Shenzhen in Festlandchina (`深圳市宝安区福永街道怀德新村8巷12号一层`), sowie eine E-Mail-Adresse (`cttywlvip888@163.com`) – ohne Telefonnummer, Chat, Kontaktformular oder Empfängerservice.

**Vorsicht vor Phishing-SMS.** Offizielle Domains von CTTYEX sind `www.cttyvip888.com` und `cttyvip888.com` ([Gefälschte Paket-SMS erkennen](guide:delivery-scam-texts)). Schreibe zu deiner Bestellung dem Händler.

## CTTYEX-Pakete mit Peek verfolgen

[Peek](/) erkennt CTTYEX sofort anhand einer `CTTY…CN`-Nummer („Erkannter Paketdienst“), die kein anderer Dienst im Katalog nutzt, oder über einen `cttyvip888.com`-Link mit `nos=` (bei manueller Wahl außerhalb von 8 bis 40 Buchstaben und Ziffern mit mindestens einer Ziffer meldet Peek, dass CTTYEX „vergibt keine Nummern wie diese“).

Peek liest Status, Scans und Zielland aus CTTYEXs Tracker: Steht am Zeilenanfang im Ausland ein Ort (oder Ort und Bundesstaat), übernimmt Peek ihn als Scan-Ort ohne Postleitzahl, blendet Dubletten und Telefonnummern aus und rechnet jede Uhrzeit von Pekinger Zeit (`UTC+8`) in deine Gerätezeitzone um („Uhrzeiten in deiner Zeitzone: …“), sodass das Datum zu deinem Tag passt. Maßgeblich für den Status ist der neueste erkannte Scan (die Kopfzeile nur, falls kein Scan erkannt wird); mangels Lieferprognose, Gewicht oder Zweitnummer verfolgt Peek das Paket durchgehend unter der CTTY-Nummer weiter.

Peek prüft bis zu alle 10 Minuten bei geöffnetem Paket oder am Liefertag mit aktiven Mitteilungen; sonst sowie nach 12 Stunden auf „Etikett erstellt“ / „Beim Paketdienst eingegangen“ oder zwei ruhigen Tagen stündlich. Für Mitteilungen wählst du „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“. CTTYEX oder den Verkäufer kontaktieren kann Peek nicht.

## Häufige Fragen zur Sendungsverfolgung von CTTYEX

### Wer ist CTTYEX?

*Shenzhen Chengtai Tongyun International Logistics* (`深圳诚泰通运国际物流`), ein E-Commerce-Weiterleiter aus Shenzhen in Festlandchina mit der Website `cttyvip888.com` und Sendungsnummern im Format `CTTY…CN`.

### Wo kann ich eine CTTY-Nummer verfolgen?

Über `物流查询` auf [cttyvip888.com](https://www.cttyvip888.com/assets/html/search.html) (in Großbuchstaben ohne Leerzeichen eingegeben) oder direkt in Peek.

### Sind die Uhrzeiten im CTTYEX-Tracking in meiner Zeitzone?

Nein. Alle Zeiten im Tracker sind Pekinger Zeit (`UTC+8`), auch bei Scans in Europa oder Amerika – ein Scan am späten Nachmittag oder Abend vor Ort trägt dort bereits das Datum des nächsten Tages. Peek rechnet jede Zeit in deine Gerätezeitzone um.

### Warum meldet CTTYEXs Tracker `查询不到`?

Das bedeutet auf Chinesisch *nicht auffindbar*: Unter der Eingabe wurde kein Paket gefunden. Prüfe Tippfehler, verwende Großbuchstaben und frag beim Händler nach; Sendungen, die älter als zwei Monate sind, zeigt der Tracker ebenfalls nicht mehr an.

### An wen wende ich mich bei einem fehlenden CTTYEX-Paket?

Zuerst an den Verkäufer, der den Versandvertrag mit CTTYEX geschlossen hat. CTTYEX nennt auf seiner chinesischen Seite lediglich eine Adresse in Shenzhen und eine E-Mail-Adresse.

:::sources
- [CTTYEX: 深圳诚泰通运国际物流 (Startseite)](https://www.cttyvip888.com/) – Firmenname, Leistungen und Kontaktdaten im Fußbereich
- [CTTYEX: 关于我们 (Über uns)](https://www.cttyvip888.com/assets/html/about_us.html) – Gründung 2011, über 200 Länder, mehr als 6 Filialen in Festlandchina, Plattformen und Charterflüge
- [CTTYEX: 物流查询 (Suche)](https://www.cttyvip888.com/assets/html/search.html) – eine Nummer pro Zeile (Buchstaben und Ziffern) und Button 提交
- [CTTYEX: 快件跟踪 (Tracking)](https://www.cttyvip888.com/index/tracknumber/track) – Verfolgung für Pakete der letzten 2 Monate, Ziel- und Statusspalten
- [CTTYEX: 联系我们 (Kontakt)](https://www.cttyvip888.com/assets/html/contact.html) – Adresse in Fuyong (Bao'an, Shenzhen, Festlandchina) und E-Mail cttywlvip888@163.com, ohne Telefon oder Formular
- [Universal Parcel Scraper: CTTYEX](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/cttyex) – CTTY…CN-Format, Abfrage in Großbuchstaben, 23 Status- und Kopfzeilen, durchgehende Pekinger Zeit und Ortserkennung
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Abgrenzung zu China Post und Darstellung in Peek
:::
