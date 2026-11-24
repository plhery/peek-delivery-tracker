---
title: SkyPostal Sendungsverfolgung: SKAMZUS, SP-Codes & Hilfe
description: Verfolge ein SkyPostal-Paket aus den USA nach Lateinamerika: SKAMZUS- und SP...BR-Codes, Miami-Hub, Zollcodes (CEN, HTI, DEL) und Support-E-Mails.
slug: skypostal-sendungsverfolgung
published: 2026-11-24
updated: 2026-11-24
---

**SkyPostal** ist ein in Miami ansässiges US-Inlands- und Lateinamerika-Paketnetzwerk, gegründet von Albert P. und A.J. Hernandez ([skypostal.com](https://www.skypostal.com/)). Über seinen wichtigsten US-Exporthub in Miami, Florida, befördert SkyPostal E-Commerce-Pakete großer Händler und Marktplätze — darunter Amazon, Temu und eBay — nach Brasilien, Mexiko, Kolumbien, Peru, Ecuador, Chile und Costa Rica, übernimmt Export- und Importverzollung und übergibt die Zustellung bis zur Haustür an regionale Partner in Lateinamerika. Du kannst eine Sendung auf [tracking.skypostal.com](https://tracking.skypostal.com/) prüfen oder deine SkyPostal-Nummer beziehungsweise deinen Link oben einfügen.

## SkyPostal Sendungsnummern (`SKAMZUS` & `SP...BR`)

SkyPostal nutzt je nach Absender und Zielland verschiedene internationale Formate — am häufigsten 17-stellige `SKAMZUS`-Codes (weit verbreitet für Amazon-US-Exporte nach Lateinamerika) sowie 15-stellige `SP...BR`-Codes für Brasilien:

| Format (erfunden) | Was es ist |
| --- | --- |
| `SKAMZUS0482917365` | 17-stelliger SkyPostal-Code für Amazon-US-Exporte (`SKAMZUS` + 10 Ziffern). |
| `SP04829173605BR` | 15-stelliger SkyPostal-Paketcode für Brasilien (`SP` + 11 Ziffern + `BR`). |

Bei manueller Auswahl sucht der SkyPostal-Tracker auch nach anderen alphanumerischen Händler-Frachtbriefen und Partnerreferenzen mit 8 bis 40 Zeichen und mindestens einer Ziffer. Siehe den Leitfaden zu [Sendungsnummern-Formaten](guide:tracking-number-formats).

## Bedeutung der SkyPostal Statusmeldungen

SkyPostal vergibt für jeden Schritt einen 3-Buchstaben-Ereigniscode — von elektronischer Voranmeldung über den Hub in Miami, Flüge und Einfuhrzoll bis zu weitergeleiteten Scans des örtlichen Zustellers:

| SkyPostal Code & Wortlaut | Was er für dich bedeutet | Peek zeigt |
| --- | --- | --- |
| `DRC` (`DATA RECEIVED`), `HCV` (`HS_CODE VALIDATION`), `TIC` (`TAX ID COLLECTED`), `DCO` (`DOCUMENTATION COMPLETE`) | Bestelldaten, HS-Zolltarifprüfung und Steuer-ID des Käufers vor Ankunft am SkyPostal-Hub eingegangen. | „Etikett erstellt“ |
| `RUT` (`RECEPTACLE RECEIVED IN US TRANSPORT FACILITY`), `REC` (`RECEIVED`) | Physisches Paket im SkyPostal-Exportzentrum in Miami eingegangen. | „Beim Paketdienst eingegangen“ |
| `XRS` (`X-RAY SCAN`), `PLP` (`PROVIDER LABEL PRINTED`), `INC` (`NESTED TO RECEPTACLE`), `PRO`, `MNF`, `FDP` (`FLIGHT DEPARTED`), `ATT`, `ATC` (`ARRIVED IN COUNTRY`), `DTP` (`DELIVERED FROM AIRLINE TO OPERATOR`) | In Miami abgefertigt, ausgeflogen und von der Airline an den Bodenabfertiger im Zielland übergeben (noch keine Haustürzustellung). | „Unterwegs“ |
| `CEN` (`CUSTOMS ENTRY`), `CHE` (`CUSTOMS HOLD AT CONTROL ENTITY`) | In der Einfuhrzollabfertigung im Zielland oder wartend auf Freigabe im Flughafenzolllager. | „Beim Zoll“ |
| `CCH` (`CUSTOMS CLEARED`), `TTC` (`TENDERED TO LOCAL CARRIER`), `TDP`, `PES` (`PROVIDER ENTRY SCAN`), `INT`, `PRH`, `PDH`, `SLH` (`HOLD RESOLVED`) | Verzollt, an den lokalen Zusteller in Lateinamerika übergeben und unterwegs durch örtliche Verteilzentren. | „Unterwegs“ |
| `OFD` (`OUT FOR DELIVERY`) | Heute mit dem lokalen Zustellpartner in Auslieferung. | „In Zustellung“ |
| `PIK` (`AVAILABLE FOR PICKUP`) | Bereit zur Abholung durch den Empfänger in einem lokalen Abholpunkt oder Postamt. | „Abholbereit“ |
| `CNA` (`CONSIGNEE NOT AVAILABLE`) | Zustellversuch fehlgeschlagen, da niemand zur Annahme erreichbar war. | „Zustellversuch“ |
| `HTI` (`HOLD FOR PROBLEMATIC TAX ID`), `ICA` (`INCORRECT / INCOMPLETE ADDRESS`), `DRF` (`DELIVERY REFUSED`), `PLR` (`PROVIDER LABEL REJECTED`), `INH` (`ON HOLD`) | Angehalten wegen Steuer-ID-Problem, falscher oder unvollständiger Adresse, abgelehntem Etikett oder Annahmeverweigerung. | „Problem gemeldet“ |
| `DEL` (`DELIVERED`) | Vom Zustellpartner im Zielland an den Empfänger zugestellt. | „Zugestellt“ |

Beachte, dass `DTP` (*Delivered from airline to operator*) lediglich die Übergabe des Luftpostsacks von der Fluggesellschaft an den örtlichen Abfertiger bedeutet — Peek belässt `DTP` auf „Unterwegs“ statt es als zugestellt zu werten. Interne Abrechnungscodes (`CUT`, `PAY`, `FSC`) ändern die Stufe nicht.

## Zoll in Lateinamerika, Steuer-IDs (`HTI`) und lokale Übergaben

Internationale Sendungen aus den USA nach Lateinamerika über SkyPostal unterliegen strengen Zoll- und Steuer-ID-Vorgaben:

- **Steuer-ID des Käufers (`TIC` / `HTI`):** Die SkyPostal-Scans halten fest, wann die Steuer-ID erfasst wurde (`TAX ID COLLECTED`) und wann ein Paket deswegen angehalten wird (`HOLD FOR PROBLEMATIC TAX ID`). Zeigt dein Verlauf `HTI`, wende dich an den Verkäufer oder schreibe unter Angabe deiner vollständigen Sendungsnummer an die SkyPostal-Supportadresse deines Landes. Siehe den Leitfaden zu [Paketen beim Zoll](guide:customs).
- **Lokale Zustellung:** Sobald `CCH` (*Customs cleared*) und `TTC` (*Tendered to local carrier*) erscheinen, übernimmt ein lokaler Zusteller die Lieferung, und SkyPostal listet dessen Scans samt dem Ort des Partners.

## SkyPostal-Paket verzögert, angehalten oder verschwunden

- **Stille Phase zwischen Miami und Zielzoll:** Zwischen `FDP` (`FLIGHT DEPARTED`), `ATC` (`ARRIVED IN COUNTRY`) und `CCH` (`CUSTOMS CLEARED`) pausiert die Sendungsverfolgung häufig, während Sammelsendungen die Zollprüfung durchlaufen. Siehe [stehengebliebene Sendungsverfolgung](guide:tracking-not-updating).
- **Adressfehler (`ICA`) oder Annahmeverweigerung (`DRF`):** Kontaktiere zuerst deinen Online-Marktplatz (Amazon, Temu, eBay usw.) für Ersatz oder Erstattung und schreibe an die länderspezifische SkyPostal-E-Mail mit deiner vollständigen Nummer, falls das Paket vor Ort lagert. Siehe auch [zugestellt, aber nicht erhalten](guide:delivered-not-received), [verlorenes Paket](guide:lost-parcel), [beschädigtes Paket](guide:damaged-parcel) und [zurück an den Absender](guide:returned-to-sender).

## SkyPostal Kundenservice und Länder-Supportadressen

Auf [SkyPostal Customer Service](https://www.skypostal.com/customer-service) bittet SkyPostal Empfänger, das Team **per E-Mail** zu kontaktieren und stets die vollständige Sendungsnummer sowie das Anliegen anzugeben (veröffentlichte Antwortzeit: **3–5 Werktage**):

- **Brasilien:** `Brazilcs@skypostal.com`
- **Mexiko:** `Mexicocs@skypostal.com`
- **Kolumbien:** `Colombiacs@skypostal.com`
- **Peru:** `Perucs@skypostal.com`
- **Ecuador:** `Ecuadorcs@skypostal.com`
- **Alle übrigen Länder:** `customerservice@skypostal.com`
- **US-Hauptsitz:** *SkyPostal*, 2990 NW 75th Ave, Miami, FL 33122, Vereinigte Staaten ([skypostal.com/about-us](https://www.skypostal.com/about-us)).

> Vorsicht vor gefälschten WhatsApp-Nachrichten oder SMS, die über eine unbekannte Website eine dringende Steuerzahlung zur Freigabe einer Lateinamerika-Sendung verlangen. Prüfe echte Einfuhrabgaben ausschließlich auf deiner Bestellseite beim Marktplatz oder im offiziellen Zoll- bzw. Postportal. Siehe [Betrugs-SMS zu Paketen](guide:delivery-scam-texts).

## SkyPostal mit Peek verfolgen

[Peek](/) erkennt SkyPostal automatisch als einzigen Kandidaten, sobald du einen `SKAMZUS`-Code (`SKAMZUS0482917365`, erfunden) oder einen `SP...BR`-Code (`SP04829173605BR`, erfunden) eingibst, und zeigt „SkyPostal hat dieses Paket.“, sobald der SkyPostal-Tracker Daten liefert; Peek erkennt außerdem eingefügte Links `tracking.skypostal.com/?trck_number=` und `tracking.skypostal.com/single-tracking.aspx?trck_number=`. Bei anderen Händler-Referenzen tippe beim Hinzufügen auf „Ändern“ oder auf der Paketseite auf „Paketdienst ändern“ und wähle **SkyPostal** (auch „Sky Postal“ findet es).

Peek liest den SkyPostal-Tracker und zeigt die Stufe („Etikett erstellt“, „Beim Paketdienst eingegangen“, „Unterwegs“, „Beim Zoll“, „In Zustellung“, „Abholbereit“, „Zustellversuch“, „Problem gemeldet“, „Zugestellt“), den englischen Scan-Wortlaut sowie die Stadt des Partners oder ersatzweise den Flughafencode von SkyPostal. Die 3-Buchstaben-Codes bestimmen die Stufe und bleiben in jeder Peek-App ausgeblendet. SkyPostal liefert Zeiten ohne Zeitzonenangabe: Peek datiert jeden Scan in der Zeitzone seines Ortes (New-York-Zeit für `MIA`, São-Paulo-Zeit für `GRU`, Bogotá- oder Lima-Zeit für dortige Scans), einen Scan ohne Ort nach dem Nachbar-Scan, und zeigt alle Zeiten in der Uhrzeit deines Geräts an. Partner-Scans können über zeitversetzte Feeds doppelt eintreffen, sodass ein früherer Schritt nach einer Zustellung diese nicht rückgängig macht. Zustellnachweise, Kommentare und die eigene Nummer des Partners speichert Peek nicht, und keine Peek-App zeigt den Namen des Unterzeichners. Voraussichtlicher Liefertag, Gewicht und gesonderte Zustellzeit fehlen im SkyPostal-Tracker.

Solange du das Paket in Peek geöffnet hast oder am Zustelltag Benachrichtigungen aktiviert sind, fragt Peek bis zu alle 10 Minuten nach; an dem Tag, an dem SkyPostal `OFD` (`OUT FOR DELIVERY`) meldet, ab 6 Uhr auf der Uhr des neuesten Scans alle 2 Minuten, solange der SkyPostal-Tracker antwortet. Andernfalls prüft Peek stündlich. Nach 12 Stunden mit lediglich „Etikett erstellt“ oder „Beim Paketdienst eingegangen“ oder nach zwei Tagen ohne Neuigkeiten prüft Peek stündlich, egal wie du die Sendung verfolgst, und nach sieben Tagen auf „Etikett erstellt“ alle 6 Stunden. Antwortet der SkyPostal-Tracker nicht und Peek verfolgt das Paket allein über Tracking-Dienste, prüft Peek stündlich, nach einem ruhigen Tag alle 3 Stunden und an dem Tag, an dem das Paket in Zustellung ist, bis zu alle 15 Minuten, solange du die Sendung geöffnet hast oder Benachrichtigungen aktiviert sind.

Ist der neueste Scan vier Tage alt und das Paket steht noch auf „Etikett erstellt“, „Beim Paketdienst eingegangen“ oder „Unterwegs“, markiert Peek es mit dem Tag dieses Scans, etwa „Keine Aktualisierung seit Mo 12. Okt“. Auf der Paketseite steht dann „Auf langen Flügen, beim Zoll oder übers Wochenende wird es oft still um ein Paket. Peek fragt weiter bei SkyPostal nach.“ samt dem Link „Hol dir Hilfe bei SkyPostal“ zur Kundenservice-Seite. Nach drei Wochen ohne neuen Scan heißt es „Seit über drei Wochen nichts Neues. Ist das Paket noch nicht angekommen, frag beim Verkäufer nach. Peek fragt weiter bei SkyPostal nach.“

Du kannst Benachrichtigungen für „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“ wählen. Peek überwacht nur Sendungsaktualisierungen und kann weder Steuer-IDs übermitteln noch Zölle in Lateinamerika abfertigen oder SkyPostal für dich kontaktieren.

## Fragen zur Sendungsverfolgung bei SkyPostal

### Wie sieht eine SkyPostal-Sendungsnummer aus?

Typische SkyPostal-Codes sind 17-stellige `SKAMZUS`-Nummern (`SKAMZUS0482917365`, erfunden) für Amazon-US-Exporte und 15-stellige `SP...BR`-Nummern (`SP04829173605BR`, erfunden) für Brasilien; SkyPostal akzeptiert aber auch andere Händler-Referenzen mit 8 bis 40 Zeichen.

### Was bedeutet `DTP` (`DELIVERED FROM AIRLINE TO OPERATOR`) bei SkyPostal?

Das bedeutet nicht, dass dein Paket bei dir zu Hause zugestellt wurde. `DTP` heißt, dass die Fluggesellschaft die Sammelsendung am Zielflughafen an den Bodenabfertiger übergeben hat — vor der Zollabfertigung (`CEN`) und der Übergabe an den lokalen Zusteller (`TTC`).

### Was bedeutet `HTI` (`HOLD FOR PROBLEMATIC TAX ID`)?

Der Zoll im Zielland hat eine fehlende oder nicht übereinstimmende Steuer-ID des Empfängers beanstandet (etwa CPF in Brasilien oder RFC in Mexiko). Kontaktiere deinen Händler und schreibe an die passende Länder-Supportadresse auf [skypostal.com/customer-service](https://www.skypostal.com/customer-service).

### Wie erreiche ich den SkyPostal-Kundenservice?

Schreibe unter Angabe deiner Sendungsnummer an die zuständige Länderadresse (`Brazilcs@`, `Mexicocs@`, `Colombiacs@`, `Perucs@`, `Ecuadorcs@` oder `customerservice@skypostal.com`); laut SkyPostal dauert eine Antwort 3–5 Werktage.

:::sources
- [SkyPostal: Official Website](https://www.skypostal.com/) – US-Inlandsversand und Lateinamerika-Export, Sitz Miami FL
- [SkyPostal: About Us](https://www.skypostal.com/about-us) – Unternehmensgeschichte und privates Zustellnetzwerk in Lateinamerika
- [SkyPostal: Customer Service](https://www.skypostal.com/customer-service) – Support-E-Mails nach Land (Brasilien, Mexiko, Kolumbien, Peru, Ecuador, allgemein), Hinweis zu Amazon/Temu/eBay und 3–5 Werktage Antwortzeit (von Peek verlinkte Hilfeseite)
- [SkyPostal: Tracking Portal](https://tracking.skypostal.com/) – öffentlicher Einzelpaket-Tracker
- [Universal Parcel Scraper: skypostal](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/skypostal) – Erkennungsregeln für `SKAMZUS` und `SP...BR`, Linkregeln für `tracking.skypostal.com`, 3-Buchstaben-Code-Zuordnung (`DRC`–`DEL`, `DTP`, `HTI`) und Zeitzonen der Scans
:::
