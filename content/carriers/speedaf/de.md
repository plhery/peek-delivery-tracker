---
title: Speedaf Express Sendungsverfolgung: Nummer und Status
description: Speedaf-Express-Sendung aus China in die Schweiz, nach Afrika oder Nahost verfolgen: Nummernformate, Statusmeldungen, Zollgebühren und Kontakte.
slug: speedaf-sendungsverfolgung
published: 2026-11-18
updated: 2026-11-20
---

Speedaf Express (速达非) ist ein 2019 gegründetes Zustellnetzwerk, das Pakete aus eigenen Lagern in China per Luftfracht nach Afrika, in den Nahen Osten, nach Pakistan und in einzelne europäische Länder fliegt und dort mit eigenen Fahrern zustellt. Im deutschsprachigen Raum steht nur die Schweiz auf der Länderliste von Speedaf – Deutschland, Österreich und Liechtenstein stehen nicht darauf. Um dein Paket zu verfolgen, gib die Sendungsnummer unter „Track“ auf [speedaf.com](https://www.speedaf.com/cn-en/send-parcel), in der Kunden-App von Speedaf oder oben ins Suchfeld ein. Die Nummer steht in den Versanddetails deiner Bestellung.

## Speedaf-Sendungsnummer: Aufbau und Beispiele

Eine Speedaf-Sendungsnummer beginnt mit dem zweistelligen Ländercode des Ziellands – etwa `NG` für Nigeria, `KE` für Kenia oder `PK` für Pakistan (für die Schweiz hat Peek kein eigenes Nummernformat hinterlegt). Zwei Aufbauten kommen vor, hier mit frei erfundenen Ziffern:

| Beispielnummer | Bedeutung |
| --- | --- |
| `NG482910573R` | Ländercode, 9 Ziffern und ein Buchstabe am Ende – meist `R`, teils `E`. |
| `KE482910573649` | Ländercode und 12 Ziffern. |
| `PK482910573649T` | Dasselbe 12-Ziffern-Format mit einem abschließenden `T`. |
| `LP48291057364918` | Keine Speedaf-Nummer, sondern eine [Cainiao](carrier:cainiao)-Referenz aus einer AliExpress-Bestellung. |

Die Sendungsverfolgung auf speedaf.com nimmt bis zu 20 Nummern gleichzeitig an („Up to 20 numbers“), jeweils eine pro Zeile. Gib Buchstaben dort groß ein: Kleinbuchstaben meldet das Portal als unbekannt. Fehlt dir die Nummer noch, helfen [Sendungsnummer finden](guide:find-tracking-number) und [AliExpress-Bestellungen verfolgen](guide:aliexpress-orders).

## Was die Statusmeldungen von Speedaf bedeuten

Speedaf schreibt seine Scans auf Englisch, andernfalls auf Chinesisch und teils zusätzlich in der Landessprache des Ziellands. Standorte stehen in eckigen Klammern wie 【hier】 mitten in der Zeile. In der Reihenfolge, in der ein Paket aus China sie durchläuft:

| Speedaf meldet | Peek zeigt | Was das für dein Paket bedeutet |
| --- | --- | --- |
| `processing, order has been inbounded` | Beim Paketdienst eingegangen | Das davor genannte Speedaf-Lager in China hat das Paket eingebucht. |
| `processing assembling operation`, `processing, order has been outbounded` | Unterwegs | In einen Versandsack gepackt und aus dem Lager ausgebucht. |
| `clearance in progress` | Beim Zoll | Ausfuhranmeldung in China. |
| `Delivery to the airport`, `Cargo flight departured`, `Cargo flight arrived` | Unterwegs | Zum Flughafen gebracht, abgeflogen und im Zielland gelandet. |
| `Clearance handover`, `Customs clearance in progress` | Beim Zoll | Am Flughafen übernommen und in der Einfuhrverzollung des Ziellands. |
| `pending Tax` | Beim Zoll | Abgaben sind offen, bevor der Zoll das Paket freigibt (siehe unten). |
| `Customs clearance tax paid` | Beim Zoll | Abgaben sind bezahlt, die Verzollung läuft weiter. |
| `Customs Clearance Completed` | Unterwegs | Der Zoll hat die Sendung freigegeben. |
| `Arrived at DC Warehouse`, `Arrival at Speedaf DC` | Unterwegs | Im Zentrallager oder Verteilzentrum von Speedaf im Zielland eingetroffen. |
| `Received in` | Unterwegs | Am ersten Speedaf-Standort im Zielland übernommen – keine neue Erstannahme. |
| `Loaded at`, `Departed for`, `Arrive at Distribution`, `Arrived at Site` | Unterwegs | Weitertransport zwischen Verteilzentren und der Zustellbasis vor Ort. |
| `scanned for delivery` | In Zustellung | Ein Zusteller hat das Paket für seine Tour geladen. |
| `Issue Parcel Reason【Rescheduled delivery】` | Zustellversuch | Die Zustellung wurde auf den in Klammern dahinter genannten Tag verschoben. |
| `Issue Parcel Reason【Receiver Rejected Parcel】` | Problem gemeldet | Die Annahme wurde verweigert. |
| `Issue Parcel Reason【Delayed payment of tax by customer】` | Beim Zoll | Der Fahrer hat das Paket zurückgebracht, weil die Abgaben noch nicht bezahlt waren. |
| `Please come to collect with the Pick-Up Code` | Abholbereit | Liegt am davor genannten Speedaf-Standort zur Abholung mit Abholcode bereit. |
| `Parcel delivered 【Collected】`, `【Parcel delivered】` | Zugestellt | Zugestellt oder abgeholt; die Zeile kann den Abholstandort nennen. |
| `Transported to the warehouse，【Return Overdue】` | Problem gemeldet | Geht zurück ins Lager von Speedaf. |
| `Warehouse received` | Zurück an Absender | Die Rücksendung ist wieder im Speedaf-Lager eingetroffen. |

Ein bloßes `In Transit` kann einen Zwischenstopp verbergen: Hinter dem internen Code steckt ein Paket, das für eine Bestandszählung zurückgehalten wird. `Products not for personal use` und `Clearance Exception` weisen auf eine Rückfrage oder Beanstandung beim Zoll hin; `Long Time Tax No Paid,Email To Sender` bedeutet, dass die Abgaben schon länger offen sind und Speedaf den Absender anschreibt. Auf Chinesisch lauten die wichtigsten Schritte `进行处理，订单已操作入库` („Beim Paketdienst eingegangen“), `清关中` („Beim Zoll“), `已做派件扫描` („In Zustellung“) und `包裹【已签收】` („Zugestellt“).

## Zustellung bei Speedaf: Haustür, Abholstation und Versuche

Speedaf setzt auf der letzten Meile eigene Kuriere ein, mit Haustürzustellung und Nachnahme (COD). Unter „Look for Service Site“ zeigt Speedaf seine Standorte auf einer Karte.

Die Zustellregeln stehen in den jeweiligen Länderbedingungen, die für den Absender verfasst sind und sich je nach Land unterscheiden; die Texte für Nigeria und die Vereinigten Arabischen Emirate regeln dortige Inlandssendungen:

- **Vereinigte Arabische Emirate:** Jede Sendung hat 3 kostenlose Zustellversuche („Every shipment will have 3 free delivery attempts“); kann sie nicht zugestellt werden, geht sie an den Absender zurück.
- **Kenia, Uganda und Pakistan:** Das Paket geht an den Absender zurück, wenn auch der dritte Zustellversuch scheitert („cannot be delivered successfully upon a third attempt“) oder wenn du die Annahme bzw. fällige Gebühren verweigerst. Alle drei Bedingungen halten fest, dass Speedaf an die angegebene Adresse liefert, aber nicht zwingend persönlich an den benannten Empfänger („but not necessarily to the named receiver in person“).
- **Nigeria und Vereinigte Arabische Emirate:** Ein Paket gilt als unzustellbar, wenn du die Sendung oder den Nachnahmebetrag ablehnst, die Adresse falsch ist oder außerhalb des Zustellgebiets liegt oder dein gewünschter neuer Zustelltag die Lagerfrist von Speedaf überschreitet („exceeds Speedaf holding time“). Wie lang diese Frist ist, nennt keiner der beiden Texte.

Eine allgemeine Abholfrist für seine Stationen veröffentlicht Speedaf nicht. Hast du den Fahrer verpasst, lies [Paketbote verpasst: was tun?](guide:missed-delivery).

## Zoll und Abgaben bei Speedaf: pending Tax

Sind Einfuhrabgaben offen, bleibt der Verlauf bei `pending Tax` stehen. Bei Sendungen nach **Südafrika** blendet die Sendungsverfolgung von Speedaf neben der Frachtbriefnummer einen Link „I want to pay taxes“ ein und zeigt nach der Zahlung „Taxes paid“.

Fordert dich eine SMS oder E-Mail zur Zahlung auf, gleiche die Meldung zuerst direkt auf speedaf.com ab: Mehr dazu unter [Paket-Phishing per SMS erkennen](guide:delivery-scam-texts) und [Paket hängt beim Zoll](guide:customs).

## Speedaf-Paket verspätet, verloren oder als zugestellt gemeldet

**Verlauf steht still.** Bewegt sich die Sendungsverfolgung seit Tagen nicht, helfen [Pakete aus China verfolgen](guide:tracking-from-china) und [Sendungsverfolgung aktualisiert sich nicht](guide:tracking-not-updating).

**Wer reklamieren kann.** Laut den Bedingungen für **Kenia, Uganda und Pakistan** besteht ein Anspruch ausschließlich zwischen Versender und Speedaf („The claim is only between the shipper and SPEEDAF“); in **Nigeria und den Vereinigten Arabischen Emiraten** werden Ansprüche nur mit dem Absender reguliert („Claims will only be settled with the Shipper.“). Bei einer Online-Bestellung ist der Shop der Absender – wende dich für Ersatz oder Erstattung direkt an den Händler oder Marktplatz.

**Melde dich rasch beim Shop.** Die Länderbedingungen von Speedaf setzen kurze Fristen:

- **Beschädigung:** schriftlich binnen 7 Kalendertagen nach Zustellung in **Kenia und Uganda**, binnen 72 Stunden in **Nigeria und den Vereinigten Arabischen Emiraten** sowie binnen 48 Stunden in **Saudi-Arabien**.
- **Zugestellt, aber nicht erhalten:** Einspruch gegen den Zustellnachweis binnen 5 Kalendertagen in **Nigeria und den Vereinigten Arabischen Emiraten** bzw. binnen 5 Werktagen in **Pakistan**.
- **Verlust:** in **Nigeria und den Vereinigten Arabischen Emiraten** eine Nachforschungsbeschwerde binnen 14 Kalendertagen nach Abholung. Bleibt ein Paket dort länger als 24 Stunden ohne neuen Scan, sucht Speedaf 7 Werktage lang danach und stuft es anschließend als verloren ein, falls es unauffindbar bleibt.

Die Sendungsverfolgung von Speedaf nennt im Zustellscan, wer quittiert hat – prüfe diesen Namen zuerst. Weiterführende Schritte stehen unter [Zugestellt, aber nicht erhalten](guide:delivered-not-received) und [Paket verloren](guide:lost-parcel).

## Speedaf kontaktieren: Telefon und Kundendienst

Jede Länderseite von Speedaf nennt ihre Kontaktdaten im Seitenfuß oder über die Länderauswahl (für die Schweiz, Irland und Sambia dort das Land und anschließend „English“ wählen; für die Schweiz gibt es Seiten auf Englisch, Chinesisch und Französisch, nicht auf Deutsch). Alle Zeiten gelten in der jeweiligen Ortszeit:

| Land | Telefon | E-Mail | Zeiten |
| --- | --- | --- | --- |
| Schweiz | +41 798559737, WhatsApp +41 786030610 | ch.info@speedaf.com | Mo–Fr 8:00–18:30 |
| Nigeria | +234 800 070 7070, +234 708 063 6400, +234 9073064940 (auch WhatsApp) | ng.info@speedaf.com, nigeria.info@speedaf.com | Mo–Fr 8:00–17:00, Sa 8:00–14:00 |
| Ghana | +233 30 824 9100 | gh.info@speedaf.com | Mo–Sa 8:30–17:30 |
| Kenia | +254 741 000 666, +254 741 000 888, WhatsApp +254 741000666 | kenya.info@speedaf.com | Mo–Fr 8:00–18:00, Sa 8:00–14:00 |
| Uganda | +256 709 198 795 | ug.info@speedaf.com | Mo–Fr 9:00–17:00, Sa 9:00–14:00 |
| Sambia | +260 971269390 | zm.info@speedaf.com | Mo–Fr 7:45–17:15, Sa 8:00–13:00 |
| Südafrika | +27 108247184, +27 726393849, WhatsApp +27 664562613 | cs.southafrica@speedaf.com | nicht angegeben |
| Pakistan | +92 311 118 7654, WhatsApp +92 3450010817 | pk.info@speedaf.com | Mo–So 9:00–18:00 |
| Vereinigte Arabische Emirate | +971 800 178 | ae.info@speedaf.com | Mo–Sa 9:00–18:00 |
| Saudi-Arabien | keine Telefonnummer | sa.info@speedaf.com | nicht angegeben |
| Irland | +35312633068, WhatsApp +353 834281088 | info_customerserviceie@speedaf.com | Mo–Fr 8:30–17:30 |
| Ägypten | +20 15818, +20 1050383370 | eg.info@speedaf.com | Sa–Do 9:00–18:00 |
| Marokko | +212 522 100 888 | ma.info@speedaf.com | Mo–Fr 9:00–18:00 |
| China | +86 400 888 0975 | info@speedaf.com | Mo–Fr 9:30–18:30 |

Das Help Center von Speedaf führt außerdem Hotlines für die übrigen Länder seiner Liste auf: Kasachstan `+7 778 371 9358`, Mauritius `4687316`, Malediven `+960 7678788`, Serbien `+41 798559737` (dieselbe Nummer wie für die Schweiz), Montenegro `+382 60021040` und Albanien `+355 45620111`. Jede Länderseite bietet zusätzlich ein Kontaktformular unter „Customer Support“.

## Speedaf-Paket mit Peek verfolgen

[Peek](/) erkennt eine Speedaf-Sendungsnummer direkt am Format, wenn sie mit `NG`, `GH`, `KE`, `UG`, `RW`, `ZM`, `ZA`, `EG`, `MA`, `SA`, `AE`, `PK`, `KZ`, `MU`, `RE`, `MV` oder `TR` beginnt, gefolgt von 9 Ziffern und `R` oder `E`. Stehen nach einem dieser Ländercodes oder nach `IE` (Irland) stattdessen 12 Ziffern (optional mit `T` am Ende), reicht die Form allein nicht aus: Peek fragt bei Speedaf nach und zeigt „Tracking-Dienste werden geprüft…“, danach „Speedaf hat dieses Paket.“, sobald Speedaf die Nummer kennt. Für die Schweiz, Albanien, Montenegro und Serbien kennt Peek kein eigenes Nummernformat – wähle für diese Länder sowie für jede abweichende Nummer über „Ändern“ manuell „Speedaf“ aus. Einen Tracking-Link von speedaf.com mit `tradeNo=` ordnet Peek unabhängig vom Nummernformat Speedaf zu. Kleinbuchstaben wandelt Peek automatisch in Großbuchstaben um.

Peek liest direkt die Sendungsverfolgung von Speedaf aus: Status, Verlauf, Zielland und Zustellzeitpunkt. Jeder Scan trägt die Ortszeit der jeweiligen Station (UTC+8 in den chinesischen Lagern, danach die Zeitzone des Ziellands) und wird in die Zeitzone deines Geräts umgerechnet; auf peektracker.com und in der iPhone-App nennt eine Zeile unter dem Verlauf diese Zone („Uhrzeiten in deiner Zeitzone: …“). Da Speedaf seine Standorte nur im Scantext nennt, gibt es keine Kartenansicht, und Speedaf liefert kein voraussichtliches Zustelldatum. Kennt Speedaf die Nummer bereits, zeigt aber noch keinen Scan, prüft Peek weiter und meldet „Warten auf Speedaf“ – oder „Warten auf den ersten Scan von Speedaf“ bei einer 12-stelligen Nummer, wenn du Speedaf nicht manuell gewählt hast. Ist ein unter Speedaf gespeichertes Paket zwei Tage später weiterhin ohne Scan und hat auch keine andere Quelle dafür geantwortet, zeigt Peek „Auch nach zwei Tagen kennt der Paketdienst diese Nummer nicht. Prüfe sie, wähle einen anderen Paketdienst oder lösche das Paket.“ und fragt danach einmal täglich ab, nach einer Woche seit dem Hinzufügen einmal wöchentlich.

Die Scanzeilen erscheinen im Originalwortlaut auf Englisch (sonst auf Chinesisch), bereinigt um die Namen von Zusteller und Scan-Mitarbeiter sowie um Telefonnummern. Wer für das Paket unterschrieben hat, bleibt im Zustellscan für die Person sichtbar, die das Paket hinzugefügt hat; auf einem geteilten Link blendet Peek diesen Namen aus und zeigt beim Zustellscan nur `Delivered`. Ebenfalls weggelassen werden das Zustellfoto, der Zahlungslink für Abgaben und die Paketanzahl; Peek verfolgt nur die eingegebene Frachtbriefnummer, nicht weitere Packstücke desselben Frachtbriefs.

Solange du das Paket geöffnet hältst oder am Zustelltag Benachrichtigungen eingeschaltet hast, prüft Peek bis zu alle 10 Minuten – und am Tag, an dem das Paket auf „In Zustellung“ springt, ab 6:00 Uhr Ortszeit bis zu alle 2 Minuten, solange der eigene Tracker von Speedaf antwortet (über Tracking-Dienste stündlich, nach einem Tag ohne neuen Scan alle 3 Stunden und am Zustelltag bei geöffnetem Paket oder aktiven Benachrichtigungen bis zu alle 15 Minuten). Außerhalb dieser Bedingungen sowie nach zwei Tagen ohne Neuigkeiten – oder nach 12 Stunden auf „Etikett erstellt“ oder „Beim Paketdienst eingegangen“ – fragt Peek stündlich ab. Als Benachrichtigungen stehen „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“ zur Wahl. Zollabgaben bezahlen, eine Zustellung umbuchen oder Speedaf bzw. den Shop kontaktieren kann Peek nicht für dich.

## Häufige Fragen zur Speedaf-Sendungsverfolgung

### Ist Speedaf Express seriös?

Ja. Speedaf wurde 2019 gegründet, mit ZTO unter den ursprünglichen Anteilseignern, und betreibt eine eigene Sendungsverfolgung unter speedaf.com sowie eigene Zusteller auf der letzten Meile in den von Speedaf sogenannten Schwellenmärkten.

### In welche Länder liefert Speedaf?

Neben China nennt die Länderliste von Speedaf Ägypten, Südafrika, Marokko, die Vereinigten Arabischen Emirate, Ghana, Nigeria, Kenia, Uganda, Saudi-Arabien, Pakistan, Kasachstan, Mauritius, die Malediven, die Schweiz, Montenegro, Albanien, Irland, Serbien und Sambia. Deutschland und Österreich gehören nicht dazu; Peek erkennt außerdem Nummern mit den Präfixen `RE` (Réunion), `RW` (Ruanda) und `TR` (Türkei).

### Wie lange dauert der Versand mit Speedaf?

Auf der eParcel-Produktseite nennt Speedaf für Pakete aus China nach Nigeria, Ghana, Kenia, Uganda, Marokko, Ägypten, Saudi-Arabien, in die Vereinigten Arabischen Emirate und nach Pakistan eine unverbindliche Referenzlaufzeit („Full Reference Transit Time“) von 5 bis 10 Werktagen. Laut Help Center hängt die Dauer vom Zielland ab, und die Bedingungen für Kenia, Uganda und Pakistan stellen klar, dass Laufzeiten nicht garantiert sind.

### Was bedeutet pending Tax bei Speedaf?

Vor der Freigabe durch den Zoll im Zielland sind Einfuhrabgaben oder Steuern zu zahlen. Sobald die Zahlung verbucht ist, folgen im Verlauf `Customs clearance tax paid` und anschließend `Customs Clearance Completed`.

:::sources
- [Speedaf: Enterprise Introduction](https://www.speedaf.com/ng-en/business/about-us) – Gründung 2019, ZTO als Anteilseigner
- [Speedaf: Select your location and language](https://www.speedaf.com/cn-en/user-opt/locale-select) – Länderliste sowie Kontakte für die Schweiz, Irland, Sambia und China
- [Speedaf: Speedaf eParcel](https://www.speedaf.com/ke-en/product/intl-straight-small) – Pakete aus China, Referenzlaufzeit 5 bis 10 Werktage
- [Speedaf: Warehouse & Fulfillment](https://www.speedaf.com/ke-en/product/warehouse-promise) – eigene Zusteller auf der letzten Meile, Nachnahme (COD)
- [Speedaf: Last-mile delivery](https://www.speedaf.com/ke-en/product/warehouse-delivery) – Haustürzustellung und Nachnahme
- [Speedaf: Help Center](https://www.speedaf.com/ng-en/help) – Laufzeit nach Zielland, Tracking auf Website und App, Länder-Hotlines
- [Speedaf: Track](https://www.speedaf.com/ng-en/send-parcel) – bis zu 20 Nummern, Steuerzahlungslink für Südafrika
- [Speedaf: Look for Service Site](https://www.speedaf.com/ke-en/location-map) – Standortkarte
- [Speedaf: Customer Support](https://www.speedaf.com/ng-en/service/support) – Kontaktformular
- [Speedaf Nigeria: Home](https://www.speedaf.com/ng-en) – Telefonnummern, E-Mails, Zeiten und WhatsApp
- [Speedaf Ghana: Home](https://www.speedaf.com/gh-en) – Telefon, E-Mail und Zeiten
- [Speedaf Kenya: Home](https://www.speedaf.com/ke-en) – Telefonnummern, E-Mail, Zeiten und WhatsApp
- [Speedaf Uganda: Home](https://www.speedaf.com/ug-en) – Telefon, E-Mail und Zeiten
- [Speedaf South Africa: Home](https://www.speedaf.com/za-en) – Telefonnummern, E-Mail und WhatsApp
- [Speedaf Pakistan: Home](https://www.speedaf.com/pk-en) – Telefon, E-Mail, Zeiten und WhatsApp
- [Speedaf UAE: Home](https://www.speedaf.com/ae-en) – Telefon, E-Mail und Zeiten
- [Speedaf Saudi Arabia: Home](https://www.speedaf.com/sa-en) – E-Mail-Adresse
- [Speedaf Egypt: Home](https://www.speedaf.com/eg-en) – Telefonnummern, E-Mail und Zeiten
- [Speedaf Maroc: Accueil](https://www.speedaf.com/ma-fr) – Telefon, E-Mail und Zeiten
- [Speedaf Kenya: Terms and Conditions](https://www.speedaf.com/ke-en/service-rule) – Zustellversuche, Annahmeverweigerung, Empfänger, Reklamationen und Fristen
- [Speedaf Uganda: Terms and Conditions](https://www.speedaf.com/ug-en/service-rule) – entsprechende Bedingungen für Uganda
- [Speedaf Pakistan: Terms and Conditions](https://www.speedaf.com/pk-en/service-rule) – Zustellversuche, Empfänger, Reklamationen und Einspruch gegen Zustellnachweis
- [Speedaf UAE: Terms & Conditions](https://www.speedaf.com/ae-en/service-rule) – Inlandszustellung: Versuche, unzustellbare Pakete, Reklamationsfristen
- [Speedaf Nigeria: Service Agreement](https://www.speedaf.com/ng-en/service-rule) – Inlandszustellung: unzustellbare Pakete, Reklamationsfristen und Suche bei Verlust
- [Speedaf Saudi Arabia: Express Shipping Terms](https://www.speedaf.com/sa-en/service-rule) – Schadensmeldung binnen 48 Stunden
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Nummernformate, Statuscodes, Scanbereinigung und Anzeige in Peek
:::
