---
title: GEODIS Polen Sendungsverfolgung: PL-Nummer & Zustellung
description: GEODIS Polen (früher Pekaes) verfolgen: Aufbau der PL-Sendungsnummer, Bedeutung der englischen Status, Zustellung, Abholung im Terminal und Kontakt.
slug: geodis-polen-sendungsverfolgung
published: 2026-11-18
updated: 2026-11-20
---

GEODIS Poland ist das polnische Paletten- und Paketnetz des französischen Transportkonzerns GEODIS, der zur französischen Staatsbahn SNCF gehört. GEODIS übernahm PEKAES im Jahr 2021; heute wird das Netz von der GEODIS Road Network sp. z o.o. mit Sitz in Błonie (Polen) betrieben. Wie du deine Sendung verfolgst, hängt davon ab, was dir der Absender geschickt hat:

- **Eine Nummer aus `PL` und 16 Ziffern oder ein Link zur Strefa Klienta:** Das ist eine Paket- oder Palettensendung von GEODIS Poland. Füge die Nummer oder den Link oben ins Suchfeld ein oder suche direkt in der Sendungsverfolgung von GEODIS Poland, [Strefa Klienta](https://strefaklienta.pekaes.geodis.pl/en/).
- **Eine andere GEODIS-Nummer:** Peek liest außerdem das französische Empfängerportal von [GEODIS](carrier:geodis), dessen Sendungsnummern mit `1G` beginnen (siehe unten). Die Suche auf `geodis.com` findet Luft-, See- und Letzte-Meile-Sendungen und verweist für den Straßentransport auf die Strefa Klienta.
- **Noch keine Nummer:** Den Code erhältst du vom Onlineshop oder Absender, meist in der Bestellbestätigung oder Versandbenachrichtigung per E-Mail.

## GEODIS Polen Sendungsnummer: Aufbau und wo du sie findest

Die Transportlösungen von GEODIS richten sich ausschließlich an Geschäftskunden: Die Sendungsnummer liegt daher beim Unternehmen oder Onlineshop, das dir die Ware schickt. Steht sie in keiner E-Mail, frag beim Verkäufer nach oder lies unter [Sendungsnummer finden](guide:find-tracking-number) weiter.

| Nummer (erfunden) | Bedeutung |
| --- | --- |
| `PL4829105736491827` | `PL` gefolgt von 16 Ziffern: eine Sendungsnummer von GEODIS Poland, in der Strefa Klienta `Shipment No.` genannt. |
| `1G4829105736` | `1G` gefolgt von 10 Großbuchstaben oder Ziffern: eine Nummer von [GEODIS](carrier:geodis) in Frankreich, verfolgbar im französischen Empfängerbereich und nicht in der Strefa Klienta. |

Gib auf der englischen Seite der Strefa Klienta die Nummer in das Feld `Enter Shipment No.` ein und klicke auf `Search` (auf der polnischen Seite: `Wpisz numer przesyłki` und `Szukaj`). Antwortet das Portal mit `Shipment with such number does not exist`, kennt es diese Nummer nicht – wende dich dann, wie GEODIS bei nicht funktionierenden Nummern rät, an den Absender oder Onlineshop. Erscheint die polnische Meldung `Proszę wprowadzić właściwy numer przesyłki.`, bittet die Seite um eine gültige Sendungsnummer: Prüfe, ob sich ein Tippfehler eingeschlichen hat.

## Was die Statusmeldungen von GEODIS Polen bedeuten

Die Strefa Klienta steht auf Polnisch und Englisch bereit. Auf der englischen Seite zeigt eine erfolgreiche Suche zuerst eine Übersicht (`Shipment No.`, `Status`, `Delivery Country`, `Delivery Location` mit Postleitzahl und Ort); über den Link `Details` öffnen sich die einzelnen Scans samt Uhrzeit und Terminalort. Die englischen Meldungen in der typischen Reihenfolge einer Beförderung:

| GEODIS-Meldung | Was das für dich heißt |
| --- | --- |
| `Received from the sender` | GEODIS hat die Sendung beim Absender übernommen (`Collected (SMS)` bestätigt die Abholung per SMS, `Failed collection (SMS)` meldet eine fehlgeschlagene Abholung). |
| `Inbound Scan From Driver to Terminal` | Ein Fahrer hat die Sendung in ein Terminal gebracht: nach der Abholung ins Versandterminal oder – falls davor `Outbound for Delivery` stand – am Ende einer Tour zurück ins Zustellterminal. Fehlt davor `Consignee Absent` oder `Failed delivery (SMS)`, fand auf dieser Tour kein Zustellversuch statt und die Sendung wartet auf die nächste Tour. |
| `Outbound Scan from (Collection) Terminal to Line-haul` | Die Sendung hat das Versandterminal per Fernverkehrs-Lkw Richtung Zustellterminal verlassen. |
| `Inbound Scan from Line-haul to (Delivery) Terminal` | Sie hat das Terminal erreicht, das deine Adresse beliefert. |
| `Awaiting Delivery` | Sie wartet im Zustellterminal auf die Einteilung in eine Zustelltour. |
| `Outbound for Delivery` | Die Sendung ist im Zustellfahrzeug unterwegs zu dir. |
| `Consignee Absent` | Der Fahrer hat dich bei der Zustellung nicht angetroffen. |
| `Failed delivery (SMS)` | Ein Zustellversuch ist fehlgeschlagen, bestätigt per SMS. |
| `Awaiting designation of new delivery date` | GEODIS wartet darauf, dass ein neuer Zustelltermin festgelegt wird. |
| `Awaiting Decision` | GEODIS wartet auf Weisungen des Auftraggebers oder Absenders. |
| `Delivery Refusal` | Der Empfänger hat die Annahme verweigert. |
| `Request for Return` | Eine Rücksendeanweisung liegt vor: Die Sendung ist auf dem Rückweg zum Absender, aber noch nicht dort eingetroffen. |
| `Delivered` | Zugestellt (`Delivered (SMS)` bedeutet dasselbe mit SMS-Bestätigung). |
| `Claim Received` | Eine Reklamation wurde erfasst – ein reiner Verwaltungseintrag ohne physische Bewegung der Sendung. |

Einige Meldungen weisen auf eine Verzögerung oder Störung hin: `Stopped at Hub` und `Stopped at Collection Terminal` (Sendung in einem Umschlaghub oder im Versandterminal angehalten), `Not Dispatched` (geplanten Lkw oder Tour verpasst, aber weiter im Netz) sowie `Failure` (Störung oder Panne). Eine Zeile mit `Pseudoscan` (`Pseudoscan – delete all previous 100, 400, 500 scans`) ist ein Korrektureintrag, der frühere Zustell-, Rücksende- und Reklamationsscans aufhebt. Mehr zu den Phasen findest du unter [Sendungsstatus erklärt](guide:tracking-statuses).

## Zustellung durch GEODIS in Polen: Privatadresse, Avisierung und Terminalabholung

Die Allgemeinen Geschäftsbedingungen (*Ogólny Regulamin Świadczenia Usług*) von GEODIS Road Network, gültig seit dem 4. November 2024, regeln das Verhältnis zwischen GEODIS und dem Auftraggeber (*Zleceniodawca*, also dem Unternehmen, das den Versand bucht, meist dem Shop) für nationale Transporte in **Polen** sowie internationale Straßentransporte:

- **Nicht angetroffen.** Bei Inlandsdiensten in **Polen** (`PAC DOM`, `LTL DOM`, `LTL DOM NST`, `PTL DOM`) hinterlässt der Fahrer bei Abwesenheit eine Benachrichtigung über den Zustellversuch; nach einem zweiten erfolglosen Versuch aus Gründen auf Empfängerseite geht die Sendung auf Kosten des Auftraggebers an den Absender zurück (ebenso bei Annahmeverweigerung oder falscher Adresse). Bei internationalen Straßensendungen (`PAC INT`, `LTL INT`, `PTL INT`) muss der Auftraggeber oder Absender nach einer erfolglosen Zustellung wegen Abwesenheit innerhalb von höchstens 2 Werktagen Weisungen erteilen, sonst sendet GEODIS die Ware auf Kosten des Auftraggebers zurück. Siehe [Zustellung verpasst](guide:missed-delivery) und [zurück an den Absender](guide:returned-to-sender).
- **Paletten an Privatadressen in Polen (`DDOP`).** Im polnischen nationalen Stückgutverkehr (`LTL DOM` und `LTL DOM NST`) erfolgt die Zustellung an Privatadressen ausschließlich werktags von Montag bis Freitag zwischen `8:00` und `16:00` Uhr. Der Auftraggeber muss deine Mobilnummer angeben und die Sendung mit `DDOP` kennzeichnen: GEODIS schickt Statusmeldungen und stimmt die Lieferung vorab telefonisch, per SMS oder per E-Mail ab – ein Anruf erfolgt spätestens am möglichen Zustelltag. Erreicht GEODIS dich innerhalb von 2 Werktagen nicht zur Terminabstimmung, kontaktiert es den Auftraggeber und schickt die Sendung an den Absender zurück.
- **Entladung und Inlands-Zusatzleistungen in Polen.** Bei `DDOP`-Sendungen in **Polen** lädt der Fahrer ebenerdig an der Lieferadresse ab – oder an der nächsten sicheren, befestigten Stelle, falls die Straßenverhältnisse ein sicheres Halten direkt vor Ort nicht zulassen. Das Hineintragen in die Wohnung (*Usługa Wniesienia*: national bei `LTL DOM` / `LTL DOM NST`, bis 300 kg Bruttogesamtgewicht, maximal 30 kg je Packstück und höchstens 30 Packstücke, maximal 150 m auf befestigtem Weg, ohne nutzbaren Aufzug bis ins zweite Geschoss, wobei sich die Lieferzeit um bis zu 3 Werktage verlängern kann), das Hineinfahren per Hubwagen (*Usługa Wwiezienia*: bis 1.200 kg brutto und 300 kg je Palette im Umkreis von 150 m bis zur ersten baulichen Barriere), Wunschtag (*Fix Day*) sowie Zustellung bis `10:00` Uhr (`D10`) oder `12:00` Uhr (`D12`), beide nur in ausgewählten polnischen Postleitzahlgebieten, sind gesonderte Inlandsoptionen, die der Auftraggeber buchen muss.
- **Selbstabholung im Terminal in Polen.** Beauftragt der Auftraggeber die Selbstabholung durch einen privaten Empfänger in einem GEODIS-Terminal, liegt die Sendung dort ab Eingang der Weisung 48 Stunden (gerechnet in Werktagen) bereit. Abgeholt werden kann sie Montag bis Freitag von `11:00` bis `16:00` Uhr; danach geht sie an den Absender zurück.
- **Nachnahme in Polen (*Usługa pobrania*).** Nur bei nationalen Diensten in **Polen** (`PAC DOM`, `LTL DOM`, `LTL DOM NST`) verfügbar: Vor Übergabe der Sendung können bis zu `10.000 PLN` (oder der Gegenwert in einer anderen Währung) pro Sendung bar oder per Zahlungskarte, BLIK oder einer anderen von GEODIS zugelassenen bargeldlosen Methode bezahlt werden.
- **Unterschrift und Prüfung.** Den Empfang bestätigst du per Unterschrift auf dem Mobilgerät des Fahrers oder auf den Frachtpapieren. Laut GEODIS-Bedingungen darf der Empfänger den Inhalt nicht vor der Empfangsbestätigung prüfen: Kontrolliere deshalb die Außenverpackung gründlich und vermerke etwaige Vorbehalte direkt bei der Übergabe auf den Frachtdokumenten.

## GEODIS-Sendung in Polen verspätet, beschädigt oder nicht angekommen

**Keine neuen Scans.** Bleibt der Status tagelang stehen, lies unter [Sendungsverfolgung aktualisiert sich nicht](guide:tracking-not-updating) nach und wende dich an den Shop.

**Verspätung nach polnischem Verbraucherrecht.** In **Polen** weist die Verbraucherschutzbehörde UOKiK (unter Verweis auf Art. 546 und 546¹ des polnischen Zivilgesetzbuchs) darauf hin, dass ein Verkäufer bei Verbraucherkäufen nach polnischem Recht die Ware unverzüglich und spätestens innerhalb von 30 Tagen ab Vertragsschluss übergeben muss, sofern nichts anderes vereinbart wurde. Nach Ablauf dieser Frist kann der Käufer eine Nachfrist setzen und bei weiterer Nichtlieferung vom Vertrag zurücktreten.

**Beschädigung oder Fehlmenge.** Bei äußerlich sichtbaren Schäden oder Teilverlusten ist direkt bei der Zustellung gemeinsam mit dem Fahrer ein Schadensprotokoll (*protokół szkodowy*) nach GEODIS-Muster aufzunehmen und von beiden Seiten leserlich zu unterschreiben. Äußerlich nicht erkennbare Schäden oder Fehlmengen müssen GEODIS vor Ablauf von 7 Tagen ab Empfang gemeldet werden; unabhängig vom Zeitpunkt der Meldung muss der Reklamierende nachweisen, dass der Schaden vor der Auslieferung entstanden ist. Bewahre die beschädigte Ware bis zum Abschluss des Verfahrens zur Besichtigung durch GEODIS auf und mache Fotos mit Datum und Uhrzeit. Das GEODIS-Reklamationsformular stellt klar, dass ein unterschriebenes Schadensprotokoll zwar Grundlage für die Prüfung ist, aber noch keine eingereichte Reklamation darstellt. Siehe auch [beschädigtes Paket](guide:damaged-parcel).

**Wer reklamiert.** Nach den Bedingungen von GEODIS Road Network sind Reklamationen schriftlich bei der Reklamationsabteilung (*Dział Reklamacji*) in der Zentrale in Błonie einzureichen – durch den Auftraggeber oder eine schriftlich bevollmächtigte Person bzw. nach Forderungsabtretung (*cesja*) –, und zwar unverzüglich, spätestens binnen 14 Tagen ab Empfang (bei Beschädigung oder Fehlmenge) bzw. binnen 21 Tagen ab dem geplanten Zustelltag (bei Verspätung oder Totalverlust). Bei einem Verbraucherkauf nach polnischem Recht trägt laut UOKiK (Art. 548 § 3 Zivilgesetzbuch) der Verkäufer die Gefahr des zufälligen Verlusts oder der Beschädigung bis zur Übergabe an den Käufer, sofern der Käufer eine vom Verkäufer angebotene Versandart gewählt hat – selbst wenn der Paketdienst den Schaden verursacht hat. Als Käufer reklamierst du daher beim Händler, der seinerseits Ansprüche gegen den Frachtführer geltend macht.

## Kontakt und Kundenservice von GEODIS Polen

- **Kundenservice in Polen (*Dział Obsługi Klienta*):** `+48 22 460 26 26`, angegeben auf der polnischen Seite der Strefa Klienta (ohne Nennung von Servicezeiten).
- **Schriftliche Reklamationen per Post:** `Dział Reklamacji, ul. Spedycyjna 1, 05-870 Błonie`, Polen. Die Formulare für Reklamation (`GRN Formularz zgloszenia reklamacji`) und Schadensprotokoll (`GRN Protokol szkody`) stehen in der Strefa Klienta zum Download bereit.
- **Standorte in Polen:** Die [polnische Kontaktseite von GEODIS](https://geodis.com/pl-pl/skontaktuj-sie-z-nami) führt 42 Standorte in Polen mit Adressen auf, darunter die Niederlassungen von GEODIS Road Network.
- **Online-Formular auf `geodis.com`:** Ausschließlich für Geschäftskunden bestimmt – Pflichtfelder sind unter anderem der Firmenname (`Firma *`) und die geschäftliche E-Mail-Adresse (`Adres e-mail służbowy *`).

## GEODIS Polen mit Peek verfolgen

Das Muster aus `PL` und 16 Ziffern gehört GEODIS nicht exklusiv (`PL` steht für Polen), sodass Peek GEODIS Poland allein anhand der Nummer nicht sicher zuordnen kann. Füge sie trotzdem ein: Peek unterstützt über 4.000 Paketdienste, fragt bei GEODIS Poland nach und zeigt „GEODIS Poland hat dieses Paket.“, sobald das Portal sie kennt. Ist die Nummer dort noch nicht gelistet, stehen „Wahrscheinlich GEODIS Poland“ und „Warten auf den ersten Scan von GEODIS Poland“, während Peek weiter prüft; kennt GEODIS sie auch nach einer Woche nicht, rät Peek, die Nummer beim Absender zu prüfen oder einen anderen Paketdienst zu wählen. Um den Dienst selbst festzulegen, tippe in der Paketdienst-Zeile auf „Ändern“ und wähle „GEODIS Poland“ unter „Passt zu dieser Nummer“ oder suche danach (auch `Pekaes` und `GEODIS Road Network` finden ihn; „GEODIS“ allein steht für GEODIS Frankreich). Bei einem bereits gespeicherten Paket nutzt du „Paketdienst ändern“. Bei einem Link zur Strefa Klienta mit `ShNumber=` übernimmt Peek GEODIS Poland direkt.

Peek liest die englische Ausgabe der Strefa Klienta: Der Code `100` (`Delivered`) erscheint übersetzt als „Zugestellt“, während alle übrigen Scan-Bezeichnungen im englischen Wortlaut von GEODIS bleiben (einschließlich `Delivered (SMS)`), zusammen mit dem Ort des Terminals, dem Zustellzeitpunkt und dem Zielland. Scans an polnischen Terminals werden in Warschauer Zeit (`Europe/Warsaw`) gelesen; Terminals im Ausland behalten ihre Ortszeit. Peek rechnet bekannte Zeitpunkte in die Zeitzone deines Geräts um; auf `peektracker.com` und in den Apps für iPhone und Android endet der Verlauf mit „Uhrzeiten in deiner Zeitzone:“ und deren Namen. Hebt eine `Pseudoscan`-Zeile frühere Einträge auf, blendet Peek diese ebenfalls aus, und `Claim Received` lässt die aktuelle Versandphase unverändert. Ein voraussichtliches Lieferdatum, Gewicht, Produktbezeichnung, Empfängername oder Zustellnachweis liefert der GEODIS-Tracker nicht; Peek lässt außerdem Zustell-Postleitzahl und -Ort, Packstückzahl, interne Terminalcodes sowie Bemerkungen weg, in denen der Name der unterzeichnenden Person stehen kann.

Solange du das Paket geöffnet hast oder am Zustelltag mit eingeschalteten Benachrichtigungen, prüft Peek bis zu alle 10 Minuten – und ab 6 Uhr Ortszeit an dem Tag, an dem GEODIS die Sendung als `Outbound for Delivery` scannt, bis zu alle 2 Minuten –, solange der eigene Tracker von GEODIS antwortet; sonst sowie nach zwei Tagen ohne Bewegung (12 Stunden, solange die Sendung noch auf „Etikett erstellt“ oder „Beim Paketdienst eingegangen“ steht) stündlich. Über Drittanbieter-Trackingdienste prüft Peek stündlich, nach einem ruhigen Tag alle 3 Stunden und am Zustelltag bis zu alle 15 Minuten, solange das Paket geöffnet ist oder Benachrichtigungen aktiv sind. Benachrichtigen lassen kannst du dich über „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“. Liefertermine vereinbaren, eine Terminalabholung beauftragen oder Reklamationen einreichen kann Peek nicht: Ruf dafür GEODIS an oder wende dich an den Händler.

## Häufige Fragen zur GEODIS Polen Sendungsverfolgung

### Ist GEODIS Poland dasselbe wie Pekaes?

Ja. GEODIS hat PEKAES im Jahr 2021 übernommen; heute wird das Netz von GEODIS Road Network betrieben. Die Sendungsverfolgung läuft weiterhin unter `strefaklienta.pekaes.geodis.pl`.

### Stellt GEODIS in Polen auch samstags zu?

Die Allgemeinen Geschäftsbedingungen von GEODIS Road Network sehen keine Samstagszustellung vor: Paletten an Privatadressen in Polen (`DDOP`) werden werktags von Montag bis Freitag zwischen `8:00` und `16:00` Uhr geliefert, und bei nationalen Paketen (`PAC DOM`) zählen Samstage, Sonntage und gesetzliche Feiertage nicht zur Laufzeit.

### Trägt der GEODIS-Fahrer meine Bestellung in Polen in die Wohnung?

Nur bei nationalen Stückgutsendungen in Polen (`LTL DOM` oder `LTL DOM NST`), wenn der Auftraggeber den kostenpflichtigen Eintrage-Service (*Usługa Wniesienia*) gebucht hat: bis 300 kg brutto (maximal 30 kg pro Packstück), höchstens 150 m vom Entladeort entfernt und ohne nutzbaren Aufzug bis ins zweite Geschoss. Ohne diese Option wird ebenerdig abgeladen.

### Wie lange dauert der Versand mit GEODIS in Polen und Europa?

Auf seiner Produktseite für Paletten und Pakete nennt GEODIS innerhalb Polens 24 bis 48 Stunden im Standardversand (3 bis 5 Tage in Europa) bzw. Expresszustellung am nächsten Tag vor Mittag in Polen (1 bis 2 Tage in Europa). Laut Geschäftsbedingungen stellt GEODIS nationale Pakete bis 30 kg (`PAC DOM`) grundsätzlich innerhalb von 48 Stunden ab Abholung (ohne Wochenenden und Feiertage) und Stückgut (`LTL`) grundsätzlich innerhalb von 2 Werktagen zu.

:::sources
- [GEODIS Poland: Strefa Klienta, shipment tracking (auf Englisch)](https://strefaklienta.pekaes.geodis.pl/en/) – Suchfeld `Enter Shipment No.`, Schaltfläche `Search`, Reklamations- und Schadensformulare
- [GEODIS Poland: Strefa Klienta, Śledzenie przesyłek (auf Polnisch)](https://strefaklienta.pekaes.geodis.pl/pl/) – Rufnummer des Kundenservice `+48 22 460 26 26`, Allgemeine Geschäftsbedingungen
- [GEODIS Road Network: Ogólny Regulamin Świadczenia Usług, gültig ab 4. November 2024 (auf Polnisch)](https://strefaklienta.pekaes.geodis.pl/pl/?file=files/pekaes/Dla%20Klienta%20-%20nowe%20www/GEODIS_SK/GEODIS%20RN_Ogolny%20Regulamin%20Swiadczenia%20Uslug_od%2004.11.2024.pdf) – Zustellversuche national und international, `DDOP`, Entladung, Eintrage-Service, Terminalabholung, Nachnahme, Unterschrift, Laufzeiten und Reklamationen
- [GEODIS Road Network: Formularz zgłoszenia reklamacji (auf Polnisch)](https://strefaklienta.pekaes.geodis.pl/pl/?file=files/pekaes/Dla%20Klienta%20-%20nowe%20www/GEODIS_SK/GRN%20Formularz%20zgloszenia%20reklamacji.pdf) – Postanschrift für Reklamationen in Błonie, Hinweis zum Schadensprotokoll
- [GEODIS: Śledź swoją przesyłkę (auf Polnisch)](https://geodis.com/pl-pl/sledz-swoja-przesylke) – Fundort der Nummer, Luft-/See-/Letzte-Meile-Sendungen auf `geodis.com`, Verweis für Straßentransport, Hilfe bei ungültiger Nummer
- [GEODIS: Twój obszar roboczy (auf Polnisch)](https://geodis.com/pl-pl/twoj-obszar-roboczy) – Strefa Klienta als Tracking-Plattform
- [GEODIS: Transport palet i paczek (auf Polnisch)](https://geodis.com/pl-pl/uslugi-transportowe/transport-palet-i-paczek) – Angebot nur für Geschäftskunden, Standard- und Expresslaufzeiten in Polen und Europa
- [GEODIS: Skontaktuj się z nami (auf Polnisch)](https://geodis.com/pl-pl/skontaktuj-sie-z-nami) – Geschäftskundenformular und Liste der 42 Standorte in Polen
- [GEODIS: Historia (auf Polnisch)](https://geodis.com/pl-pl/historia) – Übernahme von PEKAES im Jahr 2021 und Zugehörigkeit zur SNCF
- [UOKiK: Odpowiedzialność za przesyłkę (auf Polnisch)](https://prawakonsumenta.uokik.gov.pl/prawa-ogolne/odpowiedzialnosc-sprzedawcy-za-przesylke/) – Haftung des Verkäufers bis zur Übergabe nach polnischem Verbraucherrecht
- [UOKiK: Wydanie towaru (auf Polnisch)](https://prawakonsumenta.uokik.gov.pl/prawa-ogolne/wydanie-towaru/) – 30-Tage-Frist, Nachfrist und Rücktrittsrecht in Polen
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – `PL`-Nummernformat mit 16 Ziffern, Strefa-Klienta-Links, Scan-Bezeichnungen, `1G`-Nummern von GEODIS Frankreich und von Peek gelesene Felder
:::
