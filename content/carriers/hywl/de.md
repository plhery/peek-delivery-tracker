---
title: HYWL Sendungsverfolgung: Huiyi Logistics TRK & Status
description: HYWL-Paket (Huiyi Logistics, 汇一物流) aus China verfolgen: TRK-Nummern, was englische und chinesische Scans bedeuten, vorauseilende Daten und wer hilft.
slug: hywl-sendungsverfolgung
published: 2026-11-28
updated: 2026-11-28
---

Huiyi Logistics (HYWL, 汇一物流) ist 深圳市汇一物流有限公司, ein Logistikunternehmen in Shenzhen, China, das Pakete für Onlinehändler ins Ausland verschickt. Hat ein Shop deine Bestellung mit HYWL versendet, besteht deine Nummer aus `TRK` und zwölf weiteren Zeichen — oder aus vier Buchstaben, zwei Ziffern, einem `D`, vier Ziffern und vier Buchstaben: Füge sie im Feld oben ein. HYWLs eigener Tracker auf seiner Website ist auf Chinesisch, und seine Schritte erscheinen auf Englisch, Chinesisch oder in beiden Sprachen. HYWL nennt das Unternehmen, das in Deutschland, Österreich oder der Schweiz zustellt, nicht.

## HYWL-Sendungsnummer: wie sie aussieht

HYWL-Nummern gibt es in zwei Formen, beide 15 Zeichen lang. Mit erfundenen Zeichen:

| Nummer | Was es ist |
| --- | --- |
| `TRK48A29C10E57F` | `TRK` und zwölf Zeichen, jeweils eine Ziffer oder ein Buchstabe von `A` bis `F`: die Form, die HYWLs Website als Beispiel nennt. |
| `KMPQ48D2910BRTV` | Vier Buchstaben, zwei Ziffern, `D`, vier Ziffern und vier Buchstaben: die andere Form von HYWL. |
| `TRK48A29C10E57` | Nur elf Zeichen nach `TRK`: kein HYWL-Format. Prüfe, ob ein Zeichen fehlt. |

Kein anderer Paketdienst im Peek-Katalog verwendet eine dieser Formen, aber beide sind nur schwache Treffer — deshalb fragt Peek zuerst bei HYWLs Tracker nach, bevor es eine Nummer HYWL zuordnet (mehr dazu unten). Leerzeichen, Bindestriche und Kleinschreibung spielen in Peek keine Rolle. HYWLs eigener Tracker liest die Nummer exakt wie getippt: Gib sie dort in Großbuchstaben ein.

**Wo du sie findest.** Der Verkäufer bucht das Paket bei HYWL und gibt dir die Nummer in der Versand-E-Mail oder auf deiner Bestellseite weiter. Findest du sie nicht, lies [Sendungsnummer finden](guide:find-tracking-number).

**Ein Tracking-Link.** Ein Link zu HYWLs Tracker, der auf `?number=` oder `?trackingNumber=` gefolgt von einer Nummer endet, öffnet genau dieses Paket. Füge den ganzen Link in Peek ein: Peek liest die Nummer aus und ordnet das Paket sofort Huiyi Logistics zu.

## HYWL Tracking-Status erklärt

HYWLs Datensatz gibt jedem Schritt einen Status und einen Wortlaut. Der Tracker zeigt oben den Paketstatus und darunter Uhrzeit, Wortlaut und Ort jedes Schritts, den ältesten zuerst. Die Status:

| HYWL-Status | Was das für dich bedeutet | Peek zeigt |
| --- | --- | --- |
| `Picked Up` | HYWL hat das Paket. | „Beim Paketdienst eingegangen“ |
| `In Transit` | Alles vom Verlassen der HYWL-Annahmestelle in China bis zum Weg in deinem Land. | „Unterwegs“ |
| `Customs` | Ein Zolldurchlauf. Die im Peek-Code bekannten Einträge bedeuten, dass der chinesische Ausfuhrzoll abgeschlossen ist. | „Unterwegs“ für diese, „Beim Zoll“ für alle anderen |
| `Out for Delivery` | In Zustellung. | „In Zustellung“ |
| `Delivered` | Zugestellt. | „Zugestellt“ |

Die Wortlaute verraten mehr — in der Reihenfolge, in der ein Paket sie durchläuft:

| HYWL meldet | Was das für dich bedeutet | Peek zeigt |
| --- | --- | --- |
| `Shipment accepted, received by SZHYWL`, `SZHYWL已收取邮件` | HYWL (SZHYWL) hat das Paket angenommen. Es ist der erste Schritt, mit einem Ort und einem Bürokürzel. | „Beim Paketdienst eingegangen“ |
| `Departed Pickup Point`, `离开收寄点` | Es hat die Stelle verlassen, an der HYWL es entgegengenommen hat. | „Unterwegs“ |
| `Arrival at Origin Processing Center`, `Departure from Origin Processing Center` | In einem Bearbeitungszentrum in China eingetroffen, dann dort abgegangen. | „Unterwegs“ |
| `邮件到达处理中心`, `邮件离开处理中心` | In einem Bearbeitungszentrum eingetroffen, dann abgegangen. | „Unterwegs“ |
| `Received by Carrier`, `承运商接收` | Der Frachtführer auf HYWLs Linie hat das Paket von HYWL übernommen. Es handelt sich nicht um eine neue Sendung. | „Unterwegs“ |
| `Departure from`, `启运` | Von dem in Klammern genannten Ort abgegangen. | „Unterwegs“ |
| `In Transit`, `快件在该国内转运中, 干线中转` | Unterwegs. Der chinesische Text sagt, dass es sich innerhalb Chinas im Hauptlauf befindet. | „Unterwegs“ |
| `Customs Clearance Completed`, `出口清关完成` | Der chinesische Ausfuhrzoll ist abgeschlossen. Nicht der Zoll in deinem Land. | „Unterwegs“ |
| `Departure from International Hub`, `Arrival at Transit Hub` | Internationalen Umschlagpunkt verlassen, dann einen Transit-Hub unterwegs erreicht. | „Unterwegs“ |
| `Arrival at` | Im Zielland angekommen, das per Ländercode in Klammern steht. | „Unterwegs“ |
| `邮件在该国内转运中` | Unterwegs im Zielland. | „Unterwegs“ |
| `Arrival at Processing Center`, `Departure from Processing Center` | In einem Bearbeitungszentrum in deinem Land eingetroffen, dann dort abgegangen, mit deiner Stadt in Klammern. | „Unterwegs“ |
| `Dispatching`, `出门投递` | In Zustellung. | „In Zustellung“ |
| `Delivered`, `已妥投` | Zugestellt. | „Zugestellt“ |

**Orte.** HYWLs Schritte in deinem Land nennen Stadt und Postleitzahl, der erste Schritt eine Stadt und ein Bürokürzel. Peek behält nur die Stadt und schneidet Namen nach `signed by` oder `签收人` ab.

**Zweisprachige Schritte.** Schreibt HYWL einen Schritt zuerst auf Englisch und dann auf Chinesisch mit demselben Ort (zum Beispiel `Dispatching` und danach `出门投递`), behält Peek nur die englische Hälfte samt dem Ort in eckigen Klammern.

**Uhrzeiten.** HYWLs Tracker druckt jede Uhrzeit ohne Zeitzone. Schritte mit chinesischem Ortsnamen oder in Shenzhen, Guangzhou, Dongguan, Huizhou oder Hongkong gelten nach Pekinger Zeit: Im Winter ist 20:00 Uhr dort 13:00 Uhr in Deutschland, Österreich und der Schweiz (im Sommer 14:00 Uhr).

**Vorausdatierte Schritte.** Laut HYWLs Tracking-Seite legen seine Mitarbeiter die Aufträge an und pflegen die Verläufe in einem internen Backoffice. Ein Datensatz kann Schritte enthalten, deren Zeitpunkt noch vor dir liegt, und HYWLs Tracker zeigt sie trotzdem an — unter einem Status, der ebenfalls voraus sein kann. Peek hält jeden solchen Schritt aus HYWLs Tracker zurück, bis seine Zeit verstrichen ist: bei Schritten in China nach Pekinger Zeit, bei allen anderen gelesen als UTC. Peek richtet sich nach den behaltenen Schritten, nicht nach HYWLs Gesamtstatus. Ein vorausdatierter Schritt hat noch nicht stattgefunden, selbst wenn dort `Delivered` steht.

## Wer HYWL-Pakete zustellt

HYWLs Website gibt an, Linien mit Auslandszustellung anzubieten, nennt aber kein Unternehmen, das zustellt, und der HYWL-Tracker zeigt weder diesen Paketdienst noch dessen Sendungsnummer an. Auch Peeks Code nennt für kein Land einen Zusteller. Peek weiß daher nicht, wer HYWL-Pakete in Deutschland, Österreich oder der Schweiz zustellt, und kann die Sendung nicht an diesen Dienst übergeben.

HYWLs eigene Schritte laufen bis zur Zustellung: `Dispatching`, dann `Delivered`. Peek fand keine Lieferzeiten von HYWL und keine Regeln zu Zustellversuchen, Benachrichtigungskarten, Paketshops oder Lagerfristen: Dafür gelten die Regeln des zustellenden Unternehmens. Läuft bei der Zustellung etwas schief, frag den Verkäufer, welches Unternehmen deine Bestellung liefert und unter welcher Nummer, und lies dann [Zustellversuch verpasst](guide:missed-delivery).

## HYWL-Paket kommt nicht an oder Tracking hängt

**`暂时无法获取轨迹，请确认单号是否正确或稍后再试。`** HYWLs Tracker zeigt diese Meldung, wenn er die Nummer nicht findet oder nicht antworten kann: „Tracking derzeit nicht abrufbar, prüfe ob die Nummer stimmt oder versuche es später erneut.“ Prüfe die Nummer Zeichen für Zeichen in Großbuchstaben. Kommt nach ein paar Tagen nichts, frag den Verkäufer, ob das Paket wirklich abgeschickt wurde.

**`已查到单号，暂无更多轨迹节点。`** „Nummer gefunden, vorerst keine weiteren Tracking-Schritte“: Der Auftrag existiert, aber HYWL hat noch keine Schritte eingetragen. Frag den Verkäufer, wann er das Paket übergeben hat.

**HYWLs Tracker zeigt mehr als Peek.** Peek hält Schritte im HYWL-Tracker zurück, deren Zeitpunkt nach der Abfrage liegt; deshalb kann HYWLs Seite Einträge zeigen, die Peek weglässt. Prüfe ihre Uhrzeiten: Ein vorausdatierter Schritt ist noch nicht passiert. Orientiere dich an dem Lieferzeitraum des Shops und frag beim Verkäufer nach, sobald dieser verstrichen ist. Mehr unter [Sendungsverfolgung aktualisiert sich nicht](guide:tracking-not-updating) und [Pakete aus China verfolgen](guide:tracking-from-china).

**Nichts nach `Arrival at`.** Das Paket hat dein Land erreicht. In Deutschland, Österreich und der übrigen EU fällt für Online-Bestellungen von außerhalb der EU seit dem 1. Juli 2026 ein Zoll von 3 € pro Warenart an, wenn die Sendung höchstens 150 € wert ist, und seit dem 1. November 2026 eine EU-Bearbeitungsgebühr von 2 € pro Warenart unabhängig vom Wert (in der Schweiz als Nicht-EU-Land gelten diese EU-Sätze nicht). HYWL sagt nicht, wer diese Abgaben zahlt. Wirst du zu Einfuhrabgaben aufgefordert, lies [Paket beim Zoll](guide:customs). Bleibt die Sendung tagelang liegen, frag den Verkäufer, welches Unternehmen zustellt und unter welcher Nummer.

**`Delivered`, aber nichts angekommen.** Prüfe zuerst die Uhrzeit des Schritts: Liegt sie noch in der Zukunft, hat die Zustellung noch nicht stattgefunden. Ist sie bereits vorbei, melde es dem Verkäufer und frag, welches Unternehmen zugestellt hat. Siehe [Paket zugestellt, aber nicht erhalten](guide:delivered-not-received).

**Verloren oder zurückgesendet.** Der Verkäufer hat das Paket bei HYWL gebucht; deshalb ist der Verkäufer — oder der Marktplatz — dein Ansprechpartner für eine Erstattung oder Ersatzlieferung. Siehe [Paket verloren](guide:lost-parcel) und [Zurück an Absender](guide:returned-to-sender).

## HYWL-Kontakt und Kundenservice

Stand 30. Oktober 2026 ist die Website von HYWL auf Chinesisch und richtet sich an Händler, die Sendungen buchen. Sie nennt eine Adresse im Bezirk Yantian in Shenzhen sowie `待补充` („wird ergänzt“) dort, wo Telefonnummer und E-Mail stehen sollten.

Der Verkäufer hat das Paket bei HYWL gebucht und ist dein Ansprechpartner für deine Bestellung — und der Marktplatz, falls der Verkäufer nicht antwortet.

## Ein HYWL-Paket mit Peek verfolgen

[Peek](/) kann eine HYWL-Nummer nicht allein an ihrer Form sicher erkennen. Füge sie trotzdem ein: Peek verfolgt mehr als 4.500 Paketdienste. Beim Hinzufügen fragt Peek bei HYWLs Tracker nach („Tracking-Dienste werden geprüft…“) und zeigt „Huiyi Logistics hat dieses Paket.“, wenn der Tracker die Nummer kennt. Andernfalls tippe auf „Ändern“ und wähle Huiyi Logistics unter „Passt zu dieser Nummer“ (auch die Suche nach `HYWL` oder `汇一物流` findet es), oder nutze „Paketdienst ändern“ bei einem bereits hinzugefügten Paket.

Fügst du eine `TRK`-Nummer hinzu, ohne einen Paketdienst zu wählen, und HYWL listet sie noch nicht, zeigt Peek „Wahrscheinlich Huiyi Logistics“ und „Warten auf den ersten Scan von Huiyi Logistics“ und fragt weiter nach. Nach einer Woche bittet es dich, die Nummer zu prüfen oder einen anderen Paketdienst zu wählen.

Peek zeigt den Status, jeden Schritt mit seinem Ort und das Zielland, wenn HYWLs Datensatz es angibt. Schritte in China liest es nach Pekinger Zeit, für die übrigen bestimmt es die Uhr nach ihrem Ort oder dem jeweiligen Abschnitt und zeigt jede Zeit in deiner Geräte-Zeitzone, die neueste zuerst; auf peektracker.com sowie in den iPhone- und Android-Apps endet der Verlauf mit „Uhrzeiten in deiner Zeitzone:“ und deren Namen. Postleitzahlen und das Ursprungsland lässt es weg. HYWL liefert kein voraussichtliches Lieferdatum, kein Gewicht, keinen lokalen Zusteller samt Nummer, keinen Absender- oder Empfängernamen und keinen Zustellnachweis — Peek zeigt daher nichts davon an.

Ist HYWLs Tracker nicht erreichbar, fragt Peek stattdessen Tracking-Dienste ab und zeigt deren Verlauf in deren Wortlaut. Deren Kopien können vorausdatierte Schritte enthalten, aber ein Schritt, der mehr als eine Stunde nach Peeks Abfrage datiert ist, wird nie zum Paketstatus. Prüfe also die Uhrzeit jedes Schritts.

Peek bleibt bei HYWLs Tracker: Nennt dir der Verkäufer die Nummer des zustellenden Unternehmens, füge sie als eigenes Paket hinzu. Solange du das Paket geöffnet hast — oder am Zustelltag mit aktivierten Benachrichtigungen — prüft Peek bis zu alle 10 Minuten. Bei `Dispatching` oder `出门投递` zeigt das Paket „In Zustellung“, und unter denselben Bedingungen prüft Peek bis zu alle 2 Minuten, allerdings nur solange HYWLs eigener Tracker antwortet, am Tag dieses Schritts und ab 6:00 Uhr Ortszeit; antworten stattdessen Tracking-Dienste, prüft Peek stündlich und an diesem Tag bis zu alle 15 Minuten, nur solange du das Paket geöffnet hast oder Benachrichtigungen an sind. Sonst sowie nach zwei Tagen ohne Neuigkeiten (12 Stunden, solange das Paket noch „Etikett erstellt“ oder „Beim Paketdienst eingegangen“ zeigt) prüft Peek stündlich. Benachrichtigungen gibt es für „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“: „Wichtige Schritte“ umfasst `Dispatching` und `Delivered`, nicht HYWLs Ausfuhrzoll oder Sortierung. Peek kann weder HYWL noch den Verkäufer für dich kontaktieren.

## Fragen zur HYWL-Sendungsverfolgung

### Was ist HYWL (汇一物流)?

深圳市汇一物流有限公司 (Huiyi Logistics), ein Logistikunternehmen in Shenzhen, China. Nach eigenen Angaben wurde es 2013 gegründet und verschickt Pakete für grenzüberschreitende Onlinehändler und Handelsunternehmen ins Ausland. Seine Nummern beginnen mit `TRK` oder enthalten ein `D` zwischen Ziffern.

### Wo kann ich ein HYWL-Paket verfolgen?

Im Feld oben oder im chinesischsprachigen HYWL-Tracker auf szhywl.it.com: Gib die Nummer in Großbuchstaben ein und drücke `官网查询`. Der andere Button, `17TRACK 查询`, öffnet dieselbe Nummer bei 17TRACK, einem Tracking-Dienst.

### Was bedeuten 出门投递 und 已妥投 beim HYWL-Tracking?

`出门投递` bedeutet in Zustellung — `Dispatching` in HYWLs englischem Wortlaut. `已妥投` bedeutet zugestellt (`Delivered`). Prüfe die Uhrzeit des Schritts: HYWLs Tracker zeigt auch vorausdatierte Schritte an, die noch nicht stattgefunden haben.

### Wer stellt HYWL-Pakete in Deutschland, Österreich und der Schweiz zu?

HYWL nennt keinen Zusteller, und sein Tracker gibt weder den Paketdienst noch dessen Nummer an; auch Peeks Code kennt keinen. Frag den Verkäufer, welches Unternehmen deine Bestellung ausliefert.

### Wen kontaktiere ich bei einem vermissten HYWL-Paket?

Den Verkäufer, bei dem du bestellt hast: Er hat das Paket bei HYWL gebucht. HYWLs Website nennt weder Telefonnummer noch E-Mail, sondern nur `待补充` („wird ergänzt“).

:::sources
- [Huiyi Logistics: 深圳市汇一物流有限公司 (Startseite)](https://www.szhywl.it.com/) – Firmenname, Shenzhen, 2013, Kunden und Dienste, Auslandszustellung, beide Tracker-Buttons, Adresse in Yantian, Telefon und E-Mail „wird ergänzt“
- [Huiyi Logistics: 物流轨迹查询 (Sendungsverfolgung)](https://www.szhywl.it.com/track.html) – öffentlicher Tracker, Aufträge und Verlauf im Backoffice gepflegt, beide Meldungen, Zeiten ohne Zeitzone, Direktlink für ein Paket
- [Europäische Kommission: EU Customs Reform (englisch)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 3 € Zoll pro Warenart bis 150 € seit 1. Juli 2026, 2 € Bearbeitungsgebühr seit 1. November 2026
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Namen, Nummernformen und schwache Erkennung, Links, Status, Wortlaute und ihre Stufen, englische Hälfte zweisprachiger Schritte, Orte und Postleitzahlen, Pekinger Zeit, Zurückhalten vorausdatierter Schritte, kein Zusteller genannt, was Peek anzeigt und weglässt
:::
