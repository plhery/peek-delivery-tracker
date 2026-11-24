---
title: EMS Sendungsverfolgung: S10-Nummern, Status & Hilfe
description: Verfolge ein internationales EMS-Paket (Express Mail Service): 13-stellige S10-Nummern mit E, UPU-Zoll- und Zustellstatus, Verzögerungen und Post-Hilfe.
slug: ems-sendungsverfolgung
published: 2026-11-24
updated: 2026-11-24
---

**EMS** (*Express Mail Service*) ist der internationale Post-Expressdienst für Dokumente und Waren. Der **Weltpostverein (UPU)** gründete die **EMS Cooperative** ([ems.post](https://www.ems.post/en)) zu dessen Weiterentwicklung; im Juni 2026 zählte die Cooperative 179 Mitglieder. Statt eigene Flugzeuge oder Lieferwagen zu betreiben, verbindet EMS benannte nationale Postunternehmen weltweit — etwa [Japan Post](carrier:japan-post), [China Post](carrier:china-post), [Korea Post](carrier:korea-post), [USPS](carrier:usps), [Royal Mail](carrier:royal-mail), [La Poste](carrier:la-poste), [Poste Italiane](carrier:poste-italiane) und [Australia Post](carrier:australia-post) —, sodass Expressdokumente und Waren unter einer einheitlichen 13-stelligen Kennung laufen. Du kannst eine Sendung im globalen EMS-Tracker auf [items.ems.post](https://items.ems.post/) prüfen oder deine EMS-Sendungsnummer oben einfügen.

## EMS-Sendungsnummern (13-stelliges UPU-S10-Format)

Jede EMS-Sendung trägt einen 13-stelligen UPU-S10-Barcode, der mit dem Buchstaben **`E`** (für Express) beginnt, gefolgt von einem zweiten Dienstbuchstaben, 8 fortlaufenden Ziffern, 1 S10-Prüfziffer und dem zweistelligen ISO-Code des Absenderlands:

| Format (erfunden) | Worum es sich handelt |
| --- | --- |
| `EA482917367JP` | 13-stellige EMS-Sendung aus Japan (`EA`-Präfix + 9 Ziffern mit gültiger S10-Prüfziffer + `JP`). |
| `EE739184627CN` | 13-stellige EMS-Sendung aus China (`EE`-Präfix + 9 Ziffern mit gültiger S10-Prüfziffer + `CN`). |

Die letzten beiden Buchstaben bezeichnen immer das **Postunternehmen im Absenderland**, nicht das Zielland. Ein per EMS von Tokio nach Paris verschicktes Paket endet auf der gesamten Strecke auf `JP`, selbst nachdem [La Poste](carrier:la-poste) die Zustellung in Frankreich übernommen hat. Postsendungsnummern ohne `E` am Anfang (etwa `RR...` für Einschreiben oder `CP...` für Standardpakete) weist der EMS-Tracker als keine EMS-Sendungen zurück. Siehe [Sendungsnummern-Formate](guide:tracking-number-formats).

## Was die EMS-Sendungsstatus bedeuten

Die öffentliche Tracking-Tabelle der EMS Cooperative auf [items.ems.post](https://items.ems.post/) übersetzt die EDI-Ereignisse der teilnehmenden Postunternehmen in einheitliche englische Meldungen:

| EMS meldet | Was das für dich bedeutet | Peek zeigt |
| --- | --- | --- |
| `Posted` | Am Schalter eingeliefert oder vom Absender-Postunternehmen abgeholt. | „Beim Paketdienst eingegangen“ |
| `Arrived at export office`, `Departed from export office` | Im internationalen Auswechselungsamt des Absenderlands bearbeitet und ins Ausland abgesandt. | „Unterwegs“ |
| `Presented to export customs and security`, `Held for export customs inspection` | Durchläuft die Ausfuhrverzollung oder Luftsicherheitskontrolle im Absenderland. | „Beim Zoll“ |
| `Released from export customs and security` | Ausfuhrkontrolle abgeschlossen und bereit für den internationalen Versand. | „Unterwegs“ |
| `Export cancelled` | Der Exportversand wurde storniert oder vor dem Verlassen des Absenderlands zurückgeführt; wende dich an den Absender. | „Problem gemeldet“ |
| `Arrived at destination import office`, `Departed from destination import office` | Im internationalen Auswechselungsamt des Ziellands eingetroffen oder von dort zur inländischen Sortierung weitergeleitet. | „Unterwegs“ |
| `Held at destination import office`, `Presented to import customs` | Im Grenzauswechselungsamt zurückgehalten oder dem Einfuhrzoll im Zielland vorgelegt. | „Beim Zoll“ |
| `Released from import customs` | Durch den Einfuhrzoll freigegeben und an das inländische Postnetz übergeben. | „Unterwegs“ |
| `Arrived at sorting center`, `Arrived at post office` | Unterwegs über die inländischen Sortierzentren der Zielpost oder im örtlichen Zustellpostamt eingetroffen. | „Unterwegs“ |
| `Out for delivery` | Mit dem örtlichen Zusteller oder EMS-Kurier zur Zustellung unterwegs. | „In Zustellung“ |
| `Delivery attempted` | Zustellversuch ohne Erfolg; achte auf eine Benachrichtigungskarte oder Meldung des örtlichen Postamts. | „Zustellversuch“ |
| `Returned to sender` | Nach fehlgeschlagener Zustellung, Annahmeverweigerung oder Zollablehnung auf dem Rückweg ins Absenderland. | „Zurück an Absender“ |
| `Delivered` | Vom Ziel-Postunternehmen an den Empfänger oder eine berechtigte Abholstelle zugestellt. | „Zugestellt“ |

## Internationale Übergaben und Zoll bei EMS

Da EMS ein Kooperationsnetz nationaler Postunternehmen aus dem [EMS-Betreiberverzeichnis](https://www.ems.post/en/global-network/ems-operators) ist, bearbeiten zwei verschiedene Postorganisationen jede grenzüberschreitende Sendung:

- **Absenderpost vs. Zielpost:** Die Absenderpost übernimmt Annahme, Ausfuhrabfertigung und internationalen Versand; sobald `Arrived at destination import office` erscheint, steuert dein eigenes nationales Postunternehmen (oder dessen Expresssparte wie Parcelforce in Großbritannien) Einfuhrverzollung, Zustellversuche, Zweitzustellung und Lagerfristen in der Filiale.
- **EMS und lokale Post prüfen:** Der globale EMS-Feed meldet einheitliche EDI-Meilensteine, während die Sendungsverfolgung deiner Zielpost oft mehr inländische Details liefert — etwa Links zur Zollzahlung, Adressen der Abholfiliale, Zeitfenster oder Optionen für eine erneute Zustellung.
- **Zollabgaben und Einfuhrumsatzsteuer:** Grenzüberschreitende EMS-Pakete unterliegen den Zollvorschriften des Ziellands, der Einfuhrumsatzsteuer, Post-Verzollungsentgelten und Zöllen (einschließlich EU-Importgebühren für Sendungen von außerhalb der EU). Hängt das Tracking bei `Presented to import customs` oder `Held at destination import office`, achte auf einen offiziellen Brief, eine E-Mail oder eine Portalbenachrichtigung deiner örtlichen Post. Siehe [Pakete beim Zoll](guide:customs).

## EMS-Paket verspätet, hängen geblieben oder vermisst

- **Sendepause zwischen Export und Import:** Internationale Luftpostsendungen bleiben zwischen `Departed from export office` und `Arrived at destination import office` häufig mehrere Tage ohne Scan, besonders in Spitzenzeiten oder in der Warteschlange zur Zollannahme. Siehe [warum die Sendungsverfolgung nicht aktualisiert](guide:tracking-not-updating).
- **Deine lokale Post hilft, nicht der Weltpostverein:** Wie auf [EMS Tracking](https://www.ems.post/en/global-network/tracking) erläutert, bieten die EMS Unit und die UPU keine telefonische Sendungsverfolgung an und greifen nicht in Streitfälle zwischen Postunternehmen und Kunden ein. Bei einer fehlenden EMS-Sendung leitet dich die [EMS-Kontaktseite](https://www.ems.post/en/about-us/contacts-0) an den örtlichen EMS-Kundenservice oder das Callcenter weiter, das allgemeine EMS-Fragen beantwortet, deine Sendungsnummer ermittelt und bei der Suche hilft. Siehe [verlorenes Paket](guide:lost-parcel), [beschädigtes Paket](guide:damaged-parcel), [zugestellt, aber nicht erhalten](guide:delivered-not-received) und [zurück an Absender](guide:returned-to-sender).

## EMS Kundenservice und offizielles Betreiberverzeichnis

Da der EMS-Dienst jedes Landes vom jeweiligen benannten nationalen Postunternehmen betrieben wird, stellt [EMS Contact Us](https://www.ems.post/en/about-us/contacts-0) ein durchsuchbares Verzeichnis von A bis Z aller nationalen EMS-Kundenservice-Seiten und Callcenter nach Regionen bereit (Afrika, Arabischer Raum, Asien-Pazifik, Karibik, Europa, Lateinamerika und Nordamerika):

- **Telefonnummer und Kundenservice deines örtlichen EMS-Betreibers finden:** [ems.post/en/about-us/contacts-0](https://www.ems.post/en/about-us/contacts-0) und [ems.post/en/global-network/ems-operators](https://www.ems.post/en/global-network/ems-operators).
- **Globaler öffentlicher Tracker:** [items.ems.post](https://items.ems.post/) (Hinweise unter [ems.post/en/global-network/tracking](https://www.ems.post/en/global-network/tracking)).
- **Kein zentraler UPU-Telefonsupport:** Weder die EMS Unit noch der Weltpostverein bearbeiten einzelne Paketanfragen am Telefon.

> Vorsicht vor gefälschten SMS, die behaupten, ein „EMS-Paket“ sei gesperrt, bis du über einen unbekannten Link eine kleine Gebühr zahlst. Prüfe Zollgebühren immer direkt auf der offiziellen Website deines nationalen Postunternehmens mit deinem 13-stelligen EMS-Code. Siehe [Betrugs-SMS zu Paketlieferungen](guide:delivery-scam-texts).

## Ein EMS-Paket mit Peek verfolgen

Gibst du einen 13-stelligen S10-Code mit `E` (`EA482917367JP` oder `EE739184627CN`, erfunden) in [Peek](/) ein, leitet Peek ihn normalerweise zuerst an das durch die letzten beiden Buchstaben angegebene Absender-Postunternehmen weiter (etwa [Japan Post](carrier:japan-post) oder [China Post](carrier:china-post)), da EMS selbst keine eigene automatische Erkennungsregel besitzt. Um stattdessen den globalen Tracker der **EMS** Cooperative abzufragen, tippe beim Hinzufügen auf „Ändern“ oder in der Sendungsansicht auf „Paketdienst ändern“ und wähle **EMS** (auch die Suche nach „Express Mail Service“ oder „EMS Post“ findet ihn). Eingefügte Links im Format `items.ems.post/api/publicTracking/track?itemId=` erkennt Peek direkt als EMS.

Peek liest `items.ems.post` direkt aus, nachdem geprüft wurde, dass es sich um eine `E`-S10-Nummer mit gültiger Prüfziffer handelt, und zeigt die Phase („Beim Paketdienst eingegangen“, „Unterwegs“, „Beim Zoll“, „In Zustellung“, „Zustellversuch“, „Problem gemeldet“, „Zurück an Absender“, „Zugestellt“), den englischen EMS-Text und die Dienststelle an. EMS liefert Scan-Zeiten ohne Zeitzone: Peek ordnet jeden Scan der Zeitzone seiner Dienststelle zu (Tokio für `TOKYO INTERNATIONAL`, Paris für `ROISSY CDG`), einen Scan ohne Ortsangabe der Zeitzone des Nachbarscans, und zeigt alle Zeiten in der Uhrzeit deines Geräts an. Die EMS-Tabelle enthält kein voraussichtliches Lieferdatum, kein Gewicht, keinen Namen der unterschreibenden Person und keine gesonderte Zustellzeit.

Solange du das Paket in Peek geöffnet hast oder am Zustelltag Benachrichtigungen aktiviert sind, fragt Peek bis zu alle 10 Minuten nach; an dem Tag, an dem EMS `Out for delivery` meldet, ab 6 Uhr Ortszeit des neuesten Scan-Orts alle 2 Minuten, solange der EMS-Tracker antwortet. Andernfalls prüft Peek stündlich. Nach 12 Stunden mit lediglich „Etikett erstellt“ oder „Beim Paketdienst eingegangen“ oder nach zwei Tagen ohne Neuigkeiten fragt Peek stündlich nach, egal wie du die Sendung verfolgst, und nach sieben Tagen auf „Etikett erstellt“ alle 6 Stunden. Antwortet der EMS-Tracker nicht und Peek verfolgt das Paket allein über Tracking-Dienste, prüft Peek stündlich, nach einem ruhigen Tag alle 3 Stunden und an dem Tag, an dem das Paket in Zustellung ist, bis zu alle 15 Minuten, solange du die Sendung geöffnet hast oder Benachrichtigungen aktiviert sind.

Ist der neueste Scan vier Tage alt und das Paket steht noch auf „Etikett erstellt“, „Beim Paketdienst eingegangen“ oder „Unterwegs“, markiert Peek es mit dem Tag dieses Scans, etwa „Keine Aktualisierung seit Mo 12. Okt“. Auf der Paketseite steht dann „Auf langen Flügen, beim Zoll oder übers Wochenende wird es oft still um ein Paket. Peek fragt weiter bei EMS nach.“, samt Link „Hol dir Hilfe bei EMS“ zum EMS-Kontaktverzeichnis. Nach drei Wochen ohne neuen Scan heißt es „Seit über drei Wochen nichts Neues. Ist das Paket noch nicht angekommen, frag beim Verkäufer nach. Peek fragt weiter bei EMS nach.“

Bei den Benachrichtigungen kannst du zwischen „Jeden Scan“, „Wichtige Schritte“ und „Nur die Zustellung“ wählen. Peek beobachtet ausschließlich Tracking-Updates und kann weder Sendungen verzollen noch Einfuhrabgaben bezahlen oder deine örtliche Post für dich kontaktieren.

## Häufige Fragen zur EMS-Sendungsverfolgung

### Wie sieht eine gültige EMS-Sendungsnummer aus?

Eine EMS-Sendungsnummer ist ein 13-stelliger UPU-S10-Code, beginnend mit `E`, einem zweiten Buchstaben, 9 Ziffern (mit Prüfziffer am Ende) und dem zweistelligen ISO-Code des Absenderlands — zum Beispiel `EA482917367JP` oder `EE739184627CN` (erfunden).

### Wer liefert mein EMS-Paket in meinem Land aus?

EMS-Pakete werden vom benannten nationalen Postunternehmen deines Landes (oder dessen Expresssparte) zugestellt, etwa von [USPS](carrier:usps) in den USA, [Royal Mail](carrier:royal-mail) / Parcelforce in Großbritannien, [La Poste](carrier:la-poste) in Frankreich, [Poste Italiane](carrier:poste-italiane) in Italien oder [Australia Post](carrier:australia-post) in Australien.

### Warum aktualisiert mein EMS-Paket nach `Departed from export office` nicht weiter?

Nach `Departed from export office` befindet sich das Paket auf dem internationalen Luftweg oder wartet im internationalen Postauswechselungsamt des Ziellands auf die Annahme. Die Sendungsverfolgung geht weiter, sobald die Zielpost `Arrived at destination import office` scannt.

### Wen kontaktiere ich, wenn ein EMS-Paket feststeckt oder verloren ist?

UPU und EMS Unit bieten keine telefonische Sendungsverfolgung an und greifen nicht in Streitfälle zwischen Postunternehmen und Kunden ein. Über [ems.post/en/about-us/contacts-0](https://www.ems.post/en/about-us/contacts-0) findest du den örtlichen EMS-Kundenservice oder das Callcenter, das dir beim Auffinden deiner Sendung hilft.

:::sources
- [EMS Cooperative: Offizielle Website](https://www.ems.post/en) – Überblick zur UPU Express Mail Service Cooperative und dem weltweiten Postnetz
- [EMS Cooperative: Tracking-Leitfaden](https://www.ems.post/en/global-network/tracking) – 13-stelliges S10-Format, Nicht-Eingreifen von UPU/EMS Unit und Ausschluss telefonischer Sendungsverfolgung
- [EMS Cooperative: Globale Betreiberkontakte](https://www.ems.post/en/about-us/contacts-0) – A–Z-Verzeichnis nationaler EMS-Kundenservices nach Regionen (von Peek verlinkte Hilfeseite)
- [EMS Cooperative: Betreiber A–Z](https://www.ems.post/en/global-network/ems-operators) – Netzwerk der benannten nationalen Postunternehmen
- [Öffentliches EMS-Tracking-Portal](https://items.ems.post/) – weltweite öffentliche EMS-Sendungssuche
- [Universal Parcel Scraper: ems](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/ems) – S10-Prüfziffernvalidierung mit `E`-Präfix, Link-Regel `items.ems.post`, 18-Status-Zuordnung der EMS Cooperative und Datierung jedes Scans auf die Zeitzone seiner Dienststelle
:::
