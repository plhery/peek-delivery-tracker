---
title: DobroPost Sendungsverfolgung: DBRPST-Codes & China-Hubs
description: Verfolge ein DobroPost-Paket von China nach Russland oder Belarus: DBRPST-Formate, Lager- und Zollscans, PVZ-Abholung, Übergaben & Support.
slug: dobropost-sendungsverfolgung
published: 2026-11-24
updated: 2026-11-24
---

DobroPost (*ДоброПост*, betrieben von *OOO „Dobropost Logistika“* in Moskau gemeinsam mit *OOO ICOM DP SOLUTIONS* in Armenien) ist ein Paketweiterleitungsdienst von China nach Russland und Belarus für Käufer auf chinesischen Marktplätzen wie Taobao, Poizon, 1688, Pinduoduo, Tmall, 95, Goofish und JD. Käufer lassen ihre Einkäufe an das DobroPost-Lager in Jixi (Heilongjiang) liefern, wo die Ware geprüft, verpackt und gewogen wird; anschließend durchläuft das Paket den Zoll und geht an einen eigenen DobroPost-Abholpunkt (PVZ) oder an [CDEK](carrier:cdek), DPD oder [Russian Post](carrier:russian-post). Der DobroPost-Tracker leitet außerdem Scans von Boxberry weiter. Du kannst eine Sendung im Tracker unter [tracking.dobropost.com](https://tracking.dobropost.com/) prüfen oder deinen `DBRPST`-Code oben einfügen.

## DobroPost-Sendungsnummern (Format `DBRPST`)

Eine DobroPost-Paketnummer beginnt mit `DBRPST`, gefolgt von 10 oder 11 Ziffern:

| Format (erfunden) | Worum es sich handelt |
| --- | --- |
| `DBRPST6482917304` | 16-stellige DobroPost-Paketnummer (`DBRPST` + 10 Ziffern). |
| `DBRPST73918462051` | 17-stellige DobroPost-Paketnummer (`DBRPST` + 11 Ziffern). |

Die Nummern chinesischer Kurierdienste auf dem Weg zum DobroPost-Lager gehören zu diesen Diensten, nicht zu DobroPost: [tracking.dobropost.com](https://tracking.dobropost.com/) und Peek verfolgen das Paket anhand seiner `DBRPST`-Nummer. Siehe [Sendungsnummern-Formate](guide:tracking-number-formats).

## Was die DobroPost-Sendungsstatus bedeuten

DobroPost veröffentlicht Statusmeldungen auf Russisch zusammen mit internen Statuscodes — zunächst aus dem eigenen Lager in China und der Zollabfertigung, danach direkt weitergeleitet vom inländischen Zustelldienst ([CDEK](carrier:cdek), Boxberry oder [Russian Post](carrier:russian-post)):

| DobroPost-Code oder Wortlaut | Was das für dich bedeutet | Peek zeigt |
| --- | --- | --- |
| `VIRTUAL_PARCEL` | Das Paket ist im System von DobroPost angelegt; wenige Tage später verpackt das China-Lager deine Waren hinein. | „Etikett erstellt“ |
| `ON_ASSEMBLY`, `ON_WEIGHING`, `AWAITING_PAYMENT`, `READY_FOR_SENT`, `ADDED_TO_THE_REGISTRY` | Im DobroPost-Lager in China verpackt und gewogen, wartet auf Zahlung der Versandkosten oder wurde einem Versandregister hinzugefügt. | „Beim Paketdienst eingegangen“ |
| `LEFT_THE_DP_WERHOUSE_IN_CHINA`, `FLEW_OUT_FROM_CHINA` | Hat das DobroPost-Lager in China verlassen und ist auf den internationalen Transport gestartet. | „Unterwegs“ |
| `AT_CUSTOMS_IN_CHINA`, `ARRIVED_AT_CUSTOMS_IN_THE_COUNTRY_OFF_DESTINATION`, `START_OF_CUSTOMS_CLEARANCE`, `Выпуск с таможни` | In der Ausfuhrabfertigung in China oder Einfuhrverzollung in Russland bzw. Belarus. | „Beim Zoll“ |
| `RELEASE_OF_GOODS_WITHOUT_PAYMENT_OF_CUSTOMS_DUTIES`, `LEFT_CUSTOMS_AND_HEADING_TO_LAST_MILE_CARRIER'S_WAREHOUSE`, `READY_FOR_SHIPMENT_FOR_LAST_MILE_DELIVERY` | Vom Zoll freigegeben und auf dem Weg ins lokale Zustellnetz. | „Unterwegs“ |
| `REFUSAL_TO_RELEASE_GOODS_WITH_INDICATION_OF_THE_CODE_OF_THE_REASON_FOR_REFUSAL` | Der Zoll hat die Freigabe verweigert; der Tracker nennt den Grund (z. B. Ware nicht für den Eigenbedarf). Prüfe dein DobroPost-Konto. | „Problem gemeldet“ |
| `RECEIVED_AT_SHIPMENT_WAREHOUSE`, `SEND_TO_TRANSIT_OFFICE`, `SEND_TO_RECIPIENT_OFFICE`, `ACCEPTED_FOR_DELIVERY`, `SENT_TO_DESTINATION_CITY`, `Прием`, `Обработка` | Weitergeleitete Inlandsscans von CDEK, Boxberry oder Russian Post auf dem Weg in deine Stadt. | „Unterwegs“ |
| `ACCEPTED_AT_WAREHOUSE_ON_DEMAND` (CDEK), `RECEIVED_AT_THE_POINT_OF_ISSUE` (Boxberry) | Im gewählten Abholpunkt oder Paketshop eingetroffen und abholbereit. | „Abholbereit“ |
| `GETTING_READY_TO_RETURN`, `SENT_TO_COLLECTION_POINT`, `RETURNED_TO_COLLECTION_POINT`, `RETURNED`, `Возврат` | Nicht am Abholpunkt abgeholt oder auf der letzten Etappe zurückgeschickt, auf dem Rückweg zu DobroPost (erneuter Versand möglich). | „Zurück an Absender“ |
| `RELEASE_OF_GOODS_CUSTOMS_PAYMENTS_PAID` (`Возврат в Китай разрешен`) | Der Zoll hat die Freigabe abgelehnt und die Rücksendung nach China genehmigt. | „Zurück an Absender“ |
| `PARCEL_DELIVERED_TO_RECIPIENT`, `DELIVERED`, `Вручение` | Am Abholpunkt abgeholt oder dem Empfänger zugestellt. | „Zugestellt“ |

## Laufzeiten, Tarife und DobroPost-PVZ-Abholpunkte

Laut [DobroPost-Tarife](https://dobropost.com/tariffs) zählen die Laufzeiten ab dem Versand aus dem DobroPost-Lager in China bis zum Eintreffen im Abholpunkt (PVZ) oder der Übergabe an einen Inlandskurier:

- **Express-Tarife** (Volumengewicht, keine sensiblen Waren): üblicherweise **9–13 Tage** nach Moskau, **10–14 Tage** nach Sankt Petersburg, **9–12 Tage** nach Nowosibirsk, Krasnodar, Jekaterinburg, Kasan, Irkutsk und Ulan-Ude sowie **11–15 Tage** nach Minsk (Belarus).
- **Economy-Tarife** (tatsächliches Gewicht, geeignet für schwerere oder sperrige Pakete): üblicherweise **19–25 Tage** nach Moskau, **20–27 Tage** nach Sankt Petersburg, **15–23 Tage** nach Irkutsk und **22–29 Tage** nach Minsk.
- **Fernost- & Schwerpakettarife:** **6–9 Tage** nach Wladiwostok, **8–12 Tage** nach Blagoweschtschensk und **6–9 Tage** für Einzel-Schwerpakete von 5 bis 30 kg.
- **DobroPost-PVZ-Abholpunkte:** gelistet unter [dobropost.com/pvz](https://dobropost.com/pvz) in Moskau, Odinzowo, Sankt Petersburg, Kasan, Krasnodar, Nowosibirsk, Jekaterinburg, Irkutsk, Ulan-Ude, Blagoweschtschensk, Wladiwostok, Ussurijsk und Minsk. Veröffentlichte PVZ-Ausgabegebühren sind `100 RUB` (bis 5 kg), `200 RUB` (bis 10 kg) und `300 RUB` (über 10 kg); Städte ohne DobroPost-PVZ nutzen Partner-Abholpunkte und Kuriere. Ein Paket wartet im DobroPost-PVZ 7 Tage kostenlos, danach für 50 ₽ pro Tag, und wird 30 Tage nach Ankunft entsorgt.

## Zoll, Trackingpausen und Paketprobleme

- **Versandzahlung vor Abgang erforderlich:** Bleibt das Tracking auf `AWAITING_PAYMENT`, bleibt das Paket im China-Lager, bis du die berechnete Versandkostenrechnung in deinem DobroPost-Kundenkonto bezahlst.
- **Pausen bei Grenzübertritt und Zoll:** Scans pausieren häufig zwischen `FLEW_OUT_FROM_CHINA` und `ARRIVED_AT_CUSTOMS_IN_THE_COUNTRY_OFF_DESTINATION` oder während der Zollprüfung. Siehe [Pakete beim Zoll](guide:customs) und [warum die Sendungsverfolgung nicht aktualisiert](guide:tracking-not-updating).
- **Schäden, fehlende Artikel oder Rücksendungen:** Die [DobroPost-Regeln](https://dobropost.com/rules) verlangen, Schäden direkt bei Übergabe auf dem Lieferschein festzuhalten und für Schadensmeldungen Inhalt und Originalverpackung vorzuzeigen. Die [Wissensdatenbank](https://dobropost.com/baza_main) erklärt, was bei fehlenden Artikeln zu tun ist. Ein nicht abgeholtes Paket geht an DobroPost zurück (`RETURNED`) und kann über den Support erneut versandt werden. Siehe [verlorenes Paket](guide:lost-parcel), [beschädigtes Paket](guide:damaged-parcel), [zugestellt, aber nicht erhalten](guide:delivered-not-received) und [zurück an Absender](guide:returned-to-sender).

## DobroPost Kundenservice und offizielle Links

Auf der DobroPost-Website gibt es eine Schaltfläche „Напишите в поддержку“ (Support anschreiben), eine Kunden-Community und einen Telegram-Kanal; Beschwerden und Ideen können zudem direkt an CEO Sergey Serbin gerichtet werden. Die [Wissensdatenbank](https://dobropost.com/baza_main) erläutert Support-Anfragen, Tarifänderungen bereits erstellter Pakete und die Zollabfertigung.

- **Registrierter russischer Betreiber:** *OOO „Dobropost Logistika“* (*ООО «Добропост Логистика»*, INN `9722023780`), Moskau (`119192, Lomonosovsky Prospekt, 43 bldg. 2, office 4/1/1`; die Website nennt zudem `Michurinsky Prospekt, 54 bldg. 2`).
- **Armenische Gesellschaft:** *OOO ICOM DP SOLUTIONS* (*ООО ИКОМ ДП СОЛУШНС*), Wanadsor, Provinz Lori, Armenien.
- **Bedingungen & Tarife:** [dobropost.com/rules](https://dobropost.com/rules), [dobropost.com/tariffs](https://dobropost.com/tariffs) und [dobropost.com/pvz](https://dobropost.com/pvz).

> Vorsicht vor gefälschten Telegram-Nachrichten oder SMS-Links, die zusätzliche Zollgebühren außerhalb deines offiziellen DobroPost-Kundenkontos oder offiziellen Zollbroker-Portals verlangen. Prüfe jede Zahlung immer zuerst im DobroPost-Konto. Siehe [Betrugs-SMS zu Paketlieferungen](guide:delivery-scam-texts).

## Ein DobroPost-Paket mit Peek verfolgen

[Peek](/) erkennt DobroPost automatisch als einzigen Kandidaten, wenn du eine `DBRPST`-Nummer eingibst (`DBRPST6482917304` oder `DBRPST73918462051`, erfunden), und zeigt es sofort mit „Erkannter Paketdienst“ an; auch eingefügte Links von `tracking.dobropost.com/?trackNumber=` erkennt Peek. Musst du DobroPost manuell wählen, tippe beim Hinzufügen auf „Ändern“ oder in der Sendungsansicht auf „Paketdienst ändern“ (auch die Suche nach „ДоброПост“ oder „Dobropost“ findet den Dienst).

Peek liest den DobroPost-Tracker direkt aus und zeigt die Phase („Etikett erstellt“, „Beim Paketdienst eingegangen“, „Unterwegs“, „Beim Zoll“, „Abholbereit“, „Problem gemeldet“, „Zurück an Absender“, „Zugestellt“), den russischen Wortlaut von DobroPost und den weitergeleiteten Partnern (CDEK, Boxberry und Russian Post) ohne Telefonnummern sowie die Zustellzeit nach Auslieferung. Die Stadt in Klammern am Ende eines CDEK-Scans dient als Scan-Ort. Die Statuscodes erscheinen in keiner Peek-App. DobroPost gibt jeden Scan mit Moskauer UTC-Versatz aus (auch für das China-Lager), und Peek zeigt ihn in der Uhrzeit deines Geräts. Der DobroPost-Tracker liefert kein voraussichtliches Zustelldatum, kein Gewicht, keinen Namen des Unterzeichners und keine Sendungsnummer der letzten Meile.

Solange du das Paket in Peek geöffnet hast oder am Zustelltag Benachrichtigungen aktiviert sind, fragt Peek bis zu alle 10 Minuten nach, sofern der DobroPost-Tracker antwortet. Sonst fragt Peek stündlich ab. Da der DobroPost-Tracker keinen Schritt „In Zustellung“ meldet, wechselt Peek nie auf die 2-Minuten-Abfrage am Zustelltag. Nach 12 Stunden nur bei „Etikett erstellt“ oder „Beim Paketdienst eingegangen“ oder nach zwei Tagen ohne Neuigkeiten prüft Peek stündlich, egal wie du die Sendung verfolgst, und nach sieben Tagen bei „Etikett erstellt“ alle 6 Stunden. Antwortet der DobroPost-Tracker nicht und Peek verfolgt das Paket allein über Tracking-Dienste, fragt Peek stündlich nach, nach einem ruhigen Tag alle 3 Stunden und an dem Tag, an dem das Paket in Zustellung ist, bis zu alle 15 Minuten, solange du die Sendung geöffnet hast oder Benachrichtigungen aktiviert sind.

Ist der neueste Scan vier Tage alt und das Paket steht noch auf „Etikett erstellt“, „Beim Paketdienst eingegangen“ oder „Unterwegs“, markiert Peek es mit dem Tag dieses Scans, etwa „Keine Aktualisierung seit Mo 12. Okt“. Auf der Paketseite steht dann „Auf langen Flügen, beim Zoll oder übers Wochenende wird es oft still um ein Paket. Peek fragt weiter bei DobroPost nach.“ Nach drei Wochen ohne neuen Scan heißt es „Seit über drei Wochen nichts Neues. Ist das Paket noch nicht angekommen, frag beim Verkäufer nach. Peek fragt weiter bei DobroPost nach.“

Bei den Benachrichtigungen kannst du zwischen „Jeden Scan“, „Wichtige Schritte“ und „Nur die Zustellung“ wählen. Peek beobachtet ausschließlich Tracking-Updates und kann weder Versandkosten begleichen noch Zoll-Passdaten einreichen oder DobroPost bzw. dessen Inlandspartner für dich kontaktieren.

## Häufige Fragen zur DobroPost-Sendungsverfolgung

### Wie sieht eine DobroPost-Sendungsnummer aus?

Internationale DobroPost-Sendungsnummern beginnen mit `DBRPST`, gefolgt von 10 oder 11 Ziffern (etwa `DBRPST6482917304` oder `DBRPST73918462051`, erfunden).

### Wie lange dauert eine DobroPost-Lieferung aus China?

Die auf [dobropost.com/tariffs](https://dobropost.com/tariffs) genannten Laufzeiten reichen von **6–9 Tagen** nach Wladiwostok und **9–13 Tagen** (Express) bzw. **19–25 Tagen** (Economy) nach Moskau bis zu **11–15 Tagen** (Express) bzw. **22–29 Tagen** (Economy) nach Minsk, gerechnet ab Versand aus dem China-Lager.

### Welche Inlandskuriere liefern DobroPost-Pakete in Russland und Belarus aus?

DobroPost liefert an eigene PVZ-Abholpunkte in Russland und Minsk sowie laut Tarifübersicht über [CDEK](carrier:cdek), DPD und [Russian Post](carrier:russian-post) (für Minsk nur CDEK). Der Tracker leitet Scans von CDEK, Boxberry und Russian Post weiter.

### Warum hängt mein DobroPost-Paket bei `AWAITING_PAYMENT` oder beim Zoll?

`AWAITING_PAYMENT` bedeutet, dass dein Paket im China-Lager von DobroPost verpackt und gewogen wurde und auf die Zahlung des Versands in deinem Kundenkonto wartet. Bei einer Pause im Zoll erklärt die [Wissensdatenbank](https://dobropost.com/baza_main) die Zollabfertigung, und der Support informiert über den Stand.

:::sources
- [DobroPost: Offizielle Website](https://dobropost.com/) – Weiterleitung von Marktplatz-Paketen China–Russland/Belarus, Lager Jixi, Support und CEO-Kontakt, Gesellschaften in Moskau und Armenien (*OOO „Dobropost Logistika“*, INN 9722023780)
- [DobroPost: Paket-Tracker](https://tracking.dobropost.com/) – öffentliche `DBRPST`-Trackingseite
- [DobroPost: Tarife & Laufzeiten](https://dobropost.com/tariffs) – Laufzeiten Express, Economy, Fernost und Schwerpakete, Gewichtsregeln und Partnerdienste
- [DobroPost: Abholpunkte (PVZ)](https://dobropost.com/pvz) – DobroPost-PVZ-Standorte in Russland und Minsk, Gebühren und Lagerung
- [DobroPost: Wissensdatenbank](https://dobropost.com/baza_main) – Marktplatz-Leitfäden, Support-Anfragen, Tarifwechsel, Zollabfertigung und fehlende Artikel
- [DobroPost: Allgemeine Beförderungsbedingungen](https://dobropost.com/rules) – Beförderungs- und Zollbedingungen
- [Universal Parcel Scraper: dobropost](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/dobropost) – `DBRPST`-Format, Link-Regel `tracking.dobropost.com`, Statuszuordnung DobroPost/CDEK/Boxberry/Russian Post, Ort in Klammern und Moskauer Zeitstempel
:::
