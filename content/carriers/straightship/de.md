---
title: Straightship Sendungsverfolgung: STRTD-Nummern & Zustellung
description: Straightship-Paket in Kanada verfolgen: Aufbau von STRTD-, STRCA- und STSH-Nummern, Bedeutung aller Status, CBSA-Zoll, SHEIN-Bestellungen und Kontakt.
slug: straightship-sendungsverfolgung
published: 2026-11-18
updated: 2026-11-18
---

Straightship (Straightship Solutions Limited) ist ein Logistikunternehmen mit Sitz in Burnaby, British Columbia (**Kanada**), das Waren aus Asien nach Nordamerika befördert und Pakete in ganz Kanada mit eigenen Zustellfahrern ausliefert. Auf seiner Website führt das Unternehmen unter anderem SHEIN als Partner auf. Stammt zu deiner Online-Bestellung eine Sendungsnummer, die mit `STRTD` oder `STRCA` beginnt, liegt sie bei Straightship; auch eine Nummer mit `STSH` am Anfang kann zu Straightship gehören. Verfolge das Paket auf [straightship.com](https://www.straightship.com/new/) im Feld „Track Your Shipment“ oder direkt im Suchfeld oben. Die Sendungsnummer erhältst du in der Versandbestätigung des Shops oder auf deiner Bestellseite.

## Straightship-Sendungsnummer: Aufbau und Beispiele

Straightship veröffentlicht keine offizielle Formatbeschreibung. Die Nummernformen, die Peek kennt, sehen mit erfundenen Ziffern so aus:

| Sendungsnummer | Format |
| --- | --- |
| `STRTD482910573` | `STRTD` und 9 Ziffern. |
| `STRTD4829105736491827` | `STRTD` und 16 Ziffern (auch 17 Ziffern kommen vor). |
| `STRCA4829105736491` | `STRCA` und 13 Ziffern. |
| `STSHYW48291057364` | `STSH`, zwei Buchstaben und 11 Ziffern. |

Kleinbuchstaben und Leerzeichen spielen beim Einfügen in Peek keine Rolle. Kennt Straightships Tracker eine Nummer nicht, antwortet er mit `No results found!`: Vergleiche die Nummer dann mit der E-Mail des Online-Shops. Du findest überhaupt keine Nummer? Sieh unter [Sendungsnummer finden](guide:find-tracking-number) nach – und bei einer SHEIN-Bestellung unter [SHEIN-Bestellungen verfolgen](guide:shein-orders).

## Straightship-Sendungsstatus erklärt

Der Tracker von Straightship ist auf Englisch gehalten. Er zeigt oben eine Zusammenfassung mit dem Feld „Package Status“ und darunter eine Verlaufstabelle mit Uhrzeit, Statusmeldung und Ort jedes Scans. Diese Zeilen durchläuft ein Paket in **Kanada** üblicherweise der Reihe nach:

| Meldung bei Straightship | Was das für dein Paket bedeutet |
| --- | --- |
| `Package information is created in the system` | Der Shop hat das Versandetikett angelegt. Straightship hat das Paket noch nicht: Es ist noch beim Händler oder unterwegs aus Asien. |
| `Package Received with Origin Scan warehouse` | Erster Scan von Straightship in Kanada, samt Stadt und Provinz. |
| `Package is received at a Straightship station` | Eingang in einem der Depots von Straightship (ältere Seiten zeigen `Arrived at Facility`). |
| `Package arrived at transit station` | In einem Transitdepot auf dem Weg zu deinem lokalen Zustelldepot. |
| `Package is in transit to a Straightship station` | Auf der Strecke zwischen zwei Depots vor dem ausliefernden Standort. |
| `Loading` | Wird im Depot für die Zustelltour verladen; erscheint mitunter zweimal im Abstand weniger Sekunden. |
| `Package will be delivered before 9:00pm` | Heute in Zustellung: Noch am selben Tag folgt die Auslieferung oder ein Zustellversuch. |
| `Attempted Delivery` | Der Fahrer hat die Zustellung versucht, konnte das Paket aber nicht übergeben. |
| `Shipment delayed` | Verzögerung ohne Angabe eines Grunds im Tracker. |
| `Delivered` | Zugestellt, mit Stadt und Provinz (in Peek als „Zugestellt“ angezeigt). |
| `Package is being returned to shipper` | Auf dem Rückweg an den Absender. |

Zwei Meldungen nach einem Zustellversuch nennen den nächsten Schritt: `An attempt was made to deliver your package-consignee closed (please e-mail SS@straightship.com to make arrangement)` bedeutet, dass die Lieferadresse geschlossen war; steht statt `consignee closed` der Zusatz `unable to leave package at safe location`, fand der Fahrer keinen sicheren Ablageort. Beide Zeilen bitten darum, per E-Mail an `SS@straightship.com` eine neue Zustellung zu vereinbaren.

Drei Punkte sorgen regelmäßig für Verwirrung:

- **Die Zusammenfassung hinkt hinterher.** Der „Package Status“ bleibt oft auf `ReadyForShipping` (oder `Ready For Shipping`), während das Paket längst zwischen den Depots unterwegs ist. Entscheidend ist die oberste Zeile des Verlaufs darunter; spätere Werte der Zusammenfassung lauten `Shipped`, `Delivered` und `ReturnedToSender`.
- **„In progress“ heißt nicht in Zustellung.** `Delivery is in progress` und `Your order is in Progress` tauchen nach einer nicht erfolgten Zustellung auf – teils erst Tage später und vor einer Rücksendung. Schreib Straightship eine E-Mail, wenn du sie siehst.
- **Die Uhrzeiten sind in UTC.** Unter dem Verlauf weist Straightship darauf hin, dass alle Zeitangaben in koordinierter Weltzeit (UTC) stehen: Ein Abendscan in Kanada trägt deshalb bereits das Datum des Folgetags.

Selbst nach `Package is being returned to shipper` kann ein Paket in einem Depot erneut gescannt und noch einmal auf Zustelltour geschickt werden – die Zeile ist nicht zwingend das Ende.

## Straightship-Zustellung in Kanada: Wer bringt das Paket?

Straightships Tracker gilt für **Kanada** und trägt auf der Ergebnisseite die Überschrift „Canada B2C Shipment Tracking“. Laut eigenen Angaben erreicht Straightships eigenes Zustellnetz über 95 % der kanadischen Bevölkerung und 90 % der kanadischen Postleitzahlen über mehr als 85 Zustellzentren; die Zustellmeldung kündigt die Lieferung bis 21:00 Uhr (`before 9:00pm`) an. Darüber hinaus übernimmt Straightship in **Kanada** die Zustellung für den chinesischen Logistiker [JS Express](carrier:js-express): Dessen Sendungsverfolgung zeigt Straightships Scans in Kanada, und sobald im Datensatz von JS Express eine `STRTD`- oder `STRCA`-Nummer hinterlegt ist, fragt Peek auch Straightship direkt ab.

Für Pakete, die Straightship aus den USA nach **Kanada** bringt, nennt die englische Leistungsseite verschiedene Optionen für die letzte Meile – das eigene Netz, [Canada Post](carrier:canada-post), Purolator oder [UPS](carrier:ups) –, während die chinesische Seite das eigene Netz, Canada Post, [FedEx](carrier:fedex) oder UPS aufführt. Für diesen Dienst gibt Straightship 6 bis 10 Tage Laufzeit und eine Verzollung innerhalb von 24 Stunden an. Im eigenen Tracker erscheinen jedoch ausschließlich Straightships eigene Stationen, ohne fremden Paketdienst oder Zweitnummer.

Für **Kanada** veröffentlicht Straightship keine Regeln dazu, wie viele Zustellversuche unternommen werden, ob eine Benachrichtigungskarte hinterlassen wird oder wie lange ein unzustellbares Paket im Depot lagert:

- **Nach einem fehlgeschlagenen Versuch:** Schreib wie im Scan-Text angegeben mit deiner Sendungsnummer an `SS@straightship.com`.
- **Adresse oder Telefonnummer ändern:** Nutze das Kontaktformular und wähle „Address and/or contact details update“.
- **Zustellnachweis:** Laut Startseite zeigt der Tracker den aktuellen Status sowie – falls vorhanden – ein Zustellfoto („POD image (Where available)“).

## Kanadischer Zoll bei einem Straightship-Paket aus Asien

In **Kanada** übernimmt Straightship die Zollabfertigung für seine Geschäftskunden; laut seiner chinesischen Leistungsseite kann es in Kanada die Steuern einziehen (`DDU`). Wirst du zur Zahlung für ein Straightship-Paket aufgefordert, frag zuerst beim Händler nach: SHEIN Canada bestätigt in seinen Hilfeseiten ausdrücklich, dass alle Bestellungen nach **Kanada** verzollt (`DDP`, *delivered duty paid*) geliefert werden und für dich keine Zollgebühren anfallen. Andere Tracking-Plattformen zeigen vor dem ersten Scan in Kanada mitunter `Customs Released` (was Straightships eigener Tracker nicht anzeigt) sowie `Customs Held For Examination` während einer Zollbeschau oder `Customs Rejected` – letzteres bedeutet nicht zwingend das Aus: Bei einem von Peek verfolgten Paket prüfte der Zoll die Sendung und gab sie zwei Tage später frei. Peek stuft beide Zeilen als „Beim Zoll“ ein; bleibt eine davon tagelang stehen, wende dich an den Verkäufer.

Welche Abgaben in **Kanada** anfallen, richtet sich nach Warenwert und Herkunftsland gemäß den Kurierschwellen der kanadischen Grenzbehörde Canada Border Services Agency (CBSA): Bei Kuriersendungen aus China oder anderen Ländern außerhalb der USA und Mexikos bleiben Waren bis 20 CAD frei von Zoll und Steuern, darüber fallen Zoll und Steuern an. Aus den USA oder Mexiko bleiben Sendungen bis 40 CAD abgabenfrei, von 40,01 CAD bis 150 CAD fallen nur Steuern an und über 150 CAD sowohl Zoll als auch Steuern (ausgenommen sind bestimmte Waren wie Alkohol oder Tabak).

Bei Kuriersendungen nach **Kanada** erlaubt die CBSA zugelassenen Kurierdiensten, Zoll und Steuern im Namen des Empfängers abzurechnen: Laut CBSA setzt sich der Kurierdienst vor der Zustellung mit dir in Verbindung, um die Zahlung zu regeln, und hält das Paket bis zur Begleichung zurück; eigene Verzollungsgebühren des Kuriers sind von den staatlichen Abgaben getrennt und liegen außerhalb des Einflusses der CBSA. Zudem fragt die CBSA niemals per Telefon, SMS oder E-Mail nach deiner Sozialversicherungsnummer (`SIN`) oder Kreditkartennummer – solche Nachrichten sind Betrug. Mehr dazu unter [Paket beim Zoll](guide:customs).

## Straightship-Paket kommt nicht an, ist verloren oder geht zurück

**Wer handeln kann.** Vertragskunden von Straightship sind die versendenden Shops und Plattformen; ein eigenes Erstattungsverfahren für Empfänger veröffentlicht Straightship nicht. Für eine Rückzahlung oder Ersatzlieferung wende dich an den Shop (für SHEIN siehe [SHEIN-Bestellungen verfolgen](guide:shein-orders)).

**Hängt vor Kanada.** Steht lediglich `Package information is created in the system` ohne weiteren Eintrag da, hat das Paket Straightship in Kanada noch nicht erreicht und ist womöglich noch aus Asien unterwegs. Frag beim Verkäufer nach dem Versanddatum; siehe [Paket aus China verfolgen](guide:tracking-from-china).

**Kein Update in Kanada.** Tut sich mehrere Tage nach einem Depotscan nichts oder erscheint `Shipment delayed`, nutze auf Straightships Kontaktformular den Grund „Delivery Status Enquiry“. Mehr unter [Sendungsverfolgung aktualisiert sich nicht](guide:tracking-not-updating).

**Zugestellt, aber nicht da.** Schau zuerst im Straightship-Tracker nach einem Zustellfoto (nicht immer vorhanden) und prüfe Nachbarn, Postraum des Hauses sowie geschützte Stellen an deiner Tür. Für **Kanada** ergänzt SHEIN, dass ein Paket in seltenen Fällen bis zu 48 Stunden vor der tatsächlichen Ankunft als zugestellt markiert wird; taucht es danach nicht auf, wähle im Straightship-Formular „Delivered Order Not Received“ und nutze bei einer SHEIN-Bestellung unter „My orders“ die Schaltfläche „Parcel Delivered, Not Received“, um eine Rückerstattung einzuleiten. Siehe [zugestellt, aber nicht erhalten](guide:delivered-not-received).

**Zurückgeschickt.** `Package is being returned to shipper` heißt, dass die Sendung an den Händler zurückgeht: Bitte den Verkäufer um Erstattung oder Neuversand; siehe [zurück an den Absender](guide:returned-to-sender).

## Straightship Telefonnummer und Kundenservice

Auf seiner offiziellen Website nennt Straightship:

- **Telefon in Kanada:** `+1 (800) 452-3304`, Montag bis Freitag von 08:00 bis 21:00 Uhr und Samstag von 08:00 bis 16:00 Uhr Eastern Time (`EST`); außerdem führt die Startseite `+31 800 0200244` für die Niederlande (`NL`) auf.
- **E-Mail:** `SS@straightship.com`, die Adresse aus den Benachrichtigungszeilen nach einem Zustellversuch.
- **Kontaktformular:** auf [straightship.com/new/contact.html](https://www.straightship.com/new/contact.html) unter Pflichtangabe der Sendungsnummer. Straightship bittet um 2 Werktage Geduld für eine Antwort.

Für Fragen zur Bestellung selbst, zu einer Rückgabe oder Erstattung wende dich an den Verkäufer.

## Ein Straightship-Paket mit Peek verfolgen

[Peek](/) ordnet Straightship sofort anhand einer `STRTD`- oder `STRCA`-Nummer der oben genannten Muster zu, da kein anderer Dienst im Katalog diese Form teilt. Bei einer `STSH`-Nummer überschneidet sich das Muster mit manchen Nummern von Colis Privé: Peek fragt deshalb bei Straightship an und wählt ihn aus, sobald er die Sendung führt. Erscheint Straightship nicht automatisch, tippe auf „Ändern“ und wähle ihn unter „Passt zu dieser Nummer“. Auch ein Tracking-Link von `straightship.com` (`/track-your-shipment/tracking-id/` samt Nummer) lässt sich direkt einfügen. Peek fragt bei Straightship ausschließlich diese bekannten Muster ab: Wählst du Straightship über „Ändern“ für eine anders aufgebaute Nummer, meldet Peek „Dieser Paketdienst vergibt keine Sendungsnummern wie diese.“ – nutze für abweichende Nummern Straightships eigenen Tracker.

Peek liest Straightships eigenen Tracker aus und zeigt den Status, den Verlauf mit Stadt und Provinz jedes Scans sowie die Zustellzeit. Die Uhrzeiten liest Peek wie vom Tracker angegeben als UTC und rechnet sie in deine Geräte-Zeitzone um („Uhrzeiten in deiner Zeitzone: …“ auf peektracker.com und in der iPhone-App). `Package will be delivered before 9:00pm` zählt als „In Zustellung“, die Versuchsmeldungen als „Zustellversuch“ und `Delivery is in progress` als „Unterwegs“. Ein voraussichtliches Lieferdatum zeigt Peek nicht an, und Kundenreferenz sowie Zustellfoto lässt es weg – beides siehst du direkt auf Straightships Seite. Da Straightship keinen Drittzusteller nennt, begleitet Peek die Straightship-Nummer bis zum Schluss.

Solange du das Paket geöffnet hast oder am Zustelltag mit eingeschalteten Mitteilungen, prüft Peek bis zu alle 10 Minuten – und an dem Tag, an dem Straightship das Paket als in Zustellung scannt, ab 6:00 Uhr pazifischer Zeit bis zu alle 2 Minuten (Abendzustellungen eingeschlossen), solange Straightships eigener Tracker antwortet; allein über Tracking-Dienste prüft Peek stündlich, nach einem Tag ohne Scan alle 3 Stunden und am Zustelltag bis zu alle 15 Minuten, solange das Paket geöffnet ist oder Mitteilungen aktiv sind. Sonst sowie nach zwei Tagen ohne Neuigkeiten (12 Stunden auf „Etikett erstellt“ oder „Beim Paketdienst eingegangen“) prüft Peek stündlich. Benachrichtigungen gibt es für „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“. Peek kann weder Straightship noch den Verkäufer für dich kontaktieren.

## Häufige Fragen zur Straightship-Sendungsverfolgung

### Ist Straightship seriös?

Ja. Straightship Solutions Limited ist ein Logistikunternehmen im kanadischen Burnaby (British Columbia). Seine Website führt SHEIN, Canada Post, FedEx, COSCO und [YTO](carrier:yto) als Partner auf, nennt Walmart und Costco unter den belieferten Einzelhändlern und spricht von über 85 Zustellzentren in Kanada sowie über 500.000 bearbeiteten Amazon-Paketen pro Tag.

### Was bedeutet „Package will be delivered before 9:00pm“?

Dein Paket ist heute in Kanada auf Zustelltour. Der Fahrer liefert es noch am selben Tag aus oder verbucht einen Zustellversuch; klappt die Übergabe nicht, bittet die darauffolgende Zeile um eine E-Mail an `SS@straightship.com`.

### Warum steht bei Straightship immer noch „ReadyForShipping“?

Die Zusammenfassung unter „Package Status“ bleibt häufig auf `ReadyForShipping` stehen, obwohl das Paket bereits zwischen Straightships Depots unterwegs ist. Maßgeblich ist immer die neueste Zeile in der Verlaufstabelle darunter.

### Wie lange dauert die Lieferung mit Straightship?

Für Sendungen aus Asien nennt Straightship keine Regellaufzeit; für seinen Dienst aus den USA nach Kanada gibt es 6 bis 10 Tage an. Orientiere dich am Lieferzeitraum, den dir der Shop bei der Bestellung genannt hat. Sobald `Package will be delivered before 9:00pm` erscheint, kommt der Fahrer noch am selben Tag.

### Wer stellt Straightship-Pakete in Kanada zu?

Laut eigenem Tracker Straightships eigene Fahrer von seinen Depots in Kanada aus – ein anderer Zusteller wird dort nicht genannt. Für Sendungen aus den USA nach Kanada führen die Leistungsseiten zusätzlich Canada Post und UPS auf, ergänzt um Purolator auf der englischen und FedEx auf der chinesischen Seite.

:::sources
- [Straightship: Home (auf Englisch)](https://www.straightship.com/new/) – Transporte von Asien nach Nordamerika, eigene Flotte, Suchfeld, POD-Foto, Partner, Telefon, E-Mail und EST-Servicezeiten
- [Straightship: About (auf Englisch)](https://www.straightship.com/new/about.html) – Tür-zu-Tür-Logistik von Asien nach Nordamerika
- [Straightship: Services (auf Englisch)](https://www.straightship.com/new/services.html) – Abdeckung von über 95 % der Bevölkerung und 90 % der Postleitzahlen Kanadas, USA-nach-Kanada-Service in 6 bis 10 Tagen, Verzollung binnen 24 Stunden, Zustelloptionen Canada Post, Purolator und UPS
- [Straightship: 服务 (Leistungen, auf Chinesisch)](https://www.straightship.com/new/cn/services.html) – derselbe USA-nach-Kanada-Dienst mit eigenem Netz, Canada Post, FedEx oder UPS auf der letzten Meile
- [Straightship: Resources (auf Englisch)](https://www.straightship.com/new/resources.html) – über 85 Zustellzentren in Kanada, über 500.000 Amazon-Pakete täglich, Walmart und Costco
- [Straightship: Contact (auf Englisch)](https://www.straightship.com/new/contact.html) – Kontaktgründe im Formular, Pflichtfeld Sendungsnummer und Antwortzeit von 2 Werktagen
- [Straightship: Privacy Policy (auf Englisch)](https://www.straightship.com/new/privacy.html) – Firmenname Straightship Solutions Limited und Anschriften in Burnaby (British Columbia)
- [CBSA: Importing casual goods by courier (auf Englisch)](https://www.cbsa-asfc.gc.ca/import/courier/lvs-efv/prsn-eng.html) – Abrechnung von Zoll und Steuern durch Kurierdienste in Kanada sowie Verzollungsgebühren außerhalb der Kontrolle der CBSA
- [CBSA: Protect yourself against fraud (auf Englisch)](https://www.cbsa-asfc.gc.ca/security-securite/pyaf-pvcf-eng.html) – Zahlungsabwicklung vor der Zustellung durch den Kurier und keine Abfrage von SIN oder Kartendaten durch die CBSA
- [CBSA: Low-value shipment thresholds (auf Englisch)](https://www.cbsa-asfc.gc.ca/services/cusma-aceum/lvs-efv-eng.html) – Kurierfreigrenzen in Kanada (20 CAD aus Drittländern, 40 CAD und 150 CAD aus den USA und Mexiko)
- [SHEIN Canada: Are there any additional fee for customs? (auf Englisch)](https://ca.shein.com/faq/detail?id=2307) – verzollte Lieferung (DDP) aller SHEIN-Bestellungen nach Kanada
- [SHEIN Canada: My order is marked as delivered, but I haven't received it (auf Englisch)](https://ca.shein.com/faq/detail?id=2362) – Zustellvermerk bis zu 48 Stunden vorab und Schaltfläche Parcel Delivered, Not Received
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Muster `STRTD`, `STRCA` und `STSH`, Scan-Wortlaute, UTC-Hinweis, Zusammenfassungswerte, `Customs Released` und weitergeleitete Zollprüfungen, Übergabe von JS Express sowie in Peek angezeigte Felder
:::
