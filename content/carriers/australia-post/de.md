---
title: Australia Post Sendungsverfolgung: Nummern, Status & Fach
description: Australia Post Paket verfolgen: Artikelnummer finden, englische Statusmeldungen verstehen, Abholung in Postfiliale oder Parcel Locker, Zoll und Kontakt.
slug: australia-post-sendungsverfolgung
published: 2026-11-21
updated: 2026-11-21
---

Australia Post ist der staatliche Postdienst **Australiens**. Ein Paket lässt sich ohne Benutzerkonto auf [auspost.com.au/mypost/track](https://auspost.com.au/mypost/track), in der AusPost-App oder durch Einfügen der Sendungsnummer oben verfolgen. Die Nummer stammt vom Absender: aus der Bestellbestätigung des Onlineshops, einer E-Mail von Australia Post oder vom Einlieferungsbeleg einer australischen Postfiliale.

## Australia Post Sendungsnummer: Fundort und Aufbau

Australia Post bezeichnet den Code als Sendungsnummer oder Artikelnummer (*tracking number, or article ID*). Du findest ihn hier:

- **Onlinekauf:** in der Bestellbestätigung oder Rechnung des Händlers; hat der Absender deine E-Mail-Adresse übermittelt, schickt Australia Post Nummer und Statusupdates selbst per E-Mail.
- **Einlieferung in einer Postfiliale in Australien:** oft auf dem Kassenbeleg.
- **Vorfrankierte Versandtasche (*prepaid satchel*):** auf dem Barcode und dem abziehbaren Aufkleber.

Australia Post kann Sendungen weder über den Namen noch über die Adresse suchen – fehlt die Nummer, frage beim Absender nach. Sendungen, die mit der E-Mail-Adresse oder Mobilnummer deines MyPost-Kontos verknüpft sind, erscheinen dort und in der App automatisch. Auf `auspost.com.au/mypost/track` lassen sich bis zu 10 Nummern gleichzeitig abfragen.

Ein offizielles Formatblatt veröffentlicht Australia Post nicht; Peek kennt folgende Nummernformen (mit frei erfundenen Zeichen):

| Aufbau (Beispiel) | Bedeutung |
| --- | --- |
| `33X482910573000482913` | Artikel-ID mit 21 Zeichen: 3 Buchstaben oder Ziffern, 9 Ziffern, `000` und 6 Ziffern. |
| `2933X482910573000482914` | Dieselbe Artikel-ID mit 2 vorangestellten Ziffern (23 Zeichen). |
| `94004829105736000482916` | 23 Ziffern beginnend mit `9400` und `000` vor den letzten 6 Ziffern: ebenfalls Australia Post. |
| `29ABC4829105` | Fracht-/Sendungsnummer (*consignment number*) mit 12 Zeichen. |
| `ABC4829105` | Sendungsnummer mit 10 Zeichen. |
| `ABCZ48291057` | StarTrack-Sendungsnummer (12 Zeichen): 3 Buchstaben oder Ziffern, `Z` und 8 Ziffern. |
| `0048291057364918273643` | Vorfrankierter Barcode mit 22 Ziffern beginnend mit `00` bis `03` (mit GS1-Prüfziffer). |
| `R482910573649182736491827` | `R` gefolgt von 24 Ziffern (25 Zeichen). |
| `EE482910576AU` | Internationale EMS-, Paket- oder Einschreibesendung: `C`, `E` oder `L`, ein Buchstabe, 9 Ziffern und `AU`. |
| `RR482910576AU` | Andere internationale Postnummer auf `AU` (etwa Einschreiben). |

Verwechslungsgefahr: 2 Buchstaben und 10 Ziffern (`AB4829105736`) gehören zu Aramex Australia oder Aramex New Zealand; 22 Ziffern beginnend mit `9400` (`9400482910573649182736`) sind ein Code von [USPS](carrier:usps). Wird eine Nummer nicht erkannt, ist sie möglicherweise vertippt oder noch nicht gescannt – Australia Post rät zu einem erneuten Versuch nach 24 Stunden. Mehr unter [Formate von Sendungsnummern](guide:tracking-number-formats) und [Sendungsnummer finden](guide:find-tracking-number).

## Was die Statusmeldungen von Australia Post bedeuten

Über dem Einzelverlauf blendet Australia Post einen kurzen Meilenstein auf Englisch ein:

| Status bei Australia Post | Bedeutung für dein Paket | Anzeige in Peek |
| --- | --- | --- |
| `Label created by sender` | Der Absender hat ein Etikett erstellt; Australia Post hat das Paket noch nicht. | „Etikett erstellt“ |
| `Pending` | Noch nicht im System von Australia Post gescannt; sollte sich binnen 24 bis 48 Stunden ändern. | „Warten auf Australia Post“ (vor dem ersten Scan) |
| `We've got it` | Australia Post hat die Sendung übernommen. | „Beim Paketdienst eingegangen“ |
| `It's on its way` | Eingeliefert, bearbeitet und unterwegs zwischen den Sortierzentren. | „Unterwegs“ |
| `International arrival - awaiting clearance` | Auslandssendung nach Australien: wartet auf die australische Zollabfertigung. | „Beim Zoll“ |
| `Possible delay` | Wetter, Störungen, Adressfehler oder Zoll verzögern den Transport; das Paket bleibt unterwegs. | Je nach Ereigniscode des Scans, etwa „Unterwegs“ |
| `It's coming today` / `Onboard for delivery` | Heute im Zustellfahrzeug unterwegs. | „In Zustellung“ |
| `Attempted delivery` | Niemand war anzutreffen oder der Zugang war versperrt. | „Zustellversuch“ |
| `Awaiting collection` | Abholbereit in der im Scan genannten Postfiliale oder *Parcel Locker*-Packstation. | „Abholbereit“ |
| `Delivered` / `Delivered - Left in a safe place` | Zugestellt oder an einem sicheren Ablageort (*Safe Drop*) hinterlegt. | „Zugestellt“ |
| `Returning to sender` | Nicht abgeholt, Empfänger unbekannt, Annahme verweigert oder beschädigt: Rücksendung an den Absender. | Je nach Ereigniscode des Scans |

Steht `Pending` oder `Ready for processing` länger als eine Woche unverändert, frage beim Absender nach, ob das Paket tatsächlich verschickt wurde. Sendungen zwischen australischen Bundesstaaten laufen über große Verteilzentren wie Redbank (QLD), Chullora (NSW) oder Sunshine West (VIC) – ein Scan weit abseits von Start- und Zielort ist normal. Bei Paketen aus dem Ausland nach Australien zeigt `It's on its way` die Ankunft in Australien an und bleibt stehen, bis die Sendung auf das Zustellfahrzeug geladen wird.

In Australien stellen Zusteller in der Regel Montag bis Freitag zwischen 7:00 und 17:00 Uhr zu; kommt ein Paket bis 17:00 Uhr nicht an, wird es meist am nächsten Werktag bevorzugt ausgeliefert. Bei `It's coming today` verschickt Australia Post in manchen Gebieten eine SMS mit einem 2-Stunden-Zeitfenster.

`Contact sender` bedeutet, dass die Sendung wegen unvollständiger oder falscher Angaben oder nach 10 Werktagen ohne Abholung zurückging; `Returned` bestätigt die Rückgabe an den Absender, den du für einen Neuversand kontaktierst.

## Verpasste Zustellung, Postfiliale und Parcel Lockers in Australien

Die folgenden Regeln gelten für den Empfang innerhalb **Australiens**:

Ist niemand zu Hause und kein sicherer Ablageort (*Safe Drop*) möglich, bringt Australia Post das Paket in eine nahegelegene Postfiliale oder – sofern deine Mobilnummer auf der Sendung steht – in einen *Parcel Locker* (Zugangscode folgt per Nachricht). MyPost-Nutzer erhalten die Benachrichtigung per E-Mail, App oder SMS; andere bekommen mitunter noch eine *Sorry We Missed You*-Karte. Siehe auch [Zustellung verpasst](guide:missed-delivery).

Eine australische Postfiliale bewahrt das Paket **10 Werktage** lang auf, bevor es zurückgeht. Eine erneute Haustürzustellung aus einer Filiale bietet Australia Post nicht an; die Weiterleitung an eine andere Postfiliale kostet **7,55 AU$** zahlbar bei Abholung (das Transferformular nennt noch 7,20 AU$). Weißt du nicht, warum das Paket in dieser Filiale gelandet ist, kannst du über das Zustellproblem-Formular um Gebührenerlass bitten. Bringe zur Abholung einen Lichtbildausweis mit – oder zwei Ausweise ohne Foto, von denen einer deine Adresse zeigt. Eine andere Person kann mit einer Vollmacht (aus der App, per Formular oder auf der Karte) und eigenem Ausweis abholen.

*Parcel Lockers* sind kostenlos und an den meisten Standorten rund um die Uhr (24/7) zugänglich. Hole das Paket innerhalb von **2 Werktagen** (48 Stunden ohne Wochenenden und gesetzliche Feiertage) per Zugangscode oder QR-Code der App ab; danach wird es – nach einer Erinnerungs-E-Mail 24 Stunden zuvor – für 10 Werktage in eine nahegelegene Postfiliale gebracht.

Mit einem kostenlosen MyPost-Konto lässt sich ein zugeordnetes Paket bis zum Beladen des Zustellfahrzeugs an eine andere Adresse umleiten (plane einen zusätzlichen Werktag ein) und *Safe Drop* bis zu einer Stunde vor Zustellung auswählen.

## Keine Aktualisierung, Verspätung oder als zugestellt gemeldet

Bleiben neue Scans einige Tage aus, bewegt sich das Paket laut Australia Post dennoch weiter; die meisten verspäteten Sendungen kommen innerhalb von **5 Werktagen** nach dem erwarteten Termin an. Mehr unter [Sendungsverfolgung aktualisiert sich nicht](guide:tracking-not-updating).

Australia Post verweist Empfänger zuerst an den Absender, um Nummer und Adresse abzugleichen und eine Nachforschung anzustoßen. Hilft das nicht, nutze nach **10 Werktagen** über dem voraussichtlichen Lieferdatum das Onlineformular für verspätete oder vermisste Sendungen (kostenloses MyPost-Konto erforderlich). Sendungen aus Übersee können bis zu **15 Tage** beim australischen Zoll liegen – länger, falls Einfuhrabgaben anfallen; bei längerer Verwahrung durch Zoll oder Quarantäne schreiben die Behörden den Empfänger an. Nachforschungen zu internationalen Verzögerungen müssen über die Aufgabepost des Absenderlands gestellt werden. Siehe auch [Paket beim Zoll](guide:customs).

**Status zugestellt, aber Paket fehlt.** Prüfe geschützte Stellen am Grundstück, Haushaltsmitglieder und die Hausverwaltung, sieh dir in der App oder im Onlineformular das Zustellfoto an und reiche dann eine Meldung ein; zeigt das Foto die korrekte Ablage an deiner Adresse und die Sendung ist verschwunden, erstatte Anzeige bei der Polizei. Beschädigte Pakete in Australien bringst du samt Verpackung zur Schadensprüfung in eine dafür ausgestattete Postfiliale. Die meisten Tarife beinhalten bis zu **100 AU$** Haftung bei Verlust oder Beschädigung (bei Inlandsverlust in der Regel an den Absender, bei Beschädigung an den Empfänger), geltend zu machen binnen **6 Monaten** ab Einlieferung; bei Onlinekäufen wende dich zuerst an den Händler. Mehr unter [zugestellt, aber nicht erhalten](guide:delivered-not-received).

## Australia Post Kontakt und Hilfe

Offizielle Kontaktwege von Australia Post:

- **Telefon:** `13 76 78` (`13POST`) innerhalb Australiens oder `+61 3 8340 7239` aus dem Ausland, Montag bis Freitag von 8:00 bis 18:00 Uhr außer an landesweiten australischen Feiertagen.
- **Online:** Formulare für verspätete/vermisste Sendungen und Zustellprobleme sowie Chatbot und Live-Chat auf [auspost.com.au/help](https://auspost.com.au/help).
- **Vor Ort in Australien:** über 4.100 Postfilialen nehmen Beschwerden entgegen.

StarTrack, die Kurier- und Frachtsparte von Australia Post, hat eine eigene Rufnummer (`13 23 45`) und eigene Formulare; ihre Sendungen laufen über dasselbe Trackingnetz und erscheinen ebenfalls in der AusPost-App.

> Australia Post fragt niemals unaufgefordert per Anruf, SMS oder E-Mail nach persönlichen Daten, Bankdaten oder unerwarteten Zahlungen (etwa angeblichen Nachzustellgebühren oder Postleitzahl-Bestätigungen) – auch dann nicht, wenn als Absenderkennung *AusPost* erscheint. Leite verdächtige Nachrichten an `scams@auspost.com.au` weiter und lies [Gefälschte Paket-SMS erkennen](guide:delivery-scam-texts).

## Australia Post Pakete mit Peek verfolgen

[Peek](/) erkennt Australia Post sofort bei 21- und 23-stelligen Artikel-IDs sowie bei internationalen EMS-, Paket- und Trackingbrief-Nummern auf `AU` (`C…AU`, `E…AU`, `L…AU`) mit gültiger Prüfziffer und zeigt „Erkannter Paketdienst“ samt „Ändern“. Frachtnummern (einschließlich StarTrack), 22-stellige Prepaid-Barcodes und 25-stellige `R`-Codes sind für sich genommen mehrdeutig: Beim Hinzufügen fragt Peek die passenden Dienste ab („Tracking-Dienste werden geprüft…“) und meldet „Australia Post hat dieses Paket.“, sobald der Tracker antwortet – andernfalls lässt sich Australia Post über „Ändern“ unter „Passt zu dieser Nummer“ auswählen. Übrige Postnummern auf `AU` legt Peek zunächst als Postsendung an und fragt Australia Post an. Links von `auspost.com.au` mit `id=` (auch `#/search?id=`) oder `/mypost/track/details/` ordnen jedes Format direkt Australia Post zu.

Lehnt die Schnittstelle von Australia Post Peeks direkte Anfrage ab, öffnet ein Browser auf Peeks Server `auspost.com.au`; da externe Trackingdienste kaum Verlaufsdaten von Australia Post führen, bleibt der Verlauf knapp, falls auch der Browser abgewiesen wird.

Peek zeigt den Status, jeden Scan mit Ort auf der Karte, den Zustellzeitpunkt, das Zielland sowie den Namen der Postfiliale oder des *Parcel Locker*, in dem die Sendung abholbereit liegt; Empfängername, Straßenadresse und Zustellfotos lässt Peek weg (bei einer Zustellung steht nur „Zugestellt“). Eigene Scans von Australia Post tragen ihren australischen Zeitzonenversatz – umgerechnet in die Zeitzone deines Geräts („Uhrzeiten in deiner Zeitzone: …“ unter dem Verlauf auf `peektracker.com` sowie in der iPhone- und Android-App). Solange das Paket unterwegs ist, zeigt Peek das voraussichtliche Lieferdatum oder die Datumsspanne von Australia Post, bis sie verstrichen ist – und am Liefertag das Zeitfenster, solange es noch nicht verstrichen ist – und blendet die Schätzung nach einem gescheiterten Zustellversuch oder einem Problem aus, ebenso sobald das Paket zugestellt, zurückgesandt oder abholbereit ist. Liefert Australia Post kein Datum (etwa bei Einwurf in einen Straßenbriefkasten oder handschriftlicher Adresse), kann das eigene Webtool von Australia Post anhand der Postleitzahl dennoch eines schätzen. Umleitungen oder Fachwechsel kann Peek nicht vornehmen.

Kennt Australia Post die Nummer noch nicht, zeigt Peek „Warten auf Australia Post“; bleibt sie zwei Tage lang überall unbekannt, erscheint „Auch nach zwei Tagen kennt der Paketdienst diese Nummer nicht. Prüfe sie, wähle einen anderen Paketdienst oder lösche das Paket.“, und nach vier Tagen ohne neuen Scan auf dem Transportweg blendet Peek „Hol dir Hilfe bei Australia Post“ ein.

Solange du das Paket geöffnet hast oder am Zustelltag Benachrichtigungen aktiviert sind, prüft Peek bis zu alle 10 Minuten – und ab 6:00 Uhr Ortszeit (oder eine halbe Stunde vor Beginn eines angekündigten Lieferfensters) am Tag der Zustelltour bis zu alle 2 Minuten beziehungsweise alle 5 Minuten bei Browserabfrage, solange der Tracker von Australia Post antwortet (über externe Trackingdienste allein: stündlich, alle 3 Stunden nach einem ruhigen Tag und am Zustelltag bei geöffnetem Paket oder aktiven Mitteilungen bis zu alle 15 Minuten; übernimmt im Ausland die Zielpost, folgt Peek deren Takt); ansonsten sowie nach zwei Tagen ohne Neuigkeiten (oder 12 Stunden bei „Etikett erstellt“ oder „Beim Paketdienst eingegangen“) stündlich. Benachrichtigungen gibt es für „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“. Im Ausland aufgegebene Postsendungen nach Australien übernimmt Australia Post in Peek, sobald dort eigene Fortschritte vorliegen.

## Häufige Fragen zur Australia Post Sendungsverfolgung

### Was bedeutet Pending bei Australia Post?

Das Paket wurde noch nicht im Netz von Australia Post gescannt – etwa weil es erst angemeldet oder in Australien in einen Straßenbriefkasten eingeworfen wurde. Meist folgt innerhalb von 24 bis 48 Stunden der erste Scan. Auch alte Nummern ohne Aktivität seit über 3 Monaten zeigen `Pending`.

### Wie lange bleibt ein Paket im Parcel Locker von Australia Post?

**2 Werktage** (48 Stunden ohne Wochenenden und Feiertage). Danach bringt Australia Post die Sendung in die nächstgelegene Postfiliale mit Abholservice, wo sie **10 Werktage** lagert, bevor sie zurückgeschickt wird.

### Haben alle Sendungen von Australia Post eine Sendungsverfolgung?

Nein: *International Post Registered* und *International Economy* (Luft- und Seeweg) enthalten standardmäßig kein Tracking. Bei Sendungen aus dem Ausland nach Australien hängt die Sendungsverfolgung vom gewählten Tarif der Aufgabepost oder des Kurierdienstes ab.

:::sources
- [Australia Post: Paketverfolgung](https://auspost.com.au/mypost/track) – Eingabe von bis zu 10 Sendungsnummern gleichzeitig
- [Australia Post: Lost tracking number](https://auspost.com.au/help/parcel-tracking/tracking-support/lost-tracking-number) – Fundorte der Nummer, keine Namenssuche und erneuter Versuch nach 24 Stunden
- [Australia Post: Help with tracking](https://auspost.com.au/help/parcel-tracking/tracking-support/help-with-tracking) – Artikel-ID, Sortierzentren und letzte Ereignisse
- [Australia Post: What your status means](https://auspost.com.au/help/parcel-tracking/tracking-support/what-your-status-means) – Pending (24–48 Std. sowie über 3 Monate), Zustellversuch, Sorry We Missed You-Karte, It's on its way aus Übersee und 2-Stunden-SMS
- [Australia Post: Tracking service](https://auspost.com.au/personal/receiving/tracking-service) – Verfolgung ohne Konto und Verteilzentren Redbank, Chullora und Sunshine West
- [Australia Post: Taking longer than expected](https://auspost.com.au/help/parcel-tracking/taking-longer-than-expected) – Pending und Ready for processing über eine Woche, 5 Werktage Nachlauf
- [Australia Post: Delays and disruptions](https://auspost.com.au/help/parcel-tracking/delays-and-disruptions) – Bedeutung von Possible delay
- [Australia Post: Return to sender](https://auspost.com.au/help/delivered-parcel/return-to-sender) – Returning to sender, Returned und Contact sender nach 10 Werktagen
- [Australia Post: Missed parcel deliveries](https://auspost.com.au/personal/receiving/parcel-deliveries/missed-parcel-deliveries) – Postfiliale oder Locker nach Fehlversuch, Ausweispflicht, Vollmacht und Filialtransfer für 7,55 AU$
- [Australia Post: Requested delivery but parcel at Post Office](https://auspost.com.au/help/delivered-parcel/parcel-at-post-office) – 10 Werktage Lagerfrist, keine Zweitzustellung ab Filiale und Gebührenerlass über Zustellbeschwerde
- [Australia Post: Transfer to another post office](https://helpandsupport.auspost.com.au/s/products-and-services?transfertoPO) – Transferformular mit Angabe von 7,20 AU$
- [Australia Post: 24/7 Parcel Lockers](https://auspost.com.au/help/collection-delivery/parcel-lockers) – 24/7 an den meisten Standorten, 48 Stunden ohne Wochenenden/Feiertage, Code und 24-Stunden-Erinnerung
- [Australia Post: Redirect parcel or change address](https://auspost.com.au/help/parcel-tracking/redirect-parcel-change-address) – Umleitung über MyPost vor Verladung und zusätzlicher Werktag
- [Australia Post: Leave in a safe place (Safe Drop)](https://auspost.com.au/help/collection-delivery/safe-drop) – Safe Drop bis 1 Stunde vorher und Zustellzeiten 7:00 bis 17:00 Uhr werktags
- [Australia Post: AusPost app](https://auspost.com.au/personal/receiving/australia-post-app) – digitale Benachrichtigungen, Karten ohne Kontoverknüpfung und 2-Stunden-Zeitfenster
- [Australia Post: No recent update](https://auspost.com.au/help/parcel-tracking/no-recent-update) – fehlende Scans, 5 Werktage, Nachforschung ab 10 Werktagen und Telefonzeiten
- [Australia Post: Lost or late items](https://auspost.com.au/personal/receiving/parcel-deliveries/lost-late-items) – Online-Nachforschungsformular und MyPost-Konto
- [Australia Post: Customs](https://auspost.com.au/help/parcel-tracking/international-parcel/customs) – bis zu 15 Tage beim australischen Zoll, Schreiben der Behörden und Nachforschung über die Aufgabepost
- [Australia Post: International tracking](https://auspost.com.au/help/parcel-tracking/international-parcel/tracking) – Tarife ohne Standard-Tracking (International Post Registered, International Economy) und eingehendes Tracking
- [Australia Post: Delivered status but parcel not received](https://auspost.com.au/help/delivered-parcel/parcel-not-received) – Prüfung vor Ort, Zustellfoto und Polizeimeldung
- [Australia Post: Damaged parcel](https://auspost.com.au/help/delivered-parcel/damaged-parcel) – Schadensaufnahme in der Postfiliale
- [Australia Post: Compensation](https://auspost.com.au/personal/receiving/parcel-deliveries/compensation) – bis zu 100 AU$ Entschädigung, Empfangsberechtigte, 6-Monats-Frist und Vorrang des Händlers
- [Australia Post: Complaints and feedback](https://auspost.com.au/about-us/corporate-information/complaints-and-feedback) – Rufnummern 13 76 78, +61 3 8340 7239, StarTrack 13 23 45 und Hilfe in Postfilialen
- [Australia Post: Help & support](https://auspost.com.au/help) – Onlineformulare und Chat
- [Australia Post: Expected delivery date](https://auspost.com.au/help/parcel-tracking/tracking-support/expected-delivery-date) – Lieferdatum oder -spanne, Fälle ohne Datum und Postleitzahl-Schätzung
- [Australia Post: StarTrack tracking](https://auspost.com.au/help/startrack/tracking) – StarTrack-Verfolgung im selben System und in der AusPost-App
- [Australia Post: Scam alerts](https://auspost.com.au/about-us/about-our-site/online-security-scams-fraud/scam-alerts) – gefälschte AusPost-Nachrichten und Meldung an scams@auspost.com.au
- [Australia Post: About us](https://auspost.com.au/about-us) – über 4.100 Postfilialen in Australien
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – erkannte Nummern- und Linkmuster, Schnittstelle und Browserabruf, Zeitfenster und Lieferdatum, Zeitzonen und Datenschutz
:::
