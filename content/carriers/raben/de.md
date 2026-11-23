---
title: Raben Sendungsverfolgung: Nummer, Status & Lieferung
description: Raben Paletten- oder Stückgutsendung verfolgen: Sendungsnummer, Bedeutung der Statusmeldungen, Entladung, Schadensmeldung und Kontakt.
slug: raben-sendungsverfolgung
published: 2026-11-22
updated: 2026-11-22
---

Die Raben Group ist ein familiengeführter europäischer Logistikkonzern, der 1931 in den Niederlanden gegründet wurde und heute bei Posen (Poznań) in Polen sitzt. Sein Straßennetz befördert Paletten und Stückgut (Teilladungen) über Niederlassungen in 17 europäischen Ländern und beliefert auch Online-Shops – eine sperrige Bestellung kommt daher manchmal per Raben-Lkw. Verfolge sie mit der Sendungsnummer im Feld „Shipment tracking“ auf raben-group.com oder füge die Nummer oben ein.

## Raben-Sendungsnummer: Aussehen und Fundort

Raben vergibt für jede Sendung eine eigene Nummer. Sie liegt dem Unternehmen vor, das den Transport gebucht hat, also meist dem Shop oder Hersteller: schau in die Versand-E-Mail oder auf deine Bestellseite. Die polnischen Gesellschaften von Raben erklären, dass sie die Empfängerdaten vom Auftraggeber erhalten; frage also den Absender, wenn dir keine Nummer vorliegt. Mehr unter [Sendungsnummer finden](guide:find-tracking-number).

| Nummer (erfunden) | Bedeutung |
| --- | --- |
| `274829173648291` | 27 gefolgt von 13 weiteren Ziffern: das Format einer Raben-Sendungsnummer im Katalog von Peek. |
| `27 4829 1736 4829 1` | Dieselbe Nummer mit Leerzeichen. Leerzeichen und Bindestriche spielen in Peek keine Rolle. |
| `oftc.myraben.com/link/ShipmentInformation?ShipmentNumber=274829173648291` | Ein Direktlink zu einer Sendung im Raben-Tracker, wie ihn die Suche auf raben-group.com öffnet. Füge den ganzen Link in Peek ein. |

Auch [BRT](carrier:brt) in Italien und Yunda in China nutzen 15-stellige Nummern, sodass dieses Format nicht immer zu Raben gehört. Rabens eigene Suche findet Sendungen zudem über Referenzen oder Packstücketiketten, Peek verfolgt jedoch ausschließlich die eigentliche Sendungsnummer.

## Raben-Statusmeldungen erklärt

Rabens Tracker myRaben zeigt Status, jede Station mit Ort und Uhrzeit, voraussichtliches Lieferdatum, Gewicht, Packstückzahl und Volumen, einen Fortschrittsbalken, Hinweise sowie die Kontaktdaten der zuständigen Raben-Niederlassung. Die typischen Schritte in ihrer Reihenfolge:

| Raben meldet | Bedeutung für dich | Status in Peek |
| --- | --- | --- |
| `registered in the system` | Der Absender hat den Transport gebucht. Raben hat den Auftrag, aber noch nicht die Ware. | „Etikett erstellt“ |
| `arrived to the pick-up place` | Der Lkw ist zur Abholung beim Absender eingetroffen. | „Etikett erstellt“ |
| `collected` | Raben hat die Sendung beim Absender abgeholt. | „Beim Paketdienst eingegangen“ |
| `loaded` | Auf einen Lkw verladen – für das nächste Depot oder für die Zustelltour. | „Unterwegs“ |
| `unloaded` | Im nächsten Depot entladen. | „Unterwegs“ |
| `Inventory in a warehouse` | Im Lager eines Depots erfasst. | „Unterwegs“ |
| `shipment in depo` | Im Depot. | „Unterwegs“ |
| `in progress - advice required` | Unterwegs. Die Zustellung muss dem Empfänger zuerst avisiert werden. | „Unterwegs“ |
| `in progress - fix delivery` | Unterwegs zur Zustellung an einem festen Tag. | „Unterwegs“ |
| `in delivery` | Auf der Zustelltour. | „In Zustellung“ |
| `arrived to the delivery place` | Der Lkw hat die Lieferadresse erreicht. | „In Zustellung“ |
| `delivered` | Zugestellt. | „Zugestellt“ |
| `delivered D&D` | Vom Fahrer zugestellt und entladen. | „Zugestellt“ |
| `not delivered - self collection` | Nicht zugestellt: zur Selbstabholung im Depot vorgemerkt, auch wenn noch eine Tour folgen kann. Frage vorab bei der im Tracker genannten Raben-Niederlassung nach. | „Abholbereit“ |
| `not delivered - consignee not available` | Der Fahrer hat dich nicht angetroffen. | „Zustellversuch“ |

**Weitere fehlgeschlagene Zustellungen.** `not delivered - delivery refused`, `not delivered - wrong address`, `not delivered - truck with lift required` (Lkw mit Hebebühne erforderlich) und `not delivered - wrong/missing docs` nennen den Grund. Peek zeigt „Zustellversuch“.

**Verzögerungen und Stopps.** `not delivered - breakdown lorry` und `not delivered - strike` sind Störungen unterwegs; `not delivered - at sender disposal` heißt, Raben hält die Sendung für Weisungen des Absenders zurück; `cancelled` bedeutet Stornierung des Transports. Peek zeigt „Problem gemeldet“.

**Zugestellt mit Vermerk.** `delivered - damages`, `delivered - discrepancies`, `delivered - partly returned` und `delivered with delay` bedeuten alle Übergabe mit Hinweis auf die Abweichung. Peek zeigt „Zugestellt“.

**Planänderungen.** `new delivery date agreed with consignee` und `shipment parameters updated` ändern Datum oder Daten; die Ware hat sich nicht bewegt.

**Zoll.** Zolldurchgänge erzeugen Schritte wie `customs: clearance in progress` und `customs clearance completed`; `customs: lack of invoices / incomplete invoices` weist auf fehlende Rechnungen hin – wende dich an den Shop. Siehe [Paket beim Zoll](guide:customs) und [Sendungsstatus erklärt](guide:tracking-statuses).

## So liefert Raben: Paletten, Entladung und Laufzeiten

Die Paletten-FAQ von Raben Polen erklären den Ablauf. Zusatzleistungen bucht der Kunde von Raben, also das Unternehmen, das den Transport beauftragt hat (meist der Shop):

- **Entladung.** Das Entladen ist Aufgabe des Empfängers: Raben haftet nicht dafür und muss weder Gabelstapler noch Rampe mitbringen. Der Auftraggeber kann manuelles Entladen durch den Fahrer (HUN) oder Entladung am Lieferort (UNLO) zubuchen. Frage bei schweren Gütern beim Shop nach, was gebucht wurde.
- **Laufzeiten.** Innerhalb Polens braucht eine Palette standardmäßig einen Werktag – oder zwei, je nach gebuchtem Service; ins Ausland hängt es vom Zielland ab. Der Auftraggeber kann auch einen Fixtermin (FIX), Lieferung bis 8:00, 10:00, 12:00 oder 16:00 Uhr (ND 08 bis ND 16) oder Tag und Uhrzeit nach Absprache (WXACT) buchen.
- **Bei Abwesenheit.** Scheitert eine Zustellung aus Gründen beim Empfänger, kann Raben dem Auftraggeber Kosten berechnen.
- **Zustellfotos.** Mit Picture Confirming Delivery (PCD), einer kostenlosen Option für Auftraggeber, musst du nicht anwesend sein. Der Fahrer stellt die Ware am vereinbarten Ort ab und fotografiert Etikett, Sendung und beides zusammen; die Fotos ersetzen den Lieferschein.

## Raben-Sendung verspätet, beschädigt oder nicht geliefert

**Kein Fortschritt.** Steht der Verlauf tagelang still, frage beim Shop oder bei der im Tracker genannten Raben-Niederlassung nach. Siehe [Sendungsverfolgung aktualisiert sich nicht](guide:tracking-not-updating).

**Verspätet.** In der EU muss ein Händler laut dem EU-Portal Your Europe innerhalb von 30 Tagen liefern, sofern nichts anderes vereinbart wurde. Danach kannst du eine Nachfrist setzen und anschließend stornieren sowie dein Geld zurückfordern.

**Beschädigt oder verloren.** Der Händler trägt das Risiko vom Versand bis zur Übergabe; laut Your Europe wendest du dich zuerst an den Händler. Siehe [beschädigtes Paket](guide:damaged-parcel) und [verlorenes Paket](guide:lost-parcel). Rabens eigene Regeln:

- **Polen.** Raben Logistics Polska nimmt schriftliche Reklamationen von Absender oder Empfänger entgegen: über myClaim, ein Online-Formular ohne myRaben-Konto, oder per Post, Fax oder E-Mail. Rüge sichtbare Schäden bei Lieferung und melde verdeckte Schäden innerhalb von 7 Tagen nach Zustellung (im internationalen Verkehr zählen Sonn- und Feiertage nicht mit). Raben antwortet binnen 30 Tagen nach Eingang aller Unterlagen. Eine Inlandssendung gilt ab dem 31. Tag nach dem geplanten Liefertag als verloren.
- **Deutschland.** Raben Deutschland bittet, Verpackung und Vollständigkeit bei Annahme zu prüfen und sichtbare Schäden detailliert auf dem Lieferschein festzuhalten und vom Fahrer gegenzeichnen zu lassen. Melde Schäden schriftlich bei der zuständigen Raben-Niederlassung; verdeckte Schäden unverzüglich nach Entdeckung, spätestens 7 Tage nach Zustellung.

## Raben Kontakt und Kundenservice

Kontakte auf den Websites von Raben:

- **Ansprechpartner deiner Sendung.** Raben verweist auf die feste Kontaktperson auf der Statusseite der Sendung, die Peek nicht anzeigt.
- **Zeiten.** Der Kundenservice von Raben Polen arbeitet Montag bis Freitag von 8:00 bis 16:00 Uhr (polnische Zeit), strebt eine Antwort binnen 3 Stunden an und bedient standardmäßig auf Polnisch.
- **Niederlassungen.** Die [Kontaktseite von Raben](https://www.raben-group.com/contact) findet Depots per Postleitzahl und listet Standorte nach Land; jede Länderseite wie polska.raben-group.com führt eigene Kontakte.

## Raben-Sendung mit Peek verfolgen

Das 15-stellige Format teilt sich Raben mit [BRT](carrier:brt) und weiteren Paketdiensten, daher legt [Peek](/) Raben nicht allein anhand der Nummer fest. Füge sie trotzdem ein: Peek unterstützt über 4.500 Paketdienste. Beim Hinzufügen fragt Peek Raben und BRT ab, als letzte Möglichkeit Ozon, wenn keiner der beiden die Nummer kennt („Tracking-Dienste werden geprüft…“) und zeigt „Raben hat dieses Paket.“, sobald Rabens Tracker die Nummer kennt; erkennen sie mehrere, wählst du aus. Kennt noch keiner sie, steht dort „Noch kein Sendungsverlauf“, während Peek weiter prüft – oder „Warten auf Raben“, sobald du Raben gewählt hast. Um den Dienst selbst einzustellen, tippe auf „Ändern“ und wähle „Raben“ unter „Passt zu dieser Nummer“ oder suche danach (auch „Raben Group“ findet ihn). Bei bereits hinzugefügten Sendungen nutzt du „Paketdienst ändern“.

Ein Tracking-Link von myraben.com oder oftc.myraben.com (…/link/ShipmentInformation?ShipmentNumber=…) ordnet die Sendung sofort Raben zu. Hat deine Raben-Nummer ein anderes Format, wähle Raben manuell: Peek fragt Rabens Tracker für jede Kombination aus 4 bis 40 Buchstaben und Ziffern mit mindestens einer Ziffer ab.

Peek liest Rabens Tracker auf Englisch: Status, jeden Schritt in Rabens Wortlaut mit Ort und Land, den geplanten Liefertag (Datum ohne Uhrzeit) oder – sobald die Tour des Fahrers eines festlegt – das Lieferzeitfenster mit Uhrzeiten, Gewicht und Zustellzeit. Uhrzeiten erscheinen in deiner Zeitzone („Uhrzeiten in deiner Zeitzone:“). Schritte, die nur Datum oder Parameter ändern, behalten den bisherigen Status bei. Packstückzahl, Volumen, Fortschrittsbalken, Bemerkungen und Niederlassungskontakt lässt Peek weg. Der öffentliche Tracker nennt weder Absender noch Empfänger, Adresse, Abholpunkt, Service oder Abliefernachweis.

Peek fragt Raben direkt bis zu alle 10 Minuten ab – und bis zu alle 2 Minuten an dem Tag, an dem Raben die Sendung in Zustellung scannt, ab 6:00 Uhr in der Zeit des Landes ihres letzten Scans oder ab einer halben Stunde vor Beginn eines angekündigten Zeitfensters, solange die Sendung in Peek geöffnet ist oder am Liefertag mit aktiven Mitteilungen. Nach zwei Tagen ohne Neuigkeiten (nach 12 Stunden, solange noch „Etikett erstellt“ oder „Beim Paketdienst eingegangen“ angezeigt wird) prüft Peek stündlich; nach sieben Tagen ohne Neuigkeiten bei „Etikett erstellt“ alle 6 Stunden. Für Mitteilungen wählst du „Alle Updates“, „Nur wichtige“ oder „Nur am Liefertag“. Nach vier Tagen ohne Scan bei einer Sendung auf „Etikett erstellt“, „Beim Paketdienst eingegangen“ oder „Unterwegs“, zeigt Peek „Keine Aktualisierung seit …“ und erklärt: „Auf langen Flügen, beim Zoll oder übers Wochenende wird es oft still um ein Paket. Peek fragt weiter bei Raben nach.“. Peek kann weder Raben noch den Shop für dich kontaktieren, keine Entladung buchen und keine Reklamation einreichen.

## Häufige Fragen zur Raben-Sendungsverfolgung

### Was ist die Raben Group?

Ein familiengeführter europäischer Logistikkonzern, 1931 in Winterswijk in den Niederlanden gegründet und mit Sitz in Robakowo bei Posen in Polen. Er befördert Paletten und Teilladungen auf der Straße durch 17 Länder, meist im Auftrag des Absenders.

### Wie verfolge ich eine Raben-Sendung?

Gib die Sendungsnummer im Feld „Shipment tracking“ auf raben-group.com ein und klicke auf „Search“: Rabens Tracker öffnet sich in einem neuen Tab. Oder füge die Nummer beziehungsweise den Link oben ein.

### Entlädt der Raben-Fahrer meine Bestellung?

Nach den Regeln von Raben Polen nur, wenn der Auftraggeber des Transports dies gebucht hat: das Entladen ist Sache des Empfängers, und die Entladung durch den Fahrer ist eine kostenpflichtige Zusatzleistung (HUN oder UNLO). Ein Scan `delivered D&D` bedeutet, dass der Fahrer geliefert und entladen hat.

### Wie lange dauert eine Lieferung mit Raben?

Innerhalb Polens ein bis zwei Werktage je nach gebuchtem Service; ins Ausland hängt die Laufzeit vom Zielland ab. Rabens Tracker zeigt den erwarteten Liefertag an.

### An wen wende ich mich bei einer beschädigten Raben-Lieferung?

Zuerst an den Shop: in der EU haftet der Verkäufer bis zur Übergabe an dich. In Polen kannst du zudem selbst schriftlich bei Raben Logistics Polska reklamieren; verdeckte Schäden müssen binnen 7 Tagen nach Zustellung gemeldet werden.

:::sources
- [Raben Group: home page](https://www.raben-group.com/) – Tracking-Feld, „Search“, neuer Tab, 17 Länder
- [Raben Group: Customer Zone](https://www.raben-group.com/customer-zone) – eigene Nummer je Sendung, erwartetes Datum, Kontakt auf der Statusseite
- [Raben Group: Contact](https://www.raben-group.com/contact) – Depotsuche, Niederlassungen nach Land
- [Raben Group: Road transport and international transport](https://www.raben-group.com/services/road-network) – Stückgut / Groupage
- [Raben Group: E-commerce](https://www.raben-group.com/e-commerce) – Zustellung für Online-Shops
- [Raben Group: Picture Confirming Delivery](https://www.raben-group.com/it-solutions/pcd) – kostenloser Service, Fotos, Lieferschein
- [Raben Group: Raben Group in Europe](https://www.raben-group.com/about-us/raben-group-in-europe) – 1931, Winterswijk, Familienunternehmen, 17 Länder
- [Raben Group: Imprint](https://www.raben-group.com/imprint) – Robakowo bei Posen
- [Raben Poland: Pytania i odpowiedzi - Transport paletowy](https://polska.raben-group.com/strefa-klienta/pytania-i-odpowiedzi-transport-paletowy) – Entladung, HUN, UNLO, Laufzeiten, FIX, ND, WXACT, Fehlzustellungsgebühr, PCD, Servicezeiten, Polnisch
- [Raben Poland: Strefa uczestnika przewozu](https://polska.raben-group.com/strefa-uczestnika-przewozu) – Empfängerdaten vom Auftraggeber
- [Raben Logistics Polska: Claims](https://polska.raben-group.com/en/customer-zone/raben-logistics-polska/claims-raben-logistics-polska) – Anspruchsberechtigte, Verfahren, offene und verdeckte Schäden, Verlust, Antwort in 30 Tagen
- [Raben Germany: Transportschäden – Wir helfen Ihnen](https://deutschland.raben-group.com/kundenbereich/reklamationen) – Prüfung bei Lieferung, schriftliche Meldung, 7 Tage
- [Your Europe: Your rights when shopping in the EU](https://europa.eu/youreurope/citizens/consumers/shopping/shopping-consumer-rights/index_en.htm) – 30 Tage, Händlerhaftung, zuerst an den Händler wenden
- [Universal Parcel Scraper: Raben](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/raben) – Nummernformat, Links, Referenzen, angezeigte und von Peek gelesene Daten, Schritte
:::
