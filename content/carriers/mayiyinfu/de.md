---
title: Mayi Yinfu Sendungsverfolgung: MY-Nummern, Flüge und DHL
description: Mayi-Yinfu-Paket (蚂蚁银服) aus Festlandchina verfolgen: Nummern MY…US und MYYF…CN, Flugstatus, vorab eingetragene Zeilen und Übergabe an DHL in Europa.
slug: mayi-yinfu-sendungsverfolgung
published: 2026-11-21
updated: 2026-11-21
---

Mayi Yinfu (`蚂蚁银服`) ist der Kurzname von `广东蚂蚁银服国际物流有限公司` (*Mayi Yinfu International Logistics*), einem Logistikweiterleiter in der Provinz Guangdong in Festlandchina für internationale Kleinpakete und Expresssendungen. Seine Sendungsnummern bestehen aus `MY`, zehn Ziffern und `US` (oder `MYYF`, acht Ziffern und `CN`): Füge deine oben in das Feld ein oder gib sie im Feld `快递查询` auf `mayiyinfu.com.cn` ein. Mayi Yinfu fliegt Pakete in deine Zielregion und übergibt sie dort an einen Zustelldienst – nach Europa an Deutsche Post [DHL](carrier:dhl).

## Mayi-Yinfu-Sendungsnummer: Aufbau und Beispiele

`MY` steht für `蚂蚁` (*mǎyǐ*, Ameise). Diese Muster kennt Peek (mit erfundenen Ziffern):

| Nummer (erfunden) | Was das ist |
| --- | --- |
| `MY4829105736US` | `MY`, 10 Ziffern und `US` (14 Zeichen): eindeutige Mayi-Yinfu-Sendungsnummer. |
| `MYYF48291057CN` | `MYYF` (Initialen von `蚂蚁银服`), 8 Ziffern und `CN` (14 Zeichen): zweites Nummernformat auf Mayi Yinfus Tracker. |
| `MY482910573US` | Nur 9 statt 10 Ziffern: Peek meldet: „Das beginnt wie eine Sendungsnummer von Mayi Yinfu, aber ein Zeichen fehlt. Vielleicht ist es ein Tippfehler: Vergleiche die Nummer mit deiner Versandnachricht.“ |
| `RR482910576CN` | Zwei Buchstaben, 9 Ziffern und `CN`: eine Postsendung von [China Post](carrier:china-post), nicht Mayi Yinfu. |

Groß-/Kleinschreibung und Leerzeichen spielen in Peek keine Rolle. Da Mayi Yinfu für gewerbliche Versender arbeitet, erhältst du die Nummer vom Online-Shop ([Sendungsnummer finden](guide:find-tracking-number)); Mayi Yinfus eigener Tracker findet Sendungen auch über die exakt eingegebene Händlerreferenz (inklusive Bindestrichen).

## Mayi-Yinfu-Tracking-Status erklärt

Mayi Yinfu veröffentlicht keine eigene Statusübersicht. Eigene Einträge stehen auf Chinesisch (oft gefolgt von einer englischen Übersetzung), nach der Übergabe kopiert der Tracker die englischen Meldungen des Endzustellers:

| Status bei Mayi Yinfu | Bedeutung für dein Paket | Peek zeigt |
| --- | --- | --- |
| `货物电子信息已经收到` | Elektronische Auftragsdaten auf der Hualei-Plattform (`华磊`, die auch BYHD nutzt) eingegangen: Auftrag angelegt, noch kein physischer Scan. | „Etikett erstellt“ |
| `The instruction data for this shipment have been provided by the sender to DHL electronically` | Von DHL übernommen: Etikettendaten elektronisch an DHL übermittelt, noch kein Paket bei DHL. | „Etikett erstellt“ |
| `已入库,到达收货点` | Im Annahmelager eingebucht: Mayi Yinfu hat dein Paket. | „Beim Paketdienst eingegangen“ |
| `进仓时间：…；Warehouse entry time:…`, `已出库,货物离开操作中心`, `国内清关完成` | Zeit des Lagereingangs, Sortierzentrum verlassen bzw. Ausfuhrverzollung in Festlandchina abgeschlossen. | „Unterwegs“ |
| `货物已交机场` | Am Abflughafen übergeben (**nicht** an dich zugestellt, trotz englischem *delivered to the airport*), gefolgt von `货物已交机场，等待安排航班中The goods have been delivered to the airport and are awaiting the arrangement of flights.` oder dem geplanten Flugtag. | „Unterwegs“ |
| `航班延误` | Flugverspätung mit Angabe des nächsten geplanten Flugtags. | „Unterwegs“ |
| `航班已起飞`, `航班落地`, `头程起飞`, `头程落地`, `二程起飞` | Abflug ab Startflughafen und Landung (`航班已起飞`, `航班落地`), Start und Landung des ersten Flugs (`头程起飞`, `头程落地`) sowie Start des Anschlussflugs (`二程起飞`, im Tracker fälschlich mit *Flight 202* übersetzt). | „Unterwegs“ |
| `航班落地，拆板清关The flight landed, and the board was dismantled and cleared` | Im Zielgebiet gelandet; Paletten werden aufgelöst und zur Einfuhrverzollung vorgeführt. | „Beim Zoll“ |
| `清关完成，待转运Clearance has been completed and it is awaiting transfer.`, `已转运transferred` | Verzollung erledigt und Übergabe an den Endzusteller, dessen Sendungsnummer nun im Datensatz steht. | „Unterwegs“ |
| `The international shipment has been processed in the parcel center of origin`, `The international shipment has been processed in the export parcel center` | Von DHL kopiert: Bearbeitung in den deutschen DHL-Paketzentren. | „Unterwegs“ |
| `The shipment has arrived in the destination country/destination area.` | Im Zielland eingetroffen (endet teils mit einem Klammer-Link zum nationalen Zusteller). | „Unterwegs“ |
| `The shipment has been loaded onto the delivery vehicle` | Ins Zustellfahrzeug geladen. | „In Zustellung“ |
| `The shipment has been successfully delivered` | Erfolgreich zugestellt. | „Zugestellt“ |

**Skriptzeilen (`CO_C_CR`).** Alleinstehende Zeilen wie `国内清关中` (Ausfuhrzoll), `已排航班` (Flug gebucht), `起运地航班起飞` (Abflug), `已到达目的国` (im Zielland) und `清关完成` (verzollt) stammen aus einem automatischen Skript, das Flüge und Ankunft melden kann, bevor das Paket überhaupt am Flughafen ist – Peek zählt sie nicht als Scans ([Tracking-Status verstehen](guide:tracking-statuses)).

## Wer liefert Mayi-Yinfu-Pakete aus?

Auf seiner Website nennt Mayi Yinfu keine Partner, doch ab `已转运transferred` zeigt der Tracker die Anschlussnummer. **Nach Europa** ist das eine Nummer von Deutsche Post [DHL](carrier:dhl), mit der DHL das Paket bis zur Haustür verfolgt (am Ende der DHL-Ankunftszeile führt oft ein Link zum Zusteller deines Landes). **In andere Regionen** erkennst du den Partner häufig über die [Formate von Sendungsnummern](guide:tracking-number-formats) oder erfragst ihn beim Händler. Für Zustellversuche, Paketshops und Lagerfristen gelten die Regeln des Endzustellers; bei Zollforderungen hilft [Paket hängt beim Zoll](guide:customs), bei verdächtigen Zahlungslinks [Gefälschte Paket-SMS erkennen](guide:delivery-scam-texts).

## Paket kommt nicht an oder Tracking hängt

- **Nur die ersten Zeilen sichtbar:** `货物电子信息已经收到` (auch zusammen mit der elektronischen DHL-Zeile) heißt, dass der Verkäufer die Sendung angemeldet, aber noch nicht eingeliefert hat. Frag nach einigen Tagen beim Shop nach.
- **Stillstand nach `已转运transferred`:** Mayi Yinfus Teil ist beendet – verfolge ab jetzt die Nummer des Endzustellers ([Pakete aus China verfolgen](guide:tracking-from-china), [Warum sich das Tracking nicht aktualisiert](guide:tracking-not-updating)).
- **Unbekannte Nummer:** Kennt Mayi Yinfus Tracker eine Nummer nicht, gibt er trotzdem einen leeren Datensatz mit der Nummer unter `参考号` (Referenz) zurück. Peek wertet das als nicht gefundene Nummer: Es zeigt „Warten auf Mayi Yinfu“ und bittet dich, wenn die Nummer zwei Tage lang unbekannt bleibt, sie zu prüfen – gleiche die Zeichen daher genau mit der Shop-Nachricht ab.
- **Verlust, Schaden oder Rücksendung:** Ein Reklamationsverfahren nennt Mayi Yinfu nicht; wende dich für Ersatz oder Rückerstattung an den Händler oder Marktplatz ([Paket verloren](guide:lost-parcel), [Zurück an den Absender](guide:returned-to-sender), [Zugestellt, aber nicht erhalten](guide:delivered-not-received)).

## Kontakt und Kundenservice von Mayi Yinfu

Die rein chinesischsprachige Website `mayiyinfu.com.cn` nennt lediglich eine Mobilnummer in Festlandchina, **`+86 13705996996`** (`13705996996`), im Kopfbereich als landesweite Servicehotline (`全国服务热线`) sowie im Kasten `在线咨询`. Die Kontaktseite (`contact.html`) sowie Firmenprofil, Rechtshinweise und Datenschutzseite sind leer (keine Anschrift, keine E-Mail, kein Formular). Dein Ansprechpartner ist immer der Verkäufer.

## Mayi-Yinfu-Pakete mit Peek verfolgen

Fügst du eine `MY…US`-Nummer oder einen Link `mayiyinfu.com.cn/trackIndex.htm` mit `documentCode=` in [Peek](/) ein, wird sofort Mayi Yinfu gesetzt („Erkannter Paketdienst“). Bei `MYYF…CN` zeigt Peek „Tracking-Dienste werden geprüft…“ und nach Treffer „Mayi Yinfu hat dieses Paket.“. Für eine bindestrichfreie Händlerreferenz wählst du Mayi Yinfu über „Ändern“ (`蚂蚁银服` oder `MYYF` finden ihn ebenfalls; Referenzen mit Bindestrichen suchst du direkt auf `mayiyinfu.com.cn`, da Peek Bindestriche entfernt).

**Scans, Uhrzeiten und vorab eingetragene Schritte.** Nennt eine Flugzeile einen noch bevorstehenden Tag, zeigt Peek nur den englischen Schritt (etwa `The first leg has departed`) ohne das Zukunftsdatum und lässt Namen, Adressen, Postleitzahlen, Gewicht, Gebühren und Fotos weg (kein voraussichtliches Lieferdatum; das bloße Kürzel `SZ`, nach ISO Eswatini, wird als Ort geleert). Scans mit Länderkürzel `CN` (Festlandchina) sowie kopierte englische DHL-Zeilen mit Ländercode – sobald DHL zugeordnet ist – liest Peek als Pekinger Zeit (`UTC+8`) und rechnet sie in deine Gerätezeitzone um („Uhrzeiten in deiner Zeitzone: …“); übrige Zeilen behalten ihre Ortszeit. Zeilen, die mehr als 12 Stunden vor ihrer angegebenen Uhrzeit angelegt wurden oder mehr als 6 Stunden davor in derselben Sekunde wie eine solche Zeile, gelten wie die `CO_C_CR`-Zeilen als Prognose: Sie zählen nicht als Scan und ändern den Status nicht. Der Verlauf beginnt dann mit „Andere Seiten zeigen vielleicht mehr Schritte, aber das sind nur Vorhersagen.“, die Schritte stehen ausgegraut hinter „Anzeigen“. Eine Zeile mit einer Uhrzeit nach Peeks Abfrage erscheint erst, wenn diese Zeit vorbei ist.

**Automatische Übergabe an DHL und Abfragetakt.** Gehört die Transfernummer eindeutig zu [DHL](carrier:dhl), zeigt Peek „Zustellung durch DHL“ samt DHL-Nummer (bzw. „Bei DHL angekündigt, noch nicht eingetroffen“, solange DHL nur die Vorankündigung hat) und wechselt auf DHLs eigenes Tracking, sobald dort ein mindestens ebenso aktueller Scan vorliegt; jede andere Anschlussnummer fügst du separat als eigenes Paket hinzu. Bei geöffnetem Paket oder am Liefertag mit aktiven Mitteilungen prüft Peek bis zu alle 10 Minuten, sonst sowie nach 12 Stunden auf „Etikett erstellt“ / „Beim Paketdienst eingegangen“ oder zwei ruhigen Tagen stündlich (stehen nach zwei Tagen nur die Startzeilen da, fragt Peek zusätzlich Tracking-Dienste ab). Mitteilungen: „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“.

## Häufige Fragen zur Sendungsverfolgung von Mayi Yinfu

### Warum endet meine Mayi-Yinfu-Nummer auf `US`?

Jede `MY`-Nummer von Mayi Yinfu endet fest auf `US` (und `MYYF`-Nummern auf `CN`) – unabhängig vom Zielland. Es handelt sich weder um eine US-Sendung noch um USPS.

### Bedeutet `货物已交机场` (*delivered to the airport*), dass mein Paket da ist?

Nein: Mayi Yinfu hat die Sendung lediglich am Abflughafen für den Frachtflug übergeben. Die eigentliche Zustellung erfolgt später durch den Paketdienst in deinem Land.

### Wie lange dauert der Versand mit Mayi Yinfu?

Auf den Produktseiten von Mayi Yinfu stehen nur Platzhaltertexte ohne Laufzeitangaben – richte dich nach dem Lieferfenster, das dir der Online-Shop genannt hat.

### Wen kontaktiere ich, wenn mein Mayi-Yinfu-Paket fehlt?

Den Verkäufer oder Marktplatz, bei dem du bestellt hast: Er hat Mayi Yinfu beauftragt, während Mayi Yinfus Website lediglich eine chinesische Mobilnummer angibt.

:::sources
- [Mayi Yinfu: 广东蚂蚁银服国际物流有限公司 (Startseite)](http://www.mayiyinfu.com.cn/Default.aspx) – Firmenname, Suchfeld 快递查询, Kundenlogin und Mobil-Hotline +86 13705996996 (全国服务热线 / 在线咨询)
- [Mayi Yinfu: 联系我们 (Kontakt)](http://www.mayiyinfu.com.cn/contact.html) – Kontaktseite ohne Anschrift, E-Mail oder Formular über die Hotline hinaus
- [Mayi Yinfu: 国际小包 (Internationale Kleinpakete)](http://www.mayiyinfu.com.cn/products/category-1.html) – Platzhaltertext ohne Laufzeiten oder Partnerangaben
- [Universal Parcel Scraper: Mayi Yinfu](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/mayiyinfu) – Hualei-Plattform, Muster MY…US und MYYF…CN, Flug- und CO_C_CR-Zeilen, Pekinger Zeit, SZ-Filter und Übergabe an Deutsche Post DHL
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – China-Post- und DHL-Erkennung, vorab eingetragene Schritte und Tracking-Dienste
:::
