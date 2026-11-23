---
title: Cyprus Post Sendungsverfolgung: Nummern, Status & Zoll
description: Cyprus Post Sendung verfolgen: 13-stelliger S10-Code, Bedeutung der englischen UPU-Statusmeldungen, EU-Zollgebühren und Beschwerdewege.
slug: cyprus-post-sendungsverfolgung
published: 2026-11-22
updated: 2026-11-22
---

Cyprus Post (Κυπριακά Ταχυδρομεία), das Department of Postal Services, ist die staatliche Post der Republik Zypern. Verfolge eine Sendung auf der Website von Cyprus Post, cypruspost.post, oder füge die Nummer oben ein. Gar keine Nummer erhalten? Siehe [Sendungsnummer finden](guide:find-tracking-number).

## Sendungsnummer von Cyprus Post

Der Tracker von Cyprus Post akzeptiert die 13-stelligen Nummern des S10-Standards des Weltpostvereins, die Postunternehmen für Einschreiben, EMS-Sendungen, Pakete und verfolgte Briefe vergeben: [zwei Buchstaben, neun Ziffern und zwei Buchstaben](guide:tracking-number-formats).

:::anatomy RR 482917367 CY
- RR | Servicekennung: der erste Buchstabe zeigt die Sendungsart an
- 482917367 | Achtstellige Seriennummer, gefolgt von einer Prüfziffer
- CY | Ländercode: CY für eine unter zypriotischer Hoheit vergebene Nummer
:::

| Erster Buchstabe (erfundene Beispiele) | Bedeutung |
| --- | --- |
| `RR482917367CY` | R: Einschreiben |
| `CP482917367CY` | C: Paket |
| `EE482917367CY` | E: EMS-Sendung |
| `LA482917367CY` | L: verfolgter Brief |
| `UA482917367CY` | U: Briefsendung mit Waren ohne Kundenverfolgung |

Eine im Ausland aufgegebene Sendung behält die Nummer der dortigen Post mit deren Ländercode (`…GB`, `…FR`, `…GR`), und der Tracker von Cyprus Post kennt sie samt den Scans der ausländischen Post. `There is no information for this item` bedeutet, dass der Tracker die Nummer nicht kennt: prüfe sie beim Absender.

## Bedeutung der Statusmeldungen von Cyprus Post

Auf der englischen Version des Trackers von Cyprus Post, die Peek ausliest, enthält jeder Scan Uhrzeit, Land, Ort, Ereignis, nächste Stelle und Zusatzinfos. Die Ereignisse folgen den [internationalen UPU-Poststatus](guide:tracking-statuses): `(Otb)` steht für einen Schritt im Absendeland, `(Inb)` für einen im Zielland. Die nächste Stelle zeigt an, wohin die Sendung als Nächstes geht, nicht wo sie gerade ist. Für eine eingehende Sendung:

| Status von Cyprus Post | Bedeutung für dich |
| --- | --- |
| `Receive item from customer (Otb)` | Die Sendung wurde aufgegeben, in Zypern oder im Ausland. |
| `Insert item into bag (Otb)` | Im Auswechslungspostamt für den Auslandsversand verpackt. |
| `Receive item at office of exchange (Inb)` | Im Zielland angekommen. |
| `Send items to delivery point/ collection point` | Auf dem Weg zur Filiale, die zustellt oder bereithält. |
| `Receive item at delivery office (Inb)` | In der zustellenden Poststelle angekommen. |
| `Hold item at point of delivery (Inb)` | In der Zustellbasis, wartet auf die Zustelltour. |
| `Handover item to delivery agent (Inb)` | Beim Zusteller. |
| `Item not delivered/ Pending delivery` | Nicht zugestellt: die Spalte Extra Information nennt den Grund. |
| `Receive item at collection point for pick-up (Inb)` | Abholbereit in der Postfiliale. |
| `Deliver item (Inb)` | Zugestellt oder in der Postfiliale abgeholt. |

## Zollgebühren für Waren von außerhalb der EU

Seit dem 1. Juli 2026 kommen online außerhalb der EU gekaufte Waren – einschließlich Postsendungen – bei einem Sendungswert bis 150 € nicht mehr zollfrei ins Land: die EU erhebt einen vorläufigen Zoll von 3 € pro Artikel. Als ein Artikel gelten alle Waren einer Sendung mit derselben Zolltarifnummer, Beschreibung und – sofern angegeben – Herkunft, unabhängig von der Stückzahl. Seit dem 1. November 2026 erhebt die EU zudem unabhängig vom Warenwert eine Unions-Bearbeitungsgebühr von 2 € pro Artikel auf Online-Käufe aus Drittstaaten, auf die ebenfalls Mehrwertsteuer anfällt. Zahlungspflichtig ist in erster Linie, wer die Waren beim Zoll anmeldet: Plattform, Verkäufer, Beförderer oder Zollvertreter. In Zypern erhebt das Department of Customs and Excise Einfuhrzölle und Mehrwertsteuer auf Waren aus Nicht-EU-Staaten. Mehr unter [Paket beim Zoll](guide:customs).

## Cyprus-Post-Sendung verspätet oder nicht angekommen

Bewegt sich das Tracking nicht mehr, lies [Sendungsverfolgung aktualisiert sich nicht](guide:tracking-not-updating). Steht sie auf [zugestellt](guide:delivered-not-received), ist aber nicht da, prüfe die Nummer beim Absender. Bei Verlust oder Bruch helfen [verlorenes Paket](guide:lost-parcel) und [beschädigtes Paket](guide:damaged-parcel).

**Online bestellt und noch am Warten?** Laut dem zypriotischen Consumer Protection Service muss ein Händler in der Regel binnen 30 Tagen liefern, sofern kein anderer Termin vereinbart wurde. Geschieht das nicht, informiere den Händler und setze eine angemessene Nachfrist; bleibt die Lieferung weiter aus, kannst du vom Vertrag zurücktreten. Die Verbraucher-Hotline 1429 ist kostenlos.

**Eine Nachricht verlangt Geld zur Freigabe eines Pakets?** Lies [Betrugs-SMS](guide:delivery-scam-texts), bevor du auf irgendeinen Link tippst.

## Sendungen von Cyprus Post mit Peek verfolgen

Bei einer Postnummer auf `CY` mit gültiger Prüfziffer fragt [Peek](/) Cyprus Post und [Chronopost](carrier:chronopost) gleichzeitig ab und zeigt „Cyprus Post hat dieses Paket.“, sobald Cyprus Post sie kennt, oder „Bei … noch nicht gefunden. Peek prüft nach dem Hinzufügen weiter.“. Bei falscher Prüfziffer fragt Peek Cyprus Post gar nicht an, selbst wenn du den Dienst über „Ändern“ wählst: prüfe die Nummer beim Absender. Ein Link von cypruspost.post funktioniert, sofern er die Nummer enthält.

Bei Sendungen aus dem Ausland fragt Peek nach Möglichkeit zuerst die ausgebende Post – etwa [ELTA](carrier:elta) für `…GR` – oder Tracking-Dienste. Der Tracker von Cyprus Post nimmt diese Nummern ebenfalls an: wähle „Cyprus Post“ über „Ändern“. Peek probiert Cyprus Post auch selbstständig aus, wenn die ausländische Post Zypern als Ziel nennt oder du die Nummer ohne Zielangabe aus Zypern hinzufügst, und wechselt, sobald Cyprus Post mindestens ebenso aktuelle Scans hat. Peek fragt Cyprus Post nur für 13-stellige Nummern an. Unsicher beim Paketdienst? Füge die Nummer trotzdem ein: Peek unterstützt über 4.500 Paketdienste.

Peek zeigt die englischen Statusmeldungen von Cyprus Post im Wortlaut, die Filiale oder Einrichtung samt Land auf der Karte, die Zustellzeit und die Poststelle, an der die Sendung wartet oder abgeholt wurde. Zypriotische Scans behalten ihre genaue Uhrzeit, ausländische werden nach der Ortszeit des jeweiligen Landes gelesen (bei Ländern mit mehreren Zeitzonen ermittelt Peek die Zone aus dem Ortsnamen, was teils abweichen kann); alle Zeiten erscheinen in deiner Zeitzone („Uhrzeiten in deiner Zeitzone:“). Voraussichtliches Lieferdatum und Gewicht gibt es nicht, und Extra Information, nächste Stelle, Postleitzahlen sowie Unterschriften lässt Peek weg – den Grund einer Nichtzustellung siehst du auf cypruspost.post.

Peek fragt Cyprus Post direkt bis zu alle 10 Minuten ab – oder alle 2 Minuten ab 6:00 Uhr nach der Uhr des letzten Scans (zypriotische Zeit in Zypern) an dem Tag, an dem die Sendung in Zustellung geht, solange sie in Peek geöffnet ist oder am Liefertag mit aktiven Mitteilungen. Nach zwei Tagen ohne Neuigkeiten (nach 12 Stunden, solange noch „Etikett erstellt“ oder „Beim Paketdienst eingegangen“ steht) prüft Peek stündlich. Für Mitteilungen wählst du „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“. Nach vier Tagen ohne Scan bei einer Sendung auf „Etikett erstellt“, „Beim Paketdienst eingegangen“ oder „Unterwegs“ zeigt Peek „Keine Aktualisierung seit …“, erklärt „Auf langen Flügen, beim Zoll oder übers Wochenende wird es oft still um ein Paket. Peek fragt weiter bei Cyprus Post nach.“ und bietet „Hol dir Hilfe bei Cyprus Post“.

## Häufige Fragen zu Cyprus Post

### Ist Cyprus Post das Department of Postal Services?

Ja. Der Weltpostverein führt das Department of Postal Services (Cyprus Post) als benannten Postbetreiber Zyperns, den staatlichen Dienst für Postleistungen, und die Regulierungsbehörde OCECPR nennt Cyprus Post als Universaldienstleister.

### Was bedeutet CY am Ende der Nummer?

Dass die Nummer unter zypriotischer Hoheit ausgegeben wurde. Im S10-Standard des Weltpostvereins bezeichnen die letzten beiden Buchstaben das Land, unter dessen Hoheit der Code vergeben wurde, und zeigen daher nicht zwingend den Aufgabeort an. Peek fragt bei solchen Nummern Cyprus Post ab.

### Kann ich eine Sendung mit U-Nummer verfolgen?

Nicht als verfolgte Sendung. Der S10-Standard vergibt U-Nummern an Briefsendungen mit Waren ohne Kundenverfolgung: die Nummer dient der Sichtbarkeit in der Lieferkette, etwa für elektronische Vorabdaten. Peek fragt bei einer U-Nummer auf `CY` dennoch Cyprus Post ab.

### Wie beschwere ich mich über Cyprus Post?

Schreibe zuerst an Cyprus Post. Erfolgt innerhalb von 15 Tagen keine Antwort oder bist du nicht zufrieden, kannst du dich an die Postregulierungsbehörde wenden, das Office of the Commissioner for Electronic Communications and Postal Regulation (OCECPR). Das geht online; die Website ist auf Griechisch, bietet aber ein Beschwerdeformular auf Englisch.

:::sources
- [Universal Postal Union: S10, identification of postal items](https://www.upu.int/UPU/media/upu/files/postalSolutions/programmesAndServices/standards/S10-12.pdf) – 13 Zeichen, Anfangsbuchstaben R, C, E, L und U, Prüfziffer, Code des Ausgabelandes
- [Universal Postal Union: member countries, Cyprus](https://www.upu.int/en/Universal-Postal-Union/About-UPU/Member-Countries?csid=-1&cid=86) – Cyprus Post, benannter Betreiber
- [Universal Postal Union: list of designated operators](https://www.upu.int/getmedia/0ff98b1d-9dd4-46e7-bdd1-dda1b654308f/SettlementCircularsEn.pdf) – Department of Postal Services (Cyprus Post)
- [National Open Data Portal: list of post offices](https://www.data.gov.cy/el/dataset/katalogos-kai-theseis-tahydromikon-grafeion) – Department of Postal Services, staatlicher Postdienst (auf Griechisch)
- [OCECPR: universal postal service](https://ocecpr.ee.cy/katanalotes/katholiki-ypiresia/taxidromeia) – Cyprus Post als Universaldienstleister (auf Griechisch)
- [OCECPR: complaints](https://ocecpr.ee.cy/katanalotes/parapona) – erst schriftlich beim Anbieter, 15 Tage, Online-Beschwerde, englisches Formular (auf Griechisch)
- [Consumer Protection Service: online purchases](https://consumer.gov.cy/en/consumer-rights/66/59/?ctype=ar) – Lieferung binnen 30 Tagen, Nachfrist, Vertragsrücktritt (auf Griechisch)
- [Consumer Protection Service: contact us](https://consumer.gov.cy/en/contact-us) – kostenlose Verbraucher-Hotline 1429 (auf Griechisch)
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 3-€-Zoll pro Artikel seit 1. Juli 2026, 2-€-Unions-Bearbeitungsgebühr seit 1. November 2026
- [European Commission: guidance on the EUR 3 customs duty and the Union handling fee](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – Definition eines Artikels, Postsendungen, Gebühr unabhängig vom Wert, Zahlungspflichtiger, Mehrwertsteuer auf die Gebühr
- [Department of Customs and Excise: About us](https://www.mof.gov.cy/mof/customs/customs.nsf/All/289500205CFF33DFC22572AC003A2B02?OpenDocument) – Erhebung von Einfuhrzoll und Mehrwertsteuer
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – von Peek gelesene Daten: englischer Cyprus-Post-Tracker, Spalten und Ereignisnamen
:::
