---
title: Jifan Sendungsverfolgung: JFGJ-Nummern & Pekinger Zeit
description: Verfolge ein Jifan-Paket (吉凡国际物流) aus Festlandchina in die USA: Aufbau von JFGJ…CN-Nummern, Bedeutung der Statuszeilen und warum alle Zeiten UTC+8 sind.
slug: jifan-sendungsverfolgung
published: 2026-11-21
updated: 2026-11-21
---

Jifan ist Shenzhen Jifan International Logistics (`深圳市吉凡国际物流有限公司`, kurz `吉凡国际物流`), ein 2017 gegründetes Logistikunternehmen mit Sitz in Shenzhen (Festlandchina) und NVOCC-Mitglied. Es befördert Pakete für Online-Händler aus Festlandchina an Käufer in den Vereinigten Staaten – per Luft- und Seefracht. Seine Sendungsnummern bestehen aus `JFGJ`, neun Ziffern und `CN`. Gib deine Nummer im [Tracker von Jifan](https://www.szjf56.cn/index/tracknumber/track) ein oder füge sie oben ins Feld ein; in Peeks Katalog findest du den Dienst auch über `Jifan International Logistics`, `吉凡国际物流` oder `深圳市吉凡国际物流有限公司`.

## Jifan-Sendungsnummer: Aufbau und Beispiele

Jifans Website erläutert das Nummernformat nicht, doch alle Sendungen folgen einem festen Muster. `JFGJ` steht für die Pinyin-Anfangsbuchstaben des Firmennamens: `吉凡国际` (*Jifan Guoji*, „Jifan International“). Alle Beispiele sind frei erfunden:

| Nummer (erfunden) | Bedeutung |
| --- | --- |
| `JFGJ482910573CN` | `JFGJ`, neun Ziffern und `CN`: eine Sendungsnummer von Jifan (ohne Prüfziffernprüfung auf der neunten Stelle). |
| `JFGJ48291057CN` | Nur acht Ziffern: nicht Jifans Format, und Peek ordnet Jifan nicht zu. Prüfe, ob eine Ziffer fehlt. |
| `RR482910576CN` | Zwei Buchstaben, neun Ziffern und `CN`: eine Nummer von [China Post](carrier:china-post), nicht von Jifan – trotz `CN` am Ende. |

Auf `peektracker.com` erhält eine um ein Zeichen zu kurze `JFGJ…CN`-Nummer den Hinweis: „Das beginnt wie eine Sendungsnummer von Jifan, aber ein Zeichen fehlt. Vielleicht ist es ein Tippfehler: Vergleiche die Nummer mit deiner Versandnachricht.“ In Peek spielen Kleinschreibung, Leerzeichen und Bindestriche keine Rolle. Auf Jifans eigener Seite musst du die Nummer in Großbuchstaben und ohne Leerzeichen eingeben.

**Wo du die Nummer findest.** In der Versand-E-Mail des Händlers oder in deiner Bestellübersicht. Falls sie fehlt, lies [Sendungsnummer finden](guide:find-tracking-number).

**Auf Jifans Website.** Die Startseite (`szjf56.cn`, Bereich `单号查询`) nimmt bis zu 10 durch Zeilenumbruch getrennte Nummern entgegen; die Tracking-Seite `快件跟踪` mit der Schaltfläche `跟踪(Track)` erlaubt bis zu 20 Nummern gleichzeitig, findet aber nur Pakete aus den letzten zwei Monaten (`2个月内的包裹`).

## Jifan-Tracking-Status erklärt

Jifans Tracking-Seite ist auf Chinesisch gehalten, die einzelnen Sendungszeilen stehen jedoch auf Englisch – neueste zuerst, jeweils mit Zeitstempel. Darüber zeigt eine Zusammenfassung das Zielland (`目的地`), den Gesamtstatus (`状态`), die letzte Zeit (`最新时间`) und den letzten Eintrag (`最新记录`). Zwei häufige Statuswerte in der Kopfzeile:

| Der Tracker zeigt | Was das für dich bedeutet |
| --- | --- |
| `运输途中` | Chinesisch für „unterwegs“. Lies die englischen Zeilen darunter, um den genauen Stand zu sehen. |
| `查询不到` | Chinesisch für „nicht auffindbar“. Der Tracker kennt kein Paket unter dieser Nummer und zeigt keine Scans. |

Zuerst erscheinen Jifans eigene Schritte in Festlandchina – ohne Ortsangabe –, danach folgen nach dem Flug die Meldungen des US-Agenten und des Zustellnetzes (dessen Name im Tracker durch `logistics carrier` ersetzt wird). Jifans Tracker läuft auf demselben System wie CTTYEX und nutzt dieselben Formulierungen:

| Jifan meldet | Was das für dein Paket bedeutet | Peek zeigt |
| --- | --- | --- |
| `Order information received` | Der Händler hat die Auftragsdaten übermittelt; Jifan hat das Paket noch nicht. | „Etikett erstellt“ |
| `Arrive at the cross border receiving point`, `Arrive at the receiving point` | Jifan hat das Paket an seiner grenzüberschreitenden Annahmestelle in Festlandchina übernommen. | „Beim Paketdienst eingegangen“ |
| `Goods leave the operation center` | Das Paket hat Jifans Betriebszentrum in Festlandchina verlassen. | „Unterwegs“ |
| `Arrival at domestic internationality airport` | Am Abflughafen in Festlandchina eingetroffen (`internationality` ist die Schreibweise des Trackers). | „Unterwegs“ |
| `Departure from the airport of origin domestic` | Der Flug hat Festlandchina verlassen. | „Unterwegs“ |
| `Arrive at the destination international airport` | Ankunft am internationalen Zielflughafen in den USA. | „Unterwegs“ |
| `Import customs clearance` | Einfuhrverzollung in den USA (nach dem Ort des Einreisehafens). | „Beim Zoll“ |
| `Leave the … processing center` | Weitertransport ab dem genannten US-Verarbeitungszentrum. | „Unterwegs“ |
| `Arrived at logistics carrier Facility In Transit to Next Facility` | Durchlauf in einer Einrichtung des US-Zustellers auf dem Weg zur nächsten Station. | „Unterwegs“ |
| `Accepted at logistics carrier Regional Destination Facility` | Eingang im regionalen Zielzentrum des US-Zustellers; bleibt „Unterwegs“, da Jifan das Paket bereits in Festlandchina angenommen hat. | „Unterwegs“ |

Eine Abschlussmeldung über die erfolgte Übergabe zeigt Peek als „Zugestellt“. In den USA beginnen manche Zeilen mit Stadt, Bundesstaat und ZIP-Code.

**Jede Uhrzeit ist Pekinger Zeit.** Jifans Tracker druckt sämtliche Zeitstempel ohne Zeitzone – und zwar durchgängig in Pekinger Zeit (`UTC+8`), auch bei Scans aus den Vereinigten Staaten. In Peking wechselt das Datum um `16:00 UTC`: Ein Scan in New York nach 11:00 Uhr im Winter (12:00 Uhr im Sommer) oder in Los Angeles nach 08:00 Uhr im Winter (09:00 Uhr im Sommer) trägt im Tracker bereits das Datum des Folgetags.

## Wer liefert Jifan-Pakete aus?

Jifans Website führt vier Versandlinien auf, alle von Festlandchina in die **Vereinigten Staaten**:

- **US-Economy-Kleinpaket** (`美国经济小包-轻小件`): flache, leichte Sendungen unter 360 g und unter 1,8 cm Dicke, per Direktfrachtflug, zugestellt durch [UPS](carrier:ups) in den gesamten USA einschließlich Überseeinseln und Militäradressen, durchgehend verfolgbar. Richtlaufzeit: rund 10 bis 12 Kalendertage (`自然日`).
- **US-Luftfracht-Kleinpaket** (`美国空运小包标快`): 0 bis 4,5 kg, Direktfrachtflug, Zustellung per UPS (`UPS MI` laut Jifan) in den gesamten USA inklusive Überseeinseln und Militäradressen. Rund 8 bis 10 Kalendertage.
- **Matson-Seefrachtpakete** (`美森正班（限时达）海运大/小包`): Kleinpakete bis 4,5 kg laufen wie die Luftfrachtlinie (`UPS MI`, gesamte USA); größere Pakete von 4,5 bis 68 kg gehen per `UPS GROUND` ausschließlich aufs US-Festland (`仅限美国本土`). Rund 20 bis 25 Kalendertage.
- **US-Seefracht für Übergroßes** (`美国海运超大件专线`): 100 bis 2.000 kg pro Packstück auf Matson- und COSCO-Schiffen, anschließend per Lkw bis zur Tür (Gewerbe- und Privatadressen), nur US-Festland ohne Überseeinseln und Militäradressen. Rund 25 bis 40 Kalendertage.

Für Economy-Sendungen sowie Luft- und Seepakete bis 4,5 kg gilt laut Jifan ein einziger Frachtbrief bis zum Ziel (`一单到底`). Der Tracker nennt weder das Zustellunternehmen noch eine Zweitnummer; hat dir der Händler zusätzlich eine UPS-Nummer mitgeteilt, kannst du sie direkt unter [UPS](carrier:ups) verfolgen. Eigene Empfängerregeln zu Zustellversuchen oder Abholstellen ([Zustellung verpasst](guide:missed-delivery)) sowie zu Zöllen und Einfuhrabgaben ([Paket hängt beim Zoll](guide:customs)) veröffentlicht Jifan nicht.

## Jifan-Paket aktualisiert sich nicht, fehlt oder geht zurück

Jifans Vertragspartner sind die versendenden Unternehmen in Festlandchina, nicht die Empfänger: Wende dich bei Verspätung, Verlust, Beschädigung oder Rücksendung zuerst an den Verkäufer oder Marktplatz.

**`查询不到` (nicht auffindbar).** Prüfe die Nummer auf Tippfehler, gib sie in Großbuchstaben ein und frag beim Händler nach, falls sie unbekannt bleibt. Peek zeigt „Warten auf Jifan“ und fragt weiter ab. Kennt Jifan die Nummer auch nach zwei Tagen noch nicht und hat kein anderer Dienst geantwortet, meldet Peek: „Auch nach zwei Tagen kennt der Paketdienst diese Nummer nicht. Prüfe sie, wähle einen anderen Paketdienst oder lösche das Paket.“ Danach prüft Peek täglich und eine Woche nach dem Hinzufügen wöchentlich.

**Nur `Order information received`.** Jifan hat erst die elektronischen Daten, noch nicht das Paket. Tut sich mehrere Tage nichts, frag den Verkäufer, ob die Ware übergeben wurde.

**Funkstille nach dem Flughafen oder auf See.** Nach `Arrival at domestic internationality airport` oder `Departure from the airport of origin domestic` folgt oft erst nach der Landung oder beim US-Zoll der nächste Eintrag, und Seefracht ist wochenlang unterwegs. Bleibt ein Paket unterwegs vier Tage ohne neuen Scan, markiert Peek es mit dem Tag des letzten Scans („Keine Aktualisierung seit Mo 12. Okt“) und ergänzt: „Auf langen Flügen, beim Zoll oder übers Wochenende wird es oft still um ein Paket. Peek fragt weiter bei Jifan nach.“ (ohne Hilfe-Button, da Jifan keine Empfänger-Hilfeseite anbietet). Nach über drei Wochen ohne neuen Scan rät Peek stattdessen: „Ist das Paket noch nicht angekommen, frag beim Verkäufer nach.“ Siehe [Warum sich das Tracking nicht aktualisiert](guide:tracking-not-updating) und [Pakete aus China verfolgen](guide:tracking-from-china).

**Vorsicht vor Phishing-SMS.** Jifans offizielle Domains lauten `www.szjf56.cn` und `szjf56.cn`, und der Tracker weist keinerlei Zoll- oder Zustellgebühren aus. Lies [Gefälschte Paket-SMS erkennen](guide:delivery-scam-texts), bevor du auf Zahlungsaufforderungen per SMS oder E-Mail reagierst.

**Verloren, beschädigt, zurückgeschickt oder trotz Zustellvermerk nicht da.** Seinen Versandkunden in Festlandchina sagt Jifan auf der Startseite Entschädigung bei Verspätung und bei Verlust die Erstattung der Versandkosten plus denselben Betrag obendrauf zu (`延误即赔,丢件按运费退一赔一`): Das gilt zwischen Jifan und dem Händler – bitte den Shop um Rückerstattung oder Ersatz. Siehe [Paket verloren](guide:lost-parcel), [Paket beschädigt](guide:damaged-parcel), [Zurück an den Absender](guide:returned-to-sender) und [Zugestellt, aber nicht erhalten](guide:delivered-not-received).

## Jifan Kontakt

Im Abschnitt `联系我们` auf Jifans Startseite (`www.szjf56.cn`):

- **Hotline**: `400-186-0001` (`咨询电话`), eine Servicenummer in Festlandchina. Öffnungszeiten oder Erreichbarkeit aus dem Ausland und auf Englisch nennt Jifan nicht.
- **E-Mail**: `925930140@qq.com` (`联系邮箱`, QQ-Mail-Adresse, Ansprechpartner `冯生`), ohne Angaben zu Bearbeitung oder unterstützten Sprachen.
- **Büro**: `深圳市龙岗区南湾街道2990号` (Nr. 2990, Straßenviertel Nanwan, Bezirk Longgang, Shenzhen, Festlandchina).

Die Website ist rein chinesisch und bietet weder Kontaktformular noch Live-Chat für Empfänger – kontaktiere bei Fragen zu deiner Bestellung zuerst den Verkäufer.

## Jifan-Pakete mit Peek verfolgen

[Peek](/) erkennt Jifan sofort anhand einer `JFGJ…CN`-Nummer („Erkannter Paketdienst“), da kein anderer unterstützter Dienst dieses Muster nutzt. Ein Link zu `szjf56.cn/index/tracknumber/track` mit einer `JFGJ…CN`-Nummer hinter `nos=` funktioniert ebenfalls (stehen mehrere darin, übernimmt Peek die erste).

Hat dir der Händler eine andere Nummer geschickt, füge sie trotzdem ein: Peek verfolgt über 4.000 Paketdienste, prüft bei mehrdeutigen oder unbekannten Mustern Tracking-Dienste und lässt dich über „Ändern“ oder „Paketdienst ändern“ den passenden Anbieter wählen. Jifans eigenen Tracker fragt Peek nur für `JFGJ…CN`-Nummern ab – wählst du Jifan für ein anderes Format, zeigt Peek an, dass Jifan „vergibt keine Nummern wie diese“.

Peek liest Status, alle englischen Verlaufszeilen und das Zielland direkt aus Jifans Tracker. Beginnt eine Zeile in den USA mit Stadt, Bundesstaat und ZIP-Code, übernimmt Peek Stadt und Bundesstaat als Scan-Ort und verwirft den ZIP-Code. Jede Zeitangabe wird als Pekinger Zeit (`UTC+8`) gelesen und in die Zeitzone deines Geräts umgerechnet; auf `peektracker.com` sowie in der iPhone- und Android-App schließt der Verlauf mit „Uhrzeiten in deiner Zeitzone: …“. Über „Website von Jifan öffnen“ rufst du deine Nummer direkt auf `szjf56.cn` auf.

Peeks Status richtet sich nach dem neuesten zugeordneten Scan und greift nur dann auf den Zusammenfassungsstatus (`运输途中`) zurück, wenn keine Zeile eine Stufe ergibt. Da Jifans Tracker weder Lieferdatum noch Gewicht, Tarifnamen, Zustellpartner oder Zweitnummer liefert, begleitet Peek die `JFGJ…CN`-Nummer bis zum Schluss direkt bei Jifan.

Solange Jifans Tracker antwortet, prüft Peek bis zu alle 10 Minuten, wenn das Paket geöffnet ist oder am erwarteten Liefertag Mitteilungen aktiv sind. Ab 6 Uhr auf der Uhr des Paketdienstes (Pekinger Zeit) an dem Tag, an dem Jifan die Zustelltour meldet, sind bei geöffnetem Paket oder aktiven Mitteilungen Prüfungen bis zu alle 2 Minuten möglich. Geschlossen und ohne Mitteilungen sowie nach zwei Tagen ohne Neuigkeiten (12 Stunden bei „Etikett erstellt“ oder „Beim Paketdienst eingegangen“) prüft Peek stündlich; antwortet nur ein Tracking-Dienst, prüft Peek stündlich, alle 3 Stunden sobald der letzte Scan einen Tag alt ist und bis zu alle 15 Minuten am Zustelltag, aber nur bei geöffnetem Paket oder aktiven Mitteilungen. Benachrichtigungen gibt es für „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“. Jifan oder den Händler kontaktieren kann Peek nicht.

## Häufige Fragen zur Jifan-Sendungsverfolgung

### Wer ist Jifan Logistics?

Shenzhen Jifan International Logistics (`深圳市吉凡国际物流有限公司`), ein 2017 in Shenzhen (Festlandchina) gegründeter Logistikdienstleister, der für Händler Pakete per Luft- und Seefracht aus Festlandchina an Käufer in den USA verschickt und `JFGJ…CN`-Nummern vergibt.

### Wo kann ich eine JFGJ…CN-Nummer verfolgen?

Auf Jifans Tracking-Seite [快件跟踪 auf szjf56.cn](https://www.szjf56.cn/index/tracknumber/track) in Großbuchstaben oder direkt in Peek. Jifans Tracker speichert nur Sendungen der letzten zwei Monate.

### Warum stimmen die Uhrzeiten im Jifan-Tracker nicht mit der US-Ortszeit überein?

Alle Zeiten im Tracker stehen in Pekinger Zeit (`UTC+8`), auch für Scans in den Vereinigten Staaten. Im Winter liegt Peking 13 Stunden vor New York und 16 Stunden vor Los Angeles, sodass Scans nach 11:00 Uhr in New York oder nach 08:00 Uhr in Los Angeles bereits das Datum des nächsten Tags tragen. Peek rechnet jede Uhrzeit in deine Gerätezeitzone um.

### Was bedeutet `查询不到` im Jifan-Tracker?

Das heißt auf Chinesisch „nicht auffindbar“: Unter der eingegebenen Nummer liegt kein Paket vor. Kontrolliere die Schreibweise, tippe Großbuchstaben ohne Leerzeichen und wende dich an den Verkäufer, wenn weiterhin nichts erscheint; Pakete, die älter als zwei Monate sind, zeigt der Tracker nicht mehr an.

### Wie lange dauert die Lieferung mit Jifan?

Laut Jifans Website dauern US-Luftfracht-Kleinpakete etwa 8 bis 10 Kalendertage, Economy-Kleinpakete etwa 10 bis 12, Matson-Seepakete etwa 20 bis 25 und übergroße Seefrachtsendungen etwa 25 bis 40 Kalendertage. Maßgeblich für deine Bestellung ist die Lieferzeitangabe des Händlers.

:::sources
- [Jifan: 深圳市吉凡国际物流有限公司 (Startseite)](https://www.szjf56.cn/) – Firmenname, Gründung 2017, vier US-Linien, Gewichts- und Maßgrenzen, Zustellung per UPS und UPS Ground, Matson und COSCO, Kalendertage, Ein-Frachtbrief-Prinzip, Entschädigungszusage an Versender, Hotline, E-Mail und Adresse in Shenzhen
- [Jifan: 快件跟踪 (Sendungsverfolgung)](https://www.szjf56.cn/index/tracknumber/track) – chinesischer Tracker, Schaltfläche 跟踪(Track), Ergebnisspalten, bis zu 20 Nummern und Zwei-Monats-Zeitraum
- [Universal Parcel Scraper: Jifan](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/jifan) – JFGJ…CN-Aufbau und Initialen, nos=-Link, CTTYEX-System, chinesische und englische Statusmeldungen, Pekinger Zeit sowie Ortsextraktion ohne ZIP-Code
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – China-Post-Muster und gemeinsame Regeln zur Statusauswertung
:::
