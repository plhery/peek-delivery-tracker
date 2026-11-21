---
title: DPD UK Sendungsverfolgung: 14-stellige Nummer und Status
description: DPD-UK-Paket verfolgen: wo die 14-stellige Nummer steht, was die englischen Status bedeuten, Predict und Pickup in Großbritannien, Zoll und Kontakt.
slug: dpd-uk-sendungsverfolgung
published: 2026-11-21
updated: 2026-11-21
---

DPD UK (`DPDgroup UK Ltd`) ist die britische Paketgesellschaft der Geopost-Gruppe, die zur französischen [La Poste](carrier:la-poste) (*La Poste Groupe*) gehört. Im **Vereinigten Königreich** stellt sie Pakete vieler Onlineshops zu und verschickt Sendungen auch nach Kontinentaleuropa und weltweit. Du kannst ein Paket auf [Follow My Parcel von DPD UK](https://track.dpd.co.uk/) mit Referenznummer und Zustell-Postleitzahl abfragen oder die 14-stellige Paketnummer direkt oben ins Suchfeld einfügen. Die Nummer stammt vom Shop, aus der SMS oder E-Mail von DPD UK oder steht neben dem Barcode auf der Benachrichtigungskarte (*sorry we missed you*).

## Wo steht die DPD-UK-Sendungsnummer und wie ist sie aufgebaut?

Den Code erhältst du vom Absender oder über den Link in der SMS beziehungsweise E-Mail von DPD UK. Wenn der Fahrer im **Vereinigten Königreich** eine *sorry we missed you*-Karte hinterlassen hat, steht die Paketnummer neben dem Strichcode; auf `track.dpd.co.uk` funktioniert zusammen mit der Postleitzahl auch die Kartennummer (*Calling card number*). Nicht auffindbar? Siehe [Sendungsnummer finden](guide:find-tracking-number).

DPD UK beschreibt das Nummernformat auf den eigenen Hilfeseiten nicht. Auf dem Tracker von DPD UK haben Paketnummern 14 Ziffern, sehr oft mit `1550` oder `1597` am Anfang; mitunter hängt DPD UK am Ende noch ein 15. Prüfzeichen (Ziffer oder Buchstabe) an:

| Nummer (frei erfunden) | Bedeutung |
| --- | --- |
| `15504829105710` | DPD-UK-Paketnummer: 14 Ziffern mit Beginn `1550` (oder `1597`). |
| `15504829105710S` | Dieselbe Nummer mit angehängtem DPD-Prüfzeichen. |
| `18429105736482` | Andere 14-stellige Nummer: kann zu DPD UK, einer anderen DPD-Landesgesellschaft oder einem anderen Paketdienst gehören. |

Auch [DPD Deutschland](carrier:dpd-de), DPD Schweiz, DPD Österreich, [Chronopost](carrier:chronopost), [SEUR](carrier:seur), [BRT](carrier:brt), [Hermes](carrier:hermes-de) (bei bestimmten 14-stelligen Nummern) und DHL eCommerce UK nutzen 14 Ziffern – allein an der Länge lässt sich der Dienstleister daher nicht immer ablesen. In Großbritannien erscheinen echte SMS-Benachrichtigungen unter dem Absendernamen `DPD UK` (oder `DPD Local` beziehungsweise `DPDgroup UK`), während der Name des Händlers im Nachrichtentext steht. DPD Local ist die Schwestermarke von DPD UK; die DPD-App zeigt Sendungen beider Marken an. In **Irland** läuft die Zustellung über DPD Ireland mit eigenen Nummern.

## Englische Statusmeldungen von DPD UK erklärt

Die Sendungsverfolgung von DPD UK ist ausschließlich auf Englisch. Das bedeuten die wichtigsten Meldungen in der typischen Reihenfolge:

| DPD UK meldet | Was das für dein Paket heißt | Peek zeigt |
| --- | --- | --- |
| `We've received your order details, but have not yet received your parcel` | Der Händler hat den Versand angemeldet, DPD UK hat das Paket aber noch nicht übernommen. | „Etikett erstellt“ |
| `We've received your order details, and we're expecting your parcel shortly` | Das Etikett ist gebucht; DPD UK erwartet die Einlieferung durch den Absender in einem DPD Pickup Shop. | „Etikett erstellt“ |
| `Your parcel has been dropped off at a DPD Pickup Shop` | Der Absender hat das Paket in einem DPD Pickup Shop abgegeben. | „Beim Paketdienst eingegangen“ |
| `We have your parcel and it's on its way to our depot` | DPD UK hat das Paket übernommen und bringt es ins Depot. | „Beim Paketdienst eingegangen“ (Direktabholung) bzw. „Unterwegs“ (nach Abgabe im Pickup Shop) |
| `We have your parcel and it's on its way to you` | Das Paket ist im DPD-UK-Netz unterwegs in deine Region. | „Unterwegs“ |
| `Your parcel has arrived at our depot`, `Your parcel is at our depot` | Im DPD-UK-Depot eingetroffen und in der Sortierung. | „Unterwegs“ |
| `Your parcel is at our depot ready for delivery` | Im Zustelldepot bereit für die Tour; später lautet dieselbe Zeile wieder `Your parcel is at our depot`. | „Unterwegs“ |
| `A notification has been sent to advise you when the delivery will take place` | DPD UK hat dir per SMS oder E-Mail den Liefertag oder das Zeitfenster geschickt. | Vorheriger Status bleibt stehen |
| `As requested, your parcel will now be delivered on Thu 05 November 2026 at 11:00` | Du hast einen neuen Zustelltag (teils mit Uhrzeit) gewählt; Datum und Zeit variieren. | Vorheriger Status bleibt stehen |
| `Your parcel will be with you today`, `Your parcel will be with you today between 14:18 and 15:18` | Heute in Zustellung, samt einstündigem Zeitfenster, sobald es feststeht (Zeiten variieren). | „In Zustellung“ |
| `As requested, your parcel will now be left in your selected safe place` | Du hast (oder über deine App-Einstellungen) einen sicheren Ablageort hinterlegt. | Vorheriger Status bleibt stehen |
| `We missed you. Your driver won't be back today but we'll be back Wed 28 October with your parcel` | Zustellversuch fehlgeschlagen; nächster Versuch am genannten Tag. Den Fahrernamen aus der Originalzeile blendet Peek aus. | „Zustellversuch“ |
| `Your parcel has been delivered` | Zugestellt (in der Kopfzeile nennt DPD UK zusätzlich Uhrzeit und Datum). | „Zugestellt“ |
| `Your parcel is waiting for you at home (left in safe place)` | Am gewählten Ablageort zugestellt. | „Zugestellt“ |
| `Your delivery has been cancelled` | Der Absender hat die Sendung storniert; DPD UK verweist an den genannten Shop. | „Problem gemeldet“ |
| `We have your parcel and it's on its way back to the sender` | Rücksendung an den Absender läuft (außer vor dem ersten Depotscan bei einem im Pickup Shop eingelieferten Etikett, das zum buchenden Konto unterwegs ist: „Unterwegs“). | „Problem gemeldet“ |

`We have X-rayed your parcel as part of our normal security procedures` ist eine routinemäßige Sicherheitskontrolle ohne Handlungsbedarf („Unterwegs“ in Peek), und `There has been a delay with your delivery` ist ein Verzögerungshinweis, der die bisherige Phase unverändert lässt. Verlässt ein DPD-UK-Paket das **Vereinigte Königreich** ins Ausland, meldet `Your parcel has left our sortation facility and is on it's onwards journey` (so schreibt DPD UK `it's`) den Abgang aus der britischen Sortierung; danach übernimmt der Verlauf die englischen Meldungen des Zustellpartners im Zielland: `The parcel is in transit to its final destination` („Unterwegs“), `The parcel is undergoing customs clearance` und `Duties and Taxes paid` („Beim Zoll“), `The parcel has now cleared customs` und `The parcel has arrived at the delivery depot` („Unterwegs“), `The parcel is on the vehicle for delivery` („In Zustellung“) sowie `The parcel has been delivered` („Zugestellt“). Mehr dazu unter [Was Tracking-Status bedeuten](guide:tracking-statuses).

## Predict, Pickup-Shops in Großbritannien und Zoll nach Europa

Die folgenden Regeln zu Predict, Nachbarn und Pickup-Shops gelten für Zustellungen innerhalb des **Vereinigten Königreichs**:

**Predict-Zeitfenster.** In Großbritannien schickt DPD UK am Zustelltag per SMS und E-Mail ein einstündiges Lieferfenster, sobald der Fahrer alle Pakete in den Transporter gescannt hat – meist bis 12 Uhr mittags (*usually by 12 noon*). Eine Live-Karte grenzt die Ankunft im Verlauf bis auf ein 15-Minuten-Fenster ein und zeigt den Namen des Fahrers sowie deinen Platz in seiner Zustellliste. Das Zeitfenster selbst lässt sich nicht verschieben.

**Zustellung ändern.** Am Vorabend und am Liefertag kannst du in Großbritannien über SMS, E-Mail oder die DPD-App einen anderen Tag, einen Wunschnachbarn, einen Ablageort (*safe place*) oder einen DPD Pickup Shop wählen oder kostenpflichtig auf Zustellung vor 10:30 Uhr, vor 12 Uhr, am Nachmittag, Samstag oder Sonntag upgraden. Verlangt der Absender eine Unterschrift, entfallen einzelne Optionen. Für die Umleitung an [eine andere Lieferadresse](guide:change-delivery-address) musst du dich an den Absender wenden.

**Nicht angetroffen.** Im Vereinigten Königreich versucht DPD UK automatisch die Abgabe bei einem Nachbarn; laut den Beförderungsbedingungen (*Standard Terms & Conditions of Carriage*) gilt als *Neighbour*, wer in höchstens 50 Metern Gehstrecke von der Lieferadresse wohnt oder arbeitet (*within 50 metres walking distance of the Delivery Address*). Am selben Tag kann der Fahrer nicht zurückkehren. Mit Referenznummer und Postleitzahl siehst du auf `track.dpd.co.uk`, wo die Karte *sorry we missed you* hinterlegt wurde, und wählst einen neuen Termin, Ablageort, Nachbarn oder Pickup Shop; eine feste Anzahl an Zustellversuchen nennt DPD UK nicht. Steht die Sendung auf „Zugestellt“, ist aber unauffindbar, prüfe zuerst Ablageort und Nachbarn und lies dann [Zugestellt, aber nicht erhalten](guide:delivered-not-received) sowie [Zustellung verpasst](guide:missed-delivery).

**DPD Pickup Shops und Paketstationen in Großbritannien.** DPD UK betreibt im Vereinigten Königreich über 6.000 Pickup-Shops, darunter Filialen von Sainsbury's, Post Office, Co-op, Morrisons, Argos und Matalan. Ein dorthin umgeleitetes Paket liegt in der Regel am nächsten Tag bereit. Zur Abholung verlangt DPD UK zwei Nachweise (*2 pieces of ID*): einen Lichtbildausweis und einen Adressnachweis wie eine Versorgerrechnung, dazu den *DPD Pickup Pass* aus der Sendungsverfolgung. Eine pauschale Lagerfrist im Pickup Shop veröffentlicht DPD UK auf den Hilfeseiten nicht; in der DPD-App stehen außerdem Paketstationen (*Locker*) zur Wahl.

**Einfuhrabgaben in Großbritannien.** Für Sendungen aus dem Ausland nach Großbritannien fällt Einfuhrumsatzsteuer an (außer bei Geschenken bis 39 £) sowie gegebenenfalls Zoll; das Zustellunternehmen stellt die Rechnung aus (auf `track.dpd.co.uk` unter *import charges* bezahlbar) und bewahrt das Paket laut GOV.UK normalerweise rund 3 Wochen (*about 3 weeks*) auf, bevor es unbezahlt zurückgeht.

**Pakete aus Großbritannien in die EU und die Schweiz.** Da das Vereinigte Königreich nicht zur EU gehört, durchlaufen Sendungen von dort nach **Deutschland**, **Österreich** oder in die **Schweiz** den Einfuhrzoll. In der **Europäischen Union** gilt seit dem 1. Juli 2026 für online außerhalb der EU gekaufte Waren in Sendungen mit einem Sachwert bis 150 € ein EU-Zollsatz von 3 € pro Position (*per item*); seit dem 1. November 2026 fällt zusätzlich unabhängig vom Warenwert eine EU-Bearbeitungsgebühr (*Union handling fee*) von 2 € pro Position an (eine Position umfasst Waren desselben Pakets mit derselben Zolltarifnummer, Beschreibung und – sofern anzugeben – demselben Ursprung; über 150 € greifen die regulären Zollsätze). In der **Schweiz** erhebt das Bundesamt für Zoll und Grenzsicherheit (BAZG) 8,1 % Einfuhrsteuer (2,6 % ermäßigter Satz, etwa für Bücher und Lebensmittel), wobei aus Verwaltungsökonomie „Zollabgaben bis 5 Schweizerfranken nicht erhoben“ und „Steuerbeträge bis 5 Schweizerfranken nicht erhoben“ werden – zuzüglich allfälliger Verzollungsgebühren des Zustellers. Einzelheiten unter [Paket hängt beim Zoll](guide:customs).

## Paket verspätet, verloren, beschädigt oder Phishing-Nachricht

**Verzögerungen.** Laut den Beförderungsbedingungen von DPD UK sind sämtliche Laufzeiten unverbindliche Schätzungen (*estimates only*). Für Lieferungen im **Vereinigten Königreich** zeigt die Seite [Service Disruption von DPD UK](https://www.dpd.co.uk/content/products_services/uk_issues.jsp) betroffene britische Postleitzahlengebiete. Bewegt sich tagelang nichts, hilft [Warum sich das Tracking nicht aktualisiert](guide:tracking-not-updating) – wende dich anschließend an den Händler.

**Verlust oder Beschädigung.** Vertragspartner von DPD UK ist der versendende Shop (*Customer*), der deshalb den Ersatzanspruch stellen muss. Nach den Bedingungen von DPD UK muss der Absender Verlust oder Schaden innerhalb von 14 Tagen ab Versanddatum (*within 14 days of the date of despatch*) melden, angeforderte Belege binnen 28 Tagen nachreichen und den Anspruch spätestens innerhalb eines Jahres ab Versand (*within one year from the date of despatch*) geltend machen. Bewahre Verpackung und Fotos für den Shop auf: siehe [Paket verloren](guide:lost-parcel) und [Paket beschädigt](guide:damaged-parcel).

**Rücksendung an den Absender.** Kann DPD UK eine Sendung nicht zustellen und lagert sie in eigenen Räumen, behält sich DPD UK laut seiner Rückgabe- und Verwertungsrichtlinie vor, sie nach mindestens 5 Kalendertagen (*after a minimum of 5 calendar days*) an den Absender zurückzuschicken; unzustellbare und nicht rückführbare Ware kann nach 4 Wochen (persönliche Gegenstände nach 12 Wochen) verwertet oder vernichtet werden. Siehe [Zurück an den Absender](guide:returned-to-sender).

> DPD UK fordert niemals per Link in einer E-Mail, SMS oder iMessage eine Gebühr für eine erneute Zustellung (*redelivery fee*). Im Vereinigten Königreich kommen automatische DPD-UK-Mitteilungen per SMS (nicht als iMessage) unter `DPD UK`, `DPD Local` oder `DPDgroup UK` – nie von einer E-Mail-Adresse oder gewöhnlichen Handynummer –, echte E-Mails enden auf `@dpd.co.uk`, `@dpdlocal.co.uk`, `@dpdgroup.co.uk` oder `@dpd.uk`, und echte Links nutzen ausschließlich `www.dpd.co.uk`, `www.dpdlocal.co.uk` oder `www.dpdgroup.co.uk`. In **Großbritannien** lassen sich verdächtige SMS an `7726` weiterleiten und Phishing-Mails an `report@phishing.gov.uk` melden; für den deutschsprachigen Raum siehe [Gefälschte Paket-SMS erkennen](guide:delivery-scam-texts).

## DPD UK Kundenservice und Kontakt

Offizielle Kontaktwege von DPD UK im **Vereinigten Königreich**:

- **Telefon:** `0121 275 0500` (britische Rufnummer), Montag bis Freitag 8:00–18:30 Uhr, Samstag 8:00–16:00 Uhr, Sonntag 9:00–15:00 Uhr Ortszeit, ausgenommen britische Feiertage (*bank holidays*). Die Kontaktseite nennt 5 bis 10 Minuten Wartezeit (die *Complaints Charter* nennt als Ziel 2 Minuten 30 Sekunden). Halte Paketnummer und Zustell-Postleitzahl bereit.
- **Live-Chat:** auf der [Kontaktseite von DPD UK](https://www.dpd.co.uk/content/how-can-we-help/contact.jsp) nach Eingabe von Referenznummer und Postleitzahl; die Kontaktseite wirbt mit `24/7`, während die *Complaints Charter* von DPD UK dieselben Zeiten wie beim Telefon angibt (Mo–Fr 8:00–18:30, Sa 8:00–16:00, So 9:00–15:00 Uhr).
- **DPD-App:** Chat- und Anruf-Buttons direkt unter der Tracking-Karte; Anrufe aus der App rücken laut DPD UK an die Spitze der Warteschlange.
- **Postanschrift und Beschwerden:** `Customer Services, DPD UK, Roebuck Lane, Smethwick, B66 1BY`, Vereinigtes Königreich. Laut *Complaints Charter* strebt DPD UK eine Antwort binnen 5 Werktagen an (bei komplexen Fällen bis zu 28 Tage).

## DPD-UK-Pakete mit Peek verfolgen

[Peek](/) erkennt eine 14-stellige DPD-Nummer (mit oder ohne 15. Prüfzeichen). Da mehrere Paketdienste dieses Format teilen, fragt Peek bei den passenden Diensten nach, wer die Sendung führt: **zuerst DPD UK**, wenn die Nummer mit `1550` oder `1597` beginnt, andernfalls zuerst die 14-stelligen Paketdienste für dein Land. Kennt DPD UK die Nummer, erscheint „Hat dieses Paket“ und Peek bleibt bei DPD UK. Steht dort „noch nicht gefunden · Peek prüft weiter“ oder ein anderer Dienst, tippe auf „Ändern“ (bzw. „Paketdienst ändern“) und wähle **DPD UK**. Ein eingefügter Link von `track.dpd.co.uk` oder `tracking.dpd.co.uk` mit `reference=` oder `parcelNumber=` (sowie `track.dpdlocal.co.uk` mit `reference=`) ordnet das Paket sofort DPD UK zu. Bestellreferenzen und Kartennummern, die eine Postleitzahl erfordern, werden nicht unterstützt. Ist eine Nummer unter DPD UK gespeichert und weder bei DPD UK noch über Tracking-Dienste bekannt, zeigt Peek zunächst „Warten auf DPD UK“ und nach zwei Tagen „Auch nach zwei Tagen kennt der Paketdienst diese Nummer nicht. Prüfe sie, wähle einen anderen Paketdienst oder lösche das Paket.“

Die Scans aus dem Netz von DPD UK enthalten keine Zeitzonenangabe: Peek liest sie als britische Zeit (`Europe/London`) und rechnet sie in deine Geräte-Zeitzone um (Scans eines Pickup Shops oder eines ausländischen Partners ohne Zeitversatz behalten ihre angegebene Ortszeit). Auf peektracker.com sowie in der iPhone- und Android-App steht unter dem Verlauf „Uhrzeiten in deiner Zeitzone: …“. Die Statuszeilen bleiben im englischen Originalwortlaut von DPD UK – ohne den Vornamen des Fahrers bei Fehlversuchen und ohne Empfänger- oder Unterzeichnernamen nach `delivered to …` oder `signed for by …`.

DPD UK übermittelt zu den Scans keinen Ort oder Depotnamen, und Peek liest weder das voraussichtliche Lieferdatum noch das einstündige Predict-Fenster aus; interne Paketkennungen, Händlerreferenzen, Empfängerdaten und Zustellfotos verwirft Peek. Den buchenden Händler zeigt Peek als „Absender: …“ an, **außer** wenn der Verlauf zeigt, dass das Paket in einem DPD Pickup Shop eingeliefert wurde: Bei solchen Etiketten (etwa Retouren) ist der Kontoinhaber nicht der Versender, weshalb Peek das Feld ausblendet. Da DPD UK 14-stellige Nummern nach einigen Monaten neu vergibt, kann eine alte Nummer später zu einem neuen Paket gehören. Bei Auslandspaketen bleibt Peek beim Verlauf von DPD UK ohne automatischen Wechsel; nennt dir der Shop eine separate lokale Sendungsnummer (etwa von DPD Deutschland), füge sie als eigenes Paket hinzu.

Solange du das Paket geöffnet hast oder am Liefertag Mitteilungen aktiviert sind, prüft Peek bis zu alle 10 Minuten auf Neuigkeiten – und ab 6 Uhr britischer Zeit an dem Tag, an dem DPD UK das Paket als „In Zustellung“ führt, bis zu alle 2 Minuten, solange der direkte Tracker von DPD UK antwortet (über Tracking-Dienste allein stündlich, alle 3 Stunden nach einem ruhigen Tag und am Liefertag bei geöffnetem Paket oder aktiven Mitteilungen bis zu alle 15 Minuten). Sonst sowie nach zwei Tagen ohne Bewegung (oder 12 Stunden auf „Etikett erstellt“ bzw. „Beim Paketdienst eingegangen“) fragt Peek stündlich ab. Als Benachrichtigung wählst du „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“ / „Nur am Liefertag“. Zustelloptionen ändern oder DPD UK kontaktieren kann Peek nicht – nutze dafür die Website oder App von DPD UK.

## Häufige Fragen zur DPD-UK-Sendungsverfolgung

### Gehört DPD UK zu Royal Mail?

Nein. DPD UK wird von `DPDgroup UK Ltd` betrieben und gehört zu Geopost, der internationalen Paketsparte der französischen [La Poste](carrier:la-poste) (*La Poste Groupe*).

### Wie lange braucht DPD UK für eine Zustellung in Großbritannien?

Das richtet sich nach dem gebuchten Tarif: Innerhalb Großbritanniens stellt `DPD NEXT DAY` am nächsten Werktag bis Geschäftsschluss zu (daneben gibt es `DPD 10:30`, `DPD 12:00` sowie Samstags- und Sonntagszustellung). Die Kanalinseln, die Isle of Man und die Scilly-Inseln dauern regulär zwei Tage, Nordirland einen Tag per Luftfracht oder zwei Tage auf Straße und Seeweg.

### Wo sehe ich, wo mein DPD-UK-Fahrer gerade ist?

Bei einer Zustellung in Großbritannien mit Predict öffnet der Link aus der SMS oder E-Mail am Liefertag (meist bis 12 Uhr mittags) eine Live-Karte mit dem Namen des Fahrers, deiner Position in seiner Tour und einem auf 15 Minuten eingegrenzten Zeitfenster.

### Wie lange lagert DPD UK ein Paket im Pickup Shop?

Auf seinen Hilfeseiten nennt DPD UK keine feste Aufbewahrungsdauer für britische Pickup-Shops; maßgeblich sind die Abholbenachrichtigung und der *DPD Pickup Pass* auf `track.dpd.co.uk`. Allgemein behält sich DPD UK laut seiner Rückgaberichtlinie vor, unzustellbare Sendungen in eigenen Lagern nach mindestens 5 Kalendertagen an den Absender zurückzuschicken.

:::sources
- [DPD UK: Help](https://www.dpd.co.uk/content/how-can-we-help/index.jsp) – Paketnummer und Benachrichtigungskarte, Ein-Stunden-Fenster bis 12 Uhr, Nachbar, Ablageort, über 6.000 Pickup-Shops, zwei Ausweisdokumente, Adressänderung
- [DPD UK: Contact Us](https://www.dpd.co.uk/content/how-can-we-help/contact.jsp) – Telefon 0121 275 0500, Öffnungszeiten, Wartezeit, Online-Chat und bevorzugter App-Anruf
- [DPD UK: Predict & Follow My Parcel](https://www.dpd.co.uk/content/products_services/predict_fmp.jsp) – Ein-Stunden-Fenster, Live-Karte mit 15-Minuten-Slot, Fahrername, Umverfügungsoptionen und Zeit-Upgrades
- [DPD UK: Follow My Parcel](https://track.dpd.co.uk/) – Abfrage mit Sendungs- oder Kartennummer und Postleitzahl, DPD Pickup Pass, Online-Zahlung von Einfuhrabgaben
- [DPD UK: Cyber Security](https://www.dpd.co.uk/content/about_dpd/phishing.jsp) – SMS-Absenderkennungen, keine Nachliefergebühr per Link, echte Domains, Meldung an 7726 und report@phishing.gov.uk
- [DPD UK: DPD Pickup](https://www.dpd.co.uk/content/products_services/dpd_pickup.jsp) – Umleitung unterwegs an einen DPD Pickup Point und Retouren
- [DPD UK: The DPD app](https://www.dpd.co.uk/dpdapp/) – Lieferpräferenzen und Zustellung an Paketstationen (Locker)
- [DPD UK: App FAQs](https://www.dpd.co.uk/lp/app/index.html) – Anzeige von DPD-UK- und DPD-Local-Paketen in der App
- [DPD UK: Standard Terms & Conditions of Carriage (PDF)](https://www.dpd.co.uk/pdf/dpd-stcc.pdf) – Nachbardefinition (50 Meter), unverbindliche Laufzeiten, Vertragspartner und Reklamationsfristen (14 Tage, 28 Tage, ein Jahr)
- [DPD UK: Complaints Charter (PDF)](https://www.dpd.co.uk/pdf/dpd-complaints-charter.pdf) – Chat- und Telefonzeiten, Postanschrift in Smethwick, Antwortziele von 5 bis 28 Tagen
- [DPD UK: Return, Disposal and Destruction of Goods Policy](https://www.dpd.co.uk/return-disposal-of-goods-policy.jsp) – Rücksendung ab 5 Kalendertagen, Aufbewahrung 4 bzw. 12 Wochen
- [DPD UK: UK Delivery Services](https://www.dpd.co.uk/content/products_services/uk_delivery_services.jsp) – DPD Next Day, 10:30, 12:00, Wochenende, britische Inseln und Nordirland
- [DPD UK: Service Disruption](https://www.dpd.co.uk/content/products_services/uk_issues.jsp) – betroffene Postleitzahlen in Großbritannien
- [GOV.UK: Tax and customs for goods sent from abroad](https://www.gov.uk/goods-sent-from-abroad/tax-and-duty) – Abrechnung britischer Einfuhrabgaben durch den Zusteller und rund 3 Wochen Lagerfrist
- [Geopost: About us](https://www.geopost.com/en/about-us/) – Zugehörigkeit von Geopost zur La Poste Groupe
- [Europäische Kommission: EU-Zollreform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 3 € Zollsatz seit 1. Juli 2026 und 2 € Bearbeitungsgebühr seit 1. November 2026
- [Europäische Kommission: Leitlinien zum 3-€-Zoll und zur Bearbeitungsgebühr (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – Erhebung pro Position und Definition einer Position
- [BAZG: Empfangen von Briefen und Paketen](https://www.bazg.admin.ch/de/empfangen-von-briefen-und-paketen) – Schweizer Einfuhrsteuer 8,1 % / 2,6 % und Nichterhebung bis 5 Franken
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – 1550-/1597-Nummern, Erkennung, DPD-UK-Statusmeldungen, Hinweise ohne Phasenwechsel, Zeitzone und Absenderanzeige
:::
