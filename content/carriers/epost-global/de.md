---
title: ePost Global Sendungsverfolgung: EPG-Nummern & Zusteller
description: Verfolge dein ePost-Global-Paket: Aufbau von EPG-Nummern, Bedeutung der englischen Statuszeilen, Übergabe in Deutschland, Österreich und der Schweiz mit Peek.
slug: epost-global-sendungsverfolgung
published: 2026-11-21
updated: 2026-11-21
---

ePost Global (`epostglobalshipping.com`) ist ein US-amerikanischer Paketkonsolidierer, Postdienstleister und Multi-Carrier-Versandanbieter mit Sitz in Cypress, Kalifornien (`11137 Warland Drive, Cypress, CA 90630, USA`). Hervorgegangen aus der Sparte *Express Postal Options (XPO)* von R.R. Donnelley und seit über 25 Jahren im grenzüberschreitenden Versand tätig, bündelt ePost Global Online-Bestellungen in seinen US-Sortierzentren, übernimmt Luftfracht und Verzollung und übergibt die Pakete an über 100 Post- und Zustellpartner in mehr als 200 Ländern und Gebieten (dazu kommt `eDGE` für Inlandssendungen in den USA und Kanada). Seine eigenen Sendungsnummern beginnen mit `EPG`: Füge deine Nummer oben ins Suchfeld ein. Die offiziellen Tracking-Seiten sind `epgtrack.com` (*EPG Track*) und `ppxtrack.com` (*PPX Track*); in Peeks Katalog findest du ePost Global auch unter `ePostGlobal`, `EPG`, `PPX Track`, `PPXTrack`, `RR Donnelley`, `RRD Donnelley` und `RRD International Logistics`.

## ePost-Global-Sendungsnummer: Aufbau und Beispiele

Peek kennt genau ein eigenes Nummernformat für ePost Global: `EPG` gefolgt von 10 bis 15 Ziffern (aktuelle Etiketten nutzen 15 Ziffern). Mit frei erfundenen Ziffern:

| Nummer (erfunden) | Bedeutung |
| --- | --- |
| `EPG482910573649182` | `EPG` und 15 Ziffern: eine aktuelle ePost-Global-Sendungsnummer. |
| `EPG4829105736` | `EPG` und 10 Ziffern: ebenfalls ePost Global, die kürzeste von Peek akzeptierte Länge. |
| `EPG482910573` | Nur 9 Ziffern: kein ePost-Global-Format. Drei Buchstaben und neun Ziffern passen zu Colis Privé, Purolator und TNT Australia, bei denen Peek deshalb nachfragt. Wählst du ePost Global manuell aus, meldet Peek, dass der Dienst „vergibt keine Nummern wie diese“. Prüfe, ob eine Ziffer fehlt. |
| `EPG4829105736491827` | 16 Ziffern, eine zu viel: Peek erkennt keinen Paketdienst. Prüfe auf eine überzählige Ziffer. |
| `LZ482910576US` | Zwei Buchstaben, neun Ziffern und `US`: eine US-Postnummer. Bei den meisten Codes mit `C`, `E`, `L` oder `R` am Anfang nennt Peek [USPS](carrier:usps) (laut ePost Globals FAQ läuft die Sendungsverfolgung je nach Produkt über das PPX- oder das USPS-Portal). Um in Peek auch die US-Zentrumszeilen von ePost Global zu sehen, füge die `EPG`-Nummer hinzu. |

Leerzeichen, Bindestriche und Kleinschreibung spielen beim Einfügen in Peek keine Rolle. Auf `epgtrack.com` lassen sich bis zu 20 Nummern auf einmal abfragen, auf `ppxtrack.com` bis zu 10.

**Wo du die Nummer findest.** In der Versandbestätigung des Shops oder in deiner Bestellübersicht. Fehlt sie dort, helfen [Sendungsnummer finden](guide:find-tracking-number) und [Formate von Sendungsnummern](guide:tracking-number-formats).

## ePost-Global-Tracking-Status auf Deutsch erklärt

Der Tracker von ePost Global zeigt jede Zeile auf Englisch mit Datum, Uhrzeit, Statuswortlaut und meist einem Ort. Zuerst die eigenen Schritte von ePost Global in zeitlicher Reihenfolge:

| ePost Global meldet | Was das für dein Paket bedeutet | Peek zeigt |
| --- | --- | --- |
| `Data Received` | Der Händler hat die Paketdaten elektronisch übermittelt; das Paket selbst ist noch nicht bei ePost Global. | „Etikett erstellt“ |
| `Shipment in Transit to the ePost Global Processing Center` | Wird bei jedem Paket genau eine Minute nach `Data Received` erzeugt: Teil der Etikettenerstellung, noch kein echter Transportscan. | „Etikett erstellt“ |
| `Parcel Received and Processing` | Ein US-Verarbeitungszentrum von ePost Global hat das Paket gescannt (mit Ort und US-Bundesstaat). Laut ePost Global verlassen Pakete das Zentrum in 24 Stunden oder weniger. | „Beim Paketdienst eingegangen“ |
| `Parcel Shipped` | Das Paket hat das US-Verarbeitungszentrum verlassen. | „Unterwegs“ |
| `In transit to … tracking #: …` | Übergabe an den Weitertransport-Partner eine Minute nach dem Versand, mit Angabe des Services und der Partner-Sendungsnummer (teils auch `In transit via …` oder `tracking#:`). | „Unterwegs“ |
| `In transit` | Dieselbe Übergabe ohne Angabe des Services oder der Partnernummer. | „Unterwegs“ |

**Weitergeleitete Partnerzeilen.** Nach der Übergabe reicht `epgtrack.com` Scans des Partners durch. Die in Peeks Code hinterlegten Meldungen:

| Nach der Übergabe | Was das für dein Paket bedeutet | Peek zeigt |
| --- | --- | --- |
| `Shipping label created - OSM awaiting item` | Der US-Partner OSM Worldwide hat die Sendungsdaten erhalten. | keine Rückstufung |
| `Arrived at OSM facility`, `Departed OSM facility`, `Package accepted at courier partner`, `Package processed by courier partner` | Durchlauf bei OSM Worldwide und Übergabe an dessen Zustellpartner. | „Unterwegs“ |
| `Electronic manifest received` | Elektronisches Manifest bei ECMS Express nach dem Eingang bei ePost Global: reine Datenmeldung ohne Ort, die den Status unverändert lässt. | keine Änderung |
| `Parcel has arrived at export distribution warehouse` | Eingang im Exportlager von ECMS Express im Abgangsland. | „Unterwegs“ |
| `Customer Information Manifested` | DAI Post, der Partner für Neuseeland, hat die Auftragsdaten erhalten. | keine Rückstufung |
| `Checked in at origin hub`, `Airline Delay`, `Collected from the airline for processing` | US-Hub von DAI Post, Flugverspätung (`Airline Delay` heißt verspäteter Flug, kein Problem mit dem Paket) und Abholung vom Flugzeug im Zielland. | „Unterwegs“ |
| `Consignment received at the PostNL Acceptance Centre`, `The item has been manifested and ready to transport`, `The item is tied to a MAWB and ready for shipment`, `The item is received by PostNL`, `The item is on transport to the country of destination` | [PostNL](carrier:postnl) hat das Paket über Spring übernommen und für den Lufttransport ins Zielland eingeplant. | „Unterwegs“ |
| `The consignment has arrived in the country of destination`, `The item has arrived in the country of destination`, `The item is at the PostNL sorting center`, `The item is processed at the PostNL sorting center`, `The item has been processed in the country of destination` | Ankunft im Zielland und Sortierung über PostNL. | „Unterwegs“ |
| `The item is at customs`, `The item is being processed by customs` | Einfuhrzollprüfung einer über PostNL laufenden Sendung. | „Beim Zoll“ |
| `Item released by customs` | Vom Einfuhrzoll freigegeben (PostNL); das Paket läuft weiter. | „Unterwegs“ |
| `The item is out for delivery` | Zustelltour laut PostNL-Meldung. | „In Zustellung“ |
| `The item will be scheduled for a new delivery attempt` | Zustellversuch über PostNL fehlgeschlagen; ein neuer Versuch wird eingeplant. | „Zustellversuch“ |
| `Your item has been dropped off by the sender with our postal partner in the country of origin.`, `Your item is ready to be forwarded from its foreign territory. It will be given to the carrier for forwarding.` | Meldungen von [La Poste](carrier:la-poste) für Pakete nach Frankreich über PostNL, noch vor der Ankunft in Frankreich. | „Unterwegs“ |
| `The parcel has left the parcel center.`, `The parcel has reached the parcel center.` | Europäisches GLS-Netz: Abfahrt im Hub und Ankunft im Paketzentrum. | „Unterwegs“ |
| `The parcel is expected to be delivered during the day.` | Europäisches GLS-Netz: heute in der Zustellung. | „In Zustellung“ |
| `The parcel has been delivered.` | Europäisches GLS-Netz: zugestellt. | „Zugestellt“ |
| `Departed Hub`, `Shipment Received in Depot`, `Shipment Departed Depot` | Britischer Partner von [Royal Mail](carrier:royal-mail): unterwegs nach Großbritannien bzw. im Depot. | „Unterwegs“ |
| `Arrived in customs` | Derselbe britische Partner: beim britischen Zoll. | „Beim Zoll“ |
| `Ready for Collection` | Derselbe britische Partner: abholbereit in einer Abholstelle. | „Abholbereit“ |
| `Duty &/or tax fees requested` | Der Zustellpartner im Zielland fordert vor der Auslieferung Zoll oder Einfuhrabgaben an. | „Beim Zoll“ |
| `Received payment` | Die Abgaben wurden bezahlt; die Sendung wird weiterbefördert. | „Unterwegs“ |
| `The item is available at the pickuppoint` | Das Paket liegt in einem Abholpunkt des Zustellpartners bereit. | „Abholbereit“ |

Ein Eingangsscan des Partners nach `Parcel Shipped` zählt in Peek als „Unterwegs“, nicht als Neustart. Weitere Partnertexte stuft Peek nach seinen allgemeinen Wortlaut-Regeln ein; unbekannte Zeilen bleiben sichtbar und setzen ein Paket in Zustellung oder nach erfolgter Lieferung nicht zurück.

## Wer liefert ePost-Global-Pakete in Deutschland, Österreich und der Schweiz?

ePost Global arbeitet auf sechs Kontinenten mit über 100 Post- und Zustelldiensten zusammen. In den Sendungsverläufen in Peeks Code tauchen unter anderem das europäische GLS-Netz, [PostNL](carrier:postnl) über Spring (mit Weiterleitung an [La Poste](carrier:la-poste) in Frankreich), OSM Worldwide, ECMS Express, DAI Post und [Royal Mail](carrier:royal-mail) auf. Nennt ePost Global keinen Partner beim Namen, vergibt aber eine gültige internationale Postnummer (zwei Buchstaben, neun Ziffern, zwei Buchstaben), fragt Peek die Landespost des Ziellands ab: [DHL](carrier:dhl) in **Deutschland**, die [Österreichische Post](carrier:austrian-post) in **Österreich** und die [Schweizerische Post](carrier:swiss-post) in der **Schweiz** (oder [Canada Post](carrier:canada-post) in Kanada).

**Laufzeiten und Tracking-Umfang.** Ab den USA nennt ePost Global auf *Cross-Border Parcel Shipping* als Richtwert `1-5 days` von Tür zu Tür auf den meisten Routen für *Expedited Parcel with Delivery Confirmation* sowie `4 to 8 days` nach Kanada und `5 to 10 days` nach Westeuropa für *Priority Parcel with Delivery Confirmation* (`4 to 8 days` nach Kanada und Westeuropa bei *Priority Packet Tracked*, mit durchgehendem Tracking in 48 Kernländer). Fünf der sieben Paketprodukte bieten durchgehendes Tür-zu-Tür-Tracking; die beiden Economy-Tarife liefern Sendungsverfolgung im Zielland nach dortiger Ankunft. Zustellversuche und Lagerfristen richten sich nach dem lokalen Zusteller – siehe [Zustellung verpasst](guide:missed-delivery) und [Paket nicht abgeholt](guide:uncollected-parcel).

**Zoll in Deutschland, Österreich, der EU und der Schweiz.** In der **Europäischen Union** (**Deutschland** und **Österreich**) fällt seit dem 1. Juli 2026 für online außerhalb der EU gekaufte Waren in Sendungen mit einem Sachwert bis 150 € ein EU-Zollsatz von 3 € pro Position (*per item*) an; seit dem 1. November 2026 kommt unabhängig vom Wert eine EU-Bearbeitungsgebühr (*Union handling fee*) von 2 € pro Position hinzu (eine Position bündelt Waren desselben Pakets mit gleicher Zolltarifnummer, Beschreibung und – falls anzugeben – gleichem Ursprung; über 150 € gelten reguläre Zollsätze), zuzüglich Einfuhrumsatzsteuer in **Deutschland** und **Österreich**, sofern sie nicht schon beim Kauf über IOSS berechnet wurde. Laut ePost Globals eigener EU-Zollseite gilt die 2-€-Gebühr sowohl für `DDP`-Sendungen (Abgaben bereits beim Kauf bezahlt) als auch für `DDU`-Sendungen; zudem heißt es dort ausdrücklich, dass **Deutschland** keine `DDU`- (`DAP`-)Sendungen mehr annimmt und ePost Global das `DDU`-Routing nach Deutschland am 26. Oktober abgeschaltet hat (*Germany no longer accepts DDU (DAP) shipments. ePost turned off DDU routing to Germany on October 26*). In der **Schweiz** erhebt das Bundesamt für Zoll und Grenzsicherheit (BAZG) 8,1 % Einfuhrsteuer (2,6 % ermäßigter Satz), wobei „Zollabgaben bis 5 Schweizerfranken nicht erhoben“ und „Steuerbeträge bis 5 Schweizerfranken nicht erhoben“ werden – zuzüglich möglicher Verzollungsgebühren des Zustellers. Mehr unter [Paket hängt beim Zoll](guide:customs).

## ePost-Global-Paket hängt fest, kommt nicht an oder ist unbekannt

**„Warten auf ePost Global“.** Meldet `epgtrack.com` `We haven't received any tracking updates`, zeigt Peek „Warten auf ePost Global“ und prüft weiter. Kontrolliere, ob die Nummer aus `EPG` und 10 bis 15 Ziffern besteht; kennt ePost Global sie auch nach zwei Tagen noch nicht und findet kein Tracking-Dienst etwas, zeigt Peek: „Auch nach zwei Tagen kennt der Paketdienst diese Nummer nicht. Prüfe sie, wähle einen anderen Paketdienst oder lösche das Paket.“

**Keine Updates nach `In transit to …`.** Dann hat der Partner übernommen, dessen eigenes Tracking oft weiter ist. Falls Peek noch nicht dorthin gewechselt hat, kopiere die Nummer hinter `tracking #:` und füge sie separat in Peek ein.

**Seit Tagen keine Bewegung.** Nach vier Tagen ohne neuen Scan blendet Peek den Tag des letzten Scans ein („Keine Aktualisierung seit Mo 12. Okt“), weist darauf hin, dass Pakete auf Langstreckenflügen, beim Zoll oder am Wochenende oft ruhen (nach über drei Wochen ohne Neues rät Peek stattdessen, beim Verkäufer nachzufragen), und zeigt den Link „Hol dir Hilfe bei ePost Global“ (oder den des Partners, sobald Peek das Paket bei einem Zusteller verfolgt, für den eine Hilfeseite hinterlegt ist; bei OSM Worldwide und DAI Post bleibt der Link bei ePost Global). Aktuelle Ländereinschränkungen stehen unter `epostglobalshipping.com/service-updates`; siehe auch [Warum sich das Tracking nicht aktualisiert](guide:tracking-not-updating).

**Als zugestellt markiert, verloren oder zurückgeschickt.** Frag zuerst beim lokalen Zusteller mit dessen Sendungsnummer nach und melde dich dann beim Online-Shop, der den Versand beauftragt hat. Siehe [Zugestellt, aber nicht erhalten](guide:delivered-not-received), [Paket verloren](guide:lost-parcel) und [Zurück an den Absender](guide:returned-to-sender).

## ePost Global Kundenservice und Kontakt

Laut `epostglobalshipping.com`:

- **Telefon (USA)**: `+1 866-784-8444` (gebührenfrei innerhalb der USA) sowie `+1 310-784-8485` (Ortsnetz), ohne veröffentlichte Sprechzeiten oder Support-E-Mail-Adresse.
- **Online-Assistent ePost Assist**: unter `epostassist.epgtrack.com` (über *Track a package* oder *Chat with our online support*) mit den Punkten *Check package status*, *Create a support ticket* und *Get support ticket status*.
- **Kontaktformular**: unter `epostglobalshipping.com/contact-support` (*Looking for support beyond tracking a package?*) mit optionalem Feld für die Sendungsnummer.
- **Hauptsitz**: `11137 Warland Drive, Cypress, CA 90630, USA`.

**Vorsicht vor Phishing-SMS.** Offizielle Adressen von ePost Global für Sendungsverfolgung und Hilfe sind `epgtrack.com`, `epostassist.epgtrack.com`, `ppxtrack.com` und `epostglobalshipping.com`. Bei einer `DDU`-Sendung nach Österreich oder in die Schweiz kommt eine echte Zahlungsaufforderung für Zoll oder Einfuhrsteuer vom Zusteller vor Ort – prüfe verdächtige Links anhand von [Gefälschte Paket-SMS erkennen](guide:delivery-scam-texts).

## ePost-Global-Pakete mit Peek verfolgen

[Peek](/) erkennt `EPG` mit 10 bis 15 Ziffern sofort („Erkannter Paketdienst“) und fragt `epgtrack.com` ohne Konto oder Postleitzahl ab. Auch ein Tracking-Link von `epgtrack.com` oder `ppxtrack.com` funktioniert direkt: Peek liest die Nummer aus (bei mehreren Einträgen in `?id=` die erste). Hast du nur die Nummer des Zustellpartners, füge sie trotzdem ein – Peek unterstützt mehr als 3.500 Paketdienste.

Peek zeigt den Status, jeden Scan mit seinem Ort, das Zielland sowie den benannten Partner samt Partnernummer. Fortschrittsbalken, Postleitzahlen, Depot- oder Hausnummern, Kundenreferenz und Luftfrachtbriefnummern (`MAWB`) lässt Peek weg; voraussichtliches Lieferdatum, Gewicht oder Zustellnachweis liefert `epgtrack.com` nicht. Über „Website von ePost Global öffnen“ gelangst du direkt zu deiner Sendung auf `epgtrack.com`.

Die Zeitstempel des Trackers enthalten keine Zeitzone. Peek liest `Parcel Received and Processing` und `Parcel Shipped` in der Zeitzone des jeweiligen US-Bundesstaats (sofern der gesamte Staat eine einheitliche Zeit hat), länderlose PostNL-Einträge auf der Uhr von PostNL, Zeilen des europäischen GLS-Netzes in mitteleuropäischer Zeit und Zeilen des britischen Royal-Mail-Partners in britischer Zeit; übrige Zeilen werden über ihren Ort oder den Transportabschnitt eingeordnet. Alle Zeiten erscheinen in der Zeitzone deines Geräts; auf `peektracker.com` sowie in der iPhone- und Android-App endet der Verlauf mit „Uhrzeiten in deiner Zeitzone: …“.

Nennt ePost Global einen Partner, den Peek kennt, steht auf der Paketkarte etwa „Zustellung durch PostNL“ – oder „Bei PostNL angekündigt, noch nicht eingetroffen“, solange der Partner die Sendung erst ohne eigenen Scan führt – samt Partnernummer hinter der `EPG`-Nummer. Peek fragt diesen Partner ebenfalls ab (unter dessen Nummer oder, falls keine angegeben ist, unter der `EPG`-Nummer); ohne benannten Partner prüft Peek bei einer gültigen Postnummer die Post des Ziellands oder den einzelnen Paketdienst, auf den das Nummernformat eindeutig verweist (sofern Peek dessen eigenes Tracking ohne Zusatzangaben liest). Sobald dort ein Scan vorliegt, der mindestens so aktuell ist wie der jüngste von ePost Global, wechselt Peek dorthin und behält die Vorgeschichte von ePost Global (für das europäische GLS-Netz bleibt Peek bei den über ePost Global weitergeleiteten Zeilen, da ePost Global nicht angibt, welche nationale GLS-Gesellschaft zustellt).

Solange du das Paket geöffnet hast oder am Liefertag Mitteilungen aktiviert sind, prüft Peek bis zu alle 10 Minuten – und ab 6 Uhr Ortszeit am Tag mit Status „In Zustellung“ bis zu alle 2 Minuten, solange der Tracker von ePost Global antwortet (übernimmt ein Zustellpartner, folgt Peek dessen Takt; über Tracking-Dienste allein: stündlich, alle 3 Stunden nach einem ruhigen Tag und bis zu alle 15 Minuten am Liefertag bei geöffnetem Paket oder aktiven Mitteilungen). Sonst sowie nach zwei Tagen ohne Neuigkeiten (12 Stunden bei „Etikett erstellt“ oder „Beim Paketdienst eingegangen“) prüft Peek stündlich. Benachrichtigungen gibt es für „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“. ePost Global oder den Händler kontaktieren kann Peek nicht.

## Häufige Fragen zur ePost-Global-Sendungsverfolgung

### Was ist PPX Track?

Ein weiteres offizielles Tracking-Portal von ePost Global (`ppxtrack.com`, für bis zu 10 Nummern gleichzeitig) und ein Alias von ePost Global in Peeks Katalog. Peek liest `ppxtrack.com`-Links genauso aus wie Links von `epgtrack.com`.

### Was bedeutet „In transit to“ im ePost-Global-Tracking?

ePost Global hat dein Paket an den Partner übergeben, der den Weitertransport übernimmt. Die Zeile nennt das gewählte Versandprodukt und hinter `tracking #:` die eigene Sendungsnummer dieses Partners.

### Warum hängt mein Paket bei „Data Received“?

Der Online-Shop hat die elektronischen Auftragsdaten an ePost Global gesendet, aber noch kein US-Sortierzentrum hat das Paket physisch gescannt. Frag beim Händler nach, wann die Sendung tatsächlich übergeben wurde.

### Wer stellt ePost-Global-Pakete in Deutschland, Österreich und der Schweiz zu?

Ein lokaler Post- oder Paketpartner aus der Übergabezeile – in Peeks Daten etwa das europäische GLS-Netz oder PostNL über Spring. Bei internationalen Postnummern ohne benannten Partner fragt Peek die jeweilige Landespost ab (DHL in Deutschland, Österreichische Post in Österreich, Schweizerische Post in der Schweiz).

### Wie erreiche ich den Support von ePost Global?

Wende dich zuerst an den Shop, bei dem du bestellt hast. Direkt erreichst du ePost Global über *ePost Assist* auf `epostassist.epgtrack.com`, das Formular unter `epostglobalshipping.com/contact-support` oder telefonisch in den USA unter `+1 866-784-8444` bzw. `+1 310-784-8485`.

:::sources
- [ePost Global: Startseite](https://epostglobalshipping.com/) – US-Paketkonsolidierer, 20M+ Sendungen jährlich, 100+ Carrier-Partnerschaften, 200+ Länder, Adresse in Cypress und US-Telefonnummern
- [ePost Global: About us](https://epostglobalshipping.com/about-us) – über 25 Jahre Erfahrung, Ursprung bei R.R. Donnelley (XPO), Partner auf sechs Kontinenten
- [ePost Global: Cross-Border Parcel Shipping](https://epostglobalshipping.com/cross-border-parcel-shipping) – Weiterleitung binnen 24 Stunden, Laufzeiten nach Kanada und Westeuropa, Tür-zu-Tür- vs. Zielland-Tracking, DDP und DDU, PPX- und USPS-Portale
- [ePost Global: eDGE](https://epostglobalshipping.com/edge) – Versandnetz für die USA und Kanada mit über 100 Zustellpartnern
- [ePost Global: EU customs fee ecommerce](https://epostglobalshipping.com/eu-customs-fee-ecommerce) – 2-€-Gebühr bei DDP und DDU, 3-€-Zoll bis 150 €, Abschaltung von DDU nach Deutschland am 26. Oktober, IOSS
- [ePost Global: Contact support](https://epostglobalshipping.com/contact-support) – Support-Formular, Link zu ePost Assist, Telefonnummern und Anschrift
- [ePost Global: Contact us](https://epostglobalshipping.com/contact-us) – Verweis auf den Online-Support bei vermissten Paketen
- [ePost Global: Service updates](https://epostglobalshipping.com/service-updates) – aktuelle Ländersperren und Betriebshinweise
- [EPG Track](https://epgtrack.com/) – offizieller Tracker für bis zu 20 Sendungsnummern
- [PPX Track](https://ppxtrack.com/) – PPX-Track-Portal für bis zu 10 Sendungsnummern
- [ePost Assist](https://epostassist.epgtrack.com/) – Paketstatus prüfen und Support-Tickets erstellen oder abfragen
- [Europäische Kommission: EU-Zollreform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 3 € Zollsatz seit 1. Juli 2026 und 2 € Bearbeitungsgebühr seit 1. November 2026
- [Europäische Kommission: Leitlinien zum 3-€-Zoll und zur Bearbeitungsgebühr (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 2 € unabhängig vom Wert, Positionsbegriff und IOSS-Steuerbefreiung bei der Einfuhr
- [BAZG: Empfangen von Briefen und Paketen](https://www.bazg.admin.ch/de/empfangen-von-briefen-und-paketen) – Schweizer Einfuhrsteuer 8,1 % / 2,6 % und Nichterhebung bis 5 Franken
- [Universal Parcel Scraper: ePost Global](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/epost-global) – Aliasnamen, EPG-Muster, Links, eigene und weitergeleitete Statuszeilen, Zeitzonen, verworfene Felder und Hilfeseite
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Colis-Privé-, Purolator- und USPS-Muster sowie Übergabe an Partner oder Landespost
:::
