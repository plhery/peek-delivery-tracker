---
title: Shipter Sendungsverfolgung: H3-Nummern aus Südkorea
description: Verfolge internationale Luftpostpakete von Shipter (H3 Networks) aus Südkorea: H3...KR-Nummern, lokale Übergaben, Statusmeldungen und Links.
slug: shipter-sendungsverfolgung
published: 2026-11-24
updated: 2026-11-24
---

Shipter, betrieben von **H3 NETWORKS Co., Ltd.** in Südkorea, verschickt B2B- und B2C-Pakete aus Korea in mehr als **100 Länder** über über 40 Logistikkanäle auf einem Luftfrachtnetz, das jede Woche mehr als 25 Flughäfen verbindet ([shipter.kr](https://www.shipter.kr/)). Der B2B-Bereich deckt Transport, Zoll und Lagerung ab; der B2C-Bereich umfasst Auslandsversand, Zoll und letzte Meile. Amazon Korea hat das Unternehmen als offiziellen Logistikpartner in sein SPN (*Service Provider Network*) aufgenommen, es ist bei eBay, Kogan und Allegro als Paketdienst registriert, an AfterShip und 17TRACK angebunden und arbeitet zudem als Partner für Influencer-Versand.

Du kannst ein Paket auf [shipter.kr/tracking](https://www.shipter.kr/tracking) prüfen oder deine `H3...KR`-Sendungsnummer oben einfügen.

## Format der Shipter Sendungsnummer

Shipter-Nummern beginnen mit `H3` und enden auf `KR`, mit zwei Buchstaben sowie fünf oder sechs Ziffern dazwischen:

| Format (erfunden) | Was es ist |
| --- | --- |
| `H3AU482917KR` | 12 Zeichen: `H3`, zwei Buchstaben, sechs Ziffern und `KR`. Peek fragt [Colis Privé](carrier:colis-prive) und Shipter ab, in Südkorea zuerst Shipter. |
| `H3FR48291KR` | 11 Zeichen: `H3`, zwei Buchstaben, fünf Ziffern und `KR`. Peek fragt allein Shipter ab. |

Ein Tracking-Link im Format `shipter.kr/tracking/<Nummer>` oder `shiptertracking.com/trackingResult/<Nummer>` ordnet das Paket sofort Shipter zu. Für Pakete, die Korea Post aus Südkorea versendet, siehe [Korea Post](carrier:korea-post); weitere Nummernformen findest du im Leitfaden zu [Sendungsnummern-Formaten](guide:tracking-number-formats).

## Bedeutung der Shipter Statusmeldungen

Der Verlauf von Shipter verbindet eigene südkoreanische Export- und Luftfracht-Scans mit weitergeleiteten Scans des örtlichen Zustellers in deinem Zielland, jeweils auf Englisch.

| Shipter / Partner Wortlaut | Was er bedeutet |
| --- | --- |
| `The shipment information has been received by shipter, but the package has not been picked up yet.` | Vorankündigung vom koreanischen Verkäufer eingegangen; Shipter hat das physische Paket noch nicht gescannt. |
| `The parcel has been scanned by shipter and ready to be shipped.` | Shipter hat das Paket im koreanischen Zentrum angenommen und gescannt. |
| `The parcel has been shipped.` / `The flight has been departed.` / `Depart origin airport` | Aus Südkorea per internationalem Frachtflug abgefertigt. |
| `Arrived at destination airport` / `Collected from airport terminal` | Im Zielland gelandet und aus dem Frachtterminal des Flughafens abgeholt. |
| `Import customs cleared` | Der Zoll im Zielland hat das Paket für die Weiterleitung zur lokalen Zustellung freigegeben. |
| `Arrived at destination` / `In transit` | In die Zielsortierung vor den Scans des örtlichen Paketdienstes übergeben. |
| `Parcel received in processing centre (...)` / `Parcel depart from ... depot` / `The item is ready for shipment...` | Im Netz des lokalen Zustellers im Zielland bearbeitet und weitergeleitet. |
| `Your parcel has been received at ... depot..` / `Your parcel is currently in transit to ...` | Vom Zustellpartner im Zielland weitergemeldet, samt Depot und Ort. |
| `Out for delivery` / `Your parcel is with your local courier and ready for delivery..` | Heute mit dem lokalen Zusteller in Auslieferung. |
| `Your parcel was delivered` / `Delivered.` | An der Empfängeradresse zugestellt. |

Zwei vom Zustellpartner weitergeleitete Hinweise, `Your parcel will be entrusted to last mile .it is being prepared by your sender` und `Shipping information received`, erscheinen erst nach dem Annahmescan von Shipter. Sie bedeuten nicht, dass das Paket zurückgegangen ist.

## Internationaler Lufttransport, Zoll und lokale Übergabe

Shipter fliegt Pakete aus Südkorea aus und übergibt sie im Zielland einem örtlichen Paketdienst:

- **Flug und Flughafen:** Nach `Depart origin airport` meldet Shipter `Arrived at destination airport` bei der Landung, `Collected from airport terminal` beim Verlassen des Frachtterminals und `Import customs cleared` nach Abschluss der Einfuhrverzollung. Siehe den [Zoll-Leitfaden](guide:customs) und [stehengebliebene Sendungsverfolgung](guide:tracking-not-updating).
- **Zustellung auf der letzten Meile:** Ein Paketdienst im Zielland stellt das Paket zu, und Shipter übernimmt dessen Depot-, `Out for delivery`- und Zustellscans in seinen eigenen Verlauf.
- **Kein voraussichtliches Lieferdatum:** Die öffentliche Tracking-API von Shipter nennt kein geschätztes Lieferdatum; verfolge den Fortschritt über `Import customs cleared`, den Eingang im lokalen Depot und `Out for delivery`.

## Shipter mit Peek verfolgen

Füge deine `H3...KR`-Nummer oder einen Link von `shipter.kr/tracking/...` beziehungsweise `shiptertracking.com/trackingResult/...` in Peek ein. Bei einer 12-stelligen Nummer fragt Peek [Colis Privé](carrier:colis-prive) und Shipter ab (in Südkorea zuerst Shipter); bei einer 11-stelligen nur Shipter. Sobald Shipter antwortet, erscheint „Shipter hat dieses Paket.“ Nennt Peek einen anderen Dienst, tippe beim Hinzufügen auf „Ändern“ oder auf der Paketseite auf „Paketdienst ändern“ und wähle Shipter.

Peek liest Shipter direkt über den Open-Source-[Shipter-Adapter von universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/shipter) aus und wiederholt bei Netzwerkfehlern. Angezeigt werden der englische Scan-Wortlaut samt genanntem Depot oder Ort sowie der Zustellzeitpunkt. Shipter liefert Zeiten mit GMT-Offset, die Peek auf die Uhr deines Geräts umrechnet; eine Zeit ohne Offset bleibt so stehen, wie Shipter sie nennt.

Peek übersetzt die Scans von Shipter in klare Stufen: „Etikett erstellt“ für die Vorankündigung vor der Abholung, „Beim Paketdienst eingegangen“ nach dem Scan bei Shipter in Korea, „Unterwegs“ für Abflug, Flughafenankunft, Zollfreigabe und lokale Hub-Transfers, „In Zustellung“ beim Start der örtlichen Tour und „Zugestellt“ bei Abschluss. Späte Vorankündigungen des letzten Zustellers (`Shipping information received` und `Your parcel will be entrusted to last mile...`) bleiben im Verlauf sichtbar, ohne die Sendung zurückzustufen.

Ist der neueste Scan vier Tage alt und das Paket steht noch auf „Etikett erstellt“, „Beim Paketdienst eingegangen“ oder „Unterwegs“, markiert Peek es mit dem Tag dieses Scans, etwa „Keine Aktualisierung seit Mo 12. Okt“. Auf der Paketseite steht dann „Auf langen Flügen, beim Zoll oder übers Wochenende wird es oft still um ein Paket. Peek fragt weiter bei Shipter nach.“ Nach drei Wochen ohne neuen Scan heißt es „Seit über drei Wochen nichts Neues. Ist das Paket noch nicht angekommen, frag beim Verkäufer nach. Peek fragt weiter bei Shipter nach.“

Solange du das Paket in Peek geöffnet hast oder am Zustelltag Benachrichtigungen aktiviert sind, fragt Peek bis zu alle 10 Minuten nach; an dem Tag, an dem Shipter `Out for delivery` oder `Your parcel is with your local courier and ready for delivery..` meldet, ab 6 Uhr auf der Uhr des neuesten Scans alle 2 Minuten, solange der Shipter-Tracker antwortet. Andernfalls prüft Peek stündlich. Nach 12 Stunden mit lediglich „Etikett erstellt“ oder „Beim Paketdienst eingegangen“ oder nach zwei Tagen ohne Neuigkeiten prüft Peek unabhängig von deiner Nutzung stündlich, und nach sieben Tagen auf „Etikett erstellt“ alle 6 Stunden. Antwortet der Shipter-Tracker nicht und Peek verfolgt das Paket allein über Tracking-Dienste, prüft Peek stündlich, nach einem ruhigen Tag alle 3 Stunden und an dem Tag, an dem das Paket in Zustellung ist, bis zu alle 15 Minuten, solange du die Sendung geöffnet hast oder Benachrichtigungen aktiviert sind.

Peek benachrichtigt dich wahlweise bei „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“.

## Kontakt zu Shipter

Shipter betreut die Unternehmen, die ihre Sendungen darüber abwickeln:

- **Offizielle Website & Serviceanfragen:** [shipter.kr](https://www.shipter.kr/) (H3 NETWORKS Co., Ltd.; *이용문의* für Geschäftsanfragen)
- **Offizieller Online-Tracker:** [shipter.kr/tracking](https://www.shipter.kr/tracking)
- **Zuerst den Verkäufer kontaktieren:** Jeder Firmenkunde hat bei Shipter einen eigenen CS-Manager, der Probleme, Rücksendungen und Reklamationen mit dem Absender klärt. Ist dein Paket verspätet, beschädigt oder verschwunden, wende dich mit deiner `H3...KR`-Nummer an den Shop.

## Häufig gestellte Fragen

### Wie sieht eine Shipter-Sendungsnummer aus?

Sie beginnt mit `H3` und endet auf `KR`, dazwischen stehen zwei Buchstaben und fünf oder sechs Ziffern: insgesamt 11 oder 12 Zeichen, wie `H3AU482917KR`. Was die Buchstaben bedeuten, erläutert Shipters Website nicht.

### Warum erscheint „Shipping information received“ erst Tage nach dem Versand meines Pakets?

Es handelt sich um eine Vorankündigung des lokalen Zustellpartners im Zielland, die Shipter nach seinem eigenen Annahmescan weiterleitet. Das setzt das Paket nicht zurück: Peek behält die Stufe des vorherigen Scans bei.

### Warum zeigt mein Shipter-Paket Updates eines anderen Paketdienstes?

Shipter übernimmt den Export aus Südkorea und die internationale Luftfracht, übergibt das Paket im Zielland an einen örtlichen Zustellpartner und blendet dessen Depot- und Zustellscans in deinem Shipter-Verlauf ein.

### Gibt Shipter ein geschätztes Lieferdatum an?

Nein. Der öffentliche Tracking-Feed von Shipter zeigt zeitgestempelte Export-, Flug-, Zoll- und lokale Kurierereignisse mit GMT-Offsets, aber kein voraussichtliches Lieferdatum.

:::sources
- [Shipter (H3 NETWORKS Co., Ltd.)](https://www.shipter.kr/)
- [Shipter Tracking](https://www.shipter.kr/tracking)
- [universal-parcel-scraper Shipter adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/shipter)
:::
