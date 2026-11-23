---
title: CouriersPlease Sendungsverfolgung: Nummern & Hilfe
description: CouriersPlease-Paket in Australien verfolgen: Sendungsnummer, Statusmeldungen, HUBBED-Abholstellen, Neuzustellung, Delivery Choices und Reklamationen.
slug: couriersplease-sendungsverfolgung
published: 2026-11-23
updated: 2026-11-23
---

CouriersPlease (Couriers Please Pty Limited, Teil der FMH Group) ist ein australischer Paketdienst, der über lokale Franchise-Partner in australischen Großstädten und Regionalzentren zustellt. Um ein Paket zu verfolgen, gib die Sendungsnummer auf der [CouriersPlease-Trackingseite](https://www.couriersplease.com.au/tools-track) ein — dort lassen sich bis zu 10 Nummern durch Kommas getrennt abfragen — oder füge sie oben in das Suchfeld ein. Die Nummer bekommst du vom Online-Shop oder Absender, der CouriersPlease beauftragt hat.

## CouriersPlease Sendungsnummer: Wie sieht sie aus?

CouriersPlease nennt sie *Consignment Number* oder Trackingnummer. Laut der Seite für Neuzustellungen kann sie mit `CP` beginnen und bis zu 20 Zeichen lang sein. Für CouriersPlease akzeptiert Peek 8 bis 30 Buchstaben und Ziffern mit mindestens einer Ziffer — darunter Sendungsnummern, Stücketiketten, Prepaid-Coupons und SSCC-Codes einzelner Absender. Nicht jede CouriersPlease-Nummer beginnt also mit `CP`.

| Nummer (erfunden) | Was sie bedeutet |
| --- | --- |
| `CPABC4829173640` | Typische CouriersPlease-Sendungsnummer: `CP`, Buchstaben und danach Ziffern. |
| `CPA48291736405` | Ebenfalls ein CouriersPlease-Format mit `CP`, etwas kürzer. |
| `CP482917367AU` | Kein CouriersPlease: Zwei Buchstaben, neun Ziffern und `AU` sind eine internationale Postnummer, wie sie [Australia Post](carrier:australia-post) verwendet. |

**Wo du die Nummer findest.** Schau zuerst in die Bestellbestätigung des Shops. E-Mails direkt von CouriersPlease kommen von `noreply@couriersplease.com.au` (nur wenn der Absender deine E-Mail-Adresse übermittelt hat), SMS unter dem Absendernamen `Courierspls`. Eine hinterlassene Benachrichtigungskarte trägt eine *Redelivery Card Number*, die auf der Seite für Neuzustellungen ebenfalls akzeptiert wird. Weitere Tipps stehen im Ratgeber [Sendungsnummer finden](guide:find-tracking-number).

**Nummer ungültig oder noch keine Daten?** Prüfe Tippfehler und kläre mit dem Shop, ob wirklich per CouriersPlease versendet wurde. Solange noch keine Scan-Ereignisse vorliegen, meldet die Trackingseite die Nummer als ungültig oder unbekannt.

## CouriersPlease Sendungsstatus erklärt

Im [Empfängerleitfaden](https://www.couriersplease.com.au/faqs) nennt CouriersPlease fünf zentrale Meilensteine: Meldung des abzuholenden Pakets, Abholung beim Absender, Ankunft im Depot, in Zustellung sowie zugestellt oder an eine Abholstelle gebracht. Im Tracker erscheinen die Statusmeldungen auf Englisch. Hier die wichtigsten Meldungen, die CouriersPlease und [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) auswerten, in der üblichen Reihenfolge:

| CouriersPlease-Status | Was das für dich bedeutet |
| --- | --- |
| `Shipment information received` | Der Absender hat die Sendung angelegt. CouriersPlease hat das Paket möglicherweise noch nicht übernommen. |
| `Your parcel has been picked up` | CouriersPlease hat das Paket beim Absender abgeholt. |
| `Pickup attempt was unsuccessful. Awaiting rebook confirmation by sender` | Die Abholung beim Absender ist gescheitert; der Verkäufer muss neu buchen. |
| `Arrived at facility for processing` | Das Paket ist in einem CouriersPlease-Verarbeitungszentrum eingetroffen. |
| `Parcel requires further processing` | Weitere Bearbeitung im Zentrum; das Paket befindet sich regulär im Netzwerk. |
| `Processed at delivery centre` | Im Zustellzentrum bearbeitet. |
| `In transit to delivery centre` | Unterwegs zu einem Zustellzentrum (oft mit den beiden Städten in Klammern). |
| `Arrived at delivery centre` | Im Zustellzentrum eingetroffen; du musst nichts tun. |
| `In transit to regional delivery centre` | An einen regionalen Zustellpartner oder auf eine Regionalstrecke übergeben. |
| `Onboard for delivery` | Im Fahrzeug des Fahrers bzw. Franchise-Partners für die Zustellung. |
| `Delivery rescheduled for next business day` | Unzugestellt zurück im Depot und für den nächsten Werktag neu geplant. |
| `Delivery attempted – Unsafe to leave – Sent to a collection point` | Niemand war da und kein sicherer Ablageort vorhanden; Weiterleitung an eine Abholstelle. |
| `Delivery attempted – Signature required – Redirected to a collection point` | Eine Unterschrift war nötig, aber niemand war anzutreffen; Weiterleitung an eine Abholstelle. |
| `Unable to deliver` | Zustellung nicht möglich; der Vorgang muss vom Support geprüft werden. |
| `Processing for collection at a nearby collection point` | An der Abholstelle angekommen, aber noch nicht abholbereit. |
| `Pickup at Collection Point – Ready for collection` | Mit deinem Abhol-PIN an der Abholstelle abholbereit. |
| `Delivered with Authority to Leave (ATL)` | Ohne Unterschrift an einem sicheren Ort an deiner Adresse abgelegt. |
| `Delivered` | An der Lieferadresse zugestellt. |
| `Parcel has been collected from` | An der dahinter genannten Abholstelle abgeholt. |
| `Collection period expired – Return to sender in progress` | Lagerfrist abgelaufen; das Paket geht an den Absender zurück. |

`Shipment information received` sorgt am häufigsten für Rückfragen: Die Auftragsdaten liegen vor, das Paket steht aber oft noch beim Händler. Bleibt der Status stehen, bitte den Shop um Bestätigung der Übergabe.

Hinweise wie `Delivery rescheduled` oder Einträge mit `Delivery instructions –` (neues Datum, Abholstelle, Nachbar, Abstellgenehmigung oder neue Adresse) dokumentieren lediglich eine Umverfügung oder einen neuen Termin und bewegen das Paket selbst noch nicht.

## Zustellung, HUBBED-Abholstellen, Neuzustellung und Delivery Choices

**Geschätzte Laufzeiten.** Laut dem [Empfängerleitfaden](https://www.couriersplease.com.au/faqs) dauern Sendungen zwischen Großstädten 1 bis 3 Werktage, quer durch Australien (etwa Ost nach West) einige Tage länger, in ländlichen oder abgelegenen Regionen noch länger und in Spitzenzeiten wie Weihnachten rund eine Woche zusätzlich. Alle Angaben sind Schätzwerte einschließlich Auftragsbearbeitung, Depotdurchlauf sowie eventueller Neuzustellung oder Abholung; Wetter, Verkehr oder Störungen können die Laufzeit verlängern.

**Wenn niemand zu Hause ist (Authority to Leave).** Bei der Zustellung klopft oder klingelt der Fahrer, sofern der Absender keine kontaktlose Zustellung bestellt hat. Nach Klausel 5.3 der [Beförderungsbedingungen](https://www.couriersplease.com.au/media/Terms_and_Conditions_of_Carriage_12_October_2023_ed4c2f9d36.pdf) und der Seite [Delivery Choices](https://www.couriersplease.com.au/delivery-choices) gelten Sendungen standardmäßig mit Abstellgenehmigung (*Authority to Leave*, ATL), sofern nichts anderes vereinbart ist: Trifft der Fahrer jemanden an, trägt er `SIG` und den Namen der annehmenden Person ein; ist niemand da, sucht er einen sicheren Ablageort. Gibt es keinen sicheren Ort (etwa bei Mehrfamilienhäusern oder stark frequentierten Eingängen) oder verlangt der Absender eine Unterschrift, geht das Paket an eine nahe Abholstelle — außer bei Sendungen über 10 kg und/oder 100 cm kubischem Maß (etwa Handgepäckgröße), für die stattdessen eine Neuzustellung gebucht werden muss. In ländlichen Regionen werden Pakete laut CouriersPlease immer an einem sicheren Ort abgestellt (ATL).

**HUBBED-Abholstellen.** Gemeinsam mit HUBBED bietet CouriersPlease über 3.000 Abholstellen mit langen Öffnungszeiten in ganz Australien. Über die [Abholstellen-Suche](https://www.couriersplease.com.au/resources/find-a-collection-point) findest du Standorte per Vorort oder Postleitzahl und kannst nach *Drop Off*, *Redirections* oder *Failed deliveries* filtern. Hat der Absender deine E-Mail-Adresse hinterlegt, schickt CouriersPlease innerhalb von 24 Stunden eine Nachricht; andernfalls hinterlässt der Fahrer eine Benachrichtigungskarte. Laut Klausel 5.4 der Beförderungsbedingungen gilt die Sendung mit dem Versand der Abhol-PIN als zugestellt: Nach dieser PIN-Mitteilung hast du **7 Tage** Zeit zur Abholung; danach lagert das Paket weitere **5 Tage** im nächstgelegenen Depot, bevor es auf Kosten des Kunden zurückgesendet wird. Fehlt dir die PIN, kannst du sie über die [HUBBED-PIN-Seite](https://hubbed.com/getpin/) anfordern (für dich selbst, deine Firma oder eine andere Person).

**Neuzustellung.** Eine erneute Zustellung buchst du auf der [Neuzustellungsseite](https://www.couriersplease.com.au/book/redelivery) mit deiner Sendungsnummer oder der Nummer der Benachrichtigungskarte. Im Empfängerleitfaden heißt es zudem, dass für ein an eine Abholstelle gebrachtes Paket innerhalb von 6 Tagen eine Neuzustellung angefordert werden kann. Das Hilfecenter von CouriersPlease sagt dagegen, dass ein bereits an eine Abholstelle umgeleitetes Paket nicht erneut an deine Adresse zugestellt werden kann und dort abgeholt werden muss; frag im Zweifel beim Support nach, bevor du buchst.

**Delivery Choices.** Übermittelt der Absender bei der Buchung sowohl deine E-Mail-Adresse als auch deine Mobilnummer, sendet CouriersPlease einen sicheren Link zu fünf Optionen auf [Delivery Choices](https://www.couriersplease.com.au/delivery-choices):

- Unterschrift anfordern (*Signature required*: bei Abwesenheit geht das Paket an eine Abholstelle oder ins Depot);
- an einer nahegelegenen Abholstelle abholen (*Collect nearby*);
- bei einem Nachbarn innerhalb von drei Häusern links oder rechts abgeben lassen (*Leave with a neighbour*);
- die Lieferadresse ändern (*Change the address*);
- einen anderen Zustelltag innerhalb von drei Tagen nach dem ursprünglichen Termin wählen (*Choose another day*).

## Verspätetes, fehlendes oder beschädigtes CouriersPlease-Paket

**Verspätung oder fehlende Updates.** CouriersPlease erklärt, dass die meisten verspäteten Pakete innerhalb von 24 bis 48 Geschäftsstunden nach dem ursprünglichen Schätzwert eintreffen. Prüfe zunächst die Servicehinweise auf couriersplease.com.au; stimmt etwas nicht, wende dich über das Chat-Symbol des *Virtual Assistant* (unten rechts auf der Website, Auswahl *Assistance with a Delivery*) an CouriersPlease und stelle jede Anfrage nur einmal, da Mehrfachanfragen die Bearbeitung verzögern. Weitere Hinweise findest du unter [Sendungsverfolgung aktualisiert sich nicht](guide:tracking-not-updating).

**Als zugestellt markiert, aber nicht da.** Kontrolliere geschützte Ablageorte rund um den Eingang, Mitbewohner, Nachbarn und eventuelle Poststellen, und melde den Fall danach beim Händler und beim Virtual Assistant. Siehe auch [als zugestellt markiert, aber nicht erhalten](guide:delivered-not-received) und [verlorenes Paket](guide:lost-parcel).

**Schaden, Verlust und Parcel Protect.** Für Inlandszustellungen in Australien enthält [Parcel Protect Domestic Cover](https://www.couriersplease.com.au/parcel-protect-domestic-cover) eine kostenlose Grundabsicherung bis 1.000 AUD, ausgenommen sind jedoch die ersten 100 AUD Schaden, Sendungen mit *Authority to Leave* (ATL), mangelhafte Verpackung sowie bestimmte Warengruppen (unter anderem Wertgegenstände, Bargeld, Schmuck, Geschenkkarten, Bilder mit Glas, Spiegel, Blumen, Pflanzen, verderbliche Waren, persönliche Gegenstände und Elektronik sowie Gefahrgut). Verlust oder Schäden müssen innerhalb von **14 Werktagen** nach der Zustellung bzw. dem erwarteten Lieferdatum (je nachdem, was früher liegt) schriftlich gemeldet werden. Wurde der Versand über einen externen Buchungsvermittler gebucht, muss die Reklamation über diesen Vermittler laufen. Als Empfänger wendest du dich zuerst an den Shop, da dieser Vertragspartner ist. Greift Parcel Protect nicht, begrenzt Klausel 7.3 der Beförderungsbedingungen die Standardhaftung auf erneute Leistung, deren Kosten oder 100 AUD (vorbehaltlich der gesetzlichen Verbraucherrechte nach australischem Recht). Mehr dazu unter [beschädigtes Paket](guide:damaged-parcel).

## CouriersPlease kontaktieren und Phishing erkennen

- **Virtual Assistant:** Über das Chat-Symbol unten rechts auf [couriersplease.com.au](https://www.couriersplease.com.au/faqs) *Assistance with a Delivery* wählen (oder `feedback` eingeben). Jede Anfrage nur einmal absenden.
- **Help Centre:** Direkt über den Menüpunkt *Help centre* auf couriersplease.com.au erreichbar.
- **Firmensitz:** Couriers Please Pty Limited (ABN 76 006 144 734), Rosehill Industrial Estate Building C South, Level 1, 5–7 Shirley Street, Rosehill, NSW 2142, Australia.

> Laut der [Scam-Protection-Seite](https://www.couriersplease.com.au/scam-protection) stammen echte E-Mails von `noreply@couriersplease.com.au` und echte SMS vom Absendernamen `Courierspls`. CouriersPlease fordert niemals zur Änderung von Bankdaten, zur Zahlung einer Freigabegebühr, zur Eingabe von Karten- oder Zugangsdaten oder zum Antworten mit `Y` auf (Vorsicht bei gefälschten Domains wie `couriersplease.addliik.com` oder `no-reply@wufoo.com`). Im Zweifel die Nachricht löschen, direkt auf couriersplease.com.au nachsehen und Betrugsversuche bei CouriersPlease sowie bei Scamwatch melden.

## CouriersPlease-Pakete mit Peek verfolgen

[Peek](/) erkennt CouriersPlease sofort bei Nummern nach dem `CP`-Muster (wie `CPABC4829173640` oder `CPA48291736405`) sowie bei Tracking-Links von couriersplease.com.au/tools-track mit `no=` oder `trackingCode=`. Konto, Postleitzahl oder Telefonnummer sind nicht nötig. Beginnt deine CouriersPlease-Nummer nicht mit `CP`, füge den Tracking-Link ein oder wähle CouriersPlease beim Hinzufügen über „Ändern“ oder später über „Paketdienst ändern“.

Peek ruft den offiziellen CouriersPlease-Tracker ab und zeigt jeden Scan im englischen Wortlaut des Paketdienstes, mit der Uhrzeit, die CouriersPlease angibt (Sydney-Zeit, auch außerhalb von New South Wales), dem australischen Bundesstaat und der voraussichtlichen Lieferung (*Delivery ETA*), sobald mehr als nur die elektronische Ankündigung vorliegt (und bis zur Zustellung oder Rücksendung). HTML-Tags und angehängte Support-Links blendet Peek aus; Abholstellen-Adressen, HUBBED-PINs oder Ablagefotos werden nicht angezeigt.

Peek fragt direkt bei CouriersPlease bis zu alle 10 Minuten nach, solange du das Paket geöffnet hast oder am Zustelltag mit aktivierten Benachrichtigungen, und alle 2 Minuten ab 6 Uhr Sydney-Zeit an dem Tag, an dem CouriersPlease die Sendung als in Zustellung scannt (`Onboard for delivery`), solange der CouriersPlease-Tracker antwortet; sonst stündlich. Diesen Tag rechnet Peek nach Sydney-Zeit, der Uhr der CouriersPlease-Scans: In Perth beginnen diese Abfragen also um 3 oder 4 Uhr und enden um 21 oder 22 Uhr. Nach 12 Stunden im Status „Etikett erstellt“ oder „Beim Paketdienst eingegangen“ oder nach zwei Tagen ohne neuen Scan wechselt Peek auch bei geöffneter Ansicht auf stündliche Abfragen, und nach sieben Tagen weiterhin bei „Etikett erstellt“ auf alle 6 Stunden. Über zwischengeschaltete Tracking-Dienste prüft Peek stündlich, nach einem Tag ohne Neuigkeiten alle 3 Stunden und am Zustelltag bis zu alle 15 Minuten.

Bleibt eine Sendung bei „Etikett erstellt“, „Beim Paketdienst eingegangen“ oder „Unterwegs“ vier Tage lang ohne neuen Scan, markiert Peek sie mit dem Tag dieses letzten Scans (zum Beispiel „Keine Aktualisierung seit Mo 12. Okt“), zeigt „Auf langen Flügen, beim Zoll oder übers Wochenende wird es oft still um ein Paket. Peek fragt weiter bei CouriersPlease nach.“ und zeigt den Link „Hol dir Hilfe bei CouriersPlease“ zum Hilfecenter von CouriersPlease. Nach mehr als drei Wochen ohne Update erscheint: „Seit über drei Wochen nichts Neues. Ist das Paket noch nicht angekommen, frag beim Verkäufer nach. Peek fragt weiter bei CouriersPlease nach.“

Für Benachrichtigungen kannst du zwischen „Jeden Scan“, „Wichtige Schritte“ und „Nur die Zustellung“ wählen. Neuzustellungen buchen, eine HUBBED-PIN anfordern oder eine Parcel-Protect-Reklamation einreichen kann Peek nicht — dafür wende dich an CouriersPlease oder den Shop.

## Häufige Fragen zur CouriersPlease Sendungsverfolgung

### Wie lange dauert die Lieferung mit CouriersPlease?

Laut Empfängerleitfaden beträgt die geschätzte Laufzeit 1 bis 3 Werktage zwischen Großstädten, einige Tage mehr quer durch Australien, länger für regionale oder abgelegene Gebiete und rund eine Woche zusätzlich zu Spitzenzeiten wie Weihnachten.

### Warum aktualisiert sich meine CouriersPlease-Sendungsverfolgung nicht?

Auf Fernstrecken zwischen Bundesstaaten, bei regionalen Partnern oder während der Depotbearbeitung können mehrere Tage zwischen zwei Scans liegen. Die meisten verspäteten Pakete treffen laut CouriersPlease innerhalb von 24 bis 48 Geschäftsstunden nach der ursprünglichen Schätzung ein; danach hilft der Virtual Assistant auf couriersplease.com.au.

### Wie funktionieren HUBBED-Abholstellen und die Abhol-PIN?

Kann ein Paket bis 10 kg und 100 cm kubischem Maß nicht sicher abgestellt oder gegen Unterschrift übergeben werden, bringt CouriersPlease es zu einer der über 3.000 HUBBED-Abholstellen. Nach den Beförderungsbedingungen hast du ab Versand der PIN 7 Tage Zeit zur Abholung, danach bleibt das Paket noch 5 Tage im Depot, bevor es zurückgeht. Eine fehlende PIN kannst du unter hubbed.com/getpin/ anfordern.

### Was tun bei Schäden oder einer Zustellung mit Authority to Leave?

Informiere sofort den verkaufenden Shop. Schriftliche Parcel-Protect-Ansprüche müssen binnen 14 Werktagen gemeldet werden (bis 1.000 AUD abzüglich der ersten 100 AUD), gelten aber nicht für Sendungen mit *Authority to Leave* (ATL).

:::sources
- [CouriersPlease: Track A Parcel Delivery](https://www.couriersplease.com.au/tools-track) – Abfrage von bis zu 10 Sendungsnummern durch Kommas getrennt
- [CouriersPlease: Guide to receiving](https://www.couriersplease.com.au/faqs) – fünf Meilensteine, geschätzte Laufzeiten, 10 kg / 100 cm Grenze, 6 Tage für Neuzustellung, 24–48 Geschäftsstunden bei Verspätung, Virtual Assistant
- [CouriersPlease: Book A Parcel Redelivery](https://www.couriersplease.com.au/book/redelivery) – Sendungs- oder Benachrichtigungskartennummer, Präfix CP, bis zu 20 Zeichen
- [CouriersPlease: How do I arrange a redelivery?](https://support.couriersplease.com.au/hc/en-au/articles/31312747333785-How-do-I-arrange-a-redelivery) – keine Neuzustellung für ein bereits an eine Abholstelle umgeleitetes Paket
- [CouriersPlease: Find a Collection Point](https://www.couriersplease.com.au/resources/find-a-collection-point) – Suche nach Vorort/Postleitzahl und Abholstellen-Typen
- [CouriersPlease: Delivery Choices](https://www.couriersplease.com.au/delivery-choices) – Standard-ATL, fünf Zustelloptionen, regionale ATL-Regel
- [CouriersPlease: Parcel Protect Domestic Cover](https://www.couriersplease.com.au/parcel-protect-domestic-cover) – 1.000 AUD Höchstgrenze, Ausschluss der ersten 100 AUD und von ATL, 14-Werktage-Frist, Drittvermittler-Regel
- [CouriersPlease: Terms and Conditions of Carriage](https://www.couriersplease.com.au/media/Terms_and_Conditions_of_Carriage_12_October_2023_ed4c2f9d36.pdf) – Klauseln 5.3 (ATL / SIG), 5.4 (7 Tage nach PIN + 5 Tage Depotlagerung), 7.3 (Haftungsgrenze) und Unternehmensanschrift
- [CouriersPlease: Scam protection](https://www.couriersplease.com.au/scam-protection) – offizielle Absender noreply@couriersplease.com.au und Courierspls, Betrugswarnungen, Scamwatch
- [CouriersPlease: Our Story](https://www.couriersplease.com.au/our-story) – FMH Group, Franchise-Partner, über 3.000 HUBBED-Abholstellen
- [HUBBED: Get PIN](https://hubbed.com/getpin/) – Anforderung der Abhol-PIN
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Erkennung von CouriersPlease-Nummern und -Links, Statustabelle und von Peek gelesene Felder
:::
