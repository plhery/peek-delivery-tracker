---
title: JCEX Sendungsverfolgung: JCZ-Nummern, Status & Zusteller
description: JCEX-Paket aus China verfolgen: wie eine JCZ-Nummer aussieht, was jeder Status bedeutet, wer in deinem Land zustellt und wen du bei Problemen fragst.
slug: jcex-sendungsverfolgung
published: 2026-11-28
updated: 2026-11-28
---

JCEX (佳成国际, Jiacheng International Logistics) ist ein Logistikunternehmen in Hangzhou, China, das Pakete für Onlinehändler ins Ausland verschickt und sie dort für die letzte Meile an einen lokalen Paketdienst übergibt — etwa an [USPS](carrier:usps) in den USA oder an Colissimo in Frankreich. Du verfolgst die Nummer im JCEX-Tracker unter [track.jcex.com](https://track.jcex.com/) oder direkt im Feld oben. Den Code bekommst du vom Shop in den Versanddetails deiner Bestellung.

## JCEX-Sendungsnummer: wie sie aussieht

JCEX veröffentlicht kein eigenes Nummernformat. Seine Frachtbriefnummern beginnen mit `JCZ` und enden auf `DH`. Mit erfundenen Ziffern:

| Nummer | Was es ist |
| --- | --- |
| `JCZ4829105736DH` | `JCZ`, 10 Ziffern und `DH`: ein JCEX-Frachtbrief. |
| `JCZ482910573DH` | Nur 9 Ziffern: ein Tippfehler, kein JCEX-Format. |

Groß- und Kleinschreibung sowie Leerzeichen spielen in Peek keine Rolle. Der JCEX-Tracker nimmt bis zu 100 Nummern auf einmal an, getrennt durch Kommas.

**Wo du sie findest.** Keine JCEX-Seite erklärt, wie die Nummer zu dir gelangt. Der Verkäufer bucht die Sendung und gibt die Nummer in der Versand-E-Mail oder auf der Bestellseite an. Findest du sie nicht, lies [Sendungsnummer finden](guide:find-tracking-number).

**Eine zweite Nummer.** Der JCEX-Tracker kann neben dem Frachtbrief eine zweite Nummer namens `跟踪单号` (Sendungsnummer) anzeigen, wenn JCEX dieses Feld ausfüllt. Peek liest sie nicht aus: Wenn USPS oder Colissimo zustellen, erscheinen deren Scans stattdessen direkt im Verlauf von JCEX.

## JCEX Tracking-Status erklärt

Der JCEX-Tracker ist nur auf Chinesisch verfügbar, aber der Text der einzelnen Zeilen kann auf Englisch, Chinesisch oder in beiden Sprachen stehen — hinter einem UTC-Versatz, einer Uhrzeit und einem Ort. Die Zeilen, die man sieht, in der Reihenfolge, in der ein Paket ihnen meist begegnet, und was Peek anzeigt, solange jede Zeile die neueste ist:

| JCEX meldet | Was das für dich bedeutet | Peek zeigt |
| --- | --- | --- |
| `Shipment order info received` | Der Verkäufer hat den Auftrag an JCEX übermittelt. JCEX hat das Paket noch nicht. | „Etikett erstellt“ |
| `Arrive at the delivery point` | Das Lager von JCEX in China hat das Paket angenommen. Nicht dein lokaler Zustellpunkt. | „Unterwegs“ |
| `Item outbound in sorting center` | Es hat das Sortierzentrum von JCEX verlassen. | „Unterwegs“ |
| `Departed from origin facility to airport` | Auf dem Weg zum Flughafen. | „Unterwegs“ |
| `Flight dispatched（航班已飞）` | Der Flug ist gestartet. | „Unterwegs“ |
| `Flight arrived（航班已落地）` | Es ist im Zielland gelandet. | „Unterwegs“ |
| `Customs held（清关中）` | Einfuhrzoll. Der chinesische Teil bedeutet „in der Verzollung“ — nicht zwingend ein Problem. | „Beim Zoll“ |
| `Clearance processing complete（清关完成）` | Durch den Zoll. | „Unterwegs“ |
| `Handed over to the trucking company, waiting for transport（已交卡车公司 待运输）` | Wartet auf den Lkw zum zustellenden Paketdienst. | „Unterwegs“ |
| `Delivered to warehouse, waiting to be scanned（已交仓，待扫描）` | Im Depot des zustellenden Paketdienstes abgegeben, nicht bei dir. | „Unterwegs“ |
| `CONSIGNMENT RECEIVED BY OVERSEAS FORWARDER` | Der zustellende Paketdienst hat das Paket. | „Unterwegs“ |
| `out for delivery` | In Zustellung. | „In Zustellung“ |
| `Delivered` | An dich zugestellt. | „Zugestellt“ |

`The order information has been sent` und `货物电子信息已经收到` bedeuten dasselbe wie die erste Zeile, `到达收货点(包裹已入库)`, dass eine Annahmestelle von JCEX das Paket eingebucht hat. `under clearance` steht für den Einfuhrzoll und `CUSTOMS CLEARANCE COMPLETE` für dessen Abschluss. `InTransit_Arrival` und `InTransit_Departure` markieren einen Umschlagpunkt in Hongkong, und `turck transferring（卡车运输中）` (JCEXs eigene Schreibweise) ein Paket auf einem Lkw. Nach der Übergabe folgen die eigenen Zeilen des zustellenden Paketdienstes: die von USPS auf Englisch — etwa `Departed USPS Regional Facility`, `In Transit to Next Facility` und `ARRIVAL AT POST OFFICE` (alle „Unterwegs“) —, die von Colissimo auf Französisch. Colissimos `Votre colis est sur son site de distribution. Nous le préparons pour le mettre en livraison.` (im lokalen Zustellzentrum, wird für die Tour vorbereitet) zeigt Peek als „In Zustellung“ an, und `Votre colis n'a pas pu vous être remis. Il sera mis en livraison demain (hors dimanche et jours fériés).` als „Zustellversuch“. `Package has been created and is ready for pickup at the shippers location. This is a pre-transit status event code.` ist nur das Etikett des zustellenden Paketdienstes, bevor er das Paket hat: „Etikett erstellt“.

Über den einzelnen Zeilen ordnet der JCEX-Tracker jedes Paket unter einem Sammelstatus ein, den eine Legende erklärt: `收到信息` (wartet auf Abholung), `运输中` (unterwegs), `到达待取` (wartet an einem Abholpunkt), `派送途中` (in Zustellung), `投递失败` (fehlgeschlagener Zustellversuch), `可能异常` (mögliches Problem), `运输过久` (lange unterwegs ohne Zustellung) oder `查询不到` (kein Tracking gefunden).

Zwei Dinge sorgen oft für Verwirrung. JCEX verbucht die Übergabe an das Depot des zustellenden Paketdienstes als zugestellt, sodass seine Seite ein Paket als `已签收` (unterschrieben) anzeigen kann, während der lokale Paketdienst es noch hat: Lies immer die neuesten Zeilen. Und `Arrive at the delivery point` erscheint am Anfang, in China — nicht in deiner Nähe.

## Wer JCEX-Pakete zustellt

JCEX stellt im Zielland nicht selbst zu. Laut eigenen Angaben werden Kleinpakete (bis 2 kg, per Luftpost) durch die Post zugestellt, schwerere Expresssendungen durch die Post oder einen privaten Paketdienst. In Verläufen aus dem JCEX-Tracker stellte USPS in den USA zu und Colissimo, der Paketdienst von [La Poste](carrier:la-poste), in Frankreich. Für Deutschland, Österreich und die Schweiz nennt JCEX auf diesen Seiten keinen festen lokalen Partner.

JCEX veröffentlicht keine Regeln zu Zustellversuchen, Benachrichtigungskarten, Paketshops oder Lagerfristen: Dafür gelten die Regeln des zustellenden Paketdienstes, und dessen Zeilen im JCEX-Verlauf zeigen, was passiert ist. In einem französischen Verlauf vermerkte Colissimo, dass das Paket nicht zugestellt werden konnte und am nächsten Tag (außer sonntags und an Feiertagen) erneut ausgefahren würde. Peek zeigt die eigene Nummer dieses Paketdienstes nicht an; frag also den Verkäufer danach, wenn du die Zustellung umplanen musst.

## JCEX-Paket kommt nicht an oder Tracking hängt

**Wer handelt.** Die Kunden von JCEX sind die Händler, die mit JCEX versenden. JCEX bietet keinen Reklamationsweg für Empfänger an; bei einem verspäteten, verlorenen, beschädigten oder zurückgesendeten Paket wende dich daher an den Verkäufer oder den Marktplatz, über den du bestellt hast.

**Noch kein Tracking.** Der JCEX-Tracker zeigt `查询不到` ohne Zeilen sowohl bei einem neuen Frachtbrief an, den JCEX noch nicht gescannt hat, als auch bei einer unbekannten Nummer; Peek zeigt solange „Warten auf JCEX“. Führt JCEXs Antwort den Frachtbrief auf, bleibt dieser Status bis zum ersten Scan bestehen, ohne Aufforderung, die Nummer zu prüfen. Kennt JCEX die Nummer auch zwei Tage später nicht und hat kein anderer Dienst dafür geantwortet, zeigt Peek „Auch nach zwei Tagen kennt der Paketdienst diese Nummer nicht. Prüfe sie, wähle einen anderen Paketdienst oder lösche das Paket.“ und prüft danach täglich, und nach einer Woche ab dem Hinzufügen wöchentlich. In jedem Fall gilt: Ändert sich nichts, kläre die Nummer mit dem Verkäufer. Ist `Shipment order info received` einen Tag später immer noch die einzige Zeile, weist Peek darauf hin, dass der Verkäufer das Paket noch nicht an JCEX übergeben hat: Frag beim Verkäufer nach, wann es soweit ist.

**Kein Update.** JCEX gibt nicht an, ab wann ein Paket als `运输过久` gilt. Bewegt sich nichts, frag den Verkäufer — er hat die Sendung gebucht und kann bei JCEX nachhaken. Mehr dazu unter [Sendungsverfolgung aktualisiert sich nicht](guide:tracking-not-updating) und [Pakete aus China verfolgen](guide:tracking-from-china). Bei Zollstopps oder Einfuhrabgaben — in Deutschland, Österreich und dem Rest der EU gehört dazu seit dem 1. November 2026 auch eine EU-Bearbeitungsgebühr von 2 € pro Warenart unabhängig vom Warenwert (in der Schweiz als Nicht-EU-Land fällt sie nicht an) — lies [Paket beim Zoll](guide:customs).

**Zugestellt, aber nicht da.** Prüfe zuerst, ob die Zustellzeile wirklich vom zustellenden Paketdienst stammt und nicht nur JCEXs Übergabe ans Depot war. Stammt sie vom Zusteller, lies [Paket zugestellt, aber nicht erhalten](guide:delivered-not-received).

## JCEX-Kontakt und Kundenservice

Stand 21. Oktober 2026 nennt JCEX eine einzige Reihe von Kontaktwegen, alle in China:

- **Hotline:** 400-887-1083, eine chinesische Servicenummer, Montag bis Freitag 9:00 bis 18:00 Uhr chinesischer Zeit. An Wochenenden und Feiertagen geschlossen.
- **E-Mail:** cs@jcex.com.cn, in der Fußzeile der Website als Beschwerdeadresse ausgewiesen.
- **WeChat:** ein Kundenservice-Konto, in der Fußzeile mit denselben Geschäftszeiten genannt.
- **Hauptsitz:** Jiacheng International, 8th floor, Building T4, Tiehui Development Centre, 520 Jiuhe Road, Shangcheng District, Hangzhou, Zhejiang, China.

Die Kontaktseite führt außerdem Niederlassungen in China auf, die sich an Händler richten, aber keinen Kontakt für Empfänger im Ausland. Für deine Bestellung wende dich an den Verkäufer, für die Zustellung an den lokalen Paketdienst.

## Ein JCEX-Paket mit Peek verfolgen

[Peek](/) erkennt JCEX sofort an einer `JCZ…DH`-Nummer, die kein anderer Paketdienst im Katalog teilt. Auch ein Link zu track.jcex.com mit `?nums=` und der Nummer lässt sich direkt einfügen. Peek verfolgt nur JCEXs JCZ-Frachtbriefnummern; bei einer Bestellnummer frag den Verkäufer nach dem Frachtbrief.

Peek liest JCEXs eigenes Tracking aus und zeigt den Status, den Verlauf mit dem Ort jedes Scans auf einer Karte, die Zustellzeit und das Zielland. Wo JCEX einen englischen Text liefert, zeigt Peek diesen an, und die Zeilen des zustellenden Paketdienstes so, wie er sie geschrieben hat — abgesehen von genauen Treffern wie `Delivered`, die in Peeks eigenen Worten erscheinen. `Arrive at the delivery point` und die Übergabe ans Depot zählen als unterwegs, nicht als abholbereit oder zugestellt. Jede Scan-Zeit wird nach der Uhrzeit gelesen, die JCEX ihr mitgibt (chinesische Zeit oder Zeit deines Landes), und in deiner eigenen Zeitzone angezeigt. Einträge mit einem Datum nach der Abfrage sowie vorab eingetragene Zeitpläne, die JCEX manchmal bis zur Übergabe vorausfüllt, sind keine Scans: Peek schließt sie aus den Scans aus und zeigt wiederholte JCEX-Zeilen nur einmal. Eine vorausdatierte JCEX-Zeile zählt nie als Scan: Sie ändert weder Status noch voraussichtliches Datum und löst keine Benachrichtigung aus. Der Verlauf beginnt dann, bis das Paket zugestellt oder zurückgesendet ist, mit „Andere Seiten zeigen vielleicht mehr Schritte, aber das sind nur Vorhersagen.“, und über „Anzeigen“ erscheinen diese Einträge ausgegraut mit „Vorhergesagt für“ und ihrem Tag.

Da JCEX keinen zustellenden Paketdienst benennt und Peek die zweite Nummer nicht liest, bleibt Peek bis zum Schluss bei der JCEX-Nummer, statt auf USPS oder Colissimo umzuschalten, deren Scans über JCEXs Verlauf einlaufen. Ein voraussichtliches Lieferdatum, den Tarif oder einen Zustellnachweis zeigt Peek für JCEX nicht an.

Peek prüft bis zu alle 10 Minuten — oder ab 6:00 Uhr Ortszeit an dem Tag, an dem das Paket in Zustellung geht, alle 2 Minuten —, solange JCEXs eigener Tracker antwortet (über Tracking-Dienste alle 15 Minuten). Du kannst dich über „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“ benachrichtigen lassen. Nach zwei Tagen ohne Neuigkeiten (12 Stunden, solange das Paket noch „Etikett erstellt“ oder „Beim Paketdienst eingegangen“ zeigt) prüft Peek stündlich. Peek kann weder JCEX noch den Verkäufer für dich kontaktieren.

## Fragen zur JCEX-Sendungsverfolgung

### Ist JCEX seriös?

Ja. JCEX ist die Marke von Jiacheng International Logistics, gegründet im Jahr 2000 in Hangzhou, China, an der das in Shanghai börsennotierte Unternehmen Huamao Logistics seit 2021 beteiligt ist. JCEX bezeichnet sich als ausgewiesenen Logistikdienstleister für Plattformen wie Alibaba.com, Cainiao und DHgate.

### Was bedeutet „Arrive at the delivery point“ bei JCEX?

Es bedeutet, dass das Lager von JCEX in China das Paket entgegengenommen hat. Der Eintrag erscheint am Anfang des Verlaufs, noch vor dem Flug, und hat nichts mit einem Zustellpunkt in deiner Nähe zu tun.

### Warum zeigt JCEX zugestellt an, obwohl ich mein Paket nicht habe?

JCEX verbucht die Übergabe an das Depot des zustellenden Paketdienstes als zugestellt: Die Zeile `Delivered to warehouse, waiting to be scanned（已交仓，待扫描）` bedeutet, dass der lokale Paketdienst es nun hat. Warte auf dessen eigene Zeilen bis zu `Delivered`.

### Wer stellt JCEX-Pakete in den USA zu?

In den US-Verläufen aus dem JCEX-Tracker hat USPS zugestellt. Seine eigenen Zeilen wie `ARRIVAL AT POST OFFICE` und `out for delivery` erscheinen direkt im Verlauf von JCEX.

### Wie lange dauert die Lieferung mit JCEX?

Für seine Kleinpaket-Linie nennt JCEX Händlern 13 bis 16 Werktage in die USA, 8 bis 11 Werktage nach Großbritannien und 9 bis 13 Werktage nach Europa. Maßgeblich bleibt die Lieferangabe des Shops für deine Bestellung.

:::sources
- [JCEX: 全球轨迹查询平台 (Sendungsverfolgung)](https://track.jcex.com/) – nur chinesischer Tracker, bis zu 100 Nummern, Sammelstatus und Legende, Scan-Texte, beobachtete Zusteller, leere zweite Nummer, Übergabe als zugestellt verbucht
- [JCEX: 联系我们 (Kontakt)](https://www.jcex.com/bangzhuzhongxin/lianxiwomen/index.html) – Hotline, E-Mail, Geschäftszeiten, Hauptsitz, Niederlassungen
- [JCEX: 佳成国际物流 (Startseite)](https://www.jcex.com/) – Beschwerde-E-Mail, WeChat-Kundenservice, Zeiten, Beteiligung von Huamao
- [JCEX: 佳成介绍 (Über uns)](https://www.jcex.com/guanyujiacheng/jiachengjieshao/index.html) – gegründet 2000, grenzüberschreitende Onlinehändler, Huamao 2021
- [JCEX: Englische Website](https://enjc.jcex.com/) – Jiacheng International Logistics Co., Ltd, bediente Plattformen, Beschwerde-E-Mail
- [JCEX: 国际小包 (Kleinpakete)](https://www.jcex.com/chanpinyufuwu/guojixiaobaoyukuaidi/index.html) – bis 2 kg, Luftpost, Zustellung durch die Post, Laufzeiten
- [JCEX: 国际快递与快运 (Express und Fracht)](https://www.jcex.com/chanpinyufuwu/guojikuaiyun/1438101270410715138.html) – Zustellung durch die Post oder private Paketdienste
- [Europäische Kommission: EU-Zollreform (englisch)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 2 € EU-Bearbeitungsgebühr seit 1. November 2026
- [Europäische Kommission: Guidance on the 3 EUR customs duty and Union handling fee (PDF, englisch)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 2 € pro im Fernabsatz verkaufter Warenart unabhängig vom Wert
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – was Peek zeigt, Nummernformat, Lager- und Übergabezeilen, Scan-Wortlaute, übernommene Zeilen von USPS und Colissimo, vorausdatierte Einträge
:::
