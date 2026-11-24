---
title: SEKO Logistics Sendungsverfolgung: Nummern & OmniParcel
description: Verfolge SEKO Logistics und SEKO OmniParcel Sendungen: Nummernformate, GX-Codes, Statusmeldungen, US- und UK-Hotlines sowie Liefertermine.
slug: seko-logistics-sendungsverfolgung
published: 2026-11-24
updated: 2026-11-24
---

SEKO Logistics startete 1976 mit einem einzigen Büro in Chicago und betreibt heute mehr als 150 Büros in über 60 Ländern sowie 15 Haupt-Hubs. Zum Angebot gehören Luft-, See- und Landfracht, E-Commerce, *White Glove & Specialty Delivery* sowie *Home Delivery & Last Mile*. Sendungen lassen sich auf [sekologistics.com/en/track](https://www.sekologistics.com/en/track/) verfolgen, grenzüberschreitende E-Commerce-Pakete über SEKOs [OmniParcel-Tracker](https://track.omniparcel.com/).

Da das reguläre SEKO-Portal und OmniParcel als zwei getrennte Systeme mit unterschiedlichen Nummernformaten und Scan-Stilen arbeiten, hilft dir das Format deiner Nummer dabei, den Verlauf richtig zu lesen.

## Sendungsnummern bei SEKO Logistics und OmniParcel

SEKO-Logistics-Sendungen folgen keinem einheitlichen Präfix, sondern teilen sich auf zwei Nummernkreise und zwei Portale auf:

- **SEKO-Logistics-Sendungsnummern:** Peek akzeptiert 8 bis 20 Buchstaben und Ziffern mit mindestens 7 aufeinanderfolgenden Ziffern. Bekannte Formen sind 3 Buchstaben gefolgt von 7 Ziffern (z. B. `WFY4829173`), 5 Buchstaben gefolgt von 7 Ziffern oder 9 reine Ziffern (z. B. `482917360`). Peek erkennt SEKO nie allein an einer bloßen Nummer: Bei 3 Buchstaben + 7 Ziffern fragt es [Australia Post](carrier:australia-post), bei 5 Buchstaben + 7 Ziffern [Colis Privé](carrier:colis-prive) und bei 9 Ziffern [DPD Ireland](carrier:dpd-ie) (außerhalb Irlands erst als letzte Option). Wähle SEKO Logistics manuell oder füge deinen Tracking-Link ein.
- **SEKO-OmniParcel-Nummern (`GX` + 17 oder 18 Ziffern):** Grenzüberschreitende E-Commerce-Sendungen auf `track.omniparcel.com` bestehen aus `GX` gefolgt von 17 oder 18 Ziffern (z. B. `GX482917360514829173`). Die normale SEKO-Trackingseite kennt keine `GX`-Nummern; nur der OmniParcel-Tracker liefert Daten dazu.

Fügst du einen Link wie `sekologistics.com/en/track/?trackingNumbers=...` oder `track.omniparcel.com/...` ein, übernimmt Peek die Nummer und ordnet sie direkt SEKO Logistics zu. Wie sich Fracht- und Paketnummern unterscheiden, erklärt der Leitfaden zu [Sendungsnummern-Formaten](guide:tracking-number-formats).

## Statusmeldungen bei SEKO Logistics und OmniParcel

Auf SEKOs Hauptseite erscheinen Schritte häufig im Muster `Status Changed to [...]` (manchmal mit Bindestrich am Ende statt Klammern), während OmniParcel internationale Hubs, Flüge, Zoll und lokale Zustellpartner beschreibt.

| SEKO / OmniParcel Wortlaut | Was er bedeutet |
| --- | --- |
| `Shipment Data Received` / `Shipment Created` / `Tracking number allocated & order ready` | Sendungsdaten sind bei SEKO oder OmniParcel eingegangen, noch vor der Abholung oder Lagereinlagerung. |
| `Arrived at PU location` | Der Abholfahrer ist beim Absender eingetroffen; die Fracht ist noch nicht als abgeholt verbucht. |
| `Picked Up From Customer` / `At Pickup Terminal` / `Received at Origin Warehouse` | SEKO hat die Sendung abgeholt oder im Abgangsterminal beziehungsweise Ursprungslager eingebucht. |
| `In Transit` / `In Transit to Delivery Terminal` / `At Transfer Terminal` / `Processed through Export Hub` / `Flight - Freight departed to destination` | Unterwegs im nationalen Hauptlauf oder per internationaler Luftfracht Richtung Zielland oder Zustellstation. |
| `At Delivery Terminal` / `At Delivery Terminal Delivery Appt Set` / `On Hand Destination Station` / `Delivered To Warehouse` | Im SEKO-Zielterminal oder lokalen Lager vor der Endzustellung eingetroffen; `Delivery Appt Set` bedeutet, dass ein Liefertermin vereinbart wurde. |
| `Received by Destination Customs` / `Customs held for inspection and clearance` | Zollbearbeitung oder Zollprüfung im Zielland bei einer grenzüberschreitenden OmniParcel-Sendung. |
| `Customs cleared` / `Received by Last Mile Provider` / `Received by carrier` | Verzollung abgeschlossen und an das örtliche Ziel-Hub oder den Zusteller der letzten Meile übergeben. |
| `Shipment is Out for Delivery` / `Driver on site Delivery Location` / `The order is on the way to the recipient.` | Heute mit dem Fahrer in Zustellung oder Fahrer bereits vor Ort. |
| `Awaiting collection` / `Customer to collect from carrier` / `Delivered to locker/collection point` | Bereit zur Abholung durch den Empfänger im Depot, Paketshop oder an einer Packstation. |
| `Attempted delivery` / `Attempted Delivery - Refused by customer` | Ein Zustellversuch war nicht erfolgreich. |
| `Temporary Origin Hold` / `Temporary Destination Hold` | Die Sendung wurde vorübergehend am Abgangs- oder Zielterminal angehalten. |
| `Shipment Delivered` / `Delivered - Authority to Leave / Safe Drop` | An der Empfängeradresse zugestellt (oder per Abstellgenehmigung abgelegt). |
| `Return to sender` | Auf dem Rückweg zum Absender. |

Zwei Formulierungen im SEKO-Hauptportal führen oft zu Missverständnissen:

- **`Delivered To Warehouse`** bedeutet, dass die Fracht SEKOs Ziellager erreicht hat, nicht deine Wohnung — zugestellt ist sie damit noch nicht.
- **`POD Image Attached` und `POD IMAGED`** halten lediglich das Hochladen des Zustellnachweises fest (mitunter Tage nach der Zustellung), und **`Return Data Received`** meldet eine angemeldete Rücksendung, ohne dass die Ware schon unterwegs zurück ist. Keiner dieser Einträge ändert die physische Zustellstufe.

## White-Glove-Heimlieferung, Zoll und Verzögerungen

SEKOs Website nennt *White Glove & Specialty Delivery* und *Home Delivery & Last Mile* sowie gesicherte Logistik für Elektronik und Hardware neben grenzüberschreitenden OmniParcel-Bestellungen.

- **Termine für die Heimlieferung:** `At Delivery Terminal Delivery Appt Set` und `On Hand DEST Delv Appt Set` zeigen, dass ein Zustelltermin feststeht, während die Sendung im Zustellterminal wartet. Unterwegs nennt SEKO oft ein `Expected Arrival`-Datum oder ein Zeitfenster `Estimated delivery between`.
- **Grenzüberschreitende Übergaben bei OmniParcel:** Bei internationalen E-Commerce-Bestellungen (`GX...`) reichen die Scans vom Ursprungslager über Export, Flug und Einfuhrzoll bis zum örtlichen Zusteller (`Received by Last Mile Provider`). Während Flügen und beim Zoll können Scans pausieren; Hilfe bieten der [Zoll-Leitfaden](guide:customs) und [stehengebliebene Sendungsverfolgung](guide:tracking-not-updating).
- **Uhrzeiten und doppelte Einträge:** Die SEKO-Trackingseite zeigt Zeiten minutengenau ohne Zeitzone und nennt denselben Status mitunter zweimal innerhalb einer Minute in unterschiedlicher Groß- und Kleinschreibung; Peek behält einen Eintrag und datiert SEKOs Schritte (die keinen Ort nennen) auf Chicago-Zeit. OmniParcel zeigt Zeiten in UTC+10:00, sofern du das Zeitzonen-Menü nicht umstellst; Peek fragt OmniParcel in UTC ab, sodass jeder Scan seinen exakten Zeitpunkt behält. Peek stellt jeden Scan auf der Uhr deines Geräts dar.

Wenn für einen anderen Abschnitt deiner internationalen Bestellung ein großer Expressdienst genutzt wird, vergleiche [DHL Express](carrier:dhl-express) oder [FedEx](carrier:fedex).

## SEKO Logistics mit Peek verfolgen

Da sich SEKO-Nummern mit anderen Diensten überschneiden und kein durchgängiges Präfix tragen, füge möglichst deinen vollständigen Link `sekologistics.com/en/track/?trackingNumbers=...` oder `track.omniparcel.com/...` ein. Bei einer bloßen Nummer — ob `WFY4829173`, `482917360` oder `GX...` — wähle SEKO Logistics manuell über „Ändern“ beim Hinzufügen oder „Paketdienst ändern“ auf der Paketseite.

Peek fragt beide Portale direkt über den Open-Source-[SEKO-Adapter von universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/seko) ab: `GX`-Nummern mit 17 oder 18 Ziffern gehen automatisch an OmniParcel (in UTC abgefragt, inklusive Scan-Orten, Dienstname, Gewicht in Kilogramm und Zielland), während SEKO-Sendungsnummern an SEKOs Trackingseite gehen (voraussichtliches Ankunftsdatum oder Lieferfenster, Dienstname, Zielland und bereinigte Schritte ohne die Verwaltungszeilen `POD Image Attached` / `POD IMAGED` sowie ohne Unterschriftsnamen und Postleitzahlen).

Peek ordnet SEKO- und OmniParcel-Einträge klaren Stufen zu: „Etikett erstellt“ für erfasste Daten, angelegte Sendungen und Fahrer am Abholort; „Beim Paketdienst eingegangen“ für Abholung und Eingang im Ursprungslager; „Unterwegs“ für Hub-, Flug-, Transfer-, Zielterminal-, Lagerankunfts-, Verzollungsabschluss- und Übergabescans; „Beim Zoll“ für Zollprüfungen im Zielland; „In Zustellung“ für Zustellfahrt und Fahrer vor Ort; „Abholbereit“ für Abholpunkt oder Packstation; „Zustellversuch“ für erfolglose Versuche; „Problem gemeldet“ für vorübergehende Sperren; „Zugestellt“ für Zustellung und Abstellung; sowie „Zurück an Absender“ für Rücksendungen.

Solange du das Paket in Peek geöffnet hast oder am Zustelltag Benachrichtigungen aktiviert sind, fragt Peek bis zu alle 10 Minuten nach; an dem Tag, an dem SEKOs Seite `Shipment is Out for Delivery`, `Out for Delivery` oder `Driver on site Delivery Location` zeigt oder OmniParcel `Out for delivery` oder `The order is on the way to the recipient.` meldet, ab 6 Uhr auf der Uhr des neuesten Scans alle 2 Minuten, solange der SEKO-Tracker antwortet. Andernfalls prüft Peek stündlich. Nach 12 Stunden mit lediglich „Etikett erstellt“ oder „Beim Paketdienst eingegangen“ oder nach zwei Tagen ohne Neuigkeiten prüft Peek unabhängig von deiner Nutzung stündlich, und nach sieben Tagen auf „Etikett erstellt“ alle 6 Stunden. Antwortet SEKO nicht und Peek verfolgt das Paket allein über Tracking-Dienste, prüft Peek stündlich, nach einem ruhigen Tag alle 3 Stunden und an dem Tag, an dem das Paket in Zustellung ist, bis zu alle 15 Minuten, solange du die Sendung geöffnet hast oder Benachrichtigungen aktiviert sind.

Ist der neueste Scan vier Tage alt und das Paket steht noch auf „Etikett erstellt“, „Beim Paketdienst eingegangen“ oder „Unterwegs“, markiert Peek es mit dem Tag dieses Scans, etwa „Keine Aktualisierung seit Mo 12. Okt“. Auf der Paketseite steht dann „Auf langen Flügen, beim Zoll oder übers Wochenende wird es oft still um ein Paket. Peek fragt weiter bei SEKO Logistics nach.“ Nach drei Wochen ohne neuen Scan heißt es „Seit über drei Wochen nichts Neues. Ist das Paket noch nicht angekommen, frag beim Verkäufer nach. Peek fragt weiter bei SEKO Logistics nach.“

Wähle in Peeks Benachrichtigungseinstellungen „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“, um deine Hinweise anzupassen.

## Kontakt zu SEKO Logistics

SEKO veröffentlicht regionale Kundenservice-Hotlines direkt auf seiner [Tracking-Seite](https://www.sekologistics.com/en/track/) und der [Kontaktseite](https://www.sekologistics.com/en/contact-us/):

- **US Home Delivery Hotline:** (866) 268-5263
- **Kundenservice Großbritannien:** +44 1784 417120
- **Offizielle Online-Sendungsverfolgung:** [SEKO Track My Shipment](https://www.sekologistics.com/en/track/) (akzeptiert bis zu 30 Sendungsnummern gleichzeitig) und [OmniParcel Tracker](https://track.omniparcel.com/)
- **Online-Sendungsanfrage:** [SEKO Logistics kontaktieren](https://www.sekologistics.com/en/contact-us/) (Formular „Track My Shipment“ auswählen)
- **Unternehmensüberblick:** [Über SEKO Logistics](https://www.sekologistics.com/en/about/) und [sekologistics.com](https://www.sekologistics.com/en/)

Halte deine SEKO-Sendungsnummer bereit, wenn du anrufst oder das Formular „Track My Shipment“ ausfüllst.

## Häufig gestellte Fragen

### Warum funktioniert meine `GX`-Sendungsnummer nicht auf `sekologistics.com/en/track/`?

Nummern, die mit `GX` beginnen und 17 oder 18 Ziffern folgen lassen, gehören zu SEKOs OmniParcel-Plattform und werden nur auf [track.omniparcel.com](https://track.omniparcel.com/) gefunden. In Peek leitet die Auswahl SEKO Logistics `GX`-Nummern automatisch an OmniParcel und reguläre SEKO-Nummern an das Hauptportal weiter.

### Bedeutet „Delivered To Warehouse“, dass meine Bestellung an eine falsche Adresse geliefert wurde?

Nein. Im SEKO-Logistics-Tracking bedeutet `Delivered To Warehouse`, dass die Sendung vor der Endzustellung in SEKOs Ziellager angekommen ist. Warte auf `Out for delivery` und `Shipment Delivered` als Bestätigung für die Haustürzustellung.

### Warum fragt Peek mich bei meiner SEKO-Nummer nach dem Paketdienst?

SEKO-Nummern (etwa 3 Buchstaben + 7 Ziffern oder 9 Ziffern) passen auch zu anderen Diensten, und Peek erkennt SEKO nie an einer bloßen Nummer — einschließlich `GX`-Nummern. Wenn du über „Ändern“ SEKO Logistics wählst oder den offiziellen SEKO- oder OmniParcel-Link einfügst, weiß Peek genau, wo es suchen muss.

### Wie vereinbare ich einen Termin für eine SEKO-Logistics-Heimlieferung?

Rufe die US Home Delivery Hotline unter `(866) 268-5263` oder den britischen Kundenservice unter `+44 1784 417120` an, oder nutze das Formular „Track My Shipment“ auf [sekologistics.com/en/contact-us](https://www.sekologistics.com/en/contact-us/).

:::sources
- [SEKO Logistics](https://www.sekologistics.com/en/)
- [SEKO Logistics — Track My Shipment](https://www.sekologistics.com/en/track/)
- [SEKO Logistics — About](https://www.sekologistics.com/en/about/)
- [SEKO Logistics — Contact Us](https://www.sekologistics.com/en/contact-us/)
- [SEKO OmniParcel Tracker](https://track.omniparcel.com/)
- [universal-parcel-scraper SEKO adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/seko)
:::
