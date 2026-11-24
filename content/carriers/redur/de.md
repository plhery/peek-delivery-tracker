---
title: Redur Sendungsverfolgung: 14-stellige Codes & Hilfe
description: Verfolge ein Redur-Paket in Spanien, Portugal oder Europa: 14-stellige Sendungsnummern, Depot- und Zustellscans, Filialabholung, Eurodis und Kontakt.
slug: redur-sendungsverfolgung
published: 2026-11-24
updated: 2026-11-24
---

**Redur** (*Redur S.A.*) ist ein spanischer Express- und Logistikdienstleister mit über 50-jähriger Geschichte und Hauptsitz in Algete bei Madrid ([redur.es](https://redur.es/)). Mit einem zu 100 % eigengeführten Netz aus über 61 Depots (*delegaciones*) und 3.000 Mitarbeitenden auf dem spanischen Festland, den spanischen Inseln und in Portugal ist Redur zudem Gründungsmitglied von **Eurodis**, dem europäischen Kombi-Frachtnetz für Pakete und Paletten. Neben dringendem nationalem B2B-/B2C-Versand und täglichen internationalen Lkw-Abfahrten quer durch Europa betreibt Redur GDP-zertifizierte Pharmalogistik (*Farma 15-25*) und Kontraktlogistik. Du kannst eine Sendung auf [redur.es/track-trace/](https://redur.es/track-trace/) prüfen oder deine 14-stellige Redur-Nummer bzw. deinen Tracking-Link oben einfügen.

## Redur-Sendungsnummern (14-stelliges Format)

Redur-Sendungsnummern (*N° Tracking*) bestehen aus 14 Ziffern ohne Buchstaben oder Leerzeichen:

| Format (erfunden) | Worum es sich handelt |
| --- | --- |
| `58492716380419` | Standardmäßige 14-stellige Redur-Sendungsnummer (*NTRACKING*). |
| `61738492051628` | Alternativer 14-stelliger Redur-Code für Spanien, Portugal oder Eurodis-Strecken. |

Auf [redur.es/track-trace/](https://redur.es/track-trace/) sucht das Formular auch nach Expeditionsnummer sowie Absender- oder Partnerreferenzen, während Peek die 14-stellige Sendungsnummer (`NTRACKING`) oder einen direkten Link `redur.es/track-trace/?...&valor=` nutzt. Anhand der reinen Nummer erkennt Peek Redur nicht: Eine bloße 14-stellige Zahl geht an DPD, [SEUR](carrier:seur) und andere Paketdienste (in Spanien zuerst an SEUR); füge daher den Redur-Link ein oder wähle Redur manuell. Siehe [Sendungsnummern-Formate](guide:tracking-number-formats).

## Was die Redur-Sendungsstatus bedeuten

Die Redur-Trackingseite zeigt eine 7-stufige Fortschrittsleiste zusammen mit Depot-Scanzeilen (auf Spanisch unter `redur.es/track-trace/` oder auf Englisch auf der englischen Seite):

| Redur meldet (ES / EN) | Was das für dich bedeutet | Peek zeigt |
| --- | --- | --- |
| `DOCUMENTACION` / `DATA ENTERING` | Elektronische Versanddaten vom Absender übermittelt, noch vor physischem Depoteingang (nur auf den Tag datiert). | „Etikett erstellt“ |
| `ENTRADA PLAZA ORIGEN` / `INBOUND ORIGIN DEPOT` | Erster physischer Eingangsscan im Ausgangsdepot von Redur. | „Beim Paketdienst eingegangen“ |
| `SALIDA PLAZA ORIGEN` (`OUTBOUND ORIGIN DEPOT`), `ENTRADA/SALIDA PLAZA TRANSITO` (`INBOUND/OUTBOUND TRANSIT DEPOT`), `ENTRADA PLAZA DESTINO` (`INBOUND FINAL DEPOT`) | Unterwegs zwischen Ausgangs-, Transit- und Zieldepots in Spanien oder Portugal. | „Unterwegs“ |
| `CARGA CAMION T. INTERNACIONAL` / `TRUCK LOAD INTERNATIONAL TRAFFIC` | Auf einen Lkw für ein anderes Land verladen, wo ein Partner zustellt. | „Unterwegs“ |
| `PENDIENTE DE FECHA CONCERTADA`, `FECHA DE ENTREGA CONCERTADA` / `BOOKING AGREED DATE` | Wartet auf die Terminabsprache mit dem Empfänger oder Liefertermin wurde bestätigt. | „Unterwegs“ |
| `SALIDA A REPARTO` / `OUT FOR DELIVERY` | Heute im lokalen Redur-Zustellfahrzeug unterwegs. | „In Zustellung“ |
| `RECOGERÁN EN AGENCIA` | Im Redur-Zieldepot zur Abholung durch den Empfänger bereitgestellt. | „Abholbereit“ |
| `DESTINATARIO AUSENTE`, `INCORRECT ADDRESS`, `CERRADO PERMANENTE/TEMPORAL` | Zustellversuch fehlgeschlagen, da niemand anwesend war, die Adresse fehlerhaft war oder die Räume geschlossen waren. | „Zustellversuch“ |
| `DEVOLUCIÓN A ORIGEN` | Nach fehlgeschlagener Zustellung oder Annahmeverweigerung auf dem Rückweg zum Absender. | „Zurück an Absender“ |
| `ENTREGA` / `DELIVERY` | An den Empfänger zugestellt (oder über den letzten aktiven Schritt der Redur-Fortschrittsleiste bestätigt). | „Zugestellt“ |

## Spanien, Portugal und europäische Eurodis-Dienste

Laut [Redur](https://redur.es/) und [Redur Services](https://redur.es/servicios/):

- **Inland Iberien (*Servicio Doméstico*):** Eilpaket-, Paletten- und Kombifracht-Verteilung auf dem spanischen Festland, den Inseln und in Portugal über mehr als 61 eigene Depots, unterstützt durch E-Mail- und SMS-Meldungen über *Redur Connected* sowie das Sendungstool *Maia*.
- **International Europa (*Servicio Internacional*):** Tägliche Europa-Abfahrten für B2B- und B2C-Sendungen über das **Eurodis**-Partnernetz. Sobald `CARGA CAMION T. INTERNACIONAL` erscheint, übernimmt ein lokaler Eurodis-Partnerkurier im Zielland die Schlusszustellung.
- **Temperaturgeführte Pharmalogistik (*Farma 15-25*):** GDP-konforme Verteilung bei 15–25 °C für Arzneimittel und Gesundheitsprodukte.

## Verpasste Zustellung, Terminabsprache und vermisste Pakete

- **`PENDIENTE DE FECHA CONCERTADA` (wartet auf Terminabsprache):** Redur wartet darauf, vor der Auslieferung einen Liefertag mit dir abzustimmen; `FECHA DE ENTREGA CONCERTADA` oder `BOOKING AGREED DATE` heißt, dass ein Tag vereinbart wurde. Prüfe Telefon und E-Mail oder rufe Redur mit deiner 14-stelligen Nummer an.
- **`DESTINATARIO AUSENTE`, `INCORRECT ADDRESS` oder `RECOGERÁN EN AGENCIA`:** Kontaktiere rasch deinen Verkäufer oder den Redur-Kundenservice mit deinem 14-stelligen Code, um die Adresse zu prüfen, einen neuen Zustellversuch zu planen oder die Öffnungszeiten für die Depotabholung zu erfragen.
- **Verspätete, beschädigte oder vermisste Sendungen:** Informiere zuerst den Onlineshop oder Absender (er ist Vertragspartner von Redur und kann eine offizielle Reklamation einreichen) und kläre mit Redur den aktuellen Depotstandort. Siehe [zugestellt, aber nicht erhalten](guide:delivered-not-received), [beschädigtes Paket](guide:damaged-parcel), [verlorenes Paket](guide:lost-parcel), [zurück an Absender](guide:returned-to-sender) und [warum die Sendungsverfolgung nicht aktualisiert](guide:tracking-not-updating).

## Redur Kundenservice und Zentrale Madrid

Offizielle Kontaktdaten auf [Redur Contacto](https://redur.es/contacto/) und [Redur Services](https://redur.es/servicios/):

- **Zentrale Telefonnummer (Spanien):** `(+34) 916 20 46 10` (Fax `91 628 15 60`).
- **E-Mail:** `info@redur.es`.
- **Kontaktformular & Depotverzeichnis:** [redur.es/contacto/](https://redur.es/contacto/).
- **Hauptsitz (*Servicios Centrales Redur*):** *Redur S.A.*, Carretera Algete–Madrid Km 2.5 (P.I. Redur), 28110 Algete, Madrid, Spanien.

> Vorsicht vor gefälschten SMS, die eine kleine Onlinezahlung verlangen, um eine Lieferung in Spanien oder Portugal neu zu planen. Prüfe Liefermeldungen immer zuerst über [redur.es/track-trace/](https://redur.es/track-trace/) oder beim Verkäufer. Siehe [Betrugs-SMS zu Paketlieferungen](guide:delivery-scam-texts).

## Ein Redur-Paket mit Peek verfolgen

Füge einen direkten Link `redur.es/track-trace/?...&valor=` in [Peek](/) ein, und Peek erkennt Redur automatisch. Da bloße 14-stellige Nummern (`58492716380419` oder `61738492051628`, erfunden) von vielen europäischen Paketdiensten genutzt werden und Redur kein exklusives Präfix hat, gib deine 14-stellige Nummer ein und tippe beim Hinzufügen auf „Ändern“ oder in der Sendungsansicht auf „Paketdienst ändern“, um **Redur** auszuwählen (auch die Suche nach „Redur Spain“, „Redur Portugal“ oder „Redur S.A.“ findet den Dienst); Peek prüft die Nummer dann direkt bei Redur und zeigt „Redur hat dieses Paket.“, sobald bestätigt.

Peek liest den Redur-Tracker aus und zeigt die Phase („Etikett erstellt“, „Beim Paketdienst eingegangen“, „Unterwegs“, „In Zustellung“, „Abholbereit“, „Zustellversuch“, „Zurück an Absender“, „Zugestellt“), den Redur-Text und das Depot an. Leuchtet der letzte Schritt in der Fortschrittsleiste von Redur auf, zeigt Peek das Paket als zugestellt. Redur liefert Scan-Zeiten ohne Zeitzone: Peek datiert jeden Scan auf die Zeitzone seines Depots (Madrid für `ALGETE`, Lissabon für `LISBOA`, Kanaren für `LAS PALMAS`) und einen Scan aus einem nicht zuordenbaren Depot auf die Zeitzone des Nachbarscans, und zeigt alles in der Uhrzeit deines Geräts an; `DOCUMENTACION` behält nur das Datum. Peek übernimmt keine Packstückzahl. Der Redur-Tracker liefert kein voraussichtliches Lieferdatum, kein Gewicht, keinen Servicenamen, keine eigene Zustellzeit und keine Partnernummer.

Solange du das Paket in Peek geöffnet hast oder am Zustelltag Benachrichtigungen aktiviert sind, fragt Peek bis zu alle 10 Minuten nach; an dem Tag, an dem Redur `SALIDA A REPARTO` oder `OUT FOR DELIVERY` meldet, ab 6 Uhr nach der Uhr des Depots des neuesten Scans alle 2 Minuten, solange der Redur-Tracker antwortet. Andernfalls prüft Peek stündlich. Nach 12 Stunden mit lediglich „Etikett erstellt“ oder „Beim Paketdienst eingegangen“ oder nach zwei Tagen ohne Neuigkeiten fragt Peek stündlich nach, egal wie du die Sendung verfolgst, und nach sieben Tagen auf „Etikett erstellt“ alle 6 Stunden. Antwortet der Redur-Tracker nicht und Peek verfolgt das Paket allein über Tracking-Dienste, prüft Peek stündlich, nach einem ruhigen Tag alle 3 Stunden und an dem Tag, an dem das Paket in Zustellung ist, bis zu alle 15 Minuten, solange du die Sendung geöffnet hast oder Benachrichtigungen aktiviert sind.

Ist der neueste Scan vier Tage alt und das Paket steht noch auf „Etikett erstellt“, „Beim Paketdienst eingegangen“ oder „Unterwegs“, markiert Peek es mit dem Tag dieses Scans, etwa „Keine Aktualisierung seit Mo 12. Okt“. Auf der Paketseite steht dann „Auf langen Flügen, beim Zoll oder übers Wochenende wird es oft still um ein Paket. Peek fragt weiter bei Redur nach.“, samt Link „Hol dir Hilfe bei Redur“ zur Kontaktseite von Redur. Nach drei Wochen ohne neuen Scan heißt es „Seit über drei Wochen nichts Neues. Ist das Paket noch nicht angekommen, frag beim Verkäufer nach. Peek fragt weiter bei Redur nach.“

Bei den Benachrichtigungen kannst du zwischen „Jeden Scan“, „Wichtige Schritte“ und „Nur die Zustellung“ wählen. Peek beobachtet ausschließlich Tracking-Updates und kann weder Liefertermine vereinbaren noch Adressen ändern oder Redur für dich kontaktieren.

## Häufige Fragen zur Redur-Sendungsverfolgung

### Wie sieht eine Redur-Sendungsnummer aus?

Redur-Sendungsnummern bestehen aus 14 Ziffern ohne Buchstaben oder Leerzeichen (zum Beispiel `58492716380419`, erfunden).

### Was bedeutet `PENDIENTE DE FECHA CONCERTADA` bei Redur?

Es bedeutet, dass Redur vor der Auslieferung darauf wartet, mit dir einen Liefertag zu vereinbaren.

### Was bedeutet `RECOGERÁN EN AGENCIA`?

Es bedeutet, dass dein Paket in der örtlichen Redur-Filiale (*delegación*) zur Abholung für dich bereitliegt.

### Wie erreiche ich den Redur-Kundenservice?

Rufe die spanische Zentrale von Redur unter `(+34) 916 20 46 10` an, schreibe an `info@redur.es` oder nutze die Kontaktseite unter [redur.es/contacto/](https://redur.es/contacto/).

:::sources
- [Redur: Offizielle Website](https://redur.es/) – über 50 Jahre, 61+ Depots in Spanien und Portugal, 3.000 Mitarbeitende, Eurodis-Gründungsmitglied, *Maia* und *Redur Connected*
- [Redur: Track & Trace](https://redur.es/track-trace/) – öffentliche Sendungsverfolgung
- [Redur: Servicios](https://redur.es/servicios/) – *Servicio Doméstico*, *Servicio Internacional*, *Farma 15-25* (GDP) und *Logística integral*
- [Redur: Contacto](https://redur.es/contacto/) – `(+34) 916 20 46 10`, `info@redur.es` und Hauptsitz Algete (Madrid) (von Peek verlinkte Hilfeseite)
- [Universal Parcel Scraper: redur](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/redur) – 14-stelliges Format, Link-Regel `redur.es/track-trace/`, spanisch-englische Scan-Zuordnung, Auswertung der Zustell-Fortschrittsleiste und Datierung jedes Scans auf die Zeitzone seines Depots
:::
