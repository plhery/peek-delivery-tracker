---
title: DHL eCommerce Sendungsverfolgung: GM-Nummer, Status, Kontakt
description: DHL-eCommerce-Paket verfolgen (früher DHL Global Mail): die GM-Nummer, was die englischen Status heißen, wer zustellt und wen du fragst, wenn es hängt.
slug: dhl-ecommerce-sendungsverfolgung
published: 2026-10-11
updated: 2026-10-27
---

DHL eCommerce, früher DHL Global Mail, ist der Paketbereich von DHL für Online-Shops. Es sortiert dein Paket in seinen eigenen Verteilzentren und übergibt es dann einem Zusteller im Zielland: der Post vor Ort oder einem anderen Paketdienst. Verfolgen kannst du es mit der Nummer aus der Versandmail des Shops auf Webtrack, der Tracking-Seite von DHL eCommerce, oder im Feld oben.

## DHL eCommerce Trackingnummer: so sieht sie aus

Nummern von DHL eCommerce haben mindestens zehn Zeichen und beginnen meist mit GM. Keine Nummer bekommen? Dann frag den Shop.

| Nummer (erfunden) | Was sie ist |
| --- | --- |
| `GM0000000000000000` | GM und 16 bis 18 Ziffern: die GM-Nummer, die übliche Form bei DHL eCommerce. |
| `0000000000000000` | 16 oder 17 Ziffern: eine weitere Form von DHL eCommerce. |
| `0031000000000000000000000000000` | 0031 und 27 Ziffern: ebenfalls DHL eCommerce. |
| `9261000000000000000000` | 22 Ziffern mit 9261 oder 9361 vorne: die USPS-Nummer, die ein Paket in den USA nach der Übergabe an USPS trägt. |

In Webtracks Detailbox steht unter „Delivery by“, welcher Paketdienst zustellt. Auf Webtrack kannst du bis zu 10 Nummern auf einmal eingeben, getrennt durch Kommas.

Verwechslungsgefahr: 16 Ziffern können auch zu TNT, Evri, Canada Post oder Correos Express gehören, eine 9261- oder 9361-Nummer auch zu USPS. 14 Ziffern sind DHL eCommerce UK, ein eigenes Netz mit eigenem Tracking. [DHL Paket](carrier:dhl) und [DHL Express](carrier:dhl-express) nutzen wieder andere Formen, etwa `JJD…` bei DHL Paket. Mehr in [Sendungsnummer: welcher Paketdienst steckt dahinter](guide:tracking-number-formats).

## DHL eCommerce Tracking: was die Status bedeuten

Webtrack zeigt oben einen kurzen Gesamtstatus (`En Route`, `Delivered`, `Exception`…) und darunter den Verlauf („Tracking History“) mit Scans in Großbuchstaben. Standardmäßig ist die Seite auf Englisch, und Peek zeigt die Scans ebenfalls auf Englisch. Die häufigsten, in der Reihenfolge, in der ein Paket sie erreicht:

| DHL eCommerce schreibt | Was es für dich heißt |
| --- | --- |
| `LABEL CREATED` | Der Shop hat das Etikett erstellt. DHL eCommerce hat das Paket noch nicht. |
| `EN ROUTE TO DHL ECOMMERCE DISTRIBUTION CENTER OR AWAITING PROCESSING` | Es ist unterwegs zu DHL oder wartet noch beim Shop. |
| `PACKAGE RECEIVED AT DHL ECOMMERCE DISTRIBUTION CENTER` | DHL eCommerce hat das Paket. |
| `PROCESSED` | Es wurde im Verteilzentrum sortiert. |
| `DEPARTURE ORIGIN DHL ECOMMERCE FACILITY` | Es hat das erste Lager von DHL eCommerce verlassen. |
| `ARRIVAL DESTINATION DHL ECOMMERCE FACILITY` | Es ist im Lager von DHL eCommerce in der Nähe des Ziels angekommen. |
| `CUSTOMS CLEARANCE` | Es ist beim Zoll. |
| `TENDERED TO DELIVERY SERVICE PROVIDER, ALLOW 1-3 DAYS FOR UPDATES FOR PACKAGES WITHIN THE US` | Es wurde dem Zusteller übergeben. Bei Paketen in den USA können die Updates bis zu 3 Tage ruhen. |
| `OUT FOR DELIVERY` | Es ist heute beim Zusteller. |
| `DELIVERY ATTEMPT FAILED` | Die Zustellung hat nicht geklappt. Schau im Briefkasten nach einer Benachrichtigung des Zustellers. |
| `DELIVERED` | Zugestellt. `[IN/AT MAILBOX]` dahinter heißt: im Briefkasten. |
| `RETURNED TO SENDER` | Es geht zurück an den Shop. |

Stellst du Webtrack auf „Deutsch“ um, sind die meisten Scans übersetzt, einige bleiben englisch (`LABEL CREATED`, `OUT FOR DELIVERY`). So heißen die häufigsten Scans auf Deutsch, wie sie bei Paketen in die USA erscheinen:

| Webtrack auf Deutsch | Was es heißt |
| --- | --- |
| `UNTERWEGS ZUM DHL ECOMMERCE DISTRIBUTIONSZENTRUM` | Unterwegs zu DHL oder wartet noch beim Shop. |
| `ANKUNFT AM DHL ECOMMERCE DISTRIBUTIONSZENTRUM` | DHL eCommerce hat das Paket. |
| `VERARBEITUNG ERFOLGT` | Sortiert. |
| `ABGANG AUS DHL ECOMMERCE TERMINAL` | Hat das erste Lager verlassen. |
| `ANKUNFT AN DHL ECOMMERCE TERMINAL` | Im Lager nahe dem Ziel. |
| `DELIVERY SERVICE PROVIDER ÜBERGEBEN` | Dem Zusteller übergeben, halb englisch, wie DHL es schreibt. |
| `ZUGESTELLT` | Zugestellt. |

Zwei Scans verwirren oft. `DELIVERED TO RETURNS AGENT OR COLLEGE/UNIVERSITY FOR FINAL DELIVERY` heißt: an eine Zwischenstelle geliefert, etwa einen Retourendienstleister oder die Poststelle einer Hochschule, die es weitergibt. `Picked up at parcelshop` heißt, dass der Absender das Paket in einem Shop abgegeben hat. Dort wartet nichts auf dich.

## Wer stellt DHL eCommerce in Deutschland, Österreich und der Schweiz zu?

DHL eCommerce stellt meist nicht selbst zu. In den USA übernimmt USPS. Internationale Pakete bringt laut Webtracks FAQ der „local delivery service provider“ des Ziellands, und das „kann die örtliche Post oder ein anderes Zustellunternehmen sein“. Wer das in Deutschland, Österreich oder der Schweiz ist, nennt DHL eCommerce nicht allgemein.

Auch DHL Paket schreibt, dass ein Paket aus dem Ausland in Deutschland nicht zwangsläufig von der Deutschen Post oder DHL Paket zugestellt wird. Ob es dort angekommen ist, kannst du laut dhl.de mit der Sendungsnummer in der Sendungsverfolgung von dhl.de prüfen. Findet sie es nicht, ist es noch nicht im Netz der Deutschen Post oder geht über ein anderes Unternehmen.

Benachrichtigung, Abholung, Lagerfrist und neue Zustellung richten sich deshalb nach dem Zusteller, nicht nach DHL eCommerce. DHL bittet dich, im Briefkasten nach dem Paket oder einer Benachrichtigung zu schauen und Zustellwünsche dem Zusteller vor Ort mitzuteilen.

Adresse ändern: Webtracks deutsche FAQ sagt, das gehe nur, wenn beide Adressen im selben Zustellgebiet des Zustellers liegen; der Zusteller meldet sich dann für den zweiten Versuch. Die englische FAQ sagt, DHL eCommerce könne die Adresse nicht mehr ändern, sobald das Paket den Shop verlassen hat. Am sichersten fragst du den Shop.

## DHL eCommerce Paket hängt, ist verloren oder kommt zurück

**Noch keine Scans.** Laut Webtracks FAQ erscheinen die ersten Ereignisse meist innerhalb von 24 bis 48 Stunden; gibt es nach 48 Stunden noch keine, frag den Shop. Findet Webtrack nichts, sollst du die Nummer prüfen und dich an den Shop wenden.

**Tagelang kein Update.** Webtracks FAQ nennt Wetter, Feiertage, Zoll, Streiks und andere Ereignisse, auf die DHL keinen Einfluss hat. Bei Paketen in den USA kündigt schon der Übergabe-Scan bis zu 3 Tage ohne Update an. Mehr in [Sendungsverfolgung aktualisiert sich nicht](guide:tracking-not-updating).

**Nicht angekommen.** Ist das Paket nach dem erwarteten Lieferzeitraum nicht da, wende dich laut Webtracks FAQ an den Verkäufer. Kann er dir keinen aktuellen Status geben, verweist die deutsche FAQ an den Kundenservice von DHL eCommerce Solutions. In der EU haftet der Händler laut Ihr Europa für Schäden an der Ware, bis du sie erhältst, und sollte innerhalb von 30 Tagen liefern, sofern nichts anderes vereinbart ist; sonst erinnerst du ihn mit einer Nachfrist und kannst danach vom Vertrag zurücktreten und dein Geld zurückverlangen.

**Zoll.** `CUSTOMS CLEARANCE` heißt, das Paket ist beim Zoll. Was du zahlen musst, erklärt [Paket beim Zoll](guide:customs).

**Zurück an den Shop.** `RETURNED TO SENDER` heißt, das Paket geht zurück an den Shop. Frag dort nach Erstattung oder neuem Versand.

## DHL eCommerce Kontakt und Telefonnummer

Frag zuerst den Shop, so rät es auch Webtracks FAQ. Webtracks Seite „Contact Customer Service“ zeigt, Stand 12. Oktober 2026, eine Karte pro Land:

- **Deutschland:** Die Karte verweist auf den [Kundenservice von DHL](https://www.dhl.de/de/privatkunden/hilfe-kundenservice.html) auf dhl.de, Montag bis Sonntag 7 bis 20 Uhr.
- **Österreich:** +49 228 92 95 95 95, Montag bis Freitag 8 bis 18 Uhr, Samstag und Sonntag geschlossen.
- **Schweiz:** +49 228 92 95 95 95, Montag bis Freitag 9 bis 18 Uhr.
- **Pakete aus den USA:** +1 317-554-5191, Montag bis Freitag 8 bis 20 Uhr Ostküstenzeit.

Über Webtracks Formular „Contact Support“ soll sich laut DHL innerhalb von 24 bis 72 Stunden jemand melden. Was ein Anruf kostet, steht nirgends. Auf Webtrack kannst du dir außerdem Updates per E-Mail schicken lassen: bei jedem Ereignis, bei Verzögerung oder bei Zustellung.

Verdächtige Nachricht? Gefälschte SMS erkennst du laut dhl.de oft an einem gekürzten Link (etwa bit.ly), einem allgemeinen Absender wie „Lieferung“ oder einer unerwarteten Ländervorwahl. Einen Screenshot mit der Nummer kannst du an phishing@dhl.com schicken.

## DHL eCommerce mit Peek verfolgen

[Peek](/) erkennt eine GM-Nummer sofort als DHL eCommerce. Bei Formen, die sich mehrere Paketdienste teilen, fragt Peek DHL eCommerce und die anderen wahrscheinlichen gleichzeitig: TNT bei 16 Ziffern, bei passender Prüfziffer auch Evri UK, Canada Post und Correos Express; Colis Privé bei 17; die [Österreichische Post](carrier:austrian-post) bei einer 9261- oder 9361-Nummer, und kennen beide sie nicht, öffnet Peek zuletzt die Seite von USPS. Kennt nur DHL eCommerce die Nummer, steht da „DHL eCommerce hat dieses Paket.“ Kennen mehrere Marken die Nummer, wählst du mit „Ändern“, das für DHL eCommerce jede Folge aus 5 bis 40 Buchstaben und Ziffern annimmt. Links von webtrack.dhlglobalmail.com und ecommerceportal.dhl.com funktionieren auch, Tracking-Links von dhl.com nur, wenn die Nummer darin eine Form von DHL eCommerce hat.

Gefragt wird zuerst Webtrack. Kennt Webtrack das Paket nicht, wird DHLs globale Tracking-Seite auf dhl.com geöffnet. Das dauert länger und kann scheitern, wenn DHL automatische Besuche blockiert.

Du siehst den Status, jeden Scan mit Ort und Land auf einer Karte, den erwarteten Liefertag, wenn DHL einen nennt, den Absender und den Zustellzeitpunkt; von Webtrack auch Service, Gewicht und Zusteller. Deine Adresse, die Referenz des Shops und der Name der Person, die unterschrieben hat, fallen weg. Die Scans bleiben in DHLs US-Englisch, jede Zustellung erscheint als „Zugestellt“. Ein Scan, der sich zeitlich nicht einordnen lässt, erscheint nicht.

Nennt Webtrack USPS, kann das Paket bei USPS weiterverfolgt werden, und seine Seite zeigt „Zustellung durch USPS“. Pakete, die DHLs globale Seite beantwortet (Europa, Asien), verfolgt Peek nicht bis zur Post vor Ort. Mitteilungen kannst du für jeden Scan, nur für wichtige Schritte oder „Nur die Zustellung“ einstellen.

## Fragen zur DHL eCommerce Sendungsverfolgung

### Ist DHL eCommerce dasselbe wie DHL Express oder DHL Paket?

Nein. DHL eCommerce ist der Paketbereich von DHL für Online-Shops und überlässt die letzte Strecke meist einer Post oder einem Paketdienst vor Ort. DHL Express hat eine eigene Sendungsverfolgung, und DHL Paket ist das deutsche Paketnetz. Peek verfolgt sie als getrennte Paketdienste.

### DHL eCommerce Paket nicht erhalten: was tun?

Wende dich an den Verkäufer, wenn das Paket nach dem erwarteten Lieferzeitraum nicht da ist. Kann er dir keinen aktuellen Status geben, nennt Webtracks deutsche FAQ den Kundenservice von DHL eCommerce Solutions als nächste Anlaufstelle.

### Kann ich ein DHL eCommerce Paket umleiten?

Kaum. Laut Webtracks deutscher FAQ geht eine neue Adresse nur im selben Zustellgebiet des Zustellers, die englische FAQ verneint es ganz. Frag den Shop, solange das Paket noch nicht unterwegs ist.

### Warum zeigt DHL eCommerce noch keine Sendungsdaten?

Laut Webtracks FAQ erscheinen die ersten Ereignisse meist innerhalb von 24 bis 48 Stunden. Gibt es nach 48 Stunden noch keine, frag den Shop, der das Paket verschickt hat.

:::sources
- [DHL eCommerce: Webtrack](https://webtrack.dhlecs.com/) – Tracking-Seite, Gesamtstatus, deutsche und englische Scans, Detailbox, 10 Nummern, Rat bei fehlendem Ergebnis, Formular, E-Mail-Updates
- [DHL eCommerce: Webtrack Contact Customer Service](https://webtrack.dhlecs.com/support/customer-service) – Karten pro Land, Telefonnummern, Zeiten, FAQ zu 24 bis 48 Stunden, Zustellung, Verzögerung, nicht erhaltenen Paketen, Adressänderung
- [DHL Paket: Import, Pakete aus dem Ausland](https://www.dhl.de/de/privatkunden/hilfe-kundenservice/themen/international/import.html) – Zustellung nicht zwangsläufig durch Deutsche Post oder DHL Paket, Prüfung im Tracking von dhl.de
- [DHL: Kundenservice für Privatkunden](https://www.dhl.de/de/privatkunden/hilfe-kundenservice.html) – Ziel der Karte für Deutschland
- [DHL: Sicherheitshinweise](https://www.dhl.de/de/toolbar/footer/sicherheitshinweise.html) – Merkmale gefälschter SMS, phishing@dhl.com
- [Ihr Europa: Versand und Lieferung](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_de.htm) – Haftung des Händlers bis zur Lieferung, 30 Tage, Nachfrist, Rücktritt
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – früher DHL Global Mail, Nummernformen und USPS-Nummer, Scans und ihre Stufen, eigene Sendungsverfolgung von DHL Express, was Peek liest und zeigt
:::
