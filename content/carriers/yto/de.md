---
title: YTO Express Sendungsverfolgung: YT-Nummer & Status
description: Verfolge ein YTO-Express-Paket (圆通): Aufbau der YT-Sendungsnummer, Bedeutung der chinesischen Scans, Zustellung in China, Laufzeiten nach Europa und Kontakt.
slug: yto-express-sendungsverfolgung
published: 2026-11-18
updated: 2026-11-20
---

YTO Express (圆通速递, Yuantong) ist ein im Jahr 2000 in Shanghai gegründeter chinesischer Expressdienst mit mehr als 5.000 Niederlassungen in ganz China. Eine YTO-Nummer gehört meistens zu einem Paket innerhalb Chinas: etwa einer Bestellung von Taobao, Pinduoduo oder Douyin – Plattformen, die YTO beliefert – auf dem Weg zum Lager eines Weiterleitungsdiensts oder zu einer Privatadresse. Verfolgen kannst du sie im [YTO-Tracker](https://www.yto.net.cn/ytoExpress/waybill/search), der ausschließlich auf Chinesisch verfügbar ist, oder direkt im Feld oben. Sendungen von YTO ins Ausland laufen über YTO International mit eigenem Tracker unter [ytoglobal.com](https://www.ytoglobal.com/service).

## YTO-Express-Sendungsnummer: wie sie aussieht

YTO bezeichnet sie als Frachtbriefnummer (`运单号`). Mit erfundenen Ziffern:

| Nummer | Was das ist |
| --- | --- |
| `YT2468135790246` | `YT` und 13 Ziffern: ein Frachtbrief von YTO Express. |
| `D24681357902` | `D` und 11 Ziffern: ein YTO-Format, doch Nummern von Colis Privé können genauso aussehen. |
| `G24681357902` | `G` und 11 Ziffern: ein weiteres YTO-Format, das ebenfalls mit Colis Privé überlappen kann. |
| `YT2612468135790246` | `YT` und 16 Ziffern: nicht YTO, sondern [YunExpress](carrier:yunexpress). |

Kleinschreibung und Leerzeichen spielen in [Peek](/) keine Rolle. YTOs eigener Tracker nimmt bis zu 10 Nummern gleichzeitig entgegen, getrennt durch Kommas, Schrägstriche oder Leerzeichen.

**Wo du sie findest.** Der Verkäufer teilt sie dir mit den Versanddetails der Bestellung mit, außerdem steht sie auf dem Versandetikett des Pakets. Falls du sie nicht findest, lies [Sendungsnummer finden](guide:find-tracking-number).

**Abfrage auf YTOs Website.** Der Tracker bleibt selbst auf der englischen YTO-Seite auf Chinesisch: Gib die Nummer in das Suchfeld ein und klicke auf `查询` (Suchen). Laut YTOs Datenschutzerklärung bleibt ein Frachtbrief je nach Buchungsweg drei bis sechs Monate lang abrufbar; danach löscht YTO ihn aus dem Abfragesystem.

## YTO-Express-Tracking-Status erklärt

Jeder Scan in YTOs Tracking-Daten besteht aus einer kurzen chinesischen Bezeichnung, einer Uhrzeit nach chinesischer Zeit (`UTC+8`) und der Niederlassung oder Station, die ihn erfasst hat. So stellt Peek diese Einträge dar:

| Scan bei YTO | In Peek | Was passiert ist |
| --- | --- | --- |
| `揽收扫描` | `Picked up` | Beim Absender abgeholt. |
| `业务员收件` | `Picked up` | Persönlich durch einen YTO-Zusteller beim Absender entgegengenommen. |
| `装件入车扫描` | `Loaded for transport` | Für die Weiterfahrt in ein Fahrzeug verladen. |
| `下车扫描` | `Unloaded at a sorting centre` | In einem Sortierzentrum oder einer Niederlassung ausgeladen. |
| `快件城市信息` | „Unterwegs“ | Ortsmeldung einer durchlaufenen Stadt auf der Strecke. |
| `冬运会安检` | `Security check` | Sicherheitskontrolle des Pakets im YTO-Netz anlässlich einer Wintersportveranstaltung; danach geht es weiter. |
| `派件扫描` | „In Zustellung“ | Dem Zusteller für die Auslieferung übergeben. |
| `入柜\|入库` | `In a parcel locker or pickup station` | In ein Paketfach gelegt oder in einer Abholstation eingebucht. |
| `小件员取出` | `Taken out of the locker by the courier` | Der Zusteller hat das Paket wieder aus dem Fach oder der Station entnommen. |
| `PDA正常签收扫描` | „Zugestellt“ | Gegen Empfangsbestätigung (`签收`) übergeben, erfasst auf dem Handgerät des Zustellers. |
| `退回件扫描` | `Return to the sender started` | Die Rücksendung an den Absender hat begonnen. |

Die drei Zeilen in Anführungszeichen zeigt Peek auf Deutsch an; die übrigen Zeilen der Tabelle bleiben auf Englisch, und nicht aufgeführte Scans erscheinen im chinesischen Original von YTO. Der übergeordnete Paketstatus steht immer auf Deutsch, etwa „Beim Paketdienst eingegangen“ oder „Abholbereit“. Nach `入柜|入库` wechselt der Status auf „Abholbereit“; holt der Zusteller die Sendung wieder heraus (`小件员取出`), springt er zurück auf „Unterwegs“.

Lautet die Lieferadresse auf das Lager eines Weiterleitungsdiensts in China, bedeutet `PDA正常签收扫描`, dass das Lager dein Paket quittiert hat. Ab dort übernimmt die eigene Sendungsnummer des Weiterleitungsdiensts.

## YTO-Zustellung in China: Zusteller, Paketfächer und Stationen

In **China** bringt ein YTO-Zusteller das Paket vorbei (`派件扫描`), oder es wird zur Abholung in ein Paketfach beziehungsweise eine Station gelegt (`入柜|入库`). YTO führt `95161` als „Courier Outbound Hotline“ auf – die Rufnummer, von der aus dich seine Zusteller anrufen.

Die in **China** seit dem 1. März 2024 geltenden Vorschriften für Expressdienste schreiben vor:

- YTO muss dich darauf hinweisen, dass du das Paket bei der Übergabe anhand des Frachtbriefs prüfen darfst. Ist die Verpackung sichtbar beschädigt oder der Inhalt zerbrechlich, darfst du hineinschauen oder die Annahme verweigern.
- Ein Unternehmen darf ohne deine Zustimmung weder den Empfang in deinem Namen bestätigen noch das Paket in einem intelligenten Paketfach oder einer Expressstation hinterlegen.

Um ein Paket vor der Auslieferung zurückschicken oder an eine neue Adresse umleiten zu lassen (`退回/更址`), nennt YTO unter anderem seine App und sein Miniprogramm. Laut Datenschutzerklärung wendest du dich für Änderungen von Lieferadresse oder Telefonnummer an den Kundenservice.

## YTO International: Pakete ins Ausland

YTO International (圆通国际) geht auf das Jahr 2006 zurück und gibt über 50 Büros in 18 Ländern und Regionen an. Sein eigener Tracker unter [ytoglobal.com/service](https://www.ytoglobal.com/service) steht auf Englisch („Express Freight Tracking“) und Chinesisch (`国际快递查询`) bereit und akzeptiert bis zu 10 durch Kommas oder Semikolons getrennte Nummern.

YTOs Seiten nennen nicht, welcher Paketdienst in deinem Land zustellt, sondern sprechen nur von Partnerschaften mit führenden globalen Logistikdienstleistern („top-notch global logistics service providers“). Für International Standard Express, den Tarif für Privatkunden, nennt YTO ab seinem internationalen Lager unverbindlich („for reference only“) 7 bis 12 Werktage nach Deutschland, Österreich, in die Schweiz, nach Liechtenstein, Frankreich, Spanien, Polen, ins Vereinigte Königreich und nach Kanada (12 bis 17 nach Bulgarien und Rumänien), 9 bis 12 in die USA sowie 8 bis 13 nach Australien.

Trifft dich niemand an, verweist die FAQ von YTO International auf die „Redelivery Notification“, über die sich kostenlos ein neuer Zustellversuch vereinbaren lässt; scheitert auch dieser, fragt YTO beim Absender nach weiteren Anweisungen. Um vorab ein Zeitfenster abzustimmen, nennt die FAQ die Hotline `400 60 95554`. Bei Luftfrachtsendungen kontrollieren Zoll und Flughafensicherheit die Pakete; enthält eine Sendung verbotene Gegenstände, kann der Zoll sie beschlagnahmen oder vernichten.

**Zoll und Einfuhrabgaben (EU).** Abgaben richten sich nach dem Zielland: siehe [Paket beim Zoll](guide:customs) und [gefälschte Paket-SMS erkennen](guide:delivery-scam-texts). In der **Europäischen Union** fällt seit dem 1. Juli 2026 auf im Fernverkauf außerhalb des EU-Zollgebiets gekaufte Waren in Sendungen mit einem Sachwert bis 150 € ein Zoll von 3 € pro Warenkategorie an; seit dem 1. November 2026 kommt unabhängig vom Warenwert eine EU-Bearbeitungsgebühr von 2 € pro Warenkategorie hinzu (eine Kategorie umfasst alle Waren einer Sendung mit derselben Zolltarifnummer, derselben Warenbezeichnung und – soweit anzugeben – demselben Ursprung, ungeachtet der Stückzahl). In **Deutschland** liegen die Insel Helgoland und das Gebiet von Büsingen außerhalb des Zollgebiets und des Mehrwertsteuergebiets der EU.

## YTO-Paket kommt nicht an, ist verloren oder geht zurück

**Noch keine Einträge.** Ein neuer Frachtbrief zeigt nichts an, bis YTO das Paket zum ersten Mal scannt. Bleibt er leer, prüfe die Nummer beim Verkäufer. Bei einem älteren Frachtbrief kann die Anzeige leer bleiben, weil YTO die Daten nach drei bis sechs Monaten gelöscht hat (siehe oben).

**Keine Aktualisierung.** In **China** wertet YTOs Dienstleistungsvertrag mit Absendern ein Paket als verloren, wenn nach dem ersten Eintrag 4 Tage lang kein Update folgt oder es 7 Tage nach dem geplanten Termin noch nicht quittiert ist. Wende dich an den Verkäufer, der den Versand gebucht hat und bei YTO nachforschen kann. Mehr unter [Sendungsverfolgung aktualisiert sich nicht](guide:tracking-not-updating) und [Paket aus China verfolgen](guide:tracking-from-china).

**Verloren oder beschädigt.** Bei einer Shop-Bestellung hat der Verkäufer den Versand bezahlt – frag zuerst ihn. Nach demselben Vertrag in **China** wird ein verlorenes Paket ohne Wertdeklaration (`保价`) mit bis zu 300 Yuan entschädigt, bei Dokumenten mit bis zu 100 Yuan; versicherte Pakete erhalten den tatsächlichen Schaden bis zur deklarierten Summe. Beschwerden gehen telefonisch an die einliefernde YTO-Stelle oder den YTO-Kundenservice; außerdem bietet YTOs Website hinter einem Login Seiten für Beschwerden (`投诉反馈`) und Wertdeklarations-Schäden (`保价理赔`). Hilft YTOs Antwort nicht weiter oder bleibt sie aus, erlauben Chinas Vorschriften eine Beschwerde bei der Postaufsichtsbehörde State Post Bureau (`消费者申诉` auf deren Website). Siehe auch [verlorene Pakete](guide:lost-parcel).

**Zurückgeschickt.** Nach `退回件扫描` ist das Paket auf dem Rückweg, und spätere Zustellscans gehören zur Rückfahrt an den Absender. Kontaktiere den Verkäufer; siehe [zurück an den Absender](guide:returned-to-sender).

**Zugestellt, aber nicht da.** In **China** erlaubt YTOs Vertrag, dass Mitbewohner oder eine von dir benannte Person quittieren oder der Zusteller das Paket in ein Paketfach beziehungsweise eine Expressstation nahe der Adresse legt. Prüfe diese Stellen sowie den Empfang deines Gebäudes. Siehe [zugestellt, aber nicht erhalten](guide:delivered-not-received).

## YTO Express Telefonnummer und Kundenservice

Auf seinen Seiten führt YTO folgende Nummern auf:

- **`95554`**: „Service Hotline“ (`服务热线`) für YTO Express innerhalb Chinas.
- **`400 60 95554`**: „Service Hotline“ von YTO International für Auslandssendungen.
- **`95161`**: „Courier Outbound Hotline“, die Nummer für ausgehende Anrufe der YTO-Zusteller.
- **`021-69777888`**: daneben ohne eigene Bezeichnung aufgeführt.

Servicezeiten oder Sprachen gibt YTO dafür nicht an. Auf der Website gibt es zudem eine Schaltfläche für den Online-Kundenservice (`在线客服`), bei YTO International ein Kontaktformular. Für Fragen zur Bestellung selbst ist der Verkäufer zuständig.

## Ein YTO-Express-Paket mit Peek verfolgen

[Peek](/) erkennt YTO Express sofort anhand von `YT` und 13 Ziffern. Ein `D`- oder `G`-Code mit 11 Ziffern passt hingegen auch zu Colis Privé, sodass Peek allein aus der Form keine sichere Zuordnung treffen kann. Füge ihn dennoch ein: Peek verfolgt über 4.500 Paketdienste und prüft die Tracking-Dienste („Tracking-Dienste werden geprüft…“). Erscheint YTO Express nicht automatisch, tippe auf „Ändern“ und wähle ihn unter „Passt zu dieser Nummer“ aus, oder nutze „Paketdienst ändern“ beim Paket. Peeks YTO-Abfrage verarbeitet auch YTO-Frachtbriefe in anderen Formaten – wähle dafür YTO Express unter „Alle Paketdienste“. Ein Link von `yto.net.cn` mit `mailNo=` oder `waybillNo=` samt Nummer ordnet das Paket ebenfalls YTO zu.

Peek liest YTOs Inlands-Tracking in China aus, nicht das von YTO International. Angezeigt werden der Status, die von YTO gemeldeten Scans mit der Stadt der jeweiligen Niederlassung oder Station (oder ihrem chinesischen Namen, falls Peek sie nicht verorten kann) sowie die Zustellzeit. Uhrzeiten werden nach chinesischer Zeit (`UTC+8`: `20:00` Uhr in Peking entspricht in Deutschland, Österreich und der Schweiz im Winter `13:00` Uhr MEZ und im Sommer `14:00` Uhr MESZ) gelesen und in deiner Geräte-Zeitzone ausgegeben; auf peektracker.com und in den Apps für iPhone und Android endet der Verlauf mit „Uhrzeiten in deiner Zeitzone:“ und deren Namen. Wiederholte Scans erscheinen nur einmal. Unmittelbar nach `退回件扫描` wechselt der Status auf „Problem gemeldet“, und Peek zeigt den Rückweg auf Englisch als `Out for delivery back to the sender` und `In a parcel locker or station on its way back` sowie die Unterschrift des Absenders als `Returned to the sender` mit dem Status „Zurück an Absender“.

YTO nennt kein voraussichtliches Lieferdatum. Namen und Telefonnummern der Zusteller, Empfängerdaten und Abholadressen lässt Peek weg – diese stehen nur in YTOs eigenem Tracker. Da YTO jede formal gültige Nummer beantwortet, beweist das Fehlen von Scans keinen Tippfehler: Peek zeigt „Warten auf YTO Express“ und prüft weiter. Kennt YTO ein unter YTO geführtes Paket auch nach zwei Tagen noch nicht und hat keine andere Quelle geantwortet, blendet Peek „Auch nach zwei Tagen kennt der Paketdienst diese Nummer nicht. Prüfe sie, wähle einen anderen Paketdienst oder lösche das Paket.“ ein und fragt ab dann täglich sowie nach einer Woche ab dem Hinzufügen wöchentlich ab.

Peek prüft bis zu alle 10 Minuten, solange du das Paket geöffnet hast oder am Zustelltag mit eingeschalteten Mitteilungen, und bis zu alle 2 Minuten ab 6:00 Uhr chinesischer Zeit an dem Tag, an dem YTO das Paket als in Zustellung scannt (`派件扫描`), solange YTOs eigener Tracker antwortet; über Tracking-Dienste prüft Peek höchstens stündlich, nach einem Tag ohne Scan alle 3 Stunden und am Zustelltag bis zu alle 15 Minuten, solange das Paket geöffnet ist oder Mitteilungen eingeschaltet sind. Sonst sowie nach zwei Tagen ohne neuen Scan (12 Stunden auf „Beim Paketdienst eingegangen“) prüft Peek stündlich. Benachrichtigungen gibt es für „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“. Peek kann weder YTO noch den Verkäufer für dich kontaktieren.

## Häufige Fragen zur YTO-Express-Sendungsverfolgung

### Ist YTO Express seriös?

Ja. YTO Express wurde am 28. Mai 2000 in Shanghai gegründet. Zur Gruppe gehören zwei börsennotierte Gesellschaften – YTO Express (Börsencode 600233) und YTO International (06123.HK) – und Ende 2024 zählte das Unternehmen nach eigenen Angaben 550.000 Beschäftigte.

### Was bedeutet 签收 in der YTO-Sendungsverfolgung?

Gegen Unterschrift zugestellt: Der Zusteller hat das Paket übergeben und auf seinem Handgerät quittiert (`PDA正常签收扫描`), was in Peek als „Zugestellt“ erscheint. Empfänger kannst du selbst sein, eine für dich unterschriftsberechtigte Person oder das Lager eines Weiterleitungsdiensts. Hat niemand das Paket erhalten, siehe [zugestellt, aber nicht erhalten](guide:delivered-not-received).

### Warum zeigt meine YTO-Nummer keine Sendungsverfolgung?

Entweder hat YTO das Paket noch nicht gescannt, die Nummer stimmt nicht, oder der Frachtbrief ist älter als das Abfragefenster von drei bis sechs Monaten laut YTOs Datenschutzerklärung. Prüfe die Nummer beim Verkäufer oder füge sie in Peek ein, das weiter nachfragt.

### Liefert YTO Express außerhalb Chinas?

Ja, über YTO International mit eigenem Tracker auf ytoglobal.com. Welcher Paketdienst vor Ort zustellt, nennen die Seiten nicht; als unverbindlichen Richtwert gibt YTO 7 bis 12 Werktage nach Deutschland, Österreich, in die Schweiz, das Vereinigte Königreich, Kanada und die meisten EU-Staaten an.

### Wie lautet die Telefonnummer von YTO Express?

`95554` innerhalb Chinas und `400 60 95554` für YTO International, jeweils ohne veröffentlichte Sprechzeiten. Anrufe von `95161` stammen von YTO-Zustellern.

:::sources
- [YTO Express: About YTO (auf Englisch)](https://www.yto.net.cn/en/aboutYto/about) – Gründung am 28. Mai 2000 in Shanghai, Börsennotierungen, Niederlassungen und Mitarbeiterzahl
- [YTO Express: 我要查件 (Frachtbrief-Tracking, auf Chinesisch)](https://www.yto.net.cn/ytoExpress/waybill/search) – rein chinesischer Tracker, bis zu 10 Nummern, Beschwerde- und Schadensseiten
- [YTO Express: Domestic Express Services (auf Englisch)](https://www.yto.net.cn/en/domesticService) – bediente Plattformen, Rücksendung oder Adressänderung vor Zustellung, Hotlines 95554 und 95161
- [YTO Express: Startseite (auf Chinesisch)](https://www.yto.net.cn/) – Online-Kundenservice `在线客服`
- [YTO Express: 国内快递 (Inlandsdienste, auf Chinesisch)](https://www.yto.net.cn/domesticService) – `退回/更址`, `服务热线`, `快递员外呼热线`
- [YTO Express: 国内快递服务协议 (Inlands-Expressvertrag, auf Chinesisch)](https://mec.yto.net.cn/h5/#/subPages/agreement/index?code=DESA) – Verlustfristen, Entschädigung mit und ohne Wertdeklaration, Beschwerden, Empfangsberechtigte
- [YTO Express: 圆通速递隐私政策 (Datenschutzerklärung, auf Chinesisch)](https://mec.yto.net.cn/h5/#/subPages/agreement/index?code=PCPrivacyAgreement) – Abrufbarkeit für drei bis sechs Monate, Adressänderung über den Kundenservice
- [YTO Express: 寄快递 (Paket versenden, auf Chinesisch)](https://www.yto.net.cn/ytoExpress/subCreatePages/create) – Entschädigung bis 300 Yuan ohne Wertdeklaration
- [YTO Express: International Business Service (auf Englisch)](https://www.yto.net.cn/en/global/globalProduct) – Hotline von YTO International 4006095554 sowie 021-69777888
- [YTO International: Company Introduction (auf Englisch)](https://www.ytoglobal.com/about-us) – Geschichte seit 2006, Büros und Partner
- [YTO International: Express Freight Tracking](https://www.ytoglobal.com/service) – Tracker auf Englisch und Chinesisch für bis zu 10 Nummern
- [YTO International: International Standard Express (auf Englisch)](https://www.ytoglobal.com/product/express-intl) – Privatkundentarif und unverbindliche Regellaufzeiten nach Ländern
- [YTO International: FAQ (auf Englisch)](https://www.ytoglobal.com/rich/question) – Hotline 400 60 95554, kostenlose Zweitzustellung, Terminabsprache, Zoll- und Sicherheitskontrolle
- [YTO International: Contact Us (auf Englisch)](https://www.ytoglobal.com/about-us/contact) – Kontaktformular
- [Verkehrsministerium Chinas: 快递市场管理办法 (Verwaltungsvorschriften für den Expressmarkt, auf Chinesisch)](https://xxgk.mot.gov.cn/gz/202401/t20240104_3980684.html) – in Kraft seit 1. März 2024, Prüfung bei Übergabe, Einwilligungspflicht für Paketfächer und Stationen, Beschwerdeweg
- [State Post Bureau Chinas: 国家邮政局主要职责 (Hauptaufgaben, auf Chinesisch)](https://www.spb.gov.cn/gjyzj/c100001/c100006/201306/2722edab189242549b320d06e0dbd382.shtml) – Post- und Expressmarktaufsicht
- [State Post Bureau Chinas: Startseite (auf Chinesisch)](https://www.spb.gov.cn/) – Verbraucherbeschwerdebereich (`消费者申诉`)
- [Europäische Kommission: EU Customs Reform (auf Englisch)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 3 € Zoll je Warenkategorie bis 150 € seit 1. Juli 2026 und 2 € EU-Bearbeitungsgebühr seit 1. November 2026
- [Europäische Kommission: Guidance on the EUR 3 customs duty and the Union handling fee (PDF, auf Englisch)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 2 € Gebühr unabhängig vom Warenwert sowie Definition einer Warenkategorie
- [Europäische Kommission: Territorial Scope (auf Englisch)](https://taxation-customs.ec.europa.eu/taxation/vat/vat-directive/how-does-vat-work/territorial-scope_en) – Helgoland und Büsingen außerhalb des Zollgebiets und des Mehrwertsteuergebiets der EU
- [Europäische Kommission: Summertime (auf Englisch)](https://transport.ec.europa.eu/transport-themes/summertime_en) – europäische Sommerzeit vom letzten Sonntag im März bis zum letzten Sonntag im Oktober
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Nummernformate, Links von `yto.net.cn`, YTO-Scanbezeichnungen, chinesische Uhrzeit, Rücksendeverlauf sowie angezeigte und ausgeblendete Felder in Peek
:::
