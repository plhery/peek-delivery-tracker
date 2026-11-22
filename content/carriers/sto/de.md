---
title: STO Express Sendungsverfolgung: 申通快递 Nummern & Status
description: STO Express (申通快递) Paket in China verfolgen: wie die Nummern aussehen, was 签收 und die STO-Scans bedeuten, Abholstationen und Kontakt zu STO.
slug: sto-express-sendungsverfolgung
published: 2026-11-22
updated: 2026-11-22
---

STO Express (申通快递, Shentong) ist ein 1993 gegründeter chinesischer Expressdienst und bezeichnet sich als Chinas erstes privates Expressunternehmen. Eine STO-Nummer gehört meist zu einem Paket innerhalb Chinas — oft einer Online-Bestellung auf dem Weg zu dir, zu Bekannten oder zum Lager eines Weiterleiters. Du verfolgst sie auf [der STO-Website](https://www.sto.cn/pc) im Feld `查快递` (nur auf Chinesisch) oder direkt im Feld oben.

## STO Express Sendungsnummer: wie sie aussieht

STO-Nummern (`快递单号`) bestehen nur aus Ziffern. Peeks STO-Abfrage nimmt 12, 13 oder 15 Ziffern an; die neuesten Frachtbriefe haben 15 Ziffern. Mit erfundenen Ziffern:

| Nummer (erfunden) | Was es ist |
| --- | --- |
| `774829173654281` | `77` und 13 weitere Ziffern: ein Frachtbrief von STO Express. Auch [BRT](carrier:brt) und Yunda nutzen 15 Ziffern, daher fragt Peek bei STO nach. |
| `482917365428` | 12 Ziffern: ein älteres STO-Format, das auch [FedEx](carrier:fedex), [SF Express](carrier:sf-express), [ZTO Express](carrier:zto) und viele weitere nutzen. |
| `4829173654281` | 13 Ziffern: ebenfalls ein älteres STO-Format, das Peek nicht automatisch STO zuordnet. |

Leerzeichen spielen in Peek keine Rolle.

**Wo sie steht.** Der Verkäufer nennt sie in den Versanddaten deiner Bestellung, und sie steht auf dem Versandetikett (`快递面单`) auf dem Paket. Nicht gefunden? Siehe [Sendungsnummer finden](guide:find-tracking-number).

**Auf der STO-Website.** Gib die Nummer auf `sto.cn` in das Feld `查快递` ein und drücke `查询` (Suchen). Laut STOs Servicevertrag bieten Website, App und WeChat-Konto die Sendungsverfolgung ein Jahr lang ab Versandtag an.

## STO Express Tracking-Status erklärt

Jeder Scan im STO-Tracking trägt einen Typ auf Chinesisch, eine Uhrzeit in chinesischer Zeit (UTC+8) und den scannenden STO-Standort. Peek zeigt diese Typen auf Englisch:

| STO-Scan | In Peek | Peek-Status | Was das für dich bedeutet |
| --- | --- | --- | --- |
| `收件` | `Picked up` | Beim Paketdienst eingegangen | STO hat das Paket beim Absender abgeholt. |
| `发件` | `Departed`, mit `for` und nächster Station, sofern von STO genannt | Unterwegs | Hat einen STO-Standort Richtung nächstem verlassen. |
| `到件` | `Arrived` | Unterwegs | In einem Sortierzentrum oder einer Filiale eingetroffen. |
| `派件` | `Out for delivery` | In Zustellung | Ein Kurier ist damit auf Tour. |
| `驿站代收` | `At a parcel station for collection` | Abholbereit | Eine Paketstation (`驿站`) hat es für dich angenommen. Hole es ab. |
| `柜机代收` | `In a parcel locker for collection` | Abholbereit | Liegt in einer Packstation. Hole es ab. |
| `客户签收` | `Delivered` | Zugestellt | Jemand hat unterschrieben. STO trägt das auch ein, wenn eine Station oder ein Locker unterschreibt. |
| `签收` | `Delivered` | Zugestellt | Unterschrieben. |

Andere Scantypen behalten in Peek STOs chinesische Bezeichnung. Ein Scantyp mit Rücksendung (`退回` oder `退件`) erscheint als `Return to the sender started` („Problem gemeldet“). Spätere Zustellscans bilden den Rückweg ab: `Out for delivery back to the sender`, `In a parcel locker or station on its way back` und `Returned to the sender`.

**An einen Weiterleiter geschickt?** Dann bedeutet `签收`, dass das Lager des Weiterleiters unterschrieben hat und STOs Abschnitt beendet ist. Danach übernimmt die eigene Nummer des Weiterleiters.

## STO Express Zustellung in China: Kuriere, Stationen und Locker

STO gibt an, mit seinem Netz 99,7 % der Gemeinden in China zu erreichen. Laut Servicevertrag darf STO ein Paket auf vier Wegen zustellen:

- an dich persönlich;
- an eine mit dir zusammenlebende oder von dir benannte Person;
- an eine Annahmestelle (`代收点`) oder einen Abholautomaten (`自提柜`) im Gebiet der Adresse;
- auf eine andere von dir oder dem Absender gewünschte Weise.

Ist das Paket mit unversehrter Außenverpackung auf eine dieser Arten zugestellt, gilt die Leistung laut Vertrag als erbracht. „Abholbereit“ in Peek ist daher normal: Das Paket wartet an einer Station oder in einem Locker in der Nähe. Die genaue Adresse der Station steht im eigenen STO-Tracking; solange das Paket dort wartet, liest Peek den Abholcode (`取件码`) aus dem neuesten Scan aus, sofern vorhanden.

Um Empfängerdaten zu ändern, bietet STOs Online-Kundenservice ein eigenes Thema (`修改收件人信息`): Frage dort nach (Kontakt unten) oder beim Verkäufer.

## STO Paket aktualisiert sich nicht, verloren oder zurück

**Noch nichts sichtbar.** STOs Tracking antwortet für eine noch unbekannte Nummer oder nach Ablauf der Einjahresfrist gar nicht. Bleibt es leer, prüfe die Nummer beim Verkäufer. Ein unter STO Express gespeichertes Paket zeigt bis dahin „Warten auf STO Express“.

**Kein Update.** Bleibt ein Paket unterwegs 4 Tage ohne neuen Scan, zeigt Peek das Datum des letzten Scans („Keine Aktualisierung seit …“) und „Auf langen Flügen, beim Zoll oder übers Wochenende wird es oft still um ein Paket. Peek fragt weiter bei STO Express nach.“ samt dem Link „Hol dir Hilfe bei STO Express“ zum Online-Kundenservice, in dem fehlende Updates (`快递单号没有记录更新`) ein eigenes Thema sind. Siehe [warum die Sendungsverfolgung stoppt](guide:tracking-not-updating) und [Pakete aus China verfolgen](guide:tracking-from-china).

**Verloren oder beschädigt.** Frag zuerst den Verkäufer: Er hat STO beauftragt, nicht du. Laut Servicevertrag muss der Absender oder eine von ihm benannte Person binnen 30 Tagen nach Kenntnis von Verlust oder Schaden mit Nachweis reklamieren. Ohne Versicherung (`保价`) erstattet STO den tatsächlichen Schaden bis 500 Yuan, bei Dokumenten bis 100 Yuan. Ein versichertes Paket wird bis zum deklarierten Wert entschädigt. Ohne Versicherung oder Wertnachweis zahlt STO das Dreifache der Versandkosten. Verspätet sich STO aus eigenem Verschulden, entfallen die Versandkosten. Siehe [Paket verloren](guide:lost-parcel).

**Zurückgesendet.** STO kann ein Paket zurückschicken, wenn die Adresse unklar ist, der Empfänger die Annahme verweigert oder zwei Zustellversuche ohne Verschulden von STO fehlschlagen; die Kosten trägt der Absender. In Peek siehst du `Return to the sender started` und anschließend den Rückweg. Siehe [zurück an Absender](guide:returned-to-sender).

**Zugestellt, aber nicht da.** `签收` und `客户签收` bedeuten, dass jemand unterschrieben hat: du, ein Haushaltsmitglied, eine benannte Person oder eine Station bzw. ein Locker. Prüfe Stationen und Locker in der Nähe, die Gebäuderezeption und ggf. den Weiterleiter. Für eine Beschwerde nutze `投诉反馈` im Online-Kundenservice von STO. Siehe [zugestellt, aber nicht erhalten](guide:delivered-not-received).

## STO Express Kontakt und Kundenservice

Laut den Seiten von STO:

- **Online-Kundenservice** auf `page.sto.cn`, verlinkt auf `sto.cn` als `在线咨询` (Online-Anfrage): ein Chat-Assistent mit menschlichen Mitarbeitern von 08:30 bis 22:00 Uhr Pekinger Zeit, mit Schaltflächen für Versand, Preise und Beschwerden (`投诉反馈`).
- **WeChat:** Service-Konto und Mini-Programm zum Versenden. STO ist auch auf Douyin vertreten. Alle drei stehen in der Fußzeile von `sto.cn`.

Diese Seite nennt keine Telefonnummer: Peek führt nur Kontakte auf, die im Text der eigenen Seiten von STO stehen, und dort findet sich keine Nummer. Bei Fragen zur Bestellung wende dich an den Verkäufer.

## Ein STO Express Paket mit Peek verfolgen

Die Form allein verrät Peek nicht sicher, dass es sich um STO handelt. Füge die Nummer trotzdem ein: [Peek](/) verfolgt über 4500 Paketdienste.

Bei einer 15-stelligen Nummer mit `77` am Anfang fragt Peek sofort bei STO Express und BRT nach („Tracking-Dienste werden geprüft…“). Kennt STO das Paket, erscheint „STO Express hat dieses Paket.“ und Peek speichert es dort. Andernfalls steht „noch nicht gefunden · Peek prüft weiter“. Füge es dennoch hinzu: Das Paket zeigt „Wahrscheinlich STO Express“ und „Warten auf den ersten Scan von STO Express“, und Peek fragt nach den ersten Stunden alle 6 Stunden bei STO nach (in der iPhone- oder Android-App bzw. im Konto). Bei einer Abfrage ohne Konto prüft Peek in den ersten 6 Stunden, danach beim Öffnen des Links (höchstens alle 6 Stunden, nach zwei Tagen höchstens täglich) sowie bei aktivierten Benachrichtigungen täglich bis 30 Tage nach der Abfrage. Nach einer Woche ohne Treffer bittet Peek dich, die Nummer beim Absender zu prüfen oder einen anderen Paketdienst zu wählen.

Automatisch fragt Peek nur bei `77`-Nummern bei STO nach. Bei einer 12-stelligen Nummer fragt es zunächst andere Dienste dieser Form (u. a. DHL und BRT) und danach Tracking-Dienste; nennen diese STO Express, ordnet Peek das Paket dort ein und fragt STO beim nächsten Lauf direkt ab.

Um von Anfang an STOs eigenes Tracking zu lesen, tippe auf „Ändern“ und wähle STO Express unter „Passt zu dieser Nummer“ (12 Ziffern oder 15 mit `77` am Anfang). Bei 13 Ziffern oder 15 ohne `77` wähle es unter „Alle Paketdienste“ oder suche nach `STO` bzw. `申通`. Bei einem bereits hinzugefügten Paket nutze „Paketdienst ändern“. Peeks STO-Abfrage nimmt nur 12, 13 oder 15 Ziffern an — bei anderen Längen meldet Peek „vergibt keine Nummern wie diese“.

Peek liest STOs eigenes Tracking hinter dem Online-Kundenservice aus. Zu jedem Scan zeigt es den Schritt (Tabelle oben), die Stadt der STO-Stelle (deren chinesischen Namen, wo Peek sie nicht verorten kann) sowie die Zeit, gelesen auf chinesischer Uhr und angezeigt in deiner Geräte-Zeitzone („Uhrzeiten in deiner Zeitzone:“). Wiederholte Scans erscheinen nur einmal, und das Gewicht wird angezeigt, wenn STOs Waagen es erfasst haben. Außerdem liest Peek den Abholcode (`取件码`) des neuesten Stations- oder Locker-Scans aus, solange das Paket dort wartet, sowie den Unterzeichner bei Zustellung; STOs vollständigen Text samt Namen und Telefonnummern von Kurieren und Stationen sowie Abholadressen lässt Peek weg. Einen voraussichtlichen Liefertermin nennt STO nicht. Peek folgt nur der STO-Nummer — die Nummer eines Weiterleiters fügst du als eigenes Paket hinzu.

Solange du das Paket in Peek geöffnet hast — oder am Zustelltag mit aktivierten Benachrichtigungen — prüft Peek direkt bei STO bis zu alle 10 Minuten, und ab 6:00 Uhr Pekinger Zeit am Tag, an dem STO es in Zustellung scannt, bis zu alle 2 Minuten, solange STOs eigener Tracker antwortet; sonst, sowie nach zwei Tagen ohne Neuigkeiten (12 Stunden bei „Etikett erstellt“ oder „Beim Paketdienst eingegangen“), prüft Peek stündlich. Benachrichtigungen gibt es für „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“. STO, den Weiterleiter oder den Verkäufer kontaktieren kann Peek nicht für dich.

## Fragen zur STO Express Sendungsverfolgung

### Ist STO Express seriös?

Ja. STO Express wurde 1993 gegründet, bezeichnet sich als Chinas ersten privaten Expressdienst und Vorreiter beim Franchising. Das Unternehmen ist am chinesischen A-Aktienmarkt notiert und gibt 75 Sortierzentren, 5.075 unabhängige Niederlassungen und über 98.000 lokale Filialen an.

### Was bedeutet 签收 beim STO-Tracking?

Unterschrieben: Peek zeigt `签收` und `客户签收` als „Zugestellt“. Du, ein Haushaltsmitglied, eine benannte Person oder eine Station bzw. ein Locker hat unterschrieben. `驿站代收` und `柜机代收` bedeuten, dass das Paket an einer Station oder in einem Locker abholbereit liegt.

### Warum zeigt meine STO-Nummer kein Tracking an?

STO hat das Paket noch nicht gescannt, die Nummer ist falsch oder der Versand liegt mehr als ein Jahr zurück — STO speichert Trackingdaten nur ein Jahr. Prüfe die Nummer beim Verkäufer oder füge sie in Peek hinzu, das weiter prüft.

### Wo finde ich den Abholcode für mein STO-Paket?

Solange das Paket an einer Station oder in einem Locker wartet, liest Peek den Abholcode (`取件码`) aus dem neuesten Scan mit aus, sofern er dort steht, und zeigt `At a parcel station for collection` oder `In a parcel locker for collection`. Die genaue Abholadresse findest du in STOs eigenem Tracking auf `sto.cn`.

### Wie erreiche ich STO Express?

Über den Online-Kundenservice auf der STO-Website: Mitarbeiter antworten dort von 08:30 bis 22:00 Uhr Pekinger Zeit, und es gibt eine Beschwerde-Schaltfläche (`投诉反馈`). Bei Shop-Bestellungen frag zuerst den Verkäufer.

:::sources
- [STO Express: 申通快递官网 (home)](https://www.sto.cn/pc) – Tracking-Feld, WeChat-Servicekonto und Mini-Programm, Douyin
- [STO Express: 关于我们 (about STO)](https://www.sto.cn/pc/about) – gegründet 1993, erster privater Expressdienst, Franchise-Modell, A-Aktien-Notierung, Sortierzentren, Niederlassungen, Filialen, Gemeindeabdeckung
- [STO Express: 申通快递服务协议 (express service agreement)](https://www.sto.cn/h5/standard) – Tracking ein Jahr, Zustellarten, Rücksendungen, Entschädigung mit und ohne Versicherung, Frist 30 Tage, Verspätung
- [STO Express: 在线客服 (online customer service)](https://page.sto.cn/ued-projects/sto-customer-onlinekf?fromSource=pc-stocn&pc=true) – Chat, menschlicher Support 08:30–22:00 Uhr, Beschwerden, Themen kein Tracking-Update und Empfängerdaten ändern
- [Universal Parcel Scraper: STO Express](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/sto) – Nummernformen, Scantypen, Rücksendungen, chinesische Zeit, Gewicht, Abholcode, Unterzeichner, gezeigte und weggelassene Felder, kein Liefertermin
:::
