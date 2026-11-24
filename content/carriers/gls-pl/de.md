---
title: GLS Poland Sendungsverfolgung: Status, Points & Locker
description: GLS-Poland-Paket verfolgen: 11-/12-stellige Nummern & Track-ID, optionale PLZ, Status, GLS Points & ORLEN-Automaten, FlexDeliveryService & Hilfe.
slug: gls-poland-sendungsverfolgung
published: 2026-11-24
updated: 2026-11-24
---

GLS Poland (*General Logistics Systems Poland Sp. z o.o.*, mit Sitz in Głuchowo bei Posen und zentralem Sortierhub in Stryków bei Łódź) betreibt rund 50 Niederlassungen und über 26.000 Abholstandorte in ganz Polen – darunter mehr als 18.000 GLS Points (einschließlich aller 11.000 Żabka-Filialen) sowie etwa 8.000 gemeinsam genutzte [ORLEN-Paczka](carrier:orlen-paczka)-Paketautomaten rund um die Uhr. Verfolge deine Sendung auf der [Tracking-Seite von GLS Poland](https://gls-group.com/PL/en/parcel-tracking/) mit Paketnummer oder Track-ID oder füge die Nummer oben ein.

## GLS-Poland-Sendungsnummer und Track-ID: Formate

Deine Paketnummer findest du in der Versand-E-Mail des Onlineshops, in der GLS-E-Mail oder -SMS oder auf einer Benachrichtigungskarte (*awizo*). Das Suchfeld von GLS akzeptiert außerdem die Track-ID aus den GLS-Benachrichtigungen.

| Format (erfunden) | Was es ist |
| --- | --- |
| `48291736405` | 11-stellige GLS-Paketnummer. Auch genutzt von [GLS Deutschland](carrier:gls-de), [GLS Frankreich](carrier:gls-fr), [GLS Schweiz](carrier:gls-ch), [GLS Österreich](carrier:gls-at) und [PPL](carrier:ppl). |
| `482917364052` | 12-stellige Paketnummer mit Prüfziffer am Ende. Auch genutzt von [BRT](carrier:brt), [Colis Privé](carrier:colis-prive) und anderen. |

Auf der [Tracking-Seite von GLS Poland](https://gls-group.com/PL/en/parcel-tracking/) schaltet die Empfänger-Postleitzahl die vollständigen Scan-Details und Umleitungsoptionen frei. Weitere Formate findest du unter [Sendungsnummern-Formate](guide:tracking-number-formats).

## Was die GLS-Poland-Sendungsstatus bedeuten

GLS Poland zeigt auf seiner polnischen Website polnische Statusbeschreibungen und über die von Peek abgefragte internationale GLS-Tracking-API englische Gruppenbeschreibungen.

| GLS Poland / GLS Group zeigt | Was das für dich bedeutet | Peek zeigt |
| --- | --- | --- |
| `Preadvice`, `Nadawca nadał numer paczce - paczka nie została jeszcze przekazana kurierowi GLS` | Der Absender hat das Etikett elektronisch erstellt; GLS hat das physische Paket noch nicht erhalten. | „Etikett erstellt“ |
| `Paczka została przekazana GLS` | GLS hat das Paket abgeholt oder in sein Netz übernommen. | „Beim Paketdienst eingegangen“ |
| `In transit`, `Final parcel center`, `Paczka zarejestrowana w filii GLS`, `Paczka wyjechała z filii GLS` | Unterwegs durch GLS-Sortierzentren und polnische Niederlassungen Richtung Zustelldepot. | „Unterwegs“ |
| `In delivery`, `Paczka w doręczeniu` | Der Fahrer hat dein Paket heute zur Zustellung geladen. | „In Zustellung“ |
| Ankunft in einem GLS Point oder Paketautomaten, was GLS als zugestellt markiert | Wartet in einem GLS Point oder 24/7-Paketautomaten auf dich. | „Abholbereit“ |
| `The parcel has been delivered.`, `Paczka została doręczona`, `Handing over the parcel to the recipient at the GLS ParcelShop.` | An dich, eine bevollmächtigte Person oder einen Nachbarn übergeben oder im GLS Point abgeholt. | „Zugestellt“ |
| `Paczka niedoreczona - odmowa przyjecia przez Odbiorce` | Der Empfänger hat die Annahme verweigert. | „Zustellversuch“ |
| `Paczka pozostawiona w filii doreczajacej - Odbiorca odmowil przyjecia` | Annahme verweigert, das Paket liegt in der Zustellniederlassung. | „Problem gemeldet“ |

Am Zustelltag verschickt [Live Tracking von GLS Poland](https://gls-group.com/PL/en/live-tracking/) per E-Mail einen Link mit einer auf 15 Minuten eingegrenzten Lieferzeit und zeigt den Fahrer auf einer Karte, sobald 20 Stopps oder weniger verbleiben. Die GLS-Fortschrittsleiste markiert ein im GLS Point oder Automaten abgelegtes Paket bereits als zugestellt, während es auf dich wartet; Peek zeigt diesen Schritt als „Abholbereit“ und erst nach der Abholung als „Zugestellt“.

## Lieferzeiten, GLS Points, ORLEN-Automaten und FlexDeliveryService

**Zustelltage und Laufzeit.** GLS Poland liefert montags bis freitags (Samstagszustellung nur bei ausdrücklicher Buchung durch den Absender). Inländische Standardpakete kommen meist am nächsten Werktag an (nicht garantiert; Absender können Express 10:00 oder 12:00 buchen). Aus Polen nennt GLS 2 bis 7 Werktage in andere EU-Länder je nach Ziel ([GLS Points](https://gls-group.com/PL/en/shipping-receiving-points/gls-points/)).

**Gewicht, Maße und CashService (Nachnahme).** Inländische Hauspakete dürfen bis zu 31,5 kg wiegen (40 kg international; 31,5 kg in ausgewählten GLS Points; 20 kg in übrigen GLS Points, Paketautomaten und myGLS), bei höchstens 200 × 80 × 60 cm und einem Gurtmaß bis 300 cm. Nachnahme (`CashService`, bis 6.000 PLN pro Tag) lässt sich beim Fahrer bar, per Karte oder per BLIK bezahlen.

**Verpasste Zustellung und zweiter Versuch.** Laut der [Empfangsseite von GLS Poland](https://gls-group.com/PL/en/receiving/) darf der Fahrer das Paket bei Abwesenheit (mit deiner Zustimmung) beim Nachbarn abgeben oder ein geeignetes Paket ohne Nachnahme an einen nahen GLS Point umleiten. Andernfalls geht es zurück in die Niederlassung für einen zweiten Versuch am nächsten Werktag; bleibt auch dieser erfolglos, lagert es im Depot, bevor es an den Absender zurückgeht (5 Werktage bei FlexDeliveryService-Depotabholung bzw. Rücksendung nach 6 Werktagen laut Empfangsleitfaden).

**Lagerfrist in GLS Points.** Nach den [AGB von GLS Poland](https://gls-group.com/PL/pl/regulamin/) für GLS Points und Paketautomaten (in Kraft ab 9. November 2026) und den [FAQ](https://gls-group.com/PL/en/faq/) warten Pakete im GLS Point **7 Kalendertage**, bevor sie zurückgehen (die Seiten [Receiving](https://gls-group.com/PL/en/receiving/) und GLS Points sprechen noch von sieben Werktagen). Abholung erfolgt mit der per SMS oder E-Mail gesendeten PIN; GLS kann den Ausweis prüfen.

**24/7-Paketautomaten von ORLEN Paczka.** Pakete in [gemeinsamen ORLEN-Paczka-Automaten](https://gls-group.com/PL/en/shipping-receiving-points/parcel-lockers/) liegen **72 Stunden** bereit und gehen danach automatisch an den Absender zurück. Geöffnet wird das Fach mit SMS-/E-Mail-PIN, QR-Code oder Fernöffnung in der myGLS-App – ohne Ausweis, sodass jeder mit dem Code abholen kann. Technische 24/7-Automaten-Hotline: `801 167 536` oder `502 167 536`.

**FlexDeliveryService-Umleitung.** Über den Link in der GLS-Benachrichtigungs-E-Mail (ohne Konto) kannst du unter [Parcel Redirection](https://gls-group.com/PL/en/parcel-redirection/) einen GLS Point, ORLEN-Automaten, eine andere Adresse, einen neuen Tag, Depotabholung, Abstellgenehmigung oder Annahmeverweigerung wählen.

## Zoll, Schäden, Reklamationen und fehlende GLS-Poland-Pakete

Bei Onlinekäufen hält der Verkäufer den Versandvertrag mit GLS Poland und regelt Erstattungen oder Ersatzlieferungen.

**Pakete aus Nicht-EU-Ländern.** Sendungen innerhalb der EU durchlaufen keinen Zoll. Für Onlinebestellungen aus Drittstaaten gelten laut den [EU-Zollregeln der Europäischen Kommission](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) ein pauschaler Zoll von 3 € je Warenkategorie bis 150 € (in Kraft seit 1. Juli 2026) sowie eine EU-Bearbeitungsgebühr von 2 € (in Kraft seit 1. November 2026) neben der polnischen Einfuhrumsatzsteuer. Siehe [Paket beim Zoll](guide:customs).

**Sichtbare und verdeckte Schäden.** Ein sichtbar beschädigtes Paket kannst du bei der Übergabe verweigern, sodass der Fahrer den Schaden erfasst und es zurücksendet. Laut den [AGB von GLS Poland](https://gls-group.com/PL/pl/regulamin/) müssen nach Annahme entdeckte verdeckte Schäden innerhalb von **7 Tagen** gemeldet werden (schriftlich an die GLS-Zentrale oder über das [Verbraucher-Reklamationsformular](https://gls-group.com/PL/pl/reklamacje-konsumenckie/)). Allgemeine Verbraucherreklamationsfristen liegen bei 30 Tagen ab Übernahme durch GLS (45 Tage bei Verlust, binnen 12 Monaten ab Versand), und GLS bearbeitet Reklamationen binnen 30 Tagen. Siehe [Beschädigtes Paket](guide:damaged-parcel) und [Verlorenes Paket](guide:lost-parcel).

**Stillstand oder zugestellt, aber nicht da.** Bleibt das Tracking tagelang stehen, siehe [Warum sich die Sendungsverfolgung nicht aktualisiert](guide:tracking-not-updating). Steht ein Paket auf zugestellt, obwohl du es nicht hast, prüfe einen GLS Point, frag Nachbarn und melde dich beim Shop; siehe [Zugestellt, aber nicht erhalten](guide:delivered-not-received) und [Zurück an Absender](guide:returned-to-sender).

## Kundenservice von GLS Poland und Phishing-Warnung

Wende dich für Erstattungen zuerst an den Onlineshop. Direkt erreichst du GLS Poland über die Daten auf [GLS Contact](https://gls-group.com/PL/en/gls-contact/) und [Corporate Information](https://gls-group.com/PL/en/corporate-information/):

- **Kunden-Hotline:** `+48 46 814 82 20` (Montag bis Freitag 07:00–19:00 Uhr, Samstag 07:00–15:00 Uhr).
- **E-Mail:** `gls@gls-poland.com` (Zentrale `office@gls-poland.com`).
- **Online-Formulare:** [GLS-Poland-Kontaktformular](https://gls-group.com/PL/en/gls-contact/) und [Verbraucher-Reklamationsformular](https://gls-group.com/PL/pl/reklamacje-konsumenckie/).
- **24/7-Technik-Hotline für Paketautomaten:** `801 167 536` / `502 167 536`.
- **Zentrale:** General Logistics Systems Poland Sp. z o.o., ul. Tęczowa 10, Głuchowo, 62-052 Komorniki, Polen.

> **Betrugswarnung auf den [Sicherheitshinweisen von GLS Poland](https://gls-group.com/PL/en/security-advices/):** GLS verschickt niemals Zahlungslinks per E-Mail zur Bezahlung von Waren per Karte oder Überweisung (CashService/ExWorksService wird bei Übergabe direkt beim Fahrer bar, per Karte oder BLIK gezahlt) und versendet keine betrieblichen Nachrichten über kostenlose Webmail-Adressen wie Gmail oder Outlook. Leite verdächtige Nachrichten an `security@gls-poland.com` (oder `dataprotection@gls-poland.com`) weiter und melde polnische Phishing-Links an CSIRT NASK CERT (`incydent.cert.pl`, `cert@cert.pl` oder SMS `8080`). Siehe [Betrügerische Paket-SMS](guide:delivery-scam-texts).

## GLS-Poland-Paket mit Peek verfolgen

Füge die Nummer in [Peek](/) ein. Da 11- und 12-stellige Nummern von mehreren Paketdiensten genutzt werden, fragt Peek die wahrscheinlichsten ab und richtet sich nach deinem Land (der Region deines Smartphones oder im Web deinem Verbindungsland). In Polen fragt es zuerst bei GLS Poland an – vor [GLS Frankreich](carrier:gls-fr), [GLS Deutschland](carrier:gls-de), [GLS Schweiz](carrier:gls-ch) und anderen wie [PPL](carrier:ppl) bei 11 Ziffern bzw. [BRT](carrier:brt) und [Colis Privé](carrier:colis-prive) bei 12 – und zeigt meist „GLS Poland hat dieses Paket.“. Anderswo wird GLS Poland erst gefragt, wenn die anderen wahrscheinlichen Dienste die Nummer nicht finden. Ein eingefügter `gls-group.com`- oder `gls-group.eu`-Link mit `/PL/` im Pfad und der Nummer in `match=`, `parcelNumber=` oder `matchParcelNumber=` wird direkt als GLS Poland verfolgt. Sonst tippe beim Hinzufügen auf „Ändern“ oder bei einem bestehenden Paket auf „Paketdienst ändern“ und wähle GLS Poland.

Das Feld „Postleitzahl der Lieferadresse“ ist als „Optional“ gekennzeichnet, doch GLS zeigt seine Scans nur damit, wie der Hinweis darunter sagt: „GLS Poland zeigt die Scans nur mit der Postleitzahl der Lieferadresse an.“. Ohne sie erhält Peek den GLS-Status ohne Scans: Es fragt derweil andere Tracking-Dienste ab, und die Paketseite bietet „Postleitzahl eingeben“. Mit Konto probiert Peek einmal die Postleitzahl, die du zuletzt für ein GLS-Poland-Paket angegeben hast, oder hilfsweise die deines letzten Pakets. Findet GLS das Paket mit dieser Postleitzahl nicht, meldet Peek das und bietet „Postleitzahl ändern“. Solange GLS auf die Postleitzahl wartet oder das Paket mit deiner nicht findet und einen Tag lang nichts antwortet, prüft Peek einmal täglich; das Hinzufügen oder Ändern der Postleitzahl prüft das Paket sofort. Die Postleitzahl geht an GLS und bei Bedarf an Tracking-Dienste und erscheint nie auf geteilten Links.

Mit Postleitzahl siehst du Status, den Ort jedes Scans auf einer Karte, den voraussichtlichen Zustelltag, Gewicht, Zustellzeit sowie bei einem GLS Point dessen Namen und Adresse. Dein Name, deine Adresse, Kontaktdaten, die Unterschrift und die Absenderreferenz werden verworfen. Peek liest den englischen Dienst von GLS statt der polnischen Seite: Dort steht `In delivery` statt `W doręczeniu` und `The parcel has been delivered.` statt `Paczka została doręczona`. Eine Ankunft im GLS Point oder Automaten erscheint als „Abholbereit“. Zeiten werden als polnische Zeit gelesen.

Peek fragt GLS etwa einmal pro Stunde ab, Zustelltag eingeschlossen, und manuelles Aktualisieren fragt nicht früher ab (4 Stunden nach einer fehlgeschlagenen Abfrage); nach sieben Tagen ohne Neuigkeiten auf „Etikett erstellt“ alle 6 Stunden. Über Tracking-Dienste allein prüft es stündlich und nach einem stillen Tag alle 3 Stunden.

Ist der neueste Scan vier Tage alt und das Paket steht noch auf „Etikett erstellt“, „Beim Paketdienst eingegangen“ oder „Unterwegs“, markiert Peek es mit dem Datum dieses Scans, etwa „Keine Aktualisierung seit Mo 12. Okt“. Auf der Paketseite steht dann: „Auf langen Flügen, beim Zoll oder übers Wochenende wird es oft still um ein Paket. Peek fragt weiter bei GLS Poland nach.“, mit dem Link „Hol dir Hilfe bei GLS Poland“ zur Kontaktseite von GLS Poland. Nach drei Wochen ohne neuen Scan heißt es: „Seit über drei Wochen nichts Neues. Ist das Paket noch nicht angekommen, frag beim Verkäufer nach. Peek fragt weiter bei GLS Poland nach.“

Du kannst die Benachrichtigungen „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“ wählen. Peek überwacht nur Sendungsstatus und kann weder Pakete umleiten noch Reklamationen bei GLS Poland für dich einreichen.

## Fragen zur GLS-Poland-Sendungsverfolgung

### Wie lange lagert GLS Poland ein Paket im GLS Point oder ORLEN-Automaten?

Nach den aktuellen [AGB](https://gls-group.com/PL/pl/regulamin/) und den [FAQ](https://gls-group.com/PL/en/faq/) von GLS Poland lagern GLS Points Pakete 7 Kalendertage (die Seiten Receiving und GLS Points nennen noch sieben Werktage), während 24/7-Paketautomaten von ORLEN Paczka Pakete 72 Stunden bis zur automatischen Rücksendung aufbewahren.

### Warum verlangt GLS Poland meine Postleitzahl für vollständiges Tracking?

GLS schützt Sendungsdaten, indem vollständige Scan-Historie, Zustelloptionen und ParcelShop-Angaben erst ausgegeben werden, wenn Paketnummer und Empfänger-Postleitzahl übereinstimmen. Gib die Lieferpostleitzahl in Peek ein, um den direkten GLS-Feed zu erhalten.

### Kann ich ein GLS-Poland-CashService-Paket (Nachnahme) per Karte oder BLIK zahlen?

Ja. Laut GLS Poland können CashService-Sendungen (bis 6.000 PLN pro Tag) bei der Übergabe direkt beim Fahrer bar, per Zahlungskarte oder mit BLIK bezahlt werden – niemals über einen unverlangten E-Mail-Link.

### Wie leite ich ein GLS-Poland-Paket an eine Żabka-Filiale oder einen Automaten um?

Öffne den FlexDeliveryService-Link in der GLS-E-Mail (kein Konto nötig) oder nutze [Parcel Redirection](https://gls-group.com/PL/en/parcel-redirection/), um das Paket an einen GLS Point (darunter Żabka-Filialen), einen ORLEN-Paczka-Automaten, eine andere Adresse oder einen späteren Tag umzuleiten.

:::sources
- [GLS Poland: Parcel Tracking](https://gls-group.com/PL/en/parcel-tracking/) – Paketnummer, Track-ID und Postleitzahlen-Abfrage
- [GLS Poland: About GLS](https://gls-group.com/PL/en/about-gls/) – Zentralhub Stryków, Niederlassungen und Netzüberblick
- [GLS Poland: Corporate Information](https://gls-group.com/PL/en/corporate-information/) – Gesellschaftsdaten, Anschrift Głuchowo und `office@gls-poland.com`
- [GLS Poland: Receiving Parcels](https://gls-group.com/PL/en/receiving/) – Werktagszustellung, Nachbarschaftsabgabe, zweiter Versuch, Depotlagerung und CashService
- [GLS Poland: Parcel Redirection](https://gls-group.com/PL/en/parcel-redirection/) – FlexDeliveryService-Optionen ohne Konto
- [GLS Poland: GLS Points](https://gls-group.com/PL/en/shipping-receiving-points/gls-points/) – über 18.000 GLS Points (inkl. 11.000 Żabka-Filialen), über 26.000 Abholstellen, Gewichts-/Maßgrenzen und EU-Laufzeiten
- [GLS Poland: Parcel Lockers](https://gls-group.com/PL/en/shipping-receiving-points/parcel-lockers/) – ca. 8.000 gemeinsame 24/7-ORLEN-Paczka-Automaten, 72-Stunden-Abholfrist, PIN/QR/myGLS-App, 20-kg-Limit und Automaten-Hotline
- [GLS Poland: Live Tracking](https://gls-group.com/PL/en/live-tracking/) – Kartenansicht und auf 15 Minuten eingegrenzte Lieferzeit
- [GLS Poland: FAQ](https://gls-group.com/PL/en/faq/) – 7 Kalendertage im GLS Point, CashService bar/Karte/BLIK, Antworten zu verpasster Zustellung
- [GLS Poland: AGB (Regulamin)](https://gls-group.com/PL/pl/regulamin/) – Bedingungen, für GLS Points und Automaten in Kraft ab 9. November 2026: 7 Kalendertage Abholfrist, 7 Tage Meldung verdeckter Schäden, 30-/45-Tage-Reklamationsfristen und 30 Tage Bearbeitungszeit
- [GLS Poland: Contact](https://gls-group.com/PL/en/gls-contact/) – Hotline `+48 46 814 82 20` (Mo–Fr 07:00–19:00, Sa 07:00–15:00), `gls@gls-poland.com` und Kontaktformular
- [GLS Poland: Verbraucherreklamationen](https://gls-group.com/PL/pl/reklamacje-konsumenckie/) – Online-Reklamationsformular für Verbraucher
- [GLS Poland: Security Advices](https://gls-group.com/PL/en/security-advices/) – Phishing-Warnungen, `security@gls-poland.com` und Meldung an CERT NASK
- [GLS Group: Security Advice](https://gls-group.com/GROUP/en/security-advice/) – offizielle Gruppendomains und CashService-Regeln
- [Europäische Kommission: EU-Zollreform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 3-€-Zoll bis 150 € (seit 1. Juli 2026) und 2-€-EU-Bearbeitungsgebühr (seit 1. November 2026)
- [Universal Parcel Scraper: gls-pl](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/gls-pl) – 11-/12-Ziffern-Erkennung, Polen-Priorität, optionale Postleitzahl, Statuszuordnung, stündlicher/4-Stunden-Rhythmus und Hilfe-URL
:::
