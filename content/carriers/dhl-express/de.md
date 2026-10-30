---
title: DHL Express Sendungsverfolgung: Nummer, Status, Kontakt
description: DHL Express Sendung verfolgen: wo die 10-stellige Frachtbriefnummer steht, was die englischen Status heißen, Zoll, verpasste Zustellung und Kontakt.
slug: dhl-express-sendungsverfolgung
published: 2026-10-13
updated: 2026-10-27
---

Füg eine Frachtbriefnummer von DHL Express oder einen MyDHL+-Link oben ins Feld ein, um das Paket zu verfolgen. DHLs eigene Sendungsverfolgung findest du in Deutschland auf dhl.de und in der App von DHL Express.

## DHL Express Sendungsverfolgungsnummer: wo sie steht und wie sie aussieht

Die Sendungsnummer von DHL Express ist die Frachtbriefnummer (englisch „waybill number“). Schau in die Versand-E-Mail des Shops oder auf deine Bestellseite; ein MyDHL+-Link enthält die Nummer hinter `id=`. Hat der Versender DHL Express deine E-Mail-Adresse gegeben, schickt dir laut dhl.de auch DHL Express die Sendungsnummer per E-Mail. Mehr Tipps in [Sendungsnummer finden](guide:find-tracking-number).

| Nummer (erfunden) | Was sie ist |
| --- | --- |
| `1234567891` | Die Frachtbriefnummer: 10 Ziffern, damit verfolgst du die ganze Sendung. Die letzte Ziffer ist eine Prüfziffer. |
| `JD014600001234567890` | Die Packstück-Nummer: `JD01` und 16 Ziffern, auf dem Etikett als `(J)JD…` unter dem unteren Barcode. Jedes Paket einer Sendung hat seine eigene, und sie führt zur Frachtbriefnummer zurück. |

Auch Mondial Relay, Relais Colis, TIPSA, Estafeta, [Packeta](carrier:packeta), DHL eCommerce Iberia und [DHL Paket](carrier:dhl) vergeben 10-stellige Nummern. Schau also in der E-Mail des Shops nach, welcher Paketdienst genannt ist.

DHL vergibt Frachtbriefnummern neu: Unter einer Nummer können Scans einer früheren Sendung auftauchen, die Peek ignoriert.

## DHL Express Status: was die Meldungen bedeuten

Das sind Scan-Texte, die Peek von DHL Express liest, in DHLs Englisch, ungefähr in der Reihenfolge, in der ein Paket sie durchläuft, mit dem Status, den Peek jeweils zeigt. Viele enden mit dem Standort, zum Beispiel `Processed at` gefolgt von Stadt und Land.

| DHL Express' Scan | Peek zeigt | Was es heißt |
| --- | --- | --- |
| `Shipment information received`, `Supplementary information has been received and merged with applicable shipment data` | Etikett erstellt | Der Versender hat DHL die Sendungsdaten geschickt. |
| `Shipment picked up`, `Shipment accepted` | Beim Paketdienst eingegangen | DHL Express hat das Paket. |
| `Processed at`, `Arrived at DHL Sort Facility`, `Shipment has departed from a DHL facility`, `Shipment is in transit to destination` | Unterwegs | Es läuft durch DHLs Netz. |
| `Scheduled to depart on the next planned movement` | Unterwegs | Es wartet an einem Standort auf den nächsten Lkw oder Flug. |
| `Customs clearance status updated. Note - The Customs clearance process` …, `Processed for clearance at`, `Clearance event` | Beim Zoll | Es ist in der Verzollung (siehe unten). |
| `Payment is received and recorded for shipment related fees` | Beim Zoll | Die Abgaben oder Gebühren sind bezahlt; das Paket bleibt beim Zoll, bis die Verzollung abgeschlossen ist. |
| `Clearance processing complete at` | Unterwegs | Der Zoll ist fertig, es geht weiter. |
| `Arrived at DHL Delivery Facility` | Unterwegs | Es ist im DHL-Standort angekommen, der es zustellt. |
| `Shipment is out with courier for delivery` | In Zustellung | Ein Kurier bringt es zu dir. |
| `Delivery attempted but no response at Consignee address`, `Delivery attempt could not be completed` | Zustellversuch | Die Zustellung hat nicht geklappt. „Consignee“ bist du, der Empfänger. |
| `Shipment is scheduled for delivery` | Unterwegs | Eine Zustellung ist für eine spätere Tour geplant, etwa nach einem verpassten Versuch. |
| `Awaiting collection by the consignee` | Abholbereit | Es liegt in einer DHL-Einrichtung, bis du es abholst. |
| `Delivered` | Zugestellt | Es ist zugestellt. |
| `Shipment is on hold` | Problem gemeldet | DHL hat die Sendung vorerst angehalten. |
| `Shipment arrived at incorrect facility. It will be sent to the correct destination` | Problem gemeldet | Es wurde falsch sortiert und geht mit Verspätung zum richtigen Standort weiter. |
| `Returned to shipper` | Zurück an Absender | Es geht zurück an den Versender. |

## DHL Express Zustellung verpasst oder ändern

Nach einem verpassten Versuch steht in der Sendungsverfolgung `Delivery attempted but no response at Consignee address` oder `Delivery attempt could not be completed`. Folgt `Shipment is scheduled for delivery`, ist ein neuer Versuch geplant; `Awaiting collection by the consignee` heißt, das Paket wartet in einer DHL-Einrichtung auf dich.

In Deutschland hinterlässt der Fahrer laut dhl.de eine Benachrichtigungskarte mit einer Referenznummer, und die Sendung liegt dann in einem nahen DHL Service Point oder einer Filiale. Mit der Sendungs- oder Referenznummer zeigt dir DHLs kostenloser Dienst On Demand Delivery, wo: Du kannst sie noch am selben Tag abholen oder eine Zweitzustellung buchen. Zur Abholung bringst du einen gültigen Ausweis mit; schickst du jemand anderen, gibst du ihm eine Vollmacht. Holst du sie nicht innerhalb von sieben Tagen ab, geht sie zurück an den Versender.

Hat der Versender On Demand Delivery freigeschaltet, kannst du über den Link in der SMS oder E-Mail von DHL Express schon vorher umplanen: Tag oder Zeitfenster, Nachbar, andere Adresse, DHL Service Point, Urlaubsaufbewahrung oder Zustellung ohne Unterschrift. Ändern kannst du die Zustellung nur bei DHL, nicht in Peek. Mehr in [Paket verpasst](guide:missed-delivery) und [Paket nicht abgeholt](guide:uncollected-parcel).

## DHL Express Paket beim Zoll

Kommt ein Paket aus einem Nicht-EU-Staat, können Einfuhrabgaben fällig werden. Solange es beim Zoll ist, zeigt die Sendungsverfolgung `Customs clearance status updated…`, `Processed for clearance at` oder `Clearance event`, nach der Zahlung `Payment is received and recorded for shipment related fees` und nach der Freigabe `Clearance processing complete at`.

In Deutschland übernimmt laut Zoll in den meisten Fällen der Post- oder Kurierdienst die Zollanmeldung und legt die Einfuhrabgaben aus. Du zahlst sie in der Regel bei der Zustellung, dazu meist eine Servicepauschale, deren Höhe in den AGB des Dienstes steht. Seit dem 1. Juli 2026 fällt für Sendungen aus Nicht-EU-Staaten bis 150 € ein pauschaler Zoll von 3 € je Warenkategorie an, dazu wie bisher die Einfuhrumsatzsteuer von 19 bzw. 7 %. Dieser Pauschalzoll gilt laut dem EU-Portal „Ihr Europa“ in der ganzen EU, also auch in Österreich.

DHL Express Deutschland schreibt auf dhl.de, dass es fällige Zölle auslegt und sie danach bei dir einfordert, ob du die Ware annimmst oder nicht. Du zahlst bar oder per QR-Code an der Haustür oder vorab online über On Demand Delivery, ohne Anmeldung. Ohne Zahlung, oder ohne schriftliche Zusage des Versenders, die Kosten zu übernehmen, stellt DHL die Sendung nicht zu. Mehr in [Paket beim Zoll](guide:customs).

## DHL Express Sendung verspätet, angehalten oder verloren

Steht die Sendungsverfolgung still oder zeigt sie `Shipment is on hold`, lies [Sendungsverfolgung aktualisiert sich nicht](guide:tracking-not-updating). Fordert dich DHLs Sendungsverfolgung auf, DHL zu kontaktieren, reichen DHL laut dhl.de oft die Angaben nicht, etwa weil die Adresse falsch oder unvollständig ist; ein Anruf beim Kundenservice kann das schnell klären. Ist eine Sendung nicht angekommen, meldest du dich laut dhl.de so bald wie möglich beim Kundenservice, mit einer genauen Beschreibung von Ware und Verpackung.

Hast du bei einem Händler bestellt, ist er dein Ansprechpartner, auch wenn DHL Express das Paket bringt. Laut „Ihr Europa“ haftet er für Schäden an der Ware vom Versand bis zur Übergabe an dich. Ist nichts anderes vereinbart, muss er innerhalb von 30 Tagen liefern. Kommt nichts, setz ihm eine angemessene Nachfrist; hält er auch die nicht ein, kannst du vom Vertrag zurücktreten und bekommst dein Geld zurück. Das gilt in der ganzen EU.

**Zugestellt, aber nicht da?** DHLs Sendungsverfolgung kann die Unterschrift zeigen; Peek speichert sie nicht. Schau bei DHL nach und wende dich dann an den Händler. Mehr in [Paket zugestellt, aber nicht erhalten](guide:delivered-not-received) und [Paket verloren](guide:lost-parcel).

## DHL Express Kontakt und Telefonnummer

Für Express-Sendungen in Deutschland nennt DHL auf dhl.de diese Kontakte:

- **Kundenservice:** +49 (0) 228 902 435-11 für nationale und +49 (0) 228 902 435-13 für internationale Sendungen, Montag bis Freitag 7 bis 19 Uhr, nicht an bundesweiten Feiertagen.
- **Sendung verpasst (On Demand Delivery):** +49 (0)228 90243514, Montag bis Freitag 7 bis 19 Uhr.
- **Steuern und Einfuhrzölle:** +49 (0)2203 699 230-0, Montag bis Freitag 8.30 bis 16 Uhr.
- **WhatsApp-Chat:** +49 (0) 173/54 64 461, Montag bis Freitag 8 bis 18 Uhr.

DHL Paket hat eine andere Nummer. In Österreich und der Schweiz findest du die Kontakte von DHL Express auf DHLs Website für dein Land.

## DHL Express Sendung mit Peek verfolgen

[Peek](/) erkennt eine 10-stellige Frachtbriefnummer, deren Prüfziffer DHL Express' Regel besteht. Weil auch andere Paketdienste 10 Ziffern nutzen, setzt Peek DHL Express an die erste Stelle, fragt gleichzeitig auch andere, etwa ACS Courier, Geniki Taxydromiki und DHL eCommerce Iberia, und nennt den, der das Paket kennt. ACS und Geniki nutzen dieselbe Prüfziffer: Kennt einer von ihnen die Nummer ebenfalls, wählst du selbst. Fällt die Prüfziffer durch, wird DHL Express nicht angeboten: Wähle es dann mit „Ändern“. Packstück-Nummern mit `JD01` und Etiketten mit `JJD0146` (`JJD014612345678901234`) erkennt Peek sofort als DHL Express und führt sie auf ihre Frachtbriefnummer zurück. Links von mydhl.express.dhl kannst du einfügen. Bei einem dhl.com-Link füg lieber die Frachtbriefnummer selbst ein. Landet ein Paket unter „DHL“, stell es mit „Paketdienst ändern“ auf „DHL Express“ um.

Peek liest DHL Express über den Dienst hinter dessen App oder öffnet sonst die Tracking-Seite von dhl.com in einem Browser auf Peeks Server. Das ist langsamer und kann abgelehnt werden: Dann helfen Tracking-Dienste aus. Peek fragt tagsüber alle 10 Minuten nach, sobald das Paket in Zustellung ist alle 2 bis 5 Minuten, nachts alle ein bis zwei Stunden, und schickt dir auf Wunsch Mitteilungen bei jedem Scan, nur bei wichtigen Schritten oder nur am Liefertag.

Du siehst den Status, jeden Scan mit Station auf einer Karte und das Zielland. Die Uhrzeiten werden nach dem Ort der Station eingeordnet oder, wenn der nicht reicht, nach den Scans davor und danach. Die Scans stehen in DHLs Englisch, nur `Delivered`, `Shipment picked up` und `Shipment information received` zeigt Peek in eigenen Worten. Peek blendet die Unterschrift aus und ignoriert alte Scans einer neu vergebenen Frachtbriefnummer. Ein voraussichtliches Lieferdatum, Gewicht oder Service Point zeigt es nicht. Zoll bezahlen oder die Zustellung ändern geht nur bei DHL.

## Fragen zur DHL Express Sendungsverfolgung

### Warum ist die DHL Express Sendungsverfolgung nicht möglich?

Meist gehört die Nummer zu einem anderen DHL-Paketdienst: Frachtbriefnummern von DHL Express haben genau 10 Ziffern, die letzte ist eine Prüfziffer, ein Tippfehler fällt also meist durch. Eine neue Nummer zeigt oft nur `Shipment information received`, bis DHL das Paket abgeholt hat.

### Ist DHL dasselbe wie DHL Express?

Nicht in Peek: DHL Express ist ein eigener Paketdienst, getrennt von [DHL Paket](carrier:dhl) und [DHL eCommerce](carrier:dhl-ecommerce), jeder mit eigenen Nummern und eigener Sendungsverfolgung. Pakete ohne 10-stellige Frachtbriefnummer oder `JD01`-Nummer laufen meist über einen von ihnen.

### Wo finde ich die DHL Waybill Number?

Die Waybill Number ist die 10-stellige Frachtbriefnummer. Sie steht meist in der Versand-E-Mail oder Bestellbestätigung des Shops. Die `JD01`-Nummer gehört nur zu einem einzelnen Paket.

### Kann ich DHL Express ohne Frachtbriefnummer verfolgen?

In Peek nicht: Es braucht die Frachtbriefnummer oder eine `JD01`-Nummer vom Etikett. Laut dhl.de kannst du mit der Referenznummer des Versenders die „Referenzsuche“ in DHLs Express-Sendungsverfolgung auf dhl.de nutzen; sonst frag den Shop oder Versender.

### Ist eine SMS von DHL Express echt?

Nicht unbedingt. Laut den Sicherheitshinweisen auf dhl.de enthalten gefälschte SMS oft einen Kurzlink, etwa mit bit.ly, und statt einer Absendernummer eine allgemeine Bezeichnung wie „Lieferung“, und DHL fordere nie per SMS zu Zahlungen auf. Echte E-Mails von DHL kommen von @dhl.com, @dhl.de oder einer anderen DHL-Domain, nie von Gratis-Adressen. Die FAQ zur Express-Sendung auf dhl.de zeigt allerdings echte Zahlungsaufforderungen für Einfuhrabgaben: eine SMS mit Link und dem Absender „DHL Express“ und eine E-Mail von „DHL Express <noreply@dhl.com>“. Am sichersten zahlst du also nicht über einen Link, sondern gibst die Sendungsnummer selbst bei On Demand Delivery ein; dort kannst du die Abgaben auch ohne Link bezahlen. Eine verdächtige SMS schickst du als Screenshot, mit der Nummer des Absenders, an phishing@dhl.com. Mehr in [Fake-Paket-SMS erkennen](guide:delivery-scam-texts).

:::sources
- [DHL: Express-Sendung verpasst (dhl.de)](https://www.dhl.de/de/privatkunden/pakete-empfangen/express-sendung-empfangen/express-sendung-verpasst.html) – Service Point oder Filiale, Referenznummer, am selben Tag abholen oder Zweitzustellung, Ausweis, Vollmacht, sieben Tage, E-Mail mit der Sendungsnummer
- [DHL: Express-Sendung umleiten (dhl.de)](https://www.dhl.de/de/privatkunden/pakete-empfangen/express-sendung-empfangen/express-sendung-umleiten.html) – kostenloses On Demand Delivery, Optionen, Link aus SMS oder E-Mail, nur nach Freischaltung durch den Versender
- [DHL: FAQ zur Express-Sendung (dhl.de)](https://www.dhl.de/de/privatkunden/pakete-empfangen/express-sendung-empfangen/faq.html) – bar, per QR-Code oder vorab online zahlen, keine Zustellung ohne Zahlung oder Zusage des Versenders, Absender echter Zahlungs-SMS und -E-Mails, Zahlung ohne Link
- [DHL Express: Tracking & Tracing FAQ (dhl.de)](https://www.dhl.de/de/geschaeftskunden/express/verfolgen/tracking-tracing-faq.html) – Referenzsuche, Kontakt-Hinweis bei fehlenden Angaben, Zweitzustellung mit der Benachrichtigungskarte
- [DHL Express: Kundenservice-FAQs (dhl.de)](https://www.dhl.de/de/geschaeftskunden/express/infos-knowhow/kundenservice-faqs.html) – DHL legt Zölle aus, Zahlung auch ohne Annahme, verlorene Sendung früh melden
- [DHL Express: Kontakt per Telefon (dhl.de)](https://www.dhl.de/de/geschaeftskunden/express/kontakt-express/telefon.html) – Kundenservice, Sendung verpasst, Steuern und Einfuhrzölle, Zeiten, DHL Paket mit eigener Nummer
- [DHL Express: WhatsApp (dhl.de)](https://www.dhl.de/de/geschaeftskunden/express/kontakt-express/whatsapp.html) – WhatsApp-Nummer und Zeiten
- [DHL: Sicherheitshinweise (dhl.de)](https://www.dhl.de/de/toolbar/footer/sicherheitshinweise.html) – Kurzlinks, allgemeiner Absender, keine Zahlungsaufforderung per SMS, echte Absenderdomains, phishing@dhl.com
- [Zoll: Internetbestellungen](https://www.zoll.de/DE/Privatpersonen/Postsendungen-Internetbestellungen/Sendungen-aus-einem-Nicht-EU-Staat/Zoll-und-Steuern/Internetbestellungen/internetbestellungen_node.html) – Kurierdienst meldet an und legt aus, Zahlung bei Zustellung, Servicepauschale, 3 € seit 1. Juli 2026, 19 bzw. 7 %
- [Ihr Europa: Versand und Lieferung](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_de.htm) – 30 Tage, Nachfrist, Rücktritt, Haftung des Händlers, 3 € je Position in der EU
- [Universal Parcel Scraper: DHL Express](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/dhl-express) – Frachtbrief- und Packstück-Nummern, Prüfziffer, andere 10-stellige Nummern, neu vergebene Nummern, DHL Express' Scans und Peeks Status dazu, Unterschrift bei DHL, was Peek bei DHL Express liest
:::
