---
title: Maersk eCommerce Sendungsverfolgung: 1M-Codes & Status
description: Verfolge ein Paket von Maersk eCommerce (B2C Europe / InterPost): 19-stellige 1M-Codes, Statuscodes 70000–77200, Übergaben und Maersk-Support.
slug: maersk-ecommerce-sendungsverfolgung
published: 2026-11-24
updated: 2026-11-24
---

**Maersk eCommerce** (*Maersk Logistics & Services USA Inc.*, hervorgegangen aus den früheren Paketplattformen *B2C Europe* und *InterPost*) ist das nationale und grenzüberschreitende B2C-Paketnetz von A.P. Moller–Maersk ([maersk.com/supply-chain-logistics/e-commerce-logistics](https://www.maersk.com/supply-chain-logistics/e-commerce-logistics)). Mit über 80 Millionen Paketen pro Jahr und mehr als 75 angebundenen Dienstleistern in Nordamerika und Europa sortiert Maersk eCommerce Onlinehändler-Sendungen in eigenen Anlagen, verzollt B2C-Pakete auf internationalen Strecken und übergibt die Zustellung an lokale Paketdienste und Postunternehmen wie [Amazon Logistics](carrier:amazon-logistics), [OnTrac](carrier:ontrac) und [DPD Österreich](carrier:dpd-at). Du kannst deine 19-stellige `1M`-Paketnummer oben einfügen, um sowohl die Scans aus Maersk-Zentren als auch weitergeleitete Meilensteine des lokalen Zustellers zu verfolgen.

## Maersk-eCommerce-Sendungsnummern (Format `1M`)

Maersk eCommerce vergibt 19-stellige alphanumerische Paketnummern, beginnend mit `1M`, gefolgt von einem 7-stelligen Händler-/Kundencode (mit mindestens einem Buchstaben) und 10 Ziffern:

| Format (erfunden) | Worum es sich handelt |
| --- | --- |
| `1MAB4829C7360514928` | 19-stelliger Paketcode von Maersk eCommerce (`1M` + 7-stelliger Kundencode + 10 Ziffern). |
| `1MKX7391E4829173605` | Alternative 19-stellige `1M`-Paketnummer für nordamerikanische oder europäische Routen. |

Schau in die Versand-E-Mail des Händlers oder in den Tracking-Link (`maersk.com/tracking/parcel/...`), um die `1M`-Nummer zu finden. Der Maersk-Tracker kann auch den lokalen Zustelldienst sowie dessen eigene Sendungsnummer nennen. Siehe [Sendungsnummern-Formate](guide:tracking-number-formats).

## Was die Sendungsstatus von Maersk eCommerce bedeuten

Die eigenen Scans von Maersk tragen Codes von `70000` bis `72004`, die des letzten Meilenpartners ab `77100`:

| Maersk-Code & Text | Was das für dich bedeutet | Peek zeigt |
| --- | --- | --- |
| `70000` `Shipment Manifest Received`, `70001` `Maersk Shipping Label Created` | `70000`: Das Manifest des Verkäufers führt das Paket auf, bevor Maersk es erhalten hat. `70001`: Das eigene Maersk-Etikett entsteht, sobald das Paket im Netz eintrifft — oft wenige Minuten vor `70002`. | „Etikett erstellt“ |
| `70002` `Parcel Received by Maersk` | Physisches Paket in einem Eingangszentrum von Maersk eCommerce empfangen und gescannt. | „Beim Paketdienst eingegangen“ |
| `72001` `Arrived at Maersk Facility`, `72002` `Processing at Maersk Facility`, `72004` `Departed from Maersk Facility` | In einem Maersk-Konsolidierungshub sortiert und Richtung Zielregion oder Gateway versandt. | „Unterwegs“ |
| `77100` `In-Transit Scan`, `77101` `Enroute to Facility`, `77102` `Arrival at Facility`, `77103` `Processing at Facility`, `77105` `Departed from Facility`, `77106` `Parcel In-transit` | Weitergeleitete Inlandsscans des lokalen Zustellpartners (etwa OnTrac, Amazon Logistics oder DPD). | „Unterwegs“ |
| `77203` `Out for Delivery` | Heute in das Fahrzeug des lokalen Zustellfahrers geladen. | „In Zustellung“ |
| `77200` `Parcel Successfully Delivered` | Vom lokalen Zustellpartner an der Empfängeradresse zugestellt. | „Zugestellt“ |

Ein Scan ohne einen dieser Codes erscheint als „Problem gemeldet“, wenn Maersk ihn als Ausnahme markiert, und übernimmt andernfalls die Phase des Schritts im Maersk-Tracker.

## Abdeckung, Retouren und grenzüberschreitender Zoll

Maersk E-Commerce bietet flächendeckende Zustellung in 32 Ländern in Nordamerika und Europa sowie Einspeisepunkte in China und Südostasien ([Maersk E-Commerce](https://www.maersk.com/supply-chain-logistics/e-commerce-logistics)):

- **Anlagen und Partner:** In Europa 8 Sortier- und Cross-Docking-Zentren, Einspeise-Hubs in London, Taulov und Amsterdam sowie mehr als 40 Zustellpartner und Postunternehmen mit Hauszustellung und Abholpunkten. In Nordamerika Sortierzentren und Gateways an JFK, LAX und ORD; Standard dauert 2–7 Tage in den USA und 1–10 Tage in Kanada, Expedited 2–5 bzw. 1–5 Tage.
- **Internationale Zollabfertigung:** Maersk verzollt B2C-Pakete für den Versand nach Großbritannien, in die EU und in die USA. Ob bei Ankunft Zölle und Einfuhrumsatzsteuer anfallen, hängt davon ab, ob der Verkäufer sie beim Kauf berechnet hat. Siehe [Pakete beim Zoll](guide:customs).
- **E-Commerce-Retouren:** Nationale und internationale Rücksendungen per Abgabe oder Hausabholung für teilnehmende Händler, auf ausgewählten Routen papierlos und zollfrei.

## Maersk-eCommerce-Paket verspätet, still oder vermisst

Da Maersk eCommerce ein B2B-Paketkonsolidierer im Auftrag von Online-Marken ist, **wende dich zuerst immer an den Onlineshop** für Adressänderungen, fehlende Artikel, Ersatz oder Erstattungen.

- **Sendepause auf der Hauptstrecke oder bei der Übergabe:** Das Tracking pausiert oft einige Tage zwischen `72004` (`Departed from Maersk Facility`) und dem ersten `77100+`-Scan des lokalen Zustellers, während Lkw oder Flüge die Sendung zum Partner bringen. Siehe [warum die Sendungsverfolgung nicht aktualisiert](guide:tracking-not-updating).
- **Nummer des lokalen Zustellers nutzen:** Sobald Maersk den Zusteller und dessen Nummer nennt, übernehmen dessen eigener Tracker und Kundenservice. Peek zeigt „Zustellung durch“ für [Amazon Logistics](carrier:amazon-logistics), [OnTrac](carrier:ontrac) und [DPD Österreich](carrier:dpd-at).
- **Als zugestellt markiert oder beschädigt:** Prüfe Eingänge, Paketbereiche und Nachbarn, schau im Tracking des lokalen Partners nach und melde dich rasch beim Händler. Siehe [zugestellt, aber nicht erhalten](guide:delivered-not-received), [verlorenes Paket](guide:lost-parcel), [beschädigtes Paket](guide:damaged-parcel) und [zurück an Absender](guide:returned-to-sender).

## Maersk-eCommerce-Support und offizielle Portale

Die Support-Seite von Maersk bietet Live-Chat und Fallverwaltung vorwiegend für Geschäftskunden; Händler buchen über den geschäftlichen MyParcel-Login von Maersk.

- **Überblick Maersk E-Commerce & Geschäftsanfragen:** [maersk.com/supply-chain-logistics/e-commerce-logistics](https://www.maersk.com/supply-chain-logistics/e-commerce-logistics).
- **Allgemeiner Maersk-Support & FAQ:** [maersk.com/support](https://www.maersk.com/support) und [maersk.com](https://www.maersk.com/).
- **Hilfeweg für Empfänger:** Kontaktiere den Onlineshop, bei dem du bestellt hast (oder den übernehmenden lokalen Zustelldienst, sobald dieser das Paket gescannt hat).

> Vorsicht vor gefälschten SMS, die dich über einen unbekannten Link zu einer kleinen Zahlung auffordern, um ein „Maersk-Paket“ freizugeben. Prüfe Lieferhinweise immer auf deiner Bestellseite beim Händler oder der offiziellen Website des lokalen Kuriers. Siehe [Betrugs-SMS zu Paketlieferungen](guide:delivery-scam-texts).

## Ein Maersk-eCommerce-Paket mit Peek verfolgen

[Peek](/) prüft Maersk eCommerce automatisch als einzigen Kandidaten, sobald du eine 19-stellige `1M`-Paketnummer (`1MAB4829C7360514928` oder `1MKX7391E4829173605`, erfunden) eingibst, und zeigt „Maersk eCommerce hat dieses Paket.“, sobald die Maersk-Paket-API sie bestätigt; eingefügte Links von `maersk.com/tracking/parcel/` erkennt Peek ebenfalls direkt. Musst du den Dienst manuell wählen, tippe beim Hinzufügen auf „Ändern“ oder in der Sendungsansicht auf „Paketdienst ändern“ (auch die Suche nach „B2C Europe“, „InterPost“, „Maersk Parcel“ oder „Maersk E-Delivery“ findet ihn).

Peek liest den Maersk-Paket-Tracker aus und zeigt die Phase („Etikett erstellt“, „Beim Paketdienst eingegangen“, „Unterwegs“, „In Zustellung“, „Problem gemeldet“, „Zugestellt“), den Wortlaut der Scans von Maersk und lokalem Zusteller, Ort und Region, den voraussichtlichen Liefertag (sofern von Maersk angegeben), das Zielland und die Zustellzeit an. Statuscodes und Servicenamen bleiben in jeder Peek-App ausgeblendet. Maersk nennt für eigene Scans und die meisten Partner einen UTC-Versatz; ein Partnerscan ohne Versatz (etwa von DPD) wird der Zeitzone seines Orts zugeordnet (Wien für einen Scan in Graz) oder der des Nachbarscans, und Peek zeigt alles in der Uhrzeit deines Geräts an. Ein doppelt aufgeführter Partnerscan erscheint einmal. Nennt Maersk [Amazon Logistics](carrier:amazon-logistics), [OnTrac](carrier:ontrac) oder [DPD Österreich](carrier:dpd-at) mit einer passenden Sendungsnummer, prüft Peek dort das Paket: Die Ansicht zeigt dann „Zustellung durch“ samt Dienstleisternamen und beiden Nummern. Peek liest niemals die Kundenauftragsnummer, Zielort und Postleitzahl oder den Partnerlink aus.

Solange du das Paket in Peek geöffnet hast oder am Zustelltag Benachrichtigungen aktiviert sind, fragt Peek bis zu alle 10 Minuten nach; an dem Tag, an dem Maersk `77203` (`Out for Delivery`) meldet, ab 6 Uhr auf der Uhr des neuesten Scans alle 2 Minuten, solange der Maersk-Tracker antwortet. Andernfalls prüft Peek stündlich. Nach 12 Stunden mit lediglich „Etikett erstellt“ oder „Beim Paketdienst eingegangen“ oder nach zwei Tagen ohne Neuigkeiten fragt Peek stündlich nach, egal wie du die Sendung verfolgst, und nach sieben Tagen auf „Etikett erstellt“ alle 6 Stunden. Antwortet der Maersk-Tracker nicht und Peek verfolgt das Paket allein über Tracking-Dienste, prüft Peek stündlich, nach einem ruhigen Tag alle 3 Stunden und an dem Tag, an dem das Paket in Zustellung ist, bis zu alle 15 Minuten, solange du die Sendung geöffnet hast oder Benachrichtigungen aktiviert sind.

Ist der neueste Scan vier Tage alt und das Paket steht noch auf „Etikett erstellt“, „Beim Paketdienst eingegangen“ oder „Unterwegs“, markiert Peek es mit dem Tag dieses Scans, etwa „Keine Aktualisierung seit Mo 12. Okt“. Auf der Paketseite steht dann „Auf langen Flügen, beim Zoll oder übers Wochenende wird es oft still um ein Paket. Peek fragt weiter bei Maersk eCommerce nach.“ Nach drei Wochen ohne neuen Scan heißt es „Seit über drei Wochen nichts Neues. Ist das Paket noch nicht angekommen, frag beim Verkäufer nach. Peek fragt weiter bei Maersk eCommerce nach.“

Bei den Benachrichtigungen kannst du zwischen „Jeden Scan“, „Wichtige Schritte“ und „Nur die Zustellung“ wählen. Peek beobachtet ausschließlich Tracking-Updates und kann weder Sendungen verzollen noch Pakete umleiten oder Maersk eCommerce bzw. dessen Zustellpartner für dich kontaktieren.

## Häufige Fragen zur Sendungsverfolgung von Maersk eCommerce

### Was ist Maersk eCommerce (B2C Europe / InterPost)?

Maersk eCommerce ist das B2C-Paketkonsolidierungs- und Zustellnetz von A.P. Moller–Maersk in Nordamerika und Europa (mit den früheren Netzen B2C Europe und InterPost), das jährlich über 80 Mio. Pakete über mehr als 75 Zustellpartner bewegt.

### Wie sieht eine Sendungsnummer von Maersk eCommerce aus?

Paketnummern von Maersk eCommerce sind 19 Zeichen lang: `1M`, gefolgt von einem 7-stelligen alphanumerischen Kundencode (mit mindestens einem Buchstaben) und 10 Ziffern — zum Beispiel `1MAB4829C7360514928` (erfunden).

### Wer liefert mein Maersk-eCommerce-Paket bis an meine Tür?

Maersk konsolidiert und befördert das Paket zwischen Hubs und übergibt die Schlusszustellung an einen regionalen Partner in deinem Land, wie [Amazon Logistics](carrier:amazon-logistics), [OnTrac](carrier:ontrac), [DPD Österreich](carrier:dpd-at) oder nationale Postdienste.

### Warum bleibt mein Maersk-eCommerce-Paket tagelang auf `70000 Shipment Manifest Received`?

`70000` bedeutet, dass das Manifest des Verkäufers das Paket nennt, bevor Maersk es physisch erhalten hat — erst nach der Übergabe bewegt sich etwas, frag also beim Verkäufer nach. Das eigene Maersk-Etikett `70001` erscheint beim Eintritt ins Netz, oft wenige Minuten vor `70002` (`Parcel Received by Maersk`).

:::sources
- [Maersk: E-Commerce-Paketdienste](https://www.maersk.com/supply-chain-logistics/e-commerce-logistics) – 80 Mio.+ Pakete jährlich, 75+ angebundene Paketdienste, nationale, internationale und Retourennetze in Nordamerika und Europa
- [Maersk: Offizielle Website](https://www.maersk.com/) – globales Logistikportal und Einstieg zur Paketverfolgung
- [Maersk: Support & FAQ](https://www.maersk.com/support) – offizielles Maersk-Supportportal
- [Universal Parcel Scraper: maersk-ecommerce](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/maersk-ecommerce) – 19-stelliges `1M`-Format, Link-Regel `maersk.com/tracking/parcel/`, Statuscodes 70000–77200, Lieferprognose und Zielland sowie Übergaberegeln für Amazon/OnTrac/DPD-AT
:::
