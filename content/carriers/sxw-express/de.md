---
title: SXW Express Sendungsverfolgung: SXW-Nummer und Status
description: SXW-Express-Paket (Shengxingwang) aus Shenzhen verfolgen: Aufbau der SXW-Nummer, offizieller Tracker, Statusmeldungen, Zusteller und Zollgebühren.
slug: sxw-express-sendungsverfolgung
published: 2026-11-21
updated: 2026-11-21
---

SXW Express ist der Name, unter dem Peek die `Shenzhen Shengxingwang Supply Chain Management Co., Ltd.` (`深圳胜兴旺供应链管理有限公司`) führt – ein Logistikunternehmen im **chinesischen Shenzhen**, das für Exporteure und Onlinehändler Waren weltweit versendet. Hat ein chinesischer Shop deine Bestellung mit SXW verschickt, achte auf eine Nummer aus `SXW` und zwölf Ziffern und füge sie oben in das Suchfeld ein. Du kannst sie auch selbst über das Feld *Express Tracking* auf `sxwexpress.cn` abfragen; genau diesen Tracker liest Peek für dich aus.

## SXW-Express-Sendungsnummer: Aufbau und Beispiele

Auf der Website von SXW Express steht keine Formatbeschreibung. Das Muster, das Peek eindeutig SXW Express zuordnet, hat 15 Zeichen: `SXW` gefolgt von zwölf Ziffern. Beispiele mit frei erfundenen Ziffern:

| Nummer (frei erfunden) | Bedeutung |
| --- | --- |
| `SXW482910573649` | `SXW` und zwölf Ziffern: das Format von SXW Express. Peek erkennt SXW Express sofort. |
| `SXW48291057364` | Nur elf Ziffern: passt nicht zum SXW-Aufbau. Prüfe in der Versandnachricht, ob eine Ziffer fehlt. |
| `GFUS48291057364918` | `GFUS` und vierzehn Ziffern: Sendungsnummer von [GOFO Express](carrier:gofo), die im SXW-Datensatz für Pakete in die **USA** hinterlegt sein kann. |

Leerzeichen, Bindestriche und Kleinschreibung spielen beim Einfügen in Peek keine Rolle. Den Code vergibt SXW beim Buchen an den Verkäufer; falls du ihn suchst, lies [Sendungsnummer finden](guide:find-tracking-number).

**Abfrage auf der SXW-Website.** Auf der Startseite von `sxwexpress.cn` findest du unter dem Reiter *Express Tracking* das Eingabefeld *Enter your tracking number here!* und die Schaltfläche *Track*. Das Ergebnis öffnet sich in einem neuen Tab auf dem chinesischen SXW-Tracker, der auch eine englische Abfrageseite (`sxwexpress.cn:8082/en/trackIndex.htm`) besitzt. Daneben verlinkt ein 17TRACK-Logo auf `17track.net` – das ist ein externer Tracking-Dienst und nicht der eigene Tracker von SXW.

## Statusmeldungen von SXW Express erklärt

Auf dem SXW-Tracker stammen drei Statuszeilen auf Chinesisch aus dem eigenen System von SXW (Peek zeigt sie auf Englisch an), während die meisten englischen Zeilen von der beauftragten Versandlinie kommen. Das bedeuten die wichtigsten Einträge:

| SXW-Tracker / Anzeige in Peek | Was das für dein Paket heißt | Peek zeigt |
| --- | --- | --- |
| `Parcel Data Received`, `Electronic notification of order creation received.` | Der Verkäufer hat die Sendungsdaten elektronisch übermittelt; noch hat niemand das Paket übernommen. | „Etikett erstellt“ |
| `货物电子信息已经收到` → `The electronic cargo information has been received` | Dieselbe Auftragsregistrierung im System von SXW (in Peek ins Englische übersetzt). | „Etikett erstellt“ |
| `到达收货点` → `Arrived at the receiving point` | Erster physischer Scan bei SXW an der Annahmestelle (in Peek auf Englisch angezeigt). | „Beim Paketdienst eingegangen“ |
| `Parcel picked up`, `Item has been collected` | Erster Scan der Versandlinie nach Übernahme des Pakets. | „Beim Paketdienst eingegangen“ |
| `货物离开操作中心` → `Goods leave the operation center` | Das Paket hat das Betriebszentrum von SXW verlassen (in Peek auf Englisch angezeigt). | „Unterwegs“ |
| `Parcel received at Shenzhen Sorting Centre.`, `Parcel processed at Shenzhen Sorting Centre.`, `Parcel departure in Shenzhen Sorting Centre.` | Eingang, Sortierung und Ausgang in einem Sortierzentrum in Shenzhen. | „Unterwegs“ |
| `Parcel departure in Transit Facility, Parcel Departure` | Weitertransport ab einem Umschlagzentrum unterwegs. | „Unterwegs“ |
| `Customs declaration completed`, `Clearence processing completed - Export`, `Released From Export Customs` | Ausfuhrzollanmeldung in **China** vor dem Abflug erledigt (teils `Clearence` geschrieben). Das Paket hängt nicht bei deinem Zoll fest. | „Unterwegs“ |
| `Parcel is leaving Airport, Parcel Leaving Port`, `International flight has departed` | Abflug vom Flughafen in China. | „Unterwegs“ |
| `Parcel arrives at Airport, Parcel Arrival`, `International flight has arrived` | Landung am Zielflughafen. | „Unterwegs“ |
| `Start customs clearence` | Beginn der Einfuhrverzollung im Zielland (so geschrieben: `clearence`). | „Beim Zoll“ |
| `Clearance processing completed - Import`, `Released From Import Customs` | Einfuhrverzollung abgeschlossen; die Sendung reist weiter. | „Unterwegs“ |
| `Returned parcel has been recieved by shipper` | Rücksendung wieder beim Absender in China eingetroffen (so geschrieben: `recieved`). | „Zurück an Absender“ |

Da zwei Systeme denselben Auftrag beschreiben, tauchen ähnliche Schritte oft doppelt auf (`货物电子信息已经收到` neben `Parcel Data Received`, oder `到达收货点` neben `Parcel picked up`). Auf manchen Linien übernimmt SXW zudem Meldungen des letzten Zustellers (etwa `Arrived at GOFO Regional Hub` in den USA, `Outward MC - Handheld Acceptance` von Royal Mail in Großbritannien oder `Onboard for delivery` und `Delivered - Left in a safe place` von Australia Post). Allgemeines dazu unter [Was Tracking-Status bedeuten](guide:tracking-statuses).

**Uhrzeiten.** Einträge aus China liest Peek als Pekinger Zeit (`Asia/Shanghai`, UTC+8) und rechnet sie in deine Geräte-Zeitzone um; ab der frühesten Zeile, deren Ortsangabe auf ein anderes Länderkürzel endet (z. B. `…, US`, `…, GB` oder `AU`), behält Peek die Ortszeit des Ziellands bei. Auf peektracker.com sowie in der iPhone- und Android-App erscheint unter dem Verlauf „Uhrzeiten in deiner Zeitzone: …“.

## Wer stellt SXW-Express-Pakete zu und welche Zollregeln gelten?

Laut eigener Website bietet SXW Express `DDU/DDP door-to-door delivery` und `local last-mile delivery` in Europa, Amerika, Südostasien, dem Nahen Osten und Afrika an. In der Produktliste stehen [UPS](carrier:ups)-Linien ab Hongkong (*Hong Kong UPS Direct Line Double Clearing With Tax Included - F*, *Hong Kong UPS-5000 (H)*), eine *ARAMEX Special Line*, ein *Middle East and Europe small package* und ein *US Express Mail Small Packet - F*; welcher Paketdienst in deinem Land klingelt, nennt die Website jedoch nicht.

**Was Peek automatisch erkennt.** Bei Paketen in die **USA** enthält der SXW-Eintrag mitunter eine Nummer von [GOFO Express](carrier:gofo) (`GFUS` + 14 Ziffern): Dann zeigt Peek „Zustellung durch GOFO Express“ (außer der Versandkanal nennt ausdrücklich einen anderen Dienst wie UPS), solange GOFO die Sendung ohne Scan führt, „Bei GOFO Express angekündigt, noch nicht eingetroffen“ und wechselt zu GOFO, sobald dort ein ebenso neuer oder neuerer Scan vorliegt. Genauso übergibt Peek an die [Schweizerische Post](carrier:swiss-post), wenn der Versandkanal im Datensatz die Schweizerische Post mit einer gültigen Schweizer Sendungsnummer für die **Schweiz** oder **Liechtenstein** nennt. Zustellversuche und Abholstellen richten sich nach dem jeweiligen Zusteller vor Ort: Frag den Verkäufer nach dessen Namen und Sendungsnummer, und siehe [Zustellung verpasst](guide:missed-delivery).

**Zoll in Deutschland, Österreich, der EU und der Schweiz.** In der **Europäischen Union** fällt seit dem 1. Juli 2026 für online außerhalb der EU gekaufte Waren in Sendungen mit einem Sachwert bis 150 € ein EU-Zollsatz von 3 € pro Position (*per item*) an; seit dem 1. November 2026 kommt unabhängig vom Wert eine EU-Bearbeitungsgebühr (*Union handling fee*) von 2 € pro Position hinzu (eine Position bündelt Waren desselben Pakets mit gleicher Zolltarifnummer, Beschreibung und – falls anzugeben – gleichem Ursprung; über 150 € gelten reguläre Zollsätze), zuzüglich Einfuhrumsatzsteuer in **Deutschland** und **Österreich**, sofern sie nicht schon beim Kauf über IOSS berechnet wurde. In der **Schweiz** erhebt das Bundesamt für Zoll und Grenzsicherheit (BAZG) 8,1 % Einfuhrsteuer (2,6 % ermäßigter Satz), wobei „Zollabgaben bis 5 Schweizerfranken nicht erhoben“ und „Steuerbeträge bis 5 Schweizerfranken nicht erhoben“ werden – zuzüglich möglicher Verzollungsgebühren des Zustellers. Kläre beim Händler, ob `DDP` (verzollt) oder `DDU` gebucht wurde, und lies [Paket hängt beim Zoll](guide:customs).

## SXW-Express-Paket kommt nicht an oder Tracking steht still

**Nur `Parcel Data Received` oder `The electronic cargo information has been received`.** Der Verkäufer hat den Auftrag angelegt, aber noch wurde kein Paket gescannt. Tut sich nach einigen Tagen nichts, frag beim Shop nach, ob die Ware wirklich übergeben wurde.

**„Warten auf SXW Express“.** Führt SXW den Auftrag noch ohne echte Scans (oder nur mit vorab eingetragenen Schritten) und liefern auch Tracking-Dienste nichts, bleibt Peek bei „Warten auf SXW Express“: In der iPhone- oder Android-App sowie im Konto fragt Peek nach den ersten Stunden alle 6 Stunden bei SXW nach; ohne Konto in den ersten 6 Stunden, danach beim Öffnen des Links (höchstens alle 6 Stunden, nach zwei Tagen höchstens täglich) und bei aktiven Mitteilungen täglich bis 30 Tage nach der Abfrage. Meldet die SXW-Seite hingegen, dass die Nummer dort gar nicht existiert, und findet auch kein Tracking-Dienst etwas, zeigt Peek nach zwei Tagen: „Auch nach zwei Tagen kennt der Paketdienst diese Nummer nicht. Prüfe sie, wähle einen anderen Paketdienst oder lösche das Paket.“

**`Customs declaration completed`.** Das ist die Ausfuhranmeldung in China vor dem Flug („Unterwegs“), keine Zollkontrolle im Empfängerland.

**Tagelang kein neuer Schritt, als zugestellt gemeldet oder Rücksendung.** Zeigt Peek eine Nummer von GOFO Express oder der Schweizerischen Post, prüfe deren Tracking; sonst orientiere dich am Lieferfenster des Shops und kontaktiere den Verkäufer nach Ablauf. Mehr unter [Warum sich das Tracking nicht aktualisiert](guide:tracking-not-updating), [Paket aus China verfolgen](guide:tracking-from-china), [Zugestellt, aber nicht erhalten](guide:delivered-not-received), [Paket verloren](guide:lost-parcel) und [Zurück an den Absender](guide:returned-to-sender).

## SXW Express Kundenservice und Kontakt

Die Website `sxwexpress.cn` nennt weder Telefonnummer noch E-Mail-Adresse oder Postanschrift: Die Seite *Contact US* zeigt lediglich die Überschrift und ein leeres Feld *Online Support*, und das Mitglieder-Login unter `sxwexpress.cn:8082` richtet sich an Geschäftskunden mit SXW-Konto (für die Sendungsverfolgung brauchst du keines).

Vertragspartner von SXW Express ist der Verkäufer: Wende dich bei Fragen zuerst an den Shop und eröffne einen Fall auf dem Marktplatz, falls keine Antwort kommt. Bei US-Zustellungen durch GOFO Express hilft GOFO zu Fragen rund um die Auslieferung selbst.

## SXW-Express-Pakete mit Peek verfolgen

[Peek](/) ordnet eine Nummer aus `SXW` und zwölf Ziffern sofort mit „Erkannter Paketdienst“ SXW Express zu, da kein anderer Dienst im Katalog dieses Muster nutzt (andere Formate prüft Peek gegen über 4.500 Paketdienste; manuell findest du SXW über „Ändern“ oder „Paketdienst ändern“ unter `SXW Express`, `Shengxingwang` oder `胜兴旺`).

Peek fragt direkt den offiziellen Tracker von SXW ab – ohne Konto, Postleitzahl oder Telefonnummer. Angezeigt werden Status, jeder echte Scan (mit den drei chinesischen Systemzeilen auf Englisch), der Ort sofern vorhanden, die Versandart und das Zielland; Namen, Adresse, Postleitzahlen, Stückzahl, Gewicht, Kundenreferenz, Zahlungsdaten, Unterzeichner, Unterschrift und Zustellnachweis lässt Peek weg. Da SXW kein Lieferdatum liefert, zeigt Peek keines an.

**Vorab eingetragene Schritte.** Peek hält jeden nach Pekinger Zeit in der Zukunft datierten Schritt zurück, ebenso Einträge aus simulierten Routen (`CO_C_CR`) sowie Zeilen, die mehr als 12 Stunden vor ihrer angezeigten Uhrzeit geschrieben wurden (oder mehr als 6 Stunden davor in derselben Sekunde wie eine solche Zeile): Das sind Planwerte, keine Scans. Dann beginnt der Verlauf bis zur Zustellung oder Rücksendung mit „Andere Seiten zeigen vielleicht mehr Schritte, aber das sind nur Vorhersagen.“, und „Anzeigen“ listet diese Schritte ausgegraut auf, jeden mit „Vorhergesagt für“ und seinem Tag. Diese Schritte ändern nie den Status und lösen keine Mitteilung aus. Ist der SXW-Tracker nicht erreichbar oder kennt die Nummer nicht, weicht Peek auf Tracking-Dienste aus.

Solange du das Paket geöffnet hast oder am Liefertag Mitteilungen aktiv sind, prüft Peek bis zu alle 10 Minuten – und ab 6 Uhr Ortszeit am Tag mit Status „In Zustellung“ bis zu alle 2 Minuten, solange der direkte SXW-Tracker antwortet (nach einer Übergabe an GOFO Express oder die Schweizerische Post folgt Peek dem Takt des Ziel-Paketdiensts; über Tracking-Dienste allein stündlich, alle 3 Stunden nach einem ruhigen Tag und am Liefertag bei geöffnetem Paket oder aktiven Mitteilungen bis zu alle 15 Minuten). Sonst sowie nach zwei Tagen ohne Bewegung (oder 12 Stunden auf „Etikett erstellt“ bzw. „Beim Paketdienst eingegangen“) fragt Peek stündlich ab. Zur Wahl stehen „Jeden Scan“, „Wichtige Schritte“ (Einfuhrzoll wie `Start customs clearence`, Probleme, Zustelltour, Fehlversuche, Abholung, Zustellung und Rücksendung – `Customs declaration completed` löst also keinen Alarm aus) oder „Nur die Zustellung“. SXW Express oder den Verkäufer kontaktieren kann Peek nicht.

## Häufige Fragen zur SXW-Express-Sendungsverfolgung

### Wer steckt hinter SXW Express?

Unter SXW Express führt Peek die `Shenzhen Shengxingwang Supply Chain Management Co., Ltd.` (`深圳胜兴旺供应链管理有限公司`, `sxwexpress.cn`), einen grenzüberschreitenden Logistikanbieter in Shenzhen für See- und Luftfracht, Bahnverkehr nach Europa, internationalen Express und DDU/DDP-Lieferungen.

### Wo kann ich ein SXW-Express-Paket direkt beim Anbieter prüfen?

Im Feld *Express Tracking* auf der Startseite von `sxwexpress.cn`: Nummer eingeben und auf *Track* klicken. Der offizielle Tracker öffnet sich auf Chinesisch und hat auch eine englische Seite; das 17TRACK-Logo daneben führt zu einem separaten Drittanbieter.

### Was bedeutet „Customs declaration completed“ bei SXW Express?

Dass die Ausfuhrzollanmeldung in China vor dem Abflug abgeschlossen ist. Peek stuft das als „Unterwegs“ ein – es handelt sich nicht um den Einfuhrzoll in deinem Land und das Paket wird nicht festgehalten.

### Welcher Paketdienst liefert SXW-Express-Sendungen bei mir aus?

Die SXW-Website nennt keine Länderliste. Für die USA kann der Datensatz eine Nummer von GOFO Express enthalten, der Peek nach dem ersten GOFO-Scan folgt (und für die Schweiz die Schweizerische Post, sofern im Kanal benannt); frag andernfalls den Verkäufer nach dem Zusteller und seiner Sendungsnummer.

### An wen wende ich mich, wenn mein SXW-Express-Paket fehlt?

Zuerst an den Verkäufer, bei dem du bestellt hast, denn `sxwexpress.cn` veröffentlicht weder Telefonnummer noch E-Mail-Adresse. Reagiert der Händler nicht, schalte den Käuferschutz des Marktplatzes ein.

:::sources
- [SXW Express: Startseite](http://www.sxwexpress.cn/) – Firmenname, Express-Tracking-Feld mit Track-Button, 17TRACK-Link und DDU/DDP-Beschreibung
- [SXW Express: About US](http://www.sxwexpress.cn/about.html) – Sitz in Shenzhen, See-, Luft-, Bahn- und Expresslogistik, DDU/DDP und Zustellung auf der letzten Meile
- [SXW Express: Products](http://www.sxwexpress.cn/products/default.html) – Hongkong-UPS-Linien, ARAMEX Special Line, Kleinpakete Europa/Nahost und USA
- [SXW Express: Contact US](http://www.sxwexpress.cn/contact.html) – Kontaktseite ohne Telefon, E-Mail oder Anschrift und leeres Online-Support-Feld
- [SXW Express: 会员登录 (Mitglieder-Login)](http://www.sxwexpress.cn:8082/) – chinesischer Firmenname auf dem Tracking-System
- [SXW Express: trace (englischer Tracker)](http://www.sxwexpress.cn:8082/en/trackIndex.htm) – englische Seite des SXW-Trackers
- [Europäische Kommission: EU-Zollreform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 3 € Zollsatz seit 1. Juli 2026 und 2 € Bearbeitungsgebühr seit 1. November 2026
- [Europäische Kommission: Leitlinien zum 3-€-Zoll und zur Bearbeitungsgebühr (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – Erhebung pro Position
- [BAZG: Empfangen von Briefen und Paketen](https://www.bazg.admin.ch/de/empfangen-von-briefen-und-paketen) – Schweizer Einfuhrsteuer und Nichterhebung bis 5 Franken
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – SXW-Format, englische Übersetzung chinesischer Zeilen, Zeitzonen, vorab eingetragene Schritte sowie GOFO- und Schweizerische-Post-Übergabe
:::
