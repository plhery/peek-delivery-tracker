---
title: DHL Freight Sendungsverfolgung: ActiveTracing & Hilfe
description: DHL Freight Sendung über ActiveTracing verfolgen: JND-Nummern, Auftragscodes, Sendungsnummern, SSCC, Avisierung, Status-Bedeutungen und Hilfecenter.
slug: dhl-freight-sendungsverfolgung
published: 2026-11-23
updated: 2026-11-23
---

DHL Freight (*DHL Freight GmbH*, Teil der DHL Group) ist der europäische Straßen- und Schienenfracht-Spezialist von DHL mit Niederlassungen in über 40 Ländern und einem jährlichen Transportvolumen von mehr als 45 Millionen Tonnen im Stückgut- (LTL), Teilladungs- (PTL), Komplettladungs- (FTL), temperaturgeführten und Bahnfracht-Verkehr. Neben B2B-Palettenware (etwa *EuroConnect*) stellt DHL Freight auch sperrige Privatkundensendungen (*DHL Home Delivery*) sowie schwedische *DHL Service Point*-Sendungen zu. Du kannst Sendungen über [DHL ActiveTracing](https://activetracing.dhl.com/DatPublic/datSelection.do) abfragen oder deine Nummer oben einfügen.

## DHL Freight Sendungsnummern: Auftragscode, Sendungsnummer und JND-Lizenzplatte

[DHL ActiveTracing](https://activetracing.dhl.com/DatPublic/datSelection.do) akzeptiert mehrere Nummernformate; Sendungsdaten bleiben **sechs Monate** lang abrufbar:

| Format (erfunden) | Was es ist |
| --- | --- |
| `JND48291736405829173` | 20-stellige **DHL Package ID / Lizenzplatte** (`JND` + 17 Ziffern). |
| `FRA-EC-4829173` | **DHL Auftragscode**: 3-stelliger Terminalcode + 2-stelliges Produktkürzel (z. B. `EC` für EuroConnect) + 7 Ziffern (mit oder ohne Bindestriche). |
| `4829173640` | **Sendungsnummer (Consignment ID)**: 9, 10 oder 11 Ziffern (10-stellig häufig bei schwedischen *DHL Service Point*-Inlandssendungen). Teilt sich die 10-stellige Länge mit [DHL](carrier:dhl), [Mondial Relay](carrier:mondial-relay), [Relais Colis](carrier:relais-colis) und [Packeta](carrier:packeta). |
| `00348291736405829177` | 20-stellige **GS1-SSCC** (NVE) beginnend mit `00` und gültiger GS1-Prüfziffer. Teilt sich die 20-stellige SSCC-Form mit [USPS](carrier:usps) und [NZ Post](carrier:nz-post). |

Normale Pakete in Deutschland laufen über [DHL](carrier:dhl) (*DHL Paket*) und internationale Luftfracht-Expresspakete über [DHL Express](carrier:dhl-express), während Paletten, Speditionsware und europäische Straßen-/Bahnfracht über DHL Freight ActiveTracing laufen. Siehe [Sendungsnummern-Formate](guide:tracking-number-formats).

## Was die DHL Freight ActiveTracing-Status bedeuten

ActiveTracing zeigt zu jeder Zeile eine Statusmeldung auf Englisch oder Deutsch, das Terminal (z. B. `DE-KOBLENZ`) sowie Datum und Uhrzeit des Terminals, ohne Zeitzone:

| DHL Freight meldet (DE / EN) | Bedeutung für dich | Peek zeigt |
| --- | --- | --- |
| `Auftrag erfasst`, `In Abholung` / `Consignment created`, `Collection arranged - OK`, `Out for Collection`, `Not Collected - ...` | Auftrag ist angelegt oder Fahrer auf dem Weg zur Abholung; die Ware liegt noch beim Absender. | „Etikett erstellt“ |
| `Abholung` / `Collected`, `Shipment handover by sender` | Beim Absender abgeholt oder am DHL Service Point eingeliefert. | „Beim Paketdienst eingegangen“ |
| `Empfangen im Terminal`, `Verladen`, `Avisierung`, `Avisierung beim Empfänger` / `Received at terminal`, `Shipped from terminal`, `Arrival at destination`, `Loaded into ferry`, `Direct delivery`, `Receiver notified`, `Awaiting delivery appointment`, `Delivery appointment booked` | Durchläuft DHL Freight Terminals, Fährverbindungen oder wartet im Zielterminal auf die Terminabstimmung (Avisierung). | „Unterwegs“ |
| `On hold - in customs clearance` | Sendung befindet sich in der grenzüberschreitenden Zollabfertigung (z. B. Schweiz, UK oder Norwegen). | „Beim Zoll“ |
| `In Zustellung` / `Out for Delivery` | Auf dem Zustellfahrzeug zur Auslieferung heute. | „In Zustellung“ |
| `Shipment in warehouse - ready for self collection by consignee` | Abholbereit für Selbstabholung im lokalen DHL Freight Terminal. | „Abholbereit“ |
| `Attempted DHL delivery - ...` | Zustellversuch gescheitert (z. B. Empfänger geschlossen oder Annahme verweigert). | „Zustellversuch“ |
| `Shipment in warehouse - waiting for consignee payment`, `partly delivered`, `Exception occurred - Strike`, `consignment cancelled by sender` | Warten auf Empfängerzahlung, Teillieferung mit Fehlmenge, Streik oder Stornierung. | „Problem gemeldet“ |
| `New shipping instruction - shipment returned` | Rücksendung an den Absender gemäß neuer Weisung. | „Zurück an Absender“ |
| `Zugestellt` / `Delivered`, `Received by consignee`, `Picked up by receiver` | Zugestellt oder vom Empfänger abgeholt. | „Zugestellt“ |

## Avisierung, Zoll und Besonderheiten bei Speditionsware

Laut [DHL Freight Deutschland](https://www.dhl.com/de-de/home/freight.html) und dem [DHL Freight Hilfecenter](https://www.dhl.com/de-de/home/freight/help-center-for-european-road-and-rail.html):

- **Terminabstimmung (*Avisierung*):** Eine Sendung kann im Zielterminal bei `Shipment in warehouse - Awaiting delivery appointment` warten, bis ein Liefertermin mit dem Empfänger abgestimmt ist; danach folgt `Delivery appointment booked`. Wartet sie, frag beim Versender oder beim Zielterminal nach.
- **6 Monate Abrufbarkeit:** [activetracing.dhl.com](https://activetracing.dhl.com/DatPublic/datSelection.do) speichert Sendungsdaten für die **letzten sechs Monate** (verfügbar auf Deutsch, Englisch, Spanisch, Französisch, Schwedisch, Polnisch und Italienisch).
- **Zollabfertigung:** Grenzüberschreitende Fracht zwischen der EU und Drittländern (z. B. Schweiz, Vereinigtes Königreich, Norwegen) durchläuft den Zoll (`On hold - in customs clearance`). Bei Online-Bestellungen aus Nicht-EU-Ländern in die EU gelten gemäß der [EU-Zollreform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) der 3-Euro-Zollsatz bis 150 Euro sowie die EU-Bearbeitungsgebühr von 2 Euro pro Artikel; siehe [Pakete beim Zoll](guide:customs).

## DHL Freight Sendung verzögert, beschädigt oder nur teilweise geliefert

Da der Frachtvertrag zwischen Versender und DHL Freight besteht, kontaktiere bei Problemen zuerst den Händler bzw. Versender und halte die Nummer aus ActiveTracing bereit.

- **Sichtbare Schäden oder Fehlmenge (`partly delivered`):** Prüfe Ware und Packstückzahl bei Anlieferung und melde Schäden oder Fehlmengen sofort dem Versender. Im Hilfecenter erklärt DHL Freight, wie man einen Schadenersatzanspruch vorbereitet (*So bereiten Sie einen Schadenersatzanspruch vor*).
- **Warten auf Avisierung oder Zahlung:** Melde dich bei `Awaiting delivery appointment` oder `waiting for consignee payment` direkt beim im letzten Scan genannten Zielterminal oder beim Versender.
- Weitere Hilfe: [warum sich die Sendungsverfolgung nicht aktualisiert](guide:tracking-not-updating), [zugestellt, aber nicht erhalten](guide:delivered-not-received), [beschädigtes Paket](guide:damaged-parcel), [verlorenes Paket](guide:lost-parcel) und [zurück an Absender](guide:returned-to-sender).

## DHL Freight Kundenservice und Kontakt

Offizielle Anlaufstellen auf [DHL Freight Deutschland](https://www.dhl.com/de-de/home/freight.html) und im [DHL Freight Hilfecenter](https://www.dhl.com/de-de/home/freight/help-center-for-european-road-and-rail.html):

- **Hilfecenter (Deutsch & Englisch):** [dhl.com/de-de/home/freight/help-center-for-european-road-and-rail.html](https://www.dhl.com/de-de/home/freight/help-center-for-european-road-and-rail.html) und [englische Ausgabe](https://www.dhl.com/de-en/home/freight/help-center-for-european-road-and-rail.html) — FAQs, Schadenmeldung, Incoterms 2020, AGB und Gefahrgutinfos.
- **Viva und Terminalsuche:** Im Hilfecenter findest du **Viva**, den KI-gestützten Kundenservice-Assistenten von DHL Freight, und *Frachtterminals und Standorte* mit Terminal- und Bürostandorten nach Land.
- **Geschäftskundenportal:** *myDHLFreight*, mit Tutorial-Videos und FAQs im Hilfecenter.

> Ignoriere unaufgeforderte SMS oder E-Mails, die über einen fremden Link eine sofortige Kartenzahlung zur „Freigabe“ einer DHL-Freight-Sendung fordern. Prüfe den Status immer direkt auf [activetracing.dhl.com](https://activetracing.dhl.com/DatPublic/datSelection.do) oder beim zuständigen Terminal. Siehe [gefälschte Liefer-SMS](guide:delivery-scam-texts).

## DHL Freight Sendung mit Peek verfolgen

[Peek](/) fragt bei einer `JND`-Lizenzplatte (`JND48291736405829173`, erfunden) nur DHL Freight und zeigt „DHL Freight hat dieses Paket.“, sobald ActiveTracing sie kennt. Bei einer 10-stelligen Sendungsnummer (`4829173640`) fragt Peek DHL Freight zusammen mit den anderen Paketdiensten mit zehnstelligen Nummern, wenn du aus Deutschland, Schweden, Österreich, Polen, den Niederlanden, Belgien, Frankreich, Italien, Spanien oder dem Vereinigten Königreich verfolgst, anderswo nur als letzte Möglichkeit. Eingefügte `activetracing.dhl.com/DatPublic/datSelection.do`-Links (`a=`, `valuePackageField=` oder `valueShipmentOrderField=`) erkennt Peek ebenfalls. Bei Auftragscodes (`FRA-EC-4829173`), 9- oder 11-stelligen Sendungsnummern oder SSCC (`00348291736405829177`) tippe beim Hinzufügen auf „Ändern“ oder auf der Sendungsseite auf „Paketdienst ändern“ und wähle DHL Freight (auch unter „DHL ActiveTracing“, „DHL Frachtverfolgung“ oder „DHL Freight GmbH“ zu finden).

Peek liest die englische ActiveTracing-Seite direkt und zeigt die Stufe („Etikett erstellt“, „Beim Paketdienst eingegangen“, „Unterwegs“, „Beim Zoll“, „In Zustellung“, „Abholbereit“, „Zustellversuch“, „Problem gemeldet“, „Zurück an Absender“, „Zugestellt“), die englischen Statusmeldungen mit dem Terminal (z. B. `KOBLENZ, DE`), das Bruttogewicht und das Zielland. Jede Zeile datiert Peek in der Zeitzone ihres Terminals und zeigt sie in der Uhrzeit deines Geräts. Den Produktnamen zeigt keine Peek-App. Den Ort des Empfängers, den einzigen Teil der Adresse, den ActiveTracing zeigt, kann deine eigene Karte als Ziel der Route zeigen, solange die Sendung unterwegs ist; geteilte Links zeigen ihn nicht. Peek verwirft Absenderort, Packstückzahl, Volumen, Abfahrtsdatum, separaten Lieferzeitpunkt, weitere Referenzen, Abliefernachweis und Unterschrift; ActiveTracing liefert kein voraussichtliches Lieferdatum, keine Statuscodes und keine Absender- oder Empfängernamen.

Solange du die Sendung in Peek geöffnet hast, oder an ihrem Zustelltag mit aktivierten Benachrichtigungen, prüft Peek bis zu alle 10 Minuten; an dem Tag, an dem ActiveTracing `Out for Delivery` zeigt, alle 2 Minuten (alle 5, wenn die letzte Abfrage einen Browser geöffnet hat) ab 6 Uhr nach der Uhr am Ort des neuesten Scans, solange ActiveTracing antwortet; sonst stündlich. Nach 12 Stunden im Status „Etikett erstellt“ oder „Beim Paketdienst eingegangen“ oder nach zwei Tagen ohne Neuigkeiten prüft Peek stündlich, egal wie du die Sendung verfolgst, und nach sieben Tagen weiterhin bei „Etikett erstellt“ alle 6 Stunden. Antwortet ActiveTracing nicht und verfolgt Peek die Sendung nur über Tracking-Dienste, prüft Peek stündlich, nach einem Tag ohne Neuigkeiten alle 3 Stunden und am Tag der Zustellung bis zu alle 15 Minuten.

Bleibt eine Sendung bei „Etikett erstellt“, „Beim Paketdienst eingegangen“ oder „Unterwegs“ vier Tage lang ohne neuen Scan, markiert Peek sie mit dem Tag dieses letzten Scans (zum Beispiel „Keine Aktualisierung seit Mo 12. Okt“), zeigt „Auf langen Flügen, beim Zoll oder übers Wochenende wird es oft still um ein Paket. Peek fragt weiter bei DHL Freight nach.“ und den Link „Hol dir Hilfe bei DHL Freight“ zum Hilfecenter von DHL Freight. Nach mehr als drei Wochen ohne neuen Scan erscheint: „Seit über drei Wochen nichts Neues. Ist das Paket noch nicht angekommen, frag beim Verkäufer nach. Peek fragt weiter bei DHL Freight nach.“

Du kannst Mitteilungen für „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“ wählen. Peek verfolgt lediglich den Sendungsstatus und kann weder eine Avisierung vereinbaren noch Frachtgebühren begleichen oder ein DHL Freight Terminal kontaktieren.

## Häufige Fragen zu DHL Freight

### Was ist der Unterschied zwischen DHL Freight, DHL Paket und DHL Express?

DHL Freight transportiert europäische Straßen- und Bahnfracht (Paletten, Stückgut, Teilladungen, sperriges *DHL Home Delivery*) über [ActiveTracing](https://activetracing.dhl.com/DatPublic/datSelection.do), während Standardpakete über [DHL](carrier:dhl) und internationale Luftfracht-Expresssendungen über [DHL Express](carrier:dhl-express) laufen.

### Welche Nummern funktionieren in DHL ActiveTracing?

ActiveTracing akzeptiert DHL Auftragscodes (`FRA-EC-4829173`, erfunden), 9- bis 11-stellige Sendungsnummern (`4829173640`), 20-stellige `JND`-Lizenzplatten (`JND48291736405829173`) und 20-stellige GS1-SSCC-Codes (`00348291736405829177`).

### Warum steht meine Sendung auf `Avisierung` bzw. `Awaiting delivery appointment`?

Das Zielterminal hält die Sendung, bis ein Liefertermin mit dem Empfänger abgestimmt ist. Frag beim Versender nach oder beim Terminal aus der letzten Zeile.

### Wie lange sind Sendungen in DHL ActiveTracing abrufbar?

Laut [ActiveTracing](https://activetracing.dhl.com/DatPublic/datSelection.do) bleiben Sendungsdaten für die **letzten sechs Monate** online verfügbar.

:::sources
- [DHL Freight: ActiveTracing](https://activetracing.dhl.com/DatPublic/datSelection.do) – Abfrage nach Sendungsnummer, Auftragscode und Package ID/Lizenzplatte, 6 Monate Verfügbarkeit, *DHL Freight GmbH*
- [DHL Freight: Startseite Deutschland](https://www.dhl.com/de-de/home/freight.html) – über 40 Länder, mehr als 45 Mio. Tonnen jährlich, LTL/PTL/FTL, GoGreen Plus Flex, Bahnfracht Europa und Terminalnetz
- [DHL Freight: Hilfecenter Deutschland](https://www.dhl.com/de-de/home/freight/help-center-for-european-road-and-rail.html) – Hilfecenter für europäischen Straßen- und Schienentransport
- [DHL Freight: Help Center (Englisch)](https://www.dhl.com/de-en/home/freight/help-center-for-european-road-and-rail.html) – KI-Assistentin Viva, Terminal-Finder, Schadenmeldung, Incoterms 2020 und myDHLFreight (Hilfe-URL in Peek)
- [Europäische Kommission: EU-Zollreform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 3-Euro-Zoll bis 150 Euro und 2-Euro-Bearbeitungsgebühr der EU
- [Universal Parcel Scraper: dhl-freight](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/dhl-freight) – `JND`, Auftragscode, 9–11-stellige Sendungsnummer und SSCC, `activetracing.dhl.com`-Linkregeln, Statuszuordnung (EN/DE), die Ortszeit jedes Terminals, ausgelesene Felder und Hilfe-URL
:::
