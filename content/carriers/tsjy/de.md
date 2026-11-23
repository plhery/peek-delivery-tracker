---
title: TSJY Sendungsverfolgung (通世集运): Nummern und Status
description: Sammelpaket mit TSJY (通世集运) aus China verfolgen: wie eine TSJY…CN-Nummer aussieht, was die chinesischen Status bedeuten und wann 17FEIA übernimmt.
slug: tsjy-sendungsverfolgung
published: 2026-11-23
updated: 2026-11-23
---

TSJY (通世集运) ist ein Konsolidierungsdienst in China, betrieben von 苏州通世供应链有限公司, einem Logistikunternehmen in Suzhou. Du kaufst auf chinesischen Plattformen wie Taobao, Tmall, Pinduoduo, JD oder Douyin ein, und die Shops schicken deine Ware an TSJYs Lager in Suzhou. TSJY lagert sie ein, packt sie zusammen und verschickt sie ins Ausland. Verfolge deine TSJY-Nummer auf der [TSJY-Website](https://www.tsjy56.com/) unter 订单查询 (Bestellsuche) oder füge sie oben in das Feld ein.

## TSJY-Sendungsnummer: So sieht sie aus

TSJYs Website beschreibt das Nummernformat nicht, doch es folgt einem festen Muster. TSJY sind die Initialen von 通世集运, Tongshi Jiyun. Alle Beispiele unten sind erfunden:

| Nummer | Was sie ist |
| --- | --- |
| `TSJY482917365CN` | TSJY, neun Ziffern und CN: eine TSJY-Auftragsnummer. |
| `TSJY48291736CN` | Nur acht Ziffern: kein TSJY-Format, und Peek erkennt TSJY nicht. Prüfe, ob eine Ziffer fehlt. |
| `FEIKM4829173650YQ` | FEI, zwei Buchstaben, zehn Ziffern und YQ: eine [17FEIA](carrier:17feia)-Nummer der Versandlinie, die manche TSJY-Sendungen ins Ausland bringt. |

In Peek spielen Kleinbuchstaben, Leerzeichen und Bindestriche keine Rolle.

**Wo du sie findest.** Es ist die Nummer deines Versandauftrags (订单号), den du auf TSJYs Web-Bestellseite (网页下单) oder in WeChat (微信下单) aufgibst, sobald deine Waren im Lager eingetroffen sind. Hat jemand anderes den Versand gebucht, frag dort nach.

**Zwei Nummern.** TSJYs Suchfeld akzeptiert auch die Nummer der Versandlinie (运单号). Auf dem Smartphone zeigt jede Ergebniskarte die TSJY-Nummer und nach einem Schrägstrich die Nummer der Linie. Tippe auf die Karte, um die Tracking-Zeilen (轨迹详情) zu öffnen.

## TSJY-Sendungsstatus einfach erklärt

TSJYs Tracker ist auf Chinesisch. Auf dem Smartphone zeigt jede Ergebniskarte einen Kurzstatus für den gesamten Auftrag:

| Die Karte meldet | Was das für dich bedeutet |
| --- | --- |
| `等待处理` | Wartet auf Bearbeitung: TSJY hat deinen Versandauftrag erhalten. |
| `开始拣货`, `正在拣货`, `拣货完成`, `开始打包`, `已称重`, `已录单` | Das Lager kommissioniert deine Artikel, verpackt und wiegt das Paket und erfasst den Auftrag. |
| `等待出库`, `已出库`, `运输途中` | Wartet auf Warenausgang; hat das Lager verlassen; unterwegs. |
| `已送达`, `已完成` | Zugestellt; abgeschlossen. |
| `运输异常` | Problem unterwegs. Frag bei TSJY nach. |

Peek liest nicht diesen Kartenstatus, sondern die einzelnen Tracking-Zeilen (轨迹) dahinter. Zuerst kommen TSJYs eigene Schritte im Lager auf Chinesisch:

| TSJY meldet | Was das für dich bedeutet | Peek zeigt |
| --- | --- | --- |
| `订单已提交，等待系统确认` | Auftrag übermittelt, wartet auf Systembestätigung. Noch wurde nichts kommissioniert. | „Etikett erstellt“ |
| `仓库开始拣货` | Das Lager beginnt, deine Artikel zusammenzusuchen. | „Beim Paketdienst eingegangen“ |
| `仓库拣货完成` | Kommissionierung abgeschlossen. | „Beim Paketdienst eingegangen“ |
| `订单打包完成，等待出库` | Verpackt, wartet auf Warenausgang. Kann innerhalb einer Minute zweimal erscheinen. | „Beim Paketdienst eingegangen“ |
| `订单已出库，开始国际运输` | Hat TSJYs Lager verlassen, der internationale Versand beginnt. | „Unterwegs“ |

Auf der 17FEIA-Linie übernimmt TSJY anschließend 17FEIAs Einträge auf Englisch:

| Die Linie meldet | Was das für dich bedeutet | Peek zeigt |
| --- | --- | --- |
| `Item information received.` | 17FEIA hat die Auftragsdaten erhalten. Das Paket kann noch bei TSJY sein. | „Beim Paketdienst eingegangen“ |
| `The package is on the way to the facility.`, `Warehouse receipt.` | Auf dem Weg zu 17FEIAs Lager in China, danach dort eingegangen. | „Unterwegs“ |
| `The order has information pending customer confirmation.`, `Order Confirmed.` | 17FEIAs Lager hält das Paket zurück, bis der Auftrag bestätigt ist. | „Unterwegs“ |
| `Order dispatched from warehouse.` | Hat 17FEIAs Lager verlassen. | „Unterwegs“ |
| `Export declaration in progress.` | Ausfuhranmeldung in China läuft – ein Routineschritt vor dem Flug. Der Zoll hält es nicht fest, und es ist nicht dein Zoll. | „Unterwegs“ |
| `Flight has departed.` | Abgeflogen. | „Unterwegs“ |
| `Flight delayed.` | Der Flug hat Verspätung. Das Paket hat sich nicht bewegt. | Status der vorherigen Zeile |
| `Flight Arrived at Transit Hub.`, `Flight Departed from Transit Hub.` | Zwischenstopp unterwegs. | „Unterwegs“ |
| `Flight Arrived at Clearance Port.` | Gelandet, wo der Einfuhrzoll abfertigt. | „Unterwegs“ |
| `Broker picked up Shipment and delivered to warehouse.` | Ein Zollagent hat es zur Verzollung in sein Lager gebracht – keine Zustellung an dich. | „Beim Zoll“ |
| `Customs Clearance is in progress.` | Der Einfuhrzoll prüft es. | „Beim Zoll“ |
| `Customs Clearance Completed.` | Vom Zoll freigegeben. | „Unterwegs“ |

Auf einer anderen Versandlinie kann auch deren Buchung erscheinen: `已下单` (Auftrag aufgegeben), auf Chinesisch und ohne Ort, zwischen TSJYs Verpacken und Warenausgang. Wie 17FEIAs `Item information received.` erscheint es, solange TSJY das Paket noch hat: „Beim Paketdienst eingegangen“. Nach TSJYs Warenausgang bedeutet `已出库` (aus dem Lager ausgebucht), ebenfalls auf Chinesisch ohne Ort, dass das Lager dieser Linie das Paket am Tag nach Annahme weitergeleitet hat: „Unterwegs“.

Peek lässt 17FEIAs Zeile mit einem voraussichtlichen Abflugdatum weg – das ist kein Scan – sowie alle in der Zukunft datierten Einträge. Weitere Zeilen, etwa vom örtlichen Zusteller, ordnet Peek anhand ihrer Stichwörter wie zugestellt oder Zoll ein.

**Zeiten.** TSJYs Detailseite zeigt jede Zeit in der Uhrzeit deines Geräts an, und Peek zeigt TSJYs eigene Schritte genauso an. 17FEIAs Lagerzeilen liest Peek in Pekinger Zeit (UTC+8), wie 17FEIA sie einträgt. Endet der Ort einer späteren Zeile auf ein Länderkürzel wie `(DE)`, liest Peek die Zeit in der Zeitzone dieses Landes, sofern das Land nur eine Zeitzone hat.

## Wer TSJY-Pakete zustellt

TSJYs Website gibt an, in über 220 Länder und Regionen zu liefern, per Express-Luftfracht (加急空运) oder Economy-Seefracht (经济海运). Sie nennt nicht, wer in welchem Land zustellt, und nennt keine Laufzeiten. Auf der Detailseite steht die Versandlinie (渠道, Kanal), über die der Auftrag läuft.

Eine Linie kennt Peek namentlich: [17FEIA](carrier:17feia) (一起飞), ein Logistikunternehmen in China mit eigenem Tracker unter www.17feia.com. Das Tracking von 17FEIA kann den Zusteller in deinem Land samt dessen Nummer nennen.

TSJY veröffentlicht keine Regeln für Zustellversuche oder Abholpunkte: Dafür gelten die Regeln des zustellenden Dienstes. Siehe [verpasste Zustellungen](guide:missed-delivery).

**Zoll in der EU.** Online gekaufte Waren, die von außerhalb in die EU gelangen, tragen seit dem 1. Juli 2026 einen vorübergehenden EU-Zoll von 3 € pro Artikel auf Pakete bis 150 € sowie seit dem 1. November 2026 eine Unions-Bearbeitungsgebühr von 2 € pro Artikel unabhängig vom Wert. Als ein Artikel gelten Waren mit derselben zolltariflichen Einreihung, Beschreibung und – sofern erforderlich – demselben Ursprung. Beides gilt für Fernverkäufe: Waren, die vom Verkäufer oder in dessen Auftrag versandt werden. Laut Leitfaden der Kommission werden Waren nicht vom Verkäufer versandt, wenn der Kunde den Transport ohne Beteiligung des Verkäufers selbst organisiert – was zutreffen kann, wenn du TSJY selbst buchst; darüber entscheidet dein Zollamt. TSJYs Website macht keine Angaben zur Zahlung von Zöllen oder Gebühren. Siehe [Pakete beim Zoll](guide:customs).

## TSJY-Paket ohne Update, verloren oder zurückgesandt

Bei TSJY bist normalerweise du selbst der Auftraggeber, denn du hast den Versand gebucht. Wende dich bei Verspätung, Verlust oder Schaden daher zuerst an TSJY.

**Waren noch nicht im Lager.** Bevor deine Artikel bei TSJY eintreffen, laufen sie mit den chinesischen Paketdiensten der Shops: Verfolge sie dort, wo du eingekauft hast. Laut TSJYs Website beginnt das Tracking, sobald ein Paket im Lager eingeht, und läuft bis zur Quittierung.

**Kein Treffer für die Nummer.** Kennt TSJY eine Nummer nicht, zeigt die mobile Ergebnisseite `未找到相关的轨迹信息。` (keine Tracking-Informationen gefunden). Prüfe auf Tippfehler. Das Paket zeigt „Warten auf TSJY“ und Peek prüft weiter. Kennt TSJY die Nummer zwei Tage später immer noch nicht und hat kein anderer Dienst geantwortet, zeigt Peek „Auch nach zwei Tagen kennt der Paketdienst diese Nummer nicht. Prüfe sie, wähle einen anderen Paketdienst oder lösche das Paket.“ und prüft ab dann täglich, und nach einer Woche ab dem Hinzufügen wöchentlich.

**Im Lager stecken geblieben.** Stehen TSJYs eigene Schritte mehrere Tage still, frag den Kundenservice.

**Still nach dem Abflug.** Nach `Flight has departed.` gibt es oft bis zur Landung oder Verzollung keine neue Zeile. Bleibt ein Paket im Status „Etikett erstellt“, „Beim Paketdienst eingegangen“ oder „Unterwegs“ vier Tage ohne neuen Scan, meldet Peek „Seit vier Tagen keine Aktualisierung“ (oder „Keine Aktualisierung seit …“), erklärt: „Auf langen Flügen, beim Zoll oder übers Wochenende wird es oft still um ein Paket. Peek fragt weiter bei TSJY nach.“ und bietet „Hol dir Hilfe bei TSJY“ an. Siehe [warum die Sendungsverfolgung stehen bleibt](guide:tracking-not-updating) und [Pakete aus China verfolgen](guide:tracking-from-china).

**Verloren oder beschädigt.** TSJYs Website verspricht volle Erstattung bei Verlust oder Beschädigung (丢破必赔), nennt öffentlich aber keine Regeln oder Fristen: Kontaktiere den Kundenservice sofort und bewahre Karton samt Inhalt auf. Siehe [verlorene Pakete](guide:lost-parcel) und [beschädigte Pakete](guide:damaged-parcel).

**Zugestellt, aber nicht da, oder zurückgesandt.** Schau rund ums Haus nach, frag Nachbarn und melde es TSJY. Zu Rücksendungen macht die Website keine Angaben. Siehe [Paket als zugestellt markiert, aber nicht erhalten](guide:delivered-not-received) und [zurück an den Absender](guide:returned-to-sender).

## TSJY Kundenservice und Kontakt

Angaben auf der [TSJY-Startseite](https://www.tsjy56.com/) im Bereich 客服中心 (Kundenservice):

- **Telefon und E-Mail:** eine Kundenservice-Telefonnummer (客户服务电话) und eine E-Mail-Adresse (客服邮箱). Öffnungszeiten werden nicht genannt.
- **WeChat:** ein QR-Code zum Hinzufügen des WeChat-Kundenservice (添加微信客服) sowie Service-Account und Mini-Programm.
- **Lager:** in Huaqiao (花桥镇), Suzhou, mit vollständiger Anschrift auf der Seite.

Die Website ist nur auf Chinesisch und erwähnt keinen Support auf Englisch.

## Ein TSJY-Paket mit Peek verfolgen

[Peek](/) erkennt TSJY sofort an einer `TSJY…CN`-Nummer („Erkannter Paketdienst“): Kein anderer von Peek unterstützter Dienst nutzt diese Form. Auch ein Link zur TSJY-Ergebnisseite mit einer TSJY-Nummer nach `nos=` funktioniert; enthält er mehrere Nummern, nimmt Peek die erste. Ein TSJY-Link mit der Nummer der Versandlinie oder einer Nummer ohne TSJY-Format wird nicht unter TSJY gespeichert: Peek liest jene Nummer eigenständig, so als hättest du sie direkt eingefügt.

Jede andere Nummer fügst du trotzdem einfach ein: Peek verfolgt mehr als 4.500 Paketdienste. Passt das Format zu mehreren Diensten, zeigt Peek beim Prüfen „Tracking-Dienste werden geprüft…“; passt es zu keinem, schlägt Peek die Nummer nach dem Hinzufügen nach. Nennt Peek keinen Paketdienst, tippe auf „Ändern“ und wähle ihn unter „Alle Paketdienste“, oder nutze später „Paketdienst ändern“. Wählst du TSJY für eine andere Nummer, meldet Peek, dass TSJY „vergibt keine Nummern wie diese“.

Peek liest TSJYs Tracker aus: jede Zeile mit Ort und Uhrzeit. Herkunft, Zielland und Name der Linie bleiben weg. TSJY nennt kein Lieferdatum, kein Gewicht, keinen Abholcode und keinen Unterzeichner. Peeks Status folgt der neuesten Zeile, die es versteht.

Nennt TSJY eine 17FEIA-Nummer oder eine Liniennummer, deren Format eindeutig auf einen Peek bekannten Dienst zeigt, fragt Peek diesen Dienst ab. Bis jener Dienst einen Scan mindestens so aktuell wie TSJYs neuesten liefert, bleibt Peek bei TSJYs Tracking, zeigt aber bereits den Zusteller an („Zustellung durch 17FEIA“, oder „Bei 17FEIA angekündigt, noch nicht eingetroffen“, solange 17FEIA nur die Ankündigung ohne eigenen Scan hat) samt Nummer. Danach verfolgt Peek das Paket dort weiter und behält TSJYs Verlauf.

Solange du das Paket in Peek geöffnet hast oder am Zustelltag Benachrichtigungen aktiviert sind, prüft Peek bis zu alle 10 Minuten, solange TSJYs eigener Tracker antwortet (sobald 17FEIA oder der örtliche Zusteller übernimmt, folgt Peek dessen Takt – inklusive bis zu alle 2 Minuten ab 6 Uhr Ortszeit am Zustelltag, wenn dessen direkter Tracker antwortet). Nach zwei Tagen ohne Neuigkeiten (nach 12 Stunden, solange das Paket noch „Etikett erstellt“ oder „Beim Paketdienst eingegangen“ zeigt) prüft Peek stündlich; bleibt es sieben Tage lang bei „Etikett erstellt“, alle 6 Stunden. Peek kann dich für „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“ benachrichtigen. Es kann weder TSJY noch 17FEIA oder die Shops für dich kontaktieren.

## Häufige Fragen zur TSJY-Sendungsverfolgung

### Was ist TSJY (通世集运)?

Ein Konsolidierungsdienst (集运) für Käufer im Ausland, die auf chinesischen Shopping-Websites bestellen. Laut eigener Website wurde TSJY 2005 gegründet, lagert Pakete 180 Tage kostenlos ein und liefert in über 220 Länder und Regionen.

### Wo kann ich eine TSJY-Nummer verfolgen?

Auf der [TSJY-Website](https://www.tsjy56.com/) unter 订单查询: TSJY-Nummer oder Liniennummer eingeben und auf 查询 tippen – oder in Peek einfügen.

### Was bedeutet 订单打包完成，等待出库?

Chinesisch für „Auftrag verpackt, wartet auf Warenausgang“: Dein Paket liegt verpackt in TSJYs Lager und wartet auf die Versandlinie. Peek zeigt „Beim Paketdienst eingegangen“. Danach folgt `订单已出库，开始国际运输`: Es hat das Lager verlassen und der internationale Versand hat begonnen.

### Warum zeigt mein TSJY-Paket eine 17FEIA-Nummer?

[17FEIA](carrier:17feia) ist die Versandlinie, die dein Paket ins Ausland befördert: TSJYs Ergebniskarte nennt deren `FEI…YQ`-Nummer hinter einem Schrägstrich. Sobald 17FEIA aufholt, verfolgt Peek das Paket dort weiter.

### Wie kontaktiere ich TSJY?

Über den Bereich 客服中心 auf der Startseite: Telefonnummer, E-Mail-Adresse und WeChat, alles auf Chinesisch.

:::sources
- [TSJY: 通世集运 (home page)](https://www.tsjy56.com/) – Unternehmen, Leistungen, Erstattungsversprechen, Tracking-Umfang, Bestellsuche und mobile Ergebnisseite, Bestellung, Kontakte, Lager
- [TSJY: 轨迹查询 (tracking results)](https://www.tsjy56.com/orderResult.html) – mobile Ergebnisse: Karten mit beiden Nummern, Kartenstatus, Meldung ohne Treffer
- [TSJY: 轨迹详情 (track details)](https://www.tsjy56.com/orderDetail.html) – Kanalname (渠道) und Nummer, Tracking-Zeilen, Zeiten in Gerätezeit
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 3-€-Zoll seit 1. Juli 2026, 2-€-Unions-Bearbeitungsgebühr seit 1. November 2026
- [European Commission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 2 € pro Artikel unabhängig vom Wert, Definition eines Artikels, Fernverkäufe und selbst organisierter Transport
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Nummernformat, Link, Wortlaute und Stufen, Zeitzonen, Übergabe an die Linie, was Peek ausliest
:::
