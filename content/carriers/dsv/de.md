---
title: DSV Sendungsverfolgung: Nummern, Status & DB Schenker
description: DSV- oder ehemalige DB-Schenker-Sendung verfolgen (Straße, Luft, See): Nummernformate, Statusbedeutung, Zustellung, Reklamation und Kontakt.
slug: dsv-sendungsverfolgung
published: 2026-11-23
updated: 2026-11-23
---

DSV ist ein dänischer Transport- und Logistikkonzern für Straßen-, Luft- und Seefracht, der 2025 Schenker (DB Schenker) von der Deutschen Bahn übernommen hat. Auf dsv.com hat das Feld „Track your shipment“ zwei Eingaben: „Track DSV shipment“ öffnet DSVs Tracker myDSV, „Track Schenker shipment“ den früheren DB-Schenker-Tracker. Ein Konto ist nicht nötig. Die Nummer erhältst du vom Auftraggeber des Transports, meist vom Absender oder Shop – füge sie oben ein.

## DSV-Sendungsnummer: Aufbau und Fundort

Die Nummer liegt bei dem Unternehmen, das DSV beauftragt hat: Schau in die Versandmail oder Bestellseite des Shops oder frag den Absender. Mehr unter [Sendungsnummer finden](guide:find-tracking-number).

Während DSV Schenker in seine Systeme integriert, bleiben beide Tracker parallel bestehen: Eine bei Schenker gebuchte Sendung steht unter Umständen nur im Schenker-Tracker. Zeigt einer nichts, probiere den anderen.

| Nummer (erfunden) | Was es ist |
| --- | --- |
| `DEKLM482917365` | 14 Zeichen: europäischer Ländercode wie DE, SE, DK oder PL, drei Buchstaben und neun Ziffern. Frühere DB-Schenker-Landverkehrsnummer und das Format im Peek-Katalog für DSV. |
| `KLM4829173` | Drei Buchstaben und sieben oder acht Ziffern: weiteres früheres DB-Schenker-Landverkehrsformat. Peek erkennt DSV hieran nicht automatisch. |
| `094827361549283710` | 18 Ziffern (20 mit `00` davor): Packstück-Barcode einer früheren DB-Schenker-Landverkehrssendung. |
| Buchstaben und Ziffern | Eine myDSV-Sendungs-, Buchungs- oder House-Bill-Nummer. Peek fragt myDSV mit beliebigen 5 bis 40 Zeichen aus Buchstaben und Ziffern (mindestens eine Ziffer) ab. |
| `mydsv.com/track-shipment?query=DEKLM482917365` | Link zum myDSV-Tracker, wie ihn „Track DSV shipment“ auf dsv.com öffnet. |

Eine Kunden- oder Bestellreferenz sowie ein Master Air Waybill einer Fluggesellschaft finden Sendungen zwar in der myDSV-Suche, Peek folgt ihnen jedoch nicht: Nutze DSVs Sendungs-, Buchungs- oder House-Bill-Nummer. Ebenso wenig kann Peek eine einzelne Sendung auswählen, wenn eine Nummer mehrere Treffer hat.

## Was die DSV-Statusmeldungen bedeuten

myDSV protokolliert jeden Scan von der Buchung bis zum Abliefernachweis. Die öffentliche Ansicht liefert Überblicksdaten, die private Ansicht auf Anfrage alle Details. Die myDSV-Schritte, wie Peek sie liest:

| DSV schreibt | Was das für dich bedeutet | Peek-Status |
| --- | --- | --- |
| `Booking` | DSV hat die Buchung erfasst, datiert ohne Uhrzeit. | „Etikett erstellt“ |
| `Creation` | Die Sendung wurde in DSVs Landverkehrssystem angelegt. | „Etikett erstellt“ |
| `Actual pickup` | DSV hat die Ware beim Absender abgeholt. | „Beim Paketdienst eingegangen“ |
| `Cargo received` | Die Ware ist bei DSV eingetroffen, am Terminal oder durch Abholung. | „Beim Paketdienst eingegangen“ |
| `Actual Departure`, `Actual departure - Origin Port` | Abgang von Hafen oder Flughafen auf dem Hauptlauf. | „Unterwegs“ |
| `Actual arrival`, `Actual arrival- Destination Port` | Ankunft an Hafen oder Flughafen auf dem Hauptlauf. | „Unterwegs“ |
| `Handover responsibilty of cargo`, `Handover of Cargo` | DSV hat die Fracht am Ende seines Auftrags übergeben und schließt die Sendung ab (die erste Schreibweise stammt so von DSV). | „Zugestellt“ |
| `Actual delivery` | Zugestellt. | „Zugestellt“ |

`Estimated Departure`, `Estimated arrival`, `Estimated pickup`, `Estimated delivery`, `Requested pickup` und `Requested delivery` sind Planungen, keine Scans: Peek lässt sie aus dem Verlauf weg und zeigt `Estimated delivery` als voraussichtlichen Liefertag oder -zeitraum bis zur Zustellung.

Der frühere DB-Schenker-Tracker deckt Landverkehrssendungen mit eigenen Formulierungen ab:

| DB-Schenker-Tracker schreibt | Was das für dich bedeutet | Peek-Status |
| --- | --- | --- |
| `Booked` | Der Absender hat den Transport gebucht. | „Etikett erstellt“ |
| `Out for collection` | Ein Fahrer ist auf dem Weg zum Absender. | „Etikett erstellt“ |
| `Collected` | Beim Absender abgeholt. | „Beim Paketdienst eingegangen“ |
| `Delivered to terminal by shipper` | Vom Absender an einem Terminal angeliefert. | „Beim Paketdienst eingegangen“ |
| `Arrived`, `Departed` | An einem Terminal eingetroffen oder abgefahren. | „Unterwegs“ |
| `Customs clearance initiated` | Zollabfertigung gestartet. | „Beim Zoll“ |
| `Customs clearance finalised` | Verzollung abgeschlossen, wieder unterwegs. | „Unterwegs“ |
| `Out for Delivery` | Auf dem Zustell-Lkw. | „In Zustellung“ |
| `Not delivered` | Zustellung fehlgeschlagen; ein Grund kann folgen. | „Zustellversuch“ |
| `To consignee's disposal`, `Ready for pick up` | Bereit zur Abholung. | „Abholbereit“ |
| `Delivered` | Zugestellt. | „Zugestellt“ |
| `Picked up by consignee`, `Picked up at agent` | Vom Empfänger oder bei einem Agenten abgeholt. | „Zugestellt“ |
| `ePOD available` | Elektronischer Abliefernachweis verfügbar. | „Zugestellt“ |

Unterwegs erscheinen außerdem `Linehaul departed`, `Linehaul arrived`, `Terminal inventory`, `Vessel Loaded`, `Vessel Unloaded`, `Train Loaded`, `Train Unloaded`, `Vessel/Train Loaded` und `Vessel/Train Unloaded` – alle als „Unterwegs“ in Peek. `Not Collected` (Abholung gescheitert) und `Not loaded` (geplante Verladung verpasst, mit Grund) erscheinen als „Problem gemeldet“. Nennt der Tracker neben einem Schritt einen Grund, hängt Peek diesen an. Mehr zu den Stufen unter [Bedeutung von Tracking-Status](guide:tracking-statuses).

## Wie DSV liefert

**Paletten und Teilladungen.** DSV bezeichnet sein Sammelgutnetz als Europas größtes: über 36 Länder, mehr als 400 Terminals und Sendungen bis 2.500 kg – eine Palette oder sperrige Bestellung kann also per Lkw von einem DSV-Terminal kommen.

**Online-Bestellungen.** DSV Parcel E-commerce sammelt Pakete von Händlern ein, bündelt sie grenzüberschreitend und lässt lokale Letzte-Meile-Netze zustellen – möglicherweise bringt also ein anderer Paketdienst dein Paket. Nennt die Versandmail einen oder gibt eine zweite Nummer an, verfolge auch diese: Peeks DSV-Abfrage zeigt keinen nachfolgenden Dienst an.

**Updates und Abliefernachweis.** In myDSV lassen sich Benachrichtigungen (z. B. Abholung oder Verzollung) einrichten, geschätzte Ankunftszeiten für Straßenfracht abrufen und nach Abschluss der Abliefernachweis herunterladen. Hast du DSV nicht selbst gebucht, frag den Absender. Wie viele Zustellversuche DSV macht oder wie lange ein Terminal Ware lagert, steht auf den Seiten nicht: Wende dich an die zuständige DSV-Niederlassung.

**Zoll.** In den meisten Ländern mit eigenen Standorten ist DSV zugelassener Zollagent, anderswo arbeitet es mit Partnern. Bei Fragen zur Verzollung verweist DSV an DSV im jeweiligen Land. In Irland und der übrigen EU gelten für Online-Käufe aus Drittländern seit dem 1. Juli 2026 ein EU-Zoll von 3 € je Produktart bis 150 € und seit dem 1. November 2026 eine Unions-Bearbeitungsgebühr von 2 € je Produktart unabhängig vom Wert. Anderswo gelten nationale Regeln: siehe [Paket beim Zoll](guide:customs).

## DSV-Sendung verspätet, hängen geblieben oder beschädigt

**Keine Bewegung.** Stockt ein Tracker, probiere auf dsv.com den anderen: Eine Schenker-Sendung ist eventuell noch nicht in myDSV. Frag danach beim Absender nach, der DSV beauftragt hat. Siehe [Tracking aktualisiert sich nicht](guide:tracking-not-updating).

**Beschädigt oder fehlend.** Bei Online-Bestellungen wende dich zuerst an den Shop als Auftraggeber. Über DSV eClaims lassen sich Schäden samt Unterlagen einreichen, verfolgen und direkt mit lokalen Schadensregulierern klären; eine Frist nennt DSV dort nicht, warte also nicht. Siehe [beschädigte Pakete](guide:damaged-parcel) und [verlorene Pakete](guide:lost-parcel).

**Missbrauch des Namens DSV.** DSV warnt, dass unverbundene Firmen oder Personen gelegentlich den Namen DSV nutzen, um Frachtdienste anzubieten. Bei Zweifeln an einer Website oder Kontaktperson frag bei deiner örtlichen DSV-Niederlassung nach; zu verdächtigen Paket-SMS siehe [Betrugs-SMS zu Paketen](guide:delivery-scam-texts).

## DSV Kontakt und Kundenservice

Auf dsv.com:

- **Kontaktformular:** auf der [Hilfe- und Kontaktseite von DSV](https://www.dsv.com/en/support/help-and-contact).
- **Standorte vor Ort:** DSV ist in über 90 Ländern vertreten; [lokale DSV-Niederlassung finden](https://www.dsv.com/en/support/find-dsv) nach Land.
- **Schadensmeldungen:** über eClaims auf der [DSV-Claims-Seite](https://www.dsv.com/en/support/self-services/claims).
- **DSV A/S:** Hedehusene, Dänemark, +45 43 20 30 40 laut Impressum – das ist keine Tracking-Hotline.

Eine zentrale Telefonnummer für die Sendungsverfolgung nennt DSV nicht.

## DSV-Sendung mit Peek verfolgen

Füge eine 14-stellige Nummer wie `DEKLM482917365` ein: Peek fragt beim Hinzufügen bei DSV nach („Tracking-Dienste werden geprüft…“) und zeigt „DSV hat dieses Paket.“, sobald DSV sie kennt. Ist sie noch nicht gelistet, zeigt die Karte „Warten auf den ersten Scan von DSV“ mit dem Hinweis „Wahrscheinlich DSV“, während Peek weiter fragt; nach einer Woche bittet Peek, die Nummer zu prüfen oder einen anderen Paketdienst zu wählen.

Andere Nummernformate erkennt Peek nicht automatisch als DSV. Füge sie trotzdem ein: Peek verfolgt über 4500 Paketdienste. Um DSV selbst zu wählen, tippe auf „Ändern“ und suche nach DSV – auch „Schenker“ findet es („Auch DB Schenker“). Bei einem gespeicherten Paket nutze „Paketdienst ändern“. Ein mydsv.com-Link (`?query=`) ordnet die Sendung sofort DSV zu, ebenso ein Schenker-Link von dsv.com mit `refNumber=`.

Peek fragt zuerst myDSV ab. Kennt myDSV eine frühere DB-Schenker-Landverkehrsnummer oder einen Packstück-Barcode nicht, wechselt Peek zum Schenker-Tracker – dort aber nur für Straßenfracht. Luft- oder Seefracht, die nur dort steht, prüfst du direkt unter „Track Schenker shipment“ auf dsv.com.

Peek zeigt Status, jeden Schritt im DSV-Wortlaut samt Ort und Land sowie den erwarteten Liefertag. DSV liefert Ortszeiten ohne Zeitzone: Peek leitet die Zone aus Ort, Hafen oder Flughafencode oder benachbarten Scans ab, sonst gilt Kopenhagener Zeit, was vereinzelt abweichen kann. Peek lässt Servicename, Absenderort, deine Adresse, Postleitzahlen, DSVs Fortschrittsbalken sowie beim Schenker-Tracker Beteiligte, Referenzen, Güter und den Namen des Unterzeichners weg.

Übergibt das schwedische Unternehmen Early Bird dein Paket an DSV oder DB Schenker, nennt Peek DSV als zustellenden Paketdienst und folgt DSV, sobald neuere Scans vorliegen.

Peek prüft bis zu alle 10 Minuten, am Tag der Zustellung ab 6:00 Uhr Ortszeit alle 2 Minuten, solange DSVs Tracker antwortet (über Tracking-Dienste stündlich, am Zustelltag bis zu alle 15 Minuten bei geöffneter Ansicht oder aktiven Benachrichtigungen), und benachrichtigt dich nach Wahl bei „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“. Nach zwei Tagen ohne Neuigkeiten (12 Stunden bei „Etikett erstellt“ oder „Beim Paketdienst eingegangen“) prüft Peek stündlich. Es kann weder DSV oder den Absender kontaktieren noch Lieferungen buchen oder Schäden melden.

## Fragen zur DSV-Sendungsverfolgung

### Ist DSV dasselbe Unternehmen wie DB Schenker?

Ja. DSV (gegründet 1976) hat die Übernahme von Schenker von der Deutschen Bahn am 30. April 2025 abgeschlossen, und dbschenker.com wurde in dsv.com integriert. Schenker-Sendungen behalten vorerst ihren eigenen Tracker.

### Wie verfolge ich jetzt eine DB-Schenker-Sendung?

Gib die Nummer auf dsv.com unter „Track Schenker shipment“ im Feld „Track your shipment“ ein oder füge sie oben ein; hat sie nicht 14 Zeichen mit Ländercode am Anfang, wähle DSV manuell aus. Peek fragt myDSV und bei Straßenfracht den Schenker-Tracker ab.

### Was bedeutet „Handover of cargo“ beim DSV-Tracking?

DSV hat die Ware am Ende seines Auftrags übergeben und die Sendung ist abgeschlossen – Peek zeigt „Zugestellt“. Hast du nichts erhalten, frag beim Absender nach, wo übergeben wurde, und lies [zugestellt, aber nicht erhalten](guide:delivered-not-received).

### Hat DSV eine Kundenservice-Telefonnummer?

Auf den Hilfeseiten gibt es keine Nummer für das Tracking. Nutze das Kontaktformular oder finde deinen lokalen DSV-Standort auf dsv.com. Die Nummer im Impressum von DSV A/S (+45 43 20 30 40 in Dänemark) ist keine Tracking-Hotline.

:::sources
- [DSV: Book and track shipments with myDSV](https://www.dsv.com/en/digital-solutions/book-and-track) – Tracking ohne Konto, zwei Tracking-Felder, Zusammenlegung von dbschenker.com mit dsv.com
- [DSV: myDSV](https://www.dsv.com/en/digital-solutions/mydsv) – öffentliche und private Ansicht, Scan-Protokoll, Benachrichtigungen, Abliefernachweis
- [DSV: Self-services](https://www.dsv.com/en/support/self-services) – DSV- und Schenker-Tracker, eClaims, Schenker-Systeme bis zur Migration
- [DSV: Frequently asked questions](https://www.dsv.com/en/support/faq) – Kontaktformular, zwei myDSV-Versionen während des Übergangs
- [DSV: Help and contact](https://www.dsv.com/en/support/help-and-contact) – über 90 Länder, Standortsuche
- [DSV: Find your local DSV office](https://www.dsv.com/en/support/find-dsv) – Büros nach Land
- [DSV: Claims](https://www.dsv.com/en/support/self-services/claims) – Funktion von eClaims
- [DSV: Groupage transport](https://www.dsv.com/en/our-solutions/modes-of-transport/road-transport/groupage) – 36+ Länder, 400+ Terminals, bis 2.500 kg
- [DSV: E-commerce delivery solutions](https://www.dsv.com/en/our-solutions/contract-logistics/ecommerce-solutions/last-mile-delivery) – grenzüberschreitende Konsolidierung, lokale Zustellnetze
- [DSV: Customs clearance](https://www.dsv.com/en/our-solutions/modes-of-transport/value-added-services/customs-clearance) – zugelassener Zollagent, Partner, DSV im jeweiligen Land kontaktieren
- [DSV: History](https://www.dsv.com/en/about-dsv/history) – gegründet 1976, Straße, Luft und See, Kauf von Schenker 2025
- [DSV: DSV completes acquisition of Schenker](https://www.dsv.com/en/about-dsv/press/news/dsv-completes-acquisition-of-schenker-2628130) – Abschluss am 30. April 2025, Integration
- [DSV: Legal notice](https://www.dsv.com/en/about-dsv/legal-notice) – DSV A/S in Dänemark, Telefon, Missbrauch des Namens DSV
- [Europäische Kommission: EU-Zollreform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 3 € Zoll je Produktart bis 150 €, 2 € Unions-Bearbeitungsgebühr
- [Europäische Kommission: Leitfaden zu 3-€-Zoll und Unions-Bearbeitungsgebühr (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 2 € je Produktart unabhängig vom Wert
- [Universal Parcel Scraper: DSV](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/dsv) – Nummernformate und Links, beide Tracker, DSV-Wortlaute und Stufen, Ortszeiten, was Peek liest
:::
