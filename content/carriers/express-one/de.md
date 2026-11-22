---
title: Express One Sendungsverfolgung: Status & Kontakt
description: Express One Paket in Ungarn verfolgen: Herkunft der Nummer, Statusmeldungen, Paketpunkte, Automaten, verpasste Zustellungen und Kontakt.
slug: express-one-sendungsverfolgung
published: 2026-11-22
updated: 2026-11-22
---

Express One Hungary ist ein ungarischer Paketdienst im Eigentum der [Österreichischen Post](carrier:austrian-post), der für Online-Shops mit rund 700 Zustellern und mehr als 2.300 Paketpunkten und Paketautomaten liefert. Verfolge ein Paket über seine Paket-ID auf [tracking.expressone.hu](https://tracking.expressone.hu/) oder füge sie oben ein. Die Nummer steht in der E-Mail, die Express One verschickt, sobald das Paket registriert und dort eingetroffen ist.

## Sendungsnummer von Express One

Express One mailt dir eine Vorankündigung, sobald der Absender das Paket registriert hat und es physisch bei Express One angekommen ist; eine SMS kommt nur, wenn der Absender dafür bezahlt. Innerhalb Ungarns nennen die Nachrichten das Lieferzeitfenster, den Kontakt des Kuriers, einen eventuellen Nachnahmebetrag und einen Link zur Lieferänderung. Sendungen ins Ausland erhalten keine Benachrichtigung. Keine Nachricht erhalten? Frage den Shop und lies [Sendungsnummer finden](guide:find-tracking-number).

Der Tracker akzeptiert auch Absenderreferenz oder Dokumentennummer, doch dafür ist die 5-stellige Company ID des Absenders nötig – verwende daher die Paket-ID (die Barcodenummer).

Express One veröffentlicht kein festes Format. Bisher bekannte Paket-IDs treten in zwei Formen auf (Ziffern erfunden):

| Nummer | Wozu sie gehört |
| --- | --- |
| `4829173648291` | 13 Ziffern: eine Express-One-Paket-ID, aber auch DPD France und andere Dienste nutzen diese Länge. |
| `148291736482917364821011` | 24 Ziffern, die auf eine ungarische Postleitzahl enden: Express One. Auch Österreichische Post, [InPost](carrier:inpost), [bpost](carrier:bpost) und [Ciblex](carrier:ciblex) verwenden 24 Ziffern. |
| `48291736482917` | 14 Ziffern: DPD, niemals Express One. |
| `48291736482` | 11 oder 12 Ziffern: GLS, niemals Express One. |
| `RR482917367HU` | Zwei Buchstaben, 9 Ziffern, `HU`: Magyar Posta, niemals Express One. |

Orientiere dich an dem Paketdienst aus der Shop-E-Mail; mehr unter [Sendungsnummern-Formate](guide:tracking-number-formats).

## Bedeutung der Statusmeldungen von Express One

tracking.expressone.hu erscheint je nach Browsersprache auf Englisch oder Ungarisch, mit einem EN/HU-Umschalter. Jeder Scan trägt Code, Text, Datum und Uhrzeit; Hub- und Depotscans ergänzen den Ort in Klammern, etwa „(HU - Budapest)“. Die englischen Texte in typischer Reihenfolge:

| Status von Express One | Bedeutung für dich |
| --- | --- |
| `Shipping details received, shipment not yet handed over to EXOHU` | Der Absender hat [das Paket angemeldet](guide:tracking-statuses); Express One hat es noch nicht erhalten. |
| `Shipment (pan) data uploaded` | Die Paketdaten sind im System von Express One, meist direkt nach einem Hub-Scan. |
| `Arrived at central hub` | Im Zentralhub in Budapest eingetroffen. |
| `Departed central hub` | Hat den Hub verlassen. |
| `Arrived at depot` | Im Zustelldepot für deine Adresse angekommen. |
| `Out for delivery` | Heute mit dem Kurier unterwegs. |
| `Delivery schedule updated` | Der Lieferplan wurde neu berechnet; das Paket hat sich nicht bewegt. |
| `Processing in progress` | Die Zustellung wurde ohne Begründung verschoben. |
| `Addressee not present at delivery address` | Der Kurier war da und niemand hat geöffnet. |
| `Addressee unavailable` | Auch der zweite Zustellversuch schlug fehl. |
| `Obstacles of nature` | Unwetter stoppte die Tour; es zählt als Versuch und das Paket geht erneut raus. |
| `Successful delivery` | Zugestellt. |

EXOHU steht für Express One Hungary: der erste Status bedeutet, dass erst das Etikett existiert. Der Balken über dem Verlauf nutzt eigene Beschriftungen von `Shipment data uploaded` bis `Successfully delivered`. Auf Ungarisch lautet zugestellt `Sikeres kézbesítés`.

## Zustellung, Paketpunkte und verpasste Lieferungen

**Wann es kommt.** Die Vorankündigung nennt ein 4-Stunden-Fenster. Am Liefermorgen nennt eine Nachricht ein 2-Stunden-Fenster, das Express One ausdrücklich als unverbindlich bezeichnet: der Kurier kann früher oder später kommen. Am selben Tag zeigt die Tracking-Seite, wie viele Stopps noch vor dir liegen. Wochenendzustellung gibt es nicht, außer kostenpflichtiger Samstagszustellung für Geschäftskunden.

**Änderungen.** Über die Schaltfläche „Modification“ in der morgendlichen SMS oder E-Mail kannst du Zeit oder Adresse ändern oder das Paket an einen Paketshop oder Automaten umleiten – selbst noch eine Stunde vor Ankunft des Kuriers. Änderungen sind nur am ersten Liefertag und nur einmal möglich. Manche Optionen hängen vom Absender ab (große Pakete passen nicht in einen Automaten). Laut AGB zählt eine Änderung als Zustellversuch und kann extra kosten; ein Express-One-Blogbeitrag von 2024 nennt die Umleitung kostenlos.

**An der Tür.** Privatempfänger unterschreiben und geben eine Ausweisnummer an. Nachnahme wird bar (bis 300.000 Ft), per Karte (bis 1.000.000 Ft) oder vorab über einen Link in der Vorankündigung gezahlt; an einem Exopoint liegt das Barlimit bei 250.000 Ft. Auspacken vor Unterschrift und Zahlung ist nicht erlaubt.

**Verpasste Zustellung.** Laut AGB benachrichtigt dich Express One bis zum nächsten Werktag per E-Mail oder SMS, unternimmt einen weiteren kostenlosen Versuch und lagert das Paket höchstens bis zum 5. Werktag. In der Zwischenzeit kannst du einen neuen Termin oder einen Paketpunkt wählen. Nach dem zweiten erfolglosen Versuch geht das Paket zurück an den Absender; laut einem Blogbeitrag von 2024 wartet es zuvor 5 Werktage im Zentraldepot Budapest.

**Paketpunkte und Automaten.** Viele Shops lassen dich im Checkout einen Exopoint-Paketpunkt oder Automaten wählen. Die Paketpunkt-Seite von Express One nennt bis zu eine Woche Lagerzeit rund um die Uhr. Die Blogbeiträge weichen ab: 3 Tage im EXOBOX, 2 im Alzabox, bis zu 7 bei Packeta (2025), 72 Stunden im Automaten (2026), 5 Tage im Paketpunkt (2024), und nur EXOBOX- und Alzabox-Automaten sind rund um die Uhr zugänglich. Eine Abholung im Logistikzentrum Budapest (H-1239 Budapest, Európa street 12, building L1) erfordert einen Termin beim Kundenservice.

## Express-One-Paket verspätet, beschädigt oder verloren

**Keine Aktualisierung oder nicht gefunden.** Express One nennt keine Wartefrist. Der Tracker erklärt, die Nummer könne falsch, älter als 180 Tage oder vom Absender noch nicht registriert sein, und rät zu einem späteren Versuch. Laut AGB darf Express One Daten löschen, wenn innerhalb von 5 Werktagen kein Paket eingeht, und bezeichnet Lieferzeiten als unverbindlich. Siehe [Sendungsverfolgung aktualisiert sich nicht](guide:tracking-not-updating).

**Beschädigt oder falscher Artikel.** Informiere den Shop so schnell wie möglich: Express One startet eine Reklamation, sobald der Absender sie meldet, und haftet für die Beförderung, nicht für den Inhalt. Sichtbare Schäden meldest du bei Übergabe durch den Kurier; verdeckte Schäden innerhalb von 3 Werktagen nach Zustellung schriftlich, sonst verfällt der Anspruch.

**Verloren.** Meist reklamiert der Absender; du kannst es, wenn das Paket an dich zugestellt wurde oder der Absender dir das Recht schriftlich abtritt. Ein schriftlicher Antrag bei Totalverlust ist innerhalb von 6 Monaten ab dem 15. Tag nach Versand einzureichen, mit Frachtbriefnummer, Inhalts- und Wertnachweis sowie dem Reklamationsformular von Express One. Express One antwortet innerhalb von 30 Kalendertagen; bist du nicht zufrieden, kannst du dich innerhalb von 30 Tagen an die ungarische Medien- und Kommunikationsbehörde NMHH wenden.

## Express One Telefonnummer und Kundenservice

Kontakte von der englischen Kontaktseite von Express One, erreichbar werktags von 8:00 bis 17:00 Uhr (laut FAQ bis 18:00 Uhr), ungarische Zeit:

- **Kundenservice:** +36 1 8 777 400, ugyfelszolgalat@expressone.hu – auch wenn dein Kurier nicht erreichbar ist. Zu Stoßzeiten rät Express One zu einer schriftlichen Anfrage.
- **Internationaler Kundenservice:** +36 1 8 777 410, intcs@expressone.hu; Reklamationen an intcsreklamacio@expressone.hu.
- **Schadensmeldungen:** +36 1 8 777 470, karugy@expressone.hu.
- **Kontaktformular:** auf der [Kontaktseite von Express One](https://expressone.hu/en/kapcsolat) mit Betreffen wie „I am expecting a parcel“ und „Delivery complaint“; beide verlangen die Paketnummer.

Die Parcel-Tracking-Seite von Express One nennt außerdem +36 1 8 777 444 und einen Chatbot.

## Express-One-Pakete mit Peek verfolgen

Eine 13-stellige Nummer fragt [Peek](/) nicht automatisch bei Express One ab, sondern zuerst bei anderen Paketdiensten. Kennt keiner sie, erscheint „noch nicht gefunden · Peek prüft weiter“: wähle „Express One“ über „Ändern“. Bei einer 24-stelligen Nummer fragt Peek in Ungarn zuerst Express One – vor [Österreichischer Post](carrier:austrian-post), [InPost](carrier:inpost) und [Ciblex](carrier:ciblex); außerhalb Ungarns fragt Peek zunächst jene drei Dienste, und du wählst „Express One“ über „Ändern“. Oder füge einen Link von tracking.expressone.hu mit `?plc_number=` ein, der Express One unabhängig vom Format direkt erkennt.

Scans von Express One enthalten Uhrzeiten ohne Zeitzone: Peek liest sie nach der Ortszeit des Scan-Orts (ungarische Zeit bei Scans in Ungarn) und zeigt sie in deiner Zeitzone an („Uhrzeiten in deiner Zeitzone:“). Peek liest Status sowie bei Hub-Scans Ort und Land. Formulierungen bleiben wie von Express One geschrieben, außer `Out for delivery`; `Successful delivery` zählt als zugestellt und eine Rücksendung als zurückgesendet. Ein unbekannter Scan behält den Text ohne Statusstufe. Lieferprognose, Gewicht, Abholpunkt, Fortschrittsbalken und Unterschrift werden nicht übernommen.

Peek fragt Express One direkt bis zu alle 10 Minuten ab – oder alle 2 Minuten ab 6:00 Uhr nach der Uhr des letzten Scans (ungarische Zeit in Ungarn) an dem Tag, an dem Express One das Paket in Zustellung scannt, solange das Paket in Peek geöffnet ist oder am Liefertag mit aktiven Mitteilungen. Nach zwei Tagen ohne Neuigkeiten (nach 12 Stunden, solange das Paket noch auf „Etikett erstellt“ oder „Beim Paketdienst eingegangen“ steht) prüft Peek stündlich. Für Mitteilungen wählst du „Alle Updates“, „Nur wichtige“ oder „Nur am Liefertag“. Nach vier Tagen ohne Scan unterwegs, außerhalb von Zoll oder gemeldetem Problem, zeigt Peek „Keine Aktualisierung seit …“, erklärt „Auf langen Flügen, beim Zoll oder übers Wochenende wird es oft still um ein Paket. Peek fragt weiter bei Express One nach.“ und bietet „Hol dir Hilfe bei Express One“.

## Häufige Fragen zu Express One

### Was bedeutet „shipment not yet handed over to EXOHU“?

Der Absender hat die Sendung erstellt, aber Express One Hungary hat das Paket noch nicht erhalten. Bleibt dieser Status tagelang stehen, frage beim Shop nach.

### Wie lange liegt ein Express-One-Paket im Paketpunkt?

Bis zu eine Woche rund um die Uhr, heißt es auf der Paketpunkt-Seite; die Blogbeiträge nennen je nach Punkt 2 bis 7 Tage und Rund-um-die-Uhr-Zugang nur bei EXOBOX- und Alzabox-Automaten.

### Ist Express One in der Slowakei oder Slowenien dasselbe Unternehmen?

Nein. Sie gehören ebenfalls zur Österreichischen Post, sind aber eigenständige Gesellschaften; diese Seite und „Express One“ in Peek decken das ungarische Unternehmen ab.

:::sources
- [Express One: Parcel Tracking](https://expressone.hu/en/csomagkovetes) – Kurierstopps, Callcenter, Chatbot
- [Express One: Tracking](https://tracking.expressone.hu/) – ID-Arten, Company ID, Nicht-gefunden-Meldung, 180 Tage, Status, Sprachen
- [Express One: Frequently asked questions](https://expressone.hu/en/gyik) – Änderung, 2-Stunden-Fenster, Samstag, Nachnahme, Schäden, falscher Artikel
- [Express One: GYIK](https://expressone.hu/gyik) – Nachnahmelimits
- [Express One: Contact](https://expressone.hu/en/kapcsolat) – Telefonnummern, E-Mails, Zeiten, Formular, Logistikzentrum
- [Express One: Parcel point](https://expressone.hu/en/csomagpont) – 2.300 Punkte und Automaten, eine Woche, 24/7, Auswahl im Checkout
- [Express One: Home](https://expressone.hu/en) – Österreichische Post, 700 Zusteller, Umleitung bis eine Stunde vorher
- [Express One: General Terms and Conditions](https://expressone.hu/downloads/General_Terms_and_Conditions_2026_08_15.pdf) – Zustellversuche, Lagerung, Ausweis, Änderungsgebühr, Reklamationen, NMHH, Datenlöschung
- [Express One: Merre jár a csomagom?](https://expressone.hu/blog/merre-jar-a-csomagom-csomagkovetes-es-ertesitesi-rendszerek-alkalmazasa) – Vorankündigung nach Registrierung und Eingang, 4- und 2-Stunden-Fenster, SMS-Option
- [Express One: Csomag átirányítás és a kézbesítés átütemezése](https://expressone.hu/blog/csomag-atiranyitas-es-a-kezbesites-atutemezese) – kostenlose Umleitung, 5 Tage im Depot Budapest oder im Paketpunkt
- [Express One: Minden, amit az EXOPOINT megoldásokról tudni érdemes](https://expressone.hu/blog/minden-amit-az-exopoint-megoldasokrol-tudni-erdemes) – Öffnungszeiten, 3, 2 und 7 Tage
- [Express One: Csomagküldés csomagautomatába](https://expressone.hu/blog/csomagkuldes-csomagautomataba-miert-valasztjak-egyre-tobben) – 72 Stunden im Automaten
- [Express One: eBOX GYIK](https://expressone.hu/ebox/faq) – Inhalt der Benachrichtigungen, keine bei Export, 250.000 Ft bar am Exopoint
- [Austrian Post: Austrian Post Group acquires Hungarian parcel delivery provider](https://news.post.at/presse/en/post/id/2255390/) – hundertprozentige Tochtergesellschaft
- [Austrian Post: International subsidiaries](https://post.at/en/j/c/careers-austrian-post-subsidiaries-international) – Express-One-Gesellschaften in anderen Ländern
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – von Peek ausgelesene Daten
:::
