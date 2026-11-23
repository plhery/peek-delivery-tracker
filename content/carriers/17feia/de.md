---
title: 17FEIA Sendungsverfolgung: FEI…YQ-Nummern und Status
description: 17FEIA-Paket (一起飞) aus China verfolgen: Aufbau der FEI…YQ-Nummer, Bedeutung der Statuszeilen, Zusteller vor Ort, Zoll und Anzeige in Peek.
slug: 17feia-sendungsverfolgung
published: 2026-11-21
updated: 2026-11-21
---

17FEIA (一起飞, auch Shanghai Hongshan International Logistics, 上海宏杉国际物流) ist ein chinesisches Logistikunternehmen. Es nimmt Pakete in seinem Lager in Shenzhen oder Shanghai entgegen, befördert sie aus China heraus und übergibt sie nach dem Einfuhrzoll an einen Paketdienst für die Zustellung. Die Sendungsnummern beginnen mit `FEI` und enden auf `YQ`. Füge deine Nummer oben ein oder nutze den eigenen Tracker von 17FEIA unter www.17feia.com. Die Nummer kommt von demjenigen, der die Sendung verschickt hat: dem Verkäufer oder dem Weiterleitungsdienst aus China.

## 17FEIA-Sendungsnummer: So sieht sie aus

Peek erkennt 17FEIA-Nummern am Aufbau `FEI`, zwei Buchstaben, zehn Ziffern und `YQ`, insgesamt 17 Zeichen. Bei Abgängen aus Shenzhen lauten die zwei Buchstaben nach `FEI` `SZ`. Alle Beispiele sind erfunden:

| Nummer (erfunden) | Was es ist |
| --- | --- |
| `FEISZ4829105736YQ` | `FEI`, zwei Buchstaben, zehn Ziffern und `YQ`: eine 17FEIA-Nummer. Peek erkennt 17FEIA allein an der Nummer. |
| `FEISZ482910573YQ` | Nur neun Ziffern: passt nicht zum 17FEIA-Format. Prüfe auf eine fehlende Ziffer. |
| `FEISZ4829105736AB` | Zwei andere Buchstaben als `YQ` am Ende. Peek nennt 17FEIA nicht allein anhand der Form, fragt aber beim 17FEIA-Tracker nach. |
| `HHWKG4829105736YQ` | Endet auf `YQ`, beginnt aber mit `HHW`: eine Nummer von [HuaHan Logistics](carrier:huahan). |

In Peek spielen Kleinbuchstaben, Leerzeichen und Bindestriche keine Rolle.

**Wo du sie findest.** In der Versandmail, auf deiner Bestellseite oder im Konto deines Weiterleitungsdienstes, falls du einen genutzt hast. Sonst hilft [Sendungsnummer finden](guide:find-tracking-number). Später kann der Datensatz von 17FEIA Peek auch die eigene Nummer des ausliefernden Paketdienstes liefern.

## 17FEIA-Statusmeldungen erklärt

Der Datensatz von 17FEIA versieht die meisten Einträge mit einem kurzen chinesischen Etikett und einer Textzeile, die neueste zuerst. Peek zeigt den englischen Text und liest die Stufe aus dem chinesischen Etikett. Die eigenen Zeilen von 17FEIA in typischer Reihenfolge:

| Meldung von 17FEIA | Was das für dich bedeutet | Peek zeigt |
| --- | --- | --- |
| `Item information received.` | 17FEIA hat die Sendungsdaten erhalten, noch nicht das Paket. | „Etikett erstellt“ |
| `The package is on the way to the facility.` (`包裹在去设施路上`) | Auf dem Weg zum Lager von 17FEIA, das es noch nicht hat. | „Etikett erstellt“ |
| `Warehouse receipt.` (`仓库收货`) | Das Lager von 17FEIA in Shenzhen oder Shanghai hat das Paket erhalten. | „Beim Paketdienst eingegangen“ |
| `The order has information pending customer confirmation.` (`订单待客户确认`) | Das Lager wartet auf die Bestätigung durch den Auftraggeber von 17FEIA. | „Beim Paketdienst eingegangen“ |
| `Order Confirmed.` (`订单已确认`) | Der Auftrag ist bestätigt. | „Beim Paketdienst eingegangen“ |
| `Order dispatched from warehouse.` (`订单已出库`) | Das Paket hat das Lager verlassen. | „Unterwegs“ |
| `Export declaration in progress.` (`出口报关中`) | Das Paket wird in China zur Ausfuhr angemeldet – ein normaler Schritt vor dem Flug. Es wird nicht vom Zoll festgehalten und es ist nicht der Zoll deines Landes. | „Unterwegs“ |
| `Flight has departed.` (`航班已起飞`) | Der Flug hat China verlassen. | „Unterwegs“ |
| `Flight Arrived at Transit Hub.`, `Flight Departed from Transit Hub.` | Zwischenstopp unterwegs, dann der Weiterflug. | „Unterwegs“ |
| `Flight Arrived at Clearance Port.` (`航班抵达清关港`) | Gelandet an dem Ort, an dem das Paket durch den Einfuhrzoll geht. | „Unterwegs“ |
| `Broker picked up Shipment and delivered to warehouse.` (`清关行提货到仓`) | Ein Zollagent hat die Sendung zur Abfertigung in sein Lager geholt (oder nach der Freigabe übernommen). Keine Zustellung an dich. | „Beim Zoll“ (oder „Unterwegs“, wenn es nach `清关完成` erscheint) |
| `Customs Clearance is in progress.` (`清关中`) | Der Einfuhrzoll prüft die Sendung. | „Beim Zoll“ |
| `Customs inspection in Progress, Release Pending.` (`海关查验，暂未放行`) | Nach der Landung am Abfertigungsort überprüft der Einfuhrzoll die Sendung und hat sie noch nicht freigegeben. | „Beim Zoll“ |
| `Customs Clearance Completed.` (`清关完成`) | Der Einfuhrzoll hat das Paket freigegeben. | „Unterwegs“ |
| `Arrived at delivery hub，Awaiting carrie scan.` (`已交仓，等待上网`) | Im Depot des ausliefernden Paketdienstes abgegeben. Als Nächstes folgt dessen erster Scan („carrie“ steht so im Tracker). | „Unterwegs“ |
| `包裹已做国内退回处理` (`国内退回`) | Inlandsrücksendung in China: Nach der Auftragsbestätigung im Lager geht das Paket an den Absender in China zurück, statt das Land zu verlassen. | „Zurück an Absender“ |

**Zwei Zeilen ohne Statuswechsel.** `Estimated departure date:` mit einem Datum (`预计起飞日期`) ist eine Planung vor dem Flug, kein Scan: Peek lässt sie weg. `Flight delayed.` (`航班延误`) meldet eine Flugverspätung: Peek behält den Status der vorherigen Zeile bei.

**Der Fortschrittsbalken.** Die Tracking-Seite von 17FEIA zeigt außerdem einen Balken von `仓库收货` (Lagereingang) über `飞机起飞` (Abflug) und `飞机落地` (Landung) bis `成功签收` (unterschrieben/zugestellt). Peek übernimmt den Balken nicht, führt eine Zeile mit `成功签收` aber als „Zugestellt“.

**Zeilen des ausliefernden Paketdienstes.** Nach der Übergabe kann der 17FEIA-Tracker die Scans des Zustellers unter dem Etikett `运输途中` (unterwegs) in dessen Worten und mit dessen Ort weitergeben – etwa DHL (`The international shipment has been processed in the export parcel center`), UPS in den USA (`Arrived at Facility`, `Departed from Facility`, `On the Way, Delivery will be delayed by one business day.`) oder einen britischen Partner (`Shipment received in depot`). Peek wertet sie anhand ihres Wortlauts aus: Ein Depot-Eingang bleibt „Unterwegs“, während Zustellung oder Auslieferung den Status weitersetzen.

**Warum Uhrzeiten durcheinander wirken.** Die Lagerschritte in China (samt Inlandsrücksendung) laufen nach Pekinger Zeit (UTC+8). Weitergeleitete Scans des Zustellers tragen ihre Ortszeit, sofern der Ort eine eindeutige Zeitzone festlegt. Flug- und Zollzeilen nennen keinen Ort, und der von 17FEIA angezeigte Versatz passt nicht immer dazu: Orientiere dich an der Reihenfolge der Einträge.

## Wer 17FEIA-Pakete zustellt

Nach dem Einfuhrzoll übergibt 17FEIA das Paket an einen Paketdienst, der es zu dir bringt. Da Peek die Website von 17FEIA nicht abrufen konnte, enthält diese Seite keine Liste aller Partner oder Liefergebiete.

**Was der Peek-Code zeigt.** Peek kennt drei Partnernamen von 17FEIA: `DPD-DE` für [DPD Germany](carrier:dpd-de), `DE-DHL` für [DHL](carrier:dhl) in Deutschland und `FEDEX-GROUND` für [FedEx](carrier:fedex). Im Datensatz erkennt Peek fünf Zielländer: Deutschland, Frankreich, die Niederlande, Polen und die USA. Ein Verlauf im Code zeigt DHL in Deutschland bei der Übernahme eines Pakets nach Frankreich, um es an den dortigen Zusteller weiterzugeben.

**Verpasste Zustellung.** Zustellversuche und Abholung richten sich nach den Regeln des ausliefernden Dienstes: Frage mit seiner eigenen Sendungsnummer dort nach. Siehe [verpasste Zustellung](guide:missed-delivery).

**Zoll in der EU.** Online gekaufte Waren aus Ländern außerhalb der EU unterliegen seit dem 1. Juli 2026 einem vorübergehenden Zoll von 3 € pro Artikel auf Sendungen bis 150 € Warenwert sowie seit dem 1. November 2026 einer EU-Bearbeitungsgebühr von 2 € pro Artikel unabhängig vom Wert. Als ein Artikel gelten Waren mit derselben Zolltarifnummer, derselben Beschreibung und – soweit erforderlich – demselben Ursprung. Peek konnte nicht prüfen, wie diese Beträge bei einem 17FEIA-Paket erhoben werden: Frage den Verkäufer oder deinen Weiterleitungsdienst, ob sie im Kaufpreis enthalten waren. Außerhalb der EU gelten die jeweiligen Landesregeln. Solange der Einfuhrzoll prüft und nicht freigibt, zeigt 17FEIA `Customs inspection in Progress, Release Pending.` Siehe [Paket beim Zoll](guide:customs) und vor einer Zahlung nach SMS oder E-Mail [gefälschte Paket-SMS](guide:delivery-scam-texts).

## 17FEIA-Paket aktualisiert sich nicht, verloren oder zurückgeschickt

17FEIA arbeitet im Auftrag dessen, der den Versand gebucht hat: des Verkäufers oder deines Weiterleitungsdienstes. Bei Verspätung, Verlust, Schaden oder Rücksendung wende dich zuerst an ihn.

**Nummer nicht gefunden.** Bei einer unbekannten Nummer zeigt der Tracker von 17FEIA keinen Fortschritt. Peek zeigt „Warten auf 17FEIA“ und fragt weiter nach. Kennt 17FEIA die Nummer zwei Tage später noch immer nicht und hat auch keine andere Quelle geantwortet, zeigt Peek „Auch nach zwei Tagen kennt der Paketdienst diese Nummer nicht. Prüfe sie, wähle einen anderen Paketdienst oder lösche das Paket.“ Vergleiche die Nummer mit der Versandnachricht: `FEI`, zwei Buchstaben, zehn Ziffern, `YQ`.

**Steht auf `The order has information pending customer confirmation.`** Wenn du einen Weiterleitungsdienst genutzt hast, prüfe dort dein Konto oder deine Nachrichten. Sonst melde dich beim Verkäufer.

**Still nach dem Abflug.** Nach `Flight has departed.` kann tagelang nichts Neues erscheinen. Nach vier Tagen ohne neuen Eintrag warnt Peek mit dem Tag dieses Eintrags („Keine Aktualisierung seit …“). Siehe [Sendungsverfolgung aktualisiert sich nicht](guide:tracking-not-updating) und [Pakete aus China verfolgen](guide:tracking-from-china).

**Zugestellt, aber nichts angekommen.** Schau rund ums Haus und frage Nachbarn, danach den ausliefernden Paketdienst und den Verkäufer. Siehe [Paket zugestellt, aber nicht erhalten](guide:delivered-not-received).

**Verloren oder zurückgeschickt.** Bitte den Verkäufer oder Weiterleitungsdienst um Erstattung oder Ersatz. Siehe [verlorenes Paket](guide:lost-parcel) und [zurück an Absender](guide:returned-to-sender).

## 17FEIA-Kontakt und Kundenservice

Die Website von 17FEIA antwortete Peek mit einer Bot-Prüfung, sodass diese Seite keine Kontaktdaten für 17FEIA angibt. Bei Fragen zur Bestellung wende dich an den Verkäufer oder Weiterleitungsdienst, der die Sendung beauftragt hat – sie sind Kunden von 17FEIA. Für die Zustellung selbst kontaktiere den ausliefernden Paketdienst.

## Ein 17FEIA-Paket mit Peek verfolgen

[Peek](/) erkennt 17FEIA sofort an einer `FEI…YQ`-Nummer („Erkannter Paketdienst“): Kein anderer Paketdienst in Peek verwendet dieses Format. Auch ein Link zur 17FEIA-Tracking-Seite mit der Nummer hinter `orderNumber=` funktioniert. Postleitzahl oder Konto sind nicht nötig.

Jede andere Nummer, etwa die des lokalen Zustellers, kannst du ebenso einfügen: Peek verfolgt über 4500 Paketdienste. Passt das Format zu mehreren Diensten ohne Eindeutigkeit, zeigt Peek „Tracking-Dienste werden geprüft…“, während es nachfragt; passt es zu keinem, prüft Peek die Nummer nach dem Hinzufügen. Um den Dienst selbst zu wählen, tippe auf „Ändern“ und suche nach `17FEIA`, `Feia` oder `一起飞`, oder nutze „Paketdienst ändern“ bei einem bereits hinzugefügten Paket. Peek fragt 17FEIA nur nach Nummern aus `FEI`, zwei Buchstaben, zehn Ziffern und zwei Buchstaben: Bei jedem anderen Format meldet die Zeile, 17FEIA „vergibt keine Nummern wie diese“.

Peek zeigt den Status, die Zeilen im Englisch von 17FEIA samt Ort (wo angegeben) und das Zielland. Lagerschritte liest es nach Pekinger Zeit und weitergeleitete Scans aus Ländern mit einer Zeitzone (oder einzelnen US-Bundesstaaten mit einer Zeitzone) nach deren Ortszeit, umgerechnet in die Zeitzone deines Geräts. Flug- und Zollzeilen nennen keinen Ort: Peek liest sie auf der Uhr der Zeilen daneben, nach Pekinger Zeit, bis eine Zeile die Ankunft des Flugs meldet, danach nach der Uhr des ausliefernden Paketdienstes, wenn 17FEIA dessen Scans weitergibt, sonst nach der deines Landes. Diese Zeiten können ein paar Stunden abweichen. Fortschrittsbalken, Herkunft, Postleitzahlen und Gewicht lässt Peek weg. 17FEIA liefert weder Liefertermin noch Servicenamen, Absender- oder Empfängerdaten oder Zustellnachweis.

Nennt 17FEIA DPD Germany, DHL oder FedEx mit einer Nummer im Format dieses Paketdienstes, zeigt die Paketkarte das an (etwa „Zustellung durch DPD Germany“) und führt diese Nummer hinter der von 17FEIA auf. Solange dieser Paketdienst nur die Ankündigung ohne eigenen Scan hat, steht dort „Bei … angekündigt, noch nicht eingetroffen“. Sobald er einen Scan zeigt, der mindestens so aktuell ist wie der letzte von 17FEIA, verfolgt Peek das Paket dort weiter und behält den 17FEIA-Verlauf. Ist der 17FEIA-Tracker nicht erreichbar, fragt Peek stattdessen Tracking-Dienste ab und zeigt deren Verlauf in deren Worten.

Solange du das Paket geöffnet hast oder am Zustelltag mit aktivierten Mitteilungen, prüft Peek bei erreichbarem 17FEIA-Tracker bis zu alle 10 Minuten, und ab 6 Uhr in der Zeitzone des Paketdienstes am Tag der Zustellung bis zu alle 2 Minuten; sonst stündlich. Über Tracking-Dienste prüft Peek stündlich, am Zustelltag bis zu alle 15 Minuten, solange du es geöffnet hast oder Mitteilungen aktiv sind. Nach zwei Tagen ohne Neuigkeiten (nach 12 Stunden bei „Etikett erstellt“ oder „Beim Paketdienst eingegangen“) prüft Peek stündlich. Mitteilungen gibt es für „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“; „Wichtige Schritte“ schließt den Einfuhrzoll am Abfertigungsort ein, nicht aber die Ausfuhranmeldung in China. Peek kann weder 17FEIA noch den Verkäufer oder deinen Weiterleitungsdienst für dich kontaktieren.

## Fragen zur 17FEIA-Sendungsverfolgung

### Was ist 17FEIA (一起飞)?

Ein chinesisches Logistikunternehmen, auch bekannt als Shanghai Hongshan International Logistics (上海宏杉国际物流). Es verschickt Pakete aus seinem Lager in Shenzhen oder Shanghai ins Ausland und übergibt sie nach dem Einfuhrzoll an einen Paketdienst zur Zustellung. Seine Nummern beginnen mit `FEI` und enden auf `YQ`.

### Wo kann ich eine FEI…YQ-Nummer verfolgen?

Oben im Eingabefeld oder direkt beim Tracker von 17FEIA auf www.17feia.com. Gib die vollständige Nummer ein: `FEI`, zwei Buchstaben, zehn Ziffern und `YQ`.

### Was bedeutet „Awaiting carrie scan“ bei 17FEIA?

Die volle Zeile lautet `Arrived at delivery hub，Awaiting carrie scan.`: Der Einfuhrzoll ist abgeschlossen und 17FEIA hat das Paket im Depot des ausliefernden Paketdienstes abgegeben. Dieser hat es noch nicht gescannt; sein eigenes Tracking kann bald weiter sein als das von 17FEIA.

### Wer stellt 17FEIA-Pakete zu?

Der Paketdienst, an den 17FEIA das Paket nach dem Einfuhrzoll übergibt. Der Datensatz von 17FEIA kann Namen und Nummer nennen: Bei DPD Germany, DHL in Deutschland oder FedEx zeigt Peek das auf der Paketkarte an. Andernfalls frage den Verkäufer.

:::sources
- [Universal Parcel Scraper: 17FEIA](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/17feia) – Namen, `FEI…YQ`-Format und Abgänge aus Shenzhen (`SZ`), Tracker und `orderNumber=`-Link, chinesische Etiketten, englische Texte und Stufen (samt Zollprüfung, `国内退回`, Stufenwechsel von `清关行提货到仓` nach `清关完成` sowie weitergeleiteten DHL-/UPS-/UK-Depot-Zeilen), Fortschrittsbalken, Zeitzonenlogik, Partner (`DPD-DE`, `DE-DHL`, `FEDEX-GROUND`) und Zielländer
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – andere `YQ`-Formate (`HuaHan Logistics`), Tracking-Dienste als Rückfall und Übergabe an den Zusteller
- [Europäische Kommission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – vorübergehender Zoll von 3 € pro Artikel bis 150 € seit 1. Juli 2026, EU-Bearbeitungsgebühr von 2 € seit 1. November 2026
- [Europäische Kommission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 2 € Bearbeitungsgebühr pro Artikel unabhängig vom Wert, Begriff des Artikels
:::
