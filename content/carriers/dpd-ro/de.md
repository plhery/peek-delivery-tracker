---
title: DPD Romania Sendungsverfolgung: Status & Smart Control
description: DPD-Romania-Paket verfolgen: 11-stellige AWB-Nummer, englische und rumänische Status, Smart Control, Locker, Zoll und Kontakt.
slug: dpd-romania-sendungsverfolgung
published: 2026-11-23
updated: 2026-11-23
---

DPD Romania (Dynamic Parcel Distribution S.A.) ist der Paketdienst der Geopost-Gruppe in Rumänien, dort seit 2008 aktiv, mit Sitz in Mogoșoaia (Ilfov). Jedes Paket hat eine eigene Frachtbriefnummer (*waybill* bzw. AWB): Verfolge sie auf tracking.dpd.ro (auch auf Englisch verfügbar), über den Smart-Control-Link in DPDs Liefer-SMS oder oben im Feld.

## DPD-Romania-Sendungsnummer: wo sie steht

DPD nennt sie Frachtbriefnummer (*waybill number*) oder AWB. Die Seiten von DPD geben keine Längenregel an; die von Peek für DPD Romania erkannten AWB-Nummern haben 11 Ziffern und beginnen mit 8:

| Nummer (erfunden) | Was es ist |
| --- | --- |
| `84927163504` | 11 Ziffern beginnend mit 8: eine DPD-Romania-AWB. Das Format passt auch zu [GLS Germany](carrier:gls-de), [GLS France](carrier:gls-fr), [GLS Switzerland](carrier:gls-ch), GLS Austria, PPL und weiteren. |
| `01204819273645` | 14 Ziffern: Paketnummer anderer DPD-Gesellschaften wie [DPD Germany](carrier:dpd-de), [DPD UK](carrier:dpd-uk), DPD France oder [DPD Portugal](carrier:dpd-pt). Peek verfolgt DPD Romania nur über die 11-stellige AWB. |

Schau in die Versandmail des Shops oder auf deine Bestellseite. Die DPD-App bietet ein Empfänger-Dashboard; du meldest dich mit einem per SMS oder E-Mail gesendeten Code an. Nennt der Shop stattdessen [Cargus](carrier:cargus), [FAN Courier](carrier:fan-courier) oder Sameday, gehört die Nummer dorthin. Gar keine Nummer? Sieh nach unter [Sendungsnummer finden](guide:find-tracking-number).

## Was die Statusmeldungen von DPD Romania bedeuten

Der DPD-Tracker antwortet auf Rumänisch oder Englisch; rumänische Scans gibt es in einer älteren Kurzform und als neuere Sätze. In der üblichen Reihenfolge:

| DPD schreibt | Was das für dich bedeutet |
| --- | --- |
| `Shipment data received`, `Receptionare informatii colet`, `Expedierea ta a fost înregistrată de către expeditor.` | Der Absender hat die AWB erstellt. DPD hat die Daten, aber noch nicht das Paket. |
| `Expedierea nu a putut fi preluată de către curierul DPD.` | Der Kurier konnte das Paket beim Absender nicht abholen; es folgt ein Grund. |
| `Courier Pick-up`, `Expedierea ta a fost preluată de la expeditor de către curierul DPD.` | Ein DPD-Kurier hat das Paket beim Absender abgeholt. |
| `Received in Office`, `Receptie la sediu` | Eine DPD-Filiale hat es übernommen, vom Kurier oder am Schalter. |
| `Departure Scan`, `Arrival Scan`, `Intrare in depozit`, `Expedierea ta a fost recepționată în depozitul DPD.`, `Expedierea ta a fost procesată în depozitul DPD.` | Ausgang, Eingang oder Bearbeitung in einem DPD-Depot. |
| `Expedierea ta se află în tranzit spre depozitul de destinație.` | Auf dem Weg zum zustellenden Depot. |
| `Expedierea ta a ajuns în depozitul de destinație.` | Im zustellenden Depot angekommen: Als Nächstes folgt die Auslieferung. |
| `Predict`, `Predict – s-au trimis informațiile cu privire la intervalul orar de livrare.` | DPD hat dir das Zustellzeitfenster gesendet. Eine Nachricht, kein Scan. |
| `Out for Delivery`, `Iesire in livrare`, `Expedierea ta a fost preluată spre livrare de către curierul DPD.` | Beim Kurier in Zustellung. |
| `Livrare nereusita`, `Expedierea ta nu a putut fi livrată.` | Nicht zugestellt. Es folgt der Grund, etwa falsche Adresse oder Annahmeverweigerung. |
| `Expedierea ta a fost returnată în depozitul DPD.` | Nach einer fehlgeschlagenen Tour zurück im Depot, keine Rücksendung. |
| `Delivered`, `Livrat` | Zugestellt. Der Tracker nennt den Namen der Person, die es angenommen hat. |
| `Retur catre expeditor` | Rückweg zum Absender unter einer in der Notiz genannten Rücksende-AWB. |
| `Retur livrat catre expeditor` | Wieder beim Absender zugestellt. |
| `Canceled`, `Expedierea ta a fost anulată.` | Die AWB wurde storniert: Frag beim Shop nach. |

`Shipment data received` zeigt oft das erwartete Lieferdatum. Scans aus ausländischen DPD-Depots nennen Depot und Land.

## DPD-Zustellung in Rumänien: Smart Control, Filialen und Locker

**Wann.** DPD Standard liefert landesweit in Rumänien binnen 1 bis 2 Werktagen. Nachnahme zahlst du dem Kurier bar oder in Städten per Karte.

**Smart Control.** Vor der Zustellung sendet DPD eine SMS mit Lieferdatum und kostenlosem Smart-Control-Link. Darüber kannst du die Adresse bestätigen oder ändern, sehen wo dein Paket ist, es an ein DPD-Lager, eine Filiale, einen Locker oder DPD Shop umleiten oder auf den nächsten Werktag verschieben. Innerhalb derselben Stadt ist die Umleitung kostenlos. Die Übergabe erfolgt kontaktlos (keine elektronische Unterschrift mehr nötig); DPD kann deine Identität per Ausweis und PIN aus SMS oder E-Mail prüfen.

**Verpasste Zustellung.** DPD unternimmt zwei Versuche an aufeinanderfolgenden Werktagen. Nach dem zweiten Versuch hinterlässt DPD eine Nachricht und lagert das Paket kostenlos 5 Kalendertage an einem besetzten DPD-Standort, während derer du einen letzten Versuch vereinbaren kannst. Express- und Livrare-specială-Pakete erhalten nur einen Versuch und gehen dann zurück. DPD legt nicht in Briefkästen ein. Verweigerst du Annahme oder Nachnahmezahlung, startet die Rücksendung noch am selben Tag ohne Lagerung.

**Filialen, Locker und Parcel Shops.** Abholfristen laut DPD:

- DPD-Filiale: 7 Kalendertage; Zahlung bar, per Karte oder Pay by Link.
- DPD-Locker: 3 Kalendertage (72 Stunden ab Benachrichtigung laut AGB); Außen-Locker 24/7 offen; Karte oder Pay by Link.
- DPD Parcel Shop: 5 Kalendertage; Pay by Link oder Karte (keine Karte an Pay-Point-Standorten).

Nach Ablauf der Frist geht das Paket binnen 5 Werktagen an den Absender zurück (10 Werktage bei internationalen Paketen).

## DPD-Romania-Paket verspätet, verloren, beschädigt oder beim Zoll

Für Erstattung oder Neuversand wende dich an den Shop, der den Vertrag mit DPD hält.

**Kein Update.** Bleibt der Status lange gleich oder verpasst das Paket seinen Termin, bittet DPD um Kontakt an client@dpd.ro oder 031 824 90 90. Siehe [Tracking aktualisiert sich nicht](guide:tracking-not-updating).

**Zugestellt, aber nicht erhalten.** Der DPD-Tracker nennt die Person, die das Paket angenommen hat: Frag zuerst dort, dann kontaktiere DPD und den Shop. Mehr unter [zugestellt, aber nicht erhalten](guide:delivered-not-received).

**Zurückgesendet.** `Retur catre expeditor` heißt, das Paket geht zurück. Bitte den Shop um erneuten Versand. Siehe [Zurück an Absender](guide:returned-to-sender).

**Beschwerden.** Sowohl Absender als auch Empfänger können binnen 6 Monaten ab Versand per DPD-Formular reklamieren: per E-Mail an reclamatii@dpd.ro, online, an einem besetzten DPD-Standort oder per Einschreiben. DPD entscheidet binnen 3 Monaten und zahlt binnen 15 Tagen nach Entscheidung. Bei Verlust oder Schaden ohne Wertangabe oder Nachnahme zahlt DPD das 5-Fache des Versandpreises. Binnen eines Jahres nach Versand kannst du dich zudem an die Regulierungsbehörde ANCOM oder an ein Gericht wenden. Siehe [verlorene Pakete](guide:lost-parcel).

**Zoll.** Online-Käufe von außerhalb der EU unterliegen seit dem 1. Juli 2026 einem EU-Zoll von 3 € je Produktart auf Pakete bis 150 € und seit dem 1. November 2026 einer Unions-Bearbeitungsgebühr von 2 € je Produktart unabhängig vom Wert. Die Seiten von DPD Romania nennen nicht, wie DPD diese weitergibt: Frag den Shop, ob sie enthalten waren. DPDs Mitteilung vom 22. Dezember 2025 erklärt zudem Rumäniens feste Gebühr von 25 RON pro Nicht-EU-Paket unter 150 €, eingeführt am 1. Januar 2026: Verkäufer, Absender oder Marktplatz zahlen sie, und DPD zieht sie dort ein. Siehe [Paket beim Zoll](guide:customs).

## DPD Romania Kontakt und Kundenservice

Peek kann DPD oder den Shop nicht für dich kontaktieren. Laut Kontaktseite von DPD Romania:

- **Inlandspakete Rumänien:** 031 824 90 90, Montag bis Freitag 8:30 bis 16:30 Uhr (rumänische Zeit). Anfragen an client@dpd.ro.
- **Internationale Pakete:** 031 824 90 32 oder international@dpd.ro, zu denselben Zeiten.
- **Reklamationen:** reclamatii@dpd.ro oder das Beschwerdeformular.
- **Post:** Dynamic Parcel Distribution S.A., Str. Buiacului nr. 2, LogIQ Logistic Park, Corp A (A1-A2), 077135 Mogoșoaia, Ilfov.

DPD gibt nicht an, ob Mitarbeitende Englisch oder Deutsch sprechen.

> DPD Romania warnt vor gefälschten SMS und E-Mails im Namen von DPD mit Links zu Nachahmerseiten, die Zahlungen oder Kartendaten fordern. DPD Romania fragt niemals per SMS oder E-Mail nach Bankdaten. Öffne den Link nicht, melde die Nachricht als Spam und informiere DPD. Siehe [Betrugs-SMS zu Paketen](guide:delivery-scam-texts).

## Ein DPD-Romania-Paket mit Peek verfolgen

Füge die 11-stellige AWB in [Peek](/) ein. Da weitere Paketdienste dieses Format nutzen, fragt Peek nach deinem Land (Geräteregion oder im Web dein Verbindungsland) die wahrscheinlichen ab. In Rumänien fragt es zuerst bei DPD Romania nach und zeigt „DPD Romania hat dieses Paket.“, sobald DPD sie kennt. Anderswo fragt es zuerst die anderen Paketdienste und Tracking-Dienste, dann DPD Romania, und nennt DPD Romania, wenn DPD die Nummer kennt. Siehst du „noch nicht gefunden · Peek prüft weiter“, tippe auf „Ändern“ und wähle DPD Romania (auch „DPD România“, „DPD RO“ oder „Dynamic Parcel Distribution“), oder nutze „Paketdienst ändern“ bei einem gespeicherten Paket. Füge sie in jedem Fall ein: Peek verfolgt über 4500 Paketdienste. Links von tracking.dpd.ro oder services.dpd.ro/tracking mit `shipmentNumber=` sowie dpdgroup.com/ro/ oder dpd.com/ro/ mit `parcelNumber=` funktionieren ebenfalls.

Peek ruft DPDs englisches Tracking ab und zeigt den Status, den Verlauf in DPDs Wortlaut mit Ort (und Land) jedes Scans, das erwartete Lieferdatum unterwegs, mit dem Zeitfenster, wenn DPDs Predict-Nachricht eines nennt, sowie die Zustellzeit. Empfängername, Unterschrift und Rücksende-AWB bleiben außen vor; Gewicht und Absender liefert DPD nicht.

Übergibt DPD ein Paket für die letzte Meile an ACS Courier (z. B. nach Zypern), nennt es ACS und die ACS-Nummer. Hat diese Nummer zehn Ziffern wie ACS-Belege, fragt Peek mit ihr bei ACS nach und wechselt zu ACS, sobald ein mindestens ebenso aktueller Scan vorliegt. Bis dahin oder bei anderem Format folgt Peek DPD, und die Karte nennt ACS samt Nummer bereits.

Solange das Paket in Peek geöffnet ist, oder an seinem Zustelltag mit aktiven Benachrichtigungen, prüft Peek es bis zu alle 10 Minuten, und alle 2 Minuten an dem Tag, an dem DPD es in die Zustellung scannt: ab 6:00 Uhr rumänischer Zeit oder, wenn DPDs Predict-Nachricht ein Zeitfenster nennt, ab einer halben Stunde vor dessen Beginn, je nachdem, was später ist. Benachrichtigungen wählst du unter „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“. Nach zwei Tagen ohne Neuigkeiten (12 Stunden bei „Etikett erstellt“ oder „Beim Paketdienst eingegangen“) prüft Peek stündlich, und nach sieben Tagen ohne Neuigkeiten bei „Etikett erstellt“ alle 6 Stunden. Bleibt das Paket vier Tage ohne neuen Eintrag bei „Etikett erstellt“, „Beim Paketdienst eingegangen“ oder „Unterwegs“, zeigt Peek „Keine Aktualisierung seit …“ mit dem Tag dieses Eintrags und einen Link „Hol dir Hilfe bei DPD Romania“. Zum Ändern der Zustellung nutze Smart Control.

## Fragen zur DPD-Sendungsverfolgung in Rumänien

### Wie lange liefert DPD innerhalb Rumäniens?

1 bis 2 Werktage mit DPD Standard. Für Orte mit festen Anfahrtstagen sehen DPDs Bedingungen bis zu 7 Kalendertage vor.

### Wie lange lagert DPD ein Paket in Filiale oder Locker?

7 Kalendertage in einer Filiale, 5 im Parcel Shop, 3 im Locker und 5 an einem DPD-Standort nach zwei verpassten Zustellungen. Danach geht es an den Absender zurück.

### Kann ich ein DPD-Romania-Paket auf Englisch verfolgen?

Ja. Der DPD-Tracker hat eine englische Oberfläche, und Peek zeigt DPDs englische Wortlaute.

### Wie lautet die Telefonnummer von DPD Romania?

031 824 90 90 für Inlandspakete und 031 824 90 32 für internationale Pakete, Montag bis Freitag 8:30 bis 16:30 Uhr (rumänische Zeit).

:::sources
- [DPD Romania: Where is my parcel?](https://www.dpd.com/ro/en/faq/unde-este-coletul-meu/) – AWB-Nummer, Tracker
- [DPD Romania: My parcel has not arrived yet](https://www.dpd.com/ro/en/faq/coletul-meu-nu-a-sosit-inca-ce-este-de-facut/) – englischer Tracker, wann anrufen
- [DPD Romania: How can I change the place or day of delivery?](https://www.dpd.com/ro/en/faq/cum-pot-schimba-locul-sau-ziua-livrarii/) – Umleitung
- [DPD Romania: Contact customer service](https://www.dpd.com/ro/en/faq/contact-serviciul-clienti/) – Telefon, Servicezeiten
- [DPD Romania: Smart Control](https://www.dpd.com/ro/en/smart-control/) – SMS, kostenlose Umleitung, kontaktlose Zustellung
- [DPD Romania: Home](https://www.dpd.com/ro/en/) – Smart-Control-Optionen
- [DPD Romania: DPD OOH](https://www.dpd.com/ro/en/dpd-ooh/) – Abholfristen, Zahlung
- [DPD Romania: Contact](https://www.dpd.com/ro/en/support/contact-dpd/) – Telefonnummern, E-Mails, Zeiten
- [DPD Romania: National services](https://www.dpd.com/ro/en/parcel-shipping/national-services/) – 1 bis 2 Werktage, Kartenzahlung
- [DPD Romania: Payment](https://www.dpd.com/ro/en/payment/) – Nachnahme
- [DPD Romania: Mobile application](https://www.dpd.com/ro/en/mobile-application-dpd/) – App, Empfänger-Dashboard
- [DPD Romania: Termeni și condiții](https://www.dpd.com/ro/ro/termeni-si-conditii/) – AWB, PIN, Versuche, Lagerung, Annahmeverweigerung, Liefer- und Rücksendefristen, ANCOM
- [DPD Romania: Resolution mechanism of complaints](https://www.dpd.com/ro/en/support/tips-and-tricks-dpd-romania/resolution-mechanism-of-complaints/) – Beschwerden, Entschädigung
- [DPD Romania: About the company](https://www.dpd.com/ro/en/about-company/) – seit 2008, Geopost
- [DPD Romania: Legal and copyright notice](https://www.dpd.com/ro/en/support/legal-notice/) – Gesellschaft, Hauptsitz
- [DPD Romania: 25 RON fee for non-EU parcels under EUR 150](https://www.dpd.com/ro/en/2025/12/22/25-ron-fee-for-non-eu-parcels-under-eur-150-2/) – rumänische Paketgebühr
- [DPD Romania: Campanie de informare phishing](https://www.dpd.com/ro/ro/2025/08/01/campanie-de-informare-phishing/) – gefälschte SMS und E-Mails
- [Europäische Kommission: EU-Zollreform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 3 € Zoll je Produktart bis 150 € seit 1. Juli 2026, 2 € Unions-Bearbeitungsgebühr seit 1. November 2026
- [Europäische Kommission: Leitfaden zu 3-€-Zoll und Unions-Bearbeitungsgebühr (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 2 € je Produktart unabhängig vom Wert
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – was Peek liest, DPD-Romania-Wortlaute und Übergabe an ACS
:::
