---
title: 5Post Sendungsverfolgung: Nummern, Abholung & Status
description: Verfolge 5Post-Pakete (FivePost / X5) in Russland: Avito-P-Nummern, Shop-Bestellcodes, russische Status, Abholung bei Pyaterochka und Hilfe.
slug: 5post-sendungsverfolgung
published: 2026-11-24
updated: 2026-11-24
---

5Post (*5Пост* / *FivePost*, betrieben von der X5 Group) ist ein russisches E-Commerce-Paket-, Privatversand- und Fulfillment-Netzwerk, das Online-Bestellungen und Avito-Delivery-Pakete an mehr als **25.700** Paketstationen (*постаматы*) und Kassen-Abholpunkte in **Pyaterochka**- («Пятёрочка») und **Perekrestok**-Supermärkten («Перекрёсток») in **4.950** Städten und Orten Russlands liefert, unterstützt von **51** Sortierzentren ([fivepost.ru](https://fivepost.ru/)). 5Post arbeitet mit über 1.000 Online-Händlern und Marktplätzen zusammen — darunter Avito Delivery und internationale AliExpress-Bestellungen — und bietet außerdem Haustür-Kurierzustellung sowie LTL-/FTL-Fracht an.

Du kannst ein Paket auf [fivepost.ru](https://fivepost.ru/) (unter *Где мой заказ?*) prüfen oder deine Bestellnummer beziehungsweise einen Link wie `fivepost.ru/tracking?id=...` oben einfügen.

## Formate von 5Post-Sendungsnummern

5Post verfolgt Sendungen über die Bestellnummer des Shops oder Marktplatzes — eine beliebige zusammenhängende Folge aus 3 bis 40 Buchstaben und Ziffern mit mindestens einer Ziffer:

| Format (erfunden) | Worum es sich handelt |
| --- | --- |
| `P48291736051` | `P` + 11 Ziffern (12 Zeichen), das Format der Avito-Delivery-Nummern. Peek fragt [Colis Privé](carrier:colis-prive) und 5Post an, in Russland 5Post zuerst. |
| `99994829173605` | 14 Ziffern, eine Bestellnummer eines Onlineshops. Peek schlägt dafür nie 5Post vor und fragt DPD-Gesellschaften, Chronopost, SEUR, BRT und andere an: Wähle 5Post selbst aus oder füge den 5Post-Link ein. |

Da Shops ihre Bestellnummern frei vergeben, können zwei Bestellungen dieselbe Nummer tragen. Die Website von 5Post fragt dann nach Ziffern der Telefonnummer des Empfängers, und Peek kann die Bestellungen nicht unterscheiden. Ein Link `fivepost.ru/tracking?id=...` ordnet das Paket sofort 5Post zu. Weitere Formate findest du im [Leitfaden zu Sendungsnummern-Formaten](guide:tracking-number-formats).

## Was die 5Post-Sendungsstatus bedeuten

Der Tracking-Dienst von 5Post kombiniert technische Statuscodes mit russischen Beschreibungen und nennt bei Lager- und Versandscans das jeweilige regionale Sortierzentrum (`РЦ ...`).

| 5Post-Code & russischer Wortlaut | Bedeutung |
| --- | --- |
| `INFORMATION_RECEIVED` — `Ожидается передача заказа в 5post` | Bestelldaten wurden übermittelt; 5Post wartet auf die Übergabe des Pakets durch Verkäufer oder Absender. |
| `PROCESS_WITHDRAWN` — `Передан службе доставки` | Der Absender hat das Paket bei 5Post abgegeben oder übergeben. |
| `WAREHOUSE_PROCESSING` — `Обрабатывается на складе ...` | In Bearbeitung in einem regionalen 5Post-Sortierlager (`РЦ`). |
| `READY_FOR_SHIPMENT` — `Покинул склад ...` | Hat das Sortierlager in Richtung nächster Hub oder Abholfiliale verlassen. |
| `DELIVERED` — `Доставлен в пункт выдачи` | In der gewählten Pyaterochka- / Perekrestok-Filiale oder 5Post-Paketstation eingetroffen — **abholbereit**, noch nicht abgeholt! |
| `DONE` — `Получен` | Vom Empfänger abgeholt (zugestellt). |
| `RECEIVED_BY_COURIER` — `Передан курьеру` | Bei Kurierbestellungen unterwegs zur Haustürzustellung. |
| `RETURNING_BY_COURIER` — `Неуспешная попытка вручения` | Ein Kurier-Zustellversuch ist fehlgeschlagen. |
| `CUSTOMS_CLEARANCE_STARTED` / `CUSTOMS_DATA_REQUIRED` / `CUSTOMS_AWAITING_PAYMENT` | Auslandsbestellung (z. B. AliExpress) in der Verzollung, wartet auf deine Daten für den Zollvertreter oder auf die Zahlung der Abgaben. |
| `CUSTOMS_CLEARANCE_COMPLETED` — `Таможенное оформление завершено` | Der Zoll hat das Paket für den Weitertransport mit 5Post freigegeben. |
| `UNCLAIMED_...` / `RETURNED_TO_PARTNER` / `RETURNED_TO_CLIENT` | Nach Ablauf der Lagerfrist nicht abgeholt oder nach Übergabe storniert und auf dem Rückweg an Shop oder Absender (`UNCLAIMED_READY_FOR_REPICKUP` steht für die Vorbereitung einer erneuten Einlagerung im Abholpunkt). |
| `INCEPTION_PROBLEM` / `LOST` / `CANCELLED` / `REGISTRATION_ERROR_...` | Bestellproblem, Stornierung, Verlust oder fehlende Telefonnummer / fehlendes Lieferdatum / fehlerhafte Adresse. |

Beachte eine wichtige Besonderheit der 5Post-Codes: `DELIVERED` (`Доставлен в пункт выдачи`) bedeutet **abholbereit** in Filiale oder Paketstation, während erst `DONE` (`Получен`) bedeutet, dass du das Paket tatsächlich abgeholt hast.

## Abholung bei Pyaterochka, Perekrestok und an Paketstationen

Eine Abholbestellung wartet in einer 5Post-Paketstation (*постамат*) oder an der Kasse (*касса*) des bei der Bestellung gewählten Pyaterochka- oder Perekrestok-Markts; die 5Post-FAQ nennen die Öffnungszeiten der Stationen und Abholpunkte:

- **Abholcode:** Sobald die Bestellung im Abholpunkt ankommt (`Доставлен в пункт выдачи`), sendet 5Post den Abholcode per SMS. Gib ihn am Display der Paketstation ein oder nenne ihn an der Kasse. In den 5Post-FAQ steht, wo du den Code findest und was zu tun ist, falls das Personal die Bestellung nicht findet.
- **Lagerfrist verlängern & Telefonnummer ändern:** Wie in den [5Post-FAQ](https://fivepost.ru/faq/) beschrieben, können Empfänger Lagerfristen prüfen, die Aufbewahrung verlängern, über Website oder App online bezahlen oder Schritte zum Ändern einer Telefonnummer, Öffnen eines klemmenden Fachs oder Melden eines leeren beziehungsweise falschen Fachs nachlesen.
- **Grenzüberschreitende Bestellungen (AliExpress):** Internationale Bestellungen über 5Post umfassen Luftfracht (`DEPARTED_BY_AIR`, `DELIVERED_BY_AIR`) und die russische Verzollung; zeigt das Tracking `Требуется предоставить данные таможенному представителю` (`CUSTOMS_DATA_REQUIRED`) oder `Требуется оплата таможенных платежей` (`CUSTOMS_AWAITING_PAYMENT`), benötigt der Zollvertreter deine Daten oder die Abgaben sind noch offen; die 5Post-FAQ erklären, welche Bestellungen verzollt werden, wofür die Daten nötig sind, wie du den Zollvertreter erreichst und wann zu zahlen ist. Siehe [Zoll-Ratgeber](guide:customs) und [wenn das Tracking nicht aktualisiert](guide:tracking-not-updating).

Für Pakete, die innerhalb Russlands mit der Staatspost oder einem anderen Kurier laufen, vergleiche mit [Russian Post](carrier:russian-post) und [CDEK](carrier:cdek).

## 5Post mit Peek verfolgen

Füge deine `P`-Nummer, Shop-Bestellnummer oder einen `fivepost.ru/tracking?id=...`-Link in [Peek](/) ein. Bei einer `P`-Nummer fragt Peek Colis Privé und 5Post an (in Russland 5Post zuerst) und meldet „5Post hat dieses Paket.“, sobald 5Post antwortet. Bei einer 14-stelligen Bestellnummer schlägt Peek 5Post nie vor: Tippe beim Hinzufügen auf „Ändern“ oder auf der Paketseite auf „Paketdienst ändern“ und wähle 5Post. Ein 5Post-Link ordnet das Paket sofort 5Post zu.

Peek liest das Gast-Tracking von 5Post über den quelloffenen [universal-parcel-scraper 5Post-Adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/5post) und versucht es nach einem Netzwerkfehler erneut. Angezeigt werden der russische Scan-Wortlaut, das bei Lager-Scans genannte Sortierzentrum (`РЦ ...`, `ХАБ ...`), Name und Adresse des Abholpunkts, sobald das Paket dort oder in einer Station wartet, sowie der Zeitpunkt der Abholung. 5Post versieht jeden Scan mit seinem UTC-Versatz, und Peek zeigt ihn in der Uhrzeit deines Geräts. Lagerfrist, Zahlungsangaben, Öffnungszeiten des Abholpunkts, Absendername und Paketanzahl bleiben auf der Website und in der App von 5Post.

Peek ordnet die 5Post-Codes genau zu, sodass `DELIVERED` keine falsche Haustür-Zustellmeldung auslöst: **Etikett erstellt** (`INFORMATION_RECEIVED`), **Beim Paketdienst eingegangen** (`PROCESS_WITHDRAWN`), **Unterwegs** (Lagerbearbeitung, Abgang, Lufttransport und Weiterleitung nach dem Zoll), **Beim Zoll** (Datenanforderung des Zollvertreters, Freigabe und Abgabenzahlung), **In Zustellung** (`RECEIVED_BY_COURIER`), **Zustellversuch** (`RETURNING_BY_COURIER`), **Abholbereit** (`DELIVERED` — `Доставлен в пункт выдачи`), **Zugestellt** (`DONE` — `Получен`), **Zurück an Absender** (`RETURNED_TO_...` und `UNCLAIMED_...`-Codes außer `UNCLAIMED_READY_FOR_REPICKUP`, das auf Unterwegs bleibt) sowie **Problem gemeldet** (`LOST`, `CANCELLED`, `CUSTOMS_CLEARANCE_REJECTED` und Registrierungsfehler).

Ist der neueste Scan vier Tage alt und das Paket steht noch auf „Etikett erstellt“, „Beim Paketdienst eingegangen“ oder „Unterwegs“, markiert Peek es mit dem Tag dieses Scans, etwa „Keine Aktualisierung seit Mo 12. Okt“. Auf der Paketseite steht dann „Auf langen Flügen, beim Zoll oder übers Wochenende wird es oft still um ein Paket. Peek fragt weiter bei 5Post nach.“, samt Link „Hol dir Hilfe bei 5Post“ zu den 5Post-FAQ. Nach drei Wochen ohne neuen Scan heißt es „Seit über drei Wochen nichts Neues. Ist das Paket noch nicht angekommen, frag beim Verkäufer nach. Peek fragt weiter bei 5Post nach.“

Solange du das Paket in Peek geöffnet hast oder am Zustelltag Benachrichtigungen aktiviert sind, fragt Peek bis zu alle 10 Minuten nach; an dem Tag, an dem 5Post `Передан курьеру` zeigt, alle 2 Minuten ab 6 Uhr nach der Uhr des neuesten Scans, solange der eigene 5Post-Tracker antwortet. Sonst fragt Peek stündlich ab. Nach 12 Stunden nur bei „Etikett erstellt“ oder „Beim Paketdienst eingegangen“ oder nach zwei Tagen ohne Neuigkeiten prüft Peek stündlich, egal wie du die Sendung verfolgst, und nach sieben Tagen bei „Etikett erstellt“ alle 6 Stunden. Antwortet der 5Post-Tracker nicht und Peek verfolgt das Paket allein über Tracking-Dienste, fragt Peek stündlich nach, nach einem ruhigen Tag alle 3 Stunden und an dem Tag, an dem das Paket in Zustellung ist, bis zu alle 15 Minuten, solange du die Sendung geöffnet hast oder Benachrichtigungen aktiviert sind.

Du kannst Benachrichtigungen für „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“ erhalten.

## Kontakt zu 5Post

5Post veröffentlicht offizielle Telefonnummern und Support-Formulare (mit oder ohne Anmeldung) auf [fivepost.ru/contacts](https://fivepost.ru/contacts/) und [fivepost.ru/faq](https://fivepost.ru/faq/):

- **Telefon Moskau und Region Moskau:** `8-495-935-88-00` (zum Tarif deines Anbieters)
- **Russische Regionen:** `8-800-100-05-05`
- **Online-FAQ & Ticketformular:** [5Post-FAQ](https://fivepost.ru/faq/) und [5Post-Kontakt](https://fivepost.ru/contacts/) (Empfängeranfragen mit oder ohne Login sowie Dateianhänge bis 40 MB)
- **Rechtsanschrift:** 109029, Moskau, ul. Srednyaya Kalitnikovskaya, 28 bldg. 4 (INN `7722461620`, OGRN `1187746567568`)

## Häufige Fragen

### Warum lautet der interne 5Post-Status `DELIVERED`, bevor ich mein Paket abgeholt habe?

Im Tracking-System von 5Post bedeutet `DELIVERED` (`Доставлен в пункт выдачи`), dass das Paket an der Pyaterochka-/Perekrestok-Kasse oder Paketstation eingetroffen ist und auf dich wartet. Peek zeigt dafür **Abholbereit** an und wartet auf `DONE` (`Получен`), bevor **Zugestellt** erscheint.

### Wo hole ich eine 5Post-Bestellung ab?

Du holst sie an der 5Post-Paketstation (*постамат*) oder an der Supermarktkasse des beim Kauf gewählten Pyaterochka- («Пятёрочка») oder Perekrestok-Markts («Перекрёсток») mit dem Code aus der 5Post-SMS ab.

### Warum fragt 5Post bei meiner AliExpress-Bestellung nach Zolldaten?

Auslandsbestellungen werden über einen Zollvertreter verzollt, der dafür deine Daten benötigen kann (`CUSTOMS_DATA_REQUIRED`). Die 5Post-FAQ erklären, welche Bestellungen verzollt werden, warum die Angaben nötig sind und wie du den Zollvertreter erreichst. Nach der Zollfreigabe zeigt 5Post `Таможенное оформление завершено` (`CUSTOMS_CLEARANCE_COMPLETED`).

### Wie kontaktiere ich 5Post, wenn sich ein Fach nicht öffnet oder das Paket fehlt?

Rufe `8-495-935-88-00` (Moskau & Region) oder `8-800-100-05-05` (Regionen) an oder sende ein Empfänger-Ticket (mit Fotos bis 40 MB) über [fivepost.ru/contacts](https://fivepost.ru/contacts/) oder dein 5Post-Konto.

:::sources
- [5Post (fivepost.ru)](https://fivepost.ru/)
- [5Post FAQ](https://fivepost.ru/faq/)
- [5Post Kontakt](https://fivepost.ru/contacts/)
- [universal-parcel-scraper 5Post-Adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/5post)
:::
